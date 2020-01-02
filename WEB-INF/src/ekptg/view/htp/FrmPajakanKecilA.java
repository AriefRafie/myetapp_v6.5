package ekptg.view.htp;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.File;
import ekptg.helpers.HTML;
import ekptg.model.entities.Tblhtphakmilikbangunan;
import ekptg.model.htp.FrmGadaianHakmilikData;
import ekptg.model.htp.FrmGadaianPenHamilikData;
import ekptg.model.htp.FrmPajakanKecilHakmilikData;
import ekptg.model.htp.FrmPajakanKecilMaklumatData;
import ekptg.model.htp.FrmPajakanKecilPendaftaranData;
import ekptg.model.htp.FrmPajakanKecilSenaraiPermohonanData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmSenaraiFailPajakanKecilData;
import ekptg.model.htp.FrmSewaanDerafData;
import ekptg.model.htp.FrmUtilData;


public class FrmPajakanKecilA extends AjaxBasedModule{	
	/**
	 * 
	 */
	private final String PATH="app/htp/pajakankecil/";
	private Long idHakmilikUrusan = 0L;
	private static final long serialVersionUID = 7738995540185184003L;
	private static Logger mylog = Logger.getLogger(ekptg.view.htp.FrmPajakanKecilA.class);

	public String doTemplate2()throws Exception {
		 
	    HttpSession session = this.request.getSession();
	    String template_name = "app/htp/frmPajakanKecilSenaraiFail.jsp";
      	String disability = "disabled";
    	String readability = "";

	    String socAgensi = "";
	    String socKementerian = "";
	    String socNegeri = "";
	    String socUrusan = "";
	    String socSuburusan = "";
		Vector semakanSenarai = new Vector();

	    this.context.put("util", new lebah.util.Util());

	    Vector senaraiFail = null;
		    
	    socNegeri = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri");
	    
        String id_kementerian = getParam("sockementerian");
	    System.out.println("FrmPajakanKecilA:id_kementerian::"+id_kementerian);
	    String submit = getParam("command");
	    System.out.println("FrmPajakanKecilA:submit::"+submit);
	    String idFail = getParam("fail");
	    System.out.println("FrmPajakanKecilA:fail::"+idFail);
	    String pageMode = getParam("pagemode");
	    System.out.println("FrmPajakanKecilA:pagemode::"+pageMode);
	    String langkah = getParam("langkah");
	    System.out.println("FrmPajakanKecilA:langkah::"+langkah);
	    //String fail = getParam("fail");
	    //System.out.println("FrmPajakanKecilA:fail::"+fail);
		String intPB = "0";

	    if (!("".equals(submit))) {
	    	this.context.put("idsuburusan","44");
	    	this.context.put("idurusan","309");
	    	
	    	if("pksenaraifailcari".equals(submit)){ //carian
	    	    System.out.println("FrmPajakanKecilA: equals(submit)::pksenaraifailcari");

	    		String nofail = getParam("nofail");
	    		template_name = "app/htp/frmSenaraiFailPajakanKecil.jsp";
	    		senaraiFail = FrmSenaraiFailPajakanKecilData.getList(nofail);
	    		this.context.put("senaraiList", senaraiFail);  

	    	}else if("pksenaraipermohonan".equals(submit)){
	    	    System.out.println("FrmPajakanKecilA: equals(submit)::pksenaraipermohonan");
			    //String fail = getParam("fail");
		    	Vector senaraiPermohonan = null;
		    	senaraiPermohonan = FrmPajakanKecilSenaraiPermohonanData.getList(idFail);
		    	this.context.put("senaraiList", senaraiPermohonan);	    	
		    	this.context.put("idFail", idFail);	    	
		    	template_name = "app/htp/frmPajakanKecilSenaraiPermohonan.jsp";	
//Tambah Pemilik Baru##########################################		    	
		    }else if("pkfailbaru".equals(submit)){
		    	mylog.info("equals(submit)::pkfailbaru");
		    	String strOperation = "";
		    	template_name = "app/htp/frmPajakanKecilPendaftaran.jsp";
			    this.context.put("socSeksyen","3");
			    
			    if(pageMode.equals("1")){
			    	
			    	strOperation = "success";
				    //String idnegeri = getParam("socNegeri");
				    //String idagensi = getParam("socAgensi");
				    
					long longIdFail = ekptg.helpers.DB.getNextID("TBLPFDFAIL_SEQ");
					simpanFail(session,longIdFail);
					//int idPermohonan=46;
					String idPermohonan=simpanPermohonan(session,0,""+longIdFail);
				    this.context.put("idPermohonan",idPermohonan);
					String[] cbsemaks = this.request.getParameterValues("cbsemaks");
		        	FrmSemakan frmSemak = new FrmSemakan();
		        	//frmSemak.semakanHapusByPermohonan(id);
		        	if(cbsemaks!=null){
		        		for (int i = 0; i < cbsemaks.length; i++) { 
		        			frmSemak = new FrmSemakan();
		        			System.out.println("FrmPajakanKecilA:pkfailbaru|pageMode["+pageMode+"::cbsemaks:::"+cbsemaks[i]);
		        			frmSemak.semakanTambah(cbsemaks[i], ""+idPermohonan);           
		        		}
		        	}
		        	//after saved
					Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(""+idPermohonan);
				    this.context.put("permohonanInfo", permohonan);
				    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());
				    
				    socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),"disabled class=disabled");
			    	socKementerian = HTML.SelectKementerian("sockementerian",Long.parseLong(permohonan.get("idkementerian").toString()),"disabled class=disabled");
			    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),"disabled class=disabled");

			    	socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("309"),"disabled");
			    	this.context.put("socUrusan",socUrusan);
			    
				    // Senarai semakan
				    semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh","aktif");
				    this.context.put("senaraiSemakan",semakanSenarai );
				    
			    	//socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(idnegeri)," disabled class=disabled");
				    //socKementerian = HTML.SelectKementerian("sockementerian", (id_kementerian == "") ? null : Long.parseLong(id_kementerian)," disabled class=disabled");
			    	//socAgensi = HTML.SelectAgensiByKementerian("socAgensi",id_kementerian,Long.parseLong(idagensi)," disabled class=disabled");
			    	pageMode = "2";
			    	
			    	
			    }else if(pageMode.equals("3")){
			    	String id = getParam("id_kemaskini");

					Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
				    this.context.put("permohonanInfo", permohonan);
				    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());
				    
				    socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),"disabled class=disabled");
			    	socKementerian = HTML.SelectKementerian("sockementerian",Long.parseLong(permohonan.get("idkementerian").toString()),"disabled class=disabled");
			    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),null);

			    	socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("309"),"disabled");
			    	this.context.put("socUrusan",socUrusan);
			    
				    // Senarai semakan
				    semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh"+permohonan.get("idnegeri"),"aktif");
				    this.context.put("senaraiSemakan",semakanSenarai );
			    	
			    	pageMode = "4";
			    }/*else if(pageMode.equals("3")){
			    	
			    	pageMode = "4";
			    }*/else{

			    	String id = getParam("id_kemaskini");
			    	System.out.println("FrmPajakanKecilA: equals(submit)::pkfailbaru:::else|id"+id);
			    	
					Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
				    this.context.put("permohonanInfo", permohonan);
				    Hashtable h = new Hashtable();
					h.put("id_Fail", Long.parseLong((String)permohonan.get("idpermohonan")));
				    h.put("id_kemaskini", 1);

				    FrmSenaraiFailPajakanKecilData.kemaskiniFail(h);
				    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());
				    
				    
				    socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),"disabled class=disabled");
			    	socKementerian = HTML.SelectKementerian("sockementerian",Long.parseLong(permohonan.get("idkementerian").toString()),"disabled class=disabled");
			    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),"disabled class=disabled");

			    	socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("309"),"disabled");
			    	this.context.put("socUrusan",socUrusan);
			    
				    // Senarai semakan
				    //semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh","aktif");
				    //24/10/2010
			    	semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh"+permohonan.get("idnegeri"),"aktif");
				    this.context.put("senaraiSemakan",semakanSenarai );

			    	pageMode = "2";
    	
			    }
			    this.context.put("operation",strOperation);		    
			    this.context.put("socNegeri",socNegeri);
			    this.context.put("socKementerian",socKementerian);
			    this.context.put("socAgensi",socAgensi);
			    this.context.put("pageMode",pageMode);
		    //}
		    //}else{
	    	
	    	}else if ("doChangeKementerian".equals(submit)){
		    	System.out.println("FrmPajakanKecilA: equals(submit)::doChangeKementerian");

		       /* if (!("".equals(id_kementerian))) {
			     	socKementerian = HTML.SelectKementerian("sockementerian", (id_kementerian == "") ? null : Long.parseLong(id_kementerian), null, "onChange=\"doChangeKementerian()\" ");
			    	socAgensi = HTML.SelectAgensiByKementerian("socAgensi",id_kementerian,Long.parseLong("1"),"");
		
			    	//if ("doChangeKementerian".equals(submit)){
			    		//this.context.put("registerUserStatus", "none");
			       // }
			    	
			    	
			      }else {
			        //this.context.put("registerUserStatus", "none");
			        //this.context.put("selectSeksyen", HTML.SelectSeksyen("id_seksyen"));
			        //this.context.put("selectNegeri", HTML.SelectNegeri("id_negeri", "onChange=\"doChangeNegeri()\" "));
			        //this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(null, "id_daerah"));
			    	
			    	  socKementerian = HTML.SelectKementerian("sockementerian", (id_kementerian == "") ? null : Long.parseLong(id_kementerian), null, "onChange=\"doChangeKementerian()\" ");
			    	  socAgensi = HTML.SelectAgensiByKementerian("socAgensi",null,Long.parseLong("1"),"");
			      }*/
		    	template_name = "app/htp/frmPajakanKecilPendaftaran.jsp";
			    String idnegeri = getParam("socNegeri");
			    if(idnegeri == ""){
				    socNegeri = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri");
				    //socNegeri = HTML.SelectNegeri("socNegeri");
			    }else{	
			    	//socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(idnegeri),"enabled");
				    socNegeri = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri",Long.parseLong(idnegeri),"enabled");
			    }
		    	socKementerian = HTML.SelectKementerian("sockementerian", (id_kementerian == "") ? null : Long.parseLong(id_kementerian), null, "onChange=\"doChangeKementerian()\" ");
		    	socAgensi = HTML.SelectAgensiByKementerian("socAgensi",id_kementerian,Long.parseLong("1"),"");
				
			    socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("309"),"disabled class=disabled");
		    	this.context.put("socUrusan",socUrusan);
		    	String strdate = "";
		    	strdate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
		    	this.context.put("sekarang",strdate);
				//pageMode = Integer.parseInt(getParam("pagemode"));
			    this.context.put("nofail", "");  

			    semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh","aktif");
			    this.context.put("senaraiSemakan",semakanSenarai );
			    this.context.put("socSeksyen","3");

				this.context.put("pageMode","0");

	    	}else if("pkpendaftaranseterus".equals(submit)) {
		    	template_name = "app/htp/frmPajakanKecilSenaraiPemilik.jsp";	
		    	System.out.println("FrmPajakanKecilA: equals(submit)::pkpendaftaranseterus");

			    readability = "readonly";
			    disability = "disabled";

		    	Hashtable hakmilikbangunan = null;
		    	String id = getParam("id_kemaskini");
				Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
				
				socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
			    //this.context.put("socNegeri",socNegeri);
		    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),"disabled");
		    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),"disabled");
		    	
				hakmilikbangunan = FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(id);
				this.context.put("hakmilikbangunaninfo", hakmilikbangunan);
				Vector senaraiHakmilik = null;
				senaraiHakmilik = FrmPajakanKecilHakmilikData.getSenaraiHakmilik(Long.parseLong(id));
				this.context.put("senaraihakmilik", senaraiHakmilik);

				Vector senaraiPemilik = null;
				senaraiPemilik = FrmPajakanKecilHakmilikData.getSenaraiPemilik(Long.parseLong(id));
				this.context.put("senaraipemilik", senaraiPemilik);

		    	
	    	}else if ("pkpermohonantambah".equals(submit)) { //belum
		    	template_name = "app/htp/frmPajakanKecilPendaftaran.jsp";	
			    disability = "disabled class=disabled";

			    //template_name = "app/htp/frmPajakanKecilSemakPermohonan.jsp";	
				String id = getParam("id_kemaskini");
				//System.out.println("FrmPajakanKecil:pkpermohonantambah::id:::"+id);
				//FrmKemaskiniFailData.setData(Integer.parseInt(id));
				//Vector faildata = FrmKemaskiniFailData.getData();
	        	//Hashtable h = (Hashtable) faildata.get(0);
				
				Hashtable h = null;
				h = FrmSenaraiFailPajakanKecilData.getFailInfo(id);
			    socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()),disability);
		    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("idKementerian").toString()),disability);
				socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("309"),disability);
		    	this.context.put("socUrusan",socUrusan);
		        System.out.println("FrmPajakanKecil:pkpermohonantambah::tarikhDaftar:::"+h.get("tarikhDaftar"));
		        String strdate = "";
		    	strdate = lebah.util.Util.getDateTime((Date)h.get("tarikhDaftar"), "dd/MM/yyyy");
		    	this.context.put("sekarang",strdate);
		
		    	String noFail = (String)h.get("noFail");
			    this.context.put("nofail", noFail);  

			    Vector permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanHTP(id);
	        	Hashtable hpermohonan = (Hashtable) permohonan.get(0);
		    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(hpermohonan.get("idagensi").toString()),disability);

		    	this.context.put("idsuburusan","44");
		    	this.context.put("idurusan","309");
		    	this.context.put("idagensi",hpermohonan.get("idagensi"));
		 
				pageMode = "4";

			    //this.context.put("permohonanInfo", hpermohonan.get(""));
			    //this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());
			    // Senarai semakan
			    semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh");
			    this.context.put("senaraiSemakan",semakanSenarai );
				//System.out.println("FrmPajakanKecilSenaraiPermohonan:senaraiSemakan::"+FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh").size());
		    	
		    }else if ("pkpermohonankemaskini".equals(submit)) {
		    	mylog.info("equals(submit)::pkpermohonankemaskini");
	    		//1 //2 //3
		    	//template_name = "app/htp/frmPajakanKecilSemakPermohonan.jsp";	
		    	template_name = PATH+"frmPajakanKecilSemakPermohonan.jsp";	
				String id = getParam("id_kemaskini");
				Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);

			    if(pageMode.equals("4")){

			    	if(id==""){
			    		mylog.info("pkpermohonankemaskini::simpan");
			    		String idPermohonan=simpanPermohonan(session,1,"0");
			    		//System.out.println("FrmPajakanKecilA:pkpermohonankemaskini::idPermohonan:::"+idPermohonan);
			    		id = String.valueOf(idPermohonan);
			    		
			    	}else{
			    		Hashtable h = new Hashtable();
						h.put("id_Fail", Long.parseLong((String)permohonan.get("idpermohonan")));
						h.put("id_kemaskini", 1);

					    FrmSenaraiFailPajakanKecilData.kemaskiniFail(h);
						String[] cbsemaks = this.request.getParameterValues("cbsemaks");
			        	FrmSemakan frmSemak = new FrmSemakan();
			           	if(cbsemaks!=null){
			        		for (int i = 0; i < cbsemaks.length; i++) { 
			        			frmSemak = new FrmSemakan();
			        			//System.out.println("FrmPajakanKecilSenaraiPermohonan:semakankemaskini::cbsemaks:::"+cbsemaks[i]);
			        			frmSemak.semakanTambah(cbsemaks[i], id);           
			        		}
			        	} 
					    
					    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());					    
			    		permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
				    	pageMode = "2";

			    	}
				}else if(pageMode.equals("3")){
					
					socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),"");
					this.context.put("socAgensi", socAgensi);
			
				}else{
				
					this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());			    
					socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),"disabled class=disabled");
			    	pageMode = "2";
			    	
				}
				this.context.put("permohonanInfo", permohonan);
				socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),"disabled class=disabled");
		    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),"disabled class=disabled");
			    socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("309"),"disabled");
		    	this.context.put("socUrusan",socUrusan);
		    
			    // Senarai semakan
			    //semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh");
		    	//24/10/2010
			    semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh"+permohonan.get("idnegeri"),"aktif");
			    this.context.put("senaraiSemakan",semakanSenarai );
			    
	    	}else if("pksemakanseterus".equals(submit)) {
			    
	    		readability = "readonly";
			    disability = "disabled class=disabled";

		    	Hashtable hakmilikbangunan = null;
		    	String id = getParam("id_kemaskini");
				Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
				
				socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
			    //this.context.put("socNegeri",socNegeri);
		    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
		    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);
		    	
				hakmilikbangunan = FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(id);
				this.context.put("hakmilikbangunaninfo", hakmilikbangunan);
				Vector senaraiHakmilik = null;
				senaraiHakmilik = FrmPajakanKecilHakmilikData.getSenaraiHakmilik(Long.parseLong(id));
				this.context.put("senaraihakmilik", senaraiHakmilik);

				Vector senaraiPemilik = null;
				senaraiPemilik = FrmPajakanKecilHakmilikData.getSenaraiPemilik(Long.parseLong(id));
				this.context.put("senaraipemilik", senaraiPemilik);

				this.context.put("frmpk", new FrmPajakanKecilHakmilikData());

		    	template_name = "app/htp/frmPajakanKecilSenaraiPemilik.jsp";	
	    	
	    	}else if("pkpemilikkemaskini".equals(submit)){
	    		disability = "disabled class=disabled";
	    		String socDaerah = "";
//<-------------------Form pemilik baru------------------------------------->
			    	
				    //fail = idhakmilikurusan
				    //id_kemaskini = idpermohonan
				    
				    //String LHM = getParam("nohakmilik");
				    String vid = getParam("fail");
				    mylog.info("pkpemilikkemaskini 1");
				    String id = getParam("id_kemaskini");
				    mylog.info("pkpemilikkemaskini 2"+getParam("id_kemaskini"));
				    
					Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
					
					Hashtable infohakmilik = FrmUtilData.getHakmilikUrusan("0",vid); 
					ekptg.model.entities.Tblhtphakmilikurusan hakmilik = null;
					hakmilik = FrmPajakanKecilHakmilikData.getHakmilikInfo(Long.parseLong(vid));
			    	this.context.put("hakmilik", hakmilik);
			    	
					this.context.put("infohakmilik", infohakmilik);
					this.context.put("permohonanInfo", permohonan);
					this.context.put("socHakmilik",HTML.SelectJenisHakmilik("socHakmilik"));

					socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
			    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
			    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);
					socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerahalamat","");
					//Syiful
	                String socDaerah1 = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah1", " onChange=\"doChangeDaerah123("+id+")\" ");
	                String socMukim = HTML.SelectMukimByNegeri((String)permohonan.get("idnegeri"), "socMukim");
	                this.context.put("socDaerah1",socDaerah1);
	                this.context.put("socMukim", socMukim);                

	                String noLot = HTML.SelectLot("noLot");
	                this.context.put("noLot", noLot);

		    		//this.context.put("noL", LHM);
		    		this.context.put("nhk", vid);
		    		this.context.put("nhp", id);
					this.context.put("socDaerah",socDaerah);
					this.context.put("idnegeri",permohonan.get("idnegeri"));

					template_name = "app/htp/frmPajakanKecilPemilikKemaskiniN1.jsp";
		    	
		    	}else if("pilihMukim".equals(submit)){
	                template_name = "app/htp/frmPajakanKecilPemilikKemaskiniN.jsp";

				    this.context.put("pageMode", 0);  

		    		disability = "disabled class=disabled";
		    		String socDaerah = "";
		    		String socDaerah1 = "";
	                String socMukim = "";                   
	                String id = getParam("id_kemaskini");
	                //String socDaerah = getParam("socDaerah");
	                String idDaerah = getParam("socDaerah1");
	                Vector senaraiHakmilik = null;

	                senaraiHakmilik = FrmPajakanKecilHakmilikData.getSenaraiHakmilik(Long.parseLong(id));
	                this.context.put("senaraihakmilik", senaraiHakmilik);
	                Hashtable permohonan1 = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
	                this.context.put("permohonanInfo", permohonan1);
	                this.context.put("socHakmilik",HTML.SelectJenisHakmilik("socHakmilik"));
	                String noLot = HTML.SelectLot("noLot");
	                this.context.put("noLot", noLot);
	                
	                socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan1.get("idnegeri").toString()),disability);
	                socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan1.get("idkementerian").toString()),disability);
	                socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan1.get("idagensi").toString()),disability);
	                socDaerah1 = HTML.SelectDaerahByNegeri((String)permohonan1.get("idnegeri"),"socDaerah1",Long.parseLong(idDaerah) ," onChange=\"doChangeDaerah123("+id+")\"");

	                socDaerah = HTML.SelectDaerahByNegeri((String)permohonan1.get("idnegeri"),"socDaerah","" );
	                socMukim = HTML.SelectMukimByDaerah(idDaerah, "socMukim");
	                this.context.put("socDaerah",socDaerah);
	                this.context.put("socDaerah1",socDaerah1);
	                this.context.put("idnegeri",permohonan1.get("idnegeri"));
	                socMukim = HTML.SelectMukimByDaerah(idDaerah, "socMukim") ;

	                this.context.put("socMukim",socMukim);
/*
  * tambah hakmilik & pemilik baru  444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444
 * uuu */
		    }else if("pkpemiliktambah".equals(submit)) {
			    disability = "disabled class=disabled";

			    String socDaerah = "";
			    String socMukim = "";
			    //String socNegeri = "";
			    String socLuas = "";
			    String idHakmilik = "";
				String id = getParam("id_kemaskini");
				idHakmilik = getParam("fail");
				
				
				//pageMode = Integer.parseInt(getParam("pagemode"));
				//pageMode 0-baru,1-Kemaskini Hakmilik, 2-Kemaskini Pemilik
				//pageMode = 0;
				Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
				socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah");
				socMukim = HTML.SelectMukimByNegeri((String)permohonan.get("idnegeri"), "socMukim");
				this.context.put("socMukim", socMukim);
		

				this.context.put("socHakmilik",HTML.SelectJenisHakmilik("socHakmilik"));
				
				if(pageMode.equals("0")){
		    	    System.out.println("FrmPajakanKecil:pemiliktambah::idHakmilik:::"+idHakmilik);

				}else if(pageMode.equals("1")){
		    	    mylog.info("FrmPajakanKecil:pkpemiliktambah(1)::idHakmilik:::"+idHakmilik);
		    		//String id = getParam("id_kemaskini");
					//Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
		    	    if("".equals(idHakmilik)){
						
						Long idHakmilikU = getParam("fail")==""?0L:Long.parseLong(getParam("fail"));
						if(FrmPajakanKecilHakmilikData.isHakmilikUrusan(idHakmilikU)){														
							intPB = simpanPB(session,0,0L);
						}else{				
							simpanHakmilik(session,0);						
							intPB = simpanPB(session,0,idHakmilikUrusan);
							//idHakmilik = ""+idHakmilikUrusan;
						}
					}else{
						//Long idHakmilikU = getParam("fail")==""?0L:Long.parseLong(getParam("fail"));
						intPB = simpanPB(session,0,Long.parseLong(idHakmilik));
		
					}
					
					ekptg.model.entities.Tblhtppihakberkepentingan pihak = null;
					pihak = FrmPajakanKecilHakmilikData.getPemilikInfo(Long.parseLong(String.valueOf(intPB)));
			    	this.context.put("pihak", pihak);
			    	
			    	ekptg.model.entities.Tblhtphakmilikurusan hakmilik = null;
					hakmilik = FrmPajakanKecilHakmilikData.getHakmilikInfo(pihak.getIdHakmilikurusan());
			    	this.context.put("hakmilik", hakmilik);
		
					socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah",pihak.getIdDaerah(),disability);
		
		    	    
		    	    pageMode = "2";
				}else {
					
				    //String idHakmilik = "";
				    
			
				}
		    	this.context.put("pagemode", pageMode);
		    	
		    	
		    	disability = "disabled class=disabled";
			    //String socDaerah = "";
			    
			    //String LHM = getParam("nohakmilik");
			    String vid = getParam("fail");
			    mylog.info("pkpemilikkemaskini 1");
			    //String id = getParam("id_kemaskini");
			    mylog.info("pkpemilikkemaskini 2"+getParam("id_kemaskini"));
				//Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
		    	
			    this.context.put("permohonanInfo", permohonan);
			    
				this.context.put("socHakmilik",HTML.SelectJenisHakmilik("socHakmilik"));

				socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
		    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
		    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);
				socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerahalamat","");
				//Syiful
                String socDaerah1 = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah1", " onChange=\"doChangeDaerah123("+id+")\" ");
                //String socMukim = HTML.SelectMukimByNegeri((String)permohonan.get("idnegeri"), "socMukim");
                this.context.put("socDaerah1",socDaerah1);
                this.context.put("socMukim", socMukim);                

                String noLot = HTML.SelectLot("noLot");
                this.context.put("noLot", noLot);

	    		//this.context.put("noL", LHM);
	    		this.context.put("nhk", vid);
	    		this.context.put("nhp", id);
				this.context.put("socDaerah",socDaerah);
				this.context.put("idnegeri",permohonan.get("idnegeri"));
		    	//555555555555555555555555555555555555555555555555555555555555555555555555555555555

				socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
		    	this.context.put("idnegeri", permohonan.get("idnegeri"));
			    
		    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
		    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);
		
				//	System.out.println("FrmPajakanKecil:hakmilikbangunan::"+hakmilikbangunan.get("iddaerah"));
				Vector senaraiHakmilik = null;
				senaraiHakmilik = FrmPajakanKecilHakmilikData.getSenaraiHakmilik(Long.parseLong(id));
				this.context.put("senaraihakmilik", senaraiHakmilik);

				//	socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
				//template_name = "app/htp/frmPajakanKecilPemilikBaru.jsp";
				template_name = "app/htp/frmPajakanKecilPemilikKemaskiniN.jsp";
				this.context.put("socDaerah",socDaerah);

		    }else if("pkpemilikseterus".equals(submit)) {
		    	Hashtable hakmilikbangunan = null;
			    readability = "readonly";
			    disability = "disabled class=disabled";
			    String socDaerah = "";
			    //String socNegeri = "";
			    String socLuas = "";
			    
				String id = getParam("id_kemaskini");
				Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
		    	
				hakmilikbangunan = FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(id);
				this.context.put("hakmilikbangunaninfo", hakmilikbangunan);
				
				socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
			    //this.context.put("socNegeri",socNegeri);
		    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
		    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);


				if(hakmilikbangunan==null){
					socLuas = HTML.SelectLuas("socLuas");
					socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah");
					String socPegawai = HTML.SelectPegawai("socPegawai");
					this.context.put("socPegawai",socPegawai);

					template_name = "app/htp/frmPajakanKecilMaklumatBaru.jsp";
				}else{
					System.out.println("FrmPajakanKecilA:hakmilikbangunan::"+hakmilikbangunan.get("iddaerah"));
					socLuas = HTML.SelectLuas("socLuas",Long.parseLong(hakmilikbangunan.get("idluas").toString()),disability);
					socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah",Long.parseLong(hakmilikbangunan.get("iddaerah").toString()),disability);
					socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
					String socPegawai = HTML.SelectPegawai("socPegawai",Long.parseLong(hakmilikbangunan.get("idpegawai").toString()) ,disability);
					this.context.put("socPegawai",socPegawai);
					//System.out.println("FrmPajakanKecilA:getJarakBulan()::"+getJarakBulan(new Date((String)hakmilikbangunan.get("tmula")),new Date((String)hakmilikbangunan.get("ttamat"))));

					this.context.put("pagaMode","2");
					
					template_name = "app/htp/frmPajakanKecilMaklumat.jsp";
				}

			    this.context.put("socLuas",socLuas);
			    this.context.put("socDaerah",socDaerah);
			    System.out.println("FrmPajakanKecilA:FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(id)::"+FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(id));
		    
		    }else if("pkmaklumat".equals(submit)){
				template_name = "app/htp/frmPajakanKecilMaklumatBaru.jsp";
				String id = getParam("id_kemaskini");
			    readability = "readonly";
			    disability = "disabled class=disabled";
			    String socDaerah = "";				
			    Hashtable hakmilikbangunan = null;

				Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);


			    socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah");
			    this.context.put("socDaerah",socDaerah);
			    //String socNegeri = "";
			    socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
				if(pageMode.equals("0")){
		    	    System.out.println("FrmPajakanKecil:pkmaklumat::idHakmilik:::"+id);
					Tblhtphakmilikbangunan s = new Tblhtphakmilikbangunan();
					//s.setIdHakmilikbangunan(idHakmilikbangunan);
				    /*System.out.println("FrmPajakanKecil:maklumatsimpan1::"+getParam("id_kemaskini"));
				    System.out.println("FrmPajakanKecil:maklumatsimpan2::"+getParam("txtalamat1"));
				    System.out.println("FrmPajakanKecil:maklumatsimpan3::"+getParam("txtalamat2"));
				    System.out.println("FrmPajakanKecil:maklumatsimpan4::"+getParam("txtalamat3"));
				    System.out.println("FrmPajakanKecil:maklumatsimpan5::"+getParam("txtposkod"));
				    System.out.println("FrmPajakanKecil:maklumatsimpan6::"+getParam("socLuas"));
				    System.out.println("FrmPajakanKecil:maklumatsimpan7::"+getParam("txtluas"));
				    System.out.println("FrmPajakanKecil:maklumatsimpan8::"+getParam("socDaerah"));
				    System.out.println("FrmPajakanKecil:maklumatsimpan9::"+getParam("socNegeri"));
				    System.out.println("FrmPajakanKecil:maklumatsimpan10::"+getParam("txtsewa"));
				    System.out.println("FrmPajakanKecil:maklumatsimpan11::"+new Date());
				    System.out.println("FrmPajakanKecil:maklumatsimpan12::"+getParam("txddari"));
				    System.out.println("FrmPajakanKecil:maklumatsimpan13::"+getParam("txdhingga"));
				    */
				    
					s.setIdPermohonan(Long.parseLong(getParam("id_kemaskini")));
					s.setAlamat1(getParam("txtalamat1"));
					s.setAlamat2(getParam("txtalamat2"));
					s.setAlamat3(getParam("txtalamat3"));
					s.setPoskod(getParam("txtposkod"));
				    s.setIdLuas(Long.parseLong(getParam("socLuas")));
				    s.setLuas(getParam("txtluas"));
				    s.setIdDaerah(Long.parseLong(getParam("socDaerah")));
				    s.setIdNegeri(Long.parseLong(permohonan.get("idnegeri").toString()));
				    s.setSewaBulananDouble(Double.parseDouble(getParam("txtsewa")));
				    //s.setTarikhMula(new Date(getParam("txddari")));
				    //s.setTarikhTamat(new Date(getParam("txdhingga")));
				    s.setIdMasuk(Long.parseLong("1"));
				    s.setTarikhMasuk(new Date());
				    FrmPajakanKecilMaklumatData.add(s,getParam("txddari"),getParam("txdhingga"));
			    	
			    	hakmilikbangunan = FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(id);
				}else if(pageMode.equals("1")){
		    	    System.out.println("FrmPajakanKecil:pemiliktambah::idHakmilik:::"+id);
				}else if(pageMode.equals("3")){
		    	    System.out.println("FrmPajakanKecil:pemiliktambah::idHakmilik:::"+id);
					hakmilikbangunan = FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(id);
					this.context.put("hakmilikbangunaninfo", hakmilikbangunan);
					
					socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
			    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
			    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);

					String socLuas = HTML.SelectLuas("socLuas",Long.parseLong(hakmilikbangunan.get("idluas").toString()),null);
					socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah",Long.parseLong(hakmilikbangunan.get("iddaerah").toString()),"");
					String socPegawai = HTML.SelectPegawai("socPegawai",Long.parseLong(hakmilikbangunan.get("idpegawai").toString()) ,"");
					this.context.put("socPegawai",socPegawai);
					this.context.put("pageMode","4");
				    this.context.put("socLuas",socLuas);

				}else if(pageMode.equals("4")){
		    	    System.out.println("FrmPajakanKecil:pkmaklumat|4::idHakmilik:::"+id);
		    	    Hashtable s = new Hashtable();
		    	    
					hakmilikbangunan = FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(id);

					s.put("idhakmilikbangunan",Long.parseLong((String)hakmilikbangunan.get("idhakmilikbangunan")));
				    s.put("idpermohonan",Long.parseLong(getParam("id_kemaskini")));
					s.put("alamat1",getParam("txtalamat1"));
					s.put("alamat2",getParam("txtalamat2"));
					s.put("alamat3",getParam("txtalamat3"));
					s.put("poskod",getParam("txtposkod"));
				    s.put("idluas",Long.parseLong(getParam("socLuas")));
				    s.put("luas",getParam("txtluas"));
				    s.put("iddaerah",Long.parseLong(getParam("socDaerah")));
				    s.put("idnegeri",Long.parseLong(permohonan.get("idnegeri").toString()));
				    s.put("sewa",Double.parseDouble(getParam("txtsewa")));
				    //s.setTarikhMula(new Date(getParam("txddari")));
				    //s.setTarikhTamat(new Date(getParam("txdhingga")));
				    s.put("idpegawai",Long.parseLong(getParam("socPegawai")));
				    s.put("idkemaskini",Long.parseLong("1"));
				    s.put("tarikhkemaskini",new Date());
				    FrmPajakanKecilMaklumatData.kemaskini(s,getParam("txddari"),getParam("txdhingga"));
			    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
			    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);

				}

				this.context.put("hakmilikbangunaninfo", hakmilikbangunan);
				

				//System.out.println("FrmPajakanKecil:hakmilikbangunan::"+hakmilikbangunan.get("sewabulanan"));

		
		    }else if("pkmaklumatseterus".equals(submit)) {
			    disability = "disabled class=disabled";

				String id = getParam("id_kemaskini");
				Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
				
				socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
				socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
		    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);
	
		    	semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemak1");
		    	this.context.put("senaraiSemakan",semakanSenarai );
		
		    	template_name = "app/htp/frmPajakanKecilSemakanPKP.jsp";	
		    }else if("semakanPKPkemaskini".equals(submit))    {
		    	template_name = "app/htp/frmPajakanKecilSemakanPKP.jsp";	
				String id = getParam("id_kemaskini");
				String[] cbsemaks = this.request.getParameterValues("cbsemaks");
	    	    //System.out.println("FrmPajakanKecilSenaraiPermohonan:id::"+id);
	        	FrmSemakan frmSemak = new FrmSemakan();
				//VectorgetSenaraiSemakanHantar
	        	//frmSemak.semakanHapusByPermohonan(id);
	        	if(cbsemaks!=null){
	        		for (int i = 0; i < cbsemaks.length; i++) { 
	        			frmSemak = new FrmSemakan();
	        			System.out.println("FrmPajakanKecilSenaraiPermohonan:semakankemaskini::cbsemaks:::"+cbsemaks[i]);
	        			frmSemak.semakanTambah(cbsemaks[i], id);           
	        		}
	        	} 	
	        	Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
				
				socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
				socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
		    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);
	
				this.context.put("semak", new ekptg.model.htp.FrmSemakan());
				this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh"));

		    }  else if("pksemakanpkpseterus".equals(submit)) {
			    disability = "disabled class=disabled";

		    	Vector senarais = null;
		    	String id = getParam("id_kemaskini");
		    	senarais = FrmSewaanDerafData.getSenarai(id);
			    //this.context.put("utilderaf", new lebah.util.Util());
			    //System.out.println("FrmPajakanKecil:senarais::"+senarais.size());
				Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
				socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
				socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
		    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);

	   		    this.context.put("senaraideraf",senarais );
	   	
		    	template_name = "app/htp/frmPajakanKecilDeraf.jsp";	
		    }else if("pkderafseterus".equals(submit)){
		    	template_name = "app/htp/frmPajakanKecilSenaraiSurat.jsp";
		      	
		    }
	    	this.context.put("socNegeri",socNegeri);
	    	this.context.put("socKementerian",socKementerian);
	    	this.context.put("socAgensi",socAgensi);
		    this.context.put("pageMode", pageMode);  

	    	
	    }else{ // !=submit
	    	if("0->0".equals(langkah)){ //carian
	    	    System.out.println("FrmPajakanKecilA: !=submit::0->0");

	    		String nofail = getParam("nofail");
	    		template_name = "app/htp/frmPajakanKecilSenaraiFail.jsp";
	    		senaraiFail = FrmSenaraiFailPajakanKecilData.getList(nofail);
	    		this.context.put("senaraiList", senaraiFail);  

	    	}else if("0->1".equals(langkah)){
	    	    System.out.println("FrmPajakanKecilA: !=submit::0->1");
    		
		    	template_name = "app/htp/frmPajakanKecilPendaftaran.jsp";
			    this.context.put("socSeksyen","3");
				socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("309"),"disabled class=disabled");
		    	this.context.put("socUrusan",socUrusan);

		    	//perjanjian 44
		    	this.context.put("idsuburusan","44");
		    	this.context.put("idurusan","309");
		     	//socKementerian = HTML.SelectKementerian("sockementerian");
		     	
		    	String strdate = "";
		    	strdate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
		    	this.context.put("sekarang",strdate);
				//pageMode = Integer.parseInt(getParam("pagemode"));
			    this.context.put("pageMode", "0");  
			    this.context.put("nofail", "");  

			    semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh","aktif");
			    this.context.put("senaraiSemakan",semakanSenarai );


			    // by ajax
			     /* if (!("".equals(id_kementerian))) {
			     	socKementerian = HTML.SelectKementerian("sockementerian", (id_kementerian == "") ? null : Long.parseLong(id_kementerian), null, "onChange=\"doChangeKementerian()\" ");
			    	socAgensi = HTML.SelectAgensiByKementerian("socAgensi",id_kementerian,Long.parseLong("1"),"");
		
			    	//if ("doChangeKementerian".equals(submit)){
			    		//this.context.put("registerUserStatus", "none");
			       // }
//-----------------------------------------------------------			    	
			    	
			      }else {*/
			        //this.context.put("registerUserStatus", "none");
			        //this.context.put("selectSeksyen", HTML.SelectSeksyen("id_seksyen"));
			        //this.context.put("selectNegeri", HTML.SelectNegeri("id_negeri", "onChange=\"doChangeNegeri()\" "));
			        //this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(null, "id_daerah"));
			    	
			    	  socKementerian = HTML.SelectKementerian("sockementerian", (id_kementerian == "") ? null : Long.parseLong(id_kementerian), null, "onChange=\"doChangeKementerian()\" ");
			    	  socAgensi = HTML.SelectAgensiByKementerian("socAgensi","",Long.parseLong("1"),"");
			     // }
		    
		    
			    this.context.put("socNegeri",socNegeri);
			    this.context.put("socKementerian",socKementerian);
			    this.context.put("socAgensi",socAgensi);
			    this.context.put("pageMode", 0);  

	    		
	    	}else if("0->-1".equals(langkah)){
	    	    System.out.println("FrmPajakanKecilA: !=submit::0->-1");

			    //String fail = getParam("fail");
		    	Vector senaraiPermohonan = null;
		    	senaraiPermohonan = FrmPajakanKecilSenaraiPermohonanData.getList(idFail);
		    	this.context.put("senaraiList", senaraiPermohonan);	    	
		    	this.context.put("idFail", idFail);	    	
		    	template_name = "app/htp/frmPajakanKecilSenaraiPermohonan.jsp";	
		    }else{
	    	    System.out.println("FrmPajakanKecilA: !=submit::else:::user_id="+session.getAttribute("_ekptg_user_id"));

	    		template_name = "app/htp/frmPajakanKecilSenaraiFail.jsp";
	        
	    		senaraiFail = FrmSenaraiFailPajakanKecilData.getList("",(String)session.getAttribute("_ekptg_user_id"));
	    		//$session.getAttribute("_ekptg_user_id")<br>
	    		//Username : $session.getAttribute("_portal_username")<br>
	    		//Role: $session.getAttribute("_portal_role")<br>


	    		this.context.put("senaraiList", senaraiFail);  

	    	}
		 }

	    return template_name;
	  }

	 
	  private void simpanFail(HttpSession session, Long idfail) throws Exception {
		  Hashtable h = null;
		  h = new Hashtable();
		  String kodNegeriMampu = "";
		  String kodKementerianMampu = "";
		  int idNegeri = 0;
		  int idUrusan = 0;
		  int idKementerian = 0;
		  Vector vecFail = new Vector();
		  
		  idNegeri = Integer.parseInt(getParam("socNegeri"));
		  idUrusan = Integer.parseInt(getParam("idurusan"));
		  idKementerian = Integer.parseInt(getParam("sockementerian"));
		  
		  vecFail = FrmSenaraiFailPajakanKecilData.getFileCount(idNegeri,idUrusan);
		  kodNegeriMampu = FrmSenaraiFailPajakanKecilData.getNegeriByMampu(idNegeri);
		  kodKementerianMampu = FrmSenaraiFailPajakanKecilData.getKementerianByMampu(idNegeri);
		  //biFail = vecFail.size();
		  int fileSeq = 0;
		  
		  fileSeq = File.getSeqNo(Integer.parseInt(getParam("socSeksyen")), idUrusan, idKementerian, idNegeri);
		  System.out.println("FrmPajakanKecil:simpanFail::fileSeq:::"+fileSeq);

		  String noFail = "JKPTG/101/847/";
		  noFail += kodKementerianMampu+"/"+kodNegeriMampu+"-"+fileSeq;
         //JKPTG/101/882/"+this.getSocKementerian().getValue().toString()+"/"+Integer.parseInt(nTemp.getKodMampu())+"-"+bil
		  //
		  /*ID_FAIL              NUMBER CONSTRAINT TBLPFDFAIL_C01 NOT NULL,
	      -KOD_JABATAN          VARCHAR2(10 BYTE),
	      ID_TARAFKESELAMATAN  NUMBER,
	      ID_SEKSYEN           NUMBER,
	      ID_URUSAN            NUMBER,
	      ID_SUBURUSAN         NUMBER,
	      -TARIKH_DAFTAR_FAIL   DATE,
	      TAJUK_FAIL           VARCHAR2(300 BYTE),
	      NO_FAIL              VARCHAR2(400 BYTE),
	      -NO_FAIL_ROOT         VARCHAR2(400 BYTE),
	      -LOKASI               VARCHAR2(100 BYTE),
	      ID_NEGERI            NUMBER,
	      ID_KEMENTERIAN       NUMBER,
	      -FAHARASAT            VARCHAR2(100 BYTE),
	      FLAG_FAIL            NUMBER,
	      ID_STATUS            NUMBER,
	      -CATATAN              VARCHAR2(4000 BYTE),
	      ID_MASUK             NUMBER,
	      -TARIKH_MASUK         DATE,
	      -ID_KEMASKINI         NUMBER,
	      -TARIKH_KEMASKINI     DATE, */
		  int idmasuk = Integer.parseInt((String)session.getAttribute("_ekptg_user_id"));
		  h.put("id_Fail", idfail);
		  h.put("id_Tarafkeselamatan", "1");
		  h.put("id_Seksyen", getParam("socSeksyen"));
		  h.put("id_Urusan", idUrusan);
		  h.put("id_Suburusan", getParam("socSuburusan"));
		  h.put("tajuk_Fail", getParam("txttajuk"));
		  h.put("no_Fail", noFail);
		  h.put("id_Negeri", idNegeri);
		  h.put("id_Kementerian",idKementerian);
		  h.put("flag_Fail",1);
		  h.put("id_Status", "1");
		  h.put("id_Masuk", idmasuk);
		  //h.put("id_Lokasifail", "TIADA");
		  //h.put("id_Faharasat", "TIADA");
		  FrmSenaraiFailPajakanKecilData.janaFail(h);
		  

	  }

	  private void updatePermohonan(HttpSession session)throws Exception{
		  String id = getParam("id_kemaskini");
		    //String kod_cara_bayar = getParam("kod_cara_bayar");
		    //String keterangan = getParam("keterangan");
		    //String id_kemaskini = getParam("id_kemaskini");
		    //Date tarikh_kemaskini = getParam("tarikh_kemaskini");
		    //Date tarikh_kemaskini = new Date();
		    //CaraBayarData.update(Long.parseLong(id_carabayar), kod_cara_bayar,
		    	//	keterangan,Long.parseLong(id_kemaskini),tarikh_kemaskini);
	  }

	  private void deleteCaraBayar(HttpSession session) throws Exception {
	    String carabayar_id = getParam("id_carabayar");
	    //CaraBayarData.delete(carabayar_id);
	  }
	  
	  private String simpanPermohonan(HttpSession session,int i,String fail)throws Exception {
		  Hashtable data = null;
		  data = new Hashtable();
	
		  	//long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
	      //int idFail = (Integer)data.get("IdFail");
		  int idmasuk = Integer.parseInt((String)session.getAttribute("_ekptg_user_id"));
		  if(i==0)
	    	  data.put("IdFail", fail);
    	  else
	    	  data.put("IdFail", (String)getParam("fail"));
	      	//int idJKPTG = Integer.parseInt("1");
	      	String noPermohonan = "TIADA";
	      	String noPerserahan = "TIADA";
	      	data.put("no_Permohonan", noPermohonan);
	      	data.put("no_Perserahan", noPerserahan);
	      	//String TarikhSurKJP = (String)data.get("TarikhSurKJP");
	      	//String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";
	      	data.put("TarikhSurKJP", getParam("txdTarikhSuratKJP"));
	      	data.put("TarikhPermohonan", getParam("txdTarikhSuratKJP"));

	      	//String tujuan = (String)data.get("Tajuk");
	      	data.put("Tajuk", getParam("txttajuk"));
	      	//long idHtppermohonan = DB.getNextID("TBLHTPPERMOHONAN_SEQ");
	      	//int idAgensi = Integer.parseInt(data.get("socAgensi").toString());
	      	if(i==0)
	    	  data.put("id_Agensi", Integer.parseInt(getParam("socAgensi")));
	      	else
	    	  data.put("id_Agensi", Integer.parseInt(getParam("idagensi")));
	      	//int idSuburusan = (Integer)data.get("idSuburusan");
	      	data.put("idSuburusan", Integer.parseInt(getParam("socSuburusan")));
	      	String idJenistanah = "1";
	      	data.put("id_Jenistanah", Integer.parseInt(idJenistanah));
	      	//int idPegawai = Integer.parseInt("1");
	      	String NoFailKJP = "TIADA";

	      	//String NoFailLain = (String)data.get("NoFailLain");
	      	// guna fungsi di PK data.put("NoFailLain", getParam("txtNoFailLain"));
	      	data.put("no_Faillain", getParam("txtNoFailLain"));
	      	data.put("no_Failkjp", NoFailKJP);
		  
	      	//String TarikhBukaFail = (String)data.get("TarikhBukaFail");
	      	//String TBF = "to_date('" + TarikhBukaFail + "','dd/MM/yyyy')";
	      	data.put("id_Masuk",idmasuk);
	      	data.put("TarikhBukaFail", getParam("txdTarikhBukaFail"));

		  return FrmUtilData.simpanPermohonanHTP(data);
	  } 
	  
	  private void simpanHakmilik(HttpSession session,int i) throws Exception {
		  Hashtable h = new Hashtable();
			String strdate = "";
	    	strdate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
	    	//this.context.put("sekarang",strdate);
	   
		  if(i == 0){
			  //fail baru
				h.put("idPermohonan", Integer.parseInt(getParam("id_kemaskini")));
				h.put("idNegeri", getParam("idnegeri"));
				h.put("socDaerah", getParam("socDaerah1"));
				h.put("socMukim", getParam("socMukim"));
				h.put("socHakmilik", getParam("socHakmilik"));
				//h.put("socMukim", 1);
				//h.put("socJenisHakmilik", 1);
				h.put("NoHakmilik", getParam("txtnohakmilik"));
				h.put("NoLot", getParam("txtnolot"));
				h.put("socLot", getParam("noLot"));
				//h.put("socLot", 1);
				h.put("TarikhTerima", strdate);
				h.put("CukaiPertama", "0.00");
				h.put("Lokasi", "TIADA");
				h.put("socLuas", "1");
				h.put("NoPelan", "TIADA");
				h.put("socRizab", 1);
				h.put("socKategori", 1);
				h.put("Syarat", "TIADA");
				h.put("TarikhLuput", strdate);
				h.put("CukaiSemasa", "0.00");
				h.put("Luas", "0.00");
				h.put("TarikhWarta", strdate);
				h.put("NoWarta","TIADA");
				h.put("NoPU", "TIADA");
				h.put("NoSyit", "TIADA");
				h.put("Sekatan", "TIADA");
				idHakmilikUrusan = FrmPajakanKecilHakmilikData.simpan(h);
			}else{
				//kemaskini fail
//				int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
				h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
				h.put("idHakmilikurusan", Integer.parseInt(getParam("idHakmilikurusan")));
//				h.put("socNegeri", getParam("socNegeri"));
				h.put("socDaerah", getParam("socDaerah"));
				h.put("socMukim", getParam("socMukim"));
				h.put("socJenisHakmilik", getParam("socJenisHakmilik"));
				h.put("NoHakmilik", getParam("txtNoHakmilik"));
				h.put("NoLot", getParam("txtNoLot"));
				h.put("socLot", getParam("noLot"));
				h.put("TarikhTerima", getParam("txdTarikhTerima"));
				h.put("CukaiPertama", getParam("txtCukaiPertama"));
				h.put("Lokasi", getParam("txtLokasi"));
				h.put("socLuas", getParam("socLuas"));
				h.put("NoPelan", getParam("txtNoPelan"));
				h.put("socRizab", getParam("socRizab"));
				h.put("socKategori", getParam("socKategori"));
				h.put("Syarat", getParam("txtSyarat"));
				h.put("TarikhLuput", getParam("txdTarikhLuput"));
				h.put("CukaiSemasa", getParam("txtCukaiSemasa"));
				h.put("Luas", getParam("txtLuas"));
				h.put("TarikhWarta", getParam("txdTarikhWarta"));
				h.put("NoWarta", getParam("txtNoWarta"));
				h.put("NoPU", getParam("txtNoPU"));
				h.put("NoSyit", getParam("txtNoSyit"));
				h.put("Sekatan", getParam("txtSekatan"));
				FrmGadaianPenHamilikData.update(h);
			}
	  }
	  
	  private void simpanPemilik(HttpSession session,int i,Long l) throws Exception {
		  Hashtable h= new Hashtable();
			if(i==0){
				//fail baru
				//Hashtable h = new Hashtable();
				if(l!=0L)
					h.put("idHakmilikurusan", Integer.parseInt(""+l));
				else	
					h.put("idHakmilikurusan", Integer.parseInt(getParam("txtnohakmilik")));
				
				h.put("noRujukan", getParam("txtnorujukan"));
				h.put("nama", getParam("txtnama"));
				h.put("alamat1", getParam("txtalamat1"));
				h.put("alamat2", getParam("txtalamat2"));
				h.put("alamat3", getParam("txtalamat3"));
				h.put("poskod", getParam("txtposkod"));
				h.put("idDaerah", Integer.parseInt(getParam("socDaerah")));
				h.put("idNegeri", Integer.parseInt(getParam("idnegeri")));
				h.put("noTelefon", "TIADA");
				h.put("noFax", "TIADA");
				h.put("noPerserahan","TIADA");
				FrmPajakanKecilHakmilikData.simpanPemilik(h);
			}else{
				//kemaskini fail
				//Hashtable h = new Hashtable();
				h.put("idPihakberkepentingan", Integer.parseInt(getParam("idPihakberkepentingan")));
				h.put("nama", getParam("txtNama"));
				h.put("alamat1", getParam("txtAlamat1"));
				h.put("alamat2", getParam("txtAlamat2"));
				h.put("alamat3", getParam("txtAlamat3"));
				h.put("poskod", getParam("txtPoskod"));
				h.put("idDaerah", Integer.parseInt(getParam("socDaerah")));
				h.put("noTelefon", getParam("txtNoTelefon"));
				h.put("noFax", getParam("txtNoFax"));
				h.put("idBebanan", Integer.parseInt(getParam("idBebanan")));
				h.put("noPerserahan", getParam("txtNoPerserahan"));
				FrmGadaianHakmilikData.update(h);
			}
		  //FrmGadaianHakmilikData.simpan(data);

	  }
	  
      private String simpanPB(HttpSession session,int i,Long l) throws Exception {
          
          Hashtable h= new Hashtable();
                    if(i==0){
                                //fail baru
                                //Hashtable h = new Hashtable();
                                //if(l!=0L)
                                            h.put("idHakmilikurusan", Integer.parseInt(""+l));
                                //else      
                                  //          h.put("idHakmilikurusan", Integer.parseInt(getParam("fail")));
                                
                                h.put("noRujukan", getParam("txtnorujukan"));
                                h.put("nama", getParam("txtnama"));
                                h.put("alamat1", getParam("txtalamat1"));
                                h.put("alamat2", getParam("txtalamat2"));
                                h.put("alamat3", getParam("txtalamat3"));
                                h.put("poskod", getParam("txtposkod"));
                                
                                //modified by rosli 2009/12/28 socdaerah --> socDaerahalamat,idnegeri -->idNegerialamat
                                h.put("idDaerah", Integer.parseInt(getParam("socDaerahalamat")));
                                h.put("idNegeri", Integer.parseInt(getParam("idnegeri")));
                                h.put("noTelefon", "TIADA");
                                h.put("noFax", "TIADA");
                                h.put("noPerserahan","TIADA");
                                return FrmUtilData.simpanPB(h);
                    }else{
                                //kemaskini fail
                                //Hashtable h = new Hashtable();
                                h.put("idPihakberkepentingan", Integer.parseInt(getParam("idPihakberkepentingan")));
                                h.put("nama", getParam("txtNama"));
                                h.put("alamat1", getParam("txtAlamat1"));
                                h.put("alamat2", getParam("txtAlamat2"));
                                h.put("alamat3", getParam("txtAlamat3"));
                                h.put("poskod", getParam("txtPoskod"));
                                h.put("idDaerah", Integer.parseInt(getParam("socADaerah")));
                                h.put("noTelefon", getParam("txtNoTelefon"));
                                h.put("noFax", getParam("txtNoFax"));
                                h.put("idBebanan", Integer.parseInt(getParam("idBebanan")));
                                h.put("noPerserahan", getParam("txtNoPerserahan"));
                                h.put("uid", Integer.parseInt((String)session.getAttribute("_ekptg_user_id")));
                                return FrmUtilData.updatePB(h);
                    }
          //FrmGadaianHakmilikData.simpan(data);

}

	    public String getJarakBulan(Date dari,Date hingga) {
	        //RequestContext rc = RequestContext.getCurrentInstance();
	        //Date hingga = (Date)rc.getPageFlowScope().get("hingga");
	        //Date dari = (Date)this.getTxdDateDari().getValue();
	        Long diff, DayDur, MonthDur;
	            if(hingga != null && dari != null) {
	                diff = hingga.getTime() - dari.getTime();
	                DayDur = diff / (1000*60*60*24);
	                //MonthDur = (DayDur % 365) / 29; // baki daripada tahun dalam bentuk bulan
	                 MonthDur = DayDur / 29;
	                System.out.println("Duration in Month : "+MonthDur);
	            }else {
	                System.out.println("date hingga or dari is null on JarakBulan");
	                MonthDur = null;
	            }
	        return ""+MonthDur;
	    }	

}
