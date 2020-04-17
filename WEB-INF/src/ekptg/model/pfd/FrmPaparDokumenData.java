package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmPaparDokumenData {
	private static Vector paparDokumen = new Vector();
	private static Vector senaraiMinit = new Vector();
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public static void setDataDokumen(int id)throws Exception {
		
		 Db db = null;
		 paparDokumen.clear();
		 String sql = "";
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 Date now = new Date();
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Dokumen");
		      r.add("a.bil_Minit_Dokumen");
		      r.add("a.id_Jenisdokumen");
		      r.add("a.no_Rujukan_Dokumen");
		      r.add("a.tarikh_Dokumen_Keluar");
		      r.add("a.tarikh_Dokumen_Masuk");
		      r.add("a.nama_Pengirim");
		      r.add("a.nama_Penerima");
		      r.add("a.tarikh_Daftar");
		      
		     
		      
		      
		      r.add("a.id_Dokumen",id);
		     
		    
		      sql = r.getSQLSelect("Tblpfddokumen a");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		       
		      while (rs.next()) {
		    	  h.put("id_Dokumen",rs.getString("id_Dokumen"));
		    	  h.put("bil_Minit_Dokumen", rs.getString("bil_Minit_Dokumen"));
		    	  if (rs.getString("id_Jenisdokumen").equals("1")){
			    	  h.put("jenis_Dokumen", "SURAT");
 
		    	  }
		    	  else if (rs.getString("id_Jenisdokumen").equals("2")){
			    	  h.put("jenis_Dokumen", "MEMO");
 
		    	  }
		    	  else if (rs.getString("id_Jenisdokumen").equals("3")){
			    	  h.put("jenis_Dokumen", "LAPORAN");
 
		    	  }
		    	  else if (rs.getString("id_Jenisdokumen").equals("4")){
			    	  h.put("jenis_Dokumen", "MINIT MESYUARAT");
 
		    	  }
		    	  
		    	  
		    	  
		    	  h.put("no_Rujukan_Dokumen",rs.getString("no_Rujukan_Dokumen"));
		    	  h.put("tarikh_Dokumen_Keluar", sdf.format(rs.getDate("tarikh_Dokumen_Keluar")));
		    	  h.put("tarikh_Dokumen_Masuk",sdf.format(rs.getDate("tarikh_Dokumen_Masuk")));
		    	  h.put("nama_Pengirim",rs.getString("nama_Pengirim"));
		    	  h.put("nama_Penerima",rs.getString("nama_Penerima"));
		    	  h.put("tarikh_Daftar", sdf.format(rs.getDate("tarikh_Daftar")));
		    	  
		    	  
		    	  paparDokumen.addElement(h); 
		    	  
		      }
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		 
		 
	}
	 public static Vector getDataDokumen(){
		 
		  return paparDokumen;
	  }
	 public static void  setListMinitArahan(int id)throws Exception {
		 Db db = null;
		    senaraiMinit.clear();
		    String sql = "";
		    
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Minitarahan");
		      r.add("b.no_Rujukan_Dokumen");
		      r.add("a.minit_Arahan");
		      r.add("c.nama_Pegawai as PegawaiMengarah");
		      r.add("d.nama_Pegawai as PegawaiMenerima");
		      r.add("a.tarikh_Minit_Arahan");
		      r.add("a.id_statusTindakan");
		     
		      
		      r.add("a.id_Dokumen",id);
		      r.add("a.id_Pegawai_Ygmengarah",r.unquote("c.id_Pegawai"));
		      r.add("a.id_Pegawai_Ygmenerima",r.unquote("d.id_Pegawai"));
		      r.add("a.id_Dokumen",r.unquote("b.id_Dokumen"));
		      
		     
		
		      sql = r.getSQLSelect("Tblpfdminitarahan a, Tblpfddokumen b, Tblrujpegawai c, Tblrujpegawai d","a.id_Dokumen desc");
		      System.out.println("sql == "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("id_Minitarahan",rs.getString("id_Minitarahan"));
		    	  h.put("no_Rujukan_Dokumen", rs.getString("no_Rujukan_Dokumen")== null?"":rs.getString("no_Rujukan_Dokumen"));
		    	  h.put("minit_Arahan", rs.getString("minit_Arahan")== null?"":rs.getString("minit_Arahan"));
		    	  h.put("PegawaiMengarah",rs.getString("PegawaiMengarah")== null?"":rs.getString("PegawaiMengarah"));
		    	  h.put("PegawaiMenerima", rs.getString("PegawaiMenerima")== null?"":rs.getString("PegawaiMenerima"));
		    	  h.put("tarikh_Minit_Arahan", rs.getDate("tarikh_Minit_Arahan")== null?"":sdf.format(rs.getDate("tarikh_Minit_Arahan")));
		    	  
		    	  if (rs.getString("id_statusTindakan") != null){
		    		  if (Integer.parseInt(rs.getString("id_statusTindakan"))== 1){
				    	  h.put("status_Tindakan", "TELAH DIAMBIL TINDAKAN");

			    	  }
			    	  else if (Integer.parseInt(rs.getString("id_statusTindakan"))== 2){
			    		  h.put("status_Tindakan", "TUGASAN TELAH DIAGIHKAN");
			    	  }
			    	  else if (Integer.parseInt(rs.getString("id_statusTindakan"))== 3){
			    		  h.put("status_Tindakan", "SEDANG DALAM TINDAKAN");
			    	  }
		    	  }
		    	  
		    	  else{
		    		  h.put("status_Tindakan", " ");
		    	  }
		    	  
		    	  
		    	  senaraiMinit.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil","");
		    	  h.put("id_Minitarahan","");
		    	  h.put("no_Rujukan_Dokumen", "Tiada rekod.");
		    	  h.put("minit_Arahan", "");
		    	  h.put("PegawaiMengarah","");
		    	  h.put("PegawaiMenerima", "");
		    	  h.put("tarikh_Minit_Arahan", "");
		    	  h.put("status_Tindakan", "");
		    	  senaraiMinit.addElement(h);
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		}
		public static Vector getListMinitArahan(){
				 
			return senaraiMinit;
		}
	

}
