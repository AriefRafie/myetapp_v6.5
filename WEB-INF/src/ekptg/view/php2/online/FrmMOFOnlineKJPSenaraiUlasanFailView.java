/**
 * 
 */
package ekptg.view.php2.online;

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
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.php2.FrmPLPHeaderData;
import ekptg.model.php2.FrmPLPJabatanTeknikalData;
import ekptg.model.php2.online.FrmMOFOnlineKJPSenaraiUlasanFailData;

public class FrmMOFOnlineKJPSenaraiUlasanFailView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
    static Logger myLog = Logger.getLogger(FrmMOFOnlineKJPSenaraiUlasanFailView.class);

    FrmPLPHeaderData logicHeader = new FrmPLPHeaderData();
    FrmPLPJabatanTeknikalData logicJabatanTeknikal = new FrmPLPJabatanTeknikalData();
    FrmMOFOnlineKJPSenaraiUlasanFailData logic = new FrmMOFOnlineKJPSenaraiUlasanFailData();
	private String templateDir = "app/php2/online/ulasanKJP/plp";


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
		myLog.info(" command : "+command);
		context.put("command", command);
		
		context.put("templateDir", templateDir);

		String idFail = getParam("idFail");
		String idUlasanTeknikal = getParam("idUlasanTeknikal");
		String idKementerian = "";
		Vector listDetailKJP = null;
		

		try {
			String userId1 = (String) session.getAttribute("_ekptg_user_id");
			listDetailKJP = logic.getIdNegeriKJPByUserId(userId1);

			if (!listDetailKJP.isEmpty() && listDetailKJP.size() > 0) {
				Hashtable hashRayuanDB = (Hashtable) listDetailKJP.get(0);
				idKementerian = hashRayuanDB.get("idKementerian").toString();

				myLog.info("IDKEMENTERIAN="+hashRayuanDB.get("idKementerian").toString());

			}

			this.context.put("idKementerian", idKementerian);
			this.context.put("onload", "");
			this.context.put("completed", false);
			
			if ("refreshDokumenMuatNaik".equals(command)) {
				
				logicJabatanTeknikal.setMaklumatKJP(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				Hashtable maklumatUlasan = (Hashtable) logicJabatanTeknikal.getBeanMaklumatKJP().get(0);
				this.context.put("maklumatUlasan", maklumatUlasan);
				this.context.put("idUlasanTeknikal", idUlasanTeknikal);
				
				Hashtable lampiran = logic.getMaklumatLampiran(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				this.context.put("lampiran", lampiran);
				
				vm = "/maklumatUlasan.jsp";	
				
			} else if ("muatNaikDokumen".equals(command)) {
				
				logic.hapusDokumen(idUlasanTeknikal);
				uploadFiles(idUlasanTeknikal, session);
				
				vm = "/refreshDokumenMuatNaik.jsp";	
				
			} else if ("hantarUlasan".equals(command)) {
				context.remove("flagStatus");
				
				String userId = (String) session.getAttribute("_ekptg_user_id");
				String txtTarikhSurat = getParam("txtTarikhSurat");
				String txtNoRujukanSurat = getParam("txtNoRujukanSurat");
				String txtUlasan = getParam("txtUlasan");				
				String txtKeputusan = getParam("txtKeputusan");
				String txtNamaPengulas = getParam("txtNamaPengulas");
				String txtNoTelPengulas = getParam("txtNoTelPengulas");
				
				String flagStatus = logic.hantarUlasan(idUlasanTeknikal, txtTarikhSurat, txtNoRujukanSurat, txtUlasan, txtKeputusan, 
						txtNamaPengulas, txtNoTelPengulas, userId);
				this.context.put("flagStatus", flagStatus);
				
				logicJabatanTeknikal.setMaklumatKJP(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				Hashtable maklumatUlasan = (Hashtable) logicJabatanTeknikal.getBeanMaklumatKJP().get(0);
				this.context.put("maklumatUlasan", maklumatUlasan);
				this.context.put("idUlasanTeknikal", idUlasanTeknikal);
				
				Hashtable lampiran = logic.getMaklumatLampiran(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				this.context.put("lampiran", lampiran);
				
				vm = "/maklumatUlasan.jsp";
			
			} else if ("simpanUlasan".equals(command)) {
				context.remove("flagStatus");
				
				String userId = (String) session.getAttribute("_ekptg_user_id");
				String txtTarikhSurat = getParam("txtTarikhSurat");
				String txtNoRujukanSurat = getParam("txtNoRujukanSurat");
				String txtUlasan = getParam("txtUlasan");				
				String txtKeputusan = getParam("txtKeputusan");
				String txtNamaPengulas = getParam("txtNamaPengulas");
				String txtNoTelPengulas = getParam("txtNoTelPengulas");
				
				String flagStatus = logic.simpanUlasan(idUlasanTeknikal, txtTarikhSurat, txtNoRujukanSurat, txtUlasan, txtKeputusan, 
						txtNamaPengulas, txtNoTelPengulas, userId);
				this.context.put("flagStatus", flagStatus);
				
				logicJabatanTeknikal.setMaklumatKJP(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				Hashtable maklumatUlasan = (Hashtable) logicJabatanTeknikal.getBeanMaklumatKJP().get(0);
				this.context.put("maklumatUlasan", maklumatUlasan);
				this.context.put("idUlasanTeknikal", idUlasanTeknikal);
				
				Hashtable lampiran = logic.getMaklumatLampiran(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				this.context.put("lampiran", lampiran);
				
				vm = "/maklumatUlasan.jsp";
			
			} else if ("paparFail".equals(command)) {
				//TO CLEAR CONTEXT
				context.remove("BeanHeader");
				context.remove("BeanMaklumatTanah");
				context.remove("lampiran");
				context.remove("flagStatus");
				
				setMaklumatHeader(idFail, session);
				setMaklumatTanah(idFail, session);
				
				logicJabatanTeknikal.setMaklumatKJP(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				Hashtable maklumatUlasan = (Hashtable) logicJabatanTeknikal.getBeanMaklumatKJP().get(0);
				
				Vector maklumatLampiran = null;
				maklumatLampiran = new Vector();
				logicJabatanTeknikal.setLampiranKJP(logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				maklumatLampiran = logicJabatanTeknikal.getBeanMaklumatLampiranKJP();
				
				this.context.put("maklumatUlasan", maklumatUlasan);
				this.context.put("idUlasanTeknikal", idUlasanTeknikal);
				this.context.put("maklumatLampiran", maklumatLampiran);
				
				Hashtable lampiran = logic.getMaklumatLampiran(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				this.context.put("lampiran", lampiran);
				
				vm = "/start.jsp";
			
			} else if ("carian".equals(command)) {
				
				String userId = (String) session.getAttribute("_ekptg_user_id");
				String findNoFail = getParam("findNoFail");
				String findTajukFail = getParam("findTajukFail");				
				String findPemohon = getParam("findPemohon");
				String findNoPengenalan = getParam("findNoPengenalan");
				String findTarikhTerima = getParam("findTarikhTerima");
				String findNoHakmilik = getParam("findNoHakmilik");
				String findNoWarta = getParam("findNoWarta");
				String findNoPegangan = getParam("findNoPegangan");
				String findJenisHakmilik = getParam("findJenisHakmilik");
				if(findJenisHakmilik.equals(""))
				{
					findJenisHakmilik = "9999";
				}
				String findJenisLot = getParam("findJenisLot");
				if(findJenisLot.equals(""))
				{
					findJenisLot = "9999";
				}
				String findNoLot = getParam("findNoLot");
				
				String findNegeri = getParam("findNegeri");
				if(findNegeri.equals(""))
				{
					findNegeri = "9999";
				}
				String findDaerah = getParam("findDaerah");
				if(findDaerah.equals(""))
				{
					findDaerah = "9999";
				}
				String findMukim = getParam("findMukim");
				if(findMukim.equals(""))
				{
					findMukim = "9999";
				}
				
				Vector listFail = logic.getSenaraiFail(findNoFail, findTajukFail, findPemohon, findNoPengenalan, findTarikhTerima, 
						findNoHakmilik, findNoWarta, findNoPegangan, findJenisHakmilik, findJenisLot, findNoLot, findNegeri, findDaerah, findMukim, userId);
				this.context.put("SenaraiFail", listFail);
				setupPage(session, action, listFail);
				
				context.put("findNoFail", findNoFail);
				context.put("findTajukFail", findTajukFail);
				context.put("findPemohon", findPemohon);
				context.put("findNoPengenalan", findNoPengenalan);
				context.put("findTarikhTerima", findTarikhTerima);
				context.put("findNoHakmilik", findNoHakmilik);
				context.put("findNoWarta", findNoWarta);
				context.put("findNoPegangan", findNoPegangan);
				/*context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("findJenisHakmilik",Long.parseLong(findJenisHakmilik), "", ""));
				context.put("selectLot", HTML.SelectLot("findJenisLot",Long.parseLong(findJenisLot), "", ""));
				context.put("findNoLot", findNoLot);
				context.put("selectNegeri", HTML.SelectNegeri("findNegeri",Long.parseLong(findNegeri), ""," onChange=\"doChangeNegeri();\""));
				context.put("selectDaerah", HTML.SelectDaerahByIdNegeri(findNegeri, "findDaerah", Long.parseLong(findDaerah), ""," onChange=\"doChangeDaerah();\""));
				context.put("selectMukim", HTML.SelectMukimByDaerah(findDaerah, "findMukim", Long.parseLong(findMukim), "",""));
*/
				vm = "/start.jsp";
				
			} else {
				
				String userId = (String) session.getAttribute("_ekptg_user_id");
				Vector listFail = logic.getSenaraiFail(null, null, null, null, null, null, null, null, null, null, null, null, null, null, userId);
				this.context.put("SenaraiFail", listFail);
				setupPage(session, action, listFail);
				
				context.remove("findNoFail");
				context.remove("findTajukFail");
				context.remove("findPemohon");
				context.remove("findNoPengenalan");
				context.remove("findTarikhTerima");
				context.remove("findNoHakmilik");
				context.remove("findNoWarta");
				context.remove("findNoPegangan");;
				context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("findJenisHakmilik",Long.parseLong("9999"), "", ""));
				context.put("selectLot", HTML.SelectLot("findJenisLot",Long.parseLong("9999"), "", ""));
				context.remove("findNoLot");
				context.put("selectNegeri", HTML.SelectNegeri("findNegeri",Long.parseLong("9999"), ""," onChange=\"doChangeNegeri();\""));
				context.put("selectDaerah", HTML.SelectDaerahByIdNegeri("9999", "findDaerah", Long.parseLong("9999"), ""," onChange=\"doChangeDaerah();\""));
				context.put("selectMukim", HTML.SelectMukimByDaerah("9999", "findMukim", Long.parseLong("9999"), "",""));
				
				vm = "/start.jsp";
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		
		return templateDir + vm;
	}

	private void uploadFiles(String idUlasanTeknikal, HttpSession session) throws Exception {
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
					saveData(item, idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal), session);
				}
			}
		}			
	}

	private void saveData(FileItem item, String idUlasanTeknikal, String idPermohonan,
			HttpSession session) {
		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id"); 
		
		try {
			db = new Db();
			// TBLPHPDOKUMEN
			long idDokumenUpload = DB.getNextID("TBLPHPDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("insert into TBLPHPDOKUMEN "
							+ "(ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, ID_MASUK, TARIKH_MASUK, CONTENT, JENIS_MIME, NAMA_FAIL, ID_ULASANTEKNIKAL, FLAG_DOKUMEN, ID_PERMOHONAN) "
							+ "values(?,?,?,?,SYSDATE,?,?,?,?,?,?)");
			ps.setLong(1, idDokumenUpload);
			ps.setString(2, null);
			ps.setString(3, null);
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, idUlasanTeknikal);
			ps.setString(9, "L");
			ps.setString(10, idPermohonan);
			ps.executeUpdate();
			
			con.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
				
		this.context.put("flagStatus", "Y");
		this.context.put("idUlasanTeknikalReload", idUlasanTeknikal);		
	}

	private void setMaklumatTanah(String idFail, HttpSession session) throws Exception {
		String flagBorangK = "";
		logicHeader.setMaklumatHakmilik(logicHeader.getIdHakmilikPermohonanByIdFail(idFail));
		if (logicHeader.getBeanMaklumatHakmilik().size() != 0){
			Hashtable hashHakmilik = (Hashtable) logicHeader.getBeanMaklumatHakmilik().get(0);
			flagBorangK = (String) hashHakmilik.get("flagBorangK");			
		}
		this.context.put("flagBorangK", flagBorangK);
		
		if ("Y".equals(flagBorangK)){
			Vector beanMaklumatBorangK = new Vector();
			beanMaklumatBorangK = logicHeader.getBeanMaklumatHakmilik();
			this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);
		} else {
			Vector beanMaklumatTanah = new Vector();
			beanMaklumatTanah = logicHeader.getBeanMaklumatHakmilik();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
		}
	}

	private void setMaklumatHeader(String idFail, HttpSession session) throws Exception {
		Vector beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);		
	}
	
	public void setupPage(HttpSession session, String action, Vector list) {

		try {

			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10: getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage");
			}

			if ("getNext".equals(action)) {
				page++;
			} else if ("getPrevious".equals(action)) {
				page--;
			} else if ("getPage".equals(action)) {
				page = getParamAsInteger("value");
			} else if ("doChangeItemPerPage".equals(action)) {
				itemsPerPage = getParamAsInteger("itemsPerPage");
			}

			Paging paging = new Paging(session, list, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1; // reset page number
			this.context.put("SenaraiFail", paging.getPage(page));
			this.context.put("page", new Integer(page));
			this.context.put("itemsPerPage", new Integer(itemsPerPage));
			this.context.put("totalPages", new Integer(paging.getTotalPages()));
			this.context.put("startNumber", new Integer(paging.getTopNumber()));
			this.context.put("isFirstPage", new Boolean(paging.isFirstPage()));
			this.context.put("isLastPage", new Boolean(paging.isLastPage()));

		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error", e.getMessage());
		}
	}
}
