package ekptg.view.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;


public class FrmFailMapping extends AjaxBasedModule{
	
	static Logger myLogger = Logger.getLogger(PendaftaranFail.class);
	
	private static Vector paparSeksyen = null;
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public String doTemplate2() throws Exception {
		String  vm = "app/pfd/frmSenaraiFailArkib.jsp";
        String submit = getParam("command");
		String action = getParam("action");
        String id_urusan = getParam("Form_id_urusan");
		String id_suburusan= getParam("Form_id_suburusan");
		String id_subsuburusan= getParam("Form_id_subsuburusan");
		String id_subsubsuburusan= getParam("Form_id_subsubsuburusan");
		String id_failarkib = getParam("idFailArkib");
		String noFail = "";
		HttpSession session = this.request.getSession();
		context.put("ScrollX",getParam("ScrollX"));
    	context.put("ScrollY",getParam("ScrollY"));
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		
		Vector seksyen = setSeksyen(user_id);
		Hashtable hA = (Hashtable) seksyen.get(0);
     	String id_seksyen = String.valueOf(hA.get("id_seksyen"));
     	String user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
     	
     	
     	
        
        if ("doChangesUrusan".equals(submit)) {
        	
        	if (!"".equals(getParam("txtNoFail")))
  	    		noFail = getParam("txtNoFail");
        	this.context.put("txtNoFail", noFail);
			this.context.put("selectUrusan", HTML.SelectUrusanArkib("Form_id_urusan", Utils.parseLong(id_urusan), null, "onChange=\"doChangesUrusan()\" "));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(id_urusan, "Form_id_suburusan", Utils.parseLong(id_suburusan), null, "onChange=\"doChangesSuburusan()\" "));
			this.context.put("selectSubSuburusan", HTML.SelectSubSubUrusan("Form_id_subsuburusan",null,null,null));
			this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusan("Form_id_subsubsuburusan",null,null,null));
			List lists = getSenarai(noFail,id_urusan,null,null,null,id_seksyen,user_negeri);
			setupPage(session, action, lists);
			
				
		}
		else if ("doChangesSuburusan".equals(submit)) {
			
			if (!"".equals(getParam("txtNoFail")))
  	    		noFail = getParam("txtNoFail");
			this.context.put("txtNoFail", noFail);
	    	this.context.put("selectUrusan", HTML.SelectUrusanArkib("Form_id_urusan", Utils.parseLong(id_urusan), null, "onChange=\"doChangesUrusan()\" "));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(id_urusan, "Form_id_suburusan", Utils.parseLong(id_suburusan), null, "onChange=\"doChangesSuburusan()\" "));
			this.context.put("selectSubSuburusan", HTML.SelectSubSuburusanByIdSubUrusan(id_suburusan, "Form_id_subsuburusan", Utils.parseLong(id_subsuburusan), null, "onChange=\"doChangesSubSuburusan()\" "));
			this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusan("Form_id_subsubsuburusan",null,null,null));
			List lists = getSenarai(noFail,id_urusan,id_suburusan,null,null,id_seksyen,user_negeri);
			setupPage(session, action, lists);
			
		}
		else if ("doChangesSubSuburusan".equals(submit)) {
			
			if (!"".equals(getParam("txtNoFail")))
  	    		noFail = getParam("txtNoFail");
			this.context.put("txtNoFail", noFail);
			this.context.put("selectUrusan", HTML.SelectUrusanArkib("Form_id_urusan", Utils.parseLong(id_urusan), null, "onChange=\"doChangesUrusan()\" "));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(id_urusan, "Form_id_suburusan", Utils.parseLong(id_suburusan), null, "onChange=\"doChangesSuburusan()\" "));
			this.context.put("selectSubSuburusan", HTML.SelectSubSuburusanByIdSubUrusan(id_suburusan, "Form_id_subsuburusan", Utils.parseLong(id_subsuburusan), null, "onChange=\"doChangesSubSuburusan()\" "));
			this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusanByIdSubSubUrusan(id_subsuburusan, "Form_id_subsubsuburusan", Utils.parseLong(id_subsubsuburusan), null, "onChange=\"doChangesSubSubSuburusan()\" "));
			List lists = getSenarai(noFail,id_urusan,id_suburusan,id_subsuburusan,null,id_seksyen,user_negeri);
			setupPage(session, action, lists);
			
		}
		else if ("doChangesSubSubSuburusan".equals(submit)) {
			
			if (!"".equals(getParam("txtNoFail")))
  	    		noFail = getParam("txtNoFail");
			this.context.put("txtNoFail", noFail);
			this.context.put("selectUrusan", HTML.SelectUrusanArkib("Form_id_urusan", Utils.parseLong(id_urusan), null, "onChange=\"doChangesUrusan()\" "));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(id_urusan, "Form_id_suburusan", Utils.parseLong(id_suburusan), null, "onChange=\"doChangesSuburusan()\" "));
			this.context.put("selectSubSuburusan", HTML.SelectSubSuburusanByIdSubUrusan(id_suburusan, "Form_id_subsuburusan", Utils.parseLong(id_subsuburusan), null, "onChange=\"doChangesSubSuburusan()\" "));
			this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusanByIdSubSubUrusan(id_subsuburusan, "Form_id_subsubsuburusan", Utils.parseLong(id_subsubsuburusan), null, "onChange=\"doChangesSubSubSuburusan()\" "));
			List lists = getSenarai(noFail,id_urusan,id_suburusan,id_subsuburusan,id_subsubsuburusan,id_seksyen,user_negeri);
			setupPage(session, action, lists);
			
		}
		else if ("paparFail".equals(submit)){
			
			context.put("id_fail_carian",getParam("id_fail_carian"));	
			context.put("detailFail","yes");
			this.context.put("txtNoFail", noFail);
	        this.context.put("selectUrusan", HTML.SelectUrusanArkib("Form_id_urusan", Utils.parseLong(id_urusan), null, "onChange=\"doChangesUrusan()\" "));
			this.context.put("selectSuburusan", HTML.SelectSubUrusan1("Form_id_suburusan",Utils.parseLong(id_suburusan),null,"onChange=\"doChangesSuburusan()\" "));
			this.context.put("selectSubSuburusan", HTML.SelectSubSubUrusan("Form_id_subsuburusan",Utils.parseLong(id_subsuburusan),null,"onChange=\"doChangesSubSuburusan()\" "));
			this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusan("Form_id_subsubsuburusan", Utils.parseLong(id_subsubsuburusan),null, "onChange=\"doChangesSubSubSuburusan()\" "));
			List lists = getSenarai(getNoFail(getParam("id_fail_carian")).get("noFail").toString(),id_urusan,id_suburusan,id_subsuburusan,id_subsubsuburusan,id_seksyen,user_negeri);
			setupPage(session, action, lists);
	
			Hashtable hP = (Hashtable)getNoFail(getParam("id_fail_carian"));
			context.put("papar", hP);
			
			
			
			if(!" ".equals(getParam("id_Fail"))){
				
				saveMappingFile(getParam("id_Fail"),getParam("id_fail_carian"),session);
				
			}
			
			List listF = getSenaraiFailBerkaitan(getParam("id_fail_carian").toString());
			Hashtable hF = (Hashtable)listF.get(0);
			context.put("noFailLama",hF.get("NO_FAIL"));
			context.put("SenaraiFailBerkaitan", listF);
			context.put("SaizFail",listF.size());
			
			
	
		}
        
		else{
			
			
			
			if (!"".equals(getParam("txtNoFail")))
  	    		noFail = getParam("txtNoFail");
		this.context.put("txtNoFail", noFail);
        this.context.put("selectUrusan", HTML.SelectUrusanArkib("Form_id_urusan", null, null, "onChange=\"doChangesUrusan()\" "));
		this.context.put("selectSuburusan", HTML.SelectSubUrusan1("Form_id_suburusan",null,null,"onChange=\"doChangesSuburusan()\" "));
		this.context.put("selectSubSuburusan", HTML.SelectSubSubUrusan("Form_id_subsuburusan",null,null,"onChange=\"doChangesSubSuburusan()\" "));
		this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusan("Form_id_subsubsuburusan",null,null, "onChange=\"doChangesSubSubSuburusan()\" "));
		List lists = getSenaraiFailArkib(noFail,id_seksyen,user_negeri);
		setupPage(session, action, lists);
		
		}
        
		return vm;
	}
	
	public static void saveMappingFile(String idFailAsal, String idFailArkib, HttpSession session) throws Exception {
		 Db db = new Db();
		    String sql = "";
		    String sqlFindArkib = "";
		    try
		    {
		    	  Statement stmt = db.getStatement();
//		    	  sqlFindArkib = "SELECT A.ID_FAILARKIB FROM TBLPFDFAILMAPPING A WHERE   A.ID_FAILARKIB = '"+idFailArkib ;
//		    	  ResultSet rs = stmt.executeQuery(sql);
//		    	  
		    	  
		    	 
		    		  
		    		  long idFailMapping = DB.getNextID("TBLPFDFAILMAPPING_SEQ");
			    	  String user_id = (String)session.getAttribute("_ekptg_user_id");
				    
				     
				     			    			      
				     
				      SQLRenderer r = new SQLRenderer();

				      r.add("id_FailMapping", idFailMapping);
				      r.add("id_FailArkib", idFailArkib);
				      r.add("id_FailAsal", idFailAsal);
				      r.add("id_Masuk",user_id);
				      r.add("tarikh_Masuk",r.unquote("sysdate")); 
				  
				      sql = r.getSQLInsert("tblpfdfailmapping");
				      stmt.executeUpdate(sql);
		    		  
		    
		    	
		    	 
			      
			     
			      
			    } finally {
			      if (db != null) db.close();
			    }
	}
	
	
	public List getSenarai(String noFail,String id_urusan, String id_suburusan, String id_subsuburusan, String id_subsubsuburusan, String id_seksyen, String negeri)
	throws Exception {
		
		Db db = null;
		List result = null;
		String sql = "";
		Hashtable h = null;
		boolean setLimit = true;
		try {
			db = new Db();
			result = new ArrayList();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_FAIL, A.NO_FAIL, A.TAJUK_FAIL FROM TBLPFDFAIL A, TBLRUJSEKSYEN B WHERE  A.ID_SEKSYEN = B.ID_SEKSYEN AND A.ID_NEGERI = '"+negeri+"' AND A.ID_SEKSYEN = "+id_seksyen ;
			
			
			
			 if (noFail != null) {
					if (!noFail.trim().equals("")) {
						setLimit = false;
						sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'" + noFail.toUpperCase().trim() + "'|| '%'";
					}
				}
		      
			 if (id_urusan != null){
					if (!id_urusan.trim().equals("")) {
						sql += " AND A.ID_URUSAN = "+id_urusan;
					}
				}
			
			if (id_suburusan != null){
				if (!id_suburusan.trim().equals("")) {
					sql += " AND A.ID_SUBURUSAN = "+id_suburusan;
				}
			}
			if (id_subsuburusan != null){
				if (!id_subsuburusan.trim().equals("")) {
					sql += " AND A.ID_SUBSUBURUSAN = "+id_subsuburusan;
				}
			}
			if (id_subsubsuburusan != null){
				if (!id_subsubsuburusan.trim().equals("")) {
					sql += " AND A.ID_SUBSUBSUBURUSAN = "+id_subsubsuburusan;
				}
			}
			
			
			
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 1;
			while(rs.next()) {
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_FAIL",checkIsNull(rs.getString("ID_FAIL")));
				h.put("NO_FAIL",checkIsNull(rs.getString("NO_FAIL")));
				h.put("TAJUK_FAIL",checkIsNull(rs.getString("TAJUK_FAIL")));
				bil++;
				
				result.add(h);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return result;
		
	}
	public List getSenaraiFailArkib(String noFail, String user_seksyen, String user_negeri)
	throws Exception {
		
		
		
		
		Db db = null;
		List resultAll = null;
		String sql = "";
		Hashtable h = null;
		boolean setLimit = true;
		try {
			db = new Db();
			resultAll = new ArrayList();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_FAIL, A.NO_FAIL, A.TAJUK_FAIL FROM TBLPFDFAIL A, TBLRUJSEKSYEN B WHERE A.FLAG_VIEW_FILE = '5' AND A.ID_SEKSYEN = B.ID_SEKSYEN AND A.ID_NEGERI = '"+user_negeri+"' AND A.ID_SEKSYEN = '"+user_seksyen+"'";
			
			
			 if (noFail != null) {
					if (!noFail.trim().equals("")) {
						setLimit = false;
						sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'" + noFail.toUpperCase().trim() + "'|| '%'";
					}
				}
			
			 System.out.println("carian---"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 1;
			while(rs.next()) {
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_FAIL",checkIsNull(rs.getString("ID_FAIL")));
				h.put("NO_FAIL",checkIsNull(rs.getString("NO_FAIL")));
				h.put("TAJUK_FAIL",checkIsNull(rs.getString("TAJUK_FAIL")));
				bil++;
				resultAll.add(h);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return resultAll;
		
	}
	public List getSenaraiFailBerkaitan(String idFailCarian)
			throws Exception {
				
				Db db = null;
				List result = null;
				String sql = "";
				Hashtable h = null;
				boolean setLimit = true;
				try {
					db = new Db();
					result = new ArrayList();
					Statement stmt = db.getStatement();
					sql = "SELECT A.ID_FAILASAL, B.NO_FAIL, B.TAJUK_FAIL FROM TBLPFDFAILMAPPING A, TBLPFDFAIL B WHERE  A.ID_FAILASAL = B.ID_FAIL AND A.ID_FAILARKIB = "+idFailCarian ;

					
					
					ResultSet rs = stmt.executeQuery(sql);
					int bil = 1;
					while(rs.next()) {
						h = new Hashtable();
						h.put("BIL", bil);
						h.put("ID_FAILASAL",checkIsNull(rs.getString("ID_FAILASAL")));
						h.put("NO_FAIL",checkIsNull(rs.getString("NO_FAIL")));
						h.put("TAJUK_FAIL",checkIsNull(rs.getString("TAJUK_FAIL")));
						
						bil++;
						
						result.add(h);
					}
						
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (db != null) db.close();
				}
				
				return result;
				
			}
	public String checkIsNull(String txt) {
		if (txt == null) return "";
		else return txt;
	}
	
	public static Vector setSeksyen(String user_id) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparSeksyen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select a.id_seksyen, a.id_negeri,b.kod_seksyen, b.nama_seksyen from users_internal a, tblrujseksyen b where a.id_seksyen = b.id_seksyen and user_id = '"+user_id+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("id_seksyen", rs.getString("id_seksyen")==null?"":rs.getString("id_seksyen"));
		    	  h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		    	  h.put("kod_seksyen", rs.getString("kod_seksyen")==null?"":rs.getString("kod_seksyen"));
		    	  h.put("nama_seksyen", rs.getString("nama_seksyen")==null?"":rs.getString("nama_seksyen"));
		    	  paparSeksyen.addElement(h); 
		      }
		      
		      return paparSeksyen;
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		
		
	}
	
	public Hashtable getNoFail(String idFail) throws Exception{
		
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "SELECT A.ID_FAIL, I.TARAF_KESELAMATAN, F.NAMA_SEKSYEN, A.ID_URUSAN, A.ID_SUBURUSAN, A.TARIKH_DAFTAR_FAIL, A.TAJUK_FAIL, A.NO_FAIL, G.NAMA_NEGERI, H.NAMA_DAERAH, K.LOKASI_FAIL, A.FAHARASAT, J.KETERANGAN, A.FLAG_VIEW_FILE, " +
						 "B.KOD_URUSAN, B.NAMA_URUSAN, C.KOD_SUBURUSAN, C.NAMA_SUBURUSAN, D.KOD_SUBSUBURUSAN, D.NAMA_SUBSUBURUSAN, E.KOD_SUBSUBSUBURUSAN, E.NAMA_SUBSUBSUBURUSAN "+
						  "FROM TBLPFDFAIL A, TBLRUJURUSAN B, TBLRUJSUBURUSAN C, TBLRUJSUBSUBURUSAN D, TBLRUJSUBSUBSUBURUSAN E, TBLRUJSEKSYEN F, TBLRUJNEGERI G, " +
						  "TBLRUJDAERAH H, TBLPFDRUJTARAFKESELAMATAN I, TBLRUJSTATUS J, TBLPFDRUJLOKASIFAIL K " +
						  "WHERE " +
						  "A.ID_URUSAN = B.ID_URUSAN(+) " +
						  "AND A.ID_SUBURUSAN = C.ID_SUBURUSAN(+) " +
						  "AND A.ID_SUBSUBURUSAN = D.ID_SUBSUBURUSAN(+) " +
						  "AND A.ID_SUBSUBSUBURUSAN = E.ID_SUBSUBSUBURUSAN(+) " +
						  "AND A.ID_SEKSYEN = F.ID_SEKSYEN(+) " +
						  "AND A.ID_NEGERI = G.ID_NEGERI(+) " +
						  "AND A.ID_DAERAH = H.ID_DAERAH(+) " +
						  "AND A.ID_TARAFKESELAMATAN = I.ID_TARAFKESELAMATAN(+) " +
						  "AND A.ID_STATUS = J.ID_STATUS(+) " +
						  "AND A.ID_LOKASIFAIL = K.ID_LOKASIFAIL(+) "+
						  "AND A.ID_FAIL='"+idFail+"'";
		      
		     
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("idFail",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("status",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
					h.put("taraf",rs.getString("TARAF_KESELAMATAN")==null?"":rs.getString("TARAF_KESELAMATAN"));
					h.put("namaSeksyen",rs.getString("NAMA_SEKSYEN")==null?"":rs.getString("NAMA_SEKSYEN"));
					h.put("idUrusan",rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
					h.put("idSuburusan",rs.getString("ID_SUBURUSAN")==null?"":rs.getString("ID_SUBURUSAN"));
					h.put("kodUrusan",rs.getString("KOD_URUSAN")==null?"":rs.getString("KOD_URUSAN"));
					h.put("namaUrusan",rs.getString("NAMA_URUSAN")==null?"":rs.getString("NAMA_URUSAN"));
					h.put("kodSuburusan",rs.getString("KOD_SUBURUSAN")==null?"":rs.getString("KOD_SUBURUSAN"));
					h.put("namaSuburusan",rs.getString("NAMA_SUBURUSAN")==null?"":rs.getString("NAMA_SUBURUSAN"));
					h.put("kodSubSuburusan",rs.getString("KOD_SUBSUBURUSAN")==null?"":rs.getString("KOD_SUBSUBURUSAN"));
					h.put("namaSubSuburusan",rs.getString("NAMA_SUBSUBURUSAN")==null?"":rs.getString("NAMA_SUBSUBURUSAN"));
					h.put("kodSubSubSuburusan",rs.getString("KOD_SUBSUBSUBURUSAN")==null?"":rs.getString("KOD_SUBSUBSUBURUSAN"));
					h.put("namaSubSubSuburusan",rs.getString("NAMA_SUBSUBSUBURUSAN")==null?"":rs.getString("NAMA_SUBSUBSUBURUSAN"));
					h.put("tajukFail",rs.getString("TAJUK_FAIL")==null?"":rs.getString("TAJUK_FAIL"));
					h.put("noFail",rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("namaNegeri",rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
					h.put("namaDaerah",rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
					h.put("lokasi",rs.getString("LOKASI_FAIL")==null?"":rs.getString("LOKASI_FAIL"));
					h.put("kabinet",rs.getString("FAHARASAT")==null?"":rs.getString("FAHARASAT"));
					h.put("flag_view_file",rs.getString("FLAG_VIEW_FILE")==null?"":rs.getString("FLAG_VIEW_FILE"));
					h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
		    	
		      }
		      
		      return h;
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		
		
	}

}
