package ekptg.model.online.aduan;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public abstract class ComplaintSourceFacade {
	public ComplaintSource getComplaintSource(String code){
		Db db = null;
		ComplaintSource source = null;
		try{
			db = new Db();
			Statement stat = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_SUMBERADUAN");
			r.add("NAMA_SUMBER");
			r.add("KOD_SUMBER");
			r.set("KOD_SUMBER", code);
			String sql = r.getSQLSelect("TBLRUJSUMBERADUAN");
			//
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()){
				source = new ComplaintSource();
				source.setId(rs.getLong("ID_SUMBERADUAN"));
				source.setCode(code);
				source.setDescription(rs.getString("NAMA_SUMBER"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return source;
	}
	public ComplaintSource getComplaintSource(long id){
		Db db = null;
		ComplaintSource source = null;
		try{
			db = new Db();
			Statement stat = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_SUMBERADUAN",id);
			r.add("KOD_SUMBER");
			r.add("NAMA_SUMBER");
			String sql = r.getSQLSelect("TBLRUJSUMBERADUAN");
			//
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()){
				source = new ComplaintSource();
				source.setId(id);
				source.setCode(rs.getString("KOD_SUMBER"));
				source.setDescription(rs.getString("NAMA_SUMBER"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return source;
	}
	public Vector<ComplaintSource> getList(){
		Db db = null;
		ComplaintSource source = null;
		Vector<ComplaintSource> v = new Vector<ComplaintSource>();
		try{
			db = new Db();
			Statement stat = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_SUMBERADUAN");
			r.add("KOD_SUMBER");
			r.add("NAMA_SUMBER");
			String sql = r.getSQLSelect("TBLRUJSUMBERADUAN");
			//
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				source = new ComplaintSource();
				source.setId(rs.getLong("ID_SUMBERADUAN"));
				source.setCode(rs.getString("KOD_SUMBER"));
				source.setDescription(rs.getString("NAMA_SUMBER"));
				v.addElement(source);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return v;
	}
}
