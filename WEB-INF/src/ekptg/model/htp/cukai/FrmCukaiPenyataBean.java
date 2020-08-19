package ekptg.model.htp.cukai;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import lebah.util.Util;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.entities.Agensi;
import ekptg.model.entities.Daerah;
import ekptg.model.entities.Kementerian;
import ekptg.model.entities.Mukim;
import ekptg.model.entities.Negeri;
import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.entities.Tblrujlot;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.cukai.entity.BayaranCukai;
import ekptg.model.htp.cukai.entity.CukaiTemp;
import ekptg.model.htp.cukai.entity.CukaiUtama;
import ekptg.model.htp.entity.Baucer;
import ekptg.model.htp.entity.Bayaran;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.HakmilikCukai;
import ekptg.model.htp.entity.Resit;

public class FrmCukaiPenyataBean implements ICukai {
	
	static Logger myLog = Logger.getLogger(ekptg.model.htp.cukai.FrmCukaiPenyataBean.class);
	private static SimpleDateFormat format =  new SimpleDateFormat("dd/MM/yyyy");
	private String sql = "";
	private Db db = null;
 	private IHtp iHTP = null;  
 	
	public HakMilik getCukai(String idCukai){
		Connection conn = null;
		Db db = null;
		HakMilik hakmilik = null;
		SQLRenderer r = null;
 	    try {
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			r = new SQLRenderer();
			r.add("TPH.NO_FAIL_PTG");
			r.add("TPH.NO_FAIL_PTD");
			r.add("TPH.NO_FAIL");
			r.add("RK.ID_KEMENTERIAN");
			r.add("RK.NAMA_KEMENTERIAN");
			r.add("RA.ID_AGENSI");
			r.add("RA.NAMA_AGENSI");
			r.add("RN.ID_NEGERI");
			r.add("RN.NAMA_NEGERI");
			r.add("RD.ID_DAERAH");
			r.add("RD.NAMA_DAERAH");
			r.add("RM.ID_MUKIM");
			r.add("RM.NAMA_MUKIM");
			r.add("RJH.ID_JENISHAKMILIK");
			r.add("RJH.KOD_JENIS_HAKMILIK");
			r.add("TPC.ID_HAKMILIK");
			r.add("TPC.ID_HAKMILIKCUKAI");
			r.add("TPC.CUKAI_TERKINI");
			r.add("TPC.CUKAI_TALIAIR");
			r.add("TPC.CUKAI_PARIT");
			r.add("TPC.DENDA");
			r.add("TPC.BAYARAN_LAIN");
			r.add("TPC.CUKAI");
			r.add("TPC.PENGURANGAN");
			r.add("TPC.PENGECUALIAN");
			r.add("LTRIM(TPH.NO_HAKMILIK,0) NO_HAKMILIK");
			r.add("TPH.LUAS_BERSAMAAN");
			r.add("TPH.LUAS");
			r.add("TPH.ID_LUAS");
			r.add("TPH.KEGUNAAN_TANAH");
			r.add("TPH.TARIKH_DAFTAR");
			r.add("NVL((SELECT KETERANGAN FROM TBLRUJLOT WHERE ID_LOT=TPH.ID_LOT),'') KOD_LOT");
			r.add("TPH.NO_LOT");	
			r.add("TPH.ID_LOT");	
			r.add("TPH.ID_PERMOHONAN");	
			r.add("NVL((SELECT RU.NAMA_URUSAN FROM TBLRUJURUSAN RU,TBLPFDFAIL F,TBLPERMOHONAN P" +
					" WHERE RU.ID_URUSAN = F.ID_URUSAN " +
					" AND F.ID_FAIL = P.ID_FAIL" +
					" AND P.ID_PERMOHONAN = TPH.ID_PERMOHONAN),'') NAMA_URUSAN");
			r.add("TPC.ID_HAKMILIKCUKAI",r.unquote(idCukai));
			r.add("TPC.ID_HAKMILIK",r.unquote("TPH.ID_HAKMILIK"));
			r.add("TPC.ID_HAKMILIK",r.unquote("TPA.ID_HAKMILIK"));
			r.add("TPA.ID_AGENSI",r.unquote("RA.ID_AGENSI"));
			r.add("RA.ID_KEMENTERIAN",r.unquote("RK.ID_KEMENTERIAN"));
			r.add("TPA.STATUS","Y");
			r.add("TPC.STATUS","S");
			r.add("TPH.ID_MUKIM",r.unquote("RM.ID_MUKIM"));
			r.add("RM.ID_DAERAH",r.unquote("RD.ID_DAERAH"));
			r.add("RD.ID_NEGERI",r.unquote("RN.ID_NEGERI"));
			r.add("TPH.ID_JENISHAKMILIK",r.unquote("RJH.ID_JENISHAKMILIK"));

			sql = r.getSQLSelect("TBLHTPHAKMILIKCUKAI TPC,TBLHTPHAKMILIK TPH" +
				",TBLHTPHAKMILIKAGENSI TPA,TBLRUJAGENSI RA" +
				",TBLRUJKEMENTERIAN RK" +
				",TBLRUJMUKIM RM,TBLRUJDAERAH RD,TBLRUJNEGERI RN,TBLRUJJENISHAKMILIK RJH " +
				"" +
				"");
					//		",TBLHTPHAKMILIKAGENSI TPA,TBLRUJAGENSIMAPPING RAM" +
			//		",TBLRUJAGENSI RA,TBLRUJKEMENTERIAN RK,TBLRUJKEMENTERIANMAPPING RKM");

//			myLog.info("getCukai:"+sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				hakmilik = new HakMilik();
				Agensi agensi = new Agensi();
				Kementerian kem = new Kementerian();
				HakmilikCukai cukai = new HakmilikCukai();
				Negeri negeri = new Negeri();
				Daerah daerah = new Daerah();
				Mukim mukim = new Mukim();
				Tblrujlot lot = new Tblrujlot();
				Tblrujjenishakmilik jh = new Tblrujjenishakmilik();
				
				hakmilik.setIdHakmilik(rs.getLong("ID_HAKMILIK"));
				hakmilik.setNoFail(rs.getString("NO_FAIL"));
				hakmilik.setNoFailPTD(rs.getString("NO_FAIL_PTD"));
				hakmilik.setNoFailPTG(rs.getString("NO_FAIL_PTG"));
				hakmilik.setPerolehan(rs.getString("NAMA_URUSAN"));
				hakmilik.setLuasBersamaan(rs.getDouble("LUAS_BERSAMAAN"));
				hakmilik.setLuasString(rs.getString("LUAS"));
				hakmilik.setKegunaan(rs.getString("KEGUNAAN_TANAH"));
				hakmilik.setNoHakmlik(rs.getString("NO_HAKMILIK"));
				hakmilik.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				hakmilik.setNoLot(rs.getString("NO_LOT"));
				hakmilik.setIdJenisLot(rs.getLong("ID_LOT"));
				hakmilik.setTarikhDaftar(rs.getDate("TARIKH_DAFTAR"));
				hakmilik.setIdJenisLot(rs.getLong("ID_LOT"));
				hakmilik.setIdLuas(rs.getLong("ID_LUAS"));
				hakmilik.setIdPermohonan(rs.getLong("ID_PERMOHONAN"));
				
				kem.setIdKementerian(rs.getLong("ID_KEMENTERIAN"));				
				kem.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));				
				agensi.setIdAgensi(rs.getLong("ID_AGENSI"));
				agensi.setNamaAgensi(rs.getString("NAMA_AGENSI"));
				agensi.setKementerian(kem);
				hakmilik.setAgensi(agensi);
				
				cukai.setCukaiTerkini(rs.getDouble("CUKAI_TERKINI"));
				cukai.setCukai(rs.getDouble("CUKAI"));
				cukai.setCukaiTaliAir(rs.getDouble("CUKAI_TALIAIR"));
				cukai.setCukaiParit(rs.getDouble("CUKAI_PARIT"));
				cukai.setDenda(rs.getDouble("DENDA"));
				cukai.setBayaranLain(rs.getDouble("BAYARAN_LAIN"));
				cukai.setPengecualian(rs.getDouble("PENGECUALIAN"));
				cukai.setPengurangan(rs.getDouble("PENGURANGAN"));
				cukai.setIdHakmilikCukai(rs.getLong("ID_HAKMILIKCUKAI"));
				hakmilik.setHakmilikCukai(cukai);
				
				negeri.setIdNegeri(rs.getLong("ID_NEGERI"));
				negeri.setNamaNegeri(rs.getString("NAMA_NEGERI"));
				hakmilik.setNegeri(negeri);
				
				daerah.setIdDaerah(rs.getLong("ID_DAERAH"));
				daerah.setNamaDaerah(rs.getString("NAMA_DAERAH"));
				hakmilik.setDaerah(daerah);
				
				mukim.setIdMukim(rs.getLong("ID_MUKIM"));
				mukim.setNamaMukim(rs.getString("NAMA_MUKIM"));
				hakmilik.setMukim(mukim);
				
				lot.setKeterangan(rs.getString("KOD_LOT"));
				hakmilik.setRujLot(lot);
				
				jh.setKodJenisHakmilik(rs.getString("KOD_JENIS_HAKMILIK"));
				jh.setIdJenishakmilik(rs.getLong("ID_JENISHAKMILIK"));
				hakmilik.setRujJenisHakmilik(jh);
				
			}
			
		}catch(Exception e){
				e.printStackTrace();
			
	 	} finally {
	 		if (db != null) db.close();
	 	}

		return hakmilik;
	}
	
	public boolean isFailCukaiSelesai(String idNegeri){
		Connection conn = null;
		Db db = null;
		boolean returnValue = false;
 	    try {
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			sql = " SELECT F.ID_FAIL,P.ID_PERMOHONAN "+
				" FROM TBLPFDFAIL F, TBLPERMOHONAN P, TBLRUJSUBURUSANSTATUS SUS,TBLRUJSUBURUSANSTATUSFAIL SUSF  "+
				" WHERE P.ID_FAIL =F.ID_FAIL "+
				" AND P.ID_FAIL =SUSF.ID_FAIL "+
				" AND P.ID_PERMOHONAN =SUSF.ID_PERMOHONAN "+
				" AND SUSF.ID_SUBURUSANSTATUS = SUS.ID_SUBURUSANSTATUS "+
				" AND SUSF.AKTIF = 1 "+
				" AND SUS.LANGKAH <> 99 "+
				" AND F.ID_URUSAN=11 "+
				" AND F.ID_NEGERI="+idNegeri;
			 
			rs = stmt.executeQuery(sql);
			returnValue = rs.next();
			 
		}catch(Exception e){
			e.printStackTrace();
		
		} finally {
			if (db != null) db.close();
		}

		return returnValue;
	}

	public Vector getSenaraiBaucer(String idNegeri, String idPeringkatBayaran, String peringkatBayaran)
		throws Exception {		
		Connection conn = null;
		Db db = null;
		String sql = "";
		Vector list = new Vector();		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			if(peringkatBayaran.equals("1")){
				sql = "SELECT "+ 
					" RN.NAMA_NEGERI nama_daerah,RN.ID_NEGERI id_daerah "+
				  	" ,THB.id_baucer, thb.no_baucer, thb.tarikh_baucer, thb.tarikh_terima "+
					" ,thb.no_resit, thb.tarikh_resit, thb.amaun_baucer,thb.id_peringkatbayaran "+
					" FROM tblhtpbaucer THB,TBLRUJNEGERI RN "+
					" WHERE "+
					" THB.ID_NEGERI = RN.ID_NEGERI "+
				  	" AND THB.id_peringkatbayaran = '"+ idPeringkatBayaran +"' ";
			}else{
				sql = "SELECT "+
				" DISTINCT rd.nama_daerah, rd.id_daerah "+
				" , thb.id_baucer, thb.no_baucer, thb.tarikh_baucer, thb.tarikh_terima, " +
				" thb.no_resit, thb.tarikh_resit, thb.amaun_baucer, thb.id_peringkatbayaran "+
				" FROM tblhtpbaucer thb, tblrujdaerah rd " +
				" WHERE THB.id_daerah = RD.id_daerah " +
				" AND thb.id_peringkatbayaran = '"+ idPeringkatBayaran +"' "+
				" ";					
			}
	
			myLog.info("FrmCukaiBaucerData:::getListBaucer::sql::"+sql);
			rs = stmt.executeQuery(sql);			     
			Hashtable h;		
			while (rs.next()) {
				h = new Hashtable();
			    	  if(rs.getString("nama_daerah") != null){
			    		  h.put("nama_daerah", rs.getString("nama_daerah"));
			    	  }else{
			    		  h.put("nama_daerah", "");
			    	  }
			    	  if(rs.getString("id_daerah") != null){
			    		  h.put("idDaerah", rs.getString("id_daerah"));
			    	  }else{
			    		  h.put("idDaerah", "0");
			    	  }
			    	  if(rs.getString("id_baucer") != null){
			    		  h.put("id_baucer", rs.getString("id_baucer"));
			    	  }else{
			    		  h.put("id_baucer", "0");
			    	  }
			    	  if(rs.getString("no_baucer") != null){
			    		  h.put("no_baucer", rs.getString("no_baucer"));
			    	  }else{
			    		  h.put("no_baucer", "");
			    	  }
			    	  if(rs.getString("id_peringkatbayaran") != null){
			    		  h.put("idPeringkatbayaran", rs.getString("id_peringkatbayaran"));
			    	  }else{
			    		  h.put("idPeringkatbayaran", "0");
			    	  }
//			    	  if(rs.getString("peringkat_bayaran") != null){
//			    		  h.put("peringkat_bayaran", rs.getString("peringkat_bayaran"));
//			    	  }else{
//			    		  h.put("peringkat_bayaran", "0");
//			    	  }
		    		  h.put("peringkat_bayaran",idPeringkatBayaran);
    	  
			    	  if(rs.getString("tarikh_baucer") != null){
			    		  h.put("tkh_baucer", format.format(rs.getDate("tarikh_baucer")));
			    	  }else{
			    		  h.put("tkh_baucer", "");
			    	  }
			    	  if(rs.getString("no_resit") != null){
			    		  h.put("no_resit", rs.getString("no_resit"));
			    	  }else{
			    		  h.put("no_resit", "");
			    	  }
			    	  if(rs.getString("tarikh_resit") != null){
			    		  h.put("tkh_resit", format.format(rs.getDate("tarikh_resit")));
			    	  }else{
			    		  h.put("tkh_resit", "");
			    	  }
			    	  h.put("amaun_baucer", rs.getString("amaun_baucer") == null || "0".equals(rs.getString("amaun_baucer").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("amaun_baucer")));
			    	  
			    	  if(rs.getString("tarikh_terima") != null){
			    		  h.put("tkh_terima", format.format(rs.getDate("tarikh_terima")));
			    	  }else{
			    		  h.put("tkh_terima", "");
			    	  }
			    	  list.addElement(h);
			}
			return list;
			      
		} finally {
			if (db != null) db.close();
			      
		}
			
	}	

	public Vector getSenaraiBaucer(String idNegeri, String idPeringkatBayaran, String peringkatBayaran,String tahun)
		throws Exception {		
		Connection conn = null;
		Db db = null;
		String sql = "";
		Vector list = new Vector();		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			if(peringkatBayaran.equals("1")){
				sql = "SELECT "+ 
					" RN.NAMA_NEGERI nama_daerah,RN.ID_NEGERI id_daerah "+
				  	" ,THB.id_baucer, thb.no_baucer, thb.tarikh_baucer, thb.tarikh_terima "+
					" ,thb.no_resit, thb.tarikh_resit, thb.amaun_baucer,thb.id_peringkatbayaran "+
					" FROM tblhtpbaucer THB,TBLRUJNEGERI RN "+
					" WHERE "+
					" THB.ID_NEGERI = RN.ID_NEGERI "+
				  	" AND THB.id_peringkatbayaran = '"+ idPeringkatBayaran +"' ";
			}else{
				sql = "SELECT "+
				" DISTINCT rd.nama_daerah, rd.id_daerah "+
				" , thb.id_baucer, thb.no_baucer, thb.tarikh_baucer, thb.tarikh_terima, " +
				" thb.no_resit, thb.tarikh_resit, thb.amaun_baucer, thb.id_peringkatbayaran "+
				" FROM tblhtpbaucer thb, tblrujdaerah rd" +
				//", tblhtpcukaiutama TPCU " +
				" WHERE THB.id_daerah = RD.id_daerah " +
				" AND thb.id_peringkatbayaran = '"+ idPeringkatBayaran +"' "+
				//" AND thb.id_peringkatbayaran =TPCU.id_peringkatbayaran " +
				"";					
			}
			if(tahun != null)	sql += " AND THB.TAHUN='"+tahun+"'  ";
//			myLog.info("FrmCukaiBaucerData:::getListBaucer::sql::"+sql);
			rs = stmt.executeQuery(sql);			     
			Hashtable h;		
			while (rs.next()) {
				h = new Hashtable();
			    	  if(rs.getString("nama_daerah") != null){
			    		  h.put("nama_daerah", rs.getString("nama_daerah"));
			    	  }else{
			    		  h.put("nama_daerah", "");
			    	  }
			    	  if(rs.getString("id_daerah") != null){
			    		  h.put("idDaerah", rs.getString("id_daerah"));
			    	  }else{
			    		  h.put("idDaerah", "0");
			    	  }
			    	  if(rs.getString("id_baucer") != null){
			    		  h.put("id_baucer", rs.getString("id_baucer"));
			    	  }else{
			    		  h.put("id_baucer", "0");
			    	  }
			    	  if(rs.getString("no_baucer") != null){
			    		  h.put("no_baucer", rs.getString("no_baucer"));
			    	  }else{
			    		  h.put("no_baucer", "");
			    	  }
			    	  if(rs.getString("id_peringkatbayaran") != null){
			    		  h.put("idPeringkatbayaran", rs.getString("id_peringkatbayaran"));
			    	  }else{
			    		  h.put("idPeringkatbayaran", "0");
			    	  }
		    		  h.put("peringkat_bayaran",idPeringkatBayaran);
		  
			    	  if(rs.getString("tarikh_baucer") != null){
			    		  h.put("tkh_baucer", format.format(rs.getDate("tarikh_baucer")));
			    	  }else{
			    		  h.put("tkh_baucer", "");
			    	  }
			    	  if(rs.getString("no_resit") != null){
			    		  h.put("no_resit", rs.getString("no_resit"));
			    	  }else{
			    		  h.put("no_resit", "");
			    	  }
			    	  if(rs.getString("tarikh_resit") != null){
			    		  h.put("tkh_resit", format.format(rs.getDate("tarikh_resit")));
			    	  }else{
			    		  h.put("tkh_resit", "");
			    	  }
			    	  h.put("amaun_baucer", rs.getString("amaun_baucer") == null || "0".equals(rs.getString("amaun_baucer").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("amaun_baucer")));
			    	  
			    	  if(rs.getString("tarikh_terima") != null){
			    		  h.put("tkh_terima", format.format(rs.getDate("tarikh_terima")));
			    	  }else{
			    		  h.put("tkh_terima", "");
			    	  }
			    	  list.addElement(h);
			}
			return list;
			      
		} finally {
			if (db != null) db.close();
			      
		}
			
	}	

	
	public Vector<Hashtable<String, String>> getListDaerah(String idPeringkatBayaran) throws Exception {
		Db db = null;
		    String sql = "SELECT a.id_daerah, a.kod_Daerah, a.nama_Daerah, c.jumlah_cukai " +
		    			 " ,C.ID_CUKAIUTAMA " +
		    			 " FROM tblrujdaerah a, tblhtpperingkatbayaran b, tblhtpcukaiutama c " +
		       			 " WHERE b.id_negeri = c.id_negeri " +
		       			 " and c.id_daerah = a.id_daerah " +
		       			 " and b.id_peringkatbayaran = c.id_peringkatbayaran " +
		       			 " and b.id_peringkatbayaran ='"+idPeringkatBayaran +"'";
		   //mylog.info("getListDaerah:"+sql);
		    try {
		      db = new Db();
		      Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>(); 
		      Statement stmt = db.getStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable<String, String> h;
		      while (rs.next()) {
		    	  h = new Hashtable<String, String>();
		    	  h.put("idDaerah",String.valueOf(rs.getLong("id_daerah"))); 
		    	  h.put("idCukai",rs.getString("id_cukaiutama")); 
		    	  h.put("kod_Daerah",rs.getString("kod_Daerah"));
		    	  h.put("namaDaerah",rs.getString("nama_Daerah"));  
		    	  h.put("jumlahCukai",Util.formatDecimal(rs.getDouble("jumlah_cukai")));  
		    	  v.addElement(h);
		      }
		      return v;
		    } finally {
		      if (db != null) db.close();
		    }
	  }
	
	public Vector<Hashtable<String, Comparable>> getListDaerah(String idPeringkatBayaran, String tahun) 
		throws Exception {
		Db db = null;
		    String sql = "SELECT a.id_daerah, a.kod_Daerah, a.nama_Daerah, c.jumlah_cukai " +
		    			 " ,C.ID_CUKAIUTAMA " +
		    			 " FROM tblrujdaerah a, tblhtpperingkatbayaran b, tblhtpcukaiutama c " +
		       			 " WHERE " +
		       			 //" b.id_negeri = c.id_negeri and " +
		       			 " c.id_daerah = a.id_daerah " +
		       			 " and b.id_peringkatbayaran = c.id_peringkatbayaran " +
		       			 " and b.id_peringkatbayaran ='"+idPeringkatBayaran +"'" +
		       		" AND C.TAHUN = '"+tahun+"' ";
//		   myLog.info("getListDaerah:"+sql);
		    try {
		      db = new Db();
		      Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>(); 
		      Statement stmt = db.getStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable<String, Comparable> h;
		      while (rs.next()) {
		    	  h = new Hashtable<String, Comparable>();
		    	  h.put("idDaerah",rs.getLong("id_daerah")); 
		    	  h.put("idCukai",rs.getString("id_cukaiutama")); 
		    	  h.put("kod_Daerah",rs.getString("kod_Daerah"));
		    	  h.put("namaDaerah",rs.getString("nama_Daerah"));  
		    	  h.put("jumlahCukai",Util.formatDecimal(rs.getDouble("jumlah_cukai")));  
		    	  v.addElement(h);
		      }
		      return v;
		    } finally {
		      if (db != null) db.close();
		    }
	  }
	public Hashtable getPermohonanInfo(String idPermohonan)throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql= " select n.nama_negeri,k.nama_kementerian,a.nama_agensi,s.nama_suburusan," +
	      " f.no_fail,f.tarikh_daftar_fail,h.no_rujukan_kjp,h.no_rujukan_lain," +
	      " p.tarikh_surat,p.tarikh_terima,p.tujuan,p.id_permohonan,f.id_fail," +
	      " k.id_kementerian,a.id_agensi,n.id_negeri " +
	      " FROM " +
	      " tblrujnegeri n,Tblpfdfail f,tblrujkementerian k,tblpermohonan p,"+
	      " tblhtppermohonan h,tblrujagensi a,tblrujsuburusan s where "+
	      " f.ID_NEGERI=n.ID_NEGERI and f.id_kementerian=k.id_kementerian "+
	      " and p.ID_FAIL=f.ID_FAIL and p.id_permohonan=h.ID_PERMOHONAN  "+
	      " and h.ID_AGENSI=a.id_agensi and f.ID_SUBURUSAN=s.ID_SUBURUSAN  "+
	      " and p.id_permohonan = '"+idPermohonan+"'";
	      //mylog.info("getPermohonanInfo:sql="+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h = new Hashtable();

	      while (rs.next()) {
	    	  if(rs.getString("nama_negeri")==null){
	    		  h.put("negeri", "");
	      		}else{
	      			h.put("negeri", rs.getString("nama_negeri")); }
	    	  if(rs.getString("nama_kementerian")==null){
	    		  h.put("kementerian", "");
	    	  }else{
	    		  h.put("kementerian", rs.getString("nama_kementerian"));}
	    	  if(rs.getString("nama_agensi")==null){
	    		  h.put("agensi", "");
	    	  }else {
	    		  h.put("agensi", rs.getString("nama_agensi"));
	    	  }
	    	  if(rs.getString("nama_suburusan")==null){
	    		  h.put("suburusan", "");
	    	  }else{
	    		  h.put("suburusan", rs.getString("nama_suburusan"));
	    	  }
	    	  if(rs.getString("no_fail")==null){
	    		  h.put("fail", "");
	    	  }else{
	    		  h.put("fail", rs.getString("no_fail"));
	    	  }
	    	  if(rs.getDate("tarikh_daftar_fail")==null){
	    		  h.put("tdaftar",new Date());
	    	  }else{
	    		  h.put("tdaftar", rs.getDate("tarikh_daftar_fail"));
	    	  }
	    	  if(rs.getString("no_rujukan_kjp")==null){
	    		  h.put("rujukankjp", "");
	    	  }else{
	    		  h.put("rujukankjp", rs.getString("no_rujukan_kjp"));
	    	  }
	    	  if(rs.getString("no_rujukan_lain")==null){
	    		  h.put("rujukanlain", "");
	    	  }else{
	    		  h.put("rujukanlain", rs.getString("no_rujukan_lain"));
	    	  }
	    	  if(rs.getDate("tarikh_surat")==null){
	    		  h.put("tsurat",new Date());
	    	  }else{
	    		  h.put("tsurat", rs.getDate("tarikh_surat"));
	    	  }
	    	  if(rs.getDate("tarikh_terima")==null){
	    		  h.put("rtterima",new Date());
	    	  }else{
	    		  h.put("rtterima", rs.getDate("tarikh_terima"));
	    	  }
	    	  if(rs.getString("tujuan")==null){
	    		  h.put("tujuan", "");
	    	  }else{
	    		  h.put("tujuan", rs.getString("tujuan"));
	    	  }
	    	  if(rs.getString("id_permohonan")==null){
	    		  h.put("idpermohonan", "");
	    	  }else{
	    		  h.put("idpermohonan", rs.getString("id_permohonan"));
	    	  }
	    	  if(rs.getString("id_fail")==null){
	    		  h.put("idfail", "");
	    	  }else{
	    		  h.put("idfail", rs.getString("id_fail"));
	    	  }	 
    		  h.put("idagensi", rs.getString("id_agensi"));
    		  h.put("idkementerian", rs.getString("id_kementerian"));
    		  h.put("idnegeri", rs.getString("id_negeri"));
	      }
	      return h;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	@Override
	public Vector<Hashtable<String, String>> senaraiPenyataTerperinci(String idNegeri
			,String idDaerah, String idMukim)throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT DISTINCT TPHC.ID_HAKMILIKCUKAI" +
					",TPCT.CUKAI_KENA_BAYAR CUKAI,TPCT.CUKAI_PERLU_BAYAR CUKAITERKINI" +
					",TPCT.CUKAI_TALIAIR, TPCT.CUKAI_PARIT,TPCT.PENGECUALIAN,TPCT.PENGURANGAN " +
					",TPCT.DENDA,TPCT.TUNGGAKAN,TPCT.ID_CUKAITERPERINCI  "+
					",RJ.ID_JENISHAKMILIK,RJ.KOD_JENIS_HAKMILIK" +
					",H.NO_HAKMILIK,H.NO_LOT,H.TARIKH_LUPUT,H.LOKASI,H.LUAS " +
					",H.TARIKH_WARTA,H.NO_WARTA,H.NO_PELAN,H.NO_SYIT,H.SYARAT" +
					",H.SEKATAN,H.KEGUNAAN_TANAH " +
					",NVL(H.NO_FAIL,'TIADA') NO_FAIL,NVL(H.NO_FAIL_PTG,'TIADA') NO_FAIL_PTG " +
					",TO_CHAR(H.TARIKH_DAFTAR,'dd/mm/yyyy') TARIKH_DAFTAR" +
					",LOT.KETERANGAN KETERANGAN_LOT " +
					",LUAS.ID_LUAS,LUAS.KOD_LUAS " +
					",RN.KOD_MAMPU, RN.Id_Negeri,RN.NAMA_NEGERI" +
					",RD.Id_Daerah,RD.NAMA_DAERAH" +
					",RM.ID_MUKIM,RM.NAMA_MUKIM "+
					",RK.ID_KATEGORI,RK.KOD_KATEGORI,RK.KETERANGAN,TO_CHAR(sysdate,'yyyy') tahun "+
					" FROM TBLHTPCUKAITERPERINCI TPCT,TBLHTPHAKMILIKCUKAI TPHC,TBLHTPHAKMILIK H" +
					",TBLRUJJENISHAKMILIK RJ, TBLRUJLOT LOT, TBLRUJLUAS LUAS" +
					",TBLRUJNEGERI RN,TBLRUJDAERAH RD ,TBLRUJMUKIM RM,TBLRUJKATEGORI RK  " +
					" where NVL(H.NO_HAKMILIK,' ') <> ' ' "  +
					"AND H.ID_JENISHAKMILIK = RJ.ID_JENISHAKMILIK " +
					" AND H.ID_LOT = LOT.ID_LOT " +
					" AND H.ID_LUAS = LUAS.ID_LUAS " +
					" AND H.ID_KATEGORI = RK.ID_KATEGORI " +
					" AND H.ID_MUKIM = RM.ID_MUKIM " +
					" AND RM.ID_DAERAH = RD.ID_DAERAH " +
					" AND RD.ID_NEGERI = RN.ID_NEGERI " +
					" AND TPCT.ID_HAKMILIKCUKAI = TPHC.ID_HAKMILIKCUKAI "  +
					" AND TPHC.ID_HAKMILIK = H.ID_HAKMILIK "  +
					"";
			 	    if(idNegeri != ""){
			  	    	sql += " AND RN.ID_NEGERI = "+idNegeri;
			  	     }
			  	     if(idDaerah != ""){
			  	    	sql += " AND RD.ID_DAERAH = "+idDaerah;
			  	     }
			  	     if(idMukim != ""){
			  	    	sql += " AND RM.ID_MUKIM = "+idMukim;
			  	     }
					sql +=" order by RN.KOD_MAMPU,RD.ID_DAERAH,RM.ID_MUKIM";
		
		
			myLog.info("senaraiPenyataTerperinci:sql="+ sql);
		
			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();
			Hashtable h;
			// 38E94-1ND38-UTHTZ-7323Y
			while (rs.next()) {
				h = new Hashtable();		
				h.put("idCukaiTerperinci", rs.getString("ID_CUKAITERPERINCI"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM"));
				h.put("ID_HAKMILIKCUKAI", rs.getString("ID_HAKMILIKCUKAI"));
				h.put("NO_HAKMILIK", Utils.isNull(rs.getString("NO_HAKMILIK")));
				if (rs.getString("PENGECUALIAN") != "") {
					h.put("lebihan", 0);
				} else {
					h.put("lebihan", Utils.isNull(rs.getString("PENGECUALIAN")));
				}
				h.put("tunggakan", Utils.isNull(rs.getString("PENGURANGAN")));
				h.put("denda", Utils.isNull(rs.getString("denda")));
				h.put("cukai_parit", rs.getDouble("cukai_parit"));
				h.put("cukai_taliair", rs.getDouble("cukai_taliair"));
				h.put("cukaiPerluBayar", (rs.getDouble("CUKAI")+rs.getDouble("PENGURANGAN")+rs.getDouble("denda")+rs.getDouble("cukai_parit")+rs.getDouble("cukai_taliair"))-rs.getDouble("PENGECUALIAN"));
				h.put("cukai_kena_bayar", rs.getDouble("CUKAI_PERLU_BAYAR"));
				h.put("NO_LOT", Utils.isNull(rs.getString("NO_LOT")));
				h.put("KETERANGAN_LOT", Utils.isNull(rs.getString("KETERANGAN_LOT")));
				h.put("tahun", Utils.isNull(rs.getString("tahun")));
				h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
				h.put("kegunaan", Utils.isNull(rs.getString("KEGUNAAN_TANAH")));
				h.put("labelHakmilik", Utils.isNull(rs.getString("KOD_JENIS_HAKMILIK"))+Utils.isNull(rs.getString("NO_HAKMILIK")));		
				
				h.put("cukaiDenda", rs.getDouble("DENDA"));
				h.put("cukaiTunggakan", rs.getDouble("TUNGGAKAN"));
				h.put("cukaiPengurangan", rs.getDouble("PENGURANGAN"));
				h.put("cukaiPengecualian", rs.getDouble("PENGECUALIAN"));
				h.put("cukaiPengurangan", rs.getDouble("PENGURANGAN"));
				h.put("cukaiJumlah",(rs.getDouble("CUKAI")+ rs.getDouble("TUNGGAKAN")+rs.getDouble("PENGURANGAN")+rs.getDouble("denda")+rs.getDouble("cukai_parit")+rs.getDouble("cukai_taliair"))-rs.getDouble("PENGECUALIAN"));
				
				list.addElement(h);
				
			}
			return list;
			
		//}catch(Exception e){
		//	e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}

	@Override
	//public Vector<Hashtable<String, String>> senaraiPenyataTerperinci(String idNegeri
	public Vector<Hashtable<String, String>> senaraiHakmilik(String idNegeri
			,String idDaerah, String idMukim, String tahun)throws Exception {
		Db db = null;
		String sql = "";
		try {
			return senaraiPenyataCukaiTemp(idNegeri,idDaerah,idMukim,tahun);

		} finally {
			if (db != null)
				db.close();
			
		}
		
	}
	
	@Override
	public Vector<Hashtable<String, String>> senaraiHakmilik(String idNegeri
			,String idDaerah, String idMukim, String tahun, String noHakmilik)throws Exception {
		Db db = null;
		String sql = "";
		try {
			return senaraiPenyataCukaiTemp(idNegeri,idDaerah,idMukim,tahun,noHakmilik,"","");

		} finally {
			if (db != null)
				db.close();
			
		}
		
	}
	
	@Override
	public Vector<Hashtable<String, String>> senaraiHakmilik(String idNegeri
			,String idDaerah, String idMukim, String tahun
			, String noHakmilik, String jenisHakmilik, String noLot)throws Exception {
		Db db = null;
		String sql = "";
		try {
			return senaraiPenyataCukaiTemp(idNegeri,idDaerah,idMukim,tahun
					,noHakmilik,jenisHakmilik,noLot);

		} finally {
			if (db != null)
				db.close();
			
		}
		
	}
	
	
	@Override
	public Vector<Hashtable<String, String>> senaraiPenyataCukaiTemp(String idNegeri
			,String idDaerah, String idMukim, String tahun)throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT THHCT.ID_CUKAITEMP " +
				" ,THHCT.ID_MUKIM,RM.NAMA_MUKIM,THHCT.ID_DAERAH,RD.NAMA_DAERAH,THHCT.ID_NEGERI,RN.NAMA_NEGERI" +
				" ,RN.KOD_MAMPU " +
				" ,THHCT.NO_HAKMILIK,THHCT.NO_HAKMILIKUPLOAD,THHCT.NO_LOT,THHCT.NO_LOTUPLOAD " +
				" ,LOT.KETERANGAN KETERANGAN_LOT  "+
				" ,NVL((TBLTEMP.LUAS_BERSAMAAN),0) LUAS"+
				" ,THHCT.KEGUNAAN_TANAH " +
				" ,THHCT.CUKAI_PERLU_BAYAR CUKAI " +
				" ,THHCT.CUKAI_PERLU_BAYAR CUKAI_TERKINI " +
				" ,NVL((THHCT.CUKAI_TALIAIR),0) CUKAI_TALIAIR,NVL((THHCT.CUKAI_PARIT),0) CUKAI_PARIT" +
				" ,NVL((THHCT.DENDA),0) DENDA" +
				" ,NVL((THHCT.TUNGGAKAN),0) TUNGGAKAN " +
				" ,NVL((THHCT.PENGURANGAN + THHCT.CUKAI_LAIN + THHCT.BAYARAN_LAIN),0) PENGURANGAN " +
				" ,THHCT.CUKAI_LAIN,NVL(THHCT.BAYARAN_LAIN,0) BAYARAN_LAIN " +
				" ,NVL((THHCT.PENGECUALIAN),0) PENGECUALIAN " +
				" ,NVL((THHCT.LEBIHAN),0.00) LEBIHAN " +
				" ,THHCT.CUKAI_KENA_BAYAR JUMLAH_CUKAI " +
				" ,NVL((TBLTEMP.CATATAN),'') CATATAN"+
				" ,NVL(( SELECT CTI.ID_CUKAITERPERINCI " +
				" FROM " + 
		        " 		TBLHTPHAKMILIK TPHI,TBLHTPHAKMILIKCUKAI TPHCI, TBLHTPCUKAITERPERINCI CTI "+
				"     	WHERE  " +
		        " 		(TPHI.ID_HAKMILIK = TPHCI.ID_HAKMILIK AND TPHCI.STATUS = 'S')" +      
		        " 		AND CTI.ID_HAKMILIKCUKAI = TPHCI.ID_HAKMILIKCUKAI " +        
		        " 		AND TPHI.ID_HAKMILIK = TBLTEMP.ID_HAKMILIK " +   
				" 		AND CTI.TAHUN = THHCT.TAHUN AND ROWNUM<=1 " +
				"  ),0) ID_CUKAITERPERINCI " +
				" ,NVL(( SELECT TPHCI.ID_HAKMILIKCUKAI " + 
				"		FROM  " +
		        " 		TBLHTPHAKMILIKCUKAI TPHCI "+       
				"     	WHERE  " +
				"		(TPHCI.ID_HAKMILIK = TBLTEMP.ID_HAKMILIK AND TPHCI.STATUS = 'S') "+      
				"  		AND ROWNUM<=1 " +
				"  ),0) ID_HAKMILIKCUKAI " +
				" ,TBLTEMP.TARIKH_DAFTAR "+
				" ,RJ.KOD_JENIS_HAKMILIK " +
				" ,THHCT.TAHUN " +
				" FROM " +
				" TBLHTPCUKAITEMP THHCT " +
				" ,TBLRUJNEGERI RN,TBLRUJDAERAH RD,TBLRUJMUKIM RM " +
				" ,TBLRUJLOT LOT,TBLRUJJENISHAKMILIK RJ " +
	    		" ,( SELECT TPHI.ID_HAKMILIK,TPHI.ID_JENISHAKMILIK "+
 	    		" 	,TPHI.ID_MUKIM,TPHI.ID_DAERAH,TPHI.ID_NEGERI,TPHI.ID_KEMENTERIAN,RKI.NAMA_KEMENTERIAN " +
 	    		//" 	,TPHI.NO_HAKMILIK,TPHI.NO_BANGUNAN,TPHI.NO_TINGKAT,TPHI.NO_PETAK" +
 	    		" 	,LTRIM(TPHI.NO_HAKMILIK,0) NO_HAKMILIK " +   
	    		" 	,NVL(LTRIM(TPHI.NO_BANGUNAN,0),0) NO_BANGUNAN " +
	    		" 	,NVL(LTRIM(TPHI.NO_TINGKAT,0),0) NO_TINGKAT " +
	    		" 	,NVL(LTRIM(TPHI.NO_PETAK,0),0) NO_PETAK " +
 	    	    "	,TPHI.LUAS_BERSAMAAN " +
 	    		" 	,TPHI.CATATAN,TO_CHAR(TPHI.TARIKH_DAFTAR,'dd/mm/yyyy') TARIKH_DAFTAR "+ 
 	    		" 	FROM TBLHTPHAKMILIK TPHI,TBLHTPHAKMILIKAGENSI TPHAI,TBLRUJKEMENTERIAN RKI "+
 	    		" 	WHERE TPHAI.ID_HAKMILIK = TPHI.ID_HAKMILIK  "+
 	    		" 	AND TPHAI.STATUS='Y' "+
 	    		" 	AND RKI.ID_KEMENTERIAN = TPHAI.ID_KEMENTERIAN "+
 	    		" ) TBLTEMP "+
				" WHERE THHCT.TAHUN = '"+ tahun +"'"+
				" AND THHCT.ID_MUKIM=RM.ID_MUKIM " +
				" AND THHCT.ID_DAERAH=RD.ID_DAERAH " +
				" AND THHCT.ID_NEGERI=RN.ID_NEGERI " +
				" AND THHCT.ID_LOT = LOT.ID_LOT " +
				" AND THHCT.ID_JENISHAKMILIK = RJ.ID_JENISHAKMILIK " +
// 	    		" AND LTRIM(TBLTEMP.NO_HAKMILIK,0)||LTRIM(TBLTEMP.NO_BANGUNAN,0)||LTRIM(TBLTEMP.NO_TINGKAT,0)||LTRIM(TBLTEMP.NO_PETAK,0) "+  
// 	    		" = LTRIM(THHCT.NO_HAKMILIK,0)||LTRIM(THHCT.NO_BANGUNAN,0)||LTRIM(THHCT.NO_TINGKAT,0)||LTRIM(THHCT.NO_PETAK,0) "+   
// 	    	    " AND TBLTEMP.ID_JENISHAKMILIK = THHCT.ID_JENISHAKMILIK "+
// 	    	    " AND TBLTEMP.ID_MUKIM = THHCT.ID_MUKIM " +
// 	    	    " AND TBLTEMP.ID_DAERAH = THHCT.ID_DAERAH " +
// 	    	    " AND TBLTEMP.ID_NEGERI = THHCT.ID_NEGERI "+
 	    	    " AND ( "+
 	    	    " 	    TBLTEMP.NO_HAKMILIK(+) = LTRIM(THHCT.NO_HAKMILIK,0)  "+ 
	    	    " 	    AND TBLTEMP.NO_BANGUNAN(+) = THHCT.NO_BANGUNAN "+
	    	    " 	    AND TBLTEMP.NO_TINGKAT(+) = THHCT.NO_TINGKAT "+
	    	    " 	    AND TBLTEMP.NO_PETAK(+) = THHCT.NO_PETAK "+
 	    	    " 	    AND TBLTEMP.ID_JENISHAKMILIK(+) = THHCT.ID_JENISHAKMILIK "+
 	    	    " 	    AND TBLTEMP.ID_MUKIM(+) = THHCT.ID_MUKIM  "+
 	    	    " 	    AND TBLTEMP.ID_DAERAH(+) = THHCT.ID_DAERAH "+  
 	    	    " 	    AND TBLTEMP.ID_NEGERI(+) = THHCT.ID_NEGERI "+  
 	    	    " 	) "+
				"";
			if(idNegeri != ""){
				sql += " AND RN.ID_NEGERI = "+idNegeri;
			}
			if(idDaerah != ""){
				sql += " AND RD.ID_DAERAH = "+idDaerah;		  	
			}
			if(idMukim != ""){
				sql += " AND RM.ID_MUKIM = "+idMukim;
			}
			sql +=" ORDER BY RN.KOD_MAMPU,THHCT.ID_DAERAH,THHCT.ID_MUKIM,THHCT.NO_HAKMILIK,THHCT.NO_LOT";

			myLog.info("senaraiPenyataCukaiTemp:sql="+ sql);		
			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();
			Hashtable h;
			// 38E94-1ND38-UTHTZ-7323Y
			while (rs.next()) {
				h = new Hashtable();		
				h.put("idCukaiTerperinci", rs.getString("ID_CUKAITERPERINCI"));
				h.put("idCukaiTemp", rs.getString("ID_CUKAITEMP"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM"));
				h.put("ID_HAKMILIKCUKAI", rs.getString("ID_HAKMILIKCUKAI"));
				h.put("NO_HAKMILIK", Utils.isNull(rs.getString("NO_HAKMILIK")));
				h.put("cukaiPerluBayar", rs.getDouble("CUKAI_TERKINI"));
				h.put("cukaiParit", rs.getDouble("CUKAI_PARIT"));
				h.put("cukaiTaliair", rs.getDouble("CUKAI_TALIAIR"));
				h.put("cukaiTunggakan", rs.getDouble("TUNGGAKAN"));
				h.put("cukaiDenda", rs.getDouble("DENDA"));
				h.put("cukaiPengurangan", rs.getDouble("PENGURANGAN"));
				h.put("cukaiPengecualian", rs.getDouble("PENGECUALIAN"));
				h.put("cukaiLebihan", rs.getDouble("LEBIHAN"));
				h.put("cukaiKenaBayar", rs.getDouble("JUMLAH_CUKAI"));
				h.put("cukaiJumlah", rs.getDouble("JUMLAH_CUKAI"));
				h.put("NO_LOT", Utils.isNull(rs.getString("NO_LOT")));
				h.put("KETERANGAN_LOT", Utils.isNull(rs.getString("KETERANGAN_LOT")));
				h.put("tahun", Utils.isNull(rs.getString("tahun")));
				h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
				h.put("kegunaan", Utils.isNull(rs.getString("KEGUNAAN_TANAH")));
				h.put("labelHakmilik", Utils.isNull(rs.getString("KOD_JENIS_HAKMILIK"))+Utils.isNull(rs.getString("NO_HAKMILIK")));		
				//h.put("cukaiJumlah", (rs.getDouble("CUKAI")+rs.getDouble("TUNGGAKAN")+rs.getDouble("PENGURANGAN")+rs.getDouble("denda")+rs.getDouble("cukai_parit")+rs.getDouble("cukai_taliair"))-rs.getDouble("PENGECUALIAN"));
				
				list.addElement(h);
				
			}
			return list;
			
		//}catch(Exception e){
		//	e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
			
		}
		
	}
	
	public Vector<Hashtable<String, String>> senaraiPenyataCukaiTemp(String idNegeri
			,String idDaerah, String idMukim, String tahun
			,String noHakmilik, String jenisHakmilik,String noLot )throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT THHCT.ID_CUKAITEMP " +
			" ,THHCT.ID_MUKIM,RM.NAMA_MUKIM,THHCT.ID_DAERAH,RD.NAMA_DAERAH,THHCT.ID_NEGERI,RN.NAMA_NEGERI" +
			" ,RN.KOD_MAMPU " +
			" ,THHCT.NO_HAKMILIK,THHCT.NO_HAKMILIKUPLOAD,THHCT.NO_LOT,THHCT.NO_LOTUPLOAD " +
			" ,LOT.KETERANGAN KETERANGAN_LOT  "+
			" ,NVL((TBLTEMP.LUAS_BERSAMAAN),0) LUAS"+
			" ,THHCT.KEGUNAAN_TANAH " +
			" ,THHCT.CUKAI_PERLU_BAYAR CUKAI " +
			" ,THHCT.CUKAI_PERLU_BAYAR CUKAI_TERKINI " +
			" ,NVL((THHCT.CUKAI_TALIAIR),0) CUKAI_TALIAIR,NVL((THHCT.CUKAI_PARIT),0) CUKAI_PARIT" +
			" ,NVL((THHCT.DENDA),0) DENDA" +
			" ,NVL((THHCT.TUNGGAKAN),0) TUNGGAKAN " +
			" ,NVL((THHCT.PENGURANGAN + THHCT.CUKAI_LAIN + THHCT.BAYARAN_LAIN),0) PENGURANGAN " +
			" ,THHCT.CUKAI_LAIN,NVL(THHCT.BAYARAN_LAIN,0) BAYARAN_LAIN " +
			" ,NVL((THHCT.PENGECUALIAN),0) PENGECUALIAN " +
			" ,NVL((THHCT.LEBIHAN),0.00) LEBIHAN " +
			" ,THHCT.CUKAI_KENA_BAYAR JUMLAH_CUKAI " +
			" ,NVL((TBLTEMP.CATATAN),'') CATATAN"+
			" ,NVL(( SELECT CTI.ID_CUKAITERPERINCI " +
			" FROM " + 
	        " 		TBLHTPHAKMILIK TPHI,TBLHTPHAKMILIKCUKAI TPHCI, TBLHTPCUKAITERPERINCI CTI "+
			"     	WHERE  " +
	        " 		(TPHI.ID_HAKMILIK = TPHCI.ID_HAKMILIK AND TPHCI.STATUS = 'S')" +      
	        " 		AND CTI.ID_HAKMILIKCUKAI = TPHCI.ID_HAKMILIKCUKAI " +        
	        " 		AND TPHI.ID_HAKMILIK = TBLTEMP.ID_HAKMILIK " +   
			" 		AND CTI.TAHUN = THHCT.TAHUN AND ROWNUM<=1 " +
			"  ),0) ID_CUKAITERPERINCI " +
			" ,NVL(( SELECT TPHCI.ID_HAKMILIKCUKAI " + 
			"		FROM  " +
	        " 		TBLHTPHAKMILIKCUKAI TPHCI "+       
			"     	WHERE  " +
			"		(TPHCI.ID_HAKMILIK = TBLTEMP.ID_HAKMILIK AND TPHCI.STATUS = 'S') "+      
			"  		AND ROWNUM<=1 " +
			"  ),0) ID_HAKMILIKCUKAI " +
			" ,TBLTEMP.TARIKH_DAFTAR "+
			" ,RJ.KOD_JENIS_HAKMILIK " +
			" ,THHCT.TAHUN " +
			" FROM " +
			" TBLHTPCUKAITEMP THHCT " +
			" ,TBLRUJNEGERI RN,TBLRUJDAERAH RD,TBLRUJMUKIM RM " +
			" ,TBLRUJLOT LOT,TBLRUJJENISHAKMILIK RJ " +
    		" ,( SELECT TPHI.ID_HAKMILIK,TPHI.ID_JENISHAKMILIK "+
	    		" 	,TPHI.ID_MUKIM,TPHI.ID_DAERAH,TPHI.ID_NEGERI,TPHI.ID_KEMENTERIAN,RKI.NAMA_KEMENTERIAN " +
	    		//" 	,TPHI.NO_HAKMILIK,TPHI.NO_BANGUNAN,TPHI.NO_TINGKAT,TPHI.NO_PETAK" +
	    		" 	,LTRIM(TPHI.NO_HAKMILIK,0) NO_HAKMILIK " +   
	    		" 	,NVL(LTRIM(TPHI.NO_BANGUNAN,0),0) NO_BANGUNAN " +
	    		" 	,NVL(LTRIM(TPHI.NO_TINGKAT,0),0) NO_TINGKAT " +
	    		" 	,NVL(LTRIM(TPHI.NO_PETAK,0),0) NO_PETAK " +
	    	    "	,TPHI.LUAS_BERSAMAAN " +
	    		" 	,TPHI.CATATAN,TO_CHAR(TPHI.TARIKH_DAFTAR,'dd/mm/yyyy') TARIKH_DAFTAR "+ 
	    		" 	FROM TBLHTPHAKMILIK TPHI,TBLHTPHAKMILIKAGENSI TPHAI,TBLRUJKEMENTERIAN RKI "+
	    		" 	WHERE TPHAI.ID_HAKMILIK = TPHI.ID_HAKMILIK  "+
	    		" 	AND TPHAI.STATUS='Y' "+
	    		" 	AND RKI.ID_KEMENTERIAN = TPHAI.ID_KEMENTERIAN "+
	    		" ) TBLTEMP "+
			" WHERE THHCT.TAHUN = '"+ tahun +"'"+
			" AND THHCT.ID_MUKIM=RM.ID_MUKIM " +
			" AND THHCT.ID_DAERAH=RD.ID_DAERAH " +
			" AND THHCT.ID_NEGERI=RN.ID_NEGERI " +
			" AND THHCT.ID_LOT = LOT.ID_LOT " +
			" AND THHCT.ID_JENISHAKMILIK = RJ.ID_JENISHAKMILIK " +
//	    		" AND LTRIM(TBLTEMP.NO_HAKMILIK,0)||LTRIM(TBLTEMP.NO_BANGUNAN,0)||LTRIM(TBLTEMP.NO_TINGKAT,0)||LTRIM(TBLTEMP.NO_PETAK,0) "+  
//	    		" = LTRIM(THHCT.NO_HAKMILIK,0)||LTRIM(THHCT.NO_BANGUNAN,0)||LTRIM(THHCT.NO_TINGKAT,0)||LTRIM(THHCT.NO_PETAK,0) "+   
//	    	    " AND TBLTEMP.ID_JENISHAKMILIK = THHCT.ID_JENISHAKMILIK "+
//	    	    " AND TBLTEMP.ID_MUKIM = THHCT.ID_MUKIM " +
//	    	    " AND TBLTEMP.ID_DAERAH = THHCT.ID_DAERAH " +
//	    	    " AND TBLTEMP.ID_NEGERI = THHCT.ID_NEGERI "+
	    	    " AND ( "+
	    	    " 	    TBLTEMP.NO_HAKMILIK(+) = LTRIM(THHCT.NO_HAKMILIK,0)  "+ 
	    	    " 	    AND TBLTEMP.NO_BANGUNAN(+) = THHCT.NO_BANGUNAN "+
	    	    " 	    AND TBLTEMP.NO_TINGKAT(+) = THHCT.NO_TINGKAT "+
	    	    " 	    AND TBLTEMP.NO_PETAK(+) = THHCT.NO_PETAK "+
	    	    " 	    AND TBLTEMP.ID_JENISHAKMILIK(+) = THHCT.ID_JENISHAKMILIK "+
	    	    " 	    AND TBLTEMP.ID_MUKIM(+) = THHCT.ID_MUKIM  "+
	    	    " 	    AND TBLTEMP.ID_DAERAH(+) = THHCT.ID_DAERAH "+  
	    	    " 	    AND TBLTEMP.ID_NEGERI(+) = THHCT.ID_NEGERI "+  
	    	    " 	) "+
				"";
			if(idNegeri != ""){
				sql += " AND RN.ID_NEGERI = "+idNegeri;
			}
			if(idDaerah != ""){
				sql += " AND RD.ID_DAERAH = "+idDaerah;		  	
			}
			if(idMukim != ""){
				sql += " AND RM.ID_MUKIM = "+idMukim;
			}
			if(!noHakmilik.equals("")){
				sql += " AND THHCT.NO_HAKMILIK LIKE '%"+noHakmilik+"%'";
			}
			if(!noLot.equals("")){
				sql += " AND THHCT.NO_LOT LIKE '%"+noLot+"%'";
			}
			sql +=" ORDER BY RN.KOD_MAMPU,THHCT.ID_DAERAH,THHCT.ID_MUKIM,THHCT.NO_HAKMILIK,THHCT.NO_LOT";
			myLog.info("senaraiPenyataCukaiTemp:sql="+ sql);		
			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();		
				h.put("idCukaiTerperinci", rs.getString("ID_CUKAITERPERINCI"));
				h.put("idCukaiTemp", rs.getString("ID_CUKAITEMP"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM"));
				h.put("ID_HAKMILIKCUKAI", rs.getString("ID_HAKMILIKCUKAI"));
				h.put("NO_HAKMILIK", Utils.isNull(rs.getString("NO_HAKMILIK")));
				h.put("cukaiPerluBayar", rs.getDouble("CUKAI_TERKINI"));
				h.put("cukaiParit", rs.getDouble("CUKAI_PARIT"));
				h.put("cukaiTaliair", rs.getDouble("CUKAI_TALIAIR"));
				h.put("cukaiTunggakan", rs.getDouble("TUNGGAKAN"));
				h.put("cukaiDenda", rs.getDouble("DENDA"));
				h.put("cukaiPengurangan", rs.getDouble("PENGURANGAN"));
				h.put("cukaiPengecualian", rs.getDouble("PENGECUALIAN"));
				h.put("cukaiLebihan", rs.getDouble("LEBIHAN"));
				h.put("cukaiKenaBayar", rs.getDouble("JUMLAH_CUKAI"));
				h.put("cukaiJumlah", rs.getDouble("JUMLAH_CUKAI"));
				h.put("NO_LOT", Utils.isNull(rs.getString("NO_LOT")));
				h.put("KETERANGAN_LOT", Utils.isNull(rs.getString("KETERANGAN_LOT")));
				h.put("tahun", Utils.isNull(rs.getString("tahun")));
				h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
				h.put("kegunaan", Utils.isNull(rs.getString("KEGUNAAN_TANAH")));
				h.put("labelHakmilik", Utils.isNull(rs.getString("KOD_JENIS_HAKMILIK"))+Utils.isNull(rs.getString("NO_HAKMILIK")));		
				list.addElement(h);
				
			}
			return list;
			
		//}catch(Exception e){
		//	e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
			
		}
		
	}
	
	@Override
	//public Vector<Hashtable<String, String>> senaraiHakmilik(String idNegeri
	public Vector<Hashtable<String, String>> senaraiPenyataTerperinciCukai(String idNegeri
			,String idDaerah, String idMukim, String tahun)throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT DISTINCT TPHC.ID_HAKMILIKCUKAI" +
					",TPCT.CUKAI_KENA_BAYAR CUKAI,TPCT.CUKAI_PERLU_BAYAR CUKAITERKINI" +
					",TPCT.CUKAI_TALIAIR, TPCT.CUKAI_PARIT,TPCT.PENGECUALIAN,TPCT.PENGURANGAN " +
					",TPCT.TUNGGAKAN,TPCT.DENDA,TPCT.ID_CUKAITERPERINCI  "+
					",RJ.ID_JENISHAKMILIK,RJ.KOD_JENIS_HAKMILIK" +
					",H.NO_HAKMILIK,H.NO_LOT,H.TARIKH_LUPUT,H.LOKASI,H.LUAS " +
					",H.TARIKH_WARTA,H.NO_WARTA,H.NO_PELAN,H.NO_SYIT,H.SYARAT" +
					",H.SEKATAN,H.KEGUNAAN_TANAH " +
					",NVL(H.NO_FAIL,'TIADA') NO_FAIL,NVL(H.NO_FAIL_PTG,'TIADA') NO_FAIL_PTG " +
					",TO_CHAR(H.TARIKH_DAFTAR,'dd/mm/yyyy') TARIKH_DAFTAR" +
					",LOT.KETERANGAN KETERANGAN_LOT " +
					",LUAS.ID_LUAS,LUAS.KOD_LUAS " +
					",RN.KOD_MAMPU, RN.Id_Negeri,RN.NAMA_NEGERI" +
					",RD.Id_Daerah,RD.NAMA_DAERAH" +
					",RM.ID_MUKIM,RM.NAMA_MUKIM "+
					",RK.ID_KATEGORI,RK.KOD_KATEGORI,RK.KETERANGAN,TO_CHAR(sysdate,'yyyy') tahun "+
					" FROM TBLHTPCUKAITERPERINCI TPCT,TBLHTPHAKMILIKCUKAI TPHC,TBLHTPHAKMILIK H" +
					",TBLRUJJENISHAKMILIK RJ, TBLRUJLOT LOT, TBLRUJLUAS LUAS" +
					",TBLRUJNEGERI RN,TBLRUJDAERAH RD ,TBLRUJMUKIM RM,TBLRUJKATEGORI RK  " +
					" where NVL(H.NO_HAKMILIK,' ') <> ' ' "  +
					" AND H.ID_JENISHAKMILIK = RJ.ID_JENISHAKMILIK " +
					" AND H.ID_LOT = LOT.ID_LOT " +
					" AND H.ID_LUAS = LUAS.ID_LUAS " +
					" AND H.ID_KATEGORI = RK.ID_KATEGORI " +
					" AND H.ID_MUKIM = RM.ID_MUKIM " +
					" AND RM.ID_DAERAH = RD.ID_DAERAH " +
					" AND RD.ID_NEGERI = RN.ID_NEGERI " +
					" AND TPCT.ID_HAKMILIKCUKAI = TPHC.ID_HAKMILIKCUKAI "  +
					" AND TPHC.ID_HAKMILIK = H.ID_HAKMILIK " +
					" AND TPCT.TAHUN = '"+ tahun +"'"+
					"";
			 	    if(idNegeri != ""){
			  	    	sql += " AND RN.ID_NEGERI = "+idNegeri;
			  	     }
			  	     if(idDaerah != ""){
			  	    	sql += " AND RD.ID_DAERAH = "+idDaerah;
			  	     }
			  	     if(idMukim != ""){
			  	    	sql += " AND RM.ID_MUKIM = "+idMukim;
			  	     }
					sql +=" order by RN.KOD_MAMPU,RD.ID_DAERAH,RM.ID_MUKIM";
		
		
			myLog.info("senaraiPenyataTerperinci:sql="+ sql);
		
			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();
			Hashtable h;
			// 38E94-1ND38-UTHTZ-7323Y
			while (rs.next()) {
				h = new Hashtable();		
				h.put("idCukaiTerperinci", rs.getString("ID_CUKAITERPERINCI"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM"));
				h.put("ID_HAKMILIKCUKAI", rs.getString("ID_HAKMILIKCUKAI"));
				h.put("NO_HAKMILIK", Utils.isNull(rs.getString("NO_HAKMILIK")));
				if (rs.getString("PENGECUALIAN") != "") {
					h.put("lebihan", 0);
				} else {
					h.put("lebihan", Utils.isNull(rs.getString("PENGECUALIAN")));
				}
				h.put("tunggakan", Utils.isNull(rs.getString("PENGURANGAN")));
				h.put("denda", Utils.isNull(rs.getString("denda")));
				h.put("cukai_parit", rs.getDouble("cukai_parit"));
				h.put("cukai_taliair", rs.getDouble("cukai_taliair"));
				h.put("cukaiPerluBayar", (rs.getDouble("CUKAI")+rs.getDouble("PENGURANGAN")+rs.getDouble("denda")+rs.getDouble("cukai_parit")+rs.getDouble("cukai_taliair"))-rs.getDouble("PENGECUALIAN"));
				//h.put("cukai_parit2", rs.getDouble("cukai_parit2"));
				//h.put("cukai_taliair2", rs.getDouble("cukai_taliair2"));
				h.put("cukai_kena_bayar", rs.getDouble("CUKAITERKINI"));
				h.put("NO_LOT", Utils.isNull(rs.getString("NO_LOT")));
				h.put("KETERANGAN_LOT", Utils.isNull(rs.getString("KETERANGAN_LOT")));
				h.put("tahun", Utils.isNull(rs.getString("tahun")));
				h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
				h.put("kegunaan", Utils.isNull(rs.getString("KEGUNAAN_TANAH")));
				h.put("labelHakmilik", Utils.isNull(rs.getString("KOD_JENIS_HAKMILIK"))+Utils.isNull(rs.getString("NO_HAKMILIK")));		
				h.put("cukaiDenda", rs.getDouble("DENDA"));
				h.put("cukaiTunggakan", rs.getDouble("TUNGGAKAN"));
				h.put("cukaiPengurangan", rs.getDouble("PENGURANGAN"));
				h.put("cukaiPengecualian", rs.getDouble("PENGECUALIAN"));
				h.put("cukaiPengurangan", rs.getDouble("PENGURANGAN"));
				h.put("cukaiJumlah", rs.getDouble("CUKAI"));
				list.addElement(h);
			}
			return list;
			
		//}catch(Exception e){
		//	e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@Override
	public void cukaiTerperinciHapus(String idCukaiTerperinci) {
		Db db = null;
		Connection conn = null;
		String sql = null;
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            sql = "DELETE FROM TBLHTPCUKAITERPERINCI WHERE ID_CUKAITERPERINCI='"+idCukaiTerperinci+"'";
            //mylog.info("cukaiTerperinciHapus:sql="+sql);
            stmt.executeUpdate(sql);
            conn.commit();
            
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
			if(db != null)
				db.close();
			
		}

	}
	
	@Override
	public void cukaiTempHapus(String idCukaiTemp) {
		Db db = null;
		Connection conn = null;
		String sql = null;
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            sql = "DELETE FROM TBLHTPCUKAITEMP WHERE ID_CUKAITEMP='"+idCukaiTemp+"'";
            //mylog.info("cukaiTerperinciHapus:sql="+sql);
            stmt.executeUpdate(sql);
            conn.commit();
            
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
			if(db != null)
				db.close();
			
		}

	}
	
	public CukaiUtama getCukaiUtama(String idPeringkatBayaran) throws Exception {
		Db db = null;
		CukaiUtama cu = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT SUM(TPCU.JUMLAH_HAKMILIK) JUMLAH_HAKMILIK,SUM(TPCU.JUMLAH_CUKAI) JUMLAH_CUKAI " +
				" ,SUM(TPCU.AMAUN_BAYARAN_CUKAI) AMAUN_BAYARAN_CUKAI "+
				" FROM TBLHTPCUKAIUTAMA TPCU " +
				" WHERE "+
				" TPCU.ID_PERINGKATBAYARAN = '" + idPeringkatBayaran +"'"+
				" GROUP BY TPCU.ID_PERINGKATBAYARAN";

			//myLog.info("getCukaiUtama:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				cu = new CukaiUtama();
				cu.setJumlahCukai(rs.getDouble("JUMLAH_CUKAI"));
				cu.setJumlahBayar(rs.getDouble("AMAUN_BAYARAN_CUKAI"));
				cu.setBilanganHakmilik(rs.getInt("JUMLAH_HAKMILIK"));
				
			}
			
		} finally {
			if (db != null)
				db.close();
		}
			
		return cu;
		
	}
	
	public Vector<Hashtable<String, String>> CukaiSenaraiKemaskiniFail(String idNegeri,String idDaerah, String idMukim,String tahun)throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			//SQLRenderer r = new SQLRenderer();
			sql = "" +
				"SELECT distinct HTPHC.ID_HAKMILIKCUKAI,HTPHC.CUKAI,HTPHC.CUKAI_TERKINI as cukaiterkini, HTPHC.cukai_taliair " +
				", HTPHC.cukai_parit ,HTPHC.PENGECUALIAN,HTPHC.PENGURANGAN,HTPHC.BAYARAN_LAIN,HTPHC.DENDA  "+
				", U.Id_Jenishakmilik,H.KOD_JENIS_HAKMILIK,U.NO_HAKMILIK" +
				", LOT.KETERANGAN KETERANGAN_LOT,U.NO_LOT " +
				", U.TARIKH_LUPUT,U.CUKAI_TERKINI,u.LOKASI,LUAS.ID_LUAS,LUAS.KOD_LUAS,U.LUAS " +
				", U.TARIKH_WARTA,U.NO_WARTA,U.NO_PELAN,U.NO_SYIT,U.SYARAT,U.SEKATAN,U.KEGUNAAN_TANAH " +
				", NVL(U.NO_FAIL,'TIADA') NO_FAIL,NVL(U.NO_FAIL_PTG,'TIADA') NO_FAIL_PTG " +
				" ,TO_CHAR(U.TARIKH_DAFTAR,'dd/mm/yyyy') TARIKH_DAFTAR" +
				" ,RN.KOD_MAMPU, RN.Id_Negeri,RN.NAMA_NEGERI,RD.Id_Daerah,RD.NAMA_DAERAH,RM.ID_MUKIM,RM.NAMA_MUKIM "+
				" , RK.ID_KATEGORI,RK.KOD_KATEGORI,RK.KETERANGAN" +
				",TO_CHAR(sysdate,'yyyy') tahun "+
				"  "+
				" FROM TBLHTPHAKMILIK U, Tblrujjenishakmilik H, Tblrujlot LOT, Tblrujluas LUAS" +
				" , TBLRUJNEGERI RN,TBLRUJDAERAH RD ,TBLRUJMUKIM RM, "+
				" TBLRUJKATEGORI RK,TBLHTPHAKMILIKCUKAI HTPHC "+
				" where NVL(U.NO_HAKMILIK,' ') <> ' ' AND U.Id_Jenishakmilik = H.Id_Jenishakmilik "+
				" AND U.Id_Lot=lot.Id_Lot "+
				" AND U.Id_Luas=luas.Id_Luas "+
				" AND U.ID_KATEGORI=RK.ID_KATEGORI "+
				" AND U.ID_MUKIM=RM.ID_MUKIM "+
				" AND RM.ID_DAERAH=RD.ID_DAERAH "+
				" AND RD.ID_NEGERI=RN.ID_NEGERI "+
				" AND U.ID_HAKMILIK=HTPHC.ID_HAKMILIK " +
				" AND HTPHC.STATUS = 'S' "+
				" AND HTPHC.ID_HAKMILIKCUKAI not in((select TPCTI.ID_HAKMILIKCUKAI from tblhtpcukaiterperinci TPCTI,TBLHTPHAKMILIKCUKAI TPHCI "+
				" where TPCTI.ID_HAKMILIKCUKAI=TPHCI.ID_HAKMILIKCUKAI " +
				" AND TPCTI.TAHUN="+ tahun +" "+
				" )) "+
				" AND (U.STATUS_SAH IS NULL OR U.STATUS_SAH IN ('T','H','D')) " +
				" AND U.PEGANGAN_HAKMILIK NOT IN " +
				" (SELECT TPU.PEGANGAN_HAKMILIK " +
				" 	FROM TBLHTPPAJAKAN TPP,TBLHTPHAKMILIKURUSAN TPU,TBLPERMOHONAN P, TBLPFDFAIL F " +
				" 	WHERE TPU.ID_PERMOHONAN=P.ID_PERMOHONAN " +
				" 	AND P.ID_FAIL=F.ID_FAIL " +
				" 	AND F.ID_URUSAN=3 " +
				" 	AND TPP.ID_PERMOHONAN=P.ID_PERMOHONAN " +
				" 	AND TPP.KATEGORI_CUKAI = 'P' AND TPU.PEGANGAN_HAKMILIK IS NOT NULL )  " +
				"";
			 	if(idNegeri != ""){
			 		sql += " AND RN.ID_NEGERI = "+idNegeri;
			 	}
			  	if(idDaerah != ""){
			  		sql += " AND RD.ID_DAERAH = "+idDaerah;
			  	}
			  	if(idMukim != ""){
			  		sql += " AND U.ID_MUKIM = "+idMukim;
			  	}
				sql +=" order by RN.KOD_MAMPU,RD.ID_DAERAH,RM.ID_MUKIM";
				//mylog.info("FrmCukaiSenaraiFailExcelUpload:CukaiSenaraiKemaskiniFail::sql:::"+ sql);
		
				ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();
			Hashtable h;
			// 38E94-1ND38-UTHTZ-7323Y
			while (rs.next()) {
				h = new Hashtable();
		
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM"));
				//h.put("NO_HAKMILIKUPLOAD", rs.getString("NO_HAKMILIKUPLOAD"));
				h.put("ID_HAKMILIKCUKAI", rs.getString("ID_HAKMILIKCUKAI"));
				h.put("NO_HAKMILIK", Utils.isNull(rs.getString("NO_HAKMILIK")));
				if (rs.getString("PENGECUALIAN") != "") {
					h.put("lebihan", 0);
				} else {
					h.put("lebihan", Utils.isNull(rs.getString("PENGECUALIAN")));
				}
				h.put("tunggakan", Utils.isNull(rs.getString("PENGURANGAN")));
				h.put("cukaiDenda", rs.getDouble("DENDA"));
				h.put("cukaiTunggakan", rs.getDouble("BAYARAN_LAIN"));
				h.put("cukaiPengecualian", rs.getDouble("PENGECUALIAN"));
				h.put("cukaiPengurangan", rs.getDouble("PENGURANGAN"));
				h.put("denda", Utils.isNull(rs.getString("denda")));
				h.put("cukai_parit", rs.getDouble("cukai_parit"));
				h.put("cukai_taliair", rs.getDouble("cukai_taliair"));
				h.put("CUKAI_KENA_BAYAR", (rs.getDouble("CUKAI_TERKINI")+rs.getDouble("PENGURANGAN")+rs.getDouble("denda")+rs.getDouble("cukai_parit")+rs.getDouble("cukai_taliair"))-rs.getDouble("PENGECUALIAN"));
				h.put("cukaiJumlah", (rs.getDouble("CUKAI_TERKINI")+rs.getDouble("BAYARAN_LAIN")+rs.getDouble("PENGURANGAN")+rs.getDouble("DENDA")+rs.getDouble("cukai_parit")+rs.getDouble("cukai_taliair"))- rs.getDouble("PENGECUALIAN"));
				//h.put("cukai_parit2", rs.getDouble("cukai_parit2"));
				//h.put("cukai_taliair2", rs.getDouble("cukai_taliair2"));
				//Wajib bayar
				h.put("cukai_kena_bayar", rs.getDouble("CUKAI_TERKINI"));
				h.put("cukaiPerluBayar", rs.getDouble("CUKAI_TERKINI"));
				h.put("NO_LOT", Utils.isNull(rs.getString("NO_LOT")));
				h.put("KETERANGAN_LOT", Utils.isNull(rs.getString("KETERANGAN_LOT")));
				h.put("tahun", Utils.isNull(rs.getString("TAHUN")));
				h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
				h.put("kegunaan", Utils.isNull(rs.getString("KEGUNAAN_TANAH")));
				h.put("labelHakmilik", Utils.isNull(rs.getString("KOD_JENIS_HAKMILIK"))+Utils.isNull(rs.getString("NO_HAKMILIK")));
		
				list.addElement(h);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
		}	

//	@Override
//	public Vector<Hashtable<String, String>> CukaiSenaraiHakmilik(
//			String idNegeri,String idDaerah,String idMukim,String tahun)throws Exception {
//		try {
//			db = new Db();
//			Statement stmt = db.getStatement();
//			sql = "" +
//				"SELECT distinct HTPHC.ID_HAKMILIKCUKAI,HTPHC.CUKAI,HTPHC.CUKAI_TERKINI as cukaiterkini, HTPHC.cukai_taliair " +
//				" ,HTPHC.cukai_parit ,HTPHC.PENGECUALIAN,HTPHC.PENGURANGAN,HTPHC.BAYARAN_LAIN,HTPHC.DENDA  "+
//				" ,U.Id_Jenishakmilik,H.KOD_JENIS_HAKMILIK,LTRIM(U.NO_HAKMILIK,0) NO_HAKMILIK " +
//				" ,LOT.KETERANGAN KETERANGAN_LOT,U.NO_LOT " +
//				" ,U.TARIKH_LUPUT,U.CUKAI_TERKINI,u.LOKASI,LUAS.ID_LUAS,LUAS.KOD_LUAS,U.LUAS " +
//				" ,U.TARIKH_WARTA,U.NO_WARTA,U.NO_PELAN,U.NO_SYIT,U.SYARAT,U.SEKATAN,U.KEGUNAAN_TANAH " +
//				" ,NVL(U.NO_FAIL,'TIADA') NO_FAIL,NVL(U.NO_FAIL_PTG,'TIADA') NO_FAIL_PTG " +
//				" ,TO_CHAR(U.TARIKH_DAFTAR,'dd/mm/yyyy') TARIKH_DAFTAR" +
//				" ,RN.KOD_MAMPU, RN.Id_Negeri,RN.NAMA_NEGERI,RD.Id_Daerah,RD.NAMA_DAERAH,RM.ID_MUKIM,RM.NAMA_MUKIM "+
//				" ,RK.ID_KATEGORI,RK.KOD_KATEGORI,RK.KETERANGAN" +
//				" ,TO_CHAR(sysdate,'yyyy') tahun "+
//				" FROM TBLHTPHAKMILIK U, Tblrujjenishakmilik H, Tblrujlot LOT, Tblrujluas LUAS" +
//				" ,TBLRUJNEGERI RN,TBLRUJDAERAH RD ,TBLRUJMUKIM RM, "+
//				" TBLRUJKATEGORI RK,TBLHTPHAKMILIKCUKAI HTPHC "+
//				" where NVL(U.NO_HAKMILIK,' ') <> ' ' AND U.Id_Jenishakmilik = H.Id_Jenishakmilik "+
//				" AND U.Id_Lot=lot.Id_Lot "+
//				" AND U.Id_Luas=luas.Id_Luas "+
//				" AND U.ID_KATEGORI=RK.ID_KATEGORI "+
//				" AND U.ID_MUKIM=RM.ID_MUKIM "+
//				" AND RM.ID_DAERAH=RD.ID_DAERAH "+
//				" AND RD.ID_NEGERI=RN.ID_NEGERI "+
//				" AND U.ID_HAKMILIK=HTPHC.ID_HAKMILIK " +
//				" AND HTPHC.STATUS = 'S' "+
////				" AND HTPHC.ID_HAKMILIKCUKAI not in" +
////				" ((" +
////				"	select TPCTI.ID_HAKMILIKCUKAI from tblhtpcukaiterperinci TPCTI,TBLHTPHAKMILIKCUKAI TPHCI "+
////				" 	where TPCTI.ID_HAKMILIKCUKAI=TPHCI.ID_HAKMILIKCUKAI " +
////				" 	AND TPCTI.TAHUN="+ tahun +" "+
////				" )) "+
//				" AND LTRIM(U.NO_HAKMILIK,0)||LTRIM(U.NO_BANGUNAN,0)||LTRIM(U.NO_TINGKAT,0)||LTRIM(U.NO_PETAK,0) NOT IN(" +
//				" 	SELECT LTRIM(TPHI.NO_HAKMILIK,0)||LTRIM(TPHI.NO_BANGUNAN,0)||LTRIM(TPHI.NO_TINGKAT,0)||LTRIM(TPHI.NO_PETAK,0)" +
//				" 	FROM TBLHTPCUKAITEMP TPCTI,TBLHTPHAKMILIK TPHI " +
//				" 	WHERE " +
////				"LTRIM(TPHI.NO_HAKMILIK,0) = TPCTI.NO_HAKMILIK "+
//				" LTRIM(TPHI.NO_HAKMILIK,0)||LTRIM(TPHI.NO_BANGUNAN,0)||LTRIM(TPHI.NO_TINGKAT,0)||LTRIM(TPHI.NO_PETAK,0) "+  
// 	    		" = LTRIM(TPCTI.NO_HAKMILIK,0)||LTRIM(TPCTI.NO_BANGUNAN,0)||LTRIM(TPCTI.NO_TINGKAT,0)||LTRIM(TPCTI.NO_PETAK,0) "+   
//				" 	AND TPHI.ID_NEGERI=TPCTI.ID_NEGERI AND TPHI.ID_DAERAH=TPCTI.ID_DAERAH AND TPHI.ID_MUKIM=TPCTI.ID_MUKIM "+
//				" 	AND TPCTI.TAHUN="+ tahun +" "+
//				" ) "+
//				" AND (U.STATUS_SAH IS NULL OR U.STATUS_SAH IN (" +
//				" 	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1" +
//				" )) " +
//				" AND U.PEGANGAN_HAKMILIK NOT IN " +
//				" (	SELECT TPU.PEGANGAN_HAKMILIK " +
//				" 	FROM TBLHTPPAJAKAN TPP,TBLHTPHAKMILIKURUSAN TPU,TBLPERMOHONAN P, TBLPFDFAIL F " +
//				" 	WHERE TPU.ID_PERMOHONAN=P.ID_PERMOHONAN " +
//				" 	AND P.ID_FAIL=F.ID_FAIL " +
//				" 	AND F.ID_URUSAN=3 " +
//				" 	AND TPP.ID_PERMOHONAN=P.ID_PERMOHONAN " +
//				" 	AND TPP.KATEGORI_CUKAI = 'P' AND TPU.PEGANGAN_HAKMILIK IS NOT NULL  " +
//				" ) ";
//			 	if(idNegeri != ""){
//			 		sql += " AND RN.ID_NEGERI = "+idNegeri;
//			 	}
//			  	if(idDaerah != ""){
//			  		sql += " AND RD.ID_DAERAH = "+idDaerah;
//			  	}
//			  	if(idMukim != ""){
//			  		sql += " AND U.ID_MUKIM = "+idMukim;
//			  	}
//				sql +=" order by RN.KOD_MAMPU,RD.ID_DAERAH,RM.ID_MUKIM";
//				myLog.info("CukaiSenaraiHakmilik::sql="+ sql);
//		
//				ResultSet rs = stmt.executeQuery(sql);
//			Vector list = new Vector();
//			Hashtable h;
//			// 38E94-1ND38-UTHTZ-7323Y
//			while (rs.next()) {
//				h = new Hashtable();
//		
//				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI"));
//				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
//				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM"));
//				//h.put("NO_HAKMILIKUPLOAD", rs.getString("NO_HAKMILIKUPLOAD"));
//				h.put("ID_HAKMILIKCUKAI", rs.getString("ID_HAKMILIKCUKAI"));
//				h.put("NO_HAKMILIK", Utils.isNull(rs.getString("NO_HAKMILIK")));
//				if (rs.getString("PENGECUALIAN") != "") {
//					h.put("lebihan", 0);
//				} else {
//					h.put("lebihan", Utils.isNull(rs.getString("PENGECUALIAN")));
//				}
//				h.put("tunggakan", Utils.isNull(rs.getString("PENGURANGAN")));
//				h.put("cukaiDenda", rs.getDouble("DENDA"));
//				h.put("cukaiTunggakan", rs.getDouble("BAYARAN_LAIN"));
//				h.put("cukaiPengecualian", rs.getDouble("PENGECUALIAN"));
//				h.put("cukaiPengurangan", rs.getDouble("PENGURANGAN"));
//				h.put("denda", Utils.isNull(rs.getString("denda")));
//				h.put("cukai_parit", rs.getDouble("cukai_parit"));
//				h.put("cukai_taliair", rs.getDouble("cukai_taliair"));
//				h.put("CUKAI_KENA_BAYAR", (rs.getDouble("CUKAI_TERKINI")+rs.getDouble("PENGURANGAN")+rs.getDouble("denda")+rs.getDouble("cukai_parit")+rs.getDouble("cukai_taliair"))-rs.getDouble("PENGECUALIAN"));
//				h.put("cukaiJumlah", (rs.getDouble("CUKAI_TERKINI")+rs.getDouble("BAYARAN_LAIN")+rs.getDouble("PENGURANGAN")+rs.getDouble("DENDA")+rs.getDouble("cukai_parit")+rs.getDouble("cukai_taliair"))- rs.getDouble("PENGECUALIAN"));
//				//h.put("cukai_parit2", rs.getDouble("cukai_parit2"));
//				//h.put("cukai_taliair2", rs.getDouble("cukai_taliair2"));
//				//Wajib bayar
//				h.put("cukai_kena_bayar", rs.getDouble("CUKAI_TERKINI"));
//				h.put("cukaiPerluBayar", rs.getDouble("CUKAI_TERKINI"));
//				h.put("NO_LOT", Utils.isNull(rs.getString("NO_LOT")));
//				h.put("KETERANGAN_LOT", Utils.isNull(rs.getString("KETERANGAN_LOT")));
//				h.put("tahun", Utils.isNull(rs.getString("TAHUN")));
//				h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
//				h.put("kegunaan", Utils.isNull(rs.getString("KEGUNAAN_TANAH")));
//				h.put("labelHakmilik", Utils.isNull(rs.getString("KOD_JENIS_HAKMILIK"))+Utils.isNull(rs.getString("NO_HAKMILIK")));
//		
//				list.addElement(h);
//			}
//			return list;
//		} finally {
//			if (db != null)
//				db.close();
//		}
//	
//	}	
	
//	@Override
//	public Vector<Hashtable<String, String>> cukaiSenaraiHakmilik(
//			String idNegeri,String idDaerah, String idMukim,String tahun,String noHakmilik)
//			throws Exception {
//		try {
//			db = new Db();
//			Statement stmt = db.getStatement();
//			sql = "" +
//				"SELECT distinct HTPHC.ID_HAKMILIKCUKAI,HTPHC.CUKAI,HTPHC.CUKAI_TERKINI as cukaiterkini, HTPHC.cukai_taliair " +
//				" ,HTPHC.cukai_parit ,HTPHC.PENGECUALIAN,HTPHC.PENGURANGAN,HTPHC.BAYARAN_LAIN,HTPHC.DENDA  "+
//				" ,U.Id_Jenishakmilik,H.KOD_JENIS_HAKMILIK,LTRIM(U.NO_HAKMILIK,0) NO_HAKMILIK " +
//				" ,LOT.KETERANGAN KETERANGAN_LOT,U.NO_LOT " +
//				" ,U.TARIKH_LUPUT,U.CUKAI_TERKINI,u.LOKASI,LUAS.ID_LUAS,LUAS.KOD_LUAS,U.LUAS " +
//				" ,U.TARIKH_WARTA,U.NO_WARTA,U.NO_PELAN,U.NO_SYIT,U.SYARAT,U.SEKATAN,U.KEGUNAAN_TANAH " +
//				" ,NVL(U.NO_FAIL,'TIADA') NO_FAIL,NVL(U.NO_FAIL_PTG,'TIADA') NO_FAIL_PTG " +
//				" ,TO_CHAR(U.TARIKH_DAFTAR,'dd/mm/yyyy') TARIKH_DAFTAR" +
//				" ,RN.KOD_MAMPU, RN.Id_Negeri,RN.NAMA_NEGERI,RD.Id_Daerah,RD.NAMA_DAERAH,RM.ID_MUKIM,RM.NAMA_MUKIM "+
//				" ,RK.ID_KATEGORI,RK.KOD_KATEGORI,RK.KETERANGAN" +
//				" ,TO_CHAR(sysdate,'yyyy') tahun "+
//				" FROM TBLHTPHAKMILIK U, Tblrujjenishakmilik H, Tblrujlot LOT, Tblrujluas LUAS" +
//				" ,TBLRUJNEGERI RN,TBLRUJDAERAH RD ,TBLRUJMUKIM RM, "+
//				" TBLRUJKATEGORI RK,TBLHTPHAKMILIKCUKAI HTPHC "+
//				" where NVL(U.NO_HAKMILIK,' ') <> ' ' AND U.Id_Jenishakmilik = H.Id_Jenishakmilik "+
//				" AND U.Id_Lot=lot.Id_Lot "+
//				" AND U.Id_Luas=luas.Id_Luas "+
//				" AND U.ID_KATEGORI=RK.ID_KATEGORI "+
//				" AND U.ID_MUKIM=RM.ID_MUKIM "+
//				" AND RM.ID_DAERAH=RD.ID_DAERAH "+
//				" AND RD.ID_NEGERI=RN.ID_NEGERI "+
//				" AND U.ID_HAKMILIK=HTPHC.ID_HAKMILIK " +
//				" AND HTPHC.STATUS = 'S' "+
////				" AND HTPHC.ID_HAKMILIKCUKAI not in" +
////				" ((" +
////				"	select TPCTI.ID_HAKMILIKCUKAI from tblhtpcukaiterperinci TPCTI,TBLHTPHAKMILIKCUKAI TPHCI "+
////				" 	where TPCTI.ID_HAKMILIKCUKAI=TPHCI.ID_HAKMILIKCUKAI " +
////				" 	AND TPCTI.TAHUN="+ tahun +" "+
////				" )) "+
//				" AND LTRIM(U.NO_HAKMILIK,0) NOT IN(" +
//				" 	SELECT TPCTI.NO_HAKMILIK FROM TBLHTPCUKAITEMP TPCTI,TBLHTPHAKMILIK TPHI " +
//				" 	WHERE LTRIM(TPHI.NO_HAKMILIK,0) = LTRIM(TPCTI.NO_HAKMILIK,0) "+
//				" 	AND TPHI.ID_NEGERI=TPCTI.ID_NEGERI AND TPHI.ID_DAERAH=TPCTI.ID_DAERAH AND TPHI.ID_MUKIM=TPCTI.ID_MUKIM "+
//				" 	AND TPCTI.TAHUN="+ tahun +" "+
//				" ) "+
//				" AND (U.STATUS_SAH IS NULL OR U.STATUS_SAH IN (" +
//				" 	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1" +
//				" )) " +
//				" AND U.PEGANGAN_HAKMILIK NOT IN " +
//				" (	SELECT TPU.PEGANGAN_HAKMILIK " +
//				" 	FROM TBLHTPPAJAKAN TPP,TBLHTPHAKMILIKURUSAN TPU,TBLPERMOHONAN P, TBLPFDFAIL F " +
//				" 	WHERE TPU.ID_PERMOHONAN=P.ID_PERMOHONAN " +
//				" 	AND P.ID_FAIL=F.ID_FAIL " +
//				" 	AND F.ID_URUSAN=3 " +
//				" 	AND TPP.ID_PERMOHONAN=P.ID_PERMOHONAN " +
//				" 	AND TPP.KATEGORI_CUKAI = 'P' AND TPU.PEGANGAN_HAKMILIK IS NOT NULL  " +
//				" ) ";
//			 	if(idNegeri != ""){
//			 		sql += " AND RN.ID_NEGERI = "+idNegeri;
//			 	}
//			  	if(idDaerah != ""){
//			  		sql += " AND RD.ID_DAERAH = "+idDaerah;
//			  	}
//			  	if(idMukim != ""){
//			  		sql += " AND U.ID_MUKIM = "+idMukim;
//			  	}
//			  	if(noHakmilik != ""){
//			  		sql += " AND U.NO_HAKMILIK like '%"+noHakmilik+"%'";
//			  	}
//				sql +=" order by RN.KOD_MAMPU,RD.ID_DAERAH,RM.ID_MUKIM";
//				//mylog.info("FrmCukaiSenaraiFailExcelUpload:CukaiSenaraiKemaskiniFail::sql:::"+ sql);
//		
//				ResultSet rs = stmt.executeQuery(sql);
//			Vector list = new Vector();
//			Hashtable h;
//			// 38E94-1ND38-UTHTZ-7323Y
//			while (rs.next()) {
//				h = new Hashtable();
//		
//				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI"));
//				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
//				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM"));
//				//h.put("NO_HAKMILIKUPLOAD", rs.getString("NO_HAKMILIKUPLOAD"));
//				h.put("ID_HAKMILIKCUKAI", rs.getString("ID_HAKMILIKCUKAI"));
//				h.put("NO_HAKMILIK", Utils.isNull(rs.getString("NO_HAKMILIK")));
//				if (rs.getString("PENGECUALIAN") != "") {
//					h.put("lebihan", 0);
//				} else {
//					h.put("lebihan", Utils.isNull(rs.getString("PENGECUALIAN")));
//				}
//				h.put("tunggakan", Utils.isNull(rs.getString("PENGURANGAN")));
//				h.put("cukaiDenda", rs.getDouble("DENDA"));
//				h.put("cukaiTunggakan", rs.getDouble("BAYARAN_LAIN"));
//				h.put("cukaiPengecualian", rs.getDouble("PENGECUALIAN"));
//				h.put("cukaiPengurangan", rs.getDouble("PENGURANGAN"));
//				h.put("denda", Utils.isNull(rs.getString("denda")));
//				h.put("cukai_parit", rs.getDouble("cukai_parit"));
//				h.put("cukai_taliair", rs.getDouble("cukai_taliair"));
//				h.put("CUKAI_KENA_BAYAR", (rs.getDouble("CUKAI_TERKINI")+rs.getDouble("PENGURANGAN")+rs.getDouble("denda")+rs.getDouble("cukai_parit")+rs.getDouble("cukai_taliair"))-rs.getDouble("PENGECUALIAN"));
//				h.put("cukaiJumlah", (rs.getDouble("CUKAI_TERKINI")+rs.getDouble("BAYARAN_LAIN")+rs.getDouble("PENGURANGAN")+rs.getDouble("DENDA")+rs.getDouble("cukai_parit")+rs.getDouble("cukai_taliair"))- rs.getDouble("PENGECUALIAN"));
//				//h.put("cukai_parit2", rs.getDouble("cukai_parit2"));
//				//h.put("cukai_taliair2", rs.getDouble("cukai_taliair2"));
//				//Wajib bayar
//				h.put("cukai_kena_bayar", rs.getDouble("CUKAI_TERKINI"));
//				h.put("cukaiPerluBayar", rs.getDouble("CUKAI_TERKINI"));
//				h.put("NO_LOT", Utils.isNull(rs.getString("NO_LOT")));
//				h.put("KETERANGAN_LOT", Utils.isNull(rs.getString("KETERANGAN_LOT")));
//				h.put("tahun", Utils.isNull(rs.getString("TAHUN")));
//				h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
//				h.put("kegunaan", Utils.isNull(rs.getString("KEGUNAAN_TANAH")));
//				h.put("labelHakmilik", Utils.isNull(rs.getString("KOD_JENIS_HAKMILIK"))+Utils.isNull(rs.getString("NO_HAKMILIK")));
//		
//				list.addElement(h);
//			}
//			return list;
//		} finally {
//			if (db != null)
//				db.close();
//		}
//	
//	}	
	
	public Vector getSenaraiPenyatatTerperinci(String idNegeri,String tahun)throws Exception {
		Db db = null;
		String sql = "";			
		Vector list = new Vector();
		try {
			db = new Db();
			sql ="SELECT TPH.ID_DAERAH,SUM(b.CUKAI_TERKINI) AS CUKAITERKINI, SUM(b.DENDA) AS DENDA,c.NAMA_DAERAH,"+
				" count(TPH.ID_DAERAH) as TOTALMUKIM,sum(TPCP.CUKAI_KENA_BAYAR) CUKAI_KENA_BAYAR "+
				" FROM TBLHTPHAKMILIK TPH,TBLHTPHAKMILIKCUKAI b,TBLRUJDAERAH C,TBLHTPCUKAITERPERINCI TPCP "+
				" WHERE TPH.ID_NEGERI='"+idNegeri+"' AND b.ID_HAKMILIK = TPH.ID_HAKMILIK "+
				" AND TPH.ID_DAERAH = c.ID_DAERAH "+
				" AND B.ID_HAKMILIKCUKAI = TPCP.ID_HAKMILIKCUKAI " +
				" AND TPCP.TAHUN ='"+tahun+"' "+
				" GROUP BY TPH.ID_DAERAH,c.NAMA_DAERAH" +
				" ORDER BY TPH.ID_DAERAH ";			
			 myLog.info("CukaiBean::getSenaraiPenyata::sql="+sql);
			      
			 Statement stmt = db.getStatement();
			 ResultSet rs = stmt.executeQuery(sql);
			 Hashtable h;
			 while (rs.next()) {
				 h = new Hashtable();				
				 Double d = 0.00;
				 Double c = 0.00;
				 h.put("nama_daerah", rs.getString("NAMA_DAERAH"));
				 h.put("idDaerah", rs.getString("ID_DAERAH"));
				 h.put("sumDenda", rs.getDouble("DENDA"));
				 h.put("sumIdHakmilik", rs.getInt("TOTALMUKIM"));
				 h.put("sumCukai_", rs.getDouble("CUKAITERKINI"));
				 h.put("sumCukai", rs.getDouble("CUKAI_KENA_BAYAR"));			    
				 h.put("sumCukaiFormat", Utils.format2Decimal(rs.getDouble("CUKAI_KENA_BAYAR")));			    
				 list.addElement(h);
				 
			 }
			 return list;
			
		}finally {
			if (db != null) db.close();
			
		}
	}
	
	public HakMilik getHakmilikByMukimNohakmilikNolot(String idMukim,String idHakmilik,String noHakmilik
			,String idLot,String noLot){
		Connection conn = null;
		Db db = null;
		HakMilik hakmilik = new HakMilik();
		SQLRenderer r = null;
 	    try {
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			r = new SQLRenderer();
			r.add("TPH.NO_FAIL_PTG");
			r.add("TPH.NO_FAIL_PTD");
			r.add("TPH.NO_FAIL");
			r.add("TPH.ID_JENISHAKMILIK");
			r.add("RK.ID_KEMENTERIAN");
			r.add("RK.NAMA_KEMENTERIAN");
			r.add("RA.ID_AGENSI");
			r.add("RA.NAMA_AGENSI");
			r.add("RN.ID_NEGERI");
			r.add("RN.NAMA_NEGERI");
			r.add("RD.ID_DAERAH");
			r.add("RD.NAMA_DAERAH");
			r.add("RM.ID_MUKIM");
			r.add("RM.NAMA_MUKIM");
			//r.add("RJH.ID_JENISHAKMILIK");
			//r.add("RJH.KOD_JENIS_HAKMILIK");
			r.add("TPC.ID_HAKMILIK");
			r.add("TPC.ID_HAKMILIKCUKAI");
			r.add("TPC.CUKAI_TERKINI");
			r.add("TPC.CUKAI_TALIAIR");
			r.add("TPC.CUKAI_PARIT");
			r.add("TPC.DENDA");
			r.add("TPC.BAYARAN_LAIN");
			r.add("TPC.CUKAI");
			r.add("TPC.PENGURANGAN");
			r.add("TPC.PENGECUALIAN");
			r.add("TPH.NO_HAKMILIK");
			r.add("TPH.LUAS_BERSAMAAN");
			r.add("TPH.LUAS");
			r.add("TPH.ID_LUAS");
			r.add("TPH.KEGUNAAN_TANAH");
			r.add("TPH.TARIKH_DAFTAR");
			r.add("NVL((SELECT KETERANGAN FROM TBLRUJLOT WHERE ID_LOT=TPH.ID_LOT),'') KOD_LOT");
			r.add("TPH.NO_LOT");	
			r.add("TPH.ID_LOT");	
			r.add("NVL((SELECT RU.NAMA_URUSAN FROM TBLRUJURUSAN RU,TBLPFDFAIL F,TBLPERMOHONAN P" +
					" WHERE RU.ID_URUSAN = F.ID_URUSAN " +
					" AND F.ID_FAIL = P.ID_FAIL" +
					" AND P.ID_PERMOHONAN = TPH.ID_PERMOHONAN),'') NAMA_URUSAN");
			r.add("NVL((SELECT RJH.KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK RJH "+
					"WHERE RJH.ID_JENISHAKMILIK = TPH.ID_JENISHAKMILIK "+
					"),'TIADA MAKLUMAT') KOD_JENIS_HAKMILIK ");
			//r.add("TPC.ID_HAKMILIKCUKAI",r.unquote(idCukai));
			r.add("TPC.ID_HAKMILIK",r.unquote("TPH.ID_HAKMILIK"));
			r.add("TPC.ID_HAKMILIK",r.unquote("TPA.ID_HAKMILIK"));
			r.add("TPA.ID_AGENSI",r.unquote("RA.ID_AGENSI"));
			r.add("RA.ID_KEMENTERIAN",r.unquote("RK.ID_KEMENTERIAN"));
			r.add("TPA.STATUS","Y");
			r.add("TPC.STATUS","S");
			r.add("TPH.ID_MUKIM",r.unquote("RM.ID_MUKIM"));
			r.add("RM.ID_DAERAH",r.unquote("RD.ID_DAERAH"));
			r.add("RD.ID_NEGERI",r.unquote("RN.ID_NEGERI"));
			r.add("TPH.ID_MUKIM",idMukim);
			r.add("TPH.ID_JENISHAKMILIK",idHakmilik);
			//r.add("TPH.NO_HAKMILIK",noHakmilik, "LIKE");
			r.add("TPH.ID_LOT",idLot);	
			//r.add("TPH.NO_LOT",noLot);	
			sql = r.getSQLSelect("TBLHTPHAKMILIKCUKAI TPC,TBLHTPHAKMILIK TPH" +
				",TBLHTPHAKMILIKAGENSI TPA,TBLRUJAGENSI RA" +
				",TBLRUJKEMENTERIAN RK" +
				",TBLRUJMUKIM RM,TBLRUJDAERAH RD,TBLRUJNEGERI RN " +
				"");
					//		",TBLHTPHAKMILIKAGENSI TPA,TBLRUJAGENSIMAPPING RAM" +
			//		",TBLRUJAGENSI RA,TBLRUJKEMENTERIAN RK,TBLRUJKEMENTERIANMAPPING RKM");
			sql += " AND TPH.NO_HAKMILIK LIKE '%"+noHakmilik+"%'";
			sql += " AND TPH.NO_LOT LIKE '%"+noLot+"%'";
			myLog.info("getHakmilikByMukimNohakmilikNolot:"+sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Agensi agensi = new Agensi();
				Kementerian kem = new Kementerian();
				HakmilikCukai cukai = new HakmilikCukai();
				Negeri negeri = new Negeri();
				Daerah daerah = new Daerah();
				Mukim mukim = new Mukim();
				Tblrujlot lot = new Tblrujlot();
				Tblrujjenishakmilik jh = new Tblrujjenishakmilik();
				
				hakmilik.setIdHakmilik(rs.getLong("ID_HAKMILIK"));
				hakmilik.setNoFail(rs.getString("NO_FAIL"));
				hakmilik.setNoFailPTD(rs.getString("NO_FAIL_PTD"));
				hakmilik.setNoFailPTG(rs.getString("NO_FAIL_PTG"));
				hakmilik.setPerolehan(rs.getString("NAMA_URUSAN"));
				hakmilik.setLuasBersamaan(rs.getDouble("LUAS_BERSAMAAN"));
				hakmilik.setLuasString(rs.getString("LUAS"));
				hakmilik.setKegunaan(rs.getString("KEGUNAAN_TANAH"));
				hakmilik.setNoHakmlik(rs.getString("NO_HAKMILIK"));
				hakmilik.setNoLot(rs.getString("NO_LOT"));
				hakmilik.setIdJenisLot(rs.getLong("ID_LOT"));
				hakmilik.setTarikhDaftar(rs.getDate("TARIKH_DAFTAR"));
				hakmilik.setIdJenisLot(rs.getLong("ID_LOT"));
				hakmilik.setIdLuas(rs.getLong("ID_LUAS"));
				
				kem.setIdKementerian(rs.getLong("ID_KEMENTERIAN"));				
				kem.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));				
				agensi.setIdAgensi(rs.getLong("ID_AGENSI"));
				agensi.setNamaAgensi(rs.getString("NAMA_AGENSI"));
				agensi.setKementerian(kem);
				hakmilik.setAgensi(agensi);
				
				cukai.setCukaiTerkini(rs.getDouble("CUKAI_TERKINI"));
				cukai.setCukai(rs.getDouble("CUKAI"));
				cukai.setCukaiTaliAir(rs.getDouble("CUKAI_TALIAIR"));
				cukai.setCukaiParit(rs.getDouble("CUKAI_PARIT"));
				cukai.setDenda(rs.getDouble("DENDA"));
				cukai.setBayaranLain(rs.getDouble("BAYARAN_LAIN"));
				cukai.setPengecualian(rs.getDouble("PENGECUALIAN"));
				cukai.setPengurangan(rs.getDouble("PENGURANGAN"));
				cukai.setIdHakmilikCukai(rs.getLong("ID_HAKMILIKCUKAI"));
				hakmilik.setHakmilikCukai(cukai);
				
				negeri.setIdNegeri(rs.getLong("ID_NEGERI"));
				negeri.setNamaNegeri(rs.getString("NAMA_NEGERI"));
				hakmilik.setNegeri(negeri);
				
				daerah.setIdDaerah(rs.getLong("ID_DAERAH"));
				daerah.setNamaDaerah(rs.getString("NAMA_DAERAH"));
				hakmilik.setDaerah(daerah);
				
				mukim.setIdMukim(rs.getLong("ID_MUKIM"));
				mukim.setNamaMukim(rs.getString("NAMA_MUKIM"));
				hakmilik.setMukim(mukim);
				
				lot.setKeterangan(rs.getString("KOD_LOT"));
				hakmilik.setRujLot(lot);
				
				jh.setKodJenisHakmilik(rs.getString("KOD_JENIS_HAKMILIK"));
				jh.setIdJenishakmilik(rs.getLong("ID_JENISHAKMILIK"));
				hakmilik.setRujJenisHakmilik(jh);
				
			}
			
		}catch(Exception e){
				e.printStackTrace();
			
	 	} finally {
	 		if (db != null) db.close();
	 	}

		return hakmilik;
	}
	// Asal FrmCukaiKemaskiniDataV1 : 
	//public static Vector getListKCukaiView(int idHakmilikCukai, Long noHakmilik, Long idJenisHakmilik, Long idKementerian, Long idNegeri, Long idDaerah, Long idMukim)throws Exception {

	public Vector getSenaraiCukaiUtkKemaskini(int idHakmilikCukai, String noHakmilik
			, Long idJenisHakmilik, Long idKementerian, Long idNegeri, Long idDaerah
			, Long idMukim)throws Exception {
 	    Db db = null;
 	    String sql = "";
 	    String noHakmilikStr = "";
 	    
 	    if (idHakmilikCukai == 0){
 	    	idHakmilikCukai = 0;
	    }
 	    if (noHakmilikStr.equals("")){
	    	noHakmilik = null;
	    }
	    if (idJenisHakmilik == 0){
	    	idJenisHakmilik = null;
	    }
	    if (idNegeri == 0){
	    	idNegeri = null;
	    }
	    if (idDaerah == 0){
	    	idDaerah = null;
	    }
	    if (idMukim == 0){
	    	idMukim = null;
	    }
	    
 	    try {
 	      db = new Db();
 	      Statement stmt = db.getStatement();
 	      	sql = " SELECT thh.id_negeri, thh.id_daerah, thh.id_mukim, thh.id_jenishakmilik, LTRIM(thh.no_hakmilik,0) NO_HAKMILIK, ";
 	      	sql +=	" tpf.no_fail,  thh.no_fail_ptg, thh.tarikh_daftar, thh.id_luas, thh.id_lot, thh.luas, ";
 	      	sql +=	" thhc.tarikh_masuk, thhc.cukai_terkini, thhc.cukai_taliair, thhc.cukai_parit,  thhc.denda,thhc.bayaran_lain, ";
 	      	sql +=	" thh.no_lot, thhc.id_hakmilikcukai, thh.id_hakmilik, thhc.STATUS_TERKINI,  thhc.cukai, ";
 	      	//sql +=	" thhc.pengecualian, thhc.pengurangan, thhp.kegunaan_tanah, tru.nama_urusan, thpajak.kategori_cukai ";
 	      	sql +=	" thhc.pengecualian, thhc.pengurangan, thhp.kegunaan_tanah, tru.nama_urusan ";
 	      	
 	      	sql +=" FROM tblhtphakmilik thh, tblhtphakmilikcukai thhc, tblhtphakmilikperihal thhp, ";
 	      	//sql +=	" tblpermohonan tp, tblpfdfail tpf, tblrujurusan tru, tblhtppajakan thpajak ";
 	      	sql +=	" tblpermohonan tp, tblpfdfail tpf, tblrujurusan tru";
 	      	
 	      	sql +=" WHERE  thh.id_hakmilik = thhc.id_hakmilik ";
 	      	sql +=	" AND thhc.id_hakmilik = thhp.id_hakmilik ";
 	      	sql +=	" AND tpf.id_urusan = tru.id_urusan ";
 	      	sql +=	" AND tpf.id_fail = tp.id_fail ";
 	      	sql +=	" AND thh.id_permohonan = tp.id_permohonan ";
 	      	//sql +=	" AND thh.id_permohonan = thpajak.id_permohonan(+)";
 	      	
 	      	
 	      	if(idHakmilikCukai != 0){
 	      		sql +=	" AND thhc.id_hakmilikcukai = "+idHakmilikCukai;
	 	     }
 	      	if(noHakmilik != null){
	 	    	sql +=" AND thh.no_hakmilik = "+noHakmilik;
	 	     }
	 	     if(idJenisHakmilik != null){
	 	    	sql += " AND thh.id_jenishakmilik = "+idJenisHakmilik;
	 	     }
	 	     if(idNegeri != null){
	 	    	sql += " AND thh.id_negeri = "+idNegeri;
	 	     }
	 	     if(idDaerah != null){
	 	    	sql += " AND thh.id_daerah = "+idDaerah;
	 	     }
	 	     if(idMukim != null){
	 	    	sql += " AND thh.id_mukim = "+idMukim;
	 	     }
	 	     sql += " ORDER BY thhc.tarikh_masuk DESC";
 	    	
	 	     myLog.info(":getListKCukaiView::sql::"+sql);	
 	     ResultSet rs = stmt.executeQuery(sql);
 	      
 	     Vector list = new Vector();
 	     Hashtable h;
 	     int bil = 1;
 	      
 	     while (rs.next()) {
 	    	  h = new Hashtable();
 	    	  h.put("bil", bil);
 	    	  
 	    	  if(rs.getString("id_negeri") != null){
	    		  h.put("idNegeri", rs.getString("id_negeri"));
	    	  }else{
	    		  h.put("idNegeri", "0");
	    	  }
 	    	  if(rs.getString("id_daerah") != null){
	    		  h.put("idDaerah", rs.getString("id_daerah"));
	    	  }else{
	    		  h.put("idDaerah", "0");
	    	  }
 	    	  if(rs.getString("id_mukim") != null){
	    		  h.put("idMukim", rs.getString("id_mukim"));
	    	  }else{
	    		  h.put("idMukim", "0");
	    	  }
 	    	  if(rs.getString("id_luas") != null){
	    		  h.put("idLuas", rs.getString("id_luas"));
	    	  }else{
	    		  h.put("idLuas", "0");
	    	  }
 	    	  if(rs.getString("id_hakmilikcukai") != null){
	    		  h.put("idHakmilikCukai", rs.getString("id_hakmilikcukai"));
	    	  }else{
	    		  h.put("idHakmilikCukai", "0");
	    	  }
 	    	  if(rs.getString("id_hakmilik") != null){
	    		  h.put("idHakmilik", rs.getString("id_hakmilik"));
	    	  }else{
	    		  h.put("idHakmilik", "0");
	    	  }
 	    	  if(rs.getString("id_jenishakmilik") != null){
	    		  h.put("idJenisHakmilik", rs.getString("id_jenishakmilik"));
	    	  }else{
	    		  h.put("idJenisHakmilik", "0");
	    	  }
 	    	  if(rs.getString("no_hakmilik") != null){
	    		  h.put("noHakmilik", rs.getString("no_hakmilik"));
	    	  }else{
	    		  h.put("noHakmilik", "0");
	    	  }
 	    	  if(rs.getString("no_fail") != null){
	    		  h.put("noFail", rs.getString("no_fail"));
	    	  }else{
	    		  h.put("noFail", "TIADA");
	    	  }
 	    	  if(rs.getString("STATUS_TERKINI") != null){
	    		  h.put("statusBayaran", rs.getString("STATUS_TERKINI"));
	    	  }else{
	    		  h.put("statusBayaran", "TIADA");
	    	  }
 	    	  if(rs.getString("no_fail_ptg") != null){
	    		  h.put("noFailPTG", rs.getString("no_fail_ptg"));
	    	  }else{
	    		  h.put("noFailPTG", "TIADA");
	    	  }
 	    	 if(rs.getString("no_fail_ptg") != null){
	    		  h.put("noFailPTD", rs.getString("no_fail_ptg"));
	    	  }else{
	    		  h.put("noFailPTD", "TIADA");
	    	  }
	    	  if(rs.getString("tarikh_daftar") != null){
	    		  SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	    		  h.put("tarikhDaftar", Format.format(rs.getDate("tarikh_daftar")));
	    	  }else{
	    		  h.put("tarikhDaftar", "");
	    	  }
	    	  if(rs.getString("luas") != null){
	    		  h.put("Luas", Utils.formatLuas((Double)rs.getDouble("luas")));
	    	  }else{
	    		  h.put("Luas", "0");
	    	  }
	    	  if(rs.getString("nama_urusan") != null){
	    		  h.put("caraPerolehi", rs.getString("nama_urusan"));
	    	  }else{
	    		  h.put("caraPerolehi", "TIADA");
	    	  }
	    	  if(rs.getString("id_lot") != null){
	    		  h.put("idLot", rs.getString("id_lot"));
	    	  }else{
	    		  h.put("idLot", "0");
	    	  }
	    	  if(rs.getString("no_lot") != null){
	    		  h.put("NoLot", rs.getString("no_lot"));
	    	  }else{
	    		  h.put("NoLot", "0");
	    	  }
	    	  if(rs.getString("tarikh_masuk") != null){
	    		  h.put("tMasuk", rs.getString("tarikh_masuk"));
	    	  }else{
	    		  h.put("tMasuk", "0");
	    	  }

	    	  h.put("cukaiTerkini", rs.getString("cukai_terkini") == null || "0".equals(rs.getString("cukai_terkini").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("cukai_terkini")));
	    	  h.put("cukaiTaliAir", rs.getString("cukai_taliair") == null || "0".equals(rs.getString("cukai_taliair").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("cukai_taliair")));
	    	  h.put("cukaiParit", rs.getString("cukai_parit") == null || "0".equals(rs.getString("cukai_parit").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("cukai_parit")));
	    	  h.put("Denda", rs.getString("denda") == null || "0".equals(rs.getString("denda").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("denda")));
	    	  h.put("bayaranLain", rs.getString("bayaran_lain") == null || "0".equals(rs.getString("bayaran_lain").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("bayaran_lain")));
	    	  h.put("Cukai", rs.getString("cukai") == null || "0".equals(rs.getString("cukai").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("cukai")));
	    	  h.put("lebihan", rs.getString("pengecualian") == null || "0".equals(rs.getString("pengecualian").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("pengecualian")));
	    	  h.put("tunggakkan", rs.getString("pengurangan") == null || "0".equals(rs.getString("pengurangan").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("pengurangan")));
 	    	  h.put("pengecualian", rs.getString("pengecualian") == null || "0".equals(rs.getString("pengecualian").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("pengecualian")));	    	  
	    	  h.put("pengurangan", rs.getString("pengurangan") == null || "0".equals(rs.getString("pengurangan").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("pengurangan")));

	    	  if(rs.getString("kegunaan_tanah") != null){
	    		  h.put("kegunaanTanah", rs.getString("kegunaan_tanah"));
	    	  }else{
	    		  h.put("kegunaanTanah", "TIADA");
	    	  }
	    	 /* if(rs.getString("kategori_cukai") != null){
	    		  h.put("pembayarCukai", rs.getString("kategori_cukai"));
	    	  }else{
	    		  h.put("pembayarCukai", "TIADA");
	    	  }*/
 	    	
 	    	  list.addElement(h);
 	    	  bil++;
 	    	  
 	      }
 	     return list;
 	     
 	    } finally {
 	      if (db != null) db.close();
 	      
 	    }
 	    
 	}
	
 	public Vector getSenaraiHakmilikCukai20111003(String noHakmilik, Long idJenisHakmilik,
 			Long idNegeri, Long idDaerah, Long idMukim)throws Exception {
 	    Db db = null;
 	    String sql = "";
 	    
 	    if (noHakmilik.equals("")){
 	    	noHakmilik = null;
 	    }
 	    if (idJenisHakmilik == 0){
 	    	idJenisHakmilik = null;
 	    }
 	    if (idNegeri == 0){
 	    	idNegeri = null;
 	    }
 	    if (idDaerah ==0){
 	    	idDaerah = null;
 	    }
 	    if (idMukim == 0){
 	    	idMukim = null;
 	    }
 	   
 	    try {
 	    	db = new Db();
 	    	Statement stmt = db.getStatement();

 	      	sql = " SELECT DISTINCT thh.id_negeri, trn.nama_negeri, thh.id_daerah, trd.nama_daerah, thhc.tarikh_masuk, thh.id_jenishakmilik, ";
 	      	sql +=" thh.id_mukim, trm.nama_mukim, thh.id_hakmilik, NVL(thh.no_hakmilik,0)as no_hakmilik, thha.id_kementerian, ";
 	      	sql +=" trk.nama_kementerian, trjh.kod_jenis_hakmilik, thhc.id_hakmilikcukai  "; //thhc.status_terkini
 	      	sql +=" FROM " +
 	      			"tblhtphakmilik thh, tblhtphakmilikcukai thhc" +
 	      			", tblrujnegeri trn, tblrujdaerah trd,tblrujmukim trm, ";
 	      	sql +="  tblrujkementerian trk, tblhtphakmilikagensi thha, tblrujjenishakmilik trjh ";
 	      	sql +=" WHERE thh.id_jenishakmilik = trjh.id_jenishakmilik ";
 	      	sql +=" AND trn.id_negeri = thh.id_negeri ";
 	      	sql +=" AND trd.id_daerah = thh.id_daerah ";
 	      	sql +=" AND trm.id_mukim = thh.id_mukim ";
 	      	sql +=" AND thha.id_kementerian = trk.id_kementerian ";
 	      	sql +=" AND thh.id_hakmilik = thhc.id_hakmilik";
 	      	sql +=" AND thh.id_hakmilik = thha.id_hakmilik AND length(thh.no_hakmilik) > 1";
 	      	sql +=" AND THHA.STATUS='Y' ";
 	      	sql +=" AND THHC.STATUS='S' ";
 	      	sql +=" AND (THH.STATUS_SAH IS NULL OR THH.STATUS_SAH IN ('T','H','D'))";
 	      	//sql +=" AND thh.id_hakmilik = thhc.id_hakmilik ";
 	      	
 	      	
 	     if(noHakmilik != null){
 	    	sql +=" AND thh.no_hakmilik like '%"+noHakmilik+"%'";
 	     }
 	     if(idJenisHakmilik != null){
 	    	sql += " AND thh.id_jenishakmilik = '"+idJenisHakmilik+"'";
 	     }
 	     if(idNegeri != null){
 	    	sql += " AND thh.id_negeri = '"+idNegeri+"'";
 	     }
 	     if(idDaerah != null){
 	    	sql += " AND thh.id_daerah = '"+idDaerah+"'";
 	     }
 	     if(idMukim != null){
 	    	sql += " AND thh.id_mukim = '"+idMukim+"'";
 	     }
 	     
 	     	//sql +=" AND thhc.status_terkini = 'Y' ";
 	     sql +=" ORDER BY thh.id_negeri ";
	      	
 	     System.out.println("FrmCukaiSenaraiKemaskiniData::getList::sql::"+sql);	
 	     ResultSet rs = stmt.executeQuery(sql);
 	      
 	     Vector list = new Vector();
 	     Hashtable h;
 	     int bil = 1;
 	      
 	     while (rs.next()) {
 	    	  h = new Hashtable();
 	    	  h.put("bil", bil);
 	    	  h.put("idNegeri", rs.getString("id_negeri"));
 	    	  h.put("idDaerah", rs.getString("id_daerah"));
 	    	  h.put("idMukim", rs.getString("id_mukim"));
 	    	  h.put("idHakmilik", rs.getString("id_hakmilik"));
 	    	  h.put("no_hakmilik", rs.getString("no_hakmilik"));
 	    	  h.put("idJenisHakmilik", rs.getString("id_jenishakmilik"));
 	    	  h.put("idKementerian", rs.getString("id_kementerian"));
 	    	  h.put("nama_negeri", rs.getString("nama_negeri"));
 	    	  h.put("nama_daerah", rs.getString("nama_daerah"));
 	    	  h.put("nama_mukim", rs.getString("nama_mukim"));
 	    	  h.put("nama_kementerian", rs.getString("nama_kementerian"));
 	    	  h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik"));
 	    	  h.put("idHakmilikCukai", rs.getString("id_hakmilikcukai"));
 	    	  //h.put("status_terkini", rs.getString("status_terkini"));
 	    	  
 	    	  list.addElement(h);
 	    	  bil++;
 	    	  
 	      }
 	     return list;
 	    
 	    } finally {
 	      if (db != null) db.close();
 	    
 	    }
 	    
 	    
 	}
	
 	//TABLE TBLHTPCUKAITEMP
 	public Vector getSenaraiHakmilikCukai(String noHakmilik, Long idJenisHakmilik,
 			Long idNegeri, Long idDaerah, Long idMukim, String tahun)throws Exception {
 	    if (noHakmilik.equals("")||noHakmilik.equals("0")){
 	    	noHakmilik = null;
 	    }
 	    if (idJenisHakmilik == 0){
 	    	idJenisHakmilik = null;
 	    }
 	    if (idNegeri == 0){
 	    	idNegeri = null;
 	    }
 	    if (idDaerah ==0){
 	    	idDaerah = null;
 	    }
 	    if (idMukim == 0){
 	    	idMukim = null;
 	    }
 	   
 	    try {
 	    	db = new Db();
 	    	Statement stmt = db.getStatement();
 	    	sql = "SELECT "+
 	    		" THHCT.ID_CUKAITEMP ID_HAKMILIKCUKAI "+
 	    		" ,TRM.ID_MUKIM,TRM.NAMA_MUKIM,TRD.ID_DAERAH,TRD.NAMA_DAERAH "+
 	    		" ,TRN.ID_NEGERI,TRN.NAMA_NEGERI "+
 	    		" ,TRJH.ID_JENISHAKMILIK,TRJH.KOD_JENIS_HAKMILIK "+
 	    		" ,NVL(TBLTEMP.ID_HAKMILIK,0) ID_HAKMILIK "+
 	    		" ,NVL(" +
 	    		" TBLTEMP.ID_KEMENTERIAN "+
 	    		",0) ID_KEMENTERIAN "+
 	    		" ,NVL(" +
 	    		" TBLTEMP.NAMA_KEMENTERIAN "+
 	    		",'TIADA NAMA KEMENTERIAN') NAMA_KEMENTERIAN "+
 	    		" ,THHCT.KEGUNAAN_TANAH,THHCT.NO_HAKMILIK "+
 	    		" FROM TBLHTPCUKAITEMP THHCT "+
 	    		" ,tblrujnegeri trn, tblrujdaerah trd,tblrujmukim trm,tblrujjenishakmilik TRJH "+
 	    		" ,( SELECT TPHI.ID_HAKMILIK,TPHI.ID_JENISHAKMILIK,TPHI.NO_HAKMILIK "+
 	    		" 	,TPHI.ID_MUKIM,TPHI.ID_DAERAH,TPHI.ID_NEGERI,TPHI.ID_KEMENTERIAN,RKI.NAMA_KEMENTERIAN " +
 	    		" ,TPHI.NO_BANGUNAN,TPHI.NO_TINGKAT,TPHI.NO_PETAK "+ 
 	    		" 	FROM TBLHTPHAKMILIK TPHI,TBLHTPHAKMILIKAGENSI TPHAI,TBLRUJKEMENTERIAN RKI "+
 	    		" 	WHERE TPHAI.ID_HAKMILIK = TPHI.ID_HAKMILIK  "+
 	    		"     AND TPHAI.STATUS='Y' "+
 	    		"     AND RKI.ID_KEMENTERIAN = TPHAI.ID_KEMENTERIAN "+
 	    		" ) TBLTEMP "+
 	    		" WHERE "+  
 	    		" THHCT.ID_NEGERI = TRN.ID_NEGERI AND THHCT.ID_DAERAH = TRD.ID_DAERAH  AND THHCT.ID_MUKIM = TRM.ID_MUKIM "+
 	    		" AND THHCT.ID_JENISHAKMILIK = TRJH.ID_JENISHAKMILIK "+  
// 	    	    " AND LTRIM(TBLTEMP.NO_HAKMILIK,0) = LTRIM(THHCT.NO_HAKMILIK,0) "+ 
 	    		" AND LTRIM(TBLTEMP.NO_HAKMILIK,0)||LTRIM(TBLTEMP.NO_BANGUNAN,0)||LTRIM(TBLTEMP.NO_TINGKAT,0)||LTRIM(TBLTEMP.NO_PETAK,0) "+  
 	    		" = LTRIM(THHCT.NO_HAKMILIK,0)||LTRIM(THHCT.NO_BANGUNAN,0)||LTRIM(THHCT.NO_TINGKAT,0)||LTRIM(THHCT.NO_PETAK,0) "+   
 	    	    " AND TBLTEMP.ID_JENISHAKMILIK = THHCT.ID_JENISHAKMILIK "+
 	    	    " AND TBLTEMP.ID_MUKIM = THHCT.ID_MUKIM " +
 	    	    " AND TBLTEMP.ID_DAERAH = THHCT.ID_DAERAH " +
 	    	    " AND TBLTEMP.ID_NEGERI = THHCT.ID_NEGERI "+	    		
 	    		" AND THHCT.TAHUN = "+tahun;
 	      	
 	     if(noHakmilik != null){
 	    	sql +=" AND THHCT.no_hakmilik like '%"+noHakmilik+"%'";
 	     }
 	     if(idJenisHakmilik != null){
 	    	sql += " AND THHCT.id_jenishakmilik = '"+idJenisHakmilik+"'";
 	     }
 	     if(idNegeri != null){
 	    	sql += " AND THHCT.id_negeri = '"+idNegeri+"'";
 	     }
 	     if(idDaerah != null){
 	    	sql += " AND THHCT.id_daerah = '"+idDaerah+"'";
 	     }
 	     if(idMukim != null){
 	    	sql += " AND THHCT.id_mukim = '"+idMukim+"'";
 	     } 	     
 	     sql +=" ORDER BY TRN.id_negeri,TRD.id_daerah,TRM.id_mukim,THHCT.NO_HAKMILIK,THHCT.NO_LOT ";	      	
 	     myLog.info("getSenaraiHakmilikCukai::sql="+sql);	
 	     ResultSet rs = stmt.executeQuery(sql);	      
 	     Vector list = new Vector();
 	     Hashtable h;
 	     int bil = 1;
 	      
 	     while (rs.next()) {
 	    	  h = new Hashtable();
 	    	  h.put("bil", bil);
 	    	  h.put("idNegeri", rs.getString("id_negeri"));
 	    	  h.put("idDaerah", rs.getString("id_daerah"));
 	    	  h.put("idMukim", rs.getString("id_mukim"));
 	    	  h.put("idHakmilik", rs.getString("id_hakmilik"));
 	    	  h.put("no_hakmilik", rs.getString("no_hakmilik"));
 	    	  h.put("idJenisHakmilik", rs.getString("id_jenishakmilik"));
 	    	  h.put("idKementerian", rs.getString("id_kementerian"));
 	    	  h.put("nama_negeri", rs.getString("nama_negeri"));
 	    	  h.put("nama_daerah", rs.getString("nama_daerah"));
 	    	  h.put("nama_mukim", rs.getString("nama_mukim"));
 	    	  h.put("nama_kementerian", rs.getString("nama_kementerian"));
 	    	  h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik"));
 	    	  h.put("idHakmilikCukai", rs.getString("id_hakmilikcukai"));
 	    	  //h.put("status_terkini", rs.getString("status_terkini"));	    	  
 	    	  list.addElement(h);
 	    	  bil++;
 	    	  
 	      }
 	     return list;
 	    }catch (SQLException e){
			throw new Exception(getIHTP().getErrorHTML(e.toString()));
			
 	    } finally {
 	      if (db != null) db.close();
 	    
 	    }
 	    	    
 	}
 	
	//TABLE TBLHTPCUKAITEMP
	@Override
 	public Vector senaraiHakmilik(String idNegeri
 			, String idDaerah, String idMukim, String tahun, String noHakmilik, String idJenisHakmilik)
 			throws Exception {
 	    if (noHakmilik.equals("")||noHakmilik.equals("0")){
 	    	noHakmilik = null;
 	    }
 	    if (idJenisHakmilik.equals("")||idJenisHakmilik.equals("0")){
 	    	idJenisHakmilik = null;
 	    }
 	    if (idNegeri.equals("")||idNegeri.equals("0")){
 	    	idNegeri = null;
 	    }
 	    if (idDaerah.equals("")||idDaerah.equals("0")){
 	    	idDaerah = null;
 	    }
 	    if (idMukim.equals("")||idMukim.equals("0")){
 	    	idMukim = null;
 	    }
 	   
 	    try {
 	    	db = new Db();
 	    	Statement stmt = db.getStatement();
 	    	sql = "SELECT "+
 	    		" THHCT.ID_CUKAITEMP ID_HAKMILIKCUKAI "+
 	    		" ,TRM.ID_MUKIM,TRM.NAMA_MUKIM,TRD.ID_DAERAH,TRD.NAMA_DAERAH "+
 	    		" ,TRN.ID_NEGERI,TRN.NAMA_NEGERI "+
 	    		" ,TRJH.ID_JENISHAKMILIK,TRJH.KOD_JENIS_HAKMILIK "+
 	    		" ,NVL(TBLTEMP.ID_HAKMILIK,0) ID_HAKMILIK "+
 	    		" ,NVL(" +
 	    		" TBLTEMP.ID_KEMENTERIAN "+
 	    		",0) ID_KEMENTERIAN "+
 	    		" ,NVL(" +
 	    		" TBLTEMP.NAMA_KEMENTERIAN "+
 	    		",'TIADA NAMA KEMENTERIAN') NAMA_KEMENTERIAN "+
 	    		" ,THHCT.KEGUNAAN_TANAH,THHCT.NO_HAKMILIK "+
 	    		" FROM TBLHTPCUKAITEMP THHCT "+
 	    		" ,tblrujnegeri trn, tblrujdaerah trd,tblrujmukim trm,tblrujjenishakmilik TRJH "+
 	    		" ,( SELECT TPHI.ID_HAKMILIK,TPHI.ID_JENISHAKMILIK,TPHI.NO_HAKMILIK "+
 	    		" 	,TPHI.ID_MUKIM,TPHI.ID_DAERAH,TPHI.ID_NEGERI,TPHI.ID_KEMENTERIAN,RKI.NAMA_KEMENTERIAN " +
 	    		" ,TPHI.NO_BANGUNAN,TPHI.NO_TINGKAT,TPHI.NO_PETAK "+ 
 	    		" 	FROM TBLHTPHAKMILIK TPHI,TBLHTPHAKMILIKAGENSI TPHAI,TBLRUJKEMENTERIAN RKI "+
 	    		" 	WHERE TPHAI.ID_HAKMILIK = TPHI.ID_HAKMILIK  "+
 	    		"     AND TPHAI.STATUS='Y' "+
 	    		"     AND RKI.ID_KEMENTERIAN = TPHAI.ID_KEMENTERIAN "+
 	    		" ) TBLTEMP "+
 	    		" WHERE "+  
 	    		" THHCT.ID_NEGERI = TRN.ID_NEGERI AND THHCT.ID_DAERAH = TRD.ID_DAERAH  AND THHCT.ID_MUKIM = TRM.ID_MUKIM "+
 	    		" AND THHCT.ID_JENISHAKMILIK = TRJH.ID_JENISHAKMILIK "+  
 	    		" AND LTRIM(TBLTEMP.NO_HAKMILIK,0)||LTRIM(TBLTEMP.NO_BANGUNAN,0)||LTRIM(TBLTEMP.NO_TINGKAT,0)||LTRIM(TBLTEMP.NO_PETAK,0) "+  
 	    		" = LTRIM(THHCT.NO_HAKMILIK,0)||LTRIM(THHCT.NO_BANGUNAN,0)||LTRIM(THHCT.NO_TINGKAT,0)||LTRIM(THHCT.NO_PETAK,0) "+   
 	    	    " AND TBLTEMP.ID_JENISHAKMILIK = THHCT.ID_JENISHAKMILIK "+
 	    	    " AND TBLTEMP.ID_MUKIM = THHCT.ID_MUKIM " +
 	    	    " AND TBLTEMP.ID_DAERAH = THHCT.ID_DAERAH " +
 	    	    " AND TBLTEMP.ID_NEGERI = THHCT.ID_NEGERI "+	    		
 	    		" AND THHCT.TAHUN = "+tahun;
 	      	
 	     if(noHakmilik != null){
 	    	sql +=" AND THHCT.no_hakmilik like '%"+noHakmilik+"%'";
 	     }
 	     if(idJenisHakmilik != null){
 	    	sql += " AND THHCT.id_jenishakmilik = '"+idJenisHakmilik+"'";
 	     }
 	     if(idNegeri != null){
 	    	sql += " AND THHCT.id_negeri = '"+idNegeri+"'";
 	     }
 	     if(idDaerah != null){
 	    	sql += " AND THHCT.id_daerah = '"+idDaerah+"'";
 	     }
 	     if(idMukim != null){
 	    	sql += " AND THHCT.id_mukim = '"+idMukim+"'";
 	     } 	     
 	     sql +=" ORDER BY TRN.id_negeri,TRD.id_daerah,TRM.id_mukim,THHCT.NO_HAKMILIK,THHCT.NO_LOT ";	      	
 	     myLog.info("getSenaraiHakmilikCukai::sql="+sql);	
 	     ResultSet rs = stmt.executeQuery(sql);	      
 	     Vector list = new Vector();
 	     Hashtable h;
 	     int bil = 1;
 	      
 	     while (rs.next()) {
 	    	  h = new Hashtable();
 	    	  h.put("bil", bil);
 	    	  h.put("idNegeri", rs.getString("id_negeri"));
 	    	  h.put("idDaerah", rs.getString("id_daerah"));
 	    	  h.put("idMukim", rs.getString("id_mukim"));
 	    	  h.put("idHakmilik", rs.getString("id_hakmilik"));
 	    	  h.put("no_hakmilik", rs.getString("no_hakmilik"));
 	    	  h.put("idJenisHakmilik", rs.getString("id_jenishakmilik"));
 	    	  h.put("idKementerian", rs.getString("id_kementerian"));
 	    	  h.put("nama_negeri", rs.getString("nama_negeri"));
 	    	  h.put("nama_daerah", rs.getString("nama_daerah"));
 	    	  h.put("nama_mukim", rs.getString("nama_mukim"));
 	    	  h.put("nama_kementerian", rs.getString("nama_kementerian"));
 	    	  h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik"));
 	    	  h.put("idHakmilikCukai", rs.getString("id_hakmilikcukai"));
 	    	  //h.put("status_terkini", rs.getString("status_terkini"));	    	  
 	    	  list.addElement(h);
 	    	  bil++;
 	    	  
 	      }
 	     return list;
 	    }catch (SQLException e){
			throw new Exception(getIHTP().getErrorHTML(e.toString()));
			
 	    } finally {
 	      if (db != null) db.close();
 	    
 	    }
 	    	    
 	}
 	
 	//TBLHTPCUKAITEMP
	public Hashtable getHakmilikCukaiTerperinci(String idCukaiTemp)throws Exception {
		Db db = null;
 	    String sql = "";
 	    Hashtable h = null;   
 	    try {
 	    	db = new Db();
 	    	Statement stmt = db.getStatement();
 	      	sql = " SELECT thh.id_negeri, thh.id_daerah, thh.id_mukim, thh.id_jenishakmilik, thh.no_hakmilik "+
 	      		" ,thh.no_fail_ptg, thh.tarikh_daftar, thh.id_luas, thh.id_lot, thh.luas,thh.no_lot,thh.id_hakmilik "+
 	      		" ,THHCT.tarikh_masuk "+
 	      		" ,THH.cukai_terkini, THHCT.cukai_taliair, THHCT.cukai_parit,THHCT.denda,THHCT.bayaran_lain "+
 	      		" ,THHCT.pengecualian, THHCT.pengurangan,THHCT.cukai_kena_bayar cukai "+
 	      		" , tru.nama_urusan,TPF.no_fail,THHCT.kegunaan_tanah,THHCT.id_cukaitemp id_hakmilikcukai "+
 	      		" ,(SELECT thpajak.kategori_cukai FROM tblhtppajakan thpajak "+
 	      		"	WHERE  thpajak.id_permohonan=THH.id_permohonan "+
 	      		" ) kategori_cukai "+
 	      		" , NVL((SELECT thhc.STATUS_TERKINI FROM tblhtphakmilikcukai THHC "+
 	      		" 	WHERE THHC.id_hakmilik = THH.id_hakmilik "+   
 	      		" ),'') STATUS_TERKINI "+
 	      		" FROM "+
 	      		" tblhtphakmilik THH "+
 	      		", tblhtpcukaitemp THHCT "+
 	      		", tblrujnegeri TRN, tblrujdaerah TRD,tblrujmukim TRM "+
 	      		", tblrujkementerian TRK, tblhtphakmilikagensi THHA "+
 	      		", tblrujjenishakmilik TRJH "+
 	      		",tblpermohonan tp, tblpfdfail tpf, tblrujurusan tru "+
 	      		" WHERE " +
 	      		" TRN.id_negeri = THH.id_negeri "+
 	      		" AND TRD.id_daerah = THH.id_daerah "+
 	      		" AND TRM.id_mukim = THH.id_mukim "+
 	      		" AND (THH.no_hakmilik=THHCT.no_hakmilik ) "+
 	      		" AND (THH.id_hakmilik = THHA.id_hakmilik AND THHA.STATUS='Y') "+
 	      		" AND THHA.id_kementerian = TRK.id_kementerian "+
 	      		" AND THH.id_jenishakmilik = TRJH.id_jenishakmilik "+
 	      		" AND THH.id_permohonan = TP.id_permohonan "+
 	      		" AND TP.id_fail = TPF.id_fail "+
 	      		" AND TPF.id_urusan = tru.id_urusan "+ 	      		
 	      		" AND THHCT.ID_CUKAITEMP = "+idCukaiTemp;
 	    	
//	 	 myLog.info(sql);	
 	     ResultSet rs = stmt.executeQuery(sql);      
 	     Vector list = new Vector();
 	     int bil = 1;
 	      
 	     while (rs.next()) {
 	    	  h = new Hashtable();
 	    	  h.put("bil", bil);
 	    	  
 	    	  if(rs.getString("id_negeri") != null){
	    		  h.put("idNegeri", rs.getString("id_negeri"));
	    	  }else{
	    		  h.put("idNegeri", "0");
	    	  }
 	    	  if(rs.getString("id_daerah") != null){
	    		  h.put("idDaerah", rs.getString("id_daerah"));
	    	  }else{
	    		  h.put("idDaerah", "0");
	    	  }
 	    	  if(rs.getString("id_mukim") != null){
	    		  h.put("idMukim", rs.getString("id_mukim"));
	    	  }else{
	    		  h.put("idMukim", "0");
	    	  }
 	    	  if(rs.getString("id_luas") != null){
	    		  h.put("idLuas", rs.getString("id_luas"));
	    	  }else{
	    		  h.put("idLuas", "0");
	    	  }
 	    	  if(rs.getString("id_hakmilikcukai") != null){
	    		  h.put("idHakmilikCukai", rs.getString("id_hakmilikcukai"));
	    	  }else{
	    		  h.put("idHakmilikCukai", "0");
	    	  }
 	    	  if(rs.getString("id_hakmilik") != null){
	    		  h.put("idHakmilik", rs.getString("id_hakmilik"));
	    	  }else{
	    		  h.put("idHakmilik", "0");
	    	  }
 	    	  if(rs.getString("id_jenishakmilik") != null){
	    		  h.put("idJenisHakmilik", rs.getString("id_jenishakmilik"));
	    	  }else{
	    		  h.put("idJenisHakmilik", "0");
	    	  }
 	    	  if(rs.getString("no_hakmilik") != null){
	    		  h.put("noHakmilik", rs.getString("no_hakmilik"));
	    	  }else{
	    		  h.put("noHakmilik", "0");
	    	  }
 	    	  if(rs.getString("no_fail") != null){
	    		  h.put("noFail", rs.getString("no_fail"));
	    	  }else{
	    		  h.put("noFail", "TIADA");
	    	  }
 	    	  if(rs.getString("STATUS_TERKINI") != null){
	    		  h.put("statusBayaran", rs.getString("STATUS_TERKINI"));
	    	  }else{
	    		  h.put("statusBayaran", "TIADA");
	    	  }
 	    	  if(rs.getString("no_fail_ptg") != null){
	    		  h.put("noFailPTG", rs.getString("no_fail_ptg"));
	    	  }else{
	    		  h.put("noFailPTG", "TIADA");
	    	  }
 	    	 if(rs.getString("no_fail_ptg") != null){
	    		  h.put("noFailPTD", rs.getString("no_fail_ptg"));
	    	  }else{
	    		  h.put("noFailPTD", "TIADA");
	    	  }
	    	  if(rs.getString("tarikh_daftar") != null){
	    		  SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	    		  h.put("tarikhDaftar", Format.format(rs.getDate("tarikh_daftar")));
	    	  }else{
	    		  h.put("tarikhDaftar", "");
	    	  }
	    	  if(rs.getString("luas") != null){
	    		  h.put("Luas", Utils.formatLuas((Double)rs.getDouble("luas")));
	    	  }else{
	    		  h.put("Luas", "0");
	    	  }
	    	  if(rs.getString("nama_urusan") != null){
	    		  h.put("caraPerolehi", rs.getString("nama_urusan"));
	    	  }else{
	    		  h.put("caraPerolehi", "TIADA");
	    	  }
	    	  if(rs.getString("id_lot") != null){
	    		  h.put("idLot", rs.getString("id_lot"));
	    	  }else{
	    		  h.put("idLot", "0");
	    	  }
	    	  if(rs.getString("no_lot") != null){
	    		  h.put("NoLot", rs.getString("no_lot"));
	    	  }else{
	    		  h.put("NoLot", "0");
	    	  }
	    	  if(rs.getString("tarikh_masuk") != null){
	    		  h.put("tMasuk", rs.getString("tarikh_masuk"));
	    	  }else{
	    		  h.put("tMasuk", "0");
	    	  }

	    	  h.put("cukaiTerkini", rs.getString("cukai_terkini") == null || "0".equals(rs.getString("cukai_terkini").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("cukai_terkini")));
	    	  h.put("cukaiTaliAir", rs.getString("cukai_taliair") == null || "0".equals(rs.getString("cukai_taliair").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("cukai_taliair")));
	    	  h.put("cukaiParit", rs.getString("cukai_parit") == null || "0".equals(rs.getString("cukai_parit").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("cukai_parit")));
	    	  h.put("Denda", rs.getString("denda") == null || "0".equals(rs.getString("denda").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("denda")));
	    	  h.put("bayaranLain", rs.getString("bayaran_lain") == null || "0".equals(rs.getString("bayaran_lain").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("bayaran_lain")));
	    	  h.put("Cukai", rs.getString("cukai") == null || "0".equals(rs.getString("cukai").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("cukai")));
	    	  h.put("lebihan", rs.getString("pengecualian") == null || "0".equals(rs.getString("pengecualian").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("pengecualian")));
	    	  h.put("tunggakkan", rs.getString("pengurangan") == null || "0".equals(rs.getString("pengurangan").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("pengurangan")));
 	    	  h.put("pengecualian", rs.getString("pengecualian") == null || "0".equals(rs.getString("pengecualian").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("pengecualian")));	    	  
	    	  h.put("pengurangan", rs.getString("pengurangan") == null || "0".equals(rs.getString("pengurangan").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("pengurangan")));

	    	  if(rs.getString("kegunaan_tanah") != null){
	    		  h.put("kegunaanTanah", rs.getString("kegunaan_tanah"));
	    	  }else{
	    		  h.put("kegunaanTanah", "TIADA");
	    	  }
 	    	
 	    	  list.addElement(h);
 	    	  bil++;
 	    	  
 	      }
 	     return h;
 	     
 	    } finally {
 	      if (db != null) db.close();
 	      
 	    }
 	}

	public HakMilik getCukaiHakmilik(String idCukaiTemp){
		Connection conn = null;
		Db db = null;
		HakMilik hakmilik = new HakMilik();
		SQLRenderer r = null;
 	    try {
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			r = new SQLRenderer();
			r.add(" NVL(( "+
					" SELECT TPHI.NO_FAIL_PTG  FROM TBLHTPHAKMILIK TPHI WHERE "+
					" (LTRIM(TPHI.NO_HAKMILIK,0) = LTRIM(TPC.NO_HAKMILIK,0) AND TPHI.ID_JENISHAKMILIK=TPC.ID_JENISHAKMILIK) "+
					" AND TPHI.ID_MUKIM=TPC.ID_MUKIM AND TPHI.ID_DAERAH=TPC.ID_DAERAH AND TPHI.ID_NEGERI=TPC.ID_NEGERI "+
					"),0) NO_FAIL_PTG");
			r.add(" NVL(( "+
					" SELECT TPHI.NO_FAIL_PTD  FROM TBLHTPHAKMILIK TPHI WHERE "+
					" (LTRIM(TPHI.NO_HAKMILIK,0) = LTRIM(TPC.NO_HAKMILIK,0) AND TPHI.ID_JENISHAKMILIK=TPC.ID_JENISHAKMILIK) "+
					" AND TPHI.ID_MUKIM=TPC.ID_MUKIM AND TPHI.ID_DAERAH=TPC.ID_DAERAH AND TPHI.ID_NEGERI=TPC.ID_NEGERI "+
					"),0) NO_FAIL_PTD");
			r.add(" NVL(( "+
					" SELECT TPHI.NO_FAIL  FROM TBLHTPHAKMILIK TPHI WHERE "+
					" (LTRIM(TPHI.NO_HAKMILIK,0) = LTRIM(TPC.NO_HAKMILIK,0) AND TPHI.ID_JENISHAKMILIK=TPC.ID_JENISHAKMILIK) "+
					" AND TPHI.ID_MUKIM=TPC.ID_MUKIM AND TPHI.ID_DAERAH=TPC.ID_DAERAH AND TPHI.ID_NEGERI=TPC.ID_NEGERI "+
					"),0) NO_FAIL");			
			r.add(" NVL(( "+
					" SELECT TPHI.NO_FAIL  FROM TBLHTPHAKMILIK TPHI WHERE "+
					" (LTRIM(TPHI.NO_HAKMILIK,0) = LTRIM(TPC.NO_HAKMILIK,0) AND TPHI.ID_JENISHAKMILIK=TPC.ID_JENISHAKMILIK) "+
					" AND TPHI.ID_MUKIM=TPC.ID_MUKIM AND TPHI.ID_DAERAH=TPC.ID_DAERAH AND TPHI.ID_NEGERI=TPC.ID_NEGERI "+
					"),0) NO_FAIL");	
			r.add(" NVL(( "+
					" SELECT TPHI.LUAS_BERSAMAAN  FROM TBLHTPHAKMILIK TPHI WHERE "+
					" (LTRIM(TPHI.NO_HAKMILIK,0) = LTRIM(TPC.NO_HAKMILIK,0) AND TPHI.ID_JENISHAKMILIK=TPC.ID_JENISHAKMILIK) "+
					" AND TPHI.ID_MUKIM=TPC.ID_MUKIM AND TPHI.ID_DAERAH=TPC.ID_DAERAH AND TPHI.ID_NEGERI=TPC.ID_NEGERI "+
					"),0) LUAS_BERSAMAAN");				
			r.add(" NVL(( "+
					" SELECT TPHI.LUAS  FROM TBLHTPHAKMILIK TPHI WHERE "+
					" (LTRIM(TPHI.NO_HAKMILIK,0) = LTRIM(TPC.NO_HAKMILIK,0) AND TPHI.ID_JENISHAKMILIK=TPC.ID_JENISHAKMILIK) "+
					" AND TPHI.ID_MUKIM=TPC.ID_MUKIM AND TPHI.ID_DAERAH=TPC.ID_DAERAH AND TPHI.ID_NEGERI=TPC.ID_NEGERI "+
					"),'') LUAS");	
			r.add("NVL(( "+
					" SELECT TO_CHAR(TPHI.TARIKH_DAFTAR,'dd/mm/yyyy')  FROM TBLHTPHAKMILIK TPHI WHERE "+
					" (LTRIM(TPHI.NO_HAKMILIK,0) = LTRIM(TPC.NO_HAKMILIK,0) AND TPHI.ID_JENISHAKMILIK=TPC.ID_JENISHAKMILIK) "+
					" AND TPHI.ID_MUKIM=TPC.ID_MUKIM AND TPHI.ID_DAERAH=TPC.ID_DAERAH AND TPHI.ID_NEGERI=TPC.ID_NEGERI "+
					"),'01/01/1900') TARIKH_DAFTAR");	
			r.add("NVL(( "+
					" SELECT ID_HAKMILIK FROM TBLHTPHAKMILIK TPHI WHERE "+
					" (LTRIM(TPHI.NO_HAKMILIK,0) = LTRIM(TPC.NO_HAKMILIK,0) AND TPHI.ID_JENISHAKMILIK=TPC.ID_JENISHAKMILIK) "+
					" AND TPHI.ID_MUKIM=TPC.ID_MUKIM AND TPHI.ID_DAERAH=TPC.ID_DAERAH AND TPHI.ID_NEGERI=TPC.ID_NEGERI "+
					"),0) ID_HAKMILIK");	
			r.add("NVL(( "+
					" SELECT ID_LUAS FROM TBLHTPHAKMILIK TPHI WHERE "+
					" (LTRIM(TPHI.NO_HAKMILIK,0) = LTRIM(TPC.NO_HAKMILIK,0) AND TPHI.ID_JENISHAKMILIK=TPC.ID_JENISHAKMILIK) "+
					" AND TPHI.ID_MUKIM=TPC.ID_MUKIM AND TPHI.ID_DAERAH=TPC.ID_DAERAH AND TPHI.ID_NEGERI=TPC.ID_NEGERI "+
					"),0) ID_LUAS");			
			r.add("NVL(( "+
					" SELECT TPHI.CUKAI FROM TBLHTPHAKMILIK TPHI WHERE "+
					" (LTRIM(TPHI.NO_HAKMILIK,0) = LTRIM(TPC.NO_HAKMILIK,0) AND TPHI.ID_JENISHAKMILIK=TPC.ID_JENISHAKMILIK) "+
					" AND TPHI.ID_MUKIM=TPC.ID_MUKIM AND TPHI.ID_DAERAH=TPC.ID_DAERAH AND TPHI.ID_NEGERI=TPC.ID_NEGERI "+
					"),0.00) CUKAI");
			r.add("NVL(( " +
					"SELECT RKI.NAMA_KEMENTERIAN " + 
					" FROM TBLHTPHAKMILIK TPHI,TBLHTPHAKMILIKAGENSI TPHAI,TBLRUJKEMENTERIAN RKI WHERE " + 
					" RKI.ID_KEMENTERIAN = TPHAI.ID_KEMENTERIAN " +
					" AND (TPHI.ID_HAKMILIK = TPHAI.ID_HAKMILIK AND TPHAI.STATUS='Y') " +
					" AND LTRIM(TPHI.NO_HAKMILIK,0) = LTRIM(TPC.NO_HAKMILIK,0) AND TPHI.ID_JENISHAKMILIK = TPC.ID_JENISHAKMILIK " +
					" AND TPHI.ID_MUKIM = TPC.ID_MUKIM AND TPHI.ID_DAERAH = TPC.ID_DAERAH AND TPHI.ID_NEGERI = TPC.ID_NEGERI " +
					" ),'TIADA NAMA KEMENTERIAN') NAMA_KEMENTERIAN");
			r.add("NVL(( " +
					"SELECT RKI.ID_KEMENTERIAN " + 
					" FROM TBLHTPHAKMILIK TPHI,TBLHTPHAKMILIKAGENSI TPHAI,TBLRUJKEMENTERIAN RKI WHERE " + 
					" RKI.ID_KEMENTERIAN = TPHAI.ID_KEMENTERIAN " +
					" AND (TPHI.ID_HAKMILIK = TPHAI.ID_HAKMILIK AND TPHAI.STATUS='Y') " +
					" AND LTRIM(TPHI.NO_HAKMILIK,0) = LTRIM(TPC.NO_HAKMILIK,0) AND TPHI.ID_JENISHAKMILIK = TPC.ID_JENISHAKMILIK " +
					" AND TPHI.ID_MUKIM = TPC.ID_MUKIM AND TPHI.ID_DAERAH = TPC.ID_DAERAH AND TPHI.ID_NEGERI = TPC.ID_NEGERI " +
					" ),0) ID_KEMENTERIAN");
			r.add("NVL((SELECT RAI.NAMA_AGENSI"+
					" FROM TBLHTPHAKMILIK TPHI,TBLHTPHAKMILIKAGENSI TPHAI,TBLRUJAGENSI RAI WHERE " + 
					" RAI.ID_AGENSI = TPHAI.ID_AGENSI " +
					" AND (TPHI.ID_HAKMILIK = TPHAI.ID_HAKMILIK AND TPHAI.STATUS='Y') " +
					" AND LTRIM(TPHI.NO_HAKMILIK,0) = LTRIM(TPC.NO_HAKMILIK,0) AND TPHI.ID_JENISHAKMILIK = TPC.ID_JENISHAKMILIK " +
					" AND TPHI.ID_MUKIM = TPC.ID_MUKIM AND TPHI.ID_DAERAH = TPC.ID_DAERAH AND TPHI.ID_NEGERI = TPC.ID_NEGERI " +
					" ),'TIADA NAMA AGENSI') NAMA_AGENSI");
			r.add("NVL((SELECT RAI.ID_AGENSI"+
					" FROM TBLHTPHAKMILIK TPHI,TBLHTPHAKMILIKAGENSI TPHAI,TBLRUJAGENSI RAI WHERE " + 
					" RAI.ID_AGENSI = TPHAI.ID_AGENSI " +
					" AND (TPHI.ID_HAKMILIK = TPHAI.ID_HAKMILIK AND TPHAI.STATUS='Y') " +
					" AND LTRIM(TPHI.NO_HAKMILIK,0) = LTRIM(TPC.NO_HAKMILIK,0) AND TPHI.ID_JENISHAKMILIK = TPC.ID_JENISHAKMILIK " +
					" AND TPHI.ID_MUKIM = TPC.ID_MUKIM AND TPHI.ID_DAERAH = TPC.ID_DAERAH AND TPHI.ID_NEGERI = TPC.ID_NEGERI " +
					" ),0) ID_AGENSI");
			r.add("(case "+
					" WHEN ( SELECT count(*) FROM TBLHTPCUKAITEMP TPCTI,tblhtpcukaiutama TPCU " +
					"             ,tblhtpbaucer TPB, tblhtpbayarancukai TPBC " + 
					"             WHERE TPCTI.TAHUN = TPCU.TAHUN  " +
					"             AND TPCTI.ID_DAERAH = TPCU.ID_DAERAH " +
					"             AND TPB.ID_PERINGKATBAYARAN=TPCU.ID_PERINGKATBAYARAN  " +
					"             AND TPB.ID_BAUCER = TPBC.ID_BAUCER " +
					"             AND  TPCTI.ID_CUKAITEMP =TPC.ID_CUKAITEMP ) >0 " +
					"             THEN 'S' " +
					"     ELSE 'B' " +
					"  end ) STATUS_BAYARAN");
			r.add("NVL(( "+
					"     SELECT TPBC.NO_RUJBAYARAN " +
					"             FROM TBLHTPCUKAITEMP TPCTI,tblhtpcukaiutama TPCU " +
					"             ,tblhtpbaucer TPB, tblhtpbayarancukai TPBC  " +
					"             WHERE TPCTI.TAHUN = TPCU.TAHUN  " +
					"             AND TPCTI.ID_DAERAH = TPCU.ID_DAERAH " +
					"             AND TPB.ID_PERINGKATBAYARAN=TPCU.ID_PERINGKATBAYARAN  " +
					"             AND TPB.ID_BAUCER = TPBC.ID_BAUCER " +
					"             AND  TPCTI.ID_CUKAITEMP =TPC.ID_CUKAITEMP AND rownum <= 1 " +
					"   ),'') NO_RUJUKAN");
			r.add("NVL(( "+
					"     SELECT TO_CHAR(TPBC.TARIKH_BAYARAN,'dd/mm/yyyy') " +
					"             FROM TBLHTPCUKAITEMP TPCTI,tblhtpcukaiutama TPCU " +
					"             ,tblhtpbaucer TPB, tblhtpbayarancukai TPBC " + 
					"             WHERE TPCTI.TAHUN = TPCU.TAHUN " + 
					"             AND TPCTI.ID_DAERAH = TPCU.ID_DAERAH " +
					"             AND TPB.ID_PERINGKATBAYARAN=TPCU.ID_PERINGKATBAYARAN " + 
					"             AND TPB.ID_BAUCER = TPBC.ID_BAUCER " +
					"             AND  TPCTI.ID_CUKAITEMP =TPC.ID_CUKAITEMP AND rownum <= 1 " +
					"   ),'01/01/1900') TARIKH_BAYARAN ");
			r.add("RN.ID_NEGERI");
			r.add("RN.NAMA_NEGERI");
			r.add("RD.ID_DAERAH");
			r.add("RD.NAMA_DAERAH");
			r.add("RM.ID_MUKIM");
			r.add("RM.NAMA_MUKIM");
			r.add("RJH.ID_JENISHAKMILIK");
			r.add("RJH.KOD_JENIS_HAKMILIK");
			r.add("LOT.KETERANGAN KOD_LOT");
			r.add("TPC.NO_LOT");	
			r.add("TPC.ID_LOT");			
			r.add("TPC.ID_CUKAITEMP ID_HAKMILIKCUKAI");
			r.add("TPC.CUKAI_KENA_BAYAR CUKAI_TERKINI");
			r.add("TPC.CUKAI_TALIAIR");
			r.add("TPC.CUKAI_PARIT");
			r.add("TPC.DENDA");
			r.add("TPC.BAYARAN_LAIN");
			r.add("TPC.TUNGGAKAN");
			r.add("TPC.PENGURANGAN"); //X CUKUP BAYARAN
			r.add("TPC.PENGECUALIAN"); //MENGURANGKAN BAYARAN, TERLEBIH BAYAR
			r.add("TPC.CUKAI_PERLU_BAYAR");
			//r.add("TPC.CUKAI_KENA_BAYAR CUKAI");
			r.add("LTRIM(TPC.NO_HAKMILIK,0) NO_HAKMILIK");				
			r.add("NVL((SELECT RU.NAMA_URUSAN FROM TBLRUJURUSAN RU,TBLPFDFAIL F,TBLPERMOHONAN P" +
					" WHERE RU.ID_URUSAN = F.ID_URUSAN " +
					" AND F.ID_FAIL = P.ID_FAIL" +
					" AND P.ID_PERMOHONAN = TPC.ID_PERMOHONAN),'') NAMA_URUSAN");			
			r.add("TPC.KEGUNAAN_TANAH");			
			r.add("TPC.NO_RESIT");			
			r.add("NVL(TO_CHAR(TPC.TARIKH_RESIT,'dd/mm/yyyy'),'01/01/1900') TARIKH_RESIT");			
			r.add("TPC.ID_MUKIM",r.unquote("RM.ID_MUKIM"));
			r.add("TPC.ID_DAERAH",r.unquote("RD.ID_DAERAH"));
			r.add("TPC.ID_NEGERI",r.unquote("RN.ID_NEGERI"));
			r.add("TPC.ID_JENISHAKMILIK",r.unquote("RJH.ID_JENISHAKMILIK"));
			r.add("TPC.ID_LOT",r.unquote("LOT.ID_LOT"));		
			r.add("TPC.ID_CUKAITEMP",r.unquote(idCukaiTemp));
			sql = r.getSQLSelect("TBLHTPCUKAITEMP TPC " +
				",TBLRUJMUKIM RM,TBLRUJDAERAH RD,TBLRUJNEGERI RN" +
				",TBLRUJJENISHAKMILIK RJH,TBLRUJLOT LOT " +
				"");

			//myLog.info("getCukaiHakmilik("+idCukaiTemp+"):sql="+sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Agensi agensi = new Agensi();
				Kementerian kem = new Kementerian();
				HakmilikCukai cukai = new HakmilikCukai();
				Negeri negeri = new Negeri();
				Daerah daerah = new Daerah();
				Mukim mukim = new Mukim();
				Tblrujlot lot = new Tblrujlot();
				Tblrujjenishakmilik jh = new Tblrujjenishakmilik();
				BayaranCukai bCukai = new BayaranCukai();
				Resit resit = new Resit();
				
				hakmilik.setIdHakmilik(rs.getLong("ID_HAKMILIK"));
				hakmilik.setNoFail(rs.getString("NO_FAIL"));
				hakmilik.setNoFailPTD(rs.getString("NO_FAIL_PTD"));
				hakmilik.setNoFailPTG(rs.getString("NO_FAIL_PTG"));
				hakmilik.setPerolehan(rs.getString("NAMA_URUSAN"));
				hakmilik.setLuasBersamaan(rs.getDouble("LUAS_BERSAMAAN"));
				hakmilik.setLuasString(rs.getString("LUAS"));
				hakmilik.setKegunaan(rs.getString("KEGUNAAN_TANAH"));
				hakmilik.setNoHakmlik(rs.getString("NO_HAKMILIK"));
				hakmilik.setNoLot(rs.getString("NO_LOT"));
				hakmilik.setIdJenisLot(rs.getLong("ID_LOT"));
				hakmilik.setIdJenisLot(rs.getLong("ID_LOT"));
				hakmilik.setIdLuas(rs.getLong("ID_LUAS"));
				
				kem.setIdKementerian(rs.getLong("ID_KEMENTERIAN"));				
				kem.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));	
				agensi.setKementerian(kem);
				agensi.setIdAgensi(rs.getLong("ID_AGENSI"));
				agensi.setNamaAgensi(rs.getString("NAMA_AGENSI"));
				hakmilik.setAgensi(agensi);
				
				cukai.setCukai(rs.getDouble("CUKAI"));
				cukai.setCukaiTerkini(rs.getDouble("CUKAI_TERKINI"));
				cukai.setCukaiTaliAir(rs.getDouble("CUKAI_TALIAIR"));
				cukai.setCukaiParit(rs.getDouble("CUKAI_PARIT"));
				cukai.setDenda(rs.getDouble("DENDA"));
				cukai.setBayaranLain(rs.getDouble("BAYARAN_LAIN"));
				cukai.setTunggakan(rs.getDouble("TUNGGAKAN"));
				cukai.setPengurangan(rs.getDouble("PENGURANGAN")); // X CUKUP BAYARAN
				cukai.setPengecualian(rs.getDouble("PENGECUALIAN"));
				cukai.setJumlah(rs.getDouble("CUKAI_PERLU_BAYAR"));
				cukai.setIdHakmilikCukai(rs.getLong("ID_HAKMILIKCUKAI"));
				cukai.setStatusBayaran(rs.getString("STATUS_BAYARAN"));
				resit.setNoRujukan(rs.getString("NO_RESIT"));
				resit.setTarikh(new Date(rs.getString("TARIKH_RESIT")));
				bCukai.setResit(resit);
				bCukai.setStatusBayaran(rs.getString("STATUS_BAYARAN"));
				bCukai.setNoRujukan(rs.getString("NO_RUJUKAN"));
				bCukai.setTarikh(new Date(rs.getString("TARIKH_BAYARAN")));
				cukai.setBayaranCukai(bCukai);
				hakmilik.setHakmilikCukai(cukai);			
				
				negeri.setIdNegeri(rs.getLong("ID_NEGERI"));
				negeri.setNamaNegeri(rs.getString("NAMA_NEGERI"));
				hakmilik.setNegeri(negeri);
				
				daerah.setIdDaerah(rs.getLong("ID_DAERAH"));
				daerah.setNamaDaerah(rs.getString("NAMA_DAERAH"));
				hakmilik.setDaerah(daerah);
				
				mukim.setIdMukim(rs.getLong("ID_MUKIM"));
				mukim.setNamaMukim(rs.getString("NAMA_MUKIM"));
				hakmilik.setMukim(mukim);
				
				lot.setKeterangan(rs.getString("KOD_LOT"));
				hakmilik.setRujLot(lot);
				
				jh.setKodJenisHakmilik(rs.getString("KOD_JENIS_HAKMILIK"));
				jh.setIdJenishakmilik(rs.getLong("ID_JENISHAKMILIK"));
				hakmilik.setRujJenisHakmilik(jh);
				hakmilik.setTarikhDaftar(new Date(rs.getString("TARIKH_DAFTAR")));
			
			}
			
		}catch(Exception e){
				e.printStackTrace();
			
	 	} finally {
	 		if (db != null) db.close();
	 	}

		return hakmilik;
	}
	
	public HakMilik getCukaiHakmilik(String idCukaiTemp,String tableName){
		Connection conn = null;
		Db db = null;
		HakMilik hakmilik = new HakMilik();
		SQLRenderer r = null;
 	    try {
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			r = new SQLRenderer();
			r.add("TPH.NO_FAIL_PTG");
			r.add("TPH.NO_FAIL_PTD");
			r.add("TPH.NO_FAIL");
			r.add("RK.ID_KEMENTERIAN");
			r.add("RK.NAMA_KEMENTERIAN");
			r.add("RA.ID_AGENSI");
			r.add("RA.NAMA_AGENSI");
			r.add("RN.ID_NEGERI");
			r.add("RN.NAMA_NEGERI");
			r.add("RD.ID_DAERAH");
			r.add("RD.NAMA_DAERAH");
			r.add("RM.ID_MUKIM");
			r.add("RM.NAMA_MUKIM");
			r.add("RJH.ID_JENISHAKMILIK");
			r.add("RJH.KOD_JENIS_HAKMILIK");
			r.add("TPC.ID_CUKAITEMP ID_HAKMILIKCUKAI");
			r.add("TPC.CUKAI_KENA_BAYAR CUKAI_TERKINI");
			r.add("TPC.CUKAI_TALIAIR");
			r.add("TPC.CUKAI_PARIT");
			r.add("TPC.DENDA");
			r.add("TPC.BAYARAN_LAIN");
			r.add("TPC.TUNGGAKAN");
			r.add("TPC.PENGURANGAN"); //X CUKUP BAYARAN
			r.add("TPC.PENGECUALIAN"); //MENGURANGKAN BAYARAN, TERLEBIH BAYAR
			r.add("TPC.CUKAI_PERLU_BAYAR");
			r.add("TPH.ID_HAKMILIK");
			r.add("TPH.CUKAI");
			r.add("LTRIM(TPH.NO_HAKMILIK,0) NO_HAKMILIK");
			r.add("TPH.LUAS_BERSAMAAN");
			r.add("TPH.LUAS");
			r.add("TPH.ID_LUAS");
			r.add("TPH.KEGUNAAN_TANAH");
			r.add("TPH.TARIKH_DAFTAR");
			r.add("NVL((SELECT KETERANGAN FROM TBLRUJLOT WHERE ID_LOT=TPH.ID_LOT),'') KOD_LOT");
			r.add("TPH.NO_LOT");	
			r.add("TPH.ID_LOT");	
			r.add("NVL((SELECT RU.NAMA_URUSAN FROM TBLRUJURUSAN RU,TBLPFDFAIL F,TBLPERMOHONAN P" +
					" WHERE RU.ID_URUSAN = F.ID_URUSAN " +
					" AND F.ID_FAIL = P.ID_FAIL" +
					" AND P.ID_PERMOHONAN = TPH.ID_PERMOHONAN),'') NAMA_URUSAN");
			r.add("TPH.ID_MUKIM",r.unquote("RM.ID_MUKIM"));
			r.add("RM.ID_DAERAH",r.unquote("RD.ID_DAERAH"));
			r.add("RD.ID_NEGERI",r.unquote("RN.ID_NEGERI"));
			r.add("TPH.ID_JENISHAKMILIK",r.unquote("RJH.ID_JENISHAKMILIK"));
			r.add("TPC.NO_HAKMILIK",r.unquote("TPH.NO_HAKMILIK"));
			r.add("TPH.ID_HAKMILIK",r.unquote("TPA.ID_HAKMILIK"));
			r.add("TPA.STATUS","Y");
			r.add("TPA.ID_AGENSI",r.unquote("RA.ID_AGENSI"));
			r.add("RA.ID_KEMENTERIAN",r.unquote("RK.ID_KEMENTERIAN"));		
			r.add("TPC.ID_CUKAITEMP",r.unquote(idCukaiTemp));
			sql = r.getSQLSelect("TBLHTPCUKAITEMP TPC,TBLHTPHAKMILIK TPH,TBLRUJJENISHAKMILIK RJH " +
				",TBLHTPHAKMILIKAGENSI TPA,TBLRUJAGENSI RA,TBLRUJKEMENTERIAN RK" +
				",TBLRUJMUKIM RM,TBLRUJDAERAH RD,TBLRUJNEGERI RN" +
				"");

			myLog.info("getCukaiHakmilik("+idCukaiTemp+"):sql="+sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Agensi agensi = new Agensi();
				Kementerian kem = new Kementerian();
				HakmilikCukai cukai = new HakmilikCukai();
				Negeri negeri = new Negeri();
				Daerah daerah = new Daerah();
				Mukim mukim = new Mukim();
				Tblrujlot lot = new Tblrujlot();
				Tblrujjenishakmilik jh = new Tblrujjenishakmilik();
				
				hakmilik.setIdHakmilik(rs.getLong("ID_HAKMILIK"));
				hakmilik.setNoFail(rs.getString("NO_FAIL"));
				hakmilik.setNoFailPTD(rs.getString("NO_FAIL_PTD"));
				hakmilik.setNoFailPTG(rs.getString("NO_FAIL_PTG"));
				hakmilik.setPerolehan(rs.getString("NAMA_URUSAN"));
				hakmilik.setLuasBersamaan(rs.getDouble("LUAS_BERSAMAAN"));
				hakmilik.setLuasString(rs.getString("LUAS"));
				hakmilik.setKegunaan(rs.getString("KEGUNAAN_TANAH"));
				hakmilik.setNoHakmlik(rs.getString("NO_HAKMILIK"));
				hakmilik.setNoLot(rs.getString("NO_LOT"));
				hakmilik.setIdJenisLot(rs.getLong("ID_LOT"));
				hakmilik.setTarikhDaftar(rs.getDate("TARIKH_DAFTAR"));
				hakmilik.setIdJenisLot(rs.getLong("ID_LOT"));
				hakmilik.setIdLuas(rs.getLong("ID_LUAS"));
				
				kem.setIdKementerian(rs.getLong("ID_KEMENTERIAN"));				
				kem.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));				
				agensi.setIdAgensi(rs.getLong("ID_AGENSI"));
				agensi.setNamaAgensi(rs.getString("NAMA_AGENSI"));
				agensi.setKementerian(kem);
				hakmilik.setAgensi(agensi);
				
				cukai.setCukai(rs.getDouble("CUKAI"));
				cukai.setCukaiTerkini(rs.getDouble("CUKAI_TERKINI"));
				cukai.setCukaiTaliAir(rs.getDouble("CUKAI_TALIAIR"));
				cukai.setCukaiParit(rs.getDouble("CUKAI_PARIT"));
				cukai.setDenda(rs.getDouble("DENDA"));
				cukai.setBayaranLain(rs.getDouble("BAYARAN_LAIN"));
				cukai.setTunggakan(rs.getDouble("TUNGGAKAN"));
				cukai.setPengurangan(rs.getDouble("PENGURANGAN")); // X CUKUP BAYARAN
				cukai.setPengecualian(rs.getDouble("PENGECUALIAN"));
				cukai.setJumlah(rs.getDouble("CUKAI_PERLU_BAYAR"));
				cukai.setIdHakmilikCukai(rs.getLong("ID_HAKMILIKCUKAI"));
				hakmilik.setHakmilikCukai(cukai);
				
				negeri.setIdNegeri(rs.getLong("ID_NEGERI"));
				negeri.setNamaNegeri(rs.getString("NAMA_NEGERI"));
				hakmilik.setNegeri(negeri);
				
				daerah.setIdDaerah(rs.getLong("ID_DAERAH"));
				daerah.setNamaDaerah(rs.getString("NAMA_DAERAH"));
				hakmilik.setDaerah(daerah);
				
				mukim.setIdMukim(rs.getLong("ID_MUKIM"));
				mukim.setNamaMukim(rs.getString("NAMA_MUKIM"));
				hakmilik.setMukim(mukim);
				
				lot.setKeterangan(rs.getString("KOD_LOT"));
				hakmilik.setRujLot(lot);
				
				jh.setKodJenisHakmilik(rs.getString("KOD_JENIS_HAKMILIK"));
				jh.setIdJenishakmilik(rs.getLong("ID_JENISHAKMILIK"));
				hakmilik.setRujJenisHakmilik(jh);
				
			}
			
		}catch(Exception e){
				e.printStackTrace();
			
	 	} finally {
	 		if (db != null) db.close();
	 	}

		return hakmilik;
	}

//	public String kemaskiniCukai(Hashtable data) throws Exception {
//		Db db = null;
//		Db db2 = null;
//		String sql = "";
//		try{
//			String idHakmilikCukai = String.valueOf(data.get("idHakmilikCukai"));
//					int idHakmilik = (Integer)data.get("idHakmilik");
//				    double cukaiTerkini = Double.parseDouble(Utils.RemoveSymbol(data.get("cukaiTerkini").toString()));
//				    double cukaiTaliAir = Double.parseDouble(Utils.RemoveSymbol(data.get("cukaiTaliAir").toString()));
//				    double cukaiParit = Double.parseDouble(Utils.RemoveSymbol(data.get("cukaiParit").toString()));
//				    double Denda = Double.parseDouble(Utils.RemoveSymbol(data.get("Denda").toString()));
//				    double bayaranLain = Double.parseDouble(Utils.RemoveSymbol(data.get("bayaranLain").toString()));
//				    double Cukai = Double.parseDouble(Utils.RemoveSymbol(data.get("Cukai").toString()));
//				    double pengecualian = Double.parseDouble(Utils.RemoveSymbol(data.get("pengecualian").toString()));
//				    double pengurangan = Double.parseDouble(Utils.RemoveSymbol(data.get("pengurangan").toString()));
//				    String statusBayaran = (String)data.get("statusBayaran");
//				      
//					db = new Db();
//					Statement stmt = db.getStatement();
//					SQLRenderer r = new SQLRenderer();
//					r.update("id_hakmilikcukai", idHakmilikCukai);
//					r.update("id_hakmilik", idHakmilik);
//					r.add("cukai_terkini", cukaiTerkini);
//					r.add("cukai_taliair", cukaiTaliAir);
//					r.add("cukai_parit", cukaiParit);
//					r.add("denda", Denda);
//					r.add("bayaran_lain", bayaranLain);
//					r.add("cukai", Cukai);
//					r.add("pengecualian",pengecualian);
//					r.add("pengurangan", pengurangan);
//					r.add("status", statusBayaran);
//					r.add("status_terkini", "Y");
//					
//					sql = r.getSQLUpdate("Tblhtphakmilikcukai");
//				    mylog.info("FrmCukaiKemaskiniData::Update::Tblhtphakmilikcukai = "+sql);
//				    stmt.executeUpdate(sql);
//				    return (int)idHakmilikCukai;
//				    
//			}  
//			
//		}finally {
//			if (db != null) db.close();
//			
//		}
//		
//	}
	
	@Override
	public void kemaskiniHakmilikCukaiTemp(CukaiTemp data) throws SQLException, DbException{		
		double semasa = (double)data.getCukaiKenaBayar();
		double taliair = (double)data.getCukaiTaliAir();
		double parit = (double)data.getCukaiParit();
		double denda = (double)data.getDenda();
		double cukailain = (double)data.getBayaranLain();
		double tunggakkan = (double)data.getTunggakkan();
		double pengurangan = (double)data.getPengurangan();
		double pengecualian = (double)data.getPengecualian();
		double jumlah = (double)data.getTotalcukai();		
		double lebihan = (double)data.getLebihan();
		long id = (long)data.getIdCukaiTemp();
		long idmasuk = (long)data.getIdMasuk();
		String idkemaskini = data.getIdKemaskini();		
		Date now = new Date(); 
		SimpleDateFormat formatter =  new SimpleDateFormat("yyyy");
		String TBF = "to_date('" + formatter.format(now) + "','yyyy')";
		
		Db db = null;
	    String sql = "";
	    try{
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	SQLRenderer r = new SQLRenderer();		  
	    	r.update("ID_CUKAITEMP", id);
	    	r.add("CUKAI_KENA_BAYAR", Utils.RemoveComma(String.valueOf(semasa)));
	    	r.add("CUKAI_TALIAIR", Utils.RemoveComma(String.valueOf(taliair)));
	    	r.add("CUKAI_PARIT", Utils.RemoveComma(String.valueOf(parit)));	    	
	    	r.add("DENDA", Utils.RemoveComma(String.valueOf(denda)));
	    	r.add("CUKAI_LAIN", Utils.RemoveComma(String.valueOf(cukailain)));
	    	r.add("TUNGGAKAN", Utils.RemoveComma(String.valueOf(tunggakkan)));
//	    	r.add("LEBIHAN", Utils.RemoveComma(String.valueOf(lebihan)));
	    	r.add("PENGURANGAN", Utils.RemoveComma(String.valueOf(pengurangan)));
	    	r.add("PENGECUALIAN", Utils.RemoveComma(String.valueOf(pengecualian)));
	    	r.add("CUKAI_PERLU_BAYAR", Utils.RemoveComma(String.valueOf(jumlah)));
	    	r.add("ID_MASUK", idmasuk);
	    	r.add("ID_KEMASKINI", idkemaskini);
	     	r.add("NO_RESIT", data.getNoResit());
	     	if(!data.getTarikhResit().equals("")){
	     		String tarikhResit = "to_date('" + format.format(data.getTarikhResitDate()) + "','dd/MM/yyyy')";
	     		r.add("TARIKH_RESIT",r.unquote(tarikhResit));		
	     	}
	    	
	    	sql = r.getSQLUpdate("tblhtpcukaitemp");
	    	myLog.info("kemaskiniHakmilikCukaiTemp::Insert::tblhtpcukaitemp = "+sql);
	    	stmt.executeUpdate(sql);
	    
	    }finally {
	      if (db != null) db.close();
	      
	    }	
		
	}
	
	@Override
	public Vector salinCukai(String idnegeri,String iddaerah,String idmukim,String socTahun) {
		Db db = null;
//		Vector<CukaiTemp> v = new Vector<CukaiTemp>();
		Vector<HakMilik> v = new Vector<HakMilik>();		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			String sql ="SELECT "+
				" ID_PERMOHONAN " +
				" ,CUKAI_KENA_BAYAR,CUKAI_TALIAIR,CUKAI_PARIT,CUKAI_LAIN,BAYARAN_LAIN, PENGURANGAN " +
				" ,TUNGGAKAN,PENGECUALIAN,DENDA,LEBIHAN,CUKAI_DIBAYAR,CUKAI_PERLU_BAYAR " +
				" ,ID_NEGERI,ID_DAERAH,ID_MUKIM,NAMA_MUKIM " +
				" ,ID_LOT,NO_LOT,NO_LOTUPLOAD,ID_JENISHAKMILIK,NVL(NO_HAKMILIK,0) NO_HAKMILIK,NO_HAKMILIKUPLOAD " +
				" ,KEGUNAAN_TANAH " +
				" ,NVL((SELECT TPHC.ID_HAKMILIKCUKAI FROM TBLHTPHAKMILIK TPH,TBLHTPHAKMILIKCUKAI TPHC WHERE " +
				" 	TPHC.ID_HAKMILIK = TPH.ID_HAKMILIK AND TPHC.STATUS='S' "+
				"	AND TPH.ID_NEGERI=TPCT.ID_NEGERI AND TPH.ID_DAERAH=TPCT.ID_DAERAH AND TPH.ID_MUKIM=TPCT.ID_MUKIM "+
				"	AND TPH.NO_HAKMILIK = TPCT.NO_HAKMILIK "+
				" ),0) ID_HAKMILIKCUKAI " +
				" FROM TBLHTPCUKAITEMP TPCT" +
				" WHERE ";
			if(!socTahun.equals("0")){
				sql += " TAHUN = "+(Integer.parseInt(socTahun)-1); 
			}
			myLog.info("salinCukai:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				HakMilik temp = new HakMilik();
				HakmilikCukai cukai = new HakmilikCukai();
				CukaiTemp cTemp = new CukaiTemp();
				//temp.setIdHakmilik(rs.getLong("ID_HAKMILIK"));
				temp.setIdPermohonan(rs.getLong("ID_PERMOHONAN"));
				temp.setIdNegeri(rs.getLong("ID_NEGERI"));
				temp.setIdDaerah(rs.getLong("ID_DAERAH"));
				temp.setIdMukim(rs.getLong("ID_MUKIM"));
				temp.setCatatan(rs.getString("NAMA_MUKIM"));
				temp.setKegunaan(rs.getString("KEGUNAAN_TANAH"));
				temp.setIdJenisLot(rs.getLong("ID_LOT"));
				temp.setNoLot(rs.getString("NO_LOT"));
				temp.setNoFailPTD(rs.getString("NO_LOTUPLOAD"));
				temp.setIdJenisHakmilik(rs.getLong("ID_JENISHAKMILIK"));
				temp.setNoHakmlik(rs.getString("NO_HAKMILIK"));
				temp.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				temp.setNoFailPTG(rs.getString("NO_HAKMILIKUPLOAD"));			
				cukai.setCukaiTerkini(rs.getDouble("CUKAI_KENA_BAYAR"));
				cukai.setCukaiTaliAir(rs.getDouble("CUKAI_TALIAIR"));
				cukai.setCukaiParit(rs.getDouble("CUKAI_PARIT"));
				cukai.setCukailain(rs.getDouble("CUKAI_LAIN"));
				cukai.setTunggakan(rs.getDouble("TUNGGAKAN"));
				cukai.setDenda(rs.getDouble("DENDA"));
				cukai.setPengurangan(rs.getDouble("PENGURANGAN"));
				cukai.setPengecualian(rs.getDouble("PENGECUALIAN"));
				cukai.setLebihan(rs.getDouble("LEBIHAN"));
				cukai.setJumlah(rs.getDouble("CUKAI_PERLU_BAYAR"));
				cukai.setKodBayaranCukai(socTahun);
				cukai.setIdHakmilikCukai(rs.getLong("ID_HAKMILIKCUKAI"));
				temp.setHakmilikCukai(cukai);
				cTemp.setTahun(socTahun);
				temp.setHakmilikCTemp(cTemp);
				v.addElement(temp);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.close();
		}
		//Insert into TBLHTPHAKMILIK
		prosesSalinan(v);
		return v;
		
	}
	
	private void prosesSalinan(Vector<HakMilik> v){
		if(v != null && !v.isEmpty()){
			for(HakMilik temp : v){
				salinanCukaiBaru(temp);
			}
		}
	}
	
	public HakMilik salinanCukaiBaru(HakMilik hakmilik){
		Db db = null;
		Connection conn = null;
		String sql =null;
		try{
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			Hashtable data = null;
			data = new Hashtable();
			String tunggakan =""; 
			String denda =""; 
			String taliair2 =""; 
			String taliparit2  ="";
			String lebihan ="";
			String cukai_perlubayar ="";
			String pengurangan ="";
			String cukaiKenaBayarLama ="";
			String cukaiJumlah ="";

			simpanCukaiHakmilikTerperinci(hakmilik);			
			if(isCukaiTemp(hakmilik)==false){
				
				long idCukaiTemp = DB.getNextID(db,"TBLHTPCUKAITEMP_SEQ"); 
				r.add("ID_CUKAITEMP", idCukaiTemp);
				r.add("ID_PERMOHONAN",hakmilik.getIdPermohonan());
				r.add("ID_NEGERI",hakmilik.getIdNegeri());
				r.add("ID_DAERAH",hakmilik.getIdDaerah());
				r.add("ID_MUKIM",hakmilik.getIdMukim());
				r.add("NAMA_MUKIM", hakmilik.getCatatan());
				r.add("KEGUNAAN_TANAH", hakmilik.getKegunaan());
				r.add("ID_LOT", hakmilik.getIdJenisLot());
				r.add("NO_LOT",hakmilik.getNoLot());
				r.add("NO_LOTUPLOAD", hakmilik.getNoFailPTD());
	//			r.add("ID_HAKMILIK",hakmilik.getIdHakmilik());
				r.add("ID_JENISHAKMILIK",hakmilik.getIdJenisHakmilik());
				r.add("NO_HAKMILIK",hakmilik.getNoHakmlik());
				r.add("NO_HAKMILIKUPLOAD",hakmilik.getNoFailPTG());
				r.add("CUKAI_KENA_BAYAR",hakmilik.getHakmilikCukai().getCukaiTerkini());
				r.add("CUKAI_TALIAIR",hakmilik.getHakmilikCukai().getCukaiTaliAir());
				r.add("CUKAI_PARIT", hakmilik.getHakmilikCukai().getCukaiParit());
				r.add("CUKAI_LAIN", hakmilik.getHakmilikCukai().getCukailain());
				r.add("TUNGGAKAN",hakmilik.getHakmilikCukai().getTunggakan());
				r.add("DENDA",hakmilik.getHakmilikCukai().getDenda());
				r.add("PENGURANGAN", hakmilik.getHakmilikCukai().getPengurangan());
				r.add("PENGECUALIAN", hakmilik.getHakmilikCukai().getPengecualian());			
				r.add("LEBIHAN", hakmilik.getHakmilikCukai().getLebihan());			
				r.add("CUKAI_PERLU_BAYAR",hakmilik.getHakmilikCukai().getJumlah());
				r.add("TAHUN", hakmilik.getHakmilikCukai().getKodBayaranCukai());
				//r.add("ID_MASUK",hakmilik.getIdMasuk());
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				myLog.info("2231="+isCukaiTemp(hakmilik));
				sql = r.getSQLInsert("TBLHTPCUKAITEMP");
				stmt.executeUpdate(sql);	
				conn.commit();
			} 
			
		}catch(Exception e){
			try{
				conn.rollback();
			}catch(SQLException sqle){
				sqle.printStackTrace();
			}
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		
		return hakmilik;
	}
	
	//TBLHATPHAKMILIKTERPERINCI
	public void simpanCukaiHakmilikTerperinci(HakMilik hakmilik) throws Exception{		
		Db db = null;
		Connection conn = null;
	    String sql = "";
	    try	{	    	
	    	db = new Db();
	    	conn = db.getConnection();
			if(isCukaiTerperinci(hakmilik)==false){
		    	long IDCUKAITERPERINCI = DB.getNextID("tblhtpcukaiterperinci_SEQ");
		    	conn.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	SQLRenderer r = new SQLRenderer();		  
		    	r.add("ID_CUKAITERPERINCI", IDCUKAITERPERINCI);
		    	r.add("TAHUN", hakmilik.getHakmilikCukai().getKodBayaranCukai());
		    	r.add("TUNGGAKAN", hakmilik.getHakmilikCukai().getTunggakan());
		    	r.add("DENDA", hakmilik.getHakmilikCukai().getDenda());
		    	r.add("CUKAI_TALIAIR", hakmilik.getHakmilikCukai().getCukaiTaliAir());
		    	r.add("CUKAI_PARIT", hakmilik.getHakmilikCukai().getCukaiParit());
		    	r.add("CUKAI_KENA_BAYAR", hakmilik.getHakmilikCukai().getCukaiTerkini());
		    	r.add("PENGURANGAN", hakmilik.getHakmilikCukai().getPengurangan());
		    	r.add("PENGECUALIAN", hakmilik.getHakmilikCukai().getPengecualian());
		    	r.add("CUKAI_PERLU_BAYAR", hakmilik.getHakmilikCukai().getJumlah());
		    	r.add("ID_HAKMILIKCUKAI", hakmilik.getHakmilikCukai().getIdHakmilikCukai());
		    	r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
		    	sql = r.getSQLInsert("TBLHTPCUKAITERPERINCI");
	//	    	myLog.info("simpanCukaiHakmilikTerperinci::Insert::TBLHTPCUKAITERPERINCI = "+sql);
		    	stmt.executeUpdate(sql);
				conn.commit();
			}

	    }catch(Exception e){
	    	conn.rollback();
	    	e.printStackTrace();
	    
	    }finally {
	    	if (db != null) db.close();
		    if (conn != null) conn.close();
	    
	    }
	    	
	}

	@Override
	public HakMilik simpanCukaiHakmilikTemp(HakMilik hakmilik){
		Db db = null;
		Connection conn = null;
		String sql =null;
		try{
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			Hashtable data = null;
			data = new Hashtable();
			if(isCukaiTemp(hakmilik)==false){
				long idCukaiTemp = DB.getNextID(db,"TBLHTPCUKAITEMP_SEQ"); 
				r.add("ID_CUKAITEMP", idCukaiTemp);
				r.add("ID_PERMOHONAN",hakmilik.getIdPermohonan());
				r.add("ID_NEGERI",hakmilik.getIdNegeri());
				r.add("ID_DAERAH",hakmilik.getIdDaerah());
				r.add("ID_MUKIM",hakmilik.getIdMukim());
				r.add("NAMA_MUKIM", hakmilik.getHakmilikCTemp().getRujMukim().getNamaMukim());
				r.add("KEGUNAAN_TANAH", hakmilik.getKegunaan());
				r.add("ID_LOT", hakmilik.getIdJenisLot());
				r.add("NO_LOT",hakmilik.getNoLot());
				r.add("NO_LOTUPLOAD", hakmilik.getHakmilikCTemp().getNoLot());
	//			r.add("ID_HAKMILIK",hakmilik.getIdHakmilik());
				r.add("ID_JENISHAKMILIK",hakmilik.getIdJenisHakmilik());
				r.add("NO_HAKMILIK",hakmilik.getNoHakmilik());
				r.add("NO_HAKMILIKUPLOAD",hakmilik.getHakmilikCTemp().getNoHakmilik());
				r.add("CUKAI_KENA_BAYAR",hakmilik.getHakmilikCTemp().getCukaiKenaBayar());
				r.add("CUKAI_TALIAIR",hakmilik.getHakmilikCTemp().getCukaiTaliAir());
				r.add("CUKAI_PARIT", hakmilik.getHakmilikCTemp().getCukaiParit());
				r.add("CUKAI_LAIN", hakmilik.getHakmilikCTemp().getCukailain());
				r.add("TUNGGAKAN",hakmilik.getHakmilikCTemp().getTunggakkan());
				r.add("DENDA",hakmilik.getHakmilikCTemp().getDenda());
				r.add("PENGURANGAN", hakmilik.getHakmilikCTemp().getPengurangan());
				r.add("PENGECUALIAN", hakmilik.getHakmilikCTemp().getPengecualian());			
				r.add("LEBIHAN", hakmilik.getHakmilikCTemp().getLebihan());			
				r.add("CUKAI_PERLU_BAYAR",hakmilik.getHakmilikCTemp().getTotalcukai());
				r.add("TAHUN", hakmilik.getHakmilikCTemp().getTahun());
				r.add("ID_MASUK",hakmilik.getIdMasuk());
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));	  
				sql = r.getSQLInsert("TBLHTPCUKAITEMP");
				myLog.info("simpanCukaiHakmilikTemp:sql="+sql);
				stmt.executeUpdate(sql);	
			}
			 
			conn.commit();
			
		}catch(Exception e){
			try{
				conn.rollback();
			}catch(SQLException sqle){
				sqle.printStackTrace();
			}
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		
		return hakmilik;
	}	
	
	public boolean isCukaiTerperinci(HakMilik hakmilik){
		Connection conn = null;
		Db db = null;
		boolean returnValue = false;
 	    try {
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			sql = " " +
					" SELECT ID_CUKAITERPERINCI "+
					" FROM TBLHTPCUKAITERPERINCI "+
					" WHERE "+
					" ID_HAKMILIKCUKAI = "+hakmilik.getHakmilikCukai().getIdHakmilikCukai() +		
					" AND TAHUN = '"+hakmilik.getHakmilikCTemp().getTahun()+"'"+
					" ";

//			myLog.info("2383:sql="+sql);
		rs = stmt.executeQuery(sql);
			returnValue = rs.next();
			 
		}catch(Exception e){
			e.printStackTrace();
		
		} finally {
			if (db != null) db.close();
		}

		return returnValue;
	}
	
	public boolean isCukaiTemp(HakMilik hakmilik){
//		myLog.info("2399:"+hakmilik.getNoHakmilik()+","+hakmilik.getIdNegeri());
//		myLog.info("2399:"+hakmilik.getIdDaerah()+","+hakmilik.getIdMukim());
//		myLog.info("2399:"+hakmilik.getHakmilikCTemp().getTahun());
		Connection conn = null;
		Db db = null;
		boolean returnValue = false;
 	    try {
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			sql = " " +
					" SELECT ID_CUKAITEMP "+
					" FROM TBLHTPCUKAITEMP "+
					" WHERE "+
					" NO_HAKMILIK = '"+hakmilik.getNoHakmilik()+"'"+
					" AND ID_NEGERI = "+hakmilik.getIdNegeri()+
					" AND ID_DAERAH = "+hakmilik.getIdDaerah()+
					" AND ID_MUKIM = "+hakmilik.getIdMukim()+		
					" AND TAHUN = '"+hakmilik.getHakmilikCTemp().getTahun()+"'"+
					" ";
//			myLog.info("2418:sql="+sql);
			rs = stmt.executeQuery(sql);
			returnValue = rs.next();
			 
		}catch(Exception e){
			e.printStackTrace();
		
		} finally {
			if (db != null) db.close();
		}

		return returnValue;
	}
	
	public ekptg.model.htp.entity.HakMilik getIdHakmilikCukai(HakMilik hakmilikLama)throws Exception {	
	
		Connection conn = null;
		Db db = null;
		HakMilik hakmilik = null;
		SQLRenderer r = null;
		    try {
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			r = new SQLRenderer();
			r.add("TPHI.ID_PERMOHONAN");
			r.add("TPHCI.ID_HAKMILIKCUKAI");
			r.add("TPHI.ID_HAKMILIK",r.unquote("TPHCI.ID_HAKMILIK"));
			r.add("TPHCI.STATUS","S");
			r.add("TPHI.ID_MUKIM",hakmilikLama.getIdMukim());
			r.add("TPHI.ID_DAERAH",hakmilikLama.getIdDaerah());
			r.add("TPHI.ID_NEGERI",hakmilikLama.getIdNegeri());			
			r.add("LTRIM(TPHI.NO_HAKMILIK,0)",hakmilikLama.getNoHakmilik());
			r.add("TPHI.ID_JENISHAKMILIK",hakmilikLama.getIdJenisHakmilik());
			sql = r.getSQLSelect("TBLHTPHAKMILIK TPHI,TBLHTPHAKMILIKCUKAI TPHCI");
	
			//myLog.info("getIdHakmilikCukai:sql="+sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				hakmilik = new HakMilik(); 
				HakmilikCukai hc = new HakmilikCukai();
				hakmilik.setIdPermohonan(rs.getLong("ID_PERMOHONAN"));
				hc.setIdHakmilikCukai(rs.getLong("ID_HAKMILIKCUKAI"));
				hakmilik.setHakmilikCukai(hc);			
			}
			
		}catch(Exception e){
				e.printStackTrace();
			
	 	} finally {
	 		if (db != null) db.close();
	 		
	 	}
	
		return hakmilik;
	}

	public Vector<Hashtable<String, Comparable>> getListNegeri(String idPeringkatbayaran) throws Exception {
		Db db = null;
		String sql = "SELECT RN.ID_NEGERI,RN.NAMA_NEGERI,a.id_daerah, a.kod_Daerah, a.nama_Daerah, SUM(c.jumlah_cukai) jumlah_cukai" +
		   			 " ,C.ID_CUKAIUTAMA " +
		   			 " FROM tblrujdaerah a, tblhtpperingkatbayaran b, tblhtpcukaiutama c,TBLRUJNEGERI RN " +
		   			 " WHERE b.id_negeri = c.id_negeri " +
		   			 " and c.id_daerah = a.id_daerah " +
		   			 " AND A.ID_NEGERI = RN.ID_NEGERI " +
		  			 " and b.id_peringkatbayaran = c.id_peringkatbayaran " +
		   			 " and b.id_peringkatbayaran ='"+idPeringkatbayaran+"' " +
		   			 " GROUP BY " +
		   			 " RN.ID_NEGERI,RN.NAMA_NEGERI,a.id_daerah, a.kod_Daerah, a.nama_Daerah,C.ID_CUKAIUTAMA";
		myLog.info("getListNegeri:"+sql);
		try {
			db = new Db();
		      Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>(); 
		      Statement stmt = db.getStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable<String, Comparable> h;
		      while (rs.next()) {
		    	  h = new Hashtable<String, Comparable>();
		    	  h.put("idNegeri",rs.getLong("ID_NEGERI")); 
		    	  h.put("idDaerah",rs.getLong("id_daerah")); 
		    	  h.put("idCukai",rs.getString("id_cukaiutama")); 
		    	  h.put("kod_Daerah",rs.getString("kod_Daerah"));
		    	  //h.put("namaDaerah",rs.getString("nama_Daerah"));  
		    	  h.put("nama",rs.getString("NAMA_NEGERI"));  
		    	  h.put("jumlahCukai",Util.formatDecimal(rs.getDouble("jumlah_cukai")));  
		    	  v.addElement(h);
		      }
		      
		      return v;
		      
		    } finally {
		      if (db != null) db.close();
		      
		    }
		    
	  }	

	public Vector<Hashtable<String, Comparable>> XgetListNegeri(String idPeringkatbayaran,String tahun) throws Exception {
		Db db = null;
		String sql = "SELECT RN.ID_NEGERI,RN.NAMA_NEGERI,a.id_daerah, a.kod_Daerah, a.nama_Daerah, SUM(c.jumlah_cukai) jumlah_cukai" +
		   			 " ,C.ID_CUKAIUTAMA " +
		   			 " FROM tblrujdaerah a, tblhtpperingkatbayaran b, tblhtpcukaiutama c,TBLRUJNEGERI RN " +
		   			 " WHERE b.id_negeri = c.id_negeri " +
		   			 " and c.id_daerah = a.id_daerah " +
		   			 " AND A.ID_NEGERI = RN.ID_NEGERI " +
		  			 " and b.id_peringkatbayaran = c.id_peringkatbayaran " +
		   			 " and b.id_peringkatbayaran ='"+idPeringkatbayaran+"' " +
			       	 " AND C.TAHUN='"+tahun+"' "+
		   			 " GROUP BY " +
		   			 " RN.ID_NEGERI,RN.NAMA_NEGERI,a.id_daerah, a.kod_Daerah, a.nama_Daerah,C.ID_CUKAIUTAMA";
		myLog.info("getListNegeri("+tahun+","+idPeringkatbayaran+"):"+sql);
		try {
			db = new Db();
		      Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>(); 
		      Statement stmt = db.getStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable<String, Comparable> h;
		      while (rs.next()) {
		    	  h = new Hashtable<String, Comparable>();
		    	  h.put("idNegeri",rs.getLong("ID_NEGERI")); 
		    	  h.put("idDaerah",rs.getLong("id_daerah")); 
		    	  h.put("idCukai",rs.getString("id_cukaiutama")); 
		    	  h.put("kod_Daerah",rs.getString("kod_Daerah"));
		    	  //h.put("namaDaerah",rs.getString("nama_Daerah"));  
		    	  h.put("nama",rs.getString("NAMA_NEGERI"));  
		    	  h.put("jumlahCukai",Util.formatDecimal(rs.getDouble("jumlah_cukai")));  
		    	  v.addElement(h);
		      }
		      
		      return v;
		      
		    } finally {
		      if (db != null) db.close();
		      
		    }
		    
	  }		

	public Vector<Hashtable<String, Comparable>> getListNegeri(String idPeringkatbayaran,String tahun) throws Exception {
		Db db = null;
		String sql = "SELECT RN.ID_NEGERI,RN.NAMA_NEGERI " +
				//",RN.KOD_NEGERI,a.id_daerah, a.kod_Daerah, a.nama_Daerah" +
				", SUM(c.jumlah_cukai) jumlah_cukai" +
		   		//	 " ,C.ID_CUKAIUTAMA " +
		   			 " FROM tblrujdaerah a, tblhtpperingkatbayaran b, tblhtpcukaiutama c,TBLRUJNEGERI RN " +
		   			 " WHERE b.id_negeri = c.id_negeri " +
		   			 " and c.id_daerah = a.id_daerah " +
		   			 " AND A.ID_NEGERI = RN.ID_NEGERI " +
		  			 " and b.id_peringkatbayaran = c.id_peringkatbayaran " +
		   			 " and b.id_peringkatbayaran ='"+idPeringkatbayaran+"' " +
			       	 " AND C.TAHUN='"+tahun+"' "+
		   			 " GROUP BY " +
		   			 " RN.ID_NEGERI,RN.NAMA_NEGERI" +
		   			 //",a.id_daerah, a.kod_Daerah, a.nama_Daerah,C.ID_CUKAIUTAMA" +
		   			 "";
		myLog.info("getListNegeri("+tahun+","+idPeringkatbayaran+"):"+sql);
		try {
			db = new Db();
		      Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>(); 
		      Statement stmt = db.getStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable<String, Comparable> h;
		      while (rs.next()) {
		    	  h = new Hashtable<String, Comparable>();
		    	  h.put("idNegeri",rs.getLong("ID_NEGERI")); 
		    	  //h.put("idDaerah",rs.getLong("ID_NEGERI")); 
		    	  //h.put("idCukai",rs.getString("ID_CUKAIUTAMA")); 
		    	  //h.put("kod_Daerah",rs.getString("KOD_NEGERI"));
		    	  //h.put("namaDaerah",rs.getString("nama_Daerah"));  
		    	  h.put("nama",rs.getString("NAMA_NEGERI"));  
		    	  h.put("jumlahCukai",Util.formatDecimal(rs.getDouble("JUMLAH_CUKAI")));  
		    	  v.addElement(h);
		      }		      
		      return v;
		      
		    } finally {
		      if (db != null) db.close();
		      
		    }
		    
	  }		
	

	public BayaranCukai bayaranView(String idBayaranCukai) throws Exception {
		Db db = null;
		BayaranCukai bCukai = null;
		Bayaran bayaran = null;
		Baucer baucer = null;
		Resit resit = null;
		String sql = "" +
				"SELECT DISTINCT thbc.id_bayarancukai, thbc.id_baucer, thbc.id_peringkatbayaran, thbc.tarikh_bayaran, "+
			" thbc.nama_bank, thbc.amaun, thbc.no_rujbayaran,THB.NO_RESIT,THB.TARIKH_RESIT, thb.id_negeri " +
			//" trd.nama_daerah" +
			" ,case "+
			" 	when THPB.PERINGKAT_BAYARAN=1 then TRN.NAMA_NEGERI "+
			" 	when THPB.PERINGKAT_BAYARAN=2 then TRD.NAMA_DAERAH "+
			" end NAMA_DAERAH "+
			", trd.id_daerah  "+
			" FROM tblhtpbayarancukai thbc, tblhtpbaucer THB, tblrujnegeri trn, tblrujdaerah trd, tblhtpperingkatbayaran thpb "+
			" WHERE "+
		 	" thbc.id_baucer = thb.id_baucer"+
			" AND thbc.id_bayarancukai = '"+ idBayaranCukai +"'"+
			" AND thbc.id_peringkatbayaran = thpb.id_peringkatbayaran"+
			" AND thb.id_negeri = trn.id_negeri " +
			" AND thb.id_daerah = trd.id_daerah";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				bCukai = new BayaranCukai();
				bayaran = new Bayaran();
				baucer = new Baucer();
				resit = new Resit();
				Negeri negeri = new Negeri();
				Daerah daerah = new Daerah();
			
				bCukai.setId(rs.getLong("id_bayarancukai"));
				baucer.setId(rs.getLong("id_baucer"));
				bCukai.setBaucer(baucer);
				bCukai.setIdPeringkat(rs.getLong("id_peringkatbayaran"));
				bayaran.setTarikhBayaran(rs.getDate("tarikh_bayaran"));
				bayaran.setTarikhBayaranStr(format.format(rs.getDate("tarikh_bayaran")));
				bayaran.setBank(rs.getString("nama_bank"));
				bayaran.setJumlah(rs.getDouble("amaun"));
				bayaran.setNoRujukan(rs.getString("no_rujbayaran"));
				bCukai.setBayaran(bayaran);
				resit.setNoRujukan(rs.getString("no_resit"));
				resit.setTarikh(rs.getDate("tarikh_resit"));
				bCukai.setResit(resit);
				negeri.setIdNegeri(rs.getLong("id_negeri"));
				bCukai.setNegeri(negeri);
				daerah.setIdDaerah(rs.getLong("id_daerah"));
				daerah.setNamaDaerah(rs.getString("nama_daerah"));
				bCukai.setDaerah(daerah);
			}
			return bCukai;
		      
	    } finally {
	      if (db != null) db.close();
	      
	    }
		
	}	
	
	public boolean isCukaiUtama(String idPeringkat,String tahun )throws Exception {
		Db db = null;
		String sql = "";
		boolean returnValue= false;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.JUMLAH_CUKAI,A.ID_DAERAH,A.ID_PERINGKATBAYARAN "
				+ " FROM TBLHTPCUKAIUTAMA A"
				+ " WHERE A.TAHUN ='" +tahun+"'"
				+ " AND A.ID_PERINGKATBAYARAN=" +idPeringkat;			
			ResultSet rs = stmt.executeQuery(sql);
			returnValue = rs.next();

		} finally {
			if (db != null)
				db.close();
		}
		
		return returnValue;
	}

	public boolean isCukaiUtama(String idPeringkat,String idDaerah,String tahun )throws Exception {
		Db db = null;
		String sql = "";
		boolean returnValue= false;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.JUMLAH_CUKAI,A.ID_DAERAH,A.ID_PERINGKATBAYARAN "
				+ " FROM TBLHTPCUKAIUTAMA A"
				+ " WHERE A.TAHUN ='" +tahun+"'"
				+ " AND A.ID_DAERAH = '"+idDaerah+"'"
				+ " AND A.ID_PERINGKATBAYARAN=" +idPeringkat;			
			ResultSet rs = stmt.executeQuery(sql);
			returnValue = rs.next();

		} finally {
			if (db != null)
				db.close();
		}
		
		return returnValue;
	}
	
	public boolean isCukaiPeringkatBayaran(String idPermohonan,String tahun )throws Exception {
		Db db = null;
		String sql = "";
		boolean returnValue= false;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_PERMOHONAN,A.PERINGKAT_BAYARAN "
				+ " FROM TBLHTPPERINGKATBAYARAN A"
				+ " WHERE A.TAHUN_CUKAI ='" +tahun+"'"
				//+ " AND A.ID_PERINGKATBAYARAN=" +idPeringkat
				+" AND A.ID_PERMOHONAN = "+idPermohonan
				+" ";			
			ResultSet rs = stmt.executeQuery(sql);
			returnValue = rs.next();

		} finally {
			if (db != null)
				db.close();
		}
		
		return returnValue;
	}

	@Override
	public void hapusBayaranCukai(String pk) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			//ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();	
			
			r.add("ID_BAYARANCUKAI", r.unquote(pk));
			sql = r.getSQLDelete("tblhtpbayarancukai");
			myLog.info(sql);
			stmt.executeUpdate(sql);

			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah hapus data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	    //return pk;
	    
	}		
	public Vector<Hashtable<String, String>> getSenaraiNegeriXPenyata(String tahun) throws Exception {
		Db db = null;
		String sql = "SELECT RN.ID_NEGERI,RN.NAMA_NEGERI " +
		   			 " FROM TBLRUJNEGERI RN " +
		   			 " WHERE RN.ID_NEGERI NOT IN ( " +
		   			 " SELECT RNI.ID_NEGERI " +
		   			 " FROM TBLHTPCUKAITEMP RNI  " +
		  			 " WHERE RNI.TAHUN= " +tahun +
		   			 " GROUP BY RNI.ID_NEGERI " +
		   			 " ) AND RN.ID_NEGERI <>0 " +
		   			 "";
		try {
			db = new Db();
		      Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>(); 
		      Statement stmt = db.getStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable<String, String> h;
		      while (rs.next()) {
		    	  h = new Hashtable<String, String>();
		    	  h.put("idNegeri",rs.getString("ID_NEGERI")); 
		    	  h.put("nama",rs.getString("NAMA_NEGERI"));  
		    	  v.addElement(h);
		      }		      
		      return v;
		      
		    } finally {
		      if (db != null) db.close();
		      
		    }
		    
	  }	
		  
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}	
	
	
}

