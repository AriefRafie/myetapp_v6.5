/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ekptg.view.htp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.model.htp.FrmPajakanHeaderData;
import ekptg.model.htp.FrmPajakanMemorandumJemaahMenteriData;

/**
 *
 * @author Firzan.Fir
 */
public class FrmPajakanMemorandumJemaahMenteriView extends AjaxBasedModule {

    private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(FrmPajakanMemorandumJemaahMenteriView.class);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Vector vDraf = null;
    Hashtable hDraf =null;

    FrmPajakanHeaderData logicHeader = new FrmPajakanHeaderData();
    FrmPajakanMemorandumJemaahMenteriData logic = new FrmPajakanMemorandumJemaahMenteriData();
    
    @Override
    public String doTemplate2() throws Exception {

    	//fir test utk upload file 30032010
    	//this.setUploadFile(true);
    	
    	HttpSession session = this.request.getSession();
    	log.info("request : " + request.getContentType());
    	
    	 String hitButton = getParam("hitButton");
         String selectedTab = getParam("selectedTab");
         String selectedTabLower = getParam("selectedTabLower");
         String idPermohonan = getParam("idPermohonan");
         String tarikhHantarDraf = getParam("txdTarikhHantarDraf");
     	 String tarikhTerimaDraf = getParam("txdTarikhTerimaDraf");
     	 String keteranganDraf = getParam("txtKeteranganDraf");
        
         System.out.println("000000000000"+tarikhHantarDraf+tarikhTerimaDraf+keteranganDraf);

        Boolean postDB = true;
        String doPost = session.getAttribute("doPost").toString();
        
        System.out.println("doPost========"+doPost );
        
        
//        boolean doPost = request.getMethod().equalsIgnoreCase("post");
//        if (doPost.equals("true") || "saveDraf".equals(hitButton)) {
//            postDB = true;
//        }
        if (doPost.equals("true")) {
            postDB = true;
        }
//        if (doPost) {
//            postDB = true;
//        }
        log.debug("doPost:"+doPost);
        log.debug("postDBt:"+postDB);
        //GET DEFAULT PARAM
        String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
        String vm = "";
        String actionPajakan = getParam("actionPajakan"); //our main submit
        if (actionPajakan.isEmpty()){
        	actionPajakan = "papar";
        }
        String submit = getParam("command"); //for doAjax only
        String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
       
        
        if(selectedTab.equals("") || selectedTab.equals(null)){
        	selectedTab = "0";
        }
        
        if(selectedTabLower.equals("") || selectedTabLower.equals(null)){
        	selectedTabLower = "0";
        }
        
        //fir test 30032010
        log.info("actionPajakan : " + actionPajakan);
        log.info("submit : " + submit);
        log.info("mode : " + mode);
        log.info("hitButton : " + hitButton);
        log.info("selectedTab : " + selectedTab);
        log.info("selectedTabLower : " + selectedTabLower);
        
        

        //GET ID PARAM
        String idFailSession = "";
        if (session.getAttribute("idFail") != null){
        	idFailSession = (String) session.getAttribute("idFail");
        }
        String idFail = getParam("idFail");
        String idStatus = getParam("idStatus");
        
        String subUrusan = getParam("subUrusan");
        String idUlasanTeknikal = getParam("idUlasanTeknikal");
        String idUlasanNilai = getParam("idUlasanNilai");
        String idUlasanKJP = getParam("idUlasanKJP");
        String idDraf = getParam("idDraf");
        String idUlasanSPHP = getParam("idUlasanSPHP");

        //fir test 30032010
        log.info("idFail : " + idFail);
        log.info("idStatus : " + idStatus);
        log.info("idPermohonan : " + idPermohonan);
        log.info("subUrusan : " + subUrusan);
        log.info("idUlasanTeknikal : " + idUlasanTeknikal);
        log.info("idUlasanNilai : " + idUlasanNilai);
        log.info("idUlasanKJP : " + idUlasanKJP);
        log.info("idDraf : " + idDraf);
        log.info("idUlasanSPHP : " + idUlasanSPHP);
        
        
        
        //VECTOR
        Vector list = null;
        Vector beanHeader = null;
        
        vm  = "app/htp/frmPajakanMemorandumJemaahMenteri.jsp";
        
        //HITBUTTON
        if (postDB) {
        	if ("savePemohon".equals(hitButton)){    			
    			savePemohon(idPermohonan, session);
    		}
        	if ("saveMemo".equals(hitButton)){    			
        		saveMemo(idPermohonan, session);
    		}
        	if ("saveUlasanKJP".equals(hitButton)){    			
        		saveUlasanKJP(idPermohonan, session);
    		}
        	if ("saveUpdateKJP".equals(hitButton)){    			
        		saveUpdateUlasanKJP(idUlasanKJP, session);
    		}
        	if ("hapusKJP".equals(hitButton)){    			
    			logic.hapusKJP(idUlasanKJP);
    		}
    		if ("saveJPPH".equals(hitButton)){    			
    			saveJPPH(idPermohonan, session);
    		}
    		if ("saveUpdateJPPH".equals(hitButton)){    			
    			saveUpdateJPPH(idUlasanTeknikal, idUlasanNilai, session);
    		}
    		if ("hapusJPPH".equals(hitButton)){    			
    			logic.hapusJPPH(idUlasanTeknikal, idUlasanNilai);
    		}
    		if ("saveUlasanSPHP".equals(hitButton)){    			
    			saveSPHP(idPermohonan, session);
    		}
    		if ("saveUpdateSPHP".equals(hitButton)){    			
    			saveUpdateSPHP(idUlasanSPHP, session);
    		}
    		if ("hapusSPHP".equals(hitButton)){    			
    			logic.hapusSPHP(idUlasanSPHP);
    		}
    		if ("saveDraf".equals(hitButton)){ 
    			log.debug("** save draft **");
    			//saveDraf(idPermohonan, session);
    			downloadDraf(idPermohonan , session);
    		}
    		if ("downloadDraf".equals(hitButton)){ 
    			log.debug("** download draft **");
    			downloadDraf(idPermohonan , session);
    		}
    		if ("saveUpdateDraf".equals(hitButton)){    			
    			saveUpdateDraf(idDraf, session);
    		}
    		if ("hapusDraf".equals(hitButton)){    			
    			logic.hapusDraf(idDraf);
    		}
    		if ("seterusnya".equals(hitButton)){    			
    			logic.seterusnya(idFail, idPermohonan, subUrusan, session);
    		}
    		
    	}
        //log.debug("** here **"+hitButton);
        
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFailSession);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		
		if (beanHeader.size() != 0){
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = hashHeader.get("idFail").toString();
			idPermohonan = hashHeader.get("idPermohonan").toString();
			idStatus = hashHeader.get("idStatus").toString();
			subUrusan = hashHeader.get("subUrusan").toString();
		
                
	        if(actionPajakan.equalsIgnoreCase("papar")){        	
	        	
	        	if (selectedTab.equals("0")){
	        		logic.setMaklumatPemohonPajakan(idPermohonan);
	            	PemohonPajakanView(mode);
	        	
	        	} else if (selectedTab.equals("1")){

	        		if (selectedTabLower.equals("0")){
		        		//ulasan KJP
	        			
	        			Vector senaraiUlasanKJP = new Vector();
	        			
	        			logic.setListUlasanKJP(idPermohonan);
	        			senaraiUlasanKJP = logic.getSenaraiUlasanKJP();
		        		this.context.put("SenaraiUlasanKJP", senaraiUlasanKJP);
		
		        		UlasanKJPView(mode, idUlasanKJP);
	        			
	        		}else if(selectedTabLower.equals("1")){
		        		//ulasan JPPH
	        			Vector senaraiUlasanJPPH = new Vector();
		        		logic.setListUlasanJPPH(idPermohonan);
		        		senaraiUlasanJPPH = logic.getSenaraiUlasanJPPH();
		        		this.context.put("SenaraiUlasanJPPH", senaraiUlasanJPPH);
		        		
		        		UlasanJPPHView(mode, idUlasanTeknikal);
	        			
	        		}else{
	        			//ulasan SPHP
	        			
	        			Vector senaraiUlasanSPHP = new Vector();
		        		logic.setListUlasanSPHP(idPermohonan);
		        		senaraiUlasanSPHP = logic.getSenaraiUlasanSPHP();
		        		this.context.put("SenaraiUlasanSPHP", senaraiUlasanSPHP);
		        		
		        		UlasanSPHPView(mode, idUlasanSPHP);
	        			
	        		}
	        	
	        	} 
	        		
	        	//	else if (selectedTab.equals("2")){
//	        		//ulasan JPPH
//	        		Vector senaraiUlasanJPPH = new Vector();
//	        		logic.setListUlasanJPPH(idPermohonan);
//	        		senaraiUlasanJPPH = logic.getSenaraiUlasanJPPH();
//	        		this.context.put("SenaraiUlasanJPPH", senaraiUlasanJPPH);
//	        		
//	        		UlasanJPPHView(mode, idUlasanTeknikal);
//	        	
//	        	} 
	        	
//	        	 else if( selectedTab.equals("3")){
//	        		//ulasan SPHP
//	        		 /*
//		        		Vector senaraiDraf = new Vector();
//		        		logic.setListDraf(idPermohonan);
//		        		senaraiDraf = logic.getSenaraiDraf();
//		        		this.context.put("SenaraiDraf", senaraiDraf);
//		        		
//		        		DrafView(mode, idDraf);
//		        	 
//		        	 */
//		        	
//		       }
	        	
	        	else if( selectedTab.equals("2")){
	        		//draf memorandum jemaah menteri
	        		Vector senaraiDraf = new Vector();
	        		logic.setListDraf(idPermohonan);
	        		senaraiDraf = logic.getSenaraiDraf();
	        		this.context.put("SenaraiDraf", senaraiDraf);
	        		System.out.println("+++++++++++++++++++++++++++++++++");
	        		
	        		DrafView(mode, idDraf);
	        	
	        	} else {
	        		//memorandum jemaah menteri
	        		logic.setMaklumatMemorandumJemaahMenteri(idPermohonan);
	        		MemorandumJemaahMenteriView(mode);
	        	}
	        }
		}

        //SET DEFAULT PARAM
        this.context.put("actionPajakan", actionPajakan);
        this.context.put("selectedTab", selectedTab);
        this.context.put("selectedTabLower", selectedTabLower);
        this.context.put("mode", mode);

        //SET DEFAULT ID PARAM
        this.context.put("idFail", idFail);
        this.context.put("idStatus", idStatus);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("subUrusan", subUrusan);
        this.context.put("idUlasanTeknikal", idUlasanTeknikal);
        this.context.put("idUlasanNilai", idUlasanNilai);
        this.context.put("idDraf", idDraf);
        this.context.put("idUlasanKJP", idUlasanKJP);
        this.context.put("idUlasanSPHP", idUlasanSPHP);

        log.info(vm);
        return vm;
    }

    private void downloadDraf(String idPermohonan,HttpSession session) throws Exception {
		System.out.println("=======Download file==========" +idPermohonan);
		uploadFilesNew();
	}
    
   private void uploadFilesNew() throws Exception {
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    List items = upload.parseRequest(request);
	    Iterator itr = items.iterator();
	    while (itr.hasNext()) {
	      FileItem item = (FileItem)itr.next();
	      log.info("ContentType :" + item.getContentType());
	      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
	    	  saveData(item);
	      }
	    }
	  }
 private void saveData(FileItem item) throws Exception {
	 	HttpSession session = request.getSession();		
  		Db db = null;
		SimpleDateFormat sdfSource = new SimpleDateFormat("dd/mm/yyyy");
	    Date TT = sdfSource.parse(getParam("txdTarikhTerimaDraf"));
	    Date TH = sdfSource.parse(getParam("txdTarikhTerimaDraf"));
	    java.sql.Date TH2 = new java.sql.Date(TH.getTime());
	    java.sql.Date TT2 = new java.sql.Date(TT.getTime());

        try {
        	
        	long id_derafperjanjian = DB.getNextID("TBLHTPDERAFPERJANJIAN_SEQ");	        	
        	db = new Db();			      

        	Connection con = db.getConnection();
        	con.setAutoCommit(false);
        	SQLRenderer r = new SQLRenderer();
        	PreparedStatement ps = con.prepareStatement("insert into TBLHTPDERAFPERJANJIAN " +
        			"(id_derafperjanjian,id_permohonan,nama_Fail,mimetype,content_derafperjanjian,tarikh_hantarptp,tarikh_terimaptp,ulasan_seksyen,id_masuk,id_kemaskini,JENIS_DOKUMEN) " +
        			"values(?,?,?,?,?,?,?,?,?,?,?)");
        	ps.setLong(1, id_derafperjanjian);
        	ps.setNString(2, getParam("idPermohonan"));
        	ps.setString(3,item.getName());
        	ps.setString(4,item.getContentType());
        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
        	ps.setDate(6, TH2);
        	ps.setDate(7, TT2);	
        	ps.setString(8, getParam("txtKeteranganDraf"));	   
        	ps.setString(9,(String) session.getAttribute("_ekptg_user_id"));	        	      	
        	ps.setString(10,(String) session.getAttribute("_ekptg_user_id"));    
        	ps.setString(11, "M");	 
        	ps.executeUpdate();
            con.commit();  
        	
	    } finally {
		      if (db != null) db.close();
	    }
  }

	public void PemohonPajakanView(String mode) throws Exception{
    	
    	String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0){
			idDaerah = "99999";
		}
		Vector beanMaklumatPemohon = null;
		
    	try {
    		
    		if (mode.equalsIgnoreCase("view")){
    			
    			beanMaklumatPemohon = new Vector();
        		beanMaklumatPemohon = logic.getPemohonPajakan();
        		this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
        		Hashtable hashPermohonDB = (Hashtable) beanMaklumatPemohon.get(0);
        		
    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong((String)hashPermohonDB.get("idNegeri")), " disabled='disabled' class='disabled' "));
        		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri((String)hashPermohonDB.get("idNegeri"), "socDaerah", Long.parseLong((String)hashPermohonDB.get("idDaerah")), " disabled='disabled' class='disabled' "));
        		this.context.put("readOnly", "readOnly");
        		this.context.put("classDis", "disabled");
    		}
    		//mode = update
    		else if(mode.equalsIgnoreCase("update")){
    			
    			Hashtable hashPermohon;
    			beanMaklumatPemohon = new Vector();
    			//MAKLUMAT PEMOHON
    			hashPermohon = new Hashtable();
    			hashPermohon.put("nama", getParam("txtNama") == null ? "" : getParam("txtNama"));
    			hashPermohon.put("alamat1", getParam("txtAlamat1") == null ? "" : getParam("txtAlamat1"));
    			hashPermohon.put("alamat2", getParam("txtAlamat2") == null ? "" : getParam("txtAlamat2"));
    			hashPermohon.put("alamat3", getParam("txtAlamat3") == null ? "" : getParam("txtAlamat3"));
    			hashPermohon.put("poskod", getParam("txtPoskod") == null ? "" : getParam("txtPoskod"));
    			hashPermohon.put("tel", getParam("txtNoTelefon") == null ? "" : getParam("txtNoTelefon"));
    			hashPermohon.put("fax", getParam("txtNoFax") == null ? "" : getParam("txtNoFax"));
    			beanMaklumatPemohon.addElement(hashPermohon);
    			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
    			
    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", "onchange='doChangeNegeri()'"));
        		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Long.parseLong(idDaerah), ""));
    			
    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    		}

    	} catch (Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void MemorandumJemaahMenteriView(String mode) throws Exception{
    	try{
    		
    		Vector beanMJM = null;
    		
    		if (mode.equalsIgnoreCase("view")){
    			
    			beanMJM = new Vector();
    			beanMJM = logic.getMemoJemaahMenteri();
        		this.context.put("BeanMJM", beanMJM);
    			
        		this.context.put("readOnly", "readOnly");
        		this.context.put("classDis", "disabled");
        		
    		}
    		//mode = update
    		else if(mode.equalsIgnoreCase("update")){
    			
    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    		}    		
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void UlasanKJPView(String mode, String idUlasanKJP) throws Exception{
    	try{

        		Vector beanUlasanKJP = null;
        		
        		if (mode.equalsIgnoreCase("newKJP")){
        			
        			Date today = new Date();
        			
        			beanUlasanKJP = new Vector();
        			Hashtable hashUlasanKJP = new Hashtable();
        			hashUlasanKJP.put("noRujukan", getParam("txtNoRujukanKJP") == null ? "" : getParam("txtNoRujukanKJP"));
        			hashUlasanKJP.put("tarikhHantar", getParam("txdTarikhHantarKJP") == null ? "" : getParam("txdTarikhHantarKJP"));
        			hashUlasanKJP.put("tarikhTerima", getParam("txdTarikhTerimaKJP") == null ? "" : getParam("txdTarikhTerimaKJP"));
        			hashUlasanKJP.put("ulasanKJP", getParam("txtUlasanKJP") == null ? "" : getParam("txtUlasanKJP"));
        			
        			beanUlasanKJP.addElement(hashUlasanKJP);
    				this.context.put("BeanUlasanKJP", beanUlasanKJP);
    				
    				this.context.put("readOnly", "");
            		this.context.put("classDis", "");
        			
        		} else if (mode.equalsIgnoreCase("viewKJP")){
        			
        			beanUlasanKJP = new Vector();
        			logic.setMaklumatUlasanKJP(idUlasanKJP);
        			beanUlasanKJP = logic.getUlasanKJP();
            		this.context.put("BeanUlasanKJP", beanUlasanKJP);
        			
            		this.context.put("readOnly", "readOnly");
            		this.context.put("classDis", "disabled");
            		
        		}
        		//mode = update
        		else if(mode.equalsIgnoreCase("updateKJP")){
        			
        			this.context.put("readOnly", "");
            		this.context.put("classDis", "");
        		}    		
    		
    		
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    }    
    
    public void UlasanJPPHView(String mode, String idUlasanTeknikal) throws Exception{
    	try{
    		
    		Vector beanUlasanJPPH = null;
    		
    		if (mode.equalsIgnoreCase("newJPPH")){
    			
    			beanUlasanJPPH = new Vector();
    			Hashtable hashUlasanJPPH = new Hashtable();
    			hashUlasanJPPH.put("noRujukan", getParam("txtNoRujJPPH") == null ? "" : getParam("txtNoRujJPPH"));
    			hashUlasanJPPH.put("tarikhHantar", getParam("txdTarikhHantarJPPH") == null ? "" : getParam("txdTarikhHantarJPPH"));
    			hashUlasanJPPH.put("tarikhTerima", getParam("txdTarikhTerimaJPPH") == null ? "" : getParam("txdTarikhTerimaJPPH"));
    			hashUlasanJPPH.put("tahunNilaian", getParam("txtTahunNilaian") == null ? "" : getParam("txtTahunNilaian"));
    			hashUlasanJPPH.put("nilaian", getParam("txtNilaiTanah") == null ? "" : getParam("txtNilaiTanah"));
    			hashUlasanJPPH.put("syor", getParam("txtSyorBayaran") == null ? "" : getParam("txtSyorBayaran"));
    			hashUlasanJPPH.put("ulasan", getParam("txtKeteranganJPPH") == null ? "" : getParam("txtKeteranganJPPH"));
    			
    			beanUlasanJPPH.addElement(hashUlasanJPPH);
				this.context.put("BeanUlasanJPPH", beanUlasanJPPH);
				
				this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    			
    		} else if (mode.equalsIgnoreCase("viewJPPH")){
    			
    			beanUlasanJPPH = new Vector();
    			logic.setMaklumatUlasanJPPH(idUlasanTeknikal);
    			beanUlasanJPPH = logic.getBeanMaklumatUlasanJPPH();
        		this.context.put("BeanUlasanJPPH", beanUlasanJPPH);
    			
        		this.context.put("readOnly", "readOnly");
        		this.context.put("classDis", "disabled");
        		
    		}
    		//mode = update
    		else if(mode.equalsIgnoreCase("updateJPPH")){
    			
    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    		}    		
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void UlasanSPHPView(String mode, String idUlasanSPHP) throws Exception{
    	try{
    		
    		Vector beanUlasanSPHP = null;
    		
    		if (mode.equalsIgnoreCase("newSPHP")){
    			
    			beanUlasanSPHP = new Vector();
    			Hashtable hashUlasanSPHP = new Hashtable();
    			hashUlasanSPHP.put("noRujukan", getParam("txtNoRujukanSPHP") == null ? "" : getParam("txtNoRujukanSPHP"));
    			hashUlasanSPHP.put("tarikhHantar", getParam("txdTarikhHantarSPHP") == null ? "" : getParam("txdTarikhHantarSPHP"));
    			hashUlasanSPHP.put("tarikhTerima", getParam("txdTarikhTerimaSPHP") == null ? "" : getParam("txdTarikhTerimaSPHP"));
    			hashUlasanSPHP.put("ulasan", getParam("txtUlasanSPHP") == null ? "" : getParam("txtUlasanSPHP"));
    			hashUlasanSPHP.put("status", getParam("socKeputusan") == null ? "" : getParam("socKeputusan"));
    			
    			beanUlasanSPHP.addElement(hashUlasanSPHP);
				this.context.put("BeanUlasanSPHP", beanUlasanSPHP);
				
				this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    			
    		} else if (mode.equalsIgnoreCase("viewSPHP")){
    			
    			beanUlasanSPHP = new Vector();
    			logic.setMaklumatUlasanSPHP(idUlasanSPHP);
    			beanUlasanSPHP = logic.getBeanMaklumatUlasanSPHP();
        		this.context.put("BeanUlasanSPHP", beanUlasanSPHP);
    			
        		this.context.put("readOnly", "readOnly");
        		this.context.put("classDis", "disabled");
        		
    		}
    		//mode = update
    		else if(mode.equalsIgnoreCase("updateSPHP")){
    			
    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    		}    		
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void DrafView(String mode, String idDraf) throws Exception{
    	try{
    		
    		Vector beanDraf = null;
    		
    		if (mode.equalsIgnoreCase("newDraf")){
    			
    			beanDraf = new Vector();
    			Hashtable hashDraf = new Hashtable();
    			hashDraf.put("idpermohonan", getParam("txtidPermohonan") == null ? "" : getParam("txtidPermohonan"));
    			hashDraf.put("tarikhHantar", getParam("txdTarikhHantarDraf") == null ? "" : getParam("txdTarikhHantarDraf"));
    			hashDraf.put("tarikhTerima", getParam("txdTarikhTerimaDraf") == null ? "" : getParam("txdTarikhTerimaDraf"));
    			hashDraf.put("ulasan", getParam("txtKeteranganDraf") == null ? "" : getParam("txtKeteranganDraf"));
    			
    			beanDraf.addElement(hashDraf);
				this.context.put("BeanDraf", beanDraf);
				
				this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    			
    		} else if (mode.equalsIgnoreCase("viewDraf")){
    			
    			beanDraf = new Vector();
    			logic.setMaklumatDraf(idDraf);
    			beanDraf = logic.getBeanMaklumatDraf();
    			this.context.put("BeanDraf", beanDraf);
    			
        		this.context.put("readOnly", "readOnly");
        		this.context.put("classDis", "disabled");
        		
    		}
    		//mode = update
    		else if(mode.equalsIgnoreCase("updateDraf")){
    			
    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    		}    		
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    private void savePemohon(String idPermohonan, HttpSession session) throws Exception {
    	Hashtable hash = new Hashtable();
		hash.put("nama", getParam("txtNama"));
		hash.put("alamat1", getParam("txtAlamat1"));
		hash.put("alamat2", getParam("txtAlamat2"));
		hash.put("alamat3", getParam("txtAlamat3"));
		hash.put("poskod", getParam("txtPoskod"));
		hash.put("idDaerah", getParam("socDaerah"));
		hash.put("idNegeri", getParam("socNegeri"));
		hash.put("noTel", getParam("txtNoTelefon"));
		hash.put("noFax", getParam("txtNoFax"));
		
		logic.updatePemohon(idPermohonan, hash, session);
	}
    
    private void saveMemo(String idPermohonan, HttpSession session) throws Exception {
    	Hashtable hash = new Hashtable();
		hash.put("tarikhTHPTP", getParam("txdTHPTP"));
		hash.put("tarikhTTPTP", getParam("txdTTPTP"));
		hash.put("tarikhTHTUP", getParam("txdTHTUP"));
		hash.put("tarikhTMJM", getParam("txdTMJM"));
		hash.put("tarikhTTK", getParam("txdTTK"));
		hash.put("noMemo", getParam("txtNoMemorandum"));
		hash.put("keputusan", getParam("txtKeputusan"));
		hash.put("tindakanLanjut", getParam("txtKeterangan"));
		hash.put("tarikhHantarPemohon", getParam("txdTMKPemohon"));
		
		logic.updateMemo(idPermohonan, hash, session);
	}
    
    private void saveUlasanKJP(String idPermohonan, HttpSession session) throws Exception {
    	Hashtable hash = new Hashtable();
    	hash.put("noRujukanKJP", getParam("txtNoRujukanKJP"));
		hash.put("tarikhHantar", getParam("txdTarikhHantarKJP"));
		hash.put("tarikhTerima", getParam("txdTarikhTerimaKJP"));
		hash.put("ulasan", getParam("txtUlasanKJP"));
		hash.put("status", getParam("socKeputusan"));
		
//		logic.updateUlasanKJP(idPermohonan, hash, session);
		logic.saveUlasanKJP(idPermohonan, hash, session);
	}
    
    private void saveUpdateUlasanKJP(String idUlasanKJP, HttpSession session) throws Exception {
    	Hashtable hash = new Hashtable();
    	hash.put("noRujukanKJP", getParam("txtNoRujukanKJP"));
		hash.put("tarikhHantar", getParam("txdTarikhHantarKJP"));
		hash.put("tarikhTerima", getParam("txdTarikhTerimaKJP"));
		hash.put("ulasan", getParam("txtUlasanKJP"));
		hash.put("status", getParam("socKeputusan"));
		
		logic.updateUlasanKJP(idUlasanKJP, hash, session);
	}
    
    private void saveJPPH(String idPermohonan, HttpSession session) throws Exception {
    	Hashtable hash = new Hashtable();
		hash.put("noRujukan", getParam("txtNoRujJPPH"));
		hash.put("tarikhHantar", getParam("txdTarikhHantarJPPH"));
		hash.put("tarikhTerima", getParam("txdTarikhTerimaJPPH"));
		hash.put("tahunNilaian", getParam("txtTahunNilaian"));
		hash.put("nilaian", getParam("txtNilaiTanah"));
		hash.put("syor", getParam("txtSyorBayaran"));
		hash.put("ulasan", getParam("txtKeteranganJPPH"));
		
		logic.saveJPPH(idPermohonan, hash, session);
	}
    
    private void saveUpdateJPPH(String idUlasanTeknikal, String idUlasanNilai, HttpSession session) throws Exception {
    	Hashtable hash = new Hashtable();
		hash.put("noRujukan", getParam("txtNoRujJPPH"));
		hash.put("tarikhHantar", getParam("txdTarikhHantarJPPH"));
		hash.put("tarikhTerima", getParam("txdTarikhTerimaJPPH"));
		hash.put("tahunNilaian", getParam("txtTahunNilaian"));
		hash.put("nilaian", getParam("txtNilaiTanah"));
		hash.put("syor", getParam("txtSyorBayaran"));
		hash.put("ulasan", getParam("txtKeteranganJPPH"));
		
		logic.saveUpdateJPPH(idUlasanTeknikal, idUlasanNilai, hash, session);
	}
    
    //
    private void saveSPHP(String idPermohonan, HttpSession session) throws Exception {
    	Hashtable hash = new Hashtable();
		hash.put("noRujukan", getParam("txtNoRujukanSPHP"));
		hash.put("tarikhHantar", getParam("txdTarikhHantarSPHP"));
		hash.put("tarikhTerima", getParam("txdTarikhTerimaSPHP"));
		hash.put("ulasan", getParam("txtUlasanSPHP"));
		hash.put("status", getParam("socKeputusan"));
		
		logic.saveSPHP(idPermohonan, hash, session);
	}
    
    private void saveUpdateSPHP(String idUlasanSPHP, HttpSession session) throws Exception {
    	Hashtable hash = new Hashtable();
		hash.put("noRujukan", getParam("txtNoRujukanSPHP"));
		hash.put("tarikhHantar", getParam("txdTarikhHantarSPHP"));
		hash.put("tarikhTerima", getParam("txdTarikhTerimaSPHP"));
		hash.put("ulasan", getParam("txtUlasanSPHP"));
		hash.put("status", getParam("socKeputusan"));
		
		logic.saveUpdateSPHP(idUlasanSPHP, hash, session);
	}
    
    //
    private void saveDraf(String idPermohonan, HttpSession session) throws Exception {
    	idPermohonan = getParam("idPermohonan");
    	String tarikhHantarDraf = getParam("txdTarikhHantarDraf");
    	String tarikhTerimaDraf = getParam("txdTarikhTerimaDraf");
    	String keteranganDraf = getParam("txtKeteranganDraf");
   
    	System.out.println("==============>>>" + tarikhHantarDraf + " " +tarikhTerimaDraf+ " "+keteranganDraf+" " +idPermohonan);
    	Hashtable hash = new Hashtable();
		hash.put("tarikhHantar", tarikhHantarDraf);
		hash.put("tarikhTerima", tarikhTerimaDraf);
		hash.put("ulasan", keteranganDraf);
		hash.put("id_permohonan", idPermohonan);
		
		
		
		log.info("start");
		
		log.info("saveDraf Hash : " + hash);
		
		FileItem item = uploadFiles();
		
		log.info("ContentType :" + item.getContentType());
		log.info("FieldName :" + item.getFieldName());
		log.info("Name :" + item.getName());
		log.info("String :" + item.getString());
		log.info("InputStream :" + item.getInputStream());
		log.info("OutputStream :" + item.getOutputStream());
	
		
		log.info("end");
		uploadFilesNew();
		
//		logic.saveDraf(idPermohonan, hash, session, item);
		logic.saveDraf(idPermohonan, hash, session);
	}
    
    private void saveUpdateDraf(String idDraf, HttpSession session) throws Exception {
    	Hashtable hash = new Hashtable();
    	hash.put("tarikhHantar", getParam("txdTarikhHantarDraf"));
		hash.put("tarikhTerima", getParam("txdTarikhTerimaDraf"));
		hash.put("ulasan", getParam("txtKeteranganDraf"));
		
//		FileItem item = uploadFiles();
		
//		logic.saveUpdateDraf(idDraf, hash, session, item);
		logic.saveUpdateDraf(idDraf, hash);
	}
    
    // UPLOAD FILE PERJANJIAN
	private FileItem uploadFiles() throws Exception {
		
		FileItem item = null;
		FileItem itemSave = null;
		
		try{
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			
			List items = upload.parseRequest(this.request);
			Iterator itr = items.iterator();
			
			while (itr.hasNext()) {
				item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null)
						&& (!("".equals(item.getName())))) {
					itemSave = item;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return itemSave;
		
		
	}
	
}
