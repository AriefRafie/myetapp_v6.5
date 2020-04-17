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

public class PHP_modeldata extends EkptgCache implements Serializable {
	static Logger myLogger = Logger.getLogger(PHP_modeldata.class);
	
public Vector getPelepasan = null;
public Vector getPenawaran = null;
public Vector getTukarguna = null;
public Vector getPenyewaan = null;
public Vector getPenguatkuasaan = null;
public Vector getAPB = null;
public Vector getTanahRizab = null;
public Vector getSerahBalikTanah = null;
public Vector getTanahPTP = null;
	
	private String SQL;
	
	public String getSQL() {
		return SQL;
	}

	public void setSQL(String sql) {
		SQL = sql;
	}

		public Vector getListTotalPelepasan(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPelepasan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT B.NAMA_NEGERI as x, COUNT(*) AS y ";					
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C";
					sql += " WHERE  A.ID_FAIL = C.ID_FAIL  AND A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_SUBURUSAN = 34 AND A.NO_FAIL IS NOT NULL";
					
					 
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY B.NAMA_NEGERI ";			
					
					setSQL(sql);
					
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
			    	
			    	getPelepasan.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
			return getPelepasan;					
	 }
	
		public Vector getListTotalPelepasanAbbrev(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPelepasan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT B.ABBREV as x, COUNT(*) AS y ";					
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C";
					sql += " WHERE  A.ID_FAIL = C.ID_FAIL  AND A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_SUBURUSAN = 34 AND A.NO_FAIL IS NOT NULL";
					
					 
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY B.ABBREV ";			
					
					setSQL(sql);
					
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	
			    	getPelepasan.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
			return getPelepasan;					
	 }
		
		public Vector getListTotalPenawaran(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPenawaran = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT B.NAMA_NEGERI as x, COUNT(*) AS y ";					
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C";
					sql += " WHERE  A.ID_FAIL = C.ID_FAIL  AND A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_SUBURUSAN = 32 AND A.NO_FAIL IS NOT NULL";
					
					 
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY B.NAMA_NEGERI ";			
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
			    	
			    	getPenawaran.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
			return getPenawaran;					
	 }
		
		public Vector getListTotalPenawaranAbbrev(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPenawaran = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT B.ABBREV as x, COUNT(*) AS y ";					
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C";
					sql += " WHERE  A.ID_FAIL = C.ID_FAIL  AND A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_SUBURUSAN = 32 AND A.NO_FAIL IS NOT NULL";
					
					 
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY B.ABBREV ";			
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	
			    	getPenawaran.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
			return getPenawaran;					
	 }
		
		
		public Vector getListTotalTukarguna(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getTukarguna = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT B.NAMA_NEGERI as x, COUNT(*) AS y ";					
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C";
					sql += " WHERE  A.ID_FAIL = C.ID_FAIL  AND A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_SUBURUSAN = 33 AND A.NO_FAIL IS NOT NULL";
					
					 
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY B.NAMA_NEGERI ";			
					
					setSQL(sql);
					
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
			    	
			    	getTukarguna.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
			return getTukarguna;					
	 }

		public Vector getListTotalTukargunaAbbrev(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getTukarguna = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT B.ABBREV as x, COUNT(*) AS y ";					
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C";
					sql += " WHERE  A.ID_FAIL = C.ID_FAIL  AND A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_SUBURUSAN = 33 AND A.NO_FAIL IS NOT NULL";
					
					 
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY B.ABBREV ";			
					
					setSQL(sql);
					
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	
			    	getTukarguna.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
			return getTukarguna;					
	 }
		
		public Vector getListTotalPenyewaan(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPenyewaan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT B.NAMA_NEGERI as x, COUNT(*) AS y";					
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C";
					sql += " WHERE A.ID_FAIL = C.ID_FAIL AND A.ID_NEGERI = B.ID_NEGERI AND A.ID_URUSAN IN (7,12,13) AND A.NO_FAIL IS NOT NULL";
					sql += " AND C.FLAG_PERJANJIAN = 'U' AND C.FLAG_AKTIF = 'Y'";
					
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY B.NAMA_NEGERI ";			
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
			    	
			    	getPenyewaan.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
			return getPenyewaan;					
	 }

		public Vector getListTotalPenyewaanAbbrev(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPenyewaan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT B.ABBREV as x, COUNT(*) AS y";					
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C";
					sql += " WHERE A.ID_FAIL = C.ID_FAIL AND A.ID_NEGERI = B.ID_NEGERI AND A.ID_URUSAN IN (7,12,13) AND A.NO_FAIL IS NOT NULL";
					sql += " AND C.FLAG_PERJANJIAN = 'U' AND C.FLAG_AKTIF = 'Y'";
					
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY B.ABBREV ";			
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	
			    	getPenyewaan.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
			return getPenyewaan;					
	 }
		
		
		public Vector getListTotalPenguatkuasaan(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPenguatkuasaan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT B.NAMA_NEGERI as x, COUNT(*) AS y ";					
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C";
					sql += " WHERE  A.ID_FAIL = C.ID_FAIL  AND A.ID_NEGERI = B.ID_NEGERI AND A.ID_URUSAN = 8 AND A.NO_FAIL IS NOT NULL";
					
					 
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY B.NAMA_NEGERI ";			
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
			    	
			    	getPenguatkuasaan.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
			return getPenguatkuasaan;					
	 }
		public Vector getListTotalPenguatkuasaanAbbrev(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPenguatkuasaan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT B.ABBREV as x, COUNT(*) AS y ";					
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C";
					sql += " WHERE  A.ID_FAIL = C.ID_FAIL  AND A.ID_NEGERI = B.ID_NEGERI AND A.ID_URUSAN = 8 AND A.NO_FAIL IS NOT NULL";
					
					 
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY B.ABBREV ";			
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	
			    	getPenguatkuasaan.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
			return getPenguatkuasaan;					
	 }
		
		
		public Vector getListTotalAPB(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getAPB = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT B.NAMA_NEGERI as x, COUNT(*) AS y ";					
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C, TBLPHPPMOHONNJDUALPERTAMA D";
					sql += " WHERE  A.ID_FAIL = C.ID_FAIL  AND C.ID_PERMOHONAN = D.ID_PERMOHONAN AND B.ID_NEGERI = D.ID_NEGERI_PERAIRAN(+) AND A.ID_URUSAN = 9 AND A.NO_FAIL IS NOT NULL";
					
					
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY B.NAMA_NEGERI ";			
					
					setSQL(sql);
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
			    	
			    	getAPB.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
			return getAPB;					
	 }

		public Vector getListTotalAPBAbbrev(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getAPB = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT B.ABBREV as x, COUNT(*) AS y ";					
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C, TBLPHPPMOHONNJDUALPERTAMA D";
					sql += " WHERE  A.ID_FAIL = C.ID_FAIL  AND C.ID_PERMOHONAN = D.ID_PERMOHONAN AND B.ID_NEGERI = D.ID_NEGERI_PERAIRAN(+) AND A.ID_URUSAN = 9 AND A.NO_FAIL IS NOT NULL";
					
					
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY B.ABBREV ";			
					
					setSQL(sql);
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	
			    	getAPB.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
			return getAPB;					
	 }
		
		
		public Vector getListTotalTanahRizab(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getTanahRizab = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql = " SELECT B.NAMA_NEGERI as x, COUNT(*) AS y";
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C, TBLPHPHAKMILIKPERMOHONAN D, TBLHTPHAKMILIKAGENSI E, TBLHTPHAKMILIK F";
					sql += " WHERE A.ID_FAIL = C.ID_FAIL AND A.ID_NEGERI = B.ID_NEGERI AND C.ID_PERMOHONAN = D.ID_PERMOHONAN AND D.ID_HAKMILIKAGENSI = E.ID_HAKMILIKAGENSI ";
					sql += " AND E.ID_HAKMILIK = F.ID_HAKMILIK AND F.NO_HAKMILIK IS NULL AND F.NO_WARTA IS NOT NULL AND A.ID_SUBURUSAN = 34";
					
					
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY B.NAMA_NEGERI ";			
					
					setSQL(sql);

					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
			    	
			    	getTanahRizab.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
			return getTanahRizab;					
	 }

		public Vector getListTotalTanahRizabAbbrev(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getTanahRizab = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql = " SELECT B.ABBREV as x, COUNT(*) AS y";
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C, TBLPHPHAKMILIKPERMOHONAN D, TBLHTPHAKMILIKAGENSI E, TBLHTPHAKMILIK F";
					sql += " WHERE A.ID_FAIL = C.ID_FAIL AND A.ID_NEGERI = B.ID_NEGERI AND C.ID_PERMOHONAN = D.ID_PERMOHONAN AND D.ID_HAKMILIKAGENSI = E.ID_HAKMILIKAGENSI ";
					sql += " AND E.ID_HAKMILIK = F.ID_HAKMILIK AND F.NO_HAKMILIK IS NULL AND F.NO_WARTA IS NOT NULL AND A.ID_SUBURUSAN = 34";
					
					
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY B.ABBREV ";			
					
					setSQL(sql);

					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	
			    	getTanahRizab.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
			return getTanahRizab;					
	 }

		
		public Vector getListTotalTukargunaTanahPTP(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getTanahPTP = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT  E.NAMA_NEGERI as x, COUNT(*) AS y";					
					sql += " FROM TBLPFDFAIL A, TBLPERMOHONAN B,TBLPHPHAKMILIKPERMOHONAN C,TBLHTPHAKMILIK D, TBLRUJNEGERI E";
					sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_HAKMILIK = D.ID_HAKMILIK AND D.STATUS_RIZAB IS NULL AND D.ID_NEGERI = E.ID_NEGERI ";
					

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
						
					sql += " GROUP BY E.NAMA_NEGERI ";			
					
					setSQL(sql);

					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
			    	
			    	getTanahPTP.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
			return getTanahPTP;					
	 }
		
		public Vector getListTotalSerahBalikTanah(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getSerahBalikTanah = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql = " SELECT B.NAMA_NEGERI as x, COUNT(*) AS y";
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C, TBLPHPHAKMILIKPERMOHONAN D, TBLHTPHAKMILIKAGENSI E, TBLHTPHAKMILIK F";
					sql += " WHERE A.ID_FAIL = C.ID_FAIL AND A.ID_NEGERI = B.ID_NEGERI AND C.ID_PERMOHONAN = D.ID_PERMOHONAN AND D.ID_HAKMILIKAGENSI = E.ID_HAKMILIKAGENSI";
					sql += " AND E.ID_HAKMILIK = F.ID_HAKMILIK AND F.NO_HAKMILIK IS NOT NULL AND F.NO_WARTA IS NULL AND A.ID_SUBURUSAN = 34";
					

					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY B.NAMA_NEGERI ";			
					
					setSQL(sql);

					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
			    	
			    	getSerahBalikTanah.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
			return getSerahBalikTanah;					
	 }
		public Vector getListTotalSerahBalikTanahAbbrev(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getSerahBalikTanah = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql = " SELECT B.ABBREV as x, COUNT(*) AS y";
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C, TBLPHPHAKMILIKPERMOHONAN D, TBLHTPHAKMILIKAGENSI E, TBLHTPHAKMILIK F";
					sql += " WHERE A.ID_FAIL = C.ID_FAIL AND A.ID_NEGERI = B.ID_NEGERI AND C.ID_PERMOHONAN = D.ID_PERMOHONAN AND D.ID_HAKMILIKAGENSI = E.ID_HAKMILIKAGENSI";
					sql += " AND E.ID_HAKMILIK = F.ID_HAKMILIK AND F.NO_HAKMILIK IS NOT NULL AND F.NO_WARTA IS NULL AND A.ID_SUBURUSAN = 34";
					

					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(C.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY B.ABBREV ";			
					
					setSQL(sql);

					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	
			    	getSerahBalikTanah.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
			return getSerahBalikTanah;					
	 }
		
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
	
	
}
