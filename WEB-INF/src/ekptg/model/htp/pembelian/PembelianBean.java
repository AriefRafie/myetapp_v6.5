package ekptg.model.htp.pembelian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.File;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmPembelianSemakanData;
import ekptg.model.htp.FrmSenaraiFailTerimaPohonData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.PihakBerkepentingan;
import ekptg.model.htp.entity.Bangunan;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Pemohon;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;

public class PembelianBean implements IPembelian {
	static Logger myLog = Logger.getLogger(ekptg.model.htp.pembelian.PembelianBean.class);
	private static final String KOD_JABATAN ="JKPTG";
	private static String KODKEMENTERIAN ="00";
	private static final long IDTARAFKESELAMTAN = 1;
	private static final int IDSEKSYEN = 3;
	private static final int IDURUSAN = 2;
	PfdFail fail = null;
	Permohonan permohonan = null;
	HtpPermohonan htpPermohonan = null;
	private FrmSenaraiFailTerimaPohonData senaraiFail = null;

	public HtpPermohonan simpanPermohonan(HtpPermohonan htpPermohonan)throws Exception{
		Db db = null;
		Connection conn = null;
		Permohonan permohonan = htpPermohonan.getPermohonan();
		SQLRenderer r = null;
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
				  //kodMampu = rs.getString("kod_Mampu");
				  kodMampu = String.valueOf(rs.getInt("kod_Mampu"));
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
			myLog.info("===idSubUrusan" +pfdFail.getIdSubUrusan());
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
		Connection conn = null;
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
			/**	
			 * -4 untuk status - Pendaftaran
			 * -3 untuk status - Tindakan Pegawai 
			 * -2 untuk status - Tindakan Pengarah
			 * -1 untuk status Permohonan Online (Pengesahan)
			 * */
			//setIdStatus = FrmUtilData.getIdStatusByLangkah("-1",String.valueOf(pfdFail.getIdSubUrusan()),"=");
			// Dikemaskini oleh Mohamad Rosli pada 08/12/2011, tukar -1 kepada -2 
			// Dikemaskini oleh Mohamad Rosli pada 09/02/2012, tukar -2 kepada -4 
			setIdStatus = FrmUtilData.getIdStatusByLangkah("-4",String.valueOf(pfdFail.getIdSubUrusan()),"=");
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
		
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if(db != null)	db.close();
		}
		return htpPermohonan;
		
	}
	
	@Override
	public HtpPermohonan findPermohonan(String idPermohonan,String idHtpPermohonan) throws Exception {
		Db db = null;
		Connection conn = null;
		//SQLRenderer r = null;
		String sql = "";
		
		Date now = new Date();
		 SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		 String tarikhDaftarFail = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			sql = "select A.id_Permohonan,B.ID_HTPPERMOHONAN,C.ID_FAIL,C.TAJUK_FAIL,C.ID_KEMENTERIAN,B.ID_AGENSI,C.ID_URUSAN,C.NO_FAIL, E.ID_SUBURUSAN, ";
				sql += " TO_CHAR(A.TARIKH_SURAT,'DD/MM/YYYY') TARIKH_SURAT,TO_CHAR(A.TARIKH_TERIMA,'DD/MM/YYYY') AS TARIKH_TERIMA,A.TUJUAN, ";
					sql += " NVL(B.ID_JENISTANAH,0) ID_JENISTANAH,B.ID_PEGAWAI,B.NO_RUJUKAN_KJP,B.NO_RUJUKAN_LAIN,B.TARIKH_AGIHAN,B.ID_DAERAH,";
						sql += " C.ID_NEGERI,C.ID_STATUS, D.TARAF_KESELAMATAN,D.ID_TARAFKESELAMATAN,F.NAMA_NEGERI,TO_CHAR(C.TARIKH_DAFTAR_FAIL,'DD/MM/YYYY') AS OPEN_FILE_DATE, ";
						sql +=" G.NAMA_KEMENTERIAN, H.NAMA_AGENSI,E.NAMA_SUBURUSAN,E.KOD_SUBURUSAN,A.NO_PERMOHONAN,C.ID_MASUK";
							sql += " from TBLPERMOHONAN A, TBLHTPPERMOHONAN B , TBLPFDFAIL C , TBLpfdrujtarafkeselamatan D, tblrujsuburusan E,TBLRUJNEGERI F,TBLRUJKEMENTERIAN G,TBLRUJAGENSI H ";
								sql += " where A.ID_PERMOHONAN=B.ID_PERMOHONAN ";
									sql += " And C.ID_SUBURUSAN = E.ID_SUBURUSAN ";
										sql += " AND D.ID_TARAFKESELAMATAN(+) = C.ID_TARAFKESELAMATAN ";
											sql += " AND C.ID_FAIL = A.ID_FAIL ";
												sql += " AND A.ID_PERMOHONAN = '"+ idPermohonan +"'";
												sql += " AND F.ID_NEGERI = C.ID_NEGERI";
												sql += " AND G.ID_KEMENTERIAN = C.ID_KEMENTERIAN";
												sql += " AND B.ID_AGENSI = H.ID_AGENSI";
											if(!idHtpPermohonan.equals("")){
												sql += " AND B.ID_HTPPERMOHONAN = '"+ idHtpPermohonan +"'";
											}
											sql +=" ORDER BY A.id_Permohonan desc";
											myLog.info("Permohonan:::findPermohonan::sql="+sql);
											
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
										 			htpPermohonan.setIdJenisTanah(rs.getString("ID_JENISTANAH"));
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
										 			fail.setIdTarafKeselamatan(rs.getString("ID_TARAFKESELAMATAN")==null?"0":rs.getString("ID_TARAFKESELAMATAN"));
										 			fail.setTarikhDaftarFail(rs.getString("OPEN_FILE_DATE"));
										 			fail.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));
										 			fail.setNamaSuburusan(rs.getString("NAMA_SUBURUSAN"));
										 			fail.setKodSuburusan(rs.getString("KOD_SUBURUSAN"));
										 			fail.setTajukFail(rs.getString("TAJUK_FAIL"));
										 			fail.setIdMasuk(rs.getLong("ID_MASUK"));
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
			myLog.info("TBLPERMOHONAN:sql="+sql);
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
			//
			myLog.info("TBLHTPPERMOHONAN:sql="+sql);
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
			
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
			if(db != null)	db.close();
		}
		return htpPermohonan;
		
	}

	@Override
	public Vector findFail(String carian, String noFail, String idNegeri) {
		Db db = null;
		Vector<HtpPermohonan> v = new Vector();
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "SELECT DISTINCT f.id_Fail,NVL(LTRIM(RTRIM(f.no_Fail)),'TIADA') NO_FAIL, f.tajuk_Fail" +
				" ,p.id_Permohonan,p.TUJUAN,P.NO_PERMOHONAN " +
				" ,h.id_htppermohonan, n.nama_Negeri, n.kod_Mampu,n.id_Negeri,s.keterangan "+
	            " ,CASE " +
	            " 	WHEN TANAH.HAKMILIK != 0 THEN ''||TANAH.HAKMILIK||' Hakmilik/Warta' " +
	            " 	ELSE '' " +
	            " END BIL_TANAH" +				
				" FROM Tblpfdfail f, Tblpermohonan p, Tblrujsuburusanstatusfail sf" +
				" ,Tblrujsuburusanstatus ss, Tblrujstatus s, Tblrujnegeri n, tblhtppermohonan h "+
	            " , (	    SELECT TPH.ID_PERMOHONAN, COUNT(*) HAKMILIK FROM TBLPERMOHONAN PPI, TBLHTPHAKMILIK TPH " +
	            " 	    WHERE TPH.ID_PERMOHONAN = PPI.ID_PERMOHONAN " +
	            " 	    GROUP BY TPH.ID_PERMOHONAN " +
	            " 	) TANAH " +				
				" WHERE f.id_Fail = p.id_Fail AND h.id_Permohonan = p.id_Permohonan  " +
				" AND p.id_Permohonan = sf.id_Permohonan " +
	            " AND P.ID_PERMOHONAN = TANAH.ID_PERMOHONAN(+)" +

	            " AND p.id_Fail = sf.id_Fail " +
				" AND n.id_Negeri = f.id_Negeri ";
				sql +="AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status ";
				sql +="AND sf.aktif = '1' AND f.id_Urusan = 2 ";
				sql +="AND f.ID_STATUS <> 999 ";
				sql +="AND f.no_Fail LIKE '%"+noFail+"%' ";
				sql +="AND f.tajuk_Fail LIKE '%"+carian+"%' ";
			
				if(idNegeri != null && !idNegeri.equals("") && !idNegeri.equals("0"))
					sql +=" AND f.id_Negeri = "+idNegeri;
			
				sql +=" order by p.id_Permohonan desc";
				myLog.info("findFail : "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					permohonan = new Permohonan();
					fail = new PfdFail();
					htpPermohonan = new HtpPermohonan();
					
					fail.setIdFail(rs.getLong("id_Fail"));
					permohonan.setNamaNegeri(rs.getString("nama_Negeri"));
					permohonan.setIdPermohonan(rs.getLong("id_Permohonan"));
					permohonan.setNoPermohonan(rs.getString("NO_PERMOHONAN"));
					fail.setNoFail(rs.getString("no_Fail"));
					permohonan.setTujuan(rs.getString("TUJUAN"));
					//permohonan.setTujuan(rs.getString("tajuk_Fail"));
					htpPermohonan.setIdHtpPermohonan(rs.getString("id_htppermohonan"));
					htpPermohonan.setPermohonan(permohonan);
					htpPermohonan.setStatusPermohonan(rs.getString("keterangan"));
					permohonan.setPfdFail(fail);
			    	permohonan.setKeterangan(rs.getString("BIL_TANAH"));
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
	public Vector findFailKJP(String carian, String noFail, String idNegeri,String idKementerian) {
		Db db = null;
		Vector<HtpPermohonan> v = new Vector();
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "" +
				"SELECT distinct F.ID_FAIL,NVL(LTRIM(RTRIM(f.no_Fail)),'TIADA') NO_FAIL" +
				" ,F.TAJUK_FAIL,P.ID_PERMOHONAN,P.TUJUAN,P.NO_PERMOHONAN " +
				" ,H.ID_HTPPERMOHONAN, n.nama_Negeri, n.kod_Mampu,n.id_Negeri,s.keterangan " +
				" ,F.TARIKH_KEMASKINI,F.TARIKH_MASUK "+
				" FROM Tblpfdfail F, Tblpermohonan P, Tblrujsuburusanstatusfail SF " +
				" ,Tblrujsuburusanstatus SS, Tblrujstatus S, Tblrujnegeri N, tblhtppermohonan H "+
				" WHERE F.id_Fail = P.id_Fail AND H.id_Permohonan = P.id_Permohonan  " +
				" AND P.id_Permohonan = SF.id_Permohonan AND P.id_Fail = SF.id_Fail" +
				" AND N.id_Negeri = F.id_Negeri AND sf.aktif = '1' AND f.id_Urusan = 2 " +
				" AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status " +
				" AND f.ID_STATUS <> 999 " +
				" AND f.no_Fail LIKE '%"+noFail+"%' " +
				" AND f.tajuk_Fail LIKE '%"+carian+"%' "+
				"";
			if(idKementerian != null && !idKementerian.equals("") && !idKementerian.equals("0"))
				sql +=" AND F.ID_KEMENTERIAN = "+idKementerian;
			if(idNegeri != null && !idNegeri.equals("") && !idNegeri.equals("0"))
				sql +=" AND F.ID_NEGERI = "+idNegeri;
			
			//sql +=" ORDER BY F.TARIKH_KEMASKINI desc,F.ID_FAIL DESC";
			sql +=" ORDER BY F.TARIKH_KEMASKINI DESC,F.TARIKH_MASUK DESC,F.ID_FAIL DESC";
			//myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				permohonan = new Permohonan();
				fail = new PfdFail();
				htpPermohonan = new HtpPermohonan();
				
				fail.setIdFail(rs.getLong("id_Fail"));
				permohonan.setNamaNegeri(rs.getString("nama_Negeri"));
				permohonan.setIdPermohonan(rs.getLong("id_Permohonan"));
				permohonan.setNoPermohonan(rs.getString("NO_PERMOHONAN"));
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
	public Vector findFailOnline(String carian, String noFail, String idNegeri,String idKementerian) {
		Db db = null;
		Vector<HtpPermohonan> v = new Vector();
		try{
			db = new Db();
			Statement stmt = db.getStatement();


			String sql = "SELECT distinct p.NO_PERMOHONAN,p.TUJUAN, f.id_Fail, p.id_Permohonan, f.no_Fail, f.tajuk_Fail, s.keterangan, n.nama_Negeri, n.kod_Mampu,n.id_Negeri, h.id_htppermohonan ";
			sql +="FROM Tblpfdfail f, Tblpermohonan p, Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, Tblrujstatus s, Tblrujnegeri n, tblhtppermohonan h ";
			sql +="WHERE f.id_Fail = p.id_Fail AND p.id_Permohonan = sf.id_Permohonan AND n.id_Negeri = f.id_Negeri AND h.id_Permohonan = p.id_Permohonan  ";
			sql +="AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status ";
			sql +="AND sf.aktif = '1' AND f.id_Urusan = 2 AND f.tajuk_Fail LIKE '%"+carian+"%' ";
			sql +="AND f.ID_STATUS <> 999 ";
			sql+="AND nvl(no_fail,' ') = ' ' ";
			//sql +="AND f.no_Fail LIKE '%"+noFail+"%' ";
			sql +="AND p.NO_PERMOHONAN LIKE '%"+noFail.trim()+"%' ";
			if(idNegeri != null && !idNegeri.equals("") && !idNegeri.equals("0"))
	    	  sql +="AND f.id_Negeri = "+idNegeri;
			if(idKementerian != null && !idKementerian.equals("") && !idKementerian.equals("0"))
		    	  sql +="AND f.ID_KEMENTERIAN = "+idKementerian;
			//sql +=" ORDER BY n.kod_Mampu";
			sql +=" order by p.id_Permohonan desc";
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				permohonan = new Permohonan();
				fail = new PfdFail();
				htpPermohonan = new HtpPermohonan();
				
				fail.setIdFail(rs.getLong("id_Fail"));
				permohonan.setNamaNegeri(rs.getString("nama_Negeri"));
				permohonan.setIdPermohonan(rs.getLong("id_Permohonan"));
				permohonan.setNoPermohonan(rs.getString("NO_PERMOHONAN"));
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
	public Vector findFailOnline2(String carian, String noFail, String idNegeri,String idKementerian) {
		Db db = null;
		Vector<HtpPermohonan> v = new Vector();
		try{
			db = new Db();
			Statement stmt = db.getStatement();


			String sql = "SELECT distinct p.NO_PERMOHONAN,p.TUJUAN, f.id_Fail, p.id_Permohonan" +
					" ,NVL(LTRIM(RTRIM(f.no_Fail)),'TIADA') NO_FAIL, f.tajuk_Fail, s.keterangan" +
					" ,n.nama_Negeri, n.kod_Mampu,n.id_Negeri, h.id_htppermohonan " +
					" ,F.TARIKH_KEMASKINI ,F.TARIKH_MASUK ";
			sql +=" FROM Tblpfdfail f, Tblpermohonan p, Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, Tblrujstatus s, Tblrujnegeri n, tblhtppermohonan h ";
			sql +=" WHERE f.id_Fail = p.id_Fail AND p.id_Permohonan = sf.id_Permohonan AND n.id_Negeri = f.id_Negeri AND h.id_Permohonan = p.id_Permohonan  ";
			sql +=" AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status ";
			sql +=" AND sf.aktif = '1' AND f.id_Urusan = 2 AND f.tajuk_Fail LIKE '%"+carian+"%' ";
			sql +=" AND f.ID_STATUS <> 999 ";
			//sql+=" AND not nvl(no_fail,' ') = ' ' ";
			//sql +="AND f.no_Fail LIKE '%"+noFail+"%' ";
			sql +="AND P.NO_PERMOHONAN LIKE '%"+noFail.trim()+"%' ";
			if(idNegeri != null && !idNegeri.equals("") && !idNegeri.equals("0"))
	    	  sql +="AND f.id_Negeri = "+idNegeri;
			if(idKementerian != null && !idKementerian.equals("") && !idKementerian.equals("0"))
		    	  sql +="AND f.ID_KEMENTERIAN = "+idKementerian;
			//sql +=" ORDER BY n.kod_Mampu";
			sql +=" ORDER BY F.TARIKH_KEMASKINI DESC,F.TARIKH_MASUK DESC,F.ID_FAIL DESC";
			
			ResultSet rs = stmt.executeQuery(sql);
		      int bil = 1;
			while(rs.next()){
				permohonan = new Permohonan();
				fail = new PfdFail();
				htpPermohonan = new HtpPermohonan();
				
				fail.setIdFail(rs.getLong("id_Fail"));
				fail.setBil(bil);
				permohonan.setNamaNegeri(rs.getString("nama_Negeri"));
				permohonan.setIdPermohonan(rs.getLong("id_Permohonan"));
				permohonan.setNoPermohonan(rs.getString("NO_PERMOHONAN"));
				fail.setNoFail(rs.getString("no_Fail"));
				permohonan.setTujuan(rs.getString("TUJUAN"));
				//permohonan.setTujuan(rs.getString("tajuk_Fail"));
				htpPermohonan.setIdHtpPermohonan(rs.getString("id_htppermohonan"));
				htpPermohonan.setPermohonan(permohonan);
				htpPermohonan.setStatusPermohonan(rs.getString("keterangan"));
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);				
				v.addElement(htpPermohonan);
				bil++;
			
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
	public HakmilikUrusan simpanHakmilik(HakmilikUrusan urusan) {
		Db db = null;
		Connection conn = null;
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
		Connection conn = null;
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
		Connection conn = null;
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
		Connection conn = null;
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
			r.add("(SELECT NAMA_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI=A.id_Negeri) NAMA_NEGERI");
			r.add("A.id_Daerah");
			r.add("(SELECT NAMA_DAERAH FROM TBLRUJDAERAH WHERE ID_DAERAH=A.id_Daerah) NAMA_DAERAH");
			r.add("A.id_Mukim");
			r.add("(SELECT NAMA_MUKIM FROM TBLRUJMUKIM WHERE ID_MUKIM=A.id_Mukim) NAMA_MUKIM");
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
			r.add("(SELECT NAMA FROM TBLHTPPIHAKBERKEPENTINGAN WHERE ID_HAKMILIKURUSAN=A.id_Hakmilikurusan) NAMA");
			r.add("A.id_Lot",r.unquote("B.ID_LOT"));
			r.add("A.id_luas",r.unquote("C.id_luas"));
			r.add("A.id_JenisHakmilik",r.unquote("D.id_JenisHakmilik"));
			r.set("A.id_Permohonan", idPermohonan);
			String sql = r.getSQLSelect("Tblhtphakmilikurusan A, TBLRUJLOT B,TBLRUJLUAS C,tblrujjenishakmilik D");
			//
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				HakmilikUrusan urusan = new HakmilikUrusan();
				Permohonan permohonan = new Permohonan();
				urusan.setIdhakmilikurusan(rs.getString("id_Hakmilikurusan"));
				urusan.setPeganganHakmilik(rs.getString("pegangan_Hakmilik"));
				urusan.setIdNegeri(rs.getString("id_Negeri"));
				urusan.setNamanegeri(rs.getString("nama_Negeri"));
				urusan.setIdDaerah(rs.getString("id_Daerah"));
				urusan.setNamadaerah(rs.getString("nama_Daerah"));
				urusan.setIdMukim(rs.getString("id_Mukim"));
				urusan.setNamamukim(rs.getString("nama_Mukim"));
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
				PihakBerkepentingan pb = new PihakBerkepentingan();
				pb.setNama(rs.getString("nama"));
				urusan.addPihakBerkepentingan(pb);
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
		Connection conn = null;
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
			myLog.info(sql); 
	        stmt.executeUpdate(sql);	            
	        conn.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
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
		Connection conn = null;
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

	public void hapusPenjual(Pemohon pemohon) {
		Db db = null;
		Connection conn = null;
		String sql = null;
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
        	sql = "DELETE TBLHTPPEMOHON WHERE ID_PEMOHON="+pemohon.getIdPemohon();		
           //myLog.info(sql);
            stmt.executeQuery(sql);
            conn.commit();
            
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
			if(db != null)	db.close();
		}
		
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
	
	public Vector findBangunanByPermohonan(String idPermohonan) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		Vector v = new Vector();
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
		Connection conn = null;
		SQLRenderer r = null;
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

	
}
