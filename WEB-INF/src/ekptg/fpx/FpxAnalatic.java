package ekptg.fpx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import lebah.db.Db;
import ekptg.fpx.entity.DataFpx;

public class FpxAnalatic extends FpxBean  implements IFpxAnalatic {

	@Override
	public DataFpx doProcess(DataFpx fpx) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public FpxAnalaticData getTodayStatistic(){
		Connection conn = null;
	    Db db = null;
	    String sql = "";
	    FpxAnalaticData analatic = null;
	    try
	    {
	    	db = new Db();
	    	conn = db.getConnection();
	    	sql ="SELECT * FROM TBLDATAFPX WHERE TARIKH_BAYARAN >= TRUNC(SYSDATE)";
	    	Statement stmt = db.getStatement();
	    	ResultSet rs = stmt.executeQuery(sql);
	    	int totalFail = 0;
	    	int totalSuccess = 0;
	    	double collection = 0;
	    	while(rs.next()){
	    		if(rs.getString("STATUS_TRANSAKSI").equals("SUCCESSFUL")){
	    			totalSuccess = totalSuccess + 1;
	    			collection = collection + rs.getDouble("amaun_bayaran");
	    		}else{
	    			totalFail = totalFail + 1;
	    		}
	    	}
	    	
	    	analatic = new FpxAnalaticData();
	    	analatic.setTotalFail(totalFail);
	    	analatic.setTotalSuccess(totalSuccess);
	    	analatic.setTotalColletion(collection);
	    
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	db.close();
	    }
	    
	    return analatic;
	}

}
