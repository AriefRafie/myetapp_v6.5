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
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.model.php2.FrmAPBHeaderData;
import ekptg.model.php2.FrmAPBMesyuaratData;

public class FrmAPBMesyuaratView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmAPBHeaderData logicHeader = new FrmAPBHeaderData();
	FrmAPBMesyuaratData logic = new FrmAPBMesyuaratData();

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = session.getAttribute("doPost").toString();
		if (doPost.equals("true")) {
			postDB = true;
		}

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String submit = getParam("command");
		String hitButton = getParam("hitButton");
		String selectedTabUpper = getParam("selectedTabUpper").toString();
		if (selectedTabUpper == null || "".equals(selectedTabUpper)) {
			selectedTabUpper = "0";
		}
		String actionAPB = getParam("actionAPB");
		String mode = getParam("mode");

		// GET ID PARAM
		String idFail = getParam("idFail");
		String idPermohonan = getParam("idPermohonan");
		String idStatus = getParam("idStatus");
		String idMesyuarat = getParam("idMesyuarat");
		String idKehadiran = getParam("idKehadiran");
		String idDokumen = getParam("idDokumen");

		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");

		// VECTOR
		Vector beanHeader = null;
		Vector senaraiMesyuarat = null;
		Vector beanMaklumatMesyuarat = null;
		Vector beanMaklumatKehadiran = null;
		Vector senaraiKehadiran = null;
		Vector listMesyuarat = null;
		Vector beanMaklumatPengerusi = null;
		Vector senaraiImejan = null;
		Vector beanMaklumatImejan = null;

		// GET DROPDOWN PARAM
		String idJamDari = getParam("socJamDari");
		if (idJamDari == null || idJamDari.trim().length() == 0) {
			idJamDari = "99999";
		}
		String idMinitDari = getParam("socMinitDari");
		if (idMinitDari == null || idMinitDari.trim().length() == 0) {
			idMinitDari = "99999";
		}
		String idJamHingga = getParam("socJamHingga");
		if (idJamHingga == null || idJamHingga.trim().length() == 0) {
			idJamHingga = "99999";
		}
		String idMinitHingga = getParam("socMinitHingga");
		if (idMinitHingga == null || idMinitHingga.trim().length() == 0) {
			idMinitHingga = "99999";
		}
		String idLokasi = getParam("socLokasi");
		if (idLokasi == null || idLokasi.trim().length() == 0) {
			idLokasi = "99999";
		}
		String idJawatan = getParam("socidJawatan");
		if (idJawatan == null || idJawatan.trim().length() == 0) {
			idJawatan = "99999";
		}

		String step = getParam("step");

		this.context.put("completed", false);

		// HITBUTTON FOR POPUP MESYUARAT
		if (postDB) {
			if ("simpanMesyuarat".equals(hitButton)) {
				idMesyuarat = logic.saveMesyuarat(idFail, idPermohonan,
						getParam("txtTarikhMesyuarat"),
						getParam("txtBilMesyuarat"),
						getParam("txtTujuanMesyuarat"), idJamDari, idMinitDari,
						idJamHingga, idMinitHingga,
						getParam("txtCatatanMesyuarat"), idLokasi, session);
			}
			if ("simpanKemaskiniMesyuarat".equals(hitButton)) {
				logic.updateMesyuarat(idMesyuarat,
						getParam("txtTarikhMesyuarat"),
						getParam("txtBilMesyuarat"),
						getParam("txtTujuanMesyuarat"), idJamDari, idMinitDari,
						idJamHingga, idMinitHingga,
						getParam("txtCatatanMesyuarat"), idLokasi, session);
			}
			if ("simpanKemaskiniKeputusan".equals(hitButton)) {
				logic.updateKeputusanMesyuarat(idMesyuarat,
						getParam("txtUlasanMesyuarat"),
						getParam("txtTindakan"), session);
			}
			if ("hapusMesyuarat".equals(hitButton)) {
				logic.hapusMaklumatMesyuarat(idMesyuarat, session);
			}
			if ("simpanKehadiran".equals(hitButton)) {

				String listNama[] = this.request.getParameterValues("txtNama");
				String listAgensi[] = this.request
						.getParameterValues("txtAgensi");
				String listJawatan[] = this.request
						.getParameterValues("txtJawatan");
				String listNoTel[] = this.request
						.getParameterValues("txtNoTel");

				if (listNama != null) {
					for (int i = 0; i < listNama.length; i++) {
						if (!listNama[i].equals("")) {
							logic.saveKehadiran(i, idMesyuarat, listNama[i],
									listAgensi[i], listJawatan[i],
									listNoTel[i], getParam("flagPengerusi"),
									session);
						}
					}
				}
			}
			if ("simpanKemaskiniKehadiran".equals(hitButton)) {
				logic.updateKehadiran(idMesyuarat, idKehadiran,
						getParam("txtNama"), getParam("txtAgensi"),
						getParam("txtNoTel"), getParam("txtJawatan"),
						getParam("socPengerusi"), session);
			}
			if ("hapusKehadiran".equals(hitButton)) {
				logic.hapusKehadiranMesyuarat(idKehadiran, session);
			}
			if ("simpanDokumen".equals(hitButton)) {
				uploadFiles(idMesyuarat, idPermohonan, session);
			}
			if ("simpanKemaskiniDokumen".equals(hitButton)) {
				logic.simpanKemaskiniDokumen(idDokumen,
						getParam("txtNamaImej"), getParam("txtCatatanImej"),
						session);
			}
			if ("hapusDokumen".equals(hitButton)) {
				logic.hapusDokumen(idDokumen, session);
			}
			if ("doSeterusnya".equals(hitButton)) {
				logic.updateStatus(idFail, idPermohonan, session);
			}

			if ("doBatalPermohonan".equals(hitButton)) {
				logicHeader.doBatalPermohonan(idFail, idPermohonan, idStatus,
						getParam("tarikhBatal"), getParam("txtSebab"), session);
				step = "";
			}
		}

		// HEADER
		beanHeader = new Vector();
		logicHeader.setMaklumatPermohonan(idFail, session);
		beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);

		this.context.put("onload", "");

		if (beanHeader.size() != 0) {
			Hashtable hashHeader = (Hashtable) logicHeader
					.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashHeader.get("idFail");
			idPermohonan = (String) hashHeader.get("idPermohonan");
			idStatus = (String) hashHeader.get("idStatus");
			String status = (String) hashHeader.get("status");
			this.context.put("status", status.toUpperCase());
		}
		
		// GET FLAG OPEN DETAIL		
		boolean flagOpenDetail = logicHeader.getFlagOpenDetail(idStatus, 5); // 5 = MESYUARAT
		this.context.put("flagOpenDetail", flagOpenDetail);

		if ("daftarBaru".equals(actionAPB)) {

			vm = "app/php2/frmAPBMesyuaratDaftar.jsp";

			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			this.context.put("disabled", "");

			this.context.put("selectJamDari", HTML.SelectJam("socJamDari",
					Long.parseLong(idJamDari), "", ""));
			this.context.put(
					"selectMinitDari",
					HTML.SelectMinit("socMinitDari",
							Long.parseLong(idMinitDari), "", ""));
			this.context.put("selectJamHingga", HTML.SelectJam("socJamHingga",
					Long.parseLong(idJamDari), "", ""));
			this.context.put(
					"selectMinitHingga",
					HTML.SelectMinit("socMinitHingga",
							Long.parseLong(idMinitDari), "", ""));
			this.context.put(
					"selectLokasi",
					HTML.SelectLokasiMesyuarat("socLokasi",
							Long.parseLong(idLokasi), "", ""));

		} else if ("papar".equals(actionAPB)) {

			vm = "app/php2/frmAPBMesyuaratDetail.jsp";

			if ("1".equals(selectedTabUpper)) {

				if ("update".equals(modePopup)) {

					this.context.put("readonlyPopup", "");
					this.context.put("inputTextClassPopup", "");

					beanMaklumatKehadiran = new Vector();
					logic.setMaklumatKehadiran(idKehadiran);
					beanMaklumatKehadiran = logic.getBeanMaklumatKehadiran();
					this.context.put("BeanMaklumatKehadiran",
							beanMaklumatKehadiran);

				} else {

					this.context.put("readonlyPopup", "readonly");
					this.context.put("inputTextClassPopup", "disabled");

					beanMaklumatKehadiran = new Vector();
					logic.setMaklumatKehadiran(idKehadiran);
					beanMaklumatKehadiran = logic.getBeanMaklumatKehadiran();
					this.context.put("BeanMaklumatKehadiran",
							beanMaklumatKehadiran);
				}

				// SENARAI KEHADIRAN MESYUARAT
				logic.setSenaraiKehadiran(idMesyuarat);
				senaraiKehadiran = logic.getListKehadiran();
				this.context.put("SenaraiKehadiran", senaraiKehadiran);

			} else if ("3".equals(selectedTabUpper)) {

				// OPEN POPUP DOKUMEN
				if ("openPopupDokumen".equals(flagPopup)) {

					if ("new".equals(modePopup)) {

						this.context.put("readonlyPopup", "");
						this.context.put("inputTextClassPopup", "");

						beanMaklumatImejan = new Vector();
						Hashtable hashMaklumatImejan = new Hashtable();
						hashMaklumatImejan.put("namaImej", "");
						hashMaklumatImejan.put("catatanImej", "");
						beanMaklumatImejan.addElement(hashMaklumatImejan);
						this.context.put("BeanMaklumatImejan",
								beanMaklumatImejan);

					} else if ("update".equals(modePopup)) {

						this.context.put("readonlyPopup", "");
						this.context.put("inputTextClassPopup", "");

						// MAKLUMAT DOKUMEN
						beanMaklumatImejan = new Vector();
						logic.setMaklumatImej(idDokumen);
						beanMaklumatImejan = logic.getBeanMaklumatImejan();
						this.context.put("BeanMaklumatImejan",
								beanMaklumatImejan);

					} else if ("view".equals(modePopup)) {

						this.context.put("readonlyPopup", "readonly");
						this.context.put("inputTextClassPopup", "disabled");

						// MAKLUMAT DOKUMEN
						beanMaklumatImejan = new Vector();
						logic.setMaklumatImej(idDokumen);
						beanMaklumatImejan = logic.getBeanMaklumatImejan();
						this.context.put("BeanMaklumatImejan",
								beanMaklumatImejan);
					}
				}

				// SENARAI IMEJAN
				senaraiImejan = new Vector();
				logic.setSenaraiImejan(idMesyuarat);
				senaraiImejan = logic.getListImejan();
				this.context.put("SenaraiImejan", senaraiImejan);

			} else {

				// MODE VIEW
				if ("view".equals(mode)) {

					this.context.put("readonly", "readonly");
					this.context.put("inputTextClass", "disabled");
					this.context.put("disabled", "disabled");

					beanMaklumatMesyuarat = new Vector();
					logic.setMaklumatMesyuarat(idMesyuarat, idPermohonan);
					beanMaklumatMesyuarat = logic.getBeanMaklumatMesyuarat();
					this.context.put("BeanMaklumatMesyuarat",
							beanMaklumatMesyuarat);
					if (beanMaklumatMesyuarat.size() != 0) {
						Hashtable hashMesyuarat = (Hashtable) logic
								.getBeanMaklumatMesyuarat().get(0);
						idLokasi = (String) (hashMesyuarat.get("idLokasi"));
						idJamDari = (String) (hashMesyuarat.get("idJamDari"));
						idMinitDari = (String) (hashMesyuarat
								.get("idMinitDari"));
						idJamHingga = (String) (hashMesyuarat
								.get("idJamHingga"));
						idMinitHingga = (String) (hashMesyuarat
								.get("idMinitHingga"));
					}

					this.context.put("selectLokasi", HTML
							.SelectLokasiMesyuarat("socLokasi",
									Long.parseLong(idLokasi), "disabled",
									" class=\"disabled\""));
					this.context.put("selectJamDari", HTML.SelectJam(
							"socJamDari", Long.parseLong(idJamDari),
							"disabled", " class=\"disabled\""));
					this.context.put("selectMinitDari", HTML.SelectMinit(
							"socMinitDari", Long.parseLong(idMinitDari),
							"disabled", " class=\"disabled\""));
					this.context.put("selectJamHingga", HTML.SelectJam(
							"socJamHingga", Long.parseLong(idJamHingga),
							"disabled", " class=\"disabled\""));
					this.context.put("selectMinitHingga", HTML.SelectMinit(
							"socMinitHingga", Long.parseLong(idMinitHingga),
							"disabled", " class=\"disabled\""));

				} else {

					this.context.put("readonly", "");
					this.context.put("inputTextClass", "");
					this.context.put("disabled", "");

					beanMaklumatMesyuarat = new Vector();
					logic.setMaklumatMesyuarat(idMesyuarat, idPermohonan);
					beanMaklumatMesyuarat = logic.getBeanMaklumatMesyuarat();
					this.context.put("BeanMaklumatMesyuarat",
							beanMaklumatMesyuarat);

					this.context.put(
							"selectJamDari",
							HTML.SelectJam("socJamDari",
									Long.parseLong(idJamDari), "", ""));
					this.context.put(
							"selectMinitDari",
							HTML.SelectMinit("socMinitDari",
									Long.parseLong(idMinitDari), "", ""));
					this.context.put(
							"selectJamHingga",
							HTML.SelectJam("socJamHingga",
									Long.parseLong(idJamDari), "", ""));
					this.context.put(
							"selectMinitHingga",
							HTML.SelectMinit("socMinitHingga",
									Long.parseLong(idMinitDari), "", ""));
					this.context.put(
							"selectLokasi",
							HTML.SelectLokasiMesyuarat("socLokasi",
									Long.parseLong(idLokasi), "", ""));

				}
			}

		} else {

			vm = "app/php2/frmAPBMesyuarat.jsp";

			// SENARAI MESYUARAT
			listMesyuarat = new Vector();
			logic.setSenaraiMesyuarat(idPermohonan);
			senaraiMesyuarat = logic.getListMesyuarat();
			this.context.put("SenaraiMesyuarat", senaraiMesyuarat);
		}

		if ("selesaiPermohonan".equals(step)) {
			vm = "app/php2/frmSelesaiPermohonan.jsp";
		}
		if ("batalPermohonan".equals(step)) {
			vm = "app/php2/frmBatalPermohonan.jsp";
		}

		// SET DEFAULT PARAM
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		this.context.put("selectedTabUpper", selectedTabUpper);
		this.context.put("actionAPB", actionAPB);
		this.context.put("mode", mode);

		// SET ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idStatus", idStatus);
		this.context.put("idMesyuarat", idMesyuarat);
		this.context.put("idKehadiran", idKehadiran);
		this.context.put("idDokumen", idDokumen);
		
		return vm;
	}

	// UPLOAD FILE
	private void uploadFiles(String idMesyuarat, String idPermohonan,
			HttpSession session) throws Exception {
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
					saveData(item, idMesyuarat, idPermohonan, session);
				}
			}
		}
	}

	private void saveData(FileItem item, String idMesyuarat,
			String idPermohonan, HttpSession session) throws Exception {

		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		try {
			db = new Db();

			// TBLPHPDOKUMEN
			long idDokumen = DB.getNextID("TBLPHPDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO TBLPHPDOKUMEN "
							+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,ID_MESYUARAT,FLAG_DOKUMEN,ID_PERMOHONAN) "
							+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?,?,?)");
			ps.setLong(1, idDokumen);
			ps.setString(2, getParam("namaImej"));
			ps.setString(3, getParam("catatanImej"));
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, idMesyuarat);
			ps.setString(9, "MM");// MINIT MESYUARAT
			ps.setString(10, idPermohonan);
			ps.executeUpdate();

			con.commit();
			this.context.put("idDokumen", idDokumen);

		} finally {
			if (db != null)
				db.close();
		}

		this.context.put("completed", true);
	}
}
