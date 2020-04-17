package ekptg.view.ppk;


import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmTukaranStatus;
/*
 * @author
 * Muhamad Syazreen bin Yahaya
 */

//TODO: Add Audit Trail

public class FrmTukarStatus extends  AjaxBasedModule{

	static Logger myLogger = Logger.getLogger(FrmTukarStatus.class);	
	FrmTukaranStatus model = new FrmTukaranStatus();	
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	HttpSession session = null;
	String action = null;
	private final String PATH="app/ppk/";
	private String vm = PATH +"frmTukarStatus.jsp";
	
	
	public String doTemplate2() throws Exception{

		session = request.getSession();
		
		
		String submit = getParam("command");
		action = getParam("action");
    	myLogger.info("[submit] :: " +submit);

		String doPost = (String) session.getAttribute("doPost");
		myLogger.info("check doPost :"+doPost);    	
    	    	
    	Vector list = new Vector();
    	Vector keputusanPermohonan = new Vector();
    	Vector notisPerbicaraan = new Vector();
    	Vector perintah = new Vector();
    	Vector list_sub = null;
    	Vector list_status = null;
    	
    	list.clear();
    	keputusanPermohonan.clear();
    	notisPerbicaraan.clear();
    	perintah.clear();
    	
    	String usid="";  
   		usid=(String)session.getAttribute("_ekptg_user_id");
    	
   		context.put("carianrekod","");
   		context.put("txtNoFailSub", "");
   		context.put("txtNoFailSub_selected", "");
		context.put("list_sub",""); 
		context.put("cari_sub",""); 
		context.put("list_status","");
		
		context.put("ID_FAIL_TEMP","");
		context.put("ID_PERMOHONAN_TEMP","");
		context.put("NO_FAIL_TEMP", "");
		context.put("ID_SUBURUSAN_TEMP", "");  
		
		context.put("total_harta","");
		context.put("list_fail","");  
		context.put("view_list_fail","");
		context.put("id_fail_carian","");
		
    	context.put("ScrollX",getParam("ScrollX"));
    	context.put("ScrollY",getParam("ScrollY"));        

       
    	if ("Cari".equals(submit)){
    		
    		String id_permohonan = "";
			String id_fail = "";
			String id_status = "";
			String id_suburusanstatusfail = "";
			String keterangan_status = "";
    		String noFail = "";
    		String seksyen = "";
    		noFail = getParam("txtNoFail");
    		
    		model.setList(noFail,usid);
    		list = model.getList();
    		
    		if(list.size()!=0){
    			Hashtable x = (Hashtable) list.get(0);
    			id_permohonan = x.get("id_permohonan").toString();
    			id_fail = x.get("id_fail").toString();
    			id_status = x.get("id_status").toString();	
    			id_suburusanstatusfail = x.get("id_suburusanstatusfail").toString();	
    			keterangan_status = x.get("keterangan").toString();
    			seksyen = x.get("seksyen").toString();
    			
    			context.put("carianrekod","");
    		}else{
    			if(noFail!="")
    			{
    				context.put("carianrekod","tiada");
    			}else{
    				context.put("carianrekod","");
    			}
    		}
    		
    		//check id keputusan permohonan ade / xde
    		String id_keputusanpermohonan = "";   		
    		if(id_permohonan!=""){   			
    		
    			keputusanPermohonan = model.getKeputusanPermohonan(id_permohonan);  
    			
    			if(keputusanPermohonan.size()!=0){
        			Hashtable kp = (Hashtable) keputusanPermohonan.get(0);
        			id_keputusanpermohonan = kp.get("id_keputusanpermohonan").toString();
        		}
    		}
    		
    		//check id perbicaraan ade / xde
    		String id_perbicaraan = "";    		
    		if(id_keputusanpermohonan!=""){
    		
    			notisPerbicaraan = model.getNotisPerbicaraan(id_keputusanpermohonan); 
    			
    			if(notisPerbicaraan.size()!=0){
        			Hashtable np = (Hashtable) notisPerbicaraan.get(0);
        			id_perbicaraan = np.get("id_perbicaraan").toString();
        		}   			
    		}
    		
    		//check id perintah ade / xde
    		String id_perintah = "";    		
    		if(id_perbicaraan!=""){
    		
    			perintah = model.getPerintah(id_perbicaraan); 
    			
    			if(perintah.size()!=0){
        			Hashtable p = (Hashtable) perintah.get(0);
        			id_perintah = p.get("id_perintah").toString();
        		}   			
    		}
    		
    		context.put("id_perintah", id_perintah);
    		context.put("id_keputusanpermohonan", id_keputusanpermohonan);
    		context.put("id_perbicaraan", id_perbicaraan);
    		context.put("id_permohonan", id_permohonan);
			context.put("id_fail", id_fail);
			context.put("id_status", id_status);
			context.put("keterangan_status", keterangan_status);
			context.put("id_suburusanstatusfail", id_suburusanstatusfail);
			context.put("seksyen", seksyen);
			
    		context.put("listSemak",list);
    		context.put("listSemak_size",list.size());
    		context.put("nofail", noFail.trim());
    		
    		
    		//vm = "app/ppk/frmTukarStatus.jsp";
    		
    	}//close cari
    	
    	else if ("tukarstatus".equals(submit)){
    		
    		if (doPost.equals("true")) {
    			tukarstatus(session); 
    		}
    		context.put("listSemak","");
    		context.put("listSemak_size","");
    		context.put("nofail", "");
    		context.put("id_permohonan", "");
			context.put("id_fail", "");
			context.put("id_status", "");
			context.put("id_suburusanstatusfail", "");
			context.put("keterangan_status", "");
			context.put("seksyen", "");
			context.put("id_perintah", "");
    		context.put("id_keputusanpermohonan", "");
    		context.put("id_perbicaraan", "");
    		context.put("id_permohonan", "");
    		
    		
    		//vm = "app/ppk/frmTukarStatus.jsp";
    	}
    	// SKRIN SEMAK SUBURUSANSTATUSFAIL
    	
    	
    	else if ("tukarstatus_sub".equals(submit)){
    		
    		tukarstatus_sub(session);
    		
    		context.put("id_fail_carian",getParam("id_fail_carian"));
    		String txtNoFailSub = getParam("txtNoFailSub");    		 
    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")!=null)
    		{    		 		
    		context.put("txtNoFailSub_selected", model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")); 
    		}
    		context.put("txtNoFailSub", model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL"));  
    		
    		model.setListSub(getParam("id_fail_carian"));
    		list_sub = model.getListSub();
    		context.put("list_sub",list_sub);
    		context.put("list_fail",model.search_nofail(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")+"",usid,"","",""));  
    		context.put("cari_sub","yes"); 
    		list_status = model.list_status(getParam("id_fail_carian"));
    		context.put("list_status",list_status);
    		context.put("view_list_fail","yes");
    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL")!=null)
    		{
    			context.put("ID_FAIL_TEMP",model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL"));
    			context.put("ID_PERMOHONAN_TEMP",model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN"));
    			context.put("NO_FAIL_TEMP",model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL"));
    			context.put("ID_SUBURUSAN_TEMP",model.getMainFail(getParam("id_fail_carian")).get("ID_SUBURUSAN"));
    		}
    		else
    		{
    			context.put("ID_FAIL_TEMP","");
        		context.put("ID_PERMOHONAN_TEMP","");
        		context.put("NO_FAIL_TEMP", "");
        		context.put("ID_SUBURUSAN_TEMP", "");   			
    		
    		}
    		    	
    		urusan_status_selesai(session);
    		
    		context.put("listSemak","");
    		context.put("listSemak_size","");
    		context.put("nofail", "");
    		context.put("id_permohonan", "");
			context.put("id_fail", "");
			context.put("id_status", "");
			context.put("id_suburusanstatusfail", "");
			context.put("keterangan_status", "");
			context.put("seksyen", "");
			context.put("id_perintah", "");
    		context.put("id_keputusanpermohonan", "");
    		context.put("id_perbicaraan", "");
    		context.put("id_permohonan", "");
    		
    		total_harta(session);
    		
    		//vm = "app/ppk/frmTukarStatus.jsp";
    		
    		String id_permohonan ="";
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL")!=null)
    		{
    			id_permohonan = model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN")+"";
    		}
    		
    		headerppk_baru(session, id_permohonan, "Y", "", "T");
    		
    		
    		
    	}
    	
    	////bawah ni suma fungsi tebaru dalam modul ni : by Razman
    	
    	else if ("paparSub".equals(submit)){
    		context.put("id_fail_carian",getParam("id_fail_carian"));
    		String txtNoFailSub = getParam("txtNoFailSub");  
    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")!=null)
    		{    		 		
    		context.put("txtNoFailSub_selected", model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")); 
    		}
    		context.put("txtNoFailSub", model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL"));  
    		
    		model.setListSub(getParam("id_fail_carian"));
    		list_sub = model.getListSub();
    		context.put("list_sub",list_sub);
    		context.put("list_fail",model.search_nofail(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")+"",usid,"","",""));  
    		context.put("cari_sub","yes"); 
    		list_status = model.list_status(getParam("id_fail_carian"));
    		context.put("list_status",list_status);
    		context.put("view_list_fail","yes");
    		String id_permohonan = "";
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL")!=null)
    		{
    			id_permohonan = model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN")+"";
    			context.put("ID_FAIL_TEMP",model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL"));
    			context.put("ID_PERMOHONAN_TEMP",model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN"));
    			context.put("NO_FAIL_TEMP",model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL"));
    			context.put("ID_SUBURUSAN_TEMP",model.getMainFail(getParam("id_fail_carian")).get("ID_SUBURUSAN"));
    		
    		
    		}
    		else
    		{
    			context.put("ID_FAIL_TEMP","");
        		context.put("ID_PERMOHONAN_TEMP","");
        		context.put("NO_FAIL_TEMP", "");
        		context.put("ID_SUBURUSAN_TEMP", "");   			
    		
    		}    		
    		
    		
    		
    		urusan_status_selesai(session); 
    		
    		
    		context.put("listSemak","");
    		context.put("listSemak_size","");
    		context.put("nofail", "");
    		context.put("id_permohonan", "");
			context.put("id_fail", "");
			context.put("id_status", "");
			context.put("id_suburusanstatusfail", "");
			context.put("keterangan_status", "");
			context.put("seksyen", "");
			context.put("id_perintah", "");
    		context.put("id_keputusanpermohonan", "");
    		context.put("id_perbicaraan", "");
    		context.put("id_permohonan", "");
    		
    		total_harta(session);
    		headerppk_baru(session,id_permohonan,"Y","","T");
    		//vm = "app/ppk/frmTukarStatus.jsp";
    	} 
    	
    	else if ("cariMainSub".equals(submit)){
    		String txtNoFailSub = getParam("txtNoFailSub");    		
    		context.put("txtNoFailSub", txtNoFailSub.trim());     		
    		context.put("list_fail",model.search_nofail(txtNoFailSub.trim(),usid,"","","")); 
    		context.put("view_list_fail","yes");
    		/*
    		model.setListSub(txtNoFailSub.trim());
    		list_sub = model.getListSub();
    		context.put("list_sub",list_sub); 
    		context.put("cari_sub","yes"); 
    		list_status = model.list_status(txtNoFailSub.trim());
    		context.put("list_status",list_status);
    		
    		if(model.getMainFail(txtNoFailSub.trim()).get("ID_FAIL")!=null)
    		{
    			context.put("ID_FAIL_TEMP",model.getMainFail(txtNoFailSub.trim()).get("ID_FAIL"));
    			context.put("ID_PERMOHONAN_TEMP",model.getMainFail(txtNoFailSub.trim()).get("ID_PERMOHONAN"));
    			context.put("NO_FAIL_TEMP",model.getMainFail(txtNoFailSub.trim()).get("NO_FAIL"));
    			context.put("ID_SUBURUSAN_TEMP",model.getMainFail(txtNoFailSub.trim()).get("ID_SUBURUSAN"));
    		}
    		else
    		{
    			context.put("ID_FAIL_TEMP","");
        		context.put("ID_PERMOHONAN_TEMP","");
        		context.put("NO_FAIL_TEMP", "");
        		context.put("ID_SUBURUSAN_TEMP", "");   			
    		
    		}
    		
    		
    		urusan_status_selesai(session); 
    		*/
    		
    		context.put("listSemak","");
    		context.put("listSemak_size","");
    		context.put("nofail", "");
    		context.put("id_permohonan", "");
			context.put("id_fail", "");
			context.put("id_status", "");
			context.put("id_suburusanstatusfail", "");
			context.put("keterangan_status", "");
			context.put("seksyen", "");
			context.put("id_perintah", "");
    		context.put("id_keputusanpermohonan", "");
    		context.put("id_perbicaraan", "");
    		context.put("id_permohonan", "");
    		
    		total_harta(session);
    		
    		//vm = "app/ppk/frmTukarStatus.jsp";
    	} 
    	
    	//sss
    	else if ("hapusSub".equals(submit)){
    		context.put("view_list_fail","yes");
    		context.put("id_fail_carian",getParam("id_fail_carian"));
    		String txtNoFailSub = getParam("txtNoFailSub");    		
    		context.put("txtNoFailSub", txtNoFailSub.trim());
    		context.put("list_fail",model.search_nofail(txtNoFailSub.trim(),usid,"","",""));  
    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")!=null)
    		{    		 		
    		context.put("txtNoFailSub_selected", model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")); 
    		}
    		
    		String[] ids1 = this.request.getParameterValues("ids1_delete");
    		if (ids1 != null) {
    			for (int i = 0; i < ids1.length; i++) {						
    			myLogger.info("ids1 :"+ids1);		
    			model.deleteSub(ids1[i]); 
    				}
    			}	
    		
    		
    		String[] ids1_data = this.request.getParameterValues("ids1_delete_data");
    		if (ids1_data != null) {
    			for (int i = 0; i < ids1_data.length; i++) {						
    			myLogger.info("ids1_data :"+ids1_data);		
    			model.deleteData(session,ids1_data[i],getParam("id_fail_carian")); 
    			//('8','170','9','14','53','70','18','14','44','47','41','25','21','151','152')
    				}
    			}	
    		
    		
    				
    		model.setListSub(getParam("id_fail_carian"));
    		list_sub = model.getListSub();
    		context.put("list_sub",list_sub); 
    		context.put("cari_sub","yes"); 
    		list_status = model.list_status(getParam("id_fail_carian"));
    		context.put("list_status",list_status);
    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL")!=null)
    		{
    			context.put("ID_FAIL_TEMP",model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL"));
    			context.put("ID_PERMOHONAN_TEMP",model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN"));
    			context.put("NO_FAIL_TEMP",model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL"));
    			context.put("ID_SUBURUSAN_TEMP",model.getMainFail(getParam("id_fail_carian")).get("ID_SUBURUSAN"));
    		}
    		else
    		{
    			context.put("ID_FAIL_TEMP","");
        		context.put("ID_PERMOHONAN_TEMP","");
        		context.put("NO_FAIL_TEMP", "");
        		context.put("ID_SUBURUSAN_TEMP", "");   			
    		
    		}
    		
    		urusan_status_selesai(session);
    		
    		context.put("listSemak","");
    		context.put("listSemak_size","");
    		context.put("nofail", "");
    		context.put("id_permohonan", "");
			context.put("id_fail", "");
			context.put("id_status", "");
			context.put("id_suburusanstatusfail", "");
			context.put("keterangan_status", "");
			context.put("seksyen", "");
			context.put("id_perintah", "");
    		context.put("id_keputusanpermohonan", "");
    		context.put("id_perbicaraan", "");
    		context.put("id_permohonan", "");
    		
    		total_harta(session);
    		
    		String id_permohonan ="";
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL")!=null)
    		{
    			id_permohonan = model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN")+"";
    		}
    		
    		headerppk_baru(session, id_permohonan, "Y", "", "T");
    		
    		
    		//vm = "app/ppk/frmTukarStatus.jsp";
    	} 
    	
    	
    	else if ("tambahSub".equals(submit)){
    		context.put("view_list_fail","yes");
    		context.put("id_fail_carian",getParam("id_fail_carian"));
    		String ID_PERMOHONAN = getParam("ID_PERMOHONAN_TEMP");
    		String ID_SUBURUSANSTATUS = getParam("ID_SUBURUSANSTATUS_ADD");
    		String AKTIF_ADD = getParam("AKTIF_ADD");
    		String ID_FAIL = getParam("ID_FAIL_TEMP"); 
    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")!=null)
    		{    		 		
    		context.put("txtNoFailSub_selected", model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")); 
    		}
    		
    		String[] ID_SUBURUSANSTATUSFAIL = this.request.getParameterValues("ID_SUBURUSANSTATUSFAIL");
			//String ID_SUBURUSANSTATUS = getParam("ID_SUBURUSANSTATUS");
			String[] AKTIF = this.request.getParameterValues("AKTIF");
			if (ID_SUBURUSANSTATUSFAIL != null) {
				for (int i = 0; i < ID_SUBURUSANSTATUSFAIL.length; i++) {						
				myLogger.info("ID_SUBURUSANSTATUSFAIL :"+ID_SUBURUSANSTATUSFAIL[i]);	
				String ID_SUBURUSANSTATUS_TEMP = "ID_SUBURUSANSTATUS"+(i+1);
				myLogger.info("ID_SUBURUSANSTATUS_TEMP :"+ID_SUBURUSANSTATUS_TEMP);		
				myLogger.info("getParam(ID_SUBURUSANSTATUS_TEMP) :"+getParam(ID_SUBURUSANSTATUS_TEMP));	
				model.updateSSF(session,ID_SUBURUSANSTATUSFAIL[i],getParam(ID_SUBURUSANSTATUS_TEMP),AKTIF[i],usid);	
					}
				}
    		
    		model.insertSub(ID_PERMOHONAN,ID_SUBURUSANSTATUS,AKTIF_ADD,ID_FAIL,usid);   		
    		
    		String txtNoFailSub = getParam("txtNoFailSub");    		
    		context.put("txtNoFailSub", txtNoFailSub.trim());    		
    		model.setListSub(getParam("id_fail_carian"));
    		list_sub = model.getListSub();
    		context.put("list_sub",list_sub); 
    		context.put("list_fail",model.search_nofail(txtNoFailSub.trim(),usid,"","",""));  
    		context.put("cari_sub","yes"); 
    		list_status = model.list_status(getParam("id_fail_carian"));
    		context.put("list_status",list_status);
    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL")!=null)
    		{
    			context.put("ID_FAIL_TEMP",model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL"));
    			context.put("ID_PERMOHONAN_TEMP",model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN"));
    			context.put("NO_FAIL_TEMP",model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL"));
    			context.put("ID_SUBURUSAN_TEMP",model.getMainFail(getParam("id_fail_carian")).get("ID_SUBURUSAN"));
    		}
    		else
    		{
    			context.put("ID_FAIL_TEMP","");
        		context.put("ID_PERMOHONAN_TEMP","");
        		context.put("NO_FAIL_TEMP", "");
        		context.put("ID_SUBURUSAN_TEMP", "");   			
    		
    		}
    		
    		urusan_status_selesai(session);
    		
    		context.put("listSemak","");
    		context.put("listSemak_size","");
    		context.put("nofail", "");
    		context.put("id_permohonan", "");
			context.put("id_fail", "");
			context.put("id_status", "");
			context.put("id_suburusanstatusfail", "");
			context.put("keterangan_status", "");
			context.put("seksyen", "");
			context.put("id_perintah", "");
    		context.put("id_keputusanpermohonan", "");
    		context.put("id_perbicaraan", "");
    		context.put("id_permohonan", "");
    		
    		total_harta(session);
    		
    		String id_permohonan ="";
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL")!=null)
    		{
    			id_permohonan = model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN")+"";
    		}
    		
    		headerppk_baru(session, id_permohonan, "Y", "", "T");
    		
    		
    		//vm = "app/ppk/frmTukarStatus.jsp";
    	} 
    	
    	
				
    	
    	else if ("updateSub".equals(submit)){
    		context.put("view_list_fail","yes");
    		context.put("id_fail_carian",getParam("id_fail_carian"));
    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")!=null)
    		{    		 		
    		context.put("txtNoFailSub_selected", model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")); 
    		}
    		
			String[] ID_SUBURUSANSTATUSFAIL = this.request.getParameterValues("ID_SUBURUSANSTATUSFAIL");
			//String ID_SUBURUSANSTATUS = getParam("ID_SUBURUSANSTATUS");
			String[] AKTIF = this.request.getParameterValues("AKTIF");
			if (ID_SUBURUSANSTATUSFAIL != null) {
				for (int i = 0; i < ID_SUBURUSANSTATUSFAIL.length; i++) {						
				myLogger.info("ID_SUBURUSANSTATUSFAIL :"+ID_SUBURUSANSTATUSFAIL[i]);	
				String ID_SUBURUSANSTATUS_TEMP = "ID_SUBURUSANSTATUS"+(i+1);
				myLogger.info("ID_SUBURUSANSTATUS_TEMP :"+ID_SUBURUSANSTATUS_TEMP);		
				myLogger.info("getParam(ID_SUBURUSANSTATUS_TEMP) :"+getParam(ID_SUBURUSANSTATUS_TEMP));	
				model.updateSSF(session,ID_SUBURUSANSTATUSFAIL[i],getParam(ID_SUBURUSANSTATUS_TEMP),AKTIF[i],usid);	
					}
				}
			
			String txtNoFailSub = getParam("txtNoFailSub");    		
    		context.put("txtNoFailSub", txtNoFailSub.trim()); 
			
			if(model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL")!=null)
    		{
    			context.put("ID_FAIL_TEMP",model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL"));
    			context.put("ID_PERMOHONAN_TEMP",model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN"));
    			context.put("NO_FAIL_TEMP",model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL"));
    			context.put("ID_SUBURUSAN_TEMP",model.getMainFail(getParam("id_fail_carian")).get("ID_SUBURUSAN"));
    		}
    		else
    		{
    			context.put("ID_FAIL_TEMP","");
        		context.put("ID_PERMOHONAN_TEMP","");
        		context.put("NO_FAIL_TEMP", "");
        		context.put("ID_SUBURUSAN_TEMP", "");   			
    		
    		}
			
			   		
    		model.setListSub(getParam("id_fail_carian"));
    		list_sub = model.getListSub();
    		context.put("list_sub",list_sub); 
    		context.put("list_fail",model.search_nofail(txtNoFailSub.trim(),usid,"","",""));  
    		context.put("cari_sub","yes"); 
    		list_status = model.list_status(getParam("id_fail_carian"));
    		context.put("list_status",list_status);
    		
    		
    		urusan_status_selesai(session);
    		
    		context.put("listSemak","");
    		context.put("listSemak_size","");
    		context.put("nofail", "");
    		context.put("id_permohonan", "");
			context.put("id_fail", "");
			context.put("id_status", "");
			context.put("id_suburusanstatusfail", "");
			context.put("keterangan_status", "");
			context.put("seksyen", "");
			context.put("id_perintah", "");
    		context.put("id_keputusanpermohonan", "");
    		context.put("id_perbicaraan", "");
    		context.put("id_permohonan", "");
    		
    		total_harta(session);
    		
    		String id_permohonan ="";
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL")!=null)
    		{
    			id_permohonan = model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN")+"";
    		}
    		
    		headerppk_baru(session, id_permohonan, "Y", "", "T");
    		
    		
    		//vm = "app/ppk/frmTukarStatus.jsp";
		}
    	
    	else{

    		context.put("listSemak","");
    		context.put("listSemak_size","");
    		context.put("nofail", "");
    		context.put("id_permohonan", "");
			context.put("id_fail", "");
			context.put("id_status", "");
			context.put("id_suburusanstatusfail", "");
			context.put("keterangan_status", "");
			context.put("seksyen", "");
			context.put("id_perintah", "");
    		context.put("id_keputusanpermohonan", "");
    		context.put("id_perbicaraan", "");
    		context.put("id_permohonan", "");
    		context.put("txtNoFailSub", "");
    		context.put("list_sub",""); 
    		
   		   	//vm = "app/ppk/frmTukarStatus.jsp";
   		   	
   		   	
   		   	/*
   		   	 untuk tambahn skrin no 2 
   		   	 
   		   	 SELECT   a.id_perintahhtaobmst, f.id_fail, f.no_fail, a.id_hta,
            a.id_perintah, a.id_jenisperintah, a.flag_harta, a.flag_batal,
            a.nilai_bersih, a.cukai_harta, a.nama_pembayar_cukai,
            a.tarikh_jualan, a.amaun, a.jenis_lelong, a.harga_rizab,
            c.id_perbicaraan, d.id_keputusanpermohonan, e.id_permohonan
       FROM tblppkperintahhtaobmst a,
            tblppkperintah b,
            tblppkperbicaraan c,
            tblppkkeputusanpermohonan d,
            tblppkpermohonan e,
            tblpfdfail f,tblppkpermohonansimati ps,tblppksimati s
      WHERE a.id_perintah = b.id_perintah
        AND b.id_perbicaraan = c.id_perbicaraan
        AND c.id_keputusanpermohonan = d.id_keputusanpermohonan
        AND d.id_permohonan = e.id_permohonan
        AND e.id_fail = f.id_fail and ps.id_permohonan = e.id_permohonan
        and ps.id_simati = s.id_simati
   ORDER BY f.no_fail, a.id_perintahhtaobmst
   
   --boleh update dekat tblppkhta flag_pa ngn flag_pt ngn flag_selesai
   
   SELECT  f.no_fail,s.id_simati,ps.id_permohonansimati,hta.no_hakmilik,hta.flag_pa,hta.flag_pt,flag_selesai
FROM tblppkperintahhtaobmst a,
tblppkperintah b,
tblppkperbicaraan c,
tblppkkeputusanpermohonan d,
tblppkpermohonan e,tblppkhta hta,
tblpfdfail f,tblppkpermohonansimati ps,tblppksimati s
WHERE a.id_perintah = b.id_perintah
AND b.id_perbicaraan = c.id_perbicaraan
AND c.id_keputusanpermohonan = d.id_keputusanpermohonan
AND d.id_permohonan = e.id_permohonan
AND e.id_fail = f.id_fail and ps.id_permohonan = e.id_permohonan
and ps.id_simati = s.id_simati and hta.id_hta = a.id_hta
ORDER BY f.no_fail, a.id_perintahhtaobmst
   		   	*/
    	}//close else
    	
    	 return vm;
    	
	}
	
	
	private void tukarstatus_sub(HttpSession session) throws Exception{
		   
		Hashtable h = new Hashtable();
		
		String id_perintah = getParam("id_perintah");
		String id_permohonan = getParam("id_permohonan");
		String id_perbicaraan = getParam("id_perbicaraan");
		String id_keputusanpermohonan = getParam("id_keputusanpermohonan");
		String id_fail = getParam("id_fail");
		String id_suburusanstatusfail = getParam("id_suburusanstatusfail");
		
		String level = getParam("level");
		
		int seksyen = 0;
		seksyen = getParamAsInteger("seksyen");
				
		h.put("id_perintah", id_perintah);
		h.put("id_permohonan", id_permohonan);
		h.put("id_keputusanpermohonan", id_keputusanpermohonan);
		h.put("id_perbicaraan", id_perbicaraan);
		h.put("id_fail", id_fail);
		h.put("id_suburusanstatusfail", id_suburusanstatusfail);
		
		h.put("id_masuk", session.getAttribute("_ekptg_user_id"));
		
		
	  // LEVEL   
	  /*  1 = PERMOHONAN DITERUSKAN 
	      2 = NOTIS PERBICARAAN 
	      3 = SELESAI PERBICARAAN 
	      4 = PERMOHONAN SELESAI 	*/
		
		String lvl = "";
		String status = "";
		String suburusanstatus = "";
		
		
		if(level.equals("1")){
			lvl = "1";
			status = "53";
			if(seksyen==8){
				suburusanstatus = "406";
			}else{
				suburusanstatus = "435";
			}
			
		}else if(level.equals("2")){
			lvl = "2";
			status = "18";
			if(seksyen==8){
				suburusanstatus = "354";
			}else{
				suburusanstatus = "341";
			}
			
		}else if(level.equals("3")){
			lvl = "3";
			status = "41";
			if(seksyen==8){
				suburusanstatus = "373";
			}else{
				suburusanstatus = "410";
			}
			
		}else if(level.equals("4")){
			lvl = "4";
			status = "21";
			if(seksyen==8){
				suburusanstatus = "358";
			}else{
				suburusanstatus = "355";
			}
			
		}else{
			lvl = "4";
			status = "21";
			if(seksyen==8){
				suburusanstatus = "358";
			}else{
				suburusanstatus = "355";
			}
		}

		h.put("id_suburusanstatus", suburusanstatus);
		h.put("id_statusKE", status);
		h.put("level", lvl);
		
		//model.tukarstatus(h);
		model.tukarKE_sub(h);
	    model.updateANDinsertStatusFail_sub(h);
	    
	  }//close addnotis tambah
	
	
	

	//tukarstatus
	private void tukarstatus(HttpSession session) throws Exception{
		   
		Hashtable h = new Hashtable();
		
		String id_perintah = getParam("id_perintah");
		String id_permohonan = getParam("id_permohonan");
		String id_perbicaraan = getParam("id_perbicaraan");
		String id_keputusanpermohonan = getParam("id_keputusanpermohonan");
		String id_fail = getParam("id_fail");
		String id_suburusanstatusfail = getParam("id_suburusanstatusfail");
		
		String level = getParam("level");
		
		int seksyen = 0;
		seksyen = getParamAsInteger("seksyen");
				
		h.put("id_perintah", id_perintah);
		h.put("id_permohonan", id_permohonan);
		h.put("id_keputusanpermohonan", id_keputusanpermohonan);
		h.put("id_perbicaraan", id_perbicaraan);
		h.put("id_fail", id_fail);
		h.put("id_suburusanstatusfail", id_suburusanstatusfail);
		
		h.put("id_masuk", session.getAttribute("_ekptg_user_id"));
		
		
	  // LEVEL   
	  /*  1 = PERMOHONAN DITERUSKAN 
	      2 = NOTIS PERBICARAAN 
	      3 = SELESAI PERBICARAAN 
	      4 = PERMOHONAN SELESAI 	*/
		
		String lvl = "";
		String status = "";
		String suburusanstatus = "";
		
		
		if(level.equals("1")){
			lvl = "1";
			status = "53";
			if(seksyen==8){
				suburusanstatus = "406";
			}else{
				suburusanstatus = "435";
			}
			
		}else if(level.equals("2")){
			lvl = "2";
			status = "18";
			if(seksyen==8){
				suburusanstatus = "354";
			}else{
				suburusanstatus = "341";
			}
			
		}else if(level.equals("3")){
			lvl = "3";
			status = "41";
			if(seksyen==8){
				suburusanstatus = "373";
			}else{
				suburusanstatus = "410";
			}
			
		}else if(level.equals("4")){
			lvl = "4";
			status = "21";
			if(seksyen==8){
				suburusanstatus = "358";
			}else{
				suburusanstatus = "355";
			}
			
		}else{
			lvl = "4";
			status = "21";
			if(seksyen==8){
				suburusanstatus = "358";
			}else{
				suburusanstatus = "355";
			}
		}

		h.put("id_suburusanstatus", suburusanstatus);
		h.put("id_statusKE", status);
		h.put("level", lvl);
		
		//model.tukarstatus(h);
		model.tukarKE(h);
	    model.updateANDinsertStatusFail(h);
	    
	  }//close addnotis tambah
	
	
	private void total_harta(HttpSession session) throws Exception{
	int total = 0;	
	String txtNoFailSub = getParam("txtNoFailSub"); 
	Hashtable count_harta_hta = null;
	Hashtable count_harta_ha = null;
	count_harta_hta = model.getCountHta(getParam("id_fail_carian"));
	count_harta_ha = model.getCountHa(getParam("id_fail_carian"));
	total = Integer.parseInt(count_harta_hta.get("JUMLAH_HTA")+"") + Integer.parseInt(count_harta_ha.get("JUMLAH_HA")+"");
	myLogger.info("TOTAL HARTA :"+total);
	context.put("total_harta",total);	
	}
	
	private void urusan_status_selesai(HttpSession session) throws Exception {
		
	
	String sub_id_permohonan = "";
	String sub_id_fail = "";
	String sub_id_status = "";
	String sub_id_suburusanstatusfail = "";
	String sub_keterangan_status = "";
	String sub_seksyen = ""; 
	String txtNoFailSub = getParam("txtNoFailSub");
	model.setList(getParam("id_fail_carian"),(String)session.getAttribute("_ekptg_user_id"));
	Vector list = null;
	list = model.getList(); 
	context.put("listSemak_sub",list);
	Vector keputusanPermohonan = null;
	Vector notisPerbicaraan = null;
	Vector perintah = null;

	if(list.size()!=0){
		Hashtable x = (Hashtable) list.get(0);
		sub_id_permohonan = x.get("id_permohonan").toString();
		sub_id_fail = x.get("id_fail").toString();
		sub_id_status = x.get("id_status").toString();	
		sub_id_suburusanstatusfail = x.get("id_suburusanstatusfail").toString();	
		sub_keterangan_status = x.get("keterangan").toString();
		sub_seksyen = x.get("seksyen").toString();
		
		
		//check id keputusan permohonan ade / xde
		String id_keputusanpermohonan = "";   		
		if(sub_id_permohonan!=""){   			
		
			keputusanPermohonan = model.getKeputusanPermohonan(sub_id_permohonan);  
			
			if(keputusanPermohonan.size()!=0){
    			Hashtable kp = (Hashtable) keputusanPermohonan.get(0);
    			id_keputusanpermohonan = kp.get("id_keputusanpermohonan").toString();
    			context.put("sub_id_keputusanpermohonan",id_keputusanpermohonan);
    		}
			else
			{
				context.put("sub_id_keputusanpermohonan","");
			}
		}
		else
		{
			context.put("sub_id_keputusanpermohonan","");
		}
		
		//check id perbicaraan ade / xde
		String id_perbicaraan = "";    		
		if(id_keputusanpermohonan!=""){
		
			notisPerbicaraan = model.getNotisPerbicaraan(id_keputusanpermohonan); 
			
			if(notisPerbicaraan.size()!=0){
    			Hashtable np = (Hashtable) notisPerbicaraan.get(0);
    			id_perbicaraan = np.get("id_perbicaraan").toString();
    			context.put("sub_id_perbicaraan",id_perbicaraan);
    		} 
			else
			{
				context.put("sub_id_perbicaraan","");
			}
		}
		else
		{
			context.put("sub_id_perbicaraan","");
		}
		
		//check id perintah ade / xde
		String id_perintah = "";    		
		if(id_perbicaraan!=""){
		
			perintah = model.getPerintah(id_perbicaraan); 
			
			if(perintah.size()!=0){
    			Hashtable p = (Hashtable) perintah.get(0);
    			id_perintah = p.get("id_perintah").toString();
    			context.put("sub_id_perintah",id_perintah);
    		}   
			else
			{
				context.put("sub_id_perintah","");				
			}
		}
		else
		{
			context.put("sub_id_perintah","");				
		}
		
		context.put("sub_id_permohonan",sub_id_permohonan);
		context.put("sub_id_fail",sub_id_fail);
		context.put("sub_id_status",sub_id_status);
		context.put("sub_id_suburusanstatusfail",sub_id_suburusanstatusfail);
		context.put("sub_keterangan_status",sub_keterangan_status);
		context.put("sub_seksyen",sub_seksyen);
		
		
		
	}
	else
	{
		context.put("sub_id_permohonan","");
		context.put("sub_id_fail","");
		context.put("sub_id_status","");
		context.put("sub_id_suburusanstatusfail","");
		context.put("sub_keterangan_status","");
		context.put("sub_seksyen",""); 
		context.put("listSemak_sub","");
		context.put("sub_id_perintah","");	
		context.put("sub_id_perbicaraan","");	
		context.put("sub_id_keputusanpermohonan","");	
	}
	
	}
	
	private void headerppk_baru(HttpSession session,String id_permohonan,String flag_permohonan,String id_fail,String flag_fail) throws Exception {
		//hashtable maklumat header
		this.context.put("headerppk",mainheader.getHeaderData(session,id_permohonan,flag_permohonan,id_fail,flag_fail));
		Vector list_sub = null;
		list_sub = mainheader.carianFail(id_permohonan,flag_permohonan,id_fail,flag_fail);		
		this.context.put("list_sub_header",list_sub);
		this.context.put("flag_jenis_vm","ajax");
	}
	private void headerppk_baru_default()
	{
		Hashtable headerppk = null;
		this.context.put("headerppk","");
		this.context.put("list_sub_header","");
		this.context.put("flag_jenis_vm","ajax");
	}
}//close class
