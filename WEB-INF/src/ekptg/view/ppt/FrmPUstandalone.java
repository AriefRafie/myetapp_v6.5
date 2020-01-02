package ekptg.view.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmSek8PermintaanUkurData;
import ekptg.model.ppt.FrmUPTSek8HakmilikData;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmPUstandalone extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmPUstandalone.class);
	
	//model
	FrmSek8PermintaanUkurData model = new FrmSek8PermintaanUkurData();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		//command for pagings
    	String action = getParam("action");
    	
    	String vm = "";   	
    	
    	
    	Vector listPageDepan = new Vector();
    	listPageDepan.clear();

    	//screen jsp
    	String listdepan = "app/ppt/PUstandalone/frmPUList.jsp";
    	String mainscreen = "app/ppt/PUstandalone/frmPUmainscreen.jsp";
    	
    	
    	//utils
    	context.put("Utils", new ekptg.helpers.Utils());
    	
    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    	
    	//anchor
    	anchor(getParam("ScreenLocation"),getParam("CursorPoint"));
    	
    	//tabbed
    	String selectedTab = selectedTab();
    	
		//paging
    	paging(getParam("paging"));
    	
		//user login id
    	//String id_user = (String) session.getAttribute("_ekptg_user_id");
    	String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");
    	
	  	//default list
    	listPageDepan = model.getListPUStandAlone(userIdNeg);
    	
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("onchange", "no");
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	String submit2 = getParam("command2");
    	myLogger.info("submit2 : " + submit2);
    	
    	String submit3 = getParam("command3");
    	myLogger.info("submit3 : " + submit3);
    	
    	myLogger.info("TEST :"+getParam("selectedTabPelarasan"));
		context.put("selectedTabPelarasan",getParam("selectedTabPelarasan"));	
		
		context.put("id_pu","");
		context.put("tambahPB","");
		
		context.put("jenisPU","standalone");	
		context.put("selectedTabPelarasan","0");
    	  
     		if("getPelarasan".equals(submit)){ 
     			context.put("jenisPU","standalone");
     			context.put("mode",getParam("mode"));
         		context.put("isEdit",getParam("isEdit"));
         		context.put("jenisPU","standalone");	
         		
         		myLogger.info("MODE :"+getParam("mode"));
         		myLogger.info("ISEDIT :"+getParam("isEdit"));
         		
         		String id_pu = getParam("id_pu");
        		context.put("id_pu", id_pu);
        		dataMaklumatPU(id_pu,userIdNeg,"disabled");
        		listPB(id_pu);
         		/*
     			dataHMBorangK(idHakmilik);
     			dataPU(idHakmilik);
     			listPB(idHakmilik,"");
     			Hashtable getNilaiSeunit = null;
     			getNilaiSeunit = (Hashtable)getNilaiSeunit(idHakmilik);			
 				context.put("getNilaiSeunit",(String)getNilaiSeunit.get("nilai"));
 				context.put("NilaiSeunit",(String)getNilaiSeunit.get("nilaiseunit"));
     			*/
 				myLogger.info("TEST :"+getParam("selectedTabPelarasan"));
 				context.put("selectedTabPelarasan",getParam("selectedTabPelarasan"));
     			vm = "app/ppt/PermintaanUkur/skrin_pelarasan.jsp";	    			
     		}
     		     		
     		else if("getpb".equals(submit)){      			
     			context.put("tambahPB","");
     			context.put("jenisPU","standalone");
     			context.put("mode",getParam("mode"));
         		context.put("isEdit",getParam("isEdit"));         		     		
         		
         		String id_pu = getParam("id_pu");
        		context.put("id_pu", id_pu);  
        		     		
        		listPB(id_pu);         		
        		
 				myLogger.info("TEST :"+getParam("selectedTabPelarasan"));
 				context.put("selectedTabPelarasan",getParam("selectedTabPelarasan"));
     			vm = "app/ppt/PUstandalone/skrin_PB.jsp";	    			
     		}
     		
     		else if("viewPB".equals(submit)){      			
     			context.put("tambahPB","yes");
     			context.put("jenisPU","standalone");
     			context.put("mode",getParam("mode"));
         		context.put("isEdit",getParam("isEdit"));         		     		
         		
         		String id_pu = getParam("id_pu");
        		context.put("id_pu", id_pu);  
        		
        		String ID_PUPB = getParam("ID_PUPB");
         		context.put("ID_PUPB", ID_PUPB);
        		     	
         		Hashtable viewPB = null;
         		viewPB = (Hashtable)getPB(ID_PUPB);			
				
				context.put("ID_PUPB",(String)viewPB.get("ID_PUPB"));
         		context.put("txtNamaPBPU",(String)viewPB.get("NAMA_PB"));   
         		context.put("txtNoPBPU",(String)viewPB.get("NO_PB"));  
         		context.put("txtSyerAtasPU",(String)viewPB.get("SYER_ATAS"));  
         		context.put("txtSyerBawahPU",(String)viewPB.get("SYER_BAWAH")); 
        		
        		listPB(id_pu);       		
 				myLogger.info("TEST :"+getParam("selectedTabPelarasan"));
 				context.put("selectedTabPelarasan",getParam("selectedTabPelarasan"));
     			vm = "app/ppt/PUstandalone/skrin_PB.jsp";	    			
     			
     		}
     		
     		else if("simpanpb".equals(submit)){      			
     			
     			context.put("jenisPU","standalone");
     			context.put("mode",getParam("mode"));
         		context.put("isEdit",getParam("isEdit"));
         		context.put("jenisPU","standalone");         		
         		
         		String ID_PUPB = getParam("ID_PUPB");
         		context.put("ID_PUPB", ID_PUPB);
         		
         		if(!ID_PUPB.equals(""))
         		{
         			context.put("tambahPB","yes");	
         		}else
         		{
         			context.put("tambahPB","");
         		}
         		
         		String id_pu = getParam("id_pu");
        		context.put("id_pu", id_pu);
         		
        		if (doPost.equals("true")) {
         		model.updateMaklumatPB(session.getAttribute("_ekptg_user_id").toString(),ID_PUPB,id_pu,getParam("txtNamaPBPU"),getParam("txtNoPBPU"),getParam("txtSyerAtasPU"),getParam("txtSyerBawahPU"));	
        		}
         		listPB(id_pu); 	
        		     		
 				myLogger.info("TEST :"+getParam("selectedTabPelarasan"));
 				context.put("selectedTabPelarasan",getParam("selectedTabPelarasan"));
     			vm = "app/ppt/PUstandalone/skrin_PB.jsp";	    			
     		}
     		
     		else if("hapusPB".equals(submit)){      			
     			
     			context.put("jenisPU","standalone");
     			context.put("mode",getParam("mode"));
         		context.put("isEdit",getParam("isEdit"));
         		context.put("jenisPU","standalone");         		
         		
         		String ID_PUPB = getParam("ID_PUPB");
         		context.put("ID_PUPB", ID_PUPB);
         		context.put("tambahPB","");  
         		
         		hapusPBPU(ID_PUPB);      
         		
         		String id_pu = getParam("id_pu");
        		context.put("id_pu", id_pu);
         		
         		model.updateMaklumatPB(session.getAttribute("_ekptg_user_id").toString(),ID_PUPB,id_pu,getParam("txtNamaPBPU"),getParam("txtNoPBPU"),getParam("txtSyerAtasPU"),getParam("txtSyerBawahPU"));	
         		listPB(id_pu); 	
        		     		
 				myLogger.info("TEST :"+getParam("selectedTabPelarasan"));
 				context.put("selectedTabPelarasan",getParam("selectedTabPelarasan"));
     			vm = "app/ppt/PUstandalone/skrin_PB.jsp";	    			
     		}
     		
     		else if("tambahpb".equals(submit)){ 
     			context.put("tambahPB","yes");
     			context.put("jenisPU","standalone");
     			context.put("mode",getParam("mode"));
         		context.put("isEdit",getParam("isEdit"));
         		context.put("jenisPU","standalone");   
         		
         		context.put("ID_PUPB","");
         		context.put("txtNamaPBPU","");   
         		context.put("txtNoPBPU","");  
         		context.put("txtSyerAtasPU","");  
         		context.put("txtSyerBawahPU","");  
         		
         		String id_pu = getParam("id_pu");
        		context.put("id_pu", id_pu);        	
        		        		
        		listPB(id_pu); 
 				context.put("selectedTabPelarasan",getParam("selectedTabPelarasan"));
     			vm = "app/ppt/PUstandalone/skrin_PB.jsp";	    			
     		}
    	
     		else if("registerPU".equals(submit) || "viewPU".equals(submit)){
    		
    		String id_pu = getParam("id_pu");
    		
    		if("registerPU".equals(submit)){
    			//form validation
        		context.put("mode","new");
        		//reset all field
        		resetFieldValue(userIdNeg);
    		}else{ 			
    			context.put("id_pu", id_pu);
    			//form validation
        		context.put("mode","view");
        		context.put("isEdit","no"); 
        		//data pu
        		dataMaklumatPU(id_pu,userIdNeg,"disabled");
    		}
    		
    		
    		if("getAndSetDataHM".equals(submit2)){
    			
    			getAndSetDataHM(getParam("id_hakmilik"),userIdNeg,"yes");
    			
    		}//close getAndSetDataHM
    		
    		else if("doChangeDaerah".equals(submit2)){
    			
    			context.put("onchange", "yes");
    			context.put("isEdit","yes");
    			
    			getAndSetDataHM("",userIdNeg,"no");
    			
    		}//close doChangeDaerah
    		
    		else if("simpanDataPU".equals(submit2)){
    			
    			String result = "";
    			if (doPost.equals("true")) {
    				result = simpanMaklumatPU(session,userIdNeg);
    			}
    			
    			id_pu = result;   			
    			context.put("id_pu",id_pu);
    			
    			if(id_pu!=""){
    				//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");           		
            		//data pu
            		dataMaklumatPU(id_pu,userIdNeg,"disabled");
    			}else{
    				//list fail
    				listPageDepan = model.getListPUStandAlone(userIdNeg);
    				//screen
            		vm = listdepan;
            		return vm;
    			}
    			
    		}//close simpanDataPU
    		
    		else if("kemaskiniDataPU".equals(submit2)){
    			//form validation
        		context.put("isEdit","yes");  
        		//data pu
        		dataMaklumatPU(id_pu,userIdNeg,"enabled");
        		
        		if("updateDataPU".equals(submit3)){
        			if (doPost.equals("true")) {
        				updateDataPU(session,id_pu);       				
        				
        				Vector listPB = new Vector();
        				listPB.clear();		
        				model.listPBstandalone(id_pu);
        		    	listPB = model.getlistPBstandalone();
        		    	myLogger.info(" listPB.size() :"+listPB.size());
        		    	
        		    	myLogger.info(" TEST TARIK :"+getParam("id_award_all1"));
        			
        		    	if(getParam("hdJenisPelarasan").equals("1"))
        		    	{
    	    				if(listPB.size()>0)
    	    	        	{
    	    					String id_award_x = "";
    	    	        		String penama_x = "";
    	    	        		String tempoh_lewat_x = "";
    	    	        		String pampasan_tanah_x = "";
    	    	        		String pampasan_lewat_x = "";
    	    	        		String jumlah_pelarasan_x = "";
    	    	        		
    	    	        		for (int i = 1; i < (listPB.size()+1); i++) 
    	    					{        			 
    	    	        		id_award_x = getParam("id_award_all"+i);
    	    	        		penama_x = getParam("text_penama_all"+i);
    	    	        		tempoh_lewat_x = getParam("text_tempoh_lewat_all"+i);
    	    	        		pampasan_tanah_x = getParam("text_pampasan_tanah_all"+i);
    	    	        		pampasan_lewat_x = getParam("text_bhg_per_caj_all"+i);
    	    	        		jumlah_pelarasan_x = getParam("text_total_all"+i);    
    	    	        		myLogger.info("id_award_x:"+id_award_x);
    	    	        		myLogger.info("penama_x:"+penama_x);
    	    	        		myLogger.info("tempoh_lewat_x:"+tempoh_lewat_x);
    	    	        		myLogger.info("pampasan_tanah_x:"+pampasan_tanah_x);
    	    	        		myLogger.info("pampasan_lewat_x:"+pampasan_lewat_x);
    	    	        		myLogger.info("jumlah_pelarasan_x"+jumlah_pelarasan_x);
    	    	        		/*myLogger.info("id_award_x:"+getParam(id_award_x));
    	    	        		myLogger.info("penama_x:"+getParam(penama_x));
    	    	        		myLogger.info("tempoh_lewat_x:"+getParam(tempoh_lewat_x));
    	    	        		myLogger.info("pampasan_tanah_x:"+getParam(pampasan_tanah_x));
    	    	        		myLogger.info("pampasan_lewat_x:"+getParam(pampasan_lewat_x));
    	    	        		myLogger.info("jumlah_pelarasan_x"+getParam(jumlah_pelarasan_x));*/
    	    	        		model.updatePelarasanPBStandalone_auto(session.getAttribute("_ekptg_user_id").toString(),id_award_x,penama_x,pampasan_tanah_x,tempoh_lewat_x,jumlah_pelarasan_x);
    	    					}        		
    	    	        	} 
        		    	}
        				
        				
        			}
        			//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");           		
            		//data pu
            		dataMaklumatPU(id_pu,userIdNeg,"disabled");
        		}//close updateDataPU
        		
    		}//close kemaskiniDataPU
    		
    		else if("hapusDataPU".equals(submit2)){
    			
    			if (doPost.equals("true")) {
    				hapusDataPU(id_pu);  				
    			}
    			
    			//form validation
        		context.put("mode","new");
        		context.put("isEdit","no");
        		
        		//reset all field
        		resetFieldValue(userIdNeg);
        		
    		}//close hapusDataPU
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close registerPU
    	
    	else{
    		
    		//list fail
    		listPageDepan = model.getListPUStandAlone(userIdNeg);
    		
    		//screen
    		vm = listdepan;
    		
    	}
    		
    		
    		//list PU
	    	context.put("listpu", listPageDepan);
	    	
	    	myLogger.info("vm : "+vm);
    		setupPage(session,action,listPageDepan);
    		this.context.put("selectedTab",selectedTab);
    		return vm;
    		
	    }//close public template
	
	
	
	
	
	
	Hashtable getPB = null;
	public Hashtable getPB(String id_pupb) throws Exception {
		getPB = new Hashtable();
		getPB.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT ID_PUPB,ID_PU,NAMA_PB,NO_PB,SYER_ATAS,SYER_BAWAH FROM TBLPPTPUPB WHERE ID_PUPB = '"+id_pupb+"' ";
			myLogger.info("getPB ::::: "+sql);	
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				
				
				h.put("ID_PUPB", rs.getString("ID_PUPB")==null?"":rs.getString("ID_PUPB"));
				h.put("ID_PU", rs.getString("ID_PU")==null?"":rs.getString("ID_PU"));
				h.put("NAMA_PB", rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
				h.put("NO_PB", rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
				h.put("SYER_ATAS", rs.getString("SYER_ATAS")==null?"":rs.getString("SYER_ATAS"));
				h.put("SYER_BAWAH", rs.getString("SYER_BAWAH")==null?"":rs.getString("SYER_BAWAH"));
				
				
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	 public static void hapusPBPU(String id_pupb) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	  
	    		sql = "DELETE FROM TBLPPTPUPB where ID_PUPB = '"+id_pupb+"'";
	    		stmt.executeUpdate(sql);
 	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusWarta
	
	
	
//--METHOD--//	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateDataPU(HttpSession session,String id_pu) throws Exception{

		Hashtable h = new Hashtable();

		//data hakmilik
		h.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		h.put("txtNoLot", getParam("txtNoLot"));
		h.put("txdTarikhBorangK", getParam("txdTarikhBorangK"));
		h.put("txtLuasAmbil", getParam("txtLuasAmbil"));
		h.put("sorDropdownUnit", getParam("sorDropdownUnit"));
		
		h.put("txtLuasAsal", getParam("txtLuasAsal"));
		h.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));
		
		h.put("socJenisHakmilik", getParam("socJenisHakmilik"));
		h.put("socMukim", getParam("socMukim"));
		h.put("socDaerah", getParam("socDaerah"));
		
		h.put("txtNoFailPU", getParam("txtNoFailPU"));
		
		h.put("txdTarikhSuratPTG", getParam("txdTarikhSuratPTG"));
		h.put("txtNoPelan", getParam("txtNoPelan"));
		h.put("txdTarikhHantarJUPEM", getParam("txdTarikhHantarJUPEM"));		
		h.put("txtNoPU", getParam("txtNoPU"));
		h.put("txdTarikhBorangPU", getParam("txdTarikhBorangPU"));
		
		h.put("txdTarikhTerimaPA", getParam("txdTarikhTerimaPA"));
		h.put("txtNoPA", getParam("txtNoPA"));
		h.put("txdTarikhTerimaSA", getParam("txdTarikhTerimaSA"));		
		h.put("txdTarikhPenyelesaian", getParam("txdTarikhPenyelesaian"));
		h.put("txtCatatan", getParam("txtCatatan"));
		h.put("txtLotBaru", getParam("txtLotBaru"));
		
		h.put("NilaiSeunit", getParam("NilaiSeunit"));
		
		h.put("tarikhBayarTambahan", getParam("tarikhBayarTambahan"));
		
		//h.put("lblLuasAsal", Utils.RemoveSymbol(getParam("lblLuasAsal"))); 
    	//h.put("lblLuasAmbil", Utils.RemoveSymbol(getParam("lblLuasAmbil"))); 
		
		
		h.put("txtNoFailPUJUPEM", getParam("txtNoFailPUJUPEM"));
		h.put("txtNoFailProjek", getParam("txtNoFailProjek"));
		h.put("txtTujuanProjek", getParam("txtTujuanProjek"));
		h.put("txdTarikhWarta", getParam("txdTarikhWarta"));
		h.put("txtNoWarta", getParam("txtNoWarta"));
		h.put("txtLuasBorangK", getParam("txtLuasBorangK"));
		h.put("sorDropdownUnitBorangK", getParam("sorDropdownUnitBorangK"));
		h.put("txtNoWK", getParam("txtNoWK"));
		h.put("txtNoPAasal", getParam("txtNoPAasal"));
		
		
		
		
		
		double luas_asal = 0;
		double luas_ambil = 0;	
		double beza_luas = 0;
		
		if(getParam("sorDropdownUnit").equals("1"))
		{
    	h.put("lblLuasAmbil", (Utils.parseDouble((getParam("txtLuasAmbil"))))+""); 
    		luas_ambil = (Utils.parseDouble((getParam("txtLuasAmbil"))));
		}
		else if(getParam("sorDropdownUnit").equals("2"))
		{
			h.put("lblLuasAmbil", (Utils.parseDouble((getParam("txtLuasAmbil")))/10000)+""); 
			luas_ambil = (Utils.parseDouble((getParam("txtLuasAmbil")))/10000);
		}
		
		if(getParam("sorDropdownUnitAsal").equals("1"))
		{
			h.put("lblLuasAsal", (Utils.parseDouble((getParam("txtLuasAsal"))))+""); 
			luas_asal = (Utils.parseDouble((getParam("txtLuasAsal"))));
		}
		else if(getParam("sorDropdownUnitAsal").equals("2"))
		{
			h.put("lblLuasAsal", (Utils.parseDouble((getParam("txtLuasAsal")))/10000)+""); 
			luas_asal = (Utils.parseDouble((getParam("txtLuasAsal")))/10000);			
		}
		
		beza_luas = luas_asal - luas_ambil;	
		
		h.put("countBakiLuas", beza_luas+"");
		
		
		h.put("countBezaLuas", Utils.RemoveSymbol(getParam("countBezaLuas")));
		h.put("sorJenisPelarasan", getParam("hdJenisPelarasan"));
		h.put("txtKeluasanPU", Utils.RemoveSymbol(getParam("txtKeluasanPU")));	
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		
		model.updatePUStandAlone(h,id_pu);
		
	}//close updateDataPU
	
	@SuppressWarnings("static-access")
	private void hapusDataPU(String id_pu) throws Exception{
		
		model.hapusDataPU(id_pu);
		
	}//close hapusDataPU
	
	
	private void listPB(String id_pu) throws Exception{    	
		Vector listPB = new Vector();
		listPB.clear();		
		model.listPBstandalone(id_pu);
    	listPB = model.getlistPBstandalone();
    	myLogger.info(" listPB.size() :"+listPB.size());
    	context.put("saiz_pb", listPB.size());
    	context.put("listMaklumatPB", listPB);   	
	}//close listPB
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataMaklumatPU(String id_pu,String userIdNeg,String mode) throws Exception{
		
		model.setDataMaklumatPU(id_pu);
		Vector dataPU = model.getDataMaklumatPU();	
		context.put("dataPU", dataPU);
		
		String id_daerah="",id_mukim="",id_jenishakmilik="";
		if(dataPU.size()!=0){
			Hashtable dp = (Hashtable)dataPU.get(0);
			id_daerah = (String)dp.get("ID_DAERAH");
			id_mukim = (String)dp.get("ID_MUKIM");
			id_jenishakmilik = (String)dp.get("ID_JENISHAKMILIK");
		}
		

		if(mode.equals("disabled")){
			mode = "disabled class=disabled";
		}else{
			mode = "";
		}
		
		//dropdown
		if(id_daerah!=""){
			context.put("selectMukim",HTML.SelectMukimNoKodByDaerah(id_daerah,"socMukim",Utils.parseLong(id_mukim)," "+mode+" style=width:350px"));
		}else{
			context.put("selectMukim",HTML.SelectMukimNoKod("socMukim",Utils.parseLong(id_mukim)," "+mode+" style=width:350px"));
		}
    	context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(id_jenishakmilik)," "+mode+" style=width:350px")); 
    	context.put("selectDaerah",HTML.SelectDaerahByNegeriKOD("2",userIdNeg,"socDaerah",Utils.parseLong(id_daerah),null," "+mode+" id=socDaerah style=width:350px onChange=\"doChangeDaerah();\" "));
    	
	}//close dataMaklumatPU
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private String simpanMaklumatPU(HttpSession session,String userIdNeg) throws Exception{

		Hashtable h = new Hashtable();

		//data hakmilik
		h.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		h.put("txtNoLot", getParam("txtNoLot"));
		h.put("txdTarikhBorangK", getParam("txdTarikhBorangK"));
		h.put("txtLuasAmbil", getParam("txtLuasAmbil"));
		h.put("sorDropdownUnit", getParam("sorDropdownUnit"));
		
		h.put("txtLuasAsal", getParam("txtLuasAsal"));
		h.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));
		
		h.put("socJenisHakmilik", getParam("socJenisHakmilik"));
		h.put("socMukim", getParam("socMukim"));
		h.put("socDaerah", getParam("socDaerah"));
		
		h.put("txtNoFailPU", getParam("txtNoFailPU"));
		
		h.put("txdTarikhSuratPTG", getParam("txdTarikhSuratPTG"));
		h.put("txtNoPelan", getParam("txtNoPelan"));
		h.put("txdTarikhHantarJUPEM", getParam("txdTarikhHantarJUPEM"));		
		h.put("txtNoPU", getParam("txtNoPU"));
		h.put("txdTarikhBorangPU", getParam("txdTarikhBorangPU"));
		
		h.put("txdTarikhTerimaPA", getParam("txdTarikhTerimaPA"));
		h.put("txtNoPA", getParam("txtNoPA"));
		h.put("txdTarikhTerimaSA", getParam("txdTarikhTerimaSA"));		
		h.put("txdTarikhPenyelesaian", getParam("txdTarikhPenyelesaian"));
		h.put("txtCatatan", getParam("txtCatatan"));
		h.put("txtLotBaru", getParam("txtLotBaru"));
		
		h.put("txtKeluasanPU", Utils.RemoveSymbol(getParam("txtKeluasanPU")));
		
		h.put("txtNoFailPUJUPEM", getParam("txtNoFailPUJUPEM"));
		h.put("txtNoFailProjek", getParam("txtNoFailProjek"));
		h.put("txtTujuanProjek", getParam("txtTujuanProjek"));
		h.put("txdTarikhWarta", getParam("txdTarikhWarta"));
		h.put("txtNoWarta", getParam("txtNoWarta"));
		h.put("txtLuasBorangK", getParam("txtLuasBorangK"));
		h.put("sorDropdownUnitBorangK", getParam("sorDropdownUnitBorangK"));
		h.put("txtNoWK", getParam("txtNoWK"));
		h.put("txtNoPAasal", getParam("txtNoPAasal"));
		
		

		double luas_asal = 0;
		double luas_ambil = 0;
		double beza_luas = 0;
		
		if(getParam("sorDropdownUnit").equals("1"))
		{
    	h.put("lblLuasAmbil", (Utils.parseDouble((getParam("txtLuasAmbil"))))+""); 
    		luas_ambil = (Utils.parseDouble((getParam("txtLuasAmbil"))));
		}
		else if(getParam("sorDropdownUnit").equals("2"))
		{
			h.put("lblLuasAmbil", (Utils.parseDouble((getParam("txtLuasAmbil")))/10000)+""); 
			luas_ambil = (Utils.parseDouble((getParam("txtLuasAmbil")))/10000);
		}
		
		if(getParam("sorDropdownUnitAsal").equals("1"))
		{
			h.put("lblLuasAsal", (Utils.parseDouble((getParam("txtLuasAsal"))))+""); 
			luas_asal = (Utils.parseDouble((getParam("txtLuasAsal"))));
		}
		else if(getParam("sorDropdownUnitAsal").equals("2"))
		{
			h.put("lblLuasAsal", (Utils.parseDouble((getParam("txtLuasAsal")))/10000)+""); 
			luas_asal = (Utils.parseDouble((getParam("txtLuasAsal")))/10000);			
		}
		
		beza_luas = luas_asal - luas_ambil;	
		
		h.put("countBakiLuas", beza_luas+"");
		h.put("countBezaLuas", Utils.RemoveSymbol(getParam("HDcountBezaLuas")));   	
		h.put("sorJenisPelarasan", getParam("HDsorJenisPelarasan"));	
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		
		return model.simpanPUStandAlone(h,userIdNeg);
		
	}//close simpanMaklumatPU
	
	@SuppressWarnings("unchecked")
	private void getAndSetDataHM(String id_hakmilik,String userIdNeg,String getDataFromPopup) throws Exception{  
		
		String socMukim=getParam("socMukim"),socJenisHakmilik=getParam("socJenisHakmilik"),txtNoHakmilik=getParam("txtNoHakmilik"), 
		txtNoLot=getParam("txtNoLot"),txdTarikhBorangK=getParam("txdTarikhBorangK"), txtLuasAmbil=getParam("txtLuasAmbil"),
		socDaerah=getParam("socDaerah"),sorDropdownUnit=getParam("sorDropdownUnit"),lblLuasAsal=getParam("lblLuasAsal"),
		lblLuasAmbil=getParam("lblLuasAmbil"),sorDropdownUnitAsal=getParam("sorDropdownUnitAsal"),txtLuasAsal = getParam("txtLuasAsal");
		
		if(getDataFromPopup.equals("yes")){
			FrmUPTSek8HakmilikData.setListCarianPopupHM("","","","","",id_hakmilik);
			Vector listHM = FrmUPTSek8HakmilikData.getListCarianHM();	
			if(listHM.size()!=0){
				Hashtable hm = (Hashtable)listHM.get(0);
				socDaerah = (String)hm.get("ID_DAERAH");
				socMukim = (String)hm.get("ID_MUKIM");
				socJenisHakmilik = (String)hm.get("ID_JENISHAKMILIK");
				txtNoHakmilik = (String)hm.get("NO_HAKMILIK");
				txtNoLot = (String)hm.get("NO_LOTPT");
				txdTarikhBorangK = (String)hm.get("TARIKH_BORANGK");
				txtLuasAmbil = (String)hm.get("LUAS_AMBIL");
				sorDropdownUnit = (String)hm.get("ID_UNITLUASAMBIL_CONVERT");				
				lblLuasAsal = (String)hm.get("LUAS_LOT");
				lblLuasAmbil = (String)hm.get("LUAS_AMBIL");
				
				sorDropdownUnitAsal = (String)hm.get("ID_UNITLUASLOT_CONVERT");
				txtLuasAsal = (String)hm.get("LUAS_LOT");
			}
		}
	
		//dropdown
		if(socDaerah!=""){
			context.put("selectMukim",HTML.SelectMukimNoKodByDaerah(socDaerah,"socMukim",Utils.parseLong(socMukim),"style=width:350px"));
		}else{
			context.put("selectMukim",HTML.SelectMukimNoKod("socMukim",Utils.parseLong(socMukim),"style=width:350px"));
		}
    	context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(socJenisHakmilik),"style=width:350px")); 
    	context.put("selectDaerah",HTML.SelectDaerahByNegeriKOD("2",userIdNeg,"socDaerah",Utils.parseLong(socDaerah),null,"id=socDaerah style=width:350px onChange=\"doChangeDaerah();\" "));
    	
		context.put("txtNoHakmilik", txtNoHakmilik);
		context.put("txtNoLot", txtNoLot);
		context.put("txdTarikhBorangK", txdTarikhBorangK);
		context.put("txtLuasAmbil", txtLuasAmbil);
		context.put("sorDropdownUnit", sorDropdownUnit);
		
		context.put("txtLuasAsal", txtLuasAsal);
		context.put("sorDropdownUnitAsal", sorDropdownUnitAsal);
		
		//data pu
		context.put("txtNoFailPU", getParam("txtNoFailPU"));
    	context.put("txdTarikhSuratPTG", getParam("txdTarikhSuratPTG"));
    	context.put("txtNoPelan", getParam("txtNoPelan"));
    	context.put("txdTarikhHantarJUPEM", getParam("txdTarikhHantarJUPEM"));
    	context.put("txtNoPU", getParam("txtNoPU"));
    	context.put("txdTarikhBorangPU", getParam("txdTarikhBorangPU"));
    	context.put("txdTarikhTerimaPA", getParam("txdTarikhTerimaPA"));
    	context.put("txtNoPA", getParam("txtNoPA"));
    	context.put("txtLotBaru", getParam("txtLotBaru"));
    	context.put("txdTarikhTerimaSA", getParam("txdTarikhTerimaSA"));
    	context.put("txdTarikhPenyelesaian", getParam("txdTarikhPenyelesaian"));
    	context.put("txtCatatan", getParam("txtCatatan"));
    	
    	
    	//Pelarasan
    	double bakiLuas=0;  //Utils.parseDouble(getParam("countBakiLuas")==""?"":getParam("countBakiLuas"));
    	if(lblLuasAsal!="" && lblLuasAmbil!=""){
    		bakiLuas = Utils.parseDouble(lblLuasAsal)-Utils.parseDouble(lblLuasAmbil);
    	}
    	
    	double bezaLuas=0; //Utils.parseDouble(getParam("countBezaLuas")==""?"":getParam("countBezaLuas"));
    	String keluasanPU = getParam("txtKeluasanPU");
    	if(!keluasanPU.equals("")){
    		bezaLuas = Utils.parseDouble(keluasanPU)-bakiLuas;
    	}
    	
    	String sorJenisPelarasan = getParam("HDsorJenisPelarasan");
    	if((!keluasanPU.equals("") && !keluasanPU.equals("0.0000")) && bezaLuas<0){
    		sorJenisPelarasan = "1";
    	}else if((!keluasanPU.equals("") && !keluasanPU.equals("0.0000")) && bezaLuas>0){
    		sorJenisPelarasan = "2";
    	}else if((!keluasanPU.equals("") && !keluasanPU.equals("0.0000")) && bezaLuas==0){
    		sorJenisPelarasan = "3";
    	}
    	
    	context.put("txtKeluasanPU", keluasanPU); //A
    	context.put("lblLuasAsal", lblLuasAsal); //B
    	context.put("lblLuasAmbil", lblLuasAmbil); //C
    	context.put("countBakiLuas", Utils.formatLuasHM(bakiLuas)); //D = B-C
    	context.put("countBezaLuas", Utils.formatLuasHM(bezaLuas)); //A-D
    	context.put("sorJenisPelarasan", sorJenisPelarasan);
    	
	}//close getAndSetDataHM
	
	private void resetFieldValue(String userIdNeg) throws Exception{  	
		
		context.put("id_pu", "");
		
		//data hakmilik
		context.put("txtNoHakmilik", "");
		context.put("txtNoLot", "");
		context.put("txdTarikhBorangK", "");
		context.put("txtLuasAmbil", "");
		context.put("sorDropdownUnit", "");
		
		context.put("txtLuasAsal", "");
		context.put("sorDropdownUnitAsal", "");
		
		//data pu
		context.put("txtNoFailPU", "");
    	context.put("txdTarikhSuratPTG", "");
    	context.put("txtNoPelan", "");
    	context.put("txdTarikhHantarJUPEM", "");
    	context.put("txtNoPU", "");
    	context.put("txdTarikhBorangPU", "");
    	context.put("txdTarikhTerimaPA", "");
    	context.put("txtNoPA", "");
    	context.put("txtLotBaru", "");
    	context.put("txdTarikhTerimaSA", "");
    	context.put("txdTarikhPenyelesaian", "");
    	context.put("txtCatatan", "");
    	
    	//label pelarasan
    	context.put("txtKeluasanPU", "0.0000");
    	context.put("lblLuasAsal", "0.0000");
    	context.put("lblLuasAmbil", "0.0000");
    	context.put("countBakiLuas", "0.0000");
    	context.put("countBezaLuas", "0.0000");
    	context.put("sorJenisPelarasan", "");
    	
    	//dropdown
    	context.put("selectDaerah",HTML.SelectDaerahByNegeriKOD("2",userIdNeg,"socDaerah",null,null,"id=socDaerah style=width:350px onChange=\"doChangeDaerah();\" "));
    	context.put("selectMukim",HTML.SelectMukimNoKod("socMukim",null,"style=width:350px"));
    	context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",null,"style=width:350px")); 
    	
	}//close resetFieldValue
	
	private void anchor(String ScreenLocation, String CursorPoint) throws Exception{  	
    	context.put("ScreenLocation", ScreenLocation);
    	context.put("CursorPoint", CursorPoint);
	}//close anchor
	
	private String selectedTab() throws Exception{
		String selectedTab = new String();
		selectedTab = getParam("tabId").toString();	
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}
		
		return selectedTab;	
	}//close selectedTab
	
	private void paging(String flagPaging) throws Exception{		
		if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "23");
    	}		
	}//close paging
	
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
			this.context.put("listpu",paging.getPage(page));
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
