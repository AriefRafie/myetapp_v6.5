package ekptg.model.htp.gadaian;

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
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHTPHakmilikUrusan;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.PembelianBean;
import etapp.db.DbPersistence;
import etapp.entity.rujukan.Daerah;
import etapp.entity.rujukan.JenisHakmilik;
import etapp.entity.rujukan.JenisRizab;
import etapp.entity.rujukan.Kategori;
import etapp.entity.rujukan.Lot;
import etapp.entity.rujukan.Mukim;
import etapp.entity.rujukan.Negeri;
import etapp.entity.rujukan.SubKategori;
//import etapp.db.DbPersistence;

public class HTPHakmilikUrusanGadaianBean implements IHTPHakmilikUrusan {
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.gadaian.HTPHakmilikUrusanGadaianBean.class);
	private String sql = "";
	PfdFail fail = null;
	Permohonan permohonan = null;
	HtpPermohonan htpPermohonan = null;
	private IHtp iHTP = null;  	
	private IPembelian iPembelian = null;
	private Vector<HakmilikUrusan> senaraiHakmilik = null;
	//DbPersistence db_ = new DbPersistence();
	Db db = null;
	Connection conn = null;
	private etapp.entity.htp.HakmilikUrusan huObj = null;
	private Vector<Hashtable<String,String>> senaraiCarian = null;

	//PAPAR CARIAN HAKMILIK
	public Vector<Hashtable<String,String>> getCarianSenaraiHakmilik(String idJenisTanah
		,String idNegeri, String idDaerah,String idMukim
		,String noFail
		,String idJenisHakmilik,String noHakmilik
		,String noWarta
		,String idLot,String noLot
		,String idAgensi) throws Exception {
	    senaraiCarian = new Vector<Hashtable<String,String>>();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
    
	      sql = "SELECT DISTINCT A.ID_HAKMILIKURUSAN ID_HAKMILIK,C.NO_FAIL, "+
          		"CASE "+
          		"	WHEN E.KOD_JENIS_HAKMILIK = '00' THEN '' "+
          		"	ELSE E.KOD_JENIS_HAKMILIK "+
          		"END AS JENIS_HAKMILIK, A.NO_HAKMILIK, A.NO_WARTA, "+
          		"F.KETERANGAN AS JENIS_LOT, A.NO_LOT, D.NAMA_URUSAN "+
//          		",CASE "+
//          		"WHEN A.STATUS_SAH = 'H' "+
//          		"THEN 'HAKMILIK ASAL TIADA' "+
//          		"WHEN A.STATUS_SAH = 'D' "+
//          		"THEN 'DAFTAR' "+
//          		"WHEN A.STATUS_SAH = 'P' "+
//          		"THEN 'BATAL (PELEPASAN)' "+
//          		"WHEN A.STATUS_SAH = 'T' "+
//             	"THEN 'TUKAR GUNA' "+ 
//             	"WHEN A.STATUS_SAH = 'S' "+
//             	"THEN 'BATAL (SAMBUNGAN)' "+
//	            "WHEN A.STATUS_SAH = 'B' "+
//	                "THEN 'BATAL' "+
//	             "WHEN A.STATUS_SAH = 'M' "+
//		         	"THEN 'BATAL (PINDAHMILIK)' "+
//             	"ELSE 'TIADA' "+
//             	"END AS STATUS_HAKMILIK "+
             	",A.STATUS_RIZAB, A.TARIKH_MASUK TARIKH_TERIMA " +
             	",PB.NAMA "+
             	" FROM TBLHTPHAKMILIKURUSAN A, "+
             	"TBLPERMOHONAN B, "+
             	"TBLPFDFAIL C, "+
             	"TBLRUJURUSAN D, "+
             	"TBLRUJJENISHAKMILIK E, "+
             	"TBLRUJLOT F, "+
             	" TBLHTPPIHAKBERKEPENTINGAN PB "+
 //            	"TBLHTPHAKMILIKAGENSI J "+
             	"WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
             	"AND B.ID_FAIL = C.ID_FAIL "+
             	"AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
             	"AND A.ID_LOT = F.ID_LOT "+
             	"AND C.ID_URUSAN = D.ID_URUSAN "+
             	"AND A.ID_HAKMILIKURUSAN = PB.ID_HAKMILIKURUSAN "+
 //            	"AND A.ID_HAKMILIK = I.ID_HAKMILIK(+) "+
 				" AND C.ID_SUBURUSAN IN (46,47) "+
 				" AND ( B.ID_STATUS is null OR B.ID_STATUS <> 999) "+
	      		"";
	      //idJenisTanah
	      if (idJenisTanah != null) {
			if (!idJenisTanah.trim().equals("")) {
				if (Integer.parseInt(idJenisTanah) == 1){
					sql = sql + " AND A.NO_HAKMILIK IS NOT NULL AND A.STATUS_RIZAB IS NOT NULL ";
				} else if (Integer.parseInt(idJenisTanah) == 2){
					sql = sql + " AND A.NO_HAKMILIK IS NULL AND A.STATUS_RIZAB IS NULL ";
				}
			}
	      }
         
	      //id negeri
	      if (idNegeri != null) {
			if (!idNegeri.trim().equals("")) {
				if (!idNegeri.trim().equals("99999")) {
					sql = sql + " AND A.ID_NEGERI = '" + idNegeri + "'";
				}
			}
	      }
	      //id daerah
	      if (idDaerah != null) {
			if (!idDaerah.trim().equals("")) {
				if (!idDaerah.trim().equals("99999")) {
					sql = sql + " AND A.ID_DAERAH = '" + idDaerah + "'";
				}
			}
	      }
		//id mukim
		if (idMukim != null) {
			if (!idMukim.trim().equals("")) {
				if (!idMukim.trim().equals("99999")) {
					sql = sql + " AND A.ID_MUKIM = '" + idMukim + "'";
				}
			}
		}	      
		//no fail
		if (noFail != null) {
			if (!noFail.trim().equals("")) {
				sql = sql + " AND UPPER(C.NO_FAIL) LIKE '%' ||'" + noFail.toUpperCase() + "'|| '%'";
			}
		}
		//id jenishakmilik
		if (idJenisHakmilik != null) {
			if (!idJenisHakmilik.trim().equals("")) {
				if (!idJenisHakmilik.trim().equals("99999")) {
					sql = sql + " AND A.ID_JENISHAKMILIK = '" + idJenisHakmilik + "'";
				}
			}
		} 
		//no hakmilik
		if (noHakmilik != null) {
			if (!noHakmilik.trim().equals("")) {
				sql = sql + " AND UPPER(A.NO_HAKMILIK) LIKE '%' ||'" + noHakmilik.toUpperCase() + "'|| '%'";
			}
		}       
		//no warta
		if (noWarta != null) {
			if (!noWarta.trim().equals("")) {
				sql = sql + " AND UPPER(A.NO_WARTA) LIKE '%' ||'" + noWarta.toUpperCase() + "'|| '%'";
			}
		}   
		//id lot
		if (idLot != null) {
			if (!idLot.trim().equals("")) {
				if (!idLot.trim().equals("99999")) {
					sql = sql + " AND A.ID_LOT = '" + idLot + "'";
				}
			}
		} 
		//no lot
		if (noLot != null) {
			if (!noLot.trim().equals("")) {
				sql = sql + " AND UPPER(A.NO_LOT) LIKE '%' ||'" + noLot.toUpperCase() + "'|| '%'";
			}
		} 
		//no agensi
//		if (idAgensi != null) {
//			if (!idAgensi.trim().equals("")) {
//				if (!idAgensi.trim().equals("99999")) {
//					sql = sql + " AND J.ID_AGENSI = '" + idAgensi + "'";
//				}
//			}
//		}
			sql = sql +" ORDER BY A.TARIKH_MASUK DESC ";
			myLog.info("carian "+sql);
			ResultSet rs = stmt.executeQuery(sql);
  
			Hashtable<String,String> h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", bil+".");
				h.put("idHakmilik",rs.getString("ID_HAKMILIK"));
				h.put("noFail",rs.getString("NO_FAIL"));
				h.put("kodJenis", rs.getString("JENIS_HAKMILIK")== null?"":rs.getString("JENIS_HAKMILIK"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
				h.put("noWarta", rs.getString("NO_WARTA")== null?"":rs.getString("NO_WARTA"));
				h.put("kodLot", rs.getString("JENIS_LOT")== null?"":rs.getString("JENIS_LOT"));
				h.put("noLot", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
				//h.put("namaUrusan", rs.getString("NAMA_URUSAN")== null?"":rs.getString("NAMA_URUSAN"));
				//h.put("statusSah", rs.getString("STATUS_HAKMILIK")== null?"":rs.getString("STATUS_HAKMILIK"));
				//h.put("kegunaanTanah", rs.getString("KEGUNAAN_TANAH")== null?"":rs.getString("KEGUNAAN_TANAH").toUpperCase());
				h.put("nama", rs.getString("NAMA")== null? "":rs.getString("NAMA"));
				h.put("tarikhTerima",rs.getDate("TARIKH_TERIMA")== null? "":sdf.format(rs.getDate("TARIKH_TERIMA")));

				senaraiCarian.addElement(h);
				bil++;
				count++;
  	  
			}
			if (count == 0){
				h = new Hashtable<String,String>();
				h.put("bil","");
				h.put("idHakmilik","");
				h.put("noFail","");
				h.put("kodJenis", "");
				h.put("noHakmilik", "Tiada Rekod.");
				h.put("noWarta", "");
				h.put("kodLot", "");
				h.put("noLot", "");
				//h.put("namaUrusan", "");
				//h.put("statusSah", "");
				//h.put("kegunaanTanah", "");
				//h.put("statusRizab", "");
				h.put("tarikhTerima","");
  	  
  	  			senaraiCarian.addElement(h);
			}
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }		
	    return senaraiCarian;
	    
	}	
	@Override
	public Vector<HakmilikUrusan> getSenaraiHakmilikUrusan(String idPermohonan){
		senaraiHakmilik = new Vector<HakmilikUrusan>();
		senaraiHakmilik = getIPembelian().getHakmilikList(idPermohonan);
		
//		DbPersistence db_ = new DbPersistence();
//		List<etapp.entity.htp.HakmilikUrusan> list = db_.list("select hu from HakmilikUrusan hu");
//		for ( etapp.entity.htp.HakmilikUrusan hu : list ) {
//			myLog.info(hu.getNohakmilik());
//		}

		return senaraiHakmilik;
		
	}
	
	@Override
	public etapp.entity.htp.HakmilikUrusan simpan(etapp.entity.htp.HakmilikUrusan huTemp) throws Exception {
		DbPersistence db_ = new DbPersistence();
		//myLog.info(db_);
		etapp.entity.htp.HakmilikUrusan hUrusan = new etapp.entity.htp.HakmilikUrusan();
		String sql =null;
		try {			
			db = new Db();
			conn = db.getConnection();
		    conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();		
			if(huTemp.getId()==0){
				Daerah daerah = db_.find(Daerah.class, huTemp.getDaerah().getId());
				JenisHakmilik jenisHakmilik = db_.find(JenisHakmilik.class, huTemp.getJenisHakmilik().getId());
				Lot lot = db_.find(Lot.class, huTemp.getLot().getId());
				Mukim mukim = db_.find(Mukim.class, huTemp.getMukim().getId());
				Negeri negeri = db_.find(Negeri.class, huTemp.getNegeri().getId());
				etapp.entity.Permohonan permohonan_ = db_.find(etapp.entity.Permohonan.class
						, huTemp.getPermohonan().getId());
				Kategori kategori = db_.find(Kategori.class, huTemp.getIdKategoriTanah());
				SubKategori subKategori = db_.find(SubKategori.class, huTemp.getIdSubKategoriTanah());
				JenisRizab jenisRizab = db_.find(JenisRizab.class, huTemp.getIdJenisRizab());
				
//				Urusan urusan = db_.find(Urusan.class, Long.parseLong("1"));
//				Seksyen sek = db_.find(Seksyen.class, Long.parseLong("1"));
//				SubUrusan subUrusan = new SubUrusan();
				db_.begin();
//				subUrusan.setId( Long.parseLong("101"));
//				subUrusan.setKod("XX");
//				subUrusan.setNama("xxx");
//				subUrusan.setUrusan(urusan);
//				subUrusan.setSeksyen(sek);
//				db_.persist(subUrusan);
//				lot.setId(Long.parseLong("100"));
//				lot.setKeterangan("XX");
//				lot.setKod("XX");
//				db_.persist(lot);
			hUrusan.setId(DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ"));
			//hUrusan.setId(Long.parseLong("1"));
			hUrusan.setDaerah(daerah);
			hUrusan.setJenisHakmilik(jenisHakmilik);
			hUrusan.setLot(lot);
			hUrusan.setMukim(mukim);
			hUrusan.setNegeri(negeri);
			hUrusan.setPermohonan(permohonan_);
			hUrusan.setKategori(kategori);
			hUrusan.setSubKategori(subKategori);
			hUrusan.setJenisRizab(jenisRizab);
			
			hUrusan.setNohakmilik(huTemp.getNohakmilik());
			hUrusan.setNolot(huTemp.getNolot());
			hUrusan.setLokasi(huTemp.getLokasi());
			hUrusan.setNoFailJofa(huTemp.getNoFailJofa());
			hUrusan.setSyarat(huTemp.getSyarat());
			hUrusan.setSekatan(huTemp.getSekatan());
			hUrusan.setIdLuas( huTemp.getIdLuas());
			hUrusan.setLuas(huTemp.getLuas());
			hUrusan.setIdLuasBersamaan( huTemp.getIdLuasBersamaan());
			hUrusan.setLuasBersamaan(huTemp.getLuasBersamaan());
			hUrusan.setCukai(huTemp.getCukai());
			hUrusan.setCukaiTerkini(huTemp.getCukaiTerkini());
			hUrusan.setNoPelan(huTemp.getNoPelan());
			hUrusan.setNoPU(huTemp.getNoPU());
			hUrusan.setNoSyit(huTemp.getNoSyit());
			hUrusan.setTarikhMula(huTemp.getTarikhMula());
			if(!huTemp.getTarikhRizab().equals("")){
				hUrusan.setTarikhLuput(huTemp.getTarikhLuput());
			}
			hUrusan.setIdMasuk(huTemp.getIdMasuk());
			hUrusan.setTarikhMasuk(new Date());
			hUrusan.setIdKemaskini(huTemp.getIdMasuk());
			hUrusan.setTarikhKemaskini(new Date());
			//r.add("tarikh_Masuk", r.unquote("sysdate"));
			//r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			//hu.setIdhakmilikurusan(String.valueOf(idHakmilikurusan));
			db_.persist(hUrusan);
			db_.commit();				
//			long idHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
//			r.add("id_Hakmilikurusan", idHakmilikurusan);
//			r.add("id_Permohonan", huTemp.getPermohonan().getId());
//			r.add("id_Negeri", huTemp.getNegeri().getId());
//			r.add("id_Daerah", huTemp.getDaerah().getId());
//			r.add("id_Mukim", huTemp.getMukim().getId());
//			r.add("id_JenisHakmilik", huTemp.getJenisHakmilik().getId());
//			r.add("no_Hakmilik", huTemp.getNohakmilik());
//			r.add("id_Lot", huTemp.getLot().getId());
//			r.add("no_Lot", huTemp.getNolot());
//			r.add("lokasi", huTemp.getLokasi());
//			r.add("no_Fail_Jofa", huTemp.getNoFailJofa());
//			r.add("syarat", huTemp.getSyarat());
//			r.add("Sekatan",  huTemp.getSekatan());
//			r.add("id_Luas", huTemp.getIdLuas());
//			r.add("luas", huTemp.getLuas());
//			r.add("id_Luas_Bersamaan",  huTemp.getIdLuasBersamaan());
//			r.add("luas_Bersamaan",  huTemp.getLuasBersamaan());			
//			r.add("cukai", huTemp.getCukai());
//			r.add("cukai_Terkini", huTemp.getCukaiTerkini());
//			r.add("no_Pelan", huTemp.getNoPlan());
//			r.add("no_PU", huTemp.getNoPU());
//			r.add("no_Syit", huTemp.getNoSyit());
//			if(!huTemp.getTarikhRizab().equals(""))
//				r.add("tarikh_Luput",  r.unquote("to_date('" +huTemp.getTarikhRizab()+ "','dd/MM/yyyy')"));
//			r.add("id_Jenisrizab",  huTemp.getIdJenisRizab());
//			r.add("no_Rizab",  huTemp.getNoRizab());
//			  String socKategori = "1";
//			  String idSubkategori = "96";
//			r.add("id_Kategori", huTemp.getIdKategoriTanah());
//			r.add("id_Subkategori", idSubkategori);
//			//r.add("tarikh_Terima",  r.unquote(huTemp.getTarikhTerima()));
//			r.add("tarikh_Mula",  r.unquote("to_date('" +huTemp.getTarikhMula()+ "','dd/MM/yyyy')"));
//			if(!huTemp.getTarikhLuput().equals(""))
//				r.add("tarikh_Luput",  r.unquote("to_date('" +huTemp.getTarikhLuput()+ "','dd/MM/yyyy')"));
//			r.add("id_Masuk",  huTemp.getIdMasuk());
//			r.add("tarikh_Masuk", r.unquote("sysdate"));
//			r.add("id_Kemaskini",  huTemp.getIdMasuk());
//			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
//			
//			sql = r.getSQLInsert("Tblhtphakmilikurusan");
//			myLog.info("sql:"+sql);
//	    	stmt.executeUpdate(sql);
//			conn.commit();
		
			}
		      			
		    }catch(Exception e){
		    	e.printStackTrace();
		    	myLog.info(""+e);
		    }finally {
		      if (db != null) db.close();
		    
		    }
		    return hUrusan;
		    
	}	
	
	@Override
	public etapp.entity.htp.HakmilikUrusan kemaskini(etapp.entity.htp.HakmilikUrusan huTemp) throws Exception {
		DbPersistence db_ = new DbPersistence();
		//myLog.info(db_);
		etapp.entity.htp.HakmilikUrusan hUrusan = null;
		try {			
			//if(huTemp.getId()==0){
				//hUrusan = db_.find(etapp.entity.htp.HakmilikUrusan.class, huTemp.getId());
				Daerah daerah = db_.find(Daerah.class, huTemp.getDaerah().getId());
				JenisHakmilik jenisHakmilik = db_.find(JenisHakmilik.class, huTemp.getJenisHakmilik().getId());
				Lot lot = db_.find(Lot.class, huTemp.getLot().getId());
				Mukim mukim = db_.find(Mukim.class, huTemp.getMukim().getId());
				Negeri negeri = db_.find(Negeri.class, huTemp.getNegeri().getId());
				etapp.entity.Permohonan permohonan_ = db_.find(etapp.entity.Permohonan.class
						, huTemp.getPermohonan().getId());
				Kategori kategori = db_.find(Kategori.class, huTemp.getIdKategoriTanah());
				SubKategori subKategori = db_.find(SubKategori.class, huTemp.getIdSubKategoriTanah());
				JenisRizab jenisRizab = db_.find(JenisRizab.class, huTemp.getIdJenisRizab());
				
				db_.begin();
				//hUrusan.setId(DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ"));
				hUrusan.setId(huTemp.getId());
				hUrusan.setDaerah(daerah);
				hUrusan.setJenisHakmilik(jenisHakmilik);
				hUrusan.setLot(lot);
				hUrusan.setMukim(mukim);
				hUrusan.setNegeri(negeri);
				hUrusan.setPermohonan(permohonan_);
				hUrusan.setKategori(kategori);
				hUrusan.setSubKategori(subKategori);
				hUrusan.setJenisRizab(jenisRizab);
				
				hUrusan.setNohakmilik(huTemp.getNohakmilik());
//				hUrusan.setNolot(huTemp.getNolot());
//				hUrusan.setLokasi(huTemp.getLokasi());
//				hUrusan.setNoFailJofa(huTemp.getNoFailJofa());
//				hUrusan.setSyarat(huTemp.getSyarat());
//				hUrusan.setSekatan(huTemp.getSekatan());
//				hUrusan.setIdLuas( huTemp.getIdLuas());
//				hUrusan.setLuas(huTemp.getLuas());
//				hUrusan.setIdLuasBersamaan( huTemp.getIdLuasBersamaan());
//				hUrusan.setLuasBersamaan(huTemp.getLuasBersamaan());
//				hUrusan.setCukai(huTemp.getCukai());
//				hUrusan.setCukaiTerkini(huTemp.getCukaiTerkini());
//				hUrusan.setNoPelan(huTemp.getNoPelan());
//				hUrusan.setNoPU(huTemp.getNoPU());
//				hUrusan.setNoSyit(huTemp.getNoSyit());
//				hUrusan.setTarikhMula(huTemp.getTarikhMula());
//				if(!huTemp.getTarikhRizab().equals("")){
//					hUrusan.setTarikhLuput(huTemp.getTarikhLuput());
//				}
//				//hUrusan.setIdMasuk(huTemp.getIdMasuk());
//				//hUrusan.setTarikhMasuk(new Date());
//				hUrusan.setIdKemaskini(huTemp.getIdMasuk());
//				hUrusan.setTarikhKemaskini(new Date());
	//			db_.persist(hUrusan);
				db_.commit();				
				//}
		      			
		    }catch(Exception e){
		    	e.printStackTrace();
		    	myLog.info(""+e);
		    }finally {
		      if (db != null) db.close();
		    
		    }
		    return hUrusan;
		    
	}
	
	// function exist on ekptg.model.htp.pembelian.PembelianBean.findByHakmilikUrusanId(String hakmilikUrusanId)
	@Override
	public HakmilikUrusan findByIdHakmilikUrusan(String idHakmilikUrusan) {
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
			r.set("id_Hakmilikurusan", idHakmilikUrusan);
			String sql = r.getSQLSelect("Tblhtphakmilikurusan");
			
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				urusan = new HakmilikUrusan();
				Permohonan permohonan = new Permohonan();
				urusan.setIdhakmilikurusan(idHakmilikUrusan);
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
			
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
			 if (db != null){
		    	  db.close();
		      }
		}
		return urusan;
	
	}
	
	@Override
	public etapp.entity.htp.HakmilikUrusan getHakmilikUrusan(String idHakmilikUrusan) throws Exception {
		SQLRenderer r = null;
		//DbPersistence db = new DbPersistence();
		huObj = new etapp.entity.htp.HakmilikUrusan();
		try{
			//huObj = db.find(etapp.entity.htp.HakmilikUrusan.class, Long.parseLong(idhakmilikUrusan));
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
			r.set("id_Hakmilikurusan", idHakmilikUrusan);
			String sql = r.getSQLSelect("Tblhtphakmilikurusan");
			myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				etapp.entity.htp.HakmilikUrusan	urusan = new etapp.entity.htp.HakmilikUrusan();
				//Permohonan permohonan = new Permohonan();
				urusan.setId(Long.parseLong(idHakmilikUrusan));
				Negeri negeri = new Negeri();
				negeri.setId(rs.getLong("id_Negeri"));
				urusan.setNegeri(negeri);
				permohonan.setIdPermohonan(rs.getLong("id_Permohonan"));
				urusan.setPeganganHakmilik(rs.getString("pegangan_Hakmilik"));
				//urusan.setIdNegeri(rs.getString("id_Negeri"));
				//urusan.setIdDaerah(rs.getString("id_Daerah"));
				//urusan.setIdMukim(rs.getString("id_Mukim"));
				//urusan.setIdHakmilik(rs.getString("id_JenisHakmilik"));
				urusan.setNohakmilik(rs.getString("no_Hakmilik"));
				urusan.setNolot(rs.getString("no_Lot"));
				//urusan.setIdLot(rs.getString("id_Lot"));
				urusan.setNoBangunan(rs.getString("no_Bangunan"));
				urusan.setNoTingkat(rs.getString("no_Tingkat"));
				urusan.setNoPetak(rs.getString("no_Petak"));
				//urusan.setTarikhMula(rs.getString("Tarikh_Mula"));
				//urusan.setTarikhLuput(rs.getString("Tarikh_Luput"));
				urusan.setLuas(rs.getString("Luas"));
				urusan.setIdLuas(rs.getString("id_Luas"));
				//urusan.setNoPlan(rs.getString("no_Pelan"));
				urusan.setIdJenisRizab(Utils.isNull(rs.getString("id_Jenisrizab")));
				urusan.setIdKategoriTanah(rs.getString("id_Kategori"));
				//urusan.setIdSubKetegoriTanah(rs.getString("id_Subkategori"));
				urusan.setStatusTanah(rs.getString("status_tanah"));
				//urusan.setPermohonan(permohonan);
				huObj = urusan;
		
			}			
		}catch(Exception e){
			e.printStackTrace();
		
//		}finally{
//			 if (db != null){
//		    	  db.close();
//		      }
		}
		return huObj;
	
	}
	
	@Override
	// exist di class ekptg.model.htp.FrmPajakanPendaftaranData, deleteHakmilik(String idHakmilikUrusan)
	public void hapusHakmilik(String idHakmilikUrusan) throws Exception {
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
	    		getIHTP().getErrorHTML("Rollback error : " + e.getMessage());
	    		//throw new Exception("Rollback error : " + e.getMessage());
	    	}
    		getIHTP().getErrorHTML("Ralat : Masalah menghapus data : " + ex.getMessage());
	    	//throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}	
	}	
	/**
	 *  update data in database
	 * @param data
	 * @throws Exception
	 */	
	@Override
	public void kemaskini(Hashtable data) throws Exception {
		double CukaiPertama = 0.0;
		double CukaiSemasa = 0.0;
		String Lokasi = "";
		String peganganHakmilik = "JKPTG";
		String socLuas = "0";
		String NoPelan = "";
		String socRizab = "0";
		String socKategori = "1";
		String idSubkategori = "96";
		String Syarat = "";
		String TL = "";
		String Luas = "";
		String TW = "";
		String NoWarta = "";
		String NoPU = "";
		String NoSyit = "";
		String Sekatan = "";

		try{
			String idPermohonan = (String)data.get("idPermohonan");
			String idHakmilikurusan = (String)data.get("idHakmilikurusan");
			int socNegeri = Integer.parseInt(data.get("idNegeri").toString());
			int socDaerah = Integer.parseInt(data.get("socDaerah").toString());
			int socMukim = Integer.parseInt(data.get("socMukim").toString());
			int socJenisHakmilik = Integer.parseInt(data.get("socJenisHakmilik").toString());
			String NoHakmilik = (String)data.get("NoHakmilik");
			String NoLot = (String)data.get("NoLot");
			String NoJofa = (String)data.get("NoJofa");
			int socLot = Integer.parseInt(data.get("socLot").toString());
			String TarikhTerima = (String)data.get("TarikhTerima");
			String TT = "to_date('" + TarikhTerima + "','dd/MM/yyyy')";
			if(data.get("CukaiPertama") != "")
				CukaiPertama = Double.parseDouble(Utils.RemoveSymbol(data.get("CukaiPertama").toString()));
			if(data.get("Lokasi") != null)
				Lokasi = (String)data.get("Lokasi");
			if(data.get("NoPelan") != null)
				NoPelan = (String)data.get("NoPelan");
			if (data.get("socRizab") != null && data.get("socRizab") != ""){
				socRizab = data.get("socRizab").toString();
			}
			if (data.get("socKategori") != null && data.get("socKategori") != ""){
				socKategori = data.get("socKategori").toString();
			}
			if(data.get("Syarat") != null)
				Syarat = (String)data.get("Syarat");
			String TarikhLuput = (String)data.get("TarikhLuput");
			TL = "to_date('" + TarikhLuput + "','dd/MM/yyyy')";
			if(data.get("CukaiSemasa") != "")
				CukaiSemasa = Double.parseDouble(Utils.RemoveSymbol(data.get("CukaiSemasa").toString()));
			if (data.get("socLuas") != null && data.get("socLuas") != ""){
				socLuas = data.get("socLuas").toString();
			}
			if(data.get("Luas") != null)
				Luas = (String)data.get("Luas");
			String TarikhWarta = (String)data.get("TarikhWarta");
			TW = "to_date('" + TarikhWarta + "','dd/MM/yyyy')";
			if(data.get("NoWarta") != null && data.get("NoWarta") != ""){
				NoWarta = (String)data.get("NoWarta");
			}  
			if(data.get("NoPU") != null)
				NoPU = (String)data.get("NoPU");
			if(data.get("NoSyit") != null)
				NoSyit = (String)data.get("NoSyit");
			if(data.get("Sekatan") != null)
				Sekatan = (String)data.get("Sekatan");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_Hakmilikurusan", idHakmilikurusan);
			//r.update("id_Permohonan", idPermohonan);
			r.add("id_Negeri", socNegeri);
			r.add("id_Daerah", socDaerah);
			r.add("id_Mukim", socMukim);
			r.add("id_JenisHakmilik", socJenisHakmilik);
			r.add("no_Hakmilik", NoHakmilik);
			r.add("no_Lot", NoLot);
			r.add("no_fail_jofa", NoJofa);
			r.add("id_Lot", socLot);
			r.add("tarikh_Mula",r.unquote(TT));
			r.add("Cukai", CukaiPertama);
			r.add("Lokasi", Lokasi);
			r.add("id_Luas", socLuas);
			r.add("Luas", Luas);
			r.add("ID_LUAS_BERSAMAAN", r.unquote((String)data.get("idLuasBersamaan")));
			r.add("LUAS_BERSAMAAN", (String)data.get("luasBersamaan"));
			r.add("no_Pelan", NoPelan);
			r.add("id_Jenisrizab", socRizab);
			r.add("id_Kategori", socKategori);
			r.add("Syarat", Syarat);
			r.add("tarikh_Luput",r.unquote(TL));
			r.add("cukai_Terkini", CukaiSemasa);
			r.add("tarikh_Rizab",r.unquote(TW));
			r.add("no_Rizab", NoWarta);
			r.add("NO_PETAK", NoPU);
			r.add("no_Syit", NoSyit);
			r.add("Sekatan", Sekatan);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("Tblhtphakmilikurusan");
			stmt.executeUpdate(sql);
			      	    
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if (db != null) db.close();
		}
		
	
	}	  
	/**
	 * Simpan data in database
	 */	
	@Override
	public void simpan(Hashtable<String,String> data) throws Exception {
		double CukaiPertama = 0.0;
		double CukaiSemasa = 0.0;
		String Lokasi = "";
		String peganganHakmilik = "JKPTG";
		String socLuas = "0";
		String NoPelan = "";
		String socRizab = "0";
		String socKategori = "1";
		String idSubkategori = "96";
		String Syarat = "";
		String TL = "";
		String Luas = "";
		String TW = "";
		String NoWarta = "";
		String NoPU = "";
		String NoSyit = "";
		String Sekatan = "";

		try {
			String idPermohonan = (String)data.get("idPermohonan");
			long idHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
			String idNegeri = (String)data.get("idNegeri");
			String socDaerah = (String)data.get("socDaerah");
			String socMukim = (String)data.get("socMukim");
			String socJenisHakmilik = (String)data.get("socJenisHakmilik");
			String NoHakmilik = (String)data.get("NoHakmilik");
			String NoLot = (String)data.get("NoLot");
			String NoJofa = (String)data.get("NoJofa");
			String socLot = (String)data.get("socLot");
			String TarikhTerima = (String)data.get("TarikhTerima");
			String TT = "to_date('" + TarikhTerima + "','dd/MM/yyyy')";
			String luas = (String)data.get("socLuas");
			String TarikhLuput = (String)data.get("TarikhLuput");
			String TarikhWarta = (String)data.get("TarikhWarta");
			  
			if(data.get("CukaiPertama") != ""){
				CukaiPertama = Double.parseDouble(Utils.RemoveSymbol(data.get("CukaiPertama").toString()));
			}
			if(data.get("Lokasi") != null && data.get("Lokasi") != ""){
				Lokasi = (String)data.get("Lokasi");
			}
			if (luas != null && luas != ""){
				socLuas = data.get("socLuas").toString();
			}
			if(data.get("NoPelan") != null && data.get("NoPelan") != ""){
				NoPelan = (String)data.get("NoPelan");
			}
			if (data.get("socRizab") != null && data.get("socRizab") != ""){
				socRizab = data.get("socRizab").toString();
			}
			//log.info("kat : " + data.get("socKategori").toString() );
			if (data.get("socKategori") != null && data.get("socKategori") != ""){
				socKategori = data.get("socKategori").toString();
			}
			if(data.get("Syarat") != null && data.get("Syarat") != ""){
				Syarat = (String)data.get("Syarat");
			}
			if(TarikhLuput != null && TarikhLuput != ""){
				TL = "to_date('" + TarikhLuput + "','dd/MM/yyyy')";
			}
			if(data.get("CukaiSemasa") != "" && data.get("CukaiSemasa") != null){
				CukaiSemasa = Double.parseDouble(Utils.RemoveSymbol(data.get("CukaiSemasa").toString()));
			}
			if(data.get("Luas") != null && data.get("Luas") != ""){
				Luas = (String)data.get("Luas");
			}
			if (TarikhWarta != null && TarikhWarta != ""){
				TW = "to_date('" + TarikhWarta + "','dd/MM/yyyy')";
			}
			if(data.get("NoWarta") != null && data.get("NoWarta") != ""){
				NoWarta = (String)data.get("NoWarta");
			}  
			if(data.get("NoPU") != null && data.get("NoPU") != ""){
				NoPU = (String)data.get("NoPU");
			}
			if(data.get("NoSyit") != null && data.get("NoSyit") != ""){
				NoSyit = (String)data.get("NoSyit");
			}
			if(data.get("Sekatan") != null && data.get("Sekatan") != ""){
				Sekatan = (String)data.get("Sekatan");
			}
			  
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Hakmilikurusan", idHakmilikurusan);
			r.add("id_Permohonan", idPermohonan);
			r.add("id_Negeri", idNegeri);
			r.add("id_Daerah", socDaerah);
			r.add("id_Mukim", socMukim);
			r.add("id_JenisHakmilik", socJenisHakmilik);
			r.add("no_Hakmilik", NoHakmilik);
			r.add("no_Lot", NoLot);
			r.add("id_Lot", socLot);
			r.add("NO_FAIL_JOFA", NoJofa);
			r.add("tarikh_Mula", r.unquote(TT));
			if(CukaiPertama != 0.0)
				r.add("Cukai", CukaiPertama);
			  
			r.add("Lokasi", Lokasi);
			r.add("pegangan_Hakmilik",peganganHakmilik);
			r.add("id_Luas", socLuas);
			r.add("no_Pelan", NoPelan);
			r.add("id_Jenisrizab", socRizab);
			r.add("id_Kategori", socKategori);
			r.add("id_Subkategori", idSubkategori);
			r.add("Syarat", Syarat);  
			if(TL != "" && TL!= null){
				r.add("tarikh_Luput", r.unquote(TL));
			}else{
				r.add("tarikh_Luput", TL);
			}
			if(CukaiSemasa != 0.0)
				r.add("cukai_Terkini", CukaiSemasa);
			  
			r.add("Luas", Luas);
			r.add("ID_LUAS_BERSAMAAN", data.get("idLuasBersamaan"));
			r.add("LUAS_BERSAMAAN", data.get("luasBersamaan"));
			if(TW != "" && TW != null){
				r.add("tarikh_Rizab", r.unquote(TW));
			} else{
				r.add("tarikh_Rizab", TW);
			}
			  
			r.add("no_Rizab", NoWarta);
			r.add("NO_Petak", NoPU);
			r.add("no_Syit", NoSyit);
			r.add("Sekatan", Sekatan);
			sql = r.getSQLInsert("Tblhtphakmilikurusan");		      
		      //log.info("FrmGadaianPenHakMilikData:sql= " + sql);
			stmt.executeUpdate(sql);
		      
			// add tarikh melepas gadai ke dlm db bg kes soc
			String tarikhLepasGadai = (String)data.get("tarikhLepasGadai");
			String idFail = (String)data.get("idFail");
			if(tarikhLepasGadai != null && tarikhLepasGadai != ""){
				SQLRenderer rLepas = new SQLRenderer();
				Statement stmtLG = db.getStatement();
		    	  
				String TLG = "to_date('" + tarikhLepasGadai + "','dd/MM/yyyy')";
				long idFailMG = DB.getNextID("TBLHTPMAKLUMATGADAIAN_SEQ");
				rLepas.add("id_htpmaklumatgadaian", idFailMG);
				rLepas.add("id_permohonan", r.unquote(idPermohonan));
//				rLepas.add("tarikh_agihan", "");
				rLepas.add("id_masuk", idFailMG);
				rLepas.add("tarikh_masuk", "");
		   	  	rLepas.add("id_kemaskini", idFailMG);
		   	  	rLepas.add("tarikh_kemaskini", "");
		   	  	rLepas.add("tarikh_lepasgadai", r.unquote(TLG));
		   	  	rLepas.add("id_fail", r.unquote(idFail));					
		   	  	String sqlLepasGadai = rLepas.getSQLInsert("TBLHTPMAKLUMATGADAIAN");				
		   	  	//log.info("FrmGadaianPenHakmilkData:SOC::sqlLepasGadai = "+ sqlLepasGadai);				
		   	  	stmtLG.executeUpdate(sqlLepasGadai);				
				
			}
		      
		}catch(Exception e){
			e.printStackTrace();
		    
		}finally {
			if (db != null) db.close();
		    
		}
		  	
	}
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}
	
	private IPembelian getIPembelian(){
		if (iPembelian==null){
			iPembelian=new PembelianBean();
		}
		return iPembelian;
	} 

	
}
