package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.php2.FrmPLPCetakanMinitKewanganData;
import ekptg.model.php2.FrmPLPHeaderData;

public class FrmPLPCetakanMinitKewanganView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmPLPHeaderData logicHeader = new FrmPLPHeaderData();
	FrmPLPCetakanMinitKewanganData logic = new FrmPLPCetakanMinitKewanganData();

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}

		// GET DEFAULT PARAM
		String vm = "";
		String mode = getParam("mode");
		if (mode.isEmpty()) {
			mode = "view";
		}
		String hitButton = getParam("hitButton");

		String step = getParam("step");

		// GET ID PARAM
		String idFail = getParam("idFail");
		String idPermohonan = getParam("idPermohonan");
		String idStatus = getParam("idStatus");
		String idKertasKerja = getParam("idKertasKerja");

		// VECTOR
		Vector beanHeader = null;
		Vector beanKertasKewangan = null;

		vm = "app/php2/frmPLPCetakanMinitKewangan.jsp";

		// SEND TO MODEL
		if (postDB) {
			if ("doSeterusnya".equals(hitButton)) {
				logic.updateStatus(idFail, idPermohonan, session);
				logic.sendEmailtoKJP(idPermohonan, "13", session);
			}
			if ("doBatalPermohonan".equals(hitButton)) {
				logic.doBatalPermohonan(idFail, idPermohonan,
						getParam("tarikhBatal"), getParam("txtSebab"), session);
				step = "";
			}
			if ("simpanKemaskiniKewangan".equals(hitButton)) {
				logic.simpanKemaskiniKewangan(idKertasKerja,
						getParam("txtTajukKertas"), getParam("txtTujuan"),
						getParam("txtPerihalKemajuan"), getParam("txtPemohon"),
						getParam("txtLaporanNilaian"),
						getParam("txtUlasanKJP"), getParam("txtPerakuanPTP"),
						getParam("txtTarikhHantar"), session);
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
			idStatus = (String) hashHeader.get("idStatus");
		}

		// MODE VIEW
		if ("view".equals(mode)) {

			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");

			// KERTAS KEWANGAN
			beanKertasKewangan = new Vector();
			logic.setMaklumatKertasKewangan(idPermohonan);
			beanKertasKewangan = logic.getBeanKertasKewangan();
			this.context.put("BeanKertasKewangan", beanKertasKewangan);

		}
		// MODE VIEW
		if ("update".equals(mode)) {

			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");

			// KERTAS KEWANGAN
			beanKertasKewangan = new Vector();
			logic.setMaklumatKertasKewangan(idPermohonan);
			beanKertasKewangan = logic.getBeanKertasKewangan();
			this.context.put("BeanKertasKewangan", beanKertasKewangan);
		}

		if ("batalPermohonan".equals(step)) {
			vm = "app/php2/frmBatalPermohonan.jsp";
		}

		// SET DEFAULT PARAM
		this.context.put("mode", mode);

		// SET ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idStatus", idStatus);
		this.context.put("idKertasKerja", idKertasKerja);
		this.context.put("step", step);
		
		if (!"".equals(getParam("flagFrom"))){
        	session.setAttribute("FLAG_FROM", getParam("flagFrom"));
        }

		return vm;
	}
}
