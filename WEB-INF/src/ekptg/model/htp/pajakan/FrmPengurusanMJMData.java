package ekptg.model.htp.pajakan;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ekptg.helpers.DB;
import ekptg.model.htp.entity.TblMemoMenteriForm;


public class FrmPengurusanMJMData {

	private Db db = null;
	private Connection conn = null;
	private Vector<Hashtable<String,String>> memoData = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static final Log log = LogFactory.getLog(ekptg.model.htp.pajakan.FrmPengurusanMJMData.class);

	public Vector<Hashtable<String,String>> findBy(String txtNoMemo, String txtNoFail,
		String txdTarikh) throws Exception {
		Db db = null;
		String sql = "";
		try {
			memoData = new Vector<>();
			db = new Db();
			Statement stmt = db.getStatement();

			if(txtNoMemo.equals("") && txtNoFail.equals("") && txdTarikh.equals("")){
				sql = "SELECT A.NO_MEMO, A.NO_FAILSEKSYEN, A.CATATAN, A.STATUS, A.TARIKH,A.ID_TBLHTPMEMOMENTERI"
						+ " FROM TBLHTPMEMOMENTERI A";
			}else{
				sql = "SELECT A.NO_MEMO, A.NO_FAILSEKSYEN, A.CATATAN, A.STATUS, A.TARIKH,A.ID_TBLHTPMEMOMENTERI"
						+ " FROM TBLHTPMEMOMENTERI A WHERE ";
			}

			boolean noMemo = false, noFail = false;

			if(!txtNoMemo.equals("")){
				sql = sql + " UPPER(A.NO_MEMO) LIKE '%' ||'" + txtNoMemo.toUpperCase() + "'|| '%'";
				noMemo = true;
			}
			if(!txtNoFail.equals("")){
				if(noMemo == false){
					sql = sql + " UPPER(A.NO_FAILSEKSYEN) LIKE '%' ||'" + txtNoFail.toUpperCase() + "'|| '%'";
				}else{
					sql = sql + " AND UPPER(A.NO_FAILSEKSYEN) LIKE '%' ||'" + txtNoFail.toUpperCase() + "'|| '%'";
				}
				noFail = true;
			}

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
			if(!txdTarikh.equals("")){
				if(noMemo == false && noFail == false){
					System.out.println("date input "+txdTarikh);
					System.out.println("date sdf1 "+sdf1.format(sdf.parse(txdTarikh)).toUpperCase());
					sql = sql + " TO_CHAR(A.TARIKH,'dd-MON-YY') = '"+sdf1.format(sdf.parse(txdTarikh)).toUpperCase()+"'";
				}else{
					sql = sql + " AND TO_CHAR(A.TARIKH,'dd-MON-YY') = '"+sdf1.format(sdf.parse(txdTarikh)).toUpperCase()+"'";
				}
			}

			System.out.println("nidio : "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String,String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				System.out.println(rs.getDate("TARIKH"));
				h.put("bil", String.valueOf(bil));
				h.put("noMemo",
						rs.getString("NO_MEMO") == null ? "" : rs
								.getString("NO_MEMO"));
				h.put("noFail",
						rs.getString("NO_FAILSEKSYEN") == null ? "" : rs
								.getString("NO_FAILSEKSYEN"));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs
						.getString("CATATAN").toUpperCase());
				h.put("status", rs.getString("STATUS") == null ? "" : rs
						.getString("STATUS").toUpperCase());
				h.put("tarikh", rs.getDate("TARIKH") == null ? ""
						: sdf.format(rs.getDate("TARIKH")));
				h.put("id", rs.getString("ID_TBLHTPMEMOMENTERI") == null ? ""
						: rs.getString("ID_TBLHTPMEMOMENTERI"));
				memoData.addElement(h);
				bil++;

			}

		}catch (Exception re) {
			log.error("Error: ", re);
			throw re;

		}
		return memoData;

	}

	public Vector<Hashtable<String,String>> findBy(String txtNoMemo, String txtNoFail,
			String txdTarikh,String idCat) throws Exception {
			Db db = null;
			String sql = "";
			try {
				memoData = new Vector<>();
				db = new Db();
				Statement stmt = db.getStatement();

				if(txtNoMemo.equals("") && txtNoFail.equals("") && txdTarikh.equals("")){
					sql = "SELECT A.NO_MEMO, A.NO_FAILSEKSYEN, A.CATATAN, A.STATUS, A.TARIKH,A.ID_TBLHTPMEMOMENTERI"
							+ " FROM TBLHTPMEMOMENTERI A WHERE ID_KATEGORI IN ("+idCat+")";
				}else{
					sql = "SELECT A.NO_MEMO, A.NO_FAILSEKSYEN, A.CATATAN, A.STATUS, A.TARIKH,A.ID_TBLHTPMEMOMENTERI"
							+ " FROM TBLHTPMEMOMENTERI A WHERE ID_KATEGORI IN ("+idCat+") AND  ";
				}

				boolean noMemo = false, noFail = false;

				if(!txtNoMemo.equals("")){
					sql = sql + " UPPER(A.NO_MEMO) LIKE '%' ||'" + txtNoMemo.toUpperCase() + "'|| '%'";
					noMemo = true;
				}
				if(!txtNoFail.equals("")){
					if(noMemo == false){
						sql = sql + " UPPER(A.NO_FAILSEKSYEN) LIKE '%' ||'" + txtNoFail.toUpperCase() + "'|| '%'";
					}else{
						sql = sql + " AND UPPER(A.NO_FAILSEKSYEN) LIKE '%' ||'" + txtNoFail.toUpperCase() + "'|| '%'";
					}
					noFail = true;
				}

				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
				if(!txdTarikh.equals("")){
					if(noMemo == false && noFail == false){
						System.out.println("date input "+txdTarikh);
						System.out.println("date sdf1 "+sdf1.format(sdf.parse(txdTarikh)).toUpperCase());
						sql = sql + " TO_CHAR(A.TARIKH,'dd-MON-YY') = '"+sdf1.format(sdf.parse(txdTarikh)).toUpperCase()+"'";
					}else{
						sql = sql + " AND TO_CHAR(A.TARIKH,'dd-MON-YY') = '"+sdf1.format(sdf.parse(txdTarikh)).toUpperCase()+"'";
					}
				}

				System.out.println("nidio : "+sql);
				ResultSet rs = stmt.executeQuery(sql);

				Hashtable<String,String> h;
				int bil = 1;
				while (rs.next()) {
					h = new Hashtable<String,String>();
					//System.out.println(rs.getDate("TARIKH"));
					h.put("bil", String.valueOf(bil));
					h.put("noMemo",
							rs.getString("NO_MEMO") == null ? "" : rs
									.getString("NO_MEMO"));
					h.put("noFail",
							rs.getString("NO_FAILSEKSYEN") == null ? "" : rs
									.getString("NO_FAILSEKSYEN"));
					h.put("catatan", rs.getString("CATATAN") == null ? "" : rs
							.getString("CATATAN").toUpperCase());
					h.put("status", rs.getString("STATUS") == null ? "" : rs
							.getString("STATUS").toUpperCase());
					h.put("tarikh", rs.getDate("TARIKH") == null ? ""
							: sdf.format(rs.getDate("TARIKH")));
					h.put("id", rs.getString("ID_TBLHTPMEMOMENTERI") == null ? ""
							: rs.getString("ID_TBLHTPMEMOMENTERI"));
					memoData.addElement(h);
					bil++;

				}

			}  catch (Exception re) {
				log.error("Error: ", re);
				throw re;

			}
			return memoData;

		}

	public Long saveMemo(String txtNoMemo
		,String txtNoFail
		,String txdTarikh
		,String txtCatatan
		,String socStatus
		,String namaPemohon
		,String txtTanah
		,String txtPertimbangan
		,String txtPajakan,@SuppressWarnings("rawtypes") Vector docData
		,HttpSession session
		, String idTblMemoMenteri,int idCat) throws Exception  {
		String userId = (String) session.getAttribute("_ekptg_user_id");
		Long idMemo = null;
		try {
			Db db = new Db();
			java.util.Date today = new java.util.Date();
			Long id = idTblMemoMenteri == "" ? null : Long.parseLong(idTblMemoMenteri);
	    	long ID_TBLHTPMEMOMENTERI = DB.getNextID("TBLHTPMEMOMENTERI_SEQ");
	    	Connection con = db.getConnection();
	    	con.setAutoCommit(false);
	    	PreparedStatement ps;
	    	if(id != null){
	    		ps = con.prepareStatement("UPDATE TBLHTPMEMOMENTERI SET NO_MEMO = ?, NO_FAILSEKSYEN = ?, CATATAN = ?,TARIKH = ?, ID_KEMASKINI = ?, TARIKH_KEMASKINI = ?,STATUS = ?,NAMA_PEMOHON = ?,MAKLUMAT_TANAH = ?,ASAS_PERTIMBANGAN = ?,KADAR_PAJAKAN = ?  WHERE ID_TBLHTPMEMOMENTERI = "+id+"");
		    	ps.setString(1, txtNoMemo);
		    	ps.setString(2, txtNoFail);
		    	ps.setString(3, txtCatatan);
		    	SimpleDateFormat sdfSource = new SimpleDateFormat("dd/MM/yyyy");
		    	Date TT = sdfSource.parse(txdTarikh);
		    	ps.setDate(4, new java.sql.Date(TT.getTime()));
		    	ps.setString(5, userId);
		    	ps.setDate(6,  new java.sql.Date(today.getTime()));
		    	ps.setString(7, socStatus);
		    	ps.setString(8, namaPemohon);
		    	ps.setString(9, txtTanah);
		    	ps.setString(10, txtPertimbangan);
		    	ps.setString(11, txtPajakan);
	    	}else{
	    		idMemo = ID_TBLHTPMEMOMENTERI;
	    		ps = con.prepareStatement("INSERT INTO TBLHTPMEMOMENTERI " +
						"(ID_TBLHTPMEMOMENTERI,NO_MEMO,NO_FAILSEKSYEN,CATATAN,TARIKH,ID_MASUK,TARIKH_MASUK,STATUS,ID_KATEGORI,NAMA_PEMOHON,MAKLUMAT_TANAH,ASAS_PERTIMBANGAN,KADAR_PAJAKAN) " +
						"values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
	    		ps.setLong(1, ID_TBLHTPMEMOMENTERI);
		    	ps.setString(2, txtNoMemo);
		    	ps.setString(3, txtNoFail);
		    	ps.setString(4, txtCatatan);
		    	SimpleDateFormat sdfSource = new SimpleDateFormat("dd/MM/yyyy");
		    	Date TT = sdfSource.parse(txdTarikh);
		    	ps.setDate(5, new java.sql.Date(TT.getTime()));
		    	ps.setString(6, userId);
		    	ps.setDate(7,  new java.sql.Date(today.getTime()));
		    	ps.setString(8, socStatus);
		    	ps.setInt(9, idCat);
		    	ps.setString(10, namaPemohon);
		    	ps.setString(11, txtTanah);
		    	ps.setString(12, txtPertimbangan);
		    	ps.setString(13, txtPajakan);

	    	}

	    	ps.executeUpdate();
	        con.commit();

	        for (Object object : docData) {
	        	Hashtable hashHeader = (Hashtable) object;
	        	String blob = hashHeader.get("itemInputStream").toString();
	        	if(blob != ""){
	        		long ID_TBLHTPDOKUMENMEMO = DB.getNextID("TBLHTPDOKUMENMEMO_SEQ");
					PreparedStatement ps1;
					ps1 = con.prepareStatement("INSERT INTO TBLHTPDOKUMENMEMO " +
							"(ID_TBLHTPDOKUMENMEMO,ID_TBLHTPMEMOMENTERI,KANDUNGAN,NAMA_FAIL,ID_MASUK,TARIKH_MASUK) " +
							"values(?,?,?,?,?,?)");
					ps1.setLong(1, ID_TBLHTPDOKUMENMEMO);
					ps1.setLong(2, id != null ? id : ID_TBLHTPMEMOMENTERI);
			    	String size = hashHeader.get("size").toString();
			    	ps1.setBinaryStream(3, (InputStream) hashHeader.get("itemInputStream"),Integer.valueOf(size));
			    	ps1.setString(4, hashHeader.get("namaDokumen").toString());
			    	ps1.setString(5, userId);
			    	ps1.setDate(6, new java.sql.Date(today.getTime()));
			    	ps1.executeUpdate();
			        con.commit();

	        	}
			}


		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			idMemo = 0L;
		}
    	return idMemo;

	}

	public TblMemoMenteriForm findBy(Long id) {
		SQLRenderer r = null;
		TblMemoMenteriForm mmf = new TblMemoMenteriForm();
		try {
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			r = new SQLRenderer();
			r.add("ID_TBLHTPMEMOMENTERI");
			r.add("NO_MEMO");
			r.add("NO_FAILSEKSYEN");
			r.add("CATATAN");
			r.add("STATUS");
			r.add("TARIKH");
			r.add("ID_MASUK");
			r.add("TARIKH_MASUK");
			r.add("ID_KEMASKINI");
			r.add("TARIKH_KEMASKINI");
			r.add("ID_KATEGORI");
			r.add("NAMA_PEMOHON");
			r.add("MAKLUMAT_TANAH");
			r.add("ASAS_PERTIMBANGAN");
			r.add("KADAR_PAJAKAN");
			r.set("ID_TBLHTPMEMOMENTERI", id);
			String sql = r.getSQLSelect("TBLHTPMEMOMENTERI");
			//System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				mmf.setIdTblHtpMemoMenteri(id);
				mmf.setNoMemo(rs.getString("NO_MEMO"));
				mmf.setNoFailSeksyen(rs.getString("NO_FAILSEKSYEN"));
				mmf.setCatatan(rs.getString("CATATAN"));
				mmf.setTarikh(sdf.format(rs.getDate("TARIKH")));
				mmf.setStatus(rs.getString("STATUS"));
				mmf.setIdKategori(rs.getLong("ID_KATEGORI"));
				mmf.setNamaPemohon(rs.getString("NAMA_PEMOHON"));
				mmf.setMaklumatTanah(rs.getString("MAKLUMAT_TANAH"));
				mmf.setAsasPertimbangan(rs.getString("ASAS_PERTIMBANGAN"));
				mmf.setKadarPajakan(rs.getString("KADAR_PAJAKAN"));

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			if (db != null){
		    	  db.close();
		      }
		}
		return mmf;

	}

	public Vector<Hashtable<String,String>> findDocBy(Long idTblHtpMemoMenteri) {
		int bil = 1;
		SQLRenderer r = null;
		Vector<Hashtable<String,String>> docData =  new Vector<Hashtable<String,String>>();
		try {
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			r = new SQLRenderer();
			r.add("ID_TBLHTPDOKUMENMEMO");
			r.add("ID_TBLHTPMEMOMENTERI");
			r.add("NAMA_FAIL");
			r.add("ID_MASUK");
			r.add("TARIKH_MASUK");
			r.add("ID_KEMASKINI");
			r.add("TARIKH_KEMASKINI");
			r.set("ID_TBLHTPMEMOMENTERI", idTblHtpMemoMenteri);
			String sql = r.getSQLSelect("TBLHTPDOKUMENMEMO");
			//System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Hashtable<String,String> h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("namaDokumen",rs.getString("NAMA_FAIL") == null ? "" : rs.getString("NAMA_FAIL"));
				h.put("namaFail",rs.getString("NAMA_FAIL"));
				h.put("id",rs.getString("ID_TBLHTPDOKUMENMEMO"));
				h.put("itemInputStream","");
				docData.addElement(h);
				bil++;

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			if (db != null){
		    	  db.close();
		      }
		}
		return docData;

	}

	public void delete(String idTblDocMemo) throws Exception {
		Db db = new Db();
		if(idTblDocMemo != ""){
			try {
				Connection con = db.getConnection();
		    	con.setAutoCommit(false);
		    	PreparedStatement ps;
				ps = con.prepareStatement("DELETE TBLHTPDOKUMENMEMO WHERE ID_TBLHTPDOKUMENMEMO = ?");
				ps.setLong(1, Long.parseLong(idTblDocMemo));
				ps.executeUpdate();
		        con.commit();

			}  catch (Exception re) {
				log.error("Error: ", re);
				throw re;

			}finally{
				if (db != null){
			    	  db.close();
			      }
			}
		}

	}

	public void deleteMemo(String idMemoMenteri) throws Exception {
		Db db = new Db();
		if(idMemoMenteri != ""){
			try {
				Connection con = db.getConnection();
		    	con.setAutoCommit(false);

		    	PreparedStatement ps;
				ps = con.prepareStatement("DELETE TBLHTPDOKUMENMEMO WHERE ID_TBLHTPMEMOMENTERI = ?");
				ps.setLong(1, Long.parseLong(idMemoMenteri));
				ps.executeUpdate();

				PreparedStatement ps1;
		    	ps1 = con.prepareStatement("DELETE TBLHTPMEMOMENTERI WHERE ID_TBLHTPMEMOMENTERI = ?");
		    	ps1.setLong(1, Long.parseLong(idMemoMenteri));
		    	ps1.executeUpdate();

		    	con.commit();

			}  catch (Exception re) {
				log.error("Error: ", re);
				throw re;

			}finally{
				if (db != null){
					db.close();
				}

			}

		}

	}

	public Vector<Hashtable<String,String>> findByNoFail(String txtNoFail) throws Exception {
			Db db = null;
			String sql = "";
			try {
				memoData = new Vector<>();
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT A.ID_PEMOHON, A.ID_PERMOHONAN, A.NO_PEMOHON, A.NAMA_PEMOHON, ";
				sql += "A.ALAMAT_PEMOHON1, A.ALAMAT_PEMOHON2, A.ALAMAT_PEMOHON3, ";
				sql += "A.POSKOD, A.ID_NEGERI, A.ID_DAERAH,A.ID_BANDAR, A.NO_TEL, A.NO_FAX, A.EMEL, B.TAJUK_FAIL ";
				sql += "FROM TBLPFDFAIL B ,TBLHTPPEMOHON A ,TBLPERMOHONAN C ";
				sql += "WHERE B.ID_FAIL= C.ID_FAIL AND A.ID_PERMOHONAN = C.ID_PERMOHONAN AND B.NO_FAIL ";
				sql += "LIKE '%' ||'" + txtNoFail.toUpperCase() + "'|| '%'";
				//+ txtNoFail+ "'";

				System.out.println("nidio : "+sql);
				ResultSet rs = stmt.executeQuery(sql);

				Hashtable<String,String> h;
				int bil = 1;
				while (rs.next()) {
					h = new Hashtable<String,String>();
					//System.out.println(rs.getDate("TARIKH"));
					h.put("bil", String.valueOf(bil));
					h.put("namaPemohon",
							rs.getString("NAMA_PEMOHON") == null ? "" : rs
									.getString("NAMA_PEMOHON"));
					h.put("tajuk",
							rs.getString("TAJUK_FAIL") == null ? "" : rs
									.getString("TAJUK_FAIL"));
					/*h.put("noFail",
							rs.getString("NO_FAILSEKSYEN") == null ? "" : rs
									.getString("NO_FAILSEKSYEN"));
					h.put("catatan", rs.getString("CATATAN") == null ? "" : rs
							.getString("CATATAN").toUpperCase());
					h.put("status", rs.getString("STATUS") == null ? "" : rs
							.getString("STATUS").toUpperCase());
					h.put("tarikh", rs.getDate("TARIKH") == null ? ""
							: sdf.format(rs.getDate("TARIKH")));
					h.put("id", rs.getString("ID_TBLHTPMEMOMENTERI") == null ? ""
							: rs.getString("ID_TBLHTPMEMOMENTERI"));*/
					memoData.addElement(h);
					bil++;

				}

			}  catch (Exception re) {
				log.error("Error: ", re);
				throw re;

			}
			return memoData;

		}


}
