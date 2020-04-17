/**
 * 
 */
package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

/**
 * @author Firzan.Fir
 *
 */
public class FrmPenswastaan2PindahMilikTanahData {
	private static Logger log = Logger.getLogger(FrmPenswastaan2PindahMilikTanahData.class);
	private Vector MaklumatPemilik = null;
	private Vector senaraiMaklumatPemilik = null;
	private Vector senaraiMaklumatTanahPindahMilik = null;
	private Vector noHakmilikNoWarta = null;
	private Db db = null;
	private Connection conn = null;
	private SimpleDateFormat DateFormatter = new SimpleDateFormat("dd/MM/yyyy");
//	private String noHakmilikNoWarta = null;
//	
	public void setMaklumatPemilikTanah(String idPihakKepentingan) throws Exception{
		String sql = null;
		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_PIHAKBERKEPENTINGAN, A.ID_HAKMILIKURUSAN, C.ID_PINDAHMILIK, ";
			sql += "A.NAMA, A.ALAMAT1, A.ALAMAT2, A.ALAMAT3,  ";
			sql += "A.POSKOD, A.ID_DAERAH, A.ID_NEGERI, A.NO_TEL, A.NO_FAX, ";
			sql += "C.NO_PERSERAHAN, C.TARIKH_DAFTAR, B.NO_WARTA, B.NO_HAKMILIK   ";
			sql += "FROM TBLHTPPIHAKBERKEPENTINGAN A, TBLHTPHAKMILIKURUSAN B, TBLHTPPINDAHMILIK C   ";
			sql += "WHERE A.ID_HAKMILIKURUSAN = B.ID_HAKMILIKURUSAN  ";
			sql += "AND A.ID_HAKMILIKURUSAN = C.ID_HAKMILIKURUSAN  ";
			sql += "AND A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN ";
			sql += "AND A.ID_PIHAKBERKEPENTINGAN ='" + idPihakKepentingan + "'";

			log.info("setMaklumatPemilikTanah ! : " + sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable hashPemilik = null;
			MaklumatPemilik = new Vector<Hashtable>();
			
			while(rs.next()){
				
				hashPemilik = new Hashtable();
				hashPemilik.put("idPihakKepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs.getString("ID_PIHAKBERKEPENTINGAN"));
				hashPemilik.put("idHakmilikurusan", rs.getString("ID_HAKMILIKURUSAN") == null ? "" : rs.getString("ID_HAKMILIKURUSAN"));
				hashPemilik.put("idPindahMilik", rs.getString("ID_PINDAHMILIK") == null ? "" : rs.getString("ID_PINDAHMILIK"));
				hashPemilik.put("noPerserahan", rs.getString("NO_PERSERAHAN") == null ? "" : rs.getString("NO_PERSERAHAN"));
				hashPemilik.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				hashPemilik.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
				hashPemilik.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
				hashPemilik.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
				hashPemilik.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				hashPemilik.put("idDaerah", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				hashPemilik.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				hashPemilik.put("tel", rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				hashPemilik.put("fax", rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
				hashPemilik.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR") == null ? "" : DateFormatter.format(rs.getDate("TARIKH_DAFTAR")));
				hashPemilik.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				hashPemilik.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				MaklumatPemilik.addElement(hashPemilik);
					
			}
			
		}catch(Exception e){
			log.error("Error : " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	

	public void setMaklumatSenaraiPemilikTanah() throws Exception{
		String sql = null;
		
		try{
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_PIHAKBERKEPENTINGAN, A.ID_HAKMILIKURUSAN, C.ID_PINDAHMILIK, ";
			sql += "A.NAMA,B.NO_HAKMILIK, B.NO_WARTA,  D.KETERANGAN  ";
			sql += "FROM TBLHTPPIHAKBERKEPENTINGAN A, TBLHTPHAKMILIKURUSAN B, TBLHTPPINDAHMILIK C, TBLRUJJENISHAKMILIK D ";
			sql += "WHERE A.ID_HAKMILIKURUSAN = B.ID_HAKMILIKURUSAN ";
			sql += "AND A.ID_HAKMILIKURUSAN = C.ID_HAKMILIKURUSAN ";
			sql += "AND A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN ";
			sql += "AND B.ID_JENISHAKMILIK = D.ID_JENISHAKMILIK  ";

			
			log.info("setMaklumatSenaraiPemilikTanah : " + sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable hashListPemilik = null;
			senaraiMaklumatPemilik = new Vector<Hashtable>();
			
			int bil = 1;
			
			while(rs.next()){
				hashListPemilik = new Hashtable();
				hashListPemilik.put("bil", bil);
				hashListPemilik.put("idPihakKepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs.getString("ID_PIHAKBERKEPENTINGAN"));
				hashListPemilik.put("idHakmilikurusan", rs.getString("ID_HAKMILIKURUSAN") == null ? "" : rs.getString("ID_HAKMILIKURUSAN"));
				hashListPemilik.put("idPindahMilik", rs.getString("ID_PINDAHMILIK") == null ? "" : rs.getString("ID_PINDAHMILIK"));
				hashListPemilik.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				hashListPemilik.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				hashListPemilik.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				hashListPemilik.put("jenisHakmilik", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				senaraiMaklumatPemilik.addElement(hashListPemilik);
				bil++;
				
			}

		}catch(Exception e){
			log.error("Error : " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void setMaklumatSenaraiPemilikPermohonan(String idpermohonan) throws Exception{
		String sql = null;
		
		try{
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_PIHAKBERKEPENTINGAN, A.ID_HAKMILIKURUSAN, C.ID_PINDAHMILIK, ";
			sql += "A.NAMA,B.NO_HAKMILIK, B.NO_WARTA,  D.KETERANGAN,D.KOD_JENIS_HAKMILIK  ";
			sql += "FROM TBLHTPPIHAKBERKEPENTINGAN A, TBLHTPHAKMILIKURUSAN B, TBLHTPPINDAHMILIK C, TBLRUJJENISHAKMILIK D ";
			sql += "WHERE A.ID_HAKMILIKURUSAN = B.ID_HAKMILIKURUSAN ";
			sql += "AND A.ID_HAKMILIKURUSAN = C.ID_HAKMILIKURUSAN ";
			sql += "AND A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN ";
			sql += "AND B.ID_JENISHAKMILIK = D.ID_JENISHAKMILIK  " +
					"AND B.ID_PERMOHONAN="+idpermohonan;
		
			log.info("setMaklumatSenaraiPemilikTanah : " + sql);
			
			ResultSet rs = stmt.executeQuery(sql);			
			Hashtable hashListPemilik = null;
			senaraiMaklumatPemilik = new Vector<Hashtable>();			
			int bil = 1;
			
			while(rs.next()){
				hashListPemilik = new Hashtable();
				hashListPemilik.put("bil", bil);
				hashListPemilik.put("idPihakKepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs.getString("ID_PIHAKBERKEPENTINGAN"));
				hashListPemilik.put("idHakmilikurusan", rs.getString("ID_HAKMILIKURUSAN") == null ? "" : rs.getString("ID_HAKMILIKURUSAN"));
				hashListPemilik.put("idPindahMilik", rs.getString("ID_PINDAHMILIK") == null ? "" : rs.getString("ID_PINDAHMILIK"));
				hashListPemilik.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				hashListPemilik.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				hashListPemilik.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				hashListPemilik.put("jenisHakmilik", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				hashListPemilik.put("kodJenisHakmilik", Utils.isNull(rs.getString("KOD_JENIS_HAKMILIK")));
				senaraiMaklumatPemilik.addElement(hashListPemilik);
				bil++;
				
			}

		}catch(Exception e){
			log.error("Error : " + e.getMessage());
			e.printStackTrace();
		}
		
	}

	
	public void setMaklumatSenaraiPemilikTanah(String idHakmilikurusan) throws Exception{
		String sql = null;
		
		try{
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_PIHAKBERKEPENTINGAN, A.ID_HAKMILIKURUSAN, C.ID_PINDAHMILIK, ";
			sql += "A.NO_RUJUKAN, A.NAMA, A.ALAMAT1, A.ALAMAT2, A.ALAMAT3, A.POSKOD, A.ID_DAERAH, ";
			sql += "A.ID_NEGERI, A.NO_TEL, A.NO_FAX, C.NO_PERSERAHAN, C.TARIKH_DAFTAR   ";
			sql += "FROM TBLHTPPIHAKBERKEPENTINGAN A, TBLHTPHAKMILIKURUSAN B, TBLHTPPINDAHMILIK C ";
			sql += "WHERE A.ID_HAKMILIKURUSAN = B.ID_HAKMILIKURUSAN ";
			sql += "AND A.ID_HAKMILIKURUSAN = C.ID_HAKMILIKURUSAN ";
			sql += "AND A.ID_HAKMILIKURUSAN ='" + idHakmilikurusan + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable hashListPemilik = null;
			senaraiMaklumatPemilik = new Vector<Hashtable>();
			
			int bil = 1;
			
			while(rs.next()){
				hashListPemilik = new Hashtable();
				hashListPemilik.put("bil", bil);
				hashListPemilik.put("idPihakKepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs.getString("ID_PIHAKBERKEPENTINGAN"));
				hashListPemilik.put("idHakmilikurusan", rs.getString("ID_HAKMILIKURUSAN") == null ? "" : rs.getString("ID_HAKMILIKURUSAN"));
				hashListPemilik.put("idPindahMilik", rs.getString("ID_PINDAHMILIK") == null ? "" : rs.getString("ID_PINDAHMILIK"));
				hashListPemilik.put("noRujukan", rs.getString("NO_RUJUKAN") == null ? "" : rs.getString("NO_RUJUKAN"));
				hashListPemilik.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				hashListPemilik.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
				hashListPemilik.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
				hashListPemilik.put("alamat2", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
				hashListPemilik.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				hashListPemilik.put("idDaerah", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				hashListPemilik.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				hashListPemilik.put("tel", rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				hashListPemilik.put("fax", rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
				hashListPemilik.put("noPerserahan", rs.getString("NO_PERSERAHAN") == null ? "" : rs.getString("NO_PERSERAHAN"));
				hashListPemilik.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR") == null ? "" : DateFormatter.format(rs.getDate("TARIKH_DAFTAR")));
				senaraiMaklumatPemilik.addElement(hashListPemilik);
				bil++;
				
			}
			
			
			
		}catch(Exception e){
			log.error("Error : " + e.getMessage());
			e.printStackTrace();
		}
		
	}

	
	public void setMaklumatSenaraiPindahMilikTanah(String idPermohonan) throws Exception{
		
		String sql = null;
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_HAKMILIKURUSAN, A.ID_PERMOHONAN, A.ID_JENISHAKMILIK, ";
			sql += "A.PEGANGAN_HAKMILIK, A.NO_HAKMILIK, A.NO_WARTA, A.TARIKH_WARTA, ";
			sql += "A.NO_LOT, A.SEKATAN, A.SYARAT, A.STATUS_TANAH, ";
			sql += "B.NAMA_DAERAH, C.NAMA_NEGERI, D.NAMA_MUKIM, E.KETERANGAN,E.KOD_JENIS_HAKMILIK ";
			sql += "FROM TBLHTPHAKMILIKURUSAN A, TBLRUJDAERAH B, TBLRUJNEGERI C, TBLRUJMUKIM D, TBLRUJJENISHAKMILIK E ";
			sql += "WHERE A.ID_DAERAH = B.ID_DAERAH ";
			sql += "AND A.ID_NEGERI = C.ID_NEGERI ";
			sql += "AND A.ID_MUKIM = D.ID_MUKIM ";
			sql += "AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK ";
			sql += "AND A.TINDAKAN_LANJUT IN (2,3) ";
			sql += "AND A.ID_PERMOHONAN = '" + idPermohonan + "'";

			//log.info("sql : setMaklumatSenaraiPindahMilikTanah : " + sql);			
			ResultSet rs = stmt.executeQuery(sql);			
			Hashtable hashListTanahPindahMilik = null;
			senaraiMaklumatTanahPindahMilik = new Vector<Hashtable>();
			
			int bil = 1;			
			while(rs.next()){
				hashListTanahPindahMilik = new Hashtable();
				hashListTanahPindahMilik.put("bil",bil);
				hashListTanahPindahMilik.put("idHakmilikurusan", rs.getString("ID_HAKMILIKURUSAN") == null ? "" : rs.getString("ID_HAKMILIKURUSAN"));
				hashListTanahPindahMilik.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				hashListTanahPindahMilik.put("idJenisHakmilik", rs.getString("ID_JENISHAKMILIK") == null ? "" : rs.getString("ID_JENISHAKMILIK"));
				hashListTanahPindahMilik.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK"));
				hashListTanahPindahMilik.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				hashListTanahPindahMilik.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				hashListTanahPindahMilik.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? "" : DateFormatter.format(rs.getDate("TARIKH_WARTA")));
				hashListTanahPindahMilik.put("noLot", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				hashListTanahPindahMilik.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN"));
				hashListTanahPindahMilik.put("syarat", rs.getString("SYARAT") == null ? "" : rs.getString("SYARAT"));
				hashListTanahPindahMilik.put("statusTanah", rs.getString("STATUS_TANAH") == null ? "" : rs.getString("STATUS_TANAH"));
				hashListTanahPindahMilik.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH"));
				hashListTanahPindahMilik.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				hashListTanahPindahMilik.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM"));
				hashListTanahPindahMilik.put("jenisHakmilik", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				hashListTanahPindahMilik.put("kodJenisHakmilik", Utils.isNull(rs.getString("KOD_JENIS_HAKMILIK")));				
				// 20/07/2010
				// Penswastaan -Senarai Hakmilik untuk pindahmilik
				hashListTanahPindahMilik.put("nohakmilik", Utils.isNull(rs.getString("NO_HAKMILIK")));
				hashListTanahPindahMilik.put("kodjenishakmilik", Utils.isNull(rs.getString("KOD_JENIS_HAKMILIK")));				

				bil++;
				
				senaraiMaklumatTanahPindahMilik.addElement(hashListTanahPindahMilik);	
			}			
			
		}catch(Exception e){			
			e.printStackTrace();
		}

	}
	
	
	public Vector getMaklumatPemilik(){
		log.info("FrmPenswastaan2PindahMilikTanahData : getMaklumatPemilik : " + MaklumatPemilik.size() );
		return MaklumatPemilik;
	}
	
	public Vector getSenaraiMaklumatPemilik(){
		log.info("FrmPenswastaan2PindahMilikTanahData : getSenaraiMaklumatPemilik : " + senaraiMaklumatPemilik.size());
		return senaraiMaklumatPemilik;
	}
	
	public Vector getSenaraiMaklumatTanahPindahMilik(){
		log.info("FrmPenswastaan2PindahMilikTanahData : getSenaraiMaklumatTanahPindahMilik : " + senaraiMaklumatTanahPindahMilik.size());
		return senaraiMaklumatTanahPindahMilik;
	}
	
	public void setNoWartaNohakmilik(String idHakmilikurusan)throws Exception{
		
		String sql;
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_HAKMILIKURUSAN, A.NO_HAKMILIK, A.NO_WARTA ";
			sql += "FROM TBLHTPHAKMILIKURUSAN A ";
			sql += "WHERE A.ID_HAKMILIKURUSAN ='" + idHakmilikurusan + "'";
			
			log.info("sql : setNoWartaNohakmilik : " + sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			noHakmilikNoWarta = new Vector();
			Hashtable h = null;
			
			while(rs.next()){
				h = new Hashtable();
				h.put("idHakmilikurusan", rs.getString("ID_HAKMILIKURUSAN") == null ? "" : rs.getString("ID_HAKMILIKURUSAN") );
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK") );
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA") );
				
				noHakmilikNoWarta.addElement(h);
			}
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Vector getNoWartaNohakmilik(){
		log.info("FrmPenswastaan2PindahMilikTanahData : getNoWartaNohakmilik : " + noHakmilikNoWarta.size());
		return noHakmilikNoWarta;
	}
	
	public void savePemilik(String idHakmilikurusan, Hashtable hash, HttpSession session) throws Exception{
		
		String sql = null;
		try{
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			
			conn.setAutoCommit(false);
			
			SQLRenderer r = new SQLRenderer();
			String userId = session.getAttribute("_ekptg_user_id").toString();
			
			//TBLHTPPIHAKBERKEPENTINGAN
			long idPihakberkepentingan = DB.getNextID("TBLHTPPIHAKBERKEPENTINGAN_SEQ");
			r.add("id_pihakberkepentingan", idPihakberkepentingan);
			r.add("id_hakmilikurusan", idHakmilikurusan);
			r.add("id_jenisnopb", "1");
			r.add("id_jenispb", "0");
			r.add("nama", hash.get("nama"));
			r.add("alamat1", hash.get("alamat1"));
			r.add("alamat2", hash.get("alamat2"));
			r.add("alamat3", hash.get("alamat3"));
			r.add("poskod", hash.get("poskod"));
			r.add("id_daerah", hash.get("daerah"));
			r.add("id_negeri", hash.get("negeri"));
			r.add("no_tel", hash.get("tel"));
			r.add("no_fax", hash.get("fax"));
			r.add("id_masuk", userId);
			r.add("tarikh_masuk", r.unquote("SYSDATE"));
			r.add("id_kemaskini", userId);
			r.add("tarikh_kemaskini", r.unquote("SYSDATE"));
			
			sql = r.getSQLInsert("TBLHTPPIHAKBERKEPENTINGAN");
			
			log.info("savePemilik : TBLHTPPIHAKBERKEPENTINGAN : " + sql);
			
			stmt.executeQuery(sql);
			
			//TBLHTPPINDAHMILIK
			r = new SQLRenderer();
			
			long idPindahmilik = DB.getNextID("TBLHTPPINDAHMILIK_SEQ");	
			r.add("id_pindahmilik", idPindahmilik);
			r.add("id_hakmilikurusan", idHakmilikurusan);
			r.add("no_perserahan", hash.get("noPerserahan"));		
			if (!"".equals(hash.get("tarikhDaftar"))){
				r.add("tarikh_daftar", r.unquote("to_date('" + hash.get("tarikhDaftar") + "','dd/MM/yyyy')"));
			}
			r.add("id_masuk", userId);
			r.add("tarikh_masuk", r.unquote("SYSDATE"));
			r.add("id_kemaskini", userId);
			r.add("tarikh_kemaskini", r.unquote("SYSDATE"));
			r.add("id_pihakberkepentingan", idPihakberkepentingan);			
			sql = r.getSQLInsert("TBLHTPPINDAHMILIK");
			
			log.info("savePemilik : TBLHTPPINDAHMILIK : " + sql);
			
			stmt.executeQuery(sql);
			
			conn.commit();
			
			
		}catch(Exception e){
			conn.rollback();
			e.printStackTrace();
		}
		
	}
	
	public void updatePemilik(String idPihakKepentingan, Hashtable hash, HttpSession session) throws Exception{
		
		String sql = null;
		try{
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			conn.setAutoCommit(false);
			
			String userId = session.getAttribute("_ekptg_user_id").toString();
			
			SQLRenderer r = new SQLRenderer();
			r.update("id_pihakberkepentingan", idPihakKepentingan);
			
			//TBLHTPPIHAKBERKEPENTINGAN
			r.add("nama", hash.get("nama"));
			r.add("alamat1", hash.get("alamat1"));
			r.add("alamat2", hash.get("alamat2"));
			r.add("alamat3", hash.get("alamat3"));
			r.add("poskod", hash.get("poskod"));
			r.add("id_daerah", hash.get("daerah"));
			r.add("id_negeri", hash.get("negeri"));
			r.add("no_tel", hash.get("tel"));
			r.add("no_fax", hash.get("fax"));
			r.add("id_kemaskini", userId);
			r.add("tarikh_kemaskini", r.unquote("SYSDATE"));
			
			sql = r.getSQLUpdate("TBLHTPPIHAKBERKEPENTINGAN");
			
			log.info("updatePemilik : TBLHTPPIHAKBERKEPENTINGAN : " + sql);
			
			stmt.executeQuery(sql);
			
			
			//TBLHTPPINDAHMILIK
			r = new SQLRenderer();
			r.update("id_pihakberkepentingan", idPihakKepentingan);
			
			r.add("no_perserahan", hash.get("noPerserahan"));
			
			if (!"".equals(hash.get("tarikhDaftar"))){
				r.add("tarikh_daftar", r.unquote("to_date('" + hash.get("tarikhDaftar") + "','dd/MM/yyyy')"));
			}
			
			r.add("id_kemaskini", userId);
			r.add("tarikh_kemaskini", r.unquote("SYSDATE"));
			
			sql = r.getSQLUpdate("TBLHTPPINDAHMILIK");
			
			log.info("updatePemilik : TBLHTPPINDAHMILIK : " + sql);
			
			stmt.executeQuery(sql);
			
			conn.commit();
			
			
		}catch(Exception e){
			
			conn.rollback();
			e.printStackTrace();
		}
		
	}
	
	public void hapusPemilik(String idPihakBerkepentingan) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			
			//TBLHTPPINDAHMILIK
			r.add("id_pihakberkepentingan", idPihakBerkepentingan);

			sql = r.getSQLDelete("TBLHTPPINDAHMILIK");
			
			log.info("delete pemilik : TBLHTPPINDAHMILIK : " + sql);
			
			stmt.executeUpdate(sql);
			
			
			//TBLHTPPIHAKBERKEPENTINGAN
			r = new SQLRenderer();
			r.add("id_pihakberkepentingan", idPihakBerkepentingan);

			sql = r.getSQLDelete("TBLHTPPIHAKBERKEPENTINGAN");
			
			log.info("delete pemilik : TBLHTPPIHAKBERKEPENTINGAN : " + sql);
			
			stmt.executeUpdate(sql);
			
			
		
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}	
	}

}
