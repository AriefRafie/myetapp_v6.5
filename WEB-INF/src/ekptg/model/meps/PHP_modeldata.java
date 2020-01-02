package ekptg.model.meps;

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
public Vector getAbbrev = null;
	
	private String SQL;
	
	public String getSQL() {
		return SQL;
	}

	public void setSQL(String sql) {
		SQL = sql;
	}
	
	// Faresh ADDED 10.12.2012	
	public Vector getAbbrev() throws Exception {
		Db db = null;
		String sql = "";
		try{
			getAbbrev = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();									
			
			sql =  " SELECT distinct(RK.ABBREV) AS ABBREV,RK.ID_NEGERI, RK.NAMA_NEGERI AS NEGERI " +
					" FROM TBLRUJNEGERI RK WHERE RK.ABBREV <>'PEL'" +
					" ORDER BY RK.ID_NEGERI" +
					"";
	//		myLog.info(sql);
				setSQL(sql);				
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;				
			while (rs.next()) {
				h = new Hashtable();	        
		    	h.put("abbrev", Utils.isNull(rs.getString("ABBREV")));
		    	h.put("negeri", Utils.isNull(rs.getString("NEGERI")));		    	
		    	getAbbrev.addElement(h);
			}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		}finally{
			if(db != null)db.close();
		}	
		return getAbbrev;	
	
	}	
	

		public Vector getListTotalPelepasan(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPelepasan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT B.NAMA_NEGERI, B.ABBREV as x, COUNT(*) AS y " 					
					     + " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C "
						 + " WHERE  A.ID_FAIL = C.ID_FAIL  AND A.ID_NEGERI = B.ID_NEGERI(+) " 
					     + " AND A.ID_SEKSYEN = '4' AND A.ID_URUSAN = '6' "
						 + " AND A.ID_SUBURUSAN = '34' AND A.NO_FAIL IS NOT NULL";
					
					 
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
					
					if (txdTarikhMula != null && txdTarikhAkhir != null) {
						if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					
					sql += " AND C.TARIKH_TERIMA BETWEEN "+
					" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
					" 'DD/MM/YYYY')";
					
						}
					}
						
					sql += " GROUP BY B.NAMA_NEGERI, B.ABBREV ";	
					sql += " ORDER BY B.NAMA_NEGERI ";	
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));
			    	
			    	getPelepasan.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getPelepasan;					
	 }
	
		/*public Vector getListTotalPelepasanAbbrev(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPelepasan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT B.NAMA_NEGERI, B.ABBREV as x, COUNT(*) AS y ";					
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C";
					sql += " WHERE  A.ID_FAIL = C.ID_FAIL  AND A.ID_NEGERI = B.ID_NEGERI(+) AND B.ID_NEGERI NOT IN ('0') AND A.ID_SUBURUSAN = 34 AND A.NO_FAIL IS NOT NULL";
					
					 
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
					if (txdTarikhMula != null && txdTarikhAkhir != null) {
						if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					
					sql += " AND C.TARIKH_TERIMA BETWEEN "+
					" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
					" 'DD/MM/YYYY')";
					
						}
					}

						
					sql += " GROUP BY B.NAMA_NEGERI,B.ABBREV ";
					sql += " ORDER BY B.NAMA_NEGERI ";
					
					setSQL(sql);
					
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

			    	getPelepasan.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getPelepasan;					
	 }*/
		
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
					sql += " ORDER BY B.NAMA_NEGERI ";	
					
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
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
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
					
					sql =  " SELECT B.NAMA_NEGERI,B.ABBREV as x, COUNT(*) AS y ";					
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
						
					sql += " GROUP BY B.NAMA_NEGERI,B.ABBREV ";	
					sql += " ORDER BY B.NAMA_NEGERI ";
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

			    	getPenawaran.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getPenawaran;					
	 }
		public Vector getListTotalPenawaranAbbrev_baru(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPenawaran = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT B.NAMA_NEGERI,B.ABBREV as x, COUNT(*) AS y ";					
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C";
					sql += " WHERE  A.ID_FAIL = C.ID_FAIL  AND A.ID_NEGERI = B.ID_NEGERI(+) AND  B.ID_NEGERI  NOT IN ('0') AND A.ID_SUBURUSAN = 32 AND A.NO_FAIL IS NOT NULL";
					
					 
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
					
					if (txdTarikhMula != null && txdTarikhAkhir != null) {
						if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					
					sql += " AND C.TARIKH_TERIMA BETWEEN "+
					" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
					" 'DD/MM/YYYY')";
					
						}
					}

					
					sql += " GROUP BY B.NAMA_NEGERI,B.ABBREV ";	
					sql += " ORDER BY B.NAMA_NEGERI ";	
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

			    	getPenawaran.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
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
					
					sql =  " SELECT B.NAMA_NEGERI, B.ABBREV as x, COUNT(*) AS y ";					
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
						
					sql += " GROUP BY B.NAMA_NEGERI, B.ABBREV ";
					sql += " ORDER BY B.NAMA_NEGERI ";	
					
					setSQL(sql);
					
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));
			    	
			    	getTukarguna.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
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
					
					sql =  " SELECT B.NAMA_NEGERI, B.ABBREV as x, COUNT(*) AS y ";					
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
						
					sql += " GROUP BY B.NAMA_NEGERI, B.ABBREV ";
					sql += " ORDER BY B.NAMA_NEGERI ";
					
					setSQL(sql);
					
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

			    	getTukarguna.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getTukarguna;					
	 }
		
		public Vector getListTotalTukargunaAbbrev_baru(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getTukarguna = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT B.NAMA_NEGERI, B.ABBREV as x, COUNT(*) AS y ";					
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C";
					sql += " WHERE  A.ID_FAIL = C.ID_FAIL  AND A.ID_NEGERI = B.ID_NEGERI(+) AND  B.ID_NEGERI  NOT IN ('0') AND A.ID_SUBURUSAN = 33 AND A.NO_FAIL IS NOT NULL";
					
					 
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
					
					if (txdTarikhMula != null && txdTarikhAkhir != null) {
						if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					
					sql += " AND C.TARIKH_TERIMA BETWEEN "+
					" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
					" 'DD/MM/YYYY')";
					
						}
					}
						
					sql += " GROUP BY B.NAMA_NEGERI, B.ABBREV ";
					sql += " ORDER BY B.NAMA_NEGERI ";
					
					setSQL(sql);
					
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

			    	getTukarguna.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
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
					sql += " ORDER BY B.NAMA_NEGERI ";	
					
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
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getPenyewaan;					
	 }
		
		public Vector getListTotalPenyewaan_baru(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPenyewaan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT  B.NAMA_NEGERI, B.ABBREV AS x, COUNT(*) AS y "					
						     + " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C "
						     + " WHERE A.ID_FAIL = C.ID_FAIL AND A.ID_NEGERI = B.ID_NEGERI AND A.ID_SEKSYEN = 4 " 
						     + " AND A.ID_URUSAN IN (7,12,13) AND A.NO_FAIL IS NOT NULL "
						     + " AND B.ID_NEGERI NOT IN ('0') AND C.FLAG_PERJANJIAN = 'U' AND C.FLAG_AKTIF = 'Y'";
					
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
					
					if (txdTarikhMula != null && txdTarikhAkhir != null) {
						if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					
					sql += " AND C.TARIKH_TERIMA BETWEEN "+
					" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
					" 'DD/MM/YYYY')";
					
						}
					}
						
					sql += " GROUP BY B.NAMA_NEGERI, B.ABBREV ";
					sql += " ORDER BY B.NAMA_NEGERI ";
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));
			    	
			    	getPenyewaan.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
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
					
					sql =  " SELECT B.NAMA_NEGERI,B.ABBREV as x, COUNT(*) AS y";					
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
						
					sql += " GROUP BY B.NAMA_NEGERI,B.ABBREV ";	
					sql += " ORDER BY B.NAMA_NEGERI ";	
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

			    	getPenyewaan.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getPenyewaan;					
	 }
		public Vector getListTotalPenyewaanAbbrev_baru(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPenyewaan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT  B.NAMA_NEGERI, B.ABBREV AS x, COUNT(*) AS y "					
					     + " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C "
					     + " WHERE A.ID_FAIL = C.ID_FAIL AND A.ID_NEGERI = B.ID_NEGERI AND A.ID_SEKSYEN = 4 " 
					     + " AND A.ID_URUSAN IN (7,12,13) AND A.NO_FAIL IS NOT NULL "
					     + " AND B.ID_NEGERI NOT IN ('0') AND C.FLAG_PERJANJIAN = 'U' AND C.FLAG_AKTIF = 'Y'";
					
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
					
					if (txdTarikhMula != null && txdTarikhAkhir != null) {
						if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					
					sql += " AND C.TARIKH_TERIMA BETWEEN "+
					" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
					" 'DD/MM/YYYY')";
					
						}
					}
						
					sql += " GROUP BY B.NAMA_NEGERI, B.ABBREV ";	
					sql += " ORDER BY B.NAMA_NEGERI ";	
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

			    	getPenyewaan.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
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
					
					sql = " SELECT  B.NAMA_NEGERI, B.ABBREV AS x, COUNT(*) AS y "				
						    + " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C "
						    + " WHERE  A.ID_FAIL = C.ID_FAIL  AND A.ID_NEGERI = B.ID_NEGERI AND  A.ID_SEKSYEN = 4 " 
						    + " AND  A.ID_URUSAN IN (8) AND  A.NO_FAIL IS NOT NULL AND  B.ID_NEGERI NOT IN ('0') "
						    + " AND  C.FLAG_AKTIF = 'Y' ";
					 
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
						
					sql += " GROUP BY B.NAMA_NEGERI, B.ABBREV ";
					sql += " ORDER BY B.NAMA_NEGERI ";	
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));
			    	
			    	getPenguatkuasaan.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getPenguatkuasaan;					
	 }
		
		public Vector getListTotalPenguatkuasaanMengikutKJP(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPenguatkuasaan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT  TRK.NAMA_KEMENTERIAN AS KJP, TRK.NAMA_KEMENTERIAN AS x, COUNT(*) AS y "
							 + " FROM  TBLPFDFAIL TPF, TBLRUJKEMENTERIAN TRK, TBLPERMOHONAN TP "
							 + " WHERE  TPF.ID_FAIL=TP.ID_FAIL "
							 + " AND  TPF.ID_KEMENTERIAN=TRK.ID_KEMENTERIAN AND  TPF.ID_SEKSYEN = 4 "
							 + " AND  TPF.ID_URUSAN IN (8) AND  TPF.NO_FAIL IS NOT NULL "
							 + " AND  TP.FLAG_AKTIF = 'Y'";		
					 
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(TP.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(TP.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY TRK.NAMA_KEMENTERIAN ";	
					sql += " ORDER BY TRK.NAMA_KEMENTERIAN ";
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("kjp", Utils.NiceStateName(rs.getString("x")));
			    	
			    	getPenguatkuasaan.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getPenguatkuasaan;					
	 }
		
		public Vector getListTotalPenguatkuasaanMengikutStatus(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPenguatkuasaan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT  TRS.KETERANGAN AS STATUS, TRS.KETERANGAN AS x, COUNT(*) AS y "
							 + " FROM  TBLPFDFAIL TPF, TBLPERMOHONAN TP, TBLRUJSTATUS TRS "
							 + " WHERE  TPF.ID_FAIL=TP.ID_FAIL AND  TP.ID_STATUS=TRS.ID_STATUS "
							 + " AND  TPF.ID_SEKSYEN=4 AND  TPF.ID_URUSAN=8 "
							 + " AND  TPF.NO_FAIL IS NOT NULL AND  TP.FLAG_AKTIF = 'Y'";
					 
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(TP.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(TP.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY TRS.KETERANGAN ";	
					sql += " ORDER BY TRS.KETERANGAN ";
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("status", Utils.NiceStateName(rs.getString("x")));
			    	
			    	getPenguatkuasaan.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getPenguatkuasaan;					
	 }
		
		/*public Vector getListTotalPenguatkuasaanAbbrev(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPenguatkuasaan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT B.NAMA_NEGERI, B.ABBREV as x, COUNT(*) AS y ";					
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
						
					sql += " GROUP BY B.NAMA_NEGERI,B.ABBREV ";	
					sql += " ORDER BY B.NAMA_NEGERI ";
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

			    	getPenguatkuasaan.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getPenguatkuasaan;					
	 }*/
		
		public Vector getListTotalPenguatkuasaanAbbrevMengikutKJP(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPenguatkuasaan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT  TRK.NAMA_KEMENTERIAN AS KJP, TRK.NAMA_KEMENTERIAN AS x, COUNT(*) AS y "
						 + " FROM  TBLPFDFAIL TPF, TBLRUJKEMENTERIAN TRK, TBLPERMOHONAN TP "
						 + " WHERE  TPF.ID_FAIL=TP.ID_FAIL "
						 + " AND  TPF.ID_KEMENTERIAN=TRK.ID_KEMENTERIAN AND  TPF.ID_SEKSYEN = 4 "
						 + " AND  TPF.ID_URUSAN IN (8) AND  TPF.NO_FAIL IS NOT NULL "
						 + " AND  TP.FLAG_AKTIF = 'Y'";		
					 
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(TP.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(TP.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY TRK.NAMA_KEMENTERIAN ";
					sql += " ORDER BY TRK.NAMA_KEMENTERIAN ";
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("kjp",rs.getString("KJP"));

			    	getPenguatkuasaan.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getPenguatkuasaan;					
	 }
		
		
		
		public Vector getListTotalPenguatkuasaanAbbrevMengikutStatus(String socTahun,String socBulan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPenguatkuasaan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT  TRS.KETERANGAN AS STATUS, TRS.KETERANGAN AS x, COUNT(*) AS y "
							 + " FROM  TBLPFDFAIL TPF, TBLPERMOHONAN TP, TBLRUJSTATUS TRS "
							 + " WHERE  TPF.ID_FAIL=TP.ID_FAIL AND  TP.ID_STATUS=TRS.ID_STATUS "
							 + " AND  TPF.ID_SEKSYEN=4 AND  TPF.ID_URUSAN=8 "
							 + " AND  TPF.NO_FAIL IS NOT NULL AND  TP.FLAG_AKTIF = 'Y'";	
					 
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(TP.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(TP.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
						
					sql += " GROUP BY TRS.KETERANGAN ";	
					sql += " ORDER BY TRS.KETERANGAN ";
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("status",rs.getString("STATUS"));

			    	getPenguatkuasaan.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getPenguatkuasaan;					
	 }
		
	 public Vector getListTotalPenguatkuasaanAbbrev_baru(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
			Db db = null;
			String sql = "";
			try{
					getPenguatkuasaan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql = " SELECT  B.NAMA_NEGERI, B.ABBREV AS x, COUNT(*) AS y "				
					    + " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C "
					    + " WHERE  A.ID_FAIL = C.ID_FAIL  AND A.ID_NEGERI = B.ID_NEGERI AND  A.ID_SEKSYEN = 4 " 
					    + " AND  A.ID_URUSAN IN (8) AND  A.NO_FAIL IS NOT NULL AND  B.ID_NEGERI NOT IN ('0') "
					    + " AND  C.FLAG_AKTIF = 'Y' ";
					 
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
					
					if (txdTarikhMula != null && txdTarikhAkhir != null) {
						if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					
					sql += " AND C.TARIKH_TERIMA BETWEEN "+
					" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
					" 'DD/MM/YYYY')";
					
						}
					}
						
					sql += " GROUP BY B.NAMA_NEGERI,B.ABBREV ";	
					sql += " ORDER BY B.NAMA_NEGERI ";	
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

			    	getPenguatkuasaan.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getPenguatkuasaan;					
	 }
		
		public Vector getListTotalPenguatkuasaanAbbrevBaruMengikutKJP(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPenguatkuasaan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT  TRK.NAMA_KEMENTERIAN AS KJP, TRK.NAMA_KEMENTERIAN AS x, COUNT(*) AS y "
							 + " FROM  TBLPFDFAIL TPF, TBLRUJKEMENTERIAN TRK, TBLPERMOHONAN TP "
							 + " WHERE  TPF.ID_FAIL=TP.ID_FAIL "
							 + " AND  TPF.ID_KEMENTERIAN=TRK.ID_KEMENTERIAN AND  TPF.ID_SEKSYEN = 4 "
							 + " AND  TPF.ID_URUSAN IN (8) AND  TPF.NO_FAIL IS NOT NULL "
							 + " AND  TP.FLAG_AKTIF = 'Y'";		
					
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(TP.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(TP.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
					
					if (txdTarikhMula != null && txdTarikhAkhir != null) {
						if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					
					sql += " AND TP.TARIKH_TERIMA BETWEEN "+
					" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
					" 'DD/MM/YYYY')";
					
						}
					}
						
					sql += " GROUP BY TRK.NAMA_KEMENTERIAN ";
					sql += " ORDER BY TRK.NAMA_KEMENTERIAN ";
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("kjp",rs.getString("KJP"));

			    	getPenguatkuasaan.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getPenguatkuasaan;					
	 }		
		
		public Vector getListTotalPenguatkuasaanAbbrevBaruMengikutStatus(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getPenguatkuasaan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT  TRS.KETERANGAN AS STATUS, TRS.KETERANGAN AS x, COUNT(*) AS y "
							 + " FROM  TBLPFDFAIL TPF, TBLPERMOHONAN TP, TBLRUJSTATUS TRS "
							 + " WHERE  TPF.ID_FAIL=TP.ID_FAIL AND  TP.ID_STATUS=TRS.ID_STATUS "
							 + " AND  TPF.ID_SEKSYEN=4 AND  TPF.ID_URUSAN=8 "
							 + " AND  TPF.NO_FAIL IS NOT NULL AND  TP.FLAG_AKTIF = 'Y'";
					
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(TP.TARIKH_TERIMA,'YYYY') = '" + socTahun +"' ";
						}
					}
					
		    		// BULAN
					if (socBulan != null) {
						if (!socBulan.trim().equals("")) {
							sql = sql + " AND TO_CHAR(TP.TARIKH_TERIMA,'MM') = '" + socBulan +"' ";
						}
					}
					
					if (txdTarikhMula != null && txdTarikhAkhir != null) {
						if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					
					sql += " AND TP.TARIKH_TERIMA BETWEEN "+
					" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
					" 'DD/MM/YYYY')";
					
						}
					}
						
					sql += " GROUP BY TRS.KETERANGAN ";	
					sql += " ORDER BY TRS.KETERANGAN ";	
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("status",rs.getString("STATUS"));

			    	getPenguatkuasaan.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
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
					
					sql =  " SELECT   F.NAMA_NEGERI AS x, COUNT (*) AS y "	+				
							" FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, " +
							" TBLPHPBYRNSYRTKLLSNLESENAPB E, TBLRUJNEGERI F, TBLPHPPMOHONNJDUALPERTAMA G " +
							" WHERE  A.ID_FAIL = B.ID_FAIL  AND B.ID_STATUS = D.ID_STATUS(+) AND B.ID_PEMOHON = C.ID_PEMOHON(+) " +
							" AND B.ID_PERMOHONAN = G.ID_PERMOHONAN(+) AND B.ID_PERMOHONAN = E.ID_PERMOHONAN(+) " +
							" AND F.ID_NEGERI(+) = G.ID_NEGERI_PERAIRAN AND A.NO_FAIL IS NOT NULL " +
							" AND A.ID_URUSAN = 9 AND A.NO_FAIL IS NOT NULL";
					
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
						
					sql += " GROUP BY F.NAMA_NEGERI ";
					sql += " ORDER BY F.NAMA_NEGERI ";	
					
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
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getAPB;					
	 }
        
		public Vector getListTotalAPB_baru(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getAPB = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT F.NAMA_NEGERI, F.ABBREV AS x, COUNT (*) AS y "			
							 + " FROM TBLPFDFAIL A, TBLPERMOHONAN B, "
							 + " TBLPHPBYRNSYRTKLLSNLESENAPB E, TBLRUJNEGERI F, TBLPHPPMOHONNJDUALPERTAMA G "
							 + " WHERE  A.ID_FAIL = B.ID_FAIL "
							 + " AND B.ID_PERMOHONAN = G.ID_PERMOHONAN(+) AND B.ID_PERMOHONAN = E.ID_PERMOHONAN(+) "
							 + " AND F.ID_NEGERI(+) = G.ID_NEGERI_PERAIRAN AND A.NO_FAIL IS NOT NULL "
							 + " AND A.ID_URUSAN = 9 AND A.NO_FAIL IS NOT NULL AND F.ID_NEGERI NOT IN (0)";
					
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
					if (txdTarikhMula != null && txdTarikhAkhir != null) {
						if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					
					sql += " AND B.TARIKH_TERIMA BETWEEN "+
					" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
					" 'DD/MM/YYYY')";
					
						}
					}

					sql += " GROUP BY F.NAMA_NEGERI, F.ABBREV ";
					sql += " ORDER BY F.NAMA_NEGERI ";
					
					myLogger.info("getListTotalAPB_baru: "+sql);
					
					setSQL(sql);
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_negeri", rs.getString("NAMA_NEGERI") == null ? ""
							: rs.getString("NAMA_NEGERI"));
			    	getAPB.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
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
					
					sql =  " SELECT B.NAMA_NEGERI,B.ABBREV as x, COUNT(*) AS y ";					
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
						
					sql += " GROUP BY  B.NAMA_NEGERI,B.ABBREV ";	
					sql += " ORDER BY  B.NAMA_NEGERI ";
					
					myLogger.info("APB: "+sql);
					
					setSQL(sql);
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

			    	getAPB.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getAPB;					
	 }
		
     public Vector getListTotalAPBAbbrev_baru(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir
				)throws Exception {		 		 
			Db db = null;
			String sql = "";
			try{
					getAPB = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT F.NAMA_NEGERI, F.ABBREV AS x, COUNT (*) AS y "			
						 + " FROM TBLPFDFAIL A, TBLPERMOHONAN B, "
						 + " TBLPHPBYRNSYRTKLLSNLESENAPB E, TBLRUJNEGERI F, TBLPHPPMOHONNJDUALPERTAMA G "
						 + " WHERE  A.ID_FAIL = B.ID_FAIL "
						 + " AND B.ID_PERMOHONAN = G.ID_PERMOHONAN(+) AND B.ID_PERMOHONAN = E.ID_PERMOHONAN(+) "
						 + " AND F.ID_NEGERI(+) = G.ID_NEGERI_PERAIRAN AND A.NO_FAIL IS NOT NULL "
						 + " AND A.ID_URUSAN = 9 AND A.NO_FAIL IS NOT NULL AND F.ID_NEGERI NOT IN (0)";
 
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
					if (txdTarikhMula != null && txdTarikhAkhir != null) {
						if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					
					sql += " AND B.TARIKH_TERIMA BETWEEN "+
					" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
					" 'DD/MM/YYYY')";
					
						}
					}
						
					sql += " GROUP BY F.NAMA_NEGERI, F.ABBREV ";
					sql += " ORDER BY F.NAMA_NEGERI ";
					
					myLogger.info("APB2: "+sql);
					
					setSQL(sql);
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_negeri", rs.getString("NAMA_NEGERI") == null ? ""
							: rs.getString("NAMA_NEGERI"));

			    	getAPB.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getAPB;					
	 }
		
		
		public Vector getListTotalTanahRizab(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getTanahRizab = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql = " SELECT B.NAMA_NEGERI,B.ABBREV AS x, COUNT(*) AS y "
					    + " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C, TBLPHPHAKMILIKPERMOHONAN D, TBLPHPHAKMILIK E "
					    + " WHERE A.ID_FAIL = C.ID_FAIL AND A.ID_NEGERI = B.ID_NEGERI AND C.ID_PERMOHONAN = D.ID_PERMOHONAN "
					    + " AND D.ID_HAKMILIKPERMOHONAN = E.ID_HAKMILIKPERMOHONAN "
					    + " AND A.ID_SEKSYEN = '4' AND A.ID_URUSAN = '6' AND A.ID_SUBURUSAN = '34' AND B.ID_NEGERI  NOT IN ('0') " 
					    + " AND D.FLAG_HAKMILIK = 'U' AND A.NO_FAIL IS NOT NULL AND E.NO_HAKMILIK IS NULL AND E.NO_WARTA IS NOT NULL";
					
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
					
					if (txdTarikhMula != null && txdTarikhAkhir != null) {
						if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					
					sql += " AND C.TARIKH_TERIMA BETWEEN "+
					" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
					" 'DD/MM/YYYY')";
					
						}
					}
						
					sql += " GROUP BY B.NAMA_NEGERI, B.ABBREV ";	
					sql += " ORDER BY B.NAMA_NEGERI ";	
					
					setSQL(sql);

					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));
			    	
			    	getTanahRizab.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getTanahRizab;					
	 }

		/*public Vector getListTotalTanahRizabAbbrev(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir
)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getTanahRizab = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql = " SELECT B.NAMA_NEGERI,B.ABBREV as x, COUNT(*) AS y";
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C, TBLPHPHAKMILIKPERMOHONAN D, TBLHTPHAKMILIKAGENSI E, TBLHTPHAKMILIK F";
					sql += " WHERE A.ID_FAIL = C.ID_FAIL AND A.ID_NEGERI = B.ID_NEGERI AND  B.ID_NEGERI  NOT IN ('0')  AND C.ID_PERMOHONAN = D.ID_PERMOHONAN AND D.ID_HAKMILIKAGENSI = E.ID_HAKMILIKAGENSI ";
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
					
					if (txdTarikhMula != null && txdTarikhAkhir != null) {
						if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					
					sql += " AND C.TARIKH_TERIMA BETWEEN "+
					" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
					" 'DD/MM/YYYY')";
					
						}
					}

						
					sql += " GROUP BY B.NAMA_NEGERI,B.ABBREV ";	
					sql += " ORDER BY B.NAMA_NEGERI";
					
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
			    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));


			    	getTanahRizab.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getTanahRizab;					
	 }*/

		
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
					sql += " ORDER BY E.NAMA_NEGERI ";	
					
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
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getTanahPTP;					
	 }
		
	 public Vector getListTotalSerahBalikTanah(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
			Db db = null;
			String sql = "";
			try{
					getSerahBalikTanah = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql = " SELECT B.NAMA_NEGERI,B.ABBREV AS x, COUNT(*) AS y "
						    + " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C, TBLPHPHAKMILIKPERMOHONAN D, TBLPHPHAKMILIK E "
						    + " WHERE A.ID_FAIL = C.ID_FAIL AND A.ID_NEGERI = B.ID_NEGERI AND C.ID_PERMOHONAN = D.ID_PERMOHONAN "
						    + " AND D.ID_HAKMILIKPERMOHONAN = E.ID_HAKMILIKPERMOHONAN "
						    + " AND A.ID_SEKSYEN = '4' AND A.ID_URUSAN = '6' AND A.ID_SUBURUSAN = '34' AND B.ID_NEGERI  NOT IN ('0') " 
						    + " AND D.FLAG_HAKMILIK = 'U' AND A.NO_FAIL IS NOT NULL AND E.NO_HAKMILIK IS NOT NULL AND E.NO_WARTA IS NULL";
					
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
						
					if (txdTarikhMula != null && txdTarikhAkhir != null) {
						if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					
					sql += " AND C.TARIKH_TERIMA BETWEEN "+
					" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
					" 'DD/MM/YYYY')";
					
						}
					}

					sql += " GROUP BY B.NAMA_NEGERI, B.ABBREV ";
					sql += " ORDER BY B.NAMA_NEGERI ";	
					
					setSQL(sql);

					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));
			    	
			    	getSerahBalikTanah.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
	  return getSerahBalikTanah;					
	 }
		/*public Vector getListTotalSerahBalikTanahAbbrev(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getSerahBalikTanah = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql = " SELECT B.NAMA_NEGERI, B.ABBREV as x, COUNT(*) AS y";
					sql += " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C, TBLPHPHAKMILIKPERMOHONAN D, TBLHTPHAKMILIKAGENSI E, TBLHTPHAKMILIK F";
					sql += " WHERE A.ID_FAIL = C.ID_FAIL AND A.ID_NEGERI = B.ID_NEGERI AND  B.ID_NEGERI  NOT IN ('0') AND C.ID_PERMOHONAN = D.ID_PERMOHONAN AND D.ID_HAKMILIKAGENSI = E.ID_HAKMILIKAGENSI";
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
					
					if (txdTarikhMula != null && txdTarikhAkhir != null) {
						if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					
					sql += " AND C.TARIKH_TERIMA BETWEEN "+
					" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
					" 'DD/MM/YYYY')";
					
						}
					}

					sql += " GROUP BY B.NAMA_NEGERI,B.ABBREV ";
					sql += " ORDER BY B.NAMA_NEGERI ";	
					
					setSQL(sql);

					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();	
			    	
			    	h.put("bil", bil);
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

			    	getSerahBalikTanah.addElement(h);
			    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
					finally{
						if(db != null)db.close();
					}	
			return getSerahBalikTanah;					
	 }*/
		
		// GENERATE BAR/PIE CHART
		
		public  String generateXML(String nama_laporan){
			
			String xml="<chart caption='"+nama_laporan+"' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan' numberPrefix='' showLegend='1'>";
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
			} catch (Exception er) {
				myLogger.error(er);
			} finally {
				if (db!=null) db.close();
			}

			return xml;
		}

		public Vector getListDaerahByNegeri(Long idNegeri, String socTahun, String socBulan, String txdTarikhMula, String txdTarikhAkhir) throws Exception {
			 Db db = null;
			 String sql = "";
			try{
					getPelepasan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();									
					
					sql =  " SELECT F.NAMA_DAERAH as x, COUNT(*) AS y " 
						 + " FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLPERMOHONAN C , TBLPHPHAKMILIKPERMOHONAN D , TBLPHPHAKMILIK E , TBLRUJDAERAH F "
						 + " WHERE  A.ID_FAIL = C.ID_FAIL  AND A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_SUBURUSAN = 34 AND " 
						 + " A.NO_FAIL IS NOT NULL AND D.ID_PERMOHONAN = C.ID_PERMOHONAN " 
						 + " AND E.ID_HAKMILIKPERMOHONAN = D.ID_HAKMILIKPERMOHONAN AND F.ID_DAERAH = E.ID_DAERAH AND D.FLAG_HAKMILIK = 'U' "
						 + " AND B.ID_NEGERI = '" + idNegeri + "' ";
					
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
					
					if (txdTarikhMula != null && txdTarikhAkhir != null) {
						if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
							sql += " AND C.TARIKH_TERIMA BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', 'DD/MM/YYYY')";
						}
					}
					sql = sql + "GROUP BY F.NAMA_DAERAH ";
					sql = sql + "ORDER BY F.NAMA_DAERAH ";
					
					setSQL(sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
				     while (rs.next()) {
				    	h = new Hashtable();	
				    	h.put("bil", bil);
				    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				    	h.put("nama_daerah", Utils.NiceStateName(rs.getString("x")));
				    	getPelepasan.addElement(h);
				    	bil++;
			      	}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				}
				finally{
					if(db != null)db.close();
				}	
			return getPelepasan;							
		}

		public Vector getAbbrevDaerah(Long idNegeri) throws Exception {
			Db db = null;
			String sql = "";
			try{
				getAbbrev = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT distinct(RK.ID_NEGERI), RK.NAMA_DAERAH, RK.ID_DAERAH "+ 
                        	" FROM TBLRUJDAERAH RK "+
                        	" WHERE RK.ID_NEGERI = "+idNegeri+
                        	" ORDER BY RK.ID_DAERAH ";
				
					setSQL(sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;				
				while (rs.next()) {
					h = new Hashtable();	        
			    	h.put("abbrev", Utils.isNull(rs.getString("ID_DAERAH")));
			    	h.put("negeri", Utils.isNull(rs.getString("NAMA_DAERAH")));		    	
			    	getAbbrev.addElement(h);
				}      
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
			}finally{
				if(db != null)db.close();
			}	
			return getAbbrev;	
		}

		public String generateXMLDaerah(String nama_laporan) {
			String xml="<chart caption='"+nama_laporan+"' subcaption='' xAxisName='Daerah' yAxisName='Jumlah Permohonan' numberPrefix='' showLegend='1'>";
			Db db = null;
			try{
				db =  new Db();
				Connection conn = db.getConnection();
				Statement stat = conn.createStatement();
				ResultSet rs =stat.executeQuery(getSQL());
				while(rs.next()){
					xml =xml+"<set label='"+rs.getString("NAMA_DAERAH")+"' value='"+rs.getString("y")+"' />";
				}
				xml =xml+"</chart>";
			} catch (Exception er) {
				myLogger.error(er);
			} finally {
				if (db!=null) db.close();
			}

			return xml;
		}	
	
	
}
