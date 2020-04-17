package ekptg.model.htp.pajakan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.HtpPerjanjian;
import ekptg.model.htp.entity.MaklumatBil;
import ekptg.model.htp.entity.Pajakan;
import ekptg.model.htp.entity.PajakanKadar;

public class PajakanUtamaBean implements IPajakanUtama {
	
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.pajakan.PajakanUtamaBean.class);
	private Vector senaraiBayaran = null;	
	
	@Override
	public Pajakan getMaklumatPajakan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		Pajakan pajakan = new Pajakan();

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_PAJAKAN, A.TARIKH_TANDATANGAN, A.TARIKH_MULA_PAJAKAN, A.TARIKH_TAMATPAJAKAN" +
					", A.TEMPOH_PAJAKAN, A.KADAR_CUKAI, A.KADAR_PAJAKAN, C.KETERANGAN, A.CARA_BAYAR" +
					",A.KATEGORI_CUKAI "+
					" FROM TBLHTPPAJAKAN A, TBLRUJCARABAYAR C " +
					"WHERE A.CARA_BAYAR = C.ID_CARABAYAR AND A.ID_PERMOHONAN = '" + idPermohonan + "'";			
			myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				HtpPerjanjian per = new HtpPerjanjian();

				pajakan.setIdPajakan( rs.getLong("ID_PAJAKAN"));
				pajakan.setTarikhMulaPajakan(rs.getDate("TARIKH_MULA_PAJAKAN"));
				pajakan.setTarikhTamatPajakan(rs.getDate("TARIKH_TAMATPAJAKAN"));
				pajakan.setTempohPajakan(rs.getString("TEMPOH_PAJAKAN"));
				pajakan.setKadarCukai(rs.getDouble("KADAR_CUKAI"));
				pajakan.setKadarPajakan(rs.getDouble("KADAR_PAJAKAN"));
				pajakan.setKategoriCukai(rs.getString("KATEGORI_CUKAI"));
				
				per.setTarikhTandatangan(rs.getString("TARIKH_TANDATANGAN"));
				
				pajakan.setHtpPerjanjian(per);

			}

		} finally {
			if (db != null)
				db.close();
		}
		return pajakan;

	}

	@Override
	public Pajakan getMaklumatPajakanKadarPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		Pajakan pajakan = new Pajakan();

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT TPK.ID_PAJAKANKADAR,TPK.ID_PAJAKAN" +
					", ADD_MONTHS( TO_DATE(TPK.TARIKH_TAMAT_PAJAK), 12 )-1 TARIKH_MULA_PAJAKAN, TPP.TARIKH_TAMATPAJAKAN " +
					" , TPK.KADAR_BAYAR_CUKAI KADAR_CUKAI,TPK.KADAR_BAYAR_PAJAK KADAR_PAJAKAN" +
					" ,to_char(TPP.TARIKH_TAMATPAJAKAN,'YYYY')-to_char(TPK.TARIKH_TAMAT_PAJAK,'YYYY') TEMPOH_PAJAKAN "+
					" FROM TBLHTPPAJAKAN TPP, TBLHTPPAJAKANKADAR TPK " +
					" WHERE TPK.ID_PAJAKAN = TPP.ID_PAJAKAN AND TPP.ID_PERMOHONAN = '" + idPermohonan + "' " +
							"ORDER BY TPK.ID_PAJAKANKADAR ASC";			
			myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				//HtpPerjanjian per = new HtpPerjanjian();

				pajakan.setIdPajakan( rs.getLong("ID_PAJAKAN"));
				pajakan.setTarikhMulaPajakan(rs.getDate("TARIKH_MULA_PAJAKAN"));
				pajakan.setTarikhTamatPajakan(rs.getDate("TARIKH_TAMATPAJAKAN"));
				pajakan.setTempohPajakan(rs.getString("TEMPOH_PAJAKAN"));
				pajakan.setKadarCukai(rs.getDouble("KADAR_CUKAI"));
				pajakan.setKadarPajakan(rs.getDouble("KADAR_PAJAKAN"));
				//pajakan.setKategoriCukai(rs.getString("KATEGORI_CUKAI"));				
				//per.setTarikhTandatangan(rs.getString("TARIKH_TANDATANGAN"));
				
				//pajakan.setHtpPerjanjian(per);

			}

		} finally {
			if (db != null)
				db.close();
		}
		return pajakan;

	}
	
	
	@Override
	public PajakanKadar saveMaklumatKadar(PajakanKadar pk, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPPAJAKANKADAR
			long idPajakanKadar = DB.getNextID("TBLHTPPAJAKANKADAR_SEQ");
			pk.setIdPajakanKadar(idPajakanKadar);
			
			r.add("ID_PAJAKANKADAR", pk.getIdPajakanKadar());
			r.add("ID_PAJAKAN", pk.getPajakan().getIdPajakan());		
			if (!"".equals(pk.getTarikhMulaBayaran())){
				r.add("TARIKH_MULA_PAJAK", r.unquote("to_date('" + pk.getTarikhMulaBayaran() + "','dd/MM/yyyy')"));
			}
			if (!"".equals(pk.getTarikhTamatBayaran())){
				r.add("TARIKH_TAMAT_PAJAK", r.unquote("to_date('" + pk.getTarikhTamatBayaran() + "','dd/MM/yyyy')"));
			}
			r.add("TEMPOH", pk.getPajakan().getTempohPajakan());
			r.add("KADAR_BAYAR_CUKAI", pk.getPajakan().getKadarCukai());
			r.add("KADAR_BAYAR_PAJAK", pk.getPajakan().getKadarPajakan());
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLHTPPAJAKANKADAR");
			myLog.info(sql);
			stmt.executeUpdate(sql);

			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	    return pk;
	    
	}
	
	public void saveUpdatePajakan(String idPajakan, Hashtable hash, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPPAJAKAN
			r.update("ID_PAJAKAN", idPajakan);
						
			if (!"".equals(hash.get("tarikhTandatangan"))){
				r.add("TARIKH_TANDATANGAN", r.unquote("to_date('" + hash.get("tarikhTandatangan") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhMula"))){
				r.add("TARIKH_MULA_PAJAKAN", r.unquote("to_date('" + hash.get("tarikhMula") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTamat"))){
				r.add("TARIKH_TAMATPAJAKAN", r.unquote("to_date('" + hash.get("tarikhTamat") + "','dd/MM/yyyy')"));
			}
			r.add("TEMPOH_PAJAKAN", hash.get("tempoh"));
			r.add("KADAR_CUKAI", hash.get("kadar"));
			r.add("KADAR_PAJAKAN", hash.get("kadarPajakan"));
			r.add("CARA_BAYAR", hash.get("idCaraBayar"));
			r.add("KATEGORI_CUKAI", hash.get("katCukai"));
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLHTPPAJAKAN");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	}	
	
	@Override
	public Vector getPajakanKadar(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		senaraiBayaran = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT TPK.ID_PAJAKAN,TPK.ID_PAJAKANKADAR, TPK.TARIKH_MULA_PAJAK, TPK.TARIKH_TAMAT_PAJAK" +
					", TPK.KADAR_BAYAR_CUKAI,TPK.KADAR_BAYAR_PAJAK  " +
					", TPK.TEMPOH " +
					" FROM TBLHTPPAJAKAN TPP, TBLHTPPAJAKANKADAR TPK " +
					" WHERE TPK.ID_PAJAKAN = TPP.ID_PAJAKAN " +
					" AND TPP.ID_PERMOHONAN = '" + idPermohonan + "' ORDER BY TPK.ID_PAJAKANKADAR  DESC";			
			myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				PajakanKadar pajakan = new PajakanKadar();
				Pajakan p = new Pajakan();

				pajakan.setIdPajakanKadar( rs.getLong("ID_PAJAKANKADAR"));
				p.setIdPajakan( rs.getLong("ID_PAJAKAN"));
				p.setTarikhMulaPajakan(rs.getDate("TARIKH_MULA_PAJAK"));
				p.setTarikhTamatPajakan(rs.getDate("TARIKH_TAMAT_PAJAK"));
				p.setTempohPajakan(rs.getString("TEMPOH")==null?"0":rs.getString("TEMPOH"));
				p.setKadarCukai(rs.getDouble("KADAR_BAYAR_CUKAI"));
				p.setKadarPajakan(rs.getDouble("KADAR_BAYAR_PAJAK"));
				
				pajakan.setPajakan(p);
				senaraiBayaran.addElement(pajakan);
				
			}

		} finally {
			if (db != null)
				db.close();
		}
		return senaraiBayaran;

	}

	@Override
	public PajakanKadar getPajakanKadarI(String idPajakanKadar) throws Exception {
		Db db = null;
		String sql = "";
		PajakanKadar pk = new PajakanKadar();
		senaraiBayaran = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT TPK.ID_PAJAKANKADAR,TPK.ID_PAJAKAN,TPK.TARIKH_MULA_PAJAK" +
					", TPK.TARIKH_TAMAT_PAJAK" +
					", TPK.KADAR_BAYAR_CUKAI,TPK.KADAR_BAYAR_PAJAK  " +
					", TPK.TEMPOH " +
					" FROM TBLHTPPAJAKAN TPP, TBLHTPPAJAKANKADAR TPK " +
					" WHERE TPK.ID_PAJAKAN = TPP.ID_PAJAKAN " +
					" AND TPK.ID_PAJAKANKADAR = '" + idPajakanKadar + "'";			
			myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Pajakan p = new Pajakan();

				pk.setIdPajakanKadar( rs.getLong("ID_PAJAKANKADAR"));
				//pk.s
				p.setIdPajakan( rs.getLong("ID_PAJAKAN"));
				p.setTarikhMulaPajakan(rs.getDate("TARIKH_MULA_PAJAK"));
				p.setTarikhTamatPajakan(rs.getDate("TARIKH_TAMAT_PAJAK"));
				p.setTempohPajakan(rs.getString("TEMPOH")==null?"0":rs.getString("TEMPOH"));
				p.setKadarCukai(rs.getDouble("KADAR_BAYAR_CUKAI"));
				p.setKadarPajakan(rs.getDouble("KADAR_BAYAR_PAJAK"));
				
				pk.setPajakan(p);
				//senaraiBayaran.addElement(pajakan);
				
			}

		} finally {
			if (db != null)
				db.close();
		}
		return pk;

	}	
	
	@Override
	public PajakanKadar saveUpdateMaklumatKadar(PajakanKadar pk, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPPAJAKANKADAR
			//long idPajakanKadar = DB.getNextID("TBLHTPPAJAKANKADAR_SEQ");
			//pk.setIdPajakanKadar(idPajakanKadar);
			
			r.update("ID_PAJAKANKADAR", pk.getIdPajakanKadar());
			//r.add("ID_PAJAKAN", pk.getPajakan().getIdPajakan());		
			if (!"".equals(pk.getTarikhMulaBayaran())){
				r.add("TARIKH_MULA_PAJAK", r.unquote("to_date('" + pk.getTarikhMulaBayaran() + "','dd/MM/yyyy')"));
			}
			if (!"".equals(pk.getTarikhTamatBayaran())){
				r.add("TARIKH_TAMAT_PAJAK", r.unquote("to_date('" + pk.getTarikhTamatBayaran() + "','dd/MM/yyyy')"));
			}
			r.add("TEMPOH", pk.getPajakan().getTempohPajakan());
			r.add("KADAR_BAYAR_CUKAI", pk.getPajakan().getKadarCukai());
			r.add("KADAR_BAYAR_PAJAK", pk.getPajakan().getKadarPajakan());
			
			//r.add("ID_MASUK", userId);
			//r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLHTPPAJAKANKADAR");
			myLog.info(sql);
			stmt.executeUpdate(sql);

			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	    return pk;
	    
	}

	@Override
	public PajakanKadar deleteMaklumatKadar(PajakanKadar pk) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			//ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();	
			
			r.add("ID_PAJAKANKADAR", pk.getPajakan().getIdPajakan());
			sql = r.getSQLDelete("TBLHTPPAJAKANKADAR");
			myLog.info(sql);
			stmt.executeUpdate(sql);

			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah hapus data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	    return pk;
	    
	}	
	
	@Override
	public PajakanKadar getPajakanKadarI(String idPajakan,String tahun) throws Exception {
		Db db = null;
		String sql = "";
		PajakanKadar pk = new PajakanKadar();
		senaraiBayaran = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT TPK.ID_PAJAKANKADAR,TPK.ID_PAJAKAN,TPK.TARIKH_MULA_PAJAK" +
					", TPK.TARIKH_TAMAT_PAJAK" +
					", TPK.KADAR_BAYAR_CUKAI,TPK.KADAR_BAYAR_PAJAK  " +
					", TPK.TEMPOH " +
					" FROM TBLHTPPAJAKAN TPP, TBLHTPPAJAKANKADAR TPK " +
					" WHERE TPK.ID_PAJAKAN = TPP.ID_PAJAKAN " +
					" AND TPK.ID_PAJAKAN = '"+idPajakan+"'" +
					" AND TO_CHAR(TPK.tarikh_mula_pajak,'yyyy')<="+tahun+" " +
					" AND TO_CHAR(TPK.tarikh_tamat_pajak,'yyyy') >="+tahun+" ";
					//" AND TPK.ID_PAJAKANKADAR = '" + idPajakanKadar + "'";			
			myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Pajakan p = new Pajakan();

				pk.setIdPajakanKadar( rs.getLong("ID_PAJAKANKADAR"));
				//pk.s
				p.setIdPajakan( rs.getLong("ID_PAJAKAN"));
				p.setTarikhMulaPajakan(rs.getDate("TARIKH_MULA_PAJAK"));
				p.setTarikhTamatPajakan(rs.getDate("TARIKH_TAMAT_PAJAK"));
				//p.setTempohPajakan(rs.getString("TEMPOH")==null?"0":rs.getString("TEMPOH"));
				p.setKadarCukai(rs.getDouble("KADAR_BAYAR_CUKAI"));
				p.setKadarPajakan(rs.getDouble("KADAR_BAYAR_PAJAK"));
				
				pk.setPajakan(p);
				//senaraiBayaran.addElement(pajakan);
				
			}

		} finally {
			if (db != null)
				db.close();
		}
		return pk;

	}	
	
	@Override
	public MaklumatBil getPajakanBil(String idPajakan,String tahun) throws Exception {
		Db db = null;
		String sql = "";
		MaklumatBil pk = new MaklumatBil();
		senaraiBayaran = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
//			sql = "SELECT TPK.ID_PAJAKANKADAR,TPK.ID_PAJAKAN,TPK.TARIKH_MULA_PAJAK" +
//					", TPK.TARIKH_TAMAT_PAJAK" +
//					", TPK.KADAR_BAYAR_CUKAI,TPK.KADAR_BAYAR_PAJAK  " +
//					", TPK.TEMPOH " +
//					" FROM TBLHTPPAJAKAN TPP, TBLHTPPAJAKANKADAR TPK " +
//					" WHERE TPK.ID_PAJAKAN = TPP.ID_PAJAKAN " +
//					" AND TPK.ID_PAJAKANKADAR = '" + idPajakanKadar + "'";	
			sql = "SELECT TPPK.ID_PAJAKANKADAR,TPPK.TARIKH_MULA_PAJAK, TPPK.TARIKH_TAMAT_PAJAK " +
					" ,TPPK.KADAR_BAYAR_CUKAI,TPPK.KADAR_BAYAR_PAJAK " +
					" FROM tblhtppajakankadar TPPK "+
			" WHERE " +
			" TPPK.ID_PAJAKAN = '"+idPajakan+"'" +
			" AND TO_CHAR(TPPK.tarikh_mula_pajak,'yyyy')<="+tahun+" " +
			" AND TO_CHAR(TPPK.tarikh_tamat_pajak,'yyyy') >="+tahun+" ";
			myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Pajakan p = new Pajakan();

				pk.setIdPajakanKadar( rs.getLong("ID_PAJAKANKADAR"));
				//pk.s
				p.setIdPajakan( rs.getLong("ID_PAJAKAN"));
				p.setTarikhMulaPajakan(rs.getDate("TARIKH_MULA_PAJAK"));
				p.setTarikhTamatPajakan(rs.getDate("TARIKH_TAMAT_PAJAK"));
				//p.setTempohPajakan(rs.getString("TEMPOH")==null?"0":rs.getString("TEMPOH"));
				p.setKadarCukai(rs.getDouble("KADAR_BAYAR_CUKAI"));
				p.setKadarPajakan(rs.getDouble("KADAR_BAYAR_PAJAK"));
				
				pk.setPajakan(p);
				//senaraiBayaran.addElement(pajakan);
				
			}

		} finally {
			if (db != null)
				db.close();
		}
		return pk;

	}	
	
	public boolean isHakmilikPajakan(HakMilik hakmilik){
		Connection conn = null;
		Db db = null;
		boolean returnValue = false;
 	    try {
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			sql = " " +
					" SELECT TPHU.ID_HAKMILIKURUSAN "+
					" FROM TBLHTPHAKMILIKURUSAN TPHU "+
					" WHERE "+
					" TPHU.ID_NEGERI = "+hakmilik.getIdNegeri() +		
					" AND TPHU.ID_DAERAH = '"+hakmilik.getIdDaerah()+"'"+
					" AND TPHU.ID_MUKIM = '"+hakmilik.getIdMukim()+"'"+
					" AND TPHU.NO_HAKMILIK = '"+hakmilik.getNoHakmilik()+"'"+
					" AND TPHU.ID_JENISHAKMILIK = "+hakmilik.getIdJenisHakmilik()+
					" AND TPHU.ID_PERMOHONAN='"+ hakmilik.getIdPermohonan() +"'"+
					" ";
			//myLog.info("isHakmilikPajakan:sql="+sql);
			rs = stmt.executeQuery(sql);
			returnValue = rs.next();
			 
		}catch(Exception e){
			e.printStackTrace();
		
		} finally {
			if (db != null) db.close();
		}

		return returnValue;
	}
	
	
}
