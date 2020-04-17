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

/**
 * @author aida
 *
 */
public class FrmPrmhnnSek8SenaraiWarisLapisanBerikutData {
	private static Vector listWarisLapisan = new Vector();
	public static void setList() throws Exception {
		Db db = null;
		listWarisLapisan.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.umur");
			r.add("ob.id_Saudara");
			r.add("ob2.nama_Ob");
			r.add("ob.status_Hidup");
			r.add("ob.lapis");
			r.add("ob.id_Simati");
			r.add("sm.id_Simati");
			r.add("sm.id_Permohonan");
			r.add("a.id_Permohonan");
			r.add("s.id_Saudara");
			r.add("s.keterangan");
			
			r.add("ob.id_Simati",r.unquote("sm.id_Simati(+)"));
			r.add("ob.id_Saudara",r.unquote("s.id_Saudara(+)"));
			r.add("sm.id_Permohonan",r.unquote("a.id_Permohonan(+)"));
			
			
			sql = r.getSQLSelect("Tblppkpermohonan a, Tblpfdfail f, Tblrujstatus s");
			ResultSet rs = stmt.executeQuery(sql);
			
			//Vector list = new Vector(;
			Hashtable h;
			int bil = 1;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("no_Kp_Baru", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"));
				h.put("umur", rs.getString("umur")==null?"":rs.getString("umur"));
				h.put("nama_Ob2", rs.getString("nama_Ob")==null?"":rs.getString("nama_Ob2"));
				h.put("status_Hidup",rs.getString("status_Hidup")==null?"":rs.getString("status_Hidup"));
				h.put("lapis",rs.getString("lapis")==null?"":rs.getString("lapis"));
				h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				listWarisLapisan.addElement(h);
				bil++;	
			}
			//return list;
		}finally {
			if(db != null) db.close();
			
		}
		}
	public static Vector getList(){
		return listWarisLapisan;
	}
}
