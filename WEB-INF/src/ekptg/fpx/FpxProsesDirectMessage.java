package ekptg.fpx;

import java.sql.Connection;
import java.sql.Statement;

import lebah.db.Db;
import ekptg.fpx.entity.DataFpx;

public class FpxProsesDirectMessage extends FpxBean implements IFpx{

	@Override
	public DataFpx doProcess(DataFpx fpx) {
		
		String noTransaksi = fpx.getNoTransaksi();
		DataFpx temp = get(noTransaksi);
		if(temp == null){
			temp = save(fpx);
		}else{
			
			if(fpx.getDirectMessage().equals("SUCCESSFUL")){
				System.out.println("**direct message status success");
				String status = temp.getStatus();
				if(status.equals("SUCCESSFUL")){
					System.out.println("**direct message : - found indirect set to success");
					updateDirectMessageStatus(fpx);
				}else{
					System.out.println("**direct message : - found indirect set to failed - generate new resit no");
					fpx.setNoResit(getReceiptNo(noTransaksi));
					fpx.setStatus("SUCCESSFUL");
				}
				
			}else{
				System.out.println("**direct message status unsuccessful");
				updateDirectMessageStatus(fpx);
			}
				
			
		}
		return null;
	}
	private void updateStatus(DataFpx fpx){
		Connection conn = null;
	    Db db = null;
	    String sql = "";
	   
	    try
	    {
	    	db = new Db();
	    	conn = db.getConnection();
			conn.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sql ="UPDATE TBLDATAFPX SET STATUS_TRANSAKSI ='"+fpx.getStatus()+"',DIRECT_MESSAGE='"+fpx.getIndirectMessage()+"',NO_RESIT='"+fpx.getNoResit()+"' WHERE NO_TRANSAKSI='"+fpx.getNoTransaksi()+"'";
	    	
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
	private void updateDirectMessageStatus(DataFpx fpx){
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
	    	sql ="UPDATE TBLDATAFPX SET DIRECT_MESSAGE ='"+fpx.getStatus()+"' WHERE NO_TRANSAKSI='"+fpx.getNoTransaksi()+"'";
	    	
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
}
