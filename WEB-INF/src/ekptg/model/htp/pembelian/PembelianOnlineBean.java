package ekptg.model.htp.pembelian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.model.htp.FrmSenaraiFailTerimaPohonData;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;

public class PembelianOnlineBean implements IPembelianOnline {
	static Logger myLog = Logger.getLogger(ekptg.model.htp.pembelian.PembelianBean.class);
	private static final String KOD_JABATAN ="JKPTG";
	private static String KODKEMENTERIAN ="00";
	private static final long IDTARAFKESELAMTAN = 1;
	private static final int IDSEKSYEN = 3;
	private static final int IDURUSAN = 2;
	PfdFail fail = null;
	Permohonan permohonan = null;
	HtpPermohonan htpPermohonan = null;
	private FrmSenaraiFailTerimaPohonData senaraiFail = null;


	@Override
	public Vector findFailKJP(String carian, String noFail, String idNegeri
			,String idKementerian,String idAgensi, String idSuburusan, String idStatus) {
		Db db = null;
		Vector<HtpPermohonan> v = new Vector();
		
		if (idSuburusan.equals("-1")){
			idSuburusan = "";
		}
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "" +
				"SELECT distinct F.ID_FAIL,NVL(LTRIM(RTRIM(f.no_Fail)),'TIADA') NO_FAIL" +
				" ,F.TAJUK_FAIL,P.ID_PERMOHONAN,P.TUJUAN,P.NO_PERMOHONAN " +
				" ,H.ID_HTPPERMOHONAN, n.nama_Negeri, n.kod_Mampu,n.id_Negeri,s.keterangan " +
				" ,F.TARIKH_KEMASKINI,F.TARIKH_MASUK "+
				" FROM Tblpfdfail F, Tblpermohonan P, Tblrujsuburusanstatusfail SF " +
				" ,Tblrujsuburusanstatus SS, Tblrujstatus S, Tblrujnegeri N, tblhtppermohonan H "+
				" WHERE F.id_Fail = P.id_Fail AND H.id_Permohonan = P.id_Permohonan  " +
				" AND P.id_Permohonan = SF.id_Permohonan AND P.id_Fail = SF.id_Fail" +
				" AND N.id_Negeri = F.id_Negeri AND sf.aktif = '1' AND f.id_Urusan = 2 " +
				" AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status " +
				" AND f.ID_STATUS <> 999 " +
				" AND f.no_Fail LIKE '%"+noFail+"%' " +
				" AND f.tajuk_Fail LIKE '%"+carian+"%' "+
				"";
			if(idKementerian != null && !idKementerian.equals("") && !idKementerian.equals("0"))
				sql +=" AND F.ID_KEMENTERIAN = "+idKementerian;
			if(idNegeri != null && !idNegeri.equals("") && !idNegeri.equals("0"))
				sql +=" AND F.ID_NEGERI = "+idNegeri;
			if(idAgensi != null && !idAgensi.equals("") && !idAgensi.equals("0"))
				sql +=" AND H.ID_AGENSI = "+idAgensi;
			if(idSuburusan != null && !idSuburusan.equals("") && !idSuburusan.equals("0"))
				sql +=" AND SS.ID_SUBURUSAN = "+idSuburusan;
			if(idStatus != null && !idStatus.equals("") && !idStatus.equals("0"))
				sql +=" AND SS.ID_STATUS = "+idStatus;
						
			//sql +=" ORDER BY F.TARIKH_KEMASKINI desc,F.ID_FAIL DESC";
			sql +=" ORDER BY F.TARIKH_MASUK DESC,F.TARIKH_KEMASKINI DESC,F.ID_FAIL DESC";
			myLog.info(sql);
			System.out.println("---- sql ----"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 1;
			while(rs.next()){
				permohonan = new Permohonan();
				fail = new PfdFail();
				htpPermohonan = new HtpPermohonan();
				
				htpPermohonan.setBil(bil);
				fail.setIdFail(rs.getLong("id_Fail"));
				permohonan.setNamaNegeri(rs.getString("nama_Negeri"));
				permohonan.setIdPermohonan(rs.getLong("id_Permohonan"));
				permohonan.setNoPermohonan(rs.getString("NO_PERMOHONAN"));
				fail.setNoFail(rs.getString("no_Fail"));
				permohonan.setTujuan(rs.getString("TUJUAN"));
				//permohonan.setTujuan(rs.getString("tajuk_Fail"));
				htpPermohonan.setIdHtpPermohonan(rs.getString("id_htppermohonan"));
				htpPermohonan.setPermohonan(permohonan);
				htpPermohonan.setStatusPermohonan(rs.getString("keterangan"));
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);
				
				v.addElement(htpPermohonan);
				bil++;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
			 if (db != null){
		    	  db.close();
		      }
		}
		return v;
	}

	@Override
	public HtpPermohonan kemaskiniPermohonan(HtpPermohonan htpPermohonan,String idPermohonan,String idhtpPermohonan)throws Exception {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String idFail = null;

		try{
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			r = new SQLRenderer();		
			permohonan = htpPermohonan.getPermohonan();
			String TarikhSurKJP = permohonan.getTarikhSurat();
			String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";
			
			String TarikhPermohonan = permohonan.getTarikhTerima();
			String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
			
			r = new SQLRenderer();
			r.update("id_Permohonan", idPermohonan);
			//r.add("id_Fail",idFail);
			r.add("id_Jkptg",1);
			//r.add("no_Permohonan","TIADA");
			r.add("no_Perserahan","TIADA");
			r.add("tarikh_Surat", r.unquote(TSKJP));
			r.add("tarikh_Terima", r.unquote(TP));
			r.add("tujuan",permohonan.getTujuan());
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			myLog.info("TBLPERMOHONAN:sql="+sql);
			stmt.executeUpdate(sql);
			
			//long idHtpPermohonan = DB.getNextID("TBLHTPPERMOHONAN_SEQ");
			htpPermohonan.getIdHtpPermohonan();
			r = new SQLRenderer();
			r.update("id_Htppermohonan",idhtpPermohonan);
			r.update("id_Permohonan",idPermohonan);
			r.add("id_Agensi", htpPermohonan.getIdAgensi());
			r.add("id_Jenistanah", htpPermohonan.getIdJenisTanah());
			r.add("id_Pegawai", htpPermohonan.getIdPegawai());
			r.add("no_Rujukan_KJP",htpPermohonan.getNoRujukanKJP());
			r.add("no_Rujukan_Lain", htpPermohonan.getNoRujukanLain());
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			//r.add("tarikh_Agihan", r.unquote(tarikhDaftarFail));
			sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
			//
			myLog.info("TBLHTPPERMOHONAN:sql="+sql);
			stmt.executeUpdate(sql);
			
			r = new SQLRenderer();
			r.add("ID_FAIL");
			r.add("ID_PERMOHONAN",idPermohonan);				      
			sql = r.getSQLSelect("TBLPERMOHONAN");
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				idFail = rs.getString("ID_FAIL");
			}
			
			r = new SQLRenderer();
			r.update("ID_FAIL",idFail);
			r.add("TAJUK_FAIL", permohonan.getTujuan());
			r.add("ID_KEMASKINI", permohonan.getIdMasuk());
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("TBLPFDFAIL");
			//
			stmt.executeUpdate(sql);		
			conn.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
			if(db != null)	db.close();
		}
		return htpPermohonan;
		
	}	

	
}
