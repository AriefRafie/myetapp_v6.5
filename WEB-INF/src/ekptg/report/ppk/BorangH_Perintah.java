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
import ekptg.report.EkptgReportServlet;

public class BorangH_Perintah  extends EkptgReportServlet{
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	Vector idPenjaga= null;
	Vector penjaga = null;
	Vector obMinor = null;
	Vector obBlmDewasaPenjaga1 = null;
	Vector obBlmDewasaPenjaga2 = null;
	Vector obBlmDewasaPenjaga3 = null;
	Vector obBlmDewasaPenjaga4 = null;
	Vector obTdkSempurnaPenjaga1 = null;
	Vector obTdkSempurnaPenjaga2 = null;
	Vector obTdkSempurnaPenjaga3 = null;
	Vector obTdkSempurnaPenjaga4 = null;
	
	Vector maklumatPenjaga = null;
	Vector obBlmDewasa = null;
	Vector obTdkSempurnaAkal = null;
	
	
	public BorangH_Perintah() {
		
		super.setReportName("BorangH_Perintah");
		super.setFolderName("ppk");
	}

	
	public Vector getOBMinor(String idPA)throws Exception{
		
		String sql = "";
		Db db = null;
		
		try{
			db = new Db();
			obMinor = new Vector();
			
			sql = " SELECT DISTINCT D.ID_OB,D.NAMA_OB, "+
					 "CASE "+ 
					 "WHEN D.STATUS_OB = 2 THEN 'belum dewasa' "+ 
					 "WHEN D.STATUS_OB = 4 THEN 'sempurna akal' "+ 
					 "END AS STATUS_OB, "+ 
					 "CASE "+ 
					 "WHEN LENGTH (DDD.NO_KP4) IS NULL THEN '' "+ 
					 "WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||'' "+
					 "WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4) "+ 
					 "WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')' "+
					 "ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')' "+ 
					 "END  AS KP_WARIS, "+ 
					 "CASE "+ 
					 "WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN '' "+ 
					 "WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||'' "+ 
					 "WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')' "+ 
					 "WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')' "+
					 "ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')' "+ 
					 "END  AS KP_WARIS_2 "+ 
					 "FROM "+  
					 "TBLPPKOB D, "+  
					 "TBLPPKPERINTAHHTAOBDTL I, "+
      
					 "(SELECT "+ 
					 "CASE "+ 
					 "WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA "+ 
					 "WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN "+
					 "WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA "+  
					 "ELSE TBLPPKOB.NO_KP_BARU "+
					 "END || '' || "+     
					 "CASE "+
					 "WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA "+ 
					 "END || '' || "+     
					 "CASE "+
					 "WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN "+      
					 "END AS NO_KP4, ID_OB "+  
					 "FROM TBLPPKOB ) DDD, "+ 
					 "(SELECT "+ 
					 "CASE "+
					 "WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA "+ 
					 "WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN "+
					 "WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA "+ 
					 "ELSE TBLPPKOB.NO_KP_BARU "+ 
					 "END || '' || "+     
					 "CASE "+
					 "WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA "+ 
					 "END || '' || "+     
					 "CASE "+
					 "WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN "+     
					 "END AS NO_KP42, ID_OB "+   
					 "FROM TBLPPKOB ) DDD2 "+   
					 "WHERE "+
					 "D.ID_OB = DDD.ID_OB "+
					 "AND D.ID_OB = DDD2.ID_OB "+ 
					 "AND I.ID_OB = D.ID_OB "+
					 "AND (I.ID_PA1 ='"+idPA+"' OR I.ID_PA2 ='"+idPA+"' OR I.ID_PA3 ='"+idPA+"' OR I.ID_PA4 = '"+idPA+"') "+
		       
					 "UNION "+ 
       
					 "SELECT DISTINCT D.ID_OB,D.NAMA_OB, "+
					 "CASE "+
					 "WHEN D.STATUS_OB = 2 THEN 'belum dewasa' "+
					 "WHEN D.STATUS_OB = 4 THEN 'sempurna akal' "+ 
					 "END AS STATUS_OB, "+ 
					 "CASE "+ 
					 "WHEN LENGTH (DDD.NO_KP4) IS NULL THEN '' "+
					 "WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||'' "+
					 "WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4) "+ 
					 "WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')' "+
					 "ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')' "+ 
					 "END  AS KP_WARIS, "+ 
					 "CASE "+ 
					 "WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN '' "+
					 "WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||'' "+
					 "WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')' "+ 
					 "WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')' "+
					 "ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')' "+ 
					 "END  AS KP_WARIS_2 "+ 
					 "FROM "+
					 "TBLPPKOB D, "+
					 "TBLPPKPERINTAHHAOBDTL I, "+
      
					 "(SELECT "+ 
					 "CASE "+
					 "WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA "+ 
					 "WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN "+
					 "WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA "+  
					 "ELSE TBLPPKOB.NO_KP_BARU "+ 
					 "END || '' || "+     
					 "CASE "+
					 "WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA "+ 
					 "END || '' || "+     
					 "CASE "+
					 "WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN "+      
					 "END AS NO_KP4, ID_OB "+  
					 "FROM TBLPPKOB ) DDD, "+ 
					 "(SELECT "+ 
					 "CASE "+
					 "WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA "+ 
					 "WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN "+
					 "WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA "+ 
					 "ELSE TBLPPKOB.NO_KP_BARU "+ 
					 "END || '' || "+     
					 "CASE "+ 
					 "WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA "+ 
					 "END || '' || "+     
					 "CASE "+
					 "WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN "+     
					 "END AS NO_KP42, ID_OB "+   
					 "FROM TBLPPKOB ) DDD2 "+   
					 "WHERE "+
					 "D.ID_OB = DDD.ID_OB "+
					 "AND D.ID_OB = DDD2.ID_OB "+ 
					 "AND I.ID_OB = D.ID_OB "+
					 "AND (I.ID_PA1 ='"+idPA+"' OR I.ID_PA2 ='"+idPA+"' OR I.ID_PA3 ='"+idPA+"' OR I.ID_PA4 = '"+idPA+"')";
					
			Statement stmt = db.getStatement();
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
	
	
	public Vector getIdPenjaga(String idfail)throws Exception{
		
		String sql = "";
		Db db = null;
		
		try{
			db = new Db();
			idPenjaga = new Vector();
			sql = "SELECT DISTINCT J.ID_PA1 "+
            
				   "FROM "+ 

				   "TBLPFDFAIL A, "+
				   "TBLPPKPERMOHONAN B, "+ 
				   "TBLPPKPERMOHONANSIMATI C, "+ 
				   "TBLPPKOB D, "+
				   "TBLPPKKEPUTUSANPERMOHONAN E, "+
				   "TBLPPKPERBICARAAN F, "+
				   "TBLPPKPERINTAH G, "+
				   "TBLPPKPERINTAHHTAOBMST I, "+
				   "TBLPPKPERINTAHHTAOBDTL J "+
            
         
				   "WHERE A.ID_FAIL = B.ID_FAIL "+ 
				   "AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "+ 
				   "AND C.ID_SIMATI = D.ID_SIMATI "+
				   "AND B.ID_PERMOHONAN = E.ID_PERMOHONAN "+
				   "AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN "+
				   "AND F.ID_PERBICARAAN = G.ID_PERBICARAAN "+
				   "AND G.ID_PERINTAH = I.ID_PERINTAH "+
				   "AND I.ID_PERINTAHHTAOBMST = J.ID_PERINTAHHTAOBMST " +
				   "AND J.ID_PA1 IS NOT NULL "+
				   "AND J.ID_OB = D.ID_OB "+
				   "AND D.STATUS_OB IN (2,4) "+ 
				   "AND A.ID_FAIL = '"+idfail+"'"+
           
           "UNION "+ 
           
           "SELECT DISTINCT J.ID_PA2 "+
            
				   "FROM "+

				   "TBLPFDFAIL A, "+ 
				   "TBLPPKPERMOHONAN B, "+ 
				   "TBLPPKPERMOHONANSIMATI C, "+ 
				   "TBLPPKOB D, "+
				   "TBLPPKKEPUTUSANPERMOHONAN E, "+
				   "TBLPPKPERBICARAAN F, "+
				   "TBLPPKPERINTAH G, "+
				   "TBLPPKPERINTAHHTAOBMST I, "+
				   "TBLPPKPERINTAHHTAOBDTL J "+
            
         
				   "WHERE A.ID_FAIL = B.ID_FAIL "+
				   "AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "+ 
				   "AND C.ID_SIMATI = D.ID_SIMATI "+
				   "AND B.ID_PERMOHONAN = E.ID_PERMOHONAN "+
				   "AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN "+
				   "AND F.ID_PERBICARAAN = G.ID_PERBICARAAN "+
				   "AND G.ID_PERINTAH = I.ID_PERINTAH "+
				   "AND I.ID_PERINTAHHTAOBMST = J.ID_PERINTAHHTAOBMST " +
				   "AND J.ID_PA2 IS NOT NULL "+
				   "AND J.ID_OB = D.ID_OB "+
				   "AND D.STATUS_OB IN (2,4) "+ 
				   "AND A.ID_FAIL ='"+idfail+"'"+
				   
           "UNION "+
           
           "SELECT DISTINCT J.ID_PA3 "+
            
		           "FROM "+
		
				   "TBLPFDFAIL A, "+ 
				   "TBLPPKPERMOHONAN B, "+ 
				   "TBLPPKPERMOHONANSIMATI C, "+ 
				   "TBLPPKOB D, "+
				   "TBLPPKKEPUTUSANPERMOHONAN E, "+
				   "TBLPPKPERBICARAAN F, "+
				   "TBLPPKPERINTAH G, "+
				   "TBLPPKPERINTAHHTAOBMST I, "+
				   "TBLPPKPERINTAHHTAOBDTL J "+
		            
         
				   "WHERE A.ID_FAIL = B.ID_FAIL "+
				   "AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "+ 
				   "AND C.ID_SIMATI = D.ID_SIMATI "+
				   "AND B.ID_PERMOHONAN = E.ID_PERMOHONAN "+
				   "AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN "+
				   "AND F.ID_PERBICARAAN = G.ID_PERBICARAAN "+
				   "AND G.ID_PERINTAH = I.ID_PERINTAH "+
				   "AND I.ID_PERINTAHHTAOBMST = J.ID_PERINTAHHTAOBMST " +
				   "AND J.ID_PA3 IS NOT NULL "+
				   "AND J.ID_OB = D.ID_OB "+
				   "AND D.STATUS_OB IN (2,4) "+ 
				   "AND A.ID_FAIL ='"+idfail+"'"+
           
           "UNION "+ 
           
           "SELECT DISTINCT J.ID_PA4 "+ 
            
		           "FROM "+
		
				   "TBLPFDFAIL A, "+ 
				   "TBLPPKPERMOHONAN B, "+ 
				   "TBLPPKPERMOHONANSIMATI C, "+ 
				   "TBLPPKOB D, "+
				   "TBLPPKKEPUTUSANPERMOHONAN E, "+
				   "TBLPPKPERBICARAAN F, "+
				   "TBLPPKPERINTAH G, "+
				   "TBLPPKPERINTAHHTAOBMST I, "+
				   "TBLPPKPERINTAHHTAOBDTL J "+
            
         
				   "WHERE A.ID_FAIL = B.ID_FAIL "+
				   "AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "+ 
				   "AND C.ID_SIMATI = D.ID_SIMATI "+
				   "AND B.ID_PERMOHONAN = E.ID_PERMOHONAN "+
				   "AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN "+
				   "AND F.ID_PERBICARAAN = G.ID_PERBICARAAN "+
				   "AND G.ID_PERINTAH = I.ID_PERINTAH "+
				   "AND I.ID_PERINTAHHTAOBMST = J.ID_PERINTAHHTAOBMST " +
				   "AND J.ID_PA4 IS NOT NULL "+
				   "AND J.ID_OB = D.ID_OB "+
				   "AND D.STATUS_OB IN (2,4) "+ 
				   "AND A.ID_FAIL ='"+idfail+"'"+
           
				   "UNION "+ 
				   
			"SELECT DISTINCT J.ID_PA1 "+
		            
				   "FROM "+ 

				   "TBLPFDFAIL A, "+ 
				   "TBLPPKPERMOHONAN B, "+
				   "TBLPPKPERMOHONANSIMATI C, "+ 
				   "TBLPPKOB D, "+
				   "TBLPPKKEPUTUSANPERMOHONAN E, "+
				   "TBLPPKPERBICARAAN F, "+
				   "TBLPPKPERINTAH G, "+
				   "TBLPPKPERINTAHHAOBMST I, "+
				   "TBLPPKPERINTAHHAOBDTL J "+
            
         
				   "WHERE A.ID_FAIL = B.ID_FAIL "+
				   "AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "+ 
				   "AND C.ID_SIMATI = D.ID_SIMATI "+
				   "AND B.ID_PERMOHONAN = E.ID_PERMOHONAN "+
				   "AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN "+
				   "AND F.ID_PERBICARAAN = G.ID_PERBICARAAN "+
				   "AND G.ID_PERINTAH = I.ID_PERINTAH "+
				   "AND I.ID_PERINTAHHAOBMST = J.ID_PERINTAHHAOBMST " +
				   "AND J.ID_PA1 IS NOT NULL "+
				   "AND J.ID_OB = D.ID_OB "+
				   "AND D.STATUS_OB IN (2,4) "+ 
				   "AND A.ID_FAIL = '"+idfail+"'"+
           
				   "UNION "+ 
           
           "SELECT DISTINCT J.ID_PA2 "+
		            
		           "FROM "+ 
		
				   "TBLPFDFAIL A, "+ 
				   "TBLPPKPERMOHONAN B, "+
				   "TBLPPKPERMOHONANSIMATI C, "+ 
				   "TBLPPKOB D, "+
				   "TBLPPKKEPUTUSANPERMOHONAN E, "+
				   "TBLPPKPERBICARAAN F, "+
				   "TBLPPKPERINTAH G, "+
				   "TBLPPKPERINTAHHAOBMST I, "+
				   "TBLPPKPERINTAHHAOBDTL J "+
		    
		 
				   "WHERE A.ID_FAIL = B.ID_FAIL "+
				   "AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "+ 
				   "AND C.ID_SIMATI = D.ID_SIMATI "+
				   "AND B.ID_PERMOHONAN = E.ID_PERMOHONAN "+
				   "AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN "+
				   "AND F.ID_PERBICARAAN = G.ID_PERBICARAAN "+
				   "AND G.ID_PERINTAH = I.ID_PERINTAH "+
				   "AND I.ID_PERINTAHHAOBMST = J.ID_PERINTAHHAOBMST " +
				   "AND J.ID_PA2 IS NOT NULL "+
				   "AND J.ID_OB = D.ID_OB "+
				   "AND D.STATUS_OB IN (2,4) "+ 
				   "AND A.ID_FAIL = '"+idfail+"'"+
           
				   "UNION "+
           
           "SELECT DISTINCT J.ID_PA3 "+
				            
		           "FROM "+ 
		
				   "TBLPFDFAIL A, "+ 
				   "TBLPPKPERMOHONAN B, "+
				   "TBLPPKPERMOHONANSIMATI C, "+ 
				   "TBLPPKOB D, "+
				   "TBLPPKKEPUTUSANPERMOHONAN E, "+
				   "TBLPPKPERBICARAAN F, "+
				   "TBLPPKPERINTAH G, "+
				   "TBLPPKPERINTAHHAOBMST I, "+
				   "TBLPPKPERINTAHHAOBDTL J "+
		    
		 
				   "WHERE A.ID_FAIL = B.ID_FAIL "+
				   "AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "+ 
				   "AND C.ID_SIMATI = D.ID_SIMATI "+
				   "AND B.ID_PERMOHONAN = E.ID_PERMOHONAN "+
				   "AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN "+
				   "AND F.ID_PERBICARAAN = G.ID_PERBICARAAN "+
				   "AND G.ID_PERINTAH = I.ID_PERINTAH "+
				   "AND I.ID_PERINTAHHAOBMST = J.ID_PERINTAHHAOBMST " +
				   "AND J.ID_PA3 IS NOT NULL "+
				   "AND J.ID_OB = D.ID_OB "+
				   "AND D.STATUS_OB IN (2,4) "+ 
				   "AND A.ID_FAIL = '"+idfail+"'"+
				   
				   "UNION "+
           
           "SELECT DISTINCT J.ID_PA4 "+ 
		            
		           "FROM "+ 
		
				   "TBLPFDFAIL A, "+ 
				   "TBLPPKPERMOHONAN B, "+
				   "TBLPPKPERMOHONANSIMATI C, "+ 
				   "TBLPPKOB D, "+
				   "TBLPPKKEPUTUSANPERMOHONAN E, "+
				   "TBLPPKPERBICARAAN F, "+
				   "TBLPPKPERINTAH G, "+
				   "TBLPPKPERINTAHHAOBMST I, "+
				   "TBLPPKPERINTAHHAOBDTL J "+
		    
		 
				   "WHERE A.ID_FAIL = B.ID_FAIL "+
				   "AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "+ 
				   "AND C.ID_SIMATI = D.ID_SIMATI "+
				   "AND B.ID_PERMOHONAN = E.ID_PERMOHONAN "+
				   "AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN "+
				   "AND F.ID_PERBICARAAN = G.ID_PERBICARAAN "+
				   "AND G.ID_PERINTAH = I.ID_PERINTAH "+
				   "AND I.ID_PERINTAHHAOBMST = J.ID_PERINTAHHAOBMST " +
				   "AND J.ID_PA4 IS NOT NULL "+
				   "AND J.ID_OB = D.ID_OB "+
				   "AND D.STATUS_OB IN (2,4) "+ 
				   "AND A.ID_FAIL = '"+idfail+"'";
			
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_PA1",rs.getString("ID_PA1")==null?"":rs.getString("ID_PA1"));
				idPenjaga.addElement(h);
				
				
			}
			
			return idPenjaga;
			
		}finally {
			if (db != null)
				db.close();
		}		
	}
	
	public Vector getMaklumatPenjaga(String idPenjaga)throws Exception{
		
		String sql = "";
		Db db = null;
		
		try{
			db = new Db();
			penjaga = new Vector();
			
			sql = "SELECT DISTINCT"+  
             	  " UPPER(D.NAMA_OB) AS NAMA_PENJAGA,"+ 
             	  " CASE"+ 
             	  	" WHEN LENGTH (AAA.NO_KP1) IS NULL THEN ''"+ 
             	  	" WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"+ 
             	  	" WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"+ 
             	  	" ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"+ 
             	  " END  AS KP_PENJAGA,"+ 
             	  " CASE"+ 
             	  	" WHEN D.ALAMAT_1 IS NULL THEN 'TIADA'"+ 
             	  	" WHEN D.ALAMAT_2 IS NULL AND D.ALAMAT_3 IS NULL THEN  REPLACE(REPLACE(UPPER(trim(D.ALAMAT_1)),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(D.POSKOD),','),'&','&#38;') || ' ' || REPLACE(REPLACE(UPPER(trim(D.BANDAR)),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(trim(E.NAMA_NEGERI)),','),'&','&#38;')"+ 
             	  	" WHEN D.ALAMAT_2 IS NULL THEN REPLACE(REPLACE(UPPER(trim(D.ALAMAT_1)),','),'&','&#38;') ||', ' || REPLACE(REPLACE(UPPER(trim(D.ALAMAT_3)),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(trim(D.POSKOD)),','),'&','&#38;') || ' ' ||REPLACE(REPLACE(UPPER(trim(D.BANDAR)),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(trim(E.NAMA_NEGERI)),','),'&','&#38;')"+ 
             	  	" WHEN D.ALAMAT_3 IS NULL THEN REPLACE(REPLACE(UPPER(trim(D.ALAMAT_1)),','),'&','&#38;') ||', ' || REPLACE(REPLACE(UPPER(trim(D.ALAMAT_2)),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(trim(D.POSKOD)),','),'&','&#38;') || ' ' ||REPLACE(REPLACE(UPPER(trim(D.BANDAR)),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(trim(E.NAMA_NEGERI)),','),'&','&#38;')"+ 
             	  	" WHEN D.ALAMAT_3 IS NOT NULL THEN REPLACE(REPLACE(UPPER(trim(D.ALAMAT_1)),','),'&','&#38;')||', ' || REPLACE(REPLACE(UPPER(trim(D.ALAMAT_2)),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(trim(D.ALAMAT_3)),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(trim(D.POSKOD)),','),'&','&#38;') || ' ' || REPLACE(REPLACE(UPPER(trim(D.BANDAR)),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(trim(E.NAMA_NEGERI)),','),'&','&#38;')"+ 
             	  	" ELSE 'TIADA'"+   
             	  " END AS ALAMAT_PENUH_PENJAGA"+ 
             	  " FROM"+ 
             	  " TBLPFDFAIL A,"+ 
             	  " TBLPPKPERMOHONAN B,"+ 
             	  " TBLPPKPERMOHONANSIMATI C,"+ 
             	  " TBLPPKOB D,"+ 
             	  " TBLRUJNEGERI E,"+
             
             	  " (SELECT"+ 
             			  " CASE"+ 
             			  	" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
             			  	" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
             			  	" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
             			  " ELSE TBLPPKOB.NO_KP_BARU"+ 
             			  " END || '' ||"+    
             			  " CASE"+ 
             			  	" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
             			  " END || '' ||"+    
             			  " CASE"+ 
             			  	" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
             			  " END AS NO_KP1, ID_OB"+    
             			  " FROM TBLPPKOB ) AAA"+ 
             	  " WHERE A.ID_FAIL = B.ID_FAIL"+ 
             	  " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"+ 
            	  " AND C.ID_SIMATI = D.ID_SIMATI"+ 
             	  " AND D.ID_OB = AAA.ID_OB"+ 
             	  " AND E.ID_NEGERI = D.ID_NEGERI"+ 
             	  " AND D.ID_OB ='"+idPenjaga+"'";
			
			Statement stmt = db.getStatement();
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
	
	public Vector getOBBelumDewasaPenjaga1(String idPenjaga1)throws Exception{
		
		String sql = "";
		Db db = null;
		
		try{
			db = new Db();
			obBlmDewasaPenjaga1 = new Vector();
			
			sql = "SELECT DISTINCT D.ID_OB,D.NAMA_OB,"+
            	  " CASE"+ 
            	  		" WHEN D.STATUS_OB = 2 THEN 'belum dewasa'"+ 
            	  		" WHEN D.STATUS_OB = 4 THEN 'sempurna akal'"+ 
            	  " END AS STATUS_OB,"+ 
            	  " CASE"+ 
            	  		" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN ''"+ 
            	  		" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||''"+ 
            	  		" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)"+ 
            	  		" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
            	  		" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')'"+ 
            	  " END  AS KP_WARIS,"+ 
            	  " CASE"+ 
            	  		" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN ''"+ 
            	  		" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||''"+ 
            	  		" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')'"+ 
            	  		" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
            	  		" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')'"+ 
            	  " END  AS KP_WARIS_2"+ 
            	  " FROM TBLPFDFAIL A,"+ 
            	  " TBLPPKPERMOHONAN B,"+ 
            	  " TBLPPKPERMOHONANSIMATI C,"+ 
            	  " TBLPPKOB D,"+
            	  " TBLPPKKEPUTUSANPERMOHONAN E,"+
            	  " TBLPPKPERBICARAAN F,"+
            	  " TBLPPKPERINTAH G,"+
            	  " TBLPPKPERINTAHHTAOBMST I,"+
            	  " TBLPPKPERINTAHHTAOBDTL J,"+ 
  
            	  "(SELECT"+ 
            	  	" CASE"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+  
            	  		" ELSE TBLPPKOB.NO_KP_BARU"+ 
            	  	" END || '' ||"+     
            	  	" CASE"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
            	  	" END || '' ||"+     
            	  	" CASE"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+      
            	  	" END AS NO_KP4, ID_OB"+     
            	  	" FROM TBLPPKOB ) DDD,"+ 
            	  	" (SELECT"+ 
            	  		" CASE"+ 
            	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
            	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            	  			" ELSE TBLPPKOB.NO_KP_BARU"+ 
            	  		" END || '' ||"+     
            	  		" CASE"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
            	  		" END || '' ||"+     
            	  		" CASE"+ 
            	  			" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
            	  		" END AS NO_KP42, ID_OB"+     
            	  		" FROM TBLPPKOB ) DDD2"+   
            	  		" WHERE A.ID_FAIL = B.ID_FAIL"+ 
            	  		" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"+ 
            	  		" AND C.ID_SIMATI = D.ID_SIMATI"+ 
            	  		" AND B.ID_PERMOHONAN = E.ID_PERMOHONAN"+
            	  		" AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"+
            	  		" AND F.ID_PERBICARAAN = G.ID_PERBICARAAN"+
            	  		" AND G.ID_PERINTAH = I.ID_PERINTAH"+
            	  		" AND I.ID_PERINTAHHTAOBMST = J.ID_PERINTAHHTAOBMST"+
            	  		" AND J.ID_OB = D.ID_OB"+
            	  		" AND D.ID_OB = DDD.ID_OB"+ 
            	  		" AND D.ID_OB = DDD2.ID_OB"+
            	  		" AND D.STATUS_OB IN (2)"+
            			" AND J.ID_PA1 = '"+idPenjaga1+"'"+
            
            			" UNION"+

            			" SELECT DISTINCT D.ID_OB,D.NAMA_OB,"+
            				" CASE"+ 
            					" WHEN D.STATUS_OB = 2 THEN 'belum dewasa'"+ 
            					" WHEN D.STATUS_OB = 4 THEN 'sempurna akal'"+
            				" END AS STATUS_OB,"+ 
            				" CASE"+ 
            					" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN ''"+ 
            					" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||''"+ 
            					" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)"+ 
            					" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
            					" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')'"+ 
            				" END  AS KP_WARIS,"+ 
            				" CASE"+ 
            					" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN ''"+ 
            					" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||''"+ 
            					" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')'"+ 
            					" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
            					" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')'"+ 
            				" END  AS KP_WARIS_2"+ 
            				" FROM TBLPFDFAIL A, "+
            				" TBLPPKPERMOHONAN B,"+
            				" TBLPPKPERMOHONANSIMATI C,"+ 
            				" TBLPPKOB D,"+
            				" TBLPPKKEPUTUSANPERMOHONAN E,"+
            				" TBLPPKPERBICARAAN F,"+
            				" TBLPPKPERINTAH G,"+
            				" TBLPPKPERINTAHHAOBMST K,"+
            				" TBLPPKPERINTAHHAOBDTL L,"+    
            
            				" (SELECT"+ 
            					" CASE"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            						" ELSE TBLPPKOB.NO_KP_BARU"+ 
            					" END || '' ||"+     
            					" CASE"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
            					" END || '' ||"+     
            					" CASE"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+      
            					" END AS NO_KP4, ID_OB"+     
            					" FROM TBLPPKOB ) DDD,"+ 
            					" (SELECT"+ 
            						" CASE"+ 
            							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
            							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            							" ELSE TBLPPKOB.NO_KP_BARU"+ 
            						" END || '' ||"+     
            						" CASE"+ 
            							" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
            						" END || '' ||"+     
            						" CASE"+
            							" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
            						" END AS NO_KP42, ID_OB"+     
            						" FROM TBLPPKOB ) DDD2"+   
            						" WHERE A.ID_FAIL = B.ID_FAIL"+ 
            						" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"+ 
            						" AND C.ID_SIMATI = D.ID_SIMATI"+ 
            						" AND B.ID_PERMOHONAN = E.ID_PERMOHONAN"+
            						" AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"+
            						" AND F.ID_PERBICARAAN = G.ID_PERBICARAAN"+
            						" AND G.ID_PERINTAH = K.ID_PERINTAH"+
            						" AND K.ID_PERINTAHHAOBMST = L.ID_PERINTAHHAOBMST"+
            						" AND L.ID_OB = D.ID_OB"+
            						" AND D.ID_OB = DDD.ID_OB"+ 
            						" AND D.ID_OB = DDD2.ID_OB"+ 
            						" AND D.STATUS_OB IN (2)"+ 
            						" AND L.ID_PA1 = '"+idPenjaga1+"'";
			
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_OB",rs.getString("ID_OB")==null?"":rs.getString("ID_OB"));
				h.put("NAMA_OB",rs.getString("NAMA_OB")==null?"":rs.getString("NAMA_OB"));
				h.put("STATUS_OB",rs.getString("STATUS_OB")==null?"":rs.getString("STATUS_OB"));
				h.put("KP_WARIS",rs.getString("KP_WARIS")==null?"":rs.getString("KP_WARIS"));
				h.put("KP_WARIS_2",rs.getString("KP_WARIS_2")==null?"":rs.getString("KP_WARIS_2"));
				obBlmDewasaPenjaga1.addElement(h);
				
				
				
			}
			
			return obBlmDewasaPenjaga1;
			
			
		}finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector getOBBelumDewasaPenjaga(String idPenjaga)throws Exception{
		
		String sql = "";
		Db db = null;
		
		try{
			db = new Db();
			obBlmDewasaPenjaga2 = new Vector();
			
			sql = "SELECT DISTINCT D.ID_OB,D.NAMA_OB,"+
            	  " CASE"+ 
            	  		" WHEN D.STATUS_OB = 2 THEN 'belum dewasa'"+ 
            	  		" WHEN D.STATUS_OB = 4 THEN 'sempurna akal'"+ 
            	  " END AS STATUS_OB,"+ 
            	  " CASE"+ 
            	  		" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN ''"+ 
            	  		" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||''"+ 
            	  		" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)"+ 
            	  		" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
            	  		" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')'"+ 
            	  " END  AS KP_WARIS,"+ 
            	  " CASE"+ 
            	  		" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN ''"+ 
            	  		" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||''"+ 
            	  		" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')'"+ 
            	  		" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
            	  		" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')'"+ 
            	  " END  AS KP_WARIS_2"+ 
            	  " FROM TBLPFDFAIL A,"+ 
            	  " TBLPPKPERMOHONAN B,"+ 
            	  " TBLPPKPERMOHONANSIMATI C,"+ 
            	  " TBLPPKOB D,"+
            	  " TBLPPKKEPUTUSANPERMOHONAN E,"+
            	  " TBLPPKPERBICARAAN F,"+
            	  " TBLPPKPERINTAH G,"+
            	  " TBLPPKPERINTAHHTAOBMST I,"+
            	  " TBLPPKPERINTAHHTAOBDTL J,"+ 
  
            	  "(SELECT"+ 
            	  	" CASE"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+  
            	  		" ELSE TBLPPKOB.NO_KP_BARU"+ 
            	  	" END || '' ||"+     
            	  	" CASE"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
            	  	" END || '' ||"+     
            	  	" CASE"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+      
            	  	" END AS NO_KP4, ID_OB"+     
            	  	" FROM TBLPPKOB ) DDD,"+ 
            	  	" (SELECT"+ 
            	  		" CASE"+ 
            	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
            	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            	  			" ELSE TBLPPKOB.NO_KP_BARU"+ 
            	  		" END || '' ||"+     
            	  		" CASE"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
            	  		" END || '' ||"+     
            	  		" CASE"+ 
            	  			" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
            	  		" END AS NO_KP42, ID_OB"+     
            	  		" FROM TBLPPKOB ) DDD2"+   
            	  		" WHERE A.ID_FAIL = B.ID_FAIL"+ 
            	  		" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"+ 
            	  		" AND C.ID_SIMATI = D.ID_SIMATI"+ 
            	  		" AND B.ID_PERMOHONAN = E.ID_PERMOHONAN"+
            	  		" AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"+
            	  		" AND F.ID_PERBICARAAN = G.ID_PERBICARAAN"+
            	  		" AND G.ID_PERINTAH = I.ID_PERINTAH"+
            	  		" AND I.ID_PERINTAHHTAOBMST = J.ID_PERINTAHHTAOBMST"+
            	  		" AND J.ID_OB = D.ID_OB"+
            	  		" AND D.ID_OB = DDD.ID_OB"+ 
            	  		" AND D.ID_OB = DDD2.ID_OB"+
            	  		" AND D.STATUS_OB IN (2)"+
            			" AND (J.ID_PA1 = '"+idPenjaga+"'"+
            			" OR J.ID_PA2 = '"+idPenjaga+"'"+
            			" OR J.ID_PA3 = '"+idPenjaga+"'"+
            			" OR J.ID_PA4 = '"+idPenjaga+"'"+")"+
            
            			" UNION"+

            			" SELECT DISTINCT D.ID_OB,D.NAMA_OB,"+
            				" CASE"+ 
            					" WHEN D.STATUS_OB = 2 THEN 'belum dewasa'"+ 
            					" WHEN D.STATUS_OB = 4 THEN 'sempurna akal'"+
            				" END AS STATUS_OB,"+ 
            				" CASE"+ 
            					" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN ''"+ 
            					" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||''"+ 
            					" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)"+ 
            					" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
            					" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')'"+ 
            				" END  AS KP_WARIS,"+ 
            				" CASE"+ 
            					" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN ''"+ 
            					" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||''"+ 
            					" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')'"+ 
            					" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
            					" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')'"+ 
            				" END  AS KP_WARIS_2"+ 
            				" FROM TBLPFDFAIL A, "+
            				" TBLPPKPERMOHONAN B,"+
            				" TBLPPKPERMOHONANSIMATI C,"+ 
            				" TBLPPKOB D,"+
            				" TBLPPKKEPUTUSANPERMOHONAN E,"+
            				" TBLPPKPERBICARAAN F,"+
            				" TBLPPKPERINTAH G,"+
            				" TBLPPKPERINTAHHAOBMST K,"+
            				" TBLPPKPERINTAHHAOBDTL L,"+    
            
            				" (SELECT"+ 
            					" CASE"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            						" ELSE TBLPPKOB.NO_KP_BARU"+ 
            					" END || '' ||"+     
            					" CASE"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
            					" END || '' ||"+     
            					" CASE"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+      
            					" END AS NO_KP4, ID_OB"+     
            					" FROM TBLPPKOB ) DDD,"+ 
            					" (SELECT"+ 
            						" CASE"+ 
            							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
            							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            							" ELSE TBLPPKOB.NO_KP_BARU"+ 
            						" END || '' ||"+     
            						" CASE"+ 
            							" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
            						" END || '' ||"+     
            						" CASE"+
            							" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
            						" END AS NO_KP42, ID_OB"+     
            						" FROM TBLPPKOB ) DDD2"+   
            						" WHERE A.ID_FAIL = B.ID_FAIL"+ 
            						" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"+ 
            						" AND C.ID_SIMATI = D.ID_SIMATI"+ 
            						" AND B.ID_PERMOHONAN = E.ID_PERMOHONAN"+
            						" AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"+
            						" AND F.ID_PERBICARAAN = G.ID_PERBICARAAN"+
            						" AND G.ID_PERINTAH = K.ID_PERINTAH"+
            						" AND K.ID_PERINTAHHAOBMST = L.ID_PERINTAHHAOBMST"+
            						" AND L.ID_OB = D.ID_OB"+
            						" AND D.ID_OB = DDD.ID_OB"+ 
            						" AND D.ID_OB = DDD2.ID_OB"+ 
            						" AND D.STATUS_OB IN (2)"+ 
            						" AND (L.ID_PA1 = '"+idPenjaga+"'"+
            						" OR L.ID_PA2 = '"+idPenjaga+"'"+
            						" OR L.ID_PA3 = '"+idPenjaga+"'"+
            						" OR L.ID_PA4 = '"+idPenjaga+"'"+")";
			
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_OB",rs.getString("ID_OB")==null?"":rs.getString("ID_OB"));
				h.put("NAMA_OB",rs.getString("NAMA_OB")==null?"":rs.getString("NAMA_OB"));
				h.put("STATUS_OB",rs.getString("STATUS_OB")==null?"":rs.getString("STATUS_OB"));
				h.put("KP_WARIS",rs.getString("KP_WARIS")==null?"":rs.getString("KP_WARIS"));
				h.put("KP_WARIS_2",rs.getString("KP_WARIS_2")==null?"":rs.getString("KP_WARIS_2"));
				obBlmDewasaPenjaga2.addElement(h);
				
				
				
			}
			
			return obBlmDewasaPenjaga2;
			
			
		}finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getOBBelumDewasaPenjaga3(String idPenjaga1,String idPenjaga2, String idPenjaga3)throws Exception{
	
	String sql = "";
	Db db = null;
	
	try{
		db = new Db();
		obBlmDewasaPenjaga3 = new Vector();
		
		sql = "SELECT DISTINCT D.ID_OB,D.NAMA_OB,"+
        	  " CASE"+ 
        	  		" WHEN D.STATUS_OB = 2 THEN 'belum dewasa'"+ 
        	  		" WHEN D.STATUS_OB = 4 THEN 'sempurna akal'"+ 
        	  " END AS STATUS_OB,"+ 
        	  " CASE"+ 
        	  		" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN ''"+ 
        	  		" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||''"+ 
        	  		" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)"+ 
        	  		" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
        	  		" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')'"+ 
        	  " END  AS KP_WARIS,"+ 
        	  " CASE"+ 
        	  		" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN ''"+ 
        	  		" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||''"+ 
        	  		" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')'"+ 
        	  		" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
        	  		" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')'"+ 
        	  " END  AS KP_WARIS_2"+ 
        	  " FROM TBLPFDFAIL A,"+ 
        	  " TBLPPKPERMOHONAN B,"+ 
        	  " TBLPPKPERMOHONANSIMATI C,"+ 
        	  " TBLPPKOB D,"+
        	  " TBLPPKKEPUTUSANPERMOHONAN E,"+
        	  " TBLPPKPERBICARAAN F,"+
        	  " TBLPPKPERINTAH G,"+
        	  " TBLPPKPERINTAHHTAOBMST I,"+
        	  " TBLPPKPERINTAHHTAOBDTL J,"+ 

        	  "(SELECT"+ 
        	  	" CASE"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+  
        	  		" ELSE TBLPPKOB.NO_KP_BARU"+ 
        	  	" END || '' ||"+     
        	  	" CASE"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
        	  	" END || '' ||"+     
        	  	" CASE"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+      
        	  	" END AS NO_KP4, ID_OB"+     
        	  	" FROM TBLPPKOB ) DDD,"+ 
        	  	" (SELECT"+ 
        	  		" CASE"+ 
        	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
        	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        	  			" ELSE TBLPPKOB.NO_KP_BARU"+ 
        	  		" END || '' ||"+     
        	  		" CASE"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
        	  		" END || '' ||"+     
        	  		" CASE"+ 
        	  			" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
        	  		" END AS NO_KP42, ID_OB"+     
        	  		" FROM TBLPPKOB ) DDD2"+   
        	  		" WHERE A.ID_FAIL = B.ID_FAIL"+ 
        	  		" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"+ 
        	  		" AND C.ID_SIMATI = D.ID_SIMATI"+ 
        	  		" AND B.ID_PERMOHONAN = E.ID_PERMOHONAN"+
        	  		" AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"+
        	  		" AND F.ID_PERBICARAAN = G.ID_PERBICARAAN"+
        	  		" AND G.ID_PERINTAH = I.ID_PERINTAH"+
        	  		" AND I.ID_PERINTAHHTAOBMST = J.ID_PERINTAHHTAOBMST"+
        	  		" AND J.ID_OB = D.ID_OB"+
        	  		" AND D.ID_OB = DDD.ID_OB"+ 
        	  		" AND D.ID_OB = DDD2.ID_OB"+
        	  		" AND D.STATUS_OB IN (2)"+
        			" AND J.ID_PA1 = '"+idPenjaga1+"'"+
        			" AND J.ID_PA2 = '"+idPenjaga2+"'"+
        			" AND J.ID_PA3 = '"+idPenjaga3+"'"+
        
        			" UNION"+

        			" SELECT DISTINCT D.ID_OB,D.NAMA_OB,"+
        				" CASE"+ 
        					" WHEN D.STATUS_OB = 2 THEN 'belum dewasa'"+ 
        					" WHEN D.STATUS_OB = 4 THEN 'sempurna akal'"+
        				" END AS STATUS_OB,"+ 
        				" CASE"+ 
        					" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN ''"+ 
        					" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||''"+ 
        					" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)"+ 
        					" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
        					" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')'"+ 
        				" END  AS KP_WARIS,"+ 
        				" CASE"+ 
        					" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN ''"+ 
        					" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||''"+ 
        					" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')'"+ 
        					" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
        					" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')'"+ 
        				" END  AS KP_WARIS_2"+ 
        				" FROM TBLPFDFAIL A, "+
        				" TBLPPKPERMOHONAN B,"+
        				" TBLPPKPERMOHONANSIMATI C,"+ 
        				" TBLPPKOB D,"+
        				" TBLPPKKEPUTUSANPERMOHONAN E,"+
        				" TBLPPKPERBICARAAN F,"+
        				" TBLPPKPERINTAH G,"+
        				" TBLPPKPERINTAHHAOBMST K,"+
        				" TBLPPKPERINTAHHAOBDTL L,"+    
        
        				" (SELECT"+ 
        					" CASE"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        						" ELSE TBLPPKOB.NO_KP_BARU"+ 
        					" END || '' ||"+     
        					" CASE"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
        					" END || '' ||"+     
        					" CASE"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+      
        					" END AS NO_KP4, ID_OB"+     
        					" FROM TBLPPKOB ) DDD,"+ 
        					" (SELECT"+ 
        						" CASE"+ 
        							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
        							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        							" ELSE TBLPPKOB.NO_KP_BARU"+ 
        						" END || '' ||"+     
        						" CASE"+ 
        							" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
        						" END || '' ||"+     
        						" CASE"+
        							" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
        						" END AS NO_KP42, ID_OB"+     
        						" FROM TBLPPKOB ) DDD2"+   
        						" WHERE A.ID_FAIL = B.ID_FAIL"+ 
        						" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"+ 
        						" AND C.ID_SIMATI = D.ID_SIMATI"+ 
        						" AND B.ID_PERMOHONAN = E.ID_PERMOHONAN"+
        						" AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"+
        						" AND F.ID_PERBICARAAN = G.ID_PERBICARAAN"+
        						" AND G.ID_PERINTAH = K.ID_PERINTAH"+
        						" AND K.ID_PERINTAHHAOBMST = L.ID_PERINTAHHAOBMST"+
        						" AND L.ID_OB = D.ID_OB"+
        						" AND D.ID_OB = DDD.ID_OB"+ 
        						" AND D.ID_OB = DDD2.ID_OB"+ 
        						" AND D.STATUS_OB IN (2)"+ 
        						" AND L.ID_PA1 = '"+idPenjaga1+"'"+
        						" AND L.ID_PA2 = '"+idPenjaga2+"'"+
        	        			" AND L.ID_PA3 = '"+idPenjaga3+"'";
		
		Statement stmt = db.getStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		Hashtable h;
		
		while (rs.next()) {
			h = new Hashtable();
			h.put("ID_OB",rs.getString("ID_OB")==null?"":rs.getString("ID_OB"));
			h.put("NAMA_OB",rs.getString("NAMA_OB")==null?"":rs.getString("NAMA_OB"));
			h.put("STATUS_OB",rs.getString("STATUS_OB")==null?"":rs.getString("STATUS_OB"));
			h.put("KP_WARIS",rs.getString("KP_WARIS")==null?"":rs.getString("KP_WARIS"));
			h.put("KP_WARIS_2",rs.getString("KP_WARIS_2")==null?"":rs.getString("KP_WARIS_2"));
			obBlmDewasaPenjaga3.addElement(h);
			
			
			
		}
		
		return obBlmDewasaPenjaga3;
		
			
		}finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getOBBelumDewasaPenjaga4(String idPenjaga1,String idPenjaga2, String idPenjaga3, String idPenjaga4)throws Exception{
	
	String sql = "";
	Db db = null;
	
	try{
		db = new Db();
		obBlmDewasaPenjaga4 = new Vector();
		
		sql = "SELECT DISTINCT D.ID_OB,D.NAMA_OB,"+
        	  " CASE"+ 
        	  		" WHEN D.STATUS_OB = 2 THEN 'belum dewasa'"+ 
        	  		" WHEN D.STATUS_OB = 4 THEN 'sempurna akal'"+ 
        	  " END AS STATUS_OB,"+ 
        	  " CASE"+ 
        	  		" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN ''"+ 
        	  		" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||''"+ 
        	  		" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)"+ 
        	  		" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
        	  		" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')'"+ 
        	  " END  AS KP_WARIS,"+ 
        	  " CASE"+ 
        	  		" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN ''"+ 
        	  		" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||''"+ 
        	  		" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')'"+ 
        	  		" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
        	  		" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')'"+ 
        	  " END  AS KP_WARIS_2"+ 
        	  " FROM TBLPFDFAIL A,"+ 
        	  " TBLPPKPERMOHONAN B,"+ 
        	  " TBLPPKPERMOHONANSIMATI C,"+ 
        	  " TBLPPKOB D,"+
        	  " TBLPPKKEPUTUSANPERMOHONAN E,"+
        	  " TBLPPKPERBICARAAN F,"+
        	  " TBLPPKPERINTAH G,"+
        	  " TBLPPKPERINTAHHTAOBMST I,"+
        	  " TBLPPKPERINTAHHTAOBDTL J,"+ 

        	  "(SELECT"+ 
        	  	" CASE"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+  
        	  		" ELSE TBLPPKOB.NO_KP_BARU"+ 
        	  	" END || '' ||"+     
        	  	" CASE"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
        	  	" END || '' ||"+     
        	  	" CASE"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+      
        	  	" END AS NO_KP4, ID_OB"+     
        	  	" FROM TBLPPKOB ) DDD,"+ 
        	  	" (SELECT"+ 
        	  		" CASE"+ 
        	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
        	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        	  			" ELSE TBLPPKOB.NO_KP_BARU"+ 
        	  		" END || '' ||"+     
        	  		" CASE"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
        	  		" END || '' ||"+     
        	  		" CASE"+ 
        	  			" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
        	  		" END AS NO_KP42, ID_OB"+     
        	  		" FROM TBLPPKOB ) DDD2"+   
        	  		" WHERE A.ID_FAIL = B.ID_FAIL"+ 
        	  		" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"+ 
        	  		" AND C.ID_SIMATI = D.ID_SIMATI"+ 
        	  		" AND B.ID_PERMOHONAN = E.ID_PERMOHONAN"+
        	  		" AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"+
        	  		" AND F.ID_PERBICARAAN = G.ID_PERBICARAAN"+
        	  		" AND G.ID_PERINTAH = I.ID_PERINTAH"+
        	  		" AND I.ID_PERINTAHHTAOBMST = J.ID_PERINTAHHTAOBMST"+
        	  		" AND J.ID_OB = D.ID_OB"+
        	  		" AND D.ID_OB = DDD.ID_OB"+ 
        	  		" AND D.ID_OB = DDD2.ID_OB"+
        	  		" AND D.STATUS_OB IN (2)"+
        			" AND J.ID_PA1 = '"+idPenjaga1+"'"+
        			" AND J.ID_PA2 = '"+idPenjaga2+"'"+
        			" AND J.ID_PA3 = '"+idPenjaga3+"'"+
        			" AND J.ID_PA4 = '"+idPenjaga4+"'"+
        
        			" UNION"+

        			" SELECT DISTINCT D.ID_OB,D.NAMA_OB,"+
        				" CASE"+ 
        					" WHEN D.STATUS_OB = 2 THEN 'belum dewasa'"+ 
        					" WHEN D.STATUS_OB = 4 THEN 'sempurna akal'"+
        				" END AS STATUS_OB,"+ 
        				" CASE"+ 
        					" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN ''"+ 
        					" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||''"+ 
        					" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)"+ 
        					" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
        					" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')'"+ 
        				" END  AS KP_WARIS,"+ 
        				" CASE"+ 
        					" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN ''"+ 
        					" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||''"+ 
        					" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')'"+ 
        					" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
        					" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')'"+ 
        				" END  AS KP_WARIS_2"+ 
        				" FROM TBLPFDFAIL A, "+
        				" TBLPPKPERMOHONAN B,"+
        				" TBLPPKPERMOHONANSIMATI C,"+ 
        				" TBLPPKOB D,"+
        				" TBLPPKKEPUTUSANPERMOHONAN E,"+
        				" TBLPPKPERBICARAAN F,"+
        				" TBLPPKPERINTAH G,"+
        				" TBLPPKPERINTAHHAOBMST K,"+
        				" TBLPPKPERINTAHHAOBDTL L,"+    
        
        				" (SELECT"+ 
        					" CASE"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        						" ELSE TBLPPKOB.NO_KP_BARU"+ 
        					" END || '' ||"+     
        					" CASE"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
        					" END || '' ||"+     
        					" CASE"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+      
        					" END AS NO_KP4, ID_OB"+     
        					" FROM TBLPPKOB ) DDD,"+ 
        					" (SELECT"+ 
        						" CASE"+ 
        							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
        							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        							" ELSE TBLPPKOB.NO_KP_BARU"+ 
        						" END || '' ||"+     
        						" CASE"+ 
        							" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
        						" END || '' ||"+     
        						" CASE"+
        							" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
        						" END AS NO_KP42, ID_OB"+     
        						" FROM TBLPPKOB ) DDD2"+   
        						" WHERE A.ID_FAIL = B.ID_FAIL"+ 
        						" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"+ 
        						" AND C.ID_SIMATI = D.ID_SIMATI"+ 
        						" AND B.ID_PERMOHONAN = E.ID_PERMOHONAN"+
        						" AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"+
        						" AND F.ID_PERBICARAAN = G.ID_PERBICARAAN"+
        						" AND G.ID_PERINTAH = K.ID_PERINTAH"+
        						" AND K.ID_PERINTAHHAOBMST = L.ID_PERINTAHHAOBMST"+
        						" AND L.ID_OB = D.ID_OB"+
        						" AND D.ID_OB = DDD.ID_OB"+ 
        						" AND D.ID_OB = DDD2.ID_OB"+ 
        						" AND D.STATUS_OB IN (2)"+ 
        						" AND L.ID_PA1 = '"+idPenjaga1+"'"+
								" AND L.ID_PA2 = '"+idPenjaga2+"'"+
								" AND L.ID_PA3 = '"+idPenjaga3+"'"+
								" AND L.ID_PA4 = '"+idPenjaga4+"'";
								
		Statement stmt = db.getStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		Hashtable h;
		
		while (rs.next()) {
			h = new Hashtable();
			h.put("ID_OB",rs.getString("ID_OB")==null?"":rs.getString("ID_OB"));
			h.put("NAMA_OB",rs.getString("NAMA_OB")==null?"":rs.getString("NAMA_OB"));
			h.put("STATUS_OB",rs.getString("STATUS_OB")==null?"":rs.getString("STATUS_OB"));
			h.put("KP_WARIS",rs.getString("KP_WARIS")==null?"":rs.getString("KP_WARIS"));
			h.put("KP_WARIS_2",rs.getString("KP_WARIS_2")==null?"":rs.getString("KP_WARIS_2"));
			obBlmDewasaPenjaga4.addElement(h);
			
			
			
		}
		
		return obBlmDewasaPenjaga4;
		
			
		}finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector getOBTdkSempurnaAkalPenjaga1(String idPenjaga1)throws Exception{
	
		String sql = "";
		Db db = null;
		
		try{
			db = new Db();
			obTdkSempurnaPenjaga1 = new Vector();
			
			sql = "SELECT DISTINCT D.ID_OB,D.NAMA_OB,"+
            	  " CASE"+ 
            	  		" WHEN D.STATUS_OB = 2 THEN 'belum dewasa'"+ 
            	  		" WHEN D.STATUS_OB = 4 THEN 'sempurna akal'"+ 
            	  " END AS STATUS_OB,"+ 
            	  " CASE"+ 
            	  		" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN ''"+ 
            	  		" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||''"+ 
            	  		" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)"+ 
            	  		" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
            	  		" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')'"+ 
            	  " END  AS KP_WARIS,"+ 
            	  " CASE"+ 
            	  		" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN ''"+ 
            	  		" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||''"+ 
            	  		" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')'"+ 
            	  		" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
            	  		" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')'"+ 
            	  " END  AS KP_WARIS_2"+ 
            	  " FROM TBLPFDFAIL A,"+ 
            	  " TBLPPKPERMOHONAN B,"+ 
            	  " TBLPPKPERMOHONANSIMATI C,"+ 
            	  " TBLPPKOB D,"+
            	  " TBLPPKKEPUTUSANPERMOHONAN E,"+
            	  " TBLPPKPERBICARAAN F,"+
            	  " TBLPPKPERINTAH G,"+
            	  " TBLPPKPERINTAHHTAOBMST I,"+
            	  " TBLPPKPERINTAHHTAOBDTL J,"+ 
  
            	  "(SELECT"+ 
            	  	" CASE"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+  
            	  		" ELSE TBLPPKOB.NO_KP_BARU"+ 
            	  	" END || '' ||"+     
            	  	" CASE"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
            	  	" END || '' ||"+     
            	  	" CASE"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+      
            	  	" END AS NO_KP4, ID_OB"+     
            	  	" FROM TBLPPKOB ) DDD,"+ 
            	  	" (SELECT"+ 
            	  		" CASE"+ 
            	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
            	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            	  			" ELSE TBLPPKOB.NO_KP_BARU"+ 
            	  		" END || '' ||"+     
            	  		" CASE"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
            	  		" END || '' ||"+     
            	  		" CASE"+ 
            	  			" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
            	  		" END AS NO_KP42, ID_OB"+     
            	  		" FROM TBLPPKOB ) DDD2"+   
            	  		" WHERE A.ID_FAIL = B.ID_FAIL"+ 
            	  		" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"+ 
            	  		" AND C.ID_SIMATI = D.ID_SIMATI"+ 
            	  		" AND B.ID_PERMOHONAN = E.ID_PERMOHONAN"+
            	  		" AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"+
            	  		" AND F.ID_PERBICARAAN = G.ID_PERBICARAAN"+
            	  		" AND G.ID_PERINTAH = I.ID_PERINTAH"+
            	  		" AND I.ID_PERINTAHHTAOBMST = J.ID_PERINTAHHTAOBMST"+
            	  		" AND J.ID_OB = D.ID_OB"+
            	  		" AND D.ID_OB = DDD.ID_OB"+ 
            	  		" AND D.ID_OB = DDD2.ID_OB"+
            	  		" AND D.STATUS_OB IN (4)"+
            			" AND J.ID_PA1 = '"+idPenjaga1+"'"+
            
            			" UNION"+

            			" SELECT DISTINCT D.ID_OB,D.NAMA_OB,"+
            				" CASE"+ 
            					" WHEN D.STATUS_OB = 2 THEN 'belum dewasa'"+ 
            					" WHEN D.STATUS_OB = 4 THEN 'sempurna akal'"+
            				" END AS STATUS_OB,"+ 
            				" CASE"+ 
            					" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN ''"+ 
            					" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||''"+ 
            					" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)"+ 
            					" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
            					" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')'"+ 
            				" END  AS KP_WARIS,"+ 
            				" CASE"+ 
            					" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN ''"+ 
            					" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||''"+ 
            					" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')'"+ 
            					" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
            					" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')'"+ 
            				" END  AS KP_WARIS_2"+ 
            				" FROM TBLPFDFAIL A, "+
            				" TBLPPKPERMOHONAN B,"+
            				" TBLPPKPERMOHONANSIMATI C,"+ 
            				" TBLPPKOB D,"+
            				" TBLPPKKEPUTUSANPERMOHONAN E,"+
            				" TBLPPKPERBICARAAN F,"+
            				" TBLPPKPERINTAH G,"+
            				" TBLPPKPERINTAHHAOBMST K,"+
            				" TBLPPKPERINTAHHAOBDTL L,"+    
            
            				" (SELECT"+ 
            					" CASE"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            						" ELSE TBLPPKOB.NO_KP_BARU"+ 
            					" END || '' ||"+     
            					" CASE"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
            					" END || '' ||"+     
            					" CASE"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+      
            					" END AS NO_KP4, ID_OB"+     
            					" FROM TBLPPKOB ) DDD,"+ 
            					" (SELECT"+ 
            						" CASE"+ 
            							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
            							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            							" ELSE TBLPPKOB.NO_KP_BARU"+ 
            						" END || '' ||"+     
            						" CASE"+ 
            							" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
            						" END || '' ||"+     
            						" CASE"+
            							" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
            						" END AS NO_KP42, ID_OB"+     
            						" FROM TBLPPKOB ) DDD2"+   
            						" WHERE A.ID_FAIL = B.ID_FAIL"+ 
            						" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"+ 
            						" AND C.ID_SIMATI = D.ID_SIMATI"+ 
            						" AND B.ID_PERMOHONAN = E.ID_PERMOHONAN"+
            						" AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"+
            						" AND F.ID_PERBICARAAN = G.ID_PERBICARAAN"+
            						" AND G.ID_PERINTAH = K.ID_PERINTAH"+
            						" AND K.ID_PERINTAHHAOBMST = L.ID_PERINTAHHAOBMST"+
            						" AND L.ID_OB = D.ID_OB"+
            						" AND D.ID_OB = DDD.ID_OB"+ 
            						" AND D.ID_OB = DDD2.ID_OB"+ 
            						" AND D.STATUS_OB IN (4)"+ 
            						" AND L.ID_PA1 = '"+idPenjaga1+"'";
			
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_OB",rs.getString("ID_OB")==null?"":rs.getString("ID_OB"));
				h.put("NAMA_OB",rs.getString("NAMA_OB")==null?"":rs.getString("NAMA_OB"));
				h.put("STATUS_OB",rs.getString("STATUS_OB")==null?"":rs.getString("STATUS_OB"));
				h.put("KP_WARIS",rs.getString("KP_WARIS")==null?"":rs.getString("KP_WARIS"));
				h.put("KP_WARIS_2",rs.getString("KP_WARIS_2")==null?"":rs.getString("KP_WARIS_2"));
				obTdkSempurnaPenjaga1.addElement(h);
				
				
				
			}
			
			return obTdkSempurnaPenjaga1;
			
			
		}finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector getOBTdkSempurnaAkalPenjaga(String idPenjaga)throws Exception{
		
		String sql = "";
		Db db = null;
		
		try{
			db = new Db();
			obTdkSempurnaPenjaga2 = new Vector();
			
			sql = "SELECT DISTINCT D.ID_OB,D.NAMA_OB,"+
            	  " CASE"+ 
            	  		" WHEN D.STATUS_OB = 2 THEN 'belum dewasa'"+ 
            	  		" WHEN D.STATUS_OB = 4 THEN 'sempurna akal'"+ 
            	  " END AS STATUS_OB,"+ 
            	  " CASE"+ 
            	  		" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN ''"+ 
            	  		" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||''"+ 
            	  		" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)"+ 
            	  		" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
            	  		" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')'"+ 
            	  " END  AS KP_WARIS,"+ 
            	  " CASE"+ 
            	  		" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN ''"+ 
            	  		" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||''"+ 
            	  		" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')'"+ 
            	  		" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
            	  		" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')'"+ 
            	  " END  AS KP_WARIS_2"+ 
            	  " FROM TBLPFDFAIL A,"+ 
            	  " TBLPPKPERMOHONAN B,"+ 
            	  " TBLPPKPERMOHONANSIMATI C,"+ 
            	  " TBLPPKOB D,"+
            	  " TBLPPKKEPUTUSANPERMOHONAN E,"+
            	  " TBLPPKPERBICARAAN F,"+
            	  " TBLPPKPERINTAH G,"+
            	  " TBLPPKPERINTAHHTAOBMST I,"+
            	  " TBLPPKPERINTAHHTAOBDTL J,"+ 
  
            	  "(SELECT"+ 
            	  	" CASE"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+  
            	  		" ELSE TBLPPKOB.NO_KP_BARU"+ 
            	  	" END || '' ||"+     
            	  	" CASE"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
            	  	" END || '' ||"+     
            	  	" CASE"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+      
            	  	" END AS NO_KP4, ID_OB"+     
            	  	" FROM TBLPPKOB ) DDD,"+ 
            	  	" (SELECT"+ 
            	  		" CASE"+ 
            	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
            	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            	  			" ELSE TBLPPKOB.NO_KP_BARU"+ 
            	  		" END || '' ||"+     
            	  		" CASE"+ 
            	  		" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
            	  		" END || '' ||"+     
            	  		" CASE"+ 
            	  			" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
            	  		" END AS NO_KP42, ID_OB"+     
            	  		" FROM TBLPPKOB ) DDD2"+   
            	  		" WHERE A.ID_FAIL = B.ID_FAIL"+ 
            	  		" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"+ 
            	  		" AND C.ID_SIMATI = D.ID_SIMATI"+ 
            	  		" AND B.ID_PERMOHONAN = E.ID_PERMOHONAN"+
            	  		" AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"+
            	  		" AND F.ID_PERBICARAAN = G.ID_PERBICARAAN"+
            	  		" AND G.ID_PERINTAH = I.ID_PERINTAH"+
            	  		" AND I.ID_PERINTAHHTAOBMST = J.ID_PERINTAHHTAOBMST"+
            	  		" AND J.ID_OB = D.ID_OB"+
            	  		" AND D.ID_OB = DDD.ID_OB"+ 
            	  		" AND D.ID_OB = DDD2.ID_OB"+
            	  		" AND D.STATUS_OB IN (4)"+
            			" AND (J.ID_PA1 = '"+idPenjaga+"'"+
            			" OR J.ID_PA2 = '"+idPenjaga+"'"+
            			" OR J.ID_PA3 = '"+idPenjaga+"'"+
            			" OR J.ID_PA4 = '"+idPenjaga+"'"+")"+
            
            			" UNION"+

            			" SELECT DISTINCT D.ID_OB,D.NAMA_OB,"+
            				" CASE"+ 
            					" WHEN D.STATUS_OB = 2 THEN 'belum dewasa'"+ 
            					" WHEN D.STATUS_OB = 4 THEN 'sempurna akal'"+
            				" END AS STATUS_OB,"+ 
            				" CASE"+ 
            					" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN ''"+ 
            					" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||''"+ 
            					" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)"+ 
            					" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
            					" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')'"+ 
            				" END  AS KP_WARIS,"+ 
            				" CASE"+ 
            					" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN ''"+ 
            					" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||''"+ 
            					" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')'"+ 
            					" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
            					" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')'"+ 
            				" END  AS KP_WARIS_2"+ 
            				" FROM TBLPFDFAIL A, "+
            				" TBLPPKPERMOHONAN B,"+
            				" TBLPPKPERMOHONANSIMATI C,"+ 
            				" TBLPPKOB D,"+
            				" TBLPPKKEPUTUSANPERMOHONAN E,"+
            				" TBLPPKPERBICARAAN F,"+
            				" TBLPPKPERINTAH G,"+
            				" TBLPPKPERINTAHHAOBMST K,"+
            				" TBLPPKPERINTAHHAOBDTL L,"+    
            
            				" (SELECT"+ 
            					" CASE"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            						" ELSE TBLPPKOB.NO_KP_BARU"+ 
            					" END || '' ||"+     
            					" CASE"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
            					" END || '' ||"+     
            					" CASE"+ 
            						" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+      
            					" END AS NO_KP4, ID_OB"+     
            					" FROM TBLPPKOB ) DDD,"+ 
            					" (SELECT"+ 
            						" CASE"+ 
            							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
            							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
            							" ELSE TBLPPKOB.NO_KP_BARU"+ 
            						" END || '' ||"+     
            						" CASE"+ 
            							" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
            						" END || '' ||"+     
            						" CASE"+
            							" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
            						" END AS NO_KP42, ID_OB"+     
            						" FROM TBLPPKOB ) DDD2"+   
            						" WHERE A.ID_FAIL = B.ID_FAIL"+ 
            						" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"+ 
            						" AND C.ID_SIMATI = D.ID_SIMATI"+ 
            						" AND B.ID_PERMOHONAN = E.ID_PERMOHONAN"+
            						" AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"+
            						" AND F.ID_PERBICARAAN = G.ID_PERBICARAAN"+
            						" AND G.ID_PERINTAH = K.ID_PERINTAH"+
            						" AND K.ID_PERINTAHHAOBMST = L.ID_PERINTAHHAOBMST"+
            						" AND L.ID_OB = D.ID_OB"+
            						" AND D.ID_OB = DDD.ID_OB"+ 
            						" AND D.ID_OB = DDD2.ID_OB"+ 
            						" AND D.STATUS_OB IN (4)"+ 
            						" AND L.ID_PA1 = '"+idPenjaga+"'"+
            						" AND L.ID_PA2 = '"+idPenjaga+"'"+
            						" AND L.ID_PA3 = '"+idPenjaga+"'"+
            						" AND L.ID_PA4 = '"+idPenjaga+"'";
			
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_OB",rs.getString("ID_OB")==null?"":rs.getString("ID_OB"));
				h.put("NAMA_OB",rs.getString("NAMA_OB")==null?"":rs.getString("NAMA_OB"));
				h.put("STATUS_OB",rs.getString("STATUS_OB")==null?"":rs.getString("STATUS_OB"));
				h.put("KP_WARIS",rs.getString("KP_WARIS")==null?"":rs.getString("KP_WARIS"));
				h.put("KP_WARIS_2",rs.getString("KP_WARIS_2")==null?"":rs.getString("KP_WARIS_2"));
				obTdkSempurnaPenjaga2.addElement(h);
				
				
				
			}
			
			return obTdkSempurnaPenjaga2;
			
			
		}finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getOBTdkSempurnaAkalPenjaga3(String idPenjaga1,String idPenjaga2, String idPenjaga3)throws Exception{
	
	String sql = "";
	Db db = null;
	
	try{
		db = new Db();
		obTdkSempurnaPenjaga3 = new Vector();
		
		sql = "SELECT DISTINCT D.ID_OB,D.NAMA_OB,"+
        	  " CASE"+ 
        	  		" WHEN D.STATUS_OB = 2 THEN 'belum dewasa'"+ 
        	  		" WHEN D.STATUS_OB = 4 THEN 'sempurna akal'"+ 
        	  " END AS STATUS_OB,"+ 
        	  " CASE"+ 
        	  		" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN ''"+ 
        	  		" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||''"+ 
        	  		" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)"+ 
        	  		" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
        	  		" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')'"+ 
        	  " END  AS KP_WARIS,"+ 
        	  " CASE"+ 
        	  		" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN ''"+ 
        	  		" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||''"+ 
        	  		" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')'"+ 
        	  		" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
        	  		" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')'"+ 
        	  " END  AS KP_WARIS_2"+ 
        	  " FROM TBLPFDFAIL A,"+ 
        	  " TBLPPKPERMOHONAN B,"+ 
        	  " TBLPPKPERMOHONANSIMATI C,"+ 
        	  " TBLPPKOB D,"+
        	  " TBLPPKKEPUTUSANPERMOHONAN E,"+
        	  " TBLPPKPERBICARAAN F,"+
        	  " TBLPPKPERINTAH G,"+
        	  " TBLPPKPERINTAHHTAOBMST I,"+
        	  " TBLPPKPERINTAHHTAOBDTL J,"+ 

        	  "(SELECT"+ 
        	  	" CASE"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+  
        	  		" ELSE TBLPPKOB.NO_KP_BARU"+ 
        	  	" END || '' ||"+     
        	  	" CASE"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
        	  	" END || '' ||"+     
        	  	" CASE"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+      
        	  	" END AS NO_KP4, ID_OB"+     
        	  	" FROM TBLPPKOB ) DDD,"+ 
        	  	" (SELECT"+ 
        	  		" CASE"+ 
        	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
        	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        	  			" ELSE TBLPPKOB.NO_KP_BARU"+ 
        	  		" END || '' ||"+     
        	  		" CASE"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
        	  		" END || '' ||"+     
        	  		" CASE"+ 
        	  			" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
        	  		" END AS NO_KP42, ID_OB"+     
        	  		" FROM TBLPPKOB ) DDD2"+   
        	  		" WHERE A.ID_FAIL = B.ID_FAIL"+ 
        	  		" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"+ 
        	  		" AND C.ID_SIMATI = D.ID_SIMATI"+ 
        	  		" AND B.ID_PERMOHONAN = E.ID_PERMOHONAN"+
        	  		" AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"+
        	  		" AND F.ID_PERBICARAAN = G.ID_PERBICARAAN"+
        	  		" AND G.ID_PERINTAH = I.ID_PERINTAH"+
        	  		" AND I.ID_PERINTAHHTAOBMST = J.ID_PERINTAHHTAOBMST"+
        	  		" AND J.ID_OB = D.ID_OB"+
        	  		" AND D.ID_OB = DDD.ID_OB"+ 
        	  		" AND D.ID_OB = DDD2.ID_OB"+
        	  		" AND D.STATUS_OB IN (4)"+
        			" AND J.ID_PA1 = '"+idPenjaga1+"'"+
        			" AND J.ID_PA2 = '"+idPenjaga2+"'"+
        			" AND J.ID_PA3 = '"+idPenjaga3+"'"+
        
        			" UNION"+

        			" SELECT DISTINCT D.ID_OB,D.NAMA_OB,"+
        				" CASE"+ 
        					" WHEN D.STATUS_OB = 2 THEN 'belum dewasa'"+ 
        					" WHEN D.STATUS_OB = 4 THEN 'sempurna akal'"+
        				" END AS STATUS_OB,"+ 
        				" CASE"+ 
        					" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN ''"+ 
        					" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||''"+ 
        					" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)"+ 
        					" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
        					" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')'"+ 
        				" END  AS KP_WARIS,"+ 
        				" CASE"+ 
        					" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN ''"+ 
        					" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||''"+ 
        					" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')'"+ 
        					" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
        					" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')'"+ 
        				" END  AS KP_WARIS_2"+ 
        				" FROM TBLPFDFAIL A, "+
        				" TBLPPKPERMOHONAN B,"+
        				" TBLPPKPERMOHONANSIMATI C,"+ 
        				" TBLPPKOB D,"+
        				" TBLPPKKEPUTUSANPERMOHONAN E,"+
        				" TBLPPKPERBICARAAN F,"+
        				" TBLPPKPERINTAH G,"+
        				" TBLPPKPERINTAHHAOBMST K,"+
        				" TBLPPKPERINTAHHAOBDTL L,"+    
        
        				" (SELECT"+ 
        					" CASE"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        						" ELSE TBLPPKOB.NO_KP_BARU"+ 
        					" END || '' ||"+     
        					" CASE"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
        					" END || '' ||"+     
        					" CASE"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+      
        					" END AS NO_KP4, ID_OB"+     
        					" FROM TBLPPKOB ) DDD,"+ 
        					" (SELECT"+ 
        						" CASE"+ 
        							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
        							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        							" ELSE TBLPPKOB.NO_KP_BARU"+ 
        						" END || '' ||"+     
        						" CASE"+ 
        							" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
        						" END || '' ||"+     
        						" CASE"+
        							" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
        						" END AS NO_KP42, ID_OB"+     
        						" FROM TBLPPKOB ) DDD2"+   
        						" WHERE A.ID_FAIL = B.ID_FAIL"+ 
        						" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"+ 
        						" AND C.ID_SIMATI = D.ID_SIMATI"+ 
        						" AND B.ID_PERMOHONAN = E.ID_PERMOHONAN"+
        						" AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"+
        						" AND F.ID_PERBICARAAN = G.ID_PERBICARAAN"+
        						" AND G.ID_PERINTAH = K.ID_PERINTAH"+
        						" AND K.ID_PERINTAHHAOBMST = L.ID_PERINTAHHAOBMST"+
        						" AND L.ID_OB = D.ID_OB"+
        						" AND D.ID_OB = DDD.ID_OB"+ 
        						" AND D.ID_OB = DDD2.ID_OB"+ 
        						" AND D.STATUS_OB IN (4)"+ 
        						" AND L.ID_PA1 = '"+idPenjaga1+"'"+
        						" AND L.ID_PA2 = '"+idPenjaga2+"'"+
        	        			" AND L.ID_PA3 = '"+idPenjaga3+"'";
		
		Statement stmt = db.getStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		Hashtable h;
		
		while (rs.next()) {
			h = new Hashtable();
			h.put("ID_OB",rs.getString("ID_OB")==null?"":rs.getString("ID_OB"));
			h.put("NAMA_OB",rs.getString("NAMA_OB")==null?"":rs.getString("NAMA_OB"));
			h.put("STATUS_OB",rs.getString("STATUS_OB")==null?"":rs.getString("STATUS_OB"));
			h.put("KP_WARIS",rs.getString("KP_WARIS")==null?"":rs.getString("KP_WARIS"));
			h.put("KP_WARIS_2",rs.getString("KP_WARIS_2")==null?"":rs.getString("KP_WARIS_2"));
			obTdkSempurnaPenjaga3.addElement(h);
			
			
			
		}
		
		return obTdkSempurnaPenjaga3;
		
			
		}finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getOBTdkSempurnaAkalPenjaga4(String idPenjaga1,String idPenjaga2, String idPenjaga3, String idPenjaga4)throws Exception{
	
	String sql = "";
	Db db = null;
	
	try{
		db = new Db();
		obTdkSempurnaPenjaga4 = new Vector();
		
		sql = "SELECT DISTINCT D.ID_OB,D.NAMA_OB,"+
        	  " CASE"+ 
        	  		" WHEN D.STATUS_OB = 2 THEN 'belum dewasa'"+ 
        	  		" WHEN D.STATUS_OB = 4 THEN 'sempurna akal'"+ 
        	  " END AS STATUS_OB,"+ 
        	  " CASE"+ 
        	  		" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN ''"+ 
        	  		" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||''"+ 
        	  		" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)"+ 
        	  		" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
        	  		" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')'"+ 
        	  " END  AS KP_WARIS,"+ 
        	  " CASE"+ 
        	  		" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN ''"+ 
        	  		" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||''"+ 
        	  		" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')'"+ 
        	  		" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
        	  		" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')'"+ 
        	  " END  AS KP_WARIS_2"+ 
        	  " FROM TBLPFDFAIL A,"+ 
        	  " TBLPPKPERMOHONAN B,"+ 
        	  " TBLPPKPERMOHONANSIMATI C,"+ 
        	  " TBLPPKOB D,"+
        	  " TBLPPKKEPUTUSANPERMOHONAN E,"+
        	  " TBLPPKPERBICARAAN F,"+
        	  " TBLPPKPERINTAH G,"+
        	  " TBLPPKPERINTAHHTAOBMST I,"+
        	  " TBLPPKPERINTAHHTAOBDTL J,"+ 

        	  "(SELECT"+ 
        	  	" CASE"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+  
        	  		" ELSE TBLPPKOB.NO_KP_BARU"+ 
        	  	" END || '' ||"+     
        	  	" CASE"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
        	  	" END || '' ||"+     
        	  	" CASE"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+      
        	  	" END AS NO_KP4, ID_OB"+     
        	  	" FROM TBLPPKOB ) DDD,"+ 
        	  	" (SELECT"+ 
        	  		" CASE"+ 
        	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
        	  			" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        	  			" ELSE TBLPPKOB.NO_KP_BARU"+ 
        	  		" END || '' ||"+     
        	  		" CASE"+ 
        	  		" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
        	  		" END || '' ||"+     
        	  		" CASE"+ 
        	  			" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
        	  		" END AS NO_KP42, ID_OB"+     
        	  		" FROM TBLPPKOB ) DDD2"+   
        	  		" WHERE A.ID_FAIL = B.ID_FAIL"+ 
        	  		" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"+ 
        	  		" AND C.ID_SIMATI = D.ID_SIMATI"+ 
        	  		" AND B.ID_PERMOHONAN = E.ID_PERMOHONAN"+
        	  		" AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"+
        	  		" AND F.ID_PERBICARAAN = G.ID_PERBICARAAN"+
        	  		" AND G.ID_PERINTAH = I.ID_PERINTAH"+
        	  		" AND I.ID_PERINTAHHTAOBMST = J.ID_PERINTAHHTAOBMST"+
        	  		" AND J.ID_OB = D.ID_OB"+
        	  		" AND D.ID_OB = DDD.ID_OB"+ 
        	  		" AND D.ID_OB = DDD2.ID_OB"+
        	  		" AND D.STATUS_OB IN (4)"+
        			" AND J.ID_PA1 = '"+idPenjaga1+"'"+
        			" AND J.ID_PA2 = '"+idPenjaga2+"'"+
        			" AND J.ID_PA3 = '"+idPenjaga3+"'"+
        			" AND J.ID_PA4 = '"+idPenjaga4+"'"+
        
        			" UNION"+

        			" SELECT DISTINCT D.ID_OB,D.NAMA_OB,"+
        				" CASE"+ 
        					" WHEN D.STATUS_OB = 2 THEN 'belum dewasa'"+ 
        					" WHEN D.STATUS_OB = 4 THEN 'sempurna akal'"+
        				" END AS STATUS_OB,"+ 
        				" CASE"+ 
        					" WHEN LENGTH (DDD.NO_KP4) IS NULL THEN ''"+ 
        					" WHEN LENGTH(DDD.NO_KP4)<12 THEN  ''||RTRIM(DDD.NO_KP4)||''"+ 
        					" WHEN LENGTH(RTRIM(DDD.NO_KP4))=12 THEN SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)"+ 
        					" WHEN DDD.NO_KP4 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
        					" ELSE SUBSTR(DDD.NO_KP4,1,6) || '-' || SUBSTR(DDD.NO_KP4,7,2) || '-' || SUBSTR(DDD.NO_KP4,9,4)  ||' ('||SUBSTR(DDD.NO_KP4,13,LENGTH(DDD.NO_KP4))||')'"+ 
        				" END  AS KP_WARIS,"+ 
        				" CASE"+ 
        					" WHEN LENGTH (DDD2.NO_KP42) IS NULL THEN ''"+ 
        					" WHEN LENGTH(DDD2.NO_KP42)<12 THEN  ''||RTRIM(DDD2.NO_KP42)||''"+ 
        					" WHEN LENGTH(RTRIM(DDD2.NO_KP42))=12 THEN '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4) ||')'"+ 
        					" WHEN DDD2.NO_KP42 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
        					" ELSE '('||SUBSTR(DDD2.NO_KP42,1,6) || '-' || SUBSTR(DDD2.NO_KP42,7,2) || '-' || SUBSTR(DDD2.NO_KP42,9,4)  ||' / '||SUBSTR(DDD2.NO_KP42,13,LENGTH(DDD2.NO_KP42))||')'"+ 
        				" END  AS KP_WARIS_2"+ 
        				" FROM TBLPFDFAIL A, "+
        				" TBLPPKPERMOHONAN B,"+
        				" TBLPPKPERMOHONANSIMATI C,"+ 
        				" TBLPPKOB D,"+
        				" TBLPPKKEPUTUSANPERMOHONAN E,"+
        				" TBLPPKPERBICARAAN F,"+
        				" TBLPPKPERINTAH G,"+
        				" TBLPPKPERINTAHHAOBMST K,"+
        				" TBLPPKPERINTAHHAOBDTL L,"+    
        
        				" (SELECT"+ 
        					" CASE"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        						" ELSE TBLPPKOB.NO_KP_BARU"+ 
        					" END || '' ||"+     
        					" CASE"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
        					" END || '' ||"+     
        					" CASE"+ 
        						" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+      
        					" END AS NO_KP4, ID_OB"+     
        					" FROM TBLPPKOB ) DDD,"+ 
        					" (SELECT"+ 
        						" CASE"+ 
        							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+ 
        							" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
        							" ELSE TBLPPKOB.NO_KP_BARU"+ 
        						" END || '' ||"+     
        						" CASE"+ 
        							" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+ 
        						" END || '' ||"+     
        						" CASE"+
        							" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
        						" END AS NO_KP42, ID_OB"+     
        						" FROM TBLPPKOB ) DDD2"+   
        						" WHERE A.ID_FAIL = B.ID_FAIL"+ 
        						" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"+ 
        						" AND C.ID_SIMATI = D.ID_SIMATI"+ 
        						" AND B.ID_PERMOHONAN = E.ID_PERMOHONAN"+
        						" AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"+
        						" AND F.ID_PERBICARAAN = G.ID_PERBICARAAN"+
        						" AND G.ID_PERINTAH = K.ID_PERINTAH"+
        						" AND K.ID_PERINTAHHAOBMST = L.ID_PERINTAHHAOBMST"+
        						" AND L.ID_OB = D.ID_OB"+
        						" AND D.ID_OB = DDD.ID_OB"+ 
        						" AND D.ID_OB = DDD2.ID_OB"+ 
        						" AND D.STATUS_OB IN (4)"+ 
        						" AND L.ID_PA1 = '"+idPenjaga1+"'"+
								" AND L.ID_PA2 = '"+idPenjaga2+"'"+
								" AND L.ID_PA3 = '"+idPenjaga3+"'"+
								" AND L.ID_PA4 = '"+idPenjaga4+"'";
								
		Statement stmt = db.getStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		Hashtable h;
		
		while (rs.next()) {
			h = new Hashtable();
			h.put("ID_OB",rs.getString("ID_OB")==null?"":rs.getString("ID_OB"));
			h.put("NAMA_OB",rs.getString("NAMA_OB")==null?"":rs.getString("NAMA_OB"));
			h.put("STATUS_OB",rs.getString("STATUS_OB")==null?"":rs.getString("STATUS_OB"));
			h.put("KP_WARIS",rs.getString("KP_WARIS")==null?"":rs.getString("KP_WARIS"));
			h.put("KP_WARIS_2",rs.getString("KP_WARIS_2")==null?"":rs.getString("KP_WARIS_2"));
			obTdkSempurnaPenjaga4.addElement(h);
			
			
			
		}
		
		return obTdkSempurnaPenjaga4;
		
			
		}finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	
	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		
		String idfail = "";
		if (parameters.get("idfail") != null){
			idfail = parameters.get("idfail").toString();
		}
		
		String nofail = logic.getNoFailByIdFail(idfail);
		
		String flagVersion = (String)parameters.get("flagVersion");
		doVersioning("BorangH_Perintah",idfail,nofail,flagVersion);
		
	
//		Vector penjaga = null;
//		penjaga= getIdPenjaga(idfail);
//		
//		Vector ob = null;
//		Vector obMinor2 = null;
//		
//		obBlmDewasa = new Vector();
//		obTdkSempurnaAkal = new Vector();
//		
//		String temp1 = "";
//		String temp5 = "";
//		String temp9 = "";
//		String temp13 = "";
//		String tempPenjaga = "";
//		String tempOB = "";
//		String tempOBBlmDewasa = "";
//		String tempOBTdkSempurnaAkal = "";
//	
//		Hashtable hPenjaga = null;
//		for(int a=0; a<penjaga.size();a++){
//			hPenjaga= (Hashtable)penjaga.get(a);
//			
//			if(!hPenjaga.get("ID_PA1").equals("")){
//				
//					obBlmDewasa = getOBBelumDewasaPenjaga(hPenjaga.get("ID_PA1").toString());
//					obTdkSempurnaAkal = getOBTdkSempurnaAkalPenjaga(hPenjaga.get("ID_PA1").toString());
//					
//				
//					
//					ob = new Vector();
//					ob = getOBMinor(hPenjaga.get("ID_PA1").toString());
//					Hashtable hOb = null;
//					
//					
//					
//					temp13 = "";
//					Hashtable hObMinor = null;
//					obMinor2 = new Vector();
//					for (int b = 0; b < ob.size();b++){
//						hOb = (Hashtable)ob.get(b);
//						hObMinor = new Hashtable();
//						if (ob.size() - b != 1 ){
//							
//							if(temp13 == ""){
//								temp13 = hOb.get("NAMA_OB")+" " +hOb.get("KP_WARIS_2");
//								
//							}
//							else{
//								temp13 = temp13 + ", " + hOb.get("NAMA_OB")+" " +hOb.get("KP_WARIS_2");
//								
//							}
//							
//							
//						}
//						else{
//							
//							if(temp13 == ""){
//								temp13 = hOb.get("NAMA_OB")+" " +hOb.get("KP_WARIS_2");
//							}
//							else{
//								temp13 = temp13 + " dan " + hOb.get("NAMA_OB")+" " +hOb.get("KP_WARIS_2");
//							}
//							
//						}
//						
//						
//						
//					}
//					
//					hObMinor.put("temp13",temp13);
//					obMinor2.add(hObMinor);
//					
//					Hashtable hOb2 = null;
//					hOb2 = (Hashtable)obMinor2.get(0);
//				
//											
//					parameters.put("obMinor2" ,hOb2.get("temp13"));
//					
//					
//					
//					
//					if (obBlmDewasa.size() != 0){
//
//						for (int d = 0; d < obBlmDewasa.size(); d++){
//							
//							Hashtable hOBBlmDewasaP1 = (Hashtable)obBlmDewasa.get(d);
//
//							if(obBlmDewasa.size() - d != 1){
//								
//								if(temp5 == ""){
//									temp5 = hOBBlmDewasaP1.get("NAMA_OB")+ " " + hOBBlmDewasaP1.get("KP_WARIS_2");
//								}
//								else{
//									temp5 = ", " + hOBBlmDewasaP1.get("NAMA_OB")+ " " + hOBBlmDewasaP1.get("KP_WARIS_2");
//								}
//								
//							}
//							else{
//								
//								if(temp5 == ""){
//									temp5 = hOBBlmDewasaP1.get("NAMA_OB")+ " " + hOBBlmDewasaP1.get("KP_WARIS_2");
//								}
//								else{
//									temp5 = temp5 + " dan " + hOBBlmDewasaP1.get("NAMA_OB")+ " " + hOBBlmDewasaP1.get("KP_WARIS_2");
//								}
//								
//							}
//							
//						}
//					}
//				
//				
//				 
//				 
//				 
//					if (obTdkSempurnaAkal.size() != 0){
//						for (int e = 0; e < obTdkSempurnaAkal.size(); e++){
//							
//							Hashtable hOBTdkSempurnaAkalP1 = (Hashtable)obTdkSempurnaAkal.get(e);
//							
//							if(obTdkSempurnaAkal.size() - e != 1){
//								
//								if(temp9 == ""){
//									temp9 = hOBTdkSempurnaAkalP1.get("NAMA_OB")+ " " + hOBTdkSempurnaAkalP1.get("KP_WARIS_2");
//								}
//								else{
//									temp9 = ", " + hOBTdkSempurnaAkalP1.get("NAMA_OB")+ " " + hOBTdkSempurnaAkalP1.get("KP_WARIS_2");
//								}
//								
//							}
//							else{
//								
//								if(temp9 == ""){
//									temp9 = hOBTdkSempurnaAkalP1.get("NAMA_OB")+ " " + hOBTdkSempurnaAkalP1.get("KP_WARIS_2");
//								}
//								else{
//									temp9 = temp9 + " dan " + hOBTdkSempurnaAkalP1.get("NAMA_OB")+ " " + hOBTdkSempurnaAkalP1.get("KP_WARIS_2");
//								}
//								
//							}
//							
//						}
//					}
//				
//				
//					
//				
//				if(temp5 != ""){
//					if(obBlmDewasa.size() == 1){
//						tempOBBlmDewasa = temp5 + " adalah seorang yang belum dewasa";
//
//					}
//					else if(obBlmDewasa.size() > 1){
//						tempOBBlmDewasa = temp5 + " adalah belum dewasa";
//					}
//					
//				}	
//				
//				
//			
//				
//				if(temp9 != ""){
//					if(obTdkSempurnaAkal.size() == 1){
//						tempOBTdkSempurnaAkal = temp9 + " adalah seorang yang tidak sempurna akal";
//					}
//					else if(obTdkSempurnaAkal.size() > 1){
//						tempOBTdkSempurnaAkal = temp9 + " adalah tidak sempurna akal";
//					}
//					
//				}	
//				
//					
//				
//				if(tempOBBlmDewasa != ""){
//					if (tempOBTdkSempurnaAkal != "") {
//						
//						parameters.put("obMinor" , tempOBBlmDewasa + " dan " + tempOBTdkSempurnaAkal);
//					} else {
//						parameters.put("obMinor" , tempOBBlmDewasa );
//					}
//					
//				}
//				else if(tempOBTdkSempurnaAkal != ""){
//					if(tempOBBlmDewasa != ""){
//						parameters.put("obMinor" , tempOBBlmDewasa + " dan " + tempOBTdkSempurnaAkal);
//					}
//					else{
//						parameters.put("obMinor" ,tempOBTdkSempurnaAkal);
//					}
//					
//				}
//					
//			}
//			
//			
//	
//		}
//		temp13 = "";
//
	}

}
