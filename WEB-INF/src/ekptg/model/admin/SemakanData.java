package ekptg.model.admin;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.util.Util;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujurusan;

public class SemakanData {

	static Logger myLog = Logger.getLogger(ekptg.model.admin.SemakanData.class);
    Vector<Hashtable<String,String>> list = null;

	public static Vector<Hashtable<String,String>> getSenaraiSemakan(String idUrusan
		,String idSuburusan,String kodForm,String desc) 
		throws Exception {
	    Db db = null;
	    String sql = "";
	    Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
		    
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();		      
		      r.add("i.id_semakansenarai");
		      r.add("i.id_semakan");
		      r.add("i.aturan");
		      r.add("i.kod_form");
		      r.add("s.perihal");
		      r.add("ru.nama_urusan");
		      r.add("rsu.nama_suburusan");
		      r.add("i.id_urusan");
		      r.add("i.id_suburusan");
		      r.add("i.id_semakan",r.unquote("s.id_semakan"));
		      r.add("i.id_urusan",r.unquote("ru.id_urusan"));
		      r.add("i.id_suburusan",r.unquote("rsu.id_suburusan(+)"));
		      if(!kodForm.equals(""))
		    	  r.add("i.kod_form","%"+kodForm+"%","like");
		      if(!idUrusan.equals("0"))
		    	  r.add("i.id_urusan",idUrusan);
		      if(!idSuburusan.equals("0"))
		    	  r.add("i.id_suburusan",idSuburusan);
		      if(desc != null)
		    	  r.add("s.perihal","%"+desc+"%","like");
		      
		      sql = r.getSQLSelect("tblsemakan s,tblsemakansenarai i,tblrujurusan ru,tblrujsuburusan rsu","i.kod_form,i.aturan");
	          myLog.info("Semakan : sql=" + sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable<String,String> h;
		      int bil = 1;
		      while (rs.next()) {
		    	  h = new Hashtable<String,String>();
		    	  h.put("bil", String.valueOf(bil));
		    	  h.put("id", rs.getString("id_semakansenarai"));
		    	  h.put("idSemakan", rs.getString("id_semakan"));
		    	  h.put("idUrusan", rs.getString("id_urusan"));
		    	  h.put("idSubUrusan", Utils.isNull(rs.getString("id_suburusan")));
		    	  h.put("aturan", Utils.isNull(rs.getString("aturan")));
		    	  h.put("keterangan", rs.getString("perihal"));
		    	  h.put("urusan", rs.getString("nama_urusan"));
		    	  h.put("kod", rs.getString("kod_form"));
		    	  list.addElement(h);
		    	  bil++;
		    	  
		      }
		      
		    }catch(Exception e){
		    	e.printStackTrace();
		    }finally {
		      if (db != null){
		    	  db.close();
		      }
		    }	    
		return list;
		  
	}

	public static Vector<Hashtable<String, Comparable>> getSenaraiKeterangan(String idUrusan,String idSuburusan) 
		throws Exception {
		 Db db = null;
		 String sql = "";
		 try {
			 db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("kpu.id_kpiurusan");
		      r.add("kpk.keterangan");
		      r.add("kpk.id_kpiketerangan");
		      r.add("kpu.sasaran_masa");
		      r.add("kpu.jenis_sasaran");		      
		      r.add("kpu.id_masuk");
		      r.add("kpu.tarikh_masuk");
		      r.add("kpu.id_kemaskini");
		      r.add("kpu.seq");
		      r.add("kpu.tarikh_kemaskini");
		      r.add("ru.nama_urusan");
		      r.add("kpu.status_giliran");
		      r.add("kpu.pilihan");
		      r.add("kpu.id_kpiketerangan",r.unquote("kpk.id_kpiketerangan"));
		      r.add("kpu.id_suburusan",r.unquote("rsu.id_suburusan"));
		      r.add("rsu.id_urusan",r.unquote("ru.id_urusan"));
		      r.add("rsu.id_suburusan",r.unquote(idSuburusan));
		      r.add("ru.id_urusan",r.unquote(idUrusan));
				      
		      //sql = r.getSQLSelect("tblrujsuburusanstatusfail s,tblkpistatus ist,tblkpiurusan iu,tblkpiketerangan k");
		      
		      //sql = r.getSQLSelect("tblkpiketerangan kpk,tblkpiurusan kpu,tblrujsuburusanstatus s ,tblrujsuburusan r, tblrujurusan t","iu.tarikh_kemaskini");
		      sql = r.getSQLSelect("tblkpiketerangan kpk ,tblkpiurusan kpu ,tblrujsuburusan rsu,tblrujurusan ru");
		      sql += " ORDER BY kpu.seq";

		      String strDate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector<Hashtable<String, Comparable>> list = new Vector<Hashtable<String, Comparable>>();
		      while (rs.next()) {
		        Hashtable<String, Comparable> cb = new Hashtable<String, Comparable>();
		        cb.put("idkpiurusan", rs.getLong("id_kpiurusan"));
		        cb.put("keterangan",rs.getString("keterangan"));
		        cb.put("idkpiketerangan",rs.getString("id_kpiketerangan"));
		        cb.put("sasaranmasa",rs.getString("sasaran_masa"));
		        if(rs.getInt("jenis_sasaran")==1){
		        	cb.put("jenissasaran","JAM");		        
		        	cb.put("idsasaran",rs.getString("jenis_sasaran"));		        	        	
		        }else if(rs.getInt("jenis_sasaran")==2){
		        	cb.put("jenissasaran","HARI");		        
		        	cb.put("idsasaran",rs.getString("jenis_sasaran"));		        
		        }else{
		        	cb.put("jenissasaran","TIADA");		        
		        	cb.put("idsasaran",rs.getString("jenis_sasaran"));		        	        	
		        }
		        
		        cb.put("idmasuk",rs.getString("id_masuk")==null?"0":rs.getString("id_masuk"));
		        cb.put("tarikhmasuk",rs.getDate("tarikh_masuk")==null?strDate:lebah.util.Util.getDateTime(rs.getDate("tarikh_masuk"), "dd/MM/yyyy"));
		        cb.put("idkemaskini",rs.getString("id_kemaskini")==null?"0":rs.getString("id_kemaskini"));
		        cb.put("tarikhkemaskini",rs.getDate("tarikh_kemaskini")==null?"0":lebah.util.Util.getDateTime(rs.getDate("tarikh_kemaskini"), "dd/MM/yyyy"));
		        cb.put("urusan",rs.getString("nama_urusan"));
		        cb.put("giliran",rs.getInt("status_giliran"));
		        cb.put("aturan",rs.getInt("seq"));
		        cb.put("pilihan",rs.getInt("pilihan"));
		        list.addElement(cb);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  
	}
	 
	public static Vector<Hashtable<String, String>> getSenaraiJenisDokumen(
		String idUrusan,String idSuburusan,String kodForm) throws Exception {
		Db db = null;
		String sql = "";
		String strDate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
//
//SELECT S.PERIHAL
//,SS.ATURAN
//,NVL(SJD.ID_JENISDOKUMEN,0) JENIS_DOKUMEN
//,NVL(JD.KETERANGAN,'TIADA') NAMA_DOKUMEN
//--SS.*
//FROM 
//TBLSEMAKANSENARAI SS, TBLSEMAKAN S,TBLSEMAKANJENISDOKUMEN SJD,TBLRUJJENISDOKUMEN JD
//WHERE SS.ID_SEMAKAN = S.ID_SEMAKAN
//AND SS.ID_SEMAKAN  = SJD.ID_SEMAKAN(+)
//AND SJD.ID_JENISDOKUMEN  = JD.ID_JENISDOKUMEN(+)
//--AND SS.ATURAN IS NOT NULL
//--
//ORDER BY SS.ATURAN
		try {	
			db = new Db();
			Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("sjd.id_semakanjenisdokumen id_semakansenarai");
		      r.add("i.id_semakan");
		      r.add("i.aturan");
		      r.add("i.kod_form");
		      r.add("s.perihal");
		      r.add("ru.nama_urusan");
		      r.add("rsu.nama_suburusan");
		      r.add("i.id_urusan");
		      r.add("i.id_suburusan");
		      r.add("NVL(JD.ID_JENISDOKUMEN,0) JENIS_DOKUMEN");
		      r.add("NVL(JD.KETERANGAN,'TIADA') NAMA_DOKUMEN");
		      r.add("i.id_semakan",r.unquote("s.id_semakan"));
		      r.add("i.id_urusan",r.unquote("ru.id_urusan"));
		      r.add("i.id_suburusan",r.unquote("rsu.id_suburusan(+)"));
		      r.add("i.ID_SEMAKAN",r.unquote("SJD.ID_SEMAKAN(+)"));
		      r.add("SJD.ID_JENISDOKUMEN",r.unquote("JD.ID_JENISDOKUMEN(+)"));
		      if(!kodForm.equals(""))
		    	  r.add("s.perihal","%"+kodForm+"%","like");
		      if(!idUrusan.equals("0"))
		    	  r.add("i.id_urusan",idUrusan);
		      if(!idSuburusan.equals("0"))
		    	  r.add("i.id_suburusan",idSuburusan);
		      
		      sql = r.getSQLSelect("tblsemakan s"
		      		+ ",tblsemakansenarai i"
		      		+ ",tblrujurusan ru"
		      		+ ",tblrujsuburusan rsu"
		      		+ ",TBLSEMAKANJENISDOKUMEN SJD"
		      		+ ",TBLRUJJENISDOKUMEN JD","i.kod_form,i.aturan");
	      		
		      //sql += " ORDER BY kpu.seq";
		   	  System.out.println("getSenaraiJenisDokumen:sql = "+sql);
      
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
		      int bil=1;
		      while (rs.next()) {
		        Hashtable<String, String> h = new Hashtable<String, String>();
		        h.put("bil", String.valueOf(bil));
		    	  h.put("id", Utils.isNull(rs.getString("id_semakansenarai")));
		    	  h.put("idSemakan", rs.getString("id_semakan"));
		    	  h.put("idUrusan", rs.getString("id_urusan"));
		    	  h.put("idSubUrusan", Utils.isNull(rs.getString("id_suburusan")));
		    	  h.put("aturan", Utils.isNull(rs.getString("aturan")));
		    	  h.put("keterangan", rs.getString("perihal"));
		    	  h.put("urusan", rs.getString("nama_urusan"));
		    	  h.put("kod", rs.getString("kod_form"));
		    	  h.put("jenis", rs.getString("jenis_dokumen"));
		    	  h.put("namaDok", rs.getString("nama_dokumen"));
		    

		        list.addElement(h);
		        bil++;
		      }
		      return list;
		      
		    } finally {
		      if (db != null) db.close();
		    }
		  
	}
	 
	public static Vector<Hashtable<String, Comparable>> getStatusByKPISuburusan(String id)throws Exception {
		 Db db = null;
		 String sql = "";
		 try {
			 db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("kpu.id_kpiurusan");
		      r.add("kpu.sasaran_masa");
		      r.add("kpu.jenis_sasaran");		      
		      r.add("kpu.id_masuk");
		      r.add("kpu.tarikh_masuk");
		      r.add("kpu.id_kemaskini");
		      r.add("kpu.tarikh_kemaskini");
			  r.add("kps.id_suburusanstatus");
			  //r.add("ru.nama_urusan");
		      //r.add("kpu.status_giliran");
		      //r.add("kpu.id_kpiketerangan",r.unquote("kpk.id_kpiketerangan"));
		      //r.add("kpu.id_suburusan",r.unquote("rsu.id_suburusan"));
		      //r.add("rsu.id_urusan",r.unquote("ru.id_urusan"));
		      //r.add("rsu.id_suburusan",r.unquote(idSuburusan));
		      //r.add("ru.id_urusan",r.unquote(idUrusan));
		      r.add("kpu.id_kpiurusan",r.unquote("kps.id_kpiurusan"));	         
		      r.add("kpu.id_kpiurusan",r.unquote(id));	         
      
		      //sql = r.getSQLSelect("tblkpiketerangan kpk ,tblkpiurusan kpu ,tblrujsuburusan rsu,tblrujurusan ru");
		      sql = r.getSQLSelect("tblkpiurusan kpu ,tblkpistatus kps");
		      //sql += " order by kpu.tarikh_kemaskini";

		      
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector<Hashtable<String, Comparable>> list = new Vector<Hashtable<String, Comparable>>();
		      while (rs.next()) {
		        Hashtable<String, Comparable> cb = new Hashtable<String, Comparable>();
		        cb.put("idkpiurusan", rs.getLong("id_kpiurusan"));
		        cb.put("sasaranmasa",rs.getString("sasaran_masa"));
		        if(rs.getString("jenis_sasaran")=="1"){
		        	cb.put("jenissasaran","JAM");		        
		        	
		        }else{
		        	cb.put("jenissasaran","HARI");		        
		        }
		        cb.put("idmasuk",rs.getString("id_masuk"));
		        cb.put("tarikhmasuk",lebah.util.Util.getDateTime(rs.getDate("tarikh_masuk"), "dd/MM/yyyy"));
		        cb.put("idkemaskini",rs.getString("id_kemaskini"));
		        cb.put("tarikhkemaskini",lebah.util.Util.getDateTime(rs.getDate("tarikh_kemaskini"), "dd/MM/yyyy"));
		        cb.put("idsuburusanstatus",rs.getString("id_suburusanstatus"));
		        //cb.put("urusan",rs.getString("nama_urusan"));
		        //cb.put("giliran",rs.getInt("status_giliran"));
		        list.addElement(cb);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  
	} 
	 
	public static Vector<Hashtable<String, Comparable>> getKeteranganTerperinci(String idUrusan,String idSuburusan)throws Exception {
		 Db db = null;
		 String sql = "";
		 try {
			 db = new Db();
		      Statement stmt = db.getStatement();
		      
		      /*SQLRenderer r = new SQLRenderer();
		      
		      r.add("kpk.id_kpiketerangan");
		      r.add("kpk.keterangan");
		      r.add("kpu.sasaran_masa");
		      r.add("kpu.jenis_sasaran");
		      r.add("kpu.status_giliran");
		      r.add("kpu.seq");
		      r.add("kpu.id_kpiurusan");
		      r.add("kpu.pilihan");
		      r.add("count(rsf.id_suburusanstatus) aktiviti");
		      r.add("sum((rsf.tarikh_kemaskini-rsf.tarikh_masuk)) hari");		      
		      r.add("sum(((rsf.tarikh_kemaskini-rsf.tarikh_masuk)+1)) hari1");
		      
		      r.add("kpu.id_kpiketerangan",r.unquote("kpk.id_kpiketerangan"));
		      r.add("kpu.id_suburusan",r.unquote("rsu.id_suburusan"));
		      r.add("rsu.id_urusan",r.unquote("ru.id_urusan"));
		      r.add("rsu.id_suburusan",r.unquote(idSuburusan));
		      r.add("ru.id_urusan",r.unquote(idUrusan));
		      r.add("kpu.id_kpiurusan",r.unquote("kps.id_kpiurusan"));	         
		      r.add("kps.id_suburusanstatus",r.unquote("rsf.id_suburusanstatus"));		      
				
		      sql = r.getSQLSelect("tblkpiketerangan kpk ,tblkpiurusan kpu ,tblrujsuburusan rsu,tblrujurusan ru,tblkpistatus kps,tblrujsuburusanstatusfail rsf");
		      sql += " GROUP BY  kpk.id_kpiketerangan,kpk.keterangan,kpu.sasaran_masa,kpu.jenis_sasaran,kpu.tarikh_kemaskini,kpu.status_giliran,kpu.seq,kpu.id_kpiurusan ";
		      sql += " ORDER BY kpu.seq"; */
		      
		      sql =  " SELECT kpk.id_kpiketerangan, kpk.keterangan, kpu.sasaran_masa, kpu.jenis_sasaran, kpu.status_giliran, kpu.seq, kpu.id_kpiurusan, kpu.pilihan, ";
		      sql += " count(rsf.id_suburusanstatus) aktiviti, sum((rsf.tarikh_kemaskini-rsf.tarikh_masuk)) hari, sum(((rsf.tarikh_kemaskini-rsf.tarikh_masuk)+1)) hari1 ";  
		      sql += " FROM tblkpiketerangan kpk ,tblkpiurusan kpu ,tblrujsuburusan rsu,tblrujurusan ru,tblkpistatus kps,tblrujsuburusanstatusfail rsf "; 
		      sql += " WHERE kpu.id_kpiketerangan = kpk.id_kpiketerangan  AND kpu.id_suburusan = rsu.id_suburusan ";  
		      sql += " AND rsu.id_urusan = ru.id_urusan  AND rsu.id_suburusan = "+idSuburusan;  
		      sql += " AND ru.id_urusan = "+idUrusan+" AND kpu.id_kpiurusan = kps.id_kpiurusan ";  
		      sql += " AND kps.id_suburusanstatus = rsf.id_suburusanstatus ";  
		      sql += " GROUP BY  kpk.id_kpiketerangan,kpk.keterangan,kpu.sasaran_masa,kpu.jenis_sasaran,kpu.tarikh_kemaskini,kpu.status_giliran,kpu.seq,kpu.id_kpiurusan,kpu.pilihan ";  
		      sql += " ORDER BY kpu.seq ";
		      
			  myLog.info("FrmKPIData::getKeteranganTerperinci("+idUrusan+","+idSuburusan+")::sql = "+sql);
			  ResultSet rs = stmt.executeQuery(sql);
		      Vector<Hashtable<String, Comparable>> list = new Vector<Hashtable<String, Comparable>>();
		      double dblTemp =0L;
		      while (rs.next()) {
		        Hashtable<String, Comparable> cb = new Hashtable<String, Comparable>();
			    cb.put("idkpiketerangan", rs.getLong("id_kpiketerangan"));
			    cb.put("keterangan",rs.getString("keterangan"));
		        cb.put("sasaranmasa",rs.getDouble("sasaran_masa"));
		        if(rs.getString("jenis_sasaran")=="1"){
		        	cb.put("jenissasaran","JAM");		        
		        	
		        }else{
		        	cb.put("jenissasaran","HARI");		        
		        }
		        cb.put("giliran",rs.getInt("status_giliran"));
		        cb.put("aktiviti",rs.getDouble("aktiviti"));
		        java.text.DecimalFormat df = new java.text.DecimalFormat("#,###,###.0");
		        cb.put("harif",df.format(rs.getDouble("hari")));
		        cb.put("hari1f",df.format(rs.getDouble("hari1")));
		        cb.put("hari",rs.getDouble("hari"));
		        cb.put("idkpiurusan",rs.getString("id_kpiurusan"));
		        cb.put("hari1",rs.getDouble("hari1"));
		        if(rs.getInt("hari")==0)
			        cb.put("puratanew","0.0");		        	
		        else{
		        	dblTemp = rs.getDouble("hari1")/rs.getDouble("aktiviti");
			        cb.put("puratanew",Utils.format1Decimal(dblTemp));		        	
		        }
		        if(rs.getInt("hari")==0)
			        cb.put("peratusnew","0.0");		        	
		        else{
		        	double dblTempPeratus = (rs.getDouble("sasaran_masa")/dblTemp)*100;
			        cb.put("peratusnew",Utils.format1Decimal(dblTempPeratus));		        	
		        }		        	
		        cb.put("pilihan",rs.getInt("pilihan"));

		        list.addElement(cb);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
	 
	 public static Vector<Hashtable<String, Comparable>> getKeteranganTerperinciByNegeri( 
			 String idUrusan,String idSuburusan,String idNegeri,String idDaerah,
			 String txdMula,String txdAkhir)throws Exception {
		 Db db = null;
		 String sql = "";
		 String sqlBilangan = "";
		 String sqlBilanganHari = "";
		 String sqlBilanganHari1 = "";
		 SQLRenderer renderer = new SQLRenderer();
		 renderer.add("count(*)");
		 renderer.add("kps1.id_suburusanstatus",renderer.unquote("rsf.id_suburusanstatus"));
		 renderer.add("kps1.id_kpiurusan",renderer.unquote("kps.id_kpiurusan"));
		 //
		 SQLRenderer renHari = new SQLRenderer();
		 renHari.add("NVL(round(sum((rsf.tarikh_kemaskini-rsf.tarikh_masuk))),0)");
		 renHari.add("kps1.id_suburusanstatus",renHari.unquote("rsf.id_suburusanstatus"));
		 renHari.add("kps1.id_kpiurusan",renHari.unquote("kps.id_kpiurusan"));

		 SQLRenderer renHari1 = new SQLRenderer();
		 renHari1.add("NVL(round(sum((rsf.tarikh_kemaskini-rsf.tarikh_masuk)+1)),0)");
		 renHari1.add("kps1.id_suburusanstatus",renHari1.unquote("rsf.id_suburusanstatus"));
		 renHari1.add("kps1.id_kpiurusan",renHari1.unquote("kps.id_kpiurusan"));

		 if(!idNegeri.equals("0")){
			 if(idUrusan.equals("382")){
				 renderer.add("ppkp.id_negerimhn",idNegeri);
				 renHari.add("ppkp.id_negerimhn",idNegeri);
				 renHari1.add("ppkp.id_negerimhn",idNegeri);
			 
			 }else{
				 renderer.add("F.ID_NEGERI",idNegeri);
				 renHari.add("F.ID_NEGERI",idNegeri);
				 renHari1.add("F.ID_NEGERI",idNegeri);		 
			 }
	     }
		 if(idUrusan.equals("382")){
	    	 renderer.add("ppkp.id_permohonan",renderer.unquote("rsf.id_permohonan"));		      		    	  
	    	 renHari.add("ppkp.id_permohonan",renHari.unquote("rsf.id_permohonan"));		      		    	  
	    	 renHari1.add("ppkp.id_permohonan",renHari1.unquote("rsf.id_permohonan"));		      		    	  
	    	 sqlBilangan = renderer.getSQLSelect("tblkpistatus kps1,tblrujsuburusanstatusfail rsf,tblppkpermohonan ppkp");
	    	 sqlBilanganHari = renHari.getSQLSelect("tblkpistatus kps1,tblrujsuburusanstatusfail rsf,tblppkpermohonan ppkp");
	    	 sqlBilanganHari1 = renHari1.getSQLSelect("tblkpistatus kps1,tblrujsuburusanstatusfail rsf,tblppkpermohonan ppkp");
	     }else{
	    	 renderer.add("PPKP.ID_FAIL",renderer.unquote("F.ID_FAIL"));		      		    	  
	    	 renderer.add("PPKP.ID_PERMOHONAN",renderer.unquote("RSF.ID_PERMOHONAN"));		      		    	  
	    	 renderer.add("PPKP.ID_FAIL",renderer.unquote("RSF.ID_FAIL"));
	    	 
	    	 renHari.add("PPKP.ID_FAIL",renderer.unquote("F.ID_FAIL"));		      		    	  
	    	 renHari.add("PPKP.ID_PERMOHONAN",renderer.unquote("RSF.ID_PERMOHONAN"));		      		    	  
	    	 renHari.add("PPKP.ID_FAIL",renderer.unquote("RSF.ID_FAIL"));
	    	 
	    	 renHari1.add("PPKP.ID_FAIL",renderer.unquote("F.ID_FAIL"));		      		    	  
	    	 renHari1.add("PPKP.ID_PERMOHONAN",renderer.unquote("RSF.ID_PERMOHONAN"));		      		    	  
	    	 renHari1.add("PPKP.ID_FAIL",renderer.unquote("RSF.ID_FAIL"));
	    	 
	    	 sqlBilangan = renderer.getSQLSelect("tblkpistatus kps1,tblrujsuburusanstatusfail rsf,tblpermohonan ppkp,TBLPFDFAIL F");
	    	 sqlBilanganHari = renHari.getSQLSelect("tblkpistatus kps1,tblrujsuburusanstatusfail rsf,tblpermohonan ppkp,TBLPFDFAIL F");
	    	 sqlBilanganHari1 = renHari1.getSQLSelect("tblkpistatus kps1,tblrujsuburusanstatusfail rsf,tblpermohonan ppkp,TBLPFDFAIL F");
	     }
		 if(!txdMula.equals("0") && !txdAkhir.equals("0")){
			 sqlBilangan += " AND to_Char(rsf.tarikh_kemaskini,'dd/mm/yyyy') between '"+txdMula+"' AND '"+txdAkhir+"'";
			 sqlBilanganHari += " AND to_Char(rsf.tarikh_kemaskini,'dd/mm/yyyy') between '"+txdMula+"' AND '"+txdAkhir+"'";
			 sqlBilanganHari1 += " AND to_Char(rsf.tarikh_kemaskini,'dd/mm/yyyy') between '"+txdMula+"' AND '"+txdAkhir+"'";
	     }
	     //mylog.info("FrmKPIData::getKeteranganTerperinciByNegeri::sqlBilangan = "+sqlBilangan);

		 String sqlNew = "SELECT DISTINCT(kpk.id_kpiketerangan), kpk.keterangan, kpu.sasaran_masa, "+ 
		 " kpu.jenis_sasaran, kpu.status_giliran , kpu.seq, kpu.id_kpiurusan "+	 
		 ",("+ sqlBilangan +") aktiviti"+
		 " ,("+ sqlBilanganHari +") hari "+
		 " ,("+ sqlBilanganHari1 +") hari1 "+
		 " ,kpu.pilihan"+
		 " FROM tblkpiketerangan kpk ,tblkpiurusan kpu ,tblrujsuburusan rsu,tblrujurusan ru,tblkpistatus kps "+
		 " WHERE kpu.id_kpiketerangan = kpk.id_kpiketerangan  AND kpu.id_suburusan = rsu.id_suburusan "+ 
		 " AND rsu.id_urusan = ru.id_urusan  AND rsu.id_suburusan = "+idSuburusan+
		 " AND ru.id_urusan = "+idUrusan+"  AND kpu.id_kpiurusan = kps.id_kpiurusan "+  
		 " ORDER BY kpu.seq ";
		 
		 try {
			 db = new Db();
		      Statement stmt = db.getStatement();
		      myLog.info("getKeteranganTerperinciByNegeri("+idUrusan+","+idSuburusan+","+idNegeri+","+idDaerah+","+
			  txdMula+","+ txdAkhir+")::sqlNew = "+sqlNew);
			  ResultSet rs = stmt.executeQuery(sqlNew);
		      Vector<Hashtable<String, Comparable>> list = new Vector<Hashtable<String, Comparable>>();
		      double dblTemp =0L;
		      while (rs.next()) {
		        Hashtable<String, Comparable> cb = new Hashtable<String, Comparable>();
			    cb.put("idkpiketerangan", rs.getLong("id_kpiketerangan"));
			    cb.put("keterangan",rs.getString("keterangan"));
		        cb.put("sasaranmasa",rs.getDouble("sasaran_masa"));
		        if(rs.getString("jenis_sasaran")=="1"){
		        	cb.put("jenissasaran","JAM");		        
		        	
		        }else{
		        	cb.put("jenissasaran","HARI");		        
		        }
		        cb.put("giliran",rs.getInt("status_giliran"));
		        cb.put("aktiviti",rs.getInt("aktiviti"));
		        //java.text.DecimalFormat df = new java.text.DecimalFormat("#,###,##0.0");
		        cb.put("harif",Utils.format1Decimal(rs.getDouble("hari")));
		        cb.put("hari1f",Utils.format1Decimal(rs.getDouble("hari1")));
		         //cb.put("hari1f",rs.getInt("hari1"));
		        cb.put("hari",rs.getDouble("hari"));
		        cb.put("idkpiurusan",rs.getString("id_kpiurusan"));
		        cb.put("hari1",rs.getDouble("hari1"));
		        if(rs.getInt("hari1")==0)
			        cb.put("puratanew","0.0");		        	
		        else{
		        	dblTemp = rs.getDouble("hari1")/rs.getDouble("aktiviti");
			        cb.put("puratanew",Utils.format1Decimal(dblTemp));		        	
		        }
		        if(rs.getInt("hari1")==0)
			        cb.put("peratusnew","0.0");		        	
		        else{
		        	double dblTempPeratus = (rs.getDouble("sasaran_masa")/dblTemp)*100;
			        cb.put("peratusnew",Utils.format1Decimal(dblTempPeratus));		        	
		        }		        	
		        cb.put("pilihan",rs.getInt("pilihan"));
		        list.addElement(cb);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
	 
	 public static Vector<Hashtable<String, Comparable>> getKPIKeberkesanan(String idUrusan,String idSuburusan)throws Exception {
		 Db db = null;
		 //String sql = "";
		 		//" from tblrujsuburusanstatusfail rsf,tblkpistatus kps,tblkpiurusan iu,tblrujsuburusanstatus sus  " +
				 //     sql = r.getSQLSelect("tblkpiketerangan kpk ,tblkpiurusan kpu ,tblrujsuburusan rsu,tblrujurusan ru,tblkpistatus kps,tblrujsuburusanstatusfail rsf");

	 String sql = " SELECT CASE" +
		 		" WHEN rsf.id_suburusanstatus =458 THEN 'Bil. Permohonan di selesaikan' " +
		 		" WHEN rsf.id_suburusanstatus =340 THEN 'Bil. Permohonan baru di terima' " +
		 		" WHEN rsf.id_suburusanstatus =398 THEN 'Bil. Permohonan' " +
		 		" else 'Lain-Lain' " +
		 		" END  AS Menunggu" +
		 		" ,count(*) AS Bilangan " +
		 		" FROM tblkpiketerangan kpk ,tblkpiurusan kpu ,tblrujsuburusan rsu,tblrujurusan ru,tblkpistatus kps,tblrujsuburusanstatusfail rsf "+
		 		" where" +
		 		" kpu.id_kpiketerangan = kpk.id_kpiketerangan " +  
		 		" AND kpu.id_suburusan = rsu.id_suburusan " +
		 		" AND rsu.id_urusan = ru.id_urusan " +
		 		" AND rsu.id_suburusan="+ idSuburusan +
		 		" AND ru.id_urusan= "+ idUrusan +
		 		" AND kpu.id_kpiurusan = kps.id_kpiurusan " +
		 		" AND kps.id_suburusanstatus = rsf.id_suburusanstatus " +
		  		" GROUP BY " +
		 		" CASE " +
		 		" WHEN rsf.id_suburusanstatus =458  THEN 'Bil. Permohonan di selesaikan' " +
		 		" WHEN rsf.id_suburusanstatus =340 THEN 'Bil. Permohonan baru di terima' " +
		 		" WHEN rsf.id_suburusanstatus =398  THEN 'Bil. Permohonan' " +
		 		" else 'Lain-Lain' END ORDER BY 1";
		 		


		 try {
			 db = new Db();
		      Statement stmt = db.getStatement();
		   	  //System.out.println("FrmKPIData::getKPIKeberkesanan::sql = "+sql);
			  ResultSet rs = stmt.executeQuery(sql);
		      Vector<Hashtable<String, Comparable>> list = new Vector<Hashtable<String, Comparable>>();
		      while (rs.next()) {
		        Hashtable<String, Comparable> cb = new Hashtable<String, Comparable>();
			    cb.put("menunggu", rs.getString("Menunggu"));
			    cb.put("bilangan",rs.getInt("Bilangan"));
			    list.addElement(cb);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
	 //select rsf.*,f.*,p.ID_NEGERIMHN from  
	 //tblrujsuburusanstatusfail rsf,tblppkpermohonan p,tblpfdfail f
	 //where rsf.ID_SUBURUSANSTATUS=458
	 //AND rsf.ID_PERMOHONAN=p.ID_PERMOHONAN AND p.ID_FAIL=f.ID_FAIL
	 //AND p.ID_NEGERIMHN=1
	 
	 public static Vector<Hashtable<String, Comparable>> getKPIKeberkesananByNegeri(String idUrusan,String idSuburusan,
			 String idnegeri,String idpejabat,String idSuburusanStatus)throws Exception {
		 Db db = null;
		 String sql = " SELECT CASE" +
		 		" WHEN rsf.id_suburusanstatus =458 THEN 'Bil. Permohonan di selesaikan' " +
		 		" WHEN rsf.id_suburusanstatus =340 THEN 'Bil. Permohonan baru di terima' " +
		 		" WHEN rsf.id_suburusanstatus =398 THEN 'Bil. Permohonan' " +
		 		" else 'Lain-Lain' " +
		 		" END  AS Menunggu" +
		 		" ,count(*) AS Bilangan " +
		 		" FROM tblkpiketerangan kpk ,tblkpiurusan kpu ,tblrujsuburusan rsu,tblrujurusan ru,tblkpistatus kps,tblrujsuburusanstatusfail rsf "+
		 		" where" +
		 		" kpu.id_kpiketerangan = kpk.id_kpiketerangan " +  
		 		" AND kpu.id_suburusan = rsu.id_suburusan " +
		 		" AND rsu.id_urusan = ru.id_urusan " +
		 		" AND rsu.id_suburusan="+ idSuburusan +
		 		" AND ru.id_urusan= "+ idUrusan +
		 		" AND kpu.id_kpiurusan = kps.id_kpiurusan " +
		 		" AND kps.id_suburusanstatus = rsf.id_suburusanstatus " +
		 		
		  		" GROUP BY " +
		 		" CASE " +
		 		" WHEN rsf.id_suburusanstatus =458  THEN 'Bil. Permohonan di selesaikan' " +
		 		" WHEN rsf.id_suburusanstatus =340 THEN 'Bil. Permohonan baru di terima' " +
		 		" WHEN rsf.id_suburusanstatus =398  THEN 'Bil. Permohonan' " +
		 		" else 'Lain-Lain' END ORDER BY 1";
		 try {
			 db = new Db();
		      Statement stmt = db.getStatement();
		   	  myLog.info("FrmKPIData::getKPIKeberkesananByNegeri::sql = "+sql);
			  ResultSet rs = stmt.executeQuery(sql);
		      Vector<Hashtable<String, Comparable>> list = new Vector<Hashtable<String, Comparable>>();
		      while (rs.next()) {
		        Hashtable<String, Comparable> cb = new Hashtable<String, Comparable>();
			    cb.put("menunggu", rs.getString("Menunggu"));
			    cb.put("bilangan",rs.getInt("Bilangan"));
			    list.addElement(cb);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
	 
	 public static Vector<Hashtable<String, Comparable>> getKPIKeberkesananUtama(String idUrusan,String idSuburusan,
			 String idnegeri,String idpejabat,String txdMula,String txdAkhir)throws Exception {
		 Db db = null;
		 //String sql = " select rsus.id_suburusanstatus,rs.KETERANGAN "+
		 String sql = " select distinct(rsus.id_suburusanstatus)"+
		 " ,CASE "+
		 " WHEN rownum =1 THEN 'Bil. Permohonan baru di terima' "+
		 " WHEN rownum =2 THEN 'Bil. Permohonan di selesaikan' "+	 
		 " END  AS Menunggu "+
		 " ,( "+
		 " select count(distinct(rsf.ID_PERMOHONAN))"+  
		 " from tblrujsuburusanstatusfail rsf "+
		 " ,tblppkpermohonan p where "+
		 " rsf.ID_SUBURUSANSTATUS=rsus.id_suburusanstatus "+
		 " and p.ID_PERMOHONAN=rsf.ID_PERMOHONAN ";
		 if(idnegeri != "0"){
			 sql +=" and p.id_negerimhn="+idnegeri;
		 }
		 if(txdMula != "0" && txdAkhir!= "0" ){
			 sql +=" and to_char(rsf.TARIKH_KEMASKINI) between '"+txdMula+"' and '"+txdAkhir+"'";
		 }
		 sql +=" ) bilangan "+
		 " from tblrujsuburusanstatus rsus,tblrujstatus rs, "+
		 " (   SELECT  rsus.ID_SUBURUSANSTATUS ID_SUBURUSANSTATUS      from  tblrujsuburusanstatus rsus "+     
		 "    ,(SELECT min( rsus.langkah)langkah,rsus.ID_SUBURUSAN id_suburusan from tblrujsuburusanstatus rsus "+          
		 "    where rsus.ID_SUBURUSAN="+ idSuburusan +" GROUP BY  rsus.ID_SUBURUSAN) mini "+      
		 " where rsus.ID_SUBURUSAN=mini.ID_SUBURUSAN AND rsus.langkah=mini.langkah "+ 
		 " ) mini "+
		 " ,(  SELECT  rsus.ID_SUBURUSANSTATUS ID_SUBURUSANSTATUS      from  tblrujsuburusanstatus rsus "+      
		 "    ,(SELECT max( rsus.langkah)langkah,rsus.ID_SUBURUSAN id_suburusan  from tblrujsuburusanstatus rsus "+          
		 "    where rsus.ID_SUBURUSAN="+ idSuburusan +" GROUP BY  rsus.ID_SUBURUSAN) maxi "+      
		 " where rsus.ID_SUBURUSAN=maxi.ID_SUBURUSAN     AND rsus.langkah=maxi.langkah "+
		 " ) maxi "+
		 " where rs.ID_STATUS=rsus.ID_STATUS  "+
		 " and rsus.id_suburusanstatus in (mini.ID_SUBURUSANSTATUS,maxi.ID_SUBURUSANSTATUS) "+
		 " ORDER BY rsus.id_suburusanstatus ";
		 try {
			 db = new Db();
		     Statement stmt = db.getStatement();
		   	 // mylog.info("FrmKPIData::getKPIKeberkesananUtama("+ idUrusan+","+idSuburusan+","+
			 // idnegeri+","+idpejabat+","+txdMula+","+txdAkhir+"::sql = "+sql);
		     ResultSet rs = stmt.executeQuery(sql);
		     Vector<Hashtable<String, Comparable>> list = new Vector<Hashtable<String, Comparable>>();
		     while (rs.next()) {
		    	 Hashtable<String, Comparable> cb = new Hashtable<String, Comparable>();
		    	 cb.put("menunggu", rs.getString("Menunggu"));
		    	 cb.put("bilangan",rs.getInt("Bilangan"));
		    	 list.addElement(cb);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }

	 public static Vector<Hashtable<String, Comparable>> getKPIKeberkesananUtamaHTP(String idUrusan,String idSuburusan,
			 String idnegeri,String idpejabat,String txdMula,String txdAkhir)throws Exception {
		 Db db = null;
		 //String sql = " select rsus.id_suburusanstatus,rs.KETERANGAN "+
		 String sql = " SELECT  DISTINCT(RSUS.id_suburusanstatus),RSUS.LANGKAH "+
		 " ,CASE "+
		 " WHEN rownum =1 THEN 'Bil. Permohonan baru di terima' "+
		 " WHEN rownum =2 THEN 'Bil. Permohonan di selesaikan' "+	 
		 " END  AS Menunggu "+
		 " ,( "+
		 " select count(distinct(rsf.ID_PERMOHONAN))"+  
		 " from tblrujsuburusanstatusfail rsf "+
		 " ,tblpermohonan p where "+
		 " rsf.ID_SUBURUSANSTATUS=rsus.id_suburusanstatus "+
		 " and p.ID_PERMOHONAN=rsf.ID_PERMOHONAN ";
		 if(idnegeri != "0"){
			 sql +=" and p.id_negerimhn="+idnegeri;
		 }
		 if(txdMula != "0" && txdAkhir!= "0" ){
			 sql +=" and to_char(rsf.TARIKH_KEMASKINI) between '"+txdMula+"' and '"+txdAkhir+"'";
		 }
		 sql +=" ) bilangan "+
		 " from tblrujsuburusanstatus rsus,tblrujstatus rs, "+
		 " (	SELECT  RSUSE.ID_SUBURUSANSTATUS ID_SUBURUSANSTATUS from TBLRUJSUBURUSANSTATUS RSUSE "+     
		 "    	,(	SELECT RSUSI.langkah,RSUSI.ID_SUBURUSAN id_suburusan from TBLRUJSUBURUSANSTATUS RSUSI "+          
		 "    		WHERE RSUSI.ID_SUBURUSAN="+ idSuburusan +" AND RSUSI.langkah='1' GROUP BY  RSUSI.langkah,RSUSI.ID_SUBURUSAN) MINII "+      
		 " 		WHERE RSUSE.ID_SUBURUSAN=MINII.ID_SUBURUSAN AND RSUSE.langkah=MINII.langkah "+ 
		 " ) MINI "+
		 " ,(  SELECT  rsus.ID_SUBURUSANSTATUS ID_SUBURUSANSTATUS from  tblrujsuburusanstatus rsus "+      
		 //"    ,(SELECT max( rsus.langkah)langkah,rsus.ID_SUBURUSAN id_suburusan  from tblrujsuburusanstatus rsus "+          
		 "    ,(SELECT RSUS.LANGKAH LANGKAH,RSUS.ID_SUBURUSAN id_suburusan  from tblrujsuburusanstatus RSUS "+          
		 "    WHERE RSUS.ID_SUBURUSAN="+ idSuburusan +" AND RSUS.langkah='99' GROUP BY  RSUS.LANGKAH,RSUS.ID_SUBURUSAN) maxi "+      
		 " WHERE rsus.ID_SUBURUSAN=maxi.ID_SUBURUSAN  AND rsus.langkah=maxi.langkah "+
		 " ) MAXI "+
		 " WHERE rs.ID_STATUS=rsus.ID_STATUS  "+
		 " and rsus.id_suburusanstatus in (MINI.ID_SUBURUSANSTATUS,MAXI.ID_SUBURUSANSTATUS) "+
		 " ORDER BY RSUS.LANGKAH";
		 try {
			 db = new Db();
		     Statement stmt = db.getStatement();
		   	 // mylog.info("FrmKPIData::getKPIKeberkesananUtama("+ idUrusan+","+idSuburusan+","+
			 // idnegeri+","+idpejabat+","+txdMula+","+txdAkhir+"::sql = "+sql);
		     ResultSet rs = stmt.executeQuery(sql);
		     Vector<Hashtable<String, Comparable>> list = new Vector<Hashtable<String, Comparable>>();
		     while (rs.next()) {
		    	 Hashtable<String, Comparable> cb = new Hashtable<String, Comparable>();
		    	 cb.put("menunggu", rs.getString("Menunggu"));
		    	 cb.put("bilangan",rs.getInt("Bilangan"));
		    	 list.addElement(cb);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
	 
	 

	 public static Vector<Hashtable<String, Comparable>> getKPIGiliranTerperinci(String idSuburusanstatus)throws Exception {
		 Db db = null;
		 //String sql = "";
		 String sql = " SELECT CASE" +
		 " WHEN (a.tarikh_kemaskini-a.tarikh_masuk) < 8 THEN '<7'" +
		 " WHEN (a.tarikh_kemaskini-a.tarikh_masuk) > 7 AND (a.tarikh_kemaskini-a.tarikh_masuk)<15 THEN '8-14'" +
		 " WHEN (a.tarikh_kemaskini-a.tarikh_masuk) > 14 THEN '>15' " +
		 " END  AS Menunggu" +
		 " ,count(*) AS Bilangan " +
		 " from tblrujsuburusanstatusfail a,tblkpistatus ist " +
		 " where " +
		 " a.id_suburusanstatus = ist.id_suburusanstatus(+) " +
	 	 " AND a.ID_SUBURUSANSTATUS="+ idSuburusanstatus +
		 " GROUP BY  " +
		 " CASE  " +
		 " WHEN (a.tarikh_kemaskini-a.tarikh_masuk) < 8 THEN '<7' " +
		 " WHEN (a.tarikh_kemaskini-a.tarikh_masuk) > 7 AND (a.tarikh_kemaskini-a.tarikh_masuk)<15 THEN '8-14' " +
		 " WHEN (a.tarikh_kemaskini-a.tarikh_masuk) > 14 THEN '>15'" +
		 " END ORDER BY 1 ";		 		
		 try {
			 db = new Db();
		      Statement stmt = db.getStatement();
		
		      //sql = r.getSQLSelect("tblrujsuburusanstatusfail s,tblkpistatus ist,tblkpiurusan iu,tblkpiketerangan k");
		      //sql += " group by k.id_kpiketerangan,k.keterangan,iu.sasaran_masa,iu.jenis_sasaran,iu.tarikh_kemaskini,iu.status_giliran order by iu.tarikh_kemaskini";
			  //System.out.println("FrmKPIData::getKPIGiliranTerperinci::sql = "+sql);
			  ResultSet rs = stmt.executeQuery(sql);
		      Vector<Hashtable<String, Comparable>> list = new Vector<Hashtable<String, Comparable>>();
		      while (rs.next()) {
		        Hashtable<String, Comparable> cb = new Hashtable<String, Comparable>();
			    cb.put("menunggu", rs.getString("Menunggu"));
			    cb.put("bilangan",rs.getInt("Bilangan"));
			    list.addElement(cb);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }	 
	 
	 public static Vector<Hashtable<String, Comparable>> getKPIGiliranTerperinci(String idSuburusanstatus,String strQuery)throws Exception {
		 Db db = null;
		 String sql = " SELECT f.no_fail,f.tajuk_fail,round((a.tarikh_kemaskini-a.tarikh_masuk)+1) menunggu,a.tarikh_masuk,a.tarikh_kemaskini" +
		 " from tblrujsuburusanstatusfail a,tblkpistatus ist,tblppkpermohonan p,tblpfdfail f " +
		 " where " +
		 " f.ID_FAIL = p.ID_FAIL "+
		 " AND p.ID_PERMOHONAN=a.ID_PERMOHONAN"+
		 " AND a.id_suburusanstatus = ist.id_suburusanstatus(+) " +
		 " AND a.ID_SUBURUSANSTATUS="+ idSuburusanstatus +
		 " AND (a.tarikh_kemaskini-a.tarikh_masuk) ";
			 if(strQuery.equals("8"))	
				 sql +="< 8";
			 else if(strQuery.equals("15"))
				 sql +=" >7 AND (a.tarikh_kemaskini-a.tarikh_masuk)<15";
			 else if(strQuery.equals("16"))
				 sql +="> 14";
			 else if(strQuery.equals("5"))
				 sql +="< 5";
			 else if(strQuery.equals("5-14"))
				 sql +=" >4 AND (a.tarikh_kemaskini-a.tarikh_masuk)<15";
			 else if(strQuery.equals("30"))
				 sql +="<31";
			 else if(strQuery.equals("60"))
				 sql +=" >30 AND (a.tarikh_kemaskini-a.tarikh_masuk)<61";
			 else if(strQuery.equals("61"))
				 sql +="> 60";			 
			 else{	}
			 try {
			 db = new Db();
		      Statement stmt = db.getStatement();
			  //mylog.info("FrmKPIData::getKPIGiliranTerperinci("+idSuburusanstatus+","+strQuery+")::sql = "+sql);
			  //mylog.info("FrmKPIData::getKPIGiliranTerperinci("+idSuburusanstatus+","+strQuery+")::sql = "+sql);
			  ResultSet rs = stmt.executeQuery(sql);
		      Vector<Hashtable<String, Comparable>> list = new Vector<Hashtable<String, Comparable>>();
		      while (rs.next()) {
		        Hashtable<String, Comparable> cb = new Hashtable<String, Comparable>();
			    //cb.put("nofail", rs.getString("no_fail"));
			    //cb.put("tajukfail", rs.getString("tajuk_fail"));
			    //cb.put("menunggu", rs.getInt("Menunggu"));
	
		    	cb.put("nofail", rs.getString("NO_FAIL")== null? "":rs.getString("NO_FAIL"));
		    	cb.put("tajukfail", rs.getString("tajuk_fail")== null? "":rs.getString("tajuk_fail"));
		    	cb.put("menunggu", rs.getString("Menunggu")== null? 0:rs.getInt("Menunggu"));

	    		//cb.put("tarikhmasuk", Util.getDateTime(rs.getDate("tarikh_masuk"), "dd/MM/yyyy"));
	    		//cb.put("tarikhkemaskini", Util.getDateTime(rs.getDate("tarikh_kemaskini"), "dd/MM/yyyy"));
		    	cb.put("tarikhmasuk",rs.getDate("tarikh_masuk")== null? "":Util.getDateTime(rs.getDate("tarikh_masuk"), "dd/MM/yyyy"));
		    	cb.put("tarikhkemaskini",rs.getDate("tarikh_kemaskini")== null? "":Util.getDateTime(rs.getDate("tarikh_kemaskini"), "dd/MM/yyyy"));

			    list.addElement(cb);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }	 
	 
	 public static int getKPIGiliranTerperinciCount(String idSuburusanstatus,String strQuery,String idNegeri)throws Exception {
		 Db db = null;
		 int returnValue=0;
		 /*String sql1 = " SELECT f.no_fail,f.tajuk_fail," +
		 		"NVL(( SELECT DISTINCT(round((a.tarikh_kemaskini-a.tarikh_masuk)+1)) menunggu"+
				" from tblrujsuburusanstatusfail a,tblkpistatus ist,tblppkpermohonan p,tblpfdfail f " +
				" where " +
				" f.ID_FAIL = p.ID_FAIL "+
				" AND p.ID_PERMOHONAN=a.ID_PERMOHONAN"+
				" AND a.id_suburusanstatus = ist.id_suburusanstatus" +
				" AND a.ID_SUBURUSANSTATUS="+ idSuburusanstatus ;
		 */
		 String sql = " select count(distinct(a.ID_PERMOHONAN)) menunggu "+
		 	" from tblrujsuburusanstatusfail a,tblppkpermohonan p  "+
			" where a.ID_SUBURUSANSTATUS="+ idSuburusanstatus +
			" and p.ID_PERMOHONAN=a.ID_PERMOHONAN ";

		 if(idNegeri != "0"){
			 sql +=" AND p.id_negerimhn="+idNegeri;
		 }
		 		if(strQuery!=null){
		 			sql +=" AND (a.tarikh_kemaskini-a.tarikh_masuk) ";
					 if(strQuery.equals("8"))	
						 sql +="< 8";
					 else if(strQuery.equals("15"))
						 sql +=" >7 AND (a.tarikh_kemaskini-a.tarikh_masuk)<15";
					 else if(strQuery.equals("16"))
						 sql +="> 14";
					 else if(strQuery.equals("5"))
						 sql +="< 5";
					 else if(strQuery.equals("5-14"))
						 sql +=" >4 AND (a.tarikh_kemaskini-a.tarikh_masuk)<15";
					 else if(strQuery.equals("30"))
						 sql +="<31";
					 else if(strQuery.equals("60"))
						 sql +=" >30 AND (a.tarikh_kemaskini-a.tarikh_masuk)<61";
					 else if(strQuery.equals("61"))
						 sql +="> 60";			 
					 else{	}
		 		}else{}
			 //sql +="),0) menunggu " +
			 //		" from tblppkpermohonan p,tblpfdfail f  " +
			 //		" where  f.ID_FAIL = p.ID_FAIL ";
			 try {
			 db = new Db();
		      Statement stmt = db.getStatement();
			  //mylog.info("FrmKPIData::getKPIGiliranTerperinciCount("+idSuburusanstatus+","+strQuery+","+idNegeri+")::sql = "+sql);
			  ResultSet rs = stmt.executeQuery(sql);
		      while (rs.next()) {
			    returnValue = rs.getInt("Menunggu");
		      }
		      return returnValue;
		    } finally {
		      if (db != null) db.close();
		    }
		  }	 


	 public static Vector<Hashtable<String, Comparable>> getKPIGiliranTerperinci(String idSuburusanstatus,String strQuery,String idNegeri,String idPejabat,String tarikhMula,String tarikhTamat)throws Exception {
		 Db db = null;
		 String sql = " SELECT DISTINCT(f.no_fail),f.tajuk_fail,round((a.tarikh_kemaskini-a.tarikh_masuk)+1) menunggu,a.tarikh_masuk,a.tarikh_kemaskini" +
		 " from tblrujsuburusanstatusfail a,tblkpistatus ist,tblppkpermohonan p,tblpfdfail f ";
		 if(idPejabat!="0"){
			 sql += " ,tblrujpejabaturusan rpu,tblrujpejabatjkptg rpj ";
		 }
		 sql +=" where " +
		 " f.ID_FAIL = p.ID_FAIL "+
		 " AND p.ID_PERMOHONAN=a.ID_PERMOHONAN"+
		 " AND a.id_suburusanstatus = ist.id_suburusanstatus(+) " +
		 " AND a.ID_SUBURUSANSTATUS="+ idSuburusanstatus +
		 " AND f.id_negeri="+ idNegeri +
		 " AND (a.tarikh_kemaskini-a.tarikh_masuk) ";
			 if(strQuery.equals("8"))	
				 sql +="< 8";
			 else if(strQuery.equals("15"))
				 sql +=" >7 AND (a.tarikh_kemaskini-a.tarikh_masuk)<15";
			 else if(strQuery.equals("16"))
				 sql +="> 14";
			 else if(strQuery.equals("5"))
				 sql +="< 5";
			 else if(strQuery.equals("5-14"))
				 sql +=" >4 AND (a.tarikh_kemaskini-a.tarikh_masuk)<15";
			 else if(strQuery.equals("30"))
				 sql +="<31";
			 else if(strQuery.equals("60"))
				 sql +=" >30 AND (a.tarikh_kemaskini-a.tarikh_masuk)<61";
			 else if(strQuery.equals("61"))
				 sql +="> 60";			 
			 else{	}
			 if(!tarikhMula.equals("0") && !tarikhTamat.equals("0")){
				 sql += " AND to_Char(a.tarikh_kemaskini,'dd/mm/yyyy') between '"+tarikhMula+"' AND '"+tarikhTamat+"'";
		     }
			 if(idPejabat!="0"){
				 //--by UNIT PUSAKA
				 sql += " AND p.ID_DAERAHMHN=rpu.ID_DAERAHURUS "+
				 " AND rpu.ID_PEJABATJKPTG=rpj.ID_PEJABATJKPTG "+
				 " AND rpj.ID_SEKSYEN=2 AND rpj.ID_PEJABATJKPTG="+idPejabat;
			 }
			 try {
				 db = new Db();
				 Statement stmt = db.getStatement();
				 //mylog.info("FrmKPIData::getKPIGiliranTerperinci("+idSuburusanstatus+","+strQuery+","+idNegeri+","+ idPejabat+","+tarikhMula+","+tarikhTamat+")::sql = "+sql);
				 ResultSet rs = stmt.executeQuery(sql);
			     Vector<Hashtable<String, Comparable>> list = new Vector<Hashtable<String, Comparable>>();
			     while (rs.next()) {
			        Hashtable<String, Comparable> cb = new Hashtable<String, Comparable>();
				    cb.put("nofail", rs.getString("no_fail")==null?"TIADA":rs.getString("no_fail"));
				    cb.put("tajukfail", rs.getString("tajuk_fail")==null?"TIADA":rs.getString("tajuk_fail"));
				    cb.put("menunggu", rs.getString("Menunggu")==null?0:rs.getInt("Menunggu"));
		    		cb.put("tarikhmasuk", Util.getDateTime(rs.getDate("tarikh_masuk"), "dd/MM/yyyy"));
		    		cb.put("tarikhkemaskini", Util.getDateTime(rs.getDate("tarikh_kemaskini"), "dd/MM/yyyy"));
				    list.addElement(cb);
			     }
			     return list;
			    } finally {
			      if (db != null) db.close();
			    }
		  }	 

	 public static void tambah(String kod,String keterangan, String id_masuk) throws Exception {
		  Db db = null;
		  String sql = "";
		  String strTarikhSemasa="";
		  strTarikhSemasa = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");

		  String tarikhMasuk = "to_date('" + strTarikhSemasa + "','dd/MM/yyyy')";
		  long Id_KPIKeterangan= DB.getNextID("TBLSEMAKAN_SEQ");

		  try {
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.add("id_semakan", Id_KPIKeterangan);
			  r.add("kod_semak", kod);
			  r.add("perihal", keterangan);
			  r.add("id_masuk", id_masuk);
			  r.add("tarikh_masuk", r.unquote(tarikhMasuk));
			  r.add("id_kemaskini", id_masuk);
			  r.add("tarikh_kemaskini", r.unquote(tarikhMasuk));

			  sql = r.getSQLInsert("TBLSEMAKAN");
//			  System.out.println("FrmKPIData::Insert::TBLKPIKETERANGAN = "+sql);
			  stmt.executeUpdate(sql);
			  
		  } finally {
			  if (db != null) db.close();
		  }
	  }

	  public static void kemaskini(String idKpiketerangan, String keterangan, String idKemaskini) throws Exception {
		  Db db = null;
		  String sql = "";
		  String strTarikhSemasa="";
		  strTarikhSemasa =  lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");

		  String tarikhKemaskini = "to_date('" + strTarikhSemasa + "','dd/MM/yyyy')";
		
		  try {
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.clear();
			  r.update("id_semakan", idKpiketerangan);
			  r.add("perihal", keterangan);
			  r.add("id_kemaskini", idKemaskini);
			  r.add("tarikh_kemaskini", r.unquote(tarikhKemaskini));
			  sql = r.getSQLUpdate("tblsemakan");
			  stmt.executeUpdate(sql);
	    }finally {
	      if (db != null) db.close();
	    }
	  }

	  public static void hapus(String idKpiketerangan) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql = "delete from tblsemakan where id_semakan = " + idKpiketerangan;
	      stmt.executeUpdate(sql);
	    }finally {
	      if (db != null) db.close();
	    }
	    
	  }
	  
	  public static void simpanKeteranganVS(Hashtable h) throws Exception {
		  Db db = null;
		  String sql = "";
		  String strTarikhSemasa="";
		  strTarikhSemasa = Util.getDateTime(new Date(), "dd/MM/yyyy");

		  String tarikhMasuk = "to_date('" + strTarikhSemasa + "','dd/MM/yyyy')";
		  long Id_KPIUrusan= DB.getNextID("TBLSEMAKANSENARAI_SEQ");
		  
		  try {
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.add("id_semakansenarai", Id_KPIUrusan);
			  r.add("id_semakan", r.unquote((String)h.get("idKeterangan")));
			  r.add("id_urusan", r.unquote((String)h.get("idUrusan")));
			  if(!String.valueOf(h.get("idSubUrusan")).equals(""))
				  r.add("id_suburusan", r.unquote((String)h.get("idSubUrusan")));
			  
			  r.add("aturan", h.get("seq"));		  
			  r.add("kod_form", h.get("txtSkrin"));
			  
			  r.add("id_masuk", h.get("idmasuk"));
			  r.add("tarikh_masuk", r.unquote(tarikhMasuk));
			  r.add("id_kemaskini", h.get("idmasuk"));
			  r.add("tarikh_kemaskini", r.unquote(tarikhMasuk));

			  sql = r.getSQLInsert("TBLSEMAKANSENARAI");
			  //System.out.println("FrmKPIData::simpanKeteranganVS::TBLKPIURUSAN = "+sql);
			  stmt.executeUpdate(sql);
		  } finally {
			  if (db != null) db.close();
		  }
	  }
	  
	  public static void updateKeteranganvs(Hashtable<String,String> h) throws Exception {
		  Db db = null;
		  String sql = "";
		  String strTarikhSemasa="";
		  strTarikhSemasa = Util.getDateTime(new Date(), "dd/MM/yyyy");

		  String tarikhMasuk = "to_date('" + strTarikhSemasa + "','dd/MM/yyyy')";
		  long Id_KPIUrusan= Long.parseLong((String)h.get("idSenarai"));
		  try {
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_semakansenarai", Id_KPIUrusan);
			  r.add("id_semakan", r.unquote((String)h.get("idKeterangan")));
			  r.add("id_urusan", r.unquote((String)h.get("idUrusan")));
			  if(!String.valueOf(h.get("idSubUrusan")).equals(""))
				  r.add("id_suburusan", r.unquote((String)h.get("idSubUrusan")));
			  
			  r.add("aturan", h.get("seq"));		  
			  r.add("kod_form", h.get("txtSkrin"));
			  r.add("id_kemaskini", h.get("idKemaskini"));
			  r.add("tarikh_kemaskini", r.unquote(tarikhMasuk));

			  sql = r.getSQLUpdate("TBLSEMAKANSENARAI");
			  //System.out.println("FrmKPIData::upadateKeteranganvs::TBLKPIURUSAN = "+sql);
			  stmt.executeUpdate(sql);
			  
		  } finally {
			  if (db != null) db.close();
		  }
	  }
	  
	  public static void deleteVS(String idKpiketerangan)throws Exception {
		  Db db = null;
		  String sql = "";
		  
		  try {
			 db = new Db();
			 Statement stmt = db.getStatement();
			 sql = "delete from TBLSEMAKANSENARAI where id_semakansenarai = " + idKpiketerangan;
			 stmt.executeUpdate(sql);
		    
		  }finally {
			  if (db != null) db.close();
		  }
	  
	  }
	  
	  public static void simpanJenisDokumen(Hashtable<String,String> h) throws Exception {
		  Db db = null;
		  String sql = "";
		  String strTarikhSemasa="";
		  strTarikhSemasa = Util.getDateTime(new Date(), "dd/MM/yyyy");

		  String tarikhMasuk = "to_date('" + strTarikhSemasa + "','dd/MM/yyyy')";
		  long Id_KPIStatus= DB.getNextID("TBLSEMAKANJENISDOKUMEN_SEQ");

		  try {
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.add("id_semakanjenisdokumen", Id_KPIStatus);
			  r.add("id_semakan", r.unquote((String)h.get("idSemakan")));
			  r.add("id_jenisdokumen", r.unquote((String)h.get("idJenis")));
			  	  
			  r.add("id_masuk", h.get("idMasuk"));
			  r.add("tarikh_masuk", r.unquote(tarikhMasuk));
			  r.add("id_kemaskini", h.get("idMasuk"));
			  r.add("tarikh_kemaskini", r.unquote(tarikhMasuk));

			  sql = r.getSQLInsert("TBLSEMAKANJENISDOKUMEN");
			  myLog.info("simpanJenisDokumen("+h+"):sql = "+sql);
			  stmt.executeUpdate(sql);
			  
		  } finally {
			  if (db != null) db.close();
		  }
	  }
	  
	  public static void updateJenisDokumen(String idKpiketerangan
			 ,String idJenis
			 ,String idSemakan
			 ,String idKemaskini) throws Exception {
		  Db db = null;
		  String sql = "";
		  String strTarikhSemasa="";
		  strTarikhSemasa = Util.getDateTime(new Date(), "dd/MM/yyyy");

		  String tarikhKemaskini = "to_date('" + strTarikhSemasa + "','dd/MM/yyyy')";
		 
		  try {
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.clear();
			  r.update("id_semakanjenisdokumen", idKpiketerangan);
			  r.add("id_semakan", r.unquote(idSemakan));
			  r.add("id_jenisdokumen", r.unquote(idJenis));
			  r.add("id_kemaskini", idKemaskini);
			  r.add("tarikh_kemaskini", r.unquote(tarikhKemaskini));
			  sql = r.getSQLUpdate("TBLSEMAKANJENISDOKUMEN");
			  stmt.executeUpdate(sql);
	    }finally {
	      if (db != null) db.close();
	    }
	  }
	  
	  public static void deleteStatus(String idkpistatus)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "delete from TBLKPISTATUS where id_kpistatus = " + idkpistatus;
		      stmt.executeUpdate(sql);
		    }finally {
		      if (db != null) db.close();
		    }
		  }
	  
		public static Vector<Tblrujurusan> getUrusanSeksyen(String idSeksyen) throws Exception {
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("u.id_urusan");
				r.add("u.kod_urusan");
				r.add("nama_urusan");
				  
				r.add("s.id_urusan", r.unquote("u.id_urusan"));
				if(idSeksyen != null)
					r.add("s.id_seksyen", r.unquote(idSeksyen));
				
				sql = r.getSQLSelect("tblrujurusan u,tblrujurusanseksyen s","lpad(u.kod_urusan,10)");
				//System.out.println("FrmKPIData::getUrusanSeksyen()"+idSeksyen+"::sql = "+sql);

				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujurusan> v = new Vector<Tblrujurusan>();
				Tblrujurusan u = null;
				while (rs.next()) {
					u = new Tblrujurusan();
					u.setIdUrusan(rs.getLong("id_urusan"));
					u.setKodUrusan(rs.getString("kod_urusan"));
					u.setNamaUrusan(rs.getString("nama_urusan"));
					v.addElement(u);
				}
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}

		public static Vector<Hashtable<String, String>> getStatusFailBySuburusan(String idUrusan,String idSuburusan) throws Exception {
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("rsu.id_suburusan");
				r.add("rsu.nama_suburusan");
				r.add("rsus.id_suburusanstatus");
				r.add("rs.keterangan");
				r.add("rs.id_status");
					
				r.add("rsus.id_status", r.unquote("rs.id_status"));
				r.add("rsus.id_suburusan", r.unquote("rsu.id_suburusan"));
				//r.add("rsus.id_urusan", r.unquote("rsu.id_suburusan"));
				r.add("rsu.id_urusan", r.unquote(idUrusan));
				if(idSuburusan!=null)
					r.add("rsu.id_suburusan", r.unquote(idSuburusan));

				sql = r.getSQLSelect("tblrujstatus rs, tblrujsuburusanstatus rsus,tblrujsuburusan rsu","rsu.id_suburusan,rs.id_status");
				//System.out.println("FrmKPIData::getStatusFailBySuburusan("+ idUrusan+","+idSuburusan+")::sql = "+sql);

	
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();
				Hashtable<String, String> s = null;
				while (rs.next()) {
					s = new Hashtable<String, String>();
					s.put("idsuburusanstatus",rs.getString("id_suburusanstatus"));
//					s.put("idsuburusanstatus",rs.getLong("id_suburusanstatus"));
					s.put("namasuburusan",rs.getString("nama_suburusan"));
					s.put("status",rs.getString("keterangan"));
					v.addElement(s);
				}
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	
		
		public static Vector<Hashtable<String, Comparable>> getNegeriKPI(String idUrusan,String idSuburusan,String tarikhMula,String tarikhTamat) throws Exception {
			Db db = null;
			String sqlRangedate="";
			 if(!tarikhMula.equals("0") && !tarikhTamat.equals("0")){
				 sqlRangedate = " AND to_Char(P.TARIKH_MOHON,'dd/mm/yyyy') between '"+tarikhMula+"' AND '"+tarikhTamat+"'";
		     }
			 //mylog.info("getNegeriKPI("+idSuburusan+","+tarikhMula+","+tarikhTamat+"):sqlRangedate::"+sqlRangedate);

			String sql = "SELECT DISTINCT(rn.id_Negeri),rn.kod_Negeri,rn.nama_Negeri,rn.KOD_MAMPU "+
		    	" ,(SELECT count(*) FROM TBLPPKPERMOHONAN P WHERE P.ID_NEGERIMHN = RN.id_Negeri " +
				" "+sqlRangedate +		    	
		    	") BILPERMOHONAN "+
		    	" ,(SELECT NVL(sum(NVL(sum(round((rsf.tarikh_kemaskini-rsf.tarikh_masuk)+1)),0)),0) HARISEBENAR "+
		    	"		from tblkpistatus kps1,tblrujsuburusanstatusfail rsf,tblppkpermohonan P "+ 
		    	" 		WHERE kps1.id_suburusanstatus = rsf.id_suburusanstatus "+
		    	" 		AND rsf.id_permohonan=P.id_permohonan AND P.id_negerimhn = RN.id_negeri "+
				" "+sqlRangedate +		    	
		    	" 		GROUP BY  id_kpiurusan)  HARISEBENAR "+
		    	" ,(SELECT NVL(sum(NVL(sum(round((rsf.tarikh_kemaskini-rsf.tarikh_masuk)+1)),0)),0) jumlahhari  "+            
		    	" from tblkpistatus kps1,tblrujsuburusanstatusfail rsf,tblppkpermohonan P  "+              
		    	" WHERE kps1.id_suburusanstatus = rsf.id_suburusanstatus AND rsf.id_permohonan=P.id_permohonan  "+     
		    	" AND P.id_negerimhn = RN.id_negeri  AND P.id_permohonan  "+      
		    	" in ( SELECT pkp.ID_PERMOHONAN  from tblrujsuburusanstatusfail rsusf, tblppkpermohonan pkp  "+      
		    	" , (SELECT  rsus.ID_SUBURUSANSTATUS ID_SUBURUSANSTATUS from  tblrujsuburusanstatus rsus  "+    
		    	" ,(SELECT max( rsus.langkah)langkah,rsus.ID_SUBURUSAN id_suburusan from tblrujsuburusanstatus rsus  "+                             
		    	"  WHERE rsus.ID_SUBURUSAN="+idSuburusan+" GROUP BY  rsus.ID_SUBURUSAN) maxi  "+                        
		    	" WHERE rsus.ID_SUBURUSAN=maxi.ID_SUBURUSAN AND rsus.langkah=maxi.langkah) A     "+           
		    	" WHERE pkp.ID_PERMOHONAN=rsusf.ID_PERMOHONAN AND  rsusf.id_suburusanstatus=A.ID_SUBURUSANSTATUS     "+       
		    	" AND P.ID_NEGERIMHN=RN.id_negeri ) " +
				" "+sqlRangedate +
		    	" GROUP BY  kps1.ID_KPIURUSAN,P.ID_PERMOHONAN) HARISEBENARSIAP  "+    
		    	" ,( SELECT sum(kpu.sasaran_masa) "+
		    	" 		from tblkpiurusan kpu where kpu.id_suburusan=FDF.ID_SUBURUSAN "+
		    	" 	) HARISASARAN "+
		    	" ,( SELECT sum(kpu.sasaran_masa) from tblkpiurusan kpu " +
		    	" where kpu.id_suburusan=FDF.ID_SUBURUSAN and kpu.status_giliran=1) HARISASARANDALAMAN "+
		    	" ,( SELECT count(*)  from tblrujsuburusanstatusfail rsusf, tblppkpermohonan P "+
		    	" , (SELECT  rsus.ID_SUBURUSANSTATUS ID_SUBURUSANSTATUS from  tblrujsuburusanstatus rsus     "+  
		    	" ,		(SELECT max( rsus.langkah)langkah,rsus.ID_SUBURUSAN id_suburusan   "+
		    	" 			from tblrujsuburusanstatus rsus   "+        
		    	" 			where rsus.ID_SUBURUSAN="+idSuburusan+" GROUP BY  rsus.ID_SUBURUSAN) maxi    "+   
		    	" 		where rsus.ID_SUBURUSAN=maxi.ID_SUBURUSAN     AND rsus.langkah=maxi.langkah) A "+          
		    	" 	WHERE P.ID_PERMOHONAN=rsusf.ID_PERMOHONAN "+
		    	"  	AND  rsusf.id_suburusanstatus=A.ID_SUBURUSANSTATUS "+
		    	"  	AND P.ID_NEGERIMHN=RN.id_Negeri "+
				" "+sqlRangedate +
		    	"  ) BILANGANSELESAI "+
				" ,CASE  "+
				"  WHEN rn.id_negeri=5 then 'N.SEMBILAN' "+
				"   WHEN rn.id_negeri=14 then 'W.P.K.LUMPUR' "+
				"   WHEN rn.id_negeri=16 then 'W.P.PUTRAJAYA' "+
				"   WHEN rn.id_negeri=15 then 'W.P.LABUAN' "+
				"    WHEN rn.id_negeri=7 then 'P.PINANG' "+
				"     ELSE rn.nama_Negeri "+
				"   END negeripendek "+
		    	" from tblrujnegeri RN,TBLPFDFAIL FDF "+
		    	" WHERE FDF.ID_NEGERI(+)=RN.ID_NEGERI "+
		    	" AND FDF.ID_SUBURUSAN="+idSuburusan+
		    	" AND rn.KOD_NEGERI NOT IN ('99','00') "+
		    	" ORDER BY rn.KOD_MAMPU";
			
/*			String sql1 = " SELECT DISTINCT(rn.id_Negeri),rn.kod_Negeri,"+
				" rn.nama_Negeri,rn.kod_mampu,count(*) bilpermohonan, "+
				" (SELECT NVL(sum(NVL(sum(round((rsf.tarikh_kemaskini-rsf.tarikh_masuk)+1)),0)),0) hari "+
				" from tblkpistatus kps1,tblrujsuburusanstatusfail rsf,tblppkpermohonan ppkp  "+
				" where kps1.id_suburusanstatus = rsf.id_suburusanstatus "+
				" AND rsf.id_permohonan=ppkp.id_permohonan AND ppkp.id_negerimhn = f.id_negeri "+
				" GROUP BY  id_kpiurusan)  HARISEBENAR "+
				" ,( SELECT sum(kpu.sasaran_masa) "+
				" from tblkpiurusan kpu where kpu.id_suburusan=f.id_Suburusan "+
				"  "+
				" ) HARISASARAN "+
				" from tblrujnegeri rn,tblpfdfail f "+
				" where f.id_negeri=rn.ID_NEGERI "+
				" AND f.id_Suburusan="+idSuburusan+
				" GROUP BY rn.id_Negeri,rn.kod_Negeri,rn.nama_Negeri,rn.kod_mampu, f.id_negeri "+
				" ,f.id_Suburusan "+
				" ORDER BY rn.KOD_MAMPU";	*/
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				//mylog.info("getNegeriKPI("+idUrusan+","+idSuburusan+","+tarikhMula+","+tarikhTamat+"):sql::"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
				Hashtable<String, Comparable> s = null;
				while (rs.next()) {
					s = new Hashtable<String, Comparable>();
					s.put("idnegeri",rs.getInt("id_Negeri"));
					s.put("negeri_",rs.getString(3));
					s.put("permohonan",rs.getInt("BILPERMOHONAN"));
					s.put("harisebenar",rs.getDouble("HARISEBENAR"));
					s.put("harisasaran",rs.getDouble("HARISASARAN"));
					s.put("selesai",rs.getInt("BILANGANSELESAI"));
					s.put("negeri",rs.getString("negeripendek"));
					s.put("harisebenarsiap",rs.getDouble("harisebenarsiap"));
					s.put("harisasarandalaman",rs.getDouble("harisasarandalaman"));
					v.addElement(s);
				}
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		public static Vector<Hashtable<String, Comparable>> getMengikutPejabat(String idNegeri,String idSuburusan
				,String tarikhMula,String tarikhTamat) throws Exception {
			Db db = null;
			String sqlRangedate = "";
			 if(!tarikhMula.equals("0") && !tarikhTamat.equals("0")){
				 sqlRangedate = " AND to_Char(P.TARIKH_MOHON,'dd/mm/yyyy') between '"+tarikhMula+"' AND '"+tarikhTamat+"'";
		     }
			 //mylog.info("getMengikutPejabat("+idNegeri+","+idSuburusan+","+tarikhMula+","+tarikhTamat+"):sqlRangedate::"+sqlRangedate);
		 
			String sql = " SELECT rpj.id_pejabatjkptg,rpj.nama_pejabat,rpj.alamat1 "+
			" ,(SELECT count(*) "+
			" FROM TBLPPKPERMOHONAN P,TBLRUJPEJABATURUSAN rpu  "+
			" WHERE P.ID_DAERAHMHN = rpu.ID_DAERAHURUS  "+
			" AND rpu.ID_PEJABATJKPTG=RPJ.ID_PEJABATJKPTG  "+
			" "+sqlRangedate +
			" ) BILPERMOHONAN "+		
			" ,(SELECT NVL(sum(NVL(sum(round((rsf.tarikh_kemaskini-rsf.tarikh_masuk)+1)),0)),0) HARISEBENARI "+
			" FROM tblkpistatus kps1,tblrujsuburusanstatusfail rsf,tblppkpermohonan P,TBLRUJPEJABATURUSAN rpu "+
			" WHERE kps1.id_suburusanstatus = rsf.id_suburusanstatus "+
			" AND rsf.id_permohonan=P.id_permohonan AND P.ID_DAERAHMHN = rpu.ID_DAERAHURUS "+
			" AND rpu.ID_PEJABATJKPTG=RPJ.ID_PEJABATJKPTG  "+
			" "+sqlRangedate +
			" GROUP BY  id_kpiurusan)  HARISEBENAR "+               
			" ,(SELECT NVL(sum(NVL(sum(round((rsf.tarikh_kemaskini-rsf.tarikh_masuk)+1)),0)),0) jumlahhari "+
			" FROM tblkpistatus kps1,tblrujsuburusanstatusfail rsf,tblppkpermohonan P  "+
			" WHERE kps1.id_suburusanstatus = rsf.id_suburusanstatus AND rsf.id_permohonan=P.id_permohonan AND P.id_permohonan "+
			" in ( SELECT pkp.ID_PERMOHONAN from tblrujsuburusanstatusfail rsusf,tblppkpermohonan pkp,TBLRUJPEJABATURUSAN rpu "+
			" , (SELECT  rsus.ID_SUBURUSANSTATUS ID_SUBURUSANSTATUS from  tblrujsuburusanstatus rsus  "+
			" ,(SELECT max( rsus.langkah)langkah,rsus.ID_SUBURUSAN id_suburusan from tblrujsuburusanstatus rsus  "+     
			" WHERE rsus.ID_SUBURUSAN=59 GROUP BY  rsus.ID_SUBURUSAN) maxi "+       
			" WHERE rsus.ID_SUBURUSAN=maxi.ID_SUBURUSAN AND rsus.langkah=maxi.langkah) A "+           
			" WHERE pkp.ID_PERMOHONAN=rsusf.ID_PERMOHONAN AND  rsusf.id_suburusanstatus=A.ID_SUBURUSANSTATUS  "+               
			" AND pkp.ID_DAERAHMHN = rpu.ID_DAERAHURUS AND rpu.ID_PEJABATJKPTG=RPJ.ID_PEJABATJKPTG)  "+       
			" "+sqlRangedate +
			" GROUP BY  kps1.ID_KPIURUSAN,P.ID_PERMOHONAN ) HARISIAPSEBENAR"+     
			" ,( SELECT sum(kpu.sasaran_masa) from tblkpiurusan kpu where kpu.id_suburusan="+idSuburusan+"  ) HARISASARAN "+ 
			" ,( SELECT sum(kpu.sasaran_masa) from tblkpiurusan kpu where kpu.id_suburusan="+idSuburusan+"  "+ 
			" AND kpu.status_giliran=1) HARISASARANDALAMAN "+
			" ,(SELECT count(*)  from tblrujsuburusanstatusfail rsusf, tblppkpermohonan P,TBLRUJPEJABATURUSAN rpu "+
			" ,(SELECT  rsus.ID_SUBURUSANSTATUS ID_SUBURUSANSTATUS "+
			" 	    FROM  tblrujsuburusanstatus rsus "+
			" 	    ,(SELECT max( rsus.langkah)langkah,rsus.ID_SUBURUSAN id_suburusan "+
			" 	    from tblrujsuburusanstatus rsus where rsus.ID_SUBURUSAN="+idSuburusan+" GROUP BY  rsus.ID_SUBURUSAN) maxi "+
			" 	    where rsus.ID_SUBURUSAN=maxi.ID_SUBURUSAN AND rsus.langkah=maxi.langkah) A  "+
			" 	    WHERE P.ID_PERMOHONAN=rsusf.ID_PERMOHONAN AND rsusf.id_suburusanstatus=A.ID_SUBURUSANSTATUS "+
			" 	    AND P.ID_DAERAHMHN = rpu.ID_DAERAHURUS AND rpu.ID_PEJABATJKPTG=RPJ.ID_PEJABATJKPTG "+
			" "+sqlRangedate +
			" 	    ) BILANGANSELESAI "+
			" FROM tblrujpejabatjkptg RPJ,tblrujnegeri RN "+
			" WHERE rn.id_negeri=rpj.id_negeri and rpj.id_seksyen=2 "+
			" AND rpj.id_negeri ="+idNegeri+
			" AND rpj.ID_JENISPEJABAT=22 "+
			" ORDER BY rpj.nama_pejabat ";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				//mylog.info("getMengikutPejabat:sql::"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
				Hashtable<String, Comparable> s = null;
				while (rs.next()) {
					s = new Hashtable<String, Comparable>();
					s.put("idpejabat",rs.getInt(1)); //id_pejabatjkptg
					s.put("namapejabat",rs.getString(2));//nama_pejabat
					s.put("permohonan",rs.getInt(4)); //BILPERMOHONAN
					//s.put("harisasaran",rs.getDouble(5));
					//s.put("harisebenar",rs.getDouble(6));
					s.put("harisebenar",rs.getDouble("HARISEBENAR"));
					s.put("harisasaran",rs.getDouble("HARISASARAN"));
					s.put("selesai",rs.getInt("BILANGANSELESAI"));
					s.put("harisiapsebenar",rs.getDouble("HARISIAPSEBENAR"));
					s.put("harisasarandalaman",rs.getDouble("HARISASARANDALAMAN"));

					/*if(rs.getInt("bilanganselesai")==0){
						s.put("kecekapan","0.0");					
					}else{
						s.put("kecekapan",rs.getString(7));					
					}
					if(rs.getInt("bilanganselesai")==0){
						s.put("purata","0.0");					
					}else{
						s.put("purata",rs.getString(8));
					}
					s.put("selesai",rs.getInt("bilanganselesai"));
					*/v.addElement(s);
				}
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}

		 
		 public static int getBilanganSuburusanStatusByNegeri(String idSuburusanStatus,String idSuburusan,String idNegeri,
				 String idpejabat)throws Exception {
			 int returnValue=0;
			 Db db = null;
			 String sql = " SELECT count(*) " +
			  		" FROM tblrujsuburusanstatusfail rsf,tblppkpermohonan p,tblpfdfail f"+
			 		" WHERE " +
			 		" rsf.id_suburusanstatus = " + idSuburusanStatus +
			 		" AND rsf.ID_PERMOHONAN=p.ID_PERMOHONAN " +
			 		" AND p.ID_FAIL=f.ID_FAIL " +
			 		" AND f.id_suburusan="+ idSuburusan +
			 		" AND p.ID_NEGERIMHN= "+ idNegeri +
			 		" ";
			 try {
				 db = new Db();
				 Statement stmt = db.getStatement();
			   	 myLog.info("FrmKPIData::getKPIKeberkesananByNegeri::sql = "+sql);
				 ResultSet rs = stmt.executeQuery(sql);
			     while (rs.next()) {	returnValue = rs.getInt(1);	}
			     return returnValue;
			    } finally {
			      if (db != null) db.close();
			    }
			  }
		 
		 public static Vector<Hashtable<String, Comparable>> getKeteranganTerperinciByNegeri(String idUrusan,String idSuburusan,String idNegeri)throws Exception {
			 Db db = null;
			 String sql = "";
			 try {
				 db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("kpk.id_kpiketerangan");
			      r.add("kpk.keterangan");
			      r.add("kpu.sasaran_masa");
			      r.add("kpu.jenis_sasaran");
			      r.add("kpu.status_giliran");
			      r.add("kpu.seq");
			      r.add("kpu.id_kpiurusan");
			      r.add("count(rsf.id_suburusanstatus) aktiviti");
			      r.add("sum((rsf.tarikh_kemaskini-rsf.tarikh_masuk)) hari");		      
			      r.add("sum(((rsf.tarikh_kemaskini-rsf.tarikh_masuk)+1)) hari1");
			      
			      r.add("kpu.id_kpiketerangan",r.unquote("kpk.id_kpiketerangan"));
			      r.add("kpu.id_suburusan",r.unquote("rsu.id_suburusan"));
			      r.add("rsu.id_urusan",r.unquote("ru.id_urusan"));
			      r.add("rsu.id_suburusan",r.unquote(idSuburusan));
			      r.add("ru.id_urusan",r.unquote(idUrusan));
			      r.add("kpu.id_kpiurusan",r.unquote("kps.id_kpiurusan"));	         
			      r.add("kps.id_suburusanstatus",r.unquote("rsf.id_suburusanstatus"));		      
			      r.add("p.id_permohonan",r.unquote("rsf.id_permohonan"));		      
			      r.add("p.id_negerimhn",r.unquote(idNegeri));		      
					
			      sql = r.getSQLSelect("tblkpiketerangan kpk ,tblkpiurusan kpu ,tblrujsuburusan rsu,tblrujurusan ru,tblkpistatus kps,tblrujsuburusanstatusfail rsf,tblppkpermohonan p");
			      sql += " GROUP BY  kpk.id_kpiketerangan,kpk.keterangan,kpu.sasaran_masa,kpu.jenis_sasaran,kpu.tarikh_kemaskini,kpu.status_giliran,kpu.seq,kpu.id_kpiurusan ";
			      sql += " ORDER BY kpu.seq";
				  ResultSet rs = stmt.executeQuery(sql);
			      Vector<Hashtable<String, Comparable>> list = new Vector<Hashtable<String, Comparable>>();
			      while (rs.next()) {
			        Hashtable<String, Comparable> cb = new Hashtable<String, Comparable>();
				    cb.put("idkpiketerangan", rs.getLong("id_kpiketerangan"));
				    cb.put("keterangan",rs.getString("keterangan"));
			        cb.put("sasaranmasa",rs.getDouble("sasaran_masa"));
			        if(rs.getString("jenis_sasaran")=="1"){
			        	cb.put("jenissasaran","JAM");		        
			        	
			        }else{
			        	cb.put("jenissasaran","HARI");		        
			        }
			        cb.put("giliran",rs.getInt("status_giliran"));
			        cb.put("aktiviti",rs.getDouble("aktiviti"));
			        java.text.DecimalFormat df = new java.text.DecimalFormat("#,###,###.0");
			        cb.put("harif",df.format(rs.getDouble("hari")));
			        cb.put("hari1f",df.format(rs.getDouble("hari1")));
			        cb.put("hari",rs.getDouble("hari"));
			        cb.put("idkpiurusan",rs.getString("id_kpiurusan"));
			        cb.put("hari1",rs.getDouble("hari1"));
			        list.addElement(cb);
			      }
			      return list;
			    } finally {
			      if (db != null) db.close();
			    }
			  }

		 public static int getKecekapandalaman(String idUrusan,String idSuburusan,String idNegeri,
				 String idpejabat)throws Exception {
			 int returnValue=0;
			 Db db = null;
			 Vector<?> v = null;
			 //getKeteranganTerperinciByNegeri(String idUrusan,String idSuburusan,String idNegeri)throws Exception {
			 v = (Vector<?>)getKeteranganTerperinciByNegeri(idUrusan,idSuburusan,idNegeri);
			 
			 try {
			     return returnValue;
			    } finally {
			      if (db != null) db.close();
			    }
			  }
		 
		 public static int getBilanganMenunggu(String idUrusan,String idSuburusan) throws Exception {
			 Db db = null;
			String sql = "SELECT DISTINCT(rn.id_Negeri),rn.kod_Negeri,rn.nama_Negeri,rn.KOD_MAMPU "+
			    	" ,(SELECT count(*) FROM TBLPPKPERMOHONAN P WHERE P.ID_NEGERIMHN = RN.id_Negeri ) BILPERMOHONAN "+
			    	" ,(SELECT NVL(sum(NVL(sum(round((rsf.tarikh_kemaskini-rsf.tarikh_masuk)+1)),0)),0) HARISEBENAR "+
			    	"		from tblkpistatus kps1,tblrujsuburusanstatusfail rsf,tblppkpermohonan ppkp "+ 
			    	" 		WHERE kps1.id_suburusanstatus = rsf.id_suburusanstatus "+
			    	" 		AND rsf.id_permohonan=ppkp.id_permohonan AND ppkp.id_negerimhn = RN.id_negeri "+
			    	" 		GROUP BY  id_kpiurusan)  HARISEBENAR "+
			    	" ,( SELECT sum(kpu.sasaran_masa) "+
			    	" 		from tblkpiurusan kpu where kpu.id_suburusan=FDF.ID_SUBURUSAN "+
			    	" 	) HARISASARAN "+
			    	" ,( SELECT count(*)  from tblrujsuburusanstatusfail rsusf, tblppkpermohonan pkp "+
			    	" , (SELECT  rsus.ID_SUBURUSANSTATUS ID_SUBURUSANSTATUS      from  tblrujsuburusanstatus rsus     "+  
			    	" ,		(SELECT max( rsus.langkah)langkah,rsus.ID_SUBURUSAN id_suburusan   "+
			    	" 			from tblrujsuburusanstatus rsus   "+        
			    	" 			where rsus.ID_SUBURUSAN="+idSuburusan+" GROUP BY  rsus.ID_SUBURUSAN) maxi    "+   
			    	" 		where rsus.ID_SUBURUSAN=maxi.ID_SUBURUSAN     AND rsus.langkah=maxi.langkah) A "+          
			    	" 	WHERE pkp.ID_PERMOHONAN=rsusf.ID_PERMOHONAN "+
			    	"  	AND  rsusf.id_suburusanstatus=A.ID_SUBURUSANSTATUS "+
			    	"  	AND pkp.ID_NEGERIMHN=RN.id_Negeri "+
			    	"  ) BILANGANSELESAI "+
			    	" from tblrujnegeri RN,TBLPFDFAIL FDF "+
			    	" WHERE FDF.ID_NEGERI(+)=RN.ID_NEGERI "+
			    	" AND FDF.ID_SUBURUSAN="+idSuburusan+
			    	" AND rn.KOD_NEGERI NOT IN ('99','00') "+
			    	" ORDER BY rn.KOD_MAMPU";
				

				try {
					db = new Db();
					Statement stmt = db.getStatement();
					myLog.info("getBilanganMenunggu:sql::"+sql);
					ResultSet rs = stmt.executeQuery(sql);
					int v = 0;
					while (rs.next()) {
						v = rs.getInt(1);
					}
					return v;
				} finally {
					if (db != null)
						db.close();
				}
			}
		 public static Vector<Hashtable<String, Comparable>> getKeteranganUntukUtama(String idUrusan,String idSuburusan)throws Exception {
			 Db db = null;
			 String sql = " " +
			 		" select kpk.id_kpiketerangan,kpk.keterangan,kpu.sasaran_masa,kpu.jenis_sasaran" +
			 		" ,kpu.status_giliran,kpu.seq,kps.ID_SUBURUSANSTATUS,kpu.id_kpiurusan,kpu.pilihan"+
			 		" ,(select count(distinct(rsf.ID_PERMOHONAN)) "+ 
			 		" from tblrujsuburusanstatusfail rsf,tblppkpermohonan p where  "+
			 		" rsf.ID_SUBURUSANSTATUS=kps.ID_SUBURUSANSTATUS  "+
			 		" and p.ID_PERMOHONAN=rsf.ID_PERMOHONAN  "+
			 		" ) aktiviti "+
			 		" ,to_char(NVL((select sum(max(rsf.tarikh_kemaskini)-min(rsf.tarikh_masuk))  "+
			 		" from tblrujsuburusanstatusfail rsf,tblppkpermohonan p where  "+
			 		" rsf.ID_SUBURUSANSTATUS=kps.ID_SUBURUSANSTATUS  "+
			 		" and p.ID_PERMOHONAN=rsf.ID_PERMOHONAN group by rsf.ID_PERMOHONAN "+
			 		" ),0),'9999990.9') hari "+
			 		" ,to_char(NVL((select sum((max(rsf.tarikh_kemaskini)-min(rsf.tarikh_masuk))+1)  "+
			 		" from tblrujsuburusanstatusfail rsf,tblppkpermohonan p where  "+
					" rsf.ID_SUBURUSANSTATUS=kps.ID_SUBURUSANSTATUS  "+
					" and p.ID_PERMOHONAN=rsf.ID_PERMOHONAN group by rsf.ID_PERMOHONAN "+
					" ),0),'9999990.9') hari1 "+
					" from tblkpiketerangan kpk ,tblkpiurusan kpu,tblkpistatus kps "+
					" where "+
					" kpu.id_kpiketerangan=kpk.id_kpiketerangan "+
					" and kpu.id_kpiurusan = kps.id_kpiurusan "+
					" and kpu.ID_SUBURUSAN='"+idSuburusan+"' "+
					" ORDER BY kpu.seq ";
		      myLog.info("getKeteranganUntukUtama("+idUrusan+","+idSuburusan+")::sql = "+sql);
			 try {
				 db = new Db();
			      Statement stmt = db.getStatement();
			      myLog.info("getKeteranganUntukUtama("+idUrusan+","+idSuburusan+")::sql = "+sql);
				  ResultSet rs = stmt.executeQuery(sql);
			      Vector<Hashtable<String, Comparable>> list = new Vector<Hashtable<String, Comparable>>();
			      double dblTemp =0L;
			      while (rs.next()) {
			        Hashtable<String, Comparable> cb = new Hashtable<String, Comparable>();
				    cb.put("idkpiketerangan", rs.getLong("id_kpiketerangan"));
				    cb.put("keterangan",rs.getString("keterangan"));
				    cb.put("keteranganheader",rs.getString("keterangan").substring(0, rs.getString("keterangan").length()-1));
			        cb.put("sasaranmasa",rs.getDouble("sasaran_masa"));
			        if(rs.getString("jenis_sasaran")=="1"){
			        	cb.put("jenissasaran","JAM");		        
			        	
			        }else{
			        	cb.put("jenissasaran","HARI");		        
			        }
			        cb.put("giliran",rs.getInt("status_giliran"));
			        cb.put("aktiviti",rs.getInt("aktiviti"));
			        java.text.DecimalFormat df = new java.text.DecimalFormat("#,###,###.0");
			        //cb.put("harif",df.format(rs.getDouble("hari")));
			        //cb.put("hari1f",df.format(rs.getDouble("hari1")));
			        cb.put("harif",rs.getString("hari"));
			        cb.put("hari1f",rs.getString("hari1"));
			        cb.put("hari",(rs.getString("hari")==null?Double.parseDouble("0.0"):rs.getDouble("hari")));
			        cb.put("idkpiurusan",rs.getString("id_kpiurusan"));
			        cb.put("hari1",rs.getDouble("hari1"));
			        if(rs.getInt("aktiviti")==0)
				        cb.put("puratanew","0.0");		        	
			        else{
			        	dblTemp = rs.getDouble("hari1")/(rs.getDouble("aktiviti"));
				        cb.put("puratanew",Utils.format1Decimal(dblTemp));		        	
			        }
			        if(rs.getInt("aktiviti")==0)
				        cb.put("peratusnew","0.0");		        	
			        else{
			        	double dblTempPeratus = (rs.getDouble("sasaran_masa")/dblTemp)*100;
				        cb.put("peratusnew",Utils.format1Decimal(dblTempPeratus));		        	
			        }		        	
				    cb.put("idsuburusanstatus", rs.getLong("id_suburusanstatus"));
			        cb.put("pilihan",rs.getInt("pilihan"));

			        list.addElement(cb);
			      }
			      return list;
			      
			    } finally {
			      if (db != null) db.close();
			      
			    }
		 }

		 public static Vector<Hashtable<String, Comparable>> getKeteranganUntukUtamaHTP(String idUrusan,String idSuburusan)throws Exception {
			 Db db = null;
			 String sql = " select kpk.id_kpiketerangan,kpk.keterangan,kpu.sasaran_masa,kpu.jenis_sasaran" +
			 		",kpu.status_giliran,kpu.seq,kps.ID_SUBURUSANSTATUS,kpu.id_kpiurusan,kpu.pilihan"+
			 	" ,(select count(distinct(rsf.ID_PERMOHONAN)) "+ 
			 " from tblrujsuburusanstatusfail rsf,TBLPERMOHONAN P WHERE  "+
			 " rsf.ID_SUBURUSANSTATUS=kps.ID_SUBURUSANSTATUS  "+
			 " AND P.ID_PERMOHONAN=rsf.ID_PERMOHONAN  "+
			 " ) AKTIVITI "+
			 " ,to_char(NVL((select sum(max(rsf.tarikh_kemaskini)-min(rsf.tarikh_masuk))  "+
			 " from tblrujsuburusanstatusfail rsf,TBLPERMOHONAN P WHERE  "+
			 " rsf.ID_SUBURUSANSTATUS=kps.ID_SUBURUSANSTATUS  "+
			 " and p.ID_PERMOHONAN=rsf.ID_PERMOHONAN group by rsf.ID_PERMOHONAN "+
			 " ),0),'9990.9') hari "+
			 " ,to_char(NVL((select sum((max(rsf.tarikh_kemaskini)-min(rsf.tarikh_masuk))+1)  "+
			 " from tblrujsuburusanstatusfail rsf,TBLPERMOHONAN P WHERE  "+
			 " rsf.ID_SUBURUSANSTATUS=kps.ID_SUBURUSANSTATUS  "+
			 " and p.ID_PERMOHONAN=rsf.ID_PERMOHONAN group by rsf.ID_PERMOHONAN "+
			 " ),0),'9990.9') hari1 "+
			 " from tblkpiketerangan KPK ,tblkpiurusan KPU,tblkpistatus KPS "+
			 " WHERE "+
			 " KPU.id_kpiketerangan = KPK.id_kpiketerangan "+
			 " and KPU.id_kpiurusan = KPS.id_kpiurusan "+
			 " and KPU.ID_SUBURUSAN = "+idSuburusan+
			 " ORDER BY KPU.SEQ ";
			 try {
				 db = new Db();
			      Statement stmt = db.getStatement();
			      //mylog.info("FrmKPIData::getKeteranganTerperinci("+idUrusan+","+idSuburusan+")::sql = "+sql);
				  ResultSet rs = stmt.executeQuery(sql);
			      Vector<Hashtable<String, Comparable>> list = new Vector<Hashtable<String, Comparable>>();
			      double dblTemp =0L;
			      while (rs.next()) {
			        Hashtable<String, Comparable> cb = new Hashtable<String, Comparable>();
				    cb.put("idkpiketerangan", rs.getLong("id_kpiketerangan"));
				    cb.put("keterangan",rs.getString("keterangan"));
				    cb.put("keteranganheader",rs.getString("keterangan").substring(0, rs.getString("keterangan").length()-1));
			        cb.put("sasaranmasa",rs.getDouble("sasaran_masa"));
			        if(rs.getString("jenis_sasaran")=="1"){
			        	cb.put("jenissasaran","JAM");		        
			        	
			        }else{
			        	cb.put("jenissasaran","HARI");		        
			        }
			        cb.put("giliran",rs.getInt("status_giliran"));
			        cb.put("aktiviti",rs.getInt("aktiviti"));
			        java.text.DecimalFormat df = new java.text.DecimalFormat("#,###,###.0");
			        //cb.put("harif",df.format(rs.getDouble("hari")));
			        //cb.put("hari1f",df.format(rs.getDouble("hari1")));
			        cb.put("harif",rs.getString("hari"));
			        cb.put("hari1f",rs.getString("hari1"));
			        cb.put("hari",rs.getDouble("hari"));
			        cb.put("idkpiurusan",rs.getString("id_kpiurusan"));
			        cb.put("hari1",rs.getDouble("hari1"));
			        if(rs.getInt("aktiviti")==0)
				        cb.put("puratanew","0.0");		        	
			        else{
			        	dblTemp = rs.getDouble("hari1")/(rs.getDouble("aktiviti"));
				        cb.put("puratanew",Utils.format1Decimal(dblTemp));		        	
			        }
			        if(rs.getInt("aktiviti")==0)
				        cb.put("peratusnew","0.0");		        	
			        else{
			        	double dblTempPeratus = (rs.getDouble("sasaran_masa")/dblTemp)*100;
				        cb.put("peratusnew",Utils.format1Decimal(dblTempPeratus));		        	
			        }		        	
				    cb.put("idsuburusanstatus", rs.getLong("id_suburusanstatus"));
			        cb.put("pilihan",rs.getInt("pilihan"));

			        list.addElement(cb);
			      }
			      return list;
			      
			 } finally {
				 if (db != null) db.close();
			 }
			 
		 }

		 public static Vector<Hashtable<String, Comparable>> getKPIGiliranTerperinciSenarai(
				 String idSuburusanstatus,String strQuery,String idNegeri,String txdMula
				 ,String txdAkhir)throws Exception {
			 Db db = null;
			 String sql = " SELECT f.no_fail,f.tajuk_fail,round((max(a.tarikh_kemaskini)-min(a.tarikh_masuk))+1) menunggu" +
			 		",min(a.tarikh_masuk) tarikh_masuk,max(a .tarikh_kemaskini) tarikh_kemaskini" +
			 		" from tblrujsuburusanstatusfail a,tblkpistatus ist,tblppkpermohonan p,tblpfdfail f " +
			 		" where " +
			 		" f.ID_FAIL = p.ID_FAIL "+
			 		" AND p.ID_PERMOHONAN=a.ID_PERMOHONAN"+
			 		" AND a.id_suburusanstatus = ist.id_suburusanstatus " +
			 		" AND a.ID_SUBURUSANSTATUS="+ idSuburusanstatus +
			 		" AND (a.tarikh_kemaskini-a.tarikh_masuk) ";
				 if(strQuery.equals("8"))	
					 sql +="< 8";
				 else if(strQuery.equals("15")){
					 //sql +=" >7 AND (max(a.tarikh_kemaskini)-min(a.tarikh_masuk))<15";
					 sql +=" >7 AND (a.tarikh_kemaskini-a.tarikh_masuk)<15";
				 }else if(strQuery.equals("16"))
					 sql +="> 14";
				 else if(strQuery.equals("5"))
					 sql +="< 5";
				 else if(strQuery.equals("5-14")){
					 //sql +=" >4 AND (max(a.tarikh_kemaskini)-min(a.tarikh_masuk))<15";
					 sql +=" >4 AND (a.tarikh_kemaskini-a.tarikh_masuk)<15";
				 }else if(strQuery.equals("30"))
					 sql +="<31";
				 else if(strQuery.equals("60")){
					 //sql +=" >30 AND (max(a.tarikh_kemaskini)-min(a.tarikh_masuk))<61";
					 sql +=" >30 AND (a.tarikh_kemaskini - a.tarikh_masuk)<61";
				 }else if(strQuery.equals("61"))
					 sql +="> 60";			 
				 else{	}
				 if(!idNegeri.equals("0")){
					 sql +=" and p.id_negerimhn="+idNegeri;
				 }
				 if(!txdMula.equals("0") && !txdMula.equals("0") ){
					 sql +=" and to_char(a.TARIKH_KEMASKINI) between '"+txdMula+"' and '"+txdAkhir+"'";
				 }else{}

				 sql +=" group by f.no_fail,f.tajuk_fail ";
				 try {
				 db = new Db();
			      Statement stmt = db.getStatement();
			      myLog.info(sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      Vector<Hashtable<String, Comparable>> list = new Vector<Hashtable<String, Comparable>>();
			      while (rs.next()) {
			        Hashtable<String, Comparable> cb = new Hashtable<String, Comparable>();
			    	cb.put("nofail", rs.getString("NO_FAIL")== null? "":rs.getString("NO_FAIL"));
			    	cb.put("tajukfail", rs.getString("tajuk_fail")== null? "":rs.getString("tajuk_fail"));
			    	cb.put("menunggu", rs.getString("Menunggu")== null? 0:rs.getInt("Menunggu"));
			    	cb.put("tarikhmasuk",rs.getDate("tarikh_masuk")== null? "":Util.getDateTime(rs.getDate("tarikh_masuk"), "dd/MM/yyyy"));
			    	cb.put("tarikhkemaskini",rs.getDate("tarikh_kemaskini")== null? "":Util.getDateTime(rs.getDate("tarikh_kemaskini"), "dd/MM/yyyy"));
				    list.addElement(cb);
			      }
			      return list;
			    } finally {
			      if (db != null) db.close();
			    }
			  }	 

		 
		 
		 public static int getKPIGiliranBilanganUtama(String idSuburusanstatus,String strQuery
				 ,String idNegeri,String txdMula,String txdAkhir)throws Exception {
			 Db db = null;
			 int returnValue=0;
			 String sql = " select count(distinct(a.ID_PERMOHONAN)) menunggu "+
			 	" from tblrujsuburusanstatusfail a,tblppkpermohonan p  "+
				" where a.ID_SUBURUSANSTATUS="+ idSuburusanstatus +
				" and p.ID_PERMOHONAN=a.ID_PERMOHONAN ";

			 //if(!idNegeri.equals("0")){
			 if(idNegeri != "0"){
				 sql +=" AND p.id_negerimhn="+idNegeri;
			 }
			 if(txdMula != "0" && txdAkhir!= "0" ){
				 sql +=" and to_char(a.TARIKH_KEMASKINI) between '"+txdMula+"' and '"+txdAkhir+"'";
			 }
			 if(strQuery!=null){
			 			sql +=" AND (a.tarikh_kemaskini-a.tarikh_masuk) ";
						 if(strQuery.equals("8"))	
							 sql +="< 8";
						 else if(strQuery.equals("15"))
							 sql +=" >7 AND (a.tarikh_kemaskini-a.tarikh_masuk)<15";
						 else if(strQuery.equals("16"))
							 sql +="> 14";
						 else if(strQuery.equals("5"))
							 sql +="< 5";
						 else if(strQuery.equals("5-14"))
							 sql +=" >4 AND (a.tarikh_kemaskini-a.tarikh_masuk)<15";
						 else if(strQuery.equals("30"))
							 sql +="<31";
						 else if(strQuery.equals("60"))
							 sql +=" >30 AND (a.tarikh_kemaskini-a.tarikh_masuk)<61";
						 else if(strQuery.equals("61"))
							 sql +="> 60";			 
						 else{	}
			 		}else{}
				 //sql +="),0) menunggu " +
				 //		" from tblppkpermohonan p,tblpfdfail f  " +
				 //		" where  f.ID_FAIL = p.ID_FAIL ";
				 try {
				 db = new Db();
			      Statement stmt = db.getStatement();
				  //mylog.info("FrmKPIData::getKPIGiliranBilanganUtama("+idSuburusanstatus+","+strQuery+","+idNegeri+")::sql = "+sql);
				  ResultSet rs = stmt.executeQuery(sql);
			      while (rs.next()) {
				    returnValue = rs.getInt("Menunggu");
			      }
			      return returnValue;
			    } finally {
			      if (db != null) db.close();
			    }
			  }	 

		 public static int getKPIGiliranBilanganUtamaHTP(String idSuburusanstatus,String strQuery
				 ,String idNegeri,String txdMula,String txdAkhir)throws Exception {
			 Db db = null;
			 int returnValue=0;
			 String sql = " SELECT COUNT(distinct(a.ID_PERMOHONAN)) MENUNGGU "+
			 	" FROM TBLRUJSUBURUSANSTATUSFAIL A,TBLPERMOHONAN P  "+
				" WHERE a.ID_SUBURUSANSTATUS = "+ idSuburusanstatus +
				" AND P.ID_PERMOHONAN = A.ID_PERMOHONAN ";

			 //if(!idNegeri.equals("0")){
			 if(idNegeri != "0"){
				 sql +=" AND P.id_negerimhn="+idNegeri;
			 }
			 if(txdMula != "0" && txdAkhir!= "0" ){
				 sql +=" AND to_char(A.TARIKH_KEMASKINI) between '"+txdMula+"' and '"+txdAkhir+"'";
			 }
			 if(strQuery!=null){
			 			sql +=" AND (A.tarikh_kemaskini-A.tarikh_masuk) ";
						 if(strQuery.equals("8"))	
							 sql +="< 8";
						 else if(strQuery.equals("15"))
							 sql +=" >7 AND (A.tarikh_kemaskini-A.tarikh_masuk)<15";
						 else if(strQuery.equals("16"))
							 sql +="> 14";
						 else if(strQuery.equals("5"))
							 sql +="< 5";
						 else if(strQuery.equals("5-14"))
							 sql +=" >4 AND (A.tarikh_kemaskini-A.tarikh_masuk)<15";
						 else if(strQuery.equals("30"))
							 sql +="<31";
						 else if(strQuery.equals("60"))
							 sql +=" >30 AND (A.tarikh_kemaskini-A.tarikh_masuk)<61";
						 else if(strQuery.equals("61"))
							 sql +="> 60";			 
						 else{	}
			 		}else{}
			 
			 try {
				 db = new Db();
			     Statement stmt = db.getStatement();
				 ResultSet rs = stmt.executeQuery(sql);
			     while (rs.next()) {
			    	 returnValue = rs.getInt("MENUNGGU");
			     }
			     
			      return returnValue;
			 } finally {
			      if (db != null) db.close();
			 }
			    
		 }	 
		 
	public static int getKPIGiliranBilanganMengikutPejabat( 
		String idSuburusanstatus,String strQuery,String idNegeri,String idPejabat
		,String tarikhMula,String tarikhTamat) throws Exception {
			 Db db = null;
			 int returnValue=0;
			 String sql = "select menunggu,hari,tk from ( "+
					   " SELECT count(distinct(rsusf.ID_PERMOHONAN)) menunggu, max(rsusf.tarikh_kemaskini)-min(rsusf.tarikh_masuk) hari "+
					   " ,max(rsusf.tarikh_kemaskini) tk from tblrujsuburusanstatusfail rsusf,tblkpistatus ist,tblppkpermohonan p,tblpfdfail f ";
			 			if(idPejabat!="0"){
			 				sql += " ,tblrujpejabaturusan rpu ";
			 			}
					   sql += " where f.ID_FAIL = p.ID_FAIL AND p.ID_PERMOHONAN=rsusf.ID_PERMOHONAN "+
					   " AND rsusf.id_suburusanstatus = ist.id_suburusanstatus(+) AND rsusf.ID_SUBURUSANSTATUS="+idSuburusanstatus+
					   " AND f.id_negeri="+ idNegeri ;
					   if(idPejabat!="0"){
							 //--by UNIT PUSAKA
						   sql += " AND p.ID_DAERAHMHN=rpu.ID_DAERAHURUS "+
						   " AND rpu.ID_PEJABATJKPTG="+idPejabat;
					   }
					   sql +=" )";
			
			 if(!strQuery.equals("")){	
				 sql += " WHERE  hari";
				 if(strQuery.equals("8"))	
					 sql +="< 8";
				 else if(strQuery.equals("15"))
					 sql +=" >7 AND hari<15";
				 else if(strQuery.equals("16"))
					 sql +="> 14";
				 else if(strQuery.equals("5"))
					 sql +="< 5";
				 else if(strQuery.equals("5-14"))
					 sql +=" >4 AND hari<15";
				 else if(strQuery.equals("30"))
					 sql +="<31";
				 else if(strQuery.equals("60"))
					 sql +=" >30 AND hari<61";
				 else if(strQuery.equals("61"))
					 sql +="> 60";			 
			 }
			 if(!tarikhMula.equals("0"))	{	   
				 sql +=" AND to_char(tk,'dd/mm/yyyy') between '"+tarikhMula+"' and '"+tarikhTamat+"'";
			 }
				 try {
					 db = new Db();
					 Statement stmt = db.getStatement();
					 myLog.info("FrmKPIData::getKPIGiliranBilanganMengikutPejabat("+idSuburusanstatus+","+strQuery+","+idNegeri+","+ idPejabat+","+tarikhMula+","+tarikhTamat+")::sql = "+sql);
					 ResultSet rs = stmt.executeQuery(sql);
				     while (rs.next()) {
					    returnValue =rs.getInt("Menunggu");
				     }
				     return returnValue;
				    } finally {
				      if (db != null) db.close();
				    }
			  }	 
		 
		 
}