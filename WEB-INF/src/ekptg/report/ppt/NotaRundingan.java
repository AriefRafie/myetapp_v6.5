package ekptg.report.ppt;

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

public class NotaRundingan  extends EkptgReportServlet{
	
	Vector namaPB = null;
	Vector getPB = null;
	Vector notisAwam = null;
	Vector getNotisAwam = null;
	
	public NotaRundingan() {
		super.setReportName("NotaPerundinganPendudukanSementara");
		super.setFolderName("ppt");
	}
	
	public Vector getNamaPB (String idFail)throws Exception{
		Db db = null;
		String sql = "";
		
		try{
			namaPB = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT INITCAP(A.NAMA_PB) AS NAMA_PB"+

				  " FROM TBLPPTPIHAKBERKEPENTINGAN A,"+
				  " TBLPFDFAIL B,"+
				  " TBLPPTPERMOHONAN C,"+
				  " TBLPPTHAKMILIK D,"+
				  " TBLPPTHAKMILIKPB E,"+
				  " TBLRUJJENISPB F"+

				  " WHERE B.ID_FAIL = C.ID_FAIL"+
				  " AND D.ID_HAKMILIK = E.ID_HAKMILIK"+
				  " AND A.ID_PIHAKBERKEPENTINGAN = E.ID_PIHAKBERKEPENTINGAN"+
				  " AND C.ID_PERMOHONAN = D.ID_PERMOHONAN"+
				  " AND F.ID_JENISPB = A.ID_JENISPB"+
				  " AND F.KOD_JENIS_PB NOT IN ('WJ','PG','PS')"+
				  " AND B.ID_FAIL ='" +idFail+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("NAMA_PB",rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
				namaPB.addElement(h);
				
			}
				  		
			
			return namaPB;
		}finally {
			if (db != null)
				db.close();
		}
		
	}
	public Vector getNotisAwam(String idFail)throws Exception{
		Db db = null;
		String sql = "";
		
		try{
			notisAwam = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT INITCAP(A.TEMPAT) AS TEMPAT, TO_CHAR(A.TARIKH_TAMPAL,'dd.MM.yyyy') AS TARIKH_TAMPAL, A.JENIS_TEMPAT_TAMPAL"+

				  " FROM TBLPPTNOTISAWAM A,"+
				  " TBLPPTPERMOHONAN B,"+
				  " TBLPFDFAIL C"+

				  " WHERE C.ID_FAIL = B.ID_FAIL"+
				  " AND B.ID_PERMOHONAN = A.ID_PERMOHONAN"+
				  " AND C.ID_FAIL = '"+idFail+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("TEMPAT",rs.getString("TEMPAT")==null?"":rs.getString("TEMPAT"));
				h.put("TARIKH_TAMPAL",rs.getString("TARIKH_TAMPAL")==null?"":rs.getString("TARIKH_TAMPAL"));
				h.put("JENIS_TEMPAT_TAMPAL", rs.getString("JENIS_TEMPAT_TAMPAL")==null?"":rs.getString("JENIS_TEMPAT_TAMPAL"));
				notisAwam.addElement(h);
			}
			
			return notisAwam;
		}finally {
			if (db != null)
				db.close();
		}
	}
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String idfail = "";
		if (parameters.get("idfail") != null){
			idfail = parameters.get("idfail").toString();
		}
		
		getPB = new Vector();
		getPB = getNamaPB(idfail);
		
		String tempPB = "";
		
		for(int i = 0; i < getPB.size(); i++){
			Hashtable hPB = (Hashtable)getPB.get(i);
			
			if(tempPB == ""){
				tempPB = hPB.get("NAMA_PB").toString(); 
			}
			else{
				tempPB = tempPB + "\n" + hPB.get("NAMA_PB"); 
			}
			
			
		}
		
		parameters.put("namaPB",tempPB);
		
		getNotisAwam = new Vector();
		getNotisAwam = getNotisAwam(idfail);
		
		
		for(int h = 0; h < getNotisAwam.size(); h++){
			Hashtable hNA = (Hashtable)getNotisAwam.get(h);
			
			if(hNA.get("JENIS_TEMPAT_TAMPAL").equals("1")){
				parameters.put("PTG",hNA.get("TEMPAT"));
				parameters.put("TARIKH_PTG",hNA.get("TARIKH_TAMPAL"));
			
			}
			else if (hNA.get("JENIS_TEMPAT_TAMPAL").equals("2")){
				parameters.put("DALAM_MUKIM",hNA.get("TEMPAT"));
				parameters.put("TARIKH_DLM_MUKIM",hNA.get("TARIKH_TAMPAL"));
			}
			else if (hNA.get("JENIS_TEMPAT_TAMPAL").equals("3")){
				parameters.put("TARIKH_ATAS_TANAH",hNA.get("TARIKH_TAMPAL"));
			}
			
			
			
		}
	
		
	}

}
