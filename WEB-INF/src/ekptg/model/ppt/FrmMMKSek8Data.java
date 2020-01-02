package ekptg.model.ppt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmMMKSek8Data {
	static Logger myLogger = Logger.getLogger(FrmMMKSek8Data.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	
	
	private static Vector listcarian = null;
	private static Vector dataMMK = null;
	private static Vector dataMMKKeputusan = null;
	
	public static Vector getListCarian(){
		return listcarian;
	}
	public static Vector getDataMMK(){
		return dataMMK;
	}
	public static Vector getDataMMKKeputusan(){
		return dataMMKKeputusan;
	}
	
	
	public static String sqlListPermohonan(String userIdNegeri)
	{

		String sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, " +
  			  " su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, mk.flag_semakan_pengarah, ";
  		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
  		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Tblpptmmk mk ";
  		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
  		sql +="AND p.id_status = s.id_status ";
  		sql +="AND mk.id_permohonan(+) = p.id_permohonan ";
  		sql +="AND f.id_suburusan = '52' ";
  		//sql += " AND p.id_permohonan IN ";
  		//sql +="AND p.id_status IN (22,43,147,132,133,134,26) ";
  				
  				sql += " and (p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx "; 
		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_pegawai is not null) ";      
		sql += " OR p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptagihanhm aghm " +
		   		   " where hx.id_permohonan = p.id_permohonan and aghm.id_hakmilik = hx.id_hakmilik and aghm.baris = '2' )";
		sql += " OR p.id_permohonan in (select distinct tx.id_permohonan from Tblppttanahumum tx ";
		sql += " where tx.id_permohonan = p.id_permohonan)";
		sql += " OR p.id_permohonan in (select distinct tx.id_permohonan from TblpptMMK tx ";
		sql += " where tx.id_permohonan = p.id_permohonan)";
		sql +=	 " ) ";
  				
  				
  		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
  			if(userIdNegeri.equals("14")){
  				sql += "AND f.id_negeri in (14,15,16) ";
  			}else{
  				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
  			}		
  		}
		
		
		
	
	return sql;
	}
	
	
	@SuppressWarnings("unchecked")
	public static Vector getListPermohonan(String userIdNegeri)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		/*
		    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, " +
		    			  " su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, mk.flag_semakan_pengarah, ";
		    		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Tblpptmmk mk ";
		    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		    		sql +="AND p.id_status = s.id_status ";
		    		sql +="AND mk.id_permohonan(+) = p.id_permohonan ";
		    		sql +="AND f.id_suburusan = '52' ";
		    		sql +="AND p.id_status IN (22,43,147,132,133,134,26) ";
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			if(userIdNegeri.equals("14")){
		    				sql += "AND f.id_negeri in (14,15,16) ";
		    			}else{
		    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    			}		
		    		}
		    		*/
		    		sql = sqlListPermohonan(userIdNegeri);
		    		sql +="ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
		    		myLogger.info("senarai mmk sql : "+sql);
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
		    			 
		    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
		    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
		    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
		    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
		    			h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
		    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
		    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
		    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
		    			if(rs.getString("no_fail") == null){
		    				h.put("no_fail","Belum Diluluskan");
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
		    	
	}//close getListPermohonan
	
	@SuppressWarnings("unchecked")
	public static void setListCarian(String carianNofail, String carianTarikh, String status, String userIdNegeri)throws Exception {
		
		listcarian = new Vector();
		
	    Db db = null;
	    listcarian.clear();
	    String sql = "";
	    
	    String cariTarikh = "";
	    if(carianTarikh!=""){
	    	cariTarikh = "to_date('" + carianTarikh + "','dd/MM/yyyy')";
	    }	    
	    
	    String nofail = carianNofail.trim();
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		/*
	    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, " +
  			          " su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, mk.flag_semakan_pengarah, ";
	    		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
	    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Tblpptmmk mk ";
	    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +="AND p.id_status = s.id_status ";
	    		sql +="AND mk.id_permohonan(+) = p.id_permohonan ";
	    		sql +="AND f.id_suburusan = '52' ";
	    		sql +="AND p.id_status IN (22,43,147,132,133,134,26) ";
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			if(userIdNegeri.equals("14")){
	    				sql += "AND f.id_negeri in (14,15,16) ";
	    			}else{
	    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    			}		
	    		}
	    		*/
	    		
	    		sql = sqlListPermohonan(userIdNegeri);
	    		
	    		//default flag
				boolean setLimit = true;  
				
	    		//NO FAIL
				if (carianNofail != "" && carianNofail != null) {
					if (!nofail.equals("")) {
						setLimit = false;
						//sql = sql + " AND UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%'";
						sql += " AND (UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_ptg) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_upt) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_ptd) LIKE '%" + nofail.toUpperCase() + "%')";
					}
				}//close carian by nofail
		
				//TARIKH
				if (carianTarikh != "" && carianTarikh != null) {
					if (!cariTarikh.equals("")) {
						setLimit = false;
						sql = sql + " AND UPPER(p.tarikh_permohonan) = "+cariTarikh;
					}
				}//close carian by tarikh
	
	    		//STATUS
				if (status != "" && status != null) {
					setLimit = false;
					sql = sql + " AND UPPER(s.id_status) = '"+status+"'";
				}//close carian by status

				if(setLimit){	
					sql += " AND ROWNUM <= 10 ";				
				}
				
	    		sql +=" ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
	      
	    		ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable h;
	    		int bil = 1;

	    		while (rs.next()) {
	    			h = new Hashtable();
	    			h.put("bil", bil);
	    			h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));
	    			h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
			    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
	    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
	    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
	    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
	    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
	    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
	    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
	    			h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
	    			
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
	    			listcarian.addElement(h);
	    			bil++;
	    			
	      }//close while
	      
	    }// 
	    finally {
	      if (db != null) db.close();
	    }
	    
	  }//close carian
	  
	@SuppressWarnings("unchecked")
	 public static void simpanPenyediaan(Hashtable data,long id_mmk) throws Exception
		  {
			
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");
		    		String id_permohonan = (String)data.get("id_permohonan");
		    		
//		    		String txtTujuan = (String)data.get("txtTujuan");
//		    		String txtLatarBelakang = (String)data.get("txtLatarBelakang");
//		    		String txtPerihalTanah = (String)data.get("txtPerihalTanah");
//		    		String txtNilaianTanah = (String)data.get("txtNilaianTanah");
//		    		String txtSyor = (String)data.get("txtSyor");
//		    		String txtUlasan = (String)data.get("txtUlasan");
//		    		String txtKeputusan = (String)data.get("txtKeputusan");		    	
//		    		String txtPerihalPohon = (String)data.get("txtPerihalPohon");
//		    		String txtPeruntukan = (String)data.get("txtPeruntukan");
//		    		String txtNamaTuanTanah = (String)data.get("txtNamaTuanTanah");
//		    		String txtPerakuan = (String)data.get("txtPerakuan");
//		    		String txtAsasPertimbangan = (String)data.get("txtAsasPertimbangan");		    		
//		    		String txtAnggaranKos = (String)data.get("txtAnggaranKos");
//		    		String txtUlasanJT = (String)data.get("txtUlasanJT");
//		    		String txtJawatankuasa = (String)data.get("txtJawatankuasa");
//		    		String txtPengecualian = (String)data.get("txtPengecualian");
//		    		String txtHalLain = (String)data.get("txtHalLain");
//		    		String txtImplikasi = (String)data.get("txtImplikasi");
//		    		String txtKesimpulan = (String)data.get("txtKesimpulan");
//		    		String txtPenutup = (String)data.get("txtPenutup");
//		    		String txtPandangan = (String)data.get("txtPandangan");
//		    		String txtPerakuanSetiausaha = (String)data.get("txtPerakuanSetiausaha");
//		    		String txtButirAsas = (String)data.get("txtButirAsas");
//		    		String txtKeadaanTanah = (String)data.get("txtKeadaanTanah");
//		    		String txtButirTanah = (String)data.get("txtButirTanah");
//		    		String txtKemudahanAsas = (String)data.get("txtKemudahanAsas");
		    	
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
		    		r.add("id_mmk",id_mmk);
		    		r.add("id_permohonan", id_permohonan);	
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
					
					
					
//		    		r.add("tujuan", txtTujuan);		
//		    		r.add("kemudahan_asas", txtKemudahanAsas);		
//		    		r.add("butir_tanah", txtButirTanah);		
//		    		r.add("butir_asas", txtButirAsas);	
//		    		r.add("keadaan_tanah", txtKeadaanTanah);		
//		    		r.add("perakuan_setiausaha", txtPerakuanSetiausaha);	
//		    		r.add("penutup",txtPenutup);
//		    		r.add("pandangan",txtPandangan);
//		    		r.add("latarbelakang", txtLatarBelakang);	
//		    		r.add("perihal_tanah", txtPerihalTanah);	
//		    		r.add("nilai_atas_tanah", txtNilaianTanah);	
//		    		r.add("syor", txtSyor);			    		
//		    		r.add("anggaran_kos", txtAnggaranKos);	
//		    		r.add("ulasan_jabteknikal", txtUlasanJT);	
//		    		r.add("jawatankuasa_tanah", txtJawatankuasa);			    		
//		    		r.add("ulasan_pengarah", txtUlasan);	
//		    		r.add("keputusan", txtKeputusan);			    		
//		    		r.add("perihal_pohon", txtPerihalPohon);
//		    		r.add("pengesahan_peruntukan", txtPeruntukan);
//		    		r.add("nama_tuan_tanah", txtNamaTuanTanah);
//		    		r.add("perakuan_pentadbir_tnh", txtPerakuan);
//		    		r.add("asas_pertimbangan", txtAsasPertimbangan);	
//		    		r.add("pengecualian_upah_ukur", txtPengecualian);	
//		    		r.add("hal_lain", txtHalLain);	
//		    		r.add("kesimpulan", txtKesimpulan);		
//		    		r.add("implikasi", txtImplikasi);		
		    		r.add("tarikh_masuk",r.unquote("sysdate"));
		    		r.add("id_masuk",id_user);    		
		    		sql = r.getSQLInsert("tblpptmmk");
		    		myLogger.info("ADD TBLPPTMMK :: "+sql);
		    		stmt.executeUpdate(sql);
	    	
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		}//close simpanPenyediaan
	
	
	public static void updateStatus(String idpermohonan,String idUser,String idstatus) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	   
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan",idpermohonan);
	    		r.add("id_status",idstatus);      			
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",idUser);    		
	    		sql = r.getSQLUpdate("tblpptpermohonan");
	    		stmt.executeUpdate(sql);
	    		    		
  	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateStatusPenyediaan
	
	//penambahan yati
 public static void hantarKeputusan(Hashtable data) throws Exception {
		 
		 Db db = null;
		 String sql = "";
		 
		 try{
		     
		      	String id_user = (String)data.get("id_user");
		      	String id_mmk = (String)data.get("id_mmk");
		     
		      	db = new Db();
		      	Statement stmt = db.getStatement();
		      	SQLRenderer r = new SQLRenderer();
		      	r.update("id_mmk", id_mmk);
		      	r.add("flag_semakan_pengarah", "2");			  
		      	r.add("id_kemaskini",id_user);
		      	r.add("tarikh_kemaskini",r.unquote("sysdate"));
		      	sql = r.getSQLUpdate("tblpptmmk");
		      	stmt.executeUpdate(sql);
		      
		    }
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close hantarKeputusan
	
	@SuppressWarnings("unchecked")
	public static void setDataMMK(String idpermohonan)throws Exception {
			
			dataMMK = new Vector();
			
		    Db db = null;
		    dataMMK.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT a.JENIS_WAKTU_SIDANG,a.WAKTU_SIDANG,a.TARIKH_SIDANG,a.TEMPAT_SIDANG,a.KETERANGAN_SIDANG,a.TAJUK," +
		    				" b.user_name,a.id_mmk,c.id_mmkkeputusan,a.id_permohonan,a.ulasan,a.no_rujmmk,a.tarikh_semak,a.tujuan, ";  
		    		sql += " a.latarbelakang,a.asas_pertimbangan,a.kesimpulan, a.syor,a.kedudukan_laporan_tnh,a.pengesahan_peruntukan, ";
		    		sql += " a.pandangan,a.implikasi,a.ulasan_jabteknikal, a.perihal_tanah,a.perihal_pohon,a.anggaran_kos, a.kemudahan_asas, ";
		    		sql += " a.perakuan_pentadbir_tnh,a.nilai_atas_tanah, a.pengecualian_upah_ukur,a.tarikh_mmk,a.status_semakan, a.butir_tanah, ";
		    		sql += " a.ulasan_pengarah,a.keputusan,a.nama_tuan_tanah,  a.jawatankuasa_tanah, a.hal_lain, a.butir_asas, a.keadaan_tanah, ";
		    		sql += " c.tarikh_terima,c.tarikh_keputusan,a.tarikh_hantar,c.status_keputusan,a.flag_borangi,a.penutup,a.perakuan_setiausaha, ";
		    		sql += " a.flag_semakan_pengarah, c.no_perserahan, a.jenis_penggunaan_tnh, a.lokasi_tanah, a.kedudukan_tanah, a.user_id ";
					sql += " FROM tblpptmmk a, users b, tblpptmmkkeputusan c "; 
					sql += " WHERE a.user_id = b.user_id(+) "; 
					sql += " AND c.id_mmk(+) = a.id_mmk ";
					sql += " AND a.id_permohonan = '"+idpermohonan+"'";
		    		
					//QRSLOW
					myLogger.info("dataMMK : "+sql);
					ResultSet rs = stmt.executeQuery(sql);
		      
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
						
						
						
		    			h.put("user_id", rs.getString("user_id")== null?"":rs.getString("user_id"));
		    			h.put("jenis_penggunaan_tnh", rs.getString("jenis_penggunaan_tnh")== null?"":rs.getString("jenis_penggunaan_tnh"));
		    			h.put("lokasi_tanah", rs.getString("lokasi_tanah")== null?"":rs.getString("lokasi_tanah"));
		    			h.put("kedudukan_tanah", rs.getString("kedudukan_tanah")== null?"":rs.getString("kedudukan_tanah"));
		    			h.put("id_mmk", rs.getString("id_mmk")== null?"":rs.getString("id_mmk"));
		    			h.put("no_perserahan", rs.getString("no_perserahan")== null?"":rs.getString("no_perserahan"));
		    			h.put("kemudahan_asas", rs.getString("kemudahan_asas")== null?"":rs.getString("kemudahan_asas"));
		    			h.put("flag_semakan_pengarah", rs.getString("flag_semakan_pengarah")== null?"":rs.getString("flag_semakan_pengarah"));
		    			h.put("hal_lain", rs.getString("hal_lain")== null?"":rs.getString("hal_lain"));
		    			h.put("butir_tanah", rs.getString("butir_tanah")== null?"":rs.getString("butir_tanah"));
		    			h.put("keadaan_tanah", rs.getString("keadaan_tanah")== null?"":rs.getString("keadaan_tanah"));
		    			h.put("butir_asas", rs.getString("butir_asas")== null?"":rs.getString("butir_asas"));
		    			h.put("perakuan_setiausaha", rs.getString("perakuan_setiausaha")== null?"":rs.getString("perakuan_setiausaha"));
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
		    			h.put("tarikh_terima", rs.getDate("tarikh_terima")==null?"":Format.format(rs.getDate("tarikh_terima")));
		    			h.put("tarikh_keputusan", rs.getDate("tarikh_keputusan")==null?"":Format.format(rs.getDate("tarikh_keputusan")));
		    			h.put("tarikh_hantar", rs.getDate("tarikh_hantar")==null?"":Format.format(rs.getDate("tarikh_hantar")));
		    			h.put("flag_borangi", rs.getString("flag_borangi")== null?"":rs.getString("flag_borangi"));
		    			h.put("penutup", rs.getString("penutup")== null?"":rs.getString("penutup"));
		    			
		    			//keputusan mmk
		    			if(rs.getString("status_keputusan") != null){
		    				
		    				if(rs.getString("status_keputusan").equals("1")){
		    					h.put("keputusan_mmk","SETUJU");
		    				}else if(rs.getString("status_keputusan").equals("2")){
		    					h.put("keputusan_mmk","TIDAK SETUJU");
		    				}else{
		    					h.put("keputusan_mmk","");
		    				}
		    				
		    			}else{
		    				h.put("keputusan_mmk","");
		    			}
		    			
		    			//keputusan semakan
		    			if(rs.getString("status_semakan") != null){
		    				
		    				if(rs.getString("status_semakan").equals("1")){
		    					h.put("keputusan_semakan","TELAH DISEMAK");
		    				}else if(rs.getString("status_semakan").equals("2")){
		    					h.put("keputusan_semakan","BELUM DISEMAK");
		    				}else{
		    					h.put("keputusan_semakan","");
		    				}
		    				
		    			}else{
		    				h.put("keputusan_mmk","");
		    			}
		    			
		    			dataMMK.addElement(h);
		    		
		      }//close while
		      
		    }// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close setDataMMK
	
	@SuppressWarnings("unchecked")
	public static void setDataMMKKeputusan(String idmmk)throws Exception {
			
			dataMMKKeputusan = new Vector();
			
		    Db db = null;
		    dataMMKKeputusan.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT a.id_mmkkeputusan,a.id_mmk,a.tarikh_terima,a.tarikh_keputusan,a.status_keputusan, ";
		    		sql += " a.ulasan,a.tarikh_hantar,b.no_rujmmk,b.tarikh_mmk, a.no_perserahan ";
		    		sql += " FROM tblpptmmkkeputusan a, tblpptmmk b ";
		    		sql += " WHERE a.id_mmk = b.id_mmk(+) ";
		    		sql += " AND a.id_mmk = '"+idmmk+"'";
		    		
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    		
		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("no_perserahan", rs.getString("no_perserahan")== null?"":rs.getString("no_perserahan"));
		    			h.put("id_mmkkeputusan", rs.getString("id_mmkkeputusan")== null?"":rs.getString("id_mmkkeputusan"));
		    			h.put("status_keputusan", rs.getString("status_keputusan")== null?"":rs.getString("status_keputusan"));
		    			h.put("ulasan", rs.getString("ulasan")== null?"":rs.getString("ulasan"));
		    			h.put("tarikh_terima", rs.getDate("tarikh_terima")==null?"":Format.format(rs.getDate("tarikh_terima")));
		    			h.put("tarikh_keputusan", rs.getDate("tarikh_keputusan")==null?"":Format.format(rs.getDate("tarikh_keputusan")));
		    			h.put("tarikh_hantar", rs.getDate("tarikh_hantar")==null?"":Format.format(rs.getDate("tarikh_hantar")));
		    			h.put("tarikh_mmk", rs.getDate("tarikh_mmk")==null?"":Format.format(rs.getDate("tarikh_mmk")));
		    			h.put("no_rujmmk", rs.getString("no_rujmmk")== null?"":rs.getString("no_rujmmk"));
		    			dataMMKKeputusan.addElement(h);
		    		
		      }//close while
		      
		    }// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close setDataMMKKeputusan
	
	@SuppressWarnings("unchecked")
	public static void updatePenyediaan(Hashtable data) throws Exception
		  {
			System.out.println("DATA--"+data);
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");
		    		String id_mmk = (String)data.get("id_mmk");
		    		
		    		String txtJenisPenggunaan = (String)data.get("txtJenisPenggunaan");
		    		String txtLokasi = (String)data.get("txtLokasi");
		    		String txtKedudukan = (String)data.get("txtKedudukan");
		    		String txtKeadaan = (String)data.get("txtKeadaan");
		    		
//		    		String txtTujuan = (String)data.get("txtTujuan");
//		    		String txtLatarBelakang = (String)data.get("txtLatarBelakang");
//		    		String txtPerihalTanah = (String)data.get("txtPerihalTanah");
//		    		String txtNilaianTanah = (String)data.get("txtNilaianTanah");
//		    		String txtSyor = (String)data.get("txtSyor");
//		    		String txtUlasan = (String)data.get("txtUlasan");
//		    		String txtKeputusan = (String)data.get("txtKeputusan");		    	
//		    		String txtPerihalPohon = (String)data.get("txtPerihalPohon");
//		    		String txtPeruntukan = (String)data.get("txtPeruntukan");
//		    		String txtNamaTuanTanah = (String)data.get("txtNamaTuanTanah");
//		    		String txtPerakuan = (String)data.get("txtPerakuan");
//		    		String txtAsasPertimbangan = (String)data.get("txtAsasPertimbangan");		    		
//		    		String txtAnggaranKos = (String)data.get("txtAnggaranKos");
//		    		String txtUlasanJT = (String)data.get("txtUlasanJT");
//		    		String txtJawatankuasa = (String)data.get("txtJawatankuasa");
//		    		String txtPengecualian = (String)data.get("txtPengecualian");
//		    		String txtHalLain = (String)data.get("txtHalLain");
//		    		String txtImplikasi = (String)data.get("txtImplikasi");
//		    		String txtKesimpulan = (String)data.get("txtKesimpulan");
//		    		String txtPenutup = (String)data.get("txtPenutup");
//		    		String txtPandangan = (String)data.get("txtPandangan");
//		    		String txtPerakuanSetiausaha = (String)data.get("txtPerakuanSetiausaha");
//		    		String txtButirAsas = (String)data.get("txtButirAsas");
//		    		String txtKeadaanTanah = (String)data.get("txtKeadaanTanah");
//		    		String txtButirTanah = (String)data.get("txtButirTanah");
//		    		String txtKemudahanAsas = (String)data.get("txtKemudahanAsas");
		    		
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
		    		
		    		
//		    		r.add("tujuan", txtTujuan);	
//		    		r.add("kemudahan_asas", txtKemudahanAsas);		
//		    		r.add("butir_tanah", txtButirTanah);		
//		    		r.add("keadaan_tanah", txtKeadaanTanah);
//		    		r.add("butir_asas", txtButirAsas);	
//		    		r.add("perakuan_setiausaha", txtPerakuanSetiausaha);	
//		    		r.add("penutup", txtPenutup);		  
//		    		r.add("pandangan", txtPandangan);	
//		    		r.add("latarbelakang", txtLatarBelakang);	
//		    		r.add("perihal_tanah", txtPerihalTanah);	
//		    		r.add("nilai_atas_tanah", txtNilaianTanah);	
//		    		r.add("syor", txtSyor);			    		
//		    		r.add("anggaran_kos", txtAnggaranKos);	
//		    		r.add("ulasan_jabteknikal", txtUlasanJT);	
//		    		r.add("jawatankuasa_tanah", txtJawatankuasa);			    		
//		    		r.add("ulasan_pengarah", txtUlasan);	
//		    		r.add("keputusan", txtKeputusan);			    		
//		    		r.add("perihal_pohon", txtPerihalPohon);
//		    		r.add("pengesahan_peruntukan", txtPeruntukan);
//		    		r.add("nama_tuan_tanah", txtNamaTuanTanah);
//		    		r.add("perakuan_pentadbir_tnh", txtPerakuan);
//		    		r.add("asas_pertimbangan", txtAsasPertimbangan);	
//		    		r.add("pengecualian_upah_ukur", txtPengecualian);	
//		    		r.add("hal_lain", txtHalLain);	
//		    		r.add("kesimpulan", txtKesimpulan);		
//		    		r.add("implikasi", txtImplikasi);		
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",id_user);    		
		    		sql = r.getSQLUpdate("tblpptmmk");
		    		stmt.executeUpdate(sql);
	    	
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		}//close updatePenyediaan
	
	@SuppressWarnings("unchecked") //penambahanbaikan yati
	public static void updatePenyediaanSementara(Hashtable data) throws Exception
		  {
			System.out.println("DATA--"+data);
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");
		    		String id_mmk = (String)data.get("id_mmk");
		    	
		    		String txtTajuk = (String) data.get("txtTajuk");
		    		String txtTujuan = (String) data.get("txtTujuan");
		    		String txtLatarBelakang = (String) data.get("txtLatarBelakang");
		    		String txtPerihalTanah = (String) data.get("txtPerihalTanah");
		    		String txtNilaianTanah = (String) data.get("txtNilaianTanah");
		    		String txtPeruntukan = (String) data.get("txtPengesahanPeruntukan");
		    		String txtSyor = (String)data.get("txtSyor");
		    		String txtSidang = (String) data.get("txtSidang");
		    		String txtTempatSidang = (String) data.get("txtTempatSidang");
		    		String txtTarikhSidang = (String) data.get("txtTarikhSidang");
		    		String txtMasaSidang = (String) data.get("txtMasaSidang");
		    		String jeniswaktu = (String) data.get("jeniswaktu");
		    		
		    		
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_mmk", id_mmk);	
		    		
		    		r.add("TAJUK", txtTajuk);
		    		r.add("LATARBELAKANG", txtLatarBelakang);
		    		r.add("TUJUAN", txtTujuan);
		    		r.add("PERIHAL_TANAH", txtPerihalTanah);
		    		r.add("NILAI_ATAS_TANAH", txtNilaianTanah);	
		    		r.add("PENGESAHAN_PERUNTUKAN", txtPeruntukan);
		    		r.add("SYOR", txtSyor);	
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
		    		

		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",id_user);    		
		    		sql = r.getSQLUpdate("tblpptmmk");
		    		stmt.executeUpdate(sql);
	    	
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		}//close updatePenyediaanSementara
	
	@SuppressWarnings("unchecked")
	public static void updateSemakan(Hashtable data) throws Exception
		  {
			
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");
		    		String id_mmk = (String)data.get("id_mmk");
		    		String role = (String)data.get("role");
		    		 
		    		String txdTarikhSemakan = (String)data.get("txdTarikhSemakan");
		    		String socKeputusanSemak = (String)data.get("socKeputusanSemak");
		    		String txtUlasanSemak = (String)data.get("txtUlasanSemak");
		    		String txdTarikhHantar = (String)data.get("txdTarikhHantar");
		    		
		    		String TS = "to_date('" + txdTarikhSemakan + "','dd/MM/yyyy')";
		    		String TH = "to_date('" + txdTarikhHantar + "','dd/MM/yyyy')";
		    		
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_mmk", id_mmk);		
		    		
		    		if(role.equals("(PPT)KetuaPenolongPengarahUnit") || role.equals("(PPT)PengarahUnit") ||role.equals("adminppt") ){
		    			r.add("user_id", id_user);		    	
		    			r.add("status_semakan", socKeputusanSemak);	
		    			r.add("flag_semakan_pengarah", "2");
		    		}
		    		
		    		r.add("ulasan", txtUlasanSemak);	
		    		r.add("tarikh_semak", r.unquote(TS));	
		    		r.add("tarikh_hantar", r.unquote(TH));
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",id_user);    		
		    		sql = r.getSQLUpdate("tblpptmmk");
		    		
		    		myLogger.info("SQL SIMPAN SEMAK MMK :"+sql);
		    		stmt.executeUpdate(sql);
	    	
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		}//close updateSemakan
	
	@SuppressWarnings("unchecked")
	public static void simpanKeputusan(Hashtable data) throws Exception
		  {
		
			Connection conn = null;
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		conn = db.getConnection();
			    	conn.setAutoCommit(false);
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");
		    		String id_mmk = (String)data.get("id_mmk");
		    		
		    		String txtNoRujukan = (String)data.get("txtNoRujukan");
		    		String txdTarikhMMK = (String)data.get("txdTarikhMMK");
		    		//String txdTarikhHantar = (String)data.get("txdTarikhHantar");
		    		//String txdTarikhKeputusan = (String)data.get("txdTarikhKeputusan");
		    		String txdTarikhTerimaKeputusan = (String)data.get("txdTarikhTerimaKeputusan");
		    		String sorKeputusan = (String)data.get("sorKeputusan");
		    		String txtUlasanKeputusan = (String)data.get("txtUlasanKeputusan");
		    		String txtNoPerserahan = (String)data.get("txtNoPerserahan");
		    		
		    		String TMMK = "to_date('" + txdTarikhMMK + "','dd/MM/yyyy')";
		    		//String TH = "to_date('" + txdTarikhHantar + "','dd/MM/yyyy')";
		    		//String TK = "to_date('" + txdTarikhKeputusan + "','dd/MM/yyyy')";
		    		String TTK = "to_date('" + txdTarikhTerimaKeputusan + "','dd/MM/yyyy')";
		    		
		    		//Update tblpptmmk
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_mmk", id_mmk);	
		    		r.add("no_rujmmk",txtNoRujukan);    
		    		r.add("tarikh_mmk",r.unquote(TMMK));   
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",id_user);    		
		    		sql = r.getSQLUpdate("tblpptmmk");
		    		stmt.executeUpdate(sql);
		    		
		    		//Insert tblpptmmkkeputusan
		    		SQLRenderer r1 = new SQLRenderer();
		    		r1.add("id_mmk", id_mmk);	
		    		//r1.add("tarikh_hantar",r1.unquote(TH)); 
		    		//r1.add("tarikh_keputusan",r1.unquote(TK)); 
		    		r1.add("tarikh_terima",r1.unquote(TTK)); 
		    		r1.add("status_keputusan",sorKeputusan);	
		    		r1.add("ulasan",txtUlasanKeputusan);	
		    		r1.add("no_perserahan", txtNoPerserahan);
		    		r1.add("tarikh_masuk",r1.unquote("sysdate"));
		    		r1.add("id_masuk",id_user);    		
		    		sql = r1.getSQLInsert("tblpptmmkkeputusan");
		    		stmt.executeUpdate(sql);
	    	
		    		conn.commit();	
				      
		    }catch (SQLException se) { 
		    	try {
		    		conn.rollback();
		    	}catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat : Masalah penyimpanan data ");
		    }
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		}//close simpanKeputusan
	
	
	@SuppressWarnings("unchecked")
	public static void updateKeputusan(Hashtable data) throws Exception
		  {
		
			Connection conn = null;
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		conn = db.getConnection();
			    	conn.setAutoCommit(false);
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");
		    		String id_mmkkeputusan = (String)data.get("id_mmkkeputusan");
		    		String id_mmk = (String)data.get("id_mmk");
		    		
		    		String txtNoRujukan = (String)data.get("txtNoRujukan");
		    		String txdTarikhMMK = (String)data.get("txdTarikhMMK");
		    		//String txdTarikhHantar = (String)data.get("txdTarikhHantar");
		    		//String txdTarikhKeputusan = (String)data.get("txdTarikhKeputusan");
		    		String txdTarikhTerimaKeputusan = (String)data.get("txdTarikhTerimaKeputusan");
		    		String sorKeputusan = (String)data.get("sorKeputusan");
		    		String txtUlasanKeputusan = (String)data.get("txtUlasanKeputusan");
		    		String txtNoPerserahan = (String)data.get("txtNoPerserahan");
		    		
		    		String TMMK = "to_date('" + txdTarikhMMK + "','dd/MM/yyyy')";
		    		//String TH = "to_date('" + txdTarikhHantar + "','dd/MM/yyyy')";
		    		//String TK = "to_date('" + txdTarikhKeputusan + "','dd/MM/yyyy')";
		    		String TTK = "to_date('" + txdTarikhTerimaKeputusan + "','dd/MM/yyyy')";
		    		
		    		//Update tblpptmmk
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_mmk", id_mmk);	
		    		r.add("no_rujmmk",txtNoRujukan);    
		    		r.add("tarikh_mmk",r.unquote(TMMK));   
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",id_user);    		
		    		sql = r.getSQLUpdate("tblpptmmk");
		    		stmt.executeUpdate(sql);
		    		
		    		//Insert tblpptmmkkeputusan
		    		SQLRenderer r1 = new SQLRenderer();
		    		r1.update("id_mmkkeputusan", id_mmkkeputusan);	
		    		//r1.add("tarikh_hantar",r1.unquote(TH)); 
		    		//r1.add("tarikh_keputusan",r1.unquote(TK)); 
		    		r1.add("tarikh_terima",r1.unquote(TTK)); 
		    		r1.add("status_keputusan",sorKeputusan);	
		    		r1.add("ulasan",txtUlasanKeputusan);	
		    		r1.add("no_perserahan",txtNoPerserahan);	
		    		r1.add("tarikh_kemaskini",r1.unquote("sysdate"));
		    		r1.add("id_kemaskini",id_user);    		
		    		sql = r1.getSQLUpdate("tblpptmmkkeputusan");
		    		stmt.executeUpdate(sql);
	    	
		    		conn.commit();	
				      
		    }catch (SQLException se) { 
		    	try {
		    		conn.rollback();
		    	}catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat : Masalah penyimpanan data ");
		    }
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		}//close updateKeputusan
	
}//close class
