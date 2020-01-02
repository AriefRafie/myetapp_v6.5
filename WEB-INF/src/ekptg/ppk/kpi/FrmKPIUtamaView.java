package ekptg.ppk.kpi;


import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmKPIUtamaView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmKPIUtamaView.class);
	
	//model
	FrmKPIUtamaModel model = new FrmKPIUtamaModel();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

    	//Utils helper
    	UtilsItem();
    	
    	//default
    	String vm = "";
    	context.put("mode","");
		context.put("isEdit","");
		context.put("showFormKPI","no");
		context.put("showSetupKPI","no");
		
		//vector
    	Vector dataSetup = null;
    	
    	//screen jsp
    	String screenUtama = "app/kpippk/frmKPIUtama.jsp";

    	
    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    	
    	//anchor
    	anchor();
    	
    	//id
    	//String UserId = (String) session.getAttribute("_ekptg_user_id");
    	//String userIdNegeri = (String) session.getAttribute("_ekptg_user_negeri");
    	context.put("portal_role",(String) session.getAttribute("_portal_role"));
    	String id_suburusan = getParam("socSuburusan");
    	
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("onchangeNegeri".equals(submit)){
    		
    		//get and set data pilihan
    		getAndSetPilihan();
    		
    		//screen
    		vm = screenUtama;
    		
    	}//close onchangeNegeri
    	
    	else if("paparMaklumatKPI".equals(submit)){
    		
    		String id_pejabatjkptg = getParam("socUnit");
    		
    		dataSetup = new Vector();
	    	dataSetup.clear();
	    	
    		//clear
    		context.put("id_kpisasaran","");
    		
			//screen validation
			context.put("showFormKPI","yes");
			
			//get and set data
			getAndSetData();
			
			//get data kebersanan kpi
			getDataKebersananKPI(id_pejabatjkptg,id_suburusan,getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			
			//get total menunggu
			totalMenunggu(id_suburusan);
			
			//jumlah bilangan aktiviti dan masa
			jumBilAktivitiDanMasa(id_pejabatjkptg,id_suburusan,getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			
			//data setup dan maklumat kpi
			model.setDataSetup(id_pejabatjkptg,id_suburusan);
			dataSetup = model.getDataSetup();
			context.put("dataSetup", dataSetup);
			
			String submit2 = getParam("command2");
	    	myLogger.info("submit[2] : " + submit2);
	    	
	    	if("paparSetupKPI".equals(submit2)){

	    		//screen validation
				context.put("showSetupKPI","yes");
	    		
				String submit3 = getParam("command3");
		    	myLogger.info("submit[3] : " + submit3);
				
		    	//NEW FORM
				if(dataSetup.size()==0){
					
					//screen validation
					context.put("mode","new");
					
					if("simpanSetupKPI".equals(submit3)){
			    		
			    		if (doPost.equals("true")) {
			    			simpanSetupKPI(session);
			    		}
			    		
			    		//screen validation
						context.put("mode","view");
						context.put("isEdit","no");
						
						model.setDataSetup(id_pejabatjkptg,id_suburusan);
						dataSetup = model.getDataSetup();
						context.put("dataSetup", dataSetup);
						
			    	}//close simpanSetupKPI
					
					
				//VIEW FORM	
				}else{
					
					Hashtable ds = (Hashtable)dataSetup.get(0);
					context.put("id_kpisasaran", (String)ds.get("ID_KPISASARAN"));
					
					//screen validation
					context.put("mode","view");
					context.put("isEdit","no");
					
					if("kemaskiniSetupKPI".equals(submit3)){
						
						//screen validation
						context.put("mode","view");
						context.put("isEdit","yes");
						
						String submit4 = getParam("command4");
				    	myLogger.info("submit[4] : " + submit4);
				    	
				    	if("updateSetupKPI".equals(submit4)){
				    		
				    		if (doPost.equals("true")) {
				    			simpanSetupKPI(session);
				    		}
				    		
				    		//screen validation
							context.put("mode","view");
							context.put("isEdit","no");
							
							model.setDataSetup(id_pejabatjkptg,id_suburusan);
							dataSetup = model.getDataSetup();
							context.put("dataSetup", dataSetup);
							
				    	}//close updateSetupKPI
						
					}//close kemaskiniSetupKPI
					
				}//close dataSetup
				
				
	    	}//close paparSetupKPI
			
			//screen
    		vm = screenUtama;
		    
    	}//close paparMaklumatKPI
    	
    	else{	
    		
    		//dropdown
    		context.put("selectSuburusan",HTML.SelectSuburusanPPK("socSuburusan",null,"style=width:325px"));
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,null,"style=width:325px onChange=\"onchangeNegeri();\""));
    		context.put("selectUnit",HTML.SelectUnitJKPTGWithDaerah("socUnit",null,"style=width:490px",null));
    		
    		//reset value
    		resetValue();
    		
    		//screen
    		vm = screenUtama;
    		
		}//close else
   	
    		context.put("id_suburusan",id_suburusan);
    		return vm;
    		
	    }//close public template
	
	
//--METHOD--//	
	
	
	private void jumBilAktivitiDanMasa(String id_pejabatjkptg,String id_suburusan,String tarikhMula,String tarikhAkhir) throws Exception{
		
		//borang a
		bilAktivitiDanMasa(id_pejabatjkptg,id_suburusan,tarikhMula,tarikhAkhir,"borangA");
		
		//borang c
		bilAktivitiDanMasa(id_pejabatjkptg,id_suburusan,tarikhMula,tarikhAkhir,"borangC");
		
		//permohonan BKE
		bilAktivitiDanMasa(id_pejabatjkptg,id_suburusan,tarikhMula,tarikhAkhir,"mohonBKE");
		
		//borang q
		bilAktivitiDanMasa(id_pejabatjkptg,id_suburusan,tarikhMula,tarikhAkhir,"borangQ");
		
		//nilaian jpph
		bilAktivitiDanMasa(id_pejabatjkptg,id_suburusan,tarikhMula,tarikhAkhir,"nilaiJPPH");
		
		//sedia notis perbicaraan sehingga penghantaran notis
		bilAktivitiDanMasa(id_pejabatjkptg,id_suburusan,tarikhMula,tarikhAkhir,"sediaNotis");
		
		//perbicaraan pertama sehingga merekod keputusan perbicaraan
		bilAktivitiDanMasa(id_pejabatjkptg,id_suburusan,tarikhMula,tarikhAkhir,"bicaraPertama");
		
		//borang L
		bilAktivitiDanMasa(id_pejabatjkptg,id_suburusan,tarikhMula,tarikhAkhir,"borangL");
		
		//borang N
		bilAktivitiDanMasa(id_pejabatjkptg,id_suburusan,tarikhMula,tarikhAkhir,"borangN");
		
		//borang J Mahkamah Tinggi
		bilAktivitiDanMasa(id_pejabatjkptg,id_suburusan,tarikhMula,tarikhAkhir,"borangJ_MT");
		
		//keputusan Mahkamah Tinggi
		bilAktivitiDanMasa(id_pejabatjkptg,id_suburusan,tarikhMula,tarikhAkhir,"keputusanMT");
		
		//borang J Ruler of The State
		bilAktivitiDanMasa(id_pejabatjkptg,id_suburusan,tarikhMula,tarikhAkhir,"borangJ_ROTS");
		
		//keputusan Ruler of The State
		bilAktivitiDanMasa(id_pejabatjkptg,id_suburusan,tarikhMula,tarikhAkhir,"keputusanROTS");
		
		//Sedia perintah sehingga cetak
		bilAktivitiDanMasa(id_pejabatjkptg,id_suburusan,tarikhMula,tarikhAkhir,"sediaPerintah");
		
	}//close jumBilAktivitiDanMasa
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void bilAktivitiDanMasa(String id_pejabatjkptg,String id_suburusan,String tarikhMula,String tarikhAkhir,String jenisAktiviti) throws Exception{
		
		Vector aktivitiDanMasa = new Vector();
		aktivitiDanMasa.clear();
		
		double jumlah_hari = 0.00;
		int jumlah_aktiviti = 0;
		
		model.setAktivitiDanMasa(id_pejabatjkptg,id_suburusan,tarikhMula,tarikhAkhir,jenisAktiviti);
		aktivitiDanMasa = model.getAktivitiDanMasa();
		if(aktivitiDanMasa.size()!=0){
			Hashtable adm = (Hashtable)aktivitiDanMasa.get(0);
			jumlah_hari = (Double)adm.get("jumlah_hari");
			jumlah_aktiviti = (Integer)adm.get("jumlah_aktiviti");
		}
		
		double purataMasa = 0.00;
		if(jumlah_hari!=0){
			purataMasa = jumlah_hari / jumlah_aktiviti;
		}
		
		
		if(jenisAktiviti.equals("borangA")){
			context.put("jumlah_hari_boranga",jumlah_hari);
			context.put("jumlah_aktiviti_boranga",jumlah_aktiviti);
			context.put("purata_masa_boranga",Utils.format2Decimal(purataMasa));
		}else if(jenisAktiviti.equals("borangC")){
			context.put("jumlah_hari_borangc",jumlah_hari);
			context.put("jumlah_aktiviti_borangc",jumlah_aktiviti);
			context.put("purata_masa_borangc",Utils.format2Decimal(purataMasa));
		}else if(jenisAktiviti.equals("nilaiJPPH")){
			context.put("jumlah_hari_nilai_jpph",jumlah_hari);
			context.put("jumlah_aktiviti_nilai_jpph",jumlah_aktiviti);
			context.put("purata_masa_nilai_jpph",Utils.format2Decimal(purataMasa));
		}else if(jenisAktiviti.equals("sediaNotis")){
			context.put("jumlah_hari_sedia_notis",jumlah_hari);
			context.put("jumlah_aktiviti_sedia_notis",jumlah_aktiviti);
			context.put("purata_masa_sedia_notis",Utils.format2Decimal(purataMasa));
		}else if(jenisAktiviti.equals("bicaraPertama")){
			context.put("jumlah_hari_bicara_pertama",jumlah_hari);
			context.put("jumlah_aktiviti_bicara_pertama",jumlah_aktiviti);
			context.put("purata_masa_bicara_pertama",Utils.format2Decimal(purataMasa));
		}else if(jenisAktiviti.equals("mohonBKE")){
			context.put("jumlah_hari_mohon_bke",jumlah_hari);
			context.put("jumlah_aktiviti_mohon_bke",jumlah_aktiviti);
			context.put("purata_masa_mohon_bke",Utils.format2Decimal(purataMasa));
		}else if(jenisAktiviti.equals("borangQ")){
			context.put("jumlah_hari_borang_q",jumlah_hari);
			context.put("jumlah_aktiviti_borang_q",jumlah_aktiviti);
			context.put("purata_masa_borang_q",Utils.format2Decimal(purataMasa));
		}else if(jenisAktiviti.equals("borangL")){
			context.put("jumlah_hari_borang_l",jumlah_hari);
			context.put("jumlah_aktiviti_borang_l",jumlah_aktiviti);
			context.put("purata_masa_borang_l",Utils.format2Decimal(purataMasa));
		}else if(jenisAktiviti.equals("borangN")){
			context.put("jumlah_hari_borang_n",jumlah_hari);
			context.put("jumlah_aktiviti_borang_n",jumlah_aktiviti);
			context.put("purata_masa_borang_n",Utils.format2Decimal(purataMasa));
		}else if(jenisAktiviti.equals("borangJ_MT")){
			context.put("jumlah_hari_borang_j_mt",jumlah_hari);
			context.put("jumlah_aktiviti_borang_j_mt",jumlah_aktiviti);
			context.put("purata_masa_borang_j_mt",Utils.format2Decimal(purataMasa));
		}else if(jenisAktiviti.equals("keputusanMT")){
			context.put("jumlah_hari_keputusan_mt",jumlah_hari);
			context.put("jumlah_aktiviti_keputusan_mt",jumlah_aktiviti);
			context.put("purata_masa_keputusan_mt",Utils.format2Decimal(purataMasa));
		}else if(jenisAktiviti.equals("borangJ_ROTS")){
			context.put("jumlah_hari_borang_j_rots",jumlah_hari);
			context.put("jumlah_aktiviti_borang_j_rots",jumlah_aktiviti);
			context.put("purata_masa_borang_j_rots",Utils.format2Decimal(purataMasa));
		}else if(jenisAktiviti.equals("keputusanROTS")){
			context.put("jumlah_hari_keputusan_rots",jumlah_hari);
			context.put("jumlah_aktiviti_keputusan_rots",jumlah_aktiviti);
			context.put("purata_masa_keputusan_rots",Utils.format2Decimal(purataMasa));
		}else if(jenisAktiviti.equals("sediaPerintah")){
			context.put("jumlah_hari_sedia_perintah",jumlah_hari);
			context.put("jumlah_aktiviti_sedia_perintah",jumlah_aktiviti);
			context.put("purata_masa_sedia_perintah",Utils.format2Decimal(purataMasa));
		}
		
		
	}//close bilAktivitiDanMasa
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getDataKebersananKPI(String id_pejabatjkptg,String id_suburusan,String tarikhMula,String tarikhAkhir) throws Exception{
		
		Vector dataKebersananKPI = new Vector();
		dataKebersananKPI.clear();
		
		String totalPermohonan = "";
		String totalSelesai = "";
		
		model.setDataKebersananKPI(id_pejabatjkptg,id_suburusan,tarikhMula,tarikhAkhir);
		dataKebersananKPI = model.getDataKebersananKPI();
		if(dataKebersananKPI.size()!=0){
			Hashtable dm = (Hashtable)dataKebersananKPI.get(0);
			totalPermohonan = (String)dm.get("TOTALPERMOHONAN");
			totalSelesai = (String)dm.get("TOTALSELESAI");
		}
		
		context.put("totalPermohonan", totalPermohonan);
		context.put("totalSelesai", totalSelesai);
		
	}//close getDataMaklumatKPI
	
	private void totalMenunggu(String id_suburusan) throws Exception{
		
		//get jumlah menunggu jpph
		jumlahMenungguJPPH(id_suburusan);
		
		//get jumlah menunggu Borang C
		jumlahMenungguBorangC(id_suburusan);
		
		//get jumlah menunggu Borang C
		jumlahMenungguBicaraKolateral(id_suburusan);
		
		//get jumlah menunggu mahkamah tinggi
		jumlahMenungguMahkamahTinggi(id_suburusan);
		
		//get jumlah menunggu ROTS
		jumlahMenungguROTS(id_suburusan);
		
	}//close totalMenunggu
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void jumlahMenungguROTS(String id_suburusan) throws Exception{
		
		String txtT11 = "";
		String txtT12 = "";
		
		Vector dataSetup = new Vector();
		dataSetup.clear();
		model.setDataSetup(getParam("socUnit"),id_suburusan);
		dataSetup = model.getDataSetup();
		if(dataSetup.size()!=0){
			Hashtable ds = (Hashtable)dataSetup.get(0);
			txtT11 = (String)ds.get("T11");
			txtT12 = (String)ds.get("T12");
		}
		
		getTotalTunggu(getParam("socUnit"),id_suburusan,getParam("txdTarikhMula"),getParam("txdTarikhAkhir"),"rots",txtT11,txtT12);
		
	}//close jumlahMenungguROTS
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void jumlahMenungguMahkamahTinggi(String id_suburusan) throws Exception{
		
		String txtT9 = "";
		String txtT10 = "";
		
		Vector dataSetup = new Vector();
		dataSetup.clear();
		model.setDataSetup(getParam("socUnit"),id_suburusan);
		dataSetup = model.getDataSetup();
		if(dataSetup.size()!=0){
			Hashtable ds = (Hashtable)dataSetup.get(0);
			txtT9 = (String)ds.get("T9");
			txtT10 = (String)ds.get("T10");
		}
		
		getTotalTunggu(getParam("socUnit"),id_suburusan,getParam("txdTarikhMula"),getParam("txdTarikhAkhir"),"mahkamahtinggi",txtT9,txtT10);
		
	}//close jumlahMenungguMahkamahTinggi
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void jumlahMenungguBicaraKolateral(String id_suburusan) throws Exception{
		
		String txtT5 = "";
		String txtT6 = "";
		
		Vector dataSetup = new Vector();
		dataSetup.clear();
		model.setDataSetup(getParam("socUnit"),id_suburusan);
		dataSetup = model.getDataSetup();
		if(dataSetup.size()!=0){
			Hashtable ds = (Hashtable)dataSetup.get(0);
			txtT5 = (String)ds.get("T5");
			txtT6 = (String)ds.get("T6");
		}
		
		getTotalTunggu(getParam("socUnit"),id_suburusan,getParam("txdTarikhMula"),getParam("txdTarikhAkhir"),"kolateral",txtT5,txtT6);
		
	}//close jumlahMenungguBicaraKolateral
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void jumlahMenungguBorangC(String id_suburusan) throws Exception{
		
		String txtT3 = "";
		String txtT4 = "";
		
		Vector dataSetup = new Vector();
		dataSetup.clear();
		model.setDataSetup(getParam("socUnit"),id_suburusan);
		dataSetup = model.getDataSetup();
		if(dataSetup.size()!=0){
			Hashtable ds = (Hashtable)dataSetup.get(0);
			txtT3 = (String)ds.get("T3");
			txtT4 = (String)ds.get("T4");
		}
		
		getTotalTunggu(getParam("socUnit"),id_suburusan,getParam("txdTarikhMula"),getParam("txdTarikhAkhir"),"borangc",txtT3,txtT4);
		
	}//close jumlahMenungguBorangC
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void jumlahMenungguJPPH(String id_suburusan) throws Exception{
		
		String txtT1 = "";
		String txtT2 = "";
		
		Vector dataSetup = new Vector();
		dataSetup.clear();
		model.setDataSetup(getParam("socUnit"),id_suburusan);
		dataSetup = model.getDataSetup();
		if(dataSetup.size()!=0){
			Hashtable ds = (Hashtable)dataSetup.get(0);
			txtT1 = (String)ds.get("T1");
			txtT2 = (String)ds.get("T2");
		}
		
		getTotalTunggu(getParam("socUnit"),id_suburusan,getParam("txdTarikhMula"),getParam("txdTarikhAkhir"),"jpph",txtT1,txtT2);
		
	}//close jumlahMenunggu
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getTotalTunggu(String id_pejabatjkptg,String id_suburusan,String tarikhMula,
									String tarikhAkhir,String jenisProsesTunggu,String bilHari1,String bilHari2) throws Exception{
		
		Vector totalTunggu = new Vector();
		totalTunggu.clear();
		
		String hijau = "";
		String kuning = "";
		String merah = "";
		
		model.setTotalTunggu(id_pejabatjkptg,id_suburusan,tarikhMula,tarikhAkhir,jenisProsesTunggu,bilHari1,bilHari2,"","");
		totalTunggu = model.getTotalTunggu();
		if(totalTunggu.size()!=0){
			Hashtable tt = (Hashtable)totalTunggu.get(0);
			hijau = (String)tt.get("HIJAU");
			kuning = (String)tt.get("KUNING");
			merah = (String)tt.get("MERAH");
		}
		
		if(jenisProsesTunggu.equals("jpph")){
			context.put("jumlahTungguJPPHHijau", hijau);
			context.put("jumlahTungguJPPHKuning", kuning);
			context.put("jumlahTungguJPPHMerah", merah);
		}else if(jenisProsesTunggu.equals("borangc")){
			context.put("jumlahTungguBorangCHijau", hijau);
			context.put("jumlahTungguBorangCKuning", kuning);
			context.put("jumlahTungguBorangCMerah", merah);
		}else if(jenisProsesTunggu.equals("kolateral")){
			context.put("jumlahTungguBicaraKolateralHijau", hijau);
			context.put("jumlahTungguBicaraKolateralKuning", kuning);
			context.put("jumlahTungguBicaraKolateralMerah", merah);
		}else if(jenisProsesTunggu.equals("mahkamahtinggi")){
			context.put("jumlahTungguMTHijau", hijau);
			context.put("jumlahTungguMTKuning", kuning);
			context.put("jumlahTungguMTMerah", merah);
		}else if(jenisProsesTunggu.equals("rots")){
			context.put("jumlahTungguROTSHijau", hijau);
			context.put("jumlahTungguROTSKuning", kuning);
			context.put("jumlahTungguROTSMerah", merah);
		}
		
	}//close getTotalTungguJPPH
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void simpanSetupKPI(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
		
		h.put("id_kpisasaran", getParam("id_kpisasaran"));
		h.put("socUnit", getParam("socUnit"));
		h.put("socSuburusan", getParam("socSuburusan"));
	
		if(getParam("txtF1")==null){h.put("txtF1", "0");}else{h.put("txtF1", getParam("txtF1"));}
		
		h.put("txtF1", getParam("txtF1")== ""?"0":getParam("txtF1"));
		h.put("txtF2", getParam("txtF2")== ""?"0":getParam("txtF2"));
		h.put("txtF3", getParam("txtF3")== ""?"0":getParam("txtF3"));
		h.put("txtF4", getParam("txtF4")== ""?"0":getParam("txtF4"));
		h.put("txtF5", getParam("txtF5")== ""?"0":getParam("txtF5"));
		h.put("txtF6", getParam("txtF6")== ""?"0":getParam("txtF6"));
		h.put("txtF7", getParam("txtF7")== ""?"0":getParam("txtF7"));
		h.put("txtF8", getParam("txtF8")== ""?"0":getParam("txtF8"));
		h.put("txtF9", getParam("txtF9")== ""?"0":getParam("txtF9"));
		h.put("txtF10", getParam("txtF10")== ""?"0":getParam("txtF10"));
		h.put("txtF11", getParam("txtF11")== ""?"0":getParam("txtF11"));
		h.put("txtF12", getParam("txtF12")== ""?"0":getParam("txtF12"));
		h.put("txtF13", getParam("txtF13")== ""?"0":getParam("txtF13"));
		h.put("txtF14", getParam("txtF14")== ""?"0":getParam("txtF14"));
		h.put("txtF15", getParam("txtF15")== ""?"0":getParam("txtF15"));
		
		h.put("txtT1", getParam("txtT1")== null?"0":getParam("txtT1"));
		h.put("txtT2", getParam("txtT2")== null?"0":getParam("txtT2"));
		h.put("txtT3", getParam("txtT3")== null?"0":getParam("txtT3"));
		h.put("txtT4", getParam("txtT4")== null?"0":getParam("txtT4"));
		h.put("txtT5", getParam("txtT5")== null?"0":getParam("txtT5"));
		h.put("txtT6", getParam("txtT6")== null?"0":getParam("txtT6"));
		h.put("txtT7", getParam("txtT7")== null?"0":getParam("txtT7"));
		h.put("txtT8", getParam("txtT8")== null?"0":getParam("txtT8"));
		h.put("txtT9", getParam("txtT9")== null?"0":getParam("txtT9"));
		h.put("txtT10", getParam("txtT10")== null?"0":getParam("txtT10"));
		h.put("txtT11", getParam("txtT11")== null?"0":getParam("txtT11"));
		h.put("txtT12", getParam("txtT12")== null?"0":getParam("txtT12"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		model.simpanSetupKPI(h);
		
	}//close simpanSetupKPI
	
	private void getAndSetPilihan() throws Exception{
		
		String id_negeri = getParam("socNegeri");
		
		//dropdown
		context.put("selectSuburusan",HTML.SelectSuburusanPPK("socSuburusan",Utils.parseLong(getParam("socSuburusan")),"style=width:325px"));
		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(id_negeri),null,"style=width:325px onChange=\"onchangeNegeri();\""));
		
		//dropdown by
		if(id_negeri!=""){
			context.put("selectUnit",HTML.SelectUnitJKPTGWithDaerahbyIdNegeri(id_negeri,"socUnit",null,"style=width:490px",null));
		}else{
			context.put("selectUnit",HTML.SelectUnitJKPTGWithDaerah("socUnit",null,"style=width:490px",null));
		}
		
		context.put("txdTarikhMula",getParam("txdTarikhMula"));
		context.put("txdTarikhAkhir",getParam("txdTarikhAkhir"));
		
	}//close getAndSetPilihan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getAndSetData() throws Exception{
		
		Vector getKeteranganSuburusan = new Vector();
		Vector getNamaPejabatJKPTG = new Vector();
		getKeteranganSuburusan.clear();
		getNamaPejabatJKPTG.clear();
		
		
		String nama_suburusan = "";
		getKeteranganSuburusan = model.getSuburusan(getParam("socSuburusan"));
		if(getKeteranganSuburusan.size()!=0){
			Hashtable ks = (Hashtable)getKeteranganSuburusan.get(0);
			nama_suburusan = (String)ks.get("NAMA_SUBURUSAN");
		}
		
		String nama_pejabat = "";
		getNamaPejabatJKPTG = model.getPejabatJKPTG(getParam("socUnit"));
		if(getNamaPejabatJKPTG.size()!=0){
			Hashtable pj = (Hashtable)getNamaPejabatJKPTG.get(0);
			nama_pejabat = (String)pj.get("NAMA_PEJABAT");
		}
		
		//dropdown
		context.put("selectSuburusan",HTML.SelectSuburusanPPK("socSuburusan",Utils.parseLong(getParam("socSuburusan")),"style=width:325px"));
		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(getParam("socNegeri")),null,"style=width:325px onChange=\"onchangeNegeri();\""));
		
		//dropdown by
		if(!getParam("socNegeri").isEmpty()){
			context.put("selectUnit",HTML.SelectUnitJKPTGWithDaerahbyIdNegeri(getParam("socNegeri"),"socUnit",Utils.parseLong(getParam("socUnit")),"style=width:490px",null));
		}else{
			context.put("selectUnit",HTML.SelectUnitJKPTGWithDaerah("socUnit",Utils.parseLong(getParam("socUnit")),"style=width:490px",null));
		}
		
		context.put("txdTarikhMula",getParam("txdTarikhMula"));
		context.put("txdTarikhAkhir",getParam("txdTarikhAkhir"));
		
		context.put("lblSuburusan",nama_suburusan);
		context.put("lblPejabatJKPTG",nama_pejabat);
		
	}//close getAndSetData
	
	private void resetValue() throws Exception{
		
		context.put("id_kpisasaran", "");
		context.put("txdTarikhMula","");
		context.put("txdTarikhAkhir","");
		context.put("lblSuburusan","");
		context.put("lblPejabatJKPTG","");
		
	}//close resetValue
	
	private void anchor() throws Exception{
		String ScreenLocation = getParam("ScreenLocation");
    	String CursorPoint = getParam("CursorPoint");    	
    	context.put("ScreenLocation", ScreenLocation);
    	context.put("CursorPoint", CursorPoint);
	}//close anchor
	
	private void UtilsItem() throws Exception{
		context.put("Util", new lebah.util.Util());
    	context.put("Utils", new ekptg.helpers.Utils());
	}//close UtilsItem
	
}//close class
