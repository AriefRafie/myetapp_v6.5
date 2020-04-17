package ekptg.helpers;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import lebah.db.Db;
import lebah.db.DbException;

import org.apache.log4j.Logger;


public class File_PFD implements Serializable  {
	
	public static final String SEQ_TABLE = "TBLRUJSEQFAILEXTENDED";
	static Logger myLogger = Logger.getLogger(File_PFD.class);

	public static void main (String args[]) {
		try {
			// Sample
			System.out.println("SeqNo1:"+File_PFD.getSeqNoByYearMonth(5,1,2008,7,1,1));
			System.out.println("SeqNo2:"+File_PFD.getSeqNoByYearMonth(5,1,2009,8,1,1));
			System.out.println("SeqNo2:"+File_PFD.getSeqNoByYearMonth(5,1,2008,6,1,1));
			
			/*System.out.println("Jilid SeqNo2:"+File.getSeqNoJilid(5,1,1,1));
			System.out.println("Subjaket SeqNo2:"+File.getSeqNoSubjaket(5,1,1,1));
			System.out.println("SeqNo3:"+File.getSeqNo(5,1,1,1));
			*/
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/******** NO_TURUTAN_ADUAN *****************/
	public static synchronized int getSeqNoAduanOnline(int id_jenisaduan) throws DbException {
		return getSeqNoAduan(id_jenisaduan);
	}
	
	/******** NO_TURUTAN_FPX********************/
	public static synchronized int getSeqNoFPXOnline(int id_Jenisbayaran) throws DbException {
		return getSeqNoFPX(id_Jenisbayaran);
	}
	/******** NO_TURUTAN_INTERNET_BANKING ********************/
	public static synchronized int getSeqNoInternetBankOnline(int id_Jenisbayaran) throws DbException {
		return getSeqNoInternetBank(id_Jenisbayaran);
	}
	/********   NO_TURUTAN_SUBJAKET   **********/
	
	public static synchronized int getSeqNoSubjaket(int id_seksyen,int id_urusan,int id_kementerian,int id_negeri, int suburusan, int subsuburusan) throws DbException {
		return getSeqNo(id_seksyen, id_urusan, id_kementerian, id_negeri,0,false,true, suburusan, subsuburusan);
	}
	
	public static synchronized int getSeqNoSubjaket(int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,
			   int id_daerah,int suburusan, int subsuburusan) throws DbException {
		return getSeqNo(id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,false,true, suburusan,subsuburusan);
	}
	
	public static synchronized int getSeqNoSubjaket(int id_fail) throws DbException {
		return getSeqSubjaket(id_fail,true);
	}
	
	/********   NO_TURUTAN_JILID   **********/
	
	public static synchronized int getSeqNoJilid(int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,int suburusan, int subsuburusan) throws DbException {
		return getSeqNoJilid(id_seksyen,id_urusan,id_kementerian,id_negeri,0, true,false, suburusan, subsuburusan);
	}
	
	public static synchronized int getSeqNoJilid(int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,
			   int id_daerah, int suburusan, int subsuburusan) throws DbException {
		return getSeqNo(id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,true,false,suburusan, subsuburusan);
	}
	
	/********   NO_TURUTAN   **********/
	
	public static synchronized int getSeqNo(int id_seksyen,int id_urusan,int suburusan, int subsuburusan) throws DbException {
		return getSeqNo(id_seksyen, id_urusan, 0, 0,0,false,false, suburusan, subsuburusan);
	}
	
	public static synchronized int getSeqNo(int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,int suburusan, int subsuburusan) throws DbException {
		return getSeqNo(id_seksyen, id_urusan, id_kementerian, id_negeri,0,false,false, suburusan, subsuburusan);
	}
	
	public static synchronized int getSeqNo(int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,
							   int id_daerah,int suburusan, int subsuburusan) throws DbException {
		return getSeqNo(id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,false,false, suburusan, subsuburusan);
	}
	
	public static synchronized int getSeqNoByYear(int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,int tahun,int suburusan, int subsuburusan) 
	throws DbException {
		return getSeqNo(id_seksyen, id_urusan, id_kementerian, id_negeri,0,false,false, suburusan, subsuburusan);
	}

	public static synchronized int getSeqNoByYearMonth(int id_seksyen,int id_urusan,
			int tahun,int bulan,int suburusan, int subsuburusan) 
	throws DbException {
		return getSeqNo(id_seksyen, id_urusan,0,0,0,false,false,tahun,bulan, suburusan, subsuburusan);
	}
	
	public static synchronized int getSeqNo(int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,
			   int id_daerah,boolean getNoJilid,boolean getNoSubjaket,int suburusan, int subsuburusan) 
	throws DbException {
		return getSeqNo(id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,getNoJilid,getNoSubjaket,0,0, suburusan, subsuburusan);
	}	
	public static synchronized int getSeqNoJilid(int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,
			   int id_daerah,boolean getNoJilid,boolean getNoSubjaket,int suburusan, int subsuburusan) 
	throws DbException {
		return getSeqNoJilid(id_seksyen,id_urusan,id_kementerian,id_negeri,id_daerah,getNoJilid,getNoSubjaket,0, suburusan,subsuburusan);
	}
	
	public static synchronized int getSeqNo(int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,
			   int id_daerah,boolean getNoJilid,boolean getNoSubjaket,int tahun,int suburusan, int subsuburusan) throws DbException  {
		return getSeqNo(id_seksyen,id_urusan,id_kementerian,id_negeri,id_daerah,getNoJilid,getNoSubjaket,tahun,0, suburusan, subsuburusan);

	}
	
	public static synchronized int getSeqNo(int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,
			   int id_daerah,boolean getNoJilid,boolean getNoSubjaket,int tahun,int bulan, int suburusan, int subsuburusan) throws DbException  {
		return getSeqNo(new Db(),id_seksyen,id_urusan,id_kementerian,id_negeri,id_daerah,getNoJilid,getNoSubjaket,tahun,bulan, suburusan, subsuburusan);

	}
	
	public static synchronized int getSeqNo(Db db,int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,
							   int id_daerah,boolean getNoJilid,boolean getNoSubjaket,int tahun,int bulan, int id_suburusan,int id_subsuburusan   ) 
	throws DbException  {
		
		myLogger.debug("File SEQNO:"+id_seksyen+"-"+id_urusan);
		
		//Db db = null;
		//Connection conn = null;
		File_PFD f = null;
		StringBuffer sb = new StringBuffer();
		int seqno=0;
		try {
//			 db = new Db();
//		     conn = db.getConnection();
//		     conn.setAutoCommit(false);
		     
       	  	 f = new File_PFD();
			 boolean found = false;
			 
			 sb.append("SELECT NO_TURUTAN,NO_TURUTAN_JILID,NO_TURUTAN_SUBJAKET FROM "+SEQ_TABLE+" WHERE ");
			 sb.append("id_seksyen=" +id_seksyen);
			 sb.append(" AND id_urusan=" +id_urusan);
			 sb.append(" AND id_suburusan=" +id_suburusan);
			 sb.append(" AND id_subsuburusan=" +id_subsuburusan);
			 sb.append(" AND id_kementerian=" +id_kementerian);
			 sb.append(" AND id_negeri=" +id_negeri);
			 sb.append(" AND id_daerah=" +id_daerah);
			 if (tahun > 0) sb.append(" AND tahun=" +tahun);
			 if (bulan > 0) sb.append(" AND bulan=" +bulan);
			 
			  myLogger.debug("");
 	          ResultSet rs = db.getStatement().executeQuery(sb.toString()); 
	          
	          if (rs.next()) found = true;
	          if (found) {
	        	  f.increaseSeq(db,id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,getNoJilid,getNoSubjaket,bulan,tahun,id_suburusan,id_subsuburusan);     
	          } else {
	        	  f.addNew(db,id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,tahun,bulan,id_suburusan,id_subsuburusan);	        	  
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

	public static synchronized int getSeqNoJilid(int id_seksyen,int id_urusan,int id_kementerian,int id_negeri,
			   int id_daerah,boolean getNoJilid,boolean getNoSubjaket,int tahun, int id_suburusan, int id_subsuburusan ) throws DbException  {
		Db db = null;
		Connection conn = null;
		File_PFD f = null;
		StringBuffer sb = new StringBuffer();
		int seqno=0;
		try {
			 db = new Db();
		     conn = db.getConnection();
		     conn.setAutoCommit(false);
		     
       	  	 f = new File_PFD();
			 boolean found = false;
			 
			 sb.append("SELECT NO_TURUTAN,NO_TURUTAN_JILID,NO_TURUTAN_SUBJAKET FROM "+SEQ_TABLE+" WHERE ");
			 sb.append("id_seksyen=" +id_seksyen);
			 sb.append("AND id_urusan=" +id_urusan);
			 sb.append("AND id_suburusan=" +id_suburusan);
			 sb.append("AND id_subsuburusan=" +id_subsuburusan);
			 sb.append("AND id_kementerian=" +id_kementerian);
			 sb.append("AND id_negeri=" +id_negeri);
			 sb.append("AND id_daerah=" +id_daerah);
			 if (tahun > 0) sb.append("AND id_daerah=" +id_daerah);
			 
 	          ResultSet rs = db.getStatement().executeQuery(sb.toString()); 
	          
	          if (rs.next()) found = true;
	          if (found) {
	        	  if (rs.getInt("NO_TURUTAN_JILID") != 0){
	        		  f.increaseSeq(db,id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,getNoJilid,getNoSubjaket,0,0,id_suburusan, id_subsuburusan);
	        	  }else {
	        		  f.addNewSeqJilid(db,id_seksyen, id_urusan, id_kementerian, id_negeri,id_daerah,getNoJilid,getNoSubjaket, id_suburusan,id_subsuburusan);	        	  
	        	  }
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
		File_PFD f = null;
		StringBuffer sb = new StringBuffer();
		int seqno=0;
		try {
			 db = new Db();
		     conn = db.getConnection();
		     conn.setAutoCommit(false);
		     
       	  	 f = new File_PFD();
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
		File_PFD f = null;
		StringBuffer sb = new StringBuffer();
		int seqno=0;
		try {
		db = new Db();
		conn = db.getConnection();
		conn.setAutoCommit(false);
		
		f = new File_PFD();
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
		File_PFD f = null;
		StringBuffer sb = new StringBuffer();
		int seqno=0;
		try {
		db = new Db();
		conn = db.getConnection();
		conn.setAutoCommit(false);
		
		f = new File_PFD();
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
		File_PFD f = null;
		StringBuffer sb = new StringBuffer();
		int seqno=0;
		try {
		db = new Db();
		conn = db.getConnection();
		conn.setAutoCommit(false);
		
		f = new File_PFD();
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
							   int id_negeri,int id_daerah,int tahun,int bulan,int id_suburusan,int id_subsuburusan) throws DbException {
		
		//Db db = null;
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO "+SEQ_TABLE+" (id_seksyen,id_urusan,id_kementerian,id_negeri,id_daerah,");
		sb.append("no_turutan,no_turutan_jilid,no_turutan_subjaket,tahun,bulan,id_suburusan,id_subsuburusan ) VALUES (");
		sb.append("'"+id_seksyen+"','"+id_urusan+"','"+id_kementerian+"','"+id_negeri+"','"+id_daerah+"',");
		sb.append("1,0,0,"+tahun+","+bulan+","+id_suburusan+","+id_subsuburusan+")"); //initial value
		
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
	public static void addNew2(Db db,int id_seksyen,int id_urusan,int id_kementerian,
			   int id_negeri,int id_daerah,int tahun,int bulan, int suburusan, int subsuburusan) throws DbException {

//Db db = null;
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO "+SEQ_TABLE+" (id_seksyen,id_urusan,id_kementerian,id_negeri,id_daerah,id_suburusan, id_subsuburusan,");
		sb.append("no_turutan,no_turutan_jilid,no_turutan_subjaket,tahun,bulan) VALUES (");
		sb.append("'"+id_seksyen+"','"+id_urusan+"','"+id_kementerian+"','"+id_negeri+"','"+id_daerah+"','"+suburusan+"','"+subsuburusan+"',");
		sb.append("0,0,0,"+tahun+","+bulan+")"); //initial value

		try {
			//db = new Db();
			db.getStatement().executeUpdate(sb.toString());
			}catch (Exception ex) {
				throw new DbException(ex.getMessage() + ": " + sb.toString());
			}
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
			   int id_negeri,int id_daerah,boolean getNoJilid,boolean getNoSubjaket, int id_suburusan,int id_subsuburusan ) throws DbException {
		
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
		sb.append("id_suburusan='" +id_suburusan+"' AND ");
		sb.append("id_subsuburusan='" +id_subsuburusan+"' AND ");
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
	
	public void increaseSeq(Db db,int id_seksyen,int id_urusan,int id_kementerian,
			   int id_negeri,int id_daerah,boolean getNoJilid,boolean getNoSubjaket,int tahun,int bulan, int id_suburusan,int id_subsuburusan) throws DbException  {

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
		 sb.append("id_suburusan= '" +id_suburusan+"' AND ");
		 sb.append("id_subsuburusan= '" +id_subsuburusan+"' AND ");
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


}
