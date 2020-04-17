package ekptg.view.ppt;

/*
 * @author 
 * NORZAILY BINTI JASMI
 */
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek8LaporanAwalTanahData;
import ekptg.model.ppt.FrmUPTSek8HakmilikData;
import ekptg.model.ppt.PPTHeader;
import ekptg.model.ppt.SementaraLaporanAwalTanah;

public class FrmSementaraLaporanAwalTanah extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmSementaraLaporanAwalTanah.class);
	
	
	
	
	// MODEL SEKSYEN 8
	FrmSek8LaporanAwalTanahData model = new FrmSek8LaporanAwalTanahData();
	FrmUPTSek8HakmilikData modelHM = new FrmUPTSek8HakmilikData();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	PPTHeader header = new PPTHeader();
	
	// MODEL SEMENTARA	
	SementaraLaporanAwalTanah modelSementara = new SementaraLaporanAwalTanah();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();
		this.context.put("Util",new lebah.util.Util());	// UNTUK FORMAT UTIL.DECIMAL (EX: 12,000.00)
		
		//command for paging
    	String action = getParam("action");
    	myLogger.info("action :: "+action);
    	
    	//get user login detail
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	userData(id_user);
    	String userIdNeg = userData(id_user); 
	
    	String vm = "";
    	String noLOT = "";
    	context.put("Util", new lebah.util.Util());
    	
    	Vector listPageDepan = new Vector();
    	Vector dataLaporanTanah = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	
    	dataLaporanTanah.clear();
    	listPageDepan.clear();
    	getIdSuburusanstatusfail.clear();
   	
    	//screen jsp
    	String listdepan = "app/ppt/frmSementaraLaporanAwalTanahSenarai.jsp";
    	String mainScreen = "app/ppt/frmSementaraLaporanAwalTanah.jsp";
    	
    	//default list
    	listPageDepan = modelSementara.getListPermohonan(userIdNeg);
    	
    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    	
    	//method
    	context.put("Utils", new ekptg.helpers.Utils());
    	
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
    	
		//paging
    	String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "4");
    	}
    	
		//header
		String id_status = "";
		String id_fail = "";
    	String idpermohonan = getParam("id_permohonan");
    	header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String)dh.get("id_status");
			id_fail = dh.get("id_fail").toString();
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(idpermohonan);
			this.context.put("list_sub_header", list_sub);
		}
		context.put("id_fail", id_fail);
		
		//get current idsuburusanstatusfail
		String id_suburusanstatusfailppt = "";
		modelUPT.setGetIdSuburusanstatusfail(idpermohonan);
		getIdSuburusanstatusfail = modelUPT.getGetIdSuburusanstatusfail();
		if(getIdSuburusanstatusfail.size()!=0){
			Hashtable idsb = (Hashtable)getIdSuburusanstatusfail.get(0);
			id_suburusanstatusfailppt = (String)idsb.get("id_suburusanstatusfailppt");
		}
		
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("showLaporan","no");
		context.put("onchange","no");
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("tambahLaporan".equals(submit)){
    		
    		//reset value
    		resetValue();
    		
    		//list pegawai yang ada hakmilik
    		context.put("selectPegawaiHM",HTML.SelectPegawaiHMbyPermohonan(idpermohonan,"socPegawaiHM",null,null,"style=width:auto onChange=\"getLaporan()\"","sementara"));
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("getLaporan".equals(submit2)){
        		
        		String id_pegawai = getParam("socPegawaiHM");
        		context.put("id_pegawai",id_pegawai);
        		
        		noLOT = getParam("carianNoLot");
        		context.put("carianNoLot",noLOT);
        		
        		//list maklumat tanah dan total hakmilik by pegawai
        		listHakmilikByPegawai(idpermohonan,noLOT,id_pegawai);
        		
        		//dropdown
        		context.put("selectPegawaiHM",HTML.SelectPegawaiHMbyPermohonan(idpermohonan,"socPegawaiHM",Utils.parseLong(id_pegawai),null,"style=width:auto onChange=\"getLaporan()\"","sementara"));
        		
        		//screen show/hide validation
        		if(id_pegawai!=""){
        			
        			context.put("showLaporan","yes");
        			
        			//data laporan for validation
        			
        			modelSementara.setDataLaporanTanah(idpermohonan,id_pegawai);
        			dataLaporanTanah = modelSementara.getDataLaporanTanah();
        			
        			String submit3 = getParam("command3");
                	myLogger.info("submit[3] : " + submit3);
                	myLogger.info("DATA LAPORAN TANAH >> "+dataLaporanTanah.size());
                	
        			//NEW
        			if(dataLaporanTanah.size()==0){
        			
        				//form validation
        				context.put("mode","new");
        				context.put("isEdit","no");
        				
        				//dropdown
            			context.put("selectUnitLuas",HTML.SelectLuas("unitLuas",null,"style=width:auto"));
            			
            			if("onchangeUnit".equals(submit3)){
            				
            				//onchange unit
            				changeUnit();
            				
            				//get and set data
            				getAndSetData();
            				
            				}//close onchangeUnit            			
            			
           				else if("simpanLaporan".equals(submit3)){
                    	
                    		//form validation
                    		context.put("mode","view");
                    		context.put("isEdit","no");
                    		
                    		if (doPost.equals("true")) {
                				//simpan laporan
                				simpanLaporan(session);
                				updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
                				
                				if(id_status.equals("148")){
                				updateStatus(session);
                				}
                			}
                    		
                    		header.setDataHeader(idpermohonan);
                    		dataHeader = header.getDataHeader();
                    		context.put("dataHeader", dataHeader);
                    		if(dataHeader.size()!=0){
                    			Hashtable dh = (Hashtable) dataHeader.get(0);
                    			id_status = (String)dh.get("id_status");
                    		}
                    		
                    		//data laporan tanah
                    		dataLaporanTanah(session,idpermohonan,id_pegawai);
                    		
                    	}//close simpanLaporan
                    	
        			//VIEW	
        			}else{
        				
        				//form validation
        				context.put("mode","view");
        				context.put("isEdit","no");
        				
        				//data laporan tanah
                		dataLaporanTanah(session,idpermohonan,id_pegawai);
	
                	    if("kemaskiniLaporan".equals(submit3)){
                	        		
                	        //form validation
                	    	context.put("mode","view");
                	        context.put("isEdit","yes");
                	        		
                	        String submit4 = getParam("command4");
                	        myLogger.info("submit[4] : " + submit4);
                	        
                	        if("onchangeUnitUpdate".equals(submit4)){
                	        	
                	        	//onchange validation
                	        	context.put("onchange","yes");
                	        	
                	        	//onchange unit
                				changeUnit();
                				
                				//get and set data
                				getAndSetData();
                	        	
                	        }//close onchangeUnitUpdate
                	            	
                	        else if("updateLaporan".equals(submit4)){
                	            		
                	            //form validation
                	            context.put("mode","view");
                	            context.put("isEdit","no");
                	            		
                	            if (doPost.equals("true")) {
                	        		//update laporan
                	        		updateLaporan(session);
                	        	}
                	            		
                	            //data laporan tanah
                	            dataLaporanTanah(session,idpermohonan,id_pegawai);
                	            		
                	        }//close updateLaporan
                	        		
                	    }//close kemaskiniLaporan
                		
        			}//close new / view
        			
        		//hide screen laporan
        		}else{
        			
        			context.put("showLaporan","no");
        			
        		}//validation screen laporan
    
        	}//close getLaporan
    		
			//screen
    		vm = mainScreen;
		    
    	}//close  
    	
		else 
		if("cari".equals(submit)){
    		
    		//carian
    		ListCarian(session,userIdNeg);			
    		listPageDepan = modelSementara.getListCarian(userIdNeg);
	
			//screen
    		vm = listdepan;
		    
    	}//close cari
    	
    	else{	
    		
    		listPageDepan = modelSementara.getListPermohonan(userIdNeg);
    		
    		context.put("nofail", "");
			context.put("carianTarikh", "");
			context.put("carianStatus", "");
			
    		//screen
    		vm = listdepan;
    		
		}//close else
    	
    		//list permohonan
	    	context.put("listPermohonan", listPageDepan);
	    	context.put("list_size", listPageDepan.size());
	    
	    	//id
	    	context.put("id_permohonan", idpermohonan);
	    	context.put("id_status", id_status);
	    	
    		setupPage(session,action,listPageDepan);
    		this.context.put("selectedTab",selectedTab);
    		return vm;
    		
	    }//close public template
	
	
	
	
//--METHOD--//	

	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1542");
		
	}//close updateSuburusanStatusFailPPT	



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
	    
	}//close userData	
	
	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		SementaraLaporanAwalTanah.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
	
	private void resetValue() throws Exception{
    	
		context.put("id_pegawai","");
		context.put("id_tanahumum","");
		
		context.put("txdTarikhMula","");
		context.put("txdTarikhTamat","");
		context.put("txdTarikhLawat","");
		context.put("txtNoPeta","");
		context.put("txtLuasKeseluruhan","");
		context.put("sorDropdownUnitAsal","1");
		context.put("txtKosTanah","");
		context.put("txtKosBangunan","");
		context.put("txtPerihal","");
		context.put("txtPendahuluan","");
		context.put("txtTanahSendiri","");
		context.put("txtTanahNegeri","");
		context.put("txtTanahPersekutuan","");
		context.put("txtKeadaanTanah","");
		context.put("txtLokasi","");
		context.put("txtTanahMelayu","");
		context.put("txtTanahKerajaan","");
		context.put("txtSyor","");
		
	}//close resetValue
	
	@SuppressWarnings("unchecked")
	private void listHakmilikByPegawai(String idpermohonan,String noLOT,String idpegawai) throws Exception{
    	
		Vector listMaklumatTanah = new Vector();
		listMaklumatTanah.clear();
		
		//list maklumat tanah
		modelUPT.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
 	    listMaklumatTanah = modelUPT.getListMaklumatTanah(); 	    
 		context.put("listMaklumatTanah", listMaklumatTanah);
 		context.put("saiz_listTanah", listMaklumatTanah.size());
 		context.put("totalHM", listMaklumatTanah.size());
		
	}//close listHakmilikByPegawai
	
	@SuppressWarnings("unchecked")
	private void simpanLaporan(HttpSession session) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", getParam("id_permohonan"));
		
		h.put("id_pegawai", getParam("id_pegawai"));
		h.put("txdTarikhMula", getParam("txdTarikhMula"));
		h.put("txdTarikhTamat", getParam("txdTarikhTamat"));
		h.put("txdTarikhLawat", getParam("txdTarikhLawat"));
		h.put("txdTarikhLapor", getParam("txdTarikhLapor"));
		h.put("txtBilKeseluruhan", getParam("txtBilKeseluruhan"));
		h.put("txtLuasKeseluruhan", getParam("txtLuasKeseluruhan"));
//		h.put("unitLuas", getParam("unitLuas"));
		h.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));
		h.put("txtKosTanah", getParam("txtKosTanah"));
		h.put("txtKosBangunan", getParam("txtKosBangunan"));
		h.put("txtPerihal", getParam("txtPerihal"));
		
		h.put("sbcLuarKwsnSmpnMelayu",getParam("sbcLuarKwsnSmpnMelayu"));
		h.put("sbcDlmKwsnSmpnMelayu",getParam("sbcDlmKwsnSmpnMelayu"));
		h.put("sbcLuarKwsnMajlisDaerah",getParam("sbcLuarKwsnMajlisDaerah"));
		h.put("sbcDlmKwsnMajlisDaerah",getParam("sbcDlmKwsnMajlisDaerah"));
		h.put("txtLokasi", getParam("txtLokasi"));
		h.put("sorJenisTanah", getParam("sorJenisTanah"));
		
		h.put("txtRupabumiSeluruhLot", getParam("txtRupabumiSeluruhLot"));		
		h.put("txtRupabumiKwsTerlibat", getParam("txtRupabumiKwsTerlibat"));
		
		h.put("txtKeadaanLotSeluruh", getParam("txtKeadaanLotSeluruh"));		
		h.put("txtKeadaanLotTanaman", getParam("txtKeadaanLotTanaman"));
					
		h.put("txtKeadaanLotJnsTanaman", getParam("txtKeadaanLotJnsTanaman"));
		h.put("txtKeadaanLotKwsTerlibat", getParam("txtKeadaanLotKwsTerlibat"));
		
		h.put("id_hakmilik", getParam("id_hakmilik"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		SementaraLaporanAwalTanah.simpanLaporan(h);
	
	}//close simpanLaporan
	
	@SuppressWarnings("unchecked")
	private void updateStatus(HttpSession session) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", getParam("id_permohonan"));	
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8LaporanAwalTanahData.updateStatus(h);
		
	}//close updateStatus
	
	private void getAndSetData() throws Exception{
    	
		context.put("txdTarikhMula", getParam("txdTarikhMula"));
		context.put("txdTarikhTamat", getParam("txdTarikhTamat"));
		context.put("txdTarikhLawat", getParam("txdTarikhLawat"));
		context.put("txdTarikhLapor", getParam("txdTarikhLapor"));
		context.put("txtLuasKeseluruhan", getParam("txtLuasKeseluruhan"));				
		context.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));		
		context.put("txtKosTanah", getParam("txtKosTanah"));
		context.put("txtKosBangunan", getParam("txtKosBangunan"));
		context.put("txtPerihal", getParam("txtPerihal"));			
		
		context.put("sbcLuarKwsnSmpnMelayu", getParam("sbcLuarKwsnSmpnMelayu"));		
		context.put("sbcDlmKwsnSmpnMelayu", getParam("sbcDlmKwsnSmpnMelayu"));		
		context.put("sbcLuarKwsnMajlisDaerah", getParam("sbcLuarKwsnMajlisDaerah"));		
		context.put("sbcDlmKwsnMajlisDaerah", getParam("sbcDlmKwsnMajlisDaerah"));		
		context.put("txtLokasi", getParam("txtLokasi"));
		context.put("sorJenisTanah", getParam("sorJenisTanah"));

		context.put("txtRupabumiSeluruhLot", getParam("txtRupabumiSeluruhLot"));		
		context.put("txtRupabumiKwsTerlibat", getParam("txtRupabumiKwsTerlibat"));
		context.put("txtKeadaanLotSeluruh", getParam("txtKeadaanLotSeluruh"));		
		context.put("txtKeadaanLotJnsTanaman", getParam("txtKeadaanLotJnsTanaman"));
		context.put("txtKeadaanLotTanaman", getParam("txtKeadaanLotTanaman"));
		
	}//close getAndSetData	
	
	private void changeUnit() throws Exception{
		myLogger.info("IN CHANGEUNIT >>>>");
		String unitConvert = getParam("sorDropdownUnitAsal");
		context.put("sorDropdownUnitAsal",unitConvert);
		
		String txtLuasLotAmbil = Utils.RemoveSymbol(getParam("txtLuasKeseluruhan"));
		
		Double total = 0.0000;
		
		//hektar convert to meter persegi
		if(unitConvert.equals("1")){			
			total =  Double.parseDouble(txtLuasLotAmbil) * 0.0001;			
		//meter persegi	convert to hektar
		}else{			
			total =  Double.parseDouble(txtLuasLotAmbil) * 10000;			
		}
		
		//put data
		context.put("txtLuasKeseluruhan", Utils.formatLuasHM(total));
		
	}//close changeUnit	
	
	@SuppressWarnings("unchecked")
	private void updateLaporan(HttpSession session) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_tanahumum", getParam("id_tanahumum"));		
		
		h.put("txdTarikhMula", getParam("txdTarikhMula"));
		h.put("txdTarikhTamat", getParam("txdTarikhTamat"));
		h.put("txdTarikhLawat", getParam("txdTarikhLawat"));
		h.put("txdTarikhLapor", getParam("txdTarikhLapor"));
		h.put("txtBilKeseluruhan", getParam("txtBilKeseluruhan"));
		h.put("txtLuasKeseluruhan", getParam("txtLuasKeseluruhan"));
//		h.put("unitLuas", getParam("unitLuas"));
		h.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));
		h.put("txtKosTanah", getParam("txtKosTanah"));
		h.put("txtKosBangunan", getParam("txtKosBangunan"));
		h.put("txtPerihal", getParam("txtPerihal"));
		
		h.put("sbcLuarKwsnSmpnMelayu",getParam("sbcLuarKwsnSmpnMelayu"));
		h.put("sbcDlmKwsnSmpnMelayu",getParam("sbcDlmKwsnSmpnMelayu"));
		h.put("sbcLuarKwsnMajlisDaerah",getParam("sbcLuarKwsnMajlisDaerah"));
		h.put("sbcDlmKwsnMajlisDaerah",getParam("sbcDlmKwsnMajlisDaerah"));
		h.put("txtLokasi", getParam("txtLokasi"));
		h.put("sorJenisTanah", getParam("sorJenisTanah"));
		
		h.put("txtRupabumiSeluruhLot", getParam("txtRupabumiSeluruhLot"));		
		h.put("txtRupabumiKwsTerlibat", getParam("txtRupabumiKwsTerlibat"));
		
		h.put("txtKeadaanLotSeluruh", getParam("txtKeadaanLotSeluruh"));		
		h.put("txtKeadaanLotTanaman", getParam("txtKeadaanLotTanaman"));
					
		h.put("txtKeadaanLotJnsTanaman", getParam("txtKeadaanLotJnsTanaman"));
		h.put("txtKeadaanLotKwsTerlibat", getParam("txtKeadaanLotKwsTerlibat"));		
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		SementaraLaporanAwalTanah.updateLaporan(h);
		
	}//close updateLaporan
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void dataLaporanTanah(HttpSession session,String idpermohonan,String idpegawai) throws Exception{
    	
		modelSementara.setDataLaporanTanah(idpermohonan,idpegawai);
		Vector dataLaporanTanah = modelSementara.getDataLaporanTanah();
		String id_unitLuas = "";
		String id_tanahumum = "";
		if(dataLaporanTanah.size()!=0){
			Hashtable dlt = (Hashtable)dataLaporanTanah.get(0);
			id_tanahumum = (String)dlt.get("id_tanahumum").toString();
			id_unitLuas = (String)dlt.get("id_unitluas");
		}
		context.put("dataLaporanTanah", dataLaporanTanah);
		
		//id
		context.put("id_tanahumum", id_tanahumum);
		
		//dropdown
		context.put("selectUnitLuas",HTML.SelectLuas("unitLuas",Utils.parseLong(id_unitLuas),"style=width:auto"));		
		
	}//close dataLaporanTanah

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
