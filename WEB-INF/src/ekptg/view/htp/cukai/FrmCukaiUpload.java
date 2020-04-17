package ekptg.view.htp.cukai;

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
public class FrmCukaiUpload extends VTemplate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.htp.cukai.FrmCukaiUpload.class);
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
 		    //myLog.info("Log :: Factory :: "+factory);
 		    //myLog.info("Log :: upload :: "+upload);
 		    String idnegeri = null;
 		    String idDaerah = null;
 		    
    		List items = upload.parseRequest(request);
//    		myLog.info("Log :: items :: "+items);

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
//		    		myLog.info("xls");
		      		String extension = getParam("extension");
		      		File f = new File(extension);
		      		String ext = f.getName().substring(f.getName().lastIndexOf('.')+1);

		    	  if(ext.equals("xls")){
//		      		myLog.info("xls:"+item.isFormField()+","+item.getName());
		    		  if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    			    
//		    			  myLog.info("! xls"+item.isFormField()+","+item.getName());
		    	    		InputStream itemPath = item.getInputStream();
	//	    	    		myLog.info("itemPath=,"+item);
		    	    		HSSFWorkbook workBook = new HSSFWorkbook (itemPath);
//				      		myLog.info("workBook=,"+workBook);
		    				HSSFSheet sheet = workBook.getSheetAt (0);
//				      		myLog.info("sheet,"+sheet);
		    				Iterator rows = sheet.rowIterator ();
	//			      		myLog.info("rows="+rows);
		    				int noRow = sheet.getPhysicalNumberOfRows();
//				      		myLog.info("noRow="+noRow);
		    				int cellType = 0;
		    				Vector listcell = new Vector();
		    				Hashtable h = null;
		    				
		    				HSSFRow myRowObjState =sheet.getRow(0);//get negeri name/code
		    				int x = myRowObjState.getPhysicalNumberOfCells();
		    				HSSFRow myRowObjDaerah =sheet.getRow(1);// get daerah name /code
//				      		myLog.info("myRowObjState="+myRowObjState);
//				      		myLog.info("myRowObjDaerah="+myRowObjDaerah);		    				
		    				HSSFCell cellState= myRowObjState.getCell((short)1);
		    				HSSFCell cellDaerah= myRowObjDaerah.getCell((short)1);
		    				idnegeri = getIdNegeri(cellState.getStringCellValue());
		    				if(idnegeri== null){
		    					throw new Exception("ID NEGERI TAK DITEMUI");
		    				}
		    				idDaerah = getIdDaerah(idnegeri,cellDaerah.getStringCellValue());
		    				if(idDaerah== null){
		    					throw new Exception("ID DAERAH TAK DITEMUI");
		    				}
				      		myLog.info("cellState="+idnegeri+",cellDaerah="+idDaerah);
		    				FrmUploadBaruData.deleteCukai(idnegeri, idDaerah, tahunCukai);
		    				for(int i=3; i<(noRow); i++){//noRow
//					      		myLog.info("I="+i);		    								
		    			    	HSSFRow myRowObj=sheet.getRow(i);
		    			    	if(myRowObj != null){
		    			    	int noCells = myRowObj.getPhysicalNumberOfCells();
		    			    	
		    			    	HSSFCell cellMukim = myRowObj.getCell((short)1);
		    			    	String strNoHakmilik = "";
		    			    	String strKodHakmilik = "";
		    			    	String strIdKodHakmilik = "";
		    			    	String strNoLot = "";
		    			    	String strKodLot = "LOT";
		    			    	String strIdKodLot = "1"; //LOT
		    			    	String strKegunaan = "";
		    		    		String idMukim = getMukimByDaerah(idDaerah, getCellValue(cellMukim));
//	    			    		myLog.debug("**"+idMukim);
	    			    		if(idMukim != null){
	    			    			Double cukaiTerkini = 0.0;
	    			    			Double cukaiTunggakan = 0.0;
	    				    		Double cukaiTaliair = 0.00;
		    			    		Double cukaiParit = 0.00;
		    			    		Double cukaiLain = 0.00;
		    			    		Double cukaiDenda = 0.00; 			    		
		    			    		Double cukaiPengurangan = 0.00;
		    			    		Double cukaiPengecualian = 0.00;
		    			    		Double cukaiLebihan = 0.00;
		    			    		Double cukaiJumlah = 0.00;
			    			    	if(cellState.getStringCellValue().equalsIgnoreCase("KEDAH")){
				    			    	HSSFCell cellNoHakmilik = myRowObj.getCell((short)2);
				    			    	HSSFCell cellNoLot = myRowObj.getCell((short)3);
				    			    	HSSFCell cellKegunaan = myRowObj.getCell((short)5);
				    			    	HSSFCell cellCukai = myRowObj.getCell((short)7);
				    			    	HSSFCell cellCukaiTunggakan = myRowObj.getCell((short)8);
				    			    	
				    			    	strKodHakmilik = getKodHakmilik(getCellValue(cellNoHakmilik));
				    			    	strIdKodHakmilik = getIdKodHakmilik(getCellValue(cellNoHakmilik));
				    			    	strNoHakmilik = getNoHakmilik(getCellValue(cellNoHakmilik));
				    			    	strNoLot = getCellValue(cellNoLot);
				    			    	strKegunaan = getCellValue(cellKegunaan);
//			    			    		myLog.info("strKodHakmilik="+strIdKodHakmilik+","+strKodHakmilik+","+strKodHakmilik);
				    			    	//String idHakmilik = getIdHakmilik(getCellValue(cellKodHakmilik));
			    			    		cukaiTerkini = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukai))));
			    			    		cukaiTunggakan = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukaiTunggakan)))); 			    		
			    			    		cukaiJumlah = cukaiTerkini+cukaiTunggakan;

			    			    	}else if(cellState.getStringCellValue().equalsIgnoreCase("JOHOR")){
				    			    	HSSFCell cellKodHakmilik = myRowObj.getCell((short)2);
				    			    	HSSFCell cellNoHakmilik = myRowObj.getCell((short)3);
				    			    	HSSFCell cellKodLot = myRowObj.getCell((short)4);
				    			    	HSSFCell cellNoLot = myRowObj.getCell((short)5);
				    			    	HSSFCell cellKegunaan = myRowObj.getCell((short)6);
				    			    	HSSFCell cellCukai = myRowObj.getCell((short)12);
				    			    	HSSFCell cellCukaiTunggakan = myRowObj.getCell((short)13);
				    			    	
				    			    	strKodHakmilik = getCellValue(cellKodHakmilik);
				    			    	strIdKodHakmilik = getIdHakmilik(getCellValue(cellKodHakmilik));
				    			    	strNoHakmilik = getCellValue(cellNoHakmilik);
				    			    	if(strNoHakmilik.contains(".0")){
				    			    		strNoHakmilik = strNoHakmilik.substring(0,strNoHakmilik.indexOf(".0"));
										}		
				    			    	strIdKodLot = getIdLot(getCellValue(cellKodLot));
				    			    	strNoLot = getCellValue(cellNoLot);
				    			    	strKegunaan = getCellValue(cellKegunaan);
			    			    		cukaiTerkini = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukai))));
			    			    		cukaiTunggakan = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukaiTunggakan)))); 			    		
			    			    		cukaiJumlah = cukaiTerkini+cukaiTunggakan;

			    			    	}else if(cellState.getStringCellValue().equalsIgnoreCase("KELANTAN")){
				    			    	HSSFCell cellNoHakmilik = myRowObj.getCell((short)2);
				    			    	HSSFCell cellNoLot = myRowObj.getCell((short)3);
				    			    	HSSFCell cellNoHakmilik_ = myRowObj.getCell((short)4);
				    			    	HSSFCell cellNoLot_ = myRowObj.getCell((short)5);
				    			    	HSSFCell cellKegunaan = myRowObj.getCell((short)6);
				    			    	HSSFCell cellCukai = myRowObj.getCell((short)7);
				    			    	HSSFCell cellCukaiDenda = myRowObj.getCell((short)8);
				    			    	HSSFCell cellCukaiTunggakan = myRowObj.getCell((short)9);
				    			    	String hm = "";
				    			    	if(!getCellValue(cellNoHakmilik_).equals("0")){
					    			    	hm = getCellValue(cellNoHakmilik_);	    	
				    			    	}else{
				    			    		hm = getCellValue(cellNoHakmilik);
				    			    	}
				    			    	myLog.info("hm="+hm);
				    			    	strKodHakmilik = getKodHakmilik(hm);
				    			    	strIdKodHakmilik = getIdKodHakmilik(hm);
				    			    	strNoHakmilik = getNoHakmilik(hm);
				    			    	if(!getCellValue(cellNoLot_).equals("0")){
				    			    		strNoLot = getCellValue(cellNoLot_);	    	
				    			    	}else{
				    			    		strNoLot = getCellValue(cellNoLot);
				    			    		strIdKodLot = "3";
				    			    	}		

				    			    	strKegunaan = getCellValue(cellKegunaan);
			    			    		cukaiTerkini = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukai))));
			    			    		cukaiDenda = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukaiDenda)))); 			    		
			    			    		cukaiTunggakan = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukaiTunggakan)))); 			    		
			    			    		cukaiJumlah = cukaiTerkini+cukaiTunggakan+cukaiDenda;

			    			    	}else if(cellState.getStringCellValue().equalsIgnoreCase("MELAKA")){
				    			    	HSSFCell cellNoHakmilik = myRowObj.getCell((short)2);
				    			    	HSSFCell cellNoLot = myRowObj.getCell((short)3);
				    			    	HSSFCell cellNoHakmilik_ = myRowObj.getCell((short)4);
				    			    	HSSFCell cellNoLot_ = myRowObj.getCell((short)5);
				    			    	HSSFCell cellKegunaan = myRowObj.getCell((short)6);
				    			    	HSSFCell cellCukai = myRowObj.getCell((short)7);
				    			    	HSSFCell cellCukaiTunggakan = myRowObj.getCell((short)8);
				    			    	HSSFCell cellCukaiDenda = myRowObj.getCell((short)9);
				    			    	HSSFCell cellCukaiPengurangan = myRowObj.getCell((short)10);
				    			    	HSSFCell cellCukaiTerlebih = myRowObj.getCell((short)11);

				    			    	String hm = "";
				    			    	if(!getCellValue(cellNoHakmilik_).equals("0")){
					    			    	hm = getCellValue(cellNoHakmilik_);	    	
				    			    		if(!getCellValue(cellNoHakmilik_).equals("")){
						    			    	hm = getCellValue(cellNoHakmilik_);	    	
   			
				    			    		}else{
					    			    		hm = getCellValue(cellNoHakmilik);		
				    			    		}
				    			    	}else{
				    			    		hm = getCellValue(cellNoHakmilik);
				    			    		if(!getCellValue(cellNoHakmilik).equals("")){
						    			    	hm = getCellValue(cellNoHakmilik);	    	  			
				    			    		}
				    			    	}
				    			    	myLog.info("hm="+hm);
				    			    	strKodHakmilik = getKodHakmilik(hm);
				    			    	strIdKodHakmilik = getIdKodHakmilik(hm);
				    			    	strNoHakmilik = getNoHakmilik(hm);
				    			    	if(!getCellValue(cellNoLot_).equals("0")){
				    			    		strNoLot = getCellValue(cellNoLot_);	    	
				    			    	}else{
				    			    		strNoLot = getCellValue(cellNoLot);
				    			    		strIdKodLot = "3";
				    			    	}		

				    			    	strKegunaan = getCellValue(cellKegunaan);
			    			    		cukaiTerkini = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukai))));
			    			    		cukaiDenda = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukaiDenda)))); 			    		
			    			    		cukaiTunggakan = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukaiTunggakan)))); 			    		
			    			    		cukaiPengurangan = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukaiPengurangan)))); 			    		
			    			    		cukaiLebihan = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukaiTerlebih)))); 			    					    			    		
			    			    		cukaiJumlah = (cukaiTerkini+cukaiTunggakan+cukaiDenda+cukaiPengurangan)-cukaiLebihan;
			    			    	
			    			    	}else if(cellState.getStringCellValue().equalsIgnoreCase("NEGERI SEMBILAN")){
				    			    	HSSFCell cellNoHakmilik = myRowObj.getCell((short)2);
				    			    	HSSFCell cellNoLot = myRowObj.getCell((short)3);
				    			    	HSSFCell cellNoHakmilik_ = myRowObj.getCell((short)4);
				    			    	HSSFCell cellNoLot_ = myRowObj.getCell((short)5);
				    			    	HSSFCell cellKegunaan = myRowObj.getCell((short)6);
				    			    	HSSFCell cellCukai = myRowObj.getCell((short)7);
				    			    	HSSFCell cellCukaiTunggakan = myRowObj.getCell((short)8);
				    			    	HSSFCell cellCukaiDenda = myRowObj.getCell((short)9);
				    			    	HSSFCell cellCukaiPengurangan = myRowObj.getCell((short)10);
				    			    	HSSFCell cellCukaiTerlebih = myRowObj.getCell((short)11);

				    			    	String hm = "";
				    			    	if(!getCellValue(cellNoHakmilik_).equals("0")){
					    			    	hm = getCellValue(cellNoHakmilik_);	    	
				    			    		if(!getCellValue(cellNoHakmilik_).equals("")){
						    			    	hm = getCellValue(cellNoHakmilik_);	    	
   			
				    			    		}else{
					    			    		hm = getCellValue(cellNoHakmilik);		
				    			    		}
				    			    	}else{
				    			    		hm = getCellValue(cellNoHakmilik);
				    			    		if(!getCellValue(cellNoHakmilik).equals("")){
						    			    	hm = getCellValue(cellNoHakmilik);	    	  			
				    			    		}
				    			    	}
				    			    	myLog.info("hm="+hm);
				    			    	strKodHakmilik = getKodHakmilik(hm);
				    			    	strIdKodHakmilik = getIdKodHakmilik(hm);
				    			    	strNoHakmilik = getNoHakmilik(hm);
				    			    	if(!getCellValue(cellNoLot_).equals("0")){
				    			    		strNoLot = getCellValue(cellNoLot_);	    	
				    			    	}else{
				    			    		strNoLot = getCellValue(cellNoLot);
				    			    		strIdKodLot = "3";
				    			    	}		

				    			    	strKegunaan = getCellValue(cellKegunaan);
			    			    		cukaiTerkini = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukai))));
			    			    		cukaiDenda = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukaiDenda)))); 			    		
			    			    		cukaiTunggakan = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukaiTunggakan)))); 			    		
			    			    		cukaiPengurangan = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukaiPengurangan)))); 			    		
			    			    		cukaiLebihan = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukaiTerlebih)))); 			    		
			    			    		
			    			    		cukaiJumlah = (cukaiTerkini+cukaiTunggakan+cukaiDenda+cukaiPengurangan)-cukaiLebihan;

			    			    	}else if(cellState.getStringCellValue().equalsIgnoreCase("PAHANG")){
				    			    	HSSFCell cellNoHakmilik = myRowObj.getCell((short)2);
				    			    	HSSFCell cellNoLot = myRowObj.getCell((short)3);
				    			    	HSSFCell cellNoHakmilik_ = myRowObj.getCell((short)4);
				    			    	HSSFCell cellNoLot_ = myRowObj.getCell((short)5);
				    			    	HSSFCell cellKegunaan = myRowObj.getCell((short)6);
				    			    	HSSFCell cellCukai = myRowObj.getCell((short)7);
				    			    	HSSFCell cellCukaiTerlebih = myRowObj.getCell((short)8);
				    			    	
				    			    	HSSFCell cellCukaiTunggakan = myRowObj.getCell((short)9);
				    			    	HSSFCell cellCukaiDenda = myRowObj.getCell((short)10);
				    			    	//HSSFCell cellCukaiPengurangan = myRowObj.getCell((short)10);

				    			    	String hm = "";
				    			    	if(!getCellValue(cellNoHakmilik_).equals("0")){
					    			    	hm = getCellValue(cellNoHakmilik_);	    	
				    			    		if(!getCellValue(cellNoHakmilik_).equals("")){
						    			    	hm = getCellValue(cellNoHakmilik_);	    	
   			
				    			    		}else{
					    			    		hm = getCellValue(cellNoHakmilik);		
				    			    		}
				    			    	}else{
				    			    		hm = getCellValue(cellNoHakmilik);
				    			    		if(!getCellValue(cellNoHakmilik).equals("")){
						    			    	hm = getCellValue(cellNoHakmilik);	    	  			
				    			    		}
				    			    	}
				    			    	myLog.info("hm="+hm);
				    			    	strKodHakmilik = getKodHakmilik(hm);
				    			    	strIdKodHakmilik = getIdKodHakmilik(hm);
				    			    	strNoHakmilik = getNoHakmilik(hm);
				    			    	if(!getCellValue(cellNoLot_).equals("0")){
				    			    		strNoLot = getCellValue(cellNoLot_);
				    			    		if(strNoLot.contains("LOT")){
				    			    			strNoLot = getNoLot(strNoLot);	}

				    			    	}else{
				    			    		strNoLot = getCellValue(cellNoLot);
				    			    		if(strNoLot.contains("PT")){
				    			    			strNoLot = getNoLot(strNoLot);	}	
				    			    		
				    			    		strIdKodLot = "3";
				    			    	}		

				    			    	strKegunaan = getCellValue(cellKegunaan);
			    			    		cukaiTerkini = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukai))));
			    			    		cukaiDenda = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukaiDenda)))); 			    		
			    			    		cukaiTunggakan = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukaiTunggakan)))); 			    		
			    			    		//cukaiPengurangan = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukaiPengurangan)))); 			    		
			    			    		cukaiLebihan = Double.parseDouble(Utils.RemoveComma(String.valueOf(getCellValue(cellCukaiTerlebih)))); 			    		
			    			    		
			    			    		cukaiJumlah = (cukaiTerkini+cukaiTunggakan+cukaiDenda+cukaiPengurangan)-cukaiLebihan;
			    			    		
			    			    	}else{
				    					throw new Exception("SILA PASTIKAN NAMA NEGERI YANG DIPILIH ");
				    					
			    			    	}
	    							Mukim mukim = new Mukim();
									Tblrujmukim rMukim = new Tblrujmukim();
	    							Daerah daerah = new Daerah();
	    							Negeri negeri = new Negeri();
	    							Tblrujlot lot = new Tblrujlot();
	    							Tblrujjenishakmilik jh = new Tblrujjenishakmilik();
	    							HakmilikCukai hakmilikCukai = new HakmilikCukai();
	    							CukaiTemp cukaiTemp = new CukaiTemp();
					    			
					    			hakmilik.setIdMukim(Long.parseLong(idMukim));
									hakmilik.setMukim(mukim);									
									rMukim.setNamaMukim(String.valueOf(getCellValue(cellMukim)));			//TBLHTPCUKAITEMP
									cukaiTemp.setRujMukim(rMukim);									
									hakmilik.setIdHakmilik(Long.parseLong(idDaerah));
									hakmilik.setIdDaerah(Long.parseLong(idDaerah));
									daerah.setIdDaerah(Long.parseLong(idDaerah));
									hakmilik.setDaerah(daerah);
									hakmilik.setIdNegeri(Long.parseLong(idnegeri));
									negeri.setIdNegeri(Long.parseLong(idnegeri));
									hakmilik.setNegeri(negeri);			    			    	
									hakmilik.setIdJenisHakmilik(Long.parseLong(strIdKodHakmilik));
									jh.setIdJenishakmilik(Long.parseLong(strIdKodHakmilik));
									jh.setKodJenisHakmilik(strKodHakmilik);
									hakmilik.setRujJenisHakmilik(jh);
									hakmilik.setNoHakmilik(strNoHakmilik);
									hakmilik.setNoHakmlik(strNoHakmilik);
									cukaiTemp.setNoHakmilik(strKodHakmilik+strNoHakmilik);
									hakmilik.setIdJenisLot(Long.parseLong(strIdKodLot));
									lot.setIdLot(Long.parseLong(strIdKodLot));
									lot.setKodLot(strKodLot);
									hakmilik.setRujLot(lot);
									String noLot = strNoLot;
									if(noLot.contains(".0")){
										noLot = noLot.substring(0,noLot.indexOf(".0"));
									}
									hakmilik.setNoLot(noLot);
									cukaiTemp.setNoLot(strKodLot+noLot);
									hakmilik.setKegunaan(strKegunaan);	
									hakmilik.setIdPermohonan(0);		
									HakMilik htemp = null;
									htemp = new HakMilik();
									htemp = getICukai().getIdHakmilikCukai(hakmilik);
									if(htemp!=null){
										hakmilik.setIdPermohonan(htemp.getIdPermohonan());	//TBLHTPCUKAITEMP
	    			    			}
									cukaiTemp.setCukaiKenaBayar(cukaiTerkini);				//TBLHTPCUKAITEMP
									cukaiTemp.setTunggakkan(cukaiTunggakan);				//TBLHTPCUKAITEMP
									cukaiTemp.setCukaiTaliAir(cukaiTaliair);				//TBLHTPCUKAITEMP
									cukaiTemp.setCukaiParit(cukaiParit);					//TBLHTPCUKAITEMP
									cukaiTemp.setCukailain(cukaiLain);										//TBLHTPCUKAITEMP
									cukaiTemp.setDenda(cukaiDenda);						//TBLHTPCUKAITEMP
									cukaiTemp.setPengurangan(cukaiPengurangan);			//TBLHTPCUKAITEMP
									cukaiTemp.setPengecualian(cukaiPengecualian);		//TBLHTPCUKAITEMP
									cukaiTemp.setLebihan(cukaiLebihan);											//TBLHTPCUKAITEMP
									cukaiTemp.setTotalcukai(cukaiJumlah);			//TBLHTPCUKAITEMP
									cukaiTemp.setTahun(tahunCukai);
									hakmilik.setHakmilikCTemp(cukaiTemp);
									hakmilik.setIdMasuk(Long.parseLong(userId));	
									
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
									hakmilikCukai.setIdHakmilikCukai(0);
									if(htemp!=null){
										hakmilikCukai.setIdHakmilikCukai(getICukai().getIdHakmilikCukai(hakmilik).getHakmilikCukai().getIdHakmilikCukai());
									}
									hakmilik.setHakmilikCukai(hakmilikCukai);
									getICukai().simpanCukaiHakmilikTemp(hakmilik);
									getICukai().simpanCukaiHakmilikTerperinci(hakmilik);									
			    			    	totalRecord ++;
			    			    	myLog.info("totalRecord="+totalRecord);
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
			//myLog.info("getIdDaerah:sql="+sql);
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
	private String getIdHakmilik(String hakMilikDesc)throws Exception {
		Db db = null;
		String returnValue = "0";
	    try{
	    	db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Jenishakmilik");
			r.add("KOD_JENIS_HAKMILIK",hakMilikDesc.toUpperCase());
			String sql = r.getSQLSelect("Tblrujjenishakmilik");
			 ResultSet rs = stmt.executeQuery(sql);
		      if (rs.next()) {
		    	  returnValue = rs.getString("id_Jenishakmilik");
		      }
	    }
	    catch(Exception e){
			//e.printStackTrace();
			throw new Exception("ID JENIS HAKMILIK TIDAK DITEMUI");
		}
	    finally {
		      if (db != null) db.close();
	    }
	    return returnValue;
	   
	}
	
	private String getIdLot(String cellValue) throws Exception {
		Db db = null;
		String returnValue = "0";
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Lot");
			r.add("Keterangan",cellValue.toUpperCase());
			String sql = r.getSQLSelect("Tblrujlot");
//			myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				returnValue = rs.getString("id_Lot");
			}
		}catch(Exception e){
			throw new Exception("ID KOD LOT TIDAK DITEMUI");
			//e.printStackTrace();
		}
	    finally {
		      if (db != null) db.close();
	    }
		return returnValue;
	}
	
	private ICukai getICukai(){
		if(iCukai==null){
			iCukai = new CukaiBean();
		}
		
		return iCukai;
		
	}
	
	private String getKodHakmilik(String str){
		String kod = "";
		if(str.contains("HS(D)")||str.contains("HSD")){
			kod = "HSD";
    	}else if(str.contains("HS(M)")||str.contains("HSM")){
    		kod = "HSM";
    	}else if(str.contains("GM")){
    		kod = "GM";
    	}else if(str.contains("G ")||str.contains("GERAN")){
    		kod = "GRN"; 
    	}else if(str.contains("PM")){
    		kod = "PM"; 
    	}else if(str.contains("QTR")){
    		kod = "QTR"; 
    	}else if(str.contains("PN")||str.contains("PJK")){
    		kod = "PN"; 
    	}else if(str.contains("SPB")){
    		kod = "SPB"; 
    	}else if(str.contains("S/A")){
    		kod = "SA"; 
    	}else if(str.contains("GRN")){
    		kod = "GRN"; 
    	}else if(str.contains("DSPN")){
    		kod = "DSP"; 
    	}else if(str.contains("AA")){
    		kod = "AA"; 
    	}else if(str.contains("XPM")){
    		kod = "PM"; 
    	}else if(str.contains("G")){
    		try {
    		    int x = Integer.parseInt(str.substring(1,2));
    		    //System.out.println(x);
        		kod = "GRN"; 
    		}catch(NumberFormatException nFE) {
    		    //System.out.println("Not an Integer");
    		}
    	}else{
    		kod = "00";  		
    	}
		return kod;
		
	}
	private String getIdKodHakmilik(String str){
		String idKod = "";
		if(str.contains("G ")||str.contains("GRN")||str.contains("GERAN")){
    		idKod = "1";
		}else if(str.contains("PN")||str.contains("PJK")){
			idKod = "2";
    	}else if(str.contains("HS(D)")||str.contains("HSD")){
			idKod = "3";
    	}else if(str.contains("HS(M)")||str.contains("HSM")){
    		idKod = "6";
    	}else if(str.contains("GM")){
    		idKod = "4";
    	}else if(str.contains("PM")){
    		idKod = "5";
    	}else if(str.contains("QTR")){
    		idKod = "12"; 
    	}else if(str.contains("SPB")){
    		idKod = "41"; 
    	}else if(str.contains("S/A")){
    		idKod = "55"; 
    	}else if(str.contains("GN")){
			idKod = "133";
    	}else if(str.contains("PL")){
			idKod = "28";
    	}else if(str.contains("DSPN")){
			idKod = "76";
    	}else if(str.contains("AA")){
			idKod = "17";
    	}else if(str.contains("XPJK")){
			idKod = "134";
    	}else if(str.contains("G")){
    		try {
    		    int x = Integer.parseInt(str.substring(1,2));
    		    //System.out.println(x);
    		    idKod = "1";
    		}catch(NumberFormatException nFE) {
    		    //System.out.println("Not an Integer");
    		}
    	}else{
			idKod = "0";
   		}
		return idKod;
		
	}	
	private String getNoHakmilik(String str){
		String idKod = "";
		if(str.contains("HS(D)")||str.contains("HS(M)")){
			idKod = Utils.ltrim(str.substring(5,str.length()));
    	}else if(str.contains("GM")||str.contains("G ")||str.contains("PM")||str.contains("PN")){
			idKod = Utils.ltrim(str.substring(2,str.length()));
    	}else if(str.contains("GN")||str.contains("PL")||str.contains("AA")||str.contains("GD")){
			idKod = Utils.ltrim(str.substring(2,str.length()));
    	}else if(str.contains("QTR")||str.contains("SPB")||str.contains("GRN")){
			idKod = Utils.ltrim(str.substring(3,str.length()));
    	}else if(str.contains("S/A")||str.contains("HSM")||str.contains("HSD")||str.contains("PJK")){
			idKod = Utils.ltrim(str.substring(3,str.length()));
      	}else if(str.contains("DSPN")){
			idKod = Utils.ltrim(str.substring(4,str.length()));
      	}else if(str.contains("GERAN")){
			idKod = Utils.ltrim(str.substring(5,str.length()));
      	}else if(str.contains("G")){
	   		try {
    		    int x = Integer.parseInt(str.substring(1,2));
    		    //System.out.println(x);
    		    idKod = Utils.ltrim(str.substring(1,str.length()));
    		}catch(NumberFormatException nFE) {
    		    //System.out.println("Not an Integer");
    		}				
    	}else {
    		idKod = str;
    		if(str.contains(".0")){
    			idKod = str.substring(0,str.indexOf(".0"));
			}
    	}
		return idKod;
		
	}
	
	private String getIdKodLot(String str){
		String idKod = "";
		if(str.contains("LOT")){
    		idKod = "1";
    	}else{
			idKod = "0";
   		}
		return idKod;
		
	}	
	private String getNoLot(String str){
		String idKod = "";
		if(str.contains("LOT")||str.contains("XSPB")||str.contains("XGRN")){
			idKod = Utils.ltrim(str.substring(3,str.length()));
    	}else if(str.contains("PT")){
			idKod = Utils.ltrim(str.substring(2,str.length()));
      	}else if(str.contains("G")){
	   		try {
    		    int x = Integer.parseInt(str.substring(1,2));
    		    //System.out.println(x);
    		    idKod = Utils.ltrim(str.substring(1,str.length()));
    		}catch(NumberFormatException nFE) {
    		    //System.out.println("Not an Integer");
    		}				
    	}else {
    		idKod = str;
    		if(str.contains(".0")){
    			idKod = str.substring(0,str.indexOf(".0"));
			}
    	}
		return idKod;
		
	}
	
}//close class


