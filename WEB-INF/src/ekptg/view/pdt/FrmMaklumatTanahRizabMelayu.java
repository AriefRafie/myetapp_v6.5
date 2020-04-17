package ekptg.view.pdt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.pdt.FrmModelTanahRizabData;

public class FrmMaklumatTanahRizabMelayu extends AjaxBasedModule {
	private static Logger myLog = Logger
			.getLogger(ekptg.view.pdt.FrmMaklumatTanahRizabMelayu.class);
	FrmModelTanahRizabData tanahRizab = new FrmModelTanahRizabData();

	@SuppressWarnings("unchecked")
	Vector vCarian = null;
	Vector vPapar = null;
	Hashtable hPapar = null;

	String RO_General = "";
	String action = "";
	String vm = "";

	// /WARTA PEWUJUDAN
	String Wujud_IdWujud = "";
	String Wujud_IdNegeri = "";
	String Wujud_IdDaerah = "";
	String Wujud_IdMukim = "";
	String Wujud_Kawasan = "";
	String Wujud_NoWarta = "";
	String Wujud_Tarikh = "";
	String Wujud_NoPelan = "";
	String Wujud_Luas = "";
	String Wujud_Status = "";

	// /WARTA PEMBATALAN
	String Batal_IdBatal = "";
	String Batal_IdNegeri = "";
	String Batal_IdDaerah = "";
	String Batal_IdMukim = "";
	String Batal_Kawasan = "";
	String Batal_NoWarta = "";
	String Batal_Tarikh = "";
	String Batal_NoPelan = "";
	String Batal_Luas = "";
	String Batal_Status = "";

	// /WARTA PENGGANTIAN
	String Ganti_IdGanti = "";
	String Ganti_IdNegeri = "";
	String Ganti_IdDaerah = "";
	String Ganti_IdMukim = "";
	String Ganti_Kawasan = "";
	String Ganti_NoWarta = "";
	String Ganti_Tarikh = "";
	String Ganti_NoPelan = "";
	String Ganti_Luas = "";
	String Ganti_Status = "";

	String paramNegeri = "";
	String paramDaerah = "";
	String paramMukim = "";
	String paramKawasan = "";
	String paramNoWarta = "";
	String paramTarikh = "";
	String paramNoPelan = "";
	String paramLuas = "";
	String paramStatus = "";

	public String doTemplate2() throws Exception {
		String vm = "";
		action = getParam("action");
		String command = getParam("command");
		myLog.debug("REAL ACTION:" + action);
		String selectedTab = getParam("selectedTab");
		if ("".equalsIgnoreCase(selectedTab)) {
			selectedTab = "0";
		}

		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		HttpSession session = this.request.getSession();
		String user_name = (String) session.getAttribute("_portal_username");
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		String MAIN_LEGEND = "MAKLUMAT TANAH RIZAB MELAYU";
		RO_General = "readonly=\"readonly\"";

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP
											// PAGING SHJ
		String submit = getParam("command");
		String step = getParam("step");
		String hitButton = getParam("hitButton");

		// ----------------------------------------------
		// get parameters


		// ----------------------------------------------
		// processing
		vCarian = new Vector();
		vPapar = new Vector();
		hPapar = new Hashtable();
		// setUploadFile(false);
		if ("".equalsIgnoreCase(selectedTab)) {
			selectedTab = "";
			vm = "app/pdt/trm/frmMaklumatTanahRizabMelayu.jsp";
			;
			Hashtable hmaklumatWarta = new Hashtable();
			hmaklumatWarta.put("idWujud", Wujud_IdWujud);
			hmaklumatWarta.put("paramNegeri", Wujud_IdNegeri);
			hmaklumatWarta.put("paramDaerah", Wujud_IdDaerah);

			/*tanahRizab.wartaWujud(paramNegeri, paramDaerah, paramMukim,
					paramKawasan, paramNoWarta, paramTarikh, paramNoPelan,
					paramLuas, paramStatus);*/
		}
		else if ("0".equalsIgnoreCase(selectedTab)) {
			selectedTab = "0";
			vm = "app/pdt/trm/frmWartaWujud.jsp";
			;
			Hashtable hmaklumatWarta = new Hashtable();
			hmaklumatWarta.put("idWujud", Wujud_IdWujud);
			hmaklumatWarta.put("paramNegeri", Wujud_IdNegeri);
			hmaklumatWarta.put("paramDaerah", Wujud_IdDaerah);

			/*tanahRizab.wartaWujud(paramNegeri, paramDaerah, paramMukim,
					paramKawasan, paramNoWarta, paramTarikh, paramNoPelan,
					paramLuas, paramStatus);*/
		} else if ("1".equalsIgnoreCase(selectedTab)) {
			selectedTab = "1";
			vm = "app/pdt/trm/frmWartaBatal.jsp";
			;
			Hashtable hmaklumatWarta = new Hashtable();
			hmaklumatWarta.put("idWujud", Wujud_IdWujud);
			hmaklumatWarta.put("paramNegeri", Wujud_IdNegeri);
			hmaklumatWarta.put("paramDaerah", Wujud_IdDaerah);

			/*tanahRizab.wartaWujud(paramNegeri, paramDaerah, paramMukim,
					paramKawasan, paramNoWarta, paramTarikh, paramNoPelan,
					paramLuas, paramStatus);*/
		} else if ("2".equalsIgnoreCase(selectedTab)) {
			selectedTab = "2";
			vm = "app/pdt/trm/frmWartaGanti.jsp";
			;
			Hashtable hmaklumatWarta = new Hashtable();
			hmaklumatWarta.put("idWujud", Wujud_IdWujud);
			hmaklumatWarta.put("paramNegeri", Wujud_IdNegeri);
			hmaklumatWarta.put("paramDaerah", Wujud_IdDaerah);

			/*tanahRizab.wartaWujud(paramNegeri, paramDaerah, paramMukim,
					paramKawasan, paramNoWarta, paramTarikh, paramNoPelan,
					paramLuas, paramStatus);*/
		}
		context.put("selectedTab", selectedTab);
		context.put("action", action);
		context.put("RO_General", RO_General);
		myLog.debug("action:" + this.context.get("action"));
		myLog.debug("selectedTab:" + selectedTab);
		
		return vm;
	}

	public void debugParams() {
		Enumeration allparam = request.getParameterNames();
		String name = "";
		String value = "";
		for (; allparam.hasMoreElements();) {
			// Get the name of the request parameter
			name = (String) allparam.nextElement();
			// Get the value of the request parameter
			value = request.getParameter(name);
			// System.out.println(name +"="+value);
			myLog.debug(name + "=" + value);
		}
	}

	public String getNegeri(String id_negeri) throws Exception {
		Db db = null;
		String sql = "";
		String output = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			if (id_negeri != null && id_negeri.length() > 0) {
				sql = "Select upper(nama_negeri) as nama_negeri from tblRujNegeri where id_negeri='"
						+ id_negeri + "'";
			} else {
				sql = "Select upper(nama_negeri) as nama_negeri from tblRujNegeri";
			}
			//
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				output = rs.getString("nama_negeri");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (db != null)
				db.close();
		}
		return "<input type=hidden name='id_negeri' value='" + id_negeri + "'>"
				+ output;
	}

	public String getDaerah(String id_daerah) throws Exception {
		Db db = null;
		String sql = "";
		String output = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			if (id_daerah != null && id_daerah.length() > 0) {
				sql = "Select upper(nama_daerah) as nama_daerah from tblRujDaerah where id_daerah='"
						+ id_daerah + "'";
			} else {
				sql = "Select upper(nama_daerah) as nama_daerah from tblRujDaerah";
			}
			//
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				output = rs.getString("nama_daerah");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (db != null)
				db.close();
		}
		return "<input type=hidden name='id_daerah' value='" + id_daerah + "'>"
				+ output;
	}

	public String getMukim(String id_mukim) throws Exception {
		Db db = null;
		String sql = "";
		String output = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			if (id_mukim != null && id_mukim.length() > 0) {
				sql = "Select upper(nama_mukim) as nama_mukim from tblRujMukim where id_mukim='"
						+ id_mukim + "'";
			} else {
				sql = "Select upper(nama_mukim) as nama_mukim from tblRujMukim";
			}
			//
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				output = rs.getString("nama_mukim");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (db != null)
				db.close();
		}
		return "<input type=hidden name='id_mukim' value='" + id_mukim + "'>"
				+ output;
	}

	public String getStatus(String id_tblpdtwarta) throws Exception {
		Db db = null;
		String sql = "";
		String output = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			if (id_tblpdtwarta != null && id_tblpdtwarta.length() > 0) {
				sql = "Select upper(flag_statuswarta) as flag_statuswarta from tblPdtWarta where ID_TBLPDTWARTA=ID_TBLPDTWARTA";
			} else {
				sql = "Select upper(flag_statuswarta) as flag_statuswarta from tblPdtWarta";
			}
			//
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				output = rs.getString("flag_statuswarta");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (db != null)
				db.close();
		}
		return "<input type=hidden name='flag_statuswarta' value='"
				+ id_tblpdtwarta + "'>" + output;
	}

}
