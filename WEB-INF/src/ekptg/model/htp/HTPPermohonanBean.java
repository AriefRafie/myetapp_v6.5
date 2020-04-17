package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.KeputusanUlasan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;

public class HTPPermohonanBean implements IHTPPermohonan {
	HtpPermohonan htpPermohonan = null;
	Permohonan permohonan = null;	
	PfdFail fail = null;	
	private IHTPStatus iStatus = null;
    private String sql = "";
	private static SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
	static Logger myLog = Logger.getLogger(ekptg.model.htp.HTPPermohonanBean.class);

	@Override
	public HtpPermohonan kemaskiniPermohonanTarikh(HtpPermohonan htpPermohonan,String idPermohonan,String idhtpPermohonan)throws Exception {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String idFail = null;
		try{
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			r = new SQLRenderer();		
			permohonan = htpPermohonan.getPermohonan();
			r = new SQLRenderer();
			r.update("id_Permohonan", idPermohonan);
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);
			
			//htpPermohonan.getIdHtpPermohonan();
			r = new SQLRenderer();
			r.update("id_Htppermohonan",idhtpPermohonan);
			r.update("id_Permohonan",idPermohonan);
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
			stmt.executeUpdate(sql);
			
			r = new SQLRenderer();
			r.add("ID_FAIL");
			r.add("ID_PERMOHONAN",idPermohonan);				      
			sql = r.getSQLSelect("TBLPERMOHONAN");
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				idFail = rs.getString("ID_FAIL");
			}
			
			r = new SQLRenderer();
			r.update("ID_FAIL",idFail);
			r.add("ID_KEMASKINI", permohonan.getIdMasuk());
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("TBLPFDFAIL");
			stmt.executeUpdate(sql);
			conn.commit();
		
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
			if(db != null)	db.close();
		}
		return htpPermohonan;
		
	}
	
	@Override
	public KeputusanUlasan keputusanPermohonanTanah(KeputusanUlasan ku) throws Exception {
		Db db = null;
		String sql = "";
		try{
			//long idKeputusanHohon = DB.getNextID("TBLHTPKEPUTUSANMOHON_SEQ");		    	
			long idKeputusanHohon = ku.getIdUlasan();
			myLog.info("idKeputusanHohon="+idKeputusanHohon);
		    Long idUser = ku.getSubUrusanStatusFail().getIdMasuk();
		    Long idPermohonan = ku.getSubUrusanStatusFail().getIdPermohonan();
		    Long idFail = ku.getSubUrusanStatusFail().getIdFail();
			String NoRujukPTD = ku.getNo();
			String TarikhKeputusan = ku.getTarikhTerimaTxt();
			String PilihKeputusan = ku.getKeputusan();
			myLog.info("PilihKeputusan="+PilihKeputusan);
			String Catatan = ku.getUlasan();
			Long idSubUrusan = ku.getSubUrusanStatusFail().getIdSuburusanstatus();
			
			Date now = new Date();
			SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
			String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy')";
			String TK = "to_date('" + TarikhKeputusan + "','dd/MM/yyyy')";
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_kemaskini", idUser);
			r.add("tarikh_keputusan", r.unquote(TK));
			r.add("no_rujukan_keputusan", NoRujukPTD);
			r.add("status", PilihKeputusan);
			r.add("ulasan", Catatan);
			r.add("tarikh_kemaskini", r.unquote(TBF));
			
			//Penambahbaikan phase3. Syaz. 24112014
			r.add("flag_notifikasi", ku.getFlagNotifikasi());
				  
			if (!String.valueOf(idKeputusanHohon).equals("0")) {
				myLog.info("Update: idKeputusanHohon="+idKeputusanHohon);
				r.update("id_keputusanmohon",  idKeputusanHohon);
				r.update("id_permohonan", idPermohonan);
				sql = r.getSQLUpdate("TBLHTPKEPUTUSANMOHON");
				/**
				 * ID_STATUS = 86 (PERMOHONAN DILULUSKAN), LANGKAH = 13
				 * 			 = 87 (PERMOHONAN DITOLAK) LANGKAH = 14
				 * 			 = 88 (PERMOHONAN DITARIKBALIK) LANGKAH = 15
				 */
				String langkah = "";
				if(String.valueOf(PilihKeputusan).equals("01"))
					langkah = "13" ; 
				else if(String.valueOf(PilihKeputusan).equals("02"))
					langkah = "14" ; 
				else if(String.valueOf(PilihKeputusan).equals("03"))
					langkah = "15" ; 

				if(String.valueOf(PilihKeputusan).equals("01")
						||String.valueOf(PilihKeputusan).equals("02")
						||String.valueOf(PilihKeputusan).equals("03")){
					Long setIdStatus = 0L;			
					setIdStatus = FrmUtilData.getIdStatusByLangkah(langkah,String.valueOf(idSubUrusan),"=");
					Long setIdSuburusanstatus = 0L;
					setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah,String.valueOf(idSubUrusan),"=");
					Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
					s.setIdPermohonan(idPermohonan);
					s.setIdFail(idFail);
					s.setAktif("1");
					s.setIdMasuk(idUser);
					s.setIdKemaskini(idUser);
					s.setIdSuburusanstatus(setIdSuburusanstatus);
					s.setUrl("-");
					getStatus().simpanKemaskiniStatus(s,setIdStatus);
					
					if(langkah.equals("13")){
						Tblrujsuburusanstatusfail sBaru = new Tblrujsuburusanstatusfail();
						Long setIdSuburusanStatusBaru = FrmUtilData.getIdSuburusanStatusByLangkah("14",String.valueOf(idSubUrusan),"=");
						sBaru.setIdPermohonan(idPermohonan);
						sBaru.setIdFail(idFail);
						sBaru.setIdSuburusanstatus(setIdSuburusanStatusBaru);
						getStatus().hapusSubUrusanStatusPermohonan(sBaru);
						
						sBaru = new Tblrujsuburusanstatusfail();
						setIdSuburusanStatusBaru = FrmUtilData.getIdSuburusanStatusByLangkah("15",String.valueOf(idSubUrusan),"=");
						sBaru.setIdPermohonan(idPermohonan);
						sBaru.setIdFail(idFail);
						sBaru.setIdSuburusanstatus(setIdSuburusanStatusBaru);
						getStatus().hapusSubUrusanStatusPermohonan(sBaru);
					
					}else if(langkah.equals("14")){
						Tblrujsuburusanstatusfail sBaru = new Tblrujsuburusanstatusfail();
						Long setIdSuburusanStatusBaru = FrmUtilData.getIdSuburusanStatusByLangkah("13",String.valueOf(idSubUrusan),"=");
						sBaru.setIdPermohonan(idPermohonan);
						sBaru.setIdFail(idFail);
						sBaru.setIdSuburusanstatus(setIdSuburusanStatusBaru);
						getStatus().hapusSubUrusanStatusPermohonan(sBaru);
						
						sBaru = new Tblrujsuburusanstatusfail();
						setIdSuburusanStatusBaru = FrmUtilData.getIdSuburusanStatusByLangkah("15",String.valueOf(idSubUrusan),"=");
						sBaru.setIdPermohonan(idPermohonan);
						sBaru.setIdFail(idFail);
						sBaru.setIdSuburusanstatus(setIdSuburusanStatusBaru);
						getStatus().hapusSubUrusanStatusPermohonan(sBaru);
					
					}else if(langkah.equals("15")){
						Tblrujsuburusanstatusfail sBaru = new Tblrujsuburusanstatusfail();
						Long setIdSuburusanStatusBaru = FrmUtilData.getIdSuburusanStatusByLangkah("13",String.valueOf(idSubUrusan),"=");
						sBaru.setIdPermohonan(idPermohonan);
						sBaru.setIdFail(idFail);
						sBaru.setIdSuburusanstatus(setIdSuburusanStatusBaru);
						getStatus().hapusSubUrusanStatusPermohonan(sBaru);
						
						sBaru = new Tblrujsuburusanstatusfail();
						setIdSuburusanStatusBaru = FrmUtilData.getIdSuburusanStatusByLangkah("14",String.valueOf(idSubUrusan),"=");
						sBaru.setIdPermohonan(idPermohonan);
						sBaru.setIdFail(idFail);
						sBaru.setIdSuburusanstatus(setIdSuburusanStatusBaru);
						getStatus().hapusSubUrusanStatusPermohonan(sBaru);	
						
					}
				}else{
					langkah = "10";
					Long setIdStatus = 0L;			
					setIdStatus = FrmUtilData.getIdStatusByLangkah(langkah,String.valueOf(idSubUrusan),"=");
					Long setIdSuburusanstatus = 0L;
					setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah,String.valueOf(idSubUrusan),"=");
					Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
					s.setIdPermohonan(idPermohonan);
					s.setIdFail(idFail);
					s.setAktif("1");
					s.setIdMasuk(idUser);
					s.setIdKemaskini(idUser);
					s.setIdSuburusanstatus(setIdSuburusanstatus);
					s.setUrl("-");
					getStatus().simpanKemaskiniStatus(s,setIdStatus);
					
					//hapus status terdahulu
					Tblrujsuburusanstatusfail sBaru = new Tblrujsuburusanstatusfail();
					Long setIdSuburusanStatusBaru = FrmUtilData.getIdSuburusanStatusByLangkah("13",String.valueOf(idSubUrusan),"=");
					sBaru.setIdPermohonan(idPermohonan);
					sBaru.setIdFail(idFail);
					sBaru.setIdSuburusanstatus(setIdSuburusanStatusBaru);
					getStatus().hapusSubUrusanStatusPermohonan(sBaru);
					
					sBaru = new Tblrujsuburusanstatusfail();
					setIdSuburusanStatusBaru = FrmUtilData.getIdSuburusanStatusByLangkah("14",String.valueOf(idSubUrusan),"=");
					sBaru.setIdPermohonan(idPermohonan);
					sBaru.setIdFail(idFail);
					sBaru.setIdSuburusanstatus(setIdSuburusanStatusBaru);
					getStatus().hapusSubUrusanStatusPermohonan(sBaru);
					
					sBaru = new Tblrujsuburusanstatusfail();
					setIdSuburusanStatusBaru = FrmUtilData.getIdSuburusanStatusByLangkah("15",String.valueOf(idSubUrusan),"=");
					sBaru.setIdPermohonan(idPermohonan);
					sBaru.setIdFail(idFail);
					sBaru.setIdSuburusanstatus(setIdSuburusanStatusBaru);
					getStatus().hapusSubUrusanStatusPermohonan(sBaru);
					
				} 
			
			} else {
				myLog.info("Insert:idKeputusanHohon="+idKeputusanHohon);
				idKeputusanHohon = DB.getNextID("TBLHTPKEPUTUSANMOHON_SEQ");
				r.add("id_keputusanmohon", idKeputusanHohon);
				r.add("id_masuk", idUser);
				r.add("tarikh_masuk", r.unquote(TBF));
				r.add("id_permohonan", idPermohonan);
				sql = r.getSQLInsert("TBLHTPKEPUTUSANMOHON");
				
				String langkah = "";
				if(String.valueOf(PilihKeputusan).equals("01"))
					langkah = "13" ; 
				else if(String.valueOf(PilihKeputusan).equals("02"))
					langkah = "14" ; 
				else if(String.valueOf(PilihKeputusan).equals("03"))
					langkah = "15" ; 

				if(String.valueOf(PilihKeputusan).equals("01")
						||String.valueOf(PilihKeputusan).equals("02")
						||String.valueOf(PilihKeputusan).equals("03")){
					Long setIdStatus = 0L;			
					setIdStatus = FrmUtilData.getIdStatusByLangkah(langkah,String.valueOf(idSubUrusan),"=");
					Long setIdSuburusanstatus = 0L;
					setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah,String.valueOf(idSubUrusan),"=");
					Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
					s.setIdPermohonan(idPermohonan);
					s.setIdFail(idFail);
					s.setAktif("1");
					s.setIdMasuk(idUser);
					s.setIdKemaskini(idUser);
					s.setIdSuburusanstatus(setIdSuburusanstatus);
					s.setUrl("-");
					getStatus().simpanKemaskiniStatus(s,setIdStatus);
					
//					Tblrujsuburusanstatusfail sBaru = new Tblrujsuburusanstatusfail();
//					Long setIdSuburusanStatusBaru = FrmUtilData.getIdSuburusanStatusByLangkah("6",idUser,"=");
//					sBaru.setIdPermohonan(Long.parseLong(idPermohonan));
//					sBaru.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
//					sBaru.setIdSuburusanstatus(setIdSuburusanStatusBaru);
//					getStatus().hapusSubUrusanStatusPermohonan(sBaru);

					
				}
			
			}
			myLog.debug("keputusanPermohonanTanah:sql="+sql);
			stmt.executeUpdate(sql);
		    
		} finally {
			if (db != null) db.close();	      
		}	
		return ku;
		
	}
	
	public Vector<Hashtable<String, String>> getPermohonanAktifLangkah(String idUrusan,String langkah)throws Exception {
		Db db = null;
	    String sql = "";
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	sql= "SELECT p.id_Permohonan, f.no_Fail, UPPER(p.tujuan) TUJUAN, s.keterangan,RN.NAMA_NEGERI "+
	      	" FROM Tblpermohonan P, Tblpfdfail F, Tblrujsuburusanstatusfail SF,Tblrujsuburusanstatus US,Tblrujstatus S,TBLRUJNEGERI RN "+
	      	" WHERE p.id_Fail = f.id_Fail  AND p.id_Permohonan = sf.id_Permohonan AND p.id_Fail=sf.id_Fail "+
	      	" AND sf.id_Suburusanstatus = us.id_Suburusanstatus AND us.id_Status = s.id_Status  "+
	      	" AND F.ID_NEGERI=RN.ID_NEGERI AND sf.aktif IN (1) " +
	      	" AND f.id_Urusan IN ("+ idUrusan +")"+
	      	" AND US.LANGKAH ="+langkah+" " +
	      	" ";
	    	//myLog.info("FrmPajakanKecilSenaraiPermohonanData:sql::"+sql);   
	    	ResultSet rs = stmt.executeQuery(sql);
	    	Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
	    	Hashtable<String, String> h;
	    	while (rs.next()) {
	    		h = new Hashtable<String, String>();
	    		h.put("id", Utils.isNull(rs.getString("id_Permohonan")));
	    		h.put("no", Utils.isNull(rs.getString("no_Fail")));
	    		h.put("tajuk", Utils.isNull(rs.getString("tujuan")));
	    		h.put("keterangan", Utils.isNull(rs.getString("keterangan")));
	    		h.put("negeri", Utils.isNull(rs.getString("NAMA_NEGERI")));
	    		list.addElement(h);
	
	    	}
	    	return list;
	    	
	    } finally {
	      if (db != null) db.close();
	    }
	
	}
	
	@Override
	public Hashtable<String, String> getPermohonanInfo(String idpermohonan)throws Exception {
	    Db db = null;
	    String sql = "";
	    Hashtable<String, String> h = null;
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	sql= " SELECT N.NAMA_NEGERI,K.NAMA_KEMENTERIAN,A.NAMA_AGENSI,S.NAMA_SUBURUSAN," +
	        " F.NO_FAIL,TO_CHAR(F.TARIKH_DAFTAR_FAIL,'DD/MM/YYYY') TARIKH_DAFTAR_FAIL" +
	        " ,H.NO_RUJUKAN_PTD,H.NO_RUJUKAN_PTG,REPLACE(H.NO_FAIL_KJP,'$txtFailKJP','') NO_FAIL_KJP" +
	        " ,H.NO_RUJUKAN_LAIN," +
	        " TO_CHAR(P.TARIKH_SURAT,'DD/MM/YYYY') TARIKH_SURAT,TO_CHAR(P.TARIKH_TERIMA,'DD/MM/YYYY') TARIKH_TERIMA" +
	        " ,P.TUJUAN,F.TAJUK_FAIL,P.ID_PERMOHONAN,F.ID_FAIL " +
	        " ,K.ID_KEMENTERIAN,NVL(A.ID_AGENSI,0) ID_AGENSI,N.ID_NEGERI,F.ID_SUBURUSAN " +
	        " ,( SELECT U.KOD_URUSAN FROM TBLRUJURUSAN U WHERE U.ID_URUSAN = F.ID_URUSAN) KOD_URUSAN " +
	        " ,( SELECT J.KOD_JENIS_TANAH FROM TBLRUJJENISTANAH J WHERE H.ID_JENISTANAH = J.ID_JENISTANAH) KOD_JENIS_TANAH " +
	        " FROM " +
	        " TBLRUJNEGERI N,TBLPFDFAIL F,TBLRUJKEMENTERIAN K,TBLPERMOHONAN P,"+
	        " TBLHTPPERMOHONAN H,TBLRUJAGENSI A,TBLRUJSUBURUSAN S WHERE "+
	        " F.ID_NEGERI=N.ID_NEGERI AND F.ID_KEMENTERIAN=K.ID_KEMENTERIAN "+
	        " AND P.ID_FAIL=F.ID_FAIL AND P.ID_PERMOHONAN=H.ID_PERMOHONAN  "+
	        " AND H.ID_AGENSI=A.ID_AGENSI AND F.ID_SUBURUSAN=S.ID_SUBURUSAN  "+
	        " AND P.ID_PERMOHONAN = "+idpermohonan;	
	    	//myLog.info("getPermohonanInfo:sql="+sql);
	        ResultSet rs = stmt.executeQuery(sql);
	        
	        h = new Hashtable<String, String>();
	        while (rs.next()) {
	        	if(rs.getString("nama_negeri")==null){
	        		h.put("negeri", "");
	        	}else{
	        		h.put("negeri", rs.getString("nama_negeri")); 
	        	}
	        	if(rs.getString("nama_kementerian")==null){
	        		h.put("kementerian", "");
	        	}else{
	        		h.put("kementerian", rs.getString("nama_kementerian"));}
	        	if(rs.getString("nama_agensi")==null){
	        		h.put("agensi", "");
	        	}else {
		    		h.put("agensi", rs.getString("nama_agensi"));
	        	}
	        	h.put("idsuburusan", rs.getString("id_suburusan"));
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
	        		h.put("idFail", "");
	        	}else{
	        		h.put("idfail", rs.getString("id_fail"));
	        		h.put("idFail", rs.getString("id_fail"));
	        	}	 
	        	myLog.info("id_agensi="+rs.getString("id_agensi"));
	        	h.put("idagensi", rs.getString("id_agensi"));
	        	h.put("idkementerian", rs.getString("id_kementerian"));
	        	h.put("idnegeri", rs.getString("id_negeri"));
	        	h.put("kodUrusan", rs.getString("KOD_URUSAN"));
	        	h.put("kodJenisTanah", rs.getString("KOD_JENIS_TANAH"));
	        	h.put("noFailPTD", Utils.isNull(rs.getString("NO_RUJUKAN_PTD")));
	    		h.put("noFailPTG", Utils.isNull(rs.getString("NO_RUJUKAN_PTG")));
	        	if(rs.getString("TAJUK_FAIL")==null){
		    		h.put("tajukFail", Utils.isNull(rs.getString("TUJUAN")));
	        	}else{
		    		h.put("tajukFail", Utils.isNull(rs.getString("TAJUK_FAIL")));
	        	}
    		  
	      }
	      return h;

	    } finally {
	      if (db != null) db.close();
	    }
	  
	}
	
	@Override
	public HtpPermohonan getPermohonanInfo(String idFail,String idPermohonan) throws Exception {
		Db db = null;
		Connection conn = null;
		sql = "";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			sql = "SELECT A.ID_FAIL, A.NO_FAIL, A.TAJUK_FAIL, A.ID_URUSAN" +
			" ,TO_CHAR(A.TARIKH_DAFTAR_FAIL,'DD/MM/YYYY') AS OPEN_FILE_DATE" +
			" ,B.TARIKH_TERIMA, B.ID_PERMOHONAN,TO_CHAR(B.TARIKH_SURAT,'DD/MM/YYYY') TARIKH_SURAT" +
			" , B.ID_STATUS,B.NO_PERMOHONAN,B.TUJUAN "+
			" ,NVL(PP.NO_RUJUKAN_KJP,'') NO_RUJUKAN_KJP, NVL(PP.NO_RUJUKAN_LAIN,'') NO_RUJUKAN_LAIN" +
	        " ,NVL(PP.NO_RUJUKAN_PTD,'') NO_RUJUKAN_PTD ,NVL(PP.NO_RUJUKAN_PTG,'') NO_RUJUKAN_PTG" +
	        " ,PP.TARIKH_AGIHAN,PP.ID_HTPPERMOHONAN "+
			" ,D.ID_NEGERI, D.NAMA_NEGERI, E.ID_KEMENTERIAN, E.NAMA_KEMENTERIAN" +
			" ,NVL((SELECT RAI.nama_agensi FROM TBLRUJAGENSI RAI "+
			" 		WHERE RAI.ID_AGENSI = PP.ID_AGENSI "+
			" 	),'TIADA MAKLUMAT AGENSI') NAMA_AGENSI "+
			" ,NVL((SELECT RAI.ID_AGENSI FROM TBLRUJAGENSI RAI "+
			" 		WHERE RAI.ID_AGENSI = PP.ID_AGENSI "+
			" 	),'0') ID_AGENSI "+
			" ,NVL((SELECT RUI.NAMA_URUSAN FROM TBLRUJURUSAN RUI "+ 
			"  		WHERE RUI.ID_URUSAN = A.ID_URUSAN "+
			"  ),'TIADA MAKLUMAT URUSAN') NAMA_URUSAN "+ 
			" ,NVL((SELECT RSUI.ID_SUBURUSAN FROM TBLRUJSUBURUSAN RSUI "+ 
			" 		WHERE RSUI.ID_SUBURUSAN = A.ID_SUBURUSAN "+
			" 	),'0') ID_SUBURUSAN "+
			" ,NVL((SELECT RSUI.NAMA_SUBURUSAN FROM TBLRUJSUBURUSAN RSUI "+ 
			" 		WHERE RSUI.ID_SUBURUSAN = A.ID_SUBURUSAN "+
			" 	),'TIADA MAKLUMAT URUSAN') NAMA_SUBURUSAN "+
			" ,NVL((SELECT RSUI.KOD_SUBURUSAN FROM TBLRUJSUBURUSAN RSUI "+ 
			" 		WHERE RSUI.ID_SUBURUSAN = A.ID_SUBURUSAN "+
			" 	),'TIADA MAKLUMAT URUSAN') KOD_SUBURUSAN "+
			" ,NVL((SELECT RJJI.ID_JENISTANAH FROM TBLRUJJENISTANAH RJJI "+ 
			" 		WHERE RJJI.id_jenistanah = PP.id_jenistanah "+
			" 	),'0') ID_JENISTANAH "+
			" ,NVL((SELECT RJJI.KETERANGAN FROM TBLRUJJENISTANAH RJJI "+ 
			" 		WHERE RJJI.id_jenistanah = PP.id_jenistanah "+
			" 	),'TIADA MAKLUMAT JENIS TANAH') KETERANGAN "+
			" ,NVL((SELECT RTKI.ID_TARAFKESELAMATAN FROM TBLPFDRUJTARAFKESELAMATAN RTKI "+ 
			" 		WHERE A.ID_TARAFKESELAMATAN = RTKI.ID_TARAFKESELAMATAN "+
			" 	),'0') ID_TARAFKESELAMATAN "+
			" ,NVL((SELECT RTKI.TARAF_KESELAMATAN FROM TBLPFDRUJTARAFKESELAMATAN RTKI "+ 
			" 		WHERE A.ID_TARAFKESELAMATAN = RTKI.ID_TARAFKESELAMATAN "+
			" 	),'TIADA MAKLUMAT STATUS KESELAMATAN FAIL') TARAF_KESELAMATAN " +
			" ,NVL(PP.ID_DAERAH,'0') ID_DAERAH "+
		    " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLHTPPERMOHONAN PP "+
			" , TBLRUJNEGERI D, TBLRUJKEMENTERIAN E "+
			" WHERE B.ID_PERMOHONAN = PP.ID_PERMOHONAN "+
			" AND B.ID_FAIL = A.ID_FAIL "+
			" AND A.ID_NEGERI = D.ID_NEGERI "+
			" AND A.ID_KEMENTERIAN = E.ID_KEMENTERIAN "+
			"";
			if(!idPermohonan.equals("")){
				sql += " AND B.ID_PERMOHONAN = '"+ idPermohonan +"'";
			}
			if(!idFail.equals("")){
				sql += " AND A.ID_FAIL = '" + idFail + "'";
			}
			sql +=" ORDER BY B.id_Permohonan desc";
//			myLog.info("Permohonan:::findPermohonan::sql::"+sql);
											
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);		
			if(rs.next()){
				fail = new PfdFail();
				permohonan = new Permohonan();
				htpPermohonan = new HtpPermohonan();									 		

				htpPermohonan.setIdHtpPermohonan(rs.getString("ID_HTPPERMOHONAN"));
				htpPermohonan.setIdAgensi(rs.getLong("ID_AGENSI"));
				htpPermohonan.setIdDaerah(rs.getString("ID_DAERAH"));
				htpPermohonan.setIdJenisTanah(rs.getString("ID_JENISTANAH"));
				htpPermohonan.setNoRujukanKJP(rs.getString("NO_RUJUKAN_KJP"));
				htpPermohonan.setNoRujukanLain(rs.getString("NO_RUJUKAN_LAIN"));
				htpPermohonan.setNoRujukanPTG(rs.getString("NO_RUJUKAN_PTG"));
				htpPermohonan.setNoRujukanPTD(rs.getString("NO_RUJUKAN_PTD"));
				htpPermohonan.setNamaAgensi(rs.getString("NAMA_AGENSI"));
				permohonan.setNoPermohonan(rs.getString("NO_PERMOHONAN"));
				permohonan.setIdPermohonan(rs.getString("ID_PERMOHONAN"));
				permohonan.setTarikhSurat(rs.getString("TARIKH_SURAT"));
				permohonan.setTarikhTerima(rs.getString("TARIKH_TERIMA"));
				permohonan.setTujuan(rs.getString("TUJUAN"));
				permohonan.setNamaNegeri(rs.getString("NAMA_NEGERI"));
				fail.setNoFail(rs.getString("NO_FAIL"));
				fail.setIdKementerian(rs.getString("ID_KEMENTERIAN"));
				fail.setIdNegeri(rs.getString("ID_NEGERI"));
				fail.setIdUrusan(rs.getLong("ID_URUSAN"));
				fail.setIdSubUrusan(rs.getString("ID_SUBURUSAN"));
				fail.setIdTarafKeselamatan(rs.getString("ID_TARAFKESELAMATAN")==null?"1":rs.getString("ID_TARAFKESELAMATAN"));
				fail.setTarikhDaftarFail(rs.getString("OPEN_FILE_DATE"));
				fail.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));
				fail.setNamaSuburusan(rs.getString("NAMA_SUBURUSAN"));
				fail.setKodSuburusan(rs.getString("KOD_SUBURUSAN"));
				fail.setTajukFail(rs.getString("TAJUK_FAIL"));
				fail.setIdFail(rs.getLong("ID_FAIL"));
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);
				conn.commit();
			
			}
										    	
		 } finally {
		      if (db != null) db.close();
		    }
		
		return htpPermohonan;
		
	}	

	public void setMaklumatPermohonan(org.apache.velocity.VelocityContext context_
		,Hashtable<String, String> TerimaPohonInfo, String disability ) 
		throws Exception {
		try {
			String idurusan = (String)TerimaPohonInfo.get("lblidUrusan");
			String idnegeri = (String)TerimaPohonInfo.get("lblNegeri");
			context_.put("noFail", TerimaPohonInfo.get("lblNoFail"));
			context_.put("diDaftarOleh", TerimaPohonInfo.get("diDaftarOleh"));
			context_.put("diDaftarPada", TerimaPohonInfo.get("diDaftarPada"));
		
			String socNegeri = HTML.SelectNegeri("lblNegeri",Utils.parseLong(idnegeri),"disabled class=disabled");
			String socDaerah = HTML.SelectDaerahByNegeri(idnegeri,"lblDaerah",Utils.parseLong((String)TerimaPohonInfo.get("lblIdDaerah")),"disabled class=disabled");
			String socKementerian =HTML.SelectKementerian("lblKementerian", Utils.parseLong((String)TerimaPohonInfo.get("lblidKementerian")), disability +"style=\"width:400\"");
			String socAgensi = HTML.SelectAgensi("lblidAgensi",Utils.parseLong((String)TerimaPohonInfo.get("lblidAgensi")), disability +"style=\"width:400\"");
			String socUrusan = UtilHTML.SelectUrusan("lblidUrusan",Utils.parseLong((String)TerimaPohonInfo.get("lblidUrusan")),"disabled class=disabled");		
			String socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,"lblidSubUrusan",Utils.parseLong((String)TerimaPohonInfo.get("lblidSubUrusan")),"disabled class=disabled");
			String socStatustanah = HTML.SelectJenisTanah("lblidKodJTanah", Utils.parseLong((String)TerimaPohonInfo.get("lblidKodJTanah")), "disabled class=disabled");
			String socjenisFail = HTML.SelectTarafKeselamatan("lblTanahKeselamatan",Utils.parseLong((String)TerimaPohonInfo.get("lblTanahKeselamatan")),"disabled class=disabled");
		
			context_.put("lblNamaNegeri", TerimaPohonInfo.get("lblNamaNegeri"));
			context_.put("lblNamaDaerah", TerimaPohonInfo.get("lblNamaDaerah"));
			context_.put("lblKementerian", TerimaPohonInfo.get("lblKementerian"));
			context_.put("lblAgensi", TerimaPohonInfo.get("lblAgensi"));
			context_.put("lblUrusan", TerimaPohonInfo.get("lblUrusan"));
			context_.put("lblNamaSubUrusan", TerimaPohonInfo.get("lblNamaSubUrusan"));
			context_.put("lblKeterangan", TerimaPohonInfo.get("lblKeterangan"));
			context_.put("lblTarafKeselamatan", TerimaPohonInfo.get("lblTarafKeselamatan"));
			/**
			 * KJP Online
			 */
			if(disability.equals("")){
				socNegeri = TerimaPohonInfo.get("lblNamaNegeri");
				socDaerah = (String)TerimaPohonInfo.get("lblNamaDaerah");	
				socKementerian = (String)TerimaPohonInfo.get("lblKementerian");	
				socAgensi = (String)TerimaPohonInfo.get("lblAgensi");	
				socUrusan = (String)TerimaPohonInfo.get("lblUrusan");	
				socSubUrusan = (String)TerimaPohonInfo.get("lblNamaSubUrusan");	
				socStatustanah = (String)TerimaPohonInfo.get("lblKodJTanah")+" - "+(String)TerimaPohonInfo.get("lblKeterangan");	
				socjenisFail = (String)TerimaPohonInfo.get("lblTarafKeselamatan");	
	
				context_.put("noP", TerimaPohonInfo.get("noP"));
				context_.put("idPermohonan",TerimaPohonInfo.get("lblIdPermohonan"));
			
			}
			context_.put("socNegeri", socNegeri);
			context_.put("socDaerah", socDaerah);
			context_.put("socKementerian", socKementerian);
			context_.put("socAgensi", socAgensi);
			context_.put("socUrusan", socUrusan);
			context_.put("socSubUrusan", socSubUrusan);
			context_.put("socStatustanah", socStatustanah);
			context_.put("socjenisFail", socjenisFail);
		
			context_.put("txtTajuk",TerimaPohonInfo.get("lblTujuan"));
			context_.put("txdTarikhSuratKJP",TerimaPohonInfo.get("lblTrkhSurat"));
			context_.put("txtnoFailKJP",TerimaPohonInfo.get("lblNoRujKJP"));
			context_.put("txtnoFailUPT", TerimaPohonInfo.get("lblNoRujUPT"));
			context_.put("idpermohonan",TerimaPohonInfo.get("lblIdPermohonan"));
			context_.put("txtnoFailNegeri",TerimaPohonInfo.get("lblNoRujLain"));
			context_.put("txtnoFailPTG",TerimaPohonInfo.get("lblNoRujPTG"));
			context_.put("txtnoFailPTD",TerimaPohonInfo.get("lblNoRujPTD"));
			

		} catch (Exception e) {
			throw new Exception("Ralat:"+e.getCause());	
		}
		
	}
	
	public void setLabelDaerah(org.apache.velocity.VelocityContext context_, String idNegeri ){ 
		String labelDaerah = "Daerah";
		if((!idNegeri.equals("") && !idNegeri.equals("0")) && idNegeri.equals("3"))
			labelDaerah = "Jajahan";
		else if((!idNegeri.equals("") && !idNegeri.equals("0")) && idNegeri.equals("13"))
			labelDaerah = "Bahagian";

		//myLog.info("setLabelDaerah:labelDaerah="+labelDaerah);
		context_.put("labelDaerah",labelDaerah);
		context_.put("labelDaerahUP",labelDaerah.toUpperCase());
		
	}
	
	public void getValues(org.apache.velocity.VelocityContext context_
		,String tajuk, String failKJP, String tarikhSuratKJP, String idPermohonan, String noFailUPT
		,String noFailLain, String noFailPTG, String noFailPTD, String idDaerah, String idAgensi, String idKeselamatan,String userId){
		fail = new PfdFail();
		permohonan = new Permohonan();
		htpPermohonan = new HtpPermohonan();

		fail.setIdTarafKeselamatan(idKeselamatan);
		
		permohonan.setTujuan(tajuk);		
		permohonan.setTarikhSurat(tarikhSuratKJP);
		htpPermohonan.setNoRujukanKJP(failKJP);
		htpPermohonan.setNoRujukanLain(noFailLain);
		htpPermohonan.setNoRujukanUPT(noFailUPT);
		htpPermohonan.setNoRujukanPTG(noFailPTG);
		htpPermohonan.setNoRujukanPTD(noFailPTD);
		permohonan.setTarikhTerima(tarikhSuratKJP);
		permohonan.setIdPermohonan(idPermohonan);
		permohonan.setIdMasuk(Long.parseLong(userId));
		permohonan.setPfdFail(fail);
		htpPermohonan.setPermohonan(permohonan);
		htpPermohonan.setIdAgensi(idAgensi);
		htpPermohonan.setIdDaerah(idDaerah);

		context_.put("permohonan", htpPermohonan);
		
	}
	@Override
	public String getKeputusanPermohonan(int i,String idNegeri){
		//myLog.info("getKeputusanPermohonan:i="+i);
		String m="";
		String[] names = {"BELUM ADA KEPUTUSAN", "DILULUSKAN", "TIDAK DILULUSKAN","DIBATALKAN/DITARIKBALIKR","TIADA MAKLUMAT"};
		String[] nameSar =  {"PERMOHONAN DITERIMA"
								,"PERMOHONAN DIHANTAR JTS/BDA"
								,"LAWATAN TAPAK BERSAMA"
								,"LULUS MPN/SPA"
								,"SELESAI PEMBAYARAN(PREMIUM/PAMPASAN/KOS UKUR/LAIN-LAIN)"
								,"KELULUSAN PEMBERIMILIKAN (BORANG L&S80)"
								,"TIDAK LULUS MPN/SPA"
								,"DITOLAK"
								,"DIBATAL"
								,"DITANGGUH"
								,"SELESAI (HAK MILIK DAFTAR PTP)"};
		String[] nameSab = {"SUDAH DISOKONG DALAM LUC","PTU PERAKUKAN KE SUHB","SURAT TAWARAN SUDAH DIKELUARKAN"};
		
		if(idNegeri.equals("13"))
	   		m = nameSar[i];
		else if(idNegeri.equals("12"))
	   		m = nameSab[i];
		else
			m = names[i];
			
		return m;
		
	}
	private IHTPStatus getStatus(){
		if(iStatus==null){
			iStatus = new HTPStatusBean();
		}
		return iStatus;
			
	}
	
}
