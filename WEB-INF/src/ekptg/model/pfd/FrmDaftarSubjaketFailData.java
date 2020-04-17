package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;

public class FrmDaftarSubjaketFailData {
	
	private static Vector list = new Vector();
	public static void  setCarianFail(String noFail,String noFailLama,String tajukFail,String negeri,String seksyen,String status,String tarikhDaftar, String user_id, String myrole, String user_negeri, String id_seksyen)throws Exception {
	    Db db = null;
	    list.clear();
	    String sql = "";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      if (myrole.equalsIgnoreCase("(PFD)Pengarah") || myrole.equalsIgnoreCase("(PFD)Pembantu Tadbir") || myrole.equalsIgnoreCase("adminpfd"))
		  { 
		      sql = "SELECT DISTINCT J.NO_FAIL AS NO_FAIL_ASAL,A.ID_FAIL, A.TARIKH_DAFTAR_FAIL,A.TAJUK_FAIL, A.NO_FAIL, B.NAMA_NEGERI,C.KOD_SEKSYEN, D.KETERANGAN AS STATUS_FAIL, G.TARAF_KESELAMATAN," +
		      		" E.KETERANGAN AS STATUS_PERGERAKAN_FAIL" +
		      		" FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLRUJSEKSYEN C, TBLRUJSTATUS D, TBLRUJSTATUS E,TBLPFDPERGERAKANFAIL F, TBLPFDRUJTARAFKESELAMATAN G,TBLPFDFAILMAPPING I,TBLPFDFAIL J" +    		
		      		" WHERE " +
		      		" A.ID_FAIL = F.ID_FAIL(+)" +
		      		" AND A.ID_NEGERI = B.ID_NEGERI" +
		      		" AND A.ID_SEKSYEN = C.ID_SEKSYEN " +
		      		" AND A.ID_STATUS = D.ID_STATUS(+)" +
		      		" AND F.ID_STATUS = E.ID_STATUS(+)" +
		      		" AND A.ID_TARAFKESELAMATAN = G.ID_TARAFKESELAMATAN(+)" +
		      		" AND A.ID_FAIL NOT IN (SELECT ID_FAILASAL FROM TBLPFDFAILMAPPING WHERE ID_FAILASAL IS NOT NULL)" +
		      		" AND J.ID_FAIL(+) = I.ID_FAILASAL"+
		      		" AND A.ID_FAIL = I.ID_FAILARKIB(+)"+
		      		" AND A.ID_STATUS <> 999" +
		      	//	" AND A.ID_URUSAN NOT IN (382,6,108,11,10,3,4,2,5,12,7,8,9,13,17,1,309,108)";
		      		" AND A.ID_URUSAN NOT IN (383,382,6,108,3,4,2,12,7,8,9,13,17,1,309,108,5,10,11,161042)";		    	  
			    	  if(user_negeri.equals("16"))
				      {
			    		  sql +=	" and a.id_seksyen ='"+id_seksyen+"'"+
				  			" and a.id_negeri ='"+user_negeri+"'";
				      }	      	
			    	  else
			    	  {
			      		sql +=	" and a.id_negeri ='"+user_negeri+"'";
			    	  }
		  }
	      
	      else
	      {
	    	  sql = "SELECT DISTINCT J.NO_FAIL AS NO_FAIL_ASAL,A.ID_FAIL, A.TARIKH_DAFTAR_FAIL,A.TAJUK_FAIL, A.NO_FAIL, B.NAMA_NEGERI,C.KOD_SEKSYEN, D.KETERANGAN AS STATUS_FAIL," +
	    		" E.KETERANGAN AS STATUS_PERGERAKAN_FAIL" +
	    		" FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLRUJSEKSYEN C, TBLRUJSTATUS D, TBLRUJSTATUS E,TBLPFDPERGERAKANFAIL F,TBLPFDFAILMAPPING I,TBLPFDFAIL J" + 
	    		" WHERE " +
	    		" A.ID_FAIL = F.ID_FAIL(+)" +
				" AND A.ID_NEGERI = B.ID_NEGERI" +
	    		" AND A.ID_SEKSYEN = C.ID_SEKSYEN " +
	    		" AND A.ID_STATUS = D.ID_STATUS(+)" +
	    		" AND F.ID_STATUS = E.ID_STATUS(+)" +
//	     		" AND A.ID_TARAFKESELAMATAN in (1)" +
	     		" AND A.ID_FAIL NOT IN (SELECT ID_FAILASAL FROM TBLPFDFAILMAPPING WHERE ID_FAILASAL IS NOT NULL)" +
	      		" AND J.ID_FAIL(+) = I.ID_FAILASAL"+
	      		" AND A.ID_FAIL = I.ID_FAILARKIB(+)"+
	     		" AND A.ID_STATUS <> 999" +
	      		//" AND A.ID_URUSAN NOT IN (382,6,108,11,10,3,4,2,5,12,7,8,9,13,17,1,309,108)";
				" AND A.ID_URUSAN NOT IN (383,382,6,108,3,4,2,12,7,8,9,13,17,1,309,108,5,10,11,161042)";		    	  
		    	  
		    	  if(user_negeri.equals("16"))
			      {
		    		  sql +=	" and a.id_seksyen ='"+id_seksyen+"'"+
			  			" and a.id_negeri ='"+user_negeri+"'";
			      }	      	
		    	  else
		    	  {
		      		sql +=	" and a.id_negeri ='"+user_negeri+"'";
		    	  }

	      }
	      
			boolean setLimit = true;
	      
	      //NO FAIL
	      if (noFail != null) {
				if (!noFail.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'" + noFail.toUpperCase().trim() + "'|| '%'";
				}
			}
	      
	      //NO FAIL LAMA
	      if (noFailLama != null) {
				if (!noFailLama.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(J.NO_FAIL) LIKE '%' ||'" + noFailLama.toUpperCase().trim() + "'|| '%'";
					
				}
			}
	      
	      //TAJUK FAIL
	      if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(A.TAJUK_FAIL) LIKE '%' ||'" + tajukFail.toUpperCase().trim() + "'|| '%'";
				}
			}
	      
	      //NEGERI
	      if (negeri != null) {
				if (!negeri.trim().equals("")) {
					if (!negeri.trim().equals("0")) {
						setLimit = false;
						sql = sql + " AND A.ID_NEGERI = '" + negeri + "'";
					}
				}
			}
	      
	      //SEKSYEN
	      if (seksyen != null) {
				if (!seksyen.trim().equals("")) {
					if (!seksyen.trim().equals("0")) {
						setLimit = false;
						sql = sql + " AND A.ID_SEKSYEN = '" + seksyen + "'";
					}
				}
			}
	      
	      //STATUS
	      if (status != null) {
				if (!status.trim().equals("")) {
					if (!status.trim().equals("0")) {
						setLimit = false;
						sql = sql + " AND A.ID_STATUS = '" + status + "'";
					}
				}
			}
	      
	      //TARIKH DAFTAR FAIL	      
//	      SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
//	      if (tarikhDaftar != null) {
//				if (!tarikhDaftar.toString().trim().equals("")) {			 
//					sql = sql + " AND A.TARIKH_DAFTAR_FAIL = '" + sdf1.format(sdf.parse(tarikhDaftar)).toUpperCase() +"'";
//				}
//			}
	      
	      if (tarikhDaftar != null) {
				if (!tarikhDaftar.toString().trim().equals("")) {	
					setLimit = false;		 
//					sql = sql + " AND A.TARIKH_DAFTAR_FAIL = '" + sdf1.format(sdf.parse(tarikhDaftar)).toUpperCase() +"'";
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'DD/MM/YYYY') = '" + tarikhDaftar +"'";
				}
			}
	      
//			if (setLimit) {	//RESERVED BY AZAM
//				System.out.println("set LIMIT " +setLimit);	
//					sql = sql + " AND ROWNUM <= 50 ";				
//				
//			}
	      
	      sql = sql + " ORDER BY ID_FAIL DESC";
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_Fail",rs.getString("ID_FAIL"));
	    	  h.put("no_Fail", rs.getString("NO_FAIL")== null?"":rs.getString("NO_FAIL"));
	    	  h.put("no_Fail_Asal", rs.getString("NO_FAIL_ASAL")== null? "":rs.getString("NO_FAIL_ASAL"));
	    	  h.put("tajuk_Fail", rs.getString("TAJUK_FAIL")== null?"":rs.getString("TAJUK_FAIL"));
	    	  h.put("tarikh_Daftar_Fail",rs.getDate("TARIKH_DAFTAR_FAIL")== null? "":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
	    	  h.put("nama_Negeri", rs.getString("NAMA_NEGERI"));
	    	  h.put("kod_Seksyen",rs.getString("kod_Seksyen"));
	    	  h.put("keterangan1", rs.getString("STATUS_FAIL")== null? "":rs.getString("STATUS_FAIL"));
	    	  h.put("keterangan2", rs.getString("STATUS_PERGERAKAN_FAIL")== null? "":rs.getString("STATUS_PERGERAKAN_FAIL"));
	    	  list.addElement(h);	
//			if ((setLimit && bil <= 50) || setLimit == false) {	//RESERVED BY AZAM
//			    			
//			}
	    	  
	    	  bil++;
	    	  count++;
	    	  
	      }
	     
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil","");
	    	  h.put("id_Fail",0);
	    	  h.put("no_Fail", "Tiada rekod.");
	    	  h.put("no_Fail_Asal", "");
	    	  h.put("tajuk_Fail", "");
	    	  h.put("tarikh_Daftar_Fail", "");
	    	  h.put("nama_Negeri", "");
	    	  h.put("kod_Seksyen","");
	    	  h.put("keterangan1", "");
	    	  h.put("keterangan2", "");
	    	  list.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	}
	 public static Vector getList(){
		 
		  return list;
	  }
	 
	 public static Object totalFail(HttpSession session) throws Exception {
		  	String userId = (String)session.getAttribute("_ekptg_user_id");
		  	String portal_role = (String)session.getAttribute("_portal_role");
		  	String user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
		 	String myrole = (String)session.getAttribute("myrole");
		 	
		  	Db db = null; 
		  	int total = 0;
		  	String sql="";
		  	ResultSet rs = null;
			try { 
				//Open the database connection
				db = new Db(); 

				if (myrole.equalsIgnoreCase("(PFD)Pengarah") || myrole.equalsIgnoreCase("(PFD)Pembantu Tadbir") || myrole.equalsIgnoreCase("adminpfd"))
				{
			    	  sql = "SELECT COUNT(DISTINCT A.ID_FAIL) AS TOTAL "+
			      		" FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLRUJSEKSYEN C, TBLRUJSTATUS D, TBLRUJSTATUS E,TBLPFDPERGERAKANFAIL F" + 
			      		" WHERE " +
			      		" A.ID_FAIL = F.ID_FAIL(+)" +
						" AND A.ID_NEGERI = B.ID_NEGERI" +
			      		" AND A.ID_SEKSYEN = C.ID_SEKSYEN " +
			      		" AND A.ID_STATUS = D.ID_STATUS(+)" +
			      		" AND F.ID_STATUS = E.ID_STATUS(+)"+
						" AND A.ID_URUSAN NOT IN (382,6,108,3,4,2,12,7,8,9,13,17,1,309,108,5,10,11,161042)";		    	  
			    	  if(user_negeri.equals("16"))
				      {
			    		  sql +=	" and a.id_seksyen =(select id_seksyen from users_internal where user_id='"+userId+"')";
				      }	      	
			      		sql +=	" and a.id_negeri =(select id_negeri from users_internal where user_id='"+userId+"')";
			      	
			      }
			      else
			      {
			    	  sql = "SELECT COUNT(DISTINCT A.ID_FAIL) AS TOTAL "+
			      		" FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLRUJSEKSYEN C, TBLRUJSTATUS D, TBLRUJSTATUS E,TBLPFDPERGERAKANFAIL F" + 
			      		" WHERE " +
			      		" A.ID_FAIL = F.ID_FAIL(+)" +
						" AND A.ID_NEGERI = B.ID_NEGERI" +
			      		" AND A.ID_SEKSYEN = C.ID_SEKSYEN " +
			      		" AND A.ID_STATUS = D.ID_STATUS(+)" +
			      		" AND F.ID_STATUS = E.ID_STATUS(+)" +
			      		" AND A.ID_TARAFKESELAMATAN in (1)" +
						" AND A.ID_URUSAN NOT IN (382,6,108,3,4,2,12,7,8,9,13,17,1,309,108,5,10,11,161042)";		    	  
			    	  if(user_negeri.equals("16"))
				      {
			    		  sql +=	" and a.id_seksyen =(select id_seksyen from users_internal where user_id='"+userId+"')";
				      }	      	
			      		sql +=	" and a.id_negeri =(select id_negeri from users_internal where user_id='"+userId+"')";
			      }
				
				
				//get some data 
				rs = db.getStatement().executeQuery(sql); 
			if ( rs.next() ) { 
				total = rs.getInt(1); 
			} 
			} finally { 
			//Close the database connection 
			if ( db != null ) db.close(); 
			if (rs != null) rs.close();			
			} 
			return total;
		} 

}
