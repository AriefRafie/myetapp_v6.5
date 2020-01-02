package ekptg.view.ppt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppt.FrmUPTSek8HakmilikData;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */


public class FrmPopupPemegangAmanah extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmPopupPemegangAmanah.class);
	
	
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();
		//List SenaraiPB = null;
		
		String usid="";  
   		usid=(String)session.getAttribute("_ekptg_user_id");

    	//default
    	String vm = "";
    	context.put("showAlert","no");
    	
    	//screen jsp
    	String screenPopup = "app/ppt/HMdanPB/frmPopupPemegangAmanah.jsp";

    	//id
    	String id_hakmilik = getParam("id_hakmilik");
    	String id_hakmilikpb = getParam("id_hakmilikpb");
    	
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if ("updatePA".equals(submit)){
    		//updatePA(session,id_hakmilikpb);
    		updatePA_reset(usid,id_hakmilikpb);
    		
    		String[] checkPB = this.request.getParameterValues("checkPB");
    		if (checkPB != null) {
    			for (int i = 0; i < checkPB.length; i++) {						
    			myLogger.info("ids1 :"+checkPB);		
    			//deleteSub(checkPB[i]); 
    			updatePA(usid,id_hakmilikpb,checkPB[i],i+1); 
    				}
    			}	
    		
    		
    		context.put("showAlert","yes");
    		//data pa
    		
    	}
    	
    	
    	//data pa
    	//String id_pa1 = getDataPA(id_hakmilikpb);
    	
    	//dropdown pb not in (<18)
    	//context.put("selectPA",HTML.SelectPBbyHakmilikUnder18(id_hakmilik,"socIdPA",Utils.parseLong(id_pa1),null,"style=width:310px"));
    	context.put("selectPA",getPBbyHakmilikUnder18(id_hakmilik));
    	context.put("listPA",getDataPA_list(id_hakmilikpb));
    	
    	//getDataPA(id_hakmilikpb);
    	//screen
    	vm = screenPopup;
    
    	context.put("id_hakmilik",id_hakmilik);
    	context.put("id_hakmilikpb",id_hakmilikpb);
    	return vm;
    	
	    }//close public template
	
	@SuppressWarnings("unchecked")
	public static Vector getDataPA_list(String id_hakmilikpb)throws Exception {
		    
		Db db = null;
		String sql = "";
		
		try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT ID_PA1,ID_PA2,ID_PA3,ID_PA4 FROM TBLPPTHAKMILIKPB WHERE ID_HAKMILIKPB = '"+id_hakmilikpb+"'";

		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("ID_PA1", rs.getString("ID_PA1")==null?"":rs.getString("ID_PA1"));
		    	  h.put("ID_PA2", rs.getString("ID_PA2")==null?"":rs.getString("ID_PA2"));
		    	  h.put("ID_PA3", rs.getString("ID_PA3")==null?"":rs.getString("ID_PA3"));
		    	  h.put("ID_PA4", rs.getString("ID_PA4")==null?"":rs.getString("ID_PA4"));
		    	  
		    	  list.addElement(h);
		 }
		   return list;
		 } finally {
		      if (db != null) db.close();
		    }
	}//close getDataPA
	
	public static void updatePA(String id_user,String id_hakmilikpb,String id_hakmilikpa,Integer i) throws Exception {
		
	    Db db = null;
	    String sql = "";
	    Connection conn = null;
	    
	    try{
	    	
	    	 db = new Db();
	    	 conn = db.getConnection();
	    	 conn.setAutoCommit(false);
	    	 Statement stmt = db.getStatement();
	    	 
	    	 SQLRenderer r = new SQLRenderer();
	    	 r.update("id_hakmilikpb", id_hakmilikpb);
	    	 if(i==1)
	    	 {
	    	 r.add("id_pa1", id_hakmilikpa);
	    	 }
	    	 
	    	 if(i==2)
	    	 {
	    	 r.add("id_pa2", id_hakmilikpa);
	    	 }
	    	 
	    	 if(i==3)
	    	 {
	    	 r.add("id_pa3", id_hakmilikpa);
	    	 }
	    	 
	    	 if(i==4)
	    	 {
	    	 r.add("id_pa4", id_hakmilikpa);
	    	 }	    	 
	    	 
	    	 r.add("tarikh_kemaskini",r.unquote("sysdate"));
		     r.add("id_kemaskini",id_user);
	    	 sql = r.getSQLUpdate("Tblppthakmilikpb");	    	 
	    	 myLogger.info("SIMPAN PA :"+sql);
	    	 stmt.executeUpdate(sql);	
	    	 
	    	 r.clear();
	    	 
	    	 
	    		 
	    	 sql = "SELECT BPB.ID_BAHAGIANPB,INITCAP(PB.NAMA_PB) AS NAMA_PB,BPB.KETERANGAN_SYER,HPB.ID_HAKMILIKPB " +
	    	 		" FROM TBLPPTBAHAGIANPB BPB,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB "+
	    	 " WHERE BPB.ID_BAHAGIANPB(+) = HPB.ID_BAHAGIANPB " +
	    	 " AND HPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "+
	    	 " AND HPB.ID_HAKMILIKPB = '"+id_hakmilikpa+"'";
		      ResultSet rs = stmt.executeQuery(sql);		     
		      String id_bahagianpb = "";
		      String id_hakmilikpb_temp = "";
		      while (rs.next()) {		    	
		    	  id_bahagianpb = rs.getString("ID_BAHAGIANPB")==null?"":rs.getString("ID_BAHAGIANPB");	
		    	  id_hakmilikpb_temp = rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB");
		      }
		     
		      
		      /*
		      sql = "SELECT BPB.ID_BAHAGIANPB,INITCAP(PB.NAMA_PB) AS NAMA_PB,BPB.KETERANGAN_SYER,HPB.ID_HAKMILIKPB " +
		  	 		" FROM TBLPPTBAHAGIANPB BPB,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB "+
		  	 " WHERE BPB.ID_BAHAGIANPB(+) = HPB.ID_BAHAGIANPB " +
		  	 " AND HPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "+
		  	 " AND HPB.ID_HAKMILIKPB = '"+id_hakmilikpb+"'";
			      ResultSet rs1 = stmt.executeQuery(sql);
			      String nama_pb = "";			      	     
			  while (rs1.next()) {
			    	  nama_pb = rs1.getString("NAMA_PB")==null?"":rs1.getString("NAMA_PB");
			   }*/
		      
		      sql = " SELECT INITCAP(PB.NAMA_PB) AS NAMA_PB FROM TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIKPB HPB_C "+
		      " WHERE HPB_C.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "+
		      " AND (HPB.ID_HAKMILIKPB = HPB_C.ID_PA1 OR HPB.ID_HAKMILIKPB = HPB_C.ID_PA2 " +
		      " OR HPB.ID_HAKMILIKPB = HPB_C.ID_PA3 OR HPB.ID_HAKMILIKPB = HPB_C.ID_PA4) " +
		      " AND HPB.ID_HAKMILIKPB = '"+id_hakmilikpa+"' ";
		      myLogger.info("------------------- SQL NAMA LIST PB :"+sql);
		      ResultSet rs1 = stmt.executeQuery(sql);
			  
			  Hashtable h = null;
			  Vector list_pb = new Vector();
			  while (rs1.next()) {
			    	  h = new Hashtable();
			    	  h.put("NAMA_PB", rs1.getString("NAMA_PB")==null?"":rs1.getString("NAMA_PB"));
			    	  list_pb.addElement(h);
			 }
			 
			  String nama_pb = "";
			  Integer count_pb = 0;
			  Integer count_pbX=list_pb.size();
			  Integer count_pbXX=count_pbX-1;
			        
			  if(list_pb.size()!=0){
				  for (int g = 0; g < list_pb.size(); g++)
				  {
					Hashtable dp = (Hashtable)list_pb.get(g);
					count_pb++;                      
                    if(list_pb.size() > 1 && list_pb.size() != count_pb && count_pbXX != count_pb)                    
                    {nama_pb = nama_pb + (String)dp.get("NAMA_PB")+",";}
                    else if(list_pb.size() > 1 && count_pbXX == count_pb)
                    {nama_pb = nama_pb + " "+(String)dp.get("NAMA_PB")+"";}
                    else if(list_pb.size() > 1 && list_pb.size() == count_pb)                  
                    {nama_pb = nama_pb + " dan "+(String)dp.get("NAMA_PB")+"";}
                    else if(list_pb.size() == 1)
                    {nama_pb = nama_pb + " "+(String)dp.get("NAMA_PB")+"";}                     
				  } 
				}
			 
			  myLogger.info("------------------- NAMA LIST PB :"+nama_pb);
	    	 
		     if(!id_bahagianpb.equals(""))
		     {
	    	 r.update("id_bahagianpb", id_bahagianpb);
	    	 r.add("KETERANGAN_SYER","Pemegang Amanah kepada "+nama_pb);
	    	 r.add("tarikh_kemaskini",r.unquote("sysdate"));
		     r.add("id_kemaskini",id_user);
	    	 sql = r.getSQLUpdate("TBLPPTBAHAGIANPB");	    	 
	    	 myLogger.info("SIMPAN KETERANGAN :"+sql);
	    	 stmt.executeUpdate(sql);
		     }
	    	 
	    	 conn.commit();
	    	 
	    }catch (SQLException se) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data ");
	    }finally {
	      if (db != null) db.close();
	    }
	  }//close updateCatatan
	
	
	public static void resetKeteranganSyer(String id_user,String id_bahagianpb) throws Exception {
		
	    Db db = null;
	    String sql = "";
	    Connection conn = null;
	    
	    try{
	    	
	    	 db = new Db();
	    	 conn = db.getConnection();
	    	 conn.setAutoCommit(false);
	    	 Statement stmt = db.getStatement();
	    	 
	    	 SQLRenderer r = new SQLRenderer();
	    	 
	    	 if(!id_bahagianpb.equals(""))
		     {
	    	 r.update("id_bahagianpb", id_bahagianpb);
	    	 r.add("KETERANGAN_SYER","");
	    	 r.add("tarikh_kemaskini",r.unquote("sysdate"));
		     r.add("id_kemaskini",id_user);
	    	 sql = r.getSQLUpdate("TBLPPTBAHAGIANPB");	    	 
	    	 myLogger.info("RESET KETERANGAN  SYER:"+sql);
	    	 stmt.executeUpdate(sql);
		     }
	    	 
	    	 conn.commit();
	    	 
	    }catch (SQLException se) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data ");
	    }finally {
	      if (db != null) db.close();
	    }
	  }//close updateCatatan
	
	
	public static void updatePA_reset(String id_user,String id_hakmilikpb) throws Exception {
		
	    Db db = null;
	    String sql = "";
	    Connection conn = null;
	    
	    try{
	    	
	    	 db = new Db();
	    	 conn = db.getConnection();
	    	 conn.setAutoCommit(false);
	    	 Statement stmt = db.getStatement();
	    	 
	    	 
	    	 
	    	 sql =  " UPDATE TBLPPTBAHAGIANPB SET KETERANGAN_SYER = '' WHERE ID_BAHAGIANPB IN " +
 	 		"(SELECT BPB.ID_BAHAGIANPB "+
 	 "  FROM TBLPPTBAHAGIANPB BPB,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB, TBLPPTHAKMILIKPB HPB_C "+
 	 "  WHERE BPB.ID_BAHAGIANPB(+) = HPB.ID_BAHAGIANPB AND HPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "+
 	 "  AND (HPB.ID_HAKMILIKPB = HPB_C.ID_PA1 OR HPB.ID_HAKMILIKPB = HPB_C.ID_PA2  "+
 	 " 		 OR HPB.ID_HAKMILIKPB = HPB_C.ID_PA3 OR HPB.ID_HAKMILIKPB = HPB_C.ID_PA4) "+
 	 " AND HPB_C.ID_HAKMILIKPB   = '"+id_hakmilikpb+"' )";
 	 myLogger.info("GET ID_BAHAGIANPB :"+sql);
 	 stmt.executeUpdate(sql);
 	 
 	 
 	 		SQLRenderer r = new SQLRenderer();
	    	 r.update("id_hakmilikpb", id_hakmilikpb);	    	 
	    	 r.add("id_pa1", "");
	    	
	    	 r.add("id_pa2", "");
	    	 
	    	 r.add("id_pa3", "");
	    	 
	    	 r.add("id_pa4", "");	    	 
	    	 
	    	 r.add("tarikh_kemaskini",r.unquote("sysdate"));
		     r.add("id_kemaskini",id_user);
	    	 sql = r.getSQLUpdate("Tblppthakmilikpb");
	    	 stmt.executeUpdate(sql);	
	    	 
	    	 
	    	
	    	 
	    	 
	    	 
	    	 
	    	 conn.commit();
	    	 
	    }catch (SQLException se) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data ");
	    }finally {
	      if (db != null) db.close();
	    }
	  }//close updateCatatan
	
	
	public static List getPBbyHakmilikUnder18(String idhakmilik) throws Exception {
		Db db = null;
		List senaraiPB = null;
		String sql = "SELECT DISTINCT a.id_pihakberkepentingan, UPPER(b.nama_pb) AS nama_pb, a.id_hakmilik, " +
				" a.id_jenispb,a.id_hakmilikpb ";
			   sql += " FROM Tblppthakmilik h, Tblppthakmilikpb a, Tblpptpihakberkepentingan b ";
			   sql += " WHERE a.id_pihakberkepentingan = b.id_pihakberkepentingan(+) ";
			   sql += " AND a.id_hakmilik = h.id_hakmilik"; 
			   sql += " AND b.nama_pb is not null ";
			   sql += " AND a.id_hakmilik = '"+idhakmilik+"'";
			   sql += " AND a.id_jenispb not in (40,41,42)";
			   sql += " AND (b.umur is not null AND b.umur >= 18 OR b.umur is null) ";
			   sql += " ORDER BY nama_pb";
			   myLogger.info("getPBbyHakmilikUnder18 :"+sql);
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			senaraiPB = Collections.synchronizedList(new ArrayList());
			Map f = null;
			while (rs.next()) {
				
				f = Collections.synchronizedMap(new HashMap());
				f.put("id_pihakberkepentingan", rs.getString("id_pihakberkepentingan") == null ? "" : rs.getString("id_pihakberkepentingan"));
				f.put("nama_pb", rs.getString("nama_pb") == null ? "" : rs.getString("nama_pb"));
				f.put("id_hakmilikpb", rs.getString("id_hakmilikpb") == null ? "" : rs.getString("id_hakmilikpb"));
							
				senaraiPB.add(f);	
			}
			return senaraiPB;
		} finally {
			if (db != null)
				db.close();
		}
		
		}
	
//--METHOD--//	
	
	@SuppressWarnings({ "unchecked" })
	private String getDataPA(String id_hakmilikpb) throws Exception{
			
		String id_pa1 = "";
		Vector dataPA = FrmUPTSek8HakmilikData.getDataPA(id_hakmilikpb);
		if(dataPA.size()!=0){
			Hashtable dp = (Hashtable)dataPA.get(0);
			id_pa1 = (String)dp.get("ID_PA1");
		}
		
		return id_pa1;
		
	}//close getDataTolakPermohonan
	
	
	private void updatePA(HttpSession session,String id_hakmilikpb) throws Exception{
		
		FrmUPTSek8HakmilikData.updatePA((String)session.getAttribute("_ekptg_user_id"),id_hakmilikpb,getParam("socIdPA"));
		
	}//close simpanCatatanTolak
	
}//close class
