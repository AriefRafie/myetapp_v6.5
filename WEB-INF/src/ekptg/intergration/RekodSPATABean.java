package ekptg.intergration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.model.entities.Agensi;
import ekptg.model.entities.Daerah;
import ekptg.model.entities.Kementerian;
import ekptg.model.entities.Mukim;
import ekptg.model.entities.Negeri;
import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.entities.Tblrujlot;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.HakmilikAgensi;
import ekptg.model.htp.entity.HakmilikCukai;
import ekptg.model.htp.entity.HakmilikStrata;
import ekptg.model.htp.entity.HakmilikUrusanTanah;
import ekptg.model.htp.entity.StatusSah;
//import ekptg.model.htp.rekod.IHakmilikRizab;

public class RekodSPATABean implements IRekodSPATA {
	//private IHakmilikRizab iHakmilikRizab = null;
	private static Logger myLog = Logger.getLogger(ekptg.intergration.RekodSPATABean.class);

	@Override
	public Vector getSenaraiTanah(String dari, String hingga) {
		Vector v = new Vector();
		Db db = null;
		Connection conn = null;		
		String bulanDari = "01";
		String tahunDari = "1900";
		String bulanHingga = "01";
		String tahunHingga = "1900";
		try{
			//myLog.info(dari+","+ hingga);
			//myLog.info(dari.substring(3,5)+","+ dari.substring(6,10));
			//myLog.info(hingga.substring(3,5)+","+ hingga.substring(6,10));
			bulanDari = dari.substring(3,5);
			bulanHingga = hingga.substring(3,5);
			tahunDari = dari.substring(6,10);
			tahunHingga = hingga.substring(6,10);
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			String sql = "" +
					" SELECT " +
					" T.ID_HAKMILIK, T.ID_PERMOHONAN, T.PEGANGAN_HAKMILIK, T.NO_HAKMILIK,RK.KOD_DDSA KOD_DDSA_KEMENTERIAN " +
					" ,RK.NAMA_KEMENTERIAN,RA.KOD_DDSA KOD_DDSA_AGENSI,  RA.NAMA_AGENSI, RN.KOD_NEGERI,RN.NAMA_NEGERI " +
					" ,RD.NAMA_DAERAH, RM.NAMA_MUKIM, T.LOKASI, T.NO_LOT, T.ID_LOT "+
			 		" ,LOT.KETERANGAN, T.KEGUNAAN_TANAH,T.LUAS, T.ID_LUAS, T.LUAS_BERSAMAAN " +
			 		" ,T.NO_WARTA" +
			 		" ,NVL(TO_CHAR(T.TARIKH_WARTA,'dd/mm/yyyy'),'01/01/1900') TARIKH_WARTA " +
			 		" ,T.NO_SYIT,T.NO_PELAN, T.SYARAT " +
			 		" ,T.SEKATAN, T.CUKAI,T.CUKAI_TERKINI,T.STATUS_GERAN" +
			 		" ,NVL(TO_CHAR(T.TARIKH_TERIMA,'dd/mm/yyyy'),'01/01/1900') TARIKH_TERIMA " +
			 		" ,T.ID_KATEGORI,T.ID_SUBKATEGORI, T.ID_RIZAB, T.NO_RIZAB,T.STATUS_RIZAB " +
			 		" ,T.KAWASAN_RIZAB" +
			 		" ,NVL(TO_CHAR(T.TARIKH_RIZAB,'dd/mm/yyyy'),'01/01/1900') TARIKH_RIZAB " +
			 		", T.NO_BANGUNAN, T.NO_TINGKAT,T.NO_PETAK " +
			 		" ,T.STATUS_SAH" +
			 		" ,NVL(TO_CHAR(T.TARIKH_DAFTAR,'dd/mm/yyyy'),'01/01/1900') TARIKH_DAFTAR " +
			 		" ,NVL(TO_CHAR(T.TARIKH_LUPUT,'dd/mm/yyyy'),'01/01/1900') TARIKH_LUPUT " +
			 		" ,T.TEMPOH, T.NO_PU " +
			 		" ,T.NO_FAIL_HAKMILIK " +
			 		" ,NVL( " +
			 		"     (SELECT KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK WHERE ID_JENISHAKMILIK  = T.ID_JENISHAKMILIK ) " +
			 		"   ,'TIADA MAKLUMAT' " +
			 		"   ) KOD_JENIS_HAKMILIK  " +			 		
			 		" ,NVL( " +
			 		"     (SELECT KETERANGAN FROM TBLRUJJENISHAKMILIK WHERE ID_JENISHAKMILIK  = T.ID_JENISHAKMILIK ) " +
			 		"   ,'TIADA MAKLUMAT' " +
			 		"   ) JENIS_HAKMILIK  " +
			 		" ,T.TARAF_HAKMILIK, T.HAKMILIK_ASAL, T.HAKMILIK_BERIKUT " +
			 		" ,T.PEGANGAN_HAKMILIK_TUKARGANTI,T.STATUS_SWASTA" +
			 		" ,NVL(TO_CHAR(T.TARIKH_SWASTA,'dd/mm/yyyy'),'01/01/1900') TARIKH_SWASTA " +
			 		" ,T.STATUS_PAJAKAN" +
			 		" ,NVL(TO_CHAR(T.TARIKH_MOHON_PAJAKAN,'dd/mm/yyyy'),'01/01/1900') TARIKH_MOHON_PAJAKAN " +
			 		" ,T.STATUS_SEWA" +
			 		" ,NVL(TO_CHAR(T.TARIKH_MOHON_SEWA,'dd/mm/yyyy'),'01/01/1900') TARIKH_MOHON_SEWA " +
			 		" ,T.STATUS_PELEPASAN" +
			 		" ,NVL(TO_CHAR(T.TARIKH_MOHON_PELEPASAN,'dd/mm/yyyy'),'01/01/1900') TARIKH_PELEPASAN " +
			 		" ,T.CATATAN " +
			 		" ,NVL(TO_CHAR(T.TARIKH_MASUK,'dd/mm/yyyy HH:MI:SS'),'01/01/1900 00:00:00') TARIKH_MASUK " +
			 		" ,NVL(TO_CHAR(T.TARIKH_KEMASKINI,'dd/mm/yyyy HH:MI:SS'),'01/01/1900 00:00:00') TARIKH_KEMASKINI " +
			 		" FROM TBLHTPHAKMILIK T " +
			 		" , TBLRUJKEMENTERIAN RK,TBLRUJAGENSI RA " +
			 		" , TBLRUJNEGERI RN,TBLRUJDAERAH RD, TBLRUJMUKIM RM " +
			 		" , TBLRUJLOT LOT " +
			 		" WHERE RK.ID_KEMENTERIAN = T.ID_KEMENTERIAN " +
			 		" AND RA.ID_AGENSI = T.ID_AGENSI " +
			 		" AND T.ID_NEGERI = RN.ID_NEGERI " +
			 		" AND T.ID_DAERAH = RD.ID_DAERAH " +
			 		" AND T.ID_MUKIM = RM.ID_MUKIM " +
			 		" AND T.ID_LOT = LOT.ID_LOT " +   
			 		//" AND TO_CHAR(T.TARIKH_KEMASKINI,'dd') BETWEEN '02' AND '04' " +
			 		//" AND TO_CHAR(T.TARIKH_KEMASKINI,'yyyy') BETWEEN '2011' AND '2012' " +
			 		" AND TO_CHAR(T.TARIKH_KEMASKINI,'mm') BETWEEN '"+bulanDari+"' AND '"+bulanHingga+"' " +
			 		" AND TO_CHAR(T.TARIKH_KEMASKINI,'yyyy') BETWEEN '"+tahunDari+"' AND '"+tahunDari+"' " +
					" ORDER BY T.TARIKH_KEMASKINI DESC";
			myLog.info(sql);
	      	int bil = 1;
			ResultSet rs = stmt.executeQuery(sql);			
			while(rs.next()){
				HakMilik hakmilik = new HakMilik();
				Agensi agensi = new Agensi();
				Kementerian kem = new Kementerian();
				Negeri negeri = new Negeri();
				Daerah daerah = new Daerah();
				Mukim mukim = new Mukim();
				Tblrujlot lot = new Tblrujlot();
				HakmilikCukai cukai = new HakmilikCukai();
				HakmilikStrata strata = new HakmilikStrata();
				StatusSah ss = new StatusSah();
				Tblrujjenishakmilik jh = new Tblrujjenishakmilik();
				HakmilikUrusanTanah hUrusan = new HakmilikUrusanTanah();
				
				hakmilik.setIdHakmilik(rs.getLong("ID_HAKMILIK"));
				hakmilik.setIdPermohonan(rs.getLong("ID_PERMOHONAN"));
				hakmilik.setPeganganHakmilik(rs.getString("PEGANGAN_HAKMILIK"));
				hakmilik.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				hakmilik.setNoHakmlik(rs.getString("NO_HAKMILIK"));
				kem.setKodKementerian(rs.getString("KOD_DDSA_KEMENTERIAN"));
				kem.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));
				agensi.setKementerian(kem);
				agensi.setKodAgensi(rs.getString("KOD_DDSA_AGENSI"));
				agensi.setNamaAgensi(rs.getString("NAMA_AGENSI"));
				hakmilik.setAgensi(agensi);
				//
				negeri.setKodNegeri(rs.getString("KOD_NEGERI"));
				negeri.setNamaNegeri(rs.getString("NAMA_NEGERI"));
				hakmilik.setNegeri(negeri);
				//
				daerah.setNamaDaerah(rs.getString("NAMA_DAERAH"));
				hakmilik.setDaerah(daerah);
				//
				mukim.setNamaMukim(rs.getString("NAMA_MUKIM"));
				hakmilik.setMukim(mukim);
				//
				hakmilik.setLokasi(rs.getString("LOKASI"));
				hakmilik.setNoLot(rs.getString("NO_LOT"));
				lot.setIdLot(rs.getLong("ID_LOT"));
				lot.setKeterangan(rs.getString("KETERANGAN"));
				hakmilik.setRujLot(lot);
				//
				hakmilik.setKegunaan(rs.getString("KEGUNAAN_TANAH"));
				hakmilik.setLuasString(rs.getString("LUAS"));
				hakmilik.setIdLuas(rs.getLong("ID_LUAS"));
				hakmilik.setLuasBersamaan(rs.getDouble("LUAS_BERSAMAAN"));
				hakmilik.setNoWarta(rs.getString("NO_WARTA"));
				hakmilik.setTarikhWarta(new Date(rs.getString("TARIKH_WARTA")));
				hakmilik.setNoSyit(rs.getString("NO_SYIT"));
				hakmilik.setNoPelan(rs.getString("NO_PELAN"));
				hakmilik.setSyarat(rs.getString("SYARAT"));
				hakmilik.setSekatan(rs.getString("SEKATAN"));
				//
				cukai.setCukai(rs.getDouble("CUKAI"));
				cukai.setCukaiTerkini(rs.getDouble("CUKAI_TERKINI"));
				hakmilik.setHakmilikCukai(cukai);
				hakmilik.setStatusGeran(rs.getString("STATUS_GERAN"));
				hakmilik.setTarikhTerima(new Date(rs.getString("TARIKH_TERIMA")));
				hakmilik.setIdKategori(rs.getLong("ID_KATEGORI"));
				hakmilik.setIdSubkategori(rs.getLong("ID_SUBKATEGORI"));
				hakmilik.setIdRizab(rs.getString("ID_RIZAB"));
				hakmilik.setNoRizab(rs.getString("NO_RIZAB"));
				hakmilik.setStatusRizab(rs.getString("STATUS_RIZAB"));
				hakmilik.setKawasanRizab(rs.getString("KAWASAN_RIZAB"));
				hakmilik.setTarikhRizab(new Date(rs.getString("TARIKH_RIZAB")));
				strata.setBangunan(rs.getString("NO_BANGUNAN"));
				strata.setTingkat(rs.getString("NO_TINGKAT"));
				strata.setPetak(rs.getString("NO_PETAK"));		
				hakmilik.setStrata(strata);
				hakmilik.setStatusSah(rs.getString("STATUS_SAH"));
				ss.setKod(rs.getString("STATUS_SAH"));
				//ss.setKeterangan(rs.getString("STATUS_SAHKETERANGAN"));
				hakmilik.setStatus(ss);	
				hakmilik.setTarikhDaftar(new Date(rs.getString("TARIKH_DAFTAR")));
				hakmilik.setTarikhLuput(new Date(rs.getString("TARIKH_LUPUT")));
				hakmilik.setTempoh(rs.getString("TEMPOH"));
				hakmilik.setNoPU(rs.getString("NO_PU"));				
				hakmilik.setNoFailHakmilik(rs.getString("NO_FAIL_HAKMILIK"));
				//
				//jh.setIdJenishakmilik(rs.getLong("ID_JENISHAKMILIK"));
				jh.setKodJenisHakmilik(!rs.getString("KOD_JENIS_HAKMILIK").equals("00")?rs.getString("KOD_JENIS_HAKMILIK"):"");
				jh.setKeterangan(rs.getString("JENIS_HAKMILIK"));
				hakmilik.setRujJenisHakmilik(jh);
				//
				hakmilik.setTaraf(rs.getString("TARAF_HAKMILIK"));
				hakmilik.setNoHakmilikAsal(rs.getString("HAKMILIK_ASAL"));
				hakmilik.setNoHakmilikBerikut(rs.getString("HAKMILIK_BERIKUT"));
				//
				hUrusan.setHakmilikTukarGanti(rs.getString("PEGANGAN_HAKMILIK_TUKARGANTI"));
				hUrusan.setStatusSwasta(rs.getString("STATUS_SWASTA"));
				hUrusan.setTarikhSwasta(new Date(rs.getString("TARIKH_SWASTA")));
				hUrusan.setStatusPajakan(rs.getString("STATUS_PAJAKAN"));
				hUrusan.setTarikhPajakan(new Date(rs.getString("TARIKH_MOHON_PAJAKAN")));
				hUrusan.setStatusSewa(rs.getString("STATUS_SEWA"));
				hUrusan.setTarikhSewa(new Date(rs.getString("TARIKH_MOHON_SEWA")));
				hUrusan.setStatusSwasta(rs.getString("STATUS_PELEPASAN"));
				hUrusan.setTarikhSwasta(new Date(rs.getString("TARIKH_PELEPASAN")));
				hakmilik.setUrusan(hUrusan);
				hakmilik.setCatatan(rs.getString("CATATAN"));
				hakmilik.setTarikhMasuk(new Date(rs.getString("TARIKH_MASUK")));
				hakmilik.setTarikhMasukStr(rs.getString("TARIKH_MASUK"));
				hakmilik.setTarikhKemaskini(new Date(rs.getString("TARIKH_KEMASKINI")));
				hakmilik.setTarikhKemaskiniStr(rs.getString("TARIKH_KEMASKINI"));				
				hakmilik.setBil(bil);
				v.addElement(hakmilik);
	      		bil++;

			}
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
			if(db != null)
				db.close();
		}
		return v;
	
	}
	
	@Override
	public Vector getSenaraiTanahAgensi(String dari, String hingga) {
		Vector v = new Vector();
		Db db = null;
		Connection conn = null;		
		String bulanDari = "01";
		String tahunDari = "1900";
		String bulanHingga = "01";
		String tahunHingga = "1900";
		try{
			//myLog.info(dari+","+ hingga);
			//myLog.info(dari.substring(3,5)+","+ dari.substring(6,10));
			//myLog.info(hingga.substring(3,5)+","+ hingga.substring(6,10));
			bulanDari = dari.substring(3,5);
			bulanHingga = hingga.substring(3,5);
			tahunDari = dari.substring(6,10);
			tahunHingga = hingga.substring(6,10);
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			String sql = "" +
					" SELECT " +
					" T.ID_HAKMILIKAGENSI, T.ID_HAKMILIK, T.ID_LUAS " +
					" ,T.LUAS, T.ID_LUAS_BERSAMAAN, T.LUAS_BERSAMAAN " +
					" ,RK.KOD_DDSA KOD_DDSA_KEMENTERIAN,RK.NAMA_KEMENTERIAN,RA.KOD_DDSA KOD_DDSA_AGENSI,RA.NAMA_AGENSI "+
			 		" ,T.ID_MASUK" +
			 		" ,NVL(TO_CHAR(T.TARIKH_MASUK,'dd/mm/yyyy HH:MI:SS'),'01/01/1900 00:00:00') TARIKH_MASUK " +
			 		" ,NVL(TO_CHAR(T.TARIKH_KEMASKINI,'dd/mm/yyyy HH:MI:SS'),'01/01/1900 00:00:00') TARIKH_KEMASKINI " +
			 		" ,T.ID_DB, T.STATUS " +
			 		" FROM TBLHTPHAKMILIKAGENSI T,TBLRUJKEMENTERIAN RK,TBLRUJAGENSI RA " +
			 		" WHERE RK.ID_KEMENTERIAN = T.ID_KEMENTERIAN " +
			 		" AND RA.ID_AGENSI = T.ID_AGENSI " +
			 		//" AND TO_CHAR(T.TARIKH_KEMASKINI,'dd') BETWEEN '02' AND '04' " +
			 		//" AND TO_CHAR(T.TARIKH_KEMASKINI,'yyyy') BETWEEN '2011' AND '2012' " +
			 		" AND TO_CHAR(T.TARIKH_KEMASKINI,'mm') BETWEEN '"+bulanDari+"' AND '"+bulanHingga+"' " +
			 		" AND TO_CHAR(T.TARIKH_KEMASKINI,'yyyy') BETWEEN '"+tahunDari+"' AND '"+tahunDari+"' " +
					" ORDER BY T.TARIKH_KEMASKINI DESC";
			myLog.info(sql);
	      	int bil = 1;
			ResultSet rs = stmt.executeQuery(sql);			
			while(rs.next()){
				HakMilik hakmilik = new HakMilik();
				HakmilikAgensi hAgensi = new HakmilikAgensi();
				Agensi agensi = new Agensi();
				Kementerian kem = new Kementerian();
				
				hAgensi.setIdHakmilikAgensi(rs.getLong("ID_HAKMILIKAGENSI"));
				hakmilik.setIdHakmilik(rs.getLong("ID_HAKMILIK"));
				hakmilik.setIdLuas(rs.getLong("ID_LUAS"));
				hakmilik.setLuasString(rs.getString("LUAS"));
				//hakmilik.setIdLuasBersamaan(rs.getLong("ID_LUAS_BERSAMAAN"));
				hakmilik.setLuasBersamaan(rs.getDouble("LUAS_BERSAMAAN"));
				kem.setKodKementerian(rs.getString("KOD_DDSA_KEMENTERIAN"));
				kem.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));
				agensi.setKementerian(kem);
				agensi.setKodAgensi(rs.getString("KOD_DDSA_AGENSI"));
				agensi.setNamaAgensi(rs.getString("NAMA_AGENSI"));
				hakmilik.setAgensi(agensi);
				hakmilik.setIdMasuk(rs.getLong("ID_MASUK"));
				hakmilik.setTarikhMasukStr(rs.getString("TARIKH_MASUK"));
				hakmilik.setTarikhKemaskiniStr(rs.getString("TARIKH_KEMASKINI"));
				hakmilik.setIdDB(rs.getLong("ID_DB"));
				hAgensi.setStatus(rs.getString("STATUS"));
				hakmilik.setHakmilikAgensi(hAgensi);
				hakmilik.setBil(bil);
				v.addElement(hakmilik);
	      		bil++;

			}
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
			if(db != null)
				db.close();
		}
		return v;
	
	}
	
	
	
}
