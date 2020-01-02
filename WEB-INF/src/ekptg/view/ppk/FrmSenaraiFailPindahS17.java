package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import ekptg.model.ppk.PindahFailData;
/*
 * @author 
 * NORZAILY BINTI JASMI
 */
//import ekptg.helpers.DB;
//import ekptg.helpers.HTML;
//import ekptg.helpers.Utils;


public class FrmSenaraiFailPindahS17 extends VTemplate {	
	static Logger myLogger = Logger.getLogger(FrmSenaraiFailPindahS17.class);
	PindahFailData logic = new PindahFailData();
	
	public Template doTemplate() throws Exception
    {
		HttpSession session = request.getSession();
    	String vm = "";
    	
    	Vector list = new Vector();
    	Vector listPemohon = new Vector();
    	Vector listStatusID = new Vector();
    	Vector listPemohonBatal = new Vector();
    	Vector listPermohonan = new Vector();
    	
    	list.clear();
    	listPemohon.clear();    	
    	listStatusID.clear();
    	listPermohonan.clear();
		this.context.put("Util",new lebah.util.Util());

    	String submit = getParam("command");    	
    	System.out.println("SUBMIT SEKSYEN 17 = "+submit);
    	myLogger.info("FrmSenaraiFailPindahS17 ::>>>>>> ");   	
 
    	if	(submit.equals("paparTunggu")) {
    		
        	//call parameters
    		String ekptg_user_id = "";  
        	ekptg_user_id = (String)session.getAttribute("_ekptg_user_id");
        	
        	String id_permohonan = getParam("id_permohonan");
    	    this.context.put("id_permohonan",id_permohonan);
    		
    	    String id_fail = getParam("id_fail");
    	    this.context.put("id_fail",id_fail);
    	    
    	    String id_status = getParam("id_status");
    	    this.context.put("id_status",id_status);

		    
		    //clear input text
		    this.context.put("txdTarikhPindah", "");	    
    	    
    		//get data id_status & id_suburusanstatus
			Hashtable getstatusID = logic.getListStatusID(id_permohonan);				
			this.context.put("data", getstatusID);	    	    
		    
	        logic.setFailPindah(id_permohonan,id_fail);
	        listPemohon = logic.getFailPindah();		
			this.context.put("SenaraiFail",listPemohon);
			
    		context.put("mode", "add");
    		
    		vm = "app/ppk/frmFailPindahMaklumat17.jsp";  
    		
 
    		
       	}else if (submit.equals("paparBatal")) {    
       		
           	//call parameters
       		String id_permohonan = getParam("id_permohonan");
    	    this.context.put("id_permohonan",id_permohonan);
    		
    	    String id_fail = getParam("id_fail");
    	    this.context.put("id_fail",id_fail);  
    	    
    	    String id_status = getParam("id_status");
    	    this.context.put("id_status",id_status);   	    
    	    
    	    Vector noFailAwal = PindahFailData.setNoFailAwal(id_permohonan,id_fail);	
    	    Hashtable a  = (Hashtable) noFailAwal.get(0);
    	    
    	    String no_fail = a.get("no_fail").toString();
    	    this.context.put("no_fail",no_fail);
    	    
    	    myLogger.info("============================ ");
    	    myLogger.info("ID PERMOHONAN =  "+id_permohonan);
    	    myLogger.info("ID FAIL =  "+id_fail);
    	    myLogger.info("ID STATUS =  "+id_status);
    	    myLogger.info("NO FAIL =  "+no_fail);
    	    myLogger.info("============================ ");
    	    
    		//get data id_status & id_suburusanstatus
			Hashtable getstatusID = logic.getListStatusID(id_permohonan);				
			this.context.put("data", getstatusID);	
    	    
		    //get data FAIL PINDAH YG LAMA
	        logic.setFailPindahBatal(id_permohonan,id_fail);
	        listPemohonBatal = logic.getFailPindahBatal();		
			this.context.put("SenaraiFailBatal",listPemohonBatal);		
			
			
			//* get no fail baru			
			PindahFailData.setDataNoFailBaru(no_fail);
			Vector NoFailBaru = PindahFailData.getDataNoFailBaru();
			Hashtable d = (Hashtable) NoFailBaru.get(0);
			String nfAwal = d.get("no_fail_awal").toString();
			String nfBaru = d.get("no_fail_baru").toString();
			context.put("no_file_baru", nfBaru);
			myLogger.info("NO FAIL AWAL SEKSYEN 17 = "+nfAwal);
			myLogger.info("NO FAIL BARU SEKSYEN 17 = "+nfBaru);
       		
       		context.put("mode", "batal");
       		
    		vm = "app/ppk/frmFailPindahMaklumat17.jsp";  

       		
    	}else if (submit.equals("Kembali")) {   		
    		
        	//*tarik parameter
        	String ekptg_user_id = "";  
        	ekptg_user_id = (String)session.getAttribute("_ekptg_user_id");
        	
        	String id_permohonan = getParam("id_permohonan");
    	    this.context.put("id_permohonan",id_permohonan);
    		
    	    String id_fail = getParam("id_fail");
    	    this.context.put("id_fail",id_fail);
    	    
    	    String id_status = getParam("id_status");
    	    this.context.put("id_status",id_status);
    	    
    	    String id_negeri = getParam("id_negeri");
    	    this.context.put("id_negeri",id_negeri);
    	    
    	    String id_daerah = getParam("id_daerah");
    	    this.context.put("id_daerah",id_daerah);   	    
    	    
           	//* LIST PERTAMA
            logic.setListMaklumatMenunggu17(ekptg_user_id,id_negeri,id_daerah);
			list = logic.listTunggu17();
    		context.put("listMaklumatMenunggu", list);
    		context.put("listMaklumatMenunggu_size", list.size());
    		
 		
        	//*	 LIST KEDUA	[ID_STATUS = 56] FILTER BY ID_NEGERIPINDAH DAN ID_DAERAHPINDAH
	        logic.setListbyIdnegeri17(ekptg_user_id,id_negeri, id_daerah);
			list = logic.listTunggubyNeg17();			
    		context.put("listMenunggu", list);
    		context.put("listMenunggu_size", list.size());
    		

    		//*	LIST KETIGA
	        logic.setListbyIdnegeriFailLama17(ekptg_user_id,id_negeri, id_daerah);
			list = logic.listTunggubyNegFailLama17();	
    		context.put("listTelahDipindah", list);
    		context.put("listTelahDipindah_size", list.size());
    		
    		
    		vm = "app/ppk/frmSenaraiFailPindah17.jsp";
    		
    		
    	}else if  (submit.equals("Simpan_PindahFail")) { 
    	    
			//call parameters
    		String id_negeri = getParam("id_negeri");
			String id_daerah = getParam("id_daerah");
			String id_bke = getParam("id_bke");
			String kod_negeri = getParam("kod_negeri");
			String kod_daerah = getParam("kod_daerah");
			String txdTarikhPindah = getParam("txdTarikhPindah");
	      	String id_permohonan = getParam("id_permohonan");
    	    String id_fail = getParam("id_fail");
    	    String no_fail = getParam("txtNoFail1");
    	    
    	    myLogger.info("---------------------------------");
    	    myLogger.info("id_negeri :: "+id_negeri);
    	    myLogger.info("id_daerah :: "+id_daerah);
    	    myLogger.info("id_bke :: "+id_bke);
    	    myLogger.info("kod_negeri :: "+kod_negeri);
    	    myLogger.info("kod_daerah :: "+kod_daerah);
    	    myLogger.info("txdTarikhPindah :: "+txdTarikhPindah);
    	    myLogger.info("id_permohonan :: "+id_permohonan);
    	    myLogger.info("id_fail :: "+id_fail);
    	    myLogger.info("no_fail :: "+no_fail);
    	    myLogger.info("---------------------------------");   	    
    		
    		//get info pemohon
    		String usid="";  
       		usid=(String)session.getAttribute("_ekptg_user_id");      		
    		logic.setListSemak(id_permohonan, usid);
    		listPermohonan = logic.getListSemak();
    		Hashtable ls = (Hashtable) listPermohonan.get(0);
    		String id_permohonansimati = ls.get("id_permohonansimati").toString();
    		String no_subjaket =  ls.get("no_subjaket").toString();
    		context.put("listStemak", listPermohonan);
    		//close
    		
    		
    		//* get id_negeri & id_daerah baru    		
    		logic.setLokasiUserID(usid);
    		Vector listLokasiUserID = logic.getLokasiUserID();
    		Hashtable da = (Hashtable) listLokasiUserID.get(0);
    		String idnegeriB = da.get("id_negeri").toString();  
    		String iddaerahB = da.get("id_daerah").toString();  
    		
    	    
    	    try {
    	    	
        		//* -- GENERATE NEW NO_FAIL BASED ON KOD_NEGERI DAN KOD_DAERAH DR TBLPPKBKE
    	    	generateIdFail17(id_fail,id_permohonan,kod_negeri,kod_daerah,txdTarikhPindah,id_permohonansimati,id_negeri,id_daerah,no_fail,no_subjaket);

        	    //* -- UPDATE TBLPPKPERMOHONAN ID STATUS = 169 [BATAL(PINDAH PTG/KPTG)] - NO FAIL LAMA
        	    edit_status_OldFail_Tblppkpermohonan(session);
        	    
        	    //* -- UPDATE TBLRUJSUBURUSANSTATUSFAIL ID SUBURUSANSTATUS = 533 [BATAL(PINDAH PTG/KPTG)] - NO FAIL LAMA
        		edit_status_OldFail_Tblrujsuburusanstatusfail(session);	    	    	
    	    	
    	    }
			catch (Exception e){
				
				throw new Exception("PEMINDAHAN FAIL TIDAK BERJAYA.SILA CUBA LAGI");
				
			}			

    		context.put("mode", "view");
    		
    		vm = "app/ppk/frmFailPindahMaklumat17.jsp";
    		
    		
		    
    	}else if   ( submit.equals("next")   ||  submit.equals("previous") ){
    		 
	    	 vm = "app/ppk/frmSenaraiFailPindah17.jsp";
	       	 	
	    	 listPemohon = logic.getListTunggu();	
	       	 
	       	 session.setAttribute("SenaraiFail",listPemohon);
	       	 prepareItemForDisplay(session,list,10,submit);
        
        }else {	
        	
        	//*tarik parameter
        	String ekptg_user_id = "";  
        	ekptg_user_id = (String)session.getAttribute("_ekptg_user_id");
        	System.out.println("ekptg_user_id ::"+ekptg_user_id);
        	
        	String id_permohonan = getParam("id_permohonan");
    	    this.context.put("id_permohonan",id_permohonan);
    		
    	    String id_fail = getParam("id_fail");
    	    this.context.put("id_fail",id_fail);
    	    
    	    String id_status = getParam("id_status");
    	    this.context.put("id_status",id_status);
    	    
    	    String id_negeri = getParam("id_negeri");
    	    this.context.put("id_negeri",id_negeri);
    	    
    	    String id_daerah = getParam("id_daerah");
    	    this.context.put("id_daerah",id_daerah);
    	    
    	    System.out.println("id_negeri"+id_negeri);
    	    System.out.println("id_daerah"+id_daerah);
        	

        	//* LIST PERTAMA
            logic.setListMaklumatMenunggu17(ekptg_user_id,id_negeri,id_daerah);
			list = logic.listTunggu17();
    		context.put("listMaklumatMenunggu", list);
    		context.put("listMaklumatMenunggu_size", list.size());
    		
 		
        	//*	 LIST KEDUA	[ID_STATUS = 303] FILTER BY ID_NEGERIPINDAH DAN ID_DAERAHPINDAH
	        logic.setListbyIdnegeri17(ekptg_user_id,id_negeri, id_daerah);
			list = logic.listTunggubyNeg17();
			
    		context.put("listMenunggu", list);
    		context.put("listMenunggu_size", list.size());

    		//*	LIST KETIGA
	        logic.setListbyIdnegeriFailLama17(ekptg_user_id,id_negeri, id_daerah);
			list = logic.listTunggubyNegFailLama17();	
    		context.put("listTelahDipindah", list);
    		context.put("listTelahDipindah_size", list.size());
			
			vm = "app/ppk/frmSenaraiFailPindah17.jsp";						
		}
 
	        Template template = this.engine.getTemplate(vm);
	        return template;
	     
    }//close public template	


	private void generateIdFail17(String id_fail,String id_permohonan,String kod_negeri, String kod_daerah, 
			String txdTarikhPindah,String id_permohonansimati,String id_negeri,
			String id_daerah,String no_fail, String no_subjaket  )  throws Exception {		
		
		//get data
		String ekptg_user_id = getParam("ekptg_user_id");		
		Hashtable output = PindahFailData.generateIdFailS17( ekptg_user_id, id_fail, id_permohonan, kod_negeri, kod_daerah,
				txdTarikhPindah, id_permohonansimati, id_negeri, id_daerah, no_fail, no_subjaket );
		
		String no_file_baru = output.get("getNoFile").toString();		
		String id_permohonan_baru =  output.get("idPermohonanBaru").toString();		
		String id_file_baru = output.get("idFailBaru").toString();	
		String tarikh_mohon_baru = getParam("txdTarikhPindah");
				
		myLogger.info("NO FAIL BARU :: "+no_file_baru);
		myLogger.info("TARIKH MOHON BARU :: "+tarikh_mohon_baru);
		myLogger.info("ID PERMOHONAN BARU :: "+id_permohonan_baru);
		myLogger.info("ID FAIL BARU :: "+id_file_baru);
		myLogger.info("ID NEGERI BARU :: "+id_negeri);
		myLogger.info("ID DAERAH BARU :: "+id_daerah);
		myLogger.info("NO SUBJAKET :: "+no_subjaket);
		myLogger.info("generateIdFail SEKSYEN 17 berjaya");
		
		//get data & put in textfield
		this.context.put("no_file_baru",no_file_baru);
		this.context.put("txdTarikhPindah",tarikh_mohon_baru);
		this.context.put("id_permohonan_baru", id_permohonan_baru);
		this.context.put("id_file_baru", id_file_baru);
		
		//Update Status
		PindahFailData.update_status_NewFail_Tblppkpermohonan17(no_file_baru);
		
		PindahFailData.insert_idsuburusanstatusfail_NewFail17(ekptg_user_id,id_permohonan_baru,id_file_baru);
	}
	

	private void edit_status_OldFail_Tblrujsuburusanstatusfail(HttpSession session) throws Exception {
				
		String id_permohonan = getParam("id_permohonan");
		String idsuburusanstatusfail = getParam("idsuburusanstatusfail");		
		String id_fail = getParam("id_fail");
		String ekptg_user_id = getParam("ekptg_user_id");
				
		System.out.println("----------edit_status_OldFail_Tblrujsuburusanstatusfail-------------");
		System.out.println("id_permohonan = "+id_permohonan);
		System.out.println("idsuburusanstatusfail = "+idsuburusanstatusfail);
		System.out.println("ekptg_user_id = "+ekptg_user_id);
		System.out.println("id_fail = "+id_fail);
		System.out.println("-------------------------------------");
		
	    Hashtable h = null;
	    h = new Hashtable();
	    h.put("id_permohonan", id_permohonan);
	    h.put("idsuburusanstatusfail", idsuburusanstatusfail);
	    h.put("ekptg_user_id", ekptg_user_id);
	    h.put("id_fail", id_fail);
	    		
	    PindahFailData.edit_status_oldFail_Tblrujsuburusanstatusfail(h);
	}


	private void edit_status_OldFail_Tblppkpermohonan(HttpSession session) throws Exception {

		String id_permohonan =  getParam("id_permohonan");
		String ekptg_user_id = getParam("ekptg_user_id");
		
		System.out.println("-------------edit_status_OldFail_Tblppkpermohonan----------");
		System.out.println("id_permohonan = "+id_permohonan);
		System.out.println("ekptg_user_id = "+ekptg_user_id);
		System.out.println("-------------------------------------");
		
	    Hashtable h = null;
	    h = new Hashtable();
	    h.put("id_permohonan", id_permohonan);
	    h.put("ekptg_user_id", ekptg_user_id);

	    PindahFailData.edit_status_oldFail_Tblppkpermohonan(h);
	}
	

	private void prepareItemForDisplay(HttpSession session, Vector objVector, int cntItemPage, String command)
	  {
	    int x;
	    int startno = 0;
	    if (command == null) command = "first";
	    if (session.getAttribute("_portal_startno") != null) startno = ((Integer)session.getAttribute("_portal_startno")).intValue();
	    if (command.equals("previous"))
	      if (startno == objVector.size()) {
	        x = startno % cntItemPage;
	        if (x > 0) {
	          startno -= x;
	          startno -= cntItemPage;
	        } else {
	          startno -= cntItemPage * 2;
	          if (startno < 0) startno = 0;
	        }
	      } else {
	        startno -= cntItemPage * 2;
	        if (startno < 0) startno = 0;
	      }
	    else if (command.equals("first"))
	      startno = 0;
	    	
	    else if (command.equals("last"))
	      x = cntItemPage;
	    else if (command.equals("back"))
	      if (startno == objVector.size()) {
	        x = startno % cntItemPage;
	        if (x == 0) x = cntItemPage;
	        startno -= x;
	      } else {
	        startno -= cntItemPage;
	        if (startno < 0) startno = 0;

	      }
	    
	    Vector moduleVector = new Vector();
	    int i = 0; int cnt = 0;
	    if (objVector.size() > 0) {
	      cnt = 0; for (i = startno; (cnt < cntItemPage) && (i < objVector.size()); ) {
	        moduleVector.addElement(objVector.elementAt(i));

	        ++i; ++cnt;
	      }

	    }

	    session.setAttribute("_portal_moduleVector", moduleVector);
	   
	    this.context.put("startnoInt", new Integer(startno));
	    this.context.put("totalInt", new Integer(objVector.size()));
	    
	    startno = i;
	   
	    session.setAttribute("_portal_startno", new Integer(startno));	    
	  }

	private void prepareItemForDisplayBatal(HttpSession session, Vector objVector, int cntItemPage, String command)
	  {
	    int x;
	    int startno = 0;
	    if (command == null) command = "first";
	    if (session.getAttribute("_portal_startno") != null) startno = ((Integer)session.getAttribute("_portal_startno")).intValue();
	    if (command.equals("previous"))
	      if (startno == objVector.size()) {
	        x = startno % cntItemPage;
	        if (x > 0) {
	          startno -= x;
	          startno -= cntItemPage;
	        } else {
	          startno -= cntItemPage * 2;
	          if (startno < 0) startno = 0;
	        }
	      } else {
	        startno -= cntItemPage * 2;
	        if (startno < 0) startno = 0;
	      }
	    else if (command.equals("first"))
	      startno = 0;
	    	
	    else if (command.equals("last"))
	      x = cntItemPage;
	    else if (command.equals("back"))
	      if (startno == objVector.size()) {
	        x = startno % cntItemPage;
	        if (x == 0) x = cntItemPage;
	        startno -= x;
	      } else {
	        startno -= cntItemPage;
	        if (startno < 0) startno = 0;

	      }
	    
	    Vector moduleVector = new Vector();
	    int i = 0; int cnt = 0;
	    if (objVector.size() > 0) {
	      cnt = 0; for (i = startno; (cnt < cntItemPage) && (i < objVector.size()); ) {
	        moduleVector.addElement(objVector.elementAt(i));

	        ++i; ++cnt;
	      }

	    }

	    session.setAttribute("_portal_moduleVector", moduleVector);
	   
	    this.context.put("startnoInt", new Integer(startno));
	    this.context.put("totalInt", new Integer(objVector.size()));
	    
	    startno = i;
	   
	    session.setAttribute("_portal_startno", new Integer(startno));	    
	  }	

}//close class
