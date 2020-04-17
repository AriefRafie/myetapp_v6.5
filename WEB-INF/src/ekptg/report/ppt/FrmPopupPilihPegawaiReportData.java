package ekptg.report.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;





public class FrmPopupPilihPegawaiReportData {
	
	static Logger myLogger = Logger.getLogger(FrmPopupPilihPegawaiReportData.class);

	private static Vector detailPegawai = null;
	private static Vector listTarikh = null;
	private static Vector malayDate = null;
	private static Vector malayDateByDate = null;
	
	
	private static Vector dataMT = null;
	private static Vector checkBorangG = null;
	
	public static Vector getListTarikhSiasatan() {
		return listTarikh;
	}
	
	public static  Vector getDetailPegawai(){
		return detailPegawai;
	}
	
	public static Vector getMalayDateByDate() {
		return malayDateByDate;
	}
	
	public static Vector getMalayDate() {
		return malayDate;
	}
	
	public static Vector getDataMahkamahTinggi() {
		return dataMT;
	}
	
	public static Vector getDataCheckBorangG() {
		return checkBorangG;
	}
	
	@SuppressWarnings("unchecked")
	public static void setDetailPegawai(String id_pegawai)throws Exception {
	    
		detailPegawai = new Vector();
		
		Db db = null;
		detailPegawai.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = " SELECT DISTINCT U.USER_ID AS ID_PEGAWAI, U.USER_NAME AS NAMA_PEGAWAI, INITCAP(J.KETERANGAN) AS JAWATAN, UI.ID_JAWATAN, ";
	    		sql += " UI.EMEL ";
	    		sql += " FROM USERS U, USERS_INTERNAL UI, TBLRUJJAWATAN J ";
	    		sql += " WHERE U.USER_ID = UI.USER_ID ";
	    		sql += " AND UI.ID_JAWATAN = J.ID_JAWATAN(+) ";
	    	    sql += " AND U.USER_ID = '"+id_pegawai+"'";
	    	
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;

	    		while (rs.next()) {
	    	  
	    			h = new Hashtable();
	    			h.put("emel", rs.getString("EMEL")== null?"":rs.getString("EMEL"));
	    			h.put("id_pegawai", rs.getString("ID_PEGAWAI")== null?"":rs.getString("ID_PEGAWAI"));
	    			h.put("id_jawatan", rs.getString("ID_JAWATAN")== null?"":rs.getString("ID_JAWATAN"));
	    			h.put("nama_pegawai", rs.getString("NAMA_PEGAWAI")== null?"":rs.getString("NAMA_PEGAWAI").toUpperCase());
	    			h.put("jawatan", rs.getString("JAWATAN")== null?"":rs.getString("JAWATAN"));
	    			
	    			detailPegawai.addElement(h);
	    	}			    
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close detail pegawai

	
	@SuppressWarnings("unchecked")
	public static void setListTarikhSiastan(String id_permohonan) throws Exception {
		
		listTarikh = new Vector();
		
		Db db = null;
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
			
				sql =  " SELECT DISTINCT C.TARIKH_SIASATAN, TO_CHAR(C.TARIKH_SIASATAN, 'DD') AS HARI_SURAT, ";
				sql += " CASE ";
				sql += "  WHEN TO_CHAR (C.TARIKH_SIASATAN, 'MM') = '01' THEN 'Januari' ";
				sql += "  WHEN TO_CHAR (C.TARIKH_SIASATAN, 'MM') = '02' THEN 'Februari' ";
				sql += "  WHEN TO_CHAR (C.TARIKH_SIASATAN, 'MM') = '03' THEN 'Mac' ";
				sql += "  WHEN TO_CHAR (C.TARIKH_SIASATAN, 'MM') = '04' THEN 'April' ";
				sql += "  WHEN TO_CHAR (C.TARIKH_SIASATAN, 'MM') = '05' THEN 'Mei' ";
				sql += "  WHEN TO_CHAR (C.TARIKH_SIASATAN, 'MM') = '06' THEN 'Jun' ";
				sql += "  WHEN TO_CHAR (C.TARIKH_SIASATAN, 'MM') = '07' THEN 'Julai' ";
				sql += "  WHEN TO_CHAR (C.TARIKH_SIASATAN, 'MM') = '08' THEN 'Ogos' ";
				sql += "  WHEN TO_CHAR (C.TARIKH_SIASATAN, 'MM') = '09' THEN 'September' ";
				sql += "  WHEN TO_CHAR (C.TARIKH_SIASATAN, 'MM') = '10' THEN 'Oktober' ";
				sql += "  WHEN TO_CHAR (C.TARIKH_SIASATAN, 'MM') = '11' THEN 'November' ";
				sql += "  WHEN TO_CHAR (C.TARIKH_SIASATAN, 'MM') = '12' THEN 'Disember' ";
				sql += " ELSE '' ";
				sql += " END AS BULAN_SURAT, ";
				sql += " TO_CHAR(C.TARIKH_SIASATAN, 'YYYY') AS TAHUN_SURAT ";
				sql += " FROM TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B, TBLPPTBORANGE C ";
				sql += " WHERE B.ID_PERMOHONAN = A.ID_PERMOHONAN ";
				sql += " AND C.ID_HAKMILIK = B.ID_HAKMILIK ";
				sql += " AND NVL(B.FLAG_PEMBATALAN_KESELURUHAN,0) <> 'Y' ";
				sql += " AND NVL(B.FLAG_PENARIKAN_KESELURUHAN,0) <> 'Y' ";
				sql += " AND A.ID_PERMOHONAN = '"+id_permohonan+"'";
				sql += " ORDER BY TAHUN_SURAT, BULAN_SURAT, HARI_SURAT ";
				
				ResultSet rs = stmt.executeQuery(sql);
				int bil = 1;
				
				while (rs.next()) {
					Hashtable h = new Hashtable();
					h.put("bil", bil);
					h.put("tarikh2Siasatan", rs.getString("TARIKH_SIASATAN")== null?"":rs.getString("HARI_SURAT")+" "+rs.getString("BULAN_SURAT")+" "+rs.getString("TAHUN_SURAT"));
					listTarikh.addElement(h);
					bil++;	
				}	
		} finally {
			if (db != null)
				db.close();
		}
		
	}//close setListTarikhSiastan
	
	
	@SuppressWarnings("unchecked")
	public static void setMalayDateByDate(String stgdate) throws Exception {
		
		malayDateByDate = new Vector();
		malayDateByDate.clear();
		
		Db db = null;
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
			
				sql =  " SELECT TO_CHAR(TO_DATE('"+stgdate+"','DD/MM/YYYY'),'DD/MM/YYYY') AS SDATE, TO_CHAR(TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'DD') AS SDATE_HARI, ";
				sql += " CASE ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '01' THEN 'Januari' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '02' THEN 'Februari' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '03' THEN 'Mac' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '04' THEN 'April' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '05' THEN 'Mei' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '06' THEN 'Jun' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '07' THEN 'Julai' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '08' THEN 'Ogos' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '09' THEN 'September' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '10' THEN 'Oktober' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '11' THEN 'November' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '12' THEN 'Disember' ";
				sql += " ELSE '' ";
				sql += " END AS SDATE_BULAN, ";
				sql += " TO_CHAR(TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'YYYY') AS SDATE_TAHUN FROM DUAL ";
				
				myLogger.info(" CHECK DATE BY DATE :"+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					Hashtable h = new Hashtable();
					h.put("sysdateMalayByDate", rs.getString("SDATE")== null?"":rs.getString("SDATE_HARI")+" "+rs.getString("SDATE_BULAN")+" "+rs.getString("SDATE_TAHUN"));
					malayDateByDate.addElement(h);
				}	
		} finally {
			if (db != null)
				db.close();
		}
		
	}//close setMalayDate
	
	@SuppressWarnings("unchecked")
	public static void setMalayDate() throws Exception {
		
		malayDate = new Vector();
		malayDate.clear();
		
		Db db = null;
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
			
				sql =  " SELECT SYSDATE, TO_CHAR(SYSDATE, 'DD') AS SYSDATE_HARI, ";
				sql += " CASE ";
				sql += " WHEN TO_CHAR (SYSDATE, 'MM') = '01' THEN 'Januari' ";
				sql += " WHEN TO_CHAR (SYSDATE, 'MM') = '02' THEN 'Februari' ";
				sql += " WHEN TO_CHAR (SYSDATE, 'MM') = '03' THEN 'Mac' ";
				sql += " WHEN TO_CHAR (SYSDATE, 'MM') = '04' THEN 'April' ";
				sql += " WHEN TO_CHAR (SYSDATE, 'MM') = '05' THEN 'Mei' ";
				sql += " WHEN TO_CHAR (SYSDATE, 'MM') = '06' THEN 'Jun' ";
				sql += " WHEN TO_CHAR (SYSDATE, 'MM') = '07' THEN 'Julai' ";
				sql += " WHEN TO_CHAR (SYSDATE, 'MM') = '08' THEN 'Ogos' ";
				sql += " WHEN TO_CHAR (SYSDATE, 'MM') = '09' THEN 'September' ";
				sql += " WHEN TO_CHAR (SYSDATE, 'MM') = '10' THEN 'Oktober' ";
				sql += " WHEN TO_CHAR (SYSDATE, 'MM') = '11' THEN 'November' ";
				sql += " WHEN TO_CHAR (SYSDATE, 'MM') = '12' THEN 'Disember' ";
				sql += " ELSE '' ";
				sql += " END AS SYSDATE_BULAN, ";
				sql += " TO_CHAR(SYSDATE, 'YYYY') AS SYSDATE_TAHUN FROM DUAL ";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					Hashtable h = new Hashtable();
					h.put("sysdateMalay", rs.getString("SYSDATE")== null?"":rs.getString("SYSDATE_HARI")+" "+rs.getString("SYSDATE_BULAN")+" "+rs.getString("SYSDATE_TAHUN"));
					malayDate.addElement(h);
				}	
		} finally {
			if (db != null)
				db.close();
		}
		
	}//close setMalayDate
	
	@SuppressWarnings("unchecked")
	public static void setDataMahkamahTinggi(String id_negeri) throws Exception {
		
		dataMT = new Vector();
		
		Db db = null;
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
			
				sql =  " SELECT DISTINCT NAMA_PEJABAT FROM TBLRUJPEJABAT ";
				sql += " WHERE ID_JENISPEJABAT = '8' ";

				if(id_negeri.equals("15") || id_negeri.equals("16") ){
					sql += " AND ID_NEGERI = '14'";
				}else{
					sql += " AND ID_NEGERI = '"+id_negeri+"'";
				}
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					Hashtable h = new Hashtable();
					h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT")== null?"":rs.getString("NAMA_PEJABAT"));
					dataMT.addElement(h);
				}	
		} finally {
			if (db != null)
				db.close();
		}
		
	}//close setDataMahkamahTinggi
	
	
	@SuppressWarnings("unchecked")
	public static void setDataCheckBorangG(String id_hakmilik,String report,String bydate) throws Exception {
		
		checkBorangG = new Vector();
		
		Db db = null;
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
			
				sql =  " SELECT (SELECT COUNT(aw.status_penerima) ";
				sql += " FROM tblppthakmilik a, ";
				sql += " tblppthakmilikpb b, ";
				sql += " tblpptpihakberkepentingan d, ";
    			sql += " tblpptaward aw, ";
    			sql += " tblpptpermohonan p, ";
    			sql += " tblpfdfail f ";
    			sql += " WHERE a.id_hakmilik = b.id_hakmilik ";
    			sql += " AND a.id_permohonan = p.id_permohonan ";
    			sql += " AND p.id_fail = f.id_fail ";
    			sql += " AND aw.id_hakmilikpb = b.id_hakmilikpb ";
    			sql += " AND b.id_pihakberkepentingan = d.id_pihakberkepentingan ";
    			sql += " AND NVL (b.flag_borangg, '0') = '1' ";
    			sql += " AND b.id_hakmilikpb IN ( ";
    			sql += " SELECT DISTINCT hpb.id_hakmilikpb ";
    			sql += " FROM tblppthakmilikpb hpb, ";
    			sql += " tblpptaward j1, ";
    			sql += " tblpptsubaward sd ";
    			sql += " WHERE sd.id_award = j1.id_award ";
    			sql += " AND hpb.id_hakmilikpb = j1.id_hakmilikpb ";
    			sql += " AND hpb.id_hakmilik = a.id_hakmilik ";
    			sql += " AND sd.flag_jenis_award <> 'BAYAR_LAIN') ";
    			sql += " AND a.id_hakmilik = '"+id_hakmilik+"' ";
    			sql += " AND AW.STATUS_PENERIMA = '2')AS BORANGG_MT, ";
    			sql += " (SELECT COUNT(aw.status_penerima) ";
    			sql += " FROM tblppthakmilik a, ";
    			sql += " tblppthakmilikpb b, ";
    			sql += " tblpptpihakberkepentingan d, ";
    			sql += " tblpptaward aw, ";
    			sql += " tblpptpermohonan p, ";
    			sql += " tblpfdfail f ";
    			sql += " WHERE a.id_hakmilik = b.id_hakmilik ";
    			sql += "AND a.id_permohonan = p.id_permohonan ";
    			sql += "AND p.id_fail = f.id_fail ";
    			sql += "AND aw.id_hakmilikpb = b.id_hakmilikpb ";
    			sql += "AND b.id_pihakberkepentingan = d.id_pihakberkepentingan ";
    			sql += " AND NVL (b.flag_borangg, '0') = '1' ";
    			sql += "AND b.id_hakmilikpb IN ( ";
    			sql += "SELECT DISTINCT hpb.id_hakmilikpb ";
    			sql += "FROM tblppthakmilikpb hpb, ";
    			sql += "tblpptaward j1, ";
    			sql += "tblpptsubaward sd ";
    			sql += "WHERE sd.id_award = j1.id_award ";
    			sql += "AND hpb.id_hakmilikpb = j1.id_hakmilikpb ";
    			sql += "AND hpb.id_hakmilik = a.id_hakmilik ";
    			sql += "AND sd.flag_jenis_award <> 'BAYAR_LAIN') ";
    			sql += "AND a.id_hakmilik = '"+id_hakmilik+"' ";
    			sql += "AND AW.STATUS_PENERIMA = '3')AS BORANGG_ARB ";      
    			sql += "FROM DUAL ";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					Hashtable h = new Hashtable();
					h.put("BORANGG_MT", rs.getString("BORANGG_MT")== null?"":rs.getInt("BORANGG_MT"));
					h.put("BORANGG_ARB", rs.getString("BORANGG_ARB")== null?"":rs.getInt("BORANGG_ARB"));
					checkBorangG.addElement(h);
				}	
		} finally {
			if (db != null)
				db.close();
		}
		
	}//close setDataCheckBorangG
	
}
