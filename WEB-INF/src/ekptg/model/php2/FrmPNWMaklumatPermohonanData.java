/**
 * 
 */
package ekptg.model.php2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.php2.modelTable.TblPhpHakmilikModel;

/**
 * 
 *
 */
public class FrmPNWMaklumatPermohonanData {

	private Vector beanMaklumatPenawaran = null;
	private Vector listTanahBerkaitan = null;
	private Vector beanMaklumatPelan = null;
	private Vector listPelan = null;
	private static final Log log = LogFactory.getLog(FrmPNWMaklumatPermohonanData.class);
	
	FrmPNWPopupSenaraiTanahData logicTanah = new FrmPNWPopupSenaraiTanahData();

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public void saveMaklumatTanah(String idHakmilikAgensiTG, String idPermohonan, String idHakmilik, Vector beanMaklumatTanah, HttpSession session) throws Exception {
//		TblPhpHakmilikPermohonanModel phpHakmilikMohonModel = new TblPhpHakmilikPermohonanModel();
//		phpHakmilikMohonModel.setIdHakmilikAgensi(Long.parseLong(idHakmilikAgensiTG));
//		phpHakmilikMohonModel.setIdPermohonan(Long.parseLong(idPermohonan));
//		phpHakmilikMohonModel.setIdHakmilik(Long.parseLong(idHakmilik));
//		phpHakmilikMohonModel.setFlagPost("update");
//		String idHakmilikPermohonan = phpHakmilikMohonModel.save(phpHakmilikMohonModel, session);
		
		if (beanMaklumatTanah.size() > 0){
			TblPhpHakmilikModel phpHakmilikModel = new TblPhpHakmilikModel();
			Hashtable hashMaklumatTanah = (Hashtable) beanMaklumatTanah.get(0);
			phpHakmilikModel.setIdHakmilik(Long.parseLong(idHakmilik));
			phpHakmilikModel.setIdNegeri(Long.parseLong((String) hashMaklumatTanah.get("idNegeri")));
			phpHakmilikModel.setIdDaerah(Long.parseLong((String) hashMaklumatTanah.get("idDaerah")));
			phpHakmilikModel.setIdMukim(Long.parseLong((String) hashMaklumatTanah.get("idMukim")));
			phpHakmilikModel.setIdJenisHakmilik(Long.parseLong((String) hashMaklumatTanah.get("idJenisHakmilik")));
			phpHakmilikModel.setNoHakmilik((String) hashMaklumatTanah.get("noHakmilik"));
			phpHakmilikModel.setIdLot(Long.parseLong((String) hashMaklumatTanah.get("idLot")));
			phpHakmilikModel.setNoLot((String) hashMaklumatTanah.get("noLot"));
			phpHakmilikModel.setIdKategori(Long.parseLong((String) hashMaklumatTanah.get("idKategori")));
			phpHakmilikModel.setIdKementerian(Long.parseLong((String) hashMaklumatTanah.get("idKementerian")));
			phpHakmilikModel.setIdAgensi(Long.parseLong((String) hashMaklumatTanah.get("idAgensi")));
			phpHakmilikModel.setPeganganHakmilik((String) hashMaklumatTanah.get("peganganHakmilik"));
			phpHakmilikModel.setKegunaanTanah((String) hashMaklumatTanah.get("kegunaanTanah"));
			phpHakmilikModel.setIdSubkategori(Long.parseLong((String) hashMaklumatTanah.get("idSubKategori")));
			phpHakmilikModel.setSyarat((String) hashMaklumatTanah.get("syarat"));
			phpHakmilikModel.setSekatan((String) hashMaklumatTanah.get("sekatan"));
			phpHakmilikModel.setIdLuas(Long.parseLong((String) hashMaklumatTanah.get("idLuas")));
			String luas = (String) hashMaklumatTanah.get("luas");
			luas = luas.substring(0, luas.indexOf("HEKTAR")-1);
			phpHakmilikModel.setLuas(Double.parseDouble(luas));			
//			phpHakmilikModel.setIdHakmilikPermohonan(Long.parseLong(idHakmilikPermohonan));
			phpHakmilikModel.setFlagPost("update");
			phpHakmilikModel.save(phpHakmilikModel, session);
		}
	}
	
	
	
	public String carianIdPermohonan(String idFail) throws Exception {
		// TODO Auto-generated method stub
		
		Db db = null;
		String sql = "";
		try {
			db = new Db();

			sql = "SELECT ID_PERMOHONAN FROM TBLPERMOHONAN  WHERE ID_FAIL = '" + idFail + "'";
			
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_PERMOHONAN");
			}
			return "";

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
		
	}
	
	public void setSenaraiPelan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listPelan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN"
					+ " WHERE ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaPelan", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("catatanPelan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				listPelan.addElement(h);
				bil++;
				count++;
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void simpanKemaskiniDokumen(FileItem item,String idFail, String idPermohonan, String idDokumen, String txtNamaPelan, String txtCatatanPelan, String idHakmilikPelan, HttpSession session) throws Exception {
		Db db = null;
		Connection con = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		
		try {
			db = new Db();
			con = db.getConnection();
			con.setAutoCommit(false);

			if(idDokumen.equals("none")){
				long idDokumenNew = DB.getNextID("TBLPHPDOKUMEN_SEQ");
				PreparedStatement ps = con
						.prepareStatement("INSERT INTO TBLPHPDOKUMEN "
								+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,FLAG_DOKUMEN,ID_PERMOHONAN,ID_PHPHAKMILIK) "
								+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?,?,?)");
				ps.setLong(1, idDokumenNew);
				ps.setString(2, txtNamaPelan);
				ps.setString(3, txtCatatanPelan);
				ps.setString(4, userId);
				ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
				ps.setString(6, item.getContentType());
				ps.setString(7, item.getName());
				ps.setString(8, "I");
				ps.setString(9, idPermohonan);
				ps.setString(10, idHakmilikPelan);
				ps.executeUpdate();
			} else {
				PreparedStatement ps = con
						.prepareStatement("UPDATE TBLPHPDOKUMEN SET NAMA_DOKUMEN=?, CATATAN=?, CONTENT=?, JENIS_MIME=?, NAMA_FAIL=?, ID_KEMASKINI=?, TARIKH_KEMASKINI=SYSDATE, ID_PHPHAKMILIK=? WHERE ID_DOKUMEN=?");
				ps.setString(1, txtNamaPelan);
				ps.setString(2, txtCatatanPelan);
				ps.setBinaryStream(3, item.getInputStream(), (int) item.getSize());
				ps.setString(4, item.getContentType());
				ps.setString(5, item.getName());
				ps.setString(6, userId);
				ps.setString(7, idHakmilikPelan);
				ps.setString(8, idDokumen);
				ps.executeUpdate();
			}

			con.commit();
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void simpanKemaskiniDokumen(String idDokumen, String txtNamaPelan,
			String txtCatatanPelan, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPDOKUMEN
			r.update("ID_DOKUMEN", idDokumen);
			r.add("NAMA_DOKUMEN", txtNamaPelan);
			r.add("CATATAN", txtCatatanPelan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL PENAWARAN [" + idDokumen
							+ "] DIKEMASKINI");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void hapusDokumen(String idDokumen, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// TBLPHPDOKUMEN
			SQLRenderer r = new SQLRenderer();
			r.add("ID_DOKUMEN", idDokumen);

			sql = r.getSQLDelete("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "DEL",
					"FAIL PENAWARAN [" + idDokumen
							+ "] DIHAPUSKAN");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatPelan(String idDokumen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatPelan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN WHERE ID_DOKUMEN = '"
					+ idDokumen + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaPelan", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN").toUpperCase());
				h.put("catatanPelan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				beanMaklumatPelan.addElement(h);
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void doHapus(String idPermohonan, String idHakmilikPermohonan, HttpSession session)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPHAKMILIK
			r = new SQLRenderer();
			r.add("ID_HAKMILIKPERMOHONAN", idHakmilikPermohonan);
			sql = r.getSQLDelete("TBLPHPHAKMILIK");
			stmt.executeUpdate(sql);

			// TBLPHPHAKMILIKPERMOHONAN
			r = new SQLRenderer();
			r.add("ID_HAKMILIKPERMOHONAN", idHakmilikPermohonan);
			sql = r.getSQLDelete("TBLPHPHAKMILIKPERMOHONAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "DEL",
					"MAKLUMAT HAKMILIK [" + idHakmilikPermohonan
							+ "] DIHAPUSKAN");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setSenaraiTanahBerkaitan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listTanahBerkaitan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PHPHMP.ID_HAKMILIKAGENSI,  PHPHMP.ID_HAKMILIKPERMOHONAN, PHPHM.ID_HAKMILIK, PHPHM.PEGANGAN_HAKMILIK, PHPHM.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, PHPHM.NO_HAKMILIK,"
					+ " PHPHM.ID_LOT, RUJLOT.KETERANGAN AS JENIS_LOT, PHPHM.NO_LOT, PHPHM.ID_LUAS AS ID_LUAS_BERSAMAAN, PHPHM.LUAS AS LUAS_BERSAMAAN, RUJLUAS.KETERANGAN AS JENIS_LUAS,"
					+ " PHPHM.NO_WARTA, PHPHM.TARIKH_WARTA, PHPHM.ID_MUKIM, RUJMUKIM.NAMA_MUKIM, PHPHM.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, PHPHM.ID_NEGERI, RUJNEGERI.NAMA_NEGERI,"
					+ " PHPHM.ID_KATEGORI AS ID_KATEGORI, RUJKATEGORI.KETERANGAN AS KATEGORI, PHPHM.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, PHPHM.KEGUNAAN_TANAH, PHPHM.SYARAT, PHPHM.SEKATAN,"
					+ " PHPHM.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN,"
					+ " PHPHM.TARIKH_BORANGK, PHPHM.CATATAN, PHPHM.NO_PERSERAHAN, PHPHM.TARIKH_CATATAN, PHPHM.TARIKH_TERIMA,"
					+ " PHPHMP.FLAG_BORANGK"

					+ " FROM TBLPHPHAKMILIKPERMOHONAN PHPHMP, TBLPHPHAKMILIK PHPHM, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI, TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"

					+ " WHERE PHPHMP.ID_HAKMILIKPERMOHONAN = PHPHM.ID_HAKMILIKPERMOHONAN AND PHPHM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND PHPHM.ID_LOT = RUJLOT.ID_LOT(+)"
					+ " AND PHPHM.ID_LUAS = RUJLUAS.ID_LUAS(+) AND PHPHM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND PHPHM.ID_DAERAH = RUJDAERAH.ID_DAERAH(+) AND PHPHM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+)"
					+ " AND PHPHM.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND PHPHM.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+) AND PHPHM.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND PHPHMP.FLAG_HAKMILIK = 'T'"
					+ " AND PHPHMP.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idHakmilikPermohonan", rs
						.getString("ID_HAKMILIKPERMOHONAN") == null ? "" : rs
						.getString("ID_HAKMILIKPERMOHONAN").toUpperCase());
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK").toUpperCase());

				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("idJenisHakmilik",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("hakmilik",
						(rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase())
								+ " "
								+ (rs.getString("NO_HAKMILIK") == null ? ""
										: rs.getString("NO_HAKMILIK")));
				h.put("idLot",
						rs.getString("ID_LOT") == null ? "" : rs
								.getString("ID_LOT"));
				h.put("noLot",
						rs.getString("NO_LOT") == null ? "" : rs
								.getString("NO_LOT"));
				h.put("lot",
						(rs.getString("JENIS_LOT") == null ? "" : rs.getString(
								"JENIS_LOT").toUpperCase())
								+ " "
								+ (rs.getString("NO_LOT") == null ? "" : rs
										.getString("NO_LOT")));
				h.put("idLuas", rs.getString("ID_LUAS_BERSAMAAN") == null ? ""
						: rs.getString("ID_LUAS_BERSAMAAN"));
				h.put("luasBersamaan",
						rs.getString("LUAS_BERSAMAAN") == null ? "" : rs
								.getString("LUAS_BERSAMAAN"));
				h.put("luas",
						(rs.getString("LUAS_BERSAMAAN") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_BERSAMAAN")))
								+ " "
								+ (rs.getString("JENIS_LUAS") == null ? "" : rs
										.getString("JENIS_LUAS")));
				h.put("noWarta",
						rs.getString("NO_WARTA") == null ? "" : rs
								.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("idMukim",
						rs.getString("ID_MUKIM") == null ? "" : rs
								.getString("ID_MUKIM"));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs
						.getString("NAMA_MUKIM").toUpperCase());
				h.put("idDaerah",
						rs.getString("ID_DAERAH") == null ? "" : rs
								.getString("ID_DAERAH"));
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs
						.getString("NAMA_DAERAH").toUpperCase());
				h.put("idNegeri",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("idKategori", rs.getString("ID_KATEGORI") == null ? ""
						: rs.getString("ID_KATEGORI"));
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? ""
						: rs.getString("KATEGORI").toUpperCase());
				h.put("idSubKategori",
						rs.getString("ID_SUBKATEGORI") == null ? "" : rs
								.getString("ID_SUBKATEGORI"));
				h.put("subKategoriTanah",
						rs.getString("SUBKATEGORI") == null ? "" : rs
								.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs
						.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs
						.getString("SEKATAN").toUpperCase());
				h.put("kegunaanTanah",
						rs.getString("KEGUNAAN_TANAH") == null ? "" : rs
								.getString("KEGUNAAN_TANAH").toUpperCase());
				h.put("idKementerian",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs
								.getString("ID_KEMENTERIAN"));
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idAgensi",
						rs.getString("ID_AGENSI") == null ? "" : rs
								.getString("ID_AGENSI"));
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
				h.put("tarikhBorangK",
						rs.getDate("TARIKH_BORANGK") == null ? "" : sdf
								.format(rs.getDate("TARIKH_BORANGK")));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs
						.getString("CATATAN").toUpperCase());
				h.put("noPerserahan",
						rs.getString("NO_PERSERAHAN") == null ? "" : rs
								.getString("NO_PERSERAHAN").toUpperCase());
				h.put("tarikhCatatan",
						rs.getDate("TARIKH_CATATAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_CATATAN")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("flagBorangK", rs.getString("FLAG_BORANGK") == null ? ""
						: rs.getString("FLAG_BORANGK").toUpperCase());
				listTanahBerkaitan.addElement(h);
				bil++;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPenawaran(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPenawaran = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT C.ID_PHPPERMOHONANPELEPASAN, B.TARIKH_TERIMA, B.TARIKH_SURAT, B.NO_RUJ_SURAT, A.TAJUK_FAIL, C.ID_LUASASAL, C.LUAS_ASAL,"
					+ " D.KETERANGAN, C.FLAG_GUNA, C.ID_LUASMHN, C.LUAS_MHN1, C.LUAS_MHN2, C.LUAS_MHN3, C.ID_UNITLUASMHNBERSAMAAN, C.LUAS_MHNBERSAMAAN, C.ID_UNITLUASBAKI, C.LUAS_BAKI"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPERMOHONANPELEPASAN C, TBLRUJLUAS D"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_LUASASAL = D.ID_LUAS(+) AND B.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonanPelepasan", rs
						.getString("ID_PHPPERMOHONANPELEPASAN") == null ? ""
						: rs.getString("ID_PHPPERMOHONANPELEPASAN"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("perkara",
						rs.getString("TAJUK_FAIL") == null ? "" : rs
								.getString("TAJUK_FAIL"));
				h.put("flagGuna",
						rs.getString("FLAG_GUNA") == null ? "" : rs
								.getString("FLAG_GUNA"));
				h.put("luasAsal", rs.getString("LUAS_ASAL") == null ? ""
						: Utils.formatLuas(rs.getDouble("LUAS_ASAL")));
				h.put("keteranganLuasAsal",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
				h.put("idLuasMohon", rs.getString("ID_LUASMHN") == null ? ""
						: rs.getString("ID_LUASMHN"));
				h.put("luas1",
						rs.getString("LUAS_MHN1") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHN1")));
				h.put("luas2",
						rs.getString("LUAS_MHN2") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHN2")));
				h.put("luas3",
						rs.getString("LUAS_MHN3") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHN3")));
				h.put("luasBersamaan",
						rs.getString("LUAS_MHNBERSAMAAN") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHNBERSAMAAN")));
				h.put("luasBaki", rs.getString("LUAS_BAKI") == null ? ""
						: Utils.formatLuas(rs.getDouble("LUAS_BAKI")));
				beanMaklumatPenawaran.addElement(h);
				bil++;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void updatePermohonanPenawaran(String idFail, String idPermohonan,
			String tarikhTerima, String tarikhSurat, String txtPerkara,
			String idPermohonanPelepasan, String idLuasKegunaan, String idLuas,
			String txtLuasMohon1, String txtLuasMohon2, String txtLuasMohon3,
			String txtLuasBersamaan, String txtBakiLuas, String idHakmilikPermohonan, HttpSession session)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			String TT = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";
			String TS = "to_date('" + tarikhSurat + "','dd/MM/yyyy')";

			// TBLPFDFAIL
			r.update("ID_FAIL", idFail);
			r.add("TAJUK_FAIL", txtPerkara);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPFDFAIL");
			stmt.executeUpdate(sql);

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("TARIKH_SURAT", r.unquote(TS));
			r.add("TARIKH_TERIMA", r.unquote(TT));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPPERMOHONANPELEPASAN
			r = new SQLRenderer();
			r.add("FLAG_GUNA", idLuasKegunaan);
			r.add("ID_LUASMHN", idLuas);
			r.add("LUAS_MHN1", txtLuasMohon1);
			r.add("LUAS_MHN2", txtLuasMohon2);
			r.add("LUAS_MHN3", txtLuasMohon3);
			r.add("ID_UNITLUASMHNBERSAMAAN", "2");
			r.add("LUAS_MHNBERSAMAAN", txtLuasBersamaan);
			r.add("ID_UNITLUASBAKI", "2");
			r.add("LUAS_BAKI", txtBakiLuas);
			r.add("ID_PHPHAKMILIKPERMOHONAN", idHakmilikPermohonan);

			if(idPermohonanPelepasan.isEmpty()){
				long idPermohonanPelepasanNew = DB.getNextID("TBLPHPPERMOHONANPELEPASAN_SEQ");
				r.add("ID_PHPPERMOHONANPELEPASAN", idPermohonanPelepasanNew);
				r.add("ID_PERMOHONAN", idPermohonan);				
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLPHPPERMOHONANPELEPASAN");
			} else {
				r.update("ID_PHPPERMOHONANPELEPASAN", idPermohonanPelepasan);
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql = r.getSQLUpdate("TBLPHPPERMOHONANPELEPASAN");
			}

			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL PENAWARAN [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] DIKEMASKINI");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateStatus(String idFail, String idPermohonan,
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610199");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("AKTIF", "1");

			r.add("AKTIF", "0");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idSuburusanstatusfail = DB
					.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("32", "1610199")); // JABATAN
																				// TEKNIKAL
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
					"FAIL PENAWARAN [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] PROSES SETERUSNYA");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector getSenaraiSemak(String idPermohonan) throws Exception {

		Db db = null;
		String sql = "";
		Vector senaraiSemak = new Vector();
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_RUJSENARAISEMAK, KETERANGAN,"
					+ " CASE WHEN ID_RUJSENARAISEMAK IN (SELECT ID_RUJSENARAISEMAK FROM TBLPHPSENARAISEMAK WHERE ID_PERMOHONAN = '" + idPermohonan + "')"
					+ " THEN 'Y' END AS FLAG FROM TBLPHPRUJSENARAISEMAK"
					+ " WHERE FLAG_AKTIF = 'Y' AND ID_URUSAN = 6 AND ID_SUBURUSAN = 33";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idSenaraiSemak", rs.getString("ID_RUJSENARAISEMAK") == null ? "" : rs.getString("ID_RUJSENARAISEMAK"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("flag", rs.getString("FLAG") == null ? "" : rs.getString("FLAG"));
				senaraiSemak.addElement(h);
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	
		return senaraiSemak;
	}
	
	public void updateSenaraiSemak(String idPermohonan, String[] semaks, HttpSession session) throws Exception {
		
		String userId = (String) session.getAttribute("_ekptg_user_id");
		Db db = new Db();
		String sql = "";		
		
		try {
			Connection conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r = new SQLRenderer();
			
			r.add("ID_PERMOHONAN", idPermohonan);
			sql = r.getSQLDelete("TBLPHPSENARAISEMAK");
			stmt.executeUpdate(sql);
			
			for (int i = 0; i < semaks.length; i++) {
			 	r = new SQLRenderer();
				long ID_SENARAISEMAK = DB.getNextID("TBLPHPSENARAISEMAK_SEQ");
				r.add("ID_SENARAISEMAK", ID_SENARAISEMAK);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("ID_RUJSENARAISEMAK", semaks[i]);
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLPHPSENARAISEMAK");
				stmt.executeUpdate(sql);
			}
			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + idPermohonan + "] DIKEMASKINI");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doBatalPermohonan(String idFail, String idPermohonan,
			String tarikhBatal, String txtSebab, HttpSession session)
			throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610212");
			r.add("TARIKH_BATAL",
					r.unquote("to_date('" + tarikhBatal + "','dd/MM/yyyy')"));
			r.add("CATATAN_BATAL", txtSebab);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("AKTIF", "1");

			r.add("AKTIF", "0");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idSuburusanstatusfail = DB
					.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("32", "1610212")); // BATAL
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610212", "4", null, session, "UPD",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] DIBATALKAN");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void doHapusFail(String idFail, String idPermohonan,
			String tarikhHapus, String txtSebab, HttpSession session)
			throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "999");
			r.add("TARIKH_HAPUS",
					r.unquote("to_date('" + tarikhHapus + "','dd/MM/yyyy')"));
			r.add("CATATAN_HAPUS", txtSebab);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("AKTIF", "1");

			r.add("AKTIF", "0");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idSuburusanstatusfail = DB
					.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("34", "1610212")); // BATAL
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("999", "4", null, session, "UPD",
					"FAIL PENAWARAN [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] DIHAPUSKAN");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getNoFailByIdPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPFDFAIL.NO_FAIL FROM TBLPFDFAIL, TBLPERMOHONAN"
					+ " WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL"
					+ " AND TBLPERMOHONAN.ID_PERMOHONAN = '" + idPermohonan
					+ "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("NO_FAIL");
			} else {
				return "";
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdSuburusanstatus(String idSuburusan, String idStatus)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS = '"
					+ idStatus + "' AND ID_SUBURUSAN = '" + idSuburusan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_SUBURUSANSTATUS");
			} else {
				return "";
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getKodstatus(String idStatus)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_STATUS FROM TBLRUJSTATUS WHERE ID_STATUS = '" + idStatus + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_STATUS");
			} else {
				return "";
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatPenawaran() {
		return beanMaklumatPenawaran;
	}

	public void setBeanMaklumatPenawaran(Vector beanMaklumatPenawaran) {
		this.beanMaklumatPenawaran = beanMaklumatPenawaran;
	}

	public Vector getListTanahBerkaitan() {
		return listTanahBerkaitan;
	}

	public void setListTanahBerkaitan(Vector listTanahBerkaitan) {
		this.listTanahBerkaitan = listTanahBerkaitan;
	}

	public Vector getBeanMaklumatPelan() {
		return beanMaklumatPelan;
	}

	public void setBeanMaklumatPelan(Vector beanMaklumatPelan) {
		this.beanMaklumatPelan = beanMaklumatPelan;
	}

	public Vector getListPelan() {
		return listPelan;
	}

	public void setListPelan(Vector listPelan) {
		this.listPelan = listPelan;
	}

	/*public void setSenaraiTanahPelan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listPelan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT D.PEGANGAN_HAKMILIK, C.FLAG_HAKMILIK, D.ID_HAKMILIK, E.ID_PHPHAKMILIK " +
					" FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPHAKMILIKPERMOHONAN C, TBLPHPHAKMILIK D, TBLPHPDOKUMEN E " +
					" WHERE B.ID_FAIL = A.ID_FAIL AND C.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_HAKMILIKPERMOHONAN = C.ID_HAKMILIKPERMOHONAN "
					+ " AND D.ID_HAKMILIK = E.ID_PHPHAKMILIK AND B.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK"));
				h.put("flagHakmilik", rs.getString("FLAG_HAKMILIK") == null ? ""
						: rs.getString("FLAG_HAKMILIK"));
				h.put("idHakmilik",
						rs.getString("ID_HAKMILIK") == null ? "" : rs
								.getString("ID_HAKMILIK"));
				listPelan.addElement(h);
				bil++;
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}*/

	/*public Hashtable setMaklumatPelanByIdhakmilik(String idHakmilik) throws Exception {
		Db db = null;
		String sql = "";
		Hashtable hashMaklumatPelan = new Hashtable();
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN WHERE ID_PHPHAKMILIK = '"
					+ idHakmilik + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				hashMaklumatPelan.put("idDokumen", rs.getString("ID_DOKUMEN"));
				hashMaklumatPelan.put("catatanPelan", rs.getString("CATATAN") == null ? ""
						: rs.getString("CATATAN").toUpperCase());
				hashMaklumatPelan.put("namaDokumen",
						rs.getString("NAMA_DOKUMEN") == null ? "" : rs
								.getString("NAMA_DOKUMEN"));
			} else {
				hashMaklumatPelan.put("idDokumen", "none");
				hashMaklumatPelan.put("catatanPelan", "");
				hashMaklumatPelan.put("namaDokumen", "");
			}
			
			return hashMaklumatPelan;
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}*/

	public Vector setSenaraiTanahSemua(String idPermohonan, Vector senaraiTanahSemua) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT C.ID_HAKMILIKPERMOHONAN, D.PEGANGAN_HAKMILIK, C.FLAG_HAKMILIK, D.ID_HAKMILIK, D.NO_LOT, D.NO_HAKMILIK, D.NO_WARTA, RUJMUKIM.NAMA_MUKIM, RUJDAERAH.NAMA_DAERAH, RUJNEGERI.NAMA_NEGERI " +
					" FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPHAKMILIKPERMOHONAN C, TBLPHPHAKMILIK D, TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI " +
					" WHERE B.ID_FAIL = A.ID_FAIL AND C.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_HAKMILIKPERMOHONAN = C.ID_HAKMILIKPERMOHONAN " +
					" AND D.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND D.ID_DAERAH = RUJDAERAH.ID_DAERAH(+) AND D.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) "
					+ " AND B.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK"));
				h.put("flagHakmilik", rs.getString("FLAG_HAKMILIK") == null ? ""
						: rs.getString("FLAG_HAKMILIK"));
				h.put("idHakmilik",
						rs.getString("ID_HAKMILIK") == null ? "" : rs
								.getString("ID_HAKMILIK"));
				h.put("noLot",
						rs.getString("NO_LOT") == null ? "" : rs
								.getString("NO_LOT"));
				h.put("noWarta",
						rs.getString("NO_WARTA") == null ? "" : rs
								.getString("NO_WARTA"));
				h.put("noHakmilik",
						rs.getString("NO_HAKMILIK") == null ? "" : rs
								.getString("NO_HAKMILIK"));
				h.put("namaMukim",
						rs.getString("NAMA_MUKIM") == null ? "" : rs
								.getString("NAMA_MUKIM"));
				h.put("namaDaerah",
						rs.getString("NAMA_DAERAH") == null ? "" : rs
								.getString("NAMA_DAERAH"));
				h.put("namaNegeri",
						rs.getString("NAMA_NEGERI") == null ? "" : rs
								.getString("NAMA_NEGERI"));
				h.put("idHakmilikPermohonan",
						rs.getString("ID_HAKMILIKPERMOHONAN") == null ? "" : rs
								.getString("ID_HAKMILIKPERMOHONAN"));
				senaraiTanahSemua.addElement(h);
				bil++;
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
		return senaraiTanahSemua;
	}

	public void setMaklumatPenawaranByIdHakmilik(String idHakmilikPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPenawaran = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT C.ID_PHPPERMOHONANPELEPASAN, B.TARIKH_TERIMA, B.TARIKH_SURAT, B.NO_RUJ_SURAT, A.TAJUK_FAIL, C.ID_LUASASAL, C.LUAS_ASAL,"
					+ " D.KETERANGAN, C.FLAG_GUNA, C.ID_LUASMHN, C.LUAS_MHN1, C.LUAS_MHN2, C.LUAS_MHN3, C.ID_UNITLUASMHNBERSAMAAN, C.LUAS_MHNBERSAMAAN, C.ID_UNITLUASBAKI, C.LUAS_BAKI"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPERMOHONANPELEPASAN C, TBLRUJLUAS D"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_LUASASAL = D.ID_LUAS(+) AND C.ID_PHPHAKMILIKPERMOHONAN = '"
					+ idHakmilikPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonanPelepasan", rs
						.getString("ID_PHPPERMOHONANPELEPASAN") == null ? ""
						: rs.getString("ID_PHPPERMOHONANPELEPASAN"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("perkara",
						rs.getString("TAJUK_FAIL") == null ? "" : rs
								.getString("TAJUK_FAIL"));
				h.put("flagGuna",
						rs.getString("FLAG_GUNA") == null ? "" : rs
								.getString("FLAG_GUNA"));
				h.put("luasAsal", rs.getString("LUAS_ASAL") == null ? ""
						: Utils.formatLuas(rs.getDouble("LUAS_ASAL")));
				h.put("keteranganLuasAsal",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
				h.put("idLuasMohon", rs.getString("ID_LUASMHN") == null ? ""
						: rs.getString("ID_LUASMHN"));
				h.put("luas1",
						rs.getString("LUAS_MHN1") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHN1")));
				h.put("luas2",
						rs.getString("LUAS_MHN2") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHN2")));
				h.put("luas3",
						rs.getString("LUAS_MHN3") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHN3")));
				h.put("luasBersamaan",
						rs.getString("LUAS_MHNBERSAMAAN") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHNBERSAMAAN")));
				h.put("luasBaki", rs.getString("LUAS_BAKI") == null ? ""
						: Utils.formatLuas(rs.getDouble("LUAS_BAKI")));
				beanMaklumatPenawaran.addElement(h);
				bil++;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void simpanDaftarHakmilikBaru(String idPermohonan,
			String idHakmilikAgensi, String idHakmilikSementara,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPHAKMILIKPERMOHONAN
			r = new SQLRenderer();
			long idhakmilikPermohonan = DB
					.getNextID("TBLPHPHAKMILIKPERMOHONAN_SEQ");
			r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_HAKMILIKAGENSI", idHakmilikAgensi);
			r.add("ID_HAKMILIKSEMENTARA", idHakmilikSementara);
			r.add("FLAG_HAKMILIK", "T");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPHAKMILIKPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPHAKMILIK
			logicTanah.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
			if (logicTanah.getBeanMaklumatTanah().size() != 0) {
				Hashtable hashTanah = (Hashtable) logicTanah.getBeanMaklumatTanah().get(0);

				r = new SQLRenderer();
				long idHakmilik = DB.getNextID("TBLPHPHAKMILIK_SEQ");
				r.add("ID_HAKMILIK", idHakmilik);
				r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
				r.add("PEGANGAN_HAKMILIK", hashTanah.get("peganganHakmilik"));
				r.add("ID_NEGERI", hashTanah.get("idNegeri"));
				r.add("ID_DAERAH", hashTanah.get("idDaerah"));
				r.add("ID_MUKIM", hashTanah.get("idMukim"));
				r.add("NO_WARTA", hashTanah.get("noWarta"));
				r.add("TARIKH_WARTA",
						r.unquote("to_date('" + hashTanah.get("tarikhWarta")
								+ "','dd/MM/yyyy')"));
				r.add("ID_JENISHAKMILIK", hashTanah.get("idJenisHakmilik"));
				r.add("NO_HAKMILIK", hashTanah.get("noHakmilik"));
				r.add("ID_LOT", hashTanah.get("idLot"));
				r.add("NO_LOT", hashTanah.get("noLot"));
				r.add("ID_LUAS", hashTanah.get("idLuas"));
				r.add("LUAS", hashTanah.get("luasBersamaan"));
				r.add("SYARAT", hashTanah.get("syarat"));
				r.add("SEKATAN", hashTanah.get("sekatan"));
				r.add("KEGUNAAN_TANAH", hashTanah.get("kegunaanTanah"));
				r.add("ID_KATEGORI", hashTanah.get("idKategori"));
				r.add("ID_SUBKATEGORI", hashTanah.get("idSubKategori"));
				r.add("ID_KEMENTERIAN", hashTanah.get("idKementerian"));
				r.add("ID_AGENSI", hashTanah.get("idAgensi"));

				sql = r.getSQLInsert("TBLPHPHAKMILIK");
				stmt.executeUpdate(sql);
				
				// TBLPHPLAPORANTANAH
				r = new SQLRenderer();
				long idLaporanTanah = DB.getNextID("TBLPHPLAPORANTANAH_SEQ");
				r.add("ID_LAPORANTANAH", idLaporanTanah);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("FLAG_JENISTANAH", "P"); // TANAH DIPOHON
				r.add("FLAG_LAPORAN", "1"); // LAWATAN TAPAK
				r.add("ID_HAKMILIK", idhakmilikPermohonan);

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLPHPLAPORANTANAH");
				stmt.executeUpdate(sql);	

			}

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "INS",
					"FAIL PENAWARAN [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] DIDAFTARKAN");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}
	
}
