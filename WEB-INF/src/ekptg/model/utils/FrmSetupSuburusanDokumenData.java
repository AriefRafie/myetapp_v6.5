package ekptg.model.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class FrmSetupSuburusanDokumenData {

	public FrmSetupSuburusanDokumenData() {	}
	
	private static Logger mylog = Logger.getLogger(ekptg.model.utils.FrmSetupSuburusanDokumenData.class);
	public boolean delete(String id) {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLRUJSUBURUSANSTATUS WHERE ID_SUBURUSANSTATUS='"+id+"' ";
			stmt.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (db != null) db.close();
		}
	}
	
	public static boolean insert(Hashtable parameters, String uid) {
		  String sql = "";
		  String name = "";
		  String value ="";
		  Db db = null;
		  try {
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  int x = 1;
			  //long Id_SubUrusan= DB.getNextID("TBLRUJSUBURUSANSTATUS_SEQ");

			  //r.add("id_suburusan", Id_SubUrusan);
			  for (Enumeration e = parameters.keys(); e.hasMoreElements();x++) {
				  name = (String)e.nextElement();
				  value = (String)parameters.get(name);
				  r.add(name.replace("Form_",""),value);
			  }
			  r.add("id_masuk", uid);
			  r.add("tarikh_masuk",r.unquote("SYSDATE"));
			  r.add("id_kemaskini", uid);
			  r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
			  sql = r.getSQLInsert("tblrujdokumenurl");
			  mylog.info("INSERT:"+sql);
			  stmt.executeUpdate(sql);
		  } catch (Exception e) {
			  e.printStackTrace();
			  return false;
		  }
		  finally {
			  if (db != null) db.close();
		  }
		  
		  return true;
	}
	
	  public static boolean update(Hashtable parameters,String id) {
		  String sql="";
		  String name="";
		  String value="";
		  if (id != null) {
			  sql = "UPDATE tblrujdokumenurl SET ";
			  int x = 1;
			  for (Enumeration e = parameters.keys(); e.hasMoreElements();x++)
			   {
				  name = (String)e.nextElement();
				  value = (String)parameters.get(name);
				  sql = sql + name.replace("Form_","") + "='"+ value + "'" + (x<parameters.size()?",":"") ;
			   }
			  sql = sql + ",tarikh_kemaskini=SYSDATE ";
			  sql = sql + " WHERE ID_DOKUMENURL = '" +id+ "' ";
			  mylog.info("update:"+sql);
			  Db db = null;
			  try {
				  db = new Db();
				  Statement stmt = db.getStatement();
				  stmt.executeUpdate(sql);
	
			  } catch (Exception e) { 
				  e.printStackTrace();
				  return false;
			  }
			  finally {
				  if (db != null) db.close();
			  }
		  }
		  return true;
	  }
	
	  public Hashtable getDetails(String id) {
		  Hashtable details_data = null;
		  Db db = null;
		  try {
			  db = new Db();
			  details_data = new Hashtable();
			  Statement stmt = db.getStatement();
			  //String sql = "Select * from tblrujsuburusanstatus WHERE ID_suburusanstatus='"+id+"' ";
			  String sql = "select rsus.id_suburusanstatus,rs.keterangan, rsus.langkah, rsu.nama_suburusan"+
			  " ,rsu.id_urusan,rsu.id_suburusan,rsus.aktif,rsus.module_id,rsus.id_status "+
			  " from tblrujstatus rs, tblrujsuburusanstatus rsus,tblrujsuburusan rsu" +
			  " where rsus.ID_STATUS=rs.ID_STATUS" +
			  " and rsus.ID_SUBURUSAN=rsu.ID_SUBURUSAN";
			  if (id != null && !"".equals(id)) {
				sql += " and rsus.ID_suburusanstatus="+id;
			  }
			  sql += " order by rsu.ID_SUBURUSAN,rsus.langkah";

			  ResultSet rs = stmt.executeQuery(sql);
			  while (rs.next()) {
				  details_data.put("idsuburusanstatus",rs.getString("id_suburusanstatus"));
				  details_data.put("keterangan",checkIsNull(rs.getString("keterangan")));
				  details_data.put("langkah",rs.getInt("langkah"));
				  details_data.put("aktif",rs.getInt("aktif"));
				  details_data.put("moduleid",checkIsNull(rs.getString("module_id")));
				  details_data.put("namasuburusan",checkIsNull(rs.getString("nama_suburusan")));
				  details_data.put("id_Suburusan",checkIsNull(rs.getString("id_suburusan")));
				  details_data.put("id_Urusan",checkIsNull(rs.getString("id_Urusan")));
				  details_data.put("idstatus",checkIsNull(rs.getString("id_Status")));
			  }	
			  return details_data;
		  }catch (Exception e) {
			  e.printStackTrace();
		  }finally {
			  if (db != null) db.close();
		  }
		  return details_data;
	  }
	
	public Vector<Hashtable<String, String>> getListing(String id_urusan,String id_suburusan)	throws Exception{
		Db db = null;
		Vector<Hashtable<String, String>> lists = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "select RJD.KETERANGAN,RDU.DOKUMEN,RDU.PERINGKAT,RDU.TEMPLATE,RDU.ATURAN,RDU.ID_SUBURUSAN "+ 
			" ,RDU.ID_DOKUMENURL,rdu.MODULE_ID,rdu.ID_JENISDOKUMEN from tblrujjenisdokumen rjd,tblrujdokumenurl rdu" +
			" where RDU.ID_JENISDOKUMEN=RJD.ID_JENISDOKUMEN" +
			" ";
			//if (id_urusan != null && !"".equals(id_urusan)) {
			//	sql += " and rsu.ID_URUSAN="+id_urusan;
			//}
			if (id_suburusan != null && !"".equals(id_suburusan)) {
				sql += " and RDU.ID_SUBURUSAN="+id_suburusan;
			}
			sql += " ORDER BY RDU.ID_SUBURUSAN,RDU.DOKUMEN,RDU.ATURAN";
			mylog.info("getListing:"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String, String> record = null;
			lists = new Vector<Hashtable<String, String>>();
			while(rs.next()) {
				record = new Hashtable<String, String>();
				record.put("iddokumenurl",rs.getString("ID_DOKUMENURL"));
				record.put("keterangan",checkIsNull(rs.getString("keterangan")));
				record.put("peringkat",checkIsNull(checkIsNull(rs.getString("PERINGKAT"))));
				record.put("dokumen",checkIsNull(rs.getString("DOKUMEN")));
				record.put("template",checkIsNull(rs.getString("TEMPLATE")));
				record.put("aturan",checkIsNull(rs.getString("ATURAN")));
				record.put("jenisdokumen",checkIsNull(rs.getString("ID_JENISDOKUMEN")));
			lists.addElement(record);
			}
		} catch (Exception e) {
				
		}finally  {
			if (db != null) db.close();
		}
		return lists;		
	}
	
	public Hashtable getDetailsV1(String id)	throws Exception{
		Db db = null;
		Hashtable<String, String> record = null;
		Vector<Hashtable<String, String>> lists = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "select RJD.KETERANGAN,RDU.DOKUMEN,RDU.PERINGKAT,RDU.TEMPLATE,RDU.ATURAN,RDU.ID_SUBURUSAN "+ 
			" ,RDU.ID_DOKUMENURL,rdu.ID_JENISDOKUMEN,rdu.MODULE_ID  from tblrujjenisdokumen rjd,tblrujdokumenurl rdu" +
			" where RDU.ID_JENISDOKUMEN=RJD.ID_JENISDOKUMEN" +
			" ";
			//if (id_urusan != null && !"".equals(id_urusan)) {
			//	sql += " and rsu.ID_URUSAN="+id_urusan;
			//}
			if (id != null && !"".equals(id)) {
				sql += " and RDU.ID_DOKUMENURL="+id;
			}
			sql += " ORDER BY RDU.ID_SUBURUSAN,RDU.DOKUMEN,RDU.ATURAN";
			mylog.info("getListing:"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			lists = new Vector<Hashtable<String, String>>();
			while(rs.next()) {
				record = new Hashtable<String, String>();
				record.put("iddokumenurl",rs.getString("ID_DOKUMENURL"));
				record.put("keterangan",checkIsNull(rs.getString("keterangan")));
				record.put("peringkat",checkIsNull(checkIsNull(rs.getString("PERINGKAT"))));
				record.put("dokumen",checkIsNull(rs.getString("DOKUMEN")));
				record.put("template",checkIsNull(rs.getString("TEMPLATE")));
				record.put("aturan",checkIsNull(rs.getString("ATURAN")));
				record.put("jenisdokumen",checkIsNull(rs.getString("ID_JENISDOKUMEN")));
				record.put("moduleid",checkIsNull(rs.getString("MODULE_ID")));
			//	lists.addElement(record);
			}
		} catch (Exception e) {
				
		}finally  {
			if (db != null) db.close();
		}
		return record;		
	}

	public Vector getListingDaerahJagaan(String id_pejabatjkptg) 
	throws Exception{
			//Vector list = null;
			Db db = null;
			Vector lists = new Vector();
			String sql="";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				sql = "select id_daerah,kod_daerah,nama_daerah from tblRujDaerah where id_daerah in ("+
					"select distinct id_daerahurus from TBLRUJPEJABATURUSAN where id_pejabatjkptg='" + id_pejabatjkptg + "') order by kod_daerah";
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable record = null;
				//new Hashtable();
				while(rs.next()) {
					record = new Hashtable();
					//record.put("id_pejabatjkptg",rs.getString("id_pejabatjkptg"));
					record.put("id_daerahurus",checkIsNull(rs.getString("id_daerah")));
					record.put("kod_daerahurus",checkIsNull(rs.getString("kod_daerah")));
					record.put("nama_daerahurus",checkIsNull(rs.getString("nama_daerah")));
					lists.addElement(record);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally  {
				if (db != null) db.close();
			}
			return lists;
			
	}

	public String getSQL(String id_urusan,String id_suburusan) {
		String sqlWhere = "";
		String sql = "select rsus.id_suburusanstatus,rs.keterangan, rsus.langkah, rsu.nama_suburusan"+ 
		" from tblrujstatus rs, tblrujsuburusanstatus rsus,tblrujsuburusan rsu" +
		" where rsus.ID_STATUS=rs.ID_STATUS" +
		" and rsus.ID_SUBURUSAN=rsu.ID_SUBURUSAN" +
		" and rsu.ID_URUSAN=382 " +
	    " rsu.ID_SUBURUSAN= ";
		
		Hashtable parameters = new Hashtable();
		if (id_urusan != null && !"".equals(id_urusan)) {
			parameters.put("id_urusan", id_urusan);
		}
		if (id_suburusan != null && !"".equals(id_suburusan)) {
			parameters.put("id_suburusan", id_suburusan);
		}
		  int x = 1;
		  String name,value ;
		  for (Enumeration e = parameters.keys(); e.hasMoreElements();x++)
		   {
			  name = (String)e.nextElement();
			  value = (String)parameters.get(name);
			  if (x==1) sqlWhere = " WHERE ";
			  sqlWhere = sqlWhere + name + "='"+ value + "'" + (x<parameters.size()?" AND ":"") ;
		   }
		return sql+sqlWhere;
	}
	
	public String getNamaPejabatJKPTG(String id) {
		Db db = null;
		String sql="",nama="";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "Select nama_pejabat From TBLRUJPEJABATJKPTG WHERE id_pejabatjkptg='"+id+"' "; 
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			nama = checkIsNull(rs.getString("nama_pejabat"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally  {
			if (db != null) db.close();
		}
		return nama;
	}
	
	public String getAlamatPejabatJKPTG(String id) {
		Db db = null;
		String sql="",alamat="";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "Select alamat1,alamat2,alamat3,poskod From TBLRUJPEJABATJKPTG WHERE id_pejabatjkptg='"+id+"' "; 
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			alamat = checkIsNull(rs.getString("alamat1"))+"\n"+
						checkIsNull(rs.getString("alamat2"))+"\n"+
						checkIsNull(rs.getString("alamat3"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally  {
			if (db != null) db.close();
		}
		return alamat;
	}

	public String checkIsNull(String txt) {
		if (txt == null) return "";
		else return txt;
	}
	
	public String getShortName(String nicename) {
		nicename = nicename.replace("WILAYAH PERSEKUTUAN","");
		return nicename;
	}
	
}
