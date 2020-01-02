package ekptg.view.ppt;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.ppt.MaklumatJabatanTeknikalSeksyen8;

public class FrmSek8MaklumatSuratJTTambah extends VTemplate {
	
	static Logger myLogger = Logger.getLogger(FrmSek8MaklumatSuratJTTambah.class);
	
	MaklumatJabatanTeknikalSeksyen8 logic = new MaklumatJabatanTeknikalSeksyen8();
	Vector listSurat = new Vector();
	Vector listPohon = new Vector();
	
    public Template doTemplate() throws Exception
    {
       	HttpSession session = this.request.getSession();
       	
    	String vm = "";
    	String geturusan = "0";
    	String getlaporantanah = "0";
    	String tarikhmohon = "";
    	String nofail = ""; 
    	
    	Vector list = new Vector();
    	Vector alamatKementerian = new Vector();
    	Vector alamatAgensi = new Vector();
    	Vector namaNegeri = new Vector();  	
    	
    	String checkedAda = "";
    	String checkedTiada = "";
    	String checkedTidak = "";
    	String checkedYa = "";
    	
    	list.clear();
    	  	
    	String submit = getParam("command"); 
    	myLogger.info("submit ===> "+submit);

		if("getSenaraiMahkamah".equals(submit)){

			vm = "app/ppt/frmSek8MaklumatSuratJTTambah.jsp";
				
		}else if("Skrin_Tambah".equals(submit)){
			
			String txdTarikhSurat = "";
			this.context.put("txdTarikhSurat", "");
			
			String txdTarikhHantar = "";
			this.context.put("txdTarikhHantar", "");
			
			String txtTempoh = "";
			this.context.put("txtTempoh", "");
			
			String txtPerihal = "";
			this.context.put("txtPerihal", "");	
			
			String addAlamat1 = "";
			this.context.put("addAlamat1", ""); 
			
			String addAlamat2 = "";
			this.context.put("addAlamat2", "");
			
			String addAlamat3 = "";
			this.context.put("addAlamat3", "");
			
			String addPoskod = "";
			this.context.put("addPoskod", "");						
			
			String addNegeri = "";
			this.context.put("addNegeri", "");
			
			String namaneg = "";
			this.context.put("namaneg", "");	
			
			this.context.put("id_fail", getParam("id_fail"));
			int id_fail = Integer.parseInt(getParam("id_fail"));
			
			this.context.put("id_permohonan", getParam("id_permohonan"));
			int id_permohonan = Integer.parseInt(getParam("id_permohonan"));
			
			this.context.put("id_status", getParam("id_status"));
			int id_status = Integer.parseInt(getParam("id_status"));
					
			Vector getrecord_permohonan = MaklumatJabatanTeknikalSeksyen8.getRecord(id_fail,id_permohonan, id_status);	
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("PermohonanList", getrecord_permohonan);
			
			context.put("add", "yes");
			context.put("flag_terima", 0);
			
			vm = "app/ppt/frmSek8LaporanAwalTanahSuratTambah.jsp"; 
			
    		context.put("SelectAgensi",HTML.SelectAgensi("agensi",null,"style=width:450px"));
			context.put("SelectBandar",HTML.SelectMukim("bandar",null,"style=width:260px"));
    		context.put("SelectDaerah",HTML.SelectDaerah("daerah",null,"style=width:260px"));
    		
    		//--Filter--//
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian","style=width:450px onChange=\"doChangeidKementerian();\""));
    		context.put("SelectNegeri",HTML.SelectNegeri("negeri","onChange=\"doChangeidNegeri2();\""));
 
    		context.put("list", "");
    		//list = MaklumatJabatanTeknikalSeksyen8.getList();	
    		
    		context.put("add", "yes");
			context.put("flag_terima", 0);
			
			vm = "app/ppt/frmSek8LaporanAwalTanahSuratTambah.jsp"; 
			
		}else if("kembali_skrin2".equals(submit)){
			
			this.context.put("id_fail", getParam("id_fail"));
			int id_fail = Integer.parseInt(getParam("id_fail"));
			
			this.context.put("id_permohonan", getParam("id_permohonan"));
			int id_permohonan = Integer.parseInt(getParam("id_permohonan"));
			
			this.context.put("id_status", getParam("id_status"));
			int id_status = Integer.parseInt(getParam("id_status"));
			
			this.context.put("id_suburusan", getParam("id_suburusan"));
			int id_suburusan = Integer.parseInt(getParam("id_suburusan"));	
			
			context.put("add", "yes");
    		context.put("view", "no");
    		context.put("edit", "no");
			
			vm = "app/ppt/frmSek8LaporanAwalTanahSurat.jsp"; 
			
			Vector getrecord_permohonan = MaklumatJabatanTeknikalSeksyen8.getRecord(id_fail,id_permohonan, id_status);
			this.context.put("PermohonanList", getrecord_permohonan);			

		}else if("Kembali_skrin1".equals(submit)){			
			
			vm = "app/ppt/frmSek8JabTeknikalSenarai.jsp";
			
			if (!"".equals(getParam("txdTarikhMohon")));
			tarikhmohon = getParam("txdTarikhMohon");
			
			if (!"".equals(getParam("no_fail")));
				nofail = getParam("no_fail");
			
			if (!"".equals(getParam("socStatusLaporanAwalTanah")));
				getlaporantanah = getParam("socStatusLaporanAwalTanah");
						
				MaklumatJabatanTeknikalSeksyen8.setList(nofail, tarikhmohon, getlaporantanah);
				list = MaklumatJabatanTeknikalSeksyen8.getList();	
			
		    this.context.put("PermohonanList", list);
		    this.context.put("carianFail", nofail);  
		    this.context.put("CarianTarikhMohon", tarikhmohon);		    	   			
		    this.context.put("selectStatusJabatanTeknikal",HTML.SelectStatusJabatanTeknikal("socStatusJabatanTeknikal",null,""));			

		}else if("Simpan_JT".equals(submit)){
    		
    		vm = "app/ppt/frmSek8LaporanAwalTanahSurat.jsp"; 	
    		
			this.context.put("id_fail", getParam("id_fail"));
			int id_fail = Integer.parseInt(getParam("id_fail"));
			
			this.context.put("id_permohonan", getParam("id_permohonan"));
			int id_permohonan = Integer.parseInt(getParam("id_permohonan"));
			
			this.context.put("id_status", getParam("id_status"));
			int id_status = Integer.parseInt(getParam("id_status"));
			
			this.context.put("flag_terima", getParam("flag_terima"));
			int flag_terima = Integer.parseInt(getParam("flag_terima"));
			myLogger.info("flag terima---->>"+flag_terima);
			
			if (flag_terima == 1){ //pilihan YA
				
				myLogger.info("masuk flag terima = 1");
				
				context.put("add", "no");
	    		context.put("flag_terima", 1);	
	    		context.put("view", "no");
	    			
	    		vm = "app/ppt/frmSek8LaporanAwalTanahSuratTambah.jsp";	
	    		
	    		
	    		
	    		
							
			}

			if (flag_terima == 2){ //pilihan TIDAK
				myLogger.info("masuk flag terima = 2");

			    String agensi = getParam("agensi");
			    //bil_surat autogenerate
			    //String txtBilSurat = getParam("txtBilSurat");
			    String txtNoRujukan = getParam("txtNoRujukan");		
			    String txdTarikhSurat = getParam("txdTarikhSurat");		
			    String txdTarikhHantar = getParam("txdTarikhHantar");	
			    String txtTempoh = getParam("txtTempoh");
			    String txtPerihal = getParam("txtPerihal");
			    flag_terima = Integer.parseInt(getParam("flag_terima"));
			    String alamat1 = getParam("alamat1");
			    String alamat2 = getParam("alamat2");
			    String alamat3 = getParam("alamat3");
			    String poskod = getParam("poskod");
			    String negeri = getParam("negeri");
	
			    myLogger.info("----------------- flag_terima == 2 ---------------");
			    myLogger.info("id_fail---"+id_fail);
			    myLogger.info("id_permohonan---"+id_permohonan);
			    myLogger.info("id_status---"+id_status);
			    myLogger.info("agensi---"+agensi);
			    myLogger.info("txtNoRujukan---"+txtNoRujukan);
			    myLogger.info("txdTarikhSurat---"+txdTarikhSurat);
			    myLogger.info("txdTarikhHantar---"+txdTarikhHantar);
			    myLogger.info("txtTempoh---"+txtTempoh);
			    myLogger.info("txtPerihal---"+txtPerihal);
			    myLogger.info("flag_terima---"+flag_terima);
			    myLogger.info("alamat1---"+alamat1);
			    myLogger.info("alamat2---"+alamat2);
			    myLogger.info("alamat3---"+alamat3);
			    myLogger.info("poskod---"+poskod);
			    myLogger.info("negeri---"+negeri);
			    myLogger.info("--------------------------------------------------");
			    
			    add_maklumat_UlasanTeknikal(session);
			    edit_maklumat_UlasanTeknikal(session);
			    
				this.context.put("id_suburusan", getParam("id_suburusan"));
				int id_suburusan = Integer.parseInt(getParam("id_suburusan"));					
				
				context.put("add", "yes");
	    		context.put("view", "no");
	    		context.put("edit", "no");
				
				vm = "app/ppt/frmSek8LaporanAwalTanahSurat.jsp"; 
				
				Vector getrecord_permohonan = MaklumatJabatanTeknikalSeksyen8.getRecord(id_fail,id_permohonan, id_status);
				this.context.put("PermohonanList", getrecord_permohonan);
				
				Vector getrecord_jabteknikal = MaklumatJabatanTeknikalSeksyen8.getRecordListJabatanTeknikal(id_permohonan);
				this.context.put("PermohonanListJabTeknikal", getrecord_jabteknikal);
				
			}	

		}else if("doChangeidKementerian".equals(submit)){
			
    		String idKementerian = getParam("kementerian");
    		int idK = Integer.parseInt(idKementerian);
    		
    		alamatKementerian = logic.getAlamatKementerian(idK);
    		myLogger.info("idK1 = " +getParam("kementerian"));
    		myLogger.info("idK2 = " +idK);
    		myLogger.info("alamat = " +alamatKementerian);
    		
    		Hashtable AK = (Hashtable) alamatKementerian.get(0);
    		String AK_negeri;
    		String alamat1;
    		String alamat2;
    		String alamat3;
    		String poskod;
    		String namanegeri;
    		
    		AK_negeri = AK.get("id_negeri").toString();
    		alamat1 = AK.get("alamat1").toString();
    		alamat2 = AK.get("alamat2").toString();
    		alamat3 = AK.get("alamat3").toString();
    		poskod = AK.get("poskod").toString();
    		namanegeri = AK.get("nama_negeri").toString();
    
    		//----------------get and post all field content---------------------//
    		context.put("txtPerihal", getParam("txtPerihal"));
    		context.put("addPoskod", poskod);
    		context.put("addAlamat1", alamat1);
    		context.put("addAlamat2", alamat2);
    		context.put("addAlamat3", alamat3);
    		context.put("txtNoRujukan", getParam("txtNoRujukan"));
    		context.put("txdTarikhSurat", getParam("txdTarikhSurat"));
    		context.put("txdTarikhHantar", getParam("txdTarikhHantar"));
    		context.put("txtTempoh", getParam("txtTempoh"));
    		context.put("namaneg", namanegeri);
    		
    		String idNegeri = AK_negeri;
    		
    		context.put("SelectNegeri",HTML.SelectNegeri("negeri",Utils.parseLong(idNegeri),null,"onChange=\"doChangeidNegeri2();\" "));
    		//context.put("SelectDaerah",HTML.SelectDaerah("daerah",Utils.parseLong(idDaerah),"class=mediumselect"));
    		//context.put("project_negeri",HTML.SelectNegeri("projek_negeri",Utils.parseLong(idProjekNegeri),null,"onChange=\"doChangeidNegeri();\" "));
    		
    		if (getParam("flag_terima").equals("1")){
				checkedAda = "checked";
				checkedTiada = "";
			} else if (getParam("flag_terima").equals("2")){
				checkedAda = "";
				checkedTiada = "checked";
			}
			context.put("TEMPcheckedAda",checkedAda);
			context.put("TEMPcheckedTiada",checkedTiada);
    		
    		//--------------------------------------------------------------------//
			
    		if(idKementerian!="")
    		{
    			context.put("SelectAgensi",HTML.SelectAgensiByKementerian("agensi",idKementerian,null,"style=width:450px onChange=\"doChangeAlamatAgensi();\" "));
    		}
    		else{
    			context.put("SelectAgensi",HTML.SelectAgensi("agensi","style=width:450px onChange=\"doChangeAlamatAgensi();\" "));	
    		}
    			
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian",Utils.parseLong(idKementerian),null,"style=width:450px onChange=\"doChangeidKementerian();\" "));
    		
   	    	context.put("dataPermohonan", "");
    		context.put("semak", "no");
    		context.put("edit", "no");
    		
	        vm = "app/ppt/frmSek8LaporanAwalTanahSuratTambah.jsp";
   	    } 
    	else if ("doChangeidNegeri2".equals(submit)) {  		
    		
    		//----------------get and post all field content---------------------//
    		context.put("addTujuan", getParam("tujuan"));
    		context.put("addPoskod", getParam("poskod"));
    		context.put("addAlamat1", getParam("alamat1"));
    		context.put("addAlamat2", getParam("alamat2"));
    		context.put("addAlamat3", getParam("alamat3"));
    		context.put("addTarikhSurat", getParam("tarikh_surat"));
    		context.put("addTarikhHendak", getParam("tarikh_kehendaki"));
    		context.put("addRujukan_kementerian", getParam("rujukan_kementerian"));
    		context.put("tarikhPohon", getParam("edit_tarikh_permohonan"));
    		
    		String SuburusanUPT = getParam("socSuburusan");
    		String idKementerian = getParam("kementerian");
    		String idAgensi = getParam("agensi");
    		String idDaerah = getParam("daerah");
    		String idProjekNegeri = getParam("projek_negeri");
    		
    		context.put("SelectSuburusanUPT",HTML.SelectSuburusanUPT("socSuburusan",Utils.parseLong(SuburusanUPT),"style=width:400px"));
    		context.put("SelectDaerah",HTML.SelectDaerah("daerah",Utils.parseLong(idDaerah),"class=mediumselect"));
    		context.put("SelectAgensi",HTML.SelectAgensi("agensi",Utils.parseLong(idAgensi),null,"style=width:450px onChange=\"doChangeAlamatAgensi();\" "));	
    		context.put("project_negeri",HTML.SelectNegeri("projek_negeri",Utils.parseLong(idProjekNegeri),null,"onChange=\"doChangeidNegeri();\" "));
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian",Utils.parseLong(idKementerian),null,"style=width:450px onChange=\"doChangeidKementerian();\" "));
    		
    		if (getParam("flag_terima").equals("1")){
				checkedAda = "checked";
				checkedTiada = "";
			} else if (getParam("flag_terima").equals("2")){
				checkedAda = "";
				checkedTiada = "checked";
			}
			context.put("TEMPcheckedAda",checkedAda);
			context.put("TEMPcheckedTiada",checkedTiada);

    		//--------------------------------------------------------------------//

    		int idK = Integer.parseInt(idKementerian);
    		
    		alamatKementerian = logic.getAlamatKementerian(idK);
    		myLogger.info("idK1 = " +getParam("kementerian"));
    		myLogger.info("idK2 = " +idK);
    		myLogger.info("alamat = " +alamatKementerian);
    		
    		Hashtable AK = (Hashtable) alamatKementerian.get(0);
    		String AK_negeri;
    		String namanegeri;
    		
    		AK_negeri = AK.get("id_negeri").toString();
    		namanegeri = AK.get("nama_negeri").toString();			
			
			String idNegeri = getParam("negeri");
		
    		context.put("addNegeri", namanegeri);
    		
    		String now = "";
    		
    		now = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
    		context.put("currentDATE",getParam("tarikh_permohonan"));
    		
    		context.put("currentSuburusan", "");
    		context.put("currentStatus", "");
    		context.put("saiz_listTanah", list.size());
    		
    		context.put("SelectNegeri",HTML.SelectNegeri("negeri",Utils.parseLong(idNegeri),null,"onChange=\"doChangeidNegeri2();\" "));
    		
    		context.put("list", "");
    		
	        vm = "app/ppt/frmSek8LaporanAwalTanahSuratTambah.jsp";
   	    }//close doChangeidNegeri2
    	
    	else if ("doChangeidNegeri".equals(submit)) {
    		
    		//----------------get and post all field content---------------------//
    		context.put("addTujuan", getParam("tujuan"));
    		context.put("addPoskod", getParam("poskod"));
    		context.put("addAlamat1", getParam("alamat1"));
    		context.put("addAlamat2", getParam("alamat2"));
    		context.put("addAlamat3", getParam("alamat3"));
    		context.put("addTarikhSurat", getParam("tarikh_surat"));
    		context.put("addTarikhHendak", getParam("tarikh_kehendaki"));
    		context.put("addRujukan_kementerian", getParam("rujukan_kementerian"));
    		context.put("tarikhPohon", getParam("edit_tarikh_permohonan"));
    		
    		String SuburusanUPT = getParam("socSuburusan");
    		String idKementerian = getParam("kementerian");
    		String idAgensi = getParam("agensi");
    		String idBandar = getParam("bandar");
    		String idNegeri = getParam("negeri");
    		
    		context.put("SelectSuburusanUPT",HTML.SelectSuburusanUPT("socSuburusan",Utils.parseLong(SuburusanUPT),"style=width:400px"));
    		context.put("SelectAgensi",HTML.SelectAgensi("agensi",Utils.parseLong(idAgensi),null,"style=width:450px onChange=\"doChangeAlamatAgensi();\" "));	
    		context.put("SelectBandar",HTML.SelectMukim("bandar",Utils.parseLong(idBandar),"class=mediumselect"));
    		context.put("SelectNegeri",HTML.SelectNegeri("negeri",Utils.parseLong(idNegeri),null,"onChange=\"doChangeidNegeri2();\" "));
    		
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian",Utils.parseLong(idKementerian),null,"style=width:450px onChange=\"doChangeidKementerian();\" "));
    		
    		/*if (getParam("new_flag_peruntukan").equals("1")){
				checkedAda = "checked";
				checkedTiada = "";
			} else if (getParam("new_flag_peruntukan").equals("2")){
				checkedAda = "";
				checkedTiada = "checked";
			}
			context.put("TEMPcheckedAda",checkedAda);
			context.put("TEMPcheckedTiada",checkedTiada);
    		
			if (getParam("new_flag_segera").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (getParam("new_flag_segera").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);*/
    		//--------------------------------------------------------------------//
    		
    		String idProjekNegeri = getParam("projek_negeri");
 
   	    	context.put("dataPermohonan", "");
    		context.put("semak", "no");
    		context.put("edit", "no");
    		
    		String now = "";
    		
    		now = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
    		context.put("currentDATE",getParam("tarikh_permohonan"));
    		
    		context.put("currentSuburusan", "");
    		context.put("currentStatus", "");
    		context.put("saiz_listTanah", list.size());
    			
    		context.put("project_negeri",HTML.SelectNegeri("projek_negeri",Utils.parseLong(idProjekNegeri),null,"onChange=\"doChangeidNegeri();\" "));
    		context.put("SelectDaerah",HTML.SelectDaerahByNegeri(idProjekNegeri,"daerah",null,"class=mediumselect"));
    		
    		context.put("list", "");
    		
	        vm = "app/ppt/frmSek8LaporanAwalTanahSuratTambah.jsp";
    	
    	}else if("view_recordsurat".equals(submit)){	
			    		
    		this.context.put("id_ulasanteknikal", getParam("id_ulasanteknikal"));
			int id_ulasanteknikal = Integer.parseInt(getParam("id_ulasanteknikal"));
			
			this.context.put("id_jabatanteknikal", getParam("id_jabatanteknikal"));
			int id_jabatanteknikal = Integer.parseInt(getParam("id_jabatanteknikal"));
    		
    		this.context.put("id_permohonan", getParam("id_permohonan"));
			int id_permohonan = Integer.parseInt(getParam("id_permohonan"));
			
			this.context.put("id_status", getParam("id_status"));
			int id_status = Integer.parseInt(getParam("id_status"));
			
			context.put("add", "no");
    		context.put("view", "yes");
    		context.put("edit", "no");
    		context.put("flag_terima", 2);
    		
			
			vm = "app/ppt/frmSek8LaporanAwalTanahSuratTambah.jsp"; 
			
			Vector getrecord_surat = MaklumatJabatanTeknikalSeksyen8.getRecordSurat(id_permohonan, id_ulasanteknikal, id_jabatanteknikal);
			this.context.put("PermohonanGetSurat", getrecord_surat);   
			
			Hashtable hs = (Hashtable) getrecord_surat.get(0);
			
			if (hs.get("flag_terima").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (hs.get("flag_terima").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			
			context.put("checkedYa",checkedYa);
			context.put("checkedTidak",checkedTidak);
			
		}else if("openSkrin".equals(submit)){
			
			context.put("add", "no");
			context.put("edit", "no");
    		context.put("view", "no");  		
    		context.put("flag_terima", 1);			
    		
    		vm = "app/ppt/frmSek8LaporanAwalTanahSuratTambah.jsp";
				
			
		}else if("getSuratDariJT".equals(submit)){			
			
			vm = "app/ppt/frmSek8MaklumatSuratJTTambah.jsp";
			
			
		}else if("getSenaraiMahkamah".equals(submit)){		
			
			vm = "app/ppt/frmSek8MaklumatSuratJTTambah.jsp";
			
		}else{
			
			vm = "app/ppt/frmSek8JabTeknikalSenarai.jsp";
			
			if (!"".equals(getParam("txdTarikhMohon")));
				tarikhmohon = getParam("txdTarikhMohon");
				
			if (!"".equals(getParam("no_fail")));
				nofail = getParam("no_fail");
			
			if (!"".equals(getParam("socStatusLaporanAwalTanah")));
				getlaporantanah = getParam("socStatusLaporanAwalTanah");
						
				MaklumatJabatanTeknikalSeksyen8.setList(nofail, tarikhmohon, getlaporantanah);
				list = MaklumatJabatanTeknikalSeksyen8.getList();	
				
		    this.context.put("PermohonanList", list);
		    this.context.put("carianFail", nofail);  
		    this.context.put("CarianTarikhMohon", tarikhmohon);		    	   			
		    this.context.put("selectStatusJabatanTeknikal",HTML.SelectStatusJabatanTeknikal("socStatusLaporanAwalTanah",null,""));			

		}    	     	
        Template template = this.engine.getTemplate(vm);
        return template;
    }

	private void edit_maklumat_UlasanTeknikal(HttpSession session) throws Exception {
		
		String id_permohonan = getParam("id_permohonan");
	    
	    Hashtable h = null;
	    h = new Hashtable();

	    h.put("id_permohonan", id_permohonan);

		MaklumatJabatanTeknikalSeksyen8.edit_status(h);
	}

	private void add_maklumat_UlasanTeknikal(HttpSession session) throws Exception {
		
		//String id_permohonan = getParam("id_permohonan");
		String id_fail = getParam("id_fail");
		String id_status = getParam("id_status");
		String agensi = getParam("agensi");
		//String txtBilSurat = getParam("txtBilSurat");-->autogenerate
		String txdTarikhSurat = getParam("txdTarikhSurat");
		String txdTarikhHantar = getParam("txdTarikhHantar");
		String txtTempoh = getParam("txtTempoh");
		String txtPerihal = getParam("txtPerihal");
		String flag_terima = getParam("flag_terima");
	    String alamat1 = getParam("alamat1");
	    String alamat2 = getParam("alamat2");
	    String alamat3 = getParam("alamat3");
	    String poskod = getParam("poskod");
	    String negeri = getParam("negeri");
	    String txtNoRujukan = getParam("txtNoRujukan");

		this.context.put("id_permohonan", getParam("id_permohonan"));
		int id_permohonan = Integer.parseInt(getParam("id_permohonan"));
	    
	    listSurat = logic.getRecordListJabatanTeknikal(id_permohonan);
	    myLogger.info("listSurat.size ===>"+listSurat.size());
		int a = listSurat.size();
		int b = a + 1;
		
		int bil_surat = b;
		
	  	myLogger.info("----------------- add_maklumat_UlasanTeknikal ---------------");
	    myLogger.info("id_fail---"+id_fail);
	    myLogger.info("id_permohonan---"+id_permohonan);
	    myLogger.info("id_status---"+id_status);
	    myLogger.info("agensi---"+agensi);
	    myLogger.info("txdTarikhSurat---"+txdTarikhSurat);
	    myLogger.info("txdTarikhHantar---"+txdTarikhHantar);
	    myLogger.info("txtTempoh---"+txtTempoh);
	    myLogger.info("txtPerihal---"+txtPerihal);
	    myLogger.info("flag_terima---"+flag_terima);
	    myLogger.info("alamat1---"+alamat1);
	    myLogger.info("alamat2---"+alamat2);
	    myLogger.info("alamat3---"+alamat3);
	    myLogger.info("poskod---"+poskod);
	    myLogger.info("negeri---"+negeri);
	    myLogger.info("txtNoRujukan---"+txtNoRujukan);
	    myLogger.info("listSurat.size() ===>"+bil_surat);
	    myLogger.info("--------------------------------------------------");
	    
	    Hashtable h = null;
	    h = new Hashtable();

	    h.put("id_permohonan", id_permohonan);
	    h.put("agensi", agensi);
	    h.put("txdTarikhSurat", txdTarikhSurat);
	    h.put("txdTarikhHantar", txdTarikhHantar);
	    h.put("bil_surat", bil_surat);
	    h.put("txtTempoh", txtTempoh);
	    h.put("txtPerihal", txtPerihal);
	    h.put("flag_terima", flag_terima);
	    h.put("alamat1", alamat1);
	    h.put("alamat2", alamat2);
	    h.put("alamat3", alamat3);
	    h.put("poskod", poskod);
	    h.put("negeri", negeri);
	    h.put("txtNoRujukan", txtNoRujukan);

	    MaklumatJabatanTeknikalSeksyen8.add_ulasanteknikal(h);
	}
}