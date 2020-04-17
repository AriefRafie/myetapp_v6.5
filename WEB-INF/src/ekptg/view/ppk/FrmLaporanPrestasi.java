package ekptg.view.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.velocity.VTemplate;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.utils.ILaporan;
import ekptg.model.utils.LaporanBean;

public class FrmLaporanPrestasi extends VTemplate {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -736990068121482832L;
	static Logger myLogger = Logger.getLogger(FrmLaporanPrestasi.class);
	Vector negeriLogin = null;
	Vector unitLogin = null;
 	private ILaporan iLaporan = null;  

 	public Template doTemplate() throws Exception {		
		HttpSession session = request.getSession();		
		String vm = "app/ppk/frmLaporanPrestasi.jsp";
		System.out.println("FrmLaporanPrestasi.java");
		Vector negeri = null;
		Vector unit = null;
		String usid="";  
   		usid=(String)session.getAttribute("_ekptg_user_id");	
   		
   		context.put("userId",usid);
   		String submit = getParam("command");
   		System.out.println("[submit] :: " +submit);
		//2017/09
    	String reportRole = String.valueOf(session.getAttribute("myrole"));
		String idNegeri = getParam("socNegeri").equals("")
				?String.valueOf(session.getAttribute("_ekptg_user_negeri")):getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "-1";
		}
		//myLog.info("idNegeri="+idNegeri);
		String idUnit = getParam("socUnit").equals("")
						?String.valueOf(session.getAttribute("_ekptg_user_unit")):getParam("socUnit");
		if (idUnit == null || idUnit.trim().length() == 0){
			idUnit = "0";
		} 
		System.out.println("idUnit="+idUnit);       
    	if ("doChangeSelect".equals(submit)){  		
    		String sorLaporan = getParam("sorLaporan");  
    		//String sorLaporan = "3";  
    		context.put("valLaporan", sorLaporan);
    		
    		if(sorLaporan.equals("1")){
    			System.out.println("[sorLaporan] :: " +sorLaporan);
    		
    			//checked laporan
    			context.put("checkA","checked");
    			context.put("checkB","");
    			context.put("checkC","");
    			
    			//checked by bulan/tahun/tempoh
    			context.put("check1","");
    			context.put("check2","");
    			context.put("check3","");
    			
    			//pilihan by bulan/tahun/tempoh
    			context.put("disA","show");
    			context.put("disB","show");
    			context.put("disC","show");
    			
    			//disable by bulan/tahun/tempoh
    			context.put("sordisabledA","");
    			context.put("sordisabledB","");
    			context.put("sordisabledC","");
    			context.put("sorLaporan","1");
    			
    			//dropdown
    			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"style=width:340 onChange=\"doChangeTempatBicara();\" "));
    			context.put("selectUnit",HTML.SelectUnit("socTempatBicara",null,"style=width:340"));
    			
    			context.put("valTempoh", "1");
    			
    		}else if(sorLaporan.equals("2")){
    			
    			//checked
    			context.put("checkA","");
    			context.put("checkB","checked");
    			context.put("checkC","");
    			
    			//checked by bulan/tahun/tempoh
    			context.put("check1","");
    			context.put("check2","");
    			context.put("check3","");
    			
    			//disable by bulan/tahun/tempoh
    			context.put("sordisabledA","");
    			context.put("sordisabledB","");
    			context.put("sordisabledC","");
    			
    			//pilihan by bulan/tahun/tempoh
    			context.put("disA","show");
    			context.put("disB","show");
    			context.put("disC","show");
    			context.put("sorLaporan","2");
    			//dropdown 
    			negeri = getIdNegeriByLogin(usid);
    			Hashtable hN = (Hashtable)negeri.get(0);
    			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(hN.get("ID_NEGERI").toString()),"style=width:340"));
    			context.put("selectUnit",HTML.SelectUnitByNegeri(hN.get("ID_NEGERI").toString(),"doChangeTempoh",null,"style=width:340"));
    			
    			context.put("valTempoh", "");
    			
    		}else if(sorLaporan.equals("3")){
    			
    			//checked
    			context.put("checkA","");
    			context.put("checkB","");
    			context.put("checkC","checked");
    			
    			//checked by bulan/tahun/tempoh
    			context.put("check1","");
    			context.put("check2","");
    			context.put("check3","");
    			
    			//disable by bulan/tahun/tempoh
    			context.put("sordisabledA","");
    			context.put("sordisabledB","");
    			context.put("sordisabledC","");
    			
    			//pilihan by bulan/tahun/tempoh
    			context.put("disA","show");
    			context.put("disB","show");
    			context.put("disC","show");
    			context.put("sorLaporan","3");
    			
    			//dropdown
    			negeri = getIdNegeriByLogin(usid);
    			Hashtable hN = (Hashtable)negeri.get(0);
//    			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"style=width:340"));

    			//Yang ini ORIGINAL -Mula
    			//context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(hN.get("ID_NEGERI").toString()),"style=width:340"));
    			//Yang ini ORIGINAL -Akhir
    			
    			
    			//context.put("selectUnit",HTML.SelectUnitByNegeri(hN.get("ID_NEGERI").toString(),"socTempatBicara",null,"style=width:340"));
    			//unit = getIdUnitByLogin(usid);
    			//Hashtable hU = (Hashtable)unit.get(0);
    			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"style=width:340 onChange=\"doChangeTempatBicara();\" "));
    			context.put("selectUnit",HTML.SelectUnit("socTempatBicara",null,"style=width:340"));
    		    //context.put("selectUnit",HTML.SelectUnitById_Pjbt(hN.get("ID_NEGERI").toString(),"socTempatBicara",Utils.parseLong(hU.get("ID_PEJABATJKPTG").toString()),"style=width:340",""));
        		context.put("valTempoh", "");
        		
    		}else{	   			
    			//checked
    			context.put("checkA","");
    			context.put("checkB","");
    			context.put("checkC","");
    			
    			//checked by bulan/tahun/tempoh
    			context.put("check1","");
    			context.put("check2","");
    			context.put("check3","");
    			
    			//disable by bulan/tahun/tempoh
    			context.put("sordisabledA","");
    			context.put("sordisabledB","");
    			context.put("sordisabledC","");
    			
    			
    			//dropdown
        		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"style=width:340"));
        		context.put("selectUnit",HTML.SelectUnit("socTempatBicara",null,"style=width:340"));
    		
    		}//close else
    		 
    		String submit2 = getParam("command2");
    		System.out.println("[submit]2 :: " +submit2);
           
        	if ("doChangeTempoh".equals(submit2)){      		
        		String sorTempoh = getParam("sorTempoh");
        		//String sorTempoh = "1";
        		context.put("valTempoh", sorTempoh);
        		
        		if(sorTempoh.equals("1")){
        			
        			//checked
        			context.put("check1","checked");
        			context.put("check2","");
        			context.put("check3","");
        			
        			//disability
        			context.put("disA","show");
        			context.put("disB","");
        			context.put("disC","");
        			
        		}else if(sorTempoh.equals("2")){
        			
        			//checked
        			context.put("check1","");
        			context.put("check2","checked");
        			context.put("check3","");
        			
        			//disability
        			context.put("disA","");
        			context.put("disB","");
        			context.put("disC","show");
        			
        		}else if(sorTempoh.equals("3")){
        			
        			//checked
        			context.put("check1","");
        			context.put("check2","");
        			context.put("check3","checked");
        			
        			//disability
        			context.put("disA","show");
        			context.put("disB","show");
        			context.put("disC","show");
        			
        		}else{
        			
        			//checked
        			context.put("check1","");
        			context.put("check2","");
        			context.put("check3","");
        			
        			//disability
        			context.put("disA","show");
        			context.put("disB","show");
        			context.put("disC","show");
        			
        		}//close else
        	
        		String submit3 = getParam("command3");
        		System.out.println("[submit]3 :: " +submit3);
               
            if ("doChangeTempatBicara".equals(submit3)){       		
            		String id_negeri = getParam("socNegeri");
            		           		
            		//dropdown
            		if(id_negeri!=""){
            			context.put("selectUnit",HTML.SelectUnitByNegeri(id_negeri,"socTempatBicara",null,"style=width:340"));
            			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(id_negeri),null,"style=width:340 onChange=\"doChangeTempatBicara();\" "));
                	}else{
            			context.put("selectUnit",HTML.SelectUnit("socTempatBicara",null,"class=disabled disabled style=width:340"));
            			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,null,"style=width:340 onChange=\"doChangeTempatBicara();\" "));
                	}
            		
            	}//close doChangeTempatBicara
            	
        	}//close doChangeTempoh
        	//vm = "app/ppk/cetakLaporanPrestasiIbuPejabat_byBulan.jsp";
    		//vm = "app/ppk/frmLaporanPrestasi.jsp";
    		
    	//close doChangeSelect    	
    	}else{			
    		//reset data
    		System.out.println("RESET DATA");
    		context.put("checkA","");
			context.put("checkB","");
			context.put("checkC","");
			context.put("check1","");
			context.put("check2","");
			context.put("check3","");
			context.put("disA","show");
			context.put("disB","show");
			context.put("disC","show");
			context.put("valTempoh", "");
			context.put("valLaporan", "");
			//disable by bulan/tahun/tempoh
			context.put("sordisabledA","");
			context.put("sordisabledB","");
			context.put("sordisabledC","");
			context.put("sorLaporan",""); 
    		//dropdown
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"style=width:340"));
    		context.put("selectUnit",HTML.SelectUnit("socTempatBicara",null,"style=width:340"));
    		//myLog.info("reportRole="+reportRole);

     		//vm = "app/ppk/cetakLaporanPrestasiIbuPejabat_byBulan.jsp";
    		//vm = "app/ppk/frmLaporanPrestasi.jsp";
       		
   		}//close else
    	System.out.println("READ HERE AABBCC");
    	getILaporan().setReportRolePPK(context, reportRole, idNegeri, idUnit);
    	System.out.println("READ HERE XXYYZZ");
    	Template template = engine.getTemplate(vm);
    	return template;
           
	}//close template
	
	public Vector getIdNegeriByLogin (String userid)throws Exception{
		 System.out.println("getIdNegeriByLogin");
		 Db db = null;
		 String sql = "";
		 
		 try{
			 negeriLogin = new Vector();
		     db = new Db();
		     Statement stmt = db.getStatement();
		     
		     sql = "SELECT ID_NEGERI FROM USERS_INTERNAL WHERE USER_ID = '"+userid+"'";
		     ResultSet rs = stmt.executeQuery(sql);
		     Hashtable h = null;
		     while (rs.next()) {
		    	  
		    	  h = new Hashtable();
		    	  h.put("ID_NEGERI",rs.getString("ID_NEGERI"));
		    	  negeriLogin.addElement(h);
		    	  
		     }
		     
		     return negeriLogin;
			 
		 }
		 finally {
				if(db != null) db.close();
				
			}
		 
	 }
	
	public Vector getIdUnitByLogin (String userid)throws Exception{
		 Db db = null;
		 String sql = "";
		 System.out.println("getIdUnitByLogin");
		 try{
			 unitLogin = new Vector();
		     db = new Db();
		     Statement stmt = db.getStatement();
		     
		     sql = "SELECT ID_PEJABATJKPTG FROM USERS_INTERNAL WHERE USER_ID = '"+userid+"'";
		     ResultSet rs = stmt.executeQuery(sql);
		     Hashtable h = null;
		     while (rs.next()) {
		    	  
		    	  h = new Hashtable();
		    	  h.put("ID_PEJABATJKPTG",rs.getString("ID_PEJABATJKPTG"));
		    	  unitLogin.addElement(h);
		    	  
		     }
		     
		     return unitLogin;
			 
		 }
		 finally {
				if(db != null) db.close();
				
			}
		 
	 }
	private ILaporan getILaporan(){
		if(iLaporan == null)
			iLaporan = new LaporanBean();
		return iLaporan;
		
	}	
	
	
}//close class
