/**
 * 
 */
package ekptg.report.ppt;





import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppt.FrmPembatalanInternalData;
import ekptg.model.ppt.PPTHeader;

/**
 * @author Razman
 *
 */
public class FrmPopupPenarikanBalik extends AjaxBasedModule{
	
	static Logger myLogger = Logger.getLogger(FrmPopupPenarikanBalik.class);	

	private static final long serialVersionUID = 1L;
	FrmPembatalanInternalData logic = new FrmPembatalanInternalData();
	PPTHeader header = new PPTHeader();
	
	
	@Override
	public String doTemplate2() throws Exception {
		
		
		HttpSession session = request.getSession();		
		String vm = "";
		String main_command = getParam("command");			
		String report = getParam("report");
		String id_tanah = getParam("id_tanah");
		String id_fail = getParam("id_fail");
		String id_siasatan = getParam("id_siasatan");
		String id_penarikan = getParam("id_penarikan");
		String pemilik = getParam("pemilik");		
		String no_fail = getParam("no_fail");
		String id_mmk = getParam("id_mmk");
		String tarikh_surat = getParam("tarikh_surat");
		String nama_daerah = getParam("nama_daerah");	
		String senarai_mukim = getParam("senarai_mukim");
		String luas_lot = getParam("luas_lot");
		String senarai_lot = getParam("senarai_lot");		
		String id_bayaran = getParam("id_bayaran");
		String id_hakmilikpb = getParam("id_hakmilikpb");
		String bil_lot = getParam("bil_lot");
		this.context.put("id_jawatan","0");	
		String selectNoFail = getParam("sorSelectNoFail");
		
		String token = getParam("token");
		String usid = (String) session.getAttribute("_portal_username");
		String login = (String) session.getAttribute("_ekptg_user_id");
		context.put("username", usid);

		
		
		
		
		
		
		
		Vector list_pegawai1 = null;
		Vector list_pegawai_byid = null;
		Vector list_jpph = null;
		Vector maklumat_penyediaan = null;
		Vector dataIcLogin = new Vector();
		
		list_pegawai1 = logic.list_pegawai((String) session.getAttribute("_ekptg_user_negeri"),"1",(String) session.getAttribute("_ekptg_user_negeri"),(String) session.getAttribute("_portal_role"));
	    this.context.put("list_pegawai1",list_pegawai1);
	    
	    list_jpph = logic.list_jpph((String) session.getAttribute("_ekptg_user_negeri"),"1",(String) session.getAttribute("_ekptg_user_negeri"),(String) session.getAttribute("_portal_role"));
	    this.context.put("list_jpph",list_jpph);
	    
	    
	    
	    String id_negeri = "";
		String no_rujukan_ptg = "";
		String no_rujukan_ptd = "";
		String no_rujukan_upt = "";
    	String idpermohonan = getParam("id_permohonan");
    	String userlogin = "";
    	
    	
    	myLogger.info("ID_PERMOHONAN :::"+getParam("id_permohonan"));
    	myLogger.info("NO_FAIL :::"+getParam("sorSelectNoFail"));
    	
    	
    	header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_fail = (String)dh.get("id_fail");
			id_negeri = (String)dh.get("id_projekNegeri");
			no_fail = (String)dh.get("no_fail");	
			no_rujukan_ptg = (String)dh.get("no_rujukan_ptg");	
			no_rujukan_ptd = (String)dh.get("no_rujukan_ptd");	
			no_rujukan_upt = (String)dh.get("no_rujukan_upt");
		}
		
		
		String ic_login = "";
	    String id_jawatan1 = "";
	    String jawatan1 = "";
	    setIcLogin(login);
	    dataIcLogin = getIcLogin();
	    if(dataIcLogin.size()!=0){
	    	Hashtable np = (Hashtable)dataIcLogin.get(0);
	    	ic_login = np.get("ic_login").toString();
	    	id_jawatan1 = np.get("id_jawatan1").toString();
	    	jawatan1 = np.get("jawatan1").toString();
	    }
		
	    myLogger.info("ic_login :"+ic_login);
	    myLogger.info("id_jawatan1 :"+id_jawatan1);
	    myLogger.info("id_jawatan1 :"+id_jawatan1);
	    context.put("ic_login",ic_login);
	    context.put("id_jawatan1",id_jawatan1);
	    context.put("jawatan1",jawatan1);
	    this.context.put("token", token);
	    
		//tambah 08112020 - v7	
      	if ("sendDGcert".equals(submit)) {
      		
      		myLogger.info("send CERT SIGN");
      		context.put("token",token);
      		context.put("userlogin",login);
      		myLogger.info("token adalah :"+token);
				      	
      		myLogger.info("userlogin :"+userlogin);
      		
      	}
	 
	    
	    if ("getJawatan".equals(main_command)){
	    	
	    	
	    	if(getParam("nama_pegawai1")!="")
	    		
	    	{	
	    	list_pegawai_byid = logic.list_pegawai_byid((String) session.getAttribute("_ekptg_user_negeri"),getParam("nama_pegawai1"),"1",(String) session.getAttribute("_ekptg_user_negeri"),(String) session.getAttribute("_portal_role"));
		  
	    
	    	Hashtable h = (Hashtable) list_pegawai_byid.get(0);
	    	
	    	if(!h.get("NAMA_PEGAWAI").toString().equals(""))
	    			{
	    	this.context.put("nama_pegawai",h.get("NAMA_PEGAWAI").toString());	
	    	
	    			}
	    	else
	    	{
	    		this.context.put("nama_pegawai","");	
		    	
	    		
	    	}
	    	if(!h.get("ID_JAWATAN").toString().equals(""))
			{
	    		this.context.put("id_jawatan",h.get("ID_JAWATAN").toString());	
	
			}
	    	else
	    	{
	    		
	    		this.context.put("id_jawatan","0");	
	    		
	    	}
	    	}
	    	
	    	this.context.put("report",getParam("report"));
	    	this.context.put("id_tanah",getParam("id_tanah"));
	    	this.context.put("id_fail",getParam("id_fail"));
	    	this.context.put("id_siasatan",getParam("id_siasatan"));
	    	this.context.put("id_penarikan",getParam("id_penarikan"));
	    	this.context.put("pemilik",getParam("pemilik"));		
	    	this.context.put("no_fail",getParam("no_fail"));
	    	this.context.put("id_mmk",getParam("id_mmk"));
	    	this.context.put("tarikh_surat",getParam("tarikh_surat"));
	    	this.context.put("nama_daerah",getParam("nama_daerah"));	
	    	this.context.put("senarai_mukim",getParam("senarai_mukim"));
	    	this.context.put("luas_lot",getParam("luas_lot"));
	    	this.context.put("senarai_lot",getParam("senarai_lot"));		
	    	this.context.put("id_bayaran",getParam("id_bayaran"));
	    	
	    	this.context.put("nama_pegawai1",getParam("nama_pegawai1"));
	    	this.context.put("nama_menteri",getParam("nama_menteri"));
	    	this.context.put("bil_surat",getParam("bil_surat"));
	    	this.context.put("tarikh_surat",getParam("tarikh_surat"));
	    	this.context.put("id_pejabat",getParam("id_pejabat"));
	    	
	    	this.context.put("selectNoFail",selectNoFail);
	    	this.context.put("sorSelectNoFail",getParam("sorSelectNoFail"));
	    	
	    	
	    	
	    	context.put("id_fail", id_fail);
			context.put("no_fail", no_fail);
			context.put("no_rujukan_ptg", no_rujukan_ptg);
			context.put("no_rujukan_ptd", no_rujukan_ptd);
			context.put("no_rujukan_upt", no_rujukan_upt);
			context.put("id_permohonan", getParam("id_permohonan"));
	    
	    	
	    	
	    	
	    	vm = "app/ppt/frmPopupPenarikanBalik.jsp";	
	    	
	    }
	    else
	    {	
	    	this.context.put("nama_pegawai1","");
	    	this.context.put("nama_pegawai","");
	    	this.context.put("nama_menteri","");
	       	this.context.put("bil_surat","");
	    	this.context.put("tarikh_surat","");
	    	this.context.put("id_pejabat","");
	    	this.context.put("id_jawatan","");	
	    	
	    	
	    if(report.equals("mmk_kl"))
	 	{
	 	this.context.put("id_mmk", id_mmk);	 
	 	this.context.put("no_fail", no_fail);		
		this.context.put("id_fail", id_fail);
	 	this.context.put("bil_lot", bil_lot);
	 	this.context.put("luas_lot", luas_lot);	 		
	 	this.context.put("report", report);	 		
	 	vm = "app/ppt/frmPopupPenarikanBalik.jsp";
	 	}
	    
	    if(report.equals("mmk_Kedah"))
	 	{
	 	this.context.put("id_mmk", id_mmk);	 		
	  	this.context.put("report", report);	 
	  	this.context.put("no_fail", no_fail);		
		this.context.put("id_fail", id_fail);
	 	vm = "app/ppt/frmPopupPenarikanBalik.jsp";
	 	}
	    
	    if(report.equals("mmk_Johor"))
	 	{
	 	this.context.put("id_mmk", id_mmk);	 		
	  	this.context.put("report", report);	 
	  	this.context.put("no_fail", no_fail);		
		this.context.put("id_fail", id_fail);
	 	vm = "app/ppt/frmPopupPenarikanBalik.jsp";
	 	}
	    
	    if(report.equals("mmk_Perak"))
	 	{
	 	this.context.put("id_mmk", id_mmk);	 		
	  	this.context.put("report", report);	 
	  	this.context.put("no_fail", no_fail);		
		this.context.put("id_fail", id_fail);
	 	vm = "app/ppt/frmPopupPenarikanBalik.jsp";
	 	}
	    
	    
	    if(report.equals("mmk_Penang"))
	 	{
	 	this.context.put("id_mmk", id_mmk);	 		
	  	this.context.put("report", report);	 
	  	this.context.put("no_fail", no_fail);		
		this.context.put("id_fail", id_fail);
	 	vm = "app/ppt/frmPopupPenarikanBalik.jsp";
	 	}
	    
	    if(report.equals("mmk_Pahang"))
	 	{
	 	this.context.put("id_mmk", id_mmk);	 		
	  	this.context.put("report", report);	 
	  	this.context.put("no_fail", no_fail);		
		this.context.put("id_fail", id_fail);
	 	vm = "app/ppt/frmPopupPenarikanBalik.jsp";
	 	}
	    
	    
	    if(report.equals("mmk_Terengganu"))
	 	{
	 	this.context.put("id_mmk", id_mmk);	 		
	  	this.context.put("report", report);	 
	  	this.context.put("no_fail", no_fail);		
		this.context.put("id_fail", id_fail);
	 	vm = "app/ppt/frmPopupPenarikanBalik.jsp";
	 	}
	    
	    if(report.equals("mmk_Kelantan"))
	 	{
	 	this.context.put("id_mmk", id_mmk);	 		
	  	this.context.put("report", report);	 
	  	this.context.put("no_fail", no_fail);		
		this.context.put("id_fail", id_fail);
	 	vm = "app/ppt/frmPopupPenarikanBalik.jsp";
	 	}
	    
	 
		
	    
	    if(report.equals("mmk_Perlis"))
	 	{
	 	this.context.put("id_mmk", id_mmk);	 		
	  	this.context.put("report", report);	 
	  	this.context.put("no_fail", no_fail);		
		this.context.put("id_fail", id_fail);
	 	vm = "app/ppt/frmPopupPenarikanBalik.jsp";
	 	}
	    
	    if(report.equals("mmk_N9"))
	 	{
	 	this.context.put("id_mmk", id_mmk);	 		
	  	this.context.put("report", report);	 
	  	this.context.put("no_fail", no_fail);		
		this.context.put("id_fail", id_fail);
	 	vm = "app/ppt/frmPopupPenarikanBalik.jsp";
	 	}
	    
	    if(report.equals("jadual_mmk_N9"))
	 	{
	 	this.context.put("id_penarikan", id_penarikan);	
	 	this.context.put("report", report);	 		
	 	vm = "app/ppt/frmPopupPenarikanBalik.jsp";
	 	}
	    
	    
	    
	    if(report.equals("mmk_melaka"))
	 	{
	 	this.context.put("id_mmk", id_mmk);	 		
	  	this.context.put("report", report);	 
	  	this.context.put("no_fail", no_fail);		
		this.context.put("id_fail", id_fail);
	 	vm = "app/ppt/frmPopupPenarikanBalik.jsp";
	 	}
	    
	    if(report.equals("mmk_jadual_kl"))
	 	{
	 	this.context.put("id_penarikan", id_penarikan);	
	 	this.context.put("report", report);	 		
	 	vm = "app/ppt/frmPopupPenarikanBalik.jsp";
	 	}
	    	
	    	
	    	
	    
	    if(report.equals("surat_ap_siasatan")||report.equals("surat_PTD_siasatan")||report.equals("surat_PTG_siasatan")||report.equals("surat_JPPH_siasatan"))
		{
		this.context.put("id_siasatan", id_siasatan);
		this.context.put("pemilik", pemilik);
		this.context.put("report", report);		
		vm = "app/ppt/frmPopupPenarikanBalik.jsp";
		}
	    
	    
	    
	    if(report.equals("mmk_selangor"))
		{
		this.context.put("id_mmk", id_mmk);
		this.context.put("tarikh_surat", tarikh_surat);
		
		this.context.put("nama_daerah", nama_daerah);
		this.context.put("no_fail", no_fail);		
		this.context.put("id_fail", id_fail);
		
		this.context.put("report", report);
		
		vm = "app/ppt/frmPopupPenarikanBalik.jsp";
		}
	    
	    
	    
	    if(report.equals("surat_maklum_bayar_AP"))
		{
		this.context.put("id_fail", id_fail);
		this.context.put("report", report);	
		vm = "app/ppt/frmPopupPenarikanBalik.jsp";
		}
	    
	    
	    
	    
	    if(report.equals("surat_suruh_AP_Bayar"))
		{
		this.context.put("id_fail", id_fail);
		this.context.put("report", report);	
		this.context.put("id_siasatan", id_siasatan);
		vm = "app/ppt/frmPopupPenarikanBalik.jsp";
		}
	    
	    if(report.equals("surat_serah_bayar_AP"))
		{
		this.context.put("id_fail", id_fail);
		this.context.put("id_pembatalan", id_penarikan);
		this.context.put("id_bayaran", id_bayaran);
		this.context.put("report", report);	
		vm = "app/ppt/frmPopupPenarikanBalik.jsp";
		}
	    
	    
	    if(report.equals("surat_pangil_bayar_PB"))
		{
		this.context.put("id_fail", id_fail);
		this.context.put("id_pembatalan", id_penarikan);
		this.context.put("id_bayaran", id_bayaran);
		this.context.put("report", report);	
		vm = "app/ppt/frmPopupPenarikanBalik.jsp";
		}
	    
	    
	    
	    if(report.equals("akuan_penerimaan"))
		{
		this.context.put("id_bayaran", id_bayaran);
		this.context.put("report", report);	
		vm = "app/ppt/frmPopupPenarikanBalik.jsp";
		}
	    
	    if(report.equals("akuan_penerimaan_all"))
		{
		this.context.put("id_hakmilikpb", id_hakmilikpb);
		this.context.put("id_penarikan", id_penarikan);
		this.context.put("report", report);	
		vm = "app/ppt/frmPopupPenarikanBalik.jsp";
		}
	    
	    
	    
	    if(report.equals("mmk_mb_selangor"))
		{
		this.context.put("id_mmk", id_mmk);
		this.context.put("nama_mukim_mukim", senarai_mukim);		
		this.context.put("overallluas", luas_lot);
		this.context.put("no_lot_mmk", senarai_lot);	
		this.context.put("report", report);
		
		vm = "app/ppt/frmPopupPenarikanBalik.jsp";
		}
	    
	    
	    if(report.equals("mmk_mb_selangor_jadual"))
		{
	    this.context.put("id_penarikan", id_penarikan);
		this.context.put("report", report);
		
		vm = "app/ppt/frmPopupPenarikanBalik.jsp";
		}
	    
	    
	    if(report.equals("surat_pb_siasatan")||report.equals("surat_pemilik_siasatan"))
		{
	    this.context.put("id_fail", id_fail);
		this.context.put("id_siasatan", id_siasatan);
		this.context.put("id_penarikan", id_penarikan);
		this.context.put("pemilik", pemilik);
		this.context.put("report", report);
		
		vm = "app/ppt/frmPopupPenarikanBalik.jsp";
		}
	    
		
		if(report.equals("laporan_tanah"))
		{
		this.context.put("id_fail", id_fail);
		this.context.put("id_tanah", id_tanah);
		this.context.put("report", report);
		
		vm = "app/ppt/frmPopupPenarikanBalik.jsp";
		}
	    }
		
		return vm;
	}
	

	@SuppressWarnings("unchecked")
	public static void setIcLogin(String idlogin) throws Exception {
		
		icLogin = new Vector();
		
		Db db = null;
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
			
				/*sql =  "SELECT DISTINCT A.USER_ID, A.USER_NAME, A.USER_LOGIN ";
				sql += " FROM USERS A, USERS_INTERNAL B ";
				sql += " WHERE A.USER_ID = B.USER_ID ";
				sql += " AND B.FLAG_AKTIF = '1' ";
				sql += " AND A.USER_ID= '"+idlogin+"'";*/
				
				sql =  "SELECT DISTINCT A.USER_ID, A.USER_NAME, A.USER_LOGIN, B.ID_JAWATAN, C.KETERANGAN";
				sql += " FROM USERS A, USERS_INTERNAL B, TBLRUJJAWATAN C";
				sql += " WHERE A.USER_ID = B.USER_ID";
				sql += " AND B.ID_JAWATAN = C.ID_JAWATAN(+)";
				sql += " AND B.FLAG_AKTIF = '1' ";
				sql += " AND A.USER_ID= '"+idlogin+"'";
				
				
				ResultSet rs = stmt.executeQuery(sql);
				myLogger.info("sql icLogin :"+sql);
				
				while (rs.next()) {
					Hashtable h = new Hashtable();
					h.put("ic_login", rs.getString("USER_LOGIN")== null?"":rs.getString("USER_LOGIN"));
					h.put("id_jawatan1", rs.getString("ID_JAWATAN")== null?"":rs.getString("ID_JAWATAN"));
					h.put("jawatan1", rs.getString("KETERANGAN")== null?"":rs.getString("KETERANGAN"));
					icLogin.addElement(h);
				
			}
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			} finally {
			if(db != null)db.close();
		}
	}//close setIcLOGIN
	
	//get detail user
			private static Vector icLogin = null;
			
			public static Vector getIcLogin() {
				return icLogin;
			}
}
