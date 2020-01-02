package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class FrmGadaianPelepasanMaklumatPeminjam {
	private static Vector peminjam = new Vector();
	private static Logger log = Logger.getLogger(FrmGadaianPelepasanMaklumatPeminjam.class);
	
	public static void InfoPeminjam(String idFail, String idPermohonan){
		Db db = null;
	    String sql;
	    peminjam.clear();

	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();

	      sql = "select f.id_fail, P.ID_PERMOHONAN , PK.NAMA , PK.ALAMAT1,PK.ALAMAT2,PK.ALAMAT3, ";
	      sql += " PK.POSKOD, PK.NO_TEL, PK.NO_FAX, HTPP.NO_RUJUKAN_LAIN, ";
	      sql += " N.NAMA_NEGERI, D.NAMA_DAERAH, HU.NO_HAKMILIK, HU.NO_LOT, L.KETERANGAN as nama_lot,";
	      sql += " HK.KETERANGAN as jenis_hakmilik, B.NO_PERSERAHAN ";
	      sql += " from tblpfdfail f,TBLHTPFAILMAPPING FP,TBLPERMOHONAN P,TBLHTPHAKMILIKURUSAN HU,TBLHTPPIHAKBERKEPENTINGAN PK,";
	      sql += "  tblhtppermohonan htpp, tblrujdaerah d, tblrujnegeri n, tblrujlot l, tblrujjenishakmilik hk,  tblhtpbebanan b";
	      sql += " where f.id_fail=" + idFail;
	      sql += " AND FP.ID_FAIL=F.ID_FAIL ";
	      sql += " AND FP.ID_FAILLAMA =P.ID_FAIL ";
	      sql += " AND P.ID_PERMOHONAN="+idPermohonan;
	      sql += " AND P.ID_PERMOHONAN=HU.ID_PERMOHONAN ";
	      sql += " AND P.ID_PERMOHONAN=htpp.ID_PERMOHONAN ";
	      sql += " AND PK.ID_HAKMILIKURUSAN=HU.ID_HAKMILIKURUSAN ";
	      sql += " AND PK.ID_DAERAH = D.ID_DAERAH ";
	      sql += " AND PK.ID_NEGERI = N.ID_NEGERI ";
	      sql += " AND HU.ID_LOT = L.ID_LOT ";
	      sql += " AND HU.ID_JENISHAKMILIK = HK.ID_JENISHAKMILIK ";
	      sql += " AND PK.ID_PIHAKBERKEPENTINGAN = B.ID_PIHAKBERKEPENTINGAN ";

			log.info("InfoPeminjam("+idFail+","+idPermohonan+"):sql="+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	    
	     
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idFail", rs.getString("id_Fail"));
	    	  h.put("idPermohonan", rs.getString("id_permohonan"));
	    	  h.put("nama", rs.getString("nama"));
	    	  h.put("alamat1", rs.getString("alamat1"));
	    	  h.put("alamat2", rs.getString("alamat2"));
	    	  //h.put("alamat3", rs.getString("alamat3"));
	    	  //rosli 20100302
	    	  h.put("alamat3",rs.getString("alamat3")== null?"":rs.getString("alamat3"));
	    	  h.put("poskod", rs.getString("poskod"));
	    	  //h.put("noTel", rs.getString("no_tel"));
	    	  //h.put("noFax", rs.getString("no_fax"));
	    	  //rosli 20100302
	     	  h.put("noTel",rs.getString("no_tel")== null?"":rs.getString("no_tel"));
	    	  h.put("noFax",rs.getString("no_fax")== null?"":rs.getString("no_fax"));
	    	  h.put("namaNegeri", rs.getString("nama_negeri"));
	    	  h.put("namaDaerah", rs.getString("nama_daerah"));
	    	  h.put("noHakmilik", rs.getString("no_hakmilik"));
	    	  h.put("noLot", rs.getString("no_lot"));
	    	  h.put("namaLot", rs.getString("nama_lot"));
	    	  h.put("jenisHakmilik", rs.getString("jenis_hakmilik"));
	    	  h.put("noPerserahan", rs.getString("no_perserahan"));
	    	  peminjam.addElement(h);
	    	  bil++;
	      }

	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    finally {
	      if (db != null) {
	    	  db.close();
	      }
	    }
	}

	public Hashtable<String, Comparable> maklumatPeminjam(String idFail,String idPermohonan){
		Db db = null;
		Hashtable<String, Comparable> h = new Hashtable<String, Comparable>();
	    String sql;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();

	      sql = "SELECT f.id_fail, P.ID_PERMOHONAN , PK.NAMA , PK.ALAMAT1,PK.ALAMAT2,PK.ALAMAT3, ";
	      sql += " PK.POSKOD, PK.NO_TEL, PK.NO_FAX, HTPP.NO_RUJUKAN_LAIN, ";
	      sql += " N.NAMA_NEGERI, D.NAMA_DAERAH, HU.NO_HAKMILIK, HU.NO_LOT, L.KETERANGAN as nama_lot,";
	      sql += " HK.KETERANGAN as jenis_hakmilik, B.NO_PERSERAHAN ";
	      sql += " from tblpfdfail f,TBLHTPFAILMAPPING FP,TBLPERMOHONAN P,TBLHTPHAKMILIKURUSAN HU,TBLHTPPIHAKBERKEPENTINGAN PK,";
	      sql += "  tblhtppermohonan htpp, tblrujdaerah d, tblrujnegeri n, tblrujlot l, tblrujjenishakmilik hk,  tblhtpbebanan b";
	      sql += " where f.id_fail=" + idFail;
	      sql += " AND FP.ID_FAIL=F.ID_FAIL ";
	      sql += " AND FP.ID_FAILLAMA =P.ID_FAIL ";
	      sql += " AND P.ID_PERMOHONAN="+idPermohonan;
	      sql += " AND P.ID_PERMOHONAN=HU.ID_PERMOHONAN ";
	      sql += " AND P.ID_PERMOHONAN=htpp.ID_PERMOHONAN ";
	      sql += " AND PK.ID_HAKMILIKURUSAN=HU.ID_HAKMILIKURUSAN ";
	      sql += " AND PK.ID_DAERAH = D.ID_DAERAH ";
	      sql += " AND PK.ID_NEGERI = N.ID_NEGERI ";
	      sql += " AND HU.ID_LOT = L.ID_LOT ";
	      sql += " AND HU.ID_JENISHAKMILIK = HK.ID_JENISHAKMILIK ";
	      sql += " AND PK.ID_PIHAKBERKEPENTINGAN = B.ID_PIHAKBERKEPENTINGAN ";
	      log.info("InfoPeminjam("+idFail+","+idPermohonan+"):sql="+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idFail", Utils.isNull(rs.getString("id_Fail")));
	    	  h.put("idPermohonan", Utils.isNull(rs.getString("id_permohonan")));
	    	  h.put("nama", Utils.isNull(rs.getString("nama")));
	    	  h.put("alamat1", Utils.isNull(rs.getString("alamat1")));
	    	  h.put("alamat2", Utils.isNull(rs.getString("alamat2")));
	    	  h.put("alamat3",Utils.isNull(rs.getString("alamat3")));
	    	  h.put("poskod", Utils.isNull(rs.getString("poskod")));
	     	  h.put("noTel",Utils.isNull(rs.getString("no_tel")));
	    	  h.put("noFax",Utils.isNull(rs.getString("no_fax")));
	    	  h.put("namaNegeri", Utils.isNull(rs.getString("nama_negeri")));
	    	  h.put("namaDaerah", Utils.isNull(rs.getString("nama_daerah")));
	    	  h.put("noHakmilik", Utils.isNull(rs.getString("no_hakmilik")));
	    	  h.put("noLot", Utils.isNull(rs.getString("no_lot")));
	    	  h.put("namaLot", Utils.isNull(rs.getString("nama_lot")));
	    	  h.put("jenisHakmilik", Utils.isNull(rs.getString("jenis_hakmilik")));
	    	  h.put("noPerserahan", Utils.isNull(rs.getString("no_perserahan")));
	    	  //peminjam.addElement(h);
	    	  bil++;
	      }

	    }catch(Exception e){
	    	e.printStackTrace();
	    }	    
	    finally {
	      if (db != null) {
	    	  db.close();
	      }
	    }
	    return h;
	}
	
	public static Vector getPeminjam() {
		return peminjam;
	}

}
