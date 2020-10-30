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
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.File;
import ekptg.view.admin.Push;


public class FrmPrmhnnSek8DaftarSek8OnlineData {
	static Logger myLogger = Logger.getLogger(FrmPrmhnnSek8DaftarSek8OnlineData.class);
	private  Vector list = new Vector();
	private  Vector listConvert = new Vector();
	private Vector listDataHadulu = new Vector();
	private  SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	 public  Vector getData(){
		return list;	 
	  }
	 
	 public  Vector getDataConvert(){
			return listConvert;	 
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

				sql = "SELECT DISTINCT F.ID_FAIL, F.NO_FAIL, D.ID_DAERAH, P.ID_PERMOHONAN, P.TARIKH_MOHON, "
						+ " S.NO_KP_BARU, S.NO_KP_LAMA, S.JENIS_KP, S.NO_KP_LAIN, S.ID_SIMATI, "
						+ " S.NAMA_SIMATI, S.TARIKH_MATI, PM.ID_PEMOHON, PM.NAMA_PEMOHON, PM.NO_KP_BARU, "
						+ " PM.NO_KP_LAMA, PM.JENIS_KP, PM.NO_KP_LAIN, PM.ALAMAT_1, PM.ALAMAT_2, PM.ALAMAT_3, "
						+ " PM.POSKOD, PM.BANDAR, N.ID_NEGERI, N.NAMA_NEGERI, D.NAMA_DAERAH, P.SEKSYEN, "
						+ " ST.KETERANGAN, P.ID_STATUS, U.NAMA_PEJABAT, PM.ID_NEGERI,  MOSI.ID_PERMOHONANSIMATI, "
						+ " S.UMUR, S.JANTINA,  PM.UMUR, PM.JANTINA,U.ID_PEJABATJKPTG, P.NO_SUBJAKET,  "
						+ " PM.ALAMAT1_SURAT, PM.ALAMAT2_SURAT, PM.ALAMAT3_SURAT,PM.POSKOD_SURAT, PM.BANDAR_SURAT, PM.ID_NEGERISURAT, PM.ID_BANDAR, U.ALAMAT1, DX.NAMA_DAERAH AS D_P,PM.ID_BANDARSURAT,"
						+ " P.BATAL_KUASA_PENTADBIR, P.LANTIK_PENTADBIR, P.BATAL_P_AMANAH, " +
								"P.LANTIK_P_AMANAH, P.HARTA_TINGGAL, P.LAIN_TUJUAN, " +
								"PM.ID_TARAFKPTG,PM.NO_TEL,PM.NO_HP,PM.STATUS_PEMOHON," +
								"PM.ID_ARB,S.TARIKH_MATI,U.ID_NEGERI AS ID_NEGERIPEJABAT,PM.ID_SAUDARA,PM.nama_pelbagainegara   "
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
					//	+ " AND PM.ID_PERMOHONAN = P.ID_PERMOHONAN(+) "
						+ " AND P.ID_PEMOHON = PM.ID_PEMOHON(+) "
						+ " AND S.ID_SIMATI = MOSI.ID_SIMATI  "
						+ " AND P.ID_PERMOHONAN = MOSI.ID_PERMOHONAN  "
						+ " AND P.ID_STATUS = ST.ID_STATUS(+)  "
						+ " AND D.ID_DAERAH = P.ID_DAERAHMHN  "
						+ " AND U.ID_DAERAH = DX.ID_DAERAH(+) "
						+ " AND P.ID_PERMOHONAN = '" + id + "' ";

				////System.out.println("SQL FAR" + sql);

				myLogger.info("SQL SET DATA:"+sql.toUpperCase());

				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				
				

				while (rs.next()) {
					h = new Hashtable();
					h.put("nama_pelbagainegara", rs
							.getString("nama_pelbagainegara") == null ? "" : rs
							.getString("nama_pelbagainegara"));
					h.put("id_Permohonansimati", rs
							.getString("id_Permohonansimati") == null ? "" : rs
							.getString("id_Permohonansimati"));
					// h.put("id_Suburusanstatus",
					// rs.getString("id_Suburusanstatus")==null?"":rs.getString("id_Suburusanstatus"));
					// h.put("id_Suburusanstatusfail",
					// rs.getString("id_Suburusanstatusfail")==null?"":rs.getString("id_Suburusanstatusfail"));
					h.put("idFail", rs.getString("id_Fail") == null ? "" : rs
							.getString("id_Fail"));

					h.put("noFail", rs.getString("no_Fail") == null ? "" : rs
							.getString("no_Fail"));
//					//h.put("noFailBaru", "");

					h.put("idDaerah", rs.getString("id_Daerah") == null ? "" : rs
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
					h.put("noKpLama", rs.getString("no_Kp_Lama") == null ? "" : rs
							.getString("no_Kp_Lama"));
					h
							.put("jenisKp", rs.getString(8) == null ? "" : rs
									.getString(8));
					h.put("noKpLain", rs.getString("no_Kp_Lain") == null ? "" : rs
							.getString("no_Kp_Lain"));
					h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
							.getString("id_Simati"));
					h.put("namaSimati", rs.getString("nama_Simati") == null ? ""
							: rs.getString("nama_Simati"));
					h.put("tarikhMati", rs.getDate("tarikh_Mati") == null ? ""
							: sdf.format(rs.getDate("tarikh_Mati")));
					h.put("idPemohon", rs.getString("id_Pemohon") == null ? "" : rs
							.getString("id_Pemohon"));
					h.put("namaPemohon", rs.getString("nama_Pemohon") == null ? ""
							: rs.getString("nama_Pemohon"));
					h.put("noKpBaruPemohon1", rs.getString(15) == null ? "" : rs
							.getString(15).substring(0, 6));
					h.put("noKpBaruPemohon2", rs.getString(15) == null ? "" : rs
							.getString(15).substring(6, 8));
					h.put("noKpBaruPemohon3", rs.getString(15) == null ? "" : rs
							.getString(15).substring(8, 12));
					h.put("noKpLamaPemohon", rs.getString(16) == null ? "" : rs
							.getString(16));
					h.put("jenisKpPemohon", rs.getString(17) == null ? "" : rs
							.getString(17));
					h.put("noKpLainPemohon", rs.getString(18) == null ? "" : rs
							.getString(18));

					h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
							.getString("alamat_1"));
					h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
							.getString("alamat_2"));
					h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
							.getString("alamat_3"));
					h.put("poskod", rs.getString("poskod") == null ? "" : rs
							.getString("poskod"));
					h.put("bandar", rs.getString("bandar") == null ? "" : rs
							.getString("bandar"));
					h.put("idbandar", rs.getString("id_Bandar") == null ? "" : rs
							.getString("id_Bandar"));
					h.put("idbandarsurat", rs.getString("id_Bandarsurat") == null ? "" : rs
							.getString("id_Bandarsurat"));
					h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
							.getString("id_Negeri"));

					h.put("namanegeri", rs.getString("nama_Negeri") == null ? ""
							: rs.getString("nama_Negeri"));
					h.put("namadaerah", rs.getString("nama_Daerah") == null ? ""
							: rs.getString("nama_Daerah"));
					h.put("seksyen", rs.getString("seksyen") == null ? "" : rs
							.getString("seksyen"));
					h.put("keterangan", rs.getString("keterangan") == null ? ""
							: rs.getString("keterangan"));
					h.put("id_Status", rs.getString("id_Status") == null ? "" : rs
							.getString("id_Status"));
					
					if(rs.getString("ID_NEGERIPEJABAT").equals("7")){
						h.put("namaPejabat", rs.getString("nama_pejabat") == null ? "" : rs
								.getString("nama_pejabat"));
					}
					else{
					h.put("namaPejabat", rs.getString("nama_pejabat") + ","
							+ rs.getString("D_P") == null ? "" : rs
							.getString("nama_pejabat")
							+ "," + rs.getString("D_P"));}

					h.put("pmidnegeri", rs.getString(31) == null ? "" : rs
							.getString(31));

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

					h.put("umursimati", rs.getString(33) == null ? "" : rs
							.getString(33));
					h.put("jantinasimati", rs.getString(34) == null ? "" : rs
							.getString(34));

					h.put("umurpemohon", rs.getString(35) == null ? "" : rs
							.getString(35));
					h.put("jantinapemohon", rs.getString(36) == null ? "" : rs
							.getString(36));

					h.put("no_subjaket", rs.getString("no_subjaket") == null ? ""
							: rs.getString("no_subjaket"));

					h.put("tarikhMohon", rs.getString("tarikh_Mohon") == null ? ""
							: sdf.format(rs.getDate("tarikh_Mohon")));

					h.put("bkp", rs.getString("BATAL_KUASA_PENTADBIR") == null ? ""
							: rs.getString("BATAL_KUASA_PENTADBIR"));
					h.put("lp", rs.getString("LANTIK_PENTADBIR") == null ? ""
							: rs.getString("LANTIK_PENTADBIR"));
					h.put("bpa", rs.getString("BATAL_P_AMANAH") == null ? ""
							: rs.getString("BATAL_P_AMANAH"));
					h.put("lpa", rs.getString("LANTIK_P_AMANAH") == null ? ""
							: rs.getString("LANTIK_P_AMANAH"));
					h.put("ht", rs.getString("HARTA_TINGGAL") == null ? ""
							: rs.getString("HARTA_TINGGAL"));
					h.put("lt", rs.getString("LAIN_TUJUAN") == null ? ""
							: rs.getString("LAIN_TUJUAN"));

					h.put("lt", rs.getString("LAIN_TUJUAN") == null ? ""
							: rs.getString("LAIN_TUJUAN"));
					h.put("taraf_penting", rs.getString("ID_TARAFKPTG") == null ? ""
							: rs.getString("ID_TARAFKPTG"));


					h.put("no_tel", rs.getString("NO_TEL") == null ? ""
							: rs.getString("NO_TEL"));

					h.put("no_hp", rs.getString("NO_HP") == null ? ""
							: rs.getString("NO_HP"));

					h.put("jenis_pej", rs.getString("ID_ARB") == null ? ""
							: rs.getString("ID_ARB"));
					
					h.put("socSaudaraWaris", rs.getString("ID_SAUDARA") == null ? ""
							: rs.getString("ID_SAUDARA"));



					h.put("jenis_pemohon", rs.getString("STATUS_PEMOHON") == null ? ""
							: rs.getString("STATUS_PEMOHON"));

					h.put("t_mati", rs.getString("TARIKH_MATI") == null ? ""
							: rs.getString("TARIKH_MATI"));


					if (rs.getString("TARIKH_MATI") != ""
						&& rs.getString("TARIKH_MATI") != null) {
					DateFormat dm = new SimpleDateFormat("dd/MM/yyyy");
					Date tm_s = dm.parse(sdf.format(rs.getDate("TARIKH_MATI")));
					Date tm_t = dm.parse("01/11/1991");

					if (tm_s.before(tm_t)) {
						h.put("jpphlepas", "no");
					}
					else if (tm_s.after(tm_t)) {
						h.put("jpphlepas", "yes");
					} else {
						h.put("jpphlepas", "no");
					}
				    }
					else
					{
						h.put("jpphlepas", "no");
					}




	//" P.BATAL_KUASA_PENTADBIR, P.LANTIK_PENTADBIR, P.BATAL_P_AMANAH, P.LANTIK_P_AMANAH, P.HARTA_TINGGAL, P.LAIN_TUJUAN  "

					// //System.out.println("Today = " +
					// h.get("tarikhMohon").toString());

					if (rs.getString("tarikh_Mohon") != ""
							&& rs.getString("tarikh_Mohon") != null) {
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						Date t_m = df.parse(sdf.format(rs.getDate("tarikh_Mohon")));
						Date sep_m = df.parse("01/09/2009");
						//System.out.println("Today = "
							//	+ h.get("tarikhMohon").toString());

						if (t_m.before(sep_m)) {
							h.put("lepassatusept", "yes");
						} else if (t_m.after(sep_m)) {
							h.put("lepassatusept", "no");
						} else {
							h.put("lepassatusept", "no");
						}
					}

					//System.out.println("sql data-->" + h);
					list.addElement(h);
				}
				return list;
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
			
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
		
				
	              	sql = "select id_ob,id_pemohon from tblppkob where id_pemohon is not null and  id_pemohon = '"+idpemohon+"' ";
				
				//System.out.println("SQL PRINT PEMOHON WARIS:"+sql);
		
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;
			
			while(rs.next()) {		
				
				
				h = new Hashtable();
		
				h.put("id_ob", rs.getString("id_ob")==null?"":rs.getString("id_ob"));
			    listcheckpemohonwaris.addElement(h);
				
			}
		return listcheckpemohonwaris;
			}
			finally {
				if(db != null) db.close();
			}
			
		}
	 
	 public  void setDataConvert(String id) throws Exception{
		 Db db = null;
			listConvert.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
			
			//	sql = r.getSQLSelect("Tblpfdfail f, Tblppkpermohonan p, Tblrujnegeri n, Tblrujdaerah d, Tblppksimati s, Tblppkpemohon pm, Tblrujstatus st, tblrujpejabatjkptg u, Tblrujsuburusanstatusfail sub, Tblppkpermohonansimati mosi");
			
				sql = " SELECT distinct f.id_Fail, f.no_Fail, d.id_Daerah, p.id_Permohonan, p.tarikh_Mohon, s.no_Kp_Baru,"
				+ " s.no_Kp_Lama, s.jenis_Kp, s.no_Kp_Lain, s.id_Simati, s.nama_Simati, s.tarikh_Mati, pm.id_Pemohon," 
				+ " pm.nama_Pemohon, pm.no_kp_baru, pm.no_kP_lama, pm.jenis_Kp, pm.no_Kp_lain, pm.alamat_1, pm.alamat_2, pm.alamat_3," 
				+ " pm.poskod, pm.bandar, n.id_Negeri, n.nama_Negeri, d.nama_Daerah, p.seksyen, st.keterangan, p.id_Status, u.nama_pejabat," 
				+ " pm.id_negeri, mosi.id_Permohonansimati, " +
						//"sub.id_Suburusanstatus, sub.id_Suburusanstatusfail, sub.aktif, " +
						"mosi.id_Permohonansimati," 
				+ " s.umur, s.jantina, pm.umur, pm.jantina, p.id_Daerahmhn" 
				+ " FROM Tblpfdfail f, Tblppkpermohonan p, Tblrujnegeri n, Tblrujdaerah d, Tblppksimati s,"
				+ " Tblppkpemohon pm, Tblrujstatus st, tblrujpejabatjkptg u, " +
					//	"Tblrujsuburusanstatusfail sub, " +
						"Tblppkpermohonansimati mosi" 
				+ " WHERE p.id_Permohonan = '"+id+"'" 
				+ " AND p.id_Fail = f.id_Fail"
			//	+ " AND pm.id_Permohonan = p.id_Permohonan"  
				+ " AND p.id_Pemohon = pm.id_Pemohon"  
				+ " AND mosi.id_Simati = s.id_Simati"  
				+ " AND p.id_Permohonan = mosi.id_Permohonan"  
				//+ " AND p.id_Permohonan = sub.id_Permohonan"  
				//+ " AND sub.id_Suburusanstatus = 436"  
				//+ " AND p.flag_Jenis_Permohonan = 0"
				+ " AND st.id_Status = p.id_Status"  
				+ " AND d.id_daerah = u.id_daerah"
				+ " AND n.id_Negeri = f.id_Negeri(+)"  
				+ " AND d.id_Daerah = p.id_Daerahmhn(+)"  
				//+ " AND sub.aktif = 1";
				+"";

/*
				sql="SELECT distinct f.id_Fail, f.no_Fail, d.id_Daerah, p.id_Permohonan, " +
			    "p.tarikh_Mohon, s.no_Kp_Baru, s.no_Kp_Lama, s.jenis_Kp, s.no_Kp_Lain, s.id_Simati, " +
			    "s.nama_Simati, s.tarikh_Mati, pm.id_Pemohon, pm.nama_Pemohon, pm.no_kp_baru, " +
			    "pm.no_kP_lama, pm.jenis_Kp, pm.no_Kp_lain, pm.alamat_1, pm.alamat_2, pm.alamat_3, " +
			    "pm.poskod, pm.bandar, n.id_Negeri, n.nama_Negeri, d.nama_Daerah, p.seksyen, st.keterangan," +
			    " p.id_Status, u.nama_pejabat, pm.id_negeri,  mosi.id_Permohonansimati, s.umur, s.jantina, " +
			    " pm.umur, pm.jantina,u.id_pejabatjkptg, p.no_subjaket " +
			    " FROM Tblpfdfail f, Tblppkpermohonan p, Tblrujnegeri n, Tblrujdaerah d, Tblppksimati s, " +
			    " Tblppkpemohon pm, Tblrujstatus st, tblrujpejabatjkptg u,  Tblppkpermohonansimati mosi, Users_Internal ur " +
			    " WHERE f.id_Negeri = n.id_Negeri(+)  " +
			    " AND p.id_Daerahmhn = d.id_Daerah(+) " +
			    " AND p.id_Masuk = ur.user_id " +
			    " And ur.ID_PEJABATJKPTG = u.ID_PEJABATJKPTG " +
			    " AND p.id_Fail = f.id_Fail  " +
			    " AND pm.id_Permohonan = p.id_Permohonan  " +
			    " AND s.id_Simati = mosi.id_Simati  " +
			    " AND p.id_Permohonan = mosi.id_Permohonan    " +
			    " AND st.id_Status = p.id_Status  " +
			    " AND d.id_daerah = p.id_daerahmhn   " +
			    " AND p.id_Permohonan = '"+id+"'"+
			    "";

			*/	
				
				//System.out.println("SQLXXXXXX"+sql);
				
				
				ResultSet rs = stmt.executeQuery(sql);
			    Hashtable h;
				
			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  
			    	  h.put("id_Permohonansimati", rs.getString("id_Permohonansimati")==null?"":rs.getString("id_Permohonansimati"));

			    	 // h.put("id_Suburusanstatus", rs.getString("id_Suburusanstatus")==null?"":rs.getString("id_Suburusanstatus"));
			    	  
			    	  
			    	 // h.put("id_Suburusanstatusfail", rs.getString("id_Suburusanstatusfail")==null?"":rs.getString("id_Suburusanstatusfail"));
			    	 	    	  
			    	  h.put("idFail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
			    	  h.put("noFail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
			    	  h.put("idDaerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
			    	  h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
			    	  h.put("tarikhMohon", rs.getString("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
			    	  h.put("noKpBaru1", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(0,6));
			    	  h.put("noKpBaru2", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(6,8));
			    	  h.put("noKpBaru3", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(8,12));
			    	  h.put("noKpLama", rs.getString("no_Kp_Lama")==null?"":rs.getString("no_Kp_Lama"));
			    	  h.put("jenisKp", rs.getString(8)==null?"":rs.getString(8));
			    	  h.put("noKpLain", rs.getString("no_Kp_Lain")==null?"":rs.getString("no_Kp_Lain"));
			    	  h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
			    	  h.put("namaSimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
			    	  h.put("tarikhMati",  rs.getString("tarikh_Mati")==null?"": sdf.format(rs.getDate("tarikh_Mati")));
			    	  h.put("idPemohon", rs.getString("id_Pemohon")==null?"":rs.getString("id_Pemohon"));
			    	  h.put("namaPemohon", rs.getString("nama_Pemohon")==null?"":rs.getString("nama_Pemohon"));
			    	  h.put("noKpBaruPemohon1", rs.getString(15)==null?"":rs.getString(15).substring(0,6));
			    	  h.put("noKpBaruPemohon2", rs.getString(15)==null?"":rs.getString(15).substring(6,8));
			    	  h.put("noKpBaruPemohon3", rs.getString(15)==null?"":rs.getString(15).substring(8,12));
			    	  h.put("noKpLamaPemohon", rs.getString(16)==null?"":rs.getString(16));
			    	  h.put("jenisKpPemohon", rs.getString(17)==null?"":rs.getString(17));
			    	  h.put("noKpLainPemohon", rs.getString(18)==null?"":rs.getString(18));
			    	  h.put("alamat1", rs.getString("alamat_1")==null?"":rs.getString("alamat_1"));
			    	  h.put("alamat2", rs.getString("alamat_2")==null?"":rs.getString("alamat_2"));
			    	  h.put("alamat3", rs.getString("alamat_3")==null?"":rs.getString("alamat_3"));
			    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
			    	  h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
			    	  h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
			    	  h.put("namanegeri", rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
			    	  h.put("namadaerah", rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
			    	  h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
			    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
			    	  h.put("id_Status", rs.getString("id_Status")==null?"":rs.getString("id_Status"));
			    	  h.put("namaPejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
			    	  h.put("pmidnegeri", rs.getString(31)==null?"":rs.getString(31));
			    	  
			    	  
			    	//  h.put("id_Suburusanstatus", rs.getString("id_Suburusanstatus")==null?"":rs.getString("id_Suburusanstatus"));
			    	//  h.put("id_Suburusanstatusfail", rs.getString("id_Suburusanstatusfail")==null?"":rs.getString("id_Suburusanstatusfail"));
			    	  
			    	  h.put("umursimati", rs.getString(33)==null?"":rs.getString(33));
			    	  h.put("jantinasimati", rs.getString(34)==null?"":rs.getString(34));
			    	  
			    	  h.put("umurpemohon", rs.getString(35)==null?"":rs.getString(35));
			    	  h.put("jantinapemohon", rs.getString(36)==null?"":rs.getString(36));
			    	  
			    	  h.put("id_Permohonansimati", rs.getString("id_Permohonansimati")==null?"":rs.getString("id_Permohonansimati"));
			    	  h.put("id_Permohonansimati", rs.getString("id_Daerahmhn")==null?"":rs.getString("id_Daerahmhn"));
			    	  
			    	  
			    	 // mosi.id_Permohonansimati
			    	  //System.out.println("sql data-->"+h);
			    	  listConvert.addElement(h);
			}      
		}
		finally {
			if(db != null)db.close();
			}
	}



	public  void addPermohonan(Hashtable data) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		String sql8 = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try
			{			
				long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
				long idPemohon = DB.getNextID("TBLPPKPEMOHON_SEQ");
				long idsimati = DB.getNextID("TBLPPKSIMATI_SEQ");
				long idPermohonan = Long.parseLong((String)data.get("IdPermohonan"));
				
				
				//System.out.println("IDPPPPP"+idPermohonan);
				long idSubUrusanStatus = DB.getNextID("TBLRUJSUBURUSANSTATUS_SEQ");

				int UserIdPejabat = Integer.parseInt((String)data.get("userIdPejabat"));
				String userIdNeg = (String)data.get("userIdNeg");
				String userId = (String)data.get("userId");
				//String NegId = (String)data.get("negId");
				String no_daerah = (String)data.get("no_daerah");
				String negeri = (String)data.get("negeri");
				String no_kpbaru_pemohon = (String)data.get("no_kpbaru_pemohon");
				String no_kplama_pemohon = (String)data.get("no_kplama_pemohon");
				String nama_simati = (String)data.get("nama_simati");
				String tarikh_masuk = (String)data.get("tarikh_masuk");
				String no_kpbaru_simati = (String)data.get("no_kp_baru");
				String no_kplama_simati = (String)data.get("no_kplama_simati");
				String sel_jeniskp_simati = (String)data.get("sel_jeniskp_simati");
				String no_kplain_simati = (String)data.get("no_kplain_simati");
				
			    String userIdKodDaerah = (String)data.get("userIdKodDaerah");
				String userIdKodNegeri = (String)data.get("userIdKodNegeri");
				String tarikh_simati = (String)data.get("tarikh_simati");
				String sel_jeniskp_pemohon = (String)data.get("sel_jeniskp_pemohon");
				String no_kplain_pemohon = (String)data.get("no_kplain_pemohon");
				String nama_pemohon = (String)data.get("nama_pemohon");
				String alamat1 = (String)data.get("alamat1");
				String alamat2 = (String)data.get("alamat2");
				String alamat3 = (String)data.get("alamat3");
				String bandar = (String)data.get("bandar");
				String poskod = (String)data.get("poskod");
				
				String buktimati = (String)data.get("buktimati");
				String sijilmati = (String)data.get("sijilmati");
				
				String txtUmurSimati = (String)data.get("txtUmurSimati");
				String socJantinaSimati = (String)data.get("socJantinaSimati");
				String txtUmurPemohon = (String)data.get("txtUmurPemohon");
				String socJantinaPemohon = (String)data.get("socJantinaPemohon");
				
			
				
				//System.out.println("sijilmatimodel::"+sijilmati);
				
				
				
				java.util.Calendar calendar = java.util.Calendar.getInstance();
				int getYear = calendar.get(java.util.Calendar.YEAR);
				
				String X = String.format("%04d",File.getSeqNo(2,382,0,Integer.parseInt(userIdNeg),Integer.parseInt(no_daerah),false,false,getYear));
				
				if (no_daerah.length() < 1){
					no_daerah = "0"+no_daerah;
				}else{
					no_daerah = no_daerah;
				}
				if (userIdNeg.length() < 1){
					userIdNeg = "0"+userIdNeg;
				}else{
					userIdNeg = userIdNeg;
				}
				if (negeri.equals("")){
					negeri = "0";
				}
				
				String getNoFile = "JKPTG/PK/"+ userIdKodNegeri + "/"+ userIdKodDaerah + "/"+X+"/"+getYear;				
				
				tarikh_masuk = (String)data.get("tarikh_masuk");
				tarikh_simati = (String)data.get("tarikh_simati");
				String tarikh_mohon = "to_date('" + tarikh_masuk + "','dd/MM/yyyy')";
		        String tarikh_mati = "to_date('" + tarikh_simati + "','dd/MM/yyyy')";
		     //   int idNeg = Integer.parseInt(NegId);		
		        
		        
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				
				
				Statement stmtA = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_fail",idFail);
				r.add("id_seksyen",2);
				r.add("id_urusan",382);
				r.add("tarikh_daftar_fail",r.unquote("sysdate"));
				r.add("tarikh_masuk",r.unquote("sysdate")); 
				r.add("tajuk_fail",getNoFile);
				r.add("no_fail",getNoFile);
				r.add("id_negeri",Integer.parseInt(userIdNeg));
				r.add("id_suburusan",59);
				r.add("flag_fail",1);
				r.add("id_masuk",userId);
				sql = r.getSQLInsert("tblpfdfail");
				stmtA.executeUpdate(sql);
				
				 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				    Date date = new Date();
				    String currentDate = dateFormat.format(date);
				
			  
				//db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r1 = new SQLRenderer();
				r1.add("id_simati",idsimati);
				r1.add("nama_simati",nama_simati);
				r1.add("no_kp_baru",no_kpbaru_simati);
				r1.add("no_kp_lama",no_kplama_simati);
				r1.add("jenis_kp",sel_jeniskp_simati);
				r1.add("no_kp_lain",no_kplain_simati);
				
				r1.add("no_Sijil_Mati",sijilmati);
				r1.add("id_Buktimati",buktimati);
				
			
				
				r1.add("tarikh_mati",r.unquote(tarikh_mati));
		       // r1.add("id_permohonan",idPermohonan);
				r1.add("id_masuk",userId);
				r1.add("umur",txtUmurSimati);
				r1.add("jantina",socJantinaSimati);
				
				r1.add("tarikh_Masuk",r.unquote("sysdate"));
				sql1 = r1.getSQLInsert("tblppksimati");
				stmt.executeUpdate(sql1);
			
				
				    
			
				//db = new Db();
				Statement stmtc = db.getStatement();
				SQLRenderer r2 = new SQLRenderer();
				r2.add("id_pemohon",idPemohon);
				r2.add("no_kp_baru",no_kpbaru_pemohon);
				r2.add("no_kp_lama",no_kplama_pemohon);
				r2.add("jenis_kp",sel_jeniskp_pemohon);
				r2.add("no_kp_lain",no_kplain_pemohon);
				r2.add("nama_pemohon",nama_pemohon);
				
				
				r2.add("alamat_1",alamat1);
				r2.add("alamat_2",alamat2);
				r2.add("alamat_3",alamat3);
				r2.add("poskod",poskod);
				r2.add("bandar",bandar);
				r2.add("id_negeri",negeri);
				
				/*
				r2.add("alamat1_surat",alamat1);
				r2.add("alamat2_surat",alamat2);
				r2.add("alamat3_surat",alamat3);
				r2.add("poskod_surat",poskod);
				r2.add("bandar_surat",bandar);
				r2.add("id_negerisurat",negeri);
				*/
				
				r2.add("umur",txtUmurPemohon);
				r2.add("jantina",socJantinaPemohon);
			//	r2.add("id_permohonan",idPermohonan);
				r2.add("id_masuk",userId);
				r2.add("tarikh_Masuk",r.unquote("sysdate"));
				sql2 = r2.getSQLInsert("tblppkpemohon");
				stmtc.executeUpdate(sql2);
				
			//	db = new Db();
				Statement stmtd = db.getStatement();
				SQLRenderer r3 = new SQLRenderer();
				r3.add("id_permohonan",idPermohonan);
				r3.add("id_daerahmhn",no_daerah);
				r3.add("id_status",8);
				r3.add("flag_Jenis_Permohonan",1);
				r3.add("id_fail",idFail);
				r3.add("seksyen",8);
				r3.add("id_pemohon",idPemohon);
				r3.add("tarikh_masuk",r.unquote("sysdate"));
				r3.add("tarikh_mohon",r.unquote(tarikh_mohon));
				r3.add("id_unitpskawal",UserIdPejabat);
				r3.add("id_masuk",userId);
				r3.add("id_kemaskini",userId);
				sql3 = r3.getSQLInsert("tblppkpermohonan");
				stmtd.executeUpdate(sql3);
				

				
							
				
				
				
				
			    //baru			
			    
			 //   db = new Db();
				Statement stmt8 = db.getStatement();
				SQLRenderer r8 = new SQLRenderer();
				r8.add("id_Simati",idsimati);					
				r8.add("id_Permohonan",idPermohonan);
				r8.add("id_Masuk",userId);
				r8.add("tarikh_Masuk",r.unquote("sysdate"));
				sql8 = r8.getSQLInsert("tblppkpermohonansimati");
				stmt8.executeUpdate(sql8);    
			    
				
			//	db = new Db();
				Statement stmtF = db.getStatement();
				SQLRenderer r5 = new SQLRenderer();
				r5.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
				r5.add("ID_PERMOHONAN",idPermohonan);
				r5.add("ID_SUBURUSANSTATUS",340);
				r5.add("AKTIF",1);
				r5.add("ID_MASUK",userId);
				r5.add("TARIKH_MASUK",r.unquote("sysdate"));
				r5.add("ID_KEMASKINI",userId);
				r5.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql5 = r5.getSQLInsert("tblrujsuburusanstatusfail");
				stmtF.executeUpdate(sql5);	
				
				conn.commit();
				
			}catch (SQLException se) { 
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
	
	public  void addXPermohonan(Hashtable data,HttpSession session) throws Exception {
		Connection conn = null;
		Db db = null;
	//	Db dbOB = null;
		//Db dbOBU = null;
		String sqlOB = "";
		String sqlOBU = "";
		String sqlbayaran = "";
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		String sql8 = "";
		String sql19 = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try
			{			
			//	long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
				//long idPemohon = DB.getNextID("TBLPPKPEMOHON_SEQ");
				//long idsimati = DB.getNextID("TBLPPKSIMATI_SEQ");
				long idBayaran = DB.getNextID("TBLPPKBAYARAN_SEQ");
				
				long idPermohonan = Long.parseLong((String)data.get("IdPermohonan"));
				
				long idFail = Long.parseLong((String)data.get("idFail"));
				long idPemohon = Long.parseLong((String)data.get("idPemohon"));
				String IdPemohon = (String) data.get("idPemohon");
				long idSimati = Long.parseLong((String)data.get("idSimati"));
				long IdSimati = Long.parseLong((String)data.get("idSimati"));
				long id_Suburusanstatusfail = Long.parseLong((String)data.get("id_Suburusanstatusfail"));
				
				
				//System.out.println("IDPPPPP"+idPermohonan);
				
				//long idSubUrusanStatus = DB.getNextID("TBLRUJSUBURUSANSTATUS_SEQ");

				int UserIdPejabat = Integer.parseInt((String)data.get("userIdPejabat"));
				String userIdNeg = (String)data.get("userIdNeg");		
				
				
				String userId = (String)data.get("userId");
				String id_Masuk = (String)data.get("userId");
				//String NegId = (String)data.get("negId");
				String no_daerah = (String)data.get("no_daerah");
				String nofail = (String)data.get("nofail");
				
			
				String negeri = (String)data.get("negeri");
				String no_kpbaru_pemohon = (String)data.get("no_kpbaru_pemohon");
				String no_kplama_pemohon = (String)data.get("no_kplama_pemohon");
				String nama_simati = (String)data.get("nama_simati");
				String tarikh_masuk = (String)data.get("tarikh_masuk");
				String no_kpbaru_simati = (String)data.get("no_kp_baru");
				String no_kplama_simati = (String)data.get("no_kplama_simati");
				String sel_jeniskp_simati = (String)data.get("sel_jeniskp_simati");
				String no_kplain_simati = (String)data.get("no_kplain_simati");
				
			    String userIdKodDaerah = (String)data.get("userIdKodDaerah");
				String userIdKodNegeri = (String)data.get("userIdKodNegeri");
				String tarikh_simati = (String)data.get("tarikh_simati");
				String sel_jeniskp_pemohon = (String)data.get("sel_jeniskp_pemohon");
				String no_kplain_pemohon = (String)data.get("no_kplain_pemohon");
				String nama_pemohon = (String)data.get("nama_pemohon");
				String alamat1 = (String)data.get("alamat1");
				String alamat2 = (String)data.get("alamat2");
				String alamat3 = (String)data.get("alamat3");
				String bandar = (String)data.get("bandar");
				String idbandar = (String)data.get("idbandar");
				String poskod = (String)data.get("poskod");
				
				String buktimati = (String)data.get("buktimati");
				String sijilmati = (String)data.get("sijilmati");
				
				String txtUmurSimati = (String)data.get("txtUmurSimati");
				String socJantinaSimati = (String)data.get("socJantinaSimati");
				String txtUmurPemohon = (String)data.get("txtUmurPemohon");
				String socJantinaPemohon = (String)data.get("socJantinaPemohon");
				
				String txtbox = (String)data.get("txtbox");
				String tarikhresit = (String)data.get("tarikhresit");
				
				String no_tel = (String) data.get("no_tel");
				String nama_pelbagainegara = (String) data.get("nama_pelbagainegara");
				String no_hp = (String) data.get("no_hp");
				String jenis_pej = (String) data.get("jenis_pej");
				String taraf_penting = (String) data.get("taraf_penting");
				String jenis_pemohon = (String) data.get("jenis_pemohon");
				String socSaudaraWaris = (String) data.get("socSaudaraWaris");
				
				
				String adaob = (String) data.get("adaob");
				String id_Permohonansimati = (String) data.get("id_Permohonansimati");
				
				Vector cpw = checkpemohonwaris(IdPemohon);
				
				
				////System.out.println("sijilmatimodel::"+sijilmati);
				
				
				
				java.util.Calendar calendar = java.util.Calendar.getInstance();
				int getYear = calendar.get(java.util.Calendar.YEAR);
				
				String X = "";
				if(nofail.equals(""))
				{
					X = String.format("%04d",File.getSeqNoPPK(session,2,382,0,Integer.parseInt(userIdNeg),Integer.parseInt(no_daerah),false,false,getYear));
					
					AuditTrail at = new AuditTrail();
					//at.logActivity("","2",null,session,"","CREATE NO FAIL ONLINE : ID_SEKSYEN = '"+2+"';ID_URUSAN = '"+382+"';ID_KEMENTERIAN = '"+0+"';ID_NEGERI = '"+Integer.parseInt(userIdNeg)+"';ID_DAERAH = '"+Integer.parseInt(no_daerah)+"';TAHUN = '"+getYear+"'; SEQ : "+X);
					at.logActivity(382+"","","2",null,session,"","CREATE NO FAIL ONLINE : ID_SEKSYEN = '"+2+"';ID_URUSAN = '"+382+"';ID_KEMENTERIAN = '"+0+"';ID_NEGERI = '"+Integer.parseInt(userIdNeg)+"';ID_DAERAH = '"+Integer.parseInt(no_daerah)+"';TAHUN = '"+getYear+"'; SEQ : "+X+"; idFail : "+idFail);
				}
				
				if (no_daerah.length() < 1){
					no_daerah = "0"+no_daerah;
				}else{
					no_daerah = no_daerah;
				}
				if (userIdNeg.length() < 1){
					userIdNeg = "0"+userIdNeg;
				}else{
					userIdNeg = userIdNeg;
				}
				if (negeri.equals("")){
					negeri = "0";
				}
				
				String getNoFile = "";
				
				
				
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);

				
				
				Statement stmt12 = db.getStatement();
				SQLRenderer r12 = new SQLRenderer();
				
			
				r12.add("d.id_negeri");
				r12.add("d.id_daerah");
				r12.add("d.nama_daerah");
				r12.add("d.kod_daerah");
				r12.add("n.kod_negeri");
				
				r12.add("d.id_negeri",r12.unquote("n.id_Negeri"));
				r12.add("d.id_daerah",no_daerah);
				
				
				String sql12 = r12.getSQLSelect("Tblrujdaerah d, Tblrujnegeri n");
				ResultSet rs12 = stmt12.executeQuery(sql12);
				//Vector list = new Vector(;
				Hashtable h;
				//int bil = 1;
				String kod="";
				String kodn="";
				while (rs12.next()){
					h = new Hashtable();
					//h.put("bil", bil);
					//h.put("id_Permohonan", rs.getString("id_Permohonan"));
					//h.put("id_Hta", rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
					h.put("id_negeri",rs12.getString("id_negeri")==null?"":rs12.getString("id_negeri"));
					h.put("id_daerah",rs12.getString("id_daerah")==null?"":rs12.getString("id_daerah"));
					h.put("nama_daerah",rs12.getString("nama_daerah")==null?"":rs12.getString("nama_daerah"));
					h.put("kod_daerah",rs12.getString("kod_daerah")==null?"":rs12.getString("kod_daerah"));
					h.put("kod_negeri",rs12.getString("kod_negeri")==null?"":rs12.getString("kod_negeri"));
					
					
					//bil++;
					kod=rs12.getString("kod_daerah");
					kodn=rs12.getString("kod_negeri");
				}
				
				
			//	String getNoFile = "JKPTG/PK/"+ userIdKodNegeri + "/"+ userIdKodDaerah + "/"+X+"/"+getYear;				
				//String getNoFile = "JKPTG/PK/"+ kodn + "/"+ kod + "/"+X+"/"+getYear;	
				
				
				
				
				if(nofail=="")
				{
		     //	getNoFile = "JKPTG/PK/"+ userIdKodNegeri + "/"+ userIdKodDaerah + "/"+X+"/"+getYear;				
					getNoFile = "JKPTG/PK/"+ kodn + "/"+ kod + "/"+X+"/"+getYear;	
					
				}
				
				
				tarikh_masuk = (String)data.get("tarikh_masuk");
				tarikh_simati = (String)data.get("tarikh_simati");
				String tarikh_mohon = "to_date('" + tarikh_masuk + "','dd/MM/yyyy')";
		        String tarikh_mati = "to_date('" + tarikh_simati + "','dd/MM/yyyy')";
		        tarikhresit = "to_date('" + tarikhresit + "','dd/MM/yyyy')";
		        
		       // int idNeg = Integer.parseInt(NegId);		
		        
		        
			//	db = new Db();
				Statement stmtA = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.update("id_fail",idFail);
				r.add("id_seksyen",2);
				r.add("id_urusan",382);
				r.add("tarikh_daftar_fail",r.unquote("sysdate"));
				r.add("tarikh_Kemaskini",r.unquote("sysdate")); 
				
				if(nofail=="")
				{
				r.add("tajuk_fail",getNoFile);
				r.add("no_fail",getNoFile);
				}
				r.add("id_negeri",Integer.parseInt(userIdNeg));
				r.add("id_suburusan",59);
				r.add("flag_fail",1);
				r.add("flag_jenis_fail",1);
				r.add("id_Kemaskini",userId);
			//	+" AND F.FLAG_JENIS_FAIL = 1"
				sql = r.getSQLUpdate("tblpfdfail");
				myLogger.info("###1:"+sql);
				
				stmtA.executeUpdate(sql);
				
				 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				    Date date = new Date();
				    String currentDate = dateFormat.format(date);
				
				    
				   
			  /*
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r1 = new SQLRenderer();
				r1.update("id_simati",idSimati);
				r1.add("nama_simati",nama_simati);
				r1.add("no_kp_baru",no_kpbaru_simati);
				r1.add("no_kp_lama",no_kplama_simati);
				r1.add("jenis_kp",sel_jeniskp_simati);
				r1.add("no_kp_lain",no_kplain_simati);				
				r1.add("no_Sijil_Mati",sijilmati);
				r1.add("id_Buktimati",buktimati);
				r1.add("tarikh_mati",r.unquote(tarikh_mati));
				r1.add("id_Kemaskini",userId);
				r1.add("umur",txtUmurSimati);
				r1.add("jantina",socJantinaSimati);				
				r1.add("tarikh_Kemaskini",r.unquote("sysdate"));
				
				sql1 = r1.getSQLUpdate("tblppksimati");
				stmt.executeUpdate(sql1);
			
				
			
			
				db = new Db();
				Statement stmtc = db.getStatement();
				SQLRenderer r2 = new SQLRenderer();
				r2.update("id_pemohon",idPemohon);
				r2.add("no_kp_baru",no_kpbaru_pemohon);
				r2.add("no_kp_lama",no_kplama_pemohon);
				r2.add("jenis_kp",sel_jeniskp_pemohon);
				r2.add("no_kp_lain",no_kplain_pemohon);
				r2.add("nama_pemohon",nama_pemohon);				
				r2.add("alamat_1",alamat1);
				r2.add("alamat_2",alamat2);	
			    r2.add("alamat_3",alamat3);
				r2.add("poskod",poskod);
				r2.add("id_bandar",idbandar);
				r2.add("id_negeri",negeri);
				r2.add("umur",txtUmurPemohon);
				r2.add("jantina",socJantinaPemohon);
				r2.add("id_Kemaskini",userId);
				r2.add("tarikh_Kemaskini",r.unquote("sysdate"));
				sql2 = r2.getSQLUpdate("tblppkpemohon");
				stmtc.executeUpdate(sql2);
				
				*/
				    
				//    db = new Db();
					
					/*
					sql3 = "UPDATE TBLPPKPEMOHON SET ";
					if(jenis_pemohon.equals("2")){
					sql3 += "no_kp_baru='" + no_kpbaru_pemohon+
							"',umur='" + txtUmurPemohon +
							"',no_hp='"
							+ no_hp + "' ,jantina='"+ socJantinaPemohon + 					
							"',no_kp_lama='"+ no_kplama_pemohon.toUpperCase() + 
							"',jenis_kp='" + sel_jeniskp_pemohon+
							"', no_kp_lain='" + no_kplain_pemohon.toUpperCase() +"',";
					}
					else
					{
						sql3 += "no_kp_baru='',umur='',jantina='' ," +
								"no_kp_lama='',jenis_kp='', no_kp_lain='',no_hp='',";
					}		
							sql3+=" nama_pemohon=\"" + nama_pemohon.toUpperCase() +
							"\",id_Kemaskini='" + userId + 
							"',tarikh_Kemaskini=sysdate ," +					
							"alamat_1='"
							+ alamat1.toUpperCase() + "', alamat_2='" + alamat2.toUpperCase() + "', alamat_3='"
							+ alamat3.toUpperCase() + "', poskod='" + poskod + "',id_bandar='"
							+ idbandar + "', bandar='" + bandar + "', id_negeri='"
							+ negeri + "', id_tarafkptg='"
							+ taraf_penting + "', no_tel='"
							+ no_tel + "' , id_arb='"
							+ jenis_pej + "' , status_pemohon='"
							+ jenis_pemohon + "' where id_pemohon = " + idPemohon + "";
				*/
				    Statement stmtc = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					r2.update("id_pemohon",idPemohon);
					if(jenis_pemohon.equals("2")){
					r2.add("no_kp_baru",no_kpbaru_pemohon);
					r2.add("umur",txtUmurPemohon);
					r2.add("no_hp",no_hp);
					r2.add("jantina",socJantinaPemohon);
					r2.add("no_kp_lama",no_kplama_pemohon.toUpperCase());
					r2.add("jenis_kp",sel_jeniskp_pemohon);
					r2.add("no_kp_lain",no_kplain_pemohon.toUpperCase());
					}
					else
					{
					r2.add("no_kp_baru","");
					r2.add("umur","");
					r2.add("jantina","");
					r2.add("no_kp_lama","");
					r2.add("jenis_kp","");
					r2.add("no_kp_lain","");				
					}					
					r2.add("nama_pemohon",nama_pemohon.toUpperCase());
					r2.add("alamat_1",alamat1.toUpperCase());
					r2.add("alamat_2",alamat2.toUpperCase());
					r2.add("alamat_3",alamat3.toUpperCase());
					r2.add("poskod",poskod);
					r2.add("id_bandar",idbandar);
					r2.add("bandar",bandar);
					r2.add("id_negeri",negeri);
					r2.add("id_tarafkptg",taraf_penting);
					r2.add("no_tel",no_tel);
					
					r2.add("nama_pelbagainegara",nama_pelbagainegara);
					r2.add("nama_pelbagainegara_surat",nama_pelbagainegara);
					
					r2.add("id_arb",jenis_pej);
					r2.add("status_pemohon",jenis_pemohon);		
					r2.add("id_saudara", socSaudaraWaris);				
					r2.add("id_Kemaskini",userId);
					r2.add("tarikh_Kemaskini",r.unquote("sysdate"));
					sql3 = r2.getSQLUpdate("tblppkpemohon");									
					myLogger.info("###2:"+sql3);
					stmtc.executeUpdate(sql3);
				    
				   
				    /*
				    //sebelum tukar structure
					if(taraf_penting != "0" && taraf_penting!="" && cpw.size()==0)
				      {
				          Statement stmtOB = db.getStatement();
					      SQLRenderer rOB = new SQLRenderer();
					      rOB.add("id_Simati", idSimati);
					      rOB.add("nama_Ob", nama_pemohon.toUpperCase());
					      
					      if(jenis_pemohon.equals("2")){
					      rOB.add("no_Kp_Baru", no_kpbaru_pemohon);
					      rOB.add("no_Kp_Lain",no_kplain_pemohon.toUpperCase());	
					      rOB.add("no_Kp_Lama",no_kplama_pemohon.toUpperCase());
					      rOB.add("jenis_Kp", sel_jeniskp_pemohon);
					      rOB.add("jantina", socJantinaPemohon);
					      rOB.add("umur", txtUmurPemohon);
					      rOB.add("no_hp",no_hp);
					      }
					      else
					      {
					    	  rOB.add("no_Kp_Baru", "");
						      rOB.add("no_Kp_Lain","");	
						      rOB.add("no_Kp_Lama","");
						      rOB.add("jenis_Kp", "");
						      rOB.add("jantina", "");
						      rOB.add("umur", "");
						      rOB.add("no_hp","");					    	  
					      }
					      rOB.add("id_Tarafkptg",taraf_penting);
					      rOB.add("jenis_pemiutang",jenis_pemohon);			    
					      rOB.add("id_Permohonansimati",id_Permohonansimati);
						  rOB.add("no_Tel",no_tel); 
					      rOB.add("alamat_1", alamat1.toUpperCase());
					      rOB.add("alamat_2", alamat2.toUpperCase());
					      rOB.add("alamat_3", alamat3.toUpperCase());
					      rOB.add("id_Bandar",idbandar);
					      rOB.add("poskod", poskod);
					      rOB.add("lapis", 1);
					      rOB.add("status_hidup",0);
					      rOB.add("id_Pemohon",idPemohon);			    
					      rOB.add("id_Negeri", negeri);			     	
					      rOB.add("id_Kemaskini",userId);					
					      rOB.add("tarikh_Kemaskini",r.unquote("sysdate"));
					      rOB.add("id_Masuk",userId);					
					      rOB.add("tarikh_Masuk",r.unquote("sysdate"));
					      rOB.add("alamat1_Surat",alamat1.toUpperCase());
						  rOB.add("alamat2_Surat",alamat2.toUpperCase());
						  rOB.add("alamat3_Surat",alamat3.toUpperCase());
						  rOB.add("poskod_Surat",poskod);
						  rOB.add("id_Bandarsurat",idbandar);
						  rOB.add("id_Negerisurat",negeri);
						  rOB.add("id_arb",jenis_pej);
						  rOB.add("id_saudara", socSaudaraWaris);
					      sqlOB = rOB.getSQLInsert("tblppkob");
					      myLogger.info("###3:"+sqlOB);
					      stmtOB.executeUpdate(sqlOB);		    	  
				      }
					
					if(taraf_penting != "0" && taraf_penting!="" && cpw.size()==0)
					{
						  Statement stmtOBU = db.getStatement();
					      SQLRenderer rOBU = new SQLRenderer();
					      rOBU.update("id_Pemohon",idPemohon);
					      rOBU.add("id_Simati", idSimati);
					      rOBU.add("nama_Ob", nama_pemohon.toUpperCase());
					      if(jenis_pemohon.equals("2")){
					      rOBU.add("no_Kp_Baru", no_kpbaru_pemohon);
					      rOBU.add("no_Kp_Lain",no_kplain_pemohon.toUpperCase());	
					      rOBU.add("no_Kp_Lama",no_kplama_pemohon.toUpperCase());
					      rOBU.add("jenis_Kp", sel_jeniskp_pemohon);
					      rOBU.add("umur", txtUmurPemohon);	
					      rOBU.add("jantina", socJantinaPemohon);
					      rOBU.add("no_hp",no_hp);
					      }
					      else
					      {
					    	  rOBU.add("no_Kp_Baru", "");
						      rOBU.add("no_Kp_Lain","");	
						      rOBU.add("no_Kp_Lama","");
						      rOBU.add("jenis_Kp", "");
						      rOBU.add("umur", "");	
						      rOBU.add("jantina", "");
						      rOBU.add("no_hp","");
					      }
					      rOBU.add("id_Tarafkptg",taraf_penting);
					      rOBU.add("jenis_pemiutang",jenis_pemohon);			    
					      rOBU.add("id_Permohonansimati",id_Permohonansimati);			  			      
					      	 
						  rOBU.add("no_Tel",no_tel);			     
					      		      			    
					      rOBU.add("alamat_1", alamat1.toUpperCase());
					      rOBU.add("alamat_2", alamat2.toUpperCase());
					      rOBU.add("alamat_3", alamat3.toUpperCase());
					      rOBU.add("id_Bandar",idbandar);
					      rOBU.add("poskod", poskod);
					      rOBU.add("lapis", 1);
					      rOBU.add("status_hidup",0);
					      			    
					      rOBU.add("id_Negeri", negeri);			     	
					      rOBU.add("id_Kemaskini",userId);					
					      rOBU.add("tarikh_Kemaskini",r.unquote("sysdate"));
					      rOBU.add("id_Masuk",userId);					
					      rOBU.add("tarikh_Masuk",r.unquote("sysdate"));
					      rOBU.add("alamat1_Surat",alamat1.toUpperCase());
						  rOBU.add("alamat2_Surat",alamat2.toUpperCase());
						  rOBU.add("alamat3_Surat",alamat3.toUpperCase());
						  rOBU.add("poskod_Surat",poskod);
						  rOBU.add("id_Bandarsurat",idbandar);
						  rOBU.add("id_Negerisurat",negeri);
						  rOBU.add("id_arb",jenis_pej);
						  rOBU.add("id_saudara", socSaudaraWaris);
						 
					      sqlOBU = rOBU.getSQLUpdate("tblppkob");
					      myLogger.info("###4:"+sqlOBU);
					      stmtOBU.executeUpdate(sqlOBU);
					      
					      
					}
				*/	
				
				    
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
						
						r.add("nama_pelbagainegara",nama_pelbagainegara);
						r.add("nama_pelbagainegara_surat",nama_pelbagainegara);
						
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
						
						r.add("nama_pelbagainegara",nama_pelbagainegara);
						r.add("nama_pelbagainegara_surat",nama_pelbagainegara);
						
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
						
						
						r.add("nama_pelbagainegara",nama_pelbagainegara);
						r.add("nama_pelbagainegara_surat",nama_pelbagainegara);
						
						sqlOBU = r.getSQLUpdate("tblppkob");				
						stmt.executeUpdate(sqlOBU);
						
						
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
						
						r.add("nama_pelbagainegara",nama_pelbagainegara);
						r.add("nama_pelbagainegara_surat",nama_pelbagainegara);
						
						
						sqlOBU = r.getSQLUpdate("tblppkobpermohonan");				
						stmt.executeUpdate(sqlOBU);
					}

					
					
				
			
		//		db = new Db();
				Statement stmtd = db.getStatement();
				SQLRenderer r3 = new SQLRenderer();
				r3.update("id_permohonan",idPermohonan);
				r3.add("id_daerahmhn",no_daerah);
				//r3.add("id_status",8);
				r3.add("flag_Jenis_Permohonan",1);
				r3.add("id_fail",idFail);
				r3.add("seksyen",8);
				r3.add("no_subjaket",0);
				r3.add("id_pemohon",idPemohon);
				r3.add("tarikh_Kemaskini",r3.unquote("sysdate"));
				r3.add("tarikh_mohon",r3.unquote(tarikh_mohon));				
				//+ ",id_kemaskini = " + id_Masuk + ","				
				r3.add("id_unitpskawal",UserIdPejabat);
				r3.add("id_Kemaskini",userId);
				sql3 = r3.getSQLUpdate("tblppkpermohonan");
				myLogger.info("###5:"+sql3);
				stmtd.executeUpdate(sql3);

			
				
				
			    //baru			
			   /* 
			    db = new Db();
				Statement stmt8 = db.getStatement();
				SQLRenderer r8 = new SQLRenderer();
				r8.add("id_Simati",idsimati);					
				r8.add("id_Permohonan",idPermohonan);
				r8.add("id_Masuk",userId);
				r8.add("tarikh_Masuk",r.unquote("sysdate"));
				sql8 = r8.getSQLInsert("tblppkpermohonansimati");
				stmt8.executeUpdate(sql8);    
			    */
				 /*
				long idPermohonan = Long.parseLong((String)data.get("IdPermohonan"));
				long idFail = Long.parseLong((String)data.get("idFail"));
				long idPemohon = Long.parseLong((String)data.get("idPemohon"));
				long idSimati = Long.parseLong((String)data.get("idSimati"));
				long id_Suburusanstatusfail = Long.parseLong((String)data.get("id_Suburusanstatusfail"));
				
			    */
				
			
		/*
				Statement stmtG = db.getStatement();
				SQLRenderer r8 = new SQLRenderer();
				r8.update("ID_SUBURUSANSTATUSFAIL",id_Suburusanstatusfail);
				r8.add("ID_PERMOHONAN",idPermohonan);
				//r8.add("ID_SUBURUSANSTATUS",340);
				r8.add("AKTIF",0);				
				r8.add("ID_KEMASKINI",userId);
				r8.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql19 = r8.getSQLUpdate("tblrujsuburusanstatusfail");
				myLogger.info("###6:"+sql19);
				stmtG.executeUpdate(sql19);	
				
				
				Statement stmtF = db.getStatement();
				SQLRenderer r5 = new SQLRenderer();
				r5.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
				r5.add("ID_PERMOHONAN",idPermohonan);
				r5.add("ID_SUBURUSANSTATUS",340);
				r5.add("AKTIF",1);
				r5.add("ID_FAIL",idFail);				
				r5.add("ID_MASUK",userId);
				r5.add("TARIKH_MASUK",r.unquote("sysdate"));
				r5.add("ID_KEMASKINI",userId);
				r5.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql5 = r5.getSQLInsert("tblrujsuburusanstatusfail");
				myLogger.info("###7:"+sql5);
				stmtF.executeUpdate(sql5);	
				
				*/
				
			
			      Statement stmtL = db.getStatement();
			      SQLRenderer r9 = new SQLRenderer();
			      r9.add("id_bayaran", idBayaran);
			      r9.add("id_permohonan", idPermohonan);
			      r9.add("id_jenisbayaran",2);
			      
			      //r1.add("id_masuk",6); 
			      String tr = "";
			      if(txtbox != "")
			      {
			      r9.add("no_resit",txtbox);
			//      tr = "to_date('" + tarikhresit + "','dd/MM/yyyy')";
			      r9.add("tarikh_bayaran",r9.unquote(tarikhresit));
			      r9.add("jumlah_bayaran",10);
			      }
			      else{
			    	  r9.add("no_resit","");
				      tr = "to_date('" + tarikhresit + "','dd/MM/yyyy')";
				      r9.add("tarikh_bayaran","");
				      r9.add("jumlah_bayaran",0);  
			      }
			      
			      
			      
			      r9.add("ID_MASUK",userId);
			      r9.add("ID_KEMASKINI",userId);
				  r9.add("TARIKH_KEMASKINI",r9.unquote("sysdate"));
			      r9.add("tarikh_masuk",r9.unquote("sysdate")); 
			      sqlbayaran = r9.getSQLInsert("tblppkbayaran");
			      //System.out.println("sqlbayaran-->"+sqlbayaran);
			      myLogger.info("###8:"+sqlbayaran);
			      stmtL.executeUpdate(sqlbayaran);
				
			      conn.commit();
			      
			      myLogger.info("SSF KEMASKINI 17");
			      FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
			      logic_A.kemaskiniSubUrusanStatusFail(session,idPermohonan+"",userId,"8","340",idFail+"");
			      
			      //mobile notification 12092017
			      Push.genMsgPush(idFail+"", "daftar");
			      
			}catch (SQLException se) { 
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
	
	public  void updatePermohonan(Hashtable data) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		//SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			try
			{	
				String IdFail = (String)data.get("IdFail");
			    String IdSimati = (String)data.get("IdSimati");
				String IdPemohon = (String)data.get("IdPemohon");
				String IdPermohonan = (String)data.get("IdPermohonan");
			    String no_daerah = (String)data.get("no_daerah");
				String negeri = (String)data.get("negeri");
				String no_kpbaru_pemohon = (String)data.get("no_kpbaru_pemohon");
				String no_kplama_pemohon = (String)data.get("no_kplama_pemohon");
				String nama_simati = (String)data.get("nama_simati");
				String tarikh_masuk = (String)data.get("tarikh_masuk");
				String tarikh_daftar = (String)data.get("tarikh_daftar");
				String no_kpbaru_simati = (String)data.get("no_kp_baru");
				String no_kplama_simati = (String)data.get("no_kplama_simati");
				String sel_jeniskp_simati = (String)data.get("sel_jeniskp_simati");
				String no_kplain_simati = (String)data.get("no_kplain_simati");
				String tarikh_simati = (String)data.get("tarikh_simati");
				String sel_jeniskp_pemohon = (String)data.get("sel_jeniskp_pemohon");
				String no_kplain_pemohon = (String)data.get("no_kplain_pemohon");
				String nama_pemohon = (String)data.get("nama_pemohon");
				String alamat1 = (String)data.get("alamat1");
				String alamat2 = (String)data.get("alamat2");
				String alamat3 = (String)data.get("alamat3");
				String bandar = (String)data.get("bandar");
				String poskod = (String)data.get("poskod");	
				String id_Masuk = (String)data.get("id_Masuk");	
				
				String txtUmurSimati = (String)data.get("txtUmurSimati");
				String socJantinaSimati = (String)data.get("socJantinaSimati");	
				String txtUmurPemohon = (String)data.get("txtUmurPemohon");
				String socJantinaPemohon = (String)data.get("socJantinaPemohon");	
				
				//int umursimati = Integer.parseInt(txtUmurSimati);
				//int jantinasimati = Integer.parseInt(socJantinaSimati);
				
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		        Date date = new Date();
		        String currentDate = dateFormat.format(date);
				
				String tarikhdaftar = "to_date('" + tarikh_daftar + "','dd/MM/yyyy')";
				String tarikhsimati = "to_date('" + tarikh_simati + "','dd/MM/yyyy')";
				
						        					
		        db = new Db();
		        conn = db.getConnection();
		        conn.setAutoCommit(false);
		        
				Statement stmt = db.getStatement();
				sql = "UPDATE TBLPFDFAIL SET tarikh_daftar_fail=sysdate, " +
				"tarikh_kemaskini = sysdate where id_fail = "+ IdFail +" ";
				stmt.executeUpdate(sql);
				
			//	db = new Db();
				Statement stmtA = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.update("id_simati", IdSimati);
				r.add("nama_simati",nama_simati);
				r.add("no_kp_baru",no_kpbaru_simati);
				r.add("no_kp_lama",no_kplama_simati);
				r.add("jenis_kp",sel_jeniskp_simati);
				r.add("no_kp_lain",no_kplain_simati);
				r.add("tarikh_mati",r.unquote(tarikhsimati));
				r.add("tarikh_masuk",r.unquote(tarikhdaftar));
				r.add("id_Kemaskini",id_Masuk);
				
				//int umursimati = Integer.parseInt(txtUmurSimati);
				//int jantinasimati = Integer.parseInt(socJantinaSimati);
				
				r.add("umur",txtUmurSimati);
				r.add("jantina",socJantinaSimati);
				
				
			    r.add("tarikh_Kemaskini",r.unquote("sysdate"));
				  
				sql1 = r.getSQLUpdate("tblppksimati");
			    stmtA.executeUpdate(sql1);
						
				//db = new Db();
				Statement stmtc = db.getStatement();
				//sql3 = "UPDATE TBLPPKPEMOHON SET no_kp_baru='"+ no_kpbaru_pemohon +"',umur='"+ txtUmurPemohon +"',jantina='"+ socJantinaPemohon +"',id_Kemaskini='"+ id_Masuk +"',tarikh_Kemaskini=sysdate , no_kp_lama='"+ no_kplama_pemohon +"',jenis_kp='"+ sel_jeniskp_pemohon +"', no_kp_lain='"+ no_kplain_pemohon +"', nama_pemohon='"+ nama_pemohon +"', alamat1_surat='"+ alamat1 +"', alamat2_surat='"+ alamat2 +"', alamat3_surat='"+ alamat3 +"', poskod_surat='"+ poskod +"', bandar_surat='"+ bandar +"', id_negerisurat='"+ negeri +"' where id_pemohon = "+ IdPemohon +"";
				sql3 = "UPDATE TBLPPKPEMOHON SET no_kp_baru='"+ no_kpbaru_pemohon +"',umur='"+ txtUmurPemohon +"',jantina='"+ socJantinaPemohon +"',id_Kemaskini='"+ id_Masuk +"',tarikh_Kemaskini=sysdate , no_kp_lama='"+ no_kplama_pemohon +"',jenis_kp='"+ sel_jeniskp_pemohon +"', no_kp_lain='"+ no_kplain_pemohon +"', nama_pemohon='"+ nama_pemohon +"', alamat_1='"+ alamat1 +"', alamat_2='"+ alamat2 +"', alamat_3='"+ alamat3 +"', poskod='"+ poskod +"', bandar='"+ bandar +"', id_negeri='"+ negeri +"' where id_pemohon = "+ IdPemohon +"";
					
				stmtc.executeUpdate(sql3);
				
				/*db = new Db();
				Statement stmtd = db.getStatement();
				sql4 = "UPDATE TBLPPKPERMOHONAN set id_daerahmhn = "+ no_daerah +" where " +
				"id_permohonan = "+ IdPermohonan +" and id_fail="+ IdFail +"";
				//System.out.println("sql4 Permohonan-->"+sql4);
				stmtd.executeUpdate(sql4);	*/
				conn.commit();	
				
			}catch (SQLException se) { 
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
	
	 public  Vector getJenisKp()throws Exception {
		    Db db = null;
		    String sql = "Select id_jenisnopb,keterangan" +
			" from tblrujjenisnopb where id_jenisnopb in(4,5,6,7) ";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("id_jenisnopb");
		      r.add("keterangan");
		
		      //sql = r.getSQLSelect(sql);
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
		      if (db != null) db.close();
		    }
		  }

 
	//*** query data have 'pemilik' from database
		public  void setSemak(String idPermohonan)throws Exception {
			
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
		      //Vector list = new Vector();
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
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }

		public  Vector getSemak(){
			  return list;
		  }
	 
	//*** simpan data from database
	  public  int simpanSemak(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		    try
		    {
		      long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
			  String txtNomborSijil = (String)data.get("txtNomborSijil");
			  String idpermohonan = (String)data.get("idpermohonan");

		      db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();  
			  r.add("id_semakansenarai",idpermohonan);
		      r.add("catatan",txtNomborSijil);
		      sql = r.getSQLInsert("tblsemakanhantar");
			  //stmt.executeUpdate(sql);
		      return (int)idPermohonan;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	  
	//*** update data from database
	  public  String updateSemak(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		    try
		    {
		    	long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");      
//			      return (int)idPermohonan;
		    	return String.valueOf(idPermohonan);
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	  

	public  Vector getJenisHa()throws Exception {
		  Db db = null;
		  String sql = "Select id_jenisha,kod,keterangan from tblppkrujjenisha where id_jenisha > 0 order by id_jenisha  ";
			 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("id_jenisnopb");
		      r.add("keterangan");
		
		      //sql = r.getSQLSelect(sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector listjenisha = new Vector();
		      Hashtable h;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("idjenisha", rs.getString("id_jenisha"));
		    	  h.put("kod", rs.getString("kod"));
		    	  h.put("keterangan", rs.getString("keterangan"));
		    	  listjenisha.addElement(h);
		      }
		      return listjenisha;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
	public void addHa(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {

			String id_Permohonansimati = (String) data.get("id_Permohonansimati");
			String id = (String) data.get("id");
			String id1 = (String) data.get("id1");
			int socJenisHartaAlih = Integer.parseInt((String) data
					.get("socJenisHartaAlih"));
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

			//System.out.println("POSKODDDD:" + txtPoskod);

			String socNegeriHtaam = (String) data.get("socNegeriHtaam");
			String socDaerahHtaam = (String) data.get("socDaerahHtaam");
			String id_Masuk = (String) data.get("id_Masuk");

			long idInsert = DB.getNextID("TBLPPKHA_SEQ");

			db = new Db();
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

			if (socJenisHartaAlih == 2 || socJenisHartaAlih == 3) {
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

			 r.add("flag_pa","T");
			  r.add("flag_pt","T"); 
			  r.add("flag_selesai","T"); 
			  
			sql = r.getSQLInsert("tblppkha");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}
private  Vector listDataHa = new Vector();
	
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
				+ "FROM TBLPPKHA H, TBLPPKRUJJENISHA J, TBLPPKPERMOHONANSIMATI MS "
				+ "WHERE H.ID_JENISHA = J.ID_JENISHA  AND H.ID_SIMATI = MS.ID_SIMATI  "
				+ "AND H.ID_PERMOHONANSIMATI = MS.ID_PERMOHONANSIMATI AND MS.ID_PERMOHONANSIMATI = '"
				+ id + "' " + "ORDER BY H.ID_JENISHA,H.ID_HA DESC ";

		//System.out.println("WWWW:::" + sql.toUpperCase());
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		int bil = 1;

		while (rs.next()) {
			h = new Hashtable();
			h.put("bil", bil);
			h.put("id_Ha", rs.getString("id_Ha") == null ? "" : rs
					.getString("id_Ha"));
			h.put("nilai_tm", rs.getString("NILAI_HA_TARIKHMOHON") == null ? "" : rs
					.getDouble("NILAI_HA_TARIKHMOHON"));
			h.put("id_Jenisha", rs.getString("id_Jenisha") == null ? ""
					: rs.getString("id_Jenisha"));
			h.put("id_Simati", rs.getString("id_Simati") == null ? "" : rs
					.getString("id_Simati"));
			h.put("nosijil", rs.getString("no_sijil") == null ? "" : rs
					.getString("no_sijil"));
			h.put("noDaftar", rs.getString("no_Daftar") == null ? "" : rs
					.getString("no_Daftar"));
			h.put("Kod", rs.getString("kod") == null ? "" : rs
					.getString("kod"));

			h.put("alamat1", rs.getString("alamat_ha1") == null ? "" : rs
					.getString("alamat_ha1"));
			h.put("alamat2", rs.getString("alamat_ha2") == null ? "" : rs
					.getString("alamat_ha2"));
			h.put("alamat3", rs.getString("alamat_ha3") == null ? "" : rs
					.getString("alamat_ha3"));
			h.put("poskod", rs.getString("poskod") == null ? "" : rs
					.getString("poskod"));

			h.put("Keterangan", rs.getString("keterangan") == null ? ""
					: rs.getString("keterangan"));
			h.put("nilai_tarikhmohon",
					rs.getString("nilai_ha_tarikhmohon") == null ? "" : rs
							.getDouble("nilai_ha_tarikhmohon"));
			h.put("nilai_tarikhmati",
					rs.getString("nilai_ha_tarikhmati") == null ? "" : rs
							.getDouble("nilai_ha_tarikhmati"));
			
			h.put("nama_saham",
					rs.getString("nama_saham") == null ? "" : rs
							.getString("nama_saham"));
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

	public  Vector getDataHa(){
		  return listDataHa;
	  }
	
	private  Vector listOverallSum = new Vector();
	
	public  void setOverallSum(String id) throws Exception{
		
		/*	r.add("h.id_Jenisha",r.unquote("j.id_jenisha"));			
			r.add("h.id_Simati",r.unquote("ms.id_Simati"));
			r.add("h.id_Permohonansimati",id);

				
			sql = r.getSQLSelect("Tblppkha h, Tblppkrujjenisha j, Tblppkpermohonansimati ms");
			*/
		
		Db db = null;
		listOverallSum.clear();
		String sql = "Select sum(nilai) as nilai from ((Select h.nilai_ha_tarikhmohon as nilai from tblppkha h,Tblppkpermohonansimati ms where h.id_simati = ms.id_Simati and ms.id_Permohonansimati = "+ id +" ) " +
				     "union (Select k.nilai_hta_tarikhmohon as nilai from Tblppkhta k, Tblppkpermohonansimati ms where k.id_simati = ms.id_Simati and ms.id_Permohonansimati = "+ id +" )) X";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("nilaibesar", rs.getString("nilai")==null?"":Double.parseDouble(rs.getString("nilai")));
				listOverallSum.addElement(h);
				bil++;	
			}
		}finally {
			if(db != null) db.close();
		}
		}
	
	public  Vector getOverallSum(){
		  return listOverallSum;
	  }
	
private  Vector listOverallSumMati = new Vector();
	
	public  void setOverallSumMati(String id) throws Exception{
		
		
		/*	r.add("h.id_Jenisha",r.unquote("j.id_jenisha"));			
		r.add("h.id_Simati",r.unquote("ms.id_Simati"));
		r.add("h.id_Permohonansimati",id);

			
		sql = r.getSQLSelect("Tblppkha h, Tblppkrujjenisha j, Tblppkpermohonansimati ms");
		*/
		Db db = null;
		listOverallSumMati.clear();
		String sql = "Select sum(nilai) as nilaiMati from ((Select h.nilai_ha_tarikhmati as nilai from tblppkha h, Tblppkpermohonansimati ms where h.id_simati = ms.id_Simati and ms.id_Permohonansimati = "+ id +" ) " +
				     "union (Select k.nilai_hta_tarikhmati as nilai from Tblppkhta k, Tblppkpermohonansimati ms where k.id_simati = ms.id_Simati and ms.id_Permohonansimati = "+ id +" )) X";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("nilaibesarmati", rs.getString("nilaiMati")==null?"":Double.parseDouble(rs.getString("nilaiMati")));
				listOverallSumMati.addElement(h);
				bil++;	
			}
			//return list;
		}finally {
			if(db != null) db.close();
		}
		}
	
	public  Vector getOverallSumMati(){
		  return listOverallSumMati;
	  }
	
private  Vector listSelectedDataHa = new Vector();
	


	public  Vector getSelectedDataHa(){
		  return listSelectedDataHa;
	  }

	public void kemaskiniHa(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			String id1 = (String) data.get("id1");
			String id3 = (String) data.get("id3");
			int socJenisHartaAlih = Integer.parseInt((String) data
					.get("socJenisHartaAlih"));
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
			
			if((String)data.get("txtNilaiTarikhMohon")!="")
			{
			valuenilaimohon = Double.parseDouble((String) data
					.get("txtNilaiTarikhMohon"));
			}
			
			if((String)data.get("txtNilaiTarikhMati")!="")
			{	
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

			//System.out.println("@@@@@:" + socDaerahHtaam);
			String socNegeriHtaam = (String) data.get("socNegeriHtaam");

			db = new Db();
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
			// r.add("nilai_ha_tarikhmohon",txtNilaiTarikhMohon);
			// r.add("nilai_ha_tarikhmati",txtNilaiTarikhMati);
			
			
			
			if(txtNilaiTarikhMohon.equals(""))
			{	
			r.add("nilai_ha_tarikhmohon", "");
			}
			else
			{
			r.add("nilai_ha_tarikhmohon", valuenilaimohon);	
			}
			
			if(txtNilaiTarikhMati.equals(""))
			{
			r.add("nilai_ha_tarikhmati", "");
			}
			else
			{
			r.add("nilai_ha_tarikhmati", valuenilaimati);	
			}
			
			if (socJenisHartaAlih == 2 || socJenisHartaAlih == 3) {
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

			sql = r.getSQLUpdate("tblppkha");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public  void deleteDataHa(String id1, String id3) throws Exception{
	    Db db = null;
	    try
	    {
	       db = new Db();
		   Statement stmt = db.getStatement();
		  // SQLRenderer r = new SQLRenderer();
		    String sql = "delete from tblppkha where id_ha = "+ id3 +" and id_Permohonansimati = "+ id1 +"";
	       //sql = r.getSQLUpdate("tblppkha");
		   stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	//kira jumlah nilai mohon ha
private  Vector listSumDataHa = new Vector();
	
	public  void setSumDataHa(String id2) throws Exception{
		Db db = null;
		listSumDataHa.clear();
		String sql = "Select sum(nilai_ha_tarikhmohon) from Tblppkha where id_Simati = "+ id2 +"";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
						
			r.add("h.id_Ha");
			r.add("h.bil");
			r.add("h.id_Simati");
			r.add("h.id_Jenisha");
			r.add("h.id_negeri");
			r.add("h.id_daerah");
			r.add("h.jenama");
			r.add("h.no_Daftar");
			r.add("h.no_sijil");
			r.add("h.bil_unit");
			r.add("h.tarikh_harta");
			r.add("h.alamat_ha1");
			r.add("h.alamat_ha2");
			r.add("h.alamat_ha3");
			r.add("h.poskod");
			r.add("h.nilai_ha_tarikhmohon");
			r.add("h.nilai_ha_tarikhmati");
			r.add("h.ba_simati");
			r.add("h.bb_simati");
			r.add("h.catatan");
			r.add("h.nilai_seunit");
			r.add("h.id_masuk");
			r.add("h.tarikh_masuk");
			r.add("h.id_kemaskini");
			r.add("h.tarikh_kemaskini");
			r.add("h.id_db");
			r.add("j.kod");
			r.add("j.keterangan");

			r.add("h.id_Jenisha",r.unquote("j.id_jenisha"));
			r.add("h.id_Simati",id2);
				
			//sql = r.getSQLSelect("Tblppkha h, Tblppkrujjenisha j");
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("sum_nilaimohon", rs.getString("nilai_ha_tarikhmohon")==null?"":lebah.util.Util.formatDecimal(rs.getDouble("nilai_ha_tarikhmohon")));
				listSumDataHa.addElement(h);
				bil++;	
			}
			//return list;
		}finally {
			if(db != null) db.close();
		}
		}
	
	public  Vector getSumDataHa(){
		  return listSumDataHa;
	  }

	public  Vector getListtaraf() throws Exception {
		    Db db = null;
//		    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_Tarafkptg");
		      r.add("kod");
		      r.add("keterangan");
		      
		     
		      sql = r.getSQLSelect("Tblppkrujtarafkptg","kod");
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("id_Tarafkptg", rs.getInt("id_Tarafkptg"));
		     
		       
		        if(rs.getString("kod") == null){
		        	h.put("kod", "");
		        }else{
		        	h.put("kod", rs.getString("kod"));
		        }
		        
		        if(rs.getString("keterangan") == null){
		        	h.put("keterangan", "");
		        }else{
		        	h.put("keterangan", rs.getString("keterangan"));
		        }
		       /* 
		        if(rs.getString("description") == null){
		        	h.put("description", "");
		        }else{
		        	h.put("description", rs.getString("description"));
		        }
		       */
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	 
	 public  Vector getListsaudara() throws Exception {
		    Db db = null;
//		    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_Saudara");
		      r.add("kod");
		      r.add("keterangan");
		      r.add("jantina");
		      
		     
		   //   sql = r.getSQLSelect("Tblppkrujsaudara","kod");
		      
		      sql = "SELECT id_Saudara, kod, keterangan, jantina "  
					+" FROM Tblppkrujsaudara" 
					+" WHERE id_Saudara <> 24"
					+" AND id_Saudara <> 25"
				
					+" AND id_Saudara <> 29"
					+" AND id_Saudara <> 30"
					+" AND id_Saudara <> 34"
					+" AND id_Saudara <> 35"
					+"ORDER BY kod";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("id_Saudara", rs.getInt("id_Saudara"));
		     
		       
		        if(rs.getString("kod") == null){
		        	h.put("kod", "");
		        }else{
		        	h.put("kod", rs.getString("kod"));
		        }
		        
		        if(rs.getString("jantina") == null){
		        	h.put("jantina", "");
		        }else{
		        	h.put("jantina", rs.getString("jantina"));
		        }
		        
		        if(rs.getString("keterangan") == null){
		        	h.put("keterangan", "");
		        }else{
		        	h.put("keterangan", rs.getString("keterangan"));
		        }
		     
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	
	
	 public  Vector getListnegeri() throws Exception {
		    Db db = null;
//		    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_Negeri");
		      r.add("nama_Negeri");
		      r.add("kod_Negeri");
		      
		     
		      sql = r.getSQLSelect("Tblrujnegeri","kod_Negeri");
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("id_Negeri", rs.getInt("id_Negeri"));
		     
		       
		        if(rs.getString("nama_Negeri") == null){
		        	h.put("nama_Negeri", "");
		        }else{
		        	h.put("nama_Negeri", rs.getString("nama_Negeri"));
		        }
		        if(rs.getString("kod_Negeri") == null){
		        	h.put("kod_Negeri", "");
		        }else{
		        	h.put("kod_Negeri", rs.getString("kod_Negeri"));
		        }
		       /* 
		        if(rs.getString("description") == null){
		        	h.put("description", "");
		        }else{
		        	h.put("description", rs.getString("description"));
		        }
		       */
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	 
	 public  Vector getListbuktimati() throws Exception {
		    Db db = null;
//		    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_Buktimati");
		      r.add("keterangan");
		      r.add("kod");
		      
		     
		      sql = r.getSQLSelect("Tblppkrujbuktimati","kod");
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("id_Buktimati", rs.getInt("id_Buktimati"));
		     
		       
		        if(rs.getString("keterangan") == null){
		        	h.put("keterangan", "");
		        }else{
		        	h.put("keterangan", rs.getString("keterangan"));
		        }
		        
		        if(rs.getString("kod") == null){
		        	h.put("kod", "");
		        }else{
		        	h.put("kod", rs.getString("kod"));
		        }
		       /* 
		        if(rs.getString("description") == null){
		        	h.put("description", "");
		        }else{
		        	h.put("description", rs.getString("description"));
		        }
		       */
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }

	public  void add(Hashtable data) throws Exception {
		Db db = null;
			String sql = "";
			try
			{
				String noFail = (String)data.get("no_Fail"); 
				String tajukFail = (String)data.get("tajuk_Fail");
				
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("no_Fail",noFail);
				r.add("tajuk_Fail",tajukFail);
				sql = r.getSQLInsert("tblpfdfail");
				
				stmt.executeUpdate(sql);
			}finally {
				if(db != null) db.close();
			}
			
	}
	
private  Vector listChckId = new Vector();
	
public void checkData(String id) throws Exception {
	Db db = null;
	listChckId.clear();
	String sql = "Select count(p.id_pemohon) as ids from tblppkpemohon pm, tblppkpermohonan p where p.id_pemohon = pm.id_pemohon and p.id_permohonan = "
			+ id + "";
	//System.out.println("COUNT :" + sql);

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	try {
		db = new Db();
		Statement stmt = db.getStatement();
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		int bil = 1;

		while (rs.next()) {
			h = new Hashtable();
			h.put("count_id", rs.getString("ids") == null ? "" : rs
					.getString("ids"));

			listChckId.addElement(h);
			bil++;
		}
		// return list;
	} finally {
		if (db != null)
			db.close();
	}
}
	
	public  Vector getId(){
		  return listChckId;
	  }
	/*
	public  void updateDataNilai(int id, int id1) throws Exception{
	    Db db = null;
	    try
	    {
	       db = new Db();
		   Statement stmt = db.getStatement();
		   	String sql = "update tblppkpermohonan set " +
		   			"JUMLAH_HTA_TARIKHMOHON = (Select sum(a.NILAI_HTA_TARIKHMOHON) from tblppkhta a where a.id_Permohonansimati = "+ id1 +"), " +
		   			"JUMLAH_HTA_TARIKHMATI = (Select sum(a.NILAI_HTA_TARIKHMATI) from tblppkhta a where a.id_Permohonansimati = "+ id1 +"), " +
		   			"JUMLAH_HA_TARIKHMOHON = (Select sum(b.NILAI_HA_TARIKHMOHON) from tblppkha b where b.id_Permohonansimati = "+ id1 +")," +
		   			"JUMLAH_HA_TARIKHMATI = (Select sum(b.NILAI_HA_TARIKHMATI) from tblppkha b where b.id_Permohonansimati = "+ id1 +")," +
		   			"JUMLAH_HARTA_TARIKHMOHON = (Select sum(nilai) as nilai from ((Select h.nilai_ha_tarikhmohon as nilai from " +
		   			"tblppkha h where h.id_Permohonansimati = "+ id1 +" ) union (Select k.nilai_hta_tarikhmohon as nilai from " +
		   			"Tblppkhta k where k.id_Permohonansimati = "+ id1 +" )) X), JUMLAH_HARTA_TARIKHMATI = (Select sum(nilaib) as nilaib " +
		   			"from ((Select h.nilai_ha_tarikhmohon as nilaib from tblppkha h where h.id_Permohonansimati = "+ id1 +" ) union " +
		   			"(Select k.nilai_hta_tarikhmohon as nilaib from Tblppkhta k where k.id_Permohonansimati = "+ id1 +" )) Y) " +
		   			"where id_permohonan = "+ id +"";
		   			stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }


	public  void updateDataNilai(int id, int id1, String user) throws Exception{
	    Db db = null;
	    try
	    {
	    
	    	 db = new Db();
			   Statement stmt = db.getStatement();
			  
			
			   String sql = "update tblppkpermohonan set id_kemaskini = '"+user+"' , tarikh_kemaskini = sysdate, "  +
	   			"JUMLAH_HTA_TARIKHMOHON = (Select sum(a.NILAI_HTA_TARIKHMOHON) from tblppkhta a where a.id_Permohonansimati = "+ id1 +"), " +
	   			"JUMLAH_HTA_TARIKHMATI = (Select sum(a.NILAI_HTA_TARIKHMATI) from tblppkhta a where a.id_Permohonansimati = "+ id1 +"), " +
	   			"JUMLAH_HA_TARIKHMOHON = (Select sum(b.NILAI_HA_TARIKHMOHON) from tblppkha b where b.id_Permohonansimati = "+ id1 +")," +
	   			"JUMLAH_HA_TARIKHMATI = (Select sum(b.NILAI_HA_TARIKHMATI) from tblppkha b where b.id_Permohonansimati = "+ id1 +")," +
	   			"JUMLAH_HARTA_TARIKHMOHON = NVL(JUMLAH_HTA_TARIKHMOHON,0) + NVL(JUMLAH_HA_TARIKHMOHON,0), " +
	   	    	" JUMLAH_HARTA_TARIKHMATI = NVL(JUMLAH_HTA_TARIKHMATI,0) + NVL(JUMLAH_HA_TARIKHMATI,0) " +
	   			"where id_permohonan = "+ id +"";
			   stmt.executeUpdate(sql);
	   			
	   			System.out.println("UPDATE DATA NILAI :"+sql);
	   	
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	*/
	
	public void setSelectedDataHa(String id1, String id3) throws Exception {
		Db db = null;
		listSelectedDataHa.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			

			sql = "SELECT H.ID_HA, H.BIL, H.ID_SIMATI, H.ID_JENISHA, H.ID_NEGERI, H.ID_DAERAH, H.JENAMA, H.NO_DAFTAR, H.NO_SIJIL, "
					+ " H.BIL_UNIT, H.TARIKH_HARTA, H.ALAMAT_HA1, H.ALAMAT_HA2, H.ALAMAT_HA3, H.POSKOD, H.NILAI_HA_TARIKHMOHON, "
					+ " H.NILAI_HA_TARIKHMATI, H.BA_SIMATI, H.BB_SIMATI, H.CATATAN, H.NILAI_SEUNIT, H.ID_MASUK, H.TARIKH_MASUK, "
					+ " H.ID_KEMASKINI, H.TARIKH_KEMASKINI, H.ID_DB, J.KOD, J.KETERANGAN, H.ALAMAT_HA1, H.ALAMAT_HA2, "
					+ " H.ALAMAT_HA3, H.POSKOD, H.ID_DAERAH, H.ID_NEGERI, H.NAMA_SAHAM,H.BUTIRAN "
					+ " FROM TBLPPKHA H, TBLPPKRUJJENISHA J"
					+ " WHERE H.ID_JENISHA = J.ID_JENISHA   "
					+ " AND H.ID_HA = '" + id3 + "' ";

			//System.out.println("HARTA ALIH :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Ha", rs.getString("id_Ha") == null ? "" : rs
						.getString("id_Ha"));
				h.put("id_Jenisha", rs.getString("id_Jenisha") == null ? ""
						: rs.getString("id_Jenisha"));
				h.put("id_Simati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idnegeri", rs.getString("id_negeri") == null ? "" : rs
						.getString("id_negeri"));
				h.put("iddaerah", rs.getString("id_daerah") == null ? "" : rs
						.getString("id_daerah"));
				h.put("jenama", rs.getString("jenama") == null ? "" : rs
						.getString("jenama"));
				h.put("noDaftar", rs.getString("no_Daftar") == null ? "" : rs
						.getString("no_Daftar"));
				h.put("nosijil", rs.getString("no_sijil") == null ? "" : rs
						.getString("no_sijil"));
				h.put("bilunit", rs.getString("bil_unit") == null ? "" : rs
						.getString("bil_unit"));
				h.put("tarikhharta", rs.getString("tarikh_harta") == null ? ""
						: rs.getString("tarikh_harta"));

				h.put("alamat1", rs.getString("alamat_ha1") == null ? "" : rs
						.getString("alamat_ha1"));
				h.put("alamat2", rs.getString("alamat_ha2") == null ? "" : rs
						.getString("alamat_ha2"));
				h.put("alamat3", rs.getString("alamat_ha3") == null ? "" : rs
						.getString("alamat_ha3"));

				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));

				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));

				h.put("nilaiha_tarikhmohon", rs
						.getString("nilai_ha_tarikhmohon") == null ? "" : rs
						.getDouble("nilai_ha_tarikhmohon"));
				h.put("nilaiha_tarikhmati",
						rs.getString("nilai_ha_tarikhmati") == null ? "" : rs
								.getDouble("nilai_ha_tarikhmati"));
				h.put("basimati", rs.getString("ba_simati") == null ? "" : rs
						.getString("ba_simati"));
				h.put("bbsimati", rs.getString("bb_simati") == null ? "" : rs
						.getString("bb_simati"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("nilaiseunit", rs.getString("nilai_seunit") == null ? ""
						: rs.getDouble("nilai_seunit"));
				h.put("Kod", rs.getString("kod") == null ? "" : rs
						.getString("kod"));
				h.put("Keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("nama_saham", rs.getString("nama_saham") == null ? ""
						: rs.getString("nama_saham"));
				listSelectedDataHa.addElement(h);
				h.put("butiran", rs.getString("butiran") == null ? "" : rs
						.getString("butiran"));
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	
	
	public void updateDataNilai(String id, String id1, String user) throws Exception {
		Db db = null;
	//	Db db1 = null;
		try {

			db = new Db();
			Statement stmt = db.getStatement();

			String sql = "update tblppkpermohonan set id_kemaskini = '"
					+ user
					+ "' , tarikh_kemaskini = sysdate, "
					+ "JUMLAH_HTA_TARIKHMOHON = (Select sum(a.NILAI_HTA_TARIKHMOHON) from tblppkhta a where a.id_Permohonansimati = "
					+ id1
					+ "), "
					+ "JUMLAH_HTA_TARIKHMATI = (Select sum(a.NILAI_HTA_TARIKHMATI) from tblppkhta a where a.id_Permohonansimati = "
					+ id1
					+ "), "
					+ "JUMLAH_HA_TARIKHMOHON = (Select sum(b.NILAI_HA_TARIKHMOHON) from tblppkha b where b.id_Permohonansimati = "
					+ id1
					+ "),"
					+ "JUMLAH_HA_TARIKHMATI = (Select sum(b.NILAI_HA_TARIKHMATI) from tblppkha b where b.id_Permohonansimati = "
					+ id1
					+ "),"
					+ "JUMLAH_HARTA_TARIKHMOHON = NVL(JUMLAH_HTA_TARIKHMOHON,0) + NVL(JUMLAH_HA_TARIKHMOHON,0), "
					+ " JUMLAH_HARTA_TARIKHMATI = NVL(JUMLAH_HTA_TARIKHMATI,0) + NVL(JUMLAH_HA_TARIKHMATI,0) "
					+ "where id_permohonan = " + id + "";
			stmt.executeUpdate(sql);
			
			
			
		//	db1 = new Db();
			Statement stmtT = db.getStatement();

			String sqlT = "update tblppkpermohonan set id_kemaskini = '"
					+ user
					+ "' , tarikh_kemaskini = sysdate, "
					+ "JUM_HTA_TAMBAHAN_TKHMOHON = (Select sum(a.NILAI_HTA_TARIKHMOHON) from tblppkhta a, tblppkpermohonan p, tblppkpermohonansimati sm  where sm.id_Permohonan = p.id_Permohonan and a.id_Permohonansimati = sm.id_Permohonansimati and p.id_Permohonan = "
					+ id
					+ "), "
					+ "JUM_HTA_TAMBAHAN_TKHMATI = (Select sum(a.NILAI_HTA_TARIKHMATI) from tblppkhta a, tblppkpermohonan p, tblppkpermohonansimati sm  where sm.id_Permohonan = p.id_Permohonan and a.id_Permohonansimati = sm.id_Permohonansimati and p.id_Permohonan = "
					+ id
					+ "), "
					+ "JUM_HA_TAMBAHAN_TKHMOHON = (Select sum(a.NILAI_HA_TARIKHMOHON) from tblppkha a, tblppkpermohonan p, tblppkpermohonansimati sm  where sm.id_Permohonan = p.id_Permohonan and a.id_Permohonansimati = sm.id_Permohonansimati and p.id_Permohonan = "
					+ id
					+ "),"
					+ "JUM_HA_TAMBAHAN_TKHMATI = (Select sum(a.NILAI_HA_TARIKHMATI) from tblppkha a, tblppkpermohonan p, tblppkpermohonansimati sm  where sm.id_Permohonan = p.id_Permohonan and a.id_Permohonansimati = sm.id_Permohonansimati and p.id_Permohonan = "
					+ id
					+ "),"
					+ "JUM_HARTA_TAMBAHAN_TKHMOHON = NVL(JUM_HTA_TAMBAHAN_TKHMOHON,0) + NVL(JUM_HA_TAMBAHAN_TKHMOHON,0), "
					+ "JUM_HARTA_TAMBAHAN_TKHMATI = NVL(JUM_HTA_TAMBAHAN_TKHMATI,0) + NVL(JUM_HA_TAMBAHAN_TKHMATI,0) "
					+ "where id_permohonan = " + id + "";
			stmtT.executeUpdate(sqlT);
			
			
			
			

			System.out.println("UPDATE DATA NILAI :" + sql.toUpperCase());

		} finally {
			if (db != null)
				db.close();
		}
	}

	
	
	
	
	
	
	
private  Vector listGetId = new Vector();
	
public  void setGetId(String id) throws Exception{
	Db db = null;
	listGetId.clear();
	String sql = "Select a.id_pemohon, p.id_simati From tblppkpermohonansimati ms,tblppkpermohonan b, tblppkpemohon a, " +
			"tblppksimati p Where b.id_pemohon= a.id_pemohon and b.id_permohonan = ms.id_permohonan and p.id_simati = ms.id_simati " +
			"and b.id_permohonan = "+ id +"";
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	try {
		db = new Db();
		Statement stmt = db.getStatement();
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		int bil = 1;
		
		while (rs.next()){
			h = new Hashtable();
			h.put("idpemohon", rs.getString("id_pemohon")==null?"":rs.getString("id_pemohon"));
			h.put("idsimati", rs.getString("id_simati")==null?"":rs.getString("id_simati"));
			listGetId.addElement(h);
			bil++;	
		}
		//return list;
	}finally {
		if(db != null) db.close();
	}
	}
	
	public  Vector getIds(){
		  return listGetId;
	  }
	
	public  Vector getListDaerahbyNegeri(int idnegeri) throws Exception {
	    Db db = null;
//	    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Daerah");
	      r.add("nama_Daerah");
	      r.add("kod_Daerah");
	      r.add("id_Negeri");
	      
	      
	      r.add("id_Negeri",idnegeri);
	     
	      sql = r.getSQLSelect("Tblrujdaerah","kod_Daerah");
	      
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Daerah"));
	     
	       
	        if(rs.getString("nama_Daerah") == null){
	        	h.put("nama", "");
	        }else{
	        	h.put("nama", rs.getString("nama_Daerah"));
	        }
	        if(rs.getString("kod_Daerah") == null){
	        	h.put("kod", "");
	        }else{
	        	h.put("kod", rs.getString("kod_Daerah"));
	        }
	       
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	public  Vector getListMukimbyDaerah(int iddaerah) throws Exception {
	    Db db = null;
//	    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Mukim");
	      r.add("nama_Mukim");
	      r.add("kod_Mukim");
	      r.add("id_Daerah");
	      
	      r.add("id_Daerah",iddaerah);
	     
	      sql = r.getSQLSelect("Tblrujmukim","kod_Mukim");
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Mukim"));
	     
	       
	        if(rs.getString("nama_Mukim") == null){
	        	h.put("nama", "");
	        }else{
	        	h.put("nama", rs.getString("nama_Mukim"));
	        }
	        if(rs.getString("kod_Mukim") == null){
	        	h.put("kod", "");
	        }else{
	        	h.put("kod", rs.getString("kod_Mukim"));
	        }
	       
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	public  Vector getListDaerah() throws Exception {
	    Db db = null;
//	    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Daerah");
	      r.add("nama_Daerah");
	      r.add("kod_Daerah");
	      //r.add("id_Negeri");
	      
	      
	     
	     
	      sql = r.getSQLSelect("Tblrujdaerah","kod_Daerah");
	      
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Daerah"));
	     
	       
	        if(rs.getString("nama_Daerah") == null){
	        	h.put("nama", "");
	        }else{
	        	h.put("nama", rs.getString("nama_Daerah"));
	        }
	        if(rs.getString("kod_Daerah") == null){
	        	h.put("kod", "");
	        }else{
	        	h.put("kod", rs.getString("kod_Daerah"));
	        }
	       
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	public  Vector getListLuas() throws Exception {
	    Db db = null;
//	    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Luas");
	      r.add("keterangan");
	      r.add("kod_Luas");
	      //r.add("id_Negeri");
	      
	      
	     
	     
	      sql = r.getSQLSelect("Tblrujluas","kod_Luas");
	      
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Luas"));
	     
	       
	        if(rs.getString("keterangan") == null){
	        	h.put("nama", "");
	        }else{
	        	h.put("nama", rs.getString("keterangan"));
	        }
	        if(rs.getString("kod_Luas") == null){
	        	h.put("kod", "");
	        }else{
	        	h.put("kod", rs.getString("kod_Luas"));
	        }
	       
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public  Vector getListStatusPemilik(String status) throws Exception {
	    Db db = null;
//	    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Jenispb");
	      r.add("keterangan");
	      r.add("kod_Jenis_Pb");
	      r.add("jenis_Daftar_Pb");
	     
	      
	      r.add("jenis_Daftar_Pb",status);
	      
	     
	      sql = r.getSQLSelect("Tblrujjenispb","kod_Jenis_Pb");
	      
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Jenispb"));
	     
	       
	        if(rs.getString("keterangan") == null){
	        	h.put("keterangan", "");
	        }else{
	        	h.put("keterangan", rs.getString("keterangan"));
	        }
	        if(rs.getString("kod_Jenis_Pb") == null){
	        	h.put("kod", "");
	        }else{
	        	h.put("kod", rs.getString("kod_Jenis_Pb"));
	        }
	        if(rs.getString("jenis_Daftar_Pb") == null){
	        	h.put("status", "");
	        }else{
	        	h.put("status", rs.getString("jenis_Daftar_Pb"));
	        }
	       
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public  Vector getListJenisTanah() throws Exception {
	    Db db = null;
//	    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Jenistanah");
	      r.add("keterangan");
	      r.add("kod_Jenis_Tanah");
	     
	      sql = r.getSQLSelect("Tblrujjenistanah","kod_Jenis_Tanah");
	      
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Jenistanah"));
	     
	       
	        if(rs.getString("keterangan") == null){
	        	h.put("keterangan", "");
	        }else{
	        	h.put("keterangan", rs.getString("keterangan"));
	        }
	        if(rs.getString("kod_Jenis_Tanah") == null){
	        	h.put("kod", "");
	        }else{
	        	h.put("kod", rs.getString("kod_Jenis_Tanah"));
	        }
	       
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public  Vector getListMukim() throws Exception {
	    Db db = null;
//	    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Mukim");
	      r.add("nama_Mukim");
	      r.add("kod_Mukim");
	      r.add("id_Daerah");
	      
	  
	     
	      sql = r.getSQLSelect("Tblrujmukim","kod_Mukim");
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Mukim"));
	     
	       
	        if(rs.getString("nama_Mukim") == null){
	        	h.put("nama", "");
	        }else{
	        	h.put("nama", rs.getString("nama_Mukim"));
	        }
	        if(rs.getString("kod_Mukim") == null){
	        	h.put("kod", "");
	        }else{
	        	h.put("kod", rs.getString("kod_Mukim"));
	        }
	       
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
public static void simpanCatatanTolak(Hashtable data) throws Exception {
		
	    Db db = null;
	    String sql = "";
	   
	    try
	    {
	    
	    	db = new Db();
	    	 Statement stmt = db.getStatement();
	    	 
	    	 String id_user = (String)data.get("id_user");
	    	 String id_permohonan = (String)data.get("id_permohonan");
	    	 String txtCatatan = (String)data.get("txtCatatan");
	    	 String id_status = "8";
	    	 
	    	 SQLRenderer r = new SQLRenderer();
	    	 r.update("id_permohonan", id_permohonan);
	    	 
	    	 r.add("ALASAN_ONLINE_DIKEMBALIKAN", txtCatatan);	
	    	 r.add("id_status","150");
	    	 r.add("tarikh_kemaskini",r.unquote("sysdate"));
		     r.add("id_kemaskini",id_user);
		     r.add("FLAG_PERMOHONANDIKEMBALIKAN","1");
		     
	    	 sql = r.getSQLUpdate("Tblppkpermohonan");
	    	 myLogger.info("simpanCatatanTolak: "+ sql);
	    	 stmt.executeUpdate(sql);
	    	 
	    } catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}
	    finally {
	    if (db != null) db.close();
	    }
	  }//close simpanCatatanTolak
	
	public  Vector getListJenisHakMilik(String statushakmilik) throws Exception {
	    Db db = null;
//	    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
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
	      
	      r.add("status_Hakmilik",statushakmilik);
	     
	    //  sql = r.getSQLSelect("Tblrujjenishakmilik","kod_Jenis_Hakmilik");
	      
	      sql = "SELECT id_Jenishakmilik, keterangan, kod_Jenis_Hakmilik, simpanan, status_Hakmilik " +
			"FROM Tblrujjenishakmilik " +
		//	"--WHERE status_Hakmilik = 'Y'  " +
			"ORDER BY kod_Jenis_Hakmilik";
	      
	      
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Jenishakmilik"));
	     
	       
	        if(rs.getString("keterangan") == null){
	        	h.put("keterangan", "");
	        }else{
	        	h.put("keterangan", rs.getString("keterangan"));
	        }
	        if(rs.getString("kod_Jenis_Hakmilik") == null){
	        	h.put("kod", "");
	        }else{
	        	h.put("kod", rs.getString("kod_Jenis_Hakmilik"));
	        }
	        if(rs.getString("simpanan") == null){
	        	h.put("simpanan", "");
	        }else{
	        	h.put("simpanan", rs.getString("simpanan"));
	        }
	       
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public  Vector getListKategori() throws Exception {
	    Db db = null;
//	    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Kategori");
	      r.add("keterangan");
	      r.add("kod_Kategori");
	     
	      
	     
	      sql = r.getSQLSelect("Tblrujkategori","kod_Kategori");
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Kategori"));
	     
	       
	        if(rs.getString("keterangan") == null){
	        	h.put("keterangan", "");
	        }else{
	        	h.put("keterangan", rs.getString("keterangan"));
	        }
	        if(rs.getString("kod_Kategori") == null){
	        	h.put("kod", "");
	        }else{
	        	h.put("kod", rs.getString("kod_Kategori"));
	        }
	        
	       
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	private  Vector listGetUserId = new Vector();
	
	public  void setGetUserId(String userid) throws Exception {
	    Db db = null;
	    String idUser = userid;
	    String sql = "Select d.id_daerah,d.kod_daerah,d.id_negeri,r.id_pejabatjkptg,n.kod_negeri From users_internal u, tblrujdaerah d, tblrujpejabaturusan r, tblrujnegeri n " +
	    		     "Where u.id_negeri = d.id_negeri and d.id_daerah = r.id_daerah and d.id_negeri = r.id_negeri and r.id_negeri = n.id_negeri and u.user_id = "+ idUser +"";
	    //String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Kategori");
	      r.add("keterangan");
	      r.add("kod_Kategori");
	     
	      //sql = r.getSQLSelect("Tblrujkategori","kod_Kategori");
	      //
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
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public  Vector getUserIds(){
		  return listGetUserId;
	  }
	
	public  Vector getDaerahByNegeriUser(String userid)throws Exception {
	    Db db = null;
	   // String sql = "Select d.id_daerah,d.kod_daerah,d.nama_daerah,d.id_negeri From users_internal u, tblrujdaerah d, tblrujpejabaturusan r  Where u.id_negeri = d.id_negeri and d.id_daerah = r.id_daerah and d.id_negeri = r.id_negeri and u.user_id = "+ userid +" group by d.id_daerah,d.kod_daerah,d.nama_daerah,d.id_negeri order by d.id_daerah";
	    //String sql = "Select d.id_daerah,d.kod_daerah,d.nama_daerah,d.id_negeri From users_internal u, tblrujdaerah d, tblrujpejabaturusan r  Where u.id_negeri = d.id_negeri and d.id_daerah = r.id_daerah and d.id_negeri = r.id_negeri and u.user_id = 14 group by d.id_daerah,d.kod_daerah,d.nama_daerah,d.id_negeri order by d.id_daerah";
		  
	    
	    String sql = "select id_daerah,kod_daerah,nama_daerah from tblRujDaerah where id_daerah in ( select distinct u.id_daerahurus from" 
	     +" TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+userid+"' ";
		 
		  sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
					
		 sql += " )" 
	    
	     +" ORDER BY kod_daerah";
	    
	    System.out.println("@@@@"+sql);
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("id_jenisnopb");
	      r.add("keterangan");
	
	      //sql = r.getSQLSelect(sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector listDaerahByUser = new Vector();
	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("iddaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
	    	  h.put("koddaerah", rs.getString("kod_daerah")==null?"":rs.getString("kod_daerah"));
	    	  h.put("namadaerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
	    	  
		    	  listDaerahByUser.addElement(h);
	      }
	      return listDaerahByUser;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	
	public  Vector getDaerahUser(String userid)throws Exception {
	    Db db = null;
	    String sql = "Select d.id_daerah,d.kod_daerah,d.nama_daerah,d.id_negeri,r.id_pejabat From users_internal u, tblrujdaerah d, tblrujpejabaturusan r  Where u.id_negeri = d.id_negeri and d.id_daerah = r.id_daerah and d.id_negeri = r.id_negeri and u.user_id = "+ userid +" group by d.id_daerah,d.kod_daerah,d.nama_daerah,d.id_negeri,r.id_pejabat order by d.id_daerah";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("id_jenisnopb");
	      r.add("keterangan");
	
	      //sql = r.getSQLSelect(sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector listDaerahUser = new Vector();
	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("iddaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
	    	  h.put("namadaerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
	    	  h.put("idpejabat", rs.getString("id_pejabat")==null?"":rs.getString("id_pejabat"));
	    	  listDaerahUser.addElement(h);
	      }
	      return listDaerahUser;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	//kira jumlah nilai mohon hta
	private  Vector listSumDataHta = new Vector();
	private  Vector listSumDataHtaDulu = new Vector();
	
	
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
					+ "SM.ID_SIMATI, MS.ID_PERMOHONAN, P.ID_PERMOHONAN," +
					" HTA.ALAMAT_HTA1,HTA.ALAMAT_HTA2,HTA.ALAMAT_HTA3, HTA.POSKOD_HTA ," +
					"HTA.JENIS_KEPENTINGAN,HTA.FLAG_KATEGORI_HTA,HTA.NO_ROH,HTA.JENIS_HTA,RUJ.KOD_JENIS_HAKMILIK,RUJ.KETERANGAN  "+				
					"  FROM TBLPPKPERMOHONAN P, TBLRUJJENISHAKMILIK RUJ, "
					+ "TBLPPKHTA HTA, TBLRUJNEGERI N, TBLRUJDAERAH D, TBLRUJMUKIM M, TBLPPKSIMATI SM, "
					+ "TBLPPKPERMOHONANSIMATI MS WHERE HTA.ID_NEGERI = N.ID_NEGERI(+)  "
					+ "AND HTA.ID_DAERAH = D.ID_DAERAH(+)  AND HTA.ID_MUKIM = M.ID_MUKIM(+)  "
					+" AND HTA.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) "
					+ "AND HTA.ID_SIMATI = SM.ID_SIMATI(+) "
					+ "AND MS.ID_PERMOHONANSIMATI = HTA.ID_PERMOHONANSIMATI  "
					+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN AND SM.ID_SIMATI = MS.ID_SIMATI  "
					+ "AND MS.ID_PERMOHONANSIMATI = '" + id2 + "' ";

			//System.out.println("SQL NILAI HTA" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("nama_Daerah", rs.getString("nama_Daerah") == null ? ""
						: rs.getString("nama_Daerah"));
				h.put("nama_Negeri", rs.getString("nama_Negeri") == null ? ""
						: rs.getString("nama_Negeri"));
				h.put("nama_Mukim", rs.getString("nama_Mukim") == null ? ""
						: rs.getString("nama_Mukim"));
				h.put("no_Pt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("no_Perjanjian", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("nilai_tarikhmohon", rs
						.getString("nilai_hta_tarikhmohon") == null ? "" : rs
						.getDouble("nilai_hta_tarikhmohon"));
				h.put("nilai_tarikhmati",
						rs.getString("nilai_hta_tarikhmati") == null ? "" : rs
								.getDouble("nilai_hta_tarikhmati"));
				
				h.put("noroh", rs.getString("no_Roh")==null?"":rs.getString("no_Roh"));
				h.put("alamat1", rs.getString("ALAMAT_HTA1")==null?"":rs.getString("ALAMAT_HTA1"));
				h.put("alamat2", rs.getString("ALAMAT_HTA2")==null?"":rs.getString("ALAMAT_HTA2"));
				h.put("alamat3", rs.getString("ALAMAT_HTA3")==null?"":rs.getString("ALAMAT_HTA3"));
				h.put("poskod", rs.getString("POSKOD_HTA")==null?"":rs.getString("POSKOD_HTA"));
				h.put("jenis_penting", rs.getString("JENIS_KEPENTINGAN")==null?"":rs.getString("JENIS_KEPENTINGAN"));
				h.put("ketegori_hta", rs.getString("FLAG_KATEGORI_HTA")==null?"":rs.getString("FLAG_KATEGORI_HTA"));
				h.put("jenis_hta", rs.getString("JENIS_HTA")==null?"":rs.getString("JENIS_HTA"));
				h.put("kod_hakmilik", rs.getString("KOD_JENIS_HAKMILIK")==null?"":rs.getString("KOD_JENIS_HAKMILIK"));
				h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				
				
				listSumDataHta.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}
/*
	public void setSumDataHtaDulu(int id2) throws Exception {
		Db db = null;
		listSumDataHtaDulu.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			sql="SELECT HTA.ID_HTA, HTA.ID_NEGERI, HTA.ID_DAERAH, HTA.ID_MUKIM, HTA.NO_HAKMILIK, " +
					"HTA.NO_PT, HTA.BA_SIMATI, HTA.BB_SIMATI, HTA.NILAI_HTA_TARIKHMOHON, " +
					"HTA.NILAI_HTA_TARIKHMATI, N.NAMA_NEGERI, D.NAMA_DAERAH, M.NAMA_MUKIM, " +
					"SM.ID_SIMATI, MS.ID_PERMOHONAN, P.ID_PERMOHONAN,  " +
					" HTA.ALAMAT_HTA1,HTA.ALAMAT_HTA2,HTA.ALAMAT_HTA3, HTA.POSKOD_HTA ," +
					"HTA.JENIS_KEPENTINGAN,HTA.FLAG_KATEGORI_HTA,HTA.NO_ROH,HTA.JENIS_HTA,RUJ.KOD_JENIS_HAKMILIK,RUJ.KETERANGAN  "+				
					" FROM TBLPPKPERMOHONAN P,  TBLRUJJENISHAKMILIK RUJ," +
					"TBLPPKHTA HTA, TBLRUJNEGERI N, TBLRUJDAERAH D, TBLRUJMUKIM M, TBLPPKSIMATI SM, " +
					"TBLPPKPERMOHONANSIMATI MS, TBLPPKSIMATI S,  TBLPPKPERMOHONAN P1," +
					"TBLPPKPERMOHONANSIMATI MS1  " +
					"WHERE HTA.ID_NEGERI = N.ID_NEGERI(+) AND HTA.ID_SIMATI = S.ID_SIMATI  " +
					"AND HTA.ID_DAERAH = D.ID_DAERAH(+)  AND HTA.ID_MUKIM = M.ID_MUKIM(+)  " +
					"AND HTA.ID_SIMATI = SM.ID_SIMATI(+) " +
					" AND HTA.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) "+
					"AND MS.ID_PERMOHONANSIMATI <> HTA.ID_PERMOHONANSIMATI  " +
					"AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN AND SM.ID_SIMATI = MS.ID_SIMATI  " +
					"AND MS.ID_PERMOHONANSIMATI = '" + id2 + "' " +
					"AND HTA.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   " +
					"AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  " +
					"AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  " +
					"AND P1.NO_SUBJAKET < P.NO_SUBJAKET ORDER BY HTA.ID_HTA DESC ";
			
			//System.out.println("SQL NILAI HTA" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("nama_Daerah", rs.getString("nama_Daerah") == null ? ""
						: rs.getString("nama_Daerah"));
				h.put("nama_Negeri", rs.getString("nama_Negeri") == null ? ""
						: rs.getString("nama_Negeri"));
				h.put("nama_Mukim", rs.getString("nama_Mukim") == null ? ""
						: rs.getString("nama_Mukim"));
				h.put("no_Pt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("no_Perjanjian", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("nilai_tarikhmohon", rs
						.getString("nilai_hta_tarikhmohon") == null ? "" : rs
						.getDouble("nilai_hta_tarikhmohon"));
				h.put("nilai_tarikhmati",
						rs.getString("nilai_hta_tarikhmati") == null ? "" : rs
								.getDouble("nilai_hta_tarikhmati"));
				
				
				h.put("noroh", rs.getString("no_Roh")==null?"":rs.getString("no_Roh"));
				h.put("alamat1", rs.getString("ALAMAT_HTA1")==null?"":rs.getString("ALAMAT_HTA1"));
				h.put("alamat2", rs.getString("ALAMAT_HTA2")==null?"":rs.getString("ALAMAT_HTA2"));
				h.put("alamat3", rs.getString("ALAMAT_HTA3")==null?"":rs.getString("ALAMAT_HTA3"));
				h.put("poskod", rs.getString("POSKOD_HTA")==null?"":rs.getString("POSKOD_HTA"));
				h.put("jenis_penting", rs.getString("JENIS_KEPENTINGAN")==null?"":rs.getString("JENIS_KEPENTINGAN"));
				h.put("ketegori_hta", rs.getString("FLAG_KATEGORI_HTA")==null?"":rs.getString("FLAG_KATEGORI_HTA"));
				h.put("jenis_hta", rs.getString("JENIS_HTA")==null?"":rs.getString("JENIS_HTA"));
				h.put("kod_hakmilik", rs.getString("KOD_JENIS_HAKMILIK")==null?"":rs.getString("KOD_JENIS_HAKMILIK"));
				h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				
				
				
				
				listSumDataHtaDulu.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}
	*/
	
	
	public void setDataHaDulu(String id) throws Exception {
		Db db = null;
		listDataHadulu.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql="SELECT H.ID_HA, H.BIL, H.ID_SIMATI, H.ID_JENISHA, H.NO_DAFTAR, H.NILAI_HA_TARIKHMOHON, " +
					"H.NILAI_HA_TARIKHMATI, H.NO_SIJIL, J.KOD, J.KETERANGAN, H.ALAMAT_HA1, H.ALAMAT_HA2, " +
					"H.ALAMAT_HA3,H.POSKOD,H.BUTIRAN,H.NAMA_SAHAM,H.JENAMA,H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI  " +
					"FROM TBLPPKHA H, TBLPPKRUJJENISHA J, TBLPPKPERMOHONANSIMATI MS," +
					"TBLPPKSIMATI S, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI MS1 " +
					"WHERE H.ID_SIMATI = S.ID_SIMATI " +
					"AND H.ID_JENISHA = J.ID_JENISHA  " +
					"AND H.ID_SIMATI = MS.ID_SIMATI  " +
					"AND H.ID_PERMOHONANSIMATI <> MS.ID_PERMOHONANSIMATI " +
					"AND MS.ID_PERMOHONANSIMATI = '"+id+"' " +
					"AND H.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   " +
					"AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  " +
					"AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  " +
					"AND P1.NO_SUBJAKET < P.NO_SUBJAKET  " +
					"AND S.ID_SIMATI = MS.ID_SIMATI " +
					"ORDER BY H.ID_HA DESC"; 


			//System.out.println("WWWW:::" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Ha", rs.getString("id_Ha") == null ? "" : rs
						.getString("id_Ha"));
				h.put("id_Jenisha", rs.getString("id_Jenisha") == null ? ""
						: rs.getString("id_Jenisha"));
				h.put("id_Simati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("nosijil", rs.getString("no_sijil") == null ? "" : rs
						.getString("no_sijil"));
				h.put("noDaftar", rs.getString("no_Daftar") == null ? "" : rs
						.getString("no_Daftar"));
				h.put("Kod", rs.getString("kod") == null ? "" : rs
						.getString("kod"));

				h.put("alamat1", rs.getString("alamat_ha1") == null ? "" : rs
						.getString("alamat_ha1"));
				h.put("alamat2", rs.getString("alamat_ha2") == null ? "" : rs
						.getString("alamat_ha2"));
				h.put("alamat3", rs.getString("alamat_ha3") == null ? "" : rs
						.getString("alamat_ha3"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));

				h.put("Keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("nilai_tarikhmohon",
						rs.getString("nilai_ha_tarikhmohon") == null ? "" : rs
								.getDouble("nilai_ha_tarikhmohon"));
				h.put("nilai_tarikhmati",
						rs.getString("nilai_ha_tarikhmati") == null ? "" : rs
								.getDouble("nilai_ha_tarikhmati"));
				
				h.put("nilai_tm", rs.getString("NILAI_HA_TARIKHMOHON") == null ? "" : rs
						.getDouble("NILAI_HA_TARIKHMOHON"));
				
				h.put("nama_saham",
						rs.getString("nama_saham") == null ? "" : rs
								.getString("nama_saham"));
				h.put("jenama",
						rs.getString("jenama") == null ? "" : rs
								.getString("jenama"));
				
				h.put("butiran",
						rs.getString("butiran") == null ? "" : rs
								.getString("butiran"));
				
				h.put("flag_pt", rs.getString("FLAG_PT")==null?"":rs.getString("FLAG_PT"));
				h.put("flag_pa", rs.getString("FLAG_PA")==null?"":rs.getString("FLAG_PA"));
				h.put("flag_s", rs.getString("FLAG_PA")==null?"":rs.getString("FLAG_PA"));
				
				
				
				listDataHadulu.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	public Vector getDataHaDulu() {
		return listDataHadulu;
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
			
		
			sql="SELECT HTA.ID_HTA, HTA.ID_NEGERI, HTA.ID_DAERAH, HTA.ID_MUKIM, HTA.NO_HAKMILIK, " +
					"HTA.NO_PT, HTA.BA_SIMATI, HTA.BB_SIMATI, HTA.NILAI_HTA_TARIKHMOHON, " +
					"HTA.NILAI_HTA_TARIKHMATI, N.NAMA_NEGERI, D.NAMA_DAERAH, M.NAMA_MUKIM, " +
					"SM.ID_SIMATI, MS.ID_PERMOHONAN, P.ID_PERMOHONAN,  " +
					" HTA.ALAMAT_HTA1,HTA.ALAMAT_HTA2,HTA.ALAMAT_HTA3, HTA.POSKOD_HTA ," +
					"HTA.JENIS_KEPENTINGAN,HTA.FLAG_KATEGORI_HTA,HTA.NO_ROH,HTA.JENIS_HTA,RUJ.KOD_JENIS_HAKMILIK,RUJ.KETERANGAN,HTA.FLAG_PA, HTA.FLAG_PT, HTA.FLAG_SELESAI  "+				
					" FROM TBLPPKPERMOHONAN P, " +
					"TBLPPKHTA HTA, TBLRUJNEGERI N, TBLRUJDAERAH D, TBLRUJMUKIM M, TBLPPKSIMATI SM, " +
					"TBLPPKPERMOHONANSIMATI MS,  TBLRUJJENISHAKMILIK RUJ,TBLPPKSIMATI S,  TBLPPKPERMOHONAN P1," +
					"TBLPPKPERMOHONANSIMATI MS1  " +
					"WHERE HTA.ID_NEGERI = N.ID_NEGERI(+) AND HTA.ID_SIMATI = S.ID_SIMATI  " +
					"AND HTA.ID_DAERAH = D.ID_DAERAH(+)  AND HTA.ID_MUKIM = M.ID_MUKIM(+)  " +
					"AND HTA.ID_SIMATI = SM.ID_SIMATI(+) " +
					" AND HTA.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) "+
					"AND MS.ID_PERMOHONANSIMATI <> HTA.ID_PERMOHONANSIMATI  " +
					"AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN AND SM.ID_SIMATI = MS.ID_SIMATI  " +
					"AND MS.ID_PERMOHONANSIMATI = '" + id2 + "' " +
					"AND HTA.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   " +
					"AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  " +
					"AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  " +
					"AND P1.NO_SUBJAKET < P.NO_SUBJAKET ORDER BY HTA.ID_HTA DESC ";
			
			//System.out.println("SQL NILAI HTA" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("nama_Daerah", rs.getString("nama_Daerah") == null ? ""
						: rs.getString("nama_Daerah"));
				h.put("nama_Negeri", rs.getString("nama_Negeri") == null ? ""
						: rs.getString("nama_Negeri"));
				h.put("nama_Mukim", rs.getString("nama_Mukim") == null ? ""
						: rs.getString("nama_Mukim"));
				h.put("no_Pt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("no_Perjanjian", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("nilai_tarikhmohon", rs
						.getString("nilai_hta_tarikhmohon") == null ? "" : rs
						.getDouble("nilai_hta_tarikhmohon"));
				h.put("nilai_tarikhmati",
						rs.getString("nilai_hta_tarikhmati") == null ? "" : rs
								.getDouble("nilai_hta_tarikhmati"));
				
				
				h.put("noroh", rs.getString("no_Roh")==null?"":rs.getString("no_Roh"));
				h.put("alamat1", rs.getString("ALAMAT_HTA1")==null?"":rs.getString("ALAMAT_HTA1"));
				h.put("alamat2", rs.getString("ALAMAT_HTA2")==null?"":rs.getString("ALAMAT_HTA2"));
				h.put("alamat3", rs.getString("ALAMAT_HTA3")==null?"":rs.getString("ALAMAT_HTA3"));
				h.put("poskod", rs.getString("POSKOD_HTA")==null?"":rs.getString("POSKOD_HTA"));
				h.put("jenis_penting", rs.getString("JENIS_KEPENTINGAN")==null?"":rs.getString("JENIS_KEPENTINGAN"));
				h.put("ketegori_hta", rs.getString("FLAG_KATEGORI_HTA")==null?"":rs.getString("FLAG_KATEGORI_HTA"));
				h.put("jenis_hta", rs.getString("JENIS_HTA")==null?"":rs.getString("JENIS_HTA"));
				h.put("kod_hakmilik", rs.getString("KOD_JENIS_HAKMILIK")==null?"":rs.getString("KOD_JENIS_HAKMILIK"));
				h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				
				h.put("flag_pt", rs.getString("FLAG_PT")==null?"":rs.getString("FLAG_PT"));
				h.put("flag_pa", rs.getString("FLAG_PA")==null?"":rs.getString("FLAG_PA"));
				h.put("flag_s", rs.getString("FLAG_PA")==null?"":rs.getString("FLAG_PA"));
			
				
				
				
				listSumDataHtaDulu.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	

		public  Vector getSumDataHta(){
			  return listSumDataHta;
		  }
		public  Vector getSumDataHtaDulu(){
			  return listSumDataHtaDulu;
		  }
	
		private  Vector listOverallSumHta = new Vector();
		
		public  void setOverallSumHta(int id) throws Exception{
			Db db = null;
			listOverallSumHta.clear();
			String sql = "Select sum(nilai_hta_tarikhmohon) from Tblppkhta where id_Permohonansimati = "+ id +"";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("nilaibesarmohonhta", rs.getString(1)==null?"":Double.parseDouble(rs.getString(1)));
					listOverallSumHta.addElement(h);
					bil++;	
				}
				//return list;
			}finally {
				if(db != null) db.close();
			}
			}
		
		public  Vector getOverallSumHta(){
			  return listOverallSumHta;
		  }
		
		 private  Vector listFail = new Vector();
			//private  SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
			 public  Vector getDataFail(){
				return listFail;	 
			  }
			 
			 public  void setDataFail(String id) throws Exception{
					Db db = null;
					listFail.clear();
					String sql = "";
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					
					try{
						db = new Db();
						Statement stmt = db.getStatement();
						SQLRenderer r = new SQLRenderer();
						
				        sql = "SELECT sub.id_Suburusanstatus, sub.id_Suburusanstatusfail, sub.aktif, sub.id_permohonan, f.no_fail"+
				        " FROM Tblrujsuburusanstatusfail sub, Tblppkpermohonan p, Tblpfdfail f"+
				        " WHERE p.id_Permohonan = sub.id_Permohonan"+
				        " AND p.id_fail = f.id_fail"+
				        " AND sub.aktif = 1"+ 
				      //  " AND (sub.id_Suburusanstatus = 340 OR sub.id_Suburusanstatus = 353) "+
				        " AND sub.id_permohonan = '"+id+"'"+
				        "";
				        	
						System.out.println("SQLXXXXXX FAIL 12"+sql);
						
						
						ResultSet rs = stmt.executeQuery(sql);
					    Hashtable h;
						
					      while (rs.next()) {
					    	  h = new Hashtable();			    	  
					 
					    	h.put("id_Suburusanstatus", rs.getString("id_Suburusanstatus")==null?"":rs.getString("id_Suburusanstatus"));
					    	h.put("id_Suburusanstatusfail", rs.getString("id_Suburusanstatusfail")==null?"":rs.getString("id_Suburusanstatusfail"));
					    	h.put("id_Permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
					    	h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
					    	 
					    	
					    	  
					    	  System.out.println("sql data-->"+h);
					    	  listFail.addElement(h);
					}      
				}
				finally {
					if(db != null)db.close();
					}
			}
		
}	 	
