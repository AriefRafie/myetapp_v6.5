package ekptg.model.pdt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;

public class PDTPerundanganBean implements IPDT{
	
 	private IHtp iHTP = null;  
	private static Logger myLog = Logger.getLogger(ekptg.model.pdt.PDTPerundanganBean.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String sql = "";
	PfdFail fail = null;
	Permohonan permohonan = null;
	HtpPermohonan htpPermohonan = null;
	private String idTag = "";
	private Vector list = new Vector();

	@Override
	 public String tambah(Hashtable<?, ?> data) throws Exception{
		 Db db = null;
		 String sql = "";  
		 Date now = new Date();
		 try {	 
			 long idDokumen = DB.getNextID("TBLPDTPERUNDANGAN_SEQ");
			 String idSeksyen = (String)data.get("id_Seksyen");
			 String tajukDokumen = (String)data.get("tajuk_Dokumen");
			 String noRujDokumen = (String)data.get("no_Rujukan_Dokumen");
	    	 String tarikhBicara = (String)data.get("tarikhBicara");
	    	 String tkhBicara = "to_date('" + tarikhBicara + "','dd/MM/yyyy')";
	    	 String catatan = (String)data.get("catatan");
	    	 String tarikhDokumen = (String)data.get("tarikh_Dokumen");
	    	 String tkhDokumen = "to_date('" + tarikhDokumen + "','dd/MM/yyyy')";
	    	 String idMasuk = (String)data.get("idMasuk");
			 //String idJenisDokumen = (String)data.get("id_Jenisdokumen");
	    	 //String idFail = (String)data.get("id_Fail");
			      
	    	 db = new Db();
	    	 Statement stmt = db.getStatement();
	    	 SQLRenderer r = new SQLRenderer();			      
	    	 r.add("ID_PERUNDANGAN",idDokumen);
	    	 r.add("KETERANGAN_PERUNDANGAN", tajukDokumen);
	    	 r.add("TEMPAT_BICARA",noRujDokumen);
		     r.add("TARIKH_BICARA", r.unquote(tkhBicara));
		     r.add("KETERANGAN_KEPUTUSAN", catatan);
		     r.add("TARIKH_KEPUTUSAN", r.unquote(tkhDokumen));
			 r.add("ID_AKTA", idSeksyen);
			 r.add("TARIKH_DAFTAR",r.unquote("sysdate"));
			 r.add("ID_MASUK",idMasuk);
			 r.add("TARIKH_MASUK",r.unquote("sysdate"));
			 //r.add("id_Jenisdokumen", idJenisDokumen);
			 //r.add("id_Fail", idFail);
			 sql = r.getSQLInsert("TBLPDTPERUNDANGAN");			      				     
			 stmt.executeUpdate(sql);
			      
			 String tagDokumen = (String)data.get("tagDokumen");
			 String idTagDokumen = (String)data.get("idTagDokumen");
			 if(!tagDokumen.equals("")){
				 db = new Db();
				 stmt = db.getStatement();
				 r = new SQLRenderer();			      
				 if(!idTagDokumen.equals("0")){
					 r.update("ID_RUJTAG", data.get("idTagDokumen"));
					 r.add("ID_KEMASKINI", data.get("idMasuk"));
					 r.add("TAG_DOKUMEN",tagDokumen);
					 r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					 sql = r.getSQLUpdate("TBLRUJTAGDOKUMEN");								
					 idTag = String.valueOf(idTagDokumen);
						
				 }else{
					 long idTagDokumenBaru = DB.getNextID("TBLRUJTAGDOKUMEN_SEQ");
					 r.add("ID_RUJTAG",idTagDokumenBaru);
					 r.add("ID_DOKUMEN",idDokumen);
					 r.add("TAG_DOKUMEN",tagDokumen);
					 r.add("SUMBER","PERUNDANGAN");
					 r.add("TARIKH_MASUK",r.unquote("sysdate")); 
					 r.add("ID_MASUK",data.get("idMasuk"));
					 sql = r.getSQLInsert("TBLRUJTAGDOKUMEN");  
					 idTag = String.valueOf(idTagDokumenBaru);
						
				 }
				 stmt.executeUpdate(sql);

			 }
			 return String.valueOf(idDokumen);
		 	
		 } catch (Exception re) {
			 myLog.error("Error: ", re);
				 throw re;
				 }
			     finally {
			 if (db != null) db.close();
		 }
		    
	 }
	
	 public void kemaskini(Hashtable data) throws Exception {
		 Db db = null;
		 String sql = "";		 
		 try {
			 int idDokumen = (Integer)data.get("id_Dokumen");
		      String tajukDokumen = (String)data.get("tajuk_Dokumen");
		      String noRujDokumen = (String)data.get("no_Rujukan_Dokumen");
		      String tarikhBicara = (String)data.get("tarikhBicara");
		      String tkhBicara = "to_date('" + tarikhBicara + "','dd/MM/yyyy')";
		      String catatan = (String)data.get("catatan");
		      String tarikhDokumen = (String)data.get("tarikh_Dokumen");
		      String tkhDokumen = "to_date('" + tarikhDokumen + "','dd/MM/yyyy')";
		      String idSeksyen = (String)data.get("id_Seksyen");
			     //String noFail = (String)data.get("no_Fail");
		    	  //String idJenisDokumen = (String)data.get("id_Jenisdokumen");
			      
		      db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();			      
			  r.update("ID_PERUNDANGAN",idDokumen);
			  r.add("KETERANGAN_PERUNDANGAN", tajukDokumen);
			  r.add("TEMPAT_BICARA",noRujDokumen);
			  r.add("TARIKH_BICARA", r.unquote(tkhBicara));
			  r.add("KETERANGAN_KEPUTUSAN", catatan);
			  r.add("TARIKH_KEPUTUSAN", r.unquote(tkhDokumen));
			  r.add("ID_AKTA", idSeksyen);
			  r.add("ID_KEMASKINI", data.get("idMasuk"));
			  r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
			  sql = r.getSQLUpdate("TBLPDTPERUNDANGAN");
			  stmt.executeUpdate(sql);
			      
			  String tagDokumen = (String)data.get("tagDokumen");
			  String idTagDokumen = (String)data.get("idTagDokumen");
			  //myLog.info(tagDokumen+","+idTagDokumen);
			  if(!tagDokumen.equals("")){
				  db = new Db();
				  stmt = db.getStatement();
				  r = new SQLRenderer();			      
				  if(!idTagDokumen.equals("0")){
					  r.update("ID_RUJTAG", data.get("idTagDokumen"));
					  r.add("ID_KEMASKINI", data.get("idMasuk"));
					  r.add("TAG_DOKUMEN",tagDokumen);
					  r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					  sql = r.getSQLUpdate("TBLRUJTAGDOKUMEN");								
						
				  }else{
					  long idTagDokumenBaru = DB.getNextID("TBLRUJTAGDOKUMEN_SEQ");
					  r.add("ID_RUJTAG",idTagDokumenBaru);
					  r.add("ID_DOKUMEN",idDokumen);
					  r.add("TAG_DOKUMEN",tagDokumen);
					  r.add("SUMBER","PERUNDANGAN");
					  r.add("TARIKH_MASUK",r.unquote("sysdate")); 
					  r.add("ID_MASUK",data.get("idMasuk"));
					  sql = r.getSQLInsert("TBLRUJTAGDOKUMEN");  
						
				  }
				  stmt.executeUpdate(sql);
			  }

		 }catch (Exception re) {
			 myLog.error("Error: ", re);
				 throw re;
				 }
			     finally {
			 if (db != null) db.close();
		 }
		  
	 }
	
	public Hashtable getMaklumat(String idPerundangan) throws Exception{
		 Db db = null;
		 String sql = "";	 
		 Hashtable h = null;;
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();		      
		      sql = "SELECT "+ 
		      		" NVL((SELECT NAMA_AKTA FROM TBLPDTAKTA WHERE ID_AKTA = DTP.ID_AKTA),'') NAMA_AKTA "+
		      		" ,DTP.ID_PERUNDANGAN,DTP.KETERANGAN_PERUNDANGAN,DTP.TEMPAT_BICARA" +
		      		" ,to_char(DTP.TARIKH_KEPUTUSAN,'dd/MM/YYYY') TARIKH_KEPUTUSAN " +
		      		" ,DTP.ID_AKTA"+ 
		      		" ,to_char(DTP.TARIKH_BICARA,'dd/MM/YYYY') TARIKH_BICARA " +
		      		" ,to_char(DTP.TARIKH_DAFTAR,'dd/MM/YYYY') TARIKH_DAFTAR " +
				    " ,DTP.KETERANGAN_KEPUTUSAN "+
				    " ,NVL((SELECT TAG_DOKUMEN FROM TBLRUJTAGDOKUMEN WHERE ID_DOKUMEN=DTP.ID_PERUNDANGAN AND SUMBER='PERUNDANGAN' ),'') TAG_DOKUMEN"+
				    " ,NVL((SELECT ID_RUJTAG FROM TBLRUJTAGDOKUMEN WHERE ID_DOKUMEN=DTP.ID_PERUNDANGAN AND SUMBER='PERUNDANGAN' ),'0') ID_DOKUMEN"+
		      		" FROM TBLPDTPERUNDANGAN DTP " +
		      		" ";
		
		      sql = sql + " WHERE DTP.ID_PERUNDANGAN = "+idPerundangan; 
		      sql = sql + " ORDER BY DTP.ID_PERUNDANGAN";	
		      myLog.info(sql);
		      ResultSet rs = stmt.executeQuery(sql);			    
		      int bil = 1;
		      int count = 0;	      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("namaAkta",Utils.isNull(rs.getString(1)));
		    	  h.put("idPerundangan",Utils.isNull(rs.getString(2)));
		    	  h.put("maklumat",Utils.isNull(rs.getString(3)));
		    	  h.put("tempatBicara",Utils.isNull(rs.getString(4)));
		    	  h.put("tarikhKeputusan",Utils.isNull(rs.getString(5)));
		    	  h.put("idAkta",Utils.isNull(rs.getString(6)));
		    	  h.put("tarikhBicara",Utils.isNull(rs.getString(7)));
		    	  h.put("tarikhDaftar",Utils.isNull(rs.getString(8)));
		    	  h.put("catatan",Utils.isNull(rs.getString(9)));
		    	  h.put("tagging",Utils.isNull(rs.getString(10)));
		    	  h.put("idTagging",Utils.isNull(rs.getString(11)));
		    	  //list.addElement(h);
		    	  bil++;
		    	  count++;
		    	  
		      }
		 
		 } catch (Exception re) {
			 myLog.error("Error: ", re);
				 throw re;
				 }
			     finally {
		      if (db != null) db.close();
		    }
		 return h;
		 
	}
	
	public Vector<Hashtable<String, Comparable>> getCarian(String idAkta, String maklumat,String tempatBicara
			, String tarikhKeputusan,  String tag) throws Exception{
		 Db db = null;
		 list.clear();
		 String sql = "";	 
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();		      
		      sql = "SELECT "+ 
		      		" NVL((SELECT NAMA_AKTA FROM TBLPDTAKTA WHERE ID_AKTA = DTP.ID_AKTA),'') NAMA_AKTA "+
		      		" ,DTP.ID_PERUNDANGAN,DTP.KETERANGAN_PERUNDANGAN,DTP.TEMPAT_BICARA" +
		      		",to_char(DTP.TARIKH_KEPUTUSAN,'dd/MM/YYYY') TARIKH_KEPUTUSAN "+ 
		      		" FROM TBLPDTPERUNDANGAN DTP " +
		      		" ";
		      sql = sql + " WHERE UPPER(DTP.KETERANGAN_PERUNDANGAN) LIKE '%' ||'" + maklumat.toUpperCase() + "'|| '%'"; 
		      //AKTA
		      if (idAkta != null) {
					if (!idAkta.trim().equals("")) {
						if (!idAkta.trim().equals("0")) {
							sql = sql + " AND DTP.ID_AKTA = " + idAkta + "";
						}
					}
				}
		      if (tempatBicara != null) {
					if (!tempatBicara.trim().equals("")) {
						sql = sql + " AND UPPER(DTP.TEMPAT_BICARA) LIKE '%' ||'" + tempatBicara.toUpperCase() + "'|| '%'";
					}
				}
	      //TARIKH KEPUTUSAN
		      SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
		      if (tarikhKeputusan != null) {
					if (!tarikhKeputusan.toString().trim().equals("")) {			 
						sql = sql + " AND DTP.TARIKH_KEPUTUSAN = '" + sdf1.format(sdf.parse(tarikhKeputusan)).toUpperCase() +"'";
					}
				}
		      //TAGGING			
		      if (!"".equalsIgnoreCase(tag)) {
		    	  sql += " AND DTP.ID_PERUNDANGAN IN ( " +
		    	  	" SELECT TAGI.ID_DOKUMEN FROM TBLRUJTAGDOKUMEN TAGI,TBLPDTPERUNDANGAN DTPI  " +
					" WHERE DTPI.ID_PERUNDANGAN = TAGI.ID_DOKUMEN AND TAGI.SUMBER='PERUNDANGAN' " +
					" AND TAGI.TAG_DOKUMEN LIKE '%" + tag + "%' )";
		      }	      
		      sql = sql + " ORDER BY DTP.ID_PERUNDANGAN";		      
		      ResultSet rs = stmt.executeQuery(sql);			    
		      Hashtable<String, Comparable> h;
		      int bil = 1;
		      int count = 0;	      
		      while (rs.next()) {
		    	  h = new Hashtable<String, Comparable>();
		    	  h.put("bil", bil);
		    	  h.put("namaAkta",Utils.isNull(rs.getString(1)));
		    	  h.put("idPerundangan",Utils.isNull(rs.getString(2)));
		    	  h.put("maklumat",Utils.isNull(rs.getString(3)));
		    	  h.put("tempatBicara",Utils.isNull(rs.getString(4)));
		    	  h.put("tarikhKeputusan",Utils.isNull(rs.getString(5)));
		    	  list.addElement(h);
		    	  bil++;
		    	  count++;
		    	  
		      }
		 
		 } catch (Exception re) {
			 myLog.error("Error: ", re);
				 throw re;
				 }
			     finally {
		      if (db != null) db.close();
		    }
		 return list;
		 
	}
	
	 public Vector getLampirans(String id_)throws Exception {
		 Db db = null;
		 list.clear();
		 String sql = "";
		 try {
			 db = new Db();
			 Statement stmt = db.getStatement();
		     SQLRenderer r = new SQLRenderer();	      
		     r.add("id_Lampiran");
		     r.add("id_Dokumen");
		     r.add("nama_Fail");
		     r.add("jenis_Mime");	     		     		      
		     r.add("id_Dokumen",id_);
		     //r.add("SUMBER",r.unquote("PERUNDANGAN"));
		     
		     sql = r.getSQLSelect("TBLPDTRUJLAMPIRANSEMUA");
		     sql +=" AND SUMBER = 'PERUNDANGAN' ORDER BY id_Lampiran asc";
		     myLog.info("SQL List Lampiran:: "+sql);
		     ResultSet rs = stmt.executeQuery(sql);
		     Hashtable h;
		     int bil = 1;
		     int count = 0;
		     while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("id_Lampiran",rs.getInt("id_Lampiran"));
		    	  h.put("id_Dokumen", rs.getString("id_Dokumen")== null?"":rs.getString("id_Dokumen"));
		    	  h.put("nama_Fail", rs.getString("nama_Fail")== null?"":rs.getString("nama_Fail"));
		    	  h.put("jenis_Mime",rs.getString("jenis_Mime")== null?"":rs.getString("jenis_Mime"));    	 
		    	  list.addElement(h);
		    	  bil++;
		    	  count++;
		     }
		 }catch (Exception re) {
			 myLog.error("Error: ", re);
				 throw re;
				 }
			      finally {
		      if (db != null) db.close();
		 }
		    return list;
	 }
	 
	 public void hapusLampiran(String idLampiran) throws Exception {
		 Db db = null;
		 String sql = "";
		 try {
			 db = new Db();
			 Statement stmt = db.getStatement();
			 SQLRenderer r = new SQLRenderer();
			 //TBLPFDRUJLAMPIRAN
			 r.add("ID_LAMPIRAN", idLampiran);
			 sql = r.getSQLDelete("TBLPDTRUJLAMPIRANSEMUA");
			 stmt.executeUpdate(sql);
		 }catch (Exception re) {
			 myLog.error("Error: ", re);
				 throw re;
				 }
			      finally {
			if (db != null)
				db.close();
			}
	 
	 }
	 
	 public void hapus(String idPerundangan) throws Exception {
		 Db db = null;
		 String sql = "";
		 try {
			 db = new Db();
			 Statement stmt = db.getStatement();
			 SQLRenderer r = new SQLRenderer();
			 //TBLPFDRUJLAMPIRAN
			 r.add("ID_PERUNDANGAN", idPerundangan);
			 sql = r.getSQLDelete("TBLPDTPERUNDANGAN");
			 stmt.executeUpdate(sql);
		 } catch (Exception re) {
			 myLog.error("Error: ", re);
				 throw re;
				 }
			     finally {
			if (db != null)
				db.close();
			}
	 
	 }
	
	@Override
	public String getIdTag(){
		return idTag;
	}
	  
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}	
	
	
}
