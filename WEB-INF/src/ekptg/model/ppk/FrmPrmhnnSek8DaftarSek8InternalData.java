/**
 *
 */
package ekptg.model.ppk;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.File;
import ekptg.helpers.Utils;
import ekptg.view.admin.Push;
import ekptg.view.ppk.PendaftaranCheck;
//import ekptg.view.ppk.socDaerahHtaam;


public class FrmPrmhnnSek8DaftarSek8InternalData {
	static Logger myLogger = Logger.getLogger(PendaftaranCheck.class);
	private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");

	private Vector list = new Vector();
	private Vector listSenaraiPemohonSimati = new Vector();
	private Vector listDataHadulu_pilihan = new Vector();
	private Vector list_permohonan = new Vector();
	private Vector list_setData_online_8 = new Vector();
	

	public Vector getDataPPSPP() {
		return list;
		
	}
	
	
	public Vector setDataPemohon_C(String id) throws Exception {
		Db db = null;
		list_permohonan.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Keluar sini8");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT * FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '" + id
					+ "'";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_permohonan",
						rs.getString("id_permohonan") == null ? "" : rs
								.getString("id_permohonan"));
				h.put("id_fail",
						rs.getString("id_fail") == null ? "" : rs
								.getString("id_fail"));

				list_permohonan.addElement(h);
			}
			return list_permohonan;
		} finally {
			if (db != null)
				db.close();
		}

	}

	Vector list_flag = new Vector();

	public Vector setFlag(String iddulu) throws Exception {
		Db db = null;
		list_flag.clear();

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			// SQLRenderer r = new SQLRenderer();

			String sqlf = "SELECT ID_PERMOHONAN, FLAG_PERMOHONAN FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ iddulu
					+ "' AND (FLAG_PERMOHONAN <> '1' OR FLAG_PERMOHONAN IS NULL)";

			ResultSet rs = stmt.executeQuery(sqlf);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("noFail",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));

				list_flag.addElement(h);

			}
			return list_flag;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector setData_online(String id, String userid) throws Exception {
		
		Db db = null;
		list_setData_online_8.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			System.out.println("Keluar sini1 id >>> "+id);
			sql = "SELECT DISTINCT F.ID_FAIL, F.NO_FAIL, " +
					//"D.ID_DAERAH, " +
					"P.ID_PERMOHONAN, P.TARIKH_MOHON, "
					+ " S.NO_KP_BARU, S.NO_KP_LAMA, S.JENIS_KP AS JENIS_KP_SIMATI, S.NO_KP_LAIN, S.ID_SIMATI, "
					+ " S.NAMA_SIMATI, S.TARIKH_MATI, PM.ID_PEMOHON, PM.NAMA_PEMOHON, PM.NO_KP_BARU AS NO_KP_BARU_PM, "
					+ " PM.NO_KP_LAMA AS NO_KP_LAMA_PM, PM.JENIS_KP AS JENIS_KP_PM, PM.NO_KP_LAIN AS NO_KP_LAIN_PM, " +
							"PM.ALAMAT_1, PM.ALAMAT_2, PM.ALAMAT_3, "
					+ " PM.POSKOD, PM.BANDAR, PM.ID_NEGERI," +
						//	"N.ID_NEGERI, N.NAMA_NEGERI, D.NAMA_DAERAH, " +
							"" +
							"P.SEKSYEN, "
					+ " ST.KETERANGAN, P.ID_STATUS, " +
						//	"U.NAMA_PEJABAT, " +
							" MOSI.ID_PERMOHONANSIMATI, "
					+ " S.UMUR as umursimati, S.JANTINA as jantinasimati,  PM.UMUR as umurpemohon, PM.JANTINA as jantinapemohon," +
							//"U.ID_PEJABATJKPTG, " +
							"P.NO_SUBJAKET,  "
					+ " PM.ALAMAT1_SURAT, PM.ALAMAT2_SURAT, PM.ALAMAT3_SURAT,PM.POSKOD_SURAT, " +
							"PM.BANDAR_SURAT, PM.ID_NEGERISURAT, PM.ID_BANDAR," +
							" S.TARIKH_LAHIR AS TARIKH_LAHIR_SIMATI,PM.nama_pelbagainegara,PM.nama_pelbagainegara_surat, "+
							
							//" U.ALAMAT1, " +
							//"DX.NAMA_DAERAH AS D_P," +
							" PM.ID_BANDARSURAT,"
					+ " P.BATAL_KUASA_PENTADBIR, P.LANTIK_PENTADBIR, P.BATAL_P_AMANAH, " +
							"P.LANTIK_P_AMANAH, P.HARTA_TINGGAL, P.LAIN_TUJUAN, PM.ID_TARAFKPTG," +
							"PM.NO_TEL,PM.NO_HP,PM.STATUS_PEMOHON,PM.ID_ARB,S.TARIKH_MATI," +
							//"U.ID_NEGERI AS ID_NEGERIPEJABAT," +
							"PM.ID_SAUDARA,P.NO_PERMOHONAN_ONLINE,P.TARIKH_MOHON_ONLINE   "
					+ " FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P," +
							//" TBLRUJNEGERI N, TBLRUJDAERAH D, TBLRUJDAERAH DX, " +
							"TBLPPKSIMATI S, "
					+ " TBLPPKPEMOHON PM, TBLRUJSTATUS ST, " +
							//"TBLRUJPEJABATJKPTG U,  " +
							"TBLPPKPERMOHONANSIMATI MOSI" 
							//", USERS_INTERNAL UR  "
					+ " WHERE " +
					//		"F.ID_NEGERI = N.ID_NEGERI(+)  "
					// +" AND N.ID_NEGERI = PM.ID_NEGERISURAT(+)"
					//+ " AND P.ID_DAERAHMHN = D.ID_DAERAH(+) "
					//+ " AND UR.USER_ID  = '"
					//+ userid
					//+ "' "
					//+ " AND UR.ID_PEJABATJKPTG = U.ID_PEJABATJKPTG "
					//+ " AND " +
							"P.ID_FAIL = F.ID_FAIL"
					// + " AND PM.ID_PERMOHONAN = P.ID_PERMOHONAN(+) "
					+ " AND P.ID_PEMOHON = PM.ID_PEMOHON(+) "
					+ " AND S.ID_SIMATI = MOSI.ID_SIMATI  "
					+ " AND P.ID_PERMOHONAN = MOSI.ID_PERMOHONAN  "
					+ " AND P.ID_STATUS = ST.ID_STATUS(+)  "
        			//+ " AND D.ID_DAERAH = P.ID_DAERAHMHN  "
					//+ " AND U.ID_DAERAH = DX.ID_DAERAH(+) "
					+ " AND P.ID_PERMOHONAN = '" + id + "' ";
			
		
         
			//System.out.println("SQL FAR" + sql);

			myLogger.info("******** SQL SET DATA ONLINE xxxxxxxxxxxx:" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			
			while (rs.next()) {
				h = new Hashtable();
				h.put("nama_pelbagainegara",
						rs.getString("nama_pelbagainegara") == null ? "" : rs
								.getString("nama_pelbagainegara"));
				
				h.put("nama_pelbagainegara_surat",
						rs.getString("nama_pelbagainegara_surat") == null ? "" : rs
								.getString("nama_pelbagainegara_surat"));
				
				h.put("id_Permohonansimati",
						rs.getString("id_Permohonansimati") == null ? "" : rs
								.getString("id_Permohonansimati"));
			
				h.put("idFail",
						rs.getString("id_Fail") == null ? "" : rs
								.getString("id_Fail"));

				h.put("noFail",
						rs.getString("no_Fail") == null ? "" : rs
								.getString("no_Fail"));
				
				
				h.put("no_fail_online",
						rs.getString("NO_PERMOHONAN_ONLINE") == null ? "" : rs
								.getString("NO_PERMOHONAN_ONLINE"));
				
				// //h.put("noFailBaru", "");

				h.put("idDaerah","");
				h.put("idPermohonan",
						rs.getString("id_Permohonan") == null ? "" : rs
								.getString("id_Permohonan"));
				h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("noKpLama",
						rs.getString("no_Kp_Lama") == null ? "" : rs
								.getString("no_Kp_Lama"));
				h.put("jenisKp", rs.getString("JENIS_KP_SIMATI") == null ? "" : rs.getString("JENIS_KP_SIMATI"));
				h.put("noKpLain",
						rs.getString("no_Kp_Lain") == null ? "" : rs
								.getString("no_Kp_Lain"));
				h.put("idSimati",
						rs.getString("id_Simati") == null ? "" : rs
								.getString("id_Simati"));
				System.out.println("Keluar sini1a");
				h.put("namaSimati", rs.getString("nama_Simati") == null ? ""
						: rs.getString("nama_Simati"));
				h.put("tarikhMati", rs.getDate("tarikh_Mati") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mati")));
				h.put("idPemohon",
						rs.getString("id_Pemohon") == null ? "" : rs
								.getString("id_Pemohon"));
				h.put("namaPemohon", rs.getString("nama_Pemohon") == null ? ""
						: rs.getString("nama_Pemohon"));
				h.put("noKpBaruPemohon1", rs.getString("NO_KP_BARU_PM") == null ? "" : rs
						.getString("NO_KP_BARU_PM").substring(0, 6));	
				h.put("noKpBaruPemohon2", rs.getString("NO_KP_BARU_PM") == null ? "" : rs
						.getString("NO_KP_BARU_PM").substring(6, 8));
				h.put("noKpBaruPemohon3", rs.getString("NO_KP_BARU_PM") == null ? "" : rs
						.getString("NO_KP_BARU_PM").substring(8, 12));
				
				h.put("noKpLamaPemohon",
						rs.getString("NO_KP_LAMA_PM") == null ? "" : rs.getString("NO_KP_LAMA_PM"));
				h.put("jenisKpPemohon",
						rs.getString("JENIS_KP_PM") == null ? "" : rs.getString("JENIS_KP_PM"));
				h.put("noKpLainPemohon",
						rs.getString("NO_KP_LAIN_PM") == null ? "" : rs.getString("NO_KP_LAIN_PM"));

				h.put("alamat1",
						rs.getString("alamat_1") == null ? "" : rs
								.getString("alamat_1"));
				h.put("alamat2",
						rs.getString("alamat_2") == null ? "" : rs
								.getString("alamat_2"));
				h.put("alamat3",
						rs.getString("alamat_3") == null ? "" : rs
								.getString("alamat_3"));
				h.put("poskod",
						rs.getString("poskod") == null ? "" : rs
								.getString("poskod"));
				h.put("bandar",
						rs.getString("bandar") == null ? "" : rs
								.getString("bandar"));
				h.put("idbandar",
						rs.getString("ID_BANDAR") == null ? "" : rs
								.getString("ID_BANDAR"));
				h.put("idbandarsurat",
						rs.getString("id_Bandarsurat") == null ? "" : rs
								.getString("id_Bandarsurat"));
				h.put("idnegeri","");

				h.put("namanegeri","");
				h.put("namadaerah","");
				
				
				h.put("seksyen",
						rs.getString("seksyen") == null ? "" : rs
								.getString("seksyen"));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("id_Status",
						rs.getString("id_Status") == null ? "" : rs
								.getString("id_Status"));


					h.put("namaPejabat","");
				

				h.put("pmidnegeri",
						rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));

				h.put("alamat1surat",
						rs.getString("alamat1_surat") == null ? "" : rs
								.getString("alamat1_surat"));
				h.put("alamat2surat",
						rs.getString("alamat2_surat") == null ? "" : rs
								.getString("alamat2_surat"));
				h.put("alamat3surat",
						rs.getString("alamat3_surat") == null ? "" : rs
								.getString("alamat3_surat"));
				h.put("poskodsurat", rs.getString("poskod_surat") == null ? ""
						: rs.getString("poskod_surat"));
				h.put("bandarsurat", rs.getString("bandar_surat") == null ? ""
						: rs.getString("bandar_surat"));
				h.put("idnegerisurat",
						rs.getString("id_Negerisurat") == null ? "" : rs
								.getString("id_Negerisurat"));
		
				h.put("umursimati",
						rs.getString("umursimati") == null ? "" : rs.getString("umursimati"));
				h.put("jantinasimati",
						rs.getString("jantinasimati") == null ? "" : rs.getString("jantinasimati"));

				h.put("umurpemohon",
						rs.getString("umurpemohon") == null ? "" : rs.getString("umurpemohon"));
				h.put("jantinapemohon",
						rs.getString("jantinapemohon") == null ? "" : rs.getString("jantinapemohon"));

				h.put("no_subjaket", rs.getString("no_subjaket") == null ? ""
						: rs.getString("no_subjaket"));

				h.put("tarikhMohon", rs.getString("tarikh_Mohon") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mohon")));
				
				
				h.put("bkp", rs.getString("BATAL_KUASA_PENTADBIR") == null ? ""
						: rs.getString("BATAL_KUASA_PENTADBIR"));
				h.put("lp",
						rs.getString("LANTIK_PENTADBIR") == null ? "" : rs
								.getString("LANTIK_PENTADBIR"));
				h.put("bpa",
						rs.getString("BATAL_P_AMANAH") == null ? "" : rs
								.getString("BATAL_P_AMANAH"));
				h.put("lpa",
						rs.getString("LANTIK_P_AMANAH") == null ? "" : rs
								.getString("LANTIK_P_AMANAH"));
				h.put("ht",
						rs.getString("HARTA_TINGGAL") == null ? "" : rs
								.getString("HARTA_TINGGAL"));
				h.put("lt",
						rs.getString("LAIN_TUJUAN") == null ? "" : rs
								.getString("LAIN_TUJUAN"));

				h.put("lt",
						rs.getString("LAIN_TUJUAN") == null ? "" : rs
								.getString("LAIN_TUJUAN"));
				h.put("taraf_penting",
						rs.getString("ID_TARAFKPTG") == null ? "" : rs
								.getString("ID_TARAFKPTG"));

				h.put("no_tel",
						rs.getString("NO_TEL") == null ? "" : rs
								.getString("NO_TEL"));

				h.put("no_hp",
						rs.getString("NO_HP") == null ? "" : rs
								.getString("NO_HP"));

				h.put("jenis_pej",
						rs.getString("ID_ARB") == null ? "" : rs
								.getString("ID_ARB"));

				h.put("jenis_pemohon",
						rs.getString("STATUS_PEMOHON") == null ? "" : rs
								.getString("STATUS_PEMOHON"));

				h.put("t_mati",
						rs.getString("TARIKH_MATI") == null ? "" : rs
								.getString("TARIKH_MATI"));
				
				h.put("socSaudaraWaris",
						rs.getString("ID_SAUDARA") == null ? "" : rs
								.getString("ID_SAUDARA"));
				
				

				if (rs.getString("TARIKH_MATI") != ""
						&& rs.getString("TARIKH_MATI") != null) {
					DateFormat dm = new SimpleDateFormat("dd/MM/yyyy");
					Date tm_s = dm.parse(sdf.format(rs.getDate("TARIKH_MATI")));
					Date tm_t = dm.parse("01/11/1991");

					if (tm_s.before(tm_t)) {
						h.put("jpphlepas", "no");
					} else if (tm_s.after(tm_t)) {
						h.put("jpphlepas", "yes");
					} else {
						h.put("jpphlepas", "no");
					}
				} else {
					h.put("jpphlepas", "no");
				}

				
				h.put("tarikhMohonOnline", rs.getString("TARIKH_MOHON_ONLINE") == null ? "": sdf.format(rs.getDate("TARIKH_MOHON_ONLINE")));

				
				//h.put("test","keluar la");
				
				
				
				
				if (rs.getString("tarikh_Mohon") != ""
						&& rs.getString("tarikh_Mohon") != null) {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date t_m = df.parse(sdf.format(rs.getDate("tarikh_Mohon")));
					Date sep_m = df.parse("01/09/2009");
			
					if (t_m.before(sep_m)) {
						h.put("lepassatusept", "yes");
					} else if (t_m.after(sep_m)) {
						h.put("lepassatusept", "no");
					} else {
						h.put("lepassatusept", "no");
					}
				}

				
				list_setData_online_8.addElement(h);
			}
			return list_setData_online_8;
		} finally {
			if (db != null)
				db.close();
		}
	}

private Vector list_setData_online_17 = new Vector();
	
public Vector setData_online_17(String id, String userid) throws Exception {
		
		Db db = null;
		list_setData_online_17.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			System.out.println("Keluar sini2");
			sql = "SELECT DISTINCT F.ID_FAIL, F.NO_FAIL, " +
					//"D.ID_DAERAH, " +
					"P.ID_PERMOHONAN, P.TARIKH_MOHON, "
					+ " S.NO_KP_BARU, S.NO_KP_LAMA, S.JENIS_KP AS JENIS_KP_SIMATI, S.NO_KP_LAIN, S.ID_SIMATI, "
					+ " S.NAMA_SIMATI, S.TARIKH_MATI, PM.ID_PEMOHON, PM.NAMA_PEMOHON, PM.NO_KP_BARU AS NO_KP_BARU_PM, "
					+ " PM.NO_KP_LAMA AS NO_KP_LAMA_PM, PM.JENIS_KP AS JENIS_KP_PM, PM.NO_KP_LAIN AS NO_KP_LAIN_PM, " +
							"PM.ALAMAT_1, PM.ALAMAT_2, PM.ALAMAT_3, "
					+ " PM.POSKOD, PM.BANDAR, PM.ID_NEGERI," +
						//	"N.ID_NEGERI, N.NAMA_NEGERI, D.NAMA_DAERAH, " +
							"" +
							"P.SEKSYEN, "
					+ " ST.KETERANGAN, P.ID_STATUS, " +
						//	"U.NAMA_PEJABAT, " +
							" MOSI.ID_PERMOHONANSIMATI, "
					+ " S.UMUR as umursimati, S.JANTINA as jantinasimati,  PM.UMUR as umurpemohon, PM.JANTINA as jantinapemohon," +
							//"U.ID_PEJABATJKPTG, " +
							"P.NO_SUBJAKET,  "
					+ " PM.ALAMAT1_SURAT, PM.ALAMAT2_SURAT, PM.ALAMAT3_SURAT,PM.POSKOD_SURAT, " +
							"PM.BANDAR_SURAT, PM.ID_NEGERISURAT, PM.ID_BANDAR," +
							//" U.ALAMAT1, " +
							//"DX.NAMA_DAERAH AS D_P," +
							" PM.ID_BANDARSURAT,"
					+ " P.BATAL_KUASA_PENTADBIR, P.LANTIK_PENTADBIR, P.BATAL_P_AMANAH, " +
							"P.LANTIK_P_AMANAH, P.HARTA_TINGGAL, P.LAIN_TUJUAN, PM.ID_TARAFKPTG," +
							"PM.NO_TEL,PM.NO_HP,PM.STATUS_PEMOHON,PM.ID_ARB," +
							//"U.ID_NEGERI AS ID_NEGERIPEJABAT," +
							"PM.ID_SAUDARA,P.NO_PERMOHONAN_ONLINE,P.TARIKH_MOHON_ONLINE   "
					+ " FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P," +
							//" TBLRUJNEGERI N, TBLRUJDAERAH D, TBLRUJDAERAH DX, " +
							"TBLPPKSIMATI S, "
					+ " TBLPPKPEMOHON PM, TBLRUJSTATUS ST, " +
							//"TBLRUJPEJABATJKPTG U,  " +
							"TBLPPKPERMOHONANSIMATI MOSI" 
							//", USERS_INTERNAL UR  "
					+ " WHERE " +
					//		"F.ID_NEGERI = N.ID_NEGERI(+)  "
					// +" AND N.ID_NEGERI = PM.ID_NEGERISURAT(+)"
					//+ " AND P.ID_DAERAHMHN = D.ID_DAERAH(+) "
					//+ " AND UR.USER_ID  = '"
					//+ userid
					//+ "' "
					//+ " AND UR.ID_PEJABATJKPTG = U.ID_PEJABATJKPTG "
					//+ " AND " +
							"P.ID_FAIL = F.ID_FAIL"
					// + " AND PM.ID_PERMOHONAN = P.ID_PERMOHONAN(+) "
					+ " AND P.ID_PEMOHON = PM.ID_PEMOHON(+) "
					+ " AND S.ID_SIMATI = MOSI.ID_SIMATI  "
					+ " AND P.ID_PERMOHONAN = MOSI.ID_PERMOHONAN  "
					+ " AND P.ID_STATUS = ST.ID_STATUS(+)  "
        			//+ " AND D.ID_DAERAH = P.ID_DAERAHMHN  "
					//+ " AND U.ID_DAERAH = DX.ID_DAERAH(+) "
					+ " AND P.ID_PERMOHONAN = '" + id + "' ";
			
		
         
			//System.out.println("SQL FAR" + sql);

			myLogger.info("******** SQL SET DATA ONLINE 17 XXXXXXXXXXXXXXXXXXXXXX:" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_Permohonansimati",
						rs.getString("id_Permohonansimati") == null ? "" : rs
								.getString("id_Permohonansimati"));
			
				h.put("idFail",
						rs.getString("id_Fail") == null ? "" : rs
								.getString("id_Fail"));

				h.put("noFail",
						rs.getString("no_Fail") == null ? "" : rs
								.getString("no_Fail"));
				
				
				h.put("no_fail_online",
						rs.getString("NO_PERMOHONAN_ONLINE") == null ? "" : rs
								.getString("NO_PERMOHONAN_ONLINE"));
				
				// //h.put("noFailBaru", "");

				h.put("idDaerah","");
				h.put("idPermohonan",
						rs.getString("id_Permohonan") == null ? "" : rs
								.getString("id_Permohonan"));
				h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("noKpLama",
						rs.getString("no_Kp_Lama") == null ? "" : rs
								.getString("no_Kp_Lama"));
				h.put("jenisKp", rs.getString("JENIS_KP_SIMATI") == null ? "" : rs.getString("JENIS_KP_SIMATI"));
				h.put("noKpLain",
						rs.getString("no_Kp_Lain") == null ? "" : rs
								.getString("no_Kp_Lain"));
				h.put("idSimati",
						rs.getString("id_Simati") == null ? "" : rs
								.getString("id_Simati"));
				System.out.println("Keluar sini2a");
				h.put("namaSimati", rs.getString("nama_Simati") == null ? ""
						: rs.getString("nama_Simati"));
				h.put("tarikhMati", rs.getDate("tarikh_Mati") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mati")));
				/*h.put("FLAG_PRINT_NILAIAN_HARTA", rs.getString("FLAG_PRINT_NILAIAN_HARTA") == null ? ""
						: rs.getString("FLAG_PRINT_NILAIAN_HARTA"));*/
				
				h.put("idPemohon",
						rs.getString("id_Pemohon") == null ? "" : rs
								.getString("id_Pemohon"));
				h.put("namaPemohon", rs.getString("nama_Pemohon") == null ? ""
						: rs.getString("nama_Pemohon"));
				h.put("noKpBaruPemohon1", rs.getString("NO_KP_BARU_PM") == null ? "" : rs
						.getString("NO_KP_BARU_PM").substring(0, 6));	
				h.put("noKpBaruPemohon2", rs.getString("NO_KP_BARU_PM") == null ? "" : rs
						.getString("NO_KP_BARU_PM").substring(6, 8));
				h.put("noKpBaruPemohon3", rs.getString("NO_KP_BARU_PM") == null ? "" : rs
						.getString("NO_KP_BARU_PM").substring(8, 12));
				
				h.put("noKpLamaPemohon",
						rs.getString("NO_KP_LAMA_PM") == null ? "" : rs.getString("NO_KP_LAMA_PM"));
				h.put("jenisKpPemohon",
						rs.getString("JENIS_KP_PM") == null ? "" : rs.getString("JENIS_KP_PM"));
				h.put("noKpLainPemohon",
						rs.getString("NO_KP_LAIN_PM") == null ? "" : rs.getString("NO_KP_LAIN_PM"));

				h.put("alamat1",
						rs.getString("alamat_1") == null ? "" : rs
								.getString("alamat_1"));
				h.put("alamat2",
						rs.getString("alamat_2") == null ? "" : rs
								.getString("alamat_2"));
				h.put("alamat3",
						rs.getString("alamat_3") == null ? "" : rs
								.getString("alamat_3"));
				h.put("poskod",
						rs.getString("poskod") == null ? "" : rs
								.getString("poskod"));
				h.put("bandar",
						rs.getString("bandar") == null ? "" : rs
								.getString("bandar"));
				h.put("idbandar",
						rs.getString("ID_BANDAR") == null ? "" : rs
								.getString("ID_BANDAR"));
				h.put("idbandarsurat",
						rs.getString("id_Bandarsurat") == null ? "" : rs
								.getString("id_Bandarsurat"));
				h.put("idnegeri","");

				h.put("namanegeri","");
				h.put("namadaerah","");
				
				
				h.put("seksyen",
						rs.getString("seksyen") == null ? "" : rs
								.getString("seksyen"));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("id_Status",
						rs.getString("id_Status") == null ? "" : rs
								.getString("id_Status"));


					h.put("namaPejabat","");
				

				h.put("pmidnegeri",
						rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));

				h.put("alamat1surat",
						rs.getString("alamat1_surat") == null ? "" : rs
								.getString("alamat1_surat"));
				h.put("alamat2surat",
						rs.getString("alamat2_surat") == null ? "" : rs
								.getString("alamat2_surat"));
				h.put("alamat3surat",
						rs.getString("alamat3_surat") == null ? "" : rs
								.getString("alamat3_surat"));
				h.put("poskodsurat", rs.getString("poskod_surat") == null ? ""
						: rs.getString("poskod_surat"));
				h.put("bandarsurat", rs.getString("bandar_surat") == null ? ""
						: rs.getString("bandar_surat"));
				h.put("idnegerisurat",
						rs.getString("id_Negerisurat") == null ? "" : rs
								.getString("id_Negerisurat"));
		
				h.put("umursimati",
						rs.getString("umursimati") == null ? "" : rs.getString("umursimati"));
				h.put("jantinasimati",
						rs.getString("jantinasimati") == null ? "" : rs.getString("jantinasimati"));

				h.put("umurpemohon",
						rs.getString("umurpemohon") == null ? "" : rs.getString("umurpemohon"));
				h.put("jantinapemohon",
						rs.getString("jantinapemohon") == null ? "" : rs.getString("jantinapemohon"));

				h.put("no_subjaket", rs.getString("no_subjaket") == null ? ""
						: rs.getString("no_subjaket"));

				h.put("tarikhMohon", rs.getString("tarikh_Mohon") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mohon")));
				
				
				h.put("bkp", rs.getString("BATAL_KUASA_PENTADBIR") == null ? ""
						: rs.getString("BATAL_KUASA_PENTADBIR"));
				h.put("lp",
						rs.getString("LANTIK_PENTADBIR") == null ? "" : rs
								.getString("LANTIK_PENTADBIR"));
				h.put("bpa",
						rs.getString("BATAL_P_AMANAH") == null ? "" : rs
								.getString("BATAL_P_AMANAH"));
				h.put("lpa",
						rs.getString("LANTIK_P_AMANAH") == null ? "" : rs
								.getString("LANTIK_P_AMANAH"));
				h.put("ht",
						rs.getString("HARTA_TINGGAL") == null ? "" : rs
								.getString("HARTA_TINGGAL"));
				h.put("lt",
						rs.getString("LAIN_TUJUAN") == null ? "" : rs
								.getString("LAIN_TUJUAN"));

				h.put("lt",
						rs.getString("LAIN_TUJUAN") == null ? "" : rs
								.getString("LAIN_TUJUAN"));
				h.put("taraf_penting",
						rs.getString("ID_TARAFKPTG") == null ? "" : rs
								.getString("ID_TARAFKPTG"));

				h.put("no_tel",
						rs.getString("NO_TEL") == null ? "" : rs
								.getString("NO_TEL"));

				h.put("no_hp",
						rs.getString("NO_HP") == null ? "" : rs
								.getString("NO_HP"));

				h.put("jenis_pej",
						rs.getString("ID_ARB") == null ? "" : rs
								.getString("ID_ARB"));

				h.put("jenis_pemohon",
						rs.getString("STATUS_PEMOHON") == null ? "" : rs
								.getString("STATUS_PEMOHON"));

				h.put("t_mati",
						rs.getString("TARIKH_MATI") == null ? "" : rs
								.getString("TARIKH_MATI"));
				
				h.put("socSaudaraWaris",
						rs.getString("ID_SAUDARA") == null ? "" : rs
								.getString("ID_SAUDARA"));
				
				

				if (rs.getString("TARIKH_MATI") != ""
						&& rs.getString("TARIKH_MATI") != null) {
					DateFormat dm = new SimpleDateFormat("dd/MM/yyyy");
					Date tm_s = dm.parse(sdf.format(rs.getDate("TARIKH_MATI")));
					Date tm_t = dm.parse("01/11/1991");

					if (tm_s.before(tm_t)) {
						h.put("jpphlepas", "no");
					} else if (tm_s.after(tm_t)) {
						h.put("jpphlepas", "yes");
					} else {
						h.put("jpphlepas", "no");
					}
				} else {
					h.put("jpphlepas", "no");
				}

				
				h.put("tarikhMohonOnline", rs.getString("TARIKH_MOHON_ONLINE") == null ? "": sdf.format(rs.getDate("TARIKH_MOHON_ONLINE")));

			
				
				if (rs.getString("tarikh_Mohon") != ""
						&& rs.getString("tarikh_Mohon") != null) {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date t_m = df.parse(sdf.format(rs.getDate("tarikh_Mohon")));
					Date sep_m = df.parse("01/09/2009");
			
					if (t_m.before(sep_m)) {
						h.put("lepassatusept", "yes");
					} else if (t_m.after(sep_m)) {
						h.put("lepassatusept", "no");
					} else {
						h.put("lepassatusept", "no");
					}
				}

				
				list_setData_online_17.addElement(h);
			}
			return list_setData_online_17;
		} finally {
			if (db != null)
				db.close();
		}
	}

	

public Vector setDataNoKP(String idpp) throws Exception {
	Db db = null;
	list.clear();
	String sql = "";
	//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	try {
		db = new Db();
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();
		System.out.println("Keluar sini3");
		sql = "SELECT NO_KP_BARU, NO_KP_LAMA, NO_KP_LAIN FROM TBLPPKSIMATI WHERE ID_SIMATI = (SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"+idpp+"')";
		System.out.println("******** SQL SET DATANOKP:" + sql.toUpperCase());
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;

		
		
		while (rs.next()) {
			h = new Hashtable();
			
			h.put("NO_KP_BARU",
					rs.getString("NO_KP_BARU") == null ? "" : rs
							.getString("NO_KP_BARU"));
			h.put("NO_KP_LAMA",
					rs.getString("NO_KP_LAMA") == null ? "" : rs
							.getString("NO_KP_LAMA"));
			h.put("NO_KP_LAIN",
					rs.getString("NO_KP_LAIN") == null ? "" : rs
							.getString("NO_KP_LAIN"));
			list.addElement(h);
			
		}
		return list;
	} finally {
		if (db != null)
			db.close();
	}
}

	public Vector setData(String id, String userid) throws Exception {
		Db db = null;
		list.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			System.out.println("Keluar sini3");
			sql = "SELECT DISTINCT F.ID_FAIL, F.NO_FAIL, D.ID_DAERAH, P.ID_PERMOHONAN, P.TARIKH_MOHON,  "
					+ " S.NO_KP_BARU, S.NO_KP_LAMA, S.JENIS_KP, S.NO_KP_LAIN, S.ID_SIMATI, "
					+ " S.NAMA_SIMATI, S.TARIKH_MATI, PM.ID_PEMOHON, PM.NAMA_PEMOHON, PM.NO_KP_BARU, "
					+ " PM.NO_KP_LAMA, PM.JENIS_KP, PM.NO_KP_LAIN, PM.ALAMAT_1, PM.ALAMAT_2, PM.ALAMAT_3, "
					+ " PM.POSKOD, PM.BANDAR, N.ID_NEGERI, N.NAMA_NEGERI, D.NAMA_DAERAH, P.SEKSYEN, "
					+ " ST.KETERANGAN, P.ID_STATUS, U.NAMA_PEJABAT, PM.ID_NEGERI,  MOSI.ID_PERMOHONANSIMATI, "
					+ " S.UMUR, S.JANTINA,  PM.UMUR, PM.JANTINA,U.ID_PEJABATJKPTG, P.NO_SUBJAKET,  "
					+ " PM.ALAMAT1_SURAT, PM.ALAMAT2_SURAT, PM.ALAMAT3_SURAT,PM.POSKOD_SURAT, PM.BANDAR_SURAT, PM.ID_NEGERISURAT, PM.ID_BANDAR, U.ALAMAT1, DX.NAMA_DAERAH AS D_P,PM.ID_BANDARSURAT,"
					+ " P.BATAL_KUASA_PENTADBIR, P.LANTIK_PENTADBIR, P.BATAL_P_AMANAH, P.LANTIK_P_AMANAH, P.HARTA_TINGGAL, P.LAIN_TUJUAN, PM.ID_TARAFKPTG,PM.NO_TEL,PM.NO_HP,PM.STATUS_PEMOHON,PM.ID_ARB,S.TARIKH_MATI,U.ID_NEGERI AS ID_NEGERIPEJABAT,PM.ID_SAUDARA,P.NO_PERMOHONAN_ONLINE,PM.EMEL,P.FLAG_PRINT_NILAIAN_HARTA,   "
					//ADD BY PEJE
					+ " S.TARIKH_LAHIR AS TARIKH_LAHIR_SIMATI,PM.nama_pelbagainegara,PM.nama_pelbagainegara_surat "
					+ " FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLRUJNEGERI N, TBLRUJDAERAH D, TBLRUJDAERAH DX, TBLPPKSIMATI S, "
					+ " TBLPPKPEMOHON PM, TBLRUJSTATUS ST, TBLRUJPEJABATJKPTG U,  TBLPPKPERMOHONANSIMATI MOSI, USERS_INTERNAL UR  "
					+ " WHERE F.ID_NEGERI = N.ID_NEGERI(+)  "
					// +" AND N.ID_NEGERI = PM.ID_NEGERISURAT(+)"
					+ " AND P.ID_DAERAHMHN = D.ID_DAERAH(+) "
					+ " AND UR.USER_ID  = '"
					+ userid
					+ "' "
					+ " AND UR.ID_PEJABATJKPTG = U.ID_PEJABATJKPTG "
					+ " AND P.ID_FAIL = F.ID_FAIL"
					// + " AND PM.ID_PERMOHONAN = P.ID_PERMOHONAN(+) "
					+ " AND P.ID_PEMOHON = PM.ID_PEMOHON(+) "
					+ " AND S.ID_SIMATI = MOSI.ID_SIMATI  "
					+ " AND P.ID_PERMOHONAN = MOSI.ID_PERMOHONAN  "
					+ " AND P.ID_STATUS = ST.ID_STATUS(+)  "
					+ " AND D.ID_DAERAH = P.ID_DAERAHMHN  "
					+ " AND U.ID_DAERAH = DX.ID_DAERAH(+) "
					+ " AND P.ID_PERMOHONAN = '" + id + "' ";

			//System.out.println("SQL FAR" + sql);

			myLogger.info("******** SQL SET DATA:" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			
			
			while (rs.next()) {
				h = new Hashtable();
				
				h.put("nama_pelbagainegara",
						rs.getString("nama_pelbagainegara") == null ? "" : rs
								.getString("nama_pelbagainegara"));
				
				h.put("nama_pelbagainegara_surat",
						rs.getString("nama_pelbagainegara_surat") == null ? "" : rs
								.getString("nama_pelbagainegara_surat"));
				
				h.put("id_Permohonansimati",
						rs.getString("id_Permohonansimati") == null ? "" : rs
								.getString("id_Permohonansimati"));
				// h.put("id_Suburusanstatus",
				// rs.getString("id_Suburusanstatus")==null?"":rs.getString("id_Suburusanstatus"));
				// h.put("id_Suburusanstatusfail",
				// rs.getString("id_Suburusanstatusfail")==null?"":rs.getString("id_Suburusanstatusfail"));
				h.put("idFail",rs.getString("id_Fail") == null ? "" : rs.getString("id_Fail"));
				h.put("noFail",rs.getString("no_Fail") == null ? "" : rs.getString("no_Fail"));				
				h.put("no_fail_online",rs.getString("NO_PERMOHONAN_ONLINE") == null ? "" : rs.getString("NO_PERMOHONAN_ONLINE"));
				h.put("idNegeri",rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri")); /**9/1/2020: arief add**/
				h.put("idDaerah",rs.getString("id_Daerah") == null ? "" : rs.getString("id_Daerah"));

				h.put("idPermohonan",rs.getString("id_Permohonan") == null ? "" : rs.getString("id_Permohonan"));

				h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
				h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
				h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));

				h.put("noKpLama",rs.getString("no_Kp_Lama") == null ? "" : rs.getString("no_Kp_Lama"));

				h.put("jenisKp", rs.getString(8) == null ? "" : rs.getString(8));

				h.put("noKpLain",rs.getString("no_Kp_Lain") == null ? "" : rs.getString("no_Kp_Lain"));

				h.put("idSimati",rs.getString("id_Simati") == null ? "" : rs.getString("id_Simati"));

				h.put("namaSimati", rs.getString("nama_Simati") == null ? "": rs.getString("nama_Simati"));
				h.put("tarikhMati", rs.getDate("tarikh_Mati") == null ? "" : sdf.format(rs.getDate("tarikh_Mati")));
				h.put("FLAG_PRINT_NILAIAN_HARTA", rs.getString("FLAG_PRINT_NILAIAN_HARTA") == null ? "" : rs.getString("FLAG_PRINT_NILAIAN_HARTA"));
				//ADD BY PEJE
				h.put("tarikhLahirSimati", rs.getDate("tarikh_lahir_simati") == null ? ""
						: sdf.format(rs.getDate("tarikh_lahir_simati")));
				h.put("idPemohon",
						rs.getString("id_Pemohon") == null ? "" : rs
								.getString("id_Pemohon"));
				h.put("namaPemohon", rs.getString("nama_Pemohon") == null ? ""
						: rs.getString("nama_Pemohon"));
				h.put("noKpBaruPemohon1", rs.getString(15) == null ? "" : rs
						.getString(15).substring(0, 6));

				//myLogger.debug("rs.getString(15):" + rs.getString(15));

				h.put("noKpBaruPemohon2", rs.getString(15) == null ? "" : rs
						.getString(15).substring(6, 8));
				h.put("noKpBaruPemohon3", rs.getString(15) == null ? "" : rs
						.getString(15).substring(8, 12));
				h.put("noKpLamaPemohon",
						rs.getString(16) == null ? "" : rs.getString(16));
				h.put("jenisKpPemohon",
						rs.getString(17) == null ? "" : rs.getString(17));
				h.put("noKpLainPemohon",
						rs.getString(18) == null ? "" : rs.getString(18));

				h.put("alamat1",rs.getString("alamat_1") == null ? "" : rs.getString("alamat_1"));
				//System.out.println("Alamat1 " + rs.getString("alamat_1"));
				//System.out.println("Alamat1dlmFrmPrmhnnSek8DaftarSek8InternalData");
				h.put("alamat2",
						rs.getString("alamat_2") == null ? "" : rs
								.getString("alamat_2"));
				//System.out.println("Alamat2 " + rs.getString("alamat_2"));
				h.put("alamat3",
						rs.getString("alamat_3") == null ? "" : rs
								.getString("alamat_3"));
				h.put("poskod",
						rs.getString("poskod") == null ? "" : rs
								.getString("poskod"));
				h.put("bandar",
						rs.getString("bandar") == null ? "" : rs
								.getString("bandar"));
				h.put("idbandar",
						rs.getString("id_Bandar") == null ? "" : rs
								.getString("id_Bandar"));
				h.put("idbandarsurat",
						rs.getString("id_Bandarsurat") == null ? "" : rs
								.getString("id_Bandarsurat"));
				h.put("idnegeri",
						rs.getString("id_Negeri") == null ? "" : rs
								.getString("id_Negeri"));

				h.put("namanegeri", rs.getString("nama_Negeri") == null ? ""
						: rs.getString("nama_Negeri"));
				h.put("namadaerah", rs.getString("nama_Daerah") == null ? ""
						: rs.getString("nama_Daerah"));
				h.put("seksyen",
						rs.getString("seksyen") == null ? "" : rs
								.getString("seksyen"));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("id_Status",
						rs.getString("id_Status") == null ? "" : rs
								.getString("id_Status"));

				if (rs.getString("ID_NEGERIPEJABAT").equals("7")) {
					h.put("namaPejabat",
							rs.getString("nama_pejabat") == null ? "" : rs
									.getString("nama_pejabat"));
				} else {
					h.put("namaPejabat",
							rs.getString("nama_pejabat") + ","
									+ rs.getString("D_P") == null ? "" : rs
									.getString("nama_pejabat")
									+ ","
									+ rs.getString("D_P"));
				}

				h.put("pmidnegeri",
						rs.getString(31) == null ? "" : rs.getString(31));

				h.put("alamat1surat",
						rs.getString("alamat1_surat") == null ? "" : rs
								.getString("alamat1_surat"));
				h.put("alamat2surat",
						rs.getString("alamat2_surat") == null ? "" : rs
								.getString("alamat2_surat"));
				h.put("alamat3surat",
						rs.getString("alamat3_surat") == null ? "" : rs
								.getString("alamat3_surat"));
				h.put("poskodsurat", rs.getString("poskod_surat") == null ? ""
						: rs.getString("poskod_surat"));
				h.put("bandarsurat", rs.getString("bandar_surat") == null ? ""
						: rs.getString("bandar_surat"));
				h.put("idnegerisurat",
						rs.getString("id_Negerisurat") == null ? "" : rs
								.getString("id_Negerisurat"));

				// h.put("id_Suburusanstatus",
				// rs.getString("id_Suburusanstatus")==null?"":rs.getString("id_Suburusanstatus"));
				// h.put("id_Suburusanstatusfail",
				// rs.getString("id_Suburusanstatusfail")==null?"":rs.getString("id_Suburusanstatusfail"));

				h.put("umursimati",
						rs.getString(33) == null ? "" : rs.getString(33));
				h.put("jantinasimati",
						rs.getString(34) == null ? "" : rs.getString(34));

				h.put("umurpemohon",
						rs.getString(35) == null ? "" : rs.getString(35));
				h.put("jantinapemohon",
						rs.getString(36) == null ? "" : rs.getString(36));

				h.put("no_subjaket", rs.getString("no_subjaket") == null ? ""
						: rs.getString("no_subjaket"));

				h.put("tarikhMohon", rs.getString("tarikh_Mohon") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mohon")));

				h.put("bkp", rs.getString("BATAL_KUASA_PENTADBIR") == null ? ""
						: rs.getString("BATAL_KUASA_PENTADBIR"));
				
				//System.out.println("BATAL_KUASA_PENTADBIR-->" + rs.getString("BATAL_KUASA_PENTADBIR"));
				h.put("lp",
						rs.getString("LANTIK_PENTADBIR") == null ? "" : rs
								.getString("LANTIK_PENTADBIR"));
				h.put("bpa",
						rs.getString("BATAL_P_AMANAH") == null ? "" : rs
								.getString("BATAL_P_AMANAH"));
				h.put("lpa",
						rs.getString("LANTIK_P_AMANAH") == null ? "" : rs
								.getString("LANTIK_P_AMANAH"));
				h.put("ht",
						rs.getString("HARTA_TINGGAL") == null ? "" : rs
								.getString("HARTA_TINGGAL"));
				h.put("lt",
						rs.getString("LAIN_TUJUAN") == null ? "" : rs
								.getString("LAIN_TUJUAN"));

				h.put("lt",
						rs.getString("LAIN_TUJUAN") == null ? "" : rs
								.getString("LAIN_TUJUAN"));
				h.put("taraf_penting",
						rs.getString("ID_TARAFKPTG") == null ? "" : rs
								.getString("ID_TARAFKPTG"));

				h.put("no_tel",
						rs.getString("NO_TEL") == null ? "" : rs
								.getString("NO_TEL"));

				h.put("no_hp",
						rs.getString("NO_HP") == null ? "" : rs
								.getString("NO_HP"));

				h.put("jenis_pej",
						rs.getString("ID_ARB") == null ? "" : rs
								.getString("ID_ARB"));

				h.put("jenis_pemohon",
						rs.getString("STATUS_PEMOHON") == null ? "" : rs
								.getString("STATUS_PEMOHON"));

				h.put("t_mati",
						rs.getString("TARIKH_MATI") == null ? "" : rs
								.getString("TARIKH_MATI"));
				
				h.put("socSaudaraWaris",
						rs.getString("ID_SAUDARA") == null ? "" : rs
								.getString("ID_SAUDARA"));

				if (rs.getString("TARIKH_MATI") != ""
						&& rs.getString("TARIKH_MATI") != null) {
					DateFormat dm = new SimpleDateFormat("dd/MM/yyyy");
					Date tm_s = dm.parse(sdf.format(rs.getDate("TARIKH_MATI")));
					Date tm_t = dm.parse("01/11/1991");

					if (tm_s.before(tm_t)) {
						h.put("jpphlepas", "no");
					} else if (tm_s.after(tm_t)) {
						h.put("jpphlepas", "yes");
					} else {
						h.put("jpphlepas", "no");
					}
				} else {
					h.put("jpphlepas", "no");
				}

				// " P.BATAL_KUASA_PENTADBIR, P.LANTIK_PENTADBIR, P.BATAL_P_AMANAH, P.LANTIK_P_AMANAH, P.HARTA_TINGGAL, P.LAIN_TUJUAN  "

				// //System.out.println("Today = " +
				// h.get("tarikhMohon").toString());

				if (rs.getString("tarikh_Mohon") != ""
						&& rs.getString("tarikh_Mohon") != null) {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date t_m = df.parse(sdf.format(rs.getDate("tarikh_Mohon")));
					Date sep_m = df.parse("01/09/2009");
					// System.out.println("Today = "
					// + h.get("tarikhMohon").toString());

					if (t_m.before(sep_m)) {
						h.put("lepassatusept", "yes");
					} else if (t_m.after(sep_m)) {
						h.put("lepassatusept", "no");
					} else {
						h.put("lepassatusept", "no");
					}
				}

				h.put("emel_pemohon",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));
				
				// System.out.println("sql data-->" + h);
				list.addElement(h);
			}
			
			//System.out.println("sql data-->" + list.size() );
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	 
	public Vector setDataPPSPP(String id, String userid) throws Exception {
		Db db = null;
		list.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			System.out.println("Keluar sini3");
			sql = "SELECT DISTINCT F.ID_FAIL, F.NO_FAIL, D.ID_DAERAH, P.ID_PERMOHONAN, P.TARIKH_MOHON,  "
					+ " S.NO_KP_BARU, S.NO_KP_LAMA, S.JENIS_KP, S.NO_KP_LAIN, S.ID_SIMATI, "
					+ " S.NAMA_SIMATI, S.TARIKH_MATI, PM.ID_PEMOHON, PM.NAMA_PEMOHON, PM.NO_KP_BARU, "
					+ " PM.NO_KP_LAMA, PM.JENIS_KP, PM.NO_KP_LAIN, PM.ALAMAT_1, PM.ALAMAT_2, PM.ALAMAT_3, "
					+ " PM.POSKOD, PM.BANDAR, N.ID_NEGERI, N.NAMA_NEGERI, D.NAMA_DAERAH, P.SEKSYEN, "
					+ " ST.KETERANGAN, P.ID_STATUS, U.NAMA_PEJABAT, PM.ID_NEGERI,  MOSI.ID_PERMOHONANSIMATI, "
					+ " S.UMUR, S.JANTINA,  PM.UMUR, PM.JANTINA,U.ID_PEJABATJKPTG, P.NO_SUBJAKET,  "
					+ " PM.ALAMAT1_SURAT, PM.ALAMAT2_SURAT, PM.ALAMAT3_SURAT,PM.POSKOD_SURAT, PM.BANDAR_SURAT, PM.ID_NEGERISURAT, PM.ID_BANDAR, U.ALAMAT1, DX.NAMA_DAERAH AS D_P,PM.ID_BANDARSURAT,"
					+ " P.BATAL_KUASA_PENTADBIR, P.LANTIK_PENTADBIR, P.BATAL_P_AMANAH, P.LANTIK_P_AMANAH, P.HARTA_TINGGAL, P.LAIN_TUJUAN, PM.ID_TARAFKPTG,PM.NO_TEL,PM.NO_HP,PM.STATUS_PEMOHON,PM.ID_ARB,S.TARIKH_MATI,U.ID_NEGERI AS ID_NEGERIPEJABAT,PM.ID_SAUDARA,P.NO_PERMOHONAN_ONLINE,PM.EMEL,P.FLAG_PRINT_NILAIAN_HARTA,   "
					//ADD BY PEJE
					+ " S.TARIKH_LAHIR AS TARIKH_LAHIR_SIMATI,PM.nama_pelbagainegara,PM.nama_pelbagainegara_surat "
					+ " FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLRUJNEGERI N, TBLRUJDAERAH D, TBLRUJDAERAH DX, TBLPPKSIMATI S, "
					+ " TBLPPKPEMOHON PM, TBLRUJSTATUS ST, TBLRUJPEJABATJKPTG U,  TBLPPKPERMOHONANSIMATI MOSI, USERS_INTERNAL UR  "
					+ " WHERE F.ID_NEGERI = N.ID_NEGERI(+)  "
					// +" AND N.ID_NEGERI = PM.ID_NEGERISURAT(+)"
					+ " AND P.ID_DAERAHMHN = D.ID_DAERAH(+) "
					+ " AND UR.USER_ID  = '"
					+ userid
					+ "' "
					+ " AND UR.ID_PEJABATJKPTG = U.ID_PEJABATJKPTG "
					+ " AND P.ID_FAIL = F.ID_FAIL"
					// + " AND PM.ID_PERMOHONAN = P.ID_PERMOHONAN(+) "
					+ " AND P.ID_PEMOHON = PM.ID_PEMOHON(+) "
					+ " AND S.ID_SIMATI = MOSI.ID_SIMATI  "
					+ " AND P.ID_PERMOHONAN = MOSI.ID_PERMOHONAN  "
					+ " AND P.ID_STATUS = ST.ID_STATUS(+)  "
					+ " AND D.ID_DAERAH = P.ID_DAERAHMHN  "
					+ " AND U.ID_DAERAH = DX.ID_DAERAH(+) "
					+ " AND P.ID_PERMOHONAN = '" + id + "' ";

			//System.out.println("SQL FAR" + sql);

			myLogger.info("******** SQL SET DATA:" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("FLAG_PRINT_NILAIAN_HARTA", rs.getString("FLAG_PRINT_NILAIAN_HARTA") == null ? ""
						: rs.getString("FLAG_PRINT_NILAIAN_HARTA"));
	
				
				// System.out.println("sql data-->" + h);
				list.addElement(h);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector setDataPPSPPSek17(String id, String userid) throws Exception {
		Db db = null;
		list.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			System.out.println("Keluar sini3");
			sql = "SELECT DISTINCT F.ID_FAIL, F.NO_FAIL, D.ID_DAERAH, P.ID_PERMOHONAN, P.TARIKH_MOHON,  "
					+ " S.NO_KP_BARU, S.NO_KP_LAMA, S.JENIS_KP, S.NO_KP_LAIN, S.ID_SIMATI, "
					+ " S.NAMA_SIMATI, S.TARIKH_MATI, PM.ID_PEMOHON, PM.NAMA_PEMOHON, PM.NO_KP_BARU, "
					+ " PM.NO_KP_LAMA, PM.JENIS_KP, PM.NO_KP_LAIN, PM.ALAMAT_1, PM.ALAMAT_2, PM.ALAMAT_3, "
					+ " PM.POSKOD, PM.BANDAR, N.ID_NEGERI, N.NAMA_NEGERI, D.NAMA_DAERAH, P.SEKSYEN, "
					+ " ST.KETERANGAN, P.ID_STATUS, U.NAMA_PEJABAT, PM.ID_NEGERI,  MOSI.ID_PERMOHONANSIMATI, "
					+ " S.UMUR, S.JANTINA,  PM.UMUR, PM.JANTINA,U.ID_PEJABATJKPTG, P.NO_SUBJAKET,  "
					+ " PM.ALAMAT1_SURAT, PM.ALAMAT2_SURAT, PM.ALAMAT3_SURAT,PM.POSKOD_SURAT, PM.BANDAR_SURAT, PM.ID_NEGERISURAT, PM.ID_BANDAR, U.ALAMAT1, DX.NAMA_DAERAH AS D_P,PM.ID_BANDARSURAT,"
					+ " P.BATAL_KUASA_PENTADBIR, P.LANTIK_PENTADBIR, P.BATAL_P_AMANAH, P.LANTIK_P_AMANAH, P.HARTA_TINGGAL, P.LAIN_TUJUAN, PM.ID_TARAFKPTG,PM.NO_TEL,PM.NO_HP,PM.STATUS_PEMOHON,PM.ID_ARB,S.TARIKH_MATI,U.ID_NEGERI AS ID_NEGERIPEJABAT,PM.ID_SAUDARA,P.NO_PERMOHONAN_ONLINE,PM.EMEL,P.FLAG_PRINT_NILAIAN_HARTA,   "
					//ADD BY PEJE
					+ " S.TARIKH_LAHIR AS TARIKH_LAHIR_SIMATI,PM.nama_pelbagainegara,PM.nama_pelbagainegara_surat "
					+ " FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLRUJNEGERI N, TBLRUJDAERAH D, TBLRUJDAERAH DX, TBLPPKSIMATI S, "
					+ " TBLPPKPEMOHON PM, TBLRUJSTATUS ST, TBLRUJPEJABATJKPTG U,  TBLPPKPERMOHONANSIMATI MOSI, USERS_INTERNAL UR  "
					+ " WHERE F.ID_NEGERI = N.ID_NEGERI(+)  "
					// +" AND N.ID_NEGERI = PM.ID_NEGERISURAT(+)"
					+ " AND P.ID_DAERAHMHN = D.ID_DAERAH(+) "
					+ " AND UR.USER_ID  = '"
					+ userid
					+ "' "
					+ " AND UR.ID_PEJABATJKPTG = U.ID_PEJABATJKPTG "
					+ " AND P.ID_FAIL = F.ID_FAIL"
					// + " AND PM.ID_PERMOHONAN = P.ID_PERMOHONAN(+) "
					+ " AND P.ID_PEMOHON = PM.ID_PEMOHON(+) "
					+ " AND S.ID_SIMATI = MOSI.ID_SIMATI  "
					+ " AND P.ID_PERMOHONAN = MOSI.ID_PERMOHONAN  "
					+ " AND P.ID_STATUS = ST.ID_STATUS(+)  "
					+ " AND D.ID_DAERAH = P.ID_DAERAHMHN  "
					+ " AND U.ID_DAERAH = DX.ID_DAERAH(+) "
					+ " AND P.HARTA_TINGGAL = 'Y' "
					+ " AND P.ID_PERMOHONAN = '" + id + "' ";

			//System.out.println("SQL FAR" + sql);

			myLogger.info("******** SQL SET DATA:" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("FLAG_PRINT_NILAIAN_HARTA", rs.getString("FLAG_PRINT_NILAIAN_HARTA") == null ? ""
						: rs.getString("FLAG_PRINT_NILAIAN_HARTA"));
	
				
				// System.out.println("sql data-->" + h);
				list.addElement(h);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	
	public Vector setData17_online(String id, String userid) throws Exception {
		Db db = null;
		list.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			System.out.println("Keluar sini4");
			sql = "SELECT DISTINCT F.ID_FAIL, F.NO_FAIL, D.ID_DAERAH, P.ID_PERMOHONAN, P.TARIKH_MOHON, "
					+ " S.NO_KP_BARU, S.NO_KP_LAMA, S.JENIS_KP AS JENISKP_SIMATI, S.NO_KP_LAIN, S.ID_SIMATI, "
					+ " S.NAMA_SIMATI, S.TARIKH_MATI, PM.ID_PEMOHON, PM.NAMA_PEMOHON, PM.NO_KP_BARU AS NO_KP_BARU_PM, "
					+ " PM.NO_KP_LAMA AS NO_KP_LAMA_PM, PM.JENIS_KP AS JENIS_KP_PM, PM.NO_KP_LAIN AS NO_KP_LAIN_PM, " +
							"PM.ALAMAT_1, PM.ALAMAT_2, PM.ALAMAT_3, "
					+ " PM.POSKOD, PM.BANDAR, N.ID_NEGERI, N.NAMA_NEGERI, D.NAMA_DAERAH, P.SEKSYEN, "
					+ " ST.KETERANGAN, P.ID_STATUS," +
						//	" U.NAMA_PEJABAT, " +
							"PM.ID_NEGERI AS ID_NEGERI_PEMOHON,  MOSI.ID_PERMOHONANSIMATI, "
					+ " S.UMUR AS UMUR_SIMATI, S.JANTINA AS JANTINA_SIMATI,  PM.UMUR AS UMUR_PEMOHON, PM.JANTINA AS JANTINA_PEMOHON," +
							//"U.ID_PEJABATJKPTG, " +
							"P.NO_SUBJAKET,  "
					+ " PM.ALAMAT1_SURAT, PM.ALAMAT2_SURAT, PM.ALAMAT3_SURAT,PM.POSKOD_SURAT, PM.BANDAR_SURAT, " +
							"PM.ID_NEGERISURAT AS ID_NEGERISURAT_PEMOHON, PM.ID_BANDAR AS ID_BANDAR_PEMOHON," +
						//	" U.ALAMAT1," +
						//	" DX.NAMA_DAERAH AS D_P," +
							"PM.ID_BANDARSURAT,"
					+ " P.BATAL_KUASA_PENTADBIR, P.LANTIK_PENTADBIR, P.BATAL_P_AMANAH, P.LANTIK_P_AMANAH, " +
							"P.HARTA_TINGGAL, P.LAIN_TUJUAN, PM.ID_TARAFKPTG,PM.NO_TEL,PM.NO_HP,PM.STATUS_PEMOHON," +
							"PM.ID_ARB,S.TARIKH_MATI," +
							//"U.ID_NEGERI AS ID_NEGERIPEJABAT," +
							"PM.ID_SAUDARA,P.NO_PERMOHONAN_ONLINE,P.ID_PERMOHONANTERDAHULU, PM.nama_pelbagainegara   "
					+ " FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLRUJNEGERI N, TBLRUJDAERAH D, " +
							//"TBLRUJDAERAH DX, " +
							"TBLPPKSIMATI S, "
					+ " TBLPPKPEMOHON PM, TBLRUJSTATUS ST, " +
							//"TBLRUJPEJABATJKPTG U, " +
							" TBLPPKPERMOHONANSIMATI MOSI" 
							//", USERS_INTERNAL UR  "
					+ " WHERE F.ID_NEGERI = N.ID_NEGERI(+)  "
					// +" AND N.ID_NEGERI = PM.ID_NEGERISURAT(+)"
					+ " AND P.ID_DAERAHMHN = D.ID_DAERAH(+) "
					//+ " AND UR.USER_ID  = '"+ userid+ "' "
					/*+ " AND UR.ID_PEJABATJKPTG = U.ID_PEJABATJKPTG "*/
					+ " AND P.ID_FAIL = F.ID_FAIL"
					// + " AND PM.ID_PERMOHONAN = P.ID_PERMOHONAN(+) "
					+ " AND P.ID_PEMOHON = PM.ID_PEMOHON(+) "
					+ " AND S.ID_SIMATI = MOSI.ID_SIMATI  "
					+ " AND P.ID_PERMOHONAN = MOSI.ID_PERMOHONAN  "
					+ " AND P.ID_STATUS = ST.ID_STATUS(+)  "
					+ " AND D.ID_DAERAH = P.ID_DAERAHMHN  "
					/*+ " AND U.ID_DAERAH = DX.ID_DAERAH(+) "*/
					+ " AND P.ID_PERMOHONAN = '" + id + "' ";
			
			/*
			SELECT DISTINCT f.id_fail, f.no_fail, d.id_daerah, p.id_permohonan,
                p.tarikh_mohon, s.no_kp_baru, s.no_kp_lama, s.jenis_kp,
                s.no_kp_lain, s.id_simati, s.nama_simati, s.tarikh_mati,
                pm.id_pemohon, pm.nama_pemohon, pm.no_kp_baru, pm.no_kp_lama,
                pm.jenis_kp, pm.no_kp_lain, pm.alamat_1, pm.alamat_2,
                pm.alamat_3, pm.poskod, pm.bandar, n.id_negeri, n.nama_negeri,
                d.nama_daerah, p.seksyen, st.keterangan, p.id_status,
               -- u.nama_pejabat, 
                pm.id_negeri, mosi.id_permohonansimati,
                s.umur, s.jantina, pm.umur, pm.jantina, 
                --u.id_pejabatjkptg,
                p.no_subjaket, pm.alamat1_surat, pm.alamat2_surat,
                pm.alamat3_surat, pm.poskod_surat, pm.bandar_surat,
                pm.id_negerisurat, pm.id_bandar, 
                --u.alamat1,
                --dx.nama_daerah AS d_p, 
                pm.id_bandarsurat,
                p.batal_kuasa_pentadbir, p.lantik_pentadbir, p.batal_p_amanah,
                p.lantik_p_amanah, p.harta_tinggal, p.lain_tujuan,
                pm.id_tarafkptg, pm.no_tel, pm.no_hp, pm.status_pemohon,
                pm.id_arb, s.tarikh_mati,
                -- u.id_negeri AS id_negeripejabat,
                pm.id_saudara, p.no_permohonan_online
           FROM tblpfdfail f,
                tblppkpermohonan p,
                tblrujnegeri n,
                tblrujdaerah d,
                --tblrujdaerah dx,
                tblppksimati s,
                tblppkpemohon pm,
                tblrujstatus st,
                --tblrujpejabatjkptg u,
                tblppkpermohonansimati mosi
                --,users_internal ur
          WHERE f.id_negeri = n.id_negeri(+)
            AND p.id_daerahmhn = d.id_daerah(+)
             --AND ur.user_id = '16114413'
           -- AND ur.id_pejabatjkptg = u.id_pejabatjkptg
            AND p.id_fail = f.id_fail
            AND p.id_pemohon = pm.id_pemohon(+)
            AND s.id_simati = mosi.id_simati
            AND p.id_permohonan = mosi.id_permohonan
            AND p.id_status = st.id_status(+)
            AND d.id_daerah = p.id_daerahmhn
           -- AND u.id_daerah = dx.id_daerah(+)
            AND p.id_permohonan = '1611551698'			 
			 */
			
			
			

			System.out.println("SQL FAR ONLINE" + sql);

			myLogger.info("******** SQL SET DATA ONLINE:" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("nama_pelbagainegara",
						rs.getString("nama_pelbagainegara") == null ? "" : rs
								.getString("nama_pelbagainegara"));
				
				
				h.put("id_Permohonansimati",
						rs.getString("id_Permohonansimati") == null ? "" : rs
								.getString("id_Permohonansimati"));
				// h.put("id_Suburusanstatus",
				// rs.getString("id_Suburusanstatus")==null?"":rs.getString("id_Suburusanstatus"));
				// h.put("id_Suburusanstatusfail",
				// rs.getString("id_Suburusanstatusfail")==null?"":rs.getString("id_Suburusanstatusfail"));
				h.put("idFail",
						rs.getString("id_Fail") == null ? "" : rs
								.getString("id_Fail"));

				h.put("noFail",
						rs.getString("no_Fail") == null ? "" : rs
								.getString("no_Fail"));
				
				
				h.put("no_fail_online",
						rs.getString("NO_PERMOHONAN_ONLINE") == null ? "" : rs
								.getString("NO_PERMOHONAN_ONLINE"));
				
				// //h.put("noFailBaru", "");

				h.put("idDaerah",
						rs.getString("id_Daerah") == null ? "" : rs
								.getString("id_Daerah"));
				h.put("idPermohonan",
						rs.getString("id_Permohonan") == null ? "" : rs
								.getString("id_Permohonan"));
				h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("noKpLama",
						rs.getString("no_Kp_Lama") == null ? "" : rs
								.getString("no_Kp_Lama"));
				h.put("jenisKp", rs.getString("JENISKP_SIMATI") == null ? "" : rs.getString("JENISKP_SIMATI"));
				h.put("noKpLain",
						rs.getString("no_Kp_Lain") == null ? "" : rs
								.getString("no_Kp_Lain"));
				h.put("idSimati",
						rs.getString("id_Simati") == null ? "" : rs
								.getString("id_Simati"));
				System.out.println("Keluar sini4a");
				h.put("namaSimati", rs.getString("nama_Simati") == null ? ""
						: rs.getString("nama_Simati"));
				h.put("tarikhMati", rs.getDate("tarikh_Mati") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mati")));
				h.put("idPemohon",
						rs.getString("id_Pemohon") == null ? "" : rs
								.getString("id_Pemohon"));
				h.put("namaPemohon", rs.getString("nama_Pemohon") == null ? ""
						: rs.getString("nama_Pemohon"));
				h.put("noKpBaruPemohon1", rs.getString("NO_KP_BARU_PM") == null ? "" : rs
						.getString("NO_KP_BARU_PM").substring(0, 6));

				//myLogger.debug("rs.getString(15):" + rs.getString(15));

				h.put("noKpBaruPemohon2", rs.getString("NO_KP_BARU_PM") == null ? "" : rs
						.getString("NO_KP_BARU_PM").substring(6, 8));
				h.put("noKpBaruPemohon3", rs.getString("NO_KP_BARU_PM") == null ? "" : rs
						.getString("NO_KP_BARU_PM").substring(8, 12));
				
				
				//PM.NO_KP_LAMA AS NO_KP_LAMA_PM, PM.JENIS_KP AS JENIS_KP_PM, PM.NO_KP_LAIN AS NO_KP_LAIN_PM
				
				
				h.put("noKpLamaPemohon",
						rs.getString("NO_KP_LAMA_PM") == null ? "" : rs.getString("NO_KP_LAMA_PM"));
				h.put("jenisKpPemohon",
						rs.getString("JENIS_KP_PM") == null ? "" : rs.getString("JENIS_KP_PM"));
				h.put("noKpLainPemohon",
						rs.getString("NO_KP_LAIN_PM") == null ? "" : rs.getString("NO_KP_LAIN_PM"));

				h.put("alamat1",
						rs.getString("alamat_1") == null ? "" : rs
								.getString("alamat_1"));
				h.put("alamat2",
						rs.getString("alamat_2") == null ? "" : rs
								.getString("alamat_2"));
				h.put("alamat3",
						rs.getString("alamat_3") == null ? "" : rs
								.getString("alamat_3"));
				h.put("poskod",
						rs.getString("poskod") == null ? "" : rs
								.getString("poskod"));
				h.put("bandar",
						rs.getString("bandar") == null ? "" : rs
								.getString("bandar"));
				h.put("idbandar",
						rs.getString("ID_BANDAR_PEMOHON") == null ? "" : rs
								.getString("ID_BANDAR_PEMOHON"));
				h.put("idbandarsurat",
						rs.getString("id_Bandarsurat") == null ? "" : rs
								.getString("id_Bandarsurat"));
				h.put("idnegeri",
						rs.getString("id_Negeri") == null ? "" : rs
								.getString("id_Negeri"));

				h.put("namanegeri", rs.getString("nama_Negeri") == null ? ""
						: rs.getString("nama_Negeri"));
				h.put("namadaerah", rs.getString("nama_Daerah") == null ? ""
						: rs.getString("nama_Daerah"));
				h.put("seksyen",
						rs.getString("seksyen") == null ? "" : rs
								.getString("seksyen"));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("id_Status",
						rs.getString("id_Status") == null ? "" : rs
								.getString("id_Status"));

				/*if (rs.getString("ID_NEGERIPEJABAT").equals("7")) {
					h.put("namaPejabat",
							rs.getString("nama_pejabat") == null ? "" : rs
									.getString("nama_pejabat"));
				} else {
					h.put("namaPejabat",
							rs.getString("nama_pejabat") + ","
									+ rs.getString("D_P") == null ? "" : rs
									.getString("nama_pejabat")
									+ ","
									+ rs.getString("D_P"));
				}*/

				h.put("pmidnegeri",
						rs.getString("ID_NEGERI_PEMOHON") == null ? "" : rs.getString("ID_NEGERI_PEMOHON"));

				h.put("alamat1surat",
						rs.getString("alamat1_surat") == null ? "" : rs
								.getString("alamat1_surat"));
				h.put("alamat2surat",
						rs.getString("alamat2_surat") == null ? "" : rs
								.getString("alamat2_surat"));
				h.put("alamat3surat",
						rs.getString("alamat3_surat") == null ? "" : rs
								.getString("alamat3_surat"));
				h.put("poskodsurat", rs.getString("poskod_surat") == null ? ""
						: rs.getString("poskod_surat"));
				h.put("bandarsurat", rs.getString("bandar_surat") == null ? ""
						: rs.getString("bandar_surat"));
				h.put("idnegerisurat",
						rs.getString("ID_NEGERISURAT_PEMOHON") == null ? "" : rs
								.getString("ID_NEGERISURAT_PEMOHON"));
				
				h.put("id_permohonanterdahulu",
						rs.getString("ID_PERMOHONANTERDAHULU") == null ? "" : rs
								.getString("ID_PERMOHONANTERDAHULU"));
				
				
				
				

				// h.put("id_Suburusanstatus",
				// rs.getString("id_Suburusanstatus")==null?"":rs.getString("id_Suburusanstatus"));
				// h.put("id_Suburusanstatusfail",
				// rs.getString("id_Suburusanstatusfail")==null?"":rs.getString("id_Suburusanstatusfail"));

				h.put("umursimati",
						rs.getString("UMUR_SIMATI") == null ? "" : rs.getString("UMUR_SIMATI"));
				h.put("jantinasimati",
						rs.getString("JANTINA_SIMATI") == null ? "" : rs.getString("JANTINA_SIMATI"));

				h.put("umurpemohon",
						rs.getString("UMUR_PEMOHON") == null ? "" : rs.getString("UMUR_PEMOHON"));
				h.put("jantinapemohon",
						rs.getString("JANTINA_PEMOHON") == null ? "" : rs.getString("JANTINA_PEMOHON"));

				h.put("no_subjaket", rs.getString("no_subjaket") == null ? ""
						: rs.getString("no_subjaket"));

				h.put("tarikhMohon", rs.getString("tarikh_Mohon") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mohon")));

				h.put("bkp", rs.getString("BATAL_KUASA_PENTADBIR") == null ? ""
						: rs.getString("BATAL_KUASA_PENTADBIR"));
				h.put("lp",
						rs.getString("LANTIK_PENTADBIR") == null ? "" : rs
								.getString("LANTIK_PENTADBIR"));
				h.put("bpa",
						rs.getString("BATAL_P_AMANAH") == null ? "" : rs
								.getString("BATAL_P_AMANAH"));
				h.put("lpa",
						rs.getString("LANTIK_P_AMANAH") == null ? "" : rs
								.getString("LANTIK_P_AMANAH"));
				h.put("ht",
						rs.getString("HARTA_TINGGAL") == null ? "" : rs
								.getString("HARTA_TINGGAL"));
				h.put("lt",
						rs.getString("LAIN_TUJUAN") == null ? "" : rs
								.getString("LAIN_TUJUAN"));

				
				h.put("taraf_penting",
						rs.getString("ID_TARAFKPTG") == null ? "" : rs
								.getString("ID_TARAFKPTG"));

				h.put("no_tel",
						rs.getString("NO_TEL") == null ? "" : rs
								.getString("NO_TEL"));

				h.put("no_hp",
						rs.getString("NO_HP") == null ? "" : rs
								.getString("NO_HP"));

				h.put("jenis_pej",
						rs.getString("ID_ARB") == null ? "" : rs
								.getString("ID_ARB"));

				h.put("jenis_pemohon",
						rs.getString("STATUS_PEMOHON") == null ? "" : rs
								.getString("STATUS_PEMOHON"));

				h.put("t_mati",
						rs.getString("TARIKH_MATI") == null ? "" : rs
								.getString("TARIKH_MATI"));
				
				h.put("socSaudaraWaris",
						rs.getString("ID_SAUDARA") == null ? "" : rs
								.getString("ID_SAUDARA"));

				if (rs.getString("TARIKH_MATI") != ""
						&& rs.getString("TARIKH_MATI") != null) {
					DateFormat dm = new SimpleDateFormat("dd/MM/yyyy");
					Date tm_s = dm.parse(sdf.format(rs.getDate("TARIKH_MATI")));
					Date tm_t = dm.parse("01/11/1991");

					if (tm_s.before(tm_t)) {
						h.put("jpphlepas", "no");
					} else if (tm_s.after(tm_t)) {
						h.put("jpphlepas", "yes");
					} else {
						h.put("jpphlepas", "no");
					}
				} else {
					h.put("jpphlepas", "no");
				}

				// " P.BATAL_KUASA_PENTADBIR, P.LANTIK_PENTADBIR, P.BATAL_P_AMANAH, P.LANTIK_P_AMANAH, P.HARTA_TINGGAL, P.LAIN_TUJUAN  "

				// //System.out.println("Today = " +
				// h.get("tarikhMohon").toString());

				if (rs.getString("tarikh_Mohon") != ""
						&& rs.getString("tarikh_Mohon") != null) {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date t_m = df.parse(sdf.format(rs.getDate("tarikh_Mohon")));
					Date sep_m = df.parse("01/09/2009");
					// System.out.println("Today = "
					// + h.get("tarikhMohon").toString());

					if (t_m.before(sep_m)) {
						h.put("lepassatusept", "yes");
					} else if (t_m.after(sep_m)) {
						h.put("lepassatusept", "no");
					} else {
						h.put("lepassatusept", "no");
					}
				}

				// System.out.println("sql data-->" + h);
				list.addElement(h);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	private Vector listb = new Vector();

	public Vector setDataBaru(String id, String userid) throws Exception {
		Db db = null;
		listb.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			System.out.println("Keluar sini5");
			sql = "SELECT DISTINCT F.ID_FAIL, F.NO_FAIL, D.ID_DAERAH, P.ID_PERMOHONAN, P.TARIKH_MOHON, "
					+ " S.NO_KP_BARU, S.NO_KP_LAMA, S.JENIS_KP, S.NO_KP_LAIN, S.ID_SIMATI, "
					+ " S.NAMA_SIMATI, S.TARIKH_MATI, PM.ID_PEMOHON, PM.NAMA_PEMOHON, PM.NO_KP_BARU, "
					+ " PM.NO_KP_LAMA, PM.JENIS_KP, PM.NO_KP_LAIN, PM.ALAMAT_1, PM.ALAMAT_2, PM.ALAMAT_3, "
					+ " PM.POSKOD, PM.BANDAR, N.ID_NEGERI, N.NAMA_NEGERI, D.NAMA_DAERAH, P.SEKSYEN, "
					+ " ST.KETERANGAN, P.ID_STATUS, U.NAMA_PEJABAT, PM.ID_NEGERI,  MOSI.ID_PERMOHONANSIMATI, "
					+ " S.UMUR, S.JANTINA,  PM.UMUR, PM.JANTINA,U.ID_PEJABATJKPTG, P.NO_SUBJAKET,  "
					+ " PM.ALAMAT1_SURAT, PM.ALAMAT2_SURAT, PM.ALAMAT3_SURAT,PM.POSKOD_SURAT, PM.BANDAR_SURAT, PM.ID_NEGERISURAT, PM.ID_BANDAR, U.ALAMAT1,"
					+ " DX.NAMA_DAERAH AS D_P,P.ID_PERMOHONANTERDAHULU, P.BATAL_KUASA_PENTADBIR,P.LANTIK_PENTADBIR,P.BATAL_P_AMANAH,P.LANTIK_P_AMANAH,P.HARTA_TINGGAL"
					+ " FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLRUJNEGERI N, TBLRUJDAERAH D, TBLRUJDAERAH DX, TBLPPKSIMATI S, "
					+ " TBLPPKPEMOHON PM, TBLRUJSTATUS ST, TBLRUJPEJABATJKPTG U,  TBLPPKPERMOHONANSIMATI MOSI, USERS_INTERNAL UR  "
					+ " WHERE F.ID_NEGERI = N.ID_NEGERI(+)  "
					// +" AND N.ID_NEGERI = PM.ID_NEGERISURAT(+)"
					+ " AND P.ID_DAERAHMHN = D.ID_DAERAH(+) "
					+ " AND UR.USER_ID  = '"
					+ userid
					+ "' "
					+ " AND UR.ID_PEJABATJKPTG = U.ID_PEJABATJKPTG "
					+ " AND P.ID_FAIL = F.ID_FAIL"
					// + " AND PM.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ " AND P.ID_PEMOHON = PM.ID_PEMOHON(+) "
					+ " AND S.ID_SIMATI = MOSI.ID_SIMATI  "
					+ " AND P.ID_PERMOHONAN = MOSI.ID_PERMOHONAN  "
					+ " AND ST.ID_STATUS = P.ID_STATUS  "
					+ " AND D.ID_DAERAH = P.ID_DAERAHMHN  "
					+ " AND U.ID_DAERAH = DX.ID_DAERAH(+) "
					+ " AND P.ID_PERMOHONAN = '" + id + "' ";

				myLogger.info("setDataBaru : "+sql);
			// //System.out.println("SQLXXXXXX BARU" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_Permohonansimati",
						rs.getString("id_Permohonansimati") == null ? "" : rs
								.getString("id_Permohonansimati"));
				h.put("idFail",
						rs.getString("id_Fail") == null ? "" : rs
								.getString("id_Fail"));

				h.put("noFail",
						rs.getString("no_Fail") == null ? "" : rs
								.getString("no_Fail"));

				h.put("idDaerah",
						rs.getString("id_Daerah") == null ? "" : rs
								.getString("id_Daerah"));
				h.put("idPermohonan",
						rs.getString("id_Permohonan") == null ? "" : rs
								.getString("id_Permohonan"));
				h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("noKpLama",
						rs.getString("no_Kp_Lama") == null ? "" : rs
								.getString("no_Kp_Lama"));
				h.put("jenisKp", rs.getString(8) == null ? "" : rs.getString(8));
				h.put("noKpLain",
						rs.getString("no_Kp_Lain") == null ? "" : rs
								.getString("no_Kp_Lain"));
				h.put("idSimati",
						rs.getString("id_Simati") == null ? "" : rs
								.getString("id_Simati"));
				System.out.println("Keluar sini5a");
				h.put("namaSimati", rs.getString("nama_Simati") == null ? ""
						: rs.getString("nama_Simati"));
				h.put("tarikhMati", rs.getDate("tarikh_Mati") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mati")));
				h.put("idPemohon",
						rs.getString("id_Pemohon") == null ? "" : rs
								.getString("id_Pemohon"));
				h.put("namaPemohon", rs.getString("nama_Pemohon") == null ? ""
						: rs.getString("nama_Pemohon"));
				h.put("noKpBaruPemohon1", rs.getString(15) == null ? "" : rs
						.getString(15).substring(0, 6));
				h.put("noKpBaruPemohon2", rs.getString(15) == null ? "" : rs
						.getString(15).substring(6, 8));
				h.put("noKpBaruPemohon3", rs.getString(15) == null ? "" : rs
						.getString(15).substring(8, 12));
				h.put("noKpLamaPemohon",
						rs.getString(16) == null ? "" : rs.getString(16));
				h.put("jenisKpPemohon",
						rs.getString(17) == null ? "" : rs.getString(17));
				h.put("noKpLainPemohon",
						rs.getString(18) == null ? "" : rs.getString(18));

				h.put("alamat1",
						rs.getString("alamat_1") == null ? "" : rs
								.getString("alamat_1"));
				h.put("alamat2",
						rs.getString("alamat_2") == null ? "" : rs
								.getString("alamat_2"));
				h.put("alamat3",
						rs.getString("alamat_3") == null ? "" : rs
								.getString("alamat_3"));
				h.put("poskod",
						rs.getString("poskod") == null ? "" : rs
								.getString("poskod"));
				h.put("bandar",
						rs.getString("bandar") == null ? "" : rs
								.getString("bandar"));
				h.put("idbandar",
						rs.getString("id_Bandar") == null ? "" : rs
								.getString("id_Bandar"));
				h.put("idnegeri",
						rs.getString("id_Negeri") == null ? "" : rs
								.getString("id_Negeri"));

				h.put("namanegeri", rs.getString("nama_Negeri") == null ? ""
						: rs.getString("nama_Negeri"));
				h.put("namadaerah", rs.getString("nama_Daerah") == null ? ""
						: rs.getString("nama_Daerah"));
				h.put("seksyen",
						rs.getString("seksyen") == null ? "" : rs
								.getString("seksyen"));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("id_Status",
						rs.getString("id_Status") == null ? "" : rs
								.getString("id_Status"));

				h.put("namaPejabat",
						rs.getString("nama_pejabat") + ","
								+ rs.getString("D_P") == null ? "" : rs
								.getString("nama_pejabat")
								+ ","
								+ rs.getString("D_P"));

				h.put("pmidnegeri",
						rs.getString(31) == null ? "" : rs.getString(31));

				h.put("alamat1surat",
						rs.getString("alamat1_surat") == null ? "" : rs
								.getString("alamat1_surat"));
				h.put("alamat2surat",
						rs.getString("alamat2_surat") == null ? "" : rs
								.getString("alamat2_surat"));
				h.put("alamat3surat",
						rs.getString("alamat3_surat") == null ? "" : rs
								.getString("alamat3_surat"));
				h.put("poskodsurat", rs.getString("poskod_surat") == null ? ""
						: rs.getString("poskod_surat"));
				h.put("bandarsurat", rs.getString("bandar_surat") == null ? ""
						: rs.getString("bandar_surat"));
				h.put("idnegerisurat",
						rs.getString("id_Negerisurat") == null ? "" : rs
								.getString("id_Negerisurat"));

				// h.put("id_Suburusanstatus",
				// rs.getString("id_Suburusanstatus")==null?"":rs.getString("id_Suburusanstatus"));
				// h.put("id_Suburusanstatusfail",
				// rs.getString("id_Suburusanstatusfail")==null?"":rs.getString("id_Suburusanstatusfail"));

				h.put("umursimati",
						rs.getString(33) == null ? "" : rs.getString(33));
				h.put("jantinasimati",
						rs.getString(34) == null ? "" : rs.getString(34));

				h.put("umurpemohon",
						rs.getString(35) == null ? "" : rs.getString(35));
				h.put("jantinapemohon",
						rs.getString(36) == null ? "" : rs.getString(36));

				h.put("no_subjaket", rs.getString("no_subjaket") == null ? ""
						: rs.getString("no_subjaket"));
				h.put("id_dahulu",
						rs.getString("ID_PERMOHONANTERDAHULU") == null ? ""
								: rs.getString("ID_PERMOHONANTERDAHULU"));

				h.put("tarikhMohon", rs.getDate("tarikh_Mohon") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mohon")));

				// //System.out.println("Today = " +
				// h.get("tarikhMohon").toString());

				if (rs.getString("tarikh_Mohon") != ""
						&& rs.getString("tarikh_Mohon") != null) {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date t_m = df.parse(sdf.format(rs.getDate("tarikh_Mohon")));
					Date sep_m = df.parse("01/09/2009");
					// System.out.println("Today = "
					// + h.get("tarikhMohon").toString());

					if (t_m.before(sep_m)) {
						h.put("lepassatusept", "yes");
					} else if (t_m.after(sep_m)) {
						h.put("lepassatusept", "no");
					} else {
						h.put("lepassatusept", "no");
					}
				} else {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date = new Date();
					Date sep_m = dateFormat.parse("01/09/2009");
					String currentDate = dateFormat.format(date);

					// System.out.println("DATE :::"+date);

					if (date.before(sep_m)) {
						h.put("lepassatusept", "yes");
					} else if (date.after(sep_m)) {
						h.put("lepassatusept", "no");
					} else {
						h.put("lepassatusept", "no");
					}

				}

				h.put("bkp", rs.getString("BATAL_KUASA_PENTADBIR") == null ? ""
						: rs.getString("BATAL_KUASA_PENTADBIR"));
				h.put("lp",
						rs.getString("LANTIK_PENTADBIR") == null ? "" : rs
								.getString("LANTIK_PENTADBIR"));
				h.put("bpa",
						rs.getString("BATAL_P_AMANAH") == null ? "" : rs
								.getString("BATAL_P_AMANAH"));
				h.put("lpa",
						rs.getString("LANTIK_P_AMANAH") == null ? "" : rs
								.getString("LANTIK_P_AMANAH"));
				h.put("ht",
						rs.getString("HARTA_TINGGAL") == null ? "" : rs
								.getString("HARTA_TINGGAL"));

				// System.out.println("sql data-->" + h);
				listb.addElement(h);
			}
			return listb;
		} finally {
			if (db != null)
				db.close();
		}
	}

	private Vector listFailLama = new Vector();

	public Vector getDataLama() {
		return listFailLama;
	}

	public void setDataLama(String idsimati, int jaket) throws Exception {
		Db db = null;
		listFailLama.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT F.NO_FAIL FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI PS, TBLPPKSIMATI SM"
					+ " WHERE F.ID_FAIL = P.ID_FAIL"
					+ " AND P.ID_PERMOHONAN = PS.ID_PERMOHONAN"
					+ " AND PS.ID_SIMATI = SM.ID_SIMATI AND P.ID_STATUS <> '999'"
					+ " AND SM.ID_SIMATI = '"
					+ idsimati
					+ "'"
					+ " AND P.NO_SUBJAKET = '" + jaket + "'" + "  AND F.NO_FAIL is not null ";

			System.out.println("SQLXXXXXX" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("noFail",
						rs.getString("no_Fail") == null ? "" : rs
								.getString("no_Fail"));

				listFailLama.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getSenaraiPemohonSimati() {
		return listSenaraiPemohonSimati;
	}

	public void SenaraiPemohonSimati(String idsimati) throws Exception {
		Db db = null;
		listSenaraiPemohonSimati.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			 * sql =
			 * "SELECT DISTINCT MO.ID_PEMOHON,MO.NAMA_PEMOHON FROM TBLPPKPERMOHONAN P, "
			 * + "TBLPPKPERMOHONANSIMATI PM, TBLPPKSIMATI SM,TBLPPKPEMOHON MO "
			 * + "WHERE SM.ID_SIMATI = PM.ID_SIMATI " +
			 * "AND P.ID_PERMOHONAN = PM.ID_PERMOHONAN " +
			 * "AND P.ID_PEMOHON = MO.ID_PEMOHON " + "AND SM.ID_SIMATI = '" +
			 * idsimati + "'";
			 */
			// baru tambah
			sql = " SELECT DISTINCT OB.ID_OB, OB.ID_PEMOHON, OB.NAMA_OB AS NAMA_PEMOHON "+
			   " FROM TBLPPKOB OB, "+
			     " TBLPPKPERMOHONAN P, "+
			     " TBLPPKPERMOHONANSIMATI PM, "+
			     " TBLPPKSIMATI SM "+
			     " WHERE SM.ID_SIMATI = PM.ID_SIMATI "+
			     " AND P.ID_PERMOHONAN = PM.ID_PERMOHONAN "+
			     " AND OB.ID_SIMATI = SM.ID_SIMATI "+
			     " AND NVL (OB.STATUS_HIDUP, '0') != 1 "+
			     " AND NVL (OB.STATUS_OB, '0') != 2 "+
			     " AND NVL (OB.STATUS_OB, '0') != 3 "+
			     " AND NVL (OB.STATUS_OB, '0') != 4   "+
			     " AND SM.ID_SIMATI = '" + idsimati + "' "+
			     " UNION "+
			     " SELECT DISTINCT OB.ID_OB, OB.ID_PEMOHON, OB.NAMA_OB AS NAMA_PEMOHON "+
			     " FROM TBLPPKOB OB, "+
			     " TBLPPKPERMOHONAN P, "+
			     " TBLPPKPERMOHONANSIMATI PM, "+
			     " TBLPPKSIMATI SM "+
			     " WHERE SM.ID_SIMATI = PM.ID_SIMATI "+
			     " AND P.ID_PERMOHONAN = PM.ID_PERMOHONAN "+
			     " AND OB.ID_SIMATI = SM.ID_SIMATI "+
			     " AND NVL (OB.STATUS_HIDUP, '0') != 1 "+
			     " AND NVL (OB.STATUS_OB, '0') = 2 "+
			     //tutup kerana buang condition umur utk pemohon aishahlatip 06032018
			     //" AND (OB.TARIKH_LAHIR IS NOT NULL AND ((MONTHS_BETWEEN (SYSDATE, TO_DATE(TO_CHAR(OB.TARIKH_LAHIR,'DD/MM/YYYY'),'DD/MM/YYYY')) / 12)>18)) "+
			     " AND SM.ID_SIMATI = '" + idsimati + "' "+
			     " ORDER BY NAMA_PEMOHON ";

			 System.out.println("SQLXXXXXX" + sql);
			myLogger.info("LIST PEMOHON OB DAHULU :" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("id_pemohon",
						rs.getString("ID_OB") == null ? "" : rs
								.getString("ID_OB"));
				// baru tambah
				/*
				 * h.put("id_ob", rs.getString("ID_OB") == null ? "" :
				 * rs.getString("ID_OB"));
				 */

				h.put("nama_pemohon", rs.getString("NAMA_PEMOHON") == null ? ""
						: rs.getString("NAMA_PEMOHON"));
				
				System.out.println("nama pemohon=="+ rs.getString("NAMA_PEMOHON"));

				listSenaraiPemohonSimati.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	private Vector listFail = new Vector();

	// private static SimpleDateFormat Format = new
	// SimpleDateFormat("dd/MM/yyyy");
	public Vector getDataFail() {
		return listFail;
	}

	public void setDataFail(String id) throws Exception {
		Db db = null;
		listFail.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT sub.id_Suburusanstatus, sub.id_Suburusanstatusfail, sub.aktif, sub.id_permohonan"
					+ " FROM Tblrujsuburusanstatusfail sub, Tblppkpermohonan p, Tblpfdfail f"
					+ " WHERE p.id_Permohonan = sub.id_Permohonan"
					+ " AND p.id_fail = f.id_fail" + " AND (sub.aktif = 1)" +
					// " AND (sub.id_Suburusanstatus = 340 OR sub.id_Suburusanstatus = 353) "+
					" AND sub.id_permohonan = '" + id + "'" + "";

			// System.out.println("SQLXXXXXX FAIL" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("id_Suburusanstatus",
						rs.getString("id_Suburusanstatus") == null ? "" : rs
								.getString("id_Suburusanstatus"));
				h.put("id_Suburusanstatusfail",
						rs.getString("id_Suburusanstatusfail") == null ? ""
								: rs.getString("id_Suburusanstatusfail"));
				h.put("id_Permohonan",
						rs.getString("id_permohonan") == null ? "" : rs
								.getString("id_permohonan"));

				// System.out.println("sql data-->" + h);
				listFail.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	private Vector listGetPermohonanSebelum = new Vector();

	// private static SimpleDateFormat Format = new
	// SimpleDateFormat("dd/MM/yyyy");
	public Vector getlistGetPermohonanSebelum() {
		return listGetPermohonanSebelum;
	}

	public void setlistGetPermohonanSebelum(String idsimati, int subjaket)
			throws Exception {
		Db db = null;
		listGetPermohonanSebelum.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			System.out.println("Keluar sini6");
			sql = "SELECT SM.ID_SIMATI, SM.NAMA_SIMATI, P.NO_SUBJAKET, P.ID_PERMOHONAN"
					+ " FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI PS, TBLPPKPERMOHONAN P"
					+ " WHERE SM.ID_SIMATI = '"
					+ idsimati
					+ "'"
					+ " AND PS.ID_PERMOHONAN = P.ID_PERMOHONAN"
					+ " AND PS.ID_SIMATI = SM.ID_SIMATI"
					+ " AND P.NO_SUBJAKET = '" + subjaket + "'" + "";
			// System.out.println("SQLXXXXXX FAIL" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("no_subjaket", rs.getString("no_subjaket") == null ? ""
						: rs.getString("no_subjaket"));
				h.put("id_Permohonan",
						rs.getString("id_permohonan") == null ? "" : rs
								.getString("id_permohonan"));

				// System.out.println("sql data-->" + h);
				listGetPermohonanSebelum.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	/*
	 * SELECT SM.ID_SIMATI, SM.NAMA_SIMATI, P.NO_SUBJAKET, P.ID_PERMOHONAN FROM
	 * TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI PS, TBLPPKPERMOHONAN P WHERE
	 * SM.ID_SIMATI = '190090' AND PS.ID_PERMOHONAN = P.ID_PERMOHONAN AND
	 * PS.ID_SIMATI = SM.ID_SIMATI AND P.NO_SUBJAKET = 20 - 1
	 */
	
	 public void cetakNilaiHarta(String id_permohonansimati) throws Exception {
	      Db db = null;
	      String sql = "";

	      try {
	            db = new Db();
	            Statement stmt = db.getStatement();
	            
	            sql = "UPDATE TBLPPKPERMOHONAN SET FLAG_PRINT_NILAIAN_HARTA = '1' WHERE ID_PERMOHONAN = (SELECT ID_PERMOHONAN FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONANSIMATI =  '" + id_permohonansimati + "')";
	            myLogger.info("updateTablePermohonanSelesai "+sql);
	            ResultSet rs = stmt.executeQuery(sql);
	           
	            
	      } finally {
	            if (db != null)
	                  db.close();
	      }
	
	 }
	 
	 public void semakNilaiHarta(String id_permohonansimati) throws Exception {
	      Db db = null;
	      String sql = "";

	      try {
	            db = new Db();
	            Statement stmt = db.getStatement();
	            
	            sql = "SELECT FLAG_PRINT_NILAIAN_HARTA FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = (SELECT ID_PERMOHONAN FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONANSIMATI =  '" + id_permohonansimati + "'))";
	            ResultSet rs = stmt.executeQuery(sql);
	            myLogger.info("updateTablePermohonanSelesai "+sql);
	            
	      } finally {
	            if (db != null)
	                  db.close();
	      }
	
	 }
	
	public void addPermohonan(HttpSession session,Hashtable data) throws Exception {
		// Azam add Transaction on 02.02.2010
		
		
		Connection conn = null;
		Db db = null;
		// Db dbOB = null;
		String sqlOB = "";
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		String sql8 = "";

		String sqlbayaran = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

			//
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			// Generic
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			long idFail = DB.getNextID(db, "TBLPFDFAIL_SEQ");
			long idPemohon = DB.getNextID(db, "TBLPPKPEMOHON_SEQ");
			long idsimati = DB.getNextID(db, "TBLPPKSIMATI_SEQ");
			long idBayaran = DB.getNextID(db, "TBLPPKBAYARAN_SEQ");
			long idP_mati = DB.getNextID(db, "TBLPPKPERMOHONANSIMATI_SEQ");

			// System.out.println("idFail"+idFail+"idP_mati"+idP_mati);
			long idPermohonan = Long.parseLong((String) data
					.get("IdPermohonan"));

			// System.out.println("IDPPPPP" + idPermohonan);
			long idSubUrusanStatus = DB.getNextID(db,
					"TBLRUJSUBURUSANSTATUS_SEQ");

			int UserIdPejabat = Integer.parseInt((String) data
					.get("userIdPejabat"));
			String userIdNeg = (String) data.get("userIdNeg");
			String userId = (String) data.get("userId");
			String NegId = (String) data.get("negId");
			String id_Fail = (String) data.get("id_Fail");

			String no_daerah = (String) data.get("no_daerah");
			// System.out.println("IDPPPPP Daerah!!!!" + no_daerah);
			System.out.println("NegId 2 ::::" + NegId);
			int id_d = Integer.parseInt(no_daerah);

			Vector vd = new Vector();
			vd = getListnegeriByDaerah(id_d);
			int idneg = 0;
			//System.out.println("USER KOD DAERAH!!!::");
			Hashtable l = (Hashtable) vd.get(0);
			idneg = Integer.parseInt(l.get("id_Negeri").toString());

			String negeri = (String) data.get("negeri");
			
			System.out.println("negeri 1 ::::" + negeri);
			String no_kpbaru_pemohon = (String) data.get("no_kpbaru_pemohon");
			String no_kplama_pemohon = (String) data.get("no_kplama_pemohon");
			String nama_simati = (String) data.get("nama_simati");
			String tarikh_masuk = (String) data.get("tarikh_masuk");
			String no_kpbaru_simati = (String) data.get("no_kp_baru");
			String no_kplama_simati = (String) data.get("no_kplama_simati");
			String sel_jeniskp_simati = (String) data.get("sel_jeniskp_simati");
			String no_kplain_simati = (String) data.get("no_kplain_simati");
			String tarikhLahirSimati = (String) data.get("tarikhLahirSimati");
			String userIdKodDaerah = (String) data.get("userIdKodDaerah");

			System.out.println("USER KOD DAERAH!!!::" + userIdKodDaerah);

			String userIdKodNegeri = (String) data.get("userIdKodNegeri");
			String tarikh_simati = (String) data.get("tarikh_simati");
			String sel_jeniskp_pemohon = (String) data
					.get("sel_jeniskp_pemohon");
			String no_kplain_pemohon = (String) data.get("no_kplain_pemohon");
			String nama_pemohon = (String) data.get("nama_pemohon");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String bandar = (String) data.get("bandar");
			String idbandar = (String) data.get("idbandar");
			String poskod = (String) data.get("poskod");

			String buktimati = (String) data.get("buktimati");
			String sijilmati = (String) data.get("sijilmati");

			String txtUmurSimati = (String) data.get("txtUmurSimati");
			String socJantinaSimati = (String) data.get("socJantinaSimati");
			String txtUmurPemohon = (String) data.get("txtUmurPemohon");
			String socJantinaPemohon = (String) data.get("socJantinaPemohon");

			String txtbox = (String) data.get("txtbox");
			String tarikhresit = (String) data.get("tarikhresit");

			String no_tel = (String) data.get("no_tel");
			
			
			
			String nama_pelbagainegara = (String) data.get("nama_pelbagainegara");
			
			
			String no_hp = (String) data.get("no_hp");
			String taraf_penting = (String) data.get("taraf_penting");
			String jenis_pemohon = (String) data.get("jenis_pemohon");
			String adaob = (String) data.get("adaob");
			String jenis_pej = (String) data.get("jenis_pej");
			int idNeg = Integer.parseInt(NegId);
			
			String socSaudaraWaris = (String) data.get("socSaudaraWaris");

			// System.out.println("sijilmatimodel::" + sijilmati);

			//

			java.util.Calendar calendar = java.util.Calendar.getInstance();
			int getYear = calendar.get(java.util.Calendar.YEAR);

			//

			String tahun = (String) data.get("tahun");
			int thn = Integer.parseInt(tahun);

			// Add db as parameter -
			/*String X = String.format("%04d", File.getSeqNo(db, 2, 382, 0,
					Integer.parseInt(userIdNeg), Integer.parseInt(no_daerah),
					false, false, thn, 0));*/
			
		
			if (no_daerah.length() < 1) {
				no_daerah = "0" + no_daerah;
			} else {
				no_daerah = no_daerah;
			}
			if (userIdNeg.length() < 1) {
				userIdNeg = "0" + idNeg;
			} else {
				userIdNeg = idNeg+"";
			}
			if (negeri.equals("")) {
				negeri = "0";
			}

			// System.out.println("USER KOD DAERAH MOHON!!!::" + no_daerah);

			int nod = Integer.parseInt(no_daerah);

			// FrmPrmhnnSek8InternalData.setDaerahbyID(nod);

			// db = new Db();
			// Statement stmt12 = db.getStatement();
			// SQLRenderer r12 = new SQLRenderer();
			r.clear();
			r.add("d.id_negeri");
			r.add("d.id_daerah");
			r.add("d.nama_daerah");
			r.add("d.kod_daerah");
			r.add("n.kod_negeri");
			r.add("d.id_negeri", r.unquote("n.id_Negeri"));
			r.add("d.id_daerah", nod);

			String sql12 = r.getSQLSelect("Tblrujdaerah d, Tblrujnegeri n");

			ResultSet rs12 = stmt.executeQuery(sql12);
			// Vector list = new Vector(;
			Hashtable h;
			// int bil = 1;
			String kod = "";
			String kodn = "";
			String idnegerifail = "";
			while (rs12.next()) {
				h = new Hashtable();
				// h.put("bil", bil);
				// h.put("id_Permohonan", rs.getString("id_Permohonan"));
				// h.put("id_Hta",
				// rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
				h.put("id_negeri", rs12.getString("id_negeri") == null ? ""
						: rs12.getString("id_negeri"));
				h.put("id_daerah", rs12.getString("id_daerah") == null ? ""
						: rs12.getString("id_daerah"));
				h.put("nama_daerah", rs12.getString("nama_daerah") == null ? ""
						: rs12.getString("nama_daerah"));
				h.put("kod_daerah", rs12.getString("kod_daerah") == null ? ""
						: rs12.getString("kod_daerah"));
				h.put("kod_negeri", rs12.getString("kod_negeri") == null ? ""
						: rs12.getString("kod_negeri"));

				// bil++;
				kod = rs12.getString("kod_daerah");
				kodn = rs12.getString("kod_negeri");
				idnegerifail = rs12.getString("id_negeri") == null ? ""
						: rs12.getString("id_negeri");
			}
			
			
			String X = String.format("%04d", File.getSeqNo(db, 2, 382, 0,
					Integer.parseInt(idnegerifail), Integer.parseInt(no_daerah),
					false, false, thn, 0));


			// String getNoFile = "JKPTG/PK/"+ userIdKodNegeri + "/"+
			// userIdKodDaerah + "/"+X+"/"+getYear;
			// Dayah changes 06012011

			String getNoFile = "JKPTG/PK/" + kodn + "/" + kod + "/" + X + "/"
					+ tahun;

			// System.out.println("getNoFile:" + getNoFile);

			tarikh_masuk = (String) data.get("tarikh_masuk");
			tarikh_simati = (String) data.get("tarikh_simati");
			String tarikh_mohon = "to_date('" + tarikh_masuk
					+ "','dd/MM/yyyy')";
			String tarikh_mati = "to_date('" + tarikh_simati
					+ "','dd/MM/yyyy')";
			String tarikh_lahir_simati = "to_date('" + tarikhLahirSimati
			+ "','dd/MM/yyyy')";
			
			System.out.println("idNeg 3 :::::::::::::"+idNeg);
			// db = new Db();
			// Statement stmtA = db.getStatement();
			// SQLRenderer r = new SQLRenderer();
			r.clear();
			r.add("id_fail", idFail);
			r.add("id_seksyen", 2);
			r.add("id_urusan", 382);
			r.add("tarikh_daftar_fail", r.unquote("sysdate"));
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("tajuk_fail", getNoFile);
			r.add("no_fail", getNoFile);
			r.add("id_negeri", Integer.parseInt(idnegerifail));
			r.add("id_suburusan", 59);
			r.add("flag_fail", 1);
			r.add("flag_jenis_fail", 1);
			r.add("id_masuk", userId);
			sql = r.getSQLInsert("tblpfdfail");
			stmt.executeUpdate(sql);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String currentDate = dateFormat.format(date);

			// db = new Db();
			// Statement stmt = db.getStatement();
			// SQLRenderer r1 = new SQLRenderer();

			r.clear();
			r.add("id_simati", idsimati);
			r.add("nama_simati", nama_simati);
			r.add("no_kp_baru", no_kpbaru_simati);
			r.add("no_kp_lama", no_kplama_simati);
			r.add("jenis_kp", sel_jeniskp_simati);
			r.add("no_kp_lain", no_kplain_simati);
			if (tarikhLahirSimati != null && tarikhLahirSimati.length() > 0){
				r.add("tarikh_lahir", r.unquote(tarikh_lahir_simati));
			}			
			
			r.add("no_Sijil_Mati", sijilmati);
			r.add("id_Buktimati", buktimati);

			r.add("tarikh_mati", r.unquote(tarikh_mati));
			// r1.add("id_permohonan",idPermohonan);
			r.add("id_masuk", userId);
			r.add("umur", txtUmurSimati);
			r.add("jantina", socJantinaSimati);

			r.add("tarikh_Masuk", r.unquote("sysdate"));
			sql1 = r.getSQLInsert("tblppksimati");
			stmt.executeUpdate(sql1);

			// db = new Db();
			// Statement stmtc = db.getStatement();
			// SQLRenderer r2 = new SQLRenderer();
			r.clear();
			r.add("id_pemohon", idPemohon);
			if (jenis_pemohon.equals("2")) {
				r.add("no_kp_baru", no_kpbaru_pemohon);
				r.add("no_kp_lama", no_kplama_pemohon.toUpperCase());
				r.add("jenis_kp", sel_jeniskp_pemohon);
				r.add("no_kp_lain", no_kplain_pemohon.toUpperCase());
				r.add("umur", txtUmurPemohon);
				r.add("jantina", socJantinaPemohon);
				r.add("no_hp", no_hp);
			} else {
				r.add("no_kp_baru", "");
				r.add("no_kp_lama", "");
				r.add("jenis_kp", "");
				r.add("no_kp_lain", "");
				r.add("umur", "");
				r.add("jantina", "");
				r.add("no_hp", "");

			}
			r.add("nama_pemohon", nama_pemohon.toUpperCase());
			r.add("alamat_1", alamat1.toUpperCase());
			r.add("alamat_2", alamat2.toUpperCase());
			r.add("alamat_3", alamat3.toUpperCase());
			r.add("poskod", poskod);
			r.add("bandar", bandar);
			r.add("id_bandar", idbandar);
			r.add("id_negeri", negeri);
			r.add("alamat1_surat", alamat1.toUpperCase());
			r.add("alamat2_surat", alamat2.toUpperCase());
			r.add("alamat3_surat", alamat3.toUpperCase());
			r.add("poskod_surat", poskod);
			r.add("id_bandarsurat", idbandar);
			r.add("bandar_surat", bandar);
			r.add("id_negerisurat", negeri);
			r.add("no_tel", no_tel);
			
			r.add("nama_pelbagainegara", nama_pelbagainegara);
			r.add("nama_pelbagainegara_surat", nama_pelbagainegara);

			r.add("id_tarafkptg", taraf_penting);
			r.add("status_pemohon", jenis_pemohon);
			r.add("id_Arb", jenis_pej);
			
			r.add("id_saudara", socSaudaraWaris);

			// r2.add("id_permohonan", idPermohonan);
			r.add("id_masuk", userId);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			sql2 = r.getSQLInsert("tblppkpemohon");
			
			myLogger.info(" INSERT PEMOHON :"+sql2);
			stmt.executeUpdate(sql2);

			// db = new Db();
			// Statement stmtd = db.getStatement();
			// SQLRenderer r3 = new SQLRenderer();
			r.clear();
			r.add("id_permohonan", idPermohonan);
			r.add("id_daerahmhn", no_daerah);
			r.add("id_negerimhn", idnegerifail);
			
			myLogger.info(" idneg PEMOHONAN :::::::::::::::::::::::::::::: :"+idneg);
			r.add("id_pemohon", idPemohon);
			r.add("id_status", 8);
			r.add("flag_Jenis_Permohonan", 1);
			r.add("id_fail", idFail);
			r.add("seksyen", 8);
			r.add("no_subjaket", 0);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("tarikh_mohon", r.unquote(tarikh_mohon));
			r.add("id_unitpskawal", UserIdPejabat);
			r.add("id_masuk", userId);
			r.add("id_kemaskini", userId);
			sql3 = r.getSQLInsert("tblppkpermohonan");
			myLogger.info(" INSERT PERMOHONAN :"+sql3);
			stmt.executeUpdate(sql3);

			// db = new Db();
			// Statement stmt8 = db.getStatement();
			// SQLRenderer r8 = new SQLRenderer();
			r.clear();
			r.add("id_permohonansimati", idP_mati);
			r.add("id_Simati", idsimati);
			r.add("id_Permohonan", idPermohonan);
			r.add("id_Masuk", userId);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			sql8 = r.getSQLInsert("tblppkpermohonansimati");
			myLogger.info(" INSERT PERMOHONAN SIMATI :"+sql8);
			stmt.executeUpdate(sql8);
			
			

			if (taraf_penting != "0" && taraf_penting != "" && adaob != "ada") {
				long id_ob = DB.getNextID(db, "TBLPPKOB_SEQ");
				// dbOB = new Db();
				Statement stmtOB = db.getStatement();
				SQLRenderer rOB = new SQLRenderer();
				rOB.clear();
				rOB.add("id_ob", id_ob);
				rOB.add("id_Simati", idsimati);
				rOB.add("nama_Ob", nama_pemohon);
				if (jenis_pemohon.equals("2")) {
					rOB.add("no_Kp_Baru", no_kpbaru_pemohon);
					rOB.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					rOB.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					rOB.add("jenis_Kp", sel_jeniskp_pemohon);
					rOB.add("umur", txtUmurPemohon);
					rOB.add("jantina", socJantinaPemohon);
					rOB.add("no_Hp", no_hp);
					rOB.add("status_ob", "1");
				} else {
					rOB.add("no_Kp_Baru", "");
					rOB.add("no_Kp_Lain", "");
					rOB.add("no_Kp_Lama", "");
					rOB.add("jenis_Kp", "");
					rOB.add("umur", "");
					rOB.add("jantina", "");
					rOB.add("no_Hp", "");
					rOB.add("status_ob", "");
				}
				rOB.add("id_Tarafkptg", taraf_penting);
				rOB.add("jenis_pemiutang", jenis_pemohon);
				rOB.add("id_Permohonansimati", idP_mati);
				rOB.add("no_Tel", no_tel);
				
				rOB.add("nama_pelbagainegara", nama_pelbagainegara);
				rOB.add("nama_pelbagainegara_surat", nama_pelbagainegara);

				rOB.add("alamat_1", alamat1.toUpperCase());
				rOB.add("alamat_2", alamat2.toUpperCase());
				rOB.add("alamat_3", alamat3.toUpperCase());
				rOB.add("id_Bandar", idbandar);
				rOB.add("poskod", poskod);
				rOB.add("lapis", 1);
				rOB.add("status_hidup", 0);
				rOB.add("id_Pemohon", idPemohon);
				rOB.add("id_Negeri", negeri);
				rOB.add("id_Kemaskini", userId);
				rOB.add("tarikh_Kemaskini", r.unquote("sysdate"));
				rOB.add("id_Masuk", userId);
				rOB.add("tarikh_Masuk", r.unquote("sysdate"));
				rOB.add("alamat1_Surat", alamat1.toUpperCase());
				rOB.add("alamat2_Surat", alamat2.toUpperCase());
				rOB.add("alamat3_Surat", alamat3.toUpperCase());
				rOB.add("poskod_Surat", poskod);
				rOB.add("id_Bandarsurat", idbandar);
				rOB.add("id_Negerisurat", negeri);
				rOB.add("id_Arb", jenis_pej);
				
				rOB.add("id_saudara", socSaudaraWaris);

				sqlOB = rOB.getSQLInsert("tblppkob");
				// System.out.println(sqlOB);
				myLogger.info(" INSERT OB :"+sqlOB);
				stmtOB.executeUpdate(sqlOB);
				
				
				
				
				rOB.clear();
				rOB.add("id_ob", id_ob);
				rOB.add("id_Simati", idsimati);
				rOB.add("nama_Ob", nama_pemohon);
				if (jenis_pemohon.equals("2")) {
					rOB.add("no_Kp_Baru", no_kpbaru_pemohon);
					rOB.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					rOB.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					rOB.add("jenis_Kp", sel_jeniskp_pemohon);
					rOB.add("umur", txtUmurPemohon);
					rOB.add("jantina", socJantinaPemohon);
					rOB.add("no_Hp", no_hp);
					rOB.add("status_ob", "1");
				} else {
					rOB.add("no_Kp_Baru", "");
					rOB.add("no_Kp_Lain", "");
					rOB.add("no_Kp_Lama", "");
					rOB.add("jenis_Kp", "");
					rOB.add("umur", "");
					rOB.add("jantina", "");
					rOB.add("no_Hp", "");
					rOB.add("status_ob", "");
				}
				rOB.add("id_Tarafkptg", taraf_penting);
				rOB.add("jenis_pemiutang", jenis_pemohon);
				rOB.add("id_Permohonansimati", idP_mati);
				rOB.add("no_Tel", no_tel);
				
				rOB.add("nama_pelbagainegara", nama_pelbagainegara);
				rOB.add("nama_pelbagainegara_surat", nama_pelbagainegara);

				rOB.add("alamat_1", alamat1.toUpperCase());
				rOB.add("alamat_2", alamat2.toUpperCase());
				rOB.add("alamat_3", alamat3.toUpperCase());
				rOB.add("id_Bandar", idbandar);
				rOB.add("poskod", poskod);
				rOB.add("lapis", 1);
				rOB.add("status_hidup", 0);
				rOB.add("id_Pemohon", idPemohon);
				rOB.add("id_Negeri", negeri);
				rOB.add("id_Kemaskini", userId);
				rOB.add("tarikh_Kemaskini", r.unquote("sysdate"));
				rOB.add("id_Masuk", userId);
				rOB.add("tarikh_Masuk", r.unquote("sysdate"));
				rOB.add("alamat1_Surat", alamat1.toUpperCase());
				rOB.add("alamat2_Surat", alamat2.toUpperCase());
				rOB.add("alamat3_Surat", alamat3.toUpperCase());
				rOB.add("poskod_Surat", poskod);
				rOB.add("id_Bandarsurat", idbandar);
				rOB.add("id_Negerisurat", negeri);
				rOB.add("id_Arb", jenis_pej);				
				rOB.add("id_saudara", socSaudaraWaris);
				sqlOB = rOB.getSQLInsert("tblppkobpermohonan");
				
				myLogger.info(" INSERT OB PERMOHONAN :"+sqlOB);
				stmtOB.executeUpdate(sqlOB);
			}

			// baru

			// db = new Db();
			// Statement stmtF = db.getStatement();
			// SQLRenderer r5 = new SQLRenderer();
			r.clear();
			// azam remark this part, let Oracle Handle the ID
			// r.add("ID_SUBURUSANSTATUSFAIL",
			// DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
			
			
			/*r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", 340);
			r.add("ID_FAIL", idFail);
			r.add("AKTIF", 1);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql5 = r.getSQLInsert("tblrujsuburusanstatusfail");
			stmt.executeUpdate(sql5);*/
			//:::SUB
			
			

			// //System.out.println("NO RESIT :" + txtbox);

			// db = new Db();
			// Statement stmtL = db.getStatement();
			// SQLRenderer r9 = new SQLRenderer();
			r.clear();
			r.add("id_bayaran", idBayaran);
			r.add("id_permohonan", idPermohonan);
			r.add("id_jenisbayaran", 2);

			String tr = "";
			if (txtbox != "") {
				r.add("no_resit", txtbox);
				tr = "to_date('" + tarikhresit + "','dd/MM/yyyy')";
				r.add("tarikh_bayaran", r.unquote(tr));
				r.add("jumlah_bayaran", 10);
			} else {
				r.add("no_resit", "");
				tr = "to_date('" + tarikhresit + "','dd/MM/yyyy')";
				r.add("tarikh_bayaran", "");
				r.add("jumlah_bayaran", 0);
			}
			// r1.add("id_masuk",6);

			r.add("ID_MASUK", userId);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			r.add("tarikh_masuk", r.unquote("sysdate"));
			sqlbayaran = r.getSQLInsert("tblppkbayaran");
			myLogger.info(" INSERT BAYARAN:"+sqlbayaran);
			// System.out.println("sqlbayaran-->" + sqlbayaran);
			stmt.executeUpdate(sqlbayaran);

			//
			conn.commit();
			
			//:::SUB
			myLogger.info("SSF KEMASKINI 1");
			kemaskiniSubUrusanStatusFail(session,idPermohonan+"",userId,"8","340",idFail+"");
			
			//mobile notification 06092017
			String id_fail = Long.toString(idFail);
			Push.genMsgPush(id_fail, "daftar");
			

		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
	}

	public void addPermohonan_online(HttpSession session,Hashtable data) throws Exception {
		// Razman online ppk 16/11/11
		Connection conn = null;
		Db db = null;
		// Db dbOB = null;
		String sqlOB = "";
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		String sql8 = "";

		String sqlbayaran = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			long idFail = DB.getNextID(db, "TBLPFDFAIL_SEQ");
			long idPemohon = DB.getNextID(db, "TBLPPKPEMOHON_SEQ");
			long idsimati = DB.getNextID(db, "TBLPPKSIMATI_SEQ");
			long idBayaran = DB.getNextID(db, "TBLPPKBAYARAN_SEQ");
			long idP_mati = DB.getNextID(db, "TBLPPKPERMOHONANSIMATI_SEQ");

			long idPermohonan = Long.parseLong((String) data.get("IdPermohonan"));
			
			long idSubUrusanStatus = DB.getNextID(db,
					"TBLRUJSUBURUSANSTATUS_SEQ");

			//int UserIdPejabat = Integer.parseInt((String) data.get("userIdPejabat"));
			String userIdNeg = (String) data.get("userIdNeg");
			String userId = (String) data.get("userId");
			String NegId = (String) data.get("negId");
			String id_Fail = (String) data.get("id_Fail");

			String no_daerah = (String) data.get("no_daerah");

			int id_d = Integer.parseInt(no_daerah);

			Vector vd = new Vector();
			vd = getListnegeriByDaerah(id_d);
			int idneg = 0;

		/*	Hashtable l = (Hashtable) vd.get(0);
			idneg = Integer.parseInt(l.get("id_Negeri").toString());*/

			String negeri = (String) data.get("negeri");
			String no_kpbaru_pemohon = (String) data.get("no_kpbaru_pemohon");
			String no_kplama_pemohon = (String) data.get("no_kplama_pemohon");
			String nama_simati = (String) data.get("nama_simati");
			String tarikh_masuk = (String) data.get("tarikh_masuk");
			String no_kpbaru_simati = (String) data.get("no_kp_baru");
			String no_kplama_simati = (String) data.get("no_kplama_simati");
			String sel_jeniskp_simati = (String) data.get("sel_jeniskp_simati");
			String no_kplain_simati = (String) data.get("no_kplain_simati");

			String userIdKodDaerah = (String) data.get("userIdKodDaerah");

			String userIdKodNegeri = (String) data.get("userIdKodNegeri");
			String tarikh_simati = (String) data.get("tarikh_simati");
			String sel_jeniskp_pemohon = (String) data
					.get("sel_jeniskp_pemohon");
			String no_kplain_pemohon = (String) data.get("no_kplain_pemohon");
			String nama_pemohon = (String) data.get("nama_pemohon");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String bandar = (String) data.get("bandar");
			String idbandar = (String) data.get("idbandar");
			String poskod = (String) data.get("poskod");

			String buktimati = (String) data.get("buktimati");
			String sijilmati = (String) data.get("sijilmati");

			String txtUmurSimati = (String) data.get("txtUmurSimati");
			String socJantinaSimati = (String) data.get("socJantinaSimati");
			String txtUmurPemohon = (String) data.get("txtUmurPemohon");
			String socJantinaPemohon = (String) data.get("socJantinaPemohon");

			String txtbox = (String) data.get("txtbox");
			String tarikhresit = (String) data.get("tarikhresit");

			String no_tel = (String) data.get("no_tel");
			String nama_pelbagainegara = (String) data.get("nama_pelbagainegara");
			
			
			String no_hp = (String) data.get("no_hp");
			String taraf_penting = (String) data.get("taraf_penting");
			String jenis_pemohon = (String) data.get("jenis_pemohon");
			String adaob = (String) data.get("adaob");
			String jenis_pej = (String) data.get("jenis_pej");
			String socSaudaraWaris = (String) data.get("socSaudaraWaris");

			java.util.Calendar calendar = java.util.Calendar.getInstance();
			int getYear = calendar.get(java.util.Calendar.YEAR);

			String tahun = (String) data.get("tahun");
			String thn = tahun;

			// String X = String.format("%04d",File.getSeqNo(db,2, 382,
			// 0,Integer.parseInt(userIdNeg), Integer.parseInt(no_daerah),
			// false,false, thn,0));

			if (no_daerah.length() < 1) {
				no_daerah = "0" + no_daerah;
			} else {
				no_daerah = no_daerah;
			}
			if (userIdNeg.length() < 1) {
				userIdNeg = "0" + userIdNeg;
			} else {
				userIdNeg = userIdNeg;
			}
			if (negeri.equals("")) {
				negeri = "0";
			}

			int nod = Integer.parseInt(no_daerah);

			r.clear();
			r.add("d.id_negeri");
			r.add("d.id_daerah");
			r.add("d.nama_daerah");
			r.add("d.kod_daerah");
			r.add("n.kod_negeri");
			r.add("d.id_negeri", r.unquote("n.id_Negeri"));
			r.add("d.id_daerah", nod);

			String sql12 = r.getSQLSelect("Tblrujdaerah d, Tblrujnegeri n");

			ResultSet rs12 = stmt.executeQuery(sql12);
			// Vector list = new Vector(;
			Hashtable h;
			// int bil = 1;
			String kod = "";
			String kodn = "";
			while (rs12.next()) {
				h = new Hashtable();

				h.put("id_negeri", rs12.getString("id_negeri") == null ? ""
						: rs12.getString("id_negeri"));
				h.put("id_daerah", rs12.getString("id_daerah") == null ? ""
						: rs12.getString("id_daerah"));
				h.put("nama_daerah", rs12.getString("nama_daerah") == null ? ""
						: rs12.getString("nama_daerah"));
				h.put("kod_daerah", rs12.getString("kod_daerah") == null ? ""
						: rs12.getString("kod_daerah"));
				h.put("kod_negeri", rs12.getString("kod_negeri") == null ? ""
						: rs12.getString("kod_negeri"));

				kod = rs12.getString("kod_daerah");
				kodn = rs12.getString("kod_negeri");
			}

		    //belum create no fail...awal...no fail ganerate dekat pengesahan
			//String X = String.format("%06d",File.getSeqNo(db,2,01,18,0,0,false,false,0,0));
			//String no_fail_online = "JKPTG/PK/01/" + getYear + "/" + X;
			String no_fail_online = "";

			tarikh_masuk = (String) data.get("tarikh_masuk");
			tarikh_simati = (String) data.get("tarikh_simati");
			String tarikh_mohon = "to_date('" + tarikh_masuk
					+ "','dd/MM/yyyy')";
			String tarikh_mati = "to_date('" + tarikh_simati
					+ "','dd/MM/yyyy')";
			int idNeg = Integer.parseInt(NegId);

			r.clear();
			r.add("id_fail", idFail);
			//r.add("id_negeri",Integer.parseInt(idnegeri));
			r.add("id_seksyen", 2);
			r.add("id_urusan", 382);
			r.add("tarikh_daftar_fail", r.unquote("sysdate"));
			r.add("tarikh_masuk", r.unquote("sysdate"));
			// r.add("tajuk_fail", getNoFile);
			// r.add("no_fail", getNoFile);
			r.add("id_negeri", Integer.parseInt(userIdNeg));
			r.add("id_suburusan", 59);
			r.add("flag_fail", 1);
			r.add("id_status",7); // active
			//r.add("flag_jenis_fail", 1);
			r.add("id_masuk", userId);
			sql = r.getSQLInsert("tblpfdfail");
			stmt.executeUpdate(sql);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String currentDate = dateFormat.format(date);

			// db = new Db();
			// Statement stmt = db.getStatement();
			// SQLRenderer r1 = new SQLRenderer();

			r.clear();
			r.add("id_simati", idsimati);
			r.add("nama_simati", nama_simati);
			r.add("no_kp_baru", no_kpbaru_simati);
			r.add("no_kp_lama", no_kplama_simati);
			r.add("jenis_kp", sel_jeniskp_simati);
			r.add("no_kp_lain", no_kplain_simati);
			r.add("flag_permohonan_online", "1");
			
			//r.add("no_Sijil_Mati", sijilmati);
			//r.add("id_Buktimati", buktimati);

			r.add("tarikh_mati", r.unquote(tarikh_mati));
			// r1.add("id_permohonan",idPermohonan);
			r.add("id_masuk", userId);
			r.add("umur", txtUmurSimati);
			r.add("jantina", socJantinaSimati);

			r.add("tarikh_Masuk", r.unquote("sysdate"));
			sql1 = r.getSQLInsert("tblppksimati");
			//System.out.println("SQL SIMATI :" + sql1);
			
			stmt.executeUpdate(sql1);

			// db = new Db();
			// Statement stmtc = db.getStatement();
			// SQLRenderer r2 = new SQLRenderer();
			r.clear();
			r.add("id_pemohon", idPemohon);
			if (jenis_pemohon.equals("2")) {
				r.add("no_kp_baru", no_kpbaru_pemohon);
				r.add("no_kp_lama", no_kplama_pemohon.toUpperCase());
				r.add("jenis_kp", sel_jeniskp_pemohon);
				r.add("no_kp_lain", no_kplain_pemohon.toUpperCase());
				r.add("umur", txtUmurPemohon);
				r.add("jantina", socJantinaPemohon);
				r.add("no_hp", no_hp);
			} else {
				r.add("no_kp_baru", "");
				r.add("no_kp_lama", "");
				r.add("jenis_kp", "");
				r.add("no_kp_lain", "");
				r.add("umur", "");
				r.add("jantina", "");
				r.add("no_hp", "");

			}
			r.add("nama_pemohon", nama_pemohon.toUpperCase());
			r.add("alamat_1", alamat1.toUpperCase());
			r.add("alamat_2", alamat2.toUpperCase());
			r.add("alamat_3", alamat3.toUpperCase());
			r.add("poskod", poskod);
			r.add("bandar", bandar);
			r.add("id_bandar", idbandar);
			r.add("id_negeri", negeri);
			r.add("alamat1_surat", alamat1.toUpperCase());
			r.add("alamat2_surat", alamat2.toUpperCase());
			r.add("alamat3_surat", alamat3.toUpperCase());
			r.add("poskod_surat", poskod);
			r.add("id_bandarsurat", idbandar);
			r.add("bandar_surat", bandar);
			r.add("id_negerisurat", negeri);
			r.add("no_tel", no_tel);
			r.add("id_saudara", socSaudaraWaris);
			
			

			r.add("id_tarafkptg", taraf_penting);
			r.add("status_pemohon", jenis_pemohon);
			r.add("id_Arb", jenis_pej);

			// r2.add("id_permohonan", idPermohonan);
			r.add("id_masuk", userId);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			
			r.add("nama_pelbagainegara", nama_pelbagainegara);
			r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
			
			sql2 = r.getSQLInsert("tblppkpemohon");
			stmt.executeUpdate(sql2);

			// db = new Db();
			// Statement stmtd = db.getStatement();
			// SQLRenderer r3 = new SQLRenderer();
			r.clear();
			r.add("id_permohonan", idPermohonan);
			r.add("id_daerahmhn", no_daerah);
			r.add("id_negerimhn", idneg);
			r.add("id_pemohon", idPemohon);
			r.add("id_status", 150);
			r.add("flag_Jenis_Permohonan", 0);// flag_online

			r.add("id_fail", idFail);
			r.add("NO_PERMOHONAN_ONLINE", no_fail_online);
			r.add("seksyen", 8);
			r.add("no_subjaket", 0);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			//r.add("tarikh_mohon", r.unquote(tarikh_mohon));
		//	r.add("id_unitpskawal", UserIdPejabat);
			r.add("id_masuk", userId);
			sql3 = r.getSQLInsert("tblppkpermohonan");
			stmt.executeUpdate(sql3);

			// db = new Db();
			// Statement stmt8 = db.getStatement();
			// SQLRenderer r8 = new SQLRenderer();
			r.clear();
			r.add("id_permohonansimati", idP_mati);
			r.add("id_Simati", idsimati);
			r.add("id_Permohonan", idPermohonan);
			r.add("id_Masuk", userId);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			sql8 = r.getSQLInsert("tblppkpermohonansimati");
			stmt.executeUpdate(sql8);

			if (taraf_penting != "0" && taraf_penting != "" && adaob != "ada") {
				long id_ob = DB.getNextID(db, "TBLPPKOB_SEQ");
				// dbOB = new Db();
				Statement stmtOB = db.getStatement();
				SQLRenderer rOB = new SQLRenderer();
				rOB.clear();
				rOB.add("id_ob", id_ob);
				rOB.add("id_Simati", idsimati);
				rOB.add("nama_Ob", nama_pemohon);
				if (jenis_pemohon.equals("2")) {
					rOB.add("no_Kp_Baru", no_kpbaru_pemohon);
					rOB.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					rOB.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					rOB.add("jenis_Kp", sel_jeniskp_pemohon);
					rOB.add("umur", txtUmurPemohon);
					rOB.add("jantina", socJantinaPemohon);
					rOB.add("no_Hp", no_hp);
					rOB.add("status_ob", "1");
				} else {
					rOB.add("no_Kp_Baru", "");
					rOB.add("no_Kp_Lain", "");
					rOB.add("no_Kp_Lama", "");
					rOB.add("jenis_Kp", "");
					rOB.add("umur", "");
					rOB.add("jantina", "");
					rOB.add("no_Hp", "");
					rOB.add("status_ob", "");
				}
				rOB.add("id_Tarafkptg", taraf_penting);
				rOB.add("jenis_pemiutang", jenis_pemohon);
				rOB.add("id_Permohonansimati", idP_mati);
				rOB.add("no_Tel", no_tel);

				rOB.add("alamat_1", alamat1.toUpperCase());
				rOB.add("alamat_2", alamat2.toUpperCase());
				rOB.add("alamat_3", alamat3.toUpperCase());
				rOB.add("id_Bandar", idbandar);
				rOB.add("poskod", poskod);
				rOB.add("lapis", 1);
				rOB.add("status_hidup", 0);
				rOB.add("id_Pemohon", idPemohon);
				rOB.add("id_Negeri", negeri);
				rOB.add("id_Kemaskini", userId);
				rOB.add("tarikh_Kemaskini", r.unquote("sysdate"));
				rOB.add("id_Masuk", userId);
				rOB.add("tarikh_Masuk", r.unquote("sysdate"));
				rOB.add("alamat1_Surat", alamat1.toUpperCase());
				rOB.add("alamat2_Surat", alamat2.toUpperCase());
				rOB.add("alamat3_Surat", alamat3.toUpperCase());
				rOB.add("poskod_Surat", poskod);
				rOB.add("id_Bandarsurat", idbandar);
				rOB.add("id_Negerisurat", negeri);
				rOB.add("id_Arb", jenis_pej);
				
				rOB.add("id_saudara", socSaudaraWaris);
				
				rOB.add("nama_pelbagainegara", nama_pelbagainegara);
				rOB.add("nama_pelbagainegara_surat", nama_pelbagainegara);

				sqlOB = rOB.getSQLInsert("tblppkob");
				// System.out.println(sqlOB);
				stmtOB.executeUpdate(sqlOB);
				
				
				
				
				rOB.clear();
				rOB.add("id_ob", id_ob);
				rOB.add("id_Simati", idsimati);
				rOB.add("nama_Ob", nama_pemohon);
				if (jenis_pemohon.equals("2")) {
					rOB.add("no_Kp_Baru", no_kpbaru_pemohon);
					rOB.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					rOB.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					rOB.add("jenis_Kp", sel_jeniskp_pemohon);
					rOB.add("umur", txtUmurPemohon);
					rOB.add("jantina", socJantinaPemohon);
					rOB.add("no_Hp", no_hp);
					rOB.add("status_ob", "1");
				} else {
					rOB.add("no_Kp_Baru", "");
					rOB.add("no_Kp_Lain", "");
					rOB.add("no_Kp_Lama", "");
					rOB.add("jenis_Kp", "");
					rOB.add("umur", "");
					rOB.add("jantina", "");
					rOB.add("no_Hp", "");
					rOB.add("status_ob", "");
				}
				rOB.add("id_Tarafkptg", taraf_penting);
				rOB.add("jenis_pemiutang", jenis_pemohon);
				rOB.add("id_Permohonansimati", idP_mati);
				rOB.add("no_Tel", no_tel);

				rOB.add("alamat_1", alamat1.toUpperCase());
				rOB.add("alamat_2", alamat2.toUpperCase());
				rOB.add("alamat_3", alamat3.toUpperCase());
				rOB.add("id_Bandar", idbandar);
				rOB.add("poskod", poskod);
				rOB.add("lapis", 1);
				rOB.add("status_hidup", 0);
				rOB.add("id_Pemohon", idPemohon);
				rOB.add("id_Negeri", negeri);
				rOB.add("id_Kemaskini", userId);
				rOB.add("tarikh_Kemaskini", r.unquote("sysdate"));
				rOB.add("id_Masuk", userId);
				rOB.add("tarikh_Masuk", r.unquote("sysdate"));
				rOB.add("alamat1_Surat", alamat1.toUpperCase());
				rOB.add("alamat2_Surat", alamat2.toUpperCase());
				rOB.add("alamat3_Surat", alamat3.toUpperCase());
				rOB.add("poskod_Surat", poskod);
				rOB.add("id_Bandarsurat", idbandar);
				rOB.add("id_Negerisurat", negeri);
				rOB.add("id_Arb", jenis_pej);				
				rOB.add("id_saudara", socSaudaraWaris);
				rOB.add("nama_pelbagainegara", nama_pelbagainegara);
				rOB.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				sqlOB = rOB.getSQLInsert("tblppkobpermohonan");
				stmtOB.executeUpdate(sqlOB);
			}

			/*
			//sebelum ubah structure
			if (taraf_penting != "0" && taraf_penting != "" && adaob != "ada") {
				
				Statement stmtOB = db.getStatement();
				SQLRenderer rOB = new SQLRenderer();

				rOB.add("id_Simati", idsimati);
				rOB.add("nama_Ob", nama_pemohon);
				if (jenis_pemohon.equals("2")) {
					rOB.add("no_Kp_Baru", no_kpbaru_pemohon);
					rOB.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					rOB.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					rOB.add("jenis_Kp", sel_jeniskp_pemohon);
					rOB.add("umur", txtUmurPemohon);
					rOB.add("jantina", socJantinaPemohon);
					rOB.add("no_Hp", no_hp);
				} else {
					rOB.add("no_Kp_Baru", "");
					rOB.add("no_Kp_Lain", "");
					rOB.add("no_Kp_Lama", "");
					rOB.add("jenis_Kp", "");
					rOB.add("umur", "");
					rOB.add("jantina", "");
					rOB.add("no_Hp", "");
				}
				rOB.add("id_Tarafkptg", taraf_penting);
				rOB.add("jenis_pemiutang", jenis_pemohon);
				rOB.add("id_Permohonansimati", idP_mati);
				rOB.add("no_Tel", no_tel);

				rOB.add("alamat_1", alamat1.toUpperCase());
				rOB.add("alamat_2", alamat2.toUpperCase());
				rOB.add("alamat_3", alamat3.toUpperCase());
				rOB.add("id_Bandar", idbandar);
				rOB.add("poskod", poskod);
				rOB.add("lapis", 1);
				rOB.add("status_hidup", 0);
				rOB.add("id_Pemohon", idPemohon);
				rOB.add("id_Negeri", negeri);
				rOB.add("id_Kemaskini", userId);
				rOB.add("tarikh_Kemaskini", r.unquote("sysdate"));
				rOB.add("id_Masuk", userId);
				rOB.add("tarikh_Masuk", r.unquote("sysdate"));
				rOB.add("alamat1_Surat", alamat1.toUpperCase());
				rOB.add("alamat2_Surat", alamat2.toUpperCase());
				rOB.add("alamat3_Surat", alamat3.toUpperCase());
				rOB.add("poskod_Surat", poskod);
				rOB.add("id_Bandarsurat", idbandar);
				rOB.add("id_Negerisurat", negeri);
				rOB.add("id_Arb", jenis_pej);
				rOB.add("id_saudara", socSaudaraWaris);

				sqlOB = rOB.getSQLInsert("tblppkob");
				// System.out.println(sqlOB);
				stmtOB.executeUpdate(sqlOB);
			}
			*/

			// baru

			// db = new Db();
			// Statement stmtF = db.getStatement();
			// SQLRenderer r5 = new SQLRenderer();
			//r.clear();
			// azam remark this part, let Oracle Handle the ID
			// r.add("ID_SUBURUSANSTATUSFAIL",
			// DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
			
			/*
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", 436);
			r.add("ID_FAIL", idFail);
			r.add("AKTIF", 1);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql5 = r.getSQLInsert("tblrujsuburusanstatusfail");
			stmt.executeUpdate(sql5);*/
			
			
			//:::SUB

			// //System.out.println("NO RESIT :" + txtbox);

			// db = new Db();
			// Statement stmtL = db.getStatement();
			// SQLRenderer r9 = new SQLRenderer();
			
			
			r.clear();
			r.add("id_bayaran", idBayaran);
			r.add("id_permohonan", idPermohonan);
			r.add("id_jenisbayaran", 2);

			String tr = "";
			if (txtbox != "") {
				r.add("no_resit", txtbox);
				tr = "to_date('" + tarikhresit + "','dd/MM/yyyy')";
				r.add("tarikh_bayaran", r.unquote(tr));
				r.add("jumlah_bayaran", 10);
			} else {
				r.add("no_resit", "");
				tr = "to_date('" + tarikhresit + "','dd/MM/yyyy')";
				r.add("tarikh_bayaran", "");
				r.add("jumlah_bayaran", 0);
			}
			// r1.add("id_masuk",6);

			r.add("ID_MASUK", userId);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			r.add("tarikh_masuk", r.unquote("sysdate"));
			//sqlbayaran = r.getSQLInsert("tblppkbayaran");
			// System.out.println("sqlbayaran-->" + sqlbayaran);
			//stmt.executeUpdate(sqlbayaran);

			
			conn.commit();
			//:::SUB
			myLogger.info("SSF KEMASKINI 2");
			kemaskiniSubUrusanStatusFail(session,idPermohonan+"",userId,"150","436",idFail+"");

		} /*catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail:" + se.getMessage());
		} */finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
	}

	public Vector checkwaris(String idob) throws Exception {
		Vector listcheckwaris = new Vector();
		Db db = null;
		listcheckwaris.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "select id_ob,id_pemohon from tblppkob where id_ob is not null and  id_ob = '"
					+ idob + "' ";

			// System.out.println("SQL PRINT PEMOHON WARIS:"+sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("id_ob",
						rs.getString("id_ob") == null ? "" : rs
								.getString("id_ob"));
				listcheckwaris.addElement(h);

			}
			return listcheckwaris;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public Vector checkpemohonwaris(String idpemohon) throws Exception {
		Vector listcheckpemohonwaris = new Vector();
		Db db = null;
		listcheckpemohonwaris.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "select id_ob,id_pemohon from tblppkob where id_pemohon is not null and  id_pemohon = '"
					+ idpemohon + "' ";

			// System.out.println("SQL PRINT PEMOHON WARIS:"+sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("id_ob",
						rs.getString("id_ob") == null ? "" : rs
								.getString("id_ob"));
				listcheckpemohonwaris.addElement(h);

			}
			return listcheckpemohonwaris;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public Vector checkpemohonwarislong(long idpemohon) throws Exception {
		Vector listcheckpemohonwarislong = new Vector();
		Db db = null;
		listcheckpemohonwarislong.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "select id_ob,id_pemohon from tblppkob where id_pemohon is not null and  id_pemohon = '"
					+ idpemohon + "' ";

			// System.out.println("SQL PRINT PEMOHON WARIS:"+sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("id_ob",
						rs.getString("id_ob") == null ? "" : rs
								.getString("id_ob"));
				listcheckpemohonwarislong.addElement(h);

			}
			return listcheckpemohonwarislong;
		} finally {
			if (db != null)
				db.close();
		}

	}

	
	
	public void addPermohonanKutipan(HttpSession session,Hashtable data) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		String sql8 = "";
		String sqlbayaran = "";
		// Db dbOB = null;
		String sqlOB = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			//
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			long idFail = DB.getNextID(db, "TBLPFDFAIL_SEQ");
			long idPemohon = DB.getNextID(db, "TBLPPKPEMOHON_SEQ");
			long idsimati = DB.getNextID(db, "TBLPPKSIMATI_SEQ");
			long idBayaran = DB.getNextID(db, "TBLPPKBAYARAN_SEQ");
			long idP_mati = DB.getNextID(db, "TBLPPKPERMOHONANSIMATI_SEQ");

			long idPermohonan = Long.parseLong((String) data
					.get("IdPermohonan"));

			// System.out.println("IDPPPPP" + idPermohonan);
			long idSubUrusanStatus = DB.getNextID(db,
					"TBLRUJSUBURUSANSTATUS_SEQ");

			int UserIdPejabat = Integer.parseInt((String) data
					.get("userIdPejabat"));
			String userIdNeg = (String) data.get("userIdNeg");
			String userId = (String) data.get("userId");
			String NegId = (String) data.get("negId");
			String id_Fail = (String) data.get("id_Fail");

			String no_daerah = (String) data.get("no_daerah");
			// System.out.println("IDPPPPP Daerah!!!!" + no_daerah);

			int id_d = Integer.parseInt(no_daerah);

			Vector vd = new Vector();
			vd = getListnegeriByDaerah(id_d);
			int idneg = 0;

			Hashtable l = (Hashtable) vd.get(0);
			idneg = Integer.parseInt(l.get("id_Negeri").toString());

			// h.put("txtNoFail", txtNoFail);

			String txtNoFail = (String) data.get("txtNoFail");

			String negeri = (String) data.get("negeri");
			String no_kpbaru_pemohon = (String) data.get("no_kpbaru_pemohon");
			String no_kplama_pemohon = (String) data.get("no_kplama_pemohon");
			String nama_simati = (String) data.get("nama_simati");
			String tarikh_masuk = (String) data.get("tarikh_masuk");
			String no_kpbaru_simati = (String) data.get("no_kp_baru");
			String no_kplama_simati = (String) data.get("no_kplama_simati");
			String sel_jeniskp_simati = (String) data.get("sel_jeniskp_simati");
			String no_kplain_simati = (String) data.get("no_kplain_simati");

			String userIdKodDaerah = (String) data.get("userIdKodDaerah");

			// System.out.println("USER KOD DAERAH!!!::" + userIdKodDaerah);

			String userIdKodNegeri = (String) data.get("userIdKodNegeri");
			String tarikh_simati = (String) data.get("tarikh_simati");
			String sel_jeniskp_pemohon = (String) data
					.get("sel_jeniskp_pemohon");
			String no_kplain_pemohon = (String) data.get("no_kplain_pemohon");
			String nama_pemohon = (String) data.get("nama_pemohon");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String bandar = (String) data.get("bandar");
			String idbandar = (String) data.get("idbandar");
			String poskod = (String) data.get("poskod");

			String buktimati = (String) data.get("buktimati");
			String sijilmati = (String) data.get("sijilmati");

			String txtUmurSimati = (String) data.get("txtUmurSimati");
			String socJantinaSimati = (String) data.get("socJantinaSimati");
			String txtUmurPemohon = (String) data.get("txtUmurPemohon");
			String socJantinaPemohon = (String) data.get("socJantinaPemohon");

			String txtbox = (String) data.get("txtbox");
			String tarikhresit = (String) data.get("tarikhresit");
			String no_tel = (String) data.get("no_tel");
			String nama_pelbagainegara = (String) data.get("nama_pelbagainegara");
			String no_hp = (String) data.get("no_hp");
			String jenis_pej = (String) data.get("jenis_pej");
			String taraf_penting = (String) data.get("taraf_penting");
			String jenis_pemohon = (String) data.get("jenis_pemohon");
			String adaob = (String) data.get("adaob");
			
			String socSaudaraWaris = (String) data.get("socSaudaraWaris");

			// System.out.println("sijilmatimodel::" + sijilmati);

			java.util.Calendar calendar = java.util.Calendar.getInstance();
			int getYear = calendar.get(java.util.Calendar.YEAR);

			// String X = String.format("%04d", File.getSeqNo(db,2, 382, 0,
			// Integer
			// .parseInt(userIdNeg), Integer.parseInt(no_daerah), false,
			// false, getYear,0));

			if (no_daerah.length() < 1) {
				no_daerah = "0" + no_daerah;
			} else {
				no_daerah = no_daerah;
			}
			if (userIdNeg.length() < 1) {
				userIdNeg = "0" + userIdNeg;
			} else {
				userIdNeg = userIdNeg;
			}
			if (negeri.equals("")) {
				negeri = "0";
			}

			// System.out.println("USER KOD DAERAH MOHON!!!::" + no_daerah);

			int nod = Integer.parseInt(no_daerah);

			// FrmPrmhnnSek8InternalData.setDaerahbyID(nod);

			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("d.id_negeri");
			r.add("d.id_daerah");
			r.add("d.nama_daerah");
			r.add("d.kod_daerah");
			r.add("n.kod_negeri");
			r.add("d.id_negeri", r.unquote("n.id_Negeri"));
			r.add("d.id_daerah", nod);
			String sql12 = r.getSQLSelect("Tblrujdaerah d, Tblrujnegeri n");
			ResultSet rs12 = stmt.executeQuery(sql12);
			// Vector list = new Vector(;
			Hashtable h;
			// int bil = 1;
			String kod = "";
			String kodn = "";
			while (rs12.next()) {
				h = new Hashtable();
				// h.put("bil", bil);
				// h.put("id_Permohonan", rs.getString("id_Permohonan"));
				// h.put("id_Hta",
				// rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
				h.put("id_negeri", rs12.getString("id_negeri") == null ? ""
						: rs12.getString("id_negeri"));
				h.put("id_daerah", rs12.getString("id_daerah") == null ? ""
						: rs12.getString("id_daerah"));
				h.put("nama_daerah", rs12.getString("nama_daerah") == null ? ""
						: rs12.getString("nama_daerah"));
				h.put("kod_daerah", rs12.getString("kod_daerah") == null ? ""
						: rs12.getString("kod_daerah"));
				h.put("kod_negeri", rs12.getString("kod_negeri") == null ? ""
						: rs12.getString("kod_negeri"));

				// bil++;
				kod = rs12.getString("kod_daerah");
				kodn = rs12.getString("kod_negeri");
			}

			// String getNoFile = "JKPTG/PK/"+ userIdKodNegeri + "/"+
			// userIdKodDaerah + "/"+X+"/"+getYear;

			// not supposed to be here.
			// String getNoFile = "JKPTG/PK/" + kodn + "/" + kod + "/" + X + "/"
			// + getYear;

			// System.out.println("getNoFile:" + getNoFile);

			tarikh_masuk = (String) data.get("tarikh_masuk");
			tarikh_simati = (String) data.get("tarikh_simati");
			String tarikh_mohon = "to_date('" + tarikh_masuk
					+ "','dd/MM/yyyy')";
			String tarikh_mati = "to_date('" + tarikh_simati
					+ "','dd/MM/yyyy')";
			int idNeg = Integer.parseInt(NegId);

			// db = new Db();
			// Statement stmtA = db.getStatement();
			// SQLRenderer r = new SQLRenderer();
			r.clear();
			r.add("id_fail", idFail);
			r.add("id_seksyen", 2);
			r.add("id_urusan", 382);
			r.add("tarikh_daftar_fail", r.unquote("sysdate"));
			r.add("tarikh_masuk", r.unquote("sysdate"));
			// r.add("tajuk_fail",getNoFile);
			// r.add("no_fail",getNoFile);
			r.add("tajuk_fail", txtNoFail);
			r.add("no_fail", txtNoFail);
			r.add("id_negeri", Integer.parseInt(userIdNeg));
			r.add("id_suburusan", 59);
			r.add("flag_fail", 1);
			r.add("flag_jenis_fail", 3);
			r.add("id_masuk", userId);
			sql = r.getSQLInsert("tblpfdfail");
			stmt.executeUpdate(sql);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String currentDate = dateFormat.format(date);

			// Statement stmt = db.getStatement();
			// SQLRenderer r1 = new SQLRenderer();
			r.clear();
			r.add("id_simati", idsimati);
			r.add("nama_simati", nama_simati);
			r.add("no_kp_baru", no_kpbaru_simati);
			r.add("no_kp_lama", no_kplama_simati);
			r.add("jenis_kp", sel_jeniskp_simati);
			r.add("no_kp_lain", no_kplain_simati);

			r.add("no_Sijil_Mati", sijilmati);
			r.add("id_Buktimati", buktimati);

			r.add("tarikh_mati", r.unquote(tarikh_mati));
			// r1.add("id_permohonan",idPermohonan);
			r.add("id_masuk", userId);
			r.add("umur", txtUmurSimati);
			r.add("jantina", socJantinaSimati);

			r.add("tarikh_Masuk", r.unquote("sysdate"));
			sql1 = r.getSQLInsert("tblppksimati");
			stmt.executeUpdate(sql1);
			/*
			 * db = new Db(); Statement stmtc = db.getStatement(); SQLRenderer
			 * r2 = new SQLRenderer(); r2.add("id_pemohon", idPemohon);
			 * r2.add("no_kp_baru", no_kpbaru_pemohon); r2.add("no_kp_lama",
			 * no_kplama_pemohon); r2.add("jenis_kp", sel_jeniskp_pemohon);
			 * r2.add("no_kp_lain", no_kplain_pemohon); r2.add("nama_pemohon",
			 * nama_pemohon);
			 * 
			 * r2.add("alamat_1", alamat1); r2.add("alamat_2", alamat2);
			 * r2.add("alamat_3", alamat3); r2.add("poskod", poskod);
			 * r2.add("bandar", bandar); r2.add("id_bandar", idbandar);
			 * r2.add("id_negeri", negeri);
			 * 
			 * r2.add("alamat1_surat", alamat1); r2.add("alamat2_surat",
			 * alamat2); r2.add("alamat3_surat", alamat3);
			 * r2.add("poskod_surat", poskod); r2.add("bandar_surat", bandar);
			 * r2.add("id_bandarsurat", idbandar); r2.add("id_negerisurat",
			 * negeri);
			 * 
			 * r2.add("umur", txtUmurPemohon); r2.add("jantina",
			 * socJantinaPemohon); // r2.add("id_permohonan", idPermohonan);
			 * r2.add("id_masuk", userId); r2.add("tarikh_Masuk",
			 * r.unquote("sysdate")); sql2 = r2.getSQLInsert("tblppkpemohon");
			 * stmtc.executeUpdate(sql2);
			 */

			// db = new Db();
			// Statement stmtc = db.getStatement();
			// SQLRenderer r2 = new SQLRenderer();
			r.clear();
			r.add("id_pemohon", idPemohon);
			if (jenis_pemohon.equals("2")) {
				r.add("no_kp_baru", no_kpbaru_pemohon);
				r.add("no_kp_lama", no_kplama_pemohon.toUpperCase());
				r.add("jenis_kp", sel_jeniskp_pemohon);
				r.add("no_kp_lain", no_kplain_pemohon.toUpperCase());
				r.add("umur", txtUmurPemohon);
				r.add("jantina", socJantinaPemohon);
				r.add("no_Hp", no_hp);
				

			} else {
				r.add("no_kp_baru", "");
				r.add("no_kp_lama", "");
				r.add("jenis_kp", "");
				r.add("no_kp_lain", "");
				r.add("umur", "");
				r.add("jantina", "");
				r.add("no_Hp", "");
			}
			r.add("nama_pemohon", nama_pemohon.toUpperCase());
			r.add("alamat_1", alamat1.toUpperCase());
			r.add("alamat_2", alamat2.toUpperCase());
			r.add("alamat_3", alamat3.toUpperCase());
			r.add("poskod", poskod);
			r.add("bandar", bandar);
			r.add("id_bandar", idbandar);
			r.add("id_negeri", negeri);
			r.add("alamat1_surat", alamat1.toUpperCase());
			r.add("alamat2_surat", alamat2.toUpperCase());
			r.add("alamat3_surat", alamat3.toUpperCase());
			r.add("poskod_surat", poskod);
			r.add("id_bandarsurat", idbandar);
			r.add("bandar_surat", bandar);
			r.add("id_negerisurat", negeri);
			r.add("no_tel", no_tel);
			r.add("id_tarafkptg", taraf_penting);
			r.add("status_pemohon", jenis_pemohon);
			r.add("id_arb", jenis_pej);
			
			r.add("id_saudara", socSaudaraWaris);

			// r2.add("id_permohonan", idPermohonan);
			r.add("id_masuk", userId);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			
			
			r.add("nama_pelbagainegara", nama_pelbagainegara);
			r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
			
			sql2 = r.getSQLInsert("tblppkpemohon");
			stmt.executeUpdate(sql2);

			// db = new Db();
			// Statement stmtd = db.getStatement();
			// SQLRenderer r3 = new SQLRenderer();
			r.clear();
			r.add("id_permohonan", idPermohonan);
			r.add("id_daerahmhn", no_daerah);
			r.add("id_negerimhn", idneg);
			r.add("id_pemohon", idPemohon);
			r.add("id_status", 8);
			r.add("flag_Jenis_Permohonan", 1);
			r.add("id_fail", idFail);
			r.add("seksyen", 8);
			r.add("no_subjaket", 0);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("tarikh_mohon", r.unquote(tarikh_mohon));
			r.add("id_unitpskawal", UserIdPejabat);
			r.add("id_masuk", userId);
			sql3 = r.getSQLInsert("tblppkpermohonan");
			stmt.executeUpdate(sql3);

			// baru

			// db = new Db();
			// Statement stmt8 = db.getStatement();
			// SQLRenderer r8 = new SQLRenderer();
			r.clear();
			r.add("id_permohonansimati", idP_mati);
			r.add("id_Simati", idsimati);
			r.add("id_Permohonan", idPermohonan);
			r.add("id_Masuk", userId);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			sql8 = r.getSQLInsert("tblppkpermohonansimati");
			stmt.executeUpdate(sql8);
			
			
			/*
			//sebelum ubah structure
			if (taraf_penting != "0" && taraf_penting != "" && adaob != "ada") {
			
				r.clear();
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_Hp", no_hp);
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_Hp", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", idP_mati);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", idbandar);
				r.add("poskod", poskod);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Pemohon", idPemohon);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", idbandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_arb", jenis_pej);
				r.add("id_saudara", socSaudaraWaris);
				sqlOB = r.getSQLInsert("tblppkob");				
				stmt.executeUpdate(sqlOB);
			}
			*/
			
			if (taraf_penting != "0" && taraf_penting != "" && adaob != "ada") {
				long id_ob = DB.getNextID(db, "TBLPPKOB_SEQ");
				Statement stmtOB = db.getStatement();
				SQLRenderer rOB = new SQLRenderer();
				rOB.clear();
				rOB.add("id_ob", id_ob);
				rOB.add("id_Simati", idsimati);
				rOB.add("nama_Ob", nama_pemohon);
				if (jenis_pemohon.equals("2")) {
					rOB.add("no_Kp_Baru", no_kpbaru_pemohon);
					rOB.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					rOB.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					rOB.add("jenis_Kp", sel_jeniskp_pemohon);
					rOB.add("umur", txtUmurPemohon);
					rOB.add("jantina", socJantinaPemohon);
					rOB.add("no_Hp", no_hp);
					rOB.add("status_ob", "1");
				} else {
					rOB.add("no_Kp_Baru", "");
					rOB.add("no_Kp_Lain", "");
					rOB.add("no_Kp_Lama", "");
					rOB.add("jenis_Kp", "");
					rOB.add("umur", "");
					rOB.add("jantina", "");
					rOB.add("no_Hp", "");
					rOB.add("status_ob", "");
				}
				rOB.add("id_Tarafkptg", taraf_penting);
				rOB.add("jenis_pemiutang", jenis_pemohon);
				rOB.add("id_Permohonansimati", idP_mati);
				rOB.add("no_Tel", no_tel);
				rOB.add("alamat_1", alamat1.toUpperCase());
				rOB.add("alamat_2", alamat2.toUpperCase());
				rOB.add("alamat_3", alamat3.toUpperCase());
				rOB.add("id_Bandar", idbandar);
				rOB.add("poskod", poskod);
				rOB.add("lapis", 1);
				rOB.add("status_hidup", 0);
				rOB.add("id_Pemohon", idPemohon);
				rOB.add("id_Negeri", negeri);
				rOB.add("id_Kemaskini", userId);
				rOB.add("tarikh_Kemaskini", r.unquote("sysdate"));
				rOB.add("id_Masuk", userId);
				rOB.add("tarikh_Masuk", r.unquote("sysdate"));
				rOB.add("alamat1_Surat", alamat1.toUpperCase());
				rOB.add("alamat2_Surat", alamat2.toUpperCase());
				rOB.add("alamat3_Surat", alamat3.toUpperCase());
				rOB.add("poskod_Surat", poskod);
				rOB.add("id_Bandarsurat", idbandar);
				rOB.add("id_Negerisurat", negeri);
				rOB.add("id_Arb", jenis_pej);				
				rOB.add("id_saudara", socSaudaraWaris);
				
				rOB.add("nama_pelbagainegara", nama_pelbagainegara);
				rOB.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				sqlOB = rOB.getSQLInsert("tblppkob");				
				stmtOB.executeUpdate(sqlOB);
				
				rOB.clear();
				rOB.add("id_ob", id_ob);
				rOB.add("id_Simati", idsimati);
				rOB.add("nama_Ob", nama_pemohon);
				if (jenis_pemohon.equals("2")) {
					rOB.add("no_Kp_Baru", no_kpbaru_pemohon);
					rOB.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					rOB.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					rOB.add("jenis_Kp", sel_jeniskp_pemohon);
					rOB.add("umur", txtUmurPemohon);
					rOB.add("jantina", socJantinaPemohon);
					rOB.add("no_Hp", no_hp);
					rOB.add("status_ob", "1");
				} else {
					rOB.add("no_Kp_Baru", "");
					rOB.add("no_Kp_Lain", "");
					rOB.add("no_Kp_Lama", "");
					rOB.add("jenis_Kp", "");
					rOB.add("umur", "");
					rOB.add("jantina", "");
					rOB.add("no_Hp", "");
					rOB.add("status_ob", "");
				}
				rOB.add("id_Tarafkptg", taraf_penting);
				rOB.add("jenis_pemiutang", jenis_pemohon);
				rOB.add("id_Permohonansimati", idP_mati);
				rOB.add("no_Tel", no_tel);

				rOB.add("alamat_1", alamat1.toUpperCase());
				rOB.add("alamat_2", alamat2.toUpperCase());
				rOB.add("alamat_3", alamat3.toUpperCase());
				rOB.add("id_Bandar", idbandar);
				rOB.add("poskod", poskod);
				rOB.add("lapis", 1);
				rOB.add("status_hidup", 0);
				rOB.add("id_Pemohon", idPemohon);
				rOB.add("id_Negeri", negeri);
				rOB.add("id_Kemaskini", userId);
				rOB.add("tarikh_Kemaskini", r.unquote("sysdate"));
				rOB.add("id_Masuk", userId);
				rOB.add("tarikh_Masuk", r.unquote("sysdate"));
				rOB.add("alamat1_Surat", alamat1.toUpperCase());
				rOB.add("alamat2_Surat", alamat2.toUpperCase());
				rOB.add("alamat3_Surat", alamat3.toUpperCase());
				rOB.add("poskod_Surat", poskod);
				rOB.add("id_Bandarsurat", idbandar);
				rOB.add("id_Negerisurat", negeri);
				rOB.add("id_Arb", jenis_pej);				
				rOB.add("id_saudara", socSaudaraWaris);
				
				
				rOB.add("nama_pelbagainegara", nama_pelbagainegara);
				rOB.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				
				
				sqlOB = rOB.getSQLInsert("tblppkobpermohonan");
				stmtOB.executeUpdate(sqlOB);
			}

			
			
			

			r.clear();
			r.add("id_bayaran", idBayaran);
			r.add("id_permohonan", idPermohonan);
			r.add("id_jenisbayaran", 2);

			String tr = "";
			if (txtbox != "") {
				r.add("no_resit", txtbox);
				tr = "to_date('" + tarikhresit + "','dd/MM/yyyy')";
				r.add("tarikh_bayaran", r.unquote(tr));
				r.add("jumlah_bayaran", 10);
			} else {
				r.add("no_resit", "");
				tr = "to_date('" + tarikhresit + "','dd/MM/yyyy')";
				r.add("tarikh_bayaran", "");
				r.add("jumlah_bayaran", 0);
			}

			r.add("ID_MASUK", userId);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			r.add("tarikh_masuk", r.unquote("sysdate"));
			sqlbayaran = r.getSQLInsert("tblppkbayaran");
			// System.out.println("sqlbayaran-->" + sqlbayaran);
			stmt.executeUpdate(sqlbayaran);

			conn.commit();
			//:::SUB	
			myLogger.info("SSF KEMASKINI 3");
			kemaskiniSubUrusanStatusFail(session,idPermohonan+"",userId,"8","340",idFail+"");

		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail Kutipan:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}

	}

	// ##
	public void addPermohonan17(HttpSession session,Hashtable data) throws Exception {
		Db db = null;
		Connection conn = null;

		String sqlOB = "";
		String sqlOBU = "";
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		String sql8 = "";
		String sql99 = "";
		String sqlbayaran = "";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			long idFail = DB.getNextID(db, "TBLPFDFAIL_SEQ");
			long idPemohon = DB.getNextID(db, "TBLPPKPEMOHON_SEQ");
			// long idsimati = DB.getNextID("TBLPPKSIMATI_SEQ");
			long idP_mati = DB.getNextID(db, "TBLPPKPERMOHONANSIMATI_SEQ");

			long idBayaran = DB.getNextID(db, "TBLPPKBAYARAN_SEQ");

			long idPermohonan = Long.parseLong((String) data
					.get("IdPermohonan"));

			// System.out.println("IDPPPPP" + idPermohonan);
			long idSubUrusanStatus = DB.getNextID(db,
					"TBLRUJSUBURUSANSTATUS_SEQ");

			int UserIdPejabat = Integer.parseInt((String) data
					.get("userIdPejabat"));

			String userIdNeg = (String) data.get("userIdNeg");
			String userId = (String) data.get("userId");
			String NegId = (String) data.get("negId");
			String id_Fail = (String) data.get("id_Fail");
			String no_subjaket = (String) data.get("no_subjaket");

			String txtNoFail = (String) data.get("txtNoFail");

			String bkp = (String) data.get("bkp");
			String lp = (String) data.get("lp");
			String bpa = (String) data.get("bpa");
			String lpa = (String) data.get("lpa");
			String ht = (String) data.get("ht");
			String lt = (String) data.get("lt");
			String idp_dulu = (String) data.get("idp_dulu");

			// System.out.println("no_subjaket :>>>>>" + no_subjaket);

			String idsimati = (String) data.get("idSimati");

			String id_Suburusanstatusfail = (String) data
					.get("id_Suburusanstatusfail");

			String no_daerah = (String) data.get("no_daerah");
			// System.out.println("IDPPPPP Daerah!!!!" + no_daerah);

			String negeri = (String) data.get("negeri");
			String no_kpbaru_pemohon = (String) data.get("no_kpbaru_pemohon");
			String no_kplama_pemohon = (String) data.get("no_kplama_pemohon");
			// String nama_simati = (String)data.get("nama_simati");
			String tarikh_masuk = (String) data.get("tarikh_masuk");
			// String no_kpbaru_simati = (String)data.get("no_kp_baru");
			// String no_kplama_simati = (String)data.get("no_kplama_simati");
			// String sel_jeniskp_simati =
			// (String)data.get("sel_jeniskp_simati");
			// String no_kplain_simati = (String)data.get("no_kplain_simati");

			String userIdKodDaerah = (String) data.get("userIdKodDaerah");

			// System.out.println("USER KOD DAERAH!!!::" + userIdKodDaerah);

			String userIdKodNegeri = (String) data.get("userIdKodNegeri");
			// String tarikh_simati = (String)data.get("tarikh_simati");
			String sel_jeniskp_pemohon = (String) data
					.get("sel_jeniskp_pemohon");
			String no_kplain_pemohon = (String) data.get("no_kplain_pemohon");
			String nama_pemohon = (String) data.get("nama_pemohon");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String bandar = (String) data.get("bandar");
			String poskod = (String) data.get("poskod");

			String buktimati = (String) data.get("buktimati");
			String sijilmati = (String) data.get("sijilmati");

			String txtUmurSimati = (String) data.get("txtUmurSimati");
			String socJantinaSimati = (String) data.get("socJantinaSimati");
			String txtUmurPemohon = (String) data.get("txtUmurPemohon");
			String socJantinaPemohon = (String) data.get("socJantinaPemohon");

			String txtNomborResitH = (String) data.get("txtNomborResitH");
			String txdTarikhByrnH = (String) data.get("txdTarikhByrnH");

			String no_tel = (String) data.get("no_tel");
			String no_hp = (String) data.get("no_hp");
			String jenis_pej = (String) data.get("jenis_pej");
			String taraf_penting = (String) data.get("taraf_penting");
			String jenis_pemohon = (String) data.get("jenis_pemohon");
			String adaob = (String) data.get("adaob");
			String socSaudaraWaris = (String) data.get("socSaudaraWaris");
			
			
			String nama_pelbagainegara = (String) data.get("nama_pelbagainegara");
			Vector cpw = checkpemohonwarislong(idPemohon);
			
			
			


			// System.out.println("sijilmatimodel::" + sijilmati);

			java.util.Calendar calendar = java.util.Calendar.getInstance();
			int getYear = calendar.get(java.util.Calendar.YEAR);

			// Azam remark here. it's suppose not to be called here.
			// String X = String.format("%04d", File.getSeqNo(db,2, 382, 0,
			// Integer
			// .parseInt(userIdNeg), Integer.parseInt(no_daerah), false,
			// false, getYear,0));

			if (no_daerah.length() < 1) {
				no_daerah = "0" + no_daerah;
			} else {
				no_daerah = no_daerah;
			}
			if (userIdNeg.length() < 1) {
				userIdNeg = "0" + userIdNeg;
			} else {
				userIdNeg = userIdNeg;
			}
			if (negeri.equals("")) {
				negeri = "0";
			}

			// System.out.println("USER KOD DAERAH MOHON!!!::" + no_daerah);

			int nod = Integer.parseInt(no_daerah);

			// FrmPrmhnnSek8InternalData.setDaerahbyID(nod);

			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("d.id_negeri");
			r.add("d.id_daerah");
			r.add("d.nama_daerah");
			r.add("d.kod_daerah");
			r.add("n.kod_negeri");

			r.add("d.id_negeri", r.unquote("n.id_Negeri"));
			r.add("d.id_daerah", nod);

			String sql12 = r.getSQLSelect("Tblrujdaerah d, Tblrujnegeri n");
			ResultSet rs12 = stmt.executeQuery(sql12);
			// Vector list = new Vector(;
			Hashtable h;
			// int bil = 1;
			String kod = "";
			String kodn = "";
			while (rs12.next()) {
				h = new Hashtable();
				// h.put("bil", bil);
				// h.put("id_Permohonan", rs.getString("id_Permohonan"));
				// h.put("id_Hta",
				// rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
				h.put("id_negeri", rs12.getString("id_negeri") == null ? ""
						: rs12.getString("id_negeri"));
				h.put("id_daerah", rs12.getString("id_daerah") == null ? ""
						: rs12.getString("id_daerah"));
				h.put("nama_daerah", rs12.getString("nama_daerah") == null ? ""
						: rs12.getString("nama_daerah"));
				h.put("kod_daerah", rs12.getString("kod_daerah") == null ? ""
						: rs12.getString("kod_daerah"));
				h.put("kod_negeri", rs12.getString("kod_negeri") == null ? ""
						: rs12.getString("kod_negeri"));

				// bil++;
				kod = rs12.getString("kod_daerah");
				kodn = rs12.getString("kod_negeri");
			}

			int jacket = 0;
			int jacket_count = 0;
			String jac = "";
			String nosubjacket = "";

			if (no_subjaket == "") {
				jacket = 1;
				jac = "" + jacket;

				// System.out.print("jac::" + jac);
			} else {
				jacket = Integer.parseInt(no_subjaket);
				jacket_count = jacket + 1;
				jac = "" + jacket_count;
			}

			// nosubjacket = "";
			// txtNoFail

			int f_l = txtNoFail.length();
			int s_b = no_subjaket.length();
			// System.out.println("FAIL LENGHT :" + f_l);

			String s = "";

			if (no_subjaket.equals("") || no_subjaket.equals("0")) {
				s = txtNoFail;
			} else {
				if (s_b == 1) {
					s = txtNoFail.substring(0, (f_l - 6));
				} else if (s_b == 2) {
					s = txtNoFail.substring(0, (f_l - 7));
				}

			}

			String getNoFile = s + "/S17-" + jac;
			// System.out.println("getNoFile:" + getNoFile);

			tarikh_masuk = (String) data.get("tarikh_masuk");
			// tarikh_simati = (String)data.get("tarikh_simati");
			String tarikh_mohon = "to_date('" + tarikh_masuk
					+ "','dd/MM/yyyy')";
			// String tarikh_mati = "to_date('" + tarikh_simati +
			// "','dd/MM/yyyy')";
			int idNeg = Integer.parseInt(NegId);

			// db = new Db();
			// Statement stmtA = db.getStatement();
			// SQLRenderer r = new SQLRenderer();
			r.clear();
			r.add("id_fail", idFail);
			r.add("id_seksyen", 2);
			r.add("id_urusan", 382);
			r.add("tarikh_daftar_fail", r.unquote("sysdate"));
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("tajuk_fail", getNoFile);
			r.add("no_fail", getNoFile);
			r.add("id_negeri", Integer.parseInt(userIdNeg));
			r.add("id_suburusan", 60);
			r.add("flag_fail", 1);
			r.add("id_masuk", userId);
			r.add("flag_Jenis_Fail", 1);
			sql = r.getSQLInsert("tblpfdfail");
			stmt.executeUpdate(sql);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String currentDate = dateFormat.format(date);

			// db = new Db();
			// Statement stmtc = db.getStatement();
			// SQLRenderer r2 = new SQLRenderer();
			r.clear();
			r.add("id_pemohon", idPemohon);
			if (jenis_pemohon.equals("2")) {
				r.add("no_kp_baru", no_kpbaru_pemohon);
				r.add("no_kp_lama", no_kplama_pemohon.toUpperCase());
				r.add("jenis_kp", sel_jeniskp_pemohon);
				r.add("no_kp_lain", no_kplain_pemohon.toUpperCase());
				r.add("umur", txtUmurPemohon);
				r.add("jantina", socJantinaPemohon);
				r.add("no_hp", no_hp);
			} else {
				r.add("no_kp_baru", "");
				r.add("no_kp_lama", "");
				r.add("jenis_kp", "");
				r.add("no_kp_lain", "");
				r.add("umur", "");
				r.add("jantina", "");
				r.add("no_hp", "");
			}

			r.add("id_arb", jenis_pej);
			r.add("nama_pemohon", nama_pemohon.toUpperCase());

			r.add("alamat_1", alamat1.toUpperCase());
			r.add("alamat_2", alamat2.toUpperCase());
			r.add("alamat_3", alamat3.toUpperCase());
			r.add("poskod", poskod);
			r.add("id_bandar", bandar);
			r.add("id_negeri", negeri);

			r.add("alamat1_surat", alamat1.toUpperCase());
			r.add("alamat2_surat", alamat2.toUpperCase());
			r.add("alamat3_surat", alamat3.toUpperCase());
			r.add("poskod_surat", poskod);
			// r2.add("bandar_surat",bandar);
			r.add("id_bandarsurat", bandar);
			r.add("id_negerisurat", negeri);

			r.add("no_tel", no_tel);
			r.add("id_tarafkptg", taraf_penting);
			r.add("status_pemohon", jenis_pemohon);
			r.add("id_saudara", socSaudaraWaris);

			r.add("id_masuk", userId);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			
			
			r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
			r.add("nama_pelbagainegara", nama_pelbagainegara);
			
			sql2 = r.getSQLInsert("tblppkpemohon");
			stmt.executeUpdate(sql2);

			// no_subjaket

			Vector id_n = null;
			id_n = getNegeriID(no_daerah);

			Hashtable nn = (Hashtable) id_n.get(0);
			nn.get("idnegeri").toString();

			// db = new Db();
			// Statement stmtd = db.getStatement();
			// SQLRenderer r3 = new SQLRenderer();
			r.clear();
			r.add("id_permohonan", idPermohonan);
			r.add("id_daerahmhn", no_daerah);
			r.add("id_negerimhn", nn.get("idnegeri").toString());
			r.add("id_pemohon", idPemohon);

			r.add("id_status", 8);
			r.add("flag_Jenis_Permohonan", 1);

			r.add("id_fail", idFail);
			r.add("seksyen", 17);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("tarikh_mohon", r.unquote(tarikh_mohon));
			r.add("no_subjaket", jac);
			r.add("id_unitpskawal", UserIdPejabat);
			r.add("id_masuk", userId);
			r.add("id_kemaskini", userId);

			r.add("batal_kuasa_pentadbir", bkp);
			r.add("lantik_pentadbir", lp);
			r.add("batal_p_amanah", bpa);
			r.add("lantik_p_amanah", lpa);
			r.add("harta_tinggal", ht);
			r.add("lain_tujuan", lt);
			r.add("id_permohonanterdahulu", idp_dulu);

			sql3 = r.getSQLInsert("tblppkpermohonan");
			stmt.executeUpdate(sql3);

			String sqlr = "";
			// db = new Db();
			// Statement stmtr = db.getStatement();
			// SQLRenderer rj = new SQLRenderer();
			r.clear();
			r.update("id_permohonan", idp_dulu);
			r.add("flag_permohonan", 1);
			sqlr = r.getSQLUpdate("tblppkpermohonan");
			stmt.executeUpdate(sqlr);

			// db = new Db();
			// Statement stmtM = db.getStatement();
			// SQLRenderer r77 = new SQLRenderer();
			r.clear();
			r.add("id_bayaran", idBayaran);
			r.add("id_permohonan", idPermohonan);
			r.add("id_jenisbayaran", 2);

			String tr = "";
			if (txtNomborResitH != "") {
				r.add("no_resit", txtNomborResitH);
				tr = "to_date('" + txdTarikhByrnH + "','dd/MM/yyyy')";
				r.add("tarikh_bayaran", r.unquote(tr));
				r.add("jumlah_bayaran", 10);
			} else {
				r.add("no_resit", "");
				tr = "to_date('" + txdTarikhByrnH + "','dd/MM/yyyy')";
				r.add("tarikh_bayaran", "");
				r.add("jumlah_bayaran", 0);
			}

			r.add("id_masuk", userId);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			sqlbayaran = r.getSQLInsert("tblppkbayaran");
			// System.out.println("sqlbayaran-->" + sqlbayaran);
			stmt.executeUpdate(sqlbayaran);
			/*
			 * db = new Db(); Statement stmt8 = db.getStatement(); SQLRenderer
			 * r8 = new SQLRenderer(); r8.add("id_Simati", idsimati);
			 * r8.add("id_Permohonan", idPermohonan); r8.add("id_Masuk",
			 * userId); r8.add("tarikh_Masuk", r.unquote("sysdate")); sql8 =
			 * r8.getSQLInsert("tblppkpermohonansimati");
			 * stmt8.executeUpdate(sql8);
			 */

			// db = new Db();
			// Statement stmt8 = db.getStatement();
			// SQLRenderer r8 = new SQLRenderer();
			r.clear();
			r.add("id_permohonansimati", idP_mati);
			r.add("id_Simati", idsimati);
			r.add("id_Permohonan", idPermohonan);
			r.add("id_Masuk", userId);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			sql8 = r.getSQLInsert("tblppkpermohonansimati");
			stmt.executeUpdate(sql8);

			//suburusanstatusfail_lama
			/*
			r.clear();
			r.update("ID_SUBURUSANSTATUSFAIL", id_Suburusanstatusfail);
			r.add("AKTIF", 0);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql99 = r.getSQLUpdate("tblrujsuburusanstatusfail");
			stmt.executeUpdate(sql99);

			
			r.add("ID_SUBURUSANSTATUSFAIL",
					DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", 430);
			r.add("ID_FAIL", idFail);
			r.add("AKTIF", 1);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql5 = r.getSQLInsert("tblrujsuburusanstatusfail");
			stmt.executeUpdate(sql5);*/
			
			if (taraf_penting != "0" && taraf_penting != "" && cpw.size() == 0) {
				// dbOB = new Db();
				// Statement stmtOB = db.getStatement();
				// SQLRenderer rOB = new SQLRenderer();
				long id_ob = 0;
				r.clear();
				id_ob = DB.getNextID(db,"TBLPPKOB_SEQ");
				r.add("id_ob", id_ob);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
					r.add("status_ob", "");
				}
				r.add("id_arb", jenis_pej);
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", idP_mati);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Pemohon", idPemohon);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_saudara", socSaudaraWaris);
				
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				
				sqlOB = r.getSQLInsert("tblppkob");
				stmt.executeUpdate(sqlOB);
				
				r.clear();
				r.add("id_ob", id_ob);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
					r.add("status_ob", "");
				}
				r.add("id_arb", jenis_pej);
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", idP_mati);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Pemohon", idPemohon);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_saudara", socSaudaraWaris);
				
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				
				sqlOB = r.getSQLInsert("tblppkobpermohonan");
				stmt.executeUpdate(sqlOB);
			}
			
			

			if (taraf_penting != "0" && taraf_penting != "" && cpw.size() > 0) {
				r.clear();
				r.update("id_Pemohon", idPemohon);
				r.add("id_Simati", idP_mati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
					r.add("status_ob", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", idP_mati);
				r.add("id_arb", jenis_pej);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_saudara", socSaudaraWaris);
				
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				
				sqlOBU = r.getSQLUpdate("tblppkob");
				stmt.executeUpdate(sqlOBU);	
				
				r.clear();
				
				
				
				
				String sqlPemohon = "";
				sqlPemohon = " SELECT ID_OB,LAPIS,STATUS_HIDUP FROM TBLPPKOB WHERE ID_PEMOHON = '"+idPemohon+"'";
				ResultSet rs1 = stmt.executeQuery(sqlPemohon);	
				String temp_ID_OB = "";
				String temp_LAPIS = "";
				String 				temp_STATUS_HIDUP = "";
				while (rs1.next()) {				
					temp_ID_OB = rs1.getString("ID_OB") == null ? "" : rs1.getString("ID_OB");
					temp_LAPIS = rs1.getString("LAPIS") == null ? "" : rs1.getString("LAPIS");
					temp_STATUS_HIDUP = rs1.getString("STATUS_HIDUP") == null ? "" : rs1.getString("STATUS_HIDUP");
				}
				
				
				
				r.add("id_ob", temp_ID_OB);
				r.add("id_Pemohon", idPemohon);
				r.add("id_Simati", idP_mati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");	
					r.add("status_ob", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", idP_mati);
				r.add("id_arb", jenis_pej);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				//r.add("lapis", 1);
				//r.add("status_hidup", 0);
				r.add("lapis",temp_LAPIS);
				r.add("status_hidup",temp_STATUS_HIDUP);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_saudara", socSaudaraWaris);
				
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				
				sqlOBU = r.getSQLInsert("tblppkobpermohonan");
				stmt.executeUpdate(sqlOBU);
				
				/*				
				
				r.clear();
				r.update("ID_FAIL", idFail);
				r.update("AKTIF", 1);
				r.add("AKTIF", 0);
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblrujsuburusanstatusfail");
				myLogger.info("CHECK ADD NEW 1:"+sql);
				stmt.executeUpdate(sql);		
				
				r.clear();
				r.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("ID_SUBURUSANSTATUS", 430);
				r.add("AKTIF", 1);
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("ID_KEMASKINI", userId);
				r.add("id_Fail", idFail);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			    sql1 = r.getSQLInsert("tblrujsuburusanstatusfail");
				
				myLogger.info("CHECK ADD NEW 2:"+sql1);
				stmt.executeUpdate(sql1);
				
				r.clear();
				r.update("ID_PERMOHONAN", idPermohonan);
				r.add("ID_STATUS", 8);
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));			
				sql2 = r.getSQLUpdate("tblppkpermohonan");
				myLogger.info("CHECK ADD NEW 3:"+sql2);
				stmt.executeUpdate(sql2);				
*/
				
				

			}
			//:::SUB
			conn.commit();
			myLogger.info("SSF KEMASKINI 4");
			kemaskiniSubUrusanStatusFail(session,idPermohonan+"",userId,"8","430",idFail+"");

		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
	}

	public void addPermohonan17PL(HttpSession session,Hashtable data) throws Exception {
		// Azam add transaction on 04.02.2010
		Connection conn = null;
		Db db = null;
		// Db dbOB = null;
		String sqlOB = "";
		// Db dbOBU = null;
		String sqlOBU = "";
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		String sql8 = "";
		String sql99 = "";
		String sqlbayaran = "";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			long idFail = DB.getNextID(db, "TBLPFDFAIL_SEQ");
			long idPemohon = DB.getNextID(db, "TBLPPKPEMOHON_SEQ");
			long idBayaran = DB.getNextID(db, "TBLPPKBAYARAN_SEQ");
			long idP_mati = DB.getNextID(db, "TBLPPKPERMOHONANSIMATI_SEQ");

			long idPermohonan = Long.parseLong((String) data
					.get("IdPermohonan"));

			// System.out.println("IDPPPPP" + idPermohonan);
			long idSubUrusanStatus = DB.getNextID(db,
					"TBLRUJSUBURUSANSTATUS_SEQ");

			int UserIdPejabat = Integer.parseInt((String) data
					.get("userIdPejabat"));
			String userIdNeg = (String) data.get("userIdNeg");
			String userId = (String) data.get("userId");
			String NegId = (String) data.get("negId");
			String id_Fail = (String) data.get("id_Fail");
			String no_subjaket = (String) data.get("no_subjaket");

			String txtNoFail = (String) data.get("txtNoFail");

			String bkp = (String) data.get("bkp");
			String lp = (String) data.get("lp");
			String bpa = (String) data.get("bpa");
			String lpa = (String) data.get("lpa");
			String ht = (String) data.get("ht");
			String lt = (String) data.get("lt");

			int cpwsize = 0;
			int cpobsize = 0;

			String idp_dulu = (String) data.get("idp_dulu");

			// id_ob
			String pemohonSimati = (String) data.get("pemohonSimati");
			// id_pemohon
			String idpemohonsimati = (String) data.get("idpemohonsimati");

			if (idpemohonsimati != "") {
				Vector cpw = checkpemohonwaris(idpemohonsimati);
				cpwsize = cpw.size();
			} else {
				cpwsize = 0;
			}
			if (pemohonSimati != "") {
				Vector cpob = checkwaris(pemohonSimati);
				cpobsize = cpob.size();
			} else {
				cpobsize = 0;

			}

			String idsimati = (String) data.get("idSimati");
			String id_Suburusanstatusfail = (String) data
					.get("id_Suburusanstatusfail");
			String no_daerah = (String) data.get("no_daerah");
			String negeri = (String) data.get("negeri");
			String no_kpbaru_pemohon = (String) data.get("no_kpbaru_pemohon");
			String no_kplama_pemohon = (String) data.get("no_kplama_pemohon");
			String tarikh_masuk = (String) data.get("tarikh_masuk");
			String userIdKodDaerah = (String) data.get("userIdKodDaerah");
			String userIdKodNegeri = (String) data.get("userIdKodNegeri");
			String sel_jeniskp_pemohon = (String) data
					.get("sel_jeniskp_pemohon");
			String no_kplain_pemohon = (String) data.get("no_kplain_pemohon");
			String nama_pemohon = (String) data.get("nama_pemohon");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String bandar = (String) data.get("bandar");
			String poskod = (String) data.get("poskod");
			String buktimati = (String) data.get("buktimati");
			String sijilmati = (String) data.get("sijilmati");
			String txtUmurSimati = (String) data.get("txtUmurSimati");
			String socJantinaSimati = (String) data.get("socJantinaSimati");
			String txtUmurPemohon = (String) data.get("txtUmurPemohon");
			String socJantinaPemohon = (String) data.get("socJantinaPemohon");
			String txtNomborResitH = (String) data.get("txtNomborResitH");
			String txdTarikhByrnH = (String) data.get("txdTarikhByrnH");
			String no_tel = (String) data.get("no_tel");
			String taraf_penting = (String) data.get("taraf_penting");
			String jenis_pemohon = (String) data.get("jenis_pemohon");
			String adaob = (String) data.get("adaob");
			String no_hp = (String) data.get("no_hp");
			String jenis_pej = (String) data.get("jenis_pej");
			String socSaudaraWaris = (String) data.get("socSaudaraWaris");
			
			String nama_pelbagainegara = (String) data.get("nama_pelbagainegara");
			
			
			java.util.Calendar calendar = java.util.Calendar.getInstance();
			int getYear = calendar.get(java.util.Calendar.YEAR);
			// String X = String.format("%04d", File.getSeqNo(db,2, 382, 0,
			// Integer
			// .parseInt(userIdNeg), Integer.parseInt(no_daerah), false,
			// false, getYear,0));

			if (no_daerah.length() < 1) {
				no_daerah = "0" + no_daerah;
			} else {
				no_daerah = no_daerah;
			}
			if (userIdNeg.length() < 1) {
				userIdNeg = "0" + userIdNeg;
			} else {
				userIdNeg = userIdNeg;
			}
			if (negeri.equals("")) {
				negeri = "0";
			}
			int nod = Integer.parseInt(no_daerah);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("d.id_negeri");
			r.add("d.id_daerah");
			r.add("d.nama_daerah");
			r.add("d.kod_daerah");
			r.add("n.kod_negeri");

			r.add("d.id_negeri", r.unquote("n.id_Negeri"));
			r.add("d.id_daerah", nod);

			String sql12 = r.getSQLSelect("Tblrujdaerah d, Tblrujnegeri n");
			ResultSet rs12 = stmt.executeQuery(sql12);
			// Vector list = new Vector(;
			Hashtable h;
			// int bil = 1;
			String kod = "";
			String kodn = "";
			while (rs12.next()) {
				h = new Hashtable();
				// h.put("bil", bil);
				// h.put("id_Permohonan", rs.getString("id_Permohonan"));
				// h.put("id_Hta",
				// rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
				h.put("id_negeri", rs12.getString("id_negeri") == null ? ""
						: rs12.getString("id_negeri"));
				h.put("id_daerah", rs12.getString("id_daerah") == null ? ""
						: rs12.getString("id_daerah"));
				h.put("nama_daerah", rs12.getString("nama_daerah") == null ? ""
						: rs12.getString("nama_daerah"));
				h.put("kod_daerah", rs12.getString("kod_daerah") == null ? ""
						: rs12.getString("kod_daerah"));
				h.put("kod_negeri", rs12.getString("kod_negeri") == null ? ""
						: rs12.getString("kod_negeri"));

				// bil++;
				kod = rs12.getString("kod_daerah");
				kodn = rs12.getString("kod_negeri");
			}

			int jacket = 0;
			int jacket_count = 0;
			String jac = "";
			String nosubjacket = "";

			if (no_subjaket.equals("") || no_subjaket.equals("0")) {
				jacket = 1;
				jac = "" + jacket;

				// System.out.print("jac::" + jac);
			} else {
				jacket = Integer.parseInt(no_subjaket);
				jacket_count = jacket + 1;
				jac = "" + jacket_count;
			}

			int f_l = txtNoFail.length();
			int s_b = no_subjaket.length();
			// System.out.println("FAIL LENGHT :" + f_l);

			String s = "";

			if (no_subjaket.equals("") || no_subjaket.equals("0")) {
				s = txtNoFail;
			} else {
				// s = txtNoFail.substring(0, (f_l - 6));
				if (s_b == 1) {
					s = txtNoFail.substring(0, (f_l - 6));
				} else if (s_b == 2) {
					s = txtNoFail.substring(0, (f_l - 7));
				}
			}

			String getNoFile = s + "/S17-" + jac;

			tarikh_masuk = (String) data.get("tarikh_masuk");

			String tarikh_mohon = "to_date('" + tarikh_masuk
					+ "','dd/MM/yyyy')";

			int idNeg = Integer.parseInt(NegId);

			// Statement stmt = db.getStatement();
			// SQLRenderer r = new SQLRenderer();
			r.clear();
			r.add("id_fail", idFail);
			r.add("id_seksyen", 2);
			r.add("id_urusan", 382);
			r.add("tarikh_daftar_fail", r.unquote("sysdate"));
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("tajuk_fail", getNoFile);
			r.add("no_fail", getNoFile);
			r.add("id_negeri", Integer.parseInt(userIdNeg));
			r.add("id_suburusan", 60);
			r.add("flag_fail", 1);
			r.add("id_masuk", userId);
			r.add("flag_Jenis_Fail", 1);
			sql = r.getSQLInsert("tblpfdfail");
			stmt.executeUpdate(sql);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String currentDate = dateFormat.format(date);

			if (idpemohonsimati != "") {
				// Statement stmtc = db.getStatement();
				// SQLRenderer r2 = new SQLRenderer();
				r.clear();
				r.update("id_pemohon", idpemohonsimati);
				if (jenis_pemohon.equals("2")) {
					r.add("no_kp_baru", no_kpbaru_pemohon);
					r.add("no_kp_lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_kp", sel_jeniskp_pemohon);
					r.add("no_kp_lain", no_kplain_pemohon.toUpperCase());
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
				} else {
					r.add("no_kp_baru", "");
					r.add("no_kp_lama", "");
					r.add("jenis_kp", "");
					r.add("no_kp_lain", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
				}

				r.add("id_arb", jenis_pej);
				r.add("nama_pemohon", nama_pemohon.toUpperCase());

				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("poskod", poskod);
				r.add("id_bandar", bandar);
				r.add("id_negeri", negeri);
				r.add("id_saudara", socSaudaraWaris);

				r.add("alamat1_surat", alamat1.toUpperCase());
				r.add("alamat2_surat", alamat2.toUpperCase());
				r.add("alamat3_surat", alamat3.toUpperCase());
				r.add("poskod_surat", poskod);
				// r.add("bandar_surat",bandar);
				r.add("id_bandarsurat", bandar);
				r.add("id_negerisurat", negeri);
				r.add("no_tel", no_tel);
				r.add("id_tarafkptg", taraf_penting);
				r.add("status_pemohon", jenis_pemohon);
				
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				r.add("nama_pelbagainegara", nama_pelbagainegara);

				r.add("id_kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				sql2 = r.getSQLUpdate("tblppkpemohon");
				stmt.executeUpdate(sql2);
			}
			if (idpemohonsimati == "") {

				// Statement stmtc = db.getStatement();
				// SQLRenderer r2 = new SQLRenderer();
				r.clear();
				r.add("id_pemohon", idPemohon);
				if (jenis_pemohon.equals("2")) {
					r.add("no_kp_baru", no_kpbaru_pemohon);
					r.add("no_kp_lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_kp", sel_jeniskp_pemohon);
					r.add("no_kp_lain", no_kplain_pemohon.toUpperCase());
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					
				} else {
					r.add("no_kp_baru", "");
					r.add("no_kp_lama", "");
					r.add("jenis_kp", "");
					r.add("no_kp_lain", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
				}

				r.add("id_arb", jenis_pej);
				r.add("nama_pemohon", nama_pemohon.toUpperCase());

				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("poskod", poskod);
				r.add("id_bandar", bandar);
				r.add("id_negeri", negeri);

				r.add("alamat1_surat", alamat1.toUpperCase());
				r.add("alamat2_surat", alamat2.toUpperCase());
				r.add("alamat3_surat", alamat3.toUpperCase());
				r.add("poskod_surat", poskod);
				// r.add("bandar_surat",bandar);
				r.add("id_bandarsurat", bandar);
				r.add("id_negerisurat", negeri);
				r.add("no_tel", no_tel);
				r.add("id_tarafkptg", taraf_penting);
				r.add("status_pemohon", jenis_pemohon);
				r.add("id_saudara", socSaudaraWaris);
				r.add("id_masuk", userId);
				r.add("tarikh_masuk", r.unquote("sysdate"));
				r.add("id_kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				
				
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				r.add("nama_pelbagainegara", nama_pelbagainegara);

				
				sql2 = r.getSQLInsert("tblppkpemohon");
				myLogger.info("SQL PEMOHON OB :" + sql2);
				stmt.executeUpdate(sql2);

			}

			// no_subjaket

			Vector id_n = null;
			id_n = getNegeriID(no_daerah);

			Hashtable nn = (Hashtable) id_n.get(0);
			nn.get("idnegeri").toString();

			// Statement stmtd = db.getStatement();
			// SQLRenderer r3 = new SQLRenderer();
			r.clear();
			r.add("id_permohonan", idPermohonan);
			r.add("id_daerahmhn", no_daerah);
			r.add("id_negerimhn", nn.get("idnegeri").toString());
			r.add("id_status", 8);
			r.add("flag_Jenis_Permohonan", 1);
			if (idpemohonsimati != "") {
				r.add("id_pemohon", idpemohonsimati);
			}
			if (idpemohonsimati == "") {
				r.add("id_pemohon", idPemohon);
			}

			r.add("id_fail", idFail);
			r.add("seksyen", 17);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("tarikh_mohon", r.unquote(tarikh_mohon));
			r.add("no_subjaket", jac);
			r.add("id_unitpskawal", UserIdPejabat);
			r.add("id_masuk", userId);
			r.add("id_kemaskini", userId);
			r.add("batal_kuasa_pentadbir", bkp);
			r.add("lantik_pentadbir", lp);
			r.add("batal_p_amanah", bpa);
			r.add("lantik_p_amanah", lpa);
			r.add("harta_tinggal", ht);
			r.add("lain_tujuan", lt);
			r.add("id_permohonanterdahulu", idp_dulu);
			sql3 = r.getSQLInsert("tblppkpermohonan");
			System.out.println("bkp===="+bkp);
			System.out.println("sql3===="+sql3);
			stmt.executeUpdate(sql3);

			String sqlr = "";
			// db = new Db();
			// Statement stmtr = db.getStatement();
			// SQLRenderer rj = new SQLRenderer();
			r.clear();
			r.update("id_permohonan", idp_dulu);
			r.add("flag_permohonan", 1);
			sqlr = r.getSQLUpdate("tblppkpermohonan");
			stmt.executeUpdate(sqlr);

			// db = new Db();
			// Statement stmtM = db.getStatement();
			// SQLRenderer r77 = new SQLRenderer();
			r.clear();
			r.add("id_bayaran", idBayaran);
			r.add("id_permohonan", idPermohonan);
			r.add("id_jenisbayaran", 2);

			String tr = "";
			if (txtNomborResitH != "") {
				r.add("no_resit", txtNomborResitH);
				tr = "to_date('" + txdTarikhByrnH + "','dd/MM/yyyy')";
				r.add("tarikh_bayaran", r.unquote(tr));
				r.add("jumlah_bayaran", 10);
			} else {
				r.add("no_resit", "");
				tr = "to_date('" + txdTarikhByrnH + "','dd/MM/yyyy')";
				r.add("tarikh_bayaran", "");
				r.add("jumlah_bayaran", 0);
			}

			r.add("id_masuk", userId);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			sqlbayaran = r.getSQLInsert("tblppkbayaran");
			stmt.executeUpdate(sqlbayaran);

			// Statement stmt8 = db.getStatement();
			// SQLRenderer r8 = new SQLRenderer();
			r.clear();
			r.add("id_permohonansimati", idP_mati);
			r.add("id_Simati", idsimati);
			r.add("id_Permohonan", idPermohonan);
			r.add("id_Masuk", userId);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			sql8 = r.getSQLInsert("tblppkpermohonansimati");
			stmt.executeUpdate(sql8);

			//suburusan lama
			/*
			r.clear();
			r.update("ID_SUBURUSANSTATUSFAIL", id_Suburusanstatusfail);
			r.add("AKTIF", 0);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql99 = r.getSQLUpdate("tblrujsuburusanstatusfail");
			stmt.executeUpdate(sql99);

			
			r.clear();
			r.add("ID_SUBURUSANSTATUSFAIL",
					DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", 430);
			r.add("ID_FAIL", idFail);
			r.add("AKTIF", 1);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql5 = r.getSQLInsert("tblrujsuburusanstatusfail");
			stmt.executeUpdate(sql5);*/
			
			
			
			
			long id_ob = 0;
			if (taraf_penting != "0" && taraf_penting != "" && cpwsize == 0
					&& idpemohonsimati != "") {
				r.clear();
				id_ob = DB.getNextID(db,"TBLPPKOB_SEQ");
				r.add("id_ob", id_ob);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
					r.add("status_ob", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", idP_mati);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Pemohon", idpemohonsimati);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_arb", jenis_pej);
				r.add("id_saudara", socSaudaraWaris);
				
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				r.add("nama_pelbagainegara", nama_pelbagainegara);

				
				
				sqlOB = r.getSQLInsert("tblppkob");
				stmt.executeUpdate(sqlOB);
				
				r.clear();
				//id_ob = DB.getNextID(db,"TBLPPKOB_SEQ");
				r.add("id_ob", id_ob);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
					r.add("status_ob", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", idP_mati);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Pemohon", idpemohonsimati);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_arb", jenis_pej);
				r.add("id_saudara", socSaudaraWaris);
				
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				r.add("nama_pelbagainegara", nama_pelbagainegara);

				
				sqlOB = r.getSQLInsert("tblppkobpermohonan");
				stmt.executeUpdate(sqlOB);
			}

			if (taraf_penting != "0" && taraf_penting != "" && cpwsize > 0
					&& idpemohonsimati != "") {

				r.clear();
				r.update("id_Pemohon", idpemohonsimati);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
					r.add("status_ob", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				// r.add("id_Permohonansimati",idP_mati);
				r.add("id_arb", jenis_pej);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				//r.add("lapis", 1);
				//r.add("status_hidup", 0);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_saudara", socSaudaraWaris);
				
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				r.add("nama_pelbagainegara", nama_pelbagainegara);

				
				
				sqlOBU = r.getSQLUpdate("tblppkob");
				stmt.executeUpdate(sqlOBU);
				
				String temp_status_hidup = "";
				String temp_lapis = "";
				sql1 = " SELECT status_hidup,lapis FROM TBLPPKOB WHERE ID_OB = '"+pemohonSimati+"' ";
				ResultSet rs1 = stmt.executeQuery(sql1);			
			    while (rs1.next()) {				
				temp_status_hidup =	rs1.getString("status_hidup") == null ? "" : rs1.getString("status_hidup");
				temp_lapis =	rs1.getString("lapis") == null ? "" : rs1.getString("lapis");
				}
				
				r.clear();
				r.add("id_ob",pemohonSimati);
				r.add("id_Pemohon", idpemohonsimati);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
					r.add("status_ob", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati",idP_mati);
				r.add("id_arb", jenis_pej);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);				
				r.add("lapis", temp_lapis);				
				r.add("status_hidup", temp_status_hidup);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_saudara", socSaudaraWaris);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				r.add("nama_pelbagainegara", nama_pelbagainegara);

				
				
				
				sqlOBU = r.getSQLInsert("tblppkobpermohonan");
				stmt.executeUpdate(sqlOBU);

			}

			if (taraf_penting != "0" && taraf_penting != "" && cpobsize > 0
					&& idpemohonsimati == "") {				
				r.clear();
				r.update("id_Ob", pemohonSimati);
				r.add("id_Pemohon", idPemohon);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
					r.add("status_ob", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				// r.add("id_Permohonansimati",idP_mati);
				r.add("id_arb", jenis_pej);

				r.add("no_Tel", no_tel);

				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				
				//r.add("lapis", 1);
				//r.add("status_hidup", 0);

				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));

				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_saudara", socSaudaraWaris);
				
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				r.add("nama_pelbagainegara", nama_pelbagainegara);


				sqlOBU = r.getSQLUpdate("tblppkob");
				stmt.executeUpdate(sqlOBU);
				
				
				
				String temp_status_hidup = "";
				String temp_lapis = "";
				sql1 = " SELECT status_hidup,lapis FROM TBLPPKOB WHERE ID_OB = '"+pemohonSimati+"' ";
				ResultSet rs1 = stmt.executeQuery(sql1);			
			    while (rs1.next()) {				
				temp_status_hidup =	rs1.getString("status_hidup") == null ? "" : rs1.getString("status_hidup");
				temp_lapis =	rs1.getString("lapis") == null ? "" : rs1.getString("lapis");
				}
				
				
				
				r.clear();
				r.add("id_Ob", pemohonSimati);
				r.add("id_Pemohon", idPemohon);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
					r.add("status_ob", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati",idP_mati);
				r.add("id_arb", jenis_pej);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				r.add("lapis", temp_lapis);
				r.add("status_hidup", temp_status_hidup);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));

				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_saudara", socSaudaraWaris);
				
				
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				r.add("nama_pelbagainegara", nama_pelbagainegara);

				sqlOBU = r.getSQLInsert("tblppkobpermohonan");
				stmt.executeUpdate(sqlOBU);
				
			}
			conn.commit();
			//:::SUB
			myLogger.info("SSF KEMASKINI 5");
			kemaskiniSubUrusanStatusFail(session,idPermohonan+"",userId,"8","430",idFail+"");

		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail :" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
	}
	
	
	public void addPermohonan17PL_online(HttpSession session,Hashtable data) throws Exception {
		// Azam add transaction on 04.02.2010
		Connection conn = null;
		Db db = null;
		// Db dbOB = null;
		String sqlOB = "";
		// Db dbOBU = null;
		String sqlOBU = "";
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		String sql8 = "";
		String sql99 = "";
		String sqlbayaran = "";
		
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		int getYear = calendar.get(java.util.Calendar.YEAR);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			long idFail = DB.getNextID(db, "TBLPFDFAIL_SEQ");
			long idPemohon = DB.getNextID(db, "TBLPPKPEMOHON_SEQ");
			long idBayaran = DB.getNextID(db, "TBLPPKBAYARAN_SEQ");
			long idP_mati = DB.getNextID(db, "TBLPPKPERMOHONANSIMATI_SEQ");

			long idPermohonan = Long.parseLong((String) data
					.get("IdPermohonan"));

			// System.out.println("IDPPPPP" + idPermohonan);
			long idSubUrusanStatus = DB.getNextID(db,
					"TBLRUJSUBURUSANSTATUS_SEQ");

			int UserIdPejabat = Integer.parseInt((String) data
					.get("userIdPejabat"));
			String userIdNeg = (String) data.get("userIdNeg");
			String userId = (String) data.get("userId");
			String NegId = (String) data.get("negId");
			String id_Fail = (String) data.get("id_Fail");
			String no_subjaket = (String) data.get("no_subjaket");

			String txtNoFail = (String) data.get("txtNoFail");

			String bkp = (String) data.get("bkp");
			String lp = (String) data.get("lp");
			String bpa = (String) data.get("bpa");
			String lpa = (String) data.get("lpa");
			String ht = (String) data.get("ht");
			String lt = (String) data.get("lt");

			int cpwsize = 0;
			int cpobsize = 0;

			String idp_dulu = (String) data.get("idp_dulu");

			// id_ob
			String pemohonSimati = (String) data.get("pemohonSimati");
			// id_pemohon
			String idpemohonsimati = (String) data.get("idpemohonsimati");

			if (idpemohonsimati != "") {
				Vector cpw = checkpemohonwaris(idpemohonsimati);
				cpwsize = cpw.size();
			} else {
				cpwsize = 0;
			}
			if (pemohonSimati != "") {
				Vector cpob = checkwaris(pemohonSimati);
				cpobsize = cpob.size();
			} else {
				cpobsize = 0;

			}

			String idsimati = (String) data.get("idSimati");
			String id_Suburusanstatusfail = (String) data
					.get("id_Suburusanstatusfail");
			String no_daerah = (String) data.get("no_daerah");
			String negeri = (String) data.get("negeri");
			String no_kpbaru_pemohon = (String) data.get("no_kpbaru_pemohon");
			String no_kplama_pemohon = (String) data.get("no_kplama_pemohon");
			String tarikh_masuk = (String) data.get("tarikh_masuk");
			String userIdKodDaerah = (String) data.get("userIdKodDaerah");
			String userIdKodNegeri = (String) data.get("userIdKodNegeri");
			//int idusernegeri = (Integer)data.get("useridnegeri");
			String sel_jeniskp_pemohon = (String) data
					.get("sel_jeniskp_pemohon");
			String no_kplain_pemohon = (String) data.get("no_kplain_pemohon");
			String nama_pemohon = (String) data.get("nama_pemohon");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String bandar = (String) data.get("bandar");
			String poskod = (String) data.get("poskod");
			String buktimati = (String) data.get("buktimati");
			String sijilmati = (String) data.get("sijilmati");
			String txtUmurSimati = (String) data.get("txtUmurSimati");
			String socJantinaSimati = (String) data.get("socJantinaSimati");
			String txtUmurPemohon = (String) data.get("txtUmurPemohon");
			String socJantinaPemohon = (String) data.get("socJantinaPemohon");
			String txtNomborResitH = (String) data.get("txtNomborResitH");
			String txdTarikhByrnH = (String) data.get("txdTarikhByrnH");
			String no_tel = (String) data.get("no_tel");
			
			String nama_pelbagainegara = (String) data.get("nama_pelbagainegara");
			
			String taraf_penting = (String) data.get("taraf_penting");
			String jenis_pemohon = (String) data.get("jenis_pemohon");
			String adaob = (String) data.get("adaob");
			String no_hp = (String) data.get("no_hp");
			String jenis_pej = (String) data.get("jenis_pej");
			
			String socSaudaraWaris = (String) data.get("socSaudaraWaris");
			
			
			
			
			
			
			
			
			// String X = String.format("%04d", File.getSeqNo(db,2, 382, 0,
			// Integer
			// .parseInt(userIdNeg), Integer.parseInt(no_daerah), false,
			// false, getYear,0));

			if (no_daerah.length() < 1) {
				no_daerah = "0" + no_daerah;
			} else {
				no_daerah = no_daerah;
			}
			if (userIdNeg.length() < 1) {
				userIdNeg = "0" + userIdNeg;
			} else {
				userIdNeg = userIdNeg;
			}
			if (negeri.equals("")) {
				negeri = "0";
			}
			int nod = Integer.parseInt(no_daerah);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("d.id_negeri");
			r.add("d.id_daerah");
			r.add("d.nama_daerah");
			r.add("d.kod_daerah");
			r.add("n.kod_negeri");

			r.add("d.id_negeri", r.unquote("n.id_Negeri"));
			r.add("d.id_daerah", nod);

			String sql12 = r.getSQLSelect("Tblrujdaerah d, Tblrujnegeri n");
			ResultSet rs12 = stmt.executeQuery(sql12);
			// Vector list = new Vector(;
			Hashtable h;
			// int bil = 1;
			String kod = "";
			String kodn = "";
			while (rs12.next()) {
				h = new Hashtable();
				// h.put("bil", bil);
				// h.put("id_Permohonan", rs.getString("id_Permohonan"));
				// h.put("id_Hta",
				// rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
				h.put("id_negeri", rs12.getString("id_negeri") == null ? ""
						: rs12.getString("id_negeri"));
				h.put("id_daerah", rs12.getString("id_daerah") == null ? ""
						: rs12.getString("id_daerah"));
				h.put("nama_daerah", rs12.getString("nama_daerah") == null ? ""
						: rs12.getString("nama_daerah"));
				h.put("kod_daerah", rs12.getString("kod_daerah") == null ? ""
						: rs12.getString("kod_daerah"));
				h.put("kod_negeri", rs12.getString("kod_negeri") == null ? ""
						: rs12.getString("kod_negeri"));

				// bil++;
				kod = rs12.getString("kod_daerah");
				kodn = rs12.getString("kod_negeri");
			}

			int jacket = 0;
			int jacket_count = 0;
			String jac = "";
			String nosubjacket = "";

			if (no_subjaket.equals("") || no_subjaket.equals("0")) {
				jacket = 1;
				jac = "" + jacket;

				// System.out.print("jac::" + jac);
			} else {
				jacket = Integer.parseInt(no_subjaket);
				jacket_count = jacket + 1;
				jac = "" + jacket_count;
			}

			int f_l = txtNoFail.length();
			int s_b = no_subjaket.length();
			// System.out.println("FAIL LENGHT :" + f_l);

			String s = "";

			if (no_subjaket.equals("") || no_subjaket.equals("0")) {
				s = txtNoFail;
			} else {
				// s = txtNoFail.substring(0, (f_l - 6));
				if (s_b == 1) {
					s = txtNoFail.substring(0, (f_l - 6));
				} else if (s_b == 2) {
					s = txtNoFail.substring(0, (f_l - 7));
				}
			}

			String getNoFile = s + "/S17-" + jac;

			tarikh_masuk = (String) data.get("tarikh_masuk");

			String tarikh_mohon = "to_date('" + tarikh_masuk
					+ "','dd/MM/yyyy')";

			int idNeg = Integer.parseInt(NegId);

			// Statement stmt = db.getStatement();
			// SQLRenderer r = new SQLRenderer();
			r.clear();
			r.add("id_fail", idFail);
			r.add("id_seksyen", 2);
			r.add("id_urusan", 382);
			r.add("tarikh_daftar_fail", r.unquote("sysdate"));
			r.add("tarikh_masuk", r.unquote("sysdate"));
			//r.add("tajuk_fail", getNoFile);
			//r.add("no_fail", getNoFile);
			r.add("id_negeri", Integer.parseInt(userIdNeg));
			r.add("id_suburusan", 60);
			r.add("flag_fail", 1);
			r.add("id_masuk", userId);
			//r.add("flag_Jenis_Fail", 1);
			r.add("id_status",7);
			sql = r.getSQLInsert("tblpfdfail");
			stmt.executeUpdate(sql);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String currentDate = dateFormat.format(date);

			if (idpemohonsimati != "") {
				// Statement stmtc = db.getStatement();
				// SQLRenderer r2 = new SQLRenderer();
				r.clear();
				r.update("id_pemohon", idpemohonsimati);
				if (jenis_pemohon.equals("2")) {
					r.add("no_kp_baru", no_kpbaru_pemohon);
					r.add("no_kp_lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_kp", sel_jeniskp_pemohon);
					r.add("no_kp_lain", no_kplain_pemohon.toUpperCase());
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
				} else {
					r.add("no_kp_baru", "");
					r.add("no_kp_lama", "");
					r.add("jenis_kp", "");
					r.add("no_kp_lain", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
				}

				r.add("id_arb", jenis_pej);
				r.add("nama_pemohon", nama_pemohon.toUpperCase());

				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("poskod", poskod);
				r.add("id_bandar", bandar);
				r.add("id_negeri", negeri);

				r.add("alamat1_surat", alamat1.toUpperCase());
				r.add("alamat2_surat", alamat2.toUpperCase());
				r.add("alamat3_surat", alamat3.toUpperCase());
				r.add("poskod_surat", poskod);
				// r.add("bandar_surat",bandar);
				r.add("id_bandarsurat", bandar);
				r.add("id_negerisurat", negeri);
				r.add("no_tel", no_tel);
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				r.add("id_tarafkptg", taraf_penting);
				r.add("status_pemohon", jenis_pemohon);
				
				r.add("id_saudara", socSaudaraWaris);
				
				

				r.add("id_kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				sql2 = r.getSQLUpdate("tblppkpemohon");
				stmt.executeUpdate(sql2);
			}
			if (idpemohonsimati == "") {

				// Statement stmtc = db.getStatement();
				// SQLRenderer r2 = new SQLRenderer();
				r.clear();
				r.add("id_pemohon", idPemohon);
				if (jenis_pemohon.equals("2")) {
					r.add("no_kp_baru", no_kpbaru_pemohon);
					r.add("no_kp_lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_kp", sel_jeniskp_pemohon);
					r.add("no_kp_lain", no_kplain_pemohon.toUpperCase());
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
				} else {
					r.add("no_kp_baru", "");
					r.add("no_kp_lama", "");
					r.add("jenis_kp", "");
					r.add("no_kp_lain", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
				}

				r.add("id_arb", jenis_pej);
				r.add("nama_pemohon", nama_pemohon.toUpperCase());

				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("poskod", poskod);
				r.add("id_bandar", bandar);
				r.add("id_negeri", negeri);

				r.add("alamat1_surat", alamat1.toUpperCase());
				r.add("alamat2_surat", alamat2.toUpperCase());
				r.add("alamat3_surat", alamat3.toUpperCase());
				r.add("poskod_surat", poskod);
				// r.add("bandar_surat",bandar);
				r.add("id_bandarsurat", bandar);
				r.add("id_negerisurat", negeri);
				r.add("no_tel", no_tel);
				r.add("id_tarafkptg", taraf_penting);
				r.add("status_pemohon", jenis_pemohon);
				r.add("id_masuk", userId);
				r.add("tarikh_masuk", r.unquote("sysdate"));
				r.add("id_kemaskini", userId);
				
				r.add("id_saudara", socSaudaraWaris);
				
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				
				sql2 = r.getSQLInsert("tblppkpemohon");
				myLogger.info("SQL PEMOHON OB :" + sql2);
				stmt.executeUpdate(sql2);

			}

			// no_subjaket

			Vector id_n = null;
			id_n = getNegeriID(no_daerah);

			Hashtable nn = (Hashtable) id_n.get(0);
			nn.get("idnegeri").toString();
			
			//String noFail_online = String.format("%06d",File.getSeqNo(2,02,382,0,Integer.parseInt(nn.get("idnegeri").toString()),false,false));
			//String X = "JKPTG/PK/02/"+getYear+"/"+noFail_online;
			String X = "";

			// Statement stmtd = db.getStatement();
			// SQLRenderer r3 = new SQLRenderer();
			r.clear();
			r.add("id_permohonan", idPermohonan);
			r.add("id_daerahmhn", no_daerah);
			r.add("id_negerimhn", nn.get("idnegeri").toString());
			//r.add("id_status", 160);
			r.add("flag_Jenis_Permohonan", 0);
			if (idpemohonsimati != "") {
				r.add("id_pemohon", idpemohonsimati);
			}
			if (idpemohonsimati == "") {
				r.add("id_pemohon", idPemohon);
			}
			
			r.add("NO_PERMOHONAN_ONLINE",X);

			r.add("id_fail", idFail);
			r.add("seksyen", 17);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			//r.add("tarikh_mohon", r.unquote(tarikh_mohon));
			r.add("no_subjaket", jac);
			r.add("id_unitpskawal", UserIdPejabat);
			r.add("id_masuk", userId);
			r.add("batal_kuasa_pentadbir", bkp);
			r.add("lantik_pentadbir", lp);
			r.add("batal_p_amanah", bpa);
			r.add("lantik_p_amanah", lpa);
			r.add("harta_tinggal", ht);
			r.add("lain_tujuan", lt);
			r.add("id_permohonanterdahulu", idp_dulu);
			sql3 = r.getSQLInsert("tblppkpermohonan");
			stmt.executeUpdate(sql3);

			String sqlr = "";
			// db = new Db();
			// Statement stmtr = db.getStatement();
			// SQLRenderer rj = new SQLRenderer();
			r.clear();
			r.update("id_permohonan", idp_dulu);
			r.add("flag_permohonan", 1);
			sqlr = r.getSQLUpdate("tblppkpermohonan");
			stmt.executeUpdate(sqlr);

			// db = new Db();
			// Statement stmtM = db.getStatement();
			// SQLRenderer r77 = new SQLRenderer();
			r.clear();
			r.add("id_bayaran", idBayaran);
			r.add("id_permohonan", idPermohonan);
			r.add("id_jenisbayaran", 2);

			String tr = "";
			if (txtNomborResitH != "") {
				r.add("no_resit", txtNomborResitH);
				tr = "to_date('" + txdTarikhByrnH + "','dd/MM/yyyy')";
				r.add("tarikh_bayaran", r.unquote(tr));
				r.add("jumlah_bayaran", 10);
			} else {
				r.add("no_resit", "");
				tr = "to_date('" + txdTarikhByrnH + "','dd/MM/yyyy')";
				r.add("tarikh_bayaran", "");
				r.add("jumlah_bayaran", 0);
			}

			r.add("id_masuk", userId);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			//sqlbayaran = r.getSQLInsert("tblppkbayaran");
			//stmt.executeUpdate(sqlbayaran);

			// Statement stmt8 = db.getStatement();
			// SQLRenderer r8 = new SQLRenderer();
			r.clear();
			r.add("id_permohonansimati", idP_mati);
			r.add("id_Simati", idsimati);
			r.add("id_Permohonan", idPermohonan);
			r.add("id_Masuk", userId);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			sql8 = r.getSQLInsert("tblppkpermohonansimati");
			stmt.executeUpdate(sql8);

			/*
			r.clear();
			r.update("ID_SUBURUSANSTATUSFAIL", id_Suburusanstatusfail);
			r.add("AKTIF", 0);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql99 = r.getSQLUpdate("tblrujsuburusanstatusfail");
			stmt.executeUpdate(sql99);

			r.clear();
			r.add("ID_SUBURUSANSTATUSFAIL",
					DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", 464);
			r.add("ID_FAIL", idFail);
			r.add("AKTIF", 1);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql5 = r.getSQLInsert("tblrujsuburusanstatusfail");
			stmt.executeUpdate(sql5);*/
			
			

			//belum tukar structure
			/*
			if (taraf_penting != "0" && taraf_penting != "" && cpwsize == 0
					&& idpemohonsimati != "") {
				
				r.clear();
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", idP_mati);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Pemohon", idpemohonsimati);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_arb", jenis_pej);
				r.add("id_saudara", socSaudaraWaris);

				sqlOB = r.getSQLInsert("tblppkob");
				// System.out.println(sqlOB);
				stmt.executeUpdate(sqlOB);
			}

			if (taraf_penting != "0" && taraf_penting != "" && cpwsize > 0
					&& idpemohonsimati != "") {

				// dbOBU = new Db();
				// Statement stmtOBU = db.getStatement();
				// SQLRenderer rOBU = new SQLRenderer();
				r.clear();
				r.update("id_Pemohon", idpemohonsimati);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				// r.add("id_Permohonansimati",idP_mati);
				r.add("id_arb", jenis_pej);

				r.add("no_Tel", no_tel);

				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				r.add("lapis", 1);
				r.add("status_hidup", 0);

				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));

				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				
				r.add("id_saudara", socSaudaraWaris);

				sqlOBU = r.getSQLUpdate("tblppkob");
				stmt.executeUpdate(sqlOBU);

			}

			if (taraf_penting != "0" && taraf_penting != "" && cpobsize > 0
					&& idpemohonsimati == "") {

				// dbOBU = new Db();
				// Statement stmtOBU = db.getStatement();
				// SQLRenderer rOBU = new SQLRenderer();

				// dsfsdfsd
				r.clear();
				r.update("id_Ob", pemohonSimati);
				r.add("id_Pemohon", idPemohon);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				// r.add("id_Permohonansimati",idP_mati);
				r.add("id_arb", jenis_pej);

				r.add("no_Tel", no_tel);

				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				r.add("lapis", 1);
				r.add("status_hidup", 0);

				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));

				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				
				r.add("id_saudara", socSaudaraWaris);

				sqlOBU = r.getSQLUpdate("tblppkob");
				stmt.executeUpdate(sqlOBU);
			}
			*/
			long id_ob = 0;
			if (taraf_penting != "0" && taraf_penting != "" && cpwsize == 0
					&& idpemohonsimati != "") {
				r.clear();
				id_ob = DB.getNextID(db,"TBLPPKOB_SEQ");
				r.add("id_ob", id_ob);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
					r.add("status_ob", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", idP_mati);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Pemohon", idpemohonsimati);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_arb", jenis_pej);
				r.add("id_saudara", socSaudaraWaris);
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				sqlOB = r.getSQLInsert("tblppkob");
				stmt.executeUpdate(sqlOB);
				
				r.clear();
				//id_ob = DB.getNextID(db,"TBLPPKOB_SEQ");
				r.add("id_ob", id_ob);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
					r.add("status_ob", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", idP_mati);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Pemohon", idpemohonsimati);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_arb", jenis_pej);
				r.add("id_saudara", socSaudaraWaris);
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				
				sqlOB = r.getSQLInsert("tblppkobpermohonan");
				stmt.executeUpdate(sqlOB);
			}
			
			if (taraf_penting != "0" && taraf_penting != "" && cpwsize > 0
					&& idpemohonsimati != "") {
			
				r.clear();
				r.update("id_Pemohon", idpemohonsimati);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
					r.add("status_ob", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				// r.add("id_Permohonansimati",idP_mati);
				r.add("id_arb", jenis_pej);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				//r.add("lapis", 1);
				//r.add("status_hidup", 0);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_saudara", socSaudaraWaris);
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				sqlOBU = r.getSQLUpdate("tblppkob");
				stmt.executeUpdate(sqlOBU);
				
				String temp_status_hidup = "";
				String temp_lapis = "";
				sql1 = " SELECT status_hidup,lapis FROM TBLPPKOB WHERE ID_OB = '"+pemohonSimati+"' ";
				ResultSet rs1 = stmt.executeQuery(sql1);			
			    while (rs1.next()) {				
				temp_status_hidup =	rs1.getString("status_hidup") == null ? "" : rs1.getString("status_hidup");
				temp_lapis =	rs1.getString("lapis") == null ? "" : rs1.getString("lapis");
				}
				
				r.clear();
				r.add("id_ob",pemohonSimati);
				r.add("id_Pemohon", idpemohonsimati);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
					r.add("status_ob", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati",idP_mati);
				r.add("id_arb", jenis_pej);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);				
				r.add("lapis", temp_lapis);				
				r.add("status_hidup", temp_status_hidup);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_saudara", socSaudaraWaris);
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				sqlOBU = r.getSQLInsert("tblppkobpermohonan");
				stmt.executeUpdate(sqlOBU);
			
			}
			
			if (taraf_penting != "0" && taraf_penting != "" && cpobsize > 0
					&& idpemohonsimati == "") {				
				r.clear();
				r.update("id_Ob", pemohonSimati);
				r.add("id_Pemohon", idPemohon);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
					r.add("status_ob", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				// r.add("id_Permohonansimati",idP_mati);
				r.add("id_arb", jenis_pej);
			
				r.add("no_Tel", no_tel);
			
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				
				//r.add("lapis", 1);
				//r.add("status_hidup", 0);
			
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
			
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_saudara", socSaudaraWaris);
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
			
				sqlOBU = r.getSQLUpdate("tblppkob");
				stmt.executeUpdate(sqlOBU);
				
				
				
				String temp_status_hidup = "";
				String temp_lapis = "";
				sql1 = " SELECT status_hidup,lapis FROM TBLPPKOB WHERE ID_OB = '"+pemohonSimati+"' ";
				ResultSet rs1 = stmt.executeQuery(sql1);			
			    while (rs1.next()) {				
				temp_status_hidup =	rs1.getString("status_hidup") == null ? "" : rs1.getString("status_hidup");
				temp_lapis =	rs1.getString("lapis") == null ? "" : rs1.getString("lapis");
				}
				
				
				
				r.clear();
				r.add("id_Ob", pemohonSimati);
				r.add("id_Pemohon", idPemohon);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
					r.add("status_ob", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati",idP_mati);
				r.add("id_arb", jenis_pej);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				r.add("lapis", temp_lapis);
				r.add("status_hidup", temp_status_hidup);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
			
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_saudara", socSaudaraWaris);
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				
				sqlOBU = r.getSQLInsert("tblppkobpermohonan");
				stmt.executeUpdate(sqlOBU);
				
			}

			
			
			conn.commit();
			//:::SUB				
			
			myLogger.info("SSF KEMASKINI 6");
			kemaskiniSubUrusanStatusFail(session,idPermohonan+"",userId,"160","464",idFail+"");
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail :" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
	}
	
	public void addPermohonan17_online(HttpSession session,Hashtable data) throws Exception {
		Db db = null;
		Connection conn = null;

		String sqlOB = "";
		String sqlOBU = "";
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		String sql8 = "";
		String sql99 = "";
		String sqlbayaran = "";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			long idFail = DB.getNextID(db, "TBLPFDFAIL_SEQ");
			long idPemohon = DB.getNextID(db, "TBLPPKPEMOHON_SEQ");
			// long idsimati = DB.getNextID("TBLPPKSIMATI_SEQ");
			long idP_mati = DB.getNextID(db, "TBLPPKPERMOHONANSIMATI_SEQ");

			long idBayaran = DB.getNextID(db, "TBLPPKBAYARAN_SEQ");

			long idPermohonan = Long.parseLong((String) data
					.get("IdPermohonan"));

			// System.out.println("IDPPPPP" + idPermohonan);
			long idSubUrusanStatus = DB.getNextID(db,
					"TBLRUJSUBURUSANSTATUS_SEQ");

			int UserIdPejabat = Integer.parseInt((String) data
					.get("userIdPejabat"));

			String userIdNeg = (String) data.get("userIdNeg");
			String userId = (String) data.get("userId");
			String NegId = (String) data.get("negId");
			String id_Fail = (String) data.get("id_Fail");
			String no_subjaket = (String) data.get("no_subjaket");

			String txtNoFail = (String) data.get("txtNoFail");

			String bkp = (String) data.get("bkp");
			String lp = (String) data.get("lp");
			String bpa = (String) data.get("bpa");
			String lpa = (String) data.get("lpa");
			String ht = (String) data.get("ht");
			String lt = (String) data.get("lt");
			String idp_dulu = (String) data.get("idp_dulu");

			// System.out.println("no_subjaket :>>>>>" + no_subjaket);

			String idsimati = (String) data.get("idSimati");

			String id_Suburusanstatusfail = (String) data
					.get("id_Suburusanstatusfail");

			String no_daerah = (String) data.get("no_daerah");
			// System.out.println("IDPPPPP Daerah!!!!" + no_daerah);

			String negeri = (String) data.get("negeri");
			String no_kpbaru_pemohon = (String) data.get("no_kpbaru_pemohon");
			String no_kplama_pemohon = (String) data.get("no_kplama_pemohon");
			// String nama_simati = (String)data.get("nama_simati");
			String tarikh_masuk = (String) data.get("tarikh_masuk");
			// String no_kpbaru_simati = (String)data.get("no_kp_baru");
			// String no_kplama_simati = (String)data.get("no_kplama_simati");
			// String sel_jeniskp_simati =
			// (String)data.get("sel_jeniskp_simati");
			// String no_kplain_simati = (String)data.get("no_kplain_simati");

			String userIdKodDaerah = (String) data.get("userIdKodDaerah");

			// System.out.println("USER KOD DAERAH!!!::" + userIdKodDaerah);

			String userIdKodNegeri = (String) data.get("userIdKodNegeri");
			// String tarikh_simati = (String)data.get("tarikh_simati");
			String sel_jeniskp_pemohon = (String) data
					.get("sel_jeniskp_pemohon");
			String no_kplain_pemohon = (String) data.get("no_kplain_pemohon");
			String nama_pemohon = (String) data.get("nama_pemohon");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String bandar = (String) data.get("bandar");
			String poskod = (String) data.get("poskod");

			String buktimati = (String) data.get("buktimati");
			String sijilmati = (String) data.get("sijilmati");

			String txtUmurSimati = (String) data.get("txtUmurSimati");
			String socJantinaSimati = (String) data.get("socJantinaSimati");
			String txtUmurPemohon = (String) data.get("txtUmurPemohon");
			String socJantinaPemohon = (String) data.get("socJantinaPemohon");

			String txtNomborResitH = (String) data.get("txtNomborResitH");
			String txdTarikhByrnH = (String) data.get("txdTarikhByrnH");

			String no_tel = (String) data.get("no_tel");
			
			
			String nama_pelbagainegara = (String) data.get("nama_pelbagainegara");
			
			String no_hp = (String) data.get("no_hp");
			String jenis_pej = (String) data.get("jenis_pej");
			String taraf_penting = (String) data.get("taraf_penting");
			String jenis_pemohon = (String) data.get("jenis_pemohon");
			String adaob = (String) data.get("adaob");

			String socSaudaraWaris = (String) data.get("socSaudaraWaris");




			Vector cpw = checkpemohonwarislong(idPemohon);

			// System.out.println("sijilmatimodel::" + sijilmati);

			java.util.Calendar calendar = java.util.Calendar.getInstance();
			int getYear = calendar.get(java.util.Calendar.YEAR);

			// Azam remark here. it's suppose not to be called here.
			// String X = String.format("%04d", File.getSeqNo(db,2, 382, 0,
			// Integer
			// .parseInt(userIdNeg), Integer.parseInt(no_daerah), false,
			// false, getYear,0));

			if (no_daerah.length() < 1) {
				no_daerah = "0" + no_daerah;
			} else {
				no_daerah = no_daerah;
			}
			if (userIdNeg.length() < 1) {
				userIdNeg = "0" + userIdNeg;
			} else {
				userIdNeg = userIdNeg;
			}
			if (negeri.equals("")) {
				negeri = "0";
			}

			// System.out.println("USER KOD DAERAH MOHON!!!::" + no_daerah);

			int nod = Integer.parseInt(no_daerah);

			// FrmPrmhnnSek8InternalData.setDaerahbyID(nod);

			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("d.id_negeri");
			r.add("d.id_daerah");
			r.add("d.nama_daerah");
			r.add("d.kod_daerah");
			r.add("n.kod_negeri");

			r.add("d.id_negeri", r.unquote("n.id_Negeri"));
			r.add("d.id_daerah", nod);

			String sql12 = r.getSQLSelect("Tblrujdaerah d, Tblrujnegeri n");
			ResultSet rs12 = stmt.executeQuery(sql12);
			// Vector list = new Vector(;
			Hashtable h;
			// int bil = 1;
			String kod = "";
			String kodn = "";
			while (rs12.next()) {
				h = new Hashtable();
				// h.put("bil", bil);
				// h.put("id_Permohonan", rs.getString("id_Permohonan"));
				// h.put("id_Hta",
				// rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
				h.put("id_negeri", rs12.getString("id_negeri") == null ? ""
						: rs12.getString("id_negeri"));
				h.put("id_daerah", rs12.getString("id_daerah") == null ? ""
						: rs12.getString("id_daerah"));
				h.put("nama_daerah", rs12.getString("nama_daerah") == null ? ""
						: rs12.getString("nama_daerah"));
				h.put("kod_daerah", rs12.getString("kod_daerah") == null ? ""
						: rs12.getString("kod_daerah"));
				h.put("kod_negeri", rs12.getString("kod_negeri") == null ? ""
						: rs12.getString("kod_negeri"));

				// bil++;
				kod = rs12.getString("kod_daerah");
				kodn = rs12.getString("kod_negeri");
			}

			int jacket = 0;
			int jacket_count = 0;
			String jac = "";
			String nosubjacket = "";

			if (no_subjaket == "") {
				jacket = 1;
				jac = "" + jacket;

				// System.out.print("jac::" + jac);
			} else {
				jacket = Integer.parseInt(no_subjaket);
				jacket_count = jacket + 1;
				jac = "" + jacket_count;
			}

			// nosubjacket = "";
			// txtNoFail

			int f_l = txtNoFail.length();
			int s_b = no_subjaket.length();
			// System.out.println("FAIL LENGHT :" + f_l);

			String s = "";

			if (no_subjaket.equals("") || no_subjaket.equals("0")) {
				s = txtNoFail;
			} else {
				if (s_b == 1) {
					s = txtNoFail.substring(0, (f_l - 6));
				} else if (s_b == 2) {
					s = txtNoFail.substring(0, (f_l - 7));
				}

			}

			String getNoFile = s + "/S17-" + jac;
			// System.out.println("getNoFile:" + getNoFile);

			tarikh_masuk = (String) data.get("tarikh_masuk");
			// tarikh_simati = (String)data.get("tarikh_simati");
			String tarikh_mohon = "to_date('" + tarikh_masuk
					+ "','dd/MM/yyyy')";
			// String tarikh_mati = "to_date('" + tarikh_simati +
			// "','dd/MM/yyyy')";
			int idNeg = Integer.parseInt(NegId);

			// db = new Db();
			// Statement stmtA = db.getStatement();
			// SQLRenderer r = new SQLRenderer();
			r.clear();
			r.add("id_fail", idFail);
			r.add("id_seksyen", 2);
			r.add("id_urusan", 382);
			r.add("tarikh_daftar_fail", r.unquote("sysdate"));
			r.add("tarikh_masuk", r.unquote("sysdate"));
			//r.add("tajuk_fail", getNoFile);
			//r.add("no_fail", getNoFile);
			r.add("id_negeri", Integer.parseInt(userIdNeg));
			r.add("id_suburusan", 60);
			r.add("flag_fail", 1);
			r.add("id_masuk", userId);
			//r.add("flag_Jenis_Fail", 1);
			r.add("id_status",7);


			sql = r.getSQLInsert("tblpfdfail");
			stmt.executeUpdate(sql);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String currentDate = dateFormat.format(date);

			// db = new Db();
			// Statement stmtc = db.getStatement();
			// SQLRenderer r2 = new SQLRenderer();
			r.clear();
			r.add("id_pemohon", idPemohon);
			if (jenis_pemohon.equals("2")) {
				r.add("no_kp_baru", no_kpbaru_pemohon);
				r.add("no_kp_lama", no_kplama_pemohon.toUpperCase());
				r.add("jenis_kp", sel_jeniskp_pemohon);
				r.add("no_kp_lain", no_kplain_pemohon.toUpperCase());
				r.add("umur", txtUmurPemohon);
				r.add("jantina", socJantinaPemohon);
				r.add("no_hp", no_hp);
			} else {
				r.add("no_kp_baru", "");
				r.add("no_kp_lama", "");
				r.add("jenis_kp", "");
				r.add("no_kp_lain", "");
				r.add("umur", "");
				r.add("jantina", "");
				r.add("no_hp", "");
			}

			r.add("id_arb", jenis_pej);
			r.add("nama_pemohon", nama_pemohon.toUpperCase());

			r.add("alamat_1", alamat1.toUpperCase());
			r.add("alamat_2", alamat2.toUpperCase());
			r.add("alamat_3", alamat3.toUpperCase());
			r.add("poskod", poskod);
			r.add("id_bandar", bandar);
			r.add("id_negeri", negeri);

			r.add("alamat1_surat", alamat1.toUpperCase());
			r.add("alamat2_surat", alamat2.toUpperCase());
			r.add("alamat3_surat", alamat3.toUpperCase());
			r.add("poskod_surat", poskod);
			// r2.add("bandar_surat",bandar);
			r.add("id_bandarsurat", bandar);
			r.add("id_negerisurat", negeri);

			r.add("no_tel", no_tel);
			r.add("id_tarafkptg", taraf_penting);
			r.add("status_pemohon", jenis_pemohon);

			r.add("id_saudara", socSaudaraWaris);

			r.add("id_masuk", userId);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			
			
			
			r.add("nama_pelbagainegara", nama_pelbagainegara);
			r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
			
			
			sql2 = r.getSQLInsert("tblppkpemohon");
			stmt.executeUpdate(sql2);

			// no_subjaket

			Vector id_n = null;
			id_n = getNegeriID(no_daerah);

			Hashtable nn = (Hashtable) id_n.get(0);
			nn.get("idnegeri").toString();

			//String noFail_online = String.format("%06d",File.getSeqNo(2,02,382,0,Integer.parseInt(nn.get("idnegeri").toString()),false,false));
			//String X = "JKPTG/PK/02/"+getYear+"/"+noFail_online;
			String X = "";

			// db = new Db();
			// Statement stmtd = db.getStatement();
			// SQLRenderer r3 = new SQLRenderer();
			r.clear();
			r.add("id_permohonan", idPermohonan);
			r.add("id_daerahmhn", no_daerah);
			r.add("id_negerimhn", nn.get("idnegeri").toString());
			r.add("id_pemohon", idPemohon);

			//r.add("id_status", 160);
			r.add("flag_Jenis_Permohonan", 0);

			r.add("NO_PERMOHONAN_ONLINE",X);

			r.add("id_fail", idFail);
			r.add("seksyen", 17);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			//r.add("tarikh_mohon", r.unquote(tarikh_mohon));
			r.add("no_subjaket", jac);
			r.add("id_unitpskawal", UserIdPejabat);
			r.add("id_masuk", userId);

			r.add("batal_kuasa_pentadbir", bkp);
			r.add("lantik_pentadbir", lp);
			r.add("batal_p_amanah", bpa);
			r.add("lantik_p_amanah", lpa);
			r.add("harta_tinggal", ht);
			r.add("lain_tujuan", lt);
			r.add("id_permohonanterdahulu", idp_dulu);

			sql3 = r.getSQLInsert("tblppkpermohonan");
			stmt.executeUpdate(sql3);

			String sqlr = "";
			// db = new Db();
			// Statement stmtr = db.getStatement();
			// SQLRenderer rj = new SQLRenderer();
			r.clear();
			r.update("id_permohonan", idp_dulu);
			r.add("flag_permohonan", 1);
			sqlr = r.getSQLUpdate("tblppkpermohonan");
			stmt.executeUpdate(sqlr);

			// db = new Db();
			// Statement stmtM = db.getStatement();
			// SQLRenderer r77 = new SQLRenderer();
			r.clear();
			r.add("id_bayaran", idBayaran);
			r.add("id_permohonan", idPermohonan);
			r.add("id_jenisbayaran", 2);

			String tr = "";
			if (txtNomborResitH != "") {
				r.add("no_resit", txtNomborResitH);
				tr = "to_date('" + txdTarikhByrnH + "','dd/MM/yyyy')";
				r.add("tarikh_bayaran", r.unquote(tr));
				r.add("jumlah_bayaran", 10);
			} else {
				r.add("no_resit", "");
				tr = "to_date('" + txdTarikhByrnH + "','dd/MM/yyyy')";
				r.add("tarikh_bayaran", "");
				r.add("jumlah_bayaran", 0);
			}

			r.add("id_masuk", userId);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			sqlbayaran = r.getSQLInsert("tblppkbayaran");
			// System.out.println("sqlbayaran-->" + sqlbayaran);
			stmt.executeUpdate(sqlbayaran);
			/*
			 * db = new Db(); Statement stmt8 = db.getStatement(); SQLRenderer
			 * r8 = new SQLRenderer(); r8.add("id_Simati", idsimati);
			 * r8.add("id_Permohonan", idPermohonan); r8.add("id_Masuk",
			 * userId); r8.add("tarikh_Masuk", r.unquote("sysdate")); sql8 =
			 * r8.getSQLInsert("tblppkpermohonansimati");
			 * stmt8.executeUpdate(sql8);
			 */

			// db = new Db();
			// Statement stmt8 = db.getStatement();
			// SQLRenderer r8 = new SQLRenderer();
			r.clear();
			r.add("id_permohonansimati", idP_mati);
			r.add("id_Simati", idsimati);
			r.add("id_Permohonan", idPermohonan);
			r.add("id_Masuk", userId);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			sql8 = r.getSQLInsert("tblppkpermohonansimati");
			stmt.executeUpdate(sql8);

			/*
			r.clear();
			r.update("ID_SUBURUSANSTATUSFAIL", id_Suburusanstatusfail);
			r.add("AKTIF", 0);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql99 = r.getSQLUpdate("tblrujsuburusanstatusfail");
			stmt.executeUpdate(sql99);
			
			r.clear();
			r.add("ID_SUBURUSANSTATUSFAIL",
					DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", 464);
			r.add("ID_FAIL", idFail);
			r.add("AKTIF", 1);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql5 = r.getSQLInsert("tblrujsuburusanstatusfail");
			stmt.executeUpdate(sql5);
			*/
			
			/*
			//belum ubah structure
			if (taraf_penting != "0" && taraf_penting != "" && cpw.size() == 0) {
				r.clear();
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
				}
				r.add("id_arb", jenis_pej);
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", idP_mati);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Pemohon", idPemohon);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_saudara", socSaudaraWaris);
				sqlOB = r.getSQLInsert("tblppkob");
				stmt.executeUpdate(sqlOB);
			}
			if (taraf_penting != "0" && taraf_penting != "" && cpw.size() > 0) {
				r.clear();
				r.update("id_Pemohon", idPemohon);
				r.add("id_Simati", idP_mati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", idP_mati);
				r.add("id_arb", jenis_pej);

				r.add("no_Tel", no_tel);

				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_saudara", socSaudaraWaris);
				sqlOBU = r.getSQLUpdate("tblppkob");
				stmt.executeUpdate(sqlOBU);
			}
			*/
			

			if (taraf_penting != "0" && taraf_penting != "" && cpw.size() == 0) {
				// dbOB = new Db();
				// Statement stmtOB = db.getStatement();
				// SQLRenderer rOB = new SQLRenderer();
				long id_ob = 0;
				r.clear();
				id_ob = DB.getNextID(db,"TBLPPKOB_SEQ");
				r.add("id_ob", id_ob);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
					r.add("status_ob", "");
				}
				r.add("id_arb", jenis_pej);
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", idP_mati);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Pemohon", idPemohon);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_saudara", socSaudaraWaris);
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				sqlOB = r.getSQLInsert("tblppkob");
				stmt.executeUpdate(sqlOB);
				
				r.clear();
				r.add("id_ob", id_ob);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");
					r.add("status_ob", "");
				}
				r.add("id_arb", jenis_pej);
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", idP_mati);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Pemohon", idPemohon);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_saudara", socSaudaraWaris);
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				
				sqlOB = r.getSQLInsert("tblppkobpermohonan");
				stmt.executeUpdate(sqlOB);
			}
			
			

			if (taraf_penting != "0" && taraf_penting != "" && cpw.size() > 0) {
				r.clear();
				r.update("id_Pemohon", idPemohon);
				r.add("id_Simati", idP_mati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");	
					r.add("status_ob", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", idP_mati);
				r.add("id_arb", jenis_pej);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_saudara", socSaudaraWaris);
				
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				sqlOBU = r.getSQLUpdate("tblppkob");
				stmt.executeUpdate(sqlOBU);	
				
				r.clear();
				
				
				
				
				String sqlPemohon = "";
				sqlPemohon = " SELECT ID_OB,LAPIS,STATUS_HIDUP FROM TBLPPKOB WHERE ID_PEMOHON = '"+idPemohon+"'";
				ResultSet rs1 = stmt.executeQuery(sqlPemohon);	
				String temp_ID_OB = "";
				String temp_LAPIS = "";
				String 				temp_STATUS_HIDUP = "";
				while (rs1.next()) {				
					temp_ID_OB = rs1.getString("ID_OB") == null ? "" : rs1.getString("ID_OB");
					temp_LAPIS = rs1.getString("LAPIS") == null ? "" : rs1.getString("LAPIS");
					temp_STATUS_HIDUP = rs1.getString("STATUS_HIDUP") == null ? "" : rs1.getString("STATUS_HIDUP");
				}
				
				
				
				r.add("id_ob", temp_ID_OB);
				r.add("id_Pemohon", idPemohon);
				r.add("id_Simati", idP_mati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_hp", no_hp);
					r.add("status_ob", "1");
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_hp", "");	
					r.add("status_ob", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", idP_mati);
				r.add("id_arb", jenis_pej);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", bandar);
				r.add("poskod", poskod);
				//r.add("lapis", 1);
				//r.add("status_hidup", 0);
				r.add("lapis",temp_LAPIS);
				r.add("status_hidup",temp_STATUS_HIDUP);
				r.add("id_Negeri", negeri);
				r.add("id_Kemaskini", userId);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", userId);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", bandar);
				r.add("id_Negerisurat", negeri);
				r.add("id_saudara", socSaudaraWaris);
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				
				sqlOBU = r.getSQLInsert("tblppkobpermohonan");
				stmt.executeUpdate(sqlOBU);
			}			
			conn.commit();
			//:::SUB	
			myLogger.info("SSF KEMASKINI 7");
			kemaskiniSubUrusanStatusFail(session,idPermohonan+"",userId,"160","464",idFail+"");

		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
	}

	/*
	 * public void addPermohonan17PL(Hashtable data) throws Exception { Db db =
	 * null; String sql = ""; String sql1 = ""; String sql2 = ""; String sql3 =
	 * ""; String sql4 = ""; String sql5 = ""; String sql8 = ""; String sql99 =
	 * ""; String sqlbayaran = "";
	 * 
	 * SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); try { long
	 * idFail = DB.getNextID("TBLPFDFAIL_SEQ"); long idPemohon =
	 * DB.getNextID("TBLPPKPEMOHON_SEQ"); // long idsimati =
	 * DB.getNextID("TBLPPKSIMATI_SEQ");
	 * 
	 * long idBayaran = DB.getNextID("TBLPPKBAYARAN_SEQ");
	 * 
	 * 
	 * 
	 * long idPermohonan = Long.parseLong((String)data.get("IdPermohonan"));
	 * 
	 * 
	 * //System.out.println("IDPPPPP"+idPermohonan); long idSubUrusanStatus =
	 * DB.getNextID("TBLRUJSUBURUSANSTATUS_SEQ");
	 * 
	 * int UserIdPejabat = Integer.parseInt((String)data.get("userIdPejabat"));
	 * String userIdNeg = (String)data.get("userIdNeg"); String userId =
	 * (String)data.get("userId"); String NegId = (String)data.get("negId");
	 * String id_Fail = (String)data.get("id_Fail"); String no_subjaket =
	 * (String)data.get("no_subjaket");
	 * 
	 * String txtNoFail = (String)data.get("txtNoFail");
	 * 
	 * String bkp = (String)data.get("bkp"); String lp = (String)data.get("lp");
	 * String bpa = (String)data.get("bpa"); String lpa =
	 * (String)data.get("lpa"); String ht = (String)data.get("ht"); String
	 * pemohonSimati = (String)data.get("pemohonSimati");
	 * 
	 * 
	 * //System.out.println("no_subjaket :>>>>>"+no_subjaket);
	 * 
	 * String idsimati = (String)data.get("idSimati");
	 * 
	 * String id_Suburusanstatusfail =
	 * (String)data.get("id_Suburusanstatusfail");
	 * 
	 * 
	 * 
	 * String no_daerah = (String)data.get("no_daerah");
	 * //System.out.println("IDPPPPP Daerah!!!!"+no_daerah);
	 * 
	 * String negeri = (String)data.get("negeri"); String no_kpbaru_pemohon =
	 * (String)data.get("no_kpbaru_pemohon"); String no_kplama_pemohon =
	 * (String)data.get("no_kplama_pemohon"); //String nama_simati =
	 * (String)data.get("nama_simati"); String tarikh_masuk =
	 * (String)data.get("tarikh_masuk"); //String no_kpbaru_simati =
	 * (String)data.get("no_kp_baru"); //String no_kplama_simati =
	 * (String)data.get("no_kplama_simati"); //String sel_jeniskp_simati =
	 * (String)data.get("sel_jeniskp_simati"); //String no_kplain_simati =
	 * (String)data.get("no_kplain_simati");
	 * 
	 * String userIdKodDaerah = (String)data.get("userIdKodDaerah");
	 * 
	 * //System.out.println("USER KOD DAERAH!!!::"+userIdKodDaerah);
	 * 
	 * 
	 * String userIdKodNegeri = (String)data.get("userIdKodNegeri"); //String
	 * tarikh_simati = (String)data.get("tarikh_simati"); String
	 * sel_jeniskp_pemohon = (String)data.get("sel_jeniskp_pemohon"); String
	 * no_kplain_pemohon = (String)data.get("no_kplain_pemohon"); String
	 * nama_pemohon = (String)data.get("nama_pemohon"); String alamat1 =
	 * (String)data.get("alamat1"); String alamat2 =
	 * (String)data.get("alamat2"); String alamat3 =
	 * (String)data.get("alamat3"); String bandar = (String)data.get("bandar");
	 * String poskod = (String)data.get("poskod");
	 * 
	 * String buktimati = (String)data.get("buktimati"); String sijilmati =
	 * (String)data.get("sijilmati");
	 * 
	 * String txtUmurSimati = (String)data.get("txtUmurSimati"); String
	 * socJantinaSimati = (String)data.get("socJantinaSimati"); String
	 * txtUmurPemohon = (String)data.get("txtUmurPemohon"); String
	 * socJantinaPemohon = (String)data.get("socJantinaPemohon");
	 * 
	 * String txtNomborResitH = (String)data.get("txtNomborResitH"); String
	 * txdTarikhByrnH = (String)data.get("txdTarikhByrnH");
	 * 
	 * 
	 * //System.out.println("sijilmatimodel::"+sijilmati);
	 * 
	 * 
	 * 
	 * java.util.Calendar calendar = java.util.Calendar.getInstance(); int
	 * getYear = calendar.get(java.util.Calendar.YEAR);
	 * 
	 * String X =
	 * String.format("%04d",File.getSeqNo(2,382,0,Integer.parseInt(userIdNeg
	 * ),Integer.parseInt(no_daerah),false,false,getYear));
	 * 
	 * if (no_daerah.length() < 1){ no_daerah = "0"+no_daerah; }else{ no_daerah
	 * = no_daerah; } if (userIdNeg.length() < 1){ userIdNeg = "0"+userIdNeg;
	 * }else{ userIdNeg = userIdNeg; } if (negeri.equals("")){ negeri = "0"; }
	 * 
	 * //System.out.println("USER KOD DAERAH MOHON!!!::"+no_daerah);
	 * 
	 * int nod=Integer.parseInt(no_daerah);
	 * 
	 * //FrmPrmhnnSek8InternalData.setDaerahbyID(nod);
	 * 
	 * 
	 * db = new Db(); Statement stmt12 = db.getStatement(); SQLRenderer r12 =
	 * new SQLRenderer();
	 * 
	 * 
	 * r12.add("d.id_negeri"); r12.add("d.id_daerah"); r12.add("d.nama_daerah");
	 * r12.add("d.kod_daerah"); r12.add("n.kod_negeri");
	 * 
	 * r12.add("d.id_negeri",r12.unquote("n.id_Negeri"));
	 * r12.add("d.id_daerah",nod);
	 * 
	 * 
	 * String sql12 = r12.getSQLSelect("Tblrujdaerah d, Tblrujnegeri n");
	 * ResultSet rs12 = stmt12.executeQuery(sql12); //Vector list = new Vector(;
	 * Hashtable h; //int bil = 1; String kod=""; String kodn=""; while
	 * (rs12.next()){ h = new Hashtable(); //h.put("bil", bil);
	 * //h.put("id_Permohonan", rs.getString("id_Permohonan"));
	 * //h.put("id_Hta",
	 * rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
	 * h.put("id_negeri"
	 * ,rs12.getString("id_negeri")==null?"":rs12.getString("id_negeri"));
	 * h.put(
	 * "id_daerah",rs12.getString("id_daerah")==null?"":rs12.getString("id_daerah"
	 * ));
	 * h.put("nama_daerah",rs12.getString("nama_daerah")==null?"":rs12.getString
	 * ("nama_daerah"));
	 * h.put("kod_daerah",rs12.getString("kod_daerah")==null?""
	 * :rs12.getString("kod_daerah"));
	 * h.put("kod_negeri",rs12.getString("kod_negeri"
	 * )==null?"":rs12.getString("kod_negeri"));
	 * 
	 * 
	 * //bil++; kod=rs12.getString("kod_daerah");
	 * kodn=rs12.getString("kod_negeri"); }
	 * 
	 * int jacket=0; int jacket_count=0; String jac=""; String nosubjacket="";
	 * 
	 * 
	 * if(no_subjaket == "") { jacket=1; jac=""+jacket;
	 * 
	 * //System.out.print("jac::"+jac); } else { jacket =
	 * Integer.parseInt(no_subjaket); jacket_count = jacket+ 1;
	 * jac=""+jacket_count; }
	 * 
	 * //nosubjacket = ""; //txtNoFail
	 * 
	 * // String txtNoFail = "JKPTG/PK/03/02/0009/2009/S17-1"; String s =
	 * txtNoFail.substring(0,24); // //System.out.println("getNoFile SSSS:"+s);
	 * 
	 * // String s = txtNoFail.substring(txtNoFail.lastIndexOf('/')+1); //
	 * //System.out.println("getNoFile SSSS:"+s);
	 * 
	 * // String getNoFile = "JKPTG/PK/"+ userIdKodNegeri + "/"+ userIdKodDaerah
	 * + "/"+X+"/"+getYear; String getNoFile = s+"/S17-"+jac;
	 * //System.out.println("getNoFile:"+getNoFile);
	 * 
	 * tarikh_masuk = (String)data.get("tarikh_masuk"); //tarikh_simati =
	 * (String)data.get("tarikh_simati"); String tarikh_mohon = "to_date('" +
	 * tarikh_masuk + "','dd/MM/yyyy')"; // String tarikh_mati = "to_date('" +
	 * tarikh_simati + "','dd/MM/yyyy')"; int idNeg = Integer.parseInt(NegId);
	 * 
	 * 
	 * db = new Db(); Statement stmtA = db.getStatement(); SQLRenderer r = new
	 * SQLRenderer(); r.add("id_fail",idFail); r.add("id_seksyen",2);
	 * r.add("id_urusan",382); r.add("tarikh_daftar_fail",r.unquote("sysdate"));
	 * r.add("tarikh_masuk",r.unquote("sysdate"));
	 * r.add("tajuk_fail",getNoFile); r.add("no_fail",getNoFile);
	 * r.add("id_negeri",Integer.parseInt(userIdNeg)); r.add("id_suburusan",60);
	 * r.add("flag_fail",1); r.add("id_masuk",userId);
	 * r.add("flag_Jenis_Fail",1); sql = r.getSQLInsert("tblpfdfail");
	 * stmtA.executeUpdate(sql);
	 * 
	 * DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); Date date =
	 * new Date(); String currentDate = dateFormat.format(date);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * db = new Db(); Statement stmtc = db.getStatement(); SQLRenderer r2 = new
	 * SQLRenderer(); r2.update("id_pemohon",pemohonSimati);
	 * r2.add("no_kp_baru",no_kpbaru_pemohon);
	 * r2.add("no_kp_lama",no_kplama_pemohon);
	 * r2.add("jenis_kp",sel_jeniskp_pemohon);
	 * r2.add("no_kp_lain",no_kplain_pemohon);
	 * r2.add("nama_pemohon",nama_pemohon);
	 * 
	 * r2.add("alamat_1",alamat1); r2.add("alamat_2",alamat2);
	 * r2.add("alamat_3",alamat3); r2.add("poskod",poskod);
	 * r2.add("id_bandar",bandar); r2.add("id_negeri",negeri);
	 * 
	 * r2.add("alamat1_surat",alamat1); r2.add("alamat2_surat",alamat2);
	 * r2.add("alamat3_surat",alamat3); r2.add("poskod_surat",poskod); //
	 * r2.add("bandar_surat",bandar); r2.add("id_bandarsurat",bandar);
	 * r2.add("id_negerisurat",negeri);
	 * 
	 * r2.add("umur",txtUmurPemohon); r2.add("jantina",socJantinaPemohon);
	 * r2.add("id_permohonan",idPermohonan); r2.add("id_kemaskini",userId);
	 * r2.add("tarikh_Kemaskini",r.unquote("sysdate")); sql2 =
	 * r2.getSQLUpdate("tblppkpemohon"); stmtc.executeUpdate(sql2);
	 * 
	 * 
	 * //no_subjaket
	 * 
	 * db = new Db(); Statement stmtd = db.getStatement(); SQLRenderer r3 = new
	 * SQLRenderer(); r3.add("id_permohonan",idPermohonan);
	 * r3.add("id_daerahmhn",no_daerah); r3.add("id_status",8);
	 * r3.add("flag_Jenis_Permohonan",1);
	 * 
	 * r3.add("id_fail",idFail); r3.add("seksyen",17);
	 * r3.add("tarikh_masuk",r3.unquote("sysdate"));
	 * r3.add("tarikh_mohon",r3.unquote(tarikh_mohon));
	 * r3.add("no_subjaket",jac); r3.add("id_unitpskawal",UserIdPejabat);
	 * r3.add("id_masuk",userId);
	 * 
	 * r3.add("batal_kuasa_pentadbir",bkp); r3.add("lantik_pentadbir",lp);
	 * r3.add("batal_p_amanah",bpa); r3.add("lantik_p_amanah",lpa);
	 * r3.add("harta_tinggal",ht);
	 * 
	 * 
	 * 
	 * 
	 * sql3 = r3.getSQLInsert("tblppkpermohonan"); stmtd.executeUpdate(sql3);
	 * 
	 * 
	 * 
	 * 
	 * db = new Db(); Statement stmtM = db.getStatement(); SQLRenderer r77 = new
	 * SQLRenderer(); r77.add("id_bayaran", idBayaran); r77.add("id_permohonan",
	 * idPermohonan); r77.add("id_jenisbayaran",2);
	 * 
	 * 
	 * String tr = ""; if(txtNomborResitH != "") {
	 * r77.add("no_resit",txtNomborResitH); tr = "to_date('" + txdTarikhByrnH +
	 * "','dd/MM/yyyy')"; r77.add("tarikh_bayaran",r77.unquote(tr));
	 * r77.add("jumlah_bayaran",10); } else{ r77.add("no_resit",""); tr =
	 * "to_date('" + txdTarikhByrnH + "','dd/MM/yyyy')";
	 * r77.add("tarikh_bayaran",""); r77.add("jumlah_bayaran",0); }
	 * 
	 * 
	 * 
	 * r77.add("id_masuk",userId);
	 * r77.add("tarikh_masuk",r77.unquote("sysdate")); sqlbayaran =
	 * r77.getSQLInsert("tblppkbayaran");
	 * //System.out.println("sqlbayaran-->"+sqlbayaran);
	 * stmtM.executeUpdate(sqlbayaran);
	 * 
	 * 
	 * 
	 * db = new Db(); Statement stmt8 = db.getStatement(); SQLRenderer r = new
	 * SQLRenderer(); r.add("id_Simati",idsimati);
	 * r.add("id_Permohonan",idPermohonan); r.add("id_Masuk",userId);
	 * r.add("tarikh_Masuk",r.unquote("sysdate")); sql8 =
	 * r.getSQLInsert("tblppkpermohonansimati"); stmt8.executeUpdate(sql8);
	 * 
	 * 
	 * db = new Db(); Statement stmtF9 = db.getStatement(); SQLRenderer r99 =
	 * new SQLRenderer();
	 * r99.update("ID_SUBURUSANSTATUSFAIL",id_Suburusanstatusfail);
	 * r99.add("AKTIF",0); r99.add("ID_KEMASKINI",userId);
	 * r99.add("TARIKH_KEMASKINI",r.unquote("sysdate")); sql99 =
	 * r99.getSQLUpdate("tblrujsuburusanstatusfail");
	 * stmtF9.executeUpdate(sql99);
	 * 
	 * 
	 * 
	 * 
	 * db = new Db(); Statement stmtF = db.getStatement(); SQLRenderer r5 = new
	 * SQLRenderer();
	 * r5.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"
	 * )); r5.add("ID_PERMOHONAN",idPermohonan);
	 * r5.add("ID_SUBURUSANSTATUS",430); r5.add("ID_FAIL",idFail);
	 * r5.add("AKTIF",1); r5.add("ID_MASUK",userId);
	 * r5.add("TARIKH_MASUK",r.unquote("sysdate"));
	 * r5.add("ID_KEMASKINI",userId);
	 * r5.add("TARIKH_KEMASKINI",r.unquote("sysdate")); sql5 =
	 * r5.getSQLInsert("tblrujsuburusanstatusfail"); stmtF.executeUpdate(sql5);
	 * 
	 * }finally { if(db != null) db.close(); } }
	 */
   
	public void updatePermohonan(Hashtable data) throws Exception {
		Connection conn = null;
		Db db = null;
		// Db dbOB = null;
		// Db dbOBU = null;
		String sqlOB = "";
		String sqlOBU = "";
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		// SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			String IdFail = (String) data.get("IdFail");
			String IdSimati = (String) data.get("IdSimati");
			String IdPemohon = (String) data.get("IdPemohon");
			String IdPermohonan = (String) data.get("IdPermohonan");
			String no_daerah = (String) data.get("no_daerah");
			String negeri = (String) data.get("negeri");
			String no_kpbaru_pemohon = (String) data.get("no_kpbaru_pemohon");
			String no_kplama_pemohon = (String) data.get("no_kplama_pemohon");
			String nama_simati = (String) data.get("nama_simati");
			String tarikh_masuk = (String) data.get("tarikh_masuk");
			String tarikh_daftar = (String) data.get("tarikh_daftar");
			String no_kpbaru_simati = (String) data.get("no_kp_baru");
			String no_kplama_simati = (String) data.get("no_kplama_simati");
			String sel_jeniskp_simati = (String) data.get("sel_jeniskp_simati");
			String no_kplain_simati = (String) data.get("no_kplain_simati");
			String tarikh_simati = (String) data.get("tarikh_simati");
			String sel_jeniskp_pemohon = (String) data
					.get("sel_jeniskp_pemohon");
			String no_kplain_pemohon = (String) data.get("no_kplain_pemohon");
			String tarikh_lahir_simati = (String) data.get("tarikhLahirSimati");
			String nama_pemohon = (String) data.get("nama_pemohon");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String bandar = (String) data.get("bandar");
			String idbandar = (String) data.get("idbandar");
			String poskod = (String) data.get("poskod");
			String id_Masuk = (String) data.get("id_Masuk");

			String txtUmurSimati = (String) data.get("txtUmurSimati");
			String socJantinaSimati = (String) data.get("socJantinaSimati");
			String txtUmurPemohon = (String) data.get("txtUmurPemohon");
			String socJantinaPemohon = (String) data.get("socJantinaPemohon");

			String no_tel = (String) data.get("no_tel");
			
			String nama_pelbagainegara = (String) data.get("nama_pelbagainegara");
			
			
			String jenis_pej = (String) data.get("jenis_pej");

			String no_hp = (String) data.get("no_hp");
			String taraf_penting = (String) data.get("taraf_penting");
			String jenis_pemohon = (String) data.get("jenis_pemohon");
			String adaob = (String) data.get("adaob");
			
			String socSaudaraWaris = (String) data.get("socSaudaraWaris");
				
			
			
			
			
			String id_Permohonansimati = (String) data
					.get("id_Permohonansimati");

			// String pemohonSimati = (String) data.get("pemohonSimati");
			Vector cpw = checkpemohonwaris(IdPemohon);

			// System.out.print("ADA OB??"+adaob);

			// int umursimati = Integer.parseInt(txtUmurSimati);
			// int jantinasimati = Integer.parseInt(socJantinaSimati);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String currentDate = dateFormat.format(date);

			String tarikhdaftar = "to_date('" + tarikh_daftar
					+ "','dd/MM/yyyy')";
			String tarikhsimati = "to_date('" + tarikh_simati
					+ "','dd/MM/yyyy')";
			String tarikhLahirSimati = "to_date('" + tarikh_lahir_simati
			+ "','dd/MM/yyyy')";

			String tarikh_mohon = "to_date('" + tarikh_masuk
					+ "','dd/MM/yyyy')";

			// db = new Db();
			Statement stmt = db.getStatement();
			
			
			/*sql = "UPDATE TBLPPKPERMOHONAN SET tarikh_mohon=" + tarikh_mohon
					+ ", id_pemohon = " + IdPemohon + ","
					+ "tarikh_kemaskini = sysdate where id_permohonan = "
					+ IdPermohonan + " ";*/
			
			sql = "UPDATE TBLPPKPERMOHONAN SET tarikh_mohon=" + tarikh_mohon
			+ ", id_pemohon = " + IdPemohon + ",id_kemaskini = " + id_Masuk + ","
			+ "tarikh_kemaskini = sysdate where id_permohonan = "
			+ IdPermohonan + " ";

			myLogger.debug(sql);
			stmt.executeUpdate(sql);

			db = new Db();
			// Statement stmt = db.getStatement();
			sql = "UPDATE TBLPFDFAIL SET tarikh_daftar_fail=sysdate, "
					+ "tarikh_kemaskini = sysdate where id_fail = " + IdFail
					+ " ";
			stmt.executeUpdate(sql);

			// db = new Db();
			// Statement stmtA = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.clear();
			r.update("id_simati", IdSimati);
			r.add("nama_simati", nama_simati);
			r.add("no_kp_baru", no_kpbaru_simati);
			r.add("no_kp_lama", no_kplama_simati);
			r.add("jenis_kp", sel_jeniskp_simati);
			r.add("no_kp_lain", no_kplain_simati);
			if (tarikh_lahir_simati != null && tarikh_lahir_simati.length() > 0){
				r.add("tarikh_lahir", r.unquote(tarikhLahirSimati));
			}				
			r.add("tarikh_mati", r.unquote(tarikhsimati));
			r.add("tarikh_masuk", r.unquote(tarikhdaftar));
			r.add("id_Kemaskini", id_Masuk);

			// int umursimati = Integer.parseInt(txtUmurSimati);
			// int jantinasimati = Integer.parseInt(socJantinaSimati);

			r.add("umur", txtUmurSimati);
			r.add("jantina", socJantinaSimati);

			r.add("tarikh_Kemaskini", r.unquote("sysdate"));

			sql1 = r.getSQLUpdate("tblppksimati");
			System.out.println("peje --- " + sql1);
			stmt.executeUpdate(sql1);
			

			// db = new Db();
			// Statement stmtc = db.getStatement();
			
			
			
			r.clear();
			r.update("id_pemohon", IdPemohon);
			if (jenis_pemohon.equals("2")) {
				r.add("no_kp_baru", no_kpbaru_pemohon);
				r.add("no_kp_lama", no_kplama_pemohon.toUpperCase());
				r.add("jenis_kp", sel_jeniskp_pemohon);
				r.add("no_kp_lain", no_kplain_pemohon.toUpperCase());
				r.add("umur", txtUmurPemohon);
				r.add("jantina", socJantinaPemohon);
				r.add("no_hp", no_hp);
			} else {
				r.add("no_kp_baru", "");
				r.add("no_kp_lama", "");
				r.add("jenis_kp", "");
				r.add("no_kp_lain", "");
				r.add("umur", "");
				r.add("jantina", "");
				r.add("no_hp", "");

			}
			r.add("nama_pemohon", nama_pemohon.toUpperCase());
			myLogger.info(" jenis_pemohon ::::::::: "+jenis_pemohon);
			if (jenis_pemohon.equals("1")) {}
			
			
			r.add("alamat_1", alamat1.toUpperCase());
			r.add("alamat_2", alamat2.toUpperCase());
			r.add("alamat_3", alamat3.toUpperCase());
			r.add("poskod", poskod);
			r.add("bandar", bandar);
			r.add("id_bandar", idbandar);
			r.add("id_negeri", negeri);
			r.add("alamat1_surat", alamat1.toUpperCase());
			r.add("alamat2_surat", alamat2.toUpperCase());
			r.add("alamat3_surat", alamat3.toUpperCase());
			r.add("poskod_surat", poskod);
			r.add("id_bandarsurat", idbandar);
			r.add("bandar_surat", bandar);
			r.add("id_negerisurat", negeri);
			
			r.add("no_tel", no_tel);

			r.add("id_tarafkptg", taraf_penting);
			r.add("status_pemohon", jenis_pemohon);
			r.add("id_Arb", jenis_pej);
			
			r.add("id_saudara", socSaudaraWaris);

			// r2.add("id_permohonan", idPermohonan);
			r.add("id_masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			
			r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
			r.add("nama_pelbagainegara", nama_pelbagainegara);
			
			
			sql2 = r.getSQLUpdate("tblppkpemohon");
			
			myLogger.info(" UPDATE PEMOHON :"+sql2);
			stmt.executeUpdate(sql2);
			
			/*
			sql3 = "UPDATE TBLPPKPEMOHON SET ";
			if (jenis_pemohon.equals("2")) {
				sql3 += "no_kp_baru='" + no_kpbaru_pemohon + "',umur='"
						+ txtUmurPemohon + "',jantina='" + socJantinaPemohon
						+ "',no_kp_lama='" + no_kplama_pemohon.toUpperCase()
						+ "',jenis_kp='" + sel_jeniskp_pemohon
					//	+ "',id_saudara='" + socSaudaraWaris
						+ "', no_kp_lain='" + no_kplain_pemohon.toUpperCase()
						+ "'," + "  no_hp='" + no_hp + "',";

			} else {

				sql3 += "no_kp_baru='',umur=''," +
						//"id_saudara=''," +
						"jantina='',no_kp_lama='',jenis_kp='',"
						+ " no_kp_lain='', no_hp='',";
			}

			sql3 += " nama_pemohon='"
					+ Utils.escapeCharacter(nama_pemohon.toUpperCase())
					+ "',id_Kemaskini='" + id_Masuk
					+ "',tarikh_Kemaskini=sysdate ," + "alamat_1='"
					+ Utils.escapeCharacter(alamat1.toUpperCase())
					+ "', alamat_2='"
					+ Utils.escapeCharacter(alamat2.toUpperCase())
					+ "', alamat_3='"
					+ Utils.escapeCharacter(alamat3.toUpperCase())
					+ "', poskod='" + poskod + "',id_bandar='" + idbandar
					+ "', bandar='" + bandar + "', id_negeri='" + negeri + "',";
			if (jenis_pemohon.equals("1")) {
				sql3 += "alamat1_Surat='"
						+ Utils.escapeCharacter(alamat1.toUpperCase())
						+ "', alamat2_Surat='"
						+ Utils.escapeCharacter(alamat2.toUpperCase())
						+ "', alamat3_Surat='"
						+ Utils.escapeCharacter(alamat3.toUpperCase())
						+ "', poskod_Surat='" + poskod.toUpperCase()
						+ "',id_Bandarsurat='" + idbandar
						+ "', id_Negerisurat='" + negeri + "',";
			}
			sql3 += " id_tarafkptg='" + taraf_penting + "', no_tel='" + no_tel
					+ "' , status_pemohon='" + jenis_pemohon + "', id_arb='"
					+ jenis_pej + "' where id_pemohon = " + IdPemohon + "";

			// myLogger.debug("SQL3"+sql3);
			stmt.executeUpdate(sql3);
			
			*/
			
			
            
			if (taraf_penting != "0" && taraf_penting != "" && cpw.size() == 0) {
				long id_ob = DB.getNextID("TBLPPKOB_SEQ");
				r.clear();
				r.add("id_ob", id_ob);
				r.add("id_Simati", IdSimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("umur", txtUmurPemohon);
					r.add("no_Hp", no_hp);
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("jantina", "");
					r.add("umur", "");
					r.add("no_Hp", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", id_Permohonansimati);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", idbandar);
				r.add("poskod", poskod);
				r.add("id_Negeri", negeri);				
				r.add("id_saudara", socSaudaraWaris);
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", idbandar);
				r.add("id_Negerisurat", negeri);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Pemohon", IdPemohon);
				r.add("id_Arb", jenis_pej);
				r.add("id_Kemaskini", id_Masuk);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", id_Masuk);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				sqlOB = r.getSQLInsert("tblppkob");				
				stmt.executeUpdate(sqlOB);
				
				r.clear();
				r.add("id_ob", id_ob);
				r.add("id_Simati", IdSimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("umur", txtUmurPemohon);
					r.add("no_Hp", no_hp);
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("jantina", "");
					r.add("umur", "");
					r.add("no_Hp", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", id_Permohonansimati);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", idbandar);
				r.add("poskod", poskod);
				r.add("id_Negeri", negeri);				
				r.add("id_saudara", socSaudaraWaris);
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", idbandar);
				r.add("id_Negerisurat", negeri);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Pemohon", IdPemohon);
				r.add("id_Arb", jenis_pej);
				r.add("id_Kemaskini", id_Masuk);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", id_Masuk);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				
				sqlOB = r.getSQLInsert("tblppkobpermohonan");	
				
				
				stmt.executeUpdate(sqlOB);
			}

			if (taraf_penting != "0" && taraf_penting != "" && cpw.size() > 0) {
				
				r.clear();
				r.update("id_Pemohon", IdPemohon);
				r.add("id_Simati", IdSimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_Hp", no_hp);
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_Hp", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				// r.add("id_Permohonansimati",id_Permohonansimati);
				r.add("no_Tel", no_tel);
				// r.add("no_Hp",no_hp);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", idbandar);
				r.add("poskod", poskod);
				r.add("id_Negeri", negeri);
				if (jenis_pemohon.equals("1")) {
					r.add("alamat1_Surat", alamat1.toUpperCase());
					r.add("alamat2_Surat", alamat2.toUpperCase());
					r.add("alamat3_Surat", alamat3.toUpperCase());
					r.add("poskod_Surat", poskod);
					r.add("id_Bandarsurat", idbandar);
					r.add("id_Negerisurat", negeri);
				}
				//r.add("lapis", 1);
				//r.add("status_hidup", 0);
				r.add("id_Arb", jenis_pej);
				r.add("id_saudara", socSaudaraWaris);
				r.add("id_Kemaskini", id_Masuk);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", id_Masuk);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				
				sqlOBU = r.getSQLUpdate("tblppkob");				
				stmt.executeUpdate(sqlOBU);
				//update pemohon xperlu update OB
				
				
				String sqlPemohon = "";
				sqlPemohon = " SELECT ID_OB,LAPIS,STATUS_HIDUP FROM TBLPPKOB WHERE ID_PEMOHON = '"+IdPemohon+"'";
				ResultSet rs1 = stmt.executeQuery(sqlPemohon);	
				String temp_ID_OB = "";
				String temp_LAPIS = "";
				String 				temp_STATUS_HIDUP = "";
				while (rs1.next()) {				
					temp_ID_OB = rs1.getString("ID_OB") == null ? "" : rs1.getString("ID_OB");
					temp_LAPIS = rs1.getString("LAPIS") == null ? "" : rs1.getString("LAPIS");
					temp_STATUS_HIDUP = rs1.getString("STATUS_HIDUP") == null ? "" : rs1.getString("STATUS_HIDUP");
				}
				
				r.add("lapis", temp_LAPIS);
				r.add("status_hidup", temp_STATUS_HIDUP);
				
				
				r.clear();
				r.update("id_Pemohon", IdPemohon);
				r.update("id_Permohonansimati",id_Permohonansimati);
				r.add("id_Simati", IdSimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_Hp", no_hp);
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_Hp", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);				
				r.add("no_Tel", no_tel);
				// r.add("no_Hp",no_hp);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", idbandar);
				r.add("poskod", poskod);
				r.add("id_Negeri", negeri);
				if (jenis_pemohon.equals("1")) {
					r.add("alamat1_Surat", alamat1.toUpperCase());
					r.add("alamat2_Surat", alamat2.toUpperCase());
					r.add("alamat3_Surat", alamat3.toUpperCase());
					r.add("poskod_Surat", poskod);
					r.add("id_Bandarsurat", idbandar);
					r.add("id_Negerisurat", negeri);
				}
				//r.add("lapis", 1);
				//r.add("status_hidup", 0);
				r.add("lapis", temp_LAPIS);
				r.add("status_hidup", temp_STATUS_HIDUP);
				r.add("id_Arb", jenis_pej);
				r.add("id_saudara", socSaudaraWaris);
				r.add("id_Kemaskini", id_Masuk);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", id_Masuk);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				
				sqlOBU = r.getSQLUpdate("tblppkobpermohonan");				
				stmt.executeUpdate(sqlOBU);
				//xperlu update ob...ada dalam penambahbaikkan
			}

			/*
			 * db = new Db(); Statement stmtd = db.getStatement(); sql4 =
			 * "UPDATE TBLPPKPERMOHONAN set id_daerahmhn = "+ no_daerah
			 * +" where " + "id_permohonan = "+ IdPermohonan +" and id_fail="+
			 * IdFail +""; //System.out.println("sql4 Permohonan-->"+sql4);
			 * stmtd.executeUpdate(sql4);
			 */
			conn.commit();
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Kemaskini Fail:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
	}
	
	public void updatePermohonan_online(Hashtable data) throws Exception {
		Connection conn = null;
		Db db = null;
		// Db dbOB = null;
		// Db dbOBU = null;
		String sqlOB = "";
		String sqlOBU = "";
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		// SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			String IdFail = (String) data.get("IdFail");
			String IdSimati = (String) data.get("IdSimati");
			String IdPemohon = (String) data.get("IdPemohon");
			String IdPermohonan = (String) data.get("IdPermohonan");
			String no_daerah = (String) data.get("no_daerah");
			String negeri = (String) data.get("negeri");
			String no_kpbaru_pemohon = (String) data.get("no_kpbaru_pemohon");
			String no_kplama_pemohon = (String) data.get("no_kplama_pemohon");
			String nama_simati = (String) data.get("nama_simati");
			String tarikh_masuk = (String) data.get("tarikh_masuk");
			String tarikh_daftar = (String) data.get("tarikh_daftar");
			String no_kpbaru_simati = (String) data.get("no_kp_baru");
			String no_kplama_simati = (String) data.get("no_kplama_simati");
			String sel_jeniskp_simati = (String) data.get("sel_jeniskp_simati");
			String no_kplain_simati = (String) data.get("no_kplain_simati");
			String tarikh_simati = (String) data.get("tarikh_simati");
			String sel_jeniskp_pemohon = (String) data
					.get("sel_jeniskp_pemohon");
			String no_kplain_pemohon = (String) data.get("no_kplain_pemohon");
			String nama_pemohon = (String) data.get("nama_pemohon");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String bandar = (String) data.get("bandar");
			String idbandar = (String) data.get("idbandar");
			String poskod = (String) data.get("poskod");
			String id_Masuk = (String) data.get("id_Masuk");

			String txtUmurSimati = (String) data.get("txtUmurSimati");
			String socJantinaSimati = (String) data.get("socJantinaSimati");
			String txtUmurPemohon = (String) data.get("txtUmurPemohon");
			String socJantinaPemohon = (String) data.get("socJantinaPemohon");

			String no_tel = (String) data.get("no_tel");
			String nama_pelbagainegara = (String) data.get("nama_pelbagainegara");
			
			
			String jenis_pej = (String) data.get("jenis_pej");

			String no_hp = (String) data.get("no_hp");
			String taraf_penting = (String) data.get("taraf_penting");
			String jenis_pemohon = (String) data.get("jenis_pemohon");
			String adaob = (String) data.get("adaob");
			
			String socSaudaraWaris = (String) data.get("socSaudaraWaris");
			
			
			
			String id_Permohonansimati = (String) data
					.get("id_Permohonansimati");

			// String pemohonSimati = (String) data.get("pemohonSimati");
			Vector cpw = checkpemohonwaris(IdPemohon);

			// System.out.print("ADA OB??"+adaob);

			// int umursimati = Integer.parseInt(txtUmurSimati);
			// int jantinasimati = Integer.parseInt(socJantinaSimati);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String currentDate = dateFormat.format(date);

			String tarikhdaftar = "to_date('" + tarikh_daftar
					+ "','dd/MM/yyyy')";
			String tarikhsimati = "to_date('" + tarikh_simati
					+ "','dd/MM/yyyy')";

			String tarikh_mohon = "to_date('" + tarikh_masuk
					+ "','dd/MM/yyyy')";

			// db = new Db();
			Statement stmt = db.getStatement();
			sql = "UPDATE TBLPPKPERMOHONAN SET " +
				//	"tarikh_mohon=" + tarikh_mohon + ", " +
							"id_pemohon = " + IdPemohon + ","
					+ "tarikh_kemaskini = sysdate where id_permohonan = "
					+ IdPermohonan + " ";

			myLogger.debug(sql);
			stmt.executeUpdate(sql);

			db = new Db();
			// Statement stmt = db.getStatement();
			sql = "UPDATE TBLPFDFAIL SET tarikh_daftar_fail=sysdate, "
					+ "tarikh_kemaskini = sysdate where id_fail = " + IdFail
					+ " ";
			stmt.executeUpdate(sql);

			// db = new Db();
			// Statement stmtA = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.clear();
			r.update("id_simati", IdSimati);
			r.add("nama_simati", nama_simati);
			r.add("no_kp_baru", no_kpbaru_simati);
			r.add("no_kp_lama", no_kplama_simati);
			r.add("jenis_kp", sel_jeniskp_simati);
			r.add("no_kp_lain", no_kplain_simati);
			r.add("tarikh_mati", r.unquote(tarikhsimati));
			r.add("tarikh_masuk", r.unquote(tarikhdaftar));
			r.add("id_Kemaskini", id_Masuk);

			// int umursimati = Integer.parseInt(txtUmurSimati);
			// int jantinasimati = Integer.parseInt(socJantinaSimati);

			r.add("umur", txtUmurSimati);
			r.add("jantina", socJantinaSimati);

			r.add("tarikh_Kemaskini", r.unquote("sysdate"));

			sql1 = r.getSQLUpdate("tblppksimati");
			stmt.executeUpdate(sql1);

			
			sql3 = "UPDATE TBLPPKPEMOHON SET ";
			if (jenis_pemohon.equals("2")) {
				sql3 += "no_kp_baru='" + no_kpbaru_pemohon + "',umur='"
						+ txtUmurPemohon + "',jantina='" + socJantinaPemohon
						+ "',no_kp_lama='" + no_kplama_pemohon.toUpperCase()
						+ "',jenis_kp='" + sel_jeniskp_pemohon
						+ "',id_saudara='" + socSaudaraWaris
						+ "', no_kp_lain='" + no_kplain_pemohon.toUpperCase()
						+ "'," + "  no_hp='" + no_hp + "',";
			} else {

				sql3 += "no_kp_baru='',umur='',id_saudara='',jantina='',no_kp_lama='',jenis_kp='',"
						+ " no_kp_lain='', no_hp='',";
			}
			sql3 += " nama_pemohon='"
					+ Utils.escapeCharacter(nama_pemohon.toUpperCase())
					+ "',id_Kemaskini='" + id_Masuk
					+ "',tarikh_Kemaskini=sysdate ," + "alamat_1='"
					+ Utils.escapeCharacter(alamat1.toUpperCase())
					+ "', alamat_2='"
					+ Utils.escapeCharacter(alamat2.toUpperCase())
					+ "', alamat_3='"
					+ Utils.escapeCharacter(alamat3.toUpperCase())
					+ "', poskod='" + poskod + "',id_bandar='" + idbandar
					+ "', bandar='" + bandar + "', id_negeri='" + negeri + "',";
			if (jenis_pemohon.equals("1")) {}
				sql3 += "alamat1_Surat='"
						+ Utils.escapeCharacter(alamat1.toUpperCase())
						+ "', alamat2_Surat='"
						+ Utils.escapeCharacter(alamat2.toUpperCase())
						+ "', alamat3_Surat='"
						+ Utils.escapeCharacter(alamat3.toUpperCase())
						+ "', poskod_Surat='" + poskod.toUpperCase()
						+ "',id_Bandarsurat='" + idbandar
						+ "', id_Negerisurat='" + negeri 
						+ "', nama_pelbagainegara='" + nama_pelbagainegara 
						+ "', nama_pelbagainegara_surat='" + nama_pelbagainegara + "',";
			
			sql3 += " id_tarafkptg='" + taraf_penting + "', no_tel='" + no_tel
					+ "' , status_pemohon='" + jenis_pemohon + "', id_arb='"
					+ jenis_pej + "' where id_pemohon = " + IdPemohon + "";
			stmt.executeUpdate(sql3);

			if (taraf_penting != "0" && taraf_penting != "" && cpw.size() == 0) {
				r.clear();
				r.add("id_Simati", IdSimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("umur", txtUmurPemohon);
					r.add("no_Hp", no_hp);
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("jantina", "");
					r.add("umur", "");
					r.add("no_Hp", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", id_Permohonansimati);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", idbandar);
				r.add("poskod", poskod);
				r.add("id_Negeri", negeri);				
				r.add("id_saudara", socSaudaraWaris);
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", idbandar);
				r.add("id_Negerisurat", negeri);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Pemohon", IdPemohon);
				r.add("id_Arb", jenis_pej);
				r.add("id_Kemaskini", id_Masuk);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", id_Masuk);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				sqlOB = r.getSQLInsert("tblppkob");
				stmt.executeUpdate(sqlOB);
				//update pemohon xperlu update OB, ada dalam penambahbaikkan
			}

			if (taraf_penting != "0" && taraf_penting != "" && cpw.size() > 0) {
				r.clear();
				r.update("id_Pemohon", IdPemohon);
				r.add("id_Simati", IdSimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);

					r.add("no_Hp", no_hp);

				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_Hp", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", idbandar);
				r.add("poskod", poskod);
				r.add("id_Negeri", negeri);
				if (jenis_pemohon.equals("1")) {
					r.add("alamat1_Surat", alamat1.toUpperCase());
					r.add("alamat2_Surat", alamat2.toUpperCase());
					r.add("alamat3_Surat", alamat3.toUpperCase());
					r.add("poskod_Surat", poskod);
					r.add("id_Bandarsurat", idbandar);
					r.add("id_Negerisurat", negeri);
				}
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Arb", jenis_pej);
				r.add("id_saudara", socSaudaraWaris);
				r.add("id_Kemaskini", id_Masuk);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", id_Masuk);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				
				sqlOBU = r.getSQLUpdate("tblppkob");
				stmt.executeUpdate(sqlOBU);
				//kalo update pemohon xperlu update OB...ada dalam penambahbaikkan
			}
			conn.commit();
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Kemaskini Fail:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
	}

	public void updatePermohonan17Online(HttpSession session,Hashtable data) throws Exception {
		Db db = null;
		Connection conn = null;
		String sqlOB = "";
		String sqlOBU = "";
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		String sql8 = "";
		String sql99 = "";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			String IdFail = (String) data.get("IdFail");
			String id_Suburusanstatusfail = (String) data
					.get("id_Suburusanstatusfail");
			String txtNoFailBaru = (String) data.get("txtNoFailBaru");
			String subjacket_dulu = (String) data.get("subjacket_dulu");
			String txtNoFail = (String) data.get("txtNoFail");
			String userId = (String) data.get("userId");
			String IdSimati = (String) data.get("IdSimati");
			String IdPemohon = (String) data.get("IdPemohon");
			String IdPermohonan = (String) data.get("IdPermohonan");
			String no_daerah = (String) data.get("no_daerah");
			String negeri = (String) data.get("negeri");
			String no_kpbaru_pemohon = (String) data.get("no_kpbaru_pemohon");
			String no_kplama_pemohon = (String) data.get("no_kplama_pemohon");
			String nama_simati = (String) data.get("nama_simati");
			String tarikh_masuk = (String) data.get("tarikh_masuk");
			String tarikh_daftar = (String) data.get("tarikh_daftar");
			String no_kpbaru_simati = (String) data.get("no_kp_baru");
			String no_kplama_simati = (String) data.get("no_kplama_simati");
			String sel_jeniskp_simati = (String) data.get("sel_jeniskp_simati");
			String no_kplain_simati = (String) data.get("no_kplain_simati");
			String tarikh_simati = (String) data.get("tarikh_simati");
			String sel_jeniskp_pemohon = (String) data
					.get("sel_jeniskp_pemohon");
			String no_kplain_pemohon = (String) data.get("no_kplain_pemohon");
			String nama_pemohon = (String) data.get("nama_pemohon");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String bandar = (String) data.get("bandar");
			String idbandar = (String) data.get("idbandar");
			String poskod = (String) data.get("poskod");
			String id_Masuk = (String) data.get("id_Masuk");
			String txtUmurSimati = (String) data.get("txtUmurSimati");
			String socJantinaSimati = (String) data.get("socJantinaSimati");
			String txtUmurPemohon = (String) data.get("txtUmurPemohon");
			String socJantinaPemohon = (String) data.get("socJantinaPemohon");
			String no_tel = (String) data.get("no_tel");
			String nama_pelbagainegara = (String) data.get("nama_pelbagainegara");
			String no_hp = (String) data.get("no_hp");
			String jenis_pej = (String) data.get("jenis_pej");
			String taraf_penting = (String) data.get("taraf_penting");
			String jenis_pemohon = (String) data.get("jenis_pemohon");
			String adaob = (String) data.get("adaob");
			String id_Permohonansimati = (String) data
					.get("id_Permohonansimati");
			String socSaudaraWaris = (String) data.get("socSaudaraWaris");
			Vector cpw = checkpemohonwaris(IdPemohon);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String currentDate = dateFormat.format(date);
			String tarikhdaftar = "to_date('" + tarikh_daftar
					+ "','dd/MM/yyyy')";
			String tarikhsimati = "to_date('" + tarikh_simati
					+ "','dd/MM/yyyy')";
			String tarikh_mohon = "to_date('" + tarikh_masuk
					+ "','dd/MM/yyyy')";

			
			Statement stmt44 = db.getStatement();
			sql = "UPDATE TBLPPKPERMOHONAN SET tarikh_mohon="
					+ tarikh_mohon
					+ ",id_status = 8,flag_jenis_permohonan = 1,id_kemaskini = '"
					+ userId + "',id_pemohon = " + IdPemohon + ","
					+ "tarikh_kemaskini = sysdate where id_permohonan = "
					+ IdPermohonan;
			stmt44.executeUpdate(sql);

			if (txtNoFailBaru == "") {

				int f_l = txtNoFail.length();
				int s_b = subjacket_dulu.length();
				int sb = Integer.parseInt(subjacket_dulu) + 1;
				String s = "";

				if (subjacket_dulu.equals("") || subjacket_dulu.equals("0")) {
					s = txtNoFail;
				} else {
					if (s_b == 1) {
						s = txtNoFail.substring(0, (f_l - 6));
					} else if (s_b == 2) {
						s = txtNoFail.substring(0, (f_l - 7));
					}

				}
				String getNoFile = s + "/S17-" + sb;
				Statement stmt = db.getStatement();
				sql = "UPDATE TBLPFDFAIL SET tarikh_daftar_fail=sysdate, tajuk_fail = '"
						+ getNoFile
						+ "', no_fail = '"
						+ getNoFile
						+ "', id_kemaskini = '"
						+ userId
						+ "',flag_jenis_fail = '1', "
						+ "tarikh_kemaskini = sysdate where id_fail = "
						+ IdFail + " ";
				stmt.executeUpdate(sql);
			} else {
				Statement stmt = db.getStatement();
				sql = "UPDATE TBLPFDFAIL SET tarikh_daftar_fail=sysdate, id_kemaskini = '"
						+ userId
						+ "', flag_jenis_fail = '1', "
						+ "tarikh_kemaskini = sysdate where id_fail = "
						+ IdFail + " ";
				stmt.executeUpdate(sql);
			}
			Statement stmtA = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_simati", IdSimati);
			r.add("nama_simati", nama_simati);
			r.add("no_kp_baru", no_kpbaru_simati);
			r.add("no_kp_lama", no_kplama_simati);
			r.add("jenis_kp", sel_jeniskp_simati);
			r.add("no_kp_lain", no_kplain_simati);
			r.add("tarikh_mati", r.unquote(tarikhsimati));
			r.add("tarikh_masuk", r.unquote(tarikhdaftar));
			r.add("id_Kemaskini", id_Masuk);
			r.add("umur", txtUmurSimati);
			r.add("jantina", socJantinaSimati);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			sql1 = r.getSQLUpdate("tblppksimati");
			stmtA.executeUpdate(sql1);

			
			Statement stmtc = db.getStatement();			
			
			r.clear();
			r.update("id_pemohon", IdPemohon);
			if (jenis_pemohon.equals("2")) {
				r.add("no_kp_baru", no_kpbaru_pemohon);
				r.add("no_kp_lama", no_kplama_pemohon.toUpperCase());
				r.add("jenis_kp", sel_jeniskp_pemohon);
				r.add("no_kp_lain", no_kplain_pemohon.toUpperCase());
				r.add("umur", txtUmurPemohon);
				r.add("jantina", socJantinaPemohon);
				r.add("no_hp", no_hp);
			} else {
				r.add("no_kp_baru", "");
				r.add("no_kp_lama", "");
				r.add("jenis_kp", "");
				r.add("no_kp_lain", "");
				r.add("umur", "");
				r.add("jantina", "");
				r.add("no_hp", "");
			}
			r.add("nama_pemohon", nama_pemohon.toUpperCase());			
			if (jenis_pemohon.equals("1")) {}
			r.add("alamat_1", alamat1.toUpperCase());
			r.add("alamat_2", alamat2.toUpperCase());
			r.add("alamat_3", alamat3.toUpperCase());
			r.add("poskod", poskod);
			r.add("bandar", bandar);
			r.add("id_bandar", idbandar);
			r.add("id_negeri", negeri);
			r.add("alamat1_surat", alamat1.toUpperCase());
			r.add("alamat2_surat", alamat2.toUpperCase());
			r.add("alamat3_surat", alamat3.toUpperCase());
			r.add("poskod_surat", poskod);
			r.add("id_bandarsurat", idbandar);
			r.add("bandar_surat", bandar);
			r.add("id_negerisurat", negeri);
			
			r.add("no_tel", no_tel);
			r.add("id_tarafkptg", taraf_penting);
			r.add("status_pemohon", jenis_pemohon);
			r.add("id_Arb", jenis_pej);
			
			r.add("id_saudara", socSaudaraWaris);
			r.add("id_masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			
			r.add("nama_pelbagainegara", nama_pelbagainegara);
			r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
			
			
			sql2 = r.getSQLUpdate("tblppkpemohon");			
			stmtc.executeUpdate(sql2);

			
			
			Statement stmt = db.getStatement();
			if (taraf_penting != "0" && taraf_penting != "" && cpw.size() == 0) {
				long id_ob = DB.getNextID("TBLPPKOB_SEQ");
				r.clear();
				r.add("id_ob", id_ob);
				r.add("id_Simati", IdSimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("umur", txtUmurPemohon);
					r.add("no_Hp", no_hp);
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("jantina", "");
					r.add("umur", "");
					r.add("no_Hp", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", id_Permohonansimati);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", idbandar);
				r.add("poskod", poskod);
				r.add("id_Negeri", negeri);				
				r.add("id_saudara", socSaudaraWaris);
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", idbandar);
				r.add("id_Negerisurat", negeri);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Pemohon", IdPemohon);
				r.add("id_Arb", jenis_pej);
				r.add("id_Kemaskini", id_Masuk);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", id_Masuk);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				
				
				sqlOB = r.getSQLInsert("tblppkob");				
				stmt.executeUpdate(sqlOB);
				
				r.clear();
				r.add("id_ob", id_ob);
				r.add("id_Simati", IdSimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("umur", txtUmurPemohon);
					r.add("no_Hp", no_hp);
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("jantina", "");
					r.add("umur", "");
					r.add("no_Hp", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", id_Permohonansimati);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", idbandar);
				r.add("poskod", poskod);
				r.add("id_Negeri", negeri);				
				r.add("id_saudara", socSaudaraWaris);
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", idbandar);
				r.add("id_Negerisurat", negeri);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Pemohon", IdPemohon);
				r.add("id_Arb", jenis_pej);
				r.add("id_Kemaskini", id_Masuk);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", id_Masuk);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				
				
				sqlOB = r.getSQLInsert("tblppkobpermohonan");				
				stmt.executeUpdate(sqlOB);
			}

			if (taraf_penting != "0" && taraf_penting != "" && cpw.size() > 0) {
				
				r.clear();
				r.update("id_Pemohon", IdPemohon);
				r.add("id_Simati", IdSimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_Hp", no_hp);
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_Hp", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				// r.add("id_Permohonansimati",id_Permohonansimati);
				r.add("no_Tel", no_tel);
				// r.add("no_Hp",no_hp);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", idbandar);
				r.add("poskod", poskod);
				r.add("id_Negeri", negeri);
				if (jenis_pemohon.equals("1")) {}
					r.add("alamat1_Surat", alamat1.toUpperCase());
					r.add("alamat2_Surat", alamat2.toUpperCase());
					r.add("alamat3_Surat", alamat3.toUpperCase());
					r.add("poskod_Surat", poskod);
					r.add("id_Bandarsurat", idbandar);
					r.add("id_Negerisurat", negeri);
				
				//r.add("lapis", 1);
				//r.add("status_hidup", 0);
				r.add("id_Arb", jenis_pej);
				r.add("id_saudara", socSaudaraWaris);
				r.add("id_Kemaskini", id_Masuk);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", id_Masuk);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				
				
				sqlOBU = r.getSQLUpdate("tblppkob");				
				stmt.executeUpdate(sqlOBU);
				//kalo update pemohon,xperlu update OB..ada dalam penambahbaikkan
				
				
				String sqlPemohon = "";
				sqlPemohon = " SELECT ID_OB,LAPIS,STATUS_HIDUP FROM TBLPPKOB WHERE ID_PEMOHON = '"+IdPemohon+"'";
				ResultSet rs1 = stmt.executeQuery(sqlPemohon);	
				String temp_ID_OB = "";
				String temp_LAPIS = "";
				String 				temp_STATUS_HIDUP = "";
				while (rs1.next()) {				
					temp_ID_OB = rs1.getString("ID_OB") == null ? "" : rs1.getString("ID_OB");
					temp_LAPIS = rs1.getString("LAPIS") == null ? "" : rs1.getString("LAPIS");
					temp_STATUS_HIDUP = rs1.getString("STATUS_HIDUP") == null ? "" : rs1.getString("STATUS_HIDUP");
				}
				
				r.add("lapis", temp_LAPIS);
				r.add("status_hidup", temp_STATUS_HIDUP);
				
				
				r.clear();
				r.update("id_Pemohon", IdPemohon);
				r.update("id_Permohonansimati",id_Permohonansimati);
				r.add("id_Simati", IdSimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_Hp", no_hp);
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_Hp", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);				
				r.add("no_Tel", no_tel);
				// r.add("no_Hp",no_hp);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", idbandar);
				r.add("poskod", poskod);
				r.add("id_Negeri", negeri);
				if (jenis_pemohon.equals("1")) {}
					r.add("alamat1_Surat", alamat1.toUpperCase());
					r.add("alamat2_Surat", alamat2.toUpperCase());
					r.add("alamat3_Surat", alamat3.toUpperCase());
					r.add("poskod_Surat", poskod);
					r.add("id_Bandarsurat", idbandar);
					r.add("id_Negerisurat", negeri);
				
				//r.add("lapis", 1);
				//r.add("status_hidup", 0);
				r.add("lapis", temp_LAPIS);
				r.add("status_hidup", temp_STATUS_HIDUP);
				r.add("id_Arb", jenis_pej);
				r.add("id_saudara", socSaudaraWaris);
				r.add("id_Kemaskini", id_Masuk);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", id_Masuk);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				
				
				sqlOBU = r.getSQLUpdate("tblppkobpermohonan");				
				stmt.executeUpdate(sqlOBU);
				//kalo update pemohon,xyah update OB...ada dalam penambahbaikkan
			}
			conn.commit();
			
			myLogger.info("SSF KEMASKINI 8");
			kemaskiniSubUrusanStatusFail(session,IdPermohonan+"",userId,"8","430",IdFail+"");

		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
	}

	public void updatePermohonanKutipan(Hashtable data) throws Exception {
		Connection conn = null;
		Db db = null;
		String sqlOB = "";
		String sqlOBU = "";
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			String txtNoFail = (String) data.get("txtNoFail");
			String IdFail = (String) data.get("IdFail");
			String IdSimati = (String) data.get("IdSimati");
			String IdPemohon = (String) data.get("IdPemohon");
			String IdPermohonan = (String) data.get("IdPermohonan");
			String no_daerah = (String) data.get("no_daerah");
			String negeri = (String) data.get("negeri");
			String no_kpbaru_pemohon = (String) data.get("no_kpbaru_pemohon");
			String no_kplama_pemohon = (String) data.get("no_kplama_pemohon");
			String nama_simati = (String) data.get("nama_simati");
			String tarikh_masuk = (String) data.get("tarikh_masuk");
			String tarikh_daftar = (String) data.get("tarikh_daftar");
			String no_kpbaru_simati = (String) data.get("no_kp_baru");
			String no_kplama_simati = (String) data.get("no_kplama_simati");
			String sel_jeniskp_simati = (String) data.get("sel_jeniskp_simati");
			String no_kplain_simati = (String) data.get("no_kplain_simati");
			String tarikh_simati = (String) data.get("tarikh_simati");
			String sel_jeniskp_pemohon = (String) data
					.get("sel_jeniskp_pemohon");
			String no_kplain_pemohon = (String) data.get("no_kplain_pemohon");
			String nama_pemohon = (String) data.get("nama_pemohon");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String bandar = (String) data.get("bandar");
			String idbandar = (String) data.get("idbandar");
			String poskod = (String) data.get("poskod");
			String id_Masuk = (String) data.get("id_Masuk");
			String txtUmurSimati = (String) data.get("txtUmurSimati");
			String socJantinaSimati = (String) data.get("socJantinaSimati");
			String txtUmurPemohon = (String) data.get("txtUmurPemohon");
			String socJantinaPemohon = (String) data.get("socJantinaPemohon");
			String no_tel = (String) data.get("no_tel");
			String nama_pelbagainegara = (String) data.get("nama_pelbagainegara");
			String no_hp = (String) data.get("no_hp");
			String jenis_pej = (String) data.get("jenis_pej");
			String taraf_penting = (String) data.get("taraf_penting");
			String jenis_pemohon = (String) data.get("jenis_pemohon");
			String adaob = (String) data.get("adaob");
			String id_Permohonansimati = (String) data
					.get("id_Permohonansimati");			
			String socSaudaraWaris = (String) data.get("socSaudaraWaris");
			Vector cpw = checkpemohonwaris(IdPemohon);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String currentDate = dateFormat.format(date);
			String tarikhdaftar = "to_date('" + tarikh_daftar
					+ "','dd/MM/yyyy')";
			String tarikhsimati = "to_date('" + tarikh_simati
					+ "','dd/MM/yyyy')";
			String tarikh_mohon = "to_date('" + tarikh_masuk
					+ "','dd/MM/yyyy')";

			db = new Db();
			Statement stmt44 = db.getStatement();
			sql = "UPDATE TBLPPKPERMOHONAN SET tarikh_mohon=" + tarikh_mohon
					+ ",id_pemohon = " + IdPemohon + ",id_kemaskini = " + id_Masuk + ","
					+ "tarikh_kemaskini = sysdate where id_permohonan = "
					+ IdPermohonan + " ";
			stmt44.executeUpdate(sql);

			
			Statement stmt = db.getStatement();
			sql = "UPDATE TBLPFDFAIL SET tarikh_daftar_fail=sysdate, no_fail='"
					+ txtNoFail + "', tajuk_fail='" + txtNoFail + "', "
					+ "tarikh_kemaskini = sysdate where id_fail = " + IdFail
					+ " ";
			stmt.executeUpdate(sql);

			
			Statement stmtA = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_simati", IdSimati);
			r.add("nama_simati", nama_simati);
			r.add("no_kp_baru", no_kpbaru_simati);
			r.add("no_kp_lama", no_kplama_simati);
			r.add("jenis_kp", sel_jeniskp_simati);
			r.add("no_kp_lain", no_kplain_simati);
			r.add("tarikh_mati", r.unquote(tarikhsimati));
			r.add("tarikh_masuk", r.unquote(tarikhdaftar));
			r.add("id_Kemaskini", id_Masuk);
			r.add("umur", txtUmurSimati);
			r.add("jantina", socJantinaSimati);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			sql1 = r.getSQLUpdate("tblppksimati");
			stmtA.executeUpdate(sql1);

			
			Statement stmtc = db.getStatement();
			
			r.clear();
			r.update("id_pemohon", IdPemohon);
			if (jenis_pemohon.equals("2")) {
				r.add("no_kp_baru", no_kpbaru_pemohon);
				r.add("no_kp_lama", no_kplama_pemohon.toUpperCase());
				r.add("jenis_kp", sel_jeniskp_pemohon);
				r.add("no_kp_lain", no_kplain_pemohon.toUpperCase());
				r.add("umur", txtUmurPemohon);
				r.add("jantina", socJantinaPemohon);
				r.add("no_hp", no_hp);
			} else {
				r.add("no_kp_baru", "");
				r.add("no_kp_lama", "");
				r.add("jenis_kp", "");
				r.add("no_kp_lain", "");
				r.add("umur", "");
				r.add("jantina", "");
				r.add("no_hp", "");

			}
			r.add("nama_pemohon", nama_pemohon.toUpperCase());
			
			if (jenis_pemohon.equals("1")) {
			r.add("alamat_1", alamat1.toUpperCase());
			r.add("alamat_2", alamat2.toUpperCase());
			r.add("alamat_3", alamat3.toUpperCase());
			r.add("poskod", poskod);
			r.add("bandar", bandar);
			r.add("id_bandar", idbandar);
			r.add("id_negeri", negeri);
			r.add("alamat1_surat", alamat1.toUpperCase());
			r.add("alamat2_surat", alamat2.toUpperCase());
			r.add("alamat3_surat", alamat3.toUpperCase());
			r.add("poskod_surat", poskod);
			r.add("id_bandarsurat", idbandar);
			r.add("bandar_surat", bandar);
			r.add("id_negerisurat", negeri);
			}
			r.add("no_tel", no_tel);

			r.add("id_tarafkptg", taraf_penting);
			r.add("status_pemohon", jenis_pemohon);
			r.add("id_Arb", jenis_pej);
			
			r.add("id_saudara", socSaudaraWaris);
			r.add("id_masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			
			
			r.add("nama_pelbagainegara", nama_pelbagainegara);
			r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
			
			
			sql2 = r.getSQLUpdate("tblppkpemohon");
			stmt.executeUpdate(sql2);
			
			
			if (taraf_penting != "0" && taraf_penting != "" && cpw.size() == 0) {
				long id_ob = DB.getNextID("TBLPPKOB_SEQ");
				r.clear();
				r.add("id_ob", id_ob);
				r.add("id_Simati", IdSimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("umur", txtUmurPemohon);
					r.add("no_Hp", no_hp);
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("jantina", "");
					r.add("umur", "");
					r.add("no_Hp", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", id_Permohonansimati);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", idbandar);
				r.add("poskod", poskod);
				r.add("id_Negeri", negeri);				
				r.add("id_saudara", socSaudaraWaris);
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", idbandar);
				r.add("id_Negerisurat", negeri);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Pemohon", IdPemohon);
				r.add("id_Arb", jenis_pej);
				r.add("id_Kemaskini", id_Masuk);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", id_Masuk);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				sqlOB = r.getSQLInsert("tblppkob");				
				stmt.executeUpdate(sqlOB);
				
				r.clear();
				r.add("id_ob", id_ob);
				r.add("id_Simati", IdSimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("umur", txtUmurPemohon);
					r.add("no_Hp", no_hp);
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("jantina", "");
					r.add("umur", "");
					r.add("no_Hp", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", id_Permohonansimati);
				r.add("no_Tel", no_tel);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", idbandar);
				r.add("poskod", poskod);
				r.add("id_Negeri", negeri);				
				r.add("id_saudara", socSaudaraWaris);
				r.add("alamat1_Surat", alamat1.toUpperCase());
				r.add("alamat2_Surat", alamat2.toUpperCase());
				r.add("alamat3_Surat", alamat3.toUpperCase());
				r.add("poskod_Surat", poskod);
				r.add("id_Bandarsurat", idbandar);
				r.add("id_Negerisurat", negeri);
				r.add("lapis", 1);
				r.add("status_hidup", 0);
				r.add("id_Pemohon", IdPemohon);
				r.add("id_Arb", jenis_pej);
				r.add("id_Kemaskini", id_Masuk);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", id_Masuk);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				
				sqlOB = r.getSQLInsert("tblppkobpermohonan");				
				stmt.executeUpdate(sqlOB);
			}

			if (taraf_penting != "0" && taraf_penting != "" && cpw.size() > 0) {
				
				r.clear();
				r.update("id_Pemohon", IdPemohon);
				r.add("id_Simati", IdSimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_Hp", no_hp);
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_Hp", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);
				// r.add("id_Permohonansimati",id_Permohonansimati);
				r.add("no_Tel", no_tel);
				// r.add("no_Hp",no_hp);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", idbandar);
				r.add("poskod", poskod);
				r.add("id_Negeri", negeri);
				if (jenis_pemohon.equals("1")) {
					r.add("alamat1_Surat", alamat1.toUpperCase());
					r.add("alamat2_Surat", alamat2.toUpperCase());
					r.add("alamat3_Surat", alamat3.toUpperCase());
					r.add("poskod_Surat", poskod);
					r.add("id_Bandarsurat", idbandar);
					r.add("id_Negerisurat", negeri);
				}
				//r.add("lapis", 1);
				//r.add("status_hidup", 0);
				r.add("id_Arb", jenis_pej);
				r.add("id_saudara", socSaudaraWaris);
				r.add("id_Kemaskini", id_Masuk);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", id_Masuk);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				sqlOBU = r.getSQLUpdate("tblppkob");				
				stmt.executeUpdate(sqlOBU);
				//update pemohon xperlu update OB,ada dalam penambahbaikan
				
				
				String sqlPemohon = "";
				sqlPemohon = " SELECT ID_OB,LAPIS,STATUS_HIDUP FROM TBLPPKOB WHERE ID_PEMOHON = '"+IdPemohon+"'";
				ResultSet rs1 = stmt.executeQuery(sqlPemohon);	
				String temp_ID_OB = "";
				String temp_LAPIS = "";
				String 				temp_STATUS_HIDUP = "";
				while (rs1.next()) {				
					temp_ID_OB = rs1.getString("ID_OB") == null ? "" : rs1.getString("ID_OB");
					temp_LAPIS = rs1.getString("LAPIS") == null ? "" : rs1.getString("LAPIS");
					temp_STATUS_HIDUP = rs1.getString("STATUS_HIDUP") == null ? "" : rs1.getString("STATUS_HIDUP");
				}
				
				r.add("lapis", temp_LAPIS);
				r.add("status_hidup", temp_STATUS_HIDUP);
				
				
				r.clear();
				r.update("id_Pemohon", IdPemohon);
				r.update("id_Permohonansimati",id_Permohonansimati);
				r.add("id_Simati", IdSimati);
				r.add("nama_Ob", nama_pemohon.toUpperCase());
				if (jenis_pemohon.equals("2")) {
					r.add("no_Kp_Baru", no_kpbaru_pemohon);
					r.add("no_Kp_Lain", no_kplain_pemohon.toUpperCase());
					r.add("no_Kp_Lama", no_kplama_pemohon.toUpperCase());
					r.add("jenis_Kp", sel_jeniskp_pemohon);
					r.add("umur", txtUmurPemohon);
					r.add("jantina", socJantinaPemohon);
					r.add("no_Hp", no_hp);
				} else {
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					r.add("no_Kp_Lama", "");
					r.add("jenis_Kp", "");
					r.add("umur", "");
					r.add("jantina", "");
					r.add("no_Hp", "");
				}
				r.add("id_Tarafkptg", taraf_penting);
				r.add("jenis_pemiutang", jenis_pemohon);				
				r.add("no_Tel", no_tel);
				// r.add("no_Hp",no_hp);
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_Bandar", idbandar);
				r.add("poskod", poskod);
				r.add("id_Negeri", negeri);
				if (jenis_pemohon.equals("1")) {
					r.add("alamat1_Surat", alamat1.toUpperCase());
					r.add("alamat2_Surat", alamat2.toUpperCase());
					r.add("alamat3_Surat", alamat3.toUpperCase());
					r.add("poskod_Surat", poskod);
					r.add("id_Bandarsurat", idbandar);
					r.add("id_Negerisurat", negeri);
				}
				//r.add("lapis", 1);
				//r.add("status_hidup", 0);
				r.add("lapis", temp_LAPIS);
				r.add("status_hidup", temp_STATUS_HIDUP);
				r.add("id_Arb", jenis_pej);
				r.add("id_saudara", socSaudaraWaris);
				r.add("id_Kemaskini", id_Masuk);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", id_Masuk);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara);
				
				sqlOBU = r.getSQLUpdate("tblppkobpermohonan");				
				stmt.executeUpdate(sqlOBU);
				//update pemohon xperlu update OB,ada dalam penambahbaikkan
			}

			conn.commit();
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
	}

	public Vector getJenisKp() throws Exception {
		Db db = null;
		String sql = "Select id_jenisnopb,keterangan"
				+ " from tblrujjenisnopb where id_jenisnopb in(4,5,6,7) ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("id_jenisnopb");
			r.add("keterangan");

			// sql = r.getSQLSelect(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id", rs.getString("id_jenisnopb"));
				h.put("keterangan", rs.getString("keterangan"));
				list.addElement(h);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public Vector getJenisKpDb(Db db) throws Exception {
		//Db db = null;
		String sql = "Select id_jenisnopb,keterangan"
				+ " from tblrujjenisnopb where id_jenisnopb in(4,5,6,7) ";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("id_jenisnopb");
			r.add("keterangan");

			// sql = r.getSQLSelect(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id", rs.getString("id_jenisnopb"));
				h.put("keterangan", rs.getString("keterangan"));
				list.addElement(h);
			}
			return list;
		} finally {
			/*if (db != null)
				db.close();*/
		}
	}

	// *** query data have 'pemilik' from database
	public void setSemak(String idPermohonan) throws Exception {

		Db db = null;
		list.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("id_semakanhantar");
			r.add("id_semakansenarai");
			r.add("id_pemohon");
			r.add("pemohon");
			r.add("catatan");

			r.set("id_permohonan", idPermohonan);

			sql = r.getSQLSelect("tblsemakanhantar");
			ResultSet rs = stmt.executeQuery(sql);
			// Vector list = new Vector();
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idSemakanHantar", rs.getString("id_semakanhantar"));
				h.put("idKementerian", rs.getString("id_semakansenarai"));
				h.put("idpemohon", rs.getString("id_pemohon"));
				h.put("pemohon", rs.getString("pemohon"));
				h.put("catatan", rs.getString("catatan"));
				list.addElement(h);
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getSemak() {
		return list;
	}

	// *** simpan data from database
	public int simpanSemak(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
			String txtNomborSijil = (String) data.get("txtNomborSijil");
			String idpermohonan = (String) data.get("idpermohonan");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_semakansenarai", idpermohonan);
			r.add("catatan", txtNomborSijil);
			sql = r.getSQLInsert("tblsemakanhantar");
			// stmt.executeUpdate(sql);
			return (int) idPermohonan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// *** update data from database
	public String updateSemak(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
			// return (int) idPermohonan;
			return String.valueOf(idPermohonan);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getJenisHa() throws Exception {
		Db db = null;
		String sql = "Select id_jenisha,kod,keterangan from tblppkrujjenisha where id_jenisha > 0 order by id_jenisha  ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("id_jenisnopb");
			r.add("keterangan");

			// sql = r.getSQLSelect(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector listjenisha = new Vector();
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				//System.out.println("TESTTT ***** = " + rs.getString("id_jenisha"));
				h.put("idjenisha", rs.getString("id_jenisha"));
				h.put("kod", rs.getString("kod"));
				h.put("keterangan", rs.getString("keterangan"));
				listjenisha.addElement(h);
			}
			return listjenisha;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void addHa(Hashtable data) throws Exception {
		 Connection conn = null;
		 Db db = null;
		String sql = "";
		try {

			String id_Permohonansimati = (String) data
					.get("id_Permohonansimati");
			String id = (String) data.get("id");
			String id1 = (String) data.get("id1");
			String socJenisHartaAlih = (String) data.get("socJenisHartaAlih");
			String txtBhgnSimati1 = (String) data.get("txtBhgnSimati1");
			String txtBhgnSimati2 = (String) data.get("txtBhgnSimati2");
			String txtNoRujukan = (String) data.get("txtNoRujukan");
			String txtNilaiTarikhMati = (String) data.get("txtNilaiTarikhMati");
			String txtNoSijil = (String) data.get("txtNoSijil");
			String txtNilaiTarikhMohon = (String) data
					.get("txtNilaiTarikhMohon");
			String txtBilUnit = (String) data.get("txtBilUnit");
			String txtNilaiSeunit = (String) data.get("txtNilaiSeunit");
			String txtAgensi = (String) data.get("txtAgensi");
			String txtCatatan = (String) data.get("txtCatatan");
			String bil = (String) data.get("bil");

			String txtAlamat1 = (String) data.get("txtAlamat1");
			String txtAlamat2 = (String) data.get("txtAlamat2");
			String txtAlamat3 = (String) data.get("txtAlamat3");
			String txtPoskod = (String) data.get("txtPoskod");
			String nama_saham = (String) data.get("nama_saham");

			String butiran = (String) data.get("butiran");

			// System.out.println("POSKODDDD:" + txtPoskod);

			String socNegeriHtaam = (String) data.get("socNegeriHtaam");
			String socDaerahHtaam = (String) data.get("socDaerahHtaam");
			String id_Masuk = (String) data.get("id_Masuk");
			
			String FLAG_DAFTAR = (String) data.get("FLAG_DAFTAR");

			long idInsert = DB.getNextID("TBLPPKHA_SEQ");

			 db = new Db();
		      conn = db.getConnection();
		      conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("id_Permohonansimati", id_Permohonansimati);
			r.add("id_ha", idInsert);
			r.add("bil", bil);
			r.add("id_simati", id1);
			r.add("id_jenisha", socJenisHartaAlih);
			r.add("jenama", txtAgensi);
			r.add("no_daftar", txtNoRujukan);
			r.add("nama_saham", nama_saham);
			r.add("no_sijil", txtNoSijil);
			r.add("bil_unit", txtBilUnit);
			r.add("nilai_ha_tarikhmohon", txtNilaiTarikhMohon);
			r.add("nilai_ha_tarikhmati", txtNilaiTarikhMati);
			if (socJenisHartaAlih == "2" || socJenisHartaAlih == "3") {
				r.add("ba_simati", 1);
				r.add("bb_simati", 1);
			} else {
				r.add("ba_simati", txtBhgnSimati1);
				r.add("bb_simati", txtBhgnSimati2);
			}
			r.add("nilai_seunit", txtNilaiSeunit);
			r.add("catatan", txtCatatan);
			r.add("butiran", butiran);
			r.add("alamat_Ha1", txtAlamat1);
			r.add("alamat_Ha2", txtAlamat2);
			r.add("alamat_Ha3", txtAlamat3);
			r.add("poskod", txtPoskod);
			r.add("id_Negeri", socNegeriHtaam);
			r.add("id_Daerah", socDaerahHtaam);
			r.add("id_Masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_Kemaskini", id_Masuk);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			r.add("flag_pa", "T");
			r.add("flag_pt", "T");
			r.add("flag_selesai", "T");
			r.add("FLAG_DAFTAR",FLAG_DAFTAR);
			sql = r.getSQLInsert("tblppkha");
			stmt.executeUpdate(sql);
			
			r.clear();
			r.add("id_Permohonansimati", id_Permohonansimati);
			r.add("id_ha", idInsert);
			r.add("bil", bil);
			r.add("id_simati", id1);
			r.add("id_jenisha", socJenisHartaAlih);
			r.add("jenama", txtAgensi);
			r.add("no_daftar", txtNoRujukan);
			r.add("nama_saham", nama_saham);
			r.add("no_sijil", txtNoSijil);
			r.add("bil_unit", txtBilUnit);
			r.add("nilai_ha_tarikhmohon", txtNilaiTarikhMohon);
			r.add("nilai_ha_tarikhmati", txtNilaiTarikhMati);
			if (socJenisHartaAlih == "2" || socJenisHartaAlih == "3") {
				r.add("ba_simati", 1);
				r.add("bb_simati", 1);
			} else {
				r.add("ba_simati", txtBhgnSimati1);
				r.add("bb_simati", txtBhgnSimati2);
			}
			r.add("nilai_seunit", txtNilaiSeunit);
			r.add("catatan", txtCatatan);
			r.add("butiran", butiran);
			r.add("alamat_Ha1", txtAlamat1);
			r.add("alamat_Ha2", txtAlamat2);
			r.add("alamat_Ha3", txtAlamat3);
			r.add("poskod", txtPoskod);
			r.add("id_Negeri", socNegeriHtaam);
			r.add("id_Daerah", socDaerahHtaam);
			r.add("id_Masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_Kemaskini", id_Masuk);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			r.add("flag_pa", "T");
			r.add("flag_pt", "T");
			r.add("flag_selesai", "T");
			r.add("FLAG_DAFTAR",FLAG_DAFTAR);
			sql = r.getSQLInsert("tblppkhapermohonan");
			myLogger.info("sqladd = " +sql);
			stmt.executeUpdate(sql);
			
			
			conn.commit();
			
			
		} catch (SQLException se) { 
	    	try 
	    	{conn.rollback();
	    	} 
	    	catch (SQLException se2) 
	    	{
	    	throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	se.printStackTrace();
	    	throw new Exception("Ralat Jana Fail:"+se.getMessage());
	    	}finally {
	    	if (conn != null) conn.close(); 
	    	if (db != null)	db.close();
	    	}
	  }

	private Vector listDataHa = new Vector();
	private Vector listDataHadulu = new Vector();

	public void setDataHa(String id) throws Exception {
		Db db = null;
		listDataHa.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT H.ID_HA, H.BIL, H.ID_SIMATI, H.ID_JENISHA, H.NO_DAFTAR, "
					+ "H.NILAI_HA_TARIKHMOHON, H.NILAI_HA_TARIKHMATI, H.NO_SIJIL, J.KOD, H.BUTIRAN,H.NAMA_SAHAM, "
					+ "J.KETERANGAN, H.ALAMAT_HA1, H.ALAMAT_HA2, H.ALAMAT_HA3, H.POSKOD, H.JENAMA "
					+ "FROM TBLPPKHA H1,TBLPPKHAPERMOHONAN H, TBLPPKRUJJENISHA J, TBLPPKPERMOHONANSIMATI MS "
					+ "WHERE H1.ID_HA = H.ID_HA AND H.ID_PERMOHONANSIMATI = H1.ID_PERMOHONANSIMATI AND H.ID_JENISHA = J.ID_JENISHA  AND H.ID_SIMATI = MS.ID_SIMATI  "
					+ "AND H1.ID_PERMOHONANSIMATI = MS.ID_PERMOHONANSIMATI " +
							" AND MS.ID_PERMOHONANSIMATI = '"
					+ id + "' " + "ORDER BY H.ID_JENISHA,H.ID_HA DESC ";
			myLogger.info("setDataHa at nilaian :"+sql);
			// System.out.println("WWWW:::" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Ha",
						rs.getString("id_Ha") == null ? "" : rs
								.getString("id_Ha"));
				h.put("nilai_tm",
						rs.getString("NILAI_HA_TARIKHMOHON") == null ? "" : rs
								.getDouble("NILAI_HA_TARIKHMOHON"));
				h.put("id_Jenisha", rs.getString("id_Jenisha") == null ? ""
						: rs.getString("id_Jenisha"));
				h.put("id_Simati",
						rs.getString("id_Simati") == null ? "" : rs
								.getString("id_Simati"));
				h.put("nosijil",
						rs.getString("no_sijil") == null ? "" : rs
								.getString("no_sijil"));
				h.put("noDaftar",
						rs.getString("no_Daftar") == null ? "" : rs
								.getString("no_Daftar"));
				h.put("Kod",
						rs.getString("kod") == null ? "" : rs.getString("kod"));

				h.put("alamat1",
						rs.getString("alamat_ha1") == null ? "" : rs
								.getString("alamat_ha1"));
				h.put("alamat2",
						rs.getString("alamat_ha2") == null ? "" : rs
								.getString("alamat_ha2"));
				h.put("alamat3",
						rs.getString("alamat_ha3") == null ? "" : rs
								.getString("alamat_ha3"));
				h.put("poskod",
						rs.getString("poskod") == null ? "" : rs
								.getString("poskod"));

				h.put("Keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("nilai_tarikhmohon",
						rs.getString("nilai_ha_tarikhmohon") == null ? "" : rs
								.getDouble("nilai_ha_tarikhmohon"));
				h.put("nilai_tarikhmati",
						rs.getString("nilai_ha_tarikhmati") == null ? "" : rs
								.getDouble("nilai_ha_tarikhmati"));

				h.put("nama_saham", rs.getString("nama_saham") == null ? ""
						: rs.getString("nama_saham"));
				h.put("jenama",
						rs.getString("jenama") == null ? "" : rs
								.getString("jenama"));

				h.put("butiran",
						rs.getString("butiran") == null ? "" : rs
								.getString("butiran"));
				listDataHa.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setDataHaDulu(String id) throws Exception {
		Db db = null;
		listDataHadulu.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT" +
					" (SELECT RP.KETERANGAN FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "+
					" TBLPPKPERINTAHHAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "+
					" WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HA = H1.ID_HA AND SM.ID_PERMOHONANSIMATI = H1.ID_PERMOHONANSIMATI "+
					" AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "+
					" AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS JENIS_PERINTAH," +
					" H.ID_HA, H.BIL, H.ID_SIMATI, H.ID_JENISHA, H.NO_DAFTAR, H.NILAI_HA_TARIKHMOHON, "
					+ "H.NILAI_HA_TARIKHMATI, H.NO_SIJIL, J.KOD, J.KETERANGAN, H.ALAMAT_HA1, H.ALAMAT_HA2, "
					+ "H.ALAMAT_HA3,H.POSKOD,H.BUTIRAN,H.NAMA_SAHAM,H.JENAMA,H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI  "
					+ "FROM TBLPPKHA H1,TBLPPKHAPERMOHONAN H, TBLPPKRUJJENISHA J, TBLPPKPERMOHONANSIMATI MS,"
					+ "TBLPPKSIMATI S, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI MS1 "
					+ "WHERE H1.ID_HA = H.ID_HA AND H.ID_PERMOHONANSIMATI = '"+id+"' " +
							" AND H.ID_SIMATI = S.ID_SIMATI "
					+ "AND H.ID_JENISHA = J.ID_JENISHA  "
					+ "AND H1.ID_SIMATI = MS.ID_SIMATI  "
					+ "AND H1.ID_PERMOHONANSIMATI <> MS.ID_PERMOHONANSIMATI "
					+ "AND MS.ID_PERMOHONANSIMATI = '"
					+ id
					+ "' "
					+ "AND H1.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "
					+ "AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  "
					+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "
					+ "AND P1.NO_SUBJAKET < P.NO_SUBJAKET  "
					+ "AND S.ID_SIMATI = MS.ID_SIMATI "
					+ "ORDER BY H.ID_HA DESC";

			myLogger.info("data HA dahulu :::" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("JENIS_PERINTAH",
						rs.getString("JENIS_PERINTAH") == null ? "" : rs
								.getString("JENIS_PERINTAH"));
				h.put("id_Ha",
						rs.getString("id_Ha") == null ? "" : rs
								.getString("id_Ha"));
				h.put("id_Jenisha", rs.getString("id_Jenisha") == null ? ""
						: rs.getString("id_Jenisha"));
				h.put("id_Simati",
						rs.getString("id_Simati") == null ? "" : rs
								.getString("id_Simati"));
				h.put("nosijil",
						rs.getString("no_sijil") == null ? "" : rs
								.getString("no_sijil"));
				h.put("noDaftar",
						rs.getString("no_Daftar") == null ? "" : rs
								.getString("no_Daftar"));
				h.put("Kod",
						rs.getString("kod") == null ? "" : rs.getString("kod"));

				h.put("alamat1",
						rs.getString("alamat_ha1") == null ? "" : rs
								.getString("alamat_ha1"));
				h.put("alamat2",
						rs.getString("alamat_ha2") == null ? "" : rs
								.getString("alamat_ha2"));
				h.put("alamat3",
						rs.getString("alamat_ha3") == null ? "" : rs
								.getString("alamat_ha3"));
				h.put("poskod",
						rs.getString("poskod") == null ? "" : rs
								.getString("poskod"));

				h.put("Keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("nilai_tarikhmohon",
						rs.getString("nilai_ha_tarikhmohon") == null ? "" : rs
								.getDouble("nilai_ha_tarikhmohon"));
				h.put("nilai_tarikhmati",
						rs.getString("nilai_ha_tarikhmati") == null ? "" : rs
								.getDouble("nilai_ha_tarikhmati"));

				h.put("nilai_tm",
						rs.getString("NILAI_HA_TARIKHMOHON") == null ? "" : rs
								.getDouble("NILAI_HA_TARIKHMOHON"));

				h.put("nama_saham", rs.getString("nama_saham") == null ? ""
						: rs.getString("nama_saham"));
				h.put("jenama",
						rs.getString("jenama") == null ? "" : rs
								.getString("jenama"));

				h.put("butiran",
						rs.getString("butiran") == null ? "" : rs
								.getString("butiran"));

				h.put("flag_pt",
						rs.getString("FLAG_PT") == null ? "" : rs
								.getString("FLAG_PT"));
				h.put("flag_pa",
						rs.getString("FLAG_PA") == null ? "" : rs
								.getString("FLAG_PA"));
				h.put("flag_s",
						rs.getString("FLAG_PA") == null ? "" : rs
								.getString("FLAG_PA"));

				listDataHadulu.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setDataHaDulu_Pilihan(String id, String bkp, String lp,
			String bpa, String lpa) throws Exception {
		Db db = null;
		listDataHadulu_pilihan.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			

			sql = "SELECT " +
			" (SELECT RP.KETERANGAN FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "+
			" TBLPPKPERINTAHHAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "+
			" WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HA = H.ID_HA AND SM.ID_PERMOHONANSIMATI = H1.ID_PERMOHONANSIMATI "+
			" AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "+
			" AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS JENIS_PERINTAH," +
			" (SELECT RP.ID_JENISPERINTAH FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "+
			" TBLPPKPERINTAHHAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "+
			" WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HA = H.ID_HA AND SM.ID_PERMOHONANSIMATI = H1.ID_PERMOHONANSIMATI "+
			" AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "+
			" AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS ID_JENISPERINTAH," +
					" H.ID_HA, H.BIL, H.ID_SIMATI, H.ID_JENISHA, H.NO_DAFTAR, H.NILAI_HA_TARIKHMOHON, "
					+ "H.NILAI_HA_TARIKHMATI, H.NO_SIJIL, J.KOD, J.KETERANGAN, H.ALAMAT_HA1, H.ALAMAT_HA2, "
					+ "H.ALAMAT_HA3, H.POSKOD,H.BUTIRAN,H.NAMA_SAHAM,H.JENAMA,  "
					+ " H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI "
					+ "FROM TBLPPKHA H1,TBLPPKHAPERMOHONAN H, TBLPPKRUJJENISHA J, TBLPPKPERMOHONANSIMATI MS,"
					+ "TBLPPKSIMATI S, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI MS1 "
					+ "WHERE H1.ID_HA = H.ID_HA AND H.ID_PERMOHONANSIMATI = '"+id+"' " +
							" AND H.ID_SIMATI = S.ID_SIMATI "
					+ "AND H.ID_JENISHA = J.ID_JENISHA  "
					+ "AND H.ID_SIMATI = MS.ID_SIMATI  "
					+ "AND H1.ID_PERMOHONANSIMATI <> MS.ID_PERMOHONANSIMATI "
					+ "AND MS.ID_PERMOHONANSIMATI = '"
					+ id
					+ "' "
					+ "AND H1.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "
					+ "AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  "
					+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "
					+ "AND P1.NO_SUBJAKET < P.NO_SUBJAKET  "
					+ "AND S.ID_SIMATI = MS.ID_SIMATI ";
			if ((bkp.equals("Y") || lp.equals("Y"))
					&& (bpa.equals("T") && lpa.equals("T"))) {
				sql += " AND H.FLAG_PT = 'Y'";
			} else if ((bkp.equals("T") && lp.equals("T"))
					&& (bpa.equals("Y") || lpa.equals("Y"))) {
				sql += " AND H.FLAG_PA = 'Y'";
			} else if ((bkp.equals("Y") || lp.equals("Y"))
					&& (bpa.equals("Y") || lpa.equals("Y"))) {
				sql += " AND (H.FLAG_PA = 'Y' OR H.FLAG_PT = 'Y') ";
			} else {
				sql += "";
			}
			sql += "ORDER BY H.ID_HA DESC";

			myLogger.info("setDataHaDulu_Pilihan(:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);				
				h.put("ID_JENISPERINTAH",
						rs.getString("ID_JENISPERINTAH") == null ? "" : rs
								.getString("ID_JENISPERINTAH"));
				h.put("JENIS_PERINTAH",
						rs.getString("JENIS_PERINTAH") == null ? "" : rs
								.getString("JENIS_PERINTAH"));
				h.put("id_Ha",
						rs.getString("id_Ha") == null ? "" : rs
								.getString("id_Ha"));
				h.put("id_Jenisha", rs.getString("id_Jenisha") == null ? ""
						: rs.getString("id_Jenisha"));
				h.put("id_Simati",
						rs.getString("id_Simati") == null ? "" : rs
								.getString("id_Simati"));
				h.put("nosijil",
						rs.getString("no_sijil") == null ? "" : rs
								.getString("no_sijil"));
				h.put("noDaftar",
						rs.getString("no_Daftar") == null ? "" : rs
								.getString("no_Daftar"));
				h.put("Kod",
						rs.getString("kod") == null ? "" : rs.getString("kod"));

				h.put("alamat1",
						rs.getString("alamat_ha1") == null ? "" : rs
								.getString("alamat_ha1"));
				h.put("alamat2",
						rs.getString("alamat_ha2") == null ? "" : rs
								.getString("alamat_ha2"));
				h.put("alamat3",
						rs.getString("alamat_ha3") == null ? "" : rs
								.getString("alamat_ha3"));
				h.put("poskod",
						rs.getString("poskod") == null ? "" : rs
								.getString("poskod"));

				h.put("Keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("nilai_tarikhmohon",
						rs.getString("nilai_ha_tarikhmohon") == null ? "" : rs
								.getDouble("nilai_ha_tarikhmohon"));
				h.put("nilai_tarikhmati",
						rs.getString("nilai_ha_tarikhmati") == null ? "" : rs
								.getDouble("nilai_ha_tarikhmati"));

				h.put("nama_saham", rs.getString("nama_saham") == null ? ""
						: rs.getString("nama_saham"));
				h.put("jenama",
						rs.getString("jenama") == null ? "" : rs
								.getString("jenama"));

				h.put("butiran",
						rs.getString("butiran") == null ? "" : rs
								.getString("butiran"));

				listDataHadulu_pilihan.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getDataHa() {
		return listDataHa;
	}

	public Vector getDataHaDulu() {
		return listDataHadulu;
	}

	public Vector getDataHaDulu_Pilihan() {
		return listDataHadulu_pilihan;
	}

	private Vector listOverallSum = new Vector();

	public void setOverallSum(String id) throws Exception {

		

		Db db = null;
		listOverallSum.clear();
		String sql = "Select sum(nilai) as nilai " +
				" from ((Select h.nilai_ha_tarikhmohon as nilai " +
				" from tblppkha h1,tblppkhapermohonan h,Tblppkpermohonansimati ms " +
				" where h1.id_ha = h.id_ha and h1.id_Permohonansimati = h.id_Permohonansimati " +
				" and h.id_Permohonansimati = '"+id+"' and h1.id_simati = ms.id_Simati " +
						" and ms.id_Permohonansimati = "
				+ id
				+ " ) "
				+ "union (Select k.nilai_hta_tarikhmohon as nilai " +
						" from Tblppkhta k1,Tblppkhtapermohonan k, Tblppkpermohonansimati ms " +
						" where k1.id_hta = k.id_hta and k1.id_Permohonansimati = k.id_Permohonansimati  " +
						" and k.id_Permohonansimati = '"+id+"' and k1.id_simati = ms.id_Simati " +
								" and ms.id_Permohonansimati = "
				+ id + " )) X";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("nilaibesar",
						rs.getString("nilai") == null ? "" : Double
								.parseDouble(rs.getString("nilai")));
				listOverallSum.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getOverallSum() {
		return listOverallSum;
	}

	private Vector listOverallSumMati = new Vector();

	public void setOverallSumMati(String id) throws Exception {

		/*
		 * r.add("h.id_Jenisha",r.unquote("j.id_jenisha"));
		 * r.add("h.id_Simati",r.unquote("ms.id_Simati"));
		 * r.add("h.id_Permohonansimati",id);
		 * 
		 * 
		 * sql =r.getSQLSelect(
		 * "Tblppkha h, Tblppkrujjenisha j, Tblppkpermohonansimati ms");
		 */
		Db db = null;
		listOverallSumMati.clear();
		String sql = "Select sum(nilai) as nilaiMati " +
				" from ((Select h.nilai_ha_tarikhmati as nilai " +
				" from tblppkha h1,tblppkhapermohonan h, Tblppkpermohonansimati ms " +
				" where h1.id_ha = h.id_ha " +
				" and h1.id_permohonansimati = h.id_permohonansimati " +
				" and h.id_Permohonansimati = '"+id+"'  " +
				" and h1.id_simati = ms.id_Simati and ms.id_Permohonansimati = "
				+ id
				+ " ) "
				+ "union (Select k.nilai_hta_tarikhmati as nilai " +
						" from Tblppkhta k1,Tblppkhtapermohonan k, Tblppkpermohonansimati ms " +
						" where k1.id_hta = k.id_hta and k1.id_Permohonansimati = k.id_Permohonansimati" +
						" and k.id_Permohonansimati = '"+id+"' and k1.id_simati = ms.id_Simati and ms.id_Permohonansimati = "
				+ id + " )) X";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("nilaibesarmati", rs.getString("nilaiMati") == null ? ""
						: Double.parseDouble(rs.getString("nilaiMati")));
				listOverallSumMati.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getOverallSumMati() {
		return listOverallSumMati;
	}

	private Vector listSelectedDataHa = new Vector();

	public void setSelectedDataHa(String id1, String id3) throws Exception {
		Db db = null;
		listSelectedDataHa.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT H.FLAG_DAFTAR,H.ID_HA, H.BIL, H.ID_SIMATI, H.ID_JENISHA, H.ID_NEGERI, H.ID_DAERAH, H.JENAMA, H.NO_DAFTAR, H.NO_SIJIL, "
					+ " H.BIL_UNIT, H.TARIKH_HARTA, H.ALAMAT_HA1, H.ALAMAT_HA2, H.ALAMAT_HA3, H.POSKOD, H.NILAI_HA_TARIKHMOHON, "
					+ " H.NILAI_HA_TARIKHMATI, H.BA_SIMATI, H.BB_SIMATI, H.CATATAN, H.NILAI_SEUNIT, H.ID_MASUK, H.TARIKH_MASUK, "
					+ " H.ID_KEMASKINI, H.TARIKH_KEMASKINI, H.ID_DB, J.KOD, J.KETERANGAN, H.ALAMAT_HA1, H.ALAMAT_HA2, "
					+ " H.ALAMAT_HA3, H.POSKOD, H.ID_DAERAH, H.ID_NEGERI, H.NAMA_SAHAM,H.BUTIRAN "
					+ " FROM TBLPPKHA H1,TBLPPKHAPERMOHONAN H, TBLPPKRUJJENISHA J"
					+ " WHERE H1.ID_HA = H.ID_HA AND H.ID_JENISHA = J.ID_JENISHA   "
					+ " AND H.ID_HA = '" + id3 + "' ";

			System.out.println("HARTA ALIH :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("FLAG_DAFTAR",
						rs.getString("FLAG_DAFTAR") == null ? "" : rs
								.getString("FLAG_DAFTAR"));
				h.put("id_Ha",
						rs.getString("id_Ha") == null ? "" : rs
								.getString("id_Ha"));
				h.put("id_Jenisha", rs.getString("id_Jenisha") == null ? ""
						: rs.getString("id_Jenisha"));
				h.put("id_Simati",
						rs.getString("id_Simati") == null ? "" : rs
								.getString("id_Simati"));
				h.put("idnegeri",
						rs.getString("id_negeri") == null ? "" : rs
								.getString("id_negeri"));
				h.put("iddaerah",
						rs.getString("id_daerah") == null ? "" : rs
								.getString("id_daerah"));
				h.put("jenama",
						rs.getString("jenama") == null ? "" : rs
								.getString("jenama"));
				h.put("noDaftar",
						rs.getString("no_Daftar") == null ? "" : rs
								.getString("no_Daftar"));
				h.put("nosijil",
						rs.getString("no_sijil") == null ? "" : rs
								.getString("no_sijil"));
				h.put("bilunit",
						rs.getString("bil_unit") == null ? "" : rs
								.getString("bil_unit"));
				h.put("tarikhharta", rs.getString("tarikh_harta") == null ? ""
						: rs.getString("tarikh_harta"));

				h.put("alamat1",
						rs.getString("alamat_ha1") == null ? "" : rs
								.getString("alamat_ha1"));
				h.put("alamat2",
						rs.getString("alamat_ha2") == null ? "" : rs
								.getString("alamat_ha2"));
				h.put("alamat3",
						rs.getString("alamat_ha3") == null ? "" : rs
								.getString("alamat_ha3"));

				h.put("daerah",
						rs.getString("id_Daerah") == null ? "" : rs
								.getString("id_Daerah"));
				h.put("negeri",
						rs.getString("id_Negeri") == null ? "" : rs
								.getString("id_Negeri"));

				h.put("poskod",
						rs.getString("poskod") == null ? "" : rs
								.getString("poskod"));

				h.put("nilaiha_tarikhmohon",
						rs.getString("nilai_ha_tarikhmohon") == null ? "" : rs
								.getDouble("nilai_ha_tarikhmohon"));
				h.put("nilaiha_tarikhmati",
						rs.getString("nilai_ha_tarikhmati") == null ? "" : rs
								.getDouble("nilai_ha_tarikhmati"));
				h.put("basimati",
						rs.getString("ba_simati") == null ? "" : rs
								.getString("ba_simati"));
				h.put("bbsimati",
						rs.getString("bb_simati") == null ? "" : rs
								.getString("bb_simati"));
				h.put("catatan",
						rs.getString("catatan") == null ? "" : rs
								.getString("catatan"));
				h.put("nilaiseunit", rs.getString("nilai_seunit") == null ? ""
						: rs.getDouble("nilai_seunit"));
				h.put("Kod",
						rs.getString("kod") == null ? "" : rs.getString("kod"));
				h.put("Keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("nama_saham", rs.getString("nama_saham") == null ? ""
						: rs.getString("nama_saham"));
				listSelectedDataHa.addElement(h);
				h.put("butiran",
						rs.getString("butiran") == null ? "" : rs
								.getString("butiran"));
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getSelectedDataHa() {
		return listSelectedDataHa;
	}

	public void kemaskiniHa(Hashtable data) throws Exception {
		Db db = null;
		 Connection conn = null;
		String sql = "";
		try {
			String id_Permohonansimati = (String) data.get("id_Permohonansimati");
			String id1 = (String) data.get("id1");
			String id3 = (String) data.get("id3");
			String socJenisHartaAlih = (String) data.get("socJenisHartaAlih");
			String txtBhgnSimati1 = (String) data.get("txtBhgnSimati1");
			String txtBhgnSimati2 = (String) data.get("txtBhgnSimati2");
			String txtNoRujukan = (String) data.get("txtNoRujukan");
			String txtNilaiTarikhMati = (String) data.get("txtNilaiTarikhMati");
			String txtNoSijil = (String) data.get("txtNoSijil");
			String txtNilaiTarikhMohon = (String) data
					.get("txtNilaiTarikhMohon");
			String txtBilUnit = (String) data.get("txtBilUnit");
			String txtNilaiSeunit = (String) data.get("txtNilaiSeunit");
			String Agensi = (String) data.get("Agensi");
			String nama_saham = (String) data.get("nama_saham");
			String txtCatatan = (String) data.get("txtCatatan");
			String bil = (String) data.get("bil");
			Double valuenilaimohon = 0.00;
			Double valuenilaimati = 0.00;
			if ((String) data.get("txtNilaiTarikhMohon") != "") {
				valuenilaimohon = Double.parseDouble((String) data
						.get("txtNilaiTarikhMohon"));
			}
			if ((String) data.get("txtNilaiTarikhMati") != "") {
				valuenilaimati = Double.parseDouble((String) data
						.get("txtNilaiTarikhMati"));
			}
			String txtAlamat1 = (String) data.get("txtAlamat1");
			String txtAlamat2 = (String) data.get("txtAlamat2");
			String txtAlamat3 = (String) data.get("txtAlamat3");
			String txtPoskod = (String) data.get("txtPoskod");
			String socDaerahHtaam = (String) data.get("socDaerahHtaam");
			String id_Masuk = (String) data.get("id_Masuk");
			String butiran = (String) data.get("butiran");
			String socNegeriHtaam = (String) data.get("socNegeriHtaam");
			
			String FLAG_DAFTAR = (String) data.get("FLAG_DAFTAR");

			db = new Db();
		      conn = db.getConnection();
		      conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_simati", id1);
			r.update("id_ha", id3);
			r.add("bil", bil);
			r.add("id_jenisha", socJenisHartaAlih);
			r.add("jenama", Agensi);
			r.add("no_daftar", txtNoRujukan);
			r.add("no_sijil", txtNoSijil);
			r.add("bil_unit", txtBilUnit);			
			if (txtNilaiTarikhMohon.equals("")) {
				r.add("nilai_ha_tarikhmohon", "");
			} else {
				r.add("nilai_ha_tarikhmohon", valuenilaimohon);
			}
			if (txtNilaiTarikhMati.equals("")) {
				r.add("nilai_ha_tarikhmati", "");
			} else {
				r.add("nilai_ha_tarikhmati", valuenilaimati);
			}
			if (socJenisHartaAlih == "2" || socJenisHartaAlih == "3") {
				r.add("ba_simati", 1);
				r.add("bb_simati", 1);
			} else {
				r.add("ba_simati", txtBhgnSimati1);
				r.add("bb_simati", txtBhgnSimati2);
			}
			r.add("nilai_seunit", txtNilaiSeunit);
			r.add("catatan", txtCatatan);
			r.add("alamat_Ha1", txtAlamat1);
			r.add("alamat_Ha2", txtAlamat2);
			r.add("alamat_Ha3", txtAlamat3);
			r.add("poskod", txtPoskod);
			r.add("nama_saham", nama_saham);
			r.add("butiran", butiran);
			r.add("id_Negeri", socNegeriHtaam);
			if (socDaerahHtaam != "" && socDaerahHtaam != "0") {
				r.add("id_Daerah", socDaerahHtaam);
			}
			if (socDaerahHtaam == "0") {
				r.add("id_Daerah", "");
			}
			r.add("id_Kemaskini", id_Masuk);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			r.add("FLAG_DAFTAR", FLAG_DAFTAR);
			sql = r.getSQLUpdate("tblppkha");
			System.out.println("sql = "+sql);
			stmt.executeUpdate(sql);
			
			r.clear();
			r.update("id_permohonansimati", id_Permohonansimati);
			r.update("id_ha", id3);
			r.add("bil", bil);
			r.add("id_jenisha", socJenisHartaAlih);
			r.add("jenama", Agensi);
			r.add("no_daftar", txtNoRujukan);
			r.add("no_sijil", txtNoSijil);
			r.add("bil_unit", txtBilUnit);			
			if (txtNilaiTarikhMohon.equals("")) {
				r.add("nilai_ha_tarikhmohon", "");
			} else {
				r.add("nilai_ha_tarikhmohon", valuenilaimohon);
			}
			if (txtNilaiTarikhMati.equals("")) {
				r.add("nilai_ha_tarikhmati", "");
			} else {
				r.add("nilai_ha_tarikhmati", valuenilaimati);
			}
			if (socJenisHartaAlih == "2" || socJenisHartaAlih == "3") {
				r.add("ba_simati", 1);
				r.add("bb_simati", 1);
			} else {
				r.add("ba_simati", txtBhgnSimati1);
				r.add("bb_simati", txtBhgnSimati2);
			}
			r.add("nilai_seunit", txtNilaiSeunit);
			r.add("catatan", txtCatatan);
			r.add("alamat_Ha1", txtAlamat1);
			r.add("alamat_Ha2", txtAlamat2);
			r.add("alamat_Ha3", txtAlamat3);
			r.add("poskod", txtPoskod);
			r.add("nama_saham", nama_saham);
			r.add("butiran", butiran);
			r.add("id_Negeri", socNegeriHtaam);
			if (socDaerahHtaam != "" && socDaerahHtaam != "0") {
				r.add("id_Daerah", socDaerahHtaam);
			}
			if (socDaerahHtaam == "0") {
				r.add("id_Daerah", "");
			}
			r.add("id_Kemaskini", id_Masuk);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			r.add("FLAG_DAFTAR", FLAG_DAFTAR);
			sql = r.getSQLUpdate("tblppkhapermohonan");
			System.out.println("sql = "+sql);
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException se) { 
	    	try 
	    	{conn.rollback();
	    	} 
	    	catch (SQLException se2) 
	    	{
	    	throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	se.printStackTrace();
	    	throw new Exception("Ralat Jana Fail:"+se.getMessage());
	    	}finally {
	    	if (conn != null) conn.close(); 
	    	if (db != null)	db.close();
	    	}
	  }

	public void deleteDataHa(String id1, String id3) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			/*String sql = "delete from tblppkha where id_ha = " + id3
					+ " and id_Permohonansimati = " + id1 + "";*/
	
			
			  r.clear();
		      r.add("id_ha",id3);					      	
		      sql = r.getSQLDelete("TBLPPKHAPERMOHONAN");
		      stmt.executeUpdate(sql);
		      
		      r.clear();
		      r.add("id_ha",id3);							     
		      sql = r.getSQLDelete("TBLPPKHA");
		      stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}

	// kira jumlah nilai mohon ha
	private Vector listSumDataHa = new Vector();

	public void setSumDataHa(String mati) throws Exception {
		Db db = null;
		listSumDataHa.clear();
		String sql = "Select sum(p.nilai_ha_tarikhmohon) " +
				" from Tblppkha p1,Tblppkhapermohonan p, Tblppkpermohonansimati ps " +
				" where p1.id_ha = p.id_ha and p.id_Permohonansimati = p1.id_Permohonansimati " +
				" and  p.id_Permohonansimati = '"+mati+"' " +
				" and ps.id_Permohonansimati = '"
				+ mati + "' and p1.id_simati = ps.id_simati";

		// System.out.println("listSumDataHa" + sql);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			 * r.add("h.id_Ha"); r.add("h.bil"); r.add("h.id_Simati");
			 * r.add("h.id_Jenisha"); r.add("h.id_negeri");
			 * r.add("h.id_daerah"); r.add("h.jenama"); r.add("h.no_Daftar");
			 * r.add("h.no_sijil"); r.add("h.bil_unit");
			 * r.add("h.tarikh_harta"); r.add("h.alamat_ha1");
			 * r.add("h.alamat_ha2"); r.add("h.alamat_ha3"); r.add("h.poskod");
			 * r.add("h.nilai_ha_tarikhmohon"); r.add("h.nilai_ha_tarikhmati");
			 * r.add("h.ba_simati"); r.add("h.bb_simati"); r.add("h.catatan");
			 * r.add("h.nilai_seunit"); r.add("h.id_masuk");
			 * r.add("h.tarikh_masuk"); r.add("h.id_kemaskini");
			 * r.add("h.tarikh_kemaskini"); r.add("h.id_db"); r.add("j.kod");
			 * r.add("j.keterangan");
			 * 
			 * r.add("h.id_Jenisha",r.unquote("j.id_jenisha"));
			 * r.add("h.id_Simati",id2);
			 * 
			 * //sql = r.getSQLSelect("Tblppkha h, Tblppkrujjenisha j");
			 */
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("sum_nilaimohon",
						rs.getString("sum(p.nilai_ha_tarikhmohon)") == null ? ""
								: lebah.util.Util.formatDecimal(rs
										.getDouble("sum(p.nilai_ha_tarikhmohon)")));
				listSumDataHa.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getSumDataHa() {
		return listSumDataHa;
	}

	public Vector getListtaraf() throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Tarafkptg");
			r.add("kod");
			r.add("keterangan");

			sql = r.getSQLSelect("Tblppkrujtarafkptg", "kod");
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_Tarafkptg", rs.getInt("id_Tarafkptg"));

				if (rs.getString("kod") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod"));
				}

				if (rs.getString("keterangan") == null) {
					h.put("keterangan", "");
				} else {
					h.put("keterangan", rs.getString("keterangan"));
				}
				/*
				 * if(rs.getString("description") == null){ h.put("description",
				 * ""); }else{ h.put("description",
				 * rs.getString("description")); }
				 */
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public Vector getListtarafDb(Db db) throws Exception {
		//Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Tarafkptg");
			r.add("kod");
			r.add("keterangan");

			sql = r.getSQLSelect("Tblppkrujtarafkptg", "kod");
			System.out.print("SQL 333" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_Tarafkptg", rs.getInt("id_Tarafkptg"));

				if (rs.getString("kod") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod"));
				}

				if (rs.getString("keterangan") == null) {
					h.put("keterangan", "");
				} else {
					h.put("keterangan", rs.getString("keterangan"));
				}
				/*
				 * if(rs.getString("description") == null){ h.put("description",
				 * ""); }else{ h.put("description",
				 * rs.getString("description")); }
				 */
				v.addElement(h);
			}
			return v;
		} finally {
			/*if (db != null)
				db.close();*/
		}
	}

	public Vector getListsaudara() throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Saudara");
			r.add("kod");
			r.add("keterangan");
			r.add("jantina");

			// sql = r.getSQLSelect("Tblppkrujsaudara","kod");

			sql = "SELECT id_Saudara, kod, keterangan, jantina "
					+ " FROM Tblppkrujsaudara" + " WHERE id_Saudara <> 24"
					+ " AND id_Saudara <> 25" + "" + " AND id_Saudara <> 29"
					+ " AND id_Saudara <> 30" + " AND id_Saudara <> 34"
					+ " AND id_Saudara <> 35" + "ORDER BY kod";

			// System.out.println("SAUDARA :" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_Saudara", rs.getInt("id_Saudara"));

				if (rs.getString("kod") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod"));
				}

				if (rs.getString("jantina") == null) {
					h.put("jantina", "");
				} else {
					h.put("jantina", rs.getString("jantina"));
				}

				if (rs.getString("keterangan") == null) {
					h.put("keterangan", "");
				} else {
					h.put("keterangan", rs.getString("keterangan"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	
	public Vector getListsaudaraDb(Db db) throws Exception {
		//Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Saudara");
			r.add("kod");
			r.add("keterangan");
			r.add("jantina");

			// sql = r.getSQLSelect("Tblppkrujsaudara","kod");

			sql = "SELECT id_Saudara, kod, keterangan, jantina "
					+ " FROM Tblppkrujsaudara" + " WHERE id_Saudara <> 24"
					+ " AND id_Saudara <> 25" + "" + " AND id_Saudara <> 29"
					+ " AND id_Saudara <> 30" + " AND id_Saudara <> 34"
					+ " AND id_Saudara <> 35" + "ORDER BY kod";

			// System.out.println("SAUDARA :" + sql);
			System.out.print("SQL 444" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_Saudara", rs.getInt("id_Saudara"));

				if (rs.getString("kod") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod"));
				}

				if (rs.getString("jantina") == null) {
					h.put("jantina", "");
				} else {
					h.put("jantina", rs.getString("jantina"));
				}

				if (rs.getString("keterangan") == null) {
					h.put("keterangan", "");
				} else {
					h.put("keterangan", rs.getString("keterangan"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
			/*
			if (db != null)
				db.close();
				*/
		}
	}

	
	
	public Vector getListnegeriDb(Db db) throws Exception {
		//Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		System.out.print("getListnegeriDb");
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Negeri");
			r.add("nama_Negeri");
			r.add("kod_Negeri");
			
			sql = r.getSQLSelect("Tblrujnegeri", "kod_Negeri");
			System.out.print("SQL 111" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_Negeri", rs.getInt("id_Negeri"));

				if (rs.getString("nama_Negeri") == null) {
					h.put("nama_Negeri", "");
				} else {
					h.put("nama_Negeri", rs.getString("nama_Negeri"));
				}
				if (rs.getString("kod_Negeri") == null) {
					h.put("kod_Negeri", "");
				} else {
					h.put("kod_Negeri", rs.getString("kod_Negeri"));
				}
				/*
				 * if(rs.getString("description") == null){ h.put("description",
				 * ""); }else{ h.put("description",
				 * rs.getString("description")); }
				 */
				v.addElement(h);
			}
			return v;
		} finally {
			/*if (db != null)
				db.close();*/
		}
	}
	
	public Vector getListnegeri() throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Negeri");
			r.add("nama_Negeri");
			r.add("kod_Negeri");

			sql = r.getSQLSelect("Tblrujnegeri", "kod_Negeri");
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_Negeri", rs.getInt("id_Negeri"));

				if (rs.getString("nama_Negeri") == null) {
					h.put("nama_Negeri", "");
				} else {
					h.put("nama_Negeri", rs.getString("nama_Negeri"));
				}
				if (rs.getString("kod_Negeri") == null) {
					h.put("kod_Negeri", "");
				} else {
					h.put("kod_Negeri", rs.getString("kod_Negeri"));
				}
				/*
				 * if(rs.getString("description") == null){ h.put("description",
				 * ""); }else{ h.put("description",
				 * rs.getString("description")); }
				 */
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getListbuktimati() throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Buktimati");
			r.add("keterangan");
			r.add("kod");

			sql = r.getSQLSelect("Tblppkrujbuktimati", "kod");
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_Buktimati", rs.getInt("id_Buktimati"));

				if (rs.getString("keterangan") == null) {
					h.put("keterangan", "");
				} else {
					h.put("keterangan", rs.getString("keterangan"));
				}

				if (rs.getString("kod") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod"));
				}
				/*
				 * if(rs.getString("description") == null){ h.put("description",
				 * ""); }else{ h.put("description",
				 * rs.getString("description")); }
				 */
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	
	public Vector getListbuktimatiDb(Db db) throws Exception {
		//Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Buktimati");
			r.add("keterangan");
			r.add("kod");

			sql = r.getSQLSelect("Tblppkrujbuktimati", "kod");
			System.out.print("SQL 555" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_Buktimati", rs.getInt("id_Buktimati"));

				if (rs.getString("keterangan") == null) {
					h.put("keterangan", "");
				} else {
					h.put("keterangan", rs.getString("keterangan"));
				}

				if (rs.getString("kod") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod"));
				}
				/*
				 * if(rs.getString("description") == null){ h.put("description",
				 * ""); }else{ h.put("description",
				 * rs.getString("description")); }
				 */
				v.addElement(h);
			}
			return v;
		} finally {
			//if (db != null)
			//	db.close();
		}
	}

	
	
	public void add(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			String noFail = (String) data.get("no_Fail");
			String tajukFail = (String) data.get("tajuk_Fail");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("no_Fail", noFail);
			r.add("tajuk_Fail", tajukFail);
			sql = r.getSQLInsert("tblpfdfail");

			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}

	}

	private Vector listChckId = new Vector();

	public void checkData(String id) throws Exception {
		Db db = null;
		listChckId.clear();
		String sql = "Select count(p.id_pemohon) as ids from tblppkpemohon pm, tblppkpermohonan p " +
				"where p.id_pemohon = pm.id_pemohon and p.id_permohonan = '"
				+ id + "'";
		// System.out.println("COUNT :" + sql);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("count_id",
						rs.getString("ids") == null ? "" : rs.getString("ids"));

				listChckId.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getId() {
		return listChckId;
	}

	public void updateDataNilai(String id, String id1, String user)
			throws Exception {
		Db db = null;
		// Db db1 = null;
		try {

			db = new Db();
			Statement stmt = db.getStatement();

			String sql = "update tblppkpermohonan set id_kemaskini = '"
					+ user
					+ "' , tarikh_kemaskini = sysdate, "
					+ "JUMLAH_HTA_TARIKHMOHON = (Select sum(a.NILAI_HTA_TARIKHMOHON) " +
							" from tblppkhta a1,tblppkhtapermohonan a where " +
							" a1.id_hta = a.id_hta " +
							" and a1.id_permohonansimati = a.id_permohonansimati " +
							" and a1.id_Permohonansimati = "
					+ id1
					+ "), "
					+ "JUMLAH_HTA_TARIKHMATI = (Select sum(a.NILAI_HTA_TARIKHMATI) " +
							" from tblppkhta a1,tblppkhtapermohonan a " +
							" where a1.id_hta = a.id_hta " +
							" and a1.id_permohonansimati = a.id_permohonansimati" +
							" and a1.id_Permohonansimati = "
					+ id1
					+ "), "
					+ "JUMLAH_HA_TARIKHMOHON = (Select sum(b.NILAI_HA_TARIKHMOHON) " +
							" from tblppkha b1,tblppkhapermohonan b " +
							" where b1.id_ha = b.id_ha and b1.id_permohonansimati = b.id_permohonansimati " +
							" and b1.id_Permohonansimati = "
					+ id1
					+ "),"
					+ "JUMLAH_HA_TARIKHMATI = (Select sum(b.NILAI_HA_TARIKHMATI) " +
							" from tblppkha b1,tblppkhapermohonan b " +
							" where " +
							" b1.id_ha = b.id_ha and b1.id_permohonansimati = b.id_permohonansimati " +
							" and b.id_Permohonansimati = "
					+ id1
					+ "),"
					+ "JUMLAH_HARTA_TARIKHMOHON = NVL(JUMLAH_HTA_TARIKHMOHON,0) + NVL(JUMLAH_HA_TARIKHMOHON,0), "
					+ " JUMLAH_HARTA_TARIKHMATI = NVL(JUMLAH_HTA_TARIKHMATI,0) + NVL(JUMLAH_HA_TARIKHMATI,0) "
					+ " where id_permohonan = " + id + "";
			//myLogger.info("UPDATE NILAIAN SEK8:"+sql.toUpperCase());
			stmt.executeUpdate(sql);

			// db1 = new Db();
			Statement stmtT = db.getStatement();

			String sqlT = "update tblppkpermohonan set id_kemaskini = '"
					+ user
					+ "' , tarikh_kemaskini = sysdate, "
					+ "JUM_HTA_TAMBAHAN_TKHMOHON = (Select sum(a.NILAI_HTA_TARIKHMOHON) " +
							" from tblppkhta a1,tblppkhtapermohonan a, " +
							" tblppkpermohonan p, tblppkpermohonansimati sm  " +
							" where a1.id_hta = a.id_hta and a.id_permohonansimati = '"+id1+"' " +
							" and sm.id_Permohonan = p.id_Permohonan " +
							" and a1.id_Permohonansimati = sm.id_Permohonansimati " +
							" and p.id_Permohonan = "
					+ id
					+ "), "
					+ "JUM_HTA_TAMBAHAN_TKHMATI = (Select sum(a.NILAI_HTA_TARIKHMATI) " +
							" from tblppkhta a1,tblppkhtapermohonan a, tblppkpermohonan p, tblppkpermohonansimati sm  " +
							" where a1.id_hta = a.id_hta and a.id_permohonansimati = '"+id1+"' " +
							" and sm.id_Permohonan = p.id_Permohonan " +
							" and a1.id_Permohonansimati = sm.id_Permohonansimati " +
							" and p.id_Permohonan = "
					+ id
					+ "), "
					+ "JUM_HA_TAMBAHAN_TKHMOHON = (Select sum(a.NILAI_HA_TARIKHMOHON) " +
							" from tblppkha a1,tblppkhapermohonan a, " +
							" tblppkpermohonan p, tblppkpermohonansimati sm  " +
							" where a1.id_ha = a.id_ha and a.id_permohonansimati = '"+id1+"' " +
							" and sm.id_Permohonan = p.id_Permohonan " +
							" and a1.id_Permohonansimati = sm.id_Permohonansimati and p.id_Permohonan = "
					+ id
					+ "),"
					+ "JUM_HA_TAMBAHAN_TKHMATI = (Select sum(a.NILAI_HA_TARIKHMATI) " +
							" from tblppkha a1,tblppkhapermohonan a, " +
							" tblppkpermohonan p, tblppkpermohonansimati sm  " +
							" where a1.id_ha = a.id_ha and a.id_permohonansimati = '"+id1+"' " +
							" and sm.id_Permohonan = p.id_Permohonan " +
							" and a1.id_Permohonansimati = sm.id_Permohonansimati and p.id_Permohonan = "
					+ id
					+ "),"
					+ "JUM_HARTA_TAMBAHAN_TKHMOHON = NVL(JUM_HTA_TAMBAHAN_TKHMOHON,0) + NVL(JUM_HA_TAMBAHAN_TKHMOHON,0), "
					+ "JUM_HARTA_TAMBAHAN_TKHMATI = NVL(JUM_HTA_TAMBAHAN_TKHMATI,0) + NVL(JUM_HA_TAMBAHAN_TKHMATI,0) "
					+ "where id_permohonan = " + id + "";
			stmtT.executeUpdate(sqlT);
			
			// System.out.println("UPDATE DATA NILAI :" + sql.toUpperCase());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateDataNilai17(String id, String id1, String user,String id_permohonansimati)
			throws Exception {
		Db db = null;
		// Db db1 = null;
		try {

			db = new Db();
			Statement stmt = db.getStatement();

			String sql = "update tblppkpermohonan set id_kemaskini = '"+user+"' , tarikh_kemaskini = sysdate, "
					+ "JUMLAH_HTA_TARIKHMOHON = (Select sum(a.NILAI_HTA_TARIKHMOHON) from tblppkhta a1,tblppkhtapermohonan a, " +
							" tblppkpermohonansimati pm, " +
					  " tblppkpermohonan p, tblppkpermohonan per " +
					  " where a1.id_hta = a.id_hta " +
					  "and a.id_permohonansimati = '"+id_permohonansimati+"' " +
					  		" and a.id_Simati = "+id1+" and pm.id_simati = a.id_simati " +
							" and pm.id_permohonan = p.id_permohonan " +
							" and a1.id_permohonansimati = pm.id_permohonansimati and per.id_permohonan = "
					+ id
					+ " and (p.no_subjaket < per.no_subjaket or p.no_subjaket = per.no_subjaket)), "

					+ "JUMLAH_HTA_TARIKHMATI = (Select sum(a.NILAI_HTA_TARIKHMATI) " +
							" from tblppkhta a1,tblppkhtapermohonan a, " +
							" tblppkpermohonansimati pm, tblppkpermohonan p, tblppkpermohonan per " +
							" where a.id_Simati = "
					+ id1
					+ " and a1.id_hta = a.id_hta " 
					+" and a.id_permohonansimati = '"+id_permohonansimati+"' "
							+"and  pm.id_simati = a.id_simati " +
							" and pm.id_permohonan = p.id_permohonan " +
							" and a1.id_permohonansimati = pm.id_permohonansimati and per.id_permohonan = "
					+ id
					+ " and (p.no_subjaket < per.no_subjaket or p.no_subjaket = per.no_subjaket)), "

					+ "JUMLAH_HA_TARIKHMOHON = (Select sum(a.NILAI_HA_TARIKHMOHON) " +
							" from tblppkha a1,tblppkhapermohonan a, " +
							" tblppkpermohonansimati pm, tblppkpermohonan p, " +
							" tblppkpermohonan per where a.id_Simati = "
					+ id1
					+ " and a1.id_ha = a.id_ha and a.id_permohonansimati = '"+id_permohonansimati+"' " +
							"and pm.id_simati = a.id_simati and pm.id_permohonan = p.id_permohonan " +
							" and a1.id_permohonansimati = pm.id_permohonansimati and per.id_permohonan = "
					+ id
					+ " and (p.no_subjaket < per.no_subjaket or p.no_subjaket = per.no_subjaket)),"

					+ "JUMLAH_HA_TARIKHMATI = (Select sum(a.NILAI_HA_TARIKHMATI) " +
							" from tblppkha a1,tblppkhapermohonan a," +
							" tblppkpermohonansimati pm, tblppkpermohonan p, tblppkpermohonan per where a.id_Simati = "
					+ id1
					+ " and a1.id_ha = a.id_ha and a.id_permohonansimati = '"+id_permohonansimati+"' " +
							" and pm.id_simati = a.id_simati and pm.id_permohonan = p.id_permohonan " +
							" and a1.id_permohonansimati = pm.id_permohonansimati and per.id_permohonan = "
					+ id
					+ " and (p.no_subjaket < per.no_subjaket or p.no_subjaket = per.no_subjaket)),"
					+ "JUMLAH_HARTA_TARIKHMOHON = NVL(JUMLAH_HTA_TARIKHMOHON,0) + NVL(JUMLAH_HA_TARIKHMOHON,0), "
					+ "JUMLAH_HARTA_TARIKHMATI = NVL(JUMLAH_HTA_TARIKHMATI,0) + NVL(JUMLAH_HA_TARIKHMATI,0) "
					+ "where id_permohonan = " + id + "";
			stmt.executeUpdate(sql);

			// db1 = new Db();
			Statement stmtT = db.getStatement();

			String sqlT = "update tblppkpermohonan set id_kemaskini = '"
					+ user
					+ "' , tarikh_kemaskini = sysdate, "
					+ "JUM_HTA_TAMBAHAN_TKHMOHON = (Select sum(a.NILAI_HTA_TARIKHMOHON) " +
							" from tblppkhta a1,tblppkhtapermohonan a, " +
							" tblppkpermohonan p, tblppkpermohonansimati sm  " +
							" where sm.id_Permohonan = p.id_Permohonan " +
							 " and a1.id_hta = a.id_hta " 
							+" and a.id_permohonansimati = '"+id_permohonansimati+"' "
							+" and a1.id_Permohonansimati = sm.id_Permohonansimati and p.id_Permohonan = "
					+ id
					+ "), "
					+ "JUM_HTA_TAMBAHAN_TKHMATI = (Select sum(a.NILAI_HTA_TARIKHMATI) from " +
							"tblppkhta a1,tblppkhtapermohonan a, tblppkpermohonan p, " +
							" tblppkpermohonansimati sm  " +
							" where a1.id_hta = a.id_hta and a.id_permohonansimati = '"+id_permohonansimati+"' "+
							" and sm.id_Permohonan = p.id_Permohonan " +
							" and a1.id_Permohonansimati = sm.id_Permohonansimati and p.id_Permohonan = "
					+ id
					+ "), "
					+ "JUM_HA_TAMBAHAN_TKHMOHON = (Select sum(a.NILAI_HA_TARIKHMOHON) " +
							" from tblppkha a1,tblppkhapermohonan a, " +
							" tblppkpermohonan p, tblppkpermohonansimati sm  " +
							" where " +
							" a1.id_ha = a.id_ha and a.id_permohonansimati = '"+id_permohonansimati+"' " +
							" and sm.id_Permohonan = p.id_Permohonan " +
							" and a1.id_Permohonansimati = sm.id_Permohonansimati and p.id_Permohonan = "
					+ id
					+ "),"
					+ "JUM_HA_TAMBAHAN_TKHMATI = (Select sum(a.NILAI_HA_TARIKHMATI) " +
							" from tblppkha a1,tblppkhapermohonan a, " +
							" tblppkpermohonan p, tblppkpermohonansimati sm  " +
							" where a1.id_ha = a.id_ha and a.id_permohonansimati = '"+id_permohonansimati+"'" +
							" and sm.id_Permohonan = p.id_Permohonan and a1.id_Permohonansimati = sm.id_Permohonansimati and p.id_Permohonan = "
					+ id
					+ "),"
					+ "JUM_HARTA_TAMBAHAN_TKHMOHON = NVL(JUM_HTA_TAMBAHAN_TKHMOHON,0) + NVL(JUM_HA_TAMBAHAN_TKHMOHON,0), "
					+ "JUM_HARTA_TAMBAHAN_TKHMATI = NVL(JUM_HTA_TAMBAHAN_TKHMATI,0) + NVL(JUM_HA_TAMBAHAN_TKHMATI,0) "
					+ "where id_permohonan = " + id + "";
			myLogger.info("UPDATE DATA NILAI :" + sqlT.toUpperCase());
			stmtT.executeUpdate(sqlT);

			// System.out.println("UPDATE DATA NILAI :" + sql.toUpperCase());

		} finally {
			if (db != null)
				db.close();
		}
	}

	private Vector listGetId = new Vector();

	public void setGetId(String id) throws Exception {
		Db db = null;
		listGetId.clear();
		String sql = "Select a.id_pemohon, p.id_simati From tblppkpermohonansimati ms,tblppkpermohonan b, tblppkpemohon a, "
				+ "tblppksimati p Where b.id_pemohon = a.id_pemohon and ms.id_permohonan = b.id_permohonan and p.id_simati = ms.id_simati "
				+ "and b.id_permohonan = '" + id + "'";

		myLogger.info("SQL :" + sql);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idpemohon",
						rs.getString("id_pemohon") == null ? "" : rs
								.getString("id_pemohon"));
				h.put("idsimati",
						rs.getString("id_simati") == null ? "" : rs
								.getString("id_simati"));
				listGetId.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getIds() {
		return listGetId;
	}

	public Vector getListDaerahbyNegeri(int idnegeri) throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Daerah");
			r.add("nama_Daerah");
			r.add("kod_Daerah");
			r.add("id_Negeri");

			r.add("id_Negeri", idnegeri);

			sql = r.getSQLSelect("Tblrujdaerah", "kod_Daerah");
			myLogger.info("sql****** = "+sql);
			// 
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("id_Daerah"));

				if (rs.getString("nama_Daerah") == null) {
					h.put("nama", "");
				} else {
					h.put("nama", rs.getString("nama_Daerah"));
				}
				if (rs.getString("kod_Daerah") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod_Daerah"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getListMukimbyDaerah(int iddaerah) throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			/*SQLRenderer r = new SQLRenderer();
			r.add("id_Mukim");
			r.add("nama_Mukim");
			r.add("kod_Mukim");
			r.add("id_Daerah");

			r.add("id_Daerah", iddaerah);
			r.add("FLAG_AKTIF", iddaerah);

			sql = r.getSQLSelect("Tblrujmukim", "kod_Mukim");*/
			
			sql = "Select id_Mukim, NAMA_MUKIM , kod_Mukim  FROM "
					+ "Tblrujmukim p Where id_Daerah =  '" + iddaerah + "' AND (FLAG_AKTIF is null or FLAG_AKTIF ='Y')  ";
			
			myLogger.info("sql mukim = " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("id_Mukim"));

				if (rs.getString("nama_Mukim") == null) {
					h.put("nama", "");
				} else {
					h.put("nama", rs.getString("nama_Mukim"));
				}
				if (rs.getString("kod_Mukim") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod_Mukim"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getListDaerah() throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Daerah");
			r.add("nama_Daerah");
			r.add("kod_Daerah");
			// r.add("id_Negeri");
			sql = r.getSQLSelect("Tblrujdaerah", "kod_Daerah");

			// 
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("id_Daerah"));

				if (rs.getString("nama_Daerah") == null) {
					h.put("nama", "");
				} else {
					h.put("nama", rs.getString("nama_Daerah"));
				}
				if (rs.getString("kod_Daerah") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod_Daerah"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	
	public Vector getListDaerahDb(Db db) throws Exception {
		//Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Daerah");
			r.add("nama_Daerah");
			r.add("kod_Daerah");
			// r.add("id_Negeri");
			sql = r.getSQLSelect("Tblrujdaerah", "kod_Daerah");
			System.out.print("SQL 666" + sql.toUpperCase());
			// 
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("id_Daerah"));

				if (rs.getString("nama_Daerah") == null) {
					h.put("nama", "");
				} else {
					h.put("nama", rs.getString("nama_Daerah"));
				}
				if (rs.getString("kod_Daerah") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod_Daerah"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
			//if (db != null)
			//	db.close();
		}
	}

	
	
	Vector negeri_id = null;

	public Vector getNegeriID(String idd) throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			/*
			 * r.add("id_Daerah"); r.add("nama_Daerah"); r.add("kod_Daerah");
			 * //r.add("id_Negeri"); sql =
			 * r.getSQLSelect("Tblrujdaerah","kod_Daerah");
			 */

			sql = "SELECT ID_DAERAH,NAMA_DAERAH,KOD_DAERAH,ID_NEGERI FROM TBLRUJDAERAH WHERE ID_DAERAH = '"
					+ idd + "'";
			// 
			ResultSet rs = stmt.executeQuery(sql);
			Vector negeri_id = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("id_Daerah"));
				h.put("idnegeri", rs.getInt("id_Negeri"));

				if (rs.getString("nama_Daerah") == null) {
					h.put("nama", "");
				} else {
					h.put("nama", rs.getString("nama_Daerah"));
				}
				if (rs.getString("kod_Daerah") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod_Daerah"));
				}

				negeri_id.addElement(h);
			}
			return negeri_id;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getListDaerahPindah() throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Daerah");
			r.add("nama_Daerah");
			r.add("kod_Daerah");
			// r.add("id_Negeri");
			sql = r.getSQLSelect("Tblrujdaerah", "kod_Daerah");

			// 
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("idDaerah", rs.getInt("id_Daerah"));

				if (rs.getString("nama_Daerah") == null) {
					h.put("namaDaerah", "");
				} else {
					h.put("namaDaerah", rs.getString("nama_Daerah"));
				}
				if (rs.getString("kod_Daerah") == null) {
					h.put("kodDaerah", "");
				} else {
					h.put("kodDaerah", rs.getString("kod_Daerah"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getListLuas() throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Luas");
			r.add("keterangan");
			r.add("kod_Luas");
			// r.add("id_Negeri");

			sql = r.getSQLSelect("Tblrujluas", "kod_Luas");

			// 
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("id_Luas"));

				if (rs.getString("keterangan") == null) {
					h.put("nama", "");
				} else {
					h.put("nama", rs.getString("keterangan"));
				}
				if (rs.getString("kod_Luas") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod_Luas"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public Vector getListLuasDb(Db db) throws Exception {
		//Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Luas");
			r.add("keterangan");
			r.add("kod_Luas");
			// r.add("id_Negeri");

			sql = r.getSQLSelect("Tblrujluas", "kod_Luas");
			System.out.print("SQL 777" + sql.toUpperCase());
			// 
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("id_Luas"));

				if (rs.getString("keterangan") == null) {
					h.put("nama", "");
				} else {
					h.put("nama", rs.getString("keterangan"));
				}
				if (rs.getString("kod_Luas") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod_Luas"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
			/*if (db != null)
				db.close();*/
		}
	}

	public Vector getListStatusPemilik(String status) throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Jenispb");
			r.add("keterangan");
			r.add("kod_Jenis_Pb");
			r.add("jenis_Daftar_Pb");

			r.add("jenis_Daftar_Pb", status);

			sql = r.getSQLSelect("Tblrujjenispb", "kod_Jenis_Pb");

			// 
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("id_Jenispb"));

				if (rs.getString("keterangan") == null) {
					h.put("keterangan", "");
				} else {
					h.put("keterangan", rs.getString("keterangan"));
				}
				if (rs.getString("kod_Jenis_Pb") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod_Jenis_Pb"));
				}
				if (rs.getString("jenis_Daftar_Pb") == null) {
					h.put("status", "");
				} else {
					h.put("status", rs.getString("jenis_Daftar_Pb"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	
	public Vector getListStatusPemilikDb(String status,Db db) throws Exception {
		//Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Jenispb");
			r.add("keterangan");
			r.add("kod_Jenis_Pb");
			r.add("jenis_Daftar_Pb");

			r.add("jenis_Daftar_Pb", status);

			sql = r.getSQLSelect("Tblrujjenispb", "kod_Jenis_Pb");
			System.out.print("SQL 888" + sql.toUpperCase());
			// 
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("id_Jenispb"));

				if (rs.getString("keterangan") == null) {
					h.put("keterangan", "");
				} else {
					h.put("keterangan", rs.getString("keterangan"));
				}
				if (rs.getString("kod_Jenis_Pb") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod_Jenis_Pb"));
				}
				if (rs.getString("jenis_Daftar_Pb") == null) {
					h.put("status", "");
				} else {
					h.put("status", rs.getString("jenis_Daftar_Pb"));
				}

				v.addElement(h);
			}
			return v;
		} finally {/*
			if (db != null)
				db.close();*/
		}
	}
	
	public Vector getListJenisTanah() throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Jenistanah");
			r.add("keterangan");
			r.add("kod_Jenis_Tanah");
			// 2017/09/17
			//sql = r.getSQLSelect("Tblrujjenistanah", "kod_Jenis_Tanah");
			sql = r.getSQLSelect("tblppkrujjenistanah", "kod_Jenis_Tanah");

			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("id_Jenistanah"));

				if (rs.getString("keterangan") == null) {
					h.put("keterangan", "");
				} else {
					h.put("keterangan", rs.getString("keterangan"));
				}
				if (rs.getString("kod_Jenis_Tanah") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod_Jenis_Tanah"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector getListJenisTanahDb(Db db) throws Exception {
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Jenistanah");
			r.add("keterangan");
			r.add("kod_Jenis_Tanah");
			// 2017/09/17
			//sql = r.getSQLSelect("Tblrujjenistanah", "kod_Jenis_Tanah");
			sql = r.getSQLSelect("tblppkrujjenistanah", "kod_Jenis_Tanah");
			System.out.print("SQL 999" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("id_Jenistanah"));

				if (rs.getString("keterangan") == null) {
					h.put("keterangan", "");
				} else {
					h.put("keterangan", rs.getString("keterangan"));
				}
				if (rs.getString("kod_Jenis_Tanah") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod_Jenis_Tanah"));
				}

				v.addElement(h);
			}
			return v;
		} finally {/*
			if (db != null)
				db.close();*/
		}
	}

	public Vector getListMukim() throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Mukim");
			r.add("nama_Mukim");
			r.add("kod_Mukim");
			r.add("id_Daerah");

			sql = r.getSQLSelect("Tblrujmukim", "kod_Mukim");
			// 
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("id_Mukim"));

				if (rs.getString("nama_Mukim") == null) {
					h.put("nama", "");
				} else {
					h.put("nama", rs.getString("nama_Mukim"));
				}
				if (rs.getString("kod_Mukim") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod_Mukim"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public Vector getListMukimDb(Db db) throws Exception {
		//Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Mukim");
			r.add("nama_Mukim");
			r.add("kod_Mukim");
			r.add("id_Daerah");

			sql = r.getSQLSelect("Tblrujmukim", "kod_Mukim");
			System.out.print("SQL 101010" + sql.toUpperCase());
			// 
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("id_Mukim"));

				if (rs.getString("nama_Mukim") == null) {
					h.put("nama", "");
				} else {
					h.put("nama", rs.getString("nama_Mukim"));
				}
				if (rs.getString("kod_Mukim") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod_Mukim"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
			/*if (db != null)
				db.close();*/
		}
	}

	public Vector getListJenisHakMilik(String statushakmilik) throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Jenishakmilik");
			r.add("keterangan");
			r.add("kod_Jenis_Hakmilik");
			r.add("simpanan");
			r.add("status_Hakmilik");

			r.add("status_Hakmilik", statushakmilik);

			// sql = r.getSQLSelect("Tblrujjenishakmilik",
			// "kod_Jenis_Hakmilik");

			sql = "SELECT id_Jenishakmilik, keterangan, kod_Jenis_Hakmilik, simpanan, status_Hakmilik "
					+ "FROM Tblrujjenishakmilik " +
					// "--WHERE status_Hakmilik = 'Y'  " +
					"ORDER BY kod_Jenis_Hakmilik";

			// System.out.println("JENIS HAKMILIK :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("id_Jenishakmilik"));

				if (rs.getString("keterangan") == null) {
					h.put("keterangan", "");
				} else {
					h.put("keterangan", rs.getString("keterangan"));
				}
				if (rs.getString("kod_Jenis_Hakmilik") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod_Jenis_Hakmilik"));
				}
				if (rs.getString("simpanan") == null) {
					h.put("simpanan", "");
				} else {
					h.put("simpanan", rs.getString("simpanan"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public Vector getListJenisHakMilikDb(String statushakmilik,Db db) throws Exception {
		//Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Jenishakmilik");
			r.add("keterangan");
			r.add("kod_Jenis_Hakmilik");
			r.add("simpanan");
			r.add("status_Hakmilik");

			r.add("status_Hakmilik", statushakmilik);

			// sql = r.getSQLSelect("Tblrujjenishakmilik",
			// "kod_Jenis_Hakmilik");

			sql = "SELECT id_Jenishakmilik, keterangan, kod_Jenis_Hakmilik, simpanan, status_Hakmilik "
					+ "FROM Tblrujjenishakmilik " +
					// "--WHERE status_Hakmilik = 'Y'  " +
					"ORDER BY kod_Jenis_Hakmilik";

			// System.out.println("JENIS HAKMILIK :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("id_Jenishakmilik"));

				if (rs.getString("keterangan") == null) {
					h.put("keterangan", "");
				} else {
					h.put("keterangan", rs.getString("keterangan"));
				}
				if (rs.getString("kod_Jenis_Hakmilik") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod_Jenis_Hakmilik"));
				}
				if (rs.getString("simpanan") == null) {
					h.put("simpanan", "");
				} else {
					h.put("simpanan", rs.getString("simpanan"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
		/*	if (db != null)
				db.close(); */
		}
	}

	public Vector getListKategori() throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			r.add("id_Kategori");
			r.add("keterangan");
			r.add("kod_Kategori");
			sql = r.getSQLSelect("Tblrujkategori", "kod_Kategori");
			*/			
			sql = "SELECT id_Kategori, keterangan, kod_Kategori  FROM Tblrujkategori where id_kategori not in (16137,16138,16149) ORDER BY kod_Kategori";
			myLogger.info("**** Tblrujkategori : "+sql);
			// 
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("id_Kategori"));

				if (rs.getString("keterangan") == null) {
					h.put("keterangan", "");
				} else {
					h.put("keterangan", rs.getString("keterangan"));
				}
				if (rs.getString("kod_Kategori") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod_Kategori"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector getListKategoriDb(Db db) throws Exception {
		//Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			/*
			r.add("id_Kategori");
			r.add("keterangan");
			r.add("kod_Kategori");
			sql = r.getSQLSelect("Tblrujkategori", "kod_Kategori");
			*/
			// 
			sql = "SELECT id_Kategori, keterangan, kod_Kategori  FROM Tblrujkategori where id_kategori not in (16137,16138,16149) ORDER BY kod_Kategori";
			myLogger.info("**** Tblrujkategori : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("id_Kategori"));

				if (rs.getString("keterangan") == null) {
					h.put("keterangan", "");
				} else {
					h.put("keterangan", rs.getString("keterangan"));
				}
				if (rs.getString("kod_Kategori") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod_Kategori"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
		/*	if (db != null)
				db.close();*/
		}
	}

	private Vector listGetUserId = new Vector();

	public void setGetUserId(String userid) throws Exception {
		Db db = null;
		String idUser = userid;
		String sql = "Select d.id_daerah,d.kod_daerah,d.id_negeri,r.id_pejabatjkptg,n.kod_negeri From users_internal u, tblrujdaerah d, tblrujpejabaturusan r, tblrujnegeri n "
				+ "Where u.id_negeri = d.id_negeri and d.id_daerah = r.id_daerah and d.id_negeri = r.id_negeri and r.id_negeri = n.id_negeri and u.user_id = "
				+ idUser + "";
		
		System.out.println("negeri user ::::"+sql);
		
		// String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Kategori");
			r.add("keterangan");
			r.add("kod_Kategori");

			// sql = r.getSQLSelect("Tblrujkategori","kod_Kategori");
			// //
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("idDaerah", rs.getString("id_daerah"));
				h.put("kodDaerah", rs.getString("kod_daerah"));
				h.put("idNegeri", rs.getString("id_negeri"));
				h.put("idpejabat", rs.getString("id_pejabatjkptg"));
				h.put("kodnegeri", rs.getString("kod_negeri"));
				listGetUserId.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getUserIds() {
		return listGetUserId;
	}
	
	
	
	private Vector listGetUserId_online17 = new Vector();

	public void setGetUserId_online17(String id_permohonan) throws Exception {
		Db db = null;
		//int id_permohonan = Integer.parseInt(id_permohonan);
		String sql = "select p.id_daerahmhn as id_daerah,p.id_negerimhn as id_negeri,p.id_unitpskawal as id_pejabatjkptg,d.kod_daerah,n.kod_negeri from tblppkpermohonan p,tblrujdaerah d,tblrujnegeri n "+
					 "where p.id_daerahmhn = d.id_daerah and p.id_negerimhn = n.id_negeri  and p.id_permohonan = "
				+ id_permohonan + "";
		// String sql = "";
		
		myLogger.info("setGetUserId_online17 :"+sql.toUpperCase());
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		   // sql = r.getSQLSelect("Tblrujkategori","kod_Kategori");
			// //
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("idDaerah", rs.getString("id_daerah"));
				h.put("kodDaerah", rs.getString("kod_daerah"));
				h.put("idNegeri", rs.getString("id_negeri"));
				h.put("idpejabat", rs.getString("id_pejabatjkptg"));
				h.put("kodnegeri", rs.getString("kod_negeri"));
				listGetUserId_online17.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getUserIds_online17() {
		return listGetUserId_online17;
	}
	

	public Vector getDaerahByNegeriUser(String userid) throws Exception {
		Db db = null;
		// String sql =
		// "Select d.id_daerah,d.kod_daerah,d.nama_daerah,d.id_negeri From users_internal u, tblrujdaerah d, tblrujpejabaturusan r  Where u.id_negeri = d.id_negeri and d.id_daerah = r.id_daerah and d.id_negeri = r.id_negeri and u.user_id = "+
		// userid
		// +" group by d.id_daerah,d.kod_daerah,d.nama_daerah,d.id_negeri order by d.id_daerah";
		// String sql =
		// "Select d.id_daerah,d.kod_daerah,d.nama_daerah,d.id_negeri From users_internal u, tblrujdaerah d, tblrujpejabaturusan r  Where u.id_negeri = d.id_negeri and d.id_daerah = r.id_daerah and d.id_negeri = r.id_negeri and u.user_id = 14 group by d.id_daerah,d.kod_daerah,d.nama_daerah,d.id_negeri order by d.id_daerah";

		String sql = "select id_daerah,kod_daerah,nama_daerah from tblRujDaerah where id_daerah in ( select distinct u.id_daerahurus from"
				+ " TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and u.id_jenispejabat != '3' and ur.user_id='"
				+ userid + "' ";
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
				sql += " )"

				+ " ORDER BY id_negeri,kod_daerah";

		System.out.println("@@@@" + sql);

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("id_jenisnopb");
			r.add("keterangan");

			// sql = r.getSQLSelect(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector listDaerahByUser = new Vector();
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("iddaerah",
						rs.getString("id_daerah") == null ? "" : rs
								.getString("id_daerah"));
				h.put("koddaerah",
						rs.getString("kod_daerah") == null ? "" : rs
								.getString("kod_daerah"));
				h.put("namadaerah", rs.getString("nama_daerah") == null ? ""
						: rs.getString("nama_daerah"));

				listDaerahByUser.addElement(h);
			}
			return listDaerahByUser;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getDaerahUser(String userid) throws Exception {
		Db db = null;
		String sql = "Select d.id_daerah,d.kod_daerah,d.nama_daerah,d.id_negeri,r.id_pejabat From users_internal u, tblrujdaerah d, tblrujpejabaturusan r  Where u.id_negeri = d.id_negeri and d.id_daerah = r.id_daerah and d.id_negeri = r.id_negeri and u.user_id = "
				+ userid
				+ " group by d.id_daerah,d.kod_daerah,d.nama_daerah,d.id_negeri,r.id_pejabat order by d.id_daerah";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("id_jenisnopb");
			r.add("keterangan");

			// sql = r.getSQLSelect(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector listDaerahUser = new Vector();
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("iddaerah",
						rs.getString("id_daerah") == null ? "" : rs
								.getString("id_daerah"));
				h.put("namadaerah", rs.getString("nama_daerah") == null ? ""
						: rs.getString("nama_daerah"));
				h.put("idpejabat",
						rs.getString("id_pejabat") == null ? "" : rs
								.getString("id_pejabat"));
				listDaerahUser.addElement(h);
			}
			return listDaerahUser;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// kira jumlah nilai mohon hta
	private Vector listSumDataHta = new Vector();
	private Vector listSumDataHtaDulu = new Vector();

	public void setSumDataHta(String id2) throws Exception {
		Db db = null;
		listSumDataHta.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT HTA.ID_HTA, HTA.ID_NEGERI, HTA.ID_DAERAH, HTA.ID_MUKIM, HTA.NO_HAKMILIK, "
					+ "HTA.NO_PT, HTA.BA_SIMATI, HTA.BB_SIMATI, HTA.NILAI_HTA_TARIKHMOHON, "
					+ "HTA.NILAI_HTA_TARIKHMATI, N.NAMA_NEGERI, D.NAMA_DAERAH, M.NAMA_MUKIM, "
					+ "SM.ID_SIMATI, MS.ID_PERMOHONAN, P.ID_PERMOHONAN,"
					+ " HTA.ALAMAT_HTA1,HTA.ALAMAT_HTA2,HTA.ALAMAT_HTA3, HTA.POSKOD_HTA ,"
					+ "HTA.JENIS_KEPENTINGAN,HTA.FLAG_KATEGORI_HTA,HTA.NO_ROH,HTA.JENIS_HTA,RUJ.KOD_JENIS_HAKMILIK,RUJ.KETERANGAN  "
					+ "  FROM TBLPPKPERMOHONAN P, TBLRUJJENISHAKMILIK RUJ, "
					+ "TBLPPKHTA HTA1,TBLPPKHTAPERMOHONAN HTA, TBLRUJNEGERI N, TBLRUJDAERAH D, TBLRUJMUKIM M, TBLPPKSIMATI SM, "
					+ "TBLPPKPERMOHONANSIMATI MS " +
							" WHERE HTA1.ID_HTA = HTA.ID_HTA " +
							" AND  HTA.ID_PERMOHONANSIMATI = '"+id2+"' " +
							" AND  HTA.ID_NEGERI = N.ID_NEGERI(+)  "
					+ "AND HTA.ID_DAERAH = D.ID_DAERAH(+)  AND HTA.ID_MUKIM = M.ID_MUKIM(+)  "
					+ " AND HTA.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) "
					+ "AND HTA.ID_SIMATI = SM.ID_SIMATI(+) "
					+ "AND MS.ID_PERMOHONANSIMATI = HTA1.ID_PERMOHONANSIMATI  "
					+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN AND SM.ID_SIMATI = MS.ID_SIMATI  "
					+ "AND MS.ID_PERMOHONANSIMATI = '" + id2 + "' ";

			// System.out.println("SQL NILAI HTA" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idhta",
						rs.getString("id_Hta") == null ? "" : rs
								.getString("id_Hta"));
				h.put("nama_Daerah", rs.getString("nama_Daerah") == null ? ""
						: rs.getString("nama_Daerah"));
				h.put("nama_Negeri", rs.getString("nama_Negeri") == null ? ""
						: rs.getString("nama_Negeri"));
				h.put("nama_Mukim", rs.getString("nama_Mukim") == null ? ""
						: rs.getString("nama_Mukim"));
				h.put("no_Pt",
						rs.getString("no_Pt") == null ? "" : rs
								.getString("no_Pt"));
				h.put("no_Perjanjian", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("nilai_tarikhmohon",
						rs.getString("nilai_hta_tarikhmohon") == null ? "" : rs
								.getDouble("nilai_hta_tarikhmohon"));
				h.put("nilai_tarikhmati",
						rs.getString("nilai_hta_tarikhmati") == null ? "" : rs
								.getDouble("nilai_hta_tarikhmati"));

				h.put("noroh",
						rs.getString("no_Roh") == null ? "" : rs
								.getString("no_Roh"));
				h.put("alamat1",
						rs.getString("ALAMAT_HTA1") == null ? "" : rs
								.getString("ALAMAT_HTA1"));
				h.put("alamat2",
						rs.getString("ALAMAT_HTA2") == null ? "" : rs
								.getString("ALAMAT_HTA2"));
				h.put("alamat3",
						rs.getString("ALAMAT_HTA3") == null ? "" : rs
								.getString("ALAMAT_HTA3"));
				h.put("poskod",
						rs.getString("POSKOD_HTA") == null ? "" : rs
								.getString("POSKOD_HTA"));
				h.put("jenis_penting",
						rs.getString("JENIS_KEPENTINGAN") == null ? "" : rs
								.getString("JENIS_KEPENTINGAN"));
				h.put("ketegori_hta",
						rs.getString("FLAG_KATEGORI_HTA") == null ? "" : rs
								.getString("FLAG_KATEGORI_HTA"));
				h.put("jenis_hta",
						rs.getString("JENIS_HTA") == null ? "" : rs
								.getString("JENIS_HTA"));
				h.put("kod_hakmilik",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));

				listSumDataHta.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSumDataHtaDulu(String id2) throws Exception {
		Db db = null;
		listSumDataHtaDulu.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			/*
			 * sql =
			 * "SELECT HTA.ID_HTA, HTA.ID_NEGERI, HTA.ID_DAERAH, HTA.ID_MUKIM, HTA.NO_HAKMILIK, "
			 * +
			 * "HTA.NO_PT, HTA.BA_SIMATI, HTA.BB_SIMATI, HTA.NILAI_HTA_TARIKHMOHON, "
			 * +
			 * "HTA.NILAI_HTA_TARIKHMATI, N.NAMA_NEGERI, D.NAMA_DAERAH, M.NAMA_MUKIM, "
			 * +
			 * "SM.ID_SIMATI, MS.ID_PERMOHONAN, P.ID_PERMOHONAN  FROM TBLPPKPERMOHONAN P, "
			 * +
			 * "TBLPPKHTA HTA, TBLRUJNEGERI N, TBLRUJDAERAH D, TBLRUJMUKIM M, TBLPPKSIMATI SM, "
			 * +
			 * "TBLPPKPERMOHONANSIMATI MS WHERE HTA.ID_NEGERI = N.ID_NEGERI(+)  "
			 * +
			 * "AND HTA.ID_DAERAH = D.ID_DAERAH(+)  AND HTA.ID_MUKIM = M.ID_MUKIM(+)  "
			 * + "AND HTA.ID_SIMATI = SM.ID_SIMATI(+) " +
			 * "AND MS.ID_PERMOHONANSIMATI <> HTA.ID_PERMOHONANSIMATI  " +
			 * "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN AND SM.ID_SIMATI = MS.ID_SIMATI  "
			 * + "AND MS.ID_PERMOHONANSIMATI = '" + id2 + "' ";
			 */
			sql = "SELECT HTA.ID_HTA, HTA.ID_NEGERI, HTA.ID_DAERAH, HTA.ID_MUKIM, HTA.NO_HAKMILIK, "
					+ "HTA.NO_PT, HTA.BA_SIMATI, HTA.BB_SIMATI, HTA.NILAI_HTA_TARIKHMOHON, "
					+ "HTA.NILAI_HTA_TARIKHMATI, N.NAMA_NEGERI, D.NAMA_DAERAH, M.NAMA_MUKIM, "
					+ "SM.ID_SIMATI, MS.ID_PERMOHONAN, P.ID_PERMOHONAN,  "
					+ " HTA.ALAMAT_HTA1,HTA.ALAMAT_HTA2,HTA.ALAMAT_HTA3, HTA.POSKOD_HTA ,"
					+ "HTA.JENIS_KEPENTINGAN,HTA.FLAG_KATEGORI_HTA,HTA.NO_ROH,HTA.JENIS_HTA,RUJ.KOD_JENIS_HAKMILIK,RUJ.KETERANGAN,HTA.FLAG_PA, HTA.FLAG_PT, HTA.FLAG_SELESAI  "
					+ " FROM TBLPPKPERMOHONAN P, "
					+ "TBLPPKHTA HTA, TBLRUJNEGERI N, TBLRUJDAERAH D, TBLRUJMUKIM M, TBLPPKSIMATI SM, "
					+ "TBLPPKPERMOHONANSIMATI MS,  TBLRUJJENISHAKMILIK RUJ,TBLPPKSIMATI S,  TBLPPKPERMOHONAN P1,"
					+ "TBLPPKPERMOHONANSIMATI MS1  "
					+ "WHERE HTA.ID_NEGERI = N.ID_NEGERI(+) AND HTA.ID_SIMATI = S.ID_SIMATI  "
					+ "AND HTA.ID_DAERAH = D.ID_DAERAH(+)  AND HTA.ID_MUKIM = M.ID_MUKIM(+)  "
					+ "AND HTA.ID_SIMATI = SM.ID_SIMATI(+) "
					+ " AND HTA.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) "
					+ "AND MS.ID_PERMOHONANSIMATI <> HTA.ID_PERMOHONANSIMATI  "
					+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN AND SM.ID_SIMATI = MS.ID_SIMATI  "
					+ "AND MS.ID_PERMOHONANSIMATI = '"
					+ id2
					+ "' "
					+ "AND HTA.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "
					+ "AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  "
					+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "
					+ "AND P1.NO_SUBJAKET < P.NO_SUBJAKET ORDER BY HTA.ID_HTA DESC ";

			// System.out.println("SQL NILAI HTA" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idhta",
						rs.getString("id_Hta") == null ? "" : rs
								.getString("id_Hta"));
				h.put("nama_Daerah", rs.getString("nama_Daerah") == null ? ""
						: rs.getString("nama_Daerah"));
				h.put("nama_Negeri", rs.getString("nama_Negeri") == null ? ""
						: rs.getString("nama_Negeri"));
				h.put("nama_Mukim", rs.getString("nama_Mukim") == null ? ""
						: rs.getString("nama_Mukim"));
				h.put("no_Pt",
						rs.getString("no_Pt") == null ? "" : rs
								.getString("no_Pt"));
				h.put("no_Perjanjian", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("nilai_tarikhmohon",
						rs.getString("nilai_hta_tarikhmohon") == null ? "" : rs
								.getDouble("nilai_hta_tarikhmohon"));
				h.put("nilai_tarikhmati",
						rs.getString("nilai_hta_tarikhmati") == null ? "" : rs
								.getDouble("nilai_hta_tarikhmati"));

				h.put("noroh",
						rs.getString("no_Roh") == null ? "" : rs
								.getString("no_Roh"));
				h.put("alamat1",
						rs.getString("ALAMAT_HTA1") == null ? "" : rs
								.getString("ALAMAT_HTA1"));
								
				h.put("alamat2",
						rs.getString("ALAMAT_HTA2") == null ? "" : rs
								.getString("ALAMAT_HTA2"));
				h.put("alamat3",
						rs.getString("ALAMAT_HTA3") == null ? "" : rs
								.getString("ALAMAT_HTA3"));
				h.put("poskod",
						rs.getString("POSKOD_HTA") == null ? "" : rs
								.getString("POSKOD_HTA"));
				h.put("jenis_penting",
						rs.getString("JENIS_KEPENTINGAN") == null ? "" : rs
								.getString("JENIS_KEPENTINGAN"));
				h.put("ketegori_hta",
						rs.getString("FLAG_KATEGORI_HTA") == null ? "" : rs
								.getString("FLAG_KATEGORI_HTA"));
				h.put("jenis_hta",
						rs.getString("JENIS_HTA") == null ? "" : rs
								.getString("JENIS_HTA"));
				h.put("kod_hakmilik",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));

				h.put("flag_pt",
						rs.getString("FLAG_PT") == null ? "" : rs
								.getString("FLAG_PT"));
				h.put("flag_pa",
						rs.getString("FLAG_PA") == null ? "" : rs
								.getString("FLAG_PA"));
				h.put("flag_s",
						rs.getString("FLAG_PA") == null ? "" : rs
								.getString("FLAG_PA"));

				listSumDataHtaDulu.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getSumDataHta() {
		return listSumDataHta;
	}

	public Vector getSumDataHtaDulu() {
		return listSumDataHtaDulu;
	}

	private Vector listOverallSumHta = new Vector();

	public void setOverallSumHta(String id) throws Exception {
		Db db = null;
		listOverallSumHta.clear();
		String sql = "Select sum(hta.nilai_hta_tarikhmohon) " +
				" from Tblppkhta hta1, tblppkhtapermohonan hta " +
				" where hta1.id_hta = hta.id_hta and hta1.id_Permohonansimati = hta.id_Permohonansimati " +
				" and hta1.id_Permohonansimati = "
				+ id + "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("nilaibesarmohonhta", rs.getString(1) == null ? ""
						: Double.parseDouble(rs.getString(1)));
				listOverallSumHta.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getOverallSumHta() {
		return listOverallSumHta;
	}

	public void htaamstatus(HttpSession session,String idp, String usid, String id_sub,
			String id_Fail) throws Exception {		
			//:::SUB
			myLogger.info("SSF KEMASKINI 9");
			kemaskiniSubUrusanStatusFail(session,idp+"",usid,"9","342",id_Fail+"");


	}

	public void htaamstatus17(HttpSession session,String idp, String usid, String id_sub,
			String id_Fail) throws Exception {
		
			myLogger.info("SSF KEMASKINI 10");
			kemaskiniSubUrusanStatusFail(session,idp+"",usid,"9","432",id_Fail+"");

		
	}

	public void daftarstatus(HttpSession session,String idp, String usid, String id_sub,
			String id_Fail)
	throws Exception {		
			//:::SUB
			myLogger.info("SSF KEMASKINI 11");
			kemaskiniSubUrusanStatusFail(session,idp+"",usid,"170","553",id_Fail+"");
		
	}
	
	public void kemaskiniSubUrusanStatusFail_temp(HttpSession session,String id_permohonan, String user_id,String id_status, String id_suburusanstatus,
			String id_fail)
	{}
	
	
	public void kemaskiniSubUrusanStatusFail(HttpSession session,String id_permohonan, String user_id,String id_status, String id_suburusanstatus,
			String id_fail)
	throws Exception {
		//Connection conn = null;
		//Db db = null;
		Vector list_substatus = null;
		Hashtable hash_status = null;
		
		String check_sub = "";
		String current_status = "";
		String jenis_patah_balik = "";
		String status_patah_balik = "no";
		String update_audit = "";
		/*
		Iterator iterator;
		Iterator iterator_kp;
		Iterator iterator_np;
		Iterator iterator_ryn;
		*/
		try {
			myLogger.info("SSF id_permohonan ::"+id_permohonan);
			myLogger.info("SSF user_id ::"+user_id);
			myLogger.info("SSF id_status ::"+id_status);
			myLogger.info("SSF id_suburusanstatus ::"+id_suburusanstatus);
			myLogger.info("SSF id_fail ::"+id_fail);			
			
			current_status = current_status(id_fail).get("ID_STATUS")+"";
			if(current_status!=null)
			{
			myLogger.info("SSF CURRENT STATUS :"+current_status);
			}
			//KEPUTUSANPERMOHONAN : SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN ('151','152','53','50','70','56')
			//NOTISPERBICARAAN : SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN 
			//('176','174','172','47','41','18','44','173','175','177')
			
			/*
			no itu ada step...
			1--PENDAFTARAN SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN ('8','9','170','169') 
			2--KEPUTUSANPERMOHONAN SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN ('50','53','151','152','14','70','56') 
			3--NOTIS PERBICARAAN SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN ('18','44','175','173','177')
			3--KEPUTUSANPERBICARAAN(BATAL PERBICARAAN)SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN ('47')
			3--KEPUTUSANPERBICARAAN(TANGGUH KOLATERAL)SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN ('172')
			3--KEPUTUSANPERBICARAAN(TANGGUH ROTS)SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN ('176') 
			3--KEPUTUSANPERBICARAAN(TANGGUH MT)SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN ('174')
			3--KEPUTUSANPERBICARAAN(TANGGUH PERBICARAAN)SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN ('44')
			4--KEPUTUSANPERBICARAAN(SELESAI PERBICARAAN)SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN ('41')
			5--PERINTAH (PERMOHONAN SELASAI)SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN ('25')
			6--PERINTAH (SELASAI)SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN ('21')
			7--RAYUAN (PERMOHONAN RAYUAN)SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN ('64','163','166','167','180')
			8--RAYUAN (KEPUTUSAN RAYUAN)SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN ('164','165') 
			*/
			
			Integer current_step = 0;
			Integer new_step = 0;
				
			
			//pecahkan id_status mengikut turutan urusan
			//STEP 1 PERNDAFTARAN
			HashSet <String>location_id_status_PENDAFTRAN = new HashSet <String>();
			//HashSet location_id_status_PENDAFTRAN = null;
			//String id_status_PENDAFTRAN [] = {"8","9","170","169"};					
			location_id_status_PENDAFTRAN.add("8");  
			location_id_status_PENDAFTRAN.add("9"); 
			location_id_status_PENDAFTRAN.add("170"); 
			location_id_status_PENDAFTRAN.add("169");			
			//location_id_status_PENDAFTRAN = Arrays.asList(id_status_PENDAFTRAN) ;			
			
			if (location_id_status_PENDAFTRAN.contains(current_status) == true)
			{current_step = 1;}
			if (location_id_status_PENDAFTRAN.contains(id_status) == true)
			{new_step = 1;}
			myLogger.info("location_id_status_PENDAFTRAN.contains(current_status) "+location_id_status_PENDAFTRAN.contains(current_status));
			location_id_status_PENDAFTRAN.clear();
			//location_id_status_PENDAFTRAN = null;
			
			//STEP 2 KEPUTUSAN PERMOHONAN
			//List location_id_status_KEPUTUSANPERMOHONAN = null;
			HashSet <String>location_id_status_KEPUTUSANPERMOHONAN = new HashSet <String>();
			//String id_status_KEPUTUSANPERMOHONAN [] = {"50","53","151","152","14","70"};
			//location_id_status_KEPUTUSANPERMOHONAN = Arrays.asList(id_status_KEPUTUSANPERMOHONAN);
			location_id_status_KEPUTUSANPERMOHONAN.add("50");  
			location_id_status_KEPUTUSANPERMOHONAN.add("53"); 
			location_id_status_KEPUTUSANPERMOHONAN.add("151"); 
			location_id_status_KEPUTUSANPERMOHONAN.add("152");
			location_id_status_KEPUTUSANPERMOHONAN.add("14");
			location_id_status_KEPUTUSANPERMOHONAN.add("70");			
			if (location_id_status_KEPUTUSANPERMOHONAN.contains(current_status) == true)
			{current_step = 2;}
			if (location_id_status_KEPUTUSANPERMOHONAN.contains(id_status) == true)
			{new_step = 2;}
			myLogger.info("location_id_status_KEPUTUSANPERMOHONAN(current_status) "+location_id_status_KEPUTUSANPERMOHONAN.contains(current_status));
			location_id_status_KEPUTUSANPERMOHONAN.clear();
			
			
			
			//STEP 3 NOTIS PERBICARAAN + KEPUTUSAN PERBICARAAN
			//List location_id_status_NOTISPERBICARAAN = null;
			HashSet <String>location_id_status_NOTISPERBICARAAN = new HashSet <String>();
			//String id_status_NOTISPERBICARAAN [] = {"18","44","175","173","177","47","172","176","174","44"};
			//location_id_status_NOTISPERBICARAAN = Arrays.asList(id_status_NOTISPERBICARAAN);
			location_id_status_NOTISPERBICARAAN.add("18");  
			location_id_status_NOTISPERBICARAAN.add("44"); 
			location_id_status_NOTISPERBICARAAN.add("175"); 
			location_id_status_NOTISPERBICARAAN.add("173");	
			location_id_status_NOTISPERBICARAAN.add("177");  
			location_id_status_NOTISPERBICARAAN.add("47"); 
			location_id_status_NOTISPERBICARAAN.add("172"); 
			location_id_status_NOTISPERBICARAAN.add("176");
			location_id_status_NOTISPERBICARAAN.add("174");  
			location_id_status_NOTISPERBICARAAN.add("44"); 
			if (location_id_status_NOTISPERBICARAAN.contains(current_status) == true)
			{current_step = 3;}
			if (location_id_status_NOTISPERBICARAAN.contains(id_status) == true)
			{new_step = 3;}
			myLogger.info("location_id_status_NOTISPERBICARAAN(current_status) "+location_id_status_NOTISPERBICARAAN.contains(current_status));
			location_id_status_NOTISPERBICARAAN.clear();
			
			
			
			//STEP 4 KEPUTUSAN PERBICARAAN (SELESAI)
			HashSet <String>location_id_status_KEPUTUSANPERBICARAAN = new HashSet <String>();
			//List location_id_status_KEPUTUSANPERBICARAAN = null;
			//String id_status_KEPUTUSANPERBICARAAN [] = {"41"};
			//location_id_status_KEPUTUSANPERBICARAAN= Arrays.asList(id_status_KEPUTUSANPERBICARAAN);
			location_id_status_KEPUTUSANPERBICARAAN.add("41"); 
			if (location_id_status_KEPUTUSANPERBICARAAN.contains(current_status) == true)
			{current_step = 4;}
			if (location_id_status_KEPUTUSANPERBICARAAN.contains(id_status) == true)
			{new_step = 4;}
			myLogger.info("location_id_status_KEPUTUSANPERBICARAAN(current_status) "+location_id_status_KEPUTUSANPERBICARAAN.contains(current_status));
			location_id_status_KEPUTUSANPERBICARAAN.clear();
			
			
			
			
			//STEP 5 PERINTAH (PERMOHONAN SELESAI)
			HashSet <String>location_id_status_PERINTAHPERMOHONAN = new HashSet <String>();
			//List location_id_status_PERINTAHPERMOHONAN = null;
			//String id_status_PERINTAHPERMOHONAN [] = {"25"};
			//location_id_status_PERINTAHPERMOHONAN= Arrays.asList(id_status_PERINTAHPERMOHONAN);
			location_id_status_PERINTAHPERMOHONAN.add("25"); 
			if (location_id_status_PERINTAHPERMOHONAN.contains(current_status) == true)
			{current_step = 5;}
			if (location_id_status_PERINTAHPERMOHONAN.contains(id_status) == true)
			{new_step = 5;}
			myLogger.info("location_id_status_KEPUTUSANPERBICARAAN(current_status) "+location_id_status_KEPUTUSANPERBICARAAN.contains(current_status));
			location_id_status_PERINTAHPERMOHONAN.clear();
			
			
			
			//STEP 6 PERINTAH (PERINTAH SELESAI)
			HashSet <String>location_id_status_PERINTAHSELESAI = new HashSet <String>();
			//List location_id_status_PERINTAHSELESAI = null;
			//String id_status_PERINTAHSELESAI [] = {"21"};
			//location_id_status_PERINTAHSELESAI= Arrays.asList(id_status_PERINTAHSELESAI);
			location_id_status_PERINTAHSELESAI.add("21"); 
			if (location_id_status_PERINTAHSELESAI.contains(current_status) == true)
			{current_step = 6;}
			if (location_id_status_PERINTAHSELESAI.contains(id_status) == true)
			{new_step = 6;}
			myLogger.info("location_id_status_PERINTAHSELESAI(current_status) "+location_id_status_PERINTAHSELESAI.contains(current_status));
			location_id_status_PERINTAHSELESAI.clear();
			
			
			
			//STEP 7 RAYUAN PERMOHONAN
			HashSet <String>location_id_status_RAYUANPERMOHONAN = new HashSet <String>();
			//List location_id_status_RAYUANPERMOHONAN = null;
			//String id_status_RAYUANPERMOHONAN [] = {"64","163"};
			//location_id_status_RAYUANPERMOHONAN = Arrays.asList(id_status_RAYUANPERMOHONAN);
			location_id_status_RAYUANPERMOHONAN.add("64"); 
			location_id_status_RAYUANPERMOHONAN.add("163"); 
			if (location_id_status_RAYUANPERMOHONAN.contains(current_status) == true)
			{current_step = 7;}
			if (location_id_status_RAYUANPERMOHONAN.contains(id_status) == true)
			{new_step = 7;}
			myLogger.info("location_id_status_RAYUANPERMOHONAN(current_status) "+location_id_status_RAYUANPERMOHONAN.contains(current_status));
			location_id_status_RAYUANPERMOHONAN.clear();
			
			
			
			//STEP 8 RAYUAN KEPUTUSAN
			HashSet <String>location_id_status_RAYUANKEPUTUSAN = new HashSet <String>();
			//List location_id_status_RAYUANKEPUTUSAN = null;
			//String id_status_RAYUANKEPUTUSAN[] = {"166","167","180","164","165"};
			//location_id_status_RAYUANKEPUTUSAN = Arrays.asList(id_status_RAYUANKEPUTUSAN);
			location_id_status_RAYUANPERMOHONAN.add("166"); 
			location_id_status_RAYUANPERMOHONAN.add("167"); 
			location_id_status_RAYUANPERMOHONAN.add("180"); 
			location_id_status_RAYUANPERMOHONAN.add("164"); 
			location_id_status_RAYUANPERMOHONAN.add("165"); 
			if (location_id_status_RAYUANKEPUTUSAN.contains(current_status) == true)
			{current_step = 8;}
			if (location_id_status_RAYUANKEPUTUSAN.contains(id_status) == true)
			{new_step = 8;}
			myLogger.info("location_id_status_RAYUANKEPUTUSAN(current_status) "+location_id_status_RAYUANKEPUTUSAN.contains(current_status));
			location_id_status_RAYUANKEPUTUSAN.clear();
			
			//STEP 9 BKE PERMOHONAN
			HashSet <String>location_id_status_BKEPERMOHONAN = new HashSet <String>();
			//List location_id_status_BKEPERMOHONAN = null;
			//String id_status_BKEPERMOHONAN[] = {"61","154","155"};
			//location_id_status_BKEPERMOHONAN = Arrays.asList(id_status_BKEPERMOHONAN);
			location_id_status_BKEPERMOHONAN.add("61"); 
			location_id_status_BKEPERMOHONAN.add("154"); 
			location_id_status_BKEPERMOHONAN.add("155"); 
			if (location_id_status_BKEPERMOHONAN.contains(current_status) == true)
			{current_step = 9;}
			if (location_id_status_BKEPERMOHONAN.contains(id_status) == true)
			{new_step = 9;}
			myLogger.info("location_id_status_BKEPERMOHONAN(current_status) "+location_id_status_BKEPERMOHONAN.contains(current_status));
			location_id_status_BKEPERMOHONAN.clear();
			
			//STEP 10 BKE PINDAH
			HashSet <String>location_id_status_BKEPINDAH = new HashSet <String>();
			//List location_id_status_BKEPINDAH = null;
			//String id_status_BKEPINDAH[] = {"56"};
			//location_id_status_BKEPINDAH = Arrays.asList(id_status_BKEPINDAH);
			location_id_status_BKEPINDAH.add("56"); 
			if (location_id_status_BKEPINDAH.contains(current_status) == true)
			{current_step = 10;}
			if (location_id_status_BKEPINDAH.contains(id_status) == true)
			{new_step = 10;}
			myLogger.info("location_id_status_BKEPINDAH(current_status) "+location_id_status_BKEPINDAH.contains(current_status));
			location_id_status_BKEPINDAH.clear();
			
			//STEP 11 HAPUS
			HashSet <String>location_id_status_HAPUS = new HashSet <String>();
			//List location_id_status_HAPUS = null;
			//String id_status_HAPUS[] = {"999"};
			//location_id_status_HAPUS = Arrays.asList(id_status_HAPUS);
			location_id_status_HAPUS.add("999"); 
			if (location_id_status_HAPUS.contains(current_status) == true)
			{current_step = 11;}
			if (location_id_status_HAPUS.contains(id_status) == true)
			{new_step = 11;}
			myLogger.info("location_id_status_HAPUS(current_status) "+location_id_status_HAPUS.contains(current_status));			
			location_id_status_HAPUS.clear();
			
			
			myLogger.info("**CURRENT STATUS STEP ::: "+current_step);
			myLogger.info("**NEW STATUS STEP ::: "+new_step);
			
			String update_status = "no";
			if(new_step>current_step || new_step==current_step)
			{
				update_status = "yes";	
			}
			else
			{
				if(current_status.equals("166") && id_status.equals("18"))
				{
				update_status = "special";	
				}
			}
			
			myLogger.info("JENIS update_status :"+update_status);
			
			if(update_status.equals("yes") || update_status.equals("special"))
			{
			
			//id_status yang boleh berulang
				
				
			HashSet <String>location_id_status_kp = new HashSet <String>();	
			//List location_id_status_kp = null;
			//String id_status_kp [] = {"151","152","53","50","70","56"};//keputusan permohonan
			//location_id_status_kp = Arrays.asList(id_status_kp);
			location_id_status_kp.add("151"); 
			location_id_status_kp.add("152"); 
			location_id_status_kp.add("53"); 
			location_id_status_kp.add("50"); 
			location_id_status_kp.add("70"); 
			location_id_status_kp.add("56"); 
			if (location_id_status_kp.contains(id_status) == true)
			{jenis_patah_balik = "kp";}				
			myLogger.info("location_id_status_kp(id_status) "+location_id_status_kp.contains(id_status));
			
			/*			
			if (location_id_status_kp.contains(id_status) )
			{
				jenis_patah_balik = "kp";
			}
			*/		
			//location_id_status_kp.clear();
			
			
			
			//List location_id_status_np = null;
			HashSet <String>location_id_status_np = new HashSet <String>();	
			//String id_status_np [] = {"177","176","175","174","173","172","18","44","47"};//notis perbicaraan
			//location_id_status_np= Arrays.asList(id_status_np);
			location_id_status_np.add("177"); 
			location_id_status_np.add("176"); 
			location_id_status_np.add("175"); 
			location_id_status_np.add("174"); 
			location_id_status_np.add("173"); 
			location_id_status_np.add("172"); 
			location_id_status_np.add("18"); 
			location_id_status_np.add("44"); 
			location_id_status_np.add("47"); 
			if (location_id_status_np.contains(id_status) == true)
			{jenis_patah_balik = "np";}				
			myLogger.info("location_id_status_np(id_status) "+location_id_status_np.contains(id_status));
			
			/*
			if (location_id_status_np.contains(id_status) )
			{
				jenis_patah_balik = "np";
			}*/
			//location_id_status_np.clear();
			
			//List location_id_status_ryn = null;
			HashSet <String>location_id_status_ryn = new HashSet <String>();				
			//String id_status_ryn [] = {"166","167","180","164","165"};//rayuan
			//location_id_status_ryn= Arrays.asList(id_status_ryn);
			location_id_status_ryn.add("166"); 
			location_id_status_ryn.add("167"); 
			location_id_status_ryn.add("180"); 
			location_id_status_ryn.add("164"); 
			location_id_status_ryn.add("165"); 
			if (location_id_status_ryn.contains(id_status) == true)
			{jenis_patah_balik = "ryn";}				
			myLogger.info("location_id_status_ryn(id_status) "+location_id_status_ryn.contains(id_status));
			
			//location_id_status_ryn.clear();
				
						
			//myLogger.info("SSF location_id_status_kp:"+location_id_status_kp);
			myLogger.info("SSF JENIS PATAH BALIK?:"+jenis_patah_balik);		
			
			
			String jenis_update_add = "";
			
			if(jenis_patah_balik.equals("kp"))
			{
				list_substatus = list_substatus(id_fail);			
				if(list_substatus.size()>0)
				{			
					for (int i = 0; i < list_substatus.size(); i++) 
					{
						hash_status = (Hashtable) list_substatus.get(i);
						
						if(location_id_status_kp.contains(hash_status.get("ID_STATUS")) && hash_status.get("AKTIF").equals("1"))
						{
							jenis_update_add = "set1"; 							
										
						}
						
					}
				}	
				
			}
			else if(jenis_patah_balik.equals("np"))
			{
				list_substatus = list_substatus(id_fail);			
				if(list_substatus.size()>0)
				{			
					for (int i = 0; i < list_substatus.size(); i++) 
					{
						hash_status = (Hashtable) list_substatus.get(i);
						
						if(location_id_status_np.contains(hash_status.get("ID_STATUS")) && hash_status.get("AKTIF").equals("1"))
						{
							jenis_update_add = "set2"; 							
										
						}
						
					}
				}	
				
			}
			else if(jenis_patah_balik.equals("ryn"))
			{
				list_substatus = list_substatus(id_fail);			
				if(list_substatus.size()>0)
				{			
					for (int i = 0; i < list_substatus.size(); i++) 
					{
						hash_status = (Hashtable) list_substatus.get(i);
						
						if(location_id_status_ryn.contains(hash_status.get("ID_STATUS")) && hash_status.get("AKTIF").equals("1"))
						{
							jenis_update_add = "set3"; 							
										
						}
						
					}
				}	
				
			}
			myLogger.info("SSF jenis_update_add :"+jenis_update_add);	
			
			location_id_status_kp = null;
			location_id_status_np = null;
			location_id_status_ryn = null;
			
					list_substatus = list_substatus(id_fail);			
					if(list_substatus.size()>0)
					{			
					for (int i = 0; i < list_substatus.size(); i++) {
						hash_status = (Hashtable) list_substatus.get(i);
							if(hash_status.get("ID_SUBURUSANSTATUS").equals(id_suburusanstatus))
							{
							myLogger.info("SFF ID_SUBURUSAN INI TELAH WUJUD :"+id_suburusanstatus);	
							check_sub = "wujud";
							}				
						String display_sub = "";
						display_sub = "SSF ID_SUBURUSANSTATUSFAIL ::"+hash_status.get("ID_SUBURUSANSTATUSFAIL");
						display_sub += ",SSF ID_PERMOHONAN ::"+hash_status.get("ID_PERMOHONAN");
						display_sub += ",SSF ID_FAIL ::"+hash_status.get("ID_FAIL");
						display_sub += ",SSF ID_SUBURUSANSTATUS ::"+hash_status.get("ID_SUBURUSANSTATUS");
						display_sub += ",SSF AKTIF ::"+hash_status.get("AKTIF");
						myLogger.info(display_sub);
					}
					}			
					
					if(check_sub.equals("wujud"))
					{
						if(jenis_update_add.equals("set1") || jenis_update_add.equals("set3"))
						{	
							
							SST_update_aktif_permohonan(session,id_fail,id_permohonan,id_status,
									id_suburusanstatus,user_id);
							
						/*	r.clear();
							r.update("ID_FAIL", id_fail);
							r.update("AKTIF", 1);
							r.add("AKTIF", 0);
							r.add("ID_KEMASKINI", user_id);
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							sql1 = r.getSQLUpdate("tblrujsuburusanstatusfail");
							stmt.executeUpdate(sql1);		
							
							r.clear();
							r.update("id_fail", id_fail);
							r.update("id_suburusanstatus", id_suburusanstatus);				
							r.add("AKTIF", 1);
							r.add("ID_KEMASKINI", user_id);
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							sql2 = r.getSQLUpdate("tblrujsuburusanstatusfail");
							myLogger.info("SQL UPDATE WUJUD tblrujsuburusanstatusfail :"+current_status);
							stmt.executeUpdate(sql2);
							
							r.clear();
							r.update("ID_PERMOHONAN", id_permohonan);
							r.add("ID_STATUS", id_status);
							r.add("ID_KEMASKINI", user_id);
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							sql3 = r.getSQLUpdate("tblppkpermohonan");
							stmt.executeUpdate(sql3);	
							*/
							
							update_audit = "yes";						
							myLogger.info("SSF :::::::::::::::: STEP 1");
							
						}
						else if(jenis_update_add.equals("set2"))
						{	
							SST_insert_aktif_permohonan(session,id_fail,id_permohonan,id_status,
									id_suburusanstatus,user_id);
							/*
							r.clear();
							r.update("ID_FAIL", id_fail);
							r.update("AKTIF", 1);
							r.add("AKTIF", 0);
							r.add("ID_KEMASKINI", user_id);
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							sql4 = r.getSQLUpdate("tblrujsuburusanstatusfail");
							stmt.executeUpdate(sql4);		
							
							r.clear();
							r.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
							r.add("ID_PERMOHONAN", id_permohonan);
							r.add("ID_SUBURUSANSTATUS", id_suburusanstatus);
							r.add("AKTIF", 1);
							r.add("ID_MASUK", user_id);
							r.add("TARIKH_MASUK", r.unquote("sysdate"));
							r.add("ID_KEMASKINI", user_id);
							r.add("id_Fail", id_fail);
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							sql5 = r.getSQLInsert("tblrujsuburusanstatusfail");
							stmt.executeUpdate(sql5);
							
							r.clear();
							r.update("ID_PERMOHONAN", id_permohonan);
							r.add("ID_STATUS", id_status);
							r.add("ID_KEMASKINI", user_id);
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							sql6 = r.getSQLUpdate("tblppkpermohonan");
							stmt.executeUpdate(sql6);
							*/
							myLogger.info("SSF :::::::::::::::: STEP 2");
							
							update_audit = "yes";
							
						}
						else
						{
							
							if(update_status.equals("special"))
							{
								SST_insert_aktif_permohonan(session,id_fail,id_permohonan,id_status,
										id_suburusanstatus,user_id);
								/*
								r.clear();
								r.update("ID_FAIL", id_fail);
								r.update("AKTIF", 1);
								r.add("AKTIF", 0);
								r.add("ID_KEMASKINI", user_id);
								r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
								sql4 = r.getSQLUpdate("tblrujsuburusanstatusfail");
								stmt.executeUpdate(sql4);		
								
								r.clear();
								r.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
								r.add("ID_PERMOHONAN", id_permohonan);
								r.add("ID_SUBURUSANSTATUS", id_suburusanstatus);
								r.add("AKTIF", 1);
								r.add("ID_MASUK", user_id);
								r.add("TARIKH_MASUK", r.unquote("sysdate"));
								r.add("ID_KEMASKINI", user_id);
								r.add("id_Fail", id_fail);
								r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
								sql5 = r.getSQLInsert("tblrujsuburusanstatusfail");
								stmt.executeUpdate(sql5);
								
								r.clear();
								r.update("ID_PERMOHONAN", id_permohonan);
								r.add("ID_STATUS", id_status);
								r.add("ID_KEMASKINI", user_id);
								r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
								sql6 = r.getSQLUpdate("tblppkpermohonan");
								stmt.executeUpdate(sql6);
								*/
								myLogger.info("SSF :::::::::::::::: SPECIAL");
								update_audit = "yes";
							}
							else
							{
								SST_update_kosong(session,id_fail,id_permohonan,id_status,
										id_suburusanstatus,user_id);
								/*
							r.clear();
							r.update("id_fail", id_fail);
							r.update("id_suburusanstatus", id_suburusanstatus);				
							//r.add("AKTIF", 1);
							r.add("ID_KEMASKINI", user_id);
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							sql7 = r.getSQLUpdate("tblrujsuburusanstatusfail");
							myLogger.info("SQL UPDATE WUJUD tblrujsuburusanstatusfail :"+current_status);
							stmt.executeUpdate(sql7);
							*/
							myLogger.info("SSF :::::::::::::::: STEP 3");
							}
						}
					}
					else
					{
						SST_insert_aktif_permohonan(session,id_fail,id_permohonan,id_status,id_suburusanstatus,user_id);
						
						
						
						/*
						myLogger.info("ADD BARU KAT SINI");
						r.clear();
						r.update("ID_FAIL", id_fail);
						r.update("AKTIF", 1);
						r.add("AKTIF", 0);
						r.add("ID_KEMASKINI", user_id);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
						sql8 = r.getSQLUpdate("tblrujsuburusanstatusfail");
						myLogger.info("SSF 1 :"+sql8);
						stmt.executeUpdate(sql8);			
						r.clear();
						r.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
						r.add("ID_PERMOHONAN", id_permohonan);
						r.add("ID_SUBURUSANSTATUS", id_suburusanstatus);
						r.add("AKTIF", 1);
						r.add("ID_MASUK", user_id);
						r.add("TARIKH_MASUK", r.unquote("sysdate"));
						r.add("ID_KEMASKINI", user_id);
						r.add("id_Fail", id_fail);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
						sql9 = r.getSQLInsert("tblrujsuburusanstatusfail");
						myLogger.info("SSF 2 :"+sql9);
						stmt.executeUpdate(sql9);			
						r.clear();
						r.update("ID_PERMOHONAN", id_permohonan);
						r.add("ID_STATUS", id_status);
						r.add("ID_KEMASKINI", user_id);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
						sql10 = r.getSQLUpdate("tblppkpermohonan");
						myLogger.info("SSF 3 :"+sql10);
						stmt.executeUpdate(sql10);	
						*/
						myLogger.info("SSF :::::::::::::::: STEP 4");
						update_audit = "yes";
						
						
					}
					
					if(update_audit.equals("yes"))
					{
					AuditTrail at = new AuditTrail();
					at.logActivity(id_status,"2",null,session,"INS","FAIL ["+getNoFail(id_fail)+"] DI DALAM PROSES "+getNamaStatus(id_status));
						
						/*
					String no_fail_temp = "";
					sql11 = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '"+id_fail+"'";			
					ResultSet rs = stmt.executeQuery(sql11);					
					while (rs.next()){				
						no_fail_temp = rs.getString("NO_FAIL");				
				    }
					
					String nama_status_temp = "";
					sql12 = "SELECT KETERANGAN FROM TBLRUJSTATUS WHERE ID_STATUS = '"+id_status+"'";			
					ResultSet rs1 = stmt.executeQuery(sql12);					
					while (rs1.next()){				
						nama_status_temp = rs1.getString("KETERANGAN").toUpperCase();				
				    }
					
					AuditTrail at = new AuditTrail();
	    			at.logActivity(id_status,"2",null,session,"INS","FAIL ["+no_fail_temp+"] DIDALAM PROSES "+nama_status_temp);
	    			*/
					}
					
			/*
			SELECT SSS.KETERANGAN AS MAIN_STATUS,S.KETERANGAN,SSF.ID_SUBURUSANSTATUSFAIL,SSF.ID_PERMOHONAN,SSF.ID_SUBURUSANSTATUS,SSF.AKTIF,SSF.ID_FAIL,SSF.TARIKH_KEMASKINI 
            FROM TBLRUJSUBURUSANSTATUSFAIL SSF,TBLPFDFAIL  F,TBLRUJSUBURUSANSTATUS SS,TBLRUJSTATUS S,TBLRUJSTATUS SSS,TBLPPKPERMOHONAN P 
            WHERE F.NO_FAIL = 'JKPTG/PK/03/02/0023/2012/S17-1' AND SSF.ID_FAIL = F.ID_FAIL  AND P.ID_FAIL = F.ID_FAIL AND P.ID_STATUS = SSS.ID_STATUS 
            AND SSF.ID_SUBURUSANSTATUS = SS.ID_SUBURUSANSTATUS AND SS.ID_STATUS = S.ID_STATUS ORDER BY SSF.ID_SUBURUSANSTATUSFAIL
            
			SELECT S.KETERANGAN FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S
			WHERE P.ID_FAIL = F.ID_FAIL AND S.ID_STATUS = P.ID_STATUS AND F.NO_FAIL = 'JKPTG/PK/03/02/0011/2012'
			*/
			
			}
		} catch (SQLException se) {			
			throw new Exception("Ralat Jana Fail:" + se.getMessage());
		} finally {
			//if (conn != null)
			//	conn.close();
			//if (db != null)
			//	db.close();
		}
	}
	
	//:::::::::::::::::
	
	
	public void kemaskiniSubUrusanStatusFail_basic(HttpSession session,String id_permohonan, String user_id,String id_status, String id_suburusanstatus,
			String id_fail)
	throws Exception {
		Connection conn = null;
		Db db = null;
		Vector list_substatus = null;
		Hashtable hash_status = null;
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		String sql6 = "";
		String sql7 = "";
		String sql8 = "";
		String sql9 = "";
		String sql10 = "";
		String sql11 = "";
		String sql12 = "";
		String check_sub = "";
		String current_status = "";
		String jenis_patah_balik = "";
		String status_patah_balik = "no";
		String update_audit = "";
		/*
		Iterator iterator;
		Iterator iterator_kp;
		Iterator iterator_np;
		Iterator iterator_ryn;
		*/
		try {
			myLogger.info("SSF id_permohonan ::"+id_permohonan);
			myLogger.info("SSF user_id ::"+user_id);
			myLogger.info("SSF id_status ::"+id_status);
			myLogger.info("SSF id_suburusanstatus ::"+id_suburusanstatus);
			myLogger.info("SSF id_fail ::"+id_fail);			
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			conn = db.getConnection();
			conn.setAutoCommit(false);			
			current_status = current_status(id_fail).get("ID_STATUS")+"";
			if(current_status!=null)
			{
			myLogger.info("SSF CURRENT STATUS :"+current_status);
			}			
			Integer current_step = 0;
			Integer new_step = 0;
			
			
			if(current_status.equals("8") || current_status.equals("9") || current_status.equals("170") || current_status.equals("169"))
			{
				current_step = 1;			
			}
			else if(current_status.equals("50") || current_status.equals("53") || current_status.equals("151") 
					|| current_status.equals("152")
					|| current_status.equals("14") || current_status.equals("70"))
			{
				current_step = 2;			
			}
			else if(current_status.equals("18") || current_status.equals("44") || current_status.equals("175") 
					|| current_status.equals("173")
					|| current_status.equals("177") || current_status.equals("47") || current_status.equals("172")
					|| current_status.equals("176") || current_status.equals("174") || current_status.equals("44"))
			{
				current_step = 3;
			
			}
			else if(current_status.equals("41"))
			{
				current_step = 4;			
			}
			else if(current_status.equals("25"))
			{
				current_step = 5;			
			}
			else if(current_status.equals("21"))
			{
				current_step = 6;			
			}
			else if(current_status.equals("64") || current_status.equals("163"))
			{
				current_step = 7;			
			}
			
			else if(current_status.equals("166") || current_status.equals("167") || current_status.equals("180") 
					|| current_status.equals("164") || current_status.equals("165"))
			{
				current_step = 8;			
			}
			else if(current_status.equals("61") || current_status.equals("154") || current_status.equals("155"))
			{
				current_step = 9;			
			}
			else if(current_status.equals("56"))
			{
				current_step = 10;			
			}
			else if(current_status.equals("999"))
			{
				current_step = 11;			
			}
			
			
			if(id_status.equals("8") || id_status.equals("9") || id_status.equals("170") || id_status.equals("169"))
			{
				new_step = 1;			
			}
			else if(id_status.equals("50") || id_status.equals("53") || id_status.equals("151") 
					|| id_status.equals("152")
					|| id_status.equals("14") || id_status.equals("70"))
			{
				new_step = 2;			
			}
			else if(id_status.equals("18") || id_status.equals("44") || id_status.equals("175") 
					|| id_status.equals("173")
					|| id_status.equals("177") || id_status.equals("47") || id_status.equals("172")
					|| id_status.equals("176") || id_status.equals("174") || id_status.equals("44"))
			{
				new_step = 3;
			
			}
			else if(id_status.equals("41"))
			{
				new_step = 4;			
			}
			else if(id_status.equals("25"))
			{
				new_step = 5;			
			}
			else if(id_status.equals("21"))
			{
				new_step = 6;			
			}
			else if(id_status.equals("64") || id_status.equals("163"))
			{
				new_step = 7;			
			}
			
			else if(id_status.equals("166") || id_status.equals("167") || id_status.equals("180") 
					|| id_status.equals("164") || id_status.equals("165"))
			{
				new_step = 8;			
			}
			else if(id_status.equals("61") || id_status.equals("154") || id_status.equals("155"))
			{
				new_step = 9;			
			}
			else if(id_status.equals("56"))
			{
				new_step = 10;			
			}
			else if(id_status.equals("999"))
			{
				new_step = 11;			
			}
			
			
			String update_status = "no";
			if(new_step>current_step || new_step==current_step)
			{
				update_status = "yes";	
			}
			else
			{
				if(current_status.equals("166") && id_status.equals("18"))
				{
				update_status = "special";	
				}
			}
			
			myLogger.info("JENIS update_status :"+update_status);
			
			if(update_status.equals("yes") || update_status.equals("special"))
			{			
			//id_status yang boleh berulang				
				
				if(id_status.equals("151") || id_status.equals("152") || id_status.equals("53")
				|| id_status.equals("50") || id_status.equals("70") || id_status.equals("56"))
				{
					jenis_patah_balik = "kp";			
				}	
					
				if(id_status.equals("177") || id_status.equals("176") || id_status.equals("175")
				|| id_status.equals("174") || id_status.equals("173") || id_status.equals("172")
				|| id_status.equals("18") || id_status.equals("44") || id_status.equals("47"))
				{
				jenis_patah_balik = "np";			
				}
				
				if(id_status.equals("166") || id_status.equals("167") || id_status.equals("180")
				|| id_status.equals("164") || id_status.equals("165"))
				{
				jenis_patah_balik = "ryn";			
				}	
				
			
			myLogger.info("SSF JENIS PATAH BALIK?:"+jenis_patah_balik);		
			
			
			String jenis_update_add = "";
			/*
			if(jenis_patah_balik.equals("kp"))
			{
				list_substatus = list_substatus(id_fail);			
				if(list_substatus.size()>0)
				{			
					for (int i = 0; i < list_substatus.size(); i++) 
					{
						hash_status = (Hashtable) list_substatus.get(i);
						
						if(location_id_status_kp.contains(hash_status.get("ID_STATUS")) && hash_status.get("AKTIF").equals("1"))
						{
							jenis_update_add = "set1"; 							
										
						}
						
					}
				}	
				
			}
			else if(jenis_patah_balik.equals("np"))
			{
				list_substatus = list_substatus(id_fail);			
				if(list_substatus.size()>0)
				{			
					for (int i = 0; i < list_substatus.size(); i++) 
					{
						hash_status = (Hashtable) list_substatus.get(i);
						
						if(location_id_status_np.contains(hash_status.get("ID_STATUS")) && hash_status.get("AKTIF").equals("1"))
						{
							jenis_update_add = "set2"; 							
										
						}
						
					}
				}	
				
			}
			else if(jenis_patah_balik.equals("ryn"))
			{
				list_substatus = list_substatus(id_fail);			
				if(list_substatus.size()>0)
				{			
					for (int i = 0; i < list_substatus.size(); i++) 
					{
						hash_status = (Hashtable) list_substatus.get(i);
						
						if(location_id_status_ryn.contains(hash_status.get("ID_STATUS")) && hash_status.get("AKTIF").equals("1"))
						{
							jenis_update_add = "set3"; 							
										
						}
						
						
					}
				}	
				
			}
			*/
			myLogger.info("SSF jenis_update_add :"+jenis_update_add);	
		
			
					list_substatus = list_substatus(id_fail);			
					if(list_substatus.size()>0)
					{			
						for (int i = 0; i < list_substatus.size(); i++) {
							hash_status = (Hashtable) list_substatus.get(i);
								if(hash_status.get("ID_SUBURUSANSTATUS").equals(id_suburusanstatus))
								{
								myLogger.info("SFF ID_SUBURUSAN INI TELAH WUJUD :"+id_suburusanstatus);	
								check_sub = "wujud";
								}				
							String display_sub = "";
							/*
							display_sub = "SSF ID_SUBURUSANSTATUSFAIL ::"+hash_status.get("ID_SUBURUSANSTATUSFAIL");
							display_sub += ",SSF ID_PERMOHONAN ::"+hash_status.get("ID_PERMOHONAN");
							display_sub += ",SSF ID_FAIL ::"+hash_status.get("ID_FAIL");
							display_sub += ",SSF ID_SUBURUSANSTATUS ::"+hash_status.get("ID_SUBURUSANSTATUS");
							display_sub += ",SSF AKTIF ::"+hash_status.get("AKTIF");
							myLogger.info(display_sub);*/
						}
					}			
					
					if(check_sub.equals("wujud"))
					{
						if(jenis_update_add.equals("set1") || jenis_update_add.equals("set3"))
						{	
							
							r.clear();
							r.update("ID_FAIL", id_fail);
							r.update("AKTIF", 1);
							r.add("AKTIF", 0);
							r.add("ID_KEMASKINI", user_id);
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							sql1 = r.getSQLUpdate("tblrujsuburusanstatusfail");
							stmt.executeUpdate(sql1);		
							
							r.clear();
							r.update("id_fail", id_fail);
							r.update("id_suburusanstatus", id_suburusanstatus);				
							r.add("AKTIF", 1);
							r.add("ID_KEMASKINI", user_id);
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							sql2 = r.getSQLUpdate("tblrujsuburusanstatusfail");
							myLogger.info("SQL UPDATE WUJUD tblrujsuburusanstatusfail :"+current_status);
							stmt.executeUpdate(sql2);
							
							r.clear();
							r.update("ID_PERMOHONAN", id_permohonan);
							r.add("ID_STATUS", id_status);
							r.add("ID_KEMASKINI", user_id);
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							sql3 = r.getSQLUpdate("tblppkpermohonan");
							stmt.executeUpdate(sql3);	
							
							update_audit = "yes";
							
							myLogger.info("SSF :::::::::::::::: STEP 1");
						}
						else if(jenis_update_add.equals("set2"))
						{	
							r.clear();
							r.update("ID_FAIL", id_fail);
							r.update("AKTIF", 1);
							r.add("AKTIF", 0);
							r.add("ID_KEMASKINI", user_id);
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							sql4 = r.getSQLUpdate("tblrujsuburusanstatusfail");
							stmt.executeUpdate(sql4);		
							
							r.clear();
							r.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
							r.add("ID_PERMOHONAN", id_permohonan);
							r.add("ID_SUBURUSANSTATUS", id_suburusanstatus);
							r.add("AKTIF", 1);
							r.add("ID_MASUK", user_id);
							r.add("TARIKH_MASUK", r.unquote("sysdate"));
							r.add("ID_KEMASKINI", user_id);
							r.add("id_Fail", id_fail);
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							sql5 = r.getSQLInsert("tblrujsuburusanstatusfail");
							stmt.executeUpdate(sql5);
							
							r.clear();
							r.update("ID_PERMOHONAN", id_permohonan);
							r.add("ID_STATUS", id_status);
							r.add("ID_KEMASKINI", user_id);
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							sql6 = r.getSQLUpdate("tblppkpermohonan");
							stmt.executeUpdate(sql6);
							
							myLogger.info("SSF :::::::::::::::: STEP 2");
							
							update_audit = "yes";
							
						}
						else
						{
							
							if(update_status.equals("special"))
							{
								r.clear();
								r.update("ID_FAIL", id_fail);
								r.update("AKTIF", 1);
								r.add("AKTIF", 0);
								r.add("ID_KEMASKINI", user_id);
								r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
								sql4 = r.getSQLUpdate("tblrujsuburusanstatusfail");
								stmt.executeUpdate(sql4);		
								
								r.clear();
								r.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
								r.add("ID_PERMOHONAN", id_permohonan);
								r.add("ID_SUBURUSANSTATUS", id_suburusanstatus);
								r.add("AKTIF", 1);
								r.add("ID_MASUK", user_id);
								r.add("TARIKH_MASUK", r.unquote("sysdate"));
								r.add("ID_KEMASKINI", user_id);
								r.add("id_Fail", id_fail);
								r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
								sql5 = r.getSQLInsert("tblrujsuburusanstatusfail");
								stmt.executeUpdate(sql5);
								
								r.clear();
								r.update("ID_PERMOHONAN", id_permohonan);
								r.add("ID_STATUS", id_status);
								r.add("ID_KEMASKINI", user_id);
								r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
								sql6 = r.getSQLUpdate("tblppkpermohonan");
								stmt.executeUpdate(sql6);
								
								myLogger.info("SSF :::::::::::::::: SPECIAL");
								update_audit = "yes";
							}
							else
							{
							r.clear();
							r.update("id_fail", id_fail);
							r.update("id_suburusanstatus", id_suburusanstatus);				
							//r.add("AKTIF", 1);
							r.add("ID_KEMASKINI", user_id);
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							sql7 = r.getSQLUpdate("tblrujsuburusanstatusfail");
							myLogger.info("SQL UPDATE WUJUD tblrujsuburusanstatusfail :"+current_status);
							stmt.executeUpdate(sql7);
							myLogger.info("SSF :::::::::::::::: STEP 3");
							}
						}
					}
					else
					{
						myLogger.info("ADD BARU KAT SINI");
						r.clear();
						r.update("ID_FAIL", id_fail);
						r.update("AKTIF", 1);
						r.add("AKTIF", 0);
						r.add("ID_KEMASKINI", user_id);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
						sql8 = r.getSQLUpdate("tblrujsuburusanstatusfail");
						myLogger.info("SSF 1 :"+sql8);
						stmt.executeUpdate(sql8);			
						r.clear();
						r.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
						r.add("ID_PERMOHONAN", id_permohonan);
						r.add("ID_SUBURUSANSTATUS", id_suburusanstatus);
						r.add("AKTIF", 1);
						r.add("ID_MASUK", user_id);
						r.add("TARIKH_MASUK", r.unquote("sysdate"));
						r.add("ID_KEMASKINI", user_id);
						r.add("id_Fail", id_fail);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
						sql9 = r.getSQLInsert("tblrujsuburusanstatusfail");
						myLogger.info("SSF 2 :"+sql9);
						stmt.executeUpdate(sql9);			
						r.clear();
						r.update("ID_PERMOHONAN", id_permohonan);
						r.add("ID_STATUS", id_status);
						r.add("ID_KEMASKINI", user_id);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
						sql10 = r.getSQLUpdate("tblppkpermohonan");
						myLogger.info("SSF 3 :"+sql10);
						stmt.executeUpdate(sql10);	
						myLogger.info("SSF :::::::::::::::: STEP 4");
						update_audit = "yes";
						
						
					}
					
					if(update_audit.equals("yes"))
					{
					String no_fail_temp = "";
					sql11 = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '"+id_fail+"'";			
					ResultSet rs = stmt.executeQuery(sql11);					
					while (rs.next()){				
						no_fail_temp = rs.getString("NO_FAIL");				
				    }
					
					String nama_status_temp = "";
					sql12 = "SELECT KETERANGAN FROM TBLRUJSTATUS WHERE ID_STATUS = '"+id_status+"'";			
					ResultSet rs1 = stmt.executeQuery(sql12);					
					while (rs1.next()){				
						nama_status_temp = rs1.getString("KETERANGAN").toUpperCase();				
				    }
					
					AuditTrail at = new AuditTrail();
	    			at.logActivity(id_status,"2",null,session,"INS","FAIL ["+no_fail_temp+"] DIDALAM PROSES "+nama_status_temp);
					}
		
			conn.commit();
			}
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
	}
	
	
	public Hashtable current_status(String id_fail) throws Exception {
		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();			
			
			sql = " SELECT ID_STATUS FROM TBLPPKPERMOHONAN WHERE ID_FAIL = '"+id_fail+"' ";			
			myLogger.info(" SQL GET ID STATUS :"+sql);			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("ID_STATUS") == null) {
					h.put("ID_STATUS", "");
				} else {
					h.put("ID_STATUS", rs.getString("ID_STATUS"));
				}				
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}

	
	Vector list_substatus = null;
	public Vector list_substatus(String id_fail) throws Exception {
		list_substatus = new Vector();
		list_substatus.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
			sql = " SELECT S.ID_STATUS,S.KETERANGAN AS NAMA_STATUS,SSF.ID_SUBURUSANSTATUSFAIL,SSF.ID_PERMOHONAN,SSF.ID_SUBURUSANSTATUS,SSF.AKTIF,SSF.ID_FAIL,SSF.TARIKH_KEMASKINI "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL SSF,TBLPFDFAIL  F,TBLRUJSUBURUSANSTATUS SS,TBLRUJSTATUS S "+
			" WHERE F.ID_FAIL = '"+id_fail+"' AND SSF.ID_FAIL = F.ID_FAIL "+
			" AND SSF.ID_SUBURUSANSTATUS = SS.ID_SUBURUSANSTATUS AND SS.ID_STATUS = S.ID_STATUS ORDER BY SSF.ID_SUBURUSANSTATUSFAIL ";;
			
			myLogger.info("SSF SELECT ALL SUBURUSAN :"+sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				
				if (rs.getString("ID_STATUS") == null) {
					h.put("ID_STATUS", "");
				} else {
					h.put("ID_STATUS", rs.getString("ID_STATUS"));
				}
					
				if (rs.getString("ID_SUBURUSANSTATUSFAIL") == null) {
					h.put("ID_SUBURUSANSTATUSFAIL", "");
				} else {
					h.put("ID_SUBURUSANSTATUSFAIL", rs.getString("ID_SUBURUSANSTATUSFAIL"));
				}
				
				if (rs.getString("ID_PERMOHONAN") == null) {
					h.put("ID_PERMOHONAN", "");
				} else {
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN"));
				}
				
				if (rs.getString("ID_SUBURUSANSTATUS") == null) {
					h.put("ID_SUBURUSANSTATUS", "");
				} else {
					h.put("ID_SUBURUSANSTATUS", rs.getString("ID_SUBURUSANSTATUS"));
				}
				
				if (rs.getString("AKTIF") == null) {
					h.put("AKTIF", "");
				} else {
					h.put("AKTIF", rs.getString("AKTIF"));
				}
				
				if (rs.getString("ID_FAIL") == null) {
					h.put("ID_FAIL", "");
				} else {
					h.put("ID_FAIL", rs.getString("ID_FAIL"));
				}
				
				
				list_substatus.addElement(h);
			}
			return list_substatus;
		} finally {
			if (db != null)
				db.close();
		}
	}
	

	public Vector getListnegeriByDaerah(int id) throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("n.id_Negeri");
			r.add("n.nama_Negeri");
			r.add("n.kod_Negeri");
			r.add("n.id_Negeri", r.unquote("d.id_Negeri"));

			r.add("d.id_Daerah", id);

			sql = r.getSQLSelect("Tblrujnegeri n, Tblrujdaerah d");
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_Negeri", rs.getInt("id_Negeri"));

				if (rs.getString("nama_Negeri") == null) {
					h.put("nama_Negeri", "");
				} else {
					h.put("nama_Negeri", rs.getString("nama_Negeri"));
				}
				if (rs.getString("kod_Negeri") == null) {
					h.put("kod_Negeri", "");
				} else {
					h.put("kod_Negeri", rs.getString("kod_Negeri"));
				}
				/*
				 * if(rs.getString("description") == null){ h.put("description",
				 * ""); }else{ h.put("description",
				 * rs.getString("description")); }
				 */
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getNofaildunia(int seksyen, int flag_jenis_fail, int sekkk)
			throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "select 'JKPTG' || '/' || 'PK' || '/'  || to_char(TARIKH_DAFTAR_FAIL, 'YYYY') || '/' ||"
					+ " to_char(count(*),'099999') as NO_FAIL_DUNIA, count(*) as BILANGAN_SEMASA"
					+ " from tblpfdfail F, tblppkpermohonan P"
					+ " where F.id_seksyen = '"
					+ seksyen
					+ "'"
					+ " and to_char(F.TARIKH_DAFTAR_FAIL, 'YYYY')=to_char(sysdate, 'YYYY')"
					+ " and F.no_fail is not null"
					+ " and F.flag_jenis_fail = '"
					+ flag_jenis_fail
					+ "'"
					+ " and P.id_fail = F.id_fail"
					+ " and P.flag_jenis_permohonan = '1'"
					+ " and P.seksyen = '"
					+ sekkk
					+ "'"
					+ " group by F.id_seksyen,to_char(F.TARIKH_DAFTAR_FAIL, 'YYYY')";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Vector v = new Vector();
			while (rs.next()) {
				h = new Hashtable();
				h.put("no_fail_dunia",
						rs.getString("NO_FAIL_DUNIA") == null ? "" : rs
								.getString("NO_FAIL_DUNIA"));
				h.put("no_bil_fail",
						rs.getString("BILANGAN_SEMASA") == null ? "" : rs
								.getString("BILANGAN_SEMASA"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	
	public Vector getNofailduniaDb(int seksyen, int flag_jenis_fail, int sekkk,Db db)
			throws Exception {
		//Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		System.out.print("getNofailduniaDb");
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "select 'JKPTG' || '/' || 'PK' || '/'  || to_char(TARIKH_DAFTAR_FAIL, 'YYYY') || '/' ||"
					+ " to_char(count(*),'099999') as NO_FAIL_DUNIA, count(*) as BILANGAN_SEMASA"
					+ " from tblpfdfail F, tblppkpermohonan P"
					+ " where F.id_seksyen = '"
					+ seksyen
					+ "'"
					+ " and to_char(F.TARIKH_DAFTAR_FAIL, 'YYYY')=to_char(sysdate, 'YYYY')"
					+ " and F.no_fail is not null"
					+ " and F.flag_jenis_fail = '"
					+ flag_jenis_fail
					+ "'"
					+ " and P.id_fail = F.id_fail"
					+ " and P.flag_jenis_permohonan = '1'"
					+ " and P.seksyen = '"
					+ sekkk
					+ "'"
					+ " group by F.id_seksyen,to_char(F.TARIKH_DAFTAR_FAIL, 'YYYY')";
			System.out.print("SQL 222" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Vector v = new Vector();
			while (rs.next()) {
				h = new Hashtable();
				h.put("no_fail_dunia",
						rs.getString("NO_FAIL_DUNIA") == null ? "" : rs
								.getString("NO_FAIL_DUNIA"));
				h.put("no_bil_fail",
						rs.getString("BILANGAN_SEMASA") == null ? "" : rs
								.getString("BILANGAN_SEMASA"));
				v.addElement(h);
			}
			return v;
		} finally {
			/*if (db != null)
				db.close();*/
		}
	}

	
	
	public Vector getListBandar() throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			/*
			 * r.add("id_Daerah"); r.add("nama_Daerah"); r.add("kod_Daerah");
			 * //r.add("id_Negeri"); sql =
			 * r.getSQLSelect("Tblrujdaerah","kod_Daerah");
			 */

			sql = "SELECT ID_BANDAR, KOD_BANDAR, KETERANGAN, ID_NEGERI FROM TBLRUJBANDAR";

			// 
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("ID_BANDAR"));

				if (rs.getString("KETERANGAN") == null) {
					h.put("nama", "");
				} else {
					h.put("nama", rs.getString("KETERANGAN"));
				}
				if (rs.getString("KOD_BANDAR") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("KOD_BANDAR"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getListBandarByNegeri(int idnegeri) throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT ID_BANDAR, KOD_BANDAR, KETERANGAN, ID_NEGERI FROM TBLRUJBANDAR WHERE ID_NEGERI = '"
					+ idnegeri + "' ORDER BY KOD_BANDAR ASC";
			
			myLogger.info(" getListBandarByNegeri :"+sql);

			
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("ID_BANDAR"));

				if (rs.getString("KETERANGAN") == null) {
					h.put("nama", "");
				} else {
					h.put("nama", rs.getString("KETERANGAN"));
				}
				if (rs.getString("KOD_BANDAR") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("KOD_BANDAR"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public Vector getListOBUpdate(String id_permohonansimati) throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			/*sql = " SELECT DISTINCT OB.ID_OB,OB.NAMA_OB,OB.ID_SIMATI,OB.ID_PERMOHONANSIMATI,OB.ID_PEMOHON,OB.LAPIS FROM TBLPPKOB OB,TBLPPKPERMOHONANSIMATI SM "+
				  " WHERE STATUS_HIDUP = '1' AND SM.ID_SIMATI = OB.ID_SIMATI AND SM.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' "+
				  " ORDER BY NAMA_OB ASC";*/
			
			sql = " SELECT DISTINCT OB.ID_OB,OB.NAMA_OB,OB.ID_SIMATI,OB.ID_PERMOHONANSIMATI,OB.ID_PEMOHON,OB.LAPIS "+
			" FROM TBLPPKOBPERMOHONAN OB WHERE OB.STATUS_HIDUP = '1'  AND ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' "+
			" ORDER BY NAMA_OB ASC ";
			 myLogger.info("GET LIST WARIS BY SIMATI :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID_OB", rs.getString("ID_OB"));

				if (rs.getString("NAMA_OB") == null) {
					h.put("NAMA_OB", "");
				} else {
					h.put("NAMA_OB", rs.getString("NAMA_OB"));
				}
				if (rs.getString("ID_SIMATI") == null) {
					h.put("ID_SIMATI", "");
				} else {
					h.put("ID_SIMATI", rs.getString("ID_SIMATI"));
				}
				if (rs.getString("ID_PERMOHONANSIMATI") == null) {
					h.put("ID_PERMOHONANSIMATI", "");
				} else {
					h.put("ID_PERMOHONANSIMATI", rs.getString("ID_PERMOHONANSIMATI"));
				}
				if (rs.getString("ID_PEMOHON") == null) {
					h.put("ID_PEMOHON", "");
				} else {
					h.put("ID_PEMOHON", rs.getString("ID_PEMOHON"));
				}
				if (rs.getString("LAPIS") == null) {
					h.put("LAPIS", "");
				} else {
					h.put("LAPIS", rs.getString("LAPIS"));
				}

				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
		
	
	
	public void SST_update_aktif_permohonan(HttpSession session,String id_fail,String id_permohonan,String id_status,
			String id_suburusanstatus,String user_id) throws Exception {
		// Azam add Transaction on 02.02.2010
		Connection conn = null;
		Db db = null;
		
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);		
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.clear();
			r.update("ID_FAIL", id_fail);
			r.update("AKTIF", 1);
			r.add("AKTIF", 0);
			r.add("ID_KEMASKINI", user_id);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblrujsuburusanstatusfail");
			stmt.executeUpdate(sql);		
			
			r.clear();
			r.update("id_fail", id_fail);
			r.update("id_suburusanstatus", id_suburusanstatus);				
			r.add("AKTIF", 1);
			r.add("ID_KEMASKINI", user_id);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql1 = r.getSQLUpdate("tblrujsuburusanstatusfail");			
			stmt.executeUpdate(sql1);
			
			r.clear();
			r.update("ID_PERMOHONAN", id_permohonan);
			r.add("ID_STATUS", id_status);
			r.add("ID_KEMASKINI", user_id);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql2 = r.getSQLUpdate("tblppkpermohonan");
			stmt.executeUpdate(sql2);
			
			conn.commit();
	
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
	}
	
	
	public void SST_insert_aktif_permohonan(HttpSession session,String id_fail,String id_permohonan,String id_status,
			String id_suburusanstatus,String user_id) throws Exception {		
		//Connection conn = null;
		Db db = null;
		
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

			db = new Db();
			//conn = db.getConnection();
			//conn.setAutoCommit(false);		
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.clear();
			r.update("ID_FAIL", id_fail);
			r.update("AKTIF", 1);
			r.add("AKTIF", 0);
			r.add("ID_KEMASKINI", user_id);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblrujsuburusanstatusfail");
			myLogger.info("CHECK ADD NEW 1:"+sql);
			stmt.executeUpdate(sql);		
			
			r.clear();
			r.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
			r.add("ID_PERMOHONAN", id_permohonan);
			r.add("ID_SUBURUSANSTATUS", id_suburusanstatus);
			r.add("AKTIF", 1);
			r.add("ID_MASUK", user_id);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", user_id);
			r.add("id_Fail", id_fail);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
		    sql1 = r.getSQLInsert("tblrujsuburusanstatusfail");
			/*sql1 = " INSERT INTO tblrujsuburusanstatusfail (ID_SUBURUSANSTATUSFAIL, ID_PERMOHONAN, ID_SUBURUSANSTATUS, AKTIF, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, id_Fail, TARIKH_KEMASKINI)  " +
			" VALUES ('"+DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ")+"', '"+id_permohonan+"', '"+id_suburusanstatus+"', 1, '"+user_id+"',  sysdate , '"+user_id+"', '"+id_fail+"',  sysdate ) ";
			*/
			myLogger.info("CHECK ADD NEW 2:"+sql1);
			stmt.executeUpdate(sql1);
			
			r.clear();
			r.update("ID_PERMOHONAN", id_permohonan);
			r.add("ID_STATUS", id_status);
			r.add("ID_KEMASKINI", user_id);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));			
			sql2 = r.getSQLUpdate("tblppkpermohonan");
			myLogger.info("CHECK ADD NEW 3:"+sql2);
			stmt.executeUpdate(sql2);
			
			//conn.commit();
	
		} catch (SQLException se) {
			
			/*try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail:" + se.getMessage());*/
		} finally {
			/*
			if (conn != null)
				conn.close();*/
			if (db != null)
				db.close();
		}
	}
	
	
	public void SST_update_kosong(HttpSession session,String id_fail,String id_permohonan,String id_status,
			String id_suburusanstatus,String user_id) throws Exception {		
		Connection conn = null;
		Db db = null;
		
		String sql = "";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);		
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.clear();
			r.update("id_fail", id_fail);
			r.update("id_suburusanstatus", id_suburusanstatus);				
			//r.add("AKTIF", 1);
			r.add("ID_KEMASKINI", user_id);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblrujsuburusanstatusfail");			
			stmt.executeUpdate(sql);
			
			conn.commit();
	
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
	}
	
	
	
	
	
    public String getNoFail(String id_fail) throws Exception {
		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String no_fail_temp = "";
			sql = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '"+id_fail+"'";			
			ResultSet rs = stmt.executeQuery(sql);					
			while (rs.next()){				
				no_fail_temp = rs.getString("NO_FAIL");				
		    }
			return no_fail_temp;
		} finally {
			if (db != null)
				db.close();
		}
	}
    
 public String getNamaStatus(String id_status) throws Exception {
		
		Db db = null;
		String sql = "";
		String nama_status_temp = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT KETERANGAN FROM TBLRUJSTATUS WHERE ID_STATUS = '"+id_status+"'";			
			ResultSet rs1 = stmt.executeQuery(sql);					
			while (rs1.next()){				
				nama_status_temp = rs1.getString("KETERANGAN").toUpperCase();				
		    }
			return nama_status_temp;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	

	

}
