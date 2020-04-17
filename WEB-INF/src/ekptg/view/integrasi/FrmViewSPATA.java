package ekptg.view.integrasi;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import ekptg.helpers.Paging;
import ekptg.intergration.IRekodSPATA;
import ekptg.intergration.RekodSPATABean;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.sms.PopupSMSHistoryData;
import ekptg.model.sms.mySMSData;

public class FrmViewSPATA extends AjaxBasedModule{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4539076435099390877L;
	private static Logger myLog = Logger.getLogger(ekptg.view.integrasi.FrmViewSPATA.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Vector senaraiPaparan = null;
	private IRekodSPATA iSPATA = null;
	private String user_id = "";
	
	@Override
	public String doTemplate2() throws Exception {
		String  vm = "app/integrasi/indexSPATA.jsp";
        Date now = new Date();
		String submit = getParam("command");
        String action = getParam("action");
        String tarikhDari = getParam("txdTarikhDari").equals("")?sdf.format(new Date()):getParam("txdTarikhDari");
        String tarikhHingga = getParam("txdTarikhHingga").equals("")?sdf.format(new Date()):getParam("txdTarikhHingga");
        String jenisSMS = getParam("socJenisSMS");
        context.put("jenisSMS",jenisSMS);
        String jenisSMSHistory = getParam("socJenisSMSHistory");
        context.put("jenisSMSHistory", jenisSMSHistory);
        context.put("tarikhDari", tarikhDari);
        context.put("tarikhHingga",tarikhHingga);
        context.put("action",action);
        context.put("filename","");
        Vector listPendaftaran = null;
        Vector listPerbicaraan = null;
        Vector listPerintah = null;
        Vector kodPejabat = null;
        Vector historyData = null;
        HttpSession session = this.request.getSession();
        mySMSData mySMS = new mySMSData();
        Hashtable hKodPejabat;
        SimpleDateFormat Format =  new SimpleDateFormat("ddMMyyyy-hhmm");
        //SimpleDateFormat sysdate =  new SimpleDateFormat("dd/MM/yyyy");
        PopupSMSHistoryData popupSMSHistory = new PopupSMSHistoryData();
	    user_id = (String)session.getAttribute("_ekptg_user_id");
	  	    
       //if ("exportData".equals(action)){	
       if (submit.equals("exportData")){	 	   
    	   String path1 = this.request.getPathTranslated();
    	   path1 = path1.substring(0, this.request.getPathTranslated().length() + 1 - this.request.getPathInfo().length());

        	mySMS.semakKodPejabat(user_id);
        	kodPejabat = mySMS.getSemakKodPejabat();
        	hKodPejabat = (Hashtable)kodPejabat.get(0);
        	String idPejabatJKPTG = (String)hKodPejabat.get("ID_PEJABATJKPTG");
        	String fileName="";
        	String saveFileName="";
        	
       		//fileName = "SPATA_Hakmilik("+hKodPejabat.get("KOD_JKPTG")+"-"+Format.format(now)+").xls";
       		fileName = "SPATA_Hakmilik("+hKodPejabat.get("KOD_JKPTG")+"-"+Format.format(now)+").csv";
       		saveFileName=path1+"app\\integrasi\\csv\\"+fileName;
     	    writeDataToExcelFile(saveFileName);
     	    simpanHistory(session,fileName,"csv",idPejabatJKPTG);
     	    context.put("foldername","csv");
    	    context.put("filename",fileName);
    	    context.put("action",submit);
      
       }else if (submit.equals("exportAgensi")){	 	   
    	   String path1 = this.request.getPathTranslated();
    	   path1 = path1.substring(0, this.request.getPathTranslated().length() + 1 - this.request.getPathInfo().length());

    	   mySMS.semakKodPejabat(user_id);
    	   kodPejabat = mySMS.getSemakKodPejabat();
    	   hKodPejabat = (Hashtable)kodPejabat.get(0);
    	   String idPejabatJKPTG = (String)hKodPejabat.get("ID_PEJABATJKPTG");
    	   String fileName="";
    	   String saveFileName="";
    	         	
    	   //fileName = "SPATA_Agensi("+hKodPejabat.get("KOD_JKPTG")+"-"+Format.format(now)+").xls";
    	   fileName = "SPATA_Agensi("+hKodPejabat.get("KOD_JKPTG")+"-"+Format.format(now)+").csv";
    	   saveFileName=path1+"app\\integrasi\\csv\\"+fileName;
    	   writeDataAgensiToExcelFile(saveFileName);
    	   simpanHistory(session,fileName,"csv",idPejabatJKPTG);
    	   context.put("foldername","csv");
    	   context.put("filename",fileName);
    	   context.put("action","exportData");
  
        }else if (submit.equals("LihatHistory")){
        	popupSMSHistory.historyData("_");
  		    historyData = popupSMSHistory.getHistoryData();
  	        context.put("SenaraiHistory",historyData);
  	        context.put("historyData",historyData.size());
  	        setupPage(session,action,historyData);
  	        context.put("jenisSMSHistory", ""); 	      
  	        vm = "app/integrasi/historySPATA.jsp";
  	      
//        }else if (submit.equals("cari")){
//        	popupSMSHistory.historyData(jenisSMSHistory);
// 		    historyData = popupSMSHistory.getHistoryData();
// 	        context.put("SenaraiHistory",historyData);
// 	        context.put("historyData",historyData.size());
// 	        setupPage(session,action,historyData);
// 	        vm = "app/mySMS/popupHistorySMS.jsp";
        
        }else{
             	senaraiPaparan =  getSPATA().getSenaraiTanah(tarikhDari, tarikhHingga) ;     
                context.put("SenaraiPaparan",senaraiPaparan);
                setupPage(session,action,senaraiPaparan);
     
        }
       context.put("tarikhDari", tarikhDari);
       context.put("tarikhHingga",tarikhHingga);
       return vm;
		
	}

	
	public void simpanHistory(HttpSession session,String fileName,String foldername,String idPejabatJKPTG) 
		throws Exception{
		Hashtable h = new Hashtable();
		h.put("jenis_SMS","_");
   		h.put("filename",fileName);
   		h.put("user_id",user_id);
   		h.put("foldername", foldername);
   		h.put("idpejabatjkptg",idPejabatJKPTG);
   		mySMSData.simpanmySMSHistory(h);
   		
   	}
	  	
   	public void writeDataAgensiToExcelFile(String fileName) throws Exception {
        HSSFWorkbook myWorkBook = new HSSFWorkbook();
        HSSFSheet mySheet = myWorkBook.createSheet("Sheet1");
        HSSFRow myRow = null;
        HSSFCell myCell = null;
        mySMSData mySMS = new mySMSData();
      	String tarikhDari = getParam("txdTarikhDari");
        String tarikhHingga = getParam("txdTarikhHingga");
     	senaraiPaparan =  getSPATA().getSenaraiTanahAgensi(tarikhDari, tarikhHingga) ;     
      	String [][] excelData = new String [senaraiPaparan.size()+1][15];
        for (int rowNum = 0; rowNum <= senaraiPaparan.size(); rowNum++){
        	if(rowNum ==0){
        		myRow = mySheet.createRow(rowNum);
             	for (int cellNumHeader = 0; cellNumHeader < 15; cellNumHeader++){
             		excelData[0][0]="ID_HAKMILIKAGENSI";
             		excelData[0][1]="ID_HAKMILIK";
             		excelData[0][2]="ID_LUAS";
             		excelData[0][3]="LUAS";
             		excelData[0][4]="ID_LUAS_BERSAMAAN";
             		excelData[0][5]="LUAS_BERSAMAAN";
              		excelData[0][6]="KOD_DDSA_KEMENTERIAN";
             		excelData[0][7]="NAMA_KEMENTERIAN";
             		excelData[0][8]="KOD_DDSA_AGENSI";
             		excelData[0][9]="NAMA_AGENSI";
             		excelData[0][10]="ID_MASUK";
             		excelData[0][11]="TARIKH_MASUK";
             		excelData[0][12]="TARIKH_KEMASKINI";
            	    excelData[0][13]="ID_DB";
             		excelData[0][14]="STATUS";
             	
          	        myCell = myRow.createCell((short)cellNumHeader);
   	                myCell.setCellValue(excelData[rowNum][cellNumHeader]);
   	                
             	}
             			 
             }else {
            	 myRow = mySheet.createRow(rowNum);
             	 HakMilik hakmilik = (HakMilik)senaraiPaparan.elementAt(rowNum-1);
                 for (int cellNum = 0; cellNum < 15 ; cellNum++){
                	 excelData[rowNum][0] = String.valueOf(hakmilik.getHakmilikAgensi().getIdHakmilikAgensi());
               	     excelData[rowNum][1] = String.valueOf(hakmilik.getIdHakmilik());
            	     excelData[rowNum][2] = String.valueOf(hakmilik.getIdLuas());
            	     excelData[rowNum][3] = hakmilik.getLuasString();
            	     excelData[rowNum][4] = "2";
            	     excelData[rowNum][5] = String.valueOf(hakmilik.getLuasBersamaan());
           	         excelData[rowNum][6] = hakmilik.getAgensi().getKementerian().getKodKementerian();
           	         excelData[rowNum][7] = hakmilik.getAgensi().getKementerian().getNamaKementerian();
           	         excelData[rowNum][8] = hakmilik.getAgensi().getKodAgensi();
           	         excelData[rowNum][9] = hakmilik.getAgensi().getNamaAgensi();
            	     excelData[rowNum][10] = String.valueOf(hakmilik.getIdMasuk());       	                 	         
          	         excelData[rowNum][11] = String.valueOf(hakmilik.getTarikhMasukStr());
           	         excelData[rowNum][12] = String.valueOf(hakmilik.getTarikhKemaskiniStr());
            	     excelData[rowNum][13] = String.valueOf(hakmilik.getIdDB());
           	         excelData[rowNum][14] = hakmilik.getHakmilikAgensi().getStatus();
           	                
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
   	
  	public void writeDataToExcelFile(String fileName) throws Exception {
   		HttpSession session = this.request.getSession();
        HSSFWorkbook myWorkBook = new HSSFWorkbook();
        HSSFSheet mySheet = myWorkBook.createSheet("Sheet1");
        HSSFRow myRow = null;
        HSSFCell myCell = null;
        mySMSData mySMS = new mySMSData();
      	Vector listPerintah = null;
      	String tarikhDari = getParam("txdTarikhDari");
        String tarikhHingga = getParam("txdTarikhHingga");
      	 
      	//mySMS.semakPerintah(tarikhDari, tarikhHingga,user_id);
      	//listPerintah = mySMS.getSemakPerintah();
     	senaraiPaparan =  getSPATA().getSenaraiTanah(tarikhDari, tarikhHingga) ;     
      	String [][] excelData = new String [senaraiPaparan.size()+1][62];
        for (int rowNum = 0; rowNum <= senaraiPaparan.size(); rowNum++){
        	if(rowNum ==0){
        		myRow = mySheet.createRow(rowNum);
             	for (int cellNumHeader = 0; cellNumHeader < 62; cellNumHeader++){
             		excelData[0][0]="ID_HAKMILIK";
             		excelData[0][1]="ID_PERMOHONAN";
             		excelData[0][2]="PEGANGAN_HAKMILIK";
             		excelData[0][3]="NO_HAKMILIK";
             		excelData[0][4]="KOD_DDSA_KEMENTERIAN";
             		excelData[0][5]="NAMA_KEMENTERIAN";
             		excelData[0][6]="KOD_DDSA_AGENSI";
             		excelData[0][7]="NAMA_AGENSI";
             		excelData[0][8]="KOD_NEGERI";
             		excelData[0][9]="NAMA_NEGERI";
             		excelData[0][10]="NAMA_DAERAH";
             		excelData[0][11]="NAMA_MUKIM";
             		excelData[0][12]="LOKASI";
             		excelData[0][13]="NO_LOT";
             		excelData[0][14]="ID_LOT";
             		excelData[0][15]="KETERANGAN";
             		excelData[0][16]="KEGUNAAN_TANAH";
             		excelData[0][17]="LUAS";
             		excelData[0][18]="ID_LUAS";
             		excelData[0][19]="LUAS_BERSAMAAN";
             		excelData[0][20]="NO_WARTA";
             		excelData[0][21]="TARIKH_WARTA";
             		excelData[0][22]="NO_SYIT";
             		excelData[0][23]="NO_PELAN";
             		excelData[0][24]="SYARAT";
             		excelData[0][25]="SEKATAN";
             		excelData[0][26]="CUKAI";
             		excelData[0][27]="CUKAI_TERKINI";
             		excelData[0][28]="STATUS_GERAN";
             		excelData[0][29]="TARIKH_TERIMA";
             		excelData[0][30]="ID_KATEGORI";
             		excelData[0][31]="ID_SUBKATEGORI";
             		excelData[0][32]="ID_REZAB";
             		excelData[0][33]="NO_REZAB";
             		excelData[0][34]="STATUS_REZAB";
             		excelData[0][35]="KAWASAN_REZAB";
             		excelData[0][36]="TARIKH_REZAB";
             		excelData[0][37]="NO_BANGUNAN";
             		excelData[0][38]="NO_TINGKAT";
             		excelData[0][39]="NO_PETAK";
             		excelData[0][40]="STATUS_SAH";
             		excelData[0][41]="TARIKH_DAFTAR";
             		excelData[0][42]="TARIKH_LUPUT";
             		excelData[0][43]="TEMPOH";
             		excelData[0][44]="NO_PU";
             		excelData[0][45]="NO_FAIL_HAKMILIK";
             		excelData[0][46]="JENIS_HAKMILIK";
             		excelData[0][47]="TARAF_HAKMILIK";
             		excelData[0][48]="HAKMILIK_ASAL";
             		excelData[0][49]="HAKMILIK_BERIKUT";
             		excelData[0][50]="PEGANGAN_HAKMILIK_TUKARGANTI";
             		excelData[0][51]="STATUS_SWASTA";
             		excelData[0][52]="TARIKH_SWASTA";
             		excelData[0][53]="STATUS_PAJAKAN";
             		excelData[0][54]="TARIKH_MOHON_PAJAKAN";
             		excelData[0][55]="STATUS_SEWA";
             		excelData[0][56]="TARIKH_MOHON_SEWA";
             		excelData[0][57]="STATUS_PELEPASAN";
             		excelData[0][58]="TARIKH_PELEPASAN";
             		excelData[0][59]="CATATAN";
             		excelData[0][60]="TARIKH_MASUK";
             		excelData[0][61]="TARIKH_KEMASKINI";
            																	
          	        myCell = myRow.createCell((short)cellNumHeader);
   	                myCell.setCellValue(excelData[rowNum][cellNumHeader]);
             	}
             			 
             }else {
            	 myRow = mySheet.createRow(rowNum);
                 //Hashtable hPerintah = (Hashtable)listPerintah.get(rowNum-1);
            	 //senaraiPaparan =  getSPATA().getSenarai(tarikhDari, tarikhDari) ;   
            	 HakMilik hakmilik = (HakMilik)senaraiPaparan.elementAt(rowNum-1);
                 for (int cellNum = 0; cellNum < 62 ; cellNum++){
                	 excelData[rowNum][0] = String.valueOf(hakmilik.getIdHakmilik());
               	     excelData[rowNum][1] = String.valueOf(hakmilik.getIdPermohonan());
              		 excelData[rowNum][2] = hakmilik.getPeganganHakmilik();
           	         excelData[rowNum][3] = hakmilik.getNoHakmilik();
           	         excelData[rowNum][4] = hakmilik.getAgensi().getKementerian().getKodKementerian();
           	         excelData[rowNum][5] = hakmilik.getAgensi().getKementerian().getNamaKementerian();
           	         excelData[rowNum][6] = hakmilik.getAgensi().getKodAgensi();
           	         excelData[rowNum][7] = hakmilik.getAgensi().getNamaAgensi();
           	         excelData[rowNum][8] = hakmilik.getNegeri().getKodNegeri();
           	         excelData[rowNum][9] = hakmilik.getNegeri().getNamaNegeri();
           	         excelData[rowNum][10] = hakmilik.getDaerah().getNamaDaerah();
           	         excelData[rowNum][11] = hakmilik.getMukim().getNamaMukim();
           	         excelData[rowNum][12] = hakmilik.getLokasi();
           	         excelData[rowNum][13] = hakmilik.getNoLot();
           	         excelData[rowNum][14] = String.valueOf(hakmilik.getRujLot().getIdLot());
           	         excelData[rowNum][15] = hakmilik.getRujLot().getKeterangan();
           	         excelData[rowNum][16] = hakmilik.getKegunaan();
           	         excelData[rowNum][17] = hakmilik.getLuasString();
           	         excelData[rowNum][18] = String.valueOf(hakmilik.getIdLuas());
           	         excelData[rowNum][19] = String.valueOf(hakmilik.getLuasBersamaan());
           	         excelData[rowNum][20] = hakmilik.getNoWarta();
           	         excelData[rowNum][21] = String.valueOf(hakmilik.getTarikhWarta());
           	         excelData[rowNum][22] = hakmilik.getNoSyit();
           	         excelData[rowNum][23] = hakmilik.getNoPelan();
           	         excelData[rowNum][24] = hakmilik.getSyarat();
           	         excelData[rowNum][25] = hakmilik.getSekatan();
           	         excelData[rowNum][26] = String.valueOf(hakmilik.getHakmilikCukai().getCukai());
           	         excelData[rowNum][27] = String.valueOf(hakmilik.getHakmilikCukai().getCukaiTerkini());
           	         excelData[rowNum][28] = hakmilik.getStatusGeran();
           	         excelData[rowNum][29] = String.valueOf(hakmilik.getTarikhTerima());
           	         excelData[rowNum][30] = String.valueOf(hakmilik.getIdKategori());
           	         excelData[rowNum][31] = String.valueOf(hakmilik.getIdSubkategori());
           	         excelData[rowNum][32] = String.valueOf(hakmilik.getIdRizab());
           	         excelData[rowNum][33] = hakmilik.getNoRizab();
           	         excelData[rowNum][34] = hakmilik.getStatusRizab();
           	         excelData[rowNum][35] = hakmilik.getKawasanRizab();
           	         excelData[rowNum][36] = String.valueOf(hakmilik.getTarikhRizab());
           	         excelData[rowNum][37] = hakmilik.getStrata().getBangunan();
           	         excelData[rowNum][38] = hakmilik.getStrata().getTingkat();
           	         excelData[rowNum][39] = hakmilik.getStrata().getPetak();
           	         excelData[rowNum][40] = hakmilik.getStatusSah();
           	         excelData[rowNum][41] = String.valueOf(hakmilik.getTarikhDaftar());
           	         excelData[rowNum][42] = String.valueOf(hakmilik.getTarikhLuput());
           	         excelData[rowNum][43] = hakmilik.getTempoh();
           	         excelData[rowNum][44] = hakmilik.getNoPU();
           	         excelData[rowNum][45] = hakmilik.getNoFailHakmilik();
           	         excelData[rowNum][46] = hakmilik.getRujJenisHakmilik().getKeterangan();
           	         excelData[rowNum][47] = hakmilik.getTaraf();
           	         excelData[rowNum][48] = hakmilik.getNoHakmilikAsal();
           	         excelData[rowNum][49] = hakmilik.getNoHakmilikBerikut();
           	         excelData[rowNum][50] = hakmilik.getUrusan().getHakmilikTukarGanti();
           	         excelData[rowNum][51] = hakmilik.getUrusan().getStatusSwasta();
           	         excelData[rowNum][52] = String.valueOf(hakmilik.getUrusan().getTarikhSwasta());
           	         excelData[rowNum][53] = hakmilik.getUrusan().getStatusPajakan();
           	         excelData[rowNum][54] = String.valueOf(hakmilik.getUrusan().getTarikhPajakan());
           	         excelData[rowNum][55] = hakmilik.getUrusan().getStatusSewa();
           	         excelData[rowNum][56] = String.valueOf(hakmilik.getUrusan().getTarikhSewa());
           	         excelData[rowNum][57] = hakmilik.getUrusan().getStatusPelepasan();
           	         excelData[rowNum][58]= String.valueOf(hakmilik.getUrusan().getTarikhPelepasan());
           	         excelData[rowNum][59] = hakmilik.getCatatan();
           	         //excelData[rowNum][60]= String.valueOf(hakmilik.getTarikhMasuk());
           	         excelData[rowNum][60]= String.valueOf(hakmilik.getTarikhMasukStr());
           	         //excelData[rowNum][61]= String.valueOf(hakmilik.getTarikhKemaskini());
           	         excelData[rowNum][61]= String.valueOf(hakmilik.getTarikhKemaskiniStr());
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
//		this.context.put("SenaraiPerbicaraan",paging.getPage(page));
//		this.context.put("SenaraiPerintah",paging.getPage(page));
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
   	
	
	private IRekodSPATA getSPATA(){
		if(iSPATA== null)
			iSPATA = new RekodSPATABean();
		return iSPATA;
	}	
}
