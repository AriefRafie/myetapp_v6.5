package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmPerletakhakanHakmilikBaruData {
	
	private static Vector maklumatFailMaster = null;
	private static Vector maklumatSenaraiHakmilik = null;
	private static Vector infoPekmilikbaru = null;
	private static Vector maklumatSenarai = null;
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	// set maklumat fail master
	public void setMaklumatFailMaster(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			maklumatFailMaster = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, A.NO_FAIL, B.ID_PERMOHONAN,B.TUJUAN,C.TARIKH_AGIHAN,C.NO_RUJUKAN_KJP,C.NO_RUJUKAN_LAIN,A.ID_NEGERI,D.NAMA_KEMENTERIAN,F.NAMA_AGENSI,G.NAMA_SUBURUSAN,A.ID_SUBURUSAN,A.TARIKH_DAFTAR_FAIL"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B,TBLHTPPERMOHONAN C, TBLRUJKEMENTERIAN D, TBLRUJAGENSI F, TBLRUJSUBURUSAN G "
				+ "WHERE A.ID_FAIL = B.ID_FAIL(+) AND B.ID_PERMOHONAN = C.ID_PERMOHONAN(+) "
				+ "AND A.ID_KEMENTERIAN = D.ID_KEMENTERIAN(+) "
				+ "AND C.ID_AGENSI = F.ID_AGENSI(+)"
				+ "AND A.ID_SUBURUSAN = G.ID_SUBURUSAN(+)"
				+ "AND A.ID_FAIL = '" + idFail + "'";
			System.out.println("sql:"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL").toUpperCase());
				h.put("txtNoFailSeksyen", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());
				h.put("txtTajuk", rs.getString("TUJUAN") == null ? "" : rs.getString("TUJUAN").toUpperCase());
				h.put("txdTarikhSurKJP", rs.getDate("TARIKH_AGIHAN") == null ? "" : Format.format(rs.getDate("TARIKH_AGIHAN")));
				h.put("txtNoFailKJP", rs.getString("NO_RUJUKAN_KJP") == null ? "" : rs.getString("NO_RUJUKAN_KJP").toUpperCase());
				h.put("txtNoFailLain", rs.getString("NO_RUJUKAN_LAIN") == null ? "" : rs.getString("NO_RUJUKAN_LAIN").toUpperCase());
				h.put("id_negeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase());
				h.put("txtNamaKementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("txtNamaAgensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				h.put("txtNamaSuburusan", rs.getString("NAMA_SUBURUSAN") == null ? "" : rs.getString("NAMA_SUBURUSAN").toUpperCase());
				h.put("txdTarikhBukaFail", rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				

				maklumatFailMaster.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
		
	}
	
	public Vector getMaklumatFailMaster() {
		return maklumatFailMaster;
	}
	
	// set list senarai hakmilik
	public void setMaklumatSenaraiHakmilik(String idFail) throws Exception {
		Db db = null;
		String sql = "";
		

		try {
			maklumatSenaraiHakmilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_HAKMILIKURUSAN, A.NO_HAKMILIK, A.ID_NEGERI, E.KETERANGAN, A.ID_KATEGORI, "+
				  "B.ID_PERMOHONAN, D.NAMA_NEGERI, E.ID_FAIL "+
		      "FROM TBLHTPHAKMILIKURUSAN A, TBLPERMOHONAN B, TBLHTPPERMOHONAN C,TBLPFDFAIL E, "+
		      "TBLRUJNEGERI D , TBLRUJJENISHAKMILIK E "+       
		      "WHERE E.ID_FAIL = B.ID_FAIL "+
		      "AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "+
		      "AND A.ID_NEGERI = D.ID_NEGERI "+
		      "AND A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
		      "AND E.ID_JENISHAKMILIK = A.ID_JENISHAKMILIK "+
		      "AND E.ID_FAIL ="+idFail;


			  
			System.out.println("sqlhakmilik:"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil",bil);
				h.put("noHakmilik",rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
				h.put("negeri",rs.getString("NAMA_NEGERI")== null?"":rs.getString("NAMA_NEGERI"));
				h.put("jenisHakmilik",rs.getString("KETERANGAN") == null?"":rs.getString("KETERANGAN").toUpperCase());
				h.put("kategori",rs.getString("ID_KATEGORI") == null?"":rs.getString("ID_KATEGORI"));
				h.put("idPermohonan",rs.getString("ID_PERMOHONAN"));
				h.put("idHakmilikurusan",rs.getString("ID_HAKMILIKURUSAN"));
				maklumatSenaraiHakmilik.addElement(h);
				bil++;
				count++;
			}
			if(count==0){
				h = new Hashtable();
				h.put("bil","");
				h.put("noHakmilik","Tiada Rekod.");
				h.put("negeri","");
				h.put("jenisHakmilik","");
				h.put("kategori","");
				h.put("idPermohonan","");
				h.put("idHakmilikurusan","");
				maklumatSenaraiHakmilik.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}

	}
	// return list senarai
	public Vector getMaklumatSenaraiHakmilik() {	
		return maklumatSenaraiHakmilik;
		}
	
	//SIMPAN HAKMILIK
	public String simpanBaru(String idHakmilikurusan,String txtNamaAsal, String txtAlamat1,String txtAlamat2,String txtAlamat3,String txtNamaBaru, 
			String txtPoskod,String idDaerah, String idMukim, String idNegeri, HttpSession session) throws Exception {
	
	Db db = null;
	Connection conn = null;
	String userId = session.getAttribute("_ekptg_user_id").toString();
	String sql = "";
	String idLetakhakpemilikbaruString = "";
	try {
		db = new Db();
		conn = db.getConnection();
    	conn.setAutoCommit(false);
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();
		
		//TBLHTPLETAKHAKPEMILIKBARU
		
		 long idLetakhakpemilikbaru = DB.getNextID("TBLHTPLETAKHAKPEMILIKBARU_SEQ");
		    idLetakhakpemilikbaruString = String.valueOf(idLetakhakpemilikbaru);
		    r.add("ID_LETAKHAKPEMILIKBARU",idLetakhakpemilikbaru);
		    r.add("ID_HAKMILIKURUSAN",idHakmilikurusan);
		    r.add("NAMA_ASAL",txtNamaAsal);
		    r.add("ALAMAT",txtAlamat1);
		    r.add("ALAMAT1",txtAlamat2);
		    r.add("ALAMAT2",txtAlamat3);
		    r.add("POSKOD",txtPoskod);
		    r.add("ID_DAERAH",idDaerah);
		    r.add("ID_MUKIM", idMukim);
		    r.add("ID_NEGERI", idNegeri);
		    r.add("NAMA_BARU",txtNamaBaru);
		    
		    sql = r.getSQLInsert("TBLHTPLETAKHAKPEMILIKBARU");
		    System.out.println("letakhakbaru:"+sql);
		    stmt.executeUpdate(sql);
		
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
		return idLetakhakpemilikbaruString;
		}
	//set maklumat hakmilik by idHakmilikurusan
	public void setInfoPemilikbaru(String idHakmilikurusan) throws Exception {
		Db db = null;
		String sql = "";
		

		try {
			infoPekmilikbaru = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_LETAKHAKPEMILIKBARU, A.ID_HAKMILIKURUSAN,A.ID_DAERAH,A.ID_MUKIM,A.ID_NEGERI,A.NAMA_ASAL,A.ALAMAT,A.ALAMAT1,A.ALAMAT2,A.POSKOD,A.NAMA_BARU,B.ID_PERMOHONAN,E.ID_FAIL "+
				  "FROM TBLHTPLETAKHAKPEMILIKBARU A,TBLHTPHAKMILIKURUSAN B, TBLHTPPERMOHONAN C, TBLPERMOHONAN D, TBLPFDFAIL E "+       
				  "WHERE E.ID_FAIL = D.ID_FAIL "+
				  "AND C.ID_PERMOHONAN = D.ID_PERMOHONAN "+
				  "AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "+
				  "AND A.ID_HAKMILIKURUSAN = B.ID_HAKMILIKURUSAN "+
				  "AND A.ID_HAKMILIKURUSAN ="+idHakmilikurusan;


			  
			System.out.println("sqlhakxx:"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			
			while (rs.next()) {
				h = new Hashtable();
				
				h.put("ID_LETAKHAKPEMILIKBARU",rs.getString("ID_LETAKHAKPEMILIKBARU")== null?"":rs.getString("ID_LETAKHAKPEMILIKBARU"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI")== null?"":rs.getString("ID_NEGERI"));
				h.put("ID_DAERAH",rs.getString("ID_DAERAH")== null?"":rs.getString("ID_DAERAH"));
				h.put("ID_MUKIM",rs.getString("ID_MUKIM")== null?"":rs.getString("ID_MUKIM"));
				h.put("NAMA_ASAL",rs.getString("NAMA_ASAL")== null?"":rs.getString("NAMA_ASAL").toUpperCase());
				h.put("ALAMAT", rs.getString("ALAMAT")== null ? "" : rs.getString("ALAMAT").toUpperCase());
				h.put("ALAMAT1",rs.getString("ALAMAT1")== null?"":rs.getString("ALAMAT1").toUpperCase());
				h.put("ALAMAT2", rs.getString("ALAMAT2")== null? "" : rs.getString("ALAMAT2").toUpperCase());
				h.put("NAMA_BARU",rs.getString("NAMA_BARU")== null?"":rs.getString("NAMA_BARU").toUpperCase());
				h.put("POSKOD",rs.getString("POSKOD") == null?"":rs.getString("POSKOD"));
				h.put("idPermohonan",rs.getString("ID_PERMOHONAN"));
				h.put("idHakmilikurusan",rs.getString("ID_HAKMILIKURUSAN"));
				infoPekmilikbaru.addElement(h);
				bil++;

			}


		} finally {
			if (db != null)
				db.close();
		}

	}
	// return list senarai
	public Vector getInfoPemilikbaru() {	
		return infoPekmilikbaru;
		}
	
	// set list senarai
	public void setMaklumatSenarai() throws Exception {
		Db db = null;
		String sql = "";
		

		try {
			maklumatSenarai = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL,A.NO_FAIL,A.TARIKH_DAFTAR_FAIL,B.TUJUAN,C.KETERANGAN"+
			  " FROM TBLPFDFAIL A,"+
			  " TBLPERMOHONAN B,"+
			  " TBLRUJSTATUS C,"+
			  " TBLHTPPERMOHONAN D"+
			  " WHERE A.ID_SEKSYEN = 3 AND A.ID_URUSAN = 5 AND A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = D.ID_PERMOHONAN" +
			  " AND C.ID_STATUS = A.ID_STATUS";
	//		System.out.println("sql:"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil",bil);
				h.put("idFail",rs.getString("ID_FAIL"));
				h.put("noFail",rs.getString("NO_FAIL")== null?"":rs.getString("NO_FAIL"));
				h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")== null?"":Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("tujuan",rs.getString("TUJUAN") == null?"":rs.getString("TUJUAN"));
				h.put("keterangan",rs.getString("KETERANGAN") == null?"":rs.getString("KETERANGAN"));

				maklumatSenarai.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}

	}
	// return list senarai
	public Vector getMaklumatSenarai() {	
		return maklumatSenarai;
		}
	//update data tblhtpletakhakpemilikbaru
	
	public void update(String idHakmilikurusan,String txtNamaAsal, String txtAlamat1,String txtAlamat2,String txtAlamat3,String txtNamaBaru, 
			String txtPoskod,String idDaerah, String idMukim, String idNegeri, HttpSession session) throws Exception {
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
			
//			SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
//			String tarikhSurKJP = "to_date('" + txdTarikhSurKJP + "','dd/MM/yyyy HH:MI:SS AM')";
			
			//TBLHTPLETAKHAKPEMILIKBARU
			
			 
			   
			    r.update("ID_HAKMILIKURUSAN",idHakmilikurusan);
			    r.add("NAMA_ASAL",txtNamaAsal);
			    r.add("ALAMAT",txtAlamat1);
			    r.add("ALAMAT1",txtAlamat2);
			    r.add("ALAMAT2",txtAlamat3);
			    r.add("POSKOD",txtPoskod);
			    r.add("ID_DAERAH",idDaerah);
			    r.add("ID_MUKIM", idMukim);
			    r.add("ID_NEGERI", idNegeri);
			    r.add("NAMA_BARU",txtNamaBaru);
			    
			    sql = r.getSQLUpdate("TBLHTPLETAKHAKPEMILIKBARU");
			    System.out.println("letakhakbaruupdate:"+sql);
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
	
	}
	

