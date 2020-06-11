package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class FrmHakmilikSementaraSenaraiPermohonanUPTData {
	static Logger myLogger = Logger.getLogger(FrmHakmilikSementaraSenaraiPermohonanUPTData.class);
	
	Vector list = null;
	private static Vector listCarianSek8 = null;		
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static Vector listDokumen = new Vector();
	private static Vector listGetUserId = null;
	private static Vector namaPengarah = null;
	private Vector listPohon = new Vector();
	private Vector listMaklumatTanah = null;
	
	
	public static  Vector getListCarianSementara(){
		return listCarianSek8;
	} 		
	public Vector getListPohon(){
		return listPohon;
	}	
	public Vector getListMaklumatTanah(){
		return listMaklumatTanah;
	}
	public Vector getListDokumen(){
		return listDokumen;
	}		
	public static Vector getUserIds() {
		return listGetUserId;
	}	
	public static Vector getNamaPengarah() {
		return namaPengarah;
	}	
	
	
	public void setListPemohon(String tarikh, String no_permohonan, String status)throws Exception {
	    Db db = null;
	    String sql = "";
	    
  	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  		SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
  	    
	    try{
	      list = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	     
	      sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan ";
	      sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	      sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	      sql +="AND p.id_status = s.id_status ";
	     // sql +="AND s.kod_status IN ('02','27','28') ";
//	      sql +="AND s.id_seksyen = 1 ";
	      sql +="AND f.id_suburusan = 53";
	      
	      if(no_permohonan != null){
	    	  sql = sql + " AND UPPER(p.no_permohonan) LIKE '%'||'" + no_permohonan.toUpperCase() + "'||'%'";  
	      }
	     
	      if(tarikh != null){
	    	  if (!tarikh.toString().trim().equals("")){
	    	  sql +="AND p.tarikh_permohonan = '" + sdf.format(Format.parse(tarikh)).toUpperCase()+ "' ";
	    	  }
	      }
	      if(status != null){
	    	  if (!status.trim().equals("")){
	    		  if (!status.trim().equals("0")){
	    			  sql +="AND s.id_status = '" + status + "' ";
	    		  }
	    	  }	  
	      }
	    
	        
	      sql +="ORDER by p.tarikh_permohonan desc, p.no_permohonan desc ";
	     
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      
	      Hashtable h = null;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_fail", rs.getString("id_fail"));
	    	  h.put("id_status", rs.getString("id_status"));
	    	  h.put("id_permohonan", rs.getString("id_permohonan"));
	    	  h.put("no_permohonan", rs.getString("no_permohonan"));
	    	  h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":sdf.format(rs.getDate("tarikh_permohonan")));
	    	  h.put("nama_suburusan", rs.getString("nama_suburusan"));
	    	  h.put("nama_kementerian", rs.getString("nama_kementerian"));
	    	  h.put("status", rs.getString("keterangan"));
	    	  if(rs.getString("no_fail") == null){
		    		h.put("no_fail","Belum Disahkan");
		    	}else{
		    		h.put("no_fail",rs.getString("no_fail"));
		    	}
	    	  list.addElement(h);
	    	  bil++;
	    	  count++;
	    	  
	      }//close while
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("id_fail", "");
	    	  h.put("id_status", "");
	    	  h.put("id_permohonan", "");
	    	  h.put("no_permohonan", "Tiada rekod.");
	    	  h.put("tarikh_permohonan","");
	    	  h.put("nama_suburusan", "");
	    	  h.put("nama_kementerian", "");
	    	  h.put("status", "");
	    	  h.put("no_fail","");
	    	  list.addElement(h);
	    	  
	      }
	      
	    }//close try 
	    finally{
	      if (db != null) db.close();
	    }//close finally
	 }//close getListPemohon
	 public Vector getList(){
		  return list;
	  }
	 
	 
	 // ALTERED BY ELLY 07.05.2010
	 
		public static Vector getListPemohonSementara()throws Exception {
			 
			Db db = null;
			String sql = "";
				    
			try{
				    	
					db = new Db();
				    Statement stmt = db.getStatement();
				     
				    sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, "; 
		    		sql +=" su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, p.flag_semak ";
				    sql +=" FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		    		sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		    		sql +=" AND p.id_status = s.id_status ";
		    		sql +=" AND f.id_suburusan = '53' ";
//		    		sql +=" AND p.id_status IN (11,127)";
		    		sql +=" ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
		    		
				    ResultSet rs = stmt.executeQuery(sql);
				    Vector list = new Vector();
				      
				    Hashtable h = null;
				    int bil = 1;

				    while (rs.next()) {
				    	h = new Hashtable();
				    	h.put("bil", bil);
				    	h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
				    	h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
				    	h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
				    	h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
				    	h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
				    	h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
				    	h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
				    	h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));

				    	
				    	if(rs.getString("flag_semak") != null && rs.getString("flag_semak") != ""){
		    				
		    				if(rs.getString("flag_semak").equals("1")){
		    					h.put("status_fail","<b>TUNGGU PENGESAHAN</b>");
		    				}else if(rs.getString("flag_semak").equals("2")){
		    					h.put("status_fail","<b>TELAH DISAHKAN</b>");
		    				}else{
		    					h.put("status_fail","PENDAFTARAN");
		    				}
		    				
		    			}else{
		    				h.put("status_fail","PENDAFTARAN");
		    			}

				    	if(rs.getString("no_fail") == null){
				    		h.put("no_fail","BELUM DISAHKAN");
				    	}else{
				    		h.put("no_fail",rs.getString("no_fail"));
				    	}
				    	list.addElement(h);
				    	bil++;
				    	  
				    }//close while
				    	return list;
				}//close try 
				finally{
				    if (db != null) db.close();
				}//close finally
				    	
		}//close getListPemohonSementara 
		
		
//		public static void setListCarianSementara(String carianNofail, String carianTarikh, String status)throws Exception {
//			
//			listCarianSek8 = new Vector();
//			
//		    Db db = null;
//		    listCarianSek8.clear();
//		    String sql = "";
//		    
//		    String cariTarikh = "";
//		    if(carianTarikh!=""){
//		    	cariTarikh = "to_date('" + carianTarikh + "','dd/MM/yyyy')";
//		    }	    
//		    
//		    String nofail = carianNofail.trim();
//		    
//		    try {
//		    	
//		    		db = new Db();
//		    		Statement stmt = db.getStatement();
//		      
//		    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, "; 
//		    		sql +=" su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, p.flag_semak ";
//				    sql +=" FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
//		    		sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
//		    		sql +=" AND p.id_status = s.id_status ";
//		    		sql +=" AND f.id_suburusan = '53' ";
////		    		sql +=" AND p.id_status IN (11,127)";
//		     
//		    		//NO FAIL
//					if (carianNofail != "" && carianNofail != null) {
//						if (!nofail.equals("")) {
//							sql = sql + " AND UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%'";
//						}
//					}//close carian by nofail
//			
//					//TARIKH
//					if (carianTarikh != "" && carianTarikh != null) {
//						if (!cariTarikh.equals("")) {
//							sql = sql + " AND UPPER(p.tarikh_permohonan) = "+cariTarikh;
//						}
//					}//close carian by tarikh
//		
//		    		//STATUS
//					if (status != "" && status != null) {
//							sql = sql + " AND UPPER(s.id_status) = '"+status+"'";
//					}//close carian by status
//
//		    		sql +=" ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
//		    		myLogger.info("SQL setListCarianSementara :: "+sql);
//		    		ResultSet rs = stmt.executeQuery(sql);
//		      
//		    		Hashtable h;
//		    		int bil = 1;
//
//		    		while (rs.next()) {
//				    	h = new Hashtable();
//				    	h.put("bil", bil);
//				    	h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
//				    	h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
//				    	h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
//				    	h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
//				    	h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
//				    	h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
//				    	h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
//				    	h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
//				    	
//				    	if(rs.getString("flag_semak") != null && rs.getString("flag_semak") != ""){
//		    				
//		    				if(rs.getString("flag_semak").equals("1")){
//		    					h.put("status_fail","<b>TUNGGU PENGESAHAN</b>");
//		    				}else if(rs.getString("flag_semak").equals("2")){
//		    					h.put("status_fail","<b>TELAH DISAHKAN</b>");
//		    				}else{
//		    					h.put("status_fail","PENDAFTARAN");
//		    				}
//		    				
//		    			}else{
//		    				h.put("status_fail","PENDAFTARAN");
//		    			}
//
//				    	if(rs.getString("no_fail") == null){
//				    		h.put("no_fail","BELUM DISAHKAN");
//				    	}else{
//				    		h.put("no_fail",rs.getString("no_fail"));
//				    	}
//				    	listCarianSek8.addElement(h);
//				    	bil++;
//				    	  
//				    }//close while
//		      
//		    }// 
//		    finally {
//		      if (db != null) db.close();
//		    }
//		    
//		  }//close carian		

		@SuppressWarnings("unchecked")
		public void setListPohon(String idpermohonan) throws Exception {
			Db db = null;
			listPohon.clear();
			String sql = "";
			
			
			try{
					db = new Db();
					Statement stmt = db.getStatement();
					
					sql = " SELECT DISTINCT p.id_permohonan, p.id_status, p.flag_jenispermohonan, p.no_permohonan, f.no_fail, ";
					sql += " f.id_fail, n.nama_negeri, p.tarikh_permohonan, p.tarikh_permohonan_kjp,p.tujuan, k.nama_kementerian, k.id_kementerian, "; 
					sql += " s.keterangan, d.nama_daerah, d.id_daerah, p.tarikh_kehendaki,  p.tarikh_surat, p.id_agensi, "; 
					sql += " p.no_rujukan_surat, p.flag_peruntukan, p.id_mukim, p.no_permohonan_online, "; 
					sql += " us.nama_suburusan, us.id_suburusan, p.no_rujukan_ptg, p.no_rujukan_ptd, flag_jenis_kod_daerah, ";
					sql += " k.alamat1, k.alamat2, k.alamat3, k.poskod, k.id_negeri, p.flag_semak, n2.id_negeri as idProjekNegeri, n2.nama_negeri as nama_negeriprojek, ";
				    sql += " p.tarikh_pendudukan_mula, p.tarikh_pendudukan_akhir, p.tempoh_pendudukan "; //PPT-41
					sql += " FROM Tblrujsuburusan us, Tblpfdfail f, Tblrujdaerah d, Tblrujnegeri n, ";
					sql += " Tblrujkementerian k, Tblrujstatus s,  Tblpptpermohonan p, Tblrujnegeri n2 ";  
					sql += " WHERE f.id_fail(+) = p.id_fail "; 
					sql += " AND k.id_kementerian(+) = f.id_kementerian "; 
					sql += " AND n.id_negeri(+) = k.id_negeri "; 
					sql += " AND n2.id_negeri(+) = f.id_negeri "; 
					sql += " AND s.id_status(+) = p.id_status "; 
					sql += " AND d.id_daerah(+) = p.id_daerah "; 
					sql += " AND us.id_suburusan(+) = f.id_suburusan ";  
					sql += " AND p.id_permohonan = '"+idpermohonan+"'";
			
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;
			
					while(rs.next()) {
						h = new Hashtable();
						
						h.put("flag_jenis_kod_daerah", rs.getString("flag_jenis_kod_daerah")==null?"":rs.getString("flag_jenis_kod_daerah"));
						h.put("no_flag_jenispermohonan", rs.getString("flag_jenispermohonan")==null?"":rs.getString("flag_jenispermohonan"));
						h.put("no_permohonan_online", rs.getString("no_permohonan_online")==null?"":rs.getString("no_permohonan_online"));
						h.put("nama_negeriprojek", rs.getString("nama_negeriprojek")==null?"":rs.getString("nama_negeriprojek"));
						h.put("flag_semak", rs.getString("flag_semak")==null?"":rs.getString("flag_semak"));
						h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
						h.put("idProjekNegeri", rs.getString("idProjekNegeri")==null?"":rs.getString("idProjekNegeri"));
						h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
						h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
//						h.put("id_senaraisemak", rs.getString("id_senaraisemak")==null?"":rs.getString("id_senaraisemak"));
						h.put("idNegeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
						h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
						h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
						h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
						h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
						h.put("idFail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
						h.put("idDaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
						h.put("idSuburusan", rs.getString("id_suburusan")==null?"":rs.getString("id_suburusan"));
						h.put("idAgensi", rs.getString("id_agensi")==null?"0":rs.getString("id_agensi"));
						h.put("idKementerian", rs.getString("id_kementerian")==null?"":rs.getString("id_kementerian"));
						h.put("noPermohonan", rs.getString("no_permohonan")==null?"":rs.getString("no_permohonan"));
						h.put("no_fail", rs.getString("no_fail")==null?"BELUM DISAHKAN":rs.getString("no_fail"));
						h.put("namaNegeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
						h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
						h.put("tarikh_permohonan_kjp", rs.getDate("tarikh_permohonan_kjp")==null?"":Format.format(rs.getDate("tarikh_permohonan_kjp")));
						h.put("tujuan", rs.getString("tujuan")==null?"":rs.getString("tujuan"));
						h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
						h.put("status", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
						h.put("daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
						h.put("tarikh_kehendaki", rs.getDate("tarikh_kehendaki")==null?"":Format.format(rs.getDate("tarikh_kehendaki")));
						h.put("tarikh_surat", rs.getDate("tarikh_surat")==null?"":Format.format(rs.getDate("tarikh_surat")));
						h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")==null?"":rs.getString("no_rujukan_surat"));
						h.put("flag_peruntukan", rs.getString("flag_peruntukan")==null?"":rs.getString("flag_peruntukan"));
//						h.put("flag_segera", rs.getString("flag_segera")==null?"":rs.getString("flag_segera"));
						h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));
						h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
						h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"":rs.getString("no_rujukan_ptd"));
				    	h.put("tarikh_pendudukan_mula", rs.getDate("tarikh_pendudukan_mula")==null?"":Format.format(rs.getDate("tarikh_pendudukan_mula"))); //PPT-41
				    	h.put("tarikh_pendudukan_akhir", rs.getDate("tarikh_pendudukan_akhir")==null?"":Format.format(rs.getDate("tarikh_pendudukan_akhir"))); //PPT-41
				    	h.put("tempoh_pendudukan", rs.getString("tempoh_pendudukan")==null?"":rs.getString("tempoh_pendudukan")); //PPT-41
				    	
						if(rs.getLong("flag_jenispermohonan") == 1){
							h.put("flag_jenispermohonan","PERMOHONAN ONLINE");
						}
						else if(rs.getLong("flag_jenispermohonan") == 2){
							h.put("flag_jenispermohonan","PERMOHONAN KAUNTER");
						}else{
							h.put("flag_jenispermohonan","-");
						}
				
			
						listPohon.addElement(h);
					}
			}
			finally {
				if(db != null) db.close();
			}
			
		}//close setlistpohon	
		
		
		Hashtable getListMukimLot = null;
		public Hashtable getListMukimLot(String idPermohonan,String lot,String idpegawai,Integer from,Integer to) throws Exception {
			getListMukimLot = new Hashtable();
			
			String nama2Mukim = "";
			String listLOT = "";
			String listLOTHM = "";
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				sql = "SELECT COUNT(distinct mk.nama_mukim) as totalMukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";				
				ResultSet rx = stmt.executeQuery(sql);				
				int totalMukim = 0;
				while (rx.next()){					
					totalMukim = rx.getInt("totalMukim");														
				}
		
				sql = "SELECT DISTINCT initcap(nama_mukim)as nama_mukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";				
				ResultSet rz = stmt.executeQuery(sql);					
				String namaMukim = "";
				int bilMukim = 0;				
				while (rz.next()){					
					if(totalMukim!=0){
						if(bilMukim==0){							
							namaMukim += namaMukim = rz.getString("nama_mukim");						
						}else{							
							if(totalMukim - bilMukim == 1){
								namaMukim += " dan "+rz.getString("nama_mukim");
							}else{
								namaMukim += ", "+rz.getString("nama_mukim");	
							}	
						}
						bilMukim++;	
					
					}else{
						namaMukim = "";
					}
					
					if(totalMukim == bilMukim){
						nama2Mukim = namaMukim;
					}else{
						nama2Mukim = "";
					}
				}
				
				
				//////////////get lot////////////////////
				sql = "SELECT COUNT(m.id_hakmilik) as totalLOT FROM Tblpptpermohonan p, Tblppthakmilik m, Tblrujlot lt ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
				sql += " AND m.id_lot = lt.id_lot(+)"; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";				
				ResultSet rl = stmt.executeQuery(sql);				
				int totalLOT = 0;
				while (rl.next()){					
					totalLOT = rl.getInt("totalLOT");														
				}
				
				sql = "SELECT CASE ";
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";      
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
				sql += " ELSE '' ";
				sql += " END AS NO_LOTPT, m.no_hakmilik, jh.kod_jenis_hakmilik  FROM Tblpptpermohonan p, Tblrujlot lt, Tblppthakmilik m, Tblrujjenishakmilik jh ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
				sql += " AND m.id_lot = lt.id_lot(+) "; 
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";				
				ResultSet rl2 = stmt.executeQuery(sql);					
				String LotPT = "";
				String LotPTHM = "";
				int bilLOT = 0;				
				while (rl2.next()){				
					if(totalLOT!=0){
						if(bilLOT==0){							
							LotPTHM += LotPTHM = (rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));	
							LotPT += LotPT = rl2.getString("NO_LOTPT");							
						}else{							
							if(totalLOT - bilLOT == 1){
								LotPTHM += " dan "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));
								LotPT += " dan "+rl2.getString("NO_LOTPT");
							}else{
								LotPTHM += ", "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));	
								LotPT += ", "+rl2.getString("NO_LOTPT");
							}	
						}
						bilLOT++;	
					
					}else{
						LotPT = "";
						LotPTHM = "";
					}
					
					if(totalLOT == bilLOT){
						listLOT = LotPT;
						listLOTHM = LotPTHM;
					}else{
						listLOT = "";
						listLOTHM = "";
					}
				}
				

				
				/*	
				ResultSet rs = stmt.executeQuery(sql);
				
				Hashtable h;
				h = new Hashtable();
				while (rs.next()) {
					if (rs.getString("id_seksyen") == null) {
						h.put("id_seksyen_user", "");
					} else {
						h.put("id_seksyen_user", rs.getString("id_seksyen"));
					}				
				}*/
				
				//getListMukimLot = new Hashtable();
				getListMukimLot.put("listLOT", listLOT.toUpperCase());
				getListMukimLot.put("listLOTHM", listLOTHM.toUpperCase());
				getListMukimLot.put("nama2Mukim", nama2Mukim.toUpperCase());
				getListMukimLot.put("nama2MukimInit", nama2Mukim);
				
				return getListMukimLot;
			} finally {
				if (db != null) db.close();
			}
		}

		@SuppressWarnings("unchecked")
		public void setListMaklumatTanah(String idPermohonan,String lot,String idpegawai) throws Exception{
			
			listMaklumatTanah = new Vector();
			
			Db db = null;
			listMaklumatTanah.clear();
			String sql = "";
			String noLOT = lot.trim();
			String nama2Mukim = "";
			
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					
					sql = "SELECT COUNT(distinct mk.nama_mukim) as totalMukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
					sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
					sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
					
					ResultSet rx = stmt.executeQuery(sql);				
					int totalMukim = 0;

					while (rx.next()){					
						totalMukim = rx.getInt("totalMukim");														
					}
			
					sql = "SELECT DISTINCT nama_mukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
					sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
					sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
					
					ResultSet rz = stmt.executeQuery(sql);				
					String namaMukim = "";
					int bilMukim = 0;
					
					while (rz.next()){					
							
						if(totalMukim!=0){
							if(bilMukim==0){
								
								namaMukim += namaMukim = rz.getString("nama_mukim");	
							
							}else{
								
								if(totalMukim - bilMukim == 1){
									namaMukim += " dan "+rz.getString("nama_mukim");
								}else{
									namaMukim += ", "+rz.getString("nama_mukim");	
								}	
							}
							bilMukim++;	
						
						}else{
							namaMukim = "";
						}
						
						if(totalMukim == bilMukim){
							nama2Mukim = namaMukim;
						}else{
							nama2Mukim = "";
						}
					}
					
					
					sql = "SELECT DISTINCT f.no_fail, m.catatan, p.id_permohonan, ls.keterangan as unit1, lt.keterangan as unit2, m.id_hakmilik, m.id_negeri, n.nama_negeri, ";
					sql += " m.id_jenishakmilik, m.id_daerah, m.id_mukim, mk.nama_mukim, m.id_unitluaslot, m.luas_ambil, ";
					sql += " m.no_hakmilik, m.no_lot, m.luas_lot, m.no_pt, m.tarikh_daftar, m.tarikh_luput, m.tempoh_luput, ";
					sql += " m.lokasi,m.syarat_nyata,m.syarat_khas,m.sekatan_hak,m.sekatan_kepentingan,m.no_syit, jh.keterangan as jenis_hakmilik, m.id_kategoritanah, ";
					sql += " m.flag_ambil_segera,d.nama_daerah,m.flag_borangl, m.status_borangl, m.tarikh_terima_hm, e.user_name as nama_pegawai, jh.status_hakmilik, ";
					sql += " m.id_unitluasambil_convert, m.id_unitluaslot_convert, m.flag_jenis_rizab, m.nama_lain_rizab, m.no_warta_rizab, m.tarikh_warta_rizab, m.no_subjaket, m.id_pegawai ";
					sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, "; 
					sql += " Tblrujdaerah d, Users e";
					sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";  
					sql += " AND m.id_negeri = n.id_negeri "; 
					sql += " AND p.id_fail = f.id_fail "; 
					sql += " AND m.id_daerah = d.id_daerah(+)";
					sql += " AND ls.id_luas(+) = m.id_unitluaslot "; 
					sql += " AND m.id_pegawai = e.user_id(+)";
					sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
					sql += " AND m.id_lot = lt.id_lot(+) ";
					sql += " AND m.id_mukim = mk.id_mukim(+) ";  
					sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
					
					//NO LOT
					if (lot != "" && lot != null) {
						if (!noLOT.equals("")) {
							//sql += " AND UPPER(m.no_lot) OR UPPER(m.no_pt) LIKE '%" + noLOT.toUpperCase() + "%'";
							sql += " AND (UPPER(m.no_lot) LIKE '%" + noLOT.toUpperCase() + "%' OR  UPPER(m.no_pt) LIKE '%" + noLOT.toUpperCase() + "%' OR UPPER(lt.keterangan) LIKE '%" + noLOT.toUpperCase() + "%')";
						}
					}//close if nolot
					
					//ID PEGAWAI
					if (idpegawai != "" && idpegawai != null) {
							sql += " AND m.id_pegawai = '"+idpegawai+"'";
					}//close if idpegawai
					
					
					sql += " ORDER BY m.no_lot asc, mk.nama_mukim asc";
					myLogger.info("SQL setListMaklumatTanah :: "+sql);
					ResultSet rs = stmt.executeQuery(sql);	
					Hashtable h;
					int bil = 1;
					
					while (rs.next()){
						h = new Hashtable();
						h.put("nama2Mukim", nama2Mukim);
						h.put("bil", bil);
						h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
						h.put("no_subjaket", rs.getString("no_subjaket")==null?"":rs.getString("no_subjaket"));
						h.put("id_pegawai", rs.getString("id_pegawai")==null?"":rs.getString("id_pegawai"));
						h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
						h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
						h.put("kod_lot", rs.getString("unit2")==null?"":rs.getString("unit2"));
						h.put("unitluaslot", rs.getString("unit1")==null?"":rs.getString("unit1"));			
						h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
						h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
						h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
						h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
						h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
						h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
						h.put("nama_mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
						h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
						h.put("flag_ambil_segera", rs.getString("flag_ambil_segera")==null?"":rs.getString("flag_ambil_segera"));
						h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
						h.put("status_borangl", rs.getString("status_borangl")==null?"":rs.getString("status_borangl"));
						h.put("tarikh_terima_hm", rs.getDate("tarikh_terima_hm")==null?"":Format.format(rs.getDate("tarikh_terima_hm")));
						h.put("status_hakmilik", rs.getString("status_hakmilik")==null?"":rs.getString("status_hakmilik"));
						
						h.put("flag_jenis_rizab", rs.getString("flag_jenis_rizab")==null?"":rs.getString("flag_jenis_rizab"));
						h.put("nama_lain_rizab", rs.getString("nama_lain_rizab")==null?"":rs.getString("nama_lain_rizab"));
						h.put("tarikh_warta_rizab", rs.getDate("tarikh_warta_rizab")==null?"":Format.format(rs.getDate("tarikh_warta_rizab")));
						h.put("no_warta_rizab", rs.getString("no_warta_rizab")==null?"":rs.getString("no_warta_rizab"));
						
						if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
							
							double luasAmbil = rs.getDouble("luas_ambil");
							String LA = Utils.formatLuasHM(luasAmbil);
							h.put("luas_ambil",LA);
									
						}else{
							h.put("luas_ambil","0");
						}
						
						
						//validation for PU
						if(rs.getString("luas_ambil") != null && rs.getString("luas_lot") != null){
							
							double luasAmbil = 0;
							if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("2")){
								luasAmbil = rs.getDouble("luas_ambil"); //meter persegi
							}else{
								luasAmbil = rs.getDouble("luas_ambil") * 10000; //hektar
							}
							
							double luasLot = 0;
							if(rs.getString("id_unitluaslot_convert") != null && rs.getString("id_unitluaslot_convert").equals("2")){
								luasLot = rs.getDouble("luas_lot"); //meter persegi
							}else{
								luasLot = rs.getDouble("luas_lot") * 10000; //hektar
							}						
							
							double baki = luasLot - luasAmbil;
							
							if(baki>0){
								h.put("flagPU","yes");
							}else{
								h.put("flagPU","no");
							}
					
						}else{
							h.put("flagPU","no");
						}
						
						if(rs.getString("id_unitluasambil_convert") != null){
							
							if(rs.getString("id_unitluasambil_convert").equals("1")){
								h.put("unitByKategori", "Hektar");
							}else{
								h.put("unitByKategori", "Meter Persegi");
							}			
						}else{
							h.put("unitByKategori", "");
						}
						
						/*
						if(rs.getString("id_kategoritanah") != null && rs.getString("id_kategoritanah") != ""){
							
							if(rs.getString("id_kategoritanah").equals("2")){
								h.put("unitByKategori", "Hektar");
							}else{
								h.put("unitByKategori", "Meter Persegi");
							}			
						}else{
							h.put("unitByKategori", "");
						}
						*/
						
						if(rs.getString("id_unitluasambil_convert") != null){	
							
							if(rs.getString("id_unitluasambil_convert").equals("1")){	
								
								if(rs.getString("luas_ambil") != null){
									double luasAmbil = rs.getDouble("luas_ambil");
									String LAH = Utils.formatLuasHM(luasAmbil);
									h.put("nilaiTanah",LAH);
								}else{
									h.put("nilaiTanah","0");
								}
								
							}else{						
								if(rs.getString("luas_ambil") != null){
									double luasAmbil = rs.getDouble("luas_ambil") / 10000;
									String LAM = Utils.formatLuasHM(luasAmbil);
									h.put("nilaiTanah",LAM);
								}else{
									h.put("nilaiTanah","0");
								}						
							}	
							
						}else{
							h.put("nilaiTanah","0");
						}
						
						
						h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"":rs.getString("id_kategoritanah"));
						h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
						h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
						h.put("luas_lot", rs.getString("luas_lot")==null?"":rs.getDouble("luas_lot"));
						h.put("no_pt", rs.getString("no_pt")==null?"":rs.getString("no_pt"));
						h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
						
						h.put("tarikh_daftar", rs.getDate("tarikh_daftar")==null?"":Format.format(rs.getDate("tarikh_daftar")));
						h.put("tarikh_luput", rs.getDate("tarikh_luput")==null?"":Format.format(rs.getDate("tarikh_luput")));
						h.put("tempoh_luput", rs.getString("tempoh_luput")==null?"":rs.getString("tempoh_luput"));
						
						h.put("lokasi", rs.getString("lokasi")==null?"":rs.getString("lokasi"));
						h.put("syarat_nyata", rs.getString("syarat_nyata")==null?"":rs.getString("syarat_nyata"));
						h.put("syarat_khas", rs.getString("syarat_khas")==null?"":rs.getString("syarat_khas"));
						
						h.put("sekatan_hak", rs.getString("sekatan_hak")==null?"":rs.getString("sekatan_hak"));
						h.put("sekatan_kepentingan", rs.getString("sekatan_kepentingan")==null?"":rs.getString("sekatan_kepentingan"));
						h.put("no_syit", rs.getString("no_syit")==null?"":rs.getString("no_syit"));
						h.put("jenis_hakmilik", rs.getString("jenis_hakmilik")==null?"":rs.getString("jenis_hakmilik"));
						h.put("flag_borangl", rs.getString("flag_borangl")==null?"":rs.getString("flag_borangl"));
						
						listMaklumatTanah.addElement(h);
						bil++;	
					}
				//return list;
			}finally {
				if(db != null) db.close();
			}
			}//close setDataListKertas
		
		
		@SuppressWarnings("unchecked")
		public int setListMaklumatTanah_count(String idPermohonan,String lot,String idpegawai) throws Exception{
			
			listMaklumatTanah = new Vector();
			
			Db db = null;
			listMaklumatTanah.clear();
			String sql = "";
			String noLOT = lot.trim();
			String nama2Mukim = "";
			
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					
					
					
					sql = "SELECT count(*) as total FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, "; 
					sql += " Tblrujdaerah d, Users e";
					sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";  
					sql += " AND m.id_negeri = n.id_negeri "; 
					sql += " AND p.id_fail = f.id_fail "; 
					sql += " AND m.id_daerah = d.id_daerah(+)";
					sql += " AND ls.id_luas(+) = m.id_unitluaslot "; 
					sql += " AND m.id_pegawai = e.user_id(+)";
					sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
					sql += " AND m.id_lot = lt.id_lot(+) ";
					sql += " AND m.id_mukim = mk.id_mukim(+) ";  
					sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
					
					//NO LOT
					if (lot != "" && lot != null) {
						if (!noLOT.equals("")) {
							//sql += " AND UPPER(m.no_lot) OR UPPER(m.no_pt) LIKE '%" + noLOT.toUpperCase() + "%'";
							sql += " AND (UPPER(m.no_lot) LIKE '%" + noLOT.toUpperCase() + "%' OR  UPPER(m.no_pt) LIKE '%" + noLOT.toUpperCase() + "%' OR UPPER(lt.keterangan) LIKE '%" + noLOT.toUpperCase() + "%')";
						}
					}//close if nolot
					
					//ID PEGAWAI
					if (idpegawai != "" && idpegawai != null) {
							sql += " AND m.id_pegawai = '"+idpegawai+"'";
					}//close if idpegawai
					
					
					//sql += " ORDER BY m.no_lot asc, mk.nama_mukim asc";
					myLogger.info("SQL setListMaklumatTanah :: "+sql);
					ResultSet rs = stmt.executeQuery(sql);	
					Hashtable h;
					int total = 0;
					
					while (rs.next()){
						total = rs.getInt("total");
						
						
					}
				return total;
			}finally {
				if(db != null) db.close();
			}
			}//close setDataListKertas
		
		//view list dokumen by id
		 @SuppressWarnings("unchecked")
		public static void  setListDokumen(String id)throws Exception {
			    Db db = null;
			    listDokumen.clear();
			    String sql = "";
			    
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("a.id_permohonan");
			      r.add("a.id_Dokumen");
			      r.add("a.nama_Fail");
			      r.add("a.jenis_Mime");
			      r.add("a.tajuk");
			      r.add("a.keterangan");
			      r.add("a.content");
			      
			      r.add("p.id_permohonan",id);
			      r.add("p.id_permohonan",r.unquote("a.id_permohonan"));
			
			      sql = r.getSQLSelect("Tblpptdokumen a, tblpptpermohonan p");
			     
			      ResultSet rs = stmt.executeQuery(sql);
			     
			      Hashtable h;
			      int bil = 1;
			    
			      while (rs.next()) {
			    	  
			    	  h = new Hashtable();
			    	 
			    	  h.put("bil", bil);
			    	  h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
			    	  h.put("id_Dokumen", rs.getString("id_Dokumen")== null?"":rs.getString("id_Dokumen"));
			    	  h.put("nama_Fail", rs.getString("nama_Fail")== null?"":rs.getString("nama_Fail"));
			    	  h.put("jenis_Mime",rs.getString("jenis_Mime")== null?"":rs.getString("jenis_Mime"));
			    	  h.put("tajuk",rs.getString("tajuk")== null?"":rs.getString("tajuk"));
			    	  h.put("keterangan",rs.getString("keterangan")== null?"":rs.getString("keterangan"));
			          
			    	  listDokumen.addElement(h);
			    	  bil++;	    	
			      }			    
			      //return list;
			    } finally {
			      if (db != null) db.close();
			    }
			}

		 //--Get alamat Kementerian--//
			@SuppressWarnings("unchecked")
			public static Vector getAlamatKementerian(String id_kementerian)throws Exception {
				    
				Db db = null;
				String sql = "";
				  
				try {
				      	
						db = new Db();
						Statement stmt = db.getStatement();
				     
						sql = "SELECT k.alamat1, k.alamat2, k.alamat3, k.poskod, n.id_negeri ";
						sql +="FROM Tblrujkementerian k, Tblrujnegeri n ";
						sql +="WHERE k.id_negeri = n.id_negeri(+) ";
						sql +="AND k.id_kementerian = '"+id_kementerian+"' ";

				     
						ResultSet rs = stmt.executeQuery(sql);
						Vector list = new Vector();
				      
						Hashtable h = null;
				     
						while (rs.next()) {
							h = new Hashtable();
							h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
							h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
							h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
							h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
							h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
							list.addElement(h);
						}
				      return list;
				    } finally {
				      if (db != null) db.close();
				    }
				  }//find alamat kementerian		 

			@SuppressWarnings("unchecked")
			public static void setGetUserId(String userid) throws Exception {
				
				listGetUserId = new Vector();
				
				Db db = null;
				String sql = "";
				
				try {
						db = new Db();
						Statement stmt = db.getStatement();
					
						sql =  " SELECT DISTINCT B.ID_DAERAH,B.KOD_DAERAH,C.ID_NEGERI,A.ID_PEJABATJKPTG,C.KOD_MAMPU ";
						sql += " FROM USERS_INTERNAL A, TBLRUJDAERAH B, TBLRUJNEGERI C ";
						sql += " WHERE A.ID_NEGERI = C.ID_NEGERI(+) ";
						sql += " AND A.ID_DAERAH = B.ID_DAERAH(+) ";
						sql += " AND A.USER_ID = '"+userid+"'";
						
						ResultSet rs = stmt.executeQuery(sql);
						
						while (rs.next()) {
							Hashtable h = new Hashtable();
							h.put("idDaerah", rs.getString("ID_DAERAH")== null?"":rs.getString("ID_DAERAH"));
							h.put("kodDaerah", rs.getString("KOD_DAERAH")== null?"":rs.getString("KOD_DAERAH"));
							h.put("idNegeri", rs.getString("ID_NEGERI")== null?"":rs.getString("ID_NEGERI"));
							h.put("idpejabat", rs.getString("ID_PEJABATJKPTG")== null?"":rs.getString("ID_PEJABATJKPTG"));
							h.put("kodnegeri", rs.getString("KOD_MAMPU")== null?"":rs.getString("KOD_MAMPU"));
							listGetUserId.addElement(h);
					}
				} finally {
					if (db != null)
						db.close();
				}
			}

			@SuppressWarnings("unchecked")
			public static void setNamaPengarah(String userIdNeg) throws Exception {
				
				namaPengarah = new Vector();
				
				Db db = null;
				String sql = "";
				
				try {
						db = new Db();
						Statement stmt = db.getStatement();
					
						sql =  "SELECT DISTINCT A.USER_ID,A.USER_NAME ";
						sql += " FROM USERS A, USERS_INTERNAL B ";
						sql += " WHERE A.USER_ID = B.USER_ID ";
						sql += " AND B.ID_JAWATAN = '4' ";
						sql += " AND B.ID_NEGERI = '"+userIdNeg+"'";
						
						ResultSet rs = stmt.executeQuery(sql);
						
						while (rs.next()) {
							Hashtable h = new Hashtable();
							h.put("id_pengarah", rs.getString("USER_ID")== null?"":rs.getString("USER_ID"));
							h.put("nama_pengarah", rs.getString("USER_NAME")== null?"":rs.getString("USER_NAME"));
							namaPengarah.addElement(h);
							
					}
				} finally {
					if (db != null)
						db.close();
				}
			}//close setNamaPengarah			
			
}
