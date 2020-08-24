package ekptg.helpers;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;

import org.apache.log4j.Logger;


public class File implements Serializable  {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7670816347687837477L;
	public static final String SEQ_TABLE = "TBLRUJSEQFAIL";
	static Logger myLog = Logger.getLogger(ekptg.helpers.File.class);

	public static void main (String args[], HttpSession session) {
		try {
			// Sample
//			System.out.println("SeqNo1:"+File.getSeqNoByYearMonth(session, 5,1,2008,7));
//			System.out.println("SeqNo2:"+File.getSeqNoByYearMonth(session, 5,1,2009,8));
//			System.out.println("SeqNo2:"+File.getSeqNoByYearMonth(session, 5,1,2008,6));			
			/*System.out.println("Jilid SeqNo2:"+File.getSeqNoJilid(5,1,1,1));
			System.out.println("Subjaket SeqNo2:"+File.getSeqNoSubjaket(5,1,1,1));
			System.out.println("SeqNo3:"+File.getSeqNo(5,1,1,1));
			*/
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	/** NO_TURUTAN_ADUAN */
	public static synchronized int getSeqNoAduanOnline(int id_jenisaduan) throws DbException {
		return getSeqNoAduan(id_jenisaduan);
	}	
	/** NO_TURUTAN_FPX */
	public static synchronized int getSeqNoFPXOnline(int id_Jenisbayaran) throws DbException {
		return getSeqNoFPX(id_Jenisbayaran);
	}
	/** NO_TURUTAN_INTERNET_BANKING  */
	public static synchronized int getSeqNoInternetBankOnline(int id_Jenisbayaran) throws DbException {
		return getSeqNoInternetBank(id_Jenisbayaran);
	}
	/**   NO_TURUTAN_SUBJAKET */	
//	public static synchronized int getSeqNoSubjaket(HttpSession session, int id_seksyen,int id_urusan,int id_kementerian,int id_negeri) throws DbException {
//		return getSeqNo(session, id_seksyen, id_urusan, id_kementerian, id_negeri,0,false,true);
//	}
	//00
	public static synchronized int getSeqNoSubjaket( int id_seksyen,int id_urusan,int id_kementerian,int id_negeri) throws DbException {
		return getSeqNo(id_seksyen, id_urusan, id_kementerian, id_negeri,0,false,true);
	}
	
//	public static synchronized int getSeqNoSubjaket(HttpSession session, int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,
//			   int id_daerah) throws DbException {
//		return getSeqNo(session, id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,false,true);
//	}
	//00
	public static synchronized int getSeqNoSubjaket(int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,
			   int id_daerah) throws DbException {
		return getSeqNo(id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,false,true);
	}	
	
	public static synchronized int getSeqNoSubjaket(int id_fail) throws DbException {
		return getSeqSubjaket(id_fail,true);
	}
	
	/**   NO_TURUTAN_JILID  */
//	public static synchronized int getSeqNoJilid(HttpSession session, int id_seksyen,int id_urusan,int id_kementerian,int id_negeri) throws DbException {
//		return getSeqNoJilid(session, id_seksyen,id_urusan,id_kementerian,id_negeri,0, true,false);
//	}
	
//	public static synchronized int getSeqNoJilid(HttpSession session, int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,
//			   int id_daerah) throws DbException {
//		return getSeqNo(session, id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,true,false);
//	}
	
	//00
	public static synchronized int getSeqNoJilid(int id_seksyen,int id_urusan,int id_kementerian
		,int id_negeri,int id_daerah) throws DbException {
		return getSeqNo(id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,true,false);
	}
	
	/**   NO_TURUTAN */	
	public static synchronized int getSeqNoFailPenguatkuasaan(HttpSession session, int id_seksyen,int id_urusan,int id_kementerian,int id_negeri, int tahun) throws DbException {
		return getSeqNoP(session, id_seksyen, id_urusan, id_kementerian, id_negeri, 0, false, false, tahun);
	}
	
//	public static synchronized int getSeqNoFailPenguatkuasaan(int id_seksyen,int id_urusan,int id_kementerian,int id_negeri, int tahun) throws DbException {
//		return getSeqNo(id_seksyen, id_urusan, id_kementerian, id_negeri, 0, false, false, tahun);
//	}
	
	public static synchronized int getSeqNoP(HttpSession session, int id_seksyen,int id_urusan) throws DbException {
		return getSeqNoP(session, id_seksyen, id_urusan, 0, 0,0,false,false);
	}
	//00 = 02
	public static synchronized int getSeqNo(int id_seksyen
		,int id_urusan) throws DbException {
		return getSeqNo(id_seksyen, id_urusan, 0, 0,0,false,false);
	}
	
	public static synchronized int getSeqNoP(HttpSession session, int id_seksyen,int id_urusan,int id_kementerian,int id_negeri) throws DbException {
		return getSeqNoP(session, id_seksyen, id_urusan, id_kementerian, id_negeri,0,false,false);
	}
	// 00 - 04
	public static synchronized int getSeqNo(int id_seksyen
		,int id_urusan
		,int id_kementerian
		,int id_negeri) throws DbException {
		return getSeqNo(id_seksyen, id_urusan, id_kementerian, id_negeri,0,false,false);
	}
	public static synchronized int getSeqNoP(HttpSession session, int id_seksyen,int id_urusan,int id_kementerian
		,int id_negeri,int id_daerah) throws DbException {
		return getSeqNoP(session, id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,false,false);
	}
	// 00 - 05
	public static synchronized int getSeqNo(int id_seksyen
		,int id_urusan
		,int id_kementerian
		,int id_negeri
		,int id_daerah) throws DbException {
		return getSeqNo(id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,false,false);
	}
	
//	public static synchronized int getSeqNoByYear(HttpSession session, int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,int tahun) 
//		throws DbException {
//		return getSeqNo(session, id_seksyen, id_urusan, id_kementerian, id_negeri,0,false,false);
//	}
	//00 - 05, 25022012 - syah
	public static synchronized int getSeqNoByYearPPT(int id_seksyen
		,int id_urusan
		,int id_kementerian
		,int id_negeri
		,int tahun) throws DbException {
		return getSeqNo(id_seksyen, id_urusan, id_kementerian, id_negeri,tahun,false,false);
	}
	
//	public static synchronized int getSeqNoByYearMonth(HttpSession session, int id_seksyen,int id_urusan,int tahun,int bulan) 
//		throws DbException {
//		return getSeqNo(session, id_seksyen, id_urusan,0,0,0,false,false,tahun,bulan);
//	}
	
	public static synchronized int getSeqNoP(HttpSession session, int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,
		int id_daerah,boolean getNoJilid,boolean getNoSubjaket) throws DbException {
		return getSeqNoP(session, id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,getNoJilid,getNoSubjaket,0,0);
	}
	
	//00 -07
	public static synchronized int getSeqNo(int id_seksyen
		,int id_urusan
		,int id_kementerian
		,int id_negeri
		,int id_daerah
		,boolean getNoJilid
		,boolean getNoSubjaket) throws DbException {
			return getSeqNo(id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,getNoJilid,getNoSubjaket,0,0);
		}
	
	public static synchronized int getSeqNoJilid(HttpSession session, int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,
		int id_daerah,boolean getNoJilid,boolean getNoSubjaket) throws DbException {
		return getSeqNoJilid(session, id_seksyen,id_urusan,id_kementerian,id_negeri,id_daerah,getNoJilid,getNoSubjaket,0);
	}
	
	public static synchronized int getSeqNoP(HttpSession session, int id_seksyen,int id_urusan,int id_kementerian
		,int id_negeri,int id_daerah
		,boolean getNoJilid,boolean getNoSubjaket,int tahun) throws DbException  {
		return getSeqNoP(session, id_seksyen,id_urusan,id_kementerian,id_negeri,id_daerah,getNoJilid,getNoSubjaket,tahun,0);

	}	
	//00 - 08
	public static synchronized int getSeqNo(int id_seksyen
		,int id_urusan
		,int id_kementerian
		,int id_negeri
		,int id_daerah
		,boolean getNoJilid
		,boolean getNoSubjaket
		,int tahun) throws DbException  {
		return getSeqNo(id_seksyen,id_urusan,id_kementerian,id_negeri,id_daerah,getNoJilid,getNoSubjaket,tahun,0);

	}

	public static synchronized int getSeqNoP(HttpSession session, int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,
		int id_daerah,boolean getNoJilid,boolean getNoSubjaket,int tahun,int bulan) throws DbException  {
		return getSeqNoP(session, new Db(),id_seksyen,id_urusan,id_kementerian,id_negeri,id_daerah,getNoJilid,getNoSubjaket,tahun,bulan);

	}

	//00 - 09
	public static synchronized int getSeqNo(int id_seksyen
		,int id_urusan
		,int id_kementerian
		,int id_negeri
		,int id_daerah
		,boolean getNoJilid
		,boolean getNoSubjaket
		,int tahun
		,int bulan) throws DbException  {
		return getSeqNo(new Db(),id_seksyen,id_urusan,id_kementerian,id_negeri,id_daerah,getNoJilid,getNoSubjaket,tahun,bulan);

	}
	
	public static synchronized int getSeqNoP(HttpSession session, Db db,int id_seksyen,int id_urusan,int id_kementerian
		,int id_negeri,int id_daerah
		,boolean getNoJilid,boolean getNoSubjaket,int tahun,int bulan) 
		throws DbException  {		
		myLog.debug("File SEQNO:"+id_seksyen+"-"+id_urusan);
		
		//Db db = null;
		//Connection conn = null;
		File f = null;
		StringBuffer sb = new StringBuffer();
		int seqno=0;
		try {
//			 db = new Db();
//		     conn = db.getConnection();
//		     conn.setAutoCommit(false);
		     
       	  	 f = new File();
			 boolean found = false;
			 
			 sb.append("SELECT NO_TURUTAN,NO_TURUTAN_JILID,NO_TURUTAN_SUBJAKET FROM "+SEQ_TABLE+" WHERE ");
			 sb.append("id_seksyen=" +id_seksyen);
			 sb.append(" AND id_urusan=" +id_urusan);
			 sb.append(" AND id_kementerian=" +id_kementerian);
			 sb.append(" AND id_negeri=" +id_negeri);
			 sb.append(" AND id_daerah=" +id_daerah);
			 if (tahun > 0) sb.append(" AND tahun=" +tahun);
			 if (bulan > 0) sb.append(" AND bulan=" +bulan);
			 
			  //myLogger.debug("");
 	          ResultSet rs = db.getStatement().executeQuery(sb.toString()); 
	          
	          if (rs.next()) found = true;
	          if (found) {
	        	  f.increaseSeq(session, db,id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,getNoJilid,getNoSubjaket,bulan,tahun);     
	          } else {
	        	  f.addNew(db,id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,tahun,bulan);	        	  
	          }
	          ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
	          if ( rs2.next() ) {
	        	  if (getNoJilid) {
	        		  seqno = rs2.getInt("NO_TURUTAN_JILID");
	        	  }else if (getNoSubjaket) {
	        		  seqno = rs2.getInt("NO_TURUTAN_SUBJAKET");
	        	  }
	        	  else {
	        		  seqno = rs2.getInt("NO_TURUTAN");
	        	  }
	          }
	          //conn.commit();
			
		}  catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		}
//		catch (Exception ex) {
//		      try {
//		          conn.rollback(); } catch (SQLException localSQLException1) {
//		        }
//			throw new DbException(ex.getMessage() + ": " + sb.toString());
//		} 
//		finally {
//	      if (db != null) db.close();
//	    }
		
		return seqno;
	
	}
	
	//00 - 10
	public static synchronized int getSeqNo(Db db
		,int id_seksyen
		,int id_urusan
		,int id_kementerian
		,int id_negeri
		,int id_daerah
		,boolean getNoJilid
		,boolean getNoSubjaket
		,int tahun
		,int bulan) throws DbException  {		
		//myLog.debug("File SEQNO:"+id_seksyen+"-"+id_urusan);			
		myLog.debug("File SEQNO:"+id_seksyen);			
		myLog.debug("File SEQNO 2:"+id_urusan);			
		myLog.debug("File SEQNO 3:"+id_kementerian);			
		myLog.debug("File SEQNO 4:"+id_negeri);			
		myLog.debug("File SEQNO 5:"+id_daerah);	
		myLog.debug("File SEQNO 6:"+getNoJilid);			
		myLog.debug("File SEQNO 7:"+getNoSubjaket);			
		myLog.debug("File SEQNO 8:"+tahun);	
		myLog.debug("File SEQNO 9:"+bulan);	
		//Db db = null;
			//Connection conn = null;
			File f = null;
			StringBuffer sb = new StringBuffer();
			int seqno=0;
			try {
//				 db = new Db();
//			     conn = db.getConnection();
//			     conn.setAutoCommit(false);
			     
	       	  	 f = new File();
				 boolean found = false;
				 
				 sb.append("SELECT NO_TURUTAN,NO_TURUTAN_JILID,NO_TURUTAN_SUBJAKET FROM "+SEQ_TABLE+" WHERE ");
				 sb.append("id_seksyen=" +id_seksyen);
				 sb.append(" AND id_urusan=" +id_urusan);
				 sb.append(" AND id_kementerian=" +id_kementerian);
				 sb.append(" AND id_negeri=" +id_negeri);
				 sb.append(" AND id_daerah=" +id_daerah);
				 if (tahun > 0) sb.append(" AND tahun=" +tahun);
				 if (bulan > 0) sb.append(" AND bulan=" +bulan);
				 
				  myLog.info("found="+sb.toString());
	 	          ResultSet rs = db.getStatement().executeQuery(sb.toString()); 
		          
		          if (rs.next()) found = true;
		          if (found) {
		        	  f.increaseSeq(db
		        			  ,id_seksyen
		        			  ,id_urusan
		        			  ,id_kementerian
		        			  ,id_negeri
		        			  ,id_daerah
		        			  ,getNoJilid
		        			  ,getNoSubjaket
		        			  ,bulan
		        			  ,tahun);     
		          } else {
		        	  f.addNew(db,id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,tahun,bulan);	        	  
		          }
		          ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
		          if ( rs2.next() ) {
		        	  if (getNoJilid) {
		        		  seqno = rs2.getInt("NO_TURUTAN_JILID");
		        	  }else if (getNoSubjaket) {
		        		  seqno = rs2.getInt("NO_TURUTAN_SUBJAKET");
		        	  }
		        	  else {
		        		  seqno = rs2.getInt("NO_TURUTAN");
		        	  }
		          }
		          //conn.commit();
				
			}  catch (Exception ex) {
				throw new DbException(ex.getMessage() + ": " + sb.toString());
			}
//			catch (Exception ex) {
//			      try {
//			          conn.rollback(); } catch (SQLException localSQLException1) {
//			        }
//				throw new DbException(ex.getMessage() + ": " + sb.toString());
//			} 
//			finally {
//		      if (db != null) db.close();
//		    }
			myLog.debug("seqno="+seqno);			
			
			return seqno;
		
		}
	
	public static synchronized int getSeqNoPPK(HttpSession session, int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,
		int id_daerah,boolean getNoJilid,boolean getNoSubjaket,int tahun) throws DbException  {
		return getSeqNoPPK(session,id_seksyen,id_urusan,id_kementerian,id_negeri,id_daerah,getNoJilid,getNoSubjaket,tahun,0);

	}
	
	public static synchronized int getSeqNoPPK(HttpSession session,int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,
	   int id_daerah,boolean getNoJilid,boolean getNoSubjaket,int tahun,int bulan) throws DbException  {
		return getSeqNoPPK(session,new Db(),id_seksyen,id_urusan,id_kementerian,id_negeri,id_daerah,getNoJilid,getNoSubjaket,tahun,bulan);

	}
	
	//razman add	
	public static synchronized int getSeqNoPPK(HttpSession session,Db db,int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,
		int id_daerah,boolean getNoJilid,boolean getNoSubjaket,int tahun,int bulan ) 
		throws DbException  {
		myLog.debug("File getSeqNoPPK SEQNO:"+id_seksyen+"-"+id_urusan);
			
			//Db db = null;
			//Connection conn = null;
			File f = null;
			StringBuffer sb = new StringBuffer();
			int seqno=0;
			try {
			//db = new Db();
			//conn = db.getConnection();
			//conn.setAutoCommit(false);
			
			f = new File();
			boolean found = false;
			
			sb.append("SELECT NO_TURUTAN,NO_TURUTAN_JILID,NO_TURUTAN_SUBJAKET FROM "+SEQ_TABLE+" WHERE ");
			sb.append("id_seksyen=" +id_seksyen);
			sb.append(" AND id_urusan=" +id_urusan);
			sb.append(" AND id_kementerian=" +id_kementerian);
			sb.append(" AND id_negeri=" +id_negeri);
			sb.append(" AND id_daerah=" +id_daerah);
			if (tahun > 0) sb.append(" AND tahun=" +tahun);
			if (bulan > 0) sb.append(" AND bulan=" +bulan);
			
			myLog.info(">>>>>>>>>> getSeqNoPPK :: "+sb.toString());
			ResultSet rs = db.getStatement().executeQuery(sb.toString()); 
			
			if (rs.next()) found = true;
			if (found) {
			f.increaseSeqPPK(session, db,id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,getNoJilid,getNoSubjaket,bulan,tahun);     
			} else {
			f.addNew(db,id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,tahun,bulan);	        	  
			}
			ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
			if ( rs2.next() ) {
			if (getNoJilid) {
				  seqno = rs2.getInt("NO_TURUTAN_JILID");
			}else if (getNoSubjaket) {
				  seqno = rs2.getInt("NO_TURUTAN_SUBJAKET");
			}
			else {
				  seqno = rs2.getInt("NO_TURUTAN");
			}
			}
			
			}  catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
			}
			
			return seqno;
	
	}

	public static synchronized int getSeqNoJilid(HttpSession session, int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,
		int id_daerah,boolean getNoJilid,boolean getNoSubjaket,int tahun) throws DbException  {
		Db db = null;
		Connection conn = null;
		File f = null;
		StringBuffer sb = new StringBuffer();
		int seqno=0;
		try {
			 db = new Db();
		     conn = db.getConnection();
		     conn.setAutoCommit(false);
		     
       	  	 f = new File();
			 boolean found = false;
			 
			 sb.append("SELECT NO_TURUTAN,NO_TURUTAN_JILID,NO_TURUTAN_SUBJAKET FROM "+SEQ_TABLE+" WHERE ");
			 sb.append("id_seksyen=" +id_seksyen);
			 sb.append("AND id_urusan=" +id_urusan);
			 sb.append("AND id_kementerian=" +id_kementerian);
			 sb.append("AND id_negeri=" +id_negeri);
			 sb.append("AND id_daerah=" +id_daerah);
			 if (tahun > 0) sb.append("AND id_daerah=" +id_daerah);
			 System.out.println("sb.toString() : " + sb.toString());
 	          ResultSet rs = db.getStatement().executeQuery(sb.toString()); 
	          
	          if (rs.next()) found = true;
	          if (found) {
	        	  if (rs.getInt("NO_TURUTAN_JILID") != 0){
	        		  f.increaseSeq(session, db,id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,getNoJilid,getNoSubjaket,0,0);
	        	  }else {
	        		  f.addNewSeqJilid(db,id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,getNoJilid,getNoSubjaket);	        	  
	        	  }
	          }else{
        		  f.addNewSeqJilid(db,id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,getNoJilid,getNoSubjaket);	        	  	        	  
	          }
	          ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
	          if ( rs2.next() ) {
	        	  if (getNoJilid) {
	        		  seqno = rs2.getInt("NO_TURUTAN_JILID");
	        	  }else if (getNoSubjaket) {
	        		  seqno = rs2.getInt("NO_TURUTAN_SUBJAKET");
	        	  }
	        	  else {
	        		  seqno = rs2.getInt("NO_TURUTAN");
	        	  }
	          }
	          conn.commit();
			
		} catch (Exception ex) {
		      try {
		          conn.rollback(); } catch (SQLException localSQLException1) {
		        }
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} 
		finally {
	      if (db != null) db.close();
	    }
		
		return seqno;
	}
	
	public static synchronized int getSeqSubjaket(int id_fail,boolean getNoSubjaket ) throws DbException  {
		Db db = null;
		Connection conn = null;
		File f = null;
		StringBuffer sb = new StringBuffer();
		int seqno=0;
		try {
			 db = new Db();
		     conn = db.getConnection();
		     conn.setAutoCommit(false);
		     
       	  	 f = new File();
			 boolean found = false;
			 
			 sb.append("SELECT NO_TURUTAN_SUBJAKET FROM TBLRUJSEQJILIDSUBJAKET WHERE ");
			 sb.append("ID_FAIL = " +id_fail);
			 
			 ResultSet rs = db.getStatement().executeQuery(sb.toString()); 
	          
	          if (rs.next()) found = true;
	          if (found) {
	        	  f.increaseSeqSubjaket(id_fail,getNoSubjaket);     
	          } else {
	        	  f.addNewSeqSubjaket(id_fail);	        	  
	          }
	          ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
	          if ( rs2.next() ) {
	        	  if (getNoSubjaket) {
	        		  seqno = rs2.getInt("NO_TURUTAN_SUBJAKET");
	        	  }
	          }
	          conn.commit();
		}
		catch (Exception ex) {
		      try {
		          conn.rollback(); } catch (SQLException localSQLException1) {
		        }
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} 
		finally {
	      if (db != null) db.close();
	    }    
		return seqno;
	}
	
	public static synchronized int getSeqNoAduan(int id_jenisaduan) throws DbException  {
		Db db = null;
		Connection conn = null;
		File f = null;
		StringBuffer sb = new StringBuffer();
		int seqno=0;
		try {
		db = new Db();
		conn = db.getConnection();
		conn.setAutoCommit(false);
		
		f = new File();
		boolean found = false;
		
		sb.append("SELECT NO_TURUTAN FROM TBLRUJSEQADUAN WHERE ");
		sb.append("ID_JENISADUAN = " +id_jenisaduan);
		
		
		ResultSet rs = db.getStatement().executeQuery(sb.toString()); 
		
		if (rs.next()) found = true;
		if (found) {
			f.increaseSeqAduan(id_jenisaduan);     
		} else {
			f.addNewAduan(id_jenisaduan);	        	  
		}
		ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
        if ( rs2.next() ) {     	  
        	seqno = rs2.getInt("NO_TURUTAN");
      	  
        }
		conn.commit();
		
		} catch (Exception ex) {
		try {
		conn.rollback(); } catch (SQLException localSQLException1) {
		}
		throw new DbException(ex.getMessage() + ": " + sb.toString());
		} 
		finally {
		if (db != null) db.close();
		}
		
		return seqno;
		
	}
	
	public static synchronized int getSeqNoFPX(int id_Jenisbayaran) throws DbException  {

		Db db = null;
		Connection conn = null;
		File f = null;
		StringBuffer sb = new StringBuffer();
		int seqno=0;
		try {
		db = new Db();
		conn = db.getConnection();
		conn.setAutoCommit(false);
		
		f = new File();
		boolean found = false;
		
		sb.append("SELECT NO_TURUTAN_FPX FROM TBLRUJSEQPEMBAYARANONLINE WHERE ");
		sb.append("id_jenisbayaran=" +id_Jenisbayaran);
		
		
		ResultSet rs = db.getStatement().executeQuery(sb.toString()); 
		
		if (rs.next()) found = true;
		if (found) {
			f.increaseSeqFPX(id_Jenisbayaran);     
		} else {
			f.addNewFPX(id_Jenisbayaran);	        	  
		}
		ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
        if ( rs2.next() ) {
      	  
      		  seqno = rs2.getInt("NO_TURUTAN_FPX");
      	  
        }
		conn.commit();
		
		} catch (Exception ex) {
		try {
		conn.rollback(); } catch (SQLException localSQLException1) {
		}
		throw new DbException(ex.getMessage() + ": " + sb.toString());
		} 
		finally {
		if (db != null) db.close();
		}
		
		System.out.println("Seq No FPX:" +seqno);
		return seqno;
		}
	
	public static synchronized int getSeqNoInternetBank(int id_Jenisbayaran) throws DbException  {

		Db db = null;
		Connection conn = null;
		File f = null;
		StringBuffer sb = new StringBuffer();
		int seqno=0;
		try {
		db = new Db();
		conn = db.getConnection();
		conn.setAutoCommit(false);
		
		f = new File();
		boolean found = false;
		
		sb.append("SELECT NO_TURUTAN_INTERNETBANK FROM TBLRUJSEQPEMBAYARANONLINE WHERE ");
		sb.append("id_Jenisbayaran=" +id_Jenisbayaran);
		
		
		ResultSet rs = db.getStatement().executeQuery(sb.toString()); 
		
		if (rs.next()) found = true;
		if (found) {
			f.increaseSeqInternetBank(id_Jenisbayaran);     
		} else {
			f.addNewInternetBank(id_Jenisbayaran);	        	  
		}
		
		ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
        if ( rs2.next() ) {
      	  
      		  seqno = rs2.getInt("NO_TURUTAN_INTERNETBANK");
      	  
        }
		conn.commit();
		
		} catch (Exception ex) {
		try {
		conn.rollback(); } catch (SQLException localSQLException1) {
		}
		throw new DbException(ex.getMessage() + ": " + sb.toString());
		} 
		finally {
		if (db != null) db.close();
		}
		
		return seqno;
		}
	
	//Db parameter added
	public void addNew(Db db,int id_seksyen,int id_urusan,int id_kementerian,
		int id_negeri,int id_daerah,int tahun,int bulan) throws DbException {		
		//Db db = null;
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO "+SEQ_TABLE+" (id_seksyen,id_urusan,id_kementerian,id_negeri,id_daerah,");
		sb.append("no_turutan,no_turutan_jilid,no_turutan_subjaket,tahun,bulan) VALUES (");
		sb.append("'"+id_seksyen+"','"+id_urusan+"','"+id_kementerian+"','"+id_negeri+"','"+id_daerah+"',");
		sb.append("1,0,0,"+tahun+","+bulan+")"); //initial value
		
		try {
			//db = new Db();
			db.getStatement().executeUpdate(sb.toString());
		}catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		}
//		finally {
//		      if (db != null) db.close();
//		}
	}
	
	public void addNewSeqSubjaket(int id_fail) throws DbException {
		Db db = null;
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO TBLRUJSEQJILIDSUBJAKET (id_fail,no_turutan_subjaket,no_turutan_jilid)");
		sb.append(" VALUES (");
		sb.append("'"+id_fail+"',");
		sb.append("1,0)"); //initial value
		
		try {
			db = new Db();
			db.getStatement().executeUpdate(sb.toString());
		}catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		}
		finally {
		      if (db != null) db.close();
		}
	}
	
	public void addNewSeqJilid(Db db,int id_seksyen,int id_urusan,int id_kementerian,
		int id_negeri,int id_daerah,boolean getNoJilid,boolean getNoSubjaket ) throws DbException {
		//Db db = null;
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE "+SEQ_TABLE+"  SET "); 
		if (getNoJilid) {
			sb.append("no_turutan_jilid=no_turutan_jilid+2 ");
		} else if (getNoSubjaket) {
			sb.append("no_turutan_subjaket=no_turutan_subjaket+1 ");
		} else {
			sb.append("no_turutan=no_turutan+1 ");
		}
		sb.append(" WHERE ");
		sb.append("id_seksyen = '"+id_seksyen+"' AND ");
		sb.append("id_urusan = '"+id_urusan+"' AND ");
		sb.append("id_kementerian = '"+id_kementerian+"' AND ");
		sb.append("id_negeri = '"+id_negeri+"'");
		sb.append(" AND id_daerah = '"+id_daerah+"'");
		try {
			//db = new Db();
			try{
			db.getStatement().executeUpdate(sb.toString());
			} catch (SQLException x) {x.printStackTrace();}
		}catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		}
//		finally {
//		if (db != null) db.close();
//		}
	}
	
	public void addNewAduan(int id_jenisaduan) throws DbException {
		Db db = null;
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO TBLRUJSEQADUAN (ID_JENISADUAN,NO_TURUTAN)");
		sb.append(" VALUES (");
		sb.append("'"+id_jenisaduan+"'");
		sb.append(",1)"); //initial value
		
		try {
		db = new Db();
		db.getStatement().executeUpdate(sb.toString());
		}catch (Exception ex) {
		throw new DbException(ex.getMessage() + ": " + sb.toString());
		}
		finally {
		if (db != null) db.close();
		}
	}
	
	public void addNewFPX(int id_Jenisbayaran) throws DbException {
		Db db = null;
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO TBLRUJSEQPEMBAYARANONLINE (ID_JENISBAYARAN,NO_TURUTAN_FPX)");
		sb.append(" VALUES (");
		sb.append("'"+id_Jenisbayaran+"'");
		sb.append(",1)"); //initial value
		
		try {
		db = new Db();
		db.getStatement().executeUpdate(sb.toString());
		}catch (Exception ex) {
		throw new DbException(ex.getMessage() + ": " + sb.toString());
		}
		finally {
		if (db != null) db.close();
		}
	}
	
	public void addNewInternetBank(int id_Jenisbayaran) throws DbException {
		Db db = null;
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO TBLRUJSEQPEMBAYARANONLINE (ID_JENISBAYARAN,NO_TURUTAN_INTERNETBANK)");
		sb.append(" VALUES (");
		sb.append("'"+id_Jenisbayaran+"'");
		sb.append(",1)"); //initial value
		
		try {
		db = new Db();
		db.getStatement().executeUpdate(sb.toString());
		}catch (Exception ex) {
		throw new DbException(ex.getMessage() + ": " + sb.toString());
		}
		finally {
		if (db != null) db.close();
		}
	}
	
	public void increaseSeq(HttpSession session, Db db,int id_seksyen,int id_urusan,int id_kementerian
		,int id_negeri,int id_daerah
		,boolean getNoJilid,boolean getNoSubjaket,int tahun,int bulan) throws DbException  {
		//Db db = null;
	
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE "+SEQ_TABLE+"  SET "); 
		if (getNoJilid) {
			sb.append("no_turutan_jilid=no_turutan_jilid+1 ");
		} else if (getNoSubjaket) {
			sb.append("no_turutan_subjaket=no_turutan_subjaket+1 ");
		} else {
			sb.append("no_turutan=no_turutan+1 ");
		}
		sb.append(" WHERE ");
		sb.append("id_seksyen = '"+id_seksyen+"' AND ");
		sb.append("id_urusan = '"+id_urusan+"' AND ");
		sb.append("id_kementerian = '"+id_kementerian+"' AND ");
		sb.append("id_negeri = '"+id_negeri+"'");		
		sb.append(" AND id_daerah = '"+id_daerah+"'");
		try {
			//db = new Db();
			try{
				db.getStatement().executeUpdate(sb.toString());
				AuditTrail.logActivity(id_urusan+"","","2",null,session,"","increaseSeq CREATE NO FAIL INSIDE : ID_SEKSYEN = '"+id_seksyen+"';ID_URUSAN = '"+id_urusan+"';ID_KEMENTERIAN = '"+id_kementerian+"';ID_NEGERI = '"+id_negeri+"';ID_DAERAH = '"+id_daerah+"';TAHUN = '"+tahun+"' ");
			
			} catch (SQLException x) {x.printStackTrace();}
		
		}catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		}
//		finally {
//		if (db != null) db.close();
//		}
	}
	
	public void increaseSeq(Db db
		,int id_seksyen
		,int id_urusan
		,int id_kementerian
		,int id_negeri
		,int id_daerah
		,boolean getNoJilid
		,boolean getNoSubjaket
		,int tahun
		,int bulan) throws DbException  {
		//Db db = null;
		
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE "+SEQ_TABLE+"  SET "); 
		if (getNoJilid) {
			sb.append("no_turutan_jilid=no_turutan_jilid+1 ");
		} else if (getNoSubjaket) {
			sb.append("no_turutan_subjaket=no_turutan_subjaket+1 ");
		} else {
			sb.append("no_turutan=no_turutan+1 ");
		}
		sb.append(" WHERE ");
		sb.append("id_seksyen = '"+id_seksyen+"' AND ");
		sb.append("id_urusan = '"+id_urusan+"' AND ");
		sb.append("id_kementerian = '"+id_kementerian+"' AND ");
		sb.append("id_negeri = '"+id_negeri+"' AND ");		
		sb.append("id_daerah = '"+id_daerah+"'");
//		myLog.info("increaseSeq 1");
		try {
//			db = new Db();
//			myLog.info("increaseSeq 2");
		try{
			db.getStatement().executeUpdate(sb.toString());
			AuditTrail.logActivity(id_urusan+"","","2",null,null,"","increaseSeq CREATE NO FAIL INSIDE : ID_SEKSYEN = '"+id_seksyen+"';ID_URUSAN = '"+id_urusan+"';ID_KEMENTERIAN = '"+id_kementerian+"';ID_NEGERI = '"+id_negeri+"';ID_DAERAH = '"+id_daerah+"';TAHUN = '"+tahun+"' ");
				
		} catch (SQLException x) {x.printStackTrace();}
//			myLog.info("increaseSeq 3");
			
		}catch (Exception ex) {
			ex.printStackTrace();
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		}
//			finally {
//			if (db != null) db.close();
//			}
	}
		
	public void increaseSeqPPK(HttpSession session, Db db,int id_seksyen,int id_urusan,int id_kementerian,
		int id_negeri,int id_daerah,boolean getNoJilid,boolean getNoSubjaket,int bulan,int tahun) throws Exception  {
		//Db db = null;
		
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE "+SEQ_TABLE+"  SET "); 
		if (getNoJilid) {
			sb.append("no_turutan_jilid=no_turutan_jilid+1 ");
		} else if (getNoSubjaket) {
			sb.append("no_turutan_subjaket=no_turutan_subjaket+1 ");
		} else {
			sb.append("no_turutan=(SELECT NEW_TURUTAN FROM ( "+
					" SELECT M.NO_TURUTAN AS CURRENT_TURUTAN, M.NO_FAIL AS NO_FAIL_SEQ, CASE WHEN F.NO_FAIL IS NOT NULL THEN M.NO_TURUTAN + 1 ELSE M.NO_TURUTAN END AS NEW_TURUTAN "+
					" FROM TBLPFDFAIL F, (SELECT NO_TURUTAN, 'JKPTG/PK/' ||  N.KOD_NEGERI || '/' || D.KOD_DAERAH || '/' || TRIM(TO_CHAR(RS.NO_TURUTAN,'0000')) || '/' || "+tahun+" AS NO_FAIL "+
					" FROM TBLRUJSEQFAIL RS, TBLRUJNEGERI N, TBLRUJDAERAH D WHERE RS.ID_NEGERI = N.ID_NEGERI(+) AND RS.ID_DAERAH = D.ID_DAERAH(+) AND RS.ID_KEMENTERIAN = 0 "+
					" AND RS.ID_URUSAN = 382 AND RS.ID_SEKSYEN = 2 AND RS.BULAN = 0 AND RS.ID_DAERAH = "+id_daerah+" AND RS.ID_NEGERI = "+id_negeri+" AND RS.TAHUN = "+tahun+") M WHERE M.NO_FAIL = F.NO_FAIL(+) "+
					" ) WHERE ROWNUM = 1) ");
		}
		sb.append(" WHERE ");
		sb.append("id_seksyen = '"+id_seksyen+"' AND ");
		sb.append("id_urusan = '"+id_urusan+"' AND ");
		sb.append("id_kementerian = '"+id_kementerian+"' AND ");
		sb.append("id_negeri = '"+id_negeri+"'");
		sb.append(" AND tahun=" +tahun);
		sb.append(" AND id_daerah = '"+id_daerah+"'");
		try {
			//db = new Db();
			try{
			db.getStatement().executeUpdate(sb.toString());
						
			//at.logActivity("","2",null,null,"","increaseSeqPPK CREATE NO FAIL INSIDE : ID_SEKSYEN = '"+id_seksyen+"';ID_URUSAN = '"+id_urusan+"';ID_KEMENTERIAN = '"+id_kementerian+"';ID_NEGERI = '"+id_negeri+"';ID_DAERAH = '"+id_daerah+"';TAHUN = '"+tahun+"' ",db);
			AuditTrail.logActivity(id_urusan+"","","2",null,session,"","increaseSeqPPK CREATE NO FAIL INSIDE : ID_SEKSYEN = '"+id_seksyen+"';ID_URUSAN = '"+id_urusan+"';ID_KEMENTERIAN = '"+id_kementerian+"';ID_NEGERI = '"+id_negeri+"';ID_DAERAH = '"+id_daerah+"';TAHUN = '"+tahun+"' ",db); 
			
			} catch (SQLException x) {x.printStackTrace();}
		}catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		}
		
		
		
//		finally {
//		if (db != null) db.close();
//		}
	}
	
	public void increaseSeqJilid(Db db,int id_fail,boolean getNoJilid) throws DbException  {	
		//Db db = null;
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE TBLRUJSEQJILIDSUBJAKET  SET "); 
		if (getNoJilid) {
			sb.append("no_turutan_jilid=no_turutan_jilid+1 ");
		} 
		sb.append(" WHERE ");
		sb.append("id_fail = '"+id_fail+"'");
		try {
			//db = new Db();
			try{
			db.getStatement().executeUpdate(sb.toString());
			} catch (SQLException x) {x.printStackTrace();}
		}catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		}
//		finally {
//		if (db != null) db.close();
//		}
	}
	
	public void increaseSeqSubjaket(int id_fail,boolean getNoSubjaket) throws DbException  {
		Db db = null;
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE TBLRUJSEQJILIDSUBJAKET  SET "); 
		if (getNoSubjaket) {
			sb.append("no_turutan_subjaket=no_turutan_subjaket+1 ");
		} 
		sb.append(" WHERE ");
		sb.append("id_fail = '"+id_fail+"'");
		try {
			db = new Db();
			try{
			db.getStatement().executeUpdate(sb.toString());
			} catch (SQLException x) {x.printStackTrace();}
		}catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		}
		finally {
		if (db != null) db.close();
		}
	}
	
	public void increaseSeqAduan(int id_jenisaduan) throws DbException  {
		Db db = null;
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE TBLRUJSEQADUAN  SET "); 
		sb.append("no_turutan = no_turutan + 1 ");
		sb.append(" WHERE ");
		sb.append("id_jenisaduan = '"+id_jenisaduan+"'");
		
		try {
			db = new Db();
			try{
			db.getStatement().executeUpdate(sb.toString());
			} catch (SQLException x) {x.printStackTrace();}
		}catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		}
		finally {
		if (db != null) db.close();
		}
	}
	
	public void increaseSeqFPX(int id_jenisbayaran) throws DbException  {
		Db db = null;
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE TBLRUJSEQPEMBAYARANONLINE SET "); 
		sb.append("no_turutan_fpx =no_turutan_fpx + 1 ");
		sb.append(" WHERE ");
		sb.append("id_jenisbayaran = '"+id_jenisbayaran+"'");
		
		try {
			db = new Db();
			try{
			db.getStatement().executeUpdate(sb.toString());
			} catch (SQLException x) {x.printStackTrace();}
		}catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		}
		finally {
		if (db != null) db.close();
		}
		
	}
	
	public void increaseSeqInternetBank(int id_jenisbayaran) throws DbException  {
		Db db = null;
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE TBLRUJSEQPEMBAYARANONLINE  SET "); 
		sb.append("no_turutan_internetbank =no_turutan_internetbank + 1 ");
		sb.append(" WHERE ");
		sb.append("id_jenisbayaran = '"+id_jenisbayaran+"'");
		
		try {
			db = new Db();
			try{
			db.getStatement().executeUpdate(sb.toString());
			} catch (SQLException x) {x.printStackTrace();}
		}catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		}
		finally {
		if (db != null) db.close();
		}
		
	}
	
	public static synchronized String getIDFailByPermohonan(String idPermohonan) throws DbException  {
		Db db = null;
		Connection conn = null;
		String noFail = "";
		StringBuffer sb = new StringBuffer();
		try {
			db = new Db();
			conn = db.getConnection();
					
			sb.append("SELECT F.ID_FAIL ");
			sb.append("FROM TBLPFDFAIL F, TBLPERMOHONAN P WHERE ");
			sb.append("P.ID_FAIL=F.ID_FAIL AND P.ID_PERMOHONAN = " +idPermohonan);	
//			myLog.info("getIDFailByPermohonan:sql="+sb.toString());
			ResultSet rs = db.getStatement().executeQuery(sb.toString()); 
			
			if ( rs.next() ) {
				noFail= rs.getString("ID_FAIL");   	  
	        }
			
		} catch (Exception ex) {
		try {
			conn.rollback(); 
		} catch (SQLException localSQLException1) {}
		throw new DbException(ex.getMessage() + ": " + sb.toString());
		}finally {
			if (db != null) db.close();
		}		
		return noFail;
		
	}
	
	
}
//20200824 20:36

