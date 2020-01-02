/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ekptg.view.htp.pajakan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
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
import ekptg.model.entities.Tblhtpjemaahmenteri;
import ekptg.model.htp.FrmPajakanHeaderData;
import ekptg.model.htp.FrmPajakanMemorandumJemaahMenteriData;
import ekptg.model.htp.HTPStatusBean;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHTPStatus;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.PajakanUlasan;
import ekptg.model.htp.entity.Pemohon;
import ekptg.model.htp.online.IOnline;
import ekptg.model.htp.online.OnlineBean;
import ekptg.model.htp.pajakan.IPajakanMJM;
import ekptg.model.htp.pajakan.PajakanMJMBean;
import ekptg.model.integrasi.FrmJPPHModelNilaianPajakan;

public class FrmPajakanMJMView extends AjaxBasedModule {

	FrmPajakanHeaderData logicHeader = new FrmPajakanHeaderData();
    FrmPajakanMemorandumJemaahMenteriData logic = new FrmPajakanMemorandumJemaahMenteriData();
    Hashtable hDraf =null;	
    private final String PATH="app/htp/pajakan/mjm/";
    private HtpPermohonan htpPermohonan = null;
	private IHtp iHTP = null;
	private IHTPStatus iStatus = null;
	private IOnline iOnline = null;
 	private IPajakanMJM iPMJM = null;
    private Pemohon pemohon = null;
	private static final long serialVersionUID = 1L;
    private static Logger myLog = Logger.getLogger(ekptg.view.htp.pajakan.FrmPajakanMJMView.class);
    private String userID = "";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String idFail_ = "";
    String idPermohonan_ = "";
    String idSubUrusan = "";	
    String tarikhSemasa = "";
    Vector vDraf = null;
    String vm = "";

    @Override
    public String doTemplate2() throws Exception {
    		
    		HttpSession session = this.request.getSession();
	    	//log.info("request : " + request.getContentType());   	
	    	String hitButton = getParam("hitButton");
	        String selectedTab = getParam("selectedTab");
	        String selectedTabLower = getParam("selectedTabLower");
	        String idPermohonan = getParam("idPermohonan");
	        String tarikhHantarDraf = getParam("txdTarikhHantarDraf");
	     	String tarikhTerimaDraf = getParam("txdTarikhTerimaDraf");
	     	String keteranganDraf = getParam("txtKeteranganDraf");
	       	Boolean postDB = true;
	     	String doPost = session.getAttribute("doPost").toString();
//     	 myLog.info("doPost="+doPost);
	     	if (doPost.equals("true")) {
	     		postDB = true;
	     	}
//        if (doPost) {
//            postDB = true;
//        }
	     	 //GET DEFAULT PARAM
	     	 String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	     	 String actionPajakan = getParam("actionPajakan"); //our main submit
	         userID = (String)session.getAttribute("_ekptg_user_id");
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
	     	 myLog.info("action="+action+",actionPajakan="+actionPajakan+",mode= " + mode+",hitButton= " + hitButton+
	     			 ",selectedTab="+selectedTab +",selectedTabLower="+selectedTabLower);
	     	 
	     	//GET ID PARAM
		    String idFailSession = "";
	     	try{	 
		        
		        if (session.getAttribute("idFail") != null){
		        	
		        	idFailSession = String.valueOf(session.getAttribute("idFail"));
		        	if(!getParam("idFail").equals("")){
		        		idFailSession = getParam("idFail");
		        	}
		    		htpPermohonan = getIHTP().findPermohonan(idFailSession,idPermohonan,"");
		    		if(htpPermohonan == null){
						throw new Exception(getIHTP().getErrorHTML("[HTP PAJAKAN] SILA KEMASKINI FAIL TERLEBIH DAHULU"));
		    		}
		    		
		        	idPermohonan = String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan());
		        	
		        }
	     	}catch(Exception ex){
				myLog.info("catch exception");
				throw new Exception(getIHTP().getErrorHTML("[HTP PAJAKAN] SILA PILIH FAIL TERLEBIH DAHULU PADA MENU SENARAI FAIL"));
				//getIHTP().getErrorHTML(ex.toString());		
			}
	        
	        
	        //myLog.info("idPermohonan="+idPermohonan);
	        String idFail = getParam("idFail").equals("")?idFailSession:getParam("idFail");
     		session.setAttribute("idFail", idFail);
	        String idStatus = getParam("idStatus");       
	        String subUrusan = getParam("subUrusan");
	        String idUlasanTeknikal = getParam("idUlasanTeknikal");
	        String idUlasanNilai = getParam("idUlasanNilai");
	        String idUlasanKJP = getParam("idUlasanKJP");
	        String idDraf = getParam("idDraf");
	        String idUlasanSPHP = getParam("idUlasanSPHP");       
        
	        //VECTOR
	        Vector list = null;
	        Vector beanHeader = null;
	        vm  = PATH+"index.jsp";
	        idFail_ = idFail;
	        idPermohonan_ = idPermohonan;
			idSubUrusan = subUrusan;
	        //HITBUTTON
	        if (postDB) {
	        	if ("savePemohon".equals(hitButton)){    			
	    			savePemohon(idPermohonan, session);
	    		
	        	}else if (hitButton.equals("saveMemo")){    			
	        		saveMemo(idPermohonan, session);
	        		myLog.info(idFail_);
	        		uploadFiles(session,idFail_);
	
	        	}else if ("hapusmemo".equals(hitButton)){    			
	    			logic.hapusMJM(idPermohonan);
	    			
	        	}else if ("hapuslampiran".equals(hitButton)){    			
					String idDokumen = getParam("iddokumen");
					String idLampiran = getParam("idLampiran");
				    getIOnline().hapusLampiran(idDokumen,idLampiran);
	    			    			
	        	}else if ("saveUlasanKJP".equals(hitButton)){    			
	        		saveUlasanKJP(idPermohonan, session);
	    		
	        	}else if ("saveUpdateKJP".equals(hitButton)){    			
	        		saveUpdateUlasanKJP(idUlasanKJP, session);
	    		
	        	}else if ("hapusKJP".equals(hitButton)){    			
	    			logic.hapusKJP(idUlasanKJP);
	    		
	        	}else if ("saveJPPH".equals(hitButton)){    			
	    			saveJPPH(idPermohonan, session);
	    		
	        	}else if ("saveUpdateJPPH".equals(hitButton)){    			
	    			saveUpdateJPPH(idUlasanTeknikal, idUlasanNilai, session);
	    		
	        	}else if ("hapusJPPH".equals(hitButton)){    			
	    			logic.hapusJPPH(idUlasanTeknikal, idUlasanNilai);
	    		
	        	}else if ("saveUlasanSPHP".equals(hitButton)){    			
	    			saveSPHP(idPermohonan, session);
	    		
	        	}else if ("saveUpdateSPHP".equals(hitButton)){    			
	    			saveUpdateSPHP(idUlasanSPHP, session);
	    		
	        	}else if ("hapusSPHP".equals(hitButton)){    			
	    			logic.hapusSPHP(idUlasanSPHP);
	    		
	        	}else if ("saveDraf".equals(hitButton)){ 
	//    			myLog.debug("** save draft **");
	    			//saveDraf(idPermohonan, session);
	    			//downloadDraf(idPermohonan , session);
	    			//06/09/2010
	    			String idDerafPerjanjian = simpanDraf(idPermohonan, session);
	    			//myLog.debug("** save draft **idDerafPerjanjian="+idDerafPerjanjian);
	    			simpanLampiran(idDerafPerjanjian);
	    			
	    		}else if ("downloadDraf".equals(hitButton)){ 
	//    			myLog.debug("** download draft **");
	    			downloadDraf(idPermohonan , session);
	    		
	    		}else if ("saveUpdateDraf".equals(hitButton)){    
	//    			myLog.debug("** update draft **idDerafPerjanjian=");
	    			saveUpdateDraf(idDraf, session);
	    			simpanLampiran(idDraf);
		
	    		}else if ("hapusDraf".equals(hitButton)){    			
	    			logic.hapusDraf(idDraf);
	    		
	    		}else if ("seterusnya".equals(hitButton)){    			
	    			logic.seterusnya(idFail, idPermohonan, subUrusan, session);
	    		
	    		}
	    		
	    	}
	        
	        //log.debug("** here **"+hitButton);       
	        beanHeader = new Vector();
	        logicHeader.setMaklumatPermohonan(idFail);
	        beanHeader = logicHeader.getBeanMaklumatPermohonan();
			this.context.put("BeanHeader", beanHeader);

			if (beanHeader.size() != 0){
				Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
				idFail = hashHeader.get("idFail").toString();
				idPermohonan = hashHeader.get("idPermohonan").toString();
				idStatus = hashHeader.get("idStatus").toString();
				subUrusan = hashHeader.get("subUrusan").toString();		
				
				/*CHecking jika telah disahkan pengarah atau belum (bagi fail baru didaftar selepas penambahbaikan). syaz. 01122014 */
				String strval = "display";
				String flagMohonFail = hashHeader.get("flagMohonFail").toString();
				if((flagMohonFail!="") && !flagMohonFail.equalsIgnoreCase("S") ){
					strval = "hide";
					throw new Exception(getIHTP().getErrorHTML("PERMOHONAN BELUM DISAHKAN"));
				}
				this.context.put("valFlagMohonFail", strval);
				
				
				
				//TBLHTPULASANKJP
				String showpopup = "false";
				if(hashHeader.get("tarikhSuratPemohon").toString()!=null && hashHeader.get("tarikhSuratPemohon").toString()!=""){
					//System.out.println("bil : "+hashHeader.get("bil_ulasankjp"));
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(hashHeader.get("tarikhSuratPemohon").toString())); // Now use today date.
					c.add(Calendar.DATE, 21); 
					Date sysdate = sdf.parse(sdf.format(new Date()));
					Date tarikhMohon = sdf.parse(sdf.format(c.getTime()));
					//System.out.println("tarikhMohon : "+tarikhMohon);
					//System.out.println("sysdate : "+sysdate);
					//.... check kalau ulasan dah masuk xyah popup (for temporary)...
					if((tarikhMohon.before(sysdate) || tarikhMohon.equals(sysdate)) && Integer.parseInt(hashHeader.get("bil_ulasankjp").toString())==0){
		        		//System.out.println("21 hari lepas tarikhMohon is before/equal sysdate");
		        		showpopup = "true";
		        	}else{
		        		//System.out.println("21 hari lepas tarikhMohon is after sysdate");
		        		showpopup = "false";
		        	}
				}
				
				this.context.put("showpopup",showpopup);
				
				
		        if(actionPajakan.equalsIgnoreCase("papar") || actionPajakan.equalsIgnoreCase("paparan")){        	
		        	System.out.println("papar...");
		        	if (selectedTab.equals("0")){
		        		Vector vec = logic.getMaklumatPemohonPajakan(idPermohonan);
		            	PemohonPajakanView(mode,vec);
		        	
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
			        		this.context.put("SenaraiUlasanJPPHOnline", FrmJPPHModelNilaianPajakan.listUlasanOnline(idPermohonan));
		        			
		        		}else{
		        			//ulasan SPHP	        			
		        			Vector senaraiUlasanSPHP = new Vector();
			        		logic.setListUlasanSPHP(idPermohonan);
			        		senaraiUlasanSPHP = logic.getSenaraiUlasanSPHP();
			        		this.context.put("SenaraiUlasanSPHP", senaraiUlasanSPHP);		        		
			        		UlasanSPHPView(mode, idUlasanSPHP);
		        			
		        		}
		        	
		        	} else if( selectedTab.equals("2")){
		        		//draf memorandum jemaah menteri
		        		Vector senaraiDraf = new Vector();
		        		logic.setListDraf(idPermohonan);
		        		senaraiDraf = logic.getSenaraiDraf();
		        		this.context.put("SenaraiDraf", senaraiDraf);
		        		DrafView(mode, idDraf);
		        	
		        	} else {
		        		System.out.println("default");
		        		myLog.info(action+",MJM:mode="+mode);
		        		this.context.remove("BeanMJM");
		            	tarikhSemasa = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");		
		        		//memorandum jemaah menteri
	//	        		logic.setMaklumatMemorandumJemaahMenteri(idPermohonan);
	//	        		MemorandumJemaahMenteriView(mode);
		        		Vector vec = logic.getMaklumatMemorandumJemaahMenteri(idPermohonan);
		        		Tblhtpjemaahmenteri mjm = (Tblhtpjemaahmenteri)getIPMJM().getMaklumatMemorandumJemaahMenteri(idPermohonan);
		        		//myLog.info(vec.size());
		        		//MemorandumJemaahMenteriView(mode,vec);	  
		        		MemorandumJemaahMenteriView(mode,mjm);			        		
		    			this.context.put("num_files",1);	    			
		        		//myLog.info(getParamAsInteger("jumlahlampiran"));
		    			if (getParamAsInteger("jumlahlampiran")>1) {
			        		//myLog.info("MJM:mode="+mode+","+action);
		    				int j = getParamAsInteger("jumlahlampiran");
		    				this.context.put("num_files", j);				
		    			
		    			}
		        		
		        	}
		        }
			}

	        //SET DEFAULT PARAM
	        this.context.put("tarikhSemasa", tarikhSemasa);
	        this.context.put("actionPajakan", actionPajakan);
	        this.context.put("selectedTab", selectedTab);
	        this.context.put("selectedTabLower", selectedTabLower);
	        this.context.put("mode", mode);
			//myLog.info("mode:"+mode);
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
			this.context.put("page", "3");
			getPermohonanInfo();
			if(getParam("idPermohonan").equals("")){
				getPermohonanInfo(idPermohonan);
			}
			//myLog.info(vm+":idPermohonan="+idPermohonan);
		
        return vm;
        
	}
	
	private void getPermohonanInfo()throws Exception{
		String idPermohonan = getParam("idPermohonan");
		String idHtpPermohonan = getParam("idHtpPermohonan");
		htpPermohonan = getIHTP().findPermohonan(idPermohonan,idHtpPermohonan);
		context.put("htpPermohonan", htpPermohonan);
		
	}

	private void getPermohonanInfo(String idPermohonan )throws Exception{
		String idHtpPermohonan = getParam("idHtpPermohonan");
		htpPermohonan = getIHTP().findPermohonan(idPermohonan,idHtpPermohonan);
		context.put("htpPermohonan", htpPermohonan);
		
	}

    private void downloadDraf(String idPermohonan,HttpSession session) throws Exception {
		//System.out.println("=======Download file==========" +idPermohonan);
		uploadFilesNew();
		
	}
    
   private void uploadFilesNew() throws Exception {
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    List items = upload.parseRequest(request);
	    Iterator itr = items.iterator();
	    while (itr.hasNext()) {
	      FileItem item = (FileItem)itr.next();
	      //log.info("ContentType :" + item.getContentType());
	      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
	    	  saveData(item);
	      }
	    }
	  }
   
		public void simpanLampiran(String idPerjanjian) throws Exception {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
		    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
			    List items = upload.parseRequest(request);
			    Iterator itr = items.iterator();
			    while (itr.hasNext()) {
			    	FileItem item = (FileItem)itr.next();
				    if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
//				    	myLog.info("simpanLampiran:ready");
				    	simpanLampiranDraf(idPerjanjian,item);
//				    	myLog.info("simpanLampiran:success");
				    }
			    }
			}
		}
	
		private void simpanLampiranDraf(String idPerjanjian,FileItem item) throws Exception {		
			HttpSession session = request.getSession();		
			String sql = null;
			Db db = null;
			PreparedStatement ps = null;
//			myLog.info("simpanLampiranDraf:sql=1");
			try {       	
	        	db = new Db();			      
	        	Connection con = db.getConnection();
				Statement stmt = db.getStatement();
	        	con.setAutoCommit(false);
				if(logic.getLampiran(idPerjanjian)){
//					myLog.info("simpanLampiranDraf:UPDATE");
		        	ps = con.prepareStatement("UPDATE TBLHTPDERAFPERJANJIANATTACH " +
		        			"(SET NAMA_FAIL,MIMETYPE," +
		        			"CONTENT_DERAFPERJANJIAN " +
		        			",ID_KEMASKINI,TARIKH_KEMASKINI) " +
		        			" values(?,?,?,?,sysdate)");
		        	ps.setString(1,item.getName());
		        	ps.setString(2,item.getContentType());
		        	ps.setBinaryStream(3,item.getInputStream(),(int)item.getSize());
		        	ps.setString(4, (String) session.getAttribute("_ekptg_user_id"));
				
				}else{
//					myLog.info("simpanLampiranDraf:INSERT");
					long id_derafperjanjian = DB.getNextID("TBLHTPDERAFPERATTACH_SEQ");	        	
					ps = con.prepareStatement("Insert into TBLHTPDERAFPERJANJIANATTACH " +
		        			"(id_derafperjanjianattach,id_derafPerjanjian,NAMA_FAIL,MIMETYPE," +
		        			"CONTENT_DERAFPERJANJIAN " +
		        			",ID_MASUK,ID_KEMASKINI,TARIKH_MASUK,TARIKH_KEMASKINI) " +
		        			" values(?,?,?,?,?,?,?,sysdate,sysdate)");
		        	ps.setLong(1, id_derafperjanjian);
		        	ps.setString(2, idPerjanjian);
		        	ps.setString(3,item.getName());
		        	ps.setString(4,item.getContentType());
		        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
		        	ps.setString(6, (String) session.getAttribute("_ekptg_user_id"));
		        	ps.setString(7, (String) session.getAttribute("_ekptg_user_id"));	
				}
	          	
	        	ps.executeUpdate();
	            con.commit();  
	            
			 }catch (Exception e){    
	        	e.getStackTrace();
		    } finally {
			      if (db != null) db.close();
			      
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
        		if(beanMaklumatPemohon.size()!=0){
	        		Hashtable hashPermohonDB = (Hashtable) beanMaklumatPemohon.get(0);	        		
	    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong((String)hashPermohonDB.get("idNegeri")), " class='disabled' "));
	        		//this.context.put("selectDaerah", HTML.SelectDaerahByNegeri((String)hashPermohonDB.get("idNegeri"), "socDaerah", Long.parseLong((String)hashPermohonDB.get("idDaerah")), " disabled='disabled' class='disabled' "));
					this.context.put("selectDaerah",HTML.SelectBandarByNegeri(String.valueOf(hashPermohonDB.get("idNegeri")), "socDaerah",Long.parseLong(String.valueOf(hashPermohonDB.get("idBandar")))," class='disabled'"));

	        		this.context.put("readOnly", "readOnly");
	        		this.context.put("classDis", "disabled");
	        		
        		}
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
    			hashPermohon.put("noPemohon", getParam("txtnorujukan") == null ? "" : getParam("txtnorujukan"));
    			hashPermohon.put("emel", getParam("txtemail") == null ? "" : getParam("txtemail"));
    			beanMaklumatPemohon.addElement(hashPermohon);
    			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
//    			myLog.info("N="+getParam("socNegeri"));
//    			myLog.info("D="+getParam("socDaerah"));
    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", "onchange='doChangeNegeri()'"));
        		//this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Long.parseLong(idDaerah), ""));
				this.context.put("selectDaerah",HTML.SelectBandarByNegeri(idNegeri, "socDaerah",Long.parseLong(idDaerah),""));
   			
    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    		}

    	} catch (Exception e){
    		e.printStackTrace();
    	}
    	
    }
	
	public void PemohonPajakanView(String mode,Vector beanMaklumatPemohon) throws Exception{   	
    	String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0){
			idDaerah = "99999";
		}
    	try {    		
    		if (mode.equalsIgnoreCase("view")){
    			//beanMaklumatPemohon = new Vector();
        		//beanMaklumatPemohon = logic.getPemohonPajakan();
        		this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
        		if(beanMaklumatPemohon.size()!=0){
	        		Hashtable hashPermohonDB = (Hashtable) beanMaklumatPemohon.get(0);	        		
	    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong((String)hashPermohonDB.get("idNegeri")), " class='disabled' "));
	        		//this.context.put("selectDaerah", HTML.SelectDaerahByNegeri((String)hashPermohonDB.get("idNegeri"), "socDaerah", Long.parseLong((String)hashPermohonDB.get("idDaerah")), " disabled='disabled' class='disabled' "));
					this.context.put("selectDaerah",HTML.SelectBandarByNegeri(String.valueOf(hashPermohonDB.get("idNegeri")), "socDaerah",Long.parseLong(String.valueOf(hashPermohonDB.get("idBandar")))," class='disabled'"));
	        		this.context.put("readOnly", "readOnly");
	        		this.context.put("classDis", "disabled");
	        		
        		}else{
        			getPemohonValues();
        			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), " onchange='doChangeNegeri()' "));
					this.context.put("selectDaerah",HTML.SelectBandarByNegeri(String.valueOf(idNegeri), "socDaerah",Long.parseLong(idDaerah)," "));
  			 			
        		}
        		
    		}else if(mode.equalsIgnoreCase("update")){	//mode = update
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
    			hashPermohon.put("noPemohon", getParam("txtnorujukan") == null ? "" : getParam("txtnorujukan"));
    			hashPermohon.put("emel", getParam("txtemail") == null ? "" : getParam("txtemail"));
    			beanMaklumatPemohon.addElement(hashPermohon);
    			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
//    			myLog.info("N="+getParam("socNegeri"));
//    			myLog.info("D="+getParam("socDaerah"));
    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", "onchange='doChangeNegeri()'"));
        		//this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Long.parseLong(idDaerah), ""));
				this.context.put("selectDaerah",HTML.SelectBandarByNegeri(idNegeri, "socDaerah",Long.parseLong(idDaerah),""));   			
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
//    			myLog.info(beanMJM);
        		this.context.put("BeanMJM", beanMJM);  			
        		this.context.put("readOnly", "readOnly");
        		this.context.put("classDis", "disabled");
        		
    		}else if(mode.equalsIgnoreCase("update")){	//mode = update   			
    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");
        		
    		}    		
    	} catch(Exception e){
    		e.printStackTrace();
    		
    	}
    	
    }
    
    public void MemorandumJemaahMenteriView(String mode,Tblhtpjemaahmenteri beanMJM) throws Exception{
        //public void MemorandumJemaahMenteriView(String mode,Vector beanMJM) throws Exception{
    	try{   		
    		String tarikhHantarPTP = "";
    		String tarikhTerimaPTP ="";
    		String tarikhHantarKSU ="";
    		String tarikhMesyuaratMJM = "";
    		String tarikhTerimaKeputusan = "";
    		String keputusan = "";
    		String noMemorandum = "-";
    		String tindakanLanjut = "-";
    		String tarikhMaklumanKeputusan = "";
    		Vector senaraiLampiran = null;
    		if (mode.equalsIgnoreCase("view")){ 
//    			myLog.info("beanMJM="+beanMJM);
//    			myLog.info("No. Memo"+beanMJM.getNoMemorandum());
    			//myLog.info(beanMJM);
        		//this.context.put("BeanMJM", beanMJM);
    			//tarikhHantarPTP = tarikhSemasa;
    			tarikhMesyuaratMJM = tarikhSemasa;
    			tarikhTerimaKeputusan = tarikhSemasa;
    			tarikhSemasa = "";
         		this.context.put("readOnly", "");
        		this.context.put("classDis", "");   			
        		if(beanMJM.getNoMemorandum()!=null){
        			tarikhHantarPTP = beanMJM.getTarikhHantarDasarFormat();
        			tarikhTerimaPTP = beanMJM.getTarikhTerimaFormat();
        			tarikhHantarKSU = beanMJM.getTarikhHantarKsuFormat();
    				//myLog.info(beanMJM.getTarikhMsyrtJemaahFormat());
        			tarikhMesyuaratMJM = beanMJM.getTarikhMesyuaratJemaahFormat();
    				//myLog.info(beanMJM.getTarikhMsyrtJemaahFormat());
    				tarikhTerimaKeputusan = beanMJM.getTarikhKeputusanFormat();
    				keputusan = beanMJM.getStatusKeputusan();
    				noMemorandum = beanMJM.getNoMemorandum();
    				tindakanLanjut = beanMJM.getTindakanLanjut();
    				tarikhMaklumanKeputusan = beanMJM.getTarikhHantarPemohonFormat();
    				this.context.put("readOnly", "readOnly");
            		this.context.put("classDis", "disabled");
   				
    			}
    			Vector dokumens = getIOnline().getLampiranByPermohonan(idPermohonan_);
    			context.put("senaraidokumen", dokumens);

      		
    		}else if(mode.equalsIgnoreCase("update")){	//mode = update   	
           		if(beanMJM.getNoMemorandum()!=null){
         			tarikhHantarPTP = beanMJM.getTarikhHantarDasarFormat();
        			tarikhTerimaPTP = beanMJM.getTarikhTerimaFormat();
        			tarikhHantarKSU = beanMJM.getTarikhHantarKsuFormat();
        			tarikhMesyuaratMJM = beanMJM.getTarikhMesyuaratJemaahFormat();
    				tarikhTerimaKeputusan = beanMJM.getTarikhKeputusanFormat();
    				keputusan = beanMJM.getStatusKeputusan();
    				noMemorandum = beanMJM.getNoMemorandum();
    				tindakanLanjut = beanMJM.getTindakanLanjut();
    				tarikhMaklumanKeputusan = beanMJM.getTarikhHantarPemohonFormat();
    			
           		}			
    			Vector dokumens = getIOnline().getLampiranByPermohonan(idPermohonan_);
    			context.put("senaraidokumen", dokumens);
    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");
        		
    		}    		
    		
    		//this.context.put("tarikhSemasa", tarikhSemasa);  			
    		this.context.put("BeanMJM", beanMJM);  			
    		this.context.put("tarikhHantarPTP", tarikhHantarPTP); 
    		this.context.put("tarikhTerimaPTP", tarikhTerimaPTP); 
    		this.context.put("tarikhHantarKSU", tarikhHantarKSU);   		
    		this.context.put("tarikhMesyuaratMJM", tarikhMesyuaratMJM);  			
    		this.context.put("tarikhTerimaKeputusan", tarikhTerimaKeputusan);  			
    		this.context.put("keputusan", keputusan);  			
    		this.context.put("noMemorandum", noMemorandum);  			
    		this.context.put("tindakanLanjut", tindakanLanjut);  	    		
    		this.context.put("tarikhMaklumanKeputusan",tarikhMaklumanKeputusan);
    		
    	} catch(Exception e){
    		e.printStackTrace();
    		
    	}
    	
    }
    
    public void UlasanKJPView(String mode, String idUlasanKJP) throws Exception{
    	PajakanUlasan ulasan = null;
    	try{
    		Vector beanUlasanKJP = null;
       		if (mode.equalsIgnoreCase("newKJP")){
       			Date today = new Date();
//      			beanUlasanKJP = new Vector();
//        		Hashtable hashUlasanKJP = new Hashtable();
//        		hashUlasanKJP.put("noRujukan", getParam("txtNoRujukanKJP") == null ? "" : getParam("txtNoRujukanKJP"));
//        		myLog.info("newKJP:Tarikh Hantar="+sdf.format(today));
//        		hashUlasanKJP.put("tarikhHantar", getParam("txdTarikhHantarKJP") == null ? String.valueOf(sdf.format(today)) : getParam("txdTarikhHantarKJP"));
//        		hashUlasanKJP.put("tarikhTerima", getParam("txdTarikhTerimaKJP") == null ? "" : getParam("txdTarikhTerimaKJP"));
//        		hashUlasanKJP.put("ulasanKJP", getParam("txtUlasanKJP") == null ? "" : getParam("txtUlasanKJP"));
//        		beanUlasanKJP.addElement(hashUlasanKJP);
//    			this.context.put("BeanUlasanKJP", beanUlasanKJP);
    			ulasan = new PajakanUlasan();
    			ulasan.setNo(getParam("txtNoRujukanKJP") == null ? "" : getParam("txtNoRujukanKJP"));
    			ulasan.setTarikhHantarTxt(getParam("txdTarikhHantarKJP").equals("")? sdf.format(today) : getParam("txdTarikhHantarKJP"));
    			ulasan.setTarikhTerima(getParam("txdTarikhTerimaKJP") == null ? "" : getParam("txdTarikhTerimaKJP"));
    			ulasan.setKeputusan(getParam("socKeputusan") == null ? "" : getParam("socKeputusan"));
    			ulasan.setUlasan(getParam("txtUlasanKJP") == null ? "" : getParam("txtUlasanKJP"));   			
    			this.context.put("readOnly", "");
            	this.context.put("classDis", "");
        			
       		} else if (mode.equalsIgnoreCase("viewKJP")){       			
//       			beanUlasanKJP = new Vector();
//        		logic.setMaklumatUlasanKJP(idUlasanKJP);
//        		beanUlasanKJP = logic.getUlasanKJP();
//            	this.context.put("BeanUlasanKJP", beanUlasanKJP);
       			ulasan = getIPMJM().getMaklumatUlasan(idUlasanKJP);
            	this.context.put("readOnly", "readOnly");
            	this.context.put("classDis", "disabled");
            		
            //mode = update
        	}else if(mode.equalsIgnoreCase("updateKJP")){       			
       			ulasan = getIPMJM().getMaklumatUlasan(idUlasanKJP);
       			this.context.put("readOnly", "");
            	this.context.put("classDis", "");
            		
        	}    		
    		this.context.put("ulasan", ulasan);
    		
    		
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
    			hashUlasanJPPH.put("tarikhHantar", getParam("txdTarikhHantarJPPH") == null||getParam("txdTarikhHantarJPPH").equals("") ?sdf.format(new Date()) : getParam("txdTarikhHantarJPPH"));
    			hashUlasanJPPH.put("tarikhTerima", getParam("txdTarikhTerimaJPPH") == null ? "" : getParam("txdTarikhTerimaJPPH"));
    			hashUlasanJPPH.put("tahunNilaian", getParam("txtTahunNilaian") == null ? "" : getParam("txtTahunNilaian"));
    			hashUlasanJPPH.put("nilaian", getParam("txtNilaiTanah") == null||getParam("txtNilaiTanah").equals("")  ? "0.00" : getParam("txtNilaiTanah"));
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
    			hashUlasanSPHP.put("tarikhHantar", getParam("txdTarikhHantarSPHP") == null ||getParam("txdTarikhHantarSPHP").equals("") ?sdf.format(new Date()) : getParam("txdTarikhHantarSPHP"));
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
        		
    		//mode = update
    		}else if(mode.equalsIgnoreCase("updateSPHP")){   			
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
        		
    			}else if(mode.equalsIgnoreCase("updateDraf")){//mode = update
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
		//Kemaskini pada 24/12/2010-mrosli 
		hash.put("idBandar", getParam("socDaerah"));
		hash.put("noRujukan", getParam("txtnorujukan"));
		hash.put("email", getParam("txtemail"));
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
		hash.put("idFail", idFail_);	
		hash.put("idSubUrusan", idSubUrusan);	
//		myLog.info(getParam("txdTHPTP"));
//		myLog.info(getParam("txdTTPTP"));
//		myLog.info(getParam("txdTHTUP"));
//		myLog.info(getParam("txdTMJM"));
//		myLog.info(getParam("txdTTK"));
//		myLog.info(getParam("txtNoMemorandum"));
//		myLog.info(getParam("txtKeputusan"));
//		myLog.info(getParam("txtKeterangan"));
//		myLog.info(getParam("txdTMKPemohon"));		
//		myLog.info(idFail_);	
//		myLog.info(idSubUrusan);			
		logic.updateMemo(idPermohonan, hash, session);
		kemaskiniStatusPermohonan(idPermohonan,idSubUrusan,getParam("txtKeputusan"));
		
	}
    
    private void saveUlasanKJP(String idPermohonan, HttpSession session) throws Exception {
    	Hashtable hash = new Hashtable();
    	hash.put("noRujukanKJP", getParam("txtNoRujukanKJP"));
		hash.put("tarikhHantar", getParam("txdTarikhHantarKJP"));
		hash.put("tarikhTerima", getParam("txdTarikhTerimaKJP"));
		hash.put("ulasan", getParam("txtUlasanKJP"));
		hash.put("status", getParam("socKeputusan"));	
		hash.put("idSubUrusan", idSubUrusan);	
		hash.put("idFail", idFail_);	
		logic.saveUlasanKJP(idPermohonan, hash, session);
		kemaskiniStatusPermohonan(idPermohonan,idSubUrusan,getParam("socKeputusan"));
		
	}
    
    private void saveUpdateUlasanKJP(String idUlasanKJP, HttpSession session) throws Exception {
    	Hashtable hash = new Hashtable();
    	hash.put("noRujukanKJP", getParam("txtNoRujukanKJP"));
		hash.put("tarikhHantar", getParam("txdTarikhHantarKJP"));
		hash.put("tarikhTerima", getParam("txdTarikhTerimaKJP"));
		hash.put("ulasan", getParam("txtUlasanKJP"));
		hash.put("status", getParam("socKeputusan"));	
		hash.put("idSubUrusan", idSubUrusan);	
		hash.put("idFail", idFail_);	
		hash.put("idPermohonan", idPermohonan_);	
		logic.updateUlasanKJP(idUlasanKJP, hash, session);
		kemaskiniStatusPermohonan(idPermohonan_,idSubUrusan,getParam("socKeputusan"));
		
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
    
    private void saveDraf(String idPermohonan, HttpSession session) throws Exception {
    	idPermohonan = getParam("idPermohonan");
    	String tarikhHantarDraf = getParam("txdTarikhHantarDraf");
    	String tarikhTerimaDraf = getParam("txdTarikhTerimaDraf");
    	String keteranganDraf = getParam("txtKeteranganDraf");
    	Hashtable hash = new Hashtable();
		hash.put("tarikhHantar", tarikhHantarDraf);
		hash.put("tarikhTerima", tarikhTerimaDraf);
		hash.put("ulasan", keteranganDraf);
		hash.put("id_permohonan", idPermohonan);				
		//log.info("start");	
		//log.info("saveDraf Hash : " + hash)
		FileItem item = uploadFiles();
		//log.info("ContentType :" + item.getContentType());
		//log.info("FieldName :" + item.getFieldName());
		//log.info("Name :" + item.getName());
		//log.info("String :" + item.getString());
		//log.info("InputStream :" + item.getInputStream());
		//log.info("OutputStream :" + item.getOutputStream());			
		//log.info("end");
		uploadFilesNew();		
//		logic.saveDraf(idPermohonan, hash, session, item);
		logic.saveDraf(idPermohonan, hash, session);
		
	}
    
    private String simpanDraf(String idPermohonan, HttpSession session) throws Exception {
    	idPermohonan = getParam("idPermohonan");
    	String tarikhHantarDraf = getParam("txdTarikhHantarDraf");
    	String tarikhTerimaDraf = getParam("txdTarikhTerimaDraf");
    	String keteranganDraf = getParam("txtKeteranganDraf");
    	Hashtable hash = new Hashtable();
		hash.put("tarikhHantar", tarikhHantarDraf);
		hash.put("tarikhTerima", tarikhTerimaDraf);
		hash.put("ulasan", keteranganDraf);
		hash.put("id_permohonan", idPermohonan);				
		//FileItem item = uploadFiles();
		return logic.simpanDraf(idPermohonan, hash, session);
		
	}   
    
    private void saveUpdateDraf(String idDraf, HttpSession session) throws Exception {
    	Hashtable hash = new Hashtable();
    	hash.put("tarikhHantar", getParam("txdTarikhHantarDraf"));
		hash.put("tarikhTerima", getParam("txdTarikhTerimaDraf"));
		hash.put("ulasan", getParam("txtKeteranganDraf"));
		hash.put("userId", userID);
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
	  
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}
	
	private IPajakanMJM getIPMJM(){
		if(iPMJM== null)
			iPMJM = new PajakanMJMBean();
		return iPMJM;
	}	
	
	private void getPemohonValues(){
		String nama = getParam("txtNama");
		String noRujukan = getParam("txtnorujukan");
		String alamat1 = getParam("txtAlamat1");
		String alamat2 = getParam("txtAlamat2");
		String alamat3 = getParam("txtAlamat3");
		String poskod = getParam("txtPoskod");
		String idNegeri = getParam("socNegeri");
		String idBandar = getParam("socDaerah");
		String tel = getParam("txtNoTelefon");
		String fax = getParam("txtNoFax");
		String emel = getParam("txtemail");		
		//permohonan = new Permohonan();
		//permohonan.setIdPermohonan(idPermohonan);
		pemohon = new Pemohon();
		//pemohon.setIdPemohon(idPenjual);
		//pemohon.setFlagPemilik(flag);
		pemohon.setNama(nama);//pemohon.getNama()
		pemohon.setNoPemohon(noRujukan);
		pemohon.setAlamat1(alamat1);
		pemohon.setAlamat2(alamat2);
		pemohon.setAlamat3(alamat3);
		//pemohon.setPermohonan(permohonan);
		pemohon.setPoskod(poskod);
		pemohon.setIdNegeri(idNegeri);
		pemohon.setIdDaerah(idBandar);
		pemohon.setTel(tel);
		pemohon.setFax(fax);
		pemohon.setEmel(emel);		
		context.put("pemohon", pemohon);
	
	}
	
	//UPLOAD FILE MJM
	private void uploadFiles(HttpSession session,String idFail) throws Exception {
		myLog.info("uploadFiles");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
	    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	    Enumeration allparam = request.getParameterNames();
		String name="";String value="";
		for (; allparam.hasMoreElements(); ) {
	        // Get the name of the request parameter
	        name = (String)allparam.nextElement();
	        // Get the value of the request parameter
	        value = request.getParameter(name);

		}
	    List items = upload.parseRequest(request);
	    Iterator itr = items.iterator();
	    while (itr.hasNext()) {
	    	FileItem item = (FileItem)itr.next();
		    if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    	String idDokumen = simpanDokumen(item.getName(),idFail);			
		    	saveData(idDokumen,item);
		    }
	    }
	 }
	
	 private String simpanDokumen(String namaFail,String idFail) throws Exception {			
		 Hashtable h = new Hashtable();  
		 h.put("flag_Dokumen","1");
		 h.put("tajuk_Dokumen",namaFail);
		 h.put("namaPengirim", "-");
		 h.put("id_Fail",idFail);
		 h.put("id_Masuk",userID);
		 return getIOnline().addMasuk(h);
			    
	 }

	 private void saveData(String idDokumen,FileItem item) throws Exception {
		 Db db = null;
		 try {
			 db = new Db();			 
			 long id_Lampiran = DB.getNextID("TBLPFDRUJLAMPIRAN_SEQ");
			 Connection con = db.getConnection();
			 con.setAutoCommit(false);
			 PreparedStatement ps = con.prepareStatement("insert into TBLPFDRUJLAMPIRAN " +
	        		"(id_Lampiran,id_Dokumen,nama_Fail,jenis_Mime,content,id_masuk,tarikh_masuk,id_kemaskini,tarikh_kemaskini) " +
	        			"values(?,?,?,?,?,"+userID+",sysdate,"+userID+",sysdate)");
			 ps.setLong(1, id_Lampiran);
			 ps.setString(2, idDokumen);
			 ps.setString(3,item.getName());
			 ps.setString(4,item.getContentType());
			 ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
			 ps.executeUpdate();
			 con.commit();
			 
		 } finally {
			 if (db != null) db.close();
			      
		 }
		 
	 }
	 
	 private void kemaskiniStatusPermohonan(String keputusan,String idPermohonan,String idSubUrusan)
	 	throws Exception {
		String langkah = "";
		if(keputusan.equals("S")|| keputusan.equals("L")){
			langkah = "7";
		}else if(keputusan.equals("TS") || keputusan.equals("TL")){
			langkah = "6";
		}
		if(!langkah.equals(""))
			getStatus().kemaskiniStatusPermohonan(idPermohonan,idSubUrusan,langkah,userID);
	 }
	 
	 private IOnline getIOnline(){
		 if(iOnline==null){
			 iOnline = new OnlineBean();
		 }
		return iOnline;
				
	 }
		 
	 private IHTPStatus getStatus(){
		 if(iStatus==null){
			 iStatus = new HTPStatusBean();
		 }
		return iStatus;
				
	 }
		
	
}
