package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.php2.FrmPLPCetakanMinitCeraianData;
import ekptg.model.php2.FrmPLPHeaderData;

public class FrmPLPCetakanMinitCeraianView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmPLPHeaderData logicHeader = new FrmPLPHeaderData();
	FrmPLPCetakanMinitCeraianData logic = new FrmPLPCetakanMinitCeraianData();

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}

		// GET DEFAULT PARAM
		String submit = getParam("command");
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
		Vector beanKertasCeraian = null;

		vm = "app/php2/frmPLPCetakanMinitCeraian.jsp";

		// SEND TO MODEL
		if (postDB) {
			if ("doSeterusnya".equals(hitButton)) {
				logic.updateStatus(idFail, idPermohonan, session);
			}
			if ("doBatalPermohonan".equals(hitButton)) {
				logic.doBatalPermohonan(idFail, idPermohonan,
						getParam("tarikhBatal"), getParam("txtSebab"), session);
				step = "";
			}
			if ("simpanKemaskiniCeraian".equals(hitButton)) {
				logic.simpanKemaskiniCeraian(idKertasKerja,
						getParam("txtKepada"), getParam("txtMelalui"),
						getParam("txtDaripada"), getParam("txtYangBerhormat"),
						getParam("txtTajukKertas"), getParam("txtTujuan"),
						getParam("txtPerihalKemajuan"),
						getParam("txtLaporanNilaian"),
						getParam("txtUlasanKJP"),
						getParam("txtUlasanKewangan"),
						getParam("txtPerakuanPTP"), getParam("txtPerakuanKSU"),
						getParam("txtKeputusanMenteri"),
						getParam("txtTarikhHantar"), session);
			}
		}

		// HEADER
		beanHeader = new Vector();
		logicHeader.setMaklumatPermohonan(idFail, session);
		beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		if (logicHeader.getBeanMaklumatPermohonan().size() != 0) {
			Hashtable hashPermohonan = (Hashtable) logicHeader
					.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashPermohonan.get("idFail");
			idPermohonan = (String) hashPermohonan.get("idPermohonan");
			idStatus = (String) hashPermohonan.get("idStatus");
		}

		// MODE VIEW
		if ("view".equals(mode)) {

			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");

			// KERTAS CERAIAN
			beanKertasCeraian = new Vector();
			logic.setMaklumatKertasCeraian(idPermohonan);
			beanKertasCeraian = logic.getBeanKertasCeraian();
			this.context.put("BeanKertasCeraian", beanKertasCeraian);

		}
		// MODE VIEW
		if ("update".equals(mode)) {

			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");

			// KERTAS CERAIAN
			beanKertasCeraian = new Vector();
			logic.setMaklumatKertasCeraian(idPermohonan);
			beanKertasCeraian = logic.getBeanKertasCeraian();
			this.context.put("BeanKertasCeraian", beanKertasCeraian);
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
		
		this.context.put("step",step);

		return vm;
	}
}
