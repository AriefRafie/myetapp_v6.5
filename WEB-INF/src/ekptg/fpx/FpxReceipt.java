package ekptg.fpx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.fpx.entity.FpxResit;

public class FpxReceipt {
	public synchronized String getReceiptNo(String year){
		int currentNo = 0;
		FpxResit resit = getResit(year);
		if(resit == null){
			save(year);
			
		}else{
			currentNo = resit.getCurrentNo();
		}
		int newNo = currentNo + 1;
		update(year, newNo);
		year = year.substring(2, year.length());
		String formatted = String.format("%05d", newNo);
		String prefix = "P020101"+year+""+formatted;
		System.out.println("new receipt no "+prefix);
		return prefix;
	}
	
	private void save(String year){

		Connection conn = null;
	    Db db = null;
	    String sql = "";
	    
	    try{
	    	db = new Db();
	    	conn = db.getConnection();
			conn.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	FpxResit resit = new FpxResit();
	    	resit.setYear(year);
	    	resit.setCurrentNo(0);
	    	resit.setId(Integer.parseInt(year));
	    	SQLRenderer r = new SQLRenderer();	
	    	r.add("ID_FPXRESIT",resit.getId());
	    	r.add("YEAR",resit.getYear());
	    	r.add("CURRENT_NO",resit.getCurrentNo());
	    	sql = r.getSQLInsert("TBLFPXRESIT");
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
	private void update(String year,int no){
		Connection conn = null;
	    Db db = null;
	    String sql = "";
	    
	    try{
	    	db = new Db();
	    	conn = db.getConnection();
			conn.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sql="UPDATE TBLFPXRESIT SET CURRENT_NO="+no+" WHERE ID_FPXRESIT="+year;
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
	private FpxResit getResit(String year){
		Connection conn = null;
	    Db db = null;
	    String sql = "";
	    FpxResit resit = null;
	    try{
	    	db = new Db();
	    	conn = db.getConnection();
	    	Statement stmt = db.getStatement();
	    	sql ="SELECT ID_FPXRESIT,YEAR,CURRENT_NO FROM TBLFPXRESIT WHERE ID_FPXRESIT="+year;
	    	
	    	ResultSet rs = stmt.executeQuery(sql);
	    	if(rs.next()){
	    		resit = new FpxResit();
	    		resit.setId(rs.getInt("ID_FPXRESIT"));
	    		resit.setYear(rs.getString("YEAR"));
	    		resit.setCurrentNo(rs.getInt("CURRENT_NO"));
	    	}
	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	db.close();
	    }
	    return resit;

	}
	public static void main(String[] args) {
        int number = 1;
        
        //
        // String format below will add leading zeros (the %0 syntax) 
        // to the number above. The length of the formatted string will 
        // be 7 characters.
        //
        String formatted = String.format("%05d", number);
        
        System.out.println("Number with leading zeros: " + formatted);
    }
}
