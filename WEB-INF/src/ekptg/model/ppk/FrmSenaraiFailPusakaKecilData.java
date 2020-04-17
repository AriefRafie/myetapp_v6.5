/**
 * 
 */
package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
//import org.apache.xalan.xsltc.runtime.Hashtable;

public class FrmSenaraiFailPusakaKecilData {
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static Vector list = new Vector();
	public static void setList() throws Exception {
		Db db = null;
		list.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("f.id_Fail");
			r.add("f.no_Fail");
			r.add("a.id_Permohonan");
			r.add("a.tarikh_Mohon");
			r.add("a.tarikh_Masuk");
			r.add("f.tarikh_daftar_fail");
			r.add("s.keterangan");
			r.add("p.id_Simati");
			r.add("p.nama_Simati");
			
			r.add("a.id_Status",r.unquote("s.id_Status(+)"));
			r.add("a.id_Fail",r.unquote("f.id_Fail(+)"));
			r.add("a.id_Permohonan",r.unquote("p.id_Permohonan"));
			
			
			sql = r.getSQLSelect("Tblppkpermohonan a, Tblpfdfail f, Tblrujstatus s, Tblppksimati p");
			sql = sql + " AND f.no_fail is not null ORDER BY F.ID_FAIL DESC";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("testing 666666666666666=== "+sql);
			Hashtable h;
			int bil = 1;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Fail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
				h.put("no_Fail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
				h.put("tarikhmohon", rs.getDate("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("tarikhMasuk", rs.getDate("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
				h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("id_simati", rs.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
				list.addElement(h);
				bil++;	
			}
			//return list;
		}finally {
			if(db != null) db.close();
			
		}
		}
	public static Vector getList(){
		return list;
	}
}

