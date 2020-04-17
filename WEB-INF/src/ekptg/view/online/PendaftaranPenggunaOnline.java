package ekptg.view.online;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;

public class PendaftaranPenggunaOnline extends AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(PendaftaranPenggunaOnline.class);
	public String doTemplate2() throws Exception
    {
		String vm = "";
		String submit = getParam("command");
		String action = getParam("action");
		HttpSession session = this.request.getSession();
        Vector list = new Vector();
        String code = (String) session.getAttribute("verification.code");
        String attempt = getParam("txtCaptChar");
        String jantina = getParam("socJantina");
        String tarafPerkahwinan = getParam("socTarafPerkahwinan");
        
        this.context.put("errorMessage","");
        this.context.put("success","");
        if ("simpan".equals(action)){
        	
        	vm = "app/online/frmKemaskiniPengguna.jsp";
        	this.context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri",Utils.parseLong(getParam("socNegeri")),"",""));
        	if ((code != null) && (code.equals(attempt))) {
        		
        		if(getParam("txtKataLaluan").length()< 6){
            		this.context.put("errorMessage","Saiz minimum kata laluan adalah 6. Sila masukkan semula kata laluan anda.");
            		this.context.put("jantina",jantina);
            		this.context.put("tarafPerkahwinan",tarafPerkahwinan);
            		
        		}
        		
        		else if(getParam("txtKataLaluan").equals(getParam("txtSahkanKataLaluan"))){
            		simpan(session);
              	}
            	else{
            		this.context.put("errorMessage","Kata laluan tidak sama.Sila masukan semula kata laluan anda.");
            	}
         	}
        	else{
        		this.context.put("errorMessage","Kod Pengesahan salah. Sila masukkan kod pengesahan dengan betul.");
        	}
        	
        	// Azam - Auto-magically get all values from JSP
    		String key="";
    		String value="";
    		Enumeration allparam = request.getParameterNames();
    		for (; allparam.hasMoreElements(); ) {
    	        key = (String)allparam.nextElement();
    		    value = request.getParameter(key);
    		    this.context.put(key, value);
    		}
        }
        else{
        	//Init value
        	//Hashtable h = (Hashtable)this.context.getKeys();
        	//HashMap map = (HashMap)this.context;
  
//        	Map map = (Map)this.context;
//        	Set keys = map.keySet();
//            for (Iterator i = keys.iterator(); i.hasNext();) {
//              Integer key = (Integer) i.next();
//              String value = (String) map.get(key);
//              System.out.println(key + " = " + value);
//            }

        	vm = "app/online/frmKemaskiniPengguna.jsp";
        	this.context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri",null,"",""));
        	
        }
		return vm;
		
    }
	public void simpan(HttpSession session)throws Exception{
		
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		
		Hashtable h = new Hashtable();
		h.put("nama",getParam("txtNama"));
		h.put("noKP",getParam("txtNoKPBaru"));
		h.put("tarikhLahir", getParam("txdTarikhLahir"));
		h.put("umur",getParam("txtUmur"));
		h.put("jantina",getParam("socJantina"));
		h.put("tarafPerkahwinan",getParam("socTarafPerkahwinan"));
		h.put("alamat1",getParam("txtAlamat1"));
		h.put("alamat2",getParam("txtAlamat2"));
		h.put("alamat3",getParam("txtAlamat3"));
		h.put("poskod", getParam("txtPoskod"));
		h.put("negeri",getParam("socNegeri"));
		h.put("noTelRumah",getParam("txtNoTelRumah"));
		h.put("noTelBimbit", getParam("txtNoTelBimbit"));
		h.put("noFaks",getParam("txtNoFaks"));
		h.put("emel",getParam("txtEmel"));
		h.put("idPengguna",getParam("txtIdPengguna"));
		h.put("kataLaluan", getParam("txtKataLaluan"));
		try {
				//FrmKemaskiniPenggunaData.addPengguna(h);
				this.context.put("success","Pendaftaran berjaya.");
			} catch (Exception e) {
			this.context.put("errorMessage",e.getMessage());
		}
	}
	

}
