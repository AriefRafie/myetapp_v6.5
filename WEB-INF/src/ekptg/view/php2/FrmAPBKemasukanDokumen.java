/**
 * 
 */
package ekptg.view.php2;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

import ekptg.helpers.DB;
import ekptg.model.integrasi.macgdi.FrmLaporanHTPData;
import ekptg.model.php2.FrmAPBKemasukanDokumenData;

public class FrmAPBKemasukanDokumen extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmAPBKemasukanDokumenData logic = new FrmAPBKemasukanDokumenData();
	private String templateDir = "app/php2/dokumen/apb";

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}
		
		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String command = getParam("command");
		context.put("command", command);
		context.put("templateDir", templateDir);
		
		String userRole = (String)session.getAttribute("myrole");
		context.put("userRole", userRole);
		
		try {
			if ("hapusDokumen".equals(command)) {
				String idDokumen = getParam("idDokumen");
				logic.hapusDokumen(idDokumen);
				
				String idLaporan = getParam("id");
				Hashtable laporan = logic.getMaklumatLaporan(idLaporan);				
				context.put("laporan", laporan);
				
				Vector listDokumen = logic.getSenaraiDokumen(idLaporan);
				this.context.put("listDokumen", listDokumen);
				
				vm = "/start.jsp";			
			} else if ("muatNaikDokumen".equals(command)) {
				String idLaporan = getParam("idLaporan");
				String namaDokumen = getParam("namaDokumen");
				String catatan = getParam("catatan");
				
				uploadFiles(idLaporan, namaDokumen, catatan, session);
				
				vm = "/refreshDokumen.jsp";				
			} else if ("daftarDokumen".equals(command)) {
				
				String idLaporan = getParam("id");
				Hashtable laporan = logic.getMaklumatLaporan(idLaporan);				
				context.put("laporan", laporan);
				
				vm = "/start.jsp";
			} else if ("paparLaporan".equals(command)) {
				String idLaporan = getParam("idLaporan");
				Hashtable laporan = logic.getMaklumatLaporan(idLaporan);				
				context.put("laporan", laporan);
				
				Vector listDokumen = logic.getSenaraiDokumen(getParam("idLaporan"));
				this.context.put("listDokumen", listDokumen);
				
				vm = "/start.jsp";
			} else {
				
				Vector listLaporan = logic.getSenaraiLaporanAPB();
				context.put("listLaporan", listLaporan);
				
				vm = "/start.jsp";
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		
		return templateDir + vm;
	}
	
	private void uploadFiles(String idLaporan, String namaDokumen,
			String catatan, HttpSession session) throws FileUploadException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart != false) {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null)
						&& (!("".equals(item.getName())))) {
					saveData(item, idLaporan, namaDokumen, catatan, session);
				}
			}
		}		
	}

/*	private void saveData(FileItem item, String idLaporan,
			String namaDokumen, String catatan, HttpSession session) {
		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id"); 
		
		try {
			db = new Db();
			// TBLPPKDOKUMENSENARAIHUTANG
			long idDokumen = DB.getNextID("TBLINTMACGDILAPORANDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO TBLINTMACGDILAPORANDOKUMEN"
							+ " (ID, ID_MACGDILAPORAN, NAMA_DOKUMEN, NAMA_FAIL, CONTENT, JENIS_MIME, CATATAN, ID_MASUK, TARIKH_MASUK)"
							+ " VALUES(?,?,?,?,?,?,?,?,SYSDATE)");
			ps.setLong(1, idDokumen);
			ps.setLong(2, Long.valueOf(idLaporan));
			ps.setString(3, namaDokumen);
			ps.setString(4, item.getName());
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, catatan);
			ps.setString(8, userId);
			ps.executeUpdate();

			con.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		context.put("idLaporanReload", idLaporan);
	}*/
	
	private void saveData(FileItem item, String idLaporan,
			String namaDokumen, String catatan, HttpSession session) {
		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id"); 
		//TBLINTMACGDILAPORANDOKUMEN
		
		try {
			db = new Db();
			long idDokumen = DB.getNextID("TBLPHPDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO TBLPHPDOKUMEN"
							+ " (ID_DOKUMEN, ID_DOKUMEN_RUJUKAN, NAMA_DOKUMEN, NAMA_FAIL, CONTENT, JENIS_MIME, CATATAN, ID_MASUK, TARIKH_MASUK)"
							+ " VALUES(?,?,?,?,?,?,?,?,SYSDATE)");
			ps.setLong(1, idDokumen);
			ps.setLong(2, Long.valueOf(idLaporan));
			ps.setString(3, namaDokumen);
			ps.setString(4, item.getName());
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, catatan);
			ps.setString(8, userId);
			ps.executeUpdate();

			con.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		context.put("idLaporanReload", idLaporan);
	}
}
