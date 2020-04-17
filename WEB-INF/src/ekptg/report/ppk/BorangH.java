package ekptg.report.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class BorangH extends EkptgReportServlet {
	Vector obMinorTakSempurnaAkal = null;
	Vector obMinorBlmDewasa = null;
	Vector dataObMinorTakSempurnaAkal = null;
	Vector dataObMinorBlmDewasa = null;
	Vector dataGroupPenjagaBelumDewasa = null;
	Vector dataGroupMinor = null;
	Vector dataGroupPenjagaTakSempurnaAkal = null;
	Vector obMinor = null;
	Vector dataObMinor = null;
	
	Vector penjaga = null;
	Vector dataPenjaga = null;
	static Logger myLogger = Logger.getLogger(BorangH.class);
	public BorangH() {
		super.setReportName("BorangH");
		super.setFolderName("ppk");
	}
	
	public Vector getOBMinor(String idfail)throws Exception{
		
		String sql = "";
		Db db = null;
		
		try{
			db = new Db();
			obMinor = new Vector();
			
			sql = " SELECT D.ID_OB,D.NAMA_OB,";
			sql +=" CASE ";
			sql +=" WHEN D.STATUS_OB = 2 THEN 'belum dewasa' ";
			sql +=" WHEN D.STATUS_OB = 4 THEN 'sempurna akal' ";
			sql +=" END AS STATUS_OB, ";
			sql +=" CASE ";
			sql +=" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN '' ";
			sql +=" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||'' ";
			sql +=" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4) ";
			sql +=" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'";
			sql +=" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')' ";
			sql +=" END  AS KP_WARIS, ";
			sql +=" CASE ";
			sql +=" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN '' ";
			sql +=" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||'' ";
			sql +=" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')' ";
			sql +=" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'";
			sql +=" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')' ";
			sql +=" END  AS KP_WARIS_2 ";
			sql +=" FROM TBLPFDFAIL A, ";
			sql +=" TBLPPKPERMOHONAN B, ";
			sql +=" TBLPPKPERMOHONANSIMATI C, ";
			sql +=" TBLPPKOB D, ";    
			sql +=" (SELECT ";
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA "; 
			sql +=" ELSE TBLPPKOB.NO_KP_BARU ";
			sql +=" END || '' || ";    
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA ";
			sql +=" END || '' || ";    
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN ";     
			sql +=" END AS NO_KP4, ID_OB ";    
			sql +=" FROM TBLPPKOB ) DDD, ";
			sql +=" (SELECT ";
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA ";
			sql +=" ELSE TBLPPKOB.NO_KP_BARU ";
			sql +=" END || '' || ";    
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA ";
			sql +=" END || '' || ";    
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN ";    
			sql +=" END AS NO_KP42, ID_OB ";    
			sql +=" FROM TBLPPKOB ) DDD2 ";  
			sql +=" WHERE A.ID_FAIL = B.ID_FAIL ";
			sql +=" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
			sql +=" AND C.ID_SIMATI = D.ID_SIMATI ";
			sql +=" AND D.ID_OB = DDD.ID_OB ";
			sql +=" AND D.ID_OB = DDD2.ID_OB ";
			sql +=" AND D.STATUS_OB IN (2,4) ";
			sql +=" AND A.ID_FAIL = '"+idfail+"'";
		
			Statement stmt = db.getStatement();
			
			myLogger.info("H :::: getOBMinor : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			

			Hashtable h;
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("NAMA_OB",rs.getString("NAMA_OB")==null?"":rs.getString("NAMA_OB"));
				h.put("KP_WARIS_2",rs.getString("KP_WARIS_2")==null?"":rs.getString("KP_WARIS_2"));
				h.put("STATUS_OB",rs.getString("STATUS_OB")==null?"":rs.getString("STATUS_OB"));
				h.put("ID_OB",rs.getString("ID_OB")==null?"":rs.getString("ID_OB"));
				obMinor.addElement(h);
				
			}
			
			
			return obMinor;
			
		}finally {
			if (db != null)
				db.close();
		}
		
	}
	
	public Vector getOBMinorTidakSempurnaAkal(String idfail)throws Exception{
		
		String sql = "";
		Db db = null;
		
		try{
			db = new Db();
			obMinorTakSempurnaAkal = new Vector();
			
			sql = " SELECT D.ID_OB,D.NAMA_OB,";
			sql +=" CASE ";
			sql +=" WHEN D.STATUS_OB = 2 THEN 'belum dewasa' ";
			sql +=" WHEN D.STATUS_OB = 4 THEN 'sempurna akal' ";
			sql +=" END AS STATUS_OB, ";
			sql +=" CASE ";
			sql +=" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN '' ";
			sql +=" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||'' ";
			sql +=" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4) ";
			sql +=" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'";
			sql +=" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')' ";
			sql +=" END  AS KP_WARIS, ";
			sql +=" CASE ";
			sql +=" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN '' ";
			sql +=" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||'' ";
			sql +=" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')' ";
			sql +=" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'";
			sql +=" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')' ";
			sql +=" END  AS KP_WARIS_2 ";
			sql +=" FROM TBLPFDFAIL A, ";
			sql +=" TBLPPKPERMOHONAN B, ";
			sql +=" TBLPPKPERMOHONANSIMATI C, ";
			sql +=" TBLPPKOB D, ";    
			sql +=" (SELECT ";
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA "; 
			sql +=" ELSE TBLPPKOB.NO_KP_BARU ";
			sql +=" END || '' || ";    
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA ";
			sql +=" END || '' || ";    
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN ";     
			sql +=" END AS NO_KP4, ID_OB ";    
			sql +=" FROM TBLPPKOB ) DDD, ";
			sql +=" (SELECT ";
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA ";
			sql +=" ELSE TBLPPKOB.NO_KP_BARU ";
			sql +=" END || '' || ";    
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA ";
			sql +=" END || '' || ";    
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN ";    
			sql +=" END AS NO_KP42, ID_OB ";    
			sql +=" FROM TBLPPKOB ) DDD2 ";  
			sql +=" WHERE A.ID_FAIL = B.ID_FAIL ";
			sql +=" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
			sql +=" AND C.ID_SIMATI = D.ID_SIMATI ";
			sql +=" AND D.ID_OB = DDD.ID_OB ";
			sql +=" AND D.ID_OB = DDD2.ID_OB ";
			sql +=" AND D.STATUS_OB IN (4) ";
			sql +=" AND A.ID_FAIL = '"+idfail+"'";
			
			Statement stmt = db.getStatement();
			myLogger.info("H :::: getOBMinorTidakSempurnaAkal : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			

			Hashtable h;
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("NAMA_OB",rs.getString("NAMA_OB")==null?"":rs.getString("NAMA_OB"));
				h.put("KP_WARIS_2",rs.getString("KP_WARIS_2")==null?"":rs.getString("KP_WARIS_2"));
				h.put("STATUS_OB",rs.getString("STATUS_OB")==null?"":rs.getString("STATUS_OB"));
				h.put("ID_OB",rs.getString("ID_OB")==null?"":rs.getString("ID_OB"));
				obMinorTakSempurnaAkal.addElement(h);
				
			}
			
			
			return obMinorTakSempurnaAkal;
			
		}finally {
			if (db != null)
				db.close();
		}
		
	}
	
	public Vector getOBMinorBlmDewasa(String idfail)throws Exception{
		
		String sql = "";
		Db db = null;
		
		try{
			db = new Db();
			obMinorBlmDewasa = new Vector();
			
			sql = " SELECT D.ID_OB,D.NAMA_OB,";
			sql +=" CASE ";
			sql +=" WHEN D.STATUS_OB = 2 THEN 'belum dewasa' ";
			sql +=" WHEN D.STATUS_OB = 4 THEN 'sempurna akal' ";
			sql +=" END AS STATUS_OB, ";
			sql +=" CASE ";
			sql +=" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN '' ";
			sql +=" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||'' ";
			sql +=" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4) ";
			sql +=" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'";
			sql +=" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')' ";
			sql +=" END  AS KP_WARIS, ";
			sql +=" CASE ";
			sql +=" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN '' ";
			sql +=" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||'' ";
			sql +=" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')' ";
			sql +=" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'";
			sql +=" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')' ";
			sql +=" END  AS KP_WARIS_2 ";
			sql +=" FROM TBLPFDFAIL A, ";
			sql +=" TBLPPKPERMOHONAN B, ";
			sql +=" TBLPPKPERMOHONANSIMATI C, ";
			sql +=" TBLPPKOB D, ";    
			sql +=" (SELECT ";
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA "; 
			sql +=" ELSE TBLPPKOB.NO_KP_BARU ";
			sql +=" END || '' || ";    
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA ";
			sql +=" END || '' || ";    
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN ";     
			sql +=" END AS NO_KP4, ID_OB ";    
			sql +=" FROM TBLPPKOB ) DDD, ";
			sql +=" (SELECT ";
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA ";
			sql +=" ELSE TBLPPKOB.NO_KP_BARU ";
			sql +=" END || '' || ";    
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA ";
			sql +=" END || '' || ";    
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN ";    
			sql +=" END AS NO_KP42, ID_OB ";    
			sql +=" FROM TBLPPKOB ) DDD2 ";  
			sql +=" WHERE A.ID_FAIL = B.ID_FAIL ";
			sql +=" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
			sql +=" AND C.ID_SIMATI = D.ID_SIMATI ";
			sql +=" AND D.ID_OB = DDD.ID_OB ";
			sql +=" AND D.ID_OB = DDD2.ID_OB ";
			sql +=" AND D.STATUS_OB IN (2) ";
			sql +=" AND A.ID_FAIL = '"+idfail+"'";
			
			Statement stmt = db.getStatement();
			myLogger.info("H :::: getOBMinorBlmDewasa : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			

			Hashtable h;
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("NAMA_OB",rs.getString("NAMA_OB")==null?"":rs.getString("NAMA_OB"));
				h.put("KP_WARIS_2",rs.getString("KP_WARIS_2")==null?"":rs.getString("KP_WARIS_2"));
				h.put("STATUS_OB",rs.getString("STATUS_OB")==null?"":rs.getString("STATUS_OB"));
				h.put("ID_OB",rs.getString("ID_OB")==null?"":rs.getString("ID_OB"));
				obMinorBlmDewasa.addElement(h);
				
			}
			
			
			return obMinorBlmDewasa;
			
		}finally {
			if (db != null)
				db.close();
		}
		
	}
	
	
	
    
public Vector getGroupPenjaga(String id_fail, String jenis_ob)throws Exception{
		
		String sql = "";
		Db db = null;
		
		try{
			db = new Db();
			Vector list = new Vector();
			
			sql  = " SELECT OB.ID_OB, NVL(COUNT(PENJAGA.ID_OBMINOR),0) AS JUMLAH_MINOR, "+
					" RTRIM (XMLAGG (XMLELEMENT (E, PENJAGA.ID_OBMINOR || ',')).EXTRACT ('//text()'), ',') AS SET_ID_MINOR,  "+
					" UPPER(OB.NAMA_OB) AS NAMA_PENJAGA, 	(CASE 	WHEN OB.NO_KP_BARU IS NOT NULL AND LENGTH(OB.NO_KP_BARU) = 12  "+
					" THEN SUBSTR(OB.NO_KP_BARU,1,6) || '-' || SUBSTR(OB.NO_KP_BARU,7,2) || '-' || SUBSTR(OB.NO_KP_BARU,9,4)  "+
					" WHEN OB.NO_KP_LAMA IS NOT NULL THEN UPPER(OB.NO_KP_LAMA) WHEN OB.NO_KP_LAIN IS NOT NULL THEN  "+
					" (CASE WHEN OB.JENIS_KP = '5' THEN 'Tentera : ' WHEN OB.JENIS_KP = '6' THEN 'Polis : ' "+
					" WHEN OB.JENIS_KP = '4' THEN 'Passport : ' WHEN OB.JENIS_KP = '7' THEN 'Lain-lain : ' "+
					" ELSE '' END) ||  UPPER(OB.NO_KP_LAIN) ELSE '' END) AS KP_PENJAGA, CASE   "+
					" WHEN OB.ALAMAT_1 IS NULL THEN 'TIADA'   "+
					" WHEN OB.ALAMAT_2 IS NULL AND OB.ALAMAT_3 IS NULL THEN  REPLACE(REPLACE(UPPER(trim(OB.ALAMAT_1)),','),'&','&#38;') " +
					" || ', ' || REPLACE(REPLACE(UPPER(OB.POSKOD),','),'&','&#38;') || ' ' || REPLACE(REPLACE(UPPER(trim(B.KETERANGAN)),','),'&','&#38;') " +
					" ||', '|| REPLACE(REPLACE(UPPER(trim(N.NAMA_NEGERI)),','),'&','&#38;')  "+
					" WHEN OB.ALAMAT_2 IS NULL THEN REPLACE(REPLACE(UPPER(trim(OB.ALAMAT_1)),','),'&','&#38;') ||', '  "+
					" || REPLACE(REPLACE(UPPER(trim(OB.ALAMAT_3)),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(trim(OB.POSKOD)),','),'&','&#38;')  "+
					" || ' ' ||REPLACE(REPLACE(UPPER(trim(B.KETERANGAN)),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(trim(N.NAMA_NEGERI)),','),'&','&#38;') "+ 
					" WHEN OB.ALAMAT_3 IS NULL THEN REPLACE(REPLACE(UPPER(trim(OB.ALAMAT_1)),','),'&','&#38;')  "+
					" ||', ' || REPLACE(REPLACE(UPPER(trim(OB.ALAMAT_2)),','),'&','&#38;') || ', '  "+
					" || REPLACE(REPLACE(UPPER(trim(OB.POSKOD)),','),'&','&#38;') || ' ' ||REPLACE(REPLACE(UPPER(trim(B.KETERANGAN)),','),'&','&#38;') ||', ' "+
					" || REPLACE(REPLACE(UPPER(trim(N.NAMA_NEGERI)),','),'&','&#38;')  "+
					" WHEN OB.ALAMAT_3 IS NOT NULL THEN REPLACE(REPLACE(UPPER(trim(OB.ALAMAT_1)),','),'&','&#38;') "+
					" ||', ' || REPLACE(REPLACE(UPPER(trim(OB.ALAMAT_2)),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(trim(OB.ALAMAT_3)),','),'&','&#38;')  "+
					" || ', ' || REPLACE(REPLACE(UPPER(trim(OB.POSKOD)),','),'&','&#38;') || ' ' || REPLACE(REPLACE(UPPER(trim(B.KETERANGAN)),','),'&','&#38;')  "+
					" ||', '|| REPLACE(REPLACE(UPPER(trim(N.NAMA_NEGERI)),','),'&','&#38;')   "+
					" ELSE 'TIADA'      "+
					" END AS ALAMAT_PENUH_PENJAGA "+
					" FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKPERMOHONANSIMATI PSM, TBLPPKOB OB,TBLPPKOB OBMINOR, TBLPPKPENJAGA PENJAGA,  "+
					" TBLRUJJENISNOPB JPB, TBLRUJNEGERI N, TBLRUJBANDAR B "+
					" WHERE P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND OB.ID_SIMATI = PSM.ID_SIMATI AND PENJAGA.ID_OBMINOR = OBMINOR.ID_OB "+
					" AND PENJAGA.ID_OB = OB.ID_OB AND OB.JENIS_KP = JPB.ID_JENISNOPB(+) AND OB.ID_NEGERI = N.ID_NEGERI(+) AND OB.ID_BANDAR = B.ID_BANDAR(+) "+
					" AND F.ID_FAIL = '"+id_fail+"' AND OBMINOR.STATUS_OB = '"+jenis_ob+"' "+
					" GROUP BY OB.ID_OB, OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA, OB.NO_KP_LAIN, OB.JENIS_KP,OB.ALAMAT_1,OB.ALAMAT_2,OB.ALAMAT_3,OB.POSKOD, B.KETERANGAN, N.NAMA_NEGERI "; 
			
													
			Statement stmt = db.getStatement();
			myLogger.info("H :::: getGroupPenjaga : "+sql);
			ResultSet rs = stmt.executeQuery(sql);			
			
			Hashtable h;
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_OB",rs.getString("ID_OB")==null?"":rs.getString("ID_OB"));
				h.put("JUMLAH_MINOR",rs.getString("JUMLAH_MINOR")==null?0:rs.getInt("JUMLAH_MINOR"));
				h.put("SET_ID_MINOR",rs.getString("SET_ID_MINOR")==null?"":rs.getString("SET_ID_MINOR"));
				h.put("NAMA_PENJAGA",rs.getString("NAMA_PENJAGA")==null?"":rs.getString("NAMA_PENJAGA"));				
				h.put("KP_PENJAGA",rs.getString("KP_PENJAGA")==null?"":rs.getString("KP_PENJAGA"));
				h.put("ALAMAT_PENUH_PENJAGA",rs.getString("ALAMAT_PENUH_PENJAGA")==null?"":rs.getString("ALAMAT_PENUH_PENJAGA"));
				list.addElement(h);			
			}			
				
			return list;
			
		}finally {
			if (db != null)
				db.close();
		}
		
		
	}
    

	public Vector getGroupMinor(String set_id, String jenis_ob)throws Exception{
		
		String sql = "";
		Db db = null;
		
		try{
			db = new Db();
			Vector getGroupMinor = new Vector();
			
			//sql = " SELECT D.ID_OB,D.NAMA_OB,";//comment by en peje 27122017 - untuk distictkan obminor
			sql = " SELECT distinct D.ID_OB,D.NAMA_OB,";
			sql +=" CASE ";
			sql +=" WHEN D.STATUS_OB = 2 THEN 'belum dewasa' ";
			sql +=" WHEN D.STATUS_OB = 4 THEN 'sempurna akal' ";
			sql +=" END AS STATUS_OB, ";
			sql +=" CASE ";
			sql +=" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN '' ";
			sql +=" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||'' ";
			sql +=" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4) ";
			sql +=" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'";
			sql +=" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')' ";
			sql +=" END  AS KP_WARIS, ";
			sql +=" CASE ";
			sql +=" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN '' ";
			sql +=" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||'' ";
			sql +=" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')' ";
			sql +=" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'";
			sql +=" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')' ";
			sql +=" END  AS KP_WARIS_2 ";
			sql +=" FROM TBLPFDFAIL A, ";
			sql +=" TBLPPKPERMOHONAN B, ";
			sql +=" TBLPPKPERMOHONANSIMATI C, ";
			sql +=" TBLPPKOB D, ";    
			sql +=" (SELECT ";
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA "; 
			sql +=" ELSE TBLPPKOB.NO_KP_BARU ";
			sql +=" END || '' || ";    
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA ";
			sql +=" END || '' || ";    
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN ";     
			sql +=" END AS NO_KP4, ID_OB ";    
			sql +=" FROM TBLPPKOB ) DDD, ";
			sql +=" (SELECT ";
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA ";
			sql +=" ELSE TBLPPKOB.NO_KP_BARU ";
			sql +=" END || '' || ";    
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA ";
			sql +=" END || '' || ";    
			sql +=" CASE ";
			sql +=" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN ";    
			sql +=" END AS NO_KP42, ID_OB ";    
			sql +=" FROM TBLPPKOB ) DDD2 ";  
			sql +=" WHERE A.ID_FAIL = B.ID_FAIL ";
			sql +=" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
			sql +=" AND C.ID_SIMATI = D.ID_SIMATI ";
			sql +=" AND D.ID_OB = DDD.ID_OB ";
			sql +=" AND D.ID_OB = DDD2.ID_OB ";
			sql +=" AND D.STATUS_OB = '"+jenis_ob+"' ";
			sql +=" AND D.ID_OB IN ("+set_id+") ";
		
			Statement stmt = db.getStatement();
			
			myLogger.info("H :::: getGroupMinor : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
	
			Hashtable h;
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("NAMA_OB",rs.getString("NAMA_OB")==null?"":rs.getString("NAMA_OB"));
				h.put("KP_WARIS_2",rs.getString("KP_WARIS_2")==null?"":rs.getString("KP_WARIS_2"));
				h.put("STATUS_OB",rs.getString("STATUS_OB")==null?"":rs.getString("STATUS_OB"));
				h.put("ID_OB",rs.getString("ID_OB")==null?"":rs.getString("ID_OB"));
				getGroupMinor.addElement(h);
				
			}
			
			
			return getGroupMinor;
			
		}finally {
			if (db != null)
				db.close();
		}
		
	}
    
    
	public Vector getPenjaga(String idob)throws Exception{
		
		String sql = "";
		Db db = null;
		
		try{
			db = new Db();
			penjaga = new Vector();
			
			sql  =" SELECT DISTINCT ";   
			sql +=" " +
					//" UPPER(E.NAMA_PENJAGA) " +
					" UPPER(jaga.nama_ob) "+
					" AS NAMA_PENJAGA, "; 
			sql +=" CASE "; 
			sql +=" WHEN LENGTH (AAA.NO_KP1) IS NULL THEN '' "; 
			sql +=" WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||'' "; 
			sql +=" WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4) ";  
			sql +=" ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')' "; 
			sql +=" END  AS KP_PENJAGA, "; 
			sql +=" CASE "; 
			sql +=" WHEN E.ALAMAT1 IS NULL THEN 'TIADA' "; 
			sql +=" WHEN E.ALAMAT2 IS NULL AND E.ALAMAT3 IS NULL THEN  REPLACE(REPLACE(UPPER(trim(E.ALAMAT1)),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(E.POSKOD),','),'&','&#38;') || ' ' || REPLACE(REPLACE(UPPER(trim(E.BANDAR)),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(trim(F.NAMA_NEGERI)),','),'&','&#38;') "; 
			sql +=" WHEN E.ALAMAT2 IS NULL THEN REPLACE(REPLACE(UPPER(trim(E.ALAMAT1)),','),'&','&#38;') ||', ' || REPLACE(REPLACE(UPPER(trim(E.ALAMAT3)),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(trim(E.POSKOD)),','),'&','&#38;') || ' ' ||REPLACE(REPLACE(UPPER(trim(E.BANDAR)),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(trim(F.NAMA_NEGERI)),','),'&','&#38;') "; 
			sql +=" WHEN E.ALAMAT3 IS NULL THEN REPLACE(REPLACE(UPPER(trim(E.ALAMAT1)),','),'&','&#38;') ||', ' || REPLACE(REPLACE(UPPER(trim(E.ALAMAT2)),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(trim(E.POSKOD)),','),'&','&#38;') || ' ' ||REPLACE(REPLACE(UPPER(trim(E.BANDAR)),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(trim(F.NAMA_NEGERI)),','),'&','&#38;') "; 
			sql +=" WHEN E.ALAMAT3 IS NOT NULL THEN REPLACE(REPLACE(UPPER(trim(E.ALAMAT1)),','),'&','&#38;')||', ' || REPLACE(REPLACE(UPPER(trim(E.ALAMAT2)),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(trim(E.ALAMAT3)),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(trim(E.POSKOD)),','),'&','&#38;') || ' ' || REPLACE(REPLACE(UPPER(trim(E.BANDAR)),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(trim(F.NAMA_NEGERI)),','),'&','&#38;') "; 
			sql +=" ELSE 'TIADA' ";    
			sql +=" END AS ALAMAT_PENUH_PENJAGA "; 
			sql +=" FROM TBLPFDFAIL A, "; 
			sql +=" TBLPPKPERMOHONAN B, "; 
			sql +=" TBLPPKPERMOHONANSIMATI C, "; 
			sql +=" TBLPPKOB D, "; 
			sql +=" TBLPPKPENJAGA E, "; 
			sql +=" TBLRUJNEGERI F, "; 
			
			//tambah
			sql += " tblppkob jaga, ";
			
			sql +=" (SELECT "; 
			sql +=" CASE "; 
			sql +=" WHEN TBLPPKPENJAGA.NO_KP_BARU IS NULL AND TBLPPKPENJAGA.NO_KP_LAMA IS NOT NULL THEN  TBLPPKPENJAGA.NO_KP_LAMA "; 
			sql +=" WHEN TBLPPKPENJAGA.NO_KP_BARU IS NULL AND TBLPPKPENJAGA.NO_KP_LAMA IS NULL THEN  TBLPPKPENJAGA.NO_KP_LAIN "; 
			sql +=" WHEN TBLPPKPENJAGA.NO_KP_BARU IS NULL AND TBLPPKPENJAGA.NO_KP_LAIN IS NULL THEN  TBLPPKPENJAGA.NO_KP_LAMA "; 
			sql +=" ELSE TBLPPKPENJAGA.NO_KP_BARU "; 
			sql +=" END || '' || ";     
			sql +=" CASE "; 
			sql +=" WHEN TBLPPKPENJAGA.NO_KP_BARU IS NOT NULL AND TBLPPKPENJAGA.NO_KP_LAMA IS NOT NULL THEN TBLPPKPENJAGA.NO_KP_LAMA "; 
			sql +=" END || '' || ";     
			sql +=" CASE "; 
			sql +=" WHEN TBLPPKPENJAGA.NO_KP_BARU IS  NULL AND TBLPPKPENJAGA.NO_KP_LAMA IS NOT NULL THEN TBLPPKPENJAGA.NO_KP_LAIN ";      
			sql +=" END AS NO_KP1, ID_PENJAGA ";     
			sql +=" FROM TBLPPKPENJAGA ) AAA "; 
			sql +=" WHERE A.ID_FAIL = B.ID_FAIL "; 
			sql +=" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "; 
			sql +=" AND C.ID_SIMATI = D.ID_SIMATI "; 
			sql +=" AND D.ID_OB = E.ID_OBMINOR "; 
			sql +=" AND E.ID_PENJAGA = AAA.ID_PENJAGA "; 
			sql +=" AND D.STATUS_OB IN (2,4) ";  
			sql +=" AND F.ID_NEGERI = E.ID_NEGERI "; 
			
			//tambah
			sql += "  AND jaga.id_ob = e.id_ob       ";
			
			sql +=" AND E.ID_OBMINOR = '"+ idob +"' "; 
			
													
			Statement stmt = db.getStatement();
			myLogger.info("H :::: getPenjaga : "+sql);
			ResultSet rs = stmt.executeQuery(sql);			
			
			Hashtable h;
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("NAMA_PENJAGA",rs.getString("NAMA_PENJAGA")==null?"":rs.getString("NAMA_PENJAGA"));
				h.put("KP_PENJAGA",rs.getString("KP_PENJAGA")==null?"":rs.getString("KP_PENJAGA"));
				h.put("ALAMAT_PENUH_PENJAGA",rs.getString("ALAMAT_PENUH_PENJAGA")==null?"":rs.getString("ALAMAT_PENUH_PENJAGA"));
				penjaga.addElement(h);			
			}			
				
			return penjaga;
			
		}finally {
			if (db != null)
				db.close();
		}
		
		
	}
	
	
	
	public String getListMinor(String set_id, String jenis_ob) throws Exception
	{
		String set_data_minor ="";
		dataGroupMinor = getGroupMinor(set_id, jenis_ob);
		for (int i = 0; i < dataGroupMinor.size();i++){
			Hashtable hOb = (Hashtable)dataGroupMinor.get(i);
			
			if (dataGroupMinor.size() - i != 1 ){
				
				if(set_data_minor == ""){
					set_data_minor = hOb.get("NAMA_OB")+" " +hOb.get("KP_WARIS_2");
				}
				else{
					set_data_minor = set_data_minor + ", " + hOb.get("NAMA_OB")+" " +hOb.get("KP_WARIS_2");
				}
				
				
			}
			else{
				
				if(set_data_minor == ""){
					set_data_minor = hOb.get("NAMA_OB")+" " +hOb.get("KP_WARIS_2");
				}
				else{
					set_data_minor = set_data_minor + " dan " + hOb.get("NAMA_OB")+" " +hOb.get("KP_WARIS_2");
				}
				
			}
			
		}
		/*
		if(jenis_ob.equals("4"))
		{		
			if (dataGroupMinor.size() == 1 ){
				set_data_minor = set_data_minor + " adalah seorang yang tidak sempurna akal";
			}
			else{
				set_data_minor = set_data_minor + " adalah tidak sempurna akal";
			}
		}
		*/
		return set_data_minor;
	}
	
	
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String idfail = "";
		if (parameters.get("idfail") != null){
			idfail = parameters.get("idfail").toString();
		}
		
		dataObMinor = new Vector();
		dataObMinor = getOBMinor(idfail);
		
		dataObMinorBlmDewasa = new Vector();
		dataObMinorBlmDewasa = getOBMinorBlmDewasa(idfail);
		
		dataObMinorTakSempurnaAkal = new Vector();
		dataObMinorTakSempurnaAkal = getOBMinorTidakSempurnaAkal(idfail);
				
		String tempBlmDewasa = "";
		String tempTakSempurnaAkal = "";
		String tempPenjagaBlmDewasa = "";
		String tempPenjagaTakSempurnaAkal = "";
		String temp1 = "";
		String temp2 = "";
		String temp3 = "";
		String temp4 = "";
		String temp5 = "";
		String temp6 = "";
		String temp7 = "";
		
		Hashtable hOb = null;
		
		for (int i = 0; i < dataObMinor.size();i++){
			hOb = (Hashtable)dataObMinor.get(i);
			
			if (dataObMinor.size() - i != 1 ){
				
				if(temp1 == ""){
					temp1 = hOb.get("NAMA_OB")+" " +hOb.get("KP_WARIS_2");
				}
				else{
					temp1 = temp1 + ", " + hOb.get("NAMA_OB")+" " +hOb.get("KP_WARIS_2");
				}
				
				
			}
			else{
				
				if(temp1 == ""){
					temp1 = hOb.get("NAMA_OB")+" " +hOb.get("KP_WARIS_2");
				}
				else{
					temp1 = temp1 + " dan " + hOb.get("NAMA_OB")+" " +hOb.get("KP_WARIS_2");
				}
				
			}
			
		}
		parameters.put("obMinor2" ,temp1);
		
		
		
		
		//open razman kumpul penjaga belum dewasa dlu
		dataGroupPenjagaBelumDewasa = new Vector();
		dataGroupPenjagaBelumDewasa = getGroupPenjaga(idfail, "2");
		String belum_dewasa = "";
		for (int bd = 0; bd < dataGroupPenjagaBelumDewasa.size();bd++){				
			Hashtable hP = (Hashtable)dataGroupPenjagaBelumDewasa.get(bd);			
			if (dataGroupPenjagaBelumDewasa.size() - bd != 1){				
				if(belum_dewasa == ""){					
					belum_dewasa = hP.get("NAMA_PENJAGA")+" " +hP.get("KP_PENJAGA") + " yang beralamat di " +hP.get("ALAMAT_PENUH_PENJAGA") + "MENJADI PENJAGA kepada "+getListMinor(hP.get("SET_ID_MINOR").toString(), "2");
				}
				else{					
					belum_dewasa = belum_dewasa + ", " + hP.get("NAMA_PENJAGA")+" " +hP.get("KP_PENJAGA") + " yang beralamat di " +hP.get("ALAMAT_PENUH_PENJAGA")+ " MENJADI PENJAGA kepada "+getListMinor(hP.get("SET_ID_MINOR").toString(), "2");
				}				
			}
			else{				
				if(belum_dewasa == ""){					
					belum_dewasa = hP.get("NAMA_PENJAGA")+" " +hP.get("KP_PENJAGA") + " yang beralamat di " +hP.get("ALAMAT_PENUH_PENJAGA")+ " MENJADI PENJAGA kepada "+getListMinor(hP.get("SET_ID_MINOR").toString(), "2");
				}
				else{					
					belum_dewasa = belum_dewasa + " dan " + hP.get("NAMA_PENJAGA")+" " +hP.get("KP_PENJAGA") + " yang beralamat di " +hP.get("ALAMAT_PENUH_PENJAGA")+ " MENJADI PENJAGA kepada "+getListMinor(hP.get("SET_ID_MINOR").toString(), "2");
				}
			}
			
		}		
		//close razman kumpul penjaga belum dewasa dlu
		
		//open razman kumpul penjaga tak akal dlu
				dataGroupPenjagaTakSempurnaAkal = new Vector();
				dataGroupPenjagaTakSempurnaAkal = getGroupPenjaga(idfail,"4");
				String tak_akal = "";
				for (int ta = 0; ta < dataGroupPenjagaTakSempurnaAkal.size();ta++){				
					Hashtable hP = (Hashtable)dataGroupPenjagaTakSempurnaAkal.get(ta);			
					if (dataGroupPenjagaTakSempurnaAkal.size() - ta != 1){				
						if(tak_akal == ""){					
							tak_akal = hP.get("NAMA_PENJAGA")+" " +hP.get("KP_PENJAGA") + " yang beralamat di " +hP.get("ALAMAT_PENUH_PENJAGA") + "MENJADI PENJAGA kepada "+getListMinor(hP.get("SET_ID_MINOR").toString(), "4");
						}
						else{					
							tak_akal = belum_dewasa + ", " + hP.get("NAMA_PENJAGA")+" " +hP.get("KP_PENJAGA") + " yang beralamat di " +hP.get("ALAMAT_PENUH_PENJAGA")+ " MENJADI PENJAGA kepada "+getListMinor(hP.get("SET_ID_MINOR").toString(), "4");
						}				
					}
					else{				
						if(tak_akal == ""){					
							tak_akal = hP.get("NAMA_PENJAGA")+" " +hP.get("KP_PENJAGA") + " yang beralamat di " +hP.get("ALAMAT_PENUH_PENJAGA")+ " MENJADI PENJAGA kepada "+getListMinor(hP.get("SET_ID_MINOR").toString(), "4");
						}
						else{					
							tak_akal = tak_akal + " dan " + hP.get("NAMA_PENJAGA")+" " +hP.get("KP_PENJAGA") + " yang beralamat di " +hP.get("ALAMAT_PENUH_PENJAGA")+ " MENJADI PENJAGA kepada "+getListMinor(hP.get("SET_ID_MINOR").toString(), "4");
						}
					}
					
				}		
		//close razman kumpul penjaga tak akal dlu
		
		
		
		
		
		
		
		//razman comment coding asal
		
		if(dataObMinorBlmDewasa.size()!= 0){
			
			Hashtable hObBlmDewasa = null;
			for (int i = 0; i < dataObMinorBlmDewasa.size();i++){
				hObBlmDewasa = (Hashtable)dataObMinorBlmDewasa.get(i);
				
				 
					if (dataObMinorBlmDewasa.size() - i != 1 ){
						
						if(temp2 == ""){
							temp2 = hObBlmDewasa.get("NAMA_OB")+" " +hObBlmDewasa.get("KP_WARIS_2");
						}
						else{
							temp2 = temp2 + ", " + hObBlmDewasa.get("NAMA_OB")+" " +hObBlmDewasa.get("KP_WARIS_2");
						}
						
						
					}
					else{
						
						if(temp2 == ""){
							temp2 = hObBlmDewasa.get("NAMA_OB")+" " +hObBlmDewasa.get("KP_WARIS_2");
						}
						else{
							temp2 = temp2 + " dan " + hObBlmDewasa.get("NAMA_OB")+" " +hObBlmDewasa.get("KP_WARIS_2");
						}
						
					}
					
				
				
				if (dataObMinorBlmDewasa.size() == 1){
					tempBlmDewasa = temp2 + " adalah seorang yang belum dewasa";
				}
				else{
					tempBlmDewasa = temp2 + " adalah belum dewasa";
				}
				
				
				/*
				dataPenjaga = new Vector();
				dataPenjaga = getPenjaga(hObBlmDewasa.get("ID_OB").toString());
					
				for (int j = 0; j < dataPenjaga.size();j++){
						
					Hashtable hP = (Hashtable)dataPenjaga.get(j);
					
					if (dataPenjaga.size() - j != 1){
						
						if(temp3 == ""){
							
							temp3 = hP.get("NAMA_PENJAGA")+" " +hP.get("KP_PENJAGA") + " yang beralamat di " +hP.get("ALAMAT_PENUH_PENJAGA");
						}
						else{
							
							temp3 = temp3 + ", " + hP.get("NAMA_PENJAGA")+" " +hP.get("KP_PENJAGA") + " yang beralamat di " +hP.get("ALAMAT_PENUH_PENJAGA");

							
						}
						
					}
					else{
						
						if(temp3 == ""){
							
							temp3 = hP.get("NAMA_PENJAGA")+" " +hP.get("KP_PENJAGA") + " yang beralamat di " +hP.get("ALAMAT_PENUH_PENJAGA");
						}
						else{
							
							temp3 = temp3 + " dan " + hP.get("NAMA_PENJAGA")+" " +hP.get("KP_PENJAGA") + " yang beralamat di " +hP.get("ALAMAT_PENUH_PENJAGA");

							
						}
					}
					
					
				}
			
				temp4 = temp3 + " MENJADI PENJAGA kepada " + hObBlmDewasa.get("NAMA_OB")+" " +hObBlmDewasa.get("KP_WARIS_2");
				
				
				if(tempPenjagaBlmDewasa == ""){
					tempPenjagaBlmDewasa = temp4;
				}
				else{
					tempPenjagaBlmDewasa = tempPenjagaBlmDewasa + " dan " + temp4;
				}
				
				temp3 = "";
				*/
			}
							
		}
		myLogger.debug("dataObMinorTakSempurnaAkal:"+dataObMinorTakSempurnaAkal);
		if(dataObMinorTakSempurnaAkal.size() != 0){
			myLogger.debug("1");
			Hashtable hObTakSempurnaAkal = null;
			for (int i = 0; i < dataObMinorTakSempurnaAkal.size();i++){
				hObTakSempurnaAkal = (Hashtable)dataObMinorTakSempurnaAkal.get(i);
				
				myLogger.debug("2:"+hObTakSempurnaAkal); 
				
					if (dataObMinorTakSempurnaAkal.size() - i != 1 ){
						
						if(temp5 == ""){
							temp5 = hObTakSempurnaAkal.get("NAMA_OB")+" " +hObTakSempurnaAkal.get("KP_WARIS_2");
						}
						else{
							temp5 = temp5 + ", " + hObTakSempurnaAkal.get("NAMA_OB")+" " +hObTakSempurnaAkal.get("KP_WARIS_2");
						}
						
						
					}
					else{
						
						if(temp5 == ""){
							temp5 = hObTakSempurnaAkal.get("NAMA_OB")+" " +hObTakSempurnaAkal.get("KP_WARIS_2");
						}
						else{
							temp5 = temp5 + " dan " + hObTakSempurnaAkal.get("NAMA_OB")+" " +hObTakSempurnaAkal.get("KP_WARIS_2");
						}
						
					}
					
					myLogger.debug("3:"+hObTakSempurnaAkal); 
					myLogger.debug("temp5"+temp5);
					
				
				if (dataObMinorTakSempurnaAkal.size() == 1){
					tempTakSempurnaAkal = temp5 + " adalah seorang yang tidak sempurna akal";
				}
				else{
					tempTakSempurnaAkal = temp5 + " adalah tidak sempurna akal";
				}
				
				myLogger.debug("tempTakSempurnaAkal:"+tempTakSempurnaAkal);
				
				/*
				dataPenjaga = new Vector();
				dataPenjaga = getPenjaga(hObTakSempurnaAkal.get("ID_OB").toString());
					
				for (int j = 0; j < dataPenjaga.size();j++){
						
					Hashtable hP = (Hashtable)dataPenjaga.get(j);
					
					if (dataPenjaga.size() - j != 1){
						
						if(temp6 == ""){
							
							temp6 = hP.get("NAMA_PENJAGA")+" " +hP.get("KP_PENJAGA") + " yang beralamat di " +hP.get("ALAMAT_PENUH_PENJAGA");
						}
						else{
							
							temp6 = temp6 + ", " + hP.get("NAMA_PENJAGA")+" " +hP.get("KP_PENJAGA") + " yang beralamat di " +hP.get("ALAMAT_PENUH_PENJAGA");

							
						}
						
					}
					else{
						
						if(temp6 == ""){
							
							temp6 = hP.get("NAMA_PENJAGA")+" " +hP.get("KP_PENJAGA") + " yang beralamat di " +hP.get("ALAMAT_PENUH_PENJAGA");
						}
						else{
							
							temp6 = temp6 + " dan " + hP.get("NAMA_PENJAGA")+" " +hP.get("KP_PENJAGA") + " yang beralamat di " +hP.get("ALAMAT_PENUH_PENJAGA");

							
						}
					}
					
					
				}
			
				temp7 = temp6 + " MENJADI PENJAGA kepada " + hObTakSempurnaAkal.get("NAMA_OB")+" " +hObTakSempurnaAkal.get("KP_WARIS_2");
				temp6 = "";
				
				if(tempPenjagaTakSempurnaAkal == ""){
					tempPenjagaTakSempurnaAkal = temp7;
				}
				else{
					tempPenjagaTakSempurnaAkal = tempPenjagaTakSempurnaAkal + " dan " + temp7;
				}
				*/
				
			}
			
			
				
		}
		
		
		
		/*
		//razman comment
		if (tempPenjagaTakSempurnaAkal != "" ){
			if(tempPenjagaBlmDewasa != "") {
				parameters.put("penjaga" ,tempPenjagaBlmDewasa + " dan " +tempPenjagaTakSempurnaAkal);
			}
			else{
				parameters.put("penjaga" ,tempPenjagaTakSempurnaAkal);
			}
			
		}
		else if(tempPenjagaBlmDewasa != "") {
			if (tempPenjagaTakSempurnaAkal != "" ){
				parameters.put("penjaga" ,tempPenjagaBlmDewasa + " dan " +tempPenjagaTakSempurnaAkal);
			}
			else{
				parameters.put("penjaga" ,tempPenjagaBlmDewasa);
			}
			
		}
		*/
	//	sdfsdfsdf
		if (tak_akal != "" ){
			if(belum_dewasa != "") {
				parameters.put("penjaga" ,belum_dewasa + " dan " +tak_akal);
			}
			else{
				parameters.put("penjaga" ,tak_akal);
			}
			
		}
		else if(belum_dewasa != "") {
			if (tak_akal != "" ){
				parameters.put("penjaga" ,belum_dewasa + " dan " +tak_akal);
			}
			else{
				parameters.put("penjaga" ,belum_dewasa);
			}			
		}
	//sdfsdfds
	myLogger.debug("belum_dewasa======"+belum_dewasa);
	myLogger.debug("tak_akal======"+tak_akal);
		myLogger.debug("");
		
		if(tempBlmDewasa != ""){
			if (tempTakSempurnaAkal != "") {
				parameters.put("obMinor" , tempBlmDewasa + " dan " + tempTakSempurnaAkal);
			} else {
				parameters.put("obMinor" , tempBlmDewasa );
			}
			
		}
		else if(tempTakSempurnaAkal != ""){
			if(tempBlmDewasa != ""){
				parameters.put("obMinor" , tempBlmDewasa + " dan " + tempTakSempurnaAkal);
			}
			else{
				parameters.put("obMinor" ,tempTakSempurnaAkal);
			}
			
		}
	
		}

	
}