package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.php2.FrmAPBHeaderData;
import ekptg.model.php2.FrmAPBKertasRingkasanPermohonanData;

public class FrmAPBKertasRingkasanPermohonan extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmAPBHeaderData logicHeader = new FrmAPBHeaderData();
	FrmAPBKertasRingkasanPermohonanData logic = new FrmAPBKertasRingkasanPermohonanData();

	// @Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}

		// GET DEFAULT PARAM
		String vm = "";
		String hitButton = getParam("hitButton");
		String mode = getParam("mode");
		if (mode.isEmpty()) {
			mode = "view";
		}
		String paparan=getParam("paparan");
		
		// GET ID PARAM
		String idFail = getParam("idFail");
		String idPermohonan = getParam("idPermohonan");
		String idPemohon = getParam("idPemohon");
		String idStatus = getParam("idStatus");
		String idKertasKerjaApb = getParam("idKertasKerjaApb");
		String socSyor = getParam("socSyor");

		// VECTOR
		Vector beanHeader = null;
		Vector senaraiPengarah = null;
		Vector senaraiKoordinat = null;

		String step = getParam("step");

		vm = "app/php2/frmAPBKertasRingkasanPermohonan.jsp";

		if (postDB) {
			if ("doSeterusnya".equals(hitButton)) {
				logic.updateStatus(idFail, idPermohonan, session);
			}
			if ("doSimpanKemaskiniMaklumatKertasRingkasPermohonan"
					.equals(hitButton)) {
				logic.updateMaklumatKertasRingkasPermohonan(idPermohonan,	
						getParam("txtTarikhKertas"), getParam("txtNamaPTP"), getParam("txtNamaKSU"), getParam("txtNamaMenteri"), 
						getParam("JUPEM"), 
						getParam("JPS"),
						getParam("JMG"),
						getParam("PHN"),
						getParam("DOF"),
						getParam("JLM"),
						getParam("JAS"),
						getParam("PTG"),
						getParam("txtUlasanJabatan"), getParam("txtUlasanLulusBersyarat"), getParam("socSyor"),
						getParam("txtBilMesyuarat"), getParam("txtTarikhMesyuarat"),
						session);
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

		if (beanHeader.size() != 0) {
			Hashtable hashHeader = (Hashtable) logicHeader
					.getBeanMaklumatPermohonan().get(0);
			idFail = hashHeader.get("idFail").toString();
			idPermohonan = hashHeader.get("idPermohonan").toString();
			idPemohon = hashHeader.get("idPemohon").toString();
			idStatus = hashHeader.get("idStatus").toString();
			String status = (String) hashHeader.get("status");
			this.context.put("status", status.toUpperCase());
		}
		
		// GET FLAG OPEN DETAIL		
		boolean flagOpenDetail = logicHeader.getFlagOpenDetail(idStatus, 6); // 6 = CETAKAN KERTAS RINGKASAN
		this.context.put("flagOpenDetail", flagOpenDetail);

		if ("view".equals(mode)) { // MODE VIEW

			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");

			// MAKLUMAT KERTAS RINGKAS PERMOHONAN
			Vector beanMaklumatKertasRingkasPermohonan = logic.getMaklumatKertasRingkasPermohonan(idPermohonan);
			this.context.put("BeanMaklumatKertasRingkasPermohonan", beanMaklumatKertasRingkasPermohonan);

		} else if ("update".equals(mode)) { // MODE UPDATE

			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			this.context.put("disabled", "");

			// MAKLUMAT KERTAS RINGKAS
			Vector beanMaklumatKertasRingkasPermohonan = logic.getMaklumatKertasRingkasPermohonan(idPermohonan);
			this.context.put("BeanMaklumatKertasRingkasPermohonan", beanMaklumatKertasRingkasPermohonan);
		}
		
		if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }

		// SET DEFAULT PARAM
		this.context.put("mode", mode);

		this.context.put("paparan", paparan);
		
		// SET ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idPemohon", idPemohon);
		this.context.put("idStatus", idStatus);
		this.context.put("idKertasKerjaApb", idKertasKerjaApb);
		this.context.put("socSyor", socSyor);

		return vm;
	}
}
