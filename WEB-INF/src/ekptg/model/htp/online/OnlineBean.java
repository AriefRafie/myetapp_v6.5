package ekptg.model.htp.online;

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
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.FrmPembelianSemakanData;
import ekptg.model.htp.FrmSenaraiFailTerimaPohonData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.Bangunan;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Pemohon;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;

public class OnlineBean implements IOnline {
	
	//private HakmilikInterface hakmilikInterface = null;
 	private IHtp iHTP = null;  
	private static final String KOD_JABATAN ="JKPTG";
	private static String KODKEMENTERIAN ="00";
	private static final long IDTARAFKESELAMTAN = 1;
	private static final int IDSEKSYEN = 3;
	private static int IDURUSAN = 0;
	PfdFail fail = null;
	Permohonan permohonan = null;
	HtpPermohonan htpPermohonan = null;
	private FrmSenaraiFailTerimaPohonData senaraiFail = null;
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.online.OnlineBean.class);
	Connection conn = null;

	public HtpPermohonan simpanPermohonan(HtpPermohonan htpPermohonan)throws Exception{
		Db db = null;
		//Connection conn = null;
		Permohonan permohonan = htpPermohonan.getPermohonan();
		SQLRenderer r = null;
		if(permohonan == null)
			throw new Exception("VALUE Permohonan TIDAK DIPEROLEHI SILA PERIKSA METHOD simpanPermohonan");
		PfdFail pfdFail = permohonan.getPfdFail();
		String kodUrusan = null;
		String kodMampu = null;
		if(pfdFail == null)
			throw new Exception("VALUE TBLPFDFAIL TIDAK DIPEROLEHI SILA PERIKSA METHOD simpanPermohonan");
		
		 //Date now = new Date();
		 //SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		 //String tarikhDaftarFail = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		try{
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			r = new SQLRenderer();
			r.add("id_Urusan");
			r.add("kod_Urusan");				      
			r.add("id_Urusan",IDURUSAN);				      
			sql = r.getSQLSelect("Tblrujurusan");
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				kodUrusan = rs.getString("kod_Urusan");
			}
			r = new SQLRenderer();
			r.add("id_Negeri");
			r.add("kod_Mampu");				      
			r.add("id_Negeri",pfdFail.getIdNegeri());				      
			sql = r.getSQLSelect("Tblrujnegeri");
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				  kodMampu = rs.getString("kod_Mampu");
			}
			senaraiFail = new FrmSenaraiFailTerimaPohonData();
			KODKEMENTERIAN = senaraiFail.getKementerianByMampu(Integer.parseInt(String.valueOf(pfdFail.getIdKementerian())));
			

			String noFail = KOD_JABATAN+"/101/"+kodUrusan+"/"+KODKEMENTERIAN+"/"+kodMampu+"-"+ File.getSeqNo(IDSEKSYEN, IDURUSAN,Integer.parseInt(String.valueOf(pfdFail.getIdKementerian())),Integer.parseInt(String.valueOf(pfdFail.getIdNegeri()))) ;
			if (!("".equals(pfdFail.getNoFail()))) {
				  noFail = pfdFail.getNoFail();
			}/*else{
				  fileSeq = File.getSeqNo(Integer.parseInt(getParam("socSeksyen")), Integer.parseInt(idUrusan), idKementerian, idNegeri);
				  noFail += kodKementerianMampu+"/"+kodNegeriMampu+"-"+fileSeq;			  
			}*/
			pfdFail.setNoFail(noFail);
			long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			pfdFail.setIdFail(idFail);
			r = new SQLRenderer();
			r.add("id_Fail", idFail);
			  //rFail.add("kod_Jabatan", kodJabatan);
			r.add("id_Tarafkeselamatan", IDTARAFKESELAMTAN);
			r.add("id_Seksyen",IDSEKSYEN);
			r.add("id_Urusan",IDURUSAN);
			r.add("id_Suburusan", pfdFail.getIdSubUrusan());
			r.add("tarikh_Daftar_Fail", r.unquote("sysdate"));
			r.add("flag_Fail", 1);
			r.add("tajuk_Fail", permohonan.getTujuan());
			r.add("no_Fail", noFail);
			r.add("no_Fail_Root", "TIADA");
		
			r.add("id_lokasifail", 1);
			r.add("id_Negeri", pfdFail.getIdNegeri());
			r.add("id_Kementerian", pfdFail.getIdKementerian());
			r.add("id_Status", 7);
			r.add("id_faharasat", 1);
			r.add("id_Masuk", permohonan.getIdMasuk());
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPFDFAIL");
			
			stmt.executeUpdate(sql);
			
			String TarikhSurKJP = permohonan.getTarikhSurat();
			String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";
			
			String TarikhPermohonan = permohonan.getTarikhTerima();
			String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
			
			long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
			permohonan.setIdPermohonan(idPermohonan);
			r = new SQLRenderer();
			r.add("id_Permohonan", idPermohonan);
			r.add("id_Fail",idFail);
			r.add("id_Jkptg",1);
			r.add("no_Permohonan","TIADA");
			r.add("no_Perserahan","TIADA");
			r.add("tarikh_Surat", r.unquote(TSKJP));
			r.add("tarikh_Terima", r.unquote(TP));
			r.add("tujuan",permohonan.getTujuan());
			r.add("id_Masuk", permohonan.getIdMasuk());
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPERMOHONAN");
			
			stmt.executeUpdate(sql);
			
			long idHtpPermohonan = DB.getNextID("TBLHTPPERMOHONAN_SEQ");
			htpPermohonan.setIdHtpPermohonan(idHtpPermohonan);
			r = new SQLRenderer();
			r.add("id_Htppermohonan",idHtpPermohonan);
			r.add("id_Permohonan",permohonan.getIdPermohonan());
			r.add("id_Agensi", htpPermohonan.getIdAgensi());
			r.add("id_Jenistanah", htpPermohonan.getIdJenisTanah());
			r.add("id_Pegawai", htpPermohonan.getIdPegawai());
			r.add("no_Rujukan_KJP",htpPermohonan.getNoRujukanKJP());
			r.add("no_Rujukan_Lain", htpPermohonan.getNoRujukanLain());
			r.add("tarikh_Agihan", r.unquote("sysdate"));
			r.add("id_Masuk", permohonan.getIdMasuk());
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLHTPPERMOHONAN");
			
			stmt.executeUpdate(sql);
			
			conn.commit();
			
			FrmPembelianSemakanData.StatusChange_Action(idPermohonan, Integer.parseInt(String.valueOf(pfdFail.getIdSubUrusan())), idFail);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return htpPermohonan;
	}

	@Override
	public HtpPermohonan simpanPermohonanOnline(HtpPermohonan htpPermohonan)throws Exception{
		Db db = null;
		//Connection conn = null;
		Permohonan permohonan = htpPermohonan.getPermohonan();
		SQLRenderer r = null;
		String kodNegeriMampu = "";
		String kodKementerianMampu = "";
		
		if(permohonan == null)
			throw new Exception("VALUE Permohonan TIDAK DIPEROLEHI SILA PERIKSA METHOD simpanPermohonan");
		PfdFail pfdFail = permohonan.getPfdFail();
		String kodUrusan = null;
		String kodMampu = null;
		if(pfdFail == null)
			throw new Exception("VALUE PfdFail TIDAK DIPEROLEHI SILA PERIKSA METHOD simpanPermohonan");
		
		 //Date now = new Date();
		 //SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		 //String tarikhDaftarFail = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		try{
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			r = new SQLRenderer();
			r.add("id_Urusan");
			r.add("kod_Urusan");				      
			r.add("id_Urusan",IDURUSAN);				      
			sql = r.getSQLSelect("Tblrujurusan");
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				kodUrusan = rs.getString("kod_Urusan");
			}
			r = new SQLRenderer();
			r.add("id_Negeri");
			r.add("kod_Mampu");				      
			r.add("id_Negeri",pfdFail.getIdNegeri());				      
			sql = r.getSQLSelect("Tblrujnegeri");
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				  kodMampu = rs.getString("kod_Mampu");
			}
			senaraiFail = new FrmSenaraiFailTerimaPohonData();
			KODKEMENTERIAN = senaraiFail.getKementerianByMampu(Integer.parseInt(String.valueOf(pfdFail.getIdKementerian())));
			

			String noFail = KOD_JABATAN+"/101/"+kodUrusan+"/"+KODKEMENTERIAN+"/"+kodMampu+"-"+ File.getSeqNo(IDSEKSYEN, IDURUSAN,Integer.parseInt(String.valueOf(pfdFail.getIdKementerian())),Integer.parseInt(String.valueOf(pfdFail.getIdNegeri()))) ;
			if (!("".equals(pfdFail.getNoFail()))) {
				  noFail = pfdFail.getNoFail();
			}/*else{
				  fileSeq = File.getSeqNo(Integer.parseInt(getParam("socSeksyen")), Integer.parseInt(idUrusan), idKementerian, idNegeri);
				  noFail += kodKementerianMampu+"/"+kodNegeriMampu+"-"+fileSeq;			  
			}*/
			//pfdFail.setNoFail(noFail);
			long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			pfdFail.setIdFail(idFail);
			r = new SQLRenderer();
			r.add("id_Fail", idFail);
			  //rFail.add("kod_Jabatan", kodJabatan);
			r.add("id_Tarafkeselamatan", IDTARAFKESELAMTAN);
			r.add("id_Seksyen",IDSEKSYEN);
			r.add("id_Urusan",IDURUSAN);
			r.add("id_Suburusan", pfdFail.getIdSubUrusan());
			r.add("tarikh_Daftar_Fail", r.unquote("sysdate"));
			r.add("flag_Fail", 1);
			r.add("tajuk_Fail", permohonan.getTujuan());
			r.add("no_Fail", " ");
			r.add("no_Fail_Root", " ");
		
			r.add("id_lokasifail", 1);
			r.add("id_Negeri", pfdFail.getIdNegeri());
			r.add("id_Kementerian", pfdFail.getIdKementerian());
			r.add("id_Status", 7);
			r.add("id_faharasat", 1);
			r.add("id_Masuk", permohonan.getIdMasuk());
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPFDFAIL");
			
			stmt.executeUpdate(sql);
			
			String TarikhSurKJP = permohonan.getTarikhSurat();
			String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";
			
			String TarikhPermohonan = permohonan.getTarikhTerima();
			String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
			
			long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
			String idKem = String.valueOf(pfdFail.getIdKementerian());
			String idNeg = String.valueOf(pfdFail.getIdNegeri());
			Long setIdStatus = 0L;
			setIdStatus = FrmUtilData.getIdStatusByLangkah("-1",String.valueOf(pfdFail.getIdSubUrusan()),"=");
			kodKementerianMampu = getKementerianByMampu(Integer.parseInt(idKem));//
			kodNegeriMampu = getNegeriByMampu(Integer.parseInt(idNeg));
			
			permohonan.setIdPermohonan(idPermohonan);
			r = new SQLRenderer();
			r.add("id_Permohonan", idPermohonan);
			r.add("NO_PERMOHONAN",FrmUtilData.generateNoOnline(2,kodKementerianMampu, String.valueOf(idKem), kodNegeriMampu, idNeg));
			r.add("ID_STATUS",setIdStatus);
			r.add("id_Fail",idFail);
			r.add("id_Jkptg",1);
			//r.add("no_Permohonan","TIADA");
			r.add("no_Perserahan","TIADA");
			r.add("tarikh_Surat", r.unquote(TSKJP));
			r.add("tarikh_Terima", r.unquote(TP));
			r.add("tujuan",permohonan.getTujuan());
			r.add("id_Masuk", permohonan.getIdMasuk());
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPERMOHONAN");
			
			stmt.executeUpdate(sql);
			
			long idHtpPermohonan = DB.getNextID("TBLHTPPERMOHONAN_SEQ");
			htpPermohonan.setIdHtpPermohonan(idHtpPermohonan);
			r = new SQLRenderer();
			r.add("id_Htppermohonan",idHtpPermohonan);
			r.add("id_Permohonan",permohonan.getIdPermohonan());
			r.add("id_Agensi", htpPermohonan.getIdAgensi());
			r.add("id_Jenistanah", htpPermohonan.getIdJenisTanah());
			r.add("id_Pegawai", htpPermohonan.getIdPegawai());
			r.add("no_Rujukan_KJP",htpPermohonan.getNoRujukanKJP());
			r.add("no_Rujukan_Lain", htpPermohonan.getNoRujukanLain());
			r.add("tarikh_Agihan", r.unquote("sysdate"));
			r.add("id_Masuk", permohonan.getIdMasuk());
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLHTPPERMOHONAN");
			
			stmt.executeUpdate(sql);
			
			conn.commit();
			
			FrmPembelianSemakanData.StatusChange_ActionOnline(idPermohonan, Integer.parseInt(String.valueOf(pfdFail.getIdSubUrusan())), idFail);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return htpPermohonan;
	}
	
	@Override
	public HtpPermohonan findPermohonan(String idPermohonan,String idHtpPermohonan) throws Exception {
		Db db = null;
		//Connection conn = null;
		String sql = "";
		
		Date now = new Date();
		 SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		 String tarikhDaftarFail = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			sql = "select A.id_Permohonan,B.ID_HTPPERMOHONAN,C.ID_FAIL,C.TAJUK_FAIL,C.ID_KEMENTERIAN,B.ID_AGENSI,C.ID_URUSAN,C.NO_FAIL, E.ID_SUBURUSAN, ";
				sql += " TO_CHAR(A.TARIKH_SURAT,'DD/MM/YYYY') as TARIKH_SURAT,TO_CHAR(A.TARIKH_TERIMA,'DD/MM/YYYY') AS TARIKH_TERIMA,A.TUJUAN, ";
					sql += " B.ID_JENISTANAH,B.ID_PEGAWAI,B.NO_RUJUKAN_KJP,B.NO_RUJUKAN_LAIN,B.TARIKH_AGIHAN,B.ID_DAERAH,";
						sql += " C.ID_NEGERI,C.ID_STATUS, D.TARAF_KESELAMATAN,D.ID_TARAFKESELAMATAN,F.NAMA_NEGERI,TO_CHAR(C.TARIKH_DAFTAR_FAIL,'DD/MM/YYYY') AS OPEN_FILE_DATE, ";
						sql +=" G.NAMA_KEMENTERIAN, H.NAMA_AGENSI,E.NAMA_SUBURUSAN,E.KOD_SUBURUSAN,A.NO_PERMOHONAN";
							sql += " from TBLPERMOHONAN A, TBLHTPPERMOHONAN B , TBLPFDFAIL C , TBLpfdrujtarafkeselamatan D, tblrujsuburusan E,TBLRUJNEGERI F,TBLRUJKEMENTERIAN G,TBLRUJAGENSI H ";
								sql += " where A.ID_PERMOHONAN=B.ID_PERMOHONAN ";
									sql += " And C.ID_SUBURUSAN = E.ID_SUBURUSAN ";
										sql += " AND D.ID_TARAFKESELAMATAN = C.ID_TARAFKESELAMATAN ";
											sql += " AND C.ID_FAIL = A.ID_FAIL ";
												sql += " AND A.ID_PERMOHONAN = '"+ idPermohonan +"'";
												sql += " AND F.ID_NEGERI = C.ID_NEGERI";
												sql += " AND G.ID_KEMENTERIAN = C.ID_KEMENTERIAN";
												sql += " AND B.ID_AGENSI = H.ID_AGENSI";
											if(!idHtpPermohonan.equals("")){
												sql += " AND B.ID_HTPPERMOHONAN = '"+ idHtpPermohonan +"'";
											}
											sql +=" ORDER BY A.id_Permohonan desc";
											System.out.println("Permohonan:::findPermohonan::sql::"+sql);
											
											 Statement stmt = db.getStatement();
										     ResultSet rs = stmt.executeQuery(sql);		
										     
										    
										     if(rs.next()){
										    	fail = new PfdFail();
										 		permohonan = new Permohonan();
										 		htpPermohonan = new HtpPermohonan();
										 		
//										 		 String TarikhSurKJP = rs.getString("TARIKH_SURAT");
//											     String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";	
//											     String TarikhPermohonan = rs.getString("TARIKH_TERIMA");
//											     String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
											     
										 			htpPermohonan.setIdHtpPermohonan(rs.getString("ID_HTPPERMOHONAN"));
										 			htpPermohonan.setIdAgensi(rs.getLong("ID_AGENSI"));
										 			htpPermohonan.setIdJenisTanah((rs.getString("ID_JENISTANAH")==null?"0":rs.getString("ID_JENISTANAH")));
										 			htpPermohonan.setNoRujukanKJP(rs.getString("NO_RUJUKAN_KJP"));
										 			htpPermohonan.setNoRujukanLain(rs.getString("NO_RUJUKAN_LAIN"));
										 			htpPermohonan.setNamaAgensi(rs.getString("NAMA_AGENSI"));
										 			permohonan.setNoPermohonan(rs.getString("NO_PERMOHONAN"));
										 			permohonan.setIdPermohonan(rs.getString("id_Permohonan"));
										 			permohonan.setTarikhSurat(rs.getString("TARIKH_SURAT"));
										 			permohonan.setTarikhTerima(rs.getString("TARIKH_TERIMA"));
										 			permohonan.setTujuan(rs.getString("TUJUAN"));
										 			permohonan.setNamaNegeri(rs.getString("NAMA_NEGERI"));
										 			fail.setIdFail(rs.getLong("ID_FAIL"));
										 			fail.setNoFail(rs.getString("NO_FAIL"));
										 			fail.setIdKementerian(rs.getString("ID_KEMENTERIAN"));
										 			fail.setIdNegeri(rs.getString("ID_NEGERI"));
										 			fail.setIdUrusan(rs.getLong("ID_URUSAN"));
										 			fail.setIdSubUrusan(rs.getString("ID_SUBURUSAN"));
										 			fail.setIdTarafKeselamatan(rs.getString("ID_TARAFKESELAMATAN"));
										 			fail.setTarikhDaftarFail(rs.getString("OPEN_FILE_DATE"));
										 			fail.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));
										 			fail.setNamaSuburusan(rs.getString("NAMA_SUBURUSAN"));
										 			fail.setKodSuburusan(rs.getString("KOD_SUBURUSAN"));
										 			fail.setTajukFail(rs.getString("TAJUK_FAIL"));
										 			permohonan.setPfdFail(fail);
										 			htpPermohonan.setPermohonan(permohonan);
										 			
										 			conn.commit();
										     }
										    	
		 } finally {
		      if (db != null) db.close();
		    }
		

		return htpPermohonan;
	}
	@Override
	public HtpPermohonan kemaskiniPermohonan(HtpPermohonan htpPermohonan,String idPermohonan,String idhtpPermohonan)throws Exception {
		Db db = null;
		//Connection conn = null;
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
			String TarikhSurKJP = permohonan.getTarikhSurat();
			String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";
			
			String TarikhPermohonan = permohonan.getTarikhTerima();
			String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
			
			r = new SQLRenderer();
			r.update("id_Permohonan", idPermohonan);
			//r.add("id_Fail",idFail);
			r.add("id_Jkptg",1);
			r.add("no_Permohonan","TIADA");
			r.add("no_Perserahan","TIADA");
			r.add("tarikh_Surat", r.unquote(TSKJP));
			r.add("tarikh_Terima", r.unquote(TP));
			r.add("tujuan",permohonan.getTujuan());
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			
			stmt.executeUpdate(sql);
			
			//long idHtpPermohonan = DB.getNextID("TBLHTPPERMOHONAN_SEQ");
			htpPermohonan.getIdHtpPermohonan();
			r = new SQLRenderer();
			r.update("id_Htppermohonan",idhtpPermohonan);
			r.update("id_Permohonan",idPermohonan);
			r.add("id_Agensi", htpPermohonan.getIdAgensi());
			r.add("id_Jenistanah", htpPermohonan.getIdJenisTanah());
			r.add("id_Pegawai", htpPermohonan.getIdPegawai());
			r.add("no_Rujukan_KJP",htpPermohonan.getNoRujukanKJP());
			r.add("no_Rujukan_Lain", htpPermohonan.getNoRujukanLain());
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			//r.add("tarikh_Agihan", r.unquote(tarikhDaftarFail));
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
			r.add("TAJUK_FAIL", permohonan.getTujuan());
			r.add("ID_KEMASKINI", permohonan.getIdMasuk());
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("TBLPFDFAIL");
			//
			stmt.executeUpdate(sql);
			
			conn.commit();
	}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return htpPermohonan;
	}

	@Override
	public Vector<HtpPermohonan> findFail(String carian, String noFail, String idNegeri) {
		Db db = null;
		Vector<HtpPermohonan> v = new Vector<HtpPermohonan>();
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "SELECT distinct p.TUJUAN, f.id_Fail, p.id_Permohonan, f.no_Fail, f.tajuk_Fail, s.keterangan, n.nama_Negeri, n.kod_Mampu,n.id_Negeri, h.id_htppermohonan ";
			sql +="FROM Tblpfdfail f, Tblpermohonan p, Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, Tblrujstatus s, Tblrujnegeri n, tblhtppermohonan h ";
			sql +="WHERE f.id_Fail = p.id_Fail AND p.id_Permohonan = sf.id_Permohonan AND n.id_Negeri = f.id_Negeri AND h.id_Permohonan = p.id_Permohonan  ";
			sql +="AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status ";
			sql +="AND sf.aktif = '1' AND f.id_Urusan = 2 AND f.tajuk_Fail LIKE '%"+carian+"%' ";
			sql +="AND f.ID_STATUS <> 999 ";
			sql +="AND f.no_Fail LIKE '%"+noFail+"%' ";
			if(idNegeri != null && !idNegeri.equals("") && !idNegeri.equals("0"))
	    	  sql +=" AND f.id_Negeri = "+idNegeri;
			sql +=" order by p.id_Permohonan desc";
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				permohonan = new Permohonan();
				fail = new PfdFail();
				htpPermohonan = new HtpPermohonan();
				
				fail.setIdFail(rs.getLong("id_Fail"));
				permohonan.setNamaNegeri(rs.getString("nama_Negeri"));
				permohonan.setIdPermohonan(rs.getLong("id_Permohonan"));
				fail.setNoFail(rs.getString("no_Fail"));
				permohonan.setTujuan(rs.getString("TUJUAN"));
				//permohonan.setTujuan(rs.getString("tajuk_Fail"));
				htpPermohonan.setIdHtpPermohonan(rs.getString("id_htppermohonan"));
				htpPermohonan.setPermohonan(permohonan);
				htpPermohonan.setStatusPermohonan(rs.getString("keterangan"));
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);
				
				v.addElement(htpPermohonan);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
			 if (db != null){
		    	  db.close();
		      }
		}
		return v;
		
	}
	@Override
	public Vector<HtpPermohonan> findFailOnlineUrusan(String carian
		,String noFail
		,String idNegeri
		,String idKementerian
		,String idUrusan,String langkah) {
		Db db = null;
		Vector<HtpPermohonan> v = new Vector<HtpPermohonan>();
		try{
			db = new Db();
			Statement stmt = db.getStatement();

			String sql = "SELECT distinct p.no_permohonan,p.tujuan,p.id_Permohonan" +
					",  f.id_Fail, f.no_Fail, f.tajuk_Fail,f.id_urusan" +
					", s.keterangan" +
					", n.nama_Negeri, n.kod_Mampu,n.id_Negeri" +
					", h.id_htppermohonan ";
			sql +=" FROM tblpfdfail f, tblpermohonan p, tblrujsuburusanstatusfail sf, tblrujsuburusanstatus ss, tblrujstatus s, tblrujnegeri n, tblhtppermohonan h ";
			sql +=" WHERE f.id_Fail = p.id_Fail AND p.id_Permohonan = sf.id_Permohonan AND n.id_Negeri = f.id_Negeri AND h.id_Permohonan = p.id_Permohonan  ";
			sql +=" AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status ";
			sql +=" AND sf.aktif = '1' ";
			//sql +=" AND ( F.ID_STATUS <> 999 OR F.ID_STATUS IS null) ";
			//sql +=" AND ( nvl(no_fail,' ') = ' ' OR F.NO_FAIL IS null ) ";
			//sql +=" AND f.no_Fail LIKE '%"+noFail+"%' ";
			if(!carian.equals(""))
				sql +=" AND f.tajuk_fail LIKE '%"+carian+"%' ";
			if(!langkah.equals(""))
				sql +=" AND ss.langkah = '"+langkah+"' ";
			if(idNegeri != null && !idNegeri.equals("") && !idNegeri.equals("0")&& !idNegeri.equals("99999"))
	    	  sql +=" AND f.id_Negeri = "+idNegeri;
			if(idKementerian != null && !idKementerian.equals("") && !idKementerian.equals("0") && !idKementerian.equals("99999"))
		    	  sql +=" AND f.ID_KEMENTERIAN = "+idKementerian;
			if(idUrusan != null && !idUrusan.equals("") && !idUrusan.equals("0") && !idUrusan.equals("99999"))
		    	  sql +=" AND f.id_urusan IN ("+idUrusan+")";
			//sql +=" ORDER BY n.kod_Mampu";
			sql +=" order by p.id_permohonan desc";
			myLog.info("findFailOnline:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				permohonan = new Permohonan();
				fail = new PfdFail();
				htpPermohonan = new HtpPermohonan();
				
				fail.setIdFail(rs.getLong("id_fail"));
				fail.setIdUrusan(rs.getLong("id_urusan"));
				permohonan.setNamaNegeri(rs.getString("nama_negeri"));
				permohonan.setIdPermohonan(rs.getLong("id_permohonan"));
				permohonan.setNoPermohonan(rs.getString("no_permohonan"));
				fail.setNoFail(rs.getString("no_fail"));
				permohonan.setTujuan(rs.getString("tujuan"));
				//permohonan.setTujuan(rs.getString("tajuk_Fail"));
				htpPermohonan.setIdHtpPermohonan(rs.getString("id_htppermohonan"));
				htpPermohonan.setStatusPermohonan(rs.getString("keterangan"));
				//htpPermohonan.setPermohonan(permohonan);
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);
				
				v.addElement(htpPermohonan);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			 if (db != null){
		    	  db.close();
		      }
		}
		return v;
	}
	@Override
	public Vector<HtpPermohonan> findFailOnline(String carian, String noFail, String idNegeri,String idKementerian) {
		Db db = null;
		Vector<HtpPermohonan> v = new Vector<HtpPermohonan>();
		try{
			db = new Db();
			Statement stmt = db.getStatement();

			String sql = "SELECT distinct p.NO_PERMOHONAN,p.TUJUAN,p.id_Permohonan" +
					",  f.id_Fail, f.no_Fail, f.tajuk_Fail,F.ID_URUSAN" +
					", s.keterangan" +
					", n.nama_Negeri, n.kod_Mampu,n.id_Negeri" +
					", h.id_htppermohonan ";
			sql +=" FROM Tblpfdfail f, Tblpermohonan p, Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, Tblrujstatus s, Tblrujnegeri n, tblhtppermohonan h ";
			sql +=" WHERE f.id_Fail = p.id_Fail AND p.id_Permohonan = sf.id_Permohonan AND n.id_Negeri = f.id_Negeri AND h.id_Permohonan = p.id_Permohonan  ";
			sql +=" AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status ";
			sql +=" AND sf.aktif = '1' AND f.tajuk_Fail LIKE '%"+carian+"%' ";
			sql +=" AND ( F.ID_STATUS <> 999 OR F.ID_STATUS IS null) ";
			sql +=" AND ( nvl(no_fail,' ') = ' ' OR F.NO_FAIL IS null ) ";
			//sql +=" AND f.no_Fail LIKE '%"+noFail+"%' ";
			if(idNegeri != null && !idNegeri.equals("") && !idNegeri.equals("0")&& !idNegeri.equals("99999"))
	    	  sql +=" AND f.id_Negeri = "+idNegeri;
			if(idKementerian != null && !idKementerian.equals("") && !idKementerian.equals("0") && !idKementerian.equals("99999"))
		    	  sql +=" AND f.ID_KEMENTERIAN = "+idKementerian;
			//sql +=" ORDER BY n.kod_Mampu";
			sql +=" order by p.id_Permohonan desc";
			myLog.info("findFailOnline:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				permohonan = new Permohonan();
				fail = new PfdFail();
				htpPermohonan = new HtpPermohonan();
				
				fail.setIdFail(rs.getLong("id_Fail"));
				fail.setIdUrusan(rs.getLong("ID_URUSAN"));
				permohonan.setNamaNegeri(rs.getString("nama_Negeri"));
				permohonan.setIdPermohonan(rs.getLong("id_Permohonan"));
				permohonan.setNoPermohonan(rs.getString("NO_PERMOHONAN"));
				fail.setNoFail(rs.getString("no_Fail"));
				permohonan.setTujuan(rs.getString("TUJUAN"));
				//permohonan.setTujuan(rs.getString("tajuk_Fail"));
				htpPermohonan.setIdHtpPermohonan(rs.getString("id_htppermohonan"));
				htpPermohonan.setStatusPermohonan(rs.getString("keterangan"));
				//htpPermohonan.setPermohonan(permohonan);
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);
				
				v.addElement(htpPermohonan);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			 if (db != null){
		    	  db.close();
		      }
		}
		return v;
	}
	
	@Override
	public Vector<HtpPermohonan> findFailOnlineUrusan(String carian
		,String noFail
		,String idNegeri
		,String idKementerian
		,String idUrusan) {
		Db db = null;
		Vector<HtpPermohonan> v = new Vector<HtpPermohonan>();
		try{
			db = new Db();
			Statement stmt = db.getStatement();

			String sql = "SELECT distinct p.no_permohonan,p.tujuan,p.id_Permohonan" +
					",  f.id_Fail, f.no_Fail, f.tajuk_Fail,f.id_urusan" +
					", s.keterangan" +
					", n.nama_Negeri, n.kod_Mampu,n.id_Negeri" +
					", h.id_htppermohonan ";
			sql +=" FROM tblpfdfail f, tblpermohonan p, tblrujsuburusanstatusfail sf, tblrujsuburusanstatus ss, tblrujstatus s, tblrujnegeri n, tblhtppermohonan h ";
			sql +=" WHERE f.id_Fail = p.id_Fail AND p.id_Permohonan = sf.id_Permohonan AND n.id_Negeri = f.id_Negeri AND h.id_Permohonan = p.id_Permohonan  ";
			sql +=" AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status ";
			sql +=" AND sf.aktif = '1' ";
			//sql +=" AND ( F.ID_STATUS <> 999 OR F.ID_STATUS IS null) ";
			//sql +=" AND ( nvl(no_fail,' ') = ' ' OR F.NO_FAIL IS null ) ";
			//sql +=" AND f.no_Fail LIKE '%"+noFail+"%' ";
			if(!carian.equals(""))
				sql +=" AND f.tajuk_Fail LIKE '%"+carian+"%' ";
			if(idNegeri != null && !idNegeri.equals("") && !idNegeri.equals("0")&& !idNegeri.equals("99999"))
	    	  sql +=" AND f.id_Negeri = "+idNegeri;
			if(idKementerian != null && !idKementerian.equals("") && !idKementerian.equals("0") && !idKementerian.equals("99999"))
		    	  sql +=" AND f.ID_KEMENTERIAN = "+idKementerian;
			if(idUrusan != null && !idUrusan.equals("") && !idUrusan.equals("0") && !idUrusan.equals("99999"))
		    	  sql +=" AND f.id_urusan IN ("+idUrusan+")";
			//sql +=" ORDER BY n.kod_Mampu";
			sql +=" order by p.id_permohonan desc";
			myLog.info("findFailOnline:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				permohonan = new Permohonan();
				fail = new PfdFail();
				htpPermohonan = new HtpPermohonan();
				
				fail.setIdFail(rs.getLong("id_fail"));
				fail.setIdUrusan(rs.getLong("id_urusan"));
				permohonan.setNamaNegeri(rs.getString("nama_negeri"));
				permohonan.setIdPermohonan(rs.getLong("id_permohonan"));
				permohonan.setNoPermohonan(rs.getString("no_permohonan"));
				fail.setNoFail(rs.getString("no_fail"));
				permohonan.setTujuan(rs.getString("tujuan"));
				//permohonan.setTujuan(rs.getString("tajuk_Fail"));
				htpPermohonan.setIdHtpPermohonan(rs.getString("id_htppermohonan"));
				htpPermohonan.setStatusPermohonan(rs.getString("keterangan"));
				//htpPermohonan.setPermohonan(permohonan);
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);
				
				v.addElement(htpPermohonan);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			 if (db != null){
		    	  db.close();
		      }
		}
		return v;
	}
	
	@Override
	public Vector<HtpPermohonan> findFailOnlineAktif(String carian, String noFail, String idNegeri,String idKementerian) {
		Db db = null;
		Vector<HtpPermohonan> v = new Vector<HtpPermohonan>();
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "SELECT distinct p.NO_PERMOHONAN,p.TUJUAN,p.id_Permohonan" +
					",  f.id_Fail, f.no_Fail, f.tajuk_Fail,F.ID_URUSAN" +
					", s.keterangan" +
					", n.nama_Negeri, n.kod_Mampu,n.id_Negeri" +
					", h.id_htppermohonan ";
			sql +=" FROM Tblpfdfail f, Tblpermohonan p, Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, Tblrujstatus s, Tblrujnegeri n, tblhtppermohonan h ";
			sql +=" WHERE f.id_Fail = p.id_Fail AND p.id_Permohonan = sf.id_Permohonan AND n.id_Negeri = f.id_Negeri AND h.id_Permohonan = p.id_Permohonan  ";
			sql +=" AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status ";
			sql +=" AND sf.aktif = '1' AND f.tajuk_Fail LIKE '%"+carian+"%' ";
			sql +=" AND ( F.ID_STATUS <> 999 OR F.ID_STATUS IS null) ";
			sql +=" AND ( nvl(no_fail,' ') = ' ' OR F.NO_FAIL IS null ) ";
			sql +=" AND ( SS.LANGKAH <> '102' ) ";
			//sql +=" AND f.no_Fail LIKE '%"+noFail+"%' ";
			if(idNegeri != null && !idNegeri.equals("") && !idNegeri.equals("0")&& !idNegeri.equals("99999"))
	    	  sql +=" AND f.id_Negeri = "+idNegeri;
			if(idKementerian != null && !idKementerian.equals("") && !idKementerian.equals("0") && !idKementerian.equals("99999"))
		    	  sql +=" AND f.ID_KEMENTERIAN = "+idKementerian;
			//sql +=" ORDER BY n.kod_Mampu";
			sql +=" order by p.id_Permohonan desc";
			//myLog.info("findFailOnlineAktif:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				permohonan = new Permohonan();
				fail = new PfdFail();
				htpPermohonan = new HtpPermohonan();
				
				fail.setIdFail(rs.getLong("id_Fail"));
				fail.setIdUrusan(rs.getLong("ID_URUSAN"));
				permohonan.setNamaNegeri(rs.getString("nama_Negeri"));
				permohonan.setIdPermohonan(rs.getLong("id_Permohonan"));
				permohonan.setNoPermohonan(rs.getString("NO_PERMOHONAN"));
				fail.setNoFail(rs.getString("no_Fail"));
				permohonan.setTujuan(rs.getString("TUJUAN"));
				//permohonan.setTujuan(rs.getString("tajuk_Fail"));
				htpPermohonan.setIdHtpPermohonan(rs.getString("id_htppermohonan"));
				htpPermohonan.setStatusPermohonan(rs.getString("keterangan"));
				//htpPermohonan.setPermohonan(permohonan);
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);
				
				v.addElement(htpPermohonan);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			 if (db != null){
		    	  db.close();
		      }
		}
		return v;
	}
	
	public HakmilikUrusan simpanHakmilik(HakmilikUrusan urusan) {
		Db db = null;
		//Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			long idHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
			urusan.setIdhakmilikurusan(String.valueOf(idHakmilikurusan));
			
			String TT = "to_date('" + urusan.getTarikhMula() + "','dd/MM/yyyy')";
			String TL = "to_date('" + urusan.getTarikhLuput() + "','dd/MM/yyyy')";
			
			r = new SQLRenderer();
			r.add("id_Hakmilikurusan", idHakmilikurusan);
			r.add("id_Permohonan", urusan.getPermohonan().getIdPermohonan());	
			r.add("pegangan_Hakmilik",urusan.getPeganganHakmilik());
			r.add("id_Negeri", urusan.getIdNegeri());
			r.add("id_Daerah", urusan.getIdDaerah());
			r.add("id_Mukim", urusan.getIdMukim());
			r.add("id_JenisHakmilik", urusan.getIdHakmilik());
			r.add("no_Hakmilik", urusan.getNohakmilik());
			r.add("no_Lot", urusan.getNolot());
			r.add("id_Lot", urusan.getIdLot());
			r.add("no_Bangunan", urusan.getNoBangunan());
		    r.add("no_Tingkat", urusan.getNoTingkat());
		    r.add("no_Petak", urusan.getNoPetak());
			r.add("Tarikh_Mula", r.unquote(TT));
			r.add("Tarikh_Luput", r.unquote(TL));
			r.add("Luas", urusan.getLuas());
			r.add("id_Luas", urusan.getIdLuas());
			r.add("no_Pelan", urusan.getNoPlan());
			r.add("id_Jenisrizab", urusan.getIdJenisRizab());
			r.add("id_Kategori", urusan.getIdKategoriTanah());
			r.add("id_Subkategori", urusan.getIdSubKetegoriTanah());		  
			r.add("status_tanah", urusan.getStatusTanah());		  
			  
		    sql = r.getSQLInsert("Tblhtphakmilikurusan");
		    
		    stmt.executeUpdate(sql);
		    conn.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			 if (db != null){
		    	  db.close();
		      }
		}
		return urusan;
	}
	
	public HakmilikUrusan simpanHakmilikOnline(HakmilikUrusan urusan) {
		Db db = null;
		//Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			long idHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
			urusan.setIdhakmilikurusan(String.valueOf(idHakmilikurusan));
			
			String TT = "to_date('" + urusan.getTarikhMula() + "','dd/MM/yyyy')";
			String TL = "to_date('" + urusan.getTarikhLuput() + "','dd/MM/yyyy')";
			
			r = new SQLRenderer();
			r.add("id_Hakmilikurusan", idHakmilikurusan);
			r.add("id_Permohonan", urusan.getPermohonan().getIdPermohonan());	
			r.add("pegangan_Hakmilik",urusan.getPeganganHakmilik());
			r.add("id_Negeri", urusan.getIdNegeri());
			r.add("id_Daerah", urusan.getIdDaerah());
			r.add("id_Mukim", urusan.getIdMukim());
			r.add("id_JenisHakmilik", urusan.getIdHakmilik());
			r.add("no_Hakmilik", urusan.getNohakmilik());
			r.add("no_Lot", urusan.getNolot());
			r.add("id_Lot", urusan.getIdLot());
			r.add("no_Bangunan", urusan.getNoBangunan());
		    r.add("no_Tingkat", urusan.getNoTingkat());
		    r.add("no_Petak", urusan.getNoPetak());
			r.add("Tarikh_Mula", r.unquote(TT));
			r.add("Tarikh_Luput", r.unquote(TL));
			r.add("Luas", urusan.getLuas());
			r.add("id_Luas", urusan.getIdLuas());
			r.add("no_Pelan", urusan.getNoPlan());
			r.add("id_Jenisrizab", urusan.getIdJenisRizab());
			r.add("id_Kategori", urusan.getIdKategoriTanah());
			r.add("id_Subkategori", urusan.getIdSubKetegoriTanah());		  
			r.add("status_tanah", "");		  
			  
		    sql = r.getSQLInsert("Tblhtphakmilikurusan");
		    
		    stmt.executeUpdate(sql);
		    conn.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			 if (db != null){
		    	  db.close();
		      }
		}
		return urusan;
	}

	public HakmilikUrusan findByHakmilikUrusanId(String hakmilikUrusanId) {
		Db db = null;
		//Connection conn = null;
		SQLRenderer r = null;
		HakmilikUrusan urusan = null;
		try{
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			r = new SQLRenderer();
			r.add("id_Permohonan");	
			r.add("pegangan_Hakmilik");
			r.add("id_Negeri");
			r.add("id_Daerah");
			r.add("id_Mukim");
			r.add("id_JenisHakmilik");
			r.add("no_Hakmilik");
			r.add("no_Lot");
			r.add("id_Lot");
			r.add("no_Bangunan");
		    r.add("no_Tingkat");
		    r.add("no_Petak");
		    r.add("TO_CHAR(Tarikh_Mula,'DD/MM/YYYY') AS Tarikh_Mula");
			r.add("TO_CHAR(Tarikh_Luput,'DD/MM/YYYY') AS Tarikh_Luput");
			r.add("Luas");
			r.add("id_Luas");
			r.add("no_Pelan");
			r.add("NVL(id_Jenisrizab,0) id_Jenisrizab");
			r.add("NVL(id_Kategori,0) id_Kategori");
			r.add("id_Subkategori");
			r.add("id_Hakmilikurusan");
			r.add("status_tanah");
			r.set("id_Hakmilikurusan", hakmilikUrusanId);
			String sql = r.getSQLSelect("Tblhtphakmilikurusan");
			
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				urusan = new HakmilikUrusan();
				Permohonan permohonan = new Permohonan();
				urusan.setIdhakmilikurusan(hakmilikUrusanId);
				permohonan.setIdPermohonan(rs.getLong("id_Permohonan"));
				urusan.setPeganganHakmilik(rs.getString("pegangan_Hakmilik"));
				urusan.setIdNegeri(rs.getString("id_Negeri"));
				urusan.setIdDaerah(rs.getString("id_Daerah"));
				urusan.setIdMukim(rs.getString("id_Mukim"));
				urusan.setIdHakmilik(rs.getString("id_JenisHakmilik"));
				urusan.setNohakmilik(rs.getString("no_Hakmilik"));
				urusan.setNolot(rs.getString("no_Lot"));
				urusan.setIdLot(rs.getString("id_Lot"));
				urusan.setNoBangunan(rs.getString("no_Bangunan"));
				urusan.setNoTingkat(rs.getString("no_Tingkat"));
				urusan.setNoPetak(rs.getString("no_Petak"));
				urusan.setTarikhMula(rs.getString("Tarikh_Mula"));
				urusan.setTarikhLuput(rs.getString("Tarikh_Luput"));
				urusan.setLuas(rs.getString("Luas"));
				urusan.setIdLuas(rs.getString("id_Luas"));
				urusan.setNoPlan(rs.getString("no_Pelan"));
				urusan.setIdJenisRizab(Utils.isNull(rs.getString("id_Jenisrizab")));
				urusan.setIdKategoriTanah(rs.getString("id_Kategori"));
				urusan.setIdSubKetegoriTanah(rs.getString("id_Subkategori"));
				urusan.setStatusTanah(rs.getString("status_tanah"));
				urusan.setPermohonan(permohonan);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			 if (db != null){
		    	  db.close();
		      }
		}
		return urusan;
	}
	
	public Vector<HakmilikUrusan> getHakmilikList(String idPermohonan) {
		Db db = null;
		//Connection conn = null;
		SQLRenderer r = null;
		Vector<HakmilikUrusan> v = new Vector<HakmilikUrusan>();
		try{
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			r = new SQLRenderer();
			r.add("A.id_Hakmilikurusan");
			r.add("A.id_Permohonan");	
			r.add("A.pegangan_Hakmilik");
			r.add("A.id_Negeri");
			r.add("A.id_Daerah");
			r.add("A.id_Mukim");
			r.add("A.id_JenisHakmilik");
			r.add("A.no_Hakmilik");
			r.add("A.no_Lot");
			r.add("A.id_Lot");
			r.add("A.no_Bangunan");
		    r.add("A.no_Tingkat");
		    r.add("A.no_Petak");
			r.add("TO_CHAR(A.Tarikh_Mula,'DD/MM/YYYY') AS Tarikh_Mula");
			r.add("TO_CHAR(A.Tarikh_Luput,'DD/MM/YYYY') AS Tarikh_Luput");
			r.add("A.Luas");
			r.add("A.id_Luas");
			r.add("A.no_Pelan");
			r.add("A.id_Jenisrizab");
			r.add("A.id_Kategori");
			r.add("A.id_Subkategori");
			r.add("B.KETERANGAN");
			r.add("C.KETERANGAN as luas_keterangan");
			r.add("D.keterangan as jenis_keterangan");
			r.add("D.KOD_JENIS_HAKMILIK as jenis_hakmilik");
			r.add("A.id_Lot",r.unquote("B.ID_LOT"));
			r.add("A.id_luas",r.unquote("C.id_luas"));
			r.add("A.id_JenisHakmilik",r.unquote("D.id_JenisHakmilik"));
			r.set("A.id_Permohonan", idPermohonan);
			String sql = r.getSQLSelect("Tblhtphakmilikurusan A, TBLRUJLOT B,TBLRUJLUAS C,tblrujjenishakmilik D");
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				HakmilikUrusan urusan = new HakmilikUrusan();
				Permohonan permohonan = new Permohonan();
				urusan.setIdhakmilikurusan(rs.getString("id_Hakmilikurusan"));
				urusan.setPeganganHakmilik(rs.getString("pegangan_Hakmilik"));
				urusan.setIdNegeri(rs.getString("id_Negeri"));
				urusan.setIdDaerah(rs.getString("id_Daerah"));
				urusan.setIdMukim(rs.getString("id_Mukim"));
				urusan.setIdHakmilik(rs.getString("id_JenisHakmilik"));
				urusan.setNohakmilik(rs.getString("no_Hakmilik"));
				urusan.setNolot(rs.getString("no_Lot"));
				urusan.setIdLot(rs.getString("id_Lot"));
				urusan.setNoBangunan(rs.getString("no_Bangunan"));
				urusan.setNoTingkat(rs.getString("no_Tingkat"));
				urusan.setNoPetak(rs.getString("no_Petak"));
				urusan.setTarikhMula(rs.getString("Tarikh_Mula"));
				urusan.setTarikhLuput(rs.getString("Tarikh_Luput"));
				urusan.setLuas(rs.getString("Luas"));
				urusan.setIdLuas(rs.getString("id_Luas"));
				urusan.setNoPlan(rs.getString("no_Pelan"));
				urusan.setIdJenisRizab(rs.getString("id_Jenisrizab"));
				urusan.setIdKategoriTanah(rs.getString("id_Kategori"));
				urusan.setIdSubKetegoriTanah(rs.getString("id_Subkategori"));
				urusan.setKeteranganLot(rs.getString("KETERANGAN"));
				urusan.setLuasKeterangan(rs.getString("luas_keterangan"));
				urusan.setJenisKeterangan(rs.getString("jenis_keterangan"));
				urusan.setKodjenishakmilik(rs.getString("jenis_hakmilik"));
				urusan.setPermohonan(permohonan);
				v.addElement(urusan);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			 if (db != null){
		    	  db.close();
		      }
		}
		return v;
	}
	
	public HakmilikUrusan updateHakmilik(HakmilikUrusan urusan) {
		Db db = null;
		//Connection conn = null;
		SQLRenderer r = null;
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			
			String TT = "to_date('" + urusan.getTarikhMula() + "','dd/MM/yyyy')";
			String TL = "to_date('" + urusan.getTarikhLuput() + "','dd/MM/yyyy')";
			
			r = new SQLRenderer();
			r.update("id_Hakmilikurusan", urusan.getIdhakmilikurusan());
			r.add("id_Permohonan", urusan.getPermohonan().getIdPermohonan());	
			r.add("pegangan_Hakmilik",urusan.getPeganganHakmilik());
			r.add("id_Negeri", urusan.getIdNegeri());
			r.add("id_Daerah", urusan.getIdDaerah());
			r.add("id_Mukim", urusan.getIdMukim());
			r.add("id_JenisHakmilik", urusan.getIdHakmilik());
			r.add("no_Hakmilik", urusan.getNohakmilik());
			r.add("no_Lot", urusan.getNolot());
			r.add("id_Lot", urusan.getIdLot());
			r.add("no_Bangunan", urusan.getNoBangunan());
		    r.add("no_Tingkat", urusan.getNoTingkat());
		    r.add("no_Petak", urusan.getNoPetak());
		    r.add("Tarikh_Mula",r.unquote(TT));
			r.add("Tarikh_Luput",r.unquote(TL));
			r.add("Luas", urusan.getLuas());
			r.add("id_Luas", urusan.getIdLuas());
			r.add("no_Pelan", urusan.getNoPlan());
			r.add("id_Jenisrizab", urusan.getIdJenisRizab());
			r.add("id_Kategori", urusan.getIdKategoriTanah());
			r.add("id_Subkategori", urusan.getIdSubKetegoriTanah());	
			r.add("status_tanah", urusan.getStatusTanah());	
			String sql = r.getSQLUpdate("Tblhtphakmilikurusan");
			
	        stmt.executeUpdate(sql);
	            
	        conn.commit();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			 if (db != null){
		    	  db.close();
		      }
		}
		return urusan;
	}
	
	public void deleteHakmilik(String idHakmilikUrusan) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPHAKMILIKURUSAN
			r.add("ID_HAKMILIKURUSAN", idHakmilikUrusan);

			sql = r.getSQLDelete("TBLHTPHAKMILIKURUSAN");
			
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}	
	
	}
	
	public Pemohon findByPermohonan(String idPermohonan) {
		Db db = null;
		//Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		Pemohon pemohon = null;
		try{
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			r = new SQLRenderer();
			r.add("id_permohonan");
            r.add("id_pemohon");
            r.add("no_pemohon");
            r.add("no_pa");
            r.add("nama_pemohon");
            r.add("alamat_pemohon1");
            r.add("alamat_pemohon2");
            r.add("alamat_pemohon3");
            r.add("poskod");
            r.add("id_daerah");
            r.add("id_negeri");
            r.add("no_tel");
            r.add("no_fax");
            r.add("flag_penjualpemilik");
            r.set("id_permohonan",idPermohonan);
            sql = r.getSQLSelect("TBLHTPPEMOHON");
			
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				pemohon = new Pemohon();
				pemohon.setIdPemohon(rs.getLong("id_pemohon"));
				pemohon.setNoPemohon(rs.getString("no_pemohon"));
				pemohon.setNoPA(rs.getString("no_pa"));
				pemohon.setNama(rs.getString("nama_pemohon"));
				pemohon.setAlamat1(rs.getString("alamat_pemohon1"));
				pemohon.setAlamat2(rs.getString("alamat_pemohon2"));
				pemohon.setAlamat3(rs.getString("alamat_pemohon3"));
				pemohon.setPoskod(rs.getString("poskod"));
				pemohon.setIdNegeri(rs.getString("id_negeri"));
				pemohon.setIdDaerah(rs.getString("id_daerah"));
				pemohon.setFlagPemilik(rs.getString("flag_penjualpemilik"));
				pemohon.setTel(rs.getString("no_tel"));
				pemohon.setFax(rs.getString("no_fax"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return pemohon;
	}
	
	public Pemohon simpanPenjual(Pemohon pemohon) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			r = new SQLRenderer();
			
			long idPemohon = DB.getNextID("TBLHTPPEMOHON_SEQ");
			pemohon.setIdPemohon(idPemohon);
            Date now = new Date();
            SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
            String today = "to_date('" + sdf.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
            
            r.add("id_pemohon", pemohon.getIdPemohon());
            r.add("id_permohonan", pemohon.getPermohonan().getIdPermohonan());
            r.add("no_pemohon", pemohon.getNoPemohon());
            r.add("no_pa", pemohon.getNoPA());
            r.add("nama_pemohon", pemohon.getNama());
            r.add("alamat_pemohon1", pemohon.getAlamat1());
            r.add("alamat_pemohon2", pemohon.getAlamat2());
            r.add("alamat_pemohon3", pemohon.getAlamat3());
            r.add("poskod", pemohon.getPoskod());
            r.add("id_daerah", pemohon.getIdDaerah());
            r.add("id_negeri", pemohon.getIdNegeri());
            r.add("no_tel", pemohon.getTel());
            r.add("no_fax", pemohon.getFax());
            r.add("flag_penjualpemilik", pemohon.getFlagPemilik());
            //r.add("id_masuk", (String)h.get("userID"));
            r.add("tarikh_masuk", r.unquote(today));
            //r.add("id_kemaskini", (String)h.get("userID"));
           // r.add("tarikh_kemaskini", r.unquote(today));

            sql = r.getSQLInsert("TBLHTPPEMOHON");
            
            stmt.executeQuery(sql);

            conn.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return pemohon;
	}
	
	public Pemohon updatePenjual(Pemohon pemohon) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			r = new SQLRenderer();
			
			
            Date now = new Date();
            SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
            String today = "to_date('" + sdf.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
            
            r.update("id_pemohon", pemohon.getIdPemohon());
            r.add("id_permohonan", pemohon.getPermohonan().getIdPermohonan());
            r.add("no_pemohon", pemohon.getNoPemohon());
            r.add("no_pa", pemohon.getNoPA());
            r.add("nama_pemohon", pemohon.getNama());
            r.add("alamat_pemohon1", pemohon.getAlamat1());
            r.add("alamat_pemohon2", pemohon.getAlamat2());
            r.add("alamat_pemohon3", pemohon.getAlamat3());
            r.add("poskod", pemohon.getPoskod());
            r.add("id_daerah", pemohon.getIdDaerah());
            r.add("id_negeri", pemohon.getIdNegeri());
            r.add("no_tel", pemohon.getTel());
            r.add("no_fax", pemohon.getFax());
            r.add("flag_penjualpemilik", pemohon.getFlagPemilik());
            //r.add("id_masuk", (String)h.get("userID"));
            //r.add("tarikh_masuk", r.unquote(today));
            //r.add("id_kemaskini", (String)h.get("userID"));
            r.add("tarikh_kemaskini", r.unquote(today));

            sql = r.getSQLUpdate("TBLHTPPEMOHON");
            
            stmt.executeQuery(sql);

            conn.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return pemohon;
	}
	
	public Bangunan findBangunan(String idBangunan) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		Bangunan bangunan = null;
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			r = new SQLRenderer();
			r.add("ID_BANGUNAN");
			r.add("ID_HAKMILIKURUSAN");
			r.add("NOPETAK");
			r.add("NOBANGUNAN");
			r.add("NOTINGKAT");
			r.set("ID_BANGUNAN", idBangunan);
			sql = r.getSQLSelect("TBLHTPBANGUNAN");
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				bangunan = new Bangunan();
				HakmilikUrusan urusan = new HakmilikUrusan();
				urusan.setIdhakmilikurusan(rs.getString("ID_HAKMILIKURUSAN"));
				bangunan.setIdBangunan(idBangunan);
				bangunan.setNoPetak(rs.getString("NOPETAK"));
				bangunan.setNoBangunan(rs.getString("NOBANGUNAN"));
				bangunan.setNoTingkat(rs.getString("NOTINGKAT"));
				bangunan.setHakmilikUrusan(urusan);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return bangunan;
	}
	
	public Vector<Bangunan> findBangunanByPermohonan(String idPermohonan) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		Vector<Bangunan> v = new Vector<Bangunan>();
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			r = new SQLRenderer();
			
			r.add("A.ID_PERMOHONAN");
			r.add("A.id_Hakmilikurusan");
			r.add("A.id_JenisHakmilik");
			r.add("A.no_Hakmilik");
			r.add("A.no_lot");
			r.add("A.id_Lot");
			r.add("E.NOPETAK");
			r.add("E.NOBANGUNAN");
			r.add("E.NOBANGUNAN");
			r.add("E.ID_BANGUNAN");
			r.add("B.KETERANGAN");
			r.add("D.keterangan as jenis_keterangan");
			r.add("A.id_Lot",r.unquote("B.ID_LOT"));
			r.add("A.id_JenisHakmilik",r.unquote("D.id_JenisHakmilik"));
			r.add("A.id_Hakmilikurusan",r.unquote("E.id_Hakmilikurusan"));
			r.set("A.ID_PERMOHONAN", idPermohonan);
			sql = r.getSQLSelect("Tblhtphakmilikurusan A, TBLRUJLOT B,tblrujjenishakmilik D,TBLHTPBANGUNAN E");
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Bangunan bangunan = new Bangunan();
				HakmilikUrusan urusan = new HakmilikUrusan();
				urusan.setIdhakmilikurusan(rs.getString("id_Hakmilikurusan"));
				urusan.setNohakmilik(rs.getString("no_Hakmilik"));
				urusan.setNolot(rs.getString("no_lot"));
				urusan.setKeterangan(rs.getString("jenis_keterangan"));
				urusan.setKeteranganLot(rs.getString("keterangan"));
								
				bangunan.setIdBangunan(rs.getLong("ID_BANGUNAN"));
				bangunan.setHakmilikUrusan(urusan);
				bangunan.setNoPetak(rs.getString("NOPETAK"));
				bangunan.setNoBangunan(rs.getString("NOBANGUNAN"));
				bangunan.setNoTingkat(rs.getString("NOBANGUNAN"));
				
				v.addElement(bangunan);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return v;
	}
	
	public Bangunan simpanBangunan(Bangunan bangunan) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			r = new SQLRenderer();
			
			long idBangunan = DB.getNextID("TBLHTPBANGUNAN_SEQ");
			bangunan.setIdBangunan(idBangunan);
			
			r.add("ID_BANGUNAN",bangunan.getIdBangunan());
			r.add("ID_HAKMILIKURUSAN",bangunan.getHakmilikUrusan().getIdhakmilikurusan());
			r.add("NOPETAK",bangunan.getNoPetak());
			r.add("NOBANGUNAN",bangunan.getNoBangunan());
			r.add("NOTINGKAT",bangunan.getNoTingkat());
			sql = r.getSQLInsert("TBLHTPBANGUNAN");
            
            stmt.executeQuery(sql);

            conn.commit();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return bangunan;
	}
	
	public Bangunan updateBangunan(Bangunan bangunan) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			r = new SQLRenderer();
						
			r.update("ID_BANGUNAN",bangunan.getIdBangunan());
			r.add("ID_HAKMILIKURUSAN",bangunan.getHakmilikUrusan().getIdhakmilikurusan());
			r.add("NOPETAK",bangunan.getNoPetak());
			r.add("NOBANGUNAN",bangunan.getNoBangunan());
			r.add("NOTINGKAT",bangunan.getNoTingkat());
			sql = r.getSQLUpdate("TBLHTPBANGUNAN");
            
            stmt.executeQuery(sql);

            conn.commit();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return bangunan;
	}
	
	public void deleteBangunan(String idBangunan) {
		Db db = null;
		//SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			
			sql = "DELETE TBLHTPBANGUNAN WHERE ID_BANGUNAN="+idBangunan;
			stmt.executeUpdate(sql);
			
			conn.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		
	}
	
	@Override
	public void kemaskiniStatusPermohonanAktif(Tblrujsuburusanstatusfail s){
		  Db db = null;
		  String sql = "";
		  try{
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_permohonan",r.unquote(String.valueOf(s.getIdPermohonan())));
			  r.update("id_fail",r.unquote(String.valueOf(s.getIdFail())));
			  r.update("aktif", "1");
			  r.add("aktif",s.getAktif());
			  r.add("id_kemaskini", s.getIdKemaskini());
			  r.add("tarikh_kemaskini", r.unquote("sysdate")); 
			  sql = r.getSQLUpdate("Tblrujsuburusanstatusfail");
		      myLog.info("kemaskiniStatusPermohonanAktif:sql-TBLRUJSUBURUSANSTATUSFAIL::"+sql);
		      stmt.executeUpdate(sql);
		      
		  }catch(Exception ex){
			  ex.printStackTrace();
		  }finally{
			  if (db != null) db.close();
		  }		  
	  }
	  
	@Override
	public void simpanStatusPermohonan(Tblrujsuburusanstatusfail s){
		  Db db = null;
		  String sql = "";	  
		  try{
			  long IdSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");		  
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();		  
			  r.add("Id_Suburusanstatusfail", IdSuburusanstatusfail);
			  r.add("id_Permohonan", String.valueOf(s.getIdPermohonan()));
			  r.add("Id_Suburusanstatus", r.unquote(String.valueOf(s.getIdSuburusanstatus())));
			  r.add("aktif",s.getAktif());
			  r.add("id_Masuk", String.valueOf(s.getIdMasuk()));
			  r.add("tarikh_Masuk", r.unquote("sysdate"));
//			  if(!("".equals(s.getUrl())))
//				  r.add("URL", s.getUrl());
//			  else
//				  r.add("URL", "TIADA");	  
			  r.add("id_kemaskini", String.valueOf(s.getIdMasuk()));
			  r.add("tarikh_kemaskini", r.unquote("sysdate"));
			  r.add("id_fail",r.unquote(String.valueOf(s.getIdFail())));
			  sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
			  myLog.info("simpanStatusPermohonan:sql-TBLRUJSUBURUSANSTATUSFAIL::"+sql);
		      stmt.executeUpdate(sql);
	
		  }catch(Exception ex){
			  ex.printStackTrace();
			  myLog.info("simpanStatusPermohonan::ex = "+ex);
		  
		  }finally{
			  if (db != null) db.close();
		  }	
		  
	  }
	
	
	public void kemaskiniSimpanStatusPermohonanAktif(Tblrujsuburusanstatusfail s,Tblrujsuburusanstatusfail sBaru) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_permohonan",r.unquote(String.valueOf(s.getIdPermohonan())));
			r.update("id_fail",r.unquote(String.valueOf(s.getIdFail())));
			r.update("aktif", "1");
			r.add("aktif",s.getAktif());
			r.add("id_kemaskini", sBaru.getIdKemaskini());
			r.add("tarikh_kemaskini", r.unquote("sysdate")); 
			sql = r.getSQLUpdate("Tblrujsuburusanstatusfail");
			//myLog.info("kemaskiniStatusPermohonanAktif:sql-TBLRUJSUBURUSANSTATUSFAIL::"+sql);
			stmt.executeUpdate(sql);
			
			long IdSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");		  
			r = new SQLRenderer();		  
			r.add("Id_Suburusanstatusfail", IdSuburusanstatusfail);
			r.add("id_Permohonan", String.valueOf(s.getIdPermohonan()));
			r.add("Id_Suburusanstatus", r.unquote(String.valueOf(sBaru.getIdSuburusanstatus())));
			r.add("aktif",sBaru.getAktif());
			r.add("id_Masuk", String.valueOf(sBaru.getIdMasuk()));
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_kemaskini", String.valueOf(sBaru.getIdMasuk()));
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			r.add("id_fail",r.unquote(String.valueOf(s.getIdFail())));
			sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
			//myLog.info("simpanStatusPermohonan:sql-TBLRUJSUBURUSANSTATUSFAIL::"+sql);
		    stmt.executeUpdate(sql);
		    conn.commit();
	
		}catch (SQLException se) { 
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				throw new Exception("Rollback error:"+se2.getMessage());
				
			}
			throw new Exception("Ralat Pendaftaran Permohonan:"+se.getMessage());
			    
		}catch(Exception ex){
			 conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
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
	      //Tblrujkementerian f = null;
		    if (rs.next()) {
	    	 // f = new Tblrujkementerian();
	    	  //f.setKodKementerian(rs.getString("kod_kementerian"));
		    	output = rs.getString("kod_kementerian");
	      }
	      //return f.getKodKementerian();
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
	
	@Override
	public void kemaskiniFail(HtpPermohonan htpPermohonan,String idPermohonan,String idhtpPermohonan)throws Exception {
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
			myLog.info("TBLPERMOHONAN:sql="+sql);
			stmt.executeUpdate(sql);
			
			htpPermohonan.getIdHtpPermohonan();
			r = new SQLRenderer();
			r.update("id_Htppermohonan",idhtpPermohonan);
			r.update("id_Permohonan",idPermohonan);
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			myLog.info("TBLHTPPERMOHONAN:sql="+sql);
			sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
			stmt.executeUpdate(sql);
			
			
			idFail = String.valueOf(permohonan.getPfdFail().getIdFail());
			IDURUSAN = Integer.parseInt(String.valueOf(permohonan.getPfdFail().getIdUrusan())); 
			String kodUrusan = "";
			String kodMampu = "";
			r = new SQLRenderer();
			r.add("id_Urusan");
			r.add("kod_Urusan");				      
			r.add("id_Urusan",IDURUSAN);				      
			sql = r.getSQLSelect("Tblrujurusan");
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				kodUrusan = rs.getString("kod_Urusan");
			}

			r = new SQLRenderer();
			r.add("id_Negeri");
			r.add("kod_Mampu");				      
			r.add("id_Negeri",permohonan.getPfdFail().getIdNegeri());				      
			sql = r.getSQLSelect("Tblrujnegeri");
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				  kodMampu = rs.getString("kod_Mampu");
			}

			senaraiFail = new FrmSenaraiFailTerimaPohonData();
			KODKEMENTERIAN = senaraiFail.getKementerianByMampu(Integer.parseInt(String.valueOf(permohonan.getPfdFail().getIdKementerian())));
			String noFail = KOD_JABATAN+"/101/"+kodUrusan+"/"+KODKEMENTERIAN+"/"+kodMampu+"-"+ File.getSeqNo(IDSEKSYEN, IDURUSAN,Integer.parseInt(String.valueOf(permohonan.getPfdFail().getIdKementerian())),Integer.parseInt(String.valueOf(permohonan.getPfdFail().getIdNegeri()))) ;
			
			r = new SQLRenderer();
			r.update("ID_FAIL",idFail);
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("sysdate"));
			r.add("NO_FAIL", noFail);
			r.add("NO_FAIL_ROOT",noFail);
			r.add("ID_KEMASKINI", permohonan.getIdMasuk());
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("TBLPFDFAIL");
			myLog.info("TBLPFDFAIL:sql="+sql);
			stmt.executeUpdate(sql);		
			conn.commit();
			
	}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		//return htpPermohonan;
	}
	
	@Override
	public Vector<Hashtable<String,String>> senaraiFailPemajak(String idPemohon) throws Exception {
		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		Vector<Hashtable<String,String>> senaraiFail = null; 
		try {
			senaraiFail = new Vector<Hashtable<String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.TAJUK_FAIL,A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL, B.TARIKH_TERIMA, C.KETERANGAN,RN.NAMA_NEGERI"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLRUJSTATUS C, TBLHTPPEMOHON D,TBLRUJNEGERI RN,TBLHTPJEMAAHMENTERI JM "
				+ " WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_STATUS = C.ID_STATUS AND B.ID_PERMOHONAN = D.ID_PERMOHONAN(+) " 
				+ " AND A.ID_NEGERI = RN.ID_NEGERI AND A.ID_URUSAN = '3' " 
				+ " AND B.ID_PERMOHONAN = JM.ID_PERMOHONAN AND JM.STATUS_KEPUTUSAN ='L'"
				+ " AND D.NO_PEMOHON = '"+idPemohon+"'";
			
			//Nama Permohon
//			if (namaPemohon != null) {
//				if (!namaPemohon.trim().equals("")) {
//					sql = sql + " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
//							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
//				}
//			}
			
			//log.info("sql carian fail : sql=" + sql);			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
		    	h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
			    h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
		    	h.put("negeri", rs.getString("NAMA_NEGERI"));
		    	senaraiFail.addElement(h);
				bil++;
			}
			
			return senaraiFail;
			
		} finally {
			if (db != null)
				db.close();
		}
	}	

	public String addMasuk(Hashtable<String,String> data)throws Exception {		
		Db db = null;
	    String sql = "";
	    try {	 
	    	  long idDokumen = DB.getNextID("TBLPFDDOKUMEN_SEQ");
	    	  String flagDokumen = (String)data.get("flag_Dokumen");
		      //String bilMinitDokumen = (String)data.get("bil_Minit_Dokumen");
		      String idJenisdokumen = getIdDokumenByKod("");
		      //String idMinit = (String)data.get("idMinit");
		      //String idLaporan = (String)data.get("idLaporan");
		      //String idCD = (String)data.get("idCD");
		      //String noRujDok = (String)data.get("no_Rujukan_Dokumen");
		      String tajukDok = (String)data.get("tajuk_Dokumen");
		      
		      //String tkhDokDiterima = (String)data.get("tarikh_Dokumen_Diterima");
		      //String tarikhDokDiterima = "to_date('" + tkhDokDiterima + "','dd/MM/yyyy')";
		      //String tkhDok = (String)data.get("tarikh_Dokumen");
		      //String tarikhDok = "to_date('" + tkhDok + "','dd/MM/yyyy')";
		      String namaPengirim = (String)data.get("namaPengirim");
		      //String idNamaPenerima = (String)data.get("id_nama_penerima");
		      //String id_PA = (String)data.get("id_PA");
		      String idFail = (String)data.get("id_Fail");
		      //String idLogDokumen = (String)data.get("idLogDokumen");
		      String idMasuk = (String)data.get("id_Masuk");
		       
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer(); 
		      r.add("id_Dokumen",idDokumen);
		      r.add("id_Fail",idFail);
		      //r.add("id_LogDokumen",idLogDokumen);
		      //r.add("id_Minit",idMinit);
		      //r.add("id_Laporan",idLaporan);
		      //r.add("cd",idCD);
		      r.add("flag_Dokumen", flagDokumen);
		      //r.add("bil_Minit_Dokumen", bilMinitDokumen);
		      r.add("id_Jenisdokumen", idJenisdokumen);
		      r.add("tajuk_Dokumen",tajukDok);
		      //r.add("no_Rujukan_Dokumen", noRujDok);
		      //r.add("no_Rujukan_Lain", noRujLain);
		      //r.add("tarikh_Dokumen_Diterima", r.unquote(tarikhDokDiterima));
		      //r.add("tarikh_Dokumen", r.unquote(tarikhDok));
		      r.add("nama_Pengirim", namaPengirim);
		      //r.add("id_NamaPenerima", idNamaPenerima);
		      //r.add("ID_SETIAUSAHA", id_PA);
		      //r.add("STATUS_MINIT_PENGARAH","1");
		      r.add("tarikh_Masuk",r.unquote("sysdate")); 
		      r.add("id_Masuk",idMasuk);	      
		      sql = r.getSQLInsert("tblpfddokumen"); 
		      myLog.info(sql);
		      stmt.executeUpdate(sql);
 		      return String.valueOf(idDokumen);
 		      
		    } finally {
		      if (db != null) db.close();
		    }

	}	
	
	public Vector<Hashtable<String,String>> getDataDokumen(String idPermohonan)	throws Exception {		
		 Db db = null;
		 String sql = "";
		 //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 //Date now = new Date();
		 Vector<Hashtable<String,String>> paparDokumen = new Vector<Hashtable<String,String>>();
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();		      
		      r.add("a.Id_Dokumen");
		      r.add("a.Nama_Pengirim");
		      r.add("a.Id_Jenisdokumen");
		      r.add("a.Tajuk_Dokumen");
		      r.add("P.ID_FAIL",r.unquote("A.ID_FAIL"));
		      r.add("P.ID_PERMOHONAN",idPermohonan);
		     		    
		      sql = r.getSQLSelect("Tblpfddokumen a,tblpermohonan p");
		      myLog.info(sql);
		      ResultSet rs = stmt.executeQuery(sql);		     
		      Hashtable<String,String> h = new Hashtable<String,String>();
		      while (rs.next()) {
		    	  h.put("idDokumen",rs.getString("id_Dokumen"));
		    	  h.put("perihal", rs.getString("Nama_Pengirim"));	    	  
		    	  h.put("keterangan", rs.getString("Tajuk_Dokumen"));	    	  
		    	  paparDokumen.addElement(h); 
		    	  
		      }
		 }finally {
			 if (db != null) db.close();
		 }  
		 return paparDokumen;
	
	}
	
	public String getIdDokumenByKod(String idnegeri) throws Exception {
	    Db db = null;
	    String output = "";
	    String sql = "SELECT ID_JENISDOKUMEN " +
	    		" FROM TBLRUJJENISDOKUMEN WHERE KOD_JENIS_DOKUMEN ='000'";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      ResultSet rs = stmt.executeQuery(sql);
	      if (rs.next()) {
	    	  output = Utils.isNull(rs.getString("ID_JENISDOKUMEN"));
	      }
		}catch(Exception e){
			//e.printStackTrace();
			getIHTP().getErrorHTML(e.toString());
		} finally {
	      if (db != null) db.close();
	    }
	    return output;
	    
	}
	
	 public Vector<Hashtable<String, String>> getLampiranByPermohonan(String id)throws Exception {
		 Db db = null;
		 String sql = "";
		 Vector<Hashtable<String, String>> listLampiran = new Vector<Hashtable<String, String>>();
		 	try {
		 		db = new Db();
		 		Statement stmt = db.getStatement();
		 		SQLRenderer r = new SQLRenderer();
		 		r.add("a.id_Lampiran");
		 		r.add("a.nama_Fail");
		 		r.add("a.jenis_Mime");
		 		r.add("FDD.Id_Dokumen");
			    r.add("FDD.Nama_Pengirim");
			    r.add("FDD.Tajuk_Dokumen");
		        //r.add("a.id_Dokumen",id);
		 		r.add("A.ID_DOKUMEN",r.unquote("FDD.ID_DOKUMEN"));
		 		r.add("P.ID_FAIL",r.unquote("FDD.ID_FAIL"));
		 		r.add("P.ID_PERMOHONAN",id);
		        sql = r.getSQLSelect("Tblpfdrujlampiran a,Tblpfddokumen FDD,tblpermohonan p", "a.id_Lampiran asc");
		        myLog.info(sql);
		        ResultSet rs = stmt.executeQuery(sql);
		        Hashtable<String, String> h;
		        int bil = 1;
		        //int count = 0;
		        while (rs.next()) {
		    	  h = new Hashtable<String, String>();
		    	  h.put("bil",String.valueOf(bil));
		    	  h.put("idDokumen",rs.getString("Id_Dokumen"));
		    	  h.put("perihal", rs.getString("Nama_Pengirim"));	    	  
		    	  h.put("keterangan", Utils.isNull(rs.getString("Tajuk_Dokumen")));
		    	  h.put("idLampiran", rs.getString("id_Lampiran"));
		    	  h.put("namaFail",rs.getString("nama_Fail")== null?"":rs.getString("nama_Fail"));
		    	  h.put("jenis_Mime", rs.getString("jenis_Mime")== null?"":rs.getString("jenis_Mime"));
		    	  listLampiran.addElement(h);
		    	  bil++;
		    	  //count++;
		      }
//		      if (count == 0){
//		    	  h = new Hashtable();
//		    	  h.put("bil","");
//		    	  h.put("id_Lampiran", "");
//		    	  h.put("nama_Fail","Tiada rekod.");
//		    	  h.put("jenis_Mime", "");
//		    	  listLampiran.addElement(h);
//		      }
		    } finally {
		      if (db != null) db.close();
		    }
		    return listLampiran;
		    
		}
	 
	 public Vector<Hashtable<String,String>> getLampiranByDokumen(String id)throws Exception {
		 Db db = null;
		 String sql = "";
		 Vector<Hashtable<String,String>> listLampiran = new Vector<Hashtable<String,String>>();
		 	try {
		 		db = new Db();
		 		Statement stmt = db.getStatement();
		 		SQLRenderer r = new SQLRenderer();
		 		r.add("a.id_Lampiran");
		 		r.add("a.nama_Fail");
		 		r.add("a.jenis_Mime");
		 		r.add("FDD.Id_Dokumen");
			    r.add("FDD.Nama_Pengirim");
			    r.add("FDD.Tajuk_Dokumen");
		        //r.add("a.id_Dokumen",id);
		 		r.add("A.ID_DOKUMEN",r.unquote("FDD.ID_DOKUMEN"));
		 		r.add("P.ID_FAIL",r.unquote("FDD.ID_FAIL"));
		 		r.add("A.ID_LAMPIRAN",id);
		        sql = r.getSQLSelect("Tblpfdrujlampiran a,Tblpfddokumen FDD,tblpermohonan p");
		        ResultSet rs = stmt.executeQuery(sql);
		        Hashtable<String,String> h;
		        int bil = 1;
		        //int count = 0;
		        while (rs.next()) {
		    	  h = new Hashtable<String,String>();
		    	  h.put("bil",String.valueOf(bil));
		    	  h.put("idDokumen",rs.getString("Id_Dokumen"));
		    	  h.put("perihal", rs.getString("Nama_Pengirim"));	    	  
		    	  h.put("keterangan", Utils.isNull(rs.getString("Tajuk_Dokumen")));
		    	  h.put("idLampiran", rs.getString("id_Lampiran"));
		    	  h.put("namaFail",rs.getString("nama_Fail")== null?"":rs.getString("nama_Fail"));
		    	  h.put("jenis_Mime", rs.getString("jenis_Mime")== null?"":rs.getString("jenis_Mime"));
		    	  listLampiran.addElement(h);
		    	  bil++;
		    	  //count++;
		      }

		    } finally {
		      if (db != null) db.close();
		    }
		    return listLampiran;
		    
		}
	 
		
//	private HakmilikInterface getHakmilikBean(){
//		if(hakmilikInterface == null){
//			hakmilikInterface = new HakmilikBean();
//		}
//		return hakmilikInterface;
//	
//	}
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}
	
	public void hapusLampiran(String idDokumen,String idLampiran) throws Exception {
		myLog.info("hapusLampiran");
		Db db = null;
		Connection conn = null;
		String sql = "";
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			//Tblpfdrujlampiran a,Tblpfddokumen			
			//TBLHTPGAMBAR
			r.add("ID_DOKUMEN", idDokumen);
			sql = r.getSQLDelete("Tblpfddokumen");
			myLog.info("hapusLampiran:Tblpfddokumen::sql="+sql);
			stmt.executeUpdate(sql);
			
			r = new SQLRenderer();
			r.add("ID_LAMPIRAN", idLampiran);
			sql = r.getSQLDelete("Tblpfdrujlampiran");
			myLog.info("hapusLampiran:Tblpfdrujlampiran::sql="+sql);
			stmt.executeUpdate(sql);			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}	
		
	}
	
	@Override
	public boolean isHantar(Long idSubUrusan,Long idPermohonan
			,Long idFail,String langkah) throws Exception {
		  boolean returnValue = false;
		  Long setIdSuburusanstatus = 0L;
		  setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(
				  langkah,String.valueOf(idSubUrusan),"=");		  
		  returnValue = FrmUtilData.getIdSuburusanStatusFail(String.valueOf(idFail)
				  ,String.valueOf(idPermohonan),String.valueOf(setIdSuburusanstatus));
		  myLog.info("returnValue="+returnValue);
		  return returnValue;
		  
	 }
	
	@Override
	public boolean isHantarAktif(Long idSubUrusan,Long idPermohonan
			,Long idFail,String langkah) throws Exception {
		  boolean returnValue = false;
		  Long setIdSuburusanstatus = 0L;
		  setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(
				  langkah,String.valueOf(idSubUrusan),"=");		  
		  returnValue = getIdSuburusanStatusFail(String.valueOf(idFail)
				  ,String.valueOf(idPermohonan),String.valueOf(setIdSuburusanstatus));
		  myLog.info("returnValue="+returnValue);
		  return returnValue;
		  
	 }
	
	public boolean getIdSuburusanStatusFail(String idfail,String idpermohonan,String id) throws Exception {
		Db db = null;
		String sql = "";
		boolean returnValue = false;		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("rsusf.id_suburusanstatus");
		    r.add("rsusf.aktif","1");
	        r.add("rsusf.id_fail",r.unquote( idfail));
	        r.add("rsusf.id_suburusanstatus",r.unquote(id));
	        r.add("rsusf.id_permohonan",r.unquote(idpermohonan));
	           
	    sql = r.getSQLSelect("tblrujsuburusanstatusfail rsusf");
	    
	    myLog.info("getIdStatusFailPermohonanTerkini:sql::"+sql);
	    ResultSet rs = stmt.executeQuery(sql);
	    while (rs.next()) { 
	    	returnValue = true;
	    }
	      
	  }catch(Exception ex){
		  myLog.info("FrmUtilData:getIdSuburusanStatusFail::ex = "+ex);
	  }finally{
		  if (db != null) db.close();
	  }
	return returnValue;		  
}
	
	@Override
	public String kemaskiniDokumen(Hashtable<String,String> data)throws Exception {		
		Db db = null;
	    String sql = "";
	    try {	 
	    	  long idDokumen = Long.parseLong(String.valueOf(data.get("idDokumen")));
		      String tajukDok = (String)data.get("tajuk_Dokumen");
		      String namaPengirim = (String)data.get("namaPengirim");
		      String idMasuk = (String)data.get("idKemaskini");		       
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer(); 
			  r.update("ID_DOKUMEN",idDokumen);
		      r.add("TAJUK_DOKUMEN",tajukDok);
		      r.add("NAMA_PENGIRIM", namaPengirim);
		      r.add("TARIKH_KEMASKINI",r.unquote("sysdate")); 
		      r.add("ID_KEMASKINI",idMasuk);	      
			  sql = r.getSQLUpdate("TBLPFDDOKUMEN");
		      myLog.info(sql);
		      stmt.executeUpdate(sql);
 		      return String.valueOf(idDokumen);
 		      
		    } finally {
		      if (db != null) db.close();
		      
		    }

	}	
	
	
}
