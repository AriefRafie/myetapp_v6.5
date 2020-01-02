package ekptg.view.sms;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import ekptg.helpers.Paging;
import ekptg.model.sms.PopupSMSHistoryData;
import ekptg.model.sms.mySMSData;

public class mySMS extends AjaxBasedModule{	
	/**
	 * 
	 */
	static Logger myLog = Logger.getLogger(ekptg.view.sms.mySMS.class);
	private static final long serialVersionUID = 510855215697398971L;
	private String user_id = "0";
	private String path = "";
	
	@Override
	public String doTemplate2() throws Exception {
		String  vm = "app/mySMS/mySMS.jsp";
		//String submit = getParam("command");
        String action = getParam("action");
        String tarikhDari = getParam("txdTarikhDari");
        String tarikhHingga = getParam("txdTarikhHingga");
        String jenisSMS = getParam("socJenisSMS");
        context.put("jenisSMS",jenisSMS);
        String jenisSMSHistory = getParam("socJenisSMSHistory");
        context.put("jenisSMSHistory", jenisSMSHistory);
        context.put("tarikhDari", tarikhDari);
        context.put("tarikhHingga",tarikhHingga);
        context.put("action",action);
        context.put("filename","");
        Vector<Hashtable<String,String>> senaraiExcel = null;
        //Vector<Hashtable<String,String>> kodPejabat = null;
        Vector<Hashtable<String,String>> historyData = null;
        HttpSession session = this.request.getSession();
        mySMSData mySMS = new mySMSData();
        //Hashtable<String,String> hKodPejabat;
        Date now = new Date();
        SimpleDateFormat Format =  new SimpleDateFormat("ddMMyyyy-hhmm");
        //SimpleDateFormat sysdate =  new SimpleDateFormat("dd/MM/yyyy");
        PopupSMSHistoryData popupSMSHistory = new PopupSMSHistoryData();
	    user_id = (String)session.getAttribute("_ekptg_user_id");
		ResourceBundle rb = ResourceBundle.getBundle("file");
		path = rb.getString("generalpath")+"mysms/";
	    context.put("pathSMS",path);
	  		    
       if ("exportData".equals(action)){       	
        	String path1 = this.request.getPathTranslated();
        	path1 = path1.substring(0, this.request.getPathTranslated().length() + 1 - this.request.getPathInfo().length());
//        	mySMS.semakKodPejabat(user_id);
//        	kodPejabat = mySMS.getSemakKodPejabat();
//        	hKodPejabat = (Hashtable)kodPejabat.get(0);
//        	String idPejabatJKPTG = (String)hKodPejabat.get("ID_PEJABATJKPTG");
        	String idPejabatJKPTG = String.valueOf(session.getAttribute("_ekptg_user_unit"));
        	String fileName="";
        	String saveFileName="";
        	
        	if ("1".equals(jenisSMS)){
        		fileName = "Pendaftaran_"+idPejabatJKPTG+"-"+Format.format(now)+"_.xls";
     	        saveFileName=path+"Pendaftaran//"+fileName;
     	        writeDataPendaftaranToExcelFile(saveFileName);
     	        simpanHistory(session,fileName,"Pendaftaran",idPejabatJKPTG);
     	        context.put("foldername","Pendaftaran");
     	        context.put("filename",fileName);
     	      
        	}else if ("2".equals(jenisSMS)){
        		fileName = "Perbicaraan_"+idPejabatJKPTG+"-"+Format.format(now)+"_.xls";
     	        saveFileName=path+"Perbicaraan//"+fileName;
     	        writeDataPerbicaraanToExcelFile(saveFileName);
     	        simpanHistory(session,fileName,"Perbicaraan",idPejabatJKPTG);
     	        context.put("foldername","Perbicaraan");
    	        context.put("filename",fileName);
    	      
        	}else if ("3".equals(jenisSMS)){
        		fileName = "Perintah_"+idPejabatJKPTG+"-"+Format.format(now)+"_.xls";
     	        saveFileName=path+"Perintah//"+fileName;
     	        writeDataPerintahToExcelFile(saveFileName);
     	        simpanHistory(session,fileName,"Perintah",idPejabatJKPTG);
     	        context.put("foldername","Perintah");
    	        context.put("filename",fileName);
    	       
        	}
        	
        }else if ("LihatHistory".equals(action)){
        	//popupSMSHistory.historyData("0");
  		    //historyData = popupSMSHistory.getHistoryData();
  		  	historyData = popupSMSHistory.getHistoryData("0");
  	        //context.put("SenaraiHistory",historyData);
  	        //context.put("historyData",historyData.size());
  	        //setupPage(session,action,historyData);
  	        setupPageHistoryData(session,action,historyData);

  	        context.put("jenisSMSHistory", "");
  	        vm = "app/mySMS/popupHistorySMS.jsp";
  	      
        }else  if("cari".equals(action)){
  		  	historyData = popupSMSHistory.getHistoryData(jenisSMSHistory);
   	        setupPageHistoryData(session,action,historyData);
 	        vm = "app/mySMS/popupHistorySMS.jsp";
 	        
        }else{
//        	 if("semak".equals(action)){
             	
             	if("1".equals(jenisSMS)){
             		mySMS.semakPendaftaran(tarikhDari,tarikhHingga,user_id);
             		//listPendaftaran = mySMS.getSemakPendaftaran();
             		senaraiExcel = mySMS.getSemakPendaftaran();
             		//context.put("SenaraiPendaftaran",listPendaftaran);
             		//setupPage(session,action,senaraiExcel);
                      
             	}else if ("2".equals(jenisSMS)){
             		mySMS.semakPerbicaraan(tarikhDari,tarikhHingga,user_id);
             		//listPerbicaraan = mySMS.getSemakPerbicaraan();
             		senaraiExcel = mySMS.getSemakPerbicaraan();
             		//context.put("SenaraiPerbicaraan",listPerbicaraan);
             		//setupPage(session,action,listPerbicaraan);
             	
             	}else if ("3".equals(jenisSMS)){
             		mySMS.semakPerintah(tarikhDari,tarikhHingga,user_id);
             		//listPerintah = mySMS.getSemakPerintah();
             		senaraiExcel = mySMS.getSemakPerintah();
             		//context.put("SenaraiPerintah",listPerintah);
             		//setupPage(session,action,listPerintah);
             	
             	}
             	if(senaraiExcel!=null)
             		setupPage(session,action,senaraiExcel);
                  
             	//myLog.info(getParam("action_"));
             	if ("LihatHistory".equals(getParam("action_"))){
         		  	historyData = popupSMSHistory.getHistoryData("0");
           	        setupPageHistoryData(session,action,historyData);
                 	//myLog.info(historyData.size());
          	        vm = "app/mySMS/popupHistorySMS.jsp";

             	}

//             }
             
      
        }
//       context.put("tarikhDari", sysdate.format(now));
//       context.put("tarikhHingga",sysdate.format(now));
//       context.put("jenisSMS",0);      
       return vm;
	
	}

	public void simpanHistory(HttpSession session,String fileName,String foldername,String idPejabatJKPTG) throws Exception{
   		 Hashtable<String,String> h = new Hashtable<String,String>();
   		 h.put("jenis_SMS",getParam("socJenisSMS"));
   		 h.put("filename",fileName);
   		 h.put("user_id",user_id);
   		 h.put("foldername", foldername);
   		 h.put("idpejabatjkptg",idPejabatJKPTG);
   		 mySMSData.simpanmySMSHistory(h);
   		
   	}
        
   	public void writeDataPendaftaranToExcelFile(String fileName) throws Exception {            
   		 //HttpSession session = this.request.getSession();
   		 HSSFWorkbook myWorkBook = new HSSFWorkbook();
   		 HSSFSheet mySheet = myWorkBook.createSheet("Sheet1");
   		 HSSFRow myRow = null;
   		 HSSFCell myCell = null;
   		 HSSFHeader header = mySheet.getHeader();
   		 header.setCenter("DATA PENDAFTARAN");	
   		 HSSFFooter footer = mySheet.getFooter();
   		 footer.setRight( "Page " + HSSFFooter.page() 
           + " of " + HSSFFooter.numPages() );
   		 mySMSData mySMS = new mySMSData();
       	 Vector<Hashtable<String,String>> listPendaftarani = null;
       	 String tarikhDari = getParam("txdTarikhDari");
         String tarikhHingga = getParam("txdTarikhHingga");
         
         mySMS.semakPendaftaran(tarikhDari, tarikhHingga,user_id);
       	 listPendaftarani = mySMS.getSemakPendaftaran();
       	 String [][] excelData = new String [listPendaftarani.size()+1][9];
       	 for (int rowNum = 0; rowNum <= listPendaftarani.size(); rowNum++){
   	        	 
   	        	 if(rowNum ==0){
   	        		 myRow = mySheet.createRow(rowNum);
   	        		 
   	        		 for (int cellNumHeader = 0; cellNumHeader < 9; cellNumHeader++){
   	        			
   	        			 excelData[rowNum][0]="MobileNo";
   	                     excelData[rowNum][1]="Var1";
   	                     excelData[rowNum][2]="Var2";
   	                     excelData[rowNum][3]="Var3";
   	                     excelData[rowNum][4]="Var4";
   	                     excelData[rowNum][5]="Var5";
   	                     excelData[rowNum][6]="Var6";
   	                     excelData[rowNum][7]="Var7";
	                     excelData[rowNum][8]="Var8";
   	                     
   	                     myCell = myRow.createCell((short)cellNumHeader);
   	         	         myCell.setCellValue(excelData[rowNum][cellNumHeader]); 
   	        		 } 
   	        		 
   	        	 }else {
   	        		 myRow = mySheet.createRow(rowNum);
   	        		 Hashtable<String,String> hPendaftaran = (Hashtable<String,String>)listPendaftarani.get(rowNum-1);
   	        		 for (int cellNum = 0; cellNum < 9 ; cellNum++){
   	            	 	 excelData[rowNum][0]=(String) hPendaftaran.get("HP_NO");
   	            		 excelData[rowNum][1]=(String) hPendaftaran.get("NO_RUJSEKSYEN")+" ";
   	         	         excelData[rowNum][2]="dalam proses ";
   	         	         excelData[rowNum][3]= "Semakan taip ";
   	         	         excelData[rowNum][4]= "JKPTG STATUS ";
   	         	         excelData[rowNum][5]= "<NO.KP SIMATi> ";
   	         	         excelData[rowNum][6]= "hantar 15888 ";
   	         	         excelData[rowNum][7]= "Untuk pertanyaan hubungi ";
   	         	         excelData[rowNum][8]=(String) hPendaftaran.get("NO_TEL");
   	            		 myCell = myRow.createCell((short)cellNum);
   	         	         myCell.setCellValue(excelData[rowNum][cellNum]); 
   	            	   
   	        		 }
   	             }
   	         }

            try{
                FileOutputStream out = new FileOutputStream(fileName);
                myWorkBook.write(out);
                out.close();
                
            }catch(Exception e){ e.printStackTrace();}         
    
   	}
   	  	
   	public void writeDataPerbicaraanToExcelFile(String fileName) throws Exception {
   		//HttpSession session = this.request.getSession();
           HSSFWorkbook myWorkBook = new HSSFWorkbook();
           HSSFSheet mySheet = myWorkBook.createSheet("Sheet1");
           HSSFRow myRow = null;
           HSSFCell myCell = null;
           HSSFHeader header = mySheet.getHeader();
           header.setCenter("DATA PERBICARAAN");	
           HSSFFooter footer = mySheet.getFooter();
           footer.setRight( "Page " + HSSFFooter.page() 
          + " of " + HSSFFooter.numPages() );
           mySMSData mySMS = new mySMSData();
           Vector<Hashtable<String,String>> listPerbicaraani = null;
      	  	String tarikhDari = getParam("txdTarikhDari");
      	  	String tarikhHingga = getParam("txdTarikhHingga");
          
      	  	mySMS.semakPerbicaraan(tarikhDari, tarikhHingga,user_id);
      	  	listPerbicaraani = mySMS.getSemakPerbicaraan();
      	  	String [][] excelData = new String [listPerbicaraani.size()+ 1][9];
           
           for (int rowNum = 0; rowNum <= listPerbicaraani.size(); rowNum++){
          	 
          	 if(rowNum ==0){
          		 myRow = mySheet.createRow(rowNum);
          		 
          		 for (int cellNumHeader = 0; cellNumHeader < 9; cellNumHeader++){
          	     excelData[0][0]="MobileNo";
       	         excelData[0][1]="Var1";
       	         excelData[0][2]="Var2";
       	         excelData[0][3]="Var3";
       	         excelData[0][4]="Var4";
       	         excelData[0][5]="Var5";
       	         excelData[0][6]="Var6";
       	         excelData[0][7]="Var7";
       	         excelData[0][8]="Var8";
       	         myCell = myRow.createCell((short)cellNumHeader);
   	             myCell.setCellValue(excelData[rowNum][cellNumHeader]);
   	             
          		 }
          		 
          	 }else{		
          		 myRow = mySheet.createRow(rowNum);
          		 Hashtable<String,String> hPerbicaraan = (Hashtable<String,String>)listPerbicaraani.get(rowNum-1);
          		 for (int cellNum = 0; cellNum < 9 ; cellNum++){
              	
            	   	 excelData[rowNum][0]=(String) hPerbicaraan.get("HP_NO");
              		 excelData[rowNum][1]=(String) hPerbicaraan.get("NO_RUJSEKSYEN")+" ";
              		 excelData[rowNum][2]= "Tarikh bicara:";
           	         excelData[rowNum][3]=(String) hPerbicaraan.get("TARIKH_BICARA")+" ";
           	         excelData[rowNum][4]= "Pada:";
           	         excelData[rowNum][5]=(String) hPerbicaraan.get("MASA_BICARA")+" ";
           	         excelData[rowNum][6]= "Tempat:";
           	         excelData[rowNum][7]=(String) hPerbicaraan.get("TEMPAT_BICARA")+" ";
           	         excelData[rowNum][8]= "Notis Bicara akan dihantar.";
           	         
           	         myCell = myRow.createCell((short)cellNum);
           	         myCell.setCellValue(excelData[rowNum][cellNum]); 
              	 }
               }
           }

           try{
               FileOutputStream out = new FileOutputStream(fileName);
               myWorkBook.write(out);
               out.close();
               
           }catch(Exception e){ e.printStackTrace();}         

   	}
   	
   	public void writeDataPerintahToExcelFile(String fileName) throws Exception {
   		//HttpSession session = this.request.getSession();
   		HSSFWorkbook myWorkBook = new HSSFWorkbook();
   		HSSFSheet mySheet = myWorkBook.createSheet("Sheet1");
   		HSSFRow myRow = null;
   		HSSFCell myCell = null;
   		mySMSData mySMS = new mySMSData();
   		Vector listPerintahi = null;
   		String tarikhDari = getParam("txdTarikhDari");
   		String tarikhHingga = getParam("txdTarikhHingga");
      	 
   		mySMS.semakPerintah(tarikhDari, tarikhHingga,user_id);
      	listPerintahi = mySMS.getSemakPerintah();
      	String [][] excelData = new String [listPerintahi.size()+1][4];
      	for (int rowNum = 0; rowNum <= listPerintahi.size(); rowNum++){
             	 
             	 if(rowNum ==0){
             		 myRow = mySheet.createRow(rowNum);
             		 
             		 for (int cellNumHeader = 0; cellNumHeader < 4; cellNumHeader++){            			 
             			excelData[0][0]="MobileNo";
             			excelData[0][1]="Var1";
             			excelData[0][2]="Var2";
             			excelData[0][3]="Var3";
          	         
             			myCell = myRow.createCell((short)cellNumHeader);
             			myCell.setCellValue(excelData[rowNum][cellNumHeader]);
             			
             		 }
             			 
             	}else{         
             		myRow = mySheet.createRow(rowNum);
            
             		Hashtable<String,String> hPerintah = (Hashtable<String,String>)listPerintahi.get(rowNum-1);
             		for (int cellNum = 0; cellNum < 4 ; cellNum++){
             			excelData[rowNum][0]=(String) hPerintah.get("HP_NO");
             			excelData[rowNum][1]= "Perintah Pembahagian ";
             			excelData[rowNum][2]=(String) hPerintah.get("NO_RUJSEKSYEN")+" ";
             			excelData[rowNum][3]= "telah siap dan akan diposkan.";
           	   
             			myCell = myRow.createCell((short)cellNum);
             			myCell.setCellValue(excelData[rowNum][cellNum]); 
              		 
             		}        

               }
             	 
           }

           try{
               FileOutputStream out = new FileOutputStream(fileName);
               myWorkBook.write(out);
               out.close();
               
           }catch(Exception e){ e.printStackTrace();}         

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
	public void setupPageHistoryData(HttpSession session,String action,Vector list) {
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
		this.context.put("SenaraiHistory",paging.getPage(page));
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
