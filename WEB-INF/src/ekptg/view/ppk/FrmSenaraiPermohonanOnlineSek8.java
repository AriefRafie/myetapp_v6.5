package ekptg.view.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.UtilHTML;
import ekptg.model.ppk.PPKUtilData;
import ekptg.model.ppk.PPKUtilHTML;
import lebah.portal.db.PrepareUser;
import lebah.portal.element.User;

public class FrmSenaraiPermohonanOnlineSek8 extends AjaxBasedModule{
	Vector<Hashtable<String,String>> negeriLogin = null;
	Vector<Hashtable<String,String>> unitLogin = null;
	static Logger myLog = Logger.getLogger(ekptg.view.ppk.FrmLaporanPrestasiPegawai.class);
		Vector list = null;
	public String doTemplate2() throws Exception {
		String vm = "app/ppk/frmSenaraiPermohonanOnlineSek8.jsp";
		
		String submit = getParam("command");
		myLog.info("submit = "+submit);
		String action = getParam("action");
		Vector listPermohonan = new Vector();
		HttpSession session = this.request.getSession();
		listPermohonan.clear();
		String socDaerah = "";
		String userId = (String)session.getAttribute("_portal_login");
		User user = null;
		user = PrepareUser.getUserById(userId);
		//HttpSession session = this.request.getSession();
		myLog.info("socNegeri = "+getParam("socNegeri"));
		String nofailoperasi = getParam("nofailoperasi");
		myLog.info("nofailoperasi = "+getParam("nofailoperasi"));
		String seksyen = getParam("seksyen");
		String idNegeri = getParam("socNegeri").equals("")
		
		
				?String.valueOf(session.getAttribute("_ekptg_user_negeri")):getParam("socNegeri");
if (idNegeri == null || idNegeri.trim().length() == 0){
idNegeri = "-1";
}
		
		String negeri = getParam("socNegeri");
		String txdMula = getParam("txdMula");
		String txdAkhir = getParam("txdAkhir");
		if (negeri == null || negeri.trim().length() == 0){
			negeri = "0";
		}
		this.context.put("idNegeri", negeri);
		
		String unit = getParam("socUnit");
		if (unit == null || unit.trim().length() == 0){
			unit = "0";
		}
	
		String idUnit = getParam("socUnit").equals("")
				?String.valueOf(session.getAttribute("_ekptg_user_unit")):getParam("socUnit");
if (idUnit == null || idUnit.trim().length() == 0){
idUnit = "0";
}
	
		String daerah = getParam("socDaerah");
		if (daerah == null || daerah.trim().length() == 0){
			daerah = "0";
		}
		
		String reportRole = String.valueOf(session.getAttribute("myrole"));
		String selectNegeri = "";
		String selectUnit = "";
		
		if(reportRole.equals("adminppk") || reportRole.equals("meps") || reportRole.equals("(MEPS)PPK")){
			String socNegeri = UtilHTML.selectNegeriLaporan("socNegeri",Long.parseLong(idNegeri),""," onChange=\"doChangeNegeri()\"", null);
			selectNegeri = getHTMLNegeri(false,socNegeri);			
			String socUnit = PPKUtilHTML.SelectUnitPPKLaporan(
					"socUnit",Utils.parseLong(idUnit),"","style=width:400 onChange=\"doChangeUnit();\"",idNegeri);
			selectUnit = getHTMLUnit(false,"",socUnit);
			
		}else if(reportRole.equals("usernegeri_ppk") || reportRole.equals("user_ppk") ){
			selectNegeri = getHTMLNegeri(true,idNegeri);
			String socUnit = PPKUtilHTML.SelectUnitPPKLaporan(
							"socUnit",Utils.parseLong(idUnit),"","style=width:400 onChange=\"doChangeUnit();\"",idNegeri);
			selectUnit = getHTMLUnit(false,"",socUnit);

		}
		//else if(reportRole.equals("user_ppk")){
		//	selectNegeri = getHTMLNegeri(true,idNegeri);
		//	String socUnit = PPKUtilData.getNamaUnitPPK(idUnit);
		//	selectUnit = getHTMLUnit(true,idUnit,socUnit);
		//}
		
		
	//	context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri", null, "", " onChange=\"doChangeNegeri();\""));
	//	context.put("selectUnit",HTML.SelectTempatBicaraByNegeri(negeri,"socUnit",null,"style=width:340"," onChange=\"doChangeUnit()\""));
	//	context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", null, "", "",unit));
	
		context.put("selectNegeri",selectNegeri);
	    context.put("selectUnit",selectUnit);
	    context.put("reportRole",reportRole);
	    context.put("txdMula",txdMula);
	    context.put("txdAkhir",txdAkhir);
	    context.put("nofailoperasi", nofailoperasi);
	    context.put("listseksyen", seksyen);
		/*if("doChangeNegeri".equals(submit)){
			context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri", Long.parseLong(negeri), "", " onChange=\"doChangeNegeri();\""));
			context.put("selectUnit",HTML.SelectTempatBicaraByNegeri(negeri,"socUnit",Long.parseLong(unit),"style=width:340"," onChange=\"doChangeUnit()\""));
			context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", Long.parseLong(daerah), "", "",unit));
		}
		else if ("doChangeUnit".equals(submit)){
			
			context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri", Long.parseLong(negeri), "", " onChange=\"doChangeNegeri();\""));
			context.put("selectUnit",HTML.SelectTempatBicaraByNegeri(negeri,"socUnit",Long.parseLong(unit),"style=width:340"," onChange=\"doChangeUnit()\""));
			context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", Long.parseLong(daerah), "", "",unit));
		}*/
		
		//context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri", Long.parseLong(negeri), "", " onChange=\"doChangeNegeri();\""));
		//context.put("selectUnit",HTML.SelectTempatBicaraByNegeri(negeri,"socUnit",Long.parseLong(unit),"style=width:340"," onChange=\"doChangeUnit()\""));
		
	    //context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", Long.parseLong(daerah), "", "",unit));
	    socDaerah =  UtilHTML.SelectDaerahByUnitPPKXKod("socDaerah", null, "style=width:340", "", user.getId_pejabatjkptg());
	    context.put("selectDaerah",socDaerah);
		
		if (!"".equals(getParam("socNegeri")));
			negeri = getParam("socNegeri");
			
		if ("".equals(getParam("socNegeri")));
			negeri = String.valueOf(session.getAttribute("_ekptg_user_negeri"));

		if (!"".equals(getParam("socUnit")));
			unit = getParam("socUnit");
		
		if(!"".equals(getParam("socDaerah")))
			daerah = getParam("socDaerah");
		
		if(!"".equals(getParam("txdMula")))
			txdMula = getParam("txdMula");
		
		if(!"".equals(getParam("txdAkhir")))
			txdAkhir = getParam("txdAkhir");
		
		if("1".equals(nofailoperasi))
		{
			myLog.info("MESTI ADA NO FAIL OPERASI");
			if((!"".equals(txdMula)) && (!"".equals(txdAkhir)))
			{
				setCarianFailTarikhMohon(negeri,daerah,txdMula,txdAkhir,seksyen);
				myLog.info("MESTI ADA NO FAIL OPERASI (ADA TARIKH)");
				
			}
			else
			{
				setCarianFail(negeri,daerah,seksyen);
				myLog.info("MESTI ADA NO FAIL OPERASI (TIADA TARIKH)");
			}
		}
		
		if(!"1".equals(nofailoperasi))
		{
			myLog.info("TIADA NO FAIL OPERASI");
			if((!"".equals(txdMula)) && (!"".equals(txdAkhir)))
			{
				setCarianFailTarikhMohonTiadaFailOperasi(negeri,daerah,txdMula,txdAkhir,seksyen);
				myLog.info("TIADA NO FAIL OPERASI (ADA TARIKH)");
			}
			else
			{
				setCarianFailTiadaFailOperasi(negeri,daerah,seksyen);
				myLog.info("TIADA NO FAIL OPERASI (TIADA TARIKH)");
			}
		}
		
		listPermohonan = getList();
		

		
		 context.put("SenaraiPermohonan", listPermohonan);
		 context.put("list_size", listPermohonan.size());
		 setupPage(session,action,listPermohonan);
			
		
		return vm;
		
	}
	public void setupPage(HttpSession session,String action,Vector listPermohonan) {
		try {

			this.context.put("totalRecords", listPermohonan.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null
					|| this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10
						: getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage");
			}

			if ("getNext".equals(action)) {
				page++;
			} else if ("getPrevious".equals(action)) {
				page--;
			} else if ("getPage".equals(action)) {
				page = getParamAsInteger("value");
			} else if ("doChangeItemPerPage".equals(action)) {
				itemsPerPage = getParamAsInteger("itemsPerPage");
			}

			Paging paging = new Paging(session, listPermohonan, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1; // reset page number
			this.context.put("SenaraiPermohonan", paging.getPage(page));
			this.context.put("page", new Integer(page));
			this.context.put("itemsPerPage", new Integer(itemsPerPage));
			this.context.put("totalPages", new Integer(paging.getTotalPages()));
			this.context.put("startNumber", new Integer(paging.getTopNumber()));
			this.context.put("isFirstPage", new Boolean(paging.isFirstPage()));
			this.context.put("isLastPage", new Boolean(paging.isLastPage()));

		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error", e.getMessage());
		}
	}
	
	
	
public void  setCarianFailTarikhMohonTiadaFailOperasi(String negeri,String daerah,String tarikhmula, String tarikhakhir, String seksyen)throws Exception{
		
		Db db = null;
	    String sql = "";
	    myLog.info("tarikhmula = "+tarikhmula);
	    myLog.info("tarikhakhir = "+tarikhakhir);
	    try {
		      list = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		      
		      sql= "SELECT A.NO_PERMOHONAN_ONLINE,B.NAMA_SIMATI,A.TARIKH_MOHON,C.NO_FAIL"+

				   " FROM TBLPPKPERMOHONAN A,"+
				   " TBLPPKSIMATI B,"+
				   " TBLPFDFAIL C,"+
				   " TBLPPKPERMOHONANSIMATI D"+
					
				   " WHERE A.ID_PERMOHONAN = D.ID_PERMOHONAN"+
				   " AND B.ID_SIMATI = D.ID_SIMATI"+
				   " AND A.ID_FAIL = C.ID_FAIL"+
				   " AND A.NO_PERMOHONAN_ONLINE IS NOT NULL";
		      
		      if ("8".equals(seksyen))
			      
		      {
			       sql = sql + " AND A.SEKSYEN = '"+seksyen+"'";
		      }
		      
		      if ("17".equals(seksyen))
		      
		      {
			       sql = sql + " AND A.SEKSYEN = '"+seksyen+"'";
		      }
				   sql = sql + " AND A.TARIKH_MOHON BETWEEN TO_DATE('"+tarikhmula+"','dd/mm/yyyy')"+
				   " AND TO_DATE('"+tarikhakhir+"','dd/mm/yyyy')";
				   
		      
		      if(negeri != null && negeri != "0"){
		    	  if (!negeri.trim().equals("")){
		    		  sql = sql + " AND A.ID_NEGERIMHN = '" + negeri + "'";  
		    	  }
		    	 
		      }
		      if(daerah != null && daerah != "0"){
		    	  if (!daerah.trim().equals("")){
		    		  sql = sql + " AND A.ID_DAERAHMHN = '" + daerah + "'";  
		    	  }
		    	 
		      }
		      
		      sql = sql + " ORDER BY A.ID_PERMOHONAN";
		      myLog.info("sql setCarianFailTarikhMohon = "+sql);
		      ResultSet rs = stmt.executeQuery(sql); 
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("NO_PERMOHONAN_ONLINE", rs.getString("NO_PERMOHONAN_ONLINE"));
		    	  h.put("NAMA_SIMATI", rs.getString("NAMA_SIMATI"));
		    	  h.put("TARIKH_MOHON", rs.getString("TARIKH_MOHON")!=null?sdf.format(rs.getDate("TARIKH_MOHON")):"");
		    	  h.put("NO_FAIL", rs.getString("NO_FAIL")!=null?rs.getString("NO_FAIL"):"-");
		    	  list.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      
		      if(count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "");
		    	  h.put("NO_PERMOHONAN_ONLINE","Tiada rekod.");
		    	  h.put("NAMA_SIMATI", "");
		    	  h.put("TARIKH_MOHON", "");
		    	  h.put("NO_FAIL", "");
		    	  list.addElement(h);
		      }
		      
	    } finally {
		      if (db != null) db.close();
		    }
		      
		
	}

public void  setCarianFailTarikhMohon(String negeri,String daerah,String tarikhmula, String tarikhakhir, String seksyen)throws Exception{
	
	Db db = null;
    String sql = "";
    myLog.info("tarikhmula = "+tarikhmula);
    myLog.info("tarikhakhir = "+tarikhakhir);
    try {
	      list = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	      
	      sql= "SELECT A.NO_PERMOHONAN_ONLINE,B.NAMA_SIMATI,A.TARIKH_MOHON,C.NO_FAIL"+

			   " FROM TBLPPKPERMOHONAN A,"+
			   " TBLPPKSIMATI B,"+
			   " TBLPFDFAIL C,"+
			   " TBLPPKPERMOHONANSIMATI D"+
				
			   " WHERE A.ID_PERMOHONAN = D.ID_PERMOHONAN"+
			   " AND B.ID_SIMATI = D.ID_SIMATI"+
			   " AND A.ID_FAIL = C.ID_FAIL"+
			   " AND A.NO_PERMOHONAN_ONLINE IS NOT NULL"+
			   " AND C.NO_FAIL IS NOT NULL";
	      if ("8".equals(seksyen))
		      
	      {
		       sql = sql + " AND A.SEKSYEN = '"+seksyen+"'";
	      }
	      
	      if ("17".equals(seksyen))
	      
	      {
		       sql = sql + " AND A.SEKSYEN = '"+seksyen+"'";
	      }
		       
			   sql = sql + " AND A.TARIKH_MOHON BETWEEN TO_DATE('"+tarikhmula+"','dd/mm/yyyy')"+
			   " AND TO_DATE('"+tarikhakhir+"','dd/mm/yyyy')";
			   
	      
	      if(negeri != null && negeri != "0"){
	    	  if (!negeri.trim().equals("")){
	    		  sql = sql + " AND A.ID_NEGERIMHN = '" + negeri + "'";  
	    	  }
	    	 
	      }
	      if(daerah != null && daerah != "0"){
	    	  if (!daerah.trim().equals("")){
	    		  sql = sql + " AND A.ID_DAERAHMHN = '" + daerah + "'";  
	    	  }
	    	 
	      }
	      
	      sql = sql + " ORDER BY A.ID_PERMOHONAN";
	      myLog.info("sql setCarianFailTarikhMohon = "+sql);
	      ResultSet rs = stmt.executeQuery(sql); 
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("NO_PERMOHONAN_ONLINE", rs.getString("NO_PERMOHONAN_ONLINE"));
	    	  h.put("NAMA_SIMATI", rs.getString("NAMA_SIMATI"));
	    	  h.put("TARIKH_MOHON", rs.getString("TARIKH_MOHON")!=null?sdf.format(rs.getDate("TARIKH_MOHON")):"");
	    	  h.put("NO_FAIL", rs.getString("NO_FAIL")!=null?rs.getString("NO_FAIL"):"-");
	    	  list.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	      
	      if(count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("NO_PERMOHONAN_ONLINE","Tiada rekod.");
	    	  h.put("NAMA_SIMATI", "");
	    	  h.put("TARIKH_MOHON", "");
	    	  h.put("NO_FAIL", "");
	    	  list.addElement(h);
	      }
	      
    } finally {
	      if (db != null) db.close();
	    }
	      
	
}
	
	public void  setCarianFailTiadaFailOperasi(String negeri,String daerah, String seksyen)throws Exception{
		
		Db db = null;
	    String sql = "";
	    
	    try {
		      list = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		      
		      sql= "SELECT A.NO_PERMOHONAN_ONLINE,B.NAMA_SIMATI,A.TARIKH_MOHON,C.NO_FAIL"+

				   " FROM TBLPPKPERMOHONAN A,"+
				   " TBLPPKSIMATI B,"+
				   " TBLPFDFAIL C,"+
				   " TBLPPKPERMOHONANSIMATI D"+
					
				   " WHERE A.ID_PERMOHONAN = D.ID_PERMOHONAN"+
				   " AND B.ID_SIMATI = D.ID_SIMATI"+
				   " AND A.ID_FAIL = C.ID_FAIL"+
				   " AND A.NO_PERMOHONAN_ONLINE IS NOT NULL";
		      
		      if ("8".equals(seksyen))
		      
		      {
			       sql = sql + " AND A.SEKSYEN = '"+seksyen+"'";
		      }
		      
		      if ("17".equals(seksyen))
		      
		      {
			       sql = sql + " AND A.SEKSYEN = '"+seksyen+"'";
		      }
			       
				   
		      
		      if(negeri != null && negeri != "0"){
		    	  if (!negeri.trim().equals("")){
		    		  sql = sql + " AND A.ID_NEGERIMHN = '" + negeri + "'";  
		    	  }
		    	 
		      }
		      if(daerah != null && daerah != "0"){
		    	  if (!daerah.trim().equals("")){
		    		  sql = sql + " AND A.ID_DAERAHMHN = '" + daerah + "'";  
		    	  }
		    	 
		      }
		      
		      sql = sql + " ORDER BY A.ID_PERMOHONAN";
		      myLog.info("sqlsetCarianFail = "+sql);
		      ResultSet rs = stmt.executeQuery(sql); 
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("NO_PERMOHONAN_ONLINE", rs.getString("NO_PERMOHONAN_ONLINE"));
		    	  h.put("NAMA_SIMATI", rs.getString("NAMA_SIMATI"));
		    	  h.put("TARIKH_MOHON", rs.getString("TARIKH_MOHON")!=null?sdf.format(rs.getDate("TARIKH_MOHON")):"");
		    	  h.put("NO_FAIL", rs.getString("NO_FAIL")!=null?rs.getString("NO_FAIL"):"-");
		    	  list.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      
		      if(count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "");
		    	  h.put("NO_PERMOHONAN_ONLINE","Tiada rekod.");
		    	  h.put("NAMA_SIMATI", "");
		    	  h.put("TARIKH_MOHON", "");
		    	  h.put("NO_FAIL", "");
		    	  list.addElement(h);
		      }
		      
	    } finally {
		      if (db != null) db.close();
		    }
		      
		
	}
	
public void  setCarianFail(String negeri,String daerah, String seksyen)throws Exception{
		
		Db db = null;
	    String sql = "";
	    myLog.info("daerah = "+daerah);
	    try {
		      list = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		      
		      sql= "SELECT A.NO_PERMOHONAN_ONLINE,B.NAMA_SIMATI,A.TARIKH_MOHON,C.NO_FAIL"+

				   " FROM TBLPPKPERMOHONAN A,"+
				   " TBLPPKSIMATI B,"+
				   " TBLPFDFAIL C,"+
				   " TBLPPKPERMOHONANSIMATI D"+
					
				   " WHERE A.ID_PERMOHONAN = D.ID_PERMOHONAN"+
				   " AND B.ID_SIMATI = D.ID_SIMATI"+
				   " AND A.ID_FAIL = C.ID_FAIL"+
				   " AND A.NO_PERMOHONAN_ONLINE IS NOT NULL"+
				   " AND C.NO_FAIL IS NOT NULL";
		      
		      if ("8".equals(seksyen))
			      
		      {
			       sql = sql + " AND A.SEKSYEN = '"+seksyen+"'";
		      }
		      
		      if ("17".equals(seksyen))
		      
		      {
			       sql = sql + " AND A.SEKSYEN = '"+seksyen+"'";
		      }
			       
				   
		      
		      if(negeri != null && negeri != "0"){
		    	  if (!negeri.trim().equals("")){
		    		  sql = sql + " AND A.ID_NEGERIMHN = '" + negeri + "'";  
		    	  }
		    	 
		      }
		      if(daerah != null && daerah != "0"){
		    	  myLog.info("READ HERE");
		    	  if (!daerah.trim().equals("")){
		    		  sql = sql + " AND A.ID_DAERAHMHN = '" + daerah + "'";  
		    	  }
		    	 
		      }
		      
		      sql = sql + " ORDER BY A.ID_PERMOHONAN";
		      myLog.info("sqlsetCarianFail = "+sql);
		      ResultSet rs = stmt.executeQuery(sql); 
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("NO_PERMOHONAN_ONLINE", rs.getString("NO_PERMOHONAN_ONLINE"));
		    	  h.put("NAMA_SIMATI", rs.getString("NAMA_SIMATI"));
		    	  h.put("TARIKH_MOHON", rs.getString("TARIKH_MOHON")!=null?sdf.format(rs.getDate("TARIKH_MOHON")):"");
		    	  h.put("NO_FAIL", rs.getString("NO_FAIL")!=null?rs.getString("NO_FAIL"):"-");
		    	  list.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      
		      if(count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "");
		    	  h.put("NO_PERMOHONAN_ONLINE","Tiada rekod.");
		    	  h.put("NAMA_SIMATI", "");
		    	  h.put("TARIKH_MOHON", "");
		    	  h.put("NO_FAIL", "");
		    	  list.addElement(h);
		      }
		      
	    } finally {
		      if (db != null) db.close();
		    }
		      
		
	}

	public Vector getList(){
		return list;
	}
	
	private String getHTMLNegeri(boolean dsable,String negeri){
        String htmlNegeri = "<tr> ";
        htmlNegeri += "<td width=\"30%\" align=\"right\"><span class=\"style1\">Negeri</span></td>";
        htmlNegeri += "<td width=\"70%\">: "+negeri+"</td>";
        htmlNegeri += "</tr>";
        if(dsable)
            htmlNegeri = "<input type=\"hidden\" name=\"socNegeri\" value=\""+negeri+"\">";

        	
        return htmlNegeri;
      
	}
	private String getHTMLUnit(boolean dsable,String idUnit,String unit){
		 String htmlUnit = "<tr>";
		 htmlUnit += "<td width=\"30%\" align=\"right\"><span class=\"style1\">Unit</span></td>";
		 htmlUnit += "<td width=\"70%\">: "+unit+"</td>";
		 htmlUnit += "</tr>";
		 if(dsable)
			 htmlUnit += "<input type=\"hidden\" name=\"socUnit\" value=\""+idUnit+"\">";

		 return htmlUnit;
		 
	}	

}
