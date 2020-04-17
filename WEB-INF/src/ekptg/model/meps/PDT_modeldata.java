package ekptg.model.meps;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.EkptgCache;
import ekptg.helpers.Utils;

public class PDT_modeldata extends EkptgCache implements Serializable {
	static Logger myLogger = Logger.getLogger(PDT_modeldata.class);
	
	
	// VECTOR
		public Vector getKeseluruhanTRM = null;
		//public Vector getKeseluruhanTRMDaerah = null;
		Vector combine = new Vector();
		Vector combineS = new Vector();
		
		public Vector getAbbrev = null;
		
		private String SQL;

		public String getSQL() {
			return SQL;
		}

		public void setSQL(String sql) {
			SQL = sql;
		}
		
		public Vector getLaporanTRM(String id_tahunDari) throws Exception {
			Db db = null;
			String sql = "";
			try {
				getKeseluruhanTRM = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				sql = "  SELECT   NVL(LUAS,0)LUAS, KOD_NEGERI, A.ID_NEGERI, A.NAMA_NEGERI, X ";
				sql += " FROM  (SELECT ID_NEGERI, NAMA_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI NOT IN (17, 0, 4, 7, 12, 13, 15, 16)) A   LEFT JOIN ";
				sql += " (SELECT R.LUAS, N.KOD_NEGERI, N.ID_NEGERI, N.NAMA_NEGERI, N.ABBREV AS X ";
				sql += " FROM TBLPDTTRMREPTAHUNNEGERI R, TBLRUJNEGERI N ";
				sql += " WHERE R.ID_NEGERI = N.ID_NEGERI  ";
				
				/*sql = " SELECT R.LUAS, N.KOD_NEGERI, N.ID_NEGERI, N.NAMA_NEGERI, N.ABBREV AS X ";
				sql += " FROM TBLPDTTRMREPTAHUNNEGERI R, TBLRUJNEGERI N ";
				sql += " WHERE R.ID_NEGERI = N.ID_NEGERI "; */
				//AND R.TAHUN = '2017'
				
				/*sql = " SELECT   C.KOD_NEGERI, C.ID_NEGERI, C.NAMA_NEGERI, C.ABBREV AS x, nvl(R.LUAS,0) AS LUAS ";
				sql += " FROM   TBLRUJNEGERI C, TBLPDTTRMREPTAHUNNEGERI R ";
				sql += " WHERE   R.ID_NEGERI = C.ID_NEGERI ";*/
				

				// TAHUN
				if (id_tahunDari != null) {
					if (!id_tahunDari.trim().equals("")) {
						sql = sql + " AND R.TAHUN = '" + id_tahunDari + "' ";					
					}
				} 


				setSQL(sql);

				myLogger.info("LAPORAN KELUASAN KAWASAN TANAH RIZAB MELAYU ::::: " + sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;
				
				while (rs.next()) {
					h = new Hashtable();
					h.put("bil", bil);
					//h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
					h.put("nama_negeri", Utils.NiceStateName(rs.getString("NAMA_NEGERI")));
					h.put("ID_NEGERI", rs.getString("ID_NEGERI"));
					h.put("LUAS", rs.getString("LUAS"));
					getKeseluruhanTRM.addElement(h);
					bil++;
				}
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
			} finally {
				if (db != null)
					db.close();
			}
			return getKeseluruhanTRM;
		}
		
		public Vector getAbbrev() throws Exception {
			Db db = null;
			String sql = "";
			try {
				getAbbrev = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql = " SELECT distinct(RK.ABBREV) AS ABBREV,RK.ID_NEGERI, RK.NAMA_NEGERI AS NEGERI "
						+ " FROM TBLRUJNEGERI RK WHERE RK.ABBREV NOT IN('PEL', 'TM', 'MLK', 'PNG', 'SBH', 'SRW', 'LBN', 'PJY')" + " ORDER BY RK.ID_NEGERI" + "";
				// myLog.info(sql);
				setSQL(sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("abbrev", Utils.isNull(rs.getString("ABBREV")));
					h.put("negeri", Utils.isNull(rs.getString("NEGERI")));
					getAbbrev.addElement(h);
				}
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
			} finally {
				if (db != null)
					db.close();
			}
			return getAbbrev;

		}
		
		public Vector getListTotalKeseluruhanFailAbbrev(String id_tahunDari) throws Exception {
			Db db = null;
			String sql = "";
			combine.clear();
			try {
				getKeseluruhanTRM = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				sql = "  SELECT   NVL(LUAS,0) LUAS, KOD_NEGERI, A.ID_NEGERI, A.NAMA_NEGERI, X ";
				sql += " FROM  (SELECT ID_NEGERI, NAMA_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI NOT IN (17, 0, 4, 7, 12, 13, 15, 16)) A   LEFT JOIN ";
				sql += " (SELECT R.LUAS, N.KOD_NEGERI, N.ID_NEGERI, N.NAMA_NEGERI, N.ABBREV AS X ";
				sql += " FROM TBLPDTTRMREPTAHUNNEGERI R, TBLRUJNEGERI N ";
				sql += " WHERE R.ID_NEGERI = N.ID_NEGERI  "; 
				
				/*sql = " SELECT   C.KOD_NEGERI, C.ID_NEGERI, C.NAMA_NEGERI, C.ABBREV AS x, nvl(R.LUAS,0) AS LUAS ";
				sql += " FROM   TBLRUJNEGERI C, TBLPDTTRMREPTAHUNNEGERI R ";
				sql += " WHERE   R.ID_NEGERI = C.ID_NEGERI ";*/
				

				// TAHUN
				if (id_tahunDari != null) {
					if (!id_tahunDari.trim().equals("")) {
						sql = sql + " AND R.TAHUN = '" + id_tahunDari + "' ";
						sql += "  )B ON B.NAMA_NEGERI=A.NAMA_NEGERI ";
					}
				} 

				setSQL(sql);

				myLogger.info("LAPORAN KELUASAN KAWASAN TANAH RIZAB MELAYU ::: " + sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;

				Vector newKeseluruhanTRM = new Vector();
				for (int x = 1; x <= 10; x++) {
					h = new Hashtable();
					h.put("bil", x);
					//h.put("jumlah_permohonan", "0");
					String abbrev = "";
					abbrev = abbrev(x);
					h.put("abbrev", abbrev);
					String idNegeri = "";
					idNegeri = idNegeri(x);
					h.put("ID_NEGERI", idNegeri);
					String namaNegeri = "";
					namaNegeri = namaNegeri(x);
					h.put("nama_negeri", namaNegeri);
					String LUAS = "";
					h.put("LUAS", new Double(0));
					h.put("luasf", LUAS);
					newKeseluruhanTRM.addElement(h);
				}

				while (rs.next()) {
					h = new Hashtable();
					h.put("bil", bil);
					//h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
					h.put("abbrev", Utils.NiceStateName(rs.getString("NAMA_NEGERI")));
					h.put("ID_NEGERI", rs.getString("ID_NEGERI"));
					h.put("nama_negeri", rs.getString("NAMA_NEGERI"));
					h.put("LUAS", rs.getDouble("LUAS"));
					h.put("luasf", rs.getString("LUAS")==null ? "0.00000" :ekptg.helpers.Utils.formatLuas(rs.getDouble("LUAS")));
					myLogger.info(rs.getDouble("LUAS"));
					getKeseluruhanTRM.addElement(h);
					bil++;
				}

				for (Object listFull : newKeseluruhanTRM) {
					Hashtable hListFull = (Hashtable) listFull;
					for (Object listSelected : getKeseluruhanTRM) {
						Hashtable hListSelected = (Hashtable) listSelected;
						if (hListFull.get("ID_NEGERI").equals(hListSelected.get("ID_NEGERI"))) {
							//hListFull.put("jumlah_permohonan", hListSelected.get("jumlah_permohonan"));
							hListFull.put("abbrev", hListSelected.get("abbrev"));
							hListFull.put("ID_NEGERI", hListSelected.get("ID_NEGERI"));
							hListFull.put("nama_negeri", hListSelected.get("nama_negeri"));
							hListFull.put("LUAS", hListSelected.get("LUAS"));
						}
					}

					combine.add(hListFull);

				}

			} catch (Exception er) {
				myLogger.error(er);
				throw er;
			} finally {
				if (db != null)
					db.close();
			}

			return combine;
		}
		
		private String namaNegeri(int x) {
			String namaNegeri = "";
			switch (x) {
			case 1:
				namaNegeri = "JOHOR";
				break;
			case 2:
				namaNegeri = "KEDAH";
				break;
			case 3:
				namaNegeri = "KELANTAN";
				break;
			/*case 4:
				namaNegeri = "MELAKA";
				break;*/
			case 4:
				namaNegeri = "NEGERI SEMBILAN";
				break;
			case 5:
				namaNegeri = "PAHANG";
				break;
			/*case 7:
				namaNegeri = "PULAU PINANG";
				break;*/
			case 6:
				namaNegeri = "PERAK";
				break;
			case 7:
				namaNegeri = "PERLIS";
				break;
			case 8:
				namaNegeri = "SELANGOR";
				break;
			case 9:
				namaNegeri = "TERENGGANU";
				break;
			/*case 12:
				namaNegeri = "SABAH";
				break;
			case 13:
				namaNegeri = "SARAWAK";
				break;*/
			case 10:
				namaNegeri = "WILAYAH PERSEKUTUAN KUALA LUMPUR";
				break;
			/*case 15:
				namaNegeri = "WILAYAH PERSEKUTUAN LABUAN";
				break;*/
			/*case 16:
				namaNegeri = "WILAYAH PERSEKUTUAN PUTRAJAYA";
				break;*/
//			case 17:
//				namaNegeri = "TIADA MAKLUMAT";
//				break;
			default:
				break;
			}

			return namaNegeri;
		}

		private String idNegeri(int x) {
			String idNegeri = "";
			switch (x) {
			case 1:
				idNegeri = "1";
				break;
			case 2:
				idNegeri = "2";
				break;
			case 3:
				idNegeri = "3";
				break;
			/*case 4:
				idNegeri = "4";
				break;*/
			case 4:
				idNegeri = "5";
				break;
			case 5:
				idNegeri = "6";
				break;
			/*case 7:
				idNegeri = "7";
				break;*/
			case 6:
				idNegeri = "8";
				break;
			case 7:
				idNegeri = "9";
				break;
			case 8:
				idNegeri = "10";
				break;
			case 9:
				idNegeri = "11";
				break;
			/*case 12:
				idNegeri = "12";
				break;
			case 13:
				idNegeri = "13";
				break;*/
			case 10:
				idNegeri = "14";
				break;
			/*case 15:
				idNegeri = "15";
				break;
			case 16:
				idNegeri = "16";
				break;*/
//			case 17:
//				idNegeri = "0";
//				break;
			default:
				break;
			}

			return idNegeri;
		}
		
		private String abbrev(int x) {
			String abbrev = "";
			switch (x) {
			case 1:
				abbrev = "JHR";
				break;
			case 2:
				abbrev = "KDH";
				break;
			case 3:
				abbrev = "KTN";
				break;
			/*case 4:
				abbrev = "MLK";
				break;*/
			case 4:
				abbrev = "NS";
				break;
			case 5:
				abbrev = "PHG";
				break;
			/*case 7:
				abbrev = "PNG";
				break;*/
			case 6:
				abbrev = "PRK";
				break;
			case 7:
				abbrev = "PLS";
				break;
			case 8:
				abbrev = "SEL";
				break;
			case 9:
				abbrev = "TRG";
				break;
			/*case 12:
				abbrev = "SBH";
				break;
			case 13:
				abbrev = "SRW";
				break;*/
			case 10:
				abbrev = "KUL";
				break;
			/*case 15:
				abbrev = "LBN";
				break;
			case 16:
				abbrev = "PJY";
				break;*/
//			case 17:
//				abbrev = "TM";
//				break;
			default:
				break;
			}

			return abbrev;
		}
		
		// GENERATE BAR/PIE CHART

		public String generateXML(String nama_laporan) {

			String xml = "<chart caption='" + nama_laporan + "' subcaption='' xAxisName='Negeri       ' yAxisName='Jumlah      ' numberPrefix='' showLegend='1'>";
			Db db = null;
			try {			
				db = new Db();
				Connection conn = db.getConnection();
				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery(getSQL());
				
				for(Object vec : combine){
					Hashtable htbl = (Hashtable) vec;
					xml = xml + "<set label='" + htbl.get("abbrev")+ "' value='" + htbl.get("LUAS") + "' />";
				}
				
				xml = xml + "</chart>";
			} catch (Exception er) {
				myLogger.error(er);
			} finally {
				if (db != null)
					db.close();
			}

			return xml;
		}
}		
		
		
