package ekptg.model.pdt;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.model.entities.Tblrujdaerah;
import ekptg.model.entities.Tblrujmukim;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.view.pdt.FrmUploadExcelForm;

public class FrmModelTanahRizabData {

	private static Logger myLogger = Logger.getLogger(FrmModelEnakmen.class);
	private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	public Vector carianTanahRizab(String paramNegeri, String paramDaerah, String paramMukim, String paramNoWarta,
			String paramNoPelan, String paramStatus, String paramJenis) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			String SQL_SEARCH = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			if (!"".equalsIgnoreCase(paramNegeri)) {
				SQL_SEARCH += " AND UPPER(A.ID_NEGERI) = '" + paramNegeri.toUpperCase() + "' ";
			}
			if (!"".equalsIgnoreCase(paramDaerah)) {
				SQL_SEARCH += " AND UPPER(A.ID_DAERAH) = '" + paramDaerah.toUpperCase() + "' ";
			}
			if (!"".equalsIgnoreCase(paramMukim)) {
				SQL_SEARCH += " AND UPPER(C.ID_MUKIM) = '" + paramDaerah.toUpperCase() + "' ";
			}
			if (!"".equalsIgnoreCase(paramNoWarta)) {
				SQL_SEARCH += " AND UPPER(B.NO_WARTA) LIKE '%" + paramNoWarta.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(paramNoPelan)) {
				SQL_SEARCH += " AND UPPER(C.NO_PELAN) LIKE '%" + paramNoPelan.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(paramJenis)) {
				SQL_SEARCH += " AND UPPER(B.FLAG_JENISWARTA) LIKE '%" + paramJenis.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(paramStatus)) {
				if (paramStatus.equals("0")) {
					paramStatus = "T";
				} else if (paramStatus.equals("1")) {
					paramStatus = "K";
				}
				SQL_SEARCH += " AND UPPER(B.FLAG_STATUSWARTA) = '" + paramStatus.toUpperCase() + "' ";
			}


			int iCount = 1;
			String RS_IdNegeri = "", RS_IdDaerah = "", RS_IdMukim = "", RS_Kawasan = "", RS_NoPelan = "", RS_NoWarta = "", RS_TarikhWarta = "", RS_Luas = "", RS_FlagStatusWarta = "";
			String RS_IdWarta = "";
			sql = " SELECT D.NAMA_NEGERI, E.NAMA_DAERAH, S.NAMA_MUKIM, C.KAWASAN, C.NO_PELAN,  B.NO_WARTA, TO_CHAR(B.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA, C.LUAS, "
					+ " DECODE (B.FLAG_STATUSWARTA,'T','Tidak Kuatkuasa','K','Kuatkuasa') as STATUS, B.ID_TBLPDTWARTA, C.ID_TBLPDTPELAN, B.KANDUNGAN AS KANDUNGAN_WARTA, C.KANDUNGAN AS KANDUNGAN_PELAN, "
					+ " DECODE (B.FLAG_JENISWARTA,'W','Pewujudan','B','Pembatalan') as JENIS_WARTA, A.ID_TBLPDTWARTAGANTI , A.ID_TBLPDTWARTAASAL, "
					+ " F.NO_WARTA as noWartaAsalbatal, H.NO_WARTA as noWartaAsalganti, A.ID_TBLPDTTANAHRIZABMELAYU "
					+ " FROM tblpdttanahrizabmelayu A, tblpdtwarta B, tblpdtpelan C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM S, tblpdtwarta F, tblpdtwarta H "
					+ " WHERE B.ID_TBLPDTWARTA = A.ID_TBLPDTWARTA" + " AND C.ID_TBLPDTWARTA = B.ID_TBLPDTWARTA"
					+ " AND D.ID_NEGERI = A.ID_NEGERI" + " AND E.ID_DAERAH = A.ID_DAERAH" + " AND S.ID_MUKIM = C.ID_MUKIM "
							+ " AND A.ID_TBLPDTWARTAASAL = F.ID_TBLPDTWARTA (+) "
							+ " AND A.ID_TBLPDTWARTAGANTI = H.ID_TBLPDTWARTA (+) " 
					+ SQL_SEARCH;
			sql += " ORDER BY A.TARIKH_KEMASKINI DESC ";
			myLogger.info("sql ::" + sql);
			rs = stmt.executeQuery(sql);
			ResultSet rs1 = stmt.executeQuery(sql);
			Hashtable h1;

			while (rs1.next()) {
				Blob  b = rs1.getBlob(12);
				Blob  c = rs1.getBlob(13);
				RS_IdWarta = rs1.getString("ID_TBLPDTWARTA") == null ? "" : rs1.getString("ID_TBLPDTWARTA");
				RS_IdNegeri = rs1.getString(1) == null ? "" : rs1.getString(1);
				RS_IdDaerah = rs1.getString(2) == null ? "" : rs1.getString(2);
				RS_IdMukim = rs1.getString(3) == null ? "" : rs1.getString(3);
				RS_Kawasan = rs1.getString(4) == null ? "" : rs1.getString(4);
				RS_NoPelan = rs1.getString(5) == null ? "" : rs1.getString(5);
				RS_NoWarta = rs1.getString(6) == null ? "" : rs1.getString(6);
				RS_TarikhWarta = rs1.getString(7) == null ? "" : rs1.getString(7);
				RS_Luas = rs1.getString(8) == null ? "" : rs1.getString(8);
				RS_FlagStatusWarta = rs1.getString(9) == null ? "" : rs1.getString(9);
				String noWartaAsalbatal = rs1.getString("noWartaAsalbatal") == null ? "" : rs1.getString("noWartaAsalbatal");
				String noWartaAsalganti = rs1.getString("noWartaAsalganti") == null ? "" : rs1.getString("noWartaAsalganti");

				h1 = new Hashtable();
				h1.put("idTblPdtWartaganti", rs1.getString(15) == null ? "" : rs1.getString(15));
				h1.put("jenisWarta", rs1.getString(14) == null ? "" : rs1.getString(14));
				h1.put("KANDUNGAN_WARTA", b == null ? false : true);
				h1.put("KANDUNGAN_PELAN", c == null ? false : true);
				h1.put("Bil", iCount);
				h1.put("Negeri", RS_IdNegeri);
				h1.put("Daerah", RS_IdDaerah);
				h1.put("Mukim", RS_IdMukim);
				h1.put("Kawasan", RS_Kawasan);
				h1.put("NoPelan", RS_NoPelan);
				h1.put("NoWarta", RS_NoWarta);
				h1.put("TarikhWarta", RS_TarikhWarta);
				h1.put("Luas", RS_Luas);
				h1.put("Status", RS_FlagStatusWarta);
				h1.put("IdWarta", RS_IdWarta);
				h1.put("idPelan", rs1.getString(11) == null ? "" : rs1.getString(11));
				h1.put("noWartaAsalbatal", noWartaAsalbatal);
				h1.put("noWartaAsalganti", noWartaAsalganti);
				h1.put("idTblPdtTanahRizabMelayu", rs1.getString("ID_TBLPDTTANAHRIZABMELAYU") == null ? "" : rs1.getString("ID_TBLPDTTANAHRIZABMELAYU"));

				v.addElement(h1);
				iCount++;
			}
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}

	public void simpanPendaftaran(String idNegeri, String idDaerah, String idMukim, String kawasan, String noWarta,
			String tarikhWarta, String noPelan, String luas, String status, String jenisWarta, HttpSession session)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPDTTANAHRIZABMELAYU
			r = new SQLRenderer();
			DB.getNextID("TBLPDTTANAHRIZABMELAYU_SEQ");
			r.add("ID_NEGERI", idNegeri == null ? "" : idNegeri);
			r.add("ID_DAERAH", idDaerah == null ? "" : idDaerah);
			sql = r.getSQLInsert("TBLPDTTANAHRIZABMELAYU");
			stmt.executeUpdate(sql);

			conn.commit();

			// TBLPDRWARTA
			r = new SQLRenderer();
			DB.getNextID("TBLPDRWARTA_SEQ");
			r.add("NO_WARTA", noWarta == null ? "" : noWarta);
			r.add("TARIKH_WARTA", r.unquote("sysdate"));
			r.add("FLAG_STATUSWARTA", status);
			r.add("FLAG_JENISWARTA", jenisWarta);
			/*
			 * r.add("FLAG_JENISWARTA", "W"); r.add("FLAG_JENISWARTA", "B");
			 */sql = r.getSQLInsert("TBLPDRWARTA");
			stmt.executeUpdate(sql);

			conn.commit();

			// TBLPDTPELAN
			r = new SQLRenderer();
			DB.getNextID("TBLPDTPELAN_SEQ");
			r.add("ID_MUKIM", idMukim == null ? "" : idMukim);
			sql = r.getSQLInsert("TBLPDTPELAN");
			stmt.executeUpdate(sql);

			conn.commit();
		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getData(String idWarta) throws Exception {
		Vector data = new Vector();
		if(idWarta == ""){
			return data;
		}
		Db db = null;
		data.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_NEGERI, A.ID_DAERAH, C.KAWASAN, C.ID_MUKIM, B.ID_TBLPDTWARTA, B.NO_WARTA, TO_CHAR(B.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA, C.ID_TBLPDTPELAN, C.NO_PELAN, C.LUAS, B.FLAG_STATUSWARTA,"
					+ "A.ID_TBLPDTWARTAGANTI, A.ID_TBLPDTTANAHRIZABMELAYU, B.FLAG_JENISWARTA, A.ID_TBLPDTWARTAASAL, B.KANDUNGAN AS KANDUNGAN_WARTA, C.KANDUNGAN AS KANDUNGAN_PELAN, B.FLAG_STATUSWARTA , S.NAMA_MUKIM "
					+ "FROM tblpdttanahrizabmelayu A, tblpdtwarta B, tblpdtpelan C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM S "
					+ "WHERE B.ID_TBLPDTWARTA = A.ID_TBLPDTWARTA "
					+ "AND D.ID_NEGERI = A.ID_NEGERI "
					+ "AND E.ID_DAERAH = A.ID_DAERAH "
					+ "AND S.ID_MUKIM = C.ID_MUKIM "
					+ "AND C.ID_TBLPDTWARTA = B.ID_TBLPDTWARTA "
					+ "AND B.FLAG_JENISWARTA ='W' "
					+ "AND B.ID_TBLPDTWARTA = "
					+ Long.parseLong(idWarta);
			sql += "ORDER BY B.ID_TBLPDTWARTA ";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				Blob  b = rs.getBlob("KANDUNGAN_WARTA");
				Blob  c = rs.getBlob("KANDUNGAN_PELAN");
				h.put("KANDUNGAN_WARTA", b == null ? false : true);
				h.put("KANDUNGAN_PELAN", c == null ? false : true);
				h.put("ID_TBLPDTWARTA", rs.getString("ID_TBLPDTWARTA") == null ? "" : rs.getString("ID_TBLPDTWARTA"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("NAMA_NEGERI", rs.getString("ID_NEGERI") == null ? "" : getNegeri(rs.getString("ID_NEGERI")));
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("NAMA_DAERAH", rs.getString("ID_DAERAH") == null ? "" : getDaerah(rs.getString("ID_DAERAH")));
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				h.put("NAMA_MUKIM", rs.getString("ID_MUKIM") == null ? "" : getMukim(rs.getString("ID_MUKIM")));
				h.put("KAWASAN", rs.getString("KAWASAN") == null ? "" : rs.getString("KAWASAN"));
				h.put("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("ID_TBLPDTTANAHRIZABMELAYU", rs.getString("ID_TBLPDTTANAHRIZABMELAYU") == null ? "" : rs.getString("ID_TBLPDTTANAHRIZABMELAYU"));
				h.put("ID_TBLPDTWARTAGANTI", rs.getString("ID_TBLPDTWARTAGANTI") == null ? "" : rs.getString("ID_TBLPDTWARTAGANTI"));
				h.put("ID_TBLPDTWARTAASAL", rs.getString("ID_TBLPDTWARTAASAL") == null ? "" : rs.getString("ID_TBLPDTWARTAASAL"));
				/*
				 * h.put("KANDUNGAN", rs.getString("KANDUNGAN") == null ? "" :
				 * rs.getString("KANDUNGAN"));
				 */h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? "" : rs.getString("TARIKH_WARTA"));
				 h.put("ID_TBLPDTPELAN", rs.getString("ID_TBLPDTPELAN") == null ? "" : rs.getString("ID_TBLPDTPELAN"));
				h.put("NO_PELAN", rs.getString("NO_PELAN") == null ? "" : rs.getString("NO_PELAN"));
				h.put("LUAS", rs.getString("LUAS") == null ? "" : rs.getString("LUAS"));
				h.put("FLAG_STATUSWARTA", rs.getString("FLAG_STATUSWARTA") == null ? "" : rs.getString("FLAG_STATUSWARTA"));

				data.addElement(h);
			}
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null)
				db.close();
		}
		return data;
	}

	public Vector getDataBatal(String idWarta) throws Exception {
		Vector data = new Vector();
		if(idWarta == ""){
			return data;
		}
		Db db = null;
		data.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "select A.ID_NEGERI,A.ID_DAERAH, C.KAWASAN, C.ID_MUKIM, B.ID_TBLPDTWARTA,B.NO_WARTA,TO_CHAR(B.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA, "
					+ "C.ID_TBLPDTPELAN, C.NO_PELAN, C.LUAS, B.FLAG_STATUSWARTA, A.ID_TBLPDTWARTAGANTI,B.FLAG_JENISWARTA, A.ID_TBLPDTWARTAASAL, "
					+ "B.KANDUNGAN AS KANDUNGAN_WARTA, C.KANDUNGAN AS KANDUNGAN_PELAN , B.FLAG_STATUSWARTA, B.NAMA_FAIL, C.NAMA_FAIL, A.ID_TBLPDTTANAHRIZABMELAYU "
					+ "FROM tblpdttanahrizabmelayu A, tblpdtwarta B, tblpdtpelan C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM S "
					+ "where B.ID_TBLPDTWARTA = A.ID_TBLPDTWARTA "
					+ "AND D.ID_NEGERI = A.ID_NEGERI "
					+ "AND E.ID_DAERAH = A.ID_DAERAH "
					+ "AND S.ID_MUKIM = C.ID_MUKIM "
					+ "AND C.ID_TBLPDTWARTA = B.ID_TBLPDTWARTA "
					+ "AND B.FLAG_JENISWARTA ='B' "
					+ "and A.ID_TBLPDTWARTA = "
					+ Long.parseLong(idWarta);
			sql += "ORDER BY B.ID_TBLPDTWARTA ";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				Blob  b = rs.getBlob("KANDUNGAN_WARTA");
				Blob  c = rs.getBlob("KANDUNGAN_PELAN");
				h.put("KANDUNGAN_WARTA", b == null ? false : true);
				h.put("KANDUNGAN_PELAN", c == null ? false : true);
				h.put("ID_TBLPDTWARTA", rs.getString("ID_TBLPDTWARTA") == null ? "" : rs.getString("ID_TBLPDTWARTA"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("NAMA_NEGERI", rs.getString("ID_NEGERI") == null ? "" : getNegeri(rs.getString("ID_NEGERI")));
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("NAMA_DAERAH", rs.getString("ID_DAERAH") == null ? "" : getDaerah(rs.getString("ID_DAERAH")));
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				h.put("NAMA_MUKIM", rs.getString("ID_MUKIM") == null ? "" : getMukim(rs.getString("ID_MUKIM")));
				h.put("KAWASAN", rs.getString("KAWASAN") == null ? "" : rs.getString("KAWASAN"));
				h.put("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? "" : rs.getString("TARIKH_WARTA"));
				h.put("ID_TBLPDTPELAN", rs.getString("ID_TBLPDTPELAN") == null ? "" : rs.getString("ID_TBLPDTPELAN"));
				h.put("NO_PELAN", rs.getString("NO_PELAN") == null ? "" : rs.getString("NO_PELAN"));
				h.put("LUAS", rs.getString("LUAS") == null ? "" : rs.getString("LUAS"));
				h.put("FLAG_STATUSWARTA", rs.getString("FLAG_STATUSWARTA") == null ? "" : rs.getString("FLAG_STATUSWARTA"));
				h.put("ID_TBLPDTTANAHRIZABMELAYU", rs.getString("ID_TBLPDTTANAHRIZABMELAYU") == null ? "" : rs.getString("ID_TBLPDTTANAHRIZABMELAYU"));
				h.put("ID_TBLPDTWARTAGANTI", rs.getString("ID_TBLPDTWARTAGANTI") == null ? "" : rs.getString("ID_TBLPDTWARTAGANTI"));
				h.put("ID_TBLPDTWARTAASAL", rs.getString("ID_TBLPDTWARTAASAL") == null ? "" : rs.getString("ID_TBLPDTWARTAASAL"));
				data.addElement(h);
			}
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null)
				db.close();
		}
		return data;
	}
	
	
	public Vector getDataBatalAsal(String idWarta) throws Exception {
		Vector data = new Vector();
		if(idWarta == ""){
			return data;
		}
		Db db = null;
		data.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "select A.ID_NEGERI,A.ID_DAERAH, C.KAWASAN, C.ID_MUKIM, B.ID_TBLPDTWARTA,B.NO_WARTA,TO_CHAR(B.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA, "
					+ "C.ID_TBLPDTPELAN, C.NO_PELAN, C.LUAS, B.FLAG_STATUSWARTA, A.ID_TBLPDTWARTAGANTI,B.FLAG_JENISWARTA, A.ID_TBLPDTWARTAASAL, "
					+ "B.KANDUNGAN AS KANDUNGAN_WARTA, C.KANDUNGAN AS KANDUNGAN_PELAN , B.FLAG_STATUSWARTA, B.NAMA_FAIL, C.NAMA_FAIL, A.ID_TBLPDTTANAHRIZABMELAYU "
					+ "FROM tblpdttanahrizabmelayu A, tblpdtwarta B, tblpdtpelan C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM S "
					+ "where B.ID_TBLPDTWARTA = A.ID_TBLPDTWARTA "
					+ "AND D.ID_NEGERI = A.ID_NEGERI "
					+ "AND E.ID_DAERAH = A.ID_DAERAH "
					+ "AND S.ID_MUKIM = C.ID_MUKIM "
					+ "AND C.ID_TBLPDTWARTA = B.ID_TBLPDTWARTA "
					+ "AND B.FLAG_JENISWARTA ='B' "
					+ "and A.ID_TBLPDTWARTAASAL = "
					+ Long.parseLong(idWarta);
			sql += "ORDER BY B.ID_TBLPDTWARTA ";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				Blob  b = rs.getBlob("KANDUNGAN_WARTA");
				Blob  c = rs.getBlob("KANDUNGAN_PELAN");
				h.put("KANDUNGAN_WARTA", b == null ? false : true);
				h.put("KANDUNGAN_PELAN", c == null ? false : true);
				h.put("ID_TBLPDTWARTA", rs.getString("ID_TBLPDTWARTA") == null ? "" : rs.getString("ID_TBLPDTWARTA"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("NAMA_NEGERI", rs.getString("ID_NEGERI") == null ? "" : getNegeri(rs.getString("ID_NEGERI")));
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("NAMA_DAERAH", rs.getString("ID_DAERAH") == null ? "" : getDaerah(rs.getString("ID_DAERAH")));
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				h.put("NAMA_MUKIM", rs.getString("ID_MUKIM") == null ? "" : getMukim(rs.getString("ID_MUKIM")));
				h.put("KAWASAN", rs.getString("KAWASAN") == null ? "" : rs.getString("KAWASAN"));
				h.put("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? "" : rs.getString("TARIKH_WARTA"));
				h.put("ID_TBLPDTPELAN", rs.getString("ID_TBLPDTPELAN") == null ? "" : rs.getString("ID_TBLPDTPELAN"));
				h.put("NO_PELAN", rs.getString("NO_PELAN") == null ? "" : rs.getString("NO_PELAN"));
				h.put("LUAS", rs.getString("LUAS") == null ? "" : rs.getString("LUAS"));
				h.put("FLAG_STATUSWARTA", rs.getString("FLAG_STATUSWARTA") == null ? "" : rs.getString("FLAG_STATUSWARTA"));
				h.put("ID_TBLPDTTANAHRIZABMELAYU", rs.getString("ID_TBLPDTTANAHRIZABMELAYU") == null ? "" : rs.getString("ID_TBLPDTTANAHRIZABMELAYU"));

				data.addElement(h);
			}
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null)
				db.close();
		}
		return data;
	}

	public Vector getDataGanti(String idWarta) throws Exception {
		Vector data = new Vector();
		if(idWarta == ""){
			return data;
		}
		Db db = null;
		data.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "select A.ID_NEGERI,A.ID_DAERAH, C.KAWASAN, C.ID_MUKIM, B.ID_TBLPDTWARTA ,B.NO_WARTA, TO_CHAR(B.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA, "
					+ "C.NO_PELAN, C.ID_TBLPDTPELAN, C.LUAS, B.FLAG_STATUSWARTA, A.ID_TBLPDTWARTAGANTI,B.FLAG_JENISWARTA, A.ID_TBLPDTWARTAASAL, "
					+ "B.KANDUNGAN AS KANDUNGAN_WARTA, C.KANDUNGAN AS KANDUNGAN_PELAN,B.FLAG_STATUSWARTA, B.NAMA_FAIL, C.NAMA_FAIL, A.ID_TBLPDTTANAHRIZABMELAYU "
					+ "FROM tblpdttanahrizabmelayu A, tblpdtwarta B, tblpdtpelan C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM S "
					+ "WHERE A.ID_TBLPDTWARTA = B.ID_TBLPDTWARTA "
					+ "AND A.ID_NEGERI = D.ID_NEGERI "
					+ "AND A.ID_DAERAH = E.ID_DAERAH "
					+ "AND B.ID_TBLPDTWARTA = C.ID_TBLPDTWARTA "
					+ "AND C.ID_MUKIM = S.ID_MUKIM "
					+ "AND C.ID_TBLPDTWARTA = B.ID_TBLPDTWARTA "
					+ "AND B.FLAG_JENISWARTA='W' "
					+ "AND A.ID_TBLPDTWARTA = "+idWarta;
			sql += "ORDER BY B.ID_TBLPDTWARTA ";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				Blob  b = rs.getBlob("KANDUNGAN_WARTA");
				Blob  c = rs.getBlob("KANDUNGAN_PELAN");
				h.put("KANDUNGAN_WARTA", b == null ? false : true);
				h.put("KANDUNGAN_PELAN", c == null ? false : true);
				h.put("ID_TBLPDTWARTA", rs.getString("ID_TBLPDTWARTA") == null ? "" : rs.getString("ID_TBLPDTWARTA"));
				h.put("ID_TBLPDTPELAN", rs.getString("ID_TBLPDTPELAN") == null ? "" : rs.getString("ID_TBLPDTPELAN"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				h.put("KAWASAN", rs.getString("KAWASAN") == null ? "" : rs.getString("KAWASAN"));
				h.put("NAMA_MUKIM", rs.getString("ID_MUKIM") == null ? "" : getMukim(rs.getString("ID_MUKIM")));
				h.put("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? "" : rs.getString("TARIKH_WARTA"));
				h.put("NO_PELAN", rs.getString("NO_PELAN") == null ? "" : rs.getString("NO_PELAN"));
				h.put("LUAS", rs.getString("LUAS") == null ? "" : rs.getString("LUAS"));
				h.put("FLAG_STATUSWARTA", rs.getString("FLAG_STATUSWARTA") == null ? "" : rs.getString("FLAG_STATUSWARTA"));
				h.put("ID_TBLPDTTANAHRIZABMELAYU", rs.getString("ID_TBLPDTTANAHRIZABMELAYU") == null ? "" : rs.getString("ID_TBLPDTTANAHRIZABMELAYU"));
				data.addElement(h);
			}
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null)
				db.close();
		}
		return data;
	}
	
	public Vector getDataGantiByIdAsal(String idWarta) throws Exception {
		Vector data = new Vector();
		if(idWarta == ""){
			return data;
		}
		Db db = null;
		data.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "select A.ID_NEGERI,A.ID_DAERAH, C.KAWASAN, C.ID_MUKIM, B.ID_TBLPDTWARTA ,B.NO_WARTA, TO_CHAR(B.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA, "
					+ "C.NO_PELAN, C.ID_TBLPDTPELAN, C.LUAS, B.FLAG_STATUSWARTA, A.ID_TBLPDTWARTAGANTI,B.FLAG_JENISWARTA, A.ID_TBLPDTWARTAASAL, "
					+ "B.KANDUNGAN AS KANDUNGAN_WARTA, C.KANDUNGAN AS KANDUNGAN_PELAN,B.FLAG_STATUSWARTA, B.NAMA_FAIL, C.NAMA_FAIL, A.ID_TBLPDTTANAHRIZABMELAYU "
					+ "FROM tblpdttanahrizabmelayu A, tblpdtwarta B, tblpdtpelan C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM S "
					+ "WHERE A.ID_TBLPDTWARTA = B.ID_TBLPDTWARTA "
					+ "AND A.ID_NEGERI = D.ID_NEGERI "
					+ "AND A.ID_DAERAH = E.ID_DAERAH "
					+ "AND B.ID_TBLPDTWARTA = C.ID_TBLPDTWARTA "
					+ "AND C.ID_MUKIM = S.ID_MUKIM "
					+ "AND C.ID_TBLPDTWARTA = B.ID_TBLPDTWARTA "
					+ "AND B.FLAG_JENISWARTA='W' "
					+ "AND A.ID_TBLPDTWARTAGANTI = "+idWarta;
			sql += "ORDER BY B.ID_TBLPDTWARTA ";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				Blob  b = rs.getBlob("KANDUNGAN_WARTA");
				Blob  c = rs.getBlob("KANDUNGAN_PELAN");
				h.put("KANDUNGAN_WARTA", b == null ? false : true);
				h.put("KANDUNGAN_PELAN", c == null ? false : true);
				h.put("ID_TBLPDTWARTA", rs.getString("ID_TBLPDTWARTA") == null ? "" : rs.getString("ID_TBLPDTWARTA"));
				h.put("ID_TBLPDTPELAN", rs.getString("ID_TBLPDTPELAN") == null ? "" : rs.getString("ID_TBLPDTPELAN"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				h.put("KAWASAN", rs.getString("KAWASAN") == null ? "" : rs.getString("KAWASAN"));
				h.put("NAMA_MUKIM", rs.getString("ID_MUKIM") == null ? "" : getMukim(rs.getString("ID_MUKIM")));
				h.put("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? "" : rs.getString("TARIKH_WARTA"));
				h.put("NO_PELAN", rs.getString("NO_PELAN") == null ? "" : rs.getString("NO_PELAN"));
				h.put("LUAS", rs.getString("LUAS") == null ? "" : rs.getString("LUAS"));
				h.put("FLAG_STATUSWARTA", rs.getString("FLAG_STATUSWARTA") == null ? "" : rs.getString("FLAG_STATUSWARTA"));
				h.put("ID_TBLPDTTANAHRIZABMELAYU", rs.getString("ID_TBLPDTTANAHRIZABMELAYU") == null ? "" : rs.getString("ID_TBLPDTTANAHRIZABMELAYU"));
				data.addElement(h);
			}
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null)
				db.close();
		}
		return data;
	}

	public Vector getDataMaklumat(String idWarta) throws Exception {
		Vector data = new Vector();

		Db db = null;
		data.clear();
		Vector v = new Vector();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			int iCount = 1;
			String RS_IdNegeri = "", RS_IdDaerah = "", RS_NoWarta = "", RS_JenisWarta = "", RS_Status = "", RS_WartaAsal = "", RS_WartaGanti = "", RS_TarikhWarta = "", RS_NoPelan = "", RS_IdMukim = "", RS_Luas = "";
			sql = "SELECT E.NAMA_DAERAH, B.NO_WARTA, DECODE (B.FLAG_JENISWARTA,'W','Warta','B','Batal') AS JENIS_WARTA, "
					+ "DECODE (B.FLAG_STATUSWARTA,'T','Tidak Kuatkuasa','K','Kuatkuasa')  AS STATUS, F.NO_WARTA as NO_WARTA_ASAL, "
					+ "G.NO_WARTA as NO_WARTA_GANTI, TO_CHAR (B.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA, "
					+ "C.NO_PELAN, S.NAMA_MUKIM, C.LUAS "
					+ "FROM   tblpdttanahrizabmelayu A, tblpdtwarta B, tblpdtpelan C, TBLRUJNEGERI D, TBLRUJDAERAH E,TBLRUJMUKIM S, tblpdtwarta F,tblpdtwarta G "
					+ "WHERE D.ID_NEGERI = A.ID_NEGERI " + "AND E.ID_DAERAH = A.ID_DAERAH " + "AND S.ID_MUKIM = C.ID_MUKIM "
					+ "AND C.ID_TBLPDTWARTA = B.ID_TBLPDTWARTA " + "AND A.ID_TBLPDTWARTA = B.ID_TBLPDTWARTA "
					+ "AND A.ID_TBLPDTWARTAASAL = F.ID_TBLPDTWARTA(+) " + "AND A.ID_TBLPDTWARTAGANTI = G.ID_TBLPDTWARTA(+) ";
			sql += " ORDER BY A.ID_NEGERI ";
			myLogger.info("sql ::" + sql);
			ResultSet rs1 = stmt.executeQuery(sql);
			Hashtable h1;

			while (rs1.next()) {
				RS_IdNegeri = rs1.getString(1) == null ? "" : rs1.getString(1);
				RS_IdDaerah = rs1.getString(1) == null ? "" : rs1.getString(1);
				RS_NoWarta = rs1.getString(2) == null ? "" : rs1.getString(2);
				RS_JenisWarta = rs1.getString(3) == null ? "" : rs1.getString(3);
				RS_Status = rs1.getString(4) == null ? "" : rs1.getString(4);
				RS_WartaAsal = rs1.getString(5) == null ? "" : rs1.getString(5);
				RS_WartaGanti = rs1.getString(6) == null ? "" : rs1.getString(6);
				RS_TarikhWarta = rs1.getString(7) == null ? "" : rs1.getString(7);
				RS_NoPelan = rs1.getString(8) == null ? "" : rs1.getString(8);
				RS_IdMukim = rs1.getString(9) == null ? "" : rs1.getString(9);
				RS_Luas = rs1.getString(10) == null ? "" : rs1.getString(10);

				h1 = new Hashtable();
				h1.put("Bil", iCount);
				h1.put("Daerah", RS_IdDaerah);
				h1.put("NoWarta", RS_NoWarta);
				h1.put("JenisWarta", RS_JenisWarta);
				h1.put("Status", RS_Status);
				h1.put("NoWartaAsal", RS_WartaAsal);
				h1.put("NoWartaGanti", RS_WartaGanti);
				h1.put("TarikhWarta", RS_TarikhWarta);
				h1.put("NoPelan", RS_NoPelan);
				h1.put("Mukim", RS_IdMukim);
				h1.put("Luas", RS_Luas);

				v.addElement(h1);
				iCount++;
			}
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}

	public String getNegeri(String idnegeri) {
		Vector v = null;
		Tblrujnegeri n = null;
		try {
			v = new Vector();
			v = DB.getNegeri(idnegeri);
			n = (Tblrujnegeri) v.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n.getNamaNegeri();
	}

	public String getDaerah(String idDaerah) {
		Vector v = null;
		Tblrujdaerah n = null;
		try {
			v = new Vector();
			v = DB.getDaerah(idDaerah);
			n = (Tblrujdaerah) v.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n.getNamaDaerah();
	}

	public String getMukim(String idMukim) {
		Vector v = null;
		Tblrujmukim n = null;
		try {
			v = new Vector();
			v = DB.getMukim(idMukim);
			n = (Tblrujmukim) v.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n.getNamaMukim();
	}

	public boolean saveUploadExcel(List<FrmUploadExcelForm> list, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// load data daerah ke list
			ResultSet rs = null;

			String RS_Daerah = "", RS_Id = "";
			String sqlDaerah = "SELECT DISTINCT NAMA_DAERAH, ID_DAERAH FROM TBLRUJDAERAH";
			Hashtable hDaerah = new Hashtable();
			rs = stmt.executeQuery(sqlDaerah);
			while (rs.next()) {
				RS_Daerah = rs.getString(1) == null ? "" : rs.getString(1);
				RS_Id = rs.getString(2) == null ? "" : rs.getString(2);
				RS_Id = RS_Id.replace(" ", "");
				hDaerah.put(RS_Daerah, RS_Id);
			}

			// load data mukim ke list
			ResultSet rsMukim = null;
			String RS_Mukim = "", RS_IdMukim = "";
			String sqlMukim = "SELECT DISTINCT NAMA_MUKIM, ID_MUKIM FROM TBLRUJMUKIM";
			Hashtable hMukim = new Hashtable();
			rsMukim = stmt.executeQuery(sqlMukim);
			
			List idMukims = new ArrayList<>();
			List mukims = new ArrayList<>();
			
			while (rsMukim.next()) {
				RS_Mukim = rsMukim.getString(1) == null ? "" : rsMukim.getString(1);
				RS_IdMukim = rsMukim.getString(2) == null ? "" : rsMukim.getString(2);
				
//				idMukims.add(RS_IdMukim);
//				mukims.add(RS_Mukim);
				hMukim.put(RS_Mukim, RS_IdMukim);
			}

			// load flagjeniswarta
			Hashtable hFlagjeniswarta = new Hashtable();
			hFlagjeniswarta.put("pewujudan", "W");
			hFlagjeniswarta.put("pembatalan", "B");

			// load flagstatuswarta
			Hashtable hFlagstatuswarta = new Hashtable();
			hFlagstatuswarta.put("tidak kuatkuasa", "T");
			hFlagstatuswarta.put("kuatkuasa", "K");
			
			// load idPdtwarta
			Hashtable hIdsPdtwarta = new Hashtable();
			//checking insert same no warta
			List listNoWartaCheckDuplicate = new ArrayList<>();

			for (int i = 0; i < list.size(); i++) {
				FrmUploadExcelForm form = (FrmUploadExcelForm) list.get(i);
				
				if(!listNoWartaCheckDuplicate.contains(form.getNoWarta())){
					// TBLPDTWARTA
					long idTblpdtwarta = DB.getNextID("TBLPDTWARTA_SEQ");
					r = new SQLRenderer();

					r.add("ID_TBLPDTWARTA", idTblpdtwarta);
					r.add("NO_WARTA", form.getNoWarta());
					if (!form.getTarikhWarta().isEmpty()) {
						String TW = "to_date('" + formatDate(form.getTarikhWarta()) + "','dd/MM/yyyy')";
						r.add("TARIKH_WARTA", r.unquote(TW));
					}
					r.add("FLAG_JENISWARTA", hFlagjeniswarta.get(form.getFlagJeniswarta().toLowerCase()));
					r.add("FLAG_STATUSWARTA", hFlagstatuswarta.get(form.getFlagStatuswarta().toLowerCase()));
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
					r.add("MASUK_OLEH", userId);

					sql = r.getSQLInsert("TBLPDTWARTA");
					myLogger.info("add TBLPDTWARTA:" + sql);
					stmt.executeUpdate(sql);
					form.setIdTblpdtwarta(idTblpdtwarta+"");
					hIdsPdtwarta.put(form.getNoWarta(), idTblpdtwarta);
					listNoWartaCheckDuplicate.add(form.getNoWarta());
				}else{
					form.setIdTblpdtwarta(hIdsPdtwarta.get(form.getNoWarta())+"");
				}
				
				list.set(i, form);
			}

			for (int j = 0; j < list.size(); j++) {
				FrmUploadExcelForm form = (FrmUploadExcelForm) list.get(j);

				// TBLPDTTANAHRIZABMELAYU
				sql = "";
				r = new SQLRenderer();
				long idTblpdttanahrizabmelayu = DB.getNextID("TBLPDTTANAHRIZABMELAYU_SEQ");

				r.add("ID_TBLPDTTANAHRIZABMELAYU", idTblpdttanahrizabmelayu);
				r.add("ID_DAERAH", form.getDaerah()==null?"":hDaerah.get(form.getDaerah().toUpperCase().replace(" ", "")));
				r.add("ID_NEGERI", form.getIdNegeri()==null?"":form.getIdNegeri());
				r.add("ID_TBLPDTWARTA", form.getIdTblpdtwarta());
				r.add("ID_TBLPDTWARTAASAL", form.getNoWartaasal()==null?"":hIdsPdtwarta.get(form.getNoWartaasal()));
				r.add("ID_TBLPDTWARTAGANTI", form.getNoWartaganti()==null?"":hIdsPdtwarta.get(form.getNoWartaganti()));
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("MASUK_OLEH", userId);

				sql = r.getSQLInsert("TBLPDTTANAHRIZABMELAYU");
				myLogger.info("add TBLPDTTANAHRIZABMELAYU:" + sql);
				stmt.executeUpdate(sql);
				form.setIdTblpdttanahrizabmelayu(idTblpdttanahrizabmelayu + "");
//				list.set(j, form);
				
				// TBLPDTPELAN
				sql = "";
				r = new SQLRenderer();
				long idTblpdtpelan = DB.getNextID("TBLPDTPELAN_SEQ");

//				System.out.println("hMukim.get(form.getMukim().toUpperCase()):"+hMukim.get(form.getMukim().toUpperCase()));
				
				r.add("ID_TBLPDTPELAN", idTblpdtpelan);
				r.add("ID_TBLPDTWARTA", form.getIdTblpdtwarta());
				
//				int a = mukims.indexOf(form.getMukim().toUpperCase());
				
//				System.out.println("hMukim.get(form.getMukim().toUpperCase()):"+a);
//				r.add("ID_MUKIM",  form.getMukim()==null?"":idMukims.get(a));
				r.add("ID_MUKIM",  form.getMukim()==null?"":hMukim.get(form.getMukim().toUpperCase()));
				
				
				r.add("NO_PELAN", form.getNoPelan()==null?"":form.getNoPelan());
				r.add("KAWASAN", form.getKawasan()==null?"":form.getKawasan());
				r.add("LUAS", form.getLuas()==null?"":form.getLuas());
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("MASUK_OLEH", userId);

				sql = r.getSQLInsert("TBLPDTPELAN");
				myLogger.info("add TBLPDTPELAN:" + sql);
				stmt.executeUpdate(sql);
				
				form.setIdTblpdtpelan(idTblpdtpelan + "");
				list.set(j, form);
			}

			conn.commit();
		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}

		return false;
	}

	private Date parseDate(String dateTxt) throws java.text.ParseException {
		if (dateTxt != null && !"".equals(dateTxt)) {
			try {
				return new SimpleDateFormat("dd-MM-yyyy").parse(dateTxt);
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	private String formatDate(String dateTxt) {
		if (dateTxt != null && !"".equals(dateTxt)) {
			try {
				SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
				SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
				Date date = format.parse(dateTxt);
				return new SimpleDateFormat("dd-MM-yyyy").format(date);
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	public Long[] saveWartaPewujudan(String paramNegeri, String paramDaerah,
			String paramMukim, String paramKawasan, String paramNoWarta,
			String paramTarikh, String paramNoPelan, String paramLuas,
			String paramStatus,HttpSession session) throws DbException {
		Db db = new Db();
		Long ID_TBLPDTWARTA = null,ID_TBLPDTPELAN = null;
		try {
			
			/*Save table TBLPDTWARTA*/
			String userId = (String) session.getAttribute("_ekptg_user_id");
			java.util.Date today = new java.util.Date();
			Connection con = db.getConnection();
	    	con.setAutoCommit(false);
			PreparedStatement ps;
			ID_TBLPDTWARTA = DB.getNextID("TBLPDTWARTA_SEQ");
			ps = con.prepareStatement("insert into TBLPDTWARTA " +
					"(ID_TBLPDTWARTA,NO_WARTA,TARIKH_WARTA,FLAG_JENISWARTA,FLAG_STATUSWARTA,NAMA_FAIL,TARIKH_MASUK,MASUK_OLEH) " +
					"values(?,?,?,?,?,?,?,?)");
			ps.setLong(1, ID_TBLPDTWARTA);
			ps.setString(2, paramNoWarta);
			SimpleDateFormat sdfSource = new SimpleDateFormat("dd/MM/yyyy");
			Date date =  sdfSource.parse(paramTarikh);
			ps.setDate(3, new java.sql.Date(date.getTime()));
			ps.setString(4, "W");
			ps.setString(5, paramStatus);
			ps.setString(6, "");
			ps.setDate(7, new java.sql.Date(today.getTime()));
			ps.setString(8, userId);
			ps.executeUpdate();
	        con.commit();
	        
	        /*Save table TBLPDTWARTA*/
	        Connection con1 = db.getConnection();
	    	con1.setAutoCommit(false);
			PreparedStatement ps1;
			ID_TBLPDTPELAN = DB.getNextID("TBLPDTPELAN_SEQ");
			ps1 = con1.prepareStatement("insert into TBLPDTPELAN " +
					"(ID_TBLPDTPELAN,ID_TBLPDTWARTA,ID_MUKIM,NO_PELAN,KAWASAN,LUAS,TARIKH_MASUK,MASUK_OLEH) " +
					"values(?,?,?,?,?,?,?,?)");
			ps1.setLong(1, ID_TBLPDTPELAN);
			ps1.setLong(2, ID_TBLPDTWARTA);
			ps1.setLong(3, Long.parseLong(paramMukim));
			ps1.setString(4, paramNoPelan);
			ps1.setString(5, paramKawasan);
			ps1.setString(6, paramLuas);
			ps1.setDate(7, new java.sql.Date(today.getTime()));
			ps1.setString(8, userId);
			ps1.executeUpdate();
			con1.commit();
	        
	        /*Save table TBLPDTTANAHRIZABMELAYU*/
	        Connection con2 = db.getConnection();
	        con2.setAutoCommit(false);
			PreparedStatement ps2;
			Long ID_TBLPDTTANAHRIZABMELAYU = DB.getNextID("TBLPDTTANAHRIZABMELAYU_SEQ");
			ps2 = con2.prepareStatement("insert into TBLPDTTANAHRIZABMELAYU " +
					"(ID_TBLPDTTANAHRIZABMELAYU,ID_DAERAH,ID_NEGERI,ID_TBLPDTWARTA,TARIKH_MASUK,MASUK_OLEH) " +
					"values(?,?,?,?,?,?)");
			ps2.setLong(1, ID_TBLPDTTANAHRIZABMELAYU);
			ps2.setLong(2, Long.parseLong(paramDaerah));
			ps2.setLong(3, Long.parseLong(paramNegeri));
			ps2.setLong(4, ID_TBLPDTWARTA);
			ps2.setDate(5, new java.sql.Date(today.getTime()));
			ps2.setString(6, userId);
			ps2.executeUpdate();
			con2.commit();
	        
		} catch (Exception e) {
			myLogger.error(e.getStackTrace());
			db.close();
		}
		 Long[] retVals = { ID_TBLPDTWARTA, ID_TBLPDTPELAN };
		myLogger.info("paramNegeri="+paramNegeri+"\nparamDaerah="+paramDaerah+"\nparamMukim="+paramMukim+"\nparamKawasan="+paramKawasan+"\nparamNoWarta="+paramNoWarta+"\nparamTarikh="+paramTarikh+"\nparamNoPelan"+paramNoPelan+"\nparamLuas="+paramLuas+"\nparamStatus="+paramStatus);
		 return retVals;
	}
	
	public Long[] saveWartaPenggantian(String idWarta,String paramNegeri, String paramDaerah,
			String paramMukim, String paramKawasan, String paramNoWarta,
			String paramTarikh, String paramNoPelan, String paramLuas,
			String paramStatus,HttpSession session) throws DbException {
		Db db = new Db();
		Long ID_TBLPDTWARTA = null,ID_TBLPDTPELAN = null;
		try {
			
			/*Save table TBLPDTWARTA*/
			String userId = (String) session.getAttribute("_ekptg_user_id");
			java.util.Date today = new java.util.Date();
			Connection con = db.getConnection();
	    	con.setAutoCommit(false);
			PreparedStatement ps;
			ID_TBLPDTWARTA = DB.getNextID("TBLPDTWARTA_SEQ");
			ps = con.prepareStatement("insert into TBLPDTWARTA " +
					"(ID_TBLPDTWARTA,NO_WARTA,TARIKH_WARTA,FLAG_JENISWARTA,FLAG_STATUSWARTA,NAMA_FAIL,TARIKH_MASUK,MASUK_OLEH) " +
					"values(?,?,?,?,?,?,?,?)");
			ps.setLong(1, ID_TBLPDTWARTA);
			ps.setString(2, paramNoWarta);
			SimpleDateFormat sdfSource = new SimpleDateFormat("dd/MM/yyyy");
			Date date =  sdfSource.parse(paramTarikh);
			ps.setDate(3, new java.sql.Date(date.getTime()));
			ps.setString(4, "W");
			ps.setString(5, paramStatus);
			ps.setString(6, "");
			ps.setDate(7, new java.sql.Date(today.getTime()));
			ps.setString(8, userId);
			ps.executeUpdate();
	        con.commit();
	        
	        /*Save table TBLPDTWARTA*/
	        Connection con1 = db.getConnection();
	    	con1.setAutoCommit(false);
			PreparedStatement ps1;
			ID_TBLPDTPELAN = DB.getNextID("TBLPDTPELAN_SEQ");
			ps1 = con1.prepareStatement("insert into TBLPDTPELAN " +
					"(ID_TBLPDTPELAN,ID_TBLPDTWARTA,ID_MUKIM,NO_PELAN,KAWASAN,LUAS,TARIKH_MASUK,MASUK_OLEH) " +
					"values(?,?,?,?,?,?,?,?)");
			ps1.setLong(1, ID_TBLPDTPELAN);
			ps1.setLong(2, ID_TBLPDTWARTA);
			ps1.setLong(3, Long.parseLong(paramMukim));
			ps1.setString(4, paramNoPelan);
			ps1.setString(5, paramKawasan);
			ps1.setString(6, paramLuas);
			ps1.setDate(7, new java.sql.Date(today.getTime()));
			ps1.setString(8, userId);
			ps1.executeUpdate();
			con1.commit();
	        
	        /*Save table TBLPDTTANAHRIZABMELAYU*/
	        Connection con2 = db.getConnection();
	        con2.setAutoCommit(false);
			PreparedStatement ps2;
			Long ID_TBLPDTTANAHRIZABMELAYU = DB.getNextID("TBLPDTTANAHRIZABMELAYU_SEQ");
			ps2 = con2.prepareStatement("insert into TBLPDTTANAHRIZABMELAYU " +
					"(ID_TBLPDTTANAHRIZABMELAYU,ID_DAERAH,ID_NEGERI,ID_TBLPDTWARTA,TARIKH_MASUK,MASUK_OLEH, ID_TBLPDTWARTAGANTI) " +
					"values(?,?,?,?,?,?,?)");
			ps2.setLong(1, ID_TBLPDTTANAHRIZABMELAYU);
			ps2.setLong(2, Long.parseLong(paramDaerah));
			ps2.setLong(3, Long.parseLong(paramNegeri));
			ps2.setLong(4, ID_TBLPDTWARTA);
			ps2.setDate(5, new java.sql.Date(today.getTime()));
			ps2.setString(6, userId);
			ps2.setLong(7, Long.parseLong(idWarta));
			ps2.executeUpdate();
			con2.commit();
	        
		} catch (Exception e) {
			myLogger.info(e.getMessage());
			db.close();
		}
		 Long[] retVals = { ID_TBLPDTWARTA, ID_TBLPDTPELAN };
		myLogger.info("paramNegeri="+paramNegeri+"\nparamDaerah="+paramDaerah+"\nparamMukim="+paramMukim+"\nparamKawasan="+paramKawasan+"\nparamNoWarta="+paramNoWarta+"\nparamTarikh="+paramTarikh+"\nparamNoPelan"+paramNoPelan+"\nparamLuas="+paramLuas+"\nparamStatus="+paramStatus);
		 return retVals;
	}

	public void saveFileWarta(FileItem item, String idWartaNew,HttpSession session) throws IOException {
		String userId = (String) session.getAttribute("_ekptg_user_id");
		Hashtable h = new Hashtable();
		h.put("itemInputStream",item.getInputStream());
		h.put("size",item.getSize());
		h.put("fileName",item.getName());
		try {
			Db db = new Db();
			java.util.Date today = new java.util.Date();
			Connection con = db.getConnection();
	    	con.setAutoCommit(false);
	    	PreparedStatement ps;
	    	ps = con.prepareStatement("UPDATE TBLPDTWARTA SET KANDUNGAN = ?, TARIKH_KEMASKINI = ?, KEMASKINI_OLEH = ?, NAMA_FAIL = ?  WHERE ID_TBLPDTWARTA = "+idWartaNew+"");
	    	ps.setBinaryStream(1, (InputStream) h.get("itemInputStream"),Integer.valueOf(h.get("size").toString()));
	    	ps.setDate(2, new java.sql.Date(today.getTime()));
	    	ps.setString(3, userId);
	    	ps.setString(4, h.get("fileName").toString());
	    	ps.executeUpdate();
	        con.commit();
		} catch (Exception e) {
			myLogger.info(e.getMessage());
		}
	}

	public void saveFilePelan(FileItem item, String idPelanNew, HttpSession session) throws IOException {
		String userId = (String) session.getAttribute("_ekptg_user_id");
		Hashtable h = new Hashtable();
		h.put("itemInputStream",item.getInputStream());
		h.put("size",item.getSize());
		h.put("fileName",item.getName());
		try {
			Db db = new Db();
			java.util.Date today = new java.util.Date();
			Connection con = db.getConnection();
	    	con.setAutoCommit(false);
	    	PreparedStatement ps;
	    	ps = con.prepareStatement("UPDATE TBLPDTPELAN SET KANDUNGAN = ?, TARIKH_KEMASKINI = ?, KEMASKINI_OLEH = ?, NAMA_FAIL = ?  WHERE ID_TBLPDTPELAN = "+idPelanNew+"");
	    	ps.setBinaryStream(1, (InputStream) h.get("itemInputStream"),Integer.valueOf(h.get("size").toString()));
	    	ps.setDate(2, new java.sql.Date(today.getTime()));
	    	ps.setString(3, userId);
	    	ps.setString(4, h.get("fileName").toString());
	    	ps.executeUpdate();
	        con.commit();
		} catch (Exception e) {
			myLogger.info(e.getMessage());
		}
	}

	public Long[] saveWartaBatal(String idWarta,String paramNoWarta, String paramTarikh,
			String paramNoPelan, String paramLuas, HttpSession session) throws DbException {
		Db db = new Db();
		Long ID_TBLPDTWARTA = null,ID_TBLPDTPELAN = null;
		try {
			Long idWartaAsal = null,idNegeri = null,idDaerah = null,idMukim = null;
			Vector data = getData(idWarta);
			for (Object object : data) {
				Hashtable hashHeader = (Hashtable) object;
				 idWartaAsal = hashHeader.get("ID_TBLPDTWARTA") == "" ? null : Long.parseLong(hashHeader.get("ID_TBLPDTWARTA").toString());
				 idNegeri = hashHeader.get("ID_NEGERI") == "" ? null : Long.parseLong(hashHeader.get("ID_NEGERI").toString());
				 idDaerah = hashHeader.get("ID_DAERAH") == "" ? null : Long.parseLong(hashHeader.get("ID_DAERAH").toString());
				 idMukim = hashHeader.get("ID_MUKIM") == "" ? null : Long.parseLong(hashHeader.get("ID_MUKIM").toString());
			}
			
			
			/*Save table TBLPDTWARTA*/
			String userId = (String) session.getAttribute("_ekptg_user_id");
			java.util.Date today = new java.util.Date();
			Connection con = db.getConnection();
	    	con.setAutoCommit(false);
			PreparedStatement ps;
			ID_TBLPDTWARTA = DB.getNextID("TBLPDTWARTA_SEQ");
			ps = con.prepareStatement("insert into TBLPDTWARTA " +
					"(ID_TBLPDTWARTA,NO_WARTA,TARIKH_WARTA,FLAG_JENISWARTA,FLAG_STATUSWARTA,TARIKH_MASUK,MASUK_OLEH) " +
					"values(?,?,?,?,?,?,?)");
			ps.setLong(1, ID_TBLPDTWARTA);
			ps.setString(2, paramNoWarta);
			SimpleDateFormat sdfSource = new SimpleDateFormat("dd/MM/yyyy");
			Date date =  sdfSource.parse(paramTarikh);
			ps.setDate(3, new java.sql.Date(date.getTime()));
			ps.setString(4, "B");
			ps.setString(5, "K");
			ps.setDate(6, new java.sql.Date(today.getTime()));
			ps.setString(7, userId);
			ps.executeUpdate();
	        con.commit();
	        
	        /*Save table TBLPDTWARTA*/
	        Connection con1 = db.getConnection();
	    	con1.setAutoCommit(false);
			PreparedStatement ps1;
			ID_TBLPDTPELAN = DB.getNextID("TBLPDTPELAN_SEQ");
			ps1 = con1.prepareStatement("insert into TBLPDTPELAN " +
					"(ID_TBLPDTPELAN,ID_TBLPDTWARTA,NO_PELAN,LUAS,TARIKH_MASUK,MASUK_OLEH,ID_MUKIM) " +
					"values(?,?,?,?,?,?,?)");
			ps1.setLong(1, ID_TBLPDTPELAN);
			ps1.setLong(2, ID_TBLPDTWARTA);
			ps1.setString(3, paramNoPelan);
			ps1.setString(4, paramLuas);
			ps1.setDate(5, new java.sql.Date(today.getTime()));
			ps1.setString(6, userId);
			ps1.setLong(7, idMukim);
			ps1.executeUpdate();
			con1.commit();
	        
	        /*Save table TBLPDTTANAHRIZABMELAYU*/
	        Connection con2 = db.getConnection();
	        con2.setAutoCommit(false);
			PreparedStatement ps2;
			Long ID_TBLPDTTANAHRIZABMELAYU = DB.getNextID("TBLPDTTANAHRIZABMELAYU_SEQ");
			ps2 = con2.prepareStatement("insert into TBLPDTTANAHRIZABMELAYU " +
					"(ID_TBLPDTTANAHRIZABMELAYU,ID_TBLPDTWARTA,ID_TBLPDTWARTAASAL,TARIKH_MASUK,MASUK_OLEH,ID_DAERAH,ID_NEGERI) " +
					"values(?,?,?,?,?,?,?)");
			ps2.setLong(1, ID_TBLPDTTANAHRIZABMELAYU);
			ps2.setLong(2, ID_TBLPDTWARTA);
			ps2.setLong(3, idWartaAsal);
			ps2.setDate(4, new java.sql.Date(today.getTime()));
			ps2.setString(5, userId);
			ps2.setLong(6, idDaerah);
			ps2.setLong(7, idNegeri);
			ps2.executeUpdate();
			con2.commit();
	        
		} catch (Exception e) {
			myLogger.info(e.getMessage());
			db.close();
		}
		 Long[] retVals = { ID_TBLPDTWARTA, ID_TBLPDTPELAN };
		myLogger.info("paramNoWarta="+paramNoWarta+"\nparamTarikh="+paramTarikh+"\nparamNoPelan"+paramNoPelan+"\nparamLuas="+paramLuas);
		 return retVals;
	
	}

	public void updateWartaPewujudan(String idWarta,String idPelan, String idTnhRzbMly, String paramNegeri,
			String paramDaerah, String paramMukim, String paramKawasan,
			String paramNoWarta, String paramTarikh, String paramNoPelan,
			String paramLuas, String paramStatus, HttpSession session) throws DbException {

		Db db = new Db();
		try {
			
			/*Update table TBLPDTWARTA*/
			String userId = (String) session.getAttribute("_ekptg_user_id");
			java.util.Date today = new java.util.Date();
			Connection con = db.getConnection();
	    	con.setAutoCommit(false);
			PreparedStatement ps;
			ps = con.prepareStatement("UPDATE TBLPDTWARTA SET "
					+ "NO_WARTA = ?, TARIKH_WARTA = ?, FLAG_JENISWARTA = ?, FLAG_STATUSWARTA = ?, "
					+ "NAMA_FAIL = ?, TARIKH_KEMASKINI = ?, KEMASKINI_OLEH = ? "
					+ "WHERE ID_TBLPDTWARTA = "+idWarta);
			ps.setString(1, paramNoWarta);
			SimpleDateFormat sdfSource = new SimpleDateFormat("dd/MM/yyyy");
			Date date =  sdfSource.parse(paramTarikh);
			ps.setDate(2, new java.sql.Date(date.getTime()));
			ps.setString(3, "W");
			ps.setString(4, paramStatus);
			ps.setString(5, "");
			ps.setDate(6, new java.sql.Date(today.getTime()));
			ps.setString(7, userId);
			ps.executeUpdate();
	        con.commit();
	        
	        /*Update table TBLPDTWARTA*/
	        Connection con1 = db.getConnection();
	    	con1.setAutoCommit(false);
			PreparedStatement ps1;
			ps1 = con1.prepareStatement("UPDATE TBLPDTPELAN SET "
					+ "ID_TBLPDTWARTA = ?, ID_MUKIM = ?, NO_PELAN = ?, KAWASAN = ?, LUAS = ?, "
					+ "TARIKH_KEMASKINI = ?, KEMASKINI_OLEH = ? WHERE ID_TBLPDTPELAN = "+idPelan);
			ps1.setLong(1, Long.parseLong(idWarta));
			ps1.setLong(2, Long.parseLong(paramMukim));
			ps1.setString(3, paramNoPelan);
			ps1.setString(4, paramKawasan);
			ps1.setString(5, paramLuas);
			ps1.setDate(6, new java.sql.Date(today.getTime()));
			ps1.setString(7, userId);
			ps1.executeUpdate();
			con1.commit();
	        
	        /*Update table TBLPDTTANAHRIZABMELAYU*/
	        Connection con2 = db.getConnection();
	        con2.setAutoCommit(false);
			PreparedStatement ps2;
			ps2 = con2.prepareStatement("UPDATE TBLPDTTANAHRIZABMELAYU SET "
					+ "ID_DAERAH = ?, ID_NEGERI = ?, ID_TBLPDTWARTA = ?, TARIKH_KEMASKINI = ?, "
					+ "KEMASKINI_OLEH = ? WHERE ID_TBLPDTTANAHRIZABMELAYU = "+idTnhRzbMly);
			ps2.setLong(1, Long.parseLong(paramDaerah));
			ps2.setLong(2, Long.parseLong(paramNegeri));
			ps2.setLong(3, Long.parseLong(idWarta));
			ps2.setDate(4, new java.sql.Date(today.getTime()));
			ps2.setString(5, userId);
			ps2.executeUpdate();
			con2.commit();
	        
		} catch (Exception e) {
			myLogger.info(e.getMessage());
			db.close();
		}
		myLogger.info("paramNegeri="+paramNegeri+"\nparamDaerah="+paramDaerah+"\nparamMukim="+paramMukim+"\nparamKawasan="+paramKawasan+"\nparamNoWarta="+paramNoWarta+"\nparamTarikh="+paramTarikh+"\nparamNoPelan"+paramNoPelan+"\nparamLuas="+paramLuas+"\nparamStatus="+paramStatus);
		
	}

	public void updateWartaBatal(String idWarta, String idPelan,
			String idTnhRzbMly, String paramNegeri, String paramDaerah,
			String paramMukim, String paramKawasan, String paramNoWarta,
			String paramTarikh, String paramNoPelan, String paramLuas,
			String paramStatus, HttpSession session) throws DbException {
		Db db = new Db();
		Long ID_TBLPDTWARTA = null,ID_TBLPDTPELAN = null;
		try {
			
			/*Save table TBLPDTWARTA*/
			String userId = (String) session.getAttribute("_ekptg_user_id");
			java.util.Date today = new java.util.Date();
			Connection con = db.getConnection();
	    	con.setAutoCommit(false);
			PreparedStatement ps;
			ps = con.prepareStatement("UPDATE TBLPDTWARTA SET "
					+ "NO_WARTA = ?, TARIKH_WARTA = ?, FLAG_JENISWARTA = ?, FLAG_STATUSWARTA = ?, "
					+ "TARIKH_KEMASKINI = ? , KEMASKINI_OLEH = ? WHERE ID_TBLPDTWARTA = "+idWarta);
			ps.setString(1, paramNoWarta);
			SimpleDateFormat sdfSource = new SimpleDateFormat("dd/MM/yyyy");
			Date date =  sdfSource.parse(paramTarikh);
			ps.setDate(2, new java.sql.Date(date.getTime()));
			ps.setString(3, "B");
			ps.setString(4, "K");
			ps.setDate(5, new java.sql.Date(today.getTime()));
			ps.setString(6, userId);
			ps.executeUpdate();
	        con.commit();
	        
	        /*Save table TBLPDTWARTA*/
	        Connection con1 = db.getConnection();
	    	con1.setAutoCommit(false);
			PreparedStatement ps1;
			ps1 = con1.prepareStatement("UPDATE TBLPDTPELAN SET "
					+ "ID_TBLPDTWARTA = ?, NO_PELAN = ?, LUAS = ?, TARIKH_MASUK = ?, MASUK_OLEH = ? "
					+ "WHERE ID_TBLPDTPELAN ="+idPelan);
			ps1.setLong(1, ID_TBLPDTWARTA);
			ps1.setString(2, paramNoPelan);
			ps1.setString(3, paramLuas);
			ps1.setDate(4, new java.sql.Date(today.getTime()));
			ps1.setString(5, userId);
			ps1.executeUpdate();
			con1.commit();
	        
	        /*Save table TBLPDTTANAHRIZABMELAYU*/
	        Connection con2 = db.getConnection();
	        con2.setAutoCommit(false);
			PreparedStatement ps2;
			ps2 = con2.prepareStatement("UPDATE TBLPDTTANAHRIZABMELAYU SET "
					+ "ID_TBLPDTWARTA = ?, ID_TBLPDTWARTAASAL = ?, TARIKH_MASUK = ?, MASUK_OLEH = ? "
					+ "WHERE ID_TBLPDTTANAHRIZABMELAYU = "+idTnhRzbMly);
			ps2.setLong(1, ID_TBLPDTWARTA);
			ps2.setLong(2, ID_TBLPDTWARTA);
			ps2.setDate(3, new java.sql.Date(today.getTime()));
			ps2.setString(4, userId);
			ps2.executeUpdate();
			con2.commit();
	        
		} catch (Exception e) {
			myLogger.info(e.getMessage());
			db.close();
		}
		myLogger.info("paramNoWarta="+paramNoWarta+"\nparamTarikh="+paramTarikh+"\nparamNoPelan"+paramNoPelan+"\nparamLuas="+paramLuas);
	
	}

	public Vector findDataTblPdtMuatNaikTrm() throws DbException {
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		Statement stmt = db.getStatement();
		String sql = "";
		int iCount = 1;
		try {
			sql = "SELECT A.ID_MUATNAIKTRM, A.ID_NEGERI, A.NAMA_FAIL, A.TARIKH_MASUK, B.NAMA_NEGERI "
					+ "FROM TBLPDTMUATNAIKTRM A, TBLRUJNEGERI B WHERE A.ID_NEGERI = B.ID_NEGERI";
			myLogger.info("sql ::" + sql);
			ResultSet rs1 = stmt.executeQuery(sql);
			Hashtable h1;
			while (rs1.next()) {
				h1 = new Hashtable();
				h1.put("Bil", iCount);
				h1.put("idMuatNaikTrm", rs1.getString(1) == null ? "" : rs1.getString(1));
				h1.put("idNegeri", rs1.getString(2) == null ? "" : rs1.getString(2));
				h1.put("namaFail", rs1.getString(3) == null ? "" : rs1.getString(3));
				SimpleDateFormat sdfSource = new SimpleDateFormat("dd/MM/yyyy");
				h1.put("tarikhMasuk", sdfSource.format(rs1.getDate(4)));
				h1.put("namaNegeri", rs1.getString(5) == null ? "" : rs1.getString(5));
				v.addElement(h1);
				iCount++;
			}
		} catch (Exception e) {
			myLogger.info(e.getMessage());
			db.close();
		}
		return v;
	}

	public void saveTblPdtMuatNaikTrm(String namaFail, String idNegeri,HttpSession session) throws DbException {
		Db db = new Db();
		try {
			/*Save table TBLPDTMUATNAIKTRM*/
			String userId = (String) session.getAttribute("_ekptg_user_id");
			java.util.Date today = new java.util.Date();
			Connection con = db.getConnection();
	    	con.setAutoCommit(false);
			PreparedStatement ps;
			Long ID_MUATNAIKTRM = DB.getNextID("TBLPDTMUATNAIKTRM_SEQ");
			ps = con.prepareStatement("INSERT INTO TBLPDTMUATNAIKTRM " +
					"(ID_MUATNAIKTRM,ID_NEGERI,NAMA_FAIL,TARIKH_MASUK,MASUK_OLEH) " +
					"values(?,?,?,?,?)");
			ps.setLong(1, ID_MUATNAIKTRM);
			ps.setLong(2, Long.parseLong(idNegeri));
			ps.setString(3, namaFail);
			ps.setDate(4, new java.sql.Date(today.getTime()));
			ps.setString(5, userId);
			ps.executeUpdate();
	        con.commit();
		} catch (Exception e) {
			myLogger.info(e.getMessage());
			db.close();
		}
	}

	public Vector getDataExcel(String idNegeri) throws DbException {
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		Statement stmt = db.getStatement();
		String sql = "";
		int iCount = 1;
		try {
			sql = "SELECT D.NAMA_DAERAH,B.NO_WARTA,B.FLAG_JENISWARTA,B.FLAG_STATUSWARTA, "
					+ "G.NO_WARTA,H.NO_WARTA,B.TARIKH_WARTA, "
					+ "C.NO_PELAN,E.NAMA_MUKIM,C.LUAS,F.NAMA_NEGERI "
					+ "FROM TBLPDTTANAHRIZABMELAYU A, TBLPDTWARTA B, TBLPDTPELAN C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLRUJNEGERI F, TBLPDTWARTA G, TBLPDTWARTA H "
					+ "WHERE B.ID_TBLPDTWARTA = A.ID_TBLPDTWARTA "
					+ "AND C.ID_MUKIM = E.ID_MUKIM "
					+ "AND A.ID_DAERAH = D.ID_DAERAH "
					+ "AND C.ID_TBLPDTWARTA = B.ID_TBLPDTWARTA "
					+ "AND A.ID_NEGERI = "+idNegeri+" "
					+ "AND F.ID_NEGERI = A.ID_NEGERI "
					+ "AND A.ID_TBLPDTWARTAASAL = G.ID_TBLPDTWARTA (+)"
					+ "AND A.ID_TBLPDTWARTAGANTI = H.ID_TBLPDTWARTA (+)";
			myLogger.info("sql ::" + sql);
			ResultSet rs1 = stmt.executeQuery(sql);
			Hashtable h1;
			while (rs1.next()) {
				h1 = new Hashtable();
				h1.put("Bil", iCount);
				h1.put("namaDaerah", rs1.getString(1) == null ? "" : rs1.getString(1));
				h1.put("noWarta", rs1.getString(2) == null ? "" : rs1.getString(2));
				
				if(!rs1.getString(3).equals(null) && !rs1.getString(3).equals("B")){
					h1.put("flagJenisWarta","Warta Pembatalan");
				}else{
					h1.put("flagJenisWarta", "Warta Pewujudan");
				}
				
				if(!rs1.getString(3).equals(null) && !rs1.getString(4).equals("K")){
					h1.put("flagStatusWarta","Kuatkuasa");
				}else{
					h1.put("flagStatusWarta", "Tidak");
				}
				
				h1.put("idTblPdtWartaAsal", rs1.getString(5) == null ? "" : rs1.getString(5));
				h1.put("idTblPdtWartaGanti", rs1.getString(6) == null ? "" : rs1.getString(6));
				SimpleDateFormat sdfSource = new SimpleDateFormat("dd/MM/yyyy");
				h1.put("tarikhWarta", sdfSource.format(rs1.getDate(7)));
				h1.put("noPelan", rs1.getString(8) == null ? "" : rs1.getString(8));
				h1.put("namaMukim", rs1.getString(9) == null ? "" : rs1.getString(9));
				h1.put("luas", rs1.getString(10) == null ? "" : rs1.getString(10));
				h1.put("namaNegeri", rs1.getString(11) == null ? "" : rs1.getString(11));
				v.addElement(h1);
				iCount++;
			}
		} catch (Exception e) {
			myLogger.info(e.getMessage());
			db.close();
		}
		return v;
	}

	public Vector getDataByIdAsalNGanti(String idWarta) throws Exception {

		Vector data = new Vector();
		if(idWarta == ""){
			return data;
		}
		Db db = null;
		data.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_NEGERI, A.ID_DAERAH, C.KAWASAN, C.ID_MUKIM, B.ID_TBLPDTWARTA, B.NO_WARTA, TO_CHAR(B.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA, C.ID_TBLPDTPELAN, C.NO_PELAN, C.LUAS, B.FLAG_STATUSWARTA,"
					+ "A.ID_TBLPDTWARTAGANTI, A.ID_TBLPDTTANAHRIZABMELAYU, B.FLAG_JENISWARTA, A.ID_TBLPDTWARTAASAL, B.KANDUNGAN AS KANDUNGAN_WARTA, C.KANDUNGAN AS KANDUNGAN_PELAN, B.FLAG_STATUSWARTA , S.NAMA_MUKIM "
					+ "FROM tblpdttanahrizabmelayu A, tblpdtwarta B, tblpdtpelan C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM S "
					+ "WHERE B.ID_TBLPDTWARTA = A.ID_TBLPDTWARTA "
					+ "AND D.ID_NEGERI = A.ID_NEGERI "
					+ "AND E.ID_DAERAH = A.ID_DAERAH "
					+ "AND S.ID_MUKIM = C.ID_MUKIM "
					+ "AND C.ID_TBLPDTWARTA = B.ID_TBLPDTWARTA "
					+ "AND B.FLAG_JENISWARTA ='W' "
					+ "AND B.ID_TBLPDTWARTA = "
					+ Long.parseLong(idWarta);
			sql += "ORDER BY B.ID_TBLPDTWARTA ";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				Blob  b = rs.getBlob("KANDUNGAN_WARTA");
				Blob  c = rs.getBlob("KANDUNGAN_PELAN");
				h.put("KANDUNGAN_WARTA", b == null ? false : true);
				h.put("KANDUNGAN_PELAN", c == null ? false : true);
				h.put("ID_TBLPDTWARTA", rs.getString("ID_TBLPDTWARTA") == null ? "" : rs.getString("ID_TBLPDTWARTA"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("NAMA_NEGERI", rs.getString("ID_NEGERI") == null ? "" : getNegeri(rs.getString("ID_NEGERI")));
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("NAMA_DAERAH", rs.getString("ID_DAERAH") == null ? "" : getDaerah(rs.getString("ID_DAERAH")));
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				h.put("NAMA_MUKIM", rs.getString("ID_MUKIM") == null ? "" : getMukim(rs.getString("ID_MUKIM")));
				h.put("KAWASAN", rs.getString("KAWASAN") == null ? "" : rs.getString("KAWASAN"));
				h.put("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("ID_TBLPDTTANAHRIZABMELAYU", rs.getString("ID_TBLPDTTANAHRIZABMELAYU") == null ? "" : rs.getString("ID_TBLPDTTANAHRIZABMELAYU"));
				/*
				 * h.put("KANDUNGAN", rs.getString("KANDUNGAN") == null ? "" :
				 * rs.getString("KANDUNGAN"));
				 */h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? "" : rs.getString("TARIKH_WARTA"));
				 h.put("ID_TBLPDTPELAN", rs.getString("ID_TBLPDTPELAN") == null ? "" : rs.getString("ID_TBLPDTPELAN"));
				h.put("NO_PELAN", rs.getString("NO_PELAN") == null ? "" : rs.getString("NO_PELAN"));
				h.put("LUAS", rs.getString("LUAS") == null ? "" : rs.getString("LUAS"));
				h.put("FLAG_STATUSWARTA", rs.getString("FLAG_STATUSWARTA") == null ? "" : rs.getString("FLAG_STATUSWARTA"));

				data.addElement(h);
			}
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null)
				db.close();
		}
		return data;
	
	}

}
