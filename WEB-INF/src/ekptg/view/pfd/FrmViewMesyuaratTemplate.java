package ekptg.view.pfd;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.pfd.FrmModelMesyuarat;
import ekptg.model.pfd.FrmModelMesyuaratTemplate;


@SuppressWarnings({ "serial", "unused" })
public class FrmViewMesyuaratTemplate extends AjaxBasedModule{

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    String Mesyuarat_NoRujukanMesyuarat = "";
    String Mesyuarat_Bil = "";
    String Mesyuarat_Tajuk = "";
    String Mesyuarat_Kategori = "";
    String Mesyuarat_Tarikh = "";
    
    String Mesyuarat_Lokasi = "";
    String Mesyuarat_Status = "";
    String Mesyuarat_Seksyen = "";
    String Mesyuarat_Negeri = "";
    String Mesyuarat_Unit = "";
    String Mesyuarat_NoFail = "";
    String Mesyuarat_DisediakanOleh = "";
    String Mesyuarat_DisemakOleh = "";
    String Mesyuarat_DisahkanOleh = "";
    String Mesyuarat_Catatan = "";
    String Mesyuarat_ValueDari = "";
    String Mesyuarat_ValueHingga = "";
    String Mesyuarat_ValueLokasi = "";
    String Mesyuarat_ValueSeksyen = "";
    String Mesyuarat_ValueNegeri = "";
    String Mesyuarat_ValueUnit = "";
    String Mesyuarat_ValueStatus = "";
    String Mesyuarat_Masa = "";
    String Mesyuarat_Dari = "";
    String Mesyuarat_Hingga = "";
    
    String Ahli_Kategori = "";
    String Ahli_Nama = "";
    String Ahli_Negeri = "";
    String Ahli_Seksyen = "";
    String Ahli_Unit = "";
    String Ahli_Pegawai = "";
    String Ahli_Agensi = "";
    String Ahli_Jawatan = "";
    String Ahli_Email = "";
    String Ahli_NoTelefon = "";
    String Ahli_NoFaks = "";
    String Ahli_Pengerusi = "";
    String Ahli_Peranan = "";
    String AlamatAhli = "";
    String id_unit = "";
    
    String SOC_LOKASI = "SOC_LOKASI";
    String SOC_STATUS = "SOC_STATUS";
    String SOC_SEKSYEN = "SOC_SEKSYEN";
    String SOC_NEGERI = "Ahli_Negeri";
    String SOC_UNIT = "SOC_UNIT";
    String SOC_PEGAWAI = "SOC_PEGAWAI";
    String SOC_AGENDA = "SOC_AGENDA";
    String SOC_MINIT = "SOC_MINIT";
    String SOC_PERANAN = "SOC_PERANAN";
    
    
    
    @SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception{
		
		String vm = "";
		Vector listNegeri = null;
		Vector listUnit = null;
		Vector getUnitByNegeri = null;

        HttpSession session = this.request.getSession();
        
        FrmModelMesyuaratTemplate modelMesyuarat = new FrmModelMesyuaratTemplate();
        
        // action: agenda, minit, subminit
        String action = getParam("action");
        
        // mode: view, update, save, delete
        String mode = getParam("mode");
        
        // submit: used by changeSOC
        String submit = getParam("command");
        
        String user_name = (String)session.getAttribute("_portal_username");
        String user_id = (String)session.getAttribute("_ekptg_user_id");
        String user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
        String portal_role = (String)session.getAttribute("_portal_role");
        String myrole = (String)session.getAttribute("myrole");
        
        this.context.put("check_pengerusi","");
		this.context.put("check_setiausaha","");
		this.context.put("user_negeri",user_negeri);

        view_Seksyen(session);
        Vector paparSeksyen = new Vector();
        paparSeksyen = FrmModelMesyuarat.getDataSeksyen();
        Hashtable hA = (Hashtable) paparSeksyen.get(0);
        String id_seksyen = String.valueOf(hA.get("id_seksyen"));
        String id_negeri = String.valueOf(hA.get("id_negeri"));
        
        String selectedTab = getParam("selectedTab");
        if ("".equalsIgnoreCase(selectedTab)) {
        	selectedTab = "0";
        } else if (!"0".equalsIgnoreCase(selectedTab) && !"1".equalsIgnoreCase(selectedTab) && !"2".equalsIgnoreCase(selectedTab) && !"3".equalsIgnoreCase(selectedTab)) {
        	selectedTab = "0";
        }
        
        Boolean Page_Carian = false;
        
        String ID_MESYUARAT = getParam("ID_MESYUARAT");
        String ID_TEMPLATE = getParam("ID_TEMPLATE");
        String ID_AHLI = getParam("ID_AHLI");
        String ID_AGENDA = getParam("ID_AGENDA");
        String ID_MINIT = getParam("ID_MINIT");
        String ID_SUBMINIT = getParam("ID_SUBMINIT");
        
        String PrevPage = getParam("PrevPage");
        if ("".equalsIgnoreCase(PrevPage)) {
        	PrevPage = "1";
        }
          
        Mesyuarat_NoRujukanMesyuarat = (getParam("Mesyuarat_NoRujukanMesyuarat")).trim();
        Mesyuarat_Bil = getParam("Mesyuarat_Bil");
        Mesyuarat_Tajuk = getParam("Mesyuarat_Tajuk");
        Mesyuarat_Kategori = getParam("Mesyuarat_Kategori");
        Mesyuarat_Tarikh = getParam("Mesyuarat_Tarikh");
        //Mesyuarat_Dari = getParam("Mesyuarat_Dari");
        //Mesyuarat_Hingga = getParam("Mesyuarat_Hingga");
        Mesyuarat_Lokasi = getParam("Mesyuarat_Lokasi");
        Mesyuarat_Status = getParam("Mesyuarat_Status");
        Mesyuarat_Seksyen = getParam("Mesyuarat_Seksyen");
        Mesyuarat_Negeri = getParam("Mesyuarat_Negeri");
        Mesyuarat_Unit = getParam("Mesyuarat_Unit");
        Mesyuarat_NoFail = getParam("Mesyuarat_NoFail");
        Mesyuarat_DisediakanOleh = getParam("Mesyuarat_DisediakanOleh");
        Mesyuarat_DisemakOleh = getParam("Mesyuarat_DisemakOleh");
        Mesyuarat_DisahkanOleh = getParam("Mesyuarat_DisahkanOleh");
        Mesyuarat_Catatan = getParam("Mesyuarat_Catatan");
        
        Ahli_Kategori = getParam("Ahli_Kategori");
        Ahli_Nama = getParam("Ahli_Nama");
        Ahli_Negeri = getParam("Ahli_Negeri");
        Ahli_Seksyen = getParam("Ahli_Seksyen");
        Ahli_Unit = getParam("Ahli_Unit");
        Ahli_Pegawai = getParam("Ahli_Pegawai");
        Ahli_Agensi = getParam("Ahli_Agensi");
        Ahli_Jawatan = getParam("Ahli_Jawatan");
        Ahli_Email = getParam("Ahli_Email");
        Ahli_Pengerusi = getParam("Ahli_Pengerusi");
        Ahli_Peranan = getParam("Ahli_Peranan");
        
        
        System.out.println("Ahli_Pegawai Atas:"+Ahli_Pegawai);
        
        
        
        String Flag_failWujud = "false";
    	if (Mesyuarat_NoRujukanMesyuarat != "" ){
    		Boolean failwujud = modelMesyuarat.checkfailwujud(Mesyuarat_NoRujukanMesyuarat);	
    			if (failwujud == false){
    				//return default action 
    				action = "";
    		        Flag_failWujud = "true";
    		}
    	}
        if ("".equalsIgnoreCase(Ahli_Kategori)) {
        	Ahli_Kategori = "1";
        }
        
        String Ahli_Kategori1 = "";
        String Ahli_Kategori2 = "";
        String Ahli_SelPengerusi = "";
        String Ahli_SelTimbPengerusi = "";
        String Ahli_SelSetiausaha = "";
        String Ahli_DisableSelPengerusi = "";
        String Ahli_DisableSelTimbPengerusi = "";
        String Ahli_DisableSelSetiausaha = "";
        
        String RO_MaklumatMesyuarat = "",RO_NoFail = "readonly=\"readonly\"";
        
       // SOC_LOKASI = getParam("SOC_LOKASI");
        SOC_STATUS = getParam("SOC_STATUS");
        SOC_SEKSYEN = getParam("SOC_SEKSYEN");
        SOC_NEGERI = getParam("SOC_NEGERI");
        SOC_UNIT = getParam("SOC_UNIT");
        SOC_PEGAWAI = getParam("SOC_PEGAWAI");
        SOC_AGENDA = getParam("SOC_AGENDA");
        SOC_MINIT = getParam("SOC_MINIT");
        SOC_PERANAN = getParam("SOC_PERANAN");
        
    	Vector vData = new Vector();
    	Vector vList = new Vector();
    	Hashtable h = new Hashtable();
    	
    	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiByNegeri(ID_MESYUARAT,"SOC_PEGAWAI", "", SOC_NEGERI, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai1');\"",submit,action);
	       
        
        if ("daftarMesyuarat".equalsIgnoreCase(action)) {
        	vm = "app/pfd/frmMesyuaratTemplateDaftar.jsp";
        	PrevPage = "1";
        	
        	if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
            	// kalau ada ID, amik data existing
        		if("16".equalsIgnoreCase(user_negeri)){
        			vData = modelMesyuarat.getMesyuaratTemplateData(ID_MESYUARAT);
        		}
        		else{
        			vData = modelMesyuarat.getMesyuaratTemplateDataNegeri(ID_MESYUARAT);
        		}
        	//	vData = modelMesyuarat.getMesyuaratTemplateData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        	} else {
        		RO_NoFail = "";
        	}
        } else if ("daftarAhli".equalsIgnoreCase(action)) {
    		// initial VM value
    		vm = "app/pfd/frmMesyuaratTemplateDaftar.jsp";
    		
    		Boolean canProceed = false;
    		
        	if ("1".equalsIgnoreCase(PrevPage)) {
            	// kalau datang dari page daftar, save dulu

        	//	ID_MESYUARAT = modelMesyuarat.saveMesyuaratTemplateData(ID_MESYUARAT,Mesyuarat_NoRujukanMesyuarat, Mesyuarat_Bil, Mesyuarat_Tajuk, Mesyuarat_Tarikh, Mesyuarat_Dari, Mesyuarat_Hingga, SOC_LOKASI, "1", SOC_SEKSYEN, Mesyuarat_NoFail, Mesyuarat_DisediakanOleh, Mesyuarat_DisemakOleh, Mesyuarat_DisahkanOleh, Mesyuarat_Catatan, "2");
        		if("16".equalsIgnoreCase(user_negeri)){
        			ID_MESYUARAT = modelMesyuarat.saveMesyuaratTemplateData(ID_MESYUARAT, Mesyuarat_NoRujukanMesyuarat, Mesyuarat_Bil, Mesyuarat_Tajuk, Mesyuarat_Kategori, Mesyuarat_Tarikh, Mesyuarat_Dari, Mesyuarat_Hingga, SOC_LOKASI, "1", SOC_SEKSYEN, Mesyuarat_NoFail, Mesyuarat_DisediakanOleh, Mesyuarat_DisemakOleh, Mesyuarat_DisahkanOleh, Mesyuarat_Catatan, "2", user_id);

        		}
        		else{
        			ID_MESYUARAT = modelMesyuarat.saveMesyuaratTemplateDataNegeri(ID_MESYUARAT, Mesyuarat_NoRujukanMesyuarat, Mesyuarat_Bil, Mesyuarat_Tajuk, Mesyuarat_Kategori, Mesyuarat_Tarikh, Mesyuarat_Dari, Mesyuarat_Hingga, SOC_LOKASI, "1", (String) session.getAttribute("_ekptg_user_negeri"), SOC_UNIT, Mesyuarat_NoFail, Mesyuarat_DisediakanOleh, Mesyuarat_DisemakOleh, Mesyuarat_DisahkanOleh, Mesyuarat_Catatan, "2", user_id);

        		}
        		
        		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
        			canProceed = true;
        		}
        	} else if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
        		// terus buat tambah ahli
        		canProceed = true;
        	}
        	
        	if (canProceed) {
    			// success save, boleh proceed dgn tambah ahli
    			vm = "app/pfd/frmMesyuaratTemplateAhli.jsp";
    			
    			if("16".equalsIgnoreCase(user_negeri)){
    				vData = modelMesyuarat.getMesyuaratTemplateData(ID_MESYUARAT);	
    			}
    			else{
    				vData = modelMesyuarat.getMesyuaratTemplateDataNegeri(ID_MESYUARAT);	
    			}
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		//Mesyuarat_Unit = Mesyuarat_Unit;
        		//Mesyuarat_Unit = Mesyuarat_ValueUnit;
        		h.clear();
        		vData.clear();
        		
        		
        		
        		vList = modelMesyuarat.getAhliMesyuaratTemplateList(ID_MESYUARAT);
        		context.put("List_CarianAhliMesyuarat", vList);
        		Ahli_Kategori1 = "checked=\"checked\"";
        		
        		Ahli_Nama = "";
    			Ahli_Agensi = "";
    			Ahli_Jawatan = "";
    			Ahli_Email = "";
    			Ahli_NoTelefon = "";
    			Ahli_NoFaks = "";
    			Ahli_Negeri = "";
    			Ahli_Seksyen = "";
    			Ahli_Unit = "";
    			Ahli_Peranan = "";
    			ID_AHLI = "";
        	}
        	
        } else if ("tambahAhli".equalsIgnoreCase(action)) {
    		vm = "app/pfd/frmMesyuaratTemplateDaftar.jsp";
    		
    		context.put("Ahli_Pegawai", modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", "", Ahli_Seksyen, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai');\"",action) );
	    	
    		
    		
    	
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
    			vm = "app/pfd/frmMesyuaratTemplateAhli.jsp";
    			
    			if("16".equalsIgnoreCase(user_negeri)){
    				vData = modelMesyuarat.getMesyuaratTemplateData(ID_MESYUARAT);	
    			}
    			else{
    				vData = modelMesyuarat.getMesyuaratTemplateDataNegeri(ID_MESYUARAT);	
    			}
        		//vData = modelMesyuarat.getMesyuaratTemplateData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		//Mesyuarat_Unit = Mesyuarat_Unit;
        		//Mesyuarat_Unit = Mesyuarat_ValueUnit;
        		h.clear();
        		vData.clear();
        		
        		Boolean doSave = false;
        		
        		// utk pengerusi & setiauasaha, kena check peranan ni dah ada orang ke belum. kalau takde, baru boleh save
        		if ("1".equalsIgnoreCase(SOC_PERANAN)) {
        			if (!modelMesyuarat.havePengerusi(ID_MESYUARAT, ID_AHLI)) {
        				doSave = true;
        				this.context.put("check_pengerusi","yes");
        			}
        			else
        			{
        				this.context.put("check_pengerusi","no");
        			}
        		}else if ("2".equalsIgnoreCase(SOC_PERANAN)){
        			doSave = true;
        		} else if ("3".equalsIgnoreCase(SOC_PERANAN)) {
        			if (!modelMesyuarat.haveSetiausaha(ID_MESYUARAT, ID_AHLI)) {
        				doSave = true;
        				this.context.put("check_setiausaha","yes");
        			}
        			else
        			{
        				this.context.put("check_setiausaha","no");
        			}

        		}else if ("4".equalsIgnoreCase(SOC_PERANAN)){
        			doSave = true;
    			}else if ("8".equalsIgnoreCase(SOC_PERANAN)){
    			doSave = true;
    			}
        		
        		String idKategori = getParam("Ahli_Kategori");
        		
        		if(idKategori.equals("2")){	
        			doSave = true;
        		}

        		if (doSave) {
	        		// save data ahli ni
	    			modelMesyuarat.saveAhliMesyuaratTemplateData(ID_MESYUARAT, ID_AHLI, Ahli_Kategori, SOC_PEGAWAI, Ahli_Nama, Ahli_Agensi, "", SOC_NEGERI, SOC_SEKSYEN, SOC_UNIT, Ahli_Jawatan, Ahli_Email, getParam("Ahli_NoTelefon"), getParam("Ahli_NoFaks"), SOC_PERANAN);
	    			
	    			// kosongkan fields
	        		vList = modelMesyuarat.getAhliMesyuaratTemplateList(ID_MESYUARAT);
	        		context.put("List_CarianAhliMesyuarat", vList);
	        		Ahli_Kategori = "1";
	    			Ahli_Kategori1 = "checked=\"checked\"";
	    			Ahli_Nama = "";
	    			Ahli_Agensi = "";
	    			Ahli_Jawatan = "";
	    			Ahli_Email = "";
	    			Ahli_Negeri = "";
	    			Ahli_Seksyen = "";
	    			Ahli_Unit = "";
	    			Ahli_Peranan = "";
	    			  Ahli_NoTelefon = "";
	    			     Ahli_NoFaks = "";
	    			//Ahli_Pegawai = "";
	    			action = "daftarAhli";
        		} else {
        			//action = "viewAhli";
	        		vList = modelMesyuarat.getAhliMesyuaratTemplateList(ID_MESYUARAT);
	        		context.put("List_CarianAhliMesyuarat", vList);
	        		Ahli_Kategori = "1";
	    			Ahli_Kategori1 = "checked=\"checked\"";
	    			Ahli_Nama = "";
	    			Ahli_Agensi = "";
	    			Ahli_Jawatan = "";
	    			Ahli_Email = "";
	    			Ahli_Negeri = "";
	    			Ahli_Seksyen = "";
	    			Ahli_Unit = "";
	    			Ahli_Peranan = "";
	    			  Ahli_NoTelefon = "";
	    			     Ahli_NoFaks = "";
	    			//Ahli_Pegawai = "";
	    			action = "daftarAhli";
        			
        		}
    		}
    		Ahli_Kategori = "1";
			Ahli_Kategori1 = "checked=\"checked\"";
			Ahli_Nama = "";
			Ahli_Agensi = "";
			Ahli_Jawatan = "";
			Ahli_Email = "";
			Ahli_Negeri = "";
			Ahli_Seksyen = "";
			Ahli_Unit = "";
			Ahli_Peranan = "";
			  Ahli_NoTelefon = "";
			     Ahli_NoFaks = "";
        } 
        else if ("viewAhli".equalsIgnoreCase(action)) {
    		vm = "app/pfd/frmMesyuaratTemplateDaftar.jsp";
    		String jenis_kategori = "";
    		if (!"".equalsIgnoreCase(ID_MESYUARAT) && !"".equalsIgnoreCase(ID_AHLI)) {
    			vm = "app/pfd/frmMesyuaratTemplateAhli.jsp";
    			
    			Vector getAhliMesyuaratDataUrussetia = new Vector();

    			Vector kat = modelMesyuarat.getMaklumatKategori(ID_AHLI);
        		if (!kat.isEmpty()) {
        			h = (Hashtable) kat.get(0);
        			jenis_kategori = (String) h.get("KATEGORI_AHLI");
        		}
        		String negeri_user = "";
        		//String user_id = "";
    			
    			
    			if("16".equalsIgnoreCase(user_negeri)){
    				vData = modelMesyuarat.getMesyuaratTemplateData(ID_MESYUARAT);	
    			}
    			else{
    				vData = modelMesyuarat.getMesyuaratTemplateDataNegeri(ID_MESYUARAT);	
    			}
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		if(jenis_kategori.equals("1") )
        		{
        		
        		vData = modelMesyuarat.getAhliMesyuaratTemplateData(ID_MESYUARAT, ID_AHLI);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			negeri_user = (String) h.get("NEGERI_USER");
        			user_id = (String) h.get("USER_ID");
        			Ahli_Kategori = (String) h.get("KATEGORI_AHLI");
        			Ahli_Nama = (String) h.get("NAMA_AHLI");
        			Ahli_Agensi = (String) h.get("NAMA_AGENSI");
        			Ahli_Jawatan = (String) h.get("JAWATAN");
        			Ahli_Email = (String) h.get("EMAIL");
        			Ahli_Pegawai = (String) h.get("ID_PEGAWAI");
        			Ahli_Negeri = (String) h.get("ID_NEGERI");
        			Ahli_Seksyen = (String) h.get("ID_SEKSYEN");
        			Ahli_Unit = (String) h.get("ID_UNIT");
        			Ahli_Peranan = (String) h.get("ID_PERANAN");
        			Ahli_NoTelefon = (String) h.get("NO_TEL");
        			Ahli_NoFaks = (String) h.get("NO_FAKS");
        			
        			System.out.println("XXX negeri_user :"+negeri_user);
        			System.out.println("XXX user_id :"+user_id);

        			if ("1".equalsIgnoreCase(Ahli_Kategori)) {
        				Ahli_Kategori1 = "checked=\"checked\"";
        				Ahli_Kategori2 = "";
        				if ("16".equalsIgnoreCase(negeri_user)) {
        		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", user_id, SOC_SEKSYEN, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai');\"",submit,action);
        		        	System.out.println("SEKSYEN Pegawai:"+Ahli_Pegawai);
        		        	context.put("Ahli_Pegawai", Ahli_Pegawai);
                		}		        
        		        else  {		        	
        		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiByNegeri(ID_MESYUARAT,"SOC_PEGAWAI", user_id, negeri_user, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai1');\"",submit,action);
        		        	System.out.println("NEGERI Pegawai:"+Ahli_Pegawai);
        		        	context.put("Ahli_Pegawai", Ahli_Pegawai);
        		        }
        			} else {
        				Ahli_Kategori1 = "";
        				Ahli_Kategori2 = "checked=\"checked\"";
        			}
        		}
          		h.clear();
        		vData.clear();
        		
        		
    		}
        		
        		else  if(jenis_kategori.equals("2"))       		
        		{
        			System.out.println("MASUK");
        		getAhliMesyuaratDataUrussetia = modelMesyuarat.getAhliMesyuaratDataUrussetia(ID_MESYUARAT, ID_AHLI);
        		if (!getAhliMesyuaratDataUrussetia.isEmpty()) {
        			h = (Hashtable) getAhliMesyuaratDataUrussetia.get(0);
        			negeri_user = (String) h.get("NEGERI_USER");
        			user_id = (String) h.get("USER_ID");
        			Ahli_Kategori = (String) h.get("KATEGORI_AHLI");
        			Ahli_Nama = (String) h.get("NAMA_AHLI");
        			Ahli_Agensi = (String) h.get("NAMA_AGENSI");
        			Ahli_Jawatan = (String) h.get("JAWATAN");
        			Ahli_Email = (String) h.get("EMAIL");
        			Ahli_NoTelefon = (String) h.get("NO_TEL");
        			Ahli_NoFaks = (String) h.get("NO_FAKS");
        			Ahli_Pegawai = (String) h.get("ID_PEGAWAI");
        			Ahli_Negeri = (String) h.get("ID_NEGERI");
        			Ahli_Seksyen = (String) h.get("ID_SEKSYEN");
        			Ahli_Unit = (String) h.get("ID_UNIT");
        			Ahli_Peranan = (String) h.get("ID_PERANAN");
        		  //  AlamatAhli = (String) h.get("ALAMAT_AHLI");
        			
        			h.clear();
        			getAhliMesyuaratDataUrussetia.clear();
        			
        			//System.out.println("CHECK Ahli_Kategori :"+Ahli_Kategori);
        		}
        		}

        		
        		if ("1".equalsIgnoreCase(jenis_kategori)) {
    				Ahli_Kategori = "1";
    				Ahli_Kategori1 = "checked=\"checked\"";
    				Ahli_Kategori2 = "";
    			
    				if ("16".equalsIgnoreCase(negeri_user)) {
    		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", user_id, SOC_SEKSYEN, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai');\"",submit,action);
    		        	context.put("Ahli_Pegawai", Ahli_Pegawai);
            		}		        
    		        else  {		        	
    		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiByNegeri(ID_MESYUARAT,"SOC_PEGAWAI", user_id, negeri_user, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai1');\"",submit,action);
    		        	context.put("Ahli_Pegawai", Ahli_Pegawai);
    		        }
    			} else if ("2".equalsIgnoreCase(jenis_kategori)) {
    				Ahli_Kategori = "2";
    				Ahli_Kategori1 = "";
    				Ahli_Kategori2 = "checked=\"checked\"";
    				
    			}
        		vList = modelMesyuarat.getAhliMesyuaratTemplateList(ID_MESYUARAT);
        		context.put("List_CarianAhliMesyuarat", vList);
        		
        		
        		
        		
    		}
        } 
        /*
        else if ("viewAhli".equalsIgnoreCase(action)) {
    		vm = "app/pfd/frmMesyuaratTemplateDaftar.jsp";
    		
    		System.out.println("VIEW AHLI ID_MESYUARAT :"+ID_MESYUARAT);
    		System.out.println("VIEW AHLI ID_AHLI :"+ID_AHLI);
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT) && !"".equalsIgnoreCase(ID_AHLI)) {
    			vm = "app/pfd/frmMesyuaratTemplateAhli.jsp";

        		vData = modelMesyuarat.getAhliMesyuaratTemplateData(ID_MESYUARAT,ID_AHLI);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		String negeri_user = "";
        		String id_user = "";
        		String jenis_kategori = "";
        		
        		Vector getAhliMesyuaratDataUrussetia = new Vector();
        		Vector kat = modelMesyuarat.getMaklumatKategori(ID_AHLI);
        		if (!kat.isEmpty()) {
        			h = (Hashtable) kat.get(0);
        			jenis_kategori = (String) h.get("KATEGORI_AHLI");
        		}
        		
        		System.out.println("CHECK Ahli_Kategori :"+jenis_kategori);
        		
        		if(jenis_kategori.equals("1")||jenis_kategori.equals("3"))
        		{
        		vData = modelMesyuarat.getAhliMesyuaratTemplateData(ID_MESYUARAT, ID_AHLI);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			negeri_user = (String) h.get("NEGERI_USER");
        			id_user = (String) h.get("USER_ID");
        			Ahli_Kategori = (String) h.get("KATEGORI_AHLI");
        			Ahli_Nama = (String) h.get("NAMA_AHLI");
        			Ahli_Agensi = (String) h.get("NAMA_AGENSI");
        			Ahli_Jawatan = (String) h.get("JAWATAN");
        			Ahli_Email = (String) h.get("EMAIL");
        			Ahli_NoTelefon = (String) h.get("NO_TEL");
        			Ahli_NoFaks = (String) h.get("NO_FAKS");
        			Ahli_Pegawai = (String) h.get("ID_PEGAWAI");
        			Ahli_Negeri = (String) h.get("ID_NEGERI");
        			Ahli_Seksyen = (String) h.get("ID_SEKSYEN");
        			Ahli_Unit = (String) h.get("ID_UNIT");
        			Ahli_Peranan = (String) h.get("ID_PERANAN");
        			h.clear();
            		vData.clear();
        			
        			
        		}
        		}
        		else  if(jenis_kategori.equals("2"))       		
        		{
        			System.out.println("MASUK");
        		getAhliMesyuaratDataUrussetia = modelMesyuarat.getAhliMesyuaratDataUrussetia(ID_MESYUARAT, ID_AHLI);
        		if (!getAhliMesyuaratDataUrussetia.isEmpty()) {
        			h = (Hashtable) getAhliMesyuaratDataUrussetia.get(0);
        			negeri_user = (String) h.get("NEGERI_USER");
        			id_user = (String) h.get("USER_ID");
        			Ahli_Kategori = (String) h.get("KATEGORI_AHLI");
        			Ahli_Nama = (String) h.get("NAMA_AHLI");
        			Ahli_Agensi = (String) h.get("NAMA_AGENSI");
        			Ahli_Jawatan = (String) h.get("JAWATAN");
        			Ahli_Email = (String) h.get("EMAIL");
        			Ahli_NoTelefon = (String) h.get("NO_TEL");
        			Ahli_NoFaks = (String) h.get("NO_FAKS");
        			Ahli_Pegawai = (String) h.get("ID_PEGAWAI");
        			Ahli_Negeri = (String) h.get("ID_NEGERI");
        			Ahli_Seksyen = (String) h.get("ID_SEKSYEN");
        			Ahli_Unit = (String) h.get("ID_UNIT");
        			Ahli_Peranan = (String) h.get("ID_PERANAN");
        		    AlamatAhli = (String) h.get("ALAMAT_AHLI");
        			
        			h.clear();
        			getAhliMesyuaratDataUrussetia.clear();
        			
        			//System.out.println("CHECK Ahli_Kategori :"+Ahli_Kategori);
        		}
        		}
    		
        		
        			if ("1".equalsIgnoreCase(jenis_kategori)) {
        				Ahli_Kategori = "1";
        				Ahli_Kategori1 = "checked=\"checked\"";
        				Ahli_Kategori2 = "";
        				//Ahli_Kategori3 = "";
        				if ("16".equalsIgnoreCase(negeri_user)) {
        		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", id_user, SOC_SEKSYEN, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai');\"",submit,action);
        		        	context.put("Ahli_Pegawai", Ahli_Pegawai);
                		}		        
        		        else  {		        	
        		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiByNegeri(ID_MESYUARAT,"SOC_PEGAWAI", id_user, negeri_user, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai1');\"",submit,action);
        		        	context.put("Ahli_Pegawai", Ahli_Pegawai);
        		        }
        			} else if ("2".equalsIgnoreCase(jenis_kategori)) {
        				Ahli_Kategori = "2";
        				Ahli_Kategori1 = "";
        				Ahli_Kategori2 = "checked=\"checked\"";
        				//Ahli_Kategori3 = "";
        			} else if ("3".equalsIgnoreCase(jenis_kategori)) {
        				Ahli_Kategori = "3";
        				Ahli_Kategori1 = "";
        				Ahli_Kategori2 = "";
        				//Ahli_Kategori3 = "checked=\"checked\"";
        				if ("16".equalsIgnoreCase(negeri_user)) {
        		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", id_user, SOC_SEKSYEN, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai2');\"",submit,action);
        		        	context.put("Ahli_Pegawai", Ahli_Pegawai);
                		}		        
        		        else  {		        	
        		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiByNegeri(ID_MESYUARAT,"SOC_PEGAWAI", id_user, negeri_user, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai3');\"",submit,action);
        		        	context.put("Ahli_Pegawai", Ahli_Pegawai);
        		        }
        			}
        			else
        			{
        				Ahli_Kategori = "1";
        				Ahli_Kategori1 = "checked=\"checked\"";
        				Ahli_Kategori2 = "";
        			//	Ahli_Kategori3 = "";
        				if ("16".equalsIgnoreCase(negeri_user)) {
        		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", id_user, SOC_SEKSYEN, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai');\"",submit,action);
        		        	context.put("Ahli_Pegawai", Ahli_Pegawai);
                		}		        
        		        else  {		        	
        		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiByNegeri(ID_MESYUARAT,"SOC_PEGAWAI", id_user, negeri_user, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai1');\"",submit,action);
        		        	context.put("Ahli_Pegawai", Ahli_Pegawai);
        		        }
        			}
        		
        			vList = modelMesyuarat.getAhliMesyuaratTemplateList(ID_MESYUARAT);
            		context.put("List_CarianAhliMesyuarat", vList);
            		
            		

        	
        		
        		
    		}
        }
        */
        else if ("hapusAhli".equalsIgnoreCase(action)) {
    		vm = "app/pfd/frmMesyuaratTemplateDaftar.jsp";
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT) && !"".equalsIgnoreCase(ID_AHLI)) {
    			vm = "app/pfd/frmMesyuaratTemplateAhli.jsp";
    			
        		vData = modelMesyuarat.getMesyuaratTemplateData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();

        		// delete data ahli ni
    			modelMesyuarat.deleteAhliMesyuaratTemplateData(ID_MESYUARAT, ID_AHLI);
    			
    			// kosongkan fields
        		vList = modelMesyuarat.getAhliMesyuaratTemplateList(ID_MESYUARAT);
        		context.put("List_CarianAhliMesyuarat", vList);
        		Ahli_Kategori = "1";
    			Ahli_Kategori1 = "checked=\"checked\"";
    			Ahli_Nama = "";
    			Ahli_Agensi = "";
    			Ahli_Jawatan = "";
    			Ahli_Email = "";
    			Ahli_NoTelefon = "";
    			Ahli_NoFaks = "";
    			Ahli_Negeri = "";
    			Ahli_Seksyen = "";
    			Ahli_Unit = "";
    			Ahli_Peranan = "";
        		
    			action = "daftarAhli";
    		}        	
        } else if ("selesaiMesyuarat".equalsIgnoreCase(action)) {
    		vm = "app/pfd/frmMesyuaratTemplateDaftar.jsp";
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
    			vm = "app/pfd/frmMesyuaratTemplateSelesai.jsp";

    			if("16".equalsIgnoreCase(user_negeri)){
    				vData = modelMesyuarat.getMesyuaratTemplateData(ID_MESYUARAT);	
    			}
    			else{
    				vData = modelMesyuarat.getMesyuaratTemplateDataNegeri(ID_MESYUARAT);	
    			}
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		//Mesyuarat_Unit = Mesyuarat_Unit;
        		//Mesyuarat_Unit = Mesyuarat_ValueUnit;
        		h.clear();
        		vData.clear();
        		
        		vList = modelMesyuarat.getListMesyuaratTemplateKehadiran(ID_MESYUARAT);
        		context.put("List_AhliMesyuarat", vList);
        		//vList.clear();
    		}
        } else if ("carianMesyuarat".equalsIgnoreCase(action)) {
    		vm = "app/pfd/frmMesyuaratTemplateCarian.jsp";
        	
    		String Search_NoRujukanDokumen = getParam("Carian_NoRujukanDokumen");
        	String Search_Tajuk = getParam("Carian_Tajuk");
        	String Search_Urusetia = getParam("Carian_Urusetia");
        	String Search_Lokasi = getParam("Carian_Lokasi");
        	String Search_Tarikh = getParam("Carian_Tarikh");
        	
        	if("16".equalsIgnoreCase(user_negeri)){
        		vList = modelMesyuarat.getListMesyuaratTemplate(Search_NoRujukanDokumen, Search_Tajuk, Search_Urusetia, Search_Lokasi, Search_Tarikh, id_seksyen, id_negeri, user_id);
            	context.put("ListMesyuarat", vList);
    	        context.put("selectSeksyen", HTML.SelectSeksyen("Carian_Urusetia", Long.parseLong("0"), ""));
    	        context.put("selectLokasi", HTML.SelectLokasiMesyuarat("Carian_Lokasi", Long.parseLong("0"), ""));
        	}
        	else{
        		vList = modelMesyuarat.getListMesyuaratTemplateNegeri(Search_NoRujukanDokumen, Search_Tajuk, Search_Urusetia, Search_Lokasi, Search_Tarikh, id_negeri, user_id);
            	context.put("ListMesyuarat", vList);
    	       // context.put("selectUnit", HTML.SelectUnit("Carian_Urusetia", Long.parseLong("0"), ""));
    	        context.put("selectLokasi", HTML.SelectLokasiMesyuarat("Carian_Lokasi", Long.parseLong("0"), ""));
        	}
        	
    		Page_Carian = true;

    		setupPage(session, action, vList);
    		context.put("pagingTitle", "title");
        } else {
        	// similar like daftarMesyuarat
        	vm = "app/pfd/frmMesyuaratTemplateDaftar.jsp";
        	action = "daftarMesyuarat";
        	RO_NoFail = "";
        	PrevPage = "1";
        }
        
        if (Page_Carian) {
	        context.put("selectSeksyen", HTML.SelectSeksyen("Mesyuarat_Urusetia", Long.parseLong("0"), ""));
	        context.put("selectLokasi", HTML.SelectLokasiMesyuarat("Mesyuarat_Lokasi", Long.parseLong("0"), ""));
	        context.put("selectUnit", HTML.SelectSeksyen("Mesyuarat_Urusetia", Long.parseLong("0"), ""));
        } else {	
	        context.put("Mesyuarat_NoRujukanMesyuarat", Mesyuarat_NoRujukanMesyuarat);
	        context.put("Mesyuarat_Bil", Mesyuarat_Bil);
	    	context.put("Mesyuarat_Tajuk", Mesyuarat_Tajuk);
	    	context.put("Mesyuarat_Kategori", Mesyuarat_Kategori);
	    	context.put("Mesyuarat_Tarikh", Mesyuarat_Tarikh);
	    	//context.put("Mesyuarat_Dari", Mesyuarat_Dari);
	    	//context.put("Mesyuarat_Hingga", Mesyuarat_Hingga);
	    	if ("daftarMesyuarat".equalsIgnoreCase(action)) {
		    	//context.put("Mesyuarat_Lokasi", modelMesyuarat.getSOCLokasiMesyuarat("SOC_LOKASI", Mesyuarat_Lokasi, "", ""));
		    	if("16".equalsIgnoreCase(user_negeri)){
		    		context.put("Mesyuarat_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(Mesyuarat_Seksyen), ""));
		    	}
	    		else{
	    			this.context.put("Mesyuarat_Negeri",HTML.SelectNegeri("SOC_NEGERI",Utils.parseLong(id_negeri)));
//	    			String SOC_NEGERI = getParam("SOC_NEGERI");
	    			Vector getUnitByNegeri_size = modelMesyuarat.getUnitByNegeri((String) session.getAttribute("_ekptg_user_negeri"));
           	    	this.context.put("getUnitByNegeri_size", getUnitByNegeri_size.size());
           	    	//context.put("Mesyuarat_Unit", HTML.SelectUnitByNegeri((String) session.getAttribute("_ekptg_user_negeri"), "SOC_UNIT", null,null));
           	    	context.put("Mesyuarat_Unit", HTML.SelectUnit("SOC_UNIT",Utils.parseLong(user_negeri), ""));

	    	
	    		
	    		}
	    	} else {
	    		if ("2".equalsIgnoreCase(Mesyuarat_Kategori)) {
	    			context.put("Mesyuarat_Kategori", "RUTIN");
	    		} else {
	    			context.put("Mesyuarat_Kategori", "AD-HOC");
	    		}
		    	context.put("Mesyuarat_Lokasi", Mesyuarat_ValueLokasi);
		    	context.put("Mesyuarat_Seksyen", Mesyuarat_ValueSeksyen);
		    	context.put("Mesyuarat_Negeri", Mesyuarat_ValueNegeri);
		    	context.put("Mesyuarat_Unit", Mesyuarat_ValueUnit);
	    	}
	    	if ("selesaiMesyuarat".equalsIgnoreCase(action)) {
	    	//	context.put("Mesyuarat_Dari", Mesyuarat_ValueDari);
	    	//	context.put("Mesyuarat_Hingga", Mesyuarat_ValueHingga);
	    		context.put("Mesyuarat_Status", Mesyuarat_ValueStatus);
	    	} else {
	    		context.put("Mesyuarat_Status", modelMesyuarat.getSOCStatusMesyuarat("SOC_STATUS", Mesyuarat_Status, "", ""));
	    	}
	    	context.put("Mesyuarat_NoFail", Mesyuarat_NoFail);
	    	if ("".equalsIgnoreCase(ID_MESYUARAT)) {
	    	context.put("Mesyuarat_DisediakanOleh", user_name);
	    	}else{
	    		context.put("Mesyuarat_DisediakanOleh", Mesyuarat_DisediakanOleh);
	    	}
	    		
	    	context.put("Mesyuarat_DisemakOleh", Mesyuarat_DisemakOleh);
	    	context.put("Mesyuarat_DisahkanOleh", Mesyuarat_DisahkanOleh);
	    	context.put("Mesyuarat_Catatan", Mesyuarat_Catatan);
	    	//context.put("Mesyuarat_Masa", Mesyuarat_Masa);
	    	context.put("Mesyuarat_ValueLokasi", Mesyuarat_ValueLokasi);
	    	context.put("Mesyuarat_ValueSeksyen", Mesyuarat_ValueSeksyen);
	    	context.put("Mesyuarat_ValueNegeri", Mesyuarat_ValueNegeri);
	    	context.put("Mesyuarat_ValueUnit", Mesyuarat_ValueUnit);
	    	
	    	context.put("Ahli_Kategori", Ahli_Kategori);
	    	context.put("Ahli_Kategori1", Ahli_Kategori1);
	    	context.put("Ahli_Kategori2", Ahli_Kategori2);
	    	context.put("Ahli_Nama", Ahli_Nama);
	    	context.put("Ahli_Agensi", Ahli_Agensi);
	    	context.put("Ahli_Jawatan", Ahli_Jawatan);
	    	context.put("Ahli_Email", Ahli_Email);
	    	context.put("Ahli_Jawatan", Ahli_Jawatan);
	    	context.put("Ahli_NoTelefon", Ahli_NoTelefon);
	    	context.put("Ahli_NoFaks", Ahli_NoFaks);
	    
	    	
	    	if(!action.equals("viewAhli") && !action.equals("tambahAhli"))
	    	{
	    	context.put("Ahli_Pegawai", modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", SOC_PEGAWAI, Ahli_Seksyen, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai');\"",action) );
	    	}
	    	
	    	
	    	context.put("Ahli_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(Ahli_Negeri), "", " onchange=\"toggleNegeri();doChangeSOC('changeNegeri');\""));
	    	context.put("Ahli_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(Ahli_Seksyen), " onchange=\"toggleSeksyen(); doChangeSOC('changeSeksyen');\""));
	    //	context.put("Ahli_Unit", HTML.SelectUnit("SOC_UNIT", Utils.parseLong(Ahli_Unit), " onchange=\"toggleSeksyen(); doChangeSOC('changeUnit');\""));
	    	context.put("Ahli_Peranan", modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", Ahli_Peranan, Ahli_Kategori));
	    	context.put("Ahli_Pengerusi", Ahli_Pengerusi);
	    	context.put("Ahli_SelPengerusi", Ahli_SelPengerusi);
	    	context.put("Ahli_SelTimbPengerusi", Ahli_SelTimbPengerusi);
	    	context.put("Ahli_SelSetiausaha", Ahli_SelSetiausaha);
	    	context.put("Ahli_DisableSelPengerusi", Ahli_DisableSelPengerusi);
	    	context.put("Ahli_DisableSelTimbPengerusi", Ahli_DisableSelTimbPengerusi);
	    	context.put("Ahli_DisableSelSetiausaha", Ahli_DisableSelSetiausaha);
	    	
	    	context.put("ID_MESYUARAT", ID_MESYUARAT);
	        context.put("ID_TEMPLATE", ID_TEMPLATE);
	        context.put("ID_AHLI", ID_AHLI);
	        
	        context.put("selectedTab", selectedTab);
	        context.put("RO_NoFail", RO_NoFail);
	        context.put("action", action);
	        
	        
	        
	        
	        if ("changeSeksyen".equalsIgnoreCase(submit)) {
	        	System.out.println("testmansengal");
	        	context.put("Ahli_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(SOC_SEKSYEN), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeSeksyen');\""));
	        	context.put("Ahli_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(null), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeNegeri');\""));
	        	
	        	
	        	Ahli_Kategori = "1";
	        	Ahli_Kategori1 = "checked=\"checked\"";
	        	Ahli_Kategori2 = "";
	        	
	        	Ahli_Peranan = modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", Ahli_Peranan, "1", RO_MaklumatMesyuarat);
	        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", "", SOC_SEKSYEN, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai');\"",submit,action);
	            context.put("Ahli_Pegawai",Ahli_Pegawai);
	            
	           
		    	context.put("Ahli_Nama", "");
		    	context.put("txtAlamatAhli", "");
		    	context.put("Ahli_Agensi", "");
		    	context.put("Ahli_Jawatan", "");
		    	context.put("Ahli_Email", "");
		    	context.put("Ahli_NoFaks", "");
		    	context.put("Ahli_NoTelefon", "");
	  	      
	        } 
	        else if ("changePegawai".equalsIgnoreCase(submit)) {
	        	context.put("Ahli_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(SOC_SEKSYEN), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeSeksyen');\""));
	        	Ahli_Kategori = "1";	        
	        		Ahli_Kategori1 = "checked=\"checked\"";
		        	Ahli_Kategori2 = "";		        
	        	Ahli_Peranan = modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", Ahli_Peranan, "1", RO_MaklumatMesyuarat);
	        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", SOC_PEGAWAI, SOC_SEKSYEN, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai');\"",submit,action);
	        	Vector getMaklumatPegawai = new Vector();
	        	getMaklumatPegawai = modelMesyuarat.getMaklumatPegawai(getParam("SOC_PEGAWAI"));
        		if (!getMaklumatPegawai.isEmpty()) {
        			h = (Hashtable) getMaklumatPegawai.get(0);	        			
        			context.put("Ahli_Jawatan",(String) h.get("KETERANGAN"));
        			context.put("Ahli_Email",(String) h.get("EMEL"));
        		}
        		else
        		{
        			context.put("Ahli_Jawatan","");
        			context.put("Ahli_Email","");
        			
        		}
	        
	        
	        }
	        	        
	        else if ("changePegawai1".equalsIgnoreCase(submit)) {
	        	context.put("Ahli_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(SOC_NEGERI), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeNegeri');\""));
                
                    Ahli_Kategori = "1";	        	
	        		Ahli_Kategori1 = "checked=\"checked\"";
		        	Ahli_Kategori2 = "";
		        	
	        	
	        	Ahli_Peranan = modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", Ahli_Peranan, "1", RO_MaklumatMesyuarat);
	        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiByNegeri(ID_MESYUARAT,"SOC_PEGAWAI", SOC_PEGAWAI, SOC_NEGERI, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai1');\"",submit,action);
	      	Vector getMaklumatPegawai = new Vector();
	        	getMaklumatPegawai = modelMesyuarat.getMaklumatPegawai(getParam("SOC_PEGAWAI"));
        		if (!getMaklumatPegawai.isEmpty()) {
        			h = (Hashtable) getMaklumatPegawai.get(0);	        			
        			context.put("Ahli_Jawatan",(String) h.get("KETERANGAN"));
        			context.put("Ahli_Email",(String) h.get("EMEL"));
        		}
        		else
        		{
        			context.put("Ahli_Jawatan","");
        			context.put("Ahli_Email","");
        			
        		}
	        
	        
	        }
	        else if ("changeNegeri".equalsIgnoreCase(submit)) {
	        	
	        	System.out.println("MASUK SINI:");
	        	
	        	context.put("Ahli_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(SOC_NEGERI), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeNegeri');\""));
	        	context.put("Ahli_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(null), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeSeksyen');\""));
	        	
	        	Ahli_Kategori = "1";
	        	Ahli_Kategori1 = "checked=\"checked\"";
	        	Ahli_Kategori2 = "";
	        	
	        	Ahli_Peranan = modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", Ahli_Peranan, "1", RO_MaklumatMesyuarat);
	        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiByNegeri(ID_MESYUARAT,"SOC_PEGAWAI", SOC_PEGAWAI, SOC_NEGERI, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai1');\"",submit,action);
	       
	        	context.put("Ahli_Nama", "");
		    	context.put("txtAlamatAhli", "");
		    	context.put("Ahli_Agensi", "");
		    	context.put("Ahli_Jawatan", "");
		    	context.put("Ahli_Email", "");
		    	context.put("Ahli_NoFaks", "");
		    	context.put("Ahli_NoTelefon", "");
	        
	        } else if ("changeKategori1".equalsIgnoreCase(submit)) {
	        	Ahli_Kategori = "1";
	        	Ahli_Kategori1 = "checked=\"checked\"";
	        	Ahli_Kategori2 = "";
	        	
	        	Ahli_Peranan = modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", "", "1", RO_MaklumatMesyuarat);
	        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", "", Ahli_Seksyen, "1", RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai');\"",submit,action);
	        	context.put("Ahli_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(null), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeNegeri');\""));
	        	context.put("Ahli_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(null), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeSeksyen');\""));
	        	
	        	context.put("Ahli_Nama", "");
		    	context.put("txtAlamatAhli", "");
		    	context.put("Ahli_Agensi", "");
		    	context.put("Ahli_Jawatan", "");
		    	context.put("Ahli_Email", "");
		    	context.put("Ahli_NoFaks", "");
		    	context.put("Ahli_NoTelefon", "");
		    	context.put("Ahli_Peranan", Ahli_Peranan);
		    	
	        	
	        }else if ("changeKategori2".equalsIgnoreCase(submit)) {
	        	Ahli_Kategori = "2";
	        	Ahli_Kategori1 = "";
	        	Ahli_Kategori2 = "checked=\"checked\"";
	        
	        	Ahli_Peranan = modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", "", "2", RO_MaklumatMesyuarat);
	     
	        	context.put("Ahli_Nama", "");
		    	context.put("txtAlamatAhli", "");
		    	context.put("Ahli_Agensi", "");
		    	context.put("Ahli_Jawatan", "");
		    	context.put("Ahli_Email", "");
		    	context.put("Ahli_NoFaks", "");
		    	context.put("Ahli_NoTelefon", "");
		    	context.put("Ahli_Peranan", Ahli_Peranan);
	        }
	        
	        
        }
        
        
    	
        
        
      
        
        context.put("Ahli_Kategori", Ahli_Kategori);
    	context.put("Ahli_Kategori1", Ahli_Kategori1);
    	context.put("Ahli_Kategori2", Ahli_Kategori2);
    	
    	//context.put("Ahli_NoFaks", Ahli_NoFaks);
    	//context.put("Ahli_NoTelefon", Ahli_NoTelefon);
    	
    	//context.put("Ahli_Peranan", Ahli_Peranan);
    	context.put("Ahli_Pegawai", Ahli_Pegawai);
    	//context.put("Ahli_Jawatan", Ahli_Jawatan);
    	
        if (vm == "app/pfd/frmMesyuaratTemplateDaftar.jsp"){
			context.put("Flag_failWujud", Flag_failWujud);       	
        }
		return vm;
	}
	
	private void view_Seksyen(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmModelMesyuarat.setSeksyen(user_id);

		}

	private void populateMesyuaratData(Hashtable<?, ?> h) throws Exception {
		Mesyuarat_NoRujukanMesyuarat = (String) h.get("Mesyuarat_NoRujukanMesyuarat");
		Mesyuarat_Bil = (String) h.get("Mesyuarat_Bil");
		Mesyuarat_Tajuk = (String) h.get("Mesyuarat_Tajuk");
		Mesyuarat_Kategori = (String) h.get("Mesyuarat_Kategori");
		Mesyuarat_Tarikh = (String) h.get("Mesyuarat_Tarikh");
		//Mesyuarat_Dari = (String) h.get("Mesyuarat_Dari");
		//Mesyuarat_Hingga = (String) h.get("Mesyuarat_Hingga");
		Mesyuarat_Lokasi = (String) h.get("Mesyuarat_Lokasi");
		Mesyuarat_Status = (String) h.get("Mesyuarat_Status");
		Mesyuarat_Seksyen = (String) h.get("Mesyuarat_Seksyen");
		Mesyuarat_Negeri = (String) h.get("Mesyuarat_Negeri");
		Mesyuarat_Unit = (String) h.get("Mesyuarat_Unit");
		Mesyuarat_NoFail = (String) h.get("Mesyuarat_NoFail");
		Mesyuarat_DisediakanOleh = (String) h.get("Mesyuarat_DisediakanOleh");
		Mesyuarat_DisemakOleh = (String) h.get("Mesyuarat_DisemakOleh");
		Mesyuarat_DisahkanOleh = (String) h.get("Mesyuarat_DisahkanOleh");
		Mesyuarat_Catatan = (String) h.get("Mesyuarat_Catatan");
		Mesyuarat_ValueDari = (String) h.get("Mesyuarat_ValueDari");
		Mesyuarat_ValueHingga = (String) h.get("Mesyuarat_ValueHingga");
		Mesyuarat_ValueLokasi = (String) h.get("Mesyuarat_ValueLokasi");
		Mesyuarat_ValueSeksyen = (String) h.get("Mesyuarat_ValueSeksyen");
		Mesyuarat_ValueNegeri = (String) h.get("Mesyuarat_ValueNegeri");
		Mesyuarat_ValueUnit = (String) h.get("Mesyuarat_ValueUnit");
		Mesyuarat_ValueStatus  = (String) h.get("Mesyuarat_ValueStatus");
	//	Mesyuarat_Masa = Mesyuarat_ValueDari + " - " + Mesyuarat_ValueHingga;
//		if ("".equalsIgnoreCase(Mesyuarat_Status)) {
//			Mesyuarat_Status = "1";
//			Mesyuarat_ValueStatus = "BARU";
//		}
//		if ("".equalsIgnoreCase(Mesyuarat_Tarikh)) {
//			Date date = new Date();
//			Mesyuarat_Tarikh = sdf.format(date);
//		}
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
		this.context.put("SenaraiFail",paging.getPage(page));
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
}