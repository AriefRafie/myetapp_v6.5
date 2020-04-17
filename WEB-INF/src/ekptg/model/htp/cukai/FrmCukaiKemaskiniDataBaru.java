package ekptg.model.htp.cukai;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.htp.cukai.entity.CukaiTemp;


public class FrmCukaiKemaskiniDataBaru{

	private static Connection conn = null;
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.cukai.FrmCukaiKemaskiniDataBaru.class);

	public static void updateDataManual(CukaiTemp data) throws SQLException, DbException{
		
		double cukailain = (double)data.getBayaranLain();
		double tunggakkan = (double)data.getTunggakkan();
		long id = (long)data.getIdCukaiTemp();
		long idmasuk = (long)data.getIdMasuk();
		double lebihan = (double)data.getLebihan();
		double semasa = (double)data.getCukaiKenaBayar();
		double denda = (double)data.getDenda();
		String tahun = data.getTahun();
		String idkemaskini = data.getIdKemaskini();
		
		System.out.println("Tahunan semasa " + semasa);
		System.out.println("Tahunan tunggakkan " + tunggakkan);
		System.out.println("Tahunan denda " + denda);
		System.out.println("Tahunan cukai lain " + cukailain);
		System.out.println("Tahunan lebihan" + lebihan);
		System.out.println("Tahunan id " + id);
		System.out.println("Tahunan idmasuk " + idmasuk);
		System.out.println("Tahunan idkemaskini " + idkemaskini);

		
		 Date now = new Date(); 
	      SimpleDateFormat formatter =  new SimpleDateFormat("yyyy");
	      String TBF = "to_date('" + formatter.format(now) + "','yyyy')";
	      
	      System.out.println("YEAR :: " +TBF);
		
		Db db = null;
	    String sql = "";
	    try
	    {

		  db = new Db();
		  Statement stmt = db.getStatement();
		  SQLRenderer r = new SQLRenderer();
		  
		  r.update("ID_CUKAITEMP", id);
		  r.add("ID_MASUK", idmasuk);
		  r.add("LEBIHAN", Utils.RemoveComma(String.valueOf(lebihan)));
		  r.add("cukai_lain", Utils.RemoveComma(String.valueOf(cukailain)));
		  r.add("DENDA", Utils.RemoveComma(String.valueOf(denda)));
		  r.add("TUNGGAKAN", Utils.RemoveComma(String.valueOf(tunggakkan)));
		  r.add("CUKAI_KENA_BAYAR", Utils.RemoveComma(String.valueOf(semasa)));
		  r.add("ID_KEMASKINI", idkemaskini);
		 

	      sql = r.getSQLUpdate("tblhtpcukaitemp");
	      System.out.println("FrmGadaianSemakanData::Insert::tblhtpcukaitemp = "+sql);
	      stmt.executeUpdate(sql);
	    
	    }
	    finally {
	      if (db != null) db.close();
	    }	
		
	}
	
	/**
	 * get param kemaskini dari frmCukaiKemaskiniDataExcel2.jsp
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static int kemaskiniData(Hashtable data) throws Exception{
		
		Date now = new Date(); 
	    SimpleDateFormat formatter =  new SimpleDateFormat("yyyy");
	    String TBF = "to_date('" + formatter.format(now) + "','yyyy')";
		Db db = null;
	    String sql = "";
	    try	{
	    	
	    	long IDCUKAITERPERINCI = DB.getNextID("tblhtpcukaiterperinci_SEQ");
	    	/*
	    	int idFail = (Integer)data.get("IdFail");
	      	int idJKPTG = Integer.parseInt("1");
	      	String noPermohonan = "TIADA";
	      	String noPerserahan = "TIADA";
	      	String TarikhSurKJP = (String)data.get("TarikhSurKJP"); 
	      	*/ 
	      
	    	String cukai_kenabayar = "";	    
	    	String cukai_perlu_bayar = "";	    
	    	String YearKemaskini = (String)data.get("tahun");
	    	String tunggakan = (String)data.get("tunggakan");
	    	String denda = (String)data.get("denda");
	    	String cukai_taliair = (String)data.get("cukai_taliair");
	    	String cukai_parit = (String)data.get("cukai_parit");
	    	cukai_kenabayar = (String)data.get("cukai_kenabayar");
	    	String pengurangan = (String)data.get("pengurangan");
	    	String lebihan = (String)data.get("lebihan");
	    	cukai_perlu_bayar = (String)data.get("cukaiPerluBayar");
	    	String senaraiNolot = (String)data.get("senaraiNolot");
	    	String senaraiNO_HAKMILIKUPLOAD = (String)data.get("senaraiNO_HAKMILIKUPLOAD");
	    	String senaraiID_HAKMILIKCUKAI = (String)data.get("senaraiID_HAKMILIKCUKAI");
	    	String senaraiNO_HAKMILIK = (String)data.get("senaraiNO_HAKMILIK");	   

	    	db = new Db();
			  conn = db.getConnection();
			  conn.setAutoCommit(false);
			  Statement stmt = db.getStatement();
	    	SQLRenderer r = new SQLRenderer();		  
	    	r.add("ID_CUKAITERPERINCI", IDCUKAITERPERINCI);
	    	r.add("TAHUN", YearKemaskini);
	    	r.add("TUNGGAKAN", Utils.RemoveComma(tunggakan));
	    	r.add("DENDA", Utils.RemoveComma(denda));
	    	r.add("CUKAI_TALIAIR", Utils.RemoveComma(cukai_taliair));
	    	r.add("CUKAI_PARIT", Utils.RemoveComma(cukai_parit));
	    	r.add("CUKAI_KENA_BAYAR", Utils.RemoveComma(cukai_kenabayar));
	    	r.add("PENGURANGAN", Utils.RemoveComma(pengurangan));
	    	r.add("PENGECUALIAN", Utils.RemoveComma(lebihan));
	    	r.add("CUKAI_PERLU_BAYAR", Utils.RemoveComma(cukai_perlu_bayar));
	    	r.add("ID_HAKMILIKCUKAI", r.unquote(senaraiID_HAKMILIKCUKAI));
	    	r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
	    	sql = r.getSQLInsert("TBLHTPCUKAITERPERINCI");
	    	myLog.info("FrmGadaianSemakanData::Insert::TBLHTPCUKAITERPERINCI = "+sql);
	    	stmt.executeUpdate(sql);
	    	
	    	String sqlCukai = "";
//	    	r.update("ID_HAKMILIKCUKAI",r.unquote(senaraiID_HAKMILIKCUKAI)); 
//	    	r.add("STATUS","N");
//	    	r.add("ID_KEMASKINI",r.unquote(String.valueOf(data.get("idKemaskini"))));
//	    	r.add("TARIKH_KEMASKINI",r.unquote("SYSDATE"));
//	    	sqlCukai = r.getSQLUpdate("TBLHTPHAKMILIKCUKAI");
//	    	//log.info("1. sql update TBLHTPHAKMILIKCUKAI:"+sql);
//	    	stmt.executeUpdate(sqlCukai);
//	    	  
//		    r = new SQLRenderer();
//		      long idHakmilikCukai = DB.getNextID("TBLHTPHAKMILIKCUKAI_SEQ"); 
//		      r.add("ID_HAKMILIKCUKAI",idHakmilikCukai); 
//		      r.add("ID_HAKMILIK",data.get("idHakmilik")); 
//			  r.add("KOD_BAYARAN_CUKAI","P");
//			  r.add("CUKAI",data.get("txtCukaiSemasa"));
//			  r.add("CUKAI_TERKINI",data.get("txtCukaiTerkini"));
//			  r.add("STATUS","S");
//			  r.add("ID_MASUK",data.get("idKemaskini"));
//			  r.add("TARIKH_MASUK",r.unquote("SYSDATE"));
//			  r.add("ID_KEMASKINI",data.get("idKemaskini"));
//			  r.add("TARIKH_KEMASKINI",r.unquote("SYSDATE"));
//					  
//			  sqlCukai = r.getSQLInsert("TBLHTPHAKMILIKCUKAI");
//			  //log.info("2. sql INSERT TBLHTPHAKMILIKCUKAI:sqlCukai="+sqlCukai);
//			  stmt.executeUpdate(sqlCukai);
	    
	    	conn.commit();

	    }catch(Exception e){
	    	conn.rollback();
	    	e.printStackTrace();
	    
	    }finally {
	    	if (db != null) db.close();
		    if (conn != null) conn.close();
	    
	    }
	    
	    return 0;
		
	}
	
	/**
	 * @page frmCukaiBorangManual.jsp
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String SimpanDataManual(Hashtable data) throws Exception{
		
		 	Date now = new Date(); 
	      SimpleDateFormat formatter =  new SimpleDateFormat("yyyy");
	      String TBF =  formatter.format(now);
	      
	      System.out.println("YEAR :: " +TBF);
		String noHakmilikUpload;
		Db db = null;
	    String sql = "";
	    String sql2 = "";
	    String sql3 = "";
	    String sql4 = "";
	    long IDTBLHTPCUKAITEMP=0;
	    try
	    {
	    	db = new Db();	
	    	IDTBLHTPCUKAITEMP = DB.getNextID("TBLHTPCUKAITEMP_SEQ");
    		
    		String Tahunfail =  (String)data.get("Tahun");
    		System.out.println("Tahun Simpan :" +Tahunfail);
    		//String Tahun =  TBF;
    		String idmasuk = (String)data.get("idmasuk");
    		String idNegeri2 = (String)data.get("idNegeri2");
    		String idDaerah2 = (String)data.get("idDaerah2");
    		String idMukim2 = (String)data.get("idMukim2");
    		String JenisHakmilik =(String)data.get("JenisHakmilik");
    		String noHakmilik = (String)data.get("noHakmilik");
    		String JenisLot =  (String)data.get("JenisLot");
    		String noLot = (String)data.get("noLot");
    		String Tahunan = (String)data.get("Tahunan");
    		String CukaiLain =  (String)data.get("CukaiLain");
    		String Tungakan = (String)data.get("Tungakan");
    		String Denda = (String)data.get("Denda");
    		String Lebihan =  (String)data.get("Lebihan");
    		String JumBayaran = (String)data.get("JumBayaran");
    		String KegunaanTanah =  (String)data.get("KegunaanTanah");
	    
    		/****************************************************************************************************
    		 * tarik keterangan LOT
    		 */
    		
    		Statement stmt1 = db.getStatement();
    		SQLRenderer rlot = new SQLRenderer();
    		
  		  		rlot.add("id_lot");
  		  		rlot.add("keterangan");
  		  		rlot.add("id_lot",JenisLot);
  		  		
  		  		sql2 = rlot.getSQLSelect("tblrujLot");
  		  		
  		  		String keterangan = "";
  		  		ResultSet rlot1= stmt1.executeQuery(sql2);
  		  		while(rlot1.next()){
  		  			 keterangan = rlot1.getString("keterangan"); 
  		  		}
  		  		
  		  		System.out.println("Keterangan :: " + keterangan);
  		  		
  		  	/**888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
  		  	 * tarik jenishakmilik	
  		  	 */
  		  	Statement stmt2 = db.getStatement();
    		SQLRenderer rJH = new SQLRenderer();
    		
    			rJH.add("id_jenishakmilik");
    			rJH.add("kod_jenis_hakmilik");
    			rJH.add("id_jenishakmilik",JenisHakmilik);
  		  		
  		  		sql3 = rJH.getSQLSelect("Tblrujjenishakmilik");
  		  		
  		  		String jenishakmilik = "";
  		  		ResultSet rJH1= stmt2.executeQuery(sql3);
  		  		while(rJH1.next()){
  		  			jenishakmilik = rJH1.getString("kod_jenis_hakmilik"); 
  		  		}
  		  		
  		  		System.out.println("Keterangan :: " + jenishakmilik);
    		/**
    		 * tarik nama mukim
    		 */
  		  		
  		  	Statement stmt3 = db.getStatement();
    		SQLRenderer rJM = new SQLRenderer();
    		
    		rJM.add("id_mukim");
    		rJM.add("nama_mukim");
    		rJM.add("id_mukim",idMukim2);
  		  		
  		  		sql3 = rJM.getSQLSelect("tblrujmukim");
  		  		
  		  		String NamaMukim = "";
  		  		ResultSet rJM1= stmt2.executeQuery(sql3);
  		  		while(rJM1.next()){
  		  		NamaMukim = rJM1.getString("nama_mukim"); 
  		  		}
  		  		
  		  		System.out.println("Keterangan :: " + NamaMukim);
  		  		
  		  		noHakmilikUpload = jenishakmilik+noHakmilik;
  		  		String noLotUpload = keterangan+noLot;
  		  		String namaMukim = NamaMukim;
  		  	System.out.println("noHakmilikUpload :: " + noHakmilikUpload);
  		  System.out.println("noLotUpload :: " + noLotUpload);
  		  
		  db = new Db();
		  Statement stmt = db.getStatement();
		  SQLRenderer r = new SQLRenderer();
		  
		  r.add("ID_CUKAITEMP", IDTBLHTPCUKAITEMP);
		  r.add("id_masuk", idmasuk);
		  r.add("TAHUN", Tahunfail);
		  r.add("CUKAI_TALIAIR", 0);
		  r.add("CUKAI_PARIT", 0);
		  r.add("id_negeri", idNegeri2);
		  r.add("id_daerah", idDaerah2);
		  r.add("id_mukim", idMukim2);
		  r.add("nama_mukim", namaMukim);
		  
		  r.add("id_jenishakmilik", JenisHakmilik);
		  r.add("no_hakmilik", noHakmilik);
		  r.add("no_hakmilikupload", noHakmilikUpload);
		  r.add("id_lot", JenisLot);
		  r.add("no_lot", noLot);
		  r.add("no_lotupload", noLotUpload);
		  
		  r.add("tunggakan", Tungakan);
		  //r.add("bayaran_lain", Lebihan);
		  r.add("lebihan", Lebihan);
		  r.add("denda", Denda);
		  r.add("cukai_lain",CukaiLain);
		  r.add("cukai_perlu_bayar", JumBayaran);
		  r.add("cukai_kena_bayar", Tahunan);
		  
		  r.add("kegunaan_tanah", KegunaanTanah);
		  r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
		  r.add("TARIKH_MASUK", r.unquote("sysdate"));
		  //r.add("YearKemaskini", senaraiNolot);
		 // r.add("YearKemaskini", senaraiNO_HAKMILIKUPLOAD);	  
		 // r.add("YearKemaskini", senaraiNO_HAKMILIK);
		  
		 
	      sql = r.getSQLInsert("TBLHTPCUKAITEMP");
	      System.out.println("FrmGadaianSemakanData::Insert::TBLHTPCUKAITEMP = "+sql);
	      stmt.executeUpdate(sql);
	    
	    }
	    finally {
	      if (db != null) db.close();
	    }	
		
		return String.valueOf(IDTBLHTPCUKAITEMP);
		
	}



}

