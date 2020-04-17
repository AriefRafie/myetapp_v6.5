/**
 * 
 */
package ekptg.model.ppk;
 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.File;

public class FrmPrmhnnSek8DaftarSek8Data {
	static Logger myLogger = Logger.getLogger(FrmPrmhnnSek8DaftarSek8Data.class);
	
	private static Vector list = new Vector();
	 private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	 
	 public static Vector getData(){
		return list;	 
	  }
	 
	public static void setData(String id) throws Exception{
		Db db = null;
		list.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("f.id_Fail");
			r.add("f.no_Fail");
			r.add("d.id_Daerah");
			r.add("p.id_Permohonan");
			r.add("p.tarikh_Mohon");
			r.add("s.no_Kp_Baru");
			r.add("s.no_Kp_Lama");
			r.add("s.jenis_Kp");
			r.add("s.n0_Kp_Lain");
			r.add("s.id_Simati");
			r.add("s.nama_Simati");
			r.add("s.tarikh_Mati");
			r.add("pm.id_Pemohon");
			r.add("pm.nama_Pemohon");
			r.add("pm.no_kp_baru");
			r.add("pm.no_kP_lama");
			r.add("pm.jenis_Kp");
			r.add("pm.no_Kp_lain");
			
			r.add("pm.alamat_1");
			r.add("pm.alamat_2");
			r.add("pm.alamat_3");
			r.add("pm.poskod");
			r.add("pm.bandar");
			r.add("n.id_Negeri");
			r.add("n.nama_Negeri");
			r.add("d.nama_Daerah");
			r.add("p.seksyen");
			r.add("st.keterangan");
			r.add("p.id_Status");
			r.add("u.nama_pejabat");
			r.add("pm.id_negeri");
			/*r.add("p.jumlah_hta_tarikhmohon");
			r.add("p.jumlah_hta_tarikhmati");
			r.add("p.jumlah_ha_tarikhmohon");
			r.add("p.jumlah_ha_tarikhmati");
			r.add("p.jumlah_harta_tarikhmohon");
			r.add("p.jumlah_harta_tarikhmati");*/
		    //r.add("u.keterangan_unit_psk");
		    
			r.add("f.id_Negeri",r.unquote("n.id_Negeri(+)"));
			r.add("p.id_Daerahmhn",r.unquote("d.id_Daerah(+)"));
			r.add("p.id_Fail",r.unquote("f.id_Fail"));
			r.add("pm.id_Permohonan",r.unquote("p.id_Permohonan"));
			r.add("ps.id_Permohonan",r.unquote("p.id_Permohonan"));
			r.add("st.id_Status",r.unquote("p.id_Status"));
			r.add("d.id_daerah",r.unquote("u.id_daerah(+)"));
			r.add("s.id_simati",r.unquote("ps.id_simati"));
			
			r.add("p.id_Permohonan",id);
			
			sql = r.getSQLSelect("Tblpfdfail f, Tblppkpermohonan p, Tblrujnegeri n, Tblrujdaerah d, Tblppksimati s, Tblppkpemohon pm, Tblrujstatus st, tblrujpejabatjkptg u, Tblppkpermohonansimati ps");
			ResultSet rs = stmt.executeQuery(sql);
		    Hashtable h;
			
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("idFail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
		    	  h.put("noFail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
		    	  h.put("idDaerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
		    	  h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
		    	  h.put("tarikhMohon", sdf.format(rs.getDate("tarikh_Mohon"))==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
		    	  h.put("noKpBaru1", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(0,6));
		    	  h.put("noKpBaru2", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(6,8));
		    	  h.put("noKpBaru3", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(8,12));
		    	  h.put("noKpLama", rs.getString("no_Kp_Lama")==null?"":rs.getString("no_Kp_Lama"));
		    	  h.put("jenisKp", rs.getString(8)==null?"":rs.getString(8));
		    	  h.put("noKpLain", rs.getString("n0_Kp_Lain")==null?"":rs.getString("n0_Kp_Lain"));
		    	  h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
		    	  h.put("namaSimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
		    	  h.put("tarikhMati",  sdf.format(rs.getDate("tarikh_Mati"))==null?"": sdf.format(rs.getDate("tarikh_Mati")));
		    	  h.put("idPemohon", rs.getString("id_Pemohon")==null?"":rs.getString("id_Pemohon"));
		    	  h.put("namaPemohon", rs.getString("nama_Pemohon")==null?"":rs.getString("nama_Pemohon"));
		    	  h.put("noKpBaruPemohon1", rs.getString(15)==null?"":rs.getString(15).substring(0,6));
		    	  h.put("noKpBaruPemohon2", rs.getString(15)==null?"":rs.getString(15).substring(6,8));
		    	  h.put("noKpBaruPemohon3", rs.getString(15)==null?"":rs.getString(15).substring(8,12));
		    	  h.put("noKpLamaPemohon", rs.getString(16)==null?"":rs.getString(16));
		    	  h.put("jenisKpPemohon", rs.getString(17)==null?"":rs.getString(17));
		    	  h.put("noKpLainPemohon", rs.getString(18)==null?"":rs.getString(18));
		    	  h.put("alamat1", rs.getString("alamat_1")==null?"":rs.getString("alamat_1"));
		    	  h.put("alamat2", rs.getString("alamat_2")==null?"":rs.getString("alamat_2"));
		    	  h.put("alamat3", rs.getString("alamat_3")==null?"":rs.getString("alamat_3"));
		    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
		    	  h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
		    	  h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
		    	  h.put("namanegeri", rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
		    	  h.put("namadaerah", rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
		    	  h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
		    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
		    	  h.put("id_Status", rs.getString("id_Status")==null?"":rs.getString("id_Status"));
		    	  h.put("namaPejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
		    	  h.put("pmidnegeri", rs.getString(31)==null?"":rs.getString(31));
		    	  
		    	  list.addElement(h);
		}      
	}
	finally {
		if(db != null)db.close();
		}
}


	public static void addPermohonan(Hashtable data) throws Exception {
		//Azam add transaction on 04.02.2010
		Connection conn = null;
		Db db = null;
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try
			{			
				db = new Db();
		        conn = db.getConnection();
		        conn.setAutoCommit(false);
		        
				long idFail = DB.getNextID(db,"TBLPFDFAIL_SEQ");
				long idPemohon = DB.getNextID(db,"TBLPPKPEMOHON_SEQ");
				long idsimati = DB.getNextID(db,"TBLPPKSIMATI_SEQ");
				long idPermohonan = Long.parseLong((String)data.get("IdPermohonan"));
				long idSubUrusanStatus = DB.getNextID(db,"TBLRUJSUBURUSANSTATUS_SEQ");

				int UserIdPejabat = Integer.parseInt((String)data.get("userIdPejabat"));
				String userIdNeg = (String)data.get("userIdNeg");
				String userId = (String)data.get("userId");
				String NegId = (String)data.get("negId");
				String no_daerah = (String)data.get("no_daerah");
				String negeri = (String)data.get("negeri");
				String no_kpbaru_pemohon = (String)data.get("no_kpbaru_pemohon");
				String no_kplama_pemohon = (String)data.get("no_kplama_pemohon");
				String nama_simati = (String)data.get("nama_simati");
				String tarikh_masuk = (String)data.get("tarikh_masuk");
				String no_kpbaru_simati = (String)data.get("no_kp_baru");
				String no_kplama_simati = (String)data.get("no_kplama_simati");
				String sel_jeniskp_simati = (String)data.get("sel_jeniskp_simati");
				String no_kplain_simati = (String)data.get("no_kplain_simati");
				
			    String userIdKodDaerah = (String)data.get("userIdKodDaerah");
				String userIdKodNegeri = (String)data.get("userIdKodNegeri");
				String tarikh_simati = (String)data.get("tarikh_simati");
				String sel_jeniskp_pemohon = (String)data.get("sel_jeniskp_pemohon");
				String no_kplain_pemohon = (String)data.get("no_kplain_pemohon");
				String nama_pemohon = (String)data.get("nama_pemohon");
				String alamat1 = (String)data.get("alamat1");
				String alamat2 = (String)data.get("alamat2");
				String alamat3 = (String)data.get("alamat3");
				String bandar = (String)data.get("bandar");
				String poskod = (String)data.get("poskod");
				
				java.util.Calendar calendar = java.util.Calendar.getInstance();
				int getYear = calendar.get(java.util.Calendar.YEAR);
				
				String X = String.format("%04d",File.getSeqNo(db,2,382,0,Integer.parseInt(userIdNeg),Integer.parseInt(no_daerah),false,false,getYear,0));
				
				if (no_daerah.length() < 1){
					no_daerah = "0"+no_daerah;
				}else{
					no_daerah = no_daerah;
				}
				if (userIdNeg.length() < 1){
					userIdNeg = "0"+userIdNeg;
				}else{
					userIdNeg = userIdNeg;
				}
				if (negeri.equals("")){
					negeri = "0";
				}
				
				String getNoFile = "JKPTG/PK/"+ userIdKodNegeri + "/"+ userIdKodDaerah + "/"+X+"/"+getYear;				
				
				tarikh_masuk = (String)data.get("tarikh_masuk");
				tarikh_simati = (String)data.get("tarikh_simati");
				String tarikh_mohon = "to_date('" + tarikh_masuk + "','dd/MM/yyyy')";
		        String tarikh_mati = "to_date('" + tarikh_simati + "','dd/MM/yyyy')";
		        int idNeg = Integer.parseInt(NegId);		
		        
		        

				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("id_fail",idFail);
				r.add("id_seksyen",2);
				r.add("id_urusan",382);
				r.add("tarikh_daftar_fail",r.unquote("sysdate"));
				r.add("tarikh_masuk",r.unquote("sysdate")); 
				r.add("tajuk_fail",getNoFile);
				r.add("no_fail",getNoFile);
				r.add("id_negeri",Integer.parseInt(userIdNeg));
				r.add("id_suburusan",59);
				r.add("flag_fail",1);
				r.add("id_masuk",userId);
				sql = r.getSQLInsert("tblpfdfail");
				stmt.executeUpdate(sql);
				
				
				//Statement stmt = db.getStatement();
				//SQLRenderer r1 = new SQLRenderer();
				r.clear();
				r.add("id_simati",idsimati);
				r.add("nama_simati",nama_simati);
				r.add("no_kp_baru",no_kpbaru_simati);
				r.add("no_kp_lama",no_kplama_simati);
				r.add("jenis_kp",sel_jeniskp_simati);
				r.add("n0_kp_lain",no_kplain_simati);
				r.add("tarikh_mati",r.unquote(tarikh_mati));
				r.add("id_permohonan",idPermohonan);
				r.add("id_masuk",userId);
				sql1 = r.getSQLInsert("tblppksimati");
				stmt.executeUpdate(sql1);
			
				
				//Statement stmtc = db.getStatement();
				//SQLRenderer r2 = new SQLRenderer();
				r.add("id_pemohon",idPemohon);
				r.add("no_kp_baru",no_kpbaru_pemohon);
				r.add("no_kp_lama",no_kplama_pemohon);
				r.add("jenis_kp",sel_jeniskp_pemohon);
				r.add("no_kp_lain",no_kplain_pemohon);
				r.add("nama_pemohon",nama_pemohon);
				r.add("alamat_1",alamat1);
				r.add("alamat_2",alamat2);
				r.add("alamat_3",alamat3);
				r.add("poskod",poskod);
				r.add("bandar",bandar);
				r.add("id_negeri",negeri);
				r.add("id_permohonan",idPermohonan);
				r.add("id_masuk",userId);
				sql2 = r.getSQLInsert("tblppkpemohon");
				stmt.executeUpdate(sql2);
				
				
				//Statement stmtd = db.getStatement();
				//SQLRenderer r3 = new SQLRenderer();
				r.clear();
				r.add("id_permohonan",idPermohonan);
				r.add("id_daerahmhn",no_daerah);
				r.add("id_status",8);
				r.add("id_fail",idFail);
				r.add("seksyen",8);
				r.add("tarikh_masuk",r.unquote("sysdate"));
				r.add("tarikh_mohon",r.unquote(tarikh_mohon));
				r.add("id_unitpskawal",UserIdPejabat);
				r.add("id_masuk",userId);
				sql3 = r.getSQLInsert("tblppkpermohonan");
				stmt.executeUpdate(sql3);

				
				//Statement stmte = db.getStatement();
				//SQLRenderer r4 = new SQLRenderer();
				r.add("ID_SUBURUSANSTATUS",idSubUrusanStatus);
				r.add("ID_SUBURUSAN",59);
				r.add("ID_STATUS",8);
				r.add("ID_MASUK",userId);
				r.add("TARIKH_MASUK",r.unquote("sysdate"));
				r.add("ID_KEMASKINI",userId);
				r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql4 = r.getSQLInsert("tblrujsuburusanstatus");
				stmt.executeUpdate(sql4);
							
			
			//	Statement stmtF = db.getStatement();
			//	SQLRenderer r5 = new SQLRenderer();
				//Azam remark - let oracle do the seq
				//r5.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
				r.add("ID_PERMOHONAN",idPermohonan);
				r.add("ID_SUBURUSANSTATUS",idSubUrusanStatus);
				r.add("AKTIF",1);
				r.add("ID_MASUK",userId);
				r.add("TARIKH_MASUK",r.unquote("sysdate"));
				r.add("ID_KEMASKINI",userId);
				r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql5 = r.getSQLInsert("tblrujsuburusanstatusfail");
				stmt.executeUpdate(sql5);	
				
				conn.commit();
				
			}  catch (SQLException se) { 
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	se.printStackTrace();
		    	throw new Exception("Ralat Jana Fail:"+se.getMessage());
		    }finally {
		    	if (conn != null) conn.close(); 
				if (db != null)	db.close();
			}
	}
	
	public static void updatePermohonan(Hashtable data) throws Exception {
		//Razman add transaction on 06.03.2010
		Db db = null;
		Connection conn = null;
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
			try
			{	
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String IdFail = (String)data.get("IdFail");
			    String IdSimati = (String)data.get("IdSimati");
				String IdPemohon = (String)data.get("IdPemohon");
				String IdPermohonan = (String)data.get("IdPermohonan");
			    String no_daerah = (String)data.get("no_daerah");
				String negeri = (String)data.get("negeri");
				String no_kpbaru_pemohon = (String)data.get("no_kpbaru_pemohon");
				String no_kplama_pemohon = (String)data.get("no_kplama_pemohon");
				String nama_simati = (String)data.get("nama_simati");
				String tarikh_masuk = (String)data.get("tarikh_masuk");
				String tarikh_daftar = (String)data.get("tarikh_daftar");
				String no_kpbaru_simati = (String)data.get("no_kp_baru");
				String no_kplama_simati = (String)data.get("no_kplama_simati");
				String sel_jeniskp_simati = (String)data.get("sel_jeniskp_simati");
				String no_kplain_simati = (String)data.get("no_kplain_simati");
				String tarikh_simati = (String)data.get("tarikh_simati");
				String sel_jeniskp_pemohon = (String)data.get("sel_jeniskp_pemohon");
				String no_kplain_pemohon = (String)data.get("no_kplain_pemohon");
				String nama_pemohon = (String)data.get("nama_pemohon");
				String alamat1 = (String)data.get("alamat1");
				String alamat2 = (String)data.get("alamat2");
				String alamat3 = (String)data.get("alamat3");
				String bandar = (String)data.get("bandar");
				String poskod = (String)data.get("poskod");	
				
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		        Date date = new Date();
		        String currentDate = dateFormat.format(date);
				
				String tarikhdaftar = "to_date('" + tarikh_daftar + "','dd/MM/yyyy')";
				String tarikhsimati = "to_date('" + tarikh_simati + "','dd/MM/yyyy')";
				
						        					
		         
				Statement stmt = db.getStatement();
				
				sql = "UPDATE TBLPFDFAIL SET tarikh_daftar_fail=sysdate, " +
				"tarikh_kemaskini = sysdate where id_fail = "+ IdFail +" ";
				stmt.executeUpdate(sql);
				
			
				//Statement stmtA = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				r.update("id_simati", IdSimati);
				r.add("nama_simati",nama_simati);
				r.add("no_kp_baru",no_kpbaru_simati);
				r.add("no_kp_lama",no_kplama_simati);
				r.add("jenis_kp",sel_jeniskp_simati);
				r.add("n0_kp_lain",no_kplain_simati);
				r.add("tarikh_mati",r.unquote(tarikhsimati));
				r.add("tarikh_masuk",r.unquote(tarikhdaftar));
				sql1 = r.getSQLUpdate("tblppksimati");
			    stmt.executeUpdate(sql1);
						
				
			//	Statement stmtc = db.getStatement();
				sql3 = "UPDATE TBLPPKPEMOHON SET no_kp_baru='"+ no_kpbaru_pemohon +"', no_kp_lama='"+ no_kplama_pemohon +"',jenis_kp='"+ sel_jeniskp_pemohon +"', no_kp_lain='"+ no_kplain_pemohon +"', nama_pemohon='"+ nama_pemohon +"', alamat_1='"+ alamat1 +"', alamat_2='"+ alamat2 +"', alamat_3='"+ alamat3 +"', poskod='"+ poskod +"', bandar='"+ bandar +"', id_negeri='"+ negeri +"' where id_pemohon = "+ IdPemohon +"";
				stmt.executeUpdate(sql3);
				
				/*db = new Db();
				Statement stmtd = db.getStatement();
				sql4 = "UPDATE TBLPPKPERMOHONAN set id_daerahmhn = "+ no_daerah +" where " +
				"id_permohonan = "+ IdPermohonan +" and id_fail="+ IdFail +"";
				stmtd.executeUpdate(sql4);	*/
				conn.commit();	
			}catch (SQLException se) { 
				try 
				{conn.rollback();
				} 
				catch (SQLException se2) 
				{
				throw new Exception("Rollback error :"+se2.getMessage());
				}
				se.printStackTrace();
				throw new Exception("Ralat Jana Fail:"+se.getMessage());
				}finally {
				if (conn != null) conn.close(); 
				if (db != null)	db.close();
				}
	}
	
	 public static Vector getJenisKp()throws Exception {
		    Db db = null;
		    String sql = "Select id_jenisnopb,keterangan" +
			" from tblrujjenisnopb where id_jenisnopb in(4,5,6,7) ";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("id_jenisnopb");
		      r.add("keterangan");
		
		      //sql = r.getSQLSelect(sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      Hashtable h;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("id", rs.getString("id_jenisnopb"));
		    	  h.put("keterangan", rs.getString("keterangan"));
		    	  list.addElement(h);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }

 
	//*** query data have 'pemilik' from database
		public static void setSemak(int idPermohonan)throws Exception {
			
		    Db db = null;
		    list.clear();
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		 
		      r.add("id_semakanhantar");
		      r.add("id_semakansenarai");
		      r.add("id_pemohon");
		      r.add("pemohon");
		      r.add("catatan");      

		      r.set("id_permohonan", idPermohonan);
		      
		      sql = r.getSQLSelect("tblsemakanhantar");
		      ResultSet rs = stmt.executeQuery(sql);
		      //Vector list = new Vector();
		      Hashtable h;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("idSemakanHantar", rs.getString("id_semakanhantar"));
		    	  h.put("idKementerian", rs.getString("id_semakansenarai"));
		    	  h.put("idpemohon", rs.getString("id_pemohon"));
		    	  h.put("pemohon", rs.getString("pemohon"));
		    	  h.put("catatan", rs.getString("catatan"));
		    	  list.addElement(h);
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }

		public static Vector getSemak(){
			  return list;
		  }
	 
	//*** simpan data from database
	  public static int simpanSemak(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		    try
		    {
		      long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
			  String txtNomborSijil = (String)data.get("txtNomborSijil");
			  String idpermohonan = (String)data.get("idpermohonan");

		      db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();  
			  r.add("id_semakansenarai",idpermohonan);
		      r.add("catatan",txtNomborSijil);
		      sql = r.getSQLInsert("tblsemakanhantar");
			  //stmt.executeUpdate(sql);
		      return (int)idPermohonan;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	  
	//*** update data from database
	  public static int updateSemak(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		    try
		    {
		    	long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");      
			      return (int)idPermohonan;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	  

	public static Vector getJenisHa()throws Exception {
		  Db db = null;
		    String sql = "Select id_jenisha,kod,keterangan" +
			" from tblppkrujjenisha";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("id_jenisnopb");
		      r.add("keterangan");
		
		      //sql = r.getSQLSelect(sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector listjenisha = new Vector();
		      Hashtable h;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("idjenisha", rs.getString("id_jenisha"));
		    	  h.put("kod", rs.getString("kod"));
		    	  h.put("keterangan", rs.getString("keterangan"));
		    	  listjenisha.addElement(h);
		      }
		      return listjenisha;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
	
	public static void addHa(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	    try
	    {
	    	
	    	String id = (String)data.get("id");
			int id1 = Integer.parseInt((String)data.get("id1"));
			int socJenisHartaAlih = Integer.parseInt((String)data.get("socJenisHartaAlih"));
			String txtBhgnSimati1 = (String)data.get("basimati");
			String txtBhgnSimati2 = (String)data.get("bbsimati");
			String txtNoRujukan = (String)data.get("txtNoRujukan");
			double txtNilaiTarikhMati = (Double)data.get("txtNilaiTarikhMati");
			String txtNoSijil = (String)data.get("txtNoSijil");
			double txtNilaiTarikhMohon = (Double)data.get("txtNilaiTarikhMohon");
			String txtBilUnit = (String)data.get("txtBilUnit");
			String txtNilaiSeunit = (String)data.get("txtNilaiSeunit");
			String txtAgensi = (String)data.get("txtAgensi");
			String txtCatatan = (String)data.get("txtCatatan");			
			
			String bil = (String)data.get("bil");
			String idpermohonansimati = (String)data.get("idpermohonansimati");
	    	long idInsert = DB.getNextID("TBLPPKHA_SEQ");
	    	
	    	//String alamat1 = (String)data.get("alamat1");
	    	//String alamat2 = (String)data.get("alamat2");
	    	//String alamat3 = (String)data.get("alamat3");
	    	//String poskod = (String)data.get("poskod");
	    	//String idnegeri = (String)data.get("idnegeri");
	    	//String iddaerah = (String)data.get("iddaerah");

	       db = new Db();
		   Statement stmt = db.getStatement();
		   SQLRenderer r = new SQLRenderer();  
		   r.add("id_ha",idInsert);
		   r.add("bil",bil);
		   r.add("id_simati",id1);
		   r.add("id_jenisha",socJenisHartaAlih);
		   r.add("jenama",txtAgensi);
		   r.add("no_daftar",txtNoRujukan);
		   r.add("no_sijil",txtNoSijil);
		   r.add("bil_unit",txtBilUnit);
		   r.add("nilai_ha_tarikhmohon",txtNilaiTarikhMohon);
		   r.add("nilai_ha_tarikhmati",txtNilaiTarikhMati);
		   r.add("ba_simati",txtBhgnSimati1);
		   r.add("bb_simati",txtBhgnSimati2);
		   r.add("nilai_seunit",txtNilaiSeunit);
		   //r.add("alamat_ha1",alamat1);
		   //r.add("alamat_ha2",alamat2);
		   //r.add("alamat_ha3",alamat3);
		   //r.add("poskod",poskod);
		   //r.add("id_negeri",idnegeri);
		   //r.add("id_daerah",iddaerah);
		   r.add("id_permohonansimati",Integer.parseInt(idpermohonansimati));
		   r.add("catatan",txtCatatan);
	       sql = r.getSQLInsert("tblppkha");
	       System.out.println("tblppkha-->>"+sql);
		   stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	  
private static Vector listDataHa = new Vector();
	
	public static void setDataHa(String id) throws Exception{
		Db db = null;
		listDataHa.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("h.id_Ha");
			r.add("h.bil");
			r.add("h.id_Simati");
			r.add("h.id_Jenisha");
			r.add("h.no_Daftar");
			r.add("h.nilai_ha_tarikhmohon");
			r.add("h.nilai_ha_tarikhmati");
			r.add("h.no_sijil");
			r.add("j.kod");
			r.add("j.keterangan");

			r.add("h.id_Jenisha",r.unquote("j.id_jenisha"));
			r.add("h.id_Simati",id);

				
			sql = r.getSQLSelect("Tblppkha h, Tblppkrujjenisha j");
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Ha", rs.getString("id_Ha")==null?"":rs.getString("id_Ha"));
				h.put("id_Jenisha", rs.getString("id_Jenisha")==null?"":rs.getString("id_Jenisha"));
				h.put("id_Simati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("nosijil", rs.getString("no_sijil")==null?"":rs.getString("no_sijil"));
				h.put("noDaftar", rs.getString("no_Daftar")==null?"":rs.getString("no_Daftar"));
				h.put("Kod", rs.getString("kod")==null?"":rs.getString("kod"));
				h.put("Keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("nilai_tarikhmohon", rs.getString("nilai_ha_tarikhmohon")==null?"":rs.getDouble("nilai_ha_tarikhmohon"));
				h.put("nilai_tarikhmati", rs.getString("nilai_ha_tarikhmati")==null?"":rs.getDouble("nilai_ha_tarikhmati"));				
				listDataHa.addElement(h);
				bil++;	
			}
			//return list;
		}finally {
			if(db != null) db.close();
		}
		}
	
	public static Vector getDataHa(){
		  return listDataHa;
	  }
	
	private static Vector listOverallSum = new Vector();
	
	public static void setOverallSum(String id) throws Exception{
		Db db = null;
		listOverallSum.clear();
		String sql = "Select sum(nilai) as nilai from ((Select h.nilai_ha_tarikhmohon as nilai from tblppkha h where h.id_simati = "+ id +" ) " +
				     "union (Select k.nilai_hta_tarikhmohon as nilai from Tblppkhta k where k.id_simati = '"+ id +"' )) X";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("nilaibesar", rs.getString("nilai")==null?"":Double.parseDouble(rs.getString("nilai")));
				listOverallSum.addElement(h);
				bil++;	
			}
		}finally {
			if(db != null) db.close();
		}
		}
	
	public static Vector getOverallSum(){
		  return listOverallSum;
	  }
	
private static Vector listOverallSumMati = new Vector();
	
	public static void setOverallSumMati(String id) throws Exception{
		Db db = null;
		listOverallSumMati.clear();
		String sql = "Select sum(nilai) as nilaiMati from ((Select h.nilai_ha_tarikhmati as nilai from tblppkha h where h.id_simati = '"+ id +"' ) " +
				     "union (Select k.nilai_hta_tarikhmati as nilai from Tblppkhta k where k.id_simati = '"+ id +"' )) X";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("nilaibesarmati", rs.getString("nilaiMati")==null?"":Double.parseDouble(rs.getString("nilaiMati")));
				listOverallSumMati.addElement(h);
				bil++;	
			}
			//return list;
		}finally {
			if(db != null) db.close();
		}
		}
	
	public static Vector getOverallSumMati(){
		  return listOverallSumMati;
	  }
	
private static Vector listSelectedDataHa = new Vector();
	
	public static Vector setSelectedDataHa(String id1, String id3) throws Exception{
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
						
			r.add("h.id_Ha");
			r.add("h.bil");
			r.add("h.id_Simati");
			r.add("h.id_Jenisha");
			r.add("h.id_negeri");
			r.add("h.id_daerah");
			r.add("h.jenama");
			r.add("h.no_Daftar");
			r.add("h.no_sijil");
			r.add("h.bil_unit");
			r.add("h.tarikh_harta");
			r.add("h.alamat_ha1");
			r.add("h.alamat_ha2");
			r.add("h.alamat_ha3");
			r.add("h.poskod");
			r.add("h.nilai_ha_tarikhmohon");
			r.add("h.nilai_ha_tarikhmati");
			r.add("h.ba_simati");
			r.add("h.bb_simati");
			r.add("h.catatan");
			r.add("h.nilai_seunit");
			r.add("h.id_masuk");
			r.add("h.tarikh_masuk");
			r.add("h.id_kemaskini");
			r.add("h.tarikh_kemaskini");
			r.add("h.id_db");
			r.add("j.kod");
			r.add("j.keterangan");

			r.add("h.id_Jenisha",r.unquote("j.id_jenisha"));
			r.add("h.id_Simati",id1);
			r.add("h.id_Ha",id3);

				
			sql = r.getSQLSelect("Tblppkha h, Tblppkrujjenisha j");
			System.out.println(" ha -->>"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector listSelectedDataHa = new Vector();
			Hashtable h;
			int bil = 1;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Ha", rs.getString("id_Ha")==null?"":rs.getString("id_Ha"));
				h.put("id_Jenisha", rs.getString("id_Jenisha")==null?"":rs.getString("id_Jenisha"));
				h.put("id_Simati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("idnegeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
				h.put("iddaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
				h.put("jenama", rs.getString("jenama")==null?"":rs.getString("jenama"));
				h.put("noDaftar", rs.getString("no_Daftar")==null?"":rs.getString("no_Daftar"));
				h.put("nosijil", rs.getString("no_sijil")==null?"":rs.getString("no_sijil"));
				h.put("bilunit", rs.getString("bil_unit")==null?"":rs.getString("bil_unit"));
				h.put("tarikhharta", rs.getString("tarikh_harta")==null?"":rs.getString("tarikh_harta"));
				h.put("alamatha1", rs.getString("alamat_ha1")==null?"":rs.getString("alamat_ha1"));
				h.put("alamatha2", rs.getString("alamat_ha2")==null?"":rs.getString("alamat_ha2"));
				h.put("alamatha3", rs.getString("alamat_ha3")==null?"":rs.getString("alamat_ha3"));
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
				h.put("nilaitarikhmohon", rs.getString("nilai_ha_tarikhmohon")==null?"":rs.getDouble("nilai_ha_tarikhmohon"));
				h.put("nilaitarikhmati", rs.getString("nilai_ha_tarikhmati")==null?"":rs.getDouble("nilai_ha_tarikhmati"));
				h.put("basimati", rs.getString("ba_simati")==null?"":rs.getString("ba_simati"));
				h.put("bbsimati", rs.getString("bb_simati")==null?"":rs.getString("bb_simati"));
				h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
				h.put("nilaiseunit", rs.getString("nilai_seunit")==null?"":rs.getString("nilai_seunit"));		
				h.put("Kod", rs.getString("kod")==null?"":rs.getString("kod"));
				h.put("Keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				listSelectedDataHa.addElement(h);
				bil++;	
			}
			return listSelectedDataHa;
		}finally {
			if(db != null) db.close();
		}
		}
	

	public static void kemaskiniHa(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	    try
	    {
	    	int id1 = Integer.parseInt((String)data.get("id1"));
	    	int id3 = Integer.parseInt((String)data.get("id3")); 	
			int socJenisHartaAlih = Integer.parseInt((String)data.get("socJenisHartaAlih"));
			String txtBhgnSimati1 = (String)data.get("txtBhgnSimati1");
			String txtBhgnSimati2 = (String)data.get("txtBhgnSimati2");
			String txtNoRujukan = (String)data.get("txtNoRujukan");
			String txtNilaiTarikhMati = (String)data.get("txtNilaiTarikhMati");
			String txtNoSijil = (String)data.get("txtNoSijil");
			String txtNilaiTarikhMohon = (String)data.get("txtNilaiTarikhMohon");
			String txtBilUnit = (String)data.get("txtBilUnit");
			String txtNilaiSeunit = (String)data.get("txtNilaiSeunit");
			String Agensi = (String)data.get("Agensi");
			String txtCatatan = (String)data.get("txtCatatan");
			String bil = (String)data.get("bil");
			Double valuenilaimohon = 0.00;
			Double valuenilaimati = 0.00;
			valuenilaimohon = Double.parseDouble((String)data.get("txtNilaiTarikhMohon"));
			valuenilaimati = Double.parseDouble((String)data.get("txtNilaiTarikhMati"));

	       db = new Db();
		   Statement stmt = db.getStatement();
		   SQLRenderer r = new SQLRenderer();
		   r.update("id_simati",id1);
		   r.update("id_ha",id3);
		   r.add("bil",bil);
		   r.add("id_jenisha",socJenisHartaAlih);
		   r.add("jenama",Agensi);
		   r.add("no_daftar",txtNoRujukan);
		   r.add("no_sijil",txtNoSijil);
		   r.add("bil_unit",txtBilUnit);
		   //r.add("nilai_ha_tarikhmohon",txtNilaiTarikhMohon);
		   //r.add("nilai_ha_tarikhmati",txtNilaiTarikhMati);
		   r.add("nilai_ha_tarikhmohon",valuenilaimohon);
		   r.add("nilai_ha_tarikhmati",valuenilaimati);
		   r.add("ba_simati",txtBhgnSimati1);
		   r.add("bb_simati",txtBhgnSimati2);
		   r.add("nilai_seunit",txtNilaiSeunit);
		   r.add("catatan",txtCatatan);
	       sql = r.getSQLUpdate("tblppkha");
	       stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static void deleteDataHa(String id1, String id3) throws Exception{
	    Db db = null;
	    try
	    {
	       db = new Db();
		   Statement stmt = db.getStatement();
		  // SQLRenderer r = new SQLRenderer();
		    String sql = "delete from tblppkha where id_ha = '"+ id3 +"' and id_simati = '"+ id1 +"'";
	       //sql = r.getSQLUpdate("tblppkha");
		   stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	//kira jumlah nilai mohon ha
private static Vector listSumDataHa = new Vector();
	
	public static void setSumDataHa(int id2) throws Exception{
		Db db = null;
		listSumDataHa.clear();
		String sql = "Select sum(nilai_ha_tarikhmohon) from Tblppkha where id_Simati = "+ id2 +"";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
						
			r.add("h.id_Ha");
			r.add("h.bil");
			r.add("h.id_Simati");
			r.add("h.id_Jenisha");
			r.add("h.id_negeri");
			r.add("h.id_daerah");
			r.add("h.jenama");
			r.add("h.no_Daftar");
			r.add("h.no_sijil");
			r.add("h.bil_unit");
			r.add("h.tarikh_harta");
			r.add("h.alamat_ha1");
			r.add("h.alamat_ha2");
			r.add("h.alamat_ha3");
			r.add("h.poskod");
			r.add("h.nilai_ha_tarikhmohon");
			r.add("h.nilai_ha_tarikhmati");
			r.add("h.ba_simati");
			r.add("h.bb_simati");
			r.add("h.catatan");
			r.add("h.nilai_seunit");
			r.add("h.id_masuk");
			r.add("h.tarikh_masuk");
			r.add("h.id_kemaskini");
			r.add("h.tarikh_kemaskini");
			r.add("h.id_db");
			r.add("j.kod");
			r.add("j.keterangan");

			r.add("h.id_Jenisha",r.unquote("j.id_jenisha"));
			r.add("h.id_Simati",id2);
				
			//sql = r.getSQLSelect("Tblppkha h, Tblppkrujjenisha j");
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("sum_nilaimohon", rs.getString("nilai_ha_tarikhmohon")==null?"":lebah.util.Util.formatDecimal(rs.getDouble("nilai_ha_tarikhmohon")));
				listSumDataHa.addElement(h);
				bil++;	
			}
			//return list;
		}finally {
			if(db != null) db.close();
		}
		}
	
	public static Vector getSumDataHa(){
		  return listSumDataHa;
	  }

	public static Vector getListtaraf() throws Exception {
		    Db db = null;
//		    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_Tarafkptg");
		      r.add("kod");
		      r.add("keterangan");
		      
		     
		      sql = r.getSQLSelect("Tblppkrujtarafkptg","kod");
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("id_Tarafkptg", rs.getInt("id_Tarafkptg"));
		     
		       
		        if(rs.getString("kod") == null){
		        	h.put("kod", "");
		        }else{
		        	h.put("kod", rs.getString("kod"));
		        }
		        
		        if(rs.getString("keterangan") == null){
		        	h.put("keterangan", "");
		        }else{
		        	h.put("keterangan", rs.getString("keterangan"));
		        }
		       /* 
		        if(rs.getString("description") == null){
		        	h.put("description", "");
		        }else{
		        	h.put("description", rs.getString("description"));
		        }
		       */
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	 
	 public static Vector getListsaudara() throws Exception {
		    Db db = null;
//		    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_Saudara");
		      r.add("kod");
		      r.add("keterangan");
		      r.add("jantina");
		      
		      sql = r.getSQLSelect("Tblppkrujsaudara","keterangan");
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("id_Saudara", rs.getInt("id_Saudara"));
		     
		       
		        if(rs.getString("kod") == null){
		        	h.put("kod", "");
		        }else{
		        	h.put("kod", rs.getString("kod"));
		        }
		        
		        if(rs.getString("keterangan") == null){
		        	h.put("keterangan", "");
		        }else{
		        	h.put("keterangan", rs.getString("keterangan"));
		        }
		        if(rs.getString("jantina") == null){
		        	h.put("jantina", "");
		        }else{
		        	h.put("jantina", rs.getString("jantina"));
		        }
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	
	
	 public static Vector getListnegeri() throws Exception {
		    Db db = null;
//		    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_Negeri");
		      r.add("nama_Negeri");
		      r.add("kod_Negeri");
		      
		     
		      sql = r.getSQLSelect("Tblrujnegeri","kod_Negeri");
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("id_Negeri", rs.getInt("id_Negeri"));
		     
		       
		        if(rs.getString("nama_Negeri") == null){
		        	h.put("nama_Negeri", "");
		        }else{
		        	h.put("nama_Negeri", rs.getString("nama_Negeri"));
		        }
		        if(rs.getString("kod_Negeri") == null){
		        	h.put("kod_Negeri", "");
		        }else{
		        	h.put("kod_Negeri", rs.getString("kod_Negeri"));
		        }
		       /* 
		        if(rs.getString("description") == null){
		        	h.put("description", "");
		        }else{
		        	h.put("description", rs.getString("description"));
		        }
		       */
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	 
	 public static Vector getListbuktimati() throws Exception {
		    Db db = null;
//		    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_Buktimati");
		      r.add("keterangan");
		      r.add("kod");
		      
		     
		      sql = r.getSQLSelect("Tblppkrujbuktimati","kod");
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("id_Buktimati", rs.getInt("id_Buktimati"));
		     
		       
		        if(rs.getString("keterangan") == null){
		        	h.put("keterangan", "");
		        }else{
		        	h.put("keterangan", rs.getString("keterangan"));
		        }
		        
		        if(rs.getString("kod") == null){
		        	h.put("kod", "");
		        }else{
		        	h.put("kod", rs.getString("kod"));
		        }
		       /* 
		        if(rs.getString("description") == null){
		        	h.put("description", "");
		        }else{
		        	h.put("description", rs.getString("description"));
		        }
		       */
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }

	public static void add(Hashtable data) throws Exception {
		Db db = null;
			String sql = "";
			try
			{
				String noFail = (String)data.get("no_Fail"); 
				String tajukFail = (String)data.get("tajuk_Fail");
				
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("no_Fail",noFail);
				r.add("tajuk_Fail",tajukFail);
				sql = r.getSQLInsert("tblpfdfail");
				
				stmt.executeUpdate(sql);
			}finally {
				if(db != null) db.close();
			}
			
	}
	
private static Vector listChckId = new Vector();
	
	public static void checkData(int id) throws Exception{
		Db db = null;
		listChckId.clear();
		String sql = "Select count(id_permohonan) as ids from tblppkpemohon where id_permohonan = "+ id +"";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("count_id", rs.getString("ids")==null?"":rs.getString("ids"));

				listChckId.addElement(h);
				bil++;	
			}
			//return list;
		}finally {
			if(db != null) db.close();
		}
		}
	
	public static Vector getId(){
		  return listChckId;
	  }
	
	public static void updateDataNilai(String id, String id1, String idpermohonansimati) throws Exception{
	    Db db = null;
	    try
	    {
	       db = new Db();
		   Statement stmt = db.getStatement();
		   	String sql = "update tblppkpermohonan set " +
		   			"JUMLAH_HTA_TARIKHMOHON = (Select sum(a.NILAI_HTA_TARIKHMOHON) from tblppkhta a where a.id_permohonansimati = '"+ idpermohonansimati +"'), " +
		   			"JUMLAH_HTA_TARIKHMATI = (Select sum(a.NILAI_HTA_TARIKHMATI) from tblppkhta a where a.id_permohonansimati = '"+ idpermohonansimati +"'), " +
		   			"JUMLAH_HA_TARIKHMOHON = (Select sum(b.NILAI_HA_TARIKHMOHON) from tblppkha b where b.id_permohonansimati = '"+ idpermohonansimati +"')," +
		   			"JUMLAH_HA_TARIKHMATI = (Select sum(b.NILAI_HA_TARIKHMATI) from tblppkha b where b.id_permohonansimati = '"+ idpermohonansimati +"')," +
		   			"JUMLAH_HARTA_TARIKHMOHON = (Select sum(nilai) as nilai from ((Select h.nilai_ha_tarikhmohon as nilai from " +
		   			"tblppkha h where h.id_Permohonansimati = '"+ idpermohonansimati +"' ) union (Select k.nilai_hta_tarikhmohon as nilai from " +
		   			"Tblppkhta k where k.id_Permohonansimati = '"+ idpermohonansimati +"' )) X), JUMLAH_HARTA_TARIKHMATI = (Select sum(nilaib) as nilaib " +
		   			"from ((Select h.nilai_ha_tarikhmati as nilaib from tblppkha h where h.id_Permohonansimati = '"+ idpermohonansimati +"' ) union " +
		   			"(Select k.nilai_hta_tarikhmati as nilaib from Tblppkhta k where k.id_Permohonansimati = '"+ idpermohonansimati +"' )) Y) " +
		   			"where id_permohonan = '"+ id +"'";
		   			stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
private static Vector listGetId = new Vector();
	
	public static void setGetId(int id) throws Exception{
		Db db = null;
		listGetId.clear();
		String sql = "Select a.id_pemohon, p.id_simati From tblppkpermohonan b, tblppkpemohon a, " +
				"tblppksimati p Where b.id_permohonan = a.id_permohonan and b.id_permohonan = p.id_permohonan " +
				"and b.id_permohonan = "+ id +"";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("idpemohon", rs.getString("id_pemohon")==null?"":rs.getString("id_pemohon"));
				h.put("idsimati", rs.getString("id_simati")==null?"":rs.getString("id_simati"));
				listGetId.addElement(h);
				bil++;	
			}
			//return list;
		}finally {
			if(db != null) db.close();
		}
		}
	
	public static Vector getIds(){
		  return listGetId;
	  }
	
	public static Vector getListDaerahbyNegeri(int idnegeri) throws Exception {
	    Db db = null;
//	    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Daerah");
	      r.add("nama_Daerah");
	      r.add("kod_Daerah");
	      r.add("id_Negeri");
	      
	      
	      r.add("id_Negeri",idnegeri);
	     
	      sql = r.getSQLSelect("Tblrujdaerah","kod_Daerah");
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Daerah"));
	     
	       
	        if(rs.getString("nama_Daerah") == null){
	        	h.put("nama", "");
	        }else{
	        	h.put("nama", rs.getString("nama_Daerah"));
	        }
	        if(rs.getString("kod_Daerah") == null){
	        	h.put("kod", "");
	        }else{
	        	h.put("kod", rs.getString("kod_Daerah"));
	        }
	       
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	public static Vector getListMukimbyDaerah(int iddaerah) throws Exception {
	    Db db = null;
//	    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Mukim");
	      r.add("nama_Mukim");
	      r.add("kod_Mukim");
	      r.add("id_Daerah");
	      
	      r.add("id_Daerah",iddaerah);
	     
	      sql = r.getSQLSelect("Tblrujmukim","kod_Mukim");
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Mukim"));
	     
	       
	        if(rs.getString("nama_Mukim") == null){
	        	h.put("nama", "");
	        }else{
	        	h.put("nama", rs.getString("nama_Mukim"));
	        }
	        if(rs.getString("kod_Mukim") == null){
	        	h.put("kod", "");
	        }else{
	        	h.put("kod", rs.getString("kod_Mukim"));
	        }
	       
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	public static Vector getListDaerah() throws Exception {
	    Db db = null;
//	    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Daerah");
	      r.add("nama_Daerah");
	      r.add("kod_Daerah");
	      //r.add("id_Negeri");
	      
	      
	     
	     
	      sql = r.getSQLSelect("Tblrujdaerah","kod_Daerah");
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Daerah"));
	     
	       
	        if(rs.getString("nama_Daerah") == null){
	        	h.put("nama", "");
	        }else{
	        	h.put("nama", rs.getString("nama_Daerah"));
	        }
	        if(rs.getString("kod_Daerah") == null){
	        	h.put("kod", "");
	        }else{
	        	h.put("kod", rs.getString("kod_Daerah"));
	        }
	       
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	public static Vector getListLuas() throws Exception {
	    Db db = null;
//	    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Luas");
	      r.add("keterangan");
	      r.add("kod_Luas");
	      //r.add("id_Negeri");
	      
	      
	     
	     
	      sql = r.getSQLSelect("Tblrujluas","kod_Luas");
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Luas"));
	     
	       
	        if(rs.getString("keterangan") == null){
	        	h.put("nama", "");
	        }else{
	        	h.put("nama", rs.getString("keterangan"));
	        }
	        if(rs.getString("kod_Luas") == null){
	        	h.put("kod", "");
	        }else{
	        	h.put("kod", rs.getString("kod_Luas"));
	        }
	       
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static Vector getListStatusPemilik(String status) throws Exception {
	    Db db = null;
//	    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Jenispb");
	      r.add("keterangan");
	      r.add("kod_Jenis_Pb");
	      r.add("jenis_Daftar_Pb");
	     
	      
	      r.add("jenis_Daftar_Pb",status);
	      
	     
	      sql = r.getSQLSelect("Tblrujjenispb","kod_Jenis_Pb");
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Jenispb"));
	     
	       
	        if(rs.getString("keterangan") == null){
	        	h.put("keterangan", "");
	        }else{
	        	h.put("keterangan", rs.getString("keterangan"));
	        }
	        if(rs.getString("kod_Jenis_Pb") == null){
	        	h.put("kod", "");
	        }else{
	        	h.put("kod", rs.getString("kod_Jenis_Pb"));
	        }
	        if(rs.getString("jenis_Daftar_Pb") == null){
	        	h.put("status", "");
	        }else{
	        	h.put("status", rs.getString("jenis_Daftar_Pb"));
	        }
	       
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static Vector getListJenisTanah() throws Exception {
	    Db db = null;
//	    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Jenistanah");
	      r.add("keterangan");
	      r.add("kod_Jenis_Tanah");
	     
	      sql = r.getSQLSelect("Tblrujjenistanah","kod_Jenis_Tanah");
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Jenistanah"));
	     
	       
	        if(rs.getString("keterangan") == null){
	        	h.put("keterangan", "");
	        }else{
	        	h.put("keterangan", rs.getString("keterangan"));
	        }
	        if(rs.getString("kod_Jenis_Tanah") == null){
	        	h.put("kod", "");
	        }else{
	        	h.put("kod", rs.getString("kod_Jenis_Tanah"));
	        }
	       
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static Vector getListMukim() throws Exception {
	    Db db = null;
//	    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Mukim");
	      r.add("nama_Mukim");
	      r.add("kod_Mukim");
	      r.add("id_Daerah");
	      
	  
	     
	      sql = r.getSQLSelect("Tblrujmukim","kod_Mukim");
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Mukim"));
	     
	       
	        if(rs.getString("nama_Mukim") == null){
	        	h.put("nama", "");
	        }else{
	        	h.put("nama", rs.getString("nama_Mukim"));
	        }
	        if(rs.getString("kod_Mukim") == null){
	        	h.put("kod", "");
	        }else{
	        	h.put("kod", rs.getString("kod_Mukim"));
	        }
	       
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static Vector getListJenisHakMilik(String statushakmilik) throws Exception {
	    Db db = null;
//	    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Jenishakmilik");
	      r.add("keterangan");
	      r.add("kod_Jenis_Hakmilik");
	      r.add("simpanan");
	      r.add("status_Hakmilik");
	      
	      //r.add("status_Hakmilik",statushakmilik);
	     
	      sql = r.getSQLSelect("Tblrujjenishakmilik","kod_Jenis_Hakmilik");
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Jenishakmilik"));
	     
	       
	        if(rs.getString("keterangan") == null){
	        	h.put("keterangan", "");
	        }else{
	        	h.put("keterangan", rs.getString("keterangan"));
	        }
	        if(rs.getString("kod_Jenis_Hakmilik") == null){
	        	h.put("kod", "");
	        }else{
	        	h.put("kod", rs.getString("kod_Jenis_Hakmilik"));
	        }
	        if(rs.getString("simpanan") == null){
	        	h.put("simpanan", "");
	        }else{
	        	h.put("simpanan", rs.getString("simpanan"));
	        }
	       
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static Vector getListKategori() throws Exception {
	    Db db = null;
//	    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Kategori");
	      r.add("keterangan");
	      r.add("kod_Kategori");
	     
	      
	     
	      sql = r.getSQLSelect("Tblrujkategori","kod_Kategori");
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Kategori"));
	     
	       
	        if(rs.getString("keterangan") == null){
	        	h.put("keterangan", "");
	        }else{
	        	h.put("keterangan", rs.getString("keterangan"));
	        }
	        if(rs.getString("kod_Kategori") == null){
	        	h.put("kod", "");
	        }else{
	        	h.put("kod", rs.getString("kod_Kategori"));
	        }
	        
	       
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	private static Vector listGetUserId = new Vector();
	
	public static void setGetUserId(String userid) throws Exception {
	    Db db = null;
	    int idUser = Integer.parseInt(userid);
	    String sql = "Select d.id_daerah,d.kod_daerah,d.id_negeri,r.id_pejabat,n.kod_negeri From users_internal u, tblrujdaerah d, tblrujpejabaturusan r, tblrujnegeri n " +
	    		     "Where u.id_negeri = d.id_negeri and d.id_daerah = r.id_daerah and d.id_negeri = r.id_negeri and r.id_negeri = n.id_negeri and u.user_id = "+ idUser +"";
	    //String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Kategori");
	      r.add("keterangan");
	      r.add("kod_Kategori");
	     
	      //sql = r.getSQLSelect("Tblrujkategori","kod_Kategori");
	      ResultSet rs = stmt.executeQuery(sql);
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("idDaerah", rs.getString("id_daerah"));
	        h.put("kodDaerah", rs.getString("kod_daerah")); 
	        h.put("idNegeri", rs.getString("id_negeri")); 
	        h.put("idpejabat", rs.getString("id_pejabat"));
	        h.put("kodnegeri", rs.getString("kod_negeri"));
	        listGetUserId.addElement(h);
	      }
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static Vector getUserIds(){
		  return listGetUserId;
	  }
	
	public static Vector getDaerahByNegeriUser(String userid)throws Exception {
	    Db db = null;
	    String sql = "Select d.id_daerah,d.kod_daerah,d.nama_daerah,d.id_negeri From users_internal u, tblrujdaerah d, tblrujpejabaturusan r  Where u.id_negeri = d.id_negeri and d.id_daerah = r.id_daerah and d.id_negeri = r.id_negeri and u.user_id = "+ userid +" group by d.id_daerah,d.kod_daerah,d.nama_daerah,d.id_negeri order by d.id_daerah";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("id_jenisnopb");
	      r.add("keterangan");
	
	      //sql = r.getSQLSelect(sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector listDaerahByUser = new Vector();
	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("iddaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
	    	  h.put("namadaerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
		    	  listDaerahByUser.addElement(h);
	      }
	      return listDaerahByUser;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static Vector getDaerahUser(String userid)throws Exception {
	    Db db = null;
	    String sql = "Select d.id_daerah,d.kod_daerah,d.nama_daerah,d.id_negeri,r.id_pejabat From users_internal u, tblrujdaerah d, tblrujpejabaturusan r  Where u.id_negeri = d.id_negeri and d.id_daerah = r.id_daerah and d.id_negeri = r.id_negeri and u.user_id = "+ userid +" group by d.id_daerah,d.kod_daerah,d.nama_daerah,d.id_negeri,r.id_pejabat order by d.id_daerah";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("id_jenisnopb");
	      r.add("keterangan");
	
	      //sql = r.getSQLSelect(sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector listDaerahUser = new Vector();
	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("iddaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
	    	  h.put("namadaerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
	    	  h.put("idpejabat", rs.getString("id_pejabat")==null?"":rs.getString("id_pejabat"));
	    	  listDaerahUser.addElement(h);
	      }
	      return listDaerahUser;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	//kira jumlah nilai mohon hta
	private static Vector listSumDataHta = new Vector();
		
		public static void setSumDataHta(int id2) throws Exception{
			Db db = null;
			listSumDataHta.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
							
				r.add("hta.id_Hta");
				r.add("hta.id_Negeri");
				r.add("hta.id_Daerah");
				r.add("hta.id_Mukim");
				r.add("hta.no_Hakmilik");
				r.add("hta.no_Pt");
				r.add("hta.ba_Simati");
				r.add("hta.bb_Simati");
				r.add("hta.nilai_hta_tarikhmohon");
				r.add("hta.nilai_hta_tarikhmati");
				r.add("n.nama_Negeri");
				r.add("d.nama_Daerah");
				r.add("m.nama_Mukim");
				r.add("sm.id_Simati");
				r.add("sm.id_Permohonan");
				r.add("p.id_Permohonan");
				
				r.add("hta.id_Negeri",r.unquote("n.id_Negeri(+)"));
				r.add("hta.id_Daerah",r.unquote("d.id_Daerah(+)"));
				r.add("hta.id_Mukim",r.unquote("m.id_Mukim(+)"));
				r.add("hta.id_Simati",r.unquote("sm.id_Simati(+)"));
				r.add("sm.id_Permohonan",r.unquote("p.id_Permohonan(+)"));
				r.add("hta.id_Simati",id2);
					
				sql = r.getSQLSelect("Tblppkpermohonan p, Tblppkhta hta, Tblrujnegeri n, Tblrujdaerah d, Tblrujmukim m, Tblppksimati sm");
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("idhta", rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
					h.put("nama_Negeri", rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
					h.put("nama_Daerah", rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
					h.put("nama_Mukim", rs.getString("nama_Mukim")==null?"":rs.getString("nama_Mukim"));
					h.put("no_Pt", rs.getString("no_Pt")==null?"":rs.getString("no_Pt"));
					h.put("no_Perjanjian", rs.getString("no_Hakmilik")==null?"":rs.getString("no_Hakmilik"));
					h.put("nilai_tarikhmohon", rs.getString("nilai_hta_tarikhmohon")==null?"":rs.getDouble("nilai_hta_tarikhmohon"));
					h.put("nilai_tarikhmati", rs.getString("nilai_hta_tarikhmati")==null?"":rs.getDouble("nilai_hta_tarikhmati"));
					listSumDataHta.addElement(h);
					bil++;	
				}
				//return list;
			}finally {
				if(db != null) db.close();
			}
			}
		
		public static Vector getSumDataHta(){
			  return listSumDataHta;
		  }
	
		private static Vector listOverallSumHta = new Vector();
		
		public static void setOverallSumHta(int id) throws Exception{
			Db db = null;
			listOverallSumHta.clear();
			String sql = "Select sum(nilai_hta_tarikhmohon) from Tblppkhta where id_simati = "+ id +"";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("nilaibesarmohonhta", rs.getString(1)==null?"":Double.parseDouble(rs.getString(1)));
					listOverallSumHta.addElement(h);
					bil++;	
				}
				//return list;
			}finally {
				if(db != null) db.close();
			}
			}
		
		public static Vector getOverallSumHta(){
			  return listOverallSumHta;
		  }
		
		public static Vector getListDaerahbyNegerihta(int idnegeri,String idpermohonan) throws Exception {
		    Db db = null;
//		    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		    String sql = "";
		    try {
		    	db = new Db();
		    	Statement stmt = db.getStatement();
		    	SQLRenderer r = new SQLRenderer();
			    r.add("id_Daerah");
			    r.add("nama_Daerah");
			    r.add("kod_Daerah");
			    r.add("id_Negeri");		      		      
			    r.add("id_Negeri",idnegeri);
		     
			    sql += "select distinct a4.id_Daerah, a4.kod_Daerah, a4.nama_Daerah, a3.id_negeri " +
		      	"from tblrujpejabaturusan a1,tblrujnegeri a3, tblrujdaerah a4,TBLRUJPEJABATJKPTG a5,tblppkpermohonan a6," +
		      	"tblppkpermohonansimati a7,tblppkhta a8 where a1.ID_PEJABATJKPTG = a5.ID_PEJABATJKPTG and " +
		      	"a1.id_negeriurus = a3.id_negeri and a1.id_daerahurus = a4.id_daerah and a6.ID_daerahMHN NOT IN (a1.id_daerahurus) " +
		      	"and a6.id_permohonan = a7.id_permohonan and a7.id_simati = a8.id_simati and " +
		      	"a8.id_negeri = a1.id_negeriurus and a6.id_permohonan = '"+ idpermohonan +"' and a3.id_negeri = "+ idnegeri +" " +
		      	"and a1.ID_JENISPEJABAT = 22 and a1.id_seksyen = 2 " +
		      	"AND A1.ID_PEJABATJKPTG NOT IN (SELECT ID_PEJABATJKPTG FROM tblrujpejabaturusan WHERE ID_DAERAHURUS = A6.ID_DAERAHMHN " +
		      	"AND A6.ID_PERMOHONAN = '"+ idpermohonan +"') ";
			    sql += "UNION distinct select a4.id_Daerah, a4.kod_Daerah, a4.nama_Daerah, a3.id_negeri " +
		      	"from tblrujpejabaturusan a1,tblrujnegeri a3, tblrujdaerah a4,TBLRUJPEJABATJKPTG a5,tblppkpermohonan a6," +
		      	"tblppkpermohonansimati a7,tblppkha a8 where a1.ID_PEJABATJKPTG = a5.ID_PEJABATJKPTG and " +
		      	"a1.id_negeriurus = a3.id_negeri and a1.id_daerahurus = a4.id_daerah and a6.ID_daerahMHN NOT IN (a1.id_daerahurus) " +
		      	"and a6.id_permohonan = a7.id_permohonan and a7.id_simati = a8.id_simati and " +
		      	"a8.id_negeri = a1.id_negeriurus and a6.id_permohonan = '"+ idpermohonan +"' and a3.id_negeri = "+ idnegeri +" " +
		      	"and a1.ID_JENISPEJABAT = 22 and a1.id_seksyen = 2 " +
		      	"AND A1.ID_PEJABATJKPTG NOT IN (SELECT ID_PEJABATJKPTG FROM tblrujpejabaturusan WHERE ID_DAERAHURUS = A6.ID_DAERAHMHN " +
		      	"AND A6.ID_PERMOHONAN = '"+ idpermohonan +"') ";
		      	sql += "group by a4.id_Daerah, a4.kod_Daerah, a4.nama_Daerah, a3.id_negeri";
		      
		      	myLogger.info("sql getDaerah"+sql);
			    ResultSet rs = stmt.executeQuery(sql);
			    Vector v = new Vector();
			    while (rs.next()) {
			    	Hashtable h = new Hashtable();
			    	h.put("id", rs.getInt("id_Daerah"));	       
			    	if(rs.getString("nama_Daerah") == null){
			    		h.put("nama", "");
			    	}else{
			    		  h.put("nama", rs.getString("nama_Daerah"));
			    	}
			    	if(rs.getString("kod_Daerah") == null){
			    		h.put("kod", "");
			    	}else{
			    		h.put("kod", rs.getString("kod_Daerah"));
			    	}
			       
			    	v.addElement(h);
			    
			    }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
}	 	
