package ekptg.view.ppt;
/*
 * @author 
 * NORZAILY BINTI JASMI
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.BantahanPampasanAgensi;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek8PampasanData;
import ekptg.model.ppt.FrmUPTSek8HakmilikData;
import ekptg.model.ppt.PPTHeader;

public class FrmBantahanPampasanAgensi extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmBantahanPampasanAgensi.class);
	
	// MODEL SEKSYEN 8
	FrmUPTSek8HakmilikData modelHM = new FrmUPTSek8HakmilikData();
	FrmSek8PampasanData model = new FrmSek8PampasanData();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	PPTHeader header = new PPTHeader();
	
	// MODEL BANTAHAN
	BantahanPampasanAgensi modelBantahan = new BantahanPampasanAgensi();
	
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();
		
    	//get user login detail
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	userData(id_user);
    	String userIdNeg = userData(id_user); 
    	
		//command for pagings
    	String action = getParam("action");
    	
    	String vm = "";
    	String noLOT = "";
    	
    	Vector listPageDepan = new Vector();
    	Vector listSediaPampasan = new Vector();
    	Vector listMaklumatTanahWithSiasatan = new Vector();
    	Vector dataIdSiasatan = new Vector();
    	Vector dataBorangG = new Vector();
    	Vector totalPampasan = new Vector();
    	Vector dataByHM = new Vector();
    	Vector listBentukPampasan = new Vector();
    	Vector listMaklumatPenerimaanCek = new Vector();
    	Vector dataPenerimaanCek = new Vector();
    	Vector dataPBBorangH = new Vector();
    	Vector dataNamaPengarah = new Vector();
    	Vector listUserid = new Vector();
    	
    	listUserid.clear();
    	dataNamaPengarah.clear();
    	dataPBBorangH.clear();
    	dataPenerimaanCek.clear();
    	listMaklumatPenerimaanCek.clear();
    	listBentukPampasan.clear();
    	dataByHM.clear();
    	totalPampasan.clear();
    	dataBorangG.clear();
    	dataIdSiasatan.clear();
    	listMaklumatTanahWithSiasatan.clear();
    	listSediaPampasan.clear();
    	listPageDepan.clear();
    	
//    	modelUPT.setGetUserId(id_user);
//	    listUserid = modelUPT.getUserIds();
//	    String userIdNeg = "";
//	    if(listUserid.size()!=0){
//	    	Hashtable t = (Hashtable)listUserid.get(0);
//	    	userIdNeg = t.get("idNegeri").toString();
//	    }
	   
	    //GET NAMA PENGARAH
	    String nama_pengarah = "";
	    modelUPT.setNamaPengarah(userIdNeg);
	    dataNamaPengarah = modelUPT.getNamaPengarah();
	    if(dataNamaPengarah.size()!=0){
	    	Hashtable np = (Hashtable)dataNamaPengarah.get(0);
	    	nama_pengarah = np.get("nama_pengarah").toString();
	    }
	    
	    context.put("nama_pengarah",nama_pengarah);
	    context.put("userIdNeg", userIdNeg);
	    
    	//screen jsp
    	String listdepan = "app/ppt/frmBantahanAgensiPampasanSenarai.jsp";    	
    	String listHakmilik = "app/ppt/frmBantahanAgensiPampasanListHM.jsp";
//    	String screenMainPage3 = "app/ppt/frmSementaraBayaranPampasan.jsp";
    	String screenMainPage3 = "app/ppt/frmBantahanAgensiBayaranPampasan.jsp";
    	
    	
    	String mainscreen = "app/ppt/frmSek8PenyediaanPampasan.jsp";
    	String screenPage2 = "app/ppt/frmSek8BentukBayaranAwardList.jsp";
    	String screenMainPage2 = "app/ppt/frmSek8BentukBayaranAward.jsp";
    	String screenMainPage4 = "app/ppt/frmSek8PenerimaanCek.jsp";
    	
    	//default list
    	listPageDepan = modelBantahan.getListPermohonan(userIdNeg);
    	
    	//utils
    	context.put("Utils", new ekptg.helpers.Utils());
    	
    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    	
    	//anchor
    	String ScreenLocation = getParam("ScreenLocation");
    	String CursorPoint = getParam("CursorPoint");    	
    	context.put("ScreenLocation", ScreenLocation);
    	context.put("CursorPoint", CursorPoint);
    	
    	//tabbed
    	String selectedTab = new String();
		selectedTab = getParam("tabId").toString();	
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}
    	
		//header
		String id_status = "";
		String nama_kementerian = "";
		String flagSegera = "";
		String id_fail = "";
    	String id_permohonan = getParam("id_permohonan");
    	header.setDataHeader(id_permohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String)dh.get("id_status");
			nama_kementerian = (String)dh.get("nama_kementerian");
			flagSegera = dh.get("flag_segera").toString();
			id_fail = (String)dh.get("id_fail");
		}

		context.put("flagSegera",flagSegera);
		context.put("kementerianEFT", nama_kementerian);
		
		//paging ALL
    	String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "17");
    	}
    	
		//paging pampasan
    	String flagPagingPampasan = getParam("pagingPampasan");
    	if(flagPagingPampasan!=""){
    		context.put("pagingPampasan", getParam("pagingPampasan"));
    	}else{
    		context.put("pagingPampasan", "1");
    	}
    	
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("onchange","no");
		context.put("onchangeCEK","no");
		context.put("isShow","");
		context.put("onchangeEFT", "no");
		
		String idHakmilik = getParam("id_hakmilik");
		
		//for tab
		model.setlistPenyediaanPampasan(idHakmilik);
		listSediaPampasan = model.getlistPenyediaanPampasan();
		
		//for tab
		//get id siasatan from idHakmilik
		String id_siasatan = "";
		model.setIdSiasatan(idHakmilik);
		dataIdSiasatan = model.getIdSiasatan();
		if(dataIdSiasatan.size()!=0){
			Hashtable dis = (Hashtable)dataIdSiasatan.get(0);
			id_siasatan = (String)dis.get("id_siasatan");
		}
		
		//for paging 3
		model.setlistBentukPampasan(idHakmilik);
		listBentukPampasan = model.getlistBentukPampasan();
		
		//validation (id borang g)
		String id_borangg = "";
		model.setDataBorangG(id_siasatan);
		dataBorangG = model.getDataBorangG();
		if(dataBorangG.size()!=0){
			Hashtable gh = (Hashtable)dataBorangG.get(0);
			id_borangg = (String)gh.get("id_borangg");
		}
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("viewlistHM".equals(submit)){
    		
    		noLOT = getParam("carianNoLot");
    		context.put("carianNoLot", noLOT.trim());
    		
    		//data & list maklumat tanah
    		modelBantahan.setListMaklumatTanahWithSiasatan(id_permohonan,noLOT);
     		listMaklumatTanahWithSiasatan = modelBantahan.getListMaklumatTanahWithSiasatan();
     		context.put("listMaklumatTanah", listMaklumatTanahWithSiasatan);
     		context.put("saiz_listTanah", listMaklumatTanahWithSiasatan.size());
     		
    		//screen
    		vm = listHakmilik;
    		
    	}//close viewlistHM
    	
    	else 
    	if("viewJumlahAward".equals(submit)){
    			
    		//list penyediaan pampasan
            listPenyediaanPampasan(idHakmilik);
            
            //get id siasatan from idHakmilik
    		model.setIdSiasatan(idHakmilik);
    		dataIdSiasatan = model.getIdSiasatan();
    		if(dataIdSiasatan.size()!=0){
    			Hashtable dis = (Hashtable)dataIdSiasatan.get(0);
    			id_siasatan = (String)dis.get("id_siasatan");
    		}
    		
    		//validation (id borang g)
    		model.setDataBorangG(id_siasatan);
    		dataBorangG = model.getDataBorangG();
    		if(dataBorangG.size()!=0){
    			Hashtable gh = (Hashtable)dataBorangG.get(0);
    			id_borangg = (String)gh.get("id_borangg");
    		}
    		
    		//data and id
    		context.put("id_borangg",id_borangg);
    		context.put("dataBorangG",dataBorangG);
    		
    		//get total pampasan
    		getTotalPampasan(id_siasatan);
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            
    		//NEW FORM
    		if(id_borangg==""){
    			
    			//form validaiton
        		context.put("mode","new");
    			
                if("simpanBorangG".equals(submit2)){
                	
                	id_siasatan = getParam("id_siasatan");
                	
                	if (doPost.equals("true")) {
                    	//simpan data
                		simpanDataBorangG(session);
                    }
                	
                	//form validaiton
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		//data
            		dataBorangG(id_siasatan);
            		
            		model.setDataBorangG(id_siasatan);
            		dataBorangG = model.getDataBorangG();
            		if(dataBorangG.size()!=0){
            			Hashtable gh = (Hashtable)dataBorangG.get(0);
            			id_borangg = (String)gh.get("id_borangg");
            		}
            		
                }//close simpanBorangG
        		
        		
    		//VIEW FORM	
    		}else{
    			
    			//form validaiton
        		context.put("mode","view");
        		context.put("isEdit","no");
    			
        		if("kemaskiniBorangG".equals(submit2)){
        			
        			id_borangg = getParam("id_borangg");
        			context.put("id_borangg",id_borangg);
        			
        			//form validaiton
            		context.put("mode","view");
            		context.put("isEdit","yes");
            		
            		//get total pampasan
            		getTotalPampasan(id_siasatan);
        			
            		String submit3 = getParam("command3");
                    myLogger.info("submit[3] : " + submit3);
                    
                    if("updateBorangG".equals(submit3)){
                    	
                    	id_siasatan = getParam("id_siasatan");
                    	
                    	if (doPost.equals("true")) {
                        	//update data
                    		updateDataBorangG(session);
                        }
                    	
                    	//form validaiton
                		context.put("mode","view");
                		context.put("isEdit","no");
                		
                		//data
                		dataBorangG(id_siasatan);
                    	
                    }//close updateBorangG
                    
        		}//close kemaskiniBorangG
        		
    		}//close new / view
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close viewJumlahAward
    	
    	else 
    	if("penerimaanBorangH".equals(submit)){
    			
    		//id
    		id_borangg = getParam("id_borangg");
    		
    		//form validation
    		context.put("mode","new");
    		
    		//dropdown
    		context.put("selectPenerima",HTML.SelectPBbyHakmilik(idHakmilik,"socPenerima",null,null,"style=width:300px"));
    		
    		//list penerimaan borang h
    		listPenerimaanBorangH(id_borangg);
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            
            if("simpanPenerimaanBorangH".equals(submit2)){
            	
            	if (doPost.equals("true")) {
                	//update data
            		simpanPenerimaanBorangH(session,id_status,idHakmilik);
                }
            	
            	//form validation
        		context.put("mode","new");
        		
        		//dropdown
        		context.put("selectPenerima",HTML.SelectPBbyHakmilik(idHakmilik,"socPenerima",null,null,"style=width:300px"));
            	
            	//list penerimaan borang h
        		listPenerimaanBorangH(id_borangg);
            	
        		header.setDataHeader(id_permohonan);
        		dataHeader = header.getDataHeader();
        		context.put("dataHeader", dataHeader);
        		if(dataHeader.size()!=0){
        			Hashtable dh = (Hashtable) dataHeader.get(0);
        			id_status = (String)dh.get("id_status");
        		}
        		
            }//simpanPenerimaanBorangH
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close penerimaanBorangH
    	
    	else 
    	if("viewPenerimaanBorangH".equals(submit)){
    			
    		//id
    		id_borangg = getParam("id_borangg");
    		String id_borangh = getParam("id_borangh");
    		context.put("id_borangh", id_borangh);
 
    		//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		//data penerimaan borang h
    		dataPenerimaanBorangH(idHakmilik,id_borangh,"disabled");
    		
    		//list penerimaan borang h
    		listPenerimaanBorangH(id_borangg);
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            
            if("kemaskiniPenerimaanBorangH".equals(submit2)){
            	
            	//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes");
        		
        		//data penerimaan borang h
        		dataPenerimaanBorangH(idHakmilik,id_borangh,"enabled");
        		
        		String submit3 = getParam("command3");
                myLogger.info("submit[3] : " + submit3);
                
                if("updatePenerimaanBorangH".equals(submit3)){
                	
                	updatePenerimaanBorangH(session);
                	
                	//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
                	//id
            		id_borangg = getParam("id_borangg");
            		id_borangh = getParam("id_borangh");
            		context.put("id_borangh", id_borangh);
            		
            		//list penerimaan borang h
            		listPenerimaanBorangH(id_borangg);
                	
            		//data penerimaan borang h
            		dataPenerimaanBorangH(idHakmilik,id_borangh,"disabled");
            		
                }//close updatePenerimaanBorangH
            	
            }//close kemaskiniPenerimaanBorangH
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close viewPenerimaanBorangH
    	
    	else 
    	if("hapusMaklumat".equals(submit)){
    			
    		//delete
    		hapusMaklumat(session);
    		
    		//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
 
    		//reset value
    		resetValueAndId();
    		
    		//list penyediaan pampasan
    		listPenyediaanPampasan(idHakmilik);
    		
    		//dropdown
    		dropdownNew(idHakmilik);
 
        	//get data from tblppttanah,tblpptbangunan,tblpptsiasatan
        	HMgetAndSetData(id_permohonan,idHakmilik,"new");
    		
        	//screen
    		vm = mainscreen;
        	
    	}//close hapusMaklumat
    	
    	else 
        if("hapusBorangH".equals(submit)){
        			
        	//delete
        	hapusBorangH(session);
        		
        	//id
        	id_borangg = getParam("id_borangg");
        		
        	//form validation
        	context.put("mode","new");
        		
        	//dropdown
        	context.put("selectPenerima",HTML.SelectPBbyHakmilik(idHakmilik,"socPenerima",null,null,"style=width:300px"));
        		
        	//list penerimaan borang h
        	listPenerimaanBorangH(id_borangg);
        		
            //screen
        	vm = mainscreen;
            	
        }//close hapusBorangH
    	
    	
    	//page 2
        else 
    	if("bentukBayaran".equals(submit)){
    			
    		//get data warta
    		dataWarta(id_permohonan);
    		
    		//data siasatan
    		dataBorangG(id_siasatan);
    		
    		//size and data
    		listBentukPampasan(idHakmilik);
    		
    		//screen
    		vm = screenPage2;
    		
    	}//close bentukBayaran
    	
    	else 
    	if("tambahBentukBayaran".equals(submit)){
    			
    		//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
    		
    		//reset value
    		resetValue();
    		
    		//get no lot
    		getNoLot(idHakmilik);
    		
    		//dropdown
    		context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"PBgetAward()\""));
    		
    		//size and data
    		listBentukPampasan(idHakmilik);
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            
            if("PBgetAward".equals(submit2)){
            	
            	String id_pihakberkepentingan = getParam("socPB");
            	
            	//get and set data
            	getAndSetPBAward(id_siasatan,idHakmilik,id_pihakberkepentingan);           	
            	
            	String submit3 = getParam("command3");
                myLogger.info("submit[3] : " + submit3);
                
                if("simpanBentukBayaran".equals(submit3)){

                	if (doPost.equals("true")) {
                		//simpan data n file
                		simpanDataDanFail(session);
                	}
                	
                	//form validation
            		context.put("mode","new");
            		context.put("isEdit","no");
            		
            		//reset value
            		resetValue();
            		
            		//get no lot
            		getNoLot(idHakmilik);
            		
            		//dropdown
            		context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"PBgetAward()\""));
            		
            		//size and data
            		listBentukPampasan(idHakmilik);
                	
                }//close simpanBentukBayaran
            	
            }//close PBgetAward
            
    		//screen
    		vm = screenMainPage2;
    		
    	}//close tambahBentukBayaran
    	
    	else 
    	if("viewBentukBayaran".equals(submit)){
    			
    		//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		String id_award = getParam("id_award");
    		context.put("id_award", id_award);
    		
    		//data
    		dataBentukBayaran(id_award);
    		
    		//list
    		listBentukPampasan(idHakmilik);
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            
    	    if("kemaskiniBentukBayaran".equals(submit2)){
    	    		
    	    	//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes");
    	    	
        		String submit3 = getParam("command3");
                myLogger.info("submit[3] : " + submit3);
                
        	    if("updateBentukBayaran".equals(submit3)){
        	    	
        	    	if (doPost.equals("true")) {
                    	//delete and add new dokumen
        	    		hapusDokumen();
        	    		updateBentukBayaran(session);
                    }
        	    	
        	    	//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
        	    	
            		id_award = getParam("id_award");
            		context.put("id_award", id_award);
            		
            		//data
            		dataBentukBayaran(id_award);
            		
            		//list
            		listBentukPampasan(idHakmilik);
            		
        	    }//close updateBentukBayaran
        		
    	    }//close kemaskiniBentukBayaran
    		
    		//screen
    		vm = screenMainPage2;
    		
    	}//close viewBentukBayaran
    	
    	
    	else 
    	if("hapusDokumen".equals(submit)){
    			
    		hapusDokumen();
    		
    		//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
    		
    		//reset value
    		resetValue();
    		
    		//get no lot
    		getNoLot(idHakmilik);
    		
    		//dropdown
    		context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"PBgetAward()\""));
    		
    		//size and data
    		listBentukPampasan(idHakmilik);
    		
    		//screen
    		vm = screenMainPage2;
    		
    	}//close hapusDokumen
    	
    	
    	else 
    	if("maklumatSuratAgensi".equals(submit)){
    			
    		//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
    		
    		//list senarai surat agensi
    		listMaklumatSuratAgensi(idHakmilik);
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            
    	    if("simpanMaklumatSuratAgensi".equals(submit2)){
    	    	
    	    	if (doPost.equals("true")) {
                	//simpan data
    	    		simpanMaklumatSuratAgensi(session,idHakmilik);
                }
    	    	
    	    	//form validation
        		context.put("mode","new");
        		context.put("isEdit","no");
    	    	
        		//list senarai surat agensi
        		listMaklumatSuratAgensi(idHakmilik);
        		
    	    }//close simpanMaklumatSuratAgensi
    		
    		//screen
    		vm = screenMainPage3;
    		
    	}//close maklumatSuratAgensi
    	
    	else 
    	if("viewMaklumatSuratAgensi".equals(submit)){
    			
    		String id_terimabayaran = getParam("id_terimabayaran");
    		context.put("id_terimabayaran",id_terimabayaran);
    		
    		//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		//data
    		dataMaklumatSuratAgensi(id_terimabayaran);
    		
    		//list senarai surat agensi
    		listMaklumatSuratAgensi(idHakmilik);
    		
    		//list penerimaan cek
    		listPenerimaanCek(id_terimabayaran,"");
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            
    	    if("kemaskiniMaklumatSuratAgensi".equals(submit2)){
    	    	
    	    	//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes");
    	    	
        		String submit3 = getParam("command3");
                myLogger.info("submit[3] : " + submit3);
                
        	    if("updateMaklumatSuratAgensi".equals(submit3)){
        	    	
        	    	updateMaklumatSuratAgensi(session);
        	    	
        	    	//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		//data
            		dataMaklumatSuratAgensi(id_terimabayaran);
            		
            		//list senarai surat agensi
            		listMaklumatSuratAgensi(idHakmilik);
        	    	
        	    }//close updateMaklumatSuratAgensi
        		
    	    }//close kemaskiniMaklumatSuratAgensi
    		
    		//screen
    		vm = screenMainPage3;
    		
    	}//close viewMaklumatSuratAgensi
    	
    	else 
    	if("hapusMaklumatSuratAgensi".equals(submit)){
    			
    		hapusMaklumatSuratAgensi();
    		
    		//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
    		
    		//list senarai surat agensi
    		listMaklumatSuratAgensi(idHakmilik);
    		
    		//screen
    		vm = screenMainPage3;
    		
    	}//close hapusMaklumatSuratAgensi
    	
    	//Tab screen
    	else 
    	if("tambahPenerimaanCek".equals(submit)){
    			
    		String id_terimabayaran = getParam("id_terimabayaran");
    		context.put("id_terimabayaran", id_terimabayaran);
    		
    		//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
    		
    		//reset value
    		resetValuePenerimaan();
    		
    		//dropdown select pb by hakmilik filter by use or unused
    		context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"onchangeSelectPB()\""));
    		
    		//dropdown disabled for filter selectpb only
    		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,"style=width:auto disabled class=disabled"));
    		
    		//list penerimaan cek
    		listPenerimaanCek(id_terimabayaran,"");
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            
    	    if("onchangeSelectPB".equals(submit2)){
  
    	    	//set data
    	    	getAndSetPenerimaan(id_siasatan,idHakmilik,"new",id_terimabayaran);
    	    	
    	    }//close onchangeSelectPB
    		   	    
    	    else if("simpanPenerimaanCek".equals(submit2)){
    	    	
    	    	if (doPost.equals("true")) {
                	//simpan data
    	    		simpanPenerimaanCek(session);
                }
    	    	
    	    	id_terimabayaran = getParam("id_terimabayaran");
    	    	
    	    	//form validation
        		context.put("mode","new");
        		context.put("isEdit","no");
        		
        		//dropdown select pb by hakmilik filter by use or unused
        		context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"onchangeSelectPB()\""));
        		
        		//dropdown disabled for filter selectpb only
        		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,"style=width:auto disabled class=disabled"));
        		
        		//list penerimaan cek
        		listPenerimaanCek(id_terimabayaran,"");
        		
    	    }//close simpanPenerimaanCek
    	    
    		//screen
    		vm = screenMainPage4;
    		
    	}//close tambahPenerimaanCek
    	
    	else 
    	if("viewPenerimaanCek".equals(submit)){
    			
    		String id_terimabayaran = getParam("id_terimabayaran");
    		String id_bayaran = getParam("id_bayaran");
    		String id_hakmilikpb = getParam("id_hakmilikpb");
    		String flag_cara_bayar = getParam("cara_bayar");
    		
    		//id and flag
    		context.put("id_bayaran", id_bayaran);
    		context.put("id_terimabayaran", id_terimabayaran);
    		context.put("id_hakmilikpb", id_hakmilikpb);
    		context.put("cara_bayar", flag_cara_bayar);
    		
    		//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		//data
    		dataPenerimaanCek(idHakmilik,id_bayaran,"disabled",id_terimabayaran);
    		
    		//list penerimaan cek
    		listPenerimaanCek(id_terimabayaran,"");
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            
    	    if("kemaskiniPenerimaanCek".equals(submit2)){
    	    	
    	    	//form validation
        		context.put("isEdit","yes");
        		
        		//data
        		dataPenerimaanCek(idHakmilik,id_bayaran,"enabled",id_terimabayaran);
        		
        		String submit3 = getParam("command3");
                myLogger.info("submit[3] : " + submit3);
                
        	    if("onchangeSelectPBUpdate".equals(submit3)){
        	    	
        	    	//onchange validation
        	    	context.put("onchangeCEK","yes");
        	    	
        	    	//set data
        	    	getAndSetPenerimaan(id_siasatan,idHakmilik,"view",id_terimabayaran);
        	    	
        	    }//close onchangeSelectPBUpdate
        		
        	    else if("updatePenerimaanCek".equals(submit3)){
        	    	
        	    	updatePenerimaanCek(session);
                   
        	    	id_terimabayaran = getParam("id_terimabayaran");
            		id_bayaran = getParam("id_bayaran");
            	
            		//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		//data
            		dataPenerimaanCek(idHakmilik,id_bayaran,"disabled",id_terimabayaran);
            		
            		//list penerimaan cek
            		listPenerimaanCek(id_terimabayaran,"");
        	    	
        	    }//close updatePenerimaanCek
        	    
    	    }//close kemaskiniPenerimaanCek
    	    
    		//screen
    		vm = screenMainPage4;
    		
    	}//close viewPenerimaanCek
    	
    	
    	else 
    	if("hapusPenerimaanCek".equals(submit)){
    			
    		hapusPenerimaanCek();
    		
    		String id_terimabayaran = getParam("id_terimabayaran");
    		context.put("id_terimabayaran", id_terimabayaran);
    		
    		//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
    		
    		//reset value
    		resetValuePenerimaan();
    		
    		//dropdown select pb by hakmilik filter by use or unused
    		context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"onchangeSelectPB()\""));
    		
    		//dropdown disabled for filter selectpb only
    		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,"style=width:auto disabled class=disabled"));
    		
    		//list penerimaan cek
    		listPenerimaanCek(id_terimabayaran,"");
    		
    		//screen
    		vm = screenMainPage4;
    		
    	}//close hapusMaklumatSuratAgensi
    	
    	else 
        if("viewListPenyerahanCek".equals(submit)){
        	
        	String id_terimabayaran = getParam("id_terimabayaran");
        	context.put("id_terimabayaran", id_terimabayaran);
        	
        	//list penerimaan cek
    		listPenerimaanCek(id_terimabayaran,"1");
    		
        	//screen
    		vm = screenMainPage4;
    		
        }//close viewListPenyerahanCek
    	
    	else 
    	if("viewPenyerahanCek".equals(submit)){
    			
    		//form validation
    		context.put("isShow","yes");
    		
    		String id_terimabayaran = getParam("id_terimabayaran");
    		String id_bayaran = getParam("id_bayaran");
    		String id_hakmilikpb = getParam("id_hakmilikpb");
    		String flag_cara_bayar = getParam("cara_bayar");
    		
    		//id and flag
    		context.put("id_bayaran", id_bayaran);
    		context.put("id_terimabayaran", id_terimabayaran);
    		context.put("id_hakmilikpb", id_hakmilikpb);
    		context.put("cara_bayar", flag_cara_bayar);
    		
    		//data
    		dataPenerimaanCek(idHakmilik,id_bayaran,"disabled",id_terimabayaran);
    		
    		//list penerimaan cek
    		listPenerimaanCek(id_terimabayaran,"1");
    		
    		//model for validation new / view
    		String nama_wakil = "";
    		String no_wakil = "";
    		String id_jenisnowakil = "";
    		String tarikh_serah_cek = "";
    		model.setdataPenerimaanCek(id_bayaran);
    		dataPenerimaanCek = model.getdataPenerimaanCek();
    		if(dataPenerimaanCek.size()!=0){
    			Hashtable dp = (Hashtable)dataPenerimaanCek.get(0);
    			nama_wakil = (String)dp.get("nama_wakil");
    			no_wakil = (String)dp.get("no_wakil");
    			id_jenisnowakil = (String)dp.get("id_jenisnowakil");
    			tarikh_serah_cek = (String)dp.get("tarikh_serah_cek");
    		}
    		
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            
    		//NEW
    		if(nama_wakil=="" && no_wakil=="" && id_jenisnowakil=="" && tarikh_serah_cek==""){
    			
    			//form validation
        		context.put("mode","new");
        		context.put("isEdit","no");
    			
        		//dropdown
    			context.put("selectJenisNoWakil",HTML.SelectJenisNoPb("socJenisNoWakil",null,"style=width:auto"));
        		
    			if("updatePenyerahanCek".equals(submit2)){
        	    	
    				updatePenyerahanCek(session,id_status);
    				
    				//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
    				
    				//data
    	    		dataPenerimaanCek(idHakmilik,id_bayaran,"disabled",id_terimabayaran);
    				
    	    		//list penerimaan cek
    	    		listPenerimaanCek(id_terimabayaran,"1");
    	    		
        	    }//close updatePenyerahanCek
    			
    		//VIEW	
    		}else{
    			
    			//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");
    			
        		if("kemaskiniPenyerahanCek".equals(submit2)){
        			
        			//form validation
            		context.put("mode","view");
            		context.put("isEdit","yes");
        			
            		//data
    	    		dataPenerimaanCek(idHakmilik,id_bayaran,"enabled",id_terimabayaran);
            		
    	    		String submit3 = getParam("command3");
    	            myLogger.info("submit[3] : " + submit3);
    	            
    	            if("updatePenyerahanCek".equals(submit3)){
    	            	
    	            	updatePenyerahanCek(session,id_status);
        				
        				//form validation
                		context.put("mode","view");
                		context.put("isEdit","no");
        				
        				//data
        	    		dataPenerimaanCek(idHakmilik,id_bayaran,"disabled",id_terimabayaran);
        				
        	    		//list penerimaan cek
        	    		listPenerimaanCek(id_terimabayaran,"1");
    	            	
    	            }//close updatePenyerahanCek
    	    		
        		}//close kemaskiniPenyerahanCek
        		
    		}//new / view
    		
    		//screen
    		vm = screenMainPage4;
    		
    	}//close viewPenyerahanCek
    	
    	else 
        if("viewListEFT".equals(submit)){
           
        	//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
    		
    		//reset value
    		resetValue();
    		
    		//list pb
    		listEFT(idHakmilik);
    		
    		//dropdown
    		context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"getPBEFT()\""));
    		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,"style=width:auto disabled class=disabled"));
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
    		
            if("getPBEFT".equals(submit2)){
            	
            	String idpb = getParam("socPB");
            	
            	dataDetailPB(id_siasatan,idHakmilik,idpb,"new");
            	
            	String submit3 = getParam("command3");
                myLogger.info("submit[3] : " + submit3);
                
                if("simpanPBEFT".equals(submit3)){
                	
                	if (doPost.equals("true")) {
                    	//simpan data
                		simpanPBEFT(session,id_status);
                    }
                	
                	//reset value
                	resetValue();
                	
                	context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"getPBEFT()\""));
            		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,"style=width:auto disabled class=disabled"));
            		
                	//form validation
            		context.put("mode","new");
            		context.put("isEdit","no");
                	
                	//list pb
            		listEFT(idHakmilik);
                	
                }//close simpanPBEFT
            	
            }//close getPBEFT
    		
        	 //screen
        	vm = screenMainPage3;
        	
        }//close viewListEFT
    	
        else 
    	if("viewDataEFT".equals(submit)){
    		
    		//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		String id_bayaran = getParam("id_bayaran");
    		context.put("id_bayaran", id_bayaran);
    		
    		String id_hakmilikpb = getParam("id_hakmilikpb");
    		context.put("id_hakmilikpb", id_hakmilikpb);
    		
    		//data eft
    		dataEFT(id_bayaran,idHakmilik,"disabled");
    		
    		//list pb
    		listEFT(idHakmilik);
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
    		
            if("kemaskiniPBEFT".equals(submit2)){
            	
            	//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes");
            	
        		//data eft
        		dataEFT(id_bayaran,idHakmilik,"enabled");
        		
        		String submit3 = getParam("command3");
                myLogger.info("submit[3] : " + submit3);
        		
                if("getPBEFTUpdate".equals(submit3)){
                	
                	context.put("onchangeEFT", "yes");
                	
                	String idpb = getParam("socPB");
                	
                	dataDetailPB(id_siasatan,idHakmilik,idpb,"view");
                	
                }//close getPBEFTUpdate
        		
                else if("updatePBEFT".equals(submit3)){
                	
                	//simpan data
                	updatePBEFT(session);
                    
                	//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		id_bayaran = getParam("id_bayaran");
            		context.put("id_bayaran", id_bayaran);
            		
            		id_hakmilikpb = getParam("id_hakmilikpb");
            		context.put("id_hakmilikpb", id_hakmilikpb);
            		
            		//data eft
            		dataEFT(id_bayaran,idHakmilik,"disabled");
            		
            		//list pb
            		listEFT(idHakmilik);
                	
                }//close simpanPBEFT
                
            }//close kemaskiniPBEFT
            
    		//screen
        	vm = screenMainPage3;
    		
    	}//close viewDataEFT
    	
    	else 
    	if("hapusEFT".equals(submit)){
    			
    		//delete
    		hapusEFT(session);
    		
    		//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
    		
    		//reset value
    		resetValue();
    		
    		//list pb
    		listEFT(idHakmilik);
    		
    		//dropdown
    		context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"getPBEFT()\""));
    		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,"style=width:auto disabled class=disabled"));
    		
    		//screen
        	vm = screenMainPage3;
        	
    	}//close hapusEFT
    	
		else 
		if("cari".equals(submit)){
    		
    		//carian
    		ListCarian(session,userIdNeg);			
    		listPageDepan = modelBantahan.getListCarian(userIdNeg);
	
			//screen
    		vm = listdepan;
		    
    	}//close cari
    	
    	else{	
    		
    		listPageDepan = modelBantahan.getListPermohonan(userIdNeg);
    		
    		context.put("nofail", "");
			context.put("carianTarikh", "");
			context.put("carianStatus", "");
			
    		//screen
    		vm = listdepan;
    		
		}//close else
   	
    		//for paging 3
//    		if(listBentukPampasan.size()!=0){
//    			context.put("open", "yes");
//    		}else{
//    			context.put("open", "no");
//    		}
    	
    		//list permohonan
	    	context.put("listPermohonan", listPageDepan);
	    	context.put("list_size", listPageDepan.size());
	    	    	
	    	//id
	    	context.put("id_permohonan", id_permohonan);
	    	context.put("id_status", id_status);
	    	context.put("id_hakmilik", idHakmilik);
	    	context.put("id_borangg", id_borangg);
	    	context.put("id_siasatan",id_siasatan);
	    	context.put("id_fail",id_fail);
	    	
    		setupPage(session,action,listPageDepan);
    		this.context.put("selectedTab",selectedTab);
    		return vm;
    		
	    }//close public template
	
	
//--METHOD--//	
	
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
	
	private void ListCarian(HttpSession session, String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		BantahanPampasanAgensi.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataDetailPB(String id_siasatan, String idHakmilik, String idpb,String mode) throws Exception{
    	
		Vector dataPBBorangH = new Vector();
		dataPBBorangH.clear();
		
		String jenisNoPB = "";
    	String no_pb = "";
    	String id_hakmilikpb = "";
    	
		double jumlah_award = 0;
		
		model.setDataPBAward(id_siasatan,idpb);
		Vector dataPBAward = model.getDataPBAward();
		if(dataPBAward.size()!=0){
			Hashtable pba = (Hashtable)dataPBAward.get(0);
			jumlah_award = (Double)pba.get("bayar_pampasan");
		}
		
		model.setDataPBBorangH(idHakmilik,idpb);
		dataPBBorangH = model.getDataPBBorangH();
		if(dataPBBorangH.size()!=0){
			Hashtable dpb = (Hashtable)dataPBBorangH.get(0);
			no_pb = (String)dpb.get("no_pb");      			
			jenisNoPB = (String)dpb.get("id_jenisnopb");
			id_hakmilikpb = (String)dpb.get("id_hakmilikpb");
		}
    	
		context.put("id_hakmilikpb",id_hakmilikpb);
		
		if(mode.equals("new")){
			context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",Utils.parseLong(idpb),null,"style=width:300px onChange=\"getPBEFT()\""));
		}else{
			context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",Utils.parseLong(idpb),null,"style=width:300px onChange=\"getPBEFTUpdate()\""));
		}
		
		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",Utils.parseLong(jenisNoPB),"style=width:auto disabled class=disabled"));
		
		
		//String total = "";
		//if(jumlah_award!=0){total = Utils.format2Decimal(jumlah_award);}else{total = "";}
		
		context.put("lblNoPB",no_pb);
		context.put("txtNoRujSuratEft", getParam("txtNoRujSuratEft"));
		context.put("txtNoEft", getParam("txtNoEft"));
		context.put("txtAmaun", jumlah_award);
		context.put("txdTarikhTerimaSurat", getParam("txdTarikhTerimaSurat"));	
		context.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		context.put("txtNoBaucer", getParam("txtNoBaucer"));
		context.put("txtPerihal", getParam("txtPerihal"));
		
	}//close dataDetailPB
	
	@SuppressWarnings("unchecked")
	private void updateBentukBayaran(HttpSession session) throws Exception{
    
		DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
		
		//id
		String id_award = getParam("id_award");

	    List items = upload.parseRequest(request);
	    
	    Iterator itr = items.iterator();
	    while (itr.hasNext()) {
	    	FileItem item = (FileItem)itr.next();
	    	if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
	    	  saveDataTwice(item,id_award);
	    	}
	    }

	}//close simpanDataDanFail
	
	private void saveDataTwice(FileItem item,String id_award) throws Exception {
  		Db db = null;
        try {
        	db = new Db();
        	long id_dokumen = DB.getNextID("TBLPPTDOKUMEN_SEQ");
        	Connection con = db.getConnection();
        	con.setAutoCommit(false);
        	PreparedStatement ps = con.prepareStatement("insert into TBLPPTDOKUMEN " +
        			"(id_dokumen,id_award,nama_fail,jenis_Mime,content) " +
        			"values(?,?,?,?,?)");
        	ps.setLong(1, id_dokumen);
        	ps.setString(2, id_award);
        	ps.setString(3,item.getName());
        	ps.setString(4,item.getContentType());
        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
        	ps.executeUpdate();
            con.commit();
            
	    }catch (SQLException se) { 
	    	throw new Exception("Ralat : Masalah muatnaik fail");
	    }finally {
		      if (db != null) db.close();
	    }
	}
	
	@SuppressWarnings("unchecked")
	private void simpanDataDanFail(HttpSession session) throws Exception{
    
		DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
		
		//id
		String id_award = getParam("id_award");

	    List items = upload.parseRequest(request);
	    
	    Iterator itr = items.iterator();
	    while (itr.hasNext()) {
	    	FileItem item = (FileItem)itr.next();
	    	if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
	    	  saveData(item,id_award);
	    	}
	    }

	}//close simpanDataDanFail
	
	private void saveData(FileItem item,String id_award) throws Exception {
  		Db db = null;
        try {
        	db = new Db();
        	long id_dokumen = DB.getNextID("TBLPPTDOKUMEN_SEQ");
        	Connection con = db.getConnection();
        	con.setAutoCommit(false);
        	PreparedStatement ps = con.prepareStatement("insert into TBLPPTDOKUMEN " +
        			"(id_dokumen,id_award,nama_fail,jenis_Mime,content) " +
        			"values(?,?,?,?,?)");
        	ps.setLong(1, id_dokumen);
        	ps.setString(2, id_award);
        	ps.setString(3,item.getName());
        	ps.setString(4,item.getContentType());
        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
        	//ps.setString(6, getParam("nama_dokumen"));
        	ps.executeUpdate();
            con.commit();
            
	    }catch (SQLException se) { 
	    	throw new Exception("Ralat : Masalah muatnaik fail");
	    }finally {
		      if (db != null) db.close();
	    }
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getAndSetPBAward(String idSiasatan,String idHakmilik,String id_pihakberkepentingan) throws Exception{
    	
		Vector dataPBAward = new Vector();
		dataPBAward.clear();
		
		String syer_atas = "";
		String syer_bawah = "";
		double luas_ambil = 0;
		String unit_luas = "";
		String tawaran = "";
		double jumlah_award = 0;
		String id_award = "";
		
		model.setDataPBAward(idSiasatan,id_pihakberkepentingan);
		dataPBAward = model.getDataPBAward();
		if(dataPBAward.size()!=0){
			Hashtable pba = (Hashtable)dataPBAward.get(0);
			id_award = (String)pba.get("id_award");
			syer_atas = (String)pba.get("syer_atas");
			syer_bawah = (String)pba.get("syer_bawah");
			luas_ambil = (Double)pba.get("luas_ambil");
			unit_luas = (String)pba.get("unit_luas");
			tawaran = (String)pba.get("tawaran");
			jumlah_award = (Double)pba.get("bayar_pampasan");
		}
		
		//id
		context.put("id_award",id_award);
		
		//dropdown
		context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",Utils.parseLong(id_pihakberkepentingan),null,"style=width:300px onChange=\"PBgetAward()\""));
		
		context.put("lblLuasAmbil", luas_ambil);
		context.put("lblKodLuas", unit_luas);
		context.put("lblSyorAtas", syer_atas);
		context.put("lblSyorBawah", syer_bawah);
		context.put("lblTawaran", tawaran);
		
		String total = "";
		if(jumlah_award!=0){total = Utils.format2Decimal(jumlah_award);}else{total = "";}
		
		String luas = "";
		if(luas_ambil!=0){luas = Utils.format2Decimal(luas_ambil);}else{luas = "";}
		
		context.put("lblLuasAmbil", luas);
		context.put("lblJumlahAward", total);
	
	}//close resetValue
	
	private void resetValuePenerimaan() throws Exception{
    	
		context.put("id_hakmilikpb", "");
		context.put("id_bayaran", "");
		
		context.put("txtNoPB", "");
		context.put("txdTarikhAkhirBayar", "");
		context.put("txtBilLewat", "");
		context.put("txtDendaLewat", "");
		context.put("sorJenisAward", "");
		context.put("sorFlagSerah", "");	
		context.put("txtPenerimaCek", "");
		context.put("txtNoCek", "");
		context.put("txtAmaunCek", "");
		context.put("txdTarikhCek", "");
		context.put("txdTarikhAmbilCek", "");
		context.put("txtMasaAmbil", "");
		context.put("socJenisWaktu", "");
		context.put("txtTempatAmbil", "");
		
	}//close resetValuePenerimaan
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getAndSetPenerimaan(String id_siasatan,String idHakmilik,String mode,String idTerimabayaran) throws Exception{
    	
		Vector dataPBBorangH = new Vector();
		dataPBBorangH.clear();
		
		String idpb = getParam("socPB");
		
		//dropdown select pb by hakmilik filter by use or unused
		if(mode.equals("new")){
			context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",Utils.parseLong(idpb),null,"style=width:300px onChange=\"onchangeSelectPB()\""));
		}else{
			context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",Utils.parseLong(idpb),null,"style=width:300px onChange=\"onchangeSelectPBUpdate()\""));
		}
		
		String no_pb = "";
		String jenisNoPB = "";
		String id_hakmilikpb = "";
		String tarikh_akhir_bayar = "";
		String nama_pb = "";
		model.setDataPBBorangH(idHakmilik,idpb);
		dataPBBorangH = model.getDataPBBorangH();
		if(dataPBBorangH.size()!=0){
			Hashtable dpb = (Hashtable)dataPBBorangH.get(0);
			no_pb = (String)dpb.get("no_pb");
			nama_pb = (String)dpb.get("nama_pb");
			jenisNoPB = (String)dpb.get("id_jenisnopb");
			id_hakmilikpb = (String)dpb.get("id_hakmilikpb");	
			tarikh_akhir_bayar = (String)dpb.get("tarikh_akhir_bayar");
		}
		
		double jumlah_award = 0;
		model.setDataPBAward(id_siasatan,idpb);
		Vector dataPBAward = model.getDataPBAward();
		if(dataPBAward.size()!=0){
			Hashtable pba = (Hashtable)dataPBAward.get(0);
			jumlah_award = (Double)pba.get("bayar_pampasan");
		}
		
		//id
		context.put("id_hakmilikpb", id_hakmilikpb);
		
		//dropdown disabled for filter selectpb only
		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",Utils.parseLong(jenisNoPB),"style=width:auto disabled class=disabled"));
		
		context.put("txtNoPB", no_pb);
		context.put("txdTarikhAkhirBayar", tarikh_akhir_bayar);
		context.put("txtPenerimaCek", nama_pb);
		
		context.put("txtBilLewat", getParam("txtBilLewat"));
		context.put("txtDendaLewat", getParam("txtDendaLewat"));
		context.put("sorJenisAward", getParam("sorJenisAward"));
		context.put("sorFlagSerah", getParam("sorFlagSerah"));	
		context.put("txtNoCek", getParam("txtNoCek"));
		context.put("txtAmaunCek", jumlah_award);
		context.put("txdTarikhCek", getParam("txdTarikhCek"));
		context.put("txdTarikhAmbilCek", getParam("txdTarikhAmbilCek"));
		context.put("txtMasaAmbil", getParam("txtMasaAmbil"));
		context.put("socJenisWaktu", getParam("socJenisWaktu"));
		context.put("txtTempatAmbil", getParam("txtTempatAmbil"));
		
	}//close getAndSetPenerimaan
	
	private void resetValue() throws Exception{
    	
		context.put("id_hakmilikpb","");
		context.put("id_bayaran","");
		
		context.put("lblLuasAmbil", "");
		context.put("lblKodLuas", "");
		context.put("lblSyorAtas", "");
		context.put("lblSyorBawah", "");
		context.put("lblTawaran", "");
		context.put("lblJumlahAward", "");
	
		context.put("lblNoPB","");
		context.put("txtNoRujSuratEft", "");
		context.put("txtNoEft", "");
		context.put("txtAmaun","");
		context.put("txdTarikhTerimaSurat", "");	
		context.put("txdTarikhSurat", "");
		context.put("txtNoBaucer", "");
		context.put("txtPerihal", "");
		
	}//close resetValue
	
	@SuppressWarnings({ "unchecked" })
	private void getNoLot(String idHakmilik) throws Exception{
    	
		Vector dataByHM = new Vector();
		dataByHM.clear();
		
		String no_lot = "";
		model.setDataByHM(idHakmilik);
		dataByHM = model.getDataByHM();
		if(dataByHM.size()!=0){
			Hashtable db = (Hashtable)dataByHM.get(0);
			no_lot = (String)db.get("no_lot");
		}
		
		context.put("lblNoLot", no_lot);
		
	}//close getNoLot
	
	@SuppressWarnings("unchecked")
	private void dataWarta(String idpermohonan) throws Exception{
    	
		Vector dataWarta = new Vector();
		dataWarta.clear();
		
		String no_warta = "";
		String tarikh_warta = "";
		
		model.setDataWarta(idpermohonan);
		dataWarta = model.getDataWarta();
		if(dataWarta.size()!=0){
			Hashtable dw = (Hashtable)dataWarta.get(0);
			no_warta = (String)dw.get("no_warta");
			tarikh_warta = (String)dw.get("tarikh_warta");
		}
		
		context.put("lblNoWarta", no_warta);
		context.put("lblTarikhWarta", tarikh_warta);
		
	}//close dataWarta
	
	//hapus borang h
	private void hapusBorangH(HttpSession session) throws Exception{
    	
		String id_borangh = getParam("id_borangh");
	
		FrmSek8PampasanData.hapusBorangH(id_borangh);
  
	}//close hapusBorangH
	
	private void hapusEFT(HttpSession session) throws Exception{
    	
		String id_bayaran = getParam("id_bayaran");
	
		FrmSek8PampasanData.hapusEFT(id_bayaran);
  
	}//close hapusEFT
	
	//hapus Penerimaan Cek
	private void hapusPenerimaanCek() throws Exception{
    	
		String id_bayaran = getParam("id_bayaran");
	
		FrmSek8PampasanData.hapusPenerimaanCek(id_bayaran);
  
	}//close hapusPenerimaanCek
	
	//hapus
	private void hapusMaklumatSuratAgensi() throws Exception{
    	
		String id_terimabayaran = getParam("id_terimabayaran");
	
		FrmSek8PampasanData.hapusMaklumatSuratAgensi(id_terimabayaran);
  
	}//close hapusMaklumatSuratAgensi
	
	//hapus
	private void hapusDokumen() throws Exception{
    	
		String id_award = getParam("id_award");
	
		FrmSek8PampasanData.hapusDokumen(id_award);
  
	}//close hapusBorangH
	
	private void hapusMaklumat(HttpSession session) throws Exception{
    	
		String id_award = getParam("id_award");
	
		FrmSek8PampasanData.hapusMaklumat(id_award);
  
	}//close hapusMaklumat
	
	@SuppressWarnings("unchecked")
	private void updatePenerimaanBorangH(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		//id
		h.put("id_borangh", getParam("id_borangh"));
		//h.put("id_pihakberkepentingan", getParam("socPenerima"));
		h.put("txdTarikhSerah", getParam("txdTarikhSerah"));
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));
		h.put("txdTarikhAkhirBayar", getParam("txdTarikhAkhirBayar"));
		h.put("socKeputusanBorangH", getParam("socKeputusanBorangH"));
		h.put("id_hakmilikpb", getParam("id_hakmilikpb"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8PampasanData.updatePenerimaanBorangH(h);
  
	}//close updatePenerimaanBorangH
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void simpanPenerimaanBorangH(HttpSession session,String status,String id_hakmilik) throws Exception{

		Vector getIdHakmilikpb = new Vector();
		getIdHakmilikpb.clear();
		
		Hashtable h = new Hashtable();
		
		//id
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_borangg", getParam("id_borangg"));
		//h.put("id_pihakberkepentingan", getParam("socPenerima"));
		h.put("txdTarikhSerah", getParam("txdTarikhSerah"));
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));
		h.put("txdTarikhAkhirBayar", getParam("txdTarikhAkhirBayar"));
		h.put("socKeputusanBorangH", getParam("socKeputusanBorangH"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		if(status.equals("62")){
			FrmSek8PampasanData.updateStatus(h);
		}
		
		String id_hakmilikpb = "";
		model.setIdHakmilikpb(id_hakmilik,getParam("socPenerima"));
		getIdHakmilikpb = model.getIdHakmilikpb();
		if(getIdHakmilikpb.size()!=0){
			Hashtable idh = (Hashtable)getIdHakmilikpb.get(0);
			id_hakmilikpb = (String)idh.get("id_hakmilikpb");
		}
		
		h.put("id_hakmilikpb", id_hakmilikpb);
		
		FrmSek8PampasanData.simpanPenerimaanBorangH(h);
  
	}//close simpanPenerimaanBorangH
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void listPenerimaanBorangH(String id_borangg) throws Exception{
    	
		Vector listBorangH = new Vector();
		listBorangH.clear();
		
		model.setListBorangH(id_borangg);
		listBorangH = model.getListBorangH();
		
		//data and size
		context.put("listBorangH",listBorangH);
		context.put("list_borangh",listBorangH.size());
		
	}//close listPenerimaanBorangH
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataPenerimaanBorangH(String idHakmilik,String id_borangh,String disability) throws Exception{
    	
		Vector dataBorangH = new Vector();
		dataBorangH.clear();
		
		String id_pihakberkepentingan = "";
		String id_hakmilikpb = "";
		model.setDataBorangH(id_borangh);
		dataBorangH = model.getDataBorangH();
		if(dataBorangH.size()!=0){
			Hashtable dbh = (Hashtable)dataBorangH.get(0);
			id_pihakberkepentingan = (String)dbh.get("id_pihakberkepentingan");
			id_hakmilikpb = (String)dbh.get("id_hakmilikpb");
		}
		
		//id
		context.put("id_pihakberkepentingan",id_pihakberkepentingan);
		context.put("id_hakmilikpb",id_hakmilikpb);
		
		//data and size
		context.put("dataBorangH",dataBorangH);

		String mode = "";
		if(disability.equals("enabled")){
			mode = "";
		}else{
			mode = "disabled class=disabled";
		}
		
		//dropdown
		context.put("selectPenerima",HTML.SelectPBbyHakmilik(idHakmilik,"socPenerima",Utils.parseLong(id_pihakberkepentingan),null,"style=width:300px "+mode+""));
		
		
	}//close listPenerimaanBorangH
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataBorangG(String id_siasatan) throws Exception{
    	
		Vector dataBorangG = new Vector();
		dataBorangG.clear();
		
		String id_borangg = "";
		String no_siasatan = "";
		model.setDataBorangG(id_siasatan);
		dataBorangG = model.getDataBorangG();
		if(dataBorangG.size()!=0){
			Hashtable gh = (Hashtable)dataBorangG.get(0);
			id_borangg = (String)gh.get("id_borangg");
			no_siasatan = (String)gh.get("no_siasatan");
		}
		
		//data and id
		context.put("dataBorangG",dataBorangG);
		context.put("id_borangg",id_borangg);
		context.put("lblNoSiasatan", no_siasatan);
		
	}//close dataBorangG

	@SuppressWarnings({ "unchecked", "static-access" })
	private void listPenyediaanPampasan(String idHakmilik) throws Exception{
    	
		Vector listSediaPampasan = new Vector();
		listSediaPampasan.clear();
		
		model.setlistPenyediaanPampasan(idHakmilik);
		listSediaPampasan = model.getlistPenyediaanPampasan();
		context.put("listSediaPampasan",listSediaPampasan);
		context.put("list_saiz",listSediaPampasan.size());
		
	}//close listPenyediaanPampasan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataBentukBayaran(String idAward) throws Exception{
    	
		Vector dataBentukPampasan = new Vector();
		dataBentukPampasan.clear();
		
		model.setdataBentukPampasan(idAward);
		dataBentukPampasan = model.getDataBentukPampasan();
		context.put("dataBentukPampasan",dataBentukPampasan);
		
	}//close dataBentukBayaran
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void listBentukPampasan(String idHakmilik) throws Exception{
    	
		Vector listBentukPampasan = new Vector();
		listBentukPampasan.clear();
		
		model.setlistBentukPampasan(idHakmilik);
		listBentukPampasan = model.getlistBentukPampasan();
		context.put("listBentukPampasan",listBentukPampasan);
		context.put("saiz_jadual",listBentukPampasan.size());
		
	}//close listBentukPampasan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void listPenerimaanCek(String id_terimabayaran,String modeCaraBayar) throws Exception{
    	
		Vector listMaklumatPenerimaanCek = new Vector();
		listMaklumatPenerimaanCek.clear();
		
		model.setListPenerimaanCek(id_terimabayaran,modeCaraBayar);
		listMaklumatPenerimaanCek = model.getListPenerimaanCek();
		context.put("listMaklumatPenerimaanCek",listMaklumatPenerimaanCek);
		context.put("saiz_list_penerimaan", listMaklumatPenerimaanCek.size());
		
	}//close listBentukPampasan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void listEFT(String id_hakmilik) throws Exception{
    	
		Vector listEFT = new Vector();
		listEFT.clear();
		
		model.setListEFT(id_hakmilik);
		listEFT = model.getListEFT();
		context.put("listEFT",listEFT);
		context.put("saiz_list_eft", listEFT.size());
		
	}//close listBentukPampasan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataEFT(String id_bayaran,String idHakmilik,String disability) throws Exception{
    	
		Vector dataEFT = new Vector();
		dataEFT.clear();
		
		String id_pb = "";
		String id_jenisnopb = "";
		model.setDataEFT(id_bayaran);
		dataEFT = model.getDataEFT();
		context.put("dataEFT",dataEFT);
		if(dataEFT.size()!=0){
			Hashtable de = (Hashtable)dataEFT.get(0);
			id_pb = (String)de.get("id_pihakberkepentingan");
			id_jenisnopb = (String)de.get("id_jenisnopb");
		}
		
		
		String mode ="";
		if(disability.equals("enabled")){
			mode = "";
		}else{
			mode = "disabled class=disabled";
		}
		
		context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",Utils.parseLong(id_pb),null,"style=width:300px "+mode+" onChange=\"getPBEFTUpdate()\""));
		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",Utils.parseLong(id_jenisnopb),"style=width:auto disabled class=disabled"));
		
	}//close dataEFT
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void listMaklumatSuratAgensi(String idHakmilik) throws Exception{
    	
		Vector listSuratAgensi = new Vector();
		listSuratAgensi.clear();
		
		model.setlistSuratAgensi(idHakmilik);
		listSuratAgensi = model.getlistSuratAgensi();
		context.put("listSuratAgensi",listSuratAgensi);
		context.put("list_surat", listSuratAgensi.size());
		
	}//close listBentukPampasan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataMaklumatSuratAgensi(String idTerimaBayaran) throws Exception{
    	
		Vector dataSuratAgensi = new Vector();
		dataSuratAgensi.clear();
		
		model.setdataSuratAgensi(idTerimaBayaran);
		dataSuratAgensi = model.getdataSuratAgensi();
		context.put("dataSuratAgensi",dataSuratAgensi);
		
	}//close listBentukPampasan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataPenerimaanCek(String idHakmilik,String id_bayaran,String disability,String id_terimabayaran) throws Exception{
    	
		Vector dataPenerimaanCek = new Vector();
		dataPenerimaanCek.clear();
		
		String id_jenisnopb = "";
		String id_pihakberkepentingan = "";
		String id_jenisnowakil = "";
		
		model.setdataPenerimaanCek(id_bayaran);
		dataPenerimaanCek = model.getdataPenerimaanCek();
		context.put("dataPenerimaanCek",dataPenerimaanCek);
		if(dataPenerimaanCek.size()!=0){
			Hashtable dpc = (Hashtable)dataPenerimaanCek.get(0);
			id_jenisnopb = (String)dpc.get("id_jenisnopb");
			id_pihakberkepentingan =(String)dpc.get("id_pihakberkepentingan"); 
			id_jenisnowakil =(String)dpc.get("id_jenisnowakil"); 
		}
		
		String mode = "";
		
		if(disability.equals("disabled")){
			mode = "disabled class=disabled";
		}else{
			mode = "";
		}
		
		//dropdown select pb by hakmilik filter by use or unused
		context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",Utils.parseLong(id_pihakberkepentingan),null,"style=width:300px "+mode+" onChange=\"onchangeSelectPBUpdate()\""));
		
		//dropdown disabled for filter selectpb only
		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",Utils.parseLong(id_jenisnopb),"style=width:auto disabled class=disabled"));
		
		//dropdown di penyerahan cek
		context.put("selectJenisNoWakil",HTML.SelectJenisNoPb("socJenisNoWakil",Utils.parseLong(id_jenisnowakil)," "+mode+" style=width:auto"));
		
	}//close listBentukPampasan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getTotalPampasan(String id_siasatan) throws Exception{
    	
		Vector totalPampasan = new Vector();
		totalPampasan.clear();
		
		String total_pampasan = "";
		model.setTotalPampasan(id_siasatan);
		totalPampasan = model.getTotalPampasan();
		if(totalPampasan.size()!=0){
			Hashtable tp = (Hashtable)totalPampasan.get(0);
			total_pampasan = (String)tp.get("total_pampasan");
		}
		
		//total pampasan
		context.put("total_pampasan", total_pampasan);
		
	}//close getTotalPampasan
	
	
	@SuppressWarnings("unchecked")
	private void simpanDataBorangG(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		//id
		h.put("id_siasatan", getParam("id_siasatan"));
		h.put("id_hakmilik", getParam("id_hakmilik"));
		h.put("txtJumlahAward", Utils.RemoveSymbol(getParam("txtJumlahAward")));
		h.put("txdTarikhBorangG", getParam("txdTarikhBorangG"));
		h.put("txdTarikhBorangH", getParam("txdTarikhBorangH"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8PampasanData.simpanDataBorangG(h);
  
	}//close simpanDataBorangG
	
	@SuppressWarnings("unchecked")
	private void simpanMaklumatSuratAgensi(HttpSession session,String idHakmilik) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_hakmilik", idHakmilik);
		h.put("txtNoRujSurat", getParam("txtNoRujSurat"));
		h.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));
		h.put("txtNamaBank", getParam("txtNamaBank"));
		h.put("txdTarikhAmbil", getParam("txdTarikhAmbil"));
		h.put("txtTempatAmbil", getParam("txtTempatAmbil"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8PampasanData.simpanMaklumatSuratAgensi(h);
  
	}//close simpanMaklumatSuratAgensi
	
	@SuppressWarnings("unchecked")
	private void simpanPenerimaanCek(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_terimabayaran", getParam("id_terimabayaran"));
		h.put("id_hakmilikpb", getParam("id_hakmilikpb"));
		
		h.put("txtBilLewat", getParam("txtBilLewat"));
		h.put("txtDendaLewat", getParam("txtDendaLewat"));
		h.put("sorJenisAward", getParam("sorJenisAward"));
		h.put("sorFlagSerah", getParam("sorFlagSerah"));	
		h.put("txtPenerimaCek", getParam("txtPenerimaCek"));
		h.put("txtNoCek", getParam("txtNoCek"));
		h.put("txtAmaunCek", getParam("txtAmaunCek"));
		h.put("txdTarikhCek", getParam("txdTarikhCek"));
		h.put("txdTarikhAmbilCek", getParam("txdTarikhAmbilCek"));
		h.put("txtMasaAmbil", getParam("txtMasaAmbil"));
		h.put("socJenisWaktu", getParam("socJenisWaktu"));
		h.put("txtTempatAmbil", getParam("txtTempatAmbil"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8PampasanData.simpanPenerimaanCek(h);
  
	}//close simpanPenerimaanCek
	
	@SuppressWarnings("unchecked")
	private void updatePenerimaanCek(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_bayaran", getParam("id_bayaran"));
		h.put("id_hakmilikpb", getParam("id_hakmilikpb"));
		
		h.put("txtBilLewat", getParam("txtBilLewat"));
		h.put("txtDendaLewat", getParam("txtDendaLewat"));
		h.put("sorJenisAward", getParam("sorJenisAward"));
		h.put("sorFlagSerah", getParam("sorFlagSerah"));	
		h.put("txtPenerimaCek", getParam("txtPenerimaCek"));
		h.put("txtNoCek", getParam("txtNoCek"));
		h.put("txtAmaunCek", getParam("txtAmaunCek"));
		h.put("txdTarikhCek", getParam("txdTarikhCek"));
		h.put("txdTarikhAmbilCek", getParam("txdTarikhAmbilCek"));
		h.put("txtMasaAmbil", getParam("txtMasaAmbil"));
		h.put("socJenisWaktu", getParam("socJenisWaktu"));
		h.put("txtTempatAmbil", getParam("txtTempatAmbil"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8PampasanData.updatePenerimaanCek(h);
  
	}//close updatePenerimaanCek
	
	@SuppressWarnings("unchecked")
	private void updatePenyerahanCek(HttpSession session,String idstatus) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_bayaran", getParam("id_bayaran"));
		h.put("id_hakmilikpb", getParam("id_hakmilikpb"));
		
		h.put("txtNamaWakil", getParam("txtNamaWakil"));
		h.put("txtNoWakil", getParam("txtNoWakil"));
		h.put("txdTarikhSerahCek", getParam("txdTarikhSerahCek"));
		h.put("socJenisNoWakil", getParam("socJenisNoWakil"));	
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		if(idstatus.equals("68")){
			FrmSek8PampasanData.updateStatus(h,getParam("id_permohonan"));
    	}
		
		FrmSek8PampasanData.updatePenyerahanCek(h);
  
	}//close updatePenerimaanCek
	
	@SuppressWarnings("unchecked")
	private void simpanPBEFT(HttpSession session,String idstatus) throws Exception{

		Hashtable h = new Hashtable();
	
		h.put("id_hakmilikpb", getParam("id_hakmilikpb"));
		
		h.put("txtNoRujSuratEft", getParam("txtNoRujSuratEft"));
		h.put("txtNoEft", getParam("txtNoEft"));
		h.put("txtAmaun", Utils.RemoveSymbol(getParam("txtAmaun")));
		h.put("txdTarikhTerimaSurat", getParam("txdTarikhTerimaSurat"));	
		h.put("txdTarikhSuratEFT", getParam("txdTarikhSuratEFT"));
		h.put("txtNoBaucer", getParam("txtNoBaucer"));
		h.put("txtPerihal", getParam("txtPerihal"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		if(idstatus.equals("68")){
			FrmSek8PampasanData.updateStatus(h,getParam("id_permohonan"));
    	}
		
		FrmSek8PampasanData.simpanPBEFT(h);
  
	}//close simpanPBEFT
	
	@SuppressWarnings("unchecked")
	private void updatePBEFT(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
	
		h.put("id_bayaran", getParam("id_bayaran"));
		h.put("id_hakmilikpb", getParam("id_hakmilikpb"));
		
		h.put("txtNoRujSuratEft", getParam("txtNoRujSuratEft"));
		h.put("txtNoEft", getParam("txtNoEft"));
		h.put("txtAmaun", Utils.RemoveSymbol(getParam("txtAmaun")));
		h.put("txdTarikhTerimaSurat", getParam("txdTarikhTerimaSurat"));	
		h.put("txdTarikhSuratEFT", getParam("txdTarikhSuratEFT"));
		h.put("txtNoBaucer", getParam("txtNoBaucer"));
		h.put("txtPerihal", getParam("txtPerihal"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8PampasanData.updatePBEFT(h);
  
	}//close updatePBEFT
	
	
	@SuppressWarnings("unchecked")
	private void updateMaklumatSuratAgensi(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_terimabayaran", getParam("id_terimabayaran"));
		h.put("txtNoRujSurat", getParam("txtNoRujSurat"));
		h.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));
		h.put("txtNamaBank", getParam("txtNamaBank"));
		h.put("txdTarikhAmbil", getParam("txdTarikhAmbil"));
		h.put("txtTempatAmbil", getParam("txtTempatAmbil"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8PampasanData.updateMaklumatSuratAgensi(h);
  
	}//close updateMaklumatSuratAgensi
	
	@SuppressWarnings("unchecked")
	private void updateDataBorangG(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		//id
		h.put("id_borangg", getParam("id_borangg"));
		h.put("id_hakmilik", getParam("id_hakmilik"));
		h.put("txtJumlahAward", Utils.RemoveSymbol(getParam("txtJumlahAward")));
		h.put("txdTarikhBorangG", getParam("txdTarikhBorangG"));
		h.put("txdTarikhBorangH", getParam("txdTarikhBorangH"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8PampasanData.updateDataBorangG(h);
  
	}//close updateDataBorangG
	
	@SuppressWarnings("unchecked")
	private void HMgetAndSetData(String idpermohonan,String idHakmilik,String mode) throws Exception{
    	
		Vector dataByHM = new Vector();
		dataByHM.clear();
	
		String id_tanah = "";
		String id_siasatan = "";
		
		double nilai_tanah = 0;
		//double bayar_fee = 0;
		double bayar_pecahpisah = 0;
		double bayar_penjejasan = 0;
		double naik_nilai_jpph = 0;
		double nilai_bangunan = 0;
		
		String no_kes = "";
		String tarikh_siasatan = "";
		
		String id_kategoritanah = "";
		String luas_ambil = "";
		
		model.setDataByHM(idHakmilik);
		dataByHM = model.getDataByHM();
		if(dataByHM.size()!=0){
			Hashtable db = (Hashtable)dataByHM.get(0);
			id_tanah = (String)db.get("id_tanah");
			id_siasatan = (String)db.get("id_siasatan");
			
			nilai_tanah = (Double)db.get("nilai_tanah");
			//bayar_fee = (Double)db.get("bayar_fee");
			bayar_pecahpisah = (Double)db.get("bayar_pecahpisah");
			bayar_penjejasan = (Double)db.get("bayar_penjejasan");
			naik_nilai_jpph = (Double)db.get("naik_nilai_jpph");
			nilai_bangunan = (Double)db.get("nilai_bangunan");
			
			no_kes = (String)db.get("no_kes");
			tarikh_siasatan = (String)db.get("tarikh_siasatan");
			
			id_kategoritanah = (String)db.get("id_kategoritanah");
			luas_ambil = (String)db.get("luas_ambil");
		}
		
		if(mode.equals("new")){
			context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"PBgetData()\""));	
		}else{
			context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"PBgetDataUpdate()\""));	
		}
		
		//dropdown
		context.put("selectUnitLuas",HTML.SelectLuas("unitLuas",null,"style=width:250px"));
		context.put("selectJenisPB",HTML.SelectJenisPb("socJenisPB",null,"style=width:300px"));
		
		//set id
		context.put("id_tanah", id_tanah);
		context.put("id_siasatan", id_siasatan);
		
		//set data temp
		context.put("id_kategoritanah", id_kategoritanah);
		context.put("luas_ambil", luas_ambil);
		
		//set data
		context.put("txtNoKes", no_kes);
		context.put("txdTarikhBicara", tarikh_siasatan);
		//context.put("txtFeeAsal", bayar_fee);
		context.put("txtBangunanAsal", nilai_bangunan);
		context.put("txtPecahpisahAsal", bayar_pecahpisah);
		context.put("txtPenjejasanAsal", bayar_penjejasan);
		context.put("txtNaikAsal", naik_nilai_jpph);
		context.put("txtTanahAsal", nilai_tanah);
		
	}//close getAndSetData
	
	private void dropdownNew(String idHakmilik) throws Exception{
    	
		//dropdown
		context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"PBgetData()\""));
		context.put("selectUnitLuas",HTML.SelectLuas("unitLuas",null,"style=width:250px"));
		context.put("selectJenisPB",HTML.SelectJenisPb("socJenisPB",null,"style=width:300px"));
		
	}//close dropdownNew

	private void resetValueAndId() throws Exception{
    	
		//id
		context.put("id_tanah", "");
		context.put("id_bangunan", "");
		context.put("id_pihakberkepentingan", "");
		context.put("id_hakmilikpb", "");
		
		//data
		context.put("txtNoPB", "");
		context.put("txtSyorAtas", "");
		context.put("txtSyorBawah", "");
		context.put("txtNoKes", "");
		context.put("txdTarikhBicara", "");
		context.put("txtLuasAmbil", "");
		context.put("socStatusPenerima", "");
		context.put("txtFeeAsal", "");
		context.put("txtFeeAward", "");
		context.put("txtTanahAsal", "");
		context.put("txtTanahAward", "");
		context.put("txtBangunanAsal", "");
		context.put("txtBangunanAward", "");
		context.put("txtPecahpisahAsal", "");
		context.put("txtPecahpisahAward", "");
		context.put("txtPenjejasanAsal", "");
		context.put("txtPenjejasanAward", "");
		context.put("txtNaikAsal", "");
		context.put("txtNaikAward", "");
		context.put("txtLainPampasan", "");
		context.put("txtJumlahPampasan", "");
		
	}//close resetValue

	@SuppressWarnings("unchecked")
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
			this.context.put("listPermohonan",paging.getPage(page));
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
	
}//close class
