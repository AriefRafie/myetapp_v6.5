package ekptg.view.pdt;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.pdt.FrmModelTanahRizabData;

public class FrmCariMaklumatTRM_backup extends AjaxBasedModule {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	public int currIndex = 0;
	static Logger myLogger = Logger.getLogger(FrmCariMaklumatTRM_backup.class);

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = request.getSession();
		FrmModelTanahRizabData tanahRizab = new FrmModelTanahRizabData();
		Vector vList = null;

		String moduleName = (String) session.getAttribute("_portal_module");
		String[] mName = moduleName.split("_");

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}

		String selectedTab = getParam("selectedTab");
		if ("".equalsIgnoreCase(selectedTab)) {
			selectedTab = "0";
		}

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP
		String action2 = getParam("action2"); 									// PAGING SHJ
		// PAGING SHJ
		String submit = getParam("command");
		String vm = "";
		String RO_General = "";
		String mode = getParam("mode");
		String DIS_General = "";
		String step = getParam("step");
		String hitButton = getParam("hitButton");
		this.context.put("hitButton", hitButton);

		// GET ID PARAM
		String idWarta = getParam("idWarta");
		String idPelan = getParam("idPelan");
		String idTnhRzbMly = getParam("idTnhRzbMly");
		String paramNegeri = getParam("paramNegeri");
		String paramDaerah = getParam("paramDaerah");
		String paramMukim = getParam("paramMukim");
		String paramKawasan = getParam("paramKawasan");
		String paramNoWarta = getParam("paramNoWarta");
		String paramJenis = getParam("paramJenis");
		String pageDoChange = getParam("pageDoChange");
		String uploadMethod = getParam("uploadMethod");
		String saveMode = getParam("saveMode");
		
		// paramKandungan refer kepada Papar Warta
		String paramWarta = getParam("paramWarta");
		String paramTarikh = getParam("paramTarikh");
		String paramNoPelan = getParam("paramNoPelan");
		String paramLuas = getParam("paramLuas");
		// paramKandungan refer kepada Papar Pelan
		String paramKandungan = getParam("paramKandungan");
		String paramStatus = getParam("paramStatus");
		
		this.context.put("showTab", getParam("showTab"));
		

		if(submit.equals("doFilter")){
			negeriDaerahMukimSelected(paramNegeri, paramDaerah,paramMukim);
			if(pageDoChange.equals("carian")){
				vList = new Vector<>();
				setupPage(session,null,null);
				//context.put("ListResult", vList);
				vm = "app/pdt/trm/frmCariMaklumatTRM.jsp";
			}else{
				setSemula();
				negeriDaerahMukimSelected(paramNegeri, paramDaerah,paramMukim);
				this.context.put("selectedTab", selectedTab);
				this.context.put("mode", "new");
				this.context.put("idWarta", idWarta);
				this.context.put("saveMode", saveMode);
				this.context.put("idWartaNew", getParam("idWartaNew"));
				this.context.put("idPelanNew", getParam("idPelanNew"));
				this.context.put("idWartaWujud", getParam("idWartaWujud"));
				this.context.put("idPelanWujud", getParam("idPelanWujud"));
				this.context.put("idTnhRzbMlyWujud", getParam("idTnhRzbMlyWujud"));
				this.context.put("idWartaGanti", getParam("idWartaGanti"));
				this.context.put("idPelanGanti", getParam("idPelanGanti"));
				this.context.put("idTnhRzbMlyGanti", getParam("idTnhRzbMlyGanti"));
				this.context.put("paramStatus",paramStatus(paramStatus));
				vm = "app/pdt/trm/frmMaklumatTanahRizabMelayu.jsp";
			}
		}else if(submit.equals("doChanges")){
			vList = new Vector<>();
			vList = tanahRizab.carianTanahRizab(paramNegeri, paramDaerah, paramMukim, paramNoWarta, paramNoPelan, paramStatus,paramJenis);
			setupPage(session, action, vList);
			negeriDaerahMukimSelected(paramNegeri, paramDaerah,paramMukim);
			this.context.put("paramStatus",paramStatus(getParam(paramStatus)));
			vm = "app/pdt/trm/frmCariMaklumatTRM.jsp";
		}else{
			if(hitButton.equals("cari")){
				vList = new Vector<>();
				vList = tanahRizab.carianTanahRizab(paramNegeri, paramDaerah, paramMukim, paramNoWarta, paramNoPelan, paramStatus,paramJenis);
				setupPage(session, action, vList);
//				this.context.put("ListResult", vList);
				negeriDaerahMukimSelected(paramNegeri, paramDaerah,paramMukim);
				this.context.put("paramStatus",paramStatus(getParam(paramStatus)));
				vm = "app/pdt/trm/frmCariMaklumatTRM.jsp";
			}else if(hitButton.equals("tambah")){
				this.context.put("idWartaWujud", "");
				this.context.put("idPelanWujud", "");
				this.context.put("idTnhRzbMlyWujud", "");
				this.context.put("idWartaBatal", "");
				this.context.put("idPelanBatal", "");
				this.context.put("idTnhRzbMlyBatal", "");
				this.context.put("idWartaGanti", "");
				this.context.put("idPelanGanti", "");
				this.context.put("idTnhRzbMlyGanti", "");
				this.context.put("idWarta", "");
				this.context.put("kawasan", "");
				this.context.put("noWarta", "");
				this.context.put("tarikhWarta", "");
				this.context.put("noPelan", "");
				this.context.put("luas", "");
				this.context.put("saveMode", "simpanBaru");
				this.context.put("mode", "tambahBaru");
				this.context.put("selectedTab", selectedTab);
				this.context.put("paramStatus",paramStatus(getParam(paramStatus)));
				vm = "app/pdt/trm/frmMaklumatTanahRizabMelayu.jsp";
				
				/*-----------------*/
				
				
			}else if ("saveWartaPewujudan".equals(hitButton)) {
				 Long idWartaNew = null, idPelanNew = null;
				 String idWartaWujud = getParam("idWartaWujud");
				 String idPelanWujud = getParam("idPelanWujud");
				 String idTnhRzbMlyWujud = getParam("idTnhRzbMlyWujud");
				 paramKawasan = getParam("kawasan");
				 paramNoWarta = getParam("noWarta");
				 paramTarikh = getParam("tarikhWarta");
				 paramNoPelan = getParam("noPelan");
				 paramLuas = getParam("luas");
				 paramStatus = getParam("paramStatus");
				if(idWartaWujud.equals("")){
					 Long[] id = tanahRizab.saveWartaPewujudan(paramNegeri,paramDaerah,paramMukim,paramKawasan,
							 paramNoWarta,paramTarikh,paramNoPelan,paramLuas,paramStatus,session);
					 idWartaNew = id[0];idPelanNew = id[1];
					 if(saveMode.equals("simpanBaru")){
						 this.context.put("idWarta", idWartaNew);
					 }
					 this.context.put("idWartaNew", idWartaNew);
					 this.context.put("idPelanNew", idPelanNew);
				}else{
					tanahRizab.updateWartaPewujudan(idWartaWujud,idPelanWujud,idTnhRzbMlyWujud,paramNegeri,paramDaerah,paramMukim,paramKawasan,
							 paramNoWarta,paramTarikh,paramNoPelan,paramLuas,paramStatus,session);
					this.context.put("idWarta", idWartaWujud);
					this.context.put("idPelan", idPelanWujud);
					this.context.put("idWartaNew", idWartaWujud);
					this.context.put("idPelanNew", idPelanWujud);
					this.context.put("idTnhRzbMly", idTnhRzbMlyWujud);
				}
				setSemula();
				negeriDaerahMukimSelected(paramNegeri, paramDaerah,paramMukim);
				this.context.put("paramStatus",paramStatus(getParam("paramStatus")));
				this.context.put("selectedTab", selectedTab);
				this.context.put("mode", "upload");
				vm = "app/pdt/trm/frmMaklumatTanahRizabMelayu.jsp";
			}else if ("saveWartaBatal".equals(hitButton)) {
				 Long idWartaNew = null, idPelanNew = null;
				 String idWartaBatal = getParam("idWartaBatal");
				 String idPelanBatal = getParam("idPelanBatal");
				 String idTnhRzbMlyBatal = getParam("idTnhRzbMlyBatal");
				 paramNoWarta = getParam("noWarta");
				 paramTarikh = getParam("tarikhWarta");
				 paramNoPelan = getParam("noPelan");
				 paramLuas = getParam("luas");
				if(idWartaBatal.equals("")){
					 Long[] id = tanahRizab.saveWartaBatal(idWarta,paramNoWarta,paramTarikh,paramNoPelan,paramLuas,session);
					 idWartaNew = id[0];idPelanNew = id[1];
					 this.context.put("idWartaNew", idWartaNew);
					 this.context.put("idPelanNew", idPelanNew);
				}else{
					tanahRizab.updateWartaBatal(idWartaBatal,idPelanBatal,idTnhRzbMlyBatal,paramNegeri,paramDaerah,paramMukim,paramKawasan,
							 paramNoWarta,paramTarikh,paramNoPelan,paramLuas,paramStatus,session);
					this.context.put("idWarta", idWarta);
					this.context.put("idPelan", idPelan);
					this.context.put("idWartaNew", idWartaBatal);
					this.context.put("idPelanNew", idPelanBatal);
					this.context.put("idTnhRzbMly", idTnhRzbMlyBatal);
				}
				setSemula();
				this.context.put("selectedTab", selectedTab);
				this.context.put("mode", "upload");
				vm = "app/pdt/trm/frmMaklumatTanahRizabMelayu.jsp";
			}else if ("saveWartaGanti".equals(hitButton)) {
				 Long idWartaNew = null, idPelanNew = null;
				 String idWartaGanti = getParam("idWartaGanti");
				 String idPelanGanti = getParam("idPelanGanti");
				 String idTnhRzbMlyGanti = getParam("idTnhRzbMlyGanti");
				 paramKawasan = getParam("kawasan");
				 paramNoWarta = getParam("noWarta");
				 paramTarikh = getParam("tarikhWarta");
				 paramNoPelan = getParam("noPelan");
				 paramLuas = getParam("luas");
				 paramStatus = getParam("paramStatus");
				if(idWartaGanti.equals("")){
					 Long[] id = tanahRizab.saveWartaPenggantian(idWarta,paramNegeri,paramDaerah,paramMukim,paramKawasan,
							 paramNoWarta,paramTarikh,paramNoPelan,paramLuas,paramStatus,session);
					 idWartaNew = id[0];idPelanNew = id[1];
					 this.context.put("idWartaNew", idWartaNew);
					 this.context.put("idPelanNew", idPelanNew);
				}else{
					tanahRizab.updateWartaPewujudan(idWartaGanti,idPelanGanti,idTnhRzbMlyGanti,paramNegeri,paramDaerah,paramMukim,paramKawasan,
							 paramNoWarta,paramTarikh,paramNoPelan,paramLuas,paramStatus,session);
					this.context.put("idWarta", idWarta);
					this.context.put("idPelan", idPelan);
					this.context.put("idWartaNew", idWartaGanti);
					this.context.put("idPelanNew", idPelanGanti);
					this.context.put("idTnhRzbMly", idTnhRzbMlyGanti);
				}
				setSemula();
				negeriDaerahMukimSelected(paramNegeri, paramDaerah,paramMukim);
				this.context.put("paramStatus",paramStatus(getParam("paramStatus")));
				this.context.put("selectedTab", selectedTab);
				this.context.put("mode", "upload");
				vm = "app/pdt/trm/frmMaklumatTanahRizabMelayu.jsp";
			}else if ("openPageUpload".equals(hitButton)) {
				setSemula();
				this.context.put("idWarta", getParam("idWarta"));
				this.context.put("idWartaWujud", getParam("idWartaWujud"));
				this.context.put("idPelanWujud", getParam("idPelanWujud"));
				this.context.put("idWartaBatal", getParam("idWartaBatal"));
				this.context.put("idPelanBatal", getParam("idPelanBatal"));
				this.context.put("idWartaGanti", getParam("idWartaGanti"));
				this.context.put("idPelanGanti", getParam("idPelanGanti"));
				this.context.put("uploadMethod", getParam("uploadMethod"));
				this.context.put("step", getParam("step"));
				this.context.put("idWartaNew", getParam("idWartaNew"));
				this.context.put("idPelanNew", getParam("idPelanNew"));
				this.context.put("paramNegeri", getParam("paramNegeri"));
				this.context.put("paramDaerah", getParam("paramDaerah"));
				this.context.put("paramMukim", getParam("paramMukim"));
				this.context.put("paramStatus",getParam("paramStatus"));
				vm = "app/pdt/trm/frmMuatNaikImejTRM.jsp";
			}else if ("saveUploadFile".equals(hitButton)) {
				myLogger.info(getParam("step"));
				FileItem item = getItem();
				if(!item.equals(null)){
					if(uploadMethod.equals("uploadWujud")){
							vm = wartaWujudDanGanti(session, tanahRizab,
									"0", paramNegeri, paramDaerah,
									paramMukim, item);
							Vector data = getWartaKewujudan(idWarta,tanahRizab);
					}else if(uploadMethod.equals("uploadBatal")){
						if(getParam("step").equals("warta")){
							//save warta
							tanahRizab.saveFileWarta(item,getParam("idWartaNew"),session);
							Vector data = getWartaPembatalan(idWarta,tanahRizab);
							this.context.put("selectedTab", "1");
							this.context.put("mode", "view");
							vm = "app/pdt/trm/frmMaklumatTanahRizabMelayu.jsp";
						}else{
							//save pelan 
							tanahRizab.saveFilePelan(item,getParam("idPelanNew"),session);
							this.context.put("selectedTab", "1");
							this.context.put("mode", "view");
							vm = "app/pdt/trm/frmMaklumatTanahRizabMelayu.jsp";
						}
					}else{
						vm = wartaWujudDanGanti(session, tanahRizab,
								"2", paramNegeri, paramDaerah,
								paramMukim, item);
						Vector data = getWartaPenggantian(idWarta,tanahRizab);
					}
				}else{
					negeriDaerahMukimSelected(paramNegeri, paramDaerah,paramMukim);
					setSemula();
					this.context.put("paramStatus",paramStatus(getParam("paramStatus")));
					this.context.put("selectedTab", selectedTab);
					this.context.put("mode", "upload");
					vm = "app/pdt/trm/frmMaklumatTanahRizabMelayu.jsp";
				}
			}else if ("papar".equals(hitButton)) {
				if(selectedTab.equals("1")){
					getWartaPembatalan(idWarta,tanahRizab);
					this.context.put("mode", "view");
				}else{
					Vector data = getWartaKewujudan(idWarta, tanahRizab);
					if(data.size() != 0){
						this.context.put("mode", "view");
					}else{
						resetField(selectedTab, paramStatus);
					}
				}
				this.context.put("saveMode", "simpanSedia");
				this.context.put("idWarta", idWarta);
				this.context.put("selectedTab", selectedTab);
				String warta = getParam("warta");
				this.context.put("warta", warta);
				this.context.put("showTab", "Y");
				vm = "app/pdt/trm/frmMaklumatTanahRizabMelayu.jsp";
			} else if ("kemaskini".equals(hitButton)) {
				getWartaKewujudan(idWarta,tanahRizab);
				this.context.put("mode", "tambahBaru");
				vm = "app/pdt/trm/frmMaklumatTanahRizabMelayu.jsp";
			} else if ("kemaskinibatal".equals(hitButton)) {
				getWartaPembatalan(idWarta,tanahRizab);
				this.context.put("mode", "tambahBaru");
				vm = "app/pdt/trm/frmMaklumatTanahRizabMelayu.jsp";
			} else if ("kemaskiniganti".equals(hitButton)) {
				getWartaPenggantian(idWarta,tanahRizab);
				this.context.put("mode", "tambahBaru");
				vm = "app/pdt/trm/frmMaklumatTanahRizabMelayu.jsp";
			} else if ("muatnaik".equals(hitButton)) {
				vList = new Vector<>();
				vList = tanahRizab.findDataTblPdtMuatNaikTrm();
				context.put("ListResult", vList);
				this.context.put("selectNegeriUpload", HTML.SelectNegeri("paramNegeriUpload",Utils.parseLong(paramNegeri), null, ""));
				vm = "app/pdt/trm/frmMuatNaikExcelTRM.jsp";
			} else if ("kembaliexcel".equals(hitButton)) {
				vm = "app/pdt/trm/frmMuatNaikExcelTRM.jsp";
			} else if ("kembalilist".equals(hitButton)) {
				vm = "app/pdt/trm/frmCariMaklumatTRM.jsp";
			} else if ("paparexcel".equals(hitButton)) {
				vList = new Vector<>();
				vList = tanahRizab.getDataExcel(getParam("paramNegeri"));
				String namaNegeri = "";
				Double luasNegeri = Double.valueOf("0.00");
				for (Object object : vList) {
					Hashtable hashHeader = (Hashtable) object;
					String stringLuas = hashHeader.get("luas") == null ? "0.00" : (hashHeader.get("luas").toString());
					Double floatLuas = Double.valueOf(stringLuas);
					luasNegeri = luasNegeri + floatLuas;//Double. Float.sum(luasNegeri, floatLuas);
					namaNegeri = hashHeader.get("namaNegeri").toString();
				}
				this.context.put("namaNegeri", namaNegeri);
				this.context.put("luasNegeri", luasNegeri);
				this.context.put("ListExcel", vList);
				vm = "app/pdt/trm/frmMaklumatExcelTRM.jsp";
				this.context.put("mode", "kemaskiniganti");
			}else{
				String warta = getParam("warta");
				Vector data = new Vector<>();
				if(action2.equals("viewTab")){
					if (selectedTab.equalsIgnoreCase("0")) {
						if(warta.equals("batal") || warta.equals("ganti")){
							String idWartaAsalNganti = getParam("idWartaAsalNganti");
							 data = getWartaKewujudanByIdAsalNGanti(idWartaAsalNganti,tanahRizab);
						}else{
							 data = getWartaKewujudan(idWarta,tanahRizab);
						}
						if(data.size() != 0){
							this.context.put("mode", "view");
						}else{
							resetField(selectedTab, paramStatus);
						}
					}else if (selectedTab.equalsIgnoreCase("1")) {
						if(warta.equals("wujud") || warta.equals("ganti")){
							String idWartaAsalNganti = getParam("idWartaAsalNganti");
							data = getWartaPembatalanByIdAsal(idWartaAsalNganti,tanahRizab);
						}else{
							 data = getWartaPembatalan(idWarta,tanahRizab);
						}
						if(data.size() != 0){
							this.context.put("mode", "view");
						}else{
							resetField(selectedTab, paramStatus);
						}
					} else if (selectedTab.equalsIgnoreCase("2")) {
						if(warta.equals("batal") || warta.equals("wujud")){
							String idWartaAsalNganti = getParam("idWartaAsalNganti");
							data = getWartaPenggantianByIdAsal(idWartaAsalNganti, tanahRizab);
						}else{
							data = getWartaPenggantian(idWarta,tanahRizab);
						}
						if(data.size() != 0){
							this.context.put("mode", "view");
						}else{
							resetField(selectedTab, paramStatus);
						}
					}
					vm = "app/pdt/trm/frmMaklumatTanahRizabMelayu.jsp";
					this.context.put("selectedTab", selectedTab);
				}else{
					if ("muatnaikSave".equals(submit)) {
					String paramNegeriUpload = getParam("paramNegeriUpload");
					DiskFileItemFactory factory = new DiskFileItemFactory();
					ServletFileUpload upload = new ServletFileUpload(factory);
					boolean isMultipart = ServletFileUpload.isMultipartContent(request);
					boolean success = false;
					InputStream is = null;
					try {
						if (isMultipart) {
							List items = upload.parseRequest(request);
							int maxMB = 2; // set maximum size in MB
							long maxSize = ((1024 * 1024) * maxMB);
							int totalRecord = 0;

							// List<StrDokunitsyer> list = new
							// ArrayList<StrDokunitsyer>();
							List<FrmUploadExcelForm> list = new ArrayList<FrmUploadExcelForm>();
							Iterator itr = items.iterator();
							while (itr.hasNext()) {
								FileItem item = (FileItem) itr.next();
								Workbook workbook = null; // Declare XSSF
								if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {

									long itemSize = item.getSize();
									String namaFail = "";

									if (itemSize > maxSize) { // check size
										context.put("maxsize", true);
									} else {
										namaFail = item.getName();
										System.out.println(item.getName());
										is = (InputStream) item.getInputStream();
										InputStream inp = is;
										// WorkBook
										String fileExtn = GetFileExtension(item.getName());
										
										/*
										if (fileExtn.equalsIgnoreCase("xlsx")) {
											//POIFSFileSystem poifs = new POIFSFileSystem(inp);
											workbook = new XSSFWorkbook(inp);
											//POIFSFileSystem fs = new POIFSFileSystem(inp);
										}

										if (fileExtn.equalsIgnoreCase("xls")) {
											POIFSFileSystem fs = new POIFSFileSystem(inp);
											//workbook = new HSSFWorkbook(fs);
										}
										*/
										
										
										Sheet sheet = null;
										try {

										    workbook = WorkbookFactory.create(inp);
										    sheet = workbook.getSheetAt(0);
										}
										catch (Exception e) {
										}

										if (sheet == null) {

										    try {

										        workbook = new XSSFWorkbook(inp);
										        sheet = workbook.getSheetAt(0);
										    }
										    catch (Exception e) {
										    }
										}
										
										
										
										

										if (fileExtn.equals("xls") || fileExtn.equals("xlsx")) 
										{
											// for (int i = 0; i <
											// workbook.getNumberOfSheets(); i++) {
											//HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
											System.out.println("Sheet Name :" + workbook.getSheetName(0));
											List<String> header = new ArrayList<String>();
											// startIndex = 0;
											boolean flagHeaderLoop = false;
											boolean flagFoundHeader = false;

											// Iterate through each rows one by
											// one
											Iterator rowIterator = sheet.rowIterator();
											while (rowIterator.hasNext()) {
												Row row = (Row) rowIterator.next();

												FrmUploadExcelForm form = new FrmUploadExcelForm();
												// syer.setSheet(workbook.getSheetName(i));
												// syer.setIdPermohonan(pengesahanKpp.getIdPermohonan());
												// syer.setNoVersi(noVersi);

												// For each row, iterate through all the
												// columns
												Iterator<Cell> cellIterator = row.cellIterator();
												System.out.print((row.getRowNum() + 1) + "\t");

												// syer.setBaris(new
												// Long(row.getRowNum() + 1));
												boolean flag = false;
												flagHeaderLoop = false;
												currIndex = 0;
												while (cellIterator.hasNext()) {
													Cell cell = cellIterator.next();

													// Check the cell type and
													// format accordingly
													switch (cell.getCellType()) {
													case Cell.CELL_TYPE_NUMERIC:
														
//														if (HSSFDateUtil.isCellDateFormatted(cell)) {
//															mappingColumn(cell.getDateCellValue() + "", form, "num");
//															System.out.print(cell.getDateCellValue() + "\t");
//														} else {
//															System.out.print(cell.getNumericCellValue() + "\t");
//															mappingColumn(cell.getNumericCellValue() + "", form, "num");
//														}

														break;
													case Cell.CELL_TYPE_STRING:

														String val = cell.getStringCellValue().toString();
														if (!flagFoundHeader) {
															flagFoundHeader = true;
															flagHeaderLoop = true;
														}

														if (flagFoundHeader && flagHeaderLoop) {
															System.out.print(cell.getStringCellValue() + "\t");
															header.add(cell.getStringCellValue());
														} else {
															System.out.print(cell.getStringCellValue() + "\t");
															mappingColumn(cell.getStringCellValue(), form, "str");
														}
														break;
													case Cell.CELL_TYPE_FORMULA:
														if (cell.getCachedFormulaResultType() == Cell.CELL_TYPE_NUMERIC) {
															System.out.print(cell.getNumericCellValue() + "\t");
															mappingColumn(cell.getNumericCellValue() + "", form, "num");
														}
														break;
													default:
														mappingColumn("", form, "num");
														System.out.print("\t");
														break;
													}

													if (flag) {
														break;
													}
												}
												System.out.println("");
												if (!flag && flagFoundHeader && !flagHeaderLoop) {
													form.setIdNegeri(paramNegeriUpload);
													list.add(form);
												}
											}
											// }
										} else {
											context.put("wrongEXT", true);
										}
									}
									//code insert into db here
									if(!namaFail.equals("")){
										tanahRizab.saveTblPdtMuatNaikTrm(namaFail,getParam("paramNegeriUpload"),session);
									}
									boolean flagSuccess = tanahRizab.saveUploadExcel(list,session);
									System.out.println(list.size());
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException(e.getMessage());
					} finally {
						if (success) {
							if (is != null)
								try {
									is.close();
								} catch (Exception e) {
								}
						}
					}
					vList = new Vector<>();
					vList = tanahRizab.findDataTblPdtMuatNaikTrm();
					context.put("ListResult", vList);
					setupPage(session, action, vList);
					vm = "app/pdt/trm/frmMuatNaikExcelTRM.jsp";
				}else{
					vList = new Vector<>();
					setupPage(session,null,null);
					context.put("ListResult", vList);
					negeriDaerahMukim("", "", "");
					vm = "app/pdt/trm/frmCariMaklumatTRM.jsp";
				}
				
				if(action.equals("getNext") || action.equals("getPrevious") || action.equals("getPage")){
					setupPage(session,action,vList);
				}
					
					
				}
			}
		}
		return vm;
	}


	private Vector getWartaKewujudanByIdAsalNGanti(String idWarta,
			FrmModelTanahRizabData tanahRizab) throws Exception {
		Vector data = new Vector();
		data.clear();
		if(idWarta == ""){
			return data;
		}

		data = tanahRizab.getDataByIdAsalNGanti(idWarta);

		if (data.size() != 0) {
			
			Hashtable h = (Hashtable) data.get(0);

			this.context.put("kandunganWarta", h.get("KANDUNGAN_WARTA"));
			this.context.put("kandunganPelan", h.get("KANDUNGAN_PELAN"));
			this.context.put("idWartaNew", h.get("ID_TBLPDTWARTA"));
			this.context.put("idPelanNew", h.get("ID_TBLPDTPELAN"));
			this.context.put("idWartaWujud", h.get("ID_TBLPDTWARTA"));
			this.context.put("idPelanWujud", h.get("ID_TBLPDTPELAN"));
			this.context.put("idTnhRzbMlyWujud", h.get("ID_TBLPDTTANAHRIZABMELAYU"));
			this.context.put("idNegeri", h.get("ID_NEGERI"));
			this.context.put("namaNegeri", h.get("NAMA_NEGERI"));
			this.context.put("idDaerah", h.get("ID_DAERAH"));
			this.context.put("namaDaerah", h.get("NAMA_DAERAH"));
			this.context.put("idMukim", h.get("ID_MUKIM"));
			this.context.put("namaMukim", h.get("NAMA_MUKIM"));
			this.context.put("kawasan", h.get("KAWASAN"));
			this.context.put("noWarta", h.get("NO_WARTA"));
			this.context.put("tarikhWarta", h.get("TARIKH_WARTA"));
			this.context.put("noPelan", h.get("NO_PELAN"));
			this.context.put("luas", h.get("LUAS"));
			this.context.put("flagStatusWarta", h.get("FLAG_STATUSWARTA"));
			
			this.context.put("selectNegeri", HTML.SelectNegeri("paramNegeri", Utils.parseLong(h.get("ID_NEGERI").toString()), "","onChange=\"doChangeNegeri();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByIdNegeri(h.get("ID_NEGERI").toString()+"", "paramDaerah", Utils.parseLong(h.get("ID_DAERAH").toString()), "", "onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(h.get("ID_DAERAH").toString().toString(), "paramMukim", Utils.parseLong(h.get("ID_MUKIM").toString()) == null ? null : Utils.parseLong(h.get("ID_MUKIM").toString()), "", ""));
			this.context.put("paramStatus",paramStatus(h.get("FLAG_STATUSWARTA").toString()));
		}else{
			this.context.put("mode", "tambahBaru");
		}
		
		return data;
	}


	private void resetField(String selectedTab, String paramStatus)
			throws Exception {
		this.context.put("kawasan", "");
		this.context.put("noWarta", "");
		this.context.put("tarikhWarta", "");
		this.context.put("noPelan", "");
		this.context.put("luas", "");
		this.context.put("selectedTab", selectedTab);
		this.context.put("paramStatus",paramStatus(getParam(paramStatus)));
		this.context.put("mode", "tambahBaru");
		this.context.put("kandunganWarta", false);
		this.context.put("kandunganPelan", false);
		negeriDaerahMukim("", "", "");
	}


	private String wartaWujudDanGanti(HttpSession session,
			FrmModelTanahRizabData tanahRizab, String selectedTab,
			String paramNegeri, String paramDaerah, String paramMukim,
			FileItem item) throws IOException, Exception {
		String vm;
		if(getParam("step").equals("warta")){
			//save warta
			tanahRizab.saveFileWarta(item,getParam("idWartaNew"),session);
			//negeriDaerahMukimSelected(paramNegeri, paramDaerah,paramMukim);
			//setSemula();
			
			this.context.put("selectedTab", selectedTab);
			this.context.put("mode", "view");
			//this.context.put("paramStatus",paramStatus(getParam("paramStatus")));
			vm = "app/pdt/trm/frmMaklumatTanahRizabMelayu.jsp";
		}else{
			//save pelan 
			tanahRizab.saveFilePelan(item,getParam("idPelanNew"),session);
			//negeriDaerahMukimSelected(paramNegeri, paramDaerah,paramMukim);
			//setSemula();
			//this.context.put("paramStatus",paramStatus(getParam("paramStatus")));
			this.context.put("selectedTab", selectedTab);
			this.context.put("mode", "view");
			vm = "app/pdt/trm/frmMaklumatTanahRizabMelayu.jsp";
		}
		return vm;
	}


	private void setSemula() {
		this.context.put("kawasan", getParam("kawasan"));
		this.context.put("noWarta", getParam("noWarta"));
		this.context.put("tarikhWarta", getParam("tarikhWarta"));
		this.context.put("noPelan", getParam("noPelan"));
		this.context.put("luas", getParam("luas"));
	}


	private Object paramStatus(String param) {

		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='paramStatus' id='paramStatus' ");
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if(param.equals("T")){
				sb.append("<option selected value='T'>Tidak Kuatkuasa</option>\n");
			}else{
				sb.append("<option value='T'>Tidak Kuatkuasa</option>\n");
			}
			
			if(param.equals("K")){
				sb.append("<option selected value='K'>Kuatkuasa</option>\n");
			}else{
				sb.append("<option value='K'>Kuatkuasa</option>\n");
			}
			
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}


	private void negeriDaerahMukimSelected(String paramNegeri,
			String paramDaerah, String paramMukim) throws Exception {
		this.context.put("selectNegeri", HTML.SelectNegeri("paramNegeri", Utils.parseLong(paramNegeri), "","onChange=\"doChangeNegeri();\""));
		this.context.put("selectDaerah", HTML.SelectDaerahByIdNegeri(paramNegeri+"", "paramDaerah", Utils.parseLong(paramDaerah), "", "onChange=\"doChangeDaerah();\""));
		this.context.put("selectMukim", HTML.SelectMukimByDaerah(paramDaerah, "paramMukim", Utils.parseLong(paramMukim) == null ? null : Utils.parseLong(paramMukim), "", ""));
	}


	private void negeriDaerahMukim(String paramNegeri, String paramDaerah,
			String paramMukim) throws Exception {
		this.context.put("selectNegeri", HTML.SelectNegeri("paramNegeri", Utils.parseLong(paramNegeri), "","onChange=\"doChangeNegeri();\""));
		this.context.put("selectDaerah",HTML.SelectDaerah("selectDaerah",Utils.parseLong(paramDaerah),"onChange=\"doChangeDaerah();\""));
		this.context.put("selectMukim",HTML.SelectDaerah("paramMukim",Utils.parseLong(paramMukim),""));
	}


	private void resetField() {
		this.context.put("kawasan", "");
		this.context.put("noWarta", "");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.context.put("tarikhWarta", sdf.format(new Date()));
		this.context.put("noPelan", "");
		this.context.put("luas","");
	}

	private String GetFileExtension(String fname2) {
		String fileName = fname2;
		String fname = "";
		String ext = "";
		int mid = fileName.lastIndexOf(".");
		fname = fileName.substring(0, mid);
		ext = fileName.substring(mid + 1, fileName.length());
		return ext;
	}
	public FrmUploadExcelForm mappingColumn(String val, FrmUploadExcelForm form, String types) {
		DecimalFormat f = new DecimalFormat("##.0");
		if (val.equals("")) {
			val = "0";
		}
		if (currIndex == 0) {
			// hard code mmg kat excell tak berubah.
			// index 0 mmg kat excell semua value null.
			// F1 menumpang column pasai dia tak dak column kat excell fail.
			// form.setF1(0.8);
			// form.setF2(1.0);
		} else if (currIndex == 1 && types.equals("str")) {
			form.setDaerah(val);
		} else if (currIndex == 2 && types.equals("str")) {
			form.setNoWarta(val);	
		} else if (currIndex == 3) {
			form.setTarikhWarta(val);
		} else if (currIndex == 4 && types.equals("str")) {
			form.setNoPelan(val);	
		} else if (currIndex == 5) {
			form.setLuas(val);
		} else if (currIndex == 6 && types.equals("str")) {
			form.setMukim(val);
		} else if (currIndex == 7 && types.equals("str")) {
			form.setKawasan(val);
		} else if (currIndex == 8 && types.equals("str")) {
			form.setFlagJeniswarta(val);
		} else if (currIndex == 9 && types.equals("str")) {
			form.setFlagStatuswarta(val);	
		} else if (currIndex == 10 && types.equals("str")) {
			form.setNoWartaasal(val);
		} else if (currIndex == 11 && types.equals("str")) {
			form.setNoWartaganti(val);
		}

		currIndex = currIndex + 1;
		return form;
	}
	
	private Vector getWartaKewujudan(String idWarta,FrmModelTanahRizabData tanahRizab) throws Exception {
		Vector data = new Vector();
		data.clear();
		if(idWarta == ""){
			return data;
		}

		data = tanahRizab.getData(idWarta);

		if (data.size() != 0) {
			
			Hashtable h = (Hashtable) data.get(0);

			this.context.put("kandunganWarta", h.get("KANDUNGAN_WARTA"));
			this.context.put("kandunganPelan", h.get("KANDUNGAN_PELAN"));
			this.context.put("idWartaNew", h.get("ID_TBLPDTWARTA"));
			this.context.put("idPelanNew", h.get("ID_TBLPDTPELAN"));
			this.context.put("idWartaWujud", h.get("ID_TBLPDTWARTA"));
			this.context.put("idPelanWujud", h.get("ID_TBLPDTPELAN"));
			this.context.put("idTnhRzbMlyWujud", h.get("ID_TBLPDTTANAHRIZABMELAYU"));
			this.context.put("idNegeri", h.get("ID_NEGERI"));
			this.context.put("namaNegeri", h.get("NAMA_NEGERI"));
			this.context.put("idDaerah", h.get("ID_DAERAH"));
			this.context.put("namaDaerah", h.get("NAMA_DAERAH"));
			this.context.put("idMukim", h.get("ID_MUKIM"));
			this.context.put("namaMukim", h.get("NAMA_MUKIM"));
			this.context.put("kawasan", h.get("KAWASAN"));
			this.context.put("noWarta", h.get("NO_WARTA"));
			this.context.put("tarikhWarta", h.get("TARIKH_WARTA"));
			this.context.put("noPelan", h.get("NO_PELAN"));
			this.context.put("luas", h.get("LUAS"));
			this.context.put("flagStatusWarta", h.get("FLAG_STATUSWARTA"));
			
			if(!h.get("ID_TBLPDTWARTAASAL").equals("")){
				this.context.put("idWartaAsalNganti", h.get("ID_TBLPDTWARTAASAL"));
			}else if(!h.get("ID_TBLPDTWARTAGANTI").equals("")){
				this.context.put("idWartaAsalNganti", h.get("ID_TBLPDTWARTAGANTI"));
			}else{
				this.context.put("idWartaAsalNganti", h.get("ID_TBLPDTWARTA"));
			}
			
			this.context.put("selectNegeri", HTML.SelectNegeri("paramNegeri", Utils.parseLong(h.get("ID_NEGERI").toString()), "","onChange=\"doChangeNegeri();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByIdNegeri(h.get("ID_NEGERI").toString()+"", "paramDaerah", Utils.parseLong(h.get("ID_DAERAH").toString()), "", "onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(h.get("ID_DAERAH").toString().toString(), "paramMukim", Utils.parseLong(h.get("ID_MUKIM").toString()) == null ? null : Utils.parseLong(h.get("ID_MUKIM").toString()), "", ""));
			this.context.put("paramStatus",paramStatus(h.get("FLAG_STATUSWARTA").toString()));
		}else{
			this.context.put("mode", "tambahBaru");
		}
		
		return data;
	}
	
	private Vector getWartaPembatalanByIdAsal(String idWarta,FrmModelTanahRizabData tanahRizab) throws Exception {
		Vector data = new Vector();
		data.clear();
		if(idWarta == ""){
			return data;
		}

		data = tanahRizab.getDataBatalAsal(idWarta);

		if (data.size() != 0) {
			Hashtable h = (Hashtable) data.get(0);
			this.context.put("kandunganWarta", h.get("KANDUNGAN_WARTA"));
			this.context.put("kandunganPelan", h.get("KANDUNGAN_PELAN"));
			this.context.put("idWartaNew", h.get("ID_TBLPDTWARTA"));
			this.context.put("idPelanNew", h.get("ID_TBLPDTPELAN"));
			this.context.put("idWartaBatal", h.get("ID_TBLPDTWARTA"));
			this.context.put("idPelanBatal", h.get("ID_TBLPDTPELAN"));
			this.context.put("idTnhRzbMlyBatal", h.get("ID_TBLPDTTANAHRIZABMELAYU"));
			this.context.put("noWarta", h.get("NO_WARTA"));
			this.context.put("tarikhWarta", h.get("TARIKH_WARTA"));
			this.context.put("noPelan", h.get("NO_PELAN"));
			this.context.put("luas", h.get("LUAS"));
		}else{
			this.context.put("mode", "tambahBaru");
		}
		
		return data;
	}

	private Vector getWartaPembatalan(String idWarta,FrmModelTanahRizabData tanahRizab) throws Exception {
		Vector data = new Vector();
		data.clear();
		if(idWarta == ""){
			return data;
		}

		data = tanahRizab.getDataBatal(idWarta);

		if (data.size() != 0) {
			Hashtable h = (Hashtable) data.get(0);
			this.context.put("kandunganWarta", h.get("KANDUNGAN_WARTA"));
			this.context.put("kandunganPelan", h.get("KANDUNGAN_PELAN"));
			this.context.put("idWartaNew", h.get("ID_TBLPDTWARTA"));
			this.context.put("idPelanNew", h.get("ID_TBLPDTPELAN"));
			this.context.put("idWartaBatal", h.get("ID_TBLPDTWARTA"));
			this.context.put("idPelanBatal", h.get("ID_TBLPDTPELAN"));
			this.context.put("idTnhRzbMlyBatal", h.get("ID_TBLPDTTANAHRIZABMELAYU"));
			this.context.put("noWarta", h.get("NO_WARTA"));
			this.context.put("tarikhWarta", h.get("TARIKH_WARTA"));
			this.context.put("noPelan", h.get("NO_PELAN"));
			this.context.put("luas", h.get("LUAS"));
			if(!h.get("ID_TBLPDTWARTAASAL").equals("")){
				this.context.put("idWartaAsalNganti", h.get("ID_TBLPDTWARTAASAL"));
			}else if(!h.get("ID_TBLPDTWARTAGANTI").equals("")){
				this.context.put("idWartaAsalNganti", h.get("ID_TBLPDTWARTAGANTI"));
			}else{
				this.context.put("idWartaAsalNganti", "");
			}
		}else{
			this.context.put("mode", "tambahBaru");
		}
		
		return data;
	}

	private Vector getWartaPenggantian(String idWarta,FrmModelTanahRizabData tanahRizab) throws Exception {
		Vector data = new Vector();
		data.clear();
		if(idWarta == ""){
			return data;
		}
		data = tanahRizab.getDataGanti(idWarta);

		if (data.size() != 0) {
			Hashtable h = (Hashtable) data.get(0);
			this.context.put("kandunganWarta", h.get("KANDUNGAN_WARTA"));
			this.context.put("kandunganPelan", h.get("KANDUNGAN_PELAN"));
			this.context.put("idWartaNew", h.get("ID_TBLPDTWARTA"));
			this.context.put("idPelanNew", h.get("ID_TBLPDTPELAN"));
			this.context.put("idWartaGanti", h.get("ID_TBLPDTWARTA"));
			this.context.put("idPelanGanti", h.get("ID_TBLPDTPELAN"));
			this.context.put("idTnhRzbMlyGanti", h.get("ID_TBLPDTTANAHRIZABMELAYU"));
			this.context.put("noWarta", h.get("NO_WARTA"));
			this.context.put("tarikhWarta", h.get("TARIKH_WARTA"));
			this.context.put("noPelan", h.get("NO_PELAN"));
			this.context.put("luas", h.get("LUAS"));
			this.context.put("kawasan", h.get("KAWASAN"));
			this.context.put("namaMukim", h.get("NAMA_MUKIM"));
			this.context.put("selectNegeri", HTML.SelectNegeri("paramNegeri", Utils.parseLong(h.get("ID_NEGERI").toString()), "","onChange=\"doChangeNegeri();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByIdNegeri(h.get("ID_NEGERI").toString()+"", "paramDaerah", Utils.parseLong(h.get("ID_DAERAH").toString()), "", "onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(h.get("ID_DAERAH").toString().toString(), "paramMukim", Utils.parseLong(h.get("ID_MUKIM").toString()) == null ? null : Utils.parseLong(h.get("ID_MUKIM").toString()), "", ""));
			this.context.put("paramStatus",paramStatus(h.get("FLAG_STATUSWARTA").toString()));
		}else{
			this.context.put("mode", "tambahBaru");
		}
		
		return data;
	}
	
	private Vector getWartaPenggantianByIdAsal(String idWarta,FrmModelTanahRizabData tanahRizab) throws Exception {
		Vector data = new Vector();
		data.clear();
		if(idWarta == ""){
			return data;
		}
		data = tanahRizab.getDataGantiByIdAsal(idWarta);

		if (data.size() != 0) {
			Hashtable h = (Hashtable) data.get(0);
			this.context.put("kandunganWarta", h.get("KANDUNGAN_WARTA"));
			this.context.put("kandunganPelan", h.get("KANDUNGAN_PELAN"));
			this.context.put("idWartaNew", h.get("ID_TBLPDTWARTA"));
			this.context.put("idPelanNew", h.get("ID_TBLPDTPELAN"));
			this.context.put("idWartaGanti", h.get("ID_TBLPDTWARTA"));
			this.context.put("idPelanGanti", h.get("ID_TBLPDTPELAN"));
			this.context.put("idTnhRzbMlyGanti", h.get("ID_TBLPDTTANAHRIZABMELAYU"));
			this.context.put("noWarta", h.get("NO_WARTA"));
			this.context.put("tarikhWarta", h.get("TARIKH_WARTA"));
			this.context.put("noPelan", h.get("NO_PELAN"));
			this.context.put("luas", h.get("LUAS"));
			this.context.put("kawasan", h.get("KAWASAN"));
			this.context.put("namaMukim", h.get("NAMA_MUKIM"));
			this.context.put("selectNegeri", HTML.SelectNegeri("paramNegeri", Utils.parseLong(h.get("ID_NEGERI").toString()), "","onChange=\"doChangeNegeri();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByIdNegeri(h.get("ID_NEGERI").toString()+"", "paramDaerah", Utils.parseLong(h.get("ID_DAERAH").toString()), "", "onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(h.get("ID_DAERAH").toString().toString(), "paramMukim", Utils.parseLong(h.get("ID_MUKIM").toString()) == null ? null : Utils.parseLong(h.get("ID_MUKIM").toString()), "", ""));
			this.context.put("paramStatus",paramStatus(h.get("FLAG_STATUSWARTA").toString()));
		}else{
			this.context.put("mode", "tambahBaru");
		}
		
		return data;
	}


	public String getNegeri(String id_negeri) throws Exception {
		Db db = null;
		String sql = "";
		String output = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			if (id_negeri != null && id_negeri.length() > 0) {
				sql = "Select upper(nama_negeri) as nama_negeri from tblRujNegeri where id_negeri='" + id_negeri + "'";
			} else {
				sql = "Select upper(nama_negeri) as nama_negeri from tblRujNegeri";
			}
			//
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				output = rs.getString("nama_negeri");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (db != null)
				db.close();
		}
		return "<input type=hidden name='id_negeri' value='" + id_negeri + "'>" + output;
	}

	public String getDaerah(String id_daerah) throws Exception {
		Db db = null;
		String sql = "";
		String output = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			if (id_daerah != null && id_daerah.length() > 0) {
				sql = "Select upper(nama_daerah) as nama_daerah from tblRujDaerah where id_daerah='" + id_daerah + "'";
			} else {
				sql = "Select upper(nama_daerah) as nama_daerah from tblRujDaerah";
			}
			//
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				output = rs.getString("nama_daerah");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (db != null)
				db.close();
		}
		return "<input type=hidden name='id_daerah' value='" + id_daerah + "'>" + output;
	}

	public String getMukim(String id_mukim) throws Exception {
		Db db = null;
		String sql = "";
		String output = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			if (id_mukim != null && id_mukim.length() > 0) {
				sql = "Select upper(nama_mukim) as nama_mukim from tblRujMukim where id_mukim='" + id_mukim + "'";
			} else {
				sql = "Select upper(nama_mukim) as nama_mukim from tblRujMukim";
			}
			//
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				output = rs.getString("nama_mukim");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (db != null)
				db.close();
		}
		return "<input type=hidden name='id_mukim' value='" + id_mukim + "'>" + output;
	}

	public String getStatus(String id_tblpdtwarta) throws Exception {
		Db db = null;
		String sql = "";
		String output = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			if (id_tblpdtwarta != null && id_tblpdtwarta.length() > 0) {
				sql = "Select upper(flag_statuswarta) as flag_statuswarta from tblPdtWarta where ID_TBLPDTWARTA=ID_TBLPDTWARTA";
			} else {
				sql = "Select upper(flag_statuswarta) as flag_statuswarta from tblPdtWarta";
			}
			//
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				output = rs.getString("flag_statuswarta");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (db != null)
				db.close();
		}
		return "<input type=hidden name='flag_statuswarta' value='" + id_tblpdtwarta + "'>" + output;
	}
	
	private FileItem getItem() throws FileUploadException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List items = upload.parseRequest(request);
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
		  FileItem item = (FileItem)itr.next();
		  if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
			  myLogger.info(item.getName());
			  return item;
		  }
		}
		return null;
	}

}
