package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek8EndosanDHDKBorangKData;
import ekptg.model.ppt.PPTHeader;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */
//import javax.swing.JOptionPane;

public class FrmSek8EndosanDHDKBorangK extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmSek8EndosanDHDKBorangK.class);
	
	//model
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmSek8EndosanDHDKBorangKData model = new FrmSek8EndosanDHDKBorangKData();
	PPTHeader header = new PPTHeader();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		Vector listUserid = new Vector();
		Vector listPageDepan = new Vector();
		Vector listHakmilikPTGOnly = new Vector();
		Vector listHakmilikPTDOnly = new Vector();
		Vector getIdSuburusanstatusfail = new Vector();
    	
    	getIdSuburusanstatusfail.clear();
		listHakmilikPTDOnly.clear();
		listHakmilikPTGOnly.clear();
    	listPageDepan.clear();
		listUserid.clear();
		 
		//command for paging
    	String action = getParam("action");
    	
    	//get user login detail
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");
    	
    	/*
    	modelUPT.setGetUserId(id_user);
	    listUserid = modelUPT.getUserIds();
	    String userIdNeg = "";
	    if(listUserid.size()!=0){
	    	Hashtable t = (Hashtable)listUserid.get(0);
	    	userIdNeg = t.get("idNegeri").toString();
	    }
	    */
    	
    	String vm = "";
    	
    	//screen jsp
    	String listdepan = "app/ppt/frmSek8EndosanDHDKBorangKSenarai.jsp";
    	String mainscreen = "app/ppt/frmSek8EndosanDHDKBorangK.jsp";
    	String screenPTG = "app/ppt/frmSek8EndosanDHDKBorangKTambahPTG.jsp";
    	String screenPTD = "app/ppt/frmSek8EndosanDHDKBorangKTambahPTD.jsp";
    	
    	//default list
    	//listPageDepan = model.getListPermohonan(userIdNeg);
    	
    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    	
    	//anchor
    	String ScreenLocation = getParam("ScreenLocation");
    	String CursorPoint = getParam("CursorPoint");    	
    	context.put("ScreenLocation", ScreenLocation);
    	context.put("CursorPoint", CursorPoint);
    	
		//paging
    	/*
    	String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "21");
    	}
    	*/
    	context.put("paging", "21");
		
		//header
		String id_status = "";
		String id_pejabat_ptg = "";
		String id_pejabat_ptd = "";
		String flagSegera = "";
		String id_fail = "";
    	String idpermohonan = getParam("id_permohonan");
    	Vector dataHeader = null;
    	if(!idpermohonan.equals(""))
    	{
	    	header.setDataHeader(idpermohonan);
			dataHeader = header.getDataHeader();
			context.put("dataHeader", dataHeader);
			if(dataHeader.size()!=0){
				Hashtable dh = (Hashtable) dataHeader.get(0);
				id_status = (String)dh.get("id_status");
				id_pejabat_ptg = (String)dh.get("id_endosan_borangk_ptg");
				id_pejabat_ptd = (String)dh.get("id_endosan_borangk_ptd");
				flagSegera = dh.get("flag_segera").toString();
				id_fail = (String)dh.get("id_fail");
				
				Vector list_sub = null;
				list_sub = header.listPerjalananFail(idpermohonan);
				this.context.put("list_sub_header", list_sub);
			}
    	}
		
		context.put("flagSegera",flagSegera);
		
		 //GET NAMA PENGARAH
	    String nama_pengarah = "";
		//get current idsuburusanstatusfail
		String id_suburusanstatusfailppt = "";
		String id_suburusanstatus = "";
		if(!idpermohonan.equals(""))
    	{
		modelUPT.setGetIdSuburusanstatusfail(idpermohonan);
		getIdSuburusanstatusfail = modelUPT.getGetIdSuburusanstatusfail();
		if(getIdSuburusanstatusfail.size()!=0){
			Hashtable idsb = (Hashtable)getIdSuburusanstatusfail.get(0);
			id_suburusanstatusfailppt = (String)idsb.get("id_suburusanstatusfailppt");
			id_suburusanstatus = (String)idsb.get("id_suburusanstatus");
		}
		
		modelUPT.setNamaPengarah(userIdNeg);
	    Vector dataNamaPengarah = modelUPT.getNamaPengarah();
	    if(dataNamaPengarah.size()!=0){
	    	Hashtable np = (Hashtable)dataNamaPengarah.get(0);
	    	nama_pengarah = np.get("nama_pengarah").toString();
	    }
    	}
		
		
	    
	    
	    context.put("nama_pengarah",nama_pengarah);
	    
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("selectPejabatPTGX",HTML.SelectPejabatPTG("socPejabatPTG",null,null,"style=width:auto class=disabled disabled")); 
		context.put("selectPejabatPTDX",HTML.SelectPejabatPTD("socPejabatPTD",null,null,"style=width:auto class=disabled disabled")); 
		context.put("showAlertPaging","no");
		context.put("showSUKSeluruh", "no");
		context.put("showSUKSebahagian", "no");
		context.put("showPTDSeluruh", "no");
		context.put("showPTDSebahagian", "no");
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("viewEndosan".equals(submit)){
    		
    		//list hakmilik PTG shj
    		listHakmilikPTGOnly(session,idpermohonan);
    		
    		//list hakmilik PTD shj
    		listHakmilikPTDOnly(session,idpermohonan);
    		
    		//list hakmilik PTG
    		listHakmilikPTG(session,idpermohonan);
    		
    		//list hakmilik PTD
    		listHakmilikPTD(session,idpermohonan);

    		//screen
    		vm = mainscreen;
    
    	}//close 
    	
    	else 
    	if("tambahPTG".equals(submit)){
        		
    		//list hakmilik ptg shj
    		model.setHakmilikPTGOnly(idpermohonan);
     		listHakmilikPTGOnly = model.getListHakmilikPTGOnly();
    		
     		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
     		//NEW MODE
     		if(listHakmilikPTGOnly.size()==0){
     			
     			//form validation
        		context.put("mode","new");
        		
        		//list hakmilik PTG
        		listHakmilikPTG(session,idpermohonan);
        
        		//dropdown pejabat ptg by negeri user login
        		if(userIdNeg!=""){
        			context.put("selectPejabatPTG",HTML.SelectPejabatPTGbyNegeri(userIdNeg,"socPejabatPTG",null,null,"style=width:auto"));
        		}else{
        			context.put("selectPejabatPTG",HTML.SelectPejabatPTG("socPejabatPTG",null,null,"style=width:auto"));   
        		}
        		
            	if("simpanPTG".equals(submit2)){
            		
            		if (doPost.equals("true")) {
            			//simpan data
            			simpanPilihanPTG(session,id_fail);
            			/*
            			if(id_status.equals("76") && (id_suburusanstatus.equals("1486") || id_suburusanstatus.isEmpty())){
            				updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
            			}
            			*/
            			if(modelUPT.cekStatusFailDahWujud(idpermohonan,"716102426","52")==false)
	            		{
	        			modelUPT.updateStatus(idpermohonan,id_user, "1610242");
	        			updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
	            		}
            		}
            		
            		//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		header.setDataHeader(idpermohonan);
            		dataHeader = header.getDataHeader();
            		context.put("dataHeader", dataHeader);
            		if(dataHeader.size()!=0){
            			Hashtable dh = (Hashtable) dataHeader.get(0);
            			id_status = (String)dh.get("id_status");
            		}
          		
            		//list hakmilik PTG
            		listHakmilikPTG(session,idpermohonan);
            		
            		//list hakmilik PTG shj
            		listHakmilikPTGOnly(session,idpermohonan);
            		
            		//data header (get id_status)
    	    		dataHeaderStatus(session,idpermohonan);
    	    		id_pejabat_ptg = dataHeaderStatus(session,idpermohonan);
    	    		
    	    		//dropdown
            		context.put("selectPejabatPTG",HTML.SelectPejabatPTG("socPejabatPTG",Utils.parseLong(id_pejabat_ptg),null,"style=width:auto disabled class=disabled"));  
            		
            		//alert
            		context.put("showAlertPaging","yes");
            		//JOptionPane.showMessageDialog (null, "Sila Klik Kembali dan Klik 'Paging' No.21 Untuk Bukti Penyampaian Borang K", "Langkah Seterusnya", JOptionPane.INFORMATION_MESSAGE);
            		
            	}//close simpanMaklumatPTG
     			
     		//VIEW MODE	
     		}else{
     			
     			//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");
        		
        		//dropdown
        		context.put("selectPejabatPTG",HTML.SelectPejabatPTG("socPejabatPTG",Utils.parseLong(id_pejabat_ptg),null,"style=width:auto disabled class=disabled"));  
        		
        		//list hakmilik PTG
        		listHakmilikPTG(session,idpermohonan);
     			
        		//list hakmilik PTG shj
        		listHakmilikPTGOnly(session,idpermohonan);
        		
        		if("kemaskiniPTG".equals(submit2)){
        			
        			//form validation
            		context.put("mode","view");
            		context.put("isEdit","yes");
            		
            		//dropdown pejabat ptg by negeri user login
            		if(userIdNeg!=""){
            			context.put("selectPejabatPTG",HTML.SelectPejabatPTGbyNegeri(userIdNeg,"socPejabatPTG",Utils.parseLong(id_pejabat_ptg),null,"style=width:auto"));
            		}else{
            			context.put("selectPejabatPTG",HTML.SelectPejabatPTG("socPejabatPTG",Utils.parseLong(id_pejabat_ptg),null,"style=width:auto"));   
            		}
            		
            		String submit3 = getParam("command3");
                	myLogger.info("submit[3] : " + submit3);
                	
                	if("updatePTG".equals(submit3)){
                		
                		if (doPost.equals("true")) {
                			//update data
                			updatePilihanPTG(session,id_fail); 
                		}
                		
                		//form validation
                		context.put("mode","view");
                		context.put("isEdit","no");
                		
                		//list hakmilik PTG
                		listHakmilikPTG(session,idpermohonan);
                		
                		//list hakmilik PTG shj
                		listHakmilikPTGOnly(session,idpermohonan);
                		
                		//data header (get id_status)
        	    		dataHeaderStatus(session,idpermohonan);
        	    		id_pejabat_ptg = dataHeaderStatus(session,idpermohonan);
        	    		
                		//dropdown
                		context.put("selectPejabatPTG",HTML.SelectPejabatPTG("socPejabatPTG",Utils.parseLong(id_pejabat_ptg),null,"style=width:auto disabled class=disabled"));  
                		
                	}//close updatePTG
                	
        		}//close kemaskiniPTG
        		
     		}//close view mode
    		
    		//screen
    		vm = screenPTG;
    		    
        }//close tambahPTG
    	
    	else 
    	if("tambahPTD".equals(submit)){
    	    		
    		//list hakmilik ptd shj
    		model.setHakmilikPTDOnly(idpermohonan);
     		listHakmilikPTDOnly = model.getListHakmilikPTDOnly();
    		
     		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	//NEW MODE
     		if(listHakmilikPTDOnly.size()==0){
     			
     			//form validation
        		context.put("mode","new");
        		
        		//list hakmilik PTD
        		listHakmilikPTD(session,idpermohonan);
        		
        		//dropdown pejabat ptd by negeri user login
        		if(userIdNeg!=""){
        			context.put("selectPejabatPTD",HTML.SelectPejabatPTDbyNegeri(userIdNeg,"socPejabatPTD",null,null,"style=width:auto"));
        		}else{
        			context.put("selectPejabatPTD",HTML.SelectPejabatPTD("socPejabatPTD",null,null,"style=width:auto"));   
        		}
        		
        		
            	if("simpanPTD".equals(submit2)){
            		
            		if (doPost.equals("true")) {
            			//simpan data
            			simpanPilihanPTD(session,id_fail); 
            			/*
            			if(id_status.equals("76") && (id_suburusanstatus.equals("1486") || id_suburusanstatus.isEmpty())){
            				updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
            			}
            			*/
     					if(modelUPT.cekStatusFailDahWujud(idpermohonan,"716102426","52")==false)
	            		{
	        			modelUPT.updateStatus(idpermohonan,id_user, "1610242");
	        			updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
	            		}
            		}
            		
            		//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		//list hakmilik PTD
            		listHakmilikPTD(session,idpermohonan);
            		
            		//list hakmilik PTD shj
            		listHakmilikPTDOnly(session,idpermohonan);
            		
            		//data header (get id_status)
    	    		dataHeaderStatusPTD(session,idpermohonan);
    	    		id_pejabat_ptd = dataHeaderStatusPTD(session,idpermohonan);
    	    		
    	    		//dropdown
            		context.put("selectPejabatPTD",HTML.SelectPejabatPTD("socPejabatPTD",Utils.parseLong(id_pejabat_ptd),null,"style=width:auto disabled class=disabled "));  
            	
            		//alert
            		context.put("showAlertPaging","yes");
            		//JOptionPane.showMessageDialog (null, "Sila Klik Kembali dan Klik 'Paging' No.21 Untuk Bukti Penyampaian Borang K", "Langkah Seterusnya", JOptionPane.INFORMATION_MESSAGE);
            		
            		
            	}//close simpanMaklumatPTD
     			
     		//VIEW MODE	
     		}else{
     			
     			//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");
        		
        		//dropdown
        		context.put("selectPejabatPTD",HTML.SelectPejabatPTD("socPejabatPTD",Utils.parseLong(id_pejabat_ptd),null,"style=width:auto disabled class=disabled"));  
            	
        		//list hakmilik PTD
        		listHakmilikPTD(session,idpermohonan);
     			
        		//list hakmilik PTD shj
        		listHakmilikPTDOnly(session,idpermohonan);
        		
        		if("kemaskiniPTD".equals(submit2)){
        			
        			//form validation
            		context.put("mode","view");
            		context.put("isEdit","yes");
            		
            		//dropdown pejabat ptd by negeri user login
            		if(userIdNeg!=""){
            			context.put("selectPejabatPTD",HTML.SelectPejabatPTDbyNegeri(userIdNeg,"socPejabatPTD",Utils.parseLong(id_pejabat_ptd),null,"style=width:auto"));
            		}else{
            			context.put("selectPejabatPTD",HTML.SelectPejabatPTD("socPejabatPTD",Utils.parseLong(id_pejabat_ptd),null,"style=width:auto"));   
            		}
            		
            		String submit3 = getParam("command3");
                	myLogger.info("submit[3] : " + submit3);
                	
                	if("updatePTD".equals(submit3)){
                		
                		if (doPost.equals("true")) {
                			//update data
                			updatePilihanPTD(session,id_fail); 
                		}
                		
                		//form validation
                		context.put("mode","view");
                		context.put("isEdit","no");
                		
                		//list hakmilik PTD
                		listHakmilikPTD(session,idpermohonan);
                		
                		//list hakmilik PTD shj
                		listHakmilikPTDOnly(session,idpermohonan);
                		
                		//data header (get id_status)
        	    		dataHeaderStatusPTD(session,idpermohonan);
        	    		id_pejabat_ptd = dataHeaderStatusPTD(session,idpermohonan);
        	    		
        	    		//dropdown
                		context.put("selectPejabatPTD",HTML.SelectPejabatPTD("socPejabatPTD",Utils.parseLong(id_pejabat_ptd),null,"style=width:auto disabled class=disabled"));  
                    	
                	}//close updatePTD
                	
        		}//close kemaskiniPTD
        		
     		}//close view mode
        	
    		//screen
    		vm = screenPTD;
    			    
    	}//close tambahPTD
    	
    	else if("hantarHTP".equals(submit)){
    		
    		if (doPost.equals("true")) {
    			//update data
    			hantarHTP(session); 
    		}
    		
    		//list hakmilik PTG shj
    		listHakmilikPTGOnly(session,idpermohonan);
    		
    		//list hakmilik PTD shj
    		listHakmilikPTDOnly(session,idpermohonan);
    		
    		//list hakmilik PTG
    		listHakmilikPTG(session,idpermohonan);
    		
    		//list hakmilik PTD
    		listHakmilikPTD(session,idpermohonan);

    		//screen
    		vm = mainscreen;
    
    	}//close 

		else 
		if("cari".equals(submit)){
    		
    		//carian
    		ListCarian(session,userIdNeg);			
    		listPageDepan = model.getListCarian();
	
			//screen
    		vm = listdepan;
		    
    	}//close cari
    	
    	else{	
    		
    		listPageDepan = model.getListPermohonan(userIdNeg);
    		
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
    		
    		myLogger.info("---------------VM :"+vm);
    		
    		return vm;
    		
	    }//close public template
	
	
	
	
//--METHOD--//	
	
	
	@SuppressWarnings("static-access")
	private void hantarHTP(HttpSession session) throws Exception{
		
		model.updateFlagHTP(getParam("id_hakmilik"));
		
	}//close hantarHTP
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"16102725");
		
	}//close updateSuburusanStatusFailPPT
	
	@SuppressWarnings("static-access")
	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		model.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
	
	@SuppressWarnings("unchecked")
	private void listHakmilikPTG(HttpSession session,String idpermohonan) throws Exception{
    	
		//data & list maklumat tanah
		model.setHakmilikPTG(idpermohonan);
 		Vector listHakmilikPTG = model.getListHakmilikPTG();
 		context.put("listHakmilikPTG", listHakmilikPTG);
 		context.put("saiz_listHakmilikPTG", listHakmilikPTG.size());
      
	}//close listHakmilik

	@SuppressWarnings("unchecked")
	private void listHakmilikPTD(HttpSession session,String idpermohonan) throws Exception{
    	
		//data & list maklumat tanah
		model.setHakmilikPTD(idpermohonan);
 		Vector listHakmilikPTD = model.getListHakmilikPTD();
 		context.put("listHakmilikPTD", listHakmilikPTD);
 		context.put("saiz_listHakmilikPTD", listHakmilikPTD.size());
      
	}//close listHakmilik
	
	@SuppressWarnings("unchecked")
	private void listHakmilikPTGOnly(HttpSession session,String idpermohonan) throws Exception{
    	
		//list hakmilik ptg shj
		String flagSebahagian = "";
		String flagSeluruh = "";
		model.setHakmilikPTGOnly(idpermohonan);
 		Vector listHakmilikPTGOnly = model.getListHakmilikPTGOnly();
 		if(listHakmilikPTGOnly.size()!=0){
 			Hashtable lh = (Hashtable)listHakmilikPTGOnly.get(0);
 			flagSebahagian = (String)lh.get("flagSebahagian");
 			flagSeluruh = (String)lh.get("flagSeluruh");
 		}
 		
 		if(!flagSebahagian.equals("0")){
 			context.put("showSUKSebahagian", "yes");
 		}else{
 			context.put("showSUKSebahagian", "no");
 		}
 		
 		if(!flagSeluruh.equals("0")){
 			context.put("showSUKSeluruh", "yes");
 		}else{
 			context.put("showSUKSeluruh", "no");
 		}
 		
 		context.put("listHakmilikPTGOnly", listHakmilikPTGOnly);
 		context.put("saiz_listHakmilikPTGOnly", listHakmilikPTGOnly.size());
      
 		
 		
	}//close listHakmilik
	
	@SuppressWarnings("unchecked")
	private void listHakmilikPTDOnly(HttpSession session,String idpermohonan) throws Exception{
    	
		//list hakmilik ptg shj
		String flagSebahagian = "";
		String flagSeluruh = "";
		model.setHakmilikPTDOnly(idpermohonan);
 		Vector listHakmilikPTDOnly = model.getListHakmilikPTDOnly();
 		if(listHakmilikPTDOnly.size()!=0){
 			Hashtable lh = (Hashtable)listHakmilikPTDOnly.get(0);
 			flagSebahagian = (String)lh.get("flagSebahagian");
 			flagSeluruh = (String)lh.get("flagSeluruh");
 		}
 		
 		if(!flagSebahagian.equals("0")){
 			context.put("showPTDSebahagian", "yes");
 		}else{
 			context.put("showPTDSebahagian", "no");
 		}
 		
 		if(!flagSeluruh.equals("0")){
 			context.put("showPTDSeluruh", "yes");
 		}else{
 			context.put("showPTDSeluruh", "no");
 		}
 		
 		context.put("listHakmilikPTDOnly", listHakmilikPTDOnly);
 		context.put("saiz_listHakmilikPTDOnly", listHakmilikPTDOnly.size());
      
	}//close listHakmilik
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private String dataHeaderStatus(HttpSession session,String idpermohonan) throws Exception{
    	
		//header
		String id_endosan_borangk_ptg = "";
    	header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_endosan_borangk_ptg = (String)dh.get("id_endosan_borangk_ptg");
		}
		
		return id_endosan_borangk_ptg;
		
	}//close dataHeaderStatus
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private String dataHeaderStatusPTD(HttpSession session,String idpermohonan) throws Exception{
    	
		//header
		String id_endosan_borangk_ptd = "";
    	header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_endosan_borangk_ptd = (String)dh.get("id_endosan_borangk_ptd");
		}
		
		return id_endosan_borangk_ptd;
		
	}//close dataHeaderStatusPTD
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void simpanPilihanPTG(HttpSession session,String idfail) throws Exception{
    	
		Db db = null;
		try {
			db = new Db();
			
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_fail", idfail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		h.put("txtCatatanPTG", getParam("txtCatatanPTG"));
		String socPejabatPTG = getParam("socPejabatPTG");
		
		String idUser = (String)session.getAttribute("_ekptg_user_id");
		
		String[] cbsemaks = request.getParameterValues("cbsemaks");
	 	  
		//tambahan kemasukan tarikh endorsan 20042012
		String[] idDateEndors = request.getParameterValues("id_endosanborangk");
		
		if(cbsemaks!=null){
			for (int i = 0; i < cbsemaks.length; i++) { 
				model.simpanFlagPilihanPTG(h,idUser,cbsemaks[i],db);
			}
			model.updateNamaPejabatPTG(h,socPejabatPTG,db);
		}else{
			model.updateNamaPejabatPTG(h,"",db);
		}
  
		//tambahan kemasukan tarikh endorsan 20042012
		if(idDateEndors!=null){
			for (int i = 0; i < idDateEndors.length; i++) { 
				int bil = i+1;
				String date = "txdTarikhCatatan"+bil;
				model.simpanTarikhEndorsan(idUser,idDateEndors[i],getParam(date),db);
			}
		}
		
		} finally {
			if (db != null)
				db.close();
		}
		
	}//close simpanPilihanPTG
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void simpanPilihanPTD(HttpSession session,String idfail) throws Exception{
    	
		Db db = null;
		try {
			db = new Db();
			
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_fail", idfail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		h.put("txtCatatanPTD", getParam("txtCatatanPTD"));
		String socPejabatPTD = getParam("socPejabatPTD");
		
		String idUser = (String)session.getAttribute("_ekptg_user_id");
		
		String[] cbsemaks = request.getParameterValues("cbsemaks");
		
		//tambahan kemasukan tarikh endorsan 20042012
		String[] idDateEndors = request.getParameterValues("id_endosanborangk");
	
		
		if(cbsemaks!=null){
			for (int i = 0; i < cbsemaks.length; i++) { 
				model.simpanFlagPilihanPTD(h,idUser,cbsemaks[i],db);
			}
			model.updateNamaPejabatPTD(h,socPejabatPTD,db);
		}else{
			model.updateNamaPejabatPTD(h,"",db);
		}
		
		//tambahan kemasukan tarikh endorsan 20042012
		if(idDateEndors!=null){
			for (int i = 0; i < idDateEndors.length; i++) { 
				int bil = i+1;
				String date = "txdTarikhCatatan"+bil;
				model.simpanTarikhEndorsan(idUser,idDateEndors[i],getParam(date),db);
			}
		}
		
		} finally {
			if (db != null)
				db.close();
		}
		
  
		
	}//close simpanPilihanPTG
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updatePilihanPTG(HttpSession session,String idfail) throws Exception{
		Db db = null;
		try {
			db = new Db();
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_fail", idfail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		h.put("txtCatatanPTG", getParam("txtCatatanPTG"));
		String socPejabatPTG = getParam("socPejabatPTG");
		
		String idUser = (String)session.getAttribute("_ekptg_user_id");
		String idPermohonan = getParam("id_permohonan");
		
		model.deleteFlagPilihanPTG(idUser,idPermohonan,db);
		
		String[] cbsemaks = request.getParameterValues("cbsemaks");
	 	  
		//tambahan kemasukan tarikh endorsan 20042012
		String[] idDateEndors = request.getParameterValues("id_endosanborangk");
		
		if(cbsemaks!=null){
			for (int i = 0; i < cbsemaks.length; i++) { 
				model.simpanFlagPilihanPTG(h,idUser,cbsemaks[i],db);
			}
			model.updateNamaPejabatPTG(h,socPejabatPTG,db);
		}else{
			model.updateNamaPejabatPTG(h,"",db);
		}
  
		//tambahan kemasukan tarikh endorsan 20042012
		if(idDateEndors!=null){
			for (int i = 0; i < idDateEndors.length; i++) { 
				int bil = i+1;
				String date = "txdTarikhCatatan"+bil;
				model.simpanTarikhEndorsan(idUser,idDateEndors[i],getParam(date),db);
			}
		}
		} finally {
			if (db != null)
				db.close();
		}
		
		
	}//close updatePilihanPTG
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updatePilihanPTD(HttpSession session,String idfail) throws Exception{
    	
		Db db = null;
		try {
			db = new Db();
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_fail", idfail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		h.put("txtCatatanPTD", getParam("txtCatatanPTD"));
		String socPejabatPTD = getParam("socPejabatPTD");
		
		String idUser = (String)session.getAttribute("_ekptg_user_id");
		String idPermohonan = getParam("id_permohonan");
		
		model.deleteFlagPilihanPTD(idUser,idPermohonan,db);
		
		String[] cbsemaks = request.getParameterValues("cbsemaks");
		
		//tambahan kemasukan tarikh endorsan 20042012
		String[] idDateEndors = request.getParameterValues("id_endosanborangk");
		
		if(cbsemaks!=null){
			for (int i = 0; i < cbsemaks.length; i++) { 
				model.simpanFlagPilihanPTD(h,idUser,cbsemaks[i],db);
			}
			model.updateNamaPejabatPTD(h,socPejabatPTD,db);
		}else{
			model.updateNamaPejabatPTD(h,"",db);
		}
		
		if(idDateEndors!=null){
			for (int i = 0; i < idDateEndors.length; i++) { 
				int bil = i+1;
				String date = "txdTarikhCatatan"+bil;
				model.simpanTarikhEndorsan(idUser,idDateEndors[i],getParam(date),db);
			}
		}
		} finally {
			if (db != null)
				db.close();
		}
		
	}//close updatePilihanPTD
	
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
