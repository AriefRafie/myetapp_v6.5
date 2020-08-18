package ekptg.model.htp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.entities.Tblrujlot;
import ekptg.model.entities.Tblrujmukim;
import ekptg.model.htp.cukai.entity.CukaiTemp;

public class FrmCukaiSenaraiFailExcelUpload {

	static Logger myLog = Logger
			.getLogger(ekptg.model.htp.FrmCukaiSenaraiFailExcelUpload.class);

	public static CukaiTemp getCukaiTemp(String idCukaiTemp) {
		CukaiTemp temp = null;
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			SQLRenderer r = new SQLRenderer();
			sql = "select ID_CUKAITEMP,ID_NEGERI,ID_DAERAH, ID_MUKIM,ID_JENISHAKMILIK, NO_HAKMILIK,ID_LOT, NO_LOT,KEGUNAAN_TANAH, CUKAI_KENA_BAYAR, TUNGGAKAN,"
					+ "cukai_taliair,cukai_parit,DENDA, PENGURANGAN, CUKAI_PERLU_BAYAR, ID_NEGERI, ID_DAERAH, NO_HAKMILIKUPLOAD,CUKAI_LAIN,lebihan,"
					+ "NO_LOTUPLOAD, TARIKH_MASUK from TBLHTPCUKAITEMP WHERE ID_CUKAITEMP=?";
			Connection conn = db.getConnection();
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setLong(1, Long.parseLong(idCukaiTemp));
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				temp = new CukaiTemp();
				temp.setIdCukaiTemp(rs.getLong("ID_CUKAITEMP"));
				temp.setIdNegeri(rs.getLong("ID_NEGERI"));
				temp.setIdDaerah(rs.getLong("ID_DAERAH"));
				temp.setIdMukim(rs.getLong("ID_MUKIM"));
				temp.setIdJenisHakmilik(rs.getLong("ID_JENISHAKMILIK"));
				temp.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				temp.setIdLot(rs.getLong("ID_LOT"));
				temp.setNoLot(rs.getString("NO_LOT"));
				temp.setKegunaanTanah(rs.getString("KEGUNAAN_TANAH"));
				temp.setCukaiPerluBayar(rs.getDouble("CUKAI_PERLU_BAYAR"));
				temp.setCukaiKenaBayar(rs.getDouble("CUKAI_KENA_BAYAR"));
				temp.setTunggakkan(rs.getDouble("TUNGGAKAN"));
				temp.setDenda(rs.getDouble("DENDA"));
				temp.setCukaiTaliAir(rs.getDouble("CUKAI_TALIAIR"));
				temp.setCukaiParit(rs.getDouble("CUKAI_PARIT"));
				temp.setCukailain(rs.getDouble("CUKAI_LAIN"));
				temp.setLebihan(rs.getDouble("LEBIHAN"));

			}
			rs.close();
			prep.close();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (db != null)
				db.close();
		}
		return temp;
	}
	
	public static CukaiTemp getCukaiTemp2() {
		CukaiTemp temp = null;
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			SQLRenderer r = new SQLRenderer();
			sql = "select ID_CUKAITEMP,ID_NEGERI,ID_DAERAH, ID_MUKIM,ID_JENISHAKMILIK, NO_HAKMILIK,ID_LOT, NO_LOT,KEGUNAAN_TANAH, CUKAI_KENA_BAYAR, TUNGGAKAN,"
					+ "cukai_taliair,cukai_parit,DENDA, PENGURANGAN, CUKAI_PERLU_BAYAR, ID_NEGERI, ID_DAERAH, NO_HAKMILIKUPLOAD,CUKAI_LAIN,lebihan,"
					+ "NO_LOTUPLOAD, TARIKH_MASUK from TBLHTPCUKAITEMP";
			Connection conn = db.getConnection();
			PreparedStatement prep = conn.prepareStatement(sql);
			//prep.setLong(1, Long.parseLong(idCukaiTemp));
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				temp = new CukaiTemp();
				temp.setIdCukaiTemp(rs.getLong("ID_CUKAITEMP"));
				temp.setIdNegeri(rs.getLong("ID_NEGERI"));
				temp.setIdDaerah(rs.getLong("ID_DAERAH"));
				temp.setIdMukim(rs.getLong("ID_MUKIM"));
				temp.setIdJenisHakmilik(rs.getLong("ID_JENISHAKMILIK"));
				temp.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				temp.setIdLot(rs.getLong("ID_LOT"));
				temp.setNoLot(rs.getString("NO_LOT"));
				temp.setKegunaanTanah(rs.getString("KEGUNAAN_TANAH"));
				temp.setCukaiPerluBayar(rs.getDouble("CUKAI_PERLU_BAYAR"));
				temp.setCukaiKenaBayar(rs.getDouble("CUKAI_KENA_BAYAR"));
				temp.setTunggakkan(rs.getDouble("TUNGGAKAN"));
				temp.setDenda(rs.getDouble("DENDA"));
				temp.setCukaiTaliAir(rs.getDouble("CUKAI_TALIAIR"));
				temp.setCukaiParit(rs.getDouble("CUKAI_PARIT"));
				temp.setCukailain(rs.getDouble("CUKAI_LAIN"));
				temp.setLebihan(rs.getDouble("LEBIHAN"));

			}
			rs.close();
			prep.close();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (db != null)
				db.close();
		}
		return temp;
	}

	public static Vector getCukaiTempByTahun(String tahun) {
		Vector v = new Vector();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT M.NAMA_MUKIM, T.ID_CUKAITEMP,T.ID_NEGERI,T.ID_DAERAH, T.ID_MUKIM,T.ID_JENISHAKMILIK" +
				",T.NO_HAKMILIK,T.ID_LOT, T.NO_LOT,KEGUNAAN_TANAH" +
				",T.CUKAI_KENA_BAYAR, T.TUNGGAKAN, T.cukai_taliair,T.cukai_parit,T.DENDA, T.PENGURANGAN" +
				",T.CUKAI_PERLU_BAYAR,T.NO_HAKMILIKUPLOAD,T.CUKAI_LAIN,T.lebihan" +
				",T.NO_LOTUPLOAD, T.TARIKH_MASUK " +
				",NVL((SELECT RL.KETERANGAN FROM TBLRUJLOT RL WHERE RL.ID_LOT = T.ID_LOT "+
				"),'TIADA MAKLUMAT') JENIS_LOT "+
				",NVL((SELECT RJH.KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK RJH "+
				"WHERE RJH.ID_JENISHAKMILIK = T.ID_JENISHAKMILIK "+
				"),'TIADA MAKLUMAT') KOD_JENIS_HAKMILIK "+
				" FROM TBLHTPCUKAITEMP T,tblrujmukim M " +
				" WHERE T.ID_MUKIM = M.ID_MUKIM AND TAHUN=? " +
//				"ORDER BY T.id_cukaitemp DESC" +
				"";
			sql += " ORDER BY ID_NEGERI,ID_DAERAH,ID_MUKIM";

			Connection conn = db.getConnection();
			myLog.info(sql);
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setString(1, tahun);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {

				CukaiTemp temp = new CukaiTemp();
				Tblrujmukim mukim = new Tblrujmukim();
				Tblrujjenishakmilik rHakmilik = new Tblrujjenishakmilik();
				Tblrujlot rLot = new Tblrujlot();
				mukim.setNamaMukim(rs.getString("NAMA_MUKIM"));
				temp.setIdCukaiTemp(rs.getLong("ID_CUKAITEMP"));
				temp.setIdNegeri(rs.getLong("ID_NEGERI"));
				temp.setIdDaerah(rs.getLong("ID_DAERAH"));
				temp.setIdMukim(rs.getLong("ID_MUKIM"));
				temp.setIdJenisHakmilik(rs.getLong("ID_JENISHAKMILIK"));
				//temp.getRujLot().getKodLot()
				rHakmilik.setKodJenisHakmilik(rs.getString("KOD_JENIS_HAKMILIK"));
				temp.setRujJenisHakmilik(rHakmilik);
				temp.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				temp.setIdLot(rs.getLong("ID_LOT"));
				//temp.getRujJenisHakmilik().getKodJenisHakmilik()
				rLot.setKodLot(rs.getString("JENIS_LOT"));
				temp.setRujLot(rLot);
				String noLot = "";
				if(rs.getString("NO_LOT").contains(".0")){
					noLot = rs.getString("NO_LOT").substring(0,rs.getString("NO_LOT").indexOf(".0"));
					myLog.info("noLot:"+noLot);
				}else{
					noLot = rs.getString("NO_LOT");
				}
				temp.setNoLot(noLot);
				temp.setKegunaanTanah(rs.getString("KEGUNAAN_TANAH"));
				temp.setCukaiPerluBayar(rs.getDouble("CUKAI_PERLU_BAYAR"));
				temp.setCukaiKenaBayar(rs.getDouble("CUKAI_KENA_BAYAR"));
				temp.setTunggakkan(rs.getDouble("TUNGGAKAN"));
				temp.setDenda(rs.getDouble("DENDA"));
				temp.setCukaiTaliAir(rs.getDouble("CUKAI_TALIAIR"));
				temp.setCukaiParit(rs.getDouble("CUKAI_PARIT"));
				temp.setCukailain(rs.getDouble("CUKAI_LAIN"));
				temp.setLebihan(rs.getDouble("LEBIHAN"));
				temp.setRujMukim(mukim);

				v.addElement(temp);

			}
			rs.close();
			prep.close();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (db != null)
				db.close();
		}
		return v;
		
	}

	/**
	 * listing TBLHTPCUKAITEMP
	 * 
	 * @return
	 * @throws Exception
	 */

	public static Vector CukaigetSenaraiTahunFail() throws Exception {
		Db db = null;
		String sql = "";
		Vector list = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "select t.tahun,count(t.tahun) as counttahun from TBLHTPCUKAITEMP t group by t.tahun order by t.tahun desc";
			//
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("tahunFail", rs.getString("tahun") == null ? "" : rs.getString("tahun"));
				h.put("Counttahun", rs.getString("counttahun") == null ? "": rs.getString("counttahun"));
				list.addElement(h);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return list;
		
	}

	public static Vector<Hashtable<String, String>> CukaigetSenaraiFailExcel()
			throws Exception {
		Db db = null;
		String sql = "";
		// System.out.println("Fail search in Model::" + search);

		try {
			// System.out.println("Fail search in Model::" + search);
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "select ID_CUKAITEMP, NAMA_MUKIM, NO_HAKMILIK, NO_LOT, CUKAI_KENA_BAYAR, TUNGGAKAN,"
					+ "cukai_taliair,cukai_parit,DENDA, PENGURANGAN, CUKAI_PERLU_BAYAR, ID_NEGERI, ID_DAERAH, NO_HAKMILIKUPLOAD,"
					+ "NO_LOTUPLOAD, TARIKH_MASUK from TBLHTPCUKAITEMP";
			
			myLog.info("FrmCukaiSenaraiFailExcelUpload:CukaigetSenaraiFailExcel::sql:::"+ sql);

			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();
			int i = 0;
			while (rs.next()) {

				Hashtable h = new Hashtable();
				h.put("ID_CUKAITEMP", rs.getString("ID_CUKAITEMP"));
				if (rs.getDouble("cukai_parit") == 0) {

					h.put("cukai_parit", "0.00");
				} else {
					h.put("cukai_parit", rs.getDouble("cukai_parit"));
				}

				if (rs.getDouble("cukai_taliair") == 0) {

					h.put("cukai_taliair", "0.00");
				} else {
					h.put("cukai_taliair", rs.getDouble("cukai_taliair"));
				}

				if (rs.getString("NAMA_MUKIM") == null) {

					h.put("NAMA_MUKIM", "");
				} else {
					h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM"));
				}

				if (rs.getString("NO_HAKMILIK") == null) {

					h.put("NO_HAKMILIK", "");
				} else {
					h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK"));
				}

				if (rs.getString("NO_LOT") == null) {

					h.put("NO_LOT", "");
				} else {
					h.put("NO_LOT", rs.getString("NO_LOT"));
				}

				if (rs.getDouble("CUKAI_KENA_BAYAR") == 0) {

					h.put("CUKAI_KENA_BAYAR", "0.00");
				} else {
					h.put("CUKAI_KENA_BAYAR", rs.getDouble("CUKAI_KENA_BAYAR"));
				}

				if (rs.getString("TUNGGAKAN") == null) {

					h.put("TUNGGAKAN", "0.00");
				} else {
					h.put("TUNGGAKAN", rs.getString("TUNGGAKAN"));
				}

				if (rs.getString("DENDA") == null) {

					h.put("DENDA", "");
				} else {
					h.put("DENDA", rs.getString("DENDA"));
				}

				if (rs.getString("PENGURANGAN") == null) {

					h.put("PENGURANGAN", "");
				} else {
					h.put("PENGURANGAN", rs.getString("PENGURANGAN"));
				}

				if (rs.getString("CUKAI_PERLU_BAYAR") == null) {

					h.put("CUKAI_PERLU_BAYAR", "0.00");
				} else {
					h.put("CUKAI_PERLU_BAYAR", Utils.format2Decimal(rs
							.getDouble("CUKAI_PERLU_BAYAR")));
				}

				if (rs.getString("NO_HAKMILIKUPLOAD") == null) {

					h.put("NO_HAKMILIKUPLOAD", "");
				} else {
					h.put("NO_HAKMILIKUPLOAD", rs
							.getString("NO_HAKMILIKUPLOAD"));
				}

				if (rs.getString("NO_LOTUPLOAD") == null) {

					h.put("NO_LOTUPLOAD", "");
				} else {
					h.put("NO_LOTUPLOAD", rs.getString("NO_LOTUPLOAD"));
				}

				list.addElement(h);
			}

			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	/**
	 * return list untuk compare table TEMP dengan Main
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Vector<Hashtable<String, String>> CukaiSenaraiKemaskiniFail()
			throws Exception {
		Db db = null;
		String sql = "";
		// System.out.println("Fail search in Model::" + search);

		try {
			// System.out.println("Fail search in Model::" + search);
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT distinct HTPCT.tahun, HTPHC.ID_HAKMILIKCUKAI,HTPCT.PENGECUALIAN,HTPCT.TUNGGAKAN,HTPCT.denda,u.CUKAI_TERKINI as cukaiterkini, HTPHC.cukai_taliair as cukai_taliair2, HTPHC.cukai_parit as cukai_parit2, HTPCT.NO_HAKMILIKUPLOAD, HTPCT.cukai_taliair,HTPCT.cukai_parit,HTPCT.cukai_kena_bayar,HTPHC.cukai_parit, "
					+ " RN.Id_Negeri,RN.NAMA_NEGERI,RD.Id_Daerah,RD.NAMA_DAERAH,RM.Id_Mukim,RM.NAMA_MUKIM , "
					+ " u.Id_Jenishakmilik,H.KOD_JENIS_HAKMILIK,U.NO_HAKMILIK,HTPCT.NO_HAKMILIK, LOT.KETERANGAN KETERANGAN_LOT,U.NO_LOT , "
					+ " u.TARIKH_LUPUT,u.CUKAI,u.CUKAI_TERKINI,u.LOKASI,LUAS.ID_LUAS,LUAS.KOD_LUAS,U.LUAS , "
					+ " U.TARIKH_WARTA,U.NO_WARTA,U.NO_PELAN,U.NO_SYIT,RK.ID_KATEGORI,RK.KOD_KATEGORI,RK.KETERANGAN,U.SYARAT,U.SEKATAN,HTPHP.KEGUNAAN_TANAH , "
					+ " HTPHC.PENGECUALIAN,HTPHC.PENGURANGAN,NVL(U.NO_FAIL,'TIADA') NO_FAIL,NVL(U.NO_FAIL_PTG,'TIADA') NO_FAIL_PTG " +
							" ,RN.KOD_MAMPU"
					+ // ,u.ID_HAKMILIK,htphc.ID_HAKMILIKCUKAI ,u.Id_Permohonan
					" FROM Tblhtphakmilik u, Tblrujjenishakmilik h, Tblrujlot lot, Tblrujluas luas, TBLRUJNEGERI RN,TBLRUJDAERAH RD , "
					+ " TBLRUJMUKIM RM,TBLRUJKATEGORI RK,TBLHTPHAKMILIKPERIHAL HTPHP,TBLHTPHAKMILIKCUKAI HTPHC, TBLHTPCUKAITEMP HTPCT "
					+ " where u.Id_Jenishakmilik=h.Id_Jenishakmilik "
					+ " AND u.Id_Lot=lot.Id_Lot "
					+ " AND u.Id_Luas=luas.Id_Luas "
					+ " AND U.ID_KATEGORI=RK.ID_KATEGORI "
					+ " AND U.ID_MUKIM=RM.ID_MUKIM "
					+ " AND RM.ID_DAERAH=RD.ID_DAERAH "
					+ " AND RD.ID_NEGERI=RN.ID_NEGERI "
					+ " AND U.ID_HAKMILIK=HTPHP.ID_HAKMILIK "
					+ " AND U.ID_HAKMILIK=HTPHC.ID_HAKMILIK "
					+ " AND U.NO_LOT = HTPCT.NO_LOT "
					+ " AND LTRIM(U.NO_HAKMILIK,0)=LTRIM(HTPCT.NO_HAKMILIK,0) "
					+ " AND u.ID_HAKMILIK not in((select hc.ID_HAKMILIK from tblhtpcukaiterperinci ct,tblhtphakmilikcukai hc "
					+ " where hc.ID_HAKMILIKCUKAI=ct.ID_HAKMILIKCUKAI))"
					+ " order by RN.KOD_MAMPU,RD.ID_DAERAH,RM.ID_MUKIM";

			/*
			 * statement utk compare 2 table sql =
			 * "SELECT HTPCT.NO_HAKMILIKUPLOAD, HTPCT.cukai_taliair, HTPCT.cukai_parit, HTPCT.cukai_kena_bayar, HTPHC.cukai_taliair as cukai_taliair2,"
			 * +
			 * " HTPHC.cukai_parit as cukai_parit2,u.Id_Permohonan,RN.Id_Negeri,RN.NAMA_NEGERI,RD.Id_Daerah,RD.NAMA_DAERAH,RM.Id_Mukim,RM.NAMA_MUKIM,"
			 * +
			 * " u.Id_Jenishakmilik, H.KOD_JENIS_HAKMILIK, U.NO_HAKMILIK, LOT.KETERANGAN KETERANGAN_LOT, U.NO_LOT, "
			 * +
			 * " u.TARIKH_LUPUT, u.CUKAI, u.CUKAI_TERKINI as cukaiterkini, u.LOKASI, LUAS.ID_LUAS, LUAS.KOD_LUAS, U.LUAS,"
			 * + " U.TARIKH_WARTA, "+
			 * " U.NO_WARTA,U.NO_PELAN,U.NO_SYIT,RK.ID_KATEGORI,RK.KOD_KATEGORI,"
			 * + " RK.KETERANGAN,U.SYARAT,U.SEKATAN,HTPHP.KEGUNAAN_TANAH,"+
			 * " HTPHC.PENGECUALIAN,HTPHC.PENGURANGAN,NVL(U.NO_FAIL,'TIADA') NO_FAIL,NVL(U.NO_FAIL_PTG,'TIADA') NO_FAIL_PTG "
			 * +
			 * " from Tblhtphakmilik u, Tblrujjenishakmilik h, Tblrujlot lot, Tblrujluas luas, TBLRUJNEGERI RN,TBLRUJDAERAH RD,"
			 * +
			 * " TBLRUJMUKIM RM,TBLRUJKATEGORI RK,TBLHTPHAKMILIKPERIHAL HTPHP,TBLHTPHAKMILIKCUKAI HTPHC, TBLHTPCUKAITEMP HTPCT"
			 * + " where u.Id_Jenishakmilik=h.Id_Jenishakmilik "+
			 * " AND u.Id_Lot=lot.Id_Lot"+ " AND u.Id_Luas=luas.Id_Luas"+
			 * " AND U.ID_KATEGORI=RK.ID_KATEGORI"+
			 * " AND U.ID_MUKIM=RM.ID_MUKIM"+ " AND RM.ID_DAERAH=RD.ID_DAERAH"+
			 * " AND RD.ID_NEGERI=RN.ID_NEGERI"+
			 * " AND U.ID_HAKMILIK=HTPHP.ID_HAKMILIK"+
			 * " AND U.ID_HAKMILIK=HTPHC.ID_HAKMILIK"+
			 * " AND U.NO_LOT = HTPCT.NO_LOT";
			 */

			myLog.info("FrmCukaiSenaraiFailExcelUpload:CukaiSenaraiKemaskiniFail::sql:::"+ sql);

			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();
			Hashtable h;
			// 38E94-1ND38-UTHTZ-7323Y
			while (rs.next()) {
				h = new Hashtable();

				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM"));
				h.put("NO_HAKMILIKUPLOAD", rs.getString("NO_HAKMILIKUPLOAD"));
				h.put("ID_HAKMILIKCUKAI", rs.getString("ID_HAKMILIKCUKAI"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK"));
				if (rs.getString("PENGECUALIAN") != "") {
					h.put("lebihan", 0);
				} else {
					h.put("lebihan", rs.getString("PENGECUALIAN"));
				}
				h.put("tunggakan", rs.getString("TUNGGAKAN"));
				h.put("denda", rs.getString("denda"));
				h.put("cukai_parit", rs.getDouble("cukai_parit"));
				h.put("cukai_taliair", rs.getDouble("cukai_taliair"));
				h.put("CUKAI_KENA_BAYAR", rs.getDouble("CUKAI_KENA_BAYAR"));
				h.put("cukai_parit2", rs.getDouble("cukai_parit2"));
				h.put("cukai_taliair2", rs.getDouble("cukai_taliair2"));
				h.put("cukai_kena_bayar", rs.getDouble("cukaiterkini"));
				h.put("NO_LOT", rs.getString("NO_LOT"));
				h.put("KETERANGAN_LOT", rs.getString("KETERANGAN_LOT"));
				h.put("tahun", Utils.isNull(rs.getString("tahun")));

				list.addElement(h);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public static Vector<Hashtable<String, String>> CukaiSenaraiKemaskiniFailV1()throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
			
			//sql = "SELECT distinct HTPCT.tahun, HTPHC.ID_HAKMILIKCUKAI,HTPCT.PENGECUALIAN,HTPCT.TUNGGAKAN,HTPCT.denda,u.CUKAI_TERKINI as cukaiterkini, HTPHC.cukai_taliair as cukai_taliair2, HTPHC.cukai_parit as cukai_parit2, HTPCT.NO_HAKMILIKUPLOAD, HTPCT.cukai_taliair,HTPCT.cukai_parit,HTPCT.cukai_kena_bayar,HTPHC.cukai_parit, "
				sql = "SELECT distinct HTPHC.ID_HAKMILIKCUKAI,HTPHC.CUKAI,HTPHC.CUKAI_TERKINI as cukaiterkini, HTPHC.cukai_taliair " +
						", HTPHC.cukai_parit ,HTPHC.PENGECUALIAN,HTPHC.PENGURANGAN,HTPHC.DENDA  "+
						", U.Id_Jenishakmilik,H.KOD_JENIS_HAKMILIK,U.NO_HAKMILIK" +
						", LOT.KETERANGAN KETERANGAN_LOT,U.NO_LOT " +
						", U.TARIKH_LUPUT,U.CUKAI_TERKINI,u.LOKASI,LUAS.ID_LUAS,LUAS.KOD_LUAS,U.LUAS " +
						", U.TARIKH_WARTA,U.NO_WARTA,U.NO_PELAN,U.NO_SYIT,U.SYARAT,U.SEKATAN,U.KEGUNAAN_TANAH " +
						",  NVL(U.NO_FAIL,'TIADA') NO_FAIL,NVL(U.NO_FAIL_PTG,'TIADA') NO_FAIL_PTG " +
						",RN.KOD_MAMPU, RN.Id_Negeri,RN.NAMA_NEGERI,RD.Id_Daerah,RD.NAMA_DAERAH,RM.ID_MUKIM,RM.NAMA_MUKIM "+
						", RK.ID_KATEGORI,RK.KOD_KATEGORI,RK.KETERANGAN "+
						"  "+
					" FROM Tblhtphakmilik U, Tblrujjenishakmilik H, Tblrujlot LOT, Tblrujluas LUAS" +
					", TBLRUJNEGERI RN,TBLRUJDAERAH RD ,TBLRUJMUKIM RM, "
					+ " TBLRUJKATEGORI RK,TBLHTPHAKMILIKCUKAI HTPHC"
					+ " where U.Id_Jenishakmilik = H.Id_Jenishakmilik "
					+ " AND U.Id_Lot=lot.Id_Lot "
					+ " AND U.Id_Luas=luas.Id_Luas "
					+ " AND U.ID_KATEGORI=RK.ID_KATEGORI "
					+ " AND U.ID_MUKIM=RM.ID_MUKIM "
					+ " AND RM.ID_DAERAH=RD.ID_DAERAH "
					+ " AND RD.ID_NEGERI=RN.ID_NEGERI "
					+ " AND U.ID_HAKMILIK=HTPHC.ID_HAKMILIK "
					+ " AND HTPHC.ID_HAKMILIKCUKAI not in((select TPCTI.ID_HAKMILIKCUKAI from tblhtpcukaiterperinci TPCTI,tblhtphakmilikcukai TPHCI "
					+ " where TPCTI.ID_HAKMILIKCUKAI=TPHCI.ID_HAKMILIKCUKAI))"
					+ " order by RN.KOD_MAMPU,RD.ID_DAERAH,RM.ID_MUKIM";
		
		
			myLog.info("FrmCukaiSenaraiFailExcelUpload:CukaiSenaraiKemaskiniFail::sql:::"+ sql);
		
			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();
			Hashtable h;
			// 38E94-1ND38-UTHTZ-7323Y
			while (rs.next()) {
				h = new Hashtable();
		
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM"));
				//h.put("NO_HAKMILIKUPLOAD", rs.getString("NO_HAKMILIKUPLOAD"));
				h.put("ID_HAKMILIKCUKAI", rs.getString("ID_HAKMILIKCUKAI"));
				h.put("NO_HAKMILIK", Utils.isNull(rs.getString("NO_HAKMILIK")));
				if (rs.getString("PENGECUALIAN") != "") {
					h.put("lebihan", 0);
				} else {
					h.put("lebihan", Utils.isNull(rs.getString("PENGECUALIAN")));
				}
				h.put("tunggakan", Utils.isNull(rs.getString("PENGURANGAN")));
				h.put("denda", Utils.isNull(rs.getString("denda")));
				h.put("cukai_parit", rs.getDouble("cukai_parit"));
				h.put("cukai_taliair", rs.getDouble("cukai_taliair"));
				h.put("CUKAI_KENA_BAYAR", (rs.getDouble("CUKAI_TERKINI")+rs.getDouble("PENGURANGAN")+rs.getDouble("denda")+rs.getDouble("cukai_parit")+rs.getDouble("cukai_taliair"))-rs.getDouble("PENGECUALIAN"));
				//h.put("cukai_parit2", rs.getDouble("cukai_parit2"));
				//h.put("cukai_taliair2", rs.getDouble("cukai_taliair2"));
				h.put("cukai_kena_bayar", rs.getDouble("cukai_terkini"));
				h.put("NO_LOT", Utils.isNull(rs.getString("NO_LOT")));
				h.put("KETERANGAN_LOT", Utils.isNull(rs.getString("KETERANGAN_LOT")));
				//h.put("tahun", Utils.isNull(rs.getString("tahun")));
		
				list.addElement(h);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
		}

	public static Vector<Hashtable<String, String>> CukaiSenaraiKemaskiniFailV1(String idNegeri,String idDaerah, String idMukim)throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
			
			//sql = "SELECT distinct HTPCT.tahun, HTPHC.ID_HAKMILIKCUKAI,HTPCT.PENGECUALIAN,HTPCT.TUNGGAKAN,HTPCT.denda,u.CUKAI_TERKINI as cukaiterkini, HTPHC.cukai_taliair as cukai_taliair2, HTPHC.cukai_parit as cukai_parit2, HTPCT.NO_HAKMILIKUPLOAD, HTPCT.cukai_taliair,HTPCT.cukai_parit,HTPCT.cukai_kena_bayar,HTPHC.cukai_parit, "
				sql = "SELECT distinct HTPHC.ID_HAKMILIKCUKAI,HTPHC.CUKAI,HTPHC.CUKAI_TERKINI as cukaiterkini, HTPHC.cukai_taliair " +
						", HTPHC.cukai_parit ,HTPHC.PENGECUALIAN,HTPHC.PENGURANGAN,HTPHC.DENDA  "+
						", U.Id_Jenishakmilik,H.KOD_JENIS_HAKMILIK,U.NO_HAKMILIK" +
						", LOT.KETERANGAN KETERANGAN_LOT,U.NO_LOT " +
						", U.TARIKH_LUPUT,U.CUKAI_TERKINI,u.LOKASI,LUAS.ID_LUAS,LUAS.KOD_LUAS,U.LUAS " +
						", U.TARIKH_WARTA,U.NO_WARTA,U.NO_PELAN,U.NO_SYIT,U.SYARAT,U.SEKATAN,U.KEGUNAAN_TANAH " +
						",  NVL(U.NO_FAIL,'TIADA') NO_FAIL,NVL(U.NO_FAIL_PTG,'TIADA') NO_FAIL_PTG " +
						" ,TO_CHAR(U.TARIKH_DAFTAR,'dd/mm/yyyy') TARIKH_DAFTAR" +
						",RN.KOD_MAMPU, RN.Id_Negeri,RN.NAMA_NEGERI,RD.Id_Daerah,RD.NAMA_DAERAH,RM.ID_MUKIM,RM.NAMA_MUKIM "+
						", RK.ID_KATEGORI,RK.KOD_KATEGORI,RK.KETERANGAN,TO_CHAR(sysdate,'yyyy') tahun "+
						"  "+
					" FROM Tblhtphakmilik U, Tblrujjenishakmilik H, Tblrujlot LOT, Tblrujluas LUAS" +
					", TBLRUJNEGERI RN,TBLRUJDAERAH RD ,TBLRUJMUKIM RM, "
					+ " TBLRUJKATEGORI RK,TBLHTPHAKMILIKCUKAI HTPHC"
					+ " where NVL(U.NO_HAKMILIK,' ') <> ' ' AND U.Id_Jenishakmilik = H.Id_Jenishakmilik "
					+ " AND U.Id_Lot=lot.Id_Lot "
					+ " AND U.Id_Luas=luas.Id_Luas "
					+ " AND U.ID_KATEGORI=RK.ID_KATEGORI "
					+ " AND U.ID_MUKIM=RM.ID_MUKIM "
					+ " AND RM.ID_DAERAH=RD.ID_DAERAH "
					+ " AND RD.ID_NEGERI=RN.ID_NEGERI "
					+ " AND U.ID_HAKMILIK=HTPHC.ID_HAKMILIK " +
					"  "
					+ " AND HTPHC.ID_HAKMILIKCUKAI not in((select TPCTI.ID_HAKMILIKCUKAI from tblhtpcukaiterperinci TPCTI,tblhtphakmilikcukai TPHCI "
					+ " where TPCTI.ID_HAKMILIKCUKAI=TPHCI.ID_HAKMILIKCUKAI))"
					+ " AND (U.STATUS_SAH IS NULL OR U.STATUS_SAH IN ('T','H','D')) " +
					" AND U.PEGANGAN_HAKMILIK NOT IN " +
					"(SELECT TPU.PEGANGAN_HAKMILIK FROM TBLHTPHAKMILIKURUSAN TPU,TBLPERMOHONAN P, TBLPFDFAIL F " +
					"WHERE TPU.ID_PERMOHONAN=P.ID_PERMOHONAN AND P.ID_FAIL=F.ID_FAIL AND F.ID_URUSAN=3 )  " +
					"";
			 	     if(idNegeri != ""){
			  	    	sql += " AND RN.ID_NEGERI = "+idNegeri;
			  	     }
			  	     if(idDaerah != ""){
			  	    	sql += " AND RD.ID_DAERAH = "+idDaerah;
			  	     }
			  	     if(idMukim != ""){
			  	    	sql += " AND U.ID_MUKIM = "+idMukim;
			  	     }
					sql +=" order by RN.KOD_MAMPU,RD.ID_DAERAH,RM.ID_MUKIM";
		
		
			myLog.info("FrmCukaiSenaraiFailExcelUpload:CukaiSenaraiKemaskiniFail::sql:::"+ sql);
		
			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();
			Hashtable h;
			// 38E94-1ND38-UTHTZ-7323Y
			while (rs.next()) {
				h = new Hashtable();
		
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM"));
				//h.put("NO_HAKMILIKUPLOAD", rs.getString("NO_HAKMILIKUPLOAD"));
				h.put("ID_HAKMILIKCUKAI", rs.getString("ID_HAKMILIKCUKAI"));
				h.put("NO_HAKMILIK", Utils.isNull(rs.getString("NO_HAKMILIK")));
				if (rs.getString("PENGECUALIAN") != "") {
					h.put("lebihan", 0);
				} else {
					h.put("lebihan", Utils.isNull(rs.getString("PENGECUALIAN")));
				}
				h.put("tunggakan", Utils.isNull(rs.getString("PENGURANGAN")));
				h.put("denda", Utils.isNull(rs.getString("denda")));
				h.put("cukai_parit", rs.getDouble("cukai_parit"));
				h.put("cukai_taliair", rs.getDouble("cukai_taliair"));
				h.put("CUKAI_KENA_BAYAR", (rs.getDouble("CUKAI_TERKINI")+rs.getDouble("PENGURANGAN")+rs.getDouble("denda")+rs.getDouble("cukai_parit")+rs.getDouble("cukai_taliair"))-rs.getDouble("PENGECUALIAN"));
				//h.put("cukai_parit2", rs.getDouble("cukai_parit2"));
				//h.put("cukai_taliair2", rs.getDouble("cukai_taliair2"));
				h.put("cukai_kena_bayar", rs.getDouble("cukai_terkini"));
				h.put("NO_LOT", Utils.isNull(rs.getString("NO_LOT")));
				h.put("KETERANGAN_LOT", Utils.isNull(rs.getString("KETERANGAN_LOT")));
				h.put("tahun", Utils.isNull(rs.getString("tahun")));
				h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
				h.put("kegunaan", Utils.isNull(rs.getString("KEGUNAAN_TANAH")));
				h.put("labelHakmilik", Utils.isNull(rs.getString("KOD_JENIS_HAKMILIK"))+Utils.isNull(rs.getString("NO_HAKMILIK")));
		
				list.addElement(h);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
		}	

		public static Vector<Hashtable<String, String>> getCukaiKemaskiniList() throws Exception {
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

			// sql="SELECT distinct htpct.BAYARAN_LAIN,htpct.CUKAI_PERLU_BAYAR,htpct.PENGECUALIAN,HTPCT.denda,HTPCT.TUNGGAKAN,HTPHC.ID_HAKMILIKCUKAI,u.CUKAI_TERKINI as cukaiterkini, HTPHC.cukai_taliair as cukai_taliair2, HTPHC.cukai_parit as cukai_parit2, HTPCT.NO_HAKMILIKUPLOAD, HTPCT.cukai_taliair,HTPCT.cukai_parit,HTPCT.cukai_kena_bayar,HTPHC.cukai_parit, "
			// +
			// " RN.Id_Negeri,RN.NAMA_NEGERI,RD.Id_Daerah,RD.NAMA_DAERAH,RM.Id_Mukim,RM.NAMA_MUKIM , "
			// +
			// " u.Id_Jenishakmilik,H.KOD_JENIS_HAKMILIK,U.NO_HAKMILIK,HTPCT.NO_HAKMILIK, LOT.KETERANGAN KETERANGAN_LOT,U.NO_LOT , "
			// +
			// " u.TARIKH_LUPUT,u.CUKAI,u.CUKAI_TERKINI,u.LOKASI,LUAS.ID_LUAS,LUAS.KOD_LUAS,U.LUAS , "
			// +
			// " U.TARIKH_WARTA,U.NO_WARTA,U.NO_PELAN,U.NO_SYIT,RK.ID_KATEGORI,RK.KOD_KATEGORI,RK.KETERANGAN,U.SYARAT,U.SEKATAN,HTPHP.KEGUNAAN_TANAH , "
			// +
			// " HTPHC.PENGECUALIAN,HTPHC.PENGURANGAN,NVL(U.NO_FAIL,'TIADA') NO_FAIL,NVL(U.NO_FAIL_PTG,'TIADA') NO_FAIL_PTG,HTPCT.KEGUNAAN_TANAH"
			// + //,u.ID_HAKMILIK,htphc.ID_HAKMILIKCUKAI ,u.Id_Permohonan
			// " FROM Tblhtphakmilik u, Tblrujjenishakmilik h, Tblrujlot lot, Tblrujluas luas, TBLRUJNEGERI RN,TBLRUJDAERAH RD , "
			// +
			// " TBLRUJMUKIM RM,TBLRUJKATEGORI RK,TBLHTPHAKMILIKPERIHAL HTPHP,TBLHTPHAKMILIKCUKAI HTPHC, TBLHTPCUKAITEMP HTPCT "
			// +
			// " where u.Id_Jenishakmilik=h.Id_Jenishakmilik " +
			// " AND u.Id_Lot=lot.Id_Lot " +
			// " AND u.Id_Luas=luas.Id_Luas " +
			// " AND U.ID_KATEGORI=RK.ID_KATEGORI " +
			// " AND U.ID_MUKIM=RM.ID_MUKIM " +
			// " AND RM.ID_DAERAH=RD.ID_DAERAH " +
			// " AND RD.ID_NEGERI=RN.ID_NEGERI " +
			// " AND U.ID_HAKMILIK=HTPHP.ID_HAKMILIK " +
			// " AND U.ID_HAKMILIK=HTPHC.ID_HAKMILIK " +
			// " AND U.NO_LOT = HTPCT.NO_LOT " +
			// " AND LTRIM(U.NO_HAKMILIK,0)=HTPCT.NO_HAKMILIK " +
			// " AND u.ID_HAKMILIK in((select hc.ID_HAKMILIK from tblhtpcukaiterperinci ct,tblhtphakmilikcukai hc "
			// +
			// " where hc.ID_HAKMILIKCUKAI=ct.ID_HAKMILIKCUKAI))";

			// keluar double sql =
			// "SELECT distinct  TERP.TUNGGAKAN,TERP.DENDA,TERP.PENGECUALIAN,TERP.CUKAI_KENA_BAYAR,htpct.CUKAI_PERLU_BAYAR,HTPHC.ID_HAKMILIKCUKAI, "
			// +
			// "u.CUKAI_TERKINI as cukaiterkini,  HTPCT.NO_HAKMILIKUPLOAD, HTPCT.cukai_taliair,HTPCT.cukai_parit,HTPCT.cukai_kena_bayar, "
			// +
			// "RN.Id_Negeri,RN.NAMA_NEGERI,RD.Id_Daerah,RD.NAMA_DAERAH,RM.Id_Mukim,RM.NAMA_MUKIM , "
			// +
			// "u.Id_Jenishakmilik,H.KOD_JENIS_HAKMILIK, LOT.KETERANGAN KETERANGAN_LOT,U.NO_LOT , "
			// +
			// "u.TARIKH_LUPUT,u.CUKAI,u.CUKAI_TERKINI,u.LOKASI,LUAS.ID_LUAS,LUAS.KOD_LUAS,U.LUAS ,  "
			// +
			// "U.NO_PELAN,U.NO_SYIT,RK.ID_KATEGORI,RK.KOD_KATEGORI,RK.KETERANGAN,HTPHP.KEGUNAAN_TANAH , "
			// +
			// "NVL(U.NO_FAIL,'TIADA') NO_FAIL,NVL(U.NO_FAIL_PTG,'TIADA') NO_FAIL_PTG,HTPCT.KEGUNAAN_TANAH   "
			// +
			// "FROM Tblhtphakmilik u, Tblrujjenishakmilik h, Tblrujlot lot, Tblrujluas luas, TBLRUJNEGERI RN,TBLRUJDAERAH RD ,  "
			// +
			// "TBLRUJMUKIM RM,TBLRUJKATEGORI RK,TBLHTPHAKMILIKPERIHAL HTPHP,TBLHTPHAKMILIKCUKAI HTPHC, TBLHTPCUKAITEMP HTPCT , TBLHTPCUKAITERPERINCI TERP, tblhtpcukaiterperinci ct "
			// +
			// "where u.Id_Jenishakmilik=h.Id_Jenishakmilik  " +
			// " AND u.Id_Lot=lot.Id_Lot  " +
			// " AND u.Id_Luas=luas.Id_Luas  " +
			// " AND U.ID_KATEGORI=RK.ID_KATEGORI " +
			// " AND U.ID_MUKIM=RM.ID_MUKIM  " +
			// " AND RM.ID_DAERAH=RD.ID_DAERAH  " +
			// " AND RD.ID_NEGERI=RN.ID_NEGERI  " +
			// " AND U.ID_HAKMILIK=HTPHP.ID_HAKMILIK  " +
			// " AND U.ID_HAKMILIK=HTPHC.ID_HAKMILIK  " +
			// "	and HTPHC.ID_HAKMILIKCUKAI=ct.ID_HAKMILIKCUKAI"+
			// " AND U.NO_LOT = HTPCT.NO_LOT  " +
			// " AND LTRIM(U.NO_HAKMILIK,0)=HTPCT.NO_HAKMILIK  " +
			// " AND u.ID_HAKMILIK in((select hc.ID_HAKMILIK from tblhtpcukaiterperinci ct,tblhtphakmilikcukai hc  "
			// +
			// " where hc.ID_HAKMILIKCUKAI=ct.ID_HAKMILIKCUKAI)) " ;

			sql = "SELECT distinct  ct.TUNGGAKAN,ct.DENDA,ct.PENGECUALIAN,ct.CUKAI_KENA_BAYAR,HTPHC.ID_HAKMILIKCUKAI, "
					+ " u.CUKAI_TERKINI as cukaiterkini, ct.cukai_parit, ct.cukai_taliair,HTPCT.NO_HAKMILIKUPLOAD, "
					+ " RN.Id_Negeri,RN.NAMA_NEGERI,RD.Id_Daerah,RD.NAMA_DAERAH,RM.Id_Mukim,RM.NAMA_MUKIM ,"
					+ " u.Id_Jenishakmilik,H.KOD_JENIS_HAKMILIK, LOT.KETERANGAN KETERANGAN_LOT,U.NO_LOT ,"
					+ " u.TARIKH_LUPUT,u.CUKAI,u.CUKAI_TERKINI,u.LOKASI,LUAS.ID_LUAS,LUAS.KOD_LUAS,U.LUAS , "
					+ " U.NO_PELAN,U.NO_SYIT,RK.ID_KATEGORI,RK.KOD_KATEGORI,RK.KETERANGAN,HTPHP.KEGUNAAN_TANAH , "
					+ " NVL(U.NO_FAIL,'TIADA') NO_FAIL,NVL(U.NO_FAIL_PTG,'TIADA') NO_FAIL_PTG"
					+ " FROM Tblhtphakmilik u, Tblrujjenishakmilik h, Tblrujlot lot, Tblrujluas luas, TBLRUJNEGERI RN,TBLRUJDAERAH RD ,  "
					+ " TBLRUJMUKIM RM,TBLRUJKATEGORI RK,TBLHTPHAKMILIKPERIHAL HTPHP,TBLHTPHAKMILIKCUKAI HTPHC, TBLHTPCUKAITEMP HTPCT, tblhtpcukaiterperinci ct"
					+ " where u.Id_Jenishakmilik=h.Id_Jenishakmilik "
					+ " AND u.Id_Lot=lot.Id_Lot "
					+ " AND u.Id_Luas=luas.Id_Luas "
					+ " AND U.ID_KATEGORI=RK.ID_KATEGORI "
					+ " AND U.ID_MUKIM=RM.ID_MUKIM "
					+ " AND RM.ID_DAERAH=RD.ID_DAERAH "
					+ " AND RD.ID_NEGERI=RN.ID_NEGERI "
					+ " AND U.ID_HAKMILIK=HTPHP.ID_HAKMILIK "
					+ " AND U.ID_HAKMILIK=HTPHC.ID_HAKMILIK "
					+ " and HTPHC.ID_HAKMILIKCUKAI=ct.ID_HAKMILIKCUKAI"
					+ " AND U.NO_LOT = HTPCT.NO_LOT "
					+ " AND LTRIM(U.NO_HAKMILIK,0)=HTPCT.NO_HAKMILIK "
					+ " AND u.ID_HAKMILIK in((select hc.ID_HAKMILIK from tblhtpcukaiterperinci ct,tblhtphakmilikcukai hc "
					+ "where hc.ID_HAKMILIKCUKAI=ct.ID_HAKMILIKCUKAI)) order by RD.NAMA_DAERAH";

			myLog.info("FrmCukaiSenaraiFailExcelUpload:CukaiKemaskiniList::sql:::"+ sql);

			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("namaNegeri", rs.getString("NAMA_NEGERI"));
				h.put("b", rs.getString("NAMA_DAERAH"));
				h.put("c", rs.getString("NAMA_MUKIM"));
				h.put("d", rs.getString("KOD_JENIS_HAKMILIK"));
				h.put("e", rs.getString("NO_HAKMILIKUPLOAD"));
				// h.put("f", rs.getString("NO_HAKMILIK"));
				h.put("g", rs.getDouble("cukai_parit"));
				h.put("h", rs.getDouble("cukai_taliair"));
				h.put("i", rs.getDouble("CUKAI_KENA_BAYAR"));
				h.put("j", rs.getDouble("TUNGGAKAN"));
				h.put("k", rs.getDouble("DENDA"));
				// h.put("l", rs.getDouble("CUKAI_PERLU_BAYAR"));
				h.put("m", rs.getString("NO_LOT"));
				h.put("n", rs.getString("KETERANGAN_LOT"));
				h.put("o", rs.getString("LUAS"));
				h.put("p", rs.getString("KEGUNAAN_TANAH"));
				if (rs.getString("PENGECUALIAN") != "") {
					h.put("q", rs.getDouble("PENGECUALIAN"));
				} else {
					h.put("q", "0");
				}
				// if(rs.getString("BAYARAN_LAIN")!=""){
				// h.put("r", rs.getDouble("BAYARAN_LAIN"));
				// }else{
				// h.put("r", "0");
				// }
				list.addElement(h);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, String>> CukaigetSenaraiFailExcel2(
			String lblNegericari, String lblDaerahcari, String lblMukimcari,
			String noHakmilikcari, String tahun) throws Exception {
		Db db = null;
		CukaiTemp temp = null;
		Vector v = new Vector();
		Hashtable h;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT ID_CUKAITEMP, NAMA_MUKIM, NO_HAKMILIK, NO_LOT" +
				" ,CUKAI_LAIN,LEBIHAN, CUKAI_KENA_BAYAR" +
				" ,TUNGGAKAN,cukai_taliair,cukai_parit,DENDA, PENGURANGAN, CUKAI_PERLU_BAYAR" +
				" ,ID_NEGERI,ID_DAERAH, ID_MUKIM, NO_HAKMILIKUPLOAD" +
				" ,NO_LOTUPLOAD, TARIKH_MASUK " +
				" ,NVL((SELECT RL.KETERANGAN FROM TBLRUJLOT RL WHERE RL.ID_LOT = TBLHTPCUKAITEMP.ID_LOT "+
				" ),'TIADA MAKLUMAT') JENIS_LOT "+
				" ,NVL((SELECT RJH.KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK RJH "+
				" WHERE RJH.ID_JENISHAKMILIK = TBLHTPCUKAITEMP.ID_JENISHAKMILIK "+
				" ),'TIADA MAKLUMAT') KOD_JENIS_HAKMILIK" +
				" , KEGUNAAN_TANAH "+				
				" FROM TBLHTPCUKAITEMP WHERE TAHUN = "+tahun;

			if (lblNegericari != "") {
				sql += " and ID_NEGERI =" + lblNegericari;
			}
			if (lblDaerahcari != "") {
				sql += " and ID_DAERAH =" + lblDaerahcari;
			}
			if (lblMukimcari != "") {
				sql += " and ID_MUKIM =" + lblMukimcari;
			}
			if (noHakmilikcari != "") {
				sql += " and NO_HAKMILIKUPLOAD LIKE '%" + noHakmilikcari + "%'";
			}
			sql += " ORDER BY ID_NEGERI,ID_DAERAH,ID_MUKIM";
			//System.out.println(sql.toString());
			myLog.info("FrmCukaiSenaraiFailExcelUpload:CukaigetSenaraiFailExcel::sql:::"+ sql);

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				temp = new CukaiTemp();
				Tblrujmukim mukim = new Tblrujmukim();
				Tblrujjenishakmilik rHakmilik = new Tblrujjenishakmilik();
				Tblrujlot rLot = new Tblrujlot();
				
				mukim.setNamaMukim(rs.getString("NAMA_MUKIM"));
				temp.setIdCukaiTemp(rs.getLong("ID_CUKAITEMP"));
				temp.setIdNegeri(rs.getLong("ID_NEGERI"));
				temp.setIdDaerah(rs.getLong("ID_DAERAH"));
				temp.setIdMukim(rs.getLong("ID_MUKIM"));
				//temp.setIdJenisHakmilik(rs.getLong("ID_JENISHAKMILIK"));
				//temp.getRujLot().getKodLot()
				rHakmilik.setKodJenisHakmilik(rs.getString("KOD_JENIS_HAKMILIK"));
				temp.setRujJenisHakmilik(rHakmilik);
				temp.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				//temp.setIdLot(rs.getLong("ID_LOT"));
				//temp.getRujJenisHakmilik().getKodJenisHakmilik()
				rLot.setKodLot(rs.getString("JENIS_LOT"));
				temp.setRujLot(rLot);
				String noLot = "";
				if(rs.getString("NO_LOT").contains(".0")){
					noLot = rs.getString("NO_LOT").substring(0,rs.getString("NO_LOT").indexOf(".0"));
					myLog.info("noLot:"+noLot);
				}else{
					noLot = rs.getString("NO_LOT");
				}
				temp.setNoLot(noLot);

				temp.setCukaiPerluBayar(rs.getDouble("CUKAI_PERLU_BAYAR"));
				temp.setCukaiKenaBayar(rs.getDouble("CUKAI_KENA_BAYAR"));
				temp.setTunggakkan(rs.getDouble("TUNGGAKAN"));
				temp.setDenda(rs.getDouble("DENDA"));
				temp.setCukaiTaliAir(rs.getDouble("CUKAI_TALIAIR"));
				temp.setCukaiParit(rs.getDouble("CUKAI_PARIT"));
				temp.setCukailain(rs.getDouble("CUKAI_LAIN"));
				temp.setLebihan(rs.getDouble("LEBIHAN"));
				temp.setKegunaanTanah(rs.getString("KEGUNAAN_TANAH"));
				temp.setRujMukim(mukim);

				v.addElement(temp);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (db != null)
				db.close();
		}
		return v;

	}

}
