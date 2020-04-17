package ekptg.view.utils;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.utils.FrmPejabatJKPTGData;
import ekptg.view.admin.utils.JawatanInformation;
import ekptg.view.admin.utils.UsersListConnectionToDB;

/**
 * 
 * @author Azam
 * @modified Mohd Nazrul
 * 
 */
public class FrmBahagianUnit extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(ekptg.view.utils.FrmBahagianUnit.class);
	
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String id = getParam("id");
		String template = "app/utils/frmBahagianUnit.jsp";
		String submit = getParam("command"); // First Level - default by
		// AjaxBasedModule Call
		String action = getParam("action"); // Second Level
		String id_seksyen = null;
		String id_negeri = null;
		String id_daerah = null;
		Vector lists = null;
		this.context.put("result", "");

		FrmPejabatJKPTGData f = FrmPejabatJKPTGData.getInstance();		
		//myLogger.debug("submit:"+submit);
		if ("doChanges".equals(submit)) {
			id_seksyen = getParam("id_seksyen");
			id_negeri = getParam("id_negeri");
			id_daerah = getParam("id_daerah");
			context.put("id_negeri",id_negeri);
			this.context.put("id_seksyen", id_seksyen);
			this.context.put("id_negeri", id_negeri);
			this.context.put("id_daerah", id_daerah);

			this.context.put("selectSeksyen", HTML.SelectSeksyen("id_seksyen",
					Utils.parseLong(id_seksyen), null,
					"onChange=\"doChanges()\" "));
			this.context.put("selectNegeri", HTML.SelectNegeri("id_negeri",
					id_negeri == "" ? null : Utils.parseLong(id_negeri), null,
					"onChange=\"doChanges()\" "));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(
					id_negeri == "" ? "0" : id_negeri, "id_daerah", Utils
							.parseLong(id_daerah), null,
					"onChange=\"doChanges()\" "));
			
		} else if ("delete".equals(submit)) {
			f.delete(id);
			id_negeri = (String) context.get("id_negeri");
			id_seksyen = (String) context.get("id_seksyen");
			
		} else if ("edit".equals(submit)) {
			this.context.put("mode", "edit");
			template = "app/utils/frmEditBahagianUnit.jsp";
			Hashtable details = f.getPejabatJKPTGDetails(id);
			this.context.put("details", (Hashtable) details);
			// Re-do
			if ("filterDaerah".equals(action)) {
				id_negeri = getParam("Form_id_negeri");
			} else {
				id_negeri = (String) details.get("id_negeri");
			}
			id_seksyen = (String) details.get("id_seksyen");
			id_daerah = (String) details.get("id_daerah");
			this.context
					.put("selectSeksyen2", HTML.SelectSeksyen(
							"Form_id_seksyen", Utils.parseLong(id_seksyen),
							null, null));
			this.context.put("selectNegeri2", HTML.SelectNegeri(
					"Form_id_negeri", id_negeri == "" ? null : Utils
							.parseLong(id_negeri), null,
					" onChange=\"doFilter();\" "));
			this.context.put("selectDaerah2", HTML.SelectDaerahByNegeri(
					id_negeri == "" ? "0" : id_negeri, "Form_id_daerah", Utils
							.parseLong(id_daerah), null, null));
			this.context.put("id", id);
			return template;
		
		} else if ("addNewPejabat".equals(submit)) {
			this.context.put("mode", "add");
			this.context.put("details", null);
			if ("filterDaerah".equals(action)) {
				id_negeri = getParam("Form_id_negeri");
			} else {
				id_negeri = (String) this.context.get("id_negeri");
			}
			if (id_negeri == null) {
				id_negeri = "0";
			}
			id_seksyen = getParam("Form_id_seksyen");
			id_daerah = getParam("Form_id_daerah");
			this.context
					.put("selectSeksyen2", HTML.SelectSeksyen(
							"Form_id_seksyen", Utils.parseLong(id_seksyen),
							null, null));
			this.context.put("selectNegeri2", HTML.SelectNegeri(
					"Form_id_negeri", id_negeri == "" ? null : Utils
							.parseLong(id_negeri), null,
					" onChange=\"doFilter2();\" "));
			this.context.put("selectDaerah2", HTML.SelectDaerahByNegeri(
					id_negeri == "" ? "0" : id_negeri, "Form_id_daerah", Utils
							.parseLong(id_daerah), null, null));
			template = "app/utils/frmEditBahagianUnit.jsp";
			return template;
		
		} else if ("doAddNew".equals(submit)) {
			this.context.put("mode", "doAdd");
			template = "app/utils/frmEditBahagianUnit.jsp";
			Hashtable parameters = new Hashtable();
			setParameterValues(parameters);
			if (f.insert(parameters))
				this.context.put("result", "success");
			else
				this.context.put("result", "failed");
		
		} else if ("doUpdate".equals(submit)) {
			this.context.put("mode", "update");
			template = "app/utils/frmEditPejabatJKPTG.jsp";
			Hashtable parameters = new Hashtable();
			setParameterValues(parameters);
			if (f.update(parameters, id))
				this.context.put("result", "success");
			else
				this.context.put("result", "failed");
			Hashtable details = f.getPejabatJKPTGDetails(id);
			this.context.put("details", (Hashtable) details);
			return template;
		
		} else if ("view-daerahjagaan".equals(submit)) {
			template = "app/utils/frmDaerahJagaan.jsp";
			List<DaerahJagaanInformation> listDaerah = f.getListDaerahJagaan(id);
			List<PegawaiInformation> listPegawai = f.getListingPegawai(id);
			//setupPage(session, action, listDaerah);
			Hashtable details = f.getPejabatJKPTGDetails(id);
			this.context.put("details", (Hashtable) details);
			this.context.put("id_pejabat", id);
			this.context.put("listDaerah", listDaerah);
			this.context.put("totalDaerah", listDaerah.size());
			this.context.put("listPegawai", listPegawai);
			this.context.put("totalPegawai", listPegawai.size());
			return template;
		
		} else if ("goBack".equals(submit)) {
			this.context.put("selectNegeri", this.context.get("selectNegeri"));
			id_negeri = (String) this.context.get("id_negeri");
			id_seksyen = (String) this.context.get("id_seksyen");
			this.context.put("listDaerah", "");
			this.context.put("totalDaerah", "");
			this.context.put("listPegawai", "");
			this.context.put("totalPegawai", "");

		} else if ("deleteDearah".equals(submit)) {
			template = "app/utils/frmDaerahJagaan.jsp";
			deleteDaerahJagaan(session);
			List<DaerahJagaanInformation> listDaerah = f
					.getListDaerahJagaan(id);
			List<PegawaiInformation> listPegawai = f.getListingPegawai(id);
			setupPage(session, action, lists);
			Hashtable details = f.getPejabatJKPTGDetails(id);
			this.context.put("details", (Hashtable) details);
			this.context.put("listDaerah", listDaerah);
			this.context.put("totalDaerah", listDaerah.size());
			this.context.put("listPegawai", listPegawai);
			this.context.put("totalPegawai", listPegawai.size());
			return template;
		
		} else if ("deletePegawai".equals(submit)) {
			template = "app/utils/frmDaerahJagaan.jsp";
			deleteDaerahPegawai(session);
			List<DaerahJagaanInformation> listDaerah = f
					.getListDaerahJagaan(id);
			List<PegawaiInformation> listPegawai = f.getListingPegawai(id);
			setupPage(session, action, lists);
			Hashtable details = f.getPejabatJKPTGDetails(id);
			this.context.put("details", (Hashtable) details);
			this.context.put("listDaerah", listDaerah);
			this.context.put("totalDaerah", listDaerah.size());
			this.context.put("listPegawai", listPegawai);
			this.context.put("totalPegawai", listPegawai.size());
			return template;
		
		} else if ("addNewPegawai".equals(submit)) {
			String id_pejabat = getParam("id_pejabat");
			template = "app/utils/frmAddNewPegawai.jsp";
			String id_jawatan = getParam("id_jawatan");
			context.put("id_jawatan", id_jawatan);
			PejabatInformation pi = f.getListingPejabat(id_pejabat);

			context.put("pejabatInfo", pi);
			this.context.put("selectJawatan", HTML.SelectJawatan("id_jawatan", Utils.parseLong(id_jawatan),
							null, "class=\"inputFieldb3\""));

			return template;
			
		} else if ("SimpanPegawai".equals(submit)) {

			String id_pejabat = getParam("id_pejabatjkptg");
			template = "app/utils/frmDaerahJagaan.jsp";
			simpanPegawai(session);

			List<DaerahJagaanInformation> listDaerah = f.getListDaerahJagaan(id_pejabat);
			List<PegawaiInformation> listPegawai = f.getListingPegawai(id_pejabat);
			setupPage(session, action, lists);
			Hashtable details = f.getPejabatJKPTGDetails(id_pejabat);
			this.context.put("details", (Hashtable) details);
			this.context.put("id_pejabat", id);
			this.context.put("listDaerah", listDaerah);
			this.context.put("totalDaerah", listDaerah.size());
			this.context.put("listPegawai", listPegawai);
			this.context.put("totalPegawai", listPegawai.size());
			return template;
		
		} else if ("editPegawai".equals(submit)) {
			String id_pejabat = getParam("id");
			String id_unit = getParam("idUnit");

			template = "app/utils/frmEditPegawai.jsp";
			//String id_jawatan = getParam("id_jawatan");
			//context.put("id_jawatan", id_jawatan);
			PejabatInformation pi = f.getListingPejabat(id_pejabat);
			PegawaiInfoDetail pid = f.getPegawaiInfo(id_unit);
			UsersListConnectionToDB ULCD = new UsersListConnectionToDB();
			String id_jawatan = ULCD.getIdJawatanByDesc(pid.getJawatan());
			
			context.put("pejabatInfo", pi);
			context.put("pegawaiInfo", pid);
			this.context.put("selectJawatan", HTML.SelectJawatan("id_jawatan", Utils.parseLong(id_jawatan),null, "class=\"inputFieldb3\""));

			return template;
	
		} else if ("batal".equals(submit)) {
			String id_pejabat = getParam("id");
			template = "app/utils/frmDaerahJagaan.jsp";
			List<DaerahJagaanInformation> listDaerah = f
					.getListDaerahJagaan(id_pejabat);
			List<PegawaiInformation> listPegawai = f
					.getListingPegawai(id_pejabat);
			setupPage(session, action, lists);
			Hashtable details = f.getPejabatJKPTGDetails(id_pejabat);
			this.context.put("details", (Hashtable) details);
			this.context.put("id_pejabat", id_pejabat);
			this.context.put("listDaerah", listDaerah);
			this.context.put("totalDaerah", listDaerah.size());
			this.context.put("listPegawai", listPegawai);
			this.context.put("totalPegawai", listPegawai.size());
			return template;
		
		} else if ("batal2".equals(submit)) {
			String id_pejabat = getParam("id_pejabatjkptg");
			System.out.println("ID pejabat: " + id_pejabat);
			template = "app/utils/frmDaerahJagaan.jsp";
			List<DaerahJagaanInformation> listDaerah = f
					.getListDaerahJagaan(id_pejabat);
			List<PegawaiInformation> listPegawai = f
					.getListingPegawai(id_pejabat);
			setupPage(session, action, lists);
			Hashtable details = f.getPejabatJKPTGDetails(id_pejabat);
			this.context.put("details", (Hashtable) details);
			this.context.put("id_pejabat", id_pejabat);
			this.context.put("listDaerah", listDaerah);
			this.context.put("totalDaerah", listDaerah.size());
			this.context.put("listPegawai", listPegawai);
			this.context.put("totalPegawai", listPegawai.size());
			return template;
		
		} else if ("simpanKemaskini".equals(submit)) {
			String id_pejabat = getParam("id_pejabatjkptg");
			String id_unit = getParam("id_unitpsk");
			simpanDataPegawai(session);
			template = "app/utils/frmDaerahJagaan.jsp";

			List<DaerahJagaanInformation> listDaerah = f
					.getListDaerahJagaan(id_pejabat);
			List<PegawaiInformation> listPegawai = f
					.getListingPegawai(id_pejabat);
			setupPage(session, action, lists);

			Hashtable details = f.getPejabatJKPTGDetails(id_pejabat);
			this.context.put("details", (Hashtable) details);
			this.context.put("id_pejabat", id);
			this.context.put("listDaerah", listDaerah);
			this.context.put("totalDaerah", listDaerah.size());
			this.context.put("listPegawai", listPegawai);
			this.context.put("totalPegawai", listPegawai.size());
			return template;
		
		} else if ("addDaerahJagaan".equals(submit)) {
			String id_pejabat = getParam("id_pejabat");
			template = "app/utils/frmAddDaerahJagaan.jsp";
			addNewDaerahJagaan(session);
			return template;
		
		} else if ("simpanDaerah".equals(submit)) {
			String id_pejabat = getParam("pid_pejabatjkptg");
			String selectdaerah = getParam("selectDaerah");
			simpanDaerahJagaan(session);
			
			template = "app/utils/frmDaerahJagaan.jsp";

			List<DaerahJagaanInformation> listDaerah = f
					.getListDaerahJagaan(id_pejabat);
			List<PegawaiInformation> listPegawai = f
					.getListingPegawai(id_pejabat);
			setupPage(session, action, lists);

			Hashtable details = f.getPejabatJKPTGDetails(id_pejabat);
			
			this.context.put("details", (Hashtable) details);
			this.context.put("id_pejabat", id_pejabat);
			this.context.put("listDaerah", listDaerah);
			this.context.put("totalDaerah", listDaerah.size());
			this.context.put("listPegawai", listPegawai);
			this.context.put("totalPegawai", listPegawai.size());
			return template;
		} else {
			id_negeri = getParam("id_negeri");
			id_seksyen = getParam("id_seksyen");
			id_daerah = getParam("id_daerah");
			
			
			this.context.put("selectSeksyen", HTML.SelectSeksyen("id_seksyen",
					null, null, "onChange=\"doChanges()\" "));
			this.context.put("selectNegeri", HTML.SelectNegeri("id_negeri",
					"onChange=\"doChanges()\" "));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri("0",
					"id_daerah", null, null, "onChange=\"doChanges()\" "));
			this.context.put("listDaerah", "");
			this.context.put("totalDaerah", "");
			this.context.put("listPegawai", "");
			this.context.put("totalPegawai", "");
			
			myLogger.debug("template:"+template);
			
			return template;
		}
		lists = f.getListing(id_seksyen, id_negeri);
		setupPage(session, action, lists);
		myLogger.debug(template);
		return template;
	}
	
	private void simpanDaerahJagaan(HttpSession session) throws Exception{
		String id_masuk = (String) session.getAttribute("_ekptg_user_id");
		
		String pid_pejabat = getParam("pid_pejabatjkptg");
		String pid_jenispejabat = getParam("pid_jenisPejabat");
		String pid_negeri = getParam("pid_negeri");
		String pid_daerah = getParam("pid_daerah");
		String pid_seksyen = getParam("pid_seksyen");
		
		//System.out.println(pid_pejabat + " - " + pid_jenispejabat + " - " + pid_negeri + " - " + pid_daerah + " - " + pid_seksyen);
		
		String selectdaerah = getParam("selectDaerah");
		//System.out.println(selectdaerah);
		String seperator = "/";
		String uid_pejabat = selectdaerah.substring(0, selectdaerah.indexOf(seperator)).trim();
		String uid_negeri = selectdaerah.substring(selectdaerah.indexOf(seperator)+1 ).trim();
		//System.out.println(uid_pejabat + " - " + uid_negeri);
		FrmPejabatJKPTGData f = new FrmPejabatJKPTGData();
		PejabatDaerahInformation pdi = f.getPejabatUrusInfoDetail(uid_negeri, uid_pejabat);
		String uid_jenispejabat = pdi.getId_jenispejabat();
		String uid_daerah = pdi.getId_daerah();
		//System.out.println(uid_jenispejabat + " - " + uid_daerah);
		
		f.addDaerah(pid_jenispejabat, pid_pejabat, pid_negeri, pid_daerah, uid_jenispejabat, uid_pejabat, uid_negeri, uid_daerah, pid_seksyen, id_masuk);
		
	}

	private void simpanPegawai(HttpSession session) throws Exception {
		String kod_pegawai = getParam("kod_pegawai");
		String id_pejabat = getParam("id_pejabat");
		String nama_pegawai = getParam("nama_pegawai");
		String id_jawatan = getParam("id_jawatan");
		String keterangan_unit = getParam("keterangan_unit");
		String nama_pejabat = getParam("nama_pejabat");
		String alamat1 = getParam("alamat1");
		String alamat2 = getParam("alamat2");
		String alamat3 = getParam("alamat3");
		String poskod = getParam("poskod");
		String nama_negeri = getParam("nama_negeri");
		String no_tel = getParam("no_tel");
		String no_tel_ext = getParam("no_tel_ext");
		String id_bandar = getParam("id_bandar");
		String id_negeri = getParam("id_negeri");
		String id_pejabatjkptg = getParam("id_pejabatjkptg");
		String id_masuk = (String) session.getAttribute("_ekptg_user_id");

		UsersListConnectionToDB ULCD = new UsersListConnectionToDB();
		JawatanInformation ji = ULCD.listJawatanById(id_jawatan);

		String jawatan = ji.getKeterangan();
		FrmPejabatJKPTGData f = new FrmPejabatJKPTGData();
		f.addDaerahPegawai(kod_pegawai,id_pejabatjkptg, id_negeri, nama_pejabat,
				keterangan_unit, nama_pegawai, jawatan, alamat1, alamat2,
				alamat3, poskod, no_tel, no_tel_ext, id_masuk, id_bandar);

	}

	private void addNewDaerahJagaan(HttpSession session) throws Exception {
		String id_pejabat = getParam("id_pejabat");
		FrmPejabatJKPTGData f = new FrmPejabatJKPTGData();
		PejabatInformation pi = f.getPejabatInfo(id_pejabat);
		List<PejabatDaerahInformation> listDaerah = f.getPejabatUrusInfo(pi.getId_negeri(),id_pejabat);

		this.context.put("daerahInfo", pi);
		this.context.put("listDaerah", listDaerah);
	}

	private void simpanDataPegawai(HttpSession session) throws Exception {
		String id_pejabat = getParam("id_pejabatjkptg");
		String id_unit = getParam("id_unitpsk");
		String nama_pegawai = getParam("nama_pegawai");
		String id_jawatan = getParam("id_jawatan");
		String keterangan_unit = getParam("keterangan_unit");
		String nama_pejabat = getParam("nama_pejabat");
		String alamat1 = getParam("alamat1");
		String alamat2 = getParam("alamat2");
		String alamat3 = getParam("alamat3");
		String poskod = getParam("poskod");
		String nama_negeri = getParam("nama_negeri");
		String no_tel = getParam("no_tel");
		String no_tel_ext = getParam("no_tel_ext");
		String id_bandar = getParam("id_bandar");
		String id_negeri = getParam("id_negeri");
		String id_pejabatjkptg = getParam("id_pejabatjkptg");
		String id_kemaskini = (String) session.getAttribute("_ekptg_user_id");

		String jawatan = "";
		UsersListConnectionToDB ULCD = new UsersListConnectionToDB();
		if (!id_jawatan.equals("")) {
			JawatanInformation ji = ULCD.listJawatanById(id_jawatan);
			jawatan = ji.getKeterangan();
		} else {
			jawatan = "";
		}
		//System.out.println(jawatan);
		FrmPejabatJKPTGData f = new FrmPejabatJKPTGData();
		f.updatePegawaiInfo(id_unit, id_negeri, id_pejabat, nama_pegawai,
				nama_pejabat, keterangan_unit, jawatan, alamat1, alamat2,
				alamat3, poskod, no_tel, no_tel_ext, id_kemaskini);

	}

	private void deleteDaerahJagaan(HttpSession session) throws Exception {
		String id_pejabatUrusan = getParam("id_pejabatUrusan");
		FrmPejabatJKPTGData f = new FrmPejabatJKPTGData();
		f.deleteDaerahJagaan(id_pejabatUrusan);
	}

	private void deleteDaerahPegawai(HttpSession session) throws Exception {
		String idUnit = getParam("idUnit");
		FrmPejabatJKPTGData f = new FrmPejabatJKPTGData();
		f.deleteDaerahPegawai(idUnit);
	}

	public void setupPage(HttpSession session, String action, Vector lists) {
		this.context.put("totalRecords", lists.size());
		int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null
				|| this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10
					: getParamAsInteger("itemsPerPage");
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
		Paging paging = new Paging(session, lists, itemsPerPage);

		if (page > paging.getTotalPages())
			page = 1; // reset page number
		this.context.put("lists", paging.getPage(page));
		this.context.put("page", new Integer(page));
		this.context.put("itemsPerPage", new Integer(itemsPerPage));
		this.context.put("totalPages", new Integer(paging.getTotalPages()));
		this.context.put("startNumber", new Integer(paging.getTopNumber()));
		this.context.put("isFirstPage", new Boolean(paging.isFirstPage()));
		this.context.put("isLastPage", new Boolean(paging.isLastPage()));
	}

	public void setParameterValues(Hashtable parameters) {
		String name = "";
		String value = "";
		Enumeration allparam = request.getParameterNames();
		for (; allparam.hasMoreElements();) {
			name = (String) allparam.nextElement();
			if (name.indexOf("Form_") != -1) { // get only parameters with name
				// like Form_
				value = request.getParameter(name);
				parameters.put(name, value);
			}
		}
	}

}