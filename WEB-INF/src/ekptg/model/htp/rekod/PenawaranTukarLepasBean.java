package ekptg.model.htp.rekod;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.intergration.entity.BorangK;
import ekptg.model.htp.FrmSenaraiFailTerimaPohonData;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;

public class PenawaranTukarLepasBean implements IPenawaranTukarLepas {
	private static final String KOD_JABATAN ="JKPTG";
	private static String KODKEMENTERIAN ="00";
	private static final long IDTARAFKESELAMTAN = 1;
	private static final int IDSEKSYEN = 3;
	private static final int IDURUSAN = 2;
	PfdFail fail = null;
	Permohonan permohonan = null;
	HtpPermohonan htpPermohonan = null;
	private FrmSenaraiFailTerimaPohonData senaraiFail = null;
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.rekod.PenawaranTukarLepasBean.class);

	@Override
	public Vector findFail(String IdUrusan,String noFail,String tajukFail,String pemohon,String idNegeri
			,String idDaerah,String idMukim,String idKementerian,String idAgensi
			,String tarikhPohon,String tarikhBukaFail,String tarikhBukaFailHingga) {
		
		Db db = null;
		Vector<HtpPermohonan> v = new Vector();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
		try{
			db = new Db();
			Statement stmt = db.getStatement();


			String sql = "SELECT distinct P.TUJUAN, f.id_Fail, p.id_Permohonan, f.no_Fail, f.tajuk_Fail, s.keterangan, n.nama_Negeri, n.kod_Mampu,n.id_Negeri, PP.id_htppermohonan ";
			sql +="FROM Tblpfdfail F, Tblpermohonan P, Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, Tblrujstatus s, Tblrujnegeri n, tblhtppermohonan PP ";
			sql +="WHERE f.id_Fail = p.id_Fail AND p.id_Permohonan = sf.id_Permohonan AND n.id_Negeri = f.id_Negeri AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN  ";
			sql +="AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status ";
			sql +="AND sf.aktif = '1' AND F.id_Urusan = '"+IdUrusan+"' AND f.tajuk_Fail LIKE '%"+tajukFail+"%' ";
			sql +="AND f.ID_STATUS <> 999 ";
			//Nama Permohon
			if (pemohon != null) {
				if (!pemohon.trim().equals("")) {
					sql = "SELECT F.TAJUK_FAIL,F.ID_FAIL,F.NO_FAIL,P.TUJUAN, P.ID_PERMOHONAN, P.ID_STATUS,P.TARIKH_TERIMA, RS.KETERANGAN,RN.NAMA_NEGERI"
						+ " FROM TBLPFDFAIL F, TBLPERMOHONAN P, TBLRUJSTATUS RS, TBLHTPPEMOHON TPP,TBLRUJNEGERI RN "
						+ " WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_STATUS = RS.ID_STATUS AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN(+) " 
						+ " AND F.ID_NEGERI=RN.ID_NEGERI AND A.ID_URUSAN = '"+IdUrusan+"' AND f.tajuk_Fail LIKE '%"+tajukFail+"%' ";

					sql += " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
							+ pemohon.trim().toUpperCase() + "'|| '%'";
				}
			}
			sql +=" AND F.NO_FAIL LIKE '%"+noFail+"%' ";
			if(idNegeri != null && !idNegeri.equals("") && !idNegeri.equals("0")){
	    	  sql +=" AND F.ID_NEGERI = "+idNegeri;
			}
			if(idKementerian != null && !idKementerian.equals("") && !idKementerian.equals("0")){
		    	  sql +=" AND F.ID_KEMENTERIAN = "+idKementerian;
			}
			if (idAgensi!= null && !"-1".equals(idAgensi) && !"".equals(idAgensi)) {
		    	  sql = sql + " AND PP.id_agensi = '"+idAgensi+"' ";
		    }
			//tarikhTerima
			if (tarikhPohon != null) {
				if (!tarikhPohon.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_TERIMA,'dd-MON-YY') = '" + sdf1.format(sdf.parse(tarikhPohon)).toUpperCase() +"'";
				}
			}
			
			sql +=" ORDER BY P.ID_PERMOHONAN desc";
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				permohonan = new Permohonan();
				fail = new PfdFail();
				htpPermohonan = new HtpPermohonan();
				
				fail.setIdFail(rs.getLong("id_Fail"));
				permohonan.setNamaNegeri(rs.getString("nama_Negeri"));
				permohonan.setIdPermohonan(rs.getLong("id_Permohonan"));
				fail.setNoFail(rs.getString("no_Fail"));
				permohonan.setTujuan(rs.getString("TUJUAN"));
				//permohonan.setTujuan(rs.getString("tajuk_Fail"));
				htpPermohonan.setIdHtpPermohonan(rs.getString("id_htppermohonan"));
				htpPermohonan.setPermohonan(permohonan);
				htpPermohonan.setStatusPermohonan(rs.getString("keterangan"));
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);
				
				v.addElement(htpPermohonan);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			 if (db != null){
		    	  db.close();
		      }
		}
		return v;
	}
	
	public BorangK carianFailPPT(String noFail, String idHakmilik) throws Exception {
		
		Db db = null;
		Connection conn = null;
		String sql = "";
		BorangK borang = null;
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
				
			sql ="SELECT * FROM  VIEWBORANGKHTP WHERE ID_HAKMILIK="+idHakmilik;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				borang = new BorangK();
				borang.setIdHakmilik(rs.getString("ID_HAKMILIK"));
				borang.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				borang.setTarikhBorangK(rs.getString("TARIKH_BORANGK"));
				borang.setNoLot(rs.getString("NO_LOT"));
				borang.setIdLot(rs.getString("ID_LOT"));
				borang.setNoFail(rs.getString("NO_FAIL"));
				borang.setIdMukim(rs.getString("ID_MUKIM"));
				borang.setIdDaerah(rs.getString("ID_DAERAH"));
				borang.setIdNegeri(rs.getString("ID_NEGERI"));
				//borang.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				borang.setIdJenisHakmilik(rs.getString("ID_JENISHAKMILIK"));
				borang.setLuas(rs.getDouble("LUAS_LOT"));
				borang.setIdBorangK(rs.getString("ID_BORANGK"));
				borang.setIdAgensi((rs.getString("ID_AGENSI")==null?"0":rs.getString("ID_AGENSI")));
				borang.setIdKementerian(rs.getString("ID_KEMENTERIAN"));
			}
		}catch(Exception e){
			e.printStackTrace();
		
		} finally {
			if (db != null)
			db.close();
		}
		
		return borang;
	}
	
	public Vector carianFail(String noFail, String tajukFail,String tarikhTerima, String namaPemohon,String idNegeri,String idKementerian,String idAgensi) throws Exception {		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		Vector senaraiFail = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			// By Rosli, 15/10/2010
			sql = "SELECT F.TAJUK_FAIL,F.NO_FAIL,F.ID_FAIL,TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') TARIKH_DAFTAR_FAIL" +
					", P.ID_PERMOHONAN, P.ID_STATUS,  P.TARIKH_TERIMA" +
					",RSU.NAMA_SUBURUSAN, RS.KETERANGAN,RN.NAMA_NEGERI"
				+ " FROM TBLPFDFAIL F, TBLPERMOHONAN P,TBLRUJSUBURUSAN RSU" +
				//		",TBLHTPPERMOHONAN PP" +
						", TBLRUJSTATUS RS" +
						//", TBLHTPPEMOHON D" +
						",TBLRUJNEGERI RN "
				+ " WHERE P.ID_FAIL = F.ID_FAIL " +
						"AND F.ID_SUBURUSAN=RSU.ID_SUBURUSAN " +
						"AND P.ID_STATUS = RS.ID_STATUS " +
						//"AND B.ID_PERMOHONAN = D.ID_PERMOHONAN(+) " +
						//" AND B.ID_PERMOHONAN = PP.ID_PERMOHONAN " +
						" AND F.ID_NEGERI=RN.ID_NEGERI AND F.ID_URUSAN = '6' ";
			//Tajuk Fail
			if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(F.TAJUK_FAIL) LIKE '%' ||'"
							+ tajukFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//Nama Permohon
//			if (namaPemohon != null) {
//				if (!namaPemohon.trim().equals("")) {
//					sql = sql + " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
//							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
//				}
//			}
			
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
//			if(idKementerian != null){
//				if(!idKementerian.trim().equals("")&& !idKementerian.equals("99999")){
//					sql = sql + " and A.ID_KEMENTERIAN ="+idKementerian;
//				}
//			}
			
			if(idNegeri != null && !idNegeri.equals("") && !idNegeri.equals("0")&& !idNegeri.equals("99999")){
		    	  sql +=" AND RN.ID_NEGERI = "+idNegeri;
			}

//			if (idAgensi!= null && !"-1".equals(idAgensi) && !"".equals(idAgensi)&& !idAgensi.equals("99999")) {
//			    	  sql = sql + " AND PP.id_agensi = '"+idAgensi+"' ";
//			}	
//			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
//			
//			//tarikhTerima
//			if (tarikhTerima != null) {
//				if (!tarikhTerima.toString().trim().equals("")) {
//					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '" + sdf1.format(sdf.parse(tarikhTerima)).toUpperCase() +"'";
//				}
//			}
						
			sql = sql + " ORDER BY RSU.ID_SUBURUSAN,P.ID_PERMOHONAN DESC";
			myLog.info("sql carian fail : " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				permohonan = new Permohonan();
				fail = new PfdFail();
				htpPermohonan = new HtpPermohonan();
				
				fail.setIdFail(rs.getLong("ID_FAIL"));
				fail.setNoFail(rs.getString("NO_FAIL"));
				fail.setTajukFail(rs.getString("TAJUK_FAIL"));
				fail.setTarikhDaftarFail(rs.getString("TARIKH_DAFTAR_FAIL"));
				fail.setNamaSuburusan(rs.getString("NAMA_SUBURUSAN"));
				permohonan.setNamaNegeri(rs.getString("NAMA_NEGERI"));
				permohonan.setIdPermohonan(rs.getLong("ID_PERMOHONAN"));
				permohonan.setTujuan(rs.getString("TAJUK_FAIL"));
				permohonan.setTarikhTerima(rs.getString("TARIKH_TERIMA"));
				//htpPermohonan.setIdHtpPermohonan(rs.getString("ID_HTPPERMOHONAN"));
				htpPermohonan.setPermohonan(permohonan);
				htpPermohonan.setStatusPermohonan(rs.getString("KETERANGAN"));
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);
				
				senaraiFail.addElement(htpPermohonan);

			}

		} finally {
			if (db != null)
				db.close();
		}
		
		return senaraiFail;
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
			r = new SQLRenderer();
			r.update("id_Permohonan", idPermohonan);
			r.add("tarikh_Surat", r.unquote(TSKJP));
			r.add("tujuan",permohonan.getTujuan());
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);
			
			htpPermohonan.getIdHtpPermohonan();
			r = new SQLRenderer();
			r.update("id_Htppermohonan",idhtpPermohonan);
			r.update("id_Permohonan",idPermohonan);
			//r.add("no_Rujukan_KJP",htpPermohonan.getNoRujukanKJP());
			r.add("NO_RUJUKAN_LAIN", htpPermohonan.getNoRujukanLain());
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
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
			stmt.executeUpdate(sql);		
			conn.commit();
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if(db != null)
				db.close();
		}
		
		return htpPermohonan;

	}

	public void hapusBayaran(String idBayaran) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            sql ="DELETE FROM TBLHTPBAYARAN WHERE ID_BAYARAN ="+idBayaran;
            stmt.executeQuery(sql);
            conn.commit();
            
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
	}
	
}
