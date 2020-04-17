package ekptg.view.pfd;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.pfd.FrmDaftarSubjaketFailData;
import ekptg.model.pfd.FrmKemaskiniSubjaketFailData;
import ekptg.model.pfd.FrmLogDokumenData;

public class PendaftaranSubjaketFail extends AjaxBasedModule{
	
	FrmKemaskiniSubjaketFailData logic = new FrmKemaskiniSubjaketFailData();
	
	public String doTemplate2() throws Exception{
		String vm = "";
        String submit = getParam("command");
        String action1 = getParam("action1");
        String action = getParam("action");
		
		if ("doChangeItemPerPage".equals(action) ||
				"getPage".equals(action)) {
			action1 = action;
		}
        String mode = getParam("mode");
        
        String boleh_simpan ="";

        HttpSession session = this.request.getSession();
        String disability1 = "";
        String disability2 = "";
        String readability1= "";
        String readability2= "";
        String noFail = "";
        String noFailLama = "";
        String tajukFail = "";
        String negeri = getParam("socNegeriD");
        String seksyen = getParam("socSeksyenD");
        String status = getParam("socStatusD");
        String tarikhDaftar = "";
        Vector list = new Vector();
        Vector listDokumen = new Vector();
        Vector paparFail = null;
        Vector paparSubjaketFail = null;
        Vector paparSeksyen = null;
        Vector paparSemuaFail = null;
        this.context.put("Util",new Utils());
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        String doPost = (String) session.getAttribute("doPost");
        if(doPost.equals("true"))
        {
        	boleh_simpan = "yes";
        }
        
        if("tambahSubjaketFail".equals(action1)){
        	vm = "app/pfd/frmKemaskiniSubjaketFail.jsp";
        	context.put("mode","baru");
        	context.put("readOnly","");
			context.put("disabled","");
			this.context.put("subjaket","notsuccess");
			
			String user_name = (String)session.getAttribute("_portal_username");
	    	String user_id = (String)session.getAttribute("_ekptg_user_id");
	    	String idFailAsal = getParam("idFailAsal");
	    	context.put("user_Name", user_name);
	    	context.put("user_Id", user_id);
	    	context.put("idFailAsal", idFailAsal);
	    	context.put("tarikhSubjaketFail",sdf.format(now));
        	
//        	view_fail(session);  	
       		paparSemuaFail = FrmKemaskiniSubjaketFailData.getPaparSemuaFail(idFailAsal);
			Hashtable h = (Hashtable)paparSemuaFail.get(0);
			//context.put("status",h.get("keterangan"));
			context.put("idFailAsal",h.get("idFailAsal"));
	    	context.put("noFailAsal",h.get("noFailAsal"));
	    	context.put("tajukFail",h.get("tajukFail"));

		}
        
        else if ("simpanSubjaket".equals(action1)){
//
//        	view_fail2(session);	
//        	String idSubjaket = simpanSubjaket(session);
        	
        	vm = "app/pfd/frmKemaskiniSubjaketFail.jsp";
        	context.put("mode","papar");
			context.put("readOnly","readonly");
			context.put("disabled","class = disabled");
			this.context.put("subjaket","success");
			
			String user_name = (String)session.getAttribute("_portal_username");
	    	String user_id = (String)session.getAttribute("_ekptg_user_id");
	    	String user_role = (String)session.getAttribute("_portal_role");

	    	context.put("user_Role",user_role );
	    	context.put("user_Name", user_name);
	    	context.put("user_Id", user_id);
	    	
//        	view_fail(session);
//			paparFail = FrmKemaskiniSubjaketFailData.getDataFail();
//			Hashtable h = (Hashtable) paparFail.get(0);
//			context.put("status",h.get("keterangan"));
//			context.put("idFail",h.get("idFail"));
//	    	context.put("noFail",h.get("noFail"));
//	    	context.put("tajukFail",h.get("tajukFail"));
	    	
	    	String idFailAsal = getParam("idFailAsal");
        	String idFailSubjaket = simpan(session);
        	
       		paparSemuaFail = FrmKemaskiniSubjaketFailData.getPaparSemuaFail(idFailAsal);
       		Hashtable g = (Hashtable)paparSemuaFail.get(0);
       		
		   	this.context.put("idFailAsal", g.get("idFailAsal"));
       		this.context.put("noFailAsal", g.get("noFailAsal"));
        	
       		paparSubjaketFail = FrmKemaskiniSubjaketFailData.getPaparFailSubjaket(idFailSubjaket);
        	Hashtable h = (Hashtable)paparSubjaketFail.get(0);
        	
		   	this.context.put("idFailAsalSubjaket", h.get("idFailAsalSubjaket"));
       		this.context.put("noFailSubjaket", h.get("noFailSubjaket"));
        	

	    	
//			listSubjaket(session);
//			list = FrmKemaskiniSubjaketFailData.getListSubjaket();
//			this.context.put("SenaraiSubjaket",list);
        }
        
        else if ("kemaskiniSubjaket".equals(action1))
        {
        	String idSubjaket = getParam("idSubjaket");
        	
        	vm = "app/pfd/frmKemaskiniSubjaketFail.jsp";
        	//context.put("action1","paparSubjaket");
        	context.put("mode","kemaskini");
			context.put("readonly","");
			context.put("disabled","");
			context.put("idSubjaket",("idSubjaket"));
			
        	view_fail(session);
			paparFail = FrmKemaskiniSubjaketFailData.getDataFail();
			Hashtable h = (Hashtable) paparFail.get(0);
			context.put("status",h.get("keterangan"));
			context.put("idFail",h.get("idFail"));
	    	context.put("noFail",h.get("noFail"));
	    	context.put("tajukFail",h.get("tajukFail"));
	    	
	    
	    	
	    	view_SubjaketFail(session);
	    	paparSubjaketFail = FrmKemaskiniSubjaketFailData.getSubjaketFail(idSubjaket);
			Hashtable ha = (Hashtable)paparSubjaketFail.get(0);
			context.put("noFailSubjaket",ha.get("no_Fail_Subjaket"));
			context.put("selectPegawai",HTML.SelectPegawai("socPegawai",Long.parseLong(ha.get("id_Pegawai").toString()),""));
	    	context.put("tarikhSubjaketFail",ha.get("tarikh_Subjaket_Fail"));
	    	context.put("idSubjaket",ha.get("idSubjaket"));
	    	context.put("modeDokumen","baruDokumen");
	    	
			//listDokumen(session);
			listDokumen = FrmKemaskiniSubjaketFailData.getListDokumenBySubjaket(idSubjaket);
			this.context.put("SenaraiDokumen",listDokumen);
			setupPageDokumen(session, action1, listDokumen);
	    	
			listSubjaket(session);
			list = FrmKemaskiniSubjaketFailData.getListSubjaket();
			this.context.put("SenaraiSubjaket",list);
        }
        
        else if ("updateSubjaket".equals(action1))
        {
        	updateSubjaket(session);
        	String idSubjaket = getParam("idSubjaket");
        	
        	vm = "app/pfd/frmKemaskiniSubjaketFail.jsp";
        	//context.put("action1","paparSubjaket");
        	context.put("mode","update");
			context.put("readonly","readonly");
			context.put("disabled","disabled");
			context.put("idSubjaket",("idSubjaket"));
			context.put("modeDokumen","baruDokumen");
			
		
			
        	view_fail(session);
			paparFail = FrmKemaskiniSubjaketFailData.getDataFail();
			Hashtable h = (Hashtable) paparFail.get(0);
			context.put("status",h.get("keterangan"));
			context.put("idFail",h.get("idFail"));
	    	context.put("noFail",h.get("noFail"));
	    	context.put("tajukFail",h.get("tajukFail"));
	    	
	    
	    	
	    	view_SubjaketFail(session);
	    	paparSubjaketFail = FrmKemaskiniSubjaketFailData.getSubjaketFail(idSubjaket);
			Hashtable ha = (Hashtable)paparSubjaketFail.get(0);
			context.put("noFailSubjaket",ha.get("no_Fail_Subjaket"));
			context.put("selectPegawai",HTML.SelectPegawai("socPegawai",Long.parseLong(ha.get("id_Pegawai").toString()),"disabled"));
	    	context.put("tarikhSubjaketFail",ha.get("tarikh_Subjaket_Fail"));
	    	context.put("idSubjaket",ha.get("idSubjaket"));
	    	
			listDokumen(session);
			listDokumen = FrmKemaskiniSubjaketFailData.getListDokumenBySubjaket(idSubjaket);
			this.context.put("SenaraiDokumen",listDokumen);
			setupPageDokumen(session, action1, listDokumen);
	    	
			listSubjaket(session);
			list = FrmKemaskiniSubjaketFailData.getListSubjaket();
			this.context.put("SenaraiSubjaket",list);
	    	
        	
        }
        
        else if ("paparSubjaket".equals(action1)){
        	
        	String idSubjaket = getParam("idSubjaket");
        	
        	vm = "app/pfd/frmKemaskiniSubjaketFail.jsp";
        	context.put("action1","paparSubjaket");
        	context.put("mode","papar");
			context.put("readonly","readonly");
			context.put("disabled","disabled");
			context.put("idSubjaket",("idSubjaket"));
			context.put("disabledDokumen","");
	    	context.put("modeDokumen","baruDokumen");
		
			
        	view_fail(session);
			paparFail = FrmKemaskiniSubjaketFailData.getDataFail();
			Hashtable h = (Hashtable) paparFail.get(0);
			context.put("status",h.get("keterangan"));
			context.put("idFail",h.get("idFail"));
	    	context.put("noFail",h.get("noFail"));
	    	context.put("tajukFail",h.get("tajukFail"));
	
	    	
	    	view_SubjaketFail(session);
	    	paparSubjaketFail = FrmKemaskiniSubjaketFailData.getSubjaketFail(idSubjaket);
			Hashtable ha = (Hashtable)paparSubjaketFail.get(0);
			context.put("noFailSubjaket",ha.get("no_Fail_Subjaket"));
			context.put("selectPegawai",HTML.SelectPegawai("socPegawai",Long.parseLong(ha.get("id_Pegawai").toString()),"disabled"));
	    	context.put("tarikhSubjaketFail",ha.get("tarikh_Subjaket_Fail"));
	    	context.put("idSubjaket",ha.get("idSubjaket"));
	    	
	    	
			//listDokumen(session);
			listDokumen = FrmKemaskiniSubjaketFailData.getListDokumenBySubjaket(idSubjaket);
			this.context.put("SenaraiDokumen",listDokumen);
			setupPageDokumen(session, action1, listDokumen);
	    	
			listSubjaket(session);
			list = FrmKemaskiniSubjaketFailData.getListSubjaket();
			this.context.put("SenaraiSubjaket",list);
	    	
	    	
        }
       
        
//        else if ("simpanDokumen".equals(action1))
//        {
//        	
//        	vm = "app/pfd/frmKemaskiniSubjaketFail.jsp";
//        	context.put("action1","paparSubjaket");
//        	context.put("mode","papar");
//			context.put("readOnly","readonly");
//			context.put("disabled","disabled");
//			context.put("disabledDokumen","disabled");
//			context.put("modeDokumen","paparDokumen");
//        	
//        	String[] checkbox = this.request.getParameterValues("checkbox");
//        	if(checkbox != null)
//        	{
//        		if(boleh_simpan.equals("yes")){
//        		logic.deleteSubjaketDokumen(getParam("idSubjaket"));
//        		}
//        		for(int i = 0; i < checkbox.length; i++)
//        		{
//        			if(boleh_simpan.equals("yes")){
//        			logic.simpanDokumen(checkbox[i],getParam("idFail"),getParam("idSubjaket"));
//        			}
//        		}
//        		
//        	}
//
//			
//        	view_fail(session);
//			paparFail = FrmKemaskiniSubjaketFailData.getDataFail();
//			Hashtable h = (Hashtable) paparFail.get(0);
//			context.put("status",h.get("keterangan"));
//			context.put("idFail",h.get("idFail"));
//	    	context.put("noFail",h.get("noFail"));
//	    	context.put("tajukFail",h.get("tajukFail"));
//	    	
//	    	//listDokumen(session);
//			String idSubjaket = getParam("idSubjaket");
//			
//			listDokumen = FrmKemaskiniSubjaketFailData.getListDokumenBySubjaket(idSubjaket);
//			this.context.put("SenaraiDokumen",listDokumen);
//		
//	    	
//			view_SubjaketFail(session);
//	    	paparSubjaketFail = FrmKemaskiniSubjaketFailData.getSubjaketFail(idSubjaket);
//	    	if (!paparSubjaketFail.isEmpty()) {
//				Hashtable ha = (Hashtable)paparSubjaketFail.get(0);
//				System.out.println(ha.get("id_Pegawai"));
//				String ID_PEGAWAI = ha.get("id_Pegawai").toString();
//				context.put("noFailSubjaket",ha.get("no_Fail_Subjaket"));
//				context.put("selectPegawai",HTML.SelectPegawai("socPegawai",Long.parseLong(ID_PEGAWAI),"disabled"));
//		    	context.put("tarikhSubjaketFail",ha.get("tarikh_Subjaket_Fail"));
//	    	}
//        	
//        }
//        
//        else if("kemaskiniDokumen".equals(action1))
//        {
//        	vm = "app/pfd/frmKemaskiniSubjaketFail.jsp";
//        	context.put("action1","paparSubjaket");
//        	context.put("mode","papar");
//			context.put("readOnly","readonly");
//			context.put("disabled","disabled");
//			context.put("disabledDokumen","");
//			context.put("modeDokumen","kemaskiniDokumen");
//        	
//        	String[] checkbox = this.request.getParameterValues("checkbox");
//        	if(checkbox != null)
//        	{
//        		if(boleh_simpan.equals("yes")){
//        		logic.deleteSubjaketDokumen(getParam("idSubjaket"));
//        		}
//        		for(int i = 0; i < checkbox.length; i++)
//        		{
//        			if(boleh_simpan.equals("yes")){
//        			logic.simpanDokumen(checkbox[i],getParam("idFail"),getParam("idSubjaket"));
//        			}
//        		}
//        		
//        	}
//
//			
//        	view_fail(session);
//			paparFail = FrmKemaskiniSubjaketFailData.getDataFail();
//			Hashtable h = (Hashtable) paparFail.get(0);
//			context.put("status",h.get("keterangan"));
//			context.put("idFail",h.get("idFail"));
//	    	context.put("noFail",h.get("noFail"));
//	    	context.put("tajukFail",h.get("tajukFail"));
//	    	
//	    	//listDokumen(session);
//			String idSubjaket = getParam("idSubjaket");
//			listDokumen = FrmKemaskiniSubjaketFailData.getListDokumenBySubjaket(idSubjaket);
//			this.context.put("SenaraiDokumen",listDokumen);
//		
//	    	
//			
//			view_SubjaketFail(session);
//	    	paparSubjaketFail = FrmKemaskiniSubjaketFailData.getSubjaketFail(idSubjaket);
//			Hashtable ha = (Hashtable)paparSubjaketFail.get(0);
//			context.put("noFailSubjaket",ha.get("no_Fail_Subjaket"));
//			context.put("selectPegawai",HTML.SelectPegawai("socPegawai",Long.parseLong(ha.get("id_Pegawai").toString()),"disabled"));
//	    	context.put("tarikhSubjaketFail",ha.get("tarikh_Subjaket_Fail"));
//        }
//        
//        else if("updateDokumen".equals(action1))
//        {
//        	vm = "app/pfd/frmKemaskiniSubjaketFail.jsp";
//        	context.put("action1","paparSubjaket");
//        	context.put("mode","papar");
//			context.put("readOnly","readonly");
//			context.put("disabled","disabled");
//			context.put("disabledDokumen","disabled");
//			context.put("modeDokumen","updateDokumen");
//			
//			String idSJ = getParam("idSubjaket");
//        	
//        	String[] checkbox = this.request.getParameterValues("checkbox");
//        	if(checkbox != null)
//        	{
//        		if(boleh_simpan.equals("yes")){
//        		logic.deleteSubjaketDokumen(getParam("idSubjaket"));
//        		}
//        		for(int i = 0; i < checkbox.length; i++)
//        		{
//        			if(boleh_simpan.equals("yes")){
//        			logic.simpanDokumen(checkbox[i],getParam("idFail"),getParam("idSubjaket"));
//        			}
//        		}
//        		
//        	}
//
//			
//        	view_fail(session);
//			paparFail = FrmKemaskiniSubjaketFailData.getDataFail();
//			Hashtable h = (Hashtable) paparFail.get(0);
//			context.put("status",h.get("keterangan"));
//			context.put("idFail",h.get("idFail"));
//	    	context.put("noFail",h.get("noFail"));
//	    	context.put("tajukFail",h.get("tajukFail"));
//	    	
//	    	//listDokumen(session);
//			listDokumen = FrmKemaskiniSubjaketFailData.getListDokumenBySubjaket(idSJ);
//			this.context.put("SenaraiDokumen",listDokumen);
//		
//	    	
//			String idSubjaket = getParam("idSubjaket");
//			
//			view_SubjaketFail(session);
//	    	paparSubjaketFail = FrmKemaskiniSubjaketFailData.getSubjaketFail(idSubjaket);
//			Hashtable ha = (Hashtable)paparSubjaketFail.get(0);
//			context.put("noFailSubjaket",ha.get("no_Fail_Subjaket"));
//			context.put("selectPegawai",HTML.SelectPegawai("socPegawai",Long.parseLong(ha.get("id_Pegawai").toString()),"disabled"));
//	    	context.put("tarikhSubjaketFail",ha.get("tarikh_Subjaket_Fail"));
//        }
//       
        else{
        	
	    		String user_name = (String)session.getAttribute("_portal_username");
  	    		String user_id = (String)session.getAttribute("_ekptg_user_id");
  	    		String user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
  	    		String portal_role = (String)session.getAttribute("_portal_role");
  	    		String myrole = (String)session.getAttribute("myrole");

  	    		view_Seksyen(session);
	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
	         	String id_seksyen = String.valueOf(hA.get("id_seksyen"));
	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
	         	context.put("idSeksyen",(hA.get("id_seksyen")));
	         	context.put("idNegeri",(hA.get("id_negeri")));
  	    	
  	    		context.put("user_Name", user_name);
  	    		context.put("user_Id", user_id);
	    		
        	vm = "app/pfd/frmDaftarSubjaketFail.jsp";
        	
        	this.context.put("JumlahFail",FrmDaftarSubjaketFailData.totalFail(session));
        	
        	if (!"".equals(getParam("txtNoFail")))
  	    		noFail = getParam("txtNoFail");
        	 if (!"".equals(getParam("txtNoFailLama")))
	  	    		noFailLama = getParam("txtNoFailLama");
			if (!"".equals(getParam("txtTajukFail")))
				tajukFail = getParam("txtTajukFail");
			if (!"".equals(getParam("socNegeriD")))
				negeri = getParam("socNegeriD");
			if (!"".equals(getParam("socSeksyenD")))
				seksyen = getParam("socSeksyenD");
			if (!"".equals(getParam("socStatusD")))
				status = getParam("socStatusD");
			if (!"".equals(getParam("txdTarikhDaftar")))
				tarikhDaftar = getParam("txdTarikhDaftar");
			
	    	FrmDaftarSubjaketFailData.setCarianFail(noFail,noFailLama,tajukFail,negeri,seksyen,status,tarikhDaftar, user_id, myrole, user_negeri, id_seksyen);
	    	list = FrmDaftarSubjaketFailData.getList();
	    	session.setAttribute("Senaraifail", list);
	    	setupPage(session,action1,list);
	    	this.context.put("selectNegeriD",HTML.SelectNegeri("socNegeriD",Utils.parseLong(negeri),""));
	    	this.context.put("selectSeksyenD",HTML.SelectSeksyen("socSeksyenD",Utils.parseLong(seksyen),""));
	    	this.context.put("selectStatusD",HTML.SelectStatusFail("socStatusD",Utils.parseLong(status),""));
	    	this.context.put("txtNoFail", noFail);
	    	this.context.put("txtNoFailLama", noFailLama);
	    	this.context.put("txtTajukFail", tajukFail);
	    	this.context.put("txdTarikhDaftar", tarikhDaftar);
        }
//		Template template = this.engine.getTemplate(vm);
        context.put("action1", action1);
		return vm;
    }

	private String simpan(HttpSession session) throws Exception {

    	String idFailAsal = getParam("idFailAsal");
    	String user_id = (String)session.getAttribute("_ekptg_user_id");
    	
    	Hashtable h = new Hashtable();
	  	h.put("idFailAsal",idFailAsal);
	  	h.put("tarikhSubjaketFail", getParam("tarikhSubjaketFail"));
	  	h.put("id_Masuk",user_id);
	  	
	    return FrmKemaskiniSubjaketFailData.addSubjaket(h);
	    
	}

	private void view_Seksyen(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmLogDokumenData.setSeksyen(user_id);
		
	}

	private String getKnowSJ(String no_fail) {
		// TODO Auto-generated method stub
		String Flag= "true";

	    int intflag = no_fail.indexOf("SJ");
	    if (intflag != -1 ){
			Flag= "false";
	    } 
		return Flag;
	}

	private void view_fail2(HttpSession session) throws Exception {
    	int id = Integer.parseInt(getParam("idFail"));
    	FrmKemaskiniSubjaketFailData.setDataFailAll(id);
	  }
	
	private void updateSubjaket(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idSubjaket = getParam("idSubjaket");
		
		Hashtable h = new Hashtable();
		h.put("idSubjaket",idSubjaket);
		//h.put("id_nama_penerima", getParam("socPegawai"));
		h.put("id_nama_penerima", getParam("user_Id"));
	    h.put("id_Kemaskini",user_id);
	    FrmKemaskiniSubjaketFailData.updateSubjaketFail(h);
		
	}


	private void view_SubjaketFailSimpan(String idSubjaket) throws Exception {
	    FrmKemaskiniSubjaketFailData.setSubjaketFail(idSubjaket);
		
	}

	private void view_SubjaketFail(HttpSession session) throws Exception {
		String idSubjaket = getParam("idSubjaket");
	    FrmKemaskiniSubjaketFailData.setSubjaketFail(idSubjaket);
		
	}


	private void listDokumen(HttpSession session) throws Exception {
		String id = getParam("idFail");
    	FrmKemaskiniSubjaketFailData.setListDokumen(id);
		
	}


	private void listSubjaket(HttpSession session) throws Exception {
		int id = Integer.parseInt(getParam("idFail"));
    	FrmKemaskiniSubjaketFailData.setListSubjaket(id);
		
	}


	private String simpanSubjaket(HttpSession session) throws Exception {
		Vector senaraiDataFailAll = FrmKemaskiniSubjaketFailData.getDataFailAll();
		Hashtable k = (Hashtable) senaraiDataFailAll.get(0);
		
		
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idFail = getParam("idFail");
		String noFail = getParam("noFail");
		
		String no_root_file = FrmKemaskiniSubjaketFailData.getnorootfile(idFail);
		
		Hashtable h = new Hashtable();
		   
	    //h.put("id_Pegawai", getParam("socPegawai"));
	    h.put("id_Pegawai",user_id);
	    h.put("tarikh_Subjaket_Fail", getParam("txtTarikhSubjaketFail"));
	    h.put("tarikh_Masuk_Fail", getParam("txtTarikhMskFail"));
	    h.put("id_Fail",getParam("idFail"));
	    h.put("no_Fail",getParam("noFail"));
	    h.put("no_root_file", no_root_file);
	    
	    String no_fail_SJ = FrmKemaskiniSubjaketFailData.addSubjeket(h,k);
	    String[] values_Fail_bucket = no_fail_SJ.split(",");

	    String no_file_new = values_Fail_bucket[0];
	    no_file_new = no_file_new.replace("_"," ");
	    String id_subjeket = values_Fail_bucket[1];
	    String SJ_SEQ = values_Fail_bucket[2];

	    FrmKemaskiniSubjaketFailData.updateFailSJAsal(h);
	    String id_fail =  FrmKemaskiniSubjaketFailData.add(h,k, no_file_new,SJ_SEQ); 
	    return id_subjeket;
	}


	private void view_fail(HttpSession session) throws Exception {
		String id = getParam("idFailAsal");
    	System.out.println("idFail"+id);
	    FrmKemaskiniSubjaketFailData.setDataFail(id);
		
	}


	public void setupPage(HttpSession session,String action1,Vector list) {
		try {
		
		this.context.put("totalRecords",list.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
		}
	    
	    if ("getNext".equals(action1)) {
	    	page++;
	    } else if ("getPrevious".equals(action1)) {
	    	page--;
	    } else if ("getPage".equals(action1)) {
	    	page = getParamAsInteger("value");
	    } else if ("doChangeItemPerPage".equals(action1)) {
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
	
	public void setupPageDokumen(HttpSession session,String action1,Vector list) {
		try {
		
		this.context.put("totalRecords",list.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
		}
	    
	    if ("getNext".equals(action1)) {
	    	page++;
	    } else if ("getPrevious".equals(action1)) {
	    	page--;
	    } else if ("getPage".equals(action1)) {
	    	page = getParamAsInteger("value");
	    } else if ("doChangeItemPerPage".equals(action1)) {
	       itemsPerPage = getParamAsInteger("itemsPerPage");
	    }
	    	
	    Paging paging = new Paging(session,list,itemsPerPage);
		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("SenaraiDokumen",paging.getPage(page));
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
