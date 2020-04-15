package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.File;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujjenistanah;

public class FrmSenaraiFailTerimaPohonData {

	private static FrmSenaraiFailTerimaPohonData instance = null;
	private Vector<Hashtable<String, String>> list = null;
	private Vector<Hashtable<String, String>> listPPT = null;
	private static Logger myLog = Logger.getLogger(FrmSenaraiFailTerimaPohonData.class);
	private static SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
	private static Tblrujjenistanah jt = null;
	public String strNoFail = "";
	
	public static FrmSenaraiFailTerimaPohonData getInstance() {
	    if (instance == null) instance = new FrmSenaraiFailTerimaPohonData();
	    return instance;
	    
	}
	//AZAM ADD TRANSACTION
	public String simpanPermohonan(Hashtable<String, String> data,String idUser) throws Exception {
		 Db db = null;
		 Connection conn = null;
		 long idFail = 0L;
		 String sql = "";
		 try {
			 
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
	          
			String kodNegeriMampu = "";
			String kodKementerianMampu = "";
			String kod_urusan = "";
			String seksyen = "3";
			//long idFail = (Long) data.get("id_Fail");
			int idnegeri = Utils.parseInt((String)data.get("id_Negeri"));
			int iddaerah = Utils.parseInt((String)data.get("id_Daerah"));
			int flagFail = Integer.parseInt(String.valueOf(data.get("flag_Fail")));
			int idkementerian = Utils.parseInt((String)data.get("id_Kementerian"));
			int idagensi = Utils.parseInt((String)data.get("id_Agensi"));
			int idurusan = Utils.parseInt((String)data.get("id_Urusan"));
			int idsuburusan = Utils.parseInt((String)data.get("id_Suburusan"));
			
			String taraf = (String) data.get("id_Tarafkeselamatan");
			String tajukFail = (String) data.get("tajuk_Fail");
			String status = (String) data.get("id_Status");
			//String lokasi = (String) data.get("id_Lokasifail");
			//String faharasat = (String) data.get("id_Faharasat");
			
	        //PFDFAIL	          
			idFail = DB.getNextID(db,"TBLPFDFAIL_SEQ");
			int fileSeq = 0;
			//fileSeq = File.getSeqNo(3,idurusan,idkementerian,idnegeri);
			fileSeq = File.getSeqNo(db,3,idurusan,idkementerian,idnegeri,0,false,false,0,0);

			kodNegeriMampu = getNegeriByMampu(idnegeri);
			kodKementerianMampu = getKementerianByMampu(idkementerian);
			kod_urusan = getKodUrusan(idurusan);
			//KTPK/101/878/(TK)/1/13-244
			String noFail = "JKPTG/101/";
			String jenisTanah = "";
			if(idurusan==1){
				jt = (Tblrujjenistanah)FrmUtilData.getRujJenisTanah((String)data.get("StatusTanah"));
				if(jt.getKodJenistanah()!=""){
					jenisTanah = "("+jt.getKodJenistanah()+")/";			
				}
			}
			noFail += kod_urusan +"/"+jenisTanah+kodKementerianMampu+"/"+kodNegeriMampu+"-"+fileSeq;
			strNoFail = noFail;	
			//String noFailLama = (String) data.get("no_Fail");
			r.add("id_Fail", idFail);
			r.add("id_Tarafkeselamatan", taraf);
			r.add("id_Seksyen", seksyen);
			r.add("id_Urusan", idurusan);
			r.add("id_Suburusan", idsuburusan);
			r.add("tarikh_Daftar_Fail", r.unquote("sysdate"));
			r.add("tajuk_Fail", tajukFail);
			r.add("no_Fail", noFail);
			r.add("no_fail_root", noFail);
			r.add("id_Lokasifail", "1");
			r.add("id_Negeri", idnegeri);
			r.add("id_Kementerian", idkementerian);
			r.add("id_Faharasat", "1");
			r.add("flag_Fail", flagFail);
			r.add("id_Status", status);
			r.add("catatan", "TIADA");
			r.add("id_Masuk", idUser);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_Kemaskini", idUser);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			
			sql = r.getSQLInsert("tblpfdfail");
			myLog.debug("sql:"+ sql);
			stmt.executeUpdate(sql);
	        
			//END PFD FAIL
			
			//TBLPERMOHONAN
			
		      long idPermohonan = DB.getNextID(db,"TBLPERMOHONAN_SEQ");
		      long idHtppermohonan = DB.getNextID(db,"TBLHTPPERMOHONAN_SEQ");
		      int idJKPTG = Integer.parseInt("1");
		      
		      String noPermohonan = "TIADA";
		      String noPerserahan = "TIADA";
		      String TarikhSurKJP = (String)data.get("TarikhSurKJP");
		      String noFailUPT = (String)data.get("noFailUPT");
		      String tujuan = (String)data.get("tajuk_Fail"); 
	    	  String noFailKJP = (String)data.get("noFailKJP");
		      String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";
		      String idJenistanah = (String)data.get("StatusTanah");
		      //String NoFailLain = (String)data.get("NoFailLain");
		      String TarikhPermohonan = (String)data.get("TarikhPermohonan");
			  String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
			  
		      r.clear();
			  r.add("id_Permohonan", idPermohonan);
			  r.add("id_Fail",idFail);
			  r.add("id_Jkptg",idJKPTG);
			  r.add("no_Permohonan",noPermohonan);
			  r.add("no_Perserahan",noPerserahan);
		      if(idurusan!=1 && !data.get("noFailKJP").equals("")){		      
		    	  r.add("tarikh_Surat", r.unquote(TSKJP));
		    	  
		      }else{
				  r.add("tarikh_Surat", r.unquote(TSKJP));		   
	  
		      }
			  r.add("tarikh_Terima", r.unquote(TP));
			  r.add("tujuan",tujuan);
			  r.add("id_Masuk",idUser);
			  r.add("tarikh_Masuk",r.unquote("sysdate"));
			  r.add("id_Kemaskini",idUser);
			  r.add("tarikh_Kemaskini",r.unquote("sysdate"));
		      sql = r.getSQLInsert("Tblpermohonan");
		      myLog.debug("TBLPERMOHONAN = "+sql);
		      stmt.executeUpdate(sql);
		      
			  r.clear();
			  r.add("id_Htppermohonan",idHtppermohonan);
			  r.add("id_Permohonan", idPermohonan);
			  r.add("id_Daerah", iddaerah);
			  r.add("id_Agensi", idagensi);
			  r.add("id_Jenistanah", idJenistanah);
			  r.add("id_Pegawai", idUser);
		      if(idurusan!=1 && !data.get("noFailKJP").equals("")){		      
				  r.add("NO_RUJUKAN_KJP",noFailKJP);
		    	  
		      }else{
				  r.add("NO_RUJUKAN_KJP",noFailKJP);
	  
		      }
			  r.add("no_Rujukan_Lain", noFailUPT);
			  r.add("tarikh_Agihan", r.unquote("sysdate"));
			  r.add("id_Masuk",idUser);
			  r.add("tarikh_Masuk",r.unquote("sysdate"));
			  r.add("id_Kemaskini",idUser);
			  r.add("tarikh_Kemaskini",r.unquote("sysdate"));
			  sql = r.getSQLInsert("Tblhtppermohonan");
			  myLog.debug("TBLHTPPERMOHONAN = "+sql);
			  stmt.executeUpdate(sql);
	          
          
	          	//STATUS
				//long IdSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");

				//r.add("Id_Suburusanstatusfail", IdSuburusanstatusfail);	
				r.clear();
				r.add("id_Permohonan", idPermohonan);
				r.add("Id_Suburusanstatus", FrmUtilData.getIdSuburusanstatusByLangkah("1",""+idsuburusan,"="));
				r.add("aktif","1");
				r.add("id_Masuk", idUser);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("id_KEMASKINI", idUser);
				r.add("tarikh_KEMASKINI", r.unquote("sysdate"));
				r.add("ID_FAIL", idFail);
				sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
				myLog.info("StatusChange_Action::TBLRUJSUBURUSANSTATUSFAIL = "+sql);
				stmt.executeUpdate(sql);
				  
	          conn.commit();		      		
		 } catch (SQLException se) { 
			 try {
		    		conn.rollback();
		    
			 } catch (SQLException se2) {
				 throw new Exception("Rollback error:"+se2.getMessage());
			 }
		    throw new Exception("Ralat Pendaftaran Permohonan:"+se.getMessage());
		 } catch (Exception e) {
			 conn.rollback();
			 throw new Exception("Ralat:"+e.getMessage());
		 
		 }finally {
			 
			 if (db != null) db.close();
		     if (conn != null) conn.close();
		 }		
		 return String.valueOf(idFail);
	
	}
	
	public String simpanPermohonanOnline(Hashtable<String, String> data,String idUser) throws Exception {
		 Db db = null;
		 Connection conn = null;
		 long idFail = 0L;
		 String sql = "";
		 try {
			 
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
	          
			String kodNegeriMampu = "";
			String kodKementerianMampu = "";
			String kod_urusan = "";
			String seksyen = "3";
			//long idFail = (Long) data.get("id_Fail");
			int idnegeri = Utils.parseInt((String)data.get("id_Negeri"));
			int iddaerah = Utils.parseInt((String)data.get("id_Daerah"));
			int flagFail = Integer.parseInt(String.valueOf(data.get("flag_Fail")));
			int idkementerian = Utils.parseInt((String)data.get("id_Kementerian"));
			int idagensi = Utils.parseInt((String)data.get("id_Agensi"));
			int idurusan = Utils.parseInt((String)data.get("id_Urusan"));
			int idsuburusan = Utils.parseInt((String)data.get("id_Suburusan"));
			
			String taraf = (String) data.get("id_Tarafkeselamatan");
			String tajukFail = (String) data.get("tajuk_Fail");
			String status = (String) data.get("id_Status");
			//String lokasi = (String) data.get("id_Lokasifail");
			//String faharasat = (String) data.get("id_Faharasat");
			
	        //PFDFAIL	          
			idFail = DB.getNextID(db,"TBLPFDFAIL_SEQ");
			int fileSeq = 0;
			fileSeq = File.getSeqNo(3,idurusan,idkementerian,idnegeri);//Integer.parseInt(getParam("socSeksyen"))

			kodNegeriMampu = getNegeriByMampu(idnegeri);
			kodKementerianMampu = getKementerianByMampu(idkementerian);
			kod_urusan = getKodUrusan(idurusan);
			//KTPK/101/878/(TK)/1/13-244
			String noFail = "JKPTG/101/";
			jt = (Tblrujjenistanah)FrmUtilData.getRujJenisTanah((String)data.get("StatusTanah"));
			String jenisTanah = "";
			if(jt.getKodJenistanah()!=""){
				jenisTanah = "("+jt.getKodJenistanah()+")/";
			}
			noFail += kod_urusan +"/"+jenisTanah+kodKementerianMampu+"/"+kodNegeriMampu+"-"+fileSeq;
					
			//String noFailLama = (String) data.get("no_Fail");
			r.add("id_Fail", idFail);
			r.add("id_Tarafkeselamatan", taraf);
			r.add("id_Seksyen", seksyen);
			r.add("id_Urusan", idurusan);
			r.add("id_Suburusan", idsuburusan);
			r.add("tarikh_Daftar_Fail", r.unquote("sysdate"));
			r.add("tajuk_fail", tajukFail);
			
			/* EditBy Zul - 
			 * disable no fail sebab online buat permohonan xkan ada lagi no file selagi internal x bukak file 
			 * simpan data sebagai TIADA */
			// r.add("no_Fail", " ");
			// r.add("no_fail_root", " ");
			// r.add("no_Fail", noFail); //disable
			// r.add("no_fail_root", noFail); //disable
			r.add("no_Fail", "TIADA"); //new save sebagai TIADA
			r.add("no_fail_root", "TIADA"); //new save sebagai TIADA
			/* - End EditBy Zul - */
			
			r.add("id_Lokasifail", "1");
			r.add("id_Negeri", idnegeri);
			r.add("id_Kementerian", idkementerian);
			r.add("id_Faharasat", "1");
			r.add("flag_Fail", flagFail);
			r.add("id_Status", status);
			r.add("catatan", "TIADA");
			r.add("id_Masuk", idUser);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_Kemaskini", idUser);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			
			sql = r.getSQLInsert("tblpfdfail");
			myLog.debug("sql tblpfdfail:"+ sql);
			stmt.executeUpdate(sql);
	        
			//END PFD FAIL
			
			//TBLPERMOHONAN
			
		      long idPermohonan = DB.getNextID(db,"TBLPERMOHONAN_SEQ");
		      long idHtppermohonan = DB.getNextID(db,"TBLHTPPERMOHONAN_SEQ");
		      int idJKPTG = Integer.parseInt("1");
		      Long setIdStatus = 0L;
		      //setIdStatus = FrmUtilData.getIdStatusByLangkah("-1",String.valueOf(idsuburusan),"=");
		      setIdStatus = FrmUtilData.getIdStatusByLangkah("-4",String.valueOf(idsuburusan),"="); //addby zul disable yg atas : untuk dapatkan ID_STATUS = 8 (PENDAFTARAN)
		      
		      //String noPermohonan = "TIADA";
		      String noPerserahan = "TIADA";
		      String TarikhSurKJP = (String)data.get("TarikhSurKJP");
		      String noFailUPT = (String)data.get("noFailUPT");
		      String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";
		      String tujuan = (String)data.get("tajuk_Fail"); 
		      String noFailKJP = (String)data.get("noFailKJP");
		      String idJenistanah = (String)data.get("StatusTanah");
		      //String NoFailLain = (String)data.get("NoFailLain");
		      String TarikhPermohonan = (String)data.get("TarikhPermohonan");
			  String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
			  
//			  Date now = new Date();
//		      SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
//		      String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";

		      r.clear();
			  r.add("id_Permohonan", idPermohonan);
			  r.add("id_Fail",idFail);
			  r.add("id_Jkptg",idJKPTG);
			  r.add("no_Permohonan",FrmUtilData.generateNoOnline(1,kodKementerianMampu, String.valueOf(idkementerian), kodNegeriMampu, String.valueOf(idnegeri)));
			  r.add("no_Perserahan",noPerserahan);
			  r.add("ID_STATUS",setIdStatus);
			  r.add("tarikh_Surat", r.unquote(TSKJP));
			  r.add("tarikh_Terima", r.unquote(TP));
			  r.add("tujuan",tujuan);
			  r.add("id_Masuk",idUser);
			  r.add("tarikh_Masuk",r.unquote("sysdate"));
			  r.add("id_Kemaskini",idUser);
			  r.add("tarikh_Kemaskini",r.unquote("sysdate"));
		      sql = r.getSQLInsert("Tblpermohonan");
		      myLog.debug("TBLPERMOHONAN = "+sql);
		      stmt.executeUpdate(sql);
		      
			  r.clear();
			  r.add("id_Htppermohonan",idHtppermohonan);
			  r.add("id_Permohonan", idPermohonan);
			  r.add("id_Daerah", iddaerah);
			  r.add("id_Agensi", idagensi);
			  r.add("id_Jenistanah", idJenistanah);
			  r.add("id_Pegawai", idUser);
			  r.add("NO_RUJUKAN_KJP",noFailKJP);
			  r.add("no_Rujukan_Lain", noFailUPT);
			  r.add("tarikh_Agihan", r.unquote("sysdate"));
			  r.add("id_Masuk",idUser);
			  r.add("tarikh_Masuk",r.unquote("sysdate"));
			  r.add("id_Kemaskini",idUser);
			  r.add("tarikh_Kemaskini",r.unquote("sysdate"));
			  sql = r.getSQLInsert("Tblhtppermohonan");
			  myLog.debug("TBLHTPPERMOHONAN = "+sql);
			  stmt.executeUpdate(sql);
	                  
	          	//STATUS
				//long IdSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");

				//r.add("Id_Suburusanstatusfail", IdSuburusanstatusfail);	
				r.clear();
				r.add("id_Permohonan", idPermohonan);
				//By Rosli 2010/09/21 				
				//r.add("Id_Suburusanstatus", FrmUtilData.getIdSuburusanstatusByLangkah("101",""+idsuburusan,"="));
				//r.add("Id_Suburusanstatus", FrmUtilData.getIdSuburusanstatusByLangkah("-1",""+idsuburusan,"="));
				r.add("Id_Suburusanstatus", FrmUtilData.getIdSuburusanstatusByLangkah("-4",""+idsuburusan,"=")); //addby zul disable yg atas : untuk dapatkan ID_STATUS = 8 (PENDAFTARAN)
				r.add("aktif","1");
				r.add("id_Masuk", idUser);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("id_KEMASKINI", idUser);
				r.add("tarikh_KEMASKINI", r.unquote("sysdate"));
				r.add("ID_FAIL", idFail);
				sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
				myLog.info("StatusChange_Action::TBLRUJSUBURUSANSTATUSFAIL = "+sql);
				stmt.executeUpdate(sql);
				  
	          conn.commit();		      		
		 } catch (SQLException se) { 
			 try {
		    		conn.rollback();
		    
			 } catch (SQLException se2) {
				 throw new Exception("Rollback error:"+se2.getMessage());
			 }
		    throw new Exception("Ralat Pendaftaran Permohonan:"+se.getMessage());
		 } catch (Exception e) {
			 conn.rollback();
			 throw new Exception("Ralat:"+e.getMessage());
		 
		 }finally {
			 
			 if (db != null) db.close();
		     if (conn != null) conn.close();
		 }		
		 return String.valueOf(idFail);
	
	}
	/**
	 * @category simpan data ke --> tblpfdfail
	 */
	@Deprecated
	public void JanaFail(Hashtable<String, String> data) throws Exception {
		janaFail(new Db(),data);
	}
	
	public long janaFail(Db db,Hashtable<String, String> data) throws Exception {
		//Db db = null;
		String sql = "";
		//Date now = new Date();
		long idFail = 0L;
		try {
			idFail = DB.getNextID(db,"TBLPFDFAIL_SEQ");
			String seksyen = "3";
			//long idFail = (Long) data.get("id_Fail");
			int negeri = Integer.parseInt(data.get("id_Negeri"));
			int flagFail = Integer.parseInt(data.get("flag_Fail"));
			int kementerian = Integer.parseInt(data.get("id_Kementerian"));
			int idmasuk = Integer.parseInt(data.get("id_Masuk"));
			int urusan = Integer.parseInt(data.get("id_Urusan"));
			int suburusan = Integer.parseInt(data.get("id_Suburusan"));
			// String seksyen = (String)data.get("id_Seksyen");
			String taraf = (String) data.get("id_Tarafkeselamatan");
			String tajukFail = (String) data.get("tajuk_Fail");
			String status = (String) data.get("id_Status");
			//String lokasi = (String) data.get("id_Lokasifail");
			//String faharasat = (String) data.get("id_Faharasat");
			String noFailLama = (String) data.get("no_Fail");		

			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("id_Fail", idFail);
			r.add("id_Tarafkeselamatan", taraf);
			r.add("id_Seksyen", seksyen);
			r.add("id_Urusan", urusan);
			r.add("id_Suburusan", suburusan);
			r.add("tarikh_Daftar_Fail", r.unquote("sysdate"));
			r.add("tajuk_Fail", tajukFail);
			r.add("no_Fail", noFailLama);
			r.add("no_fail_root", noFailLama);
			r.add("id_Lokasifail", "1");
			r.add("id_Negeri", negeri);
			r.add("id_Kementerian", kementerian);
			r.add("id_Faharasat", "1");
			r.add("flag_Fail", flagFail);
			r.add("id_Status", status);
			r.add("catatan", "TIADA");
			r.add("id_Masuk", idmasuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_Kemaskini", idmasuk);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));

			sql = r.getSQLInsert("tblpfdfail");
			myLog.debug("sql:"+ sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
	    	throw new SQLException(e);
	    }
		return idFail;
//		} finally {
//			if (db != null)
//				db.close();
//		}

	}
	
	public String getKodUrusan(int idurusan) throws Exception {
	    Db db = null;
	    String output = "";
	    String sql = "Select kod_urusan from tblrujurusan where id_urusan='"+idurusan+"'";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      ResultSet rs = stmt.executeQuery(sql);
	      if (rs.next()) {
			   output = Utils.isNull(rs.getString("kod_urusan"));
	      }
	    } finally {
	      if (db != null) db.close();
	    }
	    return output;
	}
	
	public String getNegeriByMampu(int idnegeri) throws Exception {
	    Db db = null;
	    String output = "";
	    String sql = "Select id_negeri,kod_negeri,nama_negeri,kod_mampu" +
	    		" from tblrujnegeri where id_negeri="+idnegeri;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      ResultSet rs = stmt.executeQuery(sql);
	      //Tblrujnegeri f = null;
		    //System.out.println("FrmPajakanKecil::getNegeriByMampu 1");
		    if (rs.next()) {
	    	  //f = new Tblrujnegeri();
	    	  //f.setKodMampu(rs.getString("kod_mampu"));
		    	output = Utils.isNull(rs.getString("kod_mampu"));
	      }
		   //System.out.println("FrmPajakanKecil::getNegeriByMampu 2"+f.getKodMampu());
	      //return f.getKodMampu();
	    } finally {
	      if (db != null) db.close();
	    }
	    return output;
	}
	
	 public Vector<Hashtable<String, String>> getFileCount(int idnegeri, int idurusan)throws Exception {
		    Db db = null;
		    String sql = "";
		    //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
	      
		      r.add("a.id_Fail");
		      /*r.add("a.no_Fail");
		      r.add("a.tajuk_Fail");
		      r.add("a.tarikh_Daftar_Fail");
		      r.add("b.nama_Negeri");
		      r.add("c.kod_Seksyen");
		      r.add("d.keterangan");
		      r.add("e.keterangan as status");
		      */
		      r.add("a.id_Negeri",idnegeri);
		      r.add("a.id_Urusan",idurusan);
		      //r.add("a.id_Status",r.unquote("d.id_Status"));
		      //r.add("a.id_Fail",r.unquote("f.id_Fail(+)"));
		      //r.add("f.id_Status",r.unquote("e.id_Status(+)"));
		     	
		      sql = r.getSQLSelect("Tblpfdfail a");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable<String, String> h;
		      //int bil = 1;
		      Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();

		      while (rs.next()) {
		    	  h = new Hashtable<String, String>();
		    	  //h.put("bil", bil);
		    	  h.put("id_Fail",rs.getString("id_Fail"));
		    	  /*h.put("no_Fail", rs.getString("no_Fail"));
		    	  h.put("tajuk_Fail", rs.getString("tajuk_Fail")== null?"":rs.getString("tajuk_Fail"));
		    	  h.put("tarikh_Daftar_Fail",sdf.format(rs.getDate("tarikh_Daftar_Fail"))== null? "":sdf.format(rs.getDate("tarikh_Daftar_Fail")));
		    	  h.put("nama_Negeri", rs.getString("nama_Negeri"));
		    	  h.put("kod_Seksyen",rs.getString("kod_Seksyen"));
		    	  h.put("keterangan1", rs.getString("keterangan"));
		    	  h.put("keterangan2", rs.getString("status")== null? "":rs.getString("status"));
		    	  */
		    	  list.addElement(h);
		    	  //bil++;
		    	  
		    	  
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		}
	
	public String getKementerianByMampu(int idkementerian) throws Exception {
	    Db db = null;
	    String output = "";
	    String sql = "Select id_kementerian,kod_kementerian" +
	    		" from tblrujkementerian where id_kementerian="+idkementerian;
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	ResultSet rs = stmt.executeQuery(sql);
	    	if (rs.next()) {
	    		//output = rs.getString("kod_kementerian");		    	
		    	output = String.valueOf(rs.getInt("kod_kementerian"));
	    	}
	    } finally {
	      if (db != null) db.close();
	    
	    }
	    return output;
	}

	public Vector<Hashtable<String, String>> getList(String search)
		throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("p.id_Fail");
			r.add("f.no_Fail");
			r.add("f.tajuk_Fail");
			r.add("s.keterangan");
			r.add("p.tujuan");
			r.add("p.id_Fail", r.unquote("f.id_Fail"));
			r.add("p.id_Permohonan", r.unquote("sf.id_Permohonan"));
			r.add("sf.id_Suburusanstatus", r.unquote("us.id_Suburusanstatus"));
			r.add("us.id_Status", r.unquote("s.id_Status"));
			r.add("f.id_Urusan", "1");
			r.add("sf.aktif", "1");
			r.add("f.no_Fail", "%" + search + "%", "like");

			sql = r.getSQLSelect(
							"Tblpermohonan p, Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s",
							" f.id_kemaskini");
			myLog.info("FrmSenaraiFailPajakanKecilData:getList::sql:::" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
			Hashtable<String, String> h;

			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("id", rs.getString("id_Fail"));
				h.put("no", Utils.isNull(rs.getString("no_Fail")));
				h.put("tajuk", Utils.isNull(rs.getString("tajuk_Fail")));
				h.put("tujuan", Utils.isNull(rs.getString("tujuan")));
				h.put("keterangan", Utils.isNull(rs.getString("keterangan")));
				list.addElement(h);

				/*
				 * Tblpermohonan per = new Tblpermohonan();
				 * per.setIdPermohonan(rs.getLong("id_Fail"));
				 * 
				 * Tblfail fail = new Tblfail();
				 * fail.setKodFail(rs.getString("no_Fail"));
				 * fail.setTajukFail(rs.getString("tajuk_Fail"));
				 * 
				 * Tblrujstatus status = new Tblrujstatus();
				 * status.setKeterangan("keterangan");
				 * 
				 * list.addElement(per);
				 */
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public  Vector<Hashtable<String, String>> TerimaPohongetList(String idUser,String nofail,String tajukfail
			,String id_kementerian,String id_agensi
			,String id_negeri,String id_daerah,String id_mukim
			,String id_urusan,String tarikhBukaFail,boolean isSearch) throws Exception {
		    Db db = null;
		    String sql = "";
		    Vector<Hashtable<String, String>> list = null;
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "SELECT DISTINCT f.id_masuk,p.id_Fail,NVL(LTRIM(RTRIM(f.no_Fail)),'TIADA') NO_FAIL, f.tajuk_Fail,F.TARIKH_DAFTAR_FAIL" +
		      		" ,TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') TARIKH_DAFTAR,f.id_kemaskini" +
		      		" ,F.TARIKH_MASUK, F.TARIKH_KEMASKINI  " +
		      		" ,P.NO_PERMOHONAN, P.TUJUAN, " +
		      		" ,RN.NAMA_NEGERI NEGERI"+
		      		" ,S.KETERANGAN "+
		      		" FROM Tblpermohonan P,TblHTPPermohonan PP, "+
				    " Tblpfdfail F,Tblrujsuburusanstatusfail SF,Tblhtphakmilikurusan THMU," +
				    " Tblrujsuburusanstatus US,Tblrujstatus S " +
				    " ,TBLRUJNEGERI RN "+
				    " WHERE P.id_Fail = F.id_Fail AND F.ID_NEGERI=RN.ID_NEGERI " +
				    " AND ( F.ID_STATUS <> '999' OR F.ID_STATUS is null) " +
				    " AND P.id_Permohonan = PP.id_Permohonan "+
				    " AND P.id_Permohonan = SF.id_Permohonan  "+
				    " AND P.id_Permohonan = THMU.id_Permohonan(+)  "+
				    " AND SF.id_Suburusanstatus = US.id_Suburusanstatus  "+
				    " AND SF.ID_FAIL = F.ID_FAIL "+
				    //1,10 - Permohonan & Perizaban
				    " AND US.id_Status = S.id_Status  AND F.id_Urusan IN (1,10)  AND sf.aktif = '1'  ";
		      
		      if (idUser != null) {
		    	  sql = sql + " AND f.id_masuk='"+idUser+"'";
		      }
		      
		      if (nofail != null && !"".equals(nofail)) {
		    	  sql = sql + "AND lower(f.no_Fail) like '%"+nofail.toLowerCase()+"%' ";
		    	  isSearch = true;
		      }
		      
		      if (id_urusan != null && !"-1".equals(id_urusan) && !"".equals(id_urusan)) {
		    	  sql = sql + "AND f.id_urusan = "+id_urusan+" ";
		      }
		      
		      if (tajukfail != null && !"".equals(tajukfail)) {
		    	  sql = sql + "AND lower(f.tajuk_Fail) like '%"+tajukfail.toLowerCase()+"%' ";
		      }
		      
		      if (id_kementerian != null && !"-1".equals(id_kementerian) && !"".equals(id_kementerian)) {
		    	  sql = sql + "AND f.id_kementerian = '"+id_kementerian+"' ";
		      }
		      if (id_agensi!= null && !"-1".equals(id_agensi) && !"".equals(id_agensi)) {
		    	  sql = sql + "AND pp.id_agensi = '"+id_agensi+"' ";
		      }
		      if (id_negeri != null && !"-1".equals(id_negeri) && !"".equals(id_negeri)) {
		    	  sql = sql + "AND f.id_negeri = '"+id_negeri+"' ";
		      }
		      
		      if (id_daerah != null && !"-1".equals(id_daerah) && !"".equals(id_daerah)) {
		    	  sql = sql + "AND pp.id_daerah = '"+id_daerah+"' ";
		      }
		      
		      if (id_mukim != null && !"-1".equals(id_mukim) && !"".equals(id_mukim)) {
		    	  sql = sql + "AND thmu.id_mukim = '"+id_mukim+"' ";
		      }
		      
		      if (tarikhBukaFail != null && !"-1".equals(tarikhBukaFail) && !"".equals(tarikhBukaFail)) {
		    	  sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') = '"+tarikhBukaFail+"' ";
		      }
		      
		      if (!isSearch) {
		    	  sql = sql + " AND ROWNUM <= 50 ";
		      }	      
		      //sql = sql + "ORDER BY  f.id_kemaskini DESC";
		      //sql = sql + "ORDER BY F.TARIKH_MASUK,F.TARIKH_KEMASKINI DESC";
		      sql = sql + " ORDER BY F.TARIKH_KEMASKINI DESC,F.TARIKH_MASUK DESC"+
		      "" ;
		      //System.out.println("sql : "+sql);
		      myLog.info("TerimaPohongetList9:sql="+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      list = new Vector<Hashtable<String, String>>();
		      Hashtable<String, String> h;

		      while (rs.next()) {
		    	  h = new Hashtable<String, String>();
		    	  h.put("id", rs.getString("id_Fail"));
		    	  h.put("no", Utils.isNull(rs.getString("no_Fail")));
		    	  h.put("noP", Utils.isNull(rs.getString("NO_PERMOHONAN")));
		    	  h.put("negeri", Utils.isNull(rs.getString("negeri")));
		    	  h.put("tajuk", Utils.isNull(rs.getString("tajuk_Fail")));
		    	  h.put("tujuan", Utils.isNull(rs.getString("tujuan")));
		    	  h.put("keterangan", Utils.isNull(rs.getString("keterangan")));
		    	  h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
		    	  list.addElement(h);
		      }
		      return list;
		    
		    } finally {
		      if (db != null) db.close();
		    }
		    
		  }
	
	public  Vector<Hashtable<String, String>> TerimaPohongetList(String idUser,String nofail,String tajukfail
		,String id_kementerian,String id_agensi
		,String id_negeri,String id_daerah,String id_mukim
		,String id_urusan,String idStatus,String noRujukanOnline) throws Exception {
	    Db db = null;
	    String sql = "";
	    Vector<Hashtable<String, String>> list = null;
	    boolean isSearch = false;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql = "SELECT DISTINCT f.id_masuk,p.id_Fail,NVL(LTRIM(RTRIM(f.no_Fail)),'TIADA') NO_FAIL, f.tajuk_Fail,F.TARIKH_DAFTAR_FAIL" +
	      		" ,TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') TARIKH_DAFTAR,f.id_kemaskini" +
	      		" ,F.TARIKH_MASUK, F.TARIKH_KEMASKINI " +
	      		" ,P.NO_PERMOHONAN, P.TUJUAN " +
	      		" ,RN.NAMA_NEGERI NEGERI"+
	      		" ,S.KETERANGAN "+
	      		" FROM Tblpermohonan P,TblHTPPermohonan PP, "+
			    " Tblpfdfail F,Tblrujsuburusanstatusfail SF,Tblhtphakmilikurusan THMU," +
			    " Tblrujsuburusanstatus US,Tblrujstatus S " +
			    " ,TBLRUJNEGERI RN "+
			    " WHERE P.id_Fail = F.id_Fail AND F.ID_NEGERI=RN.ID_NEGERI " +
			    " AND ( F.ID_STATUS <> '999' OR F.ID_STATUS is null) " +
			    " AND P.id_Permohonan = PP.id_Permohonan "+
			    " AND P.id_Permohonan = SF.id_Permohonan  "+
			    " AND P.id_Permohonan = THMU.id_Permohonan(+)  "+
			    " AND SF.id_Suburusanstatus = US.id_Suburusanstatus  "+
			    " AND SF.ID_FAIL = F.ID_FAIL "+
			    //1,10 - Permohonan & Perizaban
			    " AND US.id_Status = S.id_Status  AND F.id_Urusan IN (1,10)  AND sf.aktif = '1'  ";
	      
	      if (idUser != null) {
	    	  sql = sql + " AND f.id_masuk='"+idUser+"'";
	      }
	      
	      if (nofail != null && !"".equals(nofail)) {
	    	  sql = sql + "AND lower(f.no_Fail) like '%"+nofail.toLowerCase()+"%' ";
	    	  isSearch = true;
	      }
	      
	      if (id_urusan != null && !"-1".equals(id_urusan) && !"".equals(id_urusan)) {
	    	  sql = sql + "AND f.id_urusan = "+id_urusan+" ";
	      }
	      
	      if (tajukfail != null && !"".equals(tajukfail)) {
	    	  sql = sql + "AND lower(f.tajuk_Fail) like '%"+tajukfail.toLowerCase()+"%' ";
	      }
	      
	      if (id_kementerian != null && !"-1".equals(id_kementerian) && !"".equals(id_kementerian)) {
	    	  sql = sql + "AND f.id_kementerian = '"+id_kementerian+"' ";
	      }
	      if (id_agensi!= null && !"-1".equals(id_agensi) && !"".equals(id_agensi)) {
	    	  sql = sql + "AND pp.id_agensi = '"+id_agensi+"' ";
	      }
	      if (id_negeri != null && !"-1".equals(id_negeri) && !"".equals(id_negeri)) {
	    	  sql = sql + "AND f.id_negeri = '"+id_negeri+"' ";
	      }
	      
	      if (id_daerah != null && !"-1".equals(id_daerah) && !"".equals(id_daerah)) {
	    	  sql = sql + "AND pp.id_daerah = '"+id_daerah+"' ";
	      }
	      
	      if (id_mukim != null && !"-1".equals(id_mukim) && !"".equals(id_mukim)) {
	    	  sql = sql + "AND thmu.id_mukim = '"+id_mukim+"' ";
	      }
	      
	      //--- addby zulfazdliabuas@gmail.com ---
	      if (idStatus != null && !"".equals(idStatus) && !"0".equals(idStatus)) {
	    	  sql = sql + "AND us.id_Status='"+idStatus+"' ";
	      }
	      
	      if (noRujukanOnline != null && !"".equals(noRujukanOnline)) {
	    	  sql = sql + "AND p.no_permohonan='"+noRujukanOnline+"' ";
	      }
	      //--- End addby Zul --- 
	      
	      if (!isSearch) {
	    	  sql = sql + " AND ROWNUM <= 50 ";
	      }	      
	      //sql = sql + "ORDER BY  f.id_kemaskini DESC";
	      //sql = sql + "ORDER BY F.TARIKH_MASUK,F.TARIKH_KEMASKINI DESC";
	      sql = sql + " ORDER BY F.TARIKH_KEMASKINI DESC,F.TARIKH_MASUK DESC"+
	      "" ;
	      //System.out.println("sql : "+sql);
	      myLog.info("TerimaPohongetList9:sql="+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      list = new Vector<Hashtable<String, String>>();
	      Hashtable<String, String> h;

	      while (rs.next()) {
	    	  h = new Hashtable<String, String>();
	    	  h.put("id", rs.getString("id_Fail"));
	    	  h.put("no", Utils.isNull(rs.getString("no_Fail")));
	    	  h.put("noP", Utils.isNull(rs.getString("NO_PERMOHONAN")));
	    	  h.put("negeri", Utils.isNull(rs.getString("negeri")));
	    	  h.put("tajuk", Utils.isNull(rs.getString("tajuk_Fail")));
	    	  h.put("tujuan", Utils.isNull(rs.getString("tujuan")));
	    	  h.put("keterangan", Utils.isNull(rs.getString("keterangan")));
	    	  h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
	    	  list.addElement(h);
	      }
	      return list;
	    
	    } finally {
	      if (db != null) db.close();
	    }
	    
	  }
	
	public  Vector<Hashtable<String, String>> TerimaPohongetListOnline(String idUser,
			String nofail,String tajukfail,String id_kementerian,String id_agensi,
			String id_negeri,String id_daerah,String id_mukim,String id_urusan)throws Exception {
	    Db db = null;
	    String sql = "";
	    Vector<Hashtable<String, String>> list = null;
	    boolean isSearch = false;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql = "SELECT DISTINCT f.id_masuk,p.id_Fail, f.no_Fail, f.tajuk_Fail, p.NO_PERMOHONAN, " +
	      		"(select nama_negeri from tblrujnegeri where id_negeri = f.id_negeri) negeri,"+
	      		"s.keterangan, p.tujuan,f.id_kemaskini "+
	      		"FROM Tblpermohonan p,TblHTPPermohonan pp, "+
			    "Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblhtphakmilikurusan thmu," +
			    "Tblrujsuburusanstatus us,Tblrujstatus s "+
			    "WHERE p.id_Fail = f.id_Fail  " +
			    "AND p.id_Permohonan = pp.id_Permohonan "+
			    "AND p.id_Permohonan = sf.id_Permohonan  "+
			    "AND p.id_Permohonan = thmu.id_Permohonan(+)  "+
			    "AND sf.id_Suburusanstatus = us.id_Suburusanstatus  "+
			    "AND SF.ID_FAIL = F.ID_FAIL "+
			    //1,10 - Permohonan & Perizaban
			    "AND us.id_Status = s.id_Status  AND f.id_Urusan IN (1,10)  AND sf.aktif = '1'  ";
	      
	      if (idUser != null) {
	    	  sql = sql + " AND f.id_masuk='"+idUser+"'";
	      }
	      
	      if (nofail != null && !"".equals(nofail)) {
	    	  sql = sql + "AND p.NO_PERMOHONAN like '%"+nofail.trim()+"%' ";
	    	  isSearch = true;
	      }
	      
	      if (id_urusan != null && !"-1".equals(id_urusan) && !"".equals(id_urusan)) {
	    	  sql = sql + "AND f.id_urusan = "+id_urusan+" ";
	      }
	      
	      if (tajukfail != null && !"".equals(tajukfail)) {
	    	  sql = sql + "AND f.tajuk_Fail like '%"+tajukfail.toUpperCase()+"%' ";
	      }
	      
	      if (id_kementerian != null && !"-1".equals(id_kementerian) && !"".equals(id_kementerian)) {
	    	  sql = sql + "AND f.id_kementerian = '"+id_kementerian+"' ";
	      }
	      if (id_agensi!= null && !"-1".equals(id_agensi) && !"".equals(id_agensi)) {
	    	  sql = sql + "AND pp.id_agensi = '"+id_agensi+"' ";
	      }
	      if (id_negeri != null && !"-1".equals(id_negeri) && !"".equals(id_negeri)) {
	    	  sql = sql + "AND f.id_negeri = '"+id_negeri+"' ";
	      }
	      
	      if (id_daerah != null && !"-1".equals(id_daerah) && !"".equals(id_daerah)) {
	    	  sql = sql + "AND pp.id_daerah = '"+id_daerah+"' ";
	      }
	      
	      if (id_mukim != null && !"-1".equals(id_mukim) && !"".equals(id_mukim)) {
	    	  sql = sql + "AND thmu.id_mukim = '"+id_mukim+"' ";
	      }
	      
	      if (!isSearch) {
	    	  sql = sql + " AND ROWNUM <= 50 ";
	      }	      
	      sql = sql + "ORDER BY  f.id_kemaskini DESC";

	      myLog.info("TerimaPohongetListOnline:sql="+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      list = new Vector<Hashtable<String, String>>();
	      Hashtable<String, String> h;

	      while (rs.next()) {
	    	  h = new Hashtable<String, String>();
	    	  h.put("id", rs.getString("id_Fail"));
	    	  h.put("no", Utils.isNull(rs.getString("no_Fail")));
	    	  h.put("noP", Utils.isNull(rs.getString("NO_PERMOHONAN")));
	    	  h.put("negeri", Utils.isNull(rs.getString("negeri")));
	    	  h.put("tajuk", Utils.isNull(rs.getString("tajuk_Fail")));
	    	  h.put("tujuan", Utils.isNull(rs.getString("tujuan")));
	    	  h.put("keterangan", Utils.isNull(rs.getString("keterangan")));
	    	  list.addElement(h);
	      }
	      return list;
	    
	    } finally {
	      if (db != null) db.close();
	    }
	    
	  }
	
	public  Vector<Hashtable<String, String>> TerimaPohongetListOnlineSHTP(String idUser,
			String nofail,String tajukfail,String id_kementerian,String id_agensi,
			String id_negeri,String id_daerah,String id_mukim,String id_urusan)throws Exception {
	    Db db = null;
	    String sql = "";
	    Vector<Hashtable<String, String>> list = null;
	    boolean isSearch = false;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql = "SELECT DISTINCT f.id_masuk,p.id_Fail, f.no_Fail, f.tajuk_Fail, P.NO_PERMOHONAN, " +
	      		"(select nama_negeri from tblrujnegeri where id_negeri = f.id_negeri) negeri,"+
	      		"s.keterangan, p.tujuan,f.id_kemaskini,f.tarikh_masuk,f.tarikh_kemaskini "+
	      		"FROM Tblpermohonan p,TblHTPPermohonan pp, "+
			    "Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblhtphakmilikurusan thmu," +
			    "Tblrujsuburusanstatus us,Tblrujstatus s "+
			    "WHERE p.id_Fail = f.id_Fail  " +
			    "AND p.id_Permohonan = pp.id_Permohonan "+
			    "AND p.id_Permohonan = sf.id_Permohonan  "+
			    "AND p.id_Permohonan = thmu.id_Permohonan(+)  "+
			    "AND sf.id_Suburusanstatus = us.id_Suburusanstatus  "+
			    "AND SF.ID_FAIL = F.ID_FAIL "+
			    //1,10 - Permohonan & Perizaban
			    "AND us.id_Status = s.id_Status  AND f.id_Urusan IN (1,10)  AND sf.aktif = '1'  ";
	      
	      if (idUser != null) {
	    	  sql = sql + " AND f.id_masuk='"+idUser+"'";
	      }
	      
	      if (nofail != null && !"".equals(nofail)) {
	    	  sql = sql + "AND lower(f.no_Fail) like '%"+nofail.toLowerCase()+"%' ";
	    	  isSearch = true;
	      }
	      
	      if (id_urusan != null && !"-1".equals(id_urusan) && !"".equals(id_urusan)) {
	    	  sql = sql + "AND f.id_urusan = "+id_urusan+" ";
	      }
	      
	      if (tajukfail != null && !"".equals(tajukfail)) {
	    	  sql = sql + "AND lower(f.tajuk_Fail) like '%"+tajukfail.toLowerCase()+"%' ";
	      }
	      
	      if (id_kementerian != null && !"-1".equals(id_kementerian) && !"".equals(id_kementerian)) {
	    	  sql = sql + "AND f.id_kementerian = '"+id_kementerian+"' ";
	      }
	      if (id_agensi!= null && !"-1".equals(id_agensi) && !"".equals(id_agensi)) {
	    	  sql = sql + "AND pp.id_agensi = '"+id_agensi+"' ";
	      }
	      if (id_negeri != null && !"-1".equals(id_negeri) && !"".equals(id_negeri)) {
	    	  sql = sql + "AND f.id_negeri = '"+id_negeri+"' ";
	      }
	      
	      if (id_daerah != null && !"-1".equals(id_daerah) && !"".equals(id_daerah)) {
	    	  sql = sql + "AND pp.id_daerah = '"+id_daerah+"' ";
	      }
	      
	      if (id_mukim != null && !"-1".equals(id_mukim) && !"".equals(id_mukim)) {
	    	  sql = sql + "AND thmu.id_mukim = '"+id_mukim+"' ";
	      }
	      
	      /*zul disable untuk sort by permohonan online/baru and tarikh_masuk latest
	      if (!isSearch) {
	    	  sql = sql + " AND ROWNUM <= 50 ";
	      }	      
	      sql = sql + " ORDER BY F.TARIKH_KEMASKINI DESC,F.TARIKH_MASUK DESC";
	      end */
	      sql = sql + " ORDER BY f.tarikh_masuk DESC"; //add yang ni : sortby date latest
	      
	      myLog.info("TerimaPohongetListOnlineSHTP::sql:::"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      list = new Vector<Hashtable<String, String>>();
	      Hashtable<String, String> h;

	      while (rs.next()) {
	    	  h = new Hashtable<String, String>();
	    	  h.put("id", rs.getString("id_Fail"));
	    	  h.put("no", Utils.isNull(rs.getString("no_Fail")));
	    	  h.put("noP", Utils.isNull(rs.getString("NO_PERMOHONAN")));
	    	  h.put("negeri", Utils.isNull(rs.getString("negeri")));
	    	  h.put("tajuk", Utils.isNull(rs.getString("tajuk_Fail")));
	    	  h.put("tujuan", Utils.isNull(rs.getString("tujuan")));
	    	  h.put("keterangan", Utils.isNull(rs.getString("keterangan")));
	    	  list.addElement(h);
	      }
	      return list;
	    
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	/**
	 * @author ollie_sara
	 * @function getInfoTerimaPohon Data
	 * @param idpermohonan
	 * @return
	 * @throws Exception
	 */	
	public static Hashtable<String, String> getTerimaPohonInfo(String idfail) throws Exception {		
		Db db = null;
		Hashtable<String, String> h = null;
		String sql = "";	
		try{
			db = new Db();
			Statement stmt = db.getStatement();			
		    sql = "SELECT P.NO_PERMOHONAN,P.ID_FAIL,N.NAMA_NEGERI, K.NAMA_KEMENTERIAN" +
		  		",K.ID_KEMENTERIAN, A.NAMA_AGENSI" +
	    		",U.NAMA_URUSAN,U.KOD_URUSAN,J.KOD_JENIS_TANAH,J.KETERANGAN, T.NO_FAIL, T.TAJUK_FAIL" +
	    		",HT.NO_RUJUKAN_KJP"+
	    		",HT.NO_RUJUKAN_UPT,HT.NO_RUJUKAN_PTG,HT.NO_RUJUKAN_PTD,HT.NO_RUJUKAN_LAIN,P.TUJUAN" +
	    		",TO_CHAR(P.TARIKH_SURAT,'dd/MM/YYYY') TARIKH_SURAT" +
	    		",T.ID_URUSAN,T.ID_SUBURUSAN " +
	    		",T.ID_STATUS, P.ID_PERMOHONAN,HT.ID_DAERAH"+
	    		",HT.ID_AGENSI , N.ID_NEGERI , J.ID_JENISTANAH, TK.ID_TARAFKESELAMATAN" +
	    		",RD.NAMA_DAERAH,SBU.NAMA_SUBURUSAN"+
	    		",(	SELECT USER_NAME FROM USERS WHERE USER_ID = T.ID_MASUK) DAFTAROLEH" +
	    		",T.TARIKH_MASUK,TK.TARAF_KESELAMATAN,HT.ID_HTPPERMOHONAN" +
	    		",TO_CHAR(T.TARIKH_DAFTAR_FAIL,'dd/MM/YYYY') TARIKH_DAFTAR_FAIL "+
	    		" FROM TBLPFDFAIL T, TBLPERMOHONAN P, TBLHTPPERMOHONAN HT" +
	    		",TBLRUJKEMENTERIAN K, TBLRUJAGENSI A" +
	    		",TBLRUJNEGERI N, TBLRUJDAERAH RD"+
	    		",TBLRUJURUSAN U, TBLRUJSUBURUSAN SBU, TBLRUJJENISTANAH J, TBLPFDRUJTARAFKESELAMATAN TK"+
	    		" WHERE  p.ID_PERMOHONAN = HT.ID_PERMOHONAN"+
	    		" AND T.ID_FAIL = P.ID_FAIL "+
	    		" AND T.ID_KEMENTERIAN=K.ID_KEMENTERIAN"+
	    		" AND A.ID_AGENSI=HT.ID_AGENSI"+
	    		" AND T.ID_NEGERI=N.ID_NEGERI"+
	    		" AND T.ID_URUSAN=U.ID_URUSAN"+
	    		" AND T.ID_SUBURUSAN = SBU.ID_SUBURUSAN"+
	    		" AND HT.ID_DAERAH = RD.ID_DAERAH"+
	    		" AND HT.ID_JENISTANAH = J.ID_JENISTANAH(+) "+
	    		" AND TK.ID_TARAFKESELAMATAN = T.ID_TARAFKESELAMATAN"+
	    		" AND P.ID_FAIL='"+idfail+"'";		  
		  //myLog.info("getPermohonanInfo("+idfail+") : sql::"+sql);
		  ResultSet rs1 = stmt.executeQuery(sql);
		  h = new Hashtable<String, String>();	  
		  if(rs1.next()){		  
			  h.put("idfail", Utils.isNull(rs1.getString("ID_FAIL"))); 
			  h.put("diDaftarOleh", Utils.isNull(rs1.getString("DAFTAROLEH")).toUpperCase()); 
			  h.put("diDaftarPada", Utils.isNull(rs1.getString("TARIKH_MASUK"))); 
			  h.put("lblIdPermohonan", Utils.isNull(rs1.getString("ID_PERMOHONAN"))); 
			  h.put("noP", Utils.isNull(rs1.getString("NO_PERMOHONAN"))); 			  
			  if(rs1.getString("ID_NEGERI")==null){
	    		  //TIADA MAKLUMAT
				  h.put("lblIdNegeri", "0");
			  }else{
				  h.put("lblIdNegeri", rs1.getString("ID_NEGERI")); 
			  }			  
			  if(rs1.getString("ID_DAERAH")==null){
				  //TIADA MAKLUMAT , ID_NEGERI=0 (TIADA MAKLUMAT)
				  h.put("lblIdDaerah", "0"); 
			  }else{
				  h.put("lblIdDaerah", rs1.getString("ID_DAERAH")); 
			  }			 
			  h.put("lblNamaDaerah", Utils.isNull(rs1.getString("NAMA_DAERAH"))); 
			  if(rs1.getString("ID_TARAFKESELAMATAN")==null){
				  //TERBUKA
	    		  //h.put("lblTanahKeselamatan", "1");
	    		  h.put("lblTanahKeselamatan", "0");
			  }else{
				  h.put("lblTanahKeselamatan", rs1.getString("ID_TARAFKESELAMATAN")); 
			  }			  
			  if(rs1.getString("ID_NEGERI")==null){
	    		  //TIADA MAKLUMAT
				  h.put("lblIdNegeri", "0");
			  }else{
				  h.put("lblNegeri", rs1.getString("ID_NEGERI")); 
			  }			  
			  if(rs1.getString("NAMA_NEGERI")==null){
				  h.put("lblNamaNegeri", "");
	      	  }else{
	      			h.put("lblNamaNegeri", rs1.getString("NAMA_NEGERI")); 
	      	  }
			  h.put("lblKementerian",  Utils.isNull(rs1.getString("NAMA_KEMENTERIAN"))); 
			  if(rs1.getString("ID_KEMENTERIAN")==null){
				  //KEMENTERIAN PELAJARAN
	    		  //h.put("lblidKementerian", "1");
	    		  h.put("lblidKementerian", "0");
			  }else{
				  h.put("lblidKementerian", rs1.getString("ID_KEMENTERIAN")); 
			  }
			  h.put("lblAgensi", Utils.isNull(rs1.getString("NAMA_AGENSI"))); 
	      		
			  if(rs1.getString("ID_AGENSI")==null){
				  //KEMENTERIAN PELAJARAN MALAYSIA ,  //ID_KEMENTERIAN=1 (KEMENTERIAN PELAJARAN)
				  //h.put("lblidAgensi", "155");
				  h.put("lblidAgensi", "0");
			  }else{
				  h.put("lblidAgensi", rs1.getString("ID_AGENSI")); 
			  }
			  h.put("lblUrusan", Utils.isNull(rs1.getString("NAMA_URUSAN"))); 			  
			  h.put("kodUrusan", rs1.getString("KOD_URUSAN")); 
			  if(rs1.getString("ID_URUSAN")==null){
	    		  //PERMOHONAN TANAH
				  //h.put("lblidUrusan", "1");
				  h.put("lblidUrusan", "0");
			  }else{
				  h.put("lblidUrusan", rs1.getString("ID_URUSAN")); 
			  }			  
			  if(rs1.getString("ID_SUBURUSAN")==null){
				  //PERMOHONAN TANAH, ID_URUSAN=1(PERMOHONAN TANAH)
	    		  //h.put("lblidSubUrusan", "42");
	    		  h.put("lblidSubUrusan", "0");
			  }else{
				  h.put("lblidSubUrusan", rs1.getString("ID_SUBURUSAN")); 
			  }
			  if(rs1.getString("NAMA_SUBURUSAN")==null){
				  //PERMOHONAN TANAH, ID_URUSAN=1(PERMOHONAN TANAH)
	    		  //h.put("lblidSubUrusan", "42");
	    		  h.put("lblNamaSubUrusan", "0");
			  }else{
				  h.put("lblNamaSubUrusan", rs1.getString("NAMA_SUBURUSAN")); 
			  }
			  h.put("lblKodJTanah", Utils.isNull(rs1.getString("KOD_JENIS_TANAH"))); 		  
			  if(rs1.getString("ID_JENISTANAH")==null){
	    		  h.put("lblidKodJTanah", "0");
			  }else{
				  h.put("lblidKodJTanah", rs1.getString("ID_JENISTANAH")); 
			  }
			  if(rs1.getString("TARAF_KESELAMATAN")==null){
	    		  h.put("lblTarafKeselamatan", "");
			  }else{
	      			h.put("lblTarafKeselamatan", rs1.getString("TARAF_KESELAMATAN")); 
			  }
			  h.put("lblKeterangan", Utils.isNull(rs1.getString("KETERANGAN"))); 
			  h.put("lblNoFail", Utils.isNull(rs1.getString("NO_FAIL"))); 
			  h.put("lblNoRujKJP", Utils.isNull(rs1.getString("NO_RUJUKAN_KJP"))); 
			  h.put("lblNoRujUPT", Utils.isNull(rs1.getString("NO_RUJUKAN_UPT"))); 
			  h.put("lblNoRujPTG", Utils.isNull(rs1.getString("NO_RUJUKAN_PTG"))); 
			  h.put("lblNoRujPTD", Utils.isNull(rs1.getString("NO_RUJUKAN_PTD"))); 
			  h.put("lblNoRujLain", Utils.isNull(rs1.getString("NO_RUJUKAN_LAIN"))); 
			  h.put("lblTujuan", Utils.isNull(rs1.getString("TAJUK_FAIL"))); 
			  h.put("lblTrkhSurat", Utils.isNull(rs1.getString("TARIKH_SURAT"))); 
			  h.put("idHtpPermohonan", Utils.isNull(rs1.getString("ID_HTPPERMOHONAN"))); 
			  h.put("tarikhDaftarFail", Utils.isNull(rs1.getString("TARIKH_DAFTAR_FAIL"))); 
			  
		  }
		
		}catch(Exception e){
			myLog.info(e.getMessage());
		}finally{
			if (db != null) db.close();
		}
		return h;
	
	}
	
	public static Hashtable<String, String> getPermohonanInfo(String idpermohonan)throws Exception {
	    Db db = null;
	    Hashtable <String, String> h = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      //SQLRenderer r = new SQLRenderer();
	      sql= " select trm.nama_mukim, trd.id_daerah, trd.kod_daerah, trd.NAMA_DAERAH, n.nama_negeri,k.nama_kementerian,a.nama_agensi, "+
	    	  " s.nama_suburusan,f.no_fail,to_char(f.tarikh_daftar_fail) tarikh_daftar_fail ,h.no_rujukan_kjp, "+
	    	  " h.no_rujukan_lain,to_char(p.tarikh_surat) tarikh_surat,to_char(p.tarikh_terima) tarikh_terima,p.tujuan," +
	    	  " p.id_permohonan,f.id_fail,k.id_kementerian,a.id_agensi, "+
	    	  " n.id_negeri FROM tblrujnegeri n, Tblpfdfail f, tblrujkementerian k,tblpermohonan p, tblrujdaerah trd, tblrujmukim trm,"+
	    	  " tblhtppermohonan h, tblrujagensi a,tblrujsuburusan s where  trm.id_daerah=trd.id_daerah and trd.id_negeri=n.ID_NEGERI and "+
	    	  " f.ID_NEGERI=n.ID_NEGERI and f.id_kementerian=k.id_kementerian and "+
	    	  " p.ID_FAIL=f.ID_FAIL and p.id_permohonan=h.ID_PERMOHONAN and "+
	    	  " h.ID_AGENSI=a.id_agensi and f.ID_SUBURUSAN=s.ID_SUBURUSAN and "+
	    	  " p.id_permohonan="+idpermohonan;
	      
	      //myLog.info("getPermohonanInfo("+idpermohonan+") : sql::"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      h = new Hashtable <String, String>();

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
	    	  if(rs.getString("tarikh_daftar_fail")==null){
	    		  h.put("tdaftar",sdf.format(new Date()));
	    	  }else{
	    		  h.put("tdaftar", rs.getString("tarikh_daftar_fail"));
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
	    	  if(rs.getString("tarikh_surat")==null){
	    		  h.put("tsurat",sdf.format(new Date()));
	    	  }else{
	    		  h.put("tsurat", rs.getString("tarikh_surat"));
	    	  }
	    	  if(rs.getString("tarikh_terima")==null){
	    		  h.put("rtterima",sdf.format(new Date()));
	    	  }else{
	    		  h.put("rtterima", rs.getString("tarikh_terima"));
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
    		  h.put("iddaerah", rs.getString("id_daerah"));
    		 // h.put("namamukim", rs.getString("nama_mukim"));
	    	  //list.addElement(h);
	      }
	      return h;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	/**
	 * @author ollie_sara
	 * @function getInfoTerimaPohon Data
	 * @param idpermohonan
	 * @return
	 * @throws Exception
	 */
	
	public Vector<Hashtable<String, String>> getMaklumatAsasTanahInfo(String idpermohonan)throws Exception {		
		Db db = null;
		String sql = "";
		try{
			list = new Vector<Hashtable<String, String>>();
			db = new Db();
			Statement stmt = db.getStatement();		    	
		    sql = " " +
		    	"SELECT THMU.ID_HAKMILIKURUSAN, THMU.NO_LOT,THMU.NO_SYIT,THMU.NO_PELAN ,THMU.ID_LUAS,THMU.LUAS,THMU.LUAS_BERSAMAAN,THMU.LOKASI "+
		    	" ,TRD.NAMA_DAERAH,TRM.NAMA_MUKIM,  TRL.KETERANGAN "+
		    	" ,TRLUAS.KETERANGAN AS KETERANGANLUAS   "+
		    	"  FROM TBLHTPHAKMILIKURUSAN THMU "+
		    	"  ,TBLRUJNEGERI TRN,TBLRUJDAERAH TRD, TBLRUJMUKIM TRM "+
		    	"  ,TBLRUJLOT TRL, TBLRUJLUAS TRLUAS  "+
		    	"  WHERE  "+
		    	"  THMU.ID_MUKIM = TRM.ID_MUKIM   "+
		    	"  AND THMU.ID_DAERAH = TRD.ID_DAERAH  "+
		    	"  AND TRD.ID_NEGERI = TRN.ID_NEGERI  "+ 
		    	"  AND THMU.ID_LOT = TRL.ID_LOT(+)   "+
		    	"  AND THMU.ID_LUAS = TRLUAS.ID_LUAS(+)   "+
		    	"  AND THMU.ID_PERMOHONAN = '"+idpermohonan+"'    ";
		  
		    ResultSet rs1 = stmt.executeQuery(sql);
		    Hashtable<String, String> h;
		    int bil = 1;		  
		    while(rs1.next()){
				  h = new Hashtable<String, String>();
				  h.put("bil", String.valueOf(bil));
				  h.put("idhakmilikurusan", Utils.isNull(rs1.getString("ID_HAKMILIKURUSAN"))); 
				  h.put("nama_daerah", Utils.isNull(rs1.getString("NAMA_DAERAH"))); 
				  h.put("nama_mukim", Utils.isNull(rs1.getString("NAMA_MUKIM"))); 
				  h.put("nama_mukim", Utils.isNull(rs1.getString("NAMA_MUKIM"))); 
				  h.put("keterangan", Utils.isNull(rs1.getString("KETERANGAN"))); 
				  h.put("nolot", Utils.isNull(rs1.getString("NO_LOT"))); 
				  h.put("nosyit", Utils.isNull(rs1.getString("NO_SYIT"))); 
				  h.put("nopelan", Utils.isNull(rs1.getString("NO_PELAN"))); 
				  h.put("keteranganluas", Utils.isNull(rs1.getString("keteranganluas"))); 
				  h.put("luas", Utils.isNull(rs1.getString("LUAS"))); 
				  h.put("luasH", Utils.isNull(rs1.getString("LUAS_BERSAMAAN"))); 
				  h.put("luasHFormat", Utils.formatLuas(rs1.getDouble("LUAS_BERSAMAAN")));	
	  		      h.put("lokasi", Utils.isNull(rs1.getString("LOKASI"))); 
	  			  bil++;
				  list.addElement(h);
				  
		    }
		    return list;
		    
		}finally{
			if (db != null) db.close();
		}	
	}

	public Vector<Hashtable<String, String>> getMaklumatAsasTanahKemaskini(String idhakmilikurusan)throws Exception {		
		Db db = null;
		String sql = "";	
		try{
			list = new Vector<Hashtable<String, String>>();
			db = new Db();
			Statement stmt = db.getStatement();
			sql ="select ID_HAKMILIKURUSAN,ID_NEGERI,ID_DAERAH,ID_MUKIM, " +
					"NO_LOT,NO_SYIT,NO_PELAN,ID_LOT,ID_LUAS,ID_LUAS_BERSAMAAN, " +
					"LUAS,LUAS_BERSAMAAN,LOKASI " +
					"from tblhtphakmilikurusan where id_hakmilikurusan = '"+idhakmilikurusan+"'";
	    				  
		  myLog.info("getMaklumatAsasTanahInfo: sql::"+sql);	  
		  ResultSet rs1 = stmt.executeQuery(sql);
		  Hashtable<String, String> h;
		  int bil = 1;
		  
		  while(rs1.next()){
			  h = new Hashtable<String, String>();
			  h.put("bil", String.valueOf(bil));
			  h.put("idhakmilikurusan", Utils.isNull(rs1.getString("ID_HAKMILIKURUSAN"))); 
			  h.put("idnegeri", Utils.isNull(rs1.getString("ID_NEGERI"))); 
			  h.put("iddaerah", Utils.isNull(rs1.getString("ID_DAERAH"))); 
			  h.put("idmukim", Utils.isNull(rs1.getString("ID_MUKIM"))); 
			  h.put("nolot", Utils.isNull(rs1.getString("NO_LOT"))); 
			  h.put("nosyit", Utils.isNull(rs1.getString("NO_SYIT"))); 
			  h.put("nopelan", Utils.isNull(rs1.getString("NO_PELAN"))); 
			  h.put("idlot", Utils.isNull(rs1.getString("ID_LOT"))); 
			  h.put("idluas", Utils.isNull(rs1.getString("ID_LUAS"))); 
			  h.put("idluasbersamaan", Utils.isNull(rs1.getString("ID_LUAS_BERSAMAAN"))); 
			  h.put("luas", Utils.isNull(rs1.getString("LUAS"))); 
			  h.put("luasLama", Utils.isNull(rs1.getString("LUAS_BERSAMAAN"))); 
			  h.put("luasH", Utils.isNull(rs1.getString("LUAS_BERSAMAAN"))); 
			  h.put("lokasi", Utils.isNull(rs1.getString("LOKASI"))); 
			  bil++;
			  list.addElement(h);
		  }
			return list;
		}finally{
			if (db != null) db.close();
		}	
	}
	
	public Hashtable<String, String> getLokasiidPermohonanInfo(String idhakmilikurusan) throws Exception {		
		Db db = null;
		String sql = "";
		try{
			db = new Db();
			Statement stmt = db.getStatement();
		    //SQLRenderer r = new SQLRenderer();
		    sql ="select pfd.NO_FAIL,thmu.ID_PERMOHONAN,thmu.NO_PELAN,thmu.NO_SYIT from tblpfdfail pfd, "+
		    " Tblpermohonan tp , tblhtpmaklumattanah tmt,tblhtphakmilikurusan thmu "+
			" where pfd.id_fail = tp.ID_FAIL "+
			" and thmu.ID_PERMOHONAN = tp.ID_PERMOHONAN "+
			" and thmu.ID_HAKMILIKURUSAN = tmt.ID_HAKMILIKURUSAN "+
			" and thmu.ID_HAKMILIKURUSAN="+idhakmilikurusan;
		  
		  myLog.info("getPermohonanInfo("+idhakmilikurusan+") : sql::"+sql);
		  
		  ResultSet rs1 = stmt.executeQuery(sql);
		  Hashtable<String, String> h = new Hashtable<String, String>();
		  while(rs1.next()){
			  h.put("lokasiIdPermohonan", Utils.isNull(rs1.getString("ID_PERMOHONAN"))); 
			  h.put("nosyit", Utils.isNull(rs1.getString("NO_SYIT"))); 
			  h.put("nopelan", Utils.isNull(rs1.getString("NO_PELAN"))); 
			  h.put("nofail", Utils.isNull(rs1.getString("NO_FAIL"))); 
		  }
			return h;
		}finally{
			if (db != null) db.close();
		}	
	}
	
	public Vector<Hashtable<String, String>> getLokasiTanahInfo(String idhakmilikurusan)throws Exception {		
		Db db = null;
		String sql = "";		
		try{
			list = new Vector<Hashtable<String, String>>();
			db = new Db();
			Statement stmt = db.getStatement();
		    //SQLRenderer r = new SQLRenderer();
			
		  sql = "select id_maklumattanah,id_hakmilikurusan,jarak_bandar,keterangan_bandar," +
		  		"jarak_jalan,keterangan_jalan,jarak_keretapi,keterangan_keretapi," +
		  		"jarak_sungai,keterangan_sungai,sempadan_utara,sempadan_selatan," +
		  		"sempadan_timur,sempadan_barat," +
		  		"lain_lain,keterangan,lokasi " +
		  		"from TBLHTPMAKLUMATTANAH where id_hakmilikurusan = '"+idhakmilikurusan+"'";
		  
		  myLog.info("getMaklumatAsasTanahInfo: sql::"+sql);
		  
		  ResultSet rs1 = stmt.executeQuery(sql);
		  Hashtable<String, String> h;
		  int bil = 1;
		  
		  while(rs1.next()){
			  h = new Hashtable<String, String>();
			  h.put("bil", String.valueOf(bil));
			  h.put("id_maklumattanah", Utils.isNull(rs1.getString("id_maklumattanah"))); 
			  h.put("id_hakmilikurusan", Utils.isNull(rs1.getString("id_hakmilikurusan"))); 
			  
			  h.put("jarak_bandar", Utils.isNull(rs1.getString("jarak_bandar"))); 
			  h.put("keterangan_bandar", Utils.isNull(rs1.getString("keterangan_bandar"))); 
			  h.put("jarak_jalan", Utils.isNull(rs1.getString("jarak_jalan"))); 
			  h.put("keterangan_jalan", Utils.isNull(rs1.getString("keterangan_jalan"))); 
			  h.put("jarak_keretapi", Utils.isNull(rs1.getString("jarak_keretapi"))); 
			  h.put("keterangan_keretapi", Utils.isNull(rs1.getString("keterangan_keretapi"))); 
			  h.put("jarak_sungai", Utils.isNull(rs1.getString("jarak_sungai"))); 
			  h.put("keterangan_sungai", Utils.isNull(rs1.getString("keterangan_sungai"))); 
			  h.put("sempadan_utara", Utils.isNull(rs1.getString("sempadan_utara"))); 
    		  h.put("sempadan_selatan", Utils.isNull(rs1.getString("sempadan_selatan"))); 
      		  h.put("sempadan_timur", Utils.isNull(rs1.getString("sempadan_timur"))); 
      		  h.put("sempadan_barat", Utils.isNull(rs1.getString("sempadan_barat"))); 
      		  h.put("lain_lain", Utils.isNull(rs1.getString("lain_lain"))); 
      		  h.put("keterangan", Utils.isNull(rs1.getString("keterangan"))); 
      		  h.put("lokasi", Utils.isNull(rs1.getString("lokasi"))); 
			  bil++;
			  list.addElement(h);
			  
		  }
			return list;
		}finally{
			if (db != null) db.close();
		}
		
	}
	
	//public Vector getPelanLakaranInfo(String idpermohonan,String idhakmilikurusan)throws Exception {
	public Vector<Hashtable<String, String>> getPelanLakaranInfo(String idhakmilikurusan)throws Exception {		
		Db db = null;
		String sql = "";		
		try{
			list = new Vector<Hashtable<String, String>>();
			db = new Db();
			Statement stmt = db.getStatement();
		    //SQLRenderer r = new SQLRenderer();
			
		  //sql = "select * from TBLHTPCHARTING where id_permohonan = '"+idpermohonan+"'";
//		   sql=" select tc.flag_charting,tc.ulasan,tc.jumlah_bayaran_proses  " +
//		   	   " from TBLHTPCHARTING tc,tblhtppermohonan tp,tblhtphakmilikurusan hu "+
//			   " where tc.ID_PERMOHONAN = tp.ID_PERMOHONAN "+
//			   " and hu.ID_PERMOHONAN = tp.ID_PERMOHONAN "+
//			   " and hu.ID_HAKMILIKURUSAN = tc.ID_HAKMILIKURUSAN "+
//			   " and tp.id_permohonan = '"+idpermohonan+"'"+
//			   " and hu.ID_HAKMILIKURUSAN = '"+idhakmilikurusan+"'";
		 
		    sql = "select tc.flag_charting,tc.ulasan,tc.jumlah_bayaran_proses " +
		    	  "from TBLHTPCHARTING tc WHERE tc.ID_HAKMILIKURUSAN='"+idhakmilikurusan+"'";
	    		
		  
		  myLog.info("getPelanLakaranInfo: sql::"+sql);
		  
		  ResultSet rs1 = stmt.executeQuery(sql);
		  Hashtable<String, String> h;
		  int bil = 1;
		  
		  while(rs1.next()){
			  h = new Hashtable<String, String>();
			  h.put("bil", String.valueOf(bil));
			  h.put("ulasan", Utils.isNull(rs1.getString("ulasan"))); 
			  h.put("bayarproses", Utils.isNull(rs1.getString("jumlah_bayaran_proses"))); 
			  h.put("flagcharting", Utils.isNull(rs1.getString("flag_charting"))); 
			  bil++;
			  list.addElement(h);
			  
		  }
			return list;
			
		}finally{
			if (db != null) db.close();
		}	
		
	}
	
	public Vector<Hashtable<String, String>> getPembayaranInfo(String idpermohonan)throws Exception {		
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
		try{
			list = new Vector<Hashtable<String, String>>();
			db = new Db();
			Statement stmt = db.getStatement();
		    //SQLRenderer r = new SQLRenderer();		
		    sql = "select id_bayaran,no_resit,jumlah_bayaran,no_baucer," +
		  		"nama_bank,id_carabayar,tarikh_resit,tarikh_baucer," +
		  		"tarikh_terima from tblhtpbayaran where id_jenisbayaran = 20 and  id_permohonan = '"+idpermohonan+"'";
//		   sql=" select * from TBLHTPCHARTING tc,tblhtppermohonan tp,tblhtphakmilikurusan hu "+
//			   " where tc.ID_PERMOHONAN = tp.ID_PERMOHONAN "+
//			   " and hu.ID_PERMOHONAN = tp.ID_PERMOHONAN "+
//			   " and hu.ID_HAKMILIKURUSAN = tc.ID_HAKMILIKURUSAN "+
//			   " and tp.id_permohonan = '"+idpermohonan+"'";
//			   //" and hu.ID_HAKMILIKURUSAN = '"+idhakmilikurusan+"'";
		  //myLog.info(sql);	  
		  ResultSet rs1 = stmt.executeQuery(sql);
		  Hashtable<String, String> h;
		  int bil = 1;		  
		  while(rs1.next()){
			  h = new Hashtable<String, String>();		  
			  h.put("bil", String.valueOf(bil));
			  h.put("idbayaran", Utils.isNull(rs1.getString("id_bayaran"))); 
			  h.put("NoResit", Utils.isNull(rs1.getString("no_resit"))); 
			  h.put("jumlahbayaran", Utils.isNull(rs1.getString("jumlah_bayaran"))); 
			  h.put("nobaucer", Utils.isNull(rs1.getString("no_baucer"))); 
			  h.put("namabank", Utils.isNull(rs1.getString("nama_bank"))); 
			  h.put("idcarabayar", Utils.isNull(rs1.getString("id_carabayar"))); 
			  if(rs1.getDate("tarikh_resit")==null){
	    		  h.put("tarikhresit", "");
	      		}else{
	      			h.put("tarikhresit", sdf.format(rs1.getDate("tarikh_resit"))); 
	      		}
			  
			  if(rs1.getDate("tarikh_baucer")==null){
	    		  h.put("tarikhbaucer", "");
	      		}else{
	      			h.put("tarikhbaucer", sdf.format(rs1.getDate("tarikh_baucer"))); 
	      		}
			  
			  if(rs1.getDate("tarikh_terima")==null){
	    		  h.put("tarikhterima", "");
	      		}else{
	      			h.put("tarikhterima", sdf.format(rs1.getDate("tarikh_terima"))); 
	      		}
			  bil++;
			  list.addElement(h);
		  
		  }
		  return list;
		
		}finally{
			if (db != null) db.close();
		}	
	}

	public Vector<Hashtable<String, String>> getKeputusanInfo(String idpermohonan)throws Exception {
		Db db = null;
		String sql = "";
		//myLog.debug("Idpermohonan model"+idpermohonan);
		try{
			list = new Vector<Hashtable<String, String>>();
			db = new Db();
			Statement stmt = db.getStatement();
			//SQLRenderer r = new SQLRenderer();
			sql = "" +
			" SELECT NO_RUJUKAN_KEPUTUSAN " +
	  		" ,NVL(REPLACE(STATUS,'0',''),'0') STATUS,ID_KEPUTUSANMOHON "+
	  		" ,TARIKH_KEPUTUSAN,ULASAN, FLAG_NOTIFIKASI " +
	  		" FROM TBLHTPKEPUTUSANMOHON "+
	  		" WHERE ID_PERMOHONAN = "+idpermohonan;	  
			myLog.info("getKeputusanInfo: sql::"+sql);
			ResultSet rs1 = stmt.executeQuery(sql);
			Hashtable<String, String> h;
			int bil = 1;
	  	  	while(rs1.next()){
	  	  		h = new Hashtable<String, String>();
	  	  		h.put("bil", String.valueOf(bil));
	  	  		h.put("norujukankeputusan", Utils.isNull(rs1.getString("no_rujukan_keputusan"))); 
	  	  		h.put("status", Utils.isNull(rs1.getString("status"))); 
	  	  		h.put("ulasan", Utils.isNull(rs1.getString("ulasan"))); 
	  	  		h.put("idkeputusanmohon", Utils.isNull(rs1.getString("id_keputusanmohon"))); 
	  	  		if(rs1.getDate("tarikh_keputusan")==null){
	  	  			h.put("tarikhkeputusan", "");
	      		}else{
	      			h.put("tarikhkeputusan", sdf.format(rs1.getDate("tarikh_keputusan"))); 
	      		}			  
	  	  		
	  	  		h.put("flag_notifikasi", Utils.isNull(rs1.getString("FLAG_NOTIFIKASI"))); 
	  	  		
	  	  		bil++;
	  	  		list.addElement(h);
	  	  	}
	  	  	return list;
	  	  	
		}finally{
			if (db != null) db.close();
		}	
		
	}

	public Vector<Hashtable<String, String>> getSenaraiNotis5A(String idpermohonan)throws Exception {	
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		myLog.debug("Idpermohonan model"+idpermohonan);
		try{
			list = new Vector<Hashtable<String, String>>();
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_NOTIS5A,TARIKH_NOTIS5A,TARIKH_LUPUT1"+
	  			" FROM TBLHTPNOTIS5A WHERE ID_PERMOHONAN = '"+idpermohonan+"'";			
			myLog.info("getSenaraiNotis5A: sql::"+sql);	  
			ResultSet rs1 = stmt.executeQuery(sql);
			Hashtable<String, String> h;
			int bil = 1;
			while(rs1.next()){
				h = new Hashtable<String, String>();
				h.put("bil", String.valueOf(bil));
				if(rs1.getDate("tarikh_notis5a")==null){
					//h.put("tarikhnotis5a", "-TIADA-");
					//07/02/2011
					h.put("tarikhnotis5a", "-");
				}else{
					h.put("tarikhnotis5a",  sdf.format(rs1.getDate("tarikh_notis5a"))); 
				}
				if(rs1.getString("id_notis5a")==null){
					h.put("idnotis5a", "");
				}else{
					h.put("idnotis5a", rs1.getString("id_notis5a")); 
				}
				if(rs1.getDate("tarikh_luput1")==null){
					h.put("tarikhluput1", "");
				}else{
					h.put("tarikhluput1",  sdf.format(rs1.getDate("tarikh_luput1"))); 
				}
//		  if(rs1.getString("ulasan")==null){
//    		  h.put("ulasan", "");
//      		}else{
//      			h.put("ulasan", rs1.getString("ulasan")); 
//      		}
				bil++;
				list.addElement(h);
			}
		
			return list;
		
		}finally{
			if (db != null) db.close();
		}	
	}

	public Vector<Hashtable<String, String>> getBuktiBayaranNotis5A(String idpermohonan)throws Exception {	
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//myLog.debug("Idpermohonan model"+idpermohonan);
		try{
			list = new Vector<Hashtable<String, String>>();
			db = new Db();
			Statement stmt = db.getStatement();
			//SQLRenderer r = new SQLRenderer();
		
			sql =  "SELECT ID_BAYARAN,no_resit,no_baucer,jumlah_bayaran,tarikh_baucer,tarikh_resit " +
	  			" from tblhtpbayaran where id_jenisbayaran=23 and  id_permohonan = '"+idpermohonan+"'";

			myLog.info("getPelanLakaranInfo: sql::"+sql);
	  	  	ResultSet rs1 = stmt.executeQuery(sql);
	  	  	Hashtable<String, String> h;
	  	  	int bil = 1;
	  	  	while(rs1.next()){
	  	  		h = new Hashtable<String, String>();		  
	  	  		h.put("bil", String.valueOf(bil));
	  	  		h.put("idBayaran", Utils.isNull(rs1.getString("ID_BAYARAN"))); 
	  	  		if(rs1.getString("no_resit")==null){
	  	  			h.put("noresit", "");
	  	  		}else{
	  	  			h.put("noresit", rs1.getString("no_resit")); 
	  	  		}
	  	  		if(rs1.getString("no_baucer")==null){
	  	  			h.put("nobaucer", "");
	  	  		}else{
	  	  			h.put("nobaucer", rs1.getString("no_baucer")); 
	  	  		}
	  	  		if(rs1.getString("jumlah_bayaran")==null){
	  	  			h.put("jumlahbayaran", "0.00");
	  	  		}else{
	  	  			//h.put("jumlahbayaran", rs1.getString("jumlah_bayaran")); 
			  		 h.put("jumlahbayaran", Utils.format2Decimal(rs1.getDouble("jumlah_bayaran"))); 

	  	  		}
	  	  		if(rs1.getDate("tarikh_baucer")==null){
	  	  			h.put("tarikhbaucer", "");
	  	  		}else{
	  	  			h.put("tarikhbaucer",  sdf.format(rs1.getDate("tarikh_baucer"))); 
	  	  		}
	  	  		if(rs1.getDate("tarikh_resit")==null){
	  	  			h.put("tarikhresit", "");
	  	  		}else{
	  	  			h.put("tarikhresit",  sdf.format(rs1.getDate("tarikh_resit"))); 
	  	  		}
	  	  		bil++;
	  	  		list.addElement(h);
	  	  	}
	  	  	return list;
		
		}finally{
			if (db != null) db.close();
		}	
	}
	
	public Hashtable<String, String> UpdateLokasiTanah(Hashtable<String, String> data)throws Exception {	
		Db db = null;
		String sql = "";
		//Date now = new Date();
		try {
			
			// long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			//String seksyen = "3";
			String idUser = (String) data.get("idUser");
			String idmaklumattanah = (String) data.get("idmaklumattanah");
			String idhakmilkurusan = (String) data.get("idhakmilkurusan");
			String txtbandar = (String) data.get("txtbandar");
			String txtbandarperihal = (String) data.get("txtbandarperihal");
			String txtLebuhRaya = (String) data.get("txtLebuhRaya");
			String txtLebuhRayaperihal = (String) data.get("txtLebuhRayaperihal");
			String txtJkeretapi = (String) data.get("txtJkeretapi");
			String txtJkeretapiperihal = (String)data.get("txtJkeretapiperihal");
			String txtSgPntai = (String) data.get("txtSgPntai");
			String txtSgPntaiperihal = (String) data.get("txtSgPntaiperihal");
			String txtSempadanUtara = (String) data.get("txtSempadanUtara");
			String txtSempadanSelatan = (String) data.get("txtSempadanSelatan");
			String txtSempadanTimur = (String) data.get("txtSempadanTimur");
			String txtSempadanBarat = (String) data.get("txtSempadanBarat");
			String txtSempadanKeteranganLain = (String) data.get("txtSempadanKeteranganLain");
			
	
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			  r.update("id_maklumattanah", idmaklumattanah);
			  r.add("id_masuk", idUser);
			  r.add("id_kemaskini", idUser);
			  //r.add("id_maklumattanah", idMaklumatTanah);
			  r.add("id_hakmilikurusan", idhakmilkurusan);
			  r.add("jarak_bandar", txtbandar);
			  r.add("keterangan_bandar", txtbandarperihal);
			  r.add("jarak_jalan", txtLebuhRaya);
			  r.add("keterangan_jalan", txtLebuhRayaperihal);
			  r.add("jarak_keretapi", txtJkeretapi);
			  r.add("keterangan_keretapi", txtJkeretapiperihal);
			  r.add("jarak_sungai",txtSgPntai);
			  r.add("keterangan_sungai",txtSgPntaiperihal);
			  r.add("sempadan_utara", txtSempadanUtara);
			  r.add("sempadan_selatan", txtSempadanSelatan);
			  r.add("sempadan_timur", txtSempadanTimur);
			  r.add("sempadan_barat", txtSempadanBarat);
			  r.add("lain_lain", txtSempadanKeteranganLain);
	
			sql = r.getSQLUpdate("TBLHTPMAKLUMATTANAH");
			myLog.debug("FrmSenaraiFailPajakanKecilData:janaFail::sql:::"+ sql);
			stmt.executeUpdate(sql);
			return data;
		} finally {
			if (db != null)
				db.close();
		}
				
	}
	
	public Hashtable<String, String> getDataNotis5A(String idnotis)throws Exception {	
		Db db = null;
		Hashtable<String, String> h = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			list = new Vector<Hashtable<String, String>>();
			db = new Db();
			Statement stmt = db.getStatement();
			//SQLRenderer r = new SQLRenderer();	
			sql = "" +
				" SELECT * FROM TBLHTPNOTIS5A "+
	  			" WHERE ID_NOTIS5A = '"+idnotis+"'";	  
			myLog.info(sql);
			ResultSet rs1 = stmt.executeQuery(sql);
			h = new Hashtable<String, String>();
			int bil = 1;
			while(rs1.next()){
				h.put("bil", String.valueOf(bil));
				h.put("norujukanmop", Utils.isNull(rs1.getString("no_rujukan_mop"))); 
		  	  	if(rs1.getDate("tarikh_notis5a")==null){
		  	  		h.put("tarikhnotis5a", "");
		  	  	}else{
		  	  		h.put("tarikhnotis5a", sdf.format(rs1.getDate("tarikh_notis5a"))); 
		  	  	}
		  	  	if(rs1.getDate("tarikh_luput1")==null){
		  	  		h.put("tarikhluput1", "");
		  	  	}else{
		  	  		h.put("tarikhluput1", sdf.format(rs1.getDate("tarikh_luput1"))); 
		  	  	}
		  	  	if(rs1.getDate("tarikh_luput2")==null){
		  	  		h.put("tarikhluput2", "");
		  	  	}else{
		  	  		h.put("tarikhluput2", sdf.format(rs1.getDate("tarikh_luput2"))); 
		  	  	}
		  	  	if(rs1.getDate("tarikh_luput3")==null){
		  	  		h.put("tarikhluput3", "");
		  	  	}else{
		  	  		h.put("tarikhluput3", sdf.format(rs1.getDate("tarikh_luput3"))); 
		  	  	}
		  	  	if(rs1.getString("kadar_cukai")==null){
		  	  		h.put("kadarcukai", "0.00");
		  	  	}else{
		  	  		h.put("kadarcukai", Utils.format2Decimal(rs1.getDouble("kadar_cukai"))); 
		  	  		//h.put("kadarcukai", rs1.getDouble("kadar_cukai")); 
		  	  	}
		  	  	if(rs1.getString("bayar_notis")==null){
		  	  		h.put("bayarnotis", "0.00");
		  	  	}else{
		  	  		h.put("bayarnotis", Utils.format2Decimal(rs1.getDouble("bayar_notis"))); 
		  	  		//h.put("bayarnotis", rs1.getDouble("bayar_notis")); 
		  	  	}
		  	  	if(rs1.getString("bayaran_notisf")==null){
		  	  		h.put("bayarnotisf", "0.00");
		  	  	}else{
		  	  		h.put("bayarnotisf", Utils.format2Decimal(rs1.getDouble("bayaran_notisf"))); 
		  	  		//h.put("bayarnotisf", rs1.getDouble("bayaran_notisf")); 
		  	  	}	  
		  	  	if(rs1.getString("bayar_premium")==null){
		  	  		h.put("bayarpremium", "0.00");
		  	  	}else{
		  	  		h.put("bayarpremium", Utils.format2Decimal(rs1.getDouble("bayar_premium"))); 
		  	  		//h.put("bayarpremium", rs1.getDouble("bayar_premium")); 
		  	  	}
		  	  	if(rs1.getString("rayuan_premium")==null){
		  	  		h.put("rayuanpremium", "0.00");
		  	  	}else{
		  	  		h.put("rayuanpremium", Utils.format2Decimal(rs1.getDouble("rayuan_premium"))); 
		  	  		//h.put("rayuanpremium", rs1.getDouble("rayuan_premium")); 
		  	  	}
		  	  	if(rs1.getString("rayuan_lain")==null){
		  	  		h.put("rayuanlain", "0.00");
		  	  	}else{
		  	  		h.put("rayuanlain", Utils.format2Decimal(rs1.getDouble("rayuan_lain"))); 
		  	  		//h.put("rayuanlain", rs1.getDouble("rayuan_lain")); 
		  	  	}
		  	  	h.put("perihalrayuan", Utils.isNull(rs1.getString("perihal_rayuan"))); 
		  	  	if(rs1.getDate("tarikh_terima")==null){
		  	  		h.put("tarikhterima", "");
		  	  	}else{
		  	  		h.put("tarikhterima", sdf.format(rs1.getDate("tarikh_terima"))); 
		  	  	}
		  	  	if(rs1.getString("bayar_daftar_hakmilik")==null){
		  	  		h.put("bayarhakmilik", "0.00");
		  	  	}else{
		  	  		h.put("bayarhakmilik", Utils.format2Decimal(rs1.getDouble("bayar_daftar_hakmilik"))); 
		  	  		//h.put("bayarhakmilik", rs1.getDouble("bayar_daftar_hakmilik")); 
		  	  	}
		  	  	if(rs1.getString("bayar_ukur")==null){
		  	  		h.put("bayarukur", "0.00");
		  	  	}else{
		  	  		h.put("bayarukur", Utils.format2Decimal(rs1.getDouble("bayar_ukur"))); 
		  	  		//h.put("bayarukur", rs1.getDouble("bayar_ukur")); 
		  	  	}
		  	  	if(rs1.getString("bayar_perenggan")==null){
		  	  		h.put("bayarperenggan", "0.00");
		  	  	}else{
		  	  		h.put("bayarperenggan",Utils.format2Decimal(rs1.getDouble("bayar_perenggan"))); 
		  	  		//h.put("bayarperenggan",rs1.getDouble("bayar_perenggan")); 
		  	  	}
		  	  	if(rs1.getString("bayar_sempadan")==null){
		  	  		h.put("bayarsempadan", "0.00");
		  	  	}else{
		  	  		h.put("bayarsempadan", Utils.format2Decimal(rs1.getDouble("bayar_sempadan"))); 
		  	  		//h.put("bayarsempadan", rs1.getDouble("bayar_sempadan")); 
		  	  	}
		  	  	if(rs1.getString("bayaran_lain")==null){
		  	  		h.put("bayaranlain", "0.00");
		  	  	}else{
		  	  		h.put("bayaranlain", Utils.format2Decimal(rs1.getDouble("bayaran_lain"))); 
		  	  		//h.put("bayaranlain", rs1.getDouble("bayaran_lain")); 
		  	  	}
		  	  	if(rs1.getString("bayaran_lain2")==null){
		  	  		h.put("bayaranlain2", "0.00");
		  	  	}else{
		  	  		h.put("bayaranlain2", Utils.format2Decimal(rs1.getDouble("bayaran_lain2"))); 
		  	  		//h.put("bayaranlain2", rs1.getDouble("bayaran_lain2")); 
		  	  	}
		  	    if(rs1.getString("bayaran_lain3")==null){
		  	    	h.put("bayaranlain3", "0.00");
		  	    }else{
		  	    	h.put("bayaranlain3", Utils.format2Decimal(rs1.getDouble("bayaran_lain3"))); 
		  	    	//h.put("bayaranlain2", rs1.getDouble("bayaran_lain3")); 
		  	    }		  
		  	  //h.put("bayaranlain", Utils.isNull(rs1.getString("bayaran_lain"))); 
		  	  //h.put("bayaranlain2", Utils.isNull(rs1.getString("bayaran_lain2")));
		  	  //h.put("bayaranlain3", Utils.isNull(rs1.getString("bayaran_lain3")));
		  	  //h.put("jumBayaran", Utils.isNull(rs1.getString("jumlah_bayaran"))); 
		  	  //h.put("bayarfail", Utils.isNull(rs1.getString("bayaran_fail"))); 
		  	    if(rs1.getString("jumlah_bayaran")==null){
		  	    	h.put("jumBayaran", "0.00");
		  	    }else{
		  	    	h.put("jumBayaran", Utils.format2Decimal(rs1.getDouble("jumlah_bayaran"))); 
		  	    	//h.put("jumBayaran", rs1.getDouble("jumlah_bayaran")); 
		  	    }
		  	    if(rs1.getString("bayaran_fail")==null){
		  	    	h.put("bayarfail", "0.00");
		  	    }else{
		  	    	h.put("bayarfail", Utils.format2Decimal(rs1.getDouble("bayaran_fail"))); 
		  	    	//h.put("bayarfail", rs1.getDouble("bayaran_fail")); 
		  	    } 
		  	    
		  	    /** Syaz Add 05112014 */
		  	    if(rs1.getDate("tarikh_rayuan")==null){
		  	  		h.put("tarikhRayuan", "");
		  	  	}else{
		  	  		h.put("tarikhRayuan", sdf.format(rs1.getDate("tarikh_rayuan"))); 
		  	  	}
		  	    if(rs1.getString("tempoh_rayuan")==null){
		  	    	h.put("tempohRayuan", "");
		  	    }else{
		  	    	h.put("tempohRayuan", rs1.getString("tempoh_rayuan")); 
		  	    }
		  	    
		  	    bil++;
		  		//list.addElement(h);
			}
			return h;
			
	    }catch (SQLException se) { 
	    	throw new Exception("Ralat : Masalah penyimpanan data "+se.getMessage());
		}finally{
			if (db != null) db.close();
		}	
		
	}

	public Object getidGambarfromIdhalmilikUrusan(String idhakmilikurusan) throws Exception {
		Db db = null;
		String sql = "";
		//String idGambar = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
	
			r.add("id_gambar");
			
			r.add("id_hakmilik", idhakmilikurusan);
	
			sql = r.getSQLSelect("tblhtpgambar");
			ResultSet rs = stmt.executeQuery(sql);
	
			if (rs.next()){
				return rs.getString("id_gambar").toString();
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		
	}

	public Vector<Hashtable<String, String>> getListPPT(String carianNofail)throws Exception {	
		listPPT = new Vector<Hashtable<String, String>>();	
	    Db db = null;
	    String sql = "";
	    String nofail = carianNofail.trim();
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, p.no_rujukan_surat, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk ";
	    		sql +=" FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +=" AND p.id_status = s.id_status ";
	    		sql +=" AND f.id_suburusan = '52' ";
	    		sql +=" AND p.id_status IN (76)";
	    		sql +=" AND NVL(p.flag_permohonan_htp,0) <> '1'";
	    		
	    		//NO FAIL
				if (carianNofail != "" && carianNofail != null) {
					if (!nofail.equals("")) {
						sql = sql + " AND UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%'";
					}
				}//close carian by nofail
	
	    		sql +=" ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
	      
	    		ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable<String, String> h;
	    		int bil = 1;

	    		while (rs.next()) {
	    			h = new Hashtable<String, String>();
	    			h.put("bil", String.valueOf(bil));
	    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
	    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
	    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
	    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
	    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
	    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
	    			h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
	    			if(rs.getString("no_fail") == null){
	    				h.put("no_fail","Belum Diluluskan");
	    			}else{
	    				h.put("no_fail",rs.getString("no_fail"));
	    			}
	    			listPPT.addElement(h);
	    			bil++;
	    			
	      }//close while
	      
	    }// 
	    finally {
	      if (db != null) db.close();
	    }
	    return listPPT;
	  }//close setListPPT
	
	public Vector<Hashtable<String, String>> getListMaklumatTanah(String idPermohonan,String lot) throws Exception{
		list = new Vector<Hashtable<String, String>>();	
		Db db = null;
		String sql = "";
		String noLOT = lot.trim();
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT DISTINCT m.catatan, p.id_permohonan, ls.keterangan as unit1, lt.keterangan as unit2, m.id_hakmilik, m.id_negeri, n.nama_negeri, ";
				sql += " m.id_jenishakmilik, m.id_daerah, m.id_mukim, mk.nama_mukim, m.id_unitluaslot, m.luas_ambil, ";
				sql += " m.no_hakmilik, m.no_lot, m.luas_lot, m.no_pt, m.tarikh_daftar, m.tarikh_luput, m.tempoh_luput, ";
				sql += " m.lokasi,m.syarat_nyata,m.syarat_khas,m.sekatan_hak,m.sekatan_kepentingan,m.no_syit, jh.keterangan as jenis_hakmilik, m.id_kategoritanah, ";
				sql += " m.flag_ambil_segera,d.nama_daerah ";
				sql += " FROM Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, "; 
				sql += " Tblrujdaerah d";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";  
				sql += " AND m.id_negeri = n.id_negeri "; 
				sql += " AND m.id_daerah = d.id_daerah(+)";
				sql += " AND ls.id_luas(+) = m.id_unitluaslot ";  
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
				sql += " AND m.id_lot = lt.id_lot(+) ";
				sql += " AND m.id_mukim = mk.id_mukim(+) ";  
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				//NO LOT
				if (lot != "" && lot != null) {
					if (!noLOT.equals("")) {
						sql += " AND UPPER(m.no_lot) LIKE '%" + noLOT.toUpperCase() + "%'";
					}
				}//close if nolot
				
				sql += " ORDER BY m.no_lot asc, mk.nama_mukim asc";
		
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable<String, String> h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable<String, String>();
					h.put("bil", String.valueOf(bil));
					h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("kod_lot", rs.getString("unit2")==null?"":rs.getString("unit2"));
					h.put("unitluaslot", rs.getString("unit1")==null?"-":rs.getString("unit1"));			
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
					h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
					h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
					h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
					h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
					h.put("nama_mukim", rs.getString("nama_mukim")==null?"-":rs.getString("nama_mukim"));
					h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
					h.put("flag_ambil_segera", rs.getString("flag_ambil_segera")==null?"":rs.getString("flag_ambil_segera"));
					h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
					
					if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
						
						double luasAmbil = rs.getDouble("luas_ambil");
						String LA = Utils.formatLuasHM(luasAmbil);
						h.put("luas_ambil",LA);
								
					}else{
						h.put("luas_ambil","0");
					}
					
					if(rs.getString("id_kategoritanah") != null && rs.getString("id_kategoritanah") != ""){
						
						if(rs.getString("id_kategoritanah").equals("2")){
							h.put("unitByKategori", "Hektar");
						}else{
							h.put("unitByKategori", "Meter Persegi");
						}			
					}else{
						h.put("unitByKategori", "");
					}

					if(rs.getString("id_kategoritanah") != null && rs.getString("id_kategoritanah") != ""){						
						if(rs.getString("id_kategoritanah").equals("2")){	
							
							if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
								double luasAmbil = rs.getDouble("luas_ambil") * 10000;
								String LAH = Utils.formatLuasHM(luasAmbil);
								h.put("nilaiTanah",LAH);
							}else{
								h.put("nilaiTanah","0");
							}
							
						}else{						
							if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
								double luasAmbil = rs.getDouble("luas_ambil");
								String LAM = Utils.formatLuasHM(luasAmbil);
								h.put("nilaiTanah",LAM);
							}else{
								h.put("nilaiTanah","0");
							}						
						}			
					}else{
						h.put("nilaiTanah","0");
					}
					
					
					h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"-":rs.getString("id_kategoritanah"));
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"-":rs.getString("no_hakmilik"));
					h.put("no_lot", rs.getString("no_lot")==null?"-":rs.getString("no_lot"));
					h.put("luas_lot", String.valueOf(rs.getString("luas_lot")==null?"":rs.getDouble("luas_lot")));
					h.put("no_pt", rs.getString("no_pt")==null?"":rs.getString("no_pt"));
					h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
					
					h.put("tempoh_luput", rs.getString("tempoh_luput")==null?"":rs.getString("tempoh_luput"));
					
					h.put("lokasi", rs.getString("lokasi")==null?"":rs.getString("lokasi"));
					h.put("syarat_nyata", rs.getString("syarat_nyata")==null?"":rs.getString("syarat_nyata"));
					h.put("syarat_khas", rs.getString("syarat_khas")==null?"":rs.getString("syarat_khas"));
					
					h.put("sekatan_hak", rs.getString("sekatan_hak")==null?"":rs.getString("sekatan_hak"));
					h.put("sekatan_kepentingan", rs.getString("sekatan_kepentingan")==null?"":rs.getString("sekatan_kepentingan"));
					h.put("no_syit", rs.getString("no_syit")==null?"":rs.getString("no_syit"));
					h.put("jenis_hakmilik", rs.getString("jenis_hakmilik")==null?"":rs.getString("jenis_hakmilik"));
					
					list.addElement(h);
					bil++;	
				}
			
		}finally {
			if(db != null) db.close();
		}
		return list;
	}//close setLISTHM
	
	
	public static void simpanPermohonanPPT(Hashtable<String, String> data, String idHakmilik, long id_hakmilikurusan) throws Exception {	
			Connection conn = null;
			Db db = null;
			String sql = "";
			//String sql2 = "";
				  
			try {
				  
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);
					Statement stmt = db.getStatement();
		        
					//id permohonan ppt untuk update flag di tblpptpermohonan (dah send ke htp atau blom)
					String idpermohonanPPT = (String)data.get("idpermohonanPPT");
					
					//id permohonan / idfail?? tblhtppermohonan
					String idpermohonan = (String)data.get("idpermohonan");
					
					String idUser = (String)data.get("idUser");
				
					//update flag tblpermohonanppt
					SQLRenderer rx = new SQLRenderer();
					rx.update("id_permohonan", idpermohonanPPT);
					rx.add("flag_permohonan_htp","1");
					rx.add("id_kemaskini",idUser);
					rx.add("tarikh_kemaskini",rx.unquote("sysdate"));
					sql = rx.getSQLUpdate("TBLPPTPERMOHONAN");
					stmt.executeUpdate(sql);
				
					//get max id warta
					String id_warta = "";
					sql = "SELECT DISTINCT MAX(ID_WARTA)AS ID_WARTA FROM TBLPPTWARTA WHERE ID_PERMOHONAN = '"+idpermohonanPPT+"'";
					ResultSet rsw = stmt.executeQuery(sql);
					while (rsw.next()) {
						id_warta = rsw.getString("ID_WARTA");	 
					}
					
					//get data hakmilik from tblppthakmilik
					String no_hakmilik = "";
					String id_unitluas = "";
					String no_lot = "";
					String luas_ambil = "";
					String no_syit = "";
					String id_daerah = "";
					String id_mukim = "";
					String id_negeri = "";
					String no_warta = "";
					String tarikh_warta = null;
					String id_borangk = "";
					String id_lot = "";
					
					sql =   " SELECT A.NO_HAKMILIK, A.ID_UNITLUASLOT, A.NO_LOT, A.LUAS_AMBIL, A.NO_SYIT, A.ID_DAERAH, A.ID_MUKIM, A.ID_NEGERI, ";
					sql += " C.ID_WARTA, C.NO_WARTA, C.TARIKH_WARTA, A.ID_LOT, D.ID_BORANGK ";
					sql += " FROM TBLPPTHAKMILIK A, TBLPPTPERMOHONAN B, TBLPPTWARTA C, TBLPPTBORANGK D, TBLPPTHAKMILIKBORANGK E ";
					sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN ";
					sql += " AND C.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";
					sql += " AND E.ID_BORANGK = D.ID_BORANGK(+) ";
					sql += " AND E.ID_HAKMILIK(+) = A.ID_HAKMILIK ";
					sql += " AND A.ID_HAKMILIK = '"+idHakmilik+"'";
					if(id_warta!=null){
						sql += " AND C.ID_WARTA = '"+id_warta+"'";
					}
					//myLog.debug("sql : "+sql);
					ResultSet rsX = stmt.executeQuery(sql);			      
					while (rsX.next()) {
		
						no_hakmilik = rsX.getString("NO_HAKMILIK");	 
						id_unitluas = rsX.getString("ID_UNITLUASLOT")== null?"":rsX.getString("ID_UNITLUASLOT"); 
						no_lot = rsX.getString("NO_LOT")== null?"":rsX.getString("NO_LOT"); 	 
						luas_ambil = rsX.getString("LUAS_AMBIL")== null?"":rsX.getString("LUAS_AMBIL"); 
						no_syit = rsX.getString("NO_SYIT")== null?"":rsX.getString("NO_SYIT"); 	 
						id_daerah = rsX.getString("ID_DAERAH")== null?"":rsX.getString("ID_DAERAH");  
						id_mukim = rsX.getString("ID_MUKIM")== null?"":rsX.getString("ID_MUKIM");  
						id_negeri = rsX.getString("ID_NEGERI")== null?"":rsX.getString("ID_NEGERI"); 	  
						no_warta = rsX.getString("NO_WARTA")== null?"":rsX.getString("NO_WARTA"); 	
						id_lot = rsX.getString("ID_LOT")== null?"":rsX.getString("ID_LOT"); 	
						tarikh_warta = rsX.getString("TARIKH_WARTA")== null?"":sdf.format(rsX.getDate("TARIKH_WARTA"));
						id_borangk = rsX.getString("ID_BORANGK")== null?"":rsX.getString("ID_BORANGK"); 
					}
				
					//xleh nak simpan data lebih dari 1
					
					String TW = "to_date('" + tarikh_warta + "','dd/MM/yyyy')";
					
						//insert into tblhtphakmilikurusan
						SQLRenderer r = new SQLRenderer();
						r.add("ID_HAKMILIKURUSAN", id_hakmilikurusan);
						r.add("id_permohonan", idpermohonan);
						r.add("NO_HAKMILIK", no_hakmilik);
						r.add("ID_LUAS", id_unitluas);
						r.add("NO_LOT", no_lot);
						r.add("ID_LOT", id_lot);
						r.add("LUAS", luas_ambil);
						r.add("NO_SYIT", no_syit);
						r.add("ID_DAERAH", id_daerah);
						r.add("ID_MUKIM", id_mukim);
						r.add("ID_NEGERI", id_negeri);
						r.add("NO_WARTA", no_warta);
						r.add("TARIKH_WARTA", r.unquote(TW));
						r.add("ID_MASUK", idUser);
						r.add("TARIKH_MASUK",r.unquote("sysdate"));
						sql = r.getSQLInsert("TBLHTPHAKMILIKURUSAN");
						myLog.debug("sql : "+sql);
						stmt.executeUpdate(sql);
			    						
					//insert into tblhtpinfoborangk
					SQLRenderer rk = new SQLRenderer();
					rk.add("ID_HAKMILIK", id_hakmilikurusan);	
					rk.add("ID_BORANGK",id_borangk);
					rk.add("ID_MASUK",idUser);
					rk.add("TARIKH_MASUK",rk.unquote("sysdate"));
					sql = rk.getSQLInsert("TBLHTPINFOBORANGK");
					myLog.debug("sql : "+sql);
					stmt.executeUpdate(sql);
					
			      conn.commit();	
			      
			      
			    }catch (SQLException se) { 
			    	try {
			    		conn.rollback();
			    	} catch (SQLException se2) {
			    		throw new Exception("Rollback error:"+se2.getMessage());
			    	}
			    	throw new Exception("Ralat : Masalah penyimpanan data ");
			    }
			    finally {
			      if (db != null) db.close();
			    }

	}//close simpanPermohonanPPT
	
	public Vector<Hashtable<String, String>> getListBorangK() throws Exception{		
		list = new Vector<Hashtable<String, String>>();		
		Db db = null;
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT DISTINCT B.NO_HAKMILIK, B.NO_LOT, B.NO_SYIT,B.NO_WARTA, B.TARIKH_WARTA,B.LUAS" +
						",C.NAMA_DAERAH, D.NAMA_MUKIM,  E.TARIKH_BORANGK,E.ID_BORANGK  ";
				sql += " ,F.KETERANGAN AS KOD_LUAS ";
				sql += " FROM TBLHTPINFOBORANGK A, TBLHTPHAKMILIKURUSAN B, TBLRUJDAERAH C, TBLRUJMUKIM D, TBLPPTBORANGK E, TBLRUJLUAS F ";
				sql += " WHERE A.ID_HAKMILIK = B.ID_HAKMILIKURUSAN ";
				sql += " AND B.ID_DAERAH = C.ID_DAERAH(+) ";
				sql += " AND B.ID_MUKIM = D.ID_MUKIM(+) ";
				sql += " AND A.ID_BORANGK = E.ID_BORANGK(+) ";
				sql += " AND B.ID_LUAS = F.ID_LUAS(+)";
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable<String, String> h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable<String, String>();
					h.put("bil", String.valueOf(bil));
					h.put("id_borangk", rs.getString("ID_BORANGK")==null?"":rs.getString("ID_BORANGK"));		
					h.put("no_hakmilik", rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));					
					h.put("no_lot", rs.getString("NO_LOT")==null?"":rs.getString("NO_LOT"));
					h.put("no_warta", rs.getString("NO_WARTA")==null?"":rs.getString("NO_WARTA"));
					h.put("kod_luas", rs.getString("KOD_LUAS")==null?"":rs.getString("KOD_LUAS"));
					h.put("luas", String.valueOf(rs.getString("LUAS")==null?"":rs.getDouble("LUAS")));
					h.put("tarikh_warta", rs.getDate("TARIKH_WARTA")==null?"":sdf.format(rs.getDate("TARIKH_WARTA")));
					h.put("tarikh_borangk", rs.getDate("TARIKH_BORANGK")==null?"":sdf.format(rs.getDate("TARIKH_BORANGK")));
	    			list.addElement(h);
					bil++;	
				}
			
		}finally {
			if(db != null) db.close();
		}
		return list;
	}//close setListBorangK
	
	
	
	public boolean showSahkanButton(String idpermohonan) throws Exception  {
		
		boolean output=false;
		String sql = "";
		Db db = null;
		try {
			db = new Db(); 
			sql = " select "+
				  " (Select count(*) as total from tblhtphakmilikurusan where id_permohonan='"+idpermohonan+"') - "+
				  " (Select count(*) as total from tblhtphakmilik where id_permohonan='"+idpermohonan+"') " +
				  " as total from dual ";
			//myLog.debug(sql);
			ResultSet rs = db.getStatement().executeQuery(sql); 
			if (rs.next()){	
				if (rs.getInt("total") > 0) {
					output = true;
				} else {
					output = false;
				}
				
			}
		} catch (Exception e) {
			throw new Exception ("error getting maklumat hakmilik :"+e.getMessage());
		}finally {
			if (db != null) db.close();
		}
		return output;		

	}
	
	
		
}
