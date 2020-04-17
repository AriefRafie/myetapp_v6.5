package ekptg.view.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;

public class FrmSenaraiPermohonanOnlineSek8 extends AjaxBasedModule{

	Vector list = null;
	public String doTemplate2() throws Exception {
		String vm = "app/ppk/frmSenaraiPermohonanOnlineSek8.jsp";
		
		String submit = getParam("command");
		String action = getParam("action");
		Vector listPermohonan = null;
		HttpSession session = this.request.getSession();
		
		String negeri = getParam("socNegeri");
		if (negeri == null || negeri.trim().length() == 0){
			negeri = "0";
		}
		this.context.put("idNegeri", negeri);
		
		String unit = getParam("socUnit");
		if (unit == null || unit.trim().length() == 0){
			unit = "0";
		}
		
		String daerah = getParam("socDaerah");
		if (daerah == null || daerah.trim().length() == 0){
			daerah = "0";
		}
		
		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null, "", " onChange=\"doChangeNegeri()\""));
		context.put("selectUnit",HTML.SelectTempatBicaraByNegeri(negeri,"socUnit",null,"style=width:340"," onChange=\"doChangeUnit()\""));
		context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", null, "", "",unit));
		
		if("doChangeNegeri".equals(submit)){
			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(negeri), "", " onChange=\"doChangeNegeri()\""));
			context.put("selectUnit",HTML.SelectTempatBicaraByNegeri(negeri,"socUnit",Long.parseLong(unit),"style=width:340"," onChange=\"doChangeUnit()\""));
			context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", Long.parseLong(daerah), "", "",unit));
		}
		else if("doChangeUnit".equals(submit)){
			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(negeri), "", " onChange=\"doChangeNegeri()\""));
			context.put("selectUnit",HTML.SelectTempatBicaraByNegeri(negeri,"socUnit",Long.parseLong(unit),"style=width:340"," onChange=\"doChangeUnit()\""));
			context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", Long.parseLong(daerah), "", "",unit));
		}
		
		if (!"".equals(getParam("socNegeri")));
			negeri = getParam("socNegeri");

		if (!"".equals(getParam("socUnit")));
			unit = getParam("socUnit");
		
		if(!"".equals(getParam("socDaerah")))
			daerah = getParam("socDaerah");
		
		setCarianFail(negeri,daerah);
		listPermohonan = getList();
		

		
		 context.put("SenaraiPermohonan", listPermohonan);
		 context.put("list_size", listPermohonan.size());
		 setupPage(session,action,listPermohonan);
			
		
		return vm;
		
	}
	public void setupPage(HttpSession session,String action,Vector list) {
		try {
		
		this.context.put("totalRecords",list.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
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
	    	
	    Paging paging = new Paging(session,list,itemsPerPage);
		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("SenaraiPermohonan",paging.getPage(page));
	    this.context.put("page", new Integer(page));
	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}
	public void  setCarianFail(String negeri,String daerah)throws Exception{
		
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
				   " AND A.NO_PERMOHONAN_ONLINE IS NOT NULL"+
			       " AND A.SEKSYEN = 8";
				   
		      
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
	
	

}
