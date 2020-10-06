package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.BantahanPampasan;
import ekptg.model.ppt.BantahanPampasanOperations;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.PPTHeader;

public class FrmBantahanPampasan extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmBantahanPampasan.class);
	
	// MODEL BANTAHAN
	BantahanPampasan model = new BantahanPampasan();
	BantahanPampasanOperations modelOperations = new BantahanPampasanOperations();
	PPTHeader header = new PPTHeader();
	
	// MODEL SEKSYEN 8
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	
	public String doTemplate2() throws Exception
    {
    	HttpSession session = this.request.getSession();   
    	
    	//get user login detail 
		String usid = (String)session.getAttribute("_ekptg_user_id"); 
    	userData(usid);
    	String userIdNeg = userData(usid); 
    	
    	String doPost = (String)session.getAttribute("doPost");
    	String vm = "";
    	
    	Vector list = null;
    	Vector listA = null; //getSenaraiPB
    	Vector listB = null; //getMaklumatPB
    	Vector listC = null; //getMaklumatTerimaCek
    	Vector listD = null; //getMaklumatSerahCek
    	Vector listE = null; //getMaklumatViaEFT    
    	Vector listF = null; // getMaklumatAP    
    	
      	Vector listTkhBorangH = null; //getTarikhBorangH
      	Vector listB2 = null; //getTarikhBorangH
    	
    	Vector dataSuburusanHakmilik = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
   		
    	String action = getParam("action"); 			// ACTION UTK SETUP PAGING SHJ
    	String submit = getParam("command");
    	myLogger.info("SUBMIT :: "+submit);
    	this.context.put("Util",new lebah.util.Util());	// UNTUK FORMAT UTIL.DECIMAL (EX: 12,000.00)
    	

   		
   		String id_fail = getParam("id_fail");
   		context.put("id_fail", id_fail);
   		
   		String id_permohonan = getParam("id_permohonan");
   		context.put("id_permohonan", id_permohonan);
   		
   		String id_hakmilik = getParam("id_hakmilik");
   		context.put("id_hakmilik", id_hakmilik);
   		
   		String id_pihakberkepentingan = getParam("id_pihakberkepentingan");
   		context.put("id_pihakberkepentingan", id_pihakberkepentingan);
   		
   		String id_hakmilikpb = getParam("id_hakmilikpb");
   		context.put("id_hakmilikpb", id_hakmilikpb);
   		
   		String status_bantahan = getParam("status_bantahan");
   		context.put("status_bantahan", status_bantahan);
   		
   		String id_bantahan = getParam("id_bantahan");
   		context.put("id_bantahan", id_bantahan);
   		
   		String Date5 = "";
        context.put("Date5","01/12/2017");
        myLogger.info(" Date5 :"+Date5);
        
    	String Date8 = "";
        context.put("Date8","11/30/2017");
        myLogger.info(" Date8 :"+Date8);
   		
   		myLogger.info("id_hakmilik :: "+id_hakmilik);
   		myLogger.info("id_pihakberkepentingan :: "+id_pihakberkepentingan);
   		myLogger.info("id_hakmilikpb :: "+id_hakmilikpb);
   		myLogger.info("status_bantahan :: "+status_bantahan);  
   		myLogger.info("id_bantahan :: "+id_bantahan); 
   		  		
//		list = model.getHeader(id_fail,id_permohonan); 
//		context.put("Header", list);
		
		// HEADER
   		if(id_permohonan!=null)
   		{
   		Vector list_sub = null;
		list_sub = header.listPerjalananFail(id_permohonan);
		this.context.put("list_sub_header", list_sub);
   		}
   		
   		
    	header.setDataHeader(id_permohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);			
		}
   		
		// TAB
		String selectedtab = getParam("selectedtab");
		if ("".equals(selectedtab)){
			selectedtab = "0";
		}
		this.context.put("selectedtab", selectedtab);

		// get size suburusanhakmilik
		String id_suburusanstatushakmilik = "";
		modelUPT.setDataSuburusanHakmilik(id_hakmilik);
		dataSuburusanHakmilik = modelUPT.getDataSuburusanHakmilik();
		if(dataSuburusanHakmilik.size()!=0){
			Hashtable dsh = (Hashtable)dataSuburusanHakmilik.get(0);
			id_suburusanstatushakmilik = (String)dsh.get("id_suburusanstatushakmilik");
		} 		
		
		//get current idsuburusanstatusfail
		String id_suburusanstatusfailppt = "";
		modelUPT.setGetIdSuburusanstatusfail(id_permohonan);
		getIdSuburusanstatusfail = modelUPT.getGetIdSuburusanstatusfail();
		if(getIdSuburusanstatusfail.size()!=0){
			Hashtable idsb = (Hashtable)getIdSuburusanstatusfail.get(0);
			id_suburusanstatusfailppt = (String)idsb.get("id_suburusanstatusfailppt");
		}    	
		
		
    	if("UrusanPampasan".equals(submit)){
    		
    		// SENARAI PB
    		listB = model.getSenaraiPB(id_permohonan);
    		context.put("getSenaraiPB", listB);
    		context.put("list_size", listB.size());  
    		setupPagePB(session,action,listB);
    				
    		// SENARAI AGENSI PEMOHON
    		listF = model.getSenaraiAP(id_permohonan);
    		context.put("getSenaraiAP", listF);
    		context.put("listF_size", listF.size());  
    		if(listF.size()!=0){
    			Hashtable f = (Hashtable) dataHeader.get(0);
    			String status_bantahan_ap = (String)f.get("status_bantahan_ap");
    			context.put("status_bantahan_ap", status_bantahan_ap);
    		}
    		setupPageAP(session,action,listF);
    		
    		context.put("clearForm", "yes");
    		    		
			vm = "app/ppt/frmBantahanPampasan.jsp"; 	

    	}else if("cariNamaPB".equals(submit)){
    		
    		String carianNamaPB = getParam("carianNamaPB");
    		context.put("carianNamaPB", carianNamaPB.trim());
    		
    		String carianNoLot = getParam("carianNoLot");
    		context.put("carianNoLot", carianNoLot.trim());
    		
    		listB = model.setCarianPB(id_permohonan,carianNamaPB,carianNoLot);    		
     		context.put("getSenaraiPB", listB);
     		context.put("list_size", listB.size());
     		setupPagePB(session,action,listB);
 
     		context.put("clearForm", "");
    		
     		vm = "app/ppt/frmBantahanPampasan.jsp";			
			
    	}else if("cariNamaAP".equals(submit)){
    		
    		String carianNamaAP = getParam("carianNamaAP");
    		context.put("carianNamaAP", carianNamaAP.trim());
    		
    		String carianNoHakmilik = getParam("carianNoHakmilik");
    		context.put("carianNoHakmilik", carianNoHakmilik.trim());
    		
    		listF = model.setCarianAP(id_permohonan,carianNamaAP,carianNoHakmilik);    		
     		context.put("getSenaraiAP", listF);
     		context.put("list_sizeF", listF.size());
     		setupPageAP(session,action,listF);
 
     		context.put("clearForm", "");
    		
     		vm = "app/ppt/frmBantahanPampasan.jsp";		     		
			
    	}else if("checkingCaraBayar".equals(submit)){
    		
    		listB2 = model.getMaklumatTkhH(id_hakmilikpb);
    		context.put("getMaklumatTkhH", listB2);
    		
    		if(listB2.size()!=0){
    			Hashtable a = (Hashtable) listB2.get(0);
    			String tarikhBorangH = (String)a.get("tarikh_borangh");
    			myLogger.info("tarikhBorangH :: "+tarikhBorangH);
    		}
    		
    		listB = model.getMaklumatPB(id_hakmilikpb);
    		context.put("getMaklumatPB", listB);
    		
    		if(listB.size()!=0){
    			Hashtable a = (Hashtable) listB.get(0);
    			String flag_bayar_bantahan = (String)a.get("flag_bayar_bantahan");
    			myLogger.info("FLAG BAYARAN PERINTAH :: "+flag_bayar_bantahan);
    			if(flag_bayar_bantahan.equals("1")){
    				    				
    				
    				Hashtable _methodCaraBayar = model.getMethodCaraBayar(id_hakmilikpb,id_bantahan);
    				
    				if(_methodCaraBayar.size()!=0){
    					
    					String cara_bayar = (String)_methodCaraBayar.get("cara_bayar");
    					myLogger.info("CARA BAYAR :: "+cara_bayar);
    					
    					if(cara_bayar.equals("1")){		// CARA BAYAR BY CEK
    						 
    						selectedtab = "0";
    						context.put("selectedtab", selectedtab);
    						
    	    				Hashtable _maxIdBayaran = model.getIdBayaran(id_hakmilikpb,id_bantahan);
    	    				String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
    						
    	    				listC = model.getMaklumatTerimaCek(id_bayaran);
    	    				context.put("getMaklumatTerimaCek",listC);
    	    				
    	    				context.put("mode", "disabled");
    	    				context.put("clearForm","");
    	    				context.put("button","view");
    	    				context.put("flag", "semak");
    						
    						vm = "app/ppt/frmBantahanPampasanTerimaCek.jsp";
    						
    					 }else if(cara_bayar.equals("2")) {
    							
    						myLogger.info("2 = EFT");	// CARA BAYAR BY EFT
    						
    						selectedtab = "2";
    						context.put("selectedtab", selectedtab);
    						
    	    				Hashtable _maxIdBayaran = model.getIdBayaran(id_hakmilikpb,id_bantahan);
    	    				String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
    	    				
    	    				listE = model.getMaklumatViaEFT(id_bayaran);
    						context.put("getMaklumatViaEFT",listE);
    						
    	    				context.put("mode", "disabled");
    	    				context.put("clearForm","");
    	    				context.put("button","view");
    	    				context.put("flag", "semak");
    						
    						vm = "app/ppt/frmBantahanPampasanEFT.jsp";
    												
    					}else{

    	    				context.put("mode", "");
    	    				context.put("clearForm","yes");
    	    				context.put("button","add");
    	    				context.put("flag", "");
    	    				
    	    				vm = "app/ppt/frmBantahanPampasanTerimaCek.jsp";
    						
    					}	// CLOSE CARA BAYAR
    					
    				}else{
    					
	    				context.put("mode", "");
	    				context.put("clearForm","yes");
	    				context.put("button","add");
	    				context.put("flag", "");
	    				
	    				vm = "app/ppt/frmBantahanPampasanTerimaCek.jsp";
    					
    				}	// CLOSE _methodCaraBayar
		
    			}else{
    				context.put("mode", "");
    				context.put("clearForm","yes");
    				context.put("button","add");
    				context.put("flag", "");
    				
    				vm = "app/ppt/frmBantahanPampasanTerimaCek.jsp";
    				
    			}	// CLOSE flag_bayar_bantahan
    			
    			
    		}else{
    			
    			context.put("status", true);
    			vm = "app/ppt/frmBantahanPampasanTerimaCek.jsp";
    		}
    		
    		
			
		}else if("TerimaCek".equals(submit)){
			
			selectedtab = "0";
			context.put("selectedtab", selectedtab);
			
    		listB = model.getMaklumatPB(id_hakmilikpb);
    		context.put("getMaklumatPB", listB);
    		
    		if(listB.size()!=0){
    			Hashtable a = (Hashtable) listB.get(0);
    			String flag_bayar_bantahan = (String)a.get("flag_bayar_bantahan");
    			myLogger.info("FLAG BAYARAN PERINTAH :: "+flag_bayar_bantahan);
    			
    			if(flag_bayar_bantahan.equals("1")){
    				
    				Hashtable _methodCaraBayar = model.getMethodCaraBayar(id_hakmilikpb,id_bantahan);
    				
    				if(_methodCaraBayar.size()!=0){
    					
    					String cara_bayar = (String)_methodCaraBayar.get("cara_bayar");
    					myLogger.info("CARA BAYAR :: "+cara_bayar);
    					
    					if(cara_bayar.equals("1")){
    						myLogger.info("1 = CEK");
    						
    	    				Hashtable _maxIdBayaran = model.getIdBayaran(id_hakmilikpb,id_bantahan);
    	    				String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
    	    				
    	    				listC = model.getMaklumatTerimaCek(id_bayaran);
    	    				context.put("getMaklumatTerimaCek",listC);
    	    				
    	    				context.put("mode", "disabled");
    	    				context.put("clearForm","");
    	    				context.put("button","view");
    	    				context.put("flag", "semak");
    						
    					}else{
    						
    	    				context.put("mode", "");
    	    				context.put("clearForm","");
    	    				context.put("button","add");
    	    				context.put("flag", "");
    						
    					}	// CLOSE CARA_BAYAR
    					
    				}else{
    					
	    				context.put("mode", "");
	    				context.put("clearForm","");
	    				context.put("button","add");
	    				context.put("flag", "");   					
    					
    				} // CLOSE _methodCaraBayar
    				
    			}else{    				
    				
    				context.put("mode", "");
    				context.put("clearForm","yes");
    				context.put("button","add");
    				context.put("flag", "");
    			} // CLOSE flag_bayar_bantahan
    			
    		}else{
    			context.put("status", true);
    		}
			
			vm = "app/ppt/frmBantahanPampasanTerimaCek.jsp";
			
			
		}else if("BayarTambah".equals(submit)){ //penambahan yati
			
			selectedtab = "0";
			context.put("selectedtab", selectedtab);
			
    		listB = model.getMaklumatPB(id_hakmilikpb);
    		context.put("getMaklumatPB", listB);
    		
    		if(listB.size()!=0){
    			Hashtable a = (Hashtable) listB.get(0);
    			String flag_bayar_bantahan = (String)a.get("flag_bayar_bantahan");
    			myLogger.info("FLAG BAYARAN PERINTAH :: "+flag_bayar_bantahan);
    			
    			if(flag_bayar_bantahan.equals("1")){
    				
    				Hashtable _methodCaraBayar = model.getMethodCaraBayar(id_hakmilikpb,id_bantahan);
    				
    				if(_methodCaraBayar.size()!=0){
    					
    					String cara_bayar = (String)_methodCaraBayar.get("cara_bayar");
    					myLogger.info("CARA BAYAR :: "+cara_bayar);
    					
    					if(cara_bayar.equals("1")){
    						myLogger.info("1 = CEK");
    						
    	    				Hashtable _maxIdBayaran = model.getIdBayaran(id_hakmilikpb,id_bantahan);
    	    				String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
    	    				
    	    				listC = model.getMaklumatTerimaCek(id_bayaran);
    	    				context.put("getMaklumatTerimaCek",listC);
    	    				
    	    				context.put("mode", "disabled");
    	    				context.put("clearForm","");
    	    				context.put("button","view");
    	    				context.put("flag", "semak");
    						
    					}else{
    						
    	    				context.put("mode", "");
    	    				context.put("clearForm","");
    	    				context.put("button","add");
    	    				context.put("flag", "");
    						
    					}	// CLOSE CARA_BAYAR
    					
    				}else{
    					
	    				context.put("mode", "");
	    				context.put("clearForm","");
	    				context.put("button","add");
	    				context.put("flag", "");   					
    					
    				} // CLOSE _methodCaraBayar
    				
    			}else{    				
    				
    				context.put("mode", "");
    				context.put("clearForm","yes");
    				context.put("button","add");
    				context.put("flag", "");
    			} // CLOSE flag_bayar_bantahan
    			
    		}else{
    			context.put("status", true);
    		}
			
			vm = "app/ppt/frmBantahanPampasanTerimaCek.jsp";
			
			
		}else if("add_terimaCek".equals(submit)){
			
			selectedtab = "0";
			context.put("selectedtab",selectedtab);
			
			if (doPost.equals("true")){
				
				// INSERT TBLPPTBAYARAN & UPDATE TBLPPTHAKMILIKPB & UPDATE TBLPPTBANTAHAN
				add_terimaCek(usid);
				
				// UPDATE TBLPPTPERMOHONAN
				updateStatus_urusanBayaran(session);			

				// UPDATE TBLPPTSUBURUSANHAKMILIK
				updateSuburusanHakmilik_urusanBayaran(session,id_permohonan,id_fail,id_hakmilik,id_suburusanstatushakmilik);

				// UPDATE TBLPPTSUBURUSANSTATUSFAILPPT
				updateSuburusanStatusFailPPT_urusanBayaran(session,id_permohonan,id_fail,id_suburusanstatusfailppt);				
				
				// UPDATE TBLPPTSUBURUSANSTATUSBANTAHAN
				urusanBayaran_tblrujsuburusanstatusbantahan(usid,id_bantahan,id_permohonan,id_hakmilik,id_fail);				
			}
			
			Hashtable _maxIdBayaran = model.getIdBayaran(id_hakmilikpb,id_bantahan);
			String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
			
			listC = model.getMaklumatTerimaCek(id_bayaran);
			context.put("getMaklumatTerimaCek",listC);
			
			context.put("mode", "disabled");
			context.put("clearForm","");
			context.put("flag", "semak");
			context.put("button", "view");
			
			vm = "app/ppt/frmBantahanPampasanTerimaCek.jsp";
			
		}else if("add_terimaCekAgensi".equals(submit)){
			
			selectedtab = "0";
			context.put("selectedtab",selectedtab);
			
			if (doPost.equals("true")){
				
				// INSERT TBLPPTBAYARAN & UPDATE TBLPPTHAKMILIK & UPDATE TBLPPTBANTAHAN
				add_terimaCekAgensi(usid);
				
				// UPDATE TBLPPTPERMOHONAN		
				updateStatus_urusanBayaranAgensi(session);			

				// UPDATE TBLPPTSUBURUSANHAKMILIK
				updateSuburusanHakmilik_urusanBayaranAgensi(session,id_permohonan,id_fail,id_hakmilik,id_suburusanstatushakmilik);

				// UPDATE TBLPPTSUBURUSANSTATUSFAILPPT
				updateSuburusanStatusFailPPT_urusanBayaranAgensi(session,id_permohonan,id_fail,id_suburusanstatusfailppt);
				
				// UPDATE TBLPPTSUBURUSANSTATUSBANTAHAN
				urusanBayaranAgensi_tblrujsuburusanstatusbantahan(usid,id_bantahan,id_permohonan,id_hakmilik,id_fail);				
			}
			
			Hashtable _maxIdBayaran = model.getIdBayaranAgensi(id_hakmilik,id_bantahan);
			String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
			
			listC = model.getMaklumatTerimaCek(id_bayaran);
			context.put("getMaklumatTerimaCek",listC);
			
			context.put("mode", "disabled");
			context.put("clearForm","");
			context.put("flag", "semak");
			context.put("button", "view");
			
			vm = "app/ppt/frmBantahanAgensiPampasanTerimaCek.jsp";		
			
		}else if("kemaskiniTerimaCek".equals(submit)){
			
			selectedtab = "0";
			context.put("selectedtab", selectedtab);
			
			Hashtable _maxIdBayaran = model.getIdBayaran(id_hakmilikpb,id_bantahan);
			String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
			
			listC = model.getMaklumatTerimaCek(id_bayaran);
			context.put("getMaklumatTerimaCek",listC);
			
			context.put("mode", "");
			context.put("clearForm","");
			context.put("flag", "semak");
			context.put("button", "edit");
			
			vm = "app/ppt/frmBantahanPampasanTerimaCek.jsp";
		
		}else if("kemaskiniTerimaCekAgensi".equals(submit)){
			
			selectedtab = "0";
			context.put("selectedtab", selectedtab);
			
			Hashtable _maxIdBayaran = model.getIdBayaranAgensi(id_hakmilik,id_bantahan);
			String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
			
			listC = model.getMaklumatTerimaCek(id_bayaran);
			context.put("getMaklumatTerimaCek",listC);
			
			context.put("mode", "");
			context.put("clearForm","");
			context.put("flag", "semak");
			context.put("button", "edit");
			
			vm = "app/ppt/frmBantahanAgensiPampasanTerimaCek.jsp";			
		
		}else if("update_terimaCek".equals(submit)){	
			
			selectedtab = "0";
			context.put("selectedtab", selectedtab);
			
			Hashtable _maxIdBayaran = model.getIdBayaran(id_hakmilikpb,id_bantahan);
			String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
			
			update_terimaCek(usid,id_bayaran);			
			
			listC = model.getMaklumatTerimaCek(id_bayaran);
			context.put("getMaklumatTerimaCek", listC);
			
			context.put("mode", "disabled");
			context.put("clearForm","");
			context.put("flag", "semak");
			context.put("button", "view");
			
			vm = "app/ppt/frmBantahanPampasanTerimaCek.jsp";

		}else if("update_terimaCekAgensi".equals(submit)){	
			
			selectedtab = "0";
			context.put("selectedtab", selectedtab);
			
			Hashtable _maxIdBayaran = model.getIdBayaranAgensi(id_hakmilik,id_bantahan);
			String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
			
			update_terimaCek(usid,id_bayaran);	
			
			listC = model.getMaklumatTerimaCek(id_bayaran);
			context.put("getMaklumatTerimaCek", listC);
			
			context.put("mode", "disabled");
			context.put("clearForm","");
			context.put("flag", "semak");
			context.put("button", "view");
			
			vm = "app/ppt/frmBantahanAgensiPampasanTerimaCek.jsp";			
			
			
		}else if("batalTerimaCek".equals(submit)){
			
			context.put("clearForm", "yes");			
			
			vm = "app/ppt/frmBantahanPampasanTerimaCek.jsp";
			
			
		}else if("SerahCek".equals(submit)){
			
			selectedtab = "1";
			context.put("selectedtab", selectedtab);
			
    		listB = model.getMaklumatPB(id_hakmilikpb);
    		context.put("getMaklumatPB", listB);
    		
    		Hashtable _maxIdBayaran = model.getIdBayaran(id_hakmilikpb,id_bantahan);
    		String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
    		context.put("id_bayaran", id_bayaran);
    		
    		listC = model.getMaklumatTerimaCek(id_bayaran);
    		if(listC.size()!=0){
    			Hashtable b = (Hashtable)listC.get(0);
    			String flag_serah_cek = (String)b.get("flag_serah_cek");
//    			String idBayaran = (String)b.get("id_bayaran");
    			
//    			if(flag_serah_cek.equals("1")){
    			if(!id_bayaran.equals("")){
    				
    				listD = model.getMaklumatSerahCek(id_bayaran);
    				context.put("getMaklumatSerahCek", listD);
    				
    				if (listD.size()!=0){
    					Hashtable c = (Hashtable)listD.get(0);
    					String nama_wakil = (String)c.get("nama_wakil");
    					
        				if(nama_wakil.equals("")){           	    		
            	    		context.put("flag", "");          	    	
        				}else{  
            	    		context.put("flag", "semak");          	    		
        				}
    					
        				context.put("clearForm", "");
        	    		context.put("mode","disabled");
        	    		context.put("button", "view");
    					
    				}// close listD.size
    				
    			}else{
    				
    	    		context.put("clearForm", "yes");
    	    		context.put("mode","");
    	    		context.put("flag", "");
    	    		context.put("button", "add");    				
    			}	// CLOSE idBayaran   			
    			
    		}else{   			
    			context.put("status", true);   			
    		}	// CLOSE listC.size
    			
			vm = "app/ppt/frmBantahanPampasanSerahCek.jsp";
			

		}else if("kemaskini_serahCek".equals(submit)){
			
			selectedtab = "1";
			context.put("selectedtab", selectedtab);
			
    		Hashtable _maxIdBayaran = model.getIdBayaran(id_hakmilikpb,id_bantahan);
    		String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
			
			listD = model.getMaklumatSerahCek(id_bayaran);
			context.put("getMaklumatSerahCek", listD);
			
			if (listD.size()!=0){
				Hashtable c = (Hashtable)listD.get(0);
				String nama_wakil = (String)c.get("nama_wakil");
				
				if(nama_wakil.equals("")){           	    		
    	    		context.put("flag", "");          	    	
				}else{  
    	    		context.put("flag", "semak");          	    		
				}
				
				context.put("clearForm", "");
	    		context.put("mode","");
	    		context.put("button", "edit");
				
			}// close listD.size
			
//    		context.put("clearForm", "");
//    		context.put("mode","");
//    		context.put("flag", "semak");
//    		context.put("button", "edit");    	
			
			vm = "app/ppt/frmBantahanPampasanSerahCek.jsp";
			
		}else if("kemaskini_serahCekAgensi".equals(submit)){
			
			selectedtab = "1";
			context.put("selectedtab", selectedtab);
			
    		Hashtable _maxIdBayaran = model.getIdBayaranAgensi(id_hakmilik,id_bantahan);
    		String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
			
			listD = model.getMaklumatSerahCek(id_bayaran);
			context.put("getMaklumatSerahCek", listD);
			
    		context.put("clearForm", "");
    		context.put("mode","");
    		context.put("flag", "semak");
    		context.put("button", "edit");    	
			
			vm = "app/ppt/frmBantahanAgensiPampasanSerahCek.jsp";			
			
		}else if("update_serahCek".equals(submit)){
			
			selectedtab = "1";
			context.put("selectedtab", selectedtab);
			
			Hashtable _maxIdBayaran = model.getIdBayaran(id_hakmilikpb,id_bantahan);
			String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
			
			if (doPost.equals("true")){
				update_serahCek(usid,id_bayaran,id_bantahan);			
//				updateStatusSelesai(id_permohonan,usid);
				selesaiPampasanBantahan_tblrujsuburusanstatusbantahan(usid,id_bantahan,id_permohonan,id_hakmilik,id_fail);
			}
			
			listD = model.getMaklumatSerahCek(id_bayaran);
			context.put("getMaklumatSerahCek", listD);
			
    		context.put("clearForm", "");
    		context.put("mode","disabled");
    		context.put("flag", "semak");
    		context.put("button", "view");
			
			vm = "app/ppt/frmBantahanPampasanSerahCek.jsp";		
			
			
		}else if("update_serahCekAgensi".equals(submit)){
			
			selectedtab = "1";
			context.put("selectedtab", selectedtab);
			
			Hashtable _maxIdBayaran = model.getIdBayaranAgensi(id_hakmilik,id_bantahan);
			String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
			
			if (doPost.equals("true")){
				update_serahCekAgensi(usid,id_bayaran,id_bantahan);			
//				updateStatusSelesai(id_permohonan,usid);
				selesaiPampasanBantahanAgensi_tblrujsuburusanstatusbantahan(usid,id_bantahan,id_permohonan,id_hakmilik,id_fail);
			}
			
			listD = model.getMaklumatSerahCek(id_bayaran);
			context.put("getMaklumatSerahCek", listD);
			
    		context.put("clearForm", "");
    		context.put("mode","disabled");
    		context.put("flag", "semak");
    		context.put("button", "view");
			
			vm = "app/ppt/frmBantahanAgensiPampasanSerahCek.jsp";		
						
			
		}else if("batal_noDataSerahCek".equals(submit)){
			
			selectedtab = "1";
			context.put("selectedtab", selectedtab);
			
    		context.put("clearForm", "yes");
    		context.put("mode","");
    		context.put("flag", "");
    		context.put("button", "edit");
			
			vm = "app/ppt/frmBantahanPampasanSerahCek.jsp";	
			
			
		}else if("batal_DataSerahCek".equals(submit)){
			
			selectedtab = "1";
			context.put("selectedtab", selectedtab);
			
			Hashtable _maxIdBayaran = model.getIdBayaran(id_hakmilikpb,id_bantahan);
			String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
			
			listD = model.getMaklumatSerahCek(id_bayaran);
			context.put("getMaklumatSerahCek", listD);
			
			
				if (listD.size()!=0){
					Hashtable c = (Hashtable)listD.get(0);
					String nama_wakil = (String)c.get("nama_wakil");
					
    				if(nama_wakil.equals("")){           	    		
        	    		context.put("flag", "");          	    	
    				}else{  
        	    		context.put("flag", "semak");          	    		
    				}
					
    				context.put("clearForm", "");
    	    		context.put("mode","disabled");
    	    		context.put("button", "view");
					
				}// close listD.size
			
			
//    		context.put("clearForm", "");
//    		context.put("mode","disabled");
//    		context.put("flag", "semak");
//    		context.put("button", "view");
			
			vm = "app/ppt/frmBantahanPampasanSerahCek.jsp";	
			
			
		}else if("BayaranEft".equals(submit)){
			
			selectedtab = "2";
			context.put("selectedtab", selectedtab);
			
	   		listB = model.getMaklumatPB(id_hakmilikpb);
    		context.put("getMaklumatPB", listB);
    		
    		if(listB.size()!=0){
    			
    			myLogger.info("11111 ");
    			
    			Hashtable a = (Hashtable) listB.get(0);
    			String flag_bayar_bantahan = (String)a.get("flag_bayar_bantahan");
    			
    			if(flag_bayar_bantahan.equals("1")){
    				
    				myLogger.info("2222 ");
    				
    				Hashtable _maxIdBayaran = model.getIdBayaran(id_hakmilikpb,id_bantahan);
    				String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
    				
    				listE = model.getMaklumatViaEFT(id_bayaran);
					context.put("getMaklumatViaEFT",listE);
    				
    				if(listE.size()!=0){
    					
    					myLogger.info("333 ");
    					
    					Hashtable c = (Hashtable)listE.get(0);
    					String no_eft = (String)c.get("no_eft");
    					String idBayaran = (String)c.get("id_bayaran");
    					
    					if(no_eft.equals("")){
    						
    	    				context.put("mode", "");
    	    				context.put("clearForm","yes");
    	    				context.put("button","edit");
    	    				context.put("flag", "");
    						
    					}else{
    						
    	    				context.put("mode", "disabled");
    	    				context.put("clearForm","");
    	    				context.put("button","view");
    	    				context.put("flag", "semak");
    	    				
    					}	// CLOSE no_eft
    					
    				}else{
    					
    					myLogger.info("4444 ");
//    					context.put("status", true);
    					
        	    		context.put("clearForm", "yes");
        	    		context.put("mode","");
        	    		context.put("flag", "");
        	    		context.put("button", "add");
    					
    				}	// CLOSE listE.size()
    					
    				
    				

    				
    			}else{    				
    				myLogger.info("5555 ");
    	    		context.put("clearForm", "yes");
    	    		context.put("mode","");
    	    		context.put("flag", "");
    	    		context.put("button", "add");
    	    		
    			}	// CLOSE flag_bayar_bantahan
    			
    		}else{
    			
//    			context.put("status", true);
    			
	    		context.put("clearForm", "yes");
	    		context.put("mode","");
	    		context.put("flag", "");
	    		context.put("button", "add");
    			
    		}	// CLOSE listB.size

			vm = "app/ppt/frmBantahanPampasanEFT.jsp";	
			
		
		}else if("simpan_eft".equals(submit)){
			
			selectedtab = "2";
			context.put("selectedtab", selectedtab);
			
			if (doPost.equals("true")){
				
				// INSERT BAYARAN & UPDATE TBLPPTHAKMILIKPB & UPDATE TBLPPTBANTAHAN
				simpan_eft(usid,id_bantahan);
				
				// UPDATE TBLPPTPERMOHONAN		
				updateStatus_selesaiBantahan(session);			

				// UPDATE TBLPPTSUBURUSANHAKMILIK
				updateSuburusanHakmilik_selesaiBantahan(session,id_permohonan,id_fail,id_hakmilik,id_suburusanstatushakmilik);

				// UPDATE TBLPPTSUBURUSANSTATUSFAILPPT
				updateSuburusanStatusFailPPT_selesaiBantahan(session,id_permohonan,id_fail,id_suburusanstatusfailppt);				
				
				// UPDATE TBLPPTSUBURUSANSTATUSBANTAHAN
				selesaiPampasanBantahan_tblrujsuburusanstatusbantahan(usid,id_bantahan,id_permohonan,id_hakmilik,id_fail);
			}			
			
			Hashtable _maxIdBayaran = model.getIdBayaran(id_hakmilikpb,id_bantahan);
			String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
			
			listE = model.getMaklumatViaEFT(id_bayaran);
			context.put("getMaklumatViaEFT", listE);
			
    		context.put("clearForm", "");
    		context.put("mode","disabled");
    		context.put("flag", "semak");
    		context.put("button", "view");
			
			vm = "app/ppt/frmBantahanPampasanEFT.jsp";
			
			
		}else if("simpan_eftAgensi".equals(submit)){
		
			selectedtab = "2";
			context.put("selectedtab", selectedtab);
			
			if (doPost.equals("true")){
				
				// INSERT TBLPPTBAYARAN
				simpan_eftAgensi(usid,id_bantahan);
				
				// UPDATE TBLPPTPERMOHONAN		
				updateStatus_selesaiBantahanAgensi(session);			

				// UPDATE TBLPPTSUBURUSANHAKMILIK
				updateSuburusanHakmilik_selesaiBantahanAgensi(session,id_permohonan,id_fail,id_hakmilik,id_suburusanstatushakmilik);

				// UPDATE TBLPPTSUBURUSANSTATUSFAILPPT
				updateSuburusanStatusFailPPT_selesaiBantahanAgensi(session,id_permohonan,id_fail,id_suburusanstatusfailppt);			
				
				
				// UPDATE TBLPPTSUBURUSANSTATUSBANTAHAN
				selesaiPampasanBantahanAgensi_tblrujsuburusanstatusbantahan(usid,id_bantahan,
						id_permohonan,id_hakmilik,id_fail);
			}			
			
			Hashtable _maxIdBayaran = model.getIdBayaranAgensi(id_hakmilik,id_bantahan);
			String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
			
			listE = model.getMaklumatViaEFT(id_bayaran);
			context.put("getMaklumatViaEFT", listE);
			
    		context.put("clearForm", "");
    		context.put("mode","disabled");
    		context.put("flag", "semak");
    		context.put("button", "view");
			
			vm = "app/ppt/frmBantahanAgensiPampasanEFT.jsp";
			
	
		}else if("skrin_editeft".equals(submit)){
			
			selectedtab = "2";
			context.put("selectedtab", selectedtab);
			
			Hashtable _maxIdBayaran = model.getIdBayaran(id_hakmilikpb,id_bantahan);
			String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
			
			listE = model.getMaklumatViaEFT(id_bayaran);
			context.put("getMaklumatViaEFT", listE);
			
    		context.put("clearForm", "");
    		context.put("mode","");
    		context.put("flag", "semak");
    		context.put("button", "edit");
		
			vm = "app/ppt/frmBantahanPampasanEFT.jsp";
			
			
		}else if("skrin_editeftAgensi".equals(submit)){
			
			selectedtab = "2";
			context.put("selectedtab", selectedtab);
			
			Hashtable _maxIdBayaran = model.getIdBayaranAgensi(id_hakmilik,id_bantahan);
			String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
			
			listE = model.getMaklumatViaEFT(id_bayaran);
			context.put("getMaklumatViaEFT", listE);
			
    		context.put("clearForm", "");
    		context.put("mode","");
    		context.put("flag", "semak");
    		context.put("button", "edit");
		
			vm = "app/ppt/frmBantahanAgensiPampasanEFT.jsp";			
			
			
		}else if("edit_eft".equals(submit)){
			
			selectedtab = "2";
			context.put("selectedtab", selectedtab);
			
			Hashtable _maxIdBayaran = model.getIdBayaran(id_hakmilikpb,id_bantahan);
			String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
			
			edit_eft(usid,id_bayaran);
			
			listE = model.getMaklumatViaEFT(id_bayaran);
			context.put("getMaklumatViaEFT", listE);
			
    		context.put("clearForm", "");
    		context.put("mode","disabled");
    		context.put("flag", "semak");
    		context.put("button", "view");
			
			vm = "app/ppt/frmBantahanPampasanEFT.jsp";
			
			
		}else if("edit_eftAgensi".equals(submit)){
			
			selectedtab = "2";
			context.put("selectedtab", selectedtab);
			
			Hashtable _maxIdBayaran = model.getIdBayaranAgensi(id_hakmilik,id_bantahan);
			String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
			
			edit_eft(usid,id_bayaran);
			
			listE = model.getMaklumatViaEFT(id_bayaran);
			context.put("getMaklumatViaEFT", listE);
			
    		context.put("clearForm", "");
    		context.put("mode","disabled");
    		context.put("flag", "semak");
    		context.put("button", "view");
			
			vm = "app/ppt/frmBantahanAgensiPampasanEFT.jsp";			
			
    	}else if("checkingCaraBayarAgensiPemohon".equals(submit)){
    		
    		listF = model.getMaklumatAP(id_hakmilik);
    		context.put("getMaklumatAP", listF);
    		
    		if(listF.size()!=0){
    			Hashtable a = (Hashtable) listF.get(0);
    			String flag_bayar_bantahan = (String)a.get("flag_bayar_bantahan");
    			myLogger.info("FLAG BAYARAN BANTAHAN :: "+flag_bayar_bantahan);
    			if(flag_bayar_bantahan.equals("1")){
    				Hashtable _methodCaraBayar = model.getMethodCaraBayarAgensi(id_hakmilik);
    					String cara_bayar = (String)_methodCaraBayar.get("cara_bayar");
    					myLogger.info("CARA BAYAR :: "+cara_bayar);
    					
    					if(cara_bayar.equals("1")){
    						myLogger.info("1 = CEK");
    						 
    						selectedtab = "0";
    						context.put("selectedtab", selectedtab);
    						
    	    				Hashtable _maxIdBayaran = model.getIdBayaranAgensi(id_hakmilik,id_bantahan);
    	    				String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
    						
    	    				listC = model.getMaklumatTerimaCek(id_bayaran);
    	    				context.put("getMaklumatTerimaCek",listC);
    	    				
    	    				context.put("mode", "disabled");
    	    				context.put("clearForm","");
    	    				context.put("button","view");
    	    				context.put("flag", "semak");
    						
    						vm = "app/ppt/frmBantahanAgensiPampasanTerimaCek.jsp";
    						
    					}else{
    						myLogger.info("2 = EFT");
    						
    						selectedtab = "2";
    						context.put("selectedtab", selectedtab);
    						
    	    				Hashtable _maxIdBayaran = model.getIdBayaranAgensi(id_hakmilik,id_bantahan);
    	    				String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
    	    				
    	    				listE = model.getMaklumatViaEFT(id_bayaran);
    						context.put("getMaklumatViaEFT",listE);
    						
    	    				context.put("mode", "disabled");
    	    				context.put("clearForm","");
    	    				context.put("button","view");
    	    				context.put("flag", "semak");
    						
    						vm = "app/ppt/frmBantahanAgensiPampasanEFT.jsp";
    					}
    					
    				
    			}else{
    				
    				context.put("mode", "");
    				context.put("clearForm","yes");
    				context.put("button","add");
    				context.put("flag", "");
    				
    				vm = "app/ppt/frmBantahanAgensiPampasanTerimaCek.jsp";
    			}
    			
    			
    		}else{
    			
    			context.put("status", true);
    			vm = "app/ppt/frmBantahanAgensiPampasanTerimaCek.jsp";
    		}		
    		
    		
		}else if("TerimaCekAgensi".equals(submit)){
			
			selectedtab = "0";
			context.put("selectedtab", selectedtab);
			
    		listF = model.getMaklumatAP(id_hakmilik);
    		context.put("getMaklumatAP", listF);
    		
    		if(listF.size()!=0){
    			Hashtable a = (Hashtable) listF.get(0);
    			String flag_bayar_bantahan = (String)a.get("flag_bayar_bantahan");
    			myLogger.info("FLAG BAYARAN BANTAHAN :: "+flag_bayar_bantahan);
    			
    			if(flag_bayar_bantahan.equals("1")){
    				
    				Hashtable _methodCaraBayar = model.getMethodCaraBayarAgensi(id_hakmilik);
					String cara_bayar = (String)_methodCaraBayar.get("cara_bayar");
					myLogger.info("CARA BAYAR :: "+cara_bayar);
					
					if(cara_bayar.equals("1")){
						myLogger.info("1 = CEK");
						
	    				Hashtable _maxIdBayaran = model.getIdBayaranAgensi(id_hakmilik,id_bantahan);
	    				String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
	    				
	    				listC = model.getMaklumatTerimaCek(id_bayaran);
	    				context.put("getMaklumatTerimaCek",listC);
	    				
	    				context.put("mode", "disabled");
	    				context.put("clearForm","");
	    				context.put("button","view");
	    				context.put("flag", "semak");
						
					}else{
						
	    				context.put("mode", "");
	    				context.put("clearForm","");
	    				context.put("button","add");
	    				context.put("flag", "");
						
					}	
    				
    			}else{    				
    				
    				context.put("mode", "");
    				context.put("clearForm","yes");
    				context.put("button","add");
    				context.put("flag", "");
    			}
    			
    		}else{
    			context.put("status", true);
    		}
			
			vm = "app/ppt/frmBantahanAgensiPampasanTerimaCek.jsp";    		

			
		}else if("SerahCekAgensi".equals(submit)){
			
			selectedtab = "1";
			context.put("selectedtab", selectedtab);
			
    		listF = model.getMaklumatAP(id_hakmilik);
    		context.put("getMaklumatAP", listF);
    		
    		Hashtable _maxIdBayaran = model.getIdBayaranAgensi(id_hakmilik,id_bantahan);
    		String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
    		
    		listC = model.getMaklumatTerimaCek(id_bayaran);
    		if(listC.size()!=0){
    			Hashtable b = (Hashtable)listC.get(0);
    			String flag_serah_cek = (String)b.get("flag_serah_cek");
    			
    			if(flag_serah_cek.equals("1")){
    				
    				listD = model.getMaklumatSerahCek(id_bayaran);
    				context.put("getMaklumatSerahCek", listD);
    				
    	    		context.put("clearForm", "");
    	    		context.put("mode","disabled");
    	    		context.put("flag", "semak");
    	    		context.put("button", "view");
    				
    			}else{
    				
    	    		context.put("clearForm", "yes");
    	    		context.put("mode","");
    	    		context.put("flag", "");
    	    		context.put("button", "add");    				
    			}   			
    			
    		}else{   			
    			context.put("status", true);   			
    		}
    			
			vm = "app/ppt/frmBantahanAgensiPampasanSerahCek.jsp";			
			
			
		}else if("BayaranEftAgensi".equals(submit)){
			
			selectedtab = "2";
			context.put("selectedtab", selectedtab);
			
	   		listF = model.getMaklumatAP(id_hakmilik);
    		context.put("getMaklumatAP", listF);
    		
    		if(listF.size()!=0){
    			Hashtable a = (Hashtable) listF.get(0);
    			String flag_bayar_bantahan = (String)a.get("flag_bayar_bantahan");
    			
    			if(flag_bayar_bantahan.equals("1")){
    				Hashtable _maxIdBayaran = model.getIdBayaranAgensi(id_hakmilik,id_bantahan);
    				String id_bayaran = (String) _maxIdBayaran.get("id_bayaran");
    				
    				listE = model.getMaklumatViaEFT(id_bayaran);
					context.put("getMaklumatViaEFT",listE);
    				
    				if(listE.size()!=0){
    					
    					Hashtable c = (Hashtable)listE.get(0);
    					String no_eft = (String)c.get("no_eft");
    					
    					if(no_eft.equals("")){
    						
    	    				context.put("mode", "");
    	    				context.put("clearForm","yes");
    	    				context.put("button","add");
    	    				context.put("flag", "");
    						
    					}else{
    						
    	    				context.put("mode", "disabled");
    	    				context.put("clearForm","");
    	    				context.put("button","view");
    	    				context.put("flag", "semak");
    	    				
    					}
    					
    				}else{
    					context.put("status", true);
    				}
    				
    				
    			}else{    				
    				
    	    		context.put("clearForm", "yes");
    	    		context.put("mode","");
    	    		context.put("flag", "");
    	    		context.put("button", "add");
    			}
    			
    		}else{
    			context.put("status", true);
    		}

			vm = "app/ppt/frmBantahanAgensiPampasanEFT.jsp";	
						
			
			
		}else if("kembali_listPB".equals(submit)){
			
			vm = "app/ppt/frmBantahanPampasan.jsp"; 
			
    	}else if("Cari".equals(submit)){

			carianBantahan(usid,userIdNeg);
			list = model.getListCarian(userIdNeg);

    		//data & size default list
    		context.put("PermohonanList", list);
    		context.put("list_size", list.size());  

    		//maintain searching value
    		this.context.put("txtNoFail", getParam("txtNoFail"));
    		String idKementerian = getParam("socKementerian");
    		context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Utils.parseLong(idKementerian),"style=width:470px"));
    		setupPage(session,action,list);
    		
			vm = "app/ppt/frmBantahanPampasanSenaraiCarian.jsp";    
			
		}else {
    		
			String txtNoFail = "";
			context.put("txtNoFail", "");
			
           	//GET LIST DATA
    		list = model.getListPemohon(userIdNeg);
    		
    		context.put("PermohonanList", list);
    		context.put("list_size", list.size());  	    		          		
    		context.put("selectKementerian",HTML.SelectKementerian("socKementerian",null,"style=width:470px"));
    		setupPage(session,action,list);
    		
    		vm = "app/ppt/frmBantahanPampasanSenaraiCarian.jsp";
    		
    	}
    	
    	myLogger.info("vm :"+vm);
    	return vm;
    }
	
	// METHOD
	
	private String userData(String id_user) throws Exception{
		
		Vector listUserid = new Vector();
		listUserid.clear();
		
		modelUPT.setGetUserId(id_user);
	    listUserid = modelUPT.getUserIds();
	    String userIdNeg = "";
	    if(listUserid.size()!=0){
	    	Hashtable t = (Hashtable)listUserid.get(0);
	    	userIdNeg = t.get("idNegeri").toString();
	    }
	    
	    return userIdNeg;

	}	

	private void carianBantahan(String usid,String userIdNeg) throws Exception {
		String txtNoFail = getParam("txtNoFail");
		String idKementerian = getParam("socKementerian");
		model.setCarianFail(usid,txtNoFail,idKementerian,userIdNeg);		
	}

	private void add_terimaCek(String usid) throws Exception {
		String id_hakmilik = getParam("id_hakmilik");
		String txdTkhTerima = getParam("txdTkhTerima");
		String txtPenamaCek = getParam("txtPenamaCek");
		String txtNoCek = getParam("txtNoCek");
		String txdTkhCek = getParam("txdTkhCek");
		String txtAmaunCek = getParam("txtAmaunCek");
		String txdTkhAkhirCek = getParam("txdTkhAkhirCek");
		String no_pb = getParam("no_pb");
		String id_pihakberkepentingan = getParam("id_pihakberkepentingan");
		String id_hakmilikpb = getParam("id_hakmilikpb");
		String id_bantahan = getParam("id_bantahan");
		String txdTkhAmbilCek = getParam("txdTkhAmbilCek");
		String txtMasaAmbilCek = getParam("txtMasaAmbilCek");
		//yati tambah
		String txdTkhCajLewatBantahan = getParam("txdTkhCajLewatBantahan");
		String txdTkhBayarBantah = getParam("txdTkhBayarBantah");
		String txtPeratusCaj = getParam("txtPeratusCaj");
		String txtAmaunLewatBantahan = getParam("txtAmaunLewatBantahan");
		String txtBilLewatBantahan = getParam("txtBilLewatBantahan");
		
		modelOperations.add_terimaCek(usid,no_pb,id_hakmilik,id_pihakberkepentingan,txdTkhTerima,
				txtPenamaCek,txtNoCek,txdTkhCek,txtAmaunCek,txdTkhAkhirCek,id_hakmilikpb,id_bantahan,
				txdTkhAmbilCek,txtMasaAmbilCek,txdTkhCajLewatBantahan,txdTkhBayarBantah,txtPeratusCaj,txtAmaunLewatBantahan,txtBilLewatBantahan);
	}
	
	private void add_terimaCekAgensi(String usid) throws Exception {
		String id_hakmilik = getParam("id_hakmilik");
		String txdTkhTerima = getParam("txdTkhTerima");
		String txtPenamaCek = getParam("txtPenamaCek");
		String txtNoCek = getParam("txtNoCek");
		String txdTkhCek = getParam("txdTkhCek");
		String txtAmaunCek = getParam("txtAmaunCek");
		String txdTkhAkhirCek = getParam("txdTkhAkhirCek");
		String no_pb = getParam("no_pb");
		String id_pihakberkepentingan = getParam("id_pihakberkepentingan");
		String id_hakmilikpb = getParam("id_hakmilikpb");
		String id_bantahan = getParam("id_bantahan");
		String txdTkhAmbilCek = getParam("txdTkhAmbilCek");
		String txtMasaAmbilCek = getParam("txtMasaAmbilCek");
		String id_agensi = getParam("id_agensi");
		modelOperations.add_terimaCekAgensi(usid,no_pb,id_hakmilik,id_pihakberkepentingan,txdTkhTerima,
				txtPenamaCek,txtNoCek,txdTkhCek,txtAmaunCek,txdTkhAkhirCek,id_hakmilikpb,id_bantahan,
				txdTkhAmbilCek,txtMasaAmbilCek,id_agensi);
	}	
	
//	private void updateStatusUrusanBayaran(String id_permohonan, String usid) throws Exception {
//		modelOperations.updateStatusUrusanBayaran(id_permohonan, usid);		
//	}
	
	private void update_terimaCek(String usid,String id_bayaran) throws Exception {
		String txdTkhTerima = getParam("txdTkhTerima");
		String txtNoCek = getParam("txtNoCek");
		String txdTkhCek = getParam("txdTkhCek");
		String txdTkhAkhirCek = getParam("txdTkhAkhirCek");
		String txdTkhAmbilCek = getParam("txdTkhAmbilCek");
		String txtMasaAmbilCek = getParam("txtMasaAmbilCek");
		//yati tambah
		String txdTkhCajLewatBantahan = getParam("txdTkhCajLewatBantahan");
		String txdTkhBayarBantah = getParam("txdTkhBayarBantah");
		String txtPeratusCaj = getParam("txtPeratusCaj");
		String txtAmaunLewatBantahan = getParam("txtAmaunLewatBantahan");
		String txtBilLewatBantahan = getParam("txtBilLewatBantahan");
		modelOperations.update_terimaCek(usid,id_bayaran,txdTkhTerima,txtNoCek,txdTkhCek,
				txdTkhAkhirCek,txdTkhAmbilCek,txtMasaAmbilCek,txdTkhCajLewatBantahan,txdTkhBayarBantah,txtPeratusCaj,txtAmaunLewatBantahan,txtBilLewatBantahan);		
	}
	
	private void update_serahCek(String usid, String id_bayaran, String id_bantahan) throws Exception {
		String txtPenerimaX = getParam("txtPenerimaX");
		String txtNoKPx = getParam("txtNoKPx");
		String txdTkhSerah = getParam("txdTkhSerah");
		String socStatusSerah = getParam("socStatusSerah");
		
		modelOperations.update_serahCek(usid,id_bayaran,id_bantahan,txtPenerimaX,txtNoKPx,txdTkhSerah,
				socStatusSerah);
	}	
	
	private void update_serahCekAgensi(String usid, String id_bayaran, String id_bantahan) throws Exception {
		String txtPenerimaX = getParam("txtPenerimaX");
		String txtNoKPx = getParam("txtNoKPx");
		String txdTkhSerah = getParam("txdTkhSerah");
		String socStatusSerah = getParam("socStatusSerah");
		String id_agensi = getParam("id_agensi");
		modelOperations.update_serahCekAgensi(usid,id_bayaran,id_bantahan,txtPenerimaX,txtNoKPx,txdTkhSerah,
				socStatusSerah, id_agensi);
	}		

	private void updateStatusSelesai(String id_permohonan, String usid) throws Exception {
		modelOperations.updateStatusSelesai(id_permohonan,usid);
	}
	
	private void simpan_eft(String usid,String id_bantahan) throws Exception {
		String txtNoRujSurat = getParam("txtNoRujSurat");
		String txdTkhSurat = getParam("txdTkhSurat");
		String txtNoEFT = getParam("txtNoEFT");
		String txdTkhTerimaSurat = getParam("txdTkhTerimaSurat");
		String txdTkhBayaran = getParam("txdTkhBayaran");
		String txtNoBaucer = getParam("txtNoBaucer");
		String txtAmaun = getParam("txtAmaun");
		String no_pb = getParam("no_pb");
		String id_pihakberkepentingan = getParam("id_pihakberkepentingan");
		String id_hakmilikpb = getParam("id_hakmilikpb");
		modelOperations.simpan_eft(usid,id_bantahan,txtNoRujSurat,txdTkhSurat,txtNoEFT,txdTkhTerimaSurat,txdTkhBayaran,
				txtNoBaucer,txtAmaun,no_pb,id_pihakberkepentingan,id_hakmilikpb);
	}
	
	private void simpan_eftAgensi(String usid,String id_bantahan) throws Exception {
		String txtNoRujSurat = getParam("txtNoRujSurat");
		String txdTkhSurat = getParam("txdTkhSurat");
		String txtNoEFT = getParam("txtNoEFT");
		String txdTkhTerimaSurat = getParam("txdTkhTerimaSurat");
		String txdTkhBayaran = getParam("txdTkhBayaran");
		String txtNoBaucer = getParam("txtNoBaucer");
		String txtAmaun = getParam("txtAmaun");
		String no_pb = getParam("no_pb");
		String id_pihakberkepentingan = getParam("id_pihakberkepentingan");
		String id_hakmilikpb = getParam("id_hakmilikpb");
		String id_hakmilik = getParam("id_hakmilik");
		String id_agensi = getParam("id_agensi");
		
		modelOperations.simpan_eftAgensi(usid,id_bantahan,txtNoRujSurat,txdTkhSurat,txtNoEFT,txdTkhTerimaSurat,txdTkhBayaran,
				txtNoBaucer,txtAmaun,no_pb,id_pihakberkepentingan,id_hakmilikpb,id_hakmilik,id_agensi);
	}	
	
	private void edit_eft(String usid, String id_bayaran) throws Exception {
		String txtNoRujSurat = getParam("txtNoRujSurat");
		String txdTkhSurat = getParam("txdTkhSurat");
		String txtNoEFT = getParam("txtNoEFT");
		String txdTkhTerimaSurat = getParam("txdTkhTerimaSurat");
		String txdTkhBayaran = getParam("txdTkhBayaran");
		String txtNoBaucer = getParam("txtNoBaucer");
		String txtAmaun = getParam("txtAmaun");	
		modelOperations.edit_eft(usid,id_bayaran,txtNoRujSurat,txdTkhSurat,txtNoEFT,txdTkhTerimaSurat,
				txdTkhBayaran,txtNoBaucer,txtAmaun);
	}	
	
	private void urusanBayaran_tblrujsuburusanstatusbantahan(String usid,String id_bantahan,String id_permohonan,String id_hakmilik,String id_fail) throws Exception {
		modelOperations.urusanBayaran_tblrujsuburusanstatusbantahan(usid,id_bantahan,id_permohonan,id_hakmilik,id_fail);	
	}
	
	private void selesaiPampasanBantahan_tblrujsuburusanstatusbantahan(String usid,String id_bantahan,String id_permohonan,String id_hakmilik,String id_fail) throws Exception {
		modelOperations.selesaiPampasanBantahan_tblrujsuburusanstatusbantahan(usid,id_bantahan,id_permohonan,id_hakmilik,id_fail);	
	}
	
	private void selesaiPampasanBantahanAgensi_tblrujsuburusanstatusbantahan(String usid,String id_bantahan,String id_permohonan,String id_hakmilik,String id_fail) throws Exception {
		modelOperations.selesaiPampasanBantahanAgensi_tblrujsuburusanstatusbantahan(usid,id_bantahan,id_permohonan,id_hakmilik,id_fail);	
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
		this.context.put("PermohonanList",paging.getPage(page));
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
	public void setupPagePB(HttpSession session,String action,Vector listB) {
		try {		
		this.context.put("totalRecords",listB.size());
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
	    Paging paging = new Paging(session,listB,itemsPerPage);		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("getSenaraiPB",paging.getPage(page));
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
	
	// PAGING SENARAI AGENSI
	public void setupPageAP(HttpSession session,String action,Vector listF) {
		try {		
		this.context.put("totalRecords",listF.size());
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
	    Paging paging = new Paging(session,listF,itemsPerPage);		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("getSenaraiAP",paging.getPage(page));
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
	
	private void updateStatus_urusanBayaran(HttpSession session) throws Exception{

		String id_permohonan = getParam("id_permohonan");
		String idUser = (String) session.getAttribute("_ekptg_user_id");

		//status URUSAN BAYARAN
		String idstatus = "186";

		modelOperations.updateStatus_urusanBayaran(id_permohonan,idUser,idstatus);

	}//close update updateStatus_urusanBayaran
					
					
					
	private void updateSuburusanStatusFailPPT_urusanBayaran(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		//update suburusanstatusfailppt
		modelOperations.updateSuburusanStatusFailPPT_urusanBayaran(h,id_suburusanstatusfailppt,"1504");

	}//close updateSuburusanStatusFailPPT_urusanBayaran


	private void updateSuburusanHakmilik_urusanBayaran(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		modelOperations.updateSuburusanHakmilik_urusanBayaran(h,id_suburusanstatushakmilik,"1504");

	}//close updateSuburusanHakmilik_urusanBayaran

	
	private void updateStatus_urusanBayaranAgensi(HttpSession session) throws Exception{

		String id_permohonan = getParam("id_permohonan");
		String idUser = (String) session.getAttribute("_ekptg_user_id");

		//status URUSAN DEPOSIT BANTAHAN
		String idstatus = "204";

		modelOperations.updateStatus_urusanBayaranAgensi(id_permohonan,idUser,idstatus);

	}//close updateStatus_urusanBayaranAgensi				
					
					
	private void updateSuburusanStatusFailPPT_urusanBayaranAgensi(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		//update suburusanstatusfailppt
		modelOperations.updateSuburusanStatusFailPPT_urusanBayaranAgensi(h,id_suburusanstatusfailppt,"16102118");

	}//close updateSuburusanStatusFailPPT_urusanBayaranAgensi


	private void updateSuburusanHakmilik_urusanBayaranAgensi(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		modelOperations.updateSuburusanHakmilik_urusanBayaranAgensi(h,id_suburusanstatushakmilik,"16102118");

	}//close updateSuburusanHakmilik_urusanBayaranAgensi

	private void urusanBayaranAgensi_tblrujsuburusanstatusbantahan(String usid,String id_bantahan,String id_permohonan,String id_hakmilik,String id_fail) throws Exception {
		
		modelOperations.urusanBayaranAgensi_tblrujsuburusanstatusbantahan(usid,id_bantahan,id_permohonan,id_hakmilik,id_fail);	
	
	}
	

	private void updateStatus_selesaiBantahan(HttpSession session) throws Exception{

		String id_permohonan = getParam("id_permohonan");
		String idUser = (String) session.getAttribute("_ekptg_user_id");

		//status SELESAI BANTAHAN
		String idstatus = "187";

		modelOperations.updateStatus_selesaiBantahan(id_permohonan,idUser,idstatus);

	}//close updateStatus_selesaiBantahan
					
					
					
	private void updateSuburusanStatusFailPPT_selesaiBantahan(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		//update suburusanstatusfailppt
		modelOperations.updateSuburusanStatusFailPPT_selesaiBantahan(h,id_suburusanstatusfailppt,"1505");

	}//close updateSuburusanStatusFailPPT



	private void updateSuburusanHakmilik_selesaiBantahan(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		modelOperations.updateSuburusanHakmilik_selesaiBantahan(h,id_suburusanstatushakmilik,"1505");

	}//close addSuburusanHakmilik

	
	private void updateStatus_selesaiBantahanAgensi(HttpSession session) throws Exception{

		String id_permohonan = getParam("id_permohonan");
		String idUser = (String) session.getAttribute("_ekptg_user_id");

		//status SELESAI BANTAHAN AGENSI
		String idstatus = "205";

		modelOperations.updateStatus_selesaiBantahanAgensi(id_permohonan,idUser,idstatus);

	}//close updateStatus_selesaiBantahan
					
					
					
	private void updateSuburusanStatusFailPPT_selesaiBantahanAgensi(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		//update suburusanstatusfailppt
		modelOperations.updateSuburusanStatusFailPPT_selesaiBantahanAgensi(h,id_suburusanstatusfailppt,"16102120");

	}//close updateSuburusanStatusFailPPT



	private void updateSuburusanHakmilik_selesaiBantahanAgensi(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		modelOperations.updateSuburusanHakmilik_selesaiBantahanAgensi(h,id_suburusanstatushakmilik,"16102120");

	}//close addSuburusanHakmilik	
	
}
