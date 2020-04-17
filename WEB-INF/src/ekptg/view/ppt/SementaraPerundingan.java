package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPermohonanData;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPerundinganData;
import ekptg.model.ppt.FrmHakmilikSementaraPerundinganBantahanData;
import ekptg.model.ppt.FrmHakmilikSementaraPerundinganKeputusanData;
import ekptg.model.ppt.FrmHakmilikSementaraPerundinganNilaianJPPHData;
import ekptg.model.ppt.FrmHakmilikSementaraPerundinganTuanTanahData;
import ekptg.model.ppt.FrmHakmilikSementaraPerundinganTuntutanData;
import ekptg.model.ppt.FrmHakmilikSementaraSenaraiPerundinganData;

public class SementaraPerundingan extends AjaxBasedModule{

	public String doTemplate2() throws Exception {

		String vm = "";
		String submit = getParam("command");
        String action = getParam("action");
        context.put("action",action);
        String modeTuanTanah = getParam("modeTuanTanah");
        context.put("modeTuanTanah",modeTuanTanah);
        String tarikhmohon = "";
    	String nofail = "";    
    	String noJKPTG = "";
    	String cStatus = "0";
    	String id_fail = getParam("id_fail");
		context.put("idFail", id_fail);
		String id_permohonan = getParam("id_permohonan");
		context.put("idPermohonan",id_permohonan);
		String id_siasatan = getParam("id_siasatan");
		context.put("idSiasatan",id_siasatan);
		String idHakmilikPB = getParam("idHakmilikPB");
		context.put("idHakmilikPB",idHakmilikPB);
		String idHakmilik = getParam("idHakmilik");
		context.put("idHakmilik", idHakmilik);
		String id_tanah = getParam("idTanah");
		String idPb = getParam("socPB");
    	HttpSession session = this.request.getSession();

    	FrmHakmilikSementaraSenaraiPerundinganData listCarianFail = new FrmHakmilikSementaraSenaraiPerundinganData();
		FrmHakmilikSementaraMaklumatPermohonanData prmhnnMaster = new FrmHakmilikSementaraMaklumatPermohonanData();
		FrmHakmilikSementaraMaklumatPerundinganData maklumatPerundingan = new FrmHakmilikSementaraMaklumatPerundinganData();
		FrmHakmilikSementaraPerundinganTuanTanahData tuanTanah = new FrmHakmilikSementaraPerundinganTuanTanahData();
		FrmHakmilikSementaraPerundinganNilaianJPPHData nilaian = new FrmHakmilikSementaraPerundinganNilaianJPPHData();
		FrmHakmilikSementaraPerundinganTuntutanData tuntutan = new FrmHakmilikSementaraPerundinganTuntutanData();
		FrmHakmilikSementaraPerundinganBantahanData bantahan = new FrmHakmilikSementaraPerundinganBantahanData();
		FrmHakmilikSementaraPerundinganKeputusanData kptsn = new FrmHakmilikSementaraPerundinganKeputusanData();
		
		Vector list = null;
    	Vector listAgensi = null;
    	Vector paparSet = null;
    	Vector listPerundingan = null;
    	Vector paparTuanTanah = null;
    	Vector paparNilaian = null;
    	Vector paparTuntutan = null;
    	Vector paparBantahan = null;
    	Vector paparHakmilik = null;
    	Vector paparPB = null;
    	Vector listPB = null;
    	
    	if("newPerundingan".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraPerundinganTuanTanah.jsp";

    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
			
			maklumatPerundingan.setRundingan(id_permohonan);
			listPerundingan = maklumatPerundingan.getListRundingan();
			context.put("SenaraiRundingan",listPerundingan);
    		
    		
    	}
    	
    	else if("tabTuanTanah".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraPerundinganTuanTanah.jsp";
    		
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
			
			
			maklumatPerundingan.setDataRundingan(id_siasatan);
    		paparSet = maklumatPerundingan.getDataRundingan();
    		Hashtable hP = (Hashtable)paparSet.get(0);
    		
    		context.put("BIL_RUNDING",hP.get("NO_KES"));
    		context.put("NO_RUNDING",hP.get("NO_SIASATAN"));
    		context.put("TARIKH_RUNDING",hP.get("TARIKH_SIASATAN"));
    		context.put("MASA_RUNDINGAN",hP.get("MASA_SIASATAN"));
    		
    		if(hP.get("JENIS_WAKTU_SIASATAN").equals("1")){
    			  context.put("WAKTU_RUNDING", "PAGI");

	    	}
    		else if(hP.get("JENIS_WAKTU_SIASATAN").equals("2")){
    			  context.put("WAKTU_RUNDING", "TENGAHARI");

	    	}
    		else if(hP.get("JENIS_WAKTU_SIASATAN").equals("3")){
    			  context.put("WAKTU_RUNDING","PETANG");

	    	}
    		else if(hP.get("JENIS_WAKTU_SIASATAN").equals("4")){
    			  context.put("WAKTU_RUNDING","MALAM");

	    	}
	    	  
    		
    		if (hP.get("STATUS_SIASATAN").equals("1")){
    			context.put("STATUS_RUNDINGAN","DALAM PROSES");
    		}
    		else if (hP.get("STATUS_SIASATAN").equals("2")){
    			context.put("STATUS_RUNDINGAN","DITUNDA SEBELUM SIASATAN");
    		}
    		else if (hP.get("STATUS_SIASATAN").equals("3")){
    			context.put("STATUS_RUNDINGAN","DIBATALKAN");
    		}
    		else if (hP.get("STATUS_SIASATAN").equals("4")){
    			context.put("STATUS_RUNDINGAN","ULANG SIASATAN");
    		}
    		else if (hP.get("STATUS_SIASATAN").equals("5")){
    			context.put("STATUS_RUNDINGAN","SAMBUNG SIASATAN");
    		}
    		else if (hP.get("STATUS_SIASATAN").equals("6")){
    			context.put("STATUS_RUNDINGAN","SELESAI");
    		}
    		
    		tuanTanah.setDataTuanTanah(id_siasatan);
    		paparTuanTanah = tuanTanah.getDataTuanTanah();
    		Hashtable hT = (Hashtable)paparTuanTanah.get(0);
    		
    		if(hT.get("TEMPOH_MILIK_TANAH").equals("")){
    		
	    		context.put("modeTuanTanah","newTuanTanah");
	    		context.put("readonlyTuanTanah","");
	    		context.put("disabledTuanTanah","");
				
	    		context.put("TEMPOH_MILIK_TANAH","");
	    		context.put("CARA_MILIK","");
	    		context.put("HARGA_BELI","");
	    		context.put("BEBANAN","");
	    		context.put("KETERANGAN_TUAN_TANAH","");
	    		context.put("JENIS_TANAMAN","");
	    		context.put("JENIS_BANGUNAN","");
	    		context.put("FLAG_PECAH_SEMPADAN","0");
	    		context.put("TARIKH_PECAH_SEMPADAN","");
	    		context.put("FLAG_TUKAR_SYARAT","0");
	    		context.put("TARIKH_TUKAR_SYARAT","");
    		}
    		else{
    			
    			context.put("modeTuanTanah","paparTuanTanah");
        		context.put("readonlyTuanTanah","readonly");
        		context.put("disabledTuanTanah","disabled");
    			
    			context.put("TEMPOH_MILIK_TANAH",hT.get("TEMPOH_MILIK_TANAH"));
        		context.put("CARA_MILIK",hT.get("CARA_MILIK"));
        		context.put("HARGA_BELI",Utils.format2Decimal(Double.parseDouble(hT.get("HARGA_BELI").toString())));
        		context.put("BEBANAN",hT.get("BEBANAN"));
        		context.put("KETERANGAN_TUAN_TANAH",hT.get("KETERANGAN_TUAN_TANAH"));
        		context.put("JENIS_TANAMAN",hT.get("JENIS_TANAMAN"));
        		context.put("JENIS_BANGUNAN",hT.get("JENIS_BANGUNAN"));
        		context.put("FLAG_PECAH_SEMPADAN",hT.get("FLAG_PECAH_SEMPADAN"));
        		context.put("TARIKH_PECAH_SEMPADAN",hT.get("TARIKH_PECAH_SEMPADAN"));
        		context.put("FLAG_TUKAR_SYARAT",hT.get("FLAG_TUKAR_SYARAT"));
        		context.put("TARIKH_TUKAR_SYARAT",hT.get("TARIKH_TUKAR_SYARAT"));
        		
    			
    		}
    		
    		
    	}
    	else if ("simpanTuanTanah".equals(action)){
    		
    		updateTuanTanah(session);
    		vm = "app/ppt/frmHakmilikSementaraPerundinganTuanTanah.jsp";
    		context.put("modeTuanTanah","paparTuanTanah");
    		context.put("readonlyTuanTanah","readonly");
    		context.put("disabledTuanTanah","disabled");
    		
    		tuanTanah.setDataTuanTanah(id_siasatan);
    		paparTuanTanah = tuanTanah.getDataTuanTanah();
    		Hashtable h = (Hashtable)paparTuanTanah.get(0);
    		
    		context.put("TEMPOH_MILIK_TANAH",h.get("TEMPOH_MILIK_TANAH"));
    		context.put("CARA_MILIK",h.get("CARA_MILIK"));
    		context.put("HARGA_BELI",Utils.format2Decimal(Double.parseDouble(h.get("HARGA_BELI").toString())));
    		context.put("BEBANAN",h.get("BEBANAN"));
    		context.put("KETERANGAN_TUAN_TANAH",h.get("KETERANGAN_TUAN_TANAH"));
    		context.put("JENIS_TANAMAN",h.get("JENIS_TANAMAN"));
    		context.put("JENIS_BANGUNAN",h.get("JENIS_BANGUNAN"));
    		context.put("FLAG_PECAH_SEMPADAN",h.get("FLAG_PECAH_SEMPADAN"));
    		context.put("TARIKH_PECAH_SEMPADAN",h.get("TARIKH_PECAH_SEMPADAN"));
    		context.put("FLAG_TUKAR_SYARAT",h.get("FLAG_TUKAR_SYARAT"));
    		context.put("TARIKH_TUKAR_SYARAT",h.get("TARIKH_TUKAR_SYARAT"));
    		
    	}
    	else if ("kemaskiniTuanTanah".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraPerundinganTuanTanah.jsp";
    		context.put("modeTuanTanah","kemaskiniTuanTanah");
    		context.put("readonlyTuanTanah","");
    		context.put("disabledTuanTanah","");
    		
    		tuanTanah.setDataTuanTanah(id_siasatan);
    		paparTuanTanah = tuanTanah.getDataTuanTanah();
    		Hashtable h = (Hashtable)paparTuanTanah.get(0);
    		
    		context.put("TEMPOH_MILIK_TANAH",h.get("TEMPOH_MILIK_TANAH"));
    		context.put("CARA_MILIK",h.get("CARA_MILIK"));
    		context.put("HARGA_BELI",h.get("HARGA_BELI"));
    		context.put("BEBANAN",h.get("BEBANAN"));
    		context.put("KETERANGAN_TUAN_TANAH",h.get("KETERANGAN_TUAN_TANAH"));
    		context.put("JENIS_TANAMAN",h.get("JENIS_TANAMAN"));
    		context.put("JENIS_BANGUNAN",h.get("JENIS_BANGUNAN"));
    		context.put("FLAG_PECAH_SEMPADAN",h.get("FLAG_PECAH_SEMPADAN"));
    		context.put("TARIKH_PECAH_SEMPADAN",h.get("TARIKH_PECAH_SEMPADAN"));
    		context.put("FLAG_TUKAR_SYARAT",h.get("FLAG_TUKAR_SYARAT"));
    		context.put("TARIKH_TUKAR_SYARAT",h.get("TARIKH_TUKAR_SYARAT"));
    		
    	}
    	else if ("tabNilaian".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraPerundinganNilaianJPPH.jsp";
    		
    		nilaian.setDataNilaian(id_siasatan);
    		paparNilaian = nilaian.getDataNilaian();
    		
    		
    		if(paparNilaian.size()== 0){
    			context.put("modeNilaian", "newNilaian");
        		context.put("readonlyNilaian", "");
        		context.put("disabledNilaian","");
        		
        		
        		context.put("HARGA_SEUNIT_SO","");
        		context.put("UNIT_HARGA_SO","0");
        		context.put("HARGA_PASARAN_SO","");
        		context.put("BAYAR_PENJEJASAN","");
        		context.put("BAYAR_PECAHPISAH","");
        		context.put("BAYAR_NAIK_NILAISO","");
        		
        		context.put("HARGA_SEUNIT_JPPH","");
        		context.put("UNIT_HARGA","0");
        		context.put("HARGA_PASARAN","");
        		context.put("AMAUN_PENJEJASAN_JPPH","");
        		context.put("AMAUN_PECAHPISAH_JPPH","");
        		context.put("NAIK_NILAI_JPPH","");
        		
    		}
    		else{
    			Hashtable hN = (Hashtable)paparNilaian.get(0);
        		
    			context.put("modeNilaian", "paparNilaian");
        		context.put("readonlyNilaian", "readonly");
        		context.put("disabledNilaian","disabled");
        		
        		context.put("idTanah",hN.get("ID_TANAH"));
        		
        		if(!hN.get("HARGA_SEUNIT_SO").toString().equals(""))
        		{
        		context.put("HARGA_SEUNIT_SO",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_SEUNIT_SO").toString())));
        		}
        		else
        		{
        		context.put("HARGA_SEUNIT_SO","");	
        		}
        		context.put("UNIT_HARGA_SO",hN.get("UNIT_HARGA_SO"));
        		
        		if(!hN.get("HARGA_PASARAN_SO").toString().equals(""))
        		{
        		context.put("HARGA_PASARAN_SO",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_PASARAN_SO").toString())));
        		}
        		else
        		{
        		context.put("HARGA_PASARAN_SO","");	
        		}
        		
        		if(!hN.get("BAYAR_PENJEJASAN").toString().equals(""))
        		{
        		context.put("BAYAR_PENJEJASAN",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_PENJEJASAN").toString())));
        		}
        		else
        		{
        		context.put("BAYAR_PENJEJASAN","");	
        		}
        		
        		if(!hN.get("BAYAR_PECAHPISAH").toString().equals(""))
        		{
        		context.put("BAYAR_PECAHPISAH",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_PECAHPISAH").toString())));
        		}
        		else
        		{
        		context.put("BAYAR_PECAHPISAH","");	
        		}
        		
        		if(!hN.get("BAYAR_NAIK_NILAISO").toString().equals(""))
        		{
        		context.put("BAYAR_NAIK_NILAISO",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_NAIK_NILAISO").toString())));
        		}
        		else
        		{
        		context.put("BAYAR_NAIK_NILAISO","");	
        		}
        		
        		//context.put("HARGA_PASARAN_SO",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_PASARAN_SO").toString())));
        		//context.put("BAYAR_PENJEJASAN",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_PENJEJASAN").toString())));
        		//context.put("BAYAR_PECAHPISAH",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_PECAHPISAH").toString())));
        		//context.put("BAYAR_NAIK_NILAISO",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_NAIK_NILAISO").toString())));
        		
        		
        		if(!hN.get("HARGA_SEUNIT_JPPH").toString().equals(""))
        		{
        		context.put("HARGA_SEUNIT_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_SEUNIT_JPPH").toString())));
        		}
        		else
        		{
        		context.put("HARGA_SEUNIT_JPPH","");	
        		}
        		
        		if(!hN.get("HARGA_PASARAN").toString().equals(""))
        		{
        		context.put("HARGA_PASARAN",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_PASARAN").toString())));
        		}
        		else
        		{
        		context.put("HARGA_PASARAN","");	
        		}
        		
        		
        		if(!hN.get("AMAUN_PENJEJASAN_JPPH").toString().equals(""))
        		{
        		context.put("AMAUN_PENJEJASAN_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("AMAUN_PENJEJASAN_JPPH").toString())));
        		}
        		else
        		{
        		context.put("AMAUN_PENJEJASAN_JPPH","");	
        		}
        		
        		if(!hN.get("AMAUN_PECAHPISAH_JPPH").toString().equals(""))
        		{
        		context.put("AMAUN_PECAHPISAH_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("AMAUN_PECAHPISAH_JPPH").toString())));
        		}
        		else
        		{
        		context.put("AMAUN_PECAHPISAH_JPPH","");	
        		}
        		
        		if(!hN.get("NAIK_NILAI_JPPH").toString().equals(""))
        		{
        		context.put("NAIK_NILAI_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("NAIK_NILAI_JPPH").toString())));
        		}
        		else
        		{
        		context.put("NAIK_NILAI_JPPH","");	
        		}
        		
        		
        		//context.put("HARGA_SEUNIT_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_SEUNIT_JPPH").toString())));
        		context.put("UNIT_HARGA",hN.get("UNIT_HARGA"));
        		//context.put("HARGA_PASARAN",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_PASARAN").toString())));
        		//context.put("AMAUN_PENJEJASAN_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("AMAUN_PENJEJASAN_JPPH").toString())));
        		//context.put("AMAUN_PECAHPISAH_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("AMAUN_PECAHPISAH_JPPH").toString())));
        		//context.put("NAIK_NILAI_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("NAIK_NILAI_JPPH").toString())));
        		
    		}
	
    	}
    	else if("simpanNilaian".equals(action)){
    		
    		String idTanah = simpanNilaian(session);
    		context.put("idTanah",idTanah);
    		
    		vm = "app/ppt/frmHakmilikSementaraPerundinganNilaianJPPH.jsp";
    		context.put("modeNilaian", "paparNilaian");
    		context.put("readonlyNilaian", "readonly");
    		context.put("disabledNilaian","disabled");
    		
    		nilaian.setDataNilaian(id_siasatan);
    		paparNilaian = nilaian.getDataNilaian();
    		Hashtable hN = (Hashtable)paparNilaian.get(0);
    		
    		if(!hN.get("HARGA_SEUNIT_SO").toString().equals(""))
    		{
    		context.put("HARGA_SEUNIT_SO",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_SEUNIT_SO").toString())));
    		}
    		else
    		{
    		context.put("HARGA_SEUNIT_SO","");	
    		}
    		context.put("UNIT_HARGA_SO",hN.get("UNIT_HARGA_SO"));
    		
    		if(!hN.get("HARGA_PASARAN_SO").toString().equals(""))
    		{
    		context.put("HARGA_PASARAN_SO",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_PASARAN_SO").toString())));
    		}
    		else
    		{
    		context.put("HARGA_PASARAN_SO","");	
    		}
    		
    		if(!hN.get("BAYAR_PENJEJASAN").toString().equals(""))
    		{
    		context.put("BAYAR_PENJEJASAN",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_PENJEJASAN").toString())));
    		}
    		else
    		{
    		context.put("BAYAR_PENJEJASAN","");	
    		}
    		
    		if(!hN.get("BAYAR_PECAHPISAH").toString().equals(""))
    		{
    		context.put("BAYAR_PECAHPISAH",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_PECAHPISAH").toString())));
    		}
    		else
    		{
    		context.put("BAYAR_PECAHPISAH","");	
    		}
    		
    		if(!hN.get("BAYAR_NAIK_NILAISO").toString().equals(""))
    		{
    		context.put("BAYAR_NAIK_NILAISO",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_NAIK_NILAISO").toString())));
    		}
    		else
    		{
    		context.put("BAYAR_NAIK_NILAISO","");	
    		}
    		
    		//context.put("HARGA_PASARAN_SO",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_PASARAN_SO").toString())));
    		//context.put("BAYAR_PENJEJASAN",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_PENJEJASAN").toString())));
    		//context.put("BAYAR_PECAHPISAH",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_PECAHPISAH").toString())));
    		//context.put("BAYAR_NAIK_NILAISO",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_NAIK_NILAISO").toString())));
    		
    		
    		if(!hN.get("HARGA_SEUNIT_JPPH").toString().equals(""))
    		{
    		context.put("HARGA_SEUNIT_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_SEUNIT_JPPH").toString())));
    		}
    		else
    		{
    		context.put("HARGA_SEUNIT_JPPH","");	
    		}
    		
    		if(!hN.get("HARGA_PASARAN").toString().equals(""))
    		{
    		context.put("HARGA_PASARAN",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_PASARAN").toString())));
    		}
    		else
    		{
    		context.put("HARGA_PASARAN","");	
    		}
    		
    		
    		if(!hN.get("AMAUN_PENJEJASAN_JPPH").toString().equals(""))
    		{
    		context.put("AMAUN_PENJEJASAN_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("AMAUN_PENJEJASAN_JPPH").toString())));
    		}
    		else
    		{
    		context.put("AMAUN_PENJEJASAN_JPPH","");	
    		}
    		
    		if(!hN.get("AMAUN_PECAHPISAH_JPPH").toString().equals(""))
    		{
    		context.put("AMAUN_PECAHPISAH_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("AMAUN_PECAHPISAH_JPPH").toString())));
    		}
    		else
    		{
    		context.put("AMAUN_PECAHPISAH_JPPH","");	
    		}
    		
    		if(!hN.get("NAIK_NILAI_JPPH").toString().equals(""))
    		{
    		context.put("NAIK_NILAI_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("NAIK_NILAI_JPPH").toString())));
    		}
    		else
    		{
    		context.put("NAIK_NILAI_JPPH","");	
    		}
    		
    		
    		//context.put("HARGA_SEUNIT_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_SEUNIT_JPPH").toString())));
    		context.put("UNIT_HARGA",hN.get("UNIT_HARGA"));
    		//context.put("HARGA_PASARAN",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_PASARAN").toString())));
    		//context.put("AMAUN_PENJEJASAN_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("AMAUN_PENJEJASAN_JPPH").toString())));
    		//context.put("AMAUN_PECAHPISAH_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("AMAUN_PECAHPISAH_JPPH").toString())));
    		//context.put("NAIK_NILAI_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("NAIK_NILAI_JPPH").toString())));
    		
    		
    	}
    	else if ("kemaskiniNilaian".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraPerundinganNilaianJPPH.jsp";
    		context.put("modeNilaian", "updateNilaian");
    		context.put("readonlyNilaian", "");
    		context.put("disabledNilaian","");
    		
    		nilaian.setDataNilaian(id_siasatan);
    		paparNilaian = nilaian.getDataNilaian();
    		Hashtable hN = (Hashtable)paparNilaian.get(0);
    		
    		
    		
    		context.put("HARGA_SEUNIT_SO",hN.get("HARGA_SEUNIT_SO").toString());
    		
    		context.put("UNIT_HARGA_SO",hN.get("UNIT_HARGA_SO"));
    		
    		
    		context.put("HARGA_PASARAN_SO",hN.get("HARGA_PASARAN_SO").toString());
    		
    		
    		context.put("BAYAR_PENJEJASAN",hN.get("BAYAR_PENJEJASAN").toString());
    		
    		
    		
    		context.put("BAYAR_PECAHPISAH",hN.get("BAYAR_PECAHPISAH").toString());
    		
    		
    		
    		
    		context.put("BAYAR_NAIK_NILAISO",hN.get("BAYAR_NAIK_NILAISO").toString());
    		
    		
    		//context.put("HARGA_PASARAN_SO",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_PASARAN_SO").toString())));
    		//context.put("BAYAR_PENJEJASAN",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_PENJEJASAN").toString())));
    		//context.put("BAYAR_PECAHPISAH",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_PECAHPISAH").toString())));
    		//context.put("BAYAR_NAIK_NILAISO",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_NAIK_NILAISO").toString())));
    		
    		
    		
    		context.put("HARGA_SEUNIT_JPPH",hN.get("HARGA_SEUNIT_JPPH").toString());
    		
    		
    		
    		context.put("HARGA_PASARAN",hN.get("HARGA_PASARAN").toString());
    		
    		
    		
    		
    		context.put("AMAUN_PENJEJASAN_JPPH",hN.get("AMAUN_PENJEJASAN_JPPH").toString());
    		
    		
    		
    		context.put("AMAUN_PECAHPISAH_JPPH",hN.get("AMAUN_PECAHPISAH_JPPH").toString());
    		
    		
    		
    		context.put("NAIK_NILAI_JPPH",hN.get("NAIK_NILAI_JPPH").toString());
    		
    		
    		//context.put("HARGA_SEUNIT_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_SEUNIT_JPPH").toString())));
    		context.put("UNIT_HARGA",hN.get("UNIT_HARGA"));
    		//context.put("HARGA_PASARAN",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_PASARAN").toString())));
    		//context.put("AMAUN_PENJEJASAN_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("AMAUN_PENJEJASAN_JPPH").toString())));
    		//context.put("AMAUN_PECAHPISAH_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("AMAUN_PECAHPISAH_JPPH").toString())));
    		//context.put("NAIK_NILAI_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("NAIK_NILAI_JPPH").toString())));
    		
    		
    		
    	}
    	else if ("updateNilaian".equals(action)){
    		
    		updateNilaian(session);
    		vm = "app/ppt/frmHakmilikSementaraPerundinganNilaianJPPH.jsp";
    		context.put("modeNilaian", "paparNilaian");
    		context.put("readonlyNilaian", "readonly");
    		context.put("disabledNilaian","disabled");
    		
    		nilaian.setDataNilaian(id_siasatan);
    		paparNilaian = nilaian.getDataNilaian();
    		Hashtable hN = (Hashtable)paparNilaian.get(0);
    		
    		
    		if(!hN.get("HARGA_SEUNIT_SO").toString().equals(""))
    		{
    		context.put("HARGA_SEUNIT_SO",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_SEUNIT_SO").toString())));
    		}
    		else
    		{
    		context.put("HARGA_SEUNIT_SO","");	
    		}
    		context.put("UNIT_HARGA_SO",hN.get("UNIT_HARGA_SO"));
    		
    		if(!hN.get("HARGA_PASARAN_SO").toString().equals(""))
    		{
    		context.put("HARGA_PASARAN_SO",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_PASARAN_SO").toString())));
    		}
    		else
    		{
    		context.put("HARGA_PASARAN_SO","");	
    		}
    		
    		if(!hN.get("BAYAR_PENJEJASAN").toString().equals(""))
    		{
    		context.put("BAYAR_PENJEJASAN",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_PENJEJASAN").toString())));
    		}
    		else
    		{
    		context.put("BAYAR_PENJEJASAN","");	
    		}
    		
    		if(!hN.get("BAYAR_PECAHPISAH").toString().equals(""))
    		{
    		context.put("BAYAR_PECAHPISAH",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_PECAHPISAH").toString())));
    		}
    		else
    		{
    		context.put("BAYAR_PECAHPISAH","");	
    		}
    		
    		if(!hN.get("BAYAR_NAIK_NILAISO").toString().equals(""))
    		{
    		context.put("BAYAR_NAIK_NILAISO",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_NAIK_NILAISO").toString())));
    		}
    		else
    		{
    		context.put("BAYAR_NAIK_NILAISO","");	
    		}
    		
    		//context.put("HARGA_PASARAN_SO",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_PASARAN_SO").toString())));
    		//context.put("BAYAR_PENJEJASAN",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_PENJEJASAN").toString())));
    		//context.put("BAYAR_PECAHPISAH",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_PECAHPISAH").toString())));
    		//context.put("BAYAR_NAIK_NILAISO",Utils.format2Decimal(Double.parseDouble(hN.get("BAYAR_NAIK_NILAISO").toString())));
    		
    		
    		if(!hN.get("HARGA_SEUNIT_JPPH").toString().equals(""))
    		{
    		context.put("HARGA_SEUNIT_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_SEUNIT_JPPH").toString())));
    		}
    		else
    		{
    		context.put("HARGA_SEUNIT_JPPH","");	
    		}
    		
    		if(!hN.get("HARGA_PASARAN").toString().equals(""))
    		{
    		context.put("HARGA_PASARAN",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_PASARAN").toString())));
    		}
    		else
    		{
    		context.put("HARGA_PASARAN","");	
    		}
    		
    		
    		if(!hN.get("AMAUN_PENJEJASAN_JPPH").toString().equals(""))
    		{
    		context.put("AMAUN_PENJEJASAN_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("AMAUN_PENJEJASAN_JPPH").toString())));
    		}
    		else
    		{
    		context.put("AMAUN_PENJEJASAN_JPPH","");	
    		}
    		
    		if(!hN.get("AMAUN_PECAHPISAH_JPPH").toString().equals(""))
    		{
    		context.put("AMAUN_PECAHPISAH_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("AMAUN_PECAHPISAH_JPPH").toString())));
    		}
    		else
    		{
    		context.put("AMAUN_PECAHPISAH_JPPH","");	
    		}
    		
    		if(!hN.get("NAIK_NILAI_JPPH").toString().equals(""))
    		{
    		context.put("NAIK_NILAI_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("NAIK_NILAI_JPPH").toString())));
    		}
    		else
    		{
    		context.put("NAIK_NILAI_JPPH","");	
    		}
    		
    		
    		//context.put("HARGA_SEUNIT_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_SEUNIT_JPPH").toString())));
    		context.put("UNIT_HARGA",hN.get("UNIT_HARGA"));
    		//context.put("HARGA_PASARAN",Utils.format2Decimal(Double.parseDouble(hN.get("HARGA_PASARAN").toString())));
    		//context.put("AMAUN_PENJEJASAN_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("AMAUN_PENJEJASAN_JPPH").toString())));
    		//context.put("AMAUN_PECAHPISAH_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("AMAUN_PECAHPISAH_JPPH").toString())));
    		//context.put("NAIK_NILAI_JPPH",Utils.format2Decimal(Double.parseDouble(hN.get("NAIK_NILAI_JPPH").toString())));
    		
    		
    	}
    	
    	else if ("tabTuntutan".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraPerundinganTuntutan.jsp";
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
			
			tuntutan.setDataTuntutan(id_siasatan);
			paparTuntutan = tuntutan.getDataTuntutan();
			Hashtable hT = (Hashtable)paparTuntutan.get(0);
			
			if(hT.get("TUNTUTAN_TUANTNH").equals("")){
				
				context.put("modeTuntutan","newTuntutan");
				context.put("readonlyTuntutan","");
				context.put("disabledTuntutan","");
				
				context.put("TUNTUTAN_TUANTNH","");
				context.put("TUNTUTAN_PB_BEBANAN","");
				context.put("TUNTUTAN_PB_TDKDAFTAR","");
				context.put("TUNTUTAN_PB_LAIN","");
				
			}
			else{
				
				context.put("modeTuntutan","paparTuntutan");
				context.put("readonlyTuntutan","readonly");
				context.put("disabledTuntutan","disabled");
				
				context.put("TUNTUTAN_TUANTNH",hT.get("TUNTUTAN_TUANTNH"));
				context.put("TUNTUTAN_PB_BEBANAN",hT.get("TUNTUTAN_PB_BEBANAN"));
				context.put("TUNTUTAN_PB_TDKDAFTAR",hT.get("TUNTUTAN_PB_TDKDAFTAR"));
				context.put("TUNTUTAN_PB_LAIN",hT.get("TUNTUTAN_PB_LAIN"));
				
			}
			
    		
    	}
    	else if ("updateTuntutan".equals(action)){
    		
    		updateTuntutan(session);
    		vm = "app/ppt/frmHakmilikSementaraPerundinganTuntutan.jsp";
    		context.put("modeTuntutan","paparTuntutan");
			context.put("readonlyTuntutan","readonly");
			context.put("disabledTuntutan","disabled");
			
			tuntutan.setDataTuntutan(id_siasatan);
			paparTuntutan = tuntutan.getDataTuntutan();
			Hashtable hT = (Hashtable)paparTuntutan.get(0);
			
			context.put("TUNTUTAN_TUANTNH",hT.get("TUNTUTAN_TUANTNH"));
			context.put("TUNTUTAN_PB_BEBANAN",hT.get("TUNTUTAN_PB_BEBANAN"));
			context.put("TUNTUTAN_PB_TDKDAFTAR",hT.get("TUNTUTAN_PB_TDKDAFTAR"));
			context.put("TUNTUTAN_PB_LAIN",hT.get("TUNTUTAN_PB_LAIN"));
			
    		
    	}
    	else if ("kemaskiniTuntutan".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraPerundinganTuntutan.jsp";
    		context.put("modeTuntutan","kemaskiniTuntutan");
			context.put("readonlyTuntutan","");
			context.put("disabledTuntutan","");
			
			tuntutan.setDataTuntutan(id_siasatan);
			paparTuntutan = tuntutan.getDataTuntutan();
			Hashtable hT = (Hashtable)paparTuntutan.get(0);
			
			context.put("TUNTUTAN_TUANTNH",hT.get("TUNTUTAN_TUANTNH"));
			context.put("TUNTUTAN_PB_BEBANAN",hT.get("TUNTUTAN_PB_BEBANAN"));
			context.put("TUNTUTAN_PB_TDKDAFTAR",hT.get("TUNTUTAN_PB_TDKDAFTAR"));
			context.put("TUNTUTAN_PB_LAIN",hT.get("TUNTUTAN_PB_LAIN"));
			
    		
    	}
    	
    	else if ("tabBantahan".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraPerundinganBantahan.jsp";
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
			
			bantahan.setDataBantahan(id_siasatan);
			paparBantahan = bantahan.getDataBantahan();
			Hashtable hB = (Hashtable)paparBantahan.get(0);
			
			if(hB.get("BANTAHAN_TUANTNH").equals("")){
				
				context.put("modeBantahan","newBantahan");
				context.put("readonlyBantahan","");
				context.put("disabledBantahan","");
				
				context.put("BANTAHAN_TUANTNH","");
				context.put("BANTAHAN_AGENSI","");
				context.put("BANTAHAN_LAIN","");
				
			}
			else{
				context.put("modeBantahan","paparBantahan");
				context.put("readonlyBantahan","readonly");
				context.put("disabledBantahan","disabled");
				
				context.put("BANTAHAN_TUANTNH",hB.get("BANTAHAN_TUANTNH"));
				context.put("BANTAHAN_AGENSI",hB.get("BANTAHAN_AGENSI"));
				context.put("BANTAHAN_LAIN",hB.get("BANTAHAN_LAIN"));
			}
	
    	}
    	else if ("updateBantahan".equals(action)){
    		updateBantahan(session);
    		vm = "app/ppt/frmHakmilikSementaraPerundinganBantahan.jsp";
    		context.put("modeBantahan","paparBantahan");
			context.put("readonlyBantahan","readonly");
			context.put("disabledBantahan","disabled");

			bantahan.setDataBantahan(id_siasatan);
			paparBantahan = bantahan.getDataBantahan();
			Hashtable hB = (Hashtable)paparBantahan.get(0);
			
			context.put("BANTAHAN_TUANTNH",hB.get("BANTAHAN_TUANTNH"));
			context.put("BANTAHAN_AGENSI",hB.get("BANTAHAN_AGENSI"));
			context.put("BANTAHAN_LAIN",hB.get("BANTAHAN_LAIN"));

    	}
    	else if ("kemaskiniBantahan".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraPerundinganBantahan.jsp";
    		context.put("modeBantahan","kemaskiniBantahan");
			context.put("readonlyBantahan","");
			context.put("disabledBantahan","");

			bantahan.setDataBantahan(id_siasatan);
			paparBantahan = bantahan.getDataBantahan();
			Hashtable hB = (Hashtable)paparBantahan.get(0);
			
			context.put("BANTAHAN_TUANTNH",hB.get("BANTAHAN_TUANTNH"));
			context.put("BANTAHAN_AGENSI",hB.get("BANTAHAN_AGENSI"));
			context.put("BANTAHAN_LAIN",hB.get("BANTAHAN_LAIN"));

    		
    	}
    	else if ("tabKeputusan".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraPerundinganKeputusan.jsp";
    		context.put("modeKeputusan","newKeputusan");
    		context.put("readonlyKeputusan","");
    		context.put("disabledKeputusan","");
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
			
			kptsn.setListPb(id_siasatan);
			listPB = kptsn.getListPb();
			context.put("SenaraiPB",listPB);
	
    	}
    	else if("paparPB".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraPerundinganKeputusan.jsp";
    		context.put("modeKeputusan","paparKeputusan");
    		context.put("readonlyKeputusan","readonly");
    		context.put("disabledKeputusan","disabled");
    		
    		
			kptsn.setDataHakmilik(idHakmilikPB);
			paparHakmilik = kptsn.getDataHakmilik();
			Hashtable hM = (Hashtable)paparHakmilik.get(0);
			
			if((!hM.get("NILAI_SEWA_BULANAN").equals(" ")) && (!hM.get("NILAI_SEWA_BULANAN").equals(null)) && (hM.get("NILAI_SEWA_BULANAN")!="")){
				
				context.put("NILAI_SEWA_BULANAN",Utils.format2Decimal(Double.parseDouble(hM.get("NILAI_SEWA_BULANAN").toString())));

			}
			else{
				context.put("NILAI_SEWA_BULANAN",hM.get("NILAI_SEWA_BULANAN"));

			}
			
			if((!hM.get("NILAI_SEWA_KESELURUHAN").equals(" ")) && (!hM.get("NILAI_SEWA_KESELURUHAN").equals(null)) && (hM.get("NILAI_SEWA_KESELURUHAN")!="")){
				
				context.put("NILAI_SEWA_SELURUH",Utils.format2Decimal(Double.parseDouble(hM.get("NILAI_SEWA_KESELURUHAN").toString())));
			}
			else{
				context.put("NILAI_SEWA_SELURUH",hM.get("NILAI_SEWA_KESELURUHAN"));

			}
			if((!hM.get("TAWARAN_PAMPASAN").equals(" ")) && (!hM.get("TAWARAN_PAMPASAN").equals(null)) && (hM.get("TAWARAN_PAMPASAN")!= "")){
				
				context.put("TAWARAN_PAMPASAN",Utils.format2Decimal(Double.parseDouble(hM.get("TAWARAN_PAMPASAN").toString())));
			}
			else{
				context.put("TAWARAN_PAMPASAN",hM.get("TAWARAN_PAMPASAN"));

			}
			context.put("KEPUTUSAN_PERUNDINGAN",hM.get("KEPUTUSAN_PERUNDINGAN").toString());
			context.put("ULASAN_KEPUTUSAN",hM.get("ULASAN_KEPUTUSAN"));
			context.put("ID_HAKMILIK",hM.get("ID_HAKMILIK"));
			context.put("ID_BORANGQ",hM.get("ID_BORANGQ"));
			context.put("LUAS_SEWA",hM.get("LUAS_SEWA"));
			context.put("TEMPOH_PENDUDUKAN",hM.get("TEMPOH_PENDUDUKAN"));
			context.put("TARIKH_MULA",hM.get("TARIKH_MULA"));
			context.put("TARIKH_AKHIR",hM.get("TARIKH_AKHIR"));
			context.put("NAMA_PB",hM.get("NAMA_PB"));
			context.put("NO_AKAUN",hM.get("NO_AKAUN"));
			context.put("NAMA_BANK",hM.get("NAMA_BANK"));
	
			
    		kptsn.setListPb(id_siasatan);
			listPB = kptsn.getListPb();
			context.put("SenaraiPB",listPB);
    		
    	}
    	else if("kemaskiniKeputusan".equals(action)){
    		vm = "app/ppt/frmHakmilikSementaraPerundinganKeputusan.jsp";
    		context.put("modeKeputusan","updateKeputusan");
    		context.put("readonlyKeputusan","");
    		context.put("disabledKeputusan","");
    		
    		
			kptsn.setDataHakmilik(idHakmilikPB);
			paparHakmilik = kptsn.getDataHakmilik();
			Hashtable hM = (Hashtable)paparHakmilik.get(0);
			
			if(!hM.get("NILAI_SEWA_BULANAN").equals("")){
				
				context.put("NILAI_SEWA_BULANAN",hM.get("NILAI_SEWA_BULANAN"));

			}
			else{
				context.put("NILAI_SEWA_BULANAN",hM.get("NILAI_SEWA_BULANAN"));

			}
			if(!hM.get("NILAI_SEWA_KESELURUHAN").equals("")){
				
				context.put("NILAI_SEWA_SELURUH",hM.get("NILAI_SEWA_KESELURUHAN"));
			}
			else{
				context.put("NILAI_SEWA_SELURUH",hM.get("NILAI_SEWA_KESELURUHAN"));

			}
			if(!hM.get("TAWARAN_PAMPASAN").equals("")){
				
				context.put("TAWARAN_PAMPASAN",hM.get("TAWARAN_PAMPASAN"));
			}
			else{
				context.put("TAWARAN_PAMPASAN",hM.get("TAWARAN_PAMPASAN"));

			}
			context.put("KEPUTUSAN_PERUNDINGAN",hM.get("KEPUTUSAN_PERUNDINGAN").toString());
			context.put("ULASAN_KEPUTUSAN",hM.get("ULASAN_KEPUTUSAN"));
			context.put("ID_HAKMILIK",hM.get("ID_HAKMILIK"));
			context.put("ID_BORANGQ",hM.get("ID_BORANGQ"));
			context.put("LUAS_SEWA",hM.get("LUAS_SEWA"));
			context.put("TEMPOH_PENDUDUKAN",hM.get("TEMPOH_PENDUDUKAN"));
			context.put("TARIKH_MULA",hM.get("TARIKH_MULA"));
			context.put("TARIKH_AKHIR",hM.get("TARIKH_AKHIR"));
			context.put("NAMA_PB",hM.get("NAMA_PB"));
			context.put("NO_AKAUN",hM.get("NO_AKAUN"));
			context.put("NAMA_BANK",hM.get("NAMA_BANK"));
	
			
    		kptsn.setListPb(id_siasatan);
			listPB = kptsn.getListPb();
			context.put("SenaraiPB",listPB);
    	}
    	else if ("updateKeputusan".equals(action)){
    		updateKeputusan(session);
    		vm = "app/ppt/frmHakmilikSementaraPerundinganKeputusan.jsp";
    		context.put("modeKeputusan","paparKeputusan");
    		context.put("readonlyKeputusan","readonly");
    		context.put("disabledKeputusan","disabled");
    		
    		
			kptsn.setDataHakmilik(idHakmilikPB);
			paparHakmilik = kptsn.getDataHakmilik();
			Hashtable hM = (Hashtable)paparHakmilik.get(0);
			
			if(!hM.get("NILAI_SEWA_BULANAN").equals("")){
				
				context.put("NILAI_SEWA_BULANAN",Utils.format2Decimal(Double.parseDouble(hM.get("NILAI_SEWA_BULANAN").toString())));

			}
			else{
				context.put("NILAI_SEWA_BULANAN",hM.get("NILAI_SEWA_BULANAN"));

			}
			if(!hM.get("NILAI_SEWA_KESELURUHAN").equals("")){
				
				context.put("NILAI_SEWA_SELURUH",Utils.format2Decimal(Double.parseDouble(hM.get("NILAI_SEWA_KESELURUHAN").toString())));
			}
			else{
				context.put("NILAI_SEWA_SELURUH",hM.get("NILAI_SEWA_KESELURUHAN"));

			}
			if(!hM.get("TAWARAN_PAMPASAN").equals("")){
				
				context.put("TAWARAN_PAMPASAN",Utils.format2Decimal(Double.parseDouble(hM.get("TAWARAN_PAMPASAN").toString())));
			}
			else{
				context.put("TAWARAN_PAMPASAN",hM.get("TAWARAN_PAMPASAN"));

			}
			context.put("KEPUTUSAN_PERUNDINGAN",hM.get("KEPUTUSAN_PERUNDINGAN").toString());
			context.put("ULASAN_KEPUTUSAN",hM.get("ULASAN_KEPUTUSAN"));
			context.put("ID_HAKMILIK",hM.get("ID_HAKMILIK"));
			context.put("ID_BORANGQ",hM.get("ID_BORANGQ"));
			context.put("LUAS_SEWA",hM.get("LUAS_SEWA"));
			context.put("TEMPOH_PENDUDUKAN",hM.get("TEMPOH_PENDUDUKAN"));
			context.put("TARIKH_MULA",hM.get("TARIKH_MULA"));
			context.put("TARIKH_AKHIR",hM.get("TARIKH_AKHIR"));
			context.put("NAMA_PB",hM.get("NAMA_PB"));
			context.put("NO_AKAUN",hM.get("NO_AKAUN"));
			context.put("NAMA_BANK",hM.get("NAMA_BANK"));
	
			
    		kptsn.setListPb(id_siasatan);
			listPB = kptsn.getListPb();
			context.put("SenaraiPB",listPB);
    		
    		
    	}
    	else {
    		
    		vm = "app/ppt/frmHakmilikSementaraSenaraiPerundingan.jsp";
    		
    		if (!"".equals(getParam("txdTarikh")));
    		tarikhmohon = getParam("txdTarikh");
    	
    		if (!"".equals(getParam("txtNoFail")));
    			nofail = getParam("txtNoFail");
    			
    		if (!"".equals(getParam("txtNoRujJKPTGNegeri")));
    			noJKPTG = getParam("txtNoRujJKPTGNegeri");
    		
    		if(!"".equals(getParam("socStatus")))
    			cStatus = getParam("socStatus");
    		
    		listCarianFail.setCarianFail(nofail, tarikhmohon, cStatus, noJKPTG);		
    		list = listCarianFail.getList();
    							
    	
    	    context.put("PermohonanList", list);
    	    context.put("list_size", list.size());
    	    context.put("CarianFail", nofail);  
    	    context.put("CarianNoJKPTG",noJKPTG );
    	    context.put("tarikhPermohonan", tarikhmohon);
    	    context.put("SelectStatus", HTML.SelectStatusPPT("socStatus",Utils.parseLong(cStatus),"style=width:130px"));
    	    setupPage(session,action,list);
    	}
    	
		return vm;
	}
	public void setupPage(HttpSession session,String action,Vector list) {
		try {
		
		this.context.put("totalRecords",list.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
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
	    	
	    Paging paging = new Paging(session,list,itemsPerPage);
		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("PermohonanList",paging.getPage(page));
	    this.context.put("page", new Integer(page));
	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}
			
		private void updateTuanTanah(HttpSession session) throws Exception{
			
			Hashtable h = new Hashtable();
		    
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			String idSiasatan = getParam("id_siasatan");
			
			h.put("idPermohonan",getParam("id_permohonan"));
			h.put("idSiasatan",idSiasatan);
			h.put("tarikhMilikTanah",getParam("txdTkhMilik"));
			h.put("caraMilikTanah",getParam("txtCaraMilik"));
			h.put("hargaTanah",getParam("txtHargaTnh"));
			h.put("bebananTanah",getParam("txtBebanan"));
			h.put("keteranganTuanTanah",getParam("txtKtrgnTuanTnh"));
			h.put("jenisTanaman",getParam("txtJnsTanaman"));
			h.put("jenisBangunan",getParam("txtJnsBangunan"));
			h.put("statusPecahSempadan",getParam("socStatusPecahSempadan"));
			h.put("tarikhPecahSempadan",getParam("txdTkhPecahSempadan"));
			h.put("statusTukarSyarat",getParam("socStatusTukarSyarat"));
			h.put("tarikhTukarSyarat",getParam("txdTkhPecahSyarat"));
			h.put("idKemaskini",user_id);
			
			FrmHakmilikSementaraPerundinganTuanTanahData.updateTuanTanah(h);
		
	}
		
		
		private String simpanNilaian(HttpSession session) throws Exception{
			
			Hashtable h = new Hashtable();
		    
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			String idHakmilik = getParam("idHakmilik");
			
			h.put("idHakmilik",idHakmilik);
			h.put("hargaSeunitSO",getParam("txtAnggaranHrgSeunit"));
			h.put("unitHargaSO",getParam("socUnitHargaSO"));
			h.put("hargaPasaranSO",getParam("txtAnggaranHrgPasaran"));
			h.put("penjejasanSO",getParam("txtAnggaranPenjejasan"));
			h.put("pecahpisahSO",getParam("txtAnggaranPecahpisah"));
			h.put("naikNilaiSO",getParam("txtAnggaranKenaikanNilai"));
			h.put("hargaSeunitJPPH",getParam("txtJPPHHrgSeunit"));
			h.put("unitHargaJPPH",getParam("socUnitHargaJPPH"));
			h.put("hargaPasaranJPPH",getParam("txtJPPHHrgPasaran"));
			h.put("penjejasanJPPH",getParam("txtJPPHPenjejasan"));
			h.put("pecahpisahJPPH",getParam("txtJPPHPecahpisah"));
			h.put("naikNilaiJPPH",getParam("txtJPPHKenaikanNilai"));
			h.put("idMasuk",user_id);
			
			return FrmHakmilikSementaraPerundinganNilaianJPPHData.simpanNilaian(h);
		}
		
		private void updateNilaian(HttpSession session) throws Exception{
			
			Hashtable h = new Hashtable();
		    
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			String idTanah = getParam("idTanah");
			
			h.put("idTanah",idTanah);
			h.put("hargaSeunitSO",getParam("txtAnggaranHrgSeunit"));
			h.put("unitHargaSO",getParam("socUnitHargaSO"));
			h.put("hargaPasaranSO",getParam("txtAnggaranHrgPasaran"));
			h.put("penjejasanSO",getParam("txtAnggaranPenjejasan"));
			h.put("pecahpisahSO",getParam("txtAnggaranPecahpisah"));
			h.put("naikNilaiSO",getParam("txtAnggaranKenaikanNilai"));
			h.put("hargaSeunitJPPH",getParam("txtJPPHHrgSeunit"));
			h.put("unitHargaJPPH",getParam("socUnitHargaJPPH"));
			h.put("hargaPasaranJPPH",getParam("txtJPPHHrgPasaran"));
			h.put("penjejasanJPPH",getParam("txtJPPHPenjejasan"));
			h.put("pecahpisahJPPH",getParam("txtJPPHPecahpisah"));
			h.put("naikNilaiJPPH",getParam("txtJPPHKenaikanNilai"));
			h.put("idKemaskini",user_id);
			
			FrmHakmilikSementaraPerundinganNilaianJPPHData.updateNilaian(h);
		}
		private void updateTuntutan(HttpSession session) throws Exception{
			
			Hashtable h = new Hashtable();
		    
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			String idSiasatan = getParam("id_siasatan");
			
			h.put("idSiasatan",idSiasatan);
			h.put("tuntutanTuanTanah",getParam("txtTuntutanTuanTnh"));
			h.put("tuntutanPBDaftar",getParam("txtTuntutanPenyewa"));
			h.put("tuntutanPBTdkDaftar",getParam("txtTuntutanCaveat"));
			h.put("tuntutanLain",getParam("txtTuntutanLain"));
			h.put("idKemaskini",user_id);
			
			FrmHakmilikSementaraPerundinganTuntutanData.updateTuntutan(h);
		}
		private void updateBantahan(HttpSession session) throws Exception{
			
			Hashtable h = new Hashtable();
		    
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			String idSiasatan = getParam("id_siasatan");
			
			h.put("idSiasatan",idSiasatan);
			h.put("bantahanTuanTanah",getParam("txtTuanTanah"));
			h.put("bantahanAgensi",getParam("txtAgensi"));
			h.put("bantahanLain",getParam("txtLainBantahan"));
			h.put("idKemaskini",user_id);
			
			FrmHakmilikSementaraPerundinganBantahanData.updateBantahan(h);
		}
		
		private void updateKeputusan(HttpSession session) throws Exception{
			
			Hashtable h = new Hashtable();
		    
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			String idSiasatan = getParam("id_siasatan");
			String idHakmilikPB = getParam("idHakmilikPB");
			String idBorangQ = getParam("idBorangQ");
			String idHakmilik = getParam("idHakmilik");
			
			h.put("idSiasatan",idSiasatan);
			h.put("idHakmilikPB",idHakmilikPB);
			h.put("idHakmilik",idHakmilik);
			h.put("idBorangQ",idBorangQ);
			h.put("nilaiSewaBulanan",getParam("txtNilaiSewaBulan"));
			h.put("keputusan",getParam("sorKeputusan"));
			h.put("ulasanKeputusan",getParam("txtUlasanKeputusan"));
			h.put("nilaiSewaSeluruh",getParam("txtNilaiSeluruhan"));
			h.put("amaunPampasan",getParam("txtAmaunPampasan"));
			h.put("idKemaskini",user_id);
			
			FrmHakmilikSementaraPerundinganKeputusanData.updateKeputusan(h);
		}
		
		

}
