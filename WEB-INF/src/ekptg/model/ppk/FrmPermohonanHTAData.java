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

import ekptg.helpers.Utils;

public class FrmPermohonanHTAData extends FrmPrmhnnSek8InternalData{
	static Logger myLogger = Logger.getLogger(FrmPermohonanHTAData.class);
	
	private Vector<Hashtable<String,String>> listHTAX = new Vector<Hashtable<String,String>>();
	private Vector<Hashtable<String,String>> listHTA = new Vector<Hashtable<String,String>>();
	private Vector<Hashtable<String,String>> listHarta = new Vector<Hashtable<String,String>>();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Hashtable<String,String> getDataHTAX(String idhtaam, String id_permohonansimati)
		throws Exception {
		Db db = null;
		listHarta.clear();
		String sql = "";
		Hashtable<String,String> h = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT H.FLAG_DAFTAR,H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT" +
						", TRIM(TO_CHAR(NVL(H.NILAI_HTA_TARIKHMOHON,0),'999,999,999.99')) NILAI_HTA_TARIKHMOHON" +
						", TRIM(TO_CHAR(NVL(H.NILAI_HTA_TARIKHMATI,0),'999,999,999.99')) NILAI_HTA_TARIKHMATI" +
							//" ,DOKUMEN.ID_DOKUMEN , DOKUMEN.NAMA_DOKUMEN ,PELAN.ID_PELAN " +
						", H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, "+
						" H.ID_NEGERI, H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, H.LUAS, "+
						" H.NO_CAGARAN, H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, "+
						" H.TANGGUNGAN, H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN, H.NAMA_PEMAJU, "+
						" H.ALAMAT_PEMAJU1, H.ALAMAT_PEMAJU2, H.ALAMAT_PEMAJU3, H.POSKOD_PEMAJU, "+
						" H.BANDAR_PEMAJU, H.ID_BANDARPEMAJU, H.ID_NEGERIPEMAJU, H.ALAMAT_HTA1, "+
						" H.ALAMAT_HTA2, H.ALAMAT_HTA3, H.POSKOD_HTA, H.BANDAR_HTA, H.ID_BANDARHTA, "+
						" H.NO_PERJANJIAN, H.TARIKH_PERJANJIAN, H.NAMA_RANCANGAN, H.NO_ROH, H.NO_LOT_ID, "+
						" H.FLAG_KATEGORI_HTA, H.JENIS_KEPENTINGAN  "+
						" FROM TBLPPKHTAPERMOHONAN H "+
						" WHERE " +
						" H.ID_PERMOHONANSIMATI = '"+ id_permohonansimati+ "'"+
						" AND H.JENIS_HTA = 'T'  AND H.ID_HTA = '"+ idhtaam + "' " +
						"";
			//myLogger.info("***GET BY ID getDataHTAX :sql=" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
					
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("FLAG_DAFTAR", rs.getString("FLAG_DAFTAR") == null ? "" : rs.getString("FLAG_DAFTAR"));				
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs.getString("id_Hta"));
				//h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? "": rs.getString("id_Jenishm"));
				//h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? "" : rs.getString("no_Hakmilik"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs.getString("id_Mukim"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs.getString("no_Pt"));
				h.put("nolot", rs.getString("no_Lot_Id") == null ? "" : rs.getString("no_Lot_Id"));
				h.put("noroh", rs.getString("no_Roh") == null ? "" : rs.getString("no_Roh"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs.getString("id_Kategori"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs.getString("id_Luas"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs.getString("luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs.getString("catatan"));

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs.getString("id_Simati"));
				h.put("jeniskepentingan",rs.getString("jenis_Kepentingan") == null ? "" : rs.getString("jenis_Kepentingan"));
				h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon"));			
				h.put("nilai_Hta_mati",rs.getString("nilai_Hta_Tarikhmati"));
				h.put("pemilikan",rs.getString("status_Pemilikan") == null ? "" : rs.getString("status_Pemilikan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs.getString("jenis_Hta"));
				h.put("flag", rs.getString("flag_Kategori_Hta") == null ? "": rs.getString("flag_Kategori_Hta"));

				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs.getString("no_Pajakan"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? "": rs.getString("tanggungan"));
				h.put("noperserahan",rs.getString("no_perserahan") == null ? "" : rs.getString("no_perserahan"));
				h.put("noperjanjian",rs.getString("no_Perjanjian") == null ? "" : rs.getString("no_Perjanjian"));
				h.put("tarikhperjanjian",rs.getString("tarikh_Perjanjian") == null 
							? "": sdf.format(rs.getDate("tarikh_Perjanjian")));
				h.put("namarancangan",rs.getString("nama_Rancangan") == null ? "" : rs.getString("nama_Rancangan"));
				
				h.put("namapemaju", rs.getString("nama_Pemaju") == null ? "": rs.getString("nama_Pemaju"));
				h.put("alamatpemaju1",rs.getString("alamat_Pemaju1") == null ? "" : rs.getString("alamat_Pemaju1"));
				h.put("alamatpemaju2",rs.getString("alamat_Pemaju2") == null ? "" : rs.getString("alamat_Pemaju2"));
				h.put("alamatpemaju3",rs.getString("alamat_Pemaju3") == null ? "" : rs.getString("alamat_Pemaju3"));
				h.put("poskodpemaju",rs.getString("poskod_Pemaju") == null ? "" : rs.getString("poskod_Pemaju"));
				h.put("bandarpemaju",rs.getString("id_bandarpemaju") == null ? "" : rs.getString("id_bandarpemaju"));
				h.put("bandarAdd",rs.getString("id_bandarpemaju") == null ? "" : rs.getString("id_bandarpemaju"));
				h.put("negeripemaju",rs.getString("id_Negeripemaju") == null ? "" : rs.getString("id_Negeripemaju"));
				h.put("negeriAdd",rs.getString("id_Negeripemaju") == null ? "" : rs.getString("id_Negeripemaju"));
				
				h.put("alamathta1", rs.getString("alamat_Hta1") == null ? "": rs.getString("alamat_Hta1"));
				h.put("alamathta2", rs.getString("alamat_Hta2") == null ? "": rs.getString("alamat_Hta2"));
				h.put("alamathta3", rs.getString("alamat_Hta3") == null ? "": rs.getString("alamat_Hta3"));
				h.put("poskodhta", rs.getString("poskod_Hta") == null ? "" : rs.getString("poskod_Hta"));
				h.put("bandarhta", rs.getString("id_bandarhta") == null ? "": rs.getString("id_bandarhta"));
				h.put("bandar", rs.getString("id_bandarhta") == null ? "": rs.getString("id_bandarhta"));
				listHarta.addElement(h);
						
				}
			}catch (Exception re) {
				myLogger.error("Error: ", re);
				//throw re;
			} finally {
				if (db != null)
					db.close();
			}
		myLogger.info("***GET BY ID getDataHTAX :h=" + h);
		return h;
				
	}

	public Vector<Hashtable<String,String>> getDataHTA(String idPerSimati,String jenisHarta) throws Exception {
		Db db = null;
		listHarta.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT H.ID_HTA" +
				" ,H.NO_HAKMILIK, H.NO_PT" +
				" ,H.ID_NEGERI" +
				" ,H.BA_SIMATI, H.BB_SIMATI "+
				" ,RN.NAMA_NEGERI,RD.NAMA_DAERAH,RM.NAMA_MUKIM" +
				" ,RUJ.KOD_JENIS_HAKMILIK,RUJ.KETERANGAN  " +
				" ,PS.ID_PERMOHONAN "+
				//", H.ALAMAT_HTA1, H.ALAMAT_HTA2, H.ALAMAT_HTA3" +
				//" , H.ID_JENISHM, H.ID_JENISPB, H.ID_DAERAH, H.ID_LUAS, H.ID_BANDARHTA, H.ID_MUKIM, H.LUAS_HMP,"+
				//" ,H.ID_SIMATI, H.NILAI_HTA_TARIKHMOHON, H.NILAI_HTA_TARIKHMATI,H.ID_KATEGORI "+
				//" H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, H.JENIS_TNH, H.JENIS_HTA, H.TANGGUNGAN," +
				//" H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN " +
				" FROM TBLPPKHTAPERMOHONAN H, TBLPPKPERMOHONANSIMATI PS, TBLRUJJENISHAKMILIK RUJ "+
				", TBLRUJNEGERI RN,TBLRUJDAERAH RD,TBLRUJMUKIM RM "+
				" WHERE H.ID_JENISHM = RUJ.ID_JENISHAKMILIK "+
				" AND H.ID_NEGERI=RN.ID_NEGERI "+
				" AND H.ID_DAERAH=RD.ID_DAERAH "+
				" AND H.ID_MUKIM = RM.ID_MUKIM " +
				" AND PS.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI "+
				" AND H.ID_PERMOHONANSIMATI = '"+ idPerSimati+ "' "+
				" AND H.JENIS_HTA = '"+jenisHarta+"' " + //Y-Ada hakmilik, T-Tiada Hakmilik
				" ORDER BY H.ID_HTA DESC ";
			//myLogger.info("HTAAM STRUCTURE BARU:sql=" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			//
			Hashtable<String,String> h;
			int i = 0;
			while (rs.next()) {			    
				h = new Hashtable<String,String>();
				//h.put("alamathta1", rs.getString("alamat_hta1") == null ? "" : rs.getString("alamat_hta1"));
				//h.put("alamathta2", rs.getString("alamat_hta2") == null ? "" : rs.getString("alamat_hta2"));
				//h.put("alamathta3", rs.getString("alamat_hta3") == null ? "" : rs.getString("alamat_hta3"));
				//h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs.getString("id_Simati"));
//				h.put("nilai_Hta_memohon", String.valueOf(rs.getString("nilai_Hta_Tarikhmohon") == null 
//					? "" : rs.getDouble("nilai_Hta_Tarikhmohon")));
//				h.put("nilai_Hta_mati",String.valueOf(rs.getString("nilai_Hta_Tarikhmati") == null 
//					? "" : rs.getDouble("nilai_Hta_Tarikhmati")));
//				h.put("pemilikan", rs.getString("id_Jenispb") == null ? "" : rs.getString("id_Jenispb"));
//				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs.getString("jenis_Hta"));				
//				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs.getString("jenis_Tnh"));			
//				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? "": rs.getString("id_Jenishm"));
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs.getString("id_Hta"));		
				h.put("kod_hakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK"));				
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? "": rs.getString("no_Hakmilik"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs.getString("no_Pt"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs.getString("ba_Simati"));				
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs.getString("bb_Simati"));				
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "": rs.getString("KETERANGAN"));				
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri")); 
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());
//				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs.getString("id_Kategori"));
//				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs.getString("id_Daerah"));
//				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs.getString("id_Mukim"));				
//				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs.getString("luas_Hmp"));				
//				h.put("luasasal", rs.getString("luas") == null ? "" : rs.getString("luas"));				
//				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs.getString("no_Cagaran"));				
//				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs.getString("no_Pajakan"));				
//				h.put("tanggungan", rs.getString("tanggungan") == null ? "": rs.getString("tanggungan"));				
//				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs.getString("id_Luas"));				
//				h.put("catatan", rs.getString("catatan") == null ? "" : rs.getString("catatan"));				
//				h.put("noperserahan", rs.getString("no_perserahan") == null ? "" : rs.getString("NO_PERSERAHAN"));				
				// ADD BY PEJE - ADD FIELD SEKATAN & SYARAT NYATA
				//h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());
				//h.put("syaratNyata", rs.getString("SYARAT_NYATA") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				//2018/05/24 Lampiran 
				ekptg.model.ppk.util.LampiranBean l = new ekptg.model.ppk.util.LampiranBean();
				h.put("lampirans", l.getLampirans(rs.getString("ID_HTA")));
				
				//2018/05/25 Tambah paparan negeri,daerah dan Mukim 
				h.put("namaNegeri", Utils.isNull(rs.getString("NAMA_NEGERI")));
				h.put("namaDaerah", Utils.isNull(rs.getString("NAMA_DAERAH")));
				h.put("namaMukim", Utils.isNull(rs.getString("NAMA_MUKIM")));
				
				i = i+1;
			    listHarta.addElement(h);
				
			}
		} finally {
			if (db != null)
				db.close();
		}
		return listHarta;
		
	}
	//setDataHTAbyIdHtaam
	public Hashtable<String,String> getDataHTAbyIdHtaam(String idhtaam, String idPermohonanSimati) throws Exception {
		Db db = null;
		listHarta.clear();
		Hashtable<String,String> h = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT H.FLAG_DAFTAR,H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT" +
					", NVL(LTRIM(to_char(H.NILAI_HTA_TARIKHMOHON,'99999990.00')),'0.00') NILAI_HTA_TARIKHMOHON" +
					",NVL(LTRIM(to_char(H.NILAI_HTA_TARIKHMATI,'99999990.00')),'0.00') NILAI_HTA_TARIKHMATI, "
					+ " H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB" +
					", H.ID_NEGERI,H.ID_DAERAH, H.ID_MUKIM "
					+ ", H.ID_LUAS, H.LUAS_HMP, H.LUAS, H.NO_CAGARAN, "
					+ "H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN" 
					+ ",H.ALAMAT_HTA1, H.ALAMAT_HTA2, H.ALAMAT_HTA3, H.BANDAR_HTA, H.POSKOD_HTA, H.ID_BANDARHTA, "
					+ "H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN  "
					//+ ADD BY PEJE - ADD FIELD SEKATAN & SYARAT NYATA
					+ ",H.SEKATAN, H.SYARAT_NYATA  "
					//HTATH
					+ ",H.NAMA_PEMAJU "
					+ ",H.ALAMAT_PEMAJU1, H.ALAMAT_PEMAJU2, H.ALAMAT_PEMAJU3, H.POSKOD_PEMAJU "
					+ ",H.BANDAR_PEMAJU, H.ID_BANDARPEMAJU, H.ID_NEGERIPEMAJU "
					+ ",H.NO_PERJANJIAN, H.TARIKH_PERJANJIAN, H.NAMA_RANCANGAN, H.NO_ROH, H.NO_LOT_ID "
					+ ",H.FLAG_KATEGORI_HTA, H.JENIS_KEPENTINGAN  "
					+ "FROM TBLPPKHTAPERMOHONAN H "
					//",TBLPPKHTA HP, TBLPPKSIMATI S"
					+ " WHERE "
					//"H.ID_SIMATI = S.ID_SIMATI"
					//+ " AND HP.ID_HTA = H.ID_HTA " + " AND PELAN.ID_HTA (+) = H.ID_HTA  "
					//+ " AND DOKUMEN.ID_DOKUMEN (+)= PELAN.ID_DOKUMEN  "
					// " AND H.ID_PERMOHONANSIMATI = HP.ID_PERMOHONANSIMATI " +
					//+ " AND H.JENIS_HTA = 'Y'  " +
					+ " H.ID_HTA = '"+ idhtaam+ "'"
					+ " AND H.ID_PERMOHONANSIMATI = '"+ idPermohonanSimati+ "'"
					+"";
			
			//myLogger.info("getDataHTAbyIdHtaam:sql="+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("FLAG_DAFTAR", rs.getString("FLAG_DAFTAR") == null ? "": rs.getString("FLAG_DAFTAR"));
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs.getString("id_Hta"));
				//h.put("idDokumen", rs.getString("id_dokumen") == null ? "" : rs.getString("id_dokumen"));//IL
				//h.put("namaPelanUp", rs.getString("nama_dokumen") == null ? "" : rs.getString("nama_dokumen"));//IL
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? "": rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "0" : rs.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs.getString("no_Pt"));
				h.put("nilai_Hta_memohon",rs.getString("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",rs.getString("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "0" : rs.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? "0": rs.getString("id_Jenishm"));
				h.put("pemilikan",rs.getString("status_Pemilikan") == null ? "" : rs.getString("status_Pemilikan"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "0" : rs.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "0" : rs.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? "": rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "0" : rs.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs.getString("catatan"));
				h.put("noperserahan", rs.getString("NO_PERSERAHAN") == null ? "" : rs.getString("NO_PERSERAHAN"));				
				// ADD BY SALNIZAM - ADD FIELD ALAMAT
				h.put("alamat1", rs.getString("alamat_Hta1") == null ? "" : rs.getString("alamat_Hta1"));
				h.put("alamat2", rs.getString("alamat_Hta2") == null ? "" : rs.getString("alamat_Hta2"));
				h.put("alamat3", rs.getString("alamat_Hta3") == null ? "" : rs.getString("alamat_Hta3"));
				//System.out.println("Baca sini4##### " + rs.getString("id_bandarhta"));
				h.put("poskod", rs.getString("poskod_hta") == null ? "" : rs.getString("poskod_hta"));		
				h.put("bandar", rs.getString("id_bandarhta") == null ? "0" : rs.getString("id_bandarhta"));
				//h.put("id_bandarhta", rs.getString("id_bandarhta") == null ? "" : rs.getString("id_bandarhta"));	
				// ADD BY PEJE - ADD FIELD SEKATAN & SYARAT NYATA
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());
				h.put("syaratNyata", rs.getString("SYARAT_NYATA") == null ? "": rs.getString("SYARAT_NYATA").toUpperCase());
				//2018 HTATH
				h.put("namapemaju", rs.getString("nama_Pemaju") == null ? "" : rs.getString("nama_Pemaju"));
				h.put("alamatpemaju1",rs.getString("alamat_Pemaju1") == null ? "" : rs.getString("alamat_Pemaju1"));
				h.put("alamatpemaju2",rs.getString("alamat_Pemaju2") == null ? "" : rs.getString("alamat_Pemaju2"));
				h.put("alamatpemaju3",rs.getString("alamat_Pemaju3") == null ? "" : rs.getString("alamat_Pemaju3"));
				h.put("poskodpemaju",rs.getString("poskod_Pemaju") == null ? "" : rs.getString("poskod_Pemaju"));
				h.put("bandarpemaju",rs.getString("id_bandarpemaju") == null ? "0" : rs.getString("id_bandarpemaju"));
				h.put("negeripemaju",rs.getString("id_Negeripemaju") == null ? "0" : rs.getString("id_Negeripemaju"));
				h.put("alamathta1", rs.getString("alamat_Hta1") == null ? "": rs.getString("alamat_Hta1"));
				h.put("alamathta2", rs.getString("alamat_Hta2") == null ? "": rs.getString("alamat_Hta2"));
				h.put("alamathta3", rs.getString("alamat_Hta3") == null ? "": rs.getString("alamat_Hta3"));
				h.put("poskodhta", rs.getString("poskod_Hta") == null ? "" : rs.getString("poskod_Hta"));
				h.put("bandarhta", rs.getString("id_bandarhta") == null ? "0": rs.getString("id_bandarhta"));
				h.put("noperjanjian",rs.getString("no_Perjanjian") == null ? "" : rs.getString("no_Perjanjian"));
				h.put("tarikhperjanjian"
					,rs.getString("tarikh_Perjanjian") == null ? "": sdf.format(rs.getDate("tarikh_Perjanjian")));
				h.put("namarancangan",rs.getString("nama_Rancangan") == null ? "" : rs.getString("nama_Rancangan"));
				h.put("noroh", rs.getString("no_Roh") == null ? "" : rs.getString("no_Roh"));
				h.put("nolot", rs.getString("no_Lot_Id") == null ? "" : rs.getString("no_Lot_Id"));
				h.put("jeniskepentingan",rs.getString("jenis_Kepentingan") == null ? "" : rs.getString("jenis_Kepentingan"));
				listHarta.addElement(h);
				
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
	
	public Vector<Hashtable<String,String>> getDataHTA() {
		return listHarta;
	}

	public Vector<Hashtable<String,String>> getDataHTAX(String idPerSimati) throws Exception {
		Db db = null;
		listHTAX.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT H.ID_HTA" +
				" ,H.ALAMAT_HTA1,H.ALAMAT_HTA2,H.ALAMAT_HTA3, H.POSKOD_HTA, H.BANDAR_HTA "+
				" ,H.NO_ROH"+
				" ,H.BA_SIMATI, H.BB_SIMATI "+
				" ,H.FLAG_KATEGORI_HTA,H.JENIS_KEPENTINGAN "+
				" ,RN.NAMA_NEGERI,RD.NAMA_DAERAH,RM.NAMA_MUKIM" +
				" ,PS.ID_PERMOHONAN "+
				" FROM TBLPPKHTAPERMOHONAN H, TBLPPKPERMOHONANSIMATI PS "+
				" ,TBLRUJNEGERI RN,TBLRUJDAERAH RD,TBLRUJMUKIM RM "+
				" WHERE PS.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI "+
				" AND H.ID_NEGERI=RN.ID_NEGERI "+
				" AND H.ID_DAERAH=RD.ID_DAERAH "+
				" AND H.ID_MUKIM = RM.ID_MUKIM " +
				" AND H.ID_PERMOHONANSIMATI = '"+ idPerSimati+ "' "+
				" AND H.JENIS_HTA = 'T'  " +
				" ORDER BY H.ID_HTA DESC ";
			myLogger.info("getDataHTAX:sql=" + sql.toUpperCase());
			System.out.println("getDataHTAX:sql=" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable<String,String> h;

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs.getString("id_Hta"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs.getString("bb_Simati"));
				// ADD BY SALNIZAM - ADD FIELD ALAMAT
				h.put("alamat1", rs.getString("ALAMAT_HTA1") == null ? "" : rs.getString("ALAMAT_HTA1"));
				h.put("alamat2", rs.getString("ALAMAT_HTA2") == null ? "" : rs.getString("ALAMAT_HTA2"));
				h.put("alamat3", rs.getString("ALAMAT_HTA3") == null ? "" : rs.getString("ALAMAT_HTA3"));
				h.put("poskod", rs.getString("POSKOD_HTA") == null ? "" : rs.getString("POSKOD_HTA"));
				h.put("bandar", Utils.isNull(rs.getString("BANDAR_HTA")));
				h.put("noroh", rs.getString("no_Roh") == null ? "" : rs.getString("no_Roh"));
				h.put("jenis_penting",rs.getString("JENIS_KEPENTINGAN") == null ? "" : rs.getString("JENIS_KEPENTINGAN"));
				h.put("kategori_hta",rs.getString("FLAG_KATEGORI_HTA") == null ? "" : rs.getString("FLAG_KATEGORI_HTA"));
				
				//2018/05/25 Tambah paparan negeri,daerah dan Mukim 
				h.put("namaNegeri", Utils.isNull(rs.getString("NAMA_NEGERI")));
				h.put("namaDaerah", Utils.isNull(rs.getString("NAMA_DAERAH")));
				h.put("namaMukim", Utils.isNull(rs.getString("NAMA_MUKIM")));
				
				//2018/05/24 Lampiran 
				ekptg.model.ppk.util.LampiranBean l = new ekptg.model.ppk.util.LampiranBean();
				h.put("lampirans", Utils.isNull(l.getLampirans(rs.getString("ID_HTA"))));
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
	public Vector<Hashtable<String,String>> getDataHTA_(String idsimati) throws Exception {
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
