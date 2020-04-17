/**
 * 
 */
package ekptg.model.php2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 
 */
public class FrmPLPPopupCetakLaporanData {

	private Vector beanMaklumatPegawai = null;
	private Vector beanNamaPejabat = null;
	private Vector beanDuitRM = null;
	private Vector beanMesyuarat = null;
	private Vector beanAlamat = null;
	private Vector beanMaklumatPejabat = null;
	private Vector beanMaklumatMesyuarat = null;
	private static final Log log = LogFactory.getLog(FrmPLPPopupCetakLaporanData.class);

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public String getIdNegeriByIdFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();

			sql = "SELECT ID_NEGERI FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail
					+ "'";

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_NEGERI");
				
			}
			return "";

		}catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}
	public String getNoFailByIdFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();

			sql = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail
					+ "'";

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("NO_FAIL");
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

	/*public void setMaklumatPegawai(String idPegawai) throws Exception {
		Db db = null;
		String sql = "";
		try {
			beanMaklumatPegawai = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("NAMA_PEGAWAI");
			r.add("JAWATAN");
			r.add("NO_TEL_PEJABAT");
			r.add("NO_FAKS");
			r.add("EMEL");

			r.add("ID_PEGAWAI", idPegawai);

			sql = r.getSQLSelect("TBLRUJPEGAWAI");
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();

				h.put("nama", rs.getString("NAMA_PEGAWAI") == null ? "" : rs
						.getString("NAMA_PEGAWAI").toUpperCase());
				h.put("jawatan", rs.getString("JAWATAN") == null ? "" : rs
						.getString("JAWATAN").toUpperCase());
				h.put("noTel", rs.getString("NO_TEL_PEJABAT") == null ? "" : rs
						.getString("NO_TEL_PEJABAT").toUpperCase());
				h.put("noFaks", rs.getString("NO_FAKS") == null ? "" : rs
						.getString("NO_FAKS").toUpperCase());
				h.put("emel",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));

				beanMaklumatPegawai.addElement(h);
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}*/
	
	//GET MAKLUMAT PEGAWAI FROM USERS -AIN 24052017-
	public void setMaklumatPegawai(String idPegawai) throws Exception {
		Db db = null;

		String sql = "SELECT A.USER_NAME AS NAMA_PEGAWAI, C.KETERANGAN AS JAWATAN, B.NO_TEL_PEJABAT, " +
				" B.NO_FAKS, B.EMEL" +
				" FROM USERS A, USERS_INTERNAL B, TBLRUJJAWATAN C" +
				" WHERE A.USER_ID = B.USER_ID" +
				" AND B.ID_JAWATAN = C.ID_JAWATAN(+)" +
				" AND A.USER_ID ='" + idPegawai + "'";
		
		try {
			beanMaklumatPegawai = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();

				h.put("nama", rs.getString("NAMA_PEGAWAI") == null ? "" : rs
						.getString("NAMA_PEGAWAI").toUpperCase());
				h.put("jawatan", rs.getString("JAWATAN") == null ? "" : rs
						.getString("JAWATAN").toUpperCase());
				h.put("noTel", rs.getString("NO_TEL_PEJABAT") == null ? "" : rs
						.getString("NO_TEL_PEJABAT").toUpperCase());
				h.put("noFaks", rs.getString("NO_FAKS") == null ? "" : rs
						.getString("NO_FAKS").toUpperCase());
				h.put("emel",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));

				beanMaklumatPegawai.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatMesyuarat(String idMesyuarat) throws Exception {
		Db db = null;
		String sql = "";
		try {
			beanMaklumatMesyuarat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("TARIKH_MESYUARAT");
			r.add("TAJUK");

			r.add("ID_MESYUARAT", idMesyuarat);

			sql = r.getSQLSelect("TBLPHPMESYUARAT");
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();

				h.put("tarikhMesyuarat", rs.getString("TARIKH_MESYUARAT") == null ? "" : rs
						.getString("TARIKH_MESYUARAT").toUpperCase());
				h.put("tajuk", rs.getString("TAJUK") == null ? "" : rs
						.getString("TAJUK").toUpperCase());

				beanMaklumatMesyuarat.addElement(h);
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public void setMaklumatPejabatPTG(String idPegawaiPTG) throws Exception {
		Db db = null;
		String sql = "";
		try {
			beanMaklumatPejabat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
		
			
			
			sql = "SELECT A.NAMA_PEJABAT , A.ALAMAT1 , A.ALAMAT2 , A.ALAMAT3 , A.POSKOD , B.NAMA_NEGERI , C.NAMA_DAERAH  " +
				  " FROM TBLRUJPEJABAT A , TBLRUJNEGERI B , TBLRUJDAERAH C " +
				  " WHERE A.ID_NEGERI = B.ID_NEGERI AND A.ID_DAERAH = C.ID_DAERAH AND A.ID_PEJABAT = '" + idPegawaiPTG + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Hashtable h = new Hashtable();

				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "":rs.getString("ALAMAT1").toUpperCase());
				h.put("alamat2", rs.getString("ALAMAT2") == null ? "":rs.getString("ALAMAT2").toUpperCase());
				h.put("alamat3", rs.getString("ALAMAT3") == null ? "":rs.getString("ALAMAT3").toUpperCase());
				h.put("poskod", rs.getString("POSKOD") == null ? "":rs.getString("POSKOD"));
				h.put("namaDaerah", rs.getString("NAMA_DAERAH") == null ? "":rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("namaNegeri", rs.getString("NAMA_NEGERI") == null ? "":rs.getString("NAMA_NEGERI").toUpperCase());
				
				beanMaklumatPejabat.addElement(h);
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatPejabatPTD(String idPegawaiPTD) throws Exception {
		Db db = null;
		String sql = "";
		try {
			beanMaklumatPejabat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NAMA_PEJABAT , A.ALAMAT1 , A.ALAMAT2 , A.ALAMAT3 , A.POSKOD , B.NAMA_NEGERI , C.NAMA_DAERAH  " +
					  " FROM TBLRUJPEJABAT A , TBLRUJNEGERI B , TBLRUJDAERAH C " +
					  " WHERE A.ID_NEGERI = B.ID_NEGERI AND A.ID_DAERAH = C.ID_DAERAH AND A.ID_PEJABAT = '" + idPegawaiPTD + "'";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					Hashtable h = new Hashtable();

					h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
					h.put("alamat1", rs.getString("ALAMAT1") == null ? "":rs.getString("ALAMAT1").toUpperCase());
					h.put("alamat2", rs.getString("ALAMAT2") == null ? "":rs.getString("ALAMAT2").toUpperCase());
					h.put("alamat3", rs.getString("ALAMAT3") == null ? "":rs.getString("ALAMAT3").toUpperCase());
					h.put("poskod", rs.getString("POSKOD") == null ? "":rs.getString("POSKOD"));
					h.put("namaDaerah", rs.getString("NAMA_DAERAH") == null ? "":rs.getString("NAMA_DAERAH").toUpperCase());
					h.put("namaNegeri", rs.getString("NAMA_NEGERI") == null ? "":rs.getString("NAMA_NEGERI").toUpperCase());

				beanMaklumatPejabat.addElement(h);
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatPejabatPBT(String idPegawaiPBT) throws Exception {
		Db db = null;
		String sql = "";
		try {
			beanMaklumatPejabat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NAMA_PEJABAT , A.ALAMAT1 , A.ALAMAT2 , A.ALAMAT3 , A.POSKOD , B.NAMA_NEGERI , C.NAMA_DAERAH  " +
					  " FROM TBLRUJPEJABAT A , TBLRUJNEGERI B , TBLRUJDAERAH C " +
					  " WHERE A.ID_NEGERI = B.ID_NEGERI AND A.ID_DAERAH = C.ID_DAERAH AND A.ID_PEJABAT = '" + idPegawaiPBT + "'";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					Hashtable h = new Hashtable();

					h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
					h.put("alamat1", rs.getString("ALAMAT1") == null ? "":rs.getString("ALAMAT1").toUpperCase());
					h.put("alamat2", rs.getString("ALAMAT2") == null ? "":rs.getString("ALAMAT2").toUpperCase());
					h.put("alamat3", rs.getString("ALAMAT3") == null ? "":rs.getString("ALAMAT3").toUpperCase());
					h.put("poskod", rs.getString("POSKOD") == null ? "":rs.getString("POSKOD"));
					h.put("namaDaerah", rs.getString("NAMA_DAERAH") == null ? "":rs.getString("NAMA_DAERAH").toUpperCase());
					h.put("namaNegeri", rs.getString("NAMA_NEGERI") == null ? "":rs.getString("NAMA_NEGERI").toUpperCase());

				beanMaklumatPejabat.addElement(h);
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatPejabatJKPTG(String idPegawaiJKPTG) throws Exception {
		Db db = null;
		String sql = "";
		try {
			beanMaklumatPejabat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NAMA_PEJABAT , A.ALAMAT1 , A.ALAMAT2 , A.ALAMAT3 , A.POSKOD , B.NAMA_NEGERI , C.NAMA_DAERAH  " +
					  " FROM TBLRUJPEJABATJKPTG A , TBLRUJNEGERI B , TBLRUJDAERAH C " +
					  " WHERE A.ID_NEGERI = B.ID_NEGERI AND A.ID_DAERAH = C.ID_DAERAH AND A.ID_PEJABATJKPTG = '" + idPegawaiJKPTG + "'";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					Hashtable h = new Hashtable();

					h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
					h.put("alamat1", rs.getString("ALAMAT1") == null ? "":rs.getString("ALAMAT1").toUpperCase());
					h.put("alamat2", rs.getString("ALAMAT2") == null ? "":rs.getString("ALAMAT2").toUpperCase());
					h.put("alamat3", rs.getString("ALAMAT3") == null ? "":rs.getString("ALAMAT3").toUpperCase());
					h.put("poskod", rs.getString("POSKOD") == null ? "":rs.getString("POSKOD"));
					h.put("namaDaerah", rs.getString("NAMA_DAERAH") == null ? "":rs.getString("NAMA_DAERAH").toUpperCase());
					h.put("namaNegeri", rs.getString("NAMA_NEGERI") == null ? "":rs.getString("NAMA_NEGERI").toUpperCase());

				beanMaklumatPejabat.addElement(h);
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatPejabatKJP(String idPegawaiKJP) throws Exception {
		Db db = null;
		String sql = "";
		try {
			beanMaklumatPejabat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NAMA_KEMENTERIAN , A.ALAMAT1 , A.ALAMAT2 , A.ALAMAT3 , A.POSKOD , B.NAMA_NEGERI " +
				  " FROM TBLRUJKEMENTERIAN A , TBLRUJNEGERI B ,TBLRUJAGENSI C" +
				  " WHERE A.ID_KEMENTERIAN = C.ID_KEMENTERIAN AND A.ID_NEGERI = B.ID_NEGERI  AND " +
				  " C.ID_AGENSI = '" + idPegawaiKJP + "'";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					Hashtable h = new Hashtable();

					h.put("namaPejabat", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
					h.put("alamat1", rs.getString("ALAMAT1") == null ? "":rs.getString("ALAMAT1").toUpperCase());
					h.put("alamat2", rs.getString("ALAMAT2") == null ? "":rs.getString("ALAMAT2").toUpperCase());
					h.put("alamat3", rs.getString("ALAMAT3") == null ? "":rs.getString("ALAMAT3").toUpperCase());
					h.put("poskod", rs.getString("POSKOD") == null ? "":rs.getString("POSKOD"));
//					h.put("namaDaerah", rs.getString("NAMA_DAERAH") == null ? "":rs.getString("NAMA_DAERAH").toUpperCase());
					h.put("namaNegeri", rs.getString("NAMA_NEGERI") == null ? "":rs.getString("NAMA_NEGERI").toUpperCase());

				beanMaklumatPejabat.addElement(h);
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatPejabatLL(String idPegawaiLL) throws Exception {
		Db db = null;
		String sql = "";
		try {
			beanMaklumatPejabat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NAMA_PEJABAT , A.ALAMAT1 , A.ALAMAT2 , A.ALAMAT3 , A.POSKOD , B.NAMA_NEGERI , C.NAMA_DAERAH  " +
					  " FROM TBLRUJPEJABAT A , TBLRUJNEGERI B , TBLRUJDAERAH C " +
					  " WHERE A.ID_NEGERI = B.ID_NEGERI AND A.ID_DAERAH = C.ID_DAERAH AND A.ID_PEJABAT = '" + idPegawaiLL + "'";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					Hashtable h = new Hashtable();

					h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
					h.put("alamat1", rs.getString("ALAMAT1") == null ? "":rs.getString("ALAMAT1").toUpperCase());
					h.put("alamat2", rs.getString("ALAMAT2") == null ? "":rs.getString("ALAMAT2").toUpperCase());
					h.put("alamat3", rs.getString("ALAMAT3") == null ? "":rs.getString("ALAMAT3").toUpperCase());
					h.put("poskod", rs.getString("POSKOD") == null ? "":rs.getString("POSKOD"));
					h.put("namaDaerah", rs.getString("NAMA_DAERAH") == null ? "":rs.getString("NAMA_DAERAH").toUpperCase());
					h.put("namaNegeri", rs.getString("NAMA_NEGERI") == null ? "":rs.getString("NAMA_NEGERI").toUpperCase());

				beanMaklumatPejabat.addElement(h);
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatPejabat(String idPejabat) throws Exception {
		Db db = null;
		String sql = "";
		try {
			beanMaklumatPejabat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("NAMA_PEJABAT");
			r.add("ID_PEJABAT", idPejabat);

			sql = r.getSQLSelect("TBLRUJPEJABAT");
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();

				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? "" : rs
						.getString("NAMA_PEJABAT").toUpperCase());

				beanMaklumatPejabat.addElement(h);
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}
	
	
//	public void getNamaPejabat(String idNamaPejabat) throws Exception {
//		Db db = null;
//		String sql = "";
//
//		try {
//			beanNamaPejabat = new Vector();
//			db = new Db();
//			Statement stmt = db.getStatement();
//
//			sql = "SELECT PEJ.NAMA_PEJABAT FROM TBLRUJPEJABAT PEJ, TBLPHPHAKMILIK HKMILIK, TBLPHPHAKMILIKPERMOHONAN HKMPER, TBLPERMOHONAN MOHON, TBLPFDFAIL FAIL "
//				+ "WHERE PEJ.ID_NEGERI = HKMILIK.ID_NEGERI " 
//				+ "AND PEJ.ID_JENISPEJABAT = 25 "
//				+ "AND HKMILIK.ID_HAKMILIKPERMOHONAN  = HKMPER.ID_HAKMILIKPERMOHONAN " 
//				+ "AND HKMPER.ID_PERMOHONAN = MOHON.ID_PERMOHONAN "
//				+ "AND MOHON.ID_FAIL = FAIL.ID_FAIL "
//				+ "AND FAIL.NO_FAIL = '" +idNamaPejabat + "'";
//			ResultSet rs = stmt.executeQuery(sql);
//
//			Hashtable h;
//			int bil = 1;
//			while (rs.next()) {
//				h = new Hashtable();
//
//				h.put("namaPejabat",rs.getString("PEJ.NAMA_PEJABAT") == null ? " x" : rs.getString("PEJ.NAMA_PEJABAT"));
//				bil++;
//			}
//
//		} finally {
//			if (db != null)
//				db.close();
//		}
//	}
	public Integer getFlagPerenggan2Keputusan(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NVL(A.PERENGGAN2,0) AS PERENGGAN2 FROM TBLPHPPERMOHONANPELEPASAN A, TBLPERMOHONAN B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = '"
					+ idFail + "'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			return Integer.parseInt(rs.getString("PERENGGAN2"));

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void setDuitRM(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanDuitRM = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT (NILAIAN+NILAIAN_BANGUNAN) AS TOTAL"
					+ " FROM TBLPHPHAKMILIKPERMOHONAN "
					+ " WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();

				h.put("nilaian",
						rs.getString("TOTAL") == null ? "0.00" : rs
								.getString("TOTAL"));
				beanDuitRM.addElement(h);
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

	public void getMesyuarat(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			beanMesyuarat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT MAX (TO_CHAR (A.TARIKH_MESYUARAT, 'DD/MM/YYYY')) AS TARIKH_MESYUARAT,  MAX(INITCAP(A.TAJUK)) AS TAJUK, MAX (INITCAP(C.NAMA_PEGAWAI)) AS NAMA_PEGAWAI"
					+ " FROM TBLPHPMESYUARAT A, TBLPERMOHONAN B, TBLPHPKEHADIRANMESY C"
					+ " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "
					+ " AND A.ID_MESYUARAT = C.ID_MESYUARAT(+)"
					+ " AND C.FLAG_PENGERUSI = 'Y'"
					+ " AND A.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();

				h.put("tarikhMesyuarat",
						rs.getString("TARIKH_MESYUARAT") == null ? " x" : rs
								.getString("TARIKH_MESYUARAT"));
				h.put("tajuk",
						rs.getString("TAJUK") == null ? "x" : rs
								.getString("TAJUK"));
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? "x"
						: rs.getString("NAMA_PEGAWAI"));
				beanMesyuarat.addElement(h);
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
//	public Vector getAlamat(String idPejabat, String idFail , String idJenisPejabat) throws Exception {
//		Db db = null;
//		Vector v = null;
//		String sql = "";
//		try {
//			beanAlamat = new Vector<>();
//			db = new Db();
//			Statement stmt = db.getStatement();
//			sql = "SELECT PEJ.NAMA_PEJABAT, HKMILIK.ID_NEGERI, PEJ.ALAMAT1, PEJ.ALAMAT2, PEJ.ALAMAT3, PEJ.POSKOD, NEGERI.NAMA_NEGERI, DAERAH.NAMA_DAERAH  " 
//					  + "FROM TBLRUJPEJABAT PEJ, TBLPHPHAKMILIK HKMILIK, TBLPHPHAKMILIKPERMOHONAN HKMPER, TBLPERMOHONAN MOHON, TBLPFDFAIL FAIL, " 
//					  + "TBLRUJJENISPEJABAT JENISPEJABAT, TBLRUJNEGERI NEGERI, TBLRUJDAERAH DAERAH "
//					  + "WHERE PEJ.ID_NEGERI = HKMILIK.ID_NEGERI " 
//					  + "AND HKMILIK.ID_HAKMILIKPERMOHONAN  = HKMPER.ID_HAKMILIKPERMOHONAN " 
//					  + "AND HKMPER.ID_PERMOHONAN = MOHON.ID_PERMOHONAN " 
//					  + "AND MOHON.ID_FAIL = FAIL.ID_FAIL "
//					  + "AND PEJ.ID_JENISPEJABAT = JENISPEJABAT.ID_JENISPEJABAT " 
//					  + "AND NEGERI.ID_NEGERI = PEJ.ID_NEGERI " 
//					  + "AND DAERAH.ID_DAERAH = PEJ.ID_DAERAH "
//					  + "AND PEJ.ID_PEJABAT = " + idPejabat  
//					  + " AND PEJ.ID_JENISPEJABAT = " + idJenisPejabat + " AND FAIL.ID_FAIL = " + idFail; 
//						ResultSet rs = stmt.executeQuery(sql);
//
//						v = new Vector();
//						Hashtable h;
//						while (rs.next()) {
//							h = new Hashtable();
//							h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? "":rs.getString("NAMA_PEJABAT"));
//							h.put("alamat1", rs.getString("ALAMAT1") == null ? "":rs.getString("ALAMAT1"));
//							h.put("alamat2", rs.getString("ALAMAT2") == null ? "":rs.getString("ALAMAT2"));
//							h.put("alamat3", rs.getString("ALAMAT3") == null ? "":rs.getString("ALAMAT3"));
//							h.put("poskod", rs.getString("POSKOD") == null ? "":rs.getString("POSKOD"));
//							h.put("namaDaerah", rs.getString("NAMA_DAERAH") == null ? "":rs.getString("NAMA_DAERAH"));
//							h.put("namaNegeri", rs.getString("NAMA_NEGERI") == null ? "":rs.getString("NAMA_NEGERI"));
//							beanAlamat.addElement(h);
//						}
//		} catch (Exception re) {
//	log.error("Error: ", re);
	//throw re;
	//}
 //finally {
//			if (db != null)
//				db.close();
//		}
//		
//		return beanAlamat;
//	}
	public Vector getBeanMaklumatPegawai() {
		return beanMaklumatPegawai;
	}

	public void setBeanMaklumatPegawai(Vector beanMaklumatPegawai) {
		this.beanMaklumatPegawai = beanMaklumatPegawai;
	}

	public Vector getBeanDuitRM() {
		return beanDuitRM;
	}

	public Vector getBeanMaklumatPejabat() {
		return beanMaklumatPejabat;
	}

	public void setBeanMaklumatPejabat(Vector beanMaklumatPejabat) {
		this.beanMaklumatPejabat = beanMaklumatPejabat;
	}
	public Vector getBeanMaklumatMesyuarat() {
		return beanMaklumatMesyuarat;
	}
	public void setBeanMaklumatMesyuarat(Vector beanMaklumatMesyuarat) {
		this.beanMaklumatMesyuarat = beanMaklumatMesyuarat;
	}
	public Vector getBeanMesyuarat() {
		return beanMesyuarat;
	}
	public void setBeanMesyuarat(Vector beanMesyuarat) {
		this.beanMesyuarat = beanMesyuarat;
	}	
}
