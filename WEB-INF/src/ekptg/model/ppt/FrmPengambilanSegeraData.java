package ekptg.model.ppt;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import ekptg.helpers.EkptgCache;
public class FrmPengambilanSegeraData extends EkptgCache implements Serializable{
	
	static Logger myLogger = Logger.getLogger(FrmPembatalanInternalData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	public static final String SEQ_TABLE = "TBLRUJSEQFAIL";	
	
	
	Vector senarai_pembatalan_carian = null;	
	public Vector senarai_pembatalan_carian(String no_fail,String no_jkptg_negeri,String id_kementerian,String id_urusan,String id_status,String jenis_permohon)throws Exception{
		senarai_pembatalan_carian = new Vector();	
		Db db = null;
		senarai_pembatalan_carian.clear();
		String sql = "";
		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT P.ID_PERMOHONAN,B.ID_PEMBATALAN,P.TARIKH_PERMOHONAN,B.NO_PEMBATALAN ,B.TARIKH_PEMBATALAN,F.NO_FAIL,P.NO_RUJUKAN_UPT,K.NAMA_KEMENTERIAN,D.NAMA_DAERAH,S.KETERANGAN" +
					" FROM TBLPPTPERMOHONAN P, TBLPPTPEMBATALAN B,TBLPFDFAIL F,TBLRUJKEMENTERIAN K,TBLRUJDAERAH D,TBLRUJSTATUS S " +
					" WHERE P.ID_PERMOHONAN = B.ID_PERMOHONAN(+)" +
					" AND P.ID_FAIL = F.ID_FAIL(+)" +
					" AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+)" +
					" AND P.ID_DAERAH = D.ID_DAERAH(+)" +
					" AND P.FLAG_JENISPERMOHONAN = '"+jenis_permohon+"'" +				
					" AND P.ID_STATUS = S.ID_STATUS";
			//kena filter by status (belom diwartakan)
			
			 if (no_fail != "") {
					if (!no_fail.trim().equals("")) {
						sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%" + no_fail.toUpperCase().trim() + "%'";
					}
				}
			 if (no_jkptg_negeri != "") {
					if (!no_jkptg_negeri.trim().equals("")) {
						sql = sql + " AND UPPER(P.NO_RUJUKAN_UPT) LIKE '%" + no_jkptg_negeri.toUpperCase().trim() + "%'";
					}
				}
			 if (id_urusan != "") {
					if (!id_urusan.trim().equals("")) {
						sql = sql + " AND UPPER(F.ID_SUBURUSAN) LIKE '" + id_urusan.toUpperCase() + "'";
					}
				}
			 if (id_kementerian != "") {
					if (!id_kementerian.trim().equals("")) {
						sql = sql + " AND UPPER(F.ID_KEMENTERIAN) LIKE '" + id_kementerian.toUpperCase() + "'";
					}
				}
			 if (id_status != "") {
					if (!id_status.trim().equals("")) {
						sql = sql + " AND UPPER(P.ID_STATUS) LIKE '" + id_status.toUpperCase() + "'";
					}
				}
			
			sql += " ORDER BY P.TARIKH_KEMASKINI DESC";
			
			myLogger.info("SQL BATAL CARI :"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL",bil);				
				h.put("ID_PERMOHONAN",rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("ID_PEMBATALAN",rs.getString("ID_PEMBATALAN") == null ? "" : rs.getString("ID_PEMBATALAN"));				
				h.put("TARIKH_PERMOHONAN", rs.getString("TARIKH_PERMOHONAN")==null?"":Format.format(rs.getDate("TARIKH_PERMOHONAN")));
				h.put("NO_PEMBATALAN",rs.getString("NO_PEMBATALAN") == null ? "" : rs.getString("NO_PEMBATALAN").toUpperCase());
				h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "default" : rs.getString("NO_FAIL").toUpperCase());
				h.put("TARIKH_PEMBATALAN", rs.getString("TARIKH_PEMBATALAN")==null?"":Format.format(rs.getDate("TARIKH_PEMBATALAN")));
				h.put("NO_RUJUKAN_UPT",rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs.getString("NO_RUJUKAN_UPT").toUpperCase());				
				h.put("NAMA_KEMENTERIAN",rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("NAMA_DAERAH",rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				senarai_pembatalan_carian.addElement(h);				
			}
			return senarai_pembatalan_carian;
		}finally{
			if (db != null)
				db.close();
		}
	}
	
	//UPDATE
	public void updateData(Hashtable data) throws Exception {
		
		String sql = "";
		String id_permohonan = (String)data.get("id_permohonan");
		String txdTarikhHantar = (String)data.get("txdTarikhHantar");
		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
		String txdTarikhKeputusan = (String)data.get("txdTarikhKeputusan");
		String id_Masuk = (String)data.get("id_Masuk");
		
		
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.update("ID_PERMOHONAN", id_permohonan);
			r.add("TARIKH_HANTAR",r.unquote("to_date('" + txdTarikhHantar + "','dd/MM/yyyy')")); 
			r.add("TARIKH_TERIMA",r.unquote("to_date('" + txdTarikhTerima + "','dd/MM/yyyy')")); 
			r.add("TARIKH_KEPUTUSAN", txdTarikhKeputusan);
			r.add("ID_MASUK", id_Masuk);
		    r.add("ID_KEMASKINI", id_Masuk);
		    sql = r.getSQLUpdate("TBLPPTMMKKEPUTUSAN");
		    
		    myLogger.info("SQL INSERT :" + sql.toUpperCase());
		    stmt.executeUpdate(sql);
		    
		}
		
		finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	Vector header = null;	
	public  Vector content_header(String id_permohonan)throws Exception {
			 header = new Vector();	
			Db db = null;
			header.clear();
			String sql = "";

			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
		
				sql = "SELECT P.ID_PERMOHONAN, F.ID_NEGERI, P.ID_NEGERI AS ID_NEGERI_PROJECT, P.NO_PERMOHONAN, "+
					"P.ID_FAIL,  F.NO_FAIL, F.ID_SUBURUSAN, P.TARIKH_PERMOHONAN, P.ID_STATUS,  F.ID_KEMENTERIAN, "+
					"P.ID_AGENSI, P.FLAG_PERUNTUKAN, P.FLAG_SEGERA,  P.ID_DAERAH, P.TUJUAN, P.NO_RUJUKAN_SURAT, "+
					"P.TARIKH_KEHENDAKI,  P.ALAMAT1, P.ALAMAT2, P.ALAMAT3, P.POSKOD, P.ID_MUKIM, "+ 
					"K.NAMA_KEMENTERIAN, B.NAMA_DAERAH, P.NO_RUJUKAN_PTD, P.NO_RUJUKAN_PTG, "+ 
					"P.NO_RUJUKAN_UPT, SU.NAMA_SUBURUSAN, S.KETERANGAN, C.NAMA_NEGERI,P.TARIKH_TERIMA,AG.NAMA_AGENSI "+  
					"FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLRUJKEMENTERIAN K,TBLRUJNEGERI C,  "+
					"TBLRUJDAERAH B,TBLRUJSUBURUSAN SU,TBLRUJSTATUS S, TBLRUJAGENSI AG  "+
					"WHERE "+
					"P.ID_PERMOHONAN = '"+id_permohonan+"' "+
					"AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+) "+ 
					"AND P.ID_FAIL = F.ID_FAIL(+) "+
					"AND P.ID_DAERAH = B.ID_DAERAH(+) "+
					"AND F.ID_SUBURUSAN = SU.ID_SUBURUSAN(+) "+
					"AND P.ID_STATUS = S.ID_STATUS "+
					"AND F.ID_NEGERI = C.ID_NEGERI(+) "+
					"AND P.ID_AGENSI = AG.ID_AGENSI(+)";
				
				myLogger.info("SQL setHeader :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
			    
		     while (rs.next()) {
		    	h = new Hashtable();		    	 
		        h.put("id_permohonan", rs.getString("id_permohonan")==null?"-":rs.getString("id_permohonan"));
		    
		    	
		    	h.put("txtNoLot", rs.getString("no_fail")==null?"-":rs.getString("no_fail").toUpperCase());
		    	h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"-":rs.getString("nama_kementerian").toUpperCase());
		    	h.put("nama_daerah", rs.getString("nama_daerah")==null?"-":rs.getString("nama_daerah").toUpperCase());
		    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"-":rs.getString("no_rujukan_ptd").toUpperCase());
		    	h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"-":rs.getString("no_rujukan_ptg").toUpperCase());
		    	h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"-":rs.getString("no_rujukan_upt").toUpperCase());
		    	h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"-":rs.getString("nama_suburusan").toUpperCase());
		    	h.put("keterangan", rs.getString("keterangan")==null?"-":rs.getString("keterangan").toUpperCase());
		    	h.put("nama_agensi", rs.getString("nama_agensi")==null?"-":rs.getString("nama_agensi").toUpperCase());
		    	h.put("projek_negeri", rs.getString("nama_negeri")==null?"-":rs.getString("nama_negeri").toUpperCase());
		    	if(rs.getString("id_suburusan") == null){
		    		h.put("id_suburusan","");
		    	}else{
		    		h.put("id_suburusan",rs.getString("id_suburusan"));
		    	}
		    	h.put("tarikh_permohonan", rs.getString("tarikh_permohonan")==null?"-":Format.format(rs.getDate("tarikh_permohonan")));
		    	h.put("tarikh_terima", rs.getString("tarikh_terima")==null?"-":Format.format(rs.getDate("tarikh_terima")));
		    	h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
		    	if(rs.getString("id_kementerian") == null){
		    		h.put("id_kementerian","");
		    	}else{
		    		h.put("id_kementerian",rs.getString("id_kementerian"));
		    	}
		    	if(rs.getString("id_agensi") == null){
		    		h.put("id_agensi","");
		    	}else{
		    		h.put("id_agensi",rs.getString("id_agensi"));
		    	}
		    	h.put("flag_peruntukan", rs.getString("flag_peruntukan")==null?"":rs.getString("flag_peruntukan"));
		    	h.put("flag_segera", rs.getString("flag_segera")==null?"":rs.getString("flag_segera"));
		    	if(rs.getString("ID_NEGERI_PROJECT") == null){
		    		h.put("id_negeri_projek","");
		    	}else{
		    		h.put("id_negeri_projek",rs.getString("ID_NEGERI_PROJECT"));
		    	}
		    	if(rs.getString("id_daerah") == null){
		    		h.put("id_daerah","");
		    	}else{
		    		h.put("id_daerah",rs.getString("id_daerah"));
		    	}
		    	h.put("tujuan", rs.getString("tujuan")==null?"-":rs.getString("tujuan").toUpperCase());
		    	h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")==null?"-":rs.getString("no_rujukan_surat").toUpperCase());
		    	h.put("tarikh_kehendaki", rs.getString("tarikh_kehendaki")==null?"-":Format.format(rs.getDate("tarikh_kehendaki")));
		    	h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1").toUpperCase());
		    	h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2").toUpperCase());
		    	h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3").toUpperCase());
		    	h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
		    	if(rs.getString("id_negeri") == null){
		    		h.put("id_negeri","");
		    	}else{
		    		h.put("id_negeri",rs.getString("id_negeri"));
		    	}
		    	if(rs.getString("id_mukim") == null){
		    		h.put("id_mukim","");
		    	}else{
		    		h.put("id_mukim",rs.getString("id_mukim"));
		    	}
		    	header.addElement(h);
		      	}      
				}
				finally{
					if(db != null)db.close();
				}
			return header;
		 }
	
	
	
	Vector list_kementerian = null;
	public Vector list_kementerian() throws Exception {
		 list_kementerian = new Vector();
		Vector key = list_kementerian;		
		Element cachedObject = myCache.get(key);
		myLogger.info("CACHED KEMENTERIAN :"+myCache.get(key));
		if (cachedObject != null) {
			myLogger.info("CACHED L1");
		  return (Vector)cachedObject.getObjectValue();
		} else {			
			myLogger.info("CACHED L2");
		Db db = null;
		list_kementerian.clear();
		String sql = "";		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_KEMENTERIAN, KOD_KEMENTERIAN, NAMA_KEMENTERIAN FROM TBLRUJKEMENTERIAN ORDER BY KOD_KEMENTERIAN ASC ";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_KEMENTERIAN",rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN"));
				h.put("KOD_KEMENTERIAN", rs.getString("KOD_KEMENTERIAN") == null ? "" : rs.getString("KOD_KEMENTERIAN").toUpperCase());
				h.put("NAMA_KEMENTERIAN", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				list_kementerian.addElement(h);
			}
			
			myCache.put(new Element(key, list_kementerian));
			return list_kementerian;
		} finally {
			if (db != null)
				db.close();
		}
		}
	}
	
	Vector list_urusan = null;
	public Vector list_urusan() throws Exception {
		list_urusan = new Vector();
		Db db = null;
		list_urusan.clear();
		String sql = "";		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_SUBURUSAN,KOD_SUBURUSAN,NAMA_SUBURUSAN FROM TBLRUJSUBURUSAN WHERE ID_SEKSYEN = 1";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_SUBURUSAN",rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));
				h.put("KOD_SUBURUSAN", rs.getString("KOD_SUBURUSAN") == null ? "" : rs.getString("KOD_SUBURUSAN").toUpperCase());
				h.put("NAMA_SUBURUSAN", rs.getString("NAMA_SUBURUSAN") == null ? "" : rs.getString("NAMA_SUBURUSAN").toUpperCase());
				list_urusan.addElement(h);
			}
			return list_urusan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Vector list_status = null;
	public Vector list_status() throws Exception {
		list_status = new Vector();
		Db db = null;
		list_status.clear();
		String sql = "";		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_STATUS,KOD_STATUS,KETERANGAN FROM TBLRUJSTATUS WHERE ID_SEKSYEN = 1 ORDER BY KOD_STATUS ASC";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_STATUS",rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("KOD_STATUS", rs.getString("KOD_STATUS") == null ? "" : rs.getString("KOD_STATUS").toUpperCase());
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				list_status.addElement(h);
			}
			return list_status;
		} finally {
			if (db != null)
				db.close();
		}
	}
	//ADD
	public void addData(Hashtable data) throws Exception {
		
		String sql = "";
		String txdTarikhHantar = (String)data.get("txdTarikhHantar");
		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
		String txdTarikhKeputusan = (String)data.get("txdTarikhKeputusan");
		String id_permohonan = (String)data.get("id_permohonan");
		
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("TARIKH_HANTAR", r.unquote("to_date('" + txdTarikhHantar + "','dd/MM/yyyy')"));
			r.add("TARIKH_TERIMA", r.unquote("to_date('" + txdTarikhTerima + "','dd/MM/yyyy')"));
			r.add("TARIKH_KEPUTUSAN", r.unquote("to_date('" + txdTarikhKeputusan + "','dd/MM/yyyy')"));
			//r.add("ID_PERMOHANAN", id_permohonan);
		   
		    
		    sql = r.getSQLInsert("TBLPPTMMKKEPUTUSAN");
		    
		    myLogger.info("SQL INSERT :" + sql.toUpperCase());
		    stmt.executeUpdate(sql);
		    
		}
		
		finally {
			if (db != null)
				db.close();
		}
	}
	
	//PAPAR
	Vector papar_data = null;	
	public Vector papar_data(String id_permohonan) throws Exception {
		papar_data = new Vector();
		String sql = "";
		Db db = null;
			
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT P.ID_PERMOHONAN,Q.TARIKH_BORANGI,R.ID_MMK,S.TARIKH_HANTAR,S.TARIKH_TERIMA,S.TARIKH_KEPUTUSAN " +
						"FROM TBLPPTPERMOHONAN P, TBLPPTBORANGI Q, TBLPPTMMK R, TBLPPTMMKKEPUTUSAN S " +
						"WHERE P.ID_PERMOHONAN = '"+id_permohonan+"'"  +
						"AND R.ID_PERMOHONAN = P.ID_PERMOHONAN";
				
				myLogger.info("SQL VIEW:"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
			
				while (rs.next()) {
					h = new Hashtable();	
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
					h.put("TARIKH_HANTAR", rs.getString("TARIKH_HANTAR")==null?"":rs.getString("TARIKH_HANTAR"));
					h.put("TARIKH_TERIMA", rs.getString("TARIKH_TERIMA")==null?"":rs.getString("TARIKH_TERIMA"));
					h.put("TARIKH_KEPUTUSAN", rs.getString("TARIKH_KEPUTUSAN")==null?"":rs.getString("TARIKH_KEPUTUSAN"));
					papar_data.addElement(h);								
				}
			}
			finally {
				if (db != null)
					db.close();
			}	
		return papar_data;
	}	
	
public void updateSimati(Hashtable data) throws Exception {
		
		String sql = "";
		String id_simati = (String)data.get("id_simati");
		String namasimati = (String)data.get("namasimati");
		String umursimati = (String)data.get("umursimati");
		String tempatsimati = (String)data.get("tempatsimati");
		String tarikhsimati = (String)data.get("tarikhsimati");
		String id_Masuk = (String)data.get("id_Masuk");
		
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.update("ID_SIMATI", id_simati);
			r.add("NAMA_SIMATI", namasimati);
			r.add("UMUR", umursimati);
			r.add("TEMPAT_MATI", tempatsimati);
			r.add("TARIKH_MATI",r.unquote("to_date('" + tarikhsimati + "','dd/MM/yyyy')"));
			r.add("ID_MASUK", id_Masuk);
		    r.add("ID_KEMASKINI", id_Masuk);
		    r.add("TARIKH_MASUK", r.unquote("sysdate"));
		    r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
		    
		    sql = r.getSQLUpdate("TBLPPKSIMATI");
		    
		    myLogger.info("SQL INSERT :" + sql.toUpperCase());
		    stmt.executeUpdate(sql);
		    
		}
		
		finally {
			if (db != null)
				db.close();
		}
	}

}
