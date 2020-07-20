/**
 * 
 */
package ekptg.view.htp.online;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Enumeration;
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
import ekptg.model.htp.FrmOnlinePajakanHeaderData;
//import ekptg.model.htp.FrmPajakanHeaderData;
import ekptg.model.htp.FrmOnlinePajakanSenaraiFailData;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.online.FrmOnlineMaklumatPajakanData;
import ekptg.model.htp.online.IOnline;
import ekptg.model.htp.online.OnlineBean;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.PembelianBean;
/**
 * 
 *
 */
public class FrmPajakanOnlineSenaraiFailView extends AjaxBasedModule {

	private final String PATH="app/htp/online/";
	private static final long serialVersionUID = 1L;
	
	FrmOnlinePajakanHeaderData logicHeader = new FrmOnlinePajakanHeaderData();
	FrmOnlinePajakanSenaraiFailData logic = new FrmOnlinePajakanSenaraiFailData();
	FrmOnlineMaklumatPajakanData logicMaklumat = new FrmOnlineMaklumatPajakanData();
	
	private static Logger log = Logger.getLogger(ekptg.view.htp.online.FrmPajakanOnlineSenaraiFailView.class);
	
	private PfdFail fail = null;
	private Permohonan permohonan = null;
	private HakmilikUrusan urusan = null;
	private HtpPermohonan htpPermohonan = null;
	private IOnline iOnline = null;
	private String idUser = null;

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  session.getAttribute("doPost").toString();
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    //GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = ""; 
        String actionPajakan = getParam("actionPajakan");
        String submit = getParam("command");   
        String mode = getParam("mode");
        String hitButton = getParam("hitButton");
        String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
        
        //GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idStatus = getParam("idStatus");
        String subUrusan = getParam("subUrusan");
        String idHakmilik = getParam("idHakmilik");
        String idHakmilikAgensi = getParam("idHakmilikAgensi");
        String idKementarianSession = getParam("idKementerian");
        String idDokumen = getParam("idDokumen");
        String kategori = getParam("kategori");
        idUser = (String) session.getAttribute("_ekptg_user_id");
        
        //VECTOR
        Vector beanHeader = null;
        Vector list = null;
        Vector beanMaklumatPermohonan = null;
        Vector senaraiHakmilik = null;
        Vector beanMaklumatPemohon = null;
        Vector beanMaklumatLampiran = null;
        Vector senaraiLampiran = null;
        Vector senaraiSemak = null; //TAB SENARAI SEMAK
        Vector beanMaklumatTanah = null;
        
        //GET DROPDOWN PARAM
        String selectedTab = (String) getParam("selectedTab");
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}
        String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0){
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0){
			idAgensi = "99999";
		}
		String idSuburusan = "7";
		
		String idStatusTanah = getParam("socStatusTanah");
		if (idStatusTanah == null || idStatusTanah.trim().length() == 0){
			idStatusTanah = "99999";
		}
		String idJenisFail = getParam("socJenisFail");
		if (idJenisFail == null || idJenisFail.trim().length() == 0){
			idJenisFail = "99999";
		}
		String idLuasKegunaan = getParam("socLuasKegunaan");
		if (idLuasKegunaan == null || idLuasKegunaan.trim().length() == 0){
			idLuasKegunaan = "99999";
		}
		
		this.context.put("errorPeganganHakmilik", "");
		this.context.put("onload", "");
		
		log.info("actionPajakan:"+actionPajakan);
		//HITBUTTON
		if (postDB){
    		if ("simpan".equals(hitButton)){
    			log.info("simpan");
    			idNegeri = getParam("idNegeriTanah");
         		idFail = logic.simpanOnline(idNegeri, idKementerian, idAgensi, idSuburusan, 
             			idStatusTanah, idJenisFail, getParam("txtNoFailKJP"), getParam("tarikhSuratKJP"), 
             			getParam("txtNoFailLain"), getParam("tarikhAgihan"), getParam("txtTajuk"), getParam("tarikhSuratPemohon"), 
             			idHakmilik, session);
         				/*.simpanOnline(idNegeri, getParam("idKementerianTanah"), getParam("idAgensiTanah"), idSuburusan, 
         			idStatusTanah, idJenisFail, getParam("txtNoFailKJP"), getParam("tarikhSuratKJP"), 
         			getParam("txtNoFailLain"), getParam("tarikhAgihan"), getParam("txtTajuk"), getParam("tarikhSuratPemohon"), 
         			idHakmilik, session);*/
         		session.setAttribute("idFail", idFail);
        	}
        	if ("simpanbyswasta".equals(hitButton)){
        		log.info("simpanbyswasta");
         		idFail = logic.simpan(idPermohonan,idNegeri, idKementerian, idAgensi, idSuburusan, idStatusTanah, idJenisFail, getParam("txtNoFailKJP"), 
         					getParam("tarikhSuratKJP"), getParam("txtNoFailLain"), getParam("tarikhAgihan"), getParam("txtTajuk"),getParam("tarikhSuratPemohon"), session);
         		session.setAttribute("idFail", idFail);
        	}
        	if (hitButton.equals("saveUpdateFail")){
        		saveUpdateFail(idFail,subUrusan,session);        			
        	}
        	if ("doSimpanKemaskiniMaklumatTnh".equals(hitButton)){
        		logicMaklumat.updateTanah(idPermohonan,idHakmilik,session);	
            }
        	if ("doHantar".equals(hitButton)){
        		if (logic.checkMaklumatPajakanLengkap(idPermohonan)){
    				this.context.put("onload", " \"alert('Masih terdapat maklumat penyewaan yang belum lengkap.')\"");	
				} else {
					logic.updatePengesahan(idFail,idPermohonan,session);
				}				
			}
        	if ("doHapus".equals(hitButton)){
				logic.hapusPermohonan(idFail);
			}
    	}
		
		if ("paparMaklumatPajakan".equals(actionPajakan)){
			//GO TO PAPAR PAJAKAN 
			vm = "app/htp/online/frmOnlineMaklumatPajakan.jsp";
			
			this.context.put("afterSave", "");
			this.context.put("completed", false);
			
			//HEADER
	        beanHeader = new Vector();
	        logicHeader.setMaklumatPermohonan(idFail, getParam("initiateFlagBuka"), session);
	        beanHeader = logicHeader.getBeanMaklumatPermohonan();
			this.context.put("BeanHeader", beanHeader);
			
			if (beanHeader.size() != 0){
				Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
				idFail = hashHeader.get("idFail").toString();
				idPermohonan = hashHeader.get("idPermohonan").toString();
				idStatus = hashHeader.get("idStatus").toString();
				subUrusan = hashHeader.get("subUrusan").toString();
			
			}
			log.info("mode = " + mode);

			//MODE VIEW
			log.info("mode = " + mode);
			if ("view".equals(mode)){	
				this.context.put("readOnly", "readOnly");
	        	this.context.put("classDis", "disabled");
	        	this.context.put("inputTextClass", "disabled");
	        	
				//MAKLUMAT PERMOHONAN
				logicHeader.setMaklumatPermohonan(idFail);
				beanMaklumatPermohonan = new Vector();
				beanMaklumatPermohonan = logicHeader.getBeanMaklumatPermohonan();
				this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
				//MaklumatPermohonanView(mode);
	        	
				//MAKLUMAT HAKMILIK
				beanMaklumatTanah = new Vector();
				logicMaklumat.setMaklumatTanah(idHakmilikAgensi);
				beanMaklumatTanah = logicMaklumat.getBeanMaklumatTanah();
				this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
				
				this.context.put("readOnly", "readOnly");
	        	this.context.put("classDis", "disabled");
	        	this.context.put("inputTextClass", "disabled");
	        	
				//MAKLUMAT PERMOHONAN
				logicHeader.setMaklumatPermohonan(idFail);
				beanMaklumatPermohonan = new Vector();
				beanMaklumatPermohonan = logicHeader.getBeanMaklumatPermohonan();
				this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
				//MaklumatPermohonanView(mode);
	        	
				//MAKLUMAT HAKMILIK
				beanMaklumatTanah = new Vector();
				logicMaklumat.setMaklumatTanah(idHakmilikAgensi);
				beanMaklumatTanah = logicMaklumat.getBeanMaklumatTanah();
				this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
				
				getSenaraiSemakFail(idPermohonan,mode);
    			this.context.put("SenaraiSemak", senaraiSemak);
    	    }
			else if ("update".equals(mode)){
				
				//this.context.put("readonly", "");
        		this.context.put("inputTextClass", "");
        		this.context.put("disabled", "");     
	        	
				//MAKLUMAT PERMOHONAN
				logicHeader.setMaklumatPermohonan(idFail);
				beanMaklumatPermohonan = new Vector();
				beanMaklumatPermohonan = logicHeader.getBeanMaklumatPermohonan();
				this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
				//MaklumatPermohonanView(mode);
	        	
				//MAKLUMAT HAKMILIK
        		if ("doChangeMaklumatTanah".equals(submit)){
    				beanMaklumatTanah = new Vector();
    				idHakmilik = getParam("idHakmilik");
        			logicMaklumat.setMaklumatTanah(idHakmilik);
        			beanMaklumatTanah = logicMaklumat.getBeanMaklumatTanah();
        			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
    			}
        		
        		senaraiHakmilik = new Vector();
    			logicMaklumat.setListHakmilik(idPermohonan);
    			beanMaklumatTanah = logicMaklumat.getSenaraiHakmilik();
    			this.context.put("SenaraiHakmilik", senaraiHakmilik);
    			
    	        this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\" style=\"width:auto\""));
	        	
			}
			
			this.context.put("idFail", idFail);
			this.context.put("idPermohonan", idPermohonan);
			this.context.put("idStatus", idStatus);
			this.context.put("mode", mode);
			this.context.put("subUrusan", subUrusan);
			this.context.put("selectedTab", selectedTab);
		    
			log.info(vm);
			return vm;
		}
		
		//ACTION PAJAKAN -- DAFTAR BARU
		else if ("daftarBaru".equals(actionPajakan)){  
			
		//if ("daftarBaru".equals(submit)){       	
        	//GO TO DAFTAR BARU PAJAKAN   
        	vm = "app/htp/online/frmPajakanDaftarOnline.jsp";
        	log.info("daftarBaru :: "+vm);
        	
        	mode = "new";
        	this.context.put("mode", "new");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PEMOHON
        	Vector<Hashtable<String,String>> vec = logicHeader.setMaklumatPemohon(idUser);
			this.context.put("pemohon", vec.get(0));
        	
        	//MAKLUMAT PERMOHONAN
        	beanMaklumatPermohonan = new Vector();
			Hashtable hashPermohonan = new Hashtable();
			hashPermohonan.put("noFail", "");
			hashPermohonan.put("noFailKJP", getParam("txtNoFailKJP") == null ? "" : getParam("txtNoFailKJP"));
			hashPermohonan.put("tarikhSuratKJP", getParam("tarikhSuratKJP") == null ? "" : getParam("tarikhSuratKJP"));
			hashPermohonan.put("noFailLain", getParam("txtNoFailLain") == null ? "" : getParam("txtNoFailLain"));
			hashPermohonan.put("tarikhAgihan", getParam("tarikhAgihan") == null ? "" : getParam("tarikhAgihan"));
			hashPermohonan.put("tajuk", getParam("txtTajuk") == null ? "" : getParam("txtTajuk"));
			hashPermohonan.put("tarikhSuratPemohon", getParam("tarikhSuratPemohon") == null ? "" : getParam("tarikhSuratPemohon"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			this.context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri", Long.parseLong(idNegeri), "", ""));
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,Long.parseLong(idAgensi), "", ""));
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3", "socSuburusan" ,Long.parseLong(idSuburusan), "", ""));
			this.context.put("selectStatusTanah",HTML.SelectJenisTanah("socStatusTanah" ,Long.parseLong(idStatusTanah), "", ""));
			this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" ,Long.parseLong(idJenisFail), "", ""));
			
			//MAKLUMAT HAKMILIK
			if ("doChangePeganganHakmilik".equals(submit)) {
				idHakmilik = logicMaklumat.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtnoLot").trim(), getParam("txtnoHakmilik").trim());
				if (idHakmilik.isEmpty()) {
					this.context.put("errorPeganganHakmilik","Hakmilik tidak wujud.");
				}
			}
			
			beanMaklumatTanah = new Vector();
			logicMaklumat.setMaklumatTanah(idHakmilik);
			beanMaklumatTanah = logicMaklumat.getBeanMaklumatTanah();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
			
        }else {       	
        	//GO TO LIST FAIL PAJAKAN        	
        	vm = "app/htp/online/frmPajakanSenaraiFail.jsp";
        	log.info("Else :: "+vm);
        	//logic.carianFailOnline2(getParam("txtNoFail"), getParam("txdTarikhTerima"),getParam("txtTajukFail"),getParam("txtNamaPemohon"),idKementerian);
        	logic.carianFailOnline2(getParam("txtNoFail"), getParam("txdTarikhTerima"),getParam("txtTajukFail"),getParam("txtNamaPemohon"),idUser);
			list = new Vector();
			list = logic.getSenaraiFail();
			this.context.put("senaraiFail", list);
			
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));
			
			setupPage(session,action,list);
        }
        
        //SET DEFAULT PARAM
		this.context.put("actionPajakan", actionPajakan);
		this.context.put("selectedTab", selectedTab);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		this.context.put("mode", mode);
		
		//SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idStatus", idStatus);
		this.context.put("subUrusan", subUrusan);
        
		log.info(vm);
		return vm;
	}

	public void setupPage(HttpSession session,String action,Vector list) {
		
		try {
			this.context.put("totalRecords",list.size());
			int page = getParam("page") == "" ? 1:getParamAsInteger("page");
			
			int itemsPerPage;
			if (this.context.get("itemsPerPage")  == null || this.context.get("itemsPerPage") == "") {
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
	
	/*private IPembelian getIPembelian(){
		if (iPembelian==null){
			iPembelian=new PembelianBean();
		}
		return iPembelian;
	} */
		
	private void saveUpdateFail(String idFail,String idSubUrusan,HttpSession session) throws Exception {
        String idUser = (String) session.getAttribute("_ekptg_user_id");
        Hashtable<String,String> hash = new Hashtable<String,String>();
	    hash.put("noFailKJP", getParam("txtNoFailKJP"));
		hash.put("tarikhSuratKJP", getParam("tarikhSuratKJP"));
		hash.put("noFailLain", getParam("txtNoFailLain"));
		hash.put("tarikhAgihan", getParam("tarikhAgihan"));
		hash.put("tajuk", getParam("txtTajuk"));
		hash.put("tarikhSuratPemohon", getParam("tarikhSuratPemohon"));	
		hash.put("userId", idUser);		
		logicMaklumat.saveUpdateFail(idFail, idSubUrusan, hash, session);
			
	}
	
	private void uploadFiles(HttpSession session,String idFail) throws Exception {
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
	        //System.out.println(name +"="+value);
	        log.info(name +"="+value);
		}
	    List items = upload.parseRequest(request);
	    Iterator itr = items.iterator();
	    while (itr.hasNext()) {
	    	FileItem item = (FileItem)itr.next();
		    if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    	String idDokumen = simpanDokumen(session,idFail);			
		    	saveData(idDokumen, item);
		    }
	    }
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
		       			"values(?,?,?,?,?,"+idUser+",sysdate,"+idUser+",sysdate)");
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
	private String simpanDokumen(HttpSession session,String idFail) throws Exception {			
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		Hashtable h = new Hashtable();  
		h.put("flag_Dokumen","1");
		h.put("tajuk_Dokumen",getParam("txtCatatanLampiran"));
		h.put("namaPengirim", getParam("txtNamaLampiran"));
		
		h.put("id_Fail",idFail);
		h.put("id_Masuk",user_id);
		return getIOnline().addMasuk(h);
			    
	}
		 
	private void getSenaraiSemakFail(String idPermohonan,String mode) throws Exception{
		FrmSemakan fs = new FrmSemakan();
		fs.mode = mode;
		context.put("SenaraiSemak", fs.getSenaraiSemakanAttach("htppajakanmycoid",idPermohonan));
		context.put("semakclass", new FrmSemakan());
	}
	
	private IOnline getIOnline(){
		if(iOnline==null){
			iOnline = new OnlineBean();
		}
		return iOnline;
	}



}
