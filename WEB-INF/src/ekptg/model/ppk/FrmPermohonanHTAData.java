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

public class FrmPermohonanHTAData extends FrmPrmhnnSek8InternalData{
	static Logger myLogger = Logger.getLogger(FrmPermohonanHTAData.class);
	
	private Vector<Hashtable<String,String>> listHTAX = new Vector<Hashtable<String,String>>();
	private Vector<Hashtable<String,String>> listHTA = new Vector<Hashtable<String,String>>();
	private Vector<Hashtable<String,String>> listHarta = new Vector<Hashtable<String,String>>();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	//setDataHTAbyIdHtaam
	public Hashtable<String,String> getDataHTAbyIdHtaam(String idhtaam, String id_permohonansimati) throws Exception {
		Db db = null;
		//listHarta.clear();
		Hashtable<String,String> h = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT H.FLAG_DAFTAR,H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, "
					+ "H.NILAI_HTA_TARIKHMATI, H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, "
					+ "H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, H.LUAS, H.NO_CAGARAN, "
					+ "H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN, H.ALAMAT_HTA1, H.ALAMAT_HTA2, H.ALAMAT_HTA3, H.BANDAR_HTA, H.POSKOD_HTA, H.ID_BANDARHTA, "
					+ "H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN,DOKUMEN.ID_DOKUMEN , DOKUMEN.NAMA_DOKUMEN ,  "
					+
					// ADD BY PEJE - ADD FIELD SEKATAN & SYARAT NYATA
					"H.SEKATAN, H.SYARAT_NYATA  "
					+

					"FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S , TBLPPKUPLOADPELAN PELAN , TBLPPKDOKUMEN DOKUMEN "
					+ " WHERE H.ID_SIMATI = S.ID_SIMATI"
					+ "  AND HP.ID_HTA = H.ID_HTA " + " AND PELAN.ID_HTA (+) = H.ID_HTA  "
					
					+ " AND DOKUMEN.ID_DOKUMEN (+)= PELAN.ID_DOKUMEN  "
					// " AND H.ID_PERMOHONANSIMATI = HP.ID_PERMOHONANSIMATI " +
					+ " AND H.ID_PERMOHONANSIMATI = '"
					+ id_permohonansimati
					+ "'"
					+ " AND H.JENIS_HTA = 'Y'  AND H.ID_HTA = '"
					+ idhtaam
					+ "'";
			
			System.out.println("HTAAM :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			//Hashtable<String,String> h;

			while (rs.next()) {
				System.out.println("Baca sini4");
				h = new Hashtable<String,String>();
				h.put("FLAG_DAFTAR", rs.getString("FLAG_DAFTAR") == null ? ""
						: rs.getString("FLAG_DAFTAR"));
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("idDokumen", rs.getString("id_dokumen") == null ? "" : rs.getString("id_dokumen"));//IL
				h.put("namaPelanUp", rs.getString("nama_dokumen") == null ? "" : rs.getString("nama_dokumen"));//IL
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("nilai_Hta_memohon",rs.getString("nilai_Hta_Tarikhmohon") == null 
						? "" : String.valueOf(rs.getDouble("nilai_Hta_Tarikhmohon")));
				h.put("nilai_Hta_mati",rs.getString("nilai_Hta_Tarikhmati") == null 
						? "" : String.valueOf(rs.getDouble("nilai_Hta_Tarikhmati")));
				
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
				h.put("noperserahan", rs.getString("NO_PERSERAHAN") == null ? "" : rs
								.getString("NO_PERSERAHAN"));
				
				// ADD BY SALNIZAM - ADD FIELD ALAMAT
				h.put("alamat1", rs.getString("alamat_Hta1") == null ? "" : rs
						.getString("alamat_Hta1"));
				h.put("alamat2", rs.getString("alamat_Hta2") == null ? "" : rs
						.getString("alamat_Hta2"));
				h.put("alamat2", rs.getString("alamat_Hta2") == null ? "" : rs
						.getString("alamat_Hta2"));
				h.put("alamat3", rs.getString("alamat_Hta3") == null ? "" : rs
						.getString("alamat_Hta3"));
				h.put("bandar", rs.getString("id_bandarhta") == null ? "" : rs
						.getString("id_bandarhta"));
				System.out.println("Baca sini4##### " + rs.getString("id_bandarhta"));
				h.put("poskod", rs.getString("poskod_hta") == null ? "" : rs
						.getString("poskod_hta"));		
			//	h.put("id_bandarhta", rs.getString("id_bandarhta") == null ? "" : rs
				//		.getString("id_bandarhta"));	

				

				// ADD BY PEJE - ADD FIELD SEKATAN & SYARAT NYATA
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs
						.getString("SEKATAN").toUpperCase());
				h.put("syaratNyata", rs.getString("SYARAT_NYATA") == null ? ""
						: rs.getString("SYARAT_NYATA").toUpperCase());

				//listHarta.addElement(h);
			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
		return h;

	}

	public Vector getDataHTAX(String idsimati) throws Exception {
		Db db = null;
		listHTAX.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT" +
					", TRIM(TO_CHAR(NVL(H.NILAI_HTA_TARIKHMOHON,0),'999,999,999.99')) NILAI_HTA_TARIKHMOHON" +
					", TRIM(TO_CHAR(NVL(H.NILAI_HTA_TARIKHMATI,0),'999,999,999.99')) NILAI_HTA_TARIKHMATI" +
					",D.ID_DOKUMEN,P.ID_PELAN "
					+ " ,H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, "
					+ " H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN, "
					+ " H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN, H.NO_PERJANJIAN, H.NO_ROH,"
					+ " H.ALAMAT_HTA1,H.ALAMAT_HTA2,H.ALAMAT_HTA3, H.POSKOD_HTA ,H.JENIS_KEPENTINGAN,H.FLAG_KATEGORI_HTA  "
					+ " FROM " +
					"TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI MS,TBLPPKDOKUMEN D,TBLPPKUPLOADPELAN P "
					+ " WHERE H.ID_SIMATI = S.ID_SIMATI "
					+ " AND H.ID_HTA = HP.ID_HTA AND HP.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI  "
					+ " AND H.ID_SIMATI = MS.ID_SIMATI  "
					+ " AND P.ID_HTA (+) = H.ID_HTA " //IL
					+ " AND D.ID_DOKUMEN (+)= P.ID_DOKUMEN " //IL
					+ " AND MS.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI  "
					+ " AND MS.ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "'  "
					+ " AND H.JENIS_HTA = 'T'  " + " ORDER BY H.ID_HTA DESC ";
			myLogger.info("HTAAMX STRUCTURE BARU :xxxxxxxxxxxxxxxxxxxx" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("idDokumen", rs.getString("id_dokumen") == null ? "" : rs.getString("id_dokumen"));//IL
				h.put("idPelan", rs.getString("id_pelan") == null ? "" : rs.getString("id_pelan"));//IL
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon"));
			
				h.put("nilai_Hta_mati",rs.getString("nilai_Hta_Tarikhmati"));
				/*h.put("nilai_Hta_memohon", rs
						.getString("nilai_Hta_Tarikhmohon") == null 
						? "" : String.valueOf(rs.getDouble("nilai_Hta_Tarikhmohon")));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null 
						? "" : String.valueOf(rs.getDouble("nilai_Hta_Tarikhmati")));*/
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb") == null ? "" : rs
						.getString("id_Jenispb"));
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
				h.put("noperjanjian",
						rs.getString("no_Perjanjian") == null ? "" : rs
								.getString("no_Perjanjian"));

				h.put("noroh", rs.getString("no_Roh") == null ? "" : rs
						.getString("no_Roh"));
				h.put("alamat1", rs.getString("ALAMAT_HTA1") == null ? "" : rs
						.getString("ALAMAT_HTA1"));
				h.put("alamat2", rs.getString("ALAMAT_HTA2") == null ? "" : rs
						.getString("ALAMAT_HTA2"));
				h.put("alamat3", rs.getString("ALAMAT_HTA3") == null ? "" : rs
						.getString("ALAMAT_HTA3"));
				h.put("poskod", rs.getString("POSKOD_HTA") == null ? "" : rs
						.getString("POSKOD_HTA"));
				h.put("jenis_penting",
						rs.getString("JENIS_KEPENTINGAN") == null ? "" : rs
								.getString("JENIS_KEPENTINGAN"));
				h.put("ketegori_hta",rs.getString("FLAG_KATEGORI_HTA") == null ? "" : rs.getString("FLAG_KATEGORI_HTA"));
				listHTAX.addElement(h);
				
			}
			
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
		return listHTAX;
		
	} 
	public Vector<Hashtable<String,String>> getDataHTA(String idsimati) throws Exception {
		Db db = null;
		listHTA.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT H.ID_HTA, H.ALAMAT_HTA1, H.ALAMAT_HTA2, H.ALAMAT_HTA3, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, H.NILAI_HTA_TARIKHMATI, "
					+ " H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, H.ID_DAERAH, H.ID_LUAS, H.ID_BANDARHTA, H.ID_MUKIM, H.LUAS_HMP,"
					+ " H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN, "
					+ " H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN,RUJ.KOD_JENIS_HAKMILIK,RUJ.KETERANGAN,  "

					// ADD BY PEJE - ADD FIELD SEKATAN & SYARAT NYATA
					+ " H.SEKATAN, H.SYARAT_NYATA  , MS.ID_PERMOHONAN  " +
					",DOKUMEN.ID_DOKUMEN , DOKUMEN.NAMA_DOKUMEN ,PELAN.ID_PELAN"
					+ " FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI MS, TBLRUJJENISHAKMILIK RUJ "
					+ ", TBLPPKUPLOADPELAN PELAN , TBLPPKDOKUMEN DOKUMEN  " //IL
					+ " WHERE H.ID_SIMATI = S.ID_SIMATI "
					+ " AND HP.ID_HTA = H.ID_HTA AND HP.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI  "
					+ " AND H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) "
					+ " AND H.ID_SIMATI = MS.ID_SIMATI "
					+ " AND MS.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI "
					+ " AND PELAN.ID_HTA (+) = H.ID_HTA " //IL
					+ " AND DOKUMEN.ID_DOKUMEN (+)= PELAN.ID_DOKUMEN " //IL
					+ " AND MS.ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "'  "
					+ " AND H.JENIS_HTA = 'Y'  ORDER BY H.ID_HTA DESC";

			// System.out.println("HTAAM :"+sql.toUpperCase());
			//System.out.println("******SQL :"+sql);
			myLogger.info("HTAAM STRUCTURE BARU" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			//
			Hashtable<String,String> h;
			int i = 0;
			while (rs.next()) {
			    
				h = new Hashtable<String,String>();
				//System.out.println("Try kat sini " + i);
				h.put("alamathta1", rs.getString("alamat_hta1") == null ? "" : rs
						//.getString("alamat_Hta1"));
						.getString("alamat_hta1"));
				h.put("alamathta2", rs.getString("alamat_hta2") == null ? "" : rs
						//.getString("alamat_Hta1"));
						.getString("alamat_hta2"));
				h.put("alamathta3", rs.getString("alamat_hta3") == null ? "" : rs
						//.getString("alamat_Hta1"));
						.getString("alamat_hta3"));
				//System.out.println("Alamat1 " + rs.getString("alamat_hta1"));
				//System.out.println("Alamat2 " + rs.getString("alamat_hta2"));
				//System.out.println("Try kat sini");
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs.getString("id_Hta"));		
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idDokumen", rs.getString("id_dokumen") == null ? "" : rs.getString("id_dokumen"));//IL
				h.put("idPelan", rs.getString("id_pelan") == null ? "" : rs.getString("id_pelan"));//IL		
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("nilai_Hta_memohon", String.valueOf(rs.getString("nilai_Hta_Tarikhmohon") == null 
					? "" : rs.getDouble("nilai_Hta_Tarikhmohon")));
				h.put("nilai_Hta_mati",String.valueOf(rs.getString("nilai_Hta_Tarikhmati") == null 
					? "" : rs.getDouble("nilai_Hta_Tarikhmati")));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb") == null ? "" : rs
						.getString("id_Jenispb"));
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
				
				h.put("noperserahan", rs.getString("no_perserahan") == null ? "" : rs
								.getString("NO_PERSERAHAN"));
				
				h.put("kod_hakmilik",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				
				h.put("keterangan", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));

				// ADD BY PEJE - ADD FIELD SEKATAN & SYARAT NYATA
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());
				h.put("syaratNyata", rs.getString("SYARAT_NYATA") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());

			    myLogger.info("getDataHTA:h="+h);
			   // h.put("alamathta2","hta 2");
			    i = i+1;
				listHTA.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return listHTA;
	}



//IL end
}
