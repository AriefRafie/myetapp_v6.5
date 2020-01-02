package ekptg.view.htp;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.helpers.File;
import ekptg.helpers.HTML;
import ekptg.model.entities.Tblhtphakmilikbangunan;
import ekptg.model.htp.FrmGadaianHakmilikData;
import ekptg.model.htp.FrmGadaianPenHamilikData;
import ekptg.model.htp.FrmGadaianSemakanData;
import ekptg.model.htp.FrmPajakanKecilHakmilikData;
import ekptg.model.htp.FrmPajakanKecilMaklumatData;
import ekptg.model.htp.FrmPajakanKecilSenaraiPermohonanData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmSenaraiFailPajakanKecilData;
import ekptg.model.htp.FrmSewaanDerafData;

public class FrmPajakanKecil extends VTemplate{

	 public Template doTemplate()throws Exception {
		 
	    HttpSession session = this.request.getSession();
		Vector semakanSenarai = new Vector();
    	FrmSemakan frmSemak = null;
       	String disability = "disabled";
    	String readability = "";
	    String socAgensi = "";
	    String socKementerian = "";
	    String socNegeri = "";
	    String socUrusan = "";
	    String socSuburusan = "";
	    int pageMode = 0;
	    //String template_name = "app/htp/frmSenaraiFailPajakanKecil.jsp";
	    String template_name = "";
	    String submit = getParam("command");
	    System.out.println("FrmPajakanKecil:submit::"+submit);
	    // frmSewaanDeraf,frmSewaanInfo
	    this.context.put("util", new lebah.util.Util());

	    Vector senaraiFail = null;
	    String nofail = getParam("nofail");
	    senaraiFail = FrmSenaraiFailPajakanKecilData.getList(nofail);
	    this.context.put("senaraiList", senaraiFail); 
	    //senaraiFail = FrmSenaraiFailPajakanKecilData.getList("");
	    //this.context.put("senaraiList", senaraiFail);  

	    socNegeri = HTML.SelectNegeri("socNegeri");
	    
	    if ("pkfailbaru".equals(submit)) { //belum
	    	template_name = "app/htp/frmSewaanSemak.jsp";
	    	this.context.put("socSeksyen","3");

	    	socKementerian = HTML.SelectKementerian("socKementerian");
	    	socAgensi = HTML.SelectAgensi("socAgensi");
			socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("309"),"disabled");
	    	this.context.put("socUrusan",socUrusan);
	    	//perjanjian 44
	    	
	    	this.context.put("idsuburusan","44");
	    	this.context.put("idurusan","309");
	    	
			socSuburusan = HTML.SelectSuburusan("socSuburusan",Long.parseLong("44"),"disabled");
	    	this.context.put("socSuburusan",socSuburusan);
	    	String strdate = "";
	    	strdate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
	    	this.context.put("sekarang",strdate);
	    	Vector senaraiSemakan =null;
	    	senaraiSemakan = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh","aktif");
			this.context.put("senaraiSemakan",senaraiSemakan );
			pageMode = Integer.parseInt(getParam("pagemode"));
		    this.context.put("nofail", "");  

			if(pageMode==1){
				long idFail = ekptg.helpers.DB.getNextID("TBLPFDFAIL_SEQ");

				simpanFail(session,idFail);
				int idPermohonan=simpanPermohonan(session,0,Integer.parseInt(""+idFail));
				//simpanPermohonan
				//simpanHTPPermohonan
				//simpanStatusFail
				String[] cbsemaks = this.request.getParameterValues("cbsemaks");
	        	//FrmSemakan frmSemak = new FrmSemakan();
	        	//frmSemak.semakanHapusByPermohonan(id);
	        	if(cbsemaks!=null){
	        		for (int i = 0; i < cbsemaks.length; i++) { 
	        			frmSemak = new FrmSemakan();
	        			System.out.println("FrmPajakanKecilSenaraiPermohonan:semakankemaskini::cbsemaks:::"+cbsemaks[i]);
	        			frmSemak.semakanTambah(cbsemaks[i], id);           
	        		}
	        	} 	
			}

	    	
	    }else if ("pkfailbarusimpan".equals(submit)) { //belum
	    	
	    }else if("pkfailsearch".equals(submit)){ //belum
	    	//String nofail = getParam("nofail");
	    	template_name = "app/htp/frmSenaraiFailPajakanKecil.jsp";
		    senaraiFail = FrmSenaraiFailPajakanKecilData.getList(nofail);
		    this.context.put("senaraiList", senaraiFail);  

	    }else if("pkpermohonan".equals(submit)){
		    String fail = getParam("fail");
	    	Vector senaraiPermohonan = null;
	    	senaraiPermohonan = FrmPajakanKecilSenaraiPermohonanData.getList(fail);
	    	this.context.put("senaraiList", senaraiPermohonan);	    	
	    	this.context.put("idFail", fail);	    	
	    	template_name = "app/htp/frmPajakanKecilSenaraiPermohonan.jsp";	
	    }else if ("pkpermohonantambah".equals(submit)) { //belum
	    	template_name = "app/htp/frmSewaanSemak.jsp";	
			String id = getParam("id_kemaskini");
			//System.out.println("FrmPajakanKecil:pkpermohonantambah::id:::"+id);
			//FrmKemaskiniFailData.setData(Integer.parseInt(id));
			//Vector faildata = FrmKemaskiniFailData.getData();
        	//Hashtable h = (Hashtable) faildata.get(0);
			
			Hashtable h = null;
			h = FrmSenaraiFailPajakanKecilData.getFailInfo(id);
		    socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()),"disabled");
	    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("idKementerian").toString()),"disabled");
			socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("309"),"disabled");
	    	this.context.put("socUrusan",socUrusan);
	        System.out.println("FrmPajakanKecil:pkpermohonantambah::tarikhDaftar:::"+h.get("tarikhDaftar"));
	        String strdate = "";
	    	strdate = lebah.util.Util.getDateTime((Date)h.get("tarikhDaftar"), "dd/MM/yyyy");
	    	this.context.put("sekarang",strdate);
	
	    	String noFail = (String)h.get("noFail");
		    this.context.put("nofail", noFail);  

		    Vector permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanHTP(id);
        	Hashtable hpermohonan = (Hashtable) permohonan.get(0);
	    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(hpermohonan.get("idagensi").toString()),"disabled");

	    	this.context.put("idsuburusan","44");
	    	this.context.put("idurusan","309");
	    	this.context.put("idagensi",hpermohonan.get("idagensi"));
	 
			pageMode = 4;

		    //this.context.put("permohonanInfo", hpermohonan.get(""));
		    //this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());
		    // Senarai semakan
		    semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh");
		    this.context.put("senaraiSemakan",semakanSenarai );
			//System.out.println("FrmPajakanKecilSenaraiPermohonan:senaraiSemakan::"+FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh").size());
	    	
	    }else if("pkpermohonansearch".equals(submit)){ //belum
	    	template_name = "app/htp/frmSenaraiFailPajakanKecil.jsp";
  	
	    }else if ("pkpermohonankemaskini".equals(submit)) {
	    	template_name = "app/htp/frmSewaanSemakPermohonan.jsp";	
			String id = getParam("id_kemaskini");
			if(id==""){
				System.out.println("FrmPajakanKecil:pkpermohonankemaskini::simpan");
				int idPermohonan=simpanPermohonan(session,1,0);
				System.out.println("FrmPajakanKecil:pkpermohonankemaskini::idPermohonan:::"+idPermohonan);
				id = ""+idPermohonan;
			}
			
			Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
		    this.context.put("permohonanInfo", permohonan);
		    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());
		    
		    socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),"disabled");
	    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),"disabled");
	    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),"disabled");

	    	socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("309"),"disabled");
	    	this.context.put("socUrusan",socUrusan);
	 
		    
		    // Senarai semakan
		    semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh");
		    this.context.put("senaraiSemakan",semakanSenarai );
		    System.out.println("FrmPajakanKecilSenaraiPermohonan:senaraiSemakan::"+FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh").size());

		    //String[] cbsemaks = this.request.getParameterValues("cbsemaks");
				/*Hashtable map = new Hashtable();
			semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh");
		    System.out.println("FrmPajakanKecilSenaraiPermohonan:id::"+id);
		for (int i = 0; i < semakanSenarai.size(); ++i) { 
				Hashtable obj = new Hashtable();
	            obj = (Hashtable)semakanSenarai.elementAt(i);
			    System.out.println("FrmPajakanKecilSenaraiPermohonan:cbsemaks[i]::"+obj.get("id"));
				//semakanHantar = FrmSemakan.getSenaraiSemakanHantar(id);
				    System.out.println("FrmPajakanKecilSenaraiPermohonan:FrmSemakan.isSemakan(id, cbsemaks[i])::"+FrmSemakan.isSemakan(id,""+obj.get("id")));
					if(FrmSemakan.isSemakan(id, ""+obj.get("id")))
						map.put("status", "true");
					else
						map.put("status", "false");	
			}*/

	    }else if("semakankemaskini".equals(submit))    {
	    	template_name = "app/htp/frmSewaanSemakPermohonan.jsp";	
			String id = getParam("idkemaskini");
    	    System.out.println("FrmPajakanKecil:semakankemaskini::id:::"+id);
			String[] cbsemaks = this.request.getParameterValues("cbsemaks");
        	//FrmSemakan frmSemak = new FrmSemakan();
        	//frmSemak.semakanHapusByPermohonan(id);
        	if(cbsemaks!=null){
        		for (int i = 0; i < cbsemaks.length; i++) { 
        			frmSemak = new FrmSemakan();
        			System.out.println("FrmPajakanKecilSenaraiPermohonan:semakankemaskini::cbsemaks:::"+cbsemaks[i]);
        			frmSemak.semakanTambah(cbsemaks[i], id);           
        		}
        	} 	
        	Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
		    this.context.put("permohonanInfo", permohonan);
		    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());
		    
		    socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),"disabled");
	    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),"disabled");
	    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),"disabled");

	    	socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("309"),"disabled");
	    	this.context.put("socUrusan",socUrusan);
	
			this.context.put("semak", new ekptg.model.htp.FrmSemakan());
			this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh"));

	    } else if ("delete".equals(submit)) {
	      deleteCaraBayar(session);
	    }else if("semakanseterus".equals(submit)) {
		    readability = "readonly";
		    disability = "disabled";

	    	Hashtable hakmilikbangunan = null;
	    	String id = getParam("idkemaskini");
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

	    	template_name = "app/htp/frmSewaanPemilik.jsp";	
	    }else if("pemiliktambah".equals(submit)) {
		    String socDaerah = "";
		    //String socNegeri = "";
		    String socLuas = "";
		    String idHakmilik = "";
			String id = getParam("id_kemaskini");
			idHakmilik = getParam("fail");
			pageMode = 0;
			if(idHakmilik==""){
	    	    System.out.println("FrmPajakanKecil:pemiliktambah::idHakmilik:::"+idHakmilik);

			}else{
	    	    System.out.println("FrmPajakanKecil:pemiliktambah::idHakmilik:::"+idHakmilik);
				pageMode=2;
			}
	    	this.context.put("pagemode", pageMode);
			Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);

			socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
	    	this.context.put("idnegeri", permohonan.get("idnegeri"));
		    
	    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),"disabled");
	    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),"disabled");
		
		

			//	System.out.println("FrmPajakanKecil:hakmilikbangunan::"+hakmilikbangunan.get("iddaerah"));
			//	socLuas = HTML.SelectLuas("socLuas",Long.parseLong(hakmilikbangunan.get("idluas").toString()),disability);
			socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah");
			//	socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
			    template_name = "app/htp/frmSewaanPemilikBaru.jsp";
			    this.context.put("socDaerah",socDaerah);
	    	
	    }else if("pemiliksimpan".equals(submit)) {
		    String socDaerah = "";
		    String socLuas = "";
		    
			String id = getParam("id_kemaskini");
			Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
	    	simpanHakmilik(session,0);
	    	//simpanPemilik(session);
			socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
		

			//	System.out.println("FrmPajakanKecil:hakmilikbangunan::"+hakmilikbangunan.get("iddaerah"));
			//	socLuas = HTML.SelectLuas("socLuas",Long.parseLong(hakmilikbangunan.get("idluas").toString()),disability);
			//	socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah",Long.parseLong(hakmilikbangunan.get("iddaerah").toString()),disability);
			//	socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
			template_name = "app/htp/frmSewaanPemilikBaru.jsp";
	    	
	    }else if("pemilikseterus".equals(submit)) {
	    	Hashtable hakmilikbangunan = null;
		    readability = "readonly";
		    disability = "disabled";
		    String socDaerah = "";
		    //String socNegeri = "";
		    String socLuas = "";
		    
			String id = getParam("id_kemaskini");
			Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
	    	
			hakmilikbangunan = FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(id);
			this.context.put("hakmilikbangunaninfo", hakmilikbangunan);
			
			socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
		    this.context.put("socNegeri",socNegeri);


			if(hakmilikbangunan==null){
				socLuas = HTML.SelectLuas("socLuas");
				socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah");
		    
				template_name = "app/htp/frmSewaanMaklumatBaru.jsp";
			}else{
				System.out.println("FrmPajakanKecil:hakmilikbangunan::"+hakmilikbangunan.get("iddaerah"));
				socLuas = HTML.SelectLuas("socLuas",Long.parseLong(hakmilikbangunan.get("idluas").toString()),disability);
				socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah",Long.parseLong(hakmilikbangunan.get("iddaerah").toString()),disability);
				socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
			    template_name = "app/htp/frmSewaanMaklumat.jsp";
			}
		    this.context.put("socLuas",socLuas);
		    this.context.put("socDaerah",socDaerah);
    System.out.println("FrmPajakanKecil:FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(id)::"+FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(id));
	    
	    }else if("maklumatsimpan".equals(submit)){
			template_name = "app/htp/frmSewaanMaklumat.jsp";

			Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(getParam("id_kemaskini"));

		    readability = "readonly";
		    disability = "disabled";
		    String socDaerah = "";
		    socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah");
		    this.context.put("socDaerah",socDaerah);
		    //String socNegeri = "";
		    socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);

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
	    	
		    Hashtable hakmilikbangunan = null;
			String id = getParam("id_kemaskini");
	    	hakmilikbangunan = FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(id);
			this.context.put("hakmilikbangunaninfo", hakmilikbangunan);
			

			//System.out.println("FrmPajakanKecil:hakmilikbangunan::"+hakmilikbangunan.get("sewabulanan"));

	
	    }else if("maklumatseterus".equals(submit)) {
			String id = getParam("id_kemaskini");

	    	semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemak1");
	    	this.context.put("senaraiSemakan",semakanSenarai );
	
	    	template_name = "app/htp/frmSewaanSemakPKP.jsp";	
	    }else if("semakanPKPkemaskini".equals(submit))    {
	    	template_name = "app/htp/frmSewaanSemakPKP.jsp";	
			String id = getParam("idkemaskini");
			String[] cbsemaks = this.request.getParameterValues("cbsemaks");
    	    //System.out.println("FrmPajakanKecilSenaraiPermohonan:id::"+id);
        	//FrmSemakan frmSemak = new FrmSemakan();
			//VectorgetSenaraiSemakanHantar
        	//frmSemak.semakanHapusByPermohonan(id);
        	if(cbsemaks!=null){
        		for (int i = 0; i < cbsemaks.length; i++) { 
        			frmSemak = new FrmSemakan();
        			System.out.println("FrmPajakanKecilSenaraiPermohonan:semakankemaskini::cbsemaks:::"+cbsemaks[i]);
        			frmSemak.semakanTambah(cbsemaks[i], id);           
        		}
        	} 	
			this.context.put("semak", new ekptg.model.htp.FrmSemakan());
			this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh"));

	    } else if("semakanpkpseterus".equals(submit)) {
	    	Vector senarais = null;
	    	String id = getParam("id_kemaskini");
	    	senarais = FrmSewaanDerafData.getSenarai(id);
		    //this.context.put("utilderaf", new lebah.util.Util());
		    //System.out.println("FrmPajakanKecil:senarais::"+senarais.size());
  	
   		    this.context.put("senaraideraf",senarais );
   	
	    	template_name = "app/htp/frmSewaanDeraf.jsp";	
	    }else if("derafseterus".equals(submit)){
	    	template_name = "app/htp/frmPajakanKecilSenaraiSurat.jsp";
  	
	    }else if("pkfail".equals(submit)){
	    	template_name = "app/htp/frmSenaraiFailPajakanKecil.jsp";
	    	//System.out.printf("pkfail:"+submit);
	    }else{
	    	template_name = "app/htp/frmSenaraiFailPajakanKecil.jsp";
    	
	    }
	    //guna utk Pendaftaran baru
	    this.context.put("socNegeri",socNegeri);
	    
	    this.context.put("socKementerian",socKementerian);

	    this.context.put("socAgensi",socAgensi);

	    this.context.put("pageMode",pageMode);
	    
	    Template template = this.engine.getTemplate(template_name);
	    return template;
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
		  
		  /*System.out.println("FrmPajakanKecil:maklumatsimpan1::"+getParam("idurusan"));
		  System.out.println("FrmPajakanKecil:maklumatsimpan2::"+getParam("socKementerian"));
		  System.out.println("FrmPajakanKecil:maklumatsimpan3::"+getParam("socSeksyen"));
		  System.out.println("FrmPajakanKecil:maklumatsimpan4::"+getParam("socSuburusan"));
		  System.out.println("FrmPajakanKecil:maklumatsimpan5::"+getParam("txttajuk"));
		  System.out.println("FrmPajakanKecil:maklumatsimpan9::"+getParam("socNegeri"));
		  */
		  idNegeri = Integer.parseInt(getParam("socNegeri"));
		  idUrusan = Integer.parseInt(getParam("idurusan"));
		  idKementerian = Integer.parseInt(getParam("socKementerian"));
		  
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
		  h.put("id_Masuk", 1);
		  //h.put("id_Lokasifail", "TIADA");
		  //h.put("id_Faharasat", "TIADA");
		  FrmSenaraiFailPajakanKecilData.janaFail(h);
		  /**/System.out.println("FrmPajakanKecil:simpanFail::noFail:::"+noFail);

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
	  
	  private int simpanPermohonan(HttpSession session,int i,int fail)throws Exception {
		  Hashtable data = null;
		  data = new Hashtable();
	
		  	//long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
	      //int idFail = (Integer)data.get("IdFail");
	      if(i==0)
	    	  data.put("IdFail", fail);
    	  else
	    	  data.put("IdFail", Integer.parseInt(getParam("fail")));
	      	//int idJKPTG = Integer.parseInt("1");
	      	//String noPermohonan = "TIADA";
	      	//String noPerserahan = "TIADA";
	      //String TarikhSurKJP = (String)data.get("TarikhSurKJP");
	      //String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";
	      data.put("TarikhSurKJP", getParam("txdTarikhSuratKJP"));
	      //String tujuan = (String)data.get("Tajuk");
	      data.put("Tajuk", getParam("txttajuk"));
	      	//long idHtppermohonan = DB.getNextID("TBLHTPPERMOHONAN_SEQ");
	      //int idAgensi = Integer.parseInt(data.get("socAgensi").toString());
	      if(i==0)
	    	  data.put("socAgensi", getParam("socAgensi"));
	      else
	    	  data.put("socAgensi", getParam("idagensi"));
	      //int idSuburusan = (Integer)data.get("idSuburusan");
	      data.put("idSuburusan", Integer.parseInt(getParam("socSuburusan")));
	      	//String idJenistanah = "1";
	      	//int idPegawai = Integer.parseInt("1");
	      //String NoFailLain = (String)data.get("NoFailLain");
	      data.put("NoFailLain", getParam("txtNoFailLain"));
		  
	      //String TarikhBukaFail = (String)data.get("TarikhBukaFail");
	      //String TBF = "to_date('" + TarikhBukaFail + "','dd/MM/yyyy')";
	      data.put("TarikhBukaFail", getParam("txdTarikhBukaFail"));

		  return Integer.parseInt(FrmGadaianSemakanData.simpan(data));
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
				h.put("socDaerah", getParam("socDaerah"));
				//h.put("socMukim", getParam("socMukim"));
				//h.put("socJenisHakmilik", getParam("socJenisHakmilik"));
				h.put("socMukim", 1);
				h.put("socJenisHakmilik", 1);
				h.put("NoHakmilik", getParam("txtnohakmilik"));
				h.put("NoLot", getParam("txtnolot"));
				//h.put("socLot", getParam("socLot"));
				h.put("socLot", 1);
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
				FrmGadaianPenHamilikData.simpan(h);
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
				h.put("socLot", getParam("socLot"));
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
	  
	  private void simpanPemilik(HttpSession session) throws Exception {
		  Hashtable data= new Hashtable();
		  FrmGadaianHakmilikData.simpan(data);

	  }
	

}
