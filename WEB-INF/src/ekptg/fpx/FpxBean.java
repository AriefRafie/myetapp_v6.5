package ekptg.fpx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.fpx.entity.DataFpx;
import ekptg.helpers.DB;

public abstract class FpxBean{
	
	public abstract DataFpx doProcess(DataFpx fpx);
	private IJenisBayaran jenisBayaranBean;
	
	public FpxBean(){
		if(jenisBayaranBean == null)
			jenisBayaranBean = new JenisBayaranBean();
	}
	public DataFpx save(DataFpx fpx) {

		Connection conn = null;
	    Db db = null;
	    String sql = "";

	    try
	    {
	    	db = new Db();
	    	conn = db.getConnection();
			conn.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	
	    	String id_user = fpx.getIdMasuk();
	    	String id_jenisbayaran = fpx.getIdJenisBayaran();	       
	    	String txtNoFail = fpx.getNoFail();
	    	double txtAmaun = fpx.getAmount();
	    	String txtCatatan = fpx.getCatatan();
	    	String txtSysdate = fpx.getTarikhMasuk();
	    	String txtNama = fpx.getNama();
	    	String txtNoPengenalan = fpx.getNoKp();	    	
	    	String sorFlagJenisPembayar = fpx.getType();
	    	String txtNoBil = fpx.getNoBil();
	    	String status = fpx.getStatus();
	    	String noTransaksi  = fpx.getNoTransaksi();
	    	//convert string to date
	    	String TS = "to_date('" + txtSysdate + "','dd/MM/yyyy hh:mi:ss am')";
	
	    	//generate no permohonan "JKPTG/PPT/kod_suburusan/this_year-000001
	    	long id_datafpx = DB.getNextID("TBLDATAFPX_SEQ");    
	    	fpx.setId(String.valueOf(id_datafpx));
	    	SQLRenderer r = new SQLRenderer();	
	     	r.add("id_datafpx",id_datafpx);
	     	r.add("id_jenisbayaran",id_jenisbayaran);
	     	
	     	r.add("nama",txtNama);
	     	r.add("no_kp",txtNoPengenalan);
	     	r.add("jenis_pembayar",sorFlagJenisPembayar);
	     	r.add("no_bil",txtNoBil);
	     	
	     	r.add("no_fail",txtNoFail);
	     	r.add("amaun_bayaran",txtAmaun);
	     	r.add("catatan",txtCatatan);
	     	r.add("tarikh_bayaran", r.unquote("sysdate"));
	     	r.add("tarikh_masuk",r.unquote("sysdate"));
			r.add("id_masuk",id_user);
			r.add("status_transaksi",status);
			r.add("no_transaksi",noTransaksi);
	     	sql = r.getSQLInsert("tbldatafpx");
	     	
	     	stmt.executeUpdate(sql);
	     	conn.commit();	
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	db.close();
	    }
	    return fpx;
		
	}

	public DataFpx update(DataFpx fpx) {
		Connection conn = null;
	    Db db = null;
	    String sql = "";
	   
	    try
	    {
	    	db = new Db();
	    	conn = db.getConnection();
			conn.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	//sql ="UPDATE TBLDATAFPX SET STATUS_TRANSAKSI ='"+fpx.getStatus()+"' WHERE NO_TRANSAKSI='"+fpx.getNoTransaksi()+"'";
	    	sql ="UPDATE TBLDATAFPX SET STATUS_TRANSAKSI ='"+fpx.getStatus()+"',INDIRECT_MESSAGE='"+fpx.getIndirectMessage()+"',NO_RESIT='"+fpx.getNoResit()+"' WHERE NO_TRANSAKSI='"+fpx.getNoTransaksi()+"'";
	    	
	    	stmt.executeUpdate(sql);
	    	conn.commit();
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	db.close();
	    }
		return fpx;
	}

	public DataFpx get(String idFpx) {

		Connection conn = null;
	    Db db = null;
	    String sql = "";
	    
	    DataFpx fpx = null;
	    try
	    {
	    	db = new Db();
	    	conn = db.getConnection();
			conn.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	
	    	sql ="SELECT * FROM TBLDATAFPX WHERE no_transaksi='"+idFpx+"'";
	    	ResultSet rs = stmt.executeQuery(sql);
	    	if(rs.next()){
	    		fpx = new DataFpx();
	    		fpx.setId(rs.getString("id_datafpx"));
	    		fpx.setNoTransaksi(rs.getString("no_transaksi"));
	    		fpx.setAmount(rs.getDouble("amaun_bayaran"));
	    		fpx.setStatus(rs.getString("status_transaksi"));
	    		fpx.setTransactionDate(rs.getDate("tarikh_masuk"));
	    		fpx.setNoKp(rs.getString("no_kp"));
	    		fpx.setNama(rs.getString("nama"));
	    		fpx.setNoFail(rs.getString("no_fail"));
	    		fpx.setCatatan(rs.getString("catatan"));
	    		fpx.setNoBil(rs.getString("no_bil"));
	    		fpx.setDirectMessage(rs.getString("direct_message"));
	    		fpx.setIdJenisBayaran(rs.getString("id_jenisbayaran"));
	    		fpx.setJenisBayaran(getJenisBayaran(fpx.getIdJenisBayaran()));
	    		fpx.setjBayaran(jenisBayaranBean.getJenisBayaran(rs.getString("id_jenisbayaran")));
	    		fpx.setIndirectMessage(rs.getString("indirect_message"));
	    	}
	    	
	    	
	    }catch(Exception e){
		    	e.printStackTrace();
		    }
		    finally{
		    	db.close();
		    }
		return fpx;
	}

	public void delete(String idFpx) {

		Connection conn = null;
	    Db db = null;
	    String sql = "";
	 
	    try
	    {
	    	db = new Db();
	    	conn = db.getConnection();
			conn.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sql = "DELETE TBLDATAFPX WHERE ID_DATAFPX="+idFpx;
	    	stmt.executeUpdate(sql);
	    	conn.commit();
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	db.close();
	    }
		
	}
	
	public Vector<DataFpx> search(String ic,String noFail,String tarikh,String nama,String jenisBayaran){
		Vector<DataFpx> v = new Vector<DataFpx>();
		Connection conn = null;
	    Db db = null;
	    String sql = "";
	 
	    String noic = ic.trim();
	    String Nama = nama.trim();
	    SimpleDateFormat fmt =  new SimpleDateFormat("dd/MM/yyyy");
	    try
	    {
	    	db = new Db();
	    	conn = db.getConnection();
	    	sql ="SELECT * FROM TBLDATAFPX WHERE TARIKH_BAYARAN >= TRUNC(SYSDATE)-90";
	    	
	    	//IC
	    	if (!noic.equals("")){					
				sql += " AND UPPER(NO_KP) LIKE UPPER('%" + noic + "%') ";
			}
			
	    	//NAMA
	    	if (!Nama.equals("")){					
				sql += " AND UPPER(NAMA) LIKE UPPER('%" + Nama + "%') ";
			}
	    	if(!jenisBayaran.equals("")){
	    		sql += " AND ID_JENISBAYARAN =" + jenisBayaran ;
	    	}
	    	sql +=" ORDER BY TARIKH_MASUK DESC";
	    	Statement stmt = db.getStatement();
	    	ResultSet rs = stmt.executeQuery(sql);
	    	while(rs.next()){
	    		DataFpx fpx = new DataFpx();
	    		fpx = new DataFpx();
	    		fpx.setId(rs.getString("id_datafpx"));
	    		fpx.setNoTransaksi(rs.getString("no_transaksi"));
	    		fpx.setAmount(rs.getDouble("amaun_bayaran"));
	    		fpx.setStatus(rs.getString("status_transaksi"));
	    		fpx.setTransactionDate(rs.getDate("tarikh_masuk"));
	    		fpx.setNama(rs.getString("nama"));
	    		fpx.setNoKp(rs.getString("no_kp"));
	    		fpx.setNoFail(rs.getString("no_fail"));
	    		if(rs.getString("tarikh_bayaran") != null)
	    			fpx.setTarikhMasuk(fmt.format(rs.getDate("tarikh_bayaran")));
	    		
	    		fpx.setDirectMessage(rs.getString("direct_message"));
	    		fpx.setIndirectMessage(rs.getString("indirect_message"));
	    		v.addElement(fpx);
	    	}
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	db.close();
	    }
		
		return v;
	}

	
	@SuppressWarnings("unchecked")
	private static  Vector listHistory = null;
	
	@SuppressWarnings("unchecked")
	public static Vector getListHistory(){
		return listHistory;
	}
	
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
		//view list dokumen by id
	 	@SuppressWarnings("unchecked")
	 	public static void setListHistory(String id_user)throws Exception {
		    
	 		listHistory = new Vector();
	 		Db db = null;
	 		listHistory.clear();
		    String sql = "";
		    
		    try {
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
			     
		    		sql =  " SELECT UPPER(A.NO_FAIL)AS NO_FAIL, B.KETERANGAN AS JENIS_BAYARAN, A.AMAUN_BAYARAN, ";
		    		sql += " CATATAN, TO_CHAR(A.TARIKH_BAYARAN,'DD') AS HARI_BAYARAN, A.NO_RESIT, B.KOD_JENIS_BAYARAN, ";
		    		sql += " UPPER(A.NAMA), UPPER(A.NO_KP)AS NO_KP, A.NO_BIL, A.NO_TRANSAKSI, A.STATUS_TRANSAKSI, ";
		    		sql += " CASE ";
		    		sql += " WHEN A.JENIS_PEMBAYAR = '1' THEN 'INDIVIDU' ";
		    		sql += " WHEN A.JENIS_PEMBAYAR = '2' THEN 'SYARIKAT' ";
					sql += " ELSE '' ";
					sql += " END AS JENIS_PEMBAYAR, ";
					sql += " A.TARIKH_BAYARAN, A.ID_MASUK,A.ID_DATAFPX ";  
					sql += " FROM TBLDATAFPX A, TBLRUJJENISBAYARAN B ";
					sql += " WHERE A.ID_JENISBAYARAN = B.ID_JENISBAYARAN(+) ";  
					sql += " AND TO_DATE(SYSDATE) - TO_DATE(A.TARIKH_BAYARAN) < 90 ";
					sql += " AND A.ID_MASUK = '"+id_user+"'";
					sql+= " ORDER BY A.TARIKH_BAYARAN DESC";

		    		ResultSet rs = stmt.executeQuery(sql);
		    		
					Hashtable h;
					int bil = 1;
					
		    		while (rs.next()) {
		    	  
		    			h = new Hashtable();
		    	 
		    			h.put("bil", bil);
		    			h.put("ID_FPX", rs.getString("ID_DATAFPX")== null?"":rs.getString("ID_DATAFPX"));
		    			h.put("JENIS_BAYARAN", rs.getString("JENIS_BAYARAN")== null?"":rs.getString("JENIS_BAYARAN"));
		    			h.put("AMAUN_BAYARAN", rs.getString("AMAUN_BAYARAN")== null?"":rs.getString("AMAUN_BAYARAN"));
		    			h.put("NO_TRANSAKSI", rs.getString("NO_TRANSAKSI")== null?"":rs.getString("NO_TRANSAKSI"));
		    			if(rs.getString("STATUS_TRANSAKSI").equals("SUCCESSFUL"))
		    				h.put("STATUS_TRANSAKSI", "BERJAYA");
		    			else
		    				h.put("STATUS_TRANSAKSI", "TIDAK BERJAYA");
		    			h.put("TARIKH_BAYARAN", rs.getString("TARIKH_BAYARAN")==null?"":Format.format(rs.getDate("TARIKH_BAYARAN")));
		    			h.put("NILAI",FpxUtility.generateWordEncodeURL(rs.getDouble("AMAUN_BAYARAN")).toUpperCase());
		    			listHistory.addElement(h);
		    			bil++;	    	
		    		}			    
		    		//return list;
		    		
		    } finally {
		      if (db != null) db.close();
		    }
		}
	 	@Deprecated
	 	public String getJenisBayaran(String idJenisBayaran){

			Connection conn = null;
		    Db db = null;
		    String sql = "";
		    String keterangan = "";
		    try
		    {
		    	db = new Db();
		    	conn = db.getConnection();
				conn.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sql ="SELECT KETERANGAN FROM TBLRUJJENISBAYARAN WHERE ID_JENISBAYARAN="+idJenisBayaran;
		    	ResultSet rs = stmt.executeQuery(sql);
		    	if(rs.next()){
		    		keterangan = rs.getString("KETERANGAN");
		    	}
		    }
		    catch(Exception e){
		    	e.printStackTrace();
		    }
		    finally{
		    	db.close();
		    }
		    return keterangan;
	 		
	 	}
	 	
	 	protected String getReceiptNo(String noTransaksi){
			DataFpx temp = get(noTransaksi);
			Calendar cal = Calendar.getInstance();
			cal.setTime(temp.getTransactionDate());
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH)+1;
			String receipt = new FpxReceipt().getReceiptNo(String.valueOf(year));
			
			return receipt;
		}
}
