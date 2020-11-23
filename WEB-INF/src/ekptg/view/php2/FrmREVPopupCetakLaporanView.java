package ekptg.view.php2;

import java.text.SimpleDateFormat;
import java.util.Date;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;

public class FrmREVPopupCetakLaporanView extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;

	public String doTemplate2() throws Exception {

		//GET DEFAULT PARAM
	    String vm = "app/php2/frmREVPopupCetakLaporan.jsp";
	    
	    String idPegawai = getParam("socPegawai");
		if (idPegawai == null || idPegawai.trim().length() == 0){
			idPegawai = "99999";
		}
		String idPegawaiPenyemak = getParam("socPegawaiPenyemak");
		if (idPegawaiPenyemak == null || idPegawaiPenyemak.trim().length() == 0){
			idPegawaiPenyemak = "99999";
		}
		String idPegawaiPengesah = getParam("socPegawaiPengesah");
		if (idPegawaiPengesah == null || idPegawaiPengesah.trim().length() == 0){
			idPegawaiPengesah = "99999";
		}
		String idModBayaran = getParam("socModBayaran");
		if (idModBayaran == null || idModBayaran.trim().length() == 0){
			idModBayaran = "99999";
		}
		String idKategoriPemohon = getParam("socKategoriPemohon");
		if (idKategoriPemohon == null || idKategoriPemohon.trim().length() == 0){
			idKategoriPemohon = "99999";
		}

		String pattern = "dd/MM/yyyy";
		String currentDateInString =new SimpleDateFormat(pattern).format(new Date());		
		
		String tahunDari = getParam("socTahunDari");
		String tahunHingga = getParam("socTahunHingga");
		String bulanDari = getParam("socBulanDari");
		String bulanHingga = getParam("socBulanHingga");		
	    
	    String tarikhMula = getParam("txdTarikhMula");
	    String tarikhTamat = getParam("txdTarikhTamat");
	    String rujTarikh = getParam("txtRujSurat");
	    String rujSurat = getParam("txtRujTarikh");
		String report = getParam("report");
		String noTel = getParam("txtNoTel");
		String idAkaun = getParam("idAkaun");
		String idHasil = getParam("idHasil");
		String idNotis = getParam("idNotis");
		String idJadualPertama = getParam("idJadualPertama");
		
		this.context.put("selectPegawai",HTML.selectPegawaiUnitHasil("socPegawai", Utils.parseLong(idPegawai), "", ""));
		this.context.put("selectPegawaiPenyemak",HTML.selectPegawaiUnitHasil("socPegawaiPenyemak", Utils.parseLong(idPegawaiPenyemak), "", ""));
		this.context.put("selectPegawaiPengesah",HTML.selectPegawaiUnitHasil("socPegawaiPengesah", Utils.parseLong(idPegawaiPengesah), "", ""));
		this.context.put("selectBulanDari",HTML.SelectBulan("socBulanDari",Utils.parseLong(bulanDari), null, "style=width:auto"));
		this.context.put("selectTahunDari",HTML.SelectTahun("socTahunDari",tahunDari, null, "style=width:auto"));
		this.context.put("selectBulanHingga",HTML.SelectBulan("socBulanHingga",Utils.parseLong(bulanHingga), null, "style=width:auto"));
		this.context.put("selectTahunHingga",HTML.SelectTahun("socTahunHingga",tahunHingga, null, "style=width:auto"));
		this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "", "")); //ADD BY AIN
		this.context.put("selectKategoriPemohon",HTML.SelectKategoriPemohonIndividuAndSyarikat("socKategoriPemohon",Utils.parseLong(idKategoriPemohon), null, "style=width:auto"));
		
		if(tarikhMula.equals(""))
		{
			tarikhMula = currentDateInString;
		}
		this.context.put("txdTarikhMula", tarikhMula);
		
		if(tarikhTamat.equals(""))
		{
			tarikhTamat = currentDateInString;
		}
		this.context.put("txdTarikhTamat", tarikhTamat);
		
		
		this.context.put("idHasil", idHasil);
		this.context.put("idNotis", idNotis);
		this.context.put("idJadualPertama", idJadualPertama);
		
		this.context.put("txtNoTel", noTel);
		this.context.put("report", report);
		
		return vm;
	}

}
