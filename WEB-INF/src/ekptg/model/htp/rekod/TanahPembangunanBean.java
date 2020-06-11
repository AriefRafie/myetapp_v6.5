package ekptg.model.htp.rekod;

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
import ekptg.model.htp.FrmUtilData;

public class TanahPembangunanBean implements ITanahDaftar{
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.rekod.TanahPembangunanBean.class);

	private static Vector listPerihalById = null;
	private static Vector listPerihalByIdHakmilik = null;
	private static Vector listLuasTerkumpul = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Db db = null;

	//UPDATE PEMBANGUNAN BY ID HAKMILIK PERIHAL
	public void kemaskini(Hashtable<String,String> data) throws Exception {
		Date date = new Date(); 
//		String currentDate = sdf.format(date);
	    String sql = "";
	    Connection conn = null;
	    String stateNegeri = String.valueOf(data.get("stateNegeri"));
	    try{	    	 
	    	db = new Db();
	    	  
	    	conn = db.getConnection();
	    	conn.setAutoCommit(false);
		    	
	    	Statement stmtHakmilik = db.getStatement();
	    	sql =	" SELECT FLAG_KEMASKINI_STATE FROM TBLHTPHAKMILIKPERIHAL WHERE ID_HAKMILIKPERIHAL ="+data.get("idHakmilikPerihal");			
	    	ResultSet rs = stmtHakmilik.executeQuery(sql);
			
	    	String flagKemaskiniState = "";
	    	while (rs.next()) {
	    		flagKemaskiniState = rs.getString("FLAG_KEMASKINI_STATE")==null ? "" :rs.getString("FLAG_KEMASKINI_STATE");
	    	}			  
			  
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
	    	if(!stateNegeri.equalsIgnoreCase("16") && 
	    		(flagKemaskiniState.equalsIgnoreCase("E") || flagKemaskiniState.equalsIgnoreCase("PH") || flagKemaskiniState == null || flagKemaskiniState == "")){
//	    		System.out.println("passed update flag");
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
			  
			  /**IF KEMASKINI BY  STATE, CREATE TEMP TABLE FOR RECORD DATA
			  *INSERT...
			  *SLAGI HQ X APPROVE / SELAGI X HANTAR PENGESAHAN, STATE XLEH BUAT KEMASKINI LAGI
			  */
			  if(!stateNegeri.equalsIgnoreCase("16") && (flagKemaskiniState.equalsIgnoreCase("E") || flagKemaskiniState.equalsIgnoreCase("PH") || flagKemaskiniState == null || flagKemaskiniState == "")){
//				  System.out.println("passed replicate temp");
				  Statement stmt = db.getStatement();
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
				  else if(data.get("socJenisBinaan").equals("P"))
				      r.add("LUAS_PADANG",data.get("txtLuas"));
				  else if(data.get("socJenisBinaan").equals("PR"))
					  r.add("LUAS_PARKING",data.get("txtLuas"));		 
				  else if(data.get("socJenisBinaan").equals("J"))
					  r.add("LUAS_JALAN",data.get("txtLuas"));
				  else if(data.get("socJenisBinaan").equals("L"))
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
		    if (db != null) db.close();
		}
		 
	}
	// TAMBAH MAKLUMAT PEMBANGUNAN 
	@Override
	public String simpan(Hashtable<String,String> data) throws Exception {
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
		    r.add("TARIKH_BINAAN",r.unquote(tarikhBinaan));
		    r.add("HARGA_BINAAN",data.get("txtHarga"));
		    r.add("ID_LUAS_BERSAMAAN",2);
		    r.add("ID_LUAS",data.get("socLuas"));
		    r.add("LUAS",data.get("txtLuasLama"));
		    r.add("CATATAN",data.get("txtCatatan"));
		    
		    if(data.get("socJenisBinaan").equals("B"))
		    	r.add("LUAS_BANGUNAN",data.get("txtLuas"));
		    else if(data.get("socJenisBinaan").equals("P"))
		       r.add("LUAS_PADANG",data.get("txtLuas"));
		    else if(data.get("socJenisBinaan").equals("PR"))
			   r.add("LUAS_PARKING",data.get("txtLuas"));		 
		    else if(data.get("socJenisBinaan").equals("J"))
			   r.add("LUAS_JALAN",data.get("txtLuas"));
		    else if(data.get("socJenisBinaan").equals("L"))
			   r.add("LUAS_LAIN",data.get("txtLuas"));
	  
		    sql = r.getSQLInsert("TBLHTPHAKMILIKPERIHAL");
		    myLog.info("sql insert baru :sql="+sql);
		    
			Long setIdSuburusanstatus = 0L;
			setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(
									String.valueOf(data.get("langkah")),"61","=");

		    
			stmt.executeUpdate(sql);
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
	public Hashtable getMaklumat(String idHakmilikPerihal) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		listPerihalById = new Vector();
		Hashtable h = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql =	"SELECT A.ID_HAKMILIKPERIHAL, A.ID_HAKMILIK, A.JENIS_BINAAN,A.NO_RUJUKAN_JKR," +
					"TO_CHAR(A.TARIKH_BINAAN,'dd/mm/yyyy') TARIKH_BINAAN, "+
					"A.HARGA_BINAAN, A.ID_LUAS, A.LUAS_BERSAMAAN, A.LUAS_BANGUNAN, A.LUAS_JALAN, A.LUAS_PADANG, "+
					"A.LUAS_PARKING, A.LUAS_LAIN, A.CATATAN "+
					"FROM TBLHTPHAKMILIKPERIHAL A, TBLHTPHAKMILIK B "+
					"WHERE A.ID_HAKMILIK = B.ID_HAKMILIK "+
					"AND A.ID_HAKMILIKPERIHAL = ' "+idHakmilikPerihal +"'";
			
			myLog.info("papar detail perihal "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilikPerihal", rs.getString("ID_HAKMILIKPERIHAL"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK"));
				h.put("jenisBangunan", rs.getString("JENIS_BINAAN")==null ? "" :rs.getString("JENIS_BINAAN"));
				h.put("noRujukanJKR", rs.getString("NO_RUJUKAN_JKR")==null ? "" :rs.getString("NO_RUJUKAN_JKR"));
				h.put("tarikhBina", rs.getString("TARIKH_BINAAN")==null ? "" :rs.getString("TARIKH_BINAAN"));
				h.put("hargaBina", rs.getString("HARGA_BINAAN")==null ? "" :rs.getString("HARGA_BINAAN"));
				h.put("socLuas", rs.getString("ID_LUAS")==null ? "" :rs.getString("ID_LUAS"));
				h.put("luasLama", rs.getString("LUAS_BERSAMAAN")==null ? "" :rs.getString("LUAS_BERSAMAAN"));
				h.put("luasB", rs.getString("LUAS_BANGUNAN")==null ? 0.00000 :rs.getDouble("LUAS_BANGUNAN"));
				h.put("luasJ", rs.getString("LUAS_JALAN")==null ? 0.00000 :rs.getDouble("LUAS_JALAN"));
				h.put("luasP", rs.getString("LUAS_PADANG")==null ? 0.00000 :rs.getDouble("LUAS_PADANG"));
				h.put("luasPR", rs.getString("LUAS_PARKING")==null ? 0.00000 :rs.getDouble("LUAS_PARKING"));
				h.put("luasL", rs.getString("LUAS_LAIN")==null ? 0.00000 :rs.getDouble("LUAS_LAIN"));	
				h.put("catatan", rs.getString("CATATAN")==null ? "" :rs.getString("CATATAN"));
				listPerihalById.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		//return listPerihalById;
		return h;
		
	}
	// SENARAI PEMBANGUNAN BY ID HAKMILIK
	public Vector <Hashtable<String,String>> getSenaraiMaklumat(String id) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			listPerihalByIdHakmilik = new Vector();
			Statement stmt = db.getStatement();			
			sql =	"SELECT A.ID_HAKMILIKPERIHAL, A.ID_HAKMILIK, A.LUAS_BANGUNAN, A.LUAS_JALAN, A.LUAS_PADANG, "+
					"A.LUAS_PARKING, A.LUAS_LAIN, A.LUAS_BELUMDIBANGUNKAN, B.LUAS, A.JENIS_BINAAN, A.NO_RUJUKAN_JKR, " +
					"TO_CHAR(A.TARIKH_BINAAN,'dd/mm/yyyy') TARIKH_BINAAN, A.HARGA_BINAAN, A.CATATAN ,C.KETERANGAN, A.LUAS_HEKTAR "+
					"FROM TBLHTPHAKMILIKPERIHAL A, TBLHTPHAKMILIK B, TBLRUJLUAS C "+
					"WHERE A.ID_HAKMILIK = B.ID_HAKMILIK "+
					"AND C.ID_LUAS(+) = A.ID_LUAS_BERSAMAAN "+
					"AND A.ID_HAKMILIK = ' "+id +"'"+
					" ORDER BY A.TARIKH_BINAAN ";			
			myLog.info("Papar list pembangunan:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
		    int bil = 1;
		    int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil+".");
				h.put("idHakmilikPerihal", rs.getString("ID_HAKMILIKPERIHAL"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK"));
				h.put("jenisBangunan", rs.getString("JENIS_BINAAN")==null ? "" :rs.getString("JENIS_BINAAN"));
				h.put("noRujukanJKR", rs.getString("NO_RUJUKAN_JKR")==null ? "" :rs.getString("NO_RUJUKAN_JKR"));
				h.put("tarikhBina", rs.getString("TARIKH_BINAAN")==null ? "" :rs.getString("TARIKH_BINAAN"));
				h.put("hargaBina", rs.getString("HARGA_BINAAN")==null ? "" :rs.getString("HARGA_BINAAN"));
				h.put("unitLuas", rs.getString("KETERANGAN")==null ? "" :rs.getString("KETERANGAN"));
				h.put("luasB", rs.getString("LUAS_BANGUNAN")==null ? "0.00000" :rs.getDouble("LUAS_BANGUNAN"));
				h.put("luasJ", rs.getString("LUAS_JALAN")==null ? "0.00000" :rs.getDouble("LUAS_JALAN"));
				h.put("luasP", rs.getString("LUAS_PADANG")==null ? "0.00000" :rs.getDouble("LUAS_PADANG"));
				h.put("luasPR", rs.getString("LUAS_PARKING")==null ? "0.00000" :rs.getDouble("LUAS_PARKING"));
				h.put("luasL", rs.getString("LUAS_LAIN")==null ? "0.00000" :rs.getDouble("LUAS_LAIN"));	
				h.put("catatan", rs.getString("CATATAN")==null ? "" :rs.getString("CATATAN"));
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
				listPerihalByIdHakmilik.addElement(h);
			}
				
		} finally {
			if (db != null)
				db.close();
		}
		return listPerihalByIdHakmilik;
	}	
	//UPDATE PEMBANGUNAN BY ID HAKMILIK PERIHAL
	public static void updatePembangunanById(Hashtable data) throws Exception {
	    Db dbHakmilikPerihal = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date(); 
		String currentDate = sdf.format(date);
	    String sql = "";
	    try
	    {
	    	dbHakmilikPerihal = new Db();
			  Statement stmtHakmilik = dbHakmilikPerihal.getStatement();
			  SQLRenderer rHakmilikPerihal = new SQLRenderer();
			  rHakmilikPerihal.update("ID_HAKMILIKPERIHAL", data.get("idHakmilikPerihal"));
			  rHakmilikPerihal.add("JENIS_BINAAN",data.get("socJenisBinaan"));
			  rHakmilikPerihal.add("NO_RUJUKAN_JKR",data.get("txtNoJKR"));
			  
			  String tarikhBinaan = "to_date('"+ data.get("txdTarikhBina") + "','dd/MM/yyyy')";
			  rHakmilikPerihal.add("TARIKH_BINAAN",rHakmilikPerihal.unquote(tarikhBinaan));
			  
			  rHakmilikPerihal.add("HARGA_BINAAN",data.get("txtHarga"));
			  rHakmilikPerihal.add("UNIT_LUAS",data.get("socLuasBangunan"));
			  rHakmilikPerihal.add("LUAS_HEKTAR",data.get("txtLuasH"));
			  rHakmilikPerihal.add("CATATAN",data.get("txtCatatan"));
			    
			    if(data.get("socJenisBinaan").equals("B"))
			    	rHakmilikPerihal.add("LUAS_BANGUNAN",data.get("txtLuas"));
			    else
			    if(data.get("socJenisBinaan").equals("P"))
			    	rHakmilikPerihal.add("LUAS_PADANG",data.get("txtLuas"));
			    else
				if(data.get("socJenisBinaan").equals("PR"))
					rHakmilikPerihal.add("LUAS_PARKING",data.get("txtLuas"));		 
			    else
				if(data.get("socJenisBinaan").equals("J"))
					rHakmilikPerihal.add("LUAS_JALAN",data.get("txtLuas"));
			    else
				if(data.get("socJenisBinaan").equals("L"))
					rHakmilikPerihal.add("LUAS_LAIN",data.get("txtLuas"));

			  sql = rHakmilikPerihal.getSQLUpdate("TBLHTPHAKMILIKPERIHAL");
			  myLog.debug(sql);
			  stmtHakmilik.executeUpdate(sql);
		 }
		 finally {
		    if (dbHakmilikPerihal != null) dbHakmilikPerihal.close();
		 }
	}

	//GET LUAS TERKUMPUL
	public static Vector getLuasSemua(String idHakmilik) throws Exception {
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
		
			//myLog.info("luas terkumpul "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
		    int bil = 1;
		    int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil+".");
				//h.put("idHakmilik", rs.getString("ID_HAKMILIK"));
				h.put("txtPadang", rs.getString("SUM_PADANG")==null ? 0.00000 :rs.getDouble("SUM_PADANG"));
				h.put("txtBangunan", rs.getString("SUM_BANGUNAN")==null ? 0.00000 :rs.getDouble("SUM_BANGUNAN"));
				h.put("txtJalan", rs.getString("SUM_JALAN")==null ? 0.00000 :rs.getDouble("SUM_JALAN"));
				h.put("txtParking", rs.getString("SUM_PARKING")==null ? 0.00000 :rs.getDouble("SUM_PARKING"));
				h.put("txtLain", rs.getString("SUM_LAIN")==null ? 0.00000 :rs.getDouble("SUM_LAIN"));
				h.put("txtLuasAsal", rs.getString("LUAS_ASAL")==null ? 0.00000 :rs.getDouble("LUAS_ASAL"));
				h.put("txtJumlahGuna", rs.getString("JUMLAH_GUNA")==null ? 0.00000 :rs.getDouble("JUMLAH_GUNA"));
				h.put("txtBakiBelum", rs.getString("BAKI")==null ? 0.00000 :rs.getDouble("BAKI"));
				h.put("txtPeratusBelum", rs.getString("PERATUS_BAKI")==null ? "0.0" :rs.getString("PERATUS_BAKI"));
				listLuasTerkumpul.addElement(h);
		    	bil++;
		    	count++;
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
	@Override
	public void hapus(String idHakmilikPerihal) throws Exception {

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
 }

