package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmRekodTukargunaPenawaranPelepasanData {
	
	private static Vector listFail = null;
	
	// PAPAR SENARAI FAIL
	public static void setPaparSenaraiFail() throws Exception {

		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			db = new Db();
			listFail = new Vector();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
				
			r.add("a.id_Hakmilik");
			r.add("c.no_Fail");
			r.add("c.tarikh_Masuk");
			r.add("d.nama_urusan");
			r.add("e.nama_kementerian");
			r.add("a.id_Permohonan",r.unquote("b.id_Permohonan"));
			r.add("b.id_Fail",r.unquote("c.id_Fail"));
			r.add("c.id_urusan",r.unquote("d.id_urusan(+)"));
			r.add("a.id_Kementerian",r.unquote("e.id_Kementerian(+)"));
			
			sql = r.getSQLSelect("Tblhtphakmilik a, Tblpermohonan b, Tblpfdfail c, Tblrujurusan d, Tblrujkementerian e");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
		    int bil = 1;
		    int count = 0;
			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idHakmilik", rs.getString("id_Hakmilik"));
				h.put("noFail", rs.getString("no_Fail")==null ? "" :rs.getString("no_Fail"));
				h.put("tarikhMasuk", rs.getString("tarikh_Masuk")==null ? "" :sdf.format(rs.getDate("tarikh_Masuk")));
			    h.put("namaUrusan", rs.getString("nama_urusan")==null ? "" :rs.getString("nama_urusan"));
				h.put("namaKementerian", rs.getString("nama_kementerian")==null ? "" :rs.getString("nama_kementerian"));
				listFail.addElement(h);
		    	bil++;
		    	count++;
				
			}
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil","");
		    	  h.put("idHakmilik","");
		    	  h.put("noFail", "Tiada rekod.");
		    	  h.put("tarikhMasuk", "");
		    	  h.put("namaUrusan", "");
		    	  h.put("namaKementerian", "");

		    	  listFail.addElement(h);
		      }
		} finally {
			if (db != null)
				db.close();
		}
	}
	public static Vector getPaparSenaraiFail() {
		return listFail;
	}	
}
