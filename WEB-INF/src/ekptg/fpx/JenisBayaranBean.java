package ekptg.fpx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import lebah.db.Db;

public class JenisBayaranBean implements IJenisBayaran {
	@Override
	public JenisBayaran getJenisBayaran(String idJenisBayaran){
		Db db = null;
		Connection conn = null;
		JenisBayaran jenisBayaran = null;
		 try
		 {
		   db = new Db();
		   conn = db.getConnection();
		   String sql ="SELECT ID_JENISBAYARAN,KETERANGAN, KOD_JENIS_BAYARAN FROM TBLRUJJENISBAYARAN WHERE ID_JENISBAYARAN="+idJenisBayaran;
		   Statement stmt = db.getStatement();
		   ResultSet rs = stmt.executeQuery(sql);
		   if(rs.next()){
			   jenisBayaran = new JenisBayaran();
			   jenisBayaran.setCode(rs.getString("KOD_JENIS_BAYARAN"));
			   jenisBayaran.setId(rs.getString("ID_JENISBAYARAN"));
			   jenisBayaran.setName(rs.getString("KETERANGAN"));
		   }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 finally{
			 db.close();
		 }
		 return jenisBayaran;
	}
}
