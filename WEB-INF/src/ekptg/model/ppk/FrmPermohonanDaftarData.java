/**
 *
 */
package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

public class FrmPermohonanDaftarData extends FrmPrmhnnSek8DaftarSek8InternalData{
	static Logger myLogger = Logger.getLogger(FrmPermohonanDaftarData.class);
	
	private Vector<Hashtable<String,String>> listHTAXbyIdHtaam = new Vector<Hashtable<String,String>>();
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	public Vector<Hashtable<String,String>> getDataHTAXbyIdHtaam(String idhtaam, String id_permohonansimati)
		throws Exception {
		Db db = null;
		listHTAXbyIdHtaam.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT H.FLAG_DAFTAR,H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT" +
					", TRIM(TO_CHAR(NVL(H.NILAI_HTA_TARIKHMOHON,0),'999,999,999.99')) NILAI_HTA_TARIKHMOHON" +
					", TRIM(TO_CHAR(NVL(H.NILAI_HTA_TARIKHMATI,0),'999,999,999.99')) NILAI_HTA_TARIKHMATI" +
					" ,DOKUMEN.ID_DOKUMEN , DOKUMEN.NAMA_DOKUMEN ,PELAN.ID_PELAN " +
					", H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, "
					+ " H.ID_NEGERI, H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, H.LUAS, "
					+ " H.NO_CAGARAN, H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, "
					+ " H.TANGGUNGAN, H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN, H.NAMA_PEMAJU, "
					+ " H.ALAMAT_PEMAJU1, H.ALAMAT_PEMAJU2, H.ALAMAT_PEMAJU3, H.POSKOD_PEMAJU, "
					+ " H.BANDAR_PEMAJU, H.ID_BANDARPEMAJU, H.ID_NEGERIPEMAJU, H.ALAMAT_HTA1, "
					+ " H.ALAMAT_HTA2, H.ALAMAT_HTA3, H.POSKOD_HTA, H.BANDAR_HTA, H.ID_BANDARHTA, "
					+ " H.NO_PERJANJIAN, H.TARIKH_PERJANJIAN, H.NAMA_RANCANGAN, H.NO_ROH, H.NO_LOT_ID, "
					+ " H.FLAG_KATEGORI_HTA, H.JENIS_KEPENTINGAN  "
					+ " FROM TBLPPKHTA HP, TBLPPKHTAPERMOHONAN H,TBLPPKSIMATI S , TBLPPKDOKUMEN DOKUMEN,  TBLPPKUPLOADPELAN PELAN "
					+ " WHERE H.ID_SIMATI = S.ID_SIMATI  "
					+ " AND PELAN.ID_HTA (+) = H.ID_HTA "
					+ " AND DOKUMEN.ID_DOKUMEN (+)= PELAN.ID_DOKUMEN "
					+ " AND HP.ID_HTA = H.ID_HTA "
					+
					// " AND H.ID_PERMOHONANSIMATI = HP.ID_PERMOHONANSIMATI " +
					" AND H.ID_PERMOHONANSIMATI = '"+ id_permohonansimati+ "'"
					+ " AND H.JENIS_HTA = 'T'  AND H.ID_HTA = '"+ idhtaam + "' ";

			myLogger.info("***GET BY ID HTAAMX :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable<String,String> h;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("FLAG_DAFTAR", rs.getString("FLAG_DAFTAR") == null ? "" : rs.getString("FLAG_DAFTAR"));
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs.getString("id_Hta"));
				h.put("idDokumen", rs.getString("id_dokumen") == null ? "" : rs.getString("id_dokumen"));
				h.put("idPelan", rs.getString("id_pelan") == null ? "" : rs.getString("id_pelan"));
				h.put("namaPelan", rs.getString("nama_dokumen") == null ? "" : rs.getString("nama_dokumen"));
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? "" : rs.getString("no_Hakmilik"));

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));

				/*h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon") == null 
						? "" : String.valueOf(rs.getDouble("nilai_Hta_Tarikhmohon")));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null 
						? "" : String.valueOf(rs.getDouble("nilai_Hta_Tarikhmati")));*/
				h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon"));
			
				h.put("nilai_Hta_mati",rs.getString("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan",
						rs.getString("status_Pemilikan") == null ? "" : rs
								.getString("status_Pemilikan"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs
						.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs
						.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs
						.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs
						.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs
						.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs
						.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? ""
						: rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs
						.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("noperserahan",
						rs.getString("no_perserahan") == null ? "" : rs
								.getString("no_perserahan"));

				h.put("flag", rs.getString("flag_Kategori_Hta") == null ? ""
						: rs.getString("flag_Kategori_Hta"));
				h.put("namapemaju", rs.getString("nama_Pemaju") == null ? ""
						: rs.getString("nama_Pemaju"));
				h.put("alamatpemaju1",
						rs.getString("alamat_Pemaju1") == null ? "" : rs
								.getString("alamat_Pemaju1"));
				h.put("alamatpemaju2",
						rs.getString("alamat_Pemaju2") == null ? "" : rs
								.getString("alamat_Pemaju2"));
				h.put("alamatpemaju3",
						rs.getString("alamat_Pemaju3") == null ? "" : rs
								.getString("alamat_Pemaju3"));
				h.put("poskodpemaju",
						rs.getString("poskod_Pemaju") == null ? "" : rs
								.getString("poskod_Pemaju"));
				h.put("bandarpemaju",
						rs.getString("id_bandarpemaju") == null ? "" : rs
								.getString("id_bandarpemaju"));
				h.put("negeripemaju",
						rs.getString("id_Negeripemaju") == null ? "" : rs
								.getString("id_Negeripemaju"));
				h.put("alamathta1", rs.getString("alamat_Hta1") == null ? ""
						: rs.getString("alamat_Hta1"));
				h.put("alamathta2", rs.getString("alamat_Hta2") == null ? ""
						: rs.getString("alamat_Hta2"));
				h.put("alamathta3", rs.getString("alamat_Hta3") == null ? ""
						: rs.getString("alamat_Hta3"));
				h.put("poskodhta", rs.getString("poskod_Hta") == null ? "" : rs
						.getString("poskod_Hta"));
				h.put("bandarhta", rs.getString("id_bandarhta") == null ? ""
						: rs.getString("id_bandarhta"));
				h.put("noperjanjian",
						rs.getString("no_Perjanjian") == null ? "" : rs
								.getString("no_Perjanjian"));
				h.put("tarikhperjanjian",
						rs.getString("tarikh_Perjanjian") == null ? ""
								: formatter.format(rs
										.getDate("tarikh_Perjanjian")));
				h.put("namarancangan",
						rs.getString("nama_Rancangan") == null ? "" : rs
								.getString("nama_Rancangan"));
				h.put("noroh", rs.getString("no_Roh") == null ? "" : rs
						.getString("no_Roh"));
				h.put("nolot", rs.getString("no_Lot_Id") == null ? "" : rs
						.getString("no_Lot_Id"));
				// h.put("flag",
				// rs.getString("flag_Kategori_Hta")==null?"":rs.getString("flag_Kategori_Hta"));
				h.put("jeniskepentingan",
						rs.getString("jenis_Kepentingan") == null ? "" : rs
								.getString("jenis_Kepentingan"));

				// System.out.println(h);
				listHTAXbyIdHtaam.addElement(h);
				
			}
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
		return listHTAXbyIdHtaam;
		
	}

//IL end
}
