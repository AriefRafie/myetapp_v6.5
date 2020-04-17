/**
 * 
 */
package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmPLPPopupCetakLaporanData;

/**
 * 
 *
 */
public class FrmTKRPopupCetakLaporanView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPLPPopupCetakLaporanData logic = new FrmPLPPopupCetakLaporanData();

	public String doTemplate2() throws Exception {

		//GET DEFAULT PARAM
	    String vm = "app/php2/frmPLPPopupCetakLaporan.jsp";
	    
	    
	    
	    String idFail = getParam("idFail");
	    String idUlasanTeknikal = getParam("idUlasanTeknikal");
		String idHakmilikPermohonan = getParam("idHakmilikPermohonan");
		String idTanahGanti = getParam("idTanahGanti");
		String idMesyuarat = getParam("idMesyuarat");
		String report = getParam("report");
		String bilUlangan = getParam("bilUlangan");
		String txtKandungan = getParam("txtKandungan");
		
		String idPegawai = getParam("socPegawai");
		if (idPegawai == null || idPegawai.trim().length() == 0){
			idPegawai = "99999";
		}
		

		String idPejabat = getParam("socPejabatByIdFailAndIdJenisPejabat");
		if (idPejabat == null || idPejabat.trim().length() == 0) {
			idPejabat = "99999";
		}
		String idJenisPejabat = getParam("socJenisPejabat");
		if (idJenisPejabat == null || idJenisPejabat.trim().length() == 0) {
			idJenisPejabat = "99999";
		}
		
		String noFail = logic.getNoFailByIdFail(idFail);	
		

		if ("doFilter".equals(submit)) {
//			this.context.put("selectPegawai", HTML.selectPegawaiUnitPelepasan("socPegawai",idPegawai == "99999" ? null : Utils.parseLong(idPegawai), "", ""));
//			this.context.put("selectJenisPejabat", HTML.SelectJenisPejabat("socJenisPejabat", Utils.parseLong(idJenisPejabat), "","onChange=\"doChangeJenisPejabat();\""));
			this.context.put("selectPejabat",HTML.selectPejabatByIdFailAndIdJenisPejabat("socPejabatByIdFailAndIdJenisPejabat",Utils.parseLong(idPejabat),idFail, idJenisPejabat, "", "onChange=\"doChangeNamaPejabat();\""));
//			this.context.put("selectPejabat",HTML.selectPejabatByIdFailAndIdJenisPejabat("socPejabatByIdFailAndIdJenisPejabat",Utils.parseLong(idPejabat),idFail, idJenisPejabat, "", "onChange=\"doChangeNamaPejabat();\""));
			
		} else if("doGetAlamatPejabat".equals(submit)){
			
			Vector beanAlamat = HTML.selectAlamatByIdPejabat(idPejabat);
			if (beanAlamat.size() != 0){
				Hashtable hashAlamat = (Hashtable) beanAlamat.get(0);
				
				this.context.put("alamat1", (String) hashAlamat.get("alamat1"));
				this.context.put("alamat2", (String) hashAlamat.get("alamat2"));
				this.context.put("alamat3", (String) hashAlamat.get("alamat3"));
				this.context.put("poskod", (String) hashAlamat.get("poskod"));
				this.context.put("namaDaerah", (String) hashAlamat.get("namaDaerah"));
				this.context.put("namaNegeri", (String) hashAlamat.get("namaNegeri"));
				
				this.context.put("mode", "view");
				this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");
				this.context.put("disabled", "disabled");
			}
			
			
		} 
		
		this.context.put("selectJenisPejabat", HTML.SelectJenisPejabat("socJenisPejabat", Utils.parseLong(idJenisPejabat), "","onChange=\"doChangeJenisPejabat();\""));
		this.context.put("selectPegawai",HTML.selectPegawaiUnitPelepasan("socPegawai", Utils.parseLong(idPegawai), "", " onChange=\"doChangeSocPegawai()\""));
		
		this.context.put("idFail", idFail);
		this.context.put("idUlasanTeknikal", idUlasanTeknikal);
		this.context.put("idHakmilikPermohonan", idHakmilikPermohonan);
		this.context.put("idTanahGanti", idTanahGanti);
		this.context.put("idMesyuarat", idMesyuarat);
		this.context.put("report", report);
		this.context.put("bilUlangan", bilUlangan);
		this.context.put("noFail", noFail);		
		this.context.put("socPegawai", idPegawai);
		this.context.put("txtKandungan", txtKandungan);	
		
		return vm;
	}
}
