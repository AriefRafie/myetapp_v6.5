package ekptg.model.htp.rekod;

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
import ekptg.helpers.Utils;
import ekptg.model.entities.Agensi;
import ekptg.model.entities.Daerah;
import ekptg.model.entities.Kementerian;
import ekptg.model.entities.Mukim;
import ekptg.model.entities.Negeri;
import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.entities.Tblrujlot;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.HakmilikAgensi;
import ekptg.model.htp.entity.HakmilikCukai;
import ekptg.model.htp.entity.HakmilikStrata;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.entity.StatusSah;
//import net.sf.ehcache.Element;

public class HakmilikBean implements HakmilikInterface {
	
	private HakmilikInterface iHakmilik = null;
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.rekod.HakmilikBean.class);
	private HakMilik hakmilik = null;
 	private IHtp iHTP = null;  
  	private IHTPUtilitiRekod iUtilRekod = null;
  	private ITanah iTanah = null;  
 	private PfdFail pfdFail = null;
 	private Permohonan permohonan = null;
 	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
 	
	private HakMilik copyToRecord(HakMilik hakmilik){
		Db db = null;
		Connection conn = null;
		String sql =null;
		try{
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			long idHakmilikBaru = DB.getNextID(db,"TBLHTPHAKMILIK_SEQ"); 
			hakmilik.setIdHakmilik(idHakmilikBaru);
			long idHakmilikAgensi = DB.getNextID("TBLHTPHAKMILIKAGENSI_SEQ"); 
			 r.add("ID_HAKMILIK",hakmilik.getIdHakmilik());
			 r.add("ID_PERMOHONAN",hakmilik.getIdPermohonan());
			 r.add("ID_NEGERI",hakmilik.getIdNegeri());
			 r.add("ID_DAERAH",hakmilik.getIdDaerah());
			 r.add("ID_MUKIM",hakmilik.getIdMukim());
			 r.add("ID_JENISHAKMILIK",hakmilik.getIdJenisHakmilik());
			 r.add("NO_HAKMILIK",hakmilik.getNoHakmlik());
			 r.add("NO_LOT",hakmilik.getNoLot());
			 r.add("ID_LOT", hakmilik.getIdJenisLot());
			 r.add("STATUS_SAH","D");
			 r.add("ID_MASUK",hakmilik.getIdMasuk());
			 r.add("ID_LUAS",hakmilik.getIdLuas());
			 r.add("ID_LUAS_BERSAMAAN","2");
			 r.add("PEGANGAN_HAKMILIK",hakmilik.getPeganganHakmilik());
			 r.add("ID_SUBKATEGORI",hakmilik.getIdSubkategori());
			 r.add("ID_KATEGORI",hakmilik.getIdKategori());
			 r.add("ID_AGENSI",hakmilik.getHakmilikAgensi().getIdAgensi());
			 r.add("ID_KEMENTERIAN",hakmilik.getHakmilikAgensi().getIdKementerian());
			 r.add("NO_FAIL",hakmilik.getNoFail());
			 r.add("NO_FAIL_KJP",hakmilik.getNoFailKJP());
			 r.add("NO_FAIL_PTG",hakmilik.getNoFailPTG());
			 r.add("NO_FAIL_PTD",hakmilik.getNoFailPTD());

			 sql = r.getSQLInsert("TBLHTPHAKMILIK");
			 
			 stmt.executeUpdate(sql);
		
			r = new SQLRenderer();
		    r.add("ID_HAKMILIKAGENSI",idHakmilikAgensi);
		    r.add("ID_HAKMILIK",hakmilik.getIdHakmilik());
		    r.add("ID_MASUK",hakmilik.getIdMasuk());
		    r.add("ID_AGENSI",hakmilik.getHakmilikAgensi().getIdAgensi());
		    r.add("ID_KEMENTERIAN",hakmilik.getHakmilikAgensi().getIdKementerian());
		    r.add("TARIKH_MASUK",r.unquote("SYSDATE"));
		    sql = r.getSQLInsert("TBLHTPHAKMILIKAGENSI");
		    
		    stmt.executeUpdate(sql);
		    
		    r = new SQLRenderer();
		    r.add("STATUS","S");
		    r.add("ID_HAKMILIK",hakmilik.getIdHakmilik());
		    r.add("ID_MASUK",hakmilik.getIdMasuk());
		    r.add("CUKAI_TERKINI",hakmilik.getHakmilikCukai().getCukaiTerkini());
		    r.add("CUKAI",hakmilik.getHakmilikCukai().getCukai());
		    r.add("TARIKH_MASUK",r.unquote("SYSDATE"));
		    
			sql = r.getSQLInsert("TBLHTPHAKMILIKCUKAI");
			stmt.executeUpdate(sql);
			 
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

		public HakMilik getHakmilik(String idHakmilik){
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
			// Rekod-FrmRekodPendaftaranTanah (online)
			r.add("RJH.KETERANGAN JENIS_KETERANGAN");
			r.add("TPH.NO_BANGUNAN");
			r.add("TPH.NO_TINGKAT");
			r.add("TPH.NO_PETAK");			
			r.add("TPH.TEMPOH");
	    	r.add("TPH.SYARAT");	  			
	    	r.add("TPH.HAKMILIK_ASAL");
	    	r.add("TPH.NO_FAIL_JOPA");	    
	    	r.add("TPH.TARAF_HAKMILIK");	    
			r.add("NVL(TO_CHAR(TPH.TARIKH_LUPUT,'dd/mm/yyyy'),'01/01/1900') TARIKH_LUPUT");
			r.add("TPH.CUKAI_TERKINI");
			r.add("TPH.CUKAI");
			r.add("NVL(TO_CHAR(TPH.TARIKH_RIZAB,'dd/mm/yyyy'),'01/01/1900') TARIKH_RIZAB");
			r.add("TPH.KAWASAN_RIZAB");
	    	r.add("TPH.NO_RIZAB");	    	
	    	r.add("TPH.ID_RIZAB");	    	
	    	r.add("TPH.SEKATAN");	
		    r.add("TPH.HAKMILIK_ASAL");
		    r.add("TPH.HAKMILIK_BERIKUT");
			// End Rekod-FrmRekodPendaftaranTanah (online)
						
//			r.add("TPC.ID_HAKMILIK");
//			r.add("TPC.ID_HAKMILIKCUKAI");
//			r.add("TPC.CUKAI_TALIAIR");
//			r.add("TPC.CUKAI_PARIT");
//			r.add("TPC.DENDA");
//			r.add("TPC.BAYARAN_LAIN");
//			r.add("TPC.CUKAI");
//			r.add("TPC.PENGURANGAN");
//			r.add("TPC.PENGECUALIAN");
			r.add("TPH.NO_HAKMILIK");
			r.add("TPH.NO_WARTA");
			r.add("TPH.LUAS_BERSAMAAN");
			r.add("TPH.LUAS");
			r.add("TPH.ID_LUAS");
			r.add("TPH.KEGUNAAN_TANAH");
			//r.add("TPH.TARIKH_TERIMA");
			//r.add("TPH.TARIKH_DAFTAR");
			r.add("NVL(TO_CHAR(TPH.TARIKH_DAFTAR,'dd/mm/yyyy'),'01/01/1900') TARIKH_DAFTAR");
			r.add("NVL(TO_CHAR(TPH.TARIKH_TERIMA,'dd/mm/yyyy'),'01/01/1900') TARIKH_TERIMA");
			r.add("TPH.TARIKH_WARTA");
			r.add("NVL((SELECT KETERANGAN FROM TBLRUJLOT WHERE ID_LOT=TPH.ID_LOT),'') KOD_LOT");
			r.add("TPH.NO_LOT");	
			r.add("TPH.ID_LOT");	
			r.add("NVL((SELECT RU.NAMA_URUSAN FROM TBLRUJURUSAN RU,TBLPFDFAIL F,TBLPERMOHONAN P" +
					" WHERE RU.ID_URUSAN = F.ID_URUSAN " +
					" AND F.ID_FAIL = P.ID_FAIL" +
					" AND P.ID_PERMOHONAN = TPH.ID_PERMOHONAN),'') NAMA_URUSAN");
			r.add("TPH.ID_PERMOHONAN");			
			r.add("TPH.CATATAN");			
			r.add("TPH.ID_HAKMILIK");
			r.add("TPH.LOKASI");
			r.add("TPH.NO_PELAN");
			r.add("TPH.NO_SYIT");
			r.add("TPH.NO_PU");
			r.add("( SELECT SSI.KETERANGAN FROM TBLHTPRUJSTATUSAH SSI "+
					" WHERE SSI.STATUS_SAH = TPH.STATUS_SAH "+
				 	" ) STATUS_SAHKETERANGAN");
			r.add("TPH.STATUS_SAH");
			//r.add("TO_CHAR(TPH.TARIKH_KEMASKINI,'dd/mm/yyyy') TARIKH_KEMASKINI");
			//r.add("TPH.TARIKH_KEMASKINI");
			r.add("NVL(TO_CHAR(TPH.TARIKH_KEMASKINI,'dd/mm/yyyy'),'01/01/1900') TARIKH_KEMASKINI");
			r.add("(SELECT USER_NAME FROM USERS WHERE USER_ID=TPH.ID_KEMASKINI) PEGAWAI_AKHIR");
			r.add("TPH.ID_JENISRIZAB");
			r.add("TPH.ID_HAKMILIK",r.unquote(idHakmilik));
//			r.add("TPC.ID_HAKMILIK",r.unquote("TPH.ID_HAKMILIK"));
//			r.add("TPH.ID_AGENSI",r.unquote("RA.ID_AGENSI")); //Edited by rosli on 09/06/2011
			r.add("RA.ID_KEMENTERIAN",r.unquote("RK.ID_KEMENTERIAN"));
//			r.add("TPC.STATUS","S");
			r.add("TPH.ID_MUKIM",r.unquote("RM.ID_MUKIM"));
			r.add("RM.ID_DAERAH",r.unquote("RD.ID_DAERAH"));
			r.add("RD.ID_NEGERI",r.unquote("RN.ID_NEGERI"));
			r.add("TPH.ID_JENISHAKMILIK",r.unquote("RJH.ID_JENISHAKMILIK"));
			r.add("TPH.ID_HAKMILIK",r.unquote("TPA.ID_HAKMILIK"));
			r.add("TPA.STATUS","Y");
			r.add("TPA.ID_AGENSI",r.unquote("RA.ID_AGENSI"));
			//sql = r.getSQLSelect("TBLHTPHAKMILIKCUKAI TPC,TBLHTPHAKMILIK TPH" +
			sql = r.getSQLSelect("TBLHTPHAKMILIK TPH" +
				",TBLRUJAGENSI RA" +
				",TBLRUJKEMENTERIAN RK" +
				",TBLRUJMUKIM RM,TBLRUJDAERAH RD,TBLRUJNEGERI RN,TBLRUJJENISHAKMILIK RJH " +
				",TBLHTPHAKMILIKAGENSI TPA " +
				"");
					//		",TBLRUJAGENSIMAPPING RAM" +
			//		",TBLRUJAGENSI RA,TBLRUJKEMENTERIAN RK,TBLRUJKEMENTERIANMAPPING RKM");
			//
			myLog.info("345,getHakmilik:sql="+sql);
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
				StatusSah ss = new StatusSah();
				HakmilikStrata strata = new HakmilikStrata();
				
				hakmilik.setIdPermohonan(rs.getLong("ID_PERMOHONAN"));
				hakmilik.setIdHakmilik(rs.getLong("ID_HAKMILIK"));
				hakmilik.setNoFail(rs.getString("NO_FAIL"));
				hakmilik.setNoFailPTD(rs.getString("NO_FAIL_PTD"));
				hakmilik.setNoFailPTG(rs.getString("NO_FAIL_PTG"));
				hakmilik.setNoFailJOPA(rs.getString("NO_FAIL_JOPA"));
				hakmilik.setPerolehan(rs.getString("NAMA_URUSAN"));
				hakmilik.setLuasBersamaan(rs.getDouble("LUAS_BERSAMAAN"));
				hakmilik.setLuasString(rs.getString("LUAS"));
				hakmilik.setKegunaan(rs.getString("KEGUNAAN_TANAH"));
				hakmilik.setNoHakmlik(rs.getString("NO_HAKMILIK"));
				hakmilik.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				hakmilik.setNoWarta(rs.getString("NO_WARTA"));
				hakmilik.setNoLot(rs.getString("NO_LOT"));
				hakmilik.setIdJenisLot(rs.getLong("ID_LOT"));
				hakmilik.setTarikhDaftar(new Date(rs.getString("TARIKH_DAFTAR")));
				hakmilik.setTarikhWarta(rs.getDate("TARIKH_WARTA"));
				hakmilik.setTarikhTerima(new Date(rs.getString("TARIKH_TERIMA")));
				//hakmilik.setTarikhKemaskini(rs.getDate("TARIKH_KEMASKINI"));
				hakmilik.setTarikhKemaskini(new Date(rs.getString("TARIKH_KEMASKINI")));
				hakmilik.setIdLuas(rs.getLong("ID_LUAS"));
				hakmilik.setCatatan(Utils.isNull(rs.getString("CATATAN")));
				hakmilik.setStatusSah(rs.getString("STATUS_SAH"));
				hakmilik.setNamaKemaskini(rs.getString("PEGAWAI_AKHIR"));
				hakmilik.setLokasi(rs.getString("LOKASI"));
				hakmilik.setNoPelan(rs.getString("NO_PELAN"));
				hakmilik.setNoSyit(rs.getString("NO_SYIT"));
				hakmilik.setNoPU(rs.getString("NO_PU"));
				
				hakmilik.setIdJenisLot(rs.getLong("ID_LOT"));
				hakmilik.setIdJenisRizab(rs.getInt("ID_JENISRIZAB"));
				
				kem.setIdKementerian(rs.getLong("ID_KEMENTERIAN"));				
				kem.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));				
				agensi.setIdAgensi(rs.getLong("ID_AGENSI"));
				agensi.setNamaAgensi(rs.getString("NAMA_AGENSI"));
				agensi.setKementerian(kem);
				hakmilik.setAgensi(agensi);
				
//				cukai.setCukai(rs.getDouble("CUKAI"));
//				cukai.setCukaiTaliAir(rs.getDouble("CUKAI_TALIAIR"));
//				cukai.setCukaiParit(rs.getDouble("CUKAI_PARIT"));
//				cukai.setDenda(rs.getDouble("DENDA"));
//				cukai.setBayaranLain(rs.getDouble("BAYARAN_LAIN"));
//				cukai.setPengecualian(rs.getDouble("PENGECUALIAN"));
//				cukai.setPengurangan(rs.getDouble("PENGURANGAN"));
//				cukai.setIdHakmilikCukai(rs.getLong("ID_HAKMILIKCUKAI"));
//				hakmilik.setHakmilikCukai(cukai);
				
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
				// Rekod-FrmRekodPendaftaranTanah (online)
				jh.setKeterangan(rs.getString("JENIS_KETERANGAN"));
				hakmilik.setRujJenisHakmilik(jh);
				// Rekod-FrmRekodPendaftaranTanah (online)
				ss.setKod(rs.getString("STATUS_SAH"));
				ss.setKeterangan(rs.getString("STATUS_SAHKETERANGAN"));
				hakmilik.setStatus(ss);
				strata.setBangunan(rs.getString("NO_BANGUNAN"));
				strata.setTingkat(rs.getString("NO_TINGKAT"));
				strata.setPetak(rs.getString("NO_PETAK"));		
				hakmilik.setStrata(strata);
				hakmilik.setTempoh(rs.getString("TEMPOH"));
				hakmilik.setSyarat(rs.getString("SYARAT"));
				hakmilik.setNoHakmilikAsal(rs.getString("HAKMILIK_ASAL"));
				hakmilik.setTaraf(rs.getString("TARAF_HAKMILIK"));
				hakmilik.setTarikhLuput(new Date(rs.getString("TARIKH_LUPUT")));
				cukai.setCukaiTerkini(rs.getDouble("CUKAI_TERKINI"));
				cukai.setCukai(rs.getDouble("CUKAI"));
				hakmilik.setHakmilikCukai(cukai);
				hakmilik.setTarikhRizab(new Date(rs.getString("TARIKH_RIZAB")));
				hakmilik.setKawasanRizab(rs.getString("KAWASAN_RIZAB"));
				hakmilik.setNoRizab(rs.getString("NO_RIZAB"));
				if(rs.getString("NO_HAKMILIK")!= null){
		    		  hakmilik.setRizab("M");
		    	}else{
		    		  hakmilik.setRizab("R");
		    	}
		    	if(rs.getString("NO_HAKMILIK")!= null && rs.getString("NO_WARTA")!= null){
		    		  hakmilik.setRizab("X");
		    	}
		    	//
				hakmilik.setSekatan(rs.getString("SEKATAN"));
				hakmilik.setNoHakmilikAsal(rs.getString("HAKMILIK_ASAL"));
				hakmilik.setNoHakmilikBerikut(rs.getString("HAKMILIK_BERIKUT"));
				// EndRekod-FrmRekodPendaftaranTanah (online)
				
			}
			
		}catch(Exception e){
				e.printStackTrace();
			
	 	} finally {
	 		if (db != null) db.close();
	 	}

		return hakmilik;
	}
	//Add by rosli 18/08/2010
	
	public HakMilik kemaskiniHakmilikCatatan(String idHakmilik,String catatan){
		Db db = null;
		Connection conn = null;
		String sql =null;
		HakMilik hakmilik = new HakMilik();
		try{
			hakmilik = getHakmilik(idHakmilik);
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			 r.update("ID_HAKMILIK",idHakmilik);
			 r.add("CATATAN",hakmilik.getCatatan()+catatan);
			 sql = r.getSQLUpdate("TBLHTPHAKMILIK");
			 
			 stmt.executeUpdate(sql);
			 
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
		return getHakmilik(idHakmilik);
		
	}
	
	public HakMilik getMaklumatHakmilik(String idHakmilik) throws Exception{
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
			r.add("TPH.ID_KEMENTERIAN");
			r.add("TPH.ID_AGENSI");
			r.add("TPH.ID_NEGERI");
			r.add("TPH.ID_DAERAH");
			r.add("TPH.ID_MUKIM");
			r.add("TPH.ID_JENISHAKMILIK");
			r.add("TPH.NO_HAKMILIK");
			r.add("TPH.NO_WARTA");
			r.add("TPH.LUAS_BERSAMAAN");
			r.add("TPH.LUAS");
			r.add("TPH.ID_LUAS");
			r.add("TPH.KEGUNAAN_TANAH");
			//r.add("TPH.TARIKH_TERIMA");
			r.add("NVL(TO_CHAR(TPH.TARIKH_TERIMA,'dd/mm/yyyy'),'01/01/1900') TARIKH_TERIMA");
			r.add("TPH.TARIKH_DAFTAR");
			r.add("NVL(TO_CHAR(TPH.TARIKH_WARTA,'dd/mm/yyyy'),'01/01/1900') TARIKH_WARTA");
			r.add("NVL((SELECT KETERANGAN FROM TBLRUJLOT WHERE ID_LOT=TPH.ID_LOT),'') KOD_LOT");
			r.add("TPH.NO_LOT");	
			r.add("TPH.ID_LOT");	
			r.add("NVL((SELECT RU.NAMA_URUSAN FROM TBLRUJURUSAN RU,TBLPFDFAIL F,TBLPERMOHONAN P" +
					" WHERE RU.ID_URUSAN = F.ID_URUSAN " +
					" AND F.ID_FAIL = P.ID_FAIL" +
					" AND P.ID_PERMOHONAN = TPH.ID_PERMOHONAN),'') NAMA_URUSAN");
			r.add("TPH.ID_PERMOHONAN");			
			r.add("TPH.CATATAN");			
			r.add("TPH.ID_HAKMILIK");
			r.add("TPH.LOKASI");
			r.add("TPH.NO_PELAN");
			r.add("TPH.NO_SYIT");
			r.add("TPH.NO_PU");
			r.add("CASE  "+
			      "     WHEN TPH.STATUS_SAH = 'H'  "+
			      "         THEN 'HAKMILIK ASAL TIADA'  "+
			      "     WHEN TPH.STATUS_SAH = 'D'  "+
			      "         THEN 'DAFTAR'  "+
			      " 	WHEN TPH.STATUS_SAH = 'P'  "+
			      " 	    THEN 'BATAL (PELEPASAN)'  "+
			      " 	WHEN TPH.STATUS_SAH = 'T'  "+
			      " 	    THEN 'BATAL (TUKARGUNA)'   "+
			      " 	WHEN TPH.STATUS_SAH = 'S'  "+
			      " 	    THEN 'BATAL (SAMBUNGAN)'  "+
			      " 	WHEN TPH.STATUS_SAH = 'M'  "+
			      " 	    THEN 'BATAL (PINDAHMILIK)'  "+
			      " 	WHEN TPH.STATUS_SAH = 'B'  "+
			      "        	THEN 'BATAL'  "+
			      "		WHEN TPH.STATUS_SAH = 'Q' "+
			      "			THEN 'BATAL (PERMOHONAN)' "+			      
			      " 	WHEN TPH.STATUS_SAH = 'L'  "+
			      "        THEN 'BATAL (PERLETAKHAKAN)'  "+
			      "    ELSE 'TIADA'  "+
			      " END AS STATUS_SAH");
			r.add("TPH.TARIKH_KEMASKINI");
			r.add("(SELECT USER_NAME FROM USERS WHERE USER_ID=TPH.ID_KEMASKINI) PEGAWAI_AKHIR");
			r.add("TPH.ID_MASUK");
			r.add("TPH.ID_HAKMILIK",r.unquote(idHakmilik));
			sql = r.getSQLSelect("TBLHTPHAKMILIK TPH" +
				"");
//			myLog.info("getMaklumatHakmilik:"+sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Agensi agensi = new Agensi();
				Kementerian kem = new Kementerian();
				Negeri negeri = new Negeri();
				Daerah daerah = new Daerah();
				Mukim mukim = new Mukim();
				Tblrujlot lot = new Tblrujlot();
				Tblrujjenishakmilik jh = new Tblrujjenishakmilik();
				
				hakmilik.setIdPermohonan(rs.getLong("ID_PERMOHONAN"));
				hakmilik.setIdHakmilik(rs.getLong("ID_HAKMILIK"));
				hakmilik.setNoFail(rs.getString("NO_FAIL"));
				hakmilik.setNoFailPTD(rs.getString("NO_FAIL_PTD"));
				hakmilik.setNoFailPTG(rs.getString("NO_FAIL_PTG"));
				hakmilik.setPerolehan(rs.getString("NAMA_URUSAN"));
				hakmilik.setLuasBersamaan(rs.getDouble("LUAS_BERSAMAAN"));
				hakmilik.setLuasString(rs.getString("LUAS"));
				hakmilik.setKegunaan(rs.getString("KEGUNAAN_TANAH"));
				hakmilik.setNoHakmlik(rs.getString("NO_HAKMILIK"));
				hakmilik.setNoWarta(rs.getString("NO_WARTA"));
				hakmilik.setNoLot(rs.getString("NO_LOT"));
				hakmilik.setIdJenisLot(rs.getLong("ID_LOT"));
				hakmilik.setTarikhDaftar(rs.getDate("TARIKH_DAFTAR"));
				hakmilik.setTarikhWarta(new Date(rs.getString("TARIKH_WARTA")));
				hakmilik.setTarikhTerima(new Date(rs.getString("TARIKH_TERIMA")));
				hakmilik.setTarikhKemaskini(rs.getDate("TARIKH_KEMASKINI"));
				hakmilik.setIdLuas(rs.getLong("ID_LUAS"));
				hakmilik.setCatatan(Utils.isNull(rs.getString("CATATAN")));
				hakmilik.setStatusSah(rs.getString("STATUS_SAH"));
				hakmilik.setNamaKemaskini(rs.getString("PEGAWAI_AKHIR"));
				hakmilik.setLokasi(rs.getString("LOKASI"));
				hakmilik.setNoPelan(rs.getString("NO_PELAN"));
				hakmilik.setNoSyit(rs.getString("NO_SYIT"));
				hakmilik.setNoPU(rs.getString("NO_PU"));
				
				hakmilik.setIdJenisLot(rs.getLong("ID_LOT"));
				
				kem.setIdKementerian(rs.getLong("ID_KEMENTERIAN"));				
				agensi.setIdAgensi(rs.getLong("ID_AGENSI"));
				agensi.setKementerian(kem);
				hakmilik.setAgensi(agensi);

				negeri.setIdNegeri(rs.getLong("ID_NEGERI"));
				hakmilik.setNegeri(negeri);
				
				daerah.setIdDaerah(rs.getLong("ID_DAERAH"));
				hakmilik.setDaerah(daerah);
				
				mukim.setIdMukim(rs.getLong("ID_MUKIM"));
				hakmilik.setMukim(mukim);
				
				lot.setIdLot(rs.getLong("ID_LOT"));
				hakmilik.setRujLot(lot);
				
				jh.setIdJenishakmilik(rs.getLong("ID_JENISHAKMILIK"));
				hakmilik.setRujJenisHakmilik(jh);
				hakmilik.setIdMasuk(rs.getLong("ID_MASUK"));
				
			}
			
		}catch(Exception e){
			throw new Exception(getIHTP().getErrorHTML(e.toString()));
			
	 	} finally {
	 		if (db != null) db.close();
	 	}

		return hakmilik;
	}
	
	public HakMilik getMaklumatFailById(String idHakmilik) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			hakmilik = new HakMilik();
			
			SQLRenderer r = new SQLRenderer();
			Statement stmt = db.getStatement();	
			if(isHakmilikAgensi(idHakmilik)==false){
				 Hashtable rizab = new Hashtable();
				 HakMilik hakmilik_ = new HakMilik();
				 hakmilik_ = getMaklumatHakmilik(idHakmilik);
				 
				 rizab.put("idHakmilik", idHakmilik);
				 rizab.put("idKementerian", String.valueOf(hakmilik_.getAgensi().getKementerian().getIdKementerian()));	//TBLHTPHAKMILIKAGENSI
				 rizab.put("idAgensi", String.valueOf(hakmilik_.getAgensi().getIdAgensi()));	//TBLHTPHAKMILIKAGENSI
				 rizab.put("txtLuasBersamaan", Utils.RemoveComma( String.valueOf(hakmilik_.getLuasBersamaan())));
				 rizab.put("socLuas",hakmilik_.getIdLuas());
				 rizab.put("txtLuas",hakmilik_.getLuasString());
				 rizab.put("idMasuk", hakmilik_.getIdMasuk());			
				 FrmUtilData.insertHakmilikAgensi(db,rizab,idHakmilik);

			}
			r = new SQLRenderer();
			r.add("TPH.NO_FAIL_PTG");
			r.add("TPH.NO_FAIL_PTD");
			r.add("TPH.NO_FAIL");
			r.add("TPH.NO_FAIL_KJP");
			r.add("P.TUJUAN");
			
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
			r.add("NVL((SELECT RU.NAMA_URUSAN FROM TBLRUJURUSAN RU,TBLPFDFAIL F,TBLPERMOHONAN P" +
					" WHERE RU.ID_URUSAN = F.ID_URUSAN " +
					" AND F.ID_FAIL = P.ID_FAIL" +
					" AND P.ID_PERMOHONAN = TPH.ID_PERMOHONAN),'') NAMA_URUSAN");
			r.add("NVL((SELECT FI.NO_FAIL FROM TBLPFDFAIL FI,TBLPERMOHONAN PI" +
					" WHERE  " +
					" FI.ID_FAIL = PI.ID_FAIL" +
					" AND PI.ID_PERMOHONAN = TPH.ID_PERMOHONAN),'') NO_FAIL_P");
//			r.add("NVL((SELECT RU.NAMA_URUSAN FROM TBLRUJURUSAN RU,TBLPFDFAIL F,TBLPERMOHONAN P" +
//					" WHERE RU.ID_URUSAN = F.ID_URUSAN " +
//					" AND F.ID_FAIL = P.ID_FAIL" +
//					" AND P.ID_PERMOHONAN = TPH.ID_PERMOHONAN),'') NO_FAILPTG_P");
//			r.add("NVL((SELECT RU.NAMA_URUSAN FROM TBLRUJURUSAN RU,TBLPFDFAIL F,TBLPERMOHONAN P" +
//					" WHERE RU.ID_URUSAN = F.ID_URUSAN " +
//					" AND F.ID_FAIL = P.ID_FAIL" +
//					" AND P.ID_PERMOHONAN = TPH.ID_PERMOHONAN),'') NO_FAILPTD_P");
			r.add("TPH.ID_PERMOHONAN");			
			r.add("TPH.CATATAN");			
			r.add("TPH.ID_HAKMILIK");
			r.add("TPH.ID_HAKMILIK",r.unquote(idHakmilik));
			r.add("RA.ID_KEMENTERIAN",r.unquote("RK.ID_KEMENTERIAN"));
			r.add("TPH.ID_MUKIM",r.unquote("RM.ID_MUKIM"));
			r.add("RM.ID_DAERAH",r.unquote("RD.ID_DAERAH"));
			r.add("RD.ID_NEGERI",r.unquote("RN.ID_NEGERI"));
			r.add("TPH.ID_HAKMILIK",r.unquote("TPA.ID_HAKMILIK"));
			r.add("TPA.STATUS","Y");
			r.add("TPA.ID_AGENSI",r.unquote("RA.ID_AGENSI"));
			r.add("TPH.ID_PERMOHONAN",r.unquote("P.ID_PERMOHONAN"));
			sql = r.getSQLSelect("TBLHTPHAKMILIK TPH" +
				",TBLRUJAGENSI RA" +
				",TBLRUJKEMENTERIAN RK" +
				",TBLRUJMUKIM RM,TBLRUJDAERAH RD,TBLRUJNEGERI RN " +
				",TBLHTPHAKMILIKAGENSI TPA,TBLPERMOHONAN P " +
				"");
			//myLog.info("getMaklumatFailById:sql= "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Agensi agensi = new Agensi();
				Kementerian kem = new Kementerian();
				Negeri negeri = new Negeri();
				Daerah daerah = new Daerah();
				Mukim mukim = new Mukim();
				Tblrujlot lot = new Tblrujlot();
				Tblrujjenishakmilik jh = new Tblrujjenishakmilik();
				permohonan = new Permohonan();
				pfdFail = new PfdFail();
				
				hakmilik.setIdPermohonan(rs.getLong("ID_PERMOHONAN"));
				hakmilik.setIdHakmilik(rs.getLong("ID_HAKMILIK"));
				hakmilik.setNoFail(rs.getString("NO_FAIL")==null||rs.getString("NO_FAIL").equals("")?rs.getString("NO_FAIL_P"):rs.getString("NO_FAIL"));
				hakmilik.setNoFailPTD(rs.getString("NO_FAIL_PTD"));
				hakmilik.setNoFailPTG(rs.getString("NO_FAIL_PTG"));
				hakmilik.setPerolehan(rs.getString("NAMA_URUSAN"));
				pfdFail.setTajukFail(Utils.isNull(rs.getString("TUJUAN")));
				pfdFail.setNoFail(rs.getString("NO_FAIL"));
				permohonan.setPfdFail(pfdFail);
				hakmilik.setPermohonan(permohonan);
				
				kem.setIdKementerian(rs.getLong("ID_KEMENTERIAN"));				
				kem.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));				
				agensi.setIdAgensi(rs.getLong("ID_AGENSI"));
				agensi.setNamaAgensi(rs.getString("NAMA_AGENSI"));
				agensi.setKementerian(kem);
				hakmilik.setAgensi(agensi);
				
				negeri.setIdNegeri(rs.getLong("ID_NEGERI"));
				negeri.setNamaNegeri(rs.getString("NAMA_NEGERI"));
				hakmilik.setNegeri(negeri);
				
				daerah.setIdDaerah(rs.getLong("ID_DAERAH"));
				daerah.setNamaDaerah(rs.getString("NAMA_DAERAH"));
				hakmilik.setDaerah(daerah);
				
				mukim.setIdMukim(rs.getLong("ID_MUKIM"));
				mukim.setNamaMukim(rs.getString("NAMA_MUKIM"));
				hakmilik.setMukim(mukim);
				
			}
		
		}catch(Exception e){	
			throw new Exception(getIHTP().getErrorHTML(e.toString()));

		} finally {
			if (db != null)
			db.close();
			
		}
		return hakmilik;
		
	}
	
	public HakMilik getWarta(String idHakmilik) throws Exception {
		Connection conn = null;
		Db db = null;
		hakmilik = new HakMilik();
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
			r.add("TPH.NO_FAIL_JOPA");
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
			r.add("TPH.NO_WARTA");
			r.add("TPH.LUAS_BERSAMAAN");
			r.add("TPH.LUAS");
			r.add("TPH.ID_LUAS");
			r.add("TPH.KEGUNAAN_TANAH");
			r.add("NVL(TO_CHAR(TPH.TARIKH_TERIMA,'dd/mm/yyyy'),'01/01/1900') TARIKH_TERIMA");
			r.add("NVL(TO_CHAR(TPH.TARIKH_WARTA,'dd/mm/yyyy'),'01/01/1900') TARIKH_WARTA");
			r.add("NVL((SELECT KETERANGAN FROM TBLRUJLOT WHERE ID_LOT=TPH.ID_LOT),'') KOD_LOT");
			r.add("TPH.NO_LOT");	
			r.add("TPH.ID_LOT");	
			r.add("NVL((SELECT RU.NAMA_URUSAN FROM TBLRUJURUSAN RU,TBLPFDFAIL F,TBLPERMOHONAN P" +
					" WHERE RU.ID_URUSAN = F.ID_URUSAN " +
					" AND F.ID_FAIL = P.ID_FAIL" +
					" AND P.ID_PERMOHONAN = TPH.ID_PERMOHONAN),'') NAMA_URUSAN");
			r.add("TPH.ID_PERMOHONAN");			
			r.add("TPH.CATATAN");			
			r.add("TPH.ID_HAKMILIK");
			r.add("TPH.LOKASI");
			r.add("TPH.NO_PELAN");
			r.add("TPH.NO_SYIT");
			r.add("TPH.NO_PU");
			r.add("CASE  "+
			      "     WHEN TPH.STATUS_SAH = 'H'  "+
			      "         THEN 'HAKMILIK ASAL TIADA'  "+
			      "     WHEN TPH.STATUS_SAH = 'D'  "+
			      "         THEN 'DAFTAR'  "+
			      " 	WHEN TPH.STATUS_SAH = 'P'  "+
			      " 	    THEN 'BATAL (PELEPASAN)'  "+
			      " 	WHEN TPH.STATUS_SAH = 'T'  "+
			      " 	    THEN 'BATAL (TUKARGUNA)'   "+
			      " 	WHEN TPH.STATUS_SAH = 'S'  "+
			      " 	    THEN 'BATAL (SAMBUNGAN)'  "+
			      " 	WHEN TPH.STATUS_SAH = 'M'  "+
			      " 	    THEN 'BATAL (PINDAHMILIK)'  "+
			      " 	WHEN TPH.STATUS_SAH = 'B'  "+
			      "        	THEN 'BATAL'  "+
			      "		WHEN TPH.STATUS_SAH = 'Q' "+
			      "			THEN 'BATAL (PERMOHONAN)' "+			      
			      " 	WHEN TPH.STATUS_SAH = 'L'  "+
			      "        THEN 'BATAL (PERLETAKHAKAN)'  "+
			      "    ELSE 'TIADA'  "+
			      " END AS STATUS_SAHKETERANGAN");
			r.add("TPH.STATUS_SAH");
			//r.add("TPH.TARIKH_KEMASKINI");
			r.add("NVL(TO_CHAR(TPH.TARIKH_KEMASKINI,'dd/mm/yyyy'),'01/01/1900') TARIKH_KEMASKINI");
			r.add("TPH.NO_FAIL_JOPA");
			//r.add("TPH.TARAF_HAKMILIK"); untuk hakmilik
			r.add("NVL((SELECT USER_NAME FROM USERS WHERE USER_ID=TPH.ID_KEMASKINI),'') PEGAWAI_AKHIR");
			r.add("TPH.ID_HAKMILIK",r.unquote(idHakmilik));
			r.add("RA.ID_KEMENTERIAN",r.unquote("RK.ID_KEMENTERIAN"));
			r.add("TPH.ID_MUKIM",r.unquote("RM.ID_MUKIM"));
			r.add("RM.ID_DAERAH",r.unquote("RD.ID_DAERAH"));
			r.add("RD.ID_NEGERI",r.unquote("RN.ID_NEGERI"));
			r.add("TPH.ID_HAKMILIK",r.unquote("TPA.ID_HAKMILIK"));
			r.add("TPA.STATUS","Y");
			r.add("TPA.ID_AGENSI",r.unquote("RA.ID_AGENSI"));
			sql = r.getSQLSelect("TBLHTPHAKMILIK TPH" +
				",TBLRUJAGENSI RA" +
				",TBLRUJKEMENTERIAN RK" +
				",TBLRUJMUKIM RM,TBLRUJDAERAH RD,TBLRUJNEGERI RN " +
				",TBLHTPHAKMILIKAGENSI TPA " +
				"");
//			myLog.info("getWarta:"+sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Agensi agensi = new Agensi();
				Kementerian kem = new Kementerian();
				Negeri negeri = new Negeri();
				Daerah daerah = new Daerah();
				Mukim mukim = new Mukim();
				Tblrujlot lot = new Tblrujlot();
				StatusSah ss = new StatusSah();
				
				hakmilik.setIdPermohonan(rs.getLong("ID_PERMOHONAN"));
				hakmilik.setIdHakmilik(rs.getLong("ID_HAKMILIK"));
				hakmilik.setNoFail(rs.getString("NO_FAIL"));
				hakmilik.setNoFailPTD(rs.getString("NO_FAIL_PTD"));
				hakmilik.setNoFailPTG(rs.getString("NO_FAIL_PTG"));
				hakmilik.setNoFailJOPA(rs.getString("NO_FAIL_JOPA"));
				hakmilik.setPerolehan(rs.getString("NAMA_URUSAN"));
				hakmilik.setLuasBersamaan(rs.getDouble("LUAS_BERSAMAAN"));
				hakmilik.setLuasString(rs.getString("LUAS"));
				hakmilik.setKegunaan(rs.getString("KEGUNAAN_TANAH"));
				hakmilik.setNoWarta(rs.getString("NO_WARTA"));
				hakmilik.setNoLot(rs.getString("NO_LOT"));
				hakmilik.setIdJenisLot(rs.getLong("ID_LOT"));
				hakmilik.setTarikhWarta(new Date(rs.getString("TARIKH_WARTA")));
				hakmilik.setTarikhTerima(new Date(rs.getString("TARIKH_TERIMA")));
				hakmilik.setTarikhKemaskini(new Date(rs.getString("TARIKH_KEMASKINI")));
				hakmilik.setIdLuas(rs.getLong("ID_LUAS"));
				hakmilik.setCatatan(Utils.isNull(rs.getString("CATATAN")));
				hakmilik.setStatusSah(rs.getString("STATUS_SAH"));
				ss.setKod(rs.getString("STATUS_SAH"));
				ss.setKeterangan(rs.getString("STATUS_SAHKETERANGAN"));
				hakmilik.setStatus(ss);
				hakmilik.setNamaKemaskini(rs.getString("PEGAWAI_AKHIR"));
				hakmilik.setLokasi(rs.getString("LOKASI"));
				hakmilik.setNoPelan(rs.getString("NO_PELAN"));
				hakmilik.setNoSyit(rs.getString("NO_SYIT"));
				hakmilik.setNoPU(rs.getString("NO_PU"));
				
				hakmilik.setIdJenisLot(rs.getLong("ID_LOT"));
				
				kem.setIdKementerian(rs.getLong("ID_KEMENTERIAN"));				
				kem.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));				
				agensi.setIdAgensi(rs.getLong("ID_AGENSI"));
				agensi.setNamaAgensi(rs.getString("NAMA_AGENSI"));
				agensi.setKementerian(kem);
				hakmilik.setAgensi(agensi);
				
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
				
			}
			
		}catch(Exception e){
				throw new Exception(getIHTP().getErrorHTML(e.toString()));
			
	 	} finally {
	 		if (db != null) db.close();

	 	}
		return hakmilik;
	
	}	
	
	@Override
	public Vector<HakMilik> transferRecord(String idPermohonan) {
		Db db = null;
		Vector<HakMilik> v = new Vector<HakMilik>();
		try{
			db = new Db();
			FrmRekodUtilData frmRekodUtilData = FrmRekodUtilData.getInstance();

			Statement stmt = db.getStatement();
			ResultSet rst = stmt.executeQuery("SELECT A.ID_FAIL FROM TBLPERMOHONAN A WHERE ID_PERMOHONAN="+idPermohonan);
			long idFail=0;
			if(rst.next()){
				idFail = rst.getLong("ID_FAIL");
			}
			rst = stmt.executeQuery("SELECT A.ID_KEMENTERIAN FROM TBLPFDFAIL A WHERE ID_FAIL="+idFail);
			long idKementerian=0;
			if(rst.next()){
				idKementerian = rst.getLong("ID_KEMENTERIAN");
			}
			String sql ="SELECT A.ID_PERMOHONAN,A.ID_NEGERI,A.ID_DAERAH,A.ID_MUKIM,A.ID_JENISHAKMILIK" +
					" ,A.ID_LOT,A.NO_HAKMILIK," +
					" A.NO_LOT,A.ID_LUAS,A.PEGANGAN_HAKMILIK,A.ID_SUBKATEGORI,B.ID_AGENSI,A.CUKAI_TERKINI " +
					" ,B.NO_RUJUKAN_KJP,B.NO_RUJUKAN_PTG,B.NO_RUJUKAN_PTD,F.NO_FAIL " +
					" FROM TBLHTPHAKMILIKURUSAN A,TBLHTPPERMOHONAN B,TBLPERMOHONAN P,TBLPFDFAIL F " +
					" WHERE A.ID_PERMOHONAN="+idPermohonan+" AND B.ID_PERMOHONAN = A.ID_PERMOHONAN " +
							" AND B.ID_PERMOHONAN = P.ID_PERMOHONAN " +
							" AND P.ID_FAIL = F.ID_FAIL " +
							"";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				HakMilik temp = new HakMilik();
				HakmilikAgensi agensi = new HakmilikAgensi();
				HakmilikCukai cukai = new HakmilikCukai();
				cukai.setCukaiTerkini(rs.getDouble("CUKAI_TERKINI"));
				temp.setHakmilikCukai(cukai);
				agensi.setIdAgensi(rs.getLong("ID_AGENSI"));
				agensi.setIdKementerian(idKementerian);
				temp.setHakmilikAgensi(agensi);
				temp.setIdPermohonan(rs.getLong("ID_PERMOHONAN"));
				temp.setIdNegeri(rs.getLong("ID_NEGERI"));
				temp.setIdDaerah(rs.getLong("ID_DAERAH"));
				temp.setIdMukim(rs.getLong("ID_MUKIM"));
				temp.setIdJenisHakmilik(rs.getLong("ID_JENISHAKMILIK"));
				temp.setIdJenisLot(rs.getLong("ID_LOT"));
				temp.setNoHakmlik(rs.getString("NO_HAKMILIK"));
				temp.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				temp.setNoLot(rs.getString("NO_LOT"));
				temp.setIdLuas(rs.getLong("ID_LUAS"));
				temp.setNoFail(rs.getString("NO_FAIL"));
				temp.setNoFailPTG(rs.getString("NO_RUJUKAN_PTG"));
				temp.setNoFailPTD(rs.getString("NO_RUJUKAN_PTD"));
				temp.setNoFailKJP(rs.getString("NO_RUJUKAN_KJP"));
		    	String ph = frmRekodUtilData.getKodNegeri(String.valueOf(rs.getLong("ID_NEGERI")));
		    	ph += frmRekodUtilData.getKodDaerah(String.valueOf(rs.getLong("ID_DAERAH")));
		    	ph += frmRekodUtilData.getKodMukim(String.valueOf(rs.getLong("ID_MUKIM")));
				ph += frmRekodUtilData.getKodJenisHakmilik(String.valueOf(rs.getLong("ID_JENISHAKMILIK")));
				ph += Utils.isNull(rs.getString("NO_HAKMILIK"));
				//if(!data.get("noBangunan").equals("") && !data.get("noTingkat").equals("") && !data.get("noBangunan").equals("")){	
				//		ph += (String)data.get("noBangunan")+(String)data.get("noTingkat")+(String)data.get("noBangunan");
				//}
		    	
				temp.setPeganganHakmilik(ph);
				temp.setIdSubkategori(rs.getLong("ID_SUBKATEGORI"));
				v.addElement(temp);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.close();
		}
		//Insert into TBLHTPHAKMILIK
		doProcess(v);
		return v;
		
	}
	
	private void doProcess(Vector<HakMilik> v){
		if(v != null && !v.isEmpty()){
			for(HakMilik temp : v){
				copyToRecord(temp);
			}
		}
	}

	
	//Add by rosli 18/08/2010	
	//@Override
	public HakmilikAgensi kemaskiniHakmilikAgensi(HakmilikAgensi ha) throws Exception{
		Db db = null;
		Connection conn = null;
		String sql =null;
		HakmilikAgensi hakmilikAgensi = new HakmilikAgensi();
		try{
			//hakmilik = getHakmilik(idHakmilik);
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			 r.update("ID_HAKMILIK",String.valueOf(ha.getIdHakmilik()));
			 r.update("STATUS","Y");
			 r.add("ID_AGENSI",String.valueOf(ha.getIdHakmilikAgensi()));
			 r.add("ID_KEMENTERIAN",String.valueOf(ha.getIdKementerian()));
			 r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));			  
			 r.add("ID_KEMASKINI", r.unquote(String.valueOf(ha.getIdKemaskini())));			  
			 sql = r.getSQLUpdate("TBLHTPHAKMILIKAGENSI");
			 //
			 stmt.executeUpdate(sql);			 
			 conn.commit();
			
		}catch(Exception e){
			try{
				conn.rollback();
			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw new Exception("MASALAH KEMASKINI DATA[1] , SILA SEMAK METHOD kemaskiniHakmilikAgensi(ekptg.model.htp.rekod.HakmilikBean)");
			}
			e.printStackTrace();
			throw new Exception("MASALAH KEMASKINI DATA, SILA SEMAK METHOD kemaskiniHakmilikAgensi(ekptg.model.htp.rekod.HakmilikBean)");
		}
		finally{
			if(db != null)
				db.close();
		}
		
		return hakmilikAgensi;
	}
	
	//@Override
	public void kemaskiniHakmilikTambahSambungan(Hashtable data) throws Exception {
		Connection conn = null;
	    Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date(); 
		String currentDate = sdf.format(date);
	    String sql = "";
	    try{
			  db = new Db();
			  conn = db.getConnection();
			  conn.setAutoCommit(false);
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
		      
			  tambahHakmilikBaruById(data,conn);
			  
			  //KEMASKINI TBLHTPHAKMILIK			  
	    	  r.update("ID_HAKMILIK", data.get("idHakmilik"));
	    	  r.add("ID_NEGERI", data.get("socNegeriHR"));
	    	  r.add("ID_DAERAH", data.get("socDaerahHR"));
	    	  r.add("ID_MUKIM", data.get("socMukimHR"));
	    	  r.add("ID_JENISHAKMILIK", data.get("socJenisHakmilikHR"));
	    	  r.add("NO_HAKMILIK", data.get("txtNoHakmilik"));
	    	  //r.add("ID_LOT", data.get("socLotHR"));
	    	  //r.add("NO_LOT", data.get("txtNoLot"));
	    	  //convert date before add
			  //String tarikhTerima = (String)(data.get("txdTarikhTerima"));
			  //String txdTarikhTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";	    	  
			  //r.add("TARIKH_TERIMA",r.unquote(txdTarikhTerima));  	
			  //convert date before add
			  String tarikhDaftar = (String)data.get("txdTarikhDaftar");
			  String txdTarikhDaftar = "to_date('" + tarikhDaftar + "','dd/MM/yyyy')";	  			  			  
	    	  r.add("TARIKH_DAFTAR", r.unquote(txdTarikhDaftar));
	    	  r.add("NO_BANGUNAN", data.get("txtNoBangunan"));
	    	  r.add("NO_TINGKAT", data.get("txtNoTingkat"));
	    	  r.add("NO_PETAK", data.get("txtNoPetak"));
	    	  r.add("CUKAI", data.get("txtCukaiTahun"));
	    	  r.add("LOKASI", data.get("txtLokasi"));
	    	  
	    	  r.add("ID_LUAS_BERSAMAAN",2);
	    	  r.add("LUAS_BERSAMAAN", data.get("txtLuas"));
	    	  
	    	  r.add("ID_LUAS",data.get("socLuas"));
	    	  r.add("LUAS", data.get("txtLuasLama"));
	    	  
	    	  r.add("TARAF_HAKMILIK", data.get("socTaraf"));
	    	  r.add("ID_KATEGORI", data.get("socKategori"));
	    	  r.add("NO_PELAN", data.get("txtNoPelan"));
	    	  r.add("TEMPOH", data.get("txtTempoh"));
	    	  r.add("NO_FAIL_JOPA", data.get("txtNoFailJopa"));	    	  
	    	  //r.add("HAKMILIK_ASAL", data.get("txtHakmilikAsal"));
	    	  //r.add("CUKAI_TERKINI", data.get("txtCukaiTerkini"));
	    	  //convert date before add
	    	  if(data.get("txdTarikhLuput")!=""){
	    		  String tarikhLuput = (String)data.get("txdTarikhLuput");
	    		  String txdTarikhLuput = "to_date('" + tarikhLuput + "','dd/MM/yyyy')";	
	    		  r.add("TARIKH_LUPUT", r.unquote(txdTarikhLuput));
	    	  }
	    	  r.add("NO_PU", data.get("txtNoPu"));
	    	  //convert date before add
			  String tarikhWarta = (String)data.get("txdTarikhWarta");
			  String txdTarikhWarta = "to_date('" + tarikhWarta + "','dd/MM/yyyy')";		    	  
	    	  //r.add("TARIKH_WARTA", r.unquote(txdTarikhWarta));
	    	  r.add("KAWASAN_RIZAB", data.get("txtKawasanRizab"));
	    	  r.add("NO_SYIT", data.get("txtNoSyit"));
	    	  //2010/11/04
	    	  //r.add("NO_WARTA", data.get("txtNoWarta"));
	    	  r.add("NO_RIZAB", data.get("txtNoWarta"));
	    	  r.add("SEKATAN", data.get("txtSekatan"));	  
	    	  r.add("SYARAT", data.get("txtSyarat"));	  
	    	  r.add("HAKMILIK_BERIKUT", geTanah().getKodJenisHakmilik(String.valueOf(data.get("socJenisHakmilikBaru")))+ data.get("txtHakmilikBerikut"));
	    	  r.add("STATUS_SAH", data.get("socStatus"));
	    	  //convert date before add
			  String tarikhKemaskini = currentDate;
			  String txdTarikhKemaskini = "to_date('" + tarikhKemaskini + "','dd/MM/yyyy')";		    	  
	    	  r.add("TARIKH_KEMASKINI", r.unquote(txdTarikhKemaskini));
	    	  r.add("ID_RIZAB", data.get("socJenisRizab"));   
	    	  r.add("CATATAN", data.get("catatan"));   
	    	  r.add("STATUS_RIZAB",data.get("socRizab"));
	    	  r.add("ID_KEMASKINI",data.get("idKemaskini"));
	    	  r.add("KEGUNAAN_TANAH", data.get("txtKegunaanTanah"));
			
			  sql = r.getSQLUpdate("TBLHTPHAKMILIK");
			  myLog.info("sql update TBLHTPHAKMILIK:"+sql);
		      stmt.executeUpdate(sql);
		      
//		      //TBLHTPHAKMILIKCUKAI
//		      r = new SQLRenderer();
//		      if(data.get("txtCukaiSemasa").equals(data.get("txtCukaiTerkini"))){	
//		    	  String sqlCukai = "";
//		    	  r.update("ID_HAKMILIKCUKAI",r.unquote(String.valueOf(data.get("idHakmilikCukai")))); 
//		    	  r.add("CUKAI_TERKINI",data.get("txtCukaiTerkini"));
//		    	  r.add("ID_KEMASKINI",data.get("idKemaskini"));
//		    	  r.add("TARIKH_KEMASKINI",r.unquote("SYSDATE"));
//		    	  sqlCukai = r.getSQLUpdate("TBLHTPHAKMILIKCUKAI");
//		    	  //mylog.info("1. sql update TBLHTPHAKMILIKCUKAI:"+sql);
//		    	  stmt.executeUpdate(sqlCukai);
//
//		      }else{
//		    	  String sqlCukai = "";
//		    	  SQLRenderer rCukai = new SQLRenderer();
//		    	  rCukai.update("ID_HAKMILIKCUKAI",rCukai.unquote(String.valueOf(data.get("idHakmilikCukai")))); 
//		    	  rCukai.add("STATUS","N");
//		    	  rCukai.add("ID_KEMASKINI",rCukai.unquote(String.valueOf(data.get("idKemaskini"))));
//		    	  rCukai.add("TARIKH_KEMASKINI",rCukai.unquote("SYSDATE"));
//		    	  sqlCukai = rCukai.getSQLUpdate("TBLHTPHAKMILIKCUKAI");
//		    	  //mylog.info("1. sql update TBLHTPHAKMILIKCUKAI:"+sql);
//		    	  stmt.executeUpdate(sqlCukai);
//				
//		    	  String sqlCukaiBaru = "";
//		    	  SQLRenderer rc = new SQLRenderer();
//		    	  long idHakmilikCukai = DB.getNextID("TBLHTPHAKMILIKCUKAI_SEQ"); 
//		    	  rc.add("ID_HAKMILIKCUKAI",idHakmilikCukai); 
//		    	  rc.add("ID_HAKMILIK",data.get("idHakmilik")); 
//		    	  rc.add("KOD_BAYARAN_CUKAI","P");
//		    	  rc.add("CUKAI",data.get("txtCukaiSemasa"));
//		    	  rc.add("CUKAI_TERKINI",data.get("txtCukaiTerkini"));
//		    	  rc.add("STATUS","S");
//		    	  rc.add("ID_MASUK",data.get("idKemaskini"));
//		    	  rc.add("TARIKH_MASUK",r.unquote("SYSDATE"));
//		    	  rc.add("ID_KEMASKINI",data.get("idKemaskini"));
//		    	  rc.add("TARIKH_KEMASKINI",rc.unquote("SYSDATE"));
//		    	  sqlCukaiBaru = rc.getSQLInsert("TBLHTPHAKMILIKCUKAI");
//		    	  //mylog.info("2. sql update TBLHTPHAKMILIKCUKAI:"+sqlCukaiBaru);
//		    	  stmt.executeUpdate(sqlCukaiBaru);
//
//		      }
		      		      
		      conn.commit();

	    	}catch(Exception e){
	    		conn.rollback();
	    		e.printStackTrace();
	    		
	    	}finally {
		      if (db != null) db.close();
		      if (conn != null) conn.close();
		      
		    }	
	    	
    }
	
	private void tambahHakmilikBaruById(Hashtable data,Connection conn) throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date(); 
		String currentDate = sdf.format(date);
		String idHakmilikAsal = String.valueOf(data.get("idHakmilik"));
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			long idHakmilikBaru = DB.getNextID(db,"TBLHTPHAKMILIK_SEQ"); 
			
			//GET DATA LAMA FROM TBLHTPHAKMILIK
			sql = "SELECT A.ID_PERMOHONAN, A.PEGANGAN_HAKMILIK, A.ID_SUBKATEGORI " +
				" ,A.NO_HAKMILIK,LTRIM(A.NO_HAKMILIK,0) NO_HAKMILIKX0,A.ID_LOT " +
		      	" ,A.ID_LUAS_BERSAMAAN,A.LUAS,A.ID_NEGERI,NVL(A.STATUS_RIZAB,'T') STATUS_RIZAB " +
		      	" ,A.ID_DAERAH, A.ID_MUKIM, A.ID_JENISHAKMILIK, A.ID_LOT, A.ID_LUAS " +
		      	" ,A.ID_KATEGORI" +
		      	" ,A.ID_AGENSI, A.ID_KEMENTERIAN,A.KEGUNAAN_TANAH, A.CATATAN " +
		      	" ,( SELECT RJH.KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK RJH " +
		      	" WHERE RJH.ID_JENISHAKMILIK = A.ID_JENISHAKMILIK) KOD_JENIS_HAKMILIK " + //modified on 2010/08/16
		      	" FROM TBLHTPHAKMILIK A " +
		      	" WHERE  " +
		      	" A.ID_HAKMILIK = '"+idHakmilikAsal +"'";
			myLog.info("get data lama "+sql);
			ResultSet rs = stmt.executeQuery(sql);			
			if (rs.next()) {
				//CREATE REKOD BARU 			    
			    r.add("ID_HAKMILIK",idHakmilikBaru);
			    r.add("ID_PERMOHONAN",rs.getString("ID_PERMOHONAN"));
			    //r.add("PEGANGAN_HAKMILIK",FrmUtilData.getKodNegeri(rs.getString("ID_NEGERI"))+FrmUtilData.getKodDaerah(rs.getString("ID_DAERAH"))+FrmUtilData.getKodMukim(rs.getString("ID_MUKIM"))+data.get("txtKodSocJenisHakmilikBaru")+String.format("%08d",Integer.parseInt(String.valueOf(data.get("txtHakmilikBerikut")))));
			    r.add("PEGANGAN_HAKMILIK",FrmUtilData.getKodNegeri(rs.getString("ID_NEGERI"))+FrmUtilData.getKodDaerah(rs.getString("ID_DAERAH"))+FrmUtilData.getKodMukim(rs.getString("ID_MUKIM"))+data.get("txtKodSocJenisHakmilikBaru")+data.get("txtHakmilikBerikut"));
			    r.add("ID_SUBKATEGORI",rs.getString("ID_SUBKATEGORI"));
			    r.add("ID_NEGERI",rs.getString("ID_NEGERI"));
			    r.add("ID_DAERAH",rs.getString("ID_DAERAH"));
			    r.add("ID_MUKIM",rs.getString("ID_MUKIM"));
			    r.add("ID_JENISHAKMILIK",(String)data.get("socJenisHakmilikBaru"));
			    // By rosli add format 00000000,diguna semula pada 07/06/2010
			    r.add("NO_HAKMILIK", data.get("txtHakmilikBerikut"));
			    // tidak boleh format, kerana ada data yang dimasukkan adalah abjad 
			    //r.add("NO_HAKMILIK", String.format("%08d",Integer.parseInt(String.valueOf(data.get("txtHakmilikBerikut")))));
			    r.add("NO_LOT",(String)data.get("txtNoLot"));
			    //convert date before add
				String tarikhTerima = (String)(data.get("txdTarikhTerima"));
				String txdTarikhTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";	    	  
				r.add("TARIKH_TERIMA",r.unquote(txdTarikhTerima));  	
				//convert date before add
				String tarikhDaftar = (String)data.get("txdTarikhDaftar");
				String txdTarikhDaftar = "to_date('" + tarikhDaftar + "','dd/MM/yyyy')";	  			  			  
			    r.add("TARIKH_DAFTAR", r.unquote(txdTarikhDaftar));
			    r.add("CUKAI", data.get("txtCukaiTahun"));
			    r.add("LOKASI", data.get("txtLokasi"));
			   
			    r.add("TARAF_HAKMILIK", data.get("socTaraf")); 
			    // 28/02/2011
			    //r.add("ID_LOT", rs.getString("ID_LOT"));
			    r.add("ID_LOT", data.get("socLotHR"));
			    r.add("ID_KATEGORI", rs.getString("ID_KATEGORI"));
			    r.add("NO_PELAN", data.get("txtNoPelan"));
			    
			    r.add("ID_LUAS_BERSAMAAN",2);
			    r.add("LUAS_BERSAMAAN", data.get("txtLuas"));
		    	  
			    r.add("ID_LUAS",data.get("socLuas"));
			    r.add("LUAS", data.get("txtLuasLama"));
			    
			    r.add("TEMPOH", data.get("txtTempoh"));
			    r.add("NO_FAIL_JOPA", data.get("txtNoFailJopa"));	    	  
			    r.add("CUKAI_TERKINI", data.get("txtCukaiTerkini"));
			    //convert date before add
				String tarikhLuput = (String)data.get("txdTarikhLuput");
				String txdTarikhLuput = "to_date('" + tarikhLuput + "','dd/MM/yyyy')";	  			  			 
			    r.add("TARIKH_LUPUT", r.unquote(txdTarikhLuput));
			    r.add("NO_PU", data.get("txtNoPu"));
			    //convert date before add
				String tarikhWarta = (String)data.get("txdTarikhWarta");
				String txdTarikhWarta = "to_date('" + tarikhWarta + "','dd/MM/yyyy')";		    	  
			    r.add("TARIKH_WARTA", r.unquote(txdTarikhWarta));
			    r.add("KAWASAN_RIZAB", data.get("txtKawasanRizab"));
			    r.add("NO_SYIT", data.get("txtNoSyit"));
			    //2010/11/04
			    //r.add("NO_WARTA", data.get("txtNoWarta"));
			    r.add("NO_RIZAB", data.get("txtNoWarta"));
			    r.add("SEKATAN", data.get("txtSekatan"));	  
			    r.add("SYARAT", data.get("txtSyarat"));	  
			    r.add("HAKMILIK_ASAL", rs.getString("KOD_JENIS_HAKMILIK")+rs.getString("NO_HAKMILIKX0"));
			    //r.add("STATUS_SAH", "");
			    // by Rosli 03/05/2010
			    // r.add("STATUS_SAH", data.get("socStatus"));
			    // Azam change on 20/05/2010
			    //Default to D instead of ambil dari form, 
			    //sebab dalam form ialah S (Batal Sambungan
			    //Need to check if got any problems
			    r.add("STATUS_SAH","D");
			    //convert date before add
				String tarikhKemaskini = currentDate;
				String txdTarikhKemaskini = "to_date('" + tarikhKemaskini + "','dd/MM/yyyy')";		    	  
			    r.add("TARIKH_MASUK", r.unquote(txdTarikhKemaskini));
			    r.add("ID_MASUK",data.get("idKemaskini"));
			    r.add("ID_RIZAB", data.get("socJenisRizab"));    
			    r.add("STATUS_RIZAB", Utils.isNull(rs.getString("STATUS_RIZAB")));
			    // Tambahan field untuk simpan by Rosli 16/08/2010
			    //A.ID_AGENSI, A.ID_KEMENTERIAN,A.KEGUNAAN_TANAH, A.CATATAN 
			    r.add("ID_AGENSI", Utils.isNull(rs.getString("ID_AGENSI")));
			    r.add("ID_KEMENTERIAN", Utils.isNull(rs.getString("ID_KEMENTERIAN")));
			    r.add("KEGUNAAN_TANAH", Utils.isNull(rs.getString("KEGUNAAN_TANAH")));
			    r.add("CATATAN", Utils.isNull(rs.getString("CATATAN")));
			    sql = r.getSQLInsert("TBLHTPHAKMILIK");
			    myLog.info("addHakmilikBaruById:sql insert baru::"+sql);
				stmt.executeUpdate(sql);
			}
			//GET DATA LAMA FROM TBLHTPHAKMILIKAGENSI
			sql = "SELECT TBLHTPHAKMILIKAGENSI.ID_AGENSI, TBLHTPHAKMILIKAGENSI.ID_KEMENTERIAN " +
				" FROM TBLHTPHAKMILIKAGENSI "+
		      	" WHERE TBLHTPHAKMILIKAGENSI.ID_HAKMILIK = '"+idHakmilikAsal +"'";
			//
		    myLog.info("sql="+sql);
			ResultSet rs2 = stmt.executeQuery(sql);
			//KALO ADA ROW BRU INSERT
			if (rs2.next()){
				//CREATE REKOD BARU 
				r = new SQLRenderer();
			    long idHakmilikAgensi = DB.getNextID("TBLHTPHAKMILIKAGENSI_SEQ"); 
			    r.add("ID_HAKMILIKAGENSI",idHakmilikAgensi);
			    r.add("ID_HAKMILIK",idHakmilikBaru);
			    r.add("ID_MASUK",data.get("idKemaskini"));
			    r.add("ID_AGENSI",rs2.getString("ID_AGENSI"));
			    r.add("ID_KEMENTERIAN",rs2.getString("ID_KEMENTERIAN"));
			    r.add("TARIKH_MASUK",r.unquote("SYSDATE"));
			    sql = r.getSQLInsert("TBLHTPHAKMILIKAGENSI");
			    myLog.info("insert hakmilikagensi :"+sql);
			    stmt.executeUpdate(sql);
			}
			//rs2.next();		    
		    
			//GET DATA LAMA FROM TBLHTPHAKMILIKCUKAI
			sql = "SELECT NVL(TPC.CUKAI,0) CUKAI,NVL(TPC.CUKAI_TERKINI,0) CUKAI_TERKINI, " +
					"NVL(TPC.KOD_BAYARAN_CUKAI,'P') KOD_BAYARAN_CUKAI " +
					"FROM TBLHTPHAKMILIKCUKAI TPC "+
		      		"WHERE TPC.ID_HAKMILIK = ' "+idHakmilikAsal +"'";
			ResultSet rs3 = stmt.executeQuery(sql);
			//KALO ADA ROW BARU INSERT
			if(rs3.next()){
				//CREATE REKOD BARU 
				sql = "INSERT INTO TBLHTPHAKMILIKCUKAI (ID_HAKMILIK, ID_MASUK, " +
						"CUKAI_TERKINI, CUKAI, KOD_BAYARAN_CUKAI, STATUS, TARIKH_MASUK)  " +
						"VALUES ('"+idHakmilikBaru+"',"+data.get("idKemaskini")+", " +
								"'"+data.get("txtCukaiTerkini")+"', " +
								"'"+rs3.getString("CUKAI")+"', " +
								"'"+rs3.getString("KOD_BAYARAN_CUKAI")+"', 'S',  SYSDATE )";
				
				/*r = new SQLRenderer();
			    long idHakmilikCukai = DB.getNextID("TBLHTPHAKMILIKCUKAI_SEQ"); 
			    r.add("ID_HAKMILIKCUKAI",idHakmilikCukai);
			    r.add("ID_HAKMILIK",idHakmilikBaru);
			    r.add("ID_MASUK",data.get("idKemaskini"));
			    r.add("CUKAI_TERKINI",rs3.getString("CUKAI_TERKINI"));
			    r.add("CUKAI",rs3.getString("CUKAI"));
			    r.add("KOD_BAYARAN_CUKAI",rs3.getString("KOD_BAYARAN_CUKAI"));
			    r.add("STATUS","S");
			    r.add("TARIKH_MASUK",r.unquote("SYSDATE"));
			    sql = r.getSQLInsert("TBLHTPHAKMILIKCUKAI");
			    */
			    myLog.info("insert hakmilikcukai :"+sql);
			    stmt.executeUpdate(sql);
			}
		
			conn.commit();
			
		}catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	}	
	//1.
	//ekptg.model.htp.FrmUtilData
	//static String getKodJenisHakmilik(String)
	//----------------------------------------
	//2.
	// ekptg.model.htp.rekod.hamilikBean
	// String getKodJenisHakmilik(String)
	// Dibuat pada 20/10/2010 Oleh Mohamad Rosli
	// Bertujuan mendapatkan kod bagi jenis hakmilik melalui id_jenishakmilik
	public String getKodJenisHakmilik_(String idJenisHakmilik) throws Exception {
		String returnValue = "00";
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("kod_Jenis_Hakmilik");
			r.add("id_Jenishakmilik",idJenisHakmilik);
			sql = r.getSQLSelect("Tblrujjenishakmilik");
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				returnValue = rs.getString("kod_Jenis_Hakmilik");
			}
		} finally {
			if (db != null)	db.close();
		}
		return returnValue;
	}	
	
	
	public Vector getMaklumatImejByIdHakmilikDist(String id) throws Exception {
		Db db = null;
		Hashtable h;
		String sql = "";
		Vector listImejByIdHakmilik = null; 		
		try {
			db = new Db();
			listImejByIdHakmilik = new Vector();
			Statement stmt = db.getStatement();						
			sql =	"SELECT DISTINCT(A.ID_HAKMILIK) ID_HAKMILIK,B.RINGKASAN,B.PERIHAL_IMEJ "+
					" FROM TBLHTPHAKMILIK A, TBLHTPGAMBAR B "+
					" WHERE A.ID_HAKMILIK = B.ID_HAKMILIK "+
					" AND A.ID_HAKMILIK = '"+id +"'";
			ResultSet rs = stmt.executeQuery(sql);
		    int bil = 1;
		    int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil+".");
				h.put("idHakmilik", rs.getString("ID_HAKMILIK"));
				h.put("ringkasan", rs.getString("RINGKASAN")==null ? "" :rs.getString("RINGKASAN"));
				h.put("perihalImej", rs.getString("PERIHAL_IMEJ")==null ? "" :rs.getString("PERIHAL_IMEJ"));
				listImejByIdHakmilik.addElement(h);
		    	bil++;
		    	count++;
	      }		
			
		} finally {
			if (db != null) db.close();
			
		}
		return listImejByIdHakmilik;
		
	}	
	
	public boolean isHakmilikAgensi(String idHakmilik) throws Exception {
		Connection conn = null;
		Db db = null;
		boolean returnValue = false;
		SQLRenderer r = null;
 	    try {
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			r = new SQLRenderer();
			r.add("TPA.ID_HAKMILIKAGENSI");
			//r.add("TPH.ID_HAKMILIK",r.unquote("TPA.ID_HAKMILIK"));
			r.add("TPA.STATUS","Y");
			r.add("TPA.ID_HAKMILIK",r.unquote(idHakmilik));
			sql = r.getSQLSelect("TBLHTPHAKMILIKAGENSI TPA " +
				"");
//			myLog.info("isHakmilikAgensi:sql="+sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				returnValue = true;				
			}
			
		}catch(Exception e){
			throw new Exception(getIHTP().getErrorHTML(e.toString()));
			
	 	} finally {
	 		if (db != null) db.close();

	 	}
		return returnValue;
	
	}
	
		public boolean isHakmilik(String idNegeri, String idDaerah,String idMukim
			,String idJenisHakmilik, String noHakmilik
			,String bang, String ting, String petak
			) throws Exception {
		//myLog.info("isHakmilikWarta:"+idNegeri+","+ idDaerah+","+ idMukim
		//+","+idJenisHakmilik+","+ noHakmilik);
		Connection conn = null;
		Db db = null;
		boolean returnValue = false;
		SQLRenderer r = null;
 	    try {
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			r = new SQLRenderer();
			r.add("TPH.ID_HAKMILIK");
			//r.add("TPH.ID_HAKMILIK",r.unquote("TPA.ID_HAKMILIK"));
			r.add("TPH.ID_NEGERI",idNegeri);
			r.add("TPH.ID_DAERAH",idDaerah);
			r.add("TPH.ID_MUKIM",idMukim);
			r.add("ID_JENISHAKMILIK",idJenisHakmilik);
			r.add("LTRIM(TPH.NO_HAKMILIK,0)",noHakmilik);
			if(!bang.equals(""))
				r.add("TPH.NO_BANGUNAN",bang);
			if(!ting.equals(""))
				r.add("TPH.NO_TINGKAT",ting);
			if(!petak.equals(""))
				r.add("TPH.NO_PETAK",petak);
			sql = r.getSQLSelect("TBLHTPHAKMILIK TPH " +
				"");
			myLog.info("isHakmilik:sql="+sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				returnValue = true;				
			}
			
		}catch(Exception e){
			throw new Exception(getIHTP().getErrorHTML(e.toString()));
			
	 	} finally {
	 		if (db != null) db.close();

	 	}
		return returnValue;
	
	}

	public boolean isHakmilik(String idNegeri, String idDaerah,String idMukim
			,String idJenisHakmilik, String noHakmilik
			,String bang, String ting, String petak,String idLot, String noLot
			) throws Exception {
		//myLog.info("isHakmilikWarta:"+idNegeri+","+ idDaerah+","+ idMukim
		//+","+idJenisHakmilik+","+ noHakmilik);
		Connection conn = null;
		Db db = null;
		boolean returnValue = false;
		SQLRenderer r = null;
 	    try {
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			r = new SQLRenderer();
			r.add("TPH.ID_HAKMILIK");
			//r.add("TPH.ID_HAKMILIK",r.unquote("TPA.ID_HAKMILIK"));
			r.add("TPH.ID_NEGERI",idNegeri);
			r.add("TPH.ID_DAERAH",idDaerah);
			r.add("TPH.ID_MUKIM",idMukim);
			r.add("ID_JENISHAKMILIK",idJenisHakmilik);
			r.add("LTRIM(TPH.NO_HAKMILIK,0)",noHakmilik);
			r.add("ID_LOT",idLot);
			r.add("TPH.NO_LOT",noLot);
			if(!bang.equals(""))
				r.add("TPH.NO_BANGUNAN",bang);
			if(!ting.equals(""))
				r.add("TPH.NO_TINGKAT",ting);
			if(!petak.equals(""))
				r.add("TPH.NO_PETAK",petak);
			sql = r.getSQLSelect("TBLHTPHAKMILIK TPH " +
				"");
			myLog.info("isHakmilik:sql="+sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				returnValue = true;				
			}
			
		}catch(Exception e){
			throw new Exception(getIHTP().getErrorHTML(e.toString()));
			
	 	} finally {
	 		if (db != null) db.close();

	 	}
		return returnValue;
	
	}
		
	public String getSQLHakmilik(String idHakmilik){
		String sql = "";
		sql = "SELECT F.NO_FAIL "+
		" ,TPH.ID_HAKMILIK " +
		" ,NVL(RL.KETERANGAN,'0') KOD_LOT "+
		" ,TPH.ID_LOT,TPH.NO_LOT "+
		" ,TPH.ID_NEGERI,TPH.ID_DAERAH,TPH.ID_MUKIM "+
		" ,RN.NAMA_NEGERI, RD.NAMA_DAERAH, RM.NAMA_MUKIM "+
		" ,TPH.LOKASI "+
		" ,TPH.CATATAN ,TPH.KEGUNAAN_TANAH "+
		" ,TPH.ID_KATEGORI,TPH.SYARAT,TPH.SEKATAN "+
		" ,TPH.NO_PELAN,TPH.NO_SYIT,TPH.NO_PU "+
		" ,TPH.NO_FAIL_PTG,TPH.NO_FAIL_PTD,TPH.NO_FAIL_KJP "+
		" ,TPH.ID_LUAS,NVL(TPH.LUAS,0) LUAS,TPH.ID_LUAS_BERSAMAAN,NVL(TPH.LUAS_BERSAMAAN,0) LUAS_BERSAMAAN "+
		" ,TO_CHAR(TPH.TARIKH_TERIMA,'DD/MM/YYYY') TARIKH_TERIMA "+
		" ,TPH.NO_WARTA,TO_CHAR(TPH.TARIKH_WARTA,'DD/MM/YYYY') TARIKH_WARTA "+
		" ,TPH.ID_JENISHAKMILIK,UPPER(TPH.NO_HAKMILIK) NO_HAKMILIK "+
		" ,TPH.NO_BANGUNAN,TPH.NO_TINGKAT,TPH.NO_PETAK "+
		" ,RJH.KETERANGAN,RJH.KOD_JENIS_HAKMILIK KOD_KETERANGAN "+
		" ,TO_CHAR(TPH.TARIKH_DAFTAR,'DD/MM/YYYY') TARIKH_DAFTAR "+
		" ,TPH.HAKMILIK_ASAL "+
		" ,TPH.TARAF_HAKMILIK,TPH.TEMPOH "+
		" ,TO_CHAR(TPH.TARIKH_LUPUT,'DD/MM/YYYY') TARIKH_LUPUT "+
		" ,NVL(TPH.CUKAI,0) CUKAI "+
		" ,TPH.ID_RIZAB,TPH.STATUS_RIZAB,TPH.NO_RIZAB,TPH.KAWASAN_RIZAB, TPH.TARIKH_RIZAB "+
		" ,TPH.TARIKH_KEMASKINI "+
		" ,(SELECT USER_NAME FROM USERS WHERE USER_ID=TPH.ID_KEMASKINI) PEGAWAI_AKHIR "+
		" ,CASE "+
		"	WHEN P.TUJUAN IS NULL THEN F.TAJUK_FAIL "+
		" 	WHEN P.TUJUAN IS NOT NULL THEN P.TUJUAN "+
		" 	ELSE '' "+
		" END TUJUAN "+
		" ,RU.NAMA_URUSAN "+
		" ,RK.NAMA_KEMENTERIAN,RA.NAMA_AGENSI "+
		" ,NVL(TPH.ID_KEMENTERIAN,0) ID_KEMENTERIAN,TPH.ID_AGENSI " +
		" ,P.ID_PERMOHONAN, F.ID_FAIL "+
//		" -- "+
		" FROM "+
//		" -- "+
		" TBLHTPHAKMILIK TPH "+
		" ,TBLRUJDAERAH RD "+
		" ,TBLRUJMUKIM RM "+
		" ,TBLRUJNEGERI RN "+
		" ,TBLPERMOHONAN P "+
		" ,TBLPFDFAIL F "+
		" ,TBLHTPHAKMILIKAGENSI TPHA,TBLRUJKEMENTERIANMAPPING RKME "+
//		" --,TBLRUJAGENSIMAPPING RAME "+
		" ,TBLRUJKEMENTERIAN RK,TBLRUJAGENSI RA " +
		" ,TBLRUJLOT RL " +
		" ,TBLRUJJENISHAKMILIK RJH " +
		" ,TBLRUJURUSAN RU "+
//		" -- "+
		" WHERE P.ID_PERMOHONAN = TPH.ID_PERMOHONAN "+
		" AND P.ID_FAIL = F.ID_FAIL "+
		" AND TPH.ID_MUKIM = RM.ID_MUKIM "+
		" AND RM.ID_DAERAH = RD.ID_DAERAH "+
		" AND RD.ID_NEGERI = RN.ID_NEGERI "+
//		" -- "+
		" AND TPH.ID_HAKMILIK = TPHA.ID_HAKMILIK AND TPHA.STATUS = 'Y' "+
		" AND TPHA.ID_KEMENTERIAN = RKME.ID_KEMENTERIANLAMA AND RKME.STATUS = 'A' "+
		" AND TPH.ID_KEMENTERIAN = RK.ID_KEMENTERIAN "+
		" AND TPHA.ID_AGENSI = RA.ID_AGENSI "+
		" AND TPH.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK "+
		" AND TPH.ID_LOT = RL.ID_LOT " +
		" AND F.ID_URUSAN = RU.ID_URUSAN "+
		" AND TPH.ID_HAKMILIK ='"+ idHakmilik +"'" +
		"";
		return sql;  
		
	}
	
	public String getSQLHakmilikPermohonan(String idPermohonan){
		String sql = "";
		sql = "SELECT F.NO_FAIL "+
		" ,TPH.ID_HAKMILIK "+
		" ,TPH.NO_FAIL_PTG,TPH.NO_FAIL_PTD,TPH.NO_FAIL_KJP "+
		" ,UPPER(TPH.NO_HAKMILIK) NO_HAKMILIK,TPH.NO_WARTA "+
		" ,TPH.NO_BANGUNAN,TPH.NO_TINGKAT,TPH.NO_PETAK "+
		" ,TPH.ID_LOT,TPH.NO_LOT "+
		" ,TPH.HAKMILIK_ASAL,TPH.CATATAN "+
		" ,TPH.KEGUNAAN_TANAH "+
		" ,TPH.ID_RIZAB "+
		" ,TPH.ID_NEGERI,TPH.ID_DAERAH,TPH.ID_MUKIM,TPH.ID_JENISHAKMILIK "+
		" ,RN.NAMA_NEGERI, RD.NAMA_DAERAH, RM.NAMA_MUKIM "+
		" ,TO_CHAR(TPH.TARIKH_TERIMA,'DD/MM/YYYY') TARIKH_TERIMA "+
		" ,TO_CHAR(TPH.TARIKH_DAFTAR,'DD/MM/YYYY') TARIKH_DAFTAR "+
		" ,TPH.TARAF_HAKMILIK,TPH.TEMPOH "+
		" ,TO_CHAR(TPH.TARIKH_LUPUT,'DD/MM/YYYY') TARIKH_LUPUT "+
		" ,NVL(TPH.CUKAI,0) CUKAI "+
		" ,TPH.LOKASI  "+
		" ,TPH.ID_LUAS,NVL(TPH.LUAS,0) LUAS,TPH.ID_LUAS_BERSAMAAN,NVL(TPH.LUAS_BERSAMAAN,0) LUAS_BERSAMAAN  "+
		" ,TPH.STATUS_RIZAB,TPH.NO_RIZAB "+
		" ,TO_CHAR(TPH.TARIKH_WARTA,'DD/MM/YYYY') TARIKH_WARTA,TPH.KAWASAN_RIZAB "+
		" ,TPH.ID_KATEGORI,TPH.SYARAT,TPH.SEKATAN "+
		" ,TPH.NO_PELAN,TPH.NO_SYIT,TPH.NO_PU "+
		" ,TPH.TARIKH_KEMASKINI "+
		" ,(SELECT USER_NAME FROM USERS WHERE USER_ID=TPH.ID_KEMASKINI) PEGAWAI_AKHIR "+
//		" ,P.TUJUAN "+
		" ,CASE "+
		" WHEN P.TUJUAN IS NULL THEN F.TAJUK_FAIL "+
		" WHEN P.TUJUAN IS NOT NULL THEN P.TUJUAN "+
		" ELSE '' "+
		" END TUJUAN "+
		" ,(    SELECT RJHI.KETERANGAN FROM TBLRUJJENISHAKMILIK RJHI  "+
		"     WHERE RJHI.ID_JENISHAKMILIK = TPH.ID_JENISHAKMILIK "+
		" ) KETERANGAN "+
		" ,(     SELECT RUI.NAMA_URUSAN  "+
		"       FROM TBLRUJURUSAN RUI,TBLPFDFAIL FI "+
		"     WHERE FI.ID_URUSAN = RUI.ID_URUSAN "+
		"    AND FI.ID_FAIL = F.ID_FAIL "+
		" ) NAMA_URUSAN "+
		" ,RK.NAMA_KEMENTERIAN,RA.NAMA_AGENSI "+
		" ,NVL(RK.ID_KEMENTERIAN,0) ID_KEMENTERIAN,RA.ID_AGENSI "+
//		" -- "+
		" FROM "+
//		" -- "+
		" TBLHTPHAKMILIK TPH "+
		" ,TBLRUJDAERAH RD "+
		" ,TBLRUJMUKIM RM "+
		" ,TBLRUJNEGERI RN "+
		" ,TBLPERMOHONAN P "+
		" ,TBLPFDFAIL F "+
		" ,TBLHTPHAKMILIKAGENSI TPHA,TBLRUJKEMENTERIANMAPPING RKME "+
//		" --,TBLRUJAGENSIMAPPING RAME "+
		" ,TBLRUJKEMENTERIAN RK,TBLRUJAGENSI RA "+
//		" -- "+
		" WHERE P.ID_PERMOHONAN = TPH.ID_PERMOHONAN "+
		" AND P.ID_FAIL = F.ID_FAIL "+
		" AND TPH.ID_MUKIM = RM.ID_MUKIM "+
		" AND RM.ID_DAERAH = RD.ID_DAERAH "+
		" AND RD.ID_NEGERI = RN.ID_NEGERI "+
//		" -- "+
		" AND TPH.ID_HAKMILIK = TPHA.ID_HAKMILIK AND TPHA.STATUS = 'Y' "+
		" AND TPHA.ID_KEMENTERIAN = RKME.ID_KEMENTERIANLAMA AND RKME.STATUS = 'A' "+
		" AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN "+
		" AND TPHA.ID_AGENSI = RA.ID_AGENSI "+
//		" --AND TPHA.ID_AGENSI = RAME.ID_AGENSILAMA AND RAME.STATUS = 'A' "+
//		" --AND RAME.ID_AGENSIBARU = RA.ID_AGENSI "+
//		" AND TPH.ID_HAKMILIK ='"+ idHakmilik +"'";
		" AND TPH.ID_PERMOHONAN = '"+idPermohonan+"' " +
		" AND ROWNUM<=1 ORDER BY TPH.TARIKH_MASUK";
		return sql;
		
	}
	

	public HakMilik kemaskini(HakMilik hakmilikBaru) throws Exception {
	    Db dbHakmilik = null;
	    String sql = "";
	    try {
	    	  dbHakmilik = new Db();
			  Statement stmtHakmilik = dbHakmilik.getStatement();
			  SQLRenderer rHakmilik = new SQLRenderer();
			  rHakmilik.update("ID_HAKMILIK",hakmilikBaru.getIdHakmilik());
	    	  //convert date before add
			  String tarikhTerima = hakmilikBaru.getTarikhTerimaStr();
			  String txdTarikhTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";
			  rHakmilik.add("TARIKH_TERIMA",rHakmilik.unquote(txdTarikhTerima));
			  String tarikhWarta= hakmilikBaru.getTarikhWartaStr();
			  String txdTarikhWarta = "to_date('" + tarikhWarta + "','dd/MM/yyyy')";
			  rHakmilik.add("TARIKH_WARTA",rHakmilik.unquote(txdTarikhWarta));
			  rHakmilik.add("NO_WARTA",hakmilikBaru.getNoWarta());
			  rHakmilik.add("ID_LOT", "1");
			  rHakmilik.add("NO_LOT", hakmilikBaru.getNoLot());
			  rHakmilik.add("ID_LUAS", hakmilikBaru.getIdLuas());
			  rHakmilik.add("LUAS", hakmilikBaru.getLuasString());

			  rHakmilik.add("ID_LUAS_BERSAMAAN","2");
			  rHakmilik.add("LUAS_BERSAMAAN", hakmilikBaru.getLuasBersamaan());

			  rHakmilik.add("NO_PELAN", hakmilikBaru.getNoPelan());
			  rHakmilik.add("NO_PU", hakmilikBaru.getNoPU());
			  rHakmilik.add("NO_SYIT", hakmilikBaru.getNoSyit());
			  rHakmilik.add("LOKASI", hakmilikBaru.getLokasi());
			  rHakmilik.add("NO_FAIL_JOPA", hakmilikBaru.getNoFailJOPA());
			  rHakmilik.add("STATUS_SAH", hakmilikBaru.getStatusSah());
			  rHakmilik.add("KEGUNAAN_TANAH", hakmilikBaru.getKegunaan());
			  rHakmilik.add("CATATAN", hakmilikBaru.getCatatan());
			  if (hakmilikBaru.getTarikhKemaskiniStr().equals(""))
				  rHakmilik.add("TARIKH_KEMASKINI", rHakmilik.unquote("sysdate"));
			  else
				  rHakmilik.add("TARIKH_KEMASKINI",hakmilikBaru.getTarikhKemaskiniStr());
			  
	    	  //convert date before add	  
			  rHakmilik.add("ID_KEMASKINI", hakmilikBaru.getIdMasuk());
			  sql = rHakmilik.getSQLUpdate("TBLHTPHAKMILIK");
			  myLog.info(sql);
			  stmtHakmilik.executeUpdate(sql);

	    }finally {
		    if (dbHakmilik != null) dbHakmilik.close();
	    }
		Db dbHakmilikPerihal = new Db();
		String sqlHakmilikPerihal = "";
		try{
		     Statement stmtHakmilikPerihal = dbHakmilikPerihal.getStatement();
			 SQLRenderer rHakmilikPerihal = new SQLRenderer();
			 rHakmilikPerihal.update("ID_HAKMILIK", hakmilikBaru.getIdHakmilik());
			 rHakmilikPerihal.add("KEGUNAAN_TANAH", hakmilikBaru.getKegunaan());
			 sqlHakmilikPerihal = rHakmilikPerihal.getSQLUpdate("TBLHTPHAKMILIKPERIHAL");
			 stmtHakmilikPerihal.executeUpdate(sqlHakmilikPerihal);

		}finally {
			if (dbHakmilikPerihal != null)dbHakmilikPerihal.close();
		}
		return getIUtilRekod().getTanah(String.valueOf(hakmilikBaru.getIdHakmilik()));

	}
	
	private ITanah geTanah(){
		if(iTanah== null)
			iTanah = new TanahBean();
		return iTanah;
	}
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}
	
	private IHTPUtilitiRekod getIUtilRekod(){
	if (iUtilRekod == null){
		iUtilRekod = new HTPUtilitiRekodBean();
	}
	return iUtilRekod;
	

}	

}
