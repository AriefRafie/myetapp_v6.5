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

public class CetakLaporanKesTertunggakInternalData {

	static Logger myLogger = Logger.getLogger(CetakLaporanKesTertunggakInternalData.class);

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
	private static Vector SenaraiSerahan2 = new Vector();
	private static Vector SenaraiSerahan3 = new Vector();
	private static Vector SenaraiSerahan4 = new Vector();
	private static Vector SenaraiSerahan5 = new Vector();
	private static Vector SenaraiSerahan6 = new Vector();
	

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
	
	public static Vector getSenaraiLaporanSerahanB() {
		return SenaraiSerahan;
	}
	
	public static Vector getSenaraiLaporanSerahanC() {
		return SenaraiSerahan;
	}
	
	public static Vector getLaporanPrestasiBulananPPK() {
		return SenaraiSerahan;
	}
	
	public static Vector getLaporanPrestasiBulananPPKKedah() {
		return SenaraiSerahan2;
	}

	
	public static Vector getLaporanPrestasiBulananPPKKelantan() {
		return SenaraiSerahan3;
	}
	
	public static Vector getLaporanPrestasiBulananPPKMelaka() {
		return SenaraiSerahan4;
	}
	
	public static Vector getLaporanPrestasiBulananPPKWilayah() {
		return SenaraiSerahan5;
	}
	
	public static Vector getLaporanPrestasiBulananPPKSegamat() {
		return SenaraiSerahan6;
	}
	// List permohonan Seksyen 8
	public static void setDataSenaraiSerahan(String mula_hari, String mula_bulan, String mula_tahun, String akhir_hari, String akhir_bulan, String akhir_tahun, String negeri, String daerah, String unit) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		String tarikh_mula = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
		String tarikh_akhir = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun;
		System.out.println("tarikh_mula = "+tarikh_mula);
		
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, h.tarikh_terima_borangc, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblppkkeputusanpermohonan h ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where b.tarikh_mohon BETWEEN TO_DATE('"+tarikh_mula+"', 'DD/MM/YYYY') AND TO_DATE('"+tarikh_akhir+"', 'DD/MM/YYYY') ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and a.id_fail=b.id_fail";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan = h.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati";
		    	if (negeri!="")
		    	{
		    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_NEGERIMHN ="+negeri;
		    	}
		    	if (daerah!="")
		    	{
		    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_DAERAHMHN ="+daerah;
		    	}
		    	if (unit!="")
		    	{
		    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_unitpskawal ="+unit;
		    	}
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and h.tarikh_terima_borangc is Null";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi = " + sqlJanaLaporanPindahkeMahkamahTinggi);
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
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
			
		    
			}

	// close default
	
	public static void setDataSenaraiSerahanOverall(String mula_hari, String mula_bulan, String mula_tahun, String akhir_hari, String akhir_bulan, String akhir_tahun, String negeri) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPindahkeMahkamahTinggi = "";
		List senaraiFail = null;
		String tarikh_mula = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
		String tarikh_akhir = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun;
		System.out.println("tarikh_mula = "+tarikh_mula);
		
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanPindahkeMahkamahTinggi = "select COUNT(a.no_fail) AS DistinctJumlah ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblppkkeputusanpermohonan h ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " where b.tarikh_mohon BETWEEN TO_DATE('"+tarikh_mula+"', 'DD/MM/YYYY') AND TO_DATE('"+tarikh_akhir+"', 'DD/MM/YYYY') ";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and a.id_fail=b.id_fail";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan = h.id_permohonan";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati";
		    	if (negeri!="")
		    	{
		    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_NEGERIMHN ="+negeri;
		    	}
		    	
		    	sqlJanaLaporanPindahkeMahkamahTinggi += " and h.tarikh_terima_borangc is Null";
		    	
		    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi = " + sqlJanaLaporanPindahkeMahkamahTinggi);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();

					if (rs.getString("DistinctJumlah") != null && rs.getString("DistinctJumlah") != "") {
						h.put("DistinctJumlah", rs.getString("DistinctJumlah"));
					}
					
					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
			
		    
			}

	// close default


public static void setDataSenaraiSerahanB(String mula_hari, String mula_bulan, String mula_tahun, String akhir_hari, String akhir_bulan, String akhir_tahun, String negeri, String daerah, String unit) throws Exception {
	//SenaraiSerahan = null;
	SenaraiSerahan.clear();
	Db db = null;
	String sqlJanaLaporanPindahkeMahkamahTinggi = "";
	List senaraiFail = null;
	String tarikh_mula = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
	String tarikh_akhir = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun;
	System.out.println("tarikh_mula = "+tarikh_mula);
	
	    try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, h.tarikh_terima_borangc, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += "from tblpfdfail a , tblppkpermohonan b, ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblppkkeputusanpermohonan h ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " where b.tarikh_mohon BETWEEN TO_DATE('"+tarikh_mula+"', 'DD/MM/YYYY') AND TO_DATE('"+tarikh_akhir+"', 'DD/MM/YYYY') ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and a.id_fail=b.id_fail";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan=c.id_permohonan";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan = h.id_permohonan";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati";
	    	if (negeri!="")
	    	{
	    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_NEGERIMHN ="+negeri;
	    	}
	    	if (daerah!="")
	    	{
	    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_DAERAHMHN ="+daerah;
	    	}
	    	if (unit!="")
	    	{
	    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_unitpskawal ="+unit;
	    	}
	    	//sqlJanaLaporanPindahkeMahkamahTinggi += " and h.ID_KEPUTUSANPERMOHONAN = j.ID_KEPUTUSANPERMOHONAN";
	    	//sqlJanaLaporanPindahkeMahkamahTinggi += " and j.ID_PERBICARAAN = i.ID_PERBICARAAN";
	    	//sqlJanaLaporanPindahkeMahkamahTinggi += " and i.FLAG_TANGGUH = '7'";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and h.tarikh_hantar_nilaian is null";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
	    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi = " + sqlJanaLaporanPindahkeMahkamahTinggi);
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
				SenaraiSerahan.addElement(h);
				}
	   
	    	}
	    
	    finally {
		      if (db != null) db.close();
	    }
		
	    
		}

public static void setDataSenaraiSerahanC(String mula_hari, String mula_bulan, String mula_tahun, String akhir_hari, String akhir_bulan, String akhir_tahun, String negeri, String daerah, String unit) throws Exception {
	//SenaraiSerahan = null;
	SenaraiSerahan.clear();
	Db db = null;
	String sqlJanaLaporanPindahkeMahkamahTinggi = "";
	List senaraiFail = null;
	String tarikh_mula = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
	String tarikh_akhir = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun;
	System.out.println("tarikh_mula = "+tarikh_mula);
	
	    try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, h.tarikh_terima_borangc, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += "from tblpfdfail a , tblppkpermohonan b, tblppkperintah j, tblppkperbicaraan i, ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblppkkeputusanpermohonan h ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " where b.tarikh_mohon BETWEEN TO_DATE('"+tarikh_mula+"', 'DD/MM/YYYY') AND TO_DATE('"+tarikh_akhir+"', 'DD/MM/YYYY') ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and a.id_fail=b.id_fail";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan = c.id_permohonan";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan = h.id_permohonan";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and h.ID_KEPUTUSANPERMOHONAN = i.ID_KEPUTUSANPERMOHONAN";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and i.ID_PERBICARAAN = j.ID_PERBICARAAN";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and j.FLAG_TANGGUH = '1'";
	    	if (negeri!="")
	    	{
	    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_NEGERIMHN ="+negeri;
	    	}
	    	if (daerah!="")
	    	{
	    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_DAERAHMHN ="+daerah;
	    	}
	    	if (unit!="")
	    	{
	    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_unitpskawal ="+unit;
	    	}
	    	//sqlJanaLaporanPindahkeMahkamahTinggi += " and h.tarikh_hantar_nilaian is null";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
	    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi = " + sqlJanaLaporanPindahkeMahkamahTinggi);
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
				
				SenaraiSerahan.addElement(h);
				}
	   
	    	}
	    
	    finally {
		      if (db != null) db.close();
	    }
		
	    
		}

public static void setDataSenaraiSerahanD(String mula_hari, String mula_bulan, String mula_tahun, String akhir_hari, String akhir_bulan, String akhir_tahun, String negeri, String daerah, String unit) throws Exception {
	//SenaraiSerahan = null;
	SenaraiSerahan.clear();
	Db db = null;
	String sqlJanaLaporanPindahkeMahkamahTinggi = "";
	List senaraiFail = null;
	String tarikh_mula = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
	String tarikh_akhir = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun;
	System.out.println("tarikh_mula = "+tarikh_mula);
	
	    try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, h.tarikh_terima_borangc, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += "from tblpfdfail a , tblppkpermohonan b, tblppkperintah j, tblppkperbicaraan i, ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblppkkeputusanpermohonan h ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " where b.tarikh_mohon BETWEEN TO_DATE('"+tarikh_mula+"', 'DD/MM/YYYY') AND TO_DATE('"+tarikh_akhir+"', 'DD/MM/YYYY') ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and a.id_fail=b.id_fail";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan = c.id_permohonan";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan = h.id_permohonan";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and h.ID_KEPUTUSANPERMOHONAN = i.ID_KEPUTUSANPERMOHONAN";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and i.ID_PERBICARAAN = j.ID_PERBICARAAN";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and j.FLAG_TANGGUH = '2'";
	    	if (negeri!="")
	    	{
	    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_NEGERIMHN ="+negeri;
	    	}
	    	if (daerah!="")
	    	{
	    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_DAERAHMHN ="+daerah;
	    	}
	    	if (unit!="")
	    	{
	    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_unitpskawal ="+unit;
	    	}
	    	//sqlJanaLaporanPindahkeMahkamahTinggi += " and h.tarikh_hantar_nilaian is null";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
	    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi = " + sqlJanaLaporanPindahkeMahkamahTinggi);
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
				
				SenaraiSerahan.addElement(h);
				}
	   
	    	}
	    
	    finally {
		      if (db != null) db.close();
	    }
		
	    
		}

public static void setDataSenaraiSerahanE(String mula_hari, String mula_bulan, String mula_tahun, String akhir_hari, String akhir_bulan, String akhir_tahun, String negeri, String daerah, String unit) throws Exception {
	//SenaraiSerahan = null;
	SenaraiSerahan.clear();
	Db db = null;
	String sqlJanaLaporanPindahkeMahkamahTinggi = "";
	List senaraiFail = null;
	String tarikh_mula = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
	String tarikh_akhir = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun;
	System.out.println("tarikh_mula = "+tarikh_mula);
	
	    try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, h.tarikh_terima_borangc, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += "from tblpfdfail a , tblppkpermohonan b, tblppkperintah j, tblppkperbicaraan i, ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblppkkeputusanpermohonan h ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " where b.tarikh_mohon BETWEEN TO_DATE('"+tarikh_mula+"', 'DD/MM/YYYY') AND TO_DATE('"+tarikh_akhir+"', 'DD/MM/YYYY') ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and a.id_fail=b.id_fail";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan = c.id_permohonan";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan = h.id_permohonan";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and h.ID_KEPUTUSANPERMOHONAN = i.ID_KEPUTUSANPERMOHONAN";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and i.ID_PERBICARAAN = j.ID_PERBICARAAN";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and j.FLAG_TANGGUH = '7'";
	    	if (negeri!="")
	    	{
	    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_NEGERIMHN ="+negeri;
	    	}
	    	if (daerah!="")
	    	{
	    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_DAERAHMHN ="+daerah;
	    	}
	    	if (unit!="")
	    	{
	    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_unitpskawal ="+unit;
	    	}
	    	//sqlJanaLaporanPindahkeMahkamahTinggi += " and h.tarikh_hantar_nilaian is null";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
	    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi = " + sqlJanaLaporanPindahkeMahkamahTinggi);
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
				
				SenaraiSerahan.addElement(h);
				}
	   
	    	}
	    
	    finally {
		      if (db != null) db.close();
	    }
		
	    
		}

public static void setDataSenaraiSerahanF(String mula_hari, String mula_bulan, String mula_tahun, String akhir_hari, String akhir_bulan, String akhir_tahun, String negeri, String daerah, String unit) throws Exception {
	//SenaraiSerahan = null;
	SenaraiSerahan.clear();
	Db db = null;
	String sqlJanaLaporanPindahkeMahkamahTinggi = "";
	List senaraiFail = null;
	String tarikh_mula = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
	String tarikh_akhir = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun;
	System.out.println("tarikh_mula = "+tarikh_mula);
	
	    try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, h.tarikh_terima_borangc, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += "from tblpfdfail a , tblppkpermohonan b, tblppkperintah j, tblppkperbicaraan i, ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblppkkeputusanpermohonan h ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " where b.tarikh_mohon BETWEEN TO_DATE('"+tarikh_mula+"', 'DD/MM/YYYY') AND TO_DATE('"+tarikh_akhir+"', 'DD/MM/YYYY') ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and a.id_fail=b.id_fail";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan = c.id_permohonan";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan = h.id_permohonan";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and h.ID_KEPUTUSANPERMOHONAN = i.ID_KEPUTUSANPERMOHONAN";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and i.ID_PERBICARAAN = j.ID_PERBICARAAN";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and j.FLAG_TANGGUH in (3,5)";
	    	if (negeri!="")
	    	{
	    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_NEGERIMHN ="+negeri;
	    	}
	    	if (daerah!="")
	    	{
	    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_DAERAHMHN ="+daerah;
	    	}
	    	if (unit!="")
	    	{
	    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_unitpskawal ="+unit;
	    	}
	    	//sqlJanaLaporanPindahkeMahkamahTinggi += " and h.tarikh_hantar_nilaian is null";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
	    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi = " + sqlJanaLaporanPindahkeMahkamahTinggi);
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
				
				SenaraiSerahan.addElement(h);
				}
	   
	    	}
	    
	    finally {
		      if (db != null) db.close();
	    }
		
	    
		}

public static void setDataSenaraiSerahanG(String mula_hari, String mula_bulan, String mula_tahun, String akhir_hari, String akhir_bulan, String akhir_tahun, String negeri, String daerah, String unit) throws Exception {
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
	    	sqlJanaLaporanPindahkeMahkamahTinggi = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, h.tarikh_terima_borangc, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += "from tblpfdfail a , tblppkpermohonan b, tblppkperintah j, tblppkperbicaraan i, ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " tblppkpermohonansimati c, tblppkpemohon e, tblppksimati f, tblppkkeputusanpermohonan h ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " where b.tarikh_mohon BETWEEN TO_DATE('"+tarikh_mula+"', 'DD/MM/YYYY') AND TO_DATE('"+tarikh_akhir+"', 'DD/MM/YYYY') ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and a.id_fail=b.id_fail";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan = c.id_permohonan";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_permohonan = h.id_permohonan";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_pemohon=e.id_pemohon";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and c.id_simati=f.id_simati";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and h.ID_KEPUTUSANPERMOHONAN = i.ID_KEPUTUSANPERMOHONAN";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and i.ID_PERBICARAAN = j.ID_PERBICARAAN";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and j.FLAG_TANGGUH = '9'";
	    	if (negeri!="")
	    	{
	    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_NEGERIMHN ="+negeri;
	    	}
	    	if (daerah!="")
	    	{
	    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.ID_DAERAHMHN ="+daerah;
	    	}
	    	if (unit!="")
	    	{
	    		sqlJanaLaporanPindahkeMahkamahTinggi += " and b.id_unitpskawal ="+unit;
	    	}
	    	//sqlJanaLaporanPindahkeMahkamahTinggi += " and h.tarikh_hantar_nilaian is null";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " order by a.no_fail";
	    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi = " + sqlJanaLaporanPindahkeMahkamahTinggi);
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
				
				SenaraiSerahan.addElement(h);
				}
	   
	    	}
	    
	    finally {
		      if (db != null) db.close();
	    }
		
	    
		}

public static void setLaporanPrestasiBulananPPK() throws Exception {
	//SenaraiSerahan = null;
	SenaraiSerahan.clear();
	Db db = null;
	String sqlJanaLaporanPindahkeMahkamahTinggi = "";
	List senaraiFail = null;
	
	    try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db.getStatement();

	    	sqlJanaLaporanPindahkeMahkamahTinggi = "SELECT DISTINCT A.ID_NEGERIMHN, B.NAMA_NEGERI, COUNT(*) AS JUMLAH_FAIL_JOHOR ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += "FROM TBLPPKPERMOHONAN A, TBLRUJNEGERI B, TBLPFDFAIL C";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " WHERE A.ID_NEGERIMHN =  B.ID_NEGERI ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND A.ID_NEGERIMHN = 1 ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND A.ID_FAIL  =  C.ID_FAIL ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND C.ID_SEKSYEN =2";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND C.ID_SUBURUSAN = 59";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND TO_CHAR(A.TARIKH_MOHON,'mm/yyyy') >= '10/2010'";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " GROUP BY A.ID_NEGERIMHN, B.NAMA_NEGERI";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " ORDER BY B.NAMA_NEGERI";
	    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi = " + sqlJanaLaporanPindahkeMahkamahTinggi);
	    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
	    	Hashtable h;
	    	//con.commit();
	    	while (rs.next()) {
				h = new Hashtable();

				if (rs.getString("JUMLAH_FAIL_JOHOR") != null && rs.getString("JUMLAH_FAIL_JOHOR") != "") {
					h.put("JUMLAH_FAIL_JOHOR", rs.getString("JUMLAH_FAIL_JOHOR"));
				}
				SenaraiSerahan.addElement(h);
				}
	   
	    	}
	    
	    finally {
		      if (db != null) db.close();
	    }
		
	    
		}

public static void setLaporanPrestasiBulananPPKKedah() throws Exception {
	//SenaraiSerahan = null;
	SenaraiSerahan2.clear();
	Db db2 = null;
	String sqlJanaLaporanPindahkeMahkamahTinggi = "";
	
	
	    try {
	    	db2 = new Db();
	    	Connection con = db2.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db2.getStatement();
	    	sqlJanaLaporanPindahkeMahkamahTinggi = "SELECT DISTINCT A.ID_NEGERIMHN, B.NAMA_NEGERI, COUNT(*) AS JUMLAH_FAIL_KEDAH ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += "FROM TBLPPKPERMOHONAN A, TBLRUJNEGERI B, TBLPFDFAIL C";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " WHERE A.ID_NEGERIMHN =  B.ID_NEGERI ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND A.ID_NEGERIMHN = 2 ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND A.ID_FAIL  =  C.ID_FAIL ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND C.ID_SEKSYEN =2";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND C.ID_SUBURUSAN = 59";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND TO_CHAR(A.TARIKH_MOHON,'mm/yyyy') >= '10/2010'";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " GROUP BY A.ID_NEGERIMHN, B.NAMA_NEGERI";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " ORDER BY B.NAMA_NEGERI";
	    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi = " + sqlJanaLaporanPindahkeMahkamahTinggi);
	    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
	    	Hashtable h2;
	    	//con.commit();
	    	while (rs.next()) {
				h2 = new Hashtable();

				if (rs.getString("JUMLAH_FAIL_KEDAH") != null && rs.getString("JUMLAH_FAIL_KEDAH") != "") {
					h2.put("JUMLAH_FAIL_KEDAH", rs.getString("JUMLAH_FAIL_KEDAH"));
				}
				SenaraiSerahan2.addElement(h2);
				}
	   
	    	}
	    
	    finally {
		      if (db2 != null) db2.close();
	    }
		
	    
		}

public static void setLaporanPrestasiBulananPPKKelantan() throws Exception {
	//SenaraiSerahan = null;
	SenaraiSerahan3.clear();
	Db db2 = null;
	String sqlJanaLaporanPindahkeMahkamahTinggi = "";
	
	
	    try {
	    	db2 = new Db();
	    	Connection con = db2.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db2.getStatement();
	    	sqlJanaLaporanPindahkeMahkamahTinggi = "SELECT DISTINCT A.ID_NEGERIMHN, B.NAMA_NEGERI, COUNT(*) AS JUMLAH_FAIL_KELANTAN ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += "FROM TBLPPKPERMOHONAN A, TBLRUJNEGERI B, TBLPFDFAIL C";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " WHERE A.ID_NEGERIMHN =  B.ID_NEGERI ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND A.ID_NEGERIMHN = 3 ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND A.ID_FAIL  =  C.ID_FAIL ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND C.ID_SEKSYEN =2";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND C.ID_SUBURUSAN = 59";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND TO_CHAR(A.TARIKH_MOHON,'mm/yyyy') >= '10/2010'";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " GROUP BY A.ID_NEGERIMHN, B.NAMA_NEGERI";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " ORDER BY B.NAMA_NEGERI";
	    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi = " + sqlJanaLaporanPindahkeMahkamahTinggi);
	    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
	    	Hashtable h2;
	    	//con.commit();
	    	while (rs.next()) {
				h2 = new Hashtable();

				if (rs.getString("JUMLAH_FAIL_KELANTAN") != null && rs.getString("JUMLAH_FAIL_KELANTAN") != "") {
					h2.put("JUMLAH_FAIL_KELANTAN", rs.getString("JUMLAH_FAIL_KELANTAN"));
				}
				SenaraiSerahan3.addElement(h2);
				}
	   
	    	}
	    
	    finally {
		      if (db2 != null) db2.close();
	    }
		
	    
		}

public static void setLaporanPrestasiBulananPPKMelaka() throws Exception {
	//SenaraiSerahan = null;
	SenaraiSerahan4.clear();
	Db db2 = null;
	String sqlJanaLaporanPindahkeMahkamahTinggi = "";
	
	
	    try {
	    	db2 = new Db();
	    	Connection con = db2.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db2.getStatement();
	    	sqlJanaLaporanPindahkeMahkamahTinggi = "SELECT DISTINCT A.ID_NEGERIMHN, B.NAMA_NEGERI, COUNT(*) AS JUMLAH_FAIL_MELAKA ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += "FROM TBLPPKPERMOHONAN A, TBLRUJNEGERI B, TBLPFDFAIL C";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " WHERE A.ID_NEGERIMHN =  B.ID_NEGERI ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND A.ID_NEGERIMHN = 4 ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND A.ID_FAIL  =  C.ID_FAIL ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND C.ID_SEKSYEN =2";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND C.ID_SUBURUSAN = 59";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND TO_CHAR(A.TARIKH_MOHON,'mm/yyyy') >= '10/2010'";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " GROUP BY A.ID_NEGERIMHN, B.NAMA_NEGERI";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " ORDER BY B.NAMA_NEGERI";
	    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi = " + sqlJanaLaporanPindahkeMahkamahTinggi);
	    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
	    	Hashtable h2;
	    	//con.commit();
	    	while (rs.next()) {
				h2 = new Hashtable();

				if (rs.getString("JUMLAH_FAIL_MELAKA") != null && rs.getString("JUMLAH_FAIL_MELAKA") != "") {
					h2.put("JUMLAH_FAIL_MELAKA", rs.getString("JUMLAH_FAIL_MELAKA"));
				}
				SenaraiSerahan4.addElement(h2);
				}
	   
	    	}
	    
	    finally {
		      if (db2 != null) db2.close();
	    }
		
	    
		}

public static void setLaporanPrestasiBulananPPKWilayah() throws Exception {
	//SenaraiSerahan = null;
	SenaraiSerahan5.clear();
	Db db2 = null;
	String sqlJanaLaporanPindahkeMahkamahTinggi = "";
	
	
	    try {
	    	db2 = new Db();
	    	Connection con = db2.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db2.getStatement();
	    	sqlJanaLaporanPindahkeMahkamahTinggi = "SELECT DISTINCT A.ID_NEGERIMHN, B.NAMA_NEGERI, COUNT(*) AS JUMLAH_FAIL_WILAYAH ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += "FROM TBLPPKPERMOHONAN A, TBLRUJNEGERI B, TBLPFDFAIL C";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " WHERE A.ID_NEGERIMHN =  B.ID_NEGERI ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND A.ID_NEGERIMHN = 14 ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND A.ID_FAIL  =  C.ID_FAIL ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND C.ID_SEKSYEN =2";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND C.ID_SUBURUSAN = 59";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND TO_CHAR(A.TARIKH_MOHON,'mm/yyyy') >= '10/2010'";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " GROUP BY A.ID_NEGERIMHN, B.NAMA_NEGERI";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " ORDER BY B.NAMA_NEGERI";
	    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi = " + sqlJanaLaporanPindahkeMahkamahTinggi);
	    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
	    	Hashtable h2;
	    	//con.commit();
	    	while (rs.next()) {
				h2 = new Hashtable();

				if (rs.getString("JUMLAH_FAIL_WILAYAH") != null && rs.getString("JUMLAH_FAIL_WILAYAH") != "") {
					h2.put("JUMLAH_FAIL_WILAYAH", rs.getString("JUMLAH_FAIL_WILAYAH"));
				}
				SenaraiSerahan5.addElement(h2);
				}
	   
	    	}
	    
	    finally {
		      if (db2 != null) db2.close();
	    }
		
	    
		}

public static void setLaporanPrestasiBulananPPKSegamat() throws Exception {
	//SenaraiSerahan = null;
	SenaraiSerahan6.clear();
	Db db2 = null;
	String sqlJanaLaporanPindahkeMahkamahTinggi = "";
	
	
	    try {
	    	db2 = new Db();
	    	Connection con = db2.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db2.getStatement();
	    	sqlJanaLaporanPindahkeMahkamahTinggi = "SELECT DISTINCT A.ID_NEGERIMHN, B.NAMA_NEGERI, COUNT(*) AS JUMLAH_FAIL_SEGAMAT ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += "FROM TBLPPKPERMOHONAN A, TBLRUJNEGERI B, TBLPFDFAIL C, tblrujdaerah d";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " WHERE A.ID_NEGERIMHN =  B.ID_NEGERI ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND A.ID_NEGERIMHN = 1 ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND A.ID_FAIL  =  C.ID_FAIL ";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND C.ID_SEKSYEN =2";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND C.ID_SUBURUSAN = 59";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and a.id_daerahmhn=d.id_daerah";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " AND TO_CHAR(A.TARIKH_MOHON,'mm/yyyy') >= '10/2010'";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " and d.id_daerah = 12";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " GROUP BY A.ID_NEGERIMHN, B.NAMA_NEGERI";
	    	sqlJanaLaporanPindahkeMahkamahTinggi += " ORDER BY B.NAMA_NEGERI";
	    	
	    	
	    	
	    	System.out.println("sqlJanaLaporanPindahkeMahkamahTinggi = " + sqlJanaLaporanPindahkeMahkamahTinggi);
	    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPindahkeMahkamahTinggi);
	    	Hashtable h2;
	    	//con.commit();
	    	while (rs.next()) {
				h2 = new Hashtable();

				if (rs.getString("JUMLAH_FAIL_SEGAMAT") != null && rs.getString("JUMLAH_FAIL_SEGAMAT") != "") {
					h2.put("JUMLAH_FAIL_SEGAMAT", rs.getString("JUMLAH_FAIL_SEGAMAT"));
				}
				
				SenaraiSerahan6.addElement(h2);
				}
	   
	    	}
	    
	    finally {
		      if (db2 != null) db2.close();
	    }
		
	    
		}
}// close class

