package ekptg.model.htp.pajakan;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.entities.Tblhtpjemaahmenteri;
import ekptg.model.entities.Tblhtppermohonan;
import ekptg.model.htp.entity.PajakanUlasan;

public class PajakanMJMBean implements IPajakanMJM {
	
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.pajakan.PajakanMJMBean.class);
	private PajakanUlasan ulasan = null;
	private static SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public PajakanUlasan getMaklumatUlasan(String idUlasanKJP) throws Exception{
		Db db = null;
		String sql = "";		
		try{
			ulasan = new PajakanUlasan();
			db = new Db();
			Statement stmt = db.getStatement();			
			sql = "SELECT A.ID_ULASANKJP, A.ID_PERMOHONAN, A.TARIKH_HANTAR, A.NO_RUJUKAN, ";
			sql += "A.TARIKH_TERIMA, A.ULASAN, A.STATUS_KEPUTUSAN ";
			sql += "FROM TBLHTPULASANKJP A ";
			sql += "WHERE A.ID_ULASANKJP = " + idUlasanKJP;		
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				ulasan.setIdUlasan(rs.getLong("ID_ULASANKJP"));
	   			ulasan.setNo(Utils.isNull(rs.getString("NO_RUJUKAN")));
    			ulasan.setTarikhHantarTxt(rs.getDate("TARIKH_HANTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR")));
    			ulasan.setTarikhTerima(rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
    			ulasan.setKeputusan(Utils.isNull(rs.getString("STATUS_KEPUTUSAN")));
    			ulasan.setUlasan(Utils.isNull(rs.getString("ULASAN"))); 
    			ulasan.setIdPermohonan(rs.getLong("ID_PERMOHONAN"));
		
			}
			
		} finally {
			if (db != null) db.close();
		}
		return ulasan;
	}	
	
	public Tblhtpjemaahmenteri getMaklumatMemorandumJemaahMenteri(String idPermohonan) throws Exception{
		Db db = null;
		String sql = "";
		Tblhtpjemaahmenteri memoJemaahMenteri = new Tblhtpjemaahmenteri();	
		try{
			db = new Db();			
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_JEMAAHMENTERI, A.ID_PERMOHONAN" +
				" ,NVL(TO_CHAR(A.TARIKH_HANTAR_DASAR,'dd/mm/yyyy'),'01/01/1900') TARIKH_HANTAR_DASAR";			
			sql += " ,NVL(TO_CHAR(A.TARIKH_TERIMA,'dd/mm/yyyy'),'01/01/1900') TARIKH_TERIMA";			
			sql += " ,NVL(TO_CHAR(A.TARIKH_HANTAR_KSU,'dd/mm/yyyy'),'01/01/1900') TARIKH_HANTAR_KSU";			
			sql += " ,NVL(TO_CHAR(A.TARIKH_MSYRT_JEMAAH,'dd/mm/yyyy'),'01/01/1900') TARIKH_MSYRT_JEMAAH";
			sql += " ,A.TARIKH_MSYRT_JEMAAH TARIKH_MSYRT_JEMAAH_";
			sql += " ,NVL(TO_CHAR(A.TARIKH_TERIMA_KSU,'dd/mm/yyyy'),'01/01/1900') TARIKH_TERIMA_KSU";			
			sql += " ,NVL(TO_CHAR(A.TARIKH_HANTAR_PEMOHON,'dd/mm/yyyy'),'01/01/1900') TARIKH_HANTAR_PEMOHON";
			sql += " ,A.NO_MEMORANDUM,A.STATUS_KEPUTUSAN, A.TINDAKAN_LANJUT ";
			sql += " FROM TBLHTPJEMAAHMENTERI a ";
			sql += " WHERE A.ID_PERMOHONAN = " + idPermohonan;
			//myLog.info("getMaklumatMemorandumJemaahMenteri:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				memoJemaahMenteri = new Tblhtpjemaahmenteri();
				Tblhtppermohonan pp = new Tblhtppermohonan();
				memoJemaahMenteri.setIdJemaahmenteri(rs.getLong("ID_JEMAAHMENTERI"));
				memoJemaahMenteri.setTarikhHantarDasarStr(rs.getString("TARIKH_HANTAR_DASAR"));
				pp.setIdPermohonan(rs.getLong("ID_PERMOHONAN"));
				memoJemaahMenteri.setTblhtppermohonan(pp);
				memoJemaahMenteri.setTarikhTerimaStr(rs.getString("TARIKH_TERIMA"));
				memoJemaahMenteri.setTarikhHantarKsuStr(rs.getString("TARIKH_HANTAR_KSU"));
				memoJemaahMenteri.setTarikhMesyuaratJemaahStr(rs.getString("TARIKH_MSYRT_JEMAAH"));
				//myLog.info(memoJemaahMenteri.getTarikhMesyuaratJemaahFormat());
				//memoJemaahMenteri.setTarikhMsyrtJemaah(rs.getDate("TARIKH_MSYRT_JEMAAH_"));
				memoJemaahMenteri.setTarikhKeputusanStr(rs.getString("TARIKH_TERIMA_KSU"));
				memoJemaahMenteri.setNoMemorandum(Utils.isNull(rs.getString("NO_MEMORANDUM")));
				memoJemaahMenteri.setStatusKeputusan(Utils.isNull(rs.getString("STATUS_KEPUTUSAN")));
				memoJemaahMenteri.setTindakanLanjut(Utils.isNull(rs.getString("TINDAKAN_LANJUT")));
				memoJemaahMenteri.setTarikhHantarPemohonStr(rs.getString("TARIKH_HANTAR_PEMOHON"));
				
			}			
			

		} finally {
			if (db != null)
				db.close();
		}
		return memoJemaahMenteri;
		
	}	

	
	
}
