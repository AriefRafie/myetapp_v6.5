package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmNotaBicaraData;
/*
 * @author
 * @Muhamad Syazreen bin Yahaya
 */

public class FrmNotaBicaraBc extends AjaxBasedModule{
	
	//for println output
	static Logger myLogger = Logger.getLogger(FrmNotaBicara.class);
	
	FrmNotaBicaraData model = new FrmNotaBicaraData();
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public String doTemplate2() throws Exception{
		
			myLogger.info("::FrmNotaBicara::");
			HttpSession session = request.getSession();
			String doPost = (String) session.getAttribute("doPost");
			
			Vector list = new Vector();
			Vector dataNotaBicara = new Vector();
			
			dataNotaBicara.clear();
			list.clear();
			
			String vm = "";
			String screen = "app/ppk/frmNotaBicara.jsp";
			
			String usid="";  
	   		usid=(String)session.getAttribute("_ekptg_user_id");
			
	   		//reset
			context.put("nofail","");
			context.put("showInput", "");
			context.put("list", "");
    		context.put("list_size", 0);
    		context.put("dataNotaBicara","");
    		context.put("totalWordNotaBicara","0");
    		context.put("bayaranNB", "0.00");
    		
			String submit = getParam("command");
			String action = getParam("doaction");
			String action2 = getParam("doaction2");
	    	myLogger.info("[submit] :: " +submit);
	    	myLogger.info("[action] :: " +action);
	    	myLogger.info("[action 2] :: " +action2);
	    	
	    	//*KOSONGKAN*//
	    	if ("kosongkan".equals(submit)){
	    		context.put("nofail","");
	    	}//close kosongkan
	    	
	    	//*CARIAN BY NOFAIL*//
	    	if ("cari".equals(submit)){
	    		
	    		String nofail = getParam("txtNoFail");
	    		context.put("nofail", nofail.trim());
	    		
	    		model.setList(nofail,usid);
	    		list = model.getList();
	    		String id_perbicaraan = "";
	    		if(list.size()!=0){
	    			Hashtable x = (Hashtable) list.get(0);
	    			id_perbicaraan = x.get("id_perbicaraan").toString();
	    		}//close if
	    		
	    		context.put("list", list);
	    		context.put("list_size", id_perbicaraan);
	    		
	    		if(!id_perbicaraan.equals("0") && !id_perbicaraan.equals("")){
	    			
	    			model.setDataNotaBicara(id_perbicaraan);
		    		dataNotaBicara = model.getDataNotaBicara();
	    			
		    		String id_notabicara = "";
		    		
		    		if(dataNotaBicara.size()!=0){
		    			
		    			String notabicara = "";
		    			Hashtable n = (Hashtable) dataNotaBicara.get(0);
		    			id_notabicara = n.get("id_notabicara").toString();
		    			notabicara = n.get("nota_bicara").toString();
		    			long totalWordNotaBicara = 0;
		    			String kos = "0.00";
		    			double total = 0;
		    			if(notabicara!=""){
		    				totalWordNotaBicara = Utils.wordcount(notabicara);
		    				
		    				if(totalWordNotaBicara <= 100){
            					total = 30.00;
            				}else if(totalWordNotaBicara > 100 && totalWordNotaBicara <= 200){
            					total = 60.00;
            				}else if(totalWordNotaBicara > 200 && totalWordNotaBicara <= 300){
            					total = 90.00;
            				}else if(totalWordNotaBicara > 300 && totalWordNotaBicara <= 400){
            					total = 120.00;
            				}else if(totalWordNotaBicara > 400 && totalWordNotaBicara <= 500){
            					total = 150.00;
            				}else if(totalWordNotaBicara > 500 && totalWordNotaBicara <= 600){
            					total = 180.00;
            				}else if(totalWordNotaBicara > 600 && totalWordNotaBicara <= 700){
            					total = 210.00;
            				}else if(totalWordNotaBicara > 700 && totalWordNotaBicara <= 800){
            					total = 240.00;
            				}else if(totalWordNotaBicara > 800 && totalWordNotaBicara <= 900){
            					total = 270.00;
            				}else if(totalWordNotaBicara > 900 && totalWordNotaBicara <= 1000){
            					total = 300.00;
            				}
            				kos = Utils.format2Decimal(total);
		    			}else{
		    				totalWordNotaBicara = 0;
		    				kos = Utils.format2Decimal(total);
		    			}
		    			
		    			context.put("bayaranNB", kos);
		    			context.put("totalWordNotaBicara", totalWordNotaBicara);
		    			context.put("id_notabicara", id_notabicara);
		    			context.put("mode", "view");
		    			
		    			//*KEMASKINI NOTA BICARA*//
		    			if ("kemaskiniNotaBicara".equals(action)){
		    				context.put("mode", "edit");
		    				
		    				//*UPDATE NOTA BICARA*//
		    				if ("updateNotaBicara".equals(action2)){
			    				context.put("mode", "view");
			    				if (doPost.equals("true")) {
					    			updateNotaBicara(session);
					    		}
			    				model.setDataNotaBicara(id_perbicaraan);
					    		dataNotaBicara = model.getDataNotaBicara();
					    		Hashtable m = (Hashtable) dataNotaBicara.get(0);
					    		notabicara = m.get("nota_bicara").toString();
					    		double totalx = 0;
				    			if(notabicara!=""){
				    				totalWordNotaBicara = Utils.wordcount(notabicara);
				    				
				    				if(totalWordNotaBicara <= 100){
				    					totalx = 30.00;
		            				}else if(totalWordNotaBicara > 100 && totalWordNotaBicara <= 200){
		            					totalx = 60.00;
		            				}else if(totalWordNotaBicara > 200 && totalWordNotaBicara <= 300){
		            					totalx = 90.00;
		            				}else if(totalWordNotaBicara > 300 && totalWordNotaBicara <= 400){
		            					totalx = 120.00;
		            				}else if(totalWordNotaBicara > 400 && totalWordNotaBicara <= 500){
		            					totalx = 150.00;
		            				}else if(totalWordNotaBicara > 500 && totalWordNotaBicara <= 600){
		            					totalx = 180.00;
		            				}else if(totalWordNotaBicara > 600 && totalWordNotaBicara <= 700){
		            					totalx = 210.00;
		            				}else if(totalWordNotaBicara > 700 && totalWordNotaBicara <= 800){
		            					totalx = 240.00;
		            				}else if(totalWordNotaBicara > 800 && totalWordNotaBicara <= 900){
		            					totalx = 270.00;
		            				}else if(totalWordNotaBicara > 900 && totalWordNotaBicara <= 1000){
		            					totalx = 300.00;
		            				}
		            				kos = Utils.format2Decimal(totalx);
				    			}else{
				    				totalWordNotaBicara = 0;
				    				kos = Utils.format2Decimal(totalx);
				    			}
				    			context.put("bayaranNB", kos);
				    			context.put("totalWordNotaBicara", totalWordNotaBicara);
			    				context.put("dataNotaBicara",dataNotaBicara);
			    			}//close update
		    				
		    			}//close kemaskini
		    		}else{
		    			context.put("mode", "new");
		    		}//close else size datanotabicara
		    		
		    		//*SIMPAN NOTA BICARA*//
		    		if ("simpanNotaBicara".equals(action)){
			    		context.put("mode", "view");
		    			if (doPost.equals("true")) {
			    			simpanNotaBicara(session,id_perbicaraan);
			    		}
		    			String notabicara = "";
		    			long totalWordNotaBicara = 0;
		    			String kos = "0.00";
			    		model.setDataNotaBicara(id_perbicaraan);
			    		dataNotaBicara = model.getDataNotaBicara();
			    		if(dataNotaBicara.size()!=0){
			    		Hashtable m = (Hashtable) dataNotaBicara.get(0);
			    		notabicara = m.get("nota_bicara").toString();
			    		}
			    		double total = 0;
		    			if(notabicara!=""){
		    				totalWordNotaBicara = Utils.wordcount(notabicara);
		    				
		    				if(totalWordNotaBicara <= 100){
            					total = 30.00;
            				}else if(totalWordNotaBicara > 100 && totalWordNotaBicara <= 200){
            					total = 60.00;
            				}else if(totalWordNotaBicara > 200 && totalWordNotaBicara <= 300){
            					total = 90.00;
            				}else if(totalWordNotaBicara > 300 && totalWordNotaBicara <= 400){
            					total = 120.00;
            				}else if(totalWordNotaBicara > 400 && totalWordNotaBicara <= 500){
            					total = 150.00;
            				}else if(totalWordNotaBicara > 500 && totalWordNotaBicara <= 600){
            					total = 180.00;
            				}else if(totalWordNotaBicara > 600 && totalWordNotaBicara <= 700){
            					total = 210.00;
            				}else if(totalWordNotaBicara > 700 && totalWordNotaBicara <= 800){
            					total = 240.00;
            				}else if(totalWordNotaBicara > 800 && totalWordNotaBicara <= 900){
            					total = 270.00;
            				}else if(totalWordNotaBicara > 900 && totalWordNotaBicara <= 1000){
            					total = 300.00;
            				}
            				kos = Utils.format2Decimal(total);
		    			}else{
		    				totalWordNotaBicara = 0;
		    				kos = Utils.format2Decimal(total);
		    			}
		    			context.put("bayaranNB", kos);
		    			context.put("totalWordNotaBicara", totalWordNotaBicara);
	    				context.put("dataNotaBicara",dataNotaBicara);
	    			}//close simpanNotaBicara
		    		
		    		context.put("dataNotaBicara",dataNotaBicara);
		    		context.put("showInput", "yes");
		    		
	    		}else{
	    			if(nofail.isEmpty()){
	    				context.put("showInput", "");
	    			}else{
	    				context.put("showInput", "no");
	    			}//close else
	    		}//close else
	    	
	    	}//close if cari
			
	    	vm = screen;
            
            return vm;
    
	}//close doTemplate2
	
	
	
	
	
	//METHOD
	
	private void simpanNotaBicara(HttpSession session,String id_perbicaraan) throws Exception{
		   
		Hashtable h = new Hashtable();
	
		String notaBicara = getParam("txtNotaBicara");
		
		h.put("notaBicara", notaBicara);
		h.put("id_masuk", session.getAttribute("_ekptg_user_id"));
		h.put("id_perbicaraan", id_perbicaraan);
		
		model.simpanNotaBicara(h);
		
	}//close simpanNotaBicara
	
	private void updateNotaBicara(HttpSession session) throws Exception{
		   
		Hashtable h = new Hashtable();
	
		String notaBicara = getParam("txtNotaBicara");
		String id_notabicara = getParam("id_notabicara");
		
		h.put("notaBicara", notaBicara);
		h.put("id_kemaskini", session.getAttribute("_ekptg_user_id"));
		h.put("id_notabicara", id_notabicara);
		
		model.updateNotaBicara(h);
		
	}//close simpanNotaBicara
	
	
}//close class
