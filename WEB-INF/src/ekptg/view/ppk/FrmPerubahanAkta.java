/**
 * 25/08/2020    
 *
 */

package ekptg.view.ppk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8Notis;
import ekptg.model.ppk.FrmSenaraiFailSek8ForViewData;
import ekptg.model.ppk.FrmTukaranStatus; 
import ekptg.model.ppk.PPKUtilHTML;

/**
 * @author Salnizam 
 *
 */
public class FrmPerubahanAkta extends AjaxBasedModule{

	private static final long serialVersionUID = 1L;
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	FrmSenaraiFailSek8ForViewData logic = null;
	static Logger myLogger = Logger.getLogger(FrmSenaraiFailSek8ForView.class);
	HttpSession session = null;
	String action = null;
	String role = null;
	FrmPrmhnnSek8Notis modelNotis = new FrmPrmhnnSek8Notis();
	
	@Override
	public String doTemplate2() throws Exception {
	
		session = request.getSession();
		Vector listOB = new Vector();
		Vector listPra = new Vector();
		String bolehsimpan = "";
		Vector alamatTempatBicara = new Vector();
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {		
			bolehsimpan = "yes";
		}
		myLogger.info("bolehsimpan :"+bolehsimpan);
		
		FrmTukaranStatus model = new FrmTukaranStatus();     
		FrmSenaraiFailSek8ForViewData logic = new FrmSenaraiFailSek8ForViewData();
		String usid=(String)session.getAttribute("_ekptg_user_id");
		String vm = "app/ppk/frmPerubahanAkta.jsp";
		role = (String)session.getAttribute("myrole");
		
		
		Hashtable perubahan_akta = (Hashtable) getPerubahanAkta();
		context.put("FLAG_EMAIL_PEMOHON",(String)perubahan_akta.get("FLAG_EMAIL_PEMOHON"));		
		context.put("FLAG_NOTELEFONBIMBIT_PEMOHON",(String)perubahan_akta.get("FLAG_NOTELEFONBIMBIT_PEMOHON"));
		context.put("FLAG_EMAIL_WARIS",(String)perubahan_akta.get("FLAG_EMAIL_WARIS"));
		context.put("FLAG_5JUTA",(String)perubahan_akta.get("FLAG_5JUTA"));
		context.put("AKSES_SKRIN_KEPBICARA",(String)perubahan_akta.get("AKSES_SKRIN_KEPBICARA"));
		context.put("FLAG_KEMASKINIPERINTAH_PT",(String)perubahan_akta.get("FLAG_KEMASKINIPERINTAH_PT"));
		
		String submit = getParam("command");
		action = getParam("action");
    	myLogger.info("[submit] :: " +submit);
    	String command2= getParam("command2");
    	String command3= getParam("command3");
    	
    	
    	
		

		
    	Vector list = new Vector();
    	
    	
		if("Kemaskini".equals(submit)){
			context.put("disabled","");
			context.put("buttonKemaskini", "no");
			context.put("buttonSimpan", "yes");
		}
		
		else if ("simpan".equals(submit)){
			String emelPemohon= getParam("emelPemohon");
			String hphone= getParam("hphone");
			String emelWaris= getParam("emelWaris");
			String flag5juta= getParam("flag5juta");
			String aksesSkrinKepBicara= getParam("flagAksesKepBicara");
			String flagKemaskiniPerintahPT= getParam("flagKemaskiniPerintahPT");
			
			setFlagPerubahanAkta(emelPemohon,hphone,emelWaris,flag5juta,aksesSkrinKepBicara,flagKemaskiniPerintahPT);
			context.put("disabled","disabled");
			context.put("buttonKemaskini", "yes");
			context.put("buttonSimpan", "no");
			
			perubahan_akta = (Hashtable) getPerubahanAkta();
			context.put("FLAG_EMAIL_PEMOHON",(String)perubahan_akta.get("FLAG_EMAIL_PEMOHON"));		
			context.put("FLAG_NOTELEFONBIMBIT_PEMOHON",(String)perubahan_akta.get("FLAG_NOTELEFONBIMBIT_PEMOHON"));
			context.put("FLAG_EMAIL_WARIS",(String)perubahan_akta.get("FLAG_EMAIL_WARIS"));
			context.put("FLAG_5JUTA",(String)perubahan_akta.get("FLAG_5JUTA"));
			context.put("AKSES_SKRIN_KEPBICARA",(String)perubahan_akta.get("AKSES_SKRIN_KEPBICARA"));
			context.put("FLAG_KEMASKINIPERINTAH_PT",(String)perubahan_akta.get("FLAG_KEMASKINIPERINTAH_PT"));
			
		}
		
		
		 
		else{
			context.put("disabled","disabled");
			context.put("buttonKemaskini", "yes");
			context.put("buttonSimpan", "no");
		}
		return vm;
	}
	
	
	public Hashtable getPerubahanAkta() throws Exception {
		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT * FROM TBLPPKPERUBAHANAKTA";		
				
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("FLAG_EMAIL_PEMOHON") == null) {
					h.put("FLAG_EMAIL_PEMOHON", "");
				} else {
					h.put("FLAG_EMAIL_PEMOHON", rs.getString("FLAG_EMAIL_PEMOHON"));
				}
				if (rs.getString("FLAG_NOTELEFONBIMBIT_PEMOHON") == null) {
					h.put("FLAG_NOTELEFONBIMBIT_PEMOHON", "");
				} else {
					h.put("FLAG_NOTELEFONBIMBIT_PEMOHON", rs.getString("FLAG_NOTELEFONBIMBIT_PEMOHON"));
				}
				if (rs.getString("FLAG_EMAIL_WARIS") == null) {
					h.put("FLAG_EMAIL_WARIS", "");
				} else {
					h.put("FLAG_EMAIL_WARIS", rs.getString("FLAG_EMAIL_WARIS"));
				}	
				if (rs.getString("FLAG_5JUTA") == null) {
					h.put("FLAG_5JUTA", "");
				} else {
					h.put("FLAG_5JUTA", rs.getString("FLAG_5JUTA"));
				}	
				if (rs.getString("AKSES_SKRIN_KEPBICARA") == null) {
					h.put("AKSES_SKRIN_KEPBICARA", "");
				} else {
					h.put("AKSES_SKRIN_KEPBICARA", rs.getString("AKSES_SKRIN_KEPBICARA"));
				}	
				if (rs.getString("FLAG_KEMASKINIPERINTAH_PT") == null) {
					h.put("FLAG_KEMASKINIPERINTAH_PT", "");
				} else {
					h.put("FLAG_KEMASKINIPERINTAH_PT", rs.getString("FLAG_KEMASKINIPERINTAH_PT"));
				}	
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	
	

	
	private void setFlagPerubahanAkta(String emelPemohon,String hphone,String emelWaris,String flag5juta, String aksesSkrinKepBicara, String flagKemaskiniPerintahPT) throws Exception {		 	
	 	Connection conn = null;
		Db db = null;			
		String sql1="";		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();					
					r.update("ID_PERUBAHAN","1");
					r.add("FLAG_EMAIL_PEMOHON",emelPemohon);
					r.add("FLAG_NOTELEFONBIMBIT_PEMOHON",hphone);
					r.add("FLAG_EMAIL_WARIS",emelWaris);
					r.add("FLAG_5JUTA",flag5juta);
					r.add("AKSES_SKRIN_KEPBICARA",aksesSkrinKepBicara);
					r.add("FLAG_KEMASKINIPERINTAH_PT",flagKemaskiniPerintahPT);
											
					sql1 = r.getSQLUpdate("TBLPPKPERUBAHANAKTA");
					myLogger.info("UPDATE FLAG PERUBAHAN AKTA:"+sql1);
					stmt.executeUpdate(sql1);			
		conn.commit();
	} catch (SQLException se) {
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Flag Perubahan Akta:" + se.getMessage());
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
			this.context.put("flag_jenis_vm", "utiliti_ajax");
		}

}
