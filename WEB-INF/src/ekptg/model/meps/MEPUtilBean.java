package ekptg.model.meps;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class MEPUtilBean implements Serializable,IMEPUtils {
	static Logger myLog = Logger.getLogger(ekptg.model.meps.MEPUtilBean.class);
		
	@Override
	public String SelectTahun(Vector tahun,String selectName,String selectedValue,String disability,String jsFunction) 
		throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector v = tahun;
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (selectedValue.equals(String.valueOf(h.get("id")))){
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("tahun") + "</option>\n");
			}
			sb.append("</select>");
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}		
		return sb.toString();
	
	}
	
	
	
	// GENERATE BAR/PIE CHART	
	public String generateXML(String sql, String namaLaporan){		
		//System.out.println(getSQL());
	String xml="<chart caption='"+namaLaporan+"' subcaption='' xAxisName='Tahun' yAxisName='Jumlah' numberPrefix='' showLegend='1'>";
	Db db = null;
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		myLog.info(sql);
		ResultSet rs =stat.executeQuery(sql);
			while(rs.next()){				
				xml =xml+"<set label='"+rs.getString("X")+"'  value='"+rs.getString("Y")+"' />";				
		}
			xml =xml+"</chart>";
		
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (db!=null) db.close();
		}
		return xml;
		
		//System.out.println(getSQL());
		//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
		//"useRoundEdges='1' legendBorderAlpha='0'>";
		


	}	
	
	public String generateXMLFailLine(String sql, String namaLaporan){		

		
		
		//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
		//"useRoundEdges='1' legendBorderAlpha='0'>";
		
		String xml="<chart palette='2'  xAxisName='Negeri' caption='"+namaLaporan+"' " +
					"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
					"lineThickness='1' anchorRadius='2' divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1'  " +
					"showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40'  " +
					"labelStep='1' numvdivlines='5' chartRightMargin='35' bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' " +
					"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
		     
		Db db = null;
		String xname = "";
		String categories = "";
		String dataset1="" ;
		String dataset2="" ;
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs =stat.executeQuery(sql);
			categories = "<categories>";
			dataset1 = "<dataset seriesName='Jumlah Permohonan' color='3813F0' showValues='1'>";
			dataset2 = "<dataset seriesName='Jumlah Hakmilik' color='BA1435' showValues='1'>";
			while(rs.next()){				
				//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
				//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
			
				categories = categories+"<category label='"+rs.getString("X")+"'/>";
				dataset1 = dataset1+"<set label='"+rs.getString("X")+"' value='"+rs.getString("Y1")+"'/>";
				dataset2 = dataset2+"<set value='"+rs.getString("Y2")+"'/>";
			}
			categories = categories+"</categories>";
			dataset1 = dataset1+"</dataset>";
			dataset2 = dataset2+"</dataset>";
			xml =xml+categories+dataset1+dataset2+"</chart>";
			
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (db!=null) db.close();
		}
	    
		System.out.println(xml);
		return xml;		
	

}			

	public String generateXMLFail(String sql, String namaLaporan){		
   System.out.println("masuk");
	
	String xml="<chart palette='2'  xAxisName='Negeri' caption='"+namaLaporan+"' " +
				"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
				"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
	Db db = null;
	String xname = "";
	String categories = "";
	String dataset1="" ;
	String dataset2="" ;
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs =stat.executeQuery(sql);
		categories = "<categories>";
		dataset1 = "<dataset seriesName='Jumlah Permohonan' color='3813F0' showValues='1'>";
		dataset2 = "<dataset seriesName='Jumlah Hakmilik' color='BA1435' showValues='1'>";
		while(rs.next()){				
			//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
			//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
		
			categories = categories+"<category label='"+rs.getString("X")+"'/>";
			dataset1 = dataset1+"<set label='"+Utils.NiceStateName(rs.getString("X"))+"' value='"+rs.getString("Y1")+"'/>";
			dataset2 = dataset2+"<set value='"+rs.getString("Y2")+"'/>";
		}
		categories = categories+"</categories>";
		dataset1 = dataset1+"</dataset>";
		dataset2 = dataset2+"</dataset>";
		xml =xml+categories+dataset1+dataset2+"</chart>";
		
	}catch(Exception e){
		e.printStackTrace();
	} finally {
		if (db!=null) db.close();
	}
    
	System.out.println(xml);
	return xml;

}			

}	// CLOSE MAIN CLASS
