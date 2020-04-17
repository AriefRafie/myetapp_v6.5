package ekptg.model.online.aduan.setup;


import java.sql.ResultSet;
import java.sql.Statement;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.model.online.aduan.entity.ComplaintCategory;
import ekptg.model.online.aduan.entity.ComplaintTindakan;

public class ComplaintTindakanBean extends ComplaintCategoryFacade implements IComplaintTindakanBean {
	public ComplaintTindakan getTindakan(String id){
		ComplaintTindakan tindakan = null;
		Db db = null;
		try{
			db = new Db();
			Statement stat = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_ADUANTINDAKAN");
			r.add("KOD_TINDAKAN");
			r.add("NAMA_TINDAKAN");
			r.add("ID_RUJSEKSYEN");
			r.add("ID_NEGERI");
			r.add("ID_KATEGORIADUAN");
			r.add("GROUP_EMEL");
			r.set("ID_ADUANTINDAKAN", id);
			String sql = r.getSQLSelect("TBLONLINEADUANTINDAKAN");
			//
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()){
				tindakan = new ComplaintTindakan();
				tindakan.setId(Long.parseLong(id));
				tindakan.setKod(rs.getString("KOD_TINDAKAN"));
				tindakan.setName(rs.getString("NAMA_TINDAKAN"));
				tindakan.setGroupEmail(rs.getString("GROUP_EMEL"));
				ComplaintCategory category = new ComplaintCategory();
				category.setId(rs.getLong("ID_KATEGORIADUAN"));
				tindakan.setCategory(category);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.close();
		}
		return tindakan;
	}
}
