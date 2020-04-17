package ekptg.view.htp;

import java.io.File;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.velocity.VTemplate;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.entities.Daerah;
import ekptg.model.entities.Mukim;
import ekptg.model.entities.Negeri;
import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.entities.Tblrujlot;
import ekptg.model.entities.Tblrujmukim;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.htp.FrmCukaiKemaskiniDataBaru;
import ekptg.model.htp.FrmCukaiSenaraiData;
import ekptg.model.htp.FrmCukaiSenaraiFailExcelUpload;
import ekptg.model.htp.cukai.CukaiBean;
import ekptg.model.htp.cukai.FrmUploadBaruData;
import ekptg.model.htp.cukai.ICukai;
import ekptg.model.htp.cukai.entity.CukaiTemp;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.HakmilikCukai;

//public class UploadBaru extends AjaxBasedModule{
public class UploadBaru extends VTemplate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.htp.UploadBaru.class);
	private FrmUploadBaruData fubData = null; 
	private HakMilik hakmilik = null;
	private ICukai iCukai = null;
	private String userId = "";

	//public String doTemplate2() throws Exception
	public Template doTemplate() throws Exception{
		
		HttpSession session = request.getSession();
		
		String vm = "";
		String action = getParam("action"); // Second Level

		context.put("completed",false);
		context.put("maxsize",false);
		context.put("wrongEXT",false);

		String submit = getParam("command");
		
		
    	myLog.info("submitX : " + submit);
    	String NamaDoc = getParam("nama_dokumen");
		String keterangan = getParam("keterangan");
		String fileupload11 = getParam("fileupload11");
		
		String lblNegeri = "";
		String lblDaerah = "";
		
//		Hashtable CukaiInfo = FrmUploadBaruData.getCukaiNegeriInfo("");
		
		//lblNegeri = HTML.SelectNegeri("lblNegeri", Long.parseLong(CukaiInfo.get("lblNegeri").toString()), "", "onChange=\"doChangeDaerah()\"");
		
//		lblNegeri = HTML.SelectNegeri("lblNegeri", "onChange=\"doChangeDaerah()\"");
//		lblDaerah = HTML.SelectDaerah("lblDaerah");
//		context.put("lblNegeri", lblNegeri);
//		context.put("lblDaerah", lblDaerah);
		this.context.put("page", 0);
		fubData = new FrmUploadBaruData();
	 	userId =(String)session.getAttribute("_ekptg_user_id");
	
		if ("doChangeDaerah".equals(submit)){
    		
    		vm = "app/htp/cukai/uploadBaru.jsp";
    		this.context.put("page", 0);
			//vm="app/htp/frmCukaiBorangManual.jsp";
    		
    		String socDaerah = "";
    		String socMukim = "";
    		String idNegeri = getParam("lblNegeri");
    		String idDaerah = getParam("lblDaerah");
    		NamaDoc = getParam("nama_dokumen");
    		keterangan = getParam("keterangan");
    		fileupload11 = getParam("fileupload11");
    		
    		myLog.info("doChangeDaerah::Id Negeri ::" + idNegeri);
    		//myLog.info("doChangeDaerah::Id Daerah ::" + idDaerah);
    		
    		lblNegeri = HTML.SelectNegeri("lblNegeri",Utils.parseLong(idNegeri),null, "onChange=\"doChangeDaerah()\" ");
    		//socDaerah = HTML.SelectDaerahByNegeri("socDaerah", null, Utils.parseLong(idNegeri), "");
    		context.put("lblNegeri", lblNegeri);
    		
    		if(idNegeri != ""){
    			context.put("lblDaerah",HTML.SelectDaerahByNegeri(idNegeri,"lblDaerah",null,""));
    			
    		}else {
    			//context.put("socDaerah",HTML.SelectDaerahByNegeri(idNegeri,"socDaerah",null,"onChange=\"doChangeMukim()\""));
    			context.put("lblDaerah", HTML.SelectDaerah("lblDaerah",null,null,""));
    		}		
    		
    		   		
    	}else if("SenaraiFailUpload".equals(submit)||("doChanges".equals(submit))){
    		/**
    		 * Senarai Fail Uplod selepas tamat Upload
    		 */ 		
    		Vector<?> SenaraiFail = null;
    		//String lblNegeri2 = "";		
    		vm = "app/htp/cukai/frmCukaiSenaraiFailUploadExcel.jsp";
    
    		this.context.put("lblNegeri2", HTML.SelectNegeri("lblNegeri2", "onChange=\"doChangeDaerah1()\""));
    		this.context.put("lblDaerah2", HTML.SelectDaerah("lblDaerah2"));
    		this.context.put("lblMukim2", HTML.SelectMukim("lblMukim2"));
    		
    		SenaraiFail = FrmCukaiSenaraiFailExcelUpload.CukaigetSenaraiFailExcel();
    		this.context.put("SenaraiFail", SenaraiFail);		
    		setupPage(session,action,SenaraiFail);
    		
    	}else if("doChangeDaerah1".equals(submit)||("doChanges".equals(submit))){
			
    		Vector SenaraiFail = null;
    		String lblNegeri2 = "";
    		String idNegeri2 = getParam("lblNegeri2");
    		String idDaerah2= getParam("lblDaerah2");
    		String idMukim2= getParam("lblMukim2");
    		
			vm = "app/htp/frmCukaiSenaraiFailUploadExcel.jsp";
			//vm="app/htp/frmCukaiSenaraiTapis.jsp";
			
			myLog.info("idNegeri2 ::" + idNegeri2);
			myLog.info("idDaerah2 ::" + idDaerah2);
			myLog.info("idMukim2 ::" + idMukim2);
			
			lblNegeri2 = HTML.SelectNegeri("lblNegeri2", Utils.parseLong(idNegeri2), null, "onChange=\"doChangeDaerah1()\" ");
			this.context.put("lblNegeri2", lblNegeri2);
			
			if(idNegeri2 != ""){
    			//context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2,"lblDaerah",null,""));
    			context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2, "lblDaerah2", Utils.parseLong(idDaerah2), null, "onChange=\"doChangeDaerah1()\""));
    			
    		}else {
    			//context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2,"lblDaerah2",null,"onChange=\"doChangeDaerah1()\""));
    			//context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2, "lblDaerah2", Utils.parseLong(idDaerah2), null, "onChange=\"doChangeDaerah1()\""));
    			context.put("lblDaerah2", HTML.SelectDaerah("lblDaerah2",null,null,""));
    		}
			
			if(idNegeri2 != "" && idDaerah2 != ""){
						
				this.context.put("lblMukim2", HTML.SelectMukimByDaerah(idDaerah2, "lblMukim2", Utils.parseLong(idMukim2), null, "onChange=\"doChangeDaerah1()\""));
				
			}else{
				
				this.context.put("lblMukim2", HTML.SelectMukimByDaerah(idDaerah2, "lblMukim2"));
				
			}
			
			SenaraiFail = FrmCukaiSenaraiFailExcelUpload.CukaigetSenaraiFailExcel();
    		this.context.put("SenaraiFail", SenaraiFail);
    		setupPage(session,action,SenaraiFail);
    		
		}else if ("carianFail".equals(submit)||("doChanges".equals(submit))){   		
    		vm="app/htp/frmCukaiSenaraiTapisBaru.jsp";
    		
    		Vector list = null;
    		String lblNegericari = getParam("lblNegeri2");
    		String lblDaerahcari = getParam("lblDaerah2");
    		String lblMukimcari = getParam("lblMukim2");
    		String noHakmilikcari = getParam("txtNoHakmilik").trim();
    		
//    		System.out.println("carianFail : Negeri ::"+ lblNegericari);
//    		System.out.println("carianFail : Daerah ::"+ lblDaerahcari);
//    		System.out.println("carianFail : Mukim ::"+ lblMukimcari);
//    		System.out.println("carianFail : NoHakmilik ::"+ noHakmilikcari);
    		
    		list = FrmCukaiSenaraiData.getSenaraiTapis(lblNegericari, lblDaerahcari, lblMukimcari, noHakmilikcari);
    		this.context.put("listtapis", list);
    		
    		Vector vecNegeri=null;
    	
	    	vecNegeri = ekptg.helpers.DB.getNegeri(lblNegericari);
	    	
			Tblrujnegeri h = (Tblrujnegeri) vecNegeri.get(0);
	    	this.context.put("infoNegeri", h);
    		
    		setupPage2(session,action,list);
    		
    	}else if("TambahanManual".equals(submit)){   		
    		//vm = "app/htp/uploadtest3Baru.jsp";
    		//Modified by Rosli, 21/04/2010
    		vm = "app/htp/cukai/UploadBaru.jsp";
    		this.context.put("page", 1);

    		this.context.put("manualNegeri",HTML.SelectNegeri("manualNegeri", "onChange=\"doChangeDaerahManual()\"") );
    		this.context.put("manualDaerah", HTML.SelectDaerah("manualDaerah"));
    		this.context.put("manualMukim", HTML.SelectMukim("manualMukim"));
    		this.context.put("jenisLot",HTML.SelectLot("jenisLot"));
    		this.context.put("JenisHakmilik",HTML.SelectJenisHakmilik("JenisHakmilik"));
    		
    	}else if("doChangeDaerahManual".equals(submit)){			
    		Vector SenaraiFail = null;
    		String lblNegeri3 = "";
    		String idNegeri3 = getParam("manualNegeri");
    		String idDaerah3= getParam("manualDaerah");
    		String idMukim3= getParam("manualMukim");
    		
       		//vm = "app/htp/uploadtest3Baru.jsp";
    		//Modified by Rosli, 21/04/2010
    		vm = "app/htp/cukai/UploadBaru.jsp";
    		this.context.put("page", 1);
			
			lblNegeri3 = HTML.SelectNegeri("manualNegeri", Utils.parseLong(idNegeri3), null, "onChange=\"doChangeDaerahManual()\" ");
			this.context.put("manualNegeri", lblNegeri3);
			
			if(idNegeri3 != ""){
    			//context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2,"lblDaerah",null,""));
    			context.put("manualDaerah",HTML.SelectDaerahByNegeri(idNegeri3, "manualDaerah", Utils.parseLong(idDaerah3), null, "onChange=\"doChangeDaerahManual()\""));
    			
    		}else {
    			//context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2,"lblDaerah2",null,"onChange=\"doChangeDaerah1()\""));
    			//context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2, "lblDaerah2", Utils.parseLong(idDaerah2), null, "onChange=\"doChangeDaerah1()\""));
    			context.put("manualDaerah", HTML.SelectDaerah("manualDaerah",null,null,""));
    		}
			
			/*if(idNegeri3 != "" && idDaerah3 != ""){
						
				this.context.put("manualMukim", HTML.SelectMukimByDaerah(idDaerah3, "manualMukim", Utils.parseLong(idMukim3), null, "onChange=\"doChangeDaerahManual()\""));
				
			}else{*/
				
				this.context.put("manualMukim", HTML.SelectMukimByDaerah(idDaerah3, "manualMukim"));
				
			//}
			
			this.context.put("jenisLot",HTML.SelectLot("jenisLot"));			
    		this.context.put("JenisHakmilik",HTML.SelectJenisHakmilik("JenisHakmilik"));
    		
			SenaraiFail = FrmCukaiSenaraiFailExcelUpload.CukaigetSenaraiFailExcel();
    		this.context.put("SenaraiFail", SenaraiFail);
    		setupPage(session,action,SenaraiFail);
    		
		}else if("SimpanManual".equals(submit)){
    		Hashtable h = null;
			h = new Hashtable();
			
    		String idNegeri2 = "";
    		String idDaerah2 = "";
    		String idMukim2 = "";
    		String noHakmilik = "";
    		String Luas = "";
    		String noLot = "";
    		String Tahunan = "";
    		String CukaiLain = "";
    		String Tungakan = "";
    		String Denda = "";
    		String Lebihan = "";
    		String JumBayaran = "";
    		String KegunaanTanah = "";
    		String JenisHakmilik ="";
    		String JenisLot = "";
    		
    		idNegeri2 = getParam("manualNegeri")==""?"0":getParam("manualNegeri");
    		idDaerah2 = getParam("manualDaerah")==""?"0":getParam("manualDaerah");
    		idMukim2 = getParam("manualMukim")==""?"0":getParam("manualMukim");
    		JenisHakmilik = getParam("JenisHakmilik")==""?"0":getParam("JenisHakmilik");
    		noHakmilik = getParam("txtNoHakmilik")==""?"0":getParam("txtNoHakmilik");
    		Luas = getParam("txtLuas")==""?"0":getParam("txtLuas");
    		JenisLot = getParam("jenisLot")==""?"0":getParam("jenisLot");
    		noLot = getParam("txtNoLot")==""?"0":getParam("txtNoLot");
    		Tahunan = getParam("txtTahunan")==""?"0":getParam("txtTahunan");
    		CukaiLain = getParam("txtCukaiLain")==""?"0":getParam("txtCukaiLain");
    		Tungakan = getParam("txtTungakan")==""?"0":getParam("txtTungakan");
    		Denda = getParam("txtDenda")==""?"0":getParam("txtDenda");
    		Lebihan = getParam("txtLebihan")==""?"0":getParam("txtLebihan");
    		JumBayaran = getParam("txtJumBayaran")==""?"0":getParam("txtJumBayaran"); 
    		KegunaanTanah = getParam("txtKegunaanTanah")==""?"0":getParam("txtKegunaanTanah");
    		
    		h.put("idNegeri2", idNegeri2);
    		h.put("idDaerah2", idDaerah2);
    		h.put("idMukim2", idMukim2);
    		h.put("JenisHakmilik", JenisHakmilik);
    		h.put("noHakmilik", noHakmilik);
    		h.put("Luas", Luas);
    		h.put("JenisLot", JenisLot);
    		h.put("noLot", noLot);
    		h.put("Tahunan", Tahunan);
    		h.put("CukaiLain", CukaiLain);
    		h.put("Tungakan", Tungakan);
    		h.put("Denda", Denda);
    		h.put("Lebihan", Lebihan);
    		h.put("JumBayaran", JumBayaran);
    		h.put("KegunaanTanah", KegunaanTanah);   		
    		FrmCukaiKemaskiniDataBaru.SimpanDataManual(h);
    		
     		//vm = "app/htp/uploadtest3Baru.jsp";
    		//Modified by Rosli, 21/04/2010
    		vm = "app/htp/cukai/UploadBaru.jsp";
      		this.context.put("page", 1);
    		
      		//vm = "app/htp/frmCukaiSenaraiFailUploadExcel.jsp";
    		
//   kemaskini 		this.context.put("manualNegeri", HTML.SelectNegeri("manualNegeri", Long.parseLong(idNegeri2), "disabled=\"disabled\" class=\"disabled\""));
//    		this.context.put("manualDaerah", HTML.SelectDaerah("manualDaerah",  Long.parseLong(idDaerah2), "disabled=\"disabled\" class=\"disabled\""));
//    		this.context.put("manualMukim", HTML.SelectMukim("manualMukim",  Long.parseLong(idMukim2), "disabled=\"disabled\" class=\"disabled\""));
//    		this.context.put("JenisHakmilik", HTML.SelectJenisHakmilik("JenisHakmilik",  Long.parseLong(JenisHakmilik), "disabled=\"disabled\" class=\"disabled\""));
//    		this.context.put("jenisLot", HTML.SelectLot("jenisLot",  Long.parseLong(JenisLot), "disabled=\"disabled\" class=\"disabled\""));
//    		this.context.put("noLot", noLot);
//    		this.context.put("nohakmilik", noHakmilik);
//    		this.context.put("KegunaanTanah", KegunaanTanah); 		
    		
    		this.context.put("modeDisable", "class=\"disabled\" disabled=\"disabled\"");
    		
    	}else if("uploadFile".equals(submit)){
     		NamaDoc = getParam("nama_dokumen");
    		keterangan = getParam("keterangan");
    		fileupload11 = getParam("fileupload11");
    		String tahunCukai = getParam("tahun_cukai");
    		DiskFileItemFactory factory = new DiskFileItemFactory();
 		    ServletFileUpload upload = new ServletFileUpload(factory);
 		    myLog.info("Log :: Factory :: "+factory);
 		    myLog.info("Log :: upload :: "+upload);
 		    String idnegeri = null;
 		    String idDaerah = null;
 		    
    		List items = upload.parseRequest(request);
    		myLog.info("Log :: items :: "+items);

    		int maxMB = 5; //set maximum size in MB
    		long maxSize = ((1024*1024) * maxMB );
    		int totalRecord =0;
			hakmilik = new HakMilik();
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		    	FileItem item = (FileItem)itr.next();
		    	long itemSize = item.getSize();

		    	if(itemSize > maxSize){ //check size
		    		context.put("maxsize",true);
		    	}else{
		    		myLog.info("xls");
		      		String extension = getParam("extension");
		      		File f = new File(extension);
		      		String ext = f.getName().substring(f.getName().lastIndexOf('.')+1);

		    	  if(ext.equals("xls")){
		      		myLog.info("xls:"+item.isFormField()+","+item.getName());
		    		  if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    			    
		    			  myLog.info("! xls"+item.isFormField()+","+item.getName());
		    	    		InputStream itemPath = item.getInputStream();
		    	    		myLog.info("itemPath=,"+item);
//		    	    		 
//		    	            POIFSFileSystem poif_fs = new POIFSFileSystem(itemPath);
//		    	            HSSFWorkbook workbook = new HSSFWorkbook(poif_fs);       
//				      		myLog.info("workBook=,"+workbook);
//		    	            int numberSheets = workbook.getNumberOfSheets();

		    	    		HSSFWorkbook workBook = new HSSFWorkbook (itemPath);
				      		myLog.info("workBook=,"+workBook);
		    				HSSFSheet sheet = workBook.getSheetAt (0);
				      		myLog.info("sheet,"+sheet);
		    				Iterator<Row> rows = sheet.rowIterator ();
				      		myLog.info("rows="+rows);
		    				int noRow = sheet.getPhysicalNumberOfRows();
				      		myLog.info("noRow="+noRow);
		    				int cellType = 0;
		    				Vector listcell = new Vector();
		    				Hashtable h = null;
		    				
		    				HSSFRow myRowObjState =sheet.getRow(0);//get negeri name/code
		    				int x = myRowObjState.getPhysicalNumberOfCells();
		    				HSSFRow myRowObjDaerah =sheet.getRow(1);// get daerah name /code
				      		myLog.info("myRowObjState="+myRowObjState);
				      		myLog.info("myRowObjDaerah="+myRowObjDaerah);
		    				
		    				HSSFCell cellState= myRowObjState.getCell((short)1);
		    				HSSFCell cellDaerah= myRowObjDaerah.getCell((short)1);
				      		myLog.info("cellState="+cellState);
				      		myLog.info("cellDaerah="+cellDaerah);		    				
		    				idnegeri = getIdNegeri(cellState.getStringCellValue());
		    				if(idnegeri== null){
		    					throw new Exception("ID NEGERI TAK DITEMUI");
		    				}
		    				idDaerah = getIdDaerah(idnegeri,cellDaerah.getStringCellValue());
		    				if(idDaerah== null){
		    					throw new Exception("ID DAERAH TAK DITEMUI");
		    				}
		    				FrmUploadBaruData.deleteCukai(idnegeri, idDaerah, tahunCukai);
		    				for(int i=3; i<(noRow); i++){//noRow
					      		myLog.info("I="+i);		    				
				
		    			    	HSSFRow myRowObj=sheet.getRow(i);
		    			    	if(myRowObj != null){
		    			    	int noCells = myRowObj.getPhysicalNumberOfCells();
		    			
		    			    	//HSSFCell cell = sheet.getRow(0).getCell((short)0);
		    			    	HSSFCell cellMukim = myRowObj.getCell((short)1);
		    			    	HSSFCell cellKodHakmilik = myRowObj.getCell((short)2);
		    			    	HSSFCell cellHakmilik = myRowObj.getCell((short)3);
		    			    	HSSFCell cellKodLot = myRowObj.getCell((short)4);
		    			    	HSSFCell cellLot = myRowObj.getCell((short)5);
		    			    	HSSFCell cellKegunaan = myRowObj.getCell((short)6);
		    			    	HSSFCell cellCukai = myRowObj.getCell((short)7);
		    			    	HSSFCell cellCukaiTungg = myRowObj.getCell((short)8);
		    			    	HSSFCell cellCukaiDenda = myRowObj.getCell((short)9);
		    			    	HSSFCell cellKredit = myRowObj.getCell((short)10);
		    			    	HSSFCell cellJumlah = myRowObj.getCell((short)11);
		    			    	
		    			    	Double total = 0.0;    			    		
	    			    		Double cell71 = Double.parseDouble(""+getCellValue(cellCukai));
	    			    		Double cukaiTerkini = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukai))));
	    			    		Double cell81= Double.parseDouble(""+getCellValue(cellCukaiTungg));
	    			    		Double cukaiTunggakan = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukaiTungg)))); 			    		
	    			    		Double cell91 = Double.parseDouble(""+getCellValue(cellCukaiDenda));
	    			    		Double cukaiDenda = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukaiDenda)))); 			    		
	    			    		Double pengurangan = Double.parseDouble(String.valueOf(getCellValue(cellKredit)));
	    			    		total = (cell71 + cell81 + cell91) - pengurangan ;
	    			    		Double cukaiLebihan = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellKredit))));
	    			    		Double cukaiJumlah = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellJumlah))));
	    			    		Double cukaiTaliair = 0.00;
	    			    		Double cukaiParit = 0.00;
	    			    		Double cukaiPengurangan = 0.00;
	    			    		Double cukaiPengecualian = 0.00;
	    			    		
	    			    		String idMukim = getMukimByDaerah(idDaerah, getCellValue(cellMukim));
	    			    		String idHakmilik = getIdHakmilik(getCellValue(cellKodHakmilik));
	    			    		if(idHakmilik==null)
	    			    			idHakmilik = "0";
	    			    		String idLot = getIdLot(getCellValue(cellKodLot));
	    			    		if(idLot == null)
	    			    			idLot ="0";
	    			    		myLog.debug("**"+idMukim);
	    			    		if(idMukim != null){
	    			    			h = new Hashtable();
	    							Mukim mukim = new Mukim();
									Tblrujmukim rMukim = new Tblrujmukim();
	    							Daerah daerah = new Daerah();
	    							Negeri negeri = new Negeri();
	    							Tblrujlot lot = new Tblrujlot();
	    							Tblrujjenishakmilik jh = new Tblrujjenishakmilik();
	    							HakmilikCukai hakmilikCukai = new HakmilikCukai();
	    							CukaiTemp cukaiTemp = new CukaiTemp();
		    			    		/*h.put("Mukim", getCellValue(cellMukim));
					    			h.put("idNegeri", idnegeri);
					    			h.put("idDaerah", idDaerah);				    				
					    			h.put("IdMukim", idMukim);
					    			h.put("Jenis_No", getCellValue(cellKodHakmilik));
					    			h.put("Hakmilik",getCellValue(cellHakmilik) );
					    			h.put("Jenis_Lot", getCellValue(cellKodLot));
					    			h.put("No_Lot",String.valueOf(getCellValue(cellLot)));
					    			h.put("Kegunaan_Tanah", getCellValue(cellKegunaan));
					    			h.put("Cukai", getCellValue(cellCukai));
					    			h.put("Tunggakkan", getCellValue(cellCukaiTungg));
					    			h.put("Denda_Lewat", getCellValue(cellCukaiDenda));
					    			h.put("Kredit", getCellValue(cellKredit));
					    			//h.put("Cukai_Kena_Bayar", ""+total);
					    			//h.put("Cukai_Kena_Bayar", String.valueOf(getCellValue(cellJumlah)));
					    			//h.put("Cukai_Kena_Bayar", getCellValue(cellJumlah));
					    			h.put("Cukai_Kena_Bayar", String.valueOf(total));
					    			h.put("tahunCukai",tahunCukai);	
					    			h.put("idHakmilik", idHakmilik);
					    			h.put("idLot", idLot);
					    			fubData.SimpanFailUploadCukaiBaru(h);
					    			*/
					    			
									hakmilik.setIdPermohonan(getICukai().getIdHakmilikCukai(hakmilik).getIdPermohonan());		//TBLHTPCUKAITEMP
					    			hakmilik.setIdMukim(Long.parseLong(idMukim));
									mukim.setIdMukim(Long.parseLong(idMukim));
									mukim.setNamaMukim(String.valueOf(getCellValue(cellMukim)));			//TBLHTPCUKAITEMP
									hakmilik.setMukim(mukim);	
									
									rMukim.setNamaMukim(String.valueOf(getCellValue(cellMukim)));			//TBLHTPCUKAITEMP
									cukaiTemp.setRujMukim(rMukim);
									
									hakmilik.setIdHakmilik(Long.parseLong(idDaerah));
									daerah.setIdDaerah(Long.parseLong(idDaerah));
									hakmilik.setDaerah(daerah);
									hakmilik.setIdNegeri(Long.parseLong(idnegeri));
									negeri.setIdNegeri(Long.parseLong(idnegeri));
									hakmilik.setNegeri(negeri);
									hakmilik.setIdJenisHakmilik(Long.parseLong(idHakmilik));
									jh.setIdJenishakmilik(Long.parseLong(idHakmilik));
									jh.setKodJenisHakmilik(String.valueOf(getCellValue(cellKodHakmilik)));
									hakmilik.setRujJenisHakmilik(jh);
									hakmilik.setNoHakmilik(String.valueOf(getCellValue(cellHakmilik)));
									hakmilik.setNoHakmlik(String.valueOf(getCellValue(cellHakmilik)));
									cukaiTemp.setNoHakmilik(String.valueOf(getCellValue(cellKodHakmilik))+String.valueOf(getCellValue(cellHakmilik)));
									hakmilik.setIdJenisLot(Long.parseLong(idLot));
									lot.setIdLot(Long.parseLong(idLot));
									lot.setKodLot(String.valueOf(getCellValue(cellKodLot)));
									hakmilik.setRujLot(lot);
									hakmilik.setNoLot(String.valueOf(getCellValue(cellLot)));
									hakmilik.setKegunaan(String.valueOf(getCellValue(cellKegunaan)));									
									
									cukaiTemp.setCukaiKenaBayar(cukaiTerkini); //TBLHTPCUKAITEMP
									cukaiTemp.setTunggakkan(cukaiTunggakan);				//TBLHTPCUKAITEMP
									cukaiTemp.setCukaiTaliAir(cukaiTaliair);				//TBLHTPCUKAITEMP
									cukaiTemp.setCukaiParit(cukaiParit);					//TBLHTPCUKAITEMP
									cukaiTemp.setCukailain(0.00);										//TBLHTPCUKAITEMP
									cukaiTemp.setDenda(cukaiDenda);						//TBLHTPCUKAITEMP
									cukaiTemp.setPengurangan(cukaiPengurangan);			//TBLHTPCUKAITEMP
									cukaiTemp.setPengecualian(cukaiPengecualian);		//TBLHTPCUKAITEMP
									cukaiTemp.setLebihan(cukaiLebihan);											//TBLHTPCUKAITEMP
									cukaiTemp.setTotalcukai(cukaiJumlah);			//TBLHTPCUKAITEMP
									cukaiTemp.setTahun(tahunCukai);
									hakmilik.setHakmilikCTemp(cukaiTemp);
									hakmilik.setIdMasuk(Long.parseLong(userId));				//TBLHTPCUKAITEMP
		
									
									//TBLHTPCUKAITERPERINCI
									hakmilikCukai.setKodBayaranCukai(tahunCukai);
									hakmilikCukai.setCukaiTerkini(cukaiTerkini);
									hakmilikCukai.setTunggakan(cukaiTunggakan);									
									hakmilikCukai.setDenda(cukaiDenda);
									hakmilikCukai.setCukaiTaliAir(cukaiTaliair);
									hakmilikCukai.setCukaiParit(cukaiParit);
									hakmilikCukai.setPengurangan(cukaiPengurangan);
									hakmilikCukai.setPengecualian(cukaiPengecualian);
									hakmilikCukai.setLebihan(cukaiLebihan);
									hakmilikCukai.setJumlah(cukaiJumlah);									
									hakmilikCukai.setIdHakmilikCukai(getICukai().getIdHakmilikCukai(hakmilik).getHakmilikCukai().getIdHakmilikCukai());
									hakmilik.setHakmilikCukai(hakmilikCukai);

									getICukai().simpanCukaiHakmilikTemp(hakmilik);
									getICukai().simpanCukaiHakmilikTerperinci(hakmilik);
									
					    			totalRecord ++;
	    			    		}
		    			    }
		    			    //return listcell;
		    				//mylog.info("rows1:"+noRow);
		    				}
		    				
		    	  		}
		    	  }else{
		    		  context.put("wrongEXT",true);
		    	  }
		      }

		    }//close uploadfile
		    Vector<CukaiTemp> cukaiList = FrmUploadBaruData.getUploadList(tahunCukai, idnegeri, idDaerah);
		    context.put("tahun" , tahunCukai);
		    context.put("negeri", getNegeri(idnegeri));
		    context.put("daerah", getDaerah(idnegeri,idDaerah));
		    context.put("cukaiList", cukaiList);
		    context.put("totalRecord", totalRecord);
            context.put("completed",true);
          
     		//vm = "app/htp/uploadtest3Baru.jsp";
    		//Modified by Rosli, 21/04/2010
    		vm = "app/htp/cukai/UploadList.jsp";
  
    	}else{	//close upload file
    		//vm = "app/htp/uploadtest3Baru.jsp";
    		//Modified by Rosli, 21/04/2010
    		vm = "app/htp/cukai/UploadBaru.jsp";
    		
    	}

	
		//return vm;
        Template template = this.engine.getTemplate(vm);
        return template;

    }//close dotemplate
	

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
	
	public void setupPage2(HttpSession session,String action,Vector list) {
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
		this.context.put("listtapis",paging.getPage(page));
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
	
	public String getCellValue(HSSFCell cell){ 
		String str = "0";
		//mylog.debug("cell = "+cell);
		
		if(cell != null){
			
			//mylog.debug("getcelltype:"+cell.getCellType());
			
			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				//System.out.println("Cell type for date data type is numeric."+cell.getNumericCellValue());
				str = ""+cell.getNumericCellValue();
			}
			if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
				//System.out.println("Cell type for date data type is String 1."+cell.getStringCellValue());
				str = ""+cell.getStringCellValue();
				
			}
			if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
				//System.out.println("Cell type for date data type is String 1."+cell.getStringCellValue());
				str = ""+cell.getCellFormula(); 
				
			}
			if(cell.getCellType() == HSSFCell.CELL_TYPE_ERROR){
				//System.out.println("Cell type for date data type is String 1."+cell.getStringCellValue());
				str = "0"; 
				
			}
		}
		return str;
	}
	
	private String getDaerah(String idNegeri,String idDaerah){
		String sql = "Select id_Daerah,kod_Daerah,nama_Daerah from tblrujdaerah"
			+ " where id_negeri=" +idNegeri
			+" AND id_Daerah ="+idDaerah
			+ " ORDER BY lpad(kod_Daerah,10)";
		Db db = null;
		try{
			db=new Db();
			Statement stmt = db.getStatement();
			ResultSet rs= stmt.executeQuery(sql);
			if(rs.next()){
				return rs.getString("nama_Daerah");
			}
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return null;
	}
	
	private String getNegeri(String idNegeri){
		//System.out.println("id negeri " +idNegeri);
		String sql = "Select id_Negeri,kod_Negeri,nama_Negeri from tblrujnegeri WHERE id_Negeri="+idNegeri;
		Db db = null;
		try{
			db=new Db();
			Statement stmt = db.getStatement();
			ResultSet rs= stmt.executeQuery(sql);
			if(rs.next()){
				return rs.getString("nama_Negeri");
			}
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return null;
	}

	private String getIdNegeri(String name){
		String sql = "Select id_Negeri,kod_Negeri,nama_Negeri from tblrujnegeri WHERE nama_Negeri='"+name.toUpperCase()+"'";
		Db db = null;
		try{
			db=new Db();
			Statement stmt = db.getStatement();
			ResultSet rs= stmt.executeQuery(sql);
			if(rs.next()){
				return rs.getString("id_Negeri");
			}
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return null;
	}
	private String getIdDaerah(String idNegeri,String nameDaerah){
		String sql = "Select id_Daerah,kod_Daerah,nama_Daerah from tblrujdaerah"
			+ " where id_negeri=" +idNegeri
			+" AND nama_Daerah ='"+nameDaerah.toUpperCase()+"'"
			+ " ORDER BY lpad(kod_Daerah,10)";
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				return rs.getString("id_Daerah");
			}
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return null;
	}
	
	private String getMukimByDaerah(String iddaerah,String mukimName) throws Exception {
	    Db db = null;
	    String sql = "";
	    sql = "SELECT ID_MUKIM,KOD_MUKIM,NAMA_MUKIM FROM TBLRUJMUKIM" +
	    	" WHERE ID_DAERAH='"+iddaerah+"' AND NAMA_MUKIM LIKE '%"+mukimName.trim()+"%' ORDER BY lpad(KOD_MUKIM,10)";
	    //mylog.debug(sql);
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      ResultSet rs = stmt.executeQuery(sql);
	      if (rs.next()) {
	    	 return rs.getString("ID_MUKIM");
	      }
	      return null;
	    } finally {
	      if (db != null) db.close();
	    }

	  }//close get mukim by daerah
	private String getIdHakmilik(String hakMilikDesc){
		Db db = null;
	    try{
	    	db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Jenishakmilik");
			r.add("KOD_JENIS_HAKMILIK",hakMilikDesc.toUpperCase());
			String sql = r.getSQLSelect("Tblrujjenishakmilik");
			 ResultSet rs = stmt.executeQuery(sql);
		      if (rs.next()) {
		    	 return rs.getString("id_Jenishakmilik");
		      }
	    }
	    catch(Exception e){
			e.printStackTrace();
		}
	    finally {
		      if (db != null) db.close();
	    }
	    return null;
	   
	}
	
	private String getIdLot(String cellValue) {
		Db db = null;
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Lot");
			r.add("Keterangan",cellValue.toUpperCase());
			String sql = r.getSQLSelect("Tblrujlot");
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				return rs.getString("id_Lot");
			}
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
	    finally {
		      if (db != null) db.close();
	    }
		return null;
	}
	
	private ICukai getICukai(){
		if(iCukai==null){
			iCukai = new CukaiBean();
		}
		return iCukai;
		
	}
	
	
}//close class


