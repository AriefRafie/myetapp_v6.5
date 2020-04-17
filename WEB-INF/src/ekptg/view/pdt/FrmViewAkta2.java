package ekptg.view.pdt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.pdt.FrmModelAkta;

public class FrmViewAkta2 extends AjaxBasedModule {
	
	FrmModelAkta akta = null;

	private static Logger myLogger = Logger.getLogger(FrmViewAkta2.class);
	
	public String doTemplate2() throws Exception {
		akta = new FrmModelAkta();
		String vm =  "app/pdt/frmAkta.jsp";
		
		String idAkta = getParam("idAkta");
		this.context.put("idAkta", idAkta);
		
		Vector vPapar = akta.aktaPapar(idAkta);
		if (!vPapar.isEmpty()) {
			Hashtable hPapar = (Hashtable) vPapar.get(0);
			context.put("Akta_NoAkta", hPapar.get("Akta_NoAkta"));
			context.put("Akta_NamaAkta", hPapar.get("Akta_NamaAkta"));
			context.put("Akta_Catatan",hPapar.get("Akta_Catatan"));
		}
		
		boolean isPenggalExist = isExist("TBLPDTAKTAPENGGAL",idAkta);
		boolean isBahagianExist = isExist("TBLPDTAKTABAHAGIAN",idAkta);
		boolean isBabExist = isExist("TBLPDTAKTABAB",idAkta);
		
		
		this.context.put("isPenggalExist", isPenggalExist);
		this.context.put("isBahagianExist", isBahagianExist);
		this.context.put("isBabExist", isBabExist);
		
		
//		vPapar = akta.penggalList(idAkta);
//		context.put("List_Penggal", vPapar);
//		
//		vPapar = akta.bahagianList(idAkta);
//		context.put("List_Bahagian", vPapar);
//		
//		vPapar = akta.babList(idAkta);
//		context.put("List_Bab", vPapar);
//		
//		vPapar = akta.seksyenList(idAkta);
//		context.put("List_Seksyen", vPapar);
//	
//		vPapar = akta.subSeksyenList(idAkta);
//		context.put("List_SubSeksyen", vPapar);
//		
		vPapar = akta.JadualList(idAkta);
		context.put("List_Jadual", vPapar);
		
		
		/*
		this.context.put("idAkta",idAkta);
		vPapar = this.getBabLists(idAkta);
		context.put("List_Bab", vPapar);
		*/
		FrmViewAkta2 frmAkta = new FrmViewAkta2();
		this.context.put("akta",frmAkta);
		
		return vm;
	}
	
	public FrmViewAkta2() {
		
	}
	
	public String printThis() {
		return "this is what i want";
	}

	public Vector getBabLists(String idAktaBahagian) throws Exception {
		String sql = "";
		Db db = null;
		Vector v = null;
		Hashtable h = null;
		try {
			v = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "select id_aktabab,no_bab," +
					"tajuk_bab from tblpdtaktabab " +
					"where id_aktabahagian='"+idAktaBahagian+"' ORDER BY no_bab";
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				h = new Hashtable();
				h.put("id",Utils.isNull(rs.getString("id_aktabab")));
				h.put("no",Utils.isNull(rs.getString("no_bab")));
				h.put("tajuk",Utils.isNull(rs.getString("tajuk_bab")));
				v.add(h);
			}
			
		} catch (Exception e) {
			
		}finally {
			if (db != null) {	db.close();	}
		}
		return v;
	}
	
	public Vector getBabLists2(String idAkta) throws Exception {
		String sql = "";
		Db db = null;
		Vector v = null;
		Hashtable h = null;
		try {
			v = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "select id_akta,id_aktabab,no_bab," +
					"tajuk_bab from tblpdtaktabab " +
					"where id_akta='"+idAkta+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				h = new Hashtable();
				h.put("idAktaBab",Utils.isNull(rs.getString("id_aktabab")));
				h.put("NoBab",Utils.isNull(rs.getString("no_bab")));
				h.put("TajukBab",Utils.isNull(rs.getString("tajuk_bab")));
				v.add(h);
			}
			
		} catch (Exception e) {
			
		}finally {
			if (db != null) {	db.close();	}
		}
		return v;
	}
	
	//////////
	
	public Vector getPenggalLists(String idAkta) throws Exception {
		String sql = "";
		Db db = null;
		Vector v = null;
		Hashtable h = null;
		try {
			v = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			if ("null".equals(idAkta) || idAkta == null) {
				idAkta="";
			}
			sql = "select id_aktapenggal,no_penggal,tajuk_penggal " +
					"from tblpdtaktapenggal " +
					"where id_akta='"+idAkta+"' ORDER BY no_penggal";
			
			//myLogger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				h = new Hashtable();
				h.put("id",Utils.isNull(rs.getString("id_aktapenggal")));
				h.put("no",Utils.isNull(rs.getString("no_penggal")));
				h.put("tajuk",Utils.isNull(rs.getString("tajuk_penggal")));
				v.add(h);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (db != null) {	db.close();	}
		}
		return v;
	}
	///////////
	public Vector getBahagianLists(String idAktaPenggal) throws Exception {
		String sql = "";
		Db db = null;
		Vector v = null;
		Hashtable h = null;
		try {
			v = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			if ("null".equals(idAktaPenggal) || idAktaPenggal == null) {
				idAktaPenggal="";
			}
			sql = "select id_aktabahagian,no_bahagian,tajuk_bahagian " +
					"from tblpdtaktabahagian " +
					"where id_aktapenggal='"+idAktaPenggal+"' ORDER BY no_bahagian";
			
			//myLogger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				h = new Hashtable();
				h.put("id",Utils.isNull(rs.getString("id_aktabahagian")));
				h.put("no",Utils.isNull(rs.getString("no_bahagian")));
				h.put("tajuk",Utils.isNull(rs.getString("tajuk_bahagian")));
				v.add(h);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (db != null) {	db.close();	}
		}
		return v;
	}

	
	///////////
	
	public Vector getSeksyenLists(String idType,String id,String condition) throws Exception {
		String sql = "";
		Db db = null;
		Vector v = null;
		Hashtable h = null;
		try {
			v = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			if ("null".equals(id) || id == null) {
				id="";
			}
			sql = "select id_aktaseksyen,no_seksyen,tajuk_seksyen,seksyen,proviso from tblpdtaktaseksyen " +
					"where "+idType+" ='"+id+"' "+condition+" ORDER BY no_seksyen";
			
			myLogger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				h = new Hashtable();
				h.put("id",Utils.isNull(rs.getString("id_aktaseksyen")));
				h.put("no",Utils.isNull(rs.getString("no_seksyen")));
				h.put("tajuk",Utils.isNull(rs.getString("tajuk_seksyen")));
				h.put("Seksyen",Utils.isNull(rs.getString("seksyen")));
				h.put("Proviso",Utils.isNull(rs.getString("proviso")));
				v.add(h);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (db != null) {	db.close();	}
		}
		return v;
	}
	
	public Vector getSeksyenLists2(String idAktaBab) throws Exception {
		String sql = "";
		Db db = null;
		Vector v = null;
		Hashtable h = null;
		try {
			v = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			if ("null".equals(idAktaBab) || idAktaBab == null) {
				idAktaBab="";
			}
			sql = "select id_aktaseksyen,seksyen,proviso from tblpdtaktaseksyen " +
					"where id_aktabab='"+idAktaBab+"'";
			
			myLogger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				h = new Hashtable();
				h.put("idAktaSeksyen",Utils.isNull(rs.getString("id_aktaseksyen")));
				h.put("Seksyen",Utils.isNull(rs.getString("seksyen")));
				h.put("Proviso",Utils.isNull(rs.getString("proviso")));
				v.add(h);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (db != null) {	db.close();	}
		}
		return v;
	}
	////////
	public Vector getSubSeksyenLists(String idAktaSeksyen) throws Exception {
		String sql = "";
		Db db = null;
		Vector v = null;
		Hashtable h = null;
		try {
			v = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "select id_aktasubseksyen,nosub_seksyen,sub_seksyen,proviso from tblpdtaktasubseksyen " +
					"where id_aktaseksyen='"+idAktaSeksyen+"'";
			
			//myLogger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				h = new Hashtable();
				h.put("idAktaSubSeksyen",Utils.isNull(rs.getString("id_aktasubseksyen")));
				h.put("NoSubSeksyen",Utils.isNull(rs.getString("nosub_seksyen")));
				h.put("SubSeksyen",Utils.isNull(rs.getString("sub_seksyen")));
				h.put("Proviso",Utils.isNull(rs.getString("proviso")));
				v.add(h);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (db != null) {	db.close();	}
		}
		return v;
	}
	
	public Vector getJadualLampiranList(String ID_Jadual) throws Exception {
		String sql = "";
		Db db = null;
		Vector v = new Vector();
		Hashtable h = null;
		
		try {
			if (!"".equalsIgnoreCase(ID_Jadual)) {
				int iCount = 1;
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
	
				String RS_IDJadualLampiran="",RS_NamaJadualLampiran = "",RS_NoAkta;
				sql = "select b.id_jaduallampiran,a.nama_jadual,b.catatan,b.tajuk from " +
						"tblpdtjadual a,tblpdtjaduallampiran b " +
						"where a.id_jadual = b.id_jadual "+
						"AND a.id_jadual = '" + ID_Jadual + "' ORDER BY b.TARIKH_MASUK,a.nama_jadual asc";
				//myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					RS_IDJadualLampiran = Utils.isNull(rs.getString(1));
					RS_NamaJadualLampiran = Utils.isNull(rs.getString(2));
					h = new Hashtable();
					h.put("No", iCount);
					h.put("id", RS_IDJadualLampiran);
					h.put("nama", RS_NamaJadualLampiran);
					h.put("catatan", Utils.isNull(rs.getString(3)));
					h.put("tajuk", Utils.isNull(rs.getString(4)));
					v.addElement(h);
					iCount++;
				}
			}
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}

	
	public Vector getJadualLampiranFailList(String ID_JadualLampiran) throws Exception {
		String sql = "";
		Db db = null;
		Vector v = new Vector();
		Hashtable h = null;
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				sql = "select id_jaduallampiran_fail,nama_lampiran from tblpdtjaduallampiran_fail " +
						"WHERE ID_JADUALLAMPIRAN='"+ID_JadualLampiran+"' ORDER BY tarikh_masuk asc";
				//myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					h = new Hashtable();
					h.put("id",Utils.isNull(rs.getString("id_jaduallampiran_fail")));
					h.put("nama_lampiran",Utils.isNull(rs.getString("nama_lampiran")));
					v.addElement(h);
				}
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	
	
	public boolean isExist(String table,String idAkta) throws Exception {
		boolean output = false;
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			sql = "SELECT COUNT(*) as Total From  "+table+" WHERE id_akta='"+idAkta+"' ";
			
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				if (rs.getInt("Total") > 0) {
					output = true;
				}
			}
			
		} catch (Exception e) {
			
		} finally {
			if (db != null) db.close();
		}
		//myLogger.debug("is exist "+output+";"+sql);
		return output;
		
	}
	
}
