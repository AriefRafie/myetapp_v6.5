package ekptg.view.pfd;






import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.db.UniqueID;
import lebah.portal.AjaxBasedModule;
import lebah.util.DateTool;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.pfd.FrmModelMesyuarat;
@SuppressWarnings({ "serial", "unused" })
public class FrmViewMesyuarat extends AjaxBasedModule {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
    String Mesyuarat_NoFail = "";
    String Mesyuarat_Bil = "";
    String Mesyuarat_Tajuk = "";
    String Mesyuarat_Kategori = "";
    String Mesyuarat_Tarikh = "";
    String Mesyuarat_Dari = "";
    String Mesyuarat_Hingga = "";
    String Mesyuarat_Lokasi = "";
    String Mesyuarat_Status = "";
    String Mesyuarat_Seksyen = "";
    String Mesyuarat_Unit = "";
    String Mesyuarat_Negeri = "";
    String Mesyuarat_NoFailDiperlukan = "";
    String Mesyuarat_DisediakanOleh = "";
    String Mesyuarat_DisemakOleh = "";
    String Mesyuarat_DisahkanOleh = "";
    String Mesyuarat_Catatan = "";
    String Mesyuarat_ValueDari = "";
    String Mesyuarat_ValueHingga = "";
    String Mesyuarat_ValueLokasi = "";
    String Mesyuarat_ValueSeksyen = "";
    String Mesyuarat_ValueUnit = "";
    String Mesyuarat_ValueNegeri = "";
    String Mesyuarat_ValueStatus = "";
    String Mesyuarat_Masa = "";
    String Mesyuarat_TempahMakananPemohon = "";
    String Mesyuarat_TempahMakananBilAhli = "";
    String Mesyuarat_TempahMakananMakanan = "";
    String Mesyuarat_TempahMakananMinuman = "";

    String Ahli_Kategori = "";
    String Nama_Pegawai = "";
    String Ahli_Nama = "";
    String AlamatAhli = "";
    String Ahli_Negeri = "";
    String Ahli_Seksyen = "";
    String Ahli_Unit = "";
    String Ahli_Pegawai = "";
    String set_maklumat = "";
    String Ahli_Agensi = "";
    String Ahli_Jawatan = "";
    String Ahli_Email = "";
    String Ahli_NoTelefon = "";
    String Ahli_NoFaks = "";
    String Ahli_Peranan = "";
    String txtAlamatAhli = "";

    String Agenda_No = "";
    String Agenda_Tajuk = "";
    String Agenda_Agenda = "";
    
    String Minit_Agenda = "";
    String Minit_No = "";
    String Minit_Tajuk = "";
    String Minit_Minit = "";
    String Minit_Tindakan = "";
    String Minit_Maklumbalas = "";
    String Minit_Makluman = "";

    String SubMinit_Agenda = "";
    String SubMinit_Minit = "";
    String SubMinit_No = "";
    String SubMinit_Tajuk = "";
    String SubMinit_SubMinit = "";
    String SubMinit_Tindakan = "";
    String SubMinit_Maklumbalas = "";
    String SubMinit_Makluman = "";

    String SOC_LOKASI = "";
    String SOC_STATUS = "";
    String SOC_SEKSYEN = "";
    String SOC_NEGERI = "";
    String SOC_UNIT = "";
    String SOC_PEGAWAI = "";
    String SOC_AGENDA = "";
    String SOC_MINIT = "";
    String SOC_PERANAN = "";    
    
    String Ahli_Kategori1 = "";
    String Ahli_Kategori2 = "";
    String Ahli_Kategori3 = "";
    
    String userName = "";
    Vector listPegawai = new Vector();

	
	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception{
		
		this.context.put("check_pengerusi","");
		this.context.put("check_setiausaha","");
		
		String vm = "";

        HttpSession session = this.request.getSession();
        
        FrmModelMesyuarat modelMesyuarat = new FrmModelMesyuarat();
        //
        
       
        String[] xxx = this.request.getParameterValues("Ahli_Kategori");
		if (xxx != null) {
			for (int i = 0; i < xxx.length; i++) {						
					if(xxx[i]!=null)
					{
						System.out.println("get radio :"+xxx[i]);
					}
					
				}
			}
    
    	
		String Ahli_Kategori = getParam("Ahli_Kategori");	
		
		System.out.println("get param radio :"+getParam("Ahli_Kategori"));
        
        String user_name = (String)session.getAttribute("_portal_username");
        String user_id = (String)session.getAttribute("_ekptg_user_id");
        String user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
        String portal_role = (String)session.getAttribute("_portal_role");
        String myrole = (String)session.getAttribute("myrole");

        view_Seksyen(session);
        Vector paparSeksyen = new Vector();
        paparSeksyen = FrmModelMesyuarat.getDataSeksyen();
        Hashtable hA = (Hashtable) paparSeksyen.get(0);
        String id_seksyen = String.valueOf(hA.get("id_seksyen"));
        String id_negeri = String.valueOf(hA.get("id_negeri"));
        //context.put("idSeksyen",(hA.get("id_seksyen")));
        //   context.put("idNegeri",(hA.get("id_negeri")));
        // actionx: agenda, minit, subminit
        String action = getParam("action");
        String actionx = getParam("actionx");
        String actionx1 = getParam("action");
        
        System.out.println("actionx1actionx1:"+actionx);
        
        System.out.println("actionx declare by peje:"+actionx);
        
		if ("doChangeItemPerPage".equals(action) ||
				"getPage".equals(action)) {
			actionx = action;
		}
        
        // submit: used by changeSOC
        String submit = getParam("command");
        
        userName = (String) session.getAttribute("_portal_username");
        
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
        
        String RO_MaklumatMesyuarat = "", RO_NoFail = "readonly=\"readonly\"";
        String statusProses = "";
        Boolean hideSaveButton = false;
        Boolean locIsUnavailable = false;
                
        Mesyuarat_Bil = getParam("Mesyuarat_Bil");
        Mesyuarat_Tajuk = getParam("Mesyuarat_Tajuk");
        Mesyuarat_Kategori = getParam("Mesyuarat_Kategori");
        Mesyuarat_Tarikh = getParam("Mesyuarat_Tarikh");
        Mesyuarat_Dari = getParam("Mesyuarat_Dari");
        Mesyuarat_Hingga = getParam("Mesyuarat_Hingga");
        Mesyuarat_Lokasi = getParam("Mesyuarat_Lokasi");
        Mesyuarat_Status = getParam("Mesyuarat_Status");
        Mesyuarat_Seksyen = getParam("Mesyuarat_Seksyen");
        Mesyuarat_Unit = getParam("Mesyuarat_Unit");
        Mesyuarat_Negeri = getParam("Mesyuarat_Negeri");
        Mesyuarat_NoFailDiperlukan = getParam("Mesyuarat_NoFailDiperlukan");
        Mesyuarat_NoFail = getParam("Mesyuarat_NoFail");
        Mesyuarat_DisediakanOleh = getParam("Mesyuarat_DisediakanOleh");
        Mesyuarat_DisemakOleh = getParam("Mesyuarat_DisemakOleh");
        Mesyuarat_DisahkanOleh = getParam("Mesyuarat_DisahkanOleh");
        Mesyuarat_Catatan = getParam("Mesyuarat_Catatan");
        Mesyuarat_TempahMakananPemohon = getParam("Mesyuarat_TempahMakananPemohon");
        Mesyuarat_TempahMakananBilAhli = getParam("Mesyuarat_TempahMakananBilAhli");
        Mesyuarat_TempahMakananMakanan = getParam("Mesyuarat_TempahMakananMakanan");
        Mesyuarat_TempahMakananMinuman = getParam("Mesyuarat_TempahMakananMinuman");
        
        Ahli_Kategori = getParam("Ahli_Kategori");
        
        
        Nama_Pegawai = getParam("Nama_Pegawai");
        Ahli_Nama = getParam("Ahli_Nama");
        txtAlamatAhli = getParam("txtAlamatAhli");
        Ahli_Negeri = getParam("Ahli_Negeri");
        Ahli_Seksyen = getParam("Ahli_Seksyen");
        Ahli_Unit = getParam("Ahli_Unit");
        Ahli_Pegawai = getParam("Ahli_Pegawai");
       // set_maklumat = getParam("set_maklumat");
        Ahli_Agensi = getParam("Ahli_Agensi");
        Ahli_Jawatan = getParam("Ahli_Jawatan");
        Ahli_Email = getParam("Ahli_Email");
        Ahli_NoTelefon = getParam("Ahli_NoTelefon");
        Ahli_NoFaks = getParam("Ahli_NoFaks");
        Ahli_Peranan = getParam("Ahli_Peranan");
        
        Agenda_No = getParam("Agenda_No");
        Agenda_Tajuk = getParam("Agenda_Tajuk");
        Agenda_Agenda = getParam("Agenda_Agenda");
        
        Minit_Agenda = getParam("Minit_Agenda");
        Minit_No = getParam("Minit_No");
        Minit_Tajuk = getParam("Minit_Tajuk");
        Minit_Minit = getParam("Minit_Minit");
        Minit_Tindakan = getParam("Minit_Tindakan");
        Minit_Maklumbalas = getParam("Minit_Maklumbalas");
        Minit_Makluman = getParam("Minit_Makluman");

        SubMinit_Agenda = getParam("SubMinit_Agenda");
        SubMinit_Minit = getParam("SubMinit_Minit");
        SubMinit_No = getParam("SubMinit_No");
        SubMinit_Tajuk = getParam("SubMinit_Tajuk");
        SubMinit_SubMinit = getParam("SubMinit_SubMinit");
        SubMinit_Tindakan = getParam("SubMinit_Tindakan");
        SubMinit_Maklumbalas = getParam("SubMinit_Maklumbalas");
        SubMinit_Makluman = getParam("SubMinit_Makluman");
        
        if ("".equalsIgnoreCase(Ahli_Kategori)) {
        	Ahli_Kategori = "1";
        }
        
        SOC_LOKASI = getParam("SOC_LOKASI");
        SOC_STATUS = getParam("SOC_STATUS");
        SOC_SEKSYEN = getParam("SOC_SEKSYEN");
        SOC_NEGERI = getParam("SOC_NEGERI");
        SOC_UNIT = getParam("SOC_UNIT");
        SOC_PEGAWAI = getParam("SOC_PEGAWAI");
        SOC_AGENDA = getParam("SOC_AGENDA");
        SOC_MINIT = getParam("SOC_MINIT");
        SOC_PERANAN = getParam("SOC_PERANAN");
        
    	Vector vData = new Vector();
    	Vector list = new Vector();
    	Hashtable h = new Hashtable();
    	
    	if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
    		statusProses = modelMesyuarat.getStatusProses(ID_MESYUARAT);
    		this.context.put("idNegeri", user_negeri);
    	}
        
        if ("daftarMesyuarat".equalsIgnoreCase(actionx)) {
        	vm = "app/pfd/frmMesyuaratDaftar.jsp";
        	this.context.put("idNegeri", user_negeri);
        	PrevPage = "1";
        	
        	System.out.println("ID_MESYUARAT:"+ID_MESYUARAT);
        	System.out.println("ID_TEMPLATE:"+ID_TEMPLATE);
        	
        	
        	if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
            	// kalau ada ID, amik data existing
 //       		if("16".equalsIgnoreCase(user_negeri)){
        			vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
//        		}
//        		else{
//        			vData = modelMesyuarat.getMesyuaratDataNegeri(ID_MESYUARAT);
//        		}
        		
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        	} else if (!"".equalsIgnoreCase(ID_TEMPLATE)) {
            	// else kalau ada ID template, amik data dari template
       
        		vData = modelMesyuarat.getMesyuaratTemplateData(ID_TEMPLATE);
        
        		
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		Mesyuarat_Bil = modelMesyuarat.getMesyuaratNextBilNo(Mesyuarat_Tajuk);
        		
        		//vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		
        		
        		
        		
        		
        	} else {
        		RO_NoFail = "";
        		SOC_STATUS = "1";
        	}
        } else if ("hapusMesyuarat".equalsIgnoreCase(actionx)) {
        	vm = "app/pfd/frmMesyuaratCarian.jsp";
        	this.context.put("idNegeri", user_negeri);
        	modelMesyuarat.deleteMesyuaratData(ID_MESYUARAT);        	
        	modelMesyuarat.deleteMesyuaratDiCalendar(ID_MESYUARAT);   
        	
            this.context.put("ListMesyuarat", list);
     	    prepareItemForDisplay(session,list,10,"first");

    		setupPage(session, actionx, list);
     		
        } else if ("email".equalsIgnoreCase(actionx)) {
         	//ready sop
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
    			vm = "app/pfd/frmMesyuaratSelesai.jsp";
    			this.context.put("idNegeri", user_negeri);

        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			modelMesyuarat.email(ID_MESYUARAT,h); 
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		list = modelMesyuarat.getListMesyuaratKehadiran(ID_MESYUARAT);
        		context.put("List_AhliMesyuarat", list);
        		//list.clear();

        		vData = modelMesyuarat.getListMesyuaratMinit(ID_MESYUARAT);
        		context.put("List_Minit", vData);
        		//vData.clear();
        		
        		// add to calendar event
        		int eventYear = 0, eventMonth = 0, eventDay = 0;
        		//String user_id = (String)session.getAttribute("_ekptg_user_id");
        		Hashtable event = new Hashtable();
        		if (FrmModelMesyuarat.isNumeric(Mesyuarat_Tarikh.substring(6, 10))) {
        			eventYear = Integer.parseInt(Mesyuarat_Tarikh.substring(6, 10));
        		}
        		if (FrmModelMesyuarat.isNumeric(Mesyuarat_Tarikh.substring(3, 5))) {
        			eventMonth = Integer.parseInt(Mesyuarat_Tarikh.substring(3, 5));
        		}
        		if (FrmModelMesyuarat.isNumeric(Mesyuarat_Tarikh.substring(0, 2))) {
        			eventDay = Integer.parseInt(Mesyuarat_Tarikh.substring(0, 2));
        		}
                event.put("userId", user_id);
                event.put("eventId", Mesyuarat_Tajuk);
                event.put("eventText", Mesyuarat_Tajuk);
                event.put("viewScope", "public");

                // add by Rizuan
                Boolean IDMESYAUARAT_EXIST = modelMesyuarat.checkMesyuaratDiCalendar(ID_MESYUARAT);
                
                if (IDMESYAUARAT_EXIST == true)
            	{
                	updateEvent(eventYear, eventMonth, eventDay, event, ID_MESYUARAT);
            	}
                else
                {
                    //EventData.addEvent(eventYear, eventMonth, eventDay, event);
                    addEvent(eventYear, eventMonth, eventDay, event, ID_MESYUARAT);//modified by cipon   
                }
                
                modelMesyuarat.updateStatusProses(ID_MESYUARAT, "5");
                
                if ("tempahMakanan".equalsIgnoreCase(submit)) {
                	Mesyuarat_TempahMakananPemohon = getParam("Mesyuarat_TempahMakananPemohon");
                    Mesyuarat_TempahMakananBilAhli = getParam("Mesyuarat_TempahMakananBilAhli");
                    Mesyuarat_TempahMakananMakanan = getParam("Mesyuarat_TempahMakananMakanan");
                    Mesyuarat_TempahMakananMinuman = getParam("Mesyuarat_TempahMakananMinuman");
                    modelMesyuarat.saveMesyuaratTempahanMakanan(ID_MESYUARAT, Mesyuarat_TempahMakananPemohon, Mesyuarat_TempahMakananBilAhli, Mesyuarat_TempahMakananMakanan, Mesyuarat_TempahMakananMinuman);
                }
    		}
    		
    		}
        
        
        else if ("emailminit".equalsIgnoreCase(actionx)) {
         	//ready sop
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
    			vm = "app/pfd/frmMesyuaratSelesai.jsp";
    			this.context.put("idNegeri", user_negeri);

        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			modelMesyuarat.emailminit(ID_MESYUARAT,h); 
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		list = modelMesyuarat.getListMesyuaratKehadiran(ID_MESYUARAT);
        		context.put("List_AhliMesyuarat", list);
        		//list.clear();

        		vData = modelMesyuarat.getListMesyuaratMinit(ID_MESYUARAT);
        		context.put("List_Minit", vData);
        		//vData.clear();
        		
        		// add to calendar event
        		int eventYear = 0, eventMonth = 0, eventDay = 0;
        		//String user_id = (String)session.getAttribute("_ekptg_user_id");
        		Hashtable event = new Hashtable();
        		if (FrmModelMesyuarat.isNumeric(Mesyuarat_Tarikh.substring(6, 10))) {
        			eventYear = Integer.parseInt(Mesyuarat_Tarikh.substring(6, 10));
        		}
        		if (FrmModelMesyuarat.isNumeric(Mesyuarat_Tarikh.substring(3, 5))) {
        			eventMonth = Integer.parseInt(Mesyuarat_Tarikh.substring(3, 5));
        		}
        		if (FrmModelMesyuarat.isNumeric(Mesyuarat_Tarikh.substring(0, 2))) {
        			eventDay = Integer.parseInt(Mesyuarat_Tarikh.substring(0, 2));
        		}
                event.put("userId", user_id);
                event.put("eventId", Mesyuarat_Tajuk);
                event.put("eventText", Mesyuarat_Tajuk);
                event.put("viewScope", "public");

                // add by Rizuan
                Boolean IDMESYAUARAT_EXIST = modelMesyuarat.checkMesyuaratDiCalendar(ID_MESYUARAT);
                
                if (IDMESYAUARAT_EXIST == true)
            	{
                	updateEvent(eventYear, eventMonth, eventDay, event, ID_MESYUARAT);
            	}
                else
                {
                    //EventData.addEvent(eventYear, eventMonth, eventDay, event);
                    addEvent(eventYear, eventMonth, eventDay, event, ID_MESYUARAT);//modified by cipon   
                }
                
                modelMesyuarat.updateStatusProses(ID_MESYUARAT, "5");
                
                if ("tempahMakanan".equalsIgnoreCase(submit)) {
                	Mesyuarat_TempahMakananPemohon = getParam("Mesyuarat_TempahMakananPemohon");
                    Mesyuarat_TempahMakananBilAhli = getParam("Mesyuarat_TempahMakananBilAhli");
                    Mesyuarat_TempahMakananMakanan = getParam("Mesyuarat_TempahMakananMakanan");
                    Mesyuarat_TempahMakananMinuman = getParam("Mesyuarat_TempahMakananMinuman");
                    modelMesyuarat.saveMesyuaratTempahanMakanan(ID_MESYUARAT, Mesyuarat_TempahMakananPemohon, Mesyuarat_TempahMakananBilAhli, Mesyuarat_TempahMakananMakanan, Mesyuarat_TempahMakananMinuman);
                }
    		}
    		
    		}
        
        
        else if ("daftarAhli".equalsIgnoreCase(actionx)) {
    		// initial VM value
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		Boolean canProceed = false;
    		
        	if ("1".equalsIgnoreCase(PrevPage)) {
        		int MASA_DARI = -1, MASA_HINGGA = -1;
        		if (!"".equalsIgnoreCase(Mesyuarat_Tarikh) && !"".equalsIgnoreCase(SOC_LOKASI) && !"".equalsIgnoreCase(Mesyuarat_Dari) && !"".equalsIgnoreCase(Mesyuarat_Hingga)) {
        			if (FrmModelMesyuarat.isNumeric(Mesyuarat_Dari.substring(0, 2))) {
        				MASA_DARI = Integer.parseInt(Mesyuarat_Dari.substring(0, 2));
        			}
        			if (FrmModelMesyuarat.isNumeric(Mesyuarat_Hingga.substring(0, 2))) {
        				MASA_HINGGA = Integer.parseInt(Mesyuarat_Hingga.substring(0, 2));
        			}
	        		if (modelMesyuarat.checkLocationAvailable(ID_MESYUARAT, SOC_LOKASI, Mesyuarat_Tarikh, MASA_DARI, MASA_HINGGA)) {
		            	// kalau datang dari page daftar, save dulu
	        			if ("1".equalsIgnoreCase(statusProses)) {
	        				statusProses = "2";
	        			}
//	        			if("16".equalsIgnoreCase(user_negeri)){
	        				ID_MESYUARAT = modelMesyuarat.saveMesyuaratData(ID_MESYUARAT, Mesyuarat_Bil, Mesyuarat_NoFail, Mesyuarat_Tajuk, Mesyuarat_Kategori, Mesyuarat_Tarikh, Mesyuarat_Dari, Mesyuarat_Hingga, SOC_SEKSYEN, SOC_UNIT, SOC_LOKASI, SOC_STATUS, Mesyuarat_NoFailDiperlukan, Mesyuarat_DisediakanOleh, Mesyuarat_DisemakOleh, Mesyuarat_DisahkanOleh, Mesyuarat_Catatan, "2",session, user_negeri, user_id);
//	        			}
//	        			else{
//	        				ID_MESYUARAT = modelMesyuarat.saveMesyuaratData(ID_MESYUARAT, Mesyuarat_Bil, Mesyuarat_NoFail, Mesyuarat_Tajuk, Mesyuarat_Kategori, Mesyuarat_Tarikh, Mesyuarat_Dari, Mesyuarat_Hingga, SOC_SEKSYEN, SOC_UNIT, SOC_LOKASI, SOC_STATUS, Mesyuarat_NoFailDiperlukan, Mesyuarat_DisediakanOleh, Mesyuarat_DisemakOleh, Mesyuarat_DisahkanOleh, Mesyuarat_Catatan, "2",session, user_negeri);
//	        			}
		        		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
		        			modelMesyuarat.updateStatusProses(ID_MESYUARAT, "2");
		        			canProceed = true;
		        		}
	        		} else {
	        			actionx = "daftarMesyuarat";
	        			locIsUnavailable = true;
	        			RO_NoFail = "";
	        	        Mesyuarat_Lokasi = SOC_LOKASI;
	        	        Mesyuarat_Seksyen = SOC_SEKSYEN;
	        	        Mesyuarat_Unit = SOC_UNIT;
	        	        Mesyuarat_Negeri = SOC_NEGERI;
	        	        Mesyuarat_Status = SOC_STATUS;	        	        
	        		}
        		} else {
	        		ID_MESYUARAT = modelMesyuarat.saveMesyuaratData(ID_MESYUARAT, Mesyuarat_Bil, Mesyuarat_NoFail, Mesyuarat_Tajuk, Mesyuarat_Kategori, Mesyuarat_Tarikh, Mesyuarat_Dari, Mesyuarat_Hingga, SOC_SEKSYEN, SOC_UNIT, SOC_LOKASI, SOC_STATUS, Mesyuarat_NoFailDiperlukan, Mesyuarat_DisediakanOleh, Mesyuarat_DisemakOleh, Mesyuarat_DisahkanOleh, Mesyuarat_Catatan, "2",session,user_negeri,user_id);
	        		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
	        			canProceed = true;
	        		}
        		}
        	} else if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
        		// terus buat tambah ahli
        		canProceed = true;
        	}
        	
        	if (canProceed) {
    			// success save, boleh proceed dgn tambah ahli
    			vm = "app/pfd/frmMesyuaratAhli.jsp";
    			this.context.put("idNegeri", user_negeri);
    			
        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		if (!"".equalsIgnoreCase(ID_TEMPLATE)) {
        			modelMesyuarat.saveAhliMesyuaratTemplateToMesyuarat(ID_TEMPLATE, ID_MESYUARAT);
        		}
        		list = modelMesyuarat.getAhliMesyuaratList(ID_MESYUARAT, false);
        		context.put("List_CarianAhliMesyuarat", list);
        		list = modelMesyuarat.getAhliMesyuaratList(ID_MESYUARAT, true);
        		context.put("List_CarianUrusetia", list);
	
        		if (Ahli_Kategori == "2"){
        			Ahli_Kategori2 = "checked=\"checked\"";        			
        		}else if(Ahli_Kategori == "3"){
        			Ahli_Kategori3 = "checked=\"checked\"";        			
        		}else{
        			Ahli_Kategori1 = "checked=\"checked\"";        			
        		}

        		Nama_Pegawai = "";
    			Ahli_Nama = "";
    			Ahli_Agensi = "";
    			Ahli_Jawatan = "";
    			txtAlamatAhli = "";
    			Ahli_Email = "";
    			Ahli_NoTelefon = "";
    			Ahli_NoFaks = "";
    			Ahli_Negeri = "";
    			Ahli_Seksyen = "";
    			Ahli_Unit = "";
    			Ahli_Peranan = "";
    			ID_AHLI = "";
        	}
        } else if ("tambahAhli".equalsIgnoreCase(actionx)) {
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		
    		String check_pengerusi = "";
    		String check_setiausaha = "";
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
    			vm = "app/pfd/frmMesyuaratAhli.jsp";
    			this.context.put("idNegeri", user_negeri);
    			
        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		Boolean doSave = true;
        	
        		
        		if ("1".equalsIgnoreCase(SOC_PERANAN)) {
        			if (!modelMesyuarat.havePengerusi(ID_MESYUARAT, ID_AHLI)) {
        				doSave = true;
        				this.context.put("check_pengerusi","yes");
        				check_pengerusi = "yes";
        			}
        			else
        			{
        				doSave = false;
        				this.context.put("check_pengerusi","no");
        				check_pengerusi = "no";
        			}
        		}
        		else if ("3".equalsIgnoreCase(SOC_PERANAN)) {
        			if (!modelMesyuarat.haveSetiausaha(ID_MESYUARAT, ID_AHLI)) {
        				doSave = true;
        				this.context.put("check_setiausaha","yes");
        				check_setiausaha = "yes";
        				
        			}
        			else
        			{
        				doSave = false;
        				this.context.put("check_setiausaha","no");
        				check_setiausaha = "no";
        			}

        		}
        		
        		
        		
        		// save data ahli ni
        		if(doSave==true){
    				modelMesyuarat.saveAhliMesyuaratData(ID_MESYUARAT, ID_AHLI, Ahli_Kategori, SOC_PEGAWAI, Ahli_Nama, Ahli_Agensi, "", SOC_NEGERI, SOC_SEKSYEN, SOC_UNIT, Ahli_Jawatan, Ahli_Email, Ahli_NoTelefon, Ahli_NoFaks, SOC_PERANAN,txtAlamatAhli);
        		}
    			// kosongkan fields
        		list = modelMesyuarat.getAhliMesyuaratList(ID_MESYUARAT, false);
        		context.put("List_CarianAhliMesyuarat", list);
        		list = modelMesyuarat.getAhliMesyuaratList(ID_MESYUARAT, true);
        		context.put("List_CarianUrusetia", list);
        		
    			actionx = "daftarAhli";
        	}
    		
    		Ahli_Kategori = "1";
			Ahli_Kategori1 = "checked=\"checked\"";
			Ahli_Nama = "";
			Ahli_Agensi = "";
			Ahli_Jawatan = "";
			txtAlamatAhli = "";
			Ahli_Pegawai = "";
			Ahli_Email = "";
			Ahli_NoTelefon = "";
			Ahli_NoFaks = "";
			Ahli_Negeri = "";
			Ahli_Seksyen = "";
			Ahli_Unit = "";
			Ahli_Peranan = "";
    		
    		
        } else if ("viewAhli".equalsIgnoreCase(actionx)) {
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		
    		System.out.println("VIEW AHLI ID_MESYUARAT :"+ID_MESYUARAT);
    		System.out.println("VIEW AHLI ID_AHLI :"+ID_AHLI);
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT) && !"".equalsIgnoreCase(ID_AHLI)) {
    			vm = "app/pfd/frmMesyuaratAhli.jsp";
    			this.context.put("idNegeri", user_negeri);

        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
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
        		vData = modelMesyuarat.getAhliMesyuaratData(ID_MESYUARAT, ID_AHLI);
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
        		    txtAlamatAhli = (String) h.get("ALAMAT_AHLI");
        			
        			h.clear();
        			getAhliMesyuaratDataUrussetia.clear();
        			
        			//System.out.println("CHECK Ahli_Kategori :"+Ahli_Kategori);
        		}
        		}
    		
        		
        			if ("1".equalsIgnoreCase(jenis_kategori)) {
        				Ahli_Kategori = "1";
        				Ahli_Kategori1 = "checked=\"checked\"";
        				Ahli_Kategori2 = "";
        				Ahli_Kategori3 = "";
        				if ("16".equalsIgnoreCase(negeri_user)) {
        		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", id_user, SOC_SEKSYEN, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai');\"",submit,actionx);
        		        	context.put("Ahli_Pegawai", Ahli_Pegawai);
                		}		        
        		        else  {		        	
        		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiByNegeri(ID_MESYUARAT,"SOC_PEGAWAI", id_user, negeri_user, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai1');\"",submit,actionx);
        		        	context.put("Ahli_Pegawai", Ahli_Pegawai);
        		        }
        			} else if ("2".equalsIgnoreCase(jenis_kategori)) {
        				Ahli_Kategori = "2";
        				Ahli_Kategori1 = "";
        				Ahli_Kategori2 = "checked=\"checked\"";
        				Ahli_Kategori3 = "";
        			} else if ("3".equalsIgnoreCase(jenis_kategori)) {
        				Ahli_Kategori = "3";
        				Ahli_Kategori1 = "";
        				Ahli_Kategori2 = "";
        				Ahli_Kategori3 = "checked=\"checked\"";
        				if ("16".equalsIgnoreCase(negeri_user)) {
        		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", id_user, SOC_SEKSYEN, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai2');\"",submit,actionx);
        		        	context.put("Ahli_Pegawai", Ahli_Pegawai);
                		}		        
        		        else  {		        	
        		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiByNegeri(ID_MESYUARAT,"SOC_PEGAWAI", id_user, negeri_user, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai3');\"",submit,actionx);
        		        	context.put("Ahli_Pegawai", Ahli_Pegawai);
        		        }
        			}
        			else
        			{
        				Ahli_Kategori = "1";
        				Ahli_Kategori1 = "checked=\"checked\"";
        				Ahli_Kategori2 = "";
        				Ahli_Kategori3 = "";
        				if ("16".equalsIgnoreCase(negeri_user)) {
        		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", id_user, SOC_SEKSYEN, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai');\"",submit,actionx);
        		        	context.put("Ahli_Pegawai", Ahli_Pegawai);
                		}		        
        		        else  {		        	
        		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiByNegeri(ID_MESYUARAT,"SOC_PEGAWAI", id_user, negeri_user, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai1');\"",submit,actionx);
        		        	context.put("Ahli_Pegawai", Ahli_Pegawai);
        		        }
        			}
        		
          		

        		list = modelMesyuarat.getAhliMesyuaratList(ID_MESYUARAT, false);
        		context.put("List_CarianAhliMesyuarat", list);
        		list = modelMesyuarat.getAhliMesyuaratList(ID_MESYUARAT, true);
        		context.put("List_CarianUrusetia", list);
        		
        		
        		
        		
        		
        		
        		
        		
        		
        		
    		}
        } else if ("hapusAhli".equalsIgnoreCase(actionx)) {
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT) && !"".equalsIgnoreCase(ID_AHLI)) {
    			vm = "app/pfd/frmMesyuaratAhli.jsp";
    			this.context.put("idNegeri", user_negeri);
    			
        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();

        		// delete data ahli ni
    			modelMesyuarat.deleteAhliMesyuaratData(ID_MESYUARAT, ID_AHLI);
    			
    			// kosongkan fields
        		list = modelMesyuarat.getAhliMesyuaratList(ID_MESYUARAT, false);
        		context.put("List_CarianAhliMesyuarat", list);
        		list = modelMesyuarat.getAhliMesyuaratList(ID_MESYUARAT, true);
        		context.put("List_CarianUrusetia", list);
        		Ahli_Kategori = "1";
    			Ahli_Kategori1 = "checked=\"checked\"";
    			Ahli_Nama = "";
    			Ahli_Agensi = "";
    			Ahli_Jawatan = "";
    			Ahli_Email = "";
    			Ahli_NoTelefon = "";
    			txtAlamatAhli = "";
    			Ahli_NoFaks = "";
    			Ahli_Negeri = "";
    			Ahli_Seksyen = "";
    			Ahli_Unit = "";
    			Ahli_Peranan = "";
    			actionx = "daftarAhli";
    		}        	
        } else if ("daftarMinit".equalsIgnoreCase(actionx)) {
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
    			vm = "app/pfd/frmMesyuaratMinit.jsp";
    			this.context.put("idNegeri", user_negeri);
    			
        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();

        		if ("0".equalsIgnoreCase(selectedTab)) {
	        		list = modelMesyuarat.getAhliMesyuaratKehadiran(ID_MESYUARAT);
	        		context.put("List_CarianAhliMesyuarat", list);
	        	} else if ("1".equalsIgnoreCase(selectedTab)) {
	        		
	        	} else if ("2".equalsIgnoreCase(selectedTab)) {
	        		
	        	} else if ("3".equalsIgnoreCase(selectedTab)) {
	        		
	        	}
        		modelMesyuarat.updateStatusProses(ID_MESYUARAT, "3");
    		}
        } else if ("simpanKehadiran".equalsIgnoreCase(actionx)) {
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
    			vm = "app/pfd/frmMesyuaratMinit.jsp";
    			this.context.put("idNegeri", user_negeri);

        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		Vector vSend = new Vector();
        		String RS_IDAHLI = "", RS_HADIR = "", RS_WAKIL = "";
        		Hashtable hSend = null;
        		vData = modelMesyuarat.getAhliMesyuaratListID(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
            		int iDataCount = 0;
        			for (iDataCount = 0; iDataCount < vData.size(); iDataCount++) {
        				h = (Hashtable) vData.elementAt(iDataCount);
        				RS_IDAHLI = (String) h.get("IDAhli");
        				RS_HADIR = getParam("Hadir_" + RS_IDAHLI);
        				RS_WAKIL = getParam("Wakil_" + RS_IDAHLI);
        				
        				hSend = new Hashtable();
        				hSend.put("ID", RS_IDAHLI);
        				hSend.put("HADIR", RS_HADIR);
        				hSend.put("WAKIL", RS_WAKIL);
        				vSend.add(hSend);
        			}
        			
        			// send vector ni ke model
        			modelMesyuarat.simpanKehadiran(vSend);
        		}
        		h.clear();
        		vData.clear();
        	
        		list = modelMesyuarat.getAhliMesyuaratKehadiran(ID_MESYUARAT);
        		context.put("List_CarianAhliMesyuarat", list);

        		selectedTab = "0";    		
    		}    		
        } else if ("daftarMesyuaratAgenda".equalsIgnoreCase(actionx)) {
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
    			vm = "app/pfd/frmMesyuaratMinit.jsp";
    			this.context.put("idNegeri", user_negeri);

        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		list = modelMesyuarat.getAgendaMesyuaratList(ID_MESYUARAT);
        		context.put("List_CarianMinit", list);
        		
        		selectedTab = "1";
    		}
        } else if ("viewMesyuaratAgenda".equalsIgnoreCase(actionx)) {
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
    			vm = "app/pfd/frmMesyuaratMinit.jsp";
    			this.context.put("idNegeri", user_negeri);

        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		vData = modelMesyuarat.getAgendaMesyuaratData(ID_AGENDA);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			Agenda_No = (String) h.get("Agenda_No");
        			Agenda_Tajuk = (String) h.get("Agenda_Tajuk");
        			Agenda_Agenda = (String) h.get("Agenda_Agenda");
        		}
        		h.clear();
        		vData.clear();

        		list = modelMesyuarat.getAgendaMesyuaratList(ID_MESYUARAT);
        		context.put("List_CarianMinit", list);
        		
        		selectedTab = "1";
    		}
        } else if ("simpanMesyuaratAgenda".equalsIgnoreCase(actionx)) {
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
    			vm = "app/pfd/frmMesyuaratMinit.jsp";
    			this.context.put("idNegeri", user_negeri);

        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		modelMesyuarat.saveAgendaMesyuaratData(ID_MESYUARAT, ID_AGENDA, Agenda_No, Agenda_Tajuk, Agenda_Agenda);
        		ID_AGENDA = "";
        		Agenda_No = "";
        		Agenda_Tajuk = "";
        		Agenda_Agenda = "";
        		
        		list = modelMesyuarat.getAgendaMesyuaratList(ID_MESYUARAT);
        		context.put("List_CarianMinit", list);
        		
        		selectedTab = "1";
        		actionx = "daftarMesyuaratAgenda";
    		}
        } else if ("hapusMesyuaratAgenda".equalsIgnoreCase(actionx)) {
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT) && !"".equalsIgnoreCase(ID_AGENDA)) {
    			vm = "app/pfd/frmMesyuaratMinit.jsp";
    			this.context.put("idNegeri", user_negeri);

        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		modelMesyuarat.deleteAgendaMesyuaratData(ID_MESYUARAT, ID_AGENDA);
        		ID_AGENDA = "";
        		Agenda_No = "";
        		Agenda_Tajuk = "";
        		Agenda_Agenda = "";
        		
        		list = modelMesyuarat.getAgendaMesyuaratList(ID_MESYUARAT);
        		context.put("List_CarianMinit", list);
        		
        		selectedTab = "1";
        		actionx = "daftarMesyuaratAgenda";
    		}
        }  else if ("daftarMesyuaratMinit".equalsIgnoreCase(actionx)) {
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
    			vm = "app/pfd/frmMesyuaratMinit.jsp";

        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		Minit_Agenda = modelMesyuarat.getSOCAgendaMesyuarat("SOC_AGENDA", ID_AGENDA, ID_MESYUARAT, "", "");
        		
	         	listPegawai(session);
	    		listPegawai = modelMesyuarat.getListPegawai();
	    		this.context.put("SenaraiPegawai",listPegawai);
        		
        		list = modelMesyuarat.getMinitMesyuaratList(ID_MESYUARAT);
        		context.put("List_CarianMinit", list);
        		
        		selectedTab = "2";
    		}
        } else if ("viewMesyuaratMinit".equalsIgnoreCase(actionx)) {
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		String idPegawaiTindakan = "";
    		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
    			vm = "app/pfd/frmMesyuaratMinit.jsp";
    			this.context.put("idNegeri", user_negeri);

        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		vData = modelMesyuarat.getMinitMesyuaratData(ID_MINIT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			Minit_No = (String) h.get("Minit_No");
        			Minit_Tajuk = (String) h.get("Minit_Tajuk");
        			Minit_Minit = (String) h.get("Minit_Minit");
        			Minit_Makluman = (String) h.get("Minit_Makluman");
        			Minit_Tindakan = (String) h.get("Minit_Tindakan");
        			Minit_Maklumbalas = (String) h.get("Minit_Maklumbalas");
        			ID_AGENDA = (String) h.get("Minit_Agenda");
        			idPegawaiTindakan = (String) h.get("idPegawaiTindakan");
        		}
        		h.clear();
        		vData.clear();
        		Minit_Agenda = modelMesyuarat.getSOCAgendaMesyuarat("SOC_AGENDA", ID_AGENDA, ID_MESYUARAT, "", "");

        		listPegawai(session);
	    		listPegawai = modelMesyuarat.getListPegawai();
	    		this.context.put("SenaraiPegawai",listPegawai);
	    		
	    		Vector listPegawai = modelMesyuarat.getDataPegawaiMinit(ID_MINIT);
	    		Hashtable x ;
	    		if (listPegawai.size() > 0) {
        			x = (Hashtable)listPegawai.elementAt(0);
         			this.context.put("selectidorang",x.get("user_id"));
    			 } else {
    				 this.context.put("selectidorang","0"); 
    			 }
        		
        		list = modelMesyuarat.getMinitMesyuaratList(ID_MESYUARAT);
        		context.put("List_CarianMinit", list);
        		
        		if ("1".equalsIgnoreCase(Minit_Makluman)) {
        			Minit_Makluman = "checked=\"checked\"";
        		}
        		
        		selectedTab = "2";
    		}
        } else if ("simpanMesyuaratMinit".equalsIgnoreCase(actionx)) {
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
    			vm = "app/pfd/frmMesyuaratMinit.jsp";
    			this.context.put("idNegeri", user_negeri);

        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		String idPegawaiTindakan = getParam("socPegawai");
				modelMesyuarat.saveMinitMesyuaratData(ID_MESYUARAT, SOC_AGENDA, ID_MINIT, Minit_No, Minit_Tajuk, Minit_Minit, Minit_Tindakan, Minit_Makluman, Minit_Maklumbalas, idPegawaiTindakan );
        		ID_MINIT = "";
        		Minit_No = "";
        		Minit_Tajuk = "";
        		Minit_Minit = "";
        		Minit_Tindakan = "";
        		Minit_Makluman = "";
        		Minit_Maklumbalas = "";
        		Minit_Agenda = modelMesyuarat.getSOCAgendaMesyuarat("SOC_AGENDA", ID_AGENDA, ID_MESYUARAT, "", "");
        		
           		listPegawai(session);
	    		listPegawai = modelMesyuarat.getListPegawai();
	    		this.context.put("SenaraiPegawai",listPegawai);
	    		
	    		Vector listPegawai = modelMesyuarat.getDataPegawaiMinit(ID_MINIT);
	    		Hashtable x ;
	    		if (listPegawai.size() > 0) {
        			x = (Hashtable)listPegawai.elementAt(0);
         			this.context.put("selectidorang",x.get("user_id"));
    			 } else {
    				 this.context.put("selectidorang","0"); 
    			 }
        		
        		list = modelMesyuarat.getMinitMesyuaratList(ID_MESYUARAT);
        		context.put("List_CarianMinit", list);
        		
        		selectedTab = "2";
        		actionx = "daftarMesyuaratMinit";
    		}
        } else if ("hapusMesyuaratMinit".equalsIgnoreCase(actionx)) {
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT) && !"".equalsIgnoreCase(ID_MINIT)) {
    			vm = "app/pfd/frmMesyuaratMinit.jsp";
    			this.context.put("idNegeri", user_negeri);

        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		modelMesyuarat.deleteMinitMesyuaratData(ID_MESYUARAT, ID_MINIT);
        		ID_MINIT = "";
        		Minit_No = "";
        		Minit_Tajuk = "";
        		Minit_Minit = "";
        		Minit_Tindakan = "";
        		Minit_Maklumbalas = "";
        		Minit_Makluman = "";
        		Minit_Agenda = modelMesyuarat.getSOCAgendaMesyuarat("SOC_AGENDA", ID_AGENDA, ID_MESYUARAT, "", "");
        		
        		list = modelMesyuarat.getMinitMesyuaratList(ID_MESYUARAT);
        		context.put("List_CarianMinit", list);
        		
        		selectedTab = "2";
        		actionx = "daftarMesyuaratMinit";
    		}
        }   else if ("daftarMesyuaratSubMinit".equalsIgnoreCase(actionx)) {
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
    			vm = "app/pfd/frmMesyuaratMinit.jsp";
    			this.context.put("idNegeri", user_negeri);

        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		SubMinit_Agenda = modelMesyuarat.getSOCAgendaMesyuarat("SOC_AGENDA", ID_AGENDA, ID_MESYUARAT, "", " onchange=\"doChangeSOC('changeAgenda')\"");
        		SubMinit_Minit = modelMesyuarat.getSOCMinitMesyuarat("SOC_MINIT", ID_MINIT, ID_AGENDA, "", "");
        		
        		listPegawai(session);
	    		listPegawai = modelMesyuarat.getListPegawai();
	    		this.context.put("SenaraiPegawai",listPegawai);
        		
        		list = modelMesyuarat.getSubMinitMesyuaratList(ID_MESYUARAT);
        		context.put("List_CarianMinit", list);
        		
        		selectedTab = "3";
    		}
        } else if ("viewMesyuaratSubMinit".equalsIgnoreCase(actionx)) {
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
    			vm = "app/pfd/frmMesyuaratMinit.jsp";
    			this.context.put("idNegeri", user_negeri);

        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		vData = modelMesyuarat.getSubMinitMesyuaratData(ID_SUBMINIT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			SubMinit_No = (String) h.get("SubMinit_No");
        			SubMinit_Tajuk = (String) h.get("SubMinit_Tajuk");
        			SubMinit_SubMinit = (String) h.get("SubMinit_SubMinit");
        			SubMinit_Tindakan = (String) h.get("SubMinit_Tindakan");
        			SubMinit_Maklumbalas = (String) h.get("SubMinit_Maklumbalas");
        			SubMinit_Makluman = (String) h.get("SubMinit_Makluman");
        			ID_AGENDA = (String) h.get("SubMinit_Agenda");
        			ID_MINIT = (String) h.get("SubMinit_Minit");
        		}
        		h.clear();
        		vData.clear();
        		SubMinit_Agenda = modelMesyuarat.getSOCAgendaMesyuarat("SOC_AGENDA", ID_AGENDA, ID_MESYUARAT, "", " onchange=\"doChangeSOC('changeAgenda')\"");
        		SubMinit_Minit = modelMesyuarat.getSOCMinitMesyuarat("SOC_MINIT", ID_MINIT, ID_AGENDA, "", "");

        		listPegawai(session);
	    		listPegawai = modelMesyuarat.getListPegawai();
	    		this.context.put("SenaraiPegawai",listPegawai);
	    		
	    		Vector listPegawai = modelMesyuarat.getDataPegawaiSubMinit(ID_SUBMINIT);
	    		Hashtable x ;
	    		if (listPegawai.size() > 0) {
        			x = (Hashtable)listPegawai.elementAt(0);
         			this.context.put("selectidorang",x.get("user_id"));
    			 } else {
    				 this.context.put("selectidorang","0"); 
    			 }
        		
        		list = modelMesyuarat.getSubMinitMesyuaratList(ID_MESYUARAT);
        		context.put("List_CarianMinit", list);
        		
        		if ("1".equalsIgnoreCase(SubMinit_Makluman)) {
        			SubMinit_Makluman = "checked=\"checked\"";
        		}
        		
        		selectedTab = "3";
    		}
        } else if ("simpanMesyuaratSubMinit".equalsIgnoreCase(actionx)) {
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
    			vm = "app/pfd/frmMesyuaratMinit.jsp";
    			this.context.put("idNegeri", user_negeri);

        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		String idPegawaiTindakan = getParam("socPegawai");
        		modelMesyuarat.saveSubMinitMesyuaratData(ID_MESYUARAT, SOC_AGENDA, SOC_MINIT, ID_SUBMINIT, SubMinit_No, SubMinit_Tajuk, SubMinit_SubMinit, SubMinit_Tindakan, SubMinit_Makluman,SubMinit_Maklumbalas, idPegawaiTindakan);
        		ID_SUBMINIT = "";
        		SubMinit_No = "";
        		SubMinit_Tajuk = "";
        		SubMinit_SubMinit = "";
        		SubMinit_Tindakan = "";
        		SubMinit_Maklumbalas = "";
        		SubMinit_Makluman = "";
        		SubMinit_Agenda = modelMesyuarat.getSOCAgendaMesyuarat("SOC_AGENDA", ID_AGENDA, ID_MESYUARAT, "", " onchange=\"doChangeSOC('changeAgenda')\"");
        		SubMinit_Minit = modelMesyuarat.getSOCMinitMesyuarat("SOC_MINIT", ID_MINIT, ID_AGENDA, "", "");
        		
        		listPegawai(session);
	    		listPegawai = modelMesyuarat.getListPegawai();
	    		this.context.put("SenaraiPegawai",listPegawai);
	    		
	    		Vector listPegawai = modelMesyuarat.getDataPegawaiSubMinit(ID_SUBMINIT);
	    		Hashtable x ;
	    		if (listPegawai.size() > 0) {
        			x = (Hashtable)listPegawai.elementAt(0);
         			this.context.put("selectidorang",x.get("user_id"));
    			 } else {
    				 this.context.put("selectidorang","0"); 
    			 }
        		
        		list = modelMesyuarat.getSubMinitMesyuaratList(ID_MESYUARAT);
        		context.put("List_CarianMinit", list);
        		
        		selectedTab = "3";
        		actionx = "daftarMesyuaratSubMinit";
    		}
        } else if ("hapusMesyuaratSubMinit".equalsIgnoreCase(actionx)) {
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		this.context.put("idNegeri", user_negeri);
    		
    		System.out.println("ID_SUBMINIT ID_SUBMINIT :"+ID_SUBMINIT);
    		System.out.println("ID_MESYUARAT ID_MESYUARAT :"+ID_MESYUARAT);
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT) && !"".equalsIgnoreCase(ID_SUBMINIT)) {
    			vm = "app/pfd/frmMesyuaratMinit.jsp";
    			this.context.put("idNegeri", user_negeri);

        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		modelMesyuarat.deleteSubMinitMesyuaratData(ID_MESYUARAT, ID_SUBMINIT);
        		ID_SUBMINIT = "";
        		SubMinit_No = "";
        		SubMinit_Tajuk = "";
        		SubMinit_SubMinit = "";
        		SubMinit_Tindakan = "";
        		SubMinit_Maklumbalas = "";
        		SubMinit_Makluman = "";
        		SubMinit_Agenda = modelMesyuarat.getSOCAgendaMesyuarat("SOC_AGENDA", ID_AGENDA, ID_MESYUARAT, "", " onchange=\"doChangeSOC('changeAgenda')\"");
        		SubMinit_Minit = modelMesyuarat.getSOCMinitMesyuarat("SOC_MINIT", ID_MINIT, ID_AGENDA, "", "");
        		
        		list = modelMesyuarat.getSubMinitMesyuaratList(ID_MESYUARAT);
        		context.put("List_CarianMinit", list);
        		
        		selectedTab = "3";
        		actionx = "daftarMesyuaratSubMinit";
    		}
        } else if ("selesaiMesyuarat".equalsIgnoreCase(actionx)) {
    		vm = "app/pfd/frmMesyuaratDaftar.jsp";
    		
    		if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
    			vm = "app/pfd/frmMesyuaratSelesai.jsp";
    			this.context.put("idNegeri", user_negeri);

        		vData = modelMesyuarat.getMesyuaratData(ID_MESYUARAT);
        		if (!vData.isEmpty()) {
        			h = (Hashtable) vData.get(0);
        			populateMesyuaratData(h);
        		}
        		h.clear();
        		vData.clear();
        		
        		list = modelMesyuarat.getListMesyuaratKehadiran(ID_MESYUARAT);
        		context.put("List_AhliMesyuarat", list);
        		//list.clear();

        		vData = modelMesyuarat.getListMesyuaratMinit(ID_MESYUARAT);
        		context.put("List_Minit", vData);
        		//vData.clear();
        		
        		// add to calendar event
        		int eventYear = 0, eventMonth = 0, eventDay = 0;
        		//String user_id = (String)session.getAttribute("_ekptg_user_id");
        		Hashtable event = new Hashtable();
        		if (FrmModelMesyuarat.isNumeric(Mesyuarat_Tarikh.substring(6, 10))) {
        			eventYear = Integer.parseInt(Mesyuarat_Tarikh.substring(6, 10));
        		}
        		if (FrmModelMesyuarat.isNumeric(Mesyuarat_Tarikh.substring(3, 5))) {
        			eventMonth = Integer.parseInt(Mesyuarat_Tarikh.substring(3, 5));
        		}
        		if (FrmModelMesyuarat.isNumeric(Mesyuarat_Tarikh.substring(0, 2))) {
        			eventDay = Integer.parseInt(Mesyuarat_Tarikh.substring(0, 2));
        		}
                event.put("userId", user_id);
                event.put("eventId", Mesyuarat_Tajuk);
                event.put("eventText", Mesyuarat_Tajuk);
                event.put("viewScope", "public");
                
                // add by Rizuan
                Boolean IDMESYAUARAT_EXIST = modelMesyuarat.checkMesyuaratDiCalendar(ID_MESYUARAT);
                
                if (IDMESYAUARAT_EXIST == true)
            	{
                	updateEvent(eventYear, eventMonth, eventDay, event, ID_MESYUARAT);
            	}
                else
                {
                    //EventData.addEvent(eventYear, eventMonth, eventDay, event);
                    addEvent(eventYear, eventMonth, eventDay, event, ID_MESYUARAT);//modified by cipon   
                }

                modelMesyuarat.updateStatusProses(ID_MESYUARAT, "5");
                
                if ("tempahMakanan".equalsIgnoreCase(submit)) {
                	Mesyuarat_TempahMakananPemohon = getParam("Mesyuarat_TempahMakananPemohon");
                    Mesyuarat_TempahMakananBilAhli = getParam("Mesyuarat_TempahMakananBilAhli");
                    Mesyuarat_TempahMakananMakanan = getParam("Mesyuarat_TempahMakananMakanan");
                    Mesyuarat_TempahMakananMinuman = getParam("Mesyuarat_TempahMakananMinuman");
                    modelMesyuarat.saveMesyuaratTempahanMakanan(ID_MESYUARAT, Mesyuarat_TempahMakananPemohon, Mesyuarat_TempahMakananBilAhli, Mesyuarat_TempahMakananMakanan, Mesyuarat_TempahMakananMinuman);
                }
    		}
        } else if ("carianMesyuarat".equalsIgnoreCase(actionx)) {
    		vm = "app/pfd/frmMesyuaratCarian.jsp";
    		this.context.put("idNegeri", user_negeri);
        	
        	String Search_NoFail = getParam("Carian_NoFail");
        	String Search_Tajuk = getParam("Carian_Tajuk");
        	String Search_Kategori = getParam("Carian_Kategori");
        	String Search_Urusetia = getParam("Carian_Urusetia");
        	String Search_Lokasi = getParam("Carian_Lokasi");
        	String Search_Tarikh = getParam("Carian_Tarikh");
        	
        	list = modelMesyuarat.getListMesyuarat(Search_NoFail, Search_Tajuk, Search_Kategori, Search_Urusetia, Search_Lokasi, Search_Tarikh,id_seksyen, id_negeri, user_id);
        	context.put("ListMesyuarat", list);
	        context.put("selectSeksyen", HTML.SelectSeksyen("Carian_Urusetia", Long.parseLong("0"), ""));
	        context.put("selectLokasi", HTML.SelectLokasiMesyuarat("Carian_Lokasi", Long.parseLong("0"), ""));
    		Page_Carian = true;
    		
    		setupPage(session, actionx, list);
    		context.put("pagingTitle", "title");
  	 	    prepareItemForDisplay(session,list,10,"first");
        } else {
    		vm = "app/pfd/frmMesyuaratCarian.jsp";
    		this.context.put("idNegeri", user_negeri);
        	context.put("ListMesyuarat", "");
        	if("16".equalsIgnoreCase(user_negeri)){
        		list = modelMesyuarat.getListMesyuarat("", "", "", "", "", "",id_seksyen,id_negeri, user_id);
        	}
        	else{
        		list = modelMesyuarat.getListMesyuaratNegeri("", "", "", "", "", "","",id_negeri, user_id);
        	}
        	
        	this.context.put("ListMesyuarat", list);
    		setupPage(session, actionx, list);
        	//context.put("pagingTitle", "");
  	 	    prepareItemForDisplay(session,list,10,"first");
    		Page_Carian = true;
        }
        
        if (Page_Carian) {
	        context.put("selectSeksyen", HTML.SelectSeksyen("Carian_Urusetia", Long.parseLong("0"), ""));
	        context.put("selectLokasi", HTML.SelectLokasiMesyuarat("Carian_Lokasi", Long.parseLong("0"), ""));
	    	context.put("ID_MESYUARAT", ID_MESYUARAT);
    		setupPage(session, actionx, list);
        	//context.put("pagingTitle", "");
        	this.context.put("ListMesyuarat", list);
  	 	    prepareItemForDisplay(session,list,10,"first");
        } else {
	        if ("4".equalsIgnoreCase(Mesyuarat_Status) || "5".equalsIgnoreCase(Mesyuarat_Status)) {
	        	RO_MaklumatMesyuarat = "readonly=\"readonly\"";
	        	hideSaveButton = true;
	        }
	        
	        context.put("Mesyuarat_NoFail", Mesyuarat_NoFail);
	        context.put("Mesyuarat_Bil", Mesyuarat_Bil);
	    	context.put("Mesyuarat_Tajuk", Mesyuarat_Tajuk);
	    	context.put("Mesyuarat_Kategori", Mesyuarat_Kategori);
	    	context.put("Mesyuarat_Tarikh", Mesyuarat_Tarikh);
	    	context.put("Mesyuarat_Dari", Mesyuarat_Dari);
	    	context.put("Mesyuarat_Hingga", Mesyuarat_Hingga);
	    	if ("daftarMesyuarat".equalsIgnoreCase(actionx)) {
	    		if("16".equalsIgnoreCase(user_negeri)){
	    			context.put("Mesyuarat_Lokasi", modelMesyuarat.getSOCLokasiMesyuarat("SOC_LOKASI", Mesyuarat_Lokasi, RO_MaklumatMesyuarat, id_seksyen,id_negeri));
	    		}
	    		else
	    		{
	    			context.put("Mesyuarat_Lokasi", modelMesyuarat.getSOCLokasiMesyuaratNegeri("SOC_LOKASI", Mesyuarat_Lokasi, RO_MaklumatMesyuarat, Mesyuarat_Unit ,id_negeri));

	    		}
		    	context.put("Mesyuarat_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(Mesyuarat_Seksyen), RO_MaklumatMesyuarat));		    	
		    	context.put("Mesyuarat_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(Mesyuarat_Negeri), RO_MaklumatMesyuarat));
		    	context.put("Mesyuarat_Unit", HTML.SelectUnit("SOC_UNIT", Utils.parseLong(Mesyuarat_Unit), RO_MaklumatMesyuarat));
				   
	    	} else {
	    		if ("2".equalsIgnoreCase(Mesyuarat_Kategori)) {
	    			context.put("Mesyuarat_Kategori", "RUTIN");
	    		} else {
	    			context.put("Mesyuarat_Kategori", "AD-HOC");
	    		}
		    	context.put("Mesyuarat_Lokasi", Mesyuarat_ValueLokasi);
		    	context.put("Mesyuarat_Seksyen", Mesyuarat_ValueSeksyen);
		    	context.put("Mesyuarat_Unit", Mesyuarat_ValueUnit);
		    	context.put("Mesyuarat_Negeri", Mesyuarat_ValueNegeri);
	    	}
	    	if ("selesaiMesyuarat".equalsIgnoreCase(actionx) || "email".equalsIgnoreCase(actionx)) {
	    		context.put("Mesyuarat_Dari", Mesyuarat_ValueDari);
	    		context.put("Mesyuarat_Hingga", Mesyuarat_ValueHingga);
	    		context.put("Mesyuarat_Status", Mesyuarat_ValueStatus);
	    	} else {
	    		context.put("Mesyuarat_Status", modelMesyuarat.getSOCStatusMesyuarat("SOC_STATUS", Mesyuarat_Status));
	    	}
	    	context.put("Mesyuarat_NoFailDiperlukan", Mesyuarat_NoFailDiperlukan);
	    	if ("".equalsIgnoreCase(ID_MESYUARAT)) {
		    	context.put("Mesyuarat_DisediakanOleh", user_name);
		    	}else{
		    		context.put("Mesyuarat_DisediakanOleh", Mesyuarat_DisediakanOleh);
		    	}
	    	context.put("Mesyuarat_DisemakOleh", Mesyuarat_DisemakOleh);
	    	context.put("Mesyuarat_DisahkanOleh", Mesyuarat_DisahkanOleh);
	    	context.put("Mesyuarat_Catatan", Mesyuarat_Catatan);
	    	context.put("Mesyuarat_Masa", Mesyuarat_Masa);
	    	context.put("Mesyuarat_ValueLokasi", Mesyuarat_ValueLokasi);
	    	context.put("Mesyuarat_ValueSeksyen", Mesyuarat_ValueSeksyen);
	    	context.put("Mesyuarat_ValueUnit", Mesyuarat_ValueUnit);
	    	context.put("Mesyuarat_ValueNegeri", Mesyuarat_ValueNegeri);
	    	context.put("Mesyuarat_TempahMakananPemohon", Mesyuarat_TempahMakananPemohon);
	    	context.put("Mesyuarat_TempahMakananBilAhli", Mesyuarat_TempahMakananBilAhli);
	    	context.put("Mesyuarat_TempahMakananMakanan", Mesyuarat_TempahMakananMakanan);
	    	context.put("Mesyuarat_TempahMakananMinuman", Mesyuarat_TempahMakananMinuman);
	    	context.put("Mesyuarat_Kategori", Mesyuarat_Kategori);
	    	
	    	context.put("Ahli_Kategori", Ahli_Kategori);
	    	context.put("Ahli_Kategori1", Ahli_Kategori1);
	    	context.put("Ahli_Kategori2", Ahli_Kategori2);
	    	context.put("Ahli_Kategori3", Ahli_Kategori3);
	    	context.put("Ahli_Nama", Ahli_Nama);
	    	context.put("txtAlamatAhli", txtAlamatAhli);
	    	context.put("Ahli_Agensi", Ahli_Agensi);
	    	context.put("Ahli_Jawatan", Ahli_Jawatan);
	    	context.put("Ahli_Email", Ahli_Email);
	    	context.put("Ahli_NoFaks", Ahli_NoFaks);
	    	context.put("Ahli_NoTelefon", Ahli_NoTelefon);
	    	
	    //	context.put("Ahli_Pegawai", modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", Ahli_Pegawai, Ahli_Seksyen, Ahli_Kategori, RO_MaklumatMesyuarat) );
	    	
	    	System.out.println("Ahli_Pegawai:"+getParam("SOC_PEGAWAI"));
	    	System.out.println("peje123");
	    	
	    	
	    	if(!actionx.equals("viewAhli") && !actionx.equals("tambahAhli"))
	    	{
	    	context.put("Ahli_Pegawai", modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", SOC_PEGAWAI, Ahli_Seksyen, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai');\"",submit,actionx) );
	    	}
	    	
	    	
	    	
	    	
	    	
	    	//context.put("Ahli_Pegawai", modelMesyuarat.getSOCMaklumatPegawai(ID_MESYUARAT,"SOC_PEGAWAI", Ahli_Pegawai, Ahli_Seksyen, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai');\"",submit) );
	    	
	    	
	    	
	    	context.put("Ahli_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(Ahli_Negeri), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeNegeri');\""));
	    	context.put("Ahli_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(Ahli_Seksyen), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeSeksyen');\""));
	    	context.put("Ahli_Negeri3", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(Ahli_Negeri), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeNegeri3');\""));
	    	context.put("Ahli_Seksyen3", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(Ahli_Seksyen), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeSeksyen3');\""));
	    	context.put("Ahli_Unit", HTML.SelectUnit("SOC_UNIT", Utils.parseLong(Ahli_Unit), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeSeksyen3');\""));
	    	context.put("Ahli_Peranan", modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", Ahli_Peranan, Ahli_Kategori, RO_MaklumatMesyuarat));

	    	
	    	
	    	
	    	context.put("Agenda_No", Agenda_No);
	    	context.put("Agenda_Tajuk", Agenda_Tajuk);
	    	context.put("Agenda_Agenda", Agenda_Agenda);
	    	
	    	context.put("Minit_Agenda", Minit_Agenda);
	    	context.put("Minit_No", Minit_No);
	    	context.put("Minit_Tajuk", Minit_Tajuk);
	    	context.put("Minit_Minit", Minit_Minit);
	    	context.put("Minit_Tindakan", Minit_Tindakan);
	    	context.put("Minit_Maklumbalas", Minit_Maklumbalas);
	    	context.put("Minit_Makluman", Minit_Makluman);
	
	    	context.put("SubMinit_Agenda", SubMinit_Agenda);
	    	context.put("SubMinit_Minit", SubMinit_Minit);
	    	context.put("SubMinit_No", SubMinit_No);
	    	context.put("SubMinit_Tajuk", SubMinit_Tajuk);
	    	context.put("SubMinit_SubMinit", SubMinit_SubMinit);
	    	context.put("SubMinit_Tindakan", SubMinit_Tindakan);
	    	context.put("SubMinit_Maklumbalas", SubMinit_Maklumbalas);
	    	context.put("SubMinit_Makluman", SubMinit_Makluman);
	    	
	    	
	    	
	    	
	    	context.put("ID_MESYUARAT", ID_MESYUARAT);
	        context.put("ID_TEMPLATE", ID_TEMPLATE);
	        context.put("ID_AHLI", ID_AHLI);
	        context.put("ID_AGENDA", ID_AGENDA);
	        context.put("ID_MINIT", ID_MINIT);
	        context.put("ID_SUBMINIT", ID_SUBMINIT);
	        
	        context.put("hideSaveButton", hideSaveButton);
	        context.put("locIsUnavailable", locIsUnavailable);
	        context.put("statusProses", statusProses);
	        context.put("RO_NoFail", RO_NoFail);
	        context.put("RO_MaklumatMesyuarat", RO_MaklumatMesyuarat);
	        context.put("selectedTab", selectedTab);
	        context.put("actionx", actionx);
	        
	        System.out.println("peje321 : " + getParam("Ahli_Kategori"));
	        
	        if (!"".equalsIgnoreCase(submit)) {
		        if ("changeSeksyen".equalsIgnoreCase(submit)) {
		        	System.out.println("testmansengal");
		        	context.put("Ahli_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(SOC_SEKSYEN), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeSeksyen');\""));
		        	context.put("Ahli_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(null), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeNegeri');\""));
		        	
		        	
		        	Ahli_Kategori = "1";
		        	Ahli_Kategori1 = "checked=\"checked\"";
		        	Ahli_Kategori2 = "";
		        	Ahli_Kategori3 = "";
		        	Ahli_Peranan = modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", Ahli_Peranan, "1", RO_MaklumatMesyuarat);
		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", "", SOC_SEKSYEN, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai');\"",submit,actionx);
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
			        	Ahli_Kategori3 = "";	
		        
		        	   	
		        	
		        	Ahli_Peranan = modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", Ahli_Peranan, "1", RO_MaklumatMesyuarat);
		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", SOC_PEGAWAI, SOC_SEKSYEN, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai');\"",submit,actionx);
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
			        	Ahli_Kategori3 = "";	
		        	
		        	Ahli_Peranan = modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", Ahli_Peranan, "1", RO_MaklumatMesyuarat);
		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiByNegeri(ID_MESYUARAT,"SOC_PEGAWAI", SOC_PEGAWAI, SOC_NEGERI, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai1');\"",submit,actionx);
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
		        
		        
		        
		        
		        else if ("changePegawai2".equalsIgnoreCase(submit)) {
		        	System.out.println("GET SEKSYEN :"+SOC_SEKSYEN);
		        	
		        //	context.put("Ahli_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(SOC_NEGERI), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeNegeri3');\""));
		        	Ahli_Kategori = "3";  
		        
		        		Ahli_Kategori1 = "";
			        	Ahli_Kategori2 = "";
			        	Ahli_Kategori3 = "checked=\"checked\"";
		        	      	
		        	
		        	Ahli_Peranan = modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", Ahli_Peranan, "1", RO_MaklumatMesyuarat);
		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", SOC_PEGAWAI, SOC_SEKSYEN, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai2');\"",submit,actionx);
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
	        		context.put("Ahli_Seksyen3", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(SOC_SEKSYEN), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeSeksyen3');\""));
			        
		        
		        }
		        
		        else if ("changePegawai3".equalsIgnoreCase(submit)) {
		        	//context.put("Ahli_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(SOC_SEKSYEN), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeSeksyen3');\""));
		        	Ahli_Kategori = "3";
		        	
		        
		        		Ahli_Kategori1 = "";
			        	Ahli_Kategori2 = "";
			        	Ahli_Kategori3 = "checked=\"checked\"";
		        	
		        	Ahli_Peranan = modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", Ahli_Peranan, "1", RO_MaklumatMesyuarat);
		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiByNegeri(ID_MESYUARAT,"SOC_PEGAWAI", SOC_PEGAWAI, SOC_NEGERI, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai3');\"",submit,actionx);
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
		        
	        		context.put("Ahli_Negeri3", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(SOC_NEGERI), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeNegeri3');\""));
		        	
		        }
		        
		        
		        
		        else if ("changeNegeri".equalsIgnoreCase(submit)) {
		        	context.put("Ahli_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(SOC_NEGERI), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeNegeri');\""));
		        	context.put("Ahli_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(null), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeSeksyen');\""));
		        	
		        	Ahli_Kategori = "1";
		        	Ahli_Kategori1 = "checked=\"checked\"";
		        	Ahli_Kategori2 = "";
		        	Ahli_Kategori3 = "";
		        	Ahli_Peranan = modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", Ahli_Peranan, "1", RO_MaklumatMesyuarat);
		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiByNegeri(ID_MESYUARAT,"SOC_PEGAWAI", SOC_PEGAWAI, SOC_NEGERI, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai1');\"",submit,actionx);
		       
		        	context.put("Ahli_Nama", "");
			    	context.put("txtAlamatAhli", "");
			    	context.put("Ahli_Agensi", "");
			    	context.put("Ahli_Jawatan", "");
			    	context.put("Ahli_Email", "");
			    	context.put("txtAlamatAhli", "");
			    	context.put("Ahli_NoFaks", "");
			    	context.put("Ahli_NoTelefon", "");
		        
		        } else if ("changeKategori1".equalsIgnoreCase(submit)) {
		        	Ahli_Kategori = "1";
		        	Ahli_Kategori1 = "checked=\"checked\"";
		        	Ahli_Kategori2 = "";
		        	Ahli_Kategori3 = "";
		        	Ahli_Peranan = modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", "", "1", RO_MaklumatMesyuarat);
		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", "", Ahli_Seksyen, "1", RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai');\"",submit,actionx);
		        	context.put("Ahli_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(null), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeNegeri');\""));
		        	context.put("Ahli_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(null), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeSeksyen');\""));
		        	
		        	context.put("Ahli_Nama", "");
			    	context.put("txtAlamatAhli", "");
			    	context.put("Ahli_Agensi", "");
			    	context.put("txtAlamatAhli", "");
			    	context.put("Ahli_Jawatan", "");
			    	context.put("Ahli_Email", "");
			    	context.put("Ahli_NoFaks", "");
			    	context.put("Ahli_NoTelefon", "");
			    	context.put("Ahli_Peranan", Ahli_Peranan);
		        	
		        }
		        // add by rizuan
		                else if ("changeSeksyen3".equalsIgnoreCase(submit)) {
				        	context.put("Ahli_Seksyen3", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(SOC_SEKSYEN), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeSeksyen3');\""));
				        	context.put("Ahli_Negeri3", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(null), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeNegeri3');\""));
				        	
				        	Ahli_Kategori = "3";
				        	context.put("Ahli_Kategori", "3");
				        	Ahli_Kategori1 = "";
				        	Ahli_Kategori2 = "";
				        	Ahli_Kategori3 = "checked=\"checked\"";
				        	Ahli_Peranan = modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", Ahli_Peranan, "3", RO_MaklumatMesyuarat);
				        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", "", SOC_SEKSYEN, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai2');\"",submit,actionx);
				       
				        	context.put("Ahli_Nama", "");
					    	context.put("txtAlamatAhli", "");
					    	context.put("Ahli_Agensi", "");
					    	context.put("Ahli_Jawatan", "");
					    	context.put("Ahli_Email", "");
					    	context.put("Ahli_NoFaks", "");
					    	context.put("Ahli_NoTelefon", "");
					    	context.put("txtAlamatAhli", "");
		                
		                } else if ("changeNegeri3".equalsIgnoreCase(submit)) {
				        	context.put("Ahli_Negeri3", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(SOC_NEGERI), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeNegeri3');\""));
				        	context.put("Ahli_Seksyen3", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(null), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeSeksyen3');\""));
				        	
				        	Ahli_Kategori = "3";
				        	Ahli_Kategori1 = "";
				        	Ahli_Kategori2 = "";
				        	Ahli_Kategori3 = "checked=\"checked\"";
				        	Ahli_Peranan = modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", Ahli_Peranan, "3", RO_MaklumatMesyuarat);
				        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiByNegeri(ID_MESYUARAT,"SOC_PEGAWAI", "", SOC_NEGERI, Ahli_Kategori, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai3');\"",submit,actionx);
				       
		                
				        	context.put("Ahli_Nama", "");
					    	context.put("txtAlamatAhli", "");
					    	context.put("Ahli_Agensi", "");
					    	context.put("txtAlamatAhli", "");
					    	context.put("Ahli_Jawatan", "");
					    	context.put("Ahli_Email", "");
					    	context.put("Ahli_NoFaks", "");
					    	context.put("Ahli_NoTelefon", "");
				        	
		                } else if ("changeKategori3".equalsIgnoreCase(submit)) {
				        	Ahli_Kategori = "3";
				        	Ahli_Kategori1 = "";
				        	Ahli_Kategori2 = "";
				        	Ahli_Kategori3 = "checked=\"checked\"";
				        	Ahli_Peranan = modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", "", "3", RO_MaklumatMesyuarat);
				        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", "", Ahli_Seksyen, "3", RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai2');\"",submit,actionx);
				        	context.put("Ahli_Seksyen3", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(null), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeSeksyen3');\""));
				        	context.put("Ahli_Negeri3", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(null), RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeNegeri3');\""));
				        
				        	
				        	context.put("Ahli_Nama", "");
					    	context.put("txtAlamatAhli", "");
					    	context.put("Ahli_Agensi", "");
					    	context.put("Ahli_Jawatan", "");
					    	context.put("Ahli_Email", "");
					    	context.put("Ahli_NoFaks", "");
					    	context.put("Ahli_NoTelefon", "");
					    	context.put("Ahli_Peranan", Ahli_Peranan);
				        	
		        // end by rizuan
		        } else if ("changeKategori2".equalsIgnoreCase(submit)) {
		        	Ahli_Kategori = "2";
		        	Ahli_Kategori1 = "";
		        	Ahli_Kategori2 = "checked=\"checked\"";
		        	Ahli_Kategori3 = "";
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
		        
		    /*    else if ("changePegawai".equalsIgnoreCase(submit)) {
		        	this.context.put("Ahli_Jawatan", "xxxxxxxxxxx");		        	
		        	
		        	Ahli_Peranan = modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", Ahli_Peranan, "2", RO_MaklumatMesyuarat);
		        
		        }*/
		        
		     /*   else if ("changeKategori3".equalsIgnoreCase(submit)) {
		        	Ahli_Kategori = "3";
		        	Ahli_Kategori1 = "";
		        	Ahli_Kategori2 = "";
		        	Ahli_Kategori3 = "checked=\"checked\"";
		        	Ahli_Peranan = modelMesyuarat.getSOCPerananAhliMesyuarat("SOC_PERANAN", Ahli_Peranan, "1", RO_MaklumatMesyuarat);
		        	Ahli_Pegawai = modelMesyuarat.getSOCPegawaiBySeksyen(ID_MESYUARAT,"SOC_PEGAWAI", SOC_PEGAWAI, Ahli_Seksyen, "3", RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changePegawai');\"",submit,actionx);
		        	
		        }*/ else if ("enableWakil0".equalsIgnoreCase(submit)) {
		        	context.put("fail.EnableWakil", "readonly=\"readonly\"");
		        	context.put("fail.EnableWakil", "readonly=\"readonly\"");
		        } else if ("enableWakil1".equalsIgnoreCase(submit)) {
		        	context.put("fail.EnableWakil", "");
		        } else if ("changeAgenda".equalsIgnoreCase(submit)) {
		        	context.put("SubMinit_Agenda", modelMesyuarat.getSOCAgendaMesyuarat("SOC_AGENDA", SOC_AGENDA, ID_MESYUARAT, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeAgenda')\""));
		        	context.put("SubMinit_Minit", modelMesyuarat.getSOCMinitMesyuarat("SOC_MINIT", SOC_MINIT, SOC_AGENDA, RO_MaklumatMesyuarat));
		        } else if ("clickAddButton".equalsIgnoreCase(submit)) {
		        	String addType = getParam("addType");
		        	String addId = getParam("addId");
		        	String addIdMinit = modelMesyuarat.getIDMinit(addType, addId);
		        	String addIdAgenda = modelMesyuarat.getIDAgenda(addType, addId);
		        	context.put("Minit_Agenda", modelMesyuarat.getSOCAgendaMesyuarat("SOC_AGENDA", addIdAgenda, ID_MESYUARAT, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeAgenda')\""));
		        	context.put("SubMinit_Agenda", modelMesyuarat.getSOCAgendaMesyuarat("SOC_AGENDA", addIdAgenda, ID_MESYUARAT, RO_MaklumatMesyuarat, " onchange=\"doChangeSOC('changeAgenda')\""));
		        	context.put("SubMinit_Minit", modelMesyuarat.getSOCMinitMesyuarat("SOC_MINIT", addIdMinit, addIdAgenda, RO_MaklumatMesyuarat));
		        }
		    	context.put("Ahli_Kategori", Ahli_Kategori);
		    	context.put("Ahli_Kategori1", Ahli_Kategori1);
		    	context.put("Ahli_Kategori2", Ahli_Kategori2);
		    	context.put("Ahli_Kategori3", Ahli_Kategori3);
		    	context.put("Ahli_Peranan", Ahli_Peranan);
		    	context.put("Ahli_Pegawai", Ahli_Pegawai);
	        }
        }
        System.out.println(vm);
        
        this.context.put("ListMesyuarat", list);
 	    prepareItemForDisplay(session,list,10,"first");

		setupPage(session, actionx, list);
		
		return vm;
	} 	

	
	private void listPegawai(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmModelMesyuarat.setListPegawai(user_id,(String) session.getAttribute("_ekptg_user_negeri"));
		
	}


	private void updateEvent(int year, int month, int day,Hashtable event, String id_mesyuarat) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			SQLRenderer r = new SQLRenderer();
			r.update("id_mesyuarat", id_mesyuarat);
            r.add("user_id", (String) event.get("userId"));
			r.add("event_id", (String) event.get("eventId"));
			r.add("event_text", (String) event.get("eventText"));
            r.add("view_scope", (String) event.get("viewScope"));
			r.add("event_date", r.unquote("TO_DATE('" + DateTool.getDateStr(year, month, day) + "', 'yyyy-MM-dd')"));
			sql = r.getSQLUpdate("calendar_event");
			
			db.getStatement().executeUpdate(sql);
			
		} 
		finally {
			if ( db != null ) db.close();
		}
		
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
	
	private void view_Seksyen(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmModelMesyuarat.setSeksyen(user_id);

		}
	private void populateMesyuaratData(Hashtable<?, ?> h) throws Exception {
		Mesyuarat_NoFail = (String) h.get("Mesyuarat_NoFail");
		Mesyuarat_Bil = (String) h.get("Mesyuarat_Bil");
		Mesyuarat_Tajuk = (String) h.get("Mesyuarat_Tajuk");
		Mesyuarat_Kategori = (String) h.get("Mesyuarat_Kategori");
		Mesyuarat_Tarikh = (String) h.get("Mesyuarat_Tarikh");
		Mesyuarat_Dari = (String) h.get("Mesyuarat_Dari");
		Mesyuarat_Hingga = (String) h.get("Mesyuarat_Hingga");
		Mesyuarat_Lokasi = (String) h.get("Mesyuarat_Lokasi");
		Mesyuarat_Status = (String) h.get("Mesyuarat_Status");
		Mesyuarat_Seksyen = (String) h.get("Mesyuarat_Seksyen");
		Mesyuarat_Unit = (String) h.get("Mesyuarat_Unit");
		Mesyuarat_Negeri = (String) h.get("Mesyuarat_Negeri");
		Mesyuarat_NoFailDiperlukan = (String) h.get("Mesyuarat_NoFailDiperlukan");
		Mesyuarat_DisediakanOleh = (String) h.get("Mesyuarat_DisediakanOleh");
		Mesyuarat_DisemakOleh = (String) h.get("Mesyuarat_DisemakOleh");
		Mesyuarat_DisahkanOleh = (String) h.get("Mesyuarat_DisahkanOleh");
		Mesyuarat_Catatan = (String) h.get("Mesyuarat_Catatan");
		Mesyuarat_ValueDari = (String) h.get("Mesyuarat_ValueDari");
		Mesyuarat_ValueHingga = (String) h.get("Mesyuarat_ValueHingga");
		Mesyuarat_ValueLokasi = (String) h.get("Mesyuarat_ValueLokasi");
		Mesyuarat_ValueSeksyen = (String) h.get("Mesyuarat_ValueSeksyen");
		Mesyuarat_ValueUnit = (String) h.get("Mesyuarat_ValueUnit");
		Mesyuarat_ValueNegeri = (String) h.get("Mesyuarat_ValueNegeri");
		Mesyuarat_ValueStatus  = (String) h.get("Mesyuarat_ValueStatus");
		Mesyuarat_TempahMakananPemohon  = (String) h.get("Mesyuarat_TempahMakananPemohon");
		Mesyuarat_TempahMakananBilAhli  = (String) h.get("Mesyuarat_TempahMakananBilAhli");
		Mesyuarat_TempahMakananMakanan  = (String) h.get("Mesyuarat_TempahMakananMakanan");
		Mesyuarat_TempahMakananMinuman  = (String) h.get("Mesyuarat_TempahMakananMinuman");
		Mesyuarat_Masa = Mesyuarat_ValueDari + " - " + Mesyuarat_ValueHingga;
		if ("".equalsIgnoreCase(Mesyuarat_Status)) {
			Mesyuarat_Status = "1";
			Mesyuarat_ValueStatus = "BARU";
		}
		if ("".equalsIgnoreCase(Mesyuarat_Tarikh)) {
			Date date = new Date();
			Mesyuarat_Tarikh = sdf.format(date);
		}
		if ("".equalsIgnoreCase(Mesyuarat_DisediakanOleh)) {
			Mesyuarat_DisediakanOleh = userName;
		}
	}

	public void setupPage(HttpSession session,String actionx,Vector list) {
		try {
		
		this.context.put("totalRecords",list.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
		}
	    
	    if ("getNext".equals(actionx)) {
	    	page++;
	    } else if ("getPrevious".equals(actionx)) {
	    	page--;
	    } else if ("getPage".equals(actionx)) {
	    	page = getParamAsInteger("value");
	    } else if ("doChangeItemPerPage".equals(actionx)) {
	       itemsPerPage = getParamAsInteger("itemsPerPage");
	    }
	    	
	    Paging paging = new Paging(session,list,itemsPerPage);
		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("ListMesyuarat",paging.getPage(page));
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
	
	private void addEvent(int year, int month, int day, Hashtable event, String ID_MESYUARAT) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			SQLRenderer r = new SQLRenderer();
			r.add("id", UniqueID.getUID());
            r.add("user_id", (String) event.get("userId"));
			r.add("event_id", (String) event.get("eventId"));
			r.add("event_text", (String) event.get("eventText"));
            r.add("view_scope", (String) event.get("viewScope"));
			r.add("event_date", r.unquote("TO_DATE('" + DateTool.getDateStr(year, month, day) + "', 'yyyy-MM-dd')"));
			r.add("id_mesyuarat", ID_MESYUARAT);
			sql = r.getSQLInsert("calendar_event");
			
			db.getStatement().executeUpdate(sql);
			
		} 
		finally {
			if ( db != null ) db.close();
		}
	}
}