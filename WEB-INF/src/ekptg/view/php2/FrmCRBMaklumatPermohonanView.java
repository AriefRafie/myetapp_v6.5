package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.php2.FrmCRBHeaderData;
import ekptg.model.php2.FrmCRBMaklumatPermohonanData;

public class FrmCRBMaklumatPermohonanView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmCRBHeaderData logicHeader = new FrmCRBHeaderData();
	FrmCRBMaklumatPermohonanData logic = new FrmCRBMaklumatPermohonanData();

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP
											// PAGING SHJ
		String submit = getParam("command");
		String vm = "";
		String mode = getParam("mode");
		if (mode.isEmpty()) {
			mode = "view";
		}
		String selectedTabUpper = getParam("selectedTabUpper").toString();
		if (selectedTabUpper == null || "".equals(selectedTabUpper)) {
			selectedTabUpper = "0";
		}
		String hitButton = getParam("hitButton");
		
		boolean flagOpenDetail = false;
		String status = "";

		// GET ID PARAM
		String idFail = getParam("idFail");
		String idPermohonan = getParam("idPermohonan");
		String idPemohon = getParam("idPemohon");
		String idStatus = getParam("idStatus");

		String idHakmilik = getParam("idHakmilik");
		String idHakmilikAgensi = getParam("idHakmilikAgensi");
		String flagBorangK = getParam("flagBorangK");
		String idBorangK = getParam("flagBorangK");

		// VECTOR
		Vector beanHeader = null;
		Vector beanMaklumatTanah = null;
		Vector beanMaklumatBorangK = null;
		Vector beanMaklumatAduan = null;
		Vector senaraiPencerobohan = null;
		
		String step = getParam("step");

		vm = "app/php2/frmCRBMaklumatAduan.jsp";

		if (postDB) {
			String[] jenisPencerobohan = this.request.getParameterValues("sbcSemakan");
			if ("doSimpanKemaskiniMaklumatAduan".equals(hitButton)) {
				logic.simpanMaklumatAduan(idPermohonan,
						getParam("txtLainCeroboh"), getParam("txtLokasi"),
						getParam("txtAduan"), jenisPencerobohan,
						getParam("tarikhTerima"), getParam("tarikhSurat"),
						getParam("txtPerkara"), getParam("txtAktiviti"), idFail, session);
			}
			if ("doSeterusnya".equals(hitButton)) {
				logic.updateStatus(idFail, idPermohonan, session);
			}
			if ("doSelesaiPermohonan".equals(hitButton)){
				logicHeader.doSelesaiPermohonan(idFail, idPermohonan, idStatus, getParam("tarikhSelesai"), getParam("txtSebab"), session);
    			step = "";
    		}
			if ("doBatalPermohonan".equals(hitButton)){
				logicHeader.doBatalPermohonan(idFail, idPermohonan, idStatus, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    		}
		}

		// HEADER
		beanHeader = new Vector();
		logicHeader.setMaklumatPermohonan(idFail, session);
		beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);

		if (beanHeader.size() != 0) {
			Hashtable hashHeader = (Hashtable) logicHeader
					.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashHeader.get("idFail");
			idPermohonan = (String) hashHeader.get("idPermohonan");
			idPemohon = (String) hashHeader.get("idPemohon");
			idStatus = (String) hashHeader.get("idStatus");
			status = (String) hashHeader.get("status");
			idHakmilik = (String) hashHeader.get("idHakmilik");
			idHakmilikAgensi = (String) hashHeader.get("idHakmilikAgensi");
			flagBorangK = (String) hashHeader.get("flagBorangK");
			idBorangK = (String) hashHeader.get("idBorangK");
		}

		// GET FLAG OPEN DETAIL
		flagOpenDetail = logicHeader.getFlagOpenDetail(idStatus, 2); // 2 =
																		// MAKLUMAT
																		// PERMOHONAN

		// MAKLUMAT HAKMILIK
		String statusRizab = "";
		logicHeader.setMaklumatHakmilik(logicHeader.getIdHakmilikPermohonanByIdFail(idFail));
		if (logicHeader.getBeanMaklumatHakmilik().size() != 0) {
			Hashtable hashHakmilik = (Hashtable) logicHeader.getBeanMaklumatHakmilik().get(0);
			idHakmilik = logicHeader.getIdHakmilikByIdHakmilikPermohonan((String) hashHakmilik.get("idHakmilikPermohonan"));
			if ("".equals((String) hashHakmilik.get("noHakmilik"))) {
				statusRizab = "RIZAB";
			} else {
				statusRizab = "MILIK";
			}
			flagBorangK = (String) hashHakmilik.get("flagBorangK");

		}
		this.context.put("flagBorangK", flagBorangK);

		if ("Y".equals(flagBorangK)) {
			beanMaklumatBorangK = new Vector();
			beanMaklumatBorangK = logicHeader.getBeanMaklumatHakmilik();
			this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);
		} else {
			beanMaklumatTanah = new Vector();
			beanMaklumatTanah = logicHeader.getBeanMaklumatHakmilik();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
		}

		if ("view".equals(mode)) {

			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");

			// MAKLUMAT ADUAN
			beanMaklumatAduan = new Vector();
			logic.setMaklumatAduan(idPermohonan);
			beanMaklumatAduan = logic.getBeanMaklumatAduan();
			this.context.put("BeanMaklumatAduan", beanMaklumatAduan);

			senaraiPencerobohan = logic.getSenaraiPencerobohan(idPermohonan);
			this.context.put("SenaraiPencerobohan", senaraiPencerobohan);

		} else {

			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			this.context.put("disabled", "");

			beanMaklumatAduan = new Vector();
			logic.setMaklumatAduan(idPermohonan);
			Hashtable hashAduanDB = (Hashtable) logic.getBeanMaklumatAduan().get(0);
			Hashtable hashAduan = new Hashtable();
			hashAduan.put("tarikhTerima",getParam("tarikhTerima") == null ? hashAduanDB.get("tarikhTerima") : getParam("tarikhTerima"));
			hashAduan.put("tarikhSurat",getParam("tarikhSurat") == null ? hashAduanDB.get("tarikhSurat") : getParam("tarikhSurat"));
			hashAduan.put("perkara",getParam("txtPerkara") == null ? hashAduanDB.get("perkara") : getParam("txtPerkara"));
			hashAduan.put("lokasi",getParam("txtLokasi") == null ? hashAduanDB.get("lokasi") : getParam("txtLokasi"));
			hashAduan.put("keterangan",getParam("txtAduan") == null ? hashAduanDB.get("keterangan") : getParam("txtAduan"));
			hashAduan.put("lainCeroboh",getParam("txtLainCeroboh") == null ? hashAduanDB.get("lainCeroboh") : getParam("txtLainCeroboh"));
			hashAduan.put("aktiviti",getParam("txtAktiviti") == null ? hashAduanDB.get("aktiviti") : getParam("txtAktiviti"));
			beanMaklumatAduan.addElement(hashAduan);
			this.context.put("BeanMaklumatAduan", beanMaklumatAduan);
		}
		
		if ("selesaiPermohonan".equals(step)){
        	vm = "app/php2/frmSelesaiPermohonan.jsp";
        }
		if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }

		// SET ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idPemohon", idPemohon);
		this.context.put("idStatus", idStatus);
		this.context.put("idHakmilik", idHakmilik);
		this.context.put("idHakmilikAgensi", idHakmilikAgensi);

		// SET DEFAULT PARAM
		this.context.put("mode", mode);
		this.context.put("selectedTabUpper", selectedTabUpper);

		this.context.put("flagBorangK", flagBorangK);
		this.context.put("idBorangK", idBorangK);

		this.context.put("flagOpenDetail", flagOpenDetail);
		this.context.put("status", status.toUpperCase());

		return vm;
	}
}
