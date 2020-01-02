package ekptg.view.ppt.bantahan;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmBantahanModel {
	static Logger myLogger = Logger.getLogger(FrmBantahanModel.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings("unchecked")
	private static Vector dataMaklumatBantahan = null;
	@SuppressWarnings("unchecked")
	private static Vector dataBorangO = null;
	public Vector getTarikhBorangG = null;
	@SuppressWarnings("unchecked")
	public Vector getDataMaklumatBantahan(){
		return dataMaklumatBantahan;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getDataBorangO(){
		return dataBorangO;
	}
	
	@SuppressWarnings("unchecked")
	public static Vector getListBantahanStandAlone(String userIdNegeri)throws Exception {
	
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql =  " SELECT A.ID_MAINBANTAHAN, A.NO_FAIL, A.NO_BANTAHAN, B.ID_MAINBORANGO, " +
	    			   " A.TARIKH_TERIMA, A.TARIKH_BORANGN, A.TARIKH_TERIMA_BORANGH, A.TARIKH_TERIMA_AWARD, " +
	    			   " A.NO_HAKMILIK, A.NO_LOT, A.STATUS_BANTAHAN, A.STATUS_HADIR, A.NAMA_PEMBANTAH, A.ALAMAT1, A.ALAMAT2, A.ALAMAT3, " +
	    			   " A.POSKOD, A.ID_NEGERI, A.ID_BANDAR, A.KEPENTINGANKEATAS, A.FLAG_PENERIMA_PAMPASAN, A.FLAG_BAHAGIAN_PAMPASAN, " +
	    			   " A.FLAG_UKUR_LUAS, A.FLAG_PAMPASAN, A.AMAUN_TUNTUTAN, A.ALASAN, A.MAKLUMAT_BANTAHAN_TAMAT_TEMPOH, A.ID_JENISPB " +
	    		       " FROM TBLPPTMAINBANTAHAN A,TBLPPTMAINBORANGO B ";
		    		
		    		sql += "WHERE A.ID_MAINBANTAHAN = B.ID_BANTAHAN(+)";
		    		
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			
		    			if(userIdNegeri.equals("14")){		    				
		    				sql += "AND A.USER_ID_NEGERI in (14,15,16) ";
		    			}else{		    				
		    				sql += "AND A.USER_ID_NEGERI ='"+userIdNegeri+"'";
		    			}		
		    		}
		    			    		
		    		sql += "ORDER by A.TARIKH_MASUK desc ";

		    		myLogger.info("SQL LIST BANTAHAN : "+sql);
		    		
		    		//ResultSet rs = stmt.executeQuery(sql);
		    		ResultSet rs = stmt.executeQuery(sql);	
		    		
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);
		    			h.put("ID_MAINBANTAHAN", rs.getString("ID_MAINBANTAHAN")== null?"":rs.getString("ID_MAINBANTAHAN"));
		    			h.put("NO_LOT", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
		    			h.put("NO_BANTAHAN", rs.getString("NO_BANTAHAN")== null?"":rs.getString("NO_BANTAHAN"));
		    			h.put("TARIKH_BORANGN", rs.getDate("TARIKH_BORANGN")==null?"":Format.format(rs.getDate("TARIKH_BORANGN")));
		    			h.put("NAMA_PEMBANTAH", rs.getString("NAMA_PEMBANTAH")== null?"":rs.getString("NAMA_PEMBANTAH"));
		    			h.put("ID_MAINBORANGO", rs.getString("ID_MAINBORANGO")== null?"":rs.getString("ID_MAINBORANGO"));
		    			
		    			list.addElement(h);
		    			bil++;
		    	  
		    		}//close while
		    		return list;
		    	}//close try 
		    	finally{
		    		if (db != null) db.close();
		    	}//close finally
		    	
	}//close getListBantahanStandAlone
	
	@SuppressWarnings("unchecked")
	public static String simpanBantahanStandAlone(Hashtable data,String userIdNeg) throws Exception{
	
	    Db db = null;
	    String sql = "";
	    String output="";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		long id_bantahan = DB.getNextID("TBLPPTMAINBANTAHAN_SEQ");    
	    		
	    		String id_user = (String)data.get("id_user");	
	    		
	    		String txtNoFail = (String)data.get("txtNoFail");
	    		String txtNoBantahan = (String)data.get("txtNoBantahan");		    		
	    		String txdTarikhTerimaBorangN = (String)data.get("txdTarikhTerimaBorangN");	
	    		String txdTarikhBorangN = (String)data.get("txdTarikhBorangN");
	    		String txtNoHakmilik = (String)data.get("txtNoHakmilik");
	    		String txtNoLot = (String)data.get("txtNoLot");
	    		String socStatusBantahan = (String)data.get("socStatusBantahan");
	    		String txdTarikhTerimaBorangH = (String)data.get("txdTarikhTerimaBorangH");
	    		String txdTarikhAward = (String)data.get("txdTarikhAward");
	    		String socStatusHadir = (String)data.get("socStatusHadir");
	    		String txtNamaPembantah = (String)data.get("txtNamaPembantah");
	    		String txtAlamat1 = (String)data.get("txtAlamat1");
	    		String txtAlamat2 = (String)data.get("txtAlamat2");
	    		String txtAlamat3 = (String)data.get("txtAlamat3");
	    		String txtPoskod = (String)data.get("txtPoskod");
	    		String txtKepentingan = (String)data.get("txtKepentingan");
	    		String cbUkuranLuas = (String)data.get("cbUkuranLuas");
	    		String cbAmaunPampasan = (String)data.get("cbAmaunPampasan");
	    		String cbTerimaPampasan = (String)data.get("cbTerimaPampasan");
	    		String cbBahagianPampasan = (String)data.get("cbBahagianPampasan");
	    		String txtAmaunTuntutan = (String)data.get("txtAmaunTuntutan");
	    		String txtAlasanBantahan = (String)data.get("txtAlasanBantahan");
	    		String txtMaklumatTamatTempoh = (String)data.get("txtMaklumatTamatTempoh");
	    		String socNegeri = (String)data.get("socNegeri");
	    		String socBandar = (String)data.get("socBandar");
	    		String socJenisPB = (String)data.get("socJenisPB");
	    		
	    		String tarikhTerimaBorangN = "to_date('" + txdTarikhTerimaBorangN + "','dd/MM/yyyy')";
	    		String tarikhBorangN = "to_date('" + txdTarikhBorangN + "','dd/MM/yyyy')";
	    		String tarikhTerimaBorangH = "to_date('" + txdTarikhTerimaBorangH + "','dd/MM/yyyy')";
	    		
	    		String tarikhAward = "to_date('" + txdTarikhAward + "','dd/MM/yyyy')";
	  
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_mainbantahan",id_bantahan);
	    		r.add("USER_ID_NEGERI",userIdNeg);
	    		r.add("NO_FAIL",txtNoFail);
	    		r.add("NO_BANTAHAN",txtNoBantahan);
	    		r.add("TARIKH_TERIMA",r.unquote(tarikhTerimaBorangN));
	    		r.add("TARIKH_BORANGN",r.unquote(tarikhBorangN));
	    		r.add("NO_HAKMILIK",txtNoHakmilik);
	    		r.add("NO_LOT",txtNoLot);
	    		r.add("STATUS_BANTAHAN",socStatusBantahan);
	    		r.add("TARIKH_TERIMA_BORANGH",r.unquote(tarikhTerimaBorangH));
	    		r.add("TARIKH_TERIMA_AWARD",r.unquote(tarikhAward));
	    		r.add("STATUS_HADIR",socStatusHadir);
	    		r.add("NAMA_PEMBANTAH",txtNamaPembantah);
	    		r.add("ALAMAT1",txtAlamat1);
	    		r.add("ALAMAT2",txtAlamat2);
	    		r.add("ALAMAT3",txtAlamat3);
	    		r.add("POSKOD",txtPoskod);
	    		r.add("ID_NEGERI",socNegeri);
	    		r.add("ID_BANDAR",socBandar);
	    		r.add("KEPENTINGANKEATAS",txtKepentingan);
	    		r.add("FLAG_PENERIMA_PAMPASAN",cbUkuranLuas);
	    		r.add("FLAG_BAHAGIAN_PAMPASAN",cbAmaunPampasan);
	    		r.add("FLAG_UKUR_LUAS",cbTerimaPampasan);
	    		r.add("FLAG_PAMPASAN",cbBahagianPampasan);
	    		r.add("AMAUN_TUNTUTAN",Utils.RemoveSymbol(txtAmaunTuntutan));
	    		r.add("ALASAN",txtAlasanBantahan);
	    		r.add("MAKLUMAT_BANTAHAN_TAMAT_TEMPOH",txtMaklumatTamatTempoh);
	    		r.add("ID_JENISPB",socJenisPB);
	    		
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("TBLPPTMAINBANTAHAN");
	    		myLogger.info("sql[Add Bantahan] : "+sql);
	    		stmt.executeUpdate(sql);
    	
	    		output = ""+id_bantahan;
	    		
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	    	return output;
	    	
	  }//close simpanMaklumatPU
	
	
	@SuppressWarnings("unchecked")
	public static void setDataMaklumatBantahan(String id_bantahan)throws Exception {
		
		dataMaklumatBantahan = new Vector();
		
	    Db db = null;
	    dataMaklumatBantahan.clear();
	    String sql = "";
	    
	    try {
	    		
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      //B.ID_MAINBORANGO,
	    		sql =  " SELECT  B.ID_MAINBORANGO,A.ID_MAINBANTAHAN, A.NO_FAIL, A.NO_BANTAHAN, A.TARIKH_TERIMA_RESIT, " +
	    			   " A.TARIKH_TERIMA, A.TARIKH_BORANGN, A.TARIKH_TERIMA_BORANGH, A.TARIKH_TERIMA_AWARD, " +
	    			   " A.NO_HAKMILIK, A.NO_LOT, A.STATUS_BANTAHAN, A.STATUS_HADIR, A.NAMA_PEMBANTAH, A.ALAMAT1, A.ALAMAT2, A.ALAMAT3, " +
	    			   " A.POSKOD, A.ID_NEGERI, A.ID_BANDAR, A.KEPENTINGANKEATAS, A.FLAG_PENERIMA_PAMPASAN, A.FLAG_BAHAGIAN_PAMPASAN, " +
	    			   " A.FLAG_UKUR_LUAS, A.FLAG_PAMPASAN, A.AMAUN_TUNTUTAN, A.ALASAN, A.MAKLUMAT_BANTAHAN_TAMAT_TEMPOH, A.ID_JENISPB " +
	    			   
	    			   " ,A.AMAUN_PAMPASAN, A.NO_RUJUKAN_BAYARAN, A.NO_AKAUN, A.CARA_BAYAR, A.NAMA_PENGHANTAR, A.NAMA_BANK, A.no_resit "+
	    			   " ,A.CARA_BAYAR_DEPOSIT, A.NO_RUJUKAN_BAYARAN_DEPOSIT, A.NO_AKAUN_DEPOSIT, A.NAMA_BANK_DEPOSIT "+
	    			   " ,A.FLAG_TERIMADEPOSIT, A.CATATAN_PEMULANGAN_DEPOSIT, A.tarikh_terima_resit, A.tarikh_resit, A.tarikh_surat_bayardeposit "+
	    			   " ,A.tarikh_akhir_bayardeposit, A.AMAUN_DEPOSIT "+
	    			   
	    			   " ,A.TEMPOH_BAYAR, A.UNIT_TEMPOH, A.AMAUN_AWARD "+
	    			   
	    		       " FROM TBLPPTMAINBANTAHAN A,"+
	    		       " TBLPPTMAINBORANGO B " +
	    		       " WHERE A.ID_MAINBANTAHAN ='"+id_bantahan+"' "+
	    		       " AND A.ID_MAINBANTAHAN = B.ID_BANTAHAN(+)";
	    		
	    		myLogger.info("dataBantahan : " + sql);
	        			ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable h;

	    		while (rs.next()) {
	    			h = new Hashtable();
	    			
	    			h.put("TEMPOH_BAYAR", rs.getString("TEMPOH_BAYAR")== null?"":rs.getString("TEMPOH_BAYAR"));
	    			h.put("UNIT_TEMPOH", rs.getString("UNIT_TEMPOH")== null?"":rs.getString("UNIT_TEMPOH"));
	    			h.put("AMAUN_AWARD", rs.getString("AMAUN_AWARD")== null?"":Utils.format2Decimal(rs.getDouble("AMAUN_AWARD")));
	    			
	    			h.put("ID_MAINBORANGO", rs.getString("ID_MAINBORANGO")== null?"":rs.getString("ID_MAINBORANGO"));
	    			h.put("AMAUN_PAMPASAN", rs.getString("AMAUN_PAMPASAN")== null?"":Utils.format2Decimal(rs.getDouble("AMAUN_PAMPASAN")));
	    			h.put("AMAUN_DEPOSIT", rs.getString("AMAUN_DEPOSIT")== null?"":Utils.format2Decimal(rs.getDouble("AMAUN_DEPOSIT")));	    			
	    			h.put("NO_RUJUKAN_BAYARAN_DEPOSIT", rs.getString("NO_RUJUKAN_BAYARAN_DEPOSIT")== null?"":rs.getString("NO_RUJUKAN_BAYARAN_DEPOSIT"));
	    			h.put("CARA_BAYAR_DEPOSIT", rs.getString("CARA_BAYAR_DEPOSIT")== null?"":rs.getString("CARA_BAYAR_DEPOSIT"));
	    			h.put("FLAG_TERIMADEPOSIT", rs.getString("FLAG_TERIMADEPOSIT")== null?"":rs.getString("FLAG_TERIMADEPOSIT"));
	    			h.put("CATATAN_PEMULANGAN_DEPOSIT", rs.getString("CATATAN_PEMULANGAN_DEPOSIT")== null?"":rs.getString("CATATAN_PEMULANGAN_DEPOSIT"));	    			
	    			h.put("NAMA_BANK", rs.getString("NAMA_BANK")== null?"":rs.getString("NAMA_BANK"));
	    			h.put("no_resit", rs.getString("no_resit")== null?"":rs.getString("no_resit"));
	    			h.put("NAMA_BANK_DEPOSIT", rs.getString("NAMA_BANK_DEPOSIT")== null?"":rs.getString("NAMA_BANK_DEPOSIT"));
	    			h.put("NO_AKAUN_DEPOSIT", rs.getString("NO_AKAUN_DEPOSIT")== null?"":rs.getString("NO_AKAUN_DEPOSIT"));	    			
	    			h.put("NO_RUJUKAN_BAYARAN", rs.getString("NO_RUJUKAN_BAYARAN")== null?"":rs.getString("NO_RUJUKAN_BAYARAN"));
	    			h.put("NO_AKAUN", rs.getString("NO_AKAUN")== null?"":rs.getString("NO_AKAUN"));
	    			h.put("CARA_BAYAR", rs.getString("CARA_BAYAR")== null?"":rs.getString("CARA_BAYAR"));
	    			h.put("NAMA_PENGHANTAR", rs.getString("NAMA_PENGHANTAR")== null?"":rs.getString("NAMA_PENGHANTAR"));	    			
	    			h.put("TARIKH_TERIMA_RESIT", rs.getDate("tarikh_terima_resit")==null?"":Format.format(rs.getDate("tarikh_terima_resit")));	
	    			h.put("TARIKH_RESIT", rs.getDate("tarikh_resit")==null?"":Format.format(rs.getDate("tarikh_resit")));	
	    			h.put("TARIKH_SURAT_BAYARDEPOSIT", rs.getDate("tarikh_surat_bayardeposit")==null?"":Format.format(rs.getDate("tarikh_surat_bayardeposit")));	
	    			h.put("TARIKH_AKHIR_BAYARDEPOSIT", rs.getDate("tarikh_akhir_bayardeposit")==null?"":Format.format(rs.getDate("tarikh_akhir_bayardeposit")));	
	    			
	    			
	    			h.put("ID_MAINBANTAHAN", rs.getString("ID_MAINBANTAHAN")== null?"":rs.getString("ID_MAINBANTAHAN"));
	    			h.put("TARIKH_TERIMA", rs.getDate("TARIKH_TERIMA")==null?"":Format.format(rs.getDate("TARIKH_TERIMA")));
	    			h.put("TARIKH_BORANGN", rs.getDate("TARIKH_BORANGN")==null?"":Format.format(rs.getDate("TARIKH_BORANGN")));
	    			h.put("TARIKH_TERIMA_BORANGH", rs.getDate("TARIKH_TERIMA_BORANGH")==null?"":Format.format(rs.getDate("TARIKH_TERIMA_BORANGH")));
	    			h.put("TARIKH_TERIMA_AWARD", rs.getDate("TARIKH_TERIMA_AWARD")==null?"":Format.format(rs.getDate("TARIKH_TERIMA_AWARD")));	    				    			
	    			h.put("NO_FAIL", rs.getString("NO_FAIL")== null?"":rs.getString("NO_FAIL"));
	    			h.put("NO_BANTAHAN", rs.getString("NO_BANTAHAN")== null?"":rs.getString("NO_BANTAHAN"));
	    			h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
	    			h.put("NO_LOT", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
	    			h.put("STATUS_BANTAHAN", rs.getString("STATUS_BANTAHAN")== null?"":rs.getString("STATUS_BANTAHAN"));
	    			h.put("STATUS_HADIR", rs.getString("STATUS_HADIR")== null?"":rs.getString("STATUS_HADIR"));
	    			h.put("NAMA_PEMBANTAH", rs.getString("NAMA_PEMBANTAH")== null?"":rs.getString("NAMA_PEMBANTAH"));
	    			h.put("ALAMAT1", rs.getString("ALAMAT1")== null?"":rs.getString("ALAMAT1"));
	    			h.put("ALAMAT2", rs.getString("ALAMAT2")== null?"":rs.getString("ALAMAT2"));
	    			h.put("ALAMAT3", rs.getString("ALAMAT3")== null?"":rs.getString("ALAMAT3"));
	    			h.put("POSKOD", rs.getString("POSKOD")== null?"":rs.getString("POSKOD"));
	    			h.put("ID_NEGERI", rs.getString("ID_NEGERI")== null?"":rs.getString("ID_NEGERI"));
	    			h.put("ID_BANDAR", rs.getString("ID_BANDAR")== null?"":rs.getString("ID_BANDAR"));
	    			h.put("KEPENTINGANKEATAS", rs.getString("KEPENTINGANKEATAS")== null?"":rs.getString("KEPENTINGANKEATAS"));
	    			h.put("FLAG_PENERIMA_PAMPASAN", rs.getString("FLAG_PENERIMA_PAMPASAN")== null?"":rs.getString("FLAG_PENERIMA_PAMPASAN"));
	    			h.put("FLAG_BAHAGIAN_PAMPASAN", rs.getString("FLAG_BAHAGIAN_PAMPASAN")== null?"":rs.getString("FLAG_BAHAGIAN_PAMPASAN"));
	    			h.put("FLAG_UKUR_LUAS", rs.getString("FLAG_UKUR_LUAS")== null?"":rs.getString("FLAG_UKUR_LUAS"));
	    			h.put("FLAG_PAMPASAN", rs.getString("FLAG_PAMPASAN")== null?"":rs.getString("FLAG_PAMPASAN"));
	    			h.put("AMAUN_TUNTUTAN", rs.getString("AMAUN_TUNTUTAN")== null?"":Utils.format2Decimal(rs.getDouble("AMAUN_TUNTUTAN")));
	    			h.put("ALASAN", rs.getString("ALASAN")== null?"":rs.getString("ALASAN"));
	    			h.put("MAKLUMAT_BANTAHAN_TAMAT_TEMPOH", rs.getString("MAKLUMAT_BANTAHAN_TAMAT_TEMPOH")== null?"":rs.getString("MAKLUMAT_BANTAHAN_TAMAT_TEMPOH"));
	    			h.put("ID_JENISPB", rs.getString("ID_JENISPB")== null?"":rs.getString("ID_JENISPB"));
	    			
	    			//PAGE DEPOSIT
	    			h.put("TARIKH_TERIMA_RESIT", rs.getDate("TARIKH_TERIMA_RESIT")==null?"":Format.format(rs.getDate("TARIKH_TERIMA_RESIT")));	
	    			
	    			dataMaklumatBantahan.addElement(h);
	    			
	      }//close while
	      
	    }// 
	    finally {
	      if (db != null) db.close();
	    }
	    
	  }//close setDataMaklumatBantahan
	
	public static void hapusDataBantahan(String id_bantahan) throws Exception{
		
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	  
	    		
	    		sql = "DELETE FROM TBLPPTMAINBANTAHAN WHERE ID_MAINBANTAHAN = '"+id_bantahan+"'";
	    		stmt.executeUpdate(sql);
	    		myLogger.info("sql delete : " + sql);
	    		
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close hapusDataPU
	
	@SuppressWarnings("unchecked")
	public static void updateBantahanStandAlone(Hashtable data,String id_bantahan) throws Exception{
	
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		String id_user = (String)data.get("id_user");	
	    		
	    		String txtNoFail = (String)data.get("txtNoFail");
	    		String txtNoBantahan = (String)data.get("txtNoBantahan");		    		
	    		String txdTarikhTerimaBorangN = (String)data.get("txdTarikhTerimaBorangN");	
	    		String txdTarikhBorangN = (String)data.get("txdTarikhBorangN");
	    		String txtNoHakmilik = (String)data.get("txtNoHakmilik");
	    		String txtNoLot = (String)data.get("txtNoLot");
	    		String socStatusBantahan = (String)data.get("socStatusBantahan");
	    		String txdTarikhTerimaBorangH = (String)data.get("txdTarikhTerimaBorangH");
	    		String txdTarikhAward = (String)data.get("txdTarikhAward");
	    		String socStatusHadir = (String)data.get("socStatusHadir");
	    		String txtNamaPembantah = (String)data.get("txtNamaPembantah");
	    		String txtAlamat1 = (String)data.get("txtAlamat1");
	    		String txtAlamat2 = (String)data.get("txtAlamat2");
	    		String txtAlamat3 = (String)data.get("txtAlamat3");
	    		String txtPoskod = (String)data.get("txtPoskod");
	    		String txtKepentingan = (String)data.get("txtKepentingan");
	    		String cbUkuranLuas = (String)data.get("cbUkuranLuas");
	    		String cbAmaunPampasan = (String)data.get("cbAmaunPampasan");
	    		String cbTerimaPampasan = (String)data.get("cbTerimaPampasan");
	    		String cbBahagianPampasan = (String)data.get("cbBahagianPampasan");
	    		String txtAmaunTuntutan = (String)data.get("txtAmaunTuntutan");
	    		String txtAlasanBantahan = (String)data.get("txtAlasanBantahan");
	    		String txtMaklumatTamatTempoh = (String)data.get("txtMaklumatTamatTempoh");
	    		String socNegeri = (String)data.get("socNegeri");
	    		String socBandar = (String)data.get("socBandar");
	    		String socJenisPB = (String)data.get("socJenisPB");
	    		
	    		String tarikhTerimaBorangN = "to_date('" + txdTarikhTerimaBorangN + "','dd/MM/yyyy')";
	    		String tarikhBorangN = "to_date('" + txdTarikhBorangN + "','dd/MM/yyyy')";
	    		String tarikhTerimaBorangH = "to_date('" + txdTarikhTerimaBorangH + "','dd/MM/yyyy')";
	    		
	    		String tarikhAward = "to_date('" + txdTarikhAward + "','dd/MM/yyyy')";
	  
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_mainbantahan",id_bantahan);
	    		r.add("NO_FAIL",txtNoFail);
	    		r.add("NO_BANTAHAN",txtNoBantahan);
	    		r.add("TARIKH_TERIMA",r.unquote(tarikhTerimaBorangN));
	    		r.add("TARIKH_BORANGN",r.unquote(tarikhBorangN));
	    		r.add("NO_HAKMILIK",txtNoHakmilik);
	    		r.add("NO_LOT",txtNoLot);
	    		r.add("STATUS_BANTAHAN",socStatusBantahan);
	    		r.add("TARIKH_TERIMA_BORANGH",r.unquote(tarikhTerimaBorangH));
	    		r.add("TARIKH_TERIMA_AWARD",r.unquote(tarikhAward));
	    		r.add("STATUS_HADIR",socStatusHadir);
	    		r.add("NAMA_PEMBANTAH",txtNamaPembantah);
	    		r.add("ALAMAT1",txtAlamat1);
	    		r.add("ALAMAT2",txtAlamat2);
	    		r.add("ALAMAT3",txtAlamat3);
	    		r.add("POSKOD",txtPoskod);
	    		r.add("ID_NEGERI",socNegeri);
	    		r.add("ID_BANDAR",socBandar);
	    		r.add("KEPENTINGANKEATAS",txtKepentingan);
	    		r.add("FLAG_PENERIMA_PAMPASAN",cbUkuranLuas);
	    		r.add("FLAG_BAHAGIAN_PAMPASAN",cbAmaunPampasan);
	    		r.add("FLAG_UKUR_LUAS",cbTerimaPampasan);
	    		r.add("FLAG_PAMPASAN",cbBahagianPampasan);
	    		r.add("AMAUN_TUNTUTAN",Utils.RemoveSymbol(txtAmaunTuntutan));
	    		r.add("ALASAN",txtAlasanBantahan);
	    		r.add("MAKLUMAT_BANTAHAN_TAMAT_TEMPOH",txtMaklumatTamatTempoh);
	    		r.add("ID_JENISPB",socJenisPB);
	    		
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("TBLPPTMAINBANTAHAN");
	    		myLogger.info("sql[update Bantahan] : "+sql);
	    		stmt.executeUpdate(sql);
    	
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	    	
	  }//close simpanMaklumatBantahan
	
	@SuppressWarnings("unchecked")
	public static void updateDeposit(Hashtable data,String id_bantahan) throws Exception{
	
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		String id_user = (String)data.get("id_user");	
	    		
	    		String txdTarikhTerimaResit = (String)data.get("txdTarikhTerimaResit");
	    		String txdTarikhResit = (String)data.get("txdTarikhResit");
	    		String txdTarikhPulang = (String)data.get("txdTarikhPulang");
	    		String txdTarikhHantarDeposit = (String)data.get("txdTarikhHantarDeposit");
	    		String txtAmaunPampasan = (String)data.get("txtAmaunPampasan");
	    		String txtNoCek = (String)data.get("txtNoCek");
	    		String txtNoAkaun = (String)data.get("txtNoAkaun");
	    		String socCaraBayaran = (String)data.get("socCaraBayaran");
	    		String txtNamaBank = (String)data.get("txtNamaBank");
	    		String txtNoResit = (String)data.get("txtNoResit");
	    		String txtDeposit = (String)data.get("txtDeposit");
	    		String socCaraBayaranDeposit = (String)data.get("socCaraBayaranDeposit");
	    		String txtNoCekDeposit = (String)data.get("txtNoCekDeposit");
	    		String txtNoAkaunDeposit = (String)data.get("txtNoAkaunDeposit");
	    		String txtNamaBankDeposit = (String)data.get("txtNamaBankDeposit");
	    		String socStatusPulang = (String)data.get("socStatusPulang");
	    		String txtCatatanPulang = (String)data.get("txtCatatanPulang");
	    		String txtNamaPenghantar = (String)data.get("txtNamaPenghantar");
	    		String txtAmaunTuntutan = (String)data.get("txtAmaunTuntutan");
	    		
	    		String tarikhTerimaResit = "to_date('" + txdTarikhTerimaResit + "','dd/MM/yyyy')";
	    		String tarikhResit = "to_date('" + txdTarikhResit + "','dd/MM/yyyy')";
	    		String tarikhPulang = "to_date('" + txdTarikhPulang + "','dd/MM/yyyy')";	    		
	    		String tarikhHantarDeposit = "to_date('" + txdTarikhHantarDeposit + "','dd/MM/yyyy')";
	  
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_mainbantahan",id_bantahan);
	    		r.add("AMAUN_PAMPASAN",Utils.RemoveSymbol(txtAmaunPampasan));
	    		r.add("NO_RUJUKAN_BAYARAN",txtNoCek);
	    		r.add("NO_AKAUN",txtNoAkaun);
	    		r.add("CARA_BAYAR",socCaraBayaran);
	    		r.add("NAMA_PENGHANTAR",txtNamaPenghantar);
	    		r.add("NAMA_BANK",txtNamaBank);
	    		r.add("no_resit",txtNoResit);
	    		r.add("CARA_BAYAR_DEPOSIT",socCaraBayaranDeposit);
	    		r.add("NO_RUJUKAN_BAYARAN_DEPOSIT",txtNoCekDeposit);	    		
	    		r.add("NO_AKAUN_DEPOSIT",txtNoAkaunDeposit);
	    		r.add("NAMA_BANK_DEPOSIT",txtNamaBankDeposit);
	    		r.add("FLAG_TERIMADEPOSIT",socStatusPulang);
	    		r.add("CATATAN_PEMULANGAN_DEPOSIT",txtCatatanPulang);	    		
	    		r.add("tarikh_terima_resit",r.unquote(tarikhTerimaResit));
	    		r.add("tarikh_resit",r.unquote(tarikhResit));
	    		r.add("tarikh_surat_bayardeposit",r.unquote(tarikhPulang));
	    		r.add("tarikh_akhir_bayardeposit",r.unquote(tarikhHantarDeposit));
	    		r.add("AMAUN_TUNTUTAN",Utils.RemoveSymbol(txtAmaunTuntutan));
	    		r.add("AMAUN_DEPOSIT",Utils.RemoveSymbol(txtDeposit));	    		
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("TBLPPTMAINBANTAHAN");
	    		myLogger.info("sql[update Bantahan deposit] : "+sql);
	    		stmt.executeUpdate(sql);
    	
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	    	
	  }//close updateDeposit
	public Vector getTarikhBorangG(String id_siasatan) throws Exception {	 
		 Db db = null;
		 String sql = "";
		 try{
			 getTarikhBorangG = new Vector();
			 db = new Db();
			 Statement stmt = db.getStatement();
			 SQLRenderer r = new SQLRenderer();
			 
			 sql = " SELECT TARIKH_BORANGG FROM TBLPPTBORANGG WHERE ID_SIASATAN = '"+ id_siasatan +"'";
			 ResultSet rs = stmt.executeQuery(sql);					
			 Hashtable h;			    
		     while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("tarikh_borangg", rs.getString("TARIKH_BORANGG")==null?"":rs.getDate("TARIKH_BORANGG"));
		    	getTarikhBorangG.addElement(h);
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getTarikhBorangG;
		 
	}
	@SuppressWarnings("unchecked")
	public static void setDataBorangO(String id_bantahan)throws Exception {
		
		dataBorangO = new Vector();
		
	    Db db = null;
	    dataBorangO.clear();
	    String sql = "";
	    
	    try {
	    		
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
			    
	    		sql =  " SELECT A.ID_MAINBORANGO, A.ID_BANTAHAN, B.ID_NEGERI, B.ID_BANDAR, A.NO_RUJUKAN_TANAH, "+
	    			   " A.TARIKH_BORANGO, A.ID_MAHKAMAH, A.NAMA_PENGHANTAR, A.TARIKH_HANTAR_BORANGO, A.NAMA_PENERIMA_BORANGO, "+
	    			   " B.ALAMAT1, B.ALAMAT2, B.ALAMAT3, B.POSKOD, "+
	    			   
	    			   " A.TARIKH_TERIMA_PERINTAH, A.TARIKH_PERINTAH, A.FLAG_PULANG_DEPOSIT, A.KEPUTUSAN_MAHKAMAH, "+
	    			   " A.KOS_PENGAPIT_HAKIM, A.TARIKH_LANJUTAN_MAHKAMAH_OB,A.TARIKH_LANJUTAN_MAHKAMAH_PT, A.NO_RUJUKAN_MAHKAMAH, A.KETERANGAN_PAMPASAN, "+
	    			   
	    			   " A.KOS_JPPH, A.NAMA_JPPH, A.KOS_SWASTA, A.NAMA_SWASTA, A.SYARIKAT_SWASTA"+
	    			   
	    			   " FROM TBLPPTMAINBORANGO A, TBLRUJPEJABAT B "+
	    			   " WHERE A.ID_MAHKAMAH = B.ID_PEJABAT "+
	    			   " AND A.ID_BANTAHAN = '"+id_bantahan+"' ";
	    		
	    		ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable h;

	    		while (rs.next()) {
	    			h = new Hashtable();	    
	    			
	    			h.put("FLAG_PULANG_DEPOSIT", rs.getString("FLAG_PULANG_DEPOSIT")== null?"":rs.getString("FLAG_PULANG_DEPOSIT"));
	    			h.put("KEPUTUSAN_MAHKAMAH", rs.getString("KEPUTUSAN_MAHKAMAH")== null?"":rs.getString("KEPUTUSAN_MAHKAMAH"));
	    			h.put("NO_RUJUKAN_MAHKAMAH", rs.getString("NO_RUJUKAN_MAHKAMAH")== null?"":rs.getString("NO_RUJUKAN_MAHKAMAH"));
	    			h.put("KETERANGAN_PAMPASAN", rs.getString("KETERANGAN_PAMPASAN")== null?"":rs.getString("KETERANGAN_PAMPASAN"));
	    			h.put("NO_RUJUKAN_TANAH", rs.getString("NO_RUJUKAN_TANAH")== null?"":rs.getString("NO_RUJUKAN_TANAH"));
	    			h.put("KOS_PENGAPIT_HAKIM", rs.getString("KOS_PENGAPIT_HAKIM")== null?"":Utils.format2Decimal(rs.getDouble("KOS_PENGAPIT_HAKIM")));
	    			h.put("TARIKH_TERIMA_PERINTAH", rs.getDate("TARIKH_TERIMA_PERINTAH")==null?"":Format.format(rs.getDate("TARIKH_TERIMA_PERINTAH")));
	    			h.put("TARIKH_PERINTAH", rs.getDate("TARIKH_PERINTAH")==null?"":Format.format(rs.getDate("TARIKH_PERINTAH")));
	    			h.put("TARIKH_LANJUTAN_MAHKAMAH_OB", rs.getDate("TARIKH_LANJUTAN_MAHKAMAH_OB")==null?"":Format.format(rs.getDate("TARIKH_LANJUTAN_MAHKAMAH_OB")));
	    			h.put("TARIKH_LANJUTAN_MAHKAMAH_PT", rs.getDate("TARIKH_LANJUTAN_MAHKAMAH_PT")==null?"":Format.format(rs.getDate("TARIKH_LANJUTAN_MAHKAMAH_PT")));
	    			
	    			h.put("ID_MAINBORANGO", rs.getString("ID_MAINBORANGO")== null?"":rs.getString("ID_MAINBORANGO"));
	    			h.put("ID_NEGERI", rs.getString("ID_NEGERI")== null?"":rs.getString("ID_NEGERI"));
	    			h.put("ID_BANDAR", rs.getString("ID_BANDAR")== null?"":rs.getString("ID_BANDAR"));	    	
	    			h.put("ID_MAHKAMAH", rs.getString("ID_MAHKAMAH")== null?"":rs.getString("ID_MAHKAMAH"));	  
	    			h.put("NAMA_PENGHANTAR", rs.getString("NAMA_PENGHANTAR")== null?"":rs.getString("NAMA_PENGHANTAR"));	  
	    			h.put("NAMA_PENERIMA_BORANGO", rs.getString("NAMA_PENERIMA_BORANGO")== null?"":rs.getString("NAMA_PENERIMA_BORANGO"));	  
	    			h.put("ALAMAT1", rs.getString("ALAMAT1")== null?"":rs.getString("ALAMAT1"));	  
	    			h.put("ALAMAT2", rs.getString("ALAMAT2")== null?"":rs.getString("ALAMAT2"));	  
	    			h.put("ALAMAT3", rs.getString("ALAMAT3")== null?"":rs.getString("ALAMAT3"));	  
	    			h.put("POSKOD", rs.getString("POSKOD")== null?"":rs.getString("POSKOD"));	
	    			h.put("TARIKH_BORANGO", rs.getDate("TARIKH_BORANGO")==null?"":Format.format(rs.getDate("TARIKH_BORANGO")));
	    			h.put("TARIKH_HANTAR_BORANGO", rs.getDate("TARIKH_HANTAR_BORANGO")==null?"":Format.format(rs.getDate("TARIKH_HANTAR_BORANGO")));
	    			h.put("KOS_JPPH", rs.getString("KOS_JPPH")== null?"":Utils.format2Decimal(rs.getDouble("KOS_JPPH")));	  
	    			h.put("NAMA_JPPH", rs.getString("NAMA_JPPH")== null?"":rs.getString("NAMA_JPPH"));	  
	    			h.put("KOS_SWASTA", rs.getString("KOS_SWASTA")== null?"":Utils.format2Decimal(rs.getDouble("KOS_SWASTA")));	  
	    			h.put("NAMA_SWASTA", rs.getString("NAMA_SWASTA")== null?"":rs.getString("NAMA_SWASTA"));	
	    			h.put("SYARIKAT_SWASTA", rs.getString("SYARIKAT_SWASTA")== null?"":rs.getString("SYARIKAT_SWASTA"));
	    			dataBorangO.addElement(h);
	    			
	      }//close while
	      
	    }// 
	    finally {
	      if (db != null) db.close();
	    }
	    
	  }//close setDataBorangO
	
	
	@SuppressWarnings("unchecked")
	public static Vector getDataBantahan(String id_mahkamah)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql =  " SELECT ALAMAT1,ALAMAT2,ALAMAT3,POSKOD,ID_NEGERI,ID_BANDAR FROM TBLRUJPEJABAT WHERE ID_PEJABAT= '"+id_mahkamah+"'";

		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		
		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("ALAMAT1", rs.getString("ALAMAT1")== null?"":rs.getString("ALAMAT1"));
		    			h.put("ALAMAT2", rs.getString("ALAMAT2")== null?"":rs.getString("ALAMAT2"));
		    			h.put("ALAMAT3", rs.getString("ALAMAT3")== null?"":rs.getString("ALAMAT3"));
		    			h.put("POSKOD", rs.getString("POSKOD")== null?"":rs.getString("POSKOD"));
		    			h.put("ID_NEGERI", rs.getString("ID_NEGERI")== null?"":rs.getString("ID_NEGERI"));
		    			h.put("ID_BANDAR", rs.getString("ID_BANDAR")== null?"":rs.getString("ID_BANDAR"));		    		
		    			list.addElement(h);
		    			
		    		}//close while
		    		return list;
		    	}//close try 
		    	finally{
		    		if (db != null) db.close();
		    	}//close finally
		    	
	}//close getDataBantahan
	
	@SuppressWarnings("unchecked")
	public static void simpanBorangO(Hashtable data,String id_bantahan) throws Exception{
	
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		String id_user = (String)data.get("id_user");	
	    		
	    		String txdTarikhBorangO = (String)data.get("txdTarikhBorangO");
	    		String txdTarikhHantarBorangO = (String)data.get("txdTarikhHantarBorangO");
	    		String txtNamaPenghantar = (String)data.get("txtNamaPenghantar");
	    		String txtNamaPenerima = (String)data.get("txtNamaPenerima");
	    		String socMahkamahTinggi = (String)data.get("socMahkamahTinggi");
	    		
	    		String tarikhBorangO = "to_date('" + txdTarikhBorangO + "','dd/MM/yyyy')";
	    		String tarikhHantarBorangO = "to_date('" + txdTarikhHantarBorangO + "','dd/MM/yyyy')";

	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_bantahan",id_bantahan);
	    		r.add("NAMA_PENGHANTAR",txtNamaPenghantar);
	    		r.add("NAMA_PENERIMA_BORANGO",txtNamaPenerima);
	    		r.add("ID_MAHKAMAH",socMahkamahTinggi);
	    		r.add("TARIKH_BORANGO",r.unquote(tarikhBorangO));
	    		r.add("TARIKH_HANTAR_BORANGO",r.unquote(tarikhHantarBorangO));
	    		r.add("TARIKH_MASUK",r.unquote("sysdate"));
	    		r.add("ID_MASUK",id_user);    		
	    		sql = r.getSQLInsert("TBLPPTMAINBORANGO");
	    		myLogger.info("sql[insert Borang O] : "+sql);
	    		stmt.executeUpdate(sql);
    	
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	    	
	  }//close simpanBorangO
	
	@SuppressWarnings("unchecked")
	public static void updateBorangO(Hashtable data,String id_borango) throws Exception{
	
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		String id_user = (String)data.get("id_user");	
	    		
	    		String txdTarikhBorangO = (String)data.get("txdTarikhBorangO");
	    		String txdTarikhHantarBorangO = (String)data.get("txdTarikhHantarBorangO");
	    		String txtNamaPenghantar = (String)data.get("txtNamaPenghantar");
	    		String txtNamaPenerima = (String)data.get("txtNamaPenerima");
	    		String socMahkamahTinggi = (String)data.get("socMahkamahTinggi");
	    		
	    		String tarikhBorangO = "to_date('" + txdTarikhBorangO + "','dd/MM/yyyy')";
	    		String tarikhHantarBorangO = "to_date('" + txdTarikhHantarBorangO + "','dd/MM/yyyy')";

	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_mainborango",id_borango);
	    		r.add("NAMA_PENGHANTAR",txtNamaPenghantar);
	    		r.add("NAMA_PENERIMA_BORANGO",txtNamaPenerima);
	    		r.add("ID_MAHKAMAH",socMahkamahTinggi);
	    		r.add("TARIKH_BORANGO",r.unquote(tarikhBorangO));
	    		r.add("TARIKH_HANTAR_BORANGO",r.unquote(tarikhHantarBorangO));
	    		r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
	    		r.add("ID_KEMASKINI",id_user);    		
	    		sql = r.getSQLUpdate("TBLPPTMAINBORANGO");
	    		myLogger.info("sql[update Borang O] : "+sql);
	    		stmt.executeUpdate(sql);
    	
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	    	
	  }//close updateBorangO
	
	@SuppressWarnings("unchecked")
	public static void updateSusulan(Hashtable data,String id_bantahan) throws Exception{
	
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		String id_user = (String)data.get("id_user");	
	    		
	    		String txtNoProsiding = (String)data.get("txtNoProsiding");
	    		String txtNoRujPemohon = (String)data.get("txtNoRujPemohon");
	    		String sorKeputusanMahkamah = (String)data.get("sorKeputusanMahkamah");
	    		String txdTarikhPerintah = (String)data.get("txdTarikhPerintah");
	    		
	    		String txdTarikhTerimaPerintah = (String)data.get("txdTarikhTerimaPerintah");
	    		String txtAmaunTambahan = (String)data.get("txtAmaunTambahan");
	    		String txtKosPengapit = (String)data.get("txtKosPengapit");
	    		String txtTempohBayaran = (String)data.get("txtTempohBayaran");
	    		String socUnitTempoh = (String)data.get("socUnitTempoh");
	    		
	    		String sorStatusPulangDep = (String)data.get("sorStatusPulangDep");
	    		String txtKeteranganPampasan = (String)data.get("txtKeteranganPampasan");
	    		
	    		String txtKosJPPH = (String)data.get("txtKosJPPH");
	    		String txtNamaJPPH = (String)data.get("txtNamaJPPH");
	    		String txtKosSwasta = (String)data.get("txtKosSwasta");
	    		String txtNamaSwasta = (String)data.get("txtNamaSwasta");
	    		String txtNamaSyarikat = (String)data.get("txtNamaSyarikat");
	    		
	    		String tarikhPerintah = "to_date('" + txdTarikhPerintah + "','dd/MM/yyyy')";
	    		String tarikhTerimaPerintah = "to_date('" + txdTarikhTerimaPerintah + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_mainbantahan",id_bantahan);
	    		r.add("tempoh_bayar",txtTempohBayaran);
	    		r.add("unit_tempoh",socUnitTempoh);
	    		r.add("amaun_award",Utils.RemoveSymbol(txtAmaunTambahan));	    		
	    		r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
	    		r.add("ID_KEMASKINI",id_user);    		
	    		sql = r.getSQLUpdate("TBLPPTMAINBANTAHAN");
	    		myLogger.info("sql[update Susulan (tblpptmainbantahan) ] : "+sql);
	    		stmt.executeUpdate(sql);
    	
	    		r.clear();
	    		
	    		r.update("id_bantahan",id_bantahan);
	    		r.add("tarikh_terima_perintah",r.unquote(tarikhTerimaPerintah));
	    		r.add("tarikh_perintah",r.unquote(tarikhPerintah));
	    		r.add("flag_pulang_deposit",sorStatusPulangDep);
			    r.add("keputusan_mahkamah",sorKeputusanMahkamah);
			    r.add("no_rujukan_tanah",txtNoProsiding);
			    r.add("kos_pengapit_hakim",Utils.RemoveSymbol(txtKosPengapit));
			    r.add("no_rujukan_mahkamah",txtNoRujPemohon);		   
			    r.add("KETERANGAN_PAMPASAN",txtKeteranganPampasan);
	    		r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
	    		r.add("kos_jpph",Utils.RemoveSymbol(txtKosJPPH));
			    r.add("nama_jpph",txtNamaJPPH);
			    r.add("kos_swasta",Utils.RemoveSymbol(txtKosSwasta));
			    r.add("nama_swasta",txtNamaSwasta);
			    r.add("syarikat_swasta",txtNamaSyarikat);
	    		r.add("ID_KEMASKINI",id_user);    		
	    		sql = r.getSQLUpdate("TBLPPTMAINBORANGO");
	    		myLogger.info("sql[update Susulan (tblpptmainborango) ] : "+sql);
	    		stmt.executeUpdate(sql);
	    		
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	    	
	  }//close updateSusulan
	public static void updateLanjutan(Hashtable data,String id_bantahan) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		String id_user = (String)data.get("id_user");	
	    		
	    		
	    		String txdTarikhLanjutanMahkamahOB = (String)data.get("txdTarikhLanjutanMahkamahOB");
	    		String txdTarikhLanjutanMahkamahPT = (String)data.get("txdTarikhLanjutanMahkamahPT");
	    	
	    		
	    		String tarikhLanjutanMahkamahOB = "to_date('" + txdTarikhLanjutanMahkamahOB + "','dd/MM/yyyy')";
	    		String tarikhLanjutanMahkamahPT = "to_date('" + txdTarikhLanjutanMahkamahPT + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		
	    		r.update("id_bantahan",id_bantahan);
	    		
			    r.add("tarikh_lanjutan_mahkamah_ob",r.unquote(tarikhLanjutanMahkamahOB) );
			    r.add("tarikh_lanjutan_mahkamah_pt",r.unquote(tarikhLanjutanMahkamahPT) );
			   
	    		r.add("ID_KEMASKINI",id_user);    		
	    		sql = r.getSQLUpdate("TBLPPTMAINBORANGO");
	    		stmt.executeUpdate(sql);
	    		
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	    	
	  }//close updateLanjutan
	public static void updatePemulanganDep(Hashtable data,String id_bantahan) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		String id_user = (String)data.get("id_user");	
	    		
	    		
	    		String sorStatusPulangDep = (String)data.get("sorStatusPulangDep");
	    		
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		
    	
	    		r.clear();
	    		
	    		r.update("id_bantahan",id_bantahan);
	    		r.add("flag_pulang_deposit",sorStatusPulangDep);
			    r.add("ID_KEMASKINI",id_user);    		
	    		sql = r.getSQLUpdate("TBLPPTMAINBORANGO");
//	    		myLogger.info("sql[update Susulan (tblpptmainborango) ] : "+sql);
	    		stmt.executeUpdate(sql);
	    		
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	    	
	  }//close updateSusulan
	
}//close class
