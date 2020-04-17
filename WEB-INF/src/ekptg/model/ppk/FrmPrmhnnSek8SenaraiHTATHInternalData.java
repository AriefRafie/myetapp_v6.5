package ekptg.model.ppk;



import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmPrmhnnSek8SenaraiHTATHInternalData {
	
	private  Vector list = new Vector();
	
	public void setDataHta(String id) throws Exception{
		Db db = null;
		list.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("hta.id_Hta");
			r.add("hta.id_Negeri");
			r.add("hta.id_Daerah");
			r.add("hta.id_Mukim");
			r.add("hta.no_Hakmilik");
			r.add("hta.no_Pt");
			r.add("hta.ba_Simati");
			r.add("hta.bb_Simati");
			r.add("hta.nilai_hta_tarikhmohon");
			r.add("hta.nilai_hta_tarikhmati");
			r.add("n.nama_Negeri");
			r.add("d.nama_Daerah");
			r.add("m.nama_Mukim");
			r.add("sm.id_Simati");
			r.add("ms.id_Permohonan");
			r.add("p.id_Permohonan");			
			r.add("hta.id_Negeri",r.unquote("n.id_Negeri(+)"));
			r.add("hta.id_Daerah",r.unquote("d.id_Daerah(+)"));
			r.add("hta.id_Mukim",r.unquote("m.id_Mukim(+)"));						
			r.add("p.id_Permohonan",r.unquote("ms.id_Permohonan"));
			r.add("sm.id_Simati",r.unquote("ms.id_Simati"));			
			r.add("p.id_Permohonan",id);
			
			r.add("hta.id_Simati",r.unquote("sm.id_Simati(+)"));
			r.add("hta.id_hta",r.unquote("hta1.id_hta"));
			r.add("hta.id_permohonansimati",r.unquote("hta1.id_permohonansimati"));
			r.add("hta1.id_permohonansimati",r.unquote("ms.id_permohonansimati"));
			
			sql = r.getSQLSelect("Tblppkpermohonan p," +
					" Tblppkhta hta1,Tblppkhtapermohonan hta, " +
					" Tblrujnegeri n, Tblrujdaerah d, Tblrujmukim m, " +
					" Tblppksimati sm, Tblppkpermohonansimati ms");
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Hta", rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
				h.put("id_Negeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
				h.put("id_Simati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("id_Daerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
				h.put("id_Mukim",rs.getString("id_Mukim")==null?"":rs.getString("id_Mukim"));
				h.put("no_Hakmilik",rs.getString("no_Hakmilik")==null?"":rs.getString("no_Hakmilik"));
				h.put("no_Pt",rs.getString("no_Pt")==null?"":rs.getString("no_Pt"));
				h.put("nilai_tarikhmohon",rs.getString("nilai_hta_tarikhmohon")==null?"":rs.getDouble("nilai_hta_tarikhmohon"));
				h.put("nilai_tarikhmati",rs.getString("nilai_hta_tarikhmati")==null?"":rs.getDouble("nilai_hta_tarikhmati"));
				h.put("ba_Simati",rs.getString("ba_Simati")==null?"":rs.getString("ba_Simati"));
				h.put("bb_Simati",rs.getString("bb_Simati")==null?"":rs.getString("bb_Simati"));
				h.put("nama_Negeri",rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
				h.put("nama_Daerah",rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
				h.put("nama_Mukim",rs.getString("nama_Mukim")==null?"":rs.getString("nama_Mukim"));
				list.addElement(h);
				bil++;	
			}
			//return list;
		}finally {
			if(db != null) db.close();
		}
		}
	
	public  Vector getDataHta(){
		  return list;
	  }
	
	private  Vector listHtath = new Vector();
	public void setDataHtath(String id) throws Exception{
		Db db = null;
		listHtath.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("hta.id_Hta");
			r.add("hta.id_Negeri");
			r.add("hta.id_Daerah");
			r.add("hta.id_Mukim");
			r.add("hta.no_Perjanjian");
			r.add("hta.no_Pt");
			r.add("hta.ba_Simati");
			r.add("hta.bb_Simati");
			r.add("hta.nilai_hta_tarikhmohon");
			r.add("hta.nilai_hta_tarikhmati");
			r.add("n.nama_Negeri");
			r.add("d.nama_Daerah");
			r.add("m.nama_Mukim");
			r.add("sm.id_Simati");
			r.add("ms.id_Permohonan");
			r.add("p.id_Permohonan");
			
			r.add("hta.id_Negeri",r.unquote("n.id_Negeri(+)"));
			r.add("hta.id_Daerah",r.unquote("d.id_Daerah(+)"));
			r.add("hta.id_Mukim",r.unquote("m.id_Mukim(+)"));
			r.add("hta.id_Simati",r.unquote("sm.id_Simati(+)"));
			//r.add("sm.id_Permohonan",r.unquote("p.id_Permohonan(+)"));
			
			r.add("p.id_Permohonan",r.unquote("ms.id_Permohonan"));
			r.add("sm.id_Simati",r.unquote("ms.id_Simati"));
			
			r.add("p.id_Permohonan",id);
			
			r.add("hta1.id_hta",r.unquote("hta.id_hta"));
			r.add("hta1.id_permohonansimati",r.unquote("hta.id_permohonansimati"));
			r.add("hta1.id_permohonansimati",r.unquote("ms.id_permohonansimati"));
			
			
			sql = r.getSQLSelect("Tblppkpermohonan p, Tblppkhta hta1," +
					" Tblppkhtapermohonan hta, " +
					" Tblrujnegeri n, Tblrujdaerah d, Tblrujmukim m, " +
					" Tblppksimati sm, Tblppkpermohonansimati ms");
			ResultSet rs = stmt.executeQuery(sql);
			//Vector list = new Vector(;
			Hashtable h;
			int bil = 1;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Hta", rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
				h.put("id_Negeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
				h.put("id_Simati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("id_Daerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
				h.put("id_Mukim",rs.getString("id_Mukim")==null?"":rs.getString("id_Mukim"));
				h.put("no_Perjanjian",rs.getString("no_Perjanjian")==null?"":rs.getString("no_Perjanjian"));
				h.put("no_Pt",rs.getString("no_Pt")==null?"":rs.getString("no_Pt"));
				h.put("ba_Simati",rs.getString("ba_Simati")==null?"":rs.getString("ba_Simati"));
				h.put("bb_Simati",rs.getString("bb_Simati")==null?"":rs.getString("bb_Simati"));
				h.put("nilai_tarikhmohon",rs.getString("nilai_hta_tarikhmohon")==null?"":Double.parseDouble(rs.getString("nilai_hta_tarikhmohon")));
				h.put("nilai_tarikhmati",rs.getString("nilai_hta_tarikhmati")==null?"":Double.parseDouble(rs.getString("nilai_hta_tarikhmati")));		
				h.put("nama_Negeri",rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
				h.put("nama_Daerah",rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
				h.put("nama_Mukim",rs.getString("nama_Mukim")==null?"":rs.getString("nama_Mukim"));
				
				listHtath.addElement(h);
				bil++;	
			}
			//return list;
		}finally {
			if(db != null) db.close();
		}
		}
	
	public  Vector getHtath(){
		  return listHtath;
	  }
	
	private  Vector listHa2 = new Vector();
	public void setDataHa(String id) throws Exception{
		Db db = null;
		listHa2.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("hta.id_Hta");
			r.add("hta.id_Negeri");
			r.add("hta.id_Daerah");
			r.add("hta.id_Mukim");
			r.add("hta.no_Perjanjian");
			r.add("hta.no_Pt");
			r.add("hta.ba_Simati");
			r.add("hta.bb_Simati");
			r.add("hta.nilai_hta_tarikhmohon");
			r.add("hta.nilai_hta_tarikhmati");
			r.add("n.nama_Negeri");
			r.add("d.nama_Daerah");
			r.add("m.nama_Mukim");
			r.add("sm.id_Simati");
			r.add("ms.id_Permohonan");
			r.add("p.id_Permohonan");
			
			r.add("hta.id_Negeri",r.unquote("n.id_Negeri(+)"));
			r.add("hta.id_Daerah",r.unquote("d.id_Daerah(+)"));
			r.add("hta.id_Mukim",r.unquote("m.id_Mukim(+)"));
			r.add("hta.id_Simati",r.unquote("sm.id_Simati(+)"));
			//r.add("sm.id_Permohonan",r.unquote("p.id_Permohonan(+)"));
			
			r.add("p.id_Permohonan",r.unquote("ms.id_Permohonan"));
			r.add("sm.id_Simati",r.unquote("ms.id_Simati"));
			
			r.add("p.id_Permohonan",id);
			
			r.add("hta1.id_hta",r.unquote("hta.id_hta"));
			r.add("hta1.id_permohonansimati",r.unquote("hta.id_permohonansimati"));
			r.add("hta1.id_permohonansimati",r.unquote("ms.id_permohonansimati"));
			
			sql = r.getSQLSelect("Tblppkpermohonan p, Tblppkhta hta1, " +
					" Tblppkhtapermohonan hta," +
					"Tblrujnegeri n, Tblrujdaerah d, Tblrujmukim m, Tblppksimati sm, Tblppkpermohonansimati ms");
			ResultSet rs = stmt.executeQuery(sql);
			////System.out.println("sqlppkha-->"+sql);
			//Vector list = new Vector(;
			Hashtable h;
			int bil = 1;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Hta", rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
				h.put("id_Negeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
				h.put("id_Simati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("id_Daerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
				h.put("id_Mukim",rs.getString("id_Mukim")==null?"":rs.getString("id_Mukim"));
				h.put("no_Perjanjian",rs.getString("no_Perjanjian")==null?"":rs.getString("no_Perjanjian"));
				h.put("no_Pt",rs.getString("no_Pt")==null?"":rs.getString("no_Pt"));
				h.put("ba_Simati",rs.getString("ba_Simati")==null?"":rs.getString("ba_Simati"));
				h.put("bb_Simati",rs.getString("bb_Simati")==null?"":rs.getString("bb_Simati"));
				h.put("nilai_tarikhmohon",rs.getString("nilai_hta_tarikhmohon")==null?"":rs.getDouble("nilai_hta_tarikhmohon"));
				h.put("nilai_tarikhmati",rs.getString("nilai_hta_tarikhmati")==null?"":rs.getDouble("nilai_hta_tarikhmati"));		
				h.put("nama_Negeri",rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
				h.put("nama_Daerah",rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
				h.put("nama_Mukim",rs.getString("nama_Mukim")==null?"":rs.getString("nama_Mukim"));
				//System.out.println("listha2"+h);
				listHa2.addElement(h);
				bil++;	
			}
			//return list;
		}finally {
			if(db != null) db.close();
		}
		}
	
	public  Vector getHa2(){
		  return listHa2;
	  }
}
