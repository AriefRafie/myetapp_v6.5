package ekptg.model.htp.rekod;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.entities.Agensi;
import ekptg.model.entities.Daerah;
import ekptg.model.entities.Kementerian;
import ekptg.model.entities.Mukim;
import ekptg.model.entities.Negeri;
import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.entities.Tblrujlot;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.HakmilikCukai;
import ekptg.model.htp.entity.HakmilikStrata;
import ekptg.model.htp.entity.StatusSah;

public class HTPUtilitiRekodBean implements IHTPUtilitiRekod {
	
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.rekod.HTPUtilitiRekodBean.class);
 	private IHtp iHTP = null;  
	
	/*
	 * Dibuat oleh Mohamad Rosli 14/12/2012(non-Javadoc)
	 * @see ekptg.model.htp.rekod.IHTPUtilitiRekod#kemaskiniPerolehan(java.lang.String, java.lang.String)
	 */
	
	public void kemaskiniPerolehan(String idHakmilik, String idPermohonan, String noFail){
		Db db = null;
		Connection conn = null;
		String sql =null;
		try{
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_HAKMILIK",idHakmilik);
			r.add("ID_PERMOHONAN",idPermohonan);
			r.add("NO_FAIL",noFail);
			sql = r.getSQLUpdate("TBLHTPHAKMILIK");
			myLog.info(sql);
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
		
	}
	
	public HakMilik getTanah(String idHakmilik){
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
			sql = r.getSQLSelect("TBLHTPHAKMILIK TPH" +
				",TBLRUJAGENSI RA" +
				",TBLRUJKEMENTERIAN RK" +
				",TBLRUJMUKIM RM,TBLRUJDAERAH RD,TBLRUJNEGERI RN,TBLRUJJENISHAKMILIK RJH " +
				",TBLHTPHAKMILIKAGENSI TPA " +
				"");
			myLog.info("198,getTanah:sql="+sql);
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
//	
//	private IHtp getIHTP(){
//		if(iHTP== null)
//			iHTP = new HtpBean();
//		return iHTP;
//	}
	
	
}
