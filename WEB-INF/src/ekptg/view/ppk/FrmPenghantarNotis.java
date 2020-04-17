package ekptg.view.ppk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.action.AjaxModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmPenghantarNotis extends AjaxModule{
	
	static Logger myLogger = Logger.getLogger(FrmPenghantarNotis.class);	
	private final String PATH="app/ppk/";
	private String vm = PATH +"frmPenghantarNotis.jsp";
	String userId = null;
	String role = null;
	String user_negeri_login = null;
	
	HttpSession session = null;
	String action = null;	
	
	
		
	public String doAction() throws Exception{
			
		session = request.getSession();
		userId = (String)session.getAttribute("_ekptg_user_id");
		context.put("pengguna_aktif",userId);		
		user_negeri_login = (String)session.getAttribute("_ekptg_user_negeri");
		context.put("id_negeri_user",user_negeri_login);		
		role = (String)session.getAttribute("myrole");
		String command = getParam("command");
		String mode = getParam("mode");
		action = getParam("action");

		String bolehsimpan = "";
		String readmode = "";		
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {			
			bolehsimpan = "yes";
		}
		myLogger.info("COMMAND :"+command);
		myLogger.info("ROLE :"+role);
		context.put("role",role);
		
		Vector list =null;
		Vector listPejabat = null; 
		Hashtable getPenghantarNotis = null;
		context.put("list","");		
		context.put("addSkrin","");
		context.put("ScrollX","");	
	    context.put("ScrollY","");	  	
		context.put("listPejabat","");
		
		context.put("idpejabat","");
		context.put("nama","");
		context.put("myidbaru","");
		context.put("myidlama","");
		context.put("idpenghantarnotis","");
		context.put("kod","");
		
		context.put("id_pejabat_search","");
		context.put("nama_search","");
		context.put("myid_search","");
		
	
		if(command.equals("tambah"))
		{
		context.put("addSkrin","yes");		
		context.put("ScrollX",getParam("ScrollX"));	
	    context.put("ScrollY",getParam("ScrollY"));
	    main_list();
	    default_list();
		}	
		else if(command.equals("simpan"))
		{
			if(getParam("idpenghantarnotis") != "")
			{				
				simpanPenghantarNotis(session,getParam("idpenghantarnotis"),getParam("kod"),getParam("nama"),
					getParam("myidbaru"),getParam("myidlama"),getParam("idpejabat"));
				getPenghantarNotis = (Hashtable)getPenghantarNotis(getParam("idpenghantarnotis"));			
				context.put("idpejabat",(String)getPenghantarNotis.get("ID_PEJABATJKPTG"));
				context.put("nama",(String)getPenghantarNotis.get("NAMA"));
				context.put("myidbaru",(String)getPenghantarNotis.get("NO_KP_BARU"));
				context.put("myidlama",(String)getPenghantarNotis.get("NO_KP_LAMA"));
				context.put("idpenghantarnotis",(String)getPenghantarNotis.get("ID_PENGHANTARNOTIS"));
				context.put("kod",(String)getPenghantarNotis.get("KOD_PENGHANTAR_NOTIS"));			
				context.put("ScrollX",getParam("ScrollX"));	
			    context.put("ScrollY",getParam("ScrollY"));
			    main_list();
			    default_list();
			    context.put("addSkrin","yes");
			}
			else
			{
				simpanPenghantarNotis(session,"",getParam("kod"),getParam("nama"),
						getParam("myidbaru"),getParam("myidlama"),getParam("idpejabat"));
			}
			context.put("ScrollX",getParam("ScrollX"));	
		    context.put("ScrollY",getParam("ScrollY"));
		    main_list();
		    default_list();
		}
		else if(command.equals("papar"))
		{
			getPenghantarNotis = (Hashtable)getPenghantarNotis(getParam("idpenghantarnotis"));			
			context.put("idpejabat",(String)getPenghantarNotis.get("ID_PEJABATJKPTG"));
			context.put("nama",(String)getPenghantarNotis.get("NAMA"));
			context.put("myidbaru",(String)getPenghantarNotis.get("NO_KP_BARU"));
			context.put("myidlama",(String)getPenghantarNotis.get("NO_KP_LAMA"));
			context.put("idpenghantarnotis",(String)getPenghantarNotis.get("ID_PENGHANTARNOTIS"));
			context.put("kod",(String)getPenghantarNotis.get("KOD_PENGHANTAR_NOTIS"));			
			context.put("ScrollX",getParam("ScrollX"));	
		    context.put("ScrollY",getParam("ScrollY"));
		    main_list();
		    default_list();
		    context.put("addSkrin","yes");
		}
		else if(command.equals("cari"))
		{
			context.put("id_pejabat_search",getParam("id_pejabat_search"));
			context.put("nama_search",getParam("nama_search"));
			context.put("myid_search",getParam("myid_search"));
			main_list();
			default_list();
		}
		else if ("hapus".equals(command)){   		
    		
    		String[] ids1 = this.request.getParameterValues("ids1_delete_data");
    		if (ids1 != null) {
    			for (int i = 0; i < ids1.length; i++) {						
    			myLogger.info("ids1 :"+ids1);		
    			deletePenghantarNotis(ids1[i]); 
    			}
    			}	
        	
    		main_list();
			default_list(); 
    	}
		else if(command.equals("kosongCariNotis"))
		{
			main_list();
			default_list();
		}
		else
		{			
			main_list();
			default_list();
				
		}		
		return vm;	
	}
	
	
	private void main_list()throws Exception{
		Vector list = new Vector();
		list = listPenghantarNotis(getParam("nama_search"),getParam("myid_search"),getParam("id_pejabat_search"));		
		setupPage(session,action,list);
	}
	
	private void default_list()throws Exception{
		Vector listPejabat = new Vector();
		listPejabat = listPejabat();		
		context.put("listPejabat",listPejabat);	
	}
	
	public void deletePenghantarNotis(String id_penghantarnotis) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLPPKRUJPENGHANTARNOTIS WHERE id_penghantarnotis = "
					+ id_penghantarnotis;
			
			myLogger.info("DELETE id_penghantarnotis :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Hashtable getPenghantarNotis(String id_penghantarnotis) throws Exception {
		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT ID_PENGHANTARNOTIS,KOD_PENGHANTAR_NOTIS,NAMA,NO_KP_BARU,NO_KP_LAMA,ID_PEJABATJKPTG" +
					" FROM TBLPPKRUJPENGHANTARNOTIS "
				  +" WHERE ID_PENGHANTARNOTIS = '"+id_penghantarnotis+"'";		
				
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("ID_PENGHANTARNOTIS",rs.getString("ID_PENGHANTARNOTIS") == null ? "" : rs.getString("ID_PENGHANTARNOTIS").toUpperCase());
				h.put("KOD_PENGHANTAR_NOTIS",rs.getString("KOD_PENGHANTAR_NOTIS") == null ? "" : rs.getString("KOD_PENGHANTAR_NOTIS").toUpperCase());
				h.put("NAMA",rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
				h.put("NO_KP_BARU",rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU").toUpperCase());
				h.put("NO_KP_LAMA",rs.getString("NO_KP_LAMA") == null ? "" : rs.getString("NO_KP_LAMA").toUpperCase());
				h.put("ID_PEJABATJKPTG",rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG").toUpperCase());
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	 private void simpanPenghantarNotis(HttpSession session,String idpenghantarnotis,String kod,String nama,String myidbaru,
			 String myidlama,String idpejabat) throws Exception {		 	
		 	Connection conn = null;
			Db db = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");				
			String sql="";	
			
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				String user_id = (String) session.getAttribute("_ekptg_user_id");				
				
					r.clear();					
					if(!idpenghantarnotis.equals(""))
					{
					r.update("ID_PENGHANTARNOTIS",idpenghantarnotis);	
					}
					r.add("KOD_PENGHANTAR_NOTIS",kod );
					r.add("ID_PEJABATJKPTG", idpejabat);
					r.add("NAMA", nama);
					r.add("NO_KP_BARU", myidbaru);
					r.add("NO_KP_LAMA", myidlama);
											
					if(!idpenghantarnotis.equals(""))
					{
						r.add("ID_KEMASKINI",user_id);			
						r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
						sql = r.getSQLUpdate("TBLPPKRUJPENGHANTARNOTIS");
					}
					else
					{
						r.add("ID_KEMASKINI",user_id);			
						r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
						r.add("ID_MASUK",user_id);			
						r.add("TARIKH_MASUK",r.unquote("sysdate"));
						sql = r.getSQLInsert("TBLPPKRUJPENGHANTARNOTIS");	
					}
					stmt.executeUpdate(sql);
					
				
				conn.commit();
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Simpan Mesej:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
					
		}
	
	Vector listPejabat = null;
	public Vector listPejabat()
			throws Exception {
		listPejabat = new Vector();
		Db db = null;
		listPejabat.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT N.KOD_NEGERI,PEJ.ID_PEJABATJKPTG,PEJ.NAMA_PEJABAT,N.NAMA_NEGERI,B.KETERANGAN AS NAMA_BANDAR  "+
				" FROM TBLRUJPEJABATJKPTG PEJ,TBLRUJNEGERI N,TBLRUJBANDAR B "+
				" WHERE PEJ.ID_SEKSYEN = '2' AND PEJ.ID_JENISPEJABAT = '22' AND PEJ.ID_NEGERI = N.ID_NEGERI(+) "+
				" AND PEJ.ID_BANDAR = B.ID_BANDAR(+) ORDER BY N.KOD_NEGERI";
			myLogger.info("LIST TBLRUJPEJABATJKPTG:" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_PEJABATJKPTG",rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG").toUpperCase());
				h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("NAMA_BANDAR",rs.getString("NAMA_BANDAR") == null ? "" : rs.getString("NAMA_BANDAR").toUpperCase());
				listPejabat.addElement(h);
			}
			return listPejabat;
		} finally {
			if (db != null)
				db.close();
		}

	}
	
	
	Vector listPenghantarNotis = null;
	public Vector listPenghantarNotis(String nama,String myid,String id_pejabat)
			throws Exception {
		listPenghantarNotis = new Vector();
		Db db = null;
		listPenghantarNotis.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT PN.ID_PENGHANTARNOTIS,PN.KOD_PENGHANTAR_NOTIS,PN.NAMA,PN.NO_KP_BARU,PN.NO_KP_LAMA, "+
			" JP.ID_PEJABATJKPTG,JP.NAMA_PEJABAT,JP.ALAMAT1,JP.ALAMAT2,JP.ALAMAT3,JP.POSKOD,N.NAMA_NEGERI,B.KETERANGAN AS NAMA_BANDAR "+
			" FROM TBLPPKRUJPENGHANTARNOTIS PN,TBLRUJPEJABATJKPTG JP,TBLRUJNEGERI N,TBLRUJBANDAR B "+
			" WHERE PN.ID_PEJABATJKPTG = JP.ID_PEJABATJKPTG(+) AND JP.ID_NEGERI = N.ID_NEGERI(+) " +
			" AND JP.ID_BANDAR = B.ID_BANDAR(+) ";
			
			if(!nama.equals(""))
			{
			sql += " AND UPPER(PN.NAMA) LIKE '%"+nama.trim()+"%'";
			}
			
			if(!myid.equals(""))
			{
			sql += " AND ( UPPER(PN.NO_KP_BARU) LIKE '%"+myid.trim()+"%' OR UPPER(PN.NO_KP_LAMA) LIKE '%"+myid.trim()+"%' ) ";
			}
			
			if(!id_pejabat.equals(""))
			{
			sql += " AND PN.ID_PEJABATJKPTG = '"+id_pejabat.trim()+"'";	
			}
			
			sql+=" ORDER BY PN.NAMA ";
			myLogger.info("LIST PENGHANTAR NOTIS:" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_PENGHANTARNOTIS",rs.getString("ID_PENGHANTARNOTIS") == null ? "" : rs.getString("ID_PENGHANTARNOTIS").toUpperCase());
				h.put("KOD_PENGHANTAR_NOTIS",rs.getString("KOD_PENGHANTAR_NOTIS") == null ? "" : rs.getString("KOD_PENGHANTAR_NOTIS").toUpperCase());
				h.put("NAMA",rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
				h.put("NO_KP_BARU",rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU").toUpperCase());
				h.put("NO_KP_LAMA",rs.getString("NO_KP_LAMA") == null ? "" : rs.getString("NO_KP_LAMA").toUpperCase());
				h.put("ID_PEJABATJKPTG",rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG").toUpperCase());
				h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("ALAMAT1",rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1").toUpperCase().trim());
				h.put("ALAMAT2",rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2").toUpperCase().trim());
				h.put("ALAMAT3",rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3").toUpperCase().trim());
				h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD").toUpperCase().trim());
				h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("NAMA_BANDAR",rs.getString("NAMA_BANDAR") == null ? "" : rs.getString("NAMA_BANDAR").toUpperCase());				
				listPenghantarNotis.addElement(h);
			}
			return listPenghantarNotis;
		} finally {
			if (db != null)
				db.close();
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	////////BAWAH NI FUNCTION XPAKAI//////////////////////////////////////////////////////
	
	
	
	
	
	
	private void simpanDokumen()throws Exception{
	
	Vector user_selected = null;
	Hashtable getMesej = null;
	
	String id_maininbox = getParam("id_maininbox");
	context.put("id_maininbox", id_maininbox);
	String id_inboxmesej = getParam("id_inboxmesej");
	context.put("id_inboxmesej", id_inboxmesej);			
	uploadFiles();			
	listDokumen_inbox = senarai_dokumen_inbox(id_inboxmesej);
	context.put("listDokumen_inbox",listDokumen_inbox);				
	context.put("flag_buka_upload","no");			
	getMesej = (Hashtable) getMesej(id_inboxmesej);
	context.put("id_maininbox",(String)getMesej.get("ID_MAININBOX"));	
	context.put("id_inboxmesej",(String)getMesej.get("ID_INBOXMESEJ"));	
	context.put("mesej",(String)getMesej.get("MESEJ"));	
	user_selected = getListSelectedUsers(id_inboxmesej);
	context.put("user_selected",user_selected);				
	hantarMesej(session);			
	context.put("flag_simpan_doc", "yes");			
	myLogger.info("ScrollX :"+getParam("ScrollX"));
	myLogger.info("ScrollY :"+getParam("ScrollY"));
	context.put("ScrollX",getParam("ScrollX"));	
    context.put("ScrollY",getParam("ScrollY"));	
	}
	
	private void setReloadPerbualan()throws Exception{
	Vector user_selected = null;
	Hashtable getMesej = null;		
	String id_maininbox = getParam("id_maininbox");
	context.put("id_maininbox", id_maininbox);
	String id_inboxmesej = getParam("id_inboxmesej");
	context.put("id_inboxmesej", id_inboxmesej);			
	simpanMesejSementara(session);
	
	listDokumen_inbox = senarai_dokumen_inbox(id_inboxmesej);
	context.put("listDokumen_inbox",listDokumen_inbox);				
	context.put("flag_buka_upload","no");			
	getMesej = (Hashtable) getMesej(id_inboxmesej);
	context.put("id_maininbox",(String)getMesej.get("ID_MAININBOX"));	
	context.put("id_inboxmesej",(String)getMesej.get("ID_INBOXMESEJ"));	
	context.put("mesej",(String)getMesej.get("MESEJ"));	
	user_selected = getListSelectedUsers(id_inboxmesej);
	context.put("user_selected",user_selected);				
	context.put("flag_simpan_doc", "yes");	
	
	paparMesej();
	
	
	myLogger.info("ScrollX :"+getParam("ScrollX"));
	myLogger.info("ScrollY :"+getParam("ScrollY"));
	context.put("ScrollX",getParam("ScrollX"));	
    context.put("ScrollY",getParam("ScrollY"));	
	}
	
	private void simpanDokumenPerbualan()throws Exception{
		
		Vector user_selected = null;
		Hashtable getMesej = null;		
		String id_maininbox = getParam("id_maininbox");
		context.put("id_maininbox", id_maininbox);
		String id_inboxmesej = getParam("id_inboxmesej");
		context.put("id_inboxmesej", id_inboxmesej);			
		uploadFiles();
		simpanMesejSementara(session);
		paparMesej();
		listDokumen_inbox = senarai_dokumen_inbox(id_inboxmesej);
		context.put("listDokumen_inbox",listDokumen_inbox);				
		context.put("flag_buka_upload","no");			
		getMesej = (Hashtable) getMesej(id_inboxmesej);
		context.put("id_maininbox",(String)getMesej.get("ID_MAININBOX"));	
		context.put("id_inboxmesej",(String)getMesej.get("ID_INBOXMESEJ"));	
		context.put("mesej",(String)getMesej.get("MESEJ"));	
		user_selected = getListSelectedUsers(id_inboxmesej);
		context.put("user_selected",user_selected);				
		//hantarMesejPerbualan(session);	
		context.put("flag_simpan_doc", "yes");			
		myLogger.info("ScrollX :"+getParam("ScrollX"));
		myLogger.info("ScrollY :"+getParam("ScrollY"));
		context.put("ScrollX",getParam("ScrollX"));	
	    context.put("ScrollY",getParam("ScrollY"));	
		}
	
	
	private void cariMesej()throws Exception{		
		Vector user_selected = null;
		Vector listMesejUser = null;
		Vector listDokumenMainInboxSkrin = null;		
		String id_maininbox = getParam("id_maininbox");	
		String cari_mesej = getParam("cari_mesej");	
		context.put("id_maininbox",id_maininbox);
		updateNotification(session,id_maininbox,userId,"YES");
		context.put("view_skrin","skrinPebualan");
		user_selected = getListSelectedUsers(id_maininbox);
		context.put("user_selected",user_selected);			
		getMainDetails(id_maininbox).get("id_masuk");
		context.put("id_pendaftar_mesej",getMainDetails(id_maininbox).get("id_masuk"));			
		String senarai_penerima = "";
		if(getUserAtList(id_maininbox).get("user_name")!=null)
		{
		senarai_penerima = getUserAtList(id_maininbox).get("user_name")+"";			
		senarai_penerima = senarai_penerima.replaceAll("9XXXX9", "<span class='nav' align='center' valign='top' ><font color='blue' >&nbsp;");
		senarai_penerima = senarai_penerima.replaceAll("9YYYY9", "</font>&nbsp;</span>");
		}
		context.put("senarai_penerima",senarai_penerima);						
		listMesejUser = listMesej(id_maininbox,cari_mesej);
		context.put("listMesejUser",listMesejUser);
		context.put("cari_mesej",cari_mesej);
		listDokumenMainInboxSkrin = listDokumenMainInbox(id_maininbox);
		context.put("listDokumenMainInboxSkrin",listDokumenMainInboxSkrin);			
		String  preview_user = getParam("preview_user");	
		myLogger.info("preview_user :"+preview_user);
		if(preview_user.equals("display"))
		{
		context.put("preview_user","display");	
		context.put("papar_mesej","YES");
		}
		else
		{
		context.put("list_user",list_user(""));	
		context.put("preview_user","edit");		
		}
		}
	
	
	
	private void cariMesejFromMain()throws Exception{		
		Vector user_selected = null;
		Vector listMesejUser = null;
		Vector listDokumenMainInboxSkrin = null;		
		String id_maininbox = getParam("id_maininbox");	
		String cari_mesej = getParam("carian_main");	
		context.put("id_maininbox",id_maininbox);
		updateNotification(session,id_maininbox,userId,"YES");
		context.put("view_skrin","skrinPebualan");
		user_selected = getListSelectedUsers(id_maininbox);
		context.put("user_selected",user_selected);			
		getMainDetails(id_maininbox).get("id_masuk");
		context.put("id_pendaftar_mesej",getMainDetails(id_maininbox).get("id_masuk"));			
		String senarai_penerima = "";
		if(getUserAtList(id_maininbox).get("user_name")!=null)
		{
		senarai_penerima = getUserAtList(id_maininbox).get("user_name")+"";			
		senarai_penerima = senarai_penerima.replaceAll("9XXXX9", "<span class='nav' align='center' valign='top' ><font color='blue' >&nbsp;");
		senarai_penerima = senarai_penerima.replaceAll("9YYYY9", "</font>&nbsp;</span>");
		}
		context.put("senarai_penerima",senarai_penerima);						
		listMesejUser = listMesej(id_maininbox,cari_mesej);
		context.put("listMesejUser",listMesejUser);
		context.put("cari_mesej",cari_mesej);
		listDokumenMainInboxSkrin = listDokumenMainInbox(id_maininbox);
		context.put("listDokumenMainInboxSkrin",listDokumenMainInboxSkrin);			
		String  preview_user = getParam("preview_user");	
		myLogger.info("preview_user :"+preview_user);
		if(preview_user.equals("display"))
		{
		context.put("preview_user","display");	
		context.put("papar_mesej","YES");
		}
		else
		{
		context.put("list_user",list_user(""));	
		context.put("preview_user","edit");		
		}
		}
	
	
	private void paparMesej()throws Exception{		
	Vector user_selected = null;
	Vector listMesejUser = null;
	Vector listDokumenMainInboxSkrin = null;		
	String id_maininbox = getParam("id_maininbox");	
	context.put("id_maininbox",id_maininbox);
	updateNotification(session,id_maininbox,userId,"YES");
	context.put("view_skrin","skrinPebualan");
	user_selected = getListSelectedUsers(id_maininbox);
	context.put("user_selected",user_selected);			
	getMainDetails(id_maininbox).get("id_masuk");
	context.put("id_pendaftar_mesej",getMainDetails(id_maininbox).get("id_masuk"));			
	String senarai_penerima = "";
	if(getUserAtList(id_maininbox).get("user_name")!=null)
	{
	senarai_penerima = getUserAtList(id_maininbox).get("user_name")+"";			
	senarai_penerima = senarai_penerima.replaceAll("9XXXX9", "<span class='nav' align='center' valign='top' ><font color='blue' >&nbsp;");
	senarai_penerima = senarai_penerima.replaceAll("9YYYY9", "</font>&nbsp;</span>");
	}
	context.put("senarai_penerima",senarai_penerima);						
	listMesejUser = listMesej(id_maininbox,"");
	context.put("listMesejUser",listMesejUser);			
	listDokumenMainInboxSkrin = listDokumenMainInbox(id_maininbox);
	context.put("listDokumenMainInboxSkrin",listDokumenMainInboxSkrin);			
	String  preview_user = getParam("preview_user");	
	myLogger.info("preview_user :"+preview_user);
	if(preview_user.equals("display"))
	{
	context.put("preview_user","display");	
	context.put("papar_mesej","YES");
	}
	else
	{
	context.put("list_user",list_user(""));	
	context.put("preview_user","edit");		
	}
	}
	
	private void hantarMesej(HttpSession session) throws Exception {
	 	
	 	Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql_maininbox="";
		String sql_inboxmesej="";
		String sql_usersinbox="";
		String user_id_login = (String) session.getAttribute("_ekptg_user_id");
		String role = (String) session.getAttribute("myrole");
		String id_maininbox = "";
		String id_inboxmesej = "";
		String id_maininbox_check = "";
		String id_maininbox_check_found = "";
		String id_inboxmesej_check = "";
		String command = getParam("command");
		Hashtable getMesej = null;
		Vector user_selected = null;
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();						
			
			String mesej = getParam("mesej");			
			id_maininbox = getParam("id_maininbox");
			id_inboxmesej = getParam("id_inboxmesej");
			id_maininbox_check = getParam("id_maininbox");
			id_inboxmesej_check = getParam("id_inboxmesej");
			myLogger.info("id_maininbox_check :::"+id_maininbox_check);
			
			
			if(id_maininbox_check.equals(""))
			{
			String[] user_id_check = this.request.getParameterValues("user_id");
			myLogger.info("******** USER LENGTH :"+user_id_check.length);
			if (user_id_check != null) 
			{					
				if(user_id_check.length == 2)
				{
				if(checkPerbualanSebelum(user_id_check[0],user_id_check[1]).get("id_maininbox_found")!= null)	
				{
				myLogger.info("XXX FOUND ID_MAIN INBOX :"+checkPerbualanSebelum(user_id_check[0],user_id_check[1]).get("id_maininbox_found"));	
				id_maininbox_check_found = checkPerbualanSebelum(user_id_check[0],user_id_check[1]).get("id_maininbox_found")+"";
				id_maininbox = checkPerbualanSebelum(user_id_check[0],user_id_check[1]).get("id_maininbox_found")+"";
				id_maininbox_check = checkPerbualanSebelum(user_id_check[0],user_id_check[1]).get("id_maininbox_found")+"";
				
				}								
				}				
			}
			}
			
			if(id_maininbox_check.equals(""))
			{
			id_maininbox = DB.getNextID(db,"TBLMAININBOX_SEQ")+"";			
			}
			if(id_inboxmesej_check.equals(""))
			{
			id_inboxmesej = DB.getNextID(db,"TBLINBOXMESEJ_SEQ")+"";
			}
			
			r.clear();
			if(id_maininbox_check.equals(""))
			{
			r.add("ID_MAININBOX",id_maininbox);	
			}
			else
			{
			r.update("ID_MAININBOX",id_maininbox);		
			}
			if(id_maininbox_check.equals(""))
			{
			r.add("ID_MASUK",user_id_login);			
			r.add("TARIKH_MASUK",r.unquote("sysdate"));
			}
			
			if(command.equals("hantar"))
			{
			r.add("FLAG_AKTIF","Y");
			}
			else
			{
			r.add("FLAG_AKTIF","N");	
			}			
			
			r.add("ID_KEMASKINI",user_id_login);
			r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
			if(id_maininbox_check.equals(""))
			{
			sql_maininbox = r.getSQLInsert("TBLMAININBOX");
			}
			else
			{
			sql_maininbox = r.getSQLUpdate("TBLMAININBOX");	
			}
			myLogger.info("INSERT MAIN INBOX :"+sql_maininbox.toUpperCase());
			stmt.executeUpdate(sql_maininbox);
			
			
			
			r.clear();
			if(id_inboxmesej_check.equals(""))
			{
			r.add("ID_INBOXMESEJ",id_inboxmesej);
			}
			else
			{
			r.update("ID_INBOXMESEJ",id_inboxmesej);	
			}
			r.add("MESEJ",mesej);
			r.add("ID_MAININBOX",id_maininbox);
			
			if(command.equals("hantar"))
			{
			r.add("FLAG_AKTIF","Y");
			}
			else
			{
			r.add("FLAG_AKTIF","N");	
			}
			
			if(id_inboxmesej_check.equals(""))
			{
			r.add("ID_MASUK",user_id_login);			
			r.add("TARIKH_MASUK",r.unquote("sysdate"));
			}
			r.add("ID_KEMASKINI",user_id_login);
			r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));	
			if(id_inboxmesej_check.equals(""))
			{
			sql_inboxmesej = r.getSQLInsert("TBLINBOXMESEJ");
			}
			else
			{
			sql_inboxmesej = r.getSQLUpdate("TBLINBOXMESEJ");	
			}
			myLogger.info("INSERT INBOX MESEJ :"+sql_inboxmesej.toUpperCase());
			stmt.executeUpdate(sql_inboxmesej);
						
			
			String[] user_id = this.request.getParameterValues("user_id");
			if (user_id != null) 
			{
				deleteUserSelected_byMain(id_maininbox); 
				deleteInboxNotifikasi_byMain(id_maininbox);
				for (int i = 0; i < user_id.length; i++) 
				{
					
					r.clear();
					r.add("ID_MAININBOX",id_maininbox);
					r.add("USER_ID",user_id[i]);
					r.add("ID_MASUK",user_id_login);
					r.add("FLAG_ARKIB","N");
					r.add("ID_KEMASKINI",user_id_login);
					r.add("TARIKH_MASUK",r.unquote("sysdate"));
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));			
					sql_usersinbox = r.getSQLInsert("TBLUSERSINBOX");				
					myLogger.info("INSERT USERS INBOX :"+sql_usersinbox.toUpperCase());
					stmt.executeUpdate(sql_usersinbox);
					
					if(!mesej.equals(""))
					{
						
						if(command.equals("hantar"))
						{
							if(!user_id[i].equals(userId))
							{
								addNotifikasiInbox(session,id_maininbox,user_id[i],"NO");
							}
							else
							{
								addNotifikasiInbox(session,id_maininbox,user_id[i],"YES");	
							}
						}
					}
						
					
				}
			}
			
			
			
			
			conn.commit();
			
			
			getMesej = (Hashtable) getMesej(id_inboxmesej);
			context.put("id_maininbox",(String)getMesej.get("ID_MAININBOX"));	
			context.put("id_inboxmesej",(String)getMesej.get("ID_INBOXMESEJ"));	
			context.put("mesej",(String)getMesej.get("MESEJ"));	
			user_selected = getListSelectedUsers(id_maininbox);
			context.put("user_selected",user_selected);
			
				
		    
	} catch (SQLException se) {
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Mesej:" + se.getMessage());
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	
		private void simpanMesejSementara(HttpSession session) throws Exception {
	 	
	 	Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql_maininbox="";
		String sql_inboxmesej="";
		String sql_usersinbox="";
		String user_id_login = (String) session.getAttribute("_ekptg_user_id");
		String role = (String) session.getAttribute("myrole");
		String id_maininbox = "";
		String id_inboxmesej = "";
		String id_maininbox_check = "";
		String id_maininbox_check_found = "";
		String id_inboxmesej_check = "";
		String command = getParam("command");
		Hashtable getMesej = null;
		Vector user_selected = null;
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();						
			
			String mesej = getParam("mesej");			
			id_maininbox = getParam("id_maininbox");
			id_inboxmesej = getParam("id_inboxmesej");
			id_maininbox_check = getParam("id_maininbox");
			id_inboxmesej_check = getParam("id_inboxmesej");
			myLogger.info("id_maininbox_check :::"+id_maininbox_check);
			
		
			if(id_inboxmesej_check.equals(""))
			{
			id_inboxmesej = DB.getNextID(db,"TBLINBOXMESEJ_SEQ")+"";
			}
			
			r.clear();
			if(id_inboxmesej_check.equals(""))
			{
			r.add("ID_INBOXMESEJ",id_inboxmesej);
			}
			else
			{
			r.update("ID_INBOXMESEJ",id_inboxmesej);	
			}
			r.add("MESEJ",mesej);
			r.add("ID_MAININBOX",id_maininbox);
			
			if(command.equals("hantarPerbualan"))
			{
			r.add("FLAG_AKTIF","Y");
			}
			else
			{
			r.add("FLAG_AKTIF","N");	
			}
			
			if(id_inboxmesej_check.equals(""))
			{
			r.add("ID_MASUK",user_id_login);			
			r.add("TARIKH_MASUK",r.unquote("sysdate"));
			}
			r.add("ID_KEMASKINI",user_id_login);
			r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));	
			if(id_inboxmesej_check.equals(""))
			{
			sql_inboxmesej = r.getSQLInsert("TBLINBOXMESEJ");
			}
			else
			{
			sql_inboxmesej = r.getSQLUpdate("TBLINBOXMESEJ");	
			}
			myLogger.info("INSERT INBOX MESEJ :"+sql_inboxmesej.toUpperCase());
			stmt.executeUpdate(sql_inboxmesej);
			
			conn.commit();
			
			
			getMesej = (Hashtable) getMesej(id_inboxmesej);
			context.put("id_maininbox",(String)getMesej.get("ID_MAININBOX"));	
			context.put("id_inboxmesej",(String)getMesej.get("ID_INBOXMESEJ"));	
			context.put("mesej",(String)getMesej.get("MESEJ"));	
						
		    
	} catch (SQLException se) {
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Mesej:" + se.getMessage());
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	
private void hantarMesejPerbualan(HttpSession session) throws Exception {
	 	
	 	Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql_maininbox="";
		String sql_inboxmesej="";
		String sql_usersinbox="";
		String user_id_login = (String) session.getAttribute("_ekptg_user_id");
		String role = (String) session.getAttribute("myrole");
		String id_maininbox = "";
		String id_inboxmesej = "";
		//String id_maininbox_check = "";
		String command = getParam("command");
		Hashtable getMesej = null;
		Vector user_selected = null;
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();						
			
			String mesej = getParam("mesej");			
			id_maininbox = getParam("id_maininbox");
			id_inboxmesej = getParam("id_inboxmesej");
			//id_maininbox_check = getParam("id_maininbox");			
			myLogger.info("id_maininbox_check :::"+id_maininbox);
			
			if(id_maininbox.equals(""))
			{
			id_maininbox = DB.getNextID(db,"TBLMAININBOX_SEQ")+"";	
			id_inboxmesej = DB.getNextID(db,"TBLINBOXMESEJ_SEQ")+"";	
			}
			
			r.clear();
			if(id_maininbox.equals(""))
			{
			r.add("ID_MAININBOX",id_maininbox);	
			}
			else
			{
			r.update("ID_MAININBOX",id_maininbox);		
			}
			if(id_maininbox.equals(""))
			{
			r.add("ID_MASUK",user_id_login);			
			r.add("TARIKH_MASUK",r.unquote("sysdate"));
			}
			r.add("ID_KEMASKINI",user_id_login);
			r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
			if(id_maininbox.equals(""))
			{
			sql_maininbox = r.getSQLInsert("TBLMAININBOX");
			}
			else
			{
			sql_maininbox = r.getSQLUpdate("TBLMAININBOX");	
			}
			myLogger.info("INSERT MAIN INBOX :"+sql_maininbox.toUpperCase());
			stmt.executeUpdate(sql_maininbox);
			
			r.clear();
			if(id_inboxmesej.equals(""))
			{
			r.add("ID_INBOXMESEJ",id_inboxmesej);
			}
			else
			{
			r.update("ID_INBOXMESEJ",id_inboxmesej);	
			}
			r.add("MESEJ",mesej);
			r.add("ID_MAININBOX",id_maininbox);
			
			if(command.equals("hantarPerbualan"))
			{
			r.add("FLAG_AKTIF","Y");
			}
			else
			{
			r.add("FLAG_AKTIF","N");	
			}
			
			if(id_inboxmesej.equals(""))
			{
			r.add("ID_MASUK",user_id_login);			
			r.add("TARIKH_MASUK",r.unquote("sysdate"));
			}
			r.add("ID_KEMASKINI",user_id_login);
			r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));	
			if(id_inboxmesej.equals(""))
			{
			sql_inboxmesej = r.getSQLInsert("TBLINBOXMESEJ");
			}
			else
			{
			sql_inboxmesej = r.getSQLUpdate("TBLINBOXMESEJ");	
			}
			myLogger.info("INSERT INBOX MESEJ :"+sql_inboxmesej.toUpperCase());
			stmt.executeUpdate(sql_inboxmesej);
						
			
			String[] user_id = this.request.getParameterValues("user_id");
			if (user_id != null) 
			{
				
				for (int i = 0; i < user_id.length; i++) 
				{
				myLogger.info("SEND MESEJ USER_ID"+user_id[i]);						
					if(!mesej.equals(""))
					{
						
						if(command.equals("hantarPerbualan"))
						{
							if(!user_id[i].equals(userId))
							{
								addNotifikasiInbox(session,id_maininbox,user_id[i],"NO");
							}
							else
							{
								addNotifikasiInbox(session,id_maininbox,user_id[i],"YES");	
							}
						}
					}
						
					
				}
			}
			
			
			
			
			conn.commit();
			
			
			getMesej = (Hashtable) getMesej(id_inboxmesej);
			context.put("id_maininbox",(String)getMesej.get("ID_MAININBOX"));	
			context.put("id_inboxmesej",(String)getMesej.get("ID_INBOXMESEJ"));	
			context.put("mesej",(String)getMesej.get("MESEJ"));	
			
				
		    
	} catch (SQLException se) {
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Mesej:" + se.getMessage());
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	
	private void daftarUserBaru(HttpSession session) throws Exception {
	 	
	 	Connection conn = null;
		Db db = null;
		
		String sql_usersinbox="";
		String user_id_login = (String) session.getAttribute("_ekptg_user_id");		
		String id_maininbox = "";
		
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();						
			
			id_maininbox = getParam("id_maininbox");
			
			String try_insert = "";
		
			String[] user_id = this.request.getParameterValues("user_id");
					if (user_id != null) 
					{
						deleteUserSelected_byMain(id_maininbox); 
						deleteInboxNotifikasi_byMain(id_maininbox);						
						
						for (int i = 0; i < user_id.length; i++) 
						{						
							r.clear();
							r.add("ID_MAININBOX",id_maininbox);
							r.add("USER_ID",user_id[i]);
							r.add("ID_MASUK",user_id_login);
							r.add("ID_KEMASKINI",user_id_login);
							r.add("FLAG_ARKIB","N");
							r.add("TARIKH_MASUK",r.unquote("sysdate"));
							r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));			
							sql_usersinbox = r.getSQLInsert("TBLUSERSINBOX");
							stmt.executeUpdate(sql_usersinbox);
							try_insert += sql_usersinbox.toUpperCase();
							
							
									if(!user_id[i].equals(userId))
									{
										addNotifikasiInbox_check(session,id_maininbox,user_id[i],"NO");
									}
									else
									{
										addNotifikasiInbox_check(session,id_maininbox,user_id[i],"YES");	
									}
							
							
							
																				
						}	
					}
					
					
					
					
					myLogger.info("INSERT USERS INBOX LA WEI :"+try_insert);	
					conn.commit();
	
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Simpan Mesej:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
					
		}
	
	
	
	
	public void deleteUserSelected_byMain(String id_maininbox) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLUSERSINBOX WHERE ID_MAININBOX = "
					+ id_maininbox;
			
			myLogger.info("DELETE deleteUserSelected_byMain :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void deleteInboxNotifikasi_byMain(String id_maininbox) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLINBOXNOTIFIKASI WHERE ID_MAININBOX = "
					+ id_maininbox;
			
			myLogger.info("DELETE deleteInboxNotifikasi_byMain :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public void deleteDokumen_main(String id_inboxattach) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLINBOXATTACH WHERE ID_INBOXATTACH = "
					+ id_inboxattach;
			
			myLogger.info("DELETE deleteDokumen_main :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	Vector list_user = null;
	public Vector list_user(String user_id) throws Exception {
		list_user = new Vector();
		list_user.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "select u.user_name,u.user_id from users u,users_internal " +
					"ui where ui.user_id = u.user_id ";
			if(!user_id.equals(""))
			{
			sql += " and u.user_id = "+user_id;
			}
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("user_id", rs.getString("user_id"));
				
				h.put("user_id_dum", rs.getString("user_id")+"#"+rs.getString("user_name")+"");
				
				
				if (rs.getString("user_name") == null) {
					h.put("user_name", "");
				} else {
					h.put("user_name", rs.getString("user_name"));
				}
				list_user.addElement(h);
			}
			return list_user;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	Vector listMesej = null;
	public Vector listMesej(String id_maininbox,String carian_mesej) throws Exception {
		listMesej = new Vector();
		listMesej.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			sql = ""+
			" SELECT A.ID_MAININBOX,B.ID_INBOXMESEJ,B.MESEJ,B.ID_MASUK,C.USER_ID,C.USER_NAME,TO_CHAR(B.TARIKH_KEMASKINI,'DD/MM/YYYY HH:MI AM') AS TARIKH_MASUK," +
			" TO_CHAR(B.TARIKH_KEMASKINI,'YYYY-MM-DD') AS TARIKH_MASUK_24, " +
			" TO_CHAR(B.TARIKH_KEMASKINI,'HH24:MI:SS') AS TARIKH_MASUK_TIME_24 " +
			" FROM TBLMAININBOX A,TBLINBOXMESEJ B,USERS C "+
			" WHERE A.ID_MAININBOX = B.ID_MAININBOX AND B.FLAG_AKTIF = 'Y' ";
			
			if (carian_mesej != null) {
				if (!carian_mesej.trim().equals("")) {
					
					sql = sql + " AND UPPER(B.MESEJ) LIKE '%' ||'"
							+ carian_mesej.toUpperCase() + "'|| '%' ";
				}
			}
			
			sql += " AND B.ID_MASUK = C.USER_ID AND A.ID_MAININBOX = '"+id_maininbox+"'"+
			" ORDER BY B.TARIKH_KEMASKINI ASC ";
			
			myLogger.info("CARI MESEJ DALAMAN"+sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				Hashtable h = new Hashtable();
				h.put("BIL", bil);
				if (rs.getString("TARIKH_MASUK") == null) {
					h.put("tarikh_masuk", "");
				} else {
					h.put("tarikh_masuk", rs.getString("TARIKH_MASUK"));
				}
				
				if (rs.getString("TARIKH_MASUK_TIME_24") == null) {
					h.put("tarikh_masuk_time_24", "");
				} else {
					h.put("tarikh_masuk_time_24", rs.getString("TARIKH_MASUK_TIME_24"));
				}
				
				if (rs.getString("TARIKH_MASUK_24") == null) {
					h.put("tarikh_masuk_24", "");
				} else {
					h.put("tarikh_masuk_24", rs.getString("TARIKH_MASUK_24"));
				}
				
				if (rs.getString("USER_NAME") == null) {
					h.put("user_name", "");
				} else {
					h.put("user_name", rs.getString("USER_NAME"));
				}
								
				if (rs.getString("ID_MAININBOX") == null) {
					h.put("id_maininbox", "");
				} else {
					h.put("id_maininbox", rs.getString("ID_MAININBOX"));
				}
				
				if (rs.getString("ID_INBOXMESEJ") == null) {
					h.put("id_inboxmesej", "");
				} else {
					h.put("id_inboxmesej", rs.getString("ID_INBOXMESEJ"));
				}
				
				if (rs.getString("MESEJ") == null) {
					h.put("mesej", "");
				} else {
					h.put("mesej", rs.getString("MESEJ"));
				}
				
				if (rs.getString("USER_ID") == null) {
					h.put("user_id", "");
				} else {
					h.put("user_id", rs.getString("USER_ID"));
				}
				
				if (rs.getString("ID_MASUK") == null) {
					h.put("id_masuk", "");
				} else {
					h.put("id_masuk", rs.getString("ID_MASUK"));
				}
				
				
				listMesej.addElement(h);
			}
			return listMesej;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector list_selected_users = null;
	public Vector getListSelectedUsers(String id_maininbox) throws Exception {
		list_selected_users = new Vector();
		list_selected_users.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT MI.ID_MAININBOX,UI.ID_USERSINBOX,U.USER_ID,U.USER_NAME FROM TBLMAININBOX MI,TBLUSERSINBOX UI, USERS U "
				  +"WHERE MI.ID_MAININBOX = UI.ID_MAININBOX AND UI.USER_ID = U.USER_ID ";
			sql += " AND MI.ID_MAININBOX = '"+id_maininbox+"' ORDER BY UI.TARIKH_KEMASKINI ASC"; 
			
			myLogger.info("SENARAI USER DALAM MESEJ :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_maininbox", rs.getString("ID_MAININBOX"));
				h.put("id_usersinbox", rs.getString("ID_USERSINBOX"));
				h.put("user_id", rs.getString("USER_ID"));
				h.put("user_name", rs.getString("USER_NAME"));			
				list_selected_users.addElement(h);
			}
			return list_selected_users;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	 @SuppressWarnings("unchecked")
		private void uploadFiles() throws Exception {
		 
			    DiskFileItemFactory factory = new DiskFileItemFactory();
			    ServletFileUpload upload = new ServletFileUpload(factory);
			    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			    if(!isMultipart)
			    {
			    myLogger.info("BUKAN MULTIPART");
			    } 
			    else
			    {
			    myLogger.info("MULTIPART");			    	
			    }
			    List items = upload.parseRequest(request);
			    Iterator itr = items.iterator();
			    while (itr.hasNext()) {
			      FileItem item = (FileItem)itr.next();
			      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
			    	
			    	  myLogger.info("SIZE DOC :"+item.getSize());
			    	  if(item.getSize()>2097152)
			    	  {
			    		  context.put("alert_saiz","yes");
			    	  }
			    	  else
			    	  {
			    	  saveData(item);			    	  
			    	  }
			      }
			    }
			  }
		 private void saveData(FileItem item) throws Exception {
			 	HttpSession session = request.getSession();		
		  		Db db = null;
		        try {		        	
		        	long id_inboxattach = DB.getNextID("TBLINBOXATTACH_SEQ");	        	
		        	db = new Db();		        	
		        	Connection con = db.getConnection();
		        	con.setAutoCommit(false);
		        	SQLRenderer r = new SQLRenderer();
		        	PreparedStatement ps = con.prepareStatement("insert into TBLINBOXATTACH " +
		        			"(id_inboxattach,id_inboxmesej,nama_fail,jenis_Mime,content,id_masuk,id_kemaskini,saiz,tarikh_masuk,tarikh_kemaskini) " +
		        			"values(?,?,?,?,?,?,?,?,"+r.unquote("sysdate")+","+r.unquote("sysdate")+")");
		        	
		        	myLogger.info("1:"+id_inboxattach);
		        	myLogger.info("2:"+getParam("id_inboxmesej"));
		        	myLogger.info("3:"+item.getName());
		        	myLogger.info("4:"+item.getContentType());
		        	myLogger.info("5:"+item.getInputStream());
		        	myLogger.info("6:"+(String) session.getAttribute("_ekptg_user_id"));
		        	myLogger.info("7:"+(String) session.getAttribute("_ekptg_user_id"));
		        	myLogger.info("8:"+item.getSize());
		        	
		        	ps.setLong(1, id_inboxattach);
		        	ps.setString(2, getParam("id_inboxmesej"));
		        	ps.setString(3,item.getName());
		        	ps.setString(4,item.getContentType());
		        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());		        	     	
		        	ps.setString(6,(String) session.getAttribute("_ekptg_user_id"));	        	      	
		        	ps.setString(7,(String) session.getAttribute("_ekptg_user_id"));    
		        	ps.setLong(8,item.getSize());     
		        	ps.executeUpdate();
		        	
		            con.commit(); 
		            
		            
			    } finally {
				      if (db != null) db.close();
			    }
		  }		
		 
		 
		 public Hashtable getMesej(String id_inboxmesej) throws Exception {
				
				Db db = null;
				String sql = "";
				try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					sql = " SELECT A.ID_MAININBOX,B.ID_INBOXMESEJ,B.MESEJ FROM TBLMAININBOX A,TBLINBOXMESEJ B "
						  +" WHERE A.ID_MAININBOX = B.ID_MAININBOX AND B.ID_INBOXMESEJ = '"+id_inboxmesej+"'";		
						
					ResultSet rs = stmt.executeQuery(sql);
					
					Hashtable h;
					h = new Hashtable();
					while (rs.next()) {
						if (rs.getString("ID_MAININBOX") == null) {
							h.put("ID_MAININBOX", "");
						} else {
							h.put("ID_MAININBOX", rs.getString("ID_MAININBOX"));
						}
						if (rs.getString("ID_INBOXMESEJ") == null) {
							h.put("ID_INBOXMESEJ", "");
						} else {
							h.put("ID_INBOXMESEJ", rs.getString("ID_INBOXMESEJ"));
						}
						if (rs.getString("MESEJ") == null) {
							h.put("MESEJ", "");
						} else {
							h.put("MESEJ", rs.getString("MESEJ"));
						}
						
					}
					return h;
				} finally {
					if (db != null)
						db.close();
				}
			}
		 
		 
		 public Hashtable checkNotifikasi(String id_maininbox,String id_user_notifikasi_terima) throws Exception {
				
				Db db = null;
				String sql = "";
				try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					sql = " SELECT ID_INBOXNOTIFIKASI,ID_MAININBOX,id_user_notifikasi_terima FROM TBLINBOXNOTIFIKASI " +
							"where ID_MAININBOX = '"+id_maininbox+"' and id_user_notifikasi_terima = '"+id_user_notifikasi_terima+"'";		
					myLogger.info("CHECK NOTIFIKASI :"+sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					
					Hashtable h;
					h = new Hashtable();
					while (rs.next()) {
						if (rs.getString("ID_MAININBOX") == null) {
							h.put("ID_MAININBOX", "");
						} else {
							h.put("ID_MAININBOX", rs.getString("ID_MAININBOX"));
						}
						if (rs.getString("ID_INBOXNOTIFIKASI") == null) {
							h.put("ID_INBOXNOTIFIKASI", "");
						} else {
							h.put("ID_INBOXNOTIFIKASI", rs.getString("ID_INBOXNOTIFIKASI"));
						}
						if (rs.getString("ID_USER_NOTIFIKASI_TERIMA") == null) {
							h.put("ID_USER_NOTIFIKASI_TERIMA", "");
						} else {
							h.put("ID_USER_NOTIFIKASI_TERIMA", rs.getString("ID_USER_NOTIFIKASI_TERIMA"));
						}
						
					}
					return h;
				} finally {
					if (db != null)
						db.close();
				}
			}
		 
		 
		 public Hashtable getLastMesej(String id_maininbox,String user_id) throws Exception {
				
				Db db = null;
				String sql = "";
				try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					sql = "SELECT MESEJ FROM (SELECT B.MESEJ FROM TBLMAININBOX A,TBLINBOXMESEJ B,TBLUSERSINBOX C " +
							"WHERE A.ID_MAININBOX = B.ID_MAININBOX AND B.FLAG_AKTIF = 'Y' AND A.ID_MAININBOX = '"+id_maininbox+"' " +
							"AND C.USER_ID = '"+user_id+"' " +
							"AND B.ID_MAININBOX = C.ID_MAININBOX ORDER BY B.TARIKH_KEMASKINI DESC ) " +
							"WHERE ROWNUM <= 1";
					myLogger.info("CHECK LAST MESEJ :"+sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					
					Hashtable h;
					h = new Hashtable();
					while (rs.next()) {
						if (rs.getString("MESEJ") == null) {
							h.put("mesej", "");
						} else {
							h.put("mesej", rs.getString("MESEJ"));
						}
						
						
					}
					return h;
				} finally {
					if (db != null)
						db.close();
				}
			}
		 
		 
		 public Hashtable getLastMesej_idsender(String id_maininbox,String user_id) throws Exception {
				
				Db db = null;
				String sql = "";
				try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					sql = "SELECT ID_MASUK FROM (SELECT B.ID_MASUK FROM TBLMAININBOX A,TBLINBOXMESEJ B,TBLUSERSINBOX C " +
							"WHERE A.ID_MAININBOX = B.ID_MAININBOX AND A.ID_MAININBOX = '"+id_maininbox+"' " +
							"AND C.USER_ID = '"+user_id+"' " +
							"AND B.ID_MAININBOX = C.ID_MAININBOX ORDER BY B.TARIKH_KEMASKINI DESC ) " +
							"WHERE ROWNUM <= 1";
					myLogger.info("CHECK LAST MESEJ :"+sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					
					Hashtable h;
					h = new Hashtable();
					while (rs.next()) {
						if (rs.getString("ID_MASUK") == null) {
							h.put("id_sender", "");
						} else {
							h.put("id_sender", rs.getString("ID_MASUK"));
						}
						
						
					}
					return h;
				} finally {
					if (db != null)
						db.close();
				}
			}
		 
		 public Hashtable getLastMesejAttach(String id_maininbox,String user_id) throws Exception {
				
				Db db = null;
				String sql = "";
				try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					/*sql = "SELECT ADA_ATTACH FROM (SELECT COUNT(*) AS ADA_ATTACH FROM TBLMAININBOX A,TBLINBOXMESEJ B,TBLUSERSINBOX C,TBLINBOXATTACH D " +
							"WHERE A.ID_MAININBOX = B.ID_MAININBOX AND B.ID_INBOXMESEJ = D.ID_INBOXMESEJ AND A.ID_MAININBOX = '"+id_maininbox+"' " +
							"AND C.USER_ID = '"+user_id+"' " +
							"AND B.ID_MAININBOX = C.ID_MAININBOX ORDER BY B.TARIKH_KEMASKINI DESC ) " +
							"WHERE ROWNUM <= 1";*/
					
					sql = ""+
							" SELECT ADA_ATTACH FROM ( "+
							" SELECT (SELECT COUNT(*) FROM TBLINBOXATTACH C1 WHERE C1.ID_INBOXMESEJ = B.ID_INBOXMESEJ) AS ADA_ATTACH "+
							" FROM TBLMAININBOX A,TBLINBOXMESEJ B,TBLUSERSINBOX C "+
							" WHERE A.ID_MAININBOX = B.ID_MAININBOX "+ 
							" AND A.ID_MAININBOX = '"+id_maininbox+"' AND C.USER_ID = '"+user_id+"' AND B.ID_MAININBOX = C.ID_MAININBOX "+
							" ORDER BY B.TARIKH_KEMASKINI DESC "+
							" ) WHERE ROWNUM <= 1 ";
					
					
					
					myLogger.info("CHECK LAST MESEJ ATTACH :"+sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					
					Hashtable h;
					h = new Hashtable();
					while (rs.next()) {
						if (rs.getInt("ADA_ATTACH") > 0) {
							h.put("ada_attach", "ada");
						} else {
							h.put("ada_attach", "");
						}
						
						
					}
					return h;
				} finally {
					if (db != null)
						db.close();
				}
			}
		 
		 
		 
		 private void addNotifikasiInbox(HttpSession session,String id_maininbox,String id_user_notifikasi_terima,String flag_read) throws Exception {		 	
			 	Connection conn = null;
				Db db = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String sql="";		
				String sql1="";
				String jenis_comment = "";
				Vector check_notifikasi = null;
				Hashtable cn = null;
				String id_inboxnotifikasi = "";
				
				try {
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();				
					String user_id = (String) session.getAttribute("_ekptg_user_id");						
				
					cn = checkNotifikasi(id_maininbox,id_user_notifikasi_terima);
					if(cn.size() > 0)
					{
				    id_inboxnotifikasi = (String)cn.get("ID_INBOXNOTIFIKASI");	
					}
					
					
						r.clear();
						
						if(!id_inboxnotifikasi.equals(""))
						{
						r.update("ID_INBOXNOTIFIKASI",id_inboxnotifikasi);	
						}
						r.add("FLAG_NOTIFIKASI", jenis_comment);
						r.add("ID_USER_NOTIFIKASI_HANTAR", user_id);
						r.add("ID_USER_NOTIFIKASI_TERIMA", id_user_notifikasi_terima);
						
						r.add("ID_MAININBOX", id_maininbox);
						r.add("FLAG_READ", flag_read);
						r.add("ID_MASUK",user_id);						
						r.add("TARIKH_MASUK",r.unquote("sysdate"));
						if(!id_inboxnotifikasi.equals(""))
						{
						r.add("ID_KEMASKINI",user_id);
						r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
						}
						if(!id_inboxnotifikasi.equals(""))
						{
							sql1 = r.getSQLUpdate("TBLINBOXNOTIFIKASI");
						}
						else
						{
							sql1 = r.getSQLInsert("TBLINBOXNOTIFIKASI");	
						}
						stmt.executeUpdate(sql1);
						
					
					conn.commit();
			} catch (SQLException se) {
				try {
					conn.rollback();
				} catch (SQLException se2) {
					throw new Exception("Rollback error:" + se2.getMessage());
				}
				se.printStackTrace();
				throw new Exception("Ralat Simpan Mesej:" + se.getMessage());
			} finally {
				if (conn != null)
					conn.close();
				if (db != null)
					db.close();
			}
						
			}
		 
		 
		 private void addNotifikasiInbox_check(HttpSession session,String id_maininbox,String id_user_notifikasi_terima,String flag_read) throws Exception {		 	
			 	Connection conn = null;
				Db db = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String sql="";		
				String sql1="";
				String jenis_comment = "";
				Vector check_notifikasi = null;
				Hashtable cn = null;
				String id_inboxnotifikasi = "";
				
				try {
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();				
					String user_id = (String) session.getAttribute("_ekptg_user_id");						
				
					cn = checkNotifikasi(id_maininbox,id_user_notifikasi_terima);
					if(cn.size() > 0)
					{
				    id_inboxnotifikasi = (String)cn.get("ID_INBOXNOTIFIKASI");	
					}
					
					
						r.clear();
						
						if(!id_inboxnotifikasi.equals(""))
						{
						r.update("ID_INBOXNOTIFIKASI",id_inboxnotifikasi);	
						}
						r.add("FLAG_NOTIFIKASI", jenis_comment);
						r.add("ID_USER_NOTIFIKASI_HANTAR", user_id);
						r.add("ID_USER_NOTIFIKASI_TERIMA", id_user_notifikasi_terima);
						
						r.add("ID_MAININBOX", id_maininbox);
						
						if(flag_read.equals("NO"))
						{
							if(!id_inboxnotifikasi.equals(""))
							{
							
							}
							else
							{
							r.add("FLAG_READ", "NO");	
							}
							
						}
						else
						{
						r.add("FLAG_READ", flag_read);	
						}
						
						if(id_inboxnotifikasi.equals(""))
						{
						r.add("ID_MASUK",user_id);						
						r.add("TARIKH_MASUK",r.unquote("sysdate"));
						}
						r.add("ID_KEMASKINI",user_id);
						r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
						
						if(!id_inboxnotifikasi.equals(""))
						{
							sql1 = r.getSQLUpdate("TBLINBOXNOTIFIKASI");
						}
						else
						{
							sql1 = r.getSQLInsert("TBLINBOXNOTIFIKASI");	
						}
						stmt.executeUpdate(sql1);
						
					
					conn.commit();
			} catch (SQLException se) {
				try {
					conn.rollback();
				} catch (SQLException se2) {
					throw new Exception("Rollback error:" + se2.getMessage());
				}
				se.printStackTrace();
				throw new Exception("Ralat Simpan Mesej:" + se.getMessage());
			} finally {
				if (conn != null)
					conn.close();
				if (db != null)
					db.close();
			}
						
			}
		 
		 
		    Vector listMainInbox = null;
			public Vector listMainInbox(String userId,String carian_main,String flag_arkib,String unread)
					throws Exception {
				listMainInbox = new Vector();
				Db db = null;
				listMainInbox.clear();
				String sql = "";

				try {
					db = new Db();
					Statement stmt = db.getStatement();
					
					sql = ""+
					" SELECT C.FLAG_READ,A.TARIKH_KEMASKINI AS S,A.ID_MAININBOX,B.USER_ID,B.ID_USERSINBOX,TO_CHAR(A.TARIKH_KEMASKINI,'DD/MM/YYYY HH:MI AM') AS TARIKH_KEMASKINI "+
					" FROM TBLMAININBOX A,TBLUSERSINBOX B,TBLINBOXNOTIFIKASI C "+
					" WHERE A.ID_MAININBOX = B.ID_MAININBOX AND A.FLAG_AKTIF = 'Y' AND A.ID_MAININBOX = C.ID_MAININBOX "+
					" AND B.USER_ID = '"+userId+"' AND C.ID_USER_NOTIFIKASI_TERIMA = B.USER_ID ";
					
					if(unread.equals("Y"))
					{
						sql += " AND C.FLAG_READ = 'NO' ";
					}
										
					if(flag_arkib.equals("Y"))
					{
						sql += " AND B.FLAG_ARKIB = 'Y' ";	
					}
					else
					{
						sql += " AND B.FLAG_ARKIB = 'N' ";
					}
					
					if(!carian_main.equals(""))
					{
					    //by user name
						/*sql += " AND (A.ID_MAININBOX IN ("; 
						sql += " SELECT A.ID_MAININBOX FROM TBLUSERSINBOX A,USERS B"+
						" WHERE A.USER_ID = B.USER_ID "+
						" AND A.ID_MAININBOX IN ( "+
						" SELECT A.ID_MAININBOX "+
						" FROM TBLMAININBOX A,TBLUSERSINBOX B,TBLINBOXNOTIFIKASI C "+
						" WHERE A.ID_MAININBOX = B.ID_MAININBOX AND A.FLAG_AKTIF = 'Y'  ";
						
						
						if (carian_main != null) {
							if (!carian_main.trim().equals("")) {
								
								sql = sql + " AND UPPER(B.USER_NAME) LIKE '%' ||'"
										+ carian_main.toUpperCase() + "'|| '%' ";
							}
						}
						
						
						sql += " AND A.ID_MAININBOX = C.ID_MAININBOX  AND B.USER_ID = '"+userId+"' "+
						" AND C.ID_USER_NOTIFIKASI_TERIMA = B.USER_ID "+
						" ) ";
						sql += " )";
						
						sql += " OR";
						*/
						sql += " AND A.ID_MAININBOX IN (";												
						sql += " SELECT A.ID_MAININBOX "+
						" FROM TBLMAININBOX A,TBLUSERSINBOX B,TBLINBOXNOTIFIKASI C,TBLINBOXMESEJ D "+
						" WHERE A.ID_MAININBOX = B.ID_MAININBOX AND A.FLAG_AKTIF = 'Y' "+
						" AND A.ID_MAININBOX = C.ID_MAININBOX  AND B.USER_ID = '"+userId+"' "+
						" AND C.ID_USER_NOTIFIKASI_TERIMA = B.USER_ID ";
						
						
						if (carian_main != null) {
							if (!carian_main.trim().equals("")) {
								
								sql = sql + " AND UPPER(D.MESEJ) LIKE '%' ||'"
										+ carian_main.toUpperCase() + "'|| '%' ";
							}
						}
						
						
						sql +=" AND A.ID_MAININBOX = D.ID_MAININBOX "+
						" AND D.FLAG_AKTIF = 'Y' ";						
						sql += " )";
						//sql += ")";
						
					}	
					
					sql += " AND (SELECT COUNT(*) FROM TBLINBOXMESEJ G WHERE G.ID_MAININBOX = A.ID_MAININBOX) > 0 ";
					sql += " ORDER BY C.FLAG_READ,S DESC ";
					
					
					myLogger.info("SQL LIST MAIN INBOX XX :" + sql.toUpperCase());
					ResultSet rs = stmt.executeQuery(sql);

					Hashtable h;
					int bil = 0;

					while (rs.next()) {

						bil = bil + 1;
						h = new Hashtable();
						h.put("BIL", bil);
						
						h.put("flag_read",
								rs.getString("FLAG_READ") == null ? "" : rs
										.getString("FLAG_READ"));
						
						h.put("id_maininbox",
								rs.getString("ID_MAININBOX") == null ? "" : rs
										.getString("ID_MAININBOX"));
						h.put("user_id",
								rs.getString("USER_ID") == null ? "" : rs
										.getString("USER_ID"));
						h.put("id_userinbox",
								rs.getString("ID_USERSINBOX") == null ? "" : rs
										.getString("ID_USERSINBOX"));
						
						if(getLastMesej_idsender(rs.getString("ID_MAININBOX"),userId).get("id_sender")== null)
						{	
							h.put("id_sender","");		
						}
						else
						{
							
							h.put("id_sender",getLastMesej_idsender(rs.getString("ID_MAININBOX"),userId).get("id_sender"));
							
						}
						
						
						if(getLastMesej(rs.getString("ID_MAININBOX"),userId).get("mesej")== null)
						{	
							h.put("last_mesej","");		
						}
						else
						{
							
							h.put("last_mesej",getLastMesej(rs.getString("ID_MAININBOX"),userId).get("mesej"));
							
						}
						
						
						if(getLastMesejAttach(rs.getString("ID_MAININBOX"),userId).get("ada_attach")== null)
						{	
							h.put("ada_attach","");		
						}
						else
						{
							
							h.put("ada_attach",getLastMesejAttach(rs.getString("ID_MAININBOX"),userId).get("ada_attach"));
							
						}
						
						String a = "";
						if(getUserAtList(rs.getString("ID_MAININBOX"))!=null)
						{
						//a = getUserAtList(rs.getString("ID_MAININBOX")).get("user_name")+"";
						//a = a.substring(2, a.length());
						
						a = getUserAtList(rs.getString("ID_MAININBOX")).get("user_name")+"";
						
						a = a.replaceAll("9XXXX9", "<span class='nav' align='center' valign='top' ><font color='blue' >&nbsp;");

						a = a.replaceAll("9YYYY9", "</font>&nbsp;</span>");

						
						h.put("senaraiUser",a);
						}
						else
						{
							h.put("senaraiUser","");	
						}
						
							
						if (rs.getString("TARIKH_KEMASKINI") == null) {
							h.put("tarikh_kemaskini", "");
						} else {
							h.put("tarikh_kemaskini", rs.getString("TARIKH_KEMASKINI"));
						}
												

						listMainInbox.addElement(h);
					}

					return listMainInbox;

				} finally {
					if (db != null)
						db.close();
				}

			}
	
	
			
			public Hashtable getUserAtList(String id_maininbox) throws Exception {
				
				Db db = null;
				String sql = "";
				try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					sql = " SELECT   " +
							//"rtrim (xmlagg (xmlelement (e,', ' || INITCAP( C.USER_NAME) )).extract ('//text()'), ',') " +
					"rtrim (xmlagg (xmlelement (e,'9XXXX9' || INITCAP( C.USER_NAME) || '9YYYY9' )).extract ('//text()'), ',') " +		
					" USER_NAME FROM TBLMAININBOX A,TBLUSERSINBOX B,USERS C "+
							" WHERE A.ID_MAININBOX = B.ID_MAININBOX AND B.USER_ID = C.USER_ID AND A.ID_MAININBOX = "+id_maininbox;
					sql += " ORDER BY B.TARIKH_KEMASKINI ASC ";
					myLogger.info("SQL SENARAI USER DEKAT MAINLIST :" + sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					
					Hashtable h;
					h = new Hashtable();
					while (rs.next()) {
						if (rs.getString("USER_NAME") == null) {
							h.put("user_name", "");
						} else {
							h.put("user_name", rs.getString("USER_NAME"));
						}
						
					}
					return h;
				} finally {
					if (db != null)
						db.close();
				}
			}
	
			
			
				public Hashtable getMainDetails(String id_maininbox) throws Exception {
				
				Db db = null;
				String sql = "";
				try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					sql = " SELECT ID_MAININBOX,TARIKH_MASUK,ID_MASUK,ID_KEMASKINI,TARIKH_KEMASKINI FROM TBLMAININBOX WHERE ID_MAININBOX="+id_maininbox;
					myLogger.info("SQL MAIN DETAIL :" + sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					
					Hashtable h;
					h = new Hashtable();
					while (rs.next()) {
						if (rs.getString("ID_MASUK") == null) {
							h.put("id_masuk", "");
						} else {
							h.put("id_masuk", rs.getString("ID_MASUK"));
						}
						
					}
					return h;
				} finally {
					if (db != null)
						db.close();
				}
			}
				
				
				
				
				public Hashtable checkPerbualanSebelum(String user_1,String user_2) throws Exception {
					
					Db db = null;
					String sql = "";
					try {
						db = new Db();
						Statement stmt = db.getStatement();
						SQLRenderer r = new SQLRenderer();
						
						
						sql = " SELECT UI.ID_MAININBOX FROM TBLUSERSINBOX UI "+
						" WHERE  UI.USER_ID = ('"+user_1+"') AND UI.ID_MAININBOX IN "+
						" (SELECT X.ID_MAININBOX FROM TBLUSERSINBOX X "+
						" WHERE  X.USER_ID = ('"+user_2+"')) AND "+
						" (SELECT COUNT(*) FROM TBLUSERSINBOX Y WHERE Y.ID_MAININBOX = UI.ID_MAININBOX) = 2 ";
						
						myLogger.info(" SQL checkPerbualanSebelum :"+sql);
						
						ResultSet rs = stmt.executeQuery(sql);
						
						Hashtable h;
						h = new Hashtable();
						while (rs.next()) {
							if (rs.getString("ID_MAININBOX") == null) {
								h.put("id_maininbox_found", "");
							} else {
								h.put("id_maininbox_found", rs.getString("ID_MAININBOX"));
							}
							
						}
						return h;
					} finally {
						if (db != null)
							db.close();
					}
				}
	
			
			Vector listDokumen_inbox = null;
			public Vector senarai_dokumen_inbox(String id_inboxmesej)
					throws Exception {
				listDokumen_inbox = new Vector();
				Db db = null;
				listDokumen_inbox.clear();
				String sql = "";

				try {
					db = new Db();
					Statement stmt = db.getStatement();
					sql = "SELECT A.NAMA_FAIL,A.ID_INBOXMESEJ,A.ID_INBOXATTACH,  A.JENIS_MIME,"
							+ " A.CONTENT,A.SAIZ  FROM TBLINBOXATTACH A,TBLINBOXMESEJ P WHERE A.ID_INBOXMESEJ = '"
							+ id_inboxmesej
							+ "'  "
							+ " AND A.ID_INBOXMESEJ = P.ID_INBOXMESEJ";
					myLogger.info("SQL INBOX MESEJ :" + sql.toUpperCase());
					ResultSet rs = stmt.executeQuery(sql);

					Hashtable h;
					int bil = 0;

					while (rs.next()) {

						bil = bil + 1;
						h = new Hashtable();
						h.put("BIL", bil);
						h.put("id_inboxmesej",
								rs.getString("ID_INBOXMESEJ") == null ? "" : rs
										.getString("ID_INBOXMESEJ"));
						h.put("id_inboxattach", rs.getString("ID_INBOXATTACH") == null ? ""
								: rs.getString("ID_INBOXATTACH"));
						//h.put("jenis_mime", rs.getString("JENIS_MIME") == null ? ""	: rs.getString("JENIS_MIME"));
						h.put("content", rs.getString("CONTENT") == null ? ""
								: rs.getString("CONTENT"));
						h.put("nama_fail", rs.getString("NAMA_FAIL") == null ? ""
								: rs.getString("NAMA_FAIL"));
						
						
						//a = a.replaceAll("9YYYY9", "</font>&nbsp;</span>");
						
						if(rs.getString("JENIS_MIME") == null)
						{
						h.put("jenis_mime","");	
						}else
						{
						String mime = "";
						char sym = '/';
						int index_i = 0;
						mime = rs.getString("JENIS_MIME");
						mime = mime.replaceAll("\"", "");
						
						
						for(int i=0;i<mime.length();i++)
						{
							if(mime.charAt(i) == sym)
							{
								index_i = i;	
							}
						}
						
						mime = mime.substring(0,index_i);
						h.put("jenis_mime",mime);
						}
						
						if(rs.getString("SAIZ") == null)
						{
						h.put("saiz","");	
						}
						else
						{
							 DecimalFormat dnf = new DecimalFormat( "#,###,###,##0.00" );

							 long fileSize = Long.parseLong(rs.getString("SAIZ"));
							   
							 if(fileSize >= (1024*1024))
							 {
							 h.put("saiz",(dnf.format((double)fileSize/(1024*1024)))+" MB");	 
							 }
							 else if(fileSize >= (1024))
							 {
							 h.put("saiz",(dnf.format((double)fileSize/1024))+" KB");		 
							 }
							 else
							 {
							 h.put("saiz",fileSize+" B");	 
							 }
							 myLogger.info("File size in bytes is: " + fileSize);
							 myLogger.info("File size in KB is : " + (double)fileSize/1024);
							 myLogger.info("File size in MB is :" + (double)fileSize/(1024*1024));
							
						/*	
						if (Long.parseLong(rs.getString("SAIZ")) < 1024){
						h.put("saiz",Long.parseLong(rs.getString("SAIZ"))+" B");
						}
						else if (Long.parseLong(rs.getString("SAIZ")) >= 1024 && Long.parseLong(rs.getString("SAIZ")) < (1024*1024)){
						
						long totalBytes = Long.parseLong(rs.getString("SAIZ"));	
						double line= ((double)totalBytes/(1024*1024));

						h.put("saiz",line+" KB");

						}*/
						}

						listDokumen_inbox.addElement(h);

					}

					return listDokumen_inbox;

				} finally {
					if (db != null)
						db.close();
				}

			}
			
			
			
			Vector listDokumenMainInbox = null;
			public Vector listDokumenMainInbox(String id_maininbox)
					throws Exception {
				listDokumenMainInbox = new Vector();
				Db db = null;
				listDokumenMainInbox.clear();
				String sql = "";

				try {
					db = new Db();
					Statement stmt = db.getStatement();
					sql = "SELECT A.NAMA_FAIL,A.ID_INBOXMESEJ,A.ID_INBOXATTACH,  A.JENIS_MIME,"
							+ " A.CONTENT,A.SAIZ  FROM TBLINBOXATTACH A,TBLINBOXMESEJ P WHERE P.ID_MAININBOX = '"
							+ id_maininbox
							+ "'  "
							+ " AND A.ID_INBOXMESEJ = P.ID_INBOXMESEJ";
					myLogger.info("SQL INBOX ATTACH MAIN MESEJ :" + sql.toUpperCase());
					ResultSet rs = stmt.executeQuery(sql);

					Hashtable h;
					int bil = 0;

					while (rs.next()) {

						bil = bil + 1;
						h = new Hashtable();
						h.put("BIL", bil);
						h.put("id_inboxmesej",
								rs.getString("ID_INBOXMESEJ") == null ? "" : rs
										.getString("ID_INBOXMESEJ"));
						h.put("id_inboxattach", rs.getString("ID_INBOXATTACH") == null ? ""
								: rs.getString("ID_INBOXATTACH"));
						//h.put("jenis_mime", rs.getString("JENIS_MIME") == null ? ""	: rs.getString("JENIS_MIME"));
						h.put("content", rs.getString("CONTENT") == null ? ""
								: rs.getString("CONTENT"));
						h.put("nama_fail", rs.getString("NAMA_FAIL") == null ? ""
								: rs.getString("NAMA_FAIL"));
						
						
						//a = a.replaceAll("9YYYY9", "</font>&nbsp;</span>");
						
						if(rs.getString("JENIS_MIME") == null)
						{
						h.put("jenis_mime","");	
						}else
						{
						String mime = "";
						char sym = '/';
						int index_i = 0;
						mime = rs.getString("JENIS_MIME");
						mime = mime.replaceAll("\"", "");
						
						
						for(int i=0;i<mime.length();i++)
						{
							if(mime.charAt(i) == sym)
							{
								index_i = i;	
							}
						}
						
						mime = mime.substring(0,index_i);
						h.put("jenis_mime",mime);
						}
						
						if(rs.getString("SAIZ") == null)
						{
						h.put("saiz","");	
						}
						else
						{
							 DecimalFormat dnf = new DecimalFormat( "#,###,###,##0.00" );

							 long fileSize = Long.parseLong(rs.getString("SAIZ"));
							   
							 if(fileSize >= (1024*1024))
							 {
							 h.put("saiz",(dnf.format((double)fileSize/(1024*1024)))+" MB");	 
							 }
							 else if(fileSize >= (1024))
							 {
							 h.put("saiz",(dnf.format((double)fileSize/1024))+" KB");		 
							 }
							 else
							 {
							 h.put("saiz",fileSize+" B");	 
							 }
							 myLogger.info("File size in bytes is: " + fileSize);
							 myLogger.info("File size in KB is : " + (double)fileSize/1024);
							 myLogger.info("File size in MB is :" + (double)fileSize/(1024*1024));
							
						/*	
						if (Long.parseLong(rs.getString("SAIZ")) < 1024){
						h.put("saiz",Long.parseLong(rs.getString("SAIZ"))+" B");
						}
						else if (Long.parseLong(rs.getString("SAIZ")) >= 1024 && Long.parseLong(rs.getString("SAIZ")) < (1024*1024)){
						
						long totalBytes = Long.parseLong(rs.getString("SAIZ"));	
						double line= ((double)totalBytes/(1024*1024));

						h.put("saiz",line+" KB");

						}*/
						}

						listDokumenMainInbox.addElement(h);

					}

					return listDokumenMainInbox;

				} finally {
					if (db != null)
						db.close();
				}

			}
			
			private void updateNotification(HttpSession session,String id_maininbox,String user_id,String flag) throws Exception {		 	
			 	Connection conn = null;
				Db db = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String sql="";	
				String sql1="";
				Vector check_notifikasi = null;			
				try {
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);				
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
							
							r.update("ID_MAININBOX",id_maininbox);
							r.update("ID_USER_NOTIFIKASI_TERIMA",user_id);						
							r.add("ID_USER_NOTIFIKASI_TERIMA",user_id);
							r.add("ID_MAININBOX", id_maininbox);
							r.add("FLAG_READ", flag);						
							r.add("ID_KEMASKINI",user_id);						
							r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
							sql1 = r.getSQLUpdate("TBLINBOXNOTIFIKASI");						
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
			
			
			private void setArkib(HttpSession session,String id_maininbox,String user_id,String flag) throws Exception {		 	
			 	Connection conn = null;
				Db db = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String sql="";	
				String sql1="";
				Vector check_notifikasi = null;			
				try {
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);				
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
							
							r.update("ID_MAININBOX",id_maininbox);
							r.update("USER_ID",user_id);						
							r.add("USER_ID",user_id);
							r.add("ID_MAININBOX", id_maininbox);
							r.add("FLAG_ARKIB", flag);						
							r.add("ID_KEMASKINI",user_id);						
							r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
							sql1 = r.getSQLUpdate("TBLUSERSINBOX");						
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
		 
			
			public void deleteMesej(String id_inboxmesej) throws Exception {
				Db db = null;
				Connection conn = null;
				String sql = "";
				try {
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					sql = "DELETE FROM TBLINBOXMESEJ WHERE ID_INBOXMESEJ = "
							+ id_inboxmesej;			
					stmt.executeUpdate(sql);
					
					sql = "DELETE FROM TBLINBOXATTACH WHERE ID_INBOXMESEJ = "
						+ id_inboxmesej	;			
					stmt.executeUpdate(sql);
					
					conn.commit();
					
				} finally {
					if (db != null)
						db.close();
				}
			}
			
			
		

}
