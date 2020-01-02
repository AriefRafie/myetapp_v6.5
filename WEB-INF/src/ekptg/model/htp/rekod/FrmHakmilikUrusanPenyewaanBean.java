package ekptg.model.htp.rekod;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;

public class FrmHakmilikUrusanPenyewaanBean implements IHakmilikUrusan {
	
	//private HakmilikInterface iHakmilik = null;
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.rekod.FrmHakmilikUrusanPenyewaanBean.class);
	private static Vector<Hashtable<String,String>> listCarianHakmilikDanRizab = null;
	//private HakMilik hakmilik = null;
 	private IHtp iHTP = null;  
 	//private PfdFail pfdFail = null;
 	//private Permohonan permohonan = null;
	private String sql = "";
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private	Db db = null;
	
	Vector<Hashtable<String,String>> listDataSewa = null;
	
	public Vector<Hashtable<String,String>> getMaklumatPHPPenyewaan(String idHakmilik) throws Exception {		
		listDataSewa = new Vector<Hashtable<String,String>>();
		listDataSewa.clear();
		
		sql = "SELECT MOHON.ID_PERMOHONAN, FAIL.NO_FAIL, HM.NO_HAKMILIK, PEMOHON.NAMA AS NAMA_PEMOHON, MOHONSEWA.TUJUAN , PERJANJIAN.NO_SIRI, PERJANJIAN.TARIKH_MULA_PERJANJIAN, "+
    			" PERJANJIAN.TARIKH_TAMAT_PERJANJIAN, PERJANJIAN.TEMPOH, PERJANJIAN.KADAR_SEWA "+
    			" FROM TBLPFDFAIL FAIL, TBLPERMOHONAN MOHON, "+
    			" TBLPHPPERMOHONANSEWA MOHONSEWA, TBLPHPPEMOHON PEMOHON, "+ 
    			" TBLPHPPERJANJIAN PERJANJIAN, TBLPHPHAKMILIKPERMOHONAN HMPERMOHONAN, "+ 
    			" TBLHTPHAKMILIKAGENSI HMAGENSI, TBLHTPHAKMILIK HM "+
    			" WHERE FAIL.ID_FAIL = MOHON.ID_FAIL(+) "+
    			" AND MOHON.ID_PEMOHON = PEMOHON.ID_PEMOHON(+) "+
    			" AND MOHON.ID_PERMOHONAN = PERJANJIAN.ID_PERMOHONAN(+) "+
    			" AND MOHON.ID_PERMOHONAN = MOHONSEWA.ID_PERMOHONAN(+) "+
    			" AND MOHON.ID_PERMOHONAN = HMPERMOHONAN.ID_PERMOHONAN (+)"+
    			" AND HMPERMOHONAN.ID_HAKMILIKAGENSI = HMAGENSI.ID_HAKMILIKAGENSI(+) "+
    			" AND HMAGENSI.ID_HAKMILIK = HM.ID_HAKMILIK(+) "+
    			" AND FAIL.ID_URUSAN IN (7,12,13) "+
    			" AND MOHON.FLAG_AKTIF = 'Y' "+
    			" AND MOHON.FLAG_PERJANJIAN = 'U' "+
    			" AND HMAGENSI.ID_HAKMILIK = '"+idHakmilik+"'"+
				" ORDER BY PERJANJIAN.TARIKH_MULA_PERJANJIAN ASC ";
			   
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("sql:getMaklumatPHPPenyewaan : "+sql);
			Hashtable<String,String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("BIL", String.valueOf(bil));
				h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN")== null?"":rs.getString("ID_PERMOHONAN"));
				h.put("NO_FAIL", rs.getString("NO_FAIL")== null?"":rs.getString("NO_FAIL"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
				h.put("NAMA_PEMOHON", rs.getString("NAMA_PEMOHON")== null?"":rs.getString("NAMA_PEMOHON"));
				h.put("TUJUAN", rs.getString("TUJUAN")== null?"":rs.getString("TUJUAN"));
				h.put("NO_SIRI", rs.getString("NO_SIRI")== null?"":rs.getString("NO_SIRI"));
				h.put("TEMPOH", rs.getString("TEMPOH")== null?"":rs.getString("TEMPOH"));
				h.put("KADAR_SEWA", rs.getString("KADAR_SEWA")== null?"":rs.getString("KADAR_SEWA"));
				h.put("TARIKH_MULA_PERJANJIAN", rs.getString("TARIKH_MULA_PERJANJIAN")== null?"": sdf.format(rs.getDate("TARIKH_MULA_PERJANJIAN")));
	      		h.put("TARIKH_TAMAT_PERJANJIAN", rs.getString("TARIKH_MULA_PERJANJIAN")== null?"": sdf.format(rs.getDate("TARIKH_TAMAT_PERJANJIAN")));
	      		listDataSewa.addElement(h);
	      		bil++;
			}			
		}catch(Exception e){
			getIHTP().getErrorHTML(e.toString());
		}finally {
			if (db != null)db.close();
		}		
		return listDataSewa;		
	}//CLOSE getMaklumatPHPPenyewaan
	
	
	//PAPAR CARIAN HAKMILIK DAN RIZAB
	@Override
	public Vector<Hashtable<String,String>> getMaklumat(String idHakmilik)throws Exception {
	    Db db = null;
	    listCarianHakmilikDanRizab = new Vector<Hashtable<String,String>>();
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();  
	    	
	        sql = " SELECT  f.no_fail, tph.id_hakmilik,tph.no_hakmilik, tph.no_warta, hps.tujuan, "+
	        		" (SELECT CASE "+
						" WHEN rjh.kod_jenis_hakmilik = '00' "+
						" THEN '' "+
						" ELSE rjh.kod_jenis_hakmilik "+
						" END AS jenis_hakmilik "+
						" FROM tblrujjenishakmilik rjh "+
						" WHERE rjh.id_jenishakmilik = tph.id_jenishakmilik) jenis_hakmilik, "+
					" NVL ((SELECT keterangan FROM tblrujlot WHERE id_lot = tph.id_lot), '') jenis_lot, tph.no_lot, "+
					" f.no_fail, f.tajuk_fail, "+
					" (SELECT hpp.nama FROM tblphppemohon hpp WHERE hpp.id_pemohon = p.id_pemohon) pemohon, "+
					" NVL (hpp.no_siri, '') no_siri, "+
					" hpp.tarikh_mula_perjanjian, "+
					" hpp.tarikh_tamat_perjanjian, "+
					" NVL (hpp.kadar_sewa, '0.00') kadar_sewa, "+
					" NVL (hpp.cagaran, '0.00') cagaran, NVL (hpp.tempoh, '0') tempoh "+
					" FROM tblphphakmilikpermohonan hph, "+
					" tblhtphakmilik tph, "+
					" tblpfdfail f, "+
					" tblpermohonan p, "+
					" tblphpperjanjian hpp, "+
					" tblphppermohonansewa hps "+
					" WHERE tph.id_hakmilik = hph.id_hakmilik "+
					" AND hph.id_permohonan = p.id_permohonan "+
					" AND p.id_fail = f.id_fail "+
					" AND f.id_urusan = '7' "+
					" AND hph.id_permohonan = hpp.id_permohonan(+) "+
					" AND hps.id_permohonan = hph.id_permohonan "+
					" AND tph.id_hakmilik = '"+idHakmilik+"' "+
					" ORDER BY f.no_fail DESC ";  
			
			sql = sql +" ORDER BY F.NO_FAIL DESC ";
			myLog.debug("getMaklumat:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);	    
			Hashtable<String,String> h;
	      	int bil = 1;

	      	while (rs.next()) {
	      		h = new Hashtable<String,String>();
	      		h.put("bil", bil+".");
	      		h.put("idHakmilik",idHakmilik);
	      		h.put("noFail",rs.getString("NO_FAIL"));
	      		h.put("kodJenis", rs.getString("JENIS_HAKMILIK")== null?"":rs.getString("JENIS_HAKMILIK"));
	      		h.put("noHakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
	      		h.put("noWarta", rs.getString("NO_WARTA")== null?"":rs.getString("NO_WARTA"));
	      		h.put("kodLot", rs.getString("JENIS_LOT")== null?"":rs.getString("JENIS_LOT"));
	      		h.put("noLot", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
	      		h.put("pemohon", rs.getString("PEMOHON")== null?"":rs.getString("PEMOHON"));
	      		h.put("noRujukan", Utils.isNull(rs.getString("NO_SIRI")));
	      		h.put("tarikhMula",sdf.format(rs.getDate("TARIKH_MULA_PERJANJIAN")));
	      		h.put("tarikhTamat",sdf.format(rs.getDate("TARIKH_TAMAT_PERJANJIAN")));
	      		h.put("kadar", String.valueOf(rs.getString("KADAR_SEWA")== null?0.00:rs.getDouble("KADAR_SEWA")));
	      		h.put("tempoh", rs.getString("TEMPOH"));
	      		//h.put("cagaran", rs.getString("CAGARAN")== null? 0.00:rs.getDouble("CAGARAN"));
	    	  
	      		if(rs.getString("NO_HAKMILIK")!= null){
	      			h.put("jenisTanah","M");
	      		}else{
	      			h.put("jenisTanah","R");
	      		}
	      		if(rs.getString("NO_HAKMILIK")!= null && rs.getString("NO_WARTA")!= null){
	      			h.put("jenisTanah","X");
	      		}
	      		listCarianHakmilikDanRizab.addElement(h);
	      		bil++;
	    	  
	      	}
	    } catch (Exception e) {
	    	getIHTP().getErrorHTML(e.toString());

	    } finally {
	      if (db != null) db.close();
	    
	    }		
	    return listCarianHakmilikDanRizab;
	    
	}	
	
	private static Vector<Hashtable<String,String>> dataPHPPenyewaan = null;
	public Vector<Hashtable<String,String>> getDataPHPPenyewaan(){
		return dataPHPPenyewaan;
	}
	
	public void setDataPHPPenyewaan(String idPermohonan) throws Exception{		
		dataPHPPenyewaan = new Vector<Hashtable<String,String>>();
		dataPHPPenyewaan.clear();
		Db db = null;
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT MOHON.ID_PERMOHONAN, FAIL.NO_FAIL, HM.NO_HAKMILIK, PEMOHON.NAMA AS NAMA_PEMOHON, MOHONSEWA.TUJUAN , PERJANJIAN.NO_SIRI, PERJANJIAN.TARIKH_MULA_PERJANJIAN, "+
		    			" PERJANJIAN.TARIKH_TAMAT_PERJANJIAN, PERJANJIAN.TEMPOH, PERJANJIAN.KADAR_SEWA "+
		    			" FROM TBLPFDFAIL FAIL, TBLPERMOHONAN MOHON, "+
		    			" TBLPHPPERMOHONANSEWA MOHONSEWA, TBLPHPPEMOHON PEMOHON, "+ 
		    			" TBLPHPPERJANJIAN PERJANJIAN, TBLPHPHAKMILIKPERMOHONAN HMPERMOHONAN, "+ 
		    			" TBLHTPHAKMILIKAGENSI HMAGENSI, TBLHTPHAKMILIK HM "+
		    			" WHERE FAIL.ID_FAIL = MOHON.ID_FAIL(+) "+
		    			" AND MOHON.ID_PEMOHON = PEMOHON.ID_PEMOHON(+) "+
		    			" AND MOHON.ID_PERMOHONAN = PERJANJIAN.ID_PERMOHONAN(+) "+
		    			" AND MOHON.ID_PERMOHONAN = MOHONSEWA.ID_PERMOHONAN(+) "+
		    			" AND MOHON.ID_PERMOHONAN = HMPERMOHONAN.ID_PERMOHONAN(+) "+
		    			" AND HMPERMOHONAN.ID_HAKMILIKAGENSI = HMAGENSI.ID_HAKMILIKAGENSI(+) "+
		    			" AND HMAGENSI.ID_HAKMILIK = HM.ID_HAKMILIK(+) "+
		    			" AND FAIL.ID_URUSAN IN (7,12,13) "+
		    			" AND MOHON.FLAG_AKTIF = 'Y' "+
		    			" AND MOHON.FLAG_PERJANJIAN = 'U' "+
		    			" AND MOHON.ID_PERMOHONAN = '"+idPermohonan+"'";
				
				myLog.info("dataPHPPenyewaan :"+sql);
				
				ResultSet rs = stmt.executeQuery(sql);	
				
				while (rs.next()){
					Hashtable<String,String> h = new Hashtable<String,String>();
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN")== null?"":rs.getString("ID_PERMOHONAN"));
					h.put("NO_FAIL", rs.getString("NO_FAIL")== null?"":rs.getString("NO_FAIL"));
					h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
					h.put("NAMA_PEMOHON", rs.getString("NAMA_PEMOHON")== null?"":rs.getString("NAMA_PEMOHON"));
					h.put("TUJUAN", rs.getString("TUJUAN")== null?"":rs.getString("TUJUAN"));
					h.put("NO_SIRI", rs.getString("NO_SIRI")== null?"":rs.getString("NO_SIRI"));
					h.put("TEMPOH", rs.getString("TEMPOH")== null?"":rs.getString("TEMPOH"));
					h.put("KADAR_SEWA", rs.getString("KADAR_SEWA")== null?"":Utils.format2Decimal(rs.getDouble("KADAR_SEWA")));
					h.put("TARIKH_MULA_PERJANJIAN", rs.getString("TARIKH_MULA_PERJANJIAN")== null?"": sdf.format(rs.getDate("TARIKH_MULA_PERJANJIAN")));
		      		h.put("TARIKH_TAMAT_PERJANJIAN", rs.getString("TARIKH_TAMAT_PERJANJIAN")== null?"": sdf.format(rs.getDate("TARIKH_TAMAT_PERJANJIAN")));
				
					dataPHPPenyewaan.addElement(h);					
				}
	   
		}catch (SQLException se){
			getIHTP().getErrorHTML(se.toString());		
		}finally {
			if(db != null) db.close();
		}
	}//close setDataBorangK
	
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
		
	}
	
	
	
}
