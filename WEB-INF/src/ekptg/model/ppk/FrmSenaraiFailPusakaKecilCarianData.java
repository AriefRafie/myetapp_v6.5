package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmSenaraiFailPusakaKecilCarianData {

	private static Vector list = new Vector();
	public static void  setCarianFail(String noFail, String namaPemohon, String namaSimati, String icSimati, String JenisIc)throws Exception {
	    Db db = null;
	    list.clear();
	    String sql = "";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      String chkDataFail = noFail.trim();
	      String chkDataPemohon = namaPemohon.trim();
	      String chkDataSimati = namaSimati.trim();
	      String chkDataIcSimati = icSimati.trim();
	      String chkDataJenisKp = JenisIc;
	      sql = "Select f.id_fail,f.no_fail,f.tarikh_daftar_fail,a.id_permohonan,a.tarikh_Mohon,a.tarikh_Masuk,s.keterangan,p.id_simati,p.nama_simati,pp.nama_pemohon "+
	      	"from Tblppkpermohonan a, Tblpfdfail f, Tblrujstatus s, Tblppksimati p, Tblppkpemohon pp "+
	      	"where a.id_status = s.id_status(+) and a.id_fail = f.id_fail(+) and a.id_permohonan = pp.id_permohonan(+) and p.id_permohonan = a.id_permohonan";
	      
	      //NO FAIL
	      if (noFail != "") {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%" + chkDataFail.toUpperCase() + "%'";
				}
			}
	    //NAMA PEMOHON
	      if (namaPemohon != "") {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(PP.NAMA_PEMOHON) LIKE '%" + chkDataPemohon.toUpperCase() + "%'";
				}
			}
	    //NAMA SIMATI
	      if (namaSimati != "") {
				if (!namaSimati.trim().equals("")) {
					sql = sql + " AND UPPER(P.NAMA_SIMATI) LIKE '%" + chkDataSimati.toUpperCase() + "%'";
				}
			}
	    //IC SIMATI
	      if (icSimati != "") {
			   if (!icSimati.trim().equals("")) {
					if (chkDataJenisKp.equals("1")){
						sql = sql + " AND UPPER(P.NO_KP_BARU) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
					}
					else if (chkDataJenisKp.equals("2")){
						sql = sql + " AND UPPER(P.NO_KP_LAMA) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
					}
					else if (chkDataJenisKp.equals("3")){
						sql = sql + " AND UPPER(P.N0_KP_LAIN) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
					}
		    	}
			}  
	      sql = sql + " AND f.no_fail is not null ORDER BY F.ID_FAIL DESC";
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  	h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Fail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
				h.put("no_Fail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
				h.put("tarikh_Mohon", rs.getString("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("tarikhMasuk", rs.getString("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
				h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("id_simati", rs.getString("id_Simati"));
				list.addElement(h);
	    	  bil++;
	    	  count++;  
	      }
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil","");
				h.put("id_Permohonan", "");
				h.put("id_Fail", "");
				h.put("no_Fail", "");
				h.put("tarikh_Mohon","");
				h.put("tarikhMasuk","");
				h.put("keterangan","");
				h.put("id_simati", "");
	    	  list.addElement(h);
	      }
	    } finally {
	      if (db != null) db.close();
	    }
	}
	 public static Vector getList(){
		  return list;
	  }
	
}
