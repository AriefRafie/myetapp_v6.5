package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class Seksyen4MMK {
	static Logger myLogger = Logger.getLogger(Seksyen4MMK.class);
	
	
	
	
	
	
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static Vector list = new Vector();
	private static Vector listSemak = new Vector();
	private  Vector listKertas = new Vector();
	private  Vector listSek4MMK = new Vector();
	private  Vector tarikhLuput = new Vector();
	private static  Vector listPermohonan = new Vector();
	
	public static Vector getList(){
		return list;
	}//close getList
	
	public Vector getSemak(){
		return listSemak;
	}//close getSemak 
	
	public Vector getListKertas(){
		return listKertas;
	}//close getListKertas
	
	public Vector getSek4MMK(){
		return listSek4MMK;
	}
	
	
	
	public Vector getTarikhLuput(){
		return tarikhLuput;
	}//close getTarikhLuput
	
	//--carian--//
	@SuppressWarnings("unchecked")
	public static void setList(String carianNoFail,String carianRujukan,String carianStatus, String userIdNegeri)throws Exception {
	   
		Db db = null;
	    list.clear();
	    String sql = "";
	    
	    String noFail = carianNoFail.trim();
	    String noRujukan = carianRujukan.trim();
	    
	    try {
	    	
	    	db = new Db();
	      	Statement stmt = db.getStatement();
	      
	      	sql = "SELECT p.no_rujukan_upt, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, p.no_rujukan_upt, "; 
	    	sql += " su.nama_suburusan, k.nama_kementerian, s.id_status, s.keterangan, mk.flag_semakan_pengarah, p.no_rujukan_ptg, p.no_rujukan_ptd ";
	    	sql += " FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Tblpptmmk mk ";
	    	sql += " WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan ";
	    	sql += " AND mk.id_permohonan(+) = p.id_permohonan ";
			sql += " AND f.id_kementerian = k.id_kementerian ";
			sql += " AND p.id_status = s.id_status ";
			sql += " AND p.id_status = ANY(148,147,26,31) "; 
			sql += " AND su.id_suburusan = '51' ";
			if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
    			if(userIdNegeri.equals("14")){
    				sql += "AND f.id_negeri in (14,15,16) ";
    			}else{
    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
    			}		
    		}
			
			//default flag
			boolean setLimit = true;  
			
	      if(carianNoFail != null){
	    	  setLimit = false;
	    	 // sql +="AND f.no_fail LIKE '%"+noFail.toUpperCase()+"%' ";
	    	  sql += " AND (UPPER(f.no_fail) LIKE '%" + noFail.toUpperCase() + "%' " +
	    	  		" OR UPPER(p.no_rujukan_ptg) LIKE '%" + noFail.toUpperCase() + "%' " +
	    	  		" OR UPPER(p.no_rujukan_upt) LIKE '%" + noFail.toUpperCase() + "%' " +
	    	  		" OR UPPER(p.no_rujukan_ptd) LIKE '%" + noFail.toUpperCase() + "%')";
	      }

	      if(carianStatus != null){
	    	  setLimit = false;
	    	  sql +="AND p.id_status LIKE '%"+carianStatus+"%' ";
	      }
	     
	      if(setLimit){	
				sql += " AND ROWNUM <= 10 ";				
			}
	      
	      sql +="ORDER by p.tarikh_permohonan desc, p.no_permohonan desc ";
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	 
	    	  h = new Hashtable();
	    	  h.put("bil", bil);	
	    	  h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));
	    	  h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
  			  h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
	    	  h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
	    	  h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
	    	  h.put("no_permohonan", rs.getString("no_permohonan")==null?"":rs.getString("no_permohonan"));
	    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));
	    	  h.put("status", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"-":rs.getString("nama_kementerian"));
	    	  
	    	  if(rs.getString("flag_semakan_pengarah") != null && rs.getString("flag_semakan_pengarah") != ""){
	    		  
	    		  if(rs.getString("flag_semakan_pengarah").equals("1")){
	    			  h.put("flag_semakan_pengarah","TUNGGU PENGESAHAN");
	    		  }else if(rs.getString("flag_semakan_pengarah").equals("2")){
	    			  h.put("flag_semakan_pengarah","TELAH DISAHKAN");
	    		  }else{
	    			  h.put("flag_semakan_pengarah","");
	    		  }
	    		 
	    	  }else{
	    		  h.put("flag_semakan_pengarah","");
	    	  }
	    	  
	    	  if(rs.getString("no_fail") == null){
		    		h.put("no_fail","Belum Diluluskan");
		    	}else{
		    		h.put("no_fail",rs.getString("no_fail"));
		    	}
	    	  list.addElement(h);
	    	  bil++;
	      }
	      
	    } finally {
	      if (db != null) db.close();
	    }
	    
	  }//close carian
	
	//--List Permohonan--//
	 @SuppressWarnings("unchecked")
	public static Vector getListPemohon(String userIdNegeri)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		    	
		    	db = new Db();
		    	Statement stmt = db.getStatement();
		      
		      	sql = "SELECT p.no_rujukan_upt, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, "; 
		    	sql += " su.nama_suburusan, k.nama_kementerian, s.id_status, s.keterangan, mk.flag_semakan_pengarah, p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		    	sql += " FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Tblpptmmk mk ";
		    	sql += " WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan ";
		    	sql += " AND mk.id_permohonan(+) = p.id_permohonan ";
				sql += " AND f.id_kementerian = k.id_kementerian ";
				sql += " AND p.id_status = s.id_status ";
				sql += " AND p.id_status = ANY(148,147,26,31) "; 
				sql += " AND su.id_suburusan = '51' ";
				if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			if(userIdNegeri.equals("14")){
	    				sql += "AND f.id_negeri in (14,15,16) ";
	    			}else{
	    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    			}		
	    		}
				sql += " ORDER by p.tarikh_permohonan desc, p.no_permohonan desc ";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;
		      int bil = 1;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));
		    	  h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
	    		  h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
		    	  h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
		    	  h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
		    	  h.put("no_permohonan", rs.getString("no_permohonan")==null?"":rs.getString("no_permohonan"));
		    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));
		    	  h.put("status", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
		    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"-":rs.getString("nama_kementerian"));
		    	  
		    	  if(rs.getString("flag_semakan_pengarah") != null && rs.getString("flag_semakan_pengarah") != ""){
		    		  
		    		  if(rs.getString("flag_semakan_pengarah").equals("1")){
		    			  h.put("flag_semakan_pengarah","TUNGGU PENGESAHAN");
		    		  }else if(rs.getString("flag_semakan_pengarah").equals("2")){
		    			  h.put("flag_semakan_pengarah","TELAH DISAHKAN");
		    		  }else{
		    			  h.put("flag_semakan_pengarah","");
		    		  }
		    		 
		    	  }else{
		    		  h.put("flag_semakan_pengarah","");
		    	  }
		    	  
		    	  if(rs.getString("no_fail") == null){
			    		h.put("no_fail","Belum Diluluskan");
			    	}else{
			    		h.put("no_fail",rs.getString("no_fail"));
			    	}
		    	  list.addElement(h);
		    	  bil++;	    	  
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  
	  }//close list permohonan
	
	
	//--Data Semak--//
	 public static void setSemak(int id)throws Exception {
		    Db db = null;
		    listSemak.clear();
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		 
		      r.add("p.id_permohonan");
		      r.add("p.no_permohonan");
		      r.add("p.tarikh_permohonan");
		      r.add("su.nama_suburusan");
		      r.add("k.nama_kementerian");
		      r.add("p.id_status");
		      r.add("s.keterangan");
		      
		      r.add("p.id_fail",r.unquote("f.id_fail"));
		      r.add("f.id_suburusan",r.unquote("su.id_suburusan"));
		      r.add("f.id_kementerian",r.unquote("k.id_kementerian"));
		      r.add("p.id_status",r.unquote("s.id_status"));

		      sql = r.getSQLSelect("Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k");
		      ResultSet rs = stmt.executeQuery(sql);
		      //Vector list = new Vector();
		      Hashtable h;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("id_permohonan", rs.getString("id_permohonan"));
		    	  h.put("id_status", rs.getString("id_status"));
		    	  h.put("no_permohonan", rs.getString("no_permohonan"));
		    	  h.put("tarikh_permohonan", rs.getString("tarikh_permohonan"));
		    	  h.put("nama_suburusan", rs.getString("nama_suburusan"));
		    	  h.put("nama_kementerian", rs.getString("nama_kementerian"));
		    	  h.put("keterangan", rs.getString("keterangan"));
		    	  listSemak.addElement(h);
		      }
		     
		    } finally {
		      if (db != null) db.close();
		    }
	}//close Semak
	
	 //--Data list Kertas--//
	 @SuppressWarnings("unchecked")
	public void setDataListKertas(String id_permohonan) throws Exception{
			Db db = null;
			listKertas.clear();
			String sql = "";
			
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT DISTINCT p.id_permohonan, p.id_status, m.id_mmk, f.id_fail, f.no_fail, sta.keterangan, m.no_rujmmk,"; 
				sql += " m.tarikh_mmk, m.flag_semak, ";
				sql += " mk.status_keputusan, mk.id_mmkkeputusan, mk.tarikh_keputusan, mk.tarikh_luput "; 
				sql += " FROM Tblpptpermohonan p, Tblrujstatus sta, Tblpfdfail f,Tblpptmmk m , tblpptmmkkeputusan mk "; 
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) "; 
				sql += " AND mk.id_mmk = m.id_mmk  AND p.id_fail = f.id_fail "; 
				sql += " AND p.id_status = sta.id_status "; 
				sql += " AND p.id_Permohonan = '"+id_permohonan+"'" ;  
				sql += " ORDER BY tarikh_mmk desc";
				
				//System.out.println("sql = "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("tarikh_keputusan", rs.getDate("tarikh_keputusan")==null?"":Format.format(rs.getDate("tarikh_keputusan")));
					h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
					h.put("id_mmk", rs.getString("id_mmk")==null?"":rs.getString("id_mmk"));
					h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
					h.put("id_fail", rs.getString("id_fail"));
					h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
					h.put("id_mmkkeputusan", rs.getString("id_mmkkeputusan"));
					h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
					h.put("no_rujmmk", rs.getString("no_rujmmk")==null?"Tiada No.Rujukan":rs.getString("no_rujmmk"));
					h.put("tarikh_mmk", rs.getDate("tarikh_mmk")==null?"":Format.format(rs.getDate("tarikh_mmk")));
					h.put("tarikh_luput", rs.getDate("tarikh_luput")==null?"":Format.format(rs.getDate("tarikh_luput")));
				
					if(rs.getString("flag_semak")!=null){
						if(rs.getInt("flag_semak") == 1){
							h.put("flag_semak", "Semakan Kertas MMK");
						}
						else if(rs.getInt("flag_semak") == 2){
							h.put("flag_semak", "Keputusan Dikeluarkan");
						}
						else{
							h.put("flag_semak", "Penyediaan Kertas MMK");
						}
					}else{
						h.put("flag_semak", "Penyediaan Kertas MMK");
					}
						
						if(rs.getString("status_keputusan")!=null){
							if(rs.getInt("status_keputusan") == 1){
								h.put("status_keputusan", "Diluluskan");
							
							}else if(rs.getInt("status_keputusan") == 2){
								h.put("status_keputusan", "Ditolak");
							
							}else if(rs.getInt("status_keputusan") == 3){
								h.put("status_keputusan", "Ditangguh");
							
							}else{
								h.put("status_keputusan", "Dalam proses");
							
							} 
						}else{
							h.put("status_keputusan", "Dalam proses");
						}
					listKertas.addElement(h);
					bil++;	
				}
				//return list;
			}finally {
				if(db != null) db.close();
			}
	 }//close setDataListKertas
	 
	 
	 //--Data Seksyen 4 MMK--//
	 public void setDataSek4MMK(String id) throws Exception {
			Db db = null;
			listSek4MMK.clear();
			String sql = "";
			
			
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT p.tarikh_boranga, p.id_fail,p.id_permohonan, p.no_permohonan, p.id_agensi, ag.nama_agensi, f.no_fail, n.nama_negeri, np.nama_negeri as projek_negeri, p.tarikh_terima, p.tujuan, k.nama_kementerian, "; 
				sql += " k.kod_kementerian, s.keterangan, d.nama_daerah, p.tarikh_kehendaki, p.no_rujukan_ptd, p.no_rujukan_ptg, p.no_rujukan_upt, p.no_rujukan_surat "; 
				sql += " FROM Tblpfdfail f, Tblrujdaerah d, Tblrujnegeri n, Tblrujkementerian k, Tblrujstatus s, Tblpptpermohonan p, Tblrujagensi ag, Tblrujnegeri np ";
				sql += " WHERE f.id_fail = p.id_fail "; 
				sql += " AND f.id_negeri = np.id_negeri ";
				sql += " AND k.id_kementerian = f.id_kementerian "; 
				sql += " AND n.id_negeri = p.id_negeri "; 
				sql += " AND s.id_status = p.id_status ";
				sql += " AND d.id_daerah = p.id_daerah ";
				sql += " AND p.id_agensi = ag.id_agensi(+) ";
				sql += " AND p.id_Permohonan = '"+id+"'" ;
				
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
			
				while(rs.next()) {
					h = new Hashtable();
					h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
					h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("id_agensi", rs.getString("id_agensi")==null?"":rs.getString("id_agensi"));
					h.put("tujuan", rs.getString("tujuan")==null?"":rs.getString("tujuan"));
					h.put("tarikh_kehendaki", rs.getDate("tarikh_kehendaki")==null?"-":Format.format(rs.getDate("tarikh_kehendaki")));
					h.put("status", rs.getString("keterangan")==null?"-":rs.getString("keterangan"));
					h.put("daerah", rs.getString("nama_daerah")==null?"-":rs.getString("nama_daerah"));
					h.put("no_fail", rs.getString("no_fail")==null?"-":rs.getString("no_fail"));
					h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"-":rs.getString("nama_kementerian"));
					h.put("kod_kementerian", rs.getString("kod_kementerian")==null?"-":rs.getString("kod_kementerian"));
					h.put("namaNegeri", rs.getString("nama_negeri")==null?"-":rs.getString("nama_negeri"));
					h.put("projek_negeri", rs.getString("projek_negeri")==null?"-":rs.getString("projek_negeri"));
					h.put("tarikhTerima", rs.getDate("tarikh_terima")==null?"-":Format.format(rs.getDate("tarikh_terima")));
					h.put("noPermohonan", rs.getString("no_permohonan")==null?"-":rs.getString("no_permohonan"));
					h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"-":rs.getString("no_rujukan_ptd"));
					h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"-":rs.getString("no_rujukan_ptg"));
					h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"-":rs.getString("no_rujukan_upt"));
					h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")==null?"-":rs.getString("no_rujukan_surat"));
					h.put("tarikh_boranga", rs.getDate("tarikh_boranga")==null?"":Format.format(rs.getDate("tarikh_boranga")));
					listSek4MMK.addElement(h);
				}
			}
			finally {
				if(db != null) db.close();
			}
			
		}//close datasek4mmk
	 
	 //--Add kertas baru--//
	 public static void add(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    String sql2 = "";
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
    		
	    		String id_mmk = (String)data.get("idNewMMK");
	    		String id_permohonan = (String)data.get("id_permohonan");	    		
	    		String id_user = (String)data.get("id_user");
	      
//	    		String txttujuan = (String)data.get("txttujuan");
//	    		String txtlatarbelakang = (String)data.get("txtlatarbelakang");
//	    		String txtlaporan = (String)data.get("txtlaporan");
//	    		String txtsyor = (String)data.get("txtsyor");
//	    		String txtasaspertimbangan = (String)data.get("txtasaspertimbangan");
//	    		String txtimplikasi = (String)data.get("txtimplikasi");
//	    		String txtkesimpulan = (String)data.get("txtkesimpulan");
//	    		String txtnilaian = (String)data.get("txtnilaian");
//	    		String txtulasan = (String)data.get("txtulasan");
//	    		String txtkeputusan = (String)data.get("txtkeputusan");	
//	    		String txtkedudukan = (String)data.get("txtkedudukan");
//	    		String txtperuntukan = (String)data.get("txtperuntukan");
//	    		String txtperakuan = (String)data.get("txtperakuan");
//	    		
//	    		String txtpandangan = (String)data.get("txtpandangan");
//	    		String txtperihalpohon = (String)data.get("txtperihalpohon");	
//	    		String txtanggaran = (String)data.get("txtanggaran");
//	    		String txtulasanjt = (String)data.get("txtulasanjt");
//	    		String txtjawatankuasa = (String)data.get("txtjawatankuasa");
//	    		String txthallain = (String)data.get("txthallain");
	    		
	    		String txtJenisPenggunaan = (String)data.get("txtJenisPenggunaan");
	    		String txtLokasi = (String)data.get("txtLokasi");
	    		String txtKedudukan = (String)data.get("txtKedudukan");
	    		String txtKeadaan = (String)data.get("txtKeadaan");
	    		
	    		String txtTajuk = (String) data.get("txtTajuk");
	    		String txtSidang = (String) data.get("txtSidang");
	    		String txtTempatSidang = (String) data.get("txtTempatSidang");
	    		String txtTarikhSidang = (String) data.get("txtTarikhSidang");
	    		String txtMasaSidang = (String) data.get("txtMasaSidang");
	    		String jeniswaktu = (String) data.get("jeniswaktu");
	    		
	    		//insert into tblpptmmk	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_mmk", id_mmk);
	    		r.add("id_permohonan", id_permohonan);
	    		r.add("flag_semak", "1");
	    		r.add("jenis_penggunaan_tnh", txtJenisPenggunaan);	
	    		r.add("lokasi_tanah", txtLokasi);	
	    		r.add("kedudukan_tanah", txtKedudukan);	
	    		r.add("keadaan_tanah", txtKeadaan);	
	    		
	    		r.add("TAJUK", txtTajuk);
				r.add("KETERANGAN_SIDANG", txtSidang);
				r.add("TEMPAT_SIDANG", txtTempatSidang);
				r.add("WAKTU_SIDANG", txtMasaSidang);
				r.add("JENIS_WAKTU_SIDANG", jeniswaktu);
				if (txtTarikhSidang != "") {
					r.add("TARIKH_SIDANG", r.unquote("to_date('"
							+ txtTarikhSidang + "','dd/MM/yyyy')"));
				} else {
					r.add("TARIKH_SIDANG", "");
				}
	    		
	    		
//	    		r.add("tujuan", txttujuan); 	
//	    		r.add("latarbelakang", txtlatarbelakang);
//	    		r.add("perihal_tanah", txtlaporan);	
//	    		r.add("syor", txtsyor);	
//	    		r.add("hal_lain", txthallain);	
//	    		r.add("pandangan", txtpandangan);	
//	    		r.add("asas_pertimbangan", txtasaspertimbangan);
//	    		r.add("perihal_pohon", txtperihalpohon);
//	    		r.add("anggaran_kos", txtanggaran);	
//	    		r.add("ulasan_jabteknikal", txtulasanjt);	
//	    		r.add("jawatankuasa_tanah", txtjawatankuasa);
//	    		r.add("implikasi", txtimplikasi);
//	    		r.add("kedudukan_laporan_tnh", txtkedudukan);		
//	    		r.add("pengesahan_peruntukan", txtperuntukan);
//	    		r.add("perakuan_pentadbir_tnh", txtperakuan);
//	    		r.add("kesimpulan", txtkesimpulan);		
//	    		r.add("nilai_atas_tanah", txtnilaian);
//	    		r.add("ulasan_pengarah", txtulasan);
//	    		r.add("keputusan", txtkeputusan);			
	    		r.add("id_masuk",id_user);
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		sql = r.getSQLInsert("tblpptmmk");
	    		stmt.executeUpdate(sql);
	      
	    		//insert into tblpptmmkkeputusan
	    		long idNewMMKKeputusan = DB.getNextID("TBLPPTMMKKEPUTUSAN_SEQ");  
	      
	    		SQLRenderer rK = new SQLRenderer();
	    		rK.add("id_mmkkeputusan", idNewMMKKeputusan);
	    		rK.add("id_mmk", id_mmk);
	    		rK.add("id_masuk",id_user);
	    		rK.add("tarikh_masuk",rK.unquote("sysdate"));
	    		sql2 = rK.getSQLInsert("tblpptmmkkeputusan");
	    		stmt.executeUpdate(sql2);
	      
	    		////change status to maklumat mmk      
	    		SQLRenderer rST = new SQLRenderer();
	    		rST.update("id_permohonan", id_permohonan);
	    		rST.add("id_status", "26");
	    		rST.add("id_kemaskini",id_user);
	    		rST.add("tarikh_kemaskini",rST.unquote("sysdate"));
	    		sql2 = rST.getSQLUpdate("tblpptpermohonan");
	    		stmt.executeUpdate(sql2);
	      
	    		
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally

	  }//close add

	 
	 //--Update Semakan--//
	 public static void update_item_semakan(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		    try
		    {
		      String role = (String)data.get("role");
		    	  
		      String id_mmk = (String)data.get("id_mmk");
		      String tarikh_semak = (String)data.get("tarikh_semak");
		      String id_pegawai = (String)data.get("id_pegawai");
			  String ulasan = (String)data.get("ulasan");
		      String status_semakan = (String)data.get("status_semakan");
		      String tarikh_hantar = (String)data.get("tarikh_hantar");
		      
			  //int USER_ID = Integer.parseInt(id_pegawai);
			  
			  String TD = "to_date('" + tarikh_semak + "','dd/MM/yyyy')";
			  String TH = "to_date('" + tarikh_hantar + "','dd/MM/yyyy')";
			  
			  String flag_semakan = "2";
			  
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_mmk", id_mmk);
			  r.add("tarikh_semak", r.unquote(TD));
			  r.add("tarikh_hantar", r.unquote(TH));
			  r.add("ulasan", ulasan);
			  
			  if(tarikh_hantar!=""){
				  r.add("flag_semak", flag_semakan);
			  }
			
			  if(role.equals("(PPT)KetuaPenolongPengarahUnit") || role.equals("(PPT)PengarahUnit") ||role.equals("adminppt") ){
				  r.add("status_semakan", status_semakan);
				  r.add("user_id", id_pegawai);
				  r.add("flag_semakan_pengarah", "2");
			  }
			 
			  //r.add("user_id", id_pegawai);
			  r.add("id_kemaskini",id_pegawai);
			  r.add("tarikh_kemaskini",r.unquote("sysdate"));
		      sql = r.getSQLUpdate("tblpptmmk");
		      stmt.executeUpdate(sql);
		    }
		    finally {
		      if (db != null) db.close();
		    }
	}//close add semakan

	 //--Add keputusan--//
	 public static void add_keputusan(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    try
	    {
	    	//long id_warta = DB.getNextID("TBLPPTWARTA_SEQ");
	        //String id_permohonan = (String)data.get("id_permohonan");	
	    	
	      //String tarikhLuput = (String)data.get("tarikhLuput");	
	    	
	      String id_mmk = (String)data.get("id_mmk");	
	      String txtRujMMK = (String)data.get("txtRujMMK");	
	      String tarikh_MMK = (String)data.get("txdTarikhMMK");
	      
	      String id_user = (String)data.get("id_user");
	      
	      String id_mmk_keputusan = (String)data.get("id_mmk_keputusan");
	      //String tarikh_hantar = (String)data.get("tarikh_hantar");
	      //String tarikh_keputusan = (String)data.get("tarikh_keputusan");
	      String tarikh_terima = (String)data.get("tarikh_terima");
	      String ulasan = (String)data.get("ulasan");
	      String keputusan = (String)data.get("keputusan");
	      
	     
	      //String TA = "to_date('" + tarikh_hantar + "','dd/MM/yyyy')";
	      //String TB = "to_date('" + tarikh_keputusan + "','dd/MM/yyyy')";
	      String TC = "to_date('" + tarikh_terima + "','dd/MM/yyyy')";
	      
	      String TD = "to_date('" + tarikh_MMK + "','dd/MM/yyyy')";
	      
	      //String TE = "to_date('" + tarikhLuput + "','dd/MM/yyyy')";
	      
	      //int idMMKKeputusan = Integer.parseInt(id_mmk_keputusan);
	      //int int_keputusan = Integer.parseInt(keputusan);
	      
	     // int idPermohonan = Integer.parseInt(id_permohonan);
	     // int status_notisawam = 52; 
	      
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.update("id_mmkkeputusan", id_mmk_keputusan);
	      r.add("status_keputusan", keputusan); 	
	      r.add("ulasan", ulasan);
	      //r.add("tarikh_hantar", r.unquote(TA));
	      //r.add("tarikh_keputusan", r.unquote(TB));
	      r.add("tarikh_terima", r.unquote(TC));
	      //r.add("tarikh_luput",r.unquote(TE));
	      r.add("id_kemaskini",id_user);
		  r.add("tarikh_kemaskini",r.unquote("sysdate"));
	      sql = r.getSQLUpdate("tblpptmmkkeputusan");
	      stmt.executeUpdate(sql);
	      
	/*      //--Update status--//
	      Statement stmtP = db.getStatement();
	      SQLRenderer rP = new SQLRenderer();
	      rP.update("id_permohonan", idPermohonan );
	      rP.add("id_status", status_notisawam); 	
	
	      
	      sql = rP.getSQLUpdate("tblpptpermohonan");
	      stmtP.executeUpdate(sql);
	 */  
	      
	      //--Update no.rujukan mmk--//
	      Statement stmtMMK = db.getStatement();
	      SQLRenderer rMMK = new SQLRenderer();
	      rMMK.update("id_mmk", id_mmk);
	      rMMK.add("no_rujmmk", txtRujMMK); 	
	      rMMK.add("tarikh_mmk", r.unquote(TD));
	      rMMK.add("id_kemaskini",id_user);
		  rMMK.add("tarikh_kemaskini",rMMK.unquote("sysdate"));
	      sql = rMMK.getSQLUpdate("tblpptmmk");
	      stmtMMK.executeUpdate(sql);
	      
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close add_keputusan
	 
	 //--Delete--//
	 public static void delete(String idmmk) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		SQLRenderer r = new SQLRenderer();
		    		r.add("id_mmk", idmmk);
		    		sql = r.getSQLDelete("tblpptmmk");
		    		stmt.executeUpdate(sql);
		    		
		    		r.clear();
		    		
		    		r.add("id_mmkkeputusan", idmmk);
		    		sql = r.getSQLDelete("tblpptmmkkeputusan");
		    		stmt.executeUpdate(sql);
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }//close delete
	 
	 //--Update Penyediaan--//
	 public static void update_penyediaan(Hashtable data) throws Exception {
		   
		 Db db = null;
		 String sql = "";
		    
		 try {
		    	
				db = new Db();
				Statement stmt = db.getStatement();
			  
		      	String id_mmk = (String)data.get("id_mmk");
		      	String id_user = (String)data.get("id_user");
	
//			    String txttujuan = (String)data.get("txttujuan");
//			    String txtlatarbelakang = (String)data.get("txtlatarbelakang");
//			    String txtlaporan = (String)data.get("txtlaporan");
//			    String txtsyor = (String)data.get("txtsyor");    
//			    String txtasaspertimbangan = (String)data.get("txtasaspertimbangan");
//			    String txtimplikasi = (String)data.get("txtimplikasi");
//			    String txtkesimpulan = (String)data.get("txtkesimpulan");
//			    String txtnilaian = (String)data.get("txtnilaian");
//	    		String txtulasan = (String)data.get("txtulasan");
//	    		String txtkeputusan = (String)data.get("txtkeputusan");
//	    		String txtkedudukan = (String)data.get("txtkedudukan");
//	    		String txtperuntukan = (String)data.get("txtperuntukan");
//	    		String txtperakuan = (String)data.get("txtperakuan");
//	    		String txtperihalpohon = (String)data.get("txtperihalpohon");	
//	    		String txtanggaran = (String)data.get("txtanggaran");
//	    		String txtulasanjt = (String)data.get("txtulasanjt");
//	    		String txtjawatankuasa = (String)data.get("txtjawatankuasa");
//	    		String txtpandangan = (String)data.get("txtpandangan");
//	    		String txthallain = (String)data.get("txthallain");
	    		
		      	String txtJenisPenggunaan = (String)data.get("txtJenisPenggunaan");
	    		String txtLokasi = (String)data.get("txtLokasi");
	    		String txtKedudukan = (String)data.get("txtKedudukan");
	    		String txtKeadaan = (String)data.get("txtKeadaan");
	    		String txtTajuk = (String) data.get("txtTajuk");
	    		String txtSidang = (String) data.get("txtSidang");
	    		String txtTempatSidang = (String) data.get("txtTempatSidang");
	    		String txtTarikhSidang = (String) data.get("txtTarikhSidang");
	    		String txtMasaSidang = (String) data.get("txtMasaSidang");
	    		String jeniswaktu = (String) data.get("jeniswaktu");
	    		
	    		
	    		
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_mmk", id_mmk);
			    r.add("jenis_penggunaan_tnh", txtJenisPenggunaan);	
	    		r.add("lokasi_tanah", txtLokasi);	
	    		r.add("kedudukan_tanah", txtKedudukan);	
	    		r.add("keadaan_tanah", txtKeadaan);	
//			    r.add("tujuan", txttujuan);
//			    r.add("latarbelakang", txtlatarbelakang);
//			    r.add("perihal_tanah", txtlaporan);
//			    r.add("syor", txtsyor);
//			    r.add("nilai_atas_tanah", txtnilaian);
//	    		r.add("ulasan_pengarah", txtulasan);
//	    		r.add("kedudukan_laporan_tnh", txtkedudukan);		
//	    		r.add("perihal_pohon", txtperihalpohon);
//	    		r.add("anggaran_kos", txtanggaran);	
//	    		r.add("hal_lain", txthallain);	
//	    		r.add("pandangan", txtpandangan);	
//	    		r.add("ulasan_jabteknikal", txtulasanjt);	
//	    		r.add("jawatankuasa_tanah", txtjawatankuasa);
//	    		r.add("pengesahan_peruntukan", txtperuntukan);
//	    		r.add("perakuan_pentadbir_tnh", txtperakuan);
//	    		r.add("keputusan", txtkeputusan);	
//			    r.add("asas_pertimbangan", txtasaspertimbangan);
//			    r.add("implikasi", txtimplikasi);
//			    r.add("kesimpulan", txtkesimpulan);
			    r.add("id_kemaskini",id_user);
			    r.add("tarikh_kemaskini",r.unquote("sysdate"));
			    
			    
			    
			    
			    
			    r.add("TAJUK", txtTajuk);
				r.add("KETERANGAN_SIDANG", txtSidang);
				r.add("TEMPAT_SIDANG", txtTempatSidang);
				r.add("WAKTU_SIDANG", txtMasaSidang);
				r.add("JENIS_WAKTU_SIDANG", jeniswaktu);
				if (txtTarikhSidang != "") {
					r.add("TARIKH_SIDANG", r.unquote("to_date('"
							+ txtTarikhSidang + "','dd/MM/yyyy')"));
				} else {
					r.add("TARIKH_SIDANG", "");
				}
			    
			    sql = r.getSQLUpdate("tblpptmmk");
			    myLogger.info("update MMK S4 :"+sql);
			    
			    stmt.executeUpdate(sql);
		    
		 	}
		    finally {
		    	if (db != null) db.close();
		    }
	 }//close update penyediaan
	 
	 
	//--View Data Kertas--//
	@SuppressWarnings("unchecked")
	public static Vector getViewKertas(String id_mmk) throws Exception {
		
		   Db db = null;
		   String sql = "";
		    
		   try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql = "SELECT a.JENIS_WAKTU_SIDANG,a.WAKTU_SIDANG,a.TARIKH_SIDANG,a.TEMPAT_SIDANG,a.KETERANGAN_SIDANG,a.TAJUK," +
		    				"" +
		    				"b.user_name,a.id_mmk,a.id_permohonan,a.ulasan,a.no_rujmmk,a.tarikh_semak,a.tujuan, ";
		    		sql += " a.latarbelakang,a.asas_pertimbangan,a.kesimpulan, ";
		    		sql += " a.syor,a.kedudukan_laporan_tnh,a.pengesahan_peruntukan,a.pandangan,a.implikasi,a.ulasan_jabteknikal, ";
		    		sql += " a.perihal_tanah,a.perihal_pohon,a.anggaran_kos,a.perakuan_pentadbir_tnh,a.nilai_atas_tanah, ";
		    		sql += " a.pengecualian_upah_ukur,a.tarikh_mmk,a.status_semakan,a.ulasan_pengarah,a.keputusan,a.nama_tuan_tanah, ";
		    		sql += " a.jawatankuasa_tanah, a.hal_lain, a.flag_semak, a.tarikh_hantar, a.flag_semakan_pengarah, ";
		    		sql += " a.jenis_penggunaan_tnh, a.lokasi_tanah, a.kedudukan_tanah, a.keadaan_tanah ";
		    		sql += " FROM tblpptmmk a, users b ";
					sql += " WHERE a.user_id = b.user_id(+)";
		    		sql += " AND a.id_mmk = '"+id_mmk+"'";
		    		
		    		myLogger.info("GET LIST MML SEK 4"+sql);
		    		
		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		    		Hashtable h;
		      
		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("JENIS_WAKTU_SIDANG", rs.getString("JENIS_WAKTU_SIDANG") == null ? "" : rs
								.getString("JENIS_WAKTU_SIDANG"));				
						h.put("WAKTU_SIDANG", rs.getString("WAKTU_SIDANG") == null ? "" : rs
								.getString("WAKTU_SIDANG"));				
						h.put("TARIKH_SIDANG", rs.getString("TARIKH_SIDANG") == null ? ""
								: Format.format(rs.getDate("TARIKH_SIDANG")));				
						h.put("TEMPAT_SIDANG", rs.getString("TEMPAT_SIDANG") == null ? "" : rs
								.getString("TEMPAT_SIDANG"));				
						h.put("KETERANGAN_SIDANG", rs.getString("KETERANGAN_SIDANG") == null ? "" : rs
								.getString("KETERANGAN_SIDANG"));	
						h.put("TAJUK", rs.getString("TAJUK") == null ? "" : rs
								.getString("TAJUK"));	
						
						
						
		    			h.put("jenis_penggunaan_tnh", rs.getString("jenis_penggunaan_tnh")== null?"":rs.getString("jenis_penggunaan_tnh"));
		    			h.put("lokasi_tanah", rs.getString("lokasi_tanah")== null?"":rs.getString("lokasi_tanah"));
		    			h.put("kedudukan_tanah", rs.getString("kedudukan_tanah")== null?"":rs.getString("kedudukan_tanah"));
		    			h.put("keadaan_tanah", rs.getString("keadaan_tanah")== null?"":rs.getString("keadaan_tanah"));
		    			h.put("flag_semakan_pengarah", rs.getString("flag_semakan_pengarah")== null?"":rs.getString("flag_semakan_pengarah"));
		    			h.put("tarikh_hantar", rs.getDate("tarikh_hantar")==null?"":Format.format(rs.getDate("tarikh_hantar")));
		    			h.put("id_mmk", rs.getString("id_mmk")== null?"":rs.getString("id_mmk"));
		    			h.put("hal_lain", rs.getString("hal_lain")== null?"":rs.getString("hal_lain"));
		    			h.put("ulasan", rs.getString("ulasan")== null?"":rs.getString("ulasan"));
		    			h.put("jawatankuasa_tanah", rs.getString("jawatankuasa_tanah")== null?"":rs.getString("jawatankuasa_tanah"));
		    			h.put("nama_tuan_tanah", rs.getString("nama_tuan_tanah")== null?"":rs.getString("nama_tuan_tanah"));
		    			h.put("no_rujmmk", rs.getString("no_rujmmk")== null?"":rs.getString("no_rujmmk"));
		    			h.put("tujuan", rs.getString("tujuan")== null?"":rs.getString("tujuan"));
		    			h.put("latarbelakang", rs.getString("latarbelakang")== null?"":rs.getString("latarbelakang"));
		    			h.put("asas_pertimbangan", rs.getString("asas_pertimbangan")== null?"":rs.getString("asas_pertimbangan"));
		    			h.put("kesimpulan", rs.getString("kesimpulan")== null?"":rs.getString("kesimpulan"));
		    			h.put("syor", rs.getString("syor")== null?"":rs.getString("syor"));
		    			h.put("kedudukan_laporan_tnh", rs.getString("kedudukan_laporan_tnh")== null?"":rs.getString("kedudukan_laporan_tnh"));
		    			h.put("pengesahan_peruntukan", rs.getString("pengesahan_peruntukan")== null?"":rs.getString("pengesahan_peruntukan"));
		    			h.put("pandangan", rs.getString("pandangan")== null?"":rs.getString("pandangan"));
		    			h.put("implikasi", rs.getString("implikasi")== null?"":rs.getString("implikasi"));
		    			h.put("ulasan_jabteknikal", rs.getString("ulasan_jabteknikal")== null?"":rs.getString("ulasan_jabteknikal"));
		    			h.put("perihal_tanah", rs.getString("perihal_tanah")== null?"":rs.getString("perihal_tanah"));
		    			h.put("perihal_pohon", rs.getString("perihal_pohon")== null?"":rs.getString("perihal_pohon"));
		    			h.put("anggaran_kos", rs.getString("anggaran_kos")== null?"":rs.getString("anggaran_kos"));
		    			h.put("perakuan_pentadbir_tnh", rs.getString("perakuan_pentadbir_tnh")== null?"":rs.getString("perakuan_pentadbir_tnh"));
		    			h.put("nilai_atas_tanah", rs.getString("nilai_atas_tanah")== null?"":rs.getString("nilai_atas_tanah"));
		    			h.put("pengecualian_upah_ukur", rs.getString("pengecualian_upah_ukur")== null?"":rs.getString("pengecualian_upah_ukur"));
		    			h.put("status_semakan", rs.getString("status_semakan")== null?"":rs.getString("status_semakan"));
		    			h.put("ulasan_pengarah", rs.getString("ulasan_pengarah")== null?"":rs.getString("ulasan_pengarah"));
		    			h.put("keputusan", rs.getString("keputusan")== null?"":rs.getString("keputusan"));
		    			h.put("user_name", rs.getString("user_name")== null?"":rs.getString("user_name"));
		    			h.put("tarikh_semak", rs.getDate("tarikh_semak")==null?"":Format.format(rs.getDate("tarikh_semak")));
		    			h.put("tarikh_mmk", rs.getDate("tarikh_mmk")==null?"":Format.format(rs.getDate("tarikh_mmk")));
		    			h.put("flag_semak", rs.getString("flag_semak")== null?"":rs.getString("flag_semak"));
		    			list.addElement(h);
			        
		    		}
			      return list;
			    }
			    finally {
			      if (db != null) db.close();
			    }
			    
			 }//close getList
	 
	//--View Data Keputusan Kertas--//
	 @SuppressWarnings("unchecked")
	public static Vector getViewKertasKeputusan(String id_mmk) throws Exception {
		    Db db = null;

		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmtK = db.getStatement();
		      SQLRenderer rK = new SQLRenderer();
		      
		      rK.add("id_mmk");
		      rK.add("id_mmkkeputusan");
		      rK.add("tarikh_hantar");
		      rK.add("tarikh_keputusan");
		      rK.add("tarikh_terima");
		      rK.add("status_keputusan");
		      rK.add("ulasan");
				
		      rK.add("id_mmk", id_mmk);
				
		      sql = rK.getSQLSelect("Tblpptmmkkeputusan");
		      ResultSet rs = stmtK.executeQuery(sql);
		      
		      Vector list = new Vector();
		      Hashtable h;
		      
		      while (rs.next()) {
		    	    h = new Hashtable();
			        h.put("id_mmk", rs.getString("id_mmk")==null?"":rs.getString("id_mmk"));
			        h.put("id_mmkkeputusan", rs.getString("id_mmkkeputusan")==null?"":rs.getString("id_mmkkeputusan"));
			        h.put("tarikh_hantar", rs.getDate("tarikh_hantar")==null?"":Format.format(rs.getDate("tarikh_hantar")));
			        h.put("tarikh_keputusan", rs.getDate("tarikh_keputusan")==null?"":Format.format(rs.getDate("tarikh_keputusan")));
			        h.put("tarikh_terima", rs.getDate("tarikh_terima")==null?"":Format.format(rs.getDate("tarikh_terima")));
			        h.put("status_keputusan", rs.getString("status_keputusan")==null?"":rs.getString("status_keputusan"));
			        h.put("ulasan", rs.getString("ulasan")==null?"":rs.getString("ulasan"));
			        
			        list.addElement(h);
			      }
			      return list;
			    }
			    finally {
			      if (db != null) db.close();
			    }
			    
			 }//close getList
	 
	 
	 public static void hantar(Hashtable data) throws Exception {
		    
		 Db db = null;
		 String sql = "";
		 
		 try{
			 
			 	db = new Db();
			 	Statement stmt = db.getStatement();
			 	
			 	long id_warta = DB.getNextID("TBLPPTWARTA_SEQ");
			 	String id_permohonan = (String)data.get("id_permohonan");
			 	//String tarikhWarta = (String)data.get("tarikhWarta");
			  
			 	//String TW = "to_date('" + tarikhWarta + "','dd/MM/yyyy')";
			  
			 	String id_user = (String)data.get("id_user");
			  
			 	String status = "31"; //pengwartaan
			 
			 	SQLRenderer rP = new SQLRenderer();
			 	rP.update("id_permohonan", id_permohonan );
			 	rP.add("id_status", status); 	
			 	rP.add("id_kemaskini",id_user);
			 	rP.add("tarikh_kemaskini",rP.unquote("sysdate"));
			 	sql = rP.getSQLUpdate("tblpptpermohonan");
			 	stmt.executeUpdate(sql);
		     
			 	//add idwarta sebelum ke warta
			 	SQLRenderer rW = new SQLRenderer();
			 	rW.add("id_warta", id_warta);
			 	rW.add("id_permohonan", id_permohonan); 	
			 	//rW.add("tarikh_warta",rW.unquote(TW));
			 	rW.add("id_masuk",id_user);
			 	rW.add("tarikh_masuk",rW.unquote("sysdate"));
			 	sql = rW.getSQLInsert("tblpptwarta");
			 	stmt.executeUpdate(sql);
		      
		    }
		    finally {
		      if (db != null) db.close();
		    }
	}//close hantar
	 
	 public static Vector getNamaAgensi(String idAgensi)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("a.nama_agensi");	 
		     
		      r.add("a.id_agensi",idAgensi);
		      
		      sql = r.getSQLSelect("tblrujagensi a");
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("nama_agensi",rs.getString("nama_agensi"));	    	 
		    	  list.addElement(h);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
	 
	 //simpan tarikh borang A
	 public static void simpanTarikh(Hashtable data) throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    try {
		    	
		    		String id_user = (String)data.get("id_user");
		    		String id_permohonan = (String)data.get("id_permohonan");
		    		
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_permohonan", id_permohonan);
		    		r.add("tarikh_boranga",r.unquote("sysdate"));
		    		r.add("id_kemaskini",id_user);
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		sql = r.getSQLUpdate("tblpptpermohonan");
		    		stmt.executeUpdate(sql);
		    		
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }//close simpan tarikh borang a
	 
	 
	 //get max tarikh luput
	 public void setTarikhLuput(String idmmk) throws Exception{
			
		Db db = null;
		tarikhLuput.clear();
		String sql = "";
			
			try {
					db = new Db();
					Statement stmt = db.getStatement();
				
					sql = "SELECT MAX(mk.tarikh_luput)as tarikh_luput "; 
					sql += " FROM Tblpptpermohonan p, Tblrujstatus sta, Tblpfdfail f,Tblpptmmk m , tblpptmmkkeputusan mk "; 
					sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
					sql += " AND mk.id_mmk = m.id_mmk(+) "; 
					sql += " AND p.id_fail = f.id_fail ";
					sql += " AND p.id_status = sta.id_status ";
					sql += " AND m.id_mmk = '"+idmmk+"'";
				
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;
				
					while (rs.next()){
						h = new Hashtable();
						h.put("tarikh_luput", rs.getDate("tarikh_luput")==null?"":Format.format(rs.getDate("tarikh_luput")));
						tarikhLuput.addElement(h);
					}
				
				}finally {
					if(db != null) db.close();
				}
				
	 }//close setDataListKertas
	 
	@SuppressWarnings("unchecked")
	public static Vector getIdMMK(String idpermohonan)throws Exception {
		
		    Db db = null;
		    String sql = "";
		    
		    try {
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		sql = "SELECT DISTINCT MAX(m.id_mmk)as id_mmk "; 
					sql += " FROM Tblpptpermohonan p ,Tblpptmmk m"; 
					sql += " WHERE m.id_Permohonan(+) = p.id_Permohonan ";
					sql += " AND p.id_Permohonan = '"+idpermohonan+"'";
		    		
		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("id_mmk", rs.getString("id_mmk")==null?"":rs.getString("id_mmk"));   	 
		    			list.addElement(h);
		    		}
		    		return list;
		    		} finally {
		    		if (db != null) db.close();
		    	}
	 }
	 
	 //--Update Semakan--//
	 public static void hantarPengesahan(Hashtable data) throws Exception {
		 
		 Db db = null;
		 String sql = "";
		 
		 try{
		     
		      	String id_user = (String)data.get("id_user");
		      	String id_mmk = (String)data.get("id_mmk");
		     
		      	db = new Db();
		      	Statement stmt = db.getStatement();
		      	SQLRenderer r = new SQLRenderer();
		      	r.update("id_mmk", id_mmk);
		      	r.add("flag_semakan_pengarah", "1");			  
		      	r.add("id_kemaskini",id_user);
		      	r.add("tarikh_kemaskini",r.unquote("sysdate"));
		      	sql = r.getSQLUpdate("tblpptmmk");
		      	stmt.executeUpdate(sql);
		      
		    }
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close hantarPengesahan
	 
}//close class
