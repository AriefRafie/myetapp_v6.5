package ekptg.model.htp.penswastaan;

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
import ekptg.helpers.DB;
import ekptg.helpers.File;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmPembelianSemakanData;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.entity.Bangunan;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Pemohon;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;

public class PenswastaanBean implements IPenswastaan {
	private static final String KOD_JABATAN ="JKPTG";
	private static final long IDTARAFKESELAMTAN = 1;
	private static final int IDSEKSYEN = 3;
	private static final int IDURUSAN = 2;
	PfdFail fail = null;
	Permohonan permohonan = null;
	HtpPermohonan htpPermohonan = null;
	public Hashtable h = null;
	public Vector vecPemohonReturn = null;

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
		
		 Date now = new Date();
		 SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		 String tarikhDaftarFail = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
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
			
			String noFail = KOD_JABATAN+"/101/"+kodUrusan+"/"+pfdFail.getIdKementerian()+"/"+kodMampu+"-"+ File.getSeqNo(IDSEKSYEN, IDURUSAN,Integer.parseInt(String.valueOf(pfdFail.getIdNegeri())),IDURUSAN,Integer.parseInt(String.valueOf(pfdFail.getIdNegeri()))) ;
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
			r.add("tarikh_Daftar_Fail", r.unquote(tarikhDaftarFail));
			r.add("flag_Fail", 1);
			r.add("tajuk_Fail", permohonan.getTujuan());
			r.add("no_Fail", noFail);
			r.add("no_Fail_Root", "TIADA");
		
			r.add("id_lokasifail", 1);
			r.add("id_Negeri", pfdFail.getIdNegeri());
			r.add("id_Kementerian", pfdFail.getIdKementerian());
			r.add("id_Status", 7);
			r.add("id_faharasat", 1);
			r.add("id_Masuk", idFail);
			r.add("tarikh_Masuk", r.unquote(tarikhDaftarFail));
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote(tarikhDaftarFail));
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
			r.add("tarikh_Agihan", r.unquote(tarikhDaftarFail));
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
	//@Override
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
			sql = "select A.id_Permohonan,B.ID_HTPPERMOHONAN,C.ID_FAIL,C.ID_KEMENTERIAN,B.ID_AGENSI,C.ID_URUSAN,C.NO_FAIL, E.ID_SUBURUSAN, ";
				sql += " TO_CHAR(A.TARIKH_SURAT,'DD/MM/YYYY') as TARIKH_SURAT,TO_CHAR(A.TARIKH_TERIMA,'DD/MM/YYYY') AS TARIKH_TERIMA,A.TUJUAN, ";
					sql += " B.ID_JENISTANAH,B.ID_PEGAWAI,B.NO_RUJUKAN_KJP,B.NO_RUJUKAN_LAIN,B.TARIKH_AGIHAN,B.ID_DAERAH,";
						sql += " C.ID_NEGERI,C.ID_STATUS, D.TARAF_KESELAMATAN,D.ID_TARAFKESELAMATAN,F.NAMA_NEGERI,TO_CHAR(C.TARIKH_DAFTAR_FAIL,'DD/MM/YYYY') AS OPEN_FILE_DATE, ";
						sql +=" G.NAMA_KEMENTERIAN, H.NAMA_AGENSI,E.NAMA_SUBURUSAN,E.KOD_SUBURUSAN";
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
										 			htpPermohonan.setIdJenisTanah(rs.getString("ID_JENISTANAH"));
										 			htpPermohonan.setNoRujukanKJP(rs.getString("NO_RUJUKAN_KJP"));
										 			htpPermohonan.setNoRujukanLain(rs.getString("NO_RUJUKAN_LAIN"));
										 			htpPermohonan.setNamaAgensi(rs.getString("NAMA_AGENSI"));
										 			permohonan.setIdPermohonan(rs.getString("id_Permohonan"));
										 			permohonan.setTarikhSurat(rs.getString("TARIKH_SURAT"));
										 			permohonan.setTarikhTerima(rs.getString("TARIKH_TERIMA"));
										 			permohonan.setTujuan(rs.getString("TUJUAN"));
										 			permohonan.setNamaNegeri(rs.getString("NAMA_NEGERI"));
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
										 			permohonan.setPfdFail(fail);
										 			htpPermohonan.setPermohonan(permohonan);
										 			
										 			conn.commit();
										     }
										    	
		 } finally {
		      if (db != null) db.close();
		    }
		

		return htpPermohonan;
	}
	//@Override
	public HtpPermohonan kemaskiniPermohonan(HtpPermohonan htpPermohonan,String idPermohonan,String idhtpPermohonan)throws Exception {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		


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
			//r.add("tarikh_Agihan", r.unquote(tarikhDaftarFail));
			sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
			
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
	
	//@Override
	public Vector findFail(String carian, String noFail, String idNegeri) {
		Db db = null;
		Vector<HtpPermohonan> v = new Vector();
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
	    	  sql +="AND f.id_Negeri = "+idNegeri;
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
			r.add("id_Jenisrizab");
			r.add("id_Kategori");
			r.add("id_Subkategori");
			r.add("id_Hakmilikurusan");
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
				urusan.setIdJenisRizab(rs.getString("id_Jenisrizab"));
				urusan.setIdKategoriTanah(rs.getString("id_Kategori"));
				urusan.setIdSubKetegoriTanah(rs.getString("id_Subkategori"));
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
			sql += " AND A.TINDAKAN_LANJUT IN (2,3)";
			//
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
	
	public boolean isMaklumatPemaju(String idPermohonan) throws Exception {		

		Boolean returnValue = false;
		Db db = null;
		String sql = "";
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			vecPemohonReturn = new Vector();
			
			sql = "SELECT P.ID_PEMAJU, P.ID_PERMOHONAN, P.NO_RUJUKAN_PEMAJU, P.NAMA_PEMAJU, ";
			sql += "P.ALAMAT_PEMAJU1, P.ALAMAT_PEMAJU2, P.ALAMAT_PEMAJU3, ";
			sql += "P.POSKOD_PEMAJU, P.ID_NEGERI, P.ID_DAERAH,P.ID_BANDAR, P.NO_TEL, P.NO_FAX, P.EMEL ";
			sql += "FROM TBLHTPPEMAJU P ";
			sql += "WHERE P.ID_PERMOHONAN = " + idPermohonan;
			ResultSet rs = stmt.executeQuery(sql);
			//,A.ID_BANDAR
			while(rs.next()){
				h = new Hashtable();
				h.put("idPemaju", rs.getString("ID_PEMAJU") == null ? "" : rs.getString("ID_PEMAJU"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noPemohon", rs.getString("NO_RUJUKAN_PEMAJU") == null ? "" : rs.getString("NO_RUJUKAN_PEMAJU"));
				h.put("nama", rs.getString("NAMA_PEMAJU") == null ? "" : rs.getString("NAMA_PEMAJU"));
				h.put("alamat1", rs.getString("ALAMAT_PEMAJU1") == null ? "" : rs.getString("ALAMAT_PEMAJU1"));
				h.put("alamat2", rs.getString("ALAMAT_PEMAJU2") == null ? "" : rs.getString("ALAMAT_PEMAJU2"));
				h.put("alamat3", rs.getString("ALAMAT_PEMAJU3") == null ? "" : rs.getString("ALAMAT_PEMAJU3"));
				h.put("poskod", rs.getString("POSKOD_PEMAJU") == null ? "" : rs.getString("POSKOD_PEMAJU"));
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "99999" : rs.getString("ID_NEGERI"));
				h.put("idDaerah", rs.getString("ID_DAERAH") == null ? "99999" : rs.getString("ID_DAERAH"));
				h.put("tel", rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				h.put("fax", rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
				h.put("idBandar", rs.getString("ID_BANDAR") == null ? "0" : rs.getString("ID_BANDAR"));
				h.put("emel", Utils.isNull(rs.getString("EMEL")));
				vecPemohonReturn.addElement(h);	
				returnValue = true;
			}

		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}

	public void hapusPemaju(String idPemaju) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	

			//TBLHTPPENGARAH
			r.add("ID_PEMAJU", idPemaju);

			sql = r.getSQLDelete("TBLHTPPEMAJU");
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
}
