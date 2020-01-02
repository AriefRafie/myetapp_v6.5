package ekptg.model.htp;

import java.sql.Connection;
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

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmRekodPembangunanPentadbiranData {
	private static Logger myLog = Logger.getLogger(FrmRekodPembangunanPentadbiranData.class);

	private static Vector listPerihalById = null;
	private static Vector listPerihalByIdHakmilik = null;
	private static Vector<Hashtable<String,String>> listLuasTerkumpul = null;
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	// ADD PEMBANGUNAN BY ID HAKMILIK
	public static String addDetailKeluasan(Hashtable<String,String> data) throws Exception {
		Db db = null;
		Connection conn = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date(); 
		String currentDate = sdf.format(date);
		String sql = "";
		
		String tarikhBinaan = "to_date('"+ currentDate + "','dd/MM/yyyy')";
	    if (!("".equals((String)data.get("txdTarikhBina")))) {
	    	tarikhBinaan = "to_date('" + data.get("txdTarikhBina") + "','dd/MM/yyyy')";
	    }

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
		    long idHakmilikPerihal = DB.getNextID("TBLHTPHAKMILIKPERIHAL_SEQ"); 
		    r.add("ID_HAKMILIKPERIHAL",idHakmilikPerihal);
		    r.add("ID_HAKMILIK",data.get("idHakmilik"));
		    r.add("JENIS_BINAAN",data.get("socJenisBinaan"));
		    r.add("NO_RUJUKAN_JKR",data.get("txtNoJKR"));
		    //r.add("TARIKH_BINAAN",data.get("txdTarikhBina"));
		    r.add("TARIKH_BINAAN",r.unquote(tarikhBinaan));
		    r.add("HARGA_BINAAN",data.get("txtHarga"));
		    r.add("ID_LUAS_BERSAMAAN",2);
		    r.add("ID_LUAS",data.get("socLuas"));
		    r.add("LUAS",data.get("txtLuasLama"));
		    r.add("CATATAN",data.get("txtCatatan"));
		    r.add("ID_MASUK",data.get("idMasuk"));
		    r.add("TARIKH_MASUK",r.unquote("sysdate"));
			r.add("ID_KEMASKINI",data.get("idMasuk"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
		    if(data.get("socJenisBinaan").equals("B"))
		    	r.add("LUAS_BANGUNAN",data.get("txtLuas"));
		    else
		    if(data.get("socJenisBinaan").equals("P"))
		       r.add("LUAS_PADANG",data.get("txtLuas"));
		    else
			if(data.get("socJenisBinaan").equals("PR"))
			   r.add("LUAS_PARKING",data.get("txtLuas"));		 
		    else
			if(data.get("socJenisBinaan").equals("J"))
			   r.add("LUAS_JALAN",data.get("txtLuas"));
		    else
			if(data.get("socJenisBinaan").equals("L"))
			   r.add("LUAS_LAIN",data.get("txtLuas"));
	  
		    sql = r.getSQLInsert("TBLHTPHAKMILIKPERIHAL");
		    myLog.info("sql insert baru "+sql);
			stmt.executeUpdate(sql);
			  myLog.info("sql insert baru idHakmilikPerihal "+idHakmilikPerihal);
			conn.commit();
		    return ""+idHakmilikPerihal;
		    
		}catch (SQLException ex) { 
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
	// PAPAR MAKLUMAT PERGERAKAN BY ID
	public static Vector getMaklumatPerihalById(String idHakmilikPerihal) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			listPerihalById = new Vector();
			Statement stmt = db.getStatement();		
			sql =	"SELECT A.FLAG_KEMASKINI_STATE, A.ID_HAKMILIKPERIHAL, A.ID_HAKMILIK, A.JENIS_BINAAN,A.NO_RUJUKAN_JKR" +
					",TO_CHAR(A.TARIKH_BINAAN,'dd/mm/yyyy') TARIKH_BINAAN "+
					",A.HARGA_BINAAN, A.ID_LUAS, A.LUAS, A.LUAS_BERSAMAAN " +
					",A.LUAS_BANGUNAN, A.LUAS_JALAN, A.LUAS_PADANG "+
					",A.LUAS_PARKING, A.LUAS_LAIN, A.CATATAN "+
					"FROM TBLHTPHAKMILIKPERIHAL A, TBLHTPHAKMILIK B "+
					"WHERE A.ID_HAKMILIK = B.ID_HAKMILIK "+
					"AND A.ID_HAKMILIKPERIHAL = '"+idHakmilikPerihal+"'";			
			//myLog.info("Papar detail perihal :sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);			
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilikPerihal", rs.getString("ID_HAKMILIKPERIHAL"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK"));
				h.put("jenisBangunan", rs.getString("JENIS_BINAAN")==null ? "" :rs.getString("JENIS_BINAAN"));
				h.put("noRujukanJKR", rs.getString("NO_RUJUKAN_JKR")==null ? "" :rs.getString("NO_RUJUKAN_JKR"));
				h.put("tarikhBina", rs.getString("TARIKH_BINAAN")==null ? "" :rs.getString("TARIKH_BINAAN"));
				h.put("hargaBina", rs.getString("HARGA_BINAAN")==null ? "" :rs.getString("HARGA_BINAAN"));
				h.put("socLuas", rs.getString("ID_LUAS")==null ? "" :rs.getString("ID_LUAS"));
				h.put("luasLama", rs.getString("LUAS")==null ? "" :rs.getString("LUAS"));
				
				/*
				String luasByJenis = "";
				System.out.println("jenis binaan : "+rs.getString("JENIS_BINAAN"));
				if(rs.getString("JENIS_BINAAN")=="B"){
					luasByJenis = rs.getString("LUAS_BANGUNAN")==null ? "" :rs.getString("LUAS_BANGUNAN");
				}else if(rs.getString("JENIS_BINAAN")=="P"){
			    	luasByJenis = rs.getString("LUAS_PADANG")==null ? "" :rs.getString("LUAS_PADANG");
				}else if(rs.getString("JENIS_BINAAN").trim().equalsIgnoreCase("PR")){
					System.out.println("here "+rs.getString("LUAS_PARKING"));
					luasByJenis = rs.getString("LUAS_PARKING")==null ? "" :rs.getString("LUAS_PARKING");
				}else if(rs.getString("JENIS_BINAAN")=="J"){
					luasByJenis = rs.getString("LUAS_JALAN")==null ? "" :rs.getString("LUAS_JALAN");
				}else if(rs.getString("JENIS_BINAAN")=="L"){
					luasByJenis = rs.getString("LUAS_LAIN")==null ? "" :rs.getString("LUAS_LAIN");
				}
			    	
				System.out.println("luasByJenis : "+luasByJenis);
				h.put("luasLama",luasByJenis);
				*/
				h.put("luasB", rs.getString("LUAS_BANGUNAN")==null ? 0.00000 :rs.getDouble("LUAS_BANGUNAN"));
				h.put("luasJ", rs.getString("LUAS_JALAN")==null ? 0.00000 :rs.getDouble("LUAS_JALAN"));
				h.put("luasP", rs.getString("LUAS_PADANG")==null ? 0.00000 :rs.getDouble("LUAS_PADANG"));
				h.put("luasPR", rs.getString("LUAS_PARKING")==null ? 0.00000 :rs.getDouble("LUAS_PARKING"));
				h.put("luasL", rs.getString("LUAS_LAIN")==null ? 0.00000 :rs.getDouble("LUAS_LAIN"));	
				h.put("catatan", rs.getString("CATATAN")==null ? "" :rs.getString("CATATAN"));
				h.put("flagKemaskiniState", rs.getString("FLAG_KEMASKINI_STATE")==null ? "" :rs.getString("FLAG_KEMASKINI_STATE"));
				listPerihalById.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return listPerihalById;
		
	}
	// SENARAI PEMBANGUNAN BY ID HAKMILIK
	public static Vector<Hashtable<String,String>> getMaklumatPembangunanByIdHakmilik(String id) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			listPerihalByIdHakmilik = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();			
			sql =	" SELECT  A.ID_HAKMILIKPERIHAL, A.ID_HAKMILIK, A.LUAS_BANGUNAN, A.LUAS_JALAN, A.LUAS_PADANG, "+
					" A.LUAS_PARKING, A.LUAS_LAIN, A.LUAS_BELUMDIBANGUNKAN, B.LUAS, A.JENIS_BINAAN, A.NO_RUJUKAN_JKR, " +
					" TO_CHAR(A.TARIKH_BINAAN,'dd/mm/yyyy') TARIKH_BINAAN " +
					" ,A.HARGA_BINAAN, A.CATATAN ,C.KETERANGAN, A.LUAS_HEKTAR "+
					" ,D.ID_GAMBAR, D.NAMA_FAIL, D.PERIHAL_IMEJ "+
					" FROM TBLHTPHAKMILIKPERIHAL A, TBLHTPHAKMILIK B, TBLRUJLUAS C, TBLHTPGAMBAR D "+
					" WHERE A.ID_HAKMILIK = B.ID_HAKMILIK "+
					" AND C.ID_LUAS(+) = A.ID_LUAS_BERSAMAAN "+
					" AND A.ID_HAKMILIKPERIHAL = D.ID_HAKMILIKPERIHAL(+) "+
					" AND D.JENIS_GAMBAR = 'PEMBANGUNAN' "+
					" AND A.ID_HAKMILIK = ' "+id +"'"+
					" ORDER BY A.TARIKH_BINAAN ";			
			myLog.info("Papar list pembangunan for view pembangunan -------:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
		    int bil = 1;
		    int count = 0;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", bil+".");
				h.put("idHakmilikPerihal", rs.getString("ID_HAKMILIKPERIHAL"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK"));
				h.put("jenisBangunan", rs.getString("JENIS_BINAAN")==null ? "" :rs.getString("JENIS_BINAAN"));
				h.put("noRujukanJKR", rs.getString("NO_RUJUKAN_JKR")==null ? "" :rs.getString("NO_RUJUKAN_JKR"));
				h.put("tarikhBina", rs.getString("TARIKH_BINAAN")==null ? "" :rs.getString("TARIKH_BINAAN"));
				h.put("hargaBina", rs.getString("HARGA_BINAAN")==null ? "" :rs.getString("HARGA_BINAAN"));
				h.put("unitLuas", rs.getString("KETERANGAN")==null ? "" :rs.getString("KETERANGAN"));
				h.put("luasB", rs.getString("LUAS_BANGUNAN")==null ? "0.00000" :ekptg.helpers.Utils.formatLuas(rs.getDouble("LUAS_BANGUNAN")));
				h.put("luasJ", rs.getString("LUAS_JALAN")==null ? "0.00000" :ekptg.helpers.Utils.formatLuas(rs.getDouble("LUAS_JALAN")));
				h.put("luasP", rs.getString("LUAS_PADANG")==null ? "0.00000" :ekptg.helpers.Utils.formatLuas(rs.getDouble("LUAS_PADANG")));
				h.put("luasPR", rs.getString("LUAS_PARKING")==null ? "0.00000" :ekptg.helpers.Utils.formatLuas(rs.getDouble("LUAS_PARKING")));
				h.put("luasL", rs.getString("LUAS_LAIN")==null ? "0.00000" :ekptg.helpers.Utils.formatLuas(rs.getDouble("LUAS_LAIN")));	
				h.put("catatan", rs.getString("CATATAN")==null ? "" :rs.getString("CATATAN"));
				h.put("IdGambar", rs.getString("ID_GAMBAR")==null ? "" :rs.getString("ID_GAMBAR"));
				h.put("namaFail", rs.getString("NAMA_FAIL")==null ? "" :rs.getString("NAMA_FAIL"));
				h.put("perihalImej", rs.getString("PERIHAL_IMEJ")==null ? "" :rs.getString("PERIHAL_IMEJ"));
				listPerihalByIdHakmilik.addElement(h);
		    	bil++;
		    	count++;
			}
			if (count == 0){
				h = new Hashtable();
				h.put("bil","");
				h.put("jenisBangunan","Tiada Rekod.");
				h.put("noRujukanJKR", "");
				h.put("tarikhBina","");
				h.put("hargaBina","");
				h.put("unitLuas","");
				h.put("luasB", "");
				h.put("luasJ", "");
				h.put("luasP","");
				h.put("luasPR","");
				h.put("luasL","");	
				h.put("catatan","");
				h.put("idGambar","");
				h.put("namaFail","");
				h.put("perihalImej","");
				
				listPerihalByIdHakmilik.addElement(h);
			}
				
		} finally {
			if (db != null)
				db.close();
		}
		return listPerihalByIdHakmilik;
	}	
	//UPDATE PEMBANGUNAN BY ID HAKMILIK PERIHAL
	public static void updatePembangunanById(Hashtable data,HttpSession session) throws Exception {
	    Db dbHakmilikPerihal = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date(); 
		String currentDate = sdf.format(date);
	    String sql = "";
	    String stateNegeri = (String)session.getAttribute("_ekptg_user_negeri");
	    Connection conn = null;
	    
	    try{
	    	 
	    	  dbHakmilikPerihal = new Db();
	    	  
	    	  conn = dbHakmilikPerihal.getConnection();
		      conn.setAutoCommit(false);
		    	
			  Statement stmtHakmilik = dbHakmilikPerihal.getStatement();
			  
			  //checking...
			  sql =	" SELECT FLAG_KEMASKINI_STATE FROM TBLHTPHAKMILIKPERIHAL WHERE ID_HAKMILIKPERIHAL ="+data.get("idHakmilikPerihal");			
			  ResultSet rs = stmtHakmilik.executeQuery(sql);
			  String flagKemaskiniState = "";
			  while (rs.next()) {
				  flagKemaskiniState = rs.getString("FLAG_KEMASKINI_STATE")==null ? "" :rs.getString("FLAG_KEMASKINI_STATE");
			  }
			  System.out.println("flagKemaskiniState : "+flagKemaskiniState);
			  
			  
			  SQLRenderer rHakmilikPerihal = new SQLRenderer();
			  rHakmilikPerihal.update("ID_HAKMILIKPERIHAL", data.get("idHakmilikPerihal"));
			  
			  //PENAMBAHBAIKAN. SYAZ. STATE KEMASKINI N APPROVE BY PENGARAH STATE AND HQ
			  //IF EDIT BY STATE ONLY.
			  //AND IF HAVE CHANGES.  
			  //AND IF BELUM DI APPROVE OLEH HQ
			  /** E = EDIT
			   *  H = HANTAR PENGESAHAN
			   *  PS = PENGESAHAN PENGARAH STATE
			   *  PH = PENGESAHAN HQ
			  **/  
			  //System.out.println("stateNegeri : "+stateNegeri);
			  if(!stateNegeri.equalsIgnoreCase("16") && (flagKemaskiniState.equalsIgnoreCase("E") || flagKemaskiniState.equalsIgnoreCase("PH") || flagKemaskiniState == null || flagKemaskiniState == "")){
				  System.out.println("passed update flag");
				  rHakmilikPerihal.add("FLAG_KEMASKINI_STATE","E"); 
			  }  
			  
			  String tarikhBinaan = "to_date('"+ data.get("txdTarikhBina") + "','dd/MM/yyyy')";
			  
			  //hanya hq leh update direct.
			  //state akan simpan maklumat dalam tbl temp
			  if(stateNegeri.equalsIgnoreCase("16")){
				  
				  rHakmilikPerihal.add("JENIS_BINAAN",data.get("socJenisBinaan"));
				  rHakmilikPerihal.add("NO_RUJUKAN_JKR",data.get("txtNoJKR"));
				  rHakmilikPerihal.add("TARIKH_BINAAN",rHakmilikPerihal.unquote(tarikhBinaan));
				  
				  rHakmilikPerihal.add("HARGA_BINAAN",data.get("txtHarga"));
				  //rHakmilikPerihal.add("UNIT_LUAS",data.get("socLuasBangunan"));
				  rHakmilikPerihal.add("ID_LUAS",data.get("socLuasBangunan"));
				  //rHakmilikPerihal.add("LUAS_HEKTAR",data.get("txtLuasH"));
				  rHakmilikPerihal.add("LUAS",data.get("txtLuasH"));
				  rHakmilikPerihal.add("ID_LUAS_BERSAMAAN","2");
				  rHakmilikPerihal.add("CATATAN",data.get("txtCatatan"));
				  rHakmilikPerihal.add("ID_KEMASKINI",data.get("idMasuk"));
				  rHakmilikPerihal.add("TARIKH_KEMASKINI", rHakmilikPerihal.unquote("sysdate"));
						    
				    if(data.get("socJenisBinaan").equals("B"))
				    	rHakmilikPerihal.add("LUAS_BANGUNAN",data.get("txtLuas"));
				    else if(data.get("socJenisBinaan").equals("P"))
				    	rHakmilikPerihal.add("LUAS_PADANG",data.get("txtLuas"));
				    else if(data.get("socJenisBinaan").equals("PR"))
						rHakmilikPerihal.add("LUAS_PARKING",data.get("txtLuas"));		 
				    else if(data.get("socJenisBinaan").equals("J"))
						rHakmilikPerihal.add("LUAS_JALAN",data.get("txtLuas"));
				    else if(data.get("socJenisBinaan").equals("L"))
						rHakmilikPerihal.add("LUAS_LAIN",data.get("txtLuas"));
			  }
			  
			  sql = rHakmilikPerihal.getSQLUpdate("TBLHTPHAKMILIKPERIHAL");
			  stmtHakmilik.executeUpdate(sql);
		 
			  
			  
			  
			  //IF KEMASKINI BY  STATE, CREATE TEMP TABLE FOR RECORD DATA
			  //INSERT...
			  //SLAGI HQ X APPROVE / SELAGI X HANTAR PENGESAHAN, STATE XLEH BUAT KEMASKINI LAGI
			  
			  if(!stateNegeri.equalsIgnoreCase("16") && (flagKemaskiniState.equalsIgnoreCase("E") || flagKemaskiniState.equalsIgnoreCase("PH") || flagKemaskiniState == null || flagKemaskiniState == "")){
				  System.out.println("passed replicate temp");
				  	Statement stmt = dbHakmilikPerihal.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					long idHakmilikPerihaltemp = DB.getNextID("TBLHTPHAKMILIKPERIHALTEMP_SEQ"); 
					r.add("ID_HAKMILIKPERIHALTEMP",idHakmilikPerihaltemp);
				    r.add("ID_HAKMILIKPERIHAL",data.get("idHakmilikPerihal"));
				    r.add("JENIS_BINAAN",data.get("socJenisBinaan"));
				    r.add("NO_RUJUKAN_JKR",data.get("txtNoJKR"));
				    r.add("TARIKH_BINAAN",r.unquote(tarikhBinaan));
				    r.add("HARGA_BINAAN",data.get("txtHarga"));
				    r.add("ID_LUAS_BERSAMAAN",2);
				    r.add("ID_LUAS",data.get("socLuasBangunan"));
				    r.add("LUAS",data.get("txtLuasH"));
				    r.add("CATATAN",data.get("txtCatatan"));
				    r.add("ID_MASUK",data.get("idMasuk"));
				    r.add("TARIKH_MASUK",r.unquote("sysdate"));
					r.add("ID_KEMASKINI",data.get("idMasuk"));
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
					
				    if(data.get("socJenisBinaan").equals("B"))
				    	r.add("LUAS_BANGUNAN",data.get("txtLuas"));
				    else
				    if(data.get("socJenisBinaan").equals("P"))
				       r.add("LUAS_PADANG",data.get("txtLuas"));
				    else
					if(data.get("socJenisBinaan").equals("PR"))
					   r.add("LUAS_PARKING",data.get("txtLuas"));		 
				    else
					if(data.get("socJenisBinaan").equals("J"))
					   r.add("LUAS_JALAN",data.get("txtLuas"));
				    else
					if(data.get("socJenisBinaan").equals("L"))
					   r.add("LUAS_LAIN",data.get("txtLuas"));
			  
				    sql = r.getSQLInsert("TBLHTPHAKMILIKPERIHALTEMP");
				    myLog.info("sql insert to table temp "+sql);
					stmt.executeUpdate(sql);
			  }
			  
			  conn.commit();
			  
	    }catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    }finally {
		    if (dbHakmilikPerihal != null) dbHakmilikPerihal.close();
		}
		 
	}

	//GET LUAS TERKUMPUL
	public static Vector<Hashtable<String,String>> getLuasSemua(String idHakmilik) throws Exception {
		Db dbHakmilikPerihal = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date(); 
		String currentDate = sdf.format(date);
	    String sql = "";
	    try{
	    	dbHakmilikPerihal = new Db();
			listLuasTerkumpul = new Vector();
			Statement stmt = dbHakmilikPerihal.getStatement();		
	    	sql = "SELECT "+
		    "SUM(A.LUAS_BANGUNAN) AS SUM_BANGUNAN,SUM(A.LUAS_PADANG) AS SUM_PADANG, "+
		    "SUM(A.LUAS_PARKING) AS SUM_PARKING, SUM(A.LUAS_JALAN) AS SUM_JALAN, "+
		    "SUM(A.LUAS_LAIN) AS SUM_LAIN,SUM(B.LUAS_BERSAMAAN)/COUNT(*) AS LUAS_ASAL, "+
		    "SUM(NVL(A.LUAS_BANGUNAN,0))+SUM(NVL(A.LUAS_PADANG,0))+SUM(NVL(A.LUAS_PARKING,0))+SUM(NVL(A.LUAS_JALAN,0))+SUM(NVL(A.LUAS_LAIN,0)) AS JUMLAH_GUNA, "+
		    "(SUM(B.LUAS_BERSAMAAN)/COUNT(*))-(SUM(NVL(A.LUAS_BANGUNAN,0))+SUM(NVL(A.LUAS_PADANG,0))+SUM(NVL(A.LUAS_PARKING,0))+SUM(NVL(A.LUAS_JALAN,0))+SUM(NVL(A.LUAS_LAIN,0))) AS BAKI, "+
		    "ROUND(((SUM(B.LUAS_BERSAMAAN)/COUNT(*))-(SUM(NVL(A.LUAS_BANGUNAN,0))+SUM(NVL(A.LUAS_PADANG,0))+SUM(NVL(A.LUAS_PARKING,0))+SUM(NVL(A.LUAS_JALAN,0))+SUM(NVL(A.LUAS_LAIN,0))))/(SUM(B.LUAS_BERSAMAAN)/COUNT(*))*100,0) AS PERATUS_BAKI "+   
		    "FROM TBLHTPHAKMILIKPERIHAL A, TBLHTPHAKMILIK B "+
		    "WHERE A.ID_HAKMILIK = B.ID_HAKMILIK "+
		    "AND A.ID_HAKMILIK = '"+idHakmilik +"'";
			//myLog.info("Luas terkumpul:sql= "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
		    int bil = 1;
		    //int count = 0;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", bil+".");
				//h.put("idHakmilik", rs.getString("ID_HAKMILIK"));
				h.put("txtPadang", rs.getString("SUM_PADANG")==null ? "0.00000" :ekptg.helpers.Utils.formatLuas(rs.getDouble("SUM_PADANG")));
				h.put("txtBangunan", rs.getString("SUM_BANGUNAN")==null ? "0.00000" :ekptg.helpers.Utils.formatLuas(rs.getDouble("SUM_BANGUNAN")));
				h.put("txtJalan", rs.getString("SUM_JALAN")==null ? "0.00000" :ekptg.helpers.Utils.formatLuas(rs.getDouble("SUM_JALAN")));
				h.put("txtParking", rs.getString("SUM_PARKING")==null ? "0.00000" :ekptg.helpers.Utils.formatLuas(rs.getDouble("SUM_PARKING")));
				h.put("txtLain", rs.getString("SUM_LAIN")==null ? "0.00000" :ekptg.helpers.Utils.formatLuas(rs.getDouble("SUM_LAIN")));
				h.put("txtLuasAsal", rs.getString("LUAS_ASAL")==null ? "0.00000" :ekptg.helpers.Utils.formatLuas(rs.getDouble("LUAS_ASAL")));
				h.put("txtJumlahGuna", rs.getString("JUMLAH_GUNA")==null ? "0.00000" :ekptg.helpers.Utils.formatLuas(rs.getDouble("JUMLAH_GUNA")));
				h.put("txtBakiBelum", rs.getString("BAKI")==null ? "0.00000" :ekptg.helpers.Utils.formatLuas(rs.getDouble("BAKI")));
				h.put("txtPeratusBelum", rs.getString("PERATUS_BAKI")==null ? "0.0" :rs.getString("PERATUS_BAKI"));
				listLuasTerkumpul.addElement(h);
		    	bil++;
		    	//count++;
		    	
			}
			 return listLuasTerkumpul;	
		 }
		 finally {
		    if (dbHakmilikPerihal != null) dbHakmilikPerihal.close();
		 }
	}	
	//GET LUAS ASAL
	public static String getLuasAsal(String id) throws Exception {
		Db db = null;
		String sql = "";
		String output="";
		try {
			db = new Db();
			listPerihalById = new Vector();
			Statement stmt = db.getStatement();		
			//GET DATA LAMA
			sql = "SELECT TO_CHAR(A.LUAS_BERSAMAAN,'9,999,990.00000') LUAS_BERSAMAAN FROM TBLHTPHAKMILIK A " +
		      		"WHERE A.ID_HAKMILIK = ' "+id +"'";
//			sql = "SELECT A.LUAS FROM TBLHTPHAKMILIK A " +
//      		"WHERE A.ID_HAKMILIK = ' "+id +"'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				output = rs.getString("LUAS_BERSAMAAN").trim();
			}
			rs.next();	
			//log.info("luas current "+sql);
			return output;
			
		 } finally {
			if (db != null)
				db.close();
		}
		 
	}
	
	//HAPUS PEMBANGUNAN BY ID
	public static void hapusPembangunanById(String idHakmilikPerihal) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPHAKMILIKPERIHAL
			r.add("ID_HAKMILIKPERIHAL", idHakmilikPerihal);

			sql = r.getSQLDelete("TBLHTPHAKMILIKPERIHAL");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}	
	}
	
	
	// ADD PEMBANGUNAN BY ID HAKMILIK
		public static void updateFlagKemaskiniState(String idHakmilikPerihal, String flag) throws Exception {
			Connection conn = null;
			Db db = null;
			try {
					db = new Db();
					conn = db.getConnection();
			    	conn.setAutoCommit(false);
					Statement stmt = db.getStatement();
					String sql = "UPDATE TBLHTPHAKMILIKPERIHAL SET FLAG_KEMASKINI_STATE = '"+flag+"' WHERE ID_HAKMILIKPERIHAL = "+idHakmilikPerihal;
				    myLog.info("sql insert baru "+sql);
					stmt.executeUpdate(sql);
					
					
					//UPDATE AND REPLICATE TO MAIN TABLE
					if(flag.equalsIgnoreCase("PH")){
						
						String sqltemp = " SELECT A.* FROM TBLHTPHAKMILIKPERIHALTEMP A "
						+" WHERE A.ID_HAKMILIKPERIHAL = "+idHakmilikPerihal
						+" AND A.ID_HAKMILIKPERIHALTEMP = (SELECT MAX(A1.ID_HAKMILIKPERIHALTEMP) FROM TBLHTPHAKMILIKPERIHALTEMP A1 WHERE A1.ID_HAKMILIKPERIHAL = A.ID_HAKMILIKPERIHAL)" ;
						
						ResultSet rs = stmt.executeQuery(sqltemp);			
						String jenisBinaan="",noRujukanJKR="",tarikhBinaan="",hargaBinaan="",idLuas="",luas="",catatan="";
						Double luasBangunan=0.00000,luasPadang=0.00000,luasParking=0.00000,luasJalan=0.00000,luasLain=0.00000;
						while (rs.next()) {
							jenisBinaan = rs.getString("JENIS_BINAAN")==null ? "" :rs.getString("JENIS_BINAAN");
							noRujukanJKR = rs.getString("NO_RUJUKAN_JKR")==null ? "" :rs.getString("NO_RUJUKAN_JKR");
							tarikhBinaan = rs.getDate("TARIKH_BINAAN")==null?"":Format.format(rs.getDate("TARIKH_BINAAN"));
							hargaBinaan = rs.getString("HARGA_BINAAN")==null ? "" :rs.getString("HARGA_BINAAN");
							idLuas = rs.getString("ID_LUAS")==null ? "" :rs.getString("ID_LUAS");
							luas = rs.getString("LUAS")==null ? "" :rs.getString("LUAS");
							
							catatan = rs.getString("CATATAN")==null ? "" :rs.getString("CATATAN");
							luasBangunan = rs.getString("LUAS_BANGUNAN")==null ? 0.00000 :rs.getDouble("LUAS_BANGUNAN");
							luasPadang = rs.getString("LUAS_PADANG")==null ? 0.00000 :rs.getDouble("LUAS_PADANG");
							luasParking = rs.getString("LUAS_PARKING")==null ? 0.00000 :rs.getDouble("LUAS_PARKING");
							luasJalan = rs.getString("LUAS_JALAN")==null ? 0.00000 :rs.getDouble("LUAS_JALAN");
							luasLain = rs.getString("LUAS_LAIN")==null ? 0.00000 :rs.getDouble("LUAS_LAIN");
						}
						
						  String sqlreplicate = "";
						  Statement stmtHakmilik = db.getStatement();
						  SQLRenderer rHakmilikPerihal = new SQLRenderer();
						  rHakmilikPerihal.update("ID_HAKMILIKPERIHAL", idHakmilikPerihal);
						  rHakmilikPerihal.add("JENIS_BINAAN", jenisBinaan);
						  rHakmilikPerihal.add("NO_RUJUKAN_JKR", noRujukanJKR);
						  
						  String datebina = "to_date('"+ tarikhBinaan + "','dd/MM/yyyy')";
						  rHakmilikPerihal.add("TARIKH_BINAAN",rHakmilikPerihal.unquote(datebina));
						  
						  rHakmilikPerihal.add("HARGA_BINAAN", hargaBinaan);
						  rHakmilikPerihal.add("ID_LUAS", idLuas);
						  rHakmilikPerihal.add("LUAS", luas);
						  rHakmilikPerihal.add("CATATAN",catatan);
						  rHakmilikPerihal.add("LUAS_BANGUNAN",luasBangunan);
						  rHakmilikPerihal.add("LUAS_PADANG",luasPadang);
						  rHakmilikPerihal.add("LUAS_PARKING",luasParking);		 
						  rHakmilikPerihal.add("LUAS_JALAN",luasJalan);
						  rHakmilikPerihal.add("LUAS_LAIN",luasLain);
						  rHakmilikPerihal.add("FLAG_KEMASKINI_STATE","PH"); 
						  
						  sqlreplicate = rHakmilikPerihal.getSQLUpdate("TBLHTPHAKMILIKPERIHAL");
						  stmtHakmilik.executeUpdate(sqlreplicate);
							  
					}
					
					conn.commit();
					
			}catch (SQLException ex) { 
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



