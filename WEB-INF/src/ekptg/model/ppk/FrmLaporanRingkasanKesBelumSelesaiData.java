package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmLaporanRingkasanKesBelumSelesaiData {

	public static Vector getAddress(int id_negeri) throws Exception {
	    Db db = null;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();  
	      String sql = "select nama_pejabat,alamat1,alamat2,alamat3,poskod, no_tel,id_daerah from tblRujPejabatJKPTG  where id_pejabatjkptg in " +
	      		       "(select id_pejabatjkptg from TBLRUJPEJABATURUSAN where id_negeriurus = "+ id_negeri +" and id_jenispejabat=22 and id_seksyen=2)";
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v2 = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("namapejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat").toUpperCase());
	        h.put("alamatOne", rs.getString("alamat1")==null?"":rs.getString("alamat1").toUpperCase());
	        h.put("alamatTwo", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
	        h.put("alamatThree", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
	        h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
	        h.put("notel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
	        h.put("iddaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
	        v2.addElement(h);
	      }
	      return v2;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	public static Vector getAddressSelectedDaerah(int id_negeri,int id_daerah) throws Exception {
	    Db db = null;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();  
	      String sql = "select nama_pejabat,alamat1,alamat2,alamat3,poskod, no_tel,id_daerah from tblRujPejabatJKPTG  where id_pejabatjkptg in " +
	      		       "(select id_pejabatjkptg from TBLRUJPEJABATURUSAN where id_negeriurus = "+ id_negeri +" and id_daerahurus = "+id_daerah +"and id_jenispejabat=22 and id_seksyen=2)";
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v2 = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("namapejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat").toUpperCase());
	        h.put("alamatOne", rs.getString("alamat1")==null?"":rs.getString("alamat1").toUpperCase());
	        h.put("alamatTwo", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
	        h.put("alamatThree", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
	        h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
	        h.put("notel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
	        h.put("iddaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
	        v2.addElement(h);
	      }
	      return v2;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	public static Vector getListNegeriByPpkUnit() throws Exception {
	    Db db = null;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();    
	      String sql = "select distinct(n.id_negeri),n.nama_negeri from tblrujnegeri n, tblrujdaerah d, tblppkrujunit u, tblrujpejabatjkptg p " +
	      			   "where n.id_negeri = d.id_negeri and n.id_negeri = u.id_negeri and d.id_daerah = p.id_daerah " +
		      		   "group by n.id_negeri,n.nama_negeri order by n.nama_negeri";
	      ResultSet rs = stmt.executeQuery(sql);
	      System.out.println("xxx-->>"+sql);
	      Vector v2 = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("idnegeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
	        h.put("namanegeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
	        v2.addElement(h);
	      }
	      return v2;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static int getA(String idNegeri,String idUnit,String tahun,String bulan) throws Exception {
	    Db db = null;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();    
	      String sql = "select count(*) as A from tblppkpermohonan a, tblppkkeputusanpermohonan b,tblrujpejabatjkptg c " +
	      		"where a.id_permohonan = b.id_permohonan and a.id_negerimhn = c.id_negeri and a.id_daerahmhn = c.id_daerah and to_char(tarikh_mohon,'yyyy') =  '"+ tahun +"' " +
	      		"and to_char(tarikh_mohon,'mm') = '"+ bulan +"' and a.id_negerimhn = '"+ idNegeri +"' " +
	      		"and c.id_pejabatjkptg = '"+ idUnit +"' and b.tarikh_terima_borangc is null";
	      ResultSet rs = stmt.executeQuery(sql);
	      System.out.println("getA-->>"+sql);
	      Vector v3 = new Vector();
	      if (rs.next()) {
				return rs.getString("A")==null?0:Integer.parseInt(rs.getString("A"));
			} else return 0;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static int getB(String idNegeri,String idUnit,String tahun,String bulan) throws Exception {
	    Db db = null;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();    
	      String sql = "select count(*) as A from tblppkpermohonan a, tblppkkeputusanpermohonan b, tblrujpejabatjkptg c " +
	      		"where a.id_permohonan = b.id_permohonan and a.id_negerimhn = c.id_negeri and a.id_daerahmhn = c.id_daerah and to_char(tarikh_mohon,'yyyy') =  '"+ tahun +"' " +
	      		"and to_char(tarikh_mohon,'mm') = '"+ bulan +"' and a.id_negerimhn = '"+ idNegeri +"' " +
	      		"and c.id_pejabatjkptg = '"+ idUnit +"' and b.tarikh_hantar_nilaian is null";
	      ResultSet rs = stmt.executeQuery(sql);
	      System.out.println("getB-->>"+sql);
	      Vector v3 = new Vector();
	      if (rs.next()) {
				return rs.getString("A")==null?0:Integer.parseInt(rs.getString("A"));
			} else return 0;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static int getC(String idNegeri,String idUnit,String tahun,String bulan) throws Exception {
	    Db db = null;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();    
	      String sql = "select count(*) as A from tblppkpermohonan a, tblppkkeputusanpermohonan b, tblppkperbicaraan c, tblppkperintah d,tblrujpejabatjkptg e " +
	      		"where a.id_permohonan = b.id_permohonan and b.ID_KEPUTUSANPERMOHONAN = c.ID_KEPUTUSANPERMOHONAN and c.ID_PERBICARAAN = d.ID_PERBICARAAN and a.id_negerimhn = e.id_negeri and a.id_daerahmhn = e.id_daerah " +
	      		"and to_char(a.tarikh_mohon,'yyyy') =  '"+ tahun +"' and to_char(a.tarikh_mohon,'mm') = '"+ bulan +"' and a.id_negerimhn = '"+ idNegeri +"' and e.id_pejabatjkptg = '"+ idUnit +"' " +
	      		"and d.FLAG_TANGGUH = '7'";
	      System.out.println("getC-->>"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Vector v3 = new Vector();
	      if (rs.next()) {
				return rs.getString("A")==null?0:Integer.parseInt(rs.getString("A"));
			} else return 0;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static int getD(String idNegeri,String idUnit,String tahun,String bulan) throws Exception {
	    Db db = null;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();    
	      String sql = "select count(*) as A from tblppkpermohonan a, tblppkkeputusanpermohonan b, tblppkperbicaraan c, tblppkperintah d, tblrujpejabatjkptg e " +
	      		"where a.id_permohonan = b.id_permohonan and b.ID_KEPUTUSANPERMOHONAN = c.ID_KEPUTUSANPERMOHONAN and c.ID_PERBICARAAN = d.ID_PERBICARAAN and a.id_negerimhn = e.id_negeri and a.id_daerahmhn = e.id_daerah " +
	      		"and to_char(a.tarikh_mohon,'yyyy') =  '"+ tahun +"' and to_char(a.tarikh_mohon,'mm') = '"+ bulan +"' and a.id_negerimhn = '"+ idNegeri +"' and e.id_pejabatjkptg = '"+ idUnit +"' " +
	      		"and d.FLAG_TANGGUH = '1'";
	      ResultSet rs = stmt.executeQuery(sql);
	      System.out.println("getC-->>"+sql);
	      Vector v3 = new Vector();
	      if (rs.next()) {
				return rs.getString("A")==null?0:Integer.parseInt(rs.getString("A"));
			} else return 0;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static int getE(String idNegeri,String idUnit,String tahun,String bulan) throws Exception {
	    Db db = null;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();    
	      String sql = "select count(*) as A from tblppkpermohonan a, tblppkkeputusanpermohonan b, tblppkperbicaraan c, tblppkperintah d, tblrujpejabatjkptg e " +
	      		"where a.id_permohonan = b.id_permohonan and b.ID_KEPUTUSANPERMOHONAN = c.ID_KEPUTUSANPERMOHONAN and c.ID_PERBICARAAN = d.ID_PERBICARAAN and a.id_negerimhn = e.id_negeri and a.id_daerahmhn = e.id_daerah " +
	      		"and to_char(a.tarikh_mohon,'yyyy') =  '"+ tahun +"' and to_char(a.tarikh_mohon,'mm') = '"+ bulan +"' and a.id_negerimhn = '"+ idNegeri +"' and e.id_pejabatjkptg = '"+ idUnit +"' " +
	      		"and d.FLAG_TANGGUH in (3,5)";
	      ResultSet rs = stmt.executeQuery(sql);
	      System.out.println("getC-->>"+sql);
	      Vector v3 = new Vector();
	      if (rs.next()) {
				return rs.getString("A")==null?0:Integer.parseInt(rs.getString("A"));
			} else return 0;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static int getF(String idNegeri,String idUnit,String tahun,String bulan) throws Exception {
	    Db db = null;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();    
	      String sql = "select count(*) as A from tblppkpermohonan a, tblppkkeputusanpermohonan b, tblppkperbicaraan c, tblppkperintah d, tblrujpejabatjkptg e " +
	      		"where a.id_permohonan = b.id_permohonan and b.ID_KEPUTUSANPERMOHONAN = c.ID_KEPUTUSANPERMOHONAN and c.ID_PERBICARAAN = d.ID_PERBICARAAN and a.id_negerimhn = e.id_negeri and a.id_daerahmhn = e.id_daerah " +
	      		"and to_char(a.tarikh_mohon,'yyyy') =  '"+ tahun +"' and to_char(a.tarikh_mohon,'mm') = '"+ bulan +"' and a.id_negerimhn = '"+ idNegeri +"' and e.id_pejabatjkptg = '"+ idUnit +"' " +
	      		"and d.FLAG_TANGGUH = '2'";
	      ResultSet rs = stmt.executeQuery(sql);
	      System.out.println("getC-->>"+sql);
	      Vector v3 = new Vector();
	      if (rs.next()) {
				return rs.getString("A")==null?0:Integer.parseInt(rs.getString("A"));
			} else return 0;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static int getG(String idNegeri,String idUnit,String tahun,String bulan) throws Exception {
	    Db db = null;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();    
	      String sql = "select count(*) as A from tblppkpermohonan a, tblppkkeputusanpermohonan b, tblppkperbicaraan c, tblppkperintah d, tblrujpejabatjkptg e " +
	      		"where a.id_permohonan = b.id_permohonan and b.ID_KEPUTUSANPERMOHONAN = c.ID_KEPUTUSANPERMOHONAN and c.ID_PERBICARAAN = d.ID_PERBICARAAN and a.id_negerimhn = e.id_negeri and a.id_daerahmhn = e.id_daerah " +
	      		"and to_char(a.tarikh_mohon,'yyyy') =  '"+ tahun +"' and to_char(a.tarikh_mohon,'mm') = '"+ bulan +"' and a.id_negerimhn = '"+ idNegeri +"' and e.id_pejabatjkptg = '"+ idUnit +"' " +
	      		"and d.FLAG_TANGGUH = '4'";
	      ResultSet rs = stmt.executeQuery(sql);
	      System.out.println("getC-->>"+sql);
	      Vector v3 = new Vector();
	      if (rs.next()) {
				return rs.getString("A")==null?0:Integer.parseInt(rs.getString("A"));
			} else return 0;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static int getH(String idNegeri,String idUnit,String tahun,String bulan) throws Exception {
	    Db db = null;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();    
	      String sql = "select count(*) as A from tblppkpermohonan a, tblrujpejabatjkptg b " +
	      	      		"where to_char(tarikh_mohon,'yyyy') =  '"+ tahun +"' " +
	      	      		"and to_char(tarikh_mohon,'mm') = '"+ bulan +"' and a.id_negerimhn = '"+ idNegeri +"' " +
	      	      		"and a.id_negerimhn = b.id_negeri and a.id_daerahmhn = b.id_daerah "+
	      	      		"and b.id_pejabatjkptg = '"+ idUnit +"' and a.id_status in (151,153)";
	      ResultSet rs = stmt.executeQuery(sql);
	      System.out.println("getC-->>"+sql);
	      Vector v3 = new Vector();
	      if (rs.next()) {
				return rs.getString("A")==null?0:Integer.parseInt(rs.getString("A"));
			} else return 0;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	public static int getI(String idNegeri,String idUnit,String tahun,String bulan) throws Exception {
	    Db db = null;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();    
	      String sql = "select count(*) as A from tblppkpermohonan a, tblppkkeputusanpermohonan b, tblppkperbicaraan c, tblppkperintah d, tblrujpejabatjkptg e " +
	      		"where a.id_permohonan = b.id_permohonan and b.ID_KEPUTUSANPERMOHONAN = c.ID_KEPUTUSANPERMOHONAN and c.ID_PERBICARAAN = d.ID_PERBICARAAN and a.id_negerimhn = e.id_negeri and a.id_daerahmhn = e.id_daerah " +
	      		"and to_char(a.tarikh_mohon,'yyyy') =  '"+ tahun +"' and to_char(a.tarikh_mohon,'mm') = '"+ bulan +"' and a.id_negerimhn = '"+ idNegeri +"' and e.id_pejabatjkptg = '"+ idUnit +"' " +
	      		"and d.FLAG_TANGGUH = '9'";
	      ResultSet rs = stmt.executeQuery(sql);
	      System.out.println("getC-->>"+sql);
	      Vector v3 = new Vector();
	      if (rs.next()) {
				return rs.getString("A")==null?0:Integer.parseInt(rs.getString("A"));
			} else return 0;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	public static int getK(String idNegeri,String idUnit,String tahun,String bulan) throws Exception {
	    Db db = null;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();    
	      String sql = " select count(*) as A from tblppkpermohonan a, tblrujpejabatjkptg b where to_char(a.tarikh_mohon,'yyyy') =  '"+ tahun +"' " +
	      	      		"and to_char(a.tarikh_mohon,'mm') = '"+ bulan +"' and a.id_negerimhn = '"+ idNegeri +"' " +
	      	      		"and b.id_pejabatjkptg = '"+ idUnit +"' and a.seksyen = 8 " +
	      	      		"and a.id_negerimhn = b.id_negeri and a.id_daerahmhn = b.id_daerah "+
	      	      		"and to_char(a.tarikh_mohon,'dd/mm/yyyy') <> to_char(add_months(a.tarikh_mohon,5)+15,'dd/mm/yyyy') ";
	      ResultSet rs = stmt.executeQuery(sql);
	      System.out.println("getC-->>"+sql);
	      Vector v3 = new Vector();
	      if (rs.next()) {
				return rs.getString("A")==null?0:Integer.parseInt(rs.getString("A"));
			} else return 0;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
}
