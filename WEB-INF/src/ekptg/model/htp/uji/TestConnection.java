package ekptg.model.htp.uji;

import lebah.db.Db;

public class TestConnection
{
  public static void main(String[] args)
    throws Exception
  {
    Db db = new Db("dbconnectionphg");
    System.out.println(db.getConnectionURL());
    //System.out.println(db.getConnection().);
  
  }
  
}