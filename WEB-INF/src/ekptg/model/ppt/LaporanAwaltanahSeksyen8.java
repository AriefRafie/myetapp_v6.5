package ekptg.model.ppt;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class LaporanAwaltanahSeksyen8 {
	
	private static Vector listA = new Vector();
	private static Vector listB = new Vector();
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");

	public static Vector getRecordLaporan(String id_fail, String id_permohonan, String id_status, String id_tanahumum) throws Exception {
		
	    Db db = null;
	    listA.clear();
	    String sql = "";
	    try {
	      Vector localVector1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("p.id_permohonan");
	      r.add("p.no_permohonan");
	      r.add("p.id_fail");
	      r.add("f.no_fail");
	      r.add("f.id_suburusan");
	      r.add("p.tarikh_permohonan");
	      r.add("p.id_status");
	      r.add("f.id_kementerian");
	      r.add("p.id_agensi");
	      r.add("a.nama_agensi");
	      r.add("p.flag_peruntukan");
	      r.add("p.flag_segera");
	      r.add("f.id_negeri");
	      r.add("p.id_daerah"); 
	      r.add("p.tujuan");
	      r.add("p.no_rujukan_surat");
	      r.add("p.tarikh_kehendaki");
	      r.add("p.alamat1");
	      r.add("p.alamat2");
	      r.add("p.alamat3");
	      r.add("p.poskod");
	      r.add("p.id_negeri");
	      r.add("p.id_mukim");
	      r.add("k.nama_kementerian");
	      r.add("b.nama_daerah");
	      r.add("p.no_rujukan_ptd");
	      r.add("p.no_rujukan_ptg");
	      r.add("p.no_rujukan_upt");
	      r.add("su.nama_suburusan");
	      r.add("s.keterangan");
	      r.add("a.nama_agensi");
	      r.add("c.nama_negeri");
	      r.add("p.tarikh_terima");	
	      
	      r.add("f.id_kementerian",r.unquote("k.id_kementerian"));
	      r.add("f.id_fail",r.unquote("p.id_fail"));
	      r.add("b.id_daerah",r.unquote("p.id_daerah"));
	      r.add("f.id_suburusan",r.unquote("su.id_suburusan"));
	      r.add("p.id_status",r.unquote("s.id_status"));
	      r.add("p.id_agensi",r.unquote("a.id_agensi"));
	      r.add("p.id_negeri",r.unquote("c.id_negeri"));
	      r.add("p.id_fail", id_fail, "=");
	      r.add("p.id_permohonan", id_permohonan, "=");

	      sql = r.getSQLSelect("Tblpfdfail f, Tblpptpermohonan p, Tblrujkementerian k, Tblrujagensi a, Tblrujnegeri c, Tblrujdaerah b, Tblrujsuburusan su, Tblrujstatus s");

	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	h = new Hashtable();
	    	h.put("bil", bil);
	        h.put("id_permohonan", rs.getString("id_permohonan")==null?"-":rs.getString("id_permohonan"));
	    	h.put("no_permohonan", rs.getString("no_permohonan")==null?"-":rs.getString("no_permohonan"));
	    	h.put("no_fail", rs.getString("no_fail")==null?"-":rs.getString("no_fail"));
	    	h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"-":rs.getString("nama_kementerian"));
	    	h.put("nama_daerah", rs.getString("nama_daerah")==null?"-":rs.getString("nama_daerah"));
	    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"-":rs.getString("no_rujukan_ptd"));
	    	h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"-":rs.getString("no_rujukan_ptg"));
	    	h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"-":rs.getString("no_rujukan_upt"));
	    	h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"-":rs.getString("nama_suburusan"));
	    	h.put("keterangan", rs.getString("keterangan")==null?"-":rs.getString("keterangan"));
	    	h.put("nama_agensi", rs.getString("nama_agensi")==null?"-":rs.getString("nama_agensi"));
	    	h.put("nama_negeri", rs.getString("nama_negeri")==null?"-":rs.getString("nama_negeri"));
	    	if(rs.getString("id_suburusan") == null){
	    		h.put("id_suburusan","");
	    	}else{
	    		h.put("id_suburusan",rs.getString("id_suburusan"));
	    	}
	    	h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    	h.put("tarikh_terima", rs.getDate("tarikh_terima")==null?"":Format.format(rs.getDate("tarikh_terima")));
	    	h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
	    	if(rs.getString("id_kementerian") == null){
	    		h.put("id_kementerian","");
	    	}else{
	    		h.put("id_kementerian",rs.getString("id_kementerian"));
	    	}
	    	if(rs.getString("id_agensi") == null){
	    		h.put("id_agensi","");
	    	}else{
	    		h.put("id_agensi",rs.getString("id_agensi"));
	    	}
	    	h.put("flag_peruntukan", rs.getString("flag_peruntukan")==null?"":rs.getString("flag_peruntukan"));
	    	h.put("flag_segera", rs.getString("flag_segera")==null?"":rs.getString("flag_segera"));
	    	if(rs.getString("id_negeri") == null){
	    		h.put("id_negeri_projek","");
	    	}else{
	    		h.put("id_negeri_projek",rs.getString("id_negeri"));
	    	}
	    	if(rs.getString("id_daerah") == null){
	    		h.put("id_daerah","");
	    	}else{
	    		h.put("id_daerah",rs.getString("id_daerah"));
	    	}
	    	h.put("tujuan", rs.getString("tujuan")==null?"":rs.getString("tujuan"));
	    	h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")==null?"-":rs.getString("no_rujukan_surat"));		    	
	    	h.put("tarikh_kehendaki", rs.getDate("tarikh_kehendaki")==null?"-":Format.format(rs.getDate("tarikh_kehendaki")));
	    	h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
	    	h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
	    	h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
	    	h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
	    	if(rs.getString("id_negeri") == null){
	    		h.put("id_negeri","");
	    	}else{
	    		h.put("id_negeri",rs.getString("id_negeri"));
	    	}
	    	if(rs.getString("id_mukim") == null){
	    		h.put("id_mukim","");
	    	}else{
	    		h.put("id_mukim",rs.getString("id_mukim"));
	    	}
	    	listA.addElement(h);
	    	bil++;
	      }
	      return listA;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	}//close getRecordLaporan
		
	
	public static Vector getRecordLaporanTUSek8(String id_permohonan, String id_tanahumum) throws Exception {
		
	    Db db = null;
	    listB.clear();
	    String sql = "";
	    try {
	      Vector localVector1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();       
	      
	      r.add("a.id_tanahumum");
	      r.add("a.id_permohonan");
	      r.add("a.tarikh_lawatan");
	      r.add("a.tarikh_mula_chart");
	      r.add("a.tarikh_akhir_chart");
	      r.add("a.perihal_kawasan_simpan");
	      r.add("a.lokasi_tanah");			      
	      r.add("a.keadaan_rupabumi");	
	      r.add("a.perihal_bangunan");
	      r.add("a.ulasan_syor");	
	      r.add("a.bil_hakmilik");
	      r.add("d.keterangan");				      
	      r.add("a.luas_terlibat");	
	      r.add("a.id_unitluas");
	      r.add("a.harga_anggar");
	      r.add("a.harga_anggar_bangunan");
	      r.add("a.perihal_tm_sendiri");
	      r.add("a.perihal_tr_negeri");
	      r.add("a.perihal_tmtr_sekutuan");
	      r.add("a.perihal_tnh_kerajaan");

	      r.add("a.id_permohonan",r.unquote("b.id_permohonan"));
	      r.add("a.id_unitluas",r.unquote("d.id_luas "));

	      r.add("a.id_permohonan", id_permohonan, "=");
	      r.add("a.id_tanahumum", id_tanahumum, "=");

	      sql = r.getSQLSelect(" Tblppttanahumum a, Tblpptpermohonan b, Tblrujluas d ");
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      //Vector v = new Vector();
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	h = new Hashtable();
	    	h.put("bil", bil);
	        h.put("id_tanahumum", rs.getString("id_tanahumum")==null?"-":rs.getString("id_tanahumum"));
	    	h.put("id_permohonan", rs.getString("id_permohonan")==null?"-":rs.getString("id_permohonan"));
	    	h.put("tarikh_lawatan", rs.getDate("tarikh_lawatan")==null?"":Format.format(rs.getDate("tarikh_lawatan")));
	    	h.put("tarikh_mula_chart", rs.getDate("tarikh_mula_chart")==null?"":Format.format(rs.getDate("tarikh_mula_chart")));
	    	h.put("tarikh_akhir_chart", rs.getDate("tarikh_akhir_chart")==null?"":Format.format(rs.getDate("tarikh_akhir_chart")));
	    	h.put("perihal_kawasan_simpan", rs.getString("perihal_kawasan_simpan")==null?"-":rs.getString("perihal_kawasan_simpan"));
	    	h.put("lokasi_tanah", rs.getString("lokasi_tanah")==null?"-":rs.getString("lokasi_tanah"));
	    	h.put("keadaan_rupabumi", rs.getString("keadaan_rupabumi")==null?"-":rs.getString("keadaan_rupabumi"));
	    	h.put("perihal_bangunan", rs.getString("perihal_bangunan")==null?"-":rs.getString("perihal_bangunan"));
	    	h.put("ulasan_syor", rs.getString("ulasan_syor")==null?"-":rs.getString("ulasan_syor"));
	    	h.put("bil_hakmilik", rs.getString("bil_hakmilik")==null?"-":rs.getString("bil_hakmilik"));
	    	h.put("keterangan_unitluas", rs.getString(12)==null?"-":rs.getString(12));
	    	h.put("luas_terlibat", rs.getString("luas_terlibat")==null?"-":rs.getString("luas_terlibat"));		
	    	h.put("id_unitluas", rs.getString("id_unitluas")==null?"-":rs.getString("id_unitluas"));	
	    	h.put("harga_anggar", rs.getString("harga_anggar")==null?"-":rs.getString("harga_anggar"));
	    	h.put("harga_anggar_bangunan", rs.getString("harga_anggar_bangunan")==null?"-":rs.getString("harga_anggar_bangunan"));
	    	h.put("perihal_tm_sendiri", rs.getString("perihal_tm_sendiri")==null?"-":rs.getString("perihal_tm_sendiri"));
	    	h.put("perihal_tr_negeri", rs.getString("perihal_tr_negeri")==null?"-":rs.getString("perihal_tr_negeri"));
	    	h.put("perihal_tmtr_sekutuan", rs.getString("perihal_tmtr_sekutuan")==null?"-":rs.getString("perihal_tmtr_sekutuan"));
	    	h.put("perihal_tnh_kerajaan", rs.getString("perihal_tnh_kerajaan")==null?"-":rs.getString("perihal_tnh_kerajaan"));
	    	
	    	listB.addElement(h);
	    	bil++;
	      }
	      return listB;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	}//close getRecordLaporanTUSek8	

	public static void updateMaklumatTanahUmum(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";		   
	    try
	    {		    	
	    	String id_permohonan = (String)data.get("id_permohonan");
	    	String id_tanahumum = (String)data.get("id_tanahumum");	
	      
	      String id_user = (String)data.get("id_user");
	      
		  String txdTarikhMulaChartingEdit = (String)data.get("txdTarikhMulaChartingEdit");
		  String txtKeseluruhanLotEdit = (String)data.get("txtKeseluruhanLotEdit");
		  String txdTarikhTamatChartingEdit = (String)data.get("txdTarikhTamatChartingEdit");
		  String txdTarikhLawatanEdit = (String)data.get("txdTarikhLawatanEdit");
		  String txtKeluasanEdit = (String)data.get("txtKeluasanEdit");			  
		  String txtPerihalBgnanEdit = (String)data.get("txtPerihalBgnanEdit");  
		  String EDITsocLuas = (String)data.get("EDITsocLuas");  
		  String txtAnggaranKosTanahEdit = (String)data.get("txtAnggaranKosTanahEdit");  
		  String txtAnggaranKosBangunanEdit = (String)data.get("txtAnggaranKosBangunanEdit"); 
		  String txtTanahPersendirianEdit = (String)data.get("txtTanahPersendirianEdit"); 
		  String txtTanahRizabEdit = (String)data.get("txtTanahRizabEdit"); 
		  String txtKawRizabEdit = (String)data.get("txtKawRizabEdit"); 
		  String txtTanahPersekutuanEdit = (String)data.get("txtTanahPersekutuanEdit"); 
		  String txtKeadaanTanahEdit = (String)data.get("txtKeadaanTanahEdit"); 
		  String txtLokasiTanahEdit = (String)data.get("txtLokasiTanahEdit"); 
		  String txtTanahKerajaanEdit = (String)data.get("txtTanahKerajaanEdit"); 
		  String txtSyorEdit = (String)data.get("txtSyorEdit"); 
		  
		  String TMC = "to_date('" + txdTarikhMulaChartingEdit + "','dd/MM/yyyy')";
		  String TTC = "to_date('" + txdTarikhTamatChartingEdit + "','dd/MM/yyyy')";
		  String TL = "to_date('" + txdTarikhLawatanEdit + "','dd/MM/yyyy')";	
		  
		 
		  db = new Db();			  
		  Statement stmt = db.getStatement();
		  SQLRenderer r = new SQLRenderer();
		  r.update("id_tanahumum", id_tanahumum);
		  r.update("id_permohonan", id_permohonan);
		  
		  r.add("tarikh_mula_chart", r.unquote(TMC));
		  r.add("bil_hakmilik", txtKeseluruhanLotEdit);
		  r.add("tarikh_akhir_chart", r.unquote(TTC));
		  r.add("tarikh_lawatan", r.unquote(TL));
		  r.add("luas_terlibat", txtKeluasanEdit);
		  r.add("id_unitluas", EDITsocLuas);
		  r.add("harga_anggar", txtAnggaranKosTanahEdit);
		  r.add("harga_anggar_bangunan", txtAnggaranKosBangunanEdit);
		  r.add("perihal_tm_sendiri", txtTanahPersendirianEdit);
		  r.add("perihal_tr_negeri", txtTanahRizabEdit);
		  r.add("perihal_kawasan_simpan", txtKawRizabEdit);
		  r.add("perihal_tmtr_sekutuan", txtTanahPersekutuanEdit);
		  r.add("keadaan_rupabumi", txtKeadaanTanahEdit);
		  r.add("lokasi_tanah", txtLokasiTanahEdit);
		  r.add("perihal_tnh_kerajaan", txtTanahKerajaanEdit);
		  r.add("ulasan_syor", txtSyorEdit);
		  r.add("id_kemaskini",id_user);
		  r.add("tarikh_kemaskini",r.unquote("sysdate"));
		  sql = r.getSQLUpdate("tblppttanahumum");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }//close updateMaklumatTanahUmum
}
