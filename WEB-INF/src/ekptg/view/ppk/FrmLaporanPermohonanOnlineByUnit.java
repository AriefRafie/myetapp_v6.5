package ekptg.view.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.FrmSenaraiFailOnlineData;

public class FrmLaporanPermohonanOnlineByUnit extends AjaxBasedModule {
	
	static Logger myLogger = Logger.getLogger(FrmLaporanPermohonanOnlineByUnit.class);

	FrmSenaraiFailOnlineData logic_E = new FrmSenaraiFailOnlineData();
	
	List list = null;
	List listPejabat = null;

	@Override
	public String doTemplate2() throws Exception {
		
		
		HttpSession session = this.request.getSession();
		String userId = (String)session.getAttribute("_ekptg_user_id");
		
		String vm = "app/ppk/frmLaporanPermohonanOnlineByUnit.jsp";
	
		String action = getParam("action"); 
		String command = getParam("command");
		myLogger.info("Command :"+command);
		
		list = Collections.synchronizedList(new ArrayList());
		listPejabat = Collections.synchronizedList(new ArrayList());
		
		this.context.put("usid", userId);
		this.context.put("no_fail", "");
		this.context.put("myid", "");
		this.context.put("seksyen", "");
		this.context.put("nama", "");
		this.context.put("nama_pejabat", "");
		
		String no_fail = "";
		String myid = "";
		String seksyen = "";
		String nama = "";
		String nama_pejabat = "";
		
		if(command.equals("Cari"))
		{
			this.context.put("no_fail", getParam("no_fail"));
			this.context.put("myid", getParam("myid"));
			this.context.put("seksyen", getParam("seksyen"));
			this.context.put("nama", getParam("nama"));
			this.context.put("nama_pejabat", getParam("pejabat"));
			no_fail = getParam("no_fail");
			myid = getParam("myid");
			seksyen = getParam("seksyen");
			nama = getParam("nama");
			nama_pejabat = getParam("pejabat");
			
		}
		
		
		list = logic_E.setListOnlineUtilitiByUnit(userId,no_fail,myid,seksyen,nama,nama_pejabat);	
		listPejabat = setListPejabat(userId);

		this.context.put("SenaraiFail", list);
		this.context.put("ListPejabat", listPejabat);
		setupPage(session,action,list);
		
	
		return vm;
	}
	
	public static Vector setListPejabat(String userid) throws Exception{
		Db db = null;
		Vector list_online = null;
		String sql = "";
		try {
			db = new Db();
			list_online = new Vector();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_PEJABATJKPTG,A.NAMA_PEJABAT "+
				  " FROM TBLRUJPEJABATJKPTG A "+
				  " WHERE "+
				  " A.ID_NEGERI IN ( "+
				  " SELECT DISTINCT U.ID_NEGERIURUS "+
                      " FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "+
                      " WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG "+
                      " AND UR.USER_ID = '"+userid+"') "+
                  " AND A.ID_JENISPEJABAT = 21 "+
                  " AND A.ID_SEKSYEN = 2";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h = null;
			int bil = 0;
			
			while (rs.next()){
				h = new Hashtable();
				bil++;
				h.put("ID_PEJABATJKPTG", rs.getString("ID_PEJABATJKPTG")==null?"":rs.getString("ID_PEJABATJKPTG"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT")==null?"":rs.getString("NAMA_PEJABAT"));
				list_online.addElement(h);
			}
			return list_online;
		}finally {
			if(db != null) db.close();
			
		}
	
	}

}