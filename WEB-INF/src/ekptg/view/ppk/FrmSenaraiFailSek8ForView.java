package ekptg.view.ppk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmSenaraiFailSek8ForViewData;
import ekptg.model.ppk.FrmTukaranStatus;

/**
 * @author Razman Md Zainal
 *
 */
public class FrmSenaraiFailSek8ForView extends AjaxBasedModule{

	private static final long serialVersionUID = 1L;
	
	FrmSenaraiFailSek8ForViewData logic = null;
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	static Logger myLogger = Logger.getLogger(FrmSenaraiFailSek8ForView.class);
	HttpSession session = null;
	String action = null;
	String role = null;

	@Override
	public String doTemplate2() throws Exception {
		/*logic = new FrmSenaraiFailSek8ForViewData();
		HttpSession session = this.request.getSession();		
		String vm = "";	
		Vector list = new Vector();
		list.clear();		
		vm = "app/ppk/frmSenaraiFailSek8ForView.jsp";	
		logic.carianFail(getParam("txtNoFail"), session);
		list = logic.getSenaraiFail();
		this.context.put("SenaraiFail", list);
	
		String idPerbicaraan = "";
		idPerbicaraan = logic.getIdPerbicaraan(getParam("txtNoFail"));
		this.context.put("id_perbicaraan", idPerbicaraan);
		
		this.context.put("txtNoFail", getParam("txtNoFail").trim());
		
		return vm;*/
		session = request.getSession();
		FrmTukaranStatus model = new FrmTukaranStatus();
		FrmSenaraiFailSek8ForViewData logic = new FrmSenaraiFailSek8ForViewData();
		String usid=(String)session.getAttribute("_ekptg_user_id");
		String vm = "app/ppk/frmSenaraiFailSek8ForView.jsp";
		role = (String)session.getAttribute("myrole");
		
		
		Hashtable user_name = (Hashtable) getUsername(usid);
		context.put("user_name",(String)user_name.get("USER_NAME"));
		
		context.put("usid",usid);context.put("role",role);
		
		//context.put("usid","817298371");context.put("role","userppk");
		
		//context.put("usid","3187781873");context.put("role",role);
		
		String submit = getParam("command");
		action = getParam("action");
    	myLogger.info("[submit] :: " +submit);
    	
    	context.put("ScrollX",getParam("ScrollX"));
    	context.put("ScrollY",getParam("ScrollY"));
    	
    	context.put("listSemak","");
		context.put("listSemak_size","");
		context.put("nofail","");
		context.put("id_permohonan","");
		context.put("id_fail","");
		context.put("id_status","");
		context.put("id_suburusanstatusfail","");
		context.put("keterangan_status", "");
		context.put("seksyen","");
		context.put("id_perintah","");
		context.put("id_keputusanpermohonan","");
		context.put("id_perbicaraan","");
		context.put("id_permohonan","");
		context.put("txtNoFailSub","");
		context.put("txtMyId","");
		context.put("list_sub",""); 
		context.put("list_fail","");  
		context.put("cari_sub","");    	
		context.put("view_list_fail","");
		context.put("id_fail_carian","");
		context.put("latest_idstatus","");
		context.put("flag_kebenaran_edit","");		
		context.put("user_id_kebenaran_edit","");
		context.put("id_permohonan_kebenaran","");
		context.put("nama_user_yg_beri_kebenaran","");
		context.put("catatan_kebenaran_edit","");
		context.put("txtNoFailSub_selected","");
		context.put("txtIcPemohon","");
		context.put("txtNamaSimati","");
		context.put("txtNamaPemohon","");
		
    	Vector list = new Vector();
    	
		if ("cariMainSub".equals(submit)){
			
    		String txtNoFailSub = getParam("txtNoFailSub"); 
    		String txtIcPemohon = getParam("txtIcPemohon");
    		String txtNamaSimati = getParam("txtNamaSimati"); 
    		String txtNamaPemohon = getParam("txtNamaPemohon");
    		context.put("txtNoFailSub", txtNoFailSub.trim());
    		context.put("txtIcPemohon",txtIcPemohon.trim());
    		context.put("txtNamaSimati",txtNamaSimati.trim());
    		context.put("txtNamaPemohon",txtNamaPemohon.trim());
    		context.put("list_fail",model.search_nofail(txtNoFailSub.trim(),usid,txtIcPemohon,txtNamaSimati,txtNamaPemohon));
    		context.put("view_list_fail","yes");    	  		
    
    	}
		else if ("paparSub".equals(submit)){
    		context.put("id_fail_carian",getParam("id_fail_carian"));
    		String txtNoFailSub = getParam("txtNoFailSub");
    		String txtIcPemohon = getParam("txtIcPemohon");
    		String txtNamaSimati = getParam("txtNamaSimati"); 
    		String txtNamaPemohon = getParam("txtNamaPemohon");
    		    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")!=null)
    		{    		 		
    		context.put("txtNoFailSub_selected", model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL"));
    		}
    		context.put("txtNoFailSub",model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL"));
    		context.put("list_fail",model.search_nofail(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")+"",usid,txtIcPemohon,txtNamaSimati,txtNamaPemohon));  
    		context.put("cari_sub","yes");    	
    		context.put("view_list_fail","yes");
    		
    		myLogger.info("getParam(id_fail_carian) : "+getParam("id_fail_carian"));
    		list = logic.carianFail_byID(getParam("id_fail_carian"), session);
    		this.context.put("SenaraiFail", list);
    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_STATUS")!=null)
    		{    		 		
    		context.put("latest_idstatus", model.getMainFail(getParam("id_fail_carian")).get("ID_STATUS")); 
    		} 
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN")!=null)
    		{    		 		
    		context.put("id_permohonan_kebenaran", model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN")); 
    		}
    		if(model.getMainFail(getParam("id_fail_carian")).get("FLAG_KEBENARAN_EDIT")!=null)
    		{    		 		
    		context.put("flag_kebenaran_edit", model.getMainFail(getParam("id_fail_carian")).get("FLAG_KEBENARAN_EDIT")); 
    		}    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("USER_ID_KEBENARAN_EDIT")!=null)
    		{    		 		
    		context.put("user_id_kebenaran_edit", model.getMainFail(getParam("id_fail_carian")).get("USER_ID_KEBENARAN_EDIT")); 
    		}    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("USER_NAME")!=null)
    		{    		 		
    		context.put("nama_user_yg_beri_kebenaran", model.getMainFail(getParam("id_fail_carian")).get("USER_NAME")); 
    		}
    		if(model.getMainFail(getParam("id_fail_carian")).get("CATATAN_KEBENARAN_EDIT")!=null)
    		{    		 		
    		context.put("catatan_kebenaran_edit", model.getMainFail(getParam("id_fail_carian")).get("CATATAN_KEBENARAN_EDIT")); 
    		}
    		
    		
    		String id_permohonan ="";
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL")!=null)
    		{
    			id_permohonan = model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN")+"";
    		}
    		
    		headerppk_baru(session, id_permohonan, "Y", "", "T");	
    	} 
		
		else if ("paparSubPopup".equals(submit)){
    		context.put("id_fail_carian",getParam("id_fail_carian"));
    		String txtNoFailSub = getParam("txtNoFailSub");    		
    		context.put("txtNoFailSub", txtNoFailSub.trim());  
    		String txtIcPemohon = getParam("txtIcPemohon");
    		context.put("txtIcPemohon", txtIcPemohon.trim()); 
    		
    		String txtNamaSimati = getParam("txtNamaSimati");
    		context.put("txtNamaSimati", txtNamaSimati.trim()); 
    		String txtNamaPemohon = getParam("txtNamaPemohon");
    		context.put("txtNamaPemohon", txtNamaPemohon.trim()); 
    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")!=null)
    		{    		 		
    		context.put("txtNoFailSub_selected", model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL"));  
    		}    	
    		context.put("list_fail",model.search_nofail(txtNoFailSub.trim(),usid,txtIcPemohon,txtNamaSimati,txtNamaPemohon));  
    		context.put("cari_sub","yes");    	
    		context.put("view_list_fail","yes");
    		
    		myLogger.info("getParam(id_fail_carian) : "+getParam("id_fail_carian"));
    		list = logic.carianFail_byID(getParam("id_fail_carian"), session);
    		this.context.put("SenaraiFail", list);
    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_STATUS")!=null)
    		{    		 		
    		context.put("latest_idstatus", model.getMainFail(getParam("id_fail_carian")).get("ID_STATUS")); 
    		} 
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN")!=null)
    		{    		 		
    		context.put("id_permohonan_kebenaran", model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN")); 
    		}
    		if(model.getMainFail(getParam("id_fail_carian")).get("FLAG_KEBENARAN_EDIT")!=null)
    		{    		 		
    		context.put("flag_kebenaran_edit", model.getMainFail(getParam("id_fail_carian")).get("FLAG_KEBENARAN_EDIT")); 
    		}    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("USER_ID_KEBENARAN_EDIT")!=null)
    		{    		 		
    		context.put("user_id_kebenaran_edit", model.getMainFail(getParam("id_fail_carian")).get("USER_ID_KEBENARAN_EDIT")); 
    		}    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("USER_NAME")!=null)
    		{    		 		
    		context.put("nama_user_yg_beri_kebenaran", model.getMainFail(getParam("id_fail_carian")).get("USER_NAME")); 
    		}
    		if(model.getMainFail(getParam("id_fail_carian")).get("CATATAN_KEBENARAN_EDIT")!=null)
    		{    		 		
    		context.put("catatan_kebenaran_edit", model.getMainFail(getParam("id_fail_carian")).get("CATATAN_KEBENARAN_EDIT")); 
    		}
    		
    		String id_permohonan ="";
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL")!=null)
    		{
    			id_permohonan = model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN")+"";
    		}
    		
    		headerppk_baru(session, id_permohonan, "Y", "", "T");
    		
    		vm = "app/ppk/frmSenaraiFailSek8ForView_Popup.jsp";	
    	} 
		else if ("simpanFlag".equals(submit)){
    		context.put("id_fail_carian",getParam("id_fail_carian"));
    		String txtNoFailSub = getParam("txtNoFailSub");  
    		String txtIcPemohon = getParam("txtIcPemohon"); 
    		context.put("txtNoFailSub", txtNoFailSub.trim());
    		context.put("txtIcPemohon", txtIcPemohon.trim());
    		
    		String txtNamaSimati = getParam("txtNamaSimati");  
    		context.put("txtNamaSimati", txtNamaSimati.trim());
    		String txtNamaPemohon = getParam("txtNamaPemohon"); 
    		context.put("txtNamaPemohon", txtNamaPemohon.trim());
    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")!=null)
    		{    		 		
    		context.put("txtNoFailSub_selected", model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")); 
    		}    	
    		context.put("list_fail",model.search_nofail(txtNoFailSub.trim(),usid,txtIcPemohon,txtNamaSimati,txtNamaPemohon));  
    		context.put("cari_sub","yes");    	
    		context.put("view_list_fail","yes");
    		
    		myLogger.info("getParam(id_fail_carian) : "+getParam("id_fail_carian"));
    		list = logic.carianFail_byID(getParam("id_fail_carian"), session);
    		this.context.put("SenaraiFail", list);
    		
    		setFlagKebenaran(session,getParam("id_permohonan_kebenaran"),getParam("user_id_kebenaran_edit"),getParam("flag_kebenaran_edit"),getParam("catatan_kebenaran_edit"));		 	
    			
    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_STATUS")!=null)
    		{    		 		
    		context.put("latest_idstatus", model.getMainFail(getParam("id_fail_carian")).get("ID_STATUS")); 
    		}
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN")!=null)
    		{    		 		
    		context.put("id_permohonan_kebenaran", model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN")); 
    		}
    		if(model.getMainFail(getParam("id_fail_carian")).get("FLAG_KEBENARAN_EDIT")!=null)
    		{    		 		
    		context.put("flag_kebenaran_edit", model.getMainFail(getParam("id_fail_carian")).get("FLAG_KEBENARAN_EDIT")); 
    		}    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("USER_ID_KEBENARAN_EDIT")!=null)
    		{    		 		
    		context.put("user_id_kebenaran_edit", model.getMainFail(getParam("id_fail_carian")).get("USER_ID_KEBENARAN_EDIT")); 
    		}
    		if(model.getMainFail(getParam("id_fail_carian")).get("USER_NAME")!=null)
    		{    		 		
    		context.put("nama_user_yg_beri_kebenaran", model.getMainFail(getParam("id_fail_carian")).get("USER_NAME")); 
    		}
    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("CATATAN_KEBENARAN_EDIT")!=null)
    		{    		 		
    		context.put("catatan_kebenaran_edit", model.getMainFail(getParam("id_fail_carian")).get("CATATAN_KEBENARAN_EDIT")); 
    		}
    		
    		
    		String id_permohonan ="";
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL")!=null)
    		{
    			id_permohonan = model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN")+"";
    		}
    		
    		headerppk_baru(session, id_permohonan, "Y", "", "T");
    		
    		
    	} 
		else{
    		
		}
		return vm;
	}
	
	public Hashtable getUsername(String user_id) throws Exception {
		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT USER_NAME FROM USERS WHERE USER_ID = '"+user_id+"'";		
				
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("USER_NAME") == null) {
					h.put("USER_NAME", "");
				} else {
					h.put("USER_NAME", rs.getString("USER_NAME"));
				}				
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private void setFlagKebenaran(HttpSession session,String id_permohonan,String user_id_kebenaran_edit,String flag_kebenaran_edit,String catatan_kebenaran_edit) throws Exception {		 	
	 	Connection conn = null;
		Db db = null;			
		String sql1="";		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();					
					r.update("ID_PERMOHONAN",id_permohonan);
					if(flag_kebenaran_edit.equals(""))
					{
					r.add("user_id_kebenaran_edit","");
					r.add("catatan_kebenaran_edit","");
					}
					else
					{
					r.add("user_id_kebenaran_edit",user_id_kebenaran_edit);
					r.add("catatan_kebenaran_edit",catatan_kebenaran_edit);
					}
					r.add("flag_kebenaran_edit", flag_kebenaran_edit);				
					sql1 = r.getSQLUpdate("TBLPPKPERMOHONAN");
					myLogger.info("UPDATE FLAG KEBENARAN:"+sql1);
					stmt.executeUpdate(sql1);			
		conn.commit();
	} catch (SQLException se) {
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	private void headerppk_baru(HttpSession session, String id_permohonan,
			String flag_permohonan, String id_fail, String flag_fail)
			throws Exception {
		// hashtable maklumat header
		this.context.put("headerppk", mainheader.getHeaderData(session,
				id_permohonan, flag_permohonan, id_fail, flag_fail));
		
		this.context.put("getPemohonData", mainheader.getPemohonData(session,id_permohonan, null));
		
		Vector list_sub = null;
		list_sub = mainheader.carianFail(id_permohonan, flag_permohonan,
				id_fail, flag_fail);
		this.context.put("list_sub_header", list_sub);
		this.context.put("flag_jenis_vm", "utiliti_ajax");
	}

	private void headerppk_baru_default() {
		Hashtable headerppk = null;
		this.context.put("headerppk", "");
		this.context.put("list_sub_header", "");
		this.context.put("getPemohonData", "");
		this.context.put("flag_jenis_vm", "utiliti_ajax");
	}

}
