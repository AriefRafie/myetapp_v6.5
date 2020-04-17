package ekptg.model.online.aduan.setup;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.entities.Tblrujseksyen;
import ekptg.model.online.aduan.entity.ComplaintCategory;
import ekptg.model.online.aduan.entity.ComplaintTindakan;

public abstract class ComplaintCategoryFacade {
	public Vector<ComplaintCategory> getComplaintCategory(){
		Vector<ComplaintCategory> v = new Vector<ComplaintCategory>();
		Db db = null;
		try{
			db = new Db();
			Statement stat = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_KATEGORIADUAN");
			r.add("KOD_KATEGORI");
			r.add("NAMA_KATEGORI");
			String sql = r.getSQLSelect("TBLONLINEADUANKATEGORI");
			//
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				ComplaintCategory category = new ComplaintCategory();
				category.setId(rs.getLong("ID_KATEGORIADUAN"));
				category.setKod(rs.getString("KOD_KATEGORI"));
				category.setName(rs.getString("NAMA_KATEGORI"));
				v.addElement(category);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return v;
	}
	
	public ComplaintCategory getCategory(String idCategory){
		Db db = null;
		ComplaintCategory category  = null;
		try{
			db = new Db();
			Statement stat = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_KATEGORIADUAN");
			r.add("KOD_KATEGORI");
			r.add("NAMA_KATEGORI");
			r.set("ID_KATEGORIADUAN", idCategory);
			String sql = r.getSQLSelect("TBLONLINEADUANKATEGORI");
			//
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()){
				category = new ComplaintCategory();
				category.setId(Long.parseLong(idCategory));
				category.setKod(rs.getString("KOD_KATEGORI"));
				category.setName(rs.getString("NAMA_KATEGORI"));
				
			}
			Vector<ComplaintTindakan> actions = new Vector<ComplaintTindakan>();
			ResultSet rsx = stat.executeQuery("select * from TBLONLINEADUANTINDAKAN A,TBLRUJNEGERI B WHERE A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_KATEGORIADUAN="+category.getId());
			while(rsx.next()){
				ComplaintTindakan tindakan = new ComplaintTindakan();
				Tblrujnegeri negeri = new Tblrujnegeri();
				negeri.setIdNegeri(rsx.getLong("ID_NEGERI"));
				Tblrujseksyen seksyen = new Tblrujseksyen();
				tindakan.setId(rsx.getLong("ID_ADUANTINDAKAN"));
				tindakan.setNegeri(negeri);
				tindakan.setSeksyen(seksyen);
				tindakan.setKod(rsx.getString("KOD_TINDAKAN"));
				tindakan.setName(rsx.getString("NAMA_TINDAKAN"));
				tindakan.setGroupEmail(rsx.getString("GROUP_EMEL"));
				actions.addElement(tindakan);
			}
			category.setActions(actions);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return category;
	}
	
	public void saveTindakan(ComplaintTindakan tindakan){
		Db db = null;
		try{
			db = new Db();
			Statement stat = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("NAMA_TINDAKAN",tindakan.getName());
			r.add("ID_RUJSEKSYEN",tindakan.getSeksyen().getIdSeksyen());
			r.add("ID_NEGERI",tindakan.getNegeri().getIdNegeri());
			r.add("ID_KATEGORIADUAN",tindakan.getCategory().getId());
			r.add("GROUP_EMEL",tindakan.getGroupEmail());
			String sql = r.getSQLInsert("TBLONLINEADUANTINDAKAN");
			//
			stat.executeUpdate(sql);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
	}
}
