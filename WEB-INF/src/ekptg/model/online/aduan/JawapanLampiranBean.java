package ekptg.model.online.aduan;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import lebah.db.Db;
import ekptg.model.online.aduan.entity.OnlineLampiran;

public class JawapanLampiranBean implements IJawapanLampiran {
	@Override
	public Vector<OnlineLampiran> getLampiranByRecord(String idRekod){
		Db db = null;
		Vector<OnlineLampiran> v = new Vector<OnlineLampiran>();
		try{
			db = new Db();
			Statement stat = db.getStatement();
			String sql = "SELECT ID_ONLINELAMPIRAN,ID_REKOD,JENIS_MIME,CONTENT,NAMA_FAIL,REFERENCETABLE FROM TBLONLINELAMPIRAN WHERE ID_REKOD="+idRekod;
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				OnlineLampiran lampiran = new OnlineLampiran();
				lampiran.setId(rs.getLong("ID_ONLINELAMPIRAN"));
				lampiran.setIdRekod(rs.getString("ID_REKOD"));
				lampiran.setFileName(rs.getString("NAMA_FAIL"));
				lampiran.setContent(rs.getString("CONTENT"));
				v.addElement(lampiran);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		
		return v;
	}
}
