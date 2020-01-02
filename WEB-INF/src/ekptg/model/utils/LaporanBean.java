package ekptg.model.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.EkptgCache;
import ekptg.helpers.Utils;
import ekptg.model.htp.UtilHTML;
import ekptg.model.ppk.PPKUtilData;
import ekptg.model.ppk.PPKUtilHTML;


public class LaporanBean extends EkptgCache implements ILaporan {
	
	private static Logger myLog = Logger.getLogger(ekptg.model.utils.LaporanBean.class);
	private Db db = null;
	private String sql ="";
	private Vector<Hashtable<String,String>> senarai = null;
	
	public void setReportRolePPK(org.apache.velocity.VelocityContext context 
		,String reportRole
		,String idNegeri,String idUnit) throws NumberFormatException, Exception{
		String selectNegeri = "";
		String selectUnit = "";
		String valLaporan = "";
		if(reportRole.equals("adminppk")||reportRole.equals("meps") ){
			String socNegeri = UtilHTML.selectNegeriLaporan("socNegeri",Long.parseLong(idNegeri),""," onChange=\"doChangeNegeri()\"", null);
			selectNegeri = getHTMLNegeri("30","70",false,socNegeri);			
			String socUnit = PPKUtilHTML.SelectUnitPPKLaporan(
					"socTempatBicara",Utils.parseLong(idUnit),"","style=width:400 onChange=\"doChangeUnit();\"",idNegeri);
			selectUnit = getHTMLUnit("30","70",false,"",socUnit);
			valLaporan = "2";
		
		}else if(reportRole.equals("usernegeri_ppk")){
			selectNegeri = getHTMLNegeri("30","70",true,idNegeri);
			String socUnit = PPKUtilHTML.SelectUnitPPKLaporan(
							"socTempatBicara",Utils.parseLong(idUnit),"","style=width:400 ",idNegeri);
			selectUnit = getHTMLUnit("30","70",false,"",socUnit);
			valLaporan = "1";
	
		}else if(reportRole.equals("user_ppk")){
			selectNegeri = getHTMLNegeri("30","70",true,idNegeri);
			String socUnit = PPKUtilData.getNamaUnitPPK(idUnit);
			selectUnit = getHTMLUnit("30","70",true,idUnit,socUnit);
			valLaporan = "3";
		}
		//this.context.put("selectTahun",HTML.SelectTahun("socTahun", tahun, "", " style=\"width=80px\""));
		context.put("selectNegeri",selectNegeri);
	    context.put("selectUnit",selectUnit);
	    context.put("reportRole",reportRole);
		context.put("valLaporan", valLaporan);

	}

	@SuppressWarnings("unchecked")
	public Vector<Hashtable<String, String>> getLaporanMengikutSeksyen
		(String seksyen, String level, String jlaporan) throws Exception {
		Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      SQLRenderer r = new SQLRenderer();
	      r.add("rjd.keterangan");
	      r.add("rdu.module_id");
	      r.add("rdu.peringkat");
	      r.add("rdu.template");
	      r.add("rdu.id_suburusan");
		  r.add("rjd.ID_JENISDOKUMEN",r.unquote("rdu.ID_JENISDOKUMEN"));
		  if(!seksyen.equals("")){
			  r.add("RJD.ID_SEKSYEN",r.unquote(seksyen));
		  }
		  r.add("rdu.DOKUMEN",r.unquote("'"+jlaporan+"'"));
		  String sqlAdd = "";
		  if(level!=null){
			  if(level.equals("unit")){
				  sqlAdd = " AND ( SUBSTR(rdu.peringkat,0,4)='"+level+"' OR SUBSTR(rdu.peringkat,0,6)='daerah' )";
			  }else if(level.equals("daerah"))
				  r.add("SUBSTR(rdu.peringkat,0,6)",level);
			  else if(level.equals("negeri"))
				  r.add("SUBSTR(rdu.peringkat,0,6)",level);

		  }		           
	      sql = r.getSQLSelect("tblrujdokumenurl rdu,tblrujjenisdokumen rjd");
	      sql += sqlAdd + " ORDER BY RJD.KETERANGAN desc";
	      myLog.info("sqlSenaraiRekod-->"+sql);
	      Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>(); 
	      Statement stmt = db.getStatement();
	      //mylog.info("getLaporanMengikutSeksyen("+seksyen+","+level+"):sql::"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable<String, String> h;
	      while (rs.next()) {
	    	  h = new Hashtable<String, String>();
	    	  h.put("idmodule",rs.getString("module_id")); 
	    	  h.put("keterangan",rs.getString("keterangan"));
	    	  h.put("peringkat",rs.getString("peringkat"));
	    	  h.put("template",rs.getString("template"));
	    	  h.put("idsuburusan",rs.getString("id_suburusan"));
	    	  v.addElement(h);
	      }
	      return v;
	      
	    } finally {
	      if (db != null) db.close();
	    }
	
	}
	private String getHTMLNegeri(String td,String td2,boolean dsable,String negeri){
        String htmlNegeri = "<tr> ";
        String perc = "";
        String perc2 = "";
        if(!td.equals(""))
        	perc = "width=\"30%\" ";
        if(!td2.equals(""))
        	perc2 = "width=\"69%\" ";

        htmlNegeri += "<td "+perc+" align=\"right\"><span class=\"style1\">Negeri</span></td>";
        htmlNegeri += "<td width=\"1%\">:&nbsp;</td>";
        htmlNegeri += "<td "+perc2+" >"+negeri+"</td>";
        htmlNegeri += "</tr>";
        if(dsable)
            htmlNegeri = "<input type=\"hidden\" name=\"socNegeri\" value=\""+negeri+"\">";

        	
        return htmlNegeri;
      
	}
	private String getHTMLUnit(String td,String td2,boolean dsable,String idUnit,String unit){
		 String htmlUnit = "<tr>";
	       String perc = "";
	        String perc2 = "";
	        if(!td.equals(""))
	        	perc = "width=\"30%\" ";
	        if(!td2.equals(""))
	        	perc2 = "width=\"69%\" ";

		 htmlUnit += "<td "+perc+" align=\"right\"><span class=\"style1\">Unit</span></td>";
		 htmlUnit += "<td width=\"1%\">:&nbsp;</td>";
		 htmlUnit += "<td  "+perc2+" >"+unit+"</td>";
		 htmlUnit += "</tr>";
		 if(dsable)
			 htmlUnit += "<input type=\"hidden\" name=\"socTempatBicara\" value=\""+idUnit+"\">";

		 return htmlUnit;
		 
	}

	
}
