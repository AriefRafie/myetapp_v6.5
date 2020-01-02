package ekptg.view.ppk;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8BicaraData;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8KptsanBicaraData;
import ekptg.model.ppk.FrmPrmhnnSek8Notis;
import ekptg.model.ppk.FrmSenaraiFailKptsPerbcrnData;
//import lebah.portal.velocity.VTemplate;
//import org.apache.velocity.Template;
public class FrmSenaraiFailKeputusanBicaraanSek17 extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmSenaraiFailKeputusanBicaraanSek17.class);
	
	FrmSenaraiFailKptsPerbcrnData logic2 = new FrmSenaraiFailKptsPerbcrnData();
	FrmPrmhnnSek8BicaraData logic3 = new FrmPrmhnnSek8BicaraData();
	FrmPrmhnnSek8KptsanBicaraData logic4 = new FrmPrmhnnSek8KptsanBicaraData();
	FrmPrmhnnSek8Notis logic5 = new FrmPrmhnnSek8Notis();
	FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	

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

	public String doTemplate2() throws Exception
    {
		HttpSession session = request.getSession();
		String doPost = (String)session.getAttribute("doPost");
    	String vm = "";
    	headerppk_baru_default();

    	Vector list = new Vector();
    	Vector listPemohon = new Vector();
    	Vector dataBayaran = new Vector();
    	Vector dataPerintah = new Vector();
    	Vector getrecord_perintah = new Vector();
    	Vector alamatTempatBicara = new Vector();
    	Vector dataNotis = new Vector();
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
    	Vector PermohonanROTS = new Vector();
    	Vector PermohonanMufti = new Vector();
    	Vector senarai_waris = new Vector();
    	Vector getIdPerintah = new Vector();
    	Vector PermohonanROTSkeputusan = new Vector();
    	Vector PerintahTangguhROTS = new Vector();
    	Vector OBList = new Vector();
    	Vector PerintahTangguhMufti = new Vector();
    	Vector getJumlahBayaran17 = new Vector();
    	Vector getMaklumatPermohonan17 = new Vector();
    	Vector checkingNilaianAmanahRaya = new Vector();
    	Vector getExistDataBayaran = new Vector();

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
    	myLogger.info("SUBMIT :: "+submit);
    	this.context.put("Util",new lebah.util.Util());
    	String flagFromSenaraiFailSek8 = getParam("flagFromSenaraiFailSek8");
    	String flagFromSenaraiPermohonanSek8 = getParam("flagFromSenaraiPermohonanSek8");
    	String flagForView = getParam("flagForView");

		String usid="";
   		usid=(String)session.getAttribute("_ekptg_user_id");

   		String idpermohonan = getParam("idpermohonan");
	    this.context.put("idpermohonan",idpermohonan);

	    String idsuburusanstatusfail = getParam("idsuburusanstatusfail");
	    this.context.put("idsuburusanstatusfail",idsuburusanstatusfail);

		Hashtable getstatusID = FrmPrmhnnSek8KptsanBicaraData.getListStatusID(idpermohonan);
		this.context.put("data", getstatusID);

	    //get info pemohon
		logic3.setListSemak(idpermohonan, usid);
		list = logic3.getListSemak();
		//hashtable maklumat header
		headerppk_baru(session,idpermohonan,"Y","","T");
		
		
		String idSimati = "";
		String idstatus = "";
		String idNegeriMhn = "";
		String idFail = "";
		String idPejabatJKPTG = "";
		String id_permohonansimati = "";
		if ( list.size() != 0 ){
			Hashtable ls = (Hashtable) list.get(0);
			idSimati = (String)ls.get("idSimati");
			idstatus = (String)ls.get("id_Status");
    		idNegeriMhn = (String)ls.get("pmidnegeri");
    		idFail = (String)ls.get("idFail");
    		idPejabatJKPTG =  (String)ls.get("id_pejabatjkptg");
    		id_permohonansimati = (String)ls.get("id_permohonansimati");
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
        		idUnitPsk = Long.parseLong(idn.get("id_unitpsk").toString());
        		tarikh_bicara = (String)idn.get("tarikh_bicara");
        		id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    		this.context.put("tarikh_bicara",tarikh_bicara);
    		this.context.put("id_perbicaraan",id_perbicaraan);

			context.put("selectEditPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,null));

    		//call flag
    		context.put("mode", "add");
    		context.put("flag", "");
    		context.put("button", "kembali");
    		context.put("tarikh", "perintah");

    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

    	}else if("papar_selesai".equals(submit)){

    			//get id_keputusanpermohonan - tiada id_perbicaraan
    			FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
    			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
    			String idkp="";
    			if (dataPerintah.size()!=0){
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
        			tarikh_bicara = (String)idn.get("tarikh_bicara");
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
    			String id_perintah ="";
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

    			//get jumlah_harta_tarikhmohon
    			FrmPrmhnnSek8KptsanBicaraData.setJumlahBayaran17(id_permohonansimati);
    			getJumlahBayaran17 = FrmPrmhnnSek8KptsanBicaraData.getJumlahBayaran17();

    			FrmPrmhnnSek8KptsanBicaraData.setMaklumatPermohonan17(idpermohonan);
    			getMaklumatPermohonan17 = FrmPrmhnnSek8KptsanBicaraData.getMaklumatPermohonan17();

    			//checking TBLPPKHA for NilaianAmanahRaya
  				checkingNilaianAmanahRaya = logic2.checkingNilaianAmanahRaya(id_permohonansimati);
  				double nilai_ha_tarikhmohon;
  				if(checkingNilaianAmanahRaya.size()!=0){

  					myLogger.info("ADA NILAIAN HARTA AMANAH RAYA");
  					Hashtable nilaian = (Hashtable) checkingNilaianAmanahRaya.get(0);
  					nilai_ha_tarikhmohon = Double.parseDouble(nilaian.get("nilai_ha_tarikhmohon").toString());

	    			if(getJumlahBayaran17.size()!=0){
	    				Hashtable a = (Hashtable) getJumlahBayaran17.get(0);
	    				Hashtable z = (Hashtable) getMaklumatPermohonan17.get(0);
	    		   		double jumlah_harta17_tarikhmohon;
	    	    		double bayaranYuranPerintah17;

	    	    		jumlah_harta17_tarikhmohon = Double.parseDouble(a.get("sumharta").toString());
	    	    		String batal_kuasa_pentadbir = (String)z.get("batal_kuasa_pentadbir");
	    	    		String batal_p_amanah = z.get("batal_p_amanah").toString();

	    	    		//Azam Add on 20/4/2011 , jumlah_harta17_tarikhmohon >= 1
	    				if ( jumlah_harta17_tarikhmohon >= 1 && jumlah_harta17_tarikhmohon <= 1000 ) {
	    					//bayaranYuranPerintah17 = 10.00 ;
	    					bayaranYuranPerintah17 = (0.2/100) * jumlah_harta17_tarikhmohon ;
	    					//ADD BY PEJE
	    					bayaranYuranPerintah17 = getBundaranBayaran(bayaranYuranPerintah17);
	    				} else if ( (jumlah_harta17_tarikhmohon >= 1001) && (jumlah_harta17_tarikhmohon <= 50000) ){
	    					//bayaranYuranPerintah17 = 30.00 ;
	    					bayaranYuranPerintah17 = (0.2/100) * jumlah_harta17_tarikhmohon ;
	    					//ADD BY PEJE
	    					bayaranYuranPerintah17 = getBundaranBayaran(bayaranYuranPerintah17);
	    				} else {
	    					bayaranYuranPerintah17 = (0.2/100) * jumlah_harta17_tarikhmohon ;
	    					//ADD BY PEJE
	    					bayaranYuranPerintah17 = getBundaranBayaran(bayaranYuranPerintah17);
	    				}
	    				double j = 0;
	    					if (batal_kuasa_pentadbir.equals("Y")) {
	    						j += 30.00 ;
	    					}
	    					if (batal_p_amanah.equals("Y")){
	    						j += 30.00 ;
	    					}
	    				double total = (j + bayaranYuranPerintah17);
	    				this.context.put("txtJumBayaranTerkini", total);
	//    				myLogger.info("BAYARAN YURAN PERINTAH 17 :: "+bayaranYuranPerintah17);
	//    				myLogger.info("BAYARAN J :: "+j);
	//    				myLogger.info("TOTAL BAYARAN KESELURUHAN :: "+total);
	    			}
	    				context.put("dataJumlahBayaran", getJumlahBayaran17);

  				}else{

  					myLogger.info("TIADA NILAIAN HARTA AMANAH RAYA");
  					if(getJumlahBayaran17.size()!=0){
  						Hashtable a = (Hashtable) getJumlahBayaran17.get(0);
  				   		double jumlah_harta17_tarikhmohon;
  			    		//double bayaranYuran;
  				   		jumlah_harta17_tarikhmohon = Double.parseDouble(a.get("sumharta").toString());
  						this.context.put("txtJumHarta", jumlah_harta17_tarikhmohon);
  						myLogger.info("JUMLAH HARTA :: "+jumlah_harta17_tarikhmohon);
  					}
  				}
  				//*end

  	   			//get data TBLPPKPERBICARAAN
    			Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);
    			this.context.put("dataPerbicaraan", h);

    		    FrmPrmhnnSek8KptsanBicaraData.setInfoPerintahList(idpermohonan);
    		    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahViewList();
    		    String idUnitPsk = "";
    		    if ( getrecord_perintah.size() != 0 ){
        			Hashtable d = (Hashtable) getrecord_perintah.get(0);
           			String flag_jenis_keputusan = d.get("flag_jenis_keputusan").toString();
           			idUnitPsk = d.get("id_unitpsk").toString();
           			myLogger.info("IDUNITPSK SEKSYEN17 :: "+idUnitPsk);
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
    		    }
    		    	this.context.put("dataPerintah", getrecord_perintah);

        		//call flag
        		context.put("mode", "view");
        		context.put("flag", "selesai");
        		context.put("button", "");
        		context.put("tarikh", "perintah");

        		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

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
    			tarikh_bicara = (String)idn.get("tarikh_bicara");
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
					flag_tangguh = (String)d.get("flag_tangguh");
					myLogger.info("ID PERINTAH 11111 :: "+id_perintah);
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
    		context.put("mode", "view");
    		context.put("flag", "tangguh");
    		context.put("button", "");
    		context.put("tarikh", "bicara");

    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

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
	    		if (dataNotis.size()!=0){
	    			Hashtable idn = (Hashtable) dataNotis.get(0);
	        		id_perbicaraan = (String)idn.get("id_perbicaraan");
	        		tarikh_bicara = (String)idn.get("tarikh_bicara");
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
					flag_tangguh = (String)d.get("flag_tangguh");
				}
				this.context.put("dataPerintah", getrecord_perintah);

				if (d.get("flag_jenis_keputusan").equals("1")){
					setValueFlagJenisKeputusan("","checked","");
				}
				context.put("TEMPcheckedSelesai",checkedSelesai);
				context.put("TEMPcheckedTangguh",checkedTangguh);
				context.put("TEMPcheckedBatal",checkedBatal);

				if (d.get("flag_tangguh").equals("5")){
					setValue("","","","","checked","","","","");
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

	    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

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
	        		tarikh_bicara = (String)idn.get("tarikh_bicara");
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
					flag_tangguh = (String)d.get("flag_tangguh");
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

        		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

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
    		if (dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
        		String bil = (String)idn.get("bil_bicara");
        		id_perbicaraan = (String)idn.get("id_perbicaraan");
        		tarikh_bicara = (String)idn.get("tarikh_bicara");
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
			    flag_jenis_keputusan = d.get("flag_jenis_keputusan").toString();
				flag_tangguh = d.get("flag_tangguh").toString();
			}
			this.context.put("dataPerintah", getrecord_perintah);

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

			//call flag
    		context.put("mode", "view");
    		context.put("flag", "tangguh");
    		context.put("button", "");
    		context.put("tarikh", "bicara");

    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

		}else if("papar_batal".equals(submit)){

    			//get id_keputusanpermohonan
       			FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
    			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
    			String idkp = "";
    			if (dataPerintah.size()!=0){
    				Hashtable v = (Hashtable) dataPerintah.get(0);
    				idkp = (String)v.get("id_keputusanpermohonan");
    			}
    			

    	    	//--data notis
    			logic4.setListSemakWithData(idkp);
        		dataNotis = logic4.getListSemakWithData();
        		String bil = "";
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
           			flag_jenis_keputusan = d.get("flag_jenis_keputusan").toString();
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
        		//this.context.put("dataPerintahView", dataPerintah); yang ini Original
        		this.context.put("dataPerintahView", getrecord_perintah);
        		context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPskView,"disabled"));

        		//call flag
        		context.put("mode", "view");
        		context.put("flag", "batal");
        		context.put("button", "");
        		context.put("tarikh", "perintah");
        		
        		

        		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

		}else if("openMT".equals(submit)){

		    //clear text input
		    String alamat1 = "";
		    context.put("alamat1", "");
		    String alamat2 = "";
		    context.put("alamat2", "");
		    String alamat3 = "";
		    context.put("alamat3", "");
		    String poskod = "";
		    context.put("poskod", "");
		    String notel = "";
		    context.put("notel", "");
		    String nofax = "";
		    context.put("nofax", "");
		    String txdTarikhRujukanAdd = "";
		    context.put("txdTarikhRujukanAdd", "");

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

			senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris17(id_perbicaraan,getParam("id_permohonansimati_atheader"));
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

			vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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
    		context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn, "socPegawaiPengendali", idUnitPsk," disabled", " class=\"disabled\" style=width:305"));
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

			vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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

    		vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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

    		vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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

			vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

		}else if("openROTS".equals(submit)){

		    //* CLEAR TEXT INPUT
		    context.put("alamat1", "");
		    context.put("alamat2", "");
		    context.put("alamat3", "");
		    context.put("poskod", "");
		    context.put("notel", "");
		    context.put("nofax", "");
		    context.put("txdTarikhRujukanAdd", "");

    		//GET INFO PEMOHON
       		logic3.setListSemak(idpermohonan,usid);
    		list = logic3.getListSemak();
    		//hashtable maklumat header
    		headerppk_baru(session,idpermohonan,"Y","","T");
    		String idNegeri = "";
    		if (list.size()!=0){
    			Hashtable ls = (Hashtable) list.get(0);
    			idNegeri = ls.get("pmidnegeri").toString();
    		}
    			context.put("listSemak", list);

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
			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeri,"socPegawai",idUnitPsk," disabled", " class=\"disabled\" style=width:305"));

			//CHECKING SENARAI WARIS
			senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris17(id_perbicaraan,getParam("id_permohonansimati_atheader"));
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

			vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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

			    vm = "app/ppk/frmPrmhnnSek8MahkamahTinggi17.jsp";

    	}else if("Skrin_KemaskiniTangguh".equals(submit)){

			//GET ID_KEPUTUSAN PERMOHONAN
			FrmPrmhnnSek8KptsanBicaraData.setViewPerintahList(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewPerintahList();
			String idkp = "";
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}

	    	//-- DATA NOTIS
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

			//GET DATA TBLPPKPERBICARAAN
			Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);
			context.put("dataPerbicaraan", h);
			context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,null));

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
		    		flag_jenis_keputusan = d.get("flag_jenis_keputusan").toString();
					flag_tangguh = d.get("flag_tangguh").toString();

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

    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

    	}else if("Skrin_Kemaskini".equals(submit)){	//* -- utk selesai kemaskini

		    FrmPrmhnnSek8KptsanBicaraData.setInfoPerintahList(idpermohonan);
		    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahViewList();
		    long idUnitPskView = 0;
		    String flag_jenis_keputusan = "";
		    if ( getrecord_perintah.size() != 0 ) {
				Hashtable d = (Hashtable) getrecord_perintah.get(0);
				idUnitPskView = Long.parseLong(d.get("id_unitpsk").toString());
				flag_jenis_keputusan = d.get("flag_jenis_keputusan").toString();
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
		    	context.put("dataPerintah", getrecord_perintah);
		    	context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPskView,""));

		    	//get jumlah_harta_tarikhmohon
    			FrmPrmhnnSek8KptsanBicaraData.setJumlahBayaran17(id_permohonansimati);
    			getJumlahBayaran17 = FrmPrmhnnSek8KptsanBicaraData.getJumlahBayaran17();

	   			//checking TBLPPKHA for NilaianAmanahRaya
  				checkingNilaianAmanahRaya = logic2.checkingNilaianAmanahRaya(id_permohonansimati);
  				double nilai_ha_tarikhmohon;
  				if(checkingNilaianAmanahRaya.size()!=0){

  					myLogger.info("ADA NILAIAN HARTA AMANAH RAYA");
  					Hashtable nilaian = (Hashtable) checkingNilaianAmanahRaya.get(0);
  					nilai_ha_tarikhmohon = Double.parseDouble(nilaian.get("nilai_ha_tarikhmohon").toString());
	    			if(getJumlahBayaran17.size()!=0){
	    				Hashtable a = (Hashtable) getJumlahBayaran17.get(0);
	    				Hashtable z = (Hashtable) getMaklumatPermohonan17.get(0);
	    		   		double jumlah_harta17_tarikhmohon;
	    	    		double bayaranYuranPerintah17;

	    	    		jumlah_harta17_tarikhmohon = Double.parseDouble(a.get("sumharta").toString());
	    	    		String batal_kuasa_pentadbir = z.get("batal_kuasa_pentadbir").toString();
	    	    		String batal_p_amanah = z.get("batal_p_amanah").toString();

						//Azam Add on 20/4/2011 , jumlah_harta17_tarikhmohon >= 1
	    				if ( jumlah_harta17_tarikhmohon >= 1 && jumlah_harta17_tarikhmohon <= 1000 ) {
	    					//bayaranYuranPerintah17 = 10.00 ;
	    					bayaranYuranPerintah17 = (0.2/100) * jumlah_harta17_tarikhmohon ;
	    					//ADD BY PEJE
	    					bayaranYuranPerintah17 = getBundaranBayaran(bayaranYuranPerintah17);
	    				} else if ( (jumlah_harta17_tarikhmohon >= 1001) && (jumlah_harta17_tarikhmohon <= 50000) ){
	    					//bayaranYuranPerintah17 = 30.00 ;
	    					bayaranYuranPerintah17 = (0.2/100) * jumlah_harta17_tarikhmohon ;
	    					//ADD BY PEJE
	    					bayaranYuranPerintah17 = getBundaranBayaran(bayaranYuranPerintah17);
	    				} else {
	    					bayaranYuranPerintah17 = (0.2/100) * jumlah_harta17_tarikhmohon ;
	    					//ADD BY PEJE
	    					bayaranYuranPerintah17 = getBundaranBayaran(bayaranYuranPerintah17);
	    				}
	    				double j = 0;
	    					if (batal_kuasa_pentadbir.equals("Y")) {
	    						j += 30.00 ;
	    					}
	    					if (batal_p_amanah.equals("Y")){
	    						j += 30.00 ;
	    					}
	    				double total = (j + bayaranYuranPerintah17);
	    				this.context.put("txtJumBayaranTerkini", total);
	    				this.context.put("txtJumHarta", jumlah_harta17_tarikhmohon);
	    			}
	    				context.put("dataJumlahBayaran", getJumlahBayaran17);

  				}else{
  					myLogger.info("TIADA NILAIAN HARTA AMANAH RAYA");
  					if(getJumlahBayaran17.size()!=0){
  						Hashtable a = (Hashtable) getJumlahBayaran17.get(0);
  				   		double jumlah_harta17_tarikhmohon;
  				   		jumlah_harta17_tarikhmohon = Double.parseDouble(a.get("sumharta").toString());
  				   		this.context.put("txtJumHarta", jumlah_harta17_tarikhmohon);
  					}
  				}
  				//*end

    		//call flag
    		context.put("mode", "edit");
    		context.put("flag", "selesai");
    		context.put("button", "");
    		context.put("tarikh", "perintah");

		    vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

   	}else if("Skrin_KemaskiniBatal".equals(submit)){

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
    		String id_perbicaraan = "";
    		String tarikh_bicara = "";
    		if( dataNotis.size() != 0 ){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
        		tarikh_bicara = (String)idn.get("tarikh_bicara");
        		id_perbicaraan = (String)idn.get("id_perbicaraan");
       		}
    		this.context.put("id_perbicaraan",id_perbicaraan);
    		this.context.put("tarikh_bicara",tarikh_bicara);

		    FrmPrmhnnSek8KptsanBicaraData.setInfoPerintah(idpermohonan,id_perbicaraan);
		    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahView();
		    long idUnitPskView = 0;
		    if ( getrecord_perintah.size() != 0 ){
				Hashtable d = (Hashtable) getrecord_perintah.get(0);
				idUnitPskView = Long.parseLong(d.get("id_unitpsk").toString());
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
		    	context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPskView,""));


		    setValueFlagJenisKeputusan("","","checked");
			context.put("TEMPcheckedSelesai",checkedSelesai);
			context.put("TEMPcheckedTangguh",checkedTangguh);
			context.put("TEMPcheckedBatal",checkedBatal);

    		//call flag
    		context.put("mode", "edit");
    		context.put("flag", "batal");
    		context.put("button", "");
    		context.put("tarikh", "perintah");

		    vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

    	}else if("kemaskini_selesai".equals(submit)){

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

		    FrmPrmhnnSek8KptsanBicaraData.setInfoPerintahList(idpermohonan);
		    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahViewList();
		    long idUnitPskView = 0 ;
		    if(getrecord_perintah.size()!=0){
		    	 Hashtable d = (Hashtable) getrecord_perintah.get(0);
				 idUnitPskView = Long.parseLong(d.get("id_unitpsk").toString());
		    }
    			context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPskView,""));

		    String id_bayaran_perintah = getParam("id_bayaran_perintah");
		    this.context.put("id_bayaran_perintah",id_bayaran_perintah);

			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			String id_perintah = "";
			if ( getIdPerintah.size() != 0 ){
				Hashtable z = (Hashtable) getIdPerintah.get(0);
				id_perintah = (String)z.get("id_perintah");
			}
				context.put("getIdPerintah", getIdPerintah);
				context.put("id_perintah",id_perintah);

			//if ( getParam("id_bayaran_pusaka") == "0" ){
			if ( getParam("id_bayaran_pusaka").equals("0") ){
				myLogger.info("BAYARAN PUSAKA :: "+getParam("id_bayaran_pusaka"));
				String id_bayaran_pusaka = getParam("id_bayaran_pusaka");
			    context.put("id_bayaran_pusaka",id_bayaran_pusaka);
			    if (doPost.equals("true")){
			    	 tambahMaklumatPusakaEDIT (usid,idpermohonan);
			    }
			}

			//if ( getParam("id_bayaran_baitulmal") == "0" ){
			if ( getParam("id_bayaran_baitulmal").equals("0") ){
				myLogger.info("BAYARAN BAITULMAL :: "+getParam("id_bayaran_baitulmal"));
				String id_bayaran_baitulmal = getParam("id_bayaran_baitulmal");
			    this.context.put("id_bayaran_baitulmal",id_bayaran_baitulmal);
			    if (doPost.equals("true")){
			    	tambahMaklumatBaitulMalEDIT (usid,idpermohonan);
			    }
			}

		    updateMaklumatSelesai17(usid,idpermohonan,id_perintah,id_perbicaraan);

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
				VIEWflag_jenis_keputusan = d.get("flag_jenis_keputusan").toString();
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
    			context.put("check_doc",(String)d.get("check_doc"));
    			context.put("valueKIV",(String)d.get("valueKIV"));
				
    			
				context.put("date_kiv",(String)d.get("date_kiv"));
				context.put("catatan_kiv",(String)d.get("catatan_kiv"));
		    }
		    context.put("dataPerintahView", getrecord_perintah);

				//start 091209

		    	//get jumlah_harta_tarikhmohon
    			FrmPrmhnnSek8KptsanBicaraData.setJumlahBayaran17(id_permohonansimati);
    			getJumlahBayaran17 = FrmPrmhnnSek8KptsanBicaraData.getJumlahBayaran17();

	   			//checking TBLPPKHA for NilaianAmanahRaya
  				checkingNilaianAmanahRaya = logic2.checkingNilaianAmanahRaya(id_permohonansimati);
  				double nilai_ha_tarikhmohon;
  				if(checkingNilaianAmanahRaya.size()!=0){

  					myLogger.info("ADA NILAIAN HARTA AMANAH RAYA");
  					Hashtable nilaian = (Hashtable) checkingNilaianAmanahRaya.get(0);
  					nilai_ha_tarikhmohon = Double.parseDouble(nilaian.get("nilai_ha_tarikhmohon").toString());
	    			if(getJumlahBayaran17.size()!=0){
	    				Hashtable a = (Hashtable) getJumlahBayaran17.get(0);
	    				Hashtable z = (Hashtable) getMaklumatPermohonan17.get(0);
	    		   		double jumlah_harta17_tarikhmohon;
	    	    		double bayaranYuranPerintah17;

	    	    		jumlah_harta17_tarikhmohon = Double.parseDouble(a.get("sumharta").toString());
	    	    		String batal_kuasa_pentadbir = z.get("batal_kuasa_pentadbir").toString();
	    	    		String batal_p_amanah = z.get("batal_p_amanah").toString();

						//Azam Add on 20/4/2011 , jumlah_harta17_tarikhmohon >= 1
	    				if ( jumlah_harta17_tarikhmohon >= 1 && jumlah_harta17_tarikhmohon <= 1000 ) {
	    					//bayaranYuranPerintah17 = 10.00 ;
	    					bayaranYuranPerintah17 = (0.2/100) * jumlah_harta17_tarikhmohon ;
	    					//ADD BY PEJE
	    					bayaranYuranPerintah17 = getBundaranBayaran(bayaranYuranPerintah17);
	    				} else if ( (jumlah_harta17_tarikhmohon >= 1001) && (jumlah_harta17_tarikhmohon <= 50000) ){
	    					//bayaranYuranPerintah17 = 30.00 ;
	    					bayaranYuranPerintah17 = (0.2/100) * jumlah_harta17_tarikhmohon ;
	    					//ADD BY PEJE
	    					bayaranYuranPerintah17 = getBundaranBayaran(bayaranYuranPerintah17);
	    				} else {
	    					bayaranYuranPerintah17 = (0.2/100) * jumlah_harta17_tarikhmohon ;
	    					//ADD BY PEJE
	    					bayaranYuranPerintah17 = getBundaranBayaran(bayaranYuranPerintah17);
	    				}
	    				double j = 0;
	    					if (batal_kuasa_pentadbir.equals("Y")) {
	    						j += 30.00 ;
	    					}
	    					if (batal_p_amanah.equals("Y")){
	    						j += 30.00 ;
	    					}
	    				double total = (j + bayaranYuranPerintah17);
	    				this.context.put("txtJumBayaranTerkini", total);
	    				this.context.put("txtJumHarta", jumlah_harta17_tarikhmohon);
	    			}
	    				context.put("dataJumlahBayaran", getJumlahBayaran17);

  				}else{
  					myLogger.info("TIADA NILAIAN HARTA AMANAH RAYA");
  					if(getJumlahBayaran17.size()!=0){
  						Hashtable a = (Hashtable) getJumlahBayaran17.get(0);
  				   		double jumlah_harta17_tarikhmohon;
  				   		jumlah_harta17_tarikhmohon = Double.parseDouble(a.get("sumharta").toString());
  				   		this.context.put("txtJumHarta", jumlah_harta17_tarikhmohon);
  					}
  				}
  				//* END

	    		//CALL FLAG
	    		context.put("mode", "view");
	    		context.put("flag", "selesai");
	    		context.put("button", "");
	    		context.put("tarikh", "perintah");

	    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

   	}else if("Simpan_Edit_Batal".equals(submit)){

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

			FrmPrmhnnSek8KptsanBicaraData.setViewPerintah(idpermohonan,id_perbicaraan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintah();
			this.context.put("dataPerintahView", dataPerintah);

			getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.setInfoPerintah(idpermohonan,id_perbicaraan);
			long idUnitPskView = 0;
			String flag_jenis_keputusan = "";
			if ( getrecord_perintah.size() != 0){
				Hashtable d = (Hashtable) getrecord_perintah.get(0);
				idUnitPskView = Long.parseLong(d.get("id_unitpsk").toString());

				flag_jenis_keputusan = d.get("flag_jenis_keputusan").toString();
				setValueFlagJenisKeputusan("","","checked");
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
				context.put("dataPerintahView", getrecord_perintah);
				context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPskView,"disabled"));

    		//call flag
    		context.put("mode", "view");
    		context.put("flag", "batal");
    		context.put("button", "");
    		context.put("tarikh", "perintah");

    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

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
			myLogger.info("IDPERINTAH Simpan_Edit_Tangguh :: "+id_perintah);
		 	EDITsocPegawaiPengendali = getParam("EDITsocPegawaiPengendali");
		    txdTarikhPerintahEDIT = getParam("txdTarikhPerintahEDIT");
		    flag_tangguh = getParam("flag_tangguh");

		    updateMaklumatTangguh(idpermohonan,usid,id_perintah,id_perbicaraan);

		    if(idstatus.equals("44")){ // && !id_Status.equals("41")){
			  // edit_status_BicaraTangguh(idpermohonan,idsuburusanstatusfail,usid);
			  //  edit_BicaraTangguhStatus_tblrujsuburusanstatusfail(idpermohonan,id_perbicaraan,
			  //  		usid,idsuburusanstatusfail,idFail);
			    
		    	//:::SUB
			    //ID_STATUS : 44
			    //ID_SUBURUSAN : 420
			    logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"44","420",idFail);
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
	    	idUnitPskView = Long.parseLong(d.get("id_unitpsk").toString());
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

	    	vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

    	}else if("Simpan_Batal".equals(submit)){

    		String id_perbicaraan = getParam("id_perbicaraan");
			context.put("id_perbicaraan", getParam("id_perbicaraan"));

			//* GET INFO PERBICARAAN
			getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
			String id_fail = "";
			if ( getrecord_infoperbicaraan.size() != 0 ){
				Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
				id_fail = h.get("id_fail").toString();

				 if (doPost.equals("true")) {
					add_MaklumatBatal(usid,idpermohonan,id_perbicaraan);

				   // edit_status_batal(idpermohonan,usid);
				   // edit_status_tblrujsuburusanstatusfail_batal(usid,idpermohonan,
					//		id_perbicaraan,idsuburusanstatusfail,idFail);
				    
					//:::SUB
				    //ID_STATUS : 47
				    //ID_SUBURUSAN : 425
					logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"47","425",idFail);
				 }
			}
				context.put("dataPerbicaraan", getrecord_infoperbicaraan);

			getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.setInfoPerintah(idpermohonan,id_perbicaraan);
			String flag_batal = "";
			long idUnitPskView = 0;
			if ( getrecord_perintah.size() != 0 ){
				Hashtable d = (Hashtable) getrecord_perintah.get(0);
				flag_batal = d.get("flag_batal").toString();
				idUnitPskView = Long.parseLong(d.get("id_unitpsk").toString());

				setValueFlagJenisKeputusan("","","checked");
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

			vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

		}else if("getSimpanMahkamah".equals(submit)){	//---->> MT FOR BATAL BICARA

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

					 //edit_status_batalMT(idpermohonan,usid);
					 //edit_status_tblrujsuburusanstatusfail_batalMT(usid,idpermohonan,
					//		 idsuburusanstatusfail,idFail);
					 					 
					//:::SUB
					    //ID_STATUS :47
					    //ID_SUBURUSAN :425
					 logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"47","425",idFail);
				 }
			}else {
				if (doPost.equals("true")) {
					insertMahkamah(usid,id_perbicaraan);		//INSERT TBLPPKPERINTAH INSERT TBLPPKBORANGJ

					// edit_status_batalMT(idpermohonan,usid);
					// edit_status_tblrujsuburusanstatusfail_batalMT(usid,idpermohonan,
					//			idsuburusanstatusfail,idFail);
					 
					//:::SUB
				    //ID_STATUS :47
				    //ID_SUBURUSAN :425
					logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"47","425",idFail);
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
	    		jenis_keluar_perintah = (String)b.get("jenis_keluar_perintah");
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
				setValueFlagJenisKeputusan("","","checked");
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

			vm = "app/ppk/frmPrmhnnSek8MahkamahTinggi17.jsp";

	   	}else if("getMahkamahROTS".equals(submit)){

			int idNegeri = Integer.parseInt(getParam("socNegeri"));
			this.context.put("SocNegeri",idNegeri);

			FrmPrmhnnSek8KptsanBicaraData.setListNegeri();
			Vector listNegeri = FrmPrmhnnSek8KptsanBicaraData.getListNegeri();
			this.context.put("ListNegeri",listNegeri);

			FrmPrmhnnSek8KptsanBicaraData.setListDaerah(idNegeri);
			Vector listDaerah = FrmPrmhnnSek8KptsanBicaraData.getListDaerah();
			this.context.put("ListDaerah",listDaerah);

			vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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
    			tarikh_bicara = (String)idn.get("tarikh_bicara");
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
								usid,id_perintah);					 			//UPDATE TBLPPKPERINTAH INSERT TBLPPKBORANGJ
					}
				}else {
					if (doPost.equals("true")) {
						insertBorangJ(id_perbicaraan,usid);						//INSERT TBLPPKPERINTAH INSERT TBLPPKBORANGJ
					}
				}

				if (doPost.equals("true")) {
					//edit_status_tangguh(idpermohonan,usid,idsuburusanstatusfail);
					//edit_status_tblrujsuburusanstatusfail_Tangguh17(usid,idpermohonan,
					//		idsuburusanstatusfail,idFail);
					
					//:::SUB
				    //ID_STATUS :174
				    //ID_SUBURUSAN :781
					logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"174","781",idFail);
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
					idNegeriMahkamah = (String)b.get("id_negerimahkamah");
					idMahkamah = (String)b.get("id_mahkamah");
					idUnitPSK = (String)b.get("id_unitpsk");
				}

				context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri",Utils.parseLong(idNegeriMahkamah),null,"class=\"disabled\" style=width:305 onChange=\"doChangeidNegeriROTS();\" "));
	   			context.put("selectBicara", HTML.SelectMahkamah("socTempatBicara", Utils.parseLong(idMahkamah), " disabled", " class=\"disabled\" style=width:340 onChange=\"doChangeidMahkamahROTS();\""));
	   			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",Utils.parseLong(idUnitPSK)," disabled", " class=\"disabled\" style=width:305"));
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
    		context.put("listSemak", list);
    		context.put("idstatus", idstatus);

			vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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
					jenis_rujukan = (String)c.get("jenis_rujukan");
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
					flag_rujukan = (String)c.get("flag_rujukan");
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
					String idNegeriMufti = (String)c.get("id_negeri");
					String idBandar = (String)c.get("id_bandar");

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
				FrmPrmhnnSek8KptsanBicaraData.setSelectedWaris17(idBorangJ,id_perbicaraan,getParam("id_permohonansimati_atheader"));
				senarai_waris = FrmPrmhnnSek8KptsanBicaraData.getListSelectedWaris17();
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

			vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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
					context.put("selectBicara", HTML.SelectMahkamahSyariahByNegeri(Utils.parseLong(idNegeriMahkamah),"socTempatBicara",Utils.parseLong(idMahkamah),"disabled style=width:395 "));
					context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawai",idUnitPsk,null,"style=width:305"));
					context.put("txtidnegeri", "idNegeriMahkamah");

				// get senarai selected waris + waris terdahulu
				FrmPrmhnnSek8KptsanBicaraData.setSelectedWaris17(idBorangJ,id_perbicaraan,getParam("id_permohonansimati_atheader"));
				senarai_waris = FrmPrmhnnSek8KptsanBicaraData.getListSelectedWaris17();
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

			vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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
        		tarikh_bicara = (String)idn.get("tarikh_bicara");
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
		    	//edit_status_tblppkpermohonanMufti(idpermohonan, usid);
		    	//edit_status_tblrujsuburusanstatusfailMufti(idsuburusanstatusfail, usid, id_perbicaraan, idFail, idpermohonan);
		    	
		    	//:::SUB
			    //ID_STATUS :176
			    //ID_SUBURUSAN :813
		    	
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

			//* get maklumat waris
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

			vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

		}else if("getSimpanMahkamahSyariahROTS".equals(submit)){

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
        		tarikh_bicara = (String)idn.get("tarikh_bicara");
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
				id_perintah = e.get("id_perintah").toString();
			    if (doPost.equals("true")) {
					//UPDATE TBLPPKPERINTAH INSERT TBLPPKBORANGJ
					insertBorangJ_updateTblppkperintahMS(usid,id_perintah,id_perbicaraan,txdTarikhRujukanAdd,idNegeriMahkamah,
							jenis_rujukan, idMahkamah,idUnitPSK, txtFaktaGuamanAdd,txtPendapatAdd);

			    	//edit_status_tblppkpermohonanMS(idpermohonan, usid);
			    	//edit_status_tblrujsuburusanstatusfailMS(idsuburusanstatusfail, usid, id_perbicaraan, idFail, idpermohonan);
					//:::SUB
				    //ID_STATUS : 176
				    //ID_SUBURUSAN : 813
			    	logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"176","813",idFail);
			    }
			}else {

			    if (doPost.equals("true")) {
					 //INSERT TBLPPKPERINTAH INSERT TBLPPKBORANGJ
					 insertBorangJMS(usid,id_perbicaraan,txdTarikhRujukanAdd,idNegeriMahkamah,
								jenis_rujukan, idMahkamah, idUnitPSK, txtFaktaGuamanAdd,txtPendapatAdd);

					// edit_status_tblppkpermohonanMS(idpermohonan, usid);
					// edit_status_tblrujsuburusanstatusfailMS(idsuburusanstatusfail, usid, id_perbicaraan, idFail, idpermohonan);
										 
					 //:::SUB
					    //ID_STATUS :176
					    //ID_SUBURUSAN :813
					 logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"176","813",idFail);
			    
			    }
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
				flag_jenis_keputusan = d.get("flag_jenis_keputusan").toString();
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

			//* get maklumat waris
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
    		context.put("listSemak", list);
    		context.put("id_status", idstatus);

			vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

		}else if("simpanEditMufti".equals(submit)){

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
        		tarikh_bicara = (String)idn.get("tarikh_bicara");
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
				id_perintah = e.get("id_perintah").toString();
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

			vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

		}else if("simpanSyariah".equals(submit)){

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
    			tarikh_bicara = (String)idn.get("tarikh_bicara");
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
							String id_perintah = e.get("id_perintah").toString();
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
				//edit_status_Syariah(idpermohonan, usid);
				//edit_status_tblrujsuburusanstatusfail_Syariah17(idsuburusanstatusfail, usid,
			    //		id_perbicaraan, idFail, idpermohonan);
			
				//:::SUB
			    //ID_STATUS :176
			    //ID_SUBURUSAN :853
				logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"176","853",idFail);
			}

			//* GET ID_PERINTAH
			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			String id_perintah = "";
			if ( getIdPerintah.size() != 0 ){
				Hashtable e = (Hashtable) getIdPerintah.get(0);
				id_perintah = e.get("id_perintah").toString();
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

			vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

		}else if("HapusMufti".equals(submit)){

		    String id_borangj = getParam("id_borangj");

			//get id_keputusanpermohonan - tiada id_perbicaraan
			FrmPrmhnnSek8KptsanBicaraData.setViewNotis(idpermohonan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getViewNotis();
			String idkp = "" ;
			if(dataPerintah.size()!=0){
				Hashtable v = (Hashtable) dataPerintah.get(0);
				idkp = (String)v.get("id_keputusanpermohonan");
			}
				context.put("dataPerintahView", dataPerintah);

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		String tarikh_bicara = "";
    		String id_perbicaraan  = "";
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
    			tarikh_bicara = (String)idn.get("tarikh_bicara");
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
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
				senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris17(id_perbicaraan,getParam("id_permohonansimati_atheader"));
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

    		vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

		}else if("HapusSyariah".equals(submit)){

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
				senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris17(id_perbicaraan,getParam("id_permohonansimati_atheader"));
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

    		vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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
    			tarikh_bicara = idn.get("tarikh_bicara").toString();
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
				senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris17(id_perbicaraan,getParam("id_permohonansimati_atheader"));
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

    		vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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

			vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

		}else if("hapusIDob".equals(submit)){

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

			vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

		}else if("skrin_editROTS".equals(submit)){

			String id_perbicaraan = getParam("id_perbicaraan");
		    this.context.put("id_perbicaraan", id_perbicaraan);

			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			if ( getIdPerintah.size() != 0 ){
				Hashtable e = (Hashtable) getIdPerintah.get(0);
				String id_perintah = e.get("id_perintah").toString();

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
		    		jenis_rujukan = (String)b.get("jenis_rujukan");
		    		idMahkamah = (String)b.get("id_mahkamah");
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
			logic4.setListSelectedWaris17(session,id_perbicaraan,getParam("id_permohonansimati_atheader"));
			listSelectedWaris = logic4.getSelectedWaris17();
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

			vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

		}else if("edit_keputusanROTS".equals(submit)){

			String id_perbicaraan = getParam("id_perbicaraan");
		    this.context.put("id_perbicaraan", id_perbicaraan);

			getIdPerintah = logic4.getIdPerintah(id_perbicaraan);
			if ( getIdPerintah.size() != 0 ){
				Hashtable e = (Hashtable) getIdPerintah.get(0);
				String id_perintah = e.get("id_perintah").toString();

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
		    		jenis_rujukan = (String)b.get("jenis_rujukan");
					idNegeriMahkamah= (String)b.get("id_negerimahkamah");
					idMahkamah = (String)b.get("id_mahkamah");
					idUnitPSK = (String)b.get("id_unitpsk");
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

			//* GET UPLOAD FILE
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

    		vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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
		    		jenis_rujukan = (String)b.get("jenis_rujukan");
		    		idMahkamah = (String)b.get("id_mahkamah");
		    		idNegeriMahkamah = (String)b.get("id_negerimahkamah");
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

			vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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
				id_fail = (String)a.get("id_fail");
				if(doPost.equals("true")){
					updateKeputusan(idpermohonan,usid,id_perbicaraan,idBorangJ);

				    //edit_status_ROTSkeputusan(idpermohonan,usid);
				    //edit_status_tblrujsuburusanstatusfail_ROTSkeputusan(idsuburusanstatusfail,usid,
				    //		id_perbicaraan,id_fail,idpermohonan);
				    				    
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
		    		jenis_rujukan = (String)b.get("jenis_rujukan");
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
				}

			context.put("viewEditMode", "yes");
			context.put("editMode", "no");
			context.put("viewMode", "no");
    		context.put("addMode", "no");
    		context.put("addMode", "no");
    		context.put("tab", "keputusan");
    		context.put("idstatus",idstatus);
    		context.put("listSemak", list);

			vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

		}else if("Simpan_Keputusan".equals(submit)){

			String id_perbicaraan = getParam("id_perbicaraan");
		    this.context.put("id_perbicaraan", id_perbicaraan);

		    String txdTarikhHantarAdd = getParam("txdTarikhHantarAdd");
		    String txdTarikhTerimaAdd = getParam("txdTarikhTerimaAdd");
		    String txtKeputusanAdd = getParam("txtKeputusanAdd");
		    String txtCatatanAdd = getParam("txtCatatanAdd");

			context.put("viewMode", "yes");
    		context.put("addMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("editMode", "no");

			vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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

		    context.put("viewMode", "yes");
    		context.put("addMode", "no");
    		context.put("editMode", "no");
    		context.put("viewEditMode", "no");
    		context.put("button", "no");

			vm = "app/ppk/frmPrmhnnSek8MahkamahTinggi17.jsp";

    	}else if("Cari".equals(submit)){

			carianNotis(usid);
			list = logic4.getListCarian17();

    		//data & size default list
    		context.put("listNotis", list);
    		context.put("list_size", list.size());

    		//maintain searching value
    		this.context.put("txtNoFail", getParam("txtNoFail"));
    		this.context.put("txtPemohon", getParam("txtPemohon"));
    		this.context.put("txtSimati", getParam("txtSimati"));
    		this.context.put("txtIcSimati", getParam("txtIcSimati"));
    		this.context.put("jenisIc", getParam("jenisIc"));

			vm = "app/ppk/frmSenaraiFailKeputusanBicaraanSek17.jsp";

    	}else if("tab_selesai".equals(submit)){

    		//clear field
    		context.put("TEMPcheckedTangguh", "");
    		context.put("TEMPcheckedBatal", "");
    		
    		context.put("check_kiv","");
    		context.put("date_kiv","");
    		context.put("catatan_kiv","");
    		
    		context.put("check_doc","");
    		context.put("valueKIV","");
    		
    		
    		//CLOSE

    		//DROP DOWN
    		String idUnitPsk = getParam("EDITsocPegawaiPengendali");
    		context.put("selectEditPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali", Utils.parseLong(idUnitPsk),null));
    		//CLOSE

    		//CHECKING TARIKHMOHON
			String t_mohon = getParam("tarikhMohon");
			String september_mohon ="01/09/2009";

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date tar_mohon = df.parse(t_mohon);
	        Date sep_mohon = df.parse(september_mohon);

	        if (tar_mohon.before(sep_mohon) || t_mohon.equals("")) {
    			myLogger.info("TARIKH MOHON SEBELUM SEPTEMBER");
    			context.put("FlagtarikhMohon", 1);
    		}else{
    			myLogger.info("TARIKH MOHON SELEPAS SEPTEMBER");
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

			//get jumlah_harta_tarikhmohon
			FrmPrmhnnSek8KptsanBicaraData.setJumlahBayaran17(id_permohonansimati);
			getJumlahBayaran17 = FrmPrmhnnSek8KptsanBicaraData.getJumlahBayaran17();

			FrmPrmhnnSek8KptsanBicaraData.setMaklumatPermohonan17(idpermohonan);
			getMaklumatPermohonan17 = FrmPrmhnnSek8KptsanBicaraData.getMaklumatPermohonan17();

			//checking TBLPPKHA for NilaianAmanahRaya
			checkingNilaianAmanahRaya = logic2.checkingNilaianAmanahRaya(id_permohonansimati);
			double nilai_ha_tarikhmohon;
			if(checkingNilaianAmanahRaya.size()!=0){

				myLogger.info("ADA NILAIAN HARTA AMANAH RAYA");
				Hashtable nilaian = (Hashtable) checkingNilaianAmanahRaya.get(0);
				nilai_ha_tarikhmohon = Double.parseDouble(nilaian.get("nilai_ha_tarikhmohon").toString());

				if(getJumlahBayaran17.size()!=0){

					Hashtable a = (Hashtable) getJumlahBayaran17.get(0);
					Hashtable z = (Hashtable) getMaklumatPermohonan17.get(0);
			   		double jumlah_harta17_tarikhmohon;
		    		double bayaranYuranPerintah17;
		    		double jumlahHartaDeductNilaianAmanahRaya;

		    		jumlah_harta17_tarikhmohon = Double.parseDouble(a.get("sumharta").toString());
		    		jumlahHartaDeductNilaianAmanahRaya = jumlah_harta17_tarikhmohon - nilai_ha_tarikhmohon;
		    		myLogger.info("jumlah_harta17_tarikhmohon = "+jumlah_harta17_tarikhmohon);
		    		myLogger.info("jumlahHartaDeductNilaianAmanahRaya = "+jumlahHartaDeductNilaianAmanahRaya);

		    		String batal_kuasa_pentadbir = z.get("batal_kuasa_pentadbir").toString();
		    		String batal_p_amanah = z.get("batal_p_amanah").toString();
		    		String harta_tinggal =  z.get("harta_tinggal").toString();
					if ( jumlahHartaDeductNilaianAmanahRaya <= 1000 ) {
						//bayaranYuranPerintah17 = 10.00 ;
						bayaranYuranPerintah17 = (0.2/100) * jumlahHartaDeductNilaianAmanahRaya ;
						//ADD BY PEJE
    					bayaranYuranPerintah17 = getBundaranBayaran(bayaranYuranPerintah17);
					} else if ( (jumlahHartaDeductNilaianAmanahRaya >= 1001) && (jumlahHartaDeductNilaianAmanahRaya <= 50000) ){
						//bayaranYuranPerintah17 = 30.00 ;
						bayaranYuranPerintah17 = (0.2/100) * jumlahHartaDeductNilaianAmanahRaya ;
						//ADD BY PEJE
    					bayaranYuranPerintah17 = getBundaranBayaran(bayaranYuranPerintah17);
					} else {
						bayaranYuranPerintah17 = (0.2/100) * jumlahHartaDeductNilaianAmanahRaya ;
						//ADD BY PEJE
    					bayaranYuranPerintah17 = getBundaranBayaran(bayaranYuranPerintah17);
					}

					double j = 0;
						if (batal_kuasa_pentadbir.equals("Y")) {
							j += 30.00 ;
						}
						if (batal_p_amanah.equals("Y")){
							j += 30.00 ;
						}
					//double total = (j + bayaranYuranPerintah17);
					double total = (bayaranYuranPerintah17);
					if (total < 10)
					{
						total = 10;
					}
					this.context.put("txtJumBayaran", total);
					this.context.put("txtJumHarta",jumlahHartaDeductNilaianAmanahRaya);
				}
					context.put("dataJumlahBayaran", getJumlahBayaran17);


			}else{

				myLogger.info("TIADA NILAIAN HARTA AMANAH RAYA");
				if(getJumlahBayaran17.size()!=0){

					Hashtable a = (Hashtable) getJumlahBayaran17.get(0);
					Hashtable z = (Hashtable) getMaklumatPermohonan17.get(0);
			   		double jumlah_harta17_tarikhmohon;
		    		double bayaranYuranPerintah17;

		    		jumlah_harta17_tarikhmohon = Double.parseDouble(a.get("sumharta").toString());
		    		String batal_kuasa_pentadbir = z.get("batal_kuasa_pentadbir").toString();
		    		String batal_p_amanah = z.get("batal_p_amanah").toString();
		    		String harta_tinggal =  z.get("harta_tinggal").toString();

		    		//Azam Add on 20/4/2011 , jumlah_harta17_tarikhmohon >= 1
					if ( jumlah_harta17_tarikhmohon >= 1 && jumlah_harta17_tarikhmohon <= 1000 ) {
						//bayaranYuranPerintah17 = 10.00 ;
						bayaranYuranPerintah17 = (0.2/100) * jumlah_harta17_tarikhmohon ;
						//ADD BY PEJE
    					bayaranYuranPerintah17 = getBundaranBayaran(bayaranYuranPerintah17);
					} else if ( (jumlah_harta17_tarikhmohon >= 1001) && (jumlah_harta17_tarikhmohon <= 50000) ){
						//bayaranYuranPerintah17 = 30.00 ;
						bayaranYuranPerintah17 = (0.2/100) * jumlah_harta17_tarikhmohon ;
						//ADD BY PEJE
    					bayaranYuranPerintah17 = getBundaranBayaran(bayaranYuranPerintah17);
					} else {
						bayaranYuranPerintah17 = (0.2/100) * jumlah_harta17_tarikhmohon ;
						//ADD BY PEJE
    					bayaranYuranPerintah17 = getBundaranBayaran(bayaranYuranPerintah17);
					}

					double j = 0;
						if (batal_kuasa_pentadbir.equals("Y")) {
							j += 30.00 ;
						}
						if (batal_p_amanah.equals("Y")){
							j += 30.00 ;
						}
					double total = (j + bayaranYuranPerintah17);
					this.context.put("txtJumBayaran", total);
					this.context.put("txtJumHarta",jumlah_harta17_tarikhmohon);
				}
					context.put("dataJumlahBayaran", getJumlahBayaran17);
			}

    		//call flag
    		context.put("TEMPcheckedSelesai", "checked");
    		context.put("mode", "add");
    		context.put("flag", "selesai");
    		context.put("tarikh", "");
    		context.put("button", "");
    		context.put("tarikh", "perintah");

            vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

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

    		//clear field
    		context.put("tempoh_tunggu_faraid","");
    		context.put("sebab_tangguh","");
    		context.put("keputusan_mahkamah","");
    		context.put("TEMPcheckedSelesai", "");
    		context.put("TEMPcheckedBatal", "");

    		//call flag
    		context.put("TEMPcheckedTangguh", "checked");
    		context.put("mode", "add");
    		context.put("flag", "tangguh");
    		context.put("tarikh", "bicara");
    		context.put("button", "");

            vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

    	}else if("tab_batal".equals(submit)){

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
    		//CLOSE

			//clear field
    		context.put("TEMPcheckedSelesai", "");
    		context.put("TEMPcheckedTangguh", "");

    		//call flag
    		context.put("TEMPcheckedBatal", "checked");
    		context.put("mode", "add");
    		context.put("flag", "batal");
    		context.put("tarikh", "perintah");
    		context.put("button", "");

            vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

       	}else if("kembali_skrinROTS".equals(submit)){

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

    		//call flag
    		context.put("mode", "add");
    		context.put("flag", "tangguh");
    		context.put("button", "");
    		context.put("tarikh", "bicara");

    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

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
        		tarikh_bicara = (String)idn.get("tarikh_bicara");
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

				//* CHECKING LIST SENARAI WARIS
				senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris17(id_perbicaraan,getParam("id_permohonansimati_atheader"));
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

    		vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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

	        vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

		}else if("doChangeidMahkamahROTSupdate".equals(submit)){

			String id_perbicaraan = getParam("id_perbicaraan");

			//* GET MAKLUMAT TANGGUH
			PerintahTangguh = FrmPrmhnnSek8KptsanBicaraData.setPerintahTangguh(id_perbicaraan);
			long idUnitPsk = 0;
			String jenis_keluar_perintah = "";
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

	        vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

		}else if("doChangeidMahkamahMS".equals(submit)){

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

	        vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

		}else if("doChangeidMahkamahMSupdate".equals(submit)){

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

	        vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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
    			tarikh_bicara = idn.get("tarikh_bicara").toString();
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
    		context.put("action", "onChange");

			vm = "app/ppk/frmPrmhnnSek8MahkamahTinggi17.jsp";

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
			String id_perbicaraan = "";
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
						//Hashtable b = null ;
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
					}

			vm = "app/ppk/frmPrmhnnSek8MahkamahTinggi17.jsp";

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

	        vm = "app/ppk/frmPrmhnnSek8MahkamahTinggi17.jsp";

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

	        vm = "app/ppk/frmPrmhnnSek8MahkamahTinggi17.jsp";

		}else if("doChangeidNegeriROTS".equals(submit)){

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

	        vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

		}else if("doChangeidNegeriROTSupdate".equals(submit)){

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
	    		jenis_keluar_perintah = b.get("jenis_keluar_perintah").toString();
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

	        vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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

	        vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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

	        vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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

			Vector getDataKeputusan = FrmPrmhnnSek8KptsanBicaraData.setDataKeputusan(idpermohonan);
			getDataKeputusan = FrmPrmhnnSek8KptsanBicaraData.getDataKeputusan();
			String idBayaran = "";
			if (getDataKeputusan.size()!=0){
				Hashtable c = (Hashtable) getDataKeputusan.get(0);
				idBayaran = (String)c.get("id_bayaran");
				myLogger.info("ID BAYARAN"+idBayaran);
			}
			context.put("dataKeputusan", getDataKeputusan);


			if (doPost.equals("true")) {

				addKeputusan_Editblppkkolateralmst( idpermohonan, IdKolateralmst, usid, flag_keputusan,
						txtBayaranPerintahKol, txtNomborResitPerintahKol, txtCatatanKeputusan, txdTarikhBayaranPerintahKol );

				//edit_status_KeputusanKolateral( idpermohonan, usid );
				//edit_status_tblrujsuburusanstatusfail_KeputusanKolateral( idpermohonan, idsuburusanstatusfail, idFail, usid );
				
				//:::SUB
			    //ID_STATUS :173
			    //ID_SUBURUSAN : 780
				logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"173","780",idFail);
			}

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

		    vm = "app/ppk/frmPrmhnnKolateral17.jsp";

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
				    //ID_SUBURUSAN : 779
					logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"172","779",idFail);
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

		    vm = "app/ppk/frmPrmhnnKolateral17.jsp";

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
			}
				context.put("dataPerintahKolateral", getrecord_perintahKolateral);

				getDataKeputusan = FrmPrmhnnSek8KptsanBicaraData.setDataKeputusan(idpermohonan);
				getDataKeputusan = FrmPrmhnnSek8KptsanBicaraData.getDataKeputusan();
				if (getDataKeputusan.size()!=0){
					Hashtable c = (Hashtable) getDataKeputusan.get(0);
					idBayaran = c.get("id_bayaran").toString();
					myLogger.info("ID BAYARAN"+idBayaran);
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

		    vm = "app/ppk/frmPrmhnnKolateral17.jsp";

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

	    		//* get data OB
	    		OBList = logic4.getObList(id_perbicaraan,getParam("id_permohonansimati_atheader"));
	    		this.context.put("OBList", OBList);

				//* call flag
	    		context.put("viewEditMode", "no");
				context.put("addMode", "no");
	    		context.put("viewMode", "no");
	    		context.put("editMode", "yes");
	    		context.put("tab","");

	    		vm = "app/ppk/frmPrmhnnKolateral17.jsp";

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
			    			context.put("selectBicara",HTML.SelectTempatBicaraByPejabatJKPTG(idPejabatJKPTG,"socTempatBicara",null,null,"style=width:350 onChange=\"doChangeidTempatBicara();\" "));
			    			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",null,"style=width:305"));
			            }else{
			    			context.put("selectBicara",HTML.SelectTempatBicara("socTempatBicara",null,null,"style=width:350 onChange=\"doChangeidTempatBicara();\" "));
			    			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",null,"style=width:305"));
			            }
			    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"class=disabled disabled style=width:305"));

						//senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris17(idSimati);
						senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris17(id_perbicaraan,getParam("id_permohonansimati_atheader"));
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

					vm = "app/ppk/frmPrmhnnKolateral17.jsp";

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

    		vm = "app/ppk/frmPrmhnnKolateral17.jsp";

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
							id_perintah = e.get("id_perintah").toString();
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
				    		myLogger.info("JENIS RUJUKAN :: "+jenis_rujukan);

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
								myLogger.info(">>>> JENIS RUJUKAN 1");
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
					    			myLogger.info(">>>> FLAG RUJUKAN 1");

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
					    			myLogger.info(">>>> FLAG RUJUKAN 2");
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
						senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris17(id_perbicaraan,getParam("id_permohonansimati_atheader"));
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

    		vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

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
						String flag_jenis_keputusan = "";
						String flag_tangguh = "";
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
						senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris17(id_perbicaraan,getParam("id_permohonansimati_atheader"));
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
					}

    		vm = "app/ppk/frmPrmhnnRulerOfTheState17.jsp";

		}else if("PertelingkahanKolateral".equals(submit)){

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
    			tarikh_bicara = idn.get("tarikh_bicara").toString();
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
			senarai_waris = FrmPrmhnnSek8KptsanBicaraData.setSenaraiWaris17(id_perbicaraan,getParam("id_permohonansimati_atheader"));
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

			vm = "app/ppk/frmPrmhnnKolateral17.jsp";

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

		        vm = "app/ppk/frmPrmhnnKolateral17.jsp";

		}else if("doChangeidTempatBicaraUpdate".equals(submit)){

    		String idTempatBicara = getParam("socTempatBicara");
    		String idBicara = "";
//			idBicara = idTempatBicara;
			alamatTempatBicara = logic5.getAlamatTempatBicara(idTempatBicara);
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

	        vm = "app/ppk/frmPrmhnnKolateral17.jsp";

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

	        vm = "app/ppk/frmPrmhnnSek8MahkamahTinggi17.jsp";

		}else if("doChangeidMahkamahUpdate".equals(submit)){

    		String id_perbicaraan = getParam("id_perbicaraan");

			//* GET MAKLUMAT TANGGUH
    		long idUnitPsk = 0;
    		String jenis_keluar_perintah = "";
    		PerintahTangguh = FrmPrmhnnSek8KptsanBicaraData.setPerintahTangguh(id_perbicaraan);
			if ( PerintahTangguh.size() != 0 ){
				Hashtable c  = (Hashtable) PerintahTangguh.get(0);
				idUnitPsk = Long.parseLong(c.get("id_unitpsk").toString());
	    		jenis_keluar_perintah = c.get("jenis_keluar_perintah").toString();
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

	        vm = "app/ppk/frmPrmhnnSek8MahkamahTinggi17.jsp";

	}else if("kembali_list".equals(submit)){

	        FrmSenaraiFailKptsPerbcrnData.setList17(usid);
			list = FrmSenaraiFailKptsPerbcrnData.getList17();
			prepareItemForDisplay(session,list,10,"first");

			//data & size default list
			context.put("listNotis", list);
			context.put("list_size", list.size());

    		vm = "app/ppk/frmSenaraiFailKeputusanBicaraanSek17.jsp";

	}else if("kembali_skrin2TangguhAdd".equals(submit)){

		int flag_jenis_keputusan = 2 ;
		checkedBatal= "checked" ;

		String id = getParam("idpermohonan");
	    this.context.put("id",id);

		//get id_keputusanpermohonan - tiada id_perbicaraan
		FrmPrmhnnSek8KptsanBicaraData.setViewNotis(id);
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

		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

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

		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

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
		if(dataNotis.size()!=0){
			Hashtable idn = (Hashtable) dataNotis.get(0);
			id_perbicaraan = (String)idn.get("id_perbicaraan");
			tarikh_bicara = idn.get("tarikh_bicara").toString();
		}
			context.put("id_perbicaraan",id_perbicaraan);
			context.put("tarikh_bicara",tarikh_bicara);

		//get data TBLPPKPERBICARAAN
		Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);
		long idUnitPsk = 0;
		if(h.size()!=0){
			idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
		}
			context.put("dataPerbicaraan", h);
			context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,"disabled"));

	    //get info via id_perbicaraan
		FrmPrmhnnSek8KptsanBicaraData.setViewTangguhList(idpermohonan, id_perbicaraan);
	    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getViewTangguh();
	    Hashtable d = new Hashtable();
	    String flag_jenis_keputusan = "";
	    String flag_tangguh = "";
	    if(getrecord_perintah.size()!=0){
			d = (Hashtable) getrecord_perintah.get(0);
			flag_jenis_keputusan = d.get("flag_jenis_keputusan").toString();
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

		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

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
			id_perintah = (String)v.get("id_perintah");
		}
			context.put("dataPerintahView", dataPerintah);
			context.put("id_perintah", id_perintah);

    	//--data notis
		logic4.setListSemakWithData(idkp);
		dataNotis = logic4.getListSemakWithData();
		String tarikh_bicara = "";
		if(dataNotis.size()!=0){
			Hashtable idn = (Hashtable) dataNotis.get(0);
			tarikh_bicara = (String)idn.get("tarikh_bicara");
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
	    	flag_jenis_keputusan = d.get("flag_jenis_keputusan").toString();
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

		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

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
			tarikh_bicara = (String)idn.get("tarikh_bicara");
		}
			this.context.put("id_perbicaraan",id_perbicaraan);
			this.context.put("tarikh_bicara",tarikh_bicara);

		//call flag
		context.put("mode", "add");
		context.put("flag", "tangguh");
		context.put("button", "");
		context.put("tarikh", "bicara");

		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

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
		    	flag_jenis_keputusan = d.get("flag_jenis_keputusan").toString();
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

    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

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
			logic4.setListSemakWithData(idkp);
			dataNotis = logic4.getListSemakWithData();
			String id_perbicaraan = "";
			String tarikh_bicara ="";
			if(dataNotis.size()!=0){
				Hashtable idn = (Hashtable) dataNotis.get(0);
				id_perbicaraan = (String)idn.get("id_perbicaraan");
				tarikh_bicara = (String)idn.get("tarikh_bicara");
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

    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

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

    		vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

		}else if("Simpan_Selesai".equals(submit)){

			String id_perbicaraan = getParam("id_perbicaraan");
			context.put("id_perbicaraan", getParam("id_perbicaraan"));

			getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
			if ( getrecord_infoperbicaraan.size() != 0 ){
				Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
				context.put("selectEditPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",Long.parseLong(h.get("id_unitpsk").toString()),""));
			}

			String txtJumBayaranPusaka = getParam("txtJumBayaranPusaka");
			String txtJumBayaranBaitulmal = getParam("txtJumBayaranBaitulmal");

			if (doPost.equals("true")) {

				//* CHECKING EXISTING MAKLUMAT BAYARAN
				getExistDataBayaran = FrmPrmhnnSek8KptsanBicaraData.setExistDataBayaran(idpermohonan);
				if ( getExistDataBayaran.size() != 0 ){
					//* DELETE MAKLUMAT BAYARAN UTK ID_JENISBAYARAN 24,26,29
					hapusExistingMaklumatBayaran(idpermohonan);
				}
				//END CHECKING

				add_MaklumatBayaranPerintah(idSimati,idpermohonan,id_perbicaraan,usid);

				//edit_status_selesai(idpermohonan,usid,idsuburusanstatusfail);
				//edit_status_tblrujsuburusanstatusfail(usid,idpermohonan,id_perbicaraan,
					//	idsuburusanstatusfail,idFail);
				
				//:::SUB
			    //ID_STATUS :41
			    //ID_SUBURUSAN :410
				logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"41","410",idFail);
			}

			if ( txtJumBayaranPusaka != "" ){
				if (doPost.equals("true")) {
					add_MaklumatBayaranCukaiPusaka(usid,idpermohonan,id_perbicaraan);
				}
			}
			if ( txtJumBayaranBaitulmal != "" ){
				if (doPost.equals("true")) {
					add_MaklumatBayaranBaitulMal(usid,id_perbicaraan,idpermohonan);
				}
			}

			FrmPrmhnnSek8KptsanBicaraData.setViewBicara(idpermohonan);
			dataBayaran = FrmPrmhnnSek8KptsanBicaraData.getDataBayaran();
  	    	this.context.put("dataBicaraView", dataBayaran);

			FrmPrmhnnSek8KptsanBicaraData.setViewPerintah(idpermohonan,id_perbicaraan);
			dataPerintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintah();
  	    	this.context.put("dataPerintahView", dataPerintah);

		    FrmPrmhnnSek8KptsanBicaraData.setInfoPerintah(idpermohonan,id_perbicaraan);
		    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahView();
		    long idUnitPskView = 0;
		    String flag_jenis_keputusan = "";
		    if(getrecord_perintah.size()!=0){
		    	Hashtable d = (Hashtable) getrecord_perintah.get(0);
		    	idUnitPskView = Long.parseLong(d.get("id_unitpsk").toString());
		    	flag_jenis_keputusan = d.get("flag_jenis_keputusan").toString();
		    	
		
		    	
		    	context.put("check_kiv",(String)d.get("check_kiv"));
    			context.put("check_doc",(String)d.get("check_doc"));
    			context.put("valueKIV",(String)d.get("valueKIV"));
    			
    			context.put("date_kiv",(String)d.get("date_kiv"));
    			context.put("catatan_kiv",(String)d.get("catatan_kiv"));
		    }
		    	context.put("dataPerintah", getrecord_perintah);
		    	context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",idUnitPskView,"disabled"));

    		if (getParam("flag_jenis_keputusan").equals("0")){
    			setValueFlagJenisKeputusan("checked","","");
    		    context.put("viewSelesai", "yes");
        		context.put("viewTangguh", "no");
        		context.put("viewBatal", "no");
			} else if (getParam("flag_jenis_keputusan").equals("1")){
				setValueFlagJenisKeputusan("","checked","");
    		    context.put("viewSelesai", "no");
        		context.put("viewTangguh", "yes");
        		context.put("viewBatal", "no");
			} else if (getParam("flag_jenis_keputusan").equals("2")){
				setValueFlagJenisKeputusan("","","checked");
    		    context.put("viewSelesai", "no");
        		context.put("viewTangguh", "no");
        		context.put("viewBatal", "yes");
			}
			context.put("TEMPcheckedSelesai",checkedSelesai);
			context.put("TEMPcheckedTangguh",checkedTangguh);
			context.put("TEMPcheckedBatal",checkedBatal);
			
			

   			//get jumlah_harta_tarikhmohon
			FrmPrmhnnSek8KptsanBicaraData.setJumlahBayaran17(id_permohonansimati);
			getJumlahBayaran17 = FrmPrmhnnSek8KptsanBicaraData.getJumlahBayaran17();

			FrmPrmhnnSek8KptsanBicaraData.setMaklumatPermohonan17(idpermohonan);
			getMaklumatPermohonan17 = FrmPrmhnnSek8KptsanBicaraData.getMaklumatPermohonan17();

			if(getJumlahBayaran17.size()!=0){
				Hashtable a = (Hashtable) getJumlahBayaran17.get(0);
				Hashtable z = (Hashtable) getMaklumatPermohonan17.get(0);
		   		double jumlah_harta17_tarikhmohon;
	    		double bayaranYuranPerintah17;
	    		jumlah_harta17_tarikhmohon = Double.parseDouble(a.get("sumharta").toString());
	    		String batal_kuasa_pentadbir = z.get("batal_kuasa_pentadbir").toString();
	    		String batal_p_amanah = z.get("batal_p_amanah").toString();
//				myLogger.info("BATAL KUASA TADBIR :: "+batal_kuasa_pentadbir);
//				myLogger.info("BATAL P.AMANAH :: "+batal_p_amanah);

				//Azam Add on 20/4/2011 , jumlah_harta17_tarikhmohon >= 1
				if ( jumlah_harta17_tarikhmohon >= 1 && jumlah_harta17_tarikhmohon <= 1000 ) {
					//bayaranYuranPerintah17 = 10.00 ;
					bayaranYuranPerintah17 = (0.2/100) * jumlah_harta17_tarikhmohon ;
					//ADD BY PEJE
					bayaranYuranPerintah17 = getBundaranBayaran(bayaranYuranPerintah17);
				} else if ( (jumlah_harta17_tarikhmohon >= 1001) && (jumlah_harta17_tarikhmohon <= 50000) ){
					//bayaranYuranPerintah17 = 30.00 ;
					bayaranYuranPerintah17 = (0.2/100) * jumlah_harta17_tarikhmohon ;
					//ADD BY PEJE
					bayaranYuranPerintah17 = getBundaranBayaran(bayaranYuranPerintah17);
				} else {
					bayaranYuranPerintah17 = (0.2/100) * jumlah_harta17_tarikhmohon ;
					//ADD BY PEJE
					bayaranYuranPerintah17 = getBundaranBayaran(bayaranYuranPerintah17);
				}
				double j = 0;
					if (batal_kuasa_pentadbir.equals("Y")) {
						j += 30.00 ;
					}
					if (batal_p_amanah.equals("Y")){
						j += 30.00 ;
					}
				double total = (j + bayaranYuranPerintah17);
				this.context.put("txtJumBayaranTerkini", total);
//				myLogger.info("BAYARAN YURAN PERINTAH 17 :: "+bayaranYuranPerintah17);
//				myLogger.info("BAYARAN J :: "+j);
//				myLogger.info("TOTAL BAYARAN KESELURUHAN :: "+total);
			}
				context.put("dataJumlahBayaran", getJumlahBayaran17);

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

		    vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

		}else if("Simpan_Tangguh".equals(submit)){

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

			getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
			long idUnitPsk = 0;
			if( getrecord_infoperbicaraan.size() != 0 ){
				Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
				idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
			}
				context.put("selectEditPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"EDITsocPegawaiPengendali",idUnitPsk,"class=mediumselect"));

			if (doPost.equals("true")) {
				    add_maklumat_tangguh(usid,idpermohonan,id_perbicaraan);
				   // edit_status_BicaraTangguh(idpermohonan,idsuburusanstatusfail,usid);
				  //  edit_BicaraTangguhStatus_tblrujsuburusanstatusfail(idpermohonan,id_perbicaraan,
					//		usid,idsuburusanstatusfail,idFail);
				  
				  //:::SUB
				    //ID_STATUS : 44
				    //ID_SUBURUSAN : 420
				    logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"44","420",idFail);
		    }

		    FrmPrmhnnSek8KptsanBicaraData.setInfoPerintah(idpermohonan,id_perbicaraan);
		    getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahView();
		    Hashtable d = new Hashtable();
		    long idUnitPskView = 0;
		    String flag_jenis_keputusan = "";
		    if(getrecord_perintah.size()!=0){
				d = (Hashtable) getrecord_perintah.get(0);
				idUnitPskView = Long.parseLong(d.get("id_unitpsk").toString());
				flag_jenis_keputusan = d.get("flag_jenis_keputusan").toString();
		    }
		    	context.put("dataPerintahView", getrecord_perintah);
				context.put("selectViewPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",idUnitPskView,"disabled class=mediumselect"));

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

		    vm = "app/ppk/frmPrbcrnSek8KeputusanPerbicaraanSelesai17.jsp";

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
    			tarikh_bicara = (String)idn.get("tarikh_bicara");
    			id_perbicaraan = (String)idn.get("id_perbicaraan");
    		}
    			context.put("tarikh_bicara",tarikh_bicara);
    			context.put("id_perbicaraan",id_perbicaraan);
    			context.put("selectPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,"socPegawaiPengendali",idUnitPsk,"disabled class=autoselect"));

			getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(idpermohonan,id_perbicaraan);
			String id_fail = "";
			if ( getrecord_infoperbicaraan.size() != 0 ){
				Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
				id_fail = h.get("id_fail").toString();
			}

			String txdTarikhPerakuanAdd = getParam("txdTarikhPerakuanAdd");
			String txdTarikhBicara = getParam("txdTarikhBicara");
			String txtMasaBicara = getParam("txtMasaBicara");
			String socTempatBicara = getParam("socTempatBicara");
			String txtCatatanAdd = getParam("txtCatatanAdd");
			String socNegeriBicara = getParam("socNegeri");

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
			    //ID_SUBURUSAN : 779
				logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,usid,"172","779",idFail);
				
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
		    	flag_jenis_keputusan = d.get("flag_jenis_keputusan").toString();
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

    		context.put("addMode", "no");
    		context.put("viewMode", "yes");
    		context.put("editMode", "no");
    		//context.put("tab", "");
    		context.put("flag", "onChange");
    		context.put("listSemak", list);

			vm = "app/ppk/frmPrmhnnKolateral17.jsp";

       	}else if("permohonanKolateral".equals(submit)){

       		context.put("idpermohonan", getParam("idpermohonan"));

       		String selectedTab = "";
    		selectedTab = getParam("tabId").toString();
            if (selectedTab == null || "".equals(selectedTab)){
            	selectedTab="0";
            }
            context.put("selectedTab",selectedTab);

            vm = "app/ppk/frmPrmhnnKolateral17.jsp";


       	}else if("keputusanKolateral".equals(submit)){

       		String id_perbicaraan = getParam("id_perbicaraan");
		    this.context.put("id_perbicaraan", id_perbicaraan);

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
				myLogger.info("ID BAYARAN :: "+idBayaran);
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

            vm = "app/ppk/frmPrmhnnKolateral17.jsp";

        }else {
        	//clear field carian
        	context.put("txtNoFail", "");
        	context.put("txtPemohon", "");
        	context.put("txtSimati", "");
        	context.put("txtIcSimati", "");
        	context.put("jenisIc", "");

	        FrmSenaraiFailKptsPerbcrnData.setList17(usid);
			list = FrmSenaraiFailKptsPerbcrnData.getList17();

    		//data & size default list
    		context.put("listNotis", list);
    		context.put("list_size", list.size());

		    vm = "app/ppk/frmSenaraiFailKeputusanBicaraanSek17.jsp";

		}//close else
   	
   	System.out.println("vm-----------"+vm);
   		setupPage(session,action,list);
    	this.context.put("flagFromSenaraiFailSek8", flagFromSenaraiFailSek8);
    	this.context.put("flagFromSenaraiPermohonanSek8", flagFromSenaraiPermohonanSek8);
    	this.context.put("flagForView", flagForView);
        //Template template = this.engine.getTemplate(vm);
        //return template;
        return vm;

    }//close public template

	 private void deleteMaklumatSyariah(String id_borangj,String id_perbicaraan) throws Exception {
	    FrmPrmhnnSek8BicaraData.deleteMaklumatSyariah(id_borangj,id_perbicaraan);
	 }

	 private void deleteMaklumatMufti(String id_borangj,String id_perbicaraan) throws Exception {
	    FrmPrmhnnSek8BicaraData.deleteMaklumatMufti(id_borangj,id_perbicaraan);
	 }

	 private void deleteMaklumatROTS(String id_borangj,String id_perbicaraan) throws Exception {
	    FrmPrmhnnSek8BicaraData.deleteMaklumatROTS(id_borangj,id_perbicaraan);
	 }

	 private void deleteUploadFail(String id_dokumen,String id_lampiran) throws Exception {
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

		logic4.setCarianFail17(noFail,namaPemohon,namaSimati,icSimati,JenisIc,usid);
	}

	//* Pertelingkahan Kolateral
	private void addKoleteral_updateTblppkperintah(String idpermohonan,String usid,String id_perbicaraan,
			String id_perintah) throws Exception {

		//TBLPPKKOLETERALMST
		String txdTarikhPerakuanAdd = getParam("txdTarikhPerakuanAdd");
		String txdTarikhBicara = getParam("txdTarikhBicara");
		String txtMasaBicara = getParam("txtMasaBicara");
		String socTempatBicara = getParam("socTempatBicara");
		String txtCatatanAdd = getParam("txtCatatanAdd");
		//String socNegeriBicara = getParam("socNegeri");
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

	    FrmPrmhnnSek8KptsanBicaraData.edit_BicaraTangguhStatusTblrujsuburusanstatusfail17(idpermohonan,id_perbicaraan,
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
			String is_perbicaraan,String idsuburusanstatusfail,String idFail) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_statusTblrujsuburusanstatusfail_TangguhKolateral17(idpermohonan,usid,
				is_perbicaraan,idsuburusanstatusfail,idFail);
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
		 	String txtCatatanTangguh = getParam("txtCatatanTangguh");
		 	String txtPendapatTangguh = getParam("txtPendapatTangguh");

		    FrmPrmhnnSek8KptsanBicaraData.updateMaklumatTangguh(idpermohonan,usid,id_perintah,
		    		id_perbicaraan,EDITsocPegawaiPengendali,txdTarikhPerintahEDIT,flag_tangguh,
		    		txtCatatanTangguh,txtPendapatTangguh,txtTempoh);
		}

		private void updateMaklumatBatal(String usid,String id_perintah,
				String id_perbicaraan,String idpermohonan) throws Exception {

		 	String flag_jenis_keputusan = getParam("flag_jenis_keputusan");
		 	String EDITsocPegawaiPengendali = getParam("EDITsocPegawaiPengendali");
		    String txdTarikhPerintahEDIT = getParam("txdTarikhPerintahEDIT");
		    String txtCatatanBatal = getParam("txtCatatanBatal");
		    String flag_batal = getParam("flag_batal");

		    FrmPrmhnnSek8KptsanBicaraData.updateMaklumatBatal(usid,id_perintah,
					id_perbicaraan,idpermohonan,flag_jenis_keputusan,EDITsocPegawaiPengendali,
		    		txdTarikhPerintahEDIT,txtCatatanBatal,flag_batal);
		}


		private void edit_status_tblrujsuburusanstatusfail_batal(String usid,String idpermohonan,
				String id_perbicaraan,String idsuburusanstatusfail,String idFail) throws Exception {

		    FrmPrmhnnSek8KptsanBicaraData.edit_statusTblrujsuburusanstatusfail_batal17(usid,idpermohonan,
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

	private void edit_status_tblrujsuburusanstatusfail_Tangguh17(String usid,String idpermohonan,
			String idsuburusanstatusfail,String idFail) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_statusTblrujsuburusanstatusfail_Tangguh17(usid,idpermohonan,
				idsuburusanstatusfail,idFail);
	}

	private void edit_status_tangguh(String idpermohonan,String usid,String idsuburusanstatusfail) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_status_tangguh(idpermohonan,usid,idsuburusanstatusfail);
	}

	private void edit_status_batalMT(String idpermohonan,String usid) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_status_batalMT(idpermohonan,usid);
	}


	private void edit_status_tblrujsuburusanstatusfail_batalMT(String usid,String idpermohonan,
			String idsuburusanstatusfail,String idFail) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_statusTblrujsuburusanstatusfail_BatalMT17(usid,idpermohonan,
				idsuburusanstatusfail,idFail);
	}

	private void updateMahkamah(String usid,String id_perintah,String id_perbicaraan) throws Exception {

    	String jenisPerintah = getParam("jenisPerintah");
    	String txdTarikhPerintahEdit = getParam("txdTarikhPerintahEdit");
    	String id_pejabat = getParam("socTempatBicara");
    	String id_unitpsk = getParam("socPegawai");

	    FrmPrmhnnSek8KptsanBicaraData.updateDataMahkamah(usid,id_perintah,id_perbicaraan,
	    		jenisPerintah,txdTarikhPerintahEdit,id_pejabat,id_unitpsk);
	}

	//*Menunggu Keputusan Rujukan Mahkamah Tinggi
	private void insertMahkamah(String usid,String id_perbicaraan) throws Exception {

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


	private void updateMaklumatSelesai17(String usid,String idpermohonan,String id_perintah,String id_perbicaraan) throws Exception {

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
	    
	    String check_kiv = getParam("check_kiv");
	    String date_kiv = getParam("date_kiv");
	    String catatan_kiv = getParam("catatan_kiv");
	    
	    String check_doc = getParam("check_doc");
	    String valueKIV = getParam("valueKIV");
	    
	    if(check_kiv.equals("")){
	    	check_kiv = valueKIV;
	    }

	    FrmPrmhnnSek8KptsanBicaraData.updateMaklumatSelesai17(usid,idpermohonan,id_perintah,id_perbicaraan,
	    		id_bayaran_perintah,id_bayaran_pusaka,id_bayaran_baitulmal,EDITflag_jenis_keputusan,txtJumBayaranEDIT,
	    		txtNomborResitPerintahEDIT,txdTarikhBayaranPerintahEDIT,txtJumBayaranPusakaEDIT,txtNomborResitPusakaEDIT,
	    		txdTarikhBayaranPusakaEDIT,txtJumBayaranBaitulmalEDIT,txtNomborResitBaitulmalEDIT,txdTarikhBayaranBaitulmalEDIT,
	    		txtCatatanSelesaiEDIT,EDITsocPegawaiPengendali,txdTarikhPerintahEDIT,check_kiv,date_kiv,catatan_kiv);
	}


	private void edit_status_tblrujsuburusanstatusfail(String usid,String idpermohonan,String id_perbicaraan,
			String idsuburusanstatusfail,String idFail) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_statusTblrujsuburusanstatusfail17(usid,idpermohonan,id_perbicaraan,
				idsuburusanstatusfail,idFail);
	}

	private void edit_status_selesai(String idpermohonan,String usid,String idsuburusanstatusfail) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_status_selesai(idpermohonan,usid);
	}

	private void add_MaklumatBayaranBaitulMal(String usid,String id_perbicaraan,String idpermohonan) throws Exception {

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

	    FrmPrmhnnSek8KptsanBicaraData.add_BayaranBaitulMal17(usid,id_perbicaraan,idpermohonan,txdTarikhPerintah,
	    		EDITsocPegawaiPengendali,flag_jenis_keputusan,txtCatatanSelesai,id_jenisbayaranBaitulMal,
	    		txtJumBayaranBaitulmal,txtNomborResitBaitulmal,txdTarikhBayaranBaitulmal);
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

	    FrmPrmhnnSek8KptsanBicaraData.add_BayaranPusaka17(usid,idpermohonan,id_perbicaraan,txdTarikhPerintah,
	    		EDITsocPegawaiPengendali,flag_jenis_keputusan,txtCatatanSelesai,id_jenisbayaranCukaiPusaka,
	    		txtJumBayaranPusaka,txtNomborResitPusaka,txdTarikhBayaranPusaka);
	}

	private void add_MaklumatBayaranPerintah(String idSimati,String idpermohonan,String id_perbicaraan,String usid) throws Exception {

		//TBLPPKPERINTAH
		String txdTarikhPerintah = getParam("txdTarikhPerintah");
		String EDITsocPegawaiPengendali = getParam("EDITsocPegawaiPengendali");
		String flag_jenis_keputusan = getParam("flag_jenis_keputusan");
		String txtCatatanSelesai = getParam("txtCatatanSelesai");

		//TBLPPKBAYARAN
		String id_jenisbayaranPerintah = getParam("id_jenisbayaranPerintah");
		String txtJumBayaran = getParam("txtJumBayaran");
		String txtNomborResitPerintah = getParam("txtNomborResitPerintah");
		String txdTarikhBayaranPerintah = getParam("txdTarikhBayaranPerintah");
		
		String check_kiv = getParam("check_kiv");
		String check_doc = getParam("check_doc");
		String valueKIV = getParam("valueKIV");
		
		 
	    if(check_kiv.equals("")){
	    	check_kiv = valueKIV;
	    }
		
		String date_kiv = getParam("date_kiv");
		String catatan_kiv = getParam("catatan_kiv");

		FrmPrmhnnSek8KptsanBicaraData.add_BayaranPerintah17(idSimati,idpermohonan,id_perbicaraan,usid,txdTarikhPerintah,
				EDITsocPegawaiPengendali,flag_jenis_keputusan,txtCatatanSelesai,id_jenisbayaranPerintah,
				txtJumBayaran,txtNomborResitPerintah,txdTarikhBayaranPerintah,check_kiv,date_kiv,catatan_kiv);
	}


	private void edit_status_ROTSkeputusan(String idpermohonan, String usid) throws Exception {
	    FrmPrmhnnSek8KptsanBicaraData.edit_status_ROTSkeputusan(idpermohonan, usid);
	}

	private void edit_status_Syariah(String idpermohonan, String usid) throws Exception {
	    FrmPrmhnnSek8KptsanBicaraData.edit_status_Syariah(idpermohonan, usid);
	}


	private void edit_status_tblrujsuburusanstatusfail_Syariah17(String idsuburusanstatusfail, String usid, String id_perbicaraan, String id_fail, String idpermohonan) throws Exception {
	    FrmPrmhnnSek8KptsanBicaraData.edit_statusTblrujsuburusanstatusfail_Syariah17( idsuburusanstatusfail, usid, id_perbicaraan, id_fail, idpermohonan);
	}

	private void edit_status_tblrujsuburusanstatusfail_ROTSkeputusan(String idsuburusanstatusfail, String usid, String id_perbicaraan, String id_fail, String idpermohonan) throws Exception {
	    FrmPrmhnnSek8KptsanBicaraData.edit_statusTblrujsuburusanstatusfail_ROTSkeputusan( idsuburusanstatusfail, usid, id_perbicaraan, id_fail, idpermohonan);
	}

	private void edit_status_tblppkpermohonanMS(String idpermohonan, String usid) throws Exception {
	    FrmPrmhnnSek8KptsanBicaraData.edit_status_tblppkpermohonanMS(idpermohonan, usid);

	}

	private void edit_status_tblppkpermohonanMufti(String idpermohonan, String usid) throws Exception {
	    FrmPrmhnnSek8KptsanBicaraData.edit_status_tblppkpermohonanMufti(idpermohonan, usid);
	}

	private void edit_status_tblrujsuburusanstatusfailMS(String idsuburusanstatusfail, String usid, String id_perbicaraan, String idFail, String idpermohonan) throws Exception {
		FrmPrmhnnSek8KptsanBicaraData.edit_status_tblrujsuburusanstatusfailMS17( idsuburusanstatusfail, usid, id_perbicaraan, idFail, idpermohonan);

	}

	private void edit_status_tblrujsuburusanstatusfailMufti(String idsuburusanstatusfail, String usid, String id_perbicaraan, String idFail, String idpermohonan) throws Exception {
		FrmPrmhnnSek8KptsanBicaraData.edit_status_tblrujsuburusanstatusfailMufti( idsuburusanstatusfail, usid, id_perbicaraan, idFail, idpermohonan);

	}

	private void addKeputusan_Editblppkkolateralmst( String idpermohonan, String IdKolateralmst, String usid, String flag_keputusan,
			String txtBayaranPerintahKol, String txtNomborResitPerintahKol, String txtCatatanKeputusan, String txdTarikhBayaranPerintahKol ) throws Exception {

		FrmPrmhnnSek8KptsanBicaraData.addKeputusan_Editblppkkolateralmst( idpermohonan, IdKolateralmst, usid, flag_keputusan,
				txtBayaranPerintahKol, txtNomborResitPerintahKol, txtCatatanKeputusan, txdTarikhBayaranPerintahKol );
	}

	private void updateKeputusan_tblppkkolateralmst( String idpermohonan, String IdKolateralmst, String usid, String flag_keputusan,
			String txtBayaranPerintahKol, String txtNomborResitPerintahKol, String txtCatatanKeputusan, String txdTarikhBayaranPerintahKol, String idBayaran ) throws Exception {

		FrmPrmhnnSek8KptsanBicaraData.updateKeputusan_tblppkkolateralmst( idpermohonan, IdKolateralmst, usid, flag_keputusan,
				txtBayaranPerintahKol, txtNomborResitPerintahKol, txtCatatanKeputusan, txdTarikhBayaranPerintahKol, idBayaran );
	}

	private void edit_status_KeputusanKolateral( String idpermohonan, String usid ) throws Exception {
	    FrmPrmhnnSek8KptsanBicaraData.edit_status_KeputusanKolateral( idpermohonan, usid );
	}

	private void edit_status_tblrujsuburusanstatusfail_KeputusanKolateral( String idpermohonan, String idsuburusanstatusfail, String idFail, String usid ) throws Exception {

	    FrmPrmhnnSek8KptsanBicaraData.edit_status_tblrujsuburusanstatusfail_KeputusanKolateral17( idpermohonan,
	    		idsuburusanstatusfail, idFail, usid );
	}

	private void hapusExistingMaklumatBayaran( String idpermohonan ) throws Exception {
		   logic4.hapusExistingMaklumatBayaran( idpermohonan );
	}

	private void prepareItemForDisplay(HttpSession session, Vector objVector, int cntItemPage, String command)
	  {
	    int x;
	    int startno = 0;
	    if (command == null) command = "first";
	    if (session.getAttribute("_portal_startno") != null) startno = ((Integer)session.getAttribute("_portal_startno")).intValue();
	    if (command.equals("previous"))
	      if (startno == objVector.size()) {
	        x = startno % cntItemPage;
	        if (x > 0) {
	          startno -= x;
	          startno -= cntItemPage;
	        } else {
	          startno -= cntItemPage * 2;
	          if (startno < 0) startno = 0;
	        }
	      } else {
	        startno -= cntItemPage * 2;
	        if (startno < 0) startno = 0;
	      }
	    else if (command.equals("first"))
	      startno = 0;

	    else if (command.equals("last"))
	      x = cntItemPage;
	    else if (command.equals("back"))
	      if (startno == objVector.size()) {
	        x = startno % cntItemPage;
	        if (x == 0) x = cntItemPage;
	        startno -= x;
	      } else {
	        startno -= cntItemPage;
	        if (startno < 0) startno = 0;

	      }

	    Vector moduleVector = new Vector();
	    int i = 0; int cnt = 0;
	    if (objVector.size() > 0) {
	      cnt = 0; for (i = startno; (cnt < cntItemPage) && (i < objVector.size()); ) {
	        moduleVector.addElement(objVector.elementAt(i));

	        ++i; ++cnt;
	      }

	    }

	    session.setAttribute("_portal_moduleVector", moduleVector);

	    this.context.put("startnoInt", new Integer(startno));
	    this.context.put("totalInt", new Integer(objVector.size()));

	    startno = i;

	    session.setAttribute("_portal_startno", new Integer(startno));
	  }

	////////

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
		//return Utils.parseDouble(Utils.format2Decimal(bayaran));
		//Dikemaskini oleh Rosli pada 24/07/2013
		return Utils.parseDouble(String.valueOf(bayaran));
		
	}

}//close class
