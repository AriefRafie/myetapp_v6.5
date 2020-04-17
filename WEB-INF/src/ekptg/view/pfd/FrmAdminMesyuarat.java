package ekptg.view.pfd;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import lebah.db.UniqueID;
import lebah.portal.AjaxBasedModule;
import lebah.util.DateTool;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.pfd.FrmAdminMesyuaratData;
import ekptg.model.pfd.FrmModelMesyuarat;

@SuppressWarnings({ "serial", "unused" })
public class FrmAdminMesyuarat extends AjaxBasedModule {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    String userName = "";
    String txtnamaahli = "";
    String txtnamabilik = "";
    String bilikFail = "";
    String Ahli_Negeri = "";
    String Ahli_Seksyen = "";
    String Ahli_Unit = "";


    //data bilik
    String namabilikTBilik = "";
    String namabilikTBilikFail = "";
    String Alamat1TBilik = "";
    String Alamat2TBilik = "";
    String Alamat3TBilik = "";
    String PoskodTBilik = "";
    String Ahli_NegeriTBilik = "";
    String Ahli_UnitTBilik = "";
    String Ahli_SeksyenTBilik = "";
    String id_bilik = "";
    String id_bilikFail = "";
    
    //data ahli
    String namaahliTAhli = "";
    String jawatanTAhli = "";
    String Ahli_NegeriTAhli = "";
    String Ahli_SeksyenTAhli = "";
    String id_ahli = "";
    String email_ahliTAhli = "";
	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception{
		
		String vm = "";

        HttpSession session = this.request.getSession();
        
        FrmModelMesyuarat modelMesyuarat = new FrmModelMesyuarat();
        FrmAdminMesyuaratData AdminMesyuaratData = new FrmAdminMesyuaratData();
        
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
        context.put("idNegeri", user_negeri);
        
        String action = getParam("action");
        
        // default
        String nama_Ahli = getParam("txtnamaahli");
        String nama_Bilik = getParam("txtnamabilik");
        String nama_BilikFail = getParam("txtnamabilikFail");
        Ahli_Negeri = getParam("SOC_NEGERI");
        Ahli_Seksyen = getParam("SOC_SEKSYEN");
        Ahli_Unit = getParam("SOC_UNIT");
        
        System.out.println("Ahli_Negeri -->"+Ahli_Negeri );
        System.out.println("Ahli_Seksyen -->"+Ahli_Seksyen );
        //data ahli
        namaahliTAhli = getParam("txtnamaahliTAhli");
        jawatanTAhli = getParam("SOC_JAWATAN");
        Ahli_NegeriTAhli = getParam("SOC_NEGERI_AHLI");
        Ahli_SeksyenTAhli = getParam("SOC_SEKSYEN_AHLI");
        id_ahli = getParam("txtIdPegawai");
        email_ahliTAhli = getParam("txtemailahliTAhli");

        
        //data bilik
        namabilikTBilik = getParam("txtnamabilikTBilik");
        namabilikTBilikFail = getParam("txtnamabilikTBilikFail");
        Alamat1TBilik = getParam("txtAlamat1TBilik");
        Alamat2TBilik = getParam("txtAlamat2TBilik");
        Alamat3TBilik = getParam("txtAlamat3TBilik");
        PoskodTBilik = getParam("txtPoskodTBilik");
        Ahli_NegeriTBilik = getParam("SOC_NEGERI_BILIK");
        Ahli_SeksyenTBilik = getParam("SOC_SEKSYEN_BILIK");
        Ahli_UnitTBilik = getParam("SOC_UNIT_BILIK");
        id_bilik = getParam("idBilikMesyurat");
        id_bilikFail = getParam("idBilikFail");
        
        userName = (String) session.getAttribute("_portal_username");
        
    	Vector vData = new Vector();
    	Vector vList = new Vector();
    	Hashtable h = new Hashtable();
    	
        if ("removebilik".equalsIgnoreCase(action)) {
        	vm = "app/pfd/FrmAdminMesyuarat.jsp";
        	
        	AdminMesyuaratData.hapusLokasiMesyuarat(id_bilik);
        	
        	if("16".equalsIgnoreCase(user_negeri)){
				AdminMesyuaratData.setCarianMesyuaratBilik(nama_Bilik, Ahli_Seksyen, Ahli_Negeri);	
				vData = AdminMesyuaratData.getListBilik();
				context.put("ListMesyuaratBilik", vData);
        	}
        	else{
    			AdminMesyuaratData.setCarianMesyuaratBilikNegeri(nama_Bilik, Ahli_Unit, Ahli_Negeri,user_negeri);	
    			vData = AdminMesyuaratData.getListBilik();
    			context.put("ListMesyuaratBilik", vData);
        	}
			
			context.put("mode", "bilik");
    		context.put("modeT", "");
			
    		context.put("ListMesyuaratAhli", "");
    		
    		context.put("txtnamaahli",nama_Ahli);
    		context.put("bilikFail",bilikFail);
        	context.put("txtnamabilik",nama_Bilik);
	    	context.put("Ahli_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(Ahli_Negeri), "", ""));
	    	context.put("Ahli_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(Ahli_Seksyen), "", " "));
	    	context.put("Ahli_Unit", HTML.SelectUnit("SOC_UNIT", Utils.parseLong(Ahli_Unit), "", " "));


        } 

        else if ("removebilikFail".equalsIgnoreCase(action)) {
        	vm = "app/pfd/FrmAdminMesyuarat.jsp";
        	
        	AdminMesyuaratData.hapusLokasiBilikFail(id_bilikFail);
			
        	if("16".equalsIgnoreCase(user_negeri)){
        		AdminMesyuaratData.setCarianMesyuaratBilikFail(nama_Bilik, Ahli_Seksyen, Ahli_Negeri);	
        		vData = AdminMesyuaratData.getList();
        		context.put("ListMesyuaratBilikFail", vData);

        	}
        	else
        	{
        		AdminMesyuaratData.setCarianMesyuaratBilikFailNegeri(nama_BilikFail, Ahli_Unit, Ahli_Negeri, user_negeri);	       		 
        		vData = AdminMesyuaratData.getListNegeri();
        		context.put("ListMesyuaratBilikFail", vData);
        		
        	}
			
			context.put("mode", "lokasifail");
    		context.put("modeT", "");
			
    		context.put("ListMesyuaratAhli", "");
    		
    		context.put("txtnamabilikFail",nama_BilikFail);
        	context.put("txtnamabilik",nama_Bilik);
	    	context.put("Ahli_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(Ahli_Negeri), "", ""));
	    	context.put("Ahli_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(Ahli_Seksyen), "", " "));
	    	context.put("Ahli_Unit", HTML.SelectUnit("SOC_UNIT", Utils.parseLong(Ahli_Unit), "", " "));


        } 
        
        else if ("simpanbilikMesyuarat".equalsIgnoreCase(action)) {
        	vm = "app/pfd/FrmAdminMesyuarat.jsp";
        	
        	simpanbilik();
        	
        	if("16".equalsIgnoreCase(user_negeri)){
    			AdminMesyuaratData.setCarianMesyuaratBilik(namabilikTBilik, Ahli_SeksyenTBilik, Ahli_NegeriTBilik);	
    			vData = AdminMesyuaratData.getListBilik();
    			context.put("ListMesyuaratBilik", vData);
        	}
        	else{
    			AdminMesyuaratData.setCarianMesyuaratBilikNegeri(namabilikTBilik, Ahli_UnitTBilik, Ahli_NegeriTBilik, user_negeri);	
    			vData = AdminMesyuaratData.getListBilikNegeri();
    			context.put("ListMesyuaratBilik", vData);
        	}

			
			context.put("mode", "bilik");
    		context.put("modeT", "");
			
    		context.put("ListMesyuaratAhli", "");
    		
        	context.put("txtnamabilik","");
	    	context.put("Ahli_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(Ahli_NegeriTBilik), "", ""));
	    	
	    	if("16".equalsIgnoreCase(user_negeri)){
	    		context.put("Ahli_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(Ahli_SeksyenTBilik), "", " "));
	    	}
	    	else{
	    		context.put("Ahli_Unit", HTML.SelectUnit("SOC_UNIT", Utils.parseLong(Ahli_UnitTBilik), "", " "));

	    	}
	    	
        
        } 
        
        else if ("simpanbilikFail".equalsIgnoreCase(action)) {
        	vm = "app/pfd/FrmAdminMesyuarat.jsp";
        	
        	simpanbilikFail(session);
        	
        	if("16".equalsIgnoreCase(user_negeri)){
        		AdminMesyuaratData.setCarianMesyuaratBilikFail(namabilikTBilikFail, Ahli_SeksyenTBilik, Ahli_NegeriTBilik);	
        		vData = AdminMesyuaratData.getList();
        		context.put("ListMesyuaratBilikFail", vData);
        	}
        	else{
        		AdminMesyuaratData.setCarianMesyuaratBilikFailNegeri(namabilikTBilikFail, Ahli_UnitTBilik, Ahli_NegeriTBilik, user_negeri);	       		 
        		vData = AdminMesyuaratData.getListNegeri();
        		context.put("ListMesyuaratBilikFail", vData);
        	}
    	

			context.put("mode", "lokasifail");
    		context.put("modeT", "");
			
    		context.put("ListMesyuaratBilik", "");
    		
        	context.put("txtnamabilikFail","");
	    	context.put("Ahli_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(Ahli_NegeriTBilik), "", ""));
	    	
	    	if("16".equalsIgnoreCase(user_negeri)){
	    		context.put("Ahli_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(Ahli_SeksyenTBilik), "", " "));
	    	}
	    	else{
	    		context.put("Ahli_Unit", HTML.SelectUnit("SOC_UNIT", Utils.parseLong(Ahli_UnitTBilik), "", " "));

	    	}	    	
        
        } 
        
        else if ("tambahbilikFail".equalsIgnoreCase(action)) {
    		vm = "app/pfd/FrmAdminMesyuarat.jsp";
    		context.put("modeT", "lokasifail");
    		context.put("mode", "");
	    	context.put("Ahli_NegeriTBilik", HTML.SelectNegeri("SOC_NEGERI_BILIK", Utils.parseLong(Ahli_NegeriTBilik), "", ""));	
	    	if("16".equalsIgnoreCase(user_negeri)){
	    		context.put("Ahli_SeksyenTBilik", HTML.SelectSeksyen("SOC_SEKSYEN_BILIK", Utils.parseLong(Ahli_SeksyenTBilik), "", " "));
	    	}else{
	    		context.put("Ahli_UnitTBilik", HTML.SelectUnit("SOC_UNIT_BILIK", Utils.parseLong(Ahli_UnitTBilik), "", " "));

	    	}
	    
        }	
	        else if ("tambahbilikMesyuarat".equalsIgnoreCase(action)) {
	    		vm = "app/pfd/FrmAdminMesyuarat.jsp";
	    		context.put("modeT", "bilik");
	    		context.put("mode", "");
		    	context.put("Ahli_NegeriTBilik", HTML.SelectNegeri("SOC_NEGERI_BILIK", Utils.parseLong(Ahli_NegeriTBilik), "", ""));	
		    	if("16".equalsIgnoreCase(user_negeri)){
		    		context.put("Ahli_SeksyenTBilik", HTML.SelectSeksyen("SOC_SEKSYEN_BILIK", Utils.parseLong(Ahli_SeksyenTBilik), "", " "));
		    	}else{
		    		context.put("Ahli_UnitTBilik", HTML.SelectUnit("SOC_UNIT_BILIK", Utils.parseLong(Ahli_UnitTBilik), "", " "));

		    	}
	    	
	     }
	        
	        else if ("caribilik".equalsIgnoreCase(action)) {
        	vm = "app/pfd/FrmAdminMesyuarat.jsp";
        	
    		if (!"".equalsIgnoreCase(nama_BilikFail) ){
    			if("16".equalsIgnoreCase(user_negeri)){
    			//setCarianMesyuarat
	    			AdminMesyuaratData.setCarianMesyuaratBilikFail(nama_BilikFail, Ahli_Seksyen, Ahli_Negeri);	
	    			vList = AdminMesyuaratData.getList();
	    			context.put("ListMesyuaratBilikFail", vList);
	    			context.put("mode", "lokasifail");
    			}
    			else{
        			AdminMesyuaratData.setCarianMesyuaratBilikFailNegeri(nama_BilikFail, Ahli_Unit ,Ahli_Negeri,(String) session.getAttribute("_ekptg_user_negeri"));	
        			vList = AdminMesyuaratData.getListNegeri();
        			context.put("ListMesyuaratBilikFail", vList);
        			context.put("mode", "lokasifail");
    			}
    			
    		}
    		
    		else{

            	if("16".equalsIgnoreCase(user_negeri)){
        			AdminMesyuaratData.setCarianMesyuaratBilik(nama_Bilik, Ahli_Seksyen, Ahli_Negeri);	
        			vData = AdminMesyuaratData.getListBilik();
        			context.put("ListMesyuaratBilik", vData);
        			context.put("mode", "bilik");
            	}
            	else{
        			AdminMesyuaratData.setCarianMesyuaratBilikNegeri(nama_Bilik, Ahli_Unit, Ahli_Negeri, (String) session.getAttribute("_ekptg_user_negeri"));	
        			vData = AdminMesyuaratData.getListBilikNegeri();
        			context.put("ListMesyuaratBilik", vData);
        			context.put("mode", "bilik");
            	}
    			
    		}
        	context.put("txtnamabilikFail",nama_BilikFail);
        	context.put("txtnamabilik",nama_Bilik);
    		context.put("modeT", "");
	    	context.put("Ahli_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(Ahli_Negeri), "", ""));
	    	context.put("Ahli_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(Ahli_Seksyen), "", " "));
        
        } else  if ("resetx".equalsIgnoreCase(action)) {
        	vm = "app/pfd/FrmAdminMesyuarat.jsp";
        	
        	context.put("ListMesyuaratAhli", "");
        	context.put("ListMesyuaratBilik", "");
			context.put("mode", "");
			context.put("modeT", "");
        	
        	context.put("txtnamaahli","");
        	context.put("txtnamabilik","");
	    	context.put("Ahli_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(""), "", ""));
	    	context.put("Ahli_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(""), "", " "));
    		
        } 
        
        else if ("senaraiBilikMesyuarat".equalsIgnoreCase(action) ){
        	vm = "app/pfd/FrmAdminMesyuarat.jsp";
        	
        	if("16".equalsIgnoreCase(user_negeri)){
    			AdminMesyuaratData.setCarianMesyuaratBilik(nama_Bilik, Ahli_Seksyen, Ahli_Negeri);	
    			vData = AdminMesyuaratData.getListBilik();
    			context.put("ListMesyuaratBilik", vData);
    			context.put("mode", "bilik");
    			context.put("modeT", "");
    			
        	}
        	else{
    			AdminMesyuaratData.setCarianMesyuaratBilikNegeri(nama_Bilik, Ahli_Unit, Ahli_Negeri, (String) session.getAttribute("_ekptg_user_negeri"));	
    			vData = AdminMesyuaratData.getListBilikNegeri();
    			context.put("ListMesyuaratBilik", vData);
    			context.put("mode", "bilik");
    			context.put("modeT", "");
        	}
        	
        	context.put("Ahli_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(user_negeri), "", ""));
	    	context.put("Ahli_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(Ahli_Seksyen), "", " "));
	    	context.put("Ahli_Unit", HTML.SelectUnit("SOC_UNIT", Utils.parseLong(Ahli_Unit), "", " "));
			
		}
		
		else if ("senaraiBilikFail".equalsIgnoreCase(action) ){
			vm = "app/pfd/FrmAdminMesyuarat.jsp";
			
			if("16".equalsIgnoreCase(user_negeri)){
    			//setCarianMesyuarat
	    			AdminMesyuaratData.setCarianMesyuaratBilikFail(nama_BilikFail, Ahli_Seksyen, Ahli_Negeri);	
	    			vList = AdminMesyuaratData.getList();
	    			context.put("ListMesyuaratBilikFail", vList);
	    			context.put("mode", "lokasifail");
	    			context.put("modeT", "");
    			}
    			else{
        			AdminMesyuaratData.setCarianMesyuaratBilikFailNegeri(nama_BilikFail, Ahli_Unit ,Ahli_Negeri,(String) session.getAttribute("_ekptg_user_negeri"));	
        			vList = AdminMesyuaratData.getListNegeri();
        			context.put("ListMesyuaratBilikFail", vList);
        			context.put("mode", "lokasifail");
        			context.put("modeT", "");
    			}
			
			context.put("Ahli_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(user_negeri), "", ""));
	    	context.put("Ahli_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(Ahli_Seksyen), "", " "));
	    	context.put("Ahli_Unit", HTML.SelectUnit("SOC_UNIT", Utils.parseLong(Ahli_Unit), "", " "));
			
		}
        
        
        
        else {
        	vm = "app/pfd/FrmAdminMesyuarat.jsp";
        	
        	context.put("ListMesyuaratBilikFail", "");
        	context.put("ListMesyuaratBilik", "");
			context.put("modeT", "");
			context.put("mode", "");

        	
        	context.put("txtnamabilikFail",nama_BilikFail);
        	context.put("txtnamabilik",nama_Bilik);
	    	context.put("Ahli_Negeri", HTML.SelectNegeri("SOC_NEGERI", Utils.parseLong(user_negeri), "", ""));
	    	context.put("Ahli_Seksyen", HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(Ahli_Seksyen), "", " "));
	    	context.put("Ahli_Unit", HTML.SelectUnit("SOC_UNIT", Utils.parseLong(Ahli_Unit), "", " "));
    		

        }
        
        System.out.println(vm);
		return vm;
	} 	

	private void simpanbilik() throws SQLException, DbException {
		// TODO Auto-generated method stub
		Hashtable k = new Hashtable();
		k.put("nama_bilik_TBilik",getParam("txtnamabilikTBilik"));
		k.put("Alamat1_TBilik",getParam("txtAlamat1TBilik"));
		k.put("Alamat2_TBilik",getParam("txtAlamat2TBilik"));
		k.put("Alamat3_TBilik",getParam("txtAlamat3TBilik"));
		k.put("Poskod_TBilik",getParam("txtPoskodTBilik"));
		k.put("Ahli_Negeri_TBilik",getParam("SOC_NEGERI_BILIK"));
		k.put("Ahli_Seksyen_TBilik",getParam("SOC_SEKSYEN_BILIK"));
		k.put("Ahli_Unit_TBilik",getParam("SOC_UNIT_BILIK"));
		
		FrmAdminMesyuaratData.addBilik(k);
	}
	
	private void simpanbilikFail(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
//		String user_negeri = (String)session.getAttribute("_ekptg_user_negeri");
		
		Hashtable k = new Hashtable();
		k.put("nama_bilik_TBilikFail",getParam("txtnamabilikTBilikFail"));
		k.put("Ahli_Negeri_TBilik",getParam("SOC_NEGERI_BILIK"));
		k.put("Ahli_Seksyen_TBilik",getParam("SOC_SEKSYEN_BILIK"));
		k.put("Ahli_Unit_TBilik",getParam("SOC_UNIT_BILIK"));
		k.put("idMasuk",user_id);
		
		FrmAdminMesyuaratData.addBilikFail(k);
	}
	private void view_Seksyen(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmModelMesyuarat.setSeksyen(user_id);

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
	
	private void addEvent(int year, int month, int day, Hashtable event) throws Exception {
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
			sql = r.getSQLInsert("calendar_event");
			
			db.getStatement().executeUpdate(sql);
			
		} 
		finally {
			if ( db != null ) db.close();
		}
	}
}