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

public class FrmSek8JabatanTeknikalData {
	static Logger myLogger = Logger.getLogger(FrmSek8JabatanTeknikalData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static  Vector listcarian = null;
	private static  Vector listJabatan = null;
	private static  Vector dataJabatan = null;
	
	public static  Vector getListCarian(){
		return listcarian;
	}
	public static  Vector getListJabatan(){
		return listJabatan;
	}
	public static  Vector getDataJabatan(){
		return dataJabatan;
	}
	
	public static String sqlListPermohonan(String userIdNegeri)
	{
	String sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
	sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
	sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	sql +="AND p.id_status = s.id_status ";
	sql +="AND f.id_suburusan = '52' ";
	//sql +="AND p.id_status IN (147,22,43)";
	//sql +="AND p.id_status IN (148,22)";
	
	sql += " and (p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx "; 
	sql += " where hx.id_permohonan = p.id_permohonan and hx.id_pegawai is not null) ";      
	sql += " OR p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptagihanhm aghm " +
	   		   " where hx.id_permohonan = p.id_permohonan and aghm.id_hakmilik = hx.id_hakmilik and aghm.baris = '2' )";
	sql += " OR p.id_permohonan in  (select distinct hx.id_permohonan from Tblpptulasanteknikal hx "; 
	sql += " where hx.id_jabatanteknikal is not null and hx.id_permohonan = p.id_permohonan)";
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
		    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		    		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		    		sql +="AND p.id_status = s.id_status ";
		    		sql +="AND f.id_suburusan = '52' ";
		    		//sql +="AND p.id_status IN (147,22,43)";
		    		sql +="AND p.id_status IN (148,22)";
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
	public static void setListCarian(String carianNofail, String carianTarikh, String status,String userIdNegeri)throws Exception {
		
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
	    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, p.no_rujukan_surat, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
	    		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
	    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +="AND p.id_status = s.id_status ";
	    		sql +="AND f.id_suburusan = '52' ";
	    		sql +="AND p.id_status IN (147,22,43)";
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
	public static void simpanJabatan(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		long id_ulasanteknikal = DB.getNextID("TBLPPTULASANTEKNIKAL_SEQ");
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    	
	    		String socJabatan = (String)data.get("socJabatan");
	    		String txtBilSurat = (String)data.get("txtBilSurat");
	    		String txdTarikhSurat = (String)data.get("txdTarikhSurat");
	    		String txtTempoh = (String)data.get("txtTempoh");
	    		String txtPerihal = (String)data.get("txtPerihal");
	    		String txtBilSuratJT = (String)data.get("txtBilSuratJT");
	    		
	    		String txtNoRujukan = (String)data.get("txtNoRujukan");	 
	    		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
	    		String txdTarikhSuratJT = (String)data.get("txdTarikhSuratJT");
	    		String socKeputusan = (String)data.get("socKeputusan");
	    		String txtUlasan = (String)data.get("txtUlasan");	
	    	
	    		String TS = "to_date('" + txdTarikhSurat + "','dd/MM/yyyy')";
	    		String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
	    		String TSJT = "to_date('" + txdTarikhSuratJT + "','dd/MM/yyyy')";
	    	
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_ulasanteknikal",id_ulasanteknikal);
	    		r.add("id_permohonan", id_permohonan);
	    		r.add("id_jabatanteknikal", socJabatan); 	
	    		r.add("bil_surat", txtBilSurat);
	    		r.add("tarikh_surat", r.unquote(TS));
	    		r.add("tempoh", txtTempoh);
	    		r.add("perihal", txtPerihal);
	    		r.add("bil_surat_jt", txtBilSuratJT);
	    		r.add("no_rujukansuratjt", txtNoRujukan);
	    		r.add("tarikh_terimajt", r.unquote(TT));
	    		r.add("tarikh_suratJT", r.unquote(TSJT));
	    		r.add("keputusanjt",socKeputusan);
	    		r.add("ulasanjt",txtUlasan);	    			    			
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("tblpptulasanteknikal");
	    		stmt.executeUpdate(sql);
	    		
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanJabatan
	
	
	@SuppressWarnings("unchecked")
	public static void updateJabatan(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_ulasanteknikal = (String)data.get("id_ulasanteknikal");
	    	
	    		String socJabatan = (String)data.get("socJabatan");
	    		String txtBilSurat = (String)data.get("txtBilSurat");
	    		String txdTarikhSurat = (String)data.get("txdTarikhSurat");
	    		String txtTempoh = (String)data.get("txtTempoh");
	    		String txtPerihal = (String)data.get("txtPerihal");
	    		String txtBilSuratJT = (String)data.get("txtBilSuratJT");
	    		
	    		String txtNoRujukan = (String)data.get("txtNoRujukan");	 
	    		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
	    		String txdTarikhSuratJT = (String)data.get("txdTarikhSuratJT");
	    		String socKeputusan = (String)data.get("socKeputusan");
	    		String txtUlasan = (String)data.get("txtUlasan");	
	    	
	    		String TS = "to_date('" + txdTarikhSurat + "','dd/MM/yyyy')";
	    		String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
	    		String TSJT = "to_date('" + txdTarikhSuratJT + "','dd/MM/yyyy')";
	    	
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_ulasanteknikal",id_ulasanteknikal);
	    		r.add("id_jabatanteknikal", socJabatan); 	
	    		r.add("bil_surat", txtBilSurat);
	    		r.add("tarikh_surat", r.unquote(TS));
	    		r.add("tempoh", txtTempoh);
	    		r.add("perihal", txtPerihal);
	    		r.add("bil_surat_jt", txtBilSuratJT);
	    		r.add("no_rujukansuratjt", txtNoRujukan);
	    		r.add("tarikh_terimajt", r.unquote(TT));
	    		r.add("tarikh_suratJT", r.unquote(TSJT));
	    		r.add("keputusanjt",socKeputusan);
	    		r.add("ulasanjt",txtUlasan);	    			    			
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblpptulasanteknikal");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateJabatan
	
	@SuppressWarnings("unchecked") //tambah query-merge table lama dan baru tblrujpejabat
	public static void setListJabatan(String id_permohonan,String namaPej,String userIdNegeri)throws Exception {
			
		listJabatan = new Vector();
			
		    Db db = null;
		    listJabatan.clear();
		    String sql = "";
		    
		    String namaPejabat = namaPej.trim();
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT a.id_ulasanteknikal,a.id_jabatanteknikal,b.nama_jabatan as nama_jabatan,a.perihal,a.ulasanjt,a.bil_surat,a.tarikh_surat,a.tempoh ";
		    		sql += " FROM tblpptulasanteknikal a, tblpptjabatanteknikal b ";
		    		sql += " WHERE a.id_jabatanteknikal = b.id_jabatanteknikal ";
		    		sql += " AND NVL(a.FLAG_JENIS_ULASAN,0) NOT IN (1,2) ";
		    		sql += " AND id_permohonan = '"+id_permohonan+"'";
		    		//negeri
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			if(userIdNegeri.equals("14")){
		    				sql += "OR b.id_negeri in (14,15,16) ";
		    			}else{
		    				sql += "OR b.id_negeri ='"+userIdNegeri+"'";
		    			}		
		    		}
		      
		    		sql += " UNION " ;
		    		
		    		sql += " SELECT DISTINCT a.id_ulasanteknikal, a.id_jabatanteknikal, c.nama_pejabat as nama_jabatan," +
		    				" a.perihal, a.ulasanjt, a.bil_surat, a.tarikh_surat, a.tempoh " +
		    				" FROM tblpptulasanteknikal a, tblrujpejabat c " +
		    				" WHERE a.id_jabatanteknikal = c.id_pejabat " +
		    				" AND NVL (a.flag_jenis_ulasan, 0) NOT IN (1, 2) " +
		    				" AND id_permohonan = '"+id_permohonan+"'";
		    		
		    		//negeri
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			if(userIdNegeri.equals("14")){
		    				sql += "AND c.id_negeri in (14,15,16) ";
		    			}else{
		    				sql += "AND c.id_negeri in ('16','"+userIdNegeri+"')";
		    			}		
		    		}
		    		//NAMA PEJABAT
					if (namaPej != "" && namaPej != null) {
						if (!namaPejabat.equals("")) {
							sql += " AND UPPER(nama_jabatan) LIKE '%" + namaPejabat.toUpperCase() + "%'";
						}
					}//close if nolot
					
					sql += " ORDER BY nama_jabatan asc";
					
		    		ResultSet rs = stmt.executeQuery(sql);
		    		myLogger.info("SQL LIST JABATAN TEKNIKAL : "+sql);
		    		Hashtable h;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);		    			
		    			h.put("id_ulasanteknikal", rs.getString("id_ulasanteknikal")== null?"":rs.getString("id_ulasanteknikal"));
		    			h.put("nama_jabatan", rs.getString("nama_jabatan")== null?"":rs.getString("nama_jabatan"));
		    			h.put("perihal", rs.getString("perihal")== null?"":rs.getString("perihal"));
		    			h.put("ulasanjt", rs.getString("ulasanjt")== null?"":rs.getString("ulasanjt"));
		    			h.put("bil_surat", rs.getString("bil_surat")== null?"":rs.getString("bil_surat"));
		    			h.put("tempoh", rs.getString("tempoh")== null?"":rs.getString("tempoh"));		    			
		    			h.put("tarikh_surat", rs.getDate("tarikh_surat")==null?"":Format.format(rs.getDate("tarikh_surat")));
		    			listJabatan.addElement(h);
		    			bil++;
		    			
		    		
		  		     
		      }//close while
		   
		    }// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close listJabatan
	
	@SuppressWarnings("unchecked")
	public static void setDataJabatan(String id_ulasanteknikal)throws Exception {
			
		dataJabatan = new Vector();
			
		    Db db = null;
		    dataJabatan.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT a.id_ulasanteknikal,a.id_jabatanteknikal,a.id_jabatanteknikal as id_pejabat,b.nama_jabatan as nama_jabatan,a.perihal,a.bil_surat,a.tarikh_surat,a.tempoh, ";
		    		sql += " a.bil_surat_jt,a.no_rujukansuratjt,a.tarikh_terimajt,a.tarikh_suratjt,a.keputusanjt,a.ulasanjt ";
		    		sql += " FROM tblpptulasanteknikal a, tblpptjabatanteknikal b ";
		    		sql += " WHERE a.id_jabatanteknikal = b.id_jabatanteknikal ";
		    		sql += " AND a.id_ulasanteknikal = '"+id_ulasanteknikal+"'";
		    
		    		sql += " UNION " ;
		    		sql += " SELECT DISTINCT a.id_ulasanteknikal,a.id_jabatanteknikal,c.id_pejabat as id_pejabat,c.nama_pejabat as nama_jabatan,a.perihal,a.bil_surat,a.tarikh_surat,a.tempoh, ";
		    		sql += " a.bil_surat_jt,a.no_rujukansuratjt,a.tarikh_terimajt,a.tarikh_suratjt,a.keputusanjt,a.ulasanjt ";
		    		sql += " FROM tblpptulasanteknikal a, tblrujpejabat c ";
		    		sql += " WHERE a.id_jabatanteknikal = c.id_pejabat ";
		    		sql += " AND a.id_ulasanteknikal = '"+id_ulasanteknikal+"'";
		    		
		    		ResultSet rs = stmt.executeQuery(sql);
		    
		    		Hashtable h;
		    		
		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("id_ulasanteknikal", rs.getString("id_ulasanteknikal")== null?"":rs.getString("id_ulasanteknikal"));
		    			h.put("id_jabatanteknikal", rs.getString("id_jabatanteknikal")== null?"":rs.getString("id_jabatanteknikal"));
		    			h.put("id_pejabat", rs.getString("id_pejabat")== null?"":rs.getString("id_pejabat"));
		    			h.put("nama_jabatan", rs.getString("nama_jabatan")== null?"":rs.getString("nama_jabatan"));
		    			h.put("perihal", rs.getString("perihal")== null?"":rs.getString("perihal"));
		    			h.put("bil_surat", rs.getString("bil_surat")== null?"":rs.getString("bil_surat"));
		    			h.put("tempoh", rs.getString("tempoh")== null?"":rs.getString("tempoh"));		    			
		    			h.put("tarikh_surat", rs.getDate("tarikh_surat")==null?"":Format.format(rs.getDate("tarikh_surat")));
		    			h.put("tarikh_terimajt", rs.getDate("tarikh_terimajt")==null?"":Format.format(rs.getDate("tarikh_terimajt")));
		    			h.put("tarikh_suratjt", rs.getDate("tarikh_suratjt")==null?"":Format.format(rs.getDate("tarikh_suratjt")));		    			
		    			h.put("bil_surat_jt", rs.getString("bil_surat_jt")== null?"":rs.getString("bil_surat_jt"));
		    			h.put("no_rujukansuratjt", rs.getString("no_rujukansuratjt")== null?"":rs.getString("no_rujukansuratjt"));
		    			h.put("keputusanjt", rs.getString("keputusanjt")== null?"":rs.getString("keputusanjt"));
		    			h.put("ulasanjt", rs.getString("ulasanjt")== null?"":rs.getString("ulasanjt"));

		    		
		    			dataJabatan.addElement(h);
		    			
		      }//close while
		      
		    }// 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close listJabatan
	
	public static void hapusJabatan(String id_ulasanteknikal) throws Exception{
		
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	  
	    		sql = "DELETE FROM tblpptulasanteknikal where id_ulasanteknikal = '"+id_ulasanteknikal+"'";
	    		stmt.executeUpdate(sql);
  	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close hapusHM
	

	public static void seterusnya(String idpermohonan,String idUser) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan",idpermohonan);
	    		r.add("id_status","22");      			
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",idUser);    		
	    		sql = r.getSQLUpdate("tblpptpermohonan");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close seterusnya
	
	
	@SuppressWarnings("unchecked")
	public static void updateStatus(String user_id,String idpermohonan,String status) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		//status jabatan teknikal
	    		//String status = "22";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan", idpermohonan);	   
	    		r.add("id_status",status);  
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",user_id);    		
	    		sql = r.getSQLUpdate("tblpptpermohonan");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close updateStatus
	
}//close class
