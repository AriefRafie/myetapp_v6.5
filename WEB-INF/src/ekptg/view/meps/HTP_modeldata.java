package ekptg.view.meps;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.EkptgCache;
import ekptg.helpers.Utils;

public class HTP_modeldata extends EkptgCache implements Serializable {
	static Logger myLog = Logger.getLogger(HTP_modeldata.class);

	public Vector getTotalStatusPermohonanTanahMilikNegeri = null;
	public Vector getTotalStatusPermohonanTanahMilikKementerian = null;
	public Vector getTotalPermohonanPemberimilikanMengikutNegeri = null;
	public Vector getTotalPermohonanPemberimilikanMengikutKementerian = null;
	public Vector getTotalPermohonanPerizabanMengikutNegeri = null;
	public Vector getTotalPermohonanPerizabanKementerian = null;
	public Vector getTotalPembelianMengikutNegeri = null;
	public Vector getTotalPembelianTanahMengikutKementerian = null;
    public Vector getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri = null;
    public Vector getTotalStatusBayaranNotis5ATanahMilikMengikutKementerian = null;
    public Vector getTotalPerletakhakanTanahMengikutNegeri = null;
    public Vector getTotalPerletakhakanTanahMengikutKementerian = null;
    public Vector getTotalPermohonanYgTelahDidaftarkanPerletakhakanTanah = null;
    public Vector getTotalPermohonanYgBelumDaftarPerletakhakanTanah = null;
    public Vector getTotalPajakanTanahMengikutNegeri = null;
    public Vector getTotalPajakanMengikutKementerian = null;
    public Vector getTotalRingkasanTanahRizabPersekutuanMengikutNegeri = null;
    public Vector getTotalRingkasanTanahRizabPersekutuanMengikutKementerian = null;
    public Vector getTotalRingkasanTanahMilikPersekutuanMengikutNegeri = null;
    public Vector getTotalRingkasanTanahMilikPersekutuanMengikutKementerian = null;
    public Vector getKodKementerian = null;
 
	
    private String SQL;
	
	public String getSQL() {
		return SQL;
	}

	public void setSQL(String sql) {
		SQL = sql;
	}
	
	//Laporan Status Permohonan Tanah Milik Mengikut Negeri
	
	public Vector getListTotalStatusPermohonanTanahMilikMengikutNegeri(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalStatusPermohonanTanahMilikNegeri = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  N.NAMA_NEGERI as x, COUNT(*) AS y";					
				sql += " FROM TBLPFDFAIL A,TBLPERMOHONAN B,TBLHTPPERMOHONAN C, TBLRUJDAERAH D, TBLRUJNEGERI N, TBLRUJJENISTANAH E, TBLHTPKEPUTUSANMOHON F,TBLRUJAGENSI G,TBLRUJKEMENTERIAN H";
				sql += " WHERE B.ID_FAIL=A.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_URUSAN = 1 AND A.ID_NEGERI = N.ID_NEGERI AND C.ID_DAERAH = D.ID_DAERAH AND C.ID_JENISTANAH = E.ID_JENISTANAH ";
				sql += " AND F.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_AGENSI=G.ID_AGENSI AND G.ID_KEMENTERIAN = H.ID_KEMENTERIAN ";
				
				
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY N.NAMA_NEGERI ";			
				
				setSQL(sql);
				
				
				//myLogger.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalStatusPermohonanTanahMilikNegeri.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalStatusPermohonanTanahMilikNegeri;					
	}
	
	//Laporan Status Permohonan Tanah Milik Mengikut Kementerian
	
	public Vector getListTotalStatusPermohonanTanahMilikMengikutKementerian(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalStatusPermohonanTanahMilikKementerian = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  H.KOD_KEMENTERIAN,H.NAMA_KEMENTERIAN as x, COUNT(*) AS y";					
				sql += " FROM TBLPFDFAIL A,TBLPERMOHONAN B,TBLHTPPERMOHONAN C,TBLRUJDAERAH D,TBLRUJNEGERI N,TBLRUJJENISTANAH E,TBLHTPKEPUTUSANMOHON F,TBLRUJAGENSI G,TBLRUJKEMENTERIAN H";
				sql += " WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_URUSAN = 1 AND A.ID_NEGERI = N.ID_NEGERI AND C.ID_DAERAH = D.ID_DAERAH AND C.ID_JENISTANAH = E.ID_JENISTANAH AND F.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_AGENSI = G.ID_AGENSI AND G.ID_KEMENTERIAN = H.ID_KEMENTERIAN ";
				sql += " AND F.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_AGENSI=G.ID_AGENSI AND G.ID_KEMENTERIAN = H.ID_KEMENTERIAN ";
				
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY H.NAMA_KEMENTERIAN, H.KOD_KEMENTERIAN ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_kementerian", Utils.isNull(rs.getString("x")));
		    	
		    	getTotalStatusPermohonanTanahMilikKementerian.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalStatusPermohonanTanahMilikKementerian;					
	}
	
	//Laporan Permohonan Pemberimilikan Mengikut Negeri
	
	public Vector getListTotalPermohonanPemberimilikanMengikutNegeri(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalPermohonanPemberimilikanMengikutNegeri = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  N.NAMA_NEGERI as x, COUNT(*) AS y";					
				sql += " FROM TBLPFDFAIL A,TBLPERMOHONAN B,TBLHTPPERMOHONAN C, TBLRUJDAERAH D, TBLRUJNEGERI N,TBLRUJJENISTANAH E , TBLHTPKEPUTUSANMOHON F,TBLHTPNOTIS5A G ,TBLRUJAGENSI H,TBLRUJKEMENTERIAN I";
				sql += " WHERE B.ID_FAIL=A.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_URUSAN = 1 AND A.ID_NEGERI = N.ID_NEGERI AND C.ID_DAERAH = D.ID_DAERAH AND C.ID_JENISTANAH = E.ID_JENISTANAH  ";
				sql += " AND F.ID_PERMOHONAN = B.ID_PERMOHONAN AND G.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_AGENSI= H.ID_AGENSI AND H.ID_KEMENTERIAN = I.ID_KEMENTERIAN AND C.ID_PERMOHONAN NOT IN ( SELECT ID_PERMOHONAN FROM TBLHTPHAKMILIK) ";
				
				
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY N.NAMA_NEGERI ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalPermohonanPemberimilikanMengikutNegeri.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalPermohonanPemberimilikanMengikutNegeri;					
	}
	
	public Vector getListTotalPermohonanPemberimilikanMengikutNegeriAbbrev(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalPermohonanPemberimilikanMengikutNegeri = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  N.ABBREV as x, COUNT(*) AS y";					
				sql += " FROM TBLPFDFAIL A,TBLPERMOHONAN B,TBLHTPPERMOHONAN C, TBLRUJDAERAH D, TBLRUJNEGERI N,TBLRUJJENISTANAH E , TBLHTPKEPUTUSANMOHON F,TBLHTPNOTIS5A G ,TBLRUJAGENSI H,TBLRUJKEMENTERIAN I";
				sql += " WHERE B.ID_FAIL=A.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_URUSAN = 1 AND A.ID_NEGERI = N.ID_NEGERI AND C.ID_DAERAH = D.ID_DAERAH AND C.ID_JENISTANAH = E.ID_JENISTANAH  ";
				sql += " AND F.ID_PERMOHONAN = B.ID_PERMOHONAN AND G.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_AGENSI= H.ID_AGENSI AND H.ID_KEMENTERIAN = I.ID_KEMENTERIAN AND C.ID_PERMOHONAN NOT IN ( SELECT ID_PERMOHONAN FROM TBLHTPHAKMILIK) ";
				
				
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY N.ABBREV ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalPermohonanPemberimilikanMengikutNegeri.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalPermohonanPemberimilikanMengikutNegeri;					
	}
	
	
	//Laporan Permohonan Pemberimilikan Mengikut Kementerian
	public Vector getListTotalPermohonanPemberimilikanMengikutKementerian(String socTahun,String socBulan)throws Exception {		 		 
		Db db = null;
		String sql = "";
		try{
			getTotalPermohonanPemberimilikanMengikutKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();									
				
//				sql =  " SELECT I.KOD_KEMENTERIAN,I.NAMA_KEMENTERIAN AS X, COUNT (*) AS Y";					
//				sql += " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLHTPPERMOHONAN C,TBLRUJDAERAH D, TBLRUJNEGERI N, TBLRUJJENISTANAH E,TBLHTPKEPUTUSANMOHON F,TBLHTPNOTIS5A G,TBLRUJAGENSI H,TBLRUJKEMENTERIAN I";
//				sql += " WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_URUSAN = 1 AND A.ID_NEGERI = N.ID_NEGERI  ";
//				sql += " AND C.ID_DAERAH = D.ID_DAERAH AND C.ID_JENISTANAH = E.ID_JENISTANAH AND F.ID_PERMOHONAN = B.ID_PERMOHONAN AND G.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_AGENSI = H.ID_AGENSI ";
//				sql += " AND H.ID_KEMENTERIAN = I.ID_KEMENTERIAN AND C.ID_PERMOHONAN NOT IN (SELECT ID_PERMOHONAN FROM TBLHTPHAKMILIK)";
				
			sql =  " SELECT DISTINCT A.ID_KEMENTERIAN,A.KOD_KEMENTERIAN " +
			" ,A.NAMA_KEMENTERIAN X "+
			" ,(SELECT COUNT(*) "+
			" FROM TBLRUJKEMENTERIAN C,TBLPFDFAIL F "+
			" WHERE C.ID_KEMENTERIAN = F.ID_KEMENTERIAN  "+
			" AND F.ID_KEMENTERIAN = A.ID_KEMENTERIAN "+
			" AND F.ID_STATUS NOT IN ('999') "+
			" AND F.ID_URUSAN = 1 ";
			// TAHUN 
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
				}
			}
				
	    	// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
				}
			}				
			sql +=" ) AS Y "+
			" ,(SELECT COUNT(*) "+
			" 	FROM TBLRUJKEMENTERIAN C,TBLPFDFAIL F "+
			" 	WHERE C.ID_KEMENTERIAN = F.ID_KEMENTERIAN  "+
			//" 	--AND F.ID_KEMENTERIAN = A.ID_KEMENTERIAN "+
			" 	AND F.ID_STATUS NOT IN ('999') "+
			" 	AND F.ID_URUSAN =10 "+
			" ) AS BIL_LOT_SELURUH "+
			" FROM TBLRUJKEMENTERIAN A,TBLRUJKEMENTERIANMAPPING RKME "+
			" WHERE  "+
			" A.ID_KEMENTERIAN = RKME.ID_KEMENTERIANBARU  "+
			" AND RKME.STATUS = 'A' ";
					
			//sql += " GROUP BY I.NAMA_KEMENTERIAN,I.KOD_KEMENTERIAN ";			
			sql += " ORDER BY A.NAMA_KEMENTERIAN ";			
			setSQL(sql);	
			myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;	
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();			    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_kementerian", Utils.isNull(rs.getString("x")));	    	
		    	getTotalPermohonanPemberimilikanMengikutKementerian.addElement(h);
		    	bil++;
		    	
			}      
		
		}catch (Exception e){
			
		}finally{
			if(db != null)db.close();
		}	
		return getTotalPermohonanPemberimilikanMengikutKementerian;					
	
	}
	
	//Laporan Permohonan Perizaban Mengikut Negeri	
	public Vector getListTotalPermohonanPerizabanMengikutNegeri(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalPermohonanPerizabanMengikutNegeri = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  E.NAMA_NEGERI as x, COUNT(*) AS y";					
				sql += " FROM TBLPERMOHONAN A, TBLHTPPERMOHONAN B, TBLPFDFAIL C, TBLRUJNEGERI E";
				sql += " WHERE  C.ID_FAIL = A.ID_FAIL AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_NEGERI = E.ID_NEGERI AND C.id_urusan = 10  ";	
				
				
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY E.NAMA_NEGERI ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalPermohonanPerizabanMengikutNegeri.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalPermohonanPerizabanMengikutNegeri;					
	}
	
	
	public Vector getListTotalPermohonanPerizabanMengikutNegeriAbbrev(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalPermohonanPerizabanMengikutNegeri = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  E.ABBREV as x, COUNT(*) AS y";					
				sql += " FROM TBLPERMOHONAN A, TBLHTPPERMOHONAN B, TBLPFDFAIL C, TBLRUJNEGERI E";
				sql += " WHERE  C.ID_FAIL = A.ID_FAIL AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_NEGERI = E.ID_NEGERI AND C.id_urusan = 10  ";	
				
				
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY E.ABBREV ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalPermohonanPerizabanMengikutNegeri.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalPermohonanPerizabanMengikutNegeri;					
	}
	
	//Laporan Permohonan Perizaban Mengikut Kementerian
	
	public Vector getListTotalPermohonanPerizabanMengikutKementerian(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalPermohonanPerizabanKementerian = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT G.KOD_KEMENTERIAN,G.NAMA_KEMENTERIAN AS X, COUNT (*) AS Y";					
				sql += " FROM TBLPERMOHONAN A, TBLHTPPERMOHONAN B, TBLPFDFAIL C, TBLRUJNEGERI E, TBLRUJAGENSI F, TBLRUJKEMENTERIAN G";
				sql += " WHERE C.ID_FAIL = A.ID_FAIL AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_NEGERI = E.ID_NEGERI AND C.ID_URUSAN = 10 ";
				sql += " AND B.ID_AGENSI = F.ID_AGENSI AND F.ID_KEMENTERIAN = G.ID_KEMENTERIAN ";
				
				
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY G.NAMA_KEMENTERIAN, G.KOD_KEMENTERIAN  ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_kementerian", Utils.isNull(rs.getString("x")));
		    	
		    	getTotalPermohonanPerizabanKementerian.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalPermohonanPerizabanKementerian;					
	}
	
	//Laporan Status Bayaran Notis 5A Tanah Milik Mengikut Negeri
	
	public Vector getListTotalStatusBayaranNotis5ATanahMilikMengikutNegeri(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT RUJNEG.NAMA_NEGERI AS X, NVL (SUM (NTS.JUMLAH_BAYARAN), 0) AS Y";					
				sql += " FROM (SELECT A.ID_NOTIS5A, A.JUMLAH_BAYARAN, B.ID_PERMOHONAN, C.ID_FAIL,C.NO_FAIL, C.ID_NEGERI, C.ID_KEMENTERIAN";
				sql += " FROM TBLHTPNOTIS5A A, TBLPERMOHONAN B, TBLPFDFAIL C ";
				sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN";
				sql += " AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) NTS,";
				sql += " (SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, A.NO_LOT,B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HM,";
				sql += " (SELECT A.ID_HAKMILIKURUSAN, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIKURUSAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HMU,";
				sql += " (SELECT A.ID_HTPPERMOHONAN, A.ID_AGENSI, A.NO_RUJUKAN_KJP,A.NO_RUJUKAN_LAIN, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPPERMOHONAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HTPPER,";
				sql += " TBLRUJNEGERI RUJNEG, TBLRUJKEMENTERIAN RUJKEM, TBLRUJAGENSI RUJAGEN WHERE NTS.ID_PERMOHONAN = HM.ID_PERMOHONAN";
				sql += " AND NTS.ID_FAIL = HM.ID_FAIL AND HM.ID_PERMOHONAN = HMU.ID_PERMOHONAN AND HM.ID_FAIL = HMU.ID_FAIL AND HMU.ID_PERMOHONAN = HTPPER.ID_PERMOHONAN";
				sql += " AND HMU.ID_FAIL = HTPPER.ID_FAIL AND NTS.ID_NEGERI = RUJNEG.ID_NEGERI AND NTS.ID_KEMENTERIAN = RUJKEM.ID_KEMENTERIAN AND RUJKEM.ID_KEMENTERIAN = RUJAGEN.ID_KEMENTERIAN AND HTPPER.ID_AGENSI = RUJAGEN.ID_AGENSI";
				

				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY RUJNEG.NAMA_NEGERI ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
//		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri;					
	}
	
	public Vector getListTotalStatusBayaranNotis5ATanahMilikMengikutNegeriAbbrev(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT RUJNEG.ABBREV AS X, NVL (SUM (NTS.JUMLAH_BAYARAN), 0) AS Y";					
				sql += " FROM (SELECT A.ID_NOTIS5A, A.JUMLAH_BAYARAN, B.ID_PERMOHONAN, C.ID_FAIL,C.NO_FAIL, C.ID_NEGERI, C.ID_KEMENTERIAN";
				sql += " FROM TBLHTPNOTIS5A A, TBLPERMOHONAN B, TBLPFDFAIL C ";
				sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN";
				sql += " AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) NTS,";
				sql += " (SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, A.NO_LOT,B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HM,";
				sql += " (SELECT A.ID_HAKMILIKURUSAN, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIKURUSAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HMU,";
				sql += " (SELECT A.ID_HTPPERMOHONAN, A.ID_AGENSI, A.NO_RUJUKAN_KJP,A.NO_RUJUKAN_LAIN, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPPERMOHONAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HTPPER,";
				sql += " TBLRUJNEGERI RUJNEG, TBLRUJKEMENTERIAN RUJKEM, TBLRUJAGENSI RUJAGEN WHERE NTS.ID_PERMOHONAN = HM.ID_PERMOHONAN";
				sql += " AND NTS.ID_FAIL = HM.ID_FAIL AND HM.ID_PERMOHONAN = HMU.ID_PERMOHONAN AND HM.ID_FAIL = HMU.ID_FAIL AND HMU.ID_PERMOHONAN = HTPPER.ID_PERMOHONAN";
				sql += " AND HMU.ID_FAIL = HTPPER.ID_FAIL AND NTS.ID_NEGERI = RUJNEG.ID_NEGERI AND NTS.ID_KEMENTERIAN = RUJKEM.ID_KEMENTERIAN AND RUJKEM.ID_KEMENTERIAN = RUJAGEN.ID_KEMENTERIAN AND HTPPER.ID_AGENSI = RUJAGEN.ID_AGENSI";
				

				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY RUJNEG.ABBREV ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
//		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri;					
	}
	
	
	//Laporan Status Bayaran Notis 5A Tanah Milik Mengikut Kementerian
	
	public Vector getListTotalStatusBayaranNotis5ATanahMilikMengikutKementerian(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalStatusBayaranNotis5ATanahMilikMengikutKementerian = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT RUJKEM.KOD_KEMENTERIAN,RUJKEM.NAMA_KEMENTERIAN AS X, NVL (SUM (NTS.JUMLAH_BAYARAN), 0) AS Y";					
				sql += " FROM (SELECT A.ID_NOTIS5A, A.JUMLAH_BAYARAN, B.ID_PERMOHONAN, C.ID_FAIL,C.NO_FAIL, C.ID_NEGERI, C.ID_KEMENTERIAN";
				sql += " FROM TBLHTPNOTIS5A A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) NTS,";
				sql += " (SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, A.NO_LOT, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HM, ";
				sql += " (SELECT A.ID_HAKMILIKURUSAN, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIKURUSAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HMU,";
				sql += " (SELECT A.ID_HTPPERMOHONAN, A.ID_AGENSI, A.NO_RUJUKAN_KJP,A.NO_RUJUKAN_LAIN, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPPERMOHONAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HTPPER,";
				sql += " TBLRUJNEGERI RUJNEG, TBLRUJKEMENTERIAN RUJKEM, TBLRUJAGENSI RUJAGEN";
				sql += " WHERE NTS.ID_PERMOHONAN = HM.ID_PERMOHONAN AND NTS.ID_FAIL = HM.ID_FAIL AND HM.ID_PERMOHONAN = HMU.ID_PERMOHONAN AND HM.ID_FAIL = HMU.ID_FAIL AND HMU.ID_PERMOHONAN = HTPPER.ID_PERMOHONAN";
				sql += " AND HMU.ID_FAIL = HTPPER.ID_FAIL AND NTS.ID_NEGERI = RUJNEG.ID_NEGERI AND NTS.ID_KEMENTERIAN = RUJKEM.ID_KEMENTERIAN AND RUJKEM.ID_KEMENTERIAN = RUJAGEN.ID_KEMENTERIAN AND HTPPER.ID_AGENSI = RUJAGEN.ID_AGENSI";
				
				
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY RUJKEM.NAMA_KEMENTERIAN,RUJKEM.KOD_KEMENTERIAN  ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
			
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
//		        h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
//		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("nama_kementerian", Utils.isNull(rs.getString("x")));
		    	
		    	getTotalStatusBayaranNotis5ATanahMilikMengikutKementerian.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalStatusBayaranNotis5ATanahMilikMengikutKementerian;					
	}
	
	//Laporan Pembelian Tanah Mengikut Negeri.
	
	public Vector getListTotalPembelianTanahMengikutNegeri(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalPembelianMengikutNegeri = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  N.NAMA_NEGERI AS x, COUNT(*) AS y";					
				sql += " FROM TBLPFDFAIL A,TBLPERMOHONAN B,TBLHTPPERMOHONAN C,TBLHTPPERJANJIAN D,TBLRUJSUBURUSAN E,TBLRUJKEMENTERIAN F ,TBLRUJNEGERI N,TBLRUJDAERAH G,TBLHTPPERJANJIANPEMBELIAN H, TBLRUJAGENSI I";
				sql += " WHERE A.ID_URUSAN = 2 AND B.ID_FAIL = A.ID_FAIL AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_PERMOHONAN = B.ID_PERMOHONAN  AND A.ID_SUBURUSAN = E.ID_SUBURUSAN AND C.ID_AGENSI = I.ID_AGENSI";
				sql += " AND I.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND A.ID_NEGERI = N.ID_NEGERI AND G.ID_DAERAH = C.ID_DAERAH AND D.ID_PERJANJIAN = H.ID_PERJANJIAN ";

				
				
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY N.NAMA_NEGERI ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalPembelianMengikutNegeri.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalPembelianMengikutNegeri;					
	}

	public Vector getListTotalPembelianTanahMengikutNegeriAbbrev(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalPembelianMengikutNegeri = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  N.ABBREV AS x, COUNT(*) AS y";					
				sql += " FROM TBLPFDFAIL A,TBLPERMOHONAN B,TBLHTPPERMOHONAN C,TBLHTPPERJANJIAN D,TBLRUJSUBURUSAN E,TBLRUJKEMENTERIAN F ,TBLRUJNEGERI N,TBLRUJDAERAH G,TBLHTPPERJANJIANPEMBELIAN H, TBLRUJAGENSI I";
				sql += " WHERE A.ID_URUSAN = 2 AND B.ID_FAIL = A.ID_FAIL AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_PERMOHONAN = B.ID_PERMOHONAN  AND A.ID_SUBURUSAN = E.ID_SUBURUSAN AND C.ID_AGENSI = I.ID_AGENSI";
				sql += " AND I.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND A.ID_NEGERI = N.ID_NEGERI AND G.ID_DAERAH = C.ID_DAERAH AND D.ID_PERJANJIAN = H.ID_PERJANJIAN ";

				
				
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY N.ABBREV ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalPembelianMengikutNegeri.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalPembelianMengikutNegeri;					
	}
	
	
	//Laporan Pembelian Tanah Mengikut Kementerian.
	
	public Vector getListTotalPembelianTanahMengikutKementerian(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalPembelianTanahMengikutKementerian = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  F.KOD_KEMENTERIAN,F.NAMA_KEMENTERIAN AS x, COUNT(*) AS y";					
				sql += " FROM TBLPFDFAIL A,TBLPERMOHONAN B,TBLHTPPERMOHONAN C,TBLHTPPERJANJIAN D,TBLRUJSUBURUSAN E,TBLRUJKEMENTERIAN F ,TBLRUJNEGERI N,TBLRUJDAERAH G,TBLHTPPERJANJIANPEMBELIAN H, TBLRUJAGENSI I";
				sql += " WHERE A.ID_URUSAN = 2 AND B.ID_FAIL = A.ID_FAIL AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_PERMOHONAN = B.ID_PERMOHONAN  AND A.ID_SUBURUSAN = E.ID_SUBURUSAN AND C.ID_AGENSI = I.ID_AGENSI";
				sql += " AND I.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND A.ID_NEGERI = N.ID_NEGERI AND G.ID_DAERAH = C.ID_DAERAH AND D.ID_PERJANJIAN = H.ID_PERJANJIAN ";

				
				
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY F.NAMA_KEMENTERIAN, F.KOD_KEMENTERIAN ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_kementerian", Utils.isNull(rs.getString("x")));
		    	
		    	getTotalPembelianTanahMengikutKementerian.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalPembelianTanahMengikutKementerian;					
	}
	
	//Laporan Perletakhakan Tanah Mengikut Negeri.
	
	public Vector getListTotalPerletakhakanTanahMengikutNegeri(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalPerletakhakanTanahMengikutNegeri = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  N.NAMA_NEGERI AS x, COUNT(*) AS y";					
				sql += " FROM TBLRUJMUKIM M,TBLHTPHAKMILIKURUSAN A,TBLPERMOHONAN B,TBLPFDFAIL C,TBLHTPPERMOHONAN D,TBLRUJKEMENTERIAN E,TBLRUJAGENSI F,TBLRUJDAERAH G,TBLRUJNEGERI N,TBLRUJJENISHAKMILIK H";
				sql += " WHERE A.ID_MUKIM = M.ID_MUKIM AND M.ID_DAERAH = G.ID_DAERAH AND G.ID_NEGERI = N.ID_NEGERI AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_AGENSI = F.ID_AGENSI AND F.ID_KEMENTERIAN = E.ID_KEMENTERIAN AND B.ID_FAIL = C.ID_FAIL AND A.ID_JENISHAKMILIK = H.ID_JENISHAKMILIK AND C.ID_URUSAN = 5 ";

				
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY N.NAMA_NEGERI ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalPerletakhakanTanahMengikutNegeri.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalPerletakhakanTanahMengikutNegeri;					
	}
	public Vector getListTotalPerletakhakanTanahMengikutNegeriAbbrev(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalPerletakhakanTanahMengikutNegeri = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  N.ABBREV AS x, COUNT(*) AS y";					
				sql += " FROM TBLRUJMUKIM M,TBLHTPHAKMILIKURUSAN A,TBLPERMOHONAN B,TBLPFDFAIL C,TBLHTPPERMOHONAN D,TBLRUJKEMENTERIAN E,TBLRUJAGENSI F,TBLRUJDAERAH G,TBLRUJNEGERI N,TBLRUJJENISHAKMILIK H";
				sql += " WHERE A.ID_MUKIM = M.ID_MUKIM AND M.ID_DAERAH = G.ID_DAERAH AND G.ID_NEGERI = N.ID_NEGERI AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_AGENSI = F.ID_AGENSI AND F.ID_KEMENTERIAN = E.ID_KEMENTERIAN AND B.ID_FAIL = C.ID_FAIL AND A.ID_JENISHAKMILIK = H.ID_JENISHAKMILIK AND C.ID_URUSAN = 5 ";

				
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY N.ABBREV ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalPerletakhakanTanahMengikutNegeri.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalPerletakhakanTanahMengikutNegeri;					
	}
	
	//Laporan Perletakhakan Tanah Mengikut Kementerian.
	
	public Vector getListTotalPerletakhakanTanahMengikutKementerian(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalPerletakhakanTanahMengikutKementerian = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT E.KOD_KEMENTERIAN,E.NAMA_KEMENTERIAN AS X, COUNT (*) AS Y";					
				sql += " FROM TBLRUJMUKIM M, TBLHTPHAKMILIKURUSAN A, TBLPERMOHONAN B,TBLPFDFAIL C,TBLHTPPERMOHONAN D, TBLRUJKEMENTERIAN E, TBLRUJAGENSI F,TBLRUJDAERAH G,TBLRUJNEGERI N, TBLRUJJENISHAKMILIK H";
				sql += " WHERE A.ID_MUKIM = M.ID_MUKIM AND M.ID_DAERAH = G.ID_DAERAH AND G.ID_NEGERI = N.ID_NEGERI AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_PERMOHONAN = B.ID_PERMOHONAN";
				sql += " AND D.ID_AGENSI = F.ID_AGENSI AND F.ID_KEMENTERIAN = E.ID_KEMENTERIAN AND B.ID_FAIL = C.ID_FAIL AND A.ID_JENISHAKMILIK = H.ID_JENISHAKMILIK AND C.ID_URUSAN = 5 ";
				
				
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY E.NAMA_KEMENTERIAN, E.KOD_KEMENTERIAN ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_kementerian", Utils.isNull(rs.getString("x")));
		    	
		    	getTotalPerletakhakanTanahMengikutKementerian.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalPerletakhakanTanahMengikutKementerian;					
	}
	
	//Laporan Permohonan Yang Telah Didaftarkan Perletakhakan Tanah.
	
	public Vector getListPermohonanYgTelahDidaftarkanPerletakhakanTanah(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalPermohonanYgTelahDidaftarkanPerletakhakanTanah = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  N.NAMA_NEGERI AS x, COUNT(*) AS y";					
				sql += " FROM TBLRUJJENISHAKMILIK E,TBLHTPHAKMILIKURUSAN D,TBLPERMOHONAN B,TBLPFDFAIL A,TBLHTPPERMOHONAN C,TBLRUJKEMENTERIAN F,TBLRUJAGENSI G,TBLRUJDAERAH I,TBLRUJNEGERI N,TBLRUJMUKIM J";
				sql += " WHERE D.ID_MUKIM = J.ID_MUKIM AND J.ID_DAERAH = I.ID_DAERAH AND I.ID_NEGERI = N.ID_NEGERI AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_AGENSI = G.ID_AGENSI AND G.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND B.ID_FAIL = A.ID_FAIL AND D.ID_JENISHAKMILIK=E.ID_JENISHAKMILIK AND A.ID_URUSAN = 5 AND B.ID_PERMOHONAN IN (select B.ID_PERMOHONAN from tblpfdfail A ,TBLPERMOHONAN B,TBLHTPHAKMILIK H where H.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = A.ID_FAIL AND A.id_urusan = 5 ) ";

				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY N.NAMA_NEGERI ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalPermohonanYgTelahDidaftarkanPerletakhakanTanah.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalPermohonanYgTelahDidaftarkanPerletakhakanTanah;					
	}
	public Vector getListPermohonanYgTelahDidaftarkanPerletakhakanTanahAbbrev(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalPermohonanYgTelahDidaftarkanPerletakhakanTanah = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  N.ABBREV AS x, COUNT(*) AS y";					
				sql += " FROM TBLRUJJENISHAKMILIK E,TBLHTPHAKMILIKURUSAN D,TBLPERMOHONAN B,TBLPFDFAIL A,TBLHTPPERMOHONAN C,TBLRUJKEMENTERIAN F,TBLRUJAGENSI G,TBLRUJDAERAH I,TBLRUJNEGERI N,TBLRUJMUKIM J";
				sql += " WHERE D.ID_MUKIM = J.ID_MUKIM AND J.ID_DAERAH = I.ID_DAERAH AND I.ID_NEGERI = N.ID_NEGERI AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_AGENSI = G.ID_AGENSI AND G.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND B.ID_FAIL = A.ID_FAIL AND D.ID_JENISHAKMILIK=E.ID_JENISHAKMILIK AND A.ID_URUSAN = 5 AND B.ID_PERMOHONAN IN (select B.ID_PERMOHONAN from tblpfdfail A ,TBLPERMOHONAN B,TBLHTPHAKMILIK H where H.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = A.ID_FAIL AND A.id_urusan = 5 ) ";

				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY N.ABBREV ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalPermohonanYgTelahDidaftarkanPerletakhakanTanah.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalPermohonanYgTelahDidaftarkanPerletakhakanTanah;					
	}
	
	//Laporan Permohonan Yang Belum Didaftarkan Perletakhakan Tanah.
	
	public Vector getListPermohonanYgBelumDaftarPerletakhakanTanah(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalPermohonanYgBelumDaftarPerletakhakanTanah = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  N.NAMA_NEGERI AS x, COUNT(*) AS y";					
				sql += " FROM TBLRUJJENISHAKMILIK E,TBLHTPHAKMILIKURUSAN C,TBLPERMOHONAN B,TBLPFDFAIL A,TBLHTPPERMOHONAN D,TBLRUJKEMENTERIAN F,TBLRUJAGENSI G,TBLRUJDAERAH H,TBLRUJNEGERI N,TBLRUJMUKIM I";
				sql += " WHERE C.ID_MUKIM = I.ID_MUKIM AND I.ID_DAERAH = H.ID_DAERAH AND H.ID_NEGERI = N.ID_NEGERI AND C.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_AGENSI = G.ID_AGENSI AND G.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND B.ID_FAIL = A.ID_FAIL AND C.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK AND A.ID_URUSAN = 5 ";

				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY N.NAMA_NEGERI ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);

				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalPermohonanYgBelumDaftarPerletakhakanTanah.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalPermohonanYgBelumDaftarPerletakhakanTanah;					
	}

	public Vector getListPermohonanYgBelumDaftarPerletakhakanTanahAbbrev(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalPermohonanYgBelumDaftarPerletakhakanTanah = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  N.ABBREV AS x, COUNT(*) AS y";					
				sql += " FROM TBLRUJJENISHAKMILIK E,TBLHTPHAKMILIKURUSAN C,TBLPERMOHONAN B,TBLPFDFAIL A,TBLHTPPERMOHONAN D,TBLRUJKEMENTERIAN F,TBLRUJAGENSI G,TBLRUJDAERAH H,TBLRUJNEGERI N,TBLRUJMUKIM I";
				sql += " WHERE C.ID_MUKIM = I.ID_MUKIM AND I.ID_DAERAH = H.ID_DAERAH AND H.ID_NEGERI = N.ID_NEGERI AND C.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_AGENSI = G.ID_AGENSI AND G.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND B.ID_FAIL = A.ID_FAIL AND C.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK AND A.ID_URUSAN = 5 ";

				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY N.ABBREV ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);

				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalPermohonanYgBelumDaftarPerletakhakanTanah.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalPermohonanYgBelumDaftarPerletakhakanTanah;					
	}
	
	//Laporan Pajakan Mengikut Negeri.
	
	public Vector getListTotalPajakanTanahMengikutNegeri(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalPajakanTanahMengikutNegeri = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT RUJNEG.NAMA_NEGERI AS X, COUNT (*) AS Y";					
				sql += " FROM TBLPFDFAIL F, TBLPERMOHONAN P, TBLHTPPERMOHONAN PP,TBLHTPPAJAKAN TPP, TBLHTPPEMOHON TPPE, TBLRUJNEGERI RUJNEG, TBLRUJAGENSI G, TBLRUJKEMENTERIAN H";
				sql += " WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN AND F.ID_STATUS <> 999 AND F.ID_URUSAN = 3 AND P.ID_PERMOHONAN = TPPE.ID_PERMOHONAN(+) ";
				sql += " AND f.ID_NEGERI = RUJNEG.ID_NEGERI AND PP.ID_AGENSI = G.ID_AGENSI AND G.ID_KEMENTERIAN = H.ID_KEMENTERIAN ";
				

				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY RUJNEG.NAMA_NEGERI ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalPajakanTanahMengikutNegeri.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalPajakanTanahMengikutNegeri;					
	}
	public Vector getListTotalPajakanTanahMengikutNegeriAbbrev(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalPajakanTanahMengikutNegeri = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT RUJNEG.ABBREV AS X, COUNT (*) AS Y";					
				sql += " FROM TBLPFDFAIL F, TBLPERMOHONAN P, TBLHTPPERMOHONAN PP,TBLHTPPAJAKAN TPP, TBLHTPPEMOHON TPPE, TBLRUJNEGERI RUJNEG, TBLRUJAGENSI G, TBLRUJKEMENTERIAN H";
				sql += " WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN AND F.ID_STATUS <> 999 AND F.ID_URUSAN = 3 AND P.ID_PERMOHONAN = TPPE.ID_PERMOHONAN(+) ";
				sql += " AND f.ID_NEGERI = RUJNEG.ID_NEGERI AND PP.ID_AGENSI = G.ID_AGENSI AND G.ID_KEMENTERIAN = H.ID_KEMENTERIAN ";
				

				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY RUJNEG.ABBREV ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalPajakanTanahMengikutNegeri.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalPajakanTanahMengikutNegeri;					
	}
	
	
	//Laporan Pajakan Mengikut Kementerian.
	
	public Vector getListTotalPajakanMengikutKementerian(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalPajakanMengikutKementerian = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT H.KOD_KEMENTERIAN,H.NAMA_KEMENTERIAN AS X, COUNT (*) AS Y";					
				sql += " FROM TBLPFDFAIL F, TBLPERMOHONAN P, TBLHTPPERMOHONAN PP, TBLHTPPAJAKAN TPP, TBLHTPPEMOHON TPPE,TBLRUJAGENSI G, TBLRUJKEMENTERIAN H";
				sql += " WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN AND F.ID_STATUS <> 999 AND F.ID_URUSAN = 3";
				sql += " AND P.ID_PERMOHONAN = TPPE.ID_PERMOHONAN(+) AND PP.ID_AGENSI = G.ID_AGENSI AND G.ID_KEMENTERIAN = H.ID_KEMENTERIAN ";
				
				
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " GROUP BY H.NAMA_KEMENTERIAN, H.KOD_KEMENTERIAN ";			
				
				setSQL(sql);
				
				myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_kementerian", Utils.isNull(rs.getString("x")));
		    	
		    	getTotalPajakanMengikutKementerian.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalPajakanMengikutKementerian;					
	}
	
	
	//Laporan Ringkasan Tanah Rizab Persekutuan Mengikut Negeri.
	
	public Vector getListTotalRingkasanTanahRizabPersekutuanMengikutNegeri(String socTahun,String socBulan)throws Exception {		 		 
		Db db = null;
		String sql = "";
		try{
			getTotalRingkasanTanahRizabPersekutuanMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();									
			sql = "SELECT DISTINCT A.ID_NEGERI,A.NAMA_NEGERI AS X,(SELECT COUNT(*)";
			sql += " FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			// TAHUN 
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
				}
			}
				
	    	// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
				}
			}
			sql += "	AND NVL(D.NO_WARTA,' ')<>' '  " +
					" AND (D.STATUS_SAH NOT IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
						")) " +
					") AS Y,(SELECT COUNT(*)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI ";
			sql += "	AND NVL(D.NO_WARTA,' ')<>' '";
			sql += " " +
					" AND (D.STATUS_SAH NOT IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
						"))" +
					") AS BIL_LOT_SELURUH,(SELECT NVL(SUM(D.LUAS_BERSAMAAN),0)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI"; 
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			sql += "	AND NVL(D.NO_WARTA,' ')<>' ' " +
					" AND (D.STATUS_SAH NOT IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
						")) " +
					") AS TOTAL_LUAS_NEGERI,(SELECT SUM(D.LUAS_BERSAMAAN)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI"; 
			sql += "	AND NVL(D.NO_WARTA,' ')<>' ' " +
					" AND (D.STATUS_SAH NOT IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
						"))" +
					") AS TOTAL_LUAS_SELURUH";
			sql += " FROM TBLRUJNEGERI A ";
			sql += " WHERE A.ID_NEGERI NOT IN (0,17)";
			setSQL(sql);
				
			myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;	
			int bil = 1;
			while (rs.next()) {
		    	h = new Hashtable();	
		      	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalRingkasanTanahRizabPersekutuanMengikutNegeri.addElement(h);
		    	bil++;
		    
			}      
		}finally{
			if(db != null)db.close();
		}	
		return getTotalRingkasanTanahRizabPersekutuanMengikutNegeri;					
	
	
	}

	public Vector getListTotalRingkasanTanahRizabPersekutuanMengikutNegeriAbbrev(String socTahun,String socBulan)throws Exception {		 		 
		Db db = null;
		String sql = "";
		try{
			getTotalRingkasanTanahRizabPersekutuanMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();									
			sql = "SELECT DISTINCT A.ID_NEGERI,A.ABBREV AS X,(SELECT COUNT(*)";
			sql += " FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			// TAHUN 
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
				}
			}
				
	    	// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
				}
			}
			sql += "	AND NVL(D.NO_WARTA,' ')<>' '  " +
					" AND (D.STATUS_SAH NOT IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
						")) " +
					") AS Y,(SELECT COUNT(*)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI ";
			sql += "	AND NVL(D.NO_WARTA,' ')<>' '";
			sql += " " +
					" AND (D.STATUS_SAH NOT IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
						"))" +
					") AS BIL_LOT_SELURUH,(SELECT NVL(SUM(D.LUAS_BERSAMAAN),0)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI"; 
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			sql += "	AND NVL(D.NO_WARTA,' ')<>' ' " +
					" AND (D.STATUS_SAH NOT IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
						")) " +
					") AS TOTAL_LUAS_NEGERI,(SELECT SUM(D.LUAS_BERSAMAAN)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI"; 
			sql += "	AND NVL(D.NO_WARTA,' ')<>' ' " +
					" AND (D.STATUS_SAH NOT IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
						"))" +
					") AS TOTAL_LUAS_SELURUH";
			sql += " FROM TBLRUJNEGERI A ";
			sql += " WHERE A.ID_NEGERI NOT IN (0,17)";
			setSQL(sql);
				
			myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;	
			int bil = 1;
			while (rs.next()) {
		    	h = new Hashtable();	
		      	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalRingkasanTanahRizabPersekutuanMengikutNegeri.addElement(h);
		    	bil++;
		    
			}      
		}finally{
			if(db != null)db.close();
		}	
		return getTotalRingkasanTanahRizabPersekutuanMengikutNegeri;					
	
	
	}

	
	//Laporan Ringkasan Tanah Rizab Persekutuan Mengikut Kementerian.
	
	public Vector getListTotalRingkasanTanahRizabPersekutuanMengikutKementerian(String socTahun,String socBulan)throws Exception {		 		 
		Db db = null;
		String sql = "";
		try{
			getTotalRingkasanTanahRizabPersekutuanMengikutKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	

			sql = "SELECT distinct(A.ID_KEMENTERIAN),A.KOD_KEMENTERIAN,A.NAMA_KEMENTERIAN AS X,(SELECT COUNT(*)  ";
			sql += " FROM TBLRUJKEMENTERIANMAPPING C,TBLHTPHAKMILIK D WHERE C.ID_KEMENTERIANLAMA = D.ID_KEMENTERIAN";
			sql += " AND C.ID_KEMENTERIANBARU = RKME.ID_KEMENTERIANBARU";
			// TAHUN 
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
				}
			}
				
    		// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
				}
			}
			sql += " AND RKME.STATUS = C.STATUS AND NVL(D.NO_WARTA,' ')<>' ' " +
				" AND (D.STATUS_SAH NOT IN (" +
				"	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
				"))" +					") AS Y,(SELECT NVL(SUM(D.LUAS_BERSAMAAN),0)";
			sql += " FROM TBLRUJKEMENTERIANMAPPING C,TBLHTPHAKMILIK D";
			sql += " WHERE C.ID_KEMENTERIANLAMA = D.ID_KEMENTERIAN"; 
			sql += " AND C.ID_KEMENTERIANBARU = RKME.ID_KEMENTERIANBARU";
			sql += " AND RKME.STATUS = C.STATUS";
			sql += " AND NVL(D.NO_HAKMILIK,' ')<>' ' ";
			sql += " " +
					" AND (D.STATUS_SAH NOT IN (" +
					"	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
					" ))" +
					" ) AS TOTAL_LUAS_NEGERI";
			sql += " FROM TBLRUJKEMENTERIAN A,TBLRUJKEMENTERIANMAPPING RKME";
			sql += " WHERE A.ID_KEMENTERIAN = RKME.ID_KEMENTERIANBARU AND RKME.STATUS = 'A'";
			//
			setSQL(sql);	
			myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;	
			int bil = 1;
			while (rs.next()) {
		    	h = new Hashtable();	
		       	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_kementerian", Utils.isNull(rs.getString("x")));
		    	
		    	getTotalRingkasanTanahRizabPersekutuanMengikutKementerian.addElement(h);
		    	bil++;
		      	
			}      
		}finally{
			if(db != null)db.close();
		}	
		return getTotalRingkasanTanahRizabPersekutuanMengikutKementerian;					
	
	
	}
	
	//Laporan Ringkasan Tanah Milik Persekutuan Mengikut Negeri.
	
	public Vector getListTotalRingkasanTanahMilikPersekutuanMengikutNegeri(String socTahun,String socBulan)throws Exception {		 		 
		Db db = null;
		String sql = "";
		try{
			getTotalRingkasanTanahMilikPersekutuanMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();									
				
			sql = "SELECT DISTINCT A.ID_NEGERI,A.NAMA_NEGERI AS X,(SELECT COUNT(*)";
			sql += " FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			// TAHUN 
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
				}
			}
				
	    	// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
				}
			}
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' '  " +
				" AND (D.STATUS_SAH NOT IN (" +
				" 	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
				" )" +
				")) AS Y,(SELECT COUNT(*)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI ";
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' '";
			sql += " " +
				"AND (D.STATUS_SAH NOT IN (" +
				"	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
				"))" +
				") AS BIL_LOT_SELURUH,(SELECT NVL(SUM(D.LUAS_BERSAMAAN),0)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI"; 
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' ' " +
				" AND (D.STATUS_SAH NOT IN (" +
				"	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
				"))" +
				") AS TOTAL_LUAS_NEGERI,(SELECT SUM(D.LUAS_BERSAMAAN)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI"; 
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' ' " +
				" AND (D.STATUS_SAH NOT IN (" +
				"	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
				"))" +
				") AS TOTAL_LUAS_SELURUH";
			sql += " FROM TBLRUJNEGERI A ";
			sql += " WHERE A.ID_NEGERI NOT IN (0,17)";			
			setSQL(sql);
			myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;	
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();			    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));	    	
		    	getTotalRingkasanTanahMilikPersekutuanMengikutNegeri.addElement(h);
		    	bil++;
		    	
		      	}      
			}finally{
				if(db != null)db.close();
			}	
		return getTotalRingkasanTanahMilikPersekutuanMengikutNegeri;					
	
	}
	
	public Vector getListTotalRingkasanTanahMilikPersekutuanMengikutNegeriAbbrev(String socTahun,String socBulan)throws Exception {		 		 
		Db db = null;
		String sql = "";
		try{
			getTotalRingkasanTanahMilikPersekutuanMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();									
				
			sql = "SELECT DISTINCT A.ID_NEGERI,A.ABBREV AS X,(SELECT COUNT(*)";
			sql += " FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			// TAHUN 
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
				}
			}
				
	    	// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
				}
			}
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' '  " +
				" AND (D.STATUS_SAH NOT IN (" +
				" 	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
				" )" +
				")) AS Y,(SELECT COUNT(*)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI ";
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' '";
			sql += " " +
				"AND (D.STATUS_SAH NOT IN (" +
				"	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
				"))" +
				") AS BIL_LOT_SELURUH,(SELECT NVL(SUM(D.LUAS_BERSAMAAN),0)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI"; 
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' ' " +
				" AND (D.STATUS_SAH NOT IN (" +
				"	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
				"))" +
				") AS TOTAL_LUAS_NEGERI,(SELECT SUM(D.LUAS_BERSAMAAN)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI"; 
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' ' " +
				" AND (D.STATUS_SAH NOT IN (" +
				"	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
				"))" +
				") AS TOTAL_LUAS_SELURUH";
			sql += " FROM TBLRUJNEGERI A ";
			sql += " WHERE A.ID_NEGERI NOT IN (0,17)";			
			setSQL(sql);
			myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;	
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();			    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));	    	
		    	getTotalRingkasanTanahMilikPersekutuanMengikutNegeri.addElement(h);
		    	bil++;
		    	
		      	}      
			}finally{
				if(db != null)db.close();
			}	
		return getTotalRingkasanTanahMilikPersekutuanMengikutNegeri;					
	
	}
	

	//Laporan Ringkasan Tanah Milik Persekutuan Mengikut Kementerian.
	
	public Vector getListTotalRingkasanTanahMilikPersekutuanMengikutKementerian(String socTahun,String socBulan)throws Exception {		 		 
		Db db = null;
		String sql = "";
		try{
			getTotalRingkasanTanahMilikPersekutuanMengikutKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();									
			sql = " SELECT distinct(A.ID_KEMENTERIAN),A.KOD_KEMENTERIAN,A.NAMA_KEMENTERIAN AS X,(SELECT COUNT(*)";
			sql += " FROM TBLRUJKEMENTERIANMAPPING C,TBLHTPHAKMILIK D";
			sql += " WHERE C.ID_KEMENTERIANLAMA = D.ID_KEMENTERIAN"; 
			sql += " AND C.ID_KEMENTERIANBARU = RKME.ID_KEMENTERIANBARU";

			// TAHUN 
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
				}
			}
				
	    	// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
				}
			}
			sql += " AND RKME.STATUS = C.STATUS";
			sql += " AND NVL(D.NO_HAKMILIK,' ')<>' '";
			sql += " " +
				" AND (D.STATUS_SAH NOT IN (" +
				" 	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
				" ))" +
				" ) AS Y,(SELECT NVL(SUM(D.LUAS_BERSAMAAN),0)";
			sql += " FROM TBLRUJKEMENTERIANMAPPING C,TBLHTPHAKMILIK D";
			sql += " WHERE C.ID_KEMENTERIANLAMA = D.ID_KEMENTERIAN"; 
			sql += " AND C.ID_KEMENTERIANBARU = RKME.ID_KEMENTERIANBARU";
			sql += " AND RKME.STATUS = C.STATUS";
			sql += " AND NVL(D.NO_HAKMILIK,' ')<>' '";
			sql += " " +
				" AND (D.STATUS_SAH NOT IN (" +
				"	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
				")) " +
				") AS TOTAL_LUAS_NEGERI";
			sql += " FROM TBLRUJKEMENTERIAN A,TBLRUJKEMENTERIANMAPPING RKME";
			sql += " WHERE A.ID_KEMENTERIAN = RKME.ID_KEMENTERIANBARU";
			sql += " AND RKME.STATUS = 'A'";
					
			setSQL(sql);
			myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;	
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();			    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_kementerian", Utils.isNull(rs.getString("x")));	    	
		    	getTotalRingkasanTanahMilikPersekutuanMengikutKementerian.addElement(h);
		    	bil++;
		    	
		      	}      
			}finally{
				if(db != null)db.close();
			}	
		return getTotalRingkasanTanahMilikPersekutuanMengikutKementerian;					
	
	
	}
	
	
	// ELLY ADDED 20.09.2010	
	public Vector getKodKementerian() throws Exception {
		Db db = null;
		String sql = "";
		try{
			getKodKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();									
			
			sql =  " SELECT distinct(RK.KOD_KEMENTERIAN) AS KOD, RK.NAMA_KEMENTERIAN AS KEMENTERIAN " +
					" FROM TBLRUJKEMENTERIAN RK,TBLRUJKEMENTERIANMAPPING RKME " +
					" WHERE " +
					" RK.ID_KEMENTERIAN = RKME.ID_KEMENTERIANBARU AND RKME.STATUS = 'A' " +
					" ORDER BY RK.NAMA_KEMENTERIAN" +
					"";
			myLog.info(sql);
//				setSQL(sql);				
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;				
			while (rs.next()) {
				h = new Hashtable();	        
		    	h.put("kod", Utils.isNull(rs.getString("KOD")));
		    	h.put("kementerian", Utils.isNull(rs.getString("KEMENTERIAN")));		    	
		    	getKodKementerian.addElement(h);
			}      
		}finally{
			if(db != null)db.close();
		}	
		return getKodKementerian;	
	
	}	
	
	//END OF ELLY ADDED
	

	
	// GENERATE BAR/PIE CHART
	
	public  String generateXML(){
		
		
		String xml="<chart caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan' numberPrefix=''>";
		Db db = null;
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs =stat.executeQuery(getSQL());
			while(rs.next()){
				xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";
			}
			xml =xml+"</chart>";
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (db!=null) db.close();
		}

		
		return xml;
	}	
	
	public  String generateXMLKementerian(){
		
		
		String xml="<chart caption='Laporan' subcaption='' xAxisName='Kementerian' yAxisName='Jumlah Permohonan' numberPrefix=''>";
		Db db = null;
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs =stat.executeQuery(getSQL());
			while(rs.next()){
				xml =xml+"<set label='"+rs.getString("KOD_KEMENTERIAN")+"' value='"+rs.getString("y")+"' />";
			}
			xml =xml+"</chart>";
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (db!=null) db.close();
		}

		return xml;
	}

	


}
