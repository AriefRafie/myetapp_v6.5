package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.entities.Tblppkrujunit;
import ekptg.model.entities.Tblrujpejabatjkptg;

public class PPKUtilData {
	
	Date now = new Date();
	static Logger myLog = Logger.getLogger(ekptg.model.ppk.PPKUtilData.class);
	/**
	 * Dibuat Pada 2017/10/29,
	 * @param id_jkptg
	 * @return
	 * @throws Exception
	 */
	public static Vector<Tblrujpejabatjkptg> getTempatBicaraByPejabatJKPTG(String myId,String idJkptg) 
		throws Exception {
		Db db = null;
		String sql = "";
//		sql = "Select distinct id_pejabatjkptg,kod_jkptg,nama_pejabat from tblrujpejabatjkptg";
//		sql += " where id_pejabatjkptg = '" + id_jkptg + "'";
//		sql += " ORDER BY lpad(id_pejabatjkptg,10)";
		//kemaskini pada 2017/10/26
		sql = "SELECT * FROM " +
			" ("+
			" SELECT DISTINCT RP.ID_PEJABATJKPTG,RP.KOD_JKPTG,RP.NAMA_PEJABAT "+ 
			" FROM TBLRUJPEJABATJKPTG RP, TBLRUJPEJABATURUSAN RPU "+
			" WHERE "+
			" RPU.ID_PEJABATJKPTG = RP.ID_PEJABATJKPTG "+
			" AND RPU.ID_SEKSYEN = 2" +
			" AND RPU.ID_JENISPEJABAT IN ('22')" +
			" AND RPU.ID_DAERAHURUS IN" +
			" ( SELECT ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN WHERE ID_PEJABATJKPTG='" + idJkptg + "' " +
			" ) " +
			" UNION "+
			" SELECT DISTINCT RP.ID_PEJABATJKPTG,RP.KOD_JKPTG,RP.NAMA_PEJABAT "+
			" FROM TBLRUJPEJABATJKPTG RP,TBLPERMOHONANBANTUUNIT BU "+
			" WHERE RP.ID_PEJABATJKPTG = BU.ID_UNIT " +
			" AND BU.ID_PEMOHON = '" + myId + "' " +
			" ) "+
			" ORDER BY LPAD(ID_PEJABATJKPTG,1) " +
			"";

		System.out.println("sql Tblrujpejabatjkptg="+sql);
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujpejabatjkptg> v = new Vector<Tblrujpejabatjkptg>();
			Tblrujpejabatjkptg s = null;

			while (rs.next()) {
				s = new Tblrujpejabatjkptg();
				s.setIdPejabatjkptg(rs.getLong(1));
				s.setKodJkptg(rs.getString(2));
				s.setNamaPejabat(rs.getString(3));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
		
	}
	public static Vector<Tblppkrujunit> getSenaraiPegawaiLaporan(String idNegeri) throws Exception {
		Db db = null;
		String sql = "SELECT ID_UNITPSK, NVL(KOD,'') KOD, upper(NAMA_PEGAWAI) NAMA_PEGAWAI,STATUS_PEG " + 
				" ,case " + 
				" 	when STATUS_PEG=1 then '(Aktif)' "+ 
				" 	else '(Tidak Aktif)' " + " " +
				" end AS CATATAN " + 
				" FROM TBLPPKRUJUNIT WHERE ID_NEGERI = '" + idNegeri+ "' " +
				" ORDER BY STATUS_PEG desc ,NAMA_PEGAWAI asc";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			//System.out.println("sql = " +sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblppkrujunit> list = new Vector<Tblppkrujunit>();
			Tblppkrujunit f = null;
			while (rs.next()) {
				f = new Tblppkrujunit();
				f.setidunitpk(rs.getLong(1));
				f.setkod(rs.getString(2));
				f.setnamapegawai(rs.getString(3));
				f.setstatuspeg(rs.getString(4));
				f.setcatatan(rs.getString(5));
				list.addElement(f);
				
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
		
	}
	
	public static Vector<Tblppkrujunit> getSenaraiPegawaiLaporan_KPP(String idNegeri,  boolean kpp_hq) throws Exception {
		Db db = null;
		String sql = "";
		
	
			 sql ="SELECT ID_UNITPSK, NVL(KOD,'') KOD, upper(NAMA_PEGAWAI) NAMA_PEGAWAI,STATUS_PEG " + 
						" ,case " + 
						" 	when STATUS_PEG=1 then '(Aktif)' "+ 
						" 	else '(Tidak Aktif)' " + " " +
						" end AS CATATAN " + 
						" FROM TBLPPKRUJUNIT ";
						if (kpp_hq==false){
						
						sql = sql+" WHERE  ID_NEGERI = '" + idNegeri+ "' " ;
						}
						sql = sql+" ORDER BY STATUS_PEG desc ,NAMA_PEGAWAI asc";
		
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			//System.out.println("sql = " +sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblppkrujunit> list = new Vector<Tblppkrujunit>();
			Tblppkrujunit f = null;
			while (rs.next()) {
				f = new Tblppkrujunit();
				f.setidunitpk(rs.getLong(1));
				f.setkod(rs.getString(2));
				f.setnamapegawai(rs.getString(3));
				f.setstatuspeg(rs.getString(4));
				f.setcatatan(rs.getString(5));
				list.addElement(f);
				
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
		
	}

	public static String getNamaUnitPPK(String idUnit) throws Exception {
		String returnValue="";
		Db db = null;
		String sql = "SELECT A.ID_PEJABATJKPTG, B.ID_DAERAH, B.KOD_DAERAH, B.NAMA_DAERAH, A.NAMA_PEJABAT, A.KOD_JKPTG, A.ALAMAT1"+
					 " FROM TBLRUJPEJABATJKPTG A, TBLRUJDAERAH B " +
					 " WHERE A.ID_SEKSYEN = 2 AND A.ID_JENISPEJABAT IN (22,24) AND A.ID_DAERAH = B.ID_DAERAH" +
					 " AND A.ID_PEJABATJKPTG='"+idUnit+"'" +
					 " ORDER BY A.ID_NEGERI,B.ID_DAERAH";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				returnValue = Utils.RemoveSymbol(rs.getString("NAMA_PEJABAT").toUpperCase());
			}
			return returnValue;
			
		} finally {
			if (db != null)
				db.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static Vector<Hashtable<String, Comparable>> getUnitPPK() throws Exception {
		Db db = null;
		String sql = "SELECT A.ID_PEJABATJKPTG, B.ID_DAERAH, B.KOD_DAERAH, B.NAMA_DAERAH, A.NAMA_PEJABAT, A.KOD_JKPTG, A.ALAMAT1"+
					 " FROM TBLRUJPEJABATJKPTG A, TBLRUJDAERAH B " +
					 " WHERE A.ID_SEKSYEN = 2 AND A.ID_JENISPEJABAT = 22 AND A.ID_DAERAH = B.ID_DAERAH" +
					 " ORDER BY A.ID_NEGERI,B.ID_DAERAH";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id",rs.getLong("ID_PEJABATJKPTG"));
				h.put("kod",rs.getString("KOD_JKPTG") == null ? "" : rs.getString("KOD_JKPTG"));
				h.put("keterangan", Utils.RemoveSymbol(rs.getString("NAMA_PEJABAT").toUpperCase()));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static Vector<Hashtable<String, Comparable>> getUnitPPK(String idnegeri) throws Exception {
		Db db = null;
		String sql = "SELECT A.ID_PEJABATJKPTG, B.ID_DAERAH, B.KOD_DAERAH, B.NAMA_DAERAH, A.NAMA_PEJABAT, A.KOD_JKPTG, A.ALAMAT1"+
					 " FROM TBLRUJPEJABATJKPTG A, TBLRUJDAERAH B " +
					 " WHERE A.ID_SEKSYEN = 2 AND A.ID_JENISPEJABAT = 22 AND A.ID_DAERAH = B.ID_DAERAH";
		if(idnegeri!=null)
			sql +=" AND A.ID_NEGERI="+idnegeri;
					 
		sql +=" ORDER BY A.ID_NEGERI,B.ID_DAERAH";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id",rs.getLong("ID_PEJABATJKPTG"));
				h.put("kod",rs.getString("KOD_JKPTG") == null ? "" : rs.getString("KOD_JKPTG"));
				h.put("keterangan", Utils.RemoveSymbol(rs.getString("NAMA_PEJABAT").toUpperCase()));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}	
	
    public static Tblrujpejabatjkptg getPejabatMengikutId(String idpejabat) throws Exception {
    	Db db = null;
    	String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_pejabatjkptg");
	      r.add("nama_pejabat");
	      r.add("alamat1");
	      r.add("alamat2");
	      r.add("alamat3");
	      r.add("poskod");
	      r.add("id_negeri");
	      r.add("id_daerah");
	      r.add("no_tel");
	      r.add("no_fax");
	      r.add("id_pejabatjkptg",r.unquote(idpejabat));
	      sql = r.getSQLSelect("tblrujpejabatjkptg");
	      ResultSet rs = stmt.executeQuery(sql);
	
	      Tblrujpejabatjkptg s = null;
	      while (rs.next()) {    	  
	    	  s = new Tblrujpejabatjkptg();
		      s.setIdPejabatjkptg(rs.getLong("id_pejabatjkptg"));
		      s.setNamaPejabat(rs.getString("nama_pejabat"));
		  	  s.setAlamat1(rs.getString("Alamat1"));
	    	  if(rs.getString("Alamat2") != null){
	    		  s.setAlamat2(rs.getString("Alamat2"));
	    	  }else{
	    		  s.setAlamat2("");
	    	  }
	    	  if(rs.getString("Alamat3") != null){
	    		  s.setAlamat3(rs.getString("Alamat3"));
	    	  }else{
	    		  s.setAlamat3("");
	    	  }	    	  
	    	  s.setPoskod(rs.getString("Poskod"));
	    	  s.setIdNegeri(rs.getLong("Id_negeri"));
	    	  s.setIdDaerah(rs.getLong("Id_daerah"));
	    	  
	    	  if(rs.getString("no_tel") != null){
	    		  s.setNoTel(rs.getString("no_tel"));
	    	  }else{
	    		  s.setNoTel("");
	    	  }
	    	  if(rs.getString("no_fax") != null){
	    		  s.setNoFax(rs.getString("no_fax"));
	    	  }else{
	    		  s.setNoFax("");
	    	  }
		  
	      }
	      return s;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	}
    
   
 }
