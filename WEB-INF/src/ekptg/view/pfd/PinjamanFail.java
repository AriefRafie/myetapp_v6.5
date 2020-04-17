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
import ekptg.model.pfd.FrmDaftarPinjamanFailData;
import ekptg.model.pfd.FrmPinjamanFailData;

public class PinjamanFail extends AjaxBasedModule{

	@Override
	public String doTemplate2() throws Exception {

		 String vm = "";
		 String noFail = "";
         String tajukFail = "";
         String negeri = getParam("socNegeriD");
         String seksyen = getParam("socSeksyenD");
         String status = getParam("socStatusD");
         String tarikhDaftar = "";
		 String submit = getParam("command");
		 String action = getParam("action");
		 String idFail = getParam("idFail");
		 String idPergerakan = getParam("idPergerakanfail");
		 context.put("idPergerakanfail",idPergerakan);
		 context.put("idFail",idFail);
		 HttpSession session = this.request.getSession();
		 String userName = (String)session.getAttribute("_portal_username");
		 Vector list = null;
		 Vector paparFail = null;
		 Vector paparPergerakan = null;
		 Date now = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 
		 FrmDaftarPinjamanFailData listFail = new FrmDaftarPinjamanFailData();
		 FrmPinjamanFailData pinjamanFail = new FrmPinjamanFailData();
		 
		 if ("tambahPinjaman".equals(action)){
			 
			 vm = "app/pfd/frmPinjamanFail.jsp";
			
			 
			 pinjamanFail.setDataFail(idFail);
			 paparFail = pinjamanFail.getDataFail();
			 Hashtable hF = (Hashtable)paparFail.get(0);
			 
			 context.put("noFail",hF.get("noFail"));
			 context.put("tajukFail", hF.get("tajukFail"));
			 context.put("tarikhDaftar",hF.get("tarikh_Daftar_Fail"));
			 context.put("statusFail",hF.get("keterangan1"));

			 if(hF.get("keterangan2").equals("")){
				 
				 context.put("mode","baru");
				 context.put("readOnly","");
				 context.put("disabled","");
				 
				 context.put("namaPeminjam",userName);
				 context.put("tarikhPrmhnnPinjaman",sdf.format(now));
				 context.put("flagPinjamanFail","");
				 context.put("tempohBiasaDari","");
				 context.put("tempohBiasaHingga","");
				 context.put("tempohSdpDari","");
				 context.put("tempohSdpHingga","");
				 context.put("tujuan_Pinjaman","");
				 context.put("flagLanjutTempoh","");
				 context.put("tempohLanjutDari","");
				 context.put("tempohLanjutHingga","");
				 
			 }
			 else if(!hF.get("keterangan2").equals("")){
				 
				 viewPergerakan(session);
				 paparPergerakan = pinjamanFail.getDataPergerakan();
				 Hashtable hP = (Hashtable)paparPergerakan.get(0);
				 
				 if(userName.equals(hP.get("namaPeminjam"))){
				 
				 context.put("mode","papar");
				 context.put("readOnly","readonly");
				 context.put("disabled","disabled");
				 
				 context.put("namaPeminjam",hP.get("namaPeminjam"));
				 context.put("tarikhPrmhnnPinjaman",hP.get("tarikhPrmhnnPinjaman"));
				 context.put("flagPinjamanFail",hP.get("flagPinjamanFail"));
				 context.put("tempohBiasaDari",hP.get("tempohBiasaDari"));
				 context.put("tempohBiasaHingga",hP.get("tempohBiasaHingga"));
				 context.put("tempohSdpDari",hP.get("tempohSdpDari"));
				 context.put("tempohSdpHingga",hP.get("tempohSdpHingga"));
				 context.put("tujuan_Pinjaman",hP.get("tujuan_Pinjaman"));
				 context.put("flagLanjutTempoh",hP.get("flagLanjutTempoh"));
				 context.put("tempohLanjutDari",hP.get("tempohLanjutDari"));
				 context.put("tempohLanjutHingga",hP.get("tempohLanjutHingga"));
				 }
				 else{
				 
				 context.put("mode","");
				 context.put("readOnly","readonly");
				 context.put("disabled","disabled");
				 
				 context.put("namaPeminjam",hP.get("namaPeminjam"));
				 context.put("tarikhPrmhnnPinjaman",hP.get("tarikhPrmhnnPinjaman"));
				 context.put("flagPinjamanFail",hP.get("flagPinjamanFail"));
				 context.put("tempohBiasaDari",hP.get("tempohBiasaDari"));
				 context.put("tempohBiasaHingga",hP.get("tempohBiasaHingga"));
				 context.put("tempohSdpDari",hP.get("tempohSdpDari"));
				 context.put("tempohSdpHingga",hP.get("tempohSdpHingga"));
				 context.put("tujuan_Pinjaman",hP.get("tujuan_Pinjaman"));
				 context.put("flagLanjutTempoh",hP.get("flagLanjutTempoh"));
				 context.put("tempohLanjutDari",hP.get("tempohLanjutDari"));
				 context.put("tempohLanjutHingga",hP.get("tempohLanjutHingga"));
				 }
			 }
			
			
		 }
		 else if ("simpanPinjaman".equals(action)){
			 
			 idPergerakan = simpanPinjaman(session);
			 
			 context.put("idPergerakanfail",idPergerakan);

			 vm = "app/pfd/frmPinjamanFail.jsp";
			 context.put("mode","papar");
			 context.put("readOnly","readonly");
			 context.put("disabled","disabled");
			 
			 viewPergerakan(session);
			 paparPergerakan = pinjamanFail.getDataPergerakan();
			 Hashtable hP = (Hashtable)paparPergerakan.get(0);
			 
			 context.put("namaPeminjam",hP.get("namaPeminjam"));
			 context.put("tarikhPrmhnnPinjaman",hP.get("tarikhPrmhnnPinjaman"));
			 context.put("flagPinjamanFail",hP.get("flagPinjamanFail"));
			 context.put("tempohBiasaDari",hP.get("tempohBiasaDari"));
			 context.put("tempohBiasaHingga",hP.get("tempohBiasaHingga"));
			 context.put("tempohSdpDari",hP.get("tempohSdpDari"));
			 context.put("tempohSdpHingga",hP.get("tempohSdpHingga"));
			 context.put("tujuan_Pinjaman",hP.get("tujuan_Pinjaman"));
			 context.put("flagLanjutTempoh",hP.get("flagLanjutTempoh"));
			 context.put("tempohLanjutDari",hP.get("tempohLanjutDari"));
			 context.put("tempohLanjutHingga",hP.get("tempohLanjutHingga"));
			 
		 }
		 else if ("kemaskiniPinjaman".equals(action)){
			 
			 
			 vm = "app/pfd/frmPinjamanFail.jsp";
			 context.put("mode","kemaskini");
			 context.put("readOnly","");
			 context.put("disabled","");
			 
			 viewPergerakan(session);
			 paparPergerakan = pinjamanFail.getDataPergerakan();
			 Hashtable hP = (Hashtable)paparPergerakan.get(0);
			 
			 context.put("namaPeminjam",hP.get("namaPeminjam"));
			 context.put("tarikhPrmhnnPinjaman",hP.get("tarikhPrmhnnPinjaman"));
			 context.put("flagPinjamanFail",hP.get("flagPinjamanFail"));
			 context.put("tempohBiasaDari",hP.get("tempohBiasaDari"));
			 context.put("tempohBiasaHingga",hP.get("tempohBiasaHingga"));
			 context.put("tempohSdpDari",hP.get("tempohSdpDari"));
			 context.put("tempohSdpHingga",hP.get("tempohSdpHingga"));
			 context.put("tujuan_Pinjaman",hP.get("tujuan_Pinjaman"));
			 context.put("flagLanjutTempoh",hP.get("flagLanjutTempoh"));
			 context.put("tempohLanjutDari",hP.get("tempohLanjutDari"));
			 context.put("tempohLanjutHingga",hP.get("tempohLanjutHingga"));
			 
		 }
		 else if ("updatePinjaman".equals(action)){
			 
			 kemaskiniPinjaman(session);
			 vm = "app/pfd/frmPinjamanFail.jsp";
			 context.put("mode","papar");
			 context.put("readOnly","readonly");
			 context.put("disabled","disabled");
			 
			 viewPergerakan(session);
			 paparPergerakan = pinjamanFail.getDataPergerakan();
			 Hashtable hP = (Hashtable)paparPergerakan.get(0);
			 
			 context.put("namaPeminjam",hP.get("namaPeminjam"));
			 context.put("tarikhPrmhnnPinjaman",hP.get("tarikhPrmhnnPinjaman"));
			 context.put("flagPinjamanFail",hP.get("flagPinjamanFail"));
			 context.put("tempohBiasaDari",hP.get("tempohBiasaDari"));
			 context.put("tempohBiasaHingga",hP.get("tempohBiasaHingga"));
			 context.put("tempohSdpDari",hP.get("tempohSdpDari"));
			 context.put("tempohSdpHingga",hP.get("tempohSdpHingga"));
			 context.put("tujuan_Pinjaman",hP.get("tujuan_Pinjaman"));
			 context.put("flagLanjutTempoh",hP.get("flagLanjutTempoh"));
			 context.put("tempohLanjutDari",hP.get("tempohLanjutDari"));
			 context.put("tempohLanjutHingga",hP.get("tempohLanjutHingga"));
			 
		 }
		 else{
		
			vm = "app/pfd/frmDaftarPinjamanFail.jsp";
    	 	if (!"".equals(getParam("txtNoFail")))
	    		noFail = getParam("txtNoFail");
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
			
			listFail.setCarianFail(noFail,tajukFail,negeri,seksyen,status,tarikhDaftar);
	    	list = listFail.getList();
	    	this.context.put("SenaraiFail", list);
	    	this.context.put("selectNegeriD",HTML.SelectNegeri("socNegeriD",Utils.parseLong(negeri),""));
	    	this.context.put("selectSeksyenD",HTML.SelectSeksyen("socSeksyenD",Utils.parseLong(seksyen),""));
	    	this.context.put("selectStatusD",HTML.SelectStatusFail("socStatusD",Utils.parseLong(status),""));
	    	this.context.put("txtNoFail", noFail);
	    	this.context.put("txtTajukFail", tajukFail);
	    	this.context.put("txdTarikhDaftar", tarikhDaftar);
	    	
	    	setupPage(session,action,list);
		 }
		
		return vm;
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
	
	public String simpanPinjaman(HttpSession session)throws Exception{
		
		String user_id = (String)session.getAttribute("_ekptg_user_id");
	    Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    
	    Hashtable h = new Hashtable();
	    h.put("idFail",getParam("idFail"));
	    h.put("idPeminjam",user_id);
	    h.put("tarikhPinjaman",sdf.format(now));
	    h.put("flagPinjamanFail",getParam("sorPinjamanFail"));
	    h.put("tempohBiasaDari",getParam("txdDariTkhPinjaman"));
	    h.put("tempohBiasaHingga",getParam("txdHinggaTkhPinjaman"));
	    h.put("tempohSdpDari",getParam("txdDariTkhSDP"));
	    h.put("tempohSdpHingga",getParam("txdHinggaTkhSDP"));
	    h.put("tujuanPinjaman",getParam("txtTujuanPinjaman"));
	    h.put("flagLanjutTempoh",getParam("sbcTempohDilanjutkan"));
	    h.put("tempohLanjutDari",getParam("txdDariTkhLanjutan"));
	    h.put("tempohLanjutHingga",getParam("txdHinggaTkhLanjutan"));
	    h.put("id_Masuk",user_id);
	    
	    
	    return FrmPinjamanFailData.addPinjaman(h);
	    	
	}
	
	public void kemaskiniPinjaman(HttpSession session)throws Exception{
		
		String user_id = (String)session.getAttribute("_ekptg_user_id");
	    Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    
	    Hashtable h = new Hashtable();
	    h.put("idPergerakanfail",getParam("idPergerakanfail"));
	    h.put("idFail",getParam("idFail"));
	    h.put("tarikhPinjaman",sdf.format(now));
	    h.put("flagPinjamanFail",getParam("sorPinjamanFail"));
	    h.put("tempohBiasaDari",getParam("txdDariTkhPinjaman"));
	    h.put("tempohBiasaHingga",getParam("txdHinggaTkhPinjaman"));
	    h.put("tempohSdpDari",getParam("txdDariTkhSDP"));
	    h.put("tempohSdpHingga",getParam("txdHinggaTkhSDP"));
	    h.put("tujuanPinjaman",getParam("txtTujuanPinjaman"));
	    h.put("flagLanjutTempoh",getParam("sbcTempohDilanjutkan"));
	    h.put("tempohLanjutDari",getParam("txdDariTkhLanjutan"));
	    h.put("tempohLanjutHingga",getParam("txdHinggaTkhLanjutan"));
	    h.put("id_Kemaskini",user_id);
	    
	    
	    FrmPinjamanFailData.updatePinjaman(h);
	    	
	}
	
	public void viewPergerakan (HttpSession session)throws Exception{
		
		String id = getParam("idPergerakanfail");
		System.out.println("idPergerakanfail " +id);
		FrmPinjamanFailData.paparPergerakan(id);
		
	}
}
