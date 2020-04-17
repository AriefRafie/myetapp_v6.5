package ekptg.report;

import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;
import lebah.servlets.IServlet2;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.object.Person;
//XML

public class Generator extends AjaxBasedModule {
	
	static Logger myLogger = Logger.getLogger(Generator.class);
	static int counter = 0;
	private String errMessage;
	
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String vm = "";
		String submit = getParam("command"); //First level - from AjaxBasedModule
		myLogger.info("submit:"+submit);
		String idPegawai = getParam("socPegawai");
		String bilDokumen = getParam("txtBilDokumen");
		vm = "reports/generator.jsp";
		this.context.put("error","");
		
		String idNegeri = getNegeri(user_id);
		String idHTA = getParam("socHTA");
		this.context.put("selectNamaPegawai",HTML.SelectPegawaiLaporan(idNegeri, "socPegawai", Utils.parseLong(idPegawai), "", ""));

		if ("doGetInfo".equals(submit)) {
			String txtNoFail = getParam("txtNoFail");
			Helpers helper = Helpers.getInstance();
			Person person = helper.getInfo(txtNoFail, user_id);
			if (person == null) {
				this.context.put("error",txtNoFail + " tidak wujud");
				this.context.put("txtNoFail",txtNoFail.trim());
			} else {
				this.context.put("idfail",person.getidFail());
				this.context.put("txtNoFail",txtNoFail.trim());
				this.context.put("person",person);
				this.context.put("bilDokumen",bilDokumen);
				this.context.put("idPegawai",idPegawai);
				this.context.put("selectHTA",HTML.SelectHTAByIdSimati(person.getId_simati(), "socHTA", Utils.parseLong(idHTA), "", ""));
				

		
				String parameters = "idfail="+person.getIdFile();
//				parameters += "&NoFail="+person.getNoFile();
//				parameters += "&noFail="+person.getNoFile();
//				parameters += "&nofail="+person.getNoFile();
				parameters += "&idPermohonan="+person.getId_permohonan();
//				parameters += "&idPermohonanSimati="+person.getId_permohonansimati();
//				parameters += "&idSimati="+person.getId_simati();
				parameters += "&idPegawai="+idPegawai;
				parameters += "&bilDokumen="+bilDokumen;
				parameters += "&idhta=" +idHTA;
				
			
				//Online
//				parameters += "&NoPermohonan="+person.getNoPermohonanOnline();
				
				this.context.put("parameters",parameters);
				//Vector v = getJRXMLFileListing(session,parameters);
				//this.context.put("Senarai_Fail", v);
			}
		} else if ("doLoadReport".equals(submit)) { 
			String xmlname = getParam("xmlname");
			Class klazz;
			Object object = null;
			try {
				xmlname = "ekptg.report.ppk."+xmlname;
				ServletContext context = getServletConfig().getServletContext();
				
				klazz = Class.forName(xmlname);
				object = klazz.newInstance();
				if (object instanceof IServlet2) {
					((IServlet2)object).doService(this.request,this.response,context);
				} else {
					myLogger.info("do nothin here");
				}
			} catch (ClassNotFoundException cnfex) {
				myLogger.info("ClassNotFoundException: " + cnfex.getMessage());
		    } catch (Exception ex) {
		    	myLogger.info("Exception: " + ex.getMessage());
		    }
			this.context.put("xmlname","ekptg.report.ppk."+xmlname);
		}
		else {
			//Vector v = getJRXMLFileListing(session);
			//this.context.put("Senarai_Fail", v);
		}
		return vm;
	}

	public void doXML(HttpSession session) throws Exception {
		//reference http://www.roseindia.net/jsp/jdo-mparsing-xml.shtml
		String folderpath = session.getAttribute("_portal_app_path")+"reports/";
		folderpath = folderpath.replace("/","\\");
		try {
		File file = new File(folderpath+"ReportListing.xml");
		 
		//Create instance of DocumentBuilderFactory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	 
		//Get the DocumentBuilder
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
	 
		//Parsing XML Document
		Document doc = docBuilder.parse(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Vector getJRXMLFileListing(HttpSession session,String parameters) throws Exception{
		
		Vector v = null;
		String flag="";
		try {
			//doXML(session);
			String folderpath = session.getAttribute("_portal_app_path")+"reports/ppk/";
			folderpath = folderpath.replace("/","\\");
			//myLogger.info("folderpath:"+folderpath);
			Hashtable filelist = null;
			File myDir = new File(folderpath);
			String filename = "";
			if( myDir.exists() && myDir.isDirectory()){
				File[] files = myDir.listFiles();
				v = new Vector();
				for(int i=0; i < files.length; i++){ //files.length
					filelist = new Hashtable();
					if ( files[i].toString().endsWith(".jrxml") 
							&& files[i].toString().indexOf("sub") == -1
							&& files[i].toString().indexOf("Laporan") == -1
							&& files[i].toString().indexOf("X") == -1 //assuming testing data
							) {
						filename = files[i].toString();
						filename = filename.replace(folderpath,"");
						filename = filename.replace(".jrxml","");
						
						if(filename.startsWith("Surat")) {
							flag = "&flagReport=S";
						} 
						else if(filename.startsWith("Borang") || filename.startsWith("BORANG") ){
							flag =  "&flagReport=B";
						}
						else{
							flag =  "&flagReport=";
						}
						
						filelist.put("name", filename);
						filelist.put("parameters",parameters+flag);
						v.add(filelist);
						
					}
					
					
				}
			}
			
		} catch (Exception e) {
			
		}
		return v;
	}
	
	public String getNegeri(String user_id)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			
		db = new Db();
		
		sql = "SELECT ID_NEGERI FROM USERS_INTERNAL WHERE USER_ID = " +user_id;
		
		Statement stmt = db.getStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		if (rs.next()){
			return rs.getString("ID_NEGERI").toString().toUpperCase();
		} else {
			return "";
		}
	
		}finally {
			if (db != null)
				db.close();
		}

		
		
	}
}
