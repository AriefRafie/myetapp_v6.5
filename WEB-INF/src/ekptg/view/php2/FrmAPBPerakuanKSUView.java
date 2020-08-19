package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.php2.FrmAPBHeaderData;
import ekptg.model.php2.FrmAPBPerakuanKSUData;

public class FrmAPBPerakuanKSUView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmAPBHeaderData logicHeader = new FrmAPBHeaderData();
	FrmAPBPerakuanKSUData logic = new FrmAPBPerakuanKSUData();

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
		String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper)) {
			selectedTabUpper = "0";
		}

		// GET ID PARAM
		String idFail = getParam("idFail");
		String idPermohonan = getParam("idPermohonan");
		String idPemohon = getParam("idPemohon");
		String idStatus = getParam("idStatus");
		String idKertasKerjaApb = getParam("idKertasKerjaApb");

		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		this.context.put("completed", false);

		// GET DROPDOWN PARAM
		String idPerakuan = getParam("socPerakuan");
		String idKeputusan = getParam("socKeputusan");

		// VECTOR
		Vector beanHeader = null;
		
		String step = getParam("step");

		this.context.put("onload", "");

		vm = "app/php2/frmAPBPerakuanKSU.jsp";

		// SUBMIT TO NEXT PROCESS
		if (postDB) {
			if ("doSimpanKemaskiniPerakuanKSU".equals(hitButton)) {
				logic.updateMaklumatPerakuanKSU(idPermohonan, idPerakuan,
						getParam("txtTarikhPerakuan"), getParam("txtNamaKSU"),
						getParam("txtTarikhHantarKSU"), session);
			}
			if ("doSimpanKemaskiniKeputusan".equals(hitButton)) {
				logic.updateMaklumatKeputusan(idPermohonan, idKeputusan,
						getParam("txtTarikhKeputusan"),
						getParam("txtNamaMenteri"),
						getParam("txtUlasanKeputusan"),
						getParam("txtTarikhHantarMenteri"), session);
				logic.updateStatus(idFail, idPermohonan, session);
			}

			if ("doBatalPermohonan".equals(hitButton)) {
				logicHeader.doBatalPermohonan(idFail, idPermohonan, idStatus,
						getParam("tarikhBatal"), getParam("txtSebab"), session);
				step = "";
			}

			if ("doSeterusnya".equals(hitButton)) {
				if (logic.checkSyor(idPermohonan)) {
					this.context.put("onload", " \"alert('Sila masukkan syor PTP di Cetakan Kertas Ringkasan.')\"");
				} else if (logic.checkKeputusan(idPermohonan)) {
					this.context.put("onload", " \"alert('Sila masukkan keputusan menteri.')\"");
				} else {
					logic.updateStatus(idFail, idPermohonan, session);
				}
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
		boolean flagOpenDetail = logicHeader.getFlagOpenDetail(idStatus, 7); // 7 = KELULUSAN MENTERI
		this.context.put("flagOpenDetail", flagOpenDetail);

		// MODE VIEW
		if ("view".equals(mode)) {

			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");

			// MAKLUMAT PERAKUAN / KEPUTUSAN
			Vector beanMaklumatPerakuanKeputusan =  logic.getMaklumatPerakuanKeputusan(idPermohonan);
			this.context.put("BeanMaklumatPerakuanKeputusan", beanMaklumatPerakuanKeputusan);

		} else if ("update".equals(mode)) {

			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			this.context.put("disabled", "");

			// MAKLUMAT PERAKUAN / KEPUTUSAN
			Vector beanMaklumatPerakuanKeputusan =  logic.getMaklumatPerakuanKeputusan(idPermohonan);
			this.context.put("BeanMaklumatPerakuanKeputusan", beanMaklumatPerakuanKeputusan);
		}
		
		if ("selesaiPermohonan".equals(step)){
        	vm = "app/php2/frmSelesaiPermohonan.jsp";
        }
		if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }

		// SET DEFAULT PARAM
		this.context.put("mode", mode);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		this.context.put("selectedTabUpper", selectedTabUpper);

		// SET ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idPemohon", idPemohon);
		this.context.put("idStatus", idStatus);
		this.context.put("idKertasKerjaApb", idKertasKerjaApb);

		return vm;
	}

}
