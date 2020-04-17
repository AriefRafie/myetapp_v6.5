package ekptg.model.pfd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmAdminMesyuaratData {
	
	private static Vector list = null;
	private static Vector listBilik = null;
	
	public static void  setCarianMesyuaratBilikFail(String nama_BilikFail, String seksyen, String negeri)throws Exception {
		
		Db db = null;
	    list = new Vector();
	    String sql = "";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();

	      sql = " SELECT A.ID_LOKASIFAIL, A.LOKASI_FAIL, A.ID_NEGERI,B.NAMA_NEGERI, A.ID_SEKSYEN,C.NAMA_SEKSYEN " + 
	      		 " FROM TBLPFDRUJLOKASIFAIL A, TBLRUJNEGERI B, TBLRUJSEKSYEN C " +
	      		 " WHERE A.ID_NEGERI = B.ID_NEGERI(+) "+
	      		 " AND A.ID_SEKSYEN = C.ID_SEKSYEN(+) " +
	      		 " AND B.ID_NEGERI = '16'";
	      
	      		sql+= " AND UPPER(A.LOKASI_FAIL) LIKE UPPER('%"+nama_BilikFail+"%') ";
	     
	      
	      //SEKSYEN
	      if (seksyen != null) {
				if (!seksyen.trim().equals("")) {
					if (!seksyen.trim().equals("0")) {
						sql = sql + " AND A.ID_SEKSYEN = '" + seksyen + "'";
					}
				}
			}
	      
	      //NEGERI
	      if (negeri != null) {
				if (!negeri.trim().equals("")) {
					if (!negeri.trim().equals("0")) {
						sql = sql + " AND A.ID_NEGERI = '" + negeri + "'" ;
						
					}
				}
			}

	      
	      sql = sql + " ORDER BY A.ID_LOKASIFAIL ASC";
	     
	      System.out.println("mesyarat sql ::"+sql);	
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      String waktu = "";
	      while (rs.next()) {

	    	  h = new Hashtable();
	    	  
	    	  h.put("bil", bil);
	    	  h.put("id_lokasi",rs.getString("ID_LOKASIFAIL")== null?"":rs.getString("ID_LOKASIFAIL"));	
	    	  h.put("lokasi",rs.getString("LOKASI_FAIL")== null?"":rs.getString("LOKASI_FAIL"));	
	    	  h.put("nama_negeri",rs.getString("NAMA_NEGERI")== null?"":rs.getString("NAMA_NEGERI"));
	    	  h.put("nama_seksyen",rs.getString("NAMA_SEKSYEN")== null?"":rs.getString("NAMA_SEKSYEN"));
	    	  list.addElement(h);
	    	  bil++;
	    	  count++;
	      
	    }
	      
	    if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("lokasi","Tiada Data Ditemui");
	    	  h.put("nama_negeri","");
	    	  h.put("nama_seksyen","");
	    	  list.addElement(h);
	    	
	    }
	    } finally {
		      if (db != null) db.close();
		    }
	}
	public static Vector getList(){
		 
		  return list;
	  }
	
public static void  setCarianMesyuaratBilikFailNegeri(String nama_BilikFail, String unit, String negeri, String idNegeri)throws Exception {
		
		Db db = null;
	    list = new Vector();
	    String sql = "";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();

	      sql = " SELECT A.ID_LOKASIFAIL, A.LOKASI_FAIL, A.ID_NEGERI,B.NAMA_NEGERI, A.ID_UNIT,C.NAMA_UNIT " + 
	      		 " FROM TBLPFDRUJLOKASIFAIL A, TBLRUJNEGERI B, TBLRUJUNIT C " +
	      		 " WHERE A.ID_NEGERI = B.ID_NEGERI(+) "+
	      		 " AND A.ID_UNIT = C.ID_UNIT(+) " +
	      		 " AND B.ID_NEGERI = '"+idNegeri+"'";
	      
	      		sql+= " AND UPPER(A.LOKASI_FAIL) LIKE UPPER('%"+nama_BilikFail+"%') ";
	     
	      
	      //SEKSYEN
	      if (unit != null) {
				if (!unit.trim().equals("")) {
					if (!unit.trim().equals("0")) {
						sql = sql + " AND A.ID_UNIT = '" + unit + "'";
					}
				}
			}
	      
	      //NEGERI
	      if (negeri != null) {
				if (!negeri.trim().equals("")) {
					if (!negeri.trim().equals("0")) {
						sql = sql + " AND A.ID_NEGERI = '" + negeri + "'" ;
						
					}
				}
			}

	      
	      //sql = sql + " ORDER BY A.ID_MESYUARAT ASC";
	     
	      System.out.println("mesyarat sql ::"+sql);	
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      String waktu = "";
	      while (rs.next()) {

	    	  h = new Hashtable();
	    	  
	    	  h.put("bil", bil);
	    	  h.put("id_lokasi",rs.getString("ID_LOKASIFAIL")== null?"":rs.getString("ID_LOKASIFAIL"));
	    	  h.put("lokasi",rs.getString("LOKASI_FAIL")== null?"":rs.getString("LOKASI_FAIL"));	
	    	  h.put("nama_negeri",rs.getString("NAMA_NEGERI")== null?"":rs.getString("NAMA_NEGERI"));
	    	  h.put("nama_unit",rs.getString("NAMA_UNIT")== null?"":rs.getString("NAMA_UNIT"));
	    	  list.addElement(h);
	    	  bil++;
	    	  count++;
	      
	    }
	      
	    if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("lokasi","Tiada Data Ditemui");
	    	  h.put("nama_negeri","");
	    	  h.put("nama_unit","");
	    	  list.addElement(h);
	    	
	    }
	    } finally {
		      if (db != null) db.close();
		    }
	}
	public static Vector getListNegeri(){
		 
		  return list;
	  }
	
	
	public void setCarianMesyuaratBilik(String nama_Bilik, String ahli_Seksyen, String ahli_Negeri) throws DbException, SQLException {
	
		Db db = null;
	    listBilik = new Vector();
	    String sql = "";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();

	      sql  = " SELECT A.LOKASI, A.ALAMAT1,A.ID_LOKASI,A.ALAMAT2,A.ALAMAT3,A.POSKOD, A.ID_NEGERI,B.NAMA_NEGERI, A.ID_SEKSYEN,C.NAMA_SEKSYEN " + 
	      		 " FROM TBLPFDRUJLOKASIMESYUARAT A, TBLRUJNEGERI B, TBLRUJSEKSYEN C " +
	      		 " WHERE A.ID_NEGERI = B.ID_NEGERI(+) " +
	      		 " AND A.ID_SEKSYEN = C.ID_SEKSYEN(+) " +
	      		 " AND UPPER(A.LOKASI) LIKE UPPER('%"+nama_Bilik+"%') " +
	      		 " AND B.ID_NEGERI = '16' " ;
	      
	      //SEKSYEN
	      if (ahli_Seksyen != null) {
				if (!ahli_Seksyen.trim().equals("")) {
					if (!ahli_Seksyen.trim().equals("0")) {
						sql = sql + " AND A.ID_SEKSYEN = '" + ahli_Seksyen + "'";
					}
				}
			}
	      
	      //NEGERI
	      if (ahli_Negeri != null) {
				if (!ahli_Negeri.trim().equals("")) {
					if (!ahli_Negeri.trim().equals("0")) {
						sql = sql + " AND A.ID_NEGERI = '" + ahli_Negeri + "'" ;
						
					}
				}
			}

	      
	      //sql = sql + " ORDER BY A.ID_MESYUARAT ASC";
	     
	      System.out.println("mesyarat sql ::"+sql);	
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      String waktu = "";
	      while (rs.next()) {

	    	  h = new Hashtable();
	    	  
	    	  h.put("bil", bil);
	    	  h.put("lokasi",rs.getString("LOKASI"));
	    	  h.put("id_lokasi",rs.getString("ID_LOKASI"));
	    	  h.put("alamat1", rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));
	    	  h.put("alamat2",rs.getString("ALAMAT2")== null?"":rs.getString("ALAMAT2"));
	    	  h.put("alamat3",rs.getString("ALAMAT3")== null?"":rs.getString("ALAMAT3"));	
	    	  h.put("poskod",rs.getString("POSKOD")== null?"":rs.getString("POSKOD"));	
	    	  h.put("nama_negeri",rs.getString("NAMA_NEGERI")== null?"":rs.getString("NAMA_NEGERI"));
	    	  h.put("nama_seksyen",rs.getString("NAMA_SEKSYEN")== null?"":rs.getString("NAMA_SEKSYEN"));	    	  
	    	  listBilik.addElement(h);
	    	  bil++;
	    	  count++;
	      
	    }
	      
	    if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("lokasi","Tiada Data Ditemui");
	    	  h.put("alamat1","");
	    	  h.put("alamat2","");
	    	  h.put("alamat3","");	
	    	  h.put("poskod","");	
	    	  h.put("nama_negeri","");
	    	  h.put("nama_seksyen","");	    	  

	    	  listBilik.addElement(h);
	    	
	    }
	    } finally {
		      if (db != null) db.close();
		    }
		
	}
	public static Vector getListBilik(){
		 
		  return listBilik;
	  }
	

	public static void addBilik(Hashtable datak) throws SQLException, DbException {
		// TODO Auto-generated method stub
		
		Db db = null;
	    String sql = "";
	   
	    try
	    {	 
		      		      
			  String nama_bilik_TBilik = (String)datak.get("nama_bilik_TBilik");
		      String Alamat1_TBilik = (String)datak.get("Alamat1_TBilik");
		      String Alamat2_TBilik = (String)datak.get("Alamat2_TBilik");
		      String Alamat3_TBilik = (String)datak.get("Alamat3_TBilik");
		      String Poskod_TBilik = (String)datak.get("Poskod_TBilik");
		      String Ahli_Negeri_TBilik = (String)datak.get("Ahli_Negeri_TBilik");
		      String Ahli_Seksyen_TBilik = (String)datak.get("Ahli_Seksyen_TBilik");
		      String Ahli_Unit_TBilik = (String)datak.get("Ahli_Unit_TBilik");



		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("LOKASI",nama_bilik_TBilik);
		      r.add("ALAMAT1",Alamat1_TBilik);
		      r.add("ALAMAT2",Alamat2_TBilik);
		      r.add("ALAMAT3",Alamat3_TBilik);
		      r.add("POSKOD", Poskod_TBilik);
		      r.add("ID_NEGERI", Ahli_Negeri_TBilik);
		      r.add("ID_SEKSYEN", Ahli_Seksyen_TBilik);
		      r.add("ID_UNIT", Ahli_Unit_TBilik);
		      r.add("TARIKH_MASUK",r.unquote("sysdate")); 
		      
		      sql = r.getSQLInsert("TBLPFDRUJLOKASIMESYUARAT");  
		      stmt.executeUpdate(sql);

		    } finally {
		      if (db != null) db.close();
		    }
		
	}
	
	public static void addBilikFail(Hashtable datak) throws Exception {
		// TODO Auto-generated method stub
		
		Db db = null;
	    String sql = "";
	   
	    try
	    {	 
	    	  long idLokasiFail = DB.getNextID("TBLPFDRUJLOKASIFAIL_SEQ");    
			  String nama_bilik_TBilikFail = (String)datak.get("nama_bilik_TBilikFail")== null?"":(String)datak.get("nama_bilik_TBilikFail");
		      String Ahli_Negeri_TBilik = (String)datak.get("Ahli_Negeri_TBilik")== null?"":(String)datak.get("Ahli_Negeri_TBilik");
		      String Ahli_Seksyen_TBilik = (String)datak.get("Ahli_Seksyen_TBilik")== null?"":(String)datak.get("Ahli_Seksyen_TBilik");
		      String Ahli_Unit_TBilik = (String)datak.get("Ahli_Unit_TBilik")== null?"":(String)datak.get("Ahli_Unit_TBilik");
		      String idMasuk = (String)datak.get("idMasuk")== null?"":(String)datak.get("idMasuk");



		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("ID_LOKASIFAIL",idLokasiFail);
		      r.add("LOKASI_FAIL",nama_bilik_TBilikFail);
		      r.add("ID_NEGERI", Ahli_Negeri_TBilik);
		      r.add("ID_SEKSYEN", Ahli_Seksyen_TBilik);
		      r.add("ID_UNIT", Ahli_Unit_TBilik);
		      r.add("TARIKH_MASUK",r.unquote("sysdate")); 
		      r.add("ID_MASUK",idMasuk); 
		      
		      sql = r.getSQLInsert("TBLPFDRUJLOKASIFAIL");  
		      stmt.executeUpdate(sql);

		    } finally {
		      if (db != null) db.close();
		    }
		
	}
	
	public static String selectNamaJawatan(String id_jawatan) throws DbException, SQLException {
		
		Db db = null;
	    
	    String sql = "";
	    String namaJawatan = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();


	      sql = "SELECT KETERANGAN FROM TBLRUJJAWATAN WHERE ID_JAWATAN ='"+id_jawatan+"'";
	     
	     
	      System.out.println("mesyarat sql ::"+sql);	
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      
	      while (rs.next()) {
	    	  namaJawatan = rs.getString("KETERANGAN");
	      }
	      
	    } finally {
		      if (db != null) db.close();
		    }
		return namaJawatan;
		
	}

	public static void removeAhliMesyuaratx(String id_PEGAWAI) throws DbException, SQLException {
		
		Db db = null;
	    
	    String sql = "";
	    String namaJawatan = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();


	      sql = "DELETE FROM TBLRUJPEGAWAI WHERE ID_PEGAWAI ='"+id_PEGAWAI+"'";
	     
	     
	      System.out.println("mesyarat sql ::"+sql);	
	      //stmt.executeUpdate(sql);
	      

	      
	    } finally {
		      if (db != null) db.close();
		    }

		
	}
	
	public static void removeLokasiMesyuaratx(String id_lokasi) throws DbException, SQLException {
		
		Db db = null;
	    
	    String sql = "";
	    String namaJawatan = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();


	      sql = "DELETE FROM TBLPFDRUJLOKASIMESYUARAT WHERE ID_LOKASI ='"+id_lokasi+"'";
	     
	     
	     // System.out.println("mesyarat sql ::"+sql);	
	      stmt.executeUpdate(sql);

	      
	    } finally {
		      if (db != null) db.close();
		    }

		
	}
	
	public void hapusAhliMesyuarat(String id_PEGAWAI) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
		db = new Db();
		conn = db.getConnection();
		conn.setAutoCommit(false);
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();

		//TBLRUJPEGAWAI
		r = new SQLRenderer(); 
		r.add("ID_PEGAWAI", id_PEGAWAI);

		sql = r.getSQLDelete("TBLRUJPEGAWAI");
		stmt.executeUpdate(sql);

		conn.commit();

		} catch (SQLException ex) { 
		    try {
		    conn.rollback();
		    } catch (SQLException e) {
		    throw new Exception("Rollback error : " + e.getMessage());
		    }
		    throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());
		    
		    } finally {
		if (db != null)
		db.close();
		} 
	}
	
	
	public void hapusLokasiMesyuarat(String id_lokasi) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
		db = new Db();
		conn = db.getConnection();
		conn.setAutoCommit(false);
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();

		//TBLRUJPEGAWAI
		r = new SQLRenderer(); 
		r.add("ID_LOKASI", id_lokasi);
		   

		sql = r.getSQLDelete("TBLPFDRUJLOKASIMESYUARAT");
		stmt.executeUpdate(sql);

		conn.commit();

		} catch (SQLException ex) { 
		    try {
		    conn.rollback();
		    } catch (SQLException e) {
		    throw new Exception("Rollback error : " + e.getMessage());
		    }
		    throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());
		    
		    } finally {
		if (db != null)
		db.close();
		} 
	}
	
	public void hapusLokasiBilikFail(String id_lokasi) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
		db = new Db();
		conn = db.getConnection();
		conn.setAutoCommit(false);
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();

		//TBLRUJPEGAWAI
		r = new SQLRenderer(); 
		r.add("ID_LOKASIFAIL", id_lokasi);
		   

		sql = r.getSQLDelete("TBLPFDRUJLOKASIFAIL");
		stmt.executeUpdate(sql);

		conn.commit();

		} catch (SQLException ex) { 
		    try {
		    conn.rollback();
		    } catch (SQLException e) {
		    throw new Exception("Rollback error : " + e.getMessage());
		    }
		    throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());
		    
		    } finally {
		if (db != null)
		db.close();
		} 
	}
	public void setCarianMesyuaratBilikNegeri(String nama_Bilik, String ahli_Unit, String ahli_Negeri, String user_negeri) throws DbException, SQLException {
		
		Db db = null;
	    listBilik = new Vector();
	    String sql = "";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();

	      sql  = " SELECT A.LOKASI, A.ALAMAT1,A.ID_LOKASI,A.ALAMAT2,A.ALAMAT3,A.POSKOD, A.ID_NEGERI,B.NAMA_NEGERI, A.ID_UNIT,C.NAMA_UNIT " + 
	      		 " FROM TBLPFDRUJLOKASIMESYUARAT A, TBLRUJNEGERI B, TBLRUJUNIT C " +
	      		 " WHERE A.ID_NEGERI = B.ID_NEGERI(+) " +
	      		 " AND A.ID_UNIT = C.ID_UNIT(+) " +
	      		 " AND UPPER(A.LOKASI) LIKE UPPER('%"+nama_Bilik+"%') " +
	      		 " AND B.ID_NEGERI = '"+user_negeri+"' " ;
	      
	      //SEKSYEN
	      if (ahli_Unit != null) {
				if (!ahli_Unit.trim().equals("")) {
					if (!ahli_Unit.trim().equals("0")) {
						sql = sql + " AND A.ID_UNIT = '" + ahli_Unit + "'";
					}
				}
			}
	      
	      //NEGERI
	      if (ahli_Negeri != null) {
				if (!ahli_Negeri.trim().equals("")) {
					if (!ahli_Negeri.trim().equals("0")) {
						sql = sql + " AND A.ID_NEGERI = '" + ahli_Negeri + "'" ;
						
					}
				}
			}

	      
	      //sql = sql + " ORDER BY A.ID_MESYUARAT ASC";
	     
	      System.out.println("mesyarat sql ::"+sql);	
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      String waktu = "";
	      while (rs.next()) {

	    	  h = new Hashtable();
	    	  
	    	  h.put("bil", bil);
	    	  h.put("lokasi",rs.getString("LOKASI"));
	    	  h.put("id_lokasi",rs.getString("ID_LOKASI"));
	    	  h.put("alamat1", rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));
	    	  h.put("alamat2",rs.getString("ALAMAT2")== null?"":rs.getString("ALAMAT2"));
	    	  h.put("alamat3",rs.getString("ALAMAT3")== null?"":rs.getString("ALAMAT3"));	
	    	  h.put("poskod",rs.getString("POSKOD")== null?"":rs.getString("POSKOD"));	
	    	  h.put("nama_negeri",rs.getString("NAMA_NEGERI")== null?"":rs.getString("NAMA_NEGERI"));
	    	  h.put("nama_unit",rs.getString("NAMA_UNIT")== null?"":rs.getString("NAMA_UNIT"));	    	  
	    	  listBilik.addElement(h);
	    	  bil++;
	    	  count++;
	      
	    }
	      
	    if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("lokasi","Tiada Data Ditemui");
	    	  h.put("alamat1","");
	    	  h.put("alamat2","");
	    	  h.put("alamat3","");	
	    	  h.put("poskod","");	
	    	  h.put("nama_negeri","");
	    	  h.put("nama_seksyen","");	    	  

	    	  listBilik.addElement(h);
	    	
	    }
	    } finally {
		      if (db != null) db.close();
		    }
		
	}
	public static Vector getListBilikNegeri(){
		 
		  return listBilik;
	  }
	


}
