package ekptg.model.htp.cukai;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class FrmCukaiSenaraiFailExcelUpload {
	
	static Logger mylog = Logger.getLogger(ekptg.model.htp.cukai.FrmCukaiSenaraiFailExcelUpload.class);
	/**
	 * listing TBLHTPCUKAITEMP
	 * @return
	 * @throws Exception
	 */
	public static Vector<Hashtable<String, String>> CukaigetSenaraiFailExcel()throws Exception {
	    Db db = null;
	    String sql = "";
	   // System.out.println("Fail search in Model::" + search);
	    
	    try {
	     // System.out.println("Fail search in Model::" + search);
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();

	      sql = "select ID_CUKAITEMP, NAMA_MUKIM, NO_HAKMILIK, NO_LOT, CUKAI_KENA_BAYAR, TUNGGAKAN,"+
	      				"cukai_taliair,cukai_parit,DENDA, PENGURANGAN, CUKAI_PERLU_BAYAR, ID_NEGERI, ID_DAERAH, NO_HAKMILIKUPLOAD,"+ 
	      				"NO_LOTUPLOAD, TARIKH_MASUK from TBLHTPCUKAITEMP";
	    	  
		  mylog.info("FrmCukaiSenaraiFailExcelUpload:CukaigetSenaraiFailExcel::sql:::"+sql);

	      ResultSet rs = stmt.executeQuery(sql);
	      Vector list = new Vector();
	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  
	    	  if(rs.getDouble("cukai_parit") == 0){
	    		  
	    		  h.put("cukai_parit", "");
	    	  }else{
	    		  h.put("cukai_parit", rs.getDouble("cukai_parit"));
	    	  }
	    	  
	    	  if(rs.getDouble("cukai_taliair") == 0){
	    		  
	    		  h.put("cukai_taliair", "");
	    	  }else{
	    		  h.put("cukai_taliair", rs.getDouble("cukai_taliair"));
	    	  }
	    	  
	    	  if(rs.getDouble("cukai_taliair") == 0){
	    		  
	    		  h.put("ID_CUKAITEMP", "");
	    	  }else{
	    		  h.put("ID_CUKAITEMP", rs.getString("ID_CUKAITEMP"));
	    	  }
	    	  
	    	  if(rs.getString("NAMA_MUKIM") == null){
	    		  
	    		  h.put("NAMA_MUKIM", "");
	    	  }else{
	    		  h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM"));
	    	  }
	    	  
	    	  if(rs.getString("NO_HAKMILIK") == null){
	    		  
	    		  h.put("NO_HAKMILIK", "");
	    	  }else{
	    		  h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK"));
	    	  }
	    	  
	    	  if(rs.getString("NO_LOT") == null){
	    		  
	    		  h.put("NO_LOT", "");
	    	  }else{
	    		  h.put("NO_LOT", rs.getString("NO_LOT"));
	    	  }
	    	  
	    	  if(rs.getDouble("CUKAI_KENA_BAYAR") == 0){
	    		  
	    		  h.put("CUKAI_KENA_BAYAR", "");
	    	  }else{
	    		  h.put("CUKAI_KENA_BAYAR", rs.getDouble("CUKAI_KENA_BAYAR"));
	    	  }
	    	  
	    	  if(rs.getString("TUNGGAKAN") == null){
	    		  
	    		  h.put("TUNGGAKAN", "");
	    	  }else{
	    		  h.put("TUNGGAKAN", rs.getString("TUNGGAKAN"));
	    	  }
	    	  
	    	  if( rs.getString("DENDA") == null){
	    		  
	    		  h.put("DENDA", "");
	    	  }else{
	    		  h.put("DENDA", rs.getString("DENDA"));
	    	  }
	    	  
	    	  if( rs.getString("PENGURANGAN") == null){
	    		  
	    		  h.put("PENGURANGAN", "");
	    	  }else{
	    		  h.put("PENGURANGAN", rs.getString("PENGURANGAN"));
	    	  }
	    	  
	    	  if( rs.getString("CUKAI_PERLU_BAYAR") == null){
	    		  
	    		  h.put("CUKAI_PERLU_BAYAR", "");
	    	  }else{
	    		  h.put("CUKAI_PERLU_BAYAR", rs.getString("CUKAI_PERLU_BAYAR"));
	    	  }
	    	  
	    	  if( rs.getString("NO_HAKMILIKUPLOAD") == null){
	    		  
	    		  h.put("NO_HAKMILIKUPLOAD", "");
	    	  }else{
	    		  h.put("NO_HAKMILIKUPLOAD", rs.getString("NO_HAKMILIKUPLOAD"));
	    	  }
	    	  
	    	  if( rs.getString("NO_LOTUPLOAD") == null){
	    		  
	    		  h.put("NO_LOTUPLOAD", "");
	    	  }else{
	    		  h.put("NO_LOTUPLOAD", rs.getString("NO_LOTUPLOAD"));
	    	  }
	    	  
//	    	  h.put("cukai_parit", rs.getDouble("cukai_parit"));
//	    	  h.put("cukai_taliair", rs.getDouble("cukai_taliair"));
//	    	  h.put("ID_CUKAITEMP", rs.getString("ID_CUKAITEMP"));
//	    	  h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM"));
//	    	  h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK"));
//	    	  h.put("NO_LOT", rs.getString("NO_LOT"));
//	    	  h.put("CUKAI_KENA_BAYAR", rs.getDouble("CUKAI_KENA_BAYAR"));
//	    	  h.put("TUNGGAKAN", rs.getString("TUNGGAKAN"));
//	    	  h.put("DENDA", rs.getString("DENDA"));
//	    	  h.put("PENGURANGAN", rs.getString("PENGURANGAN"));
//	    	  h.put("CUKAI_PERLU_BAYAR", rs.getString("CUKAI_PERLU_BAYAR"));
//	    	  h.put("NO_HAKMILIKUPLOAD", rs.getString("NO_HAKMILIKUPLOAD"));
//	    	  h.put("NO_LOTUPLOAD", rs.getString("NO_LOTUPLOAD"));
	    	  
	    	  list.addElement(h);    
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	/**
	 * return list untuk compare table TEMP dengan Main
	 * @return
	 * @throws Exception
	 */
	public static Vector<Hashtable<String, String>> CukaiSenaraiKemaskiniFail()throws Exception {
	    Db db = null;
	    String sql = "";
	   // System.out.println("Fail search in Model::" + search);
	    
	    try {
	     // System.out.println("Fail search in Model::" + search);
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      sql="SELECT distinct HTPHC.ID_HAKMILIKCUKAI,HTPCT.PENGECUALIAN,HTPCT.TUNGGAKAN,HTPCT.denda,u.CUKAI_TERKINI as cukaiterkini, HTPHC.cukai_taliair as cukai_taliair2, HTPHC.cukai_parit as cukai_parit2, HTPCT.NO_HAKMILIKUPLOAD, HTPCT.cukai_taliair,HTPCT.cukai_parit,HTPCT.cukai_kena_bayar,HTPHC.cukai_parit, " +
	      		" RN.Id_Negeri,RN.NAMA_NEGERI,RD.Id_Daerah,RD.NAMA_DAERAH,RM.Id_Mukim,RM.NAMA_MUKIM , " +
	      		" u.Id_Jenishakmilik,H.KOD_JENIS_HAKMILIK,U.NO_HAKMILIK,HTPCT.NO_HAKMILIK, LOT.KETERANGAN KETERANGAN_LOT,U.NO_LOT , " +
	      		" u.TARIKH_LUPUT,u.CUKAI,u.CUKAI_TERKINI,u.LOKASI,LUAS.ID_LUAS,LUAS.KOD_LUAS,U.LUAS , " +
	      		" U.TARIKH_WARTA,U.NO_WARTA,U.NO_PELAN,U.NO_SYIT,RK.ID_KATEGORI,RK.KOD_KATEGORI,RK.KETERANGAN,U.SYARAT,U.SEKATAN,HTPHP.KEGUNAAN_TANAH , " +
	      		" HTPHC.PENGECUALIAN,HTPHC.PENGURANGAN,NVL(U.NO_FAIL,'TIADA') NO_FAIL,NVL(U.NO_FAIL_PTG,'TIADA') NO_FAIL_PTG" + //,u.ID_HAKMILIK,htphc.ID_HAKMILIKCUKAI ,u.Id_Permohonan 
	      		" FROM Tblhtphakmilik u, Tblrujjenishakmilik h, Tblrujlot lot, Tblrujluas luas, TBLRUJNEGERI RN,TBLRUJDAERAH RD , " +
	      		" TBLRUJMUKIM RM,TBLRUJKATEGORI RK,TBLHTPHAKMILIKPERIHAL HTPHP,TBLHTPHAKMILIKCUKAI HTPHC, TBLHTPCUKAITEMP HTPCT " +
	      		" where u.Id_Jenishakmilik=h.Id_Jenishakmilik " +
	      		" AND u.Id_Lot=lot.Id_Lot " +
	      		" AND u.Id_Luas=luas.Id_Luas " +
	      		" AND U.ID_KATEGORI=RK.ID_KATEGORI " +
	      		" AND U.ID_MUKIM=RM.ID_MUKIM " +
	      		" AND RM.ID_DAERAH=RD.ID_DAERAH " +
	      		" AND RD.ID_NEGERI=RN.ID_NEGERI " +
	      		" AND U.ID_HAKMILIK=HTPHP.ID_HAKMILIK " +
	      		" AND U.ID_HAKMILIK=HTPHC.ID_HAKMILIK " +
	      		" AND U.NO_LOT = HTPCT.NO_LOT " +
	      		" AND LTRIM(U.NO_HAKMILIK,0)=HTPCT.NO_HAKMILIK " +
	      		" AND u.ID_HAKMILIK not in((select hc.ID_HAKMILIK from tblhtpcukaiterperinci ct,tblhtphakmilikcukai hc " +
	      		" where hc.ID_HAKMILIKCUKAI=ct.ID_HAKMILIKCUKAI))" +
	      		" order by nama_negeri";

	     /* statement utk compare 2 table
	      * sql = "SELECT HTPCT.NO_HAKMILIKUPLOAD, HTPCT.cukai_taliair, HTPCT.cukai_parit, HTPCT.cukai_kena_bayar, HTPHC.cukai_taliair as cukai_taliair2,"+
	      " HTPHC.cukai_parit as cukai_parit2,u.Id_Permohonan,RN.Id_Negeri,RN.NAMA_NEGERI,RD.Id_Daerah,RD.NAMA_DAERAH,RM.Id_Mukim,RM.NAMA_MUKIM,"+
	      " u.Id_Jenishakmilik, H.KOD_JENIS_HAKMILIK, U.NO_HAKMILIK, LOT.KETERANGAN KETERANGAN_LOT, U.NO_LOT, "+
	      " u.TARIKH_LUPUT, u.CUKAI, u.CUKAI_TERKINI as cukaiterkini, u.LOKASI, LUAS.ID_LUAS, LUAS.KOD_LUAS, U.LUAS,"+
	      " U.TARIKH_WARTA, "+
	      " U.NO_WARTA,U.NO_PELAN,U.NO_SYIT,RK.ID_KATEGORI,RK.KOD_KATEGORI,"+
	      " RK.KETERANGAN,U.SYARAT,U.SEKATAN,HTPHP.KEGUNAAN_TANAH,"+
	      " HTPHC.PENGECUALIAN,HTPHC.PENGURANGAN,NVL(U.NO_FAIL,'TIADA') NO_FAIL,NVL(U.NO_FAIL_PTG,'TIADA') NO_FAIL_PTG "+
	      " from Tblhtphakmilik u, Tblrujjenishakmilik h, Tblrujlot lot, Tblrujluas luas, TBLRUJNEGERI RN,TBLRUJDAERAH RD,"+
	      " TBLRUJMUKIM RM,TBLRUJKATEGORI RK,TBLHTPHAKMILIKPERIHAL HTPHP,TBLHTPHAKMILIKCUKAI HTPHC, TBLHTPCUKAITEMP HTPCT"+
	      " where u.Id_Jenishakmilik=h.Id_Jenishakmilik "+
	      " AND u.Id_Lot=lot.Id_Lot"+
	      " AND u.Id_Luas=luas.Id_Luas"+
	      " AND U.ID_KATEGORI=RK.ID_KATEGORI"+
	      " AND U.ID_MUKIM=RM.ID_MUKIM"+
	      " AND RM.ID_DAERAH=RD.ID_DAERAH"+
	      " AND RD.ID_NEGERI=RN.ID_NEGERI"+
	      " AND U.ID_HAKMILIK=HTPHP.ID_HAKMILIK"+
	      " AND U.ID_HAKMILIK=HTPHC.ID_HAKMILIK"+
	      " AND U.NO_LOT = HTPCT.NO_LOT";*/
	    	  
		  mylog.info("FrmCukaiSenaraiFailExcelUpload:CukaiSenaraiKemaskiniFail::sql:::"+sql);

	      ResultSet rs = stmt.executeQuery(sql);
	      Vector list = new Vector();
	      Hashtable h;
	      //38E94-1ND38-UTHTZ-7323Y
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  
	    	  h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI"));
	    	  h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
	    	  h.put("NO_HAKMILIKUPLOAD", rs.getString("NO_HAKMILIKUPLOAD"));
	    	  h.put("ID_HAKMILIKCUKAI", rs.getString("ID_HAKMILIKCUKAI"));
	    	  h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK"));
	    	  if(rs.getString("PENGECUALIAN")!= "")
	    	  {
	    		  h.put("lebihan", 0);
	    	  }else {
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
	    	  
	    	  list.addElement(h);    
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static Vector<Hashtable<String, String>> getCukaiKemaskiniList()throws Exception {
	    Db db = null;
	    String sql = "";
	   // System.out.println("Fail search in Model::" + search);
	    
	    try {
	     // System.out.println("Fail search in Model::" + search);
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();

//	      sql="SELECT distinct htpct.BAYARAN_LAIN,htpct.CUKAI_PERLU_BAYAR,htpct.PENGECUALIAN,HTPCT.denda,HTPCT.TUNGGAKAN,HTPHC.ID_HAKMILIKCUKAI,u.CUKAI_TERKINI as cukaiterkini, HTPHC.cukai_taliair as cukai_taliair2, HTPHC.cukai_parit as cukai_parit2, HTPCT.NO_HAKMILIKUPLOAD, HTPCT.cukai_taliair,HTPCT.cukai_parit,HTPCT.cukai_kena_bayar,HTPHC.cukai_parit, " +
//    		" RN.Id_Negeri,RN.NAMA_NEGERI,RD.Id_Daerah,RD.NAMA_DAERAH,RM.Id_Mukim,RM.NAMA_MUKIM , " +
//    		" u.Id_Jenishakmilik,H.KOD_JENIS_HAKMILIK,U.NO_HAKMILIK,HTPCT.NO_HAKMILIK, LOT.KETERANGAN KETERANGAN_LOT,U.NO_LOT , " +
//    		" u.TARIKH_LUPUT,u.CUKAI,u.CUKAI_TERKINI,u.LOKASI,LUAS.ID_LUAS,LUAS.KOD_LUAS,U.LUAS , " +
//    		" U.TARIKH_WARTA,U.NO_WARTA,U.NO_PELAN,U.NO_SYIT,RK.ID_KATEGORI,RK.KOD_KATEGORI,RK.KETERANGAN,U.SYARAT,U.SEKATAN,HTPHP.KEGUNAAN_TANAH , " +
//    		" HTPHC.PENGECUALIAN,HTPHC.PENGURANGAN,NVL(U.NO_FAIL,'TIADA') NO_FAIL,NVL(U.NO_FAIL_PTG,'TIADA') NO_FAIL_PTG,HTPCT.KEGUNAAN_TANAH" + //,u.ID_HAKMILIK,htphc.ID_HAKMILIKCUKAI ,u.Id_Permohonan 
//    		" FROM Tblhtphakmilik u, Tblrujjenishakmilik h, Tblrujlot lot, Tblrujluas luas, TBLRUJNEGERI RN,TBLRUJDAERAH RD , " +
//    		" TBLRUJMUKIM RM,TBLRUJKATEGORI RK,TBLHTPHAKMILIKPERIHAL HTPHP,TBLHTPHAKMILIKCUKAI HTPHC, TBLHTPCUKAITEMP HTPCT " +
//    		" where u.Id_Jenishakmilik=h.Id_Jenishakmilik " +
//    		" AND u.Id_Lot=lot.Id_Lot " +
//    		" AND u.Id_Luas=luas.Id_Luas " +
//    		" AND U.ID_KATEGORI=RK.ID_KATEGORI " +
//    		" AND U.ID_MUKIM=RM.ID_MUKIM " +
//    		" AND RM.ID_DAERAH=RD.ID_DAERAH " +
//    		" AND RD.ID_NEGERI=RN.ID_NEGERI " +
//    		" AND U.ID_HAKMILIK=HTPHP.ID_HAKMILIK " +
//    		" AND U.ID_HAKMILIK=HTPHC.ID_HAKMILIK " +
//    		" AND U.NO_LOT = HTPCT.NO_LOT " +
//    		" AND LTRIM(U.NO_HAKMILIK,0)=HTPCT.NO_HAKMILIK " +
//    		" AND u.ID_HAKMILIK in((select hc.ID_HAKMILIK from tblhtpcukaiterperinci ct,tblhtphakmilikcukai hc " +
//    		" where hc.ID_HAKMILIKCUKAI=ct.ID_HAKMILIKCUKAI))";
	      
// keluar double	     sql = "SELECT distinct  TERP.TUNGGAKAN,TERP.DENDA,TERP.PENGECUALIAN,TERP.CUKAI_KENA_BAYAR,htpct.CUKAI_PERLU_BAYAR,HTPHC.ID_HAKMILIKCUKAI, " +
//	      "u.CUKAI_TERKINI as cukaiterkini,  HTPCT.NO_HAKMILIKUPLOAD, HTPCT.cukai_taliair,HTPCT.cukai_parit,HTPCT.cukai_kena_bayar, " +
//	      "RN.Id_Negeri,RN.NAMA_NEGERI,RD.Id_Daerah,RD.NAMA_DAERAH,RM.Id_Mukim,RM.NAMA_MUKIM , " +
//	      "u.Id_Jenishakmilik,H.KOD_JENIS_HAKMILIK, LOT.KETERANGAN KETERANGAN_LOT,U.NO_LOT , " +
//	      "u.TARIKH_LUPUT,u.CUKAI,u.CUKAI_TERKINI,u.LOKASI,LUAS.ID_LUAS,LUAS.KOD_LUAS,U.LUAS ,  " +
//	      "U.NO_PELAN,U.NO_SYIT,RK.ID_KATEGORI,RK.KOD_KATEGORI,RK.KETERANGAN,HTPHP.KEGUNAAN_TANAH , " + 
//	      "NVL(U.NO_FAIL,'TIADA') NO_FAIL,NVL(U.NO_FAIL_PTG,'TIADA') NO_FAIL_PTG,HTPCT.KEGUNAAN_TANAH   " + 
//	      "FROM Tblhtphakmilik u, Tblrujjenishakmilik h, Tblrujlot lot, Tblrujluas luas, TBLRUJNEGERI RN,TBLRUJDAERAH RD ,  " +
//	      "TBLRUJMUKIM RM,TBLRUJKATEGORI RK,TBLHTPHAKMILIKPERIHAL HTPHP,TBLHTPHAKMILIKCUKAI HTPHC, TBLHTPCUKAITEMP HTPCT , TBLHTPCUKAITERPERINCI TERP, tblhtpcukaiterperinci ct " +
//	      "where u.Id_Jenishakmilik=h.Id_Jenishakmilik  " +
//	      " AND u.Id_Lot=lot.Id_Lot  " +
//	      " AND u.Id_Luas=luas.Id_Luas  " +
//	      " AND U.ID_KATEGORI=RK.ID_KATEGORI " +
//	      " AND U.ID_MUKIM=RM.ID_MUKIM  " +
//	      " AND RM.ID_DAERAH=RD.ID_DAERAH  " +
//	      " AND RD.ID_NEGERI=RN.ID_NEGERI  " +
//	      " AND U.ID_HAKMILIK=HTPHP.ID_HAKMILIK  " +
//	      " AND U.ID_HAKMILIK=HTPHC.ID_HAKMILIK  " +
//	      "	and HTPHC.ID_HAKMILIKCUKAI=ct.ID_HAKMILIKCUKAI"+
//	      " AND U.NO_LOT = HTPCT.NO_LOT  " +
//	      " AND LTRIM(U.NO_HAKMILIK,0)=HTPCT.NO_HAKMILIK  " +
//	      " AND u.ID_HAKMILIK in((select hc.ID_HAKMILIK from tblhtpcukaiterperinci ct,tblhtphakmilikcukai hc  " +
//	      " where hc.ID_HAKMILIKCUKAI=ct.ID_HAKMILIKCUKAI)) " ;
	      
	      sql="SELECT distinct  ct.TUNGGAKAN,ct.DENDA,ct.PENGECUALIAN,ct.CUKAI_KENA_BAYAR,HTPHC.ID_HAKMILIKCUKAI, "+
	    	  " u.CUKAI_TERKINI as cukaiterkini, ct.cukai_parit, ct.cukai_taliair,HTPCT.NO_HAKMILIKUPLOAD, "+
	    	  " RN.Id_Negeri,RN.NAMA_NEGERI,RD.Id_Daerah,RD.NAMA_DAERAH,RM.Id_Mukim,RM.NAMA_MUKIM ,"+
	    	  " u.Id_Jenishakmilik,H.KOD_JENIS_HAKMILIK, LOT.KETERANGAN KETERANGAN_LOT,U.NO_LOT ,"+
	    	  " u.TARIKH_LUPUT,u.CUKAI,u.CUKAI_TERKINI,u.LOKASI,LUAS.ID_LUAS,LUAS.KOD_LUAS,U.LUAS , "+
	    	  " U.NO_PELAN,U.NO_SYIT,RK.ID_KATEGORI,RK.KOD_KATEGORI,RK.KETERANGAN,HTPHP.KEGUNAAN_TANAH , "+
	    	  " NVL(U.NO_FAIL,'TIADA') NO_FAIL,NVL(U.NO_FAIL_PTG,'TIADA') NO_FAIL_PTG"+
	    	  " FROM Tblhtphakmilik u, Tblrujjenishakmilik h, Tblrujlot lot, Tblrujluas luas, TBLRUJNEGERI RN,TBLRUJDAERAH RD ,  "+
	    	  " TBLRUJMUKIM RM,TBLRUJKATEGORI RK,TBLHTPHAKMILIKPERIHAL HTPHP,TBLHTPHAKMILIKCUKAI HTPHC, TBLHTPCUKAITEMP HTPCT, tblhtpcukaiterperinci ct"+
	    	  " where u.Id_Jenishakmilik=h.Id_Jenishakmilik "+
	    	  " AND u.Id_Lot=lot.Id_Lot "+
	    	  " AND u.Id_Luas=luas.Id_Luas "+
	    	  " AND U.ID_KATEGORI=RK.ID_KATEGORI "+
	    	  " AND U.ID_MUKIM=RM.ID_MUKIM "+
	    	  " AND RM.ID_DAERAH=RD.ID_DAERAH "+
	    	  " AND RD.ID_NEGERI=RN.ID_NEGERI "+
	    	  " AND U.ID_HAKMILIK=HTPHP.ID_HAKMILIK "+
	    	  " AND U.ID_HAKMILIK=HTPHC.ID_HAKMILIK "+
	    	  " and HTPHC.ID_HAKMILIKCUKAI=ct.ID_HAKMILIKCUKAI"+
	    	  " AND U.NO_LOT = HTPCT.NO_LOT "+
	    	  " AND LTRIM(U.NO_HAKMILIK,0)=HTPCT.NO_HAKMILIK "+
	    	  " AND u.ID_HAKMILIK in((select hc.ID_HAKMILIK from tblhtpcukaiterperinci ct,tblhtphakmilikcukai hc "+
	    	  "where hc.ID_HAKMILIKCUKAI=ct.ID_HAKMILIKCUKAI)) order by RD.NAMA_DAERAH";
	    	  
		  mylog.info("FrmCukaiSenaraiFailExcelUpload:CukaiKemaskiniList::sql:::"+sql);

	      ResultSet rs = stmt.executeQuery(sql);
	      Vector list = new Vector();
	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  
	    	  //h.put("a", rs.getString("NAMA_NEGERI"));
	    	  h.put("b", rs.getString("NAMA_DAERAH"));
	    	  h.put("c", rs.getString("NAMA_MUKIM"));
	    	  h.put("d", rs.getString("KOD_JENIS_HAKMILIK"));
	    	  h.put("e", rs.getString("NO_HAKMILIKUPLOAD"));
	    	  //h.put("f", rs.getString("NO_HAKMILIK"));
	    	  h.put("g", rs.getDouble("cukai_parit"));
	    	  h.put("h", rs.getDouble("cukai_taliair"));
	    	  h.put("i", rs.getDouble("CUKAI_KENA_BAYAR"));
	    	  h.put("j", rs.getDouble("TUNGGAKAN")); 
	    	  h.put("k", rs.getDouble("DENDA"));
	    	  //h.put("l", rs.getDouble("CUKAI_PERLU_BAYAR"));
	    	  h.put("m", rs.getString("NO_LOT"));
	    	  h.put("n", rs.getString("KETERANGAN_LOT"));
	    	  h.put("o", rs.getString("LUAS"));
	    	  h.put("p", rs.getString("KEGUNAAN_TANAH"));
	    	  if(rs.getString("PENGECUALIAN")!=""){
	    		  h.put("q", rs.getDouble("PENGECUALIAN"));
	    	  }else{
	    	  h.put("q", "0");
	    	  }
//	    	  if(rs.getString("BAYARAN_LAIN")!=""){
//	    		  h.put("r", rs.getDouble("BAYARAN_LAIN"));
//	    	  }else{
//	    	  h.put("r", "0");
//	    	  }
	    	  list.addElement(h);    
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }

}
