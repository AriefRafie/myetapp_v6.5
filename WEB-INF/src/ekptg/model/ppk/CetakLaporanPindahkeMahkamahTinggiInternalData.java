package ekptg.model.ppk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

/*import lebah.pm.entity.ActivityEvent;
 import lebah.pm.entity.UserActivityEvent;
 import lebah.portal.action.Command;
 import lebah.template.DbPersistence;*/

public class CetakLaporanPindahkeMahkamahTinggiInternalData {

	static Logger myLogger = Logger.getLogger(CetakLaporanPindahkeMahkamahTinggiInternalData.class);

	private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");

	// seksyen 8 (ada campur utk seksyen 17)
	private static Vector list = new Vector();
	private static Vector listOB = new Vector();
	private static Vector listSemuaOB = new Vector();
	private static Vector listOB18 = new Vector();
	private static Vector listDefault = new Vector();
	private static Vector listSemak = new Vector();
	private static Vector listSemakWithData = new Vector();
	private static Vector listBicara = new Vector();
	private static Vector listPenjaga = new Vector();
	private static Vector penjaga1 = new Vector();
	private static Vector penjaga2 = new Vector();
	private static Vector penjaga3 = new Vector();
	private static Vector penjaga4 = new Vector();
	private static Vector MaklumatPenjaga = new Vector();
	private static Vector listDataPenjaga = new Vector();
	private static Vector listPenerimaNotis = new Vector();
	private static Vector listDataOBNotis = new Vector();
	private static Vector selectedOB = new Vector();
	private static Vector selectedDropdown = new Vector();
	private static Vector selectedDropdownWithPenjaga = new Vector();
	private static Vector listOBatas18 = new Vector();
	private static Vector listOBatas18Semua = new Vector();
	private static Vector listKPPenjaga = new Vector();
	private static Vector penghantarNotisByJkptg = new Vector();
	private static Vector penghantarNotis = new Vector();
	private static Vector listOBsemak = new Vector();

	// -- 23122009
	private static Vector selectedOB17 = new Vector();

	// -- 08122009
	private static Vector checkCetakAkuanSumpah = new Vector();

	// seksyen 17
	private static Vector listSek17 = new Vector();
	private static Vector listDefaultSek17 = new Vector();
	private static Vector SenaraiSerahan = new Vector();

	/** ADD BY PEJE - SENARAI PEMIUTANG **/
	private static Vector listPemiutang = new Vector();

	/** ADD BY PEJE - SENARAI PEMIUTANG **/
	public static Vector getListPemiutang() {
		return listPemiutang;
	}
	
	/** ADD BY PEJE - ID_PERBICARAAN **/
	private static String idPerbicaraan = "";
	
	public static String getIdPerbicaraan() {
		return idPerbicaraan;
	}

	// seksyen 8 (ada campur utk seksyen 17)
	public static Vector getListCarian() {
		return list;
	}

	public static Vector getListOBatas18() {
		return listOBatas18;
	}

	public static Vector getListOBatas18Semua() {
		return listOBatas18Semua;
	}

	public static Vector getDataPenjaga() {
		return listDataPenjaga;
	}

	public static Vector getMaklumatPenjaga() {
		return MaklumatPenjaga;
	}

	public static Vector getListPenjaga() {
		return listPenjaga;
	}

	public static Vector getPenjaga1() {
		return penjaga1;
	}

	public static Vector getPenjaga2() {
		return penjaga2;
	}

	public static Vector getPenjaga3() {
		return penjaga3;
	}

	public static Vector getPenjaga4() {
		return penjaga4;
	}

	public static Vector getListDefault() {
		return listDefault;
	}

	public static Vector getListSemak() {
		return listSemak;
	}

	public static Vector getListOB() {
		return listOB;
	}

	public static Vector getSelectedOB() {
		return selectedOB;
	}

	public static Vector getSelectedDropdown() {
		return selectedDropdown;
	}

	public static Vector getListPenerimaNotis() {
		return listPenerimaNotis;
	}

	public static Vector getDataOBNotis() {
		return listDataOBNotis;
	}

	public static Vector getListOB18() {
		return listOB18;
	}

	public static Vector getListSemakWithData() {
		return listSemakWithData;
	}

	public static Vector getListBicara() {
		return listBicara;
	}

	public static Vector getSelectedDropdownWithPenjaga() {
		return selectedDropdownWithPenjaga;
	}

	public static Vector getListSemuaOB() {
		return listSemuaOB;
	}

	public static Vector getPenghantarNotisByJkptg() {
		return penghantarNotisByJkptg;
	}

	public static Vector getPenghantarNotis() {
		return penghantarNotis;
	}

	public static Vector getListOBsemak() {
		return listOBsemak;
	}

	// -- 08122009
	public static Vector getCheckCetakAkuanSumpah() {
		return checkCetakAkuanSumpah;
	}

	// -- 23122009
	public static Vector getSelectedOB17() {
		return selectedOB17;
	}

	// seksyen 17
	public static Vector getListCarianSek17() {
		return listSek17;
	}

	public static Vector getListDefaultSek17() {
		return listDefaultSek17;
	}
	
	public static Vector getSenaraiLaporanSerahan() {
		return SenaraiSerahan;
	}

	// List permohonan Seksyen 8
	public static void setDataSenaraiSerahan(String mula_hari, String mula_bulan, String mula_tahun, String akhir_hari, String akhir_bulan, String akhir_tahun, String jenis_report) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		String tarikh_mula = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
		String tarikh_akhir = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun;
		
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where b.tarikh_mohon BETWEEN TO_DATE('"+tarikh_mula+"', 'DD/MM/YYYY') AND TO_DATE('"+tarikh_akhir+"', 'DD/MM/YYYY') ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=50 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi1 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }

		    
			}	
	
	public static void setDataSenaraiSerahanUnit(String unit) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		{
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=50 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi1 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
 
			}	

}// close class
	
	public static void setDataSenaraiSerahanUnitTahun(String unit, String tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		{
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=50 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and TO_CHAR(b.TARIKH_MOHON,'yyyy') ="+tahun;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi1 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
 
			}	

}// close class
	
	public static void setDataSenaraiSerahanUnitSelangmasa(String unit, String tarikh_mula, String tarikh_akhir) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		{
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=50 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND b.TARIKH_MOHON between TO_DATE('"+tarikh_mula+"','dd/mm/yyyy') and TO_DATE('"+tarikh_akhir+"','dd/mm/yyyy')";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi1 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
 
			}	

}// close class
	
	public static void setDataSenaraiSerahanUnitBulan(String unit, String mula_bulan, String mula_tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		{
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=50 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND TO_CHAR(b.TARIKH_MOHON,'mm') = '"+mula_bulan+"'";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND TO_CHAR(b.TARIKH_MOHON,'yyyy') = '"+mula_tahun+"'";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi1 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
 
			}	

}// close class
	
	public static void setDataSenaraiSerahanNegeri(String negeri) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		{
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=50 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_NEGERIMHN ="+negeri;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi1 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
 
			}	

}// close class
	
	public static void setDataSenaraiSerahanNegeriTahun(String negeri, String tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		{
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=50 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_NEGERIMHN ="+negeri;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and TO_CHAR(b.TARIKH_MOHON,'yyyy') ="+tahun;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi1 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
 
			}	

}// close class
	
	public static void setDataSenaraiSerahanNegeriSelangmasa(String negeri, String tarikh_mula, String tarikh_akhir) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		{
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=50 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_NEGERIMHN ="+negeri;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND b.TARIKH_MOHON between TO_DATE('"+tarikh_mula+"','dd/mm/yyyy') and TO_DATE('"+tarikh_akhir+"','dd/mm/yyyy')";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi1 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
 
			}	

}// close class
	
	public static void setDataSenaraiSerahanNegeriBulan(String negeri, String mula_bulan, String mula_tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		{
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=50 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_NEGERIMHN ="+negeri;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND TO_CHAR(b.TARIKH_MOHON,'mm') = '"+mula_bulan+"'";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND TO_CHAR(b.TARIKH_MOHON,'yyyy') = '"+mula_tahun+"'";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi1 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
 
			}	

}// close class
	
	public static void setDataSenaraiSerahanDaerah(String daerah) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		{
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=50 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_DAERAHMHN ="+daerah;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi1 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
 
			}	

}// close class
	
	public static void setDataSenaraiSerahanDaerahTahun(String daerah, String tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		{
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=50 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_DAERAHMHN ="+daerah;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and TO_CHAR(b.TARIKH_MOHON,'yyyy') ="+tahun;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi1 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
 
			}	

}// close class
	
	public static void setDataSenaraiSerahanDaerahSelangmasa(String daerah, String tarikh_mula, String tarikh_akhir) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		{
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=50 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_DAERAHMHN ="+daerah;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND b.TARIKH_MOHON between TO_DATE('"+tarikh_mula+"','dd/mm/yyyy') and TO_DATE('"+tarikh_akhir+"','dd/mm/yyyy')";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi1 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
 
			}	

}// close class
	
	public static void setDataSenaraiSerahanDaerahBulan(String daerah, String mula_bulan, String mula_tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		{
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=50 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_DAERAHMHN ="+daerah;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND TO_CHAR(b.TARIKH_MOHON,'mm') = '"+mula_bulan+"'";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND TO_CHAR(b.TARIKH_MOHON,'yyyy') = '"+mula_tahun+"'";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi1 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
 
			}	

}// close class
	
	public static void setDataSenaraiWasiatSerahanUnit(String unit) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		
		{
			try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf, tblppkperintah i, tblppkperbicaraan j, tblppkkeputusanpermohonan k ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_permohonan=k.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and k.id_keputusanpermohonan=j.id_keputusanpermohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and j.id_perbicaraan=i.id_perbicaraan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=47 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and i.flag_batal=1 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi2 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
		}

}// close class
	
	public static void setDataSenaraiWasiatSerahanUnitTahun(String unit, String tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		
		{
			try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf, tblppkperintah i, tblppkperbicaraan j, tblppkkeputusanpermohonan k ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_permohonan=k.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and k.id_keputusanpermohonan=j.id_keputusanpermohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and j.id_perbicaraan=i.id_perbicaraan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=47 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and i.flag_batal=1 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and TO_CHAR(b.TARIKH_MOHON,'yyyy') ="+tahun;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi2 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
		}

}// close class
	
	public static void setDataSenaraiWasiatSerahanUnitSelangmasa(String unit, String tarikh_mula, String tarikh_akhir) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		
		{
			try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf, tblppkperintah i, tblppkperbicaraan j, tblppkkeputusanpermohonan k ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_permohonan=k.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and k.id_keputusanpermohonan=j.id_keputusanpermohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and j.id_perbicaraan=i.id_perbicaraan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=47 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and i.flag_batal=1 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND b.TARIKH_MOHON between TO_DATE('"+tarikh_mula+"','dd/mm/yyyy') and TO_DATE('"+tarikh_akhir+"','dd/mm/yyyy')";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi2 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
		}

}// close class
	
	public static void setDataSenaraiWasiatSerahanUnitBulan(String unit, String mula_bulan, String mula_tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		
		{
			try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf, tblppkperintah i, tblppkperbicaraan j, tblppkkeputusanpermohonan k ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_permohonan=k.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and k.id_keputusanpermohonan=j.id_keputusanpermohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and j.id_perbicaraan=i.id_perbicaraan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=47 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and i.flag_batal=1 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND TO_CHAR(b.TARIKH_MOHON,'mm') = '"+mula_bulan+"'";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND TO_CHAR(b.TARIKH_MOHON,'yyyy') = '"+mula_tahun+"'";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi2 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
		}

}// close class
	
	public static void setDataSenaraiWasiatSerahanNegeri(String negeri) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		
		{
			try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf, tblppkperintah i, tblppkperbicaraan j, tblppkkeputusanpermohonan k ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_permohonan=k.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and k.id_keputusanpermohonan=j.id_keputusanpermohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and j.id_perbicaraan=i.id_perbicaraan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=47 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and i.flag_batal=1 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_NEGERIMHN ="+negeri;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi2 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
		}

}// close class
	
	public static void setDataSenaraiWasiatSerahanNegeriTahun(String negeri, String tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		
		{
			try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf, tblppkperintah i, tblppkperbicaraan j, tblppkkeputusanpermohonan k ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_permohonan=k.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and k.id_keputusanpermohonan=j.id_keputusanpermohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and j.id_perbicaraan=i.id_perbicaraan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=47 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and i.flag_batal=1 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_NEGERIMHN ="+negeri;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and TO_CHAR(b.TARIKH_MOHON,'yyyy') ="+tahun;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi2 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
		}

}// close class
	
	public static void setDataSenaraiWasiatSerahanNegeriSelangmasa(String negeri, String tarikh_mula, String tarikh_akhir) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		
		{
			try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf, tblppkperintah i, tblppkperbicaraan j, tblppkkeputusanpermohonan k ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_permohonan=k.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and k.id_keputusanpermohonan=j.id_keputusanpermohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and j.id_perbicaraan=i.id_perbicaraan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=47 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and i.flag_batal=1 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_NEGERIMHN ="+negeri;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND b.TARIKH_MOHON between TO_DATE('"+tarikh_mula+"','dd/mm/yyyy') and TO_DATE('"+tarikh_akhir+"','dd/mm/yyyy')";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi2 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
		}

}// close class
	
	public static void setDataSenaraiWasiatSerahanNegeriBulan(String negeri, String mula_bulan, String mula_tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		
		{
			try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf, tblppkperintah i, tblppkperbicaraan j, tblppkkeputusanpermohonan k ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_permohonan=k.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and k.id_keputusanpermohonan=j.id_keputusanpermohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and j.id_perbicaraan=i.id_perbicaraan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=47 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and i.flag_batal=1 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_NEGERIMHN ="+negeri;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND TO_CHAR(b.TARIKH_MOHON,'mm') = '"+mula_bulan+"'";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND TO_CHAR(b.TARIKH_MOHON,'yyyy') = '"+mula_tahun+"'";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi2 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
		}

}// close class
	
	public static void setDataSenaraiWasiatSerahanDaerah(String daerah) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		
		{
			try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf, tblppkperintah i, tblppkperbicaraan j, tblppkkeputusanpermohonan k ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_permohonan=k.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and k.id_keputusanpermohonan=j.id_keputusanpermohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and j.id_perbicaraan=i.id_perbicaraan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=47 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and i.flag_batal=1 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_DAERAHMHN ="+daerah;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi2 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
		}

}// close class
	
	public static void setDataSenaraiWasiatSerahanDaerahTahun(String daerah, String tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		
		{
			try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf, tblppkperintah i, tblppkperbicaraan j, tblppkkeputusanpermohonan k ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_permohonan=k.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and k.id_keputusanpermohonan=j.id_keputusanpermohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and j.id_perbicaraan=i.id_perbicaraan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=47 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and i.flag_batal=1 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_DAERAHMHN ="+daerah;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and TO_CHAR(b.TARIKH_MOHON,'yyyy') ="+tahun;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi2 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
		}

}// close class
	
	public static void setDataSenaraiWasiatSerahanDaerahSelangmasa(String daerah, String tarikh_mula, String tarikh_akhir) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		
		{
			try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf, tblppkperintah i, tblppkperbicaraan j, tblppkkeputusanpermohonan k ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_permohonan=k.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and k.id_keputusanpermohonan=j.id_keputusanpermohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and j.id_perbicaraan=i.id_perbicaraan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=47 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and i.flag_batal=1 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_DAERAHMHN ="+daerah;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND b.TARIKH_MOHON between TO_DATE('"+tarikh_mula+"','dd/mm/yyyy') and TO_DATE('"+tarikh_akhir+"','dd/mm/yyyy')";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi2 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
		}

}// close class
	
	public static void setDataSenaraiWasiatSerahanDaerahBulan(String daerah, String mula_bulan, String mula_tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		
		{
			try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "TO_CHAR(MIN(rsusf.TARIKH_MASUK),'dd/mm/yyyy') tarikhSelesai";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblrujsuburusanstatus rsus, tblrujsuburusanstatusfail rsusf, tblppkperintah i, tblppkperbicaraan j, tblppkkeputusanpermohonan k ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_permohonan=k.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and k.id_keputusanpermohonan=j.id_keputusanpermohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and j.id_perbicaraan=i.id_perbicaraan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati and rsus.ID_STATUS = b.id_status";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsus.ID_SUBURUSANSTATUS = rsusf.ID_SUBURUSANSTATUS";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and rsusf.ID_PERMOHONAN = b.ID_PERMOHONAN";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_status=47 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and i.flag_batal=1 ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_DAERAHMHN ="+daerah;
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND TO_CHAR(b.TARIKH_MOHON,'mm') = '"+mula_bulan+"'";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND TO_CHAR(b.TARIKH_MOHON,'yyyy') = '"+mula_tahun+"'";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " group by  a.no_fail,f.nama_simati, e.nama_pemohon,b.tarikh_mohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi2 = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					
					if (rs.getString("noFail") != null && rs.getString("noFail") != "") {
						h.put("noFail", rs.getString("noFail"));
					}
					if (rs.getString("namaSimati") != null && rs.getString("namaSimati") != "") {
						h.put("namaSimati", rs.getString("namaSimati"));
					}
					if (rs.getString("namaPemohon") != null && rs.getString("namaPemohon") != "") {
						h.put("namaPemohon", rs.getString("namaPemohon"));
					}
					if (rs.getString("tarikhMohon") != null && rs.getString("tarikhMohon") != "") {
						h.put("tarikhMohon", rs.getString("tarikhMohon"));
					}
					if (rs.getString("tarikhSelesai") != null && rs.getString("tarikhSelesai") != "") {
						h.put("tarikhSelesai", rs.getString("tarikhSelesai"));
					}
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
		}

}// close class
	//
}
