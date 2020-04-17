/**
 * 
 */
package ekptg.model.php2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;

import lebah.db.Db;

/**
 * 
 * 
 */
public class FrmREVPopupCetakLaporanData {
	
	public Hashtable getMaklumatPegawai(String idPegawai) {
		Db db = null;
		String sql = "";
		Hashtable pegawai = null;
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.USER_NAME AS NAMA_PEGAWAI, C.KETERANGAN AS JAWATAN, B.NO_TEL_PEJABAT, "
					+ " B.NO_FAKS, B.EMEL"
					+ " FROM USERS A, USERS_INTERNAL B, TBLRUJJAWATAN C"
					+ " WHERE A.USER_ID = B.USER_ID"
					+ " AND B.ID_JAWATAN = C.ID_JAWATAN(+)"
					+ " AND A.USER_ID = '" + idPegawai + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				pegawai = new Hashtable();
				pegawai.put("nama", rs.getString("NAMA_PEGAWAI") == null ? "" : rs.getString("NAMA_PEGAWAI").toUpperCase());
				pegawai.put("jawatan", rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN").toUpperCase());
				pegawai.put("noTel", rs.getString("NO_TEL_PEJABAT") == null ? "" : rs.getString("NO_TEL_PEJABAT").toUpperCase());
				pegawai.put("noFaks", rs.getString("NO_FAKS") == null ? "" : rs.getString("NO_FAKS").toUpperCase());
				pegawai.put("emel", rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return pegawai;
	}
	
	public Hashtable getAlamatKJP(String idHasil) {
		Db db = null;
		String sql = "";
		Hashtable alamatKJP = null;
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLRUJKEMENTERIAN.JAWATAN, TBLRUJKEMENTERIAN.NAMA_KEMENTERIAN, TBLRUJAGENSI.NAMA_AGENSI,"
					+ " TBLRUJKEMENTERIAN.ALAMAT1, TBLRUJKEMENTERIAN.ALAMAT2, TBLRUJKEMENTERIAN.ALAMAT3, TBLRUJKEMENTERIAN.POSKOD,"
					+ " TBLRUJNEGERI.NAMA_NEGERI"
					+ " FROM TBLPHPHASIL, TBLRUJAGENSI, TBLRUJKEMENTERIAN, TBLRUJNEGERI"
					+ " WHERE TBLPHPHASIL.ID_AGENSI(+) = TBLRUJAGENSI.ID_AGENSI"
					+ " AND TBLRUJAGENSI.ID_KEMENTERIAN = TBLRUJKEMENTERIAN.ID_KEMENTERIAN"
					+ " AND TBLRUJKEMENTERIAN.ID_NEGERI(+) = TBLRUJNEGERI.ID_NEGERI"
					+ " AND TBLPHPHASIL.ID_HASIL = '" + idHasil + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				alamatKJP = new Hashtable();
				alamatKJP.put("jawatan", rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN").toUpperCase());
				alamatKJP.put("namaKementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				alamatKJP.put("namaAgensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());				
				alamatKJP.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1").toUpperCase());
				alamatKJP.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2").toUpperCase());
				alamatKJP.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3").toUpperCase());
				alamatKJP.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD").toUpperCase());
				alamatKJP.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return alamatKJP;
	}
	
	public Hashtable getAlamatJKPTG(String idHasil) {
		Db db = null;
		String sql = "";
		Hashtable alamatJKPTG = null;
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLRUJJAWATAN.KETERANGAN AS JAWATAN, TBLRUJPEJABATJKPTG.NAMA_PEJABAT,"
					+ " TBLRUJPEJABATJKPTG.ALAMAT1, TBLRUJPEJABATJKPTG.ALAMAT2, TBLRUJPEJABATJKPTG.ALAMAT3, TBLRUJPEJABATJKPTG.POSKOD, TBLRUJNEGERI.NAMA_NEGERI"
					+ " FROM TBLPHPHASIL, TBLPFDFAIL, TBLRUJPEJABATJKPTG, TBLRUJNEGERI, TBLRUJJAWATAN"
					+ " WHERE TBLPHPHASIL.ID_FAIL = TBLPFDFAIL.ID_FAIL"
					+ " AND TBLPFDFAIL.ID_NEGERI(+) = TBLRUJPEJABATJKPTG.ID_NEGERI"
					+ " AND TBLRUJPEJABATJKPTG.ID_NEGERI = TBLRUJNEGERI.ID_NEGERI"
					+ " AND TBLRUJPEJABATJKPTG.ID_JAWATAN(+) = TBLRUJJAWATAN.ID_JAWATAN"
					+ " AND TBLRUJPEJABATJKPTG.ID_JENISPEJABAT = 24"
					+ " AND TBLRUJPEJABATJKPTG.ID_SEKSYEN = 2"
					+ " AND TBLPHPHASIL.ID_HASIL = '" + idHasil + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				alamatJKPTG = new Hashtable();
				alamatJKPTG.put("jawatan", rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN").toUpperCase());
				alamatJKPTG.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());			
				alamatJKPTG.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1").toUpperCase());
				alamatJKPTG.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2").toUpperCase());
				alamatJKPTG.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3").toUpperCase());
				alamatJKPTG.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD").toUpperCase());
				alamatJKPTG.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return alamatJKPTG;
	}

	public int getKategoriPemohon(String idHasil) throws Exception {

		Db db = null;
		int idKategori = 0;

		String sql = "SELECT ID_KATEGORIPEMOHON FROM TBLPHPPEMOHON A, TBLPHPHASIL B "
				+ "WHERE A.ID_PEMOHON = B.ID_PEMOHON AND B.ID_HASIL = "
				+ idHasil;

		System.out.println("cek sql " + sql);
		try {

			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				idKategori = (rs.getInt("ID_KATEGORIPEMOHON"));
				System.out.println("nama = " + idKategori);
				return idKategori;
			}

			return idKategori;

		} finally {
			if (db != null)
				db.close();
		}
	}

	public boolean checkJenisSuratTunggakan(String idHasil) throws Exception {

		Db db = null;
		boolean flag_surat = false;

		String sql = " SELECT G.BAYARAN AS KADAR_SEWA, (((CASE WHEN SUM(F.DEBIT) IS NULL THEN 0 ELSE SUM(DEBIT) END)  - "
				+ " (CASE WHEN SUM(F.KREDIT) IS NULL THEN 0 ELSE SUM(KREDIT) END)) / G.BAYARAN) AS BULAN"
				+ " FROM TBLPHPAKAUN F, TBLPHPBAYARANPERLUDIBAYAR G, TBLPHPHASIL B"
				+ " WHERE B.ID_HASIL = F.ID_HASIL AND B.ID_HASIL = G.ID_HASIL"
				+ " AND B.ID_HASIL = "
				+ idHasil
				+ " AND F.ID_JENISBAYARAN = 10" + " GROUP BY  G.BAYARAN";

		System.out.println("cek sql " + sql);
		try {

			// beanMaklumatPegawai = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				// h = new Hashtable();

				// h.put("USER_NAME", rs.getString("NAME") == null ? "" :
				// rs.getString("NAME").toUpperCase());
				// beanMaklumatPegawai.addElement(h);
				// System.out.println("nama = "+rs.getString("NAME"));
				System.out.println("cek bulan = " + rs.getDouble("BULAN"));
				if (rs.getDouble("BULAN") % 1 != 0) {
					flag_surat = true; // ada baki
				} else {
					flag_surat = false; // takde baki
				}

			}

		} finally {
			if (db != null)
				db.close();
		}
		return flag_surat;
	}

	public double getKadarSewaSebulan(String idAkaun) {
		double kadarSewaSebulan = 0D;
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT TBLPHPBAYARANPERLUDIBAYAR.BAYARAN"

					+ " FROM TBLPHPAKAUN, TBLPHPHASIL, TBLPHPBAYARANPERLUDIBAYAR"

					+ " WHERE TBLPHPAKAUN.ID_HASIL = TBLPHPHASIL.ID_HASIL AND TBLPHPHASIL.ID_HASIL = TBLPHPBAYARANPERLUDIBAYAR.ID_HASIL(+)"
					+ " AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_PERJANJIAN = 'U'"
					+ " AND TBLPHPAKAUN.TARIKH BETWEEN TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA AND TBLPHPBAYARANPERLUDIBAYAR.TARIKH_TAMAT"
					+ " AND TBLPHPAKAUN.ID_AKAUN = '" + idAkaun + "'"
					+ " ORDER BY TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA DESC" ;
			
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				if (rs.getString("BAYARAN") != null) {
					if (rs.getDouble("BAYARAN") >= 0D) {
						kadarSewaSebulan = rs.getDouble("BAYARAN");
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return kadarSewaSebulan;
	}
	
	public double getTunggakanSewa(String idAkaun) {
		double tunggakanSewa = 0D;
		Db db = null;
		Db db1 = null;
		String sql = "";
		String temp = "";
		try {
			db = new Db();
			db1 = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_AKAUN, DEBIT, KREDIT"					
					+ " FROM TBLPHPAKAUN"					
					+ " WHERE ID_JENISBAYARAN = 10 AND FLAG_AKTIF = 'Y'"
					+ " AND TARIKH < (SELECT A.TARIKH FROM TBLPHPAKAUN A WHERE A.ID_AKAUN = '" + idAkaun + "')"
					+ " AND TBLPHPAKAUN.ID_HASIL = (SELECT B.ID_HASIL FROM TBLPHPAKAUN B WHERE B.ID_AKAUN = '" + idAkaun + "')"
					+ " ORDER BY TARIKH, ID_AKAUN ASC";
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (!"".equals(rs.getString("ID_AKAUN"))) {
					idAkaun = rs.getString("ID_AKAUN");

					if ("".equals(temp)) {
						temp = idAkaun;
					} else {
						temp = temp + "," + idAkaun;
					}
				}
			}
			
			Double baki = getTotalBaki(temp, db1);
			tunggakanSewa =  baki;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			if (db != null) db.close();
			if (db1 != null) db1.close();
		}
		return tunggakanSewa;
	}
	
	private Double getTotalBaki(String temp, Db db) throws Exception {
		String sql = "";
		Double baki = 0D;
		Double totalDebit = 0D;
		Double totalKredit = 0D;

		try {
			Statement stmt = db.getStatement();

			// DEBIT
			sql = "SELECT DEBIT FROM TBLPHPAKAUN WHERE ID_AKAUN IN (" + temp + ")";
			ResultSet rsDebit = stmt.executeQuery(sql);

			while (rsDebit.next()) {
				totalDebit = totalDebit
						+ Double.valueOf(rsDebit.getDouble("DEBIT"));
			}

			// KREDIT
			sql = "SELECT KREDIT FROM TBLPHPAKAUN WHERE ID_AKAUN IN (" + temp + ")";
			ResultSet rsKredit = stmt.executeQuery(sql);

			while (rsKredit.next()) {
				totalKredit = totalKredit
						+ Double.valueOf(rsKredit.getDouble("KREDIT"));
			}

			baki = totalDebit - totalKredit;

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return baki;
	}
	
	public double getKadarSewaSemasa(String idAkaun) {
		double kadarSewaSemasa = 0D;
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT TBLPHPBAYARANPERLUDIBAYAR.BAYARAN"

					+ " FROM TBLPHPAKAUN, TBLPHPHASIL, TBLPHPBAYARANPERLUDIBAYAR"

					+ " WHERE TBLPHPAKAUN.ID_HASIL = TBLPHPHASIL.ID_HASIL AND TBLPHPHASIL.ID_HASIL = TBLPHPBAYARANPERLUDIBAYAR.ID_HASIL(+)"
					+ " AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_PERJANJIAN != 'U'"
					+ " AND TBLPHPAKAUN.TARIKH BETWEEN TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA AND TBLPHPBAYARANPERLUDIBAYAR.TARIKH_TAMAT"
					+ " AND TBLPHPAKAUN.ID_AKAUN = '" + idAkaun + "'"
					+ " ORDER BY TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA DESC" ;
			ResultSet rsPerjanjianTambahan = stmt.executeQuery(sql);
			if (rsPerjanjianTambahan.next()) {
				if (rsPerjanjianTambahan.getString("BAYARAN") != null) {
					if (rsPerjanjianTambahan.getDouble("BAYARAN") >= 0D) {
						kadarSewaSemasa = rsPerjanjianTambahan.getDouble("BAYARAN");
					}
				}
			} else {
				sql = "SELECT TBLPHPBAYARANPERLUDIBAYAR.BAYARAN"

					+ " FROM TBLPHPAKAUN, TBLPHPHASIL, TBLPHPBAYARANPERLUDIBAYAR"

					+ " WHERE TBLPHPAKAUN.ID_HASIL = TBLPHPHASIL.ID_HASIL AND TBLPHPHASIL.ID_HASIL = TBLPHPBAYARANPERLUDIBAYAR.ID_HASIL(+)"
					+ " AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_PERJANJIAN = 'U'"
					+ " AND TBLPHPAKAUN.TARIKH BETWEEN TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA AND TBLPHPBAYARANPERLUDIBAYAR.TARIKH_TAMAT"
					+ " AND TBLPHPAKAUN.ID_AKAUN = '" + idAkaun + "'"
					+ " ORDER BY TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA DESC" ;
				ResultSet rsPerjanjianUtama = stmt.executeQuery(sql);
				if (rsPerjanjianUtama.next()) {
					if (rsPerjanjianUtama.getString("BAYARAN") != null) {
						if (rsPerjanjianUtama.getDouble("BAYARAN") >= 0D) {
							kadarSewaSemasa = rsPerjanjianUtama.getDouble("BAYARAN");
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		return kadarSewaSemasa;
	}

	public boolean getJenisSuratTuntutan(String idNotis) {
		boolean bool = false;		
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPNOTISHASIL WHERE ID_NOTIS = '" + idNotis + "'";			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				//1 = CUKUP BULAN; 2 = TAK CUKUP BULAN
				if (rs.getString("JUMLAH_TUNGGAKAN") != null && rs.getString("KADAR_SEWA") != null) {
					Double kadarSewa = rs.getDouble("KADAR_SEWA");
					Double jumlahTunggakan = rs.getDouble("JUMLAH_TUNGGAKAN");
					double remainder = jumlahTunggakan%kadarSewa;
					if (remainder == 0D) {
						bool = true;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return bool;
	}

	public Double getJumlahTunggakan(String idNotis) {
		Double jumlahTunggakan = 0D;
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPNOTISHASIL WHERE ID_NOTIS = '" + idNotis + "'";			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				if (rs.getString("JUMLAH_TUNGGAKAN") != null) {
					jumlahTunggakan = rs.getDouble("JUMLAH_TUNGGAKAN");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		return jumlahTunggakan;
	}
	
	public Double getJumlahDeposit(String idNotis) {
		Double jumlahDeposit = 0D;
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NVL(SUM(TBLPHPAKAUN.KREDIT),0) AS JUMLAH_DEPOSIT"
					+ " FROM TBLPHPNOTISHASIL, TBLPHPHASIL, TBLPHPAKAUN"
					+ " WHERE TBLPHPNOTISHASIL.ID_HASIL = TBLPHPHASIL.ID_HASIL AND TBLPHPHASIL.ID_HASIL = TBLPHPAKAUN.ID_HASIL"
					+ " AND TBLPHPAKAUN.ID_JENISBAYARAN = 9 AND TBLPHPAKAUN.FLAG_AKTIF = 'Y' AND TBLPHPAKAUN.FLAG_DEPOSIT = 'Y'"
					+ " AND TBLPHPNOTISHASIL.ID_NOTIS = '" + idNotis + "'";			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				if (rs.getString("JUMLAH_DEPOSIT") != null) {
					jumlahDeposit = rs.getDouble("JUMLAH_DEPOSIT");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		return jumlahDeposit;
	}
}
