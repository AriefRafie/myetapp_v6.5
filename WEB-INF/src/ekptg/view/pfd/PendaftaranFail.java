package ekptg.view.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.pfd.FrmDaftarFailData;
import ekptg.model.pfd.FrmKemaskiniDokumenData;
import ekptg.model.pfd.FrmKemaskiniFailData;
import ekptg.model.pfd.FrmLogDokumenData;
import ekptg.model.pfd.FrmPendaftaranFailData;

public class PendaftaranFail extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(PendaftaranFail.class);
	FrmPendaftaranFailData logic = new FrmPendaftaranFailData();
	Date now = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
   
   
	public String doTemplate2() throws Exception

    {
		
//		System.out.println("#########################");
//		System.out.println("noFailExists(\"PTP 15/175/1996\"): " + logic.noFailExists("PTP 15/175/1996"));
//		System.out.println("getNoFail(SEKSYEN-TIADA AKTIVITI): " + logic.getNoFail(true, 1, 17, 0, 0, 10, 5, 0, false));
//		System.out.println("getNoFail(SEKSYEN-ADA AKTIVITI): " + logic.getNoFail(true, 2, 17, 0, 0, 10, 5, 0, true));
//		System.out.println("getNoFail(SEKSYEN-ADA SUBSUBURUSAN): " + logic.getNoFail(true, 3, 17, 0, 0, 10, 5, 1, true));
//		System.out.println("getNoFail(NEGERI-TIADA AKTIVITI): " + logic.getNoFail(false, 4, 0, 1, 149, 10, 5, 1, false));
//		System.out.println("getNoFail(NEGERI-ADA AKTIVITI): " + logic.getNoFail(false, 5, 0, 1, 149, 10, 5, 1, true));
//		System.out.println("#########################");
			
            String vm = "";
            String submit = getParam("command");
            String submit1 = getParam("command1");
            String action1 = getParam("action1");
            String action2 = getParam("action2");
			String action = getParam("action");
			
			if ("doChangeItemPerPage".equals(action) ||
					"getPage".equals(action)) {
				action1 = action;
			}
            String mode = getParam("mode");
            String modeAktiviti = getParam("modeAktiviti");
            HttpSession session = this.request.getSession();
          	String user_id = null;
            String tajukFail = "";
            String aktiviti = "";
            String aktivitiValue = "";
            String noFail = getParam("txtNoFail");
            String noFailLama = getParam("txtNoFailLama");
            String noFailArkib = getParam("txtNoFailArkib");
            String Flag_failWujud = "false";
            String modeSubUrusan = "";
            String idFail2 = getParam("idFail");
            String value = getParam("value");
            String negeri = getParam("socNegeriD");
            String seksyen = getParam("socSeksyenD");
            String unit = getParam("socUnit");
            String status = getParam("socStatusD");
            String idNegeriA = getParam("socNegeriA");
            String idSeksyenA = getParam("socSeksyenA");
            String id_FailArkib = getParam("id_FailArkib");
            String id_FailLama = getParam("id_FailLama");
            context.put("id_FailLama",id_FailLama);
            
            String idTarafA = getParam("socTarafA");
            if ("".equals(idTarafA) || "0".equals(idTarafA)){
            	idTarafA = "1";
            }
            String tajukFailA = "";
            String idStatusA= getParam("socStatusA");
            if ("".equals(idStatusA) || "0".equals(idStatusA)){
            	idStatusA = "7";
            }
            String idLokasiA = getParam("socLokasiFailA");
            String idFaharasatA = getParam("socFaharasatA");
            String idUrusanA = getParam("socUrusanA");
            String idSubUrusanA = getParam("socSuburusanA");
            
            String idNegeriB = getParam("socNegeriB");
            String idSeksyenB = getParam("socSeksyenB");
            String idTarafB = getParam("socTarafB");
            if ("".equals(idTarafB) || "0".equals(idTarafB)){
            	idTarafB = "1";
            }
            String tajukFailB = "";
            String idStatusB= getParam("socStatusB");
            if ("".equals(idStatusB) || "0".equals(idStatusB)){
            	idStatusB = "7";
            }
            String idLokasiB = getParam("socLokasiFailB");
            String idFaharasatB = getParam("socFaharasatB");
            String idUrusanB = getParam("socUrusanB");
           
            String idSubUrusanB = getParam("socSuburusanB");
            String tarikhDaftar = "";
            context.put("ScrollX",getParam("ScrollX"));
            context.put("ScrollY",getParam("ScrollY"));        
            
            String tajukFailC = "";
            String kodSeksyen = "";
            String kodBaruSeksyen = "";
            String kod_negeri = "";
            String namaSeksyen = "";
            String idSeksyenC = getParam("socSeksyenC");
            String idUrusanC = getParam("socUrusanC");
            String idSubUrusanC = getParam("socSuburusanC");
            String idSubSubUrusanC = getParam("socSubSuburusanC");
            String idSubSubSubUrusanC = getParam("socSubSubSuburusanC");
            String idTarafC = getParam("socTarafC");
            if ("".equals(idTarafC) || "0".equals(idTarafC)){
            	idTarafC = "1";
            }
            String idStatusC= getParam("socStatusC");
            if ("".equals(idStatusC) || "0".equals(idStatusC)){
            	idStatusC = "7";
            }
            String idLokasiC = getParam("socLokasiFailC");
            String idFaharasatC = getParam("socFaharasatC");
            
            Vector list = new Vector();
            Vector paparSeksyen = null;
            Vector paparUnit = null;
            Vector paparFailLama = null;
            Vector paparFailBaru = null;
            Vector paparTaraf = null;
            Vector paparStatus = null;
            Vector paparFail = null;
            Vector paparFailArkib = null;
            Vector paparSemuaFail = null;
            Vector paparFailLamaAll = null;
            Vector paparFailBaruAll = null;
            Vector paparNoTurutanIDFail = null;
            Vector getSubUrusanbyIdUrusan = null;
            Vector getSubSubUrusanbyIdUrusan = null;
            Vector listUrusan = null;
            Vector listSubUrusan = null;
            Vector listSubSubUrusan = null;
            Vector listUrusanArkib = null;
            Vector failLama = null;
            
            this.context.put("Util",new Utils());
            String user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
            String doPost = (String) session.getAttribute("doPost");
            context.put("hapus", "false");
            context.put("result","");
            context.put("user_negeri", user_negeri);
          
            
//------------------------------------- fail seksyen -----------------------------------------------------------------------              
           
            if("checkNoFail".equals(submit))
            {
            	vm = "app/pfd/frmPendaftaranFailSeksyenLama.jsp";
        		context.put("mode", "baru");
            	noFail = getParam("noFail");
            	
            	if (noFail != "" ){
            		Boolean failwujud = logic.noFailExists(noFail,user_negeri);	
            			if (failwujud == true)
            			{
            				
            				context.put("mode", "baru");
            				Flag_failWujud = "true";
             		        if("16".equalsIgnoreCase(user_negeri)){
            		        	context.put("noFail", noFail);
            		        	user_negeri = "16";
            		        	action1 = "tabFailSeksyenLama";	
            		        }
            		        else{
            		        	context.put("noFail", noFail);
            		        	action1 = "tabFailNegeriLama";	
            		        }
            				
            			}
            			else{
            				context.put("mode", "baru");
            				Flag_failWujud = "false";
             		        if("16".equalsIgnoreCase(user_negeri)){
            		        	context.put("noFail", noFail);
            		        	user_negeri = "16";
            		        	action1 = "tabFailSeksyenLama";	
            		        }
            		        else{
            		        	context.put("noFail", noFail);
            		        	action1 = "tabFailNegeriLama";	
            		        }
             				
            			}
            	}
            	
            	
            }
            
         
            
            if("16".equalsIgnoreCase(user_negeri))
            {
            	if("tabFailSeksyenLama".equals(action1))
            	{
            		vm = "app/pfd/frmPendaftaranFailSeksyenLama.jsp";
            		
            		this.context.put("mode", "baru");
                	context.put("readonly","");
    				context.put("disabled","");
                	context.put("readonlyNoFail","");
    				context.put("readonlyTajukFail","");
    			  	context.put("disabledNoFail","");
    				context.put("disabledTajukFail","");
    				context.put("readonlyNoTurutan","");
    				context.put("disabledNoTurutan","");
    				context.put("readonlyNoJilid","");
    				context.put("disabledNoJilid","");
    				context.put("readonlyNoSubjaket","");
    				context.put("disabledNoSubjaket","");
    				context.put("Flag_failWujud", Flag_failWujud); 
    				context.put("SenaraiSubUrusan","");
    				
                	String idFail = getParam("idFail");
                	String flagsubjaket = getParam("flagsubjaket");
                	context.put("flagsubjaket",flagsubjaket);
                	
                	view_Seksyen(session);
    	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
    	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
    	         	idSeksyenB = String.valueOf(hA.get("id_seksyen"));
    	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
    	         	context.put("idSeksyen",idSeksyenB);
    	         	context.put("idNegeri",id_negeri);
    				

                	noFail = getParam("noFail");
                	this.context.put("noFail",noFail);
                	String noFailRoot = getParam("noFailRoot");
                	this.context.put("noFailRoot",noFailRoot);
                	String noTurutan = getParam("noTurutan");
                	this.context.put("noTurutan","0");
                	String noJilid = getParam("noJilid");
                	this.context.put("noJilid","1");
                	String noSubjaket = getParam("noSubjaket");
                	this.context.put("noSubjaket","0");
                	tajukFailB = getParam("txtTajukFailB");
                	this.context.put("tajukFailB",tajukFailB);
                	
                	
                	
                	this.context.put("selectSeksyenB",HTML.SelectSeksyen("socSeksyenB",Utils.parseLong(idSeksyenB),"disabled"));
                	
              	
                	this.context.put("idUrusan","");
                	this.context.put("idSubUrusan","");
                	FrmPendaftaranFailData.getUrusan();
                	listUrusan = FrmPendaftaranFailData.getListUrusan();
                	this.context.put("SenaraiUrusan",listUrusan);
                	
                
                	if ("getSubUrusan".equals(submit1)){
                		
                		this.context.put("idUrusan",idUrusanB);
                		FrmPendaftaranFailData.getSubUrusan(idUrusanB);
                		listSubUrusan = FrmPendaftaranFailData.getListSubUrusan();
                		this.context.put("SenaraiSubUrusan", listSubUrusan);
                		this.context.put("TotalSubUrusan",listSubUrusan.size());
                		this.context.put("idSubUrusan",idSubUrusanB);
                		
                	}
                	
                	        	    
               	    this.context.put("selectLokasiFailB",HTML.SelectLokasiFailNegeri("socLokasiFailB",Utils.parseLong(idLokasiB),"", user_negeri));
                	String kabinetB = getParam("txtKabinetB");
                	this.context.put("kabinetB",kabinetB);
               	    this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(idTarafB),""));
               	    this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(idStatusB),"disabled"));
               	    this.context.put("tarikhDaftarB", sdf.format(now));
            	}
            	
            	else if("tabFailSeksyenBaru".equals(action1))
            	{
            		vm = "app/pfd/frmPendaftaranFailSeksyenBaru.jsp";
                 	this.context.put("mode", "baru");
                	context.put("readonly","");
    				context.put("disabled","");
                	context.put("readonlyNoFail","");
    				context.put("readonlyTajukFail","");
    			  	context.put("disabledNoFail","");
    				context.put("disabledTajukFail","");
    				context.put("disabledNoFail","");
    				context.put("disabledTajukFail","");
    				context.put("readonlyK","");
    			  	context.put("disabledK","");
    			  	context.put("SenaraiSubUrusan","");
    				
                	String idFail = getParam("idFail");
                	String flagsubjaket = getParam("flagsubjaket");
                	context.put("flagsubjaket",flagsubjaket);
                	
                	view_Seksyen(session);
    	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
    	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
    	         	idSeksyenB = String.valueOf(hA.get("id_seksyen"));
    	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
    	         	context.put("idSeksyen",idSeksyenB);
    	         	context.put("idNegeri",id_negeri);
                	
                	tajukFailB = getParam("txtTajukFailB");
                	this.context.put("tajukFailB",tajukFailB);
                	this.context.put("selectSeksyenB",HTML.SelectSeksyen("socSeksyenB",Utils.parseLong(idSeksyenB),"disabled"));
                	
                		if ("ada".equals(modeAktiviti)) {
                			submit = getParam("command");
                			String socNegeriB = getParam("socNegeriB");
               	    		String socDaerahB = getParam("socDaerahB");
               	    		String socSeksyenB = getParam("socSeksyenB");
               	    		String selectUrusanB = getParam("selectUrusanB")==""?"0" : getParam("selectUrusanB");
               	    		String selectSubUrusanB = getParam("selectSubUrusanB")==""?"0" : getParam("selectSubUrusanB");
                			this.context.put("aktiviti1","ada");
    	         	 		this.context.put("aktiviti","");
    	         	 		this.context.put("radioCheck1", "");
    	         	 		this.context.put("radioCheck2", "checked");
    	         	 		this.context.put("readOnly","");
    	         	 		this.context.put("aktivitiValue","1");
    	         	 		
    	         	 		this.context.put("idUrusan",idUrusanB);
    	         	 		this.context.put("idSubUrusan",idSubUrusanB);
    	                	FrmPendaftaranFailData.getUrusan();
    	                	listUrusan = FrmPendaftaranFailData.getListUrusan();
    	                	this.context.put("SenaraiUrusan",listUrusan);
    	                	
    	                
    	                	if ("getSubUrusan".equals(submit1)){
    	                		this.context.put("idUrusan",idUrusanB);
    	                		FrmPendaftaranFailData.getSubUrusan(idUrusanB);
    	                		listSubUrusan = FrmPendaftaranFailData.getListSubUrusan();
    	                		this.context.put("SenaraiSubUrusan", listSubUrusan);
    	                		this.context.put("TotalSubUrusan",listSubUrusan.size());
    	                		this.context.put("idSubUrusan",idSubUrusanB);
    	                	}
    	         	 		
           	    	
    	           	    this.context.put("selectSubSubUrusanB",HTML.SelectSuburusan("socSubSubUrusanB"));
    	           	    this.context.put("selectLokasiFailB",HTML.SelectLokasiFailNegeri("socLokasiFailB",Utils.parseLong(idLokasiB),"",user_negeri));
    	            	String kabinetB = getParam("txtKabinetB");
    	            	this.context.put("kabinetB",kabinetB);    
    	           	    this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(idTarafB),""));
    	           	    this.context.put("selectStatusB",HTML.SelectStatusFailA("socStatusB",Utils.parseLong(idStatusB),"disabled"));
    	           	    this.context.put("tarikhDaftarB", sdf.format(now));
    	   
    	         	 	}
                		
    	         	 	else{	 	
    	         	 		String socNegeriB = getParam("socNegeriB");
    	       	    		String socDaerahB = getParam("socDaerahB");
    	       	    		String socDaerah = getParam("socDaerahB");
    	       	    		String selectUrusanB = getParam("selectUrusanB");
    	       	    		String selectSubUrusanB = getParam("selectSubUrusanB");
    	         	 		this.context.put("aktiviti1","tiada");
    	         	 		this.context.put("radioCheck1", "checked");
    	         	 		this.context.put("radioCheck2", "");
    	         	 		this.context.put("readOnly","");
    	         	 		this.context.put("aktivitiValue","0");
    	         	 		
    	         	 		this.context.put("idUrusan","");
    	                	this.context.put("idSubUrusan","");
    	                	FrmPendaftaranFailData.getUrusan();
    	                	listUrusan = FrmPendaftaranFailData.getListUrusan();
    	                	this.context.put("SenaraiUrusan",listUrusan);
    	                	
    	                
    	                	if ("getSubUrusan".equals(submit1)){
    	                		
    	                		this.context.put("idUrusan",idUrusanB);
    	                		FrmPendaftaranFailData.getSubUrusan(idUrusanB);
    	                		listSubUrusan = FrmPendaftaranFailData.getListSubUrusan();
    	                		this.context.put("SenaraiSubUrusan", listSubUrusan);
    	                		this.context.put("TotalSubUrusan",listSubUrusan.size());
    	                		this.context.put("idSubUrusan",idSubUrusanB);
    	                		
    	                	}
    	         	 		
    	         	 		

    	          	    this.context.put("selectSubSubUrusanB",HTML.SelectSuburusan("socSubSubUrusanB"));
    	           	    this.context.put("selectLokasiFailB",HTML.SelectLokasiFailNegeri("socLokasiFailB",Utils.parseLong(idLokasiB),"",user_negeri));
    	            	String kabinetB = getParam("txtKabinetB");
    	            	this.context.put("kabinetB",kabinetB);
    	           	    this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(idTarafB),""));
    	           	    this.context.put("selectStatusB",HTML.SelectStatusFailA("socStatusB",Utils.parseLong(idStatusB),"disabled"));
    	           	    this.context.put("tarikhDaftarB", sdf.format(now));
    	           	    
    	         	 	}
            	}
            	
            	else if("tabFailSeksyenArkib".equals(action1))
            	{
            		vm = "app/pfd/frmPendaftaranFailSeksyenArkib.jsp";
                 	this.context.put("mode", "baru");
                	context.put("readonly","");
    				context.put("disabled","");
                	context.put("readonlyNoFail","");
    			  	context.put("disabledNoFail","");
    				
    			  	
    			  	
    			  	
                	String idFail = getParam("idFail");
                	String flagsubjaket = getParam("flagsubjaket");
                	context.put("flagsubjaket",flagsubjaket);
                	
                	view_Seksyen(session);
    	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
    	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
    	         	idSeksyenC = String.valueOf(hA.get("id_seksyen"));
    	         	kodSeksyen = String.valueOf(hA.get("kod_seksyen"));
    	         	namaSeksyen = String.valueOf(hA.get("nama_seksyen"));
    	         	kodBaruSeksyen = String.valueOf(hA.get("kod_baru_seksyen"));
    	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
    	         	
    	         	context.put("idSeksyen",idSeksyenC);
    	         	context.put("seksyen",kodSeksyen + "-"+ namaSeksyen);
    	         	context.put("idNegeri",id_negeri);
                	
                	
                	this.context.put("noFailArkib", "");
	         	 	this.context.put("idUrusan", "");
	         	 	this.context.put("namaUrusan","");
	         	 	this.context.put("idSuburusan","");
	         	 	this.context.put("namaSuburusan","");
	         	 	this.context.put("idSubSuburusan","");
	         	 	this.context.put("namaSubSuburusan", "");
	         	 	this.context.put("idSubSubSuburusan","");
	         	 	this.context.put("namaSubSubSuburusan", "");
	         	 	this.context.put("tajukFailC","");
	         	 	this.context.put("noFailLama","");
	         	 	
	         	 	
                	if ("ada".equals(modeAktiviti)) { 
                		submit = getParam("command");
                			
               	    		
                		this.context.put("aktiviti1","ada");
    	         	 	this.context.put("aktiviti","");
    	         	 	this.context.put("radioCheck1", "");
    	         	 	this.context.put("radioCheck2", "checked");
    	         	 	this.context.put("readOnly","");
    	         	 	this.context.put("aktivitiValue","1");
    	         	 	
    	         	 		if (!"".equals(id_FailArkib)){
	    	         	 	
	    	         	 	FrmPendaftaranFailData.getFailArkib(id_FailArkib);
	    	         	 	paparFailArkib = FrmPendaftaranFailData.paparFailArkib();
	    	         	 	Hashtable hPaparArkib = (Hashtable)paparFailArkib.get(0);
	    	         	 	
	    	         	 	this.context.put("noFailArkib", "JKPTG-"+kodBaruSeksyen+"/"+hPaparArkib.get("NO_FAIL_ARKIB"));
	    	         	 	this.context.put("idUrusan", hPaparArkib.get("ID_URUSAN"));
	    	         	 	this.context.put("namaUrusan", hPaparArkib.get("KOD_URUSAN")+"-"+hPaparArkib.get("NAMA_URUSAN"));
	    	         	 	this.context.put("idSuburusan", hPaparArkib.get("ID_SUBURUSAN"));
	    	         	 	this.context.put("namaSuburusan", hPaparArkib.get("KOD_SUBURUSAN")+"-"+ hPaparArkib.get("NAMA_SUBURUSAN"));
	    	         	 	this.context.put("idSubSuburusan", hPaparArkib.get("ID_SUBSUBURUSAN"));
	    	         	 	this.context.put("namaSubSuburusan",hPaparArkib.get("KOD_SUBSUBURUSAN")+"-"+ hPaparArkib.get("NAMA_SUBSUBURUSAN"));
	    	         	 	this.context.put("idSubSubSuburusan", hPaparArkib.get("ID_SUBSUBSUBURUSAN"));
	    	         	 	this.context.put("namaSubSubSuburusan", hPaparArkib.get("KOD_SUBSUBSUBURUSAN")+"-"+hPaparArkib.get("NAMA_SUBSUBSUBURUSAN"));
	                    	this.context.put("tajukFailC",hPaparArkib.get("NAMA_SUBSUBSUBURUSAN"));
	    	         	 	
	    	         	 	}
    	         	 		
    	         	 		
    	         	 	
    	         	 		if(!"".equals(id_FailLama)){
    	        				
    	        				
    	         	 			failLama = getNoFailLama(id_FailLama);
	    	    				Hashtable hF = (Hashtable)failLama.get(0);
	    	    				context.put("noFailLama",hF.get("NO_FAIL"));
    	        				
    	        			}
    	         	 		
    	           	    this.context.put("selectLokasiFailC",HTML.SelectLokasiFailNegeri("socLokasiFailC",Utils.parseLong(idLokasiC),"",user_negeri));
    	            	String kabinetB = getParam("txtKabinetC");
    	            	this.context.put("kabinetC",kabinetB);    
    	           	    this.context.put("selectTarafC",HTML.SelectTarafKeselamatan("socTarafC",Utils.parseLong(idTarafC),""));
    	           	    this.context.put("selectStatusC",HTML.SelectStatusFailA("socStatusC",Utils.parseLong(idStatusC),"disabled"));
    	           	    this.context.put("tarikhDaftarC", sdf.format(now));
    	   
    	         	 	}
                		
    	         	 	else{	 	
    	         	 		
    	         	 	this.context.put("aktiviti1","tiada");
    	         	 	this.context.put("radioCheck1", "checked");
    	         	 	this.context.put("radioCheck2", "");
    	         	 	this.context.put("readOnly","");
    	         	 	this.context.put("aktivitiValue","0");
    	         	    
    	         	 	
	    	         	 	if (!"".equals(id_FailArkib)){
	    	         	 	
	    	         	 	FrmPendaftaranFailData.getFailArkib(id_FailArkib);
	    	         	 	paparFailArkib = FrmPendaftaranFailData.paparFailArkib();
	    	         	 	Hashtable hPaparArkib = (Hashtable)paparFailArkib.get(0);
	    	         	 	
	    	         	 	this.context.put("noFailArkib", "JKPTG-"+kodBaruSeksyen+"/"+hPaparArkib.get("NO_FAIL_ARKIB"));
	    	         	 	this.context.put("idUrusan", hPaparArkib.get("ID_URUSAN"));
	    	         	 	this.context.put("namaUrusan", hPaparArkib.get("KOD_URUSAN")+"-"+hPaparArkib.get("NAMA_URUSAN"));
	    	         	 	this.context.put("idSuburusan", hPaparArkib.get("ID_SUBURUSAN"));
	    	         	 	this.context.put("namaSuburusan", hPaparArkib.get("KOD_SUBURUSAN")+"-"+ hPaparArkib.get("NAMA_SUBURUSAN"));
	    	         	 	this.context.put("idSubSuburusan", hPaparArkib.get("ID_SUBSUBURUSAN"));
	    	         	 	this.context.put("namaSubSuburusan",hPaparArkib.get("KOD_SUBSUBURUSAN")+"-"+ hPaparArkib.get("NAMA_SUBSUBURUSAN"));
	    	         	 	this.context.put("idSubSubSuburusan", hPaparArkib.get("ID_SUBSUBSUBURUSAN"));
	    	         	 	this.context.put("namaSubSubSuburusan", hPaparArkib.get("KOD_SUBSUBSUBURUSAN")+"-"+hPaparArkib.get("NAMA_SUBSUBSUBURUSAN"));
	    	         	 	this.context.put("tajukFailC",hPaparArkib.get("NAMA_SUBSUBSUBURUSAN"));
	    	         		 	
	    	         	 	
	    	         	 	}
	    	         	 	
	    	         	 	if(!"".equals(id_FailLama)){
	    	    				
	    	    				
	    	    				failLama = getNoFailLama(id_FailLama);
	    	    				Hashtable hF = (Hashtable)failLama.get(0);
	    	    				context.put("noFailLama",hF.get("NO_FAIL"));
	    	    				
	    	    				
	    	    				
	    	    			}
	    	         	 	
	    	         	
    	           	    this.context.put("selectLokasiFailC",HTML.SelectLokasiFailNegeri("socLokasiFailC",Utils.parseLong(idLokasiC),"",user_negeri));
     	            	String kabinetB = getParam("txtKabinetC");
     	            	this.context.put("kabinetC",kabinetB);    
     	           	    this.context.put("selectTarafC",HTML.SelectTarafKeselamatan("socTarafC",Utils.parseLong(idTarafC),""));
     	           	    this.context.put("selectStatusC",HTML.SelectStatusFailA("socStatusC",Utils.parseLong(idStatusC),"disabled"));
     	           	    this.context.put("tarikhDaftarC", sdf.format(now));
     	      
    	         	 	}
                	
                	
	         	 		

            	}
            	else if("simpanFailSeksyenBaru".equals(action1))
            	{
                	vm = "app/pfd/frmPendaftaranFailSeksyenBaru.jsp";
                	this.context.put("mode", "papar");
                	context.put("readonly","readonly");
    				context.put("disabled","disabled");
                	context.put("readonlyNoFail","readonly");
    				context.put("readonlyTajukFail","readonly");
    			  	context.put("disabledNoFail","disabled");
    				context.put("disabledTajukFail","disabled");
    				context.put("readonlyNoTurutan","readonly");
    				context.put("disabledNoTurutan","disabled");
    				context.put("readonlyNoFailRoot","readonly");
    				context.put("disabledNoFailRoot","disabled");
    				context.put("readonlyNoJilid","readonly");
    				context.put("disabledNoJilid","disabled");
    				context.put("readonlyNoSubjaket","readonly");
    				context.put("disabledNoSubjaket","disabled");
    				context.put("readonlyK","readonly");
    			  	context.put("disabledK","disabled");
    				 
            		if (doPost.equals("true")) {
            		
            		String idFail = simpanFailBaruSeksyen(session);
            			
        
					logic.updateSubjaketNJilid(idFail);
    				
                	paparFailBaru = FrmPendaftaranFailData.getPaparFailBaru(idFail);
        			
        			Hashtable hA = (Hashtable)paparFailBaru.get(0);
        			
        			this.context.put("idFail", hA.get("idFail"));
        			this.context.put("noFail", hA.get("noFail"));
        			this.context.put("noFailArkib", hA.get("noFailArkib"));
        			this.context.put("noFailRoot", hA.get("noFailRoot"));
        			this.context.put("tajukFailB", hA.get("tajukFail"));
        			this.context.put("kabinetB", hA.get("kabinet"));
        			this.context.put("idUrusan",hA.get("idUrusan"));
        			this.context.put("idSubUrusan",hA.get("idSuburusan"));
        			this.context.put("selectSeksyenB",HTML.SelectSeksyen("socSeksyenB",Utils.parseLong(hA.get("idSeksyen").toString()),"disabled"));
             	    this.context.put("selectSubSubUrusanB",HTML.SelectSuburusan("socSubSubUrusanB",Utils.parseLong(hA.get("idSubSubUrusan").toString()),"disabled"));
             	    this.context.put("aktivitiValue", hA.get("idAktiviti"));
    	         	    if (hA.get("idAktiviti").equals("0")){
    	         	    	this.context.put("modeAktiviti", "tiada");
    	         	 		this.context.put("aktiviti1","tiada");
    	         	 		this.context.put("radioCheck1", "checked");
    	         	 		this.context.put("radioCheck2", "");
    	         	 		this.context.put("readOnly","disabled");
    	         	    }
    	         	    else
    	         	    {
    	         	    	this.context.put("modeAktiviti", "ada");
    	         	 		this.context.put("aktiviti1","ada");
    	         	 		this.context.put("radioCheck1", "");
    	         	 		this.context.put("radioCheck2", "checked");
    	         	 		this.context.put("readOnly","disabled");
    	         	 		
    	         	    }
    	         	    
             	    this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
        			this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),"disabled"));
        			this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"disabled"));
        			this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
        			
            		}
        		
            	}
            	
            	else if("simpanFailSeksyenLama".equals(action1))
            	{
            		vm = "app/pfd/frmPendaftaranFailSeksyenLama.jsp";
            		this.context.put("mode", "papar");
                	context.put("readonly","readonly");
    				context.put("disabled","disabled");
                	context.put("readonlyNoFail","readonly");
                	context.put("disabledNoFail","disabled");
                	context.put("readonlyNoFailRoot","readonly");
                	context.put("disabledNoFailRoot","disabled");
    				context.put("readonlyTajukFail","readonly");
    				context.put("disabledTajukFail","disabled");
    				context.put("Flag_failWujud", Flag_failWujud); 
    				context.put("readonlyNoTurutan","readonly");
    				context.put("disabledNoTurutan","disabled");
    				context.put("readonlyNoJilid","readonly");
    				context.put("disabledNoJilid","disabled");
    				context.put("readonlyNoSubjaket","readonly");
    				context.put("disabledNoSubjaket","disabled");
    				context.put("readonlyK","readonly");
    			  	context.put("disabledK","disabled");
    			  	context.put("readonlyDate","readonly");
    			  	context.put("disabledDate","disabled");
    				
    				
    			  	
    			  	
    			  	
    		  		if (doPost.equals("true")) {
    		  		
    		  		String idFail = simpanFailLamaSeksyen(session);
            		
            		paparFailLama = FrmPendaftaranFailData.getPaparFailLama(idFail);
        			
        			Hashtable hA = (Hashtable)paparFailLama.get(0);
        			
        			this.context.put("idFail", hA.get("idFail"));
        			this.context.put("noFail", hA.get("noFail"));
        			this.context.put("noFailRoot", hA.get("noFailRoot"));
        			this.context.put("noTurutan", hA.get("noTurutan"));
        			this.context.put("noJilid", hA.get("noJilid"));
        			this.context.put("noSubjaket", hA.get("noSubjaket"));
        			this.context.put("tajukFailB", hA.get("tajukFail"));
        			this.context.put("kabinetB", hA.get("kabinet"));
        			this.context.put("idUrusan",hA.get("idUrusan"));
        			this.context.put("idSubUrusan",hA.get("idSuburusan"));
        			this.context.put("selectSeksyenB",HTML.SelectSeksyen("socSeksyenB",Utils.parseLong(hA.get("idSeksyen").toString()),"disabled"));
             	    this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
        			this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),"disabled"));
        			this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"disabled"));
        			this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));

    		  		}
            	}
            	else if("simpanFailArkib".equals(action1))
            	{
                	vm = "app/pfd/frmPendaftaranFailSeksyenArkib.jsp";
                	this.context.put("mode", "papar");
                	context.put("readonly","readonly");
    				context.put("disabled","disabled");
    				
    				
    				Boolean failArkib = logic.noFailExists(noFailArkib,user_negeri);
    				
    				
    				
    				if (failArkib == false){
    					
    					
    				 
	            		if (doPost.equals("true")) {
	            		
	            		String idFail = simpanFailArkib(session);
	            		
	            		
	        
						logic.updateSubjaketNJilid(idFail);
	    				
	                	paparFailBaru = FrmPendaftaranFailData.getPaparFailArkib(idFail);
	        			
	        			Hashtable hA = (Hashtable)paparFailBaru.get(0);
	        			
	        			this.context.put("idFail", hA.get("idFail"));
	        			this.context.put("noFailArkib", hA.get("noFail"));
	        			this.context.put("tajukFailC", hA.get("tajukFail"));
	        			this.context.put("kabinetC", hA.get("kabinet"));
		         	 	this.context.put("idUrusan", hA.get("ID_URUSAN"));
		         	 	this.context.put("namaUrusan", hA.get("KOD_URUSAN")+"-"+hA.get("NAMA_URUSAN"));
		         	 	this.context.put("namaSuburusan", hA.get("KOD_SUBURUSAN")+"-"+ hA.get("NAMA_SUBURUSAN"));
		         	 	this.context.put("namaSubSuburusan",hA.get("KOD_SUBSUBURUSAN")+"-"+ hA.get("NAMA_SUBSUBURUSAN"));
		         	 	this.context.put("namaSubSubSuburusan", hA.get("KOD_SUBSUBSUBURUSAN")+"-"+hA.get("NAMA_SUBSUBSUBURUSAN"));
		         	 	
		         	 	FrmPendaftaranFailData.getSeksyen(hA.get("idSeksyen").toString());
    	         	 	paparSeksyen = FrmPendaftaranFailData.getListSeksyen();
    	         	 	Hashtable hB = (Hashtable) paparSeksyen.get(0);
    	         	 	
    	         	 	this.context.put("seksyen", hB.get("KOD_SEKSYEN")+"-"+hB.get("NAMA_SEKSYEN"));
    	         	 	
	             	    this.context.put("aktivitiValue", hA.get("idAktiviti"));
	    	         	    if (hA.get("idAktiviti").equals("0")){
	    	         	    	this.context.put("modeAktiviti", "tiada");
	    	         	 		this.context.put("aktiviti1","tiada");
	    	         	 		this.context.put("radioCheck1", "checked");
	    	         	 		this.context.put("radioCheck2", "");
	    	         	 		this.context.put("readOnly","disabled");
	    	         	    }
	    	         	    else
	    	         	    {
	    	         	    	this.context.put("modeAktiviti", "ada");
	    	         	 		this.context.put("aktiviti1","ada");
	    	         	 		this.context.put("radioCheck1", "");
	    	         	 		this.context.put("radioCheck2", "checked");
	    	         	 		this.context.put("readOnly","disabled");
	    	         	 		
	    	         	 		
	    	         	    }
	    	         	    
	    	         	   FrmPendaftaranFailData.getTaraf(hA.get("idTaraf").toString());
	        	         	paparTaraf = FrmPendaftaranFailData.getListTaraf();
	        	         	Hashtable hC = (Hashtable) paparTaraf.get(0);
	        	         	
	        	         	if (hC.get("KOD_TARAF_KESELAMATAN").equals("")){
	        	         		this.context.put("taraf", hC.get("TARAF_KESELAMATAN"));
	        	         	}
	        	         	else{
	        	         		this.context.put("taraf", hC.get("KOD_TARAF_KESELAMATAN")+"-"+hC.get("TARAF_KESELAMATAN"));
	        	         	}
	        	         	
	        	         	FrmPendaftaranFailData.getStatusFail(hA.get("idStatus").toString());
	    	         	 	paparStatus = FrmPendaftaranFailData.getListStatus();
	    	         	 	Hashtable hD = (Hashtable) paparStatus.get(0);
	    	         	 	
	    	         	 	this.context.put("status", hD.get("KETERANGAN"));
	    	         	 	
	    	         	 	if(!hA.get("idLokasi").equals("")){
	    	         	 	
	    	         	 	FrmPendaftaranFailData.getLokasiFail(hA.get("idLokasi").toString());
	    	         	 	paparStatus = FrmPendaftaranFailData.getListLokasi();
	    	         	 	Hashtable hE = (Hashtable) paparStatus.get(0);
	    	         	 	
	    	         	 	this.context.put("lokasi", hE.get("LOKASI_FAIL"));
	    	         	 	}
	    	         	 	else{
	    	         	 		this.context.put("lokasi", "");
	    	         	 	}
	    	         	 	this.context.put("tarikhDaftarC", hA.get("tarikhDaftar"));
	        			
	            		}
	            		
	            			
	            		}
    					else{
            			
            			this.context.put("result","yes");
            			this.context.put("mode", "baru");
                    	context.put("readonly","");
        				context.put("disabled","");
                    	context.put("readonlyNoFail","");
        			  	context.put("disabledNoFail","");
        				
        			  	
        			  	
        			  	
                    	String idFail = getParam("idFail");
                    	String flagsubjaket = getParam("flagsubjaket");
                    	context.put("flagsubjaket",flagsubjaket);
                    	
                    	view_Seksyen(session);
        	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
        	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
        	         	idSeksyenC = String.valueOf(hA.get("id_seksyen"));
        	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
        	         	context.put("idSeksyen",idSeksyenC);
        	         	context.put("idNegeri",id_negeri);
                    	
                    	tajukFailC = getParam("txtTajukFailC");
                    	this.context.put("tajukFailC",tajukFailC);
                    	this.context.put("noFailArkib", "");
    	         	 	this.context.put("idUrusan", "");
    	         	 	this.context.put("namaUrusan","");
    	         	 	this.context.put("idSuburusan","");
    	         	 	this.context.put("namaSuburusan","");
    	         	 	this.context.put("idSubSuburusan","");
    	         	 	this.context.put("namaSubSuburusan", "");
    	         	 	this.context.put("idSubSubSuburusan","");
    	         	 	this.context.put("namaSubSubSuburusan", "");
                    	  	    			
                    	if ("ada".equals(modeAktiviti)) { 
                    		submit = getParam("command");
                    			
                   	    		
                    		this.context.put("aktiviti1","ada");
        	         	 	this.context.put("aktiviti","");
        	         	 	this.context.put("radioCheck1", "");
        	         	 	this.context.put("radioCheck2", "checked");
        	         	 	this.context.put("readOnly","");
        	         	 	this.context.put("aktivitiValue","1");
        	         	 	this.context.put("selectSeksyenC",HTML.SelectSeksyen("socSeksyenC",Utils.parseLong(idSeksyenC),"disabled"));
        	         	 	
        	         	 		if (!"".equals(id_FailArkib)){
    	    	         	 	
    	    	         	 	FrmPendaftaranFailData.getFailArkib(id_FailArkib);
    	    	         	 	paparFailArkib = FrmPendaftaranFailData.paparFailArkib();
    	    	         	 	Hashtable hPaparArkib = (Hashtable)paparFailArkib.get(0);
    	    	         	 	
    	    	         	 	
    	    	         	 	this.context.put("noFailArkib", hPaparArkib.get("NO_FAIL_ARKIB"));
    	    	         	 	this.context.put("idUrusan", hPaparArkib.get("ID_URUSAN"));
    	    	         	 	this.context.put("namaUrusan", hPaparArkib.get("KOD_URUSAN")+"-"+hPaparArkib.get("NAMA_URUSAN"));
    	    	         	 	this.context.put("idSuburusan", hPaparArkib.get("ID_SUBURUSAN"));
    	    	         	 	this.context.put("namaSuburusan", hPaparArkib.get("KOD_SUBURUSAN")+"-"+ hPaparArkib.get("NAMA_SUBURUSAN"));
    	    	         	 	this.context.put("idSubSuburusan", hPaparArkib.get("ID_SUBSUBURUSAN"));
    	    	         	 	this.context.put("namaSubSuburusan",hPaparArkib.get("KOD_SUBSUBURUSAN")+"-"+ hPaparArkib.get("NAMA_SUBSUBURUSAN"));
    	    	         	 	this.context.put("idSubSubSuburusan", hPaparArkib.get("ID_SUBSUBSUBURUSAN"));
    	    	         	 	this.context.put("namaSubSubSuburusan", hPaparArkib.get("KOD_SUBSUBSUBURUSAN")+"-"+hPaparArkib.get("NAMA_SUBSUBSUBURUSAN"));
    	    	         	 	
    	    	         	 	
    	    	         	 	}
        	         	 		
        	           	    this.context.put("selectLokasiFailC",HTML.SelectLokasiFailNegeri("socLokasiFailC",Utils.parseLong(idLokasiC),"",user_negeri));
        	            	String kabinetB = getParam("txtKabinetC");
        	            	this.context.put("kabinetC",kabinetB);    
        	           	    this.context.put("selectTarafC",HTML.SelectTarafKeselamatan("socTarafC",Utils.parseLong(idTarafC),""));
        	           	    this.context.put("selectStatusC",HTML.SelectStatusFailA("socStatusC",Utils.parseLong(idStatusC),"disabled"));
        	           	    this.context.put("tarikhDaftarC", sdf.format(now));
        	   
        	         	 	}
                    		
        	         	 	else{	 	
        	         	 		
        	         	 	this.context.put("aktiviti1","tiada");
        	         	 	this.context.put("radioCheck1", "checked");
        	         	 	this.context.put("radioCheck2", "");
        	         	 	this.context.put("readOnly","");
        	         	 	this.context.put("aktivitiValue","0");
        	         	 	this.context.put("selectSeksyenC",HTML.SelectSeksyen("socSeksyenC",Utils.parseLong(idSeksyenC),"disabled"));	
        	         	    
        	         	 	
    	    	         	 	if (!"".equals(id_FailArkib)){
    	    	         	 	
    	    	         	 	FrmPendaftaranFailData.getFailArkib(id_FailArkib);
    	    	         	 	paparFailArkib = FrmPendaftaranFailData.paparFailArkib();
    	    	         	 	Hashtable hPaparArkib = (Hashtable)paparFailArkib.get(0);
    	    	         	 	
    	    	         	 	
    	    	         	 	this.context.put("noFailArkib", hPaparArkib.get("NO_FAIL_ARKIB"));
    	    	         	 	this.context.put("idUrusan", hPaparArkib.get("ID_URUSAN"));
    	    	         	 	this.context.put("namaUrusan", hPaparArkib.get("KOD_URUSAN")+"-"+hPaparArkib.get("NAMA_URUSAN"));
    	    	         	 	this.context.put("idSuburusan", hPaparArkib.get("ID_SUBURUSAN"));
    	    	         	 	this.context.put("namaSuburusan", hPaparArkib.get("KOD_SUBURUSAN")+"-"+ hPaparArkib.get("NAMA_SUBURUSAN"));
    	    	         	 	this.context.put("idSubSuburusan", hPaparArkib.get("ID_SUBSUBURUSAN"));
    	    	         	 	this.context.put("namaSubSuburusan",hPaparArkib.get("KOD_SUBSUBURUSAN")+"-"+ hPaparArkib.get("NAMA_SUBSUBURUSAN"));
    	    	         	 	this.context.put("idSubSubSuburusan", hPaparArkib.get("ID_SUBSUBSUBURUSAN"));
    	    	         	 	this.context.put("namaSubSubSuburusan", hPaparArkib.get("KOD_SUBSUBSUBURUSAN")+"-"+hPaparArkib.get("NAMA_SUBSUBSUBURUSAN"));
    	    	         	 	
    	    	         		 	
    	    	         	 	
    	    	         	 	}
        	         	 	
        	           	    this.context.put("selectLokasiFailC",HTML.SelectLokasiFailNegeri("socLokasiFailC",Utils.parseLong(idLokasiC),"",user_negeri));
         	            	String kabinetB = getParam("txtKabinetC");
         	            	this.context.put("kabinetC",kabinetB);    
         	           	    this.context.put("selectTarafC",HTML.SelectTarafKeselamatan("socTarafC",Utils.parseLong(idTarafC),""));
         	           	    this.context.put("selectStatusC",HTML.SelectStatusFailA("socStatusC",Utils.parseLong(idStatusC),"disabled"));
         	           	    this.context.put("tarikhDaftarC", sdf.format(now));
         	      
        	         	 	}
                    	
            		}
        		
            	}
            	
              	else if ("papar".equals(action1)){
               		
		    			
               		String idFail = getParam("idFail");
               		    	    	
               		paparSemuaFail = FrmPendaftaranFailData.getPaparSemuaFail(idFail);
               		Hashtable h = (Hashtable)paparSemuaFail.get(0);
               		
               		
               		    			
               		if(h.get("flag_view_file").equals("1"))
               		{
               		    				
               		    context.put("mode", "papar");
               		    context.put("readonly","readonly");
               		    context.put("disabled","disabled");
                      	context.put("readonlyNoFail","readonly");
               		    context.put("disabledNoFail","disabled");
                      	context.put("readonlyNoFailRoot","readonly");
               		    context.put("disabledNoFailRoot","disabled");
               		    context.put("readonlyTajukFail","readonly");
               		    context.put("disabledTajukFail","disabled");
        				context.put("readonlyNoTurutan","readonly");
        				context.put("disabledNoTurutan","disabled");
        				context.put("readonlyNoJilid","readonly");
        				context.put("disabledNoJilid","disabled");
        				context.put("readonlyNoSubjaket","readonly");
        				context.put("disabledNoSubjaket","disabled");
               		    context.put("readonlyK","readonly");
               		    context.put("disabledK","disabled");
               		    context.put("readonlyDate","readonly");
            		    context.put("disabledDate","disabled");
               		    				
               		    vm = "app/pfd/frmPendaftaranFailSeksyenLama.jsp";
               		            		
               		    paparFailLama = FrmPendaftaranFailData.getPaparFailLama(idFail);
               		    
               		  
               		        			
               		    Hashtable hA = (Hashtable)paparFailLama.get(0);
               		        			
               		    this.context.put("idFail", hA.get("idFail"));
               		    this.context.put("noFail", hA.get("noFail"));
               		    this.context.put("noFailRoot", hA.get("noFailRoot"));
	               		this.context.put("noTurutan", hA.get("noTurutan"));
	         			this.context.put("noJilid", hA.get("noJilid"));
	         			this.context.put("noSubjaket", hA.get("noSubjaket"));
               		    this.context.put("tajukFailB", hA.get("tajukFail"));
               		    this.context.put("kabinetB", hA.get("kabinet"));
               		    this.context.put("idUrusan",hA.get("idUrusan"));
               		    this.context.put("idSubUrusan",hA.get("idSuburusan"));
               		    
               		    FrmPendaftaranFailData.getUrusan();
               		    listUrusan = FrmPendaftaranFailData.getListUrusan();
               		    this.context.put("SenaraiUrusan",listUrusan);

          		 		FrmPendaftaranFailData.getSubUrusan(hA.get("idUrusan").toString());
          		 		listSubUrusan = FrmPendaftaranFailData.getListSubUrusan();
          		 		this.context.put("SenaraiSubUrusan", listSubUrusan);
               		    
               		    this.context.put("selectSeksyenB",HTML.SelectSeksyen("socSeksyenB",Utils.parseLong(hA.get("idSeksyen").toString()),"disabled"));
            		    this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
               		    this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),"disabled"));
               		    this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"disabled"));
               		    this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
               		    				
               		}
               		    			
               		else if(h.get("flag_view_file").equals("2")){
               		    				
               		    context.put("mode", "papar");
               		    context.put("readonly","readonly");
               		    context.put("disabled","disabled");
               		    context.put("readonlyNoFail","readonly");
              		    context.put("disabledNoFail","disabled");
               		    context.put("readonlyNoFailRoot","readonly");
              		    context.put("disabledNoFailRoot","disabled");
               		    context.put("readonlyTajukFail","readonly");
               		    context.put("disabledTajukFail","disabled");
        				context.put("readonlyNoTurutan","readonly");
        				context.put("disabledNoTurutan","disabled");
        				context.put("readonlyNoJilid","readonly");
        				context.put("disabledNoJilid","disabled");
        				context.put("readonlyNoSubjaket","readonly");
        				context.put("disabledNoSubjaket","disabled");
               		    context.put("readonlyK","readonly");
               		    context.put("disabledK","disabled");
               		    context.put("readonlyDate","readonly");
            		    context.put("disabledDate","disabled");
               		    				
               		    String idFailA = getParam("idFail");
               		    vm = "app/pfd/frmPendaftaranFailNegeriLama.jsp";
               		            		
               		    paparFailLama = FrmPendaftaranFailData.getPaparFailLama(idFail);
               		        			
               		    Hashtable hA = (Hashtable)paparFailLama.get(0);
               		        			
               		    this.context.put("idFail", hA.get("idFail"));
               		    this.context.put("noFail", hA.get("noFail"));
               		    this.context.put("noFailRoot", hA.get("noFailRoot"));
	               		this.context.put("noTurutan", hA.get("noTurutan"));
	         			this.context.put("noJilid", hA.get("noJilid"));
	         			this.context.put("noSubjaket", hA.get("noSubjaket"));
	               		this.context.put("tajukFailB", hA.get("tajukFail"));
               		    this.context.put("kabinetB", hA.get("kabinet"));
               		 	this.context.put("idUrusan",hA.get("idUrusan"));
               		 	this.context.put("idSubUrusan",hA.get("idSuburusan"));
               		 	
               		 	FrmPendaftaranFailData.getUrusan();
            		    listUrusan = FrmPendaftaranFailData.getListUrusan();
            		    this.context.put("SenaraiUrusan",listUrusan);

       		 			FrmPendaftaranFailData.getSubUrusan(hA.get("idUrusan").toString());
       		 			listSubUrusan = FrmPendaftaranFailData.getListSubUrusan();
       		 			this.context.put("SenaraiSubUrusan", listSubUrusan);
               		 	
               		    this.context.put("selectNegeriB",HTML.SelectNegeriA("socNegeriB",Utils.parseLong(hA.get("idNegeri").toString()),"disabled"));
               		    this.context.put("selectDaerahB",HTML.SelectDaerah("socDaerahB",Utils.parseLong(hA.get("idDaerah").toString()),"disabled"));
            		    this.context.put("aktivitiValue", hA.get("idAktiviti"));  
               		    this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
               		    this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),"disabled"));
               		    this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"disabled"));
 	        			this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
               		}
               		
               		else if(h.get("flag_view_file").equals("3")){
             
               			context.put("mode", "papar");
               			context.put("readonly","readonly");
           				context.put("disabled","disabled");
           				context.put("readonlyNoFail","readonly");
           				context.put("readonlyTajukFail","readonly");
           				context.put("disabledNoFail","disabled");
           				context.put("disabledTajukFail","disabled");
        				context.put("readonlyNoTurutan","readonly");
        				context.put("disabledNoTurutan","disabled");
        				context.put("readonlyNoJilid","readonly");
        				context.put("disabledNoJilid","disabled");
        				context.put("readonlyNoSubjaket","readonly");
        				context.put("disabledNoSubjaket","disabled");
           				context.put("readonlyK","readonly");
            		  	context.put("disabledK","disabled");
               		    context.put("readonlyDate","readonly");
            		    context.put("disabledDate","disabled");
               		    				
            		  	String idFailA = getParam("idFail");
               		    vm = "app/pfd/frmPendaftaranFailSeksyenBaru.jsp";
               		                	
               		    paparFailBaru = FrmPendaftaranFailData.getPaparFailBaru(idFail);
               		        			
               		    Hashtable hA = (Hashtable)paparFailBaru.get(0);
               		        			
               		    this.context.put("idFail", hA.get("idFail"));
               		    this.context.put("noFail", hA.get("noFail"));
               		    this.context.put("noTurutan", hA.get("noTurutan"));
	         			this.context.put("noJilid", hA.get("noJilid"));
	         			this.context.put("noSubjaket", hA.get("noSubjaket"));
               		    this.context.put("tajukFailB", hA.get("tajukFail"));
               		    this.context.put("kabinetB", hA.get("kabinet"));
               		    this.context.put("aktivitiValue", hA.get("idAktiviti"));
               				if (hA.get("idAktiviti").equals("0")){
               					this.context.put("modeAktiviti", "tiada");
	               				this.context.put("aktiviti1","tiada");
	               				this.context.put("radioCheck1", "checked");
	               				this.context.put("radioCheck2", "");
	               				this.context.put("readOnly","disabled");
               				}
               		  	    else
               		  	    {
               		  	    	this.context.put("modeAktiviti", "ada");
               		  	    	this.context.put("aktiviti1","ada");
               		  	    	this.context.put("radioCheck1", "");
               		  	    	this.context.put("radioCheck2", "checked");
               		  	    	this.context.put("readOnly","disabled");
               		  	    	this.context.put("aktiviti", hA.get("aktiviti"));
               		  	        	 		
               		  	    }
               				this.context.put("idUrusan",hA.get("idUrusan"));
                			this.context.put("idSubUrusan",hA.get("idSuburusan"));
               				this.context.put("selectSeksyenB",HTML.SelectSeksyen("socSeksyenB",Utils.parseLong(hA.get("idSeksyen").toString()),"disabled"));
               				this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
               		    	this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),"disabled"));
               		    	this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"disabled"));
               		    	this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
               			
               			}
               		else if(h.get("flag_view_file").equals("5")){
               			context.put("mode", "papar");
               			context.put("readonly","readonly");
           				context.put("disabled","disabled");
           				context.put("readonlyNoFail","readonly");
          				context.put("disabledNoFail","disable");
           				vm = "app/pfd/frmPendaftaranFailSeksyenArkib.jsp";
               			paparFailBaru = FrmPendaftaranFailData.getPaparFailArkib(idFail);
            			
            			Hashtable hA = (Hashtable)paparFailBaru.get(0);
            			
            			this.context.put("idFail", hA.get("idFail"));
            			this.context.put("noFailArkib", hA.get("noFail"));
            			this.context.put("noFailLama", hA.get("noFailLama"));
            			this.context.put("tajukFailC", hA.get("tajukFail"));
            			this.context.put("kabinetC", hA.get("kabinet"));
    	         	 	this.context.put("idUrusan", hA.get("ID_URUSAN"));
    	         	 	this.context.put("namaUrusan", hA.get("KOD_URUSAN")+"-"+hA.get("NAMA_URUSAN"));
    	         	 	this.context.put("namaSuburusan", hA.get("KOD_SUBURUSAN")+"-"+ hA.get("NAMA_SUBURUSAN"));
    	         	 	this.context.put("namaSubSuburusan",hA.get("KOD_SUBSUBURUSAN")+"-"+ hA.get("NAMA_SUBSUBURUSAN"));
    	         	 	this.context.put("namaSubSubSuburusan", hA.get("KOD_SUBSUBSUBURUSAN")+"-"+hA.get("NAMA_SUBSUBSUBURUSAN"));
    	         	 	
    	         	 	FrmPendaftaranFailData.getSeksyen(hA.get("idSeksyen").toString());
    	         	 	paparSeksyen = FrmPendaftaranFailData.getListSeksyen();
    	         	 	Hashtable hB = (Hashtable) paparSeksyen.get(0);
    	         	 	
    	         	 	this.context.put("seksyen", hB.get("KOD_SEKSYEN")+"-"+hB.get("NAMA_SEKSYEN"));
    	         	 	
                 	    this.context.put("aktivitiValue", hA.get("idAktiviti"));
        	         	    if (hA.get("idAktiviti").equals("0")){
        	         	    	this.context.put("modeAktiviti", "tiada");
        	         	 		this.context.put("aktiviti1","tiada");
        	         	 		this.context.put("radioCheck1", "checked");
        	         	 		this.context.put("radioCheck2", "");
        	         	 		this.context.put("readOnly","disabled");
        	         	    }
        	         	    else
        	         	    {
        	         	    	this.context.put("modeAktiviti", "ada");
        	         	 		this.context.put("aktiviti1","ada");
        	         	 		this.context.put("radioCheck1", "");
        	         	 		this.context.put("radioCheck2", "checked");
        	         	 		this.context.put("readOnly","disabled");
        	         	 		
        	         	 		
        	         	    }
        	         	
        	         	FrmPendaftaranFailData.getTaraf(hA.get("idTaraf").toString());
        	         	paparTaraf = FrmPendaftaranFailData.getListTaraf();
        	         	Hashtable hC = (Hashtable) paparTaraf.get(0);
        	         	
        	         	if (hC.get("KOD_TARAF_KESELAMATAN").equals("")){
        	         		this.context.put("taraf", hC.get("TARAF_KESELAMATAN"));
        	         	}
        	         	else{
        	         		this.context.put("taraf", hC.get("KOD_TARAF_KESELAMATAN")+"-"+hC.get("TARAF_KESELAMATAN"));
        	         	}
        	         	
        	         	FrmPendaftaranFailData.getStatusFail(hA.get("idStatus").toString());
    	         	 	paparStatus = FrmPendaftaranFailData.getListStatus();
    	         	 	Hashtable hD = (Hashtable) paparStatus.get(0);
    	         	 	
    	         	 	this.context.put("status", hD.get("KETERANGAN"));
    	         	 	
    	         	 	if(!hA.get("idLokasi").equals("")){
    	         	 	
    	         	 	FrmPendaftaranFailData.getLokasiFail(hA.get("idLokasi").toString());
    	         	 	paparStatus = FrmPendaftaranFailData.getListLokasi();
    	         	 	Hashtable hE = (Hashtable) paparStatus.get(0);
    	         	 	
    	         	 	this.context.put("lokasi", hE.get("LOKASI_FAIL"));
    	         	 	}
    	         	 	else{
    	         	 		this.context.put("lokasi", "");
    	         	 	}
    	         	 	
            			this.context.put("tarikhDaftarC", hA.get("tarikhDaftar"));
            			
               		}
               		    else{
               			
               		      	context.put("mode", "papar");
               		      	context.put("readonly","readonly");
               		      	context.put("disabled","disabled");
               		      	context.put("readonlyNoFail","readonly");
               		      	context.put("readonlyTajukFail","readonly");
               		      	context.put("disabledNoFail","disabled");
               		      	context.put("disabledTajukFail","disabled");
            				context.put("readonlyNoTurutan","readonly");
            				context.put("disabledNoTurutan","disabled");
            				context.put("readonlyNoJilid","readonly");
            				context.put("disabledNoJilid","disabled");
            				context.put("readonlyNoSubjaket","readonly");
            				context.put("disabledNoSubjaket","disabled");
               		      	context.put("readonlyK","readonly");
            			  	context.put("disabledK","disabled");
                   		    context.put("readonlyDate","readonly");
                		    context.put("disabledDate","disabled");
               		    				
               		   		vm = "app/pfd/frmPendaftaranFailNegeriBaru.jsp";
               		                	
               		   		String idFailA = getParam("idFail");
               		   		paparFailBaru = FrmPendaftaranFailData.getPaparFailBaru(idFail);
               		        			
               		   		Hashtable hA = (Hashtable)paparFailBaru.get(0);
               		        			
               		   		this.context.put("idFail", hA.get("idFail"));
               		   		this.context.put("noFail", hA.get("noFail"));
	               		   	this.context.put("noTurutan", hA.get("noTurutan"));
	            			this.context.put("noJilid", hA.get("noJilid"));
	            			this.context.put("noSubjaket", hA.get("noSubjaket"));
               		   		this.context.put("tajukFailB", hA.get("tajukFail"));
               		   		this.context.put("kabinetB", hA.get("kabinet"));
               		   		this.context.put("idUrusan",hA.get("idUrusan"));
               		   		this.context.put("idSubUrusan",hA.get("idSuburusan"));
               		   		
               		   	 	FrmPendaftaranFailData.getUrusan();
                		    listUrusan = FrmPendaftaranFailData.getListUrusan();
                		    this.context.put("SenaraiUrusan",listUrusan);

           		 			FrmPendaftaranFailData.getSubUrusan(hA.get("idUrusan").toString());
           		 			listSubUrusan = FrmPendaftaranFailData.getListSubUrusan();
           		 			this.context.put("SenaraiSubUrusan", listSubUrusan);
               		   		
               		   		this.context.put("selectNegeriB",HTML.SelectNegeriA("socNegeriB",Utils.parseLong(hA.get("idNegeri").toString()),"disabled"));
               		   		this.context.put("selectDaerahB",HTML.SelectDaerah("socDaerahB",Utils.parseLong(hA.get("idDaerah").toString()),"disabled"));
               		   		this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
               		   		this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),"disabled"));
               		   		this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"disabled"));
               		   		this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
		               			
               		    }
               	}
            	
             
      	    else if ("kemaskiniFailSeksyenLama".equals(action1)){
      	    	
      	    	String idFail = getParam("idFail");
      	    	
      	    	vm = "app/pfd/frmPendaftaranFailSeksyenLama.jsp";
            	context.put("mode", "kemaskini");
            	context.put("readonly","");
				context.put("disabled","");
            	context.put("readonlyNoFail","");
			  	context.put("disabledNoFail","");
            	context.put("readonlyNoFailRoot","");
			  	context.put("disabledNoFailRoot","");
				context.put("readonlyTajukFail","");
				context.put("disabledTajukFail","");
				context.put("readonlyNoTurutan","");
				context.put("disabledNoTurutan","");
				context.put("readonlyNoJilid","");
				context.put("disabledNoJilid","");
				context.put("readonlyNoSubjaket","");
				context.put("disabledNoSubjaket","");
				context.put("readonlyK","");
			  	context.put("disabledK","");
       		    context.put("readonlyDate","");
    		    context.put("disabledDate","");
        		
        		paparFailLama = FrmPendaftaranFailData.getPaparFailLama(idFail);
    			
    			Hashtable hA = (Hashtable)paparFailLama.get(0);
    			this.context.put("kabinetB", hA.get("kabinet"));
    			this.context.put("idFail", hA.get("idFail"));
    			this.context.put("noFail", hA.get("noFail"));
    			this.context.put("noFailRoot", hA.get("noFailRoot"));
    			this.context.put("noTurutan", hA.get("noTurutan"));
    			this.context.put("noJilid", hA.get("noJilid"));
    			this.context.put("noSubjaket", hA.get("noSubjaket"));
    			this.context.put("tajukFailB", hA.get("tajukFail"));
    			this.context.put("idUrusan",hA.get("idUrusan"));
    			this.context.put("idSubUrusan",hA.get("idSuburusan"));
    			this.context.put("selectSeksyenB",HTML.SelectSeksyen("socSeksyenB",Utils.parseLong(hA.get("idSeksyen").toString()),"disabled"));
    			this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
    			this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),""));
    			this.context.put("selectLokasiFailB",HTML.SelectLokasiFailNegeri("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"",user_negeri));
    			this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
      	    	
      	    }
            	
      	    else if ("kemaskiniFailSeksyenBaru".equals(action1)){
				
      	    String idFail = getParam("idFail");
				
      	    vm = "app/pfd/frmPendaftaranFailSeksyenBaru.jsp";
            	this.context.put("mode", "kemaskini");
            	context.put("readonly","disabled");
				context.put("disabled","disabled");
            	context.put("readonlyNoFail","readonly");
				context.put("readonlyTajukFail","");
			  	context.put("disabledNoFail","disabled");
				context.put("disabledTajukFail","");
				context.put("readonlyK","");
			  	context.put("disabledK","");
				
            	
            	paparFailBaru = FrmPendaftaranFailData.getPaparFailBaru(idFail);
    			
    			Hashtable hA = (Hashtable)paparFailBaru.get(0);
    			this.context.put("kabinetB", hA.get("kabinet"));
    			this.context.put("idFail", hA.get("idFail"));
    			this.context.put("noFail", hA.get("noFail"));
    			this.context.put("tajukFailB", hA.get("tajukFail"));
    			this.context.put("idUrusan",hA.get("idUrusan"));
    			this.context.put("idSubUrusan",hA.get("idSuburusan"));
    			this.context.put("selectSeksyenB",HTML.SelectSeksyen("socSeksyenB",Utils.parseLong(hA.get("idSeksyen").toString()),"disabled"));
    			this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
    			this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),""));
    			this.context.put("selectLokasiFailB",HTML.SelectLokasiFailNegeri("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"", user_negeri));
    			this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
   	
      	    	
      	    }
      	  else if ("kemaskiniFailArkib".equals(action1)){
				
        	    String idFail = getParam("idFail");
        	    
        	    
        	    vm = "app/pfd/frmPendaftaranFailSeksyenArkib.jsp";
              	this.context.put("mode", "kemaskini");
              	context.put("readonly","");
  				context.put("disabled","");
  				context.put("readonlyNoFail","readonly");
  				context.put("disabledNoFail","disable");
              	
              	paparFailBaru = FrmPendaftaranFailData.getPaparFailArkib(idFail);
      			
      			Hashtable hA = (Hashtable)paparFailBaru.get(0);
      			this.context.put("idFail", hA.get("idFail"));
    			this.context.put("noFailArkib", hA.get("noFail"));
    			this.context.put("noFailLama",hA.get("noFailLama"));
    			this.context.put("tajukFailC", hA.get("tajukFail"));
    			this.context.put("kabinetC", hA.get("kabinet"));
         	 	this.context.put("idUrusan", hA.get("ID_URUSAN"));
         	 	this.context.put("namaUrusan", hA.get("KOD_URUSAN")+"-"+hA.get("NAMA_URUSAN"));
         	 	this.context.put("namaSuburusan", hA.get("KOD_SUBURUSAN")+"-"+ hA.get("NAMA_SUBURUSAN"));
         	 	this.context.put("namaSubSuburusan",hA.get("KOD_SUBSUBURUSAN")+"-"+ hA.get("NAMA_SUBSUBURUSAN"));
         	 	this.context.put("namaSubSubSuburusan", hA.get("KOD_SUBSUBSUBURUSAN")+"-"+hA.get("NAMA_SUBSUBSUBURUSAN"));
         	 	
         	 	FrmPendaftaranFailData.getSeksyen(hA.get("idSeksyen").toString());
         	 	paparSeksyen = FrmPendaftaranFailData.getListSeksyen();
         	 	Hashtable hB = (Hashtable) paparSeksyen.get(0);
         	 	
         	 	this.context.put("seksyen", hB.get("KOD_SEKSYEN")+"-"+hB.get("NAMA_SEKSYEN"));
         	 	
      			
         	 	this.context.put("selectSeksyenC",HTML.SelectSeksyen("socSeksyenC",Utils.parseLong(hA.get("idSeksyen").toString()),"disabled"));
      			this.context.put("selectTarafC",HTML.SelectTarafKeselamatan("socTarafC",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
     			this.context.put("selectStatusC",HTML.SelectStatusFailB("socStatusC",Utils.parseLong(hA.get("idStatus").toString()),null));
     			this.context.put("selectLokasiFailC",HTML.SelectLokasiFail("socLokasiFailC",Utils.parseLong(hA.get("idLokasi").toString()),null));
     			this.context.put("tarikhDaftarC", hA.get("tarikhDaftar"));
     			
     			
     			
     			if(!"".equals(id_FailLama)){
    				
    				
     	 			failLama = getNoFailLama(id_FailLama);
    				Hashtable hF = (Hashtable)failLama.get(0);
    				context.put("noFailLama",hF.get("NO_FAIL"));
    				
    			}
     	
        	    	
        }  	
      	  else if ("updateFailSeksyenLama".equals(action1)){
  	    	
    		  	updateFailLama(session);
    		  
    		  	String idFail = getParam("idFail");  
	    	
		    	vm = "app/pfd/frmPendaftaranFailSeksyenLama.jsp";
	  			this.context.put("mode", "papar");
	        	context.put("readonly","readonly");
				context.put("disabled","disabled");
				context.put("readonlyNoFail","readonly");
			  	context.put("disabledNoFail","disabled");
				context.put("readonlyNoFailRoot","readonly");
			  	context.put("disabledNoFailRoot","disabled");
			  	context.put("readonlyNoTurutan","readonly");
				context.put("disabledNoTurutan","disabled");
				context.put("readonlyNoJilid","readonly");
				context.put("disabledNoJilid","disabled");
				context.put("readonlyNoSubjaket","readonly");
				context.put("disabledNoSubjaket","disabled");
				context.put("readonlyTajukFail","readonly");
				context.put("disabledTajukFail","disabled");
				context.put("readonlyK","readonly");
			  	context.put("disabledK","disabled");
			  	context.put("readonlyDate","readonly");
			  	context.put("disabledDate","disabled");
		    	
				
		
	      		paparFailLama = FrmPendaftaranFailData.getPaparFailLama(idFail);
	  			
	  			Hashtable hA = (Hashtable)paparFailLama.get(0);
	  			
	  			this.context.put("idFail", hA.get("idFail"));
	  			this.context.put("noFail", hA.get("noFail"));
	  			this.context.put("noFailRoot", hA.get("noFailRoot"));
	  			this.context.put("noTurutan", hA.get("noTurutan"));
    			this.context.put("noJilid", hA.get("noJilid"));
    			this.context.put("noSubjaket", hA.get("noSubjaket"));
	  			this.context.put("tajukFailB", hA.get("tajukFail"));
	  			this.context.put("kabinetB", hA.get("kabinet"));
	   			this.context.put("tajukFailB", hA.get("tajukFail"));
	   			this.context.put("idUrusan",hA.get("idUrusan"));
    			this.context.put("idSubUrusan",hA.get("idSuburusan"));
	  			this.context.put("selectSeksyenB",HTML.SelectSeksyen("socSeksyenB",Utils.parseLong(hA.get("idSeksyen").toString()),"disabled"));
	  			this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
	  			this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),"disabled"));
	  			this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"disabled"));
	  			this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
  	    }
          	
    	    else if ("updateFailSeksyenBaru".equals(action1)){
	    	
	    	String idFail = getParam("idFail");  
	    	
	    	vm = "app/pfd/frmPendaftaranFailSeksyenBaru.jsp";
			this.context.put("mode", "paparSemua");
			context.put("readonly","readonly");
			context.put("disabled","disabled");
			context.put("readonlyNoFail","readonly");
			context.put("readonlyTajukFail","readonly");
		  	context.put("disabledNoFail","disabled");
			context.put("disabledTajukFail","disabled");
			context.put("readonlyK","readonly");
		  	context.put("disabledK","disabled");

			updateFailBaru(session);

			paparFailBaru = FrmPendaftaranFailData.getPaparFailBaru(idFail);
			
			Hashtable hA = (Hashtable)paparFailBaru.get(0);
			
			this.context.put("idFail", hA.get("idFail"));
			this.context.put("noFail", hA.get("noFail"));
			this.context.put("tajukFailB", hA.get("tajukFail"));
			this.context.put("kabinetB", hA.get("kabinet"));
			this.context.put("idUrusan",hA.get("idUrusan"));
			this.context.put("idSubUrusan",hA.get("idSuburusan"));
			this.context.put("selectSeksyenB",HTML.SelectSeksyen("socSeksyenB",Utils.parseLong(hA.get("idSeksyen").toString()),"disabled"));
			this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
			this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),"disabled"));
			this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"disabled"));
			this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
	    }
        
    	    else if ("updateFailSeksyenArkib".equals(action1)){
    	    	
    	    	String idFail = getParam("idFail");  
    	    	
    	    	vm = "app/pfd/frmPendaftaranFailSeksyenArkib.jsp";
    			this.context.put("mode", "papar");
    			context.put("readonly","readonly");
    			context.put("disabled","disabled");
    			context.put("readonlyNoFail","readonly");
    		  	context.put("disabledNoFail","disabled");
    			

    		  	updateFailArkib(session);

    			paparFailArkib = FrmPendaftaranFailData.getPaparFailArkib(idFail);
    			
    			Hashtable hA = (Hashtable)paparFailArkib.get(0);
    			
    			this.context.put("idFail", hA.get("idFail"));
    			this.context.put("noFailArkib", hA.get("noFail"));
    			this.context.put("noFailLama",hA.get("noFailLama"));
    			this.context.put("tajukFailC", hA.get("tajukFail"));
    			this.context.put("kabinetC", hA.get("kabinet"));
         	 	this.context.put("idUrusan", hA.get("ID_URUSAN"));
         	 	this.context.put("namaUrusan", hA.get("KOD_URUSAN")+"-"+hA.get("NAMA_URUSAN"));
         	 	this.context.put("namaSuburusan", hA.get("KOD_SUBURUSAN")+"-"+ hA.get("NAMA_SUBURUSAN"));
         	 	this.context.put("namaSubSuburusan",hA.get("KOD_SUBSUBURUSAN")+"-"+ hA.get("NAMA_SUBSUBURUSAN"));
         	 	this.context.put("namaSubSubSuburusan", hA.get("KOD_SUBSUBSUBURUSAN")+"-"+hA.get("NAMA_SUBSUBSUBURUSAN"));
    			this.context.put("selectSeksyenC",HTML.SelectSeksyen("socSeksyenC",Utils.parseLong(hA.get("idSeksyen").toString()),"disabled"));
    			this.context.put("aktivitiValue", hA.get("idAktiviti"));
         	    if (hA.get("idAktiviti").equals("0")){
         	    	this.context.put("modeAktiviti", "tiada");
         	 		this.context.put("aktiviti1","tiada");
         	 		this.context.put("radioCheck1", "checked");
         	 		this.context.put("radioCheck2", "");
         	 		this.context.put("readOnly","disabled");
         	    }
         	    else
         	    {
         	    	this.context.put("modeAktiviti", "ada");
         	 		this.context.put("aktiviti1","ada");
         	 		this.context.put("radioCheck1", "");
         	 		this.context.put("radioCheck2", "checked");
         	 		this.context.put("readOnly","disabled");
         	 		
         	 		
         	    }
         	    
         	   FrmPendaftaranFailData.getSeksyen(hA.get("idSeksyen").toString());
        	   paparSeksyen = FrmPendaftaranFailData.getListSeksyen();
        	   Hashtable hB = (Hashtable) paparSeksyen.get(0);
        	 	
        	 	this.context.put("seksyen", hB.get("KOD_SEKSYEN")+"-"+hB.get("NAMA_SEKSYEN"));
        	 	
         	    
         	   FrmPendaftaranFailData.getTaraf(hA.get("idTaraf").toString());
	         	paparTaraf = FrmPendaftaranFailData.getListTaraf();
	         	Hashtable hC = (Hashtable) paparTaraf.get(0);
	         	
	         	if (hC.get("KOD_TARAF_KESELAMATAN").equals("")){
	         		this.context.put("taraf", hC.get("TARAF_KESELAMATAN"));
	         	}
	         	else{
	         		this.context.put("taraf", hC.get("KOD_TARAF_KESELAMATAN")+"-"+hC.get("TARAF_KESELAMATAN"));
	         	}
	         	
	         	FrmPendaftaranFailData.getStatusFail(hA.get("idStatus").toString());
        	 	paparStatus = FrmPendaftaranFailData.getListStatus();
        	 	Hashtable hD = (Hashtable) paparStatus.get(0);
        	 	
        	 	this.context.put("status", hD.get("KETERANGAN"));
        	 	
        	 	if(!hA.get("idLokasi").equals("")){
        	 	
        	 	FrmPendaftaranFailData.getLokasiFail(hA.get("idLokasi").toString());
        	 	paparStatus = FrmPendaftaranFailData.getListLokasi();
        	 	Hashtable hE = (Hashtable) paparStatus.get(0);
        	 	
        	 	this.context.put("lokasi", hE.get("LOKASI_FAIL"));
        	 	}
        	 	else{
        	 		this.context.put("lokasi", "");
        	 	}
        	 	this.context.put("tarikhDaftarC", hA.get("tarikhDaftar"));
    	    }
            	
    	    else if ("hapus".equals(action1)){
       			
		    	String idFail = getParam("idFail");  
		    	context.put("hapus", "true");
		    	
		    	logic.hapus(idFail);
		    	
		    	if("16".equalsIgnoreCase(user_negeri)){
		    		vm = "app/pfd/frmPendaftaranFailSeksyenBaru.jsp";
	              	this.context.put("mode", "paparUpdate");
	 			  	action1 = "tabFailSeksyenBaru";
 			  	
 			  	
		    	}
		    	else{
		    		vm = "app/pfd/frmPendaftaranFailNegeriBaru.jsp";
		    		this.context.put("mode", "paparUpdate");    
		    		action1 = "tabFailNegeriBaru";
		    	}

      	 	}	
            	
            	else{
    	    		
            		String user_name = (String)session.getAttribute("_portal_username");
    	    		user_id = (String)session.getAttribute("_ekptg_user_id");
    	    		user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
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
    	    		context.put("user_negeri", user_negeri);
    	    	
    	    		vm = "app/pfd/frmDaftarFail.jsp";

    	    		this.context.put("JumlahFail",FrmDaftarFailData.totalFail(session));
      	    	
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
    				if (!"".equals(getParam("txtTarikhDaftar")))
    					tarikhDaftar = getParam("txtTarikhDaftar");
    				
    				FrmDaftarFailData.setCarianFail(noFail,noFailLama,tajukFail,negeri,seksyen,status,tarikhDaftar, user_id, myrole, user_negeri, id_seksyen); 
    	 	    	list = FrmDaftarFailData.getList();
    	 	    	this.context.put("SenaraiFail", list);
    	 	    	this.context.put("selectNegeriD",HTML.SelectNegeriA("socNegeriD",Utils.parseLong(negeri),""));
    		    	this.context.put("selectSeksyenD",HTML.SelectSeksyen("socSeksyenD",Utils.parseLong(seksyen),""));
    		    	this.context.put("selectStatusD",HTML.SelectStatusFailB("socStatusD",Utils.parseLong(status),""));
    		    	this.context.put("txtNoFailLama", noFailLama);
    		    	this.context.put("txtNoFail", noFail);
    		    	this.context.put("txtTajukFail", tajukFail);
    		    	this.context.put("txtTarikhDaftar", tarikhDaftar);
    		    	this.context.put("myrole", myrole);

    		    	
    		    	setupPage(session,action1,list);
            		
            	}
            }
            
 //------------------------------------- fail negeri -----------------------------------------------------------------------           
            	
            else if(!"16".equalsIgnoreCase(user_negeri))
            {
            	
            	if("tabFailNegeriLama".equals(action1))
            	{
            		
            		vm = "app/pfd/frmPendaftaranFailNegeriLama.jsp";
            		this.context.put("mode", "baru");
                	context.put("readonly","");
    				context.put("disabled","");
                	context.put("readonlyNoFail","");
                	context.put("disabledNoFail","");
                	context.put("readonlyNoFailRoot","");
    			  	context.put("disabledNoFailRoot","");
    			  	context.put("readonlyNoTurutan","");
    				context.put("disabledNoTurutan","");
    				context.put("readonlyNoJilid","");
    				context.put("disabledNoJilid","");
    				context.put("readonlyNoSubjaket","");
    				context.put("disabledNoSubjaket","");
    				context.put("readonlyTajukFail","");
    				context.put("disabledTajukFail","");
    				context.put("Flag_failWujud", Flag_failWujud); 
    				
                	String idFail = getParam("idFail");
                	String flagsubjaket = getParam("flagsubjaket");
                	context.put("flagsubjaket",flagsubjaket);
    				
                	view_Seksyen(session);
    	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
    	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
    	         	String id_seksyen = String.valueOf(hA.get("id_seksyen"));
    	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
    	         	context.put("idSeksyen",(hA.get("id_seksyen")));
    	         	context.put("idNegeri",(hA.get("id_negeri")));
    				
                	noFail = getParam("noFail");
                	this.context.put("noFail",noFail);
                	String noFailRoot = getParam("noFailRoot");
                	this.context.put("noFailRoot",noFailRoot);
                	String noTurutan = getParam("noTurutan");
                	this.context.put("noTurutan","0");
                	String noJilid = getParam("noJilid");
                	this.context.put("noJilid","1");
                	String noSubjaket = getParam("noSubjaket");
                	this.context.put("noSubjaket","0");
                
                	if ("ada".equals(modeAktiviti)) {
                		String socNegeriB = getParam("socNegeriB");
           	    		String socDaerahB = getParam("socDaerahB");
           	    		String socDaerah = getParam("socDaerahB");
           	    		String socSubU = getParam("socSuburusanB");
             	 		this.context.put("aktiviti1","ada");
             	 		this.context.put("aktiviti","");
             	 		this.context.put("radioCheck1", "");
             	 		this.context.put("radioCheck2", "checked");
             	 		this.context.put("readOnly","");
             	 		this.context.put("aktivitiValue","1");
             	 		
             	 		tajukFailB = getParam("txtTajukFailB");
                    	this.context.put("tajukFailB",tajukFailB);
                    	this.context.put("selectNegeriB",HTML.SelectNegeriA("socNegeriB",Utils.parseLong(id_negeri),null,"onChange=\"doChangeNegeriB();\" "));
                    	this.context.put("selectDaerahB",HTML.SelectDaerahByIdNegeri(id_negeri,"socDaerahB",null,null));

    	           	    	
                    	this.context.put("idUrusan","");
                    	this.context.put("idSubUrusan","");
                    	FrmPendaftaranFailData.getUrusan();
                    	listUrusan = FrmPendaftaranFailData.getListUrusan();
                    	this.context.put("SenaraiUrusan",listUrusan);
                    	
                    
                    	if ("getSubUrusan".equals(submit1)){
                    		
                    		System.out.println("idUrusan---"+idUrusanB);
                    		this.context.put("idUrusan",idUrusanB);
                    		FrmPendaftaranFailData.getSubUrusan(idUrusanB);
                    		listSubUrusan = FrmPendaftaranFailData.getListSubUrusan();
                    		this.context.put("SenaraiSubUrusan", listSubUrusan);
                    		this.context.put("TotalSubUrusan",listSubUrusan.size());
                    		this.context.put("idSubUrusan",idSubUrusanB);
                    		
                    	}
                    	
    	           	  	this.context.put("selectSubSubUrusanB",HTML.SelectSuburusan("socSubSubUrusanB"));
    	            	   
    	           	    this.context.put("selectLokasiFailB",HTML.SelectLokasiFailNegeri("socLokasiFailB",Utils.parseLong(idLokasiB),"",user_negeri));
    	            	String kabinetB = getParam("txtKabinetB");
    	            	this.context.put("kabinetB",kabinetB);
    	           	    this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(idTarafB),""));
    	           	    this.context.put("selectStatusB",HTML.SelectStatusFailA("socStatusB",Utils.parseLong(idStatusB),"disabled"));
    	           	    this.context.put("tarikhDaftarB", sdf.format(now));
       
             	 	}
             	 	else{	 	
             	 		String socNegeriB = getParam("socNegeriB");
           	    		String socDaerahB = getParam("socDaerahB");
           	    		String socDaerah = getParam("socDaerahB");
             	 		this.context.put("aktiviti1","tiada");
             	 		this.context.put("radioCheck1", "checked");
             	 		this.context.put("radioCheck2", "");
             	 		this.context.put("readOnly","");
             	 		this.context.put("aktivitiValue","0");
             	 		
             	 		tajukFailB = getParam("txtTajukFailB");
                    	this.context.put("tajukFailB",tajukFailB);
                    	this.context.put("selectNegeriB",HTML.SelectNegeriA("socNegeriB",Utils.parseLong(id_negeri),null,"onChange=\"doChangeNegeriB();\" "));
                    	this.context.put("selectDaerahB",HTML.SelectDaerahByIdNegeri(id_negeri,"socDaerahB",null,null));

                    	this.context.put("idUrusan","");
                    	this.context.put("idSubUrusan","");
                    	FrmPendaftaranFailData.getUrusan();
                    	listUrusan = FrmPendaftaranFailData.getListUrusan();
                    	this.context.put("SenaraiUrusan",listUrusan);
                    	
                    
                    	if ("getSubUrusan".equals(submit1)){
                    		
                    		this.context.put("idUrusan",idUrusanB);
                    		FrmPendaftaranFailData.getSubUrusan(idUrusanB);
                    		listSubUrusan = FrmPendaftaranFailData.getListSubUrusan();
                    		this.context.put("SenaraiSubUrusan", listSubUrusan);
                    		this.context.put("TotalSubUrusan",listSubUrusan.size());
                    		this.context.put("idSubUrusan",idSubUrusanB);
                    		
                    	}	
    	           	  	this.context.put("selectSubSubUrusanB",HTML.SelectSuburusan("socSubSubUrusanB"));
    	            	   
    	           	    this.context.put("selectLokasiFailB",HTML.SelectLokasiFailNegeri("socLokasiFailB",Utils.parseLong(idLokasiB),"", user_negeri));
    	            	String kabinetB = getParam("txtKabinetB");
    	            	this.context.put("kabinetB",kabinetB);
    	           	    this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(idTarafB),""));
    	           	    this.context.put("selectStatusB",HTML.SelectStatusFailA("socStatusB",Utils.parseLong(idStatusB),"disabled"));
    	           	    this.context.put("tarikhDaftarB", sdf.format(now));
             	 	}
    
            	}
            	
            	else if("tabFailNegeriBaru".equals(action1))
            	{
            		vm = "app/pfd/frmPendaftaranFailNegeriBaru.jsp";
                 	this.context.put("mode", "baru");
                	context.put("readonly","");
    				context.put("disabled","");
                	context.put("readonlyNoFail","");
    				context.put("readonlyTajukFail","");
    			  	context.put("disabledNoFail","");
    				context.put("disabledTajukFail","");
    				
    				view_Seksyen(session);
    	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
    	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
    	         	String id_seksyen = String.valueOf(hA.get("id_seksyen"));
    	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
    	         	context.put("idSeksyen",(hA.get("id_seksyen")));
    	         	context.put("idNegeri",(hA.get("id_negeri")));
    
                	String idFail = getParam("idFail");
                	String flagsubjaket = getParam("flagsubjaket");
                	context.put("flagsubjaket",flagsubjaket);
    
                	if ("ada".equals(modeAktiviti)) {
                		String socNegeriB = getParam("socNegeriB");
           	    		String socDaerahB = getParam("socDaerahB");
           	    		String socDaerah = getParam("socDaerahB");
           	    		String socSubU = getParam("socSuburusanB");
             	 		this.context.put("aktiviti1","ada");
             	 		this.context.put("aktiviti","");
             	 		this.context.put("radioCheck1", "");
             	 		this.context.put("radioCheck2", "checked");
             	 		this.context.put("readOnly","");
             	 		this.context.put("aktivitiValue","1");
             	 		
             	 		tajukFailB = getParam("txtTajukFailB");
                    	this.context.put("tajukFailB",tajukFailB);
                    	this.context.put("selectNegeriB",HTML.SelectNegeriA("socNegeriB",Utils.parseLong(id_negeri),null,"onChange=\"doChangeNegeriB();\" "));
                    	this.context.put("selectDaerahB",HTML.SelectDaerahByIdNegeri(id_negeri,"socDaerahB",null,null));

                    	this.context.put("idUrusan","");
                    	this.context.put("idSubUrusan","");
                    	FrmPendaftaranFailData.getUrusan();
                    	listUrusan = FrmPendaftaranFailData.getListUrusan();
                    	this.context.put("SenaraiUrusan",listUrusan);
                    	
                    
                    	if ("getSubUrusan".equals(submit1)){
                    		
                    		System.out.println("idUrusan---"+idUrusanB);
                    		this.context.put("idUrusan",idUrusanB);
                    		FrmPendaftaranFailData.getSubUrusan(idUrusanB);
                    		listSubUrusan = FrmPendaftaranFailData.getListSubUrusan();
                    		this.context.put("SenaraiSubUrusan", listSubUrusan);
                    		this.context.put("TotalSubUrusan",listSubUrusan.size());
                    		this.context.put("idSubUrusan",idSubUrusanB);
                    		
                    	}
    	           	    	
    	           	  	this.context.put("selectSubSubUrusanB",HTML.SelectSuburusan("socSubSubUrusanB"));
    	            	   
    	           	    this.context.put("selectLokasiFailB",HTML.SelectLokasiFailNegeri("socLokasiFailB",Utils.parseLong(idLokasiB),"",user_negeri));
    	            	String kabinetB = getParam("txtKabinetB");
    	            	this.context.put("kabinetB",kabinetB);
    	           	    this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(idTarafB),""));
    	           	    this.context.put("selectStatusB",HTML.SelectStatusFailA("socStatusB",Utils.parseLong(idStatusB),"disabled"));
    	           	    this.context.put("tarikhDaftarB", sdf.format(now));
       
             	 	}          
 
             	 	else{	 	
             	 		String socNegeriB = getParam("socNegeriB");
           	    		String socDaerahB = getParam("socDaerahB");
           	    		String socDaerah = getParam("socDaerahB");
             	 		this.context.put("aktiviti1","tiada");
             	 		this.context.put("radioCheck1", "checked");
             	 		this.context.put("radioCheck2", "");
             	 		this.context.put("readOnly","");
             	 		this.context.put("aktivitiValue","0");
             	 		
             	 		tajukFailB = getParam("txtTajukFailB");
                    	this.context.put("tajukFailB",tajukFailB);
                    	this.context.put("selectNegeriB",HTML.SelectNegeriA("socNegeriB",Utils.parseLong(id_negeri),null,"onChange=\"doChangeNegeriB();\" "));
                    	this.context.put("selectDaerahB",HTML.SelectDaerahByIdNegeri(idNegeriB,"socDaerahB",null,null));
                    	

                    	this.context.put("idUrusan","");
                    	this.context.put("idSubUrusan","");
                    	FrmPendaftaranFailData.getUrusan();
                    	listUrusan = FrmPendaftaranFailData.getListUrusan();
                    	this.context.put("SenaraiUrusan",listUrusan);
                    	
                    
                    	if ("getSubUrusan".equals(submit1)){
                    		
                    		this.context.put("idUrusan",idUrusanB);
                    		FrmPendaftaranFailData.getSubUrusan(idUrusanB);
                    		listSubUrusan = FrmPendaftaranFailData.getListSubUrusan();
                    		this.context.put("SenaraiSubUrusan", listSubUrusan);
                    		this.context.put("TotalSubUrusan",listSubUrusan.size());
                    		this.context.put("idSubUrusan",idSubUrusanB);
                    		
                    	}
    	           	    	
    	           	  	this.context.put("selectSubSubUrusanB",HTML.SelectSuburusan("socSubSubUrusanB"));
    	            	   
    	           	    this.context.put("selectLokasiFailB",HTML.SelectLokasiFailNegeri("socLokasiFailB",Utils.parseLong(idLokasiB),"",user_negeri));
    	            	String kabinetB = getParam("txtKabinetB");
    	            	this.context.put("kabinetB",kabinetB);
    	           	    this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(idTarafB),""));
    	           	    this.context.put("selectStatusB",HTML.SelectStatusFailA("socStatusB",Utils.parseLong(idStatusB),"disabled"));
    	           	    this.context.put("tarikhDaftarB", sdf.format(now));
             	 	}
    
            	}
            	
            	else if("tabFailSeksyenArkib".equals(action1))
            	{
            		vm = "app/pfd/frmPendaftaranFailSeksyenArkib.jsp";
                 	this.context.put("mode", "baru");
                	context.put("readonly","");
    				context.put("disabled","");
                	context.put("readonlyNoFail","");
    			  	context.put("disabledNoFail","");
    				
    			  	
    			  	
    			  	
                	String idFail = getParam("idFail");
                	String flagsubjaket = getParam("flagsubjaket");
                	context.put("flagsubjaket",flagsubjaket);
                	
                	view_Seksyen(session);
    	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
    	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
    	         	idSeksyenC = String.valueOf(hA.get("id_seksyen"));
    	         	kodSeksyen = String.valueOf(hA.get("kod_seksyen"));
    	         	kod_negeri = String.valueOf(hA.get("kod_negeri"));
    	         	namaSeksyen = String.valueOf(hA.get("nama_seksyen"));
    	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
    	         	String nama_negeri = String.valueOf(hA.get("nama_negeri"));
    	         	
    	         	
    	         	
    	         	context.put("idNegeri",id_negeri);
    	         	
    	         	
    	         	
    	         	view_unit(session);
    	         	paparUnit = FrmLogDokumenData.getDataUnit();
    	         	Hashtable hZ = (Hashtable)paparUnit.get(0);
    	         	String nama_pejabat = String.valueOf(hZ.get("NAMA_PEJABAT"));
    	         	String id_pejabatjkptg = String.valueOf(hZ.get("ID_PEJABATJKPTG"));
    	         	context.put("seksyen",nama_negeri + "-"+ nama_pejabat);
    	         	context.put("id_pejabatjkptg",id_pejabatjkptg);
                	
                	
                	this.context.put("noFailArkib", "");
	         	 	this.context.put("idUrusan", "");
	         	 	this.context.put("namaUrusan","");
	         	 	this.context.put("idSuburusan","");
	         	 	this.context.put("namaSuburusan","");
	         	 	this.context.put("idSubSuburusan","");
	         	 	this.context.put("namaSubSuburusan", "");
	         	 	this.context.put("idSubSubSuburusan","");
	         	 	this.context.put("namaSubSubSuburusan", "");
	         	 	this.context.put("tajukFailC","");
	         	 	this.context.put("noFailLama","");
	         	 	
                	if ("ada".equals(modeAktiviti)) { 
                		submit = getParam("command");
                			
               	    		
                		this.context.put("aktiviti1","ada");
    	         	 	this.context.put("aktiviti","");
    	         	 	this.context.put("radioCheck1", "");
    	         	 	this.context.put("radioCheck2", "checked");
    	         	 	this.context.put("readOnly","");
    	         	 	this.context.put("aktivitiValue","1");
    	         	 	
    	         	 		if (!"".equals(id_FailArkib)){
	    	         	 	
	    	         	 	FrmPendaftaranFailData.getFailArkib(id_FailArkib);
	    	         	 	paparFailArkib = FrmPendaftaranFailData.paparFailArkib();
	    	         	 	Hashtable hPaparArkib = (Hashtable)paparFailArkib.get(0);
	    	         	 	
	    	         	 	this.context.put("noFailArkib", "JKPTGN-"+kod_negeri+"/"+hPaparArkib.get("NO_FAIL_ARKIB"));
	    	         	 	this.context.put("idUrusan", hPaparArkib.get("ID_URUSAN"));
	    	         	 	this.context.put("namaUrusan", hPaparArkib.get("KOD_URUSAN")+"-"+hPaparArkib.get("NAMA_URUSAN"));
	    	         	 	this.context.put("idSuburusan", hPaparArkib.get("ID_SUBURUSAN"));
	    	         	 	this.context.put("namaSuburusan", hPaparArkib.get("KOD_SUBURUSAN")+"-"+ hPaparArkib.get("NAMA_SUBURUSAN"));
	    	         	 	this.context.put("idSubSuburusan", hPaparArkib.get("ID_SUBSUBURUSAN"));
	    	         	 	this.context.put("namaSubSuburusan",hPaparArkib.get("KOD_SUBSUBURUSAN")+"-"+ hPaparArkib.get("NAMA_SUBSUBURUSAN"));
	    	         	 	this.context.put("idSubSubSuburusan", hPaparArkib.get("ID_SUBSUBSUBURUSAN"));
	    	         	 	this.context.put("namaSubSubSuburusan", hPaparArkib.get("KOD_SUBSUBSUBURUSAN")+"-"+hPaparArkib.get("NAMA_SUBSUBSUBURUSAN"));
	    	         	 	this.context.put("tajukFailC",hPaparArkib.get("NAMA_SUBSUBSUBURUSAN"));
	    	         	 	
	    	         	 	}
    	         	 		
    	         	 		if(!"".equals(id_FailLama)){
    	        				
    	        				
    	         	 			failLama = getNoFailLama(id_FailLama);
	    	    				Hashtable hF = (Hashtable)failLama.get(0);
	    	    				context.put("noFailLama",hF.get("NO_FAIL"));
    	        				
    	        			}
    	         	 		
    	         	 		
    	           	    this.context.put("selectLokasiFailC",HTML.SelectLokasiFailNegeri("socLokasiFailC",Utils.parseLong(idLokasiC),"",user_negeri));
    	            	String kabinetB = getParam("txtKabinetC");
    	            	this.context.put("kabinetC",kabinetB);    
    	           	    this.context.put("selectTarafC",HTML.SelectTarafKeselamatan("socTarafC",Utils.parseLong(idTarafC),""));
    	           	    this.context.put("selectStatusC",HTML.SelectStatusFailA("socStatusC",Utils.parseLong(idStatusC),"disabled"));
    	           	    this.context.put("tarikhDaftarC", sdf.format(now));
    	   
    	         	 	}
                		
    	         	 	else{	 	
    	         	 		
    	         	 	this.context.put("aktiviti1","tiada");
    	         	 	this.context.put("radioCheck1", "checked");
    	         	 	this.context.put("radioCheck2", "");
    	         	 	this.context.put("readOnly","");
    	         	 	this.context.put("aktivitiValue","0");
    	         	    
    	         	 	
	    	         	 	if (!"".equals(id_FailArkib)){
	    	         	 	
	    	         	 	FrmPendaftaranFailData.getFailArkib(id_FailArkib);
	    	         	 	paparFailArkib = FrmPendaftaranFailData.paparFailArkib();
	    	         	 	Hashtable hPaparArkib = (Hashtable)paparFailArkib.get(0);
	    	         	 	
	    	         	 	this.context.put("noFailArkib", "JKPTGN-"+kod_negeri+"/"+hPaparArkib.get("NO_FAIL_ARKIB"));
	    	         	 	this.context.put("idUrusan", hPaparArkib.get("ID_URUSAN"));
	    	         	 	this.context.put("namaUrusan", hPaparArkib.get("KOD_URUSAN")+"-"+hPaparArkib.get("NAMA_URUSAN"));
	    	         	 	this.context.put("idSuburusan", hPaparArkib.get("ID_SUBURUSAN"));
	    	         	 	this.context.put("namaSuburusan", hPaparArkib.get("KOD_SUBURUSAN")+"-"+ hPaparArkib.get("NAMA_SUBURUSAN"));
	    	         	 	this.context.put("idSubSuburusan", hPaparArkib.get("ID_SUBSUBURUSAN"));
	    	         	 	this.context.put("namaSubSuburusan",hPaparArkib.get("KOD_SUBSUBURUSAN")+"-"+ hPaparArkib.get("NAMA_SUBSUBURUSAN"));
	    	         	 	this.context.put("idSubSubSuburusan", hPaparArkib.get("ID_SUBSUBSUBURUSAN"));
	    	         	 	this.context.put("namaSubSubSuburusan", hPaparArkib.get("KOD_SUBSUBSUBURUSAN")+"-"+hPaparArkib.get("NAMA_SUBSUBSUBURUSAN"));
	    	         	 	this.context.put("tajukFailC",hPaparArkib.get("NAMA_SUBSUBSUBURUSAN"));
	    	         		 	
	    	         	 	
	    	         	 	}
	    	         	 	
	    	         	 	if(!"".equals(id_FailLama)){
    	        				
    	        				
    	         	 			failLama = getNoFailLama(id_FailLama);
	    	    				Hashtable hF = (Hashtable)failLama.get(0);
	    	    				context.put("noFailLama",hF.get("NO_FAIL"));
    	        				
    	        			}
    	         	 		
    	         	 	
    	           	    this.context.put("selectLokasiFailC",HTML.SelectLokasiFailNegeri("socLokasiFailC",Utils.parseLong(idLokasiC),"",user_negeri));
     	            	String kabinetB = getParam("txtKabinetC");
     	            	this.context.put("kabinetC",kabinetB);    
     	           	    this.context.put("selectTarafC",HTML.SelectTarafKeselamatan("socTarafC",Utils.parseLong(idTarafC),""));
     	           	    this.context.put("selectStatusC",HTML.SelectStatusFailA("socStatusC",Utils.parseLong(idStatusC),"disabled"));
     	           	    this.context.put("tarikhDaftarC", sdf.format(now));
     	      
    	         	 	}
                	
                	
	         	 		

            	}
            	
            	else if("simpanFailNegeriLama".equals(action1))
            	{
            		vm = "app/pfd/frmPendaftaranFailNegeriLama.jsp";
            		this.context.put("mode", "papar");
                	context.put("readonly","readonly");
    				context.put("disabled","disabled");
                	context.put("readonlyNoFail","readonly");
    				context.put("readonlyTajukFail","readonly");
    			  	context.put("disabledNoFail","disabled");
    				context.put("disabledTajukFail","disabled");
    				context.put("readonlyNoFailRoot","readonly");
    			  	context.put("disabledNoFailRoot","disabled");
    			  	context.put("readonlyNoTurutan","readonly");
    				context.put("disabledNoTurutan","disabled");
    				context.put("readonlyNoJilid","readonly");
    				context.put("disabledNoJilid","disabled");
    				context.put("readonlyNoSubjaket","readonly");
    				context.put("disabledNoSubjaket","disabled");
    				context.put("readonlyK","readonly");
    			  	context.put("disabledK","disabled");
    			  	context.put("readonlyDate","readonly");
    			  	context.put("disabledDate","disabled");
    				
    				
    		  		if (doPost.equals("true")) {
    		  		String idFail = simpanFailLamaNegeri(session);

            		paparFailLama = FrmPendaftaranFailData.getPaparFailLama(idFail);
        			
        			Hashtable hA = (Hashtable)paparFailLama.get(0);
        			
        			this.context.put("idFail", hA.get("idFail"));
        			this.context.put("noFail", hA.get("noFail"));
        			this.context.put("noFailRoot", hA.get("noFailRoot"));
        			this.context.put("noTurutan", hA.get("noTurutan"));
        			this.context.put("noJilid", hA.get("noJilid"));
        			this.context.put("noSubjaket", hA.get("noSubjaket"));
        			this.context.put("tajukFailB", hA.get("tajukFail"));
        			this.context.put("kabinetB", hA.get("kabinet"));
        			this.context.put("idUrusan",hA.get("idUrusan"));
        			this.context.put("idSubUrusan",hA.get("idSuburusan"));
        			this.context.put("selectNegeriB",HTML.SelectNegeriA("socNegeriB",Utils.parseLong(hA.get("idNegeri").toString()),"disabled"));
        			this.context.put("selectDaerahB",HTML.SelectDaerah("socDaerahB",Utils.parseLong(hA.get("idDaerah").toString()),"disabled"));
             	    this.context.put("aktivitiValue", hA.get("idAktiviti"));  
             	    this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
        			this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),"disabled"));
        			this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"disabled"));
        			this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
    		  		}
            	}
            	
               	else if("simpanFailNegeriBaru".equals(action1))
            	{
                	vm = "app/pfd/frmPendaftaranFailNegeriBaru.jsp";
                	context.put("mode", "papar");
                	context.put("readonly","readonly");
    				context.put("disabled","disabled");
                	context.put("readonlyNoFail","readonly");
    				context.put("readonlyTajukFail","readonly");
    			  	context.put("disabledNoFail","disabled");
    				context.put("disabledTajukFail","disabled");
    				context.put("readonlyK","readonly");
    			  	context.put("disabledK","disabled");
    				
    		  		if (doPost.equals("true")) {
    		  		String idFail = simpanFailBaruNegeri(session);  
    
    				logic.updateSubjaketNJilid(idFail);
    				
                	paparFailBaru = FrmPendaftaranFailData.getPaparFailBaru(idFail);
        			
        			Hashtable hA = (Hashtable)paparFailBaru.get(0);
        			
        			this.context.put("idFail", hA.get("idFail"));
        			this.context.put("noFail", hA.get("noFail"));
        			this.context.put("tajukFailB", hA.get("tajukFail"));
        			this.context.put("kabinetB", hA.get("kabinet"));
        			this.context.put("idUrusan",hA.get("idUrusan"));
        			this.context.put("idSubUrusan",hA.get("idSuburusan"));
        			this.context.put("selectNegeriB",HTML.SelectNegeriA("socNegeriB",Utils.parseLong(hA.get("idNegeri").toString()),"disabled"));
        			this.context.put("selectDaerahB",HTML.SelectDaerah("socDaerahB",Utils.parseLong(hA.get("idDaerah").toString()),"disabled"));
        			this.context.put("aktivitiValue", hA.get("idAktiviti"));
    	        	    if (hA.get("idAktiviti").equals("0")){
    	        	    	this.context.put("modeAktiviti", "tiada");
    	        	 		this.context.put("aktiviti1","tiada");
    	        	 		this.context.put("radioCheck1", "checked");
    	        	 		this.context.put("radioCheck2", "");
    	        	 		this.context.put("readOnly","disabled");
    	        	    }
    	        	    else
    	        	    {
    	        	    	this.context.put("modeAktiviti", "ada");
    	        	 		this.context.put("aktiviti1","ada");
    	        	 		this.context.put("radioCheck1", "");
    	        	 		this.context.put("radioCheck2", "checked");
    	        	 		this.context.put("readOnly","disabled");
    	        	 		this.context.put("aktiviti", hA.get("aktiviti"));
    	        	 		
    	        	    }
             	    this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
        			this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),"disabled"));
        			this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"disabled"));
        			this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
    		  		}
            	}
            	
               	else if("simpanFailArkib".equals(action1))
            	{
                	vm = "app/pfd/frmPendaftaranFailSeksyenArkib.jsp";
                	this.context.put("mode", "papar");
                	context.put("readonly","readonly");
    				context.put("disabled","disabled");
                	
    				
    				Boolean failArkib = logic.noFailExists(noFailArkib,user_negeri);
    				
    				
    				
    				if (failArkib == false){
    					
    					
    				 
	            		if (doPost.equals("true")) {
	            		
	            		String idFail = simpanFailArkib(session);
	            		
	            		
	        
						logic.updateSubjaketNJilid(idFail);
	    				
	                	paparFailBaru = FrmPendaftaranFailData.getPaparFailArkib(idFail);
	        			
	        			Hashtable hA = (Hashtable)paparFailBaru.get(0);
	        			
	        			this.context.put("idFail", hA.get("idFail"));
	        			this.context.put("noFailArkib", hA.get("noFail"));
	        			this.context.put("noFailLama", hA.get("noFailLama"));
	        			this.context.put("tajukFailC", hA.get("tajukFail"));
	        			this.context.put("kabinetC", hA.get("kabinet"));
		         	 	this.context.put("idUrusan", hA.get("ID_URUSAN"));
		         	 	this.context.put("namaUrusan", hA.get("KOD_URUSAN")+"-"+hA.get("NAMA_URUSAN"));
		         	 	this.context.put("namaSuburusan", hA.get("KOD_SUBURUSAN")+"-"+ hA.get("NAMA_SUBURUSAN"));
		         	 	this.context.put("namaSubSuburusan",hA.get("KOD_SUBSUBURUSAN")+"-"+ hA.get("NAMA_SUBSUBURUSAN"));
		         	 	this.context.put("namaSubSubSuburusan", hA.get("KOD_SUBSUBSUBURUSAN")+"-"+hA.get("NAMA_SUBSUBSUBURUSAN"));
		         	 	
		         	 	view_Seksyen(session);
	    	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
	    	         	Hashtable hB = (Hashtable) paparSeksyen.get(0);
	    	         	idSeksyenC = String.valueOf(hB.get("id_seksyen"));
	    	         	kodSeksyen = String.valueOf(hB.get("kod_seksyen"));
	    	         	kod_negeri = String.valueOf(hB.get("kod_negeri"));
	    	         	namaSeksyen = String.valueOf(hB.get("nama_seksyen"));
	    	         	String id_negeri = String.valueOf(hB.get("id_negeri"));
	    	         	String nama_negeri = String.valueOf(hB.get("nama_negeri"));
	    	         	
	    	         	context.put("idSeksyen",idSeksyenC);
	    	         	
	    	         	context.put("idNegeri",id_negeri);
	    	         	
    	         	 	
    	         	 	
    	         	 	
    	         	 	view_unit(session);
        	         	paparUnit = FrmLogDokumenData.getDataUnit();
        	         	Hashtable hZ = (Hashtable)paparUnit.get(0);
        	         	String nama_pejabat = String.valueOf(hZ.get("NAMA_PEJABAT"));
        	         	context.put("seksyen",nama_negeri + "-"+ nama_pejabat);
    	         	 	
	             	    this.context.put("aktivitiValue", hA.get("idAktiviti"));
	    	         	    if (hA.get("idAktiviti").equals("0")){
	    	         	    	this.context.put("modeAktiviti", "tiada");
	    	         	 		this.context.put("aktiviti1","tiada");
	    	         	 		this.context.put("radioCheck1", "checked");
	    	         	 		this.context.put("radioCheck2", "");
	    	         	 		this.context.put("readOnly","disabled");
	    	         	    }
	    	         	    else
	    	         	    {
	    	         	    	this.context.put("modeAktiviti", "ada");
	    	         	 		this.context.put("aktiviti1","ada");
	    	         	 		this.context.put("radioCheck1", "");
	    	         	 		this.context.put("radioCheck2", "checked");
	    	         	 		this.context.put("readOnly","disabled");
	    	         	 		
	    	         	 		
	    	         	    }
	    	         	    
	    	         	   FrmPendaftaranFailData.getTaraf(hA.get("idTaraf").toString());
	        	         	paparTaraf = FrmPendaftaranFailData.getListTaraf();
	        	         	Hashtable hC = (Hashtable) paparTaraf.get(0);
	        	         	
	        	         	if (hC.get("KOD_TARAF_KESELAMATAN").equals("")){
	        	         		this.context.put("taraf", hC.get("TARAF_KESELAMATAN"));
	        	         	}
	        	         	else{
	        	         		this.context.put("taraf", hC.get("KOD_TARAF_KESELAMATAN")+"-"+hC.get("TARAF_KESELAMATAN"));
	        	         	}
	        	         	
	        	         	FrmPendaftaranFailData.getStatusFail(hA.get("idStatus").toString());
	    	         	 	paparStatus = FrmPendaftaranFailData.getListStatus();
	    	         	 	Hashtable hD = (Hashtable) paparStatus.get(0);
	    	         	 	
	    	         	 	this.context.put("status", hD.get("KETERANGAN"));
	    	         	 	
	    	         	 	if(!hA.get("idLokasi").equals("")){
	    	         	 	
	    	         	 	FrmPendaftaranFailData.getLokasiFail(hA.get("idLokasi").toString());
	    	         	 	paparStatus = FrmPendaftaranFailData.getListLokasi();
	    	         	 	Hashtable hE = (Hashtable) paparStatus.get(0);
	    	         	 	
	    	         	 	this.context.put("lokasi", hE.get("LOKASI_FAIL"));
	    	         	 	}
	    	         	 	else{
	    	         	 		this.context.put("lokasi", "");
	    	         	 	}
	    	         	 	this.context.put("tarikhDaftarC", hA.get("tarikhDaftar"));
	        			
	            		}
	            		
	            			
	            		}
    					else{
            			
            			this.context.put("result","yes");
            			this.context.put("mode", "baru");
                    	context.put("readonly","");
        				context.put("disabled","");
                    	context.put("readonlyNoFail","");
        			  	context.put("disabledNoFail","");
        				
        			  	
        			  	
        			  	
                    	String idFail = getParam("idFail");
                    	String flagsubjaket = getParam("flagsubjaket");
                    	context.put("flagsubjaket",flagsubjaket);
                    	
                    	view_Seksyen(session);
	    	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
	    	         	Hashtable hB = (Hashtable) paparSeksyen.get(0);
	    	         	idSeksyenC = String.valueOf(hB.get("id_seksyen"));
	    	         	kodSeksyen = String.valueOf(hB.get("kod_seksyen"));
	    	         	kod_negeri = String.valueOf(hB.get("kod_negeri"));
	    	         	namaSeksyen = String.valueOf(hB.get("nama_seksyen"));
	    	         	String id_negeri = String.valueOf(hB.get("id_negeri"));
	    	         	String nama_negeri = String.valueOf(hB.get("nama_negeri"));
	    	         	
	    	         	context.put("idSeksyen",idSeksyenC);
	    	         	
	    	         	context.put("idNegeri",id_negeri);
	    	         	
    	         	 	
    	         	 	
    	         	 	
    	         	 	view_unit(session);
        	         	paparUnit = FrmLogDokumenData.getDataUnit();
        	         	Hashtable hZ = (Hashtable)paparUnit.get(0);
        	         	String nama_pejabat = String.valueOf(hZ.get("NAMA_PEJABAT"));
        	         	context.put("seksyen",nama_negeri + "-"+ nama_pejabat);
                    	
                    	tajukFailC = getParam("txtTajukFailC");
                    	this.context.put("tajukFailC",tajukFailC);
                    	this.context.put("noFailArkib", "");
    	         	 	this.context.put("idUrusan", "");
    	         	 	this.context.put("namaUrusan","");
    	         	 	this.context.put("idSuburusan","");
    	         	 	this.context.put("namaSuburusan","");
    	         	 	this.context.put("idSubSuburusan","");
    	         	 	this.context.put("namaSubSuburusan", "");
    	         	 	this.context.put("idSubSubSuburusan","");
    	         	 	this.context.put("namaSubSubSuburusan", "");
    	         	 	this.context.put("noFailLama", "");
                    	  	    			
                    	if ("ada".equals(modeAktiviti)) { 
                    		submit = getParam("command");
                    			
                   	    		
                    		this.context.put("aktiviti1","ada");
        	         	 	this.context.put("aktiviti","");
        	         	 	this.context.put("radioCheck1", "");
        	         	 	this.context.put("radioCheck2", "checked");
        	         	 	this.context.put("readOnly","");
        	         	 	this.context.put("aktivitiValue","1");
        	         	 	this.context.put("selectSeksyenC",HTML.SelectSeksyen("socSeksyenC",Utils.parseLong(idSeksyenC),"disabled"));
        	         	 	
        	         	 		if (!"".equals(id_FailArkib)){
    	    	         	 	
    	    	         	 	FrmPendaftaranFailData.getFailArkib(id_FailArkib);
    	    	         	 	paparFailArkib = FrmPendaftaranFailData.paparFailArkib();
    	    	         	 	Hashtable hPaparArkib = (Hashtable)paparFailArkib.get(0);
    	    	         	 	
    	    	         	 	
    	    	         	 	this.context.put("noFailArkib", hPaparArkib.get("NO_FAIL_ARKIB"));
    	    	         	 	this.context.put("idUrusan", hPaparArkib.get("ID_URUSAN"));
    	    	         	 	this.context.put("namaUrusan", hPaparArkib.get("KOD_URUSAN")+"-"+hPaparArkib.get("NAMA_URUSAN"));
    	    	         	 	this.context.put("idSuburusan", hPaparArkib.get("ID_SUBURUSAN"));
    	    	         	 	this.context.put("namaSuburusan", hPaparArkib.get("KOD_SUBURUSAN")+"-"+ hPaparArkib.get("NAMA_SUBURUSAN"));
    	    	         	 	this.context.put("idSubSuburusan", hPaparArkib.get("ID_SUBSUBURUSAN"));
    	    	         	 	this.context.put("namaSubSuburusan",hPaparArkib.get("KOD_SUBSUBURUSAN")+"-"+ hPaparArkib.get("NAMA_SUBSUBURUSAN"));
    	    	         	 	this.context.put("idSubSubSuburusan", hPaparArkib.get("ID_SUBSUBSUBURUSAN"));
    	    	         	 	this.context.put("namaSubSubSuburusan", hPaparArkib.get("KOD_SUBSUBSUBURUSAN")+"-"+hPaparArkib.get("NAMA_SUBSUBSUBURUSAN"));
    	    	         	 	
    	    	         	 	
    	    	         	 	}
        	         	 		
        	           	    this.context.put("selectLokasiFailC",HTML.SelectLokasiFailNegeri("socLokasiFailC",Utils.parseLong(idLokasiC),"",user_negeri));
        	            	String kabinetB = getParam("txtKabinetC");
        	            	this.context.put("kabinetC",kabinetB);    
        	           	    this.context.put("selectTarafC",HTML.SelectTarafKeselamatan("socTarafC",Utils.parseLong(idTarafC),""));
        	           	    this.context.put("selectStatusC",HTML.SelectStatusFailA("socStatusC",Utils.parseLong(idStatusC),"disabled"));
        	           	    this.context.put("tarikhDaftarC", sdf.format(now));
        	   
        	         	 	}
                    		
        	         	 	else{	 	
        	         	 		
        	         	 	this.context.put("aktiviti1","tiada");
        	         	 	this.context.put("radioCheck1", "checked");
        	         	 	this.context.put("radioCheck2", "");
        	         	 	this.context.put("readOnly","");
        	         	 	this.context.put("aktivitiValue","0");
        	         	 	this.context.put("selectSeksyenC",HTML.SelectSeksyen("socSeksyenC",Utils.parseLong(idSeksyenC),"disabled"));	
        	         	    
        	         	 	
    	    	         	 	if (!"".equals(id_FailArkib)){
    	    	         	 	
    	    	         	 	FrmPendaftaranFailData.getFailArkib(id_FailArkib);
    	    	         	 	paparFailArkib = FrmPendaftaranFailData.paparFailArkib();
    	    	         	 	Hashtable hPaparArkib = (Hashtable)paparFailArkib.get(0);
    	    	         	 	
    	    	         	 	
    	    	         	 	this.context.put("noFailArkib", hPaparArkib.get("NO_FAIL_ARKIB"));
    	    	         	 	this.context.put("idUrusan", hPaparArkib.get("ID_URUSAN"));
    	    	         	 	this.context.put("namaUrusan", hPaparArkib.get("KOD_URUSAN")+"-"+hPaparArkib.get("NAMA_URUSAN"));
    	    	         	 	this.context.put("idSuburusan", hPaparArkib.get("ID_SUBURUSAN"));
    	    	         	 	this.context.put("namaSuburusan", hPaparArkib.get("KOD_SUBURUSAN")+"-"+ hPaparArkib.get("NAMA_SUBURUSAN"));
    	    	         	 	this.context.put("idSubSuburusan", hPaparArkib.get("ID_SUBSUBURUSAN"));
    	    	         	 	this.context.put("namaSubSuburusan",hPaparArkib.get("KOD_SUBSUBURUSAN")+"-"+ hPaparArkib.get("NAMA_SUBSUBURUSAN"));
    	    	         	 	this.context.put("idSubSubSuburusan", hPaparArkib.get("ID_SUBSUBSUBURUSAN"));
    	    	         	 	this.context.put("namaSubSubSuburusan", hPaparArkib.get("KOD_SUBSUBSUBURUSAN")+"-"+hPaparArkib.get("NAMA_SUBSUBSUBURUSAN"));
    	    	         	 	
    	    	         		 	
    	    	         	 	
    	    	         	 	}
        	         	 	
        	           	    this.context.put("selectLokasiFailC",HTML.SelectLokasiFailNegeri("socLokasiFailC",Utils.parseLong(idLokasiC),"",user_negeri));
         	            	String kabinetB = getParam("txtKabinetC");
         	            	this.context.put("kabinetC",kabinetB);    
         	           	    this.context.put("selectTarafC",HTML.SelectTarafKeselamatan("socTarafC",Utils.parseLong(idTarafC),""));
         	           	    this.context.put("selectStatusC",HTML.SelectStatusFailA("socStatusC",Utils.parseLong(idStatusC),"disabled"));
         	           	    this.context.put("tarikhDaftarC", sdf.format(now));
         	      
        	         	 	}
                    	
            		}
        		
            	}
            	
               	else if ("papar".equals(action1)){
               		
               		    			
               		String idFail = getParam("idFail");
               		    	    	
               		paparSemuaFail = FrmPendaftaranFailData.getPaparSemuaFail(idFail);
               		Hashtable h = (Hashtable)paparSemuaFail.get(0);
               		
               		System.out.println("flag_view_file>>"+h.get("flag_view_file"));
               		    			
               		    			
               		if(h.get("flag_view_file").equals("1"))
               		{
               		    				
               		    context.put("mode", "papar");
               		    context.put("readonly","readonly");
               		    context.put("disabled","disabled");
                      	context.put("readonlyNoFail","readonly");
               		    context.put("readonlyTajukFail","readonly");
               		    context.put("disabledNoFail","disabled");
               		    context.put("disabledTajukFail","disabled");
               		    context.put("readonlyK","readonly");
               		    context.put("disabledK","disabled");
               		    context.put("readonlyDate","readonly");
            		    context.put("disabledDate","disabled");
               		    				
               		    String idFailA = getParam("idFail");
               		    vm = "app/pfd/frmPendaftaranFailSeksyenLama.jsp";
               		            		
               		    paparFailLama = FrmPendaftaranFailData.getPaparFailLama(idFail);
               		        			
               		    Hashtable hA = (Hashtable)paparFailLama.get(0);
               		        			
               		    this.context.put("idFail", hA.get("idFail"));
               		    this.context.put("noFail", hA.get("noFail"));
               		    this.context.put("tajukFailB", hA.get("tajukFail"));
               		    this.context.put("kabinetB", hA.get("kabinet"));
               		    this.context.put("idUrusan",hA.get("idUrusan"));
               		    this.context.put("idSubUrusan",hA.get("idSuburusan"));
               		    this.context.put("selectSeksyenB",HTML.SelectSeksyen("socSeksyenB",Utils.parseLong(hA.get("idSeksyen").toString()),"disabled"));
              		    this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
               		    this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),"disabled"));
               		    this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"disabled"));
               		    this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
               		    
               		    FrmPendaftaranFailData.getUrusan();
            		    listUrusan = FrmPendaftaranFailData.getListUrusan();
            		    this.context.put("SenaraiUrusan",listUrusan);

       		 			FrmPendaftaranFailData.getSubUrusan(hA.get("idUrusan").toString());
       		 			listSubUrusan = FrmPendaftaranFailData.getListSubUrusan();
       		 			this.context.put("SenaraiSubUrusan", listSubUrusan);
               		    
               		    				
               		}
               		    			
               		else if(h.get("flag_view_file").equals("2")){
               		    				
               		    context.put("mode", "papar");
               		    context.put("readonly","readonly");
               		    context.put("disabled","disabled");
               		    context.put("readonlyNoFail","readonly");
               		    context.put("readonlyTajukFail","readonly");
               		    context.put("disabledNoFail","disabled");
               		    context.put("disabledTajukFail","disabled");
	               		context.put("readonlyNoFailRoot","readonly");
	     			  	context.put("disabledNoFailRoot","disabled");
	     			  	context.put("readonlyNoTurutan","readonly");
	     				context.put("disabledNoTurutan","disabled");
	     				context.put("readonlyNoJilid","readonly");
	     				context.put("disabledNoJilid","disabled");
	     				context.put("readonlyNoSubjaket","readonly");
	     				context.put("disabledNoSubjaket","disabled");
               		    context.put("readonlyK","readonly");
               		    context.put("disabledK","disabled");
               		    context.put("readonlyDate","readonly");
               		    context.put("disabledDate","disabled");
               		    				
               		    String idFailA = getParam("idFail");
               		    vm = "app/pfd/frmPendaftaranFailNegeriLama.jsp";
               		            		
               		    paparFailLama = FrmPendaftaranFailData.getPaparFailLama(idFail);
               		        			
               		    Hashtable hA = (Hashtable)paparFailLama.get(0);
               		        			
               		    this.context.put("idFail", hA.get("idFail"));
               		    this.context.put("noFail", hA.get("noFail"));
               		    this.context.put("noFailRoot", hA.get("noFailRoot"));
               		    this.context.put("noTurutan", hA.get("noTurutan"));
               		    this.context.put("noJilid", hA.get("noJilid"));
               		    this.context.put("noSubjaket", hA.get("noSubjaket"));
               		    this.context.put("tajukFailB", hA.get("tajukFail"));
               		    this.context.put("kabinetB", hA.get("kabinet"));
               		    this.context.put("idUrusan",hA.get("idUrusan"));
             		    this.context.put("idSubUrusan",hA.get("idSuburusan"));
               		    this.context.put("selectNegeriB",HTML.SelectNegeriA("socNegeriB",Utils.parseLong(hA.get("idNegeri").toString()),"disabled"));
               		    this.context.put("selectDaerahB",HTML.SelectDaerah("socDaerahB",Utils.parseLong(hA.get("idDaerah").toString()),"disabled"));
              		    this.context.put("aktivitiValue", hA.get("idAktiviti"));  
               		    this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
               		    this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),"disabled"));
               		    this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"disabled"));
 	        			this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
 	        			
 	        			FrmPendaftaranFailData.getUrusan();
                		listUrusan = FrmPendaftaranFailData.getListUrusan();
                		this.context.put("SenaraiUrusan",listUrusan);

           		 		FrmPendaftaranFailData.getSubUrusan(hA.get("idUrusan").toString());
           		 		listSubUrusan = FrmPendaftaranFailData.getListSubUrusan();
           		 		this.context.put("SenaraiSubUrusan", listSubUrusan);
 	        			
               		}
               		
               		else if(h.get("flag_view_file").equals("3")){
             
               			context.put("mode", "papar");
               			context.put("readonly","readonly");
           				context.put("disabled","disabled");
           				context.put("readonlyNoFail","readonly");
           				context.put("readonlyTajukFail","readonly");
           				context.put("disabledNoFail","disabled");
           				context.put("disabledTajukFail","disabled");
           				context.put("readonlyK","readonly");
            		  	context.put("disabledK","disabled");
            		  	context.put("readonlyDate","readonly");
            		    context.put("disabledDate","disabled");
               		    				
            		  	String idFailA = getParam("idFail");
               		    vm = "app/pfd/frmPendaftaranFailSeksyenBaru.jsp";
               		                	
               		    paparFailBaru = FrmPendaftaranFailData.getPaparFailBaru(idFail);
               		        			
               		    Hashtable hA = (Hashtable)paparFailBaru.get(0);
               		        			
               		    this.context.put("idFail", hA.get("idFail"));
               		    this.context.put("noFail", hA.get("noFail"));
               		    this.context.put("tajukFailB", hA.get("tajukFail"));
               		    this.context.put("kabinetB", hA.get("kabinet"));
               		    this.context.put("aktivitiValue", hA.get("idAktiviti"));
               		    this.context.put("idUrusan",hA.get("idUrusan"));
               		    this.context.put("idSubUrusan",hA.get("idSuburusan"));
               				if (hA.get("idAktiviti").equals("0")){
               					this.context.put("modeAktiviti", "tiada");
	               				this.context.put("aktiviti1","tiada");
	               				this.context.put("radioCheck1", "checked");
	               				this.context.put("radioCheck2", "");
	               				this.context.put("readOnly","disabled");
               				}
               		  	    else
               		  	    {
               		  	    	this.context.put("modeAktiviti", "ada");
               		  	    	this.context.put("aktiviti1","ada");
               		  	    	this.context.put("radioCheck1", "");
               		  	    	this.context.put("radioCheck2", "checked");
               		  	    	this.context.put("readOnly","disabled");
               		  	    	this.context.put("aktiviti", hA.get("aktiviti"));
               		  	        	 		
               		  	    }
               				this.context.put("selectSeksyenB",HTML.SelectSeksyen("socSeksyenB",Utils.parseLong(hA.get("idSeksyen").toString()),"disabled"));
             		    	this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
               		    	this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),"disabled"));
               		    	this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"disabled"));
               		    	this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
               			
               			}
               		
               		else if(h.get("flag_view_file").equals("5")){
               			context.put("mode", "papar");
               			context.put("readonly","readonly");
           				context.put("disabled","disabled");
           				context.put("readonlyNoFail","readonly");
          				context.put("disabledNoFail","disable");
           				vm = "app/pfd/frmPendaftaranFailSeksyenArkib.jsp";
               			paparFailBaru = FrmPendaftaranFailData.getPaparFailArkib(idFail);
            			
            			Hashtable hA = (Hashtable)paparFailBaru.get(0);
            			
            			this.context.put("idFail", hA.get("idFail"));
            			this.context.put("noFailArkib", hA.get("noFail"));
            			this.context.put("noFailLama", hA.get("noFailLama"));
            			this.context.put("tajukFailC", hA.get("tajukFail"));
            			this.context.put("kabinetC", hA.get("kabinet"));
    	         	 	this.context.put("idUrusan", hA.get("ID_URUSAN"));
    	         	 	this.context.put("namaUrusan", hA.get("KOD_URUSAN")+"-"+hA.get("NAMA_URUSAN"));
    	         	 	this.context.put("namaSuburusan", hA.get("KOD_SUBURUSAN")+"-"+ hA.get("NAMA_SUBURUSAN"));
    	         	 	this.context.put("namaSubSuburusan",hA.get("KOD_SUBSUBURUSAN")+"-"+ hA.get("NAMA_SUBSUBURUSAN"));
    	         	 	this.context.put("namaSubSubSuburusan", hA.get("KOD_SUBSUBSUBURUSAN")+"-"+hA.get("NAMA_SUBSUBSUBURUSAN"));
    	         	 	
    	         	 	view_Seksyen(session);
	    	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
	    	         	Hashtable hB = (Hashtable) paparSeksyen.get(0);
	    	         	idSeksyenC = String.valueOf(hB.get("id_seksyen"));
	    	         	kodSeksyen = String.valueOf(hB.get("kod_seksyen"));
	    	         	kod_negeri = String.valueOf(hB.get("kod_negeri"));
	    	         	namaSeksyen = String.valueOf(hB.get("nama_seksyen"));
	    	         	String id_negeri = String.valueOf(hB.get("id_negeri"));
	    	         	String nama_negeri = String.valueOf(hB.get("nama_negeri"));
	    	         	
	    	         	context.put("idSeksyen",idSeksyenC);
	    	         	
	    	         	context.put("idNegeri",id_negeri);
	    	         	
    	         	 	
    	         	 	
    	         	 	
    	         	 	view_unit(session);
        	         	paparUnit = FrmLogDokumenData.getDataUnit();
        	         	Hashtable hZ = (Hashtable)paparUnit.get(0);
        	         	String nama_pejabat = String.valueOf(hZ.get("NAMA_PEJABAT"));
        	         	context.put("seksyen",nama_negeri + "-"+ nama_pejabat);
    	         	 	
                 	    this.context.put("aktivitiValue", hA.get("idAktiviti"));
        	         	    if (hA.get("idAktiviti").equals("0")){
        	         	    	this.context.put("modeAktiviti", "tiada");
        	         	 		this.context.put("aktiviti1","tiada");
        	         	 		this.context.put("radioCheck1", "checked");
        	         	 		this.context.put("radioCheck2", "");
        	         	 		this.context.put("readOnly","disabled");
        	         	    }
        	         	    else
        	         	    {
        	         	    	this.context.put("modeAktiviti", "ada");
        	         	 		this.context.put("aktiviti1","ada");
        	         	 		this.context.put("radioCheck1", "");
        	         	 		this.context.put("radioCheck2", "checked");
        	         	 		this.context.put("readOnly","disabled");
        	         	 		
        	         	 		
        	         	    }
        	         	
        	         	FrmPendaftaranFailData.getTaraf(hA.get("idTaraf").toString());
        	         	paparTaraf = FrmPendaftaranFailData.getListTaraf();
        	         	Hashtable hC = (Hashtable) paparTaraf.get(0);
        	         	
        	         	if (hC.get("KOD_TARAF_KESELAMATAN").equals("")){
        	         		this.context.put("taraf", hC.get("TARAF_KESELAMATAN"));
        	         	}
        	         	else{
        	         		this.context.put("taraf", hC.get("KOD_TARAF_KESELAMATAN")+"-"+hC.get("TARAF_KESELAMATAN"));
        	         	}
        	         	
        	         	FrmPendaftaranFailData.getStatusFail(hA.get("idStatus").toString());
    	         	 	paparStatus = FrmPendaftaranFailData.getListStatus();
    	         	 	Hashtable hD = (Hashtable) paparStatus.get(0);
    	         	 	
    	         	 	this.context.put("status", hD.get("KETERANGAN"));
    	         	 	
    	         	 	if(!hA.get("idLokasi").equals("")){
    	         	 	
    	         	 	FrmPendaftaranFailData.getLokasiFail(hA.get("idLokasi").toString());
    	         	 	paparStatus = FrmPendaftaranFailData.getListLokasi();
    	         	 	Hashtable hE = (Hashtable) paparStatus.get(0);
    	         	 	
    	         	 	this.context.put("lokasi", hE.get("LOKASI_FAIL"));
    	         	 	}
    	         	 	else{
    	         	 		this.context.put("lokasi", "");
    	         	 	}
    	         	 	
            			this.context.put("tarikhDaftarC", hA.get("tarikhDaftar"));
            			
               		}
               		
               		    else{
               			
               		      	context.put("mode", "papar");
               		      	context.put("readonly","readonly");
               		      	context.put("disabled","disabled");
               		      	context.put("readonlyNoFail","readonly");
               		      	context.put("readonlyTajukFail","readonly");
               		      	context.put("disabledNoFail","disabled");
               		      	context.put("disabledTajukFail","disabled");
               		      	context.put("readonlyK","readonly");
            			  	context.put("disabledK","disabled");
            			  	context.put("readonlyDate","readonly");
                		    context.put("disabledDate","disabled");
               		    				
               		   		vm = "app/pfd/frmPendaftaranFailNegeriBaru.jsp";
               		                	
               		   		String idFailA = getParam("idFail");
               		   		paparFailBaru = FrmPendaftaranFailData.getPaparFailBaru(idFail);
               		        			
               		   		Hashtable hA = (Hashtable)paparFailBaru.get(0);
               		        			
               		   		this.context.put("idFail", hA.get("idFail"));
               		   		this.context.put("noFail", hA.get("noFail"));
               		   		this.context.put("tajukFailB", hA.get("tajukFail"));
               		   		this.context.put("kabinetB", hA.get("kabinet"));
               		   	 	this.context.put("idUrusan",hA.get("idUrusan"));
              		    	this.context.put("idSubUrusan",hA.get("idSuburusan"));
               		   		this.context.put("selectNegeriB",HTML.SelectNegeriA("socNegeriB",Utils.parseLong(hA.get("idNegeri").toString()),"disabled"));
               		   		this.context.put("selectDaerahB",HTML.SelectDaerah("socDaerahB",Utils.parseLong(hA.get("idDaerah").toString()),"disabled"));
              		   		this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
               		   		this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),"disabled"));
               		   		this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"disabled"));
               		   		this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
               		   		
               		   		FrmPendaftaranFailData.getUrusan();
               		   		listUrusan = FrmPendaftaranFailData.getListUrusan();
               		   		this.context.put("SenaraiUrusan",listUrusan);
               		   		
               		   		FrmPendaftaranFailData.getSubUrusan(hA.get("idUrusan").toString());
               		   		listSubUrusan = FrmPendaftaranFailData.getListSubUrusan();
               		   		this.context.put("SenaraiSubUrusan", listSubUrusan);
               		   		
               		   		
		               			
               		    }
               	}
            	
	      	    else if ("kemaskiniFailNegeriLama".equals(action1)){
	    	    	
	      	    	String idFail = getParam("idFail");
	    	    	
	      	    	vm = "app/pfd/frmPendaftaranFailNegeriLama.jsp";
	      	    	context.put("mode", "kemaskini");
	      	    	context.put("readonly","disabled");
	      	    	context.put("disabled","disabled");
	            	context.put("readonlyNoFail","");
					context.put("readonlyTajukFail","");
				  	context.put("disabledNoFail","");
					context.put("disabledTajukFail","");
					context.put("readonlyNoFailRoot","");
    			  	context.put("disabledNoFailRoot","");
    			  	context.put("readonlyNoTurutan","");
    				context.put("disabledNoTurutan","");
    				context.put("readonlyNoJilid","");
    				context.put("disabledNoJilid","");
    				context.put("readonlyNoSubjaket","");
    				context.put("disabledNoSubjaket","");
					context.put("readonlyK","");
				  	context.put("disabledK","");
				  	context.put("readonlyDate","");
				  	context.put("disabledDate","");
	    		
	      	    	paparFailLama = FrmPendaftaranFailData.getPaparFailLama(idFail);
				
	      	    	Hashtable hA = (Hashtable)paparFailLama.get(0);
				
	    			this.context.put("idFail", hA.get("idFail"));
	    			this.context.put("noFail", hA.get("noFail"));
	    			this.context.put("noFailRoot", hA.get("noFailRoot"));
	    			this.context.put("noTurutan", hA.get("noTurutan"));
        			this.context.put("noJilid", hA.get("noJilid"));
        			this.context.put("noSubjaket", hA.get("noSubjaket"));
	    			this.context.put("tajukFailB", hA.get("tajukFail"));
	    			this.context.put("kabinetB", hA.get("kabinet"));
	    			this.context.put("idUrusan",hA.get("idUrusan"));
          		    this.context.put("idSubUrusan",hA.get("idSuburusan"));
	    			this.context.put("selectNegeriB",HTML.SelectNegeriA("socNegeriB",Utils.parseLong(hA.get("idNegeri").toString()),"disabled"));
	    			this.context.put("selectDaerahB",HTML.SelectDaerah("socDaerahB",Utils.parseLong(hA.get("idDaerah").toString()),"disabled"));
	    			this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
	    			this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),""));
	    			this.context.put("selectLokasiFailB",HTML.SelectLokasiFailNegeri("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"", user_negeri));
	    			this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
	    	    	
	    	    }
            	
        	  else if ("kemaskiniFailNegeriBaru".equals(action1)){
				
    	    	String idFail = getParam("idFail");
				
    	    	vm = "app/pfd/frmPendaftaranFailNegeriBaru.jsp";
    	    	this.context.put("mode", "kemaskini");
    	    	context.put("readonly","disabled");
				context.put("disabled","disabled");
            	context.put("readonlyNoFail","readonly");
				context.put("readonlyTajukFail","");
			  	context.put("disabledNoFail","disabled");
				context.put("disabledTajukFail","");
				context.put("readonlyK","");
			  	context.put("disabledK","");
				
          	
				paparFailBaru = FrmPendaftaranFailData.getPaparFailBaru(idFail);
  			
				Hashtable hA = (Hashtable)paparFailBaru.get(0);
  			
				this.context.put("idFail", hA.get("idFail"));
				this.context.put("noFail", hA.get("noFail"));
	  			this.context.put("tajukFailB", hA.get("tajukFail"));
	  			this.context.put("kabinetB", hA.get("kabinet"));
	  			this.context.put("idUrusan",hA.get("idUrusan"));
      		    this.context.put("idSubUrusan",hA.get("idSuburusan"));
	  			this.context.put("selectNegeriB",HTML.SelectNegeriA("socNegeriB",Utils.parseLong(hA.get("idNegeri").toString()),"disabled"));
	  			this.context.put("selectSeksyenB",HTML.SelectSeksyen("socSeksyenB",Utils.parseLong(hA.get("idSeksyen").toString()),"disabled"));
	  			this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
	  			this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),""));
	  			this.context.put("selectLokasiFailB",HTML.SelectLokasiFailNegeri("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"",user_negeri));
	  			this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
    	    	
    	    }
            	
        	  else if ("kemaskiniFailArkib".equals(action1)){
  				
          	    String idFail = getParam("idFail");
    				
          	    vm = "app/pfd/frmPendaftaranFailSeksyenArkib.jsp";
                	this.context.put("mode", "kemaskini");
                	context.put("readonly","");
    				context.put("disabled","");
    				context.put("readonlyNoFail","readonly");
    				context.put("disabledNoFail","disable");
                	
                	
    				
                	
                	paparFailBaru = FrmPendaftaranFailData.getPaparFailArkib(idFail);
        			
        			Hashtable hA = (Hashtable)paparFailBaru.get(0);
        			this.context.put("idFail", hA.get("idFail"));
      			this.context.put("noFailArkib", hA.get("noFail"));
      			this.context.put("noFailLama", hA.get("noFailLama"));
      			this.context.put("tajukFailC", hA.get("tajukFail"));
      			this.context.put("kabinetC", hA.get("kabinet"));
           	 	this.context.put("idUrusan", hA.get("ID_URUSAN"));
           	 	this.context.put("namaUrusan", hA.get("KOD_URUSAN")+"-"+hA.get("NAMA_URUSAN"));
           	 	this.context.put("namaSuburusan", hA.get("KOD_SUBURUSAN")+"-"+ hA.get("NAMA_SUBURUSAN"));
           	 	this.context.put("namaSubSuburusan",hA.get("KOD_SUBSUBURUSAN")+"-"+ hA.get("NAMA_SUBSUBURUSAN"));
           	 	this.context.put("namaSubSubSuburusan", hA.get("KOD_SUBSUBSUBURUSAN")+"-"+hA.get("NAMA_SUBSUBSUBURUSAN"));
           	 	
           	 	view_Seksyen(session);
	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
	         	Hashtable hB = (Hashtable) paparSeksyen.get(0);
	         	idSeksyenC = String.valueOf(hB.get("id_seksyen"));
	         	kodSeksyen = String.valueOf(hB.get("kod_seksyen"));
	         	kod_negeri = String.valueOf(hB.get("kod_negeri"));
	         	namaSeksyen = String.valueOf(hB.get("nama_seksyen"));
	         	String id_negeri = String.valueOf(hB.get("id_negeri"));
	         	String nama_negeri = String.valueOf(hB.get("nama_negeri"));
	         	
	         	context.put("idSeksyen",idSeksyenC);
	         	
	         	context.put("idNegeri",id_negeri);
	         	
      	 	      	 	
	         	view_unit(session);
	         	paparUnit = FrmLogDokumenData.getDataUnit();
	         	Hashtable hZ = (Hashtable)paparUnit.get(0);
	         	String nama_pejabat = String.valueOf(hZ.get("NAMA_PEJABAT"));
	         	context.put("seksyen",nama_negeri + "-"+ nama_pejabat);
        			
           	 	this.context.put("selectSeksyenC",HTML.SelectSeksyen("socSeksyenC",Utils.parseLong(hA.get("idSeksyen").toString()),"disabled"));
        			this.context.put("selectTarafC",HTML.SelectTarafKeselamatan("socTarafC",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
       			this.context.put("selectStatusC",HTML.SelectStatusFailB("socStatusC",Utils.parseLong(hA.get("idStatus").toString()),null));
       			this.context.put("selectLokasiFailC",HTML.SelectLokasiFail("socLokasiFailC",Utils.parseLong(hA.get("idLokasi").toString()),null));
       			this.context.put("tarikhDaftarC", hA.get("tarikhDaftar"));
       			
       			if(!"".equals(id_FailLama)){
    				
    				
     	 			failLama = getNoFailLama(id_FailLama);
    				Hashtable hF = (Hashtable)failLama.get(0);
    				context.put("noFailLama",hF.get("NO_FAIL"));
    				
    			}
       	
          	    	
          	    }  	  
        	  else if ("updateFailNegeriLama".equals(action1)){
        		  	
        		String idFail = getParam("idFail");  
        	    	
      	    	vm = "app/pfd/frmPendaftaranFailNegeriLama.jsp";
  	  			this.context.put("mode", "paparSemua");
              	context.put("readonly","readonly");
  				context.put("disabled","disabled");
              	context.put("readonlyNoFail","readonly");
  				context.put("readonlyTajukFail","readonly");
  			  	context.put("disabledNoFail","disabled");
  				context.put("disabledTajukFail","disabled");
  				context.put("readonlyNoFailRoot","readonly");
			  	context.put("disabledNoFailRoot","disabled");
			  	context.put("readonlyNoTurutan","readonly");
				context.put("disabledNoTurutan","disabled");
				context.put("readonlyNoJilid","readonly");
				context.put("disabledNoJilid","disabled");
				context.put("readonlyNoSubjaket","readonly");
				context.put("disabledNoSubjaket","disabled");
  				context.put("readonlyK","readonly");
  			  	context.put("disabledK","disabled");
  			  	context.put("readonlyDate","readonly");
			  	context.put("disabledDate","disabled");
      	    	
  				updateFailLama(session);
  		
  	      		paparFailLama = FrmPendaftaranFailData.getPaparFailLama(idFail);
  	  			
  	  			Hashtable hA = (Hashtable)paparFailLama.get(0);
  	  			
  	  			this.context.put("idFail", hA.get("idFail"));
  	  			this.context.put("noFail", hA.get("noFail"));
  	  			this.context.put("noFailRoot", hA.get("noFailRoot"));
  	  			this.context.put("noTurutan", hA.get("noTurutan"));
  	  			this.context.put("noJilid", hA.get("noJilid"));
  	  			this.context.put("noSubjaket", hA.get("noSubjaket"));
      			this.context.put("tajukFailB", hA.get("tajukFail"));
      			this.context.put("kabinetB", hA.get("kabinet"));
      			this.context.put("idUrusan",hA.get("idUrusan"));
      		    this.context.put("idSubUrusan",hA.get("idSuburusan"));
      			this.context.put("selectNegeriB",HTML.SelectNegeriA("socNegeriB",Utils.parseLong(hA.get("idNegeri").toString()),"disabled"));
      			this.context.put("selectDaerahB",HTML.SelectDaerah("socDaerahB",Utils.parseLong(hA.get("idDaerah").toString()),"disabled"));
      			this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
      			this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),"disabled"));
      			this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"disabled"));
      			this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
      	    }
            	
      	    else if ("updateFailNegeriBaru".equals(action1)){
    			
  	    	String idFail = getParam("idFail");  
  	    	
  	    	vm = "app/pfd/frmPendaftaranFailNegeriBaru.jsp";
  			this.context.put("mode", "paparSemua");
        	context.put("readonly","readonly");
			context.put("disabled","disabled");
        	context.put("readonlyNoFail","readonly");
			context.put("readonlyTajukFail","readonly");
		  	context.put("disabledNoFail","disabled");
			context.put("disabledTajukFail","disabled");
			context.put("readonlyK","readonly");
		  	context.put("disabledK","disabled");

  			updateFailBaru(session);

        	paparFailBaru = FrmPendaftaranFailData.getPaparFailBaru(idFail);
			
			Hashtable hA = (Hashtable)paparFailBaru.get(0);
			
			this.context.put("idFail", hA.get("idFail"));
			this.context.put("noFail", hA.get("noFail"));
			this.context.put("tajukFailB", hA.get("tajukFail"));
			this.context.put("idUrusan",hA.get("idUrusan"));
  		    this.context.put("idSubUrusan",hA.get("idSuburusan"));
			this.context.put("selectNegeriB",HTML.SelectNegeriA("socNegeriB",Utils.parseLong(hA.get("idNegeri").toString()),"disabled"));
			this.context.put("selectSeksyenB",HTML.SelectSeksyen("socSeksyenB",Utils.parseLong(hA.get("idSeksyen").toString()),"disabled"));
			this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
			this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),"disabled"));
			this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"disabled"));
			this.context.put("kabinetB", hA.get("kabinet"));
			this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
    	}
            	
      	  else if ("updateFailNegeriLama".equals(action1)){
  		  	
      		String idFail = getParam("idFail");  
      	    	
    	    	vm = "app/pfd/frmPendaftaranFailNegeriLama.jsp";
	  			this.context.put("mode", "paparSemua");
            	context.put("readonly","readonly");
				context.put("disabled","disabled");
            	context.put("readonlyNoFail","readonly");
				context.put("readonlyTajukFail","readonly");
			  	context.put("disabledNoFail","disabled");
				context.put("disabledTajukFail","disabled");
				context.put("readonlyNoFailRoot","readonly");
			  	context.put("disabledNoFailRoot","disabled");
			  	context.put("readonlyNoTurutan","readonly");
				context.put("disabledNoTurutan","disabled");
				context.put("readonlyNoJilid","readonly");
				context.put("disabledNoJilid","disabled");
				context.put("readonlyNoSubjaket","readonly");
				context.put("disabledNoSubjaket","disabled");
				context.put("readonlyK","readonly");
			  	context.put("disabledK","disabled");
			  	context.put("readonlyDate","readonly");
			  	context.put("disabledDate","disabled");
    	    	
				updateFailLama(session);
		
	      		paparFailLama = FrmPendaftaranFailData.getPaparFailLama(idFail);
	  			
	  			Hashtable hA = (Hashtable)paparFailLama.get(0);
	  			
	  			this.context.put("idFail", hA.get("idFail"));
	  			this.context.put("noFail", hA.get("noFail"));
	  			this.context.put("noFailRoot", hA.get("noFailRoot"));
	  			this.context.put("noTurutan", hA.get("noTurutan"));
	  			this.context.put("noJilid", hA.get("noJilid"));
	  			this.context.put("noSubjaket", hA.get("noSubjaket"));
    			this.context.put("tajukFailB", hA.get("tajukFail"));
    			this.context.put("kabinetB", hA.get("kabinet"));
    			this.context.put("idUrusan",hA.get("idUrusan"));
      		    this.context.put("idSubUrusan",hA.get("idSuburusan"));
    			this.context.put("selectNegeriB",HTML.SelectNegeriA("socNegeriB",Utils.parseLong(hA.get("idNegeri").toString()),"disabled"));
    			this.context.put("selectDaerahB",HTML.SelectDaerah("socDaerahB",Utils.parseLong(hA.get("idDaerah").toString()),"disabled"));
    			this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(hA.get("idTaraf").toString()),"disabled"));
    			this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(hA.get("idStatus").toString()),"disabled"));
    			this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(hA.get("idLokasi").toString()),"disabled"));
    			this.context.put("tarikhDaftarB", hA.get("tarikhDaftar"));
    	    }
            	
      	 else if ("updateFailSeksyenArkib".equals(action1)){
 	    	
 	    	String idFail = getParam("idFail");  
 	    	
 	    	vm = "app/pfd/frmPendaftaranFailSeksyenArkib.jsp";
 			this.context.put("mode", "papar");
 			context.put("readonly","readonly");
 			context.put("disabled","disabled");
 			context.put("readonlyNoFail","readonly");
 		  	context.put("disabledNoFail","disabled");
 			

 		  	updateFailArkib(session);

 			paparFailArkib = FrmPendaftaranFailData.getPaparFailArkib(idFail);
 			
 			Hashtable hA = (Hashtable)paparFailArkib.get(0);
 			
 			this.context.put("idFail", hA.get("idFail"));
 			this.context.put("noFailArkib", hA.get("noFail"));
 			this.context.put("noFailLama", hA.get("noFailLama"));
 			this.context.put("tajukFailC", hA.get("tajukFail"));
 			this.context.put("kabinetC", hA.get("kabinet"));
      	 	this.context.put("idUrusan", hA.get("ID_URUSAN"));
      	 	this.context.put("namaUrusan", hA.get("KOD_URUSAN")+"-"+hA.get("NAMA_URUSAN"));
      	 	this.context.put("namaSuburusan", hA.get("KOD_SUBURUSAN")+"-"+ hA.get("NAMA_SUBURUSAN"));
      	 	this.context.put("namaSubSuburusan",hA.get("KOD_SUBSUBURUSAN")+"-"+ hA.get("NAMA_SUBSUBURUSAN"));
      	 	this.context.put("namaSubSubSuburusan", hA.get("KOD_SUBSUBSUBURUSAN")+"-"+hA.get("NAMA_SUBSUBSUBURUSAN"));
 			this.context.put("selectSeksyenC",HTML.SelectSeksyen("socSeksyenC",Utils.parseLong(hA.get("idSeksyen").toString()),"disabled"));
 			this.context.put("aktivitiValue", hA.get("idAktiviti"));
      	    if (hA.get("idAktiviti").equals("0")){
      	    	this.context.put("modeAktiviti", "tiada");
      	 		this.context.put("aktiviti1","tiada");
      	 		this.context.put("radioCheck1", "checked");
      	 		this.context.put("radioCheck2", "");
      	 		this.context.put("readOnly","disabled");
      	    }
      	    else
      	    {
      	    	this.context.put("modeAktiviti", "ada");
      	 		this.context.put("aktiviti1","ada");
      	 		this.context.put("radioCheck1", "");
      	 		this.context.put("radioCheck2", "checked");
      	 		this.context.put("readOnly","disabled");
      	 		
      	 		
      	    }
      	    
      	  FrmPendaftaranFailData.getTaraf(hA.get("idTaraf").toString());
       	paparTaraf = FrmPendaftaranFailData.getListTaraf();
       	Hashtable hC = (Hashtable) paparTaraf.get(0);
       	
       	if (hC.get("KOD_TARAF_KESELAMATAN").equals("")){
       		this.context.put("taraf", hC.get("TARAF_KESELAMATAN"));
       	}
       	else{
       		this.context.put("taraf", hC.get("KOD_TARAF_KESELAMATAN")+"-"+hC.get("TARAF_KESELAMATAN"));
       	}
       	
       	FrmPendaftaranFailData.getStatusFail(hA.get("idStatus").toString());
   	 	paparStatus = FrmPendaftaranFailData.getListStatus();
   	 	Hashtable hD = (Hashtable) paparStatus.get(0);
   	 	
   	 	this.context.put("status", hD.get("KETERANGAN"));
   	 	
   	 	if(!hA.get("idLokasi").equals("")){
   	 	
   	 	FrmPendaftaranFailData.getLokasiFail(hA.get("idLokasi").toString());
   	 	paparStatus = FrmPendaftaranFailData.getListLokasi();
   	 	Hashtable hE = (Hashtable) paparStatus.get(0);
   	 	
   	 	this.context.put("lokasi", hE.get("LOKASI_FAIL"));
   	 	}
   	 	else{
   	 		this.context.put("lokasi", "");
   	 	}
   	 	this.context.put("tarikhDaftarC", hA.get("tarikhDaftar"));
 	    }	

      	  	else if ("hapus".equals(action1)){
   			
		    	String idFail = getParam("idFail");  
		    	context.put("hapus", "true");
		    	
		    	logic.hapus(idFail);
		    	
		    	if("16".equalsIgnoreCase(user_negeri)){
		    		vm = "app/pfd/frmPendaftaranFailSeksyenBaru.jsp";
              	this.context.put("mode", "paparUpdate");
              	context.put("action1","tabFailSeksyenBaru");
             	context.put("readonly","readonly");
 				context.put("disabled","disabled");
             	context.put("readonlyNoFail","readonly");
 				context.put("readonlyTajukFail","readonly");
 			  	context.put("disabledNoFail","disabled");
 				context.put("disabledTajukFail","disabled");
 				context.put("disabledNoFail","disabled");
 				context.put("disabledTajukFail","");
 				context.put("readonlyK","readonly");
 			  	context.put("disabledK","disabled");
		    	}
		    	else{
		    		vm = "app/pfd/frmPendaftaranFailNegeriBaru.jsp";
              	this.context.put("mode", "paparUpdate");
              	context.put("action1","tabFailNegeriBaru");
             	context.put("readonly","readonly");
 				context.put("disabled","disabled");
             	context.put("readonlyNoFail","readonly");
 				context.put("readonlyTajukFail","readonly");
 			  	context.put("disabledNoFail","disabled");
 				context.put("disabledTajukFail","disabled");
		    	}

      	 	}	
            	
            else
            {
            	
		    		String user_name = (String)session.getAttribute("_portal_username");
		    		user_id = (String)session.getAttribute("_ekptg_user_id");
		    		user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
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
		    	
		    		vm = "app/pfd/frmDaftarFail.jsp";
	
		    		this.context.put("JumlahFail",FrmDaftarFailData.totalFail(session));
	  	    	
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
					if (!"".equals(getParam("txtTarikhDaftar")))
						tarikhDaftar = getParam("txtTarikhDaftar");
					if (!"".equals(getParam("txtNoFailLama")))
		  	    		noFailLama = getParam("txtNoFailLama");
					
					FrmDaftarFailData.setCarianFail(noFail,noFailLama,tajukFail,negeri,seksyen,status,tarikhDaftar, user_id, myrole, user_negeri, id_seksyen); 
		 	    	list = FrmDaftarFailData.getList();
		 	    	this.context.put("SenaraiFail", list);
		 	    	this.context.put("selectNegeriD",HTML.SelectNegeriA("socNegeriD",Utils.parseLong(negeri),""));
			   
		 	    	this.context.put("selectSeksyenD",HTML.SelectSeksyen("socSeksyenD",Utils.parseLong(seksyen),""));
//		 	    	this.context.put("selectUnit", HTML.SelectUnitByIdNegeri(user_negeri,"socUnit", Utils.parseLong(unit), "",""));
			    	this.context.put("selectStatusD",HTML.SelectStatusFailB("socStatusD",Utils.parseLong(status),""));
			    	this.context.put("txtNoFail", noFail);
			    	this.context.put("txtNoFailLama", noFailLama);
			    	this.context.put("txtTajukFail", tajukFail);
			    	this.context.put("txtTarikhDaftar", tarikhDaftar);
			    	this.context.put("myrole", myrole);

			    	
			    	
			    	setupPage(session,action1,list);
            	}  
            }
            
                context.put("action1",action1);
                context.put("id_FailArkib",id_FailArkib);
            	return vm;
       
    }

		private void view_unit(HttpSession session) throws Exception {
		
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			FrmLogDokumenData.setUnit(user_id);
		
	}

		private Vector getNoFailLama(String idFailLama) {

		Db db = null;
		Vector result = null;
		String sql = "";
		Hashtable h = null;
		boolean setLimit = true;
		try {
			db = new Db();
			result = new Vector();
			Statement stmt = db.getStatement();
			sql = "SELECT B.ID_FAIL, B.NO_FAIL, B.TAJUK_FAIL FROM TBLPFDFAIL B WHERE B.ID_FAIL = "+idFailLama;

			
			System.out.println("sql--"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 1;
			while(rs.next()) {
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_FAIL",checkIsNull(rs.getString("ID_FAIL")));
				h.put("NO_FAIL",checkIsNull(rs.getString("NO_FAIL")));
				h.put("TAJUK_FAIL",checkIsNull(rs.getString("TAJUK_FAIL")));
				
				bil++;
				
				result.add(h);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return result;
	}

	public String checkIsNull(String txt) {
		if (txt == null) return "";
		else return txt;
	}
	

	public static void saveMappingFile(String idFailAsal, String idFailArkib, HttpSession session) throws Exception {
		 Db db = new Db();
		    String sql = "";
		    try
		    {
		    	  long idFailMapping = DB.getNextID("TBLPFDFAILMAPPING_SEQ");
		    	  String user_id = (String)session.getAttribute("_ekptg_user_id");
			    
			     
			     			    			      
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();

			      r.add("id_FailMapping", idFailMapping);
			      r.add("id_FailArkib", idFailArkib);
			      r.add("id_FailAsal", idFailAsal);
			      r.add("id_Masuk",user_id);
			      r.add("tarikh_Masuk",r.unquote("sysdate")); 
			  
			      sql = r.getSQLInsert("tblpfdfailmapping");
			      
			      System.out.println("sql save mapping--"+sql);
			      
			      stmt.executeUpdate(sql);
			      
			     
			      
			    } finally {
			      if (db != null) db.close();
			    }
	}

	private void updateNoTurutan(String idFail) {
		
		FrmPendaftaranFailData.updateNoTurutan(idFail);		
	}


	private void view_Seksyen(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmLogDokumenData.setSeksyen(user_id);
		
	}


	private void view_fail(HttpSession session) throws Exception {
		String id = getParam("idFail");
	    FrmKemaskiniDokumenData.setDataFail(id);
	   
	}


	private String simpanFailLamaSeksyen(HttpSession session) throws Exception {

		String user_id = (String)session.getAttribute("_ekptg_user_id");
		//String id_Status = getParam("socStatusA"));
		String noFail = getParam("noFail");
		String noFailRoot = getParam("noFailRoot");
		String tajuk_Fail = getParam("txtTajukFailB");
		String id_Tarafkeselamatan = getParam("socTarafB");
		String id_Lokasi = getParam("socLokasiFailB");
		String kabinet = getParam("txtKabinetB");
		Integer id_Seksyen = Integer.parseInt(getParam("idSeksyen")==""?"0" : getParam("idSeksyen"));
		Integer id_Urusan = Integer.parseInt(getParam("socUrusanB")==""?"0" : getParam("socUrusanB"));
		Integer id_Suburusan = Integer.parseInt(getParam("socSuburusanB")==""?"0" : getParam("socSuburusanB"));
	//	Integer id_SubSuburusan = Integer.parseInt(getParam("socSubSuburusanB")==""?"0" : getParam("socSubSuburusanB"));
		Integer noTurutan = Integer.parseInt(getParam("noTurutan")==""?"0" : getParam("noTurutan"));
    	Integer noJilid = Integer.parseInt(getParam("noJilid")==""?"0" : getParam("noJilid"));
    	Integer noSubjaket = Integer.parseInt(getParam("noSubjaket")==""?"0" : getParam("noSubjaket"));
		String tarikhDaftar = getParam("txtTarikhDaftarB");
		
	
		Hashtable h = new Hashtable();
 	    h.put("no_Fail", noFail);
 	    h.put("noFailRoot", noFailRoot);
 	    h.put("noTurutan", noTurutan);
 	    h.put("noJilid", noJilid);
 	  	h.put("noSubjaket", noSubjaket);
 	    h.put("id_Negeri", 16); //hardcode negeri putrajaya
 	    h.put("id_Seksyen", id_Seksyen);
 	    h.put("id_Urusan", id_Urusan);
 	    h.put("id_Suburusan", id_Suburusan);
 //	    h.put("id_SubSuburusan", id_SubSuburusan);
 	    h.put("id_Tarafkeselamatan", id_Tarafkeselamatan);
 	    h.put("tajuk_Fail", tajuk_Fail);
 	    h.put("id_Status", "7");
 	    h.put("id_Lokasi", id_Lokasi);
	 	h.put("kabinet",kabinet );
 	    h.put("tarikhDaftar", tarikhDaftar);
 	    h.put("flag_jenis_Fail",3);
 	    h.put("view_Fail",1);
 	    h.put("id_Masuk",user_id);
 	    
 	    return FrmPendaftaranFailData.addFailLamaSeksyen(h);
	}
	
	private String simpanFailLamaNegeri(HttpSession session) throws Exception {

		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idFail = getParam("idFail");
		String noFailRoot = getParam("noFailRoot");
    	String noTurutan = getParam("noTurutan");
    	String noJilid = getParam("noJilid");
    	String noSubjaket = getParam("noSubjaket");
	
		Hashtable h = new Hashtable();
 	    h.put("no_Fail", getParam("noFail"));
 	    h.put("noFailRoot", noFailRoot);
 	    h.put("noTurutan", noTurutan);
 	    h.put("noJilid", noJilid);
 	  	h.put("noSubjaket", noSubjaket);
 	    h.put("id_Negeri", Integer.parseInt(getParam("socNegeriB")));
 	    h.put("id_Daerah",Integer.parseInt(getParam("socDaerahB")==""?"0" : getParam("socDaerahB")));
 	    h.put("id_Seksyen", Integer.parseInt(getParam("idSeksyen")));
 	    h.put("id_Urusan", Integer.parseInt(getParam("socUrusanB")));
 	    h.put("id_Suburusan", getParam("socSuburusanB"));
 	    h.put("id_Tarafkeselamatan", getParam("socTarafB"));
 	    h.put("tajuk_Fail", getParam("txtTajukFailB"));
 	    h.put("id_Status", "7");
 	    h.put("id_Lokasi", getParam("socLokasiFailB"));
	 	h.put("kabinet", getParam("txtKabinetB"));
 	    h.put("tarikhDaftar", getParam("txtTarikhDaftarB"));
 	    h.put("flag_jenis_Fail",3);
 	    h.put("view_Fail",2);
 	    h.put("id_Masuk",user_id);

 	    return FrmPendaftaranFailData.addFailLamaNegeri(h);
	}

	private String simpanFailBaruSeksyen(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
	    String user_role = (String) session.getAttribute("_portal_role");
	    String user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
	    String id_Seksyen = getParam("idSeksyen");
		String id_Urusan = getParam("socUrusanB");
		String id_Suburusan = getParam("socSuburusanB");
		String id_SubSubUrusan = getParam("socSubSubUrusanB");

	
  		 Hashtable h = new Hashtable();
  		 h.put("id_Negeri", "16");//hardcode negeri putrajaya
  		 h.put("id_Seksyen", id_Seksyen);
  		 h.put("id_Urusan", id_Urusan);
  		 h.put("id_Suburusan", id_Suburusan);
  		 h.put("id_SubSubUrusan", id_SubSubUrusan);
  		 h.put("id_Aktiviti", getParam("aktivitiValue"));
  		 h.put("id_Tarafkeselamatan", getParam("socTarafB"));
  		 h.put("tajuk_Fail", getParam("txtTajukFailB"));
  	     h.put("id_Status", "7");
	 	 h.put("id_Lokasi", getParam("socLokasiFailB"));
	 	 h.put("kabinet", getParam("txtKabinetB"));
	 	 h.put("tarikhDaftar", getParam("txtTarikhDaftarB"));
  		 h.put("flag_jenis_Fail",1);
  		 h.put("view_Fail",3);
  		 h.put("id_Masuk",user_id);
 		 
  		 return FrmPendaftaranFailData.addFailBaruSeksyen(h);
	}

	private String simpanFailBaruNegeri(HttpSession session) throws Exception {
		
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idFail = getParam("idFail");
	
  		 Hashtable h = new Hashtable();
  		 h.put("id_Seksyen", getParam("idSeksyen"));
  		 h.put("id_Negeri", getParam("socNegeriB"));
  		 h.put("id_Daerah",getParam("socDaerahB")==""?"0" : getParam("socDaerahB"));
  		 h.put("id_Urusan", getParam("socUrusanB"));
  		 h.put("id_Suburusan", getParam("socSuburusanB"));
  		 h.put("id_SubSubUrusan", getParam("socSubSubUrusanB"));
  		 h.put("id_Aktiviti", getParam("aktivitiValue"));
  		// h.put("aktiviti", getParam("txtAktiviti"));
  		 h.put("id_Tarafkeselamatan", getParam("socTarafB"));
  		 h.put("tajuk_Fail", getParam("txtTajukFailB"));
  	     h.put("id_Status", "7");
	 	 h.put("id_Lokasi", getParam("socLokasiFailB"));
	 	 h.put("kabinet", getParam("txtKabinetB"));
	 	 h.put("tarikhDaftar", getParam("txtTarikhDaftarB"));
  		 h.put("flag_jenis_Fail",1);
  		 h.put("view_Fail",4);
  		 h.put("id_Masuk",user_id);
  		 
 		 
  		 return FrmPendaftaranFailData.addFailBaruNegeri(h);
	}
	private String simpanFailArkib(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
	    String user_role = (String) session.getAttribute("_portal_role");
	    String user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
	    String id_Seksyen = getParam("idSeksyen");
		String id_Urusan = getParam("idUrusan");
		String id_Suburusan = getParam("idSuburusan");
		String id_SubSubUrusan = getParam("idSubSuburusan");
		String id_SubSubSubUrusan = getParam("idSubSubSuburusan");
//		String id_pejabatjkptg = getParam("id_pejabatjkptg");

	
  		 Hashtable h = new Hashtable();
  		 h.put("no_fail",getParam("txtNoFailArkib"));
  		 h.put("id_Negeri", user_negeri);
  		 h.put("id_Seksyen", id_Seksyen);
//  		 h.put("id_pejabatjkptg", id_pejabatjkptg);
  		 h.put("id_Urusan", id_Urusan);
  		 h.put("id_Suburusan", id_Suburusan);
  		 h.put("id_SubSubUrusan", id_SubSubUrusan);
  		 h.put("id_SubSubSubUrusan", id_SubSubSubUrusan);
  		 h.put("id_Aktiviti", getParam("aktivitiValue"));
  		 h.put("id_Tarafkeselamatan", getParam("socTarafC"));
  		 h.put("tajuk_Fail", getParam("txtTajukFailC"));
  	     h.put("id_Status", "7");
	 	 h.put("id_Lokasi", getParam("socLokasiFailC"));
	 	 h.put("kabinet", getParam("txtKabinetC"));
	 	 h.put("tarikhDaftar", sdf.format(now));
  		 h.put("flag_jenis_Fail",4);
  		 h.put("view_Fail",5);
  		 h.put("id_Masuk",user_id); 		 
  		 h.put("id_FailLama",getParam("id_FailLama"));
  		 
 		 
  		 return FrmPendaftaranFailData.addFailArkib(h);
	}
	private String updateFailArkib(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idFail = getParam("idFail");
		String kabinet = getParam("txtKabinetC");
	
		Hashtable h = new Hashtable();
		h.put("idFail",idFail);	
 	    h.put("tajukFailC", getParam("txtTajukFailC"));
// 	    h.put("selectStatusC", getParam("socStatusC"));
 	    h.put("selectLokasiFailC", getParam("socLokasiFailC"));
 	    h.put("kabinet", kabinet);
 	    h.put("idMasuk",user_id);
 	    
 	    
 	   
 	    
 	    if(!getParam("id_FailLama").equals(null)){
 	    	h.put("idFailLama", getParam("id_FailLama"));
 	    }

 	    
 	    FrmKemaskiniFailData.updateFailArkib(h);
 	    
 	   return idFail;
	}

	private String updateFailBaru(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idFail = getParam("idFail");
		String kabinet = getParam("txtKabinetB");
	
		Hashtable h = new Hashtable();
		h.put("idFail",idFail);	
// 	    h.put("noFail", getParam("txtNoFail"));
// 	    h.put("selectNegeriB", Integer.parseInt(getParam("socNegeriB")));
// 	    h.put("selectSeksyenB", Integer.parseInt(getParam("socSeksyenB")));
// 	    h.put("selectUrusanB", Integer.parseInt(getParam("socUrusanB")));
// 	    h.put("selectSubUrusanB", getParam("socSuburusanB"));
// 	    h.put("selectTarafB", getParam("socTarafB"));
 	    h.put("tajukFailB", getParam("txtTajukFailB"));
 	    h.put("selectStatusB", getParam("socStatusB"));
 	    h.put("selectLokasiFailB", getParam("socLokasiFailB"));
 	    h.put("kabinet", kabinet);
// 	    h.put("tarikhDaftar", getParam("txtTarikhDaftarB"));
// 	    h.put("flag_Fail",1);
 	    h.put("idMasuk",user_id);

 	   System.out.println(h);
 	    
 	    FrmKemaskiniFailData.updateFailBaru(h);
 	    
 	   return idFail;
	}


	private String updateFailLama(HttpSession session) throws Exception {

		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idFail = getParam("idFail");
		String kabinet = getParam("txtKabinetB");
		String noFailRoot = getParam("noFailRoot");
		String noTurutan = getParam("noTurutan");
    	String noJilid = getParam("noJilid");
    	String noSubjaket = getParam("noSubjaket");
		
		Hashtable h = new Hashtable();
		h.put("idFail",idFail);	
 	    h.put("noFail", getParam("noFail"));
 	    h.put("noFailRoot", getParam("noFailRoot"));
 	    h.put("noTurutan", noTurutan);
	    h.put("noJilid", noJilid);
	  	h.put("noSubjaket", noSubjaket);
// 	    h.put("selectNegeriA", Integer.parseInt(getParam("socNegeriA")));
// 	    h.put("selectSeksyenA", Integer.parseInt(getParam("socSeksyenA")));
// 	    h.put("selectUrusanA", Integer.parseInt(getParam("socUrusanA")));
// 	    h.put("selectSubUrusanA", getParam("socSuburusanA"));
// 	    h.put("selectTarafB", getParam("socTarafB"));
 	    h.put("tajukFailB", getParam("txtTajukFailB"));
 	    h.put("selectStatusB", getParam("socStatusB"));
 	    h.put("selectLokasiFailB", getParam("socLokasiFailB"));
 	    h.put("kabinet", kabinet);
 	    h.put("tarikhDaftar", getParam("txtTarikhDaftarB"));
// 	    h.put("flag_Fail",1);
 	    h.put("idMasuk",user_id);

 	   System.out.println(h);
 	    
 	    FrmKemaskiniFailData.updateFailLama(h);
 	    
 	    return idFail;

		
	}


	private String updateFail(HttpSession session) throws Exception {
		
		Hashtable h = new Hashtable();
		
		String user_id = (String)session.getAttribute("_ekptg_user_id");

		String idFail = getParam("idFail");

		System.out.println("id Fail :;"+idFail);
		
		 h.put("idFail",idFail);	
		 h.put("noFail",getParam("txtNoFail"));
		 h.put("selectNegeriC",getParam("socNegeriC"));
		 h.put("selectSeksyenC",getParam("socSeksyenC"));
		 h.put("selectUrusanC",getParam("socUrusanC"));
		 h.put("selectSubUrusanC", getParam("socSuburusanC"));
		 h.put("selectTarafC",getParam("socTarafC"));
		 h.put("selectStatusC",getParam("socStatusC"));
		 h.put("tajukFail",getParam("txtTajukFail"));
		 h.put("lokasiFail",getParam("socLokasiFail"));
		 h.put("faharasat",getParam("txtFaharasat"));
		 h.put("tarikhDaftar",getParam("txtTarikhDaftar"));
		 h.put("user_Name",getParam("txdTarikhDaftar"));
		 h.put("idMasuk", user_id);
		 
	
		
		 FrmKemaskiniFailData.updateFail(h);
		 
		 return idFail;
		
	}


	public void setupPage(HttpSession session,String action1,Vector list) {
		try {
			
			System.out.println("totalRecords: "+list.size());
			
			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null
					|| this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10
						: getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage");
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

			Paging paging = new Paging(session, list, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1; // reset page number
			this.context.put("SenaraiFail", paging.getPage(page));
			this.context.put("page", new Integer(page));
			this.context.put("itemsPerPage", new Integer(itemsPerPage));
			this.context.put("totalPages", new Integer(paging.getTotalPages()));
			this.context.put("startNumber", new Integer(paging.getTopNumber()));
			this.context.put("isFirstPage", new Boolean(paging.isFirstPage()));
			this.context.put("isLastPage", new Boolean(paging.isLastPage()));

		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error", e.getMessage());
		}
	}	
	
	private void view_mode(HttpSession session) throws Exception {
    	int id = Integer.parseInt(getParam("idFail"));
	    FrmKemaskiniFailData.setData(id);
	   
	  }
    private void edit_mode(HttpSession session) throws Exception {
    	int id = Integer.parseInt(getParam("idFail"));
	    FrmKemaskiniFailData.setData(id);
 
	  }
    
    
//	private String simpanFailLama(HttpSession session) throws Exception {
//		
//    		String user_id = (String)session.getAttribute("_ekptg_user_id");
//    		String idFail = getParam("idFail");
//    	
//    		Hashtable h = new Hashtable();
//	 	    h.put("no_Fail", getParam("txtNoFail"));
//	 	    h.put("id_Negeri", Integer.parseInt(getParam("socNegeriA")));
//	 	    h.put("id_Seksyen", Integer.parseInt(getParam("socSeksyenA")));
//	 	    h.put("id_Urusan", Integer.parseInt(getParam("socUrusanA")));
//	 	    h.put("id_Suburusan", getParam("socSuburusanA"));
//	 	    h.put("id_Tarafkeselamatan", getParam("socTarafA"));
//	 	    h.put("tajuk_Fail", getParam("txtTajukFailA"));
//	 	    h.put("id_Status", getParam("socStatusA"));
//	 	    h.put("id_Lokasi", getParam("socLokasiFailA"));
//	 	    h.put("id_Faharasat", getParam("socFaharasatA"));
//	 	    h.put("tarikhDaftar", getParam("txtTarikhDaftarA"));
//	 	    h.put("flag_Fail",1);
//	 	    h.put("id_Masuk",user_id);
//
//	 	    return FrmPendaftaranFailData.addFailLama(h);
//	}
	
//	private String simpanFailBaru(HttpSession session) throws Exception {
//		
//		String user_id = (String)session.getAttribute("_ekptg_user_id");
//		String idFail = getParam("idFail");
//	
//  		 Hashtable h = new Hashtable();
//  		 h.put("id_Negeri", Integer.parseInt(getParam("socNegeriB")));
//  		 h.put("id_Seksyen", Integer.parseInt(getParam("socSeksyenB")));
//  		 h.put("id_Urusan", Integer.parseInt(getParam("socUrusanB")));
//  		 h.put("id_Suburusan", getParam("socSuburusanB"));
//  		 h.put("id_Tarafkeselamatan", getParam("socTarafB"));
//  		 h.put("tajuk_Fail", getParam("txtTajukFailB"));
//  		 h.put("id_Status", getParam("socStatusB"));
//	 	 h.put("id_Lokasi", getParam("socLokasiFailB"));
//	 	 h.put("id_Faharasat", getParam("socFaharasatB"));
//	 	 h.put("tarikhDaftar", getParam("txtTarikhDaftarB"));
//  		 h.put("flag_Fail",1);
//  		 h.put("id_Masuk",user_id);
// 		 
//  		 return FrmPendaftaranFailData.addFailBaru(h);
//	}
	 private void simpanKemaskiniFail(HttpSession session) throws Exception
	  {
		 	String user_id = (String)session.getAttribute("_ekptg_user_id");
		    Hashtable h = new Hashtable();
		    
		    h.put("id_Fail", Integer.parseInt(getParam("idFail")));
		    h.put("tajuk_Fail", getParam("txtTajukFail"));
		    h.put("id_Lokasifail", getParam("socLokasiC")==null?0:getParam("socLokasiC"));
		    h.put("id_Faharasat", getParam("socFaharasatC")==null?0:getParam("socFaharasatC"));
		    h.put("id_Kemaskini",user_id);
		    FrmKemaskiniFailData.update(h);
    
	  }
	 
	 
	 
}
