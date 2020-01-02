package ekptg.view.pfd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.model.pfd.TestData;

public class Test extends VTemplate{
	
	protected String boundary = null;
	public Template doTemplate() throws Exception

    {	
		 String command = getParam("command");
		 String nav = getParam("nav");
         String vm = "";
         String noFail = "";
         String tajukFail = "";
         String negeri = "0";
         String seksyen = "0";
         String status = "0";
         String tarikhDaftar = "";
         Vector list = new Vector();

		 HttpSession session = this.request.getSession();


		 if ("simpan".equals(command)){
			 vm = "app/pfd/Test.jsp";

//    		 simpanBlob(session);
			 
    		 
    		
    	 } 
		 if (("next".equals(command)) || ("previous".equals(command))){
			
			 	vm = "app/pfd/Test.jsp";
			 	if (!"".equals(getParam("txtNoFail")))
	  	    		noFail = getParam("txtNoFail");
				if (!"".equals(getParam("txtTajukFail")))
					tajukFail = getParam("txtTajukFail");
				if (!"".equals(getParam("socNegeriD")))
					negeri = getParam("socNegeriD");
				if (!"".equals(getParam("socSeksyenD")))
					seksyen = getParam("socSeksyenD");
				if (!"".equals(getParam("socStatusD")))
					status = getParam("socStatusD");
				if (!"".equals(getParam("txdTarikhDaftar")))
					tarikhDaftar = getParam("txdTarikhDaftar");
				
				TestData.setCarianFail(noFail,tajukFail,negeri,seksyen,status,tarikhDaftar); 
	 	    	list = TestData.getList();
	 	    	session.setAttribute("SenaraiFail", list);
	 	    	prepareItemForDisplay(session,list,10,command);
	 	    	this.context.put("selectNegeriD",HTML.SelectNegeri("socNegeriD",Long.parseLong(negeri),""));
		    	this.context.put("selectSeksyenD",HTML.SelectSeksyen("socSeksyenD",Long.parseLong(seksyen),""));
		    	this.context.put("selectStatusD",HTML.SelectStatusFail("socStatusD",Long.parseLong(status),""));
		    	this.context.put("txtNoFail", noFail);
		    	this.context.put("txtTajukFail", tajukFail);
		    	this.context.put("txdTarikhDaftar", tarikhDaftar);
		 }
		 else{
			vm = "app/pfd/Test.jsp";
			
			if (!"".equals(getParam("txtNoFail")))
  	    		noFail = getParam("txtNoFail");
			if (!"".equals(getParam("txtTajukFail")))
				tajukFail = getParam("txtTajukFail");
			if (!"".equals(getParam("socNegeriD")))
				negeri = getParam("socNegeriD");
			if (!"".equals(getParam("socSeksyenD")))
				seksyen = getParam("socSeksyenD");
			if (!"".equals(getParam("socStatusD")))
				status = getParam("socStatusD");
			if (!"".equals(getParam("txdTarikhDaftar")))
				tarikhDaftar = getParam("txdTarikhDaftar");
			
			TestData.setCarianFail(noFail,tajukFail,negeri,seksyen,status,tarikhDaftar); 
 	    	list = TestData.getList();
 	    	session.setAttribute("SenaraiFail", list);
 	    	prepareItemForDisplay(session,list,10,"first");
 	    	this.context.put("selectNegeriD",HTML.SelectNegeri("socNegeriD",Long.parseLong(negeri),""));
	    	this.context.put("selectSeksyenD",HTML.SelectSeksyen("socSeksyenD",Long.parseLong(seksyen),""));
	    	this.context.put("selectStatusD",HTML.SelectStatusFail("socStatusD",Long.parseLong(status),""));
	    	this.context.put("txtNoFail", noFail);
	    	this.context.put("txtTajukFail", tajukFail);
	    	this.context.put("txdTarikhDaftar", tarikhDaftar);
 	    	
		 }
		 Template template = this.engine.getTemplate(vm);
         return template;

    }
	private void simpanBlob(HttpSession session)throws ServletException, IOException, Exception {
 Hashtable h = new Hashtable();
		 
		 
		 File fBlob = new File ( getParam("txtLampiran") );
		 
		 FileInputStream is = null;
		 System.out.println("1----"+request.getContentLength());
		 System.out.println("2----"+request.getContentType());
		 System.out.println("3----"+response.getContentType());
//		 ServletOutputStream out = response.getOutputStream();
		 if (fBlob != null && request.getContentLength() > 0){
			 
			 try{
				 is = new FileInputStream(fBlob);
				 h.put("content",is);
			 }
			 catch (IOException e){
	                e.printStackTrace();
	         }
		 }
		 h.put("id_Dokumen",getParam("idDokumen"));
		 h.put("jenis_Mime",request.getContentType());
		 h.put("nama_Fail",fBlob.getName());
		 
		 TestData.add(h);		
	
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
	


}
