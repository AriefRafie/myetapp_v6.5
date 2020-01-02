/**
 * 
 */
package ekptg.model.htp;

import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;



/**
 * @author Firzan
 *
 */
public class frmPembelianKelulusan {
	
	private static Logger log = Logger.getLogger(frmPembelianKelulusan.class);
	private static Connection conn = null;
	private static Db db = null;
	private static Vector list = new Vector();
	private static SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
	private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void SimpanKelulusan(Hashtable hash) throws Exception{
		
		String sqlKJP;
		String sqlKPTG;
		
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			Statement stmtKJP = db.getStatement();
			Statement stmtKPTG = db.getStatement();
			/*
			 *  new data (this data will go to table TBLHTPULASANKJP
			 *  
			 *  idUlasanKJP (PK)
			 *  idPermohonan (FK)
			 *  tarikhHantar
			 *  tarikhTerima
			 *  tarikhSuratKeputusan
			 *  ulasan
			 *  StatusKeputusan
			 *  idMasuk
			 *  tarikhmasuk
			 *  tarikhkemaskini
			 *  idKemaskini
			 *  id_db
			 *  no_rujukan
			 *  
			 */

			Date now = new Date();
		    String today = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";

			long idUlasanKJP = DB.getNextID("TBLHTPULASANKJP_SEQ");
			String idPermohonan = hash.get("idPermohonan").toString();
			String tarikhHantar = "";
			String idMasuk = hash.get("userId").toString();
			String tarikhTerima = hash.get("tarikhSurKem").toString();
			String tarikhT =  "to_date('" + tarikhTerima + "','dd/MM/yyyy')";
			
//			String tarikhSuratKeputusan = hash.get("tarikhSuratKeputusan").toString();
			String tarikhSuratKeputusan = "";
			String tarikhSurK = "to_date('" + tarikhSuratKeputusan + "','dd/MM/yyyy')";
			String ulasanKJP = hash.get("catatanKementerian").toString();
			String StatusKeputusan = hash.get("semakanKem").toString();
			
			String tarikhmasuk = "";
//			String tarikhkemaskini = today;
			String id_db = "";
			String rujukanKJP = hash.get("noRujSurKem").toString();

			
			SQLRenderer rKJP = new SQLRenderer();
			
			rKJP.add("id_ulasankjp", idUlasanKJP);
			rKJP.add("id_permohonan", idPermohonan);
			
			if (tarikhHantar != null  && tarikhHantar != ""){
				rKJP.add("tarikh_hantar", rKJP.unquote(tarikhHantar));
			}
			else{
				rKJP.add("tarikh_hantar", tarikhHantar);
			}
			
			if(tarikhTerima != null && tarikhTerima != ""){
				rKJP.add("tarikh_terima", rKJP.unquote(tarikhT));
				
			}
			else{
				rKJP.add("tarikh_terima", tarikhTerima);
				
			}
			
			if(tarikhSuratKeputusan != null && tarikhSuratKeputusan != ""){
				rKJP.add("tarikh_surat_keputusan", rKJP.unquote(tarikhSurK));
				
			}
			else{
				rKJP.add("tarikh_surat_keputusan", tarikhSuratKeputusan);
				
			}
			
			rKJP.add("ulasan", ulasanKJP);
			rKJP.add("status_keputusan", StatusKeputusan);
			rKJP.add("id_masuk", idMasuk);
			rKJP.add("tarikh_masuk", tarikhmasuk);
			rKJP.add("id_kemaskini", idMasuk);
			rKJP.add("tarikh_kemaskini", rKJP.unquote(today));
			rKJP.add("id_db", "");
			rKJP.add("no_rujukan", rujukanKJP);
			
			sqlKJP = rKJP.getSQLInsert("TBLHTPULASANKJP");
			
			log.info("TBLHTPULASANKJP : Insert : " + sqlKJP);
			
			stmtKJP.executeUpdate(sqlKJP);
			
			/*
			 *  new data (this data will go to table TBLHTPULASANKPTG
			 *  
			 *  idUlasanKPTG (PK)
			 *  idPermohonan (FK)
			 *  tarikhHantar
			 *  Status
			 *  ulasan
			 *  idMasuk
			 *  tarikhmasuk
			 *  idKemaskini
			 *  tarikhkemaskini
			 *  id_db
			 *  no_rujukan
			 *  
			 */
			
			
			long idUlasanKPTG = DB.getNextID("TBLHTPULASANKPTG_SEQ");
			String idPermohonanKPTG = hash.get("idPermohonan").toString();
			String tarikhHantarKPTG = "";
			String tarikhTerimaKPTG = hash.get("tarikhSurPKP").toString();
			
			String TarikhTKPTG = "to_date('" + tarikhTerimaKPTG + "','dd/MM/yyyy')";
			
			String tarikhSuratKeputusanKPTG = "";
			String ulasanKPTG = hash.get("catatanPKP").toString();
			String StatusKeputusanKPTG = hash.get("selectAsalPKP").toString();
			String noRujKPTG = hash.get("noRujSurPKP").toString();
			
			String tarikhmasukKPTG = "";

			SQLRenderer rKPTG = new SQLRenderer();
			rKPTG.add("id_ulasankptg", idUlasanKPTG);
			rKPTG.add("id_permohonan", idPermohonan);
			rKPTG.add("tarikh_hantar", tarikhHantarKPTG);
			rKPTG.add("status", StatusKeputusanKPTG);
			rKPTG.add("ulasan", ulasanKPTG);
			rKPTG.add("id_masuk", idMasuk);
			rKPTG.add("tarikh_masuk", "");
			rKPTG.add("id_kemaskini", idMasuk);
			rKPTG.add("tarikh_kemaskini", rKPTG.unquote(today));
			rKPTG.add("id_db", "");
			rKPTG.add("no_rujukan", noRujKPTG);
			rKPTG.add("tarikh_terima", rKPTG.unquote(TarikhTKPTG));
			
			sqlKPTG = rKPTG.getSQLInsert("TBLHTPULASANKPTG");
			
			log.info("TBLHTPULASANKPTG : Insert : " + sqlKPTG);
			
			stmtKPTG.executeUpdate(sqlKPTG);
			
			
			conn.commit();
			
		}catch(Exception e){
			conn.rollback();
			e.printStackTrace();
	
		}
		
		finally{
			if(conn != null)conn.close();
			if (db != null)db.close();
		}
		
		
		
	}
	
	public void getKelulusanData(String idPermohonan){
		
		String sql;
		
		try{
			sql = "SELECT KJP.NO_RUJUKAN as no_rujukan_kjp, KJP.STATUS_KEPUTUSAN, ";
			sql += "KJP.TARIKH_TERIMA as tarikh_terima_kjp, KJP.ULASAN as ulasan_kjp, ";
			sql += "KPTG.NO_RUJUKAN as no_rujukan_kptg, KPTG.STATUS, ";
			sql += "KPTG.TARIKH_TERIMA as tarikh_terima_kptg, KPTG.ULASAN as ulasan_kptg ";
			sql += "  ";
			sql += " ";
			
//			select  
//			
//			from tblhtpulasankjp kjp, tblhtpulasankptg kptg
//			where KJP.ID_PERMOHONAN = 1610468
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
			if(db != null)db.close();
		}
		 
		
	}

}
