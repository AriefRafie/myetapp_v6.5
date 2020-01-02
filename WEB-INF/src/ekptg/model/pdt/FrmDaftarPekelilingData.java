package ekptg.model.pdt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class FrmDaftarPekelilingData {
	private static Logger myLogger = Logger.getLogger(FrmDaftarPekelilingData.class);

	private static Vector list = new Vector();
	public void  setCarianPekeliling(String kategoriPekeliling,String perkaraPekeliling, String tajukPekeliling, String tahun, String tarikhKuatkuasa) throws Exception{
		 Db db = null;
		 list.clear();
		 String sql = "";
		    
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		 
		 sql = "SELECT A.ID_PEKELILING,A.BIL_PEKELILING, A.TAJUK_PEKELILING,A.TARIKH_KUATKUASA, A.TAHUN,B.PERKARA_PEKELILING, C.JENIS_DOKUMEN_PEKELILING,D.KETERANGAN,E.ID_LAMPIRAN" +
		 	   " FROM TBLPDTPEKELILING A, TBLPDTRUJPERKARAPEKELILING B, TBLPDTRUJDOKUMENPEKELILING C, TBLRUJSTATUS D, TBLPDTRUJLAMPIRAN E" +
		 	   " WHERE A.ID_PERKARAPEKELILING = B.ID_PERKARAPEKELILING(+)" +
		 	   " AND A.ID_DOKUMENPEKELILING = C.ID_DOKUMENPEKELILING(+)" +
		 	   " AND A.ID_STATUS = D.ID_STATUS(+)" +
		 	   " AND A.ID_PEKELILING = E.ID_PEKELILING(+)";
		 	  
		 		
		 
		 //DOKUMEN PEKELILING
		 if (kategoriPekeliling != null) {
				if (!kategoriPekeliling.trim().equals("")) {
					if (!kategoriPekeliling.trim().equals("0")) {
						sql = sql + " AND A.ID_DOKUMENPEKELILING '" + kategoriPekeliling + "'";
					}
				}
		 }
		 
		 //PERKARA PEKELILING
		 if (perkaraPekeliling != null) {
				if (!perkaraPekeliling.trim().equals("")) {
					if (!perkaraPekeliling.trim().equals("0")) {
						sql = sql + " AND A.ID_PERKARAPEKELILING =  '" + perkaraPekeliling + "'";
					}
				}
		 }
		 
		
		 //TAJUK PEKELILING
		 if (tajukPekeliling != null) {
				if (!tajukPekeliling.trim().equals("")) {
					sql = sql + " AND UPPER(A.TAJUK_PEKELILING) LIKE '%' ||'" + tajukPekeliling.toUpperCase() + "'|| '%'";
				}
			}
		 
		 //TAHUN
		 if (tahun != null) {
				if (!tahun.trim().equals("")) {
					sql = sql + " AND UPPER(A.TAHUN) LIKE '%' ||'" + tahun.toUpperCase() + "'|| '%'";
				}
			}
		 
		 //TARIKH KUATKUASA
		 SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
	      if (tarikhKuatkuasa != null) {
				if (!tarikhKuatkuasa.toString().trim().equals("")) {			 
					sql = sql + " AND A.TARIKH_KUATKUASA = '" + sdf1.format(sdf.parse(tarikhKuatkuasa)).toUpperCase() +"'";
				}
			}
	      
	      myLogger.debug(sql);
	      sql = sql + " ORDER BY A.ID_PEKELILING";
	      
	      ResultSet rs = stmt.executeQuery(sql);
		    
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_Pekeliling",rs.getString("ID_PEKELILING"));
	    	  h.put("bil_Pekeliling", rs.getString("BIL_PEKELILING")==null?"":rs.getString("BIL_PEKELILING"));
	    	  h.put("tajuk_Pekeliling",rs.getString("TAJUK_PEKELILING") == null?"":rs.getString("TAJUK_PEKELILING"));
	    	  h.put("kategori_Pekeliling", rs.getString("JENIS_DOKUMEN_PEKELILING")== null? "":rs.getString("JENIS_DOKUMEN_PEKELILING"));
	    	  h.put("perkara_Pekeliling", rs.getString("PERKARA_PEKELILING")== null?"":rs.getString("PERKARA_PEKELILING"));
	    	  h.put("tahun",rs.getString("TAHUN")== null? "":rs.getString("TAHUN"));
	    	  h.put("tarikh_Kuatkuasa", rs.getDate("TARIKH_KUATKUASA") == null? "":sdf.format(rs.getDate("TARIKH_KUATKUASA")));
	    	  h.put("status",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
	    	  h.put("id_Lampiran",rs.getString("ID_LAMPIRAN")== null?"":rs.getString("ID_LAMPIRAN"));
	    	  list.addElement(h);
	    	  bil++;
	    	  count++;
	    	  
	      }
	     
//	      if (count == 0){
//	    	  h = new Hashtable();
//	    	  h.put("bil","");
//	    	  h.put("id_Pekeliling",0);
//	    	  h.put("bil_Pekeliling", "Tiada rekod.");
//	    	  h.put("tajuk_Pekeliling","");
//	    	  h.put("kategori_Pekeliling", "");
//	    	  h.put("perkara_Pekeliling", "");
//	    	  h.put("tahun", "");
//	    	  h.put("tarikh_Kuatkuasa", "");
//	    	  h.put("status","");
//	    	  h.put("id_Lampiran","");
//	    	  list.addElement(h);
//	      }
	      //return list;
	    }catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}
		 finally {
	      if (db != null) db.close();
	    }

	}

	public Vector getCarianPekeliling(String kategoriPekeliling,String perkaraPekeliling, String tajukPekeliling
			, String tahun, String tarikhKuatkuasa, String tag, String role) throws Exception{
		 Db db = null;
		 list.clear();
		 String sql = ""; 
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 try {
			 db = new Db();
			 Statement stmt = db.getStatement();
			 sql = "SELECT A.ID_DOKUMENPEKELILING, A.ID_PEKELILING,A.BIL_PEKELILING, A.TAJUK_PEKELILING,A.TARIKH_KUATKUASA, A.TAHUN,B.PERKARA_PEKELILING, C.JENIS_DOKUMEN_PEKELILING,D.KETERANGAN,E.ID_LAMPIRAN" +
		 	 	" FROM TBLPDTPEKELILING A, TBLPDTRUJPERKARAPEKELILING B, TBLPDTRUJDOKUMENPEKELILING C, TBLRUJSTATUS D, TBLPDTRUJLAMPIRAN E" +
		 	 	" WHERE A.ID_PERKARAPEKELILING = B.ID_PERKARAPEKELILING(+)" +
		 	 	" AND A.ID_DOKUMENPEKELILING = C.ID_DOKUMENPEKELILING(+)" +
		 	 	" AND A.ID_STATUS = D.ID_STATUS(+)" +
		 	 	" AND A.ID_PEKELILING = E.ID_PEKELILING(+)";

			 //DOKUMEN PEKELILING
		 if (kategoriPekeliling != null) {
				if (!kategoriPekeliling.trim().equals("")) {
					if (!kategoriPekeliling.trim().equals("0")) {
						sql = sql + " AND A.ID_DOKUMENPEKELILING =  '" + kategoriPekeliling + "'";
					}
				}
		 }
		 
		 //PERKARA PEKELILING
		 if (perkaraPekeliling != null) {
				if (!perkaraPekeliling.trim().equals("")) {
					if (!perkaraPekeliling.trim().equals("0")) {
						sql = sql + " AND A.ID_PERKARAPEKELILING =  '" + perkaraPekeliling + "'";
					}
				}
		 }
		 
		
		 //TAJUK PEKELILING
		 if (tajukPekeliling != null) {
				if (!tajukPekeliling.trim().equals("")) {
					sql = sql + " AND UPPER(A.TAJUK_PEKELILING) LIKE '%' ||'" + tajukPekeliling.toUpperCase() + "'|| '%'";
				}
			}
		 
		 //TAHUN
		 if (tahun != null) {
				if (!tahun.trim().equals("")) {
					sql = sql + " AND UPPER(A.TAHUN) LIKE '%' ||'" + tahun.toUpperCase() + "'|| '%'";
				}
			}
		 
		 //TARIKH KUATKUASA
		 SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
	      if (tarikhKuatkuasa != null) {
				if (!tarikhKuatkuasa.toString().trim().equals("")) {			 
					sql = sql + " AND A.TARIKH_KUATKUASA = '" + sdf1.format(sdf.parse(tarikhKuatkuasa)).toUpperCase() +"'";
				}
			}
			if (!"".equalsIgnoreCase(tag)) {
				sql += " AND A.ID_PEKELILING IN ( " +
						" SELECT TAGI.ID_DOKUMEN FROM TBLRUJTAGDOKUMEN TAGI,TBLPDTPEKELILING DTPI  " +
						" WHERE DTPI.ID_PEKELILING = TAGI.ID_DOKUMEN AND TAGI.SUMBER='PEKELILING' " +
						" AND TAGI.TAG_DOKUMEN LIKE '%" + tag + "%' )";
			}
	      
			/*if(role.equals("(PDT) Pengguna Bahagian Standard Inspektorat") || role.equals("(PDT) Pengguna Bahagian Pengambilan Tanah") || role.equals("(PDT) Pengguna Bahagian Pengurusan ICT") || role.equals("(PDT) Pengguna Bahagian Pembahagian Pusaka"))
			{
				//1-PEKELILING KPTG
				//2-PEKELILING PTP
				//3-PEKELILING KPPT
				//4-SURAT PEKELILING
				//5-LAIN - LAIN PEKELILINGmj
				sql += " AND A.ID_DOKUMENPEKELILING = 1  ";
			}
			
			else if(role.equals("(PDT) Pengguna Bahagian Penguatkuasa dan Hasil Persekutuan"))
			{
				//1-PEKELILING KPTG
				//2-PEKELILING PTP
				//3-PEKELILING KPPT
				//4-SURAT PEKELILING
				//5-LAIN - LAIN PEKELILING
				sql += " AND A.ID_DOKUMENPEKELILING = 2  ";
			}

			else if(role.equals("(PDT) Pengguna Bahagian Standard Inspektorat") || role.equals("(PDT) Pengguna Bahagian Pengambilan Tanah") ||  role.equals("(PDT) Pengguna Bahagian Pengurusan ICT") || role.equals("(PDT) Pengguna Bahagian Pembahagian Pusaka"))
			{
				//1-PEKELILING KPTG
				//2-PEKELILING PTP
				//3-PEKELILING KPPT
				//4-SURAT PEKELILING
				//5-LAIN - LAIN PEKELILING
				sql += " AND A.ID_DOKUMENPEKELILING = 4  ";
			}
			else if(role.equals("(PDT) Pengguna Bahagian Standard Inspektorat") || role.equals("(PDT) Pengguna Bahagian Pengambilan Tanah")  || role.equals("(PDT) Pengguna Bahagian Pengurusan ICT") || role.equals("(PDT) Pengguna Bahagian Pembahagian Pusaka") || role.equals("(PDT) Pengguna Bahagian Pasukan Petugas Khas") || role.equals("(PDT) Pengguna Bahagian Khidmat Pengurusan"))
			{
				//1-PEKELILING KPTG
				//2-PEKELILING PTP
				//3-PEKELILING KPPT
				//4-SURAT PEKELILING
				//5-LAIN - LAIN PEKELILING
				sql += " AND A.ID_DOKUMENPEKELILING = 5  ";
			}*/
			/*else 
			{
				//1-PEKELILING KPTG
				//2-PEKELILING PTP
				//3-PEKELILING KPPT
				//4-SURAT PEKELILING
				//5-LAIN - LAIN PEKELILING
				sql += " AND A.ID_DOKUMENPEKELILING in (1,2,3,4,5)  ";
			}*/
			
	      
	      sql = sql + " ORDER BY A.ID_PEKELILING";
	      myLogger.info(" getCarianPekeliling : "+sql);
	      ResultSet rs = stmt.executeQuery(sql);		    
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_Pekeliling",rs.getString("ID_PEKELILING"));
	    	  h.put("bil_Pekeliling", rs.getString("BIL_PEKELILING")==null?"":rs.getString("BIL_PEKELILING"));
	    	  h.put("tajuk_Pekeliling",rs.getString("TAJUK_PEKELILING") == null?"":rs.getString("TAJUK_PEKELILING"));
	    	  h.put("kategori_Pekeliling", rs.getString("JENIS_DOKUMEN_PEKELILING")== null? "":rs.getString("JENIS_DOKUMEN_PEKELILING"));
	    	  h.put("perkara_Pekeliling", rs.getString("PERKARA_PEKELILING")== null?"":rs.getString("PERKARA_PEKELILING"));
	    	  h.put("tahun",rs.getString("TAHUN")== null? "":rs.getString("TAHUN"));
	    	  h.put("tarikh_Kuatkuasa", rs.getDate("TARIKH_KUATKUASA") == null? "":sdf.format(rs.getDate("TARIKH_KUATKUASA")));
	    	  h.put("status",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
	    	  h.put("id_Lampiran",rs.getString("ID_LAMPIRAN")== null?"":rs.getString("ID_LAMPIRAN"));
	    	  list.addElement(h);
	    	  bil++;
	    	  count++;
	    	  
	      }
	    }catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}
		  finally {
	      if (db != null) db.close();
	    }
	    return list;

	}	
	 public Vector getList(){	 
		 return list;
	 }
	  
	 public void Delete(String idPekeliling) throws Exception {
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_Pekeliling",idPekeliling);
				sql = r.getSQLDelete("TBLPDTPEKELILING");
				myLogger.debug(sql);
				stmt.executeUpdate(sql);
			} catch (Exception re) {
		    	myLogger.error("Error: ", re);
		    	throw re;
		    	}
			 finally {
				if (db != null)
					db.close();
			}

		}
	 

}
