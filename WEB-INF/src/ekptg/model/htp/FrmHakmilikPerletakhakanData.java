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
import lebah.util.Util;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmHakmilikPerletakhakanData {
	
	private Vector senaraiFail = null;
	private Vector beanHeader = null;
	private Vector senaraiHakmilik = null;
	private Vector beanHakmilik = null;
	private Vector senaraiPemilik = null;
	private Vector beanPemilik = null;
	private Vector senaraiBebanan = null;
	private Vector beanBebanan = null;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static Logger mylog = Logger.getLogger(ekptg.model.htp.FrmHakmilikPerletakhakanData.class);

	public void carianFail(String noFail) throws Exception {
		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL,A.NO_FAIL,A.TARIKH_DAFTAR_FAIL,B.TUJUAN,C.KETERANGAN, E.NAMA_NEGERI"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLRUJSTATUS C, TBLHTPPERMOHONAN D, TBLRUJNEGERI E"
				+ " WHERE A.ID_SEKSYEN = 3 AND A.ID_URUSAN = 5 AND A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = D.ID_PERMOHONAN"
				+ " AND C.ID_STATUS = A.ID_STATUS AND A.ID_NEGERI = E.ID_NEGERI";
			  
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}			
			
			sql = sql + " ORDER BY A.ID_FAIL DESC";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idFail", rs.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL")== null ? "" : rs.getString("NO_FAIL"));
				h.put("tarikhDaftar", rs.getString("TARIKH_DAFTAR_FAIL") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("tujuan", rs.getString("TUJUAN") == null ? "" : rs.getString("TUJUAN"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				
				senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatHeader(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanHeader = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, A.NO_FAIL,A.ID_NEGERI,A.ID_SUBURUSAN,A.TARIKH_DAFTAR_FAIL" +
			" , B.ID_PERMOHONAN,B.TUJUAN,B.NO_PERMOHONAN" +
			" ,C.TARIKH_AGIHAN,C.NO_RUJUKAN_KJP,C.NO_RUJUKAN_LAIN,D.NAMA_KEMENTERIAN" +
			" ,F.NAMA_AGENSI,G.NAMA_SUBURUSAN,RD.NAMA_DAERAH"+
			" ,RN.NAMA_NEGERI FROM TBLPFDFAIL A, TBLPERMOHONAN B,TBLHTPPERMOHONAN C, TBLRUJKEMENTERIAN D" +
			" , TBLRUJAGENSI F, TBLRUJSUBURUSAN G,TBLRUJNEGERI RN,TBLRUJDAERAH RD "+
			" WHERE A.ID_FAIL = B.ID_FAIL(+) AND B.ID_PERMOHONAN = C.ID_PERMOHONAN(+) "+
			" AND A.ID_KEMENTERIAN = D.ID_KEMENTERIAN(+) "+
			" AND C.ID_AGENSI = F.ID_AGENSI(+) "+
			" AND A.ID_SUBURUSAN = G.ID_SUBURUSAN(+)"+
			" AND RN.ID_NEGERI=A.ID_NEGERI "+
			" AND C.ID_DAERAH = RD.ID_DAERAH(+) "+
			" AND A.ID_FAIL = '" + idFail + "'";

			mylog.info("setMaklumatHeader:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL").toUpperCase());
				h.put("txtNoFailSeksyen", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("noP", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN").toUpperCase());
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());
				h.put("txtTajuk", rs.getString("TUJUAN") == null ? "" : rs.getString("TUJUAN").toUpperCase());
				h.put("txdTarikhSurKJP", rs.getDate("TARIKH_AGIHAN") == null ? "" : sdf.format(rs.getDate("TARIKH_AGIHAN")));
				h.put("txtNoFailKJP", rs.getString("NO_RUJUKAN_KJP") == null ? "" : rs.getString("NO_RUJUKAN_KJP").toUpperCase());
				h.put("txtNoFailLain", rs.getString("NO_RUJUKAN_LAIN") == null ? "" : rs.getString("NO_RUJUKAN_LAIN").toUpperCase());
				h.put("id_negeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase());
				h.put("txtNamaKementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("txtNamaAgensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				h.put("txtNamaSuburusan", rs.getString("NAMA_SUBURUSAN") == null ? "" : rs.getString("NAMA_SUBURUSAN").toUpperCase());
				h.put("txdTarikhBukaFail", rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("txdNamaNegeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				h.put("txdNamaDaerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH"));

				beanHeader.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatSenaraiHakmilik(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			senaraiHakmilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_HAKMILIKURUSAN, B.NAMA_NEGERI, D.NAMA_DAERAH, E.NAMA_MUKIM, C.KOD_JENIS_HAKMILIK, A.NO_HAKMILIK, F.KETERANGAN, A.NO_LOT"
				+ " FROM TBLHTPHAKMILIKURUSAN A, TBLRUJNEGERI B, TBLRUJJENISHAKMILIK C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLRUJLOT F"
				+ " WHERE A.ID_NEGERI = B.ID_NEGERI AND A.ID_DAERAH = D.ID_DAERAH AND A.ID_MUKIM = E.ID_MUKIM AND A.ID_LOT = F.ID_LOT AND A.ID_JENISHAKMILIK = C.ID_JENISHAKMILIK"
				+ " AND A.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil",bil);
				h.put("idHakmilikurusan",rs.getString("ID_HAKMILIKURUSAN"));
				h.put("negeri",rs.getString("NAMA_NEGERI")== null?"":rs.getString("NAMA_NEGERI"));
				h.put("daerah",rs.getString("NAMA_DAERAH")== null?"":rs.getString("NAMA_DAERAH"));
				h.put("mukim",rs.getString("NAMA_MUKIM")== null?"":rs.getString("NAMA_MUKIM"));
				h.put("noHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null ? "" : 
					rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " + rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("noLot",rs.getString("KETERANGAN") == null || rs.getString("NO_LOT") == null ? "" : 
					rs.getString("KETERANGAN").toUpperCase() + " " + rs.getString("NO_LOT").toUpperCase());
				
				senaraiHakmilik.addElement(h);
				bil++;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String saveHakmilik(String idPermohonan,String txtLokasi, String txtCukaiSemasa,String txtSyaratNyata,String txtNoHakmilik,String txtNoStrata,String txtNoPelan,String txtLuasBersaman,String txtNoSyit,String txtSekatan,String txtLot,String txtLuas,String idNegeriPemohon, String idLuas,String idDaerah, String idLot,String idKategori,String idMukim,String idJenisHakmilik, 
			HttpSession session) throws Exception {
	
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idHakmilikurusanString = "";
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			//TBLHTPHAKMILIKURUSAN
		    r = new SQLRenderer();
		    long idHakmilikUrusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
		    idHakmilikurusanString = String.valueOf(idHakmilikUrusan);
		    r.add("id_hakmilikurusan",idHakmilikUrusan);
		    r.add("id_permohonan",idPermohonan);
		    r.add("id_luas",idLuas);
		    r.add("pegangan_hakmilik","jkptg");
		    r.add("id_subkategori",2);
		    r.add("id_kategori",idKategori);
		    r.add("id_daerah",idDaerah);
		    r.add("id_negeri",idNegeriPemohon);
		    r.add("id_mukim",idMukim);
		    r.add("id_lot",idLot);
		    r.add("no_lot",txtLot);
		    r.add("luas",txtLuas);
		    r.add("id_jenishakmilik",idJenisHakmilik);
		    r.add("no_hakmilik",txtNoHakmilik);
		    r.add("cukai_terkini", Utils.RemoveSymbol(txtCukaiSemasa));
		    r.add("no_pelan",txtNoPelan);
		    r.add("no_syit",txtNoSyit);
		    r.add("lokasi",txtLokasi);
		    r.add("luas_bersamaan",txtLuasBersaman);
		    r.add("sekatan",txtSekatan);
		    r.add("syarat",txtSyaratNyata);
		   
		    r.add("id_masuk",userId);
		    r.add("tarikh_masuk",r.unquote("SYSDATE"));
	
		    sql = r.getSQLInsert("TBLHTPHAKMILIKURUSAN");
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
		return idHakmilikurusanString;
	}
	
	public void setMaklumatHakmilik(String idHakmilikurusan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanHakmilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_HAKMILIKURUSAN,A.ID_NEGERI,A.ID_DAERAH, A.ID_MUKIM,A.ID_LUAS,A.ID_LOT,A.NO_LOT,A.LUAS,A.ID_KATEGORI,A.NO_HAKMILIK, A.ID_JENISHAKMILIK, A.ID_KATEGORI, A.LOKASI,A.NO_PELAN,A.NO_SYIT,A.LUAS_BERSAMAAN,A.SEKATAN,A.SYARAT,A.CUKAI_TERKINI,B.ID_PERMOHONAN, E.ID_FAIL "
					+ "FROM TBLHTPHAKMILIKURUSAN A, TBLPERMOHONAN B, TBLHTPPERMOHONAN C, TBLPFDFAIL E "
					+ "WHERE E.ID_FAIL = B.ID_FAIL (+)"
					+ "AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "
					+ "AND A.ID_PERMOHONAN = B.ID_PERMOHONAN "
					+ "AND A.ID_HAKMILIKURUSAN =" + idHakmilikurusan;

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? "": rs.getString("NO_HAKMILIK"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				h.put("ID_LOT", rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("ID_LUAS", rs.getString("ID_LUAS") == null ? "" : rs.getString("ID_LUAS"));
				h.put("LUAS", rs.getString("LUAS") == null ? "" : rs.getString("LUAS"));
				h.put("ID_KATEGORI", rs.getString("ID_KATEGORI") == null ? "": rs.getString("ID_KATEGORI"));
				h.put("ID_JENISHAKMILIK",rs.getString("ID_JENISHAKMILIK") == null ? "" : rs.getString("ID_JENISHAKMILIK"));
				h.put("ID_KATEGORI", rs.getString("ID_KATEGORI") == null ? "": rs.getString("ID_KATEGORI"));
				h.put("ID_HAKMILIKURUSAN", rs.getString("ID_HAKMILIKURUSAN"));
				h.put("LOKASI", rs.getString("LOKASI") == null ? "" : rs.getString("LOKASI"));
				h.put("NO_PELAN", rs.getString("NO_PELAN") == null ? "" : rs.getString("NO_PELAN"));
				h.put("NO_SYIT", rs.getString("NO_SYIT") == null ? "" : rs.getString("NO_SYIT"));
				h.put("LUAS_BERSAMAAN",rs.getString("LUAS_BERSAMAAN") == null ? "" : rs.getString("LUAS_BERSAMAAN"));
				h.put("SEKATAN", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN"));
				h.put("CUKAI_TERKINI",rs.getString("CUKAI_TERKINI") == null ? "" : Util.formatDecimal(Double.valueOf(rs.getString("CUKAI_TERKINI"))));
				h.put("SYARAT", rs.getString("SYARAT") == null ? "" : rs.getString("SYARAT"));
				beanHakmilik.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateHakmilik(String idHakmilikUrusan,String txtLokasi, String txtCukaiSemasa,String txtSyaratNyata,String txtNoHakmilik,String txtNoStrata,String txtNoPelan,String txtLuasBersamaan,String txtNoSyit,String txtSekatan,String txtLot,String txtLuas,String idNegeriPemohon, String idLuas,String idDaerah, String idLot,String idKategori,String idMukim,String idJenisHakmilik, 
			HttpSession session) throws Exception {
	
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
		    
			//TBLHTPHAKMILIKURUSAN
		    r = new SQLRenderer();

		    r.update("id_hakmilikurusan",idHakmilikUrusan);
		    r.add("id_luas",idLuas);
		    r.add("pegangan_hakmilik","jkptg");
		    r.add("id_subkategori",2);
		    r.add("id_kategori",idKategori);
		    r.add("id_daerah",idDaerah);
		    r.add("id_negeri",idNegeriPemohon);
		    r.add("id_mukim",idMukim);
		    r.add("id_lot",idLot);
		    r.add("no_lot",txtLot);
		    r.add("luas",txtLuas);
		    r.add("id_jenishakmilik",idJenisHakmilik);
		    r.add("no_hakmilik",txtNoHakmilik);
		    r.add("cukai_terkini", Utils.RemoveSymbol(txtCukaiSemasa));
		    r.add("no_pelan",txtNoPelan);
		    r.add("no_syit",txtNoSyit);
		    r.add("lokasi",txtLokasi);
		    r.add("luas_bersamaan",txtLuasBersamaan);
		    r.add("sekatan",txtSekatan);
		    r.add("syarat",txtSyaratNyata);
		   
		    r.add("id_kemaskini",userId);
		    r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
	
		    sql = r.getSQLUpdate("TBLHTPHAKMILIKURUSAN");
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
	
	public void setMaklumatSenaraiPemilik(String idHakmilikurusan) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			senaraiPemilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PIHAKBERKEPENTINGAN, NAMA, NO_RUJUKAN"
				+ " FROM TBLHTPPIHAKBERKEPENTINGAN WHERE ID_HAKMILIKURUSAN = '" + idHakmilikurusan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil",bil);
				h.put("idPihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN"));
				h.put("namaPemilik",rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));				
				h.put("noPengenalan", rs.getString("NO_RUJUKAN") == null ? "" : rs.getString("NO_RUJUKAN"));
				senaraiPemilik.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatPemilik(String idPihakberkepentingan) throws Exception {
		Db db = null;
		String sql = "";
		

		try {
			beanPemilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PIHAKBERKEPENTINGAN, ID_NEGERI, ID_DAERAH, NAMA, ALAMAT1, ALAMAT2, ALAMAT3, POSKOD, NO_RUJUKAN "+
				  "FROM TBLHTPPIHAKBERKEPENTINGAN WHERE ID_PIHAKBERKEPENTINGAN = '" + idPihakberkepentingan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_PIHAKBERKEPENTINGAN",rs.getString("ID_PIHAKBERKEPENTINGAN"));
				h.put("ID_NEGERI",rs.getString("ID_NEGERI")== null ? "99999" : rs.getString("ID_NEGERI"));
				h.put("ID_DAERAH",rs.getString("ID_DAERAH")== null ? "99999" : rs.getString("ID_DAERAH"));
				h.put("NAMA",rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
				h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
				h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
				h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("NO_PENGENALAN", rs.getString("NO_RUJUKAN") == null ? "" : rs.getString("NO_RUJUKAN"));
				beanPemilik.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String simpanPemilik(String idHakmilikurusan, String txtNama, String txtNoPengenalan, String txtAlamat1,String txtAlamat2,String txtAlamat3,String txtPoskod,String idNegeri,String idDaerah, HttpSession session) throws Exception {
		
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idPihakBerkepentinganString = "";
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
		    //TBLHTPPIHAKBERKEPENTINGAN
		    r = new SQLRenderer();
		    long idPihakberkepentingan = DB.getNextID("TBLHTPPIHAKBERKEPENTINGAN_SEQ");
		    idPihakBerkepentinganString = String.valueOf(idPihakberkepentingan);
		    r.add("id_Pihakberkepentingan",idPihakberkepentingan);
		    r.add("id_hakmilikurusan",idHakmilikurusan);
		    r.add("id_JenisNoPB",1);
		    r.add("id_JenisPB",1);
		    r.add("id_daerah",idDaerah);
		    r.add("id_negeri",idNegeri);
		    r.add("nama",txtNama);
		    r.add("no_rujukan",txtNoPengenalan);
		    r.add("alamat1",txtAlamat1);
		    r.add("alamat2",txtAlamat2);
		    r.add("alamat3",txtAlamat3);
		    r.add("poskod",txtPoskod);
		    
		    r.add("id_masuk",userId);
		    r.add("tarikh_masuk",r.unquote("SYSDATE"));
		    
		    sql = r.getSQLInsert("TBLHTPPIHAKBERKEPENTINGAN");
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
		return idPihakBerkepentinganString;
	}
	
	public void updatePemilik(String idPihakberkepentingan, String txtNama, String txtNoPengenalan, String txtAlamat1,String txtAlamat2,String txtAlamat3,String txtPoskod,String idNegeri,String idDaerah, HttpSession session) throws Exception {
		
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
			
		    //TBLHTPPIHAKBERKEPENTINGAN
		    r.update("id_Pihakberkepentingan",idPihakberkepentingan);

		    r.add("id_JenisNoPB",1);
		    r.add("id_JenisPB",1);
		    r.add("id_daerah",idDaerah);
		    r.add("id_negeri",idNegeri);
		    r.add("nama",txtNama);
		    r.add("no_rujukan",txtNoPengenalan);
		    r.add("alamat1",txtAlamat1);
		    r.add("alamat2",txtAlamat2);
		    r.add("alamat3",txtAlamat3);
		    r.add("poskod",txtPoskod);
		    
		    r.add("id_kemaskini",userId);
		    r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
		    
		    sql = r.getSQLUpdate("TBLHTPPIHAKBERKEPENTINGAN");
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
	
	public void setMaklumatSenaraiBebanan(String idPihakberkepentingan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			senaraiBebanan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_BEBANAN, A.NO_PERSERAHAN, A.NAMA_PIHAK_BERKEPENTINGAN, A.TARIKH_DAFTAR, B.KETERANGAN"
				+ " FROM TBLHTPBEBANAN A, TBLRUJBEBANAN B"
				+ " WHERE A.ID_RUJBEBANAN = B.ID_BEBANAN "
				+ " AND A.ID_PIHAKBERKEPENTINGAN = '" + idPihakberkepentingan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil",bil);
				h.put("noPerserahan",rs.getString("NO_PERSERAHAN") == null ? "" : rs.getString("NO_PERSERAHAN"));
				h.put("namaPB",rs.getString("NAMA_PIHAK_BERKEPENTINGAN") == null ? "" : rs.getString("NAMA_PIHAK_BERKEPENTINGAN"));
				h.put("keterangan",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR")));
				h.put("idBebanan", rs.getString("ID_BEBANAN"));				
				senaraiBebanan.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatBebanan(String idBebanan) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			beanBebanan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_BEBANAN, NAMA_PIHAK_BERKEPENTINGAN, NO_PERSERAHAN, JILID, FOLIO, TARIKH_DAFTAR, ALAMAT1, ALAMAT2, ALAMAT3, POSKOD,"
				+ " ID_DAERAH, ID_NEGERI, ID_RUJBEBANAN, ID_JENISPB, NO_PIHAK_BERKEPENTINGAN FROM TBLHTPBEBANAN WHERE ID_BEBANAN = '" + idBebanan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_BEBANAN",rs.getString("ID_BEBANAN"));
				h.put("NAMA_PIHAK_BERKEPENTINGAN",rs.getString("NAMA_PIHAK_BERKEPENTINGAN")== null?"":rs.getString("NAMA_PIHAK_BERKEPENTINGAN"));
				h.put("NO_PERSERAHAN", rs.getString("NO_PERSERAHAN")== null?"":rs.getString("NO_PERSERAHAN"));
				h.put("JILID",rs.getString("JILID") == null?"":rs.getString("JILID"));
				h.put("FOLIO", rs.getString("FOLIO") == null ? "" : rs.getString("FOLIO"));
				h.put("TARIKH_DAFTAR", rs.getString("TARIKH_DAFTAR")== null?"":sdf.format(rs.getDate("TARIKH_DAFTAR")));
				h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
				h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
				h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
				h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));				
				h.put("NO_PIHAK_BERKEPENTINGAN", rs.getString("NO_PIHAK_BERKEPENTINGAN") == null ? "" : rs.getString("NO_PIHAK_BERKEPENTINGAN"));
				h.put("ID_RUJBEBANAN", rs.getString("ID_RUJBEBANAN") == null ? "" : rs.getString("ID_RUJBEBANAN"));
				h.put("ID_JENISPB", rs.getString("ID_JENISPB") == null ? "" : rs.getString("ID_JENISPB"));
				beanBebanan.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String simpanBebanan(String idPihakberkepentingan, String txtNoPerserahan,String txdTarikhDaftar,String txtNamaPb, String txtAlamat1,String txtAlamat2,String txtAlamat3,String txtPoskod,String txtJilid,String txtFolio,String txtNoPB,String idNegeri,String idDaerah,String idJenisBebanan, String idJenisPB, HttpSession session) throws Exception {
		
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idBebananString = "";
		SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
		String tarikhDaftar = "to_date('" + txdTarikhDaftar + "','dd/MM/yyyy')";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
		    //TBLHTPBEBANAN
		    r = new SQLRenderer();
		    long idBebanan = DB.getNextID("TBLHTPBEBANAN_SEQ");
		    idBebananString = String.valueOf(idBebanan);
		    r.add("id_Bebanan",idBebanan);
		    r.add("id_pihakberkepentingan",idPihakberkepentingan);
		    r.add("no_Perserahan",txtNoPerserahan);
		    r.add("jilid",txtJilid);
		    r.add("folio",txtFolio);
		    r.add("id_negeri",idNegeri);
		    r.add("id_daerah",idDaerah);
		    r.add("id_rujBebanan",idJenisBebanan);
		    r.add("id_jenisPB", idJenisPB);
		    r.add("nama_pihak_berkepentingan",txtNamaPb);
		    r.add("no_pihak_berkepentingan",txtNoPB);
		    if (txdTarikhDaftar != null){
		    	r.add("tarikh_daftar",r.unquote(tarikhDaftar));
		    }		    
		    r.add("alamat1",txtAlamat1);
		    r.add("alamat2",txtAlamat2);
		    r.add("alamat3",txtAlamat3);
		    r.add("poskod",txtPoskod);
		    
		    r.add("id_masuk",userId);
		    r.add("tarikh_masuk",r.unquote("SYSDATE"));
		    
		    sql = r.getSQLInsert("TBLHTPBEBANAN");
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
		return idBebananString;
	}
	
	public String simpanUpdateBebanan(String idBebanan, String txtNoPerserahan,String txdTarikhDaftar,String txtNamaPb, String txtAlamat1,String txtAlamat2,String txtAlamat3,String txtPoskod,String txtJilid,String txtFolio,String txtNoPB,String idNegeri,String idDaerah,String idJenisBebanan, String idJenisPB, HttpSession session) throws Exception {
		
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idBebananString = "";
		SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
		String tarikhDaftar = "to_date('" + txdTarikhDaftar + "','dd/MM/yyyy')";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
		    //TBLHTPBEBANAN
		    r = new SQLRenderer();
		    r.update("id_Bebanan",idBebanan);
		    r.add("no_Perserahan",txtNoPerserahan);
		    r.add("jilid",txtJilid);
		    r.add("folio",txtFolio);
		    r.add("id_negeri",idNegeri);
		    r.add("id_daerah",idDaerah);
		    r.add("id_rujBebanan",idJenisBebanan);
		    r.add("id_jenisPB", idJenisPB);
		    r.add("nama_pihak_berkepentingan",txtNamaPb);
		    r.add("no_pihak_berkepentingan",txtNoPB);
		    if (txdTarikhDaftar != null){
		    	r.add("tarikh_daftar",r.unquote(tarikhDaftar));
		    }		    
		    r.add("alamat1",txtAlamat1);
		    r.add("alamat2",txtAlamat2);
		    r.add("alamat3",txtAlamat3);
		    r.add("poskod",txtPoskod);
		    
		    r.add("id_kemaskini",userId);
		    r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
		    
		    sql = r.getSQLUpdate("TBLHTPBEBANAN");
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
		return idBebananString;
	}

	public Vector getSenaraiFail() {
		return senaraiFail;
	}

	public void setSenaraiFail(Vector senaraiFail) {
		this.senaraiFail = senaraiFail;
	}

	public Vector getBeanHeader() {
		return beanHeader;
	}

	public void setBeanHeader(Vector beanHeader) {
		this.beanHeader = beanHeader;
	}

	public Vector getSenaraiHakmilik() {
		return senaraiHakmilik;
	}

	public void setSenaraiHakmilik(Vector senaraiHakmilik) {
		this.senaraiHakmilik = senaraiHakmilik;
	}

	public Vector getBeanHakmilik() {
		return beanHakmilik;
	}

	public void setBeanHakmilik(Vector beanHakmilik) {
		this.beanHakmilik = beanHakmilik;
	}

	public Vector getSenaraiPemilik() {
		return senaraiPemilik;
	}

	public void setSenaraiPemilik(Vector senaraiPemilik) {
		this.senaraiPemilik = senaraiPemilik;
	}

	public Vector getBeanPemilik() {
		return beanPemilik;
	}

	public void setBeanPemilik(Vector beanPemilik) {
		this.beanPemilik = beanPemilik;
	}

	public Vector getSenaraiBebanan() {
		return senaraiBebanan;
	}

	public void setSenaraiBebanan(Vector senaraiBebanan) {
		this.senaraiBebanan = senaraiBebanan;
	}

	public Vector getBeanBebanan() {
		return beanBebanan;
	}

	public void setBeanBebanan(Vector beanBebanan) {
		this.beanBebanan = beanBebanan;
	}
}


