package ekptg.view.admin.utils;

import java.sql.ResultSet;
import java.sql.Statement;

import lebah.db.Db;

/**
 * 
 * @author Azam
 * @modified by Mohd Nazrul
 * @version 1.1 
 *
 */

public class FilterDropDown {

	 public String SelectPejabatJKPTG(String selectName) throws Exception{
		  return SelectPejabatJKPTG(selectName,null,null,null,null,null,null);
	 }
	 
	 public String SelectPejabatJKPTG(String selectName,String idNegeri,String idDaerah,String idSeksyen,String selectedValue) 
	  throws Exception{
		  return SelectPejabatJKPTG(selectName,idNegeri,idDaerah,idSeksyen,selectedValue,null,null);
	 }
	  
	 public String SelectPejabatJKPTG(String selectName,String idNegeri,String idDaerah,String idSeksyen,
				String selectedValue, String disability,String jsFunction) 
				throws Exception {
		  
			if (idNegeri == null) idNegeri = ""; 
			//System.out.println("idSeksyen:"+idSeksyen);
			StringBuffer sb = new StringBuffer();
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>Sila Pilih Pejabat</option>\n");
			
			Db db = null;
			String selected="";
			try {
				db = new Db();
				String sql = "select A.ID_PEJABATJKPTG,A.KOD_JKPTG," +
						"A.NAMA_PEJABAT " +
						//"|| ' ' || A.ALAMAT1 
						"AS NAMA_PEJABAT from tblRujPejabatJKPTG A " ;
				sql = sql + " WHERE id_negeri='"+idNegeri+"'";
				
//				if (idDaerah != null && !"".equals(idDaerah) && !"0".equals(idDaerah)) {
//					sql = sql + " AND id_daerah='"+idDaerah+"'";
//				}
				
				if (idSeksyen != null && !"".equals(idSeksyen) && !"0".equals(idSeksyen)) {
					sql = sql + " AND id_seksyen='"+idSeksyen+"'";
				}
				sql = sql + " ORDER BY A.KOD_JKPTG";
				Statement stmt = db.getStatement();
				//myLogger.info(sql);
				ResultSet rs = stmt.executeQuery(sql);
				String id="";
				String nama = "";
				String kod="";
				while (rs.next()) {
					id = rs.getString("ID_PEJABATJKPTG");
					kod = rs.getString("KOD_JKPTG");
					nama = rs.getString("NAMA_PEJABAT");
					if (id.equals(selectedValue)) selected = "selected";
					else selected = "";
					sb.append("<option " + selected + " value=" + id + ">"
							+ kod + " - " + nama + "</option>\n");
				}
				sb.append("</select>");
			} catch (Exception ex) {
				ex.printStackTrace();
				throw ex;
			}
			return sb.toString();

	}
	  
	 public String getDaerahJagaan(String id_pejabatjkptg) throws Exception {
		  Db db = null;
		  String sql = "";
		  String output="";
		  try {
			  db = new Db();
			  Statement stmt = db.getStatement();
			  sql = "Select nama_daerah from tblRujDaerah where "+
			  "id_daerah in (select distinct id_daerahurus " +
			  "from TBLRUJPEJABATURUSAN where id_pejabatjkptg='"+id_pejabatjkptg+"') "+
			  "order by kod_daerah";
			  ResultSet rs = stmt.executeQuery(sql);
			  while (rs.next()) {
				  output = output + rs.getString("nama_daerah") + ",";
			  }
			  if (output.length() > 0) output=output.substring(0,output.length()-1);
		  } catch (Exception e) {
				e.printStackTrace();
				throw e;
		  }
		  return output;
	 }
}
