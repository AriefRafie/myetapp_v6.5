package ekptg.intergration.eTanah.pengambilan;

import integrasi.rest.etanah.wpkl.RESTInvoker;
import integrasi.rest.etanah.wpkl.ppt.EtanahWPKLPPTManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class PopupETanahPPTWPKL extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger myLog = Logger.getLogger(PopupETanahPPTWPKL.class);

	PopupETanahPPTWPKLData logic = new PopupETanahPPTWPKLData();

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}
		String templateDir = "app/integrasi/etanah/wpkl/ppt";
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String command = getParam("command");
		context.put("command", command);
		context.put("templateDir", templateDir);
		String userID = (String) session.getAttribute("_portal_username");

		String idFail = getParam("idFail");
		String idPermohonan = getParam("idPermohonan");
		String idPPTWarta = getParam("idPPTWarta");		
		String idPPTHakmilik = getParam("idPPTHakmilik");	
		String idPPTPenarikanBalik = getParam("idPPTPenarikanBalik");	
		
		String jenisSkrin = getParam("jenisSkrin");		
		
		vm = "/start.jsp";

		String flagUrusan = "";
		if ("WartaS8".equals(jenisSkrin)) {
			flagUrusan = "D";
		} else if ("BorangK".equals(jenisSkrin)) {
			flagUrusan = "K";
		} else if ("TarikBalik".equals(jenisSkrin)) {
			flagUrusan = "PD";
		}		
		
		Db dbMain = null;
		Connection conn = null;
		try {
			dbMain = new Db();
			conn = dbMain.getConnection();
			conn.setAutoCommit(false);

			String idPermohonanIntegrasi = logic.getIdPermohonanIntegrasi(idFail, idPPTWarta, idPPTHakmilik, idPPTPenarikanBalik, flagUrusan, dbMain);
			context.remove("errorMsg");
			
			if ("hantarBorangD".equals(command)) {
				if (logic.checkSenaraiHakmilik(userID, idPermohonanIntegrasi, dbMain)) {
					EtanahWPKLPPTManager.hantarBorangD(idPermohonanIntegrasi, userID, dbMain);
				} else {
					context.put("errorMsg", "SILA SEMAK SEMULA SENARAI HAKMILIK YANG DIDAFTARKAN");
				}
			} else if ("hantarBorangK".equals(command)) {
				if (logic.checkSenaraiHakmilik(userID, idPermohonanIntegrasi, dbMain)) {
					EtanahWPKLPPTManager.hantarBorangK(idPermohonanIntegrasi, userID, dbMain);
				} else {
					context.put("errorMsg", "SILA SEMAK SEMULA SENARAI HAKMILIK YANG DIDAFTARKAN");
				}
			} else if ("hantarTarikBalik".equals(command)) {
				if (logic.checkSenaraiHakmilik(userID, idPermohonanIntegrasi, dbMain)) {
					EtanahWPKLPPTManager.hantarPenarikanBalik(idPermohonanIntegrasi, userID, dbMain);
				} else {
					context.put("errorMsg", "SILA SEMAK SEMULA SENARAI HAKMILIK YANG DIDAFTARKAN");
				}
			} else if ("deleteDokumen".equals(command)) {
				String idDokumen = getParam("idDokumen");
				logic.deleteDokumen(idDokumen, dbMain);
			} else if ("muatNaikDokumen".equals(command)) {
				String jenisDokumen = getParam("jenisDokumen");
				uploadFiles(idPermohonanIntegrasi, jenisDokumen, session, dbMain);
				
			} else if ("generatePermohonanIntegrasi".equals(command)) {
				if (flagUrusan.equals("D")) {
					idPermohonanIntegrasi = logic.generatePermohonanD(idPermohonanIntegrasi, idFail, idPPTWarta, flagUrusan, userID, dbMain);
				} else if (flagUrusan.equals("K")) {
					idPermohonanIntegrasi = logic.generatePermohonanK(idFail, idPPTHakmilik,  flagUrusan, idPermohonanIntegrasi, userID, dbMain);
				} else if (flagUrusan.equals("PD")) {
					idPermohonanIntegrasi = logic.generatePermohonanPD(idFail, idPermohonanIntegrasi, idPPTPenarikanBalik, flagUrusan, userID, dbMain);
				}
			} else if ("generatePermohonanBaruK".equals(command)) {
				idPermohonanIntegrasi = logic.generatePermohonanK(idFail, idPPTHakmilik,  flagUrusan, "", userID, dbMain);
			} else {
				if ("".equals(idPermohonanIntegrasi)) {
					if (flagUrusan.equals("D")) {
						idPermohonanIntegrasi = logic.generatePermohonanD(idPermohonanIntegrasi, idFail, idPPTWarta, flagUrusan, userID, dbMain);
					} else if (flagUrusan.equals("K")) {
						idPermohonanIntegrasi = logic.generatePermohonanK(idFail, idPPTHakmilik,  flagUrusan, idPermohonanIntegrasi, userID, dbMain);
					} else if (flagUrusan.equals("PD")) {
						idPermohonanIntegrasi = logic.generatePermohonanPD(idFail, idPermohonanIntegrasi, idPPTPenarikanBalik, flagUrusan, userID, dbMain);
					}
				}
			}
			conn.commit();
			
			Hashtable maklumatPermohonan = logic.getMaklumatPermohonan(idPermohonanIntegrasi, dbMain);
			context.put("maklumatPermohonan", maklumatPermohonan);
			Vector listHakmilik = logic.getSenaraiHakmilik(idPermohonanIntegrasi, dbMain);
			this.context.put("listHakmilik", listHakmilik);
			Vector listHakmilikEndorsan = logic.getSenaraiHakmilik(idPermohonanIntegrasi, dbMain);			
			this.context.put("listHakmilikEndorsan", listHakmilikEndorsan);
			
			context.remove("flagEndorsanHakmilikBorangK");
			if (maklumatPermohonan != null) {
				if ("Y".equals(maklumatPermohonan.get("flagEndorsan"))) {
					if ("K".equals(maklumatPermohonan.get("flagUrusan"))) {
						if (listHakmilikEndorsan != null) {
							Hashtable hakmilikEndorsan = (Hashtable) listHakmilikEndorsan.get(0);
							context.put("flagEndorsanHakmilikBorangK", hakmilikEndorsan.get("statusEndorsan"));
						}
					}
				}
			}
			
			Vector listDokumen = logic.getSenaraiDokumen(idPermohonanIntegrasi, dbMain);
			this.context.put("listDokumen", listDokumen);
			Vector listDokumenEndorsan = logic.getSenaraiDokumenEndorsan(idPermohonanIntegrasi, dbMain);
			this.context.put("listDokumenEndorsan", listDokumenEndorsan);

			context.put("idFail", idFail);
			context.put("idPermohonan", idPermohonan);
			context.put("jenisSkrin", jenisSkrin);
			context.put("idPermohonanIntegrasi", idPermohonanIntegrasi);
			context.put("idPPTWarta", idPPTWarta);
			context.put("idPPTHakmilik", idPPTHakmilik);
			context.put("idPPTPenarikanBalik", idPPTPenarikanBalik);	

		} catch (SQLException se) {
			try {
				conn.rollback();
				myLog.info("ROLLBACK CALLED");
			} catch (SQLException se2) {
				myLog.info("RALAT ROLLBACK :" + se2.getMessage());
			}
			se.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
			if (dbMain != null)
				dbMain.close();
		}

		return templateDir + vm;
	}

	private void uploadFiles(String idPermohonanIntegrasi, String jenisDokumen,
		HttpSession session, Db db) throws FileUploadException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart != false) {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
					String etanahRef = RESTInvoker.uploadFile(item);
					if (!"".equals(etanahRef)) {
						myLog.info("uploadFiles idPermohonanIntegrasi:::: "+idPermohonanIntegrasi);
						saveData(item, idPermohonanIntegrasi, jenisDokumen, etanahRef, session, db);
					}
				}
			}
		}
	}

	private void saveData(FileItem item, String idPermohonanIntegrasi,
			String jenisDokumen, String etanahRef, HttpSession session, Db db) {
		String userId = (String) session.getAttribute("_ekptg_user_id");
		myLog.info("idPermohonanIntegrasi:::: "+idPermohonanIntegrasi);
		try {
			// INT_PPTDOKUMENPERMOHONAN
			long idDokumen = DB.getNextID(db, "INT_PPTDOKUMENPERMOHONAN_SEQ");
			Connection con = db.getConnection();
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO INT_PPTDOKUMENPERMOHONAN"
							+ " (ID_DOKUMENPERMOHONAN, ID_PERMOHONAN, FLAG_DOKUMEN, NAMA_DOKUMEN, CONTENT, JENIS_MIME, KOD_DOKUMEN)"
							+ " VALUES(?,?,?,?,?,?,?)");
			ps.setLong(1, idDokumen);
			ps.setString(2, idPermohonanIntegrasi);
			ps.setString(3, jenisDokumen);
			ps.setString(4, item.getName());
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, etanahRef);
			ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
	}
	
	
}
