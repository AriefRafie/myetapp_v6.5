package ekptg.view.ppk;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8BicaraData;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8KeputusanPermohonanInternalData;
import ekptg.model.ppk.FrmPrmhnnSek8KptsanBicaraData;
import ekptg.model.ppk.FrmPrmhnnSek8Notis;
import ekptg.model.ppk.FrmRynSek8SemakPenerimaan;
import ekptg.model.ppk.FrmSenaraiFailKeputusanPermohonanInternalData;
import ekptg.model.ppk.FrmSenaraiFailKptsPerbcrnData;
/*
 * NORZAILY BINTI JASMI
 */
public class FrmSenaraiFailKeputusanPerbicaraan extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmSenaraiFailKeputusanPerbicaraan.class);
	FrmSenaraiFailKptsPerbcrnData logic2 = new FrmSenaraiFailKptsPerbcrnData();
	FrmPrmhnnSek8BicaraData logic3 = new FrmPrmhnnSek8BicaraData();
	FrmPrmhnnSek8KptsanBicaraData logic4 = new FrmPrmhnnSek8KptsanBicaraData();
	FrmPrmhnnSek8Notis logic5 = new FrmPrmhnnSek8Notis();
	FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	FrmPrmhnnSek8Notis modelNotis = new FrmPrmhnnSek8Notis();
	FrmRynSek8SemakPenerimaan model = new FrmRynSek8SemakPenerimaan();
	FrmPrmhnnSek8KeputusanPermohonanInternalData logicKeputusanPrmhnn = new FrmPrmhnnSek8KeputusanPermohonanInternalData();
	
 	String checkedTidakHadir = "";
	String checkedWarisTidakLengkap = "";
	String checkedMahkamahTinggi = "";
	String checkedBuktiTidakLengkap = "";
	String checkedMahkamahSyariah = "";
	String checkedPertelingkahanKolateral = "";
	String checkedSijilFaraid = "";
	String checkedSuratSetuju = "";
	String checkedSebabLain = "";
	String checkedMahkamahTinggiWasiat = "";
	String checkedTidakHadir3Kali = "";
	String checkedPermintaanPemohon = "";
	String checkedMahkamahTinggi2Juta = "";
	String checkedSebabLainBatal = "";
	String checkedMahkamahTinggiROTS = "";
	String checkedROTS = "";
	String checkedMS = "";
	String checkedPejMufti = "";
	String checkedBox = "";
	String checkedROTSMahkamahSyariah = "";
	String checkedROTSPejabatMufti = "";
	String checkedSelesai = "";
	String checkedTangguh = "";
	String checkedBatal = "";
	
	int maxTahun = 20; //arief add
	double bayaranDenda = 0.00; // arief add
	boolean flagDenda = true; //arief add
	int bilHari = 365; //arief add
	int bilHariSebenar = 370; //arief add
	int bezaTahun = 0; //arief add
	int tahunAktifDenda = 2020; // arief add
	int tahunDaftar = 2021; //arief add
	
	public String doTemplate2() throws Exception
    {
		HttpSession session = request.getSession();
		String doPost = (String)session.getAttribute("doPost");
    	String vm = "";
    	String userRole = String.valueOf(session.getAttribute("myrole"));
    	headerppk_baru_default();

    	Vector list = new Vector();
    	Vector listPemohon = new Vector();
    	Vector dataBayaran = new Vector();
    	Vector dataPerintah = new Vector();
    	Vector getrecord_perintah = new Vector();
    	Vector alamatTempatBicara = new Vector();
    	Vector dataNotis = new Vector();
    	Vector checkingNilaianAmanahRaya = new Vector();
    	Vector getrecord_perintahKolateral = new Vector();
    	Vector maklumatSerahanPenasihat = new Vector();
    	Vector maklumatSerahanMahkamah = new Vector();
    	Vector listMahkamah = new Vector();
    	Vector detailMahkamah = new Vector();
    	Vector listSelectedWaris = new Vector();
    	Vector MaklumatWaris = new Vector();
    	Vector getFailUpload = new Vector();
    	Vector Maklumatdokumen = new Vector();
    	Vector PerintahTangguh = new Vector();
    	Vector getrecord_infoperbicaraan = new Vector();
    	Vector checkingExistingTblppkbayaran = new Vector();
    	Vector PermohonanROTS = new Vector();
    	Vector PermohonanMufti = new Vector();
    	Vector senarai_waris = new Vector();
    	Vector getIdPerintah = new Vector();
    	Vector PermohonanROTSkeputusan = new Vector();
    	Vector PerintahTangguhROTS = new Vector();
    	Vector OBList = new Vector();
    	Vector PerintahTangguhMufti = new Vector();
    	Vector getExistDataBayaran = new Vector();
    	Vector listPenerimaNotis = new Vector();
    	Vector dataPemohon = new Vector();
    	Vector listSupportingDoc = null;
    	Vector flag5juta =  new Vector(); //arief add 5 juta
    	//boolean f5juta = false; //arief add 5 juta
    	
    	//hartaYangDikenakanBayaranPerintah = getParam("txtJumHartaDikenakanBayaranPerintah"); //arief add
    	
    	//bayaranFiSebenar = getParam("txtJumBayaranSebenar"); //arief add
    	
    	list.clear();
    	listPemohon.clear();
       	detailMahkamah.clear();
    	listMahkamah.clear();
    	maklumatSerahanPenasihat.clear();
    	maklumatSerahanMahkamah.clear();
    	listSelectedWaris.clear();
    	MaklumatWaris.clear();
    	getFailUpload.clear();
    	Maklumatdokumen.clear();
    	PerintahTangguh.clear();
    	getrecord_infoperbicaraan.clear();
    	PermohonanROTS.clear();
    	senarai_waris.clear();
    	getIdPerintah.clear();
    	PermohonanROTSkeputusan.clear();
    	PerintahTangguhROTS.clear();
    	OBList.clear();
    	PermohonanMufti.clear();
    	PerintahTangguhMufti.clear();

    	String action = getParam("action"); //* action ni hanya utk setup paging sahaja
    	String submit = getParam("command");
    	System.out.println("SUBMIT KP :: "+submit);
    	System.out.println("vm :: "+vm);
    	this.context.put("Util",new lebah.util.Util());// UNTUK FORMAT UTIL.DECIMAL (EX: 12,000.00)
    	String flagFromSenaraiFailSek8 = getParam("flagFromSenaraiFailSek8");
    	String flagFromSenaraiPermohonanSek8 = getParam("flagFromSenaraiPermohonanSek8");
    	String flagForView = getParam("flagForView");
    	String jenisDoc = "99205";
    	
		String usid="";
   		usid=(String)session.getAttribute("_ekptg_user_id");

   		String idpermohonan = getParam("idpermohonan");
	    this.context.put("idpermohonan",idpermohonan);
	    System.out.println("idpermohonan===XXX"+idpermohonan);
	  
	    String idsuburusanstatusfail = getParam("idsuburusanstatusfail");
	    this.context.put("idsuburusanstatusfail",idsuburusanstatusfail);

		Hashtable getstatusID = FrmPrmhnnSek8KptsanBicaraData.getListStatusID(idpermohonan);
		this.context.put("data", getstatusID);

	    //get info pemohon
		logic3.setListSemak(idpermohonan, usid);
		//hashtable maklumat header
		headerppk_baru(session,idpermohonan,"Y","","T");
		
		//arief add 5 juta
		FrmPrmhnnSek8KeputusanPermohonanInternalData.checkFlag5Juta(id);
		flag5juta = FrmPrmhnnSek8KeputusanPermohonanInternalData.getFlag5Juta();
		this.context.put("flag5juta", flag5juta);
		
		
		list = logic3.getListSemak();
		String idSimati = "";
		String idstatus = "";
		String idNegeriMhn = "";
		String idFail = "";
		String idPejabatJKPTG = "";
		String id_permohonansimati = "";
		String noFail = "";
		if ( list.size() != 0 ){
			Hashtable ls = (Hashtable) list.get(0);
			idSimati = (String)ls.get("idSimati");
			idstatus = ls.get("id_Status").toString();
    		idNegeriMhn = ls.get("pmidnegeri").toString();
    		idFail = ls.get("idFail").toString();
    		idPejabatJKPTG =  ls.get("id_pejabatjkptg").toString();
    		id_permohonansimati = ls.get("id_permohonansimati").toString();
    		noFail = ls.get("noFail").toString();
		}
			context.put("listSemak", list);

   	if	("papar_notis".equals(submit)){

 			//clear field
    		context.put("TEMPcheckedSelesai", "");
    		context.put("TEMPcheckedTangguh", "");
    		context.put("TEMPcheckedBatal", "");
    		
    		context.put("check_kiv","");
    		context.put("check_doc","");
    		context.put("valueKIV","");
    		
    		context.put("date_kiv","");
    		context.put("catatan_kiv","");

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if ( dataPerintah.size() != 0 ){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}
			this.context.put("dataPerintahView", dataPerintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		long idUnitPsk = 0;
    		String tarikh_bicara = "";
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			
    			
    			if(idn.get("id_unitpsk").toString()!=""){
        		idUnitPsk = Long.parseLong(idn.get("id_unitpsk").toString());
    			}
        		System.out.println("idUnitPsk = "+idUnitPsk);
        		tarikh_bicara = idn.get("tarikh_bicara").toString();
        		id_perbicaraan = (String) idn.get("id_perbicaraan");
    		}
    		String id = getParam("id_permohonan");
			Hashtable kp2a = new Hashtable();
			//Object id_permohonansimati1 = new Hashtable();
			kp2a = mainheader.getHeaderData(session, id, "Y", "", "T");
			//id_permohonansimati1 = kp2a.get("ID_PERMOHONANSIMATI");
			System.out.println("------------id_permohonansimati = "+id_permohonansimati);
			String id_permohonansimati2= String.valueOf(id_permohonansimati);
			
			// get senarai penerima notis
			modelNotis.setListPenerimaNotis(id_perbicaraan, id_permohonansimati2);
			listPenerimaNotis = modelNotis.getListPenerimaNotis();
			
			context.put("listPenerimaNotis_size1", listPenerimaNotis.size());
    		
			/*int dt5   = Integer.parseInt(tarikh_bicara.substring(0,2),10);
		   	int mon5  = Integer.parseInt(tarikh_bicara.substring(3,5),10);
		   	int yr5   = Integer.parseInt(tarikh_bicara.substring(6,10),10);
			@SuppressWarnings("deprecation")
			Date date5 = new Date(yr5, mon5, dt5);
			Date harini = new Date();*/
			Date harini = new Date();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date tarikh_bicara2 = df.parse(tarikh_bicara);
	        //Date sep_mohon = df.parse(harini);

	        if (harini.before(tarikh_bicara2) ) {
    			System.out.println("TARIKH BICARA LAMBAT LAGI!!!");
    			context.put("DoNotSave", "1");
	        }
			
			
    		this.context.put("tarikh_bicara",tarikh_bicara);
    		this.context.put("id_perbicaraan",id_perbicaraan);

			context.put("selectEditPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,null));

    		//call flag
    		context.put("mode", "add");
    		context.put("flag", "");
    		context.put("button", "kembali");
    		context.put("tarikh", "perintah");
    		
    		//get info pemohon
    		modelNotis.setListSemak(id,usid);
    		dataPemohon = modelNotis.getListSemak();
    		//headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_fail2 = "";
    		String id_pemohon = "";
    		String id_negeri = "";
    		
    		if(dataPemohon.size()!=0){
    			Hashtable x2 = (Hashtable) dataPemohon.get(0);
    			id_pemohon = x2.get("idPemohon").toString();
    			id_fail2 = x2.get("idFail").toString();
    			id_negeri = x2.get("pmidnegeri").toString();
    		}
    		context.put("id_negeri", id_negeri);

    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

    	}else if("papar_selesai".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if ( dataPerintah.size() != 0 ){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}

    		//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String id_perbicaraan = "";
    		String tarikh_bicara = "";
    		if (dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			id_perbicaraan = (String) idn.get("id_perbicaraan");
    			tarikh_bicara = idn.get("tarikh_bicara").toString();
    		}
    		this.context.put("id_perbicaraan",id_perbicaraan);
    		this.context.put("tarikh_bicara",tarikh_bicara);

    		//case butang Seterusnya
    		this.context.put("idPermohonanSimati", id_permohonansimati);
    		this.context.put("idPermohonan", idpermohonan);
    		//end

    			//get id_keputusanpermohonan - tiada id_perbicaraan
    			FrmPrmhnnSek8KptsanBicaraData.setViewPerintah(idpermohonan,id_perbicaraan);
    			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintah();
    			String id_perintah = "";
    			if (dataPerintah.size()!=0){
    				Hashtable v = (Hashtable) dataPerintah.get(0);
    				idkp = (String)v.get("id_keputusanpermohonan");
        			id_perintah = (String)v.get("id_perintah");
    			}
    			context.put("dataPerintahView", dataPerintah);
    			context.put("id_perintah", id_perintah);

    			FrmPrmhnnSek8KptsanBicaraData.setViewBicara(idpermohonan);
    			dataBayaran = FrmPrmhnnSek8KptsanBicaraData.getDataBayaran();
      	    	this.context.put("dataBicaraView", dataBayaran);

      	    	//* 07122009
      			//get jumlah_harta_tarikhmohon TBLPPKPERMOHONAN
      			Vector getJumlahBayaran = FrmPrmhnnSek8KptsanBicaraData.setJumlahBayaran(idpermohonan);
      			
      				//checking TBLPPKHA for NilaianAmanahRaya
      				checkingNilaianAmanahRaya = logic2.checkingNilaianAmanahRaya(id_permohonansimati);
      				double nilai_ha_tarikhmohon;
      				if(checkingNilaianAmanahRaya.size()!=0){

      					System.out.println("ADA NILAIAN HARTA AMANAH RAYA");
      					Hashtable nilaian = (Hashtable) checkingNilaianAmanahRaya.get(0);
      					nilai_ha_tarikhmohon = Double.parseDouble(nilaian.get("nilai_ha_tarikhmohon").toString());

      					if(getJumlahBayaran.size()!=0){
      						Hashtable a = (Hashtable) getJumlahBayaran.get(0);
      				   		double jumlah_harta_tarikhmohon;
      			    		double bayaranYuran;
      			    		double jumlahHartaDeductNilaianAmanahRaya;
      			    		jumlah_harta_tarikhmohon = Double.parseDouble(a.get("jumlah_harta_tarikhmohon").toString());
      			    		//jumlahHartaDeductNilaianAmanahRaya = jumlah_harta_tarikhmohon;
      			    		//ini yang asal
      			    		jumlahHartaDeductNilaianAmanahRaya = jumlah_harta_tarikhmohon - nilai_ha_tarikhmohon;
      			    		System.out.println("JUMLAH HARTA :: "+jumlah_harta_tarikhmohon);
      			    		System.out.println("JUMLAH HARTA YANG DIKENAKAN BAYARAN :: "+jumlahHartaDeductNilaianAmanahRaya);
      			    		
      			    		//arief add 
      			    		if ( (jumlahHartaDeductNilaianAmanahRaya > 0) && (jumlahHartaDeductNilaianAmanahRaya <= 1000) ) {
          						bayaranYuran = 10.00 ;
          					} else if ( (jumlahHartaDeductNilaianAmanahRaya > 1000) && (jumlahHartaDeductNilaianAmanahRaya <= 50000) ){
          						bayaranYuran = 30.00 ;
          					} else if ( (jumlahHartaDeductNilaianAmanahRaya > 50000) && (jumlahHartaDeductNilaianAmanahRaya <= 5000000) ) {
          						bayaranYuran = (0.002) * jumlahHartaDeductNilaianAmanahRaya ;
          						bayaranYuran = getBundaranBayaran(bayaranYuran);
          					} else {
          						bayaranYuran = (0.005) * jumlahHartaDeductNilaianAmanahRaya ;
          						bayaranYuran = getBundaranBayaran(bayaranYuran);
          					}
      			    			/**yang asal
      			    			 * 
      			    			 * if ( jumlahHartaDeductNilaianAmanahRaya <= 1000 ) {
      								bayaranYuran = 10.00 ;
      							} else if ( (jumlahHartaDeductNilaianAmanahRaya >= 1001) && (jumlahHartaDeductNilaianAmanahRaya <= 50000) ){
      								bayaranYuran = 30.00 ;
      							} else {
      								bayaranYuran = (0.2/100) * jumlahHartaDeductNilaianAmanahRaya ;
      								//ADD BY PEJE
      								bayaranYuran = getBundaranBayaran(bayaranYuran);
      							}**/
      			    		
      			    		//arief add
      			    		bezaTahun = tahunDaftar - tahunAktifDenda;
      			    		if (bilHariSebenar > bilHari) {
      			    			if (bezaTahun == 20){
      			    				bayaranDenda = 1000;
  	      						}else {
  	      							for (int bilTahun = 1; bilTahun <= bezaTahun; bilTahun++) {
  	      							bayaranDenda = bayaranDenda + 50;
  	      							}
  	      						}
  	      					}
  	      					bayaranYuran = bayaranYuran + bayaranDenda;
      			    		
      			    		//String t_mohon = logic_A.getTarikhMohon(idpermohonan);
  	      					//String tarikhAktifFlagDendaLewat = "14/07/2020";
      	      				//if (!t_mohon.equals("")) {
      	      					
      	      					
      	      					//DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      	      					//Date tar_mohon = df.parse(t_mohon);
      	      					//Date tar_aktif = df.parse(tarikhAktifFlagDendaLewat);
      	      					//String tarikhMohon = df.format(tar_mohon);
      	      					//myLogger.info("tarikh aktif = "+tar_aktif);
      	      					//myLogger.info("tarikh Mohon = "+tarikhMohon);
      	      					//if (tar_mohon.after(tar_aktif) || tar_mohon.equals(tar_aktif)) {
      	      						//if(tarikhMohon.substring(6,9).length() == 4 && parseInt(tarikhMohon.substring(6,9).length())>1000) {
      	      							//if () {
      	      								
      	      							//}
      	      							
      	      							//Vector bayaranDendaLewat = logic4.setDendaLewat(idpermohonan);
      	      						//}
      	      					//}
      	      					
      	      				//}
      			    		
  	      					this.context.put("txtJumDendaLewat", bayaranDenda);
      			    		this.context.put("txtJumBayaran", bayaranYuran);
      			    		this.context.put("txtJumHarta", jumlah_harta_tarikhmohon);
      						this.context.put("txtJumHartaDikenakanBayaranPerintah", jumlahHartaDeductNilaianAmanahRaya); //arief add
      						System.out.println("txtJumBayaran = "+bayaranYuran);
      						System.out.println("txtJumHarta = "+jumlah_harta_tarikhmohon);
      						System.out.println("txtJumHartaDikenakanBayaranPerintah = "+jumlahHartaDeductNilaianAmanahRaya);
      						System.out.println("txtJumDendaLewat = "+bayaranDenda);
      					}

      				}else{

      					System.out.println("TIADA NILAIAN HARTA AMANAH RAYA");
      					if(getJumlahBayaran.size()!=0){
      						Hashtable a = (Hashtable) getJumlahBayaran.get(0);
      				   		double jumlah_harta_tarikhmohon;
      			    		double bayaranYuran;
      			    		jumlah_harta_tarikhmohon = Double.parseDouble(a.get("jumlah_harta_tarikhmohon").toString());
      			    		//arief add 
      			    		if ( (jumlah_harta_tarikhmohon > 0) && (jumlah_harta_tarikhmohon <= 1000) ) {
          						bayaranYuran = 10.00 ;
          					} else if ( (jumlah_harta_tarikhmohon > 1000) && (jumlah_harta_tarikhmohon <= 50000) ){
          						bayaranYuran = 30.00 ;
          					} else if ( (jumlah_harta_tarikhmohon > 50000) && (jumlah_harta_tarikhmohon <= 5000000) ) {
          						bayaranYuran = (0.002) * jumlah_harta_tarikhmohon ;
          						bayaranYuran = getBundaranBayaran(bayaranYuran);
          					} else {
          						bayaranYuran = (0.005) * jumlah_harta_tarikhmohon ;
          						bayaranYuran = getBundaranBayaran(bayaranYuran);
          					}
      			    		
      			    		bezaTahun = tahunDaftar - tahunAktifDenda;
      			    		if (bilHariSebenar > bilHari) {
      			    			if (bezaTahun == 20){
      			    				bayaranDenda = 1000;
  	      						}else {
  	      							for (int bilTahun = 1; bilTahun <= bezaTahun; bilTahun++) {
  	      							bayaranDenda = bayaranDenda + 50;
  	      							}
  	      						}
  	      					}
  	      					bayaranYuran = bayaranYuran + bayaranDenda;
      			    			
      			    			/**yang asal
      			    			 * 
      			    			 * if ( jumlah_harta_tarikhmohon <= 1000 ) {
      								bayaranYuran = 10.00 ;
      							} else if ( (jumlah_harta_tarikhmohon >= 1001) && (jumlah_harta_tarikhmohon <= 50000) ){
      								bayaranYuran = 30.00 ;
      							} else {
      								bayaranYuran = (0.2/100) * jumlah_harta_tarikhmohon ;
      								//ADD BY PEJE
      								bayaranYuran = getBundaranBayaran(bayaranYuran);
      							}**/
      			    		this.context.put("txtJumBayaran", bayaranYuran);
      			    		this.context.put("txtJumHarta", jumlah_harta_tarikhmohon);
      			    		this.context.put("txtJumHartaDikenakanBayaranPerintah", jumlah_harta_tarikhmohon);//arief add
      			    		
      			    		System.out.println("txtJumBayaran = "+bayaranYuran);
      			    		System.out.println("txtJumHarta = "+jumlah_harta_tarikhmohon);
      			    		System.out.println("txtJumHartaDikenakanBayaranPerintah = "+jumlah_harta_tarikhmohon);
      			    		
      							
      					}
      				}
      				//*end
      				
      				//arief add bayaran denda lewat
      				/****/
      				
      				
      				
    			//get data TBLPPKPERBICARAAN
    			Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);
    			this.context.put("dataPerbicaraan", h);

    		    FrmPrmhnnSek8KptsanBicaraData.setInfoPerintahList(idpermohonan);
    		    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahViewList();
    		    String idUnitPsk = "";
    		    if ( getrecord_perintah.size() != 0 ){
        			Hashtable d = (Hashtable) getrecord_perintah.get(0);
           			String flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");
           			idUnitPsk = d.get("id_unitpsk").toString();
           			if ( idUnitPsk != ""){
           				context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",Utils.parseLong(idUnitPsk),"disabled"));
           			}else{
           				context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",null,"disabled"));
           			}

        			if (d.get("flag_jenis_keputusan").equals("0")){
        				setValueFlagJenisKeputusan("checked","","");
        			}
        			context.put("TEMPcheckedSelesai",checkedSelesai);
        			context.put("TEMPcheckedTangguh",checkedTangguh);
        			context.put("TEMPcheckedBatal",checkedBatal);

    		    }else{
    		    	setValueFlagJenisKeputusan("checked","","");
    		    	context.put("TEMPcheckedSelesai",checkedSelesai);
    		    	context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",null,"disabled"));
    		    }
    		    	this.context.put("dataPerintah", getrecord_perintah);

        		//call flag
        		context.put("mode", "view");
        		context.put("flag", "selesai");
        		context.put("button", "");
        		context.put("tarikh", "perintah");
        		context.put("listSemak", list);

        		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

		}else if("papar_tangguh".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
			String idkp = "";
			if (dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				this.context.put("dataPerintahView", dataPerintah);
				idkp = (String)v.get("id_keputusanpermohonan");
			}

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		Hashtable idn = (Hashtable) dataNotis.get(0);
    		String bil = idn.get("bil_bicara").toString();
    		String id_perbicaraan = "";
    		String tarikh_bicara = "";
    		if (dataNotis.size()!=0){
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
    			tarikh_bicara = idn.get("tarikh_bicara").toString();
    		}
    		this.context.put("id_perbicaraan",id_perbicaraan);
    		this.context.put("tarikh_bicara",tarikh_bicara);

			//get data TBLPPKPERBICARAAN
			Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);
			long idUnitPsk = 0;
			if (h.size()!=0){
				idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
				this.context.put("dataPerbicaraan", h);

			context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,"disabled"));

		    //get info via id_perbicaraan
    		FrmPrmhnnSek8KptsanBicaraData.setViewTangguhList(idpermohonan, id_perbicaraan);
		    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getViewTangguh();
		    if ( getrecord_perintah.size() != 0 ){
		    	String flag_jenis_keputusan = "";
			    String flag_tangguh = "";
			    String id_perintah = "";
			    Hashtable d = (Hashtable) getrecord_perintah.get(0);
		    	if (d.size()!=0){
		    		id_perintah = (String)d.get("id_perintah");
		    		flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");
					flag_tangguh = d.get("flag_tangguh").toString();
					System.out.println("ID PERINTAH 11111 :: "+id_perintah);
					if (d.get("flag_jenis_keputusan").equals("1")){
						setValueFlagJenisKeputusan("","checked","");
					}
					context.put("TEMPcheckedSelesai",checkedSelesai);
					context.put("TEMPcheckedTangguh",checkedTangguh);
					context.put("TEMPcheckedBatal",checkedBatal);

					if (d.get("flag_tangguh").equals("1")){
						setValue("checked","","","","","","","","");
					} else if (d.get("flag_tangguh").equals("2")){
						setValue("","checked","","","","","","","");
					} else if (d.get("flag_tangguh").equals("3")){
						setValue("","","checked","","","","","","");
					} else if (d.get("flag_tangguh").equals("4")){
						setValue("","","","checked","","","","","");
					} else if (d.get("flag_tangguh").equals("5")){
						setValue("","","","","checked","","","","");
					} else if (d.get("flag_tangguh").equals("6")){
						setValue("","","","","","checked","","","");
					} else if (d.get("flag_tangguh").equals("7")){
						setValue("","","","","","","checked","","");
					} else if (d.get("flag_tangguh").equals("8")){
						setValue("","","","","","","","checked","");
					} else if (d.get("flag_tangguh").equals("9")){
						setValue("","","","","","","","","checked");
					}
					context.put("TEMPcheckedTidakHadir",checkedTidakHadir);
					context.put("TEMPcheckedWarisTidakLengkap",checkedWarisTidakLengkap);
					context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggi);
					context.put("TEMPcheckedBuktiTidakLengkap",checkedBuktiTidakLengkap);
					context.put("TEMPcheckedMahkamahSyariah",checkedMahkamahSyariah);
					context.put("TEMPcheckedPertelingkahanKolateral",checkedPertelingkahanKolateral);
					context.put("TEMPcheckedSijilFaraid",checkedSijilFaraid);
					context.put("TEMPcheckedSuratSetuju",checkedSuratSetuju);
					context.put("TEMPcheckedSebabLain",checkedSebabLain);
				}
		    		context.put("dataPerintahView", getrecord_perintah);
		    		context.put("id_perintah", id_perintah);
		    }

    		//call flag
    		context.put("mode", "view");
    		context.put("flag", "tangguh");
    		context.put("button", "");
    		context.put("tarikh", "bicara");

    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

		}else if("papar_selesai_rujukanROTS".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
			String idkp = "";
			String id_perintah = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
				id_perintah = (String)v.get("id_perintah");
			}
			context.put("dataPerintahView", dataPerintah);
			context.put("id_perintah", id_perintah);

		    	//--data notis
				logic4.setListSemakWithData(idkp);
	    		dataNotis = logic4.getListSemakWithData();
	    		String id_perbicaraan = "";
	    		String tarikh_bicara = "";
	    		long idUnitPsk = 0;
	    		if (dataNotis.size()!=0){
	    			Hashtable idn = (Hashtable) dataNotis.get(0);
	        		id_perbicaraan = (String)idn.get("id_perbicaraan");
	        		tarikh_bicara = idn.get("tarikh_bicara").toString();
	        		idUnitPsk = Long.parseLong(idn.get("id_unitpsk").toString());
	    		}
	    		context.put("id_perbicaraan",id_perbicaraan);
	    		context.put("tarikh_bicara",tarikh_bicara);
	    		context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,"disabled"));

				//get data TBLPPKPERBICARAAN
				Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);
				context.put("dataPerbicaraan", h);

			    //get info via id_perbicaraan
	    		FrmPrmhnnSek8KptsanBicaraData.setViewTangguhList(idpermohonan, id_perbicaraan);
			    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getViewTangguh();
			    String flag_jenis_keputusan = "";
			    String flag_tangguh = "";
			    Hashtable d = new Hashtable();
			    if(getrecord_perintah.size()!=0){
				    d = (Hashtable) getrecord_perintah.get(0);
				    flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");
					flag_tangguh = d.get("flag_tangguh").toString();
				}
				this.context.put("dataPerintah", getrecord_perintah);

				if (d.get("flag_jenis_keputusan").equals("0")){
					setValueFlagJenisKeputusan("checked","","");
				} else if (d.get("flag_jenis_keputusan").equals("1")){
					setValueFlagJenisKeputusan("","checked","");
				} else if (d.get("flag_jenis_keputusan").equals("2")){
					setValueFlagJenisKeputusan("","","checked");
				}
				context.put("TEMPcheckedSelesai",checkedSelesai);
				context.put("TEMPcheckedTangguh",checkedTangguh);
				context.put("TEMPcheckedBatal",checkedBatal);

				if (d.get("flag_tangguh").equals("1")){
					setValue("checked","","","","","","","","");
				} else if (d.get("flag_tangguh").equals("2")){
					setValue("","checked","","","","","","","");
				} else if (d.get("flag_tangguh").equals("3")){
					setValue("","","checked","","","","","","");
				} else if (d.get("flag_tangguh").equals("4")){
					setValue("","","","checked","","","","","");
				} else if (d.get("flag_tangguh").equals("5")){
					setValue("","","","","checked","","","","");
				} else if (d.get("flag_tangguh").equals("6")){
					setValue("","","","","","checked","","","");
				} else if (d.get("flag_tangguh").equals("7")){
					setValue("","","","","","","checked","","");
				} else if (d.get("flag_tangguh").equals("8")){
					setValue("","","","","","","","checked","");
				} else if (d.get("flag_tangguh").equals("9")){
					setValue("","","","","","","","","checked");
				}
				context.put("TEMPcheckedTidakHadir",checkedTidakHadir);
				context.put("TEMPcheckedWarisTidakLengkap",checkedWarisTidakLengkap);
				context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggi);
				context.put("TEMPcheckedBuktiTidakLengkap",checkedBuktiTidakLengkap);
				context.put("TEMPcheckedMahkamahSyariah",checkedMahkamahSyariah);
				context.put("TEMPcheckedPertelingkahanKolateral",checkedPertelingkahanKolateral);
				context.put("TEMPcheckedSijilFaraid",checkedSijilFaraid);
				context.put("TEMPcheckedSuratSetuju",checkedSuratSetuju);
				context.put("TEMPcheckedSebabLain",checkedSebabLain);

				//call flag
	    		context.put("mode", "view");
	    		context.put("flag", "tangguh");
	    		context.put("button", "");
	    		context.put("tarikh", "bicara");

	    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

		}else if("papar_selesai_kolateral".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
			String idkp = "";
			String id_perintah = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
				id_perintah = (String)v.get("id_perintah");
			}
				context.put("dataPerintahView", dataPerintah);
				context.put("id_perintah", id_perintah);

		    	//--data notis
				logic4.setListSemakWithData(idkp);
	    		dataNotis = logic4.getListSemakWithData();
	    		String id_perbicaraan = "";
	    		String tarikh_bicara = "";
	    		if (dataNotis.size()!=0){
	    			Hashtable idn = (Hashtable) dataNotis.get(0);
	        		id_perbicaraan = (String)idn.get("id_perbicaraan");
	        		tarikh_bicara = idn.get("tarikh_bicara").toString();
	    		}
	    		context.put("id_perbicaraan",id_perbicaraan);
	    		context.put("tarikh_bicara",tarikh_bicara);

				//get data TBLPPKPERBICARAAN
				Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);
				long idUnitPsk = 0;
				if (h.size()!=0){
					idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
				}
				context.put("dataPerbicaraan", h);
				context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,"disabled"));

			    //get info via id_perbicaraan
	    		FrmPrmhnnSek8KptsanBicaraData.setViewTangguhList(idpermohonan, id_perbicaraan);
			    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getViewTangguh();
			    String flag_jenis_keputusan = "";
			    String flag_tangguh = "";
			    Hashtable d = new Hashtable();
			    if(getrecord_perintah.size()!=0){
				    d = (Hashtable) getrecord_perintah.get(0);
				    flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");
					flag_tangguh = d.get("flag_tangguh").toString();
				}
				this.context.put("dataPerintah", getrecord_perintah);

				if (d.get("flag_jenis_keputusan").equals("0")){
					setValueFlagJenisKeputusan("checked","","");
				} else if (d.get("flag_jenis_keputusan").equals("1")){
					setValueFlagJenisKeputusan("","checked","");
				} else if (d.get("flag_jenis_keputusan").equals("2")){
					setValueFlagJenisKeputusan("","","checked");
				}
				context.put("TEMPcheckedSelesai",checkedSelesai);
				context.put("TEMPcheckedTangguh",checkedTangguh);
				context.put("TEMPcheckedBatal",checkedBatal);

				if (d.get("flag_tangguh").equals("1")){
					setValue("checked","","","","","","","","");
				} else if (d.get("flag_tangguh").equals("2")){
					setValue("","checked","","","","","","","");
				} else if (d.get("flag_tangguh").equals("3")){
					setValue("","","checked","","","","","","");
				} else if (d.get("flag_tangguh").equals("4")){
					setValue("","","","checked","","","","","");
				} else if (d.get("flag_tangguh").equals("5")){
					setValue("","","","","checked","","","","");
				} else if (d.get("flag_tangguh").equals("6")){
					setValue("","","","","","checked","","","");
				} else if (d.get("flag_tangguh").equals("7")){
					setValue("","","","","","","checked","","");
				} else if (d.get("flag_tangguh").equals("8")){
					setValue("","","","","","","","checked","");
				} else if (d.get("flag_tangguh").equals("9")){
					setValue("","","","","","","","","checked");
				}
				context.put("TEMPcheckedTidakHadir",checkedTidakHadir);
				context.put("TEMPcheckedWarisTidakLengkap",checkedWarisTidakLengkap);
				context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggi);
				context.put("TEMPcheckedBuktiTidakLengkap",checkedBuktiTidakLengkap);
				context.put("TEMPcheckedMahkamahSyariah",checkedMahkamahSyariah);
				context.put("TEMPcheckedPertelingkahanKolateral",checkedPertelingkahanKolateral);
				context.put("TEMPcheckedSijilFaraid",checkedSijilFaraid);
				context.put("TEMPcheckedSuratSetuju",checkedSuratSetuju);
				context.put("TEMPcheckedSebabLain",checkedSebabLain);

    			//call flag
        		context.put("mode", "view");
        		context.put("flag", "tangguh");
        		context.put("button", "");
        		context.put("tarikh", "bicara");

        		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

		}else if("papar_selesai_rujukanMT".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
			String idkp = "";
			String id_perintah = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
				id_perintah = (String)v.get("id_perintah");
			}
				context.put("dataPerintahView", dataPerintah);
				context.put("id_perintah", id_perintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String id_perbicaraan = "";
    		String tarikh_bicara = "";
    		long idUnitPsk = 0;
    		if (dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
        		String bil = idn.get("bil_bicara").toString();
        		id_perbicaraan = (String)idn.get("id_perbicaraan");
        		tarikh_bicara = idn.get("tarikh_bicara").toString();
        		idUnitPsk = Long.parseLong(idn.get("id_unitpsk").toString());
    		}
    		context.put("id_perbicaraan",id_perbicaraan);
    		context.put("tarikh_bicara",tarikh_bicara);
    		context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,"disabled"));

			//get data TBLPPKPERBICARAAN
			Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);
			//if (h.size()!=0){
				//idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
			//}
			context.put("dataPerbicaraan", h);

		    //get info via id_perbicaraan
    		FrmPrmhnnSek8KptsanBicaraData.setViewTangguhList(idpermohonan, id_perbicaraan);
		    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getViewTangguh();
		    String flag_jenis_keputusan = "";
		    String flag_tangguh = "";
		    Hashtable d = new Hashtable();
		    if(getrecord_perintah.size()!=0){
			    d = (Hashtable) getrecord_perintah.get(0);
			    flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");
				flag_tangguh = d.get("flag_tangguh").toString();
			}
			this.context.put("dataPerintah", getrecord_perintah);

			if (d.get("flag_jenis_keputusan").equals("0")){
				setValueFlagJenisKeputusan("checked","","");
			} else if (d.get("flag_jenis_keputusan").equals("1")){
				setValueFlagJenisKeputusan("","checked","");
			} else if (d.get("flag_jenis_keputusan").equals("2")){
				setValueFlagJenisKeputusan("","","checked");
			}
			context.put("TEMPcheckedSelesai",checkedSelesai);
			context.put("TEMPcheckedTangguh",checkedTangguh);
			context.put("TEMPcheckedBatal",checkedBatal);

			if (d.get("flag_tangguh").equals("1")){
				setValue("checked","","","","","","","","");
			} else if (d.get("flag_tangguh").equals("2")){
				setValue("","checked","","","","","","","");
			} else if (d.get("flag_tangguh").equals("3")){
				setValue("","","checked","","","","","","");
			} else if (d.get("flag_tangguh").equals("4")){
				setValue("","","","checked","","","","","");
			} else if (d.get("flag_tangguh").equals("5")){
				setValue("","","","","checked","","","","");
			} else if (d.get("flag_tangguh").equals("6")){
				setValue("","","","","","checked","","","");
			} else if (d.get("flag_tangguh").equals("7")){
				setValue("","","","","","","checked","","");
			} else if (d.get("flag_tangguh").equals("8")){
				setValue("","","","","","","","checked","");
			} else if (d.get("flag_tangguh").equals("9")){
				setValue("","","","","","","","","checked");
			}
			context.put("TEMPcheckedTidakHadir",checkedTidakHadir);
			context.put("TEMPcheckedWarisTidakLengkap",checkedWarisTidakLengkap);
			context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggi);
			context.put("TEMPcheckedBuktiTidakLengkap",checkedBuktiTidakLengkap);
			context.put("TEMPcheckedMahkamahSyariah",checkedMahkamahSyariah);
			context.put("TEMPcheckedPertelingkahanKolateral",checkedPertelingkahanKolateral);
			context.put("TEMPcheckedSijilFaraid",checkedSijilFaraid);
			context.put("TEMPcheckedSuratSetuju",checkedSuratSetuju);
			context.put("TEMPcheckedSebabLain",checkedSebabLain);

			//call flag
    		context.put("mode", "view");
    		context.put("flag", "tangguh");
    		context.put("button", "");
    		context.put("tarikh", "bicara");

    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

		}else if("papar_batal".equals(submit)){

    			//get id_keputusanpermohonan
       			FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
    			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
    			String idkp = "";
    			if (dataPerintah.size()!=0){
    				Hashtable v = (Hashtable) dataPerintah.get(0);
    				idkp = (String)v.get("id_keputusanpermohonan");
    				this.context.put("id_perintah",v.get("id_perintah"));
    			}
    			this.context.put("dataPerintahView", dataPerintah);
    			

    	    	//--data notis 
    			logic4.setListSemakWithData(idkp);
        		dataNotis = logic4.getListSemakWithData();
        		String id_perbicaraan="";
        		String tarikh_bicara="";
        		if(dataNotis.size()!=0){
        			Hashtable idn = (Hashtable) dataNotis.get(0);
            		id_perbicaraan = idn.get("id_perbicaraan").toString();
            		tarikh_bicara = idn.get("tarikh_bicara").toString();
        		}
        		this.context.put("tarikh_bicara",tarikh_bicara);

    		    FrmPrmhnnSek8KptsanBicaraData.setInfoPerintahList(idpermohonan);
    		    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahViewList();
    		    Hashtable d = new Hashtable();
    		    long idUnitPskView =0;
    		    String flag_jenis_keputusan ="";
    		    String flag_batal="";
    		    if ( getrecord_perintah.size() != 0 ){
    		    	d = (Hashtable) getrecord_perintah.get(0);
        			idUnitPskView = Long.parseLong(d.get("id_unitpsk").toString());
           			flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");
           			flag_batal = d.get("flag_batal").toString();

        			if (d.get("flag_jenis_keputusan").equals("0")){
        				setValueFlagJenisKeputusan("checked","","");
        			} else if (d.get("flag_jenis_keputusan").equals("1")){
        				setValueFlagJenisKeputusan("","checked","");
        			} else if (d.get("flag_jenis_keputusan").equals("2")){
        				setValueFlagJenisKeputusan("","","checked");
        			}
        			context.put("TEMPcheckedSelesai",checkedSelesai);
        			context.put("TEMPcheckedTangguh",checkedTangguh);
        			context.put("TEMPcheckedBatal",checkedBatal);

           			if (d.get("flag_batal").equals("1")){
           				setValueFlagBatal("checked","","","","");
        			} else if (d.get("flag_batal").equals("2")){
        				setValueFlagBatal("","checked","","","");
        			} else if (d.get("flag_batal").equals("3")){
        				setValueFlagBatal("","","checked","","");
        			} else if (d.get("flag_batal").equals("4")){
        				setValueFlagBatal("","","","checked","");
        			} else if (d.get("flag_batal").equals("5")){
        				setValueFlagBatal("","","","","checked");
        			}
        			context.put("TEMPcheckedMahkamahTinggiWasiat",checkedMahkamahTinggiWasiat);
        			context.put("TEMPcheckedTidakHadir3Kali",checkedTidakHadir3Kali);
        			context.put("TEMPcheckedPermintaanPemohon",checkedPermintaanPemohon);
        			context.put("TEMPcheckedMahkamahTinggi2Juta",checkedMahkamahTinggi2Juta);
        			context.put("TEMPcheckedSebabLainBatal",checkedSebabLainBatal);
    		    }
        		this.context.put("dataPerintah", getrecord_perintah);
        		context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPskView,"disabled"));

        		//call flag
        		context.put("mode", "view");
        		context.put("flag", "batal");
        		context.put("button", "");
        		context.put("tarikh", "perintah");

        		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

		}else if("openMT".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}
			this.context.put("dataPerintahView", dataPerintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String id_perbicaraan = "";
    		String tarikh_bicara = "";
    		if (dataNotis.size()!=0){
    	   		Hashtable idn = (Hashtable) dataNotis.get(0);
        		tarikh_bicara = idn.get("tarikh_bicara").toString();
        		id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    		this.context.put("tarikh_bicara",tarikh_bicara);
    		this.context.put("id_perbicaraan",id_perbicaraan);

			//get data TBLPPKPERBICARAAN
			Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);
			long idUnitPsk = 0;
			if (h.size()!=0){
				idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
			this.context.put("dataPerbicaraan", h);

			//DROP DOWN
			context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",null,null,"style=width:305 onChange=\"doChangeidNegeriROTS();\" "));
   			context.put("selectBicara", HTML.SelectMahkamah("socTempatBicara", null, " disabled", " class=\"disabled\" style=width:340 onChange=\"doChangeidMahkamahROTS();\""));
			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn, "socPegawai", idUnitPsk," disabled", " class=\"disabled\" style=width:305"));

			//CHECKING SENARAI WARIS
			senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris(idpermohonan,getParam("id_permohonansimati_atheader"));
			if ( senarai_waris.size() != 0 ){
				Hashtable a  = (Hashtable) senarai_waris.get(0);
				this.context.put("dataListWaris", senarai_waris);
			}else{
				this.context.put("dataListWaris", "");
			}

    		//call flag
			context.put("tempcheckedmahkamahtinggi","checked");
			context.put("tempcheckedrots","");
		    context.put("viewMode", "no");
    		context.put("addMode", "yes");
    		context.put("editMode", "no");
    		context.put("flag", "MT");
    		context.put("button", "");
    		context.put("flagRujukan", "");

			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";


		}else if("openPejMufti".equals(submit)){

		    //clear content input
		    String txdTarikhRujukanAdd = "";
		    context.put("txdTarikhRujukanAdd", "");

			//get data TBLPPKPERBICARAAN
			Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);
			long idUnitPsk = 0;
			if (h.size()!=0){
				idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
			}

    		//* DROP DOWN
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"style=width:305 onChange=\"doChangeidNegeriPejabatMufti();\" "));
    		context.put("selectBandar", HTML.SelectBandar("socBandar",null," disabled"));
    		//context.put("selectPegawai", HTML.SelectPegawai("socPegawaiPengendali", null, " disabled"));
    		context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn, "socPegawaiPengendali", idUnitPsk," disabled", " class=\"disabled\" style=width:305"));

			//CHECKING SENARAI WARIS
			senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris(idpermohonan,getParam("id_permohonansimati_atheader"));
			if ( senarai_waris.size() != 0 ){
				Hashtable a  = (Hashtable) senarai_waris.get(0);
			}
				context.put("dataListWaris", senarai_waris);

    		//call flag
			context.put("tempcheckedmahkamahtinggi","");
			context.put("tempcheckedrots","checked");
			context.put("tempcheckedms","");
			context.put("tempcheckedpejmufti","checked");
			context.put("addMode", "yes");
			context.put("viewMode", "no");
			context.put("viewEditMode", "no");
			context.put("editMode", "no");
			context.put("flag", "ROTS");
			context.put("button", "");
    		context.put("flagRujukan", "rulerofthestate");
    		context.put("jenispejabat", "pejmufti");

			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("doChangeidNegeriPejabatMuftiUpdate".equals(submit)){

    		//* drop down
    		String idnegeri = getParam("socNegeri");
    		String idUnitPSK = getParam("socPegawaiPengendali");
    		context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri), "style=width:305 onChange=\"doChangeidNegeriPejabatMuftiUpdate();\" "));
    		context.put("selectBandar", HTML.SelectBandarByNegeri(idnegeri, "socBandar", null, "style=width:305 "));
    		context.put("selectPegawai", HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn, "socPegawaiPengendali", Utils.parseLong(idUnitPSK), "style=width:305 "));

    		//* get & post all field content
    		String txdTarikhRujukanAdd = getParam("txdTarikhRujukanAdd");
    		context.put("txdTarikhRujukanAdd", getParam("txdTarikhRujukanAdd"));

    		vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("doChangeidNegeriPejabatMufti".equals(submit)){

    		//* drop down
    		String idnegeri = getParam("socNegeri");
    		String idUnitPSK = getParam("socPegawaiPengendali");
    		context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri), "style=width:305 onChange=\"doChangeidNegeriPejabatMufti();\" "));
    		context.put("selectBandar", HTML.SelectBandarByNegeri(idnegeri, "socBandar", null, "style=width:305 "));
    		context.put("selectPegawai", HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn, "socPegawaiPengendali", Utils.parseLong(idUnitPSK), "style=width:305 "));

    		//* get & post all field content
    		String txdTarikhRujukanAdd = getParam("txdTarikhRujukanAdd");
    		context.put("txdTarikhRujukanAdd", getParam("txdTarikhRujukanAdd"));

    		vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("openFlagRujukan".equals(submit)){

			//call flag
			context.put("tempcheckedmahkamahtinggi","");
			context.put("tempcheckedrots","checked");
			context.put("tempcheckedms","");
			context.put("tempcheckedpejmufti","");
			context.put("addMode", "yes");
			context.put("viewMode", "no");
			context.put("viewEditMode", "no");
			context.put("editMode", "no");
			context.put("flag", "");
			context.put("button", "kembali");
    		context.put("flagRujukan", "rulerofthestate");

			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("openROTS".equals(submit)){

		    //* CLEAR TEXT INPUT
		    context.put("alamat1", "");
		    context.put("alamat2", "");
		    context.put("alamat3", "");
		    context.put("poskod", "");
		    context.put("notel", "");
		    context.put("nofax", "");
		    context.put("txdTarikhRujukanAdd", "");

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}
				context.put("dataPerintahView", dataPerintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("id_perbicaraan",id_perbicaraan);

			//GET INFO TBLPPKPERBICARAAN
			Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);
			long idUnitPsk = 0;
			if (h.size() != 0){
				idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
				context.put("dataPerbicaraan", h);

			//DROP DOWN
			context.put("selectNegeri",HTML.SelectNegeriByMahkamahSyariah("socNegeri",null,null,"style=width:305 onChange=\"doChangeidNegeriMS();\" "));
   			context.put("selectBicara", HTML.SelectMahkamah("socTempatBicara", null, " disabled", " class=\"disabled\" style=width:340 onChange=\"doChangeidMahkamahMS();\""));
			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",idUnitPsk," disabled", " class=\"disabled\" style=width:305"));

			//CHECKING SENARAI WARIS
			senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris(idpermohonan,getParam("id_permohonansimati_atheader"));
			if ( senarai_waris.size() != 0 ){
				Hashtable a  = (Hashtable) senarai_waris.get(0);
			}
				context.put("dataListWaris", senarai_waris);

    		//CALL FLAG
			context.put("tempcheckedmahkamahtinggi","");
			context.put("tempcheckedrots","checked");
			context.put("tempcheckedms","checked");
			context.put("tempcheckedpejmufti","");
		    context.put("viewMode", "no");
    		context.put("addMode", "yes");
    		context.put("editMode", "no");
    		context.put("flag", "ROTS");
    		context.put("button", "");
    		context.put("jenispejabat", "syariah");
    		context.put("action", "");

			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

    	}else if("skrin_kemaskiniMT".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
			String idkp = "";
			String id_perintah = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
				id_perintah = (String)v.get("id_perintah");
			}
				context.put("dataPerintahView", dataPerintah);
				context.put("id_perintah",id_perintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("id_perbicaraan",id_perbicaraan);

			//* GET MAKLUMAT TANGGUH
			Hashtable b = new Hashtable() ;
			PerintahTangguh = FrmPrmhnnSek8KptsanBicaraData.setPerintahTangguh(id_perbicaraan);
			long idUnitPsk = 0;
			String jenis_keluar_perintah = "";
			long idPejabat = 0;
			String idNegeri = "";

			if ( PerintahTangguh.size() != 0 ){
				b  = (Hashtable) PerintahTangguh.get(0);
				idUnitPsk = Long.parseLong(b.get("id_unitpsk").toString());
	    		jenis_keluar_perintah = b.get("jenis_keluar_perintah").toString();
	    		idPejabat = Long.parseLong(b.get("id_pejabatmahkamah").toString());
	    		idNegeri = b.get("id_negeri").toString();
			}
				context.put("infoPerintahTangguh", PerintahTangguh);
	    		context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",Utils.parseLong(idNegeri),"style=width:305 onChange=\"doChangeidNegeriUpdate();\" "));
	    		context.put("selectBicara",HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeri), "socTempatBicara", idPejabat,"disabled onChange=\"doChangeidMahkamahUpdate();\""));
	    		context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",idUnitPsk,"disabled"));

	    		context.put("addMode", "no");
	    		context.put("viewMode", "no");
	    		context.put("viewEditMode", "no");
	    		context.put("button", "no");
	    		context.put("editMode", "yes");
	    		context.put("action", "onChange");

			    vm = "app/ppk/frmPrmhnnSek8MahkamahTinggi.jsp";

    	}else if("Skrin_KemaskiniTangguh".equals(submit)){

			//GET ID_KEPUTUSAN PERMOHONAN
			FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String id_perbicaraan = "";
    		long idUnitPsk = 0;
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
    			idUnitPsk = Long.parseLong(idn.get("id_unitpsk").toString());
    		}
	    	context.put("id_perbicaraan",id_perbicaraan);

			//get data TBLPPKPERBICARAAN
			Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);

			if(h.size()!=0){
				//idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
			context.put("dataPerbicaraan", h);
			//context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,null));
			context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,"disabled"));
		    //get info via id_perbicaraan
    		FrmPrmhnnSek8KptsanBicaraData.setViewTangguhList(idpermohonan, id_perbicaraan);
		    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getViewTangguh();
		    if ( getrecord_perintah.size() != 0 ){
		    	String flag_jenis_keputusan = "";
			    String flag_tangguh = "";
			    String id_perintah = "";
			    Hashtable d = (Hashtable) getrecord_perintah.get(0);
		    	if (d.size()!=0){
		    		id_perintah = (String)d.get("id_perintah");
		    		flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");
					flag_tangguh = d.get("flag_tangguh").toString();
					System.out.println("ID PERINTAH 11111 :: "+id_perintah);
					if (d.get("flag_jenis_keputusan").equals("1")){
						setValueFlagJenisKeputusan("","checked","");
					}
					context.put("TEMPcheckedSelesai",checkedSelesai);
					context.put("TEMPcheckedTangguh",checkedTangguh);
					context.put("TEMPcheckedBatal",checkedBatal);

					if (d.get("flag_tangguh").equals("1")){
						setValue("checked","","","","","","","","");
					} else if (d.get("flag_tangguh").equals("2")){
						setValue("","checked","","","","","","","");
					} else if (d.get("flag_tangguh").equals("3")){
						setValue("","","checked","","","","","","");
					} else if (d.get("flag_tangguh").equals("4")){
						setValue("","","","checked","","","","","");
					} else if (d.get("flag_tangguh").equals("5")){
						setValue("","","","","checked","","","","");
					} else if (d.get("flag_tangguh").equals("6")){
						setValue("","","","","","checked","","","");
					} else if (d.get("flag_tangguh").equals("7")){
						setValue("","","","","","","checked","","");
					} else if (d.get("flag_tangguh").equals("8")){
						setValue("","","","","","","","checked","");
					} else if (d.get("flag_tangguh").equals("9")){
						setValue("","","","","","","","","checked");
					}
					context.put("TEMPcheckedTidakHadir",checkedTidakHadir);
					context.put("TEMPcheckedWarisTidakLengkap",checkedWarisTidakLengkap);
					context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggi);
					context.put("TEMPcheckedBuktiTidakLengkap",checkedBuktiTidakLengkap);
					context.put("TEMPcheckedMahkamahSyariah",checkedMahkamahSyariah);
					context.put("TEMPcheckedPertelingkahanKolateral",checkedPertelingkahanKolateral);
					context.put("TEMPcheckedSijilFaraid",checkedSijilFaraid);
					context.put("TEMPcheckedSuratSetuju",checkedSuratSetuju);
					context.put("TEMPcheckedSebabLain",checkedSebabLain);
				}
		    		context.put("dataPerintahView", getrecord_perintah);
		    		context.put("id_perintah", id_perintah);
		    }

			//CALL FLAG
    		context.put("mode", "edit");
    		context.put("flag", "tangguh");
    		context.put("button", "");
    		context.put("tarikh", "bicara");

    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

    	}else if("Skrin_Kemaskini".equals(submit)){		//* -- utk selesai kemaskini
    		
    		//alter dayah 23082010
    		
    		String id_perbicaraan = getParam("id_perbicaraan2");
    		System.out.println("id_perbicaraan--"+id_perbicaraan);
    		FrmPrmhnnSek8KptsanBicaraData.setInfoPerintah(idpermohonan,id_perbicaraan);
		    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahView();
		   
		    
		    Hashtable e = (Hashtable)getrecord_perintah.get(0);
		    
		    String tarikh_bicara = (String)e.get("tarikh_perintah");
		    
		    context.put("tarikh_bicara", tarikh_bicara);
		    System.out.println("tarikh_bicara--"+tarikh_bicara);
    		//

		    FrmPrmhnnSek8KptsanBicaraData.setInfoPerintahList(idpermohonan);
		    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahViewList();
		    String idUnitPskView = "";
		    String flag_jenis_keputusan = "";
		    System.out.println("size perintah :: "+getrecord_perintah.size());
		    if ( getrecord_perintah.size() != 0 ) {
				Hashtable d = (Hashtable) getrecord_perintah.get(0);
				idUnitPskView = d.get("id_unitpsk").toString();
				if ( idUnitPskView != "" ){
					//context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",Utils.parseLong(idUnitPskView),""));
					context.put("selectEditPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",Utils.parseLong(idUnitPskView),"class=mediumselect"));
				}else{
					context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",null,""));
				}

				flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");
				if (d.get("flag_jenis_keputusan").equals("0")){
					setValueFlagJenisKeputusan("checked","","");
				} else if (d.get("flag_jenis_keputusan").equals("1")){
					setValueFlagJenisKeputusan("","checked","");
				} else if (d.get("flag_jenis_keputusan").equals("2")){
					setValueFlagJenisKeputusan("","","checked");
				}
				context.put("TEMPcheckedSelesai",checkedSelesai);
				context.put("TEMPcheckedTangguh",checkedTangguh);
				context.put("TEMPcheckedBatal",checkedBatal);

       			if (d.get("flag_batal").equals("1")){
       				setValueFlagBatal("checked","","","","");
    			} else if (d.get("flag_batal").equals("2")){
    				setValueFlagBatal("","checked","","","");
    			} else if (d.get("flag_batal").equals("3")){
    				setValueFlagBatal("","","checked","","");
    			} else if (d.get("flag_batal").equals("4")){
    				setValueFlagBatal("","","","checked","");
    			} else if (d.get("flag_batal").equals("5")){
    				setValueFlagBatal("","","","","checked");
    			}
				context.put("TEMPcheckedMahkamahTinggiWasiat",checkedMahkamahTinggiWasiat);
				context.put("TEMPcheckedTidakHadir3Kali",checkedTidakHadir3Kali);
				context.put("TEMPcheckedPermintaanPemohon",checkedPermintaanPemohon);
				context.put("TEMPcheckedMahkamahTinggi2Juta",checkedMahkamahTinggi2Juta);
				context.put("TEMPcheckedSebabLainBatal",checkedSebabLainBatal);
		    }else{
		    	setValueFlagJenisKeputusan("checked","","");
		    	context.put("TEMPcheckedSelesai",checkedSelesai);
		    	context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",null,""));
		    }
		    	context.put("dataPerintah", getrecord_perintah);

		    //call flag
    		context.put("mode", "edit");
    		context.put("flag", "selesai");
    		context.put("button", "");
    		context.put("tarikh", "perintah");

		    vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";


   	}else if("Skrin_KemaskiniBatal".equals(submit)){

		//get id_keputusanpermohonan
		FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
		dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
		String idkp = "";
		if (dataPerintah.size()!=0){
			Hashtable v = (Hashtable) dataPerintah.get(0);
			idkp = (String)v.get("id_keputusanpermohonan");
		}
		this.context.put("dataPerintahView", dataPerintah);

    	//--data notis
		logic4.setListSemakWithData(idkp);
		dataNotis = logic4.getListSemakWithData();
		String id_perbicaraan="";
		String tarikh_bicara="";
		if(dataNotis.size()!=0){
			Hashtable idn = (Hashtable) dataNotis.get(0);
    		id_perbicaraan = idn.get("id_perbicaraan").toString();
    		tarikh_bicara = idn.get("tarikh_bicara").toString();
		}
		this.context.put("tarikh_bicara",tarikh_bicara);

	    FrmPrmhnnSek8KptsanBicaraData.setInfoPerintahList(idpermohonan);
	    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahViewList();
	    long idUnitPskView = 0;
	    String flag_jenis_keputusan ="";
	    String flag_batal="";
	    if ( getrecord_perintah.size() != 0 ){
	    	Hashtable d = (Hashtable) getrecord_perintah.get(0);
			idUnitPskView = Long.parseLong(d.get("id_unitpsk").toString());
   			flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");
   			flag_batal = d.get("flag_batal").toString();
			if (d.get("flag_jenis_keputusan").equals("0")){
				setValueFlagJenisKeputusan("checked","","");
			} else if (d.get("flag_jenis_keputusan").equals("1")){
				setValueFlagJenisKeputusan("","checked","");
			} else if (d.get("flag_jenis_keputusan").equals("2")){
				setValueFlagJenisKeputusan("","","checked");
			}
			context.put("TEMPcheckedSelesai",checkedSelesai);
			context.put("TEMPcheckedTangguh",checkedTangguh);
			context.put("TEMPcheckedBatal",checkedBatal);

   			if (d.get("flag_batal").equals("1")){
   				setValueFlagBatal("checked","","","","");
			} else if (d.get("flag_batal").equals("2")){
				setValueFlagBatal("","checked","","","");
			} else if (d.get("flag_batal").equals("3")){
				setValueFlagBatal("","","checked","","");
			} else if (d.get("flag_batal").equals("4")){
				setValueFlagBatal("","","","checked","");
			} else if (d.get("flag_batal").equals("5")){
				setValueFlagBatal("","","","","checked");
			}
			context.put("TEMPcheckedMahkamahTinggiWasiat",checkedMahkamahTinggiWasiat);
			context.put("TEMPcheckedTidakHadir3Kali",checkedTidakHadir3Kali);
			context.put("TEMPcheckedPermintaanPemohon",checkedPermintaanPemohon);
			context.put("TEMPcheckedMahkamahTinggi2Juta",checkedMahkamahTinggi2Juta);
			context.put("TEMPcheckedSebabLainBatal",checkedSebabLainBatal);
			//this.context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPskView,null));
			context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPskView,""));
	    }
			this.context.put("dataPerintah", getrecord_perintah);

    		//call flag
    		context.put("mode", "edit");
    		context.put("flag", "batal");
    		context.put("button", "");
    		context.put("tarikh", "perintah");

		    vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

    	}else if("kemaskini_selesai".equals(submit)){
    		
			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}
				//context.put("dataPerintahView", dataPerintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String tarikh_bicara = "";
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			tarikh_bicara = idn.get("tarikh_bicara").toString();
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("tarikh_bicara",tarikh_bicara);
    			context.put("id_perbicaraan",id_perbicaraan);

		    FrmPrmhnnSek8KptsanBicaraData.setInfoPerintahList(idpermohonan);
		    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahViewList();
		    long idUnitPskView = 0 ;
		    if(getrecord_perintah.size()!=0){
		    	 Hashtable d = (Hashtable) getrecord_perintah.get(0);
				 idUnitPskView = Long.parseLong(d.get("id_unitpsk").toString());
				 System.out.println("idUnitPskView = " +idUnitPskView);
		    }
    			context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPskView,""));

		    String id_bayaran_perintah = getParam("id_bayaran_perintah");
		    this.context.put("id_bayaran_perintah",id_bayaran_perintah);

			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			String id_perintah = "0";
			if ( getIdPerintah.size() != 0 ){
				Hashtable z = (Hashtable) getIdPerintah.get(0);
				id_perintah = z.get("id_perintah").toString();
			}
				context.put("getIdPerintah", getIdPerintah);
				context.put("id_perintah",id_perintah);
			
			System.out.println("IDBAYARAN PERINTAH >>> "+getParam("id_bayaran_perintah"));
			if ( id_bayaran_perintah.equals("0") ){		
			    context.put("id_bayaran_perintah",id_bayaran_perintah);
			    if (doPost.equals("true")){
			    	 tambahMaklumatPerintahEDIT (usid,idpermohonan);
			    }
			}

			System.out.println("IDBAYARAN PUSAKA >>> "+getParam("id_bayaran_pusaka"));
			String id_bayaran_pusaka = getParam("id_bayaran_pusaka");
//			if ( getParam("id_bayaran_pusaka") == "0" ){		
			if ( id_bayaran_pusaka.equals("0") ){		
			    context.put("id_bayaran_pusaka",id_bayaran_pusaka);
			    if (doPost.equals("true")){
			    	 tambahMaklumatPusakaEDIT (usid,idpermohonan);
			    }
			}

			System.out.println("IDBAYARAN BAITULMAL >>> "+getParam("id_bayaran_baitulmal"));
			String id_bayaran_baitulmal = getParam("id_bayaran_baitulmal");
//			if ( getParam("id_bayaran_baitulmal") == "0" ){
			if ( id_bayaran_baitulmal.equals("0") ){	
			    this.context.put("id_bayaran_baitulmal",id_bayaran_baitulmal);
			    if (doPost.equals("true")){
			    	tambahMaklumatBaitulMalEDIT (usid,idpermohonan);
			    }
			}

		    updateMaklumatSelesai(usid,idpermohonan,id_perintah,id_perbicaraan);

			//get info perbicaraan
    		FrmPrmhnnSek8KptsanBicaraData.setViewBicara(idpermohonan);
			dataBayaran = FrmPrmhnnSek8KptsanBicaraData.getDataBayaran();
  	    	context.put("dataBicaraView", dataBayaran);

			//get info perintah
//  	    	FrmPrmhnnSek8KptsanBicaraData.setViewPerintah(idpermohonan,id_perbicaraan);
//			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintah();

		    FrmPrmhnnSek8KptsanBicaraData.setInfoPerintah(idpermohonan,id_perbicaraan);
		    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahView();
		    String VIEWflag_jenis_keputusan = "";
		    String idUnitPsk = "";
		    if(getrecord_perintah.size()!=0){
		    	Hashtable d = (Hashtable) getrecord_perintah.get(0);
		    	idUnitPsk = d.get("id_unitpsk").toString();
				VIEWflag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");
				context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",Utils.parseLong(idUnitPsk),"disabled"));

				if (d.get("flag_jenis_keputusan").equals("0")){
			    	setValueFlagJenisKeputusan("checked","","");
	    		    context.put("viewSelesai", "yes");
	        		context.put("viewTangguh", "no");
	        		context.put("viewBatal", "no");
			    }
				context.put("TEMPcheckedSelesai",checkedSelesai);
				context.put("TEMPcheckedTangguh",checkedTangguh);
				context.put("TEMPcheckedBatal",checkedBatal);
				
				context.put("check_kiv",(String)d.get("check_kiv"));
				context.put("check_doc",(String)d.get("check_doc"));//ecah
				context.put("valueKIV",(String)d.get("valueKIV"));//ecah
				
				context.put("date_kiv",(String)d.get("date_kiv"));
				context.put("catatan_kiv",(String)d.get("catatan_kiv"));
				
				System.out.println("noFail:"+noFail);
				System.out.println("check_kiv===="+(String)d.get("check_kiv"));
				String check_kiv = (String)d.get("check_kiv");
				String date_kiv = (String)d.get("date_kiv");
				if (check_kiv.equals("1")){
					System.out.println("masukkkkk");
					logic3.insertActivityEvent(idUnitPsk,id_perbicaraan,noFail, date_kiv);
				}
				
		    }
				context.put("dataPerintahView", getrecord_perintah);

    		//call flag
    		context.put("mode", "view");
    		context.put("flag", "selesai");
    		context.put("button", "");
    		context.put("tarikh", "perintah");

		    vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

   	}else if("Simpan_Edit_Batal".equals(submit)){

    		//get id_keputusanpermohonan - tiada id_perbicaraan
    		FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
    		dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
    		String idkp = "";
    		if(dataPerintah.size()!=0){
    			Hashtable v = (Hashtable) dataPerintah.get(0);
    			idkp = (String)v.get("id_keputusanpermohonan");
    		}
    			context.put("dataPerintahView", dataPerintah);

        	//--data notis
    		logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("id_perbicaraan",id_perbicaraan);

			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			String id_perintah = "";
			if ( getIdPerintah.size() != 0 ){
				Hashtable z = (Hashtable) getIdPerintah.get(0);
				id_perintah = (String)z.get("id_perintah");

				 updateMaklumatBatal(usid,id_perintah,id_perbicaraan,idpermohonan);
			}
				context.put("id_perintah",id_perintah);

//			FrmPrmhnnSek8KptsanBicaraData.setViewPerintah(idpermohonan,id_perbicaraan);
//			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintah();
//			this.context.put("dataPerintahView", dataPerintah);

			getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.setInfoPerintah(idpermohonan,id_perbicaraan);
			long idUnitPskView = 0;
			String flag_jenis_keputusan = "";
			if ( getrecord_perintah.size() != 0){
				Hashtable d = (Hashtable) getrecord_perintah.get(0);
				idUnitPskView = Long.parseLong(d.get("id_unitpsk").toString());

				flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");
				setValueFlagJenisKeputusan("","","checked");
				context.put("TEMPcheckedBatal",checkedBatal);

       			if (d.get("flag_batal").equals("1")){
       				setValueFlagBatal("checked","","","","");
    			} else if (d.get("flag_batal").equals("2")){
    				setValueFlagBatal("","checked","","","");
    			} else if (d.get("flag_batal").equals("3")){
    				setValueFlagBatal("","","checked","","");
    			} else if (d.get("flag_batal").equals("4")){
    				setValueFlagBatal("","","","checked","");
    			} else if (d.get("flag_batal").equals("5")){
    				setValueFlagBatal("","","","","checked");
    			}
				context.put("TEMPcheckedMahkamahTinggiWasiat",checkedMahkamahTinggiWasiat);
				context.put("TEMPcheckedTidakHadir3Kali",checkedTidakHadir3Kali);
				context.put("TEMPcheckedPermintaanPemohon",checkedPermintaanPemohon);
				context.put("TEMPcheckedMahkamahTinggi2Juta",checkedMahkamahTinggi2Juta);
				context.put("TEMPcheckedSebabLainBatal",checkedSebabLainBatal);
			}
				context.put("dataPerintahView", getrecord_perintah);
				context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPskView,"disabled"));

    		//call flag
    		context.put("mode", "view");
    		context.put("flag", "batal");
    		context.put("button", "");
    		context.put("tarikh", "perintah");

    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

	}else if("Simpan_Edit_Tangguh".equals(submit)){

		//GET ID_KEPUTUSAN PERMOHONAN
		FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
		dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
		String idkp = "";
		if(dataPerintah.size()!=0){
			Hashtable v = (Hashtable) dataPerintah.get(0);
			idkp = (String)v.get("id_keputusanpermohonan");
		}

    	//--data notis
		logic4.setListSemakWithData(idkp);
		dataNotis = logic4.getListSemakWithData();
		String id_perbicaraan = "";
		if(dataNotis.size()!=0){
			Hashtable idn = (Hashtable) dataNotis.get(0);
			id_perbicaraan = (String)idn.get("id_perbicaraan");
		}
		context.put("id_perbicaraan",id_perbicaraan);

		getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
		String EDITsocPegawaiPengendali = "";
		String txdTarikhPerintahEDIT = "";
		String flag_tangguh = "";
		String id_perintah = "";
		if( getIdPerintah.size() != 0 ){
			Hashtable e = (Hashtable) getIdPerintah.get(0);
			id_perintah = (String)e.get("id_perintah");
			System.out.println("IDPERINTAH Simpan_Edit_Tangguh :: "+id_perintah);
		 	EDITsocPegawaiPengendali = getParam("EDITsocPegawaiPengendali");
		    txdTarikhPerintahEDIT = getParam("txdTarikhPerintahEDIT");
		    flag_tangguh = getParam("flag_tangguh");

		    updateMaklumatTangguh(idpermohonan,usid,id_perintah,id_perbicaraan);

		    if(idstatus.equals("44")){ // && !id_Status.equals("41")){
			    //edit_status_BicaraTangguh(idpermohonan,idsuburusanstatusfail,usid);
			    //edit_BicaraTangguhStatus_tblrujsuburusanstatusfail(idpermohonan,id_perbicaraan,
					//	usid,idsuburusanstatusfail,idFail);
			    //:::SUB
			    //ID_STATUS : 44
			    //ID_SUBURUSAN : 374
			    logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"44","374",idFail);
			    
			    
			    
		    }
		}
		context.put("id_perintah",id_perintah);

		//get id_keputusanpermohonan
		FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
		dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();

		if (dataPerintah.size()!=0){
			Hashtable v = (Hashtable) dataPerintah.get(0);
			idkp = (String)v.get("id_keputusanpermohonan");
		}
		this.context.put("dataPerintahView", dataPerintah);

    	//--data notis
		logic4.setListSemakWithData(idkp);
		dataNotis = logic4.getListSemakWithData();
		if(dataNotis.size()!=0){
			Hashtable idn = (Hashtable) dataNotis.get(0);
			id_perbicaraan = (String)idn.get("id_perbicaraan");
		}
			context.put("id_perbicaraan",id_perbicaraan);

	    FrmPrmhnnSek8KptsanBicaraData.setInfoPerintah(idpermohonan,id_perbicaraan);
	    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahView();
	    long idUnitPskView = 0;
	    String flag_jenis_keputusan = "";
	    if(getrecord_perintah.size()!=0){
	    	Hashtable d = (Hashtable) getrecord_perintah.get(0);
	    	if(!((String)d.get("id_unitpsk")).equals(""))
	    	{
	    	idUnitPskView = Long.parseLong((String)d.get("id_unitpsk"));
	    	}
			flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");

		    if (d.get("flag_jenis_keputusan").equals("1")){
		    	setValueFlagJenisKeputusan("", "checked", "");
		    }
			context.put("TEMPcheckedSelesai",checkedSelesai);
			context.put("TEMPcheckedTangguh",checkedTangguh);
			context.put("TEMPcheckedBatal",checkedBatal);

			if (d.get("flag_tangguh").equals("1")){
				setValue("checked","","","","","","","","");
			} else if (d.get("flag_tangguh").equals("2")){
				setValue("","checked","","","","","","","");
			} else if (d.get("flag_tangguh").equals("3")){
				setValue("","","checked","","","","","","");
			} else if (d.get("flag_tangguh").equals("4")){
				setValue("","","","checked","","","","","");
			} else if (d.get("flag_tangguh").equals("5")){
				setValue("","","","","checked","","","","");
			} else if (d.get("flag_tangguh").equals("6")){
				setValue("","","","","","checked","","","");
			} else if (d.get("flag_tangguh").equals("7")){
				setValue("","","","","","","checked","","");
			} else if (d.get("flag_tangguh").equals("8")){
				setValue("","","","","","","","checked","");
			} else if (d.get("flag_tangguh").equals("9")){
				setValue("","","","","","","","","checked");
			}
			context.put("TEMPcheckedTidakHadir",checkedTidakHadir);
			context.put("TEMPcheckedWarisTidakLengkap",checkedWarisTidakLengkap);
			context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggi);
			context.put("TEMPcheckedBuktiTidakLengkap",checkedBuktiTidakLengkap);
			context.put("TEMPcheckedMahkamahSyariah",checkedMahkamahSyariah);
			context.put("TEMPcheckedPertelingkahanKolateral",checkedPertelingkahanKolateral);
			context.put("TEMPcheckedSijilFaraid",checkedSijilFaraid);
			context.put("TEMPcheckedSuratSetuju",checkedSuratSetuju);
			context.put("TEMPcheckedSebabLain",checkedSebabLain);
	    }
	    	context.put("dataPerintahView", getrecord_perintah);
	    	context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",idUnitPskView,"disabled"));

		//call flag
		context.put("mode", "view");
		context.put("flag", "tangguh");
		context.put("button", "");
		context.put("tarikh", "bicara");
		context.put("listSemak", list);

	    vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

       	}else if("Simpan_Batal".equals(submit)){

			String id_perbicaraan = getParam("id_perbicaraan");
			myLogger.info("id_perbicaraan = "+id_perbicaraan);
			context.put("id_perbicaraan", getParam("id_perbicaraan"));

			//* GET INFO PERBICARAAN
			getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
			String id_fail = "";
			if ( getrecord_infoperbicaraan.size() != 0 ){
				Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
				id_fail = h.get("id_fail").toString();

				 //if (doPost.equals("true")) {
					add_MaklumatBatal(usid,idpermohonan,id_perbicaraan);

				    //edit_status_batal(idpermohonan,usid);
				    //edit_status_tblrujsuburusanstatusfail_batal(usid,idpermohonan,
					//		id_perbicaraan,idsuburusanstatusfail,idFail);
				    //:::SUB
				    //ID_STATUS : 47
				    //ID_SUBURUSAN : 398
				    
					logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"47","398",idFail);
					   
				    
				// }
			}
				context.put("dataPerbicaraan", getrecord_infoperbicaraan);
	    		String id_pejabat = getParam("socTempatBicara");
	    		String id_perintah = getParam("id_perintah");
	    		String socMahkamah = getParam("socMahkamah");
	    		String txtAlamatMahkamah1 = getParam("txtAlamatMahkamah1");
	    		String txtAlamatMahkamah2 = getParam("txtAlamatMahkamah2");
	    		String txtAlamatMahkamah3 = getParam("txtAlamatMahkamah3");
	    		String txtPoskodMahkamah = getParam("txtPoskodMahkamah");
			getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.setInfoPerintah(idpermohonan,id_perbicaraan);
			String flag_batal = "";
			long idUnitPskView = 0;
			if ( getrecord_perintah.size() != 0 ){
				Hashtable d = (Hashtable) getrecord_perintah.get(0);
				flag_batal = d.get("flag_batal").toString();
				id_perintah = d.get("id_perintah").toString();
				idUnitPskView = Long.parseLong(d.get("id_unitpsk").toString());

				setValueFlagJenisKeputusan("","","checked");
				context.put("TEMPcheckedBatal",checkedBatal);

				if (d.get("flag_batal").equals("1")){
					setValueFlagBatal("checked","","","","");
					detailMahkamah = model.getDetailMahkamah(socMahkamah);

            		if(detailMahkamah.size()!=0){

            			//MAHKAMAH

            	    	
            	    	String bandarM = "";
                		String alamatM1 = "";
            	    	String alamatM2 = "";
            	    	String alamatM3 = "";
            	    	String poskodM = "";
            	    	String idnegeriM = "";
            	    	String idbandarM = "";
            	    	String nama_pejabat = "";

            			Hashtable onc = (Hashtable) detailMahkamah.get(0);

            			nama_pejabat = onc.get("nama_pejabat").toString();
            			alamatM1 = onc.get("alamat1").toString();
            			alamatM2 = onc.get("alamat2").toString();
            			alamatM3 = onc.get("alamat3").toString();
            			poskodM = onc.get("poskod").toString();


					context.put("namaMahkamah",nama_pejabat);
					context.put("alamat1Mahkamah", alamatM1);
					context.put("alamat2Mahkamah", alamatM2);
					context.put("alamat3Mahkamah", alamatM3);
					context.put("txtPoskodMahkamah", poskodM);
					context.put("batalWasiat", "1");
					context.put("batal2juta", "");
				}
				} else if (d.get("flag_batal").equals("2")){
					setValueFlagBatal("","checked","","","");
				} else if (d.get("flag_batal").equals("3")){
					setValueFlagBatal("","","checked","","");
				} else if (d.get("flag_batal").equals("4")){
					setValueFlagBatal("","","","checked","");
					detailMahkamah = model.getDetailMahkamah(socMahkamah);

            		if(detailMahkamah.size()!=0){

            			//MAHKAMAH

            	    	
            	    	String bandarM = "";
                		String alamatM1 = "";
            	    	String alamatM2 = "";
            	    	String alamatM3 = "";
            	    	String poskodM = "";
            	    	String idnegeriM = "";
            	    	String idbandarM = "";
            	    	String nama_pejabat = "";

            			Hashtable onc = (Hashtable) detailMahkamah.get(0);

            			nama_pejabat = onc.get("nama_pejabat").toString();
            			alamatM1 = onc.get("alamat1").toString();
            			alamatM2 = onc.get("alamat2").toString();
            			alamatM3 = onc.get("alamat3").toString();
            			poskodM = onc.get("poskod").toString();


					context.put("namaMahkamah",nama_pejabat);
					context.put("alamat1Mahkamah", alamatM1);
					context.put("alamat2Mahkamah", alamatM2);
					context.put("alamat3Mahkamah", alamatM3);
					context.put("txtPoskodMahkamah", poskodM);
					context.put("batal2juta", "1");
					context.put("batalWasiat", "");
				}
				} else if (d.get("flag_batal").equals("5")){
					setValueFlagBatal("","","","","checked");
				}
				context.put("TEMPcheckedMahkamahTinggiWasiat",checkedMahkamahTinggiWasiat);
				context.put("TEMPcheckedTidakHadir3Kali",checkedTidakHadir3Kali);
				context.put("TEMPcheckedPermintaanPemohon",checkedPermintaanPemohon);
				context.put("TEMPcheckedMahkamahTinggi2Juta",checkedMahkamahTinggi2Juta);
				context.put("TEMPcheckedSebabLainBatal",checkedSebabLainBatal);
			}
				context.put("dataPerintahView", getrecord_perintah);
//				context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPskView,"disabled class=mediumselect"));
				context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPskView,"disabled"));
			    //get info pemohon
				logic3.setListSemak(idpermohonan, usid);
				list = logic3.getListSemak();
				//hashtable maklumat header
				headerppk_baru(session,idpermohonan,"Y","","T");
				if ( list.size() != 0 ){
					Hashtable ls = (Hashtable) list.get(0);
					idstatus = ls.get("id_Status").toString();
				}

			//CALL FLAG
    		context.put("mode", "view");
    		context.put("flag", "batal");
    		context.put("button", "");
    		context.put("tarikh", "perintah");
    		context.put("listSemak", list);
    		

    		
    		myLogger.info("flag_batal = "+flag_batal);
    		myLogger.info("id_perintah = "+id_perintah);
    		myLogger.info("socMahkamah = "+socMahkamah);
    		if ((flag_batal.equals("1")) || (flag_batal.equals("4")))  {
    			myLogger.info("flag_batal = "+flag_batal);
    			updateMahkamah(usid,id_perintah,id_perbicaraan);
    			
    		}
    		
    		

			vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";


		}else if("getSimpanMahkamah".equals(submit)){ //---->> MT FOR BATAL BICARA

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}
				context.put("dataPerintahView", dataPerintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
			dataNotis = logic4.getListSemakWithData();
			String id_perbicaraan = "";
			if(dataNotis.size()!=0){
				Hashtable idn = (Hashtable) dataNotis.get(0);
				id_perbicaraan = (String)idn.get("id_perbicaraan");
			}
				context.put("id_perbicaraan",id_perbicaraan);

		    String id_pejabat = getParam("id_pejabat");
		    String txdTarikhPerintahAdd = getParam("txdTarikhPerintahAdd");

			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			String id_perintah = "";
			if ( getIdPerintah.size() != 0 ){
				Hashtable e = (Hashtable) getIdPerintah.get(0);
				id_perintah = (String)e.get("id_perintah");
				 if (doPost.equals("true")) {
					 insertMahkamah_updateTblppkperintah(usid,id_perbicaraan,id_perintah); 	//UPDATE TBLPPKPERINTAH

					// edit_status_batalMT(idpermohonan,usid);
					// edit_status_tblrujsuburusanstatusfail_batalMT(idpermohonan,
					//    		idsuburusanstatusfail,usid,idFail);
					 //:::SUB
					 //ID_STATUS : 47
					 //ID_SUBURUSAN : 398
					 logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"47","398",idFail);
					   
					 
				 }
			}else {
				if (doPost.equals("true")) {
					insertMahkamah(usid,id_perbicaraan);		//INSERT TBLPPKPERINTAH INSERT TBLPPKBORANGJ

					//edit_status_batalMT(idpermohonan,usid);
				   // edit_status_tblrujsuburusanstatusfail_batalMT(idpermohonan,
				    	//	idsuburusanstatusfail,usid,idFail);
				  //:::SUB
					 //ID_STATUS : 47
					 //ID_SUBURUSAN : 398
				    logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"47","398",idFail);
					   
				    
				}
			}

		    PerintahTangguh = FrmPrmhnnSek8KptsanBicaraData.setPerintahTangguh(id_perbicaraan);
		    long idUnitPsk = 0;
		    String jenis_keluar_perintah ="";
		    long idPejabat = 0;
		    long idNegeri = 0;
		    if (PerintahTangguh.size()!=0){
				Hashtable b = (Hashtable) PerintahTangguh.get(0);
				idUnitPsk = Long.parseLong(b.get("id_unitpsk").toString());
	    		jenis_keluar_perintah = b.get("jenis_keluar_perintah").toString();
	    		idPejabat = Long.parseLong(b.get("id_pejabatmahkamah").toString());
	    		idNegeri = Long.parseLong(b.get("id_negeri").toString());
			}
		    	context.put("infoPerintahTangguh", PerintahTangguh);
	    		context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",idNegeri,"disabled style=width:305 "));
	    		context.put("selectBicara",HTML.SelectMahkamahByNegeri(idNegeri, "socTempatBicara", idPejabat,"disabled"));
	    		context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",idUnitPsk,"disabled"));


		    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.setInfoPerintah(idpermohonan,id_perbicaraan);
			long idUnitPskView = 0;
			String flag_jenis_keputusan = "";
			if ( getrecord_perintah.size() != 0){
			    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahView();
				Hashtable d = (Hashtable) getrecord_perintah.get(0);
				idUnitPskView = Long.parseLong(d.get("id_unitpsk").toString());
				flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");

				checkedSelesai = "";
				checkedTangguh = "";
				checkedBatal = "checked";

				context.put("TEMPcheckedSelesai",checkedSelesai);
				context.put("TEMPcheckedTangguh",checkedTangguh);
				context.put("TEMPcheckedBatal",checkedBatal);

       			if (d.get("flag_batal").equals("1")){
       				setValueFlagBatal("checked","","","","");
    			} else if (d.get("flag_batal").equals("2")){
    				setValueFlagBatal("","checked","","","");
    			} else if (d.get("flag_batal").equals("3")){
    				setValueFlagBatal("","","checked","","");
    			} else if (d.get("flag_batal").equals("4")){
    				setValueFlagBatal("","","","checked","");
    			} else if (d.get("flag_batal").equals("5")){
    				setValueFlagBatal("","","","","checked");
    			}
				context.put("TEMPcheckedMahkamahTinggiWasiat",checkedMahkamahTinggiWasiat);
				context.put("TEMPcheckedTidakHadir3Kali",checkedTidakHadir3Kali);
				context.put("TEMPcheckedPermintaanPemohon",checkedPermintaanPemohon);
				context.put("TEMPcheckedMahkamahTinggi2Juta",checkedMahkamahTinggi2Juta);
				context.put("TEMPcheckedSebabLainBatal",checkedSebabLainBatal);
			}
				context.put("dataPerintah", getrecord_perintah);
				context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",idUnitPskView,"disabled class=mediumselect"));


			//call flag
    		context.put("addMode", "no");
    		context.put("viewMode", "yes");
    		context.put("editMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("action", "onChange");
    		context.put("listSemak", list);

			vm = "app/ppk/frmPrmhnnSek8MahkamahTinggi.jsp";

	   	}else if("getMahkamahROTS".equals(submit)){

		    String idP = getParam("idPermohonan");
		    this.context.put("idPermohonan",idP);

			int idNegeri = getParamAsInteger("socNegeri");
			this.context.put("SocNegeri",idNegeri);

			FrmPrmhnnSek8KptsanBicaraData.setListNegeri();
			Vector listNegeri = FrmPrmhnnSek8KptsanBicaraData.getListNegeri();
			this.context.put("ListNegeri",listNegeri);

			FrmPrmhnnSek8KptsanBicaraData.setListDaerah(idNegeri);
			Vector listDaerah = FrmPrmhnnSek8KptsanBicaraData.getListDaerah();
			this.context.put("ListDaerah",listDaerah);

			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("getSimpanMahkamahROTS".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String tarikh_bicara = "";
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			tarikh_bicara = idn.get("tarikh_bicara").toString();
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("tarikh_bicara",tarikh_bicara);
    			context.put("id_perbicaraan",id_perbicaraan);

			//* GET INFO PERBICARAAN
			getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
			long id_unitpsk = 0;
			if ( getrecord_infoperbicaraan.size() != 0 ){
				Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
				id_unitpsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
				this.context.put("dataPerbicaraan", getrecord_infoperbicaraan);

				getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
				if ( getIdPerintah.size() != 0 ){
					Hashtable e = (Hashtable) getIdPerintah.get(0);
					String id_perintah = (String)e.get("id_perintah");
					if (doPost.equals("true")) {
						insertBorangJ_updateTblppkperintah(idpermohonan,id_perbicaraan,
								usid,id_perintah); 					//UPDATE TBLPPKPERINTAH INSERT TBLPPKBORANGJ
					}
				}else {
					if (doPost.equals("true")) {
						insertBorangJ(id_perbicaraan,usid);			//INSERT TBLPPKPERINTAH INSERT TBLPPKBORANGJ
					}
				}

				if (doPost.equals("true")) {
					 //edit_status_tangguh(idpermohonan,usid,idsuburusanstatusfail);
					// edit_status_tblrujsuburusanstatusfail_Tangguh(idpermohonan,usid,idFail,idsuburusanstatusfail);
					 //:::SUB
					 //ID_STATUS : 174
					 //ID_SUBURUSAN : 777
					 logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"174","777",idFail);
						
					 
				}

			//* GET ID_BORANGJ
			Hashtable getIdBorangJ = FrmPrmhnnSek8KptsanBicaraData.getIdBorangJ(id_perbicaraan);
			String idBorangJ = "";
			if (getIdBorangJ.size() > 0) {
			    idBorangJ = (String)getIdBorangJ.get("id_borangj");
			    if (doPost.equals("true")) {
			    	insertBorangJDTL(usid,idBorangJ);
			    }
			}

			PermohonanROTS = FrmPrmhnnSek8KptsanBicaraData.setROTS(idBorangJ,id_perbicaraan);
			String jenis_rujukan = "";
			if ( PermohonanROTS.size() != 0 ){
				Hashtable c = (Hashtable) PermohonanROTS.get(0);
				jenis_rujukan = c.get("jenis_rujukan").toString();
				if (c.get("jenis_rujukan").equals("1")){
					checkedMahkamahTinggiROTS = "checked";
					checkedROTS = "";
				} else if (c.get("jenis_rujukan").equals("2")){
					checkedMahkamahTinggiROTS = "";
					checkedROTS = "checked";
				}
				context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggiROTS);
				context.put("TEMPcheckedROTS",checkedROTS);
			}
				context.put("infoPermohonanROTS", PermohonanROTS);

			getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.setViewTangguhList(idpermohonan, id_perbicaraan);
			String flag_jenis_keputusan = "";
			String flag_tangguh = "";
			String id_perintah = "";
			if ( getrecord_perintah.size() != 0 ){
				Hashtable d = (Hashtable) getrecord_perintah.get(0);
				flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");
				flag_tangguh = (String)d.get("flag_tangguh");
				id_perintah = (String)d.get("id_perintah");

				//GET ID NEGERI & ID MAHKAMAH
				PerintahTangguhROTS = logic4.setTangguhROTS(id_perbicaraan,id_perintah,idBorangJ);
				String idNegeriMahkamah = "";
				String idMahkamah = "";
				String idUnitPSK = "";
				if(PerintahTangguhROTS.size()!=0){
					Hashtable b = (Hashtable) PerintahTangguhROTS.get(0);
					idNegeriMahkamah = b.get("id_negerimahkamah").toString();
					idMahkamah = b.get("id_mahkamah").toString();
					idUnitPSK = b.get("id_unitpsk").toString();
				}

				context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",Utils.parseLong(idNegeriMahkamah),null,"disabled class=\"disabled\" style=width:305 onChange=\"doChangeidNegeriROTS();\" "));
	   			context.put("selectBicara", HTML.SelectMahkamah("socTempatBicara", Utils.parseLong(idMahkamah), "disabled", " class=\"disabled\" style=width:340 onChange=\"doChangeidMahkamahROTS();\""));
				context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",Utils.parseLong(idUnitPSK),"disabled", " class=\"disabled\" style=width:305"));
			}
			context.put("getrecord_perintah",PerintahTangguhROTS);

			logic4.setMaklumatWaris(idBorangJ,id_perbicaraan,getParam("id_permohonansimati_atheader"));
			MaklumatWaris = logic4.getMaklumatWaris();
			if ( MaklumatWaris.size() != 0 ){
				this.context.put("SenaraiWaris",MaklumatWaris);
			}else {
				this.context.put("SenaraiWaris","");
			}

			//* GET UPLOAD FILE
			logic4.setUploadFail(idFail);
			getFailUpload = logic4.getFailUpload();
			if ( getFailUpload.size() != 0 ){
				this.context.put("SenaraiLampiran",getFailUpload);
			}else {
				this.context.put("SenaraiLampiran","");
			}

		    //REFRESH SEMULA IDSTATUS
			logic3.setListSemak(idpermohonan, usid);
			list = logic3.getListSemak();
			//hashtable maklumat header
			headerppk_baru(session,idpermohonan,"Y","","T");
			if ( list.size() != 0 ){
				Hashtable ls = (Hashtable) list.get(0);
				idstatus = ls.get("id_Status").toString();
			}

    		//CALL FLAG
		    context.put("viewMode", "yes");
    		context.put("addMode", "no");
    		context.put("editMode", "no");
    		context.put("flag", "MT");
    		context.put("idstatus", idstatus);

			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("skrin_kemaskiniMufti".equals(submit)){

			String id_perbicaraan = getParam("id_perbicaraan");
		    this.context.put("id_perbicaraan", id_perbicaraan);

			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			String id_perintah = "";
			if ( getIdPerintah.size() != 0 ){
				Hashtable e = (Hashtable) getIdPerintah.get(0);
				id_perintah = (String)e.get("id_perintah");

				//* GET ID_BORANGJ
				Hashtable getIdBorangJ = FrmPrmhnnSek8KptsanBicaraData.getIdBorangJ(id_perbicaraan);
				String idBorangJ = "";
				if (getIdBorangJ.size() > 0) {
				    idBorangJ = (String)getIdBorangJ.get("id_borangj");
				}

				//* get maklumat pejabat mufti
				PermohonanMufti = FrmPrmhnnSek8KptsanBicaraData.setMufti(idBorangJ,id_perbicaraan);
				String jenis_rujukan = "";
				String flag_rujukan = "";
				if ( PermohonanMufti.size() != 0 ){
					Hashtable c = (Hashtable) PermohonanMufti.get(0);
					jenis_rujukan = c.get("jenis_rujukan").toString();
					if (c.get("jenis_rujukan").equals("1")){
						checkedMahkamahTinggiROTS = "checked";
						checkedROTS = "";
					} else {
						checkedMahkamahTinggiROTS = "";
						checkedROTS = "checked";
					}
					context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggiROTS);
					context.put("TEMPcheckedROTS",checkedROTS);

					//* flag_rujukan 1: Mahkamah Syariah || 2: Pejabat Mufti
					flag_rujukan = c.get("flag_rujukan").toString();
					if( c.get("flag_rujukan").equals("1") ){
						checkedROTSMahkamahSyariah = "checked";
						checkedROTSPejabatMufti = "";
					}else{
						checkedROTSMahkamahSyariah = "";
						checkedROTSPejabatMufti = "checked";
					}
					context.put("tempcheckedms",checkedROTSMahkamahSyariah );
					context.put("tempcheckedpejmufti", checkedROTSPejabatMufti);

					//* DROP DOWN
					String idNegeriMufti = c.get("id_negeri").toString();
					String idBandar = c.get("id_bandar").toString();

					context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeriMufti)," style=width:305 onChange=\"doChangeidNegeriPejabatMuftiUpdate();\" "));
					context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(idBandar),"disabled style=width:305 "));

					//* GET MAKLUMAT TANGGUH
					PerintahTangguhMufti = FrmPrmhnnSek8KptsanBicaraData.setPerintahTangguhMufti(id_perbicaraan);
					long idUnitPsk = 0;
					if ( PerintahTangguhMufti.size() != 0 ){
						Hashtable d  = (Hashtable) PerintahTangguhMufti.get(0);
						idUnitPsk = Long.parseLong(d.get("id_unitpsk").toString());
					}
					context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",idUnitPsk," disabled", " class=\"disabled\" style=width:355"));
				}
					context.put("infoPermohonanMufti", PermohonanMufti);


				// get senarai selected waris + waris terdahulu
				FrmPrmhnnSek8KptsanBicaraData.setSelectedWaris(idBorangJ,id_perbicaraan,getParam("id_permohonansimati_atheader"));
				senarai_waris = FrmPrmhnnSek8KptsanBicaraData.getListSelectedWaris();
				if ( senarai_waris.size() != 0 ){
					Hashtable a = (Hashtable) senarai_waris.get(0);
					this.context.put("dataSelectedWaris", senarai_waris);
				}else{
					this.context.put("dataSelectedWaris", "");
				}
			}

			//* GET LIST DOKUMEN UPLOADED
			Maklumatdokumen = FrmPrmhnnSek8KptsanBicaraData.getMaklumatDokumen(idFail);
			if ( Maklumatdokumen.size() != 0 ){
				this.context.put("SenaraiLampiran",Maklumatdokumen);
			}else {
				this.context.put("SenaraiLampiran","");
			}

			//call flag
			context.put("editMode", "yes");
			context.put("viewMode", "no");
    		context.put("addMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("tab", "permohonan");
    		context.put("jenispejabat", "pejmufti");
    		context.put("flag", "ROTS");
    		context.put("action", "onChangeMufti");

			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";


		}else if("skrin_kemaskiniSyariah".equals(submit)){

			String id_perbicaraan = getParam("id_perbicaraan");
		    this.context.put("id_perbicaraan", id_perbicaraan);

			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			String id_perintah = "";
			if ( getIdPerintah.size() != 0 ){
				Hashtable e = (Hashtable) getIdPerintah.get(0);
				id_perintah = (String)e.get("id_perintah");
				//* GET ID_BORANGJ
				Hashtable getIdBorangJ = FrmPrmhnnSek8KptsanBicaraData.getIdBorangJ(id_perbicaraan);
				String idBorangJ = "";
				if (getIdBorangJ.size() > 0) {
				    idBorangJ = (String)getIdBorangJ.get("id_borangj");
				}

				PerintahTangguhROTS = logic4.setTangguhROTS(id_perbicaraan,id_perintah,idBorangJ);
				long idUnitPsk = 0;
				String namaPegawai = "";
				String jenis_rujukan = "";
				String idMahkamah = "";
				String idNegeriMahkamah = "";
				if(PerintahTangguhROTS.size() != 0  ){
					Hashtable b = (Hashtable) PerintahTangguhROTS.get(0);
					idUnitPsk = Long.parseLong(b.get("id_unitpsk").toString());
		    		namaPegawai = b.get("nama_pegawai").toString();
		    		jenis_rujukan = b.get("jenis_rujukan").toString();
		    		idMahkamah = b.get("id_mahkamah").toString();
		    		context.put("id_pejabat", idMahkamah);
		    		idNegeriMahkamah = b.get("id_negerimahkamah").toString();
					if (b.get("jenis_rujukan").equals("1")){
						checkedMahkamahTinggiROTS = "checked";
						checkedROTS = "";
					} else {
						checkedMahkamahTinggiROTS = "";
						checkedROTS = "checked";
					}
					
					context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggiROTS);
					context.put("TEMPcheckedROTS",checkedROTS);
				}
					context.put("infoPerintahTangguhROTS", PerintahTangguhROTS);

					//dropdown
					context.put("selectNegeri",HTML.SelectNegeriByMahkamahSyariah("socNegeri",Utils.parseLong(idNegeriMahkamah),"style=width:305 onChange=\"doChangeidNegeriMSupdate();\" "));
					//context.put("selectBicara", HTML.SelectMahkamahSyariahByNegeri(Utils.parseLong(idNegeriMahkamah),"socTempatBicara",Utils.parseLong(idMahkamah)," style=width:395 "));
					context.put("selectBicara",HTML.SelectMahkamahSyariahByNegeri(Utils.parseLong(idNegeriMahkamah),"socTempatBicara",Utils.parseLong(idMahkamah),"style=width:340 onChange=\"doChangeidMahkamahMSupdate();\" "));
		   			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",idUnitPsk,null,"style=width:305"));
					context.put("txtidnegeri", "idNegeriMahkamah");

				// get senarai selected waris + waris terdahulu
				FrmPrmhnnSek8KptsanBicaraData.setSelectedWaris(idBorangJ,id_perbicaraan,getParam("id_permohonansimati_atheader"));
				senarai_waris = FrmPrmhnnSek8KptsanBicaraData.getListSelectedWaris();
				if ( senarai_waris.size() != 0 ){
					Hashtable a = (Hashtable) senarai_waris.get(0);
					this.context.put("dataSelectedWaris", senarai_waris);
				}else{
					this.context.put("dataSelectedWaris", "");
				}
			}

			//* GET LIST DOKUMEN UPLOADED
			Maklumatdokumen = FrmPrmhnnSek8KptsanBicaraData.getMaklumatDokumen(idFail);
			if ( Maklumatdokumen.size() != 0 ){
				this.context.put("SenaraiLampiran",Maklumatdokumen);
			}else {
				this.context.put("SenaraiLampiran","");
			}

			//call flag
			context.put("editMode", "yes");
			context.put("viewMode", "no");
    		context.put("addMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("tab", "permohonan");
    		context.put("action", "onChange");

			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("getSimpanMufti".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String tarikh_bicara = "";
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
        		tarikh_bicara = idn.get("tarikh_bicara").toString();
        		id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("tarikh_bicara",tarikh_bicara);
    			context.put("id_perbicaraan",id_perbicaraan);

			//* GET INFO PERBICARAAN
			getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
			long id_unitpsk = 0;
			if ( getrecord_infoperbicaraan.size() != 0 ){
				Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
				id_unitpsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
				context.put("dataPerbicaraan", getrecord_infoperbicaraan);

			//* GET VALUE
			String txdTarikhRujukanAdd = getParam("txdTarikhRujukanAdd");
			String jenis_rujukan = getParam("jenis_rujukan");
			String idNegeriMufti = getParam("socNegeri");
			String txtnamapej = getParam("txtnamapej");
			String txtAlamat1 = getParam("txtAlamat1");
			String txtAlamat2 = getParam("txtAlamat2");
			String txtAlamat3 = getParam("txtAlamat3");
			String txtPoskod = getParam("txtPoskod");
			String idBandar = getParam("socBandar");
			String txtTelefon = getParam("txtTelefon");
			String txtfax = getParam("txtfax");
			String idUnitPSK = getParam("socPegawaiPengendali");
			String txtFaktaGuamanAdd = getParam("txtFaktaGuamanAdd");
			String txtPendapatAdd = getParam("txtPendapatAdd");

			//* GET ID PERINTAH
			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			if ( getIdPerintah.size() != 0 ){
				Hashtable e = (Hashtable) getIdPerintah.get(0);
				this.context.put("getIdPerintah", getIdPerintah);
				String id_perintah = (String)e.get("id_perintah");
			    if (doPost.equals("true")) {
					//UPDATE TBLPPKPERINTAH INSERT TBLPPKBORANGJ
			    	insertBorangJ_updateTblppkperintahMufti(usid,id_perintah,id_perbicaraan,txdTarikhRujukanAdd,idNegeriMufti,
							jenis_rujukan, txtnamapej,txtAlamat1,txtAlamat2,txtAlamat3,txtPoskod,idBandar,
							txtTelefon,txtfax,idUnitPSK,txtFaktaGuamanAdd,txtPendapatAdd);
			    }
			}else {
			    if (doPost.equals("true")) {
					 //INSERT TBLPPKPERINTAH INSERT TBLPPKBORANGJ
					 insertBorangJMufti( usid,id_perbicaraan,txdTarikhRujukanAdd,idNegeriMufti,
								jenis_rujukan, txtnamapej,txtAlamat1,txtAlamat2,txtAlamat3,txtPoskod,idBandar,
								txtTelefon,txtfax,idUnitPSK,txtFaktaGuamanAdd,txtPendapatAdd );
			    }
			}

		    if (doPost.equals("true")) {
				// edit_status_tblppkpermohonanMufti(idpermohonan, usid);
				// edit_status_tblrujsuburusanstatusfailMufti(idsuburusanstatusfail, usid, id_perbicaraan, idFail, idpermohonan);
				 //:::SUB
				 //ID_STATUS : 176
				 //ID_SUBURUSAN : 813
				 logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"176","813",idFail);
					
				 
		    }

			//* GET ID_BORANGJ
			Hashtable getIdBorangJ = FrmPrmhnnSek8KptsanBicaraData.getIdBorangJ(id_perbicaraan);
			String idBorangJ = "";
			if (getIdBorangJ.size() > 0) {
			    idBorangJ = (String)getIdBorangJ.get("id_borangj");
			    if (doPost.equals("true")) {
			    	insertBorangJDTL(usid,idBorangJ);
			    }
			}

			//* get maklumat pejabat mufti
			PermohonanMufti = FrmPrmhnnSek8KptsanBicaraData.setMufti(idBorangJ,id_perbicaraan);
			if ( PermohonanMufti.size() != 0 ){
				Hashtable c = (Hashtable) PermohonanMufti.get(0);
				jenis_rujukan = c.get("jenis_rujukan").toString();
				if (c.get("jenis_rujukan").equals("1")){
					checkedMahkamahTinggiROTS = "checked";
					checkedROTS = "";
				} else {
					checkedMahkamahTinggiROTS = "";
					checkedROTS = "checked";
				}
				context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggiROTS);
				context.put("TEMPcheckedROTS",checkedROTS);

				//* flag_rujukan 1: Mahkamah Syariah || 2: Pejabat Mufti
				String flag_rujukan = c.get("flag_rujukan").toString();
				if( c.get("flag_rujukan").equals("1") ){
					checkedROTSMahkamahSyariah = "checked";
					checkedROTSPejabatMufti = "";
				}else{
					checkedROTSMahkamahSyariah = "";
					checkedROTSPejabatMufti = "checked";
				}
				context.put("tempcheckedms",checkedROTSMahkamahSyariah );
				context.put("tempcheckedpejmufti", checkedROTSPejabatMufti);

				//* DROP DOWN
				idNegeriMufti = c.get("id_negeri").toString();
				idBandar = c.get("id_bandar").toString();

				context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeriMufti)," disabled ","class=\"disabled\" style=width:305 "));
				context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(idBandar),"disabled style=width:305 "));

				//* GET MAKLUMAT TANGGUH
				PerintahTangguhMufti = FrmPrmhnnSek8KptsanBicaraData.setPerintahTangguhMufti(id_perbicaraan);
				long idUnitPsk = 0;
				if ( PerintahTangguhMufti.size() != 0 ){
					Hashtable d  = (Hashtable) PerintahTangguhMufti.get(0);
					idUnitPsk = Long.parseLong(d.get("id_unitpsk").toString());
				}
				context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",idUnitPsk," disabled", " class=\"disabled\" style=width:355"));
			}
				context.put("infoPermohonanMufti", PermohonanMufti);

			//GET MAKLUMAT WARIS
			logic4.setMaklumatWaris(idBorangJ,id_perbicaraan,getParam("id_permohonansimati_atheader"));
			MaklumatWaris = logic4.getMaklumatWaris();
			if ( MaklumatWaris.size() != 0 ){
				this.context.put("SenaraiWaris",MaklumatWaris);
			}else {
				this.context.put("SenaraiWaris","");
			}

		    //GET REFRESH IDSTATUS
			logic3.setListSemak(idpermohonan, usid);
			list = logic3.getListSemak();
			//hashtable maklumat header
			headerppk_baru(session,idpermohonan,"Y","","T");
			if ( list.size() != 0 ){
				Hashtable ls = (Hashtable) list.get(0);
				idstatus = ls.get("id_Status").toString();
			}

    		//CALL FLAG
		    context.put("viewMode", "yes");
    		context.put("addMode", "no");
    		context.put("editMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("action", "onChangeMufti");
    		context.put("flag", "ROTS");
    		context.put("jenispejabat", "pejmufti");
    		context.put("listSemak", list);
    		context.put("id_status", idstatus);

			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("getSimpanMahkamahSyariahROTS".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String) v.get("id_keputusanpermohonan");
			}

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String tarikh_bicara = "";
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
        		tarikh_bicara = (String) idn.get("tarikh_bicara");
        		id_perbicaraan = (String) idn.get("id_perbicaraan");
    		}
    		context.put("tarikh_bicara",tarikh_bicara);
    		context.put("id_perbicaraan",id_perbicaraan);

			//* GET INFO PERBICARAAN
			getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
			long id_unitpsk = 0;
			if ( getrecord_infoperbicaraan.size() != 0 ){
				Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
				id_unitpsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
			context.put("dataPerbicaraan", getrecord_infoperbicaraan);

			//* GET VALUE
			String txdTarikhRujukanAdd = getParam("txdTarikhRujukanAdd");
			String jenis_rujukan = getParam("jenis_rujukan");
			String idNegeriMahkamah = getParam("socNegeri");
			String idMahkamah = getParam("socTempatBicara");
			String idUnitPSK = getParam("socPegawai");
			String txtFaktaGuamanAdd = getParam("txtFaktaGuamanAdd");
			String txtPendapatAdd = getParam("txtPendapatAdd");

			//* GET ID PERINTAH
			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			String id_perintah = "";
			if ( getIdPerintah.size() != 0 ){
				Hashtable e = (Hashtable) getIdPerintah.get(0);
				id_perintah = (String)e.get("id_perintah");
			    if (doPost.equals("true")) {
					//UPDATE TBLPPKPERINTAH INSERT TBLPPKBORANGJ
					insertBorangJ_updateTblppkperintahMS(usid,id_perintah,id_perbicaraan,txdTarikhRujukanAdd,idNegeriMahkamah,
							jenis_rujukan, idMahkamah,idUnitPSK, txtFaktaGuamanAdd,txtPendapatAdd);
			    }
			}else {

			    if (doPost.equals("true")) {
					 //INSERT TBLPPKPERINTAH INSERT TBLPPKBORANGJ
					 insertBorangJMS(usid,id_perbicaraan,txdTarikhRujukanAdd,idNegeriMahkamah,
								jenis_rujukan, idMahkamah, idUnitPSK, txtFaktaGuamanAdd,txtPendapatAdd);
			    }
			}

			if (doPost.equals("true")) {
			//	 edit_status_tblppkpermohonanMS(idpermohonan, usid);
			//	 edit_status_tblrujsuburusanstatusfailMS(idsuburusanstatusfail, usid, id_perbicaraan, idFail, idpermohonan);
				 //:::SUB
				 //ID_STATUS : 176
				 //ID_SUBURUSAN : 813
				 logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"176","813",idFail);
					
				 
			}

			//* GET ID_BORANGJ
			Hashtable getIdBorangJ = FrmPrmhnnSek8KptsanBicaraData.getIdBorangJ(id_perbicaraan);
			String idBorangJ = "";
			if (getIdBorangJ.size() > 0) {
			    idBorangJ = (String)getIdBorangJ.get("id_borangj");
			    System.out.println("idBorangJ :: "+idBorangJ);
			    if (doPost.equals("true")) {
			    	insertBorangJDTL(usid,idBorangJ);
			    }
			}

			//* get maklumat ruler of the state
			PermohonanROTS = FrmPrmhnnSek8KptsanBicaraData.setROTS(idBorangJ,id_perbicaraan);
			if ( PermohonanROTS.size() != 0 ){
				Hashtable c = (Hashtable) PermohonanROTS.get(0);
				jenis_rujukan = c.get("jenis_rujukan").toString();
				if (c.get("jenis_rujukan").equals("1")){
					checkedMahkamahTinggiROTS = "checked";
					checkedROTS = "";
				} else if (c.get("jenis_rujukan").equals("2")){
					checkedMahkamahTinggiROTS = "";
					checkedROTS = "checked";
				}
				context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggiROTS);
				context.put("TEMPcheckedROTS",checkedROTS);
			}
				context.put("infoPermohonanROTS", PermohonanROTS);

			getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.setViewTangguhList(idpermohonan, id_perbicaraan);
			String flag_jenis_keputusan = "";
			String flag_tangguh = "";
			if ( getrecord_perintah.size() != 0 ){
				Hashtable d = (Hashtable) getrecord_perintah.get(0);
				flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");
				flag_tangguh = d.get("flag_tangguh").toString();
				id_perintah = d.get("id_perintah").toString();

				//GET ID NEGERI & ID MAHKAMAH
				PerintahTangguhROTS = logic4.setTangguhROTS(id_perbicaraan,id_perintah,idBorangJ);
				if(PerintahTangguhROTS.size()!=0){
					Hashtable b = (Hashtable) PerintahTangguhROTS.get(0);
					idNegeriMahkamah= b.get("id_negerimahkamah").toString();
					idMahkamah = b.get("id_mahkamah").toString();
					idUnitPSK = b.get("id_unitpsk").toString();
				}
					context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Utils.parseLong(idNegeriMahkamah)," disabled ", "class=\"disabled\" style=width:305 onChange=\"doChangeidNegeriMS();\" "));
					context.put("selectBicara", HTML.SelectMahkamahSyariahByNegeri(Utils.parseLong(idNegeriMahkamah),"socTempatBicara", Utils.parseLong(idMahkamah), " disabled", " class=\"disabled\" style=width:355 onChange=\"doChangeidMahkamahMS();\""));
					context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",Utils.parseLong(idUnitPSK)," disabled", " class=\"disabled\" style=width:305"));
			}
				this.context.put("getrecord_perintah",PerintahTangguhROTS);

			//* GET MAKLUMAT WARIS
			logic4.setMaklumatWaris(idBorangJ,id_perbicaraan,getParam("id_permohonansimati_atheader"));
			MaklumatWaris = logic4.getMaklumatWaris();
			if ( MaklumatWaris.size() != 0 ){
				this.context.put("SenaraiWaris",MaklumatWaris);
			}else {
				this.context.put("SenaraiWaris","");
			}

		    //GET REFRESH IDSTATUS
			logic3.setListSemak(idpermohonan, usid);
			list = logic3.getListSemak();
			//hashtable maklumat header
			headerppk_baru(session,idpermohonan,"Y","","T");
			if ( list.size() != 0 ){
				Hashtable ls = (Hashtable) list.get(0);
				idstatus = ls.get("id_Status").toString();
			}

    		//CALL FLAG
		    context.put("viewMode", "yes");
    		context.put("addMode", "no");
    		context.put("editMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("action", "onChange");
    		context.put("tab", "permohonan");
    		context.put("tab", "keputusan");
    		context.put("listSemak", list);
    		context.put("id_status", idstatus);

			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("simpanEditMufti".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String) v.get("id_keputusanpermohonan");
			}

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String tarikh_bicara = "";
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
        		tarikh_bicara = (String) idn.get("tarikh_bicara");
        		id_perbicaraan = (String) idn.get("id_perbicaraan");
    		}
    			context.put("tarikh_bicara",tarikh_bicara);
    			context.put("id_perbicaraan",id_perbicaraan);

			//* GET INFO PERBICARAAN
			getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
			long id_unitpsk = 0;
			if ( getrecord_infoperbicaraan.size() != 0 ){
				Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
				id_unitpsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
				context.put("dataPerbicaraan", getrecord_infoperbicaraan);

			//* GET VALUE
			String txdTarikhRujukanAdd = getParam("txdTarikhRujukanAdd");
			String jenis_rujukan = getParam("jenis_rujukan");
			String idNegeriMufti = getParam("socNegeri");
			String txtnamapej = getParam("txtnamapej");
			String txtAlamat1 = getParam("txtAlamat1");
			String txtAlamat2 = getParam("txtAlamat2");
			String txtAlamat3 = getParam("txtAlamat3");
			String txtPoskod = getParam("txtPoskod");
			String txtTelefon = getParam("txtTelefon");
			String txtfax = getParam("txtfax");
			String idUnitPSK = getParam("socPegawaiPengendali");
			String txtFaktaGuamanAdd = getParam("txtFaktaGuamanAdd");
			String txtPendapatAdd = getParam("txtPendapatAdd");

			// get id_borangj
			Hashtable getIdBorangJ = FrmPrmhnnSek8KptsanBicaraData.getIdBorangJ(id_perbicaraan);
			String idBorangJ = "";
			if (getIdBorangJ.size() > 0) {
				idBorangJ = (String)getIdBorangJ.get("id_borangj");
						//* GET ID PERINTAH
						getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
						if ( getIdPerintah.size() != 0 ){
							Hashtable e = (Hashtable) getIdPerintah.get(0);
							this.context.put("getIdPerintah", getIdPerintah);
							String id_perintah = (String)e.get("id_perintah");
							//UPDATE TBLPPKPERINTAH UPDATE TBLPPKBORANGJ
							updateBorangJ_updateTblppkperintahMufti( idBorangJ,usid,id_perintah,id_perbicaraan,txdTarikhRujukanAdd,idNegeriMufti,
									jenis_rujukan, txtnamapej,txtAlamat1,txtAlamat2,txtAlamat3,txtPoskod,txtTelefon,txtfax,idUnitPSK, txtFaktaGuamanAdd,txtPendapatAdd );

						}else {
							this.context.put("getIdPerintah", "");
						}
			}else{
				idBorangJ = "";
			}

			//* GET ID_BORANGJ
			getIdBorangJ = FrmPrmhnnSek8KptsanBicaraData.getIdBorangJ(id_perbicaraan);
			idBorangJ = "";
			if (getIdBorangJ.size() > 0) {
			    idBorangJ = (String)getIdBorangJ.get("id_borangj");
			    if (doPost.equals("true")) {
			    	deleteWarisTerdahulu(usid,idBorangJ);
			    	updateMuftiborangJDTL(usid,id_perbicaraan,idBorangJ);
			    }
			}

			//* GET ID_PERINTAH
			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			String id_perintah = "";
			if ( getIdPerintah.size() != 0 ){
				Hashtable e = (Hashtable) getIdPerintah.get(0);
				this.context.put("getIdPerintah", getIdPerintah);
				id_perintah = (String)e.get("id_perintah");
			}else{
				this.context.put("getIdPerintah", "");
			}

			//* get maklumat pejabat mufti
			PermohonanMufti = FrmPrmhnnSek8KptsanBicaraData.setMufti(idBorangJ,id_perbicaraan);
			if ( PermohonanMufti.size() != 0 ){
				Hashtable c = (Hashtable) PermohonanMufti.get(0);
				this.context.put("infoPermohonanMufti", PermohonanMufti);

				jenis_rujukan = c.get("jenis_rujukan").toString();
				if (c.get("jenis_rujukan").equals("1")){
					checkedMahkamahTinggiROTS = "checked";
					checkedROTS = "";
				} else {
					checkedMahkamahTinggiROTS = "";
					checkedROTS = "checked";
				}
				context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggiROTS);
				context.put("TEMPcheckedROTS",checkedROTS);

				//* flag_rujukan 1: Mahkamah Syariah || 2: Pejabat Mufti
				String flag_rujukan = c.get("flag_rujukan").toString();
				if( c.get("flag_rujukan").equals("1") ){
					checkedROTSMahkamahSyariah = "checked";
					checkedROTSPejabatMufti = "";
				}else{
					checkedROTSMahkamahSyariah = "";
					checkedROTSPejabatMufti = "checked";
				}
				context.put("tempcheckedms",checkedROTSMahkamahSyariah );
				context.put("tempcheckedpejmufti", checkedROTSPejabatMufti);

				//drop down
				String idNegeri = c.get("id_negeri").toString();
				String idBandar = c.get("id_bandar").toString();
				context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"disabled style=width:305 "));
				context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(idBandar),"disabled style=width:305 "));

			}else{
				this.context.put("infoPermohonanMufti", "");
			}

				//* GET MAKLUMAT TANGGUH
				PerintahTangguhMufti = FrmPrmhnnSek8KptsanBicaraData.setPerintahTangguhMufti(id_perbicaraan);
				long idUnitPsk = 0;
				if ( PerintahTangguhMufti.size() != 0 ){
					Hashtable d  = (Hashtable) PerintahTangguhMufti.get(0);
					idUnitPsk = Long.parseLong(d.get("id_unitpsk").toString());
				}
				context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",idUnitPsk," disabled", " class=\"disabled\" style=width:355"));

			//* get maklumat waris
			logic4.setMaklumatWaris(idBorangJ,id_perbicaraan,getParam("id_permohonansimati_atheader"));
			MaklumatWaris = logic4.getMaklumatWaris();
			if ( MaklumatWaris.size() != 0 ){
				this.context.put("SenaraiWaris",MaklumatWaris);
			}else {
				this.context.put("SenaraiWaris","");
			}

    		//call flag
		    context.put("viewMode", "yes");
    		context.put("addMode", "no");
    		context.put("editMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("jenispejabat", "pejmufti");
    		context.put("action", "onChangeMufti");
    		context.put("flag", "ROTS");

			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";


		}else if("simpanSyariah".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String) v.get("id_keputusanpermohonan");
			}

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String tarikh_bicara = "";
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			tarikh_bicara = (String) idn.get("tarikh_bicara");
    			id_perbicaraan = (String) idn.get("id_perbicaraan");
    		}
    			context.put("tarikh_bicara",tarikh_bicara);
    			context.put("id_perbicaraan",id_perbicaraan);

			//* GET INFO PERBICARAAN
			getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
			long id_unitpsk = 0;
			if ( getrecord_infoperbicaraan.size() != 0 ){
				Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);

				id_unitpsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
				context.put("dataPerbicaraan", getrecord_infoperbicaraan);

			//* GET VALUE
			String txdTarikhRujukanAdd = getParam("txdTarikhRujukanAdd");
			String jenis_rujukan = getParam("jenis_rujukan");
			String idNegeriMahkamah = getParam("socNegeri");
			String idMahkamah = getParam("socTempatBicara");
			String idUnitPSK = getParam("socPegawai");
			String txtFaktaGuamanAdd = getParam("txtFaktaGuamanAdd");
			String txtPendapatAdd = getParam("txtPendapatAdd");

			// get id_borangj
			Hashtable getIdBorangJ = FrmPrmhnnSek8KptsanBicaraData.getIdBorangJ(id_perbicaraan);
			String idBorangJ = "";
			if (getIdBorangJ.size() > 0) {
				idBorangJ = (String)getIdBorangJ.get("id_borangj");
						//* GET ID PERINTAH
						getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
						if ( getIdPerintah.size() != 0 ){
							Hashtable e = (Hashtable) getIdPerintah.get(0);
							String id_perintah = (String)e.get("id_perintah");
							//UPDATE TBLPPKPERINTAH UPDATE TBLPPKBORANGJ
							updateBorangJ_updateTblppkperintahMS(idBorangJ,usid,id_perintah,id_perbicaraan,txdTarikhRujukanAdd,idNegeriMahkamah,
									jenis_rujukan, idMahkamah,idUnitPSK, txtFaktaGuamanAdd,txtPendapatAdd);
						}
			}

			//* GET ID_BORANGJ
			getIdBorangJ = FrmPrmhnnSek8KptsanBicaraData.getIdBorangJ(id_perbicaraan);
			idBorangJ = "";
			if (getIdBorangJ.size() > 0) {
			    idBorangJ = (String)getIdBorangJ.get("id_borangj");
			    if (doPost.equals("true")) {
			    	deleteWarisTerdahulu(usid,idBorangJ);
			    	updateMSborangJDTL(usid,idBorangJ);
			    }
			}

			if (doPost.equals("true")){
			//	edit_status_tblppkpermohonanMS(idpermohonan, usid);
			//	edit_status_tblrujsuburusanstatusfailMS(idsuburusanstatusfail, usid,
			//    		id_perbicaraan, idFail, idpermohonan);
				 //:::SUB
				 //ID_STATUS : 176
				 //ID_SUBURUSAN : 813
				logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"176","813",idFail);
				
				
			}

			//* GET ID_PERINTAH
			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			String id_perintah = "";
			if ( getIdPerintah.size() != 0 ){
				Hashtable e = (Hashtable) getIdPerintah.get(0);
				id_perintah = (String)e.get("id_perintah");
			}

			//* GET MAKLUMAT TANGGUH ROTS
			PerintahTangguhROTS = logic4.setTangguhROTS(id_perbicaraan,id_perintah,idBorangJ);
			long idUnitPsk = 0;
			if ( PerintahTangguhROTS.size() != 0){
				Hashtable b = (Hashtable) PerintahTangguhROTS.get(0);

				idUnitPsk = Long.parseLong(b.get("id_unitpsk").toString());
	    		jenis_rujukan = b.get("jenis_rujukan").toString();
	    		idNegeriMahkamah = b.get("id_negerimahkamah").toString();
	    		idMahkamah = b.get("id_mahkamah").toString();

			}
				context.put("infoPerintahTangguhROTS", PerintahTangguhROTS);
	    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeriMahkamah),"disabled style=width:305 "));
				context.put("selectBicara", HTML.SelectMahkamahSyariahByNegeri(Utils.parseLong(idNegeriMahkamah),"socTempatBicara",Utils.parseLong(idMahkamah),"disabled style=width:375 "));
				context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",idUnitPsk," disabled", " class=\"disabled\" style=width:305"));
				context.put("txtidnegeri", idNegeriMahkamah);

			//* get maklumat ruler of the state
			PermohonanROTS = FrmPrmhnnSek8KptsanBicaraData.setROTS(idBorangJ,id_perbicaraan);
			if ( PermohonanROTS.size() != 0 ){
				Hashtable c = (Hashtable) PermohonanROTS.get(0);
				jenis_rujukan = c.get("jenis_rujukan").toString();
				if (c.get("jenis_rujukan").equals("1")){
					checkedMahkamahTinggiROTS = "checked";
					checkedROTS = "";
				} else if (c.get("jenis_rujukan").equals("2")){
					checkedMahkamahTinggiROTS = "";
					checkedROTS = "checked";
				}
				context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggiROTS);
				context.put("TEMPcheckedROTS",checkedROTS);
			}
				context.put("infoPermohonanROTS", PermohonanROTS);

			//* get maklumat waris
			logic4.setMaklumatWaris(idBorangJ,id_perbicaraan,getParam("id_permohonansimati_atheader"));
			MaklumatWaris = logic4.getMaklumatWaris();
			if ( MaklumatWaris.size() != 0 ){
				this.context.put("SenaraiWaris",MaklumatWaris);
			}else {
				this.context.put("SenaraiWaris","");
			}

    		//call flag
		    context.put("viewMode", "yes");
    		context.put("addMode", "no");
    		context.put("editMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("action", "onChange");

			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("HapusMufti".equals(submit)){

		    String id_borangj = getParam("id_borangj");

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "" ;
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String) v.get("id_keputusanpermohonan");
			}
				context.put("dataPerintahView", dataPerintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String tarikh_bicara = "";
    		String id_perbicaraan  = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			tarikh_bicara = (String) idn.get("tarikh_bicara");
    			id_perbicaraan = (String) idn.get("id_perbicaraan");
    		}
    			context.put("tarikh_bicara",tarikh_bicara);
    			context.put("id_perbicaraan",id_perbicaraan);

		    if (doPost.equals("true")) {
		    	deleteMaklumatMufti(id_borangj, id_perbicaraan);
		    }

			getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
			long idUnitPsk = 0;
			if ( getrecord_infoperbicaraan.size() != 0 ){
				Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
				idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
				context.put("dataPerbicaraan", getrecord_infoperbicaraan);

			//* CHECKING LIST SENARAI WARIS
			senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris(idpermohonan,getParam("id_permohonansimati_atheader"));
			if ( senarai_waris.size() != 0 ){
				Hashtable a  = (Hashtable) senarai_waris.get(0);
				this.context.put("dataListWaris", senarai_waris);
			}else{
				this.context.put("dataListWaris", "");
			}

			context.put("viewMode", "no");
    		context.put("addMode", "yes");
    		context.put("editMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("tap", "permohonan");
    		context.put("button", "kembali");
    		context.put("flag", "");
    		context.put("tempcheckedmahkamahtinggi", "");
    		context.put("tempcheckedROTS", "");
    		context.put("flagRujukan", "");

    		vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";


		}else if("HapusSyariah".equals(submit)){

		    String id_borangj = getParam("id_borangj");

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String) v.get("id_keputusanpermohonan");
			}
				context.put("dataPerintahView", dataPerintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String tarikh_bicara = "";
    		String id_perbicaraan = "" ;
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			tarikh_bicara = (String)idn.get("tarikh_bicara");
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("tarikh_bicara",tarikh_bicara);
    			context.put("id_perbicaraan",id_perbicaraan);

		    if (doPost.equals("true")) {
		    	deleteMaklumatSyariah(id_borangj, id_perbicaraan);
		    }

			getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
			long idUnitPsk = 0;
			if ( getrecord_infoperbicaraan.size() != 0 ){
				Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
				idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
				context.put("dataPerbicaraan", getrecord_infoperbicaraan);

			//* CHECKING LIST SENARAI WARIS
			senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris(idpermohonan,getParam("id_permohonansimati_atheader"));
			if ( senarai_waris.size() != 0 ){
				Hashtable a  = (Hashtable) senarai_waris.get(0);
				this.context.put("dataListWaris", senarai_waris);
			}else{
				this.context.put("dataListWaris", "");
			}

			context.put("viewMode", "no");
    		context.put("addMode", "yes");
    		context.put("editMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("tap", "permohonan");
    		context.put("button", "kembali");
    		context.put("flag", "");
    		context.put("tempcheckedmahkamahtinggi", "");
    		context.put("tempcheckedROTS", "");
    		context.put("flagRujukan", "");

    		vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("hapusROTS".equals(submit)){

		    String id_borangj = getParam("id_borangj");

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}
				context.put("dataPerintahView", dataPerintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String tarikh_bicara = "";
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			tarikh_bicara = (String) idn.get("tarikh_bicara");
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("tarikh_bicara",tarikh_bicara);
    			context.put("id_perbicaraan",id_perbicaraan);

		    if (doPost.equals("true")) {
		    	deleteMaklumatROTS(id_borangj, id_perbicaraan);
		    }

			getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
			long idUnitPsk = 0;
			if ( getrecord_infoperbicaraan.size() != 0 ){
				Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
				idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
				context.put("dataPerbicaraan", getrecord_infoperbicaraan);

			//* CHECKING LIST SENARAI WARIS
			senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris(idpermohonan,getParam("id_permohonansimati_atheader"));
			if ( senarai_waris.size() != 0 ){
				Hashtable a  = (Hashtable) senarai_waris.get(0);
				this.context.put("dataListWaris", senarai_waris);
			}else{
				this.context.put("dataListWaris", "");
			}

			context.put("viewMode", "no");
    		context.put("addMode", "yes");
    		context.put("editMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("tap", "permohonan");
    		context.put("button", "kembali");
    		context.put("flag", "");
    		context.put("flagRujukan", "");
    		context.put("tempcheckedmahkamahtinggi", "");
    		context.put("tempcheckedROTS", "");

    		vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("hapusFail".equals(submit)){

		    String id_perbicaraan = getParam("id_perbicaraan");
		    this.context.put("id_perbicaraan", id_perbicaraan);
		    String id_dokumen = getParam("idDokumen");
			String id_lampiran = getParam("idLampiran");

		    deleteUploadFail(id_dokumen, id_lampiran);

			//* GET ID_BORANGJ
			Hashtable getIdBorangJ = FrmPrmhnnSek8KptsanBicaraData.getIdBorangJ(id_perbicaraan);
			String idBorangJ = "";
			if (getIdBorangJ.size() > 0) {
			    idBorangJ = (String)getIdBorangJ.get("id_borangj");
			}

			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			String id_perintah = "";
			if ( getIdPerintah.size() != 0 ){
				Hashtable e = (Hashtable) getIdPerintah.get(0);
				id_perintah = (String)e.get("id_perintah");

				PerintahTangguhROTS = logic4.setTangguhROTS(id_perbicaraan,id_perintah,idBorangJ);
				long idUnitPsk = 0;
				String jenis_rujukan = "";
				String idNegeriMahkamah = "";
				String idMahkamah = "";

				if ( PerintahTangguhROTS.size() != 0 ){
					Hashtable b = (Hashtable) PerintahTangguhROTS.get(0);
					idUnitPsk = Long.parseLong(b.get("id_unitpsk").toString());
		    		jenis_rujukan = b.get("jenis_rujukan").toString();
		    		idNegeriMahkamah = b.get("id_negerimahkamah").toString();
		    		idMahkamah = b.get("id_mahkamah").toString();
					if (b.get("jenis_rujukan").equals("1")){
						checkedMahkamahTinggiROTS = "checked";
						checkedROTS = "";
					} else if (b.get("jenis_rujukan").equals("2")){
						checkedMahkamahTinggiROTS = "";
						checkedROTS = "checked";
					}
					context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggiROTS);
					context.put("TEMPcheckedROTS",checkedROTS);
				}
					context.put("infoPerintahTangguhROTS", PerintahTangguhROTS);
			}

			//* GET UPLOAD FILE
			logic4.setUploadFail(idFail);
			getFailUpload = logic4.getFailUpload();
			if ( getFailUpload.size() != 0 ){
				this.context.put("SenaraiLampiran",getFailUpload);
			}else {
				this.context.put("SenaraiLampiran","");
			}

		    context.put("viewMode", "no");
    		context.put("addMode", "no");
    		context.put("editMode", "yes");
    		context.put("viewEditMode", "no");
    		context.put("tap", "keputusan");

			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";


		}else if("hapusIDob".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp =(String)v.get("id_keputusanpermohonan");
			}

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
        		id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("id_perbicaraan",id_perbicaraan);

			//* GET ID_BORANGJ
			Hashtable getIdBorangJ = FrmPrmhnnSek8KptsanBicaraData.getIdBorangJ(id_perbicaraan);
			String idBorangJ = "";

			if (getIdBorangJ.size() > 0) {
			    idBorangJ = (String)getIdBorangJ.get("id_borangj");
			}

			//* GET ID_BORANGJDTL
			Vector getIdBorangJDTL = FrmPrmhnnSek8KptsanBicaraData.getIdBorangJDTL(id_perbicaraan,idBorangJ);
			Hashtable jdtl = (Hashtable) getIdBorangJDTL.get(0);
			this.context.put("getIdBorangJ", getIdBorangJDTL);
			String idBorangJDTL = (String)jdtl.get("id_borangjdtl");

			deleteMaklumatWaris(idBorangJ,idBorangJDTL);

			//* LIST KAN MAKLUMAT WARIS YANG ADA
			logic4.setMaklumatWaris(idBorangJ,id_perbicaraan,getParam("id_permohonansimati_atheader"));
			MaklumatWaris = logic4.getMaklumatWaris();
			this.context.put("SenaraiWaris",MaklumatWaris);

			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("skrin_editROTS".equals(submit)){

			String id_perbicaraan = getParam("id_perbicaraan");
		    this.context.put("id_perbicaraan", id_perbicaraan);

			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			if ( getIdPerintah.size() != 0 ){
				Hashtable e = (Hashtable) getIdPerintah.get(0);
				String id_perintah = (String)e.get("id_perintah");

				//* GET ID_BORANGJ
				Hashtable getIdBorangJ = FrmPrmhnnSek8KptsanBicaraData.getIdBorangJ(id_perbicaraan);
				String idBorangJ = "";
				if (getIdBorangJ.size() > 0) {
				    idBorangJ = (String)getIdBorangJ.get("id_borangj");
				}

				PerintahTangguhROTS = logic4.setTangguhROTS(id_perbicaraan,id_perintah,idBorangJ);
				long idUnitPsk = 0;
				String jenis_rujukan = "";
				String idMahkamah = "";
				String idNegeriMahkamah = "";
				if(PerintahTangguhROTS.size() != 0  ){
					Hashtable b = (Hashtable) PerintahTangguhROTS.get(0);
					idUnitPsk = Long.parseLong(b.get("id_unitpsk").toString());
		    		jenis_rujukan = b.get("jenis_rujukan").toString();
		    		idMahkamah = b.get("id_mahkamah").toString();
		    		context.put("id_pejabat", idMahkamah);
		    		idNegeriMahkamah = b.get("id_negerimahkamah").toString();
					if (b.get("jenis_rujukan").equals("1")){
						checkedMahkamahTinggiROTS = "checked";
						checkedROTS = "";
					} else if (b.get("jenis_rujukan").equals("2")){
						checkedMahkamahTinggiROTS = "";
						checkedROTS = "checked";
					}
					context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggiROTS);
					context.put("TEMPcheckedROTS",checkedROTS);
				}
					context.put("infoPerintahTangguhROTS", PerintahTangguhROTS);

	       		//dropdown
				context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",Utils.parseLong(idNegeriMahkamah),"style=width:305 onChange=\"doChangeidNegeriROTSupdate();\" "));
	    		if( idNegeriMhn != "" ){
	    			context.put("selectBicara",HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriMahkamah),"socTempatBicara",Utils.parseLong(idMahkamah),null,"disabled style=width:340 onChange=\"doChangeidMahkamahROTSupdate();\" "));
	    			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",idUnitPsk,"style=width:305"));
	            }else{
	    			context.put("selectBicara",HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriMahkamah),"socTempatBicara",null,null,"style=width:340 onChange=\"doChangeidMahkamahROTSupdate();\" "));
	    			context.put("selectPegawai",HTML.SelectPegawaiPengendali("socPegawai",idUnitPsk,"style=width:305"));
	            }

				checkedBox = "checked";
				context.put("TEMPcheckbox",checkedBox);

				//* SENARAIKAN MAKLUMAT WARIS
				logic4.setMaklumatWaris(idBorangJ, id_perbicaraan,getParam("id_permohonansimati_atheader"));
				MaklumatWaris = logic4.getMaklumatWaris();
				if ( MaklumatWaris.size() != 0 ){
					this.context.put("SenaraiWaris",MaklumatWaris);
				}else {
					this.context.put("SenaraiWaris","");
				}
			}

			//* GET SELECTED ID WARIS YG DIPILIH DARI POPUP
			logic4.setListSelectedWaris(session,idpermohonan,getParam("id_permohonansimati_atheader"));
			listSelectedWaris = logic4.getSelectedWaris();
			if ( listSelectedWaris.size() != 0 ){
				this.context.put("ListSelectedWaris",listSelectedWaris);
			}else {
				this.context.put("ListSelectedWaris","");
			}

			//* GET LIST DOKUMEN UPLOADED
			Maklumatdokumen = FrmPrmhnnSek8KptsanBicaraData.getMaklumatDokumen(idFail);
			if ( Maklumatdokumen.size() != 0 ){
				this.context.put("SenaraiLampiran",Maklumatdokumen);
			}else {
				this.context.put("SenaraiLampiran","");
			}

			//call flag
			context.put("editMode", "yes");
			context.put("viewMode", "no");
    		context.put("addMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("tab", "permohonan");
    		context.put("action", "onChange");
    		context.put("flag", "MT");

			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";


		}else if("edit_keputusanROTS".equals(submit)){

			String id_perbicaraan = getParam("id_perbicaraan");
		    this.context.put("id_perbicaraan", id_perbicaraan);

			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			if ( getIdPerintah.size() != 0 ){
				Hashtable e = (Hashtable) getIdPerintah.get(0);
				String id_perintah = (String)e.get("id_perintah");

				//* GET ID_BORANGJ
				Hashtable getIdBorangJ = FrmPrmhnnSek8KptsanBicaraData.getIdBorangJ(id_perbicaraan);
				String idBorangJ = "";
				if (getIdBorangJ.size() > 0) {
				    idBorangJ = (String)getIdBorangJ.get("id_borangj");
				}

				PerintahTangguhROTS = logic4.setTangguhROTS(id_perbicaraan,id_perintah,idBorangJ);
				String jenis_rujukan = "";
				String idNegeriMahkamah = "";
				String idMahkamah = "";
				String idUnitPSK = "";
				if ( PerintahTangguhROTS.size() != 0 ){
					Hashtable b = (Hashtable) PerintahTangguhROTS.get(0);
		    		jenis_rujukan = b.get("jenis_rujukan").toString();
					idNegeriMahkamah= b.get("id_negerimahkamah").toString();
					idMahkamah = b.get("id_mahkamah").toString();
					idUnitPSK = b.get("id_unitpsk").toString();
					if (b.get("jenis_rujukan").equals("1")){
						checkedMahkamahTinggiROTS = "checked";
						checkedROTS = "";
					} else if (b.get("jenis_rujukan").equals("2")){
						checkedMahkamahTinggiROTS = "";
						checkedROTS = "checked";
					}
					context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggiROTS);
					context.put("TEMPcheckedROTS",checkedROTS);
				}
					context.put("infoPerintahTangguhROTS", PerintahTangguhROTS);

				//DROP DOWN
				context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",Utils.parseLong(idNegeriMahkamah),null,"class=\"disabled\" style=width:305 onChange=\"doChangeidNegeriROTS();\" "));
	   			context.put("selectBicara", HTML.SelectMahkamah("socTempatBicara", Utils.parseLong(idMahkamah), " disabled", " class=\"disabled\" style=width:340 onChange=\"doChangeidMahkamahROTS();\""));
				context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",Utils.parseLong(idUnitPSK)," disabled", " class=\"disabled\" style=width:305"));

				//* GET MAKLUMAT WARIS
				MaklumatWaris = logic4.getMaklumatWaris();
				if ( MaklumatWaris.size() != 0 ){
					logic4.setMaklumatWaris(idBorangJ,id_perbicaraan,getParam("id_permohonansimati_atheader"));
					this.context.put("SenaraiWaris",MaklumatWaris);
				}else {
					this.context.put("SenaraiWaris","");
				}
			}

			logic4.setUploadFail(idFail);
			getFailUpload = logic4.getFailUpload();
			if ( getFailUpload.size() != 0 ){
				this.context.put("SenaraiLampiran",getFailUpload);
			}else{
				this.context.put("SenaraiLampiran","");
			}

			//call flag
			context.put("editMode", "yes");
			context.put("viewMode", "no");
    		context.put("addMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("tab", "keputusan");

    		vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("simpan_editROTS".equals(submit)){

		    String id_perbicaraan = getParam("id_perbicaraan");
		    this.context.put("id_perbicaraan", id_perbicaraan);
		    String id_pejabat = getParam("id_pejabat");

			//* GET ID_BORANGJ
			Hashtable getIdBorangJ = FrmPrmhnnSek8KptsanBicaraData.getIdBorangJ(id_perbicaraan);
			String idBorangJ = "";
			if (getIdBorangJ.size() > 0) {
			    idBorangJ = (String)getIdBorangJ.get("id_borangj");
			}

			updateROTSmaklumat(idpermohonan,id_perbicaraan,usid,idBorangJ);
			updateBorangJDTL(idBorangJ,usid);										//* UPDATE SENARAI WARIS

			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			if ( getIdPerintah.size() != 0 ){
				Hashtable e = (Hashtable) getIdPerintah.get(0);
				String id_perintah = (String)e.get("id_perintah");

				PerintahTangguhROTS = logic4.setTangguhROTS(id_perbicaraan,id_perintah,idBorangJ);
				long idUnitPsk = 0;
				String jenis_rujukan = "";
				String idMahkamah = "";
				String idNegeriMahkamah = "";

				if ( PerintahTangguhROTS.size() != 0 ){
					Hashtable b = (Hashtable) PerintahTangguhROTS.get(0);
					idUnitPsk = Long.parseLong(b.get("id_unitpsk").toString());
		    		jenis_rujukan = b.get("jenis_rujukan").toString();
		    		idMahkamah = b.get("id_mahkamah").toString();
		    		idNegeriMahkamah = b.get("id_negerimahkamah").toString();
					if (b.get("jenis_rujukan").equals("1")){
						checkedMahkamahTinggiROTS = "checked";
						checkedROTS = "";
					} else if (b.get("jenis_rujukan").equals("2")){
						checkedMahkamahTinggiROTS = "";
						checkedROTS = "checked";
					}
					context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggiROTS);
					context.put("TEMPcheckedROTS",checkedROTS);
				}
					context.put("infoPerintahTangguhROTS", PerintahTangguhROTS);

				context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",Utils.parseLong(idNegeriMahkamah),"disabled style=width:305 onChange=\"doChangeidNegeriUpdate();\" "));
				context.put("selectBicara",HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriMahkamah),"socTempatBicara",Utils.parseLong(idMahkamah),"disabled style=width:340 onChange=\"doChangeidMahkamahUpdate();\" "));
				context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",idUnitPsk,"disabled style=width:305"));
			}

			//* SENARAI MAKLUMAT WARIS
			logic4.setMaklumatWaris(idBorangJ,id_perbicaraan,getParam("id_permohonansimati_atheader"));
			MaklumatWaris = logic4.getMaklumatWaris();
			if ( MaklumatWaris.size() != 0 ){
				this.context.put("SenaraiWaris",MaklumatWaris);
			}else {
				this.context.put("SenaraiWaris","");
			}

			context.put("viewEditMode", "yes");
			context.put("editMode", "no");
			context.put("viewMode", "no");
    		context.put("addMode", "no");
    		context.put("action", "onChange2");

			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("edit_keputusan".equals(submit)){

		    String id_perbicaraan = getParam("id_perbicaraan");
		    this.context.put("id_perbicaraan", id_perbicaraan);

			//* GET ID_BORANGJ
			Hashtable getIdBorangJ = FrmPrmhnnSek8KptsanBicaraData.getIdBorangJ(id_perbicaraan);
			String idBorangJ = "";
			if (getIdBorangJ.size() > 0) {
			    idBorangJ = (String)getIdBorangJ.get("id_borangj");
			}

			getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
			String id_fail = "";
			if ( getrecord_infoperbicaraan.size() != 0 ){
				Hashtable a = (Hashtable) getrecord_infoperbicaraan.get(0);
				id_fail = a.get("id_fail").toString();
				if(doPost.equals("true")){
					updateKeputusan(idpermohonan,usid,id_perbicaraan,idBorangJ);

				  //  edit_status_ROTSkeputusan(idpermohonan, usid);
				  //  edit_status_tblrujsuburusanstatusfail_ROTSkeputusan(idsuburusanstatusfail, usid,
				  //  		id_perbicaraan, id_fail, idpermohonan);
				    //:::SUB
				    //ID_STATUS : 177
				    //ID_SUBURUSAN : 814
				    logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"177","814",idFail);
					
				    
				}
			}
				context.put("getrecord_infoperbicaraan",getrecord_infoperbicaraan);

			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			if ( getIdPerintah.size() != 0 ){
				Hashtable e = (Hashtable) getIdPerintah.get(0);
				String id_perintah = (String)e.get("id_perintah");

				PerintahTangguhROTS = logic4.setTangguhROTS(id_perbicaraan,id_perintah,idBorangJ);
				long idUnitPsk = 0;
				String jenis_rujukan = "";
				if ( PerintahTangguhROTS.size() != 0 ){
					Hashtable b = (Hashtable) PerintahTangguhROTS.get(0);
					idUnitPsk = Long.parseLong(b.get("id_unitpsk").toString());
		    		jenis_rujukan = b.get("jenis_rujukan").toString();
					if (b.get("jenis_rujukan").equals("1")){
						checkedMahkamahTinggiROTS = "checked";
						checkedROTS = "";
					} else if (b.get("jenis_rujukan").equals("2")){
						checkedMahkamahTinggiROTS = "";
						checkedROTS = "checked";
					}
					context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggiROTS);
					context.put("TEMPcheckedROTS",checkedROTS);
				}
					context.put("infoPerintahTangguhROTS", PerintahTangguhROTS);
			}

				// GET DATA KEPUTUSAN
				PermohonanROTSkeputusan = FrmPrmhnnSek8KptsanBicaraData.setROTSkeputusan(id_perbicaraan);
				if ( PermohonanROTSkeputusan.size() != 0 ){
					Hashtable c = (Hashtable) PermohonanROTSkeputusan.get(0);
					this.context.put("infoPermohonanROTSkeputusan", PermohonanROTSkeputusan);
				}else{
					this.context.put("infoPermohonanROTSkeputusan", "");
				}

			    //get info pemohon
				logic3.setListSemak(idpermohonan, usid);
				list = logic3.getListSemak();
				//hashtable maklumat header
				headerppk_baru(session,idpermohonan,"Y","","T");
				if ( list.size() != 0 ){
					Hashtable ls = (Hashtable) list.get(0);
					idstatus = ls.get("id_Status").toString();
					System.out.println("ID STATUS :: "+idstatus);
				}
					context.put("listSemak", list);
					context.put("id_status", idstatus);

			context.put("viewEditMode", "yes");
			context.put("editMode", "no");
			context.put("viewMode", "no");
    		context.put("addMode", "no");
    		context.put("addMode", "no");
    		context.put("tab", "keputusan");

			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("simpan_editMT".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
			String idkp = "";
			String id_perintah = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
				id_perintah = (String)v.get("id_perintah");
			}
				context.put("dataPerintahView", dataPerintah);
				context.put("id_perintah",id_perintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("id_perbicaraan",id_perbicaraan);

			String txdTarikhPerintahEdit = getParam("txdTarikhPerintahEdit");
			String jenisPerintah = getParam("jenisPerintah");
			String socPegawaiPengendali = getParam("socPegawaiPengendali");
			String id_pejabat = getParam("socTempatBicara");

			updateMahkamah(usid,id_perintah,id_perbicaraan);

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();

			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
				id_perintah = (String)v.get("id_perintah");
			}
				context.put("dataPerintahView", dataPerintah);
				context.put("id_perintah",id_perintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();

    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("id_perbicaraan", id_perbicaraan);

			//** call refresh data
			PerintahTangguh = FrmPrmhnnSek8KptsanBicaraData.setPerintahTangguh(id_perbicaraan);
			long idUnitPsk = 0;
			String jenis_keluar_perintah = "";
			long idPejabat = 0;
			long idNegeriMT = 0;
			if(PerintahTangguh.size()!=0){
				Hashtable b  = (Hashtable) PerintahTangguh.get(0);
				idUnitPsk = Long.parseLong(b.get("id_unitpsk").toString());
	    		jenis_keluar_perintah = b.get("jenis_keluar_perintah").toString();
	    		idPejabat = Long.parseLong(b.get("id_pejabatmahkamah").toString());
	    		idNegeriMT = Long.parseLong(b.get("id_negeri").toString());
			}
				context.put("infoPerintahTangguh", PerintahTangguh);
	    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",idNegeriMT,"disabled style=width:305 onChange=\"doChangeidNegeriUpdate();\" "));
	    		context.put("selectBicara",HTML.SelectMahkamahByNegeri(idNegeriMT, "socTempatBicara", idPejabat,"disabled onChange=\"doChangeidMahkamahUpdate();\""));
	    		context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",idUnitPsk,"disabled"));

				//get status id & id_suburusanstatusfail
				getstatusID = FrmPrmhnnSek8KptsanBicaraData.getListStatusID(idpermohonan);
				this.context.put("data", getstatusID);

		    context.put("viewMode", "yes");
    		context.put("addMode", "no");
    		context.put("editMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("button", "no");

			vm = "app/ppk/frmPrmhnnSek8MahkamahTinggi.jsp";


    	}else if("Cari".equals(submit)){

			carianNotis(usid);
			list = logic4.getListCarian();

    		//data & size default list
    		context.put("listNotis", list);
    		context.put("list_size", list.size());

    		//maintain searching value
    		this.context.put("txtNoFail", getParam("txtNoFail"));
    		this.context.put("txtPemohon", getParam("txtPemohon"));
    		this.context.put("txtSimati", getParam("txtSimati"));
    		this.context.put("txtIcSimati", getParam("txtIcSimati"));
    		this.context.put("jenisIc", getParam("jenisIc"));
    		this.context.put("statusFail", getParam("statusFail"));

			vm = "app/ppk/frmSenaraiFailKeputusanPerbicaraan.jsp";

    	}else if("tab_selesai".equals(submit)){

    		//CLEAR FIELD
    		context.put("TEMPcheckedTangguh", "");
    		context.put("TEMPcheckedBatal", "");
    		
    		context.put("check_kiv","");
    		
    		context.put("check_doc","");
    		context.put("valueKIV","");
    		context.put("date_kiv","");
    		context.put("catatan_kiv","");
    		
    		//CLOSE

    		//DROP DOWN
    		String idUnitPsk = getParam("EDITsocPegawaiPengendali");
    		context.put("selectEditPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali", Utils.parseLong(idUnitPsk),null));
    		//CLOSE

    		//CHECKING TARIKHMOHON
			String t_mohon = getParam("tarikhMohon");
			System.out.println("t_mohon = "+  t_mohon);
			String september_mohon ="01/09/2009";

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date tar_mohon = df.parse(t_mohon);
	        Date sep_mohon = df.parse(september_mohon);

	        if (tar_mohon.before(sep_mohon) || t_mohon.equals("")) {
    			System.out.println("TARIKH MOHON SEBELUM SEPTEMBER");
    			context.put("FlagtarikhMohon", 1);
    		}else{
    			System.out.println("TARIKH MOHON SELEPAS SEPTEMBER");
    			context.put("FlagtarikhMohon", 0);
    		}
    		//CLOSE

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if ( dataPerintah.size() != 0 ){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}

    		//--data notis
			//logic4.setListSemakWithData(idkp);
    		//dataNotis = logic4.getListSemakWithData();
    		//long idUnitPsk = 0;
    		//if(dataNotis.size()!=0){
    		//	Hashtable idn = (Hashtable) dataNotis.get(0);
    			//idUnitPsk = Long.parseLong(idn.get("id_unitpsk").toString());
        		//context.put("selectEditPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,null));
    		//}

    		//get jumlah_harta_tarikhmohon TBLPPKPERMOHONAN
    		Vector getJumlahBayaran = FrmPrmhnnSek8KptsanBicaraData.setJumlahBayaran(idpermohonan);

			//checking TBLPPKHA for NilaianAmanahRaya
			checkingNilaianAmanahRaya = logic2.checkingNilaianAmanahRaya(id_permohonansimati);
			double nilai_ha_tarikhmohon;
			if(checkingNilaianAmanahRaya.size()!=0){

				System.out.println("ADA NILAIAN HARTA AMANAH RAYA");
				Hashtable nilaian = (Hashtable) checkingNilaianAmanahRaya.get(0);
				nilai_ha_tarikhmohon = Double.parseDouble(nilaian.get("nilai_ha_tarikhmohon").toString());

				if(getJumlahBayaran.size()!=0){
					Hashtable a = (Hashtable) getJumlahBayaran.get(0);
			   		double jumlah_harta_tarikhmohon;
		    		double bayaranYuran;
		    		double jumlahHartaDeductNilaianAmanahRaya;
		    		double DisplayJumHartaAfterDeductAmanahRaya;
		    		jumlah_harta_tarikhmohon = Double.parseDouble(a.get("jumlah_harta_tarikhmohon").toString());
		    		//jumlahHartaDeductNilaianAmanahRaya = jumlah_harta_tarikhmohon;
		    		jumlahHartaDeductNilaianAmanahRaya = jumlah_harta_tarikhmohon - nilai_ha_tarikhmohon;
		    		
		    		//arief add 
			    	if ( (jumlahHartaDeductNilaianAmanahRaya > 0) && (jumlahHartaDeductNilaianAmanahRaya <= 1000) ) {
  						bayaranYuran = 10.00 ;
  					} else if ( (jumlahHartaDeductNilaianAmanahRaya > 1000) && (jumlahHartaDeductNilaianAmanahRaya <= 50000) ){
  						bayaranYuran = 30.00 ;
  					} else if ( (jumlahHartaDeductNilaianAmanahRaya > 50000) && (jumlahHartaDeductNilaianAmanahRaya <= 5000000) ) {
  						bayaranYuran = (0.002) * jumlahHartaDeductNilaianAmanahRaya ;
  						bayaranYuran = getBundaranBayaran(bayaranYuran);
  					} else {
  						bayaranYuran = (0.005) * jumlahHartaDeductNilaianAmanahRaya ;
  						bayaranYuran = getBundaranBayaran(bayaranYuran);
  					}
			    
			    		/** yang asal
			    		 * 
		    			if ( jumlahHartaDeductNilaianAmanahRaya <= 1000 ) {
							bayaranYuran = 10.00 ;
						} else if ( (jumlahHartaDeductNilaianAmanahRaya >= 1001) && (jumlahHartaDeductNilaianAmanahRaya <= 50000) ){
							bayaranYuran = 30.00 ;
						} else {
							bayaranYuran = (0.2/100) * jumlahHartaDeductNilaianAmanahRaya ;
							//ADD BY PEJE2
							bayaranYuran = getBundaranBayaran(bayaranYuran);
						}**/
			    		bezaTahun = tahunDaftar - tahunAktifDenda;
			    		if (bilHariSebenar > bilHari) {
			    			if (bezaTahun == 20){
			    				bayaranDenda = 1000;
    						}else {
    							for (int bilTahun = 1; bilTahun <= bezaTahun; bilTahun++) {
    							bayaranDenda = bayaranDenda + 50;
    							}
    						}
    					}
    					bayaranYuran = bayaranYuran + bayaranDenda;
			    	
						this.context.put("txtJumBayaran", bayaranYuran); //Yuran Perintah
						this.context.put("txtJumHarta", jumlah_harta_tarikhmohon);
						this.context.put("txtJumHartaDikenakanBayaranPerintah", jumlahHartaDeductNilaianAmanahRaya);
						System.out.println("txtJumBayaran = "+bayaranYuran);
						System.out.println("txtJumHarta = "+jumlah_harta_tarikhmohon);
						System.out.println("txtJumHartaDikenakanBayaranPerintah = "+jumlahHartaDeductNilaianAmanahRaya);
						this.context.put("txtJumDendaLewat", bayaranDenda);
  			    		System.out.println("txtJumDendaLewat = "+bayaranDenda);
				}
				context.put("dataJumlahBayaran", getJumlahBayaran);

			}else{

				System.out.println("TIADA NILAIAN HARTA AMANAH RAYA");
				if(getJumlahBayaran.size()!=0){
					Hashtable a = (Hashtable) getJumlahBayaran.get(0);
			   		double jumlah_harta_tarikhmohon;
		    		double bayaranYuran;
		    		
		    		if (a.get("jumlah_harta_tarikhmohon") == null || "".equals(a.get("jumlah_harta_tarikhmohon"))) {
		    			jumlah_harta_tarikhmohon = 0.00;
		    		} else {
		    			jumlah_harta_tarikhmohon = Double.parseDouble(""+a.get("jumlah_harta_tarikhmohon"));
		    		}
		    		

		    		//arief add
		    		
		    		if ( (jumlah_harta_tarikhmohon > 0) && (jumlah_harta_tarikhmohon <= 1000) ) {
							bayaranYuran = 10.00 ;
						} else if ( (jumlah_harta_tarikhmohon > 1000) && (jumlah_harta_tarikhmohon <= 50000) ){
							bayaranYuran = 30.00 ;
						} else if ( (jumlah_harta_tarikhmohon > 50000) && (jumlah_harta_tarikhmohon <= 5000000) ) {
							bayaranYuran = (0.002) * jumlah_harta_tarikhmohon ;
							bayaranYuran = getBundaranBayaran(bayaranYuran);
						} else {
							bayaranYuran = (0.005) * jumlah_harta_tarikhmohon ;
							bayaranYuran = getBundaranBayaran(bayaranYuran);
						}
		    		
		    		bezaTahun = tahunDaftar - tahunAktifDenda;
			    		if (bilHariSebenar > bilHari) {
			    			if (bezaTahun == 20){
			    				bayaranDenda = 1000;
    						}else {
    							for (int bilTahun = 1; bilTahun <= bezaTahun; bilTahun++) {
    							bayaranDenda = bayaranDenda + 50;
    							}
    						}
    					}
    				bayaranYuran = bayaranYuran + bayaranDenda;
    				
		    		this.context.put("txtJumBayaran", bayaranYuran);
					this.context.put("txtJumHarta", jumlah_harta_tarikhmohon);
					this.context.put("txtJumHartaDikenakanBayaranPerintah", jumlah_harta_tarikhmohon);
					System.out.println("txtJumBayaran = "+bayaranYuran);
					System.out.println("txtJumHarta = "+jumlah_harta_tarikhmohon);
					System.out.println("txtJumHartaDikenakanBayaranPerintah = "+jumlah_harta_tarikhmohon);
					this.context.put("txtJumDendaLewat", bayaranDenda);
			    	System.out.println("txtJumDendaLewat = "+bayaranDenda);
		    		}
		    		
		    		/** yang asal
		    		if ( jumlah_harta_tarikhmohon <= 1000 ) {
							bayaranYuran = 10.00 ;
						} else if ( (jumlah_harta_tarikhmohon >= 1001) && (jumlah_harta_tarikhmohon <= 50000) ){
							bayaranYuran = 30.00 ;
						} else {
							bayaranYuran = (0.2/100) * jumlah_harta_tarikhmohon ;
							//ADD BY PEJE1
							bayaranYuran = getBundaranBayaran(bayaranYuran);
						}
						this.context.put("txtJumBayaran", bayaranYuran);
						this.context.put("txtJumHarta", jumlah_harta_tarikhmohon);
				}**/
					context.put("dataJumlahBayaran", getJumlahBayaran);

			}

    		//call flag
    		context.put("TEMPcheckedSelesai", "checked");
    		context.put("mode", "add");
    		context.put("flag", "selesai");
    		context.put("tarikh", "");
    		context.put("button", "");
    		context.put("tarikh", "perintah");

            vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

    	}else if("tab_tangguh".equals(submit)){

			//clear field
    		context.put("TEMPcheckedTidakHadir", "");
    		context.put("TEMPcheckedWarisTidakLengkap", "");
    		context.put("TEMPcheckedMahkamahTinggi", "");
    		context.put("TEMPcheckedBuktiTidakLengkap", "");
    		context.put("TEMPcheckedMahkamahSyariah", "");
    		context.put("TEMPcheckedPertelingkahanKolateral", "");
    		context.put("TEMPcheckedSijilFaraid", "");
    		context.put("TEMPcheckedSuratSetuju", "");
    		context.put("TEMPcheckedSebabLain", "");

    		//DROP DOWN
    		String idUnitPsk = getParam("EDITsocPegawaiPengendali");
    		context.put("selectEditPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali", Utils.parseLong(idUnitPsk),null));
    		//CLOSE

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if ( dataPerintah.size() != 0 ){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}

    		//--data notis
			//logic4.setListSemakWithData(idkp);
    		//dataNotis = logic4.getListSemakWithData();
    		//long idUnitPsk = 0;
    		//if(dataNotis.size()!=0){
    		//	Hashtable idn = (Hashtable) dataNotis.get(0);
    			//idUnitPsk = Long.parseLong(idn.get("id_unitpsk").toString());
        		//context.put("selectEditPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,null));
    		//}

			//clear field
    		//context.put("tempoh_tunggu_faraid","");
    		//context.put("sebab_tangguh","");
    		context.put("keputusan_mahkamah","");
    		context.put("TEMPcheckedSelesai", "");
    		context.put("TEMPcheckedBatal", "");

    		//call flag
    		context.put("TEMPcheckedTangguh", "checked");
    		context.put("mode", "add");
    		context.put("flag", "tangguh");
    		context.put("tarikh", "bicara");
    		context.put("button", "");

            vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";


    	}else if("tab_batal".equals(submit)){
    		//command 2
        	String submit2 = getParam("command2");
        	myLogger.info("[submit2] :: " +submit2);
    		if ("changeGetAlamatMahkamah".equals(submit2)){
    			
    			//onchange id
        		String id_namaM = getParam("socMahkamah");
        		
    	    	//MAHKAMAH

    	    	
    	    	String bandarM = "";
        		String alamatM1 = "";
    	    	String alamatM2 = "";
    	    	String alamatM3 = "";
    	    	String poskodM = "";
    	    	String idnegeriM = "";
    	    	String idbandarM = "";
    	    	
    	    	
    	    	if(id_namaM!=""){

        	    	String id_pejabat = "";

            		detailMahkamah = model.getDetailMahkamah(id_namaM);

            		if(detailMahkamah.size()!=0){

            			String C_namaM = "";

            			Hashtable onc = (Hashtable) detailMahkamah.get(0);

            			id_pejabat = onc.get("id_pejabat").toString();
            			alamatM1 = onc.get("alamat1").toString();
            			alamatM2 = onc.get("alamat2").toString();
            			alamatM3 = onc.get("alamat3").toString();
            			poskodM = onc.get("poskod").toString();
            			idnegeriM = onc.get("id_negeri").toString();
            			idbandarM = onc.get("id_bandar").toString();
            			C_namaM = onc.get("nama_pejabat").toString();

            			context.put("id_pejabatCH", id_pejabat);
            	    	context.put("C_namaM", C_namaM);

            		}//close if(detailMahkamah.size()!=0)


            	}//close if get namaM != ""

        	    	context.put("alamatM1", alamatM1);
        	    	context.put("alamatM2", alamatM2);
        	    	context.put("alamatM3", alamatM3);
        	    	context.put("poskodM", poskodM);

        	    	if(idnegeriM!=""){
            			context.put("selectNegeriMahkamah",HTML.SelectNegeri("socNegeriMahkamah",Utils.parseLong(idnegeriM),null,"style=width:300px onchange=onchangeBandarByNegeri()"));
            			if(idbandarM!=""){
            				context.put("selectBandarMahkamah",HTML.SelectBandarByNegeri(idnegeriM,"socBandarMahkamah",Utils.parseLong(idbandarM),"style=width:300px"));
            			}else{
            				context.put("selectBandarMahkamah",HTML.SelectBandarByNegeri(idnegeriM,"socBandarMahkamah",null,"style=width:300px"));
                		}
            		}else{
            			context.put("selectNegeriMahkamah",HTML.SelectNegeri("socNegeriMahkamah",null,null,"style=width:300px onchange=onchangeBandarByNegeri()"));
            			if(idbandarM!=""){
            				context.put("selectBandarMahkamah",HTML.SelectBandar("socBandarMahkamah",Utils.parseLong(idbandarM),"style=width:300px"));
            			}else{
            				context.put("selectBandarMahkamah",HTML.SelectBandar("socBandarMahkamah",null,"style=width:300px"));
                		}
            		}

            	
    		}
    		if ("deleteSuppDocMode".equals(submit2))
    		{
    			//String idSimati = getParam("idSimati");
    			deleteSuppDoc(idSimati, jenisDoc);
    			/*String idPermohonan = getParam("idPermohonan");
    			logic_A.setSupportingDoc(idPermohonan, jenisDoc);
    			listSupportingDoc = logic_A.setSupportingDoc(idPermohonan, jenisDoc);
    			this.context.put("ViewSupportingDoc", listSupportingDoc);*/
    		}
    		
			//clear field    		
    		context.put("TEMPcheckedMahkamahTinggiWasiat", "");
    		context.put("TEMPcheckedTidakHadir3Kali", "");
    		context.put("TEMPcheckedPermintaanPemohon", "");
    		context.put("TEMPcheckedMahkamahTinggi2Juta", "");    		
    		context.put("TEMPcheckedSebabLainBatal", "");

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if ( dataPerintah.size() != 0 ){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}

    		//DROP DOWN
    		String idUnitPsk = getParam("EDITsocPegawaiPengendali");
    		context.put("selectEditPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali", Utils.parseLong(idUnitPsk),null));

			//clear field
    		context.put("TEMPcheckedSelesai", "");
    		context.put("TEMPcheckedTangguh", "");

    		//call flag
    		context.put("TEMPcheckedBatal", "checked");
    		context.put("mode", "add");
    		context.put("flag", "batal");
    		context.put("tarikh", "perintah");
    		context.put("button", "");
    		
    		String id_negeri = getParam("id_negeri");
    		
    		//get list mahkamah
    		listMahkamah = model.getListMahkamah(id_negeri);

    		//size mahkamah (if=0 gune textfield)
    		context.put("saiz_listM", listMahkamah.size());
    		context.put("listMahkamah", listMahkamah);
    		
    		String id = getParam("id_permohonan");
			FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			
			Vector listPemohon2 = FrmSenaraiFailKeputusanPermohonanInternalData
					.getData();
			this.context.put("ViewPemohon", listPemohon2);
			
			logic_A.setSupportingDoc(id, jenisDoc);
			listSupportingDoc = logic_A.setSupportingDoc(id, jenisDoc);
			this.context.put("ViewSupportingDoc", listSupportingDoc);

            vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

		}else if("RulerOfTheState".equals(submit)){

		    //* CLEAR INPUT
		    context.put("tempcheckedmahkamahtinggi","");
		    context.put("tempcheckedrots","");
		    context.put("tempcheckedms","");
		    context.put("tempcheckedpejmufti","");
		    context.put("txtPendapatTangguh","");
		    context.put("txtCatatanTangguh","");

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}
				context.put("dataPerintahView", dataPerintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String tarikh_bicara = "";
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
        		tarikh_bicara = (String) idn.get("tarikh_bicara");
        		id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("tarikh_bicara",tarikh_bicara);
    			context.put("id_perbicaraan",id_perbicaraan);


    		//GET INFO PERBICARAAN
    		getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
			long idUnitPsk = 0;
			if ( getrecord_infoperbicaraan.size() != 0 ){
				Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
				idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
				context.put("dataPerbicaraan", getrecord_infoperbicaraan);

			//CHECKING SENARAI WARIS
			senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris(idpermohonan,getParam("id_permohonansimati_atheader"));
			if ( senarai_waris.size() != 0 ){
				Hashtable a  = (Hashtable) senarai_waris.get(0);
				this.context.put("dataListWaris", senarai_waris);
			}else{
				this.context.put("dataListWaris", "");
			}

			// call flag
			context.put("addMode", "yes");
			context.put("viewMode", "no");
			context.put("viewEditMode", "no");
			context.put("editMode", "no");
			context.put("flag", "");
			context.put("button", "kembali");
			context.put("flagRujukan", "");

    		vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";


		}else if("doChangeidMahkamahROTS".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}
				context.put("dataPerintahView", dataPerintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String tarikh_bicara = "";
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			tarikh_bicara = (String) idn.get("tarikh_bicara");
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("tarikh_bicara",tarikh_bicara);
    			context.put("id_perbicaraan",id_perbicaraan);

			//get data TBLPPKPERBICARAAN
			Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);
			long idUnitPsk = 0;
			if(h.size()!=0){
				idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
				context.put("dataPerbicaraan", h);

    		String idNegeriTempatBicara = getParam("socNegeri");
    		String idPejabat = getParam("socTempatBicara");
    		String jenisPerintah = getParam("jenisPerintah");
    		String txdTarikhPerintahAdd = getParam("txdTarikhPerintahAdd");

    		int idNegeri = 0;
    		String id_pejabat = "";
    		String nama_pejabat = "";
    		String alamat1 = "";
    		String alamat2 = "";
    		String alamat3 = "";
    		String poskod  = "";
    		String negeri  = "";
    		String no_tel  = "";
    		String no_fax = "";

    		if ( idNegeriTempatBicara != "" )
    		{
    			idNegeri = Integer.parseInt(idNegeriTempatBicara);
    			alamatTempatBicara = logic4.getAlamatTempatMahkamah(idNegeriTempatBicara,idPejabat);
    			Hashtable AB = (Hashtable) alamatTempatBicara.get(0);
    			idPejabat  = AB.get("id_pejabat").toString();
    			nama_pejabat = AB.get("nama_pejabat").toString();
        		alamat1 = AB.get("alamat1").toString();
        		alamat2 = AB.get("alamat2").toString();
        		alamat3 = AB.get("alamat3").toString();
        		poskod 	= AB.get("poskod").toString();
        		no_tel 	= AB.get("no_tel").toString();
        		no_fax 	= AB.get("no_fax").toString();
        		negeri 	= AB.get("id_negeri").toString();
    		}
    		if ( idNegeriTempatBicara != "" ){
    			context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",Utils.parseLong(idNegeriTempatBicara),"style=width:305 onChange=\"doChangeidNegeriROTS();\" "));
    		}else{
    			context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",null,"style=width:305 onChange=\"doChangeidNegeri();\" "));
    		}
    		context.put("selectBicara",HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriTempatBicara),"socTempatBicara",Utils.parseLong(idPejabat),"style=width:340 onChange=\"doChangeidMahkamahROTS();\" "));
			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",idUnitPsk,"style=width:305"));

       		//list & data
    		context.put("listSemak", list);

    		//get & post all field content
    		context.put("jenisPerintah", getParam("jenisPerintaah"));
    		context.put("id_pejabat", idPejabat);
    		context.put("nama_pejabat", nama_pejabat);
    		context.put("alamat1", alamat1);
    		context.put("alamat2", alamat2);
    		context.put("alamat3", alamat3);
    		context.put("poskod", poskod);
    		context.put("notel", no_tel);
    		context.put("nofax", no_fax);
    		context.put("txdTarikhPerintahAdd", getParam("txdTarikhPerintahAdd"));

    		//form validation
			context.put("addMode", "yes");
			context.put("viewMode", "no");
			context.put("viewEditMode", "no");
			context.put("editMode", "no");
			context.put("flag", "MT");

	        vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("doChangeidMahkamahROTSupdate".equals(submit)){

    		String id_perbicaraan = getParam("id_perbicaraan");

			//* GET MAKLUMAT TANGGUH
			PerintahTangguh = FrmPrmhnnSek8KptsanBicaraData.setPerintahTangguh(id_perbicaraan);
			long idUnitPsk = 0;
			String jenis_keluar_perintah = "";
			if ( PerintahTangguh.size() != 0 ){
				Hashtable c  = (Hashtable) PerintahTangguh.get(0);
				idUnitPsk = Long.parseLong(c.get("id_unitpsk").toString());
	    		jenis_keluar_perintah =(String) c.get("jenis_keluar_perintah");
			}
				context.put("infoPerintahTangguh",PerintahTangguh);

    		String jenisPerintah = getParam("jenisPerintah");
    		String txdTarikhPerintahAdd = getParam("txdTarikhPerintahAdd");
    		String idNegeriTempatBicara = getParam("socNegeri");
    		String idPejabat = getParam("socTempatBicara");

    		int idNegeri = 0;
    		String alamat1 = "";
    		String alamat2 = "";
    		String alamat3 = "";
    		String poskod  = "";
    		String negeri  = "";
    		String no_tel  = "";
    		String no_fax = "";

    		if( idNegeriTempatBicara != "" )
    		{
    			idNegeri = Integer.parseInt(idNegeriTempatBicara);
    			alamatTempatBicara = logic4.getAlamatTempatMahkamah(idNegeriTempatBicara,idPejabat);
    			Hashtable AB = (Hashtable) alamatTempatBicara.get(0);
    			idPejabat = AB.get("id_pejabat").toString();
        		alamat1 = AB.get("alamat1").toString();
        		alamat2 = AB.get("alamat2").toString();
        		alamat3 = AB.get("alamat3").toString();
        		poskod  = AB.get("poskod").toString();
        		no_tel  = AB.get("no_tel").toString();
        		no_fax  = AB.get("no_fax").toString();
        		negeri  = AB.get("id_negeri").toString();
    		}
    	    context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",Utils.parseLong(idNegeriTempatBicara),"style=width:305 onChange=\"doChangeidNegeriROTSupdate();\" "));
    		context.put("selectBicara",HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriTempatBicara),"socTempatBicara",Utils.parseLong(idPejabat),"style=width:340 onChange=\"doChangeidMahkamahROTSupdate();\" "));
    		context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn, "socPegawai", idUnitPsk, "style=width:305"));

       		//list & data
    		context.put("listSemak", list);

    		//get & post all field content
    		context.put("jenisPerintah", getParam("jenisPerintah"));
    		context.put("id_pejabat", idPejabat);
    		context.put("alamat1", alamat1);
    		context.put("alamat2", alamat2);
    		context.put("alamat3", alamat3);
    		context.put("poskod", poskod);
    		context.put("no_tel", no_tel);
    		context.put("no_fax", no_fax);
    		context.put("txdTarikhPerintahAdd", getParam("txdTarikhPerintahAdd"));

    		//form validation
			context.put("addMode", "no");
			context.put("viewMode", "no");
			context.put("viewEditMode", "no");
			context.put("editMode", "yes");
			context.put("action", "");

	        vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";


		}else if("doChangeidMahkamahMS".equals(submit)){

    		//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String) v.get("id_keputusanpermohonan");
			}
				context.put("dataPerintahView", dataPerintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String tarikh_bicara = "";
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
        		tarikh_bicara = (String) idn.get("tarikh_bicara").toString();
        		id_perbicaraan = (String) idn.get("id_perbicaraan");
    		}
    			context.put("tarikh_bicara",tarikh_bicara);
    			context.put("id_perbicaraan",id_perbicaraan);

			//get data TBLPPKPERBICARAAN
			Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);
			long idUnitPsk = 0;
			if(h.size()!=0){
				idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
				context.put("dataPerbicaraan", h);

    		String idNegeriTempatBicara = getParam("socNegeri");
    		String idPejabat = getParam("socTempatBicara");
    		String jenisPerintah = getParam("jenisPerintah");
    		String txdTarikhPerintahAdd = getParam("txdTarikhPerintahAdd");

    		int idNegeri = 0;
    		String id_pejabat = "";
    		String nama_pejabat = "";
    		String alamat1 = "";
    		String alamat2 = "";
    		String alamat3 = "";
    		String poskod  = "";
    		String negeri  = "";
    		String no_tel  = "";
    		String no_fax = "";

    		if ( idNegeriTempatBicara != "" )
    		{
    			idNegeri = Integer.parseInt(idNegeriTempatBicara);
    			alamatTempatBicara = logic4.getAlamatTempatMahkamahSyariah(idNegeriTempatBicara,idPejabat);
    			if (alamatTempatBicara.size()!=0){
        			Hashtable AB = (Hashtable) alamatTempatBicara.get(0);
        			idPejabat  = AB.get("id_pejabat").toString();
        			nama_pejabat = AB.get("nama_pejabat").toString();
            		alamat1 = AB.get("alamat1").toString();
            		alamat2 = AB.get("alamat2").toString();
            		alamat3 = AB.get("alamat3").toString();
            		poskod 	= AB.get("poskod").toString();
            		no_tel 	= AB.get("no_tel").toString();
            		no_fax 	= AB.get("no_fax").toString();
            		negeri 	= AB.get("id_negeri").toString();
    			}
    		}

    		if ( idNegeriTempatBicara != "" ){
    			context.put("selectNegeri",HTML.SelectNegeriByMahkamahSyariah("socNegeri",Utils.parseLong(idNegeriTempatBicara),"style=width:375 onChange=\"doChangeidNegeriMS();\" "));
    		}else{
    			context.put("selectNegeri",HTML.SelectNegeriByMahkamahSyariah("socNegeri",null,"style=width:375 onChange=\"doChangeidNegeriMS();\" "));
    		}
    		context.put("selectBicara",HTML.SelectMahkamahSyariahByNegeri(Utils.parseLong(idNegeriTempatBicara),"socTempatBicara",Utils.parseLong(idPejabat),"style=width:375 onChange=\"doChangeidMahkamahMS();\" "));
			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",idUnitPsk,"style=width:305"));

       		//list & data
    		context.put("listSemak", list);

    		//get & post all field content
    		context.put("jenisPerintah", getParam("jenisPerintah"));
    		context.put("id_pejabat", idPejabat);
    		context.put("nama_pejabat", nama_pejabat);
    		context.put("alamat1", alamat1);
    		context.put("alamat2", alamat2);
    		context.put("alamat3", alamat3);
    		context.put("poskod", poskod);
    		context.put("notel", no_tel);
    		context.put("nofax", no_fax);
    		context.put("txdTarikhPerintahAdd", getParam("txdTarikhPerintahAdd"));

    		//form validation
			context.put("addMode", "yes");
			context.put("viewMode", "no");
			context.put("viewEditMode", "no");
			context.put("editMode", "no");
			context.put("flag", "ROTS");
			context.put("action", "onChange");

	        vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("doChangeidMahkamahMSupdate".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String) v.get("id_keputusanpermohonan");
			}
				context.put("dataPerintahView", dataPerintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String tarikh_bicara = "";
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			tarikh_bicara = (String) idn.get("tarikh_bicara").toString();
    			id_perbicaraan = (String) idn.get("id_perbicaraan");
    		}
    			context.put("tarikh_bicara",tarikh_bicara);
    			context.put("id_perbicaraan",id_perbicaraan);

			//get data TBLPPKPERBICARAAN
			Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);
			long idUnitPsk = 0;
			if(h.size()!=0){
				idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
				context.put("dataPerbicaraan", h);

    		String idNegeriTempatBicara = getParam("socNegeri");
    		String idPejabat = getParam("socTempatBicara");
    		String jenisPerintah = getParam("jenisPerintah");
    		String txdTarikhPerintahAdd = getParam("txdTarikhPerintahAdd");

    		int idNegeri = 0;
    		String id_pejabat = "";
    		String nama_pejabat = "";
    		String alamat1 = "";
    		String alamat2 = "";
    		String alamat3 = "";
    		String poskod  = "";
    		String negeri  = "";
    		String no_tel  = "";
    		String no_fax = "";

    		if ( idNegeriTempatBicara != "" )
    		{
    			idNegeri = Integer.parseInt(idNegeriTempatBicara);
    			alamatTempatBicara = logic4.getAlamatTempatMahkamahSyariah(idNegeriTempatBicara,idPejabat);
    			if(alamatTempatBicara.size()!=0){
    				Hashtable AB = (Hashtable) alamatTempatBicara.get(0);
        			idPejabat  = AB.get("id_pejabat").toString();
        			nama_pejabat = AB.get("nama_pejabat").toString();
            		alamat1 = AB.get("alamat1").toString();
            		alamat2 = AB.get("alamat2").toString();
            		alamat3 = AB.get("alamat3").toString();
            		poskod 	= AB.get("poskod").toString();
            		no_tel 	= AB.get("no_tel").toString();
            		no_fax 	= AB.get("no_fax").toString();
            		negeri 	= AB.get("id_negeri").toString();
    			}
    		}
    		if ( idNegeriTempatBicara != "" ){
    			context.put("selectNegeri",HTML.SelectNegeriByMahkamahSyariah("socNegeri",Utils.parseLong(idNegeriTempatBicara),"style=width:305 onChange=\"doChangeidNegeriMSupdate();\" "));
    		}else{
    			context.put("selectNegeri",HTML.SelectNegeriByMahkamahSyariah("socNegeri",null,"style=width:305 onChange=\"doChangeidNegeriMSupdate();\" "));
    		}
    		context.put("selectBicara",HTML.SelectMahkamahSyariahByNegeri(Utils.parseLong(idNegeriTempatBicara),"socTempatBicara",Utils.parseLong(idPejabat),"style=width:340 onChange=\"doChangeidMahkamahMSupdate();\" "));
			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",idUnitPsk,"style=width:305"));

       		//list & data
    		context.put("listSemak", list);

    		//get & post all field content
    		context.put("jenisPerintah", getParam("jenisPerintah"));
    		context.put("id_pejabat", idPejabat);
    		context.put("nama_pejabat", nama_pejabat);
    		context.put("alamat1", alamat1);
    		context.put("alamat2", alamat2);
    		context.put("alamat3", alamat3);
    		context.put("poskod", poskod);
    		context.put("notel", no_tel);
    		context.put("nofax", no_fax);
    		context.put("txdTarikhPerintahAdd", getParam("txdTarikhPerintahAdd"));

    		//form validation
			context.put("addMode", "no");
			context.put("viewMode", "no");
			context.put("viewEditMode", "no");
			context.put("editMode", "yes");
			context.put("flag", "ROTS");

	        vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("MahkamahTinggi".equals(submit)){

			//clearkan field
		    this.context.put("id_pejabat", "");
		    this.context.put("txtidnegeri", "");
		    this.context.put("nama_pejabat", "");
			this.context.put("alamat1", "");
			this.context.put("alamat2", "");
			this.context.put("alamat3", "");
			this.context.put("poskod", "");
			this.context.put("notel", "");
			this.context.put("nofax", "");
			//close

    		//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}
				context.put("dataPerintahView", dataPerintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String tarikh_bicara = "";
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			tarikh_bicara = (String)idn.get("tarikh_bicara");
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("tarikh_bicara",tarikh_bicara);
    			context.put("id_perbicaraan",id_perbicaraan);

			//get data TBLPPKPERBICARAAN
			Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);
			long idUnitPsk = 0;
			if(h.size()!=0){
				idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
				context.put("dataPerbicaraan", h);

    		context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",null,null,"style=width:305 onChange=\"doChangeidNegeri();\" "));
   			context.put("selectBicara", HTML.SelectMahkamah("socTempatBicara", null, "class=disabled disabled style=width:340 onChange=\"doChangeidMahkamah();\" "));
			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",idUnitPsk,"style=width:305"));

			String jenisPerintah = "";
    		if(jenisPerintah.equals("PT")){
	    		context.put("jenisPerintah", "PT");
	    	}else if(jenisPerintah.equals("PD")){
	    		context.put("jenisPerintah", "PD");
	    	}else if(jenisPerintah.equals("PDS")){
	    		context.put("jenisPerintah", "PDS");
	    	}else{
	    		context.put("jenisPerintah", "");
	    	}

    		context.put("addMode", "yes");
    		context.put("viewMode", "no");
    		context.put("editMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("action", "");

			vm = "app/ppk/frmPrmhnnSek8MahkamahTinggi.jsp";


		}else if("MahkamahTinggiEdit".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if (dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}
				context.put("dataPerintahView", dataPerintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
			dataNotis = logic4.getListSemakWithData();
			String id_perbicaraan ="";
			if (dataNotis.size()!=0){
				Hashtable idn = (Hashtable) dataNotis.get(0);
				id_perbicaraan = (String)idn.get("id_perbicaraan");
			}
				context.put("id_perbicaraan",id_perbicaraan);

			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			String id_perintah = "";
			if ( getIdPerintah.size() != 0 ){
				Hashtable d = (Hashtable) getIdPerintah.get(0);
				id_perintah = (String)d.get("id_perintah");
			}
				context.put("id_perintah",id_perintah);


				Vector getID_pejabatMahkamah = logic4.setIDPejabatMahkamah(id_perintah,id_perbicaraan);
				String id_pejabatmahkamah = "";
				if (getID_pejabatMahkamah.size()!=0){
					Hashtable p = (Hashtable) getID_pejabatMahkamah.get(0);
					id_pejabatmahkamah = p.get("id_pejabatmahkamah").toString();
				}

					if (id_pejabatmahkamah != ""){
						PerintahTangguh = FrmPrmhnnSek8KptsanBicaraData.setPerintahTangguh(id_perbicaraan);
						long idUnitPsk = 0;
						String jenis_keluar_perintah ="";
						long idPejabat =0;
						long idNegeri =0;
						if (PerintahTangguh.size()!=0){
							Hashtable b = (Hashtable) PerintahTangguh.get(0);
							idUnitPsk = Long.parseLong(b.get("id_unitpsk").toString());
				    		jenis_keluar_perintah = b.get("jenis_keluar_perintah").toString();
				    		idPejabat = Long.parseLong(b.get("id_pejabatmahkamah").toString());
				    		idNegeri = Long.parseLong(b.get("id_negeri").toString());
						}
							context.put("infoPerintahTangguh", PerintahTangguh);

			    		context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",idNegeri,"disabled style=width:305 onChange=\"doChangeidNegeriUpdate();\" "));
			    		context.put("selectBicara",HTML.SelectMahkamahByNegeri(idNegeri, "socTempatBicara", idPejabat,"disabled onChange=\"doChangeidMahkamahUpdate();\""));
			    		context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",idUnitPsk,"disabled"));

					    context.put("viewMode", "no");
			    		context.put("addMode", "no");
			    		context.put("editMode", "no");
			    		context.put("viewEditMode", "yes");
			    		context.put("button", "viewEditMode");
			    		context.put("action", "onChange");

					} else {
						String alamat1 = "";
						String alamat2 = "";
						String alamat3 = "";
						String poskod = "";
						String notel = "";
						String nofax = "";
						String txtFaktaGuamanAdd = "";
						String txtPendapatAdd = "";

						getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
						long idUnitPsk = 0;
						if(getrecord_infoperbicaraan.size()!=0){
							Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
							idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
						}
						this.context.put("dataPerbicaraan", getrecord_infoperbicaraan);
			    		context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",null,"style=width:305 onChange=\"doChangeidNegeri();\" "));
			    		context.put("selectBicara", HTML.SelectMahkamah("socTempatBicara", null, "class=disabled disabled style=width:340 onChange=\"doChangeidMahkamah();\" "));
			    		context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",idUnitPsk,"disabled"));

						//call flag
			    		context.put("addMode", "yes");
			    		context.put("viewMode", "no");
			    		context.put("editMode", "no");
			    		context.put("viewEditMode", "no");
			    		context.put("action", "");
					}

			vm = "app/ppk/frmPrmhnnSek8MahkamahTinggi.jsp";

		}else if("doChangeidNegeri".equals(submit)){

    		String idNegeriTempatBicara ="";
    		String jenisPerintah ="";
    		idNegeriTempatBicara = getParam("socNegeri");
    		jenisPerintah = getParam("jenisPerintah");

    		int idNegeri = 0;
    		String idPejabat = "";
    		String alamat1 = "";
    		String alamat2 = "";
    		String alamat3 = "";
    		String poskod  = "";
    		String negeri  = "";
    		String notel = "";
    		String nofax = "";
    		String txdTarikhPerakuanAdd  = "";
    		String txdTarikhBicara = "";

    		if( idNegeriTempatBicara != "" )
    		{
    			idNegeri = Integer.parseInt(idNegeriTempatBicara);
    			alamatTempatBicara = logic4.getAlamatMahkamah(idNegeriTempatBicara);
    			Hashtable AB = (Hashtable) alamatTempatBicara.get(0);
        		idPejabat = AB.get("id_pejabat").toString();
    		}
    			context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",Utils.parseLong(idNegeriTempatBicara),"style=width:305 onChange=\"doChangeidNegeri();\" "));
    			context.put("selectBicara",HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriTempatBicara),"socTempatBicara",Utils.parseLong(idNegeriTempatBicara),"style=width:340 onChange=\"doChangeidMahkamah();\" "));
    			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",null,"style=width:305"));

    		//list & data
    		context.put("listSemak", list);

    		//get & post all field content
    		context.put("poskod", poskod);
    		context.put("alamat1", alamat1);
    		context.put("alamat2", alamat2);
    		context.put("alamat3", alamat3);
    		context.put("txdTarikhPerakuanAdd", getParam("txdTarikhPerakuanAdd"));
    		context.put("txdTarikhBicara", getParam("txdTarikhBicara"));
    		context.put("txtMasaBicara", getParam("txtMasaBicara"));
    		context.put("jenisPerintah", jenisPerintah);
    		context.put("txtidnegeri", idNegeri);

    		if(jenisPerintah.equals("PT")){
	    		context.put("jenisPerintah", "PT");
	    	}else if(jenisPerintah.equals("PD")){
	    		context.put("jenisPerintah", "PD");
	    	}else if(jenisPerintah.equals("PDS")){
	    		context.put("jenisPerintah", "PDS");
	    	}else{
	    		context.put("jenisPerintah", "");
	    	}
    		//form validation
    		context.put("addMode", "yes");
    		context.put("viewEditMode", "no");
    		context.put("viewMode", "no");
    		context.put("editMode", "no");
    		context.put("action", "onChange");

	        vm = "app/ppk/frmPrmhnnSek8MahkamahTinggi.jsp";


		}else if("doChangeidNegeriUpdate".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
			String idkp = "";
			String id_perintah = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
				id_perintah = (String)v.get("id_perintah");
			}
				context.put("dataPerintahView", dataPerintah);
				context.put("id_perintah",id_perintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String id_perbicaraan = "";
    		if (dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
        		id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("id_perbicaraan",id_perbicaraan);

			//* GET MAKLUMAT TANGGUH
    		long idUnitPsk = 0;
			PerintahTangguh = FrmPrmhnnSek8KptsanBicaraData.setPerintahTangguh(id_perbicaraan);
			if ( PerintahTangguh.size() != 0 ){
				Hashtable b  = (Hashtable) PerintahTangguh.get(0);

				idUnitPsk = Long.parseLong(b.get("id_unitpsk").toString());
	    		String jenis_keluar_perintah = b.get("jenis_keluar_perintah").toString();
			}
				context.put("infoPerintahTangguh",PerintahTangguh);


    		String idNegeriTempatBicara = getParam("socNegeri");
    		String jenisPerintah = getParam("jenisPerintah");

    		int idNegeri = 0;
    		String alamat1 = "";
    		String alamat2 = "";
    		String alamat3 = "";
    		String poskod  = "";
    		String notel = "";
    		String nofax = "";

    		if( idNegeriTempatBicara != "" ){
    			idNegeri = Integer.parseInt(idNegeriTempatBicara);
    			alamatTempatBicara = logic4.getAlamatMahkamah(idNegeriTempatBicara);
    			Hashtable AB = (Hashtable) alamatTempatBicara.get(0);
        		String idPejabat = AB.get("id_pejabat").toString();
    		}

    		//list & data
    		context.put("listSemak", list);

    		//get & post all field content
    		context.put("txdTarikhPerakuanAdd", getParam("txdTarikhPerakuanAdd"));
    		context.put("txdTarikhBicara", getParam("txdTarikhBicara"));
    		context.put("txtMasaBicara", getParam("txtMasaBicara"));
    		context.put("jenisPerintah", jenisPerintah);
    		context.put("txtidnegeri", idNegeri);

    		context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",Utils.parseLong(idNegeriTempatBicara),"style=width:305 onChange=\"doChangeidNegeriUpdate();\" "));
    		context.put("selectBicara",HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriTempatBicara),"socTempatBicara",Utils.parseLong(idNegeriTempatBicara),"style=width:340 onChange=\"doChangeidMahkamahUpdate();\" "));
    		context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn, "socPegawai", idUnitPsk, "style=width:305"));

    		if(jenisPerintah.equals("PT")){
	    		context.put("jenisPerintah", "PT");
	    	}else if(jenisPerintah.equals("PD")){
	    		context.put("jenisPerintah", "PD");
	    	}else if(jenisPerintah.equals("PDS")){
	    		context.put("jenisPerintah", "PDS");
	    	}else{
	    		context.put("jenisPerintah", "");
	    	}

    		//form validation
    		context.put("addMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("viewMode", "no");
    		context.put("editMode", "yes");

	        vm = "app/ppk/frmPrmhnnSek8MahkamahTinggi.jsp";

		}	else if(submit.equals("doChangeidNegeriROTS")){

    		String idNegeriTempatBicara = getParam("socNegeri");
    		String jenisPerintah = getParam("jenisPerintah");

    		int idNegeri = 0;
    		String idPejabat = "";
    		String alamat1 = "";
    		String alamat2 = "";
    		String alamat3 = "";
    		String poskod  = "";
    		String negeri  = "";
    		String notel = "";
    		String nofax = "";
    		String txdTarikhPerakuanAdd  = "";
    		String txdTarikhBicara = "";

    		if( idNegeriTempatBicara != "" ){
    			idNegeri = Integer.parseInt(idNegeriTempatBicara);
    			alamatTempatBicara = logic4.getAlamatMahkamah(idNegeriTempatBicara);
    			Hashtable AB = (Hashtable) alamatTempatBicara.get(0);
        		idPejabat = AB.get("id_pejabat").toString();
    		}

    		//list & data
    		context.put("listSemak", list);

    		//get & post all field content
    		context.put("poskod", poskod);
    		context.put("alamat1", alamat1);
    		context.put("alamat2", alamat2);
    		context.put("alamat3", alamat3);
    		context.put("txdTarikhRujukanAdd", getParam("txdTarikhRujukanAdd"));
    		context.put("txtFaktaGuamanAdd", getParam("txtFaktaGuamanAdd"));
    		context.put("txtPendapatAdd", getParam("txtPendapatAdd"));
    		context.put("txtidnegeri", idNegeri);

    		context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",Utils.parseLong(idNegeriTempatBicara),"style=width:305 onChange=\"doChangeidNegeriROTS();\" "));
    		context.put("selectBicara",HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriTempatBicara),"socTempatBicara",Utils.parseLong(idNegeriTempatBicara),"style=width:340 onChange=\"doChangeidMahkamahROTS();\" "));
			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",null,"style=width:305"));

    		if(jenisPerintah.equals("PT")){
	    		context.put("jenisPerintah", "PT");
	    	}else if(jenisPerintah.equals("PD")){
	    		context.put("jenisPerintah", "PD");
	    	}else if(jenisPerintah.equals("PDS")){
	    		context.put("jenisPerintah", "PDS");
	    	}else{
	    		context.put("jenisPerintah", "");
	    	}

			context.put("tempcheckedmahkamahtinggi","checked");
			context.put("tempcheckedROTS","");
		    context.put("viewMode", "no");
    		context.put("addMode", "yes");
    		context.put("editMode", "no");
    		context.put("flag", "MT");
    		context.put("button", "");
    		context.put("action", "onChange");

	        vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}	else if(submit.equals("doChangeidNegeriROTSupdate")){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
			String idkp = "";
			String id_perintah = "";
			if (dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
				id_perintah = (String)v.get("id_perintah");
			}
				context.put("dataPerintahView", dataPerintah);
				context.put("id_perintah",id_perintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String id_perbicaraan = "";
    		if (dataNotis.size()!=0){
        		Hashtable idn = (Hashtable) dataNotis.get(0);
        		id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("id_perbicaraan",id_perbicaraan);

			//* GET MAKLUMAT TANGGUH
			PerintahTangguh = FrmPrmhnnSek8KptsanBicaraData.setPerintahTangguh(id_perbicaraan);
			long idUnitPsk = 0;
			String jenis_keluar_perintah = "";
			if ( PerintahTangguh.size() != 0 ){
				Hashtable b  = (Hashtable) PerintahTangguh.get(0);
				idUnitPsk = Long.parseLong(b.get("id_unitpsk").toString());
	    		jenis_keluar_perintah = (String) b.get("jenis_keluar_perintah");
			}
				context.put("infoPerintahTangguh",PerintahTangguh);

    		String idNegeriTempatBicara = getParam("socNegeri");
    		String jenisPerintah = getParam("jenisPerintah");
    		int idNegeri = 0;
    		String alamat1 = "";
    		String alamat2 = "";
    		String alamat3 = "";
    		String poskod  = "";
    		String notel = "";
    		String nofax = "";

    		if( idNegeriTempatBicara != "" ){
    			idNegeri = Integer.parseInt(idNegeriTempatBicara);
    			alamatTempatBicara = logic4.getAlamatMahkamah(idNegeriTempatBicara);
    			Hashtable AB = (Hashtable) alamatTempatBicara.get(0);
        		String idPejabat = AB.get("id_pejabat").toString();
    		}

    		//list & data
    		context.put("listSemak", list);

    		//get & post all field content
    		context.put("txdTarikhPerakuanAdd", getParam("txdTarikhPerakuanAdd"));
    		context.put("txdTarikhBicara", getParam("txdTarikhBicara"));
    		context.put("txtMasaBicara", getParam("txtMasaBicara"));
    		context.put("jenisPerintah", jenisPerintah);
    		context.put("txtidnegeri", idNegeri);

    		context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",Utils.parseLong(idNegeriTempatBicara),"style=width:305 onChange=\"doChangeidNegeriROTSupdate();\" "));
    		context.put("selectBicara",HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriTempatBicara),"socTempatBicara",Utils.parseLong(idNegeriTempatBicara),"style=width:340 onChange=\"doChangeidMahkamahROTSupdate();\" "));
			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn, "socPegawai", idUnitPsk, "style=width:305"));

			if(jenisPerintah.equals("PT")){
	    		context.put("jenisPerintah", "PT");
	    	}else if(jenisPerintah.equals("PD")){
	    		context.put("jenisPerintah", "PD");
	    	}else if(jenisPerintah.equals("PDS")){
	    		context.put("jenisPerintah", "PDS");
	    	}else{
	    		context.put("jenisPerintah", "");
	    	}

    		//form validation
			context.put("tempcheckedmahkamahtinggi","checked");
			context.put("tempcheckedROTS","");
		    context.put("viewMode", "no");
    		context.put("addMode", "no");
    		context.put("editMode", "yes");
    		context.put("flag", "MT");
    		context.put("button", "");

	        vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";


		}else if("doChangeidNegeriMSupdate".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
			String idkp = "";
			String id_perintah = "";
			if (dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
				id_perintah = (String)v.get("id_perintah");
			}
				context.put("dataPerintahView", dataPerintah);
				context.put("id_perintah",id_perintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("id_perbicaraan",id_perbicaraan);

			//* GET MAKLUMAT TANGGUH
    		long idUnitPsk = 0;
			PerintahTangguh = FrmPrmhnnSek8KptsanBicaraData.setPerintahTangguh(id_perbicaraan);
			if ( PerintahTangguh.size() != 0 ){
				Hashtable b  = (Hashtable) PerintahTangguh.get(0);
				idUnitPsk = Long.parseLong(b.get("id_unitpsk").toString());
	    		String jenis_keluar_perintah = (String) b.get("jenis_keluar_perintah");
			}
				context.put("infoPerintahTangguh",PerintahTangguh);

    		String idNegeriTempatBicara = getParam("socNegeri");
    		String jenisPerintah = getParam("jenisPerintah");
    		int idNegeri = 0;
    		String alamat1 = "";
    		String alamat2 = "";
    		String alamat3 = "";
    		String poskod  = "";
    		String notel = "";
    		String nofax = "";
    		String idPejabat = "";

    		if( idNegeriTempatBicara != "" )
    		{
    			idNegeri = Integer.parseInt(idNegeriTempatBicara);
    			alamatTempatBicara = logic4.getAlamatMahkamahSyariah(idNegeriTempatBicara);
    			Hashtable AB = (Hashtable) alamatTempatBicara.get(0);
        		idPejabat = AB.get("id_pejabat").toString();
    		}

    		//list & data
    		context.put("listSemak", list);

    		//get & post all field content
    		context.put("txdTarikhPerakuanAdd", getParam("txdTarikhPerakuanAdd"));
    		context.put("txdTarikhBicara", getParam("txdTarikhBicara"));
    		context.put("txtMasaBicara", getParam("txtMasaBicara"));
    		context.put("jenisPerintah", jenisPerintah);
    		context.put("txtidnegeri", idNegeri);
    		context.put("id_pejabat", idPejabat);
			context.put("selectNegeri",HTML.SelectNegeriByMahkamahSyariah("socNegeri", Utils.parseLong(idNegeriTempatBicara), " style=width:305 onChange=\"doChangeidNegeriMSupdate();\" "));
   			context.put("selectBicara",HTML.SelectMahkamahSyariahByNegeri(Utils.parseLong(idNegeriTempatBicara),"socTempatBicara",Utils.parseLong(idNegeriTempatBicara),"style=width:340 onChange=\"doChangeidMahkamahMSupdate();\" "));
   			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn, "socPegawai", idUnitPsk, "style=width:305"));

    		//form validation
			context.put("tempcheckedmahkamahtinggi","");
			context.put("tempcheckedROTS","checked");
		    context.put("viewMode", "no");
    		context.put("addMode", "no");
    		context.put("editMode", "yes");
    		context.put("flag", "ROTS");
    		context.put("button", "");
    		context.put("action", "");

	        vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";


		}else if("doChangeidNegeriMS".equals(submit)){

    		String idNegeriTempatBicara = getParam("socNegeri");
    		String jenisPerintah = getParam("jenisPerintah");

    		int idNegeri = 0;
    		String idPejabat = "";
    		String alamat1 = "";
    		context.put("alamat1", "");

    		String alamat2 = "";
    		String alamat3 = "";
    		String poskod  = "";
    		String negeri  = "";
    		String notel = "";
    		String nofax = "";
    		String txdTarikhPerakuanAdd  = "";
    		String txdTarikhBicara = "";

    		if( idNegeriTempatBicara != "" )
    		{
    			idNegeri = Integer.parseInt(idNegeriTempatBicara);
    			alamatTempatBicara = logic4.getAlamatMahkamahSyariah(idNegeriTempatBicara);
    			Hashtable AB = (Hashtable) alamatTempatBicara.get(0);
        		idPejabat = AB.get("id_pejabat").toString();
    		}

    		//list & data
    		context.put("listSemak", list);

    		//get & post all field content
    		context.put("poskod", poskod);
    		context.put("alamat1", alamat1);
    		context.put("alamat2", alamat2);
    		context.put("alamat3", alamat3);
    		context.put("txdTarikhRujukanAdd", getParam("txdTarikhRujukanAdd"));
    		context.put("txtFaktaGuamanAdd", getParam("txtFaktaGuamanAdd"));
    		context.put("txtPendapatAdd", getParam("txtPendapatAdd"));
    		context.put("txtidnegeri", idNegeri);

    		context.put("selectNegeri",HTML.SelectNegeriByMahkamahSyariah("socNegeri",Utils.parseLong(idNegeriTempatBicara),"style=width:305 onChange=\"doChangeidNegeriMS();\" "));
    		context.put("selectBicara",HTML.SelectMahkamahSyariahByNegeri(Utils.parseLong(idNegeriTempatBicara),"socTempatBicara",Utils.parseLong(idNegeriTempatBicara),"style=width:340 onChange=\"doChangeidMahkamahMS();\" "));
    		context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",null,"style=width:305"));

    		if(jenisPerintah.equals("PT")){
	    		context.put("jenisPerintah", "PT");
	    	}else if(jenisPerintah.equals("PD")){
	    		context.put("jenisPerintah", "PD");
	    	}else if(jenisPerintah.equals("PDS")){
	    		context.put("jenisPerintah", "PDS");
	    	}else{
	    		context.put("jenisPerintah", "");
	    	}

			context.put("tempcheckedmahkamahtinggi","");
			context.put("tempcheckedROTS","checked");
		    context.put("viewMode", "no");
    		context.put("addMode", "yes");
    		context.put("editMode", "no");
    		context.put("flag", "ROTS");
    		context.put("button", "");
    		context.put("action", "onChange");

	        vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";


		}else if("simpanKeputusanKolateral".equals(submit)){

		    String id_perbicaraan = getParam("id_perbicaraan");
		    this.context.put("id_perbicaraan",id_perbicaraan);

    		//get values
    		String flag_keputusan = getParam("flag_keputusan");
    		String txtBayaranPerintahKol = getParam("txtBayaranPerintahKol");
    		String txtNomborResitPerintahKol = getParam("txtNomborResitPerintahKol");
    		String txtCatatanKeputusan = getParam("txtCatatanKeputusan");
    		String txdTarikhBayaranPerintahKol = getParam("txdTarikhBayaranPerintahKol");

			//* get id_kolateralmst
			Vector getIdKolateralmst = FrmPrmhnnSek8KptsanBicaraData.getIdKolSateralmst(id_perbicaraan);
			String IdKolateralmst = "";
			if(getIdKolateralmst.size()!=0){
				Hashtable e = (Hashtable) getIdKolateralmst.get(0);
				IdKolateralmst = (String)e.get("id_kolateralmst");
			}
			//*get idbayaran
			Vector getDataKeputusan = FrmPrmhnnSek8KptsanBicaraData.setDataKeputusan(idpermohonan);
			getDataKeputusan = FrmPrmhnnSek8KptsanBicaraData.getDataKeputusan();
			String idBayaran = "";
			if (getDataKeputusan.size()!=0){
				Hashtable c = (Hashtable) getDataKeputusan.get(0);
				idBayaran = c.get("id_bayaran").toString();
				System.out.println("ID BAYARAN"+idBayaran);
			}
			context.put("dataKeputusan", getDataKeputusan);

			if (doPost.equals("true")) {

				addKeputusan_Editblppkkolateralmst( idpermohonan, IdKolateralmst, usid, flag_keputusan,
						txtBayaranPerintahKol, txtNomborResitPerintahKol, txtCatatanKeputusan, txdTarikhBayaranPerintahKol );

			//	edit_status_KeputusanKolateral( idpermohonan, usid );
			//	edit_status_tblrujsuburusanstatusfail_KeputusanKolateral( idpermohonan, idsuburusanstatusfail, idFail, usid );
			//:::SUB
				//ID_STATUS : 173
				//ID_SUBURUSAN : 776
				logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"173","776",idFail);
				
				
			}

			    FrmPrmhnnSek8KptsanBicaraData.setPerintahKolateral(idpermohonan,id_perbicaraan);
			    getrecord_perintahKolateral = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahKolateral();
			    if(getrecord_perintahKolateral.size()!=0){
					Hashtable d = (Hashtable) getrecord_perintahKolateral.get(0);
					long idUnitPsk = Long.parseLong(d.get("id_unitpsk").toString());
					long id_pejabatmahkamah = Long.parseLong(d.get("id_pejabatmahkamah").toString());
					long id_negeri = Long.parseLong(d.get("id_negeri").toString());


				context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,"disabled "));
				context.put("selectViewBicara",HTML.SelectTempatBicara("socTempatBicara",id_pejabatmahkamah," disabled "));
				context.put("selectNegeri",HTML.SelectNegeri("socNegeri",id_negeri," disabled "));
			}
				context.put("dataPerintahKolateral", getrecord_perintahKolateral);

	    		String selectedTab = "";
	    		selectedTab = "1";
	            if (selectedTab == null || "".equals(selectedTab)) {
	            	selectedTab="1";
	            }
	            context.put("selectedTab",selectedTab);

		    //call flag
		    context.put("addMode", "no");
    		context.put("editMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("viewMode", "yes");
    		context.put("listSemak", list);

		    vm = "app/ppk/frmPrmhnnKolateral.jsp";

		}else if("simpanEditKeputusanKolateral".equals(submit)){

		    String id_perbicaraan = getParam("id_perbicaraan");
		    this.context.put("id_perbicaraan",id_perbicaraan);

    		//get values
    		String flag_keputusan = getParam("flag_keputusan");
    		String txtBayaranPerintahKol = getParam("txtBayaranPerintahKol");
    		String txtNomborResitPerintahKol = getParam("txtNomborResitPerintahKol");
    		String txtCatatanKeputusan = getParam("txtCatatanKeputusan");
    		String txdTarikhBayaranPerintahKol = getParam("txdTarikhBayaranPerintahKol");

			//* get id_kolateralmst
			Vector getIdKolateralmst = FrmPrmhnnSek8KptsanBicaraData.getIdKolSateralmst(id_perbicaraan);
			String IdKolateralmst = "";
			if(getIdKolateralmst.size()!=0){
				Hashtable e = (Hashtable) getIdKolateralmst.get(0);
				IdKolateralmst = (String)e.get("id_kolateralmst");
			}

			//*get idbayaran
			Vector getDataKeputusan = FrmPrmhnnSek8KptsanBicaraData.setDataKeputusan(idpermohonan);
			getDataKeputusan = FrmPrmhnnSek8KptsanBicaraData.getDataKeputusan();
			String idBayaran = "";
			if (getDataKeputusan.size()!=0){
				Hashtable c = (Hashtable) getDataKeputusan.get(0);
				idBayaran = c.get("id_bayaran").toString();
			}
			context.put("dataKeputusan", getDataKeputusan);

			Vector getDataKeputusanBayaran = FrmPrmhnnSek8KptsanBicaraData.setDataKeputusanBayaran(id_perbicaraan);
			getDataKeputusanBayaran = FrmPrmhnnSek8KptsanBicaraData.getDataKeputusanBayaran();
			if (getDataKeputusan.size()!=0){
				Hashtable bayaran = (Hashtable) getDataKeputusanBayaran.get(0);
			}
			context.put("dataKeputusanBayaran", getDataKeputusanBayaran);

				updateKeputusan_tblppkkolateralmst( idpermohonan, IdKolateralmst, usid, flag_keputusan,
						txtBayaranPerintahKol, txtNomborResitPerintahKol, txtCatatanKeputusan, txdTarikhBayaranPerintahKol, idBayaran );

			    FrmPrmhnnSek8KptsanBicaraData.setPerintahKolateral(idpermohonan,id_perbicaraan);
			    getrecord_perintahKolateral = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahKolateral();
			    if(getrecord_perintahKolateral.size()!=0){
					Hashtable d = (Hashtable) getrecord_perintahKolateral.get(0);
					long idUnitPsk = Long.parseLong(d.get("id_unitpsk").toString());
					long id_pejabatmahkamah = Long.parseLong(d.get("id_pejabatmahkamah").toString());
					long id_negeri = Long.parseLong(d.get("id_negeri").toString());

				context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,"disabled "));
				context.put("selectViewBicara",HTML.SelectTempatBicara("socTempatBicara",id_pejabatmahkamah," disabled "));
				context.put("selectNegeri",HTML.SelectNegeri("socNegeri",id_negeri," disabled "));
			}
				context.put("dataPerintahKolateral", getrecord_perintahKolateral);

				getDataKeputusan = FrmPrmhnnSek8KptsanBicaraData.setDataKeputusan(idpermohonan);
				getDataKeputusan = FrmPrmhnnSek8KptsanBicaraData.getDataKeputusan();
				if (getDataKeputusan.size()!=0){
					Hashtable c = (Hashtable) getDataKeputusan.get(0);
					idBayaran = c.get("id_bayaran").toString();
				}
				context.put("dataKeputusan", getDataKeputusan);

				getDataKeputusanBayaran = FrmPrmhnnSek8KptsanBicaraData.setDataKeputusanBayaran(id_perbicaraan);
				getDataKeputusanBayaran = FrmPrmhnnSek8KptsanBicaraData.getDataKeputusanBayaran();
				if (getDataKeputusan.size()!=0){
					Hashtable bayaran = (Hashtable) getDataKeputusanBayaran.get(0);

				}
				context.put("dataKeputusanBayaran", getDataKeputusanBayaran);

	    		String selectedTab = "";
	    		selectedTab = "1";
	            if (selectedTab == null || "".equals(selectedTab)){
	            	selectedTab="1";
	            }
	            context.put("selectedTab",selectedTab);

		    //call flag
		    context.put("addMode", "no");
    		context.put("editMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("viewMode", "yes");
    		context.put("listSemak", list);
    		context.put("tab", "keputusan");

		    vm = "app/ppk/frmPrmhnnKolateral.jsp";

		}else if("simpan_editkolateral".equals(submit)){

		    String id_perbicaraan = getParam("id_perbicaraan");
		    this.context.put("id_perbicaraan",id_perbicaraan);

		    //* get values
		    String txdTarikhPerakuanAdd = getParam("txdTarikhPerakuanAdd");
		    String EDITsocPegawaiPengendali = getParam("socPegawai");
		    String txdTarikhBicara = getParam("txdTarikhBicara");
		    String txtMasaBicara = getParam("txtMasaBicara");
		    String socTempatBicara = getParam("socTempatBicara");
		    String socNegeri = getParam("socNegeri");
		    String txtCatatanAdd = getParam("txtCatatanAdd");

			//* checking wujud id_perintah atau tidak
			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			String id_perintah = "";
			if ( getIdPerintah.size() != 0 ){
				Hashtable a = (Hashtable) getIdPerintah.get(0);
				id_perintah = (String)a.get("id_perintah");

				//* get id_kolateralmst
				Vector getIdKolateralmst = FrmPrmhnnSek8KptsanBicaraData.getIdKolSateralmst(id_perbicaraan);
				String IdKolateralmst = "";
				if(getIdKolateralmst.size()!=0){
					Hashtable e = (Hashtable) getIdKolateralmst.get(0);
					IdKolateralmst = (String)e.get("id_kolateralmst");
				}

				updateKolateral_updateTblppkperintah( id_perbicaraan, id_perintah, usid, IdKolateralmst, txdTarikhPerakuanAdd, EDITsocPegawaiPengendali, txdTarikhBicara,
						txtMasaBicara, socTempatBicara, socNegeri, txtCatatanAdd );

				if (doPost.equals("true")) {
					deleteWarisTerdahuluKolateral( IdKolateralmst );
					update_tblppkkolateraldtl( IdKolateralmst, usid );
					//edit_status_tangguhKolateral(idpermohonan,idsuburusanstatusfail,usid);
					//edit_status_tblrujsuburusanstatusfail_TangguhKolateral(idpermohonan,usid,
					//		id_perbicaraan,idsuburusanstatusfail,idFail);
					//:::SUB
					//ID_STATUS : 172
					//ID_SUBURUSAN : 775
					logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"172","775",idFail);
					
					
				}
			}

			//checking id_kolateralmst
			Vector getIdKolateralmst = FrmPrmhnnSek8KptsanBicaraData.getIdKolSateralmst(id_perbicaraan);
			String IdKolateralmst = "";
			if ( getIdKolateralmst.size() != 0 ){
				Hashtable e = (Hashtable) getIdKolateralmst.get(0);
				IdKolateralmst = (String)e.get("id_kolateralmst");

			    FrmPrmhnnSek8KptsanBicaraData.setPerintahKolateral(idpermohonan,id_perbicaraan);
			    getrecord_perintahKolateral = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahKolateral();
			    long idUnitPsk = 0;
			    long id_pejabatmahkamah = 0;
			    long id_negeri = 0;
			    if(getrecord_perintahKolateral.size()!=0){
					Hashtable d = (Hashtable) getrecord_perintahKolateral.get(0);
					idUnitPsk = Long.parseLong(d.get("id_unitpsk").toString());

					if(idUnitPsk != 0){
						context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,"disabled "));
					}else{
						context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",null,"disabled "));
					}

					id_pejabatmahkamah = Long.parseLong(d.get("id_pejabatmahkamah").toString());
					id_negeri = Long.parseLong(d.get("id_negeri").toString());
				}
				//context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,"disabled "));
			    context.put("selectViewBicara",HTML.SelectTempatBicara("socTempatBicara",id_pejabatmahkamah," disabled "));
				context.put("selectNegeri",HTML.SelectNegeri("socNegeri",id_negeri," disabled "));

				//checking ada senarai waris ke tidak
				Vector senarai_warisPlanitif = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWarisPlanitif(id_perbicaraan,getParam("id_permohonansimati_atheader"));
				context.put("dataWarisPlanitif", senarai_warisPlanitif);

				Vector senarai_warisDefendan = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWarisDefendan(id_perbicaraan,getParam("id_permohonansimati_atheader"));
				context.put("dataWarisDefendan", senarai_warisDefendan);

				context.put("viewMode", "yes");
	    		context.put("addMode", "no");
	    		context.put("editMode", "no");
	    		context.put("viewEditMode", "no");
	    		context.put("listSemak", list);
			}
				context.put("dataPerintahKolateral", getrecord_perintahKolateral);

	    		String selectedTab = "";
	    		selectedTab = "0";
	            if (selectedTab == null || "".equals(selectedTab)) {
	            	selectedTab="0";
	            }
	            context.put("selectedTab",selectedTab);

		    //call flag
		    context.put("addMode", "no");
    		context.put("editMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("viewMode", "yes");
    		context.put("listSemak", list);

		    vm = "app/ppk/frmPrmhnnKolateral.jsp";

		}else if("Skrin_EditKolateral".equals(submit)){

				//GET ID_KEPUTUSAN PERMOHONAN
				FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
				dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
				String idkp = "";
				String id_perintah = "";
				if(dataPerintah.size()!=0){
					Hashtable v = (Hashtable) dataPerintah.get(0);
					idkp = (String)v.get("id_keputusanpermohonan");
					id_perintah = (String)v.get("id_perintah");
				}
					context.put("dataPerintahView", dataPerintah);

		    	//-- DATA NOTIS
				logic4.setListSemakWithData(idkp);
	    		dataNotis = logic4.getListSemakWithData();
	    		String id_perbicaraan = "";
	    		if(dataNotis.size()!=0){
	    			Hashtable idn = (Hashtable) dataNotis.get(0);
	    			id_perbicaraan = (String)idn.get("id_perbicaraan");
	    		}
	    			context.put("id_perbicaraan",id_perbicaraan);

	    		//* GET INFO KOLATERAL
	    		FrmPrmhnnSek8KptsanBicaraData.setPerintahKolateral(idpermohonan,id_perbicaraan);
	    		getrecord_perintahKolateral = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahKolateral();
	    		long idUnitPsk = 0;
	    		long id_negeri = 0;
	    		long id_pejabatmahkamah = 0;
	    		if ( getrecord_perintahKolateral.size() != 0 ){
					Hashtable d = (Hashtable) getrecord_perintahKolateral.get(0);
					idUnitPsk = Long.parseLong(d.get("id_unitpsk").toString());
					id_pejabatmahkamah = Long.parseLong(d.get("id_pejabatmahkamah").toString());
					id_negeri = Long.parseLong(d.get("id_negeri").toString());

		    		if(idNegeriMhn!=""){
		    			context.put("selectBicara",HTML.SelectTempatBicaraByPejabatJKPTG(idPejabatJKPTG,"socTempatBicara",id_pejabatmahkamah,null,"style=width:340 onChange=\"doChangeidTempatBicaraUpdate();\" "));
		    			context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",idUnitPsk,"style=width:305"));
		            }else{
		            	context.put("selectBicara",HTML.SelectTempatBicaraByPejabatJKPTG(idPejabatJKPTG,"socTempatBicara",null,null,"style=width:340 onChange=\"doChangeidTempatBicaraUpdate();\" "));
		            	context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",null,"style=width:305"));
		            }
		    			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",id_negeri," disabled "));
	    		}
	    			context.put("dataPerintahKolateral", getrecord_perintahKolateral);

	        		String selectedTab = "";
	        		selectedTab = "0";
	                if (selectedTab == null || "".equals(selectedTab)){
	                	selectedTab="0";
	                }
	                context.put("selectedTab",selectedTab);

	    		//* get data OB
	    		OBList = logic4.getObList(id_perbicaraan,getParam("id_permohonansimati_atheader"));
	    		this.context.put("OBList", OBList);

				//* call flag
	    		context.put("viewEditMode", "no");
				context.put("addMode", "no");
	    		context.put("viewMode", "no");
	    		context.put("editMode", "yes");
	    		context.put("tab","");

	    		vm = "app/ppk/frmPrmhnnKolateral.jsp";

		}else if("Skrin_EditKeputusanKolateral".equals(submit)){

			//GET ID_KEPUTUSAN PERMOHONAN
			FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
			String idkp = "";
			String id_perintah = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
				id_perintah = (String)v.get("id_perintah");
			}
				context.put("dataPerintahView", dataPerintah);

	    	//-- DATA NOTIS
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("id_perbicaraan",id_perbicaraan);

    		//* GET INFO KOLATERAL
    		FrmPrmhnnSek8KptsanBicaraData.setPerintahKolateral(idpermohonan,id_perbicaraan);
    		getrecord_perintahKolateral = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahKolateral();
    		long idUnitPsk = 0;
    		long id_negeri = 0;
    		long id_pejabatmahkamah = 0;
    		if ( getrecord_perintahKolateral.size() != 0 ){
				Hashtable d = (Hashtable) getrecord_perintahKolateral.get(0);
				idUnitPsk = Long.parseLong(d.get("id_unitpsk").toString());
				id_pejabatmahkamah = Long.parseLong(d.get("id_pejabatmahkamah").toString());
				id_negeri = Long.parseLong(d.get("id_negeri").toString());

	    		if(idNegeriMhn!=""){
	    			context.put("selectBicara",HTML.SelectTempatBicaraByPejabatJKPTG(idPejabatJKPTG,"socTempatBicara",id_pejabatmahkamah,null,"style=width:340 onChange=\"doChangeidTempatBicaraUpdate();\" "));
	    			context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",idUnitPsk,"style=width:305"));
	            }else{
	            	context.put("selectBicara",HTML.SelectTempatBicaraByPejabatJKPTG(idPejabatJKPTG,"socTempatBicara",null,null,"style=width:340 onChange=\"doChangeidTempatBicaraUpdate();\" "));
	            	context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",null,"style=width:305"));
	            }
	    			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",id_negeri," disabled "));
    		}
    			context.put("dataPerintahKolateral", getrecord_perintahKolateral);

    		//* get data OB
    		OBList = logic4.getObList(id_perbicaraan,getParam("id_permohonansimati_atheader"));
    		this.context.put("OBList", OBList);

    		String selectedTab = "";
            selectedTab = "1";
            if (selectedTab == null || "".equals(selectedTab)) {
            	selectedTab="1";
              }
            context.put("selectedTab",selectedTab);

			Vector getDataKeputusan = FrmPrmhnnSek8KptsanBicaraData.setDataKeputusan(idpermohonan);
			getDataKeputusan = FrmPrmhnnSek8KptsanBicaraData.getDataKeputusan();
			if (getDataKeputusan.size()!=0){
				Hashtable c = (Hashtable) getDataKeputusan.get(0);
				String idBayaran = c.get("id_bayaran").toString();
			}
			context.put("dataKeputusan", getDataKeputusan);

			Vector getDataKeputusanBayaran = FrmPrmhnnSek8KptsanBicaraData.setDataKeputusanBayaran(id_perbicaraan);
			getDataKeputusanBayaran = FrmPrmhnnSek8KptsanBicaraData.getDataKeputusanBayaran();
			if (getDataKeputusan.size()!=0){
				Hashtable bayaran = (Hashtable) getDataKeputusanBayaran.get(0);

			}
			context.put("dataKeputusanBayaran", getDataKeputusanBayaran);

			//* call flag
    		context.put("viewEditMode", "no");
			context.put("addMode", "no");
    		context.put("viewMode", "no");
    		context.put("editMode", "yes");
    		context.put("tab", "");

    		vm = "app/ppk/frmPrmhnnKolateral.jsp";

		}else if("PertelingkahanKolateralEdit".equals(submit)){

		    String id_perbicaraan = getParam("id_perbicaraan");
		    this.context.put("id_perbicaraan", id_perbicaraan);

    		String selectedTab = "";
            selectedTab = "0";
            if (selectedTab == null || "".equals(selectedTab)) {
            	selectedTab="0";
              }
            context.put("selectedTab",selectedTab);

					//checking id_kolateralmst
					Vector getIdKolateralmst = FrmPrmhnnSek8KptsanBicaraData.getIdKolSateralmst(id_perbicaraan);
					String IdKolateralmst = "";
					if ( getIdKolateralmst.size() != 0 ){
						Hashtable e = (Hashtable) getIdKolateralmst.get(0);
						IdKolateralmst = (String)e.get("id_kolateralmst");

					    FrmPrmhnnSek8KptsanBicaraData.setPerintahKolateral(idpermohonan,id_perbicaraan);
					    getrecord_perintahKolateral = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahKolateral();
					    long idUnitPsk = 0;
					    long id_pejabatmahkamah = 0;
					    long id_negeri = 0;
					    if(getrecord_perintahKolateral.size()!=0){
					    	Hashtable d = (Hashtable) getrecord_perintahKolateral.get(0);
					    	idUnitPsk = Long.parseLong(d.get("id_unitpsk").toString());

					    	if (idUnitPsk != 0){
					    		context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk," disabled "));
					    	}else{
					    		context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",null," disabled "));
					    	}

							id_pejabatmahkamah = Long.parseLong(d.get("id_pejabatmahkamah").toString());
							id_negeri = Long.parseLong(d.get("id_negeri").toString());
					    }
							context.put("dataPerintahKolateral", getrecord_perintahKolateral);

						//context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk," disabled "));
						context.put("selectViewBicara",HTML.SelectTempatBicaraByPejabatJKPTG(idPejabatJKPTG,"socTempatBicara",id_pejabatmahkamah," disabled "));
						context.put("selectNegeri",HTML.SelectNegeri("socNegeri",id_negeri," disabled "));

						//checking ada senarai waris ke tidak
						Vector senarai_warisPlanitif = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWarisPlanitif(id_perbicaraan,getParam("id_permohonansimati_atheader"));
						context.put("dataWarisPlanitif", senarai_warisPlanitif);

						Vector senarai_warisDefendan = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWarisDefendan(id_perbicaraan,getParam("id_permohonansimati_atheader"));
						context.put("dataWarisDefendan", senarai_warisDefendan);

						context.put("viewMode", "yes");
			    		context.put("addMode", "no");
			    		context.put("editMode", "no");
			    		context.put("viewEditMode", "no");
			    		context.put("flag", "onChange");
			    		context.put("tab", "");

					}else{

						Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);//.setInfoBicara(id,id_perbicaraan);
						long idUnitPsk = 0;
						if(h.size()!=0){
							idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
						}
							context.put("dataPerbicaraan", h);

						context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",idUnitPsk,"disabled class=autoselect"));
						context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"style=width:310px"));
						context.put("selectViewBicara",HTML.SelectTempatBicara("socTempatBicara",null,null,"style=width:310px onChange=\"doChangeidTempatBicara();\" "));

						String idTempatBicara = getParam("socTempatBicara");
			    		int idBicara = 0;
			    		String alamat1 = "";
			    		this.context.put("alamat1", "");
			    		String alamat2 = "";
			    		this.context.put("alamat2", "");
			    		String alamat3 = "";
			    		this.context.put("alamat3", "");
			    		String poskod  = "";
			    		this.context.put("poskod", "");
						String txdTarikhPerakuanAdd = "";
						this.context.put("txdTarikhPerakuanAdd", "");
						String txdTarikhBicara = "";
						this.context.put("txdTarikhBicara", "");
						String txtCatatanAdd = "";
						this.context.put("txtCatatanAdd", "");

			    		//dropdown
			    		if(idPejabatJKPTG!=""){
			    			context.put("selectBicara",HTML.SelectTempatBicaraByPejabatJKPTG(idPejabatJKPTG,"socTempatBicara",null,null,"style=width:340 onChange=\"doChangeidTempatBicara();\" "));
			    			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",null,"style=width:305"));
			            }else{
			    			context.put("selectBicara",HTML.SelectTempatBicara("socTempatBicara",null,null,"style=width:340 onChange=\"doChangeidTempatBicara();\" "));
			    			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",null,"style=width:305"));
			            }
			    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"class=disabled disabled style=width:305"));

						senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris(idpermohonan,getParam("id_permohonansimati_atheader"));
						if ( senarai_waris.size() != 0 ){
							Hashtable a  = (Hashtable) senarai_waris.get(0);

							context.put("addMode", "yes");
				    		context.put("viewMode", "no");
				    		context.put("editMode", "no");
				    		context.put("viewEditMode", "no");
				    		context.put("tab", "");
						}
							context.put("dataListWaris", senarai_waris);
					}

					vm = "app/ppk/frmPrmhnnKolateral.jsp";

		}else if("RulerOfTheStateEdit".equals(submit)){

		    String id_perbicaraan = getParam("id_perbicaraan");
		    this.context.put("id_perbicaraan", id_perbicaraan);

			//checking id_borangj
			Hashtable getIdBorangJ = FrmPrmhnnSek8KptsanBicaraData.getIdBorangJ(id_perbicaraan);
			String idBorangJ = "";
					if ( getIdBorangJ.size() > 0 ){
						idBorangJ = (String)getIdBorangJ.get("id_borangj");

						//* GET ID_PERINTAH
						getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
						String id_perintah = "";
						if ( getIdPerintah.size() != 0 ){
							Hashtable e = (Hashtable) getIdPerintah.get(0);
							id_perintah = (String)e.get("id_perintah");
						}

						//* GET MAKLUMAT TANGGUH ROTS
						PerintahTangguhROTS = logic4.setTangguhROTS(id_perbicaraan,id_perintah,idBorangJ);
						long idUnitPsk = 0;
						String jenis_rujukan = "";
						String idNegeriMahkamah = "";
						String idMahkamah = "";
						if ( PerintahTangguhROTS.size() != 0){
							Hashtable b = (Hashtable) PerintahTangguhROTS.get(0);
							idUnitPsk = Long.parseLong(b.get("id_unitpsk").toString());
				    		jenis_rujukan = b.get("jenis_rujukan").toString();
				    		idNegeriMahkamah = b.get("id_negerimahkamah").toString();
				    		idMahkamah = b.get("id_mahkamah").toString();
				    		if (b.get("jenis_rujukan").equals("1")){
								checkedMahkamahTinggiROTS = "checked";
								checkedROTS = "";
								context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",Utils.parseLong(idNegeriMahkamah),"disabled style=width:305 onChange=\"doChangeidNegeriUpdate();\" "));
								context.put("selectBicara",HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriMahkamah),"socTempatBicara",Utils.parseLong(idMahkamah),"disabled style=width:375 "));
								context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn, "socPegawai", idUnitPsk, "disabled style=width:305"));
				    		}
				    		if (b.get("jenis_rujukan").equals("2") ){
								checkedMahkamahTinggiROTS = "";
								checkedROTS = "checked";
								checkedMS = "checked";
								checkedPejMufti = "";

								context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeriMahkamah),"disabled style=width:305 "));
								context.put("selectBicara", HTML.SelectMahkamahSyariahByNegeri(Utils.parseLong(idNegeriMahkamah),"socTempatBicara",Utils.parseLong(idMahkamah),"disabled style=width:375 "));
								context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",idUnitPsk," disabled", " class=\"disabled\" style=width:305"));
								context.put("txtidnegeri",idNegeriMahkamah);
							}
							context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggiROTS);
							context.put("TEMPcheckedROTS",checkedROTS);
							context.put("tempcheckedms",checkedMS);
							context.put("tempcheckedpejmufti",checkedPejMufti);
						}
							context.put("infoPerintahTangguhROTS", PerintahTangguhROTS);

						//* SENARAIKAN MAKLUMAT WARIS
						logic4.setMaklumatWaris(idBorangJ, id_perbicaraan,getParam("id_permohonansimati_atheader"));
						MaklumatWaris = logic4.getMaklumatWaris();
						if ( MaklumatWaris.size() != 0 ){
							this.context.put("SenaraiWaris",MaklumatWaris);
						}else {
							this.context.put("SenaraiWaris","");
						}

						// GET DATA KEPUTUSAN
						PermohonanROTSkeputusan = FrmPrmhnnSek8KptsanBicaraData.setROTSkeputusan(id_perbicaraan);
						if ( PermohonanROTSkeputusan.size() != 0 ){
							Hashtable c = (Hashtable) PermohonanROTSkeputusan.get(0);
							this.context.put("infoPermohonanROTSkeputusan", PermohonanROTSkeputusan);
						}else{
							this.context.put("infoPermohonanROTSkeputusan", "");
						}

						//* GET UPLOAD FILE
						logic4.setUploadFail(idFail);
						getFailUpload = logic4.getFailUpload();
						if ( getFailUpload.size() != 0 ){
						this.context.put("SenaraiLampiran",getFailUpload);
						}else {
							this.context.put("SenaraiLampiran","");
						}

						PerintahTangguhROTS = logic4.setTangguhROTS(id_perbicaraan,id_perintah,idBorangJ);
						if ( PerintahTangguhROTS.size() != 0 ){
							Hashtable b = (Hashtable) PerintahTangguhROTS.get(0);
							if (b.get("jenis_rujukan").equals("1")){	//----> MAHKAMAH TINGGI
							    // CALL FLAG
								context.put("viewMode", "no");
					    		context.put("addMode", "no");
					    		context.put("editMode", "no");
					    		context.put("viewEditMode", "yes");
					    		context.put("tab", "permohonan");
					    		context.put("tab", "keputusan");
					    		context.put("action", "onChange2");
					    		context.put("flag", "MT");
					    		context.put("jenispejabat", "");

							}else{

					    		if(b.get("flag_rujukan").equals("1")){	//---> MAHKAMAH SYARIAH

					    			context.put("viewMode", "no");
						    		context.put("addMode", "no");
						    		context.put("editMode", "no");
						    		context.put("viewEditMode", "yes");
						    		context.put("action", "onChange2");
						    		context.put("flag", "ROTS");
						    		context.put("jenispejabat", "syariah");
						    		context.put("tab", "permohonan");
						    		context.put("tab", "keputusan");

					    		}
					    		if(b.get("flag_rujukan").equals("2")){
					    			//---> PEJABAT MUFTI
					    			context.put("viewMode", "no");
						    		context.put("addMode", "no");
						    		context.put("editMode", "no");
						    		context.put("viewEditMode", "yes");
						    		context.put("action", "onChange2");
						    		context.put("flag", "ROTS");
						    		context.put("jenispejabat", "pejmufti");
						    		context.put("tab", "permohonan");
						    		context.put("tab", "keputusan");
					    		}
							}

						}else{

							//* get maklumat pejabat mufti
							PermohonanMufti = FrmPrmhnnSek8KptsanBicaraData.setMufti(idBorangJ,id_perbicaraan);
							String flag_rujukan = "";
							if ( PermohonanMufti.size() != 0 ){
								Hashtable c = (Hashtable) PermohonanMufti.get(0);
								jenis_rujukan = c.get("jenis_rujukan").toString();
								if (c.get("jenis_rujukan").equals("1")){
									checkedMahkamahTinggiROTS = "checked";
									checkedROTS = "";
								} else {
									checkedMahkamahTinggiROTS = "";
									checkedROTS = "checked";
								}
								context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggiROTS);
								context.put("TEMPcheckedROTS",checkedROTS);

								//* flag_rujukan 1: Mahkamah Syariah || 2: Pejabat Mufti
								flag_rujukan = c.get("flag_rujukan").toString();
								if( c.get("flag_rujukan").equals("1") ){
									checkedROTSMahkamahSyariah = "checked";
									checkedROTSPejabatMufti = "";
								}else{
									checkedROTSMahkamahSyariah = "";
									checkedROTSPejabatMufti = "checked";
								}
								context.put("tempcheckedms",checkedROTSMahkamahSyariah );
								context.put("tempcheckedpejmufti", checkedROTSPejabatMufti);

								//drop down
								String idNegeri = c.get("id_negeri").toString();
								String idBandar = c.get("id_bandar").toString();
								context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"disabled style=width:305 "));
								context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(idBandar),"disabled style=width:305 "));

								//* GET MAKLUMAT TANGGUH
								PerintahTangguhMufti = FrmPrmhnnSek8KptsanBicaraData.setPerintahTangguhMufti(id_perbicaraan);
								if ( PerintahTangguhMufti.size() != 0 ){
									Hashtable d  = (Hashtable) PerintahTangguhMufti.get(0);
									idUnitPsk = Long.parseLong(d.get("id_unitpsk").toString());
								}
								context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",idUnitPsk," disabled", " class=\"disabled\" style=width:355"));

								// CALL FLAG
								context.put("viewMode", "no");
					    		context.put("addMode", "no");
					    		context.put("editMode", "no");
					    		context.put("viewEditMode", "yes");
					    		context.put("action", "onChangeMufti");
					    		context.put("flag", "ROTS");
					    		context.put("jenispejabat", "pejmufti");
					    		context.put("tab", "permohonan");
					    		context.put("tab", "keputusan");
							}
								context.put("infoPermohonanMufti", PermohonanMufti);
						}

					}else {

						//clear text input
						this.context.put("txdTarikhHantarAdd", "");
						this.context.put("tarikh_terima_borangj", "");
						this.context.put("txtKeputusanAdd", "");
						this.context.put("txtCatatanAdd", "");
						this.context.put("tempcheckedmahkamahtinggi", "");
						this.context.put("tempcheckedROTS","");
						this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"));

						//* GET INFO PERBICARAAN
						getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
						long idUnitPsk = 0;
						if ( getrecord_infoperbicaraan.size() != 0 ){
							Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
							idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
						}
							context.put("dataPerbicaraan", getrecord_infoperbicaraan);
							context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",idUnitPsk,"disabled"));

						//GET SENARAI WARIS
						senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris(idpermohonan,getParam("id_permohonansimati_atheader"));
						if ( senarai_waris.size() != 0 ){
							Hashtable a = (Hashtable) senarai_waris.get(0);
							this.context.put("dataListWaris", senarai_waris);
						}else{
							this.context.put("dataListWaris", "");
						}

						// CALL FLAG
						context.put("addMode", "yes");
						context.put("viewMode", "no");
						context.put("viewEditMode", "no");
						context.put("editMode", "no");
						context.put("button", "kembali");
					}

    		vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("refreshTambahMufti".equals(submit)){

		    String id_perbicaraan = getParam("id_perbicaraan");
		    this.context.put("id_perbicaraan", id_perbicaraan);

			//CHECKING ID_BORANGJ
			Hashtable getIdBorangJ = FrmPrmhnnSek8KptsanBicaraData.getIdBorangJ(id_perbicaraan);
			String idBorangJ = "";
					if ( getIdBorangJ.size() > 0 ){
						idBorangJ = (String)getIdBorangJ.get("id_borangj");
						//* GET ID_PERINTAH
						getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
						String id_perintah = "";
						if ( getIdPerintah.size() != 0 ){
							Hashtable e = (Hashtable) getIdPerintah.get(0);
							id_perintah = (String)e.get("id_perintah");
						}

						//* GET INFO TANGGUH ROTS
						PerintahTangguhROTS = logic4.setTangguhROTS(id_perbicaraan,id_perintah,idBorangJ);
						long idUnitPsk = 0;
						String jenis_rujukan = "";
						String idNegeriMahkamah = "";
						String idMahkamah = "";
						if ( PerintahTangguhROTS.size() != 0 ){
							Hashtable b = (Hashtable) PerintahTangguhROTS.get(0);
							idUnitPsk = Long.parseLong(b.get("id_unitpsk").toString());
				    		jenis_rujukan = b.get("jenis_rujukan").toString();
				    		idNegeriMahkamah = b.get("id_negerimahkamah").toString();
				    		idMahkamah = b.get("id_mahkamah").toString();
							if (b.get("jenis_rujukan").equals("1")){
								checkedMahkamahTinggiROTS = "checked";
								checkedROTS = "";
							} else if (b.get("jenis_rujukan").equals("2")){
								checkedMahkamahTinggiROTS = "";
								checkedROTS = "checked";
							}
							context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggiROTS);
							context.put("TEMPcheckedROTS",checkedROTS);
						}
							context.put("infoPerintahTangguhROTS", PerintahTangguhROTS);

			       		//dropdown
						context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",Utils.parseLong(idNegeriMahkamah),"disabled style=width:305 onChange=\"doChangeidNegeriUpdate();\" "));
						context.put("selectBicara",HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriMahkamah),"socTempatBicara",Utils.parseLong(idMahkamah),"disabled style=width:340 onChange=\"doChangeidMahkamahUpdate();\" "));
						context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn, "socPegawai", idUnitPsk, "disabled style=width:305"));

						//* SENARAIKAN MAKLUMAT WARIS
						logic4.setMaklumatWaris(idBorangJ, id_perbicaraan,getParam("id_permohonansimati_atheader"));
						MaklumatWaris = logic4.getMaklumatWaris();
						if ( MaklumatWaris.size() != 0 ){
							this.context.put("SenaraiWaris",MaklumatWaris);
						}else {
							this.context.put("SenaraiWaris","");
						}

						//* GET MAKLUMAT KEPUTUSAN
						PermohonanROTSkeputusan = FrmPrmhnnSek8KptsanBicaraData.setROTSkeputusan(id_perbicaraan);
						if ( PermohonanROTSkeputusan.size() != 0 ){
							Hashtable c = (Hashtable) PermohonanROTSkeputusan.get(0);
							this.context.put("infoPermohonanROTSkeputusan", PermohonanROTSkeputusan);
						}else{
							this.context.put("infoPermohonanROTSkeputusan", "");
						}

						//* GET UPLOAD FILE
						logic4.setUploadFail(idFail);
						getFailUpload = logic4.getFailUpload();
						if ( getFailUpload.size() != 0 ){
							this.context.put("SenaraiLampiran",getFailUpload);
						}else {
							this.context.put("SenaraiLampiran","");
						}

					    //* CALL FLAG
						context.put("viewMode", "no");
			    		context.put("addMode", "no");
			    		context.put("editMode", "yes");
			    		context.put("viewEditMode", "no");
			    		context.put("action", "onChange");
			    		context.put("jenispejabat", "");
			    		context.put("tab", "keputusan");

					}else {
						//clear text input
						this.context.put("txdTarikhHantarAdd", "");
						this.context.put("tarikh_terima_borangj", "");
						this.context.put("txtKeputusanAdd", "");
						this.context.put("txtCatatanAdd", "");
						this.context.put("tempcheckedmahkamahtinggi", "");
						this.context.put("tempcheckedROTS","checked");

						//* GET INFO PERBICARAAN
						getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
						long idUnitPsk = 0;
						if ( getrecord_infoperbicaraan.size() != 0 ){
							Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
							idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
						}
							context.put("dataPerbicaraan", getrecord_infoperbicaraan);

						//* GET MAKLUMAT TANGGUH
						PerintahTangguh = FrmPrmhnnSek8KptsanBicaraData.setPerintahTangguh(id_perbicaraan);
						if ( PerintahTangguh.size() != 0 ){
							Hashtable c  = (Hashtable) PerintahTangguh.get(0);
							idUnitPsk = Long.parseLong(c.get("id_unitpsk").toString());
				    		String jenis_keluar_perintah = c.get("jenis_keluar_perintah").toString();
						}
							context.put("infoPerintahTangguh",PerintahTangguh);

						context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",idUnitPsk,"class=autoselect"));
						this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"));

						//* CHECKING SENARAI WARIS
						senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris(idpermohonan,getParam("id_permohonansimati_atheader"));
						if ( senarai_waris.size() != 0){
							Hashtable a = (Hashtable) senarai_waris.get(0);
							this.context.put("dataListWaris", senarai_waris);
						}else{
							this.context.put("dataListWaris", "");
						}

						//CALL FLAG
						context.put("addMode", "yes");
						context.put("viewMode", "no");
						context.put("viewEditMode", "no");
						context.put("editMode", "no");
						context.put("button", "");
						context.put("jenispejabat", "");
						context.put("tab", "keputusan");
					}

    		vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";

		}else if("PertelingkahanKolateral".equals(submit)){

			String selectedTab = "0";
            if (selectedTab == null || "".equals(selectedTab)){
            	selectedTab="0";
            }
            context.put("selectedTab",selectedTab);

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}
				context.put("dataPerintahView", dataPerintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String tarikh_bicara = "";
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			tarikh_bicara = (String)idn.get("tarikh_bicara");
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("tarikh_bicara",tarikh_bicara);
    			context.put("id_perbicaraan",id_perbicaraan);

    		//* GET INFO PERBICARAAN
    		getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
    		long idUnitPsk = 0;
    		if ( getrecord_infoperbicaraan.size() != 0 ){
    			Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
    			idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
    		}
    			context.put("dataPerbicaraan", getrecord_infoperbicaraan);

    		String idTempatBicara = getParam("socTempatBicara");
    		int idBicara = 0;
    		String alamat1 = "";
    		this.context.put("alamat1", "");
    		String alamat2 = "";
    		this.context.put("alamat2", "");
    		String alamat3 = "";
    		this.context.put("alamat3", "");
    		String poskod  = "";
    		this.context.put("poskod", "");
    		String txdTarikhPerakuanAdd = "";
    		this.context.put("txdTarikhPerakuanAdd", "");
    		String txdTarikhBicara = "";
    		this.context.put("txdTarikhBicara", "");
    		String txtMasaBicara = "";
    		this.context.put("txtMasaBicara", "");
    		String txtCatatanAdd = "";
    		this.context.put("txtCatatanAdd", "");

    		//dropdown
    		if(idPejabatJKPTG!=""){
    			context.put("selectBicara",HTML.SelectTempatBicaraByPejabatJKPTG(idPejabatJKPTG,"socTempatBicara",null,null,"style=width:340 onChange=\"doChangeidTempatBicara();\" "));
    			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",null,"style=width:305"));
            }else{
    			context.put("selectBicara",HTML.SelectTempatBicara("socTempatBicara",null,null,"style=width:340 onChange=\"doChangeidTempatBicara();\" "));
    			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",null,"style=width:305"));
            }
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"class=disabled disabled style=width:305"));

			//* GET SENARAI WARIS
			senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris(idpermohonan,getParam("id_permohonansimati_atheader"));
			if ( senarai_waris.size() != 0 ){
				Hashtable a  = (Hashtable) senarai_waris.get(0);
				this.context.put("dataListWaris", senarai_waris);
			}else{
				this.context.put("dataListWaris", "");
			}
			context.put("addMode", "yes");
    		context.put("viewMode", "no");
    		context.put("editMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("tab","");

			vm = "app/ppk/frmPrmhnnKolateral.jsp";

		}else if("doChangeidTempatBicara".equals(submit)){
			
	    		String idTempatBicara = getParam("socTempatBicara");
	    		String idBicara = "";
	    		String alamat1 = "";
	    		String alamat2 = "";
	    		String alamat3 = "";
	    		String poskod  = "";
	    		String negeri  = "";
	    		String txdTarikhPerakuanAdd  = "";
	    		String txdTarikhBicara = "";
	    		String id_pejabatjkptg = "";

	    		if(!idTempatBicara.equals("")){
	    	        idBicara = getParam(idTempatBicara);
	    	        alamatTempatBicara = logic5.getAlamatTempatBicara(idTempatBicara);
	    	        if(alamatTempatBicara.size()>0){
	    	         Hashtable AB = (Hashtable) alamatTempatBicara.get(0);
	    	            alamat1 = AB.get("alamat1").toString();
	    	            alamat2 = AB.get("alamat2").toString();
	    	            alamat3 = AB.get("alamat3").toString();
	    	            poskod  = AB.get("poskod").toString();
	    	            negeri  = AB.get("id_negeri").toString();
	    	        }
	    	    }

	    		Hashtable semak = (Hashtable) list.get(0);
	    		String idNegeri = semak.get("pmidnegeri").toString();

	    		//list & data
	    		context.put("listSemak", list);

	    		//get & post all field content
	    		context.put("poskod", poskod);
	    		context.put("alamat1", alamat1);
	    		context.put("alamat2", alamat2);
	    		context.put("alamat3", alamat3);
	    		context.put("txdTarikhPerakuanAdd", getParam("txdTarikhPerakuanAdd"));
	    		context.put("txdTarikhBicara", getParam("txdTarikhBicara"));
	    		context.put("txtMasaBicara", getParam("txtMasaBicara"));
	    		context.put("id_pejabatjkptg", idBicara);
	    		context.put("id_negeri", negeri);

	    		if(negeri!=""){
	    			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(negeri),"class=disabled disabled style=width:305"));
	        	}else{
	        		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"class=disabled disabled style=width:305"));
	            }

	    		//dropdown
	    		if(idPejabatJKPTG!=""){
	    			context.put("selectBicara",HTML.SelectTempatBicaraByPejabatJKPTG(idPejabatJKPTG,"socTempatBicara",Utils.parseLong(idTempatBicara),null,"style=width:340 onChange=\"doChangeidTempatBicara();\" "));
	    			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",null,"style=width:305"));
	            }else{
	    			context.put("selectBicara",HTML.SelectTempatBicaraByPejabatJKPTG("socTempatBicara",null,null,"style=width:340 onChange=\"doChangeidTempatBicara();\" "));
	    			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",null,"style=width:305"));
	            }

	    		//id
	    		context.put("id_permohonan",id);
	    		context.put("idnegeri", idNegeri);

	    		//form validation
	    		context.put("addMode", "yes");
	    		context.put("viewEditMode", "no");
	    		context.put("viewMode", "no");
	    		context.put("editMode", "no");

		        vm = "app/ppk/frmPrmhnnKolateral.jsp";

		}else if("doChangeidTempatBicaraUpdate".equals(submit)){

    		String idTempatBicara = getParam("socTempatBicara");
    		String idBicara = "";
			idBicara = getParam(idTempatBicara);
			alamatTempatBicara = logic5.getAlamatTempatBicara(idBicara);
			String alamat1 = "";
			String alamat2 = "";
			String alamat3 = "";
			String poskod = "";
			if(alamatTempatBicara.size()!=0){
				Hashtable AB = (Hashtable) alamatTempatBicara.get(0);
	    		alamat1 = AB.get("alamat1").toString();
	    		alamat2 = AB.get("alamat2").toString();
	    		alamat3 = AB.get("alamat3").toString();
	    		poskod 	= AB.get("poskod").toString();
			}

    		Hashtable semak = (Hashtable) list.get(0);
    		String idNegeri = semak.get("pmidnegeri").toString();

    		//list & data
    		context.put("listSemak", list);

    		//get & post all field content
    		context.put("poskod", poskod);
    		context.put("alamat1", alamat1);
    		context.put("alamat2", alamat2);
    		context.put("alamat3", alamat3);
    		context.put("txdTarikhPerakuanAdd", getParam("txdTarikhPerakuanAdd"));
    		context.put("txtMasaBicara", getParam("txtMasaBicara"));
    		context.put("txdTarikhBicara", getParam("txdTarikhBicara"));

    		//id
    		context.put("id_permohonan",id);
    		context.put("idnegeri", idNegeri);

    		//Dropdown
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(semak.get("pmidnegeri").toString()),"disabled style=width:310px"));
    		context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",Utils.parseLong(idTempatBicara),"disabled style=width:310px"));
    		context.put("selectViewBicara",HTML.SelectTempatBicara("socTempatBicara",Utils.parseLong(idTempatBicara),null,"style=width:310px onChange=\"doChangeidTempatBicaraUpdate();\" "));

    		//form validation
    		context.put("viewEditMode", "yes");
    		context.put("addMode", "no");
    		context.put("viewMode", "no");
    		context.put("editMode", "no");

	        vm = "app/ppk/frmPrmhnnKolateral.jsp";


		}else if("doChangeidMahkamah".equals(submit)){	//* ini utk rujukan mahkamah tinggi

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}
				context.put("dataPerintahView", dataPerintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String tarikh_bicara = "";
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
        		tarikh_bicara = (String)idn.get("tarikh_bicara");
        		id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("tarikh_bicara",tarikh_bicara);
    			context.put("id_perbicaraan",id_perbicaraan);

			//get data TBLPPKPERBICARAAN
			Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);
			long idUnitPsk = 0;
			if(h.size()!=0){
				idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
				context.put("dataPerbicaraan", h);

    		String idNegeriTempatBicara = getParam("socNegeri");
    		String idPejabat = getParam("socTempatBicara");
    		String jenisPerintah = getParam("jenisPerintah");
    		String txdTarikhPerintahAdd = getParam("txdTarikhPerintahAdd");

    		int idNegeri = 0;
    		String id_pejabat = "";
    		String nama_pejabat = "";
    		String alamat1 = "";
    		String alamat2 = "";
    		String alamat3 = "";
    		String poskod  = "";
    		String negeri  = "";
    		String no_tel  = "";
    		String no_fax = "";

    		if ( idNegeriTempatBicara != "" )
    		{
    			idNegeri = Integer.parseInt(idNegeriTempatBicara);
    			alamatTempatBicara = logic4.getAlamatTempatMahkamah(idNegeriTempatBicara,idPejabat);
    			if(alamatTempatBicara.size()!=0){
        			Hashtable AB = (Hashtable) alamatTempatBicara.get(0);
        			idPejabat  = AB.get("id_pejabat").toString();
        			nama_pejabat = AB.get("nama_pejabat").toString();
            		alamat1 = AB.get("alamat1").toString();
            		alamat2 = AB.get("alamat2").toString();
            		alamat3 = AB.get("alamat3").toString();
            		poskod 	= AB.get("poskod").toString();
            		no_tel 	= AB.get("no_tel").toString();
            		no_fax 	= AB.get("no_fax").toString();
            		negeri 	= AB.get("id_negeri").toString();
    			}
    		}
    		if ( idNegeriTempatBicara != "" ){
    			context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",Utils.parseLong(idNegeriTempatBicara),"style=width:305 onChange=\"doChangeidNegeri();\" "));
    		}else{
    			context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",null,"style=width:305 onChange=\"doChangeidNegeri();\" "));
    		}
    		context.put("selectBicara",HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriTempatBicara),"socTempatBicara",Utils.parseLong(idPejabat),"style=width:340 onChange=\"doChangeidMahkamah();\" "));
			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",idUnitPsk,"style=width:305"));

       		//list & data
    		context.put("listSemak", list);

    		//get & post all field content
    		context.put("jenisPerintah", getParam("jenisPerintah"));
    		context.put("id_pejabat", idPejabat);
    		context.put("nama_pejabat", nama_pejabat);
    		context.put("alamat1", alamat1);
    		context.put("alamat2", alamat2);
    		context.put("alamat3", alamat3);
    		context.put("poskod", poskod);
    		context.put("notel", no_tel);
    		context.put("nofax", no_fax);
    		context.put("txdTarikhPerintahAdd", getParam("txdTarikhPerintahAdd"));

    		//form validation
    		context.put("addMode", "yes");
    		context.put("viewEditMode", "no");
    		context.put("viewMode", "no");
    		context.put("editMode", "no");
    		context.put("action", "onChange");

	        vm = "app/ppk/frmPrmhnnSek8MahkamahTinggi.jsp";

		}else if("doChangeidMahkamahUpdate".equals(submit)){

    		String id_perbicaraan = getParam("id_perbicaraan");

			//* GET MAKLUMAT TANGGUH
    		long idUnitPsk = 0;
    		String jenis_keluar_perintah = "";
    		PerintahTangguh = FrmPrmhnnSek8KptsanBicaraData.setPerintahTangguh(id_perbicaraan);
			if ( PerintahTangguh.size() != 0 ){
				Hashtable c  = (Hashtable) PerintahTangguh.get(0);
				idUnitPsk = Long.parseLong(c.get("id_unitpsk").toString());
	    		jenis_keluar_perintah = (String)c.get("jenis_keluar_perintah");
			}
				context.put("infoPerintahTangguh",PerintahTangguh);

    		String jenisPerintah = getParam("jenisPerintah");
    		String txdTarikhPerintahAdd = getParam("txdTarikhPerintahAdd");
    		String idNegeriTempatBicara = getParam("socNegeri");
    		String idPejabat = getParam("socTempatBicara");

    		int idNegeri = 0;
    		String alamat1 = "";
    		String alamat2 = "";
    		String alamat3 = "";
    		String poskod  = "";
    		String negeri  = "";
    		String no_tel  = "";
    		String no_fax = "";

    		if( idNegeriTempatBicara != "" )
    		{
    			idNegeri = Integer.parseInt(idNegeriTempatBicara);
    			alamatTempatBicara = logic4.getAlamatTempatMahkamah(idNegeriTempatBicara,idPejabat);

    			if(alamatTempatBicara.size()!=0){
        			Hashtable AB = (Hashtable) alamatTempatBicara.get(0);
        			idPejabat = AB.get("id_pejabat").toString();
            		alamat1 = AB.get("alamat1").toString();
            		alamat2 = AB.get("alamat2").toString();
            		alamat3 = AB.get("alamat3").toString();
            		poskod  = AB.get("poskod").toString();
            		no_tel  = AB.get("no_tel").toString();
            		no_fax  = AB.get("no_fax").toString();
            		negeri  = AB.get("id_negeri").toString();
    			}
    		}
    	    context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",Utils.parseLong(idNegeriTempatBicara),"style=width:305 onChange=\"doChangeidNegeriUpdate();\" "));
    		context.put("selectBicara",HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriTempatBicara),"socTempatBicara",Utils.parseLong(idPejabat),"style=width:340 onChange=\"doChangeidMahkamahUpdate();\" "));
    		context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn, "socPegawai", idUnitPsk, "style=width:305"));

       		//list & data
    		context.put("listSemak", list);

    		//get & post all field content
    		context.put("jenisPerintah", getParam("jenisPerintah"));
    		context.put("id_pejabat", idPejabat);
    		context.put("alamat1", alamat1);
    		context.put("alamat2", alamat2);
    		context.put("alamat3", alamat3);
    		context.put("poskod", poskod);
    		context.put("no_tel", no_tel);
    		context.put("no_fax", no_fax);
    		context.put("txdTarikhPerintahAdd", getParam("txdTarikhPerintahAdd"));

    		//form validation
    		context.put("addMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("viewMode", "no");
    		context.put("editMode", "yes");
    		context.put("action", "");

	        vm = "app/ppk/frmPrmhnnSek8MahkamahTinggi.jsp";

   	}else if("kembali_list".equals(submit)){

	    	//get list data
	        FrmSenaraiFailKptsPerbcrnData.setList(usid);
			list = FrmSenaraiFailKptsPerbcrnData.getList();

			//data & size default list
			this.context.put("listNotis", list);
			context.put("list", list.size());

    		vm = "app/ppk/frmSenaraiFailKeputusanPerbicaraan.jsp";

	}else if("kembali_skrin2TangguhAdd".equals(submit)){

		int flag_jenis_keputusan = 2 ;
		checkedBatal= "checked" ;

		//get id_keputusanpermohonan - tiada id_perbicaraan
		FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
		dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
		String idkp = "";
		if(dataPerintah.size()!=0){
			Hashtable v = (Hashtable) dataPerintah.get(0);
			idkp = (String)v.get("id_keputusanpermohonan");
		}
			context.put("dataPerintahView", dataPerintah);

    	//--data notis
		logic4.setListSemakWithData(idkp);
		dataNotis = logic4.getListSemakWithData();
		String tarikh_bicara = "";
		String id_perbicaraan = "";
		if(dataNotis.size()!=0){
			Hashtable idn = (Hashtable) dataNotis.get(0);
			tarikh_bicara = (String)idn.get("tarikh_bicara");
			id_perbicaraan = (String)idn.get("id_perbicaraan");
		}
			context.put("tarikh_bicara",tarikh_bicara);
			context.put("id_perbicaraan",id_perbicaraan);

		//call flag
		context.put("mode", "add");
		context.put("flag", "batal");
		context.put("button", "");
		context.put("tarikh", "perintah");

		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

	}else if("kembali_skrinROTSadd".equals(submit)){

		int flag_jenis_keputusan = 1 ;
		context.put("TEMPcheckedTangguh","checked");

		//get id_keputusanpermohonan - tiada id_perbicaraan
		FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
		dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
		String idkp = "";
		if(dataPerintah.size()!=0){
			Hashtable v = (Hashtable) dataPerintah.get(0);
			idkp = (String)v.get("id_keputusanpermohonan");
		}
			context.put("dataPerintahView", dataPerintah);

    	//--data notis
		logic4.setListSemakWithData(idkp);
		dataNotis = logic4.getListSemakWithData();
		long idUnitPsk = 0;
		String tarikh_bicara = "";
		String id_perbicaraan = "";
		if(dataNotis.size()!=0){
			Hashtable idn = (Hashtable) dataNotis.get(0);
			idUnitPsk = Long.parseLong(idn.get("id_unitpsk").toString());
			tarikh_bicara = (String)idn.get("tarikh_bicara");
			id_perbicaraan = (String)idn.get("id_perbicaraan");
		}
			context.put("tarikh_bicara",tarikh_bicara);
			context.put("id_perbicaraan",id_perbicaraan);
			context.put("selectEditPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,null));

		//call flag
		context.put("mode", "add");
		context.put("flag", "tangguh");
		context.put("button", "");
		context.put("tarikh", "bicara");

		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

	}else if("kembali_skrinROTSedit".equals(submit)){

		//get id_keputusanpermohonan - tiada id_perbicaraan
		FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
		dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
		String idkp = "";
		String id_perintah = "";
		if(dataPerintah.size()!=0){
			Hashtable v = (Hashtable) dataPerintah.get(0);
			idkp = (String)v.get("id_keputusanpermohonan");
			id_perintah = (String)v.get("id_perintah");
		}
		context.put("dataPerintahView", dataPerintah);

    	//--data notis
		logic4.setListSemakWithData(idkp);
		dataNotis = logic4.getListSemakWithData();
		String id_perbicaraan = "";
		String tarikh_bicara = "";
		long idUnitPsk = 0;
		if(dataNotis.size()!=0){
			Hashtable idn = (Hashtable) dataNotis.get(0);
			id_perbicaraan = (String)idn.get("id_perbicaraan");
			tarikh_bicara = (String)idn.get("tarikh_bicara");
			idUnitPsk = Long.parseLong(idn.get("id_unitpsk").toString());
		}
			context.put("id_perbicaraan",id_perbicaraan);
			context.put("tarikh_bicara",tarikh_bicara);
			context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,"disabled"));

		//get data TBLPPKPERBICARAAN
		Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);
		//long idUnitPsk = 0;
		if(h.size()!=0){
			//idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
			//System.out.println("ID UNITPSK :: "+idUnitPsk);
		}
			context.put("dataPerbicaraan", h);


	    //get info via id_perbicaraan
		FrmPrmhnnSek8KptsanBicaraData.setViewTangguhList(idpermohonan, id_perbicaraan);
	    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getViewTangguh();
	    Hashtable d = new Hashtable();
	    String flag_jenis_keputusan = "";
	    String flag_tangguh = "";
	    if(getrecord_perintah.size()!=0){
			d = (Hashtable) getrecord_perintah.get(0);
			flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");
			flag_tangguh = d.get("flag_tangguh").toString();
		}
	    context.put("dataPerintah", getrecord_perintah);

		if (d.get("flag_jenis_keputusan").equals("0")){
			setValueFlagJenisKeputusan("checked","","");
		} else if (d.get("flag_jenis_keputusan").equals("1")){
			setValueFlagJenisKeputusan("","checked","");
		} else if (d.get("flag_jenis_keputusan").equals("2")){
			setValueFlagJenisKeputusan("","","checked");
		}
		context.put("TEMPcheckedSelesai",checkedSelesai);
		context.put("TEMPcheckedTangguh",checkedTangguh);
		context.put("TEMPcheckedBatal",checkedBatal);
		if (d.get("flag_tangguh").equals("1")){
			setValue("checked","","","","","","","","");
		} else if (d.get("flag_tangguh").equals("2")){
			setValue("","checked","","","","","","","");
		} else if (d.get("flag_tangguh").equals("3")){
			setValue("","","checked","","","","","","");
		} else if (d.get("flag_tangguh").equals("4")){
			setValue("","","","checked","","","","","");
		} else if (d.get("flag_tangguh").equals("5")){
			setValue("","","","","checked","","","","");
		} else if (d.get("flag_tangguh").equals("6")){
			setValue("","","","","","checked","","","");
		} else if (d.get("flag_tangguh").equals("7")){
			setValue("","","","","","","checked","","");
		} else if (d.get("flag_tangguh").equals("8")){
			setValue("","","","","","","","checked","");
		} else if (d.get("flag_tangguh").equals("9")){
			setValue("","","","","","","","","checked");
		}
		context.put("TEMPcheckedTidakHadir",checkedTidakHadir);
		context.put("TEMPcheckedWarisTidakLengkap",checkedWarisTidakLengkap);
		context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggi);
		context.put("TEMPcheckedBuktiTidakLengkap",checkedBuktiTidakLengkap);
		context.put("TEMPcheckedMahkamahSyariah",checkedMahkamahSyariah);
		context.put("TEMPcheckedPertelingkahanKolateral",checkedPertelingkahanKolateral);
		context.put("TEMPcheckedSijilFaraid",checkedSijilFaraid);
		context.put("TEMPcheckedSuratSetuju",checkedSuratSetuju);
		context.put("TEMPcheckedSebabLain",checkedSebabLain);

		//call flag
		context.put("mode", "edit");
		context.put("flag", "tangguh");
		context.put("button", "");
		context.put("tarikh", "bicara");

		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

	}else if("kembali_skrin2TangguhKolEdit".equals(submit)){

	    String id_perbicaraan = getParam("id_perbicaraan");
	    this.context.put("id_perbicaraan",id_perbicaraan);

		//get id_keputusanpermohonan - tiada id_perbicaraan
		FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
		dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
		String idkp = "";
		String id_perintah = "";
		if(dataPerintah.size()!=0){
			Hashtable v = (Hashtable) dataPerintah.get(0);
			idkp = (String)v.get("id_keputusanpermohonan");
			id_perintah = v.get("id_perintah").toString();
		}
		context.put("dataPerintahView", dataPerintah);
		context.put("id_perintah", id_perintah);

    	//--data notis
		logic4.setListSemakWithData(idkp);
		dataNotis = logic4.getListSemakWithData();
		String tarikh_bicara = "";
		if(dataNotis.size()!=0){
			Hashtable idn = (Hashtable) dataNotis.get(0);
			tarikh_bicara = idn.get("tarikh_bicara").toString();
		}
		context.put("tarikh_bicara",tarikh_bicara);

	    FrmPrmhnnSek8KptsanBicaraData.setInfoPerintah(idpermohonan,id_perbicaraan);
	    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahView();
	    Hashtable d = new Hashtable();
	    long idUnitPskView = 0;
	    String flag_jenis_keputusan = "";
	    if(getrecord_perintah.size()!=0){
	    	d = (Hashtable) getrecord_perintah.get(0);
	    	idUnitPskView = Long.parseLong(d.get("id_unitpsk").toString());
	    	flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");
	    }
		context.put("dataPerintah", getrecord_perintah);
		context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPskView,""));

		if (d.get("flag_jenis_keputusan").equals("0")){
			setValueFlagJenisKeputusan("checked","","");
		} else if (d.get("flag_jenis_keputusan").equals("1")){
			setValueFlagJenisKeputusan("","checked","");
		} else if (d.get("flag_jenis_keputusan").equals("2")){
			setValueFlagJenisKeputusan("","","checked");
		}
		context.put("TEMPcheckedSelesai",checkedSelesai);
		context.put("TEMPcheckedTangguh",checkedTangguh);
		context.put("TEMPcheckedBatal",checkedBatal);
		if (d.get("flag_tangguh").equals("1")){
			setValue("checked","","","","","","","","");
		} else if (d.get("flag_tangguh").equals("2")){
			setValue("","checked","","","","","","","");
		} else if (d.get("flag_tangguh").equals("3")){
			setValue("","","checked","","","","","","");
		} else if (d.get("flag_tangguh").equals("4")){
			setValue("","","","checked","","","","","");
		} else if (d.get("flag_tangguh").equals("5")){
			setValue("","","","","checked","","","","");
		} else if (d.get("flag_tangguh").equals("6")){
			setValue("","","","","","checked","","","");
		} else if (d.get("flag_tangguh").equals("7")){
			setValue("","","","","","","checked","","");
		} else if (d.get("flag_tangguh").equals("8")){
			setValue("","","","","","","","checked","");
		} else if (d.get("flag_tangguh").equals("9")){
			setValue("","","","","","","","","checked");
		}
		context.put("TEMPcheckedTidakHadir",checkedTidakHadir);
		context.put("TEMPcheckedWarisTidakLengkap",checkedWarisTidakLengkap);
		context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggi);
		context.put("TEMPcheckedBuktiTidakLengkap",checkedBuktiTidakLengkap);
		context.put("TEMPcheckedMahkamahSyariah",checkedMahkamahSyariah);
		context.put("TEMPcheckedPertelingkahanKolateral",checkedPertelingkahanKolateral);
		context.put("TEMPcheckedSijilFaraid",checkedSijilFaraid);
		context.put("TEMPcheckedSuratSetuju",checkedSuratSetuju);
		context.put("TEMPcheckedSebabLain",checkedSebabLain);

		//call flag
		context.put("mode", "edit");
		context.put("flag", "tangguh");
		context.put("button", "");
		context.put("tarikh", "bicara");

		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

	}else if("kembali_skrin2TangguhKolAdd".equals(submit)){

		int flag_jenis_keputusan = 1 ;
		checkedTangguh = "checked" ;

		//get id_keputusanpermohonan - tiada id_perbicaraan
		FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
		dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
		String idkp = "";
		if (dataPerintah.size()!=0){
			Hashtable v = (Hashtable) dataPerintah.get(0);
			idkp = (String)v.get("id_keputusanpermohonan");
		}
		this.context.put("dataPerintahView", dataPerintah);

    	//--data notis
		logic4.setListSemakWithData(idkp);
		dataNotis = logic4.getListSemakWithData();
		String id_perbicaraan = "";
		String tarikh_bicara = "";
		if (dataNotis.size()!=0){
			Hashtable idn = (Hashtable) dataNotis.get(0);
			id_perbicaraan = (String)idn.get("id_perbicaraan");
			tarikh_bicara = idn.get("tarikh_bicara").toString();
		}
			this.context.put("id_perbicaraan",id_perbicaraan);
			this.context.put("tarikh_bicara",tarikh_bicara);

		//call flag
		context.put("mode", "add");
		context.put("flag", "tangguh");
		context.put("button", "");
		context.put("tarikh", "bicara");

		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

    	}else if("kembali_skrin2Tangguh".equals(submit)){	//* edit mode

		    //get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}

	    	//--data notis
			logic4.setListSemakWithData(idkp);
			dataNotis = logic4.getListSemakWithData();
			String id_perbicaraan = "";
			if(dataNotis.size()!=0){
				Hashtable idn = (Hashtable) dataNotis.get(0);
				id_perbicaraan = (String)idn.get("id_perbicaraan");
			}
				context.put("id_perbicaraan",id_perbicaraan);

		    //get info via id_perbicaraan
    		FrmPrmhnnSek8KptsanBicaraData.setViewTangguhList(idpermohonan, id_perbicaraan);
		    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getViewTangguh();
		    Hashtable d = new Hashtable();
		    long idUnitPskView = 0;
		    String flag_tangguh = "";
		    String flag_jenis_keputusan = "";
		    if(getrecord_perintah.size()!=0){
		    	d = (Hashtable) getrecord_perintah.get(0);
		    	idUnitPskView = Long.parseLong(d.get("id_unitpsk").toString());
		    	flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");
				flag_tangguh = d.get("flag_tangguh").toString();
		    }
		    	context.put("dataPerintah", getrecord_perintah);
		    	context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",idUnitPskView,"disabled class=mediumselect"));

			if (d.get("flag_jenis_keputusan").equals("0")){
				setValueFlagJenisKeputusan("checked","","");
			} else if (d.get("flag_jenis_keputusan").equals("1")){
				setValueFlagJenisKeputusan("","checked","");
			} else if (d.get("flag_jenis_keputusan").equals("2")){
				setValueFlagJenisKeputusan("","","checked");
			}
			context.put("TEMPcheckedSelesai",checkedSelesai);
			context.put("TEMPcheckedTangguh",checkedTangguh);
			context.put("TEMPcheckedBatal",checkedBatal);

   			if (d.get("flag_batal").equals("1")){
   				setValueFlagBatal("checked","","","","");
			} else if (d.get("flag_batal").equals("2")){
				setValueFlagBatal("","checked","","","");
			} else if (d.get("flag_batal").equals("3")){
				setValueFlagBatal("","","checked","","");
			} else if (d.get("flag_batal").equals("4")){
				setValueFlagBatal("","","","checked","");
			} else if (d.get("flag_batal").equals("5")){
				setValueFlagBatal("","","","","checked");
			}
		context.put("TEMPcheckedMahkamahTinggiWasiat",checkedMahkamahTinggiWasiat);
		context.put("TEMPcheckedTidakHadir3Kali",checkedTidakHadir3Kali);
		context.put("TEMPcheckedPermintaanPemohon",checkedPermintaanPemohon);
		context.put("TEMPcheckedMahkamahTinggi2Juta",checkedMahkamahTinggi2Juta);
		context.put("TEMPcheckedSebabLainBatal",checkedSebabLainBatal);
    		getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
    		long idUnitPsk = 0;
    		if ( getrecord_infoperbicaraan.size() != 0 ){
    			Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
    			idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
    		}
    			context.put("dataPerbicaraan", getrecord_infoperbicaraan);
    			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",idUnitPsk,"disabled class=mediumselect"));
    		//call flag
    		context.put("mode", "edit");
    		context.put("flag", "batal");
    		context.put("button", "");
    		context.put("tarikh", "perintah");

    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

   	}else if("kembali_skrin2TangguhEdit".equals(submit)){

			int flag_jenis_keputusan = 2 ;
			checkedBatal = "checked" ;

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}
			context.put("dataPerintahView", dataPerintah);

	    	//--data notis
			System.out.println("Read Here data Notis********");
			logic4.setListSemakWithData(idkp);
			dataNotis = logic4.getListSemakWithData();
			String id_perbicaraan = "";
			String tarikh_bicara ="";
			if(dataNotis.size()!=0){
				Hashtable idn = (Hashtable) dataNotis.get(0);
				id_perbicaraan = (String)idn.get("id_perbicaraan");
				tarikh_bicara = idn.get("tarikh_bicara").toString();
			}
			context.put("id_perbicaraan",id_perbicaraan);
			context.put("tarikh_bicara",tarikh_bicara);

    		getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
    		long idUnitPsk = 0;

    		if ( getrecord_infoperbicaraan.size() != 0 ){
    			Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
    			idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
    		}
    		context.put("dataPerbicaraan", getrecord_infoperbicaraan);
    		context.put("selectEditPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",idUnitPsk,"disabled class=mediumselect"));

    		//call flag
    		context.put("mode", "edit");
    		context.put("flag", "batal");
    		context.put("button", "");
    		context.put("tarikh", "perintah");

    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

    	}else if("kembali_skrinBicaraSelesai".equals(submit)){

			int flag_jenis_keputusan = 1 ;
			checkedTangguh = "checked" ;

			int sorReason = Integer.parseInt(getParam("sorReason"));

			if (getParam("sorReason").equals("5")){ //skrin RulerOfTheState
				checkedMahkamahSyariah = "checked";
				checkedMahkamahTinggi = "";
				checkedPertelingkahanKolateral = "";
			} else if (getParam("sorReason").equals("3")){ //skrin MahkamahTinggi
				checkedMahkamahSyariah = "";
				checkedMahkamahTinggi = "checked";
				checkedPertelingkahanKolateral = "";
			} else if (getParam("sorReason").equals("6")){ //skrin PertelingkahanKolateral
				checkedMahkamahSyariah = "";
				checkedMahkamahTinggi = "";
				checkedPertelingkahanKolateral = "checked";
			}
    		context.put("TEMPcheckedMahkamahSyariah",checkedMahkamahSyariah);
    		context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggi);
    		context.put("TEMPcheckedPertelingkahanKolateral",checkedPertelingkahanKolateral);

    		String selectedTab = "";
            selectedTab = getParam("tabId").toString();
            if (selectedTab == null || "".equals(selectedTab)) {
            	selectedTab="1";
              }
            context.put("selectedTab",selectedTab);

		    String id_perbicaraan = getParam("id_perbicaraan");
		    this.context.put("id_perbicaraan",id_perbicaraan);

    		getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
    		long idUnitPsk = 0;
    		if( getrecord_infoperbicaraan.size() != 0){
    			Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
    			idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
    		}
    		context.put("dataPerbicaraan", getrecord_infoperbicaraan);
    		context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",idUnitPsk,"disabled class=mediumselect"));

    		context.put("beforeFlag", "yes");
    		context.put("flag_jenis_keputusan", "no");
    		context.put("addTangguh", "yes");
    		context.put("beforeFlag", "no");
    		context.put("modeAdd", "no");
    		context.put("modeEdit", "yes");

    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

		}else if("Simpan_Selesai".equals(submit)){

		    //get info pemohon
			logic3.setListSemak(idpermohonan, usid);
			list = logic3.getListSemak();
			//hashtable maklumat header
			headerppk_baru(session,idpermohonan,"Y","","T");
			if ( list.size() != 0 ){
				Hashtable ls = (Hashtable) list.get(0);
				idstatus = ls.get("id_Status").toString();
	    		idFail = ls.get("idFail").toString();
	    		noFail = ls.get("noFail").toString();
			}

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if ( dataPerintah.size() != 0 ){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}

    		//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String id_perbicaraan = "";
    		String tarikh_bicara = "";
    		if (dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
    			tarikh_bicara = idn.get("tarikh_bicara").toString();
    		}
    		this.context.put("id_perbicaraan",id_perbicaraan);
    		this.context.put("tarikh_bicara",tarikh_bicara);

			getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
			if ( getrecord_infoperbicaraan.size() != 0 ){
				Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
				context.put("selectEditPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",Long.parseLong(h.get("id_unitpsk").toString()),"class=mediumselect"));
			}

			String txtJumBayaranPusaka = getParam("txtJumBayaranPusaka");
			String txtJumBayaranBaitulmal = getParam("txtJumBayaranBaitulmal");
			String txtCatatanSelesai = getParam("txtCatatanSelesai");

			//* CHECKING EXISTING MAKLUMAT BAYARAN
			getExistDataBayaran = FrmPrmhnnSek8KptsanBicaraData.setExistDataBayaran(idpermohonan);
			if ( getExistDataBayaran.size() != 0 ){
				//* DELETE MAKLUMAT BAYARAN UTK ID_JENISBAYARAN 24,26,29
				hapusExistingMaklumatBayaran(idpermohonan);
			}
			//END CHECKING

				if (doPost.equals("true")) {
					add_MaklumatBayaranPerintah(idpermohonan,usid);
				}

					add_maklumatPerintah(id_perbicaraan,usid,txtCatatanSelesai);
					//edit_status_selesai(idpermohonan,usid);
					//edit_status_tblrujsuburusanstatusfail(usid,idpermohonan,id_perbicaraan,
					//		idsuburusanstatusfail,idFail);
					//:::SUB
					//ID_STATUS :41
					//ID_SUBURUSAN :373
					logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"41","373",idFail);
					
					

				if ( txtJumBayaranPusaka != "" ){
					if (doPost.equals("true")) {
						add_MaklumatBayaranCukaiPusaka(usid,idpermohonan,id_perbicaraan);
					}
				}
				if ( txtJumBayaranBaitulmal != "" ){
					if (doPost.equals("true")) {
						add_MaklumatBayaranBaitulMal(usid,idpermohonan,id_perbicaraan);
					}
				}

			FrmPrmhnnSek8KptsanBicaraData.setViewBicara(idpermohonan);
			dataBayaran = FrmPrmhnnSek8KptsanBicaraData.getDataBayaran();
  	    	this.context.put("dataBicaraView", dataBayaran);

   			Vector getJumlahBayaran = FrmPrmhnnSek8KptsanBicaraData.setJumlahBayaran(idpermohonan);
			Hashtable a = (Hashtable) getJumlahBayaran.get(0);
			this.context.put("dataJumlahBayaran", getJumlahBayaran);

			//get data TBLPPKPERBICARAAN
			Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);
			//long idUnitPsk = 0;
			if (h.size()!=0){
				//idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
			this.context.put("dataPerbicaraan", h);
			//context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,"disabled"));

		    FrmPrmhnnSek8KptsanBicaraData.setInfoPerintahList(idpermohonan);
		    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahViewList();
		    String idUnitPsk = "";
		    String flag_jenis_keputusan = "";
		    if ( getrecord_perintah.size() != 0 ){
    			Hashtable d = (Hashtable) getrecord_perintah.get(0);
    			idUnitPsk = d.get("id_unitpsk").toString();
       			flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");
       			if (idUnitPsk!=""){
       				context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",Utils.parseLong(idUnitPsk),"disabled"));
       			}else{
       				context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",null,"disabled"));
       			}
    			if (d.get("flag_jenis_keputusan").equals("0")){
    				setValueFlagJenisKeputusan("checked","","");
    			}
    			context.put("TEMPcheckedSelesai",checkedSelesai);
    			context.put("TEMPcheckedTangguh",checkedTangguh);
    			context.put("TEMPcheckedBatal",checkedBatal);
    			
    			context.put("check_kiv",(String)d.get("check_kiv"));
    			context.put("check_doc",(String)d.get("check_doc"));
    			context.put("valueKIV",(String)d.get("valueKIV"));
    			
    			context.put("date_kiv",(String)d.get("date_kiv"));
    			context.put("catatan_kiv",(String)d.get("catatan_kiv"));
    			
    			System.out.println("check_kiv===="+(String)d.get("check_kiv"));
    			String check_kiv = (String)d.get("check_kiv");
    			String date_kiv = (String)d.get("date_kiv");
				if (check_kiv.equals("1")){
					System.out.println("masukkkkk");
					logic3.insertActivityEvent(idUnitPsk,id_perbicaraan,noFail, date_kiv);
				}
		    }
		    	this.context.put("dataPerintahView", getrecord_perintah);

			    //get info pemohon
				logic3.setListSemak(idpermohonan, usid);
				list = logic3.getListSemak();
				//hashtable maklumat header
				headerppk_baru(session,idpermohonan,"Y","","T");
				if ( list.size() != 0 ){
					Hashtable ls = (Hashtable) list.get(0);
					idstatus = ls.get("id_Status").toString();
				}
				
				
				

		    //call flag
    		context.put("mode", "view");
    		context.put("flag", "selesai");
    		context.put("button", "");
    		context.put("tarikh", "perintah");
    		context.put("listSemak", list);
    		context.put("idstatus", idstatus);
    		
    		
    		

		    vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";


		}else if("Simpan_Tangguh".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}
				context.put("dataPerintahView", dataPerintah);

			//ALTER BY ELLY 1.03.2010
			//--data notis
//			logic4.setListSemakWithData(idkp);
//    		dataNotis = logic4.getListSemakWithData();
//    		String id_perbicaraan = "";
//    		if(dataNotis.size()!=0){
//    			Hashtable idn = (Hashtable) dataNotis.get(0);
//        		id_perbicaraan = (String)idn.get("id_perbicaraan");
//    		}
//    		context.put("id_perbicaraan",id_perbicaraan);
    		
    		String id_perbicaraan = getParam("id_perbicaraan");
    		//END ALTER
    		
    		

			getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
			long idUnitPsk = 0;
			if( getrecord_infoperbicaraan.size() != 0 ){
				Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
				idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
				System.out.println("idUnitPsk = "+idUnitPsk);
			}
			context.put("selectEditPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,"class=mediumselect"));

			//if (doPost.equals("true")) {
				    add_maklumat_tangguh(usid,idpermohonan,id_perbicaraan);
				    //edit_status_BicaraTangguh(idpermohonan,idsuburusanstatusfail,usid);
				    //edit_BicaraTangguhStatus_tblrujsuburusanstatusfail(idpermohonan,id_perbicaraan,
					//		usid,idsuburusanstatusfail,idFail);
				    //:::SUB
				    //ID_STATUS : 44
				    //ID_SUBURUSAN : 374
				    logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"44","374",idFail);
					
				    
		    //}

		    FrmPrmhnnSek8KptsanBicaraData.setInfoPerintah(idpermohonan,id_perbicaraan);
		    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahView();
		    Hashtable d = new Hashtable();
		    long idUnitPskView = 0;
		    String flag_jenis_keputusan = "";
		    if(getrecord_perintah.size()!=0){
				d = (Hashtable) getrecord_perintah.get(0);
				idUnitPskView = Long.parseLong(d.get("id_unitpsk").toString());
				flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");
				
				//azam put it here.
	    		if (d.get("flag_jenis_keputusan").equals("0")){
	    			setValueFlagJenisKeputusan("checked","","");
	    		    context.put("viewSelesai", "yes");
	        		context.put("viewTangguh", "no");
	        		context.put("viewBatal", "no");
				} else if (d.get("flag_jenis_keputusan").equals("1")){
					setValueFlagJenisKeputusan("","checked","");
	    		    context.put("viewSelesai", "no");
	        		context.put("viewTangguh", "yes");
	        		context.put("viewBatal", "no");
				} else if (d.get("flag_jenis_keputusan").equals("2")){
					setValueFlagJenisKeputusan("","","checked");
	    		    context.put("viewSelesai", "no");
	        		context.put("viewTangguh", "no");
	        		context.put("viewBatal", "yes");
				}
				
		    } else {
				setValueFlagJenisKeputusan("","","");
    		    context.put("viewSelesai", "no");
        		context.put("viewTangguh", "no");
        		context.put("viewBatal", "no");
		    }
		    context.put("dataPerintahView", getrecord_perintah);
			context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",idUnitPskView,"disabled"));
			
			//azam removed

			context.put("TEMPcheckedSelesai",checkedSelesai);
			context.put("TEMPcheckedTangguh",checkedTangguh);
			context.put("TEMPcheckedBatal",checkedBatal);

			if (d.get("flag_tangguh").equals("1")){
				setValue("checked","","","","","","","","");
			} else if (d.get("flag_tangguh").equals("2")){
				setValue("","checked","","","","","","","");
			} else if (d.get("flag_tangguh").equals("3")){
				setValue("","","checked","","","","","","");
			} else if (d.get("flag_tangguh").equals("4")){
				setValue("","","","checked","","","","","");
			} else if (d.get("flag_tangguh").equals("5")){
				setValue("","","","","checked","","","","");
			} else if (d.get("flag_tangguh").equals("6")){
				setValue("","","","","","checked","","","");
			} else if (d.get("flag_tangguh").equals("7")){
				setValue("","","","","","","checked","","");
			} else if (d.get("flag_tangguh").equals("8")){
				setValue("","","","","","","","checked","");
			} else if (d.get("flag_tangguh").equals("9")){
				setValue("","","","","","","","","checked");
			}
			context.put("TEMPcheckedTidakHadir",checkedTidakHadir);
			context.put("TEMPcheckedWarisTidakLengkap",checkedWarisTidakLengkap);
			context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggi);
			context.put("TEMPcheckedBuktiTidakLengkap",checkedBuktiTidakLengkap);
			context.put("TEMPcheckedMahkamahSyariah",checkedMahkamahSyariah);
			context.put("TEMPcheckedPertelingkahanKolateral",checkedPertelingkahanKolateral);
			context.put("TEMPcheckedSijilFaraid",checkedSijilFaraid);
			context.put("TEMPcheckedSuratSetuju",checkedSuratSetuju);
			context.put("TEMPcheckedSebabLain",checkedSebabLain);

		    //get info pemohon
			logic3.setListSemak(idpermohonan, usid);
			list = logic3.getListSemak();
			//hashtable maklumat header
			headerppk_baru(session,idpermohonan,"Y","","T");
			if ( list.size() != 0 ){
				Hashtable ls = (Hashtable) list.get(0);
				idstatus = ls.get("id_Status").toString();
			}

    		//call flag
    		context.put("mode", "view");
    		context.put("flag", "tangguh");
    		context.put("button", "");
    		context.put("tarikh", "bicara");
    		context.put("listSemak", list);

		    vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai.jsp";

       	}else if("Simpan_AddKolateral".equals(submit)){

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}
				context.put("dataPerintahView", dataPerintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		long idUnitPsk = 0;
    		String tarikh_bicara = "";
    		String id_perbicaraan = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			idUnitPsk = Long.parseLong(idn.get("id_unitpsk").toString());
    			tarikh_bicara = idn.get("tarikh_bicara").toString();
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("tarikh_bicara",tarikh_bicara);
    			context.put("id_perbicaraan",id_perbicaraan);
    			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",idUnitPsk,"disabled class=autoselect"));

			getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
			if ( getrecord_infoperbicaraan.size() != 0 ){
				Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
			}

			//* checking wujud id_perintah atau tidak
			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			String id_perintah = "";
			if ( getIdPerintah.size() != 0 ){
				Hashtable a = (Hashtable) getIdPerintah.get(0);
				id_perintah = a.get("id_perintah").toString();
				if (doPost.equals("true")) {
			         addKoleteral_updateTblppkperintah(idpermohonan,usid,id_perbicaraan,id_perintah);
				}

				Vector getIdKolateralmst = FrmPrmhnnSek8KptsanBicaraData.getIdKolSateralmst(id_perbicaraan);
				String IdKolateralmst = "";
				if(getIdKolateralmst.size()!=0){
					Hashtable e = (Hashtable) getIdKolateralmst.get(0);
					IdKolateralmst = (String)e.get("id_kolateralmst");
					if (doPost.equals("true")) {
						add_tblppkkolateraldtl(usid,IdKolateralmst);
					}
				}

			}else {

				if (doPost.equals("true")) {
					//INSERT TBLPPKPERINTAH INSERT TBLPPKKOLATERALMST
					add_maklumat_koleteral(idpermohonan,usid,id_perbicaraan);
				}

				Vector getIdKolateralmst = FrmPrmhnnSek8KptsanBicaraData.getIdKolSateralmst(id_perbicaraan);
				String IdKolateralmst = "";
				if(getIdKolateralmst.size()!=0){
					Hashtable e = (Hashtable) getIdKolateralmst.get(0);
					IdKolateralmst = (String)e.get("id_kolateralmst");
				}
					context.put("getIdKolateralmst", getIdKolateralmst);

				if (doPost.equals("true")) {
					add_tblppkkolateraldtl(usid,IdKolateralmst);
				}
			}

			if (doPost.equals("true")) {
				//edit_status_tangguhKolateral(idpermohonan,idsuburusanstatusfail,usid);
				//edit_status_tblrujsuburusanstatusfail_TangguhKolateral(idpermohonan,usid,
				//		id_perbicaraan,idsuburusanstatusfail,idFail);
				//:::SUB
				//ID_STATUS : 172
				//ID_SUBURUSAN : 775
				logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"172","775",idFail);
			}

		    FrmPrmhnnSek8KptsanBicaraData.setPerintahKolateral(idpermohonan,id_perbicaraan);
		    getrecord_perintahKolateral = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahKolateral();
		    Hashtable d = new Hashtable();
		    long id_pejabatmahkamah = 0;
		    long id_negeri = 0;
		    String flag_jenis_keputusan = "";
		    String flag_tangguh = "";
		    if(getrecord_perintahKolateral.size()!=0){
		    	d = (Hashtable) getrecord_perintahKolateral.get(0);
		    	idUnitPsk = Long.parseLong(d.get("id_unitpsk").toString());
		    	id_pejabatmahkamah = Long.parseLong(d.get("id_pejabatmahkamah").toString());
		    	id_negeri = Long.parseLong(d.get("id_negeri").toString());
		    	flag_jenis_keputusan = (String)d.get("flag_jenis_keputusan");
		    	flag_tangguh = d.get("flag_tangguh").toString();
		    }
		    	context.put("dataPerintahKolateral", getrecord_perintahKolateral);
		    	context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",Long.parseLong(d.get("id_unitpsk").toString())," disabled "));
		    	context.put("selectViewBicara",HTML.SelectTempatBicaraByPejabatJKPTG(idPejabatJKPTG,"socTempatBicara",id_pejabatmahkamah,null," disabled "));
		    	context.put("selectNegeri",HTML.SelectNegeri("socNegeri",id_negeri," disabled "));

			    setValueFlagJenisKeputusan("","checked","");
				context.put("TEMPcheckedTangguh",checkedTangguh);

			if (d.get("flag_tangguh").equals("1")){
				setValue("checked","","","","","","","","");
			} else if (d.get("flag_tangguh").equals("2")){
				setValue("","checked","","","","","","","");
			} else if (d.get("flag_tangguh").equals("3")){
				setValue("","","checked","","","","","","");
			} else if (d.get("flag_tangguh").equals("4")){
				setValue("","","","checked","","","","","");
			} else if (d.get("flag_tangguh").equals("5")){
				setValue("","","","","checked","","","","");
			} else if (d.get("flag_tangguh").equals("6")){
				setValue("","","","","","checked","","","");
			} else if (d.get("flag_tangguh").equals("7")){
				setValue("","","","","","","checked","","");
			} else if (d.get("flag_tangguh").equals("8")){
				setValue("","","","","","","","checked","");
			} else if (d.get("flag_tangguh").equals("9")){
				setValue("","","","","","","","","checked");
			}
			context.put("TEMPcheckedTidakHadir",checkedTidakHadir);
			context.put("TEMPcheckedWarisTidakLengkap",checkedWarisTidakLengkap);
			context.put("TEMPcheckedMahkamahTinggi",checkedMahkamahTinggi);
			context.put("TEMPcheckedBuktiTidakLengkap",checkedBuktiTidakLengkap);
			context.put("TEMPcheckedMahkamahSyariah",checkedMahkamahSyariah);
			context.put("TEMPcheckedPertelingkahanKolateral",checkedPertelingkahanKolateral);
			context.put("TEMPcheckedSijilFaraid",checkedSijilFaraid);
			context.put("TEMPcheckedSuratSetuju",checkedSuratSetuju);
			context.put("TEMPcheckedSebabLain",checkedSebabLain);

			//checking ada senarai waris ke tidak
			Vector senarai_warisPlanitif = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWarisPlanitif(id_perbicaraan,getParam("id_permohonansimati_atheader"));
			context.put("dataWarisPlanitif", senarai_warisPlanitif);

			Vector senarai_warisDefendan = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWarisDefendan(id_perbicaraan,getParam("id_permohonansimati_atheader"));
			context.put("dataWarisDefendan", senarai_warisDefendan);

			String selectedTab = "";
    		selectedTab = getParam("tabId").toString();
            if (selectedTab == null || "".equals(selectedTab)){
            	selectedTab="1";
            }
            context.put("selectedTab",selectedTab);

    		context.put("addMode", "no");
    		context.put("viewMode", "yes");
    		context.put("editMode", "no");
    		context.put("flag", "onChange");
    		context.put("listSemak", list);

			vm = "app/ppk/frmPrmhnnKolateral.jsp";

       	}else if("permohonanKolateral".equals(submit)){

       		context.put("idpermohonan", getParam("idpermohonan"));

       		String selectedTab = "";
    		selectedTab = getParam("tabId").toString();
            if (selectedTab == null || "".equals(selectedTab)){
            	selectedTab="0";
            }
            context.put("selectedTab",selectedTab);

            vm = "app/ppk/frmPrmhnnKolateral.jsp";

       	}else if("keputusanKolateral".equals(submit)){

		    String id_perbicaraan = getParam("id_perbicaraan");
		    this.context.put("id_perbicaraan", id_perbicaraan);

       		context.put("idpermohonan", getParam("idpermohonan"));

			String selectedTab = "";
    		selectedTab = getParam("tabId").toString();
            if (selectedTab == null || "".equals(selectedTab)){
            	selectedTab="0";
            }
            context.put("selectedTab",selectedTab);
            context.put("tab","keputusan");

            Vector getIdKolateralmst = FrmPrmhnnSek8KptsanBicaraData.getIdKolSateralmst(id_perbicaraan);
            String IdKolateralmst = "";
			Hashtable e = (Hashtable) getIdKolateralmst.get(0);
			IdKolateralmst = (String)e.get("id_kolateralmst");

		    FrmPrmhnnSek8KptsanBicaraData.setPerintahKolateral(idpermohonan,id_perbicaraan);
		    getrecord_perintahKolateral = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahKolateral();
		    long idUnitPsk = 0;
		    long id_pejabatmahkamah = 0;
		    long id_negeri = 0;
		    if(getrecord_perintahKolateral.size()!=0){
		    	Hashtable d = (Hashtable) getrecord_perintahKolateral.get(0);
		    	idUnitPsk = Long.parseLong(d.get("id_unitpsk").toString());
				id_pejabatmahkamah = Long.parseLong(d.get("id_pejabatmahkamah").toString());
				id_negeri = Long.parseLong(d.get("id_negeri").toString());
		    }
			context.put("dataPerintahKolateral", getrecord_perintahKolateral);

			context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk," disabled "));
			context.put("selectViewBicara",HTML.SelectTempatBicaraByPejabatJKPTG(idPejabatJKPTG,"socTempatBicara",id_pejabatmahkamah," disabled "));
			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",id_negeri," disabled "));

			//checking ada senarai waris ke tidak
			Vector senarai_warisPlanitif = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWarisPlanitif(id_perbicaraan,getParam("id_permohonansimati_atheader"));
			context.put("dataWarisPlanitif", senarai_warisPlanitif);

			Vector senarai_warisDefendan = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWarisDefendan(id_perbicaraan,getParam("id_permohonansimati_atheader"));
			context.put("dataWarisDefendan", senarai_warisDefendan);

			Vector getDataKeputusan = FrmPrmhnnSek8KptsanBicaraData.setDataKeputusan(idpermohonan);
			getDataKeputusan = FrmPrmhnnSek8KptsanBicaraData.getDataKeputusan();
			if (getDataKeputusan.size()!=0){
				Hashtable c = (Hashtable) getDataKeputusan.get(0);
				String idBayaran = c.get("id_bayaran").toString();
				System.out.println("ID BAYARAN :: "+idBayaran);
			}
				context.put("dataKeputusan", getDataKeputusan);

			Vector getDataKeputusanBayaran = FrmPrmhnnSek8KptsanBicaraData.setDataKeputusanBayaran(id_perbicaraan);
			getDataKeputusanBayaran = FrmPrmhnnSek8KptsanBicaraData.getDataKeputusanBayaran();
			if (getDataKeputusan.size()!=0){
				Hashtable bayaran = (Hashtable) getDataKeputusanBayaran.get(0);
			}
			context.put("dataKeputusanBayaran", getDataKeputusanBayaran);

			context.put("viewMode", "yes");
    		context.put("addMode", "no");
    		context.put("editMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("flag", "onChange");

            vm = "app/ppk/frmPrmhnnKolateral.jsp";

        }else {

        	//clear field carian
        	context.put("txtNoFail", "");
        	context.put("txtPemohon", "");
        	context.put("txtSimati", "");
        	context.put("txtIcSimati", "");
        	context.put("jenisIc", "");

        	//get list data
	        FrmSenaraiFailKptsPerbcrnData.setList(usid);
			list = FrmSenaraiFailKptsPerbcrnData.getList();

    		//data & size default list
    		this.context.put("listNotis", list);
    		context.put("list", list.size());

		    vm = "app/ppk/frmSenaraiFailKeputusanPerbicaraan.jsp";

		}//close else

   		setupPage(session,action,list);
    	this.context.put("flagFromSenaraiFailSek8", flagFromSenaraiFailSek8);
    	this.context.put("flagFromSenaraiPermohonanSek8", flagFromSenaraiPermohonanSek8);
    	this.context.put("flagForView", flagForView);
        
		//Template template = this.engine.getTemplate(vm);
        //return template;
    	this.context.put("userRole", userRole);
    	System.out.println("skrin vm : "+vm);
    	return vm;

    }//close public template
	
	private int parseInt(int length) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void deleteSuppDoc(String idSimati, String jenisDoc) throws Exception 
	{
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " DELETE FROM TBLPPKDOKUMENSIMATI WHERE ID_SIMATI = '"+idSimati+"' AND ID_JENISDOKUMEN = '"+jenisDoc+"'";
			myLogger.info("sql1 >>> "+sql);
			stmt.executeUpdate(sql);
			
			
			conn.commit();

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	
	
}

	private void tambahMaklumatPerintahEDIT(String usid,String idpermohonan) throws Exception {

		//TBLPPKBAYARAN
		String txtJumBayaranEDIT = getParam("txtJumBayaranEDIT");
		String txtNomborResitPerintahEDIT = getParam("txtNomborResitPerintahEDIT");
		String txdTarikhBayaranPerintahEDIT = getParam("txdTarikhBayaranPerintahEDIT");

	    FrmPrmhnnSek8KptsanBicaraData.add_BayaranPerintahEDIT(usid,idpermohonan,txtJumBayaranEDIT,
	    		txtNomborResitPerintahEDIT,txdTarikhBayaranPerintahEDIT);
	}
	

	private void deleteMaklumatSyariah(String id_borangj,String id_perbicaraan) throws Exception{
	    FrmPrmhnnSek8BicaraData.deleteMaklumatSyariah(id_borangj,id_perbicaraan);
	  }

	 private void hapusExistingMaklumatBayaran( String idpermohonan ) throws Exception {
		   logic4.hapusExistingMaklumatBayaran( idpermohonan );
	 }

	 private void deleteMaklumatMufti(String id_borangj,String id_perbicaraan) throws Exception{
	    FrmPrmhnnSek8BicaraData.deleteMaklumatMufti(id_borangj,id_perbicaraan);
	  }

	 private void deleteMaklumatROTS(String id_borangj,String id_perbicaraan) throws Exception{
	    FrmPrmhnnSek8BicaraData.deleteMaklumatROTS(id_borangj,id_perbicaraan);
	  }

	 private void deleteUploadFail(String id_dokumen,String id_lampiran) throws Exception{
	    FrmPrmhnnSek8BicaraData.deleteUploadFail(id_dokumen,id_lampiran);
	  }

	 private void deleteMaklumatWaris(String idBorangJ,String idBorangJDTL) throws Exception {

			FrmPrmhnnSek8BicaraData.deleteMaklumatWaris(idBorangJ, idBorangJDTL);
	  }

	private void updateKeputusan(String idpermohonan,String usid,String id_perbicaraan,String idBorangJ) throws Exception {

		String txdTarikhHantarAdd = getParam("txdTarikhHantarAdd");
		String txdTarikhTerimaAdd = getParam("txdTarikhTerimaAdd");
		String txtKeputusanAdd = getParam("txtKeputusanAdd");
		String txtCatatanAdd = getParam("txtCatatanAdd");

	    FrmPrmhnnSek8KptsanBicaraData.updateKeputusanROTSmaklumat(idpermohonan,usid,
	    		id_perbicaraan,idBorangJ,txdTarikhHantarAdd,txdTarikhTerimaAdd,txtKeputusanAdd,txtCatatanAdd);
}

	private void carianNotis(String usid) throws Exception{

		String noFail = getParam("txtNoFail");
		String namaPemohon = getParam("txtPemohon");
		String namaSimati = getParam("txtSimati");
		String icSimati = getParam("txtIcSimati");
		String JenisIc = getParam("jenisIc");
		String statusFail = getParam("statusFail");

		logic4.setCarianFail(noFail,namaPemohon,namaSimati,icSimati,JenisIc,usid,statusFail);
	}

	//* Pertelingkahan Kolateral
	private void addKoleteral_updateTblppkperintah(String idpermohonan,String usid,String id_perbicaraan,
			String id_perintah) throws Exception {

		String txdTarikhPerakuanAdd = getParam("txdTarikhPerakuanAdd");
		String txdTarikhBicara = getParam("txdTarikhBicara");
		String txtMasaBicara = getParam("txtMasaBicara");
		String socTempatBicara = getParam("socTempatBicara");
		String txtCatatanAdd = getParam("txtCatatanAdd");
		String socNegeriBicara = getParam("id_negeri");
		String socPegawaiPengendali = getParam("socPegawaiPengendali");

	    FrmPrmhnnSek8KptsanBicaraData.insertkolateralmst_updateTblppkperintah(idpermohonan,usid,id_perbicaraan,
				id_perintah,txdTarikhPerakuanAdd,txdTarikhBicara,txtMasaBicara,socTempatBicara,
				txtCatatanAdd,socNegeriBicara,socPegawaiPengendali);
}

	private void updateKolateral_updateTblppkperintah( String id_perbicaraan, String id_perintah, String usid, String IdKolateralmst, String txdTarikhPerakuanAdd, String EDITsocPegawaiPengendali,
			String txdTarikhBicara, String txtMasaBicara, String socTempatBicara, String socNegeri, String txtCatatanAdd ) throws Exception {

		FrmPrmhnnSek8KptsanBicaraData.updateKolateral_updateTblppkperintah( id_perbicaraan, id_perintah, usid, IdKolateralmst, txdTarikhPerakuanAdd, EDITsocPegawaiPengendali,
				txdTarikhBicara, txtMasaBicara, socTempatBicara, socNegeri, txtCatatanAdd );
}

	//* Menunggu Keputusan Rujukan Mahkamah Syariah(ROTS)
	private void insertBorangJ_updateTblppkperintah(String idpermohonan,String id_perbicaraan,
			String usid,String id_perintah) throws Exception {

		String txdTarikhRujukanAdd = getParam("txdTarikhRujukanAdd");
		String txtFaktaGuamanAdd = getParam("txtFaktaGuamanAdd");
		String txtPendapatAdd = getParam("txtPendapatAdd");
		String jenis_rujukan = getParam("jenis_rujukan");
		String socPegawai = getParam("socPegawai");
		String socNegeri = getParam("socNegeri");
		String id_pejabat = getParam("id_pejabat");

	    FrmPrmhnnSek8KptsanBicaraData.insertBorangJ_updateTblppkperintah(idpermohonan,id_perbicaraan,
				usid,id_perintah,txdTarikhRujukanAdd,txtFaktaGuamanAdd,jenis_rujukan,socPegawai,socNegeri,
				id_pejabat,txtPendapatAdd);
}

	private void insertBorangJ_updateTblppkperintahMufti(String usid,String id_perintah,String id_perbicaraan,
			String txdTarikhRujukanAdd,String idNegeriMufti,String jenis_rujukan, String txtnamapej, String txtAlamat1,
			String txtAlamat2,String txtAlamat3,String txtPoskod,String idBandar,
			String txtTelefon,String txtfax,String idUnitPSK,String txtFaktaGuamanAdd,String txtPendapatAdd ) throws Exception {


	    FrmPrmhnnSek8KptsanBicaraData.insertBorangJ_updateTblppkperintahMufti( usid, id_perintah, id_perbicaraan,
				 txdTarikhRujukanAdd, idNegeriMufti, jenis_rujukan,  txtnamapej,  txtAlamat1,
				 txtAlamat2, txtAlamat3, txtPoskod, idBandar, txtTelefon, txtfax, idUnitPSK, txtFaktaGuamanAdd, txtPendapatAdd);
}

	private void insertBorangJ_updateTblppkperintahMS(String usid, String id_perintah,String id_perbicaraan,String txdTarikhRujukanAdd,String idNegeriMahkamah,
			String jenis_rujukan,String idMahkamah,String idUnitPSK,String txtFaktaGuamanAdd,String txtPendapatAdd ) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.insertBorangJ_updateTblppkperintahMS(usid,id_perintah,
	    		id_perbicaraan,txdTarikhRujukanAdd,idNegeriMahkamah,
				jenis_rujukan, idMahkamah,idUnitPSK, txtFaktaGuamanAdd,txtPendapatAdd);
}

	private void updateBorangJ_updateTblppkperintahMS(String idBorangJ,String usid, String id_perintah,String id_perbicaraan,String txdTarikhRujukanAdd,String  idNegeriMahkamah,
			String jenis_rujukan,String idMahkamah,String idUnitPSK,String txtFaktaGuamanAdd,String txtPendapatAdd ) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.updateBorangJ_updateTblppkperintahMS(idBorangJ,usid,id_perintah,
	    		id_perbicaraan,txdTarikhRujukanAdd,idNegeriMahkamah,
				jenis_rujukan, idMahkamah,idUnitPSK, txtFaktaGuamanAdd,txtPendapatAdd);

}

	private void updateBorangJ_updateTblppkperintahMufti( String idBorangJ, String usid, String id_perintah, String id_perbicaraan,
			String txdTarikhRujukanAdd, String idNegeriMufti, String jenis_rujukan, String txtnamapej, String txtAlamat1, String txtAlamat2,
			String txtAlamat3, String txtPoskod, String txtTelefon, String txtfax, String idUnitPSK, String txtFaktaGuamanAdd, String txtPendapatAdd ) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.updateBorangJ_updateTblppkperintahMufti( idBorangJ,usid,id_perintah,
	    		id_perbicaraan,txdTarikhRujukanAdd,idNegeriMufti,jenis_rujukan, txtnamapej,
	    		txtAlamat1,txtAlamat2,txtAlamat3,txtPoskod,txtTelefon,txtfax,idUnitPSK, txtFaktaGuamanAdd,txtPendapatAdd );
}

	private void edit_BicaraTangguhStatus_tblrujsuburusanstatusfail(String idpermohonan,String id_perbicaraan,
			String usid,String idsuburusanstatusfail,String idFail) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_BicaraTangguhStatusTblrujsuburusanstatusfail(idpermohonan,id_perbicaraan,
				usid,idsuburusanstatusfail,idFail);
	}


	private void edit_status_BicaraTangguh(String idpermohonan,String idsuburusanstatusfail,String usid) throws Exception {
	    FrmPrmhnnSek8KptsanBicaraData.edit_status_BicaraTangguh(idpermohonan,idsuburusanstatusfail,usid);

	}

	private void add_maklumat_tangguh(String usid,String idpermohonan,String id_perbicaraan) throws Exception {

		//TBLPPKPERINTAH
		String txdTarikhPerintah = getParam("txdTarikhPerintah");
		String EDITsocPegawaiPengendali = getParam("EDITsocPegawaiPengendali");
		String flag_jenis_keputusan = getParam("flag_jenis_keputusan");
		String txtTempoh = getParam("txtTempoh");
		String flag_tangguh = getParam("flag_tangguh");
		String txtCatatanTangguh = getParam("txtCatatanTangguh");
		String txtPendapatTangguh = getParam("txtPendapatTangguh");

		FrmPrmhnnSek8KptsanBicaraData.add_maklumat_tangguh(usid,idpermohonan,id_perbicaraan,txdTarikhPerintah,
				EDITsocPegawaiPengendali,flag_jenis_keputusan,txtTempoh,flag_tangguh,txtCatatanTangguh,txtPendapatTangguh);
	}

	private void updateROTSmaklumat(String idpermohonan,String id_perbicaraan,String usid,String idBorangJ) throws Exception {

		String txdTarikhRujukanAdd = getParam("txdTarikhRujukanAdd");
		String txtFaktaGuamanAdd = getParam("txtFaktaGuamanAdd");
		String txtPendapatAdd = getParam("txtPendapatAdd");
		String socPegawai = getParam("socPegawai");
		String id_pejabat = getParam("id_pejabat");
		String socNegeri = getParam("socNegeri");

	    FrmPrmhnnSek8KptsanBicaraData.updateROTSmaklumat(idpermohonan,id_perbicaraan,usid,idBorangJ,txdTarikhRujukanAdd,
	    		txtFaktaGuamanAdd,txtPendapatAdd,socPegawai,id_pejabat,socNegeri);
}


	private void updateBorangJDTL(String idBorangJ,String usid) throws Exception {

	    String[] cbsemaks = request.getParameterValues("cbsemaks");
	    if ( cbsemaks  != null ){
	    	for (int i = 0; i < cbsemaks.length; i++) {
	    		logic4.updateLaporan(idBorangJ,usid,cbsemaks[i]);
	    	}
	    }
}

	private void update_tblppkkolateraldtl( String IdKolateralmst, String usid ) throws Exception {

	    String[] cbsemaksP = request.getParameterValues("cbsemaksP");
	    if ( cbsemaksP  != null ){
	    	for (int i = 0; i < cbsemaksP.length; i++) {
	    		logic4.updateKolateralDTLplantif( IdKolateralmst, usid, cbsemaksP[i]);
	    	}
	    }

	    String[] cbsemaksD = request.getParameterValues("cbsemaksD");
	    if ( cbsemaksD  != null ){
	    	for (int i = 0; i < cbsemaksD.length; i++) {
	    		logic4.updateKolateralDTLdefendan( IdKolateralmst, usid, cbsemaksD[i]);
	    	}
	    }
}

	private void edit_status_tblrujsuburusanstatusfail_TangguhKolateral(String idpermohonan,String usid,
			String id_perbicaraan,String idsuburusanstatusfail,String idFail) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_statusTblrujsuburusanstatusfail_TangguhKolateral(idpermohonan,usid,
				id_perbicaraan,idsuburusanstatusfail,idFail);
	}

	private void edit_status_tangguhKolateral(String idpermohonan,String idsuburusanstatusfail,String usid) throws Exception {
	    FrmPrmhnnSek8KptsanBicaraData.edit_status_tangguhKolateral(idpermohonan,idsuburusanstatusfail,usid);

	}

	private void add_tblppkkolateraldtl(String usid,String IdKolateralmst) throws Exception {

	   String[] cbsemaksP = request.getParameterValues("cbsemaksP");
		for (int i = 0; i < cbsemaksP.length; i++) {
    		logic4.addIDOBPlanitif(usid,IdKolateralmst,cbsemaksP[i]);
    	}

	   String[] cbsemaksD = request.getParameterValues("cbsemaksD");
    	for (int j = 0; j < cbsemaksD.length; j++) {
    		logic4.addIDOBDefendan(usid,IdKolateralmst,cbsemaksD[j]);
    	}
}

	private void add_maklumat_koleteral(String idpermohonan,String usid,String id_perbicaraan) throws Exception {

		String txdTarikhPerakuanAdd = getParam("txdTarikhPerakuanAdd");
		String txdTarikhBicara = getParam("txdTarikhBicara");
		String txtMasaBicara = getParam("txtMasaBicara");
		String socTempatBicara = getParam("socTempatBicara");
		String txtCatatanAdd = getParam("txtCatatanAdd");
		String socNegeriBicara = getParam("socNegeri");
		String socPegawaiPengendali = getParam("socPegawaiPengendali");

		FrmPrmhnnSek8KptsanBicaraData.add_MaklumatKolateral(idpermohonan,usid,id_perbicaraan,
				txdTarikhPerakuanAdd,txdTarikhBicara,txtMasaBicara,socTempatBicara,txtCatatanAdd,
				socNegeriBicara,socPegawaiPengendali);
	}

		private void insertBorangJ(String id_perbicaraan,String usid) throws Exception {

				String socNegeri = getParam("socNegeri");
				String txdTarikhRujukanAdd = getParam("txdTarikhRujukanAdd");
				String txtFaktaGuamanAdd = getParam("txtFaktaGuamanAdd");
				String txtPendapatAdd = getParam("txtPendapatAdd");
				String jenis_rujukan = getParam("jenis_rujukan");
				String socPegawai = getParam("socPegawai");
				String id_pejabat = getParam("id_pejabat");

			    FrmPrmhnnSek8KptsanBicaraData.insertBorangJ(id_perbicaraan,usid,socNegeri,
			    		txdTarikhRujukanAdd,txtFaktaGuamanAdd,txtPendapatAdd,jenis_rujukan,
			    		socPegawai,id_pejabat);
		}

		private void insertBorangJMufti( String usid,String id_perbicaraan,String txdTarikhRujukanAdd,String idNegeri,
				String jenis_rujukan, String txtnamapej,String txtAlamat1,String txtAlamat2,String txtAlamat3,String txtPoskod,String idBandar,
				String txtTelefon,String txtfax,String idUnitPSK,String txtFaktaGuamanAdd,String txtPendapatAdd ) throws Exception {


		    FrmPrmhnnSek8KptsanBicaraData.insertBorangJMufti( usid,id_perbicaraan,txdTarikhRujukanAdd,idNegeri,
					jenis_rujukan, txtnamapej,txtAlamat1,txtAlamat2,txtAlamat3,txtPoskod,idBandar,
					txtTelefon,txtfax,idUnitPSK,txtFaktaGuamanAdd,txtPendapatAdd );
	}


		private void insertBorangJMS(String usid,String id_perbicaraan,String txdTarikhRujukanAdd,
				String idNegeriMahkamah,String jenis_rujukan,String idMahkamah, String idUnitPSK,
				String txtFaktaGuamanAdd,String txtPendapatAdd) throws Exception {


		    FrmPrmhnnSek8KptsanBicaraData.insertBorangJMS( usid,id_perbicaraan,txdTarikhRujukanAdd,
		    		idNegeriMahkamah,jenis_rujukan, idMahkamah, idUnitPSK,
		    		txtFaktaGuamanAdd,txtPendapatAdd );
	}


		private void insertBorangJDTL(String usid,String idBorangJ) throws Exception {

		    String[] cbsemaks = request.getParameterValues("cbsemaks");
		    if ( cbsemaks  != null ){
		    	for (int i = 0; i < cbsemaks.length; i++) {
		    		logic4.addLaporan(usid,idBorangJ,cbsemaks[i]);
		    	}
		    }
	}

		private void deleteWarisTerdahulu(String usid,String idBorangJ) throws Exception {

		   logic4.deleteWarisMSterdahulu(usid,idBorangJ);

	}

		private void deleteWarisTerdahuluKolateral( String IdKolateralmst ) throws Exception {

		   logic4.deleteWarisTerdahuluKolateral( IdKolateralmst );
	}

		private void updateMSborangJDTL(String usid,String idBorangJ) throws Exception {

		   String[] cbsemaks = request.getParameterValues("cbsemaks");
		    if ( cbsemaks  != null ){
		    	for (int i = 0; i < cbsemaks.length; i++) {
		    		logic4.updateWarisMS(usid,idBorangJ,cbsemaks[i]);
		    	}
		    }
	}

		private void updateMuftiborangJDTL(String usid,String id_perbicaraan,String idBorangJ) throws Exception {

		    String[] cbsemaks = request.getParameterValues("cbsemaks");
		    if ( cbsemaks  != null ){
		    	for (int i = 0; i < cbsemaks.length; i++) {
		    		logic4.updateWarisMufti(usid,idBorangJ,cbsemaks[i]);
		    	}
		    }
	}

		private void updateMaklumatTangguh(String idpermohonan,String usid,
				String id_perintah,String id_perbicaraan) throws Exception {

		 	String EDITsocPegawaiPengendali = getParam("EDITsocPegawaiPengendali");
		    String txdTarikhPerintahEDIT = getParam("txdTarikhPerintahEDIT");
		    String flag_tangguh = getParam("flag_tangguh");
		 	String txtTempoh = getParam("txtTempoh");
		 	String txtCatatanTangguh = getParam("txtCatatanTangguhEDIT");
		 	String txtPendapatTangguh = getParam("txtPendapatTangguhEDIT");

		    FrmPrmhnnSek8KptsanBicaraData.updateMaklumatTangguh(idpermohonan,usid,id_perintah,
		    		id_perbicaraan,EDITsocPegawaiPengendali,txdTarikhPerintahEDIT,flag_tangguh,
		    		txtCatatanTangguh,txtPendapatTangguh,txtTempoh);
		}


		private void updateMaklumatBatal(String usid,String id_perintah,
				String id_perbicaraan,String idpermohonan) throws Exception {

		 	String flag_jenis_keputusan = getParam("flag_jenis_keputusan");
		 	String EDITsocPegawaiPengendali = getParam("EDITsocPegawaiPengendali");
		    String txdTarikhPerintahEDIT = getParam("txdTarikhPerintahEDIT");
		    String txtCatatanBatal = getParam("txtCatatanBatalEDIT");
		    String flag_batal = getParam("flag_batal");

		    FrmPrmhnnSek8KptsanBicaraData.updateMaklumatBatal( usid,id_perintah,
					id_perbicaraan,idpermohonan,flag_jenis_keputusan,EDITsocPegawaiPengendali,
		    		txdTarikhPerintahEDIT,txtCatatanBatal,flag_batal );
		}


		private void edit_status_tblrujsuburusanstatusfail_batal(String usid,String idpermohonan,
				String id_perbicaraan,String idsuburusanstatusfail,String idFail) throws Exception {

		    FrmPrmhnnSek8KptsanBicaraData.edit_statusTblrujsuburusanstatusfail_batal(usid,idpermohonan,
		    		id_perbicaraan,idsuburusanstatusfail,idFail);
		}

		private void edit_status_batal(String idpermohonan,String usid) throws Exception {

		    FrmPrmhnnSek8KptsanBicaraData.edit_status_batal(idpermohonan,usid);
		}

		private void add_MaklumatBatal(String usid,String idpermohonan,String id_perbicaraan) throws Exception {

			//TBLPPKPERINTAH
			String txdTarikhPerintah = getParam("txdTarikhPerintah");
			String EDITsocPegawaiPengendali = getParam("EDITsocPegawaiPengendali");
			String flag_batal = getParam("flag_batal");
			String flag_jenis_keputusan = getParam("flag_jenis_keputusan");
			String txtCatatanBatal = getParam("txtCatatanBatal");

			FrmPrmhnnSek8KptsanBicaraData.add_MaklumatBatal(usid,idpermohonan,id_perbicaraan,txdTarikhPerintah,
					EDITsocPegawaiPengendali,flag_batal,flag_jenis_keputusan,txtCatatanBatal);
		}

	private void edit_status_tblrujsuburusanstatusfail_Tangguh(String idpermohonan,String usid,
			String idFail,String idsuburusanstatusfail) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_statusTblrujsuburusanstatusfail_Tangguh(idpermohonan,
	    		usid,idFail,idsuburusanstatusfail);
	}

	private void edit_status_tangguh(String idpermohonan,String usid,String idsuburusanstatusfail) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_status_tangguh(idpermohonan,usid,idsuburusanstatusfail);
	}

	private void edit_status_batalMT(String idpermohonan,String usid) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_status_batalMT(idpermohonan,usid);
	}

	private void edit_status_tblrujsuburusanstatusfail_batalMT(String idpermohonan,String idsuburusanstatusfail,
			String usid,String idFail) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_statusTblrujsuburusanstatusfail_BatalMT(idpermohonan,
	    		idsuburusanstatusfail,usid,idFail);
	}


	private void updateMahkamah(String usid,String id_perintah,String id_perbicaraan) throws Exception {

    	String jenisPerintah = getParam("jenisPerintah");
    	String txdTarikhPerintahEdit = getParam("txdTarikhPerintah");
    	//String id_pejabat = getParam("socTempatBicara");
    	String id_pejabat = getParam("socMahkamah");
    	myLogger.info("id_pejabat = "+id_pejabat);
    	String id_unitpsk = getParam("EDITsocPegawaiPengendali");

	    FrmPrmhnnSek8KptsanBicaraData.updateDataMahkamah(usid,id_perintah,id_perbicaraan,
	    		jenisPerintah,txdTarikhPerintahEdit,id_pejabat,id_unitpsk);
	}

	//*Menunggu Keputusan Rujukan Mahkamah Tinggi
	private void insertMahkamah(String usid, String id_perbicaraan) throws Exception {

    	String id_pejabat = getParam("id_pejabat");
		String id_unitpsk = getParam("socPegawai");
		String txdTarikhPerintahAdd = getParam("txdTarikhPerintahAdd");
		String jenisPerintah = getParam("jenisPerintah");

	    FrmPrmhnnSek8KptsanBicaraData.insertDataMahkamah(usid,id_perbicaraan,id_pejabat,id_unitpsk,
	    		txdTarikhPerintahAdd,jenisPerintah);
	}

	//*Menunggu Keputusan Rujukan Mahkamah Tinggi
	private void insertMahkamah_updateTblppkperintah(String usid,String id_perbicaraan,String id_perintah) throws Exception {

    	String id_pejabat = getParam("id_pejabat");
		String id_unitpsk = getParam("socPegawai");
		String txdTarikhPerintahAdd = getParam("txdTarikhPerintahAdd");
		String jenisPerintah = getParam("jenisPerintah");

	    FrmPrmhnnSek8KptsanBicaraData.insertDataMahkamah_updateTblppkperintah(usid,id_perbicaraan,id_perintah,
	    		id_pejabat,id_unitpsk,txdTarikhPerintahAdd,jenisPerintah);
	}

		private void tambahMaklumatBaitulMalEDIT(String usid,String idpermohonan) throws Exception {

			//TBLPPKBAYARAN
			String txtJumBayaranBaitulmalEDIT = getParam("txtJumBayaranBaitulmalEDIT");
			String txtNomborResitBaitulmalEDIT = getParam("txtNomborResitBaitulmalEDIT");
			String txdTarikhBayaranBaitulmalEDIT = getParam("txdTarikhBayaranBaitulmalEDIT");

		    FrmPrmhnnSek8KptsanBicaraData.add_BayaranBaitulMalEDIT(usid,idpermohonan,
		    		txtJumBayaranBaitulmalEDIT,txtNomborResitBaitulmalEDIT,txdTarikhBayaranBaitulmalEDIT);
		}

		private void tambahMaklumatPusakaEDIT(String usid,String idpermohonan) throws Exception {

			//TBLPPKBAYARAN
			String txtJumBayaranPusakaEDIT = getParam("txtJumBayaranPusakaEDIT");
			String txtNomborResitPusakaEDIT = getParam("txtNomborResitPusakaEDIT");
			String txdTarikhBayaranPusakaEDIT = getParam("txdTarikhBayaranPusakaEDIT");

		    FrmPrmhnnSek8KptsanBicaraData.add_BayaranPusakaEDIT(usid,idpermohonan,txtJumBayaranPusakaEDIT,
		    		txtNomborResitPusakaEDIT,txdTarikhBayaranPusakaEDIT);
		}

		private void editMaklumatPusaka(String usid,String idBayaran,String noResit,
				String jumlahBayaran,String tarikhBayaran) throws Exception {

		    FrmPrmhnnSek8KptsanBicaraData.BayaranPusakaEDIT(usid, idBayaran, noResit,
					 jumlahBayaran, tarikhBayaran);
		}

		private void editMaklumatBaitulMal(String usid,String idBayaran,String noResit,
				String jumlahBayaran,String tarikhBayaran) throws Exception {

		    FrmPrmhnnSek8KptsanBicaraData.BayaranBaitulMalEDIT(usid, idBayaran, noResit,
					 jumlahBayaran, tarikhBayaran);
		}

		private void editMaklumatPerintah(String usid,String idBayaran,String noResit,
				String jumlahBayaran,String tarikhBayaran) throws Exception {

		    FrmPrmhnnSek8KptsanBicaraData.BayaranPerintahEDIT(usid, idBayaran, noResit,
					 jumlahBayaran, tarikhBayaran);
		}

	private void updateMaklumatSelesai(String usid,String idpermohonan,String id_perintah,String id_perbicaraan) throws Exception {
/*
	    String id_bayaran_perintah = "24" ;
	    String id_bayaran_pusaka = "26" ;
	    String id_bayaran_baitulmal = "29" ;
*/
		
		String id_bayaran_perintah = getParam("id_bayaran_perintah");
		String id_bayaran_pusaka = getParam("id_bayaran_pusaka");
		String id_bayaran_baitulmal = getParam("id_bayaran_baitulmal");
		
	 	String EDITflag_jenis_keputusan = getParam("EDITflag_jenis_keputusan");
	    String txtJumBayaranEDIT = getParam("txtJumBayaranEDIT");
	    String txtNomborResitPerintahEDIT = getParam("txtNomborResitPerintahEDIT");
	    String txdTarikhBayaranPerintahEDIT = getParam("txdTarikhBayaranPerintahEDIT");
	    String txtJumBayaranPusakaEDIT = getParam("txtJumBayaranPusakaEDIT");
	    String txtNomborResitPusakaEDIT = getParam("txtNomborResitPusakaEDIT");
	    String txdTarikhBayaranPusakaEDIT = getParam("txdTarikhBayaranPusakaEDIT");
	    String txtJumBayaranBaitulmalEDIT = getParam("txtJumBayaranBaitulmalEDIT");
	    String txtNomborResitBaitulmalEDIT = getParam("txtNomborResitBaitulmalEDIT");
	    String txdTarikhBayaranBaitulmalEDIT = getParam("txdTarikhBayaranBaitulmalEDIT");
	    String txtCatatanSelesaiEDIT = getParam("txtCatatanSelesaiEDIT");
	    String EDITsocPegawaiPengendali = getParam("EDITsocPegawaiPengendali");
	    String txdTarikhPerintahEDIT = getParam("txdTarikhPerintahEDIT");
	    String txtJustifikasiPegawai = getParam("justifikasi_pegawai");
	    
	    String check_kiv = getParam("check_kiv");
	    System.out.println("--- check_kiv -- "+check_kiv);
	    String check_doc = getParam("check_doc");
	    String valueKIV = getParam("valueKIV");
	    System.out.println("--- valueKIV -- "+valueKIV);
	    
	    //if(check_kiv.equals("")){
	    //	check_kiv = valueKIV;
	   // }else{
	    //	if(!valueKIV.equals("")){
	    //		check_kiv = valueKIV;
	    //	}
	   // }
	    
	    //System.out.println("--- check_kiv -- "+check_kiv);
	    //System.out.println("--- check_doc -- "+check_doc);
	    
	    //System.out.println("--- valueKIV -- "+valueKIV);
	    
	    String date_kiv = getParam("date_kiv");
	    String catatan_kiv = getParam("catatan_kiv");
	    
	    System.out.println("--- txdTarikhPerintahEDIT -- "+txdTarikhPerintahEDIT);
	    
	    System.out.println("--- EDITsocPegawaiPengendali -- "+EDITsocPegawaiPengendali);

	    FrmPrmhnnSek8KptsanBicaraData.updateMaklumatSelesai(usid,idpermohonan,id_perintah,id_perbicaraan,
	    		id_bayaran_perintah,id_bayaran_pusaka,id_bayaran_baitulmal,EDITflag_jenis_keputusan,txtJumBayaranEDIT,
	    		txtNomborResitPerintahEDIT,txdTarikhBayaranPerintahEDIT,txtJumBayaranPusakaEDIT,txtNomborResitPusakaEDIT,
	    		txdTarikhBayaranPusakaEDIT,txtJumBayaranBaitulmalEDIT,txtNomborResitBaitulmalEDIT,txdTarikhBayaranBaitulmalEDIT,
	    		txtCatatanSelesaiEDIT,EDITsocPegawaiPengendali,txdTarikhPerintahEDIT,check_kiv,date_kiv,catatan_kiv,txtJustifikasiPegawai);
	}


	private void edit_status_tblrujsuburusanstatusfail(String usid,String idpermohonan,String id_perbicaraan,
			String idsuburusanstatusfail,String idFail) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_statusTblrujsuburusanstatusfail(usid,idpermohonan,id_perbicaraan,
				idsuburusanstatusfail,idFail);
	}

	private void edit_status_selesai(String idpermohonan,String usid) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_status_selesai(idpermohonan,usid);
	}

	private void add_MaklumatBayaranBaitulMal(String usid,String idpermohonan,String id_perbicaraan) throws Exception {

		//TBLPPKPERINTAH
		String txdTarikhPerintah = getParam("txdTarikhPerintah");
		String EDITsocPegawaiPengendali = getParam("EDITsocPegawaiPengendali");
		String flag_jenis_keputusan = getParam("flag_jenis_keputusan");
		String txtCatatanSelesai = getParam("txtCatatanSelesai");

		//TBLPPKBAYARAN
		String id_jenisbayaranBaitulMal = getParam("id_jenisbayaranBaitulMal");
		String txtJumBayaranBaitulmal = getParam("txtJumBayaranBaitulmal");
		String txtNomborResitBaitulmal = getParam("txtNomborResitBaitulmal");
		String txdTarikhBayaranBaitulmal = getParam("txdTarikhBayaranBaitulmal");

	    FrmPrmhnnSek8KptsanBicaraData.add_BayaranBaitulMal(usid,idpermohonan,id_perbicaraan,txdTarikhPerintah,
	    		EDITsocPegawaiPengendali,flag_jenis_keputusan,txtCatatanSelesai,id_jenisbayaranBaitulMal,txtJumBayaranBaitulmal,
	    		txtNomborResitBaitulmal,txdTarikhBayaranBaitulmal);
	}

	private void add_MaklumatBayaranCukaiPusaka(String usid,String idpermohonan,String id_perbicaraan) throws Exception {

		//TBLPPKPERINTAH
		String txdTarikhPerintah = getParam("txdTarikhPerintah");
		String EDITsocPegawaiPengendali = getParam("EDITsocPegawaiPengendali");
		String flag_jenis_keputusan = getParam("flag_jenis_keputusan");
		String txtCatatanSelesai = getParam("txtCatatanSelesai");

		//TBLPPKBAYARAN
		String id_jenisbayaranCukaiPusaka = getParam("id_jenisbayaranCukaiPusaka");
		String txtJumBayaranPusaka = getParam("txtJumBayaranPusaka");
		String txtNomborResitPusaka = getParam("txtNomborResitPusaka");
		String txdTarikhBayaranPusaka = getParam("txdTarikhBayaranPusaka");

	    FrmPrmhnnSek8KptsanBicaraData.add_BayaranPusaka(usid,idpermohonan,id_perbicaraan,txdTarikhPerintah,
	    		EDITsocPegawaiPengendali,flag_jenis_keputusan,txtCatatanSelesai,id_jenisbayaranCukaiPusaka,
	    		txtJumBayaranPusaka,txtNomborResitPusaka,txdTarikhBayaranPusaka);
	}

	private void add_MaklumatBayaranPerintah(String idpermohonan,String usid) throws Exception {

		//TBLPPKPERINTAH
//		String txdTarikhPerintah = getParam("txdTarikhPerintah");
//		String EDITsocPegawaiPengendali = getParam("EDITsocPegawaiPengendali");
//		String flag_jenis_keputusan = getParam("flag_jenis_keputusan");

		//TBLPPKBAYARAN
		String id_jenisbayaranPerintah = getParam("id_jenisbayaranPerintah");
		String txtJumBayaran = getParam("txtJumBayaran");
		String txtNomborResitPerintah = getParam("txtNomborResitPerintah");
		String txdTarikhBayaranPerintah = getParam("txdTarikhBayaranPerintah");

		FrmPrmhnnSek8KptsanBicaraData.add_BayaranPerintah(idpermohonan,usid,
				id_jenisbayaranPerintah,txtJumBayaran,txtNomborResitPerintah,txdTarikhBayaranPerintah);
	}

	private void add_maklumatPerintah(String id_perbicaraan,
			String usid, String txtCatatanSelesai) throws Exception {

		//TBLPPKPERINTAH
		String txdTarikhPerintah = getParam("txdTarikhPerintah");
		String EDITsocPegawaiPengendali = getParam("EDITsocPegawaiPengendali");
		String flag_jenis_keputusan = getParam("flag_jenis_keputusan");
		
		String check_kiv = getParam("check_kiv");
		String check_doc = getParam("check_doc");
		String valueKIV = getParam("valueKIV");
		
		String date_kiv = getParam("date_kiv");
		String catatan_kiv = getParam("catatan_kiv");
		
		if(check_kiv.equals("")){
			check_kiv = valueKIV;
		}
		System.out.println("check_kiv==="+check_kiv);	
		System.out.println("valueKIV==="+valueKIV);
		
		FrmPrmhnnSek8KptsanBicaraData.add_maklumatPerintah(id_perbicaraan,usid,txdTarikhPerintah,
				EDITsocPegawaiPengendali,flag_jenis_keputusan,txtCatatanSelesai,check_kiv,date_kiv,catatan_kiv);
	}

	private void edit_status_ROTSkeputusan(String idpermohonan, String usid) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_status_ROTSkeputusan(idpermohonan, usid);
	}

	private void edit_status_tblrujsuburusanstatusfail_ROTSkeputusan(String idsuburusanstatusfail,
			String usid, String id_perbicaraan, String id_fail, String idpermohonan) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_statusTblrujsuburusanstatusfail_ROTSkeputusan( idsuburusanstatusfail,
	    		usid, id_perbicaraan, id_fail, idpermohonan);
	}

	private void edit_status_tblppkpermohonanMS(String idPermohonan, String usid) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_status_tblppkpermohonanMS(idPermohonan, usid);

	}

	private void edit_status_tblppkpermohonanMufti(String idPermohonan, String usid) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_status_tblppkpermohonanMufti(idPermohonan, usid);
	}

	private void edit_status_tblrujsuburusanstatusfailMS(String idsuburusanstatusfail, String usid, String id_perbicaraan, String idFail, String idPermohonan) throws Exception {

		FrmPrmhnnSek8KptsanBicaraData.edit_status_tblrujsuburusanstatusfailMS( idsuburusanstatusfail, usid, id_perbicaraan, idFail, idPermohonan);

	}

	private void edit_status_tblrujsuburusanstatusfailMufti(String idsuburusanstatusfail, String usid, String id_perbicaraan, String idFail, String idPermohonan) throws Exception {

		FrmPrmhnnSek8KptsanBicaraData.edit_status_tblrujsuburusanstatusfailMufti( idsuburusanstatusfail, usid, id_perbicaraan, idFail, idPermohonan);

	}

	private void updateKeputusan_tblppkkolateralmst( String id, String IdKolateralmst, String usid, String flag_keputusan,
			String txtBayaranPerintahKol, String txtNomborResitPerintahKol, String txtCatatanKeputusan, String txdTarikhBayaranPerintahKol, String idBayaran ) throws Exception {

		FrmPrmhnnSek8KptsanBicaraData.updateKeputusan_tblppkkolateralmst( id, IdKolateralmst, usid, flag_keputusan,
				txtBayaranPerintahKol, txtNomborResitPerintahKol, txtCatatanKeputusan, txdTarikhBayaranPerintahKol, idBayaran );
	}

	private void updateKeputusan( String idpermohonan, String IdKolateralmst, String usid, String flag_keputusan,
			String txtBayaranPerintahKol, String txtNomborResitPerintahKol, String txtCatatanKeputusan, String txdTarikhBayaranPerintahKol ) throws Exception {

		FrmPrmhnnSek8KptsanBicaraData.updateKeputusan( idpermohonan, IdKolateralmst, usid, flag_keputusan,
				txtBayaranPerintahKol, txtNomborResitPerintahKol, txtCatatanKeputusan, txdTarikhBayaranPerintahKol );
	}

	private void edit_status_KeputusanKolateral( String id, String usid ) throws Exception {
	    FrmPrmhnnSek8KptsanBicaraData.edit_status_KeputusanKolateral( id, usid );
	}

	private void edit_status_tblrujsuburusanstatusfail_KeputusanKolateral( String id, String idsuburusanstatusfail, String idFail, String usid ) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_status_tblrujsuburusanstatusfail_KeputusanKolateral( id,
	    		idsuburusanstatusfail, idFail, usid );
	}

	private void createIdPerintah(String usid, String id_perbicaraan) throws Exception {

		//TBLPPKPERINTAH
		String txdTarikhPerintah = getParam("txdTarikhPerintah");
		String EDITsocPegawaiPengendali = getParam("EDITsocPegawaiPengendali");
		String txtCatatanSelesai = getParam("txtCatatanSelesai");

		FrmPrmhnnSek8KptsanBicaraData.createIdPerintah(id_perbicaraan,usid,txdTarikhPerintah,
				EDITsocPegawaiPengendali,txtCatatanSelesai);
	}

	private void addMaklumatBayaranPerintah(String usid, String idpermohonan) throws Exception {

		//TBLPPKBAYARAN
		String txtJumBayaran = getParam("txtJumBayaran");
		String txtNomborResitPerintah = getParam("txtNomborResitPerintah");
		String txdTarikhBayaranPerintah = getParam("txdTarikhBayaranPerintah");

		FrmPrmhnnSek8KptsanBicaraData.addMaklumatBayaranPerintah(usid, idpermohonan,
				txtJumBayaran, txtNomborResitPerintah, txdTarikhBayaranPerintah);
	}

	private void addMaklumatBayaranBaitulMal(String usid, String idpermohonan) throws Exception {

		//TBLPPKBAYARAN
		String txtJumBayaran = getParam("txtJumBayaran");
		String txtNomborResitPerintah = getParam("txtNomborResitPerintah");
		String txdTarikhBayaranPerintah = getParam("txdTarikhBayaranPerintah");

		FrmPrmhnnSek8KptsanBicaraData.addMaklumatBayaranBaitulMal(usid, idpermohonan,
				txtJumBayaran, txtNomborResitPerintah, txdTarikhBayaranPerintah);
	}

	private void addMaklumatBayaranPusaka(String usid, String idpermohonan) throws Exception {

		//TBLPPKBAYARAN
		String txtJumBayaran = getParam("txtJumBayaran");
		String txtNomborResitPerintah = getParam("txtNomborResitPerintah");
		String txdTarikhBayaranPerintah = getParam("txdTarikhBayaranPerintah");

		FrmPrmhnnSek8KptsanBicaraData.addMaklumatBayaranPusaka(usid, idpermohonan,
				txtJumBayaran, txtNomborResitPerintah, txdTarikhBayaranPerintah);
	}

	private void addKeputusan_Editblppkkolateralmst( String idpermohonan, String IdKolateralmst, String usid, String flag_keputusan,
			String txtBayaranPerintahKol, String txtNomborResitPerintahKol, String txtCatatanKeputusan, String txdTarikhBayaranPerintahKol ) throws Exception {

		FrmPrmhnnSek8KptsanBicaraData.addKeputusan_Editblppkkolateralmst( idpermohonan, IdKolateralmst, usid, flag_keputusan,
				txtBayaranPerintahKol, txtNomborResitPerintahKol, txtCatatanKeputusan, txdTarikhBayaranPerintahKol );
	}

	public void setValue(String checkedTidakHadir,String checkedWarisTidakLengkap,
			String checkedMahkamahTinggi,String checkedBuktiTidakLengkap, String checkedMahkamahSyariah,
			String checkedPertelingkahanKolateral, String checkedSijilFaraid, String checkedSuratSetuju,
			String checkedSebabLain ) {

		this.checkedTidakHadir = checkedTidakHadir;
		this.checkedWarisTidakLengkap = checkedWarisTidakLengkap;
		this.checkedMahkamahTinggi = checkedMahkamahTinggi;
		this.checkedBuktiTidakLengkap = checkedBuktiTidakLengkap;
		this.checkedMahkamahSyariah = checkedMahkamahSyariah;
		this.checkedPertelingkahanKolateral = checkedPertelingkahanKolateral;
		this.checkedSijilFaraid = checkedSijilFaraid;
		this.checkedSuratSetuju = checkedSuratSetuju;
		this.checkedSebabLain = checkedSebabLain;
	}

	public void setValueFlagBatal(String checkedMahkamahTinggiWasiat,String checkedTidakHadir3Kali,
			String checkedPermintaanPemohon,String checkedMahkamahTinggi2Juta,String checkedSebabLainBatal ) {

		this.checkedMahkamahTinggiWasiat = checkedMahkamahTinggiWasiat;
		this.checkedTidakHadir3Kali = checkedTidakHadir3Kali;
		this.checkedPermintaanPemohon = checkedPermintaanPemohon;
		this.checkedMahkamahTinggi2Juta = checkedMahkamahTinggi2Juta;
		this.checkedSebabLainBatal = checkedSebabLainBatal;
	}

	public void setValueFlagJenisKeputusan(String checkedSelesai,String checkedTangguh,String checkedBatal ) {

		this.checkedSelesai = checkedSelesai;
		this.checkedTangguh = checkedTangguh;
		this.checkedBatal = checkedBatal;
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
		this.context.put("listNotis",paging.getPage(page));
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
	
	private void headerppk_baru(HttpSession session,String id_permohonan,String flag_permohonan,String id_fail,String flag_fail) throws Exception {
		//hashtable maklumat header
		this.context.put("headerppk",mainheader.getHeaderData(session,id_permohonan,flag_permohonan,id_fail,flag_fail));
		Vector list_sub = null;
		list_sub = mainheader.carianFail(id_permohonan,flag_permohonan,id_fail,flag_fail);		
		this.context.put("list_sub_header",list_sub);
		this.context.put("flag_jenis_vm","ajax");
		
		Vector getListBayaranPerintah = null;
		getListBayaranPerintah = mainheader.getListBayaranPerintah(id_permohonan,"");
		this.context.put("getListBayaranPerintah",getListBayaranPerintah);
		
		Vector getListBayaranCukai = null;
		getListBayaranCukai = mainheader.getListBayaranCukai(id_permohonan,"");
		this.context.put("getListBayaranCukai",getListBayaranCukai);
		
		Vector getListBayaranBaitulmal = null;
		getListBayaranBaitulmal = mainheader.getListBayaranBaitulmal(id_permohonan,"");
		this.context.put("getListBayaranBaitulmal",getListBayaranBaitulmal);
	}
	private void headerppk_baru_default()
	{
		Hashtable headerppk = null;
		this.context.put("headerppk","");
		this.context.put("list_sub_header","");
		this.context.put("flag_jenis_vm","ajax");
		this.context.put("getListBayaranPerintah","");
		this.context.put("getListBayaranCukai","");
		this.context.put("getListBayaranBaitulmal","");
	}
	
	
	
	/** ADD BY PEJE
	 * BUNDAR VALUE BAYARAN BASED ON PEKELILING
	 * @param bayaranYuran
	 * @return
	 */
	private Double getBundaranBayaran(double bayaran) {
		bayaran = Utils.parseDouble(Utils.RemoveComma(Utils.format2Decimal(bayaran)));
		String sen = String.valueOf(bayaran);
		sen = sen.substring(sen.length()-1, sen.length());
		
		if ("1".equals(sen) || "6".equals(sen)){
			bayaran = bayaran - 0.01D;
		} else if ("2".equals(sen) || "7".equals(sen)){
			bayaran = bayaran - 0.02D;
		} else if ("3".equals(sen) || "8".equals(sen)){
			bayaran = bayaran + 0.02D;
		} else if ("4".equals(sen) || "9".equals(sen)){
			bayaran = bayaran + 0.01D;
		}
		return bayaran;
	}
	
}
