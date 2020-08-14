/**
 * 
 */
package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.pm.entity.ActivityEvent;
import lebah.pm.entity.UserActivityEvent;
import lebah.template.DbPersistence;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;



public class FrmPrmhnnSek8BicaraData {
	static Logger myLogger = Logger.getLogger(FrmPrmhnnSek8BicaraData.class);
	
	private Vector list = new Vector();
	private Vector listSimati = new Vector();
	private Vector listPemohon = new Vector();
	private Vector listPeguam = new Vector();
	private Vector listKeputusan = new Vector();
	private Vector listWaris = new Vector();
	private Vector listHta = new Vector();
	private Vector listHtath = new Vector();
	private Vector listHa = new Vector();
	private Vector listRujJenisHa = new Vector();
	private Vector listPenting = new Vector();
	private Vector listPentingbyIDOB = new Vector();
	private Vector listSaksi = new Vector();
	private Vector listPenghutang= new Vector();
	private Vector listPemiutang= new Vector();
	private Vector listWarisParent = new Vector();
	private Vector listWarisLapisan = new Vector();
	private Vector listWarisUpdate = new Vector();
	private Vector listDaerah = new Vector();
	private Vector listLuas = new Vector();
	private Vector listHTA = new Vector();
	private Vector listHTAX = new Vector();
	private Vector listHTAbyIdHtaam= new Vector();
	private Vector listHTAXbyIdHtaam= new Vector();
	private Vector listWarisOB = new Vector();
	private Vector listWarisLapisanIdMati = new Vector();
	private Vector listWarisLapisanIdMatiDelete = new Vector();
	private Vector listCheckPeguam = new Vector();
	private Vector listPenghutangbyIDOB = new Vector();
	private Vector listTangguhROTS = new Vector();
	private static Vector listSemak = new Vector();
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	public  Vector getData(){		
		return list;	
	}
	public Vector getDataHTAX(){
		return listHTAX;
	}
	public Vector getDataHTAXbyIdHtaam(){
		return listHTAXbyIdHtaam;
	}
	public Vector getDataHTAbyIdHtaam(){
		return listHTAbyIdHtaam;
	}
	public Vector getDataHTA(){
		return listHTA;
	}
	public Vector getDataWarisOB(){
		return listWarisOB;
	}
	public Vector getDataSimati(){
		return listSimati;
	}
	public Vector getDataWarisLapisanIdMati(){
		return listWarisLapisanIdMati;
	}
	public Vector getDataWarisLapisanIdMatiDelete(){
		return listWarisLapisanIdMatiDelete;
	}
	
	public Vector getDataPemohon(){
		return listPemohon;
	}	
	public Vector getDataPeguam(){
		return listPeguam;
	}
	
	public Vector getDataPenting(){
		return listPenting;
	}
	public Vector getDataPentingbyIDOB(){
		return listPentingbyIDOB;
	}	
	public Vector getDataSaksi(){
		return listSaksi;
	}
	
	public Vector getDataPenghutang(){
		return listPenghutang;
	}
	
	public Vector getDataPenghutangbyIDOB(){
		return listPenghutangbyIDOB;
	}
	
	public Vector getDataPemiutang(){
		return listPemiutang;
	}
	
	public Vector getDataWaris(){
		return listWaris;
	}
	
	public Vector getDataWarisParent(){
		return listWarisParent;
	}
	
	public Vector getDataWarisLapisan(){
		return listWarisLapisan;
	}
	
	public Vector getDataWarisUpdate(){
		return listWarisUpdate;
	}	
	public static Vector getListSemak(){
		return listSemak;
	}	

	public void setDataSimati(String id) throws Exception {
		Db db = null;
		listSimati.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
		r.add("p.id_Permohonan");
		r.add("s.id_Simati");
		r.add("s.no_Kp_Baru");
		r.add("s.no_Kp_Lama");
		r.add("s.jenis_Kp");
		r.add("s.no_Kp_Lain");
		r.add("s.nama_Simati");
		r.add("s.tarikh_Mati");
		r.add("s.nama_Lain");
		r.add("s.jantina");
		r.add("s.jenis_Warga");
		r.add("s.jenis_Agama");
		r.add("s.umur");
		r.add("s.id_Buktimati");
		r.add("s.tempat_Mati");
		r.add("s.no_Sijil_Mati");
		r.add("s.waktu_Kematian");
		r.add("s.jenis_Waktu_Mati");		
		r.add("s.id_Buktimati");		
		r.add("s.sebab_Mati");
		r.add("s.alamat_1");
		r.add("s.alamat_2");
		r.add("s.alamat_3");
		r.add("s.poskod");
		r.add("s.bandar");
		r.add("s.id_Negeri");
		r.add("s.catatan");
	    r.add("po.id_Pemohon");
	    r.add("ps.id_permohonansimati");
		
		r.add("ps.id_Permohonan",r.unquote("p.id_Permohonan"));
		r.add("p.id_Pemohon",r.unquote("po.id_Pemohon"));
		r.add("s.id_simati",r.unquote("ps.id_simati"));
		
		r.add("p.id_Permohonan",id);
		
		sql = r.getSQLSelect("Tblppksimati s, Tblppkpermohonan p, Tblppkpemohon po, Tblppkpermohonansimati ps");
		myLogger.info(sql);
		ResultSet rs = stmt.executeQuery(sql);
		
		Hashtable h;
		
		while(rs.next()) {
			h = new Hashtable();
			h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
			h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
			h.put("noKpBaru", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"));
			h.put("noKpBaru1", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(0,6));
			h.put("noKpBaru2", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(6,8));
			h.put("noKpBaru3", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(8,12));
			h.put("noKpLama", rs.getString("no_Kp_Lama")==null?"":rs.getString("no_Kp_Lama"));
			h.put("jenisKp", rs.getString("jenis_Kp")==null?"":rs.getString("jenis_Kp"));
			h.put("noKpLain", rs.getString("no_Kp_Lain")==null?"":rs.getString("no_Kp_Lain"));
			h.put("namaSimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
			h.put("namaLain", rs.getString("nama_Lain")==null?"":rs.getString("nama_Lain"));
			h.put("idPemohon", rs.getString("id_Pemohon")==null?"":rs.getString("id_Pemohon"));
			h.put("masamati", rs.getString("waktu_Kematian")==null?"":rs.getString("waktu_Kematian"));
			h.put("tarikhMati",rs.getDate("tarikh_Mati")==null?"":formatter.format(rs.getDate("tarikh_Mati")));
			h.put("jantina", rs.getString("jantina")==null?"":rs.getString("jantina"));
			h.put("jenisWarga", rs.getString("jenis_Warga")==null?"":rs.getString("jenis_Warga"));
			h.put("jenisAgama", rs.getString("jenis_Agama")==null?"":rs.getString("jenis_Agama"));
			h.put("umur", rs.getString("umur")==null?"":rs.getString("umur"));
			h.put("idBuktimati", rs.getString("id_Buktimati")==null?"":rs.getString("id_Buktimati"));
			h.put("tempatMati", rs.getString("tempat_Mati")==null?"":rs.getString("tempat_Mati"));
			h.put("noSijilMati", rs.getString("no_Sijil_Mati")==null?"":rs.getString("no_Sijil_Mati"));
			h.put("masaMati", rs.getString("waktu_Kematian")==null?"":rs.getString("waktu_Kematian"));
			h.put("jeniswaktumati", rs.getString("jenis_Waktu_Mati")==null?"":rs.getString("jenis_Waktu_Mati"));
			h.put("sebabMati", rs.getString("sebab_Mati")==null?"":rs.getString("sebab_Mati"));
			h.put("alamat1", rs.getString("alamat_1")==null?"":rs.getString("alamat_1"));
			h.put("alamat2", rs.getString("alamat_2")==null?"":rs.getString("alamat_2"));
			h.put("alamat3", rs.getString("alamat_3")==null?"":rs.getString("alamat_3"));
			h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
			h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
			h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
			h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
			h.put("id_permohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));

			listSimati.addElement(h);
		}
		}
		finally {
			if(db != null) db.close();
		}
		
	}
		 				 
			public static void setListSemak(String idpermohonan, String usid)throws Exception {
				Db db = null;
				listSemak.clear();
				String sql = "";
				
				try{
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					sql =  "SELECT distinct pm.id_negeri, n.id_Negeri, n.nama_Negeri,f.id_Fail, f.no_Fail, d.id_Daerah, p.id_Permohonan, "; 
					sql += "p.tarikh_Mohon, s.no_Kp_Baru, s.no_Kp_Lama, s.jenis_Kp, s.no_Kp_Lain, s.id_Simati, ";
					sql += "s.nama_Simati, s.tarikh_Mati, pm.id_Pemohon, pm.nama_Pemohon, pm.no_kp_baru, ";
					sql += "pm.no_kP_lama, pm.jenis_Kp, pm.no_Kp_lain, pm.alamat_1, pm.alamat_2, pm.alamat_3, "; 
					sql += "pm.poskod, pm.bandar, d.nama_Daerah, p.seksyen, st.keterangan, ";
					sql += "p.id_Status, u.nama_pejabat, mosi.id_Permohonansimati, s.umur, s.jantina, "; 
					sql += "pm.umur, pm.jantina,u.id_pejabatjkptg, p.no_subjaket, sstf.id_suburusanstatusfail, dx.nama_daerah AS D_P, mosi.id_permohonansimati,u.id_negeri as id_negeripejabat "; 
					sql += "FROM Tblpfdfail f,Tblppkpermohonan p, Tblrujnegeri n, Tblrujdaerah d, Tblppksimati s, "; 
					sql += "Tblppkpemohon pm, Tblrujstatus st, tblrujsuburusanstatusfail sstf, tblrujdaerah dx, tblrujsuburusanstatus sst, "; 
					sql += "tblrujpejabatjkptg u, Tblppkpermohonansimati mosi, Users_Internal ur ";
					sql += "WHERE f.id_Negeri = n.id_Negeri(+) ";
					sql += "AND sstf.id_permohonan = p.id_permohonan ";
					sql += "AND sstf.id_suburusanstatus = sst.id_suburusanstatus ";
					sql += "AND sst.id_status = st.id_status ";
					sql += "AND p.id_Daerahmhn = d.id_Daerah(+) ";  
					sql += "AND ur.user_id = "+usid ;
					sql += "AND ur.ID_PEJABATJKPTG = u.ID_PEJABATJKPTG "; 
					sql += "AND p.id_Fail = f.id_Fail ";
					sql += "AND pm.id_Pemohon = p.id_Pemohon ";   
					sql += "AND s.id_Simati = mosi.id_Simati ";
					sql += "AND p.id_Permohonan = mosi.id_Permohonan ";   
					sql += "AND st.id_Status = p.id_Status ";
					sql += "AND d.id_daerah = p.id_daerahmhn "; 
					sql += "AND u.id_daerah = dx.id_daerah(+) "; 
					//sql += "AND sstf.aktif = 1 ";
					sql += "AND p.id_Permohonan = '" +idpermohonan+ "' " ;					
					
					System.out.println("[CHECKLIST HEADER]:"+sql);
					ResultSet rs = stmt.executeQuery(sql);
				    Hashtable h;
					
			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  
			    	  h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
			    	  h.put("idFail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
			    	  h.put("noFail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
			    	  h.put("idDaerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
			    	  h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
			    	  h.put("tarikhMohon", rs.getDate("tarikh_Mohon")==null?"":Format.format(rs.getDate("tarikh_Mohon")));
			    	  h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
			    	  h.put("namaSimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
			    	  h.put("tarikhMati", rs.getDate("tarikh_Mati")==null?"": Format.format(rs.getDate("tarikh_Mati")));
			    	  h.put("idPemohon", rs.getString("id_Pemohon")==null?"":rs.getString("id_Pemohon"));
			    	  h.put("namaPemohon", rs.getString("nama_Pemohon")==null?"":rs.getString("nama_Pemohon"));
			    	  h.put("alamat1", rs.getString("alamat_1")==null?"":rs.getString("alamat_1"));
			    	  h.put("alamat2", rs.getString("alamat_2")==null?"":rs.getString("alamat_2"));
			    	  h.put("alamat3", rs.getString("alamat_3")==null?"":rs.getString("alamat_3"));
			    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
			    	  h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
			    	  h.put("idnegeri", rs.getString(16)==null?"":rs.getString(16));
			    	  h.put("namanegeri", rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
			    	  h.put("namadaerah", rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
			    	  h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
			    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
			    	  h.put("id_Status", rs.getString("id_Status")==null?"":rs.getString("id_Status"));
			    	  
			    	  if(rs.getString("id_negeripejabat").equals("7")){
			    		  h.put("namaPejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
			    	  }
			    	  else{
			    		  h.put("namaPejabat", rs.getString("nama_pejabat")+","+rs.getString("D_P")==null?"":rs.getString("nama_pejabat")+","+rs.getString("D_P"));
			    	  }
			    	 
				      h.put("id_pejabatjkptg", rs.getString("id_pejabatjkptg"));
			    	  if(rs.getString(3) == null || rs.getString(3) ==""){
			    		  	h.put("pmNama_negeri","");
				    	}else{
				    		h.put("pmNama_negeri",rs.getString(3));
				    	}
			    	  if(rs.getString(42) == null || rs.getString(42) ==""){
				    		h.put("pmidnegeri","0");
				    	}else{
				    		h.put("pmidnegeri",rs.getString(42));
				    	}
			    	  h.put("id_permohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
			    	  
			    	  listSemak.addElement(h);
			      	}      
					}
					finally{
						if(db != null)db.close();
					}
			}//close semak
			
							 
	public static void deleteMaklumatWaris(String idBorangJ, String idBorangJDTL) throws Exception {
		
	    Db db = null;
	    String sql = "";
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("id_borangjdtl", idBorangJDTL);
	      r.add("id_borangj", idBorangJ);
	      
	      sql = r.getSQLDelete("tblppkborangjdtl");
	      myLogger.info("SQL DELETE MAKLUMAT WARIS :: "+sql);
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }//close delete

	
	public static void deleteUploadFail(String id_dokumen, String id_lampiran) throws Exception {
		
	    Db db = null;
	    String sql = "";
	    String sql2 = "";
	    
	    try {
	      db = new Db();
	      
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("id_dokumen", id_dokumen);

	      sql = r.getSQLDelete("tblpfddokumen");
	      myLogger.info("SQL DELETE ID DOKUMEN :: "+sql);	      
	      stmt.executeUpdate(sql);
	      
	     //* -------------------------------------
	      
	      Statement stmt2 = db.getStatement();
	      SQLRenderer r2 = new SQLRenderer();
	      
	      r2.add("id_lampiran", id_lampiran);

	      sql2 = r2.getSQLDelete("tblpfdrujlampiran");
	      myLogger.info("SQL DELETE ID LAMPIRAN :: "+sql2);	      
	      stmt2.executeUpdate(sql2);	      
	      
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }//close delete	
	
	public static void deleteMaklumatROTS(String id_borangj, String id_perbicaraan) throws Exception {
		
	    Db db = null;
	    String sql = "";
	    String sql2 = "";
	    
	    try {
	      db = new Db();
	      
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("id_borangj", id_borangj);

	      sql = r.getSQLDelete("tblppkborangjdtl");
	      myLogger.info("SQL DELETE TBLPPKBORANGJDTL ROTS :: "+sql);	      
	      stmt.executeUpdate(sql);
	      
	     //* -------------------------------------
	      
	      Statement stmt2 = db.getStatement();
	      SQLRenderer r2 = new SQLRenderer();
	      
	      r2.add("id_borangj", id_borangj);
	      r2.add("id_perbicaraan", id_perbicaraan);

	      sql2 = r2.getSQLDelete("tblppkborangj");
	      myLogger.info("SQL DELETE TBLPPKBORANGJ ROTS :: "+sql2);	      
	      stmt2.executeUpdate(sql2);	      
	      
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }	

	public static void deleteMaklumatMufti(String id_borangj, String id_perbicaraan) throws Exception {
		
	    Db db = null;
	    String sql = "";
	    String sql2 = "";
	    
	    try {
	      db = new Db();
	      
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("id_borangj", id_borangj);

	      sql = r.getSQLDelete("tblppkborangjdtl");
	      myLogger.info("SQL DELETE TBLPPKBORANGJDTL MUFTI :: "+sql);	      
	      stmt.executeUpdate(sql);
	      
	     //* -------------------------------------
	      
	      Statement stmt2 = db.getStatement();
	      SQLRenderer r2 = new SQLRenderer();
	      
	      r2.add("id_borangj", id_borangj);
	      r2.add("id_perbicaraan", id_perbicaraan);

	      sql2 = r2.getSQLDelete("tblppkborangj");
	      myLogger.info("SQL DELETE TBLPPKBORANGJ MUFTI :: "+sql2);	      
	      stmt2.executeUpdate(sql2);	      
	      
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }	
	
	
	
	public static void deleteMaklumatSyariah(String id_borangj, String id_perbicaraan) throws Exception {
		
	    Db db = null;
	    String sql = "";
	    String sql2 = "";
	    
	    try {
	      db = new Db();
	      
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("id_borangj", id_borangj);

	      sql = r.getSQLDelete("tblppkborangjdtl");
	      myLogger.info("SQL DELETE TBLPPKBORANGJDTL SYARIAH :: "+sql);	      
	      stmt.executeUpdate(sql);
	      
	     //* -------------------------------------
	      
	      Statement stmt2 = db.getStatement();
	      SQLRenderer r2 = new SQLRenderer();
	      
	      r2.add("id_borangj", id_borangj);
	      r2.add("id_perbicaraan", id_perbicaraan);

	      sql2 = r2.getSQLDelete("tblppkborangj");
	      myLogger.info("SQL DELETE TBLPPKBORANGJ SYARIAH :: "+sql2);	      
	      stmt2.executeUpdate(sql2);	      
	      
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }		
	
	
	public static void insertPejMuftiIDP(String id_perintah,String id_perbicaraan,String ekptg_user_id,
			String idNegeri,String txtnamapej,String txtAlamat1,String txtAlamat2,String txtAlamat3,String txtPoskod,
			String idBandar,String txtTelefon,String txtfax ) throws Exception	{
		
	    Db db = null;
	    String sql = "";
	    String sql2 = "";
	    
	    try
	    {
	    	long id_borangj = DB.getNextID("TBLPPKBORANGJ_SEQ");	    	
	    	
	    	//INSERT TBLPPKBORANGJ 
	    	db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
	      
		    r.add("id_borangj", id_borangj);
		    r.add("id_perbicaraan",id_perbicaraan);	
		    r.add("jenis_rujukan",2);				//* RULER OF THE STATE
		    r.add("id_negeri",idNegeri);	
		    r.add("nama_pejabat",txtnamapej);
		    r.add("alamat1",txtAlamat1);
		    r.add("alamat2",txtAlamat2);
		    r.add("alamat3",txtAlamat3);
		    r.add("poskod",txtPoskod);
		    r.add("id_bandar",idBandar);
		    r.add("no_tel",txtTelefon);
		    r.add("no_fax",txtfax);		   
		    r.add("id_masuk",ekptg_user_id);
		    r.add("tarikh_masuk",r.unquote("sysdate"));
	      
		    sql = r.getSQLInsert("TBLPPKBORANGJ");
		    myLogger.info("SQL INSERT TBLPPKBORANGJ = "+sql);
		    stmt.executeUpdate(sql);	
		    
		    
		    // UPDATE TBLPPKPERINTAH
	    	Statement stmt2 = db.getStatement();
			SQLRenderer r2 = new SQLRenderer();
			
			r2.update("id_perintah", id_perintah);			
		    r2.update("id_perbicaraan",id_perbicaraan);
		    
		    r2.add("id_negeri",idNegeri);
		    r2.add("flag_jenis_keputusan",1);		//* 1 - TANGGUH
		    r2.add("flag_tangguh",5);				//* 5 - MENUNGGU KEPUTUSAN RUJUKAN RULER OF THE STATE ATAU MAHKAMAH TINGGI
		    r2.add("id_kemaskini",ekptg_user_id);
		    r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
		    
		    sql2 = r2.getSQLUpdate("TBLPPKPERINTAH");
		    myLogger.info("SQL UPDATE TBLPPKPERINTAH = "+sql2);
		    stmt2.executeUpdate(sql2);

    	} finally {
    		if (db != null) db.close();
    	}	    		    	
	}
	

	public static void insertPejMufti(String id_perbicaraan,String ekptg_user_id,
			String idNegeri,String txtnamapej,String txtAlamat1,String txtAlamat2,String txtAlamat3,String txtPoskod,
			String idBandar,String txtTelefon,String txtfax ) throws Exception	{
		
	    Db db = null;
	    String sql = "";
	    String sql2 = "";
	    
	    try
	    {
	    	long id_borangj = DB.getNextID("TBLPPKBORANGJ_SEQ");
	    	long id_perintah = DB.getNextID("TBLPPKPERINTAH_SEQ"); 
	    	
	    	//INSERT TBLPPKBORANGJ 
	    	db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
	      
		    r.add("id_borangj", id_borangj);
		    r.add("id_perbicaraan",id_perbicaraan);	
		    r.add("jenis_rujukan",2);				//* RULER OF THE STATE
		    r.add("id_negeri",idNegeri);	
		    r.add("nama_pejabat",txtnamapej);
		    r.add("alamat1",txtAlamat1);
		    r.add("alamat2",txtAlamat2);
		    r.add("alamat3",txtAlamat3);
		    r.add("poskod",txtPoskod);
		    r.add("id_bandar",idBandar);
		    r.add("no_tel",txtTelefon);
		    r.add("no_fax",txtfax);		   
		    r.add("id_masuk",ekptg_user_id);
		    r.add("tarikh_masuk",r.unquote("sysdate"));
	      
		    sql = r.getSQLInsert("TBLPPKBORANGJ");
		    myLogger.info("SQL INSERT TBLPPKBORANGJ = "+sql);
		    stmt.executeUpdate(sql);	
		    
		    
	    	//INSERT TBLPPKPERINTAH
	    	
	    	Statement stmt2 = db.getStatement();
			SQLRenderer r2 = new SQLRenderer();
			
			r2.add("id_perintah", id_perintah);
		    r2.add("id_perbicaraan",id_perbicaraan);	
		    r2.add("id_negeri",idNegeri);
		    r2.add("flag_jenis_keputusan",1);		//* 1 - TANGGUH
		    r2.add("flag_tangguh",5);				//* 5 - MENUNGGU KEPUTUSAN RUJUKAN RULER OF THE STATE ATAU MAHKAMAH TINGGI
		    r2.add("id_masuk",ekptg_user_id);
		    r2.add("tarikh_masuk",r2.unquote("sysdate"));
		    
		    sql2 = r2.getSQLInsert("TBLPPKPERINTAH");
		    myLogger.info("SQL INSERT TBLPPKPERINTAH = "+sql2);
		    stmt2.executeUpdate(sql2);

		    
    	} finally {
    		if (db != null) db.close();
    	}	    		    	
	}	
	
	public static Hashtable getIdBorangJ(String id_perbicaraan) throws Exception {
	    Hashtable v = new Hashtable();
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	 
	      r.add("a.id_perbicaraan");
	      r.add("b.id_borangj");
	      
	      r.add("a.id_perbicaraan",r.unquote("b.id_perbicaraan"));
	      
	      r.add("a.id_perbicaraan",id_perbicaraan);
	      
	      sql = r.getSQLSelect("Tblppkperbicaraan a, Tblppkborangj b");			      
	      myLogger.info("SQL ID BORANG J = "+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      
	      if (rs.next()) {
	        //Hashtable h = new Hashtable();
	    	v.put("id_perbicaraan", rs.getString("id_perbicaraan")==null?"":rs.getString("id_perbicaraan"));
	    	v.put("id_borangj", rs.getString("id_borangj")==null?"":rs.getString("id_borangj"));
	        
	        //v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
  }
	
	
	public Vector setTangguhROTS(String id_perbicaraan, String id_perintah, String idBorangJ) throws Exception  {
	    Db db = null;
	    listTangguhROTS.clear();
	    String sql = "";
	    
	    try {
	      Vector localVector1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("a.id_perintah");
	      r.add("a.id_perbicaraan"); 
	      r.add("a.flag_jenis_keputusan");     
	      r.add("a.id_unitpsk");    
	      r.add("b.nama_pegawai");  
	      r.add("a.jenis_keluar_perintah");  
	      r.add("a.tarikh_perintah");
	      r.add("j.id_mahkamah");
	      r.add("j.id_negerimahkamah");
	      r.add("d.nama_negeri");
	      r.add("c.id_daerah");
	      r.add("e.nama_daerah");
	      r.add("c.nama_pejabat");
	      r.add("c.alamat1");
	      r.add("c.alamat2");
	      r.add("c.alamat3");
	      r.add("c.poskod");
	      r.add("c.no_tel");
	      r.add("c.no_fax");
	      r.add("j.catatan1");
	      r.add("j.keputusan_mahkamah");
	      r.add("j.jenis_rujukan");
	      r.add("j.id_borangj");
	      r.add("j.tarikh_hantar_borangj");
	      r.add("j.tarikh_terima_borangj");
	      r.add("j.catatan2");
	      r.add("j.catatan3");
	      r.add("j.catatan4");
	      r.add("j.catatan5");
	      r.add("a.flag_tangguh");
	      
	      r.add("a.id_unitpsk",r.unquote("b.id_unitpsk"));
	      r.add("j.id_mahkamah",r.unquote("c.id_pejabat"));
	      r.add("d.id_negeri",r.unquote("c.id_negeri"));
	      r.add("e.id_daerah",r.unquote("c.id_daerah"));

	      r.add("a.id_perbicaraan", id_perbicaraan, "=");
	      r.add("a.id_perintah", id_perintah, "=");
	      r.add("j.id_borangj", idBorangJ, "=");

	      sql = r.getSQLSelect("Tblppkperintah a, Tblppkrujunit b, Tblrujpejabat c, Tblrujnegeri d, Tblrujdaerah e, Tblppkborangj j");
	      myLogger.info("SQL TANGGUH ROTS LATEST = "+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	   
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	h = new Hashtable();
	    	h.put("bil", bil);
	        h.put("id_perintah", rs.getString("id_perintah")==null?"":rs.getString("id_perintah"));
	    	h.put("flag_jenis_keputusan", rs.getString("flag_jenis_keputusan")==null?"":rs.getString("flag_jenis_keputusan"));
	    	h.put("id_unitpsk", rs.getString("id_unitpsk")==null?"":rs.getString("id_unitpsk"));
	    	h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
	    	h.put("jenis_keluar_perintah", rs.getString("jenis_keluar_perintah")==null?"":rs.getString("jenis_keluar_perintah"));
	    	h.put("tarikh_perintah", rs.getDate("tarikh_perintah")==null?"":Format.format(rs.getDate("tarikh_perintah")));
	    	h.put("id_mahkamah", rs.getString("id_mahkamah")==null?"":rs.getString("id_mahkamah"));
	    	h.put("id_negerimahkamah", rs.getString("id_negerimahkamah")==null?"":rs.getString("id_negerimahkamah"));
	    	h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
	    	h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
	    	h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
	    	h.put("nama_pejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
	    	h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
	    	h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
	    	h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
	    	h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
	    	h.put("no_tel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
	    	h.put("no_fax", rs.getString("no_fax")==null?"":rs.getString("no_fax"));
	    	h.put("catatan1", rs.getString("catatan1")==null?"":rs.getString("catatan1"));
	    	h.put("keputusan_mahkamah", rs.getString("keputusan_mahkamah")==null?"":rs.getString("keputusan_mahkamah"));
	    	h.put("jenis_rujukan", rs.getString("jenis_rujukan")==null?"":rs.getString("jenis_rujukan"));
	    	h.put("id_borangj", rs.getString("id_borangj")==null?"":rs.getString("id_borangj"));
	    	h.put("tarikh_hantar_borangj", rs.getDate("tarikh_hantar_borangj")==null?"":Format.format(rs.getDate("tarikh_hantar_borangj")));
	    	h.put("tarikh_terima_borangj", rs.getDate("tarikh_terima_borangj")==null?"":Format.format(rs.getDate("tarikh_terima_borangj")));
	    	h.put("catatan2", rs.getString("catatan2")==null?"":rs.getString("catatan2"));
	    	h.put("catatan3", rs.getString("catatan3")==null?"":rs.getString("catatan3"));
	    	h.put("catatan4", rs.getString("catatan4")==null?"":rs.getString("catatan4"));
	    	h.put("catatan5", rs.getString("catatan5")==null?"":rs.getString("catatan5"));
	    	h.put("flag_tangguh", rs.getString("flag_tangguh")==null?"":rs.getString("flag_tangguh"));
	    	
	    	listTangguhROTS.addElement(h);
	    	bil++;
	      }
	      return listTangguhROTS;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	}
	
	
	//aishahlatip
	public static void insertActivityEvent(String idUnitPsk, String id_perbicaraan, String no_fail, String tarikh_kiv) throws Exception {
		
		Db db = null;
		String sql2 = "";
		
			try
			{		
				db = new Db();
				Statement stmt = db.getStatement();
				
				String userLoginPegawai = "";
				Statement stmtPegawai = db.getStatement();
				Statement stmtfail = db.getStatement();
				
				System.out.println("idUnitPsk==="+idUnitPsk);
				String event_id = "KEPUTUSAN PERBICARAAN PUSAKA KECIL BAGI FAIL " + no_fail;
				String event_text = "TERDAPAT DOKUMEN KIV BAGI FAIL " + no_fail;
				String event_location = "";
				String masa_bicara = "0700";
				
				String sqlPegawai = "SELECT * FROM TBLPPKRUJUNIT, USERS WHERE UPPER(TBLPPKRUJUNIT.NAMA_PEGAWAI) = UPPER(USERS.USER_NAME) AND"
					+ " USERS.USER_TYPE = 'internal' AND TBLPPKRUJUNIT.ID_UNITPSK = '" + idUnitPsk + "'";
				
				
				System.out.println("sqlPegawai=="+sqlPegawai);
				ResultSet rsPegawai = stmtfail.executeQuery(sqlPegawai);		
				if (rsPegawai.next()) {
					if (rsPegawai.getString("USER_LOGIN") != null) {
						userLoginPegawai = rsPegawai.getString("USER_LOGIN");
						System.out.println("userLoginPegawai=="+userLoginPegawai);
						//aishah edit 26072017
						System.out.println("id_perbicaraan=="+id_perbicaraan);
						saveActivityEvent(userLoginPegawai, Long.valueOf(id_perbicaraan), event_text, event_location, tarikh_kiv, masa_bicara, "1");
						/** END ADD BY PEJE - REGISTER EVENT TO MYCALENDAR TABLE **/
					}
				}
				
				
				
			}finally {
				if(db != null) db.close();
			}			
	
}
	
	private static void saveActivityEvent(String userLoginPegawai, Long idPerbicaraan, String description, String locationRemark, String tarikhMula, String masa_bicara, String jenisWaktu) throws Exception {
		System.out.println("sini lepas xxxxxxxxxxxxxxxx==="+idPerbicaraan);
		DbPersistence db = new DbPersistence();
		System.out.println("idPerbicaraan==="+idPerbicaraan);
		System.out.println("userLoginPegawai==="+userLoginPegawai);
		deleteUserActivityEvent(idPerbicaraan);
		System.out.println("----------------------done delete------------------");
		//find UserActivityEvent
		UserActivityEvent userActivityEvent = (UserActivityEvent) db.get("select u from UserActivityEvent u where u.userLogin = '" + userLoginPegawai + "'");
		
		System.out.println("select u from UserActivityEvent u where u.userLogin = '" + userLoginPegawai + "'");
		
		if ( userActivityEvent == null ) {
			db.begin();
			userActivityEvent = new UserActivityEvent();
			userActivityEvent.setUserLogin(userLoginPegawai);
			db.persist(userActivityEvent);
			db.commit();
		}		
		System.out.println("----------------------berjaya save aktivity------------------");
		String displayColor = "#FFCCCC";
				
		String jamBicara  = "";
		String minitBicara = "";
		String startTime = "";
		String endTime = "";
		//System.out.println("----------------------2------------------");
		if (!"".equals(masa_bicara)){
			
			jamBicara = masa_bicara.substring(0, 2);
			minitBicara = masa_bicara.substring(2, 4);
			System.out.println("----------------------3------------------");
			if (Integer.valueOf(minitBicara) < 15){
				minitBicara = "00";
			} else if (Integer.valueOf(minitBicara) >= 15 && Integer.valueOf(minitBicara) < 30){
				minitBicara = "15";
			} else if (Integer.valueOf(minitBicara) >= 30 && Integer.valueOf(minitBicara) < 45){
				minitBicara = "30";
			} else if (Integer.valueOf(minitBicara) >= 45 && Integer.valueOf(minitBicara) < 60){
				minitBicara = "45";
			}
			//System.out.println("----------------------4------------------");
			int jamTamat = Integer.valueOf(jamBicara);
			jamTamat = jamTamat + 1;
			//System.out.println("----------------------5------------------");
			int minitTamat = Integer.valueOf(minitBicara);
			//System.out.println("----------------------6------------------");
			startTime = jamBicara + ":" + minitBicara;
			if ("1".equals(jenisWaktu)){
				startTime = startTime + " AM";
				
				if (jamTamat < 12) {
					endTime = "" + jamTamat;
					while (endTime.length() <= 2){
						endTime = "0" + endTime;
					}						
					endTime = endTime + ":" + minitBicara + " AM";
				} else if (jamTamat == 12) {
					endTime = "" + jamTamat;
					while (endTime.length() <= 2){
						endTime = "0" + endTime;
					}						
					endTime = endTime + ":" + minitBicara + " PM";
				} else {
					jamTamat = jamTamat - 12;
					endTime = "" + jamTamat;
					while (endTime.length() <= 2){
						endTime = "0" + endTime;
					}						
					endTime = endTime + ":" + minitBicara + " PM";
				}
					
			} else {
				startTime = startTime + " PM";
				
				if (jamTamat < 12) {
					endTime = "" + jamTamat;
					while (endTime.length() <= 2){
						endTime = "0" + endTime;
					}						
					endTime = endTime + ":" + minitBicara + " PM";
				} else if (jamTamat < 12) {
					endTime = "" + jamTamat;
					while (endTime.length() <= 2){
						endTime = "0" + endTime;
					}						
					endTime = endTime + ":" + minitBicara + " PM";
				} else {
					jamTamat = jamTamat - 12;
					endTime = "" + jamTamat;
					while (endTime.length() <= 2){
						endTime = "0" + endTime;
					}						
					endTime = endTime + ":" + minitBicara + " PM";
				}
			}			
		}		
		//System.out.println("----------------------7------------------");
		Date startDateTime = parseDateTime(tarikhMula + " " + startTime);
		//System.out.println("----------------------8------------------");
		String eventDateEnd_ = tarikhMula;
		Date endDateTime = parseDateTime(eventDateEnd_ + " " + endTime);	
		//System.out.println("----------------------9------------------");
		db.begin();
		ActivityEvent activityEvent = (ActivityEvent) db.get("select a from ActivityEvent a where a.idPerbicaraan = '" + Long.valueOf(idPerbicaraan) + "'");
		//System.out.println("----------------------10------------------");
		if ( activityEvent == null ) {
			activityEvent = new ActivityEvent();
			activityEvent.setDescription(description);
			activityEvent.setEventDate(parseDate(tarikhMula));
			activityEvent.setStartDateTime(startDateTime);
			activityEvent.setEndDateTime(endDateTime);
			activityEvent.setLocationRemark(locationRemark);
			activityEvent.setRemark("");
			activityEvent.setDisplayColor(displayColor);
			activityEvent.setIdPerbicaraan(idPerbicaraan);
			activityEvent.setUserActivityEvent(userActivityEvent);
			activityEvent.setCreateDate(new Date());
			db.persist(activityEvent);
			if ( userActivityEvent.getEvents() == null ) userActivityEvent.setEvents(new ArrayList<ActivityEvent>());
			userActivityEvent.getEvents().add(activityEvent);
			
		} else {
			activityEvent.setDescription(description);
			activityEvent.setEventDate(parseDate(tarikhMula));
			activityEvent.setStartDateTime(startDateTime);
			activityEvent.setEndDateTime(endDateTime);
			activityEvent.setLocationRemark(locationRemark);
			activityEvent.setRemark("");
			activityEvent.setDisplayColor(displayColor);
			activityEvent.setIdPerbicaraan(idPerbicaraan);
		}			
		
		try {
			db.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
private static void deleteUserActivityEvent(Long idPerbicaraan) throws Exception {
		
		Db db = null;
		String sql = "";

		try {			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "DELETE FROM TBLACTIVITYEVENT WHERE ID_PERBICARAAN = '" + idPerbicaraan + "' AND DESCRIPTION LIKE '%KIV%' ";
			stmt.execute(sql);
			
		} finally {
			if (db != null)
				db.close();
		}		
	}
	

public static Date parseDateTime(String dateTxt) {
	if (dateTxt != null && !"".equals(dateTxt)) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy hh:mm a")
					.parse(dateTxt);
		} catch (ParseException e) {
			return null;
		}
	}
	return null;
}

public static Date parseDate(String dateTxt) {
	if (dateTxt != null && !"".equals(dateTxt)) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(dateTxt);
		} catch (ParseException e) {
			return null;
		}
	}
	return null;
}
	
}