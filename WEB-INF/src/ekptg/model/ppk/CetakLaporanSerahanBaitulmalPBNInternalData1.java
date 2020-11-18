package ekptg.model.ppk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.DbException;

import org.apache.log4j.Logger;

/*import lebah.pm.entity.ActivityEvent;
 import lebah.pm.entity.UserActivityEvent;
 import lebah.portal.action.Command;
 import lebah.template.DbPersistence;*/
 
public class CetakLaporanSerahanBaitulmalPBNInternalData1 {

	static Logger myLogger = Logger.getLogger(CetakLaporanSerahanBaitulmalPBNInternalData1.class);
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
	private static Vector<Hashtable<String,String>> SenaraiSerahan = new Vector<Hashtable<String,String>>();
	private static Vector<Hashtable<String,String>> SenaraiSerahanAllState = new Vector<Hashtable<String,String>>();
	private static Vector SenaraiSerahan2 = new Vector();
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
	
	public Vector<Hashtable<String,String>> getSenaraiLaporanSerahan() {
		return SenaraiSerahan;
	}
	
	public Vector<Hashtable<String,String>> getSenaraiLaporanSerahanAllState() {
		return SenaraiSerahanAllState;
	}
	
		public static Vector getSenaraiLaporanSerahan2() {
		return SenaraiSerahan2;
	}

	// List permohonan Seksyen 8
	public static void setDataSenaraiSerahan(String mula_hari, String mula_bulan, String mula_tahun, String akhir_hari, String akhir_bulan, String akhir_tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		String tarikh_mula = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
		String tarikh_akhir = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where b.tarikh_mohon BETWEEN TO_DATE('"+tarikh_mula+"', 'DD/MM/YYYY') AND TO_DATE('"+tarikh_akhir+"', 'DD/MM/YYYY') ";
		    	sqlJanaLaporanBaitulmalPBN += " and a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=8";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBN5 = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	// List permohonan Seksyen 8
	public static void setDataSenaraiSerahandaerahtahun(String mula_hari, String mula_bulan, String mula_tahun, String akhir_hari, String akhir_bulan, String akhir_tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		String tarikh_mula = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
		String tarikh_akhir = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where TO_CHAR(b.tarikh_mohon, 'YYYY') = '"+mula_tahun+"'";
		    	sqlJanaLaporanBaitulmalPBN += " and a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=8";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBN6 = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahandaerahbulan(String mula_hari, String mula_bulan, String mula_tahun, String akhir_hari, String akhir_bulan, String akhir_tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		String tarikh_mula = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
		String tarikh_akhir = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where TO_CHAR(b.tarikh_mohon, 'MM/YYYY') = '"+mula_bulan+"/"+mula_tahun+"'";
		    	sqlJanaLaporanBaitulmalPBN += " and a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=8";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBN7 = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanUnit(String unit) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=8";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit8 = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanUnitPA(String unit) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and (d.id_tarafkptg=8 and e.id_tarafkptg=8)";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit8 = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahannegeri(String negeri) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=8";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.ID_NEGERIMHN ="+negeri;
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit9 = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahannegeriPA(String negeri) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and (d.id_tarafkptg=8 and e.id_tarafkptg=8)";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.ID_NEGERIMHN ="+negeri;
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit9 = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanDaerah(String daerah) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=8";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.ID_DAERAHMHN ="+daerah;
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit10 = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanDaerahPA(String daerah) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and (d.id_tarafkptg=8 and e.id_tarafkptg=8";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.ID_DAERAHMHN ="+daerah;
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit10 = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanUnittahun(String unit, String tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=8";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanBaitulmalPBN += " and TO_CHAR(b.TARIKH_MOHON,'yyyy') ="+tahun;
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit11 = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanUnittahunPA(String unit, String tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and (d.id_tarafkptg=8 and e-id_tarafkptg=8)";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanBaitulmalPBN += " and TO_CHAR(b.TARIKH_MOHON,'yyyy') ="+tahun;
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit11 = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanNegeritahun(String negeri, String tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=8";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.ID_NEGERIMHN ="+negeri;
		    	sqlJanaLaporanBaitulmalPBN += " and TO_CHAR(b.TARIKH_MOHON,'yyyy') ="+tahun;
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanNegeritahunPA(String negeri, String tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and (d.id_tarafkptg=8 and e.id_tarafkptg=8)";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.ID_NEGERIMHN ="+negeri;
		    	sqlJanaLaporanBaitulmalPBN += " and TO_CHAR(b.TARIKH_MOHON,'yyyy') ="+tahun;
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setLaporanPrestasiNegeritahun(String negeri, String tahun) throws DbException, SQLException {
		SenaraiSerahan2 = null;
		SenaraiSerahan2.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		int tahunsebelum = Integer.parseInt(tahun)-1;
		//String tahunsebelum = Integer.parseInt(tahunsebelumInt.toString());
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select "; 
		    	sqlJanaLaporanBaitulmalPBN += " to_char(add_months(to_date('01/01/1000', 'DD/MM/RRRR'), ind.l-1), 'MONTH') as month_descr";
		    	sqlJanaLaporanBaitulmalPBN += " ,";
		    	sqlJanaLaporanBaitulmalPBN += " CASE ";
		    	sqlJanaLaporanBaitulmalPBN += "      --WHEN TO_CHAR(add_months(to_date('01/01/1000', 'DD/MM/RRRR'), ind.l-1), 'FMMONTH')='JANUARY' THEN 'JANUARI' ";
		    	sqlJanaLaporanBaitulmalPBN += "      -- WHEN TO_CHAR(add_months(to_date('01/01/1000', 'DD/MM/RRRR'), ind.l-1), 'FMMONTH')='FEBRUARY' THEN 'FEBRUARI'";
		    	sqlJanaLaporanBaitulmalPBN += "     --WHEN TO_CHAR(add_months(to_date('01/01/1000', 'DD/MM/RRRR'), ind.l-1), 'FMMONTH')='MARCH' THEN 'MAC'";
		    	sqlJanaLaporanBaitulmalPBN += "     -- WHEN TO_CHAR(add_months(to_date('01/01/1000', 'DD/MM/RRRR'), ind.l-1), 'FMMONTH')='APRIL' THEN 'APRIL'";
		    	sqlJanaLaporanBaitulmalPBN += "     -- WHEN TO_CHAR(add_months(to_date('01/01/1000', 'DD/MM/RRRR'), ind.l-1), 'FMMONTH')='MAY' THEN 'MEI'";
		    	sqlJanaLaporanBaitulmalPBN += "     -- WHEN TO_CHAR(add_months(to_date('01/01/1000', 'DD/MM/RRRR'), ind.l-1), 'FMMONTH')='JUNE' THEN 'JUN'";
		    	sqlJanaLaporanBaitulmalPBN += "  -- WHEN TO_CHAR(add_months(to_date('01/01/1000', 'DD/MM/RRRR'), ind.l-1), 'FMMONTH')='JULY' THEN 'JULAI'";
		    	sqlJanaLaporanBaitulmalPBN += "   -- WHEN TO_CHAR(add_months(to_date('01/01/1000', 'DD/MM/RRRR'), ind.l-1), 'FMMONTH')='AUGUST' THEN 'OGOS' ";
		    	sqlJanaLaporanBaitulmalPBN += "  -- WHEN TO_CHAR(add_months(to_date('01/01/1000', 'DD/MM/RRRR'), ind.l-1), 'FMMONTH')='SEPTEMBER' THEN 'SEPTEMBER' ";
		    sqlJanaLaporanBaitulmalPBN += "  -- WHEN TO_CHAR(add_months(to_date('01/01/1000', 'DD/MM/RRRR'), ind.l-1), 'FMMONTH')='OCTOBER' THEN 'OKTOBER'";
		    sqlJanaLaporanBaitulmalPBN += "  -- WHEN TO_CHAR(add_months(to_date('01/01/1000', 'DD/MM/RRRR'), ind.l-1), 'FMMONTH')='NOVEMBER' THEN 'NOVEMBER' ";
		    sqlJanaLaporanBaitulmalPBN += "   -- WHEN TO_CHAR(add_months(to_date('01/01/1000', 'DD/MM/RRRR'), ind.l-1), 'FMMONTH')='DECEMBER' THEN 'DISEMBER' ";
		    sqlJanaLaporanBaitulmalPBN += "   WHEN ind.l='1' THEN 'JANUARI' ";
		    sqlJanaLaporanBaitulmalPBN += "   WHEN ind.l='2' THEN 'FEBRUARI'"; 
		    sqlJanaLaporanBaitulmalPBN += "  WHEN ind.l='3' THEN 'MAC' ";
		    sqlJanaLaporanBaitulmalPBN += "   WHEN ind.l='4' THEN 'APRIL' ";
		    sqlJanaLaporanBaitulmalPBN += "   WHEN ind.l='5' THEN 'MEI' ";
		    sqlJanaLaporanBaitulmalPBN += "   WHEN ind.l='6' THEN 'JUN' ";
		    sqlJanaLaporanBaitulmalPBN += "   WHEN ind.l='7' THEN 'JULAI'"; 
		    sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='8' THEN 'OGOS' ";
		    sqlJanaLaporanBaitulmalPBN += "  WHEN ind.l='9' THEN 'SEPTEMBER'  ";
		    sqlJanaLaporanBaitulmalPBN += "   WHEN ind.l='10' THEN 'OKTOBER'";
		    sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='11' THEN 'NOVEMBER'";
		    sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='12' THEN 'DISEMBER'";
		    sqlJanaLaporanBaitulmalPBN += "  END  AS BULAN       ";
		    sqlJanaLaporanBaitulmalPBN += " ,ind.l as month_ind,";
		    sqlJanaLaporanBaitulmalPBN += " CASE ";
		    sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='1' THEN '01' ";
		    sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='2' THEN '02' ";
		    sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='3' THEN '03' ";
		    sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='4' THEN '04' ";
		    sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='5' THEN '05' ";
		    sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='6' THEN '06' ";
		    sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='7' THEN '07' ";
		    sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='8' THEN '08' ";
		    sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='9' THEN '09'  ";
		    sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='10' THEN '10'";
		    sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='11' THEN '11'";
		    sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='12' THEN '12'";
		    sqlJanaLaporanBaitulmalPBN += " END  AS BULANSTRING"; 
   			 sqlJanaLaporanBaitulmalPBN += "    ,";
  			 sqlJanaLaporanBaitulmalPBN += "  CASE ";
      		 sqlJanaLaporanBaitulmalPBN += "  WHEN ind.l='1' THEN '12' ";
       		sqlJanaLaporanBaitulmalPBN += "  WHEN ind.l='2' THEN '01' ";
         	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='3' THEN '02'"; 
        sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='4' THEN '03' ";
    sqlJanaLaporanBaitulmalPBN += "     WHEN ind.l='5' THEN '04' ";
        sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='6' THEN '05' ";
        sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='7' THEN '06' ";
        sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='8' THEN '07' ";
        sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='9' THEN '08'  ";
        sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='10' THEN '09'  ";
        sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='11' THEN '10'  ";
        sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='12' THEN '11'  ";
     sqlJanaLaporanBaitulmalPBN += " END  AS BULANSEBELUM,";
     
     	sqlJanaLaporanBaitulmalPBN += " (SELECT NAMA_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI="+negeri+") as NAMA_NEGERI,";
      
	sqlJanaLaporanBaitulmalPBN += " (SELECT count(*) bilangans8 FROM TBLPPKPERMOHONAN p,TBLPFDFAIL F ";
	sqlJanaLaporanBaitulmalPBN += " WHERE F.ID_FAIL = P.ID_FAIL ";
	sqlJanaLaporanBaitulmalPBN += " AND f.id_suburusan in (59) ";
	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(P.TARIKH_MOHON,'mm/yyyy')>='10/"+tahun+"'";
	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(P.TARIKH_MOHON,'mm/yyyy')<= (";
        	sqlJanaLaporanBaitulmalPBN += " CASE ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='1' THEN '01/"+tahun+"' ";
           	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='2' THEN '02/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='3' THEN '03/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='4' THEN '04/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='5' THEN '05/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='6' THEN '06/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='7' THEN '07/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='8' THEN '08/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='9' THEN '09/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='10' THEN '10/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='11' THEN '11/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='12' THEN '12/"+tahun+"'  ";
        sqlJanaLaporanBaitulmalPBN += " END )";
	sqlJanaLaporanBaitulmalPBN += " AND F.ID_NEGERI = 10) BILANGANFAIL,";
    	sqlJanaLaporanBaitulmalPBN += " (";
    	sqlJanaLaporanBaitulmalPBN += " SELECT count(distinct(F.ID_FAIL)) bilangans8 FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F ";
    	sqlJanaLaporanBaitulmalPBN += " WHERE F.ID_FAIL = P.ID_FAIL ";
         sqlJanaLaporanBaitulmalPBN += " AND P.ID_STATUS NOT IN (18,41,999,171,152,70)";
    	sqlJanaLaporanBaitulmalPBN += " AND F.id_suburusan = 59 ";
    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(P.TARIKH_MOHON,'mm/yyyy')= (";
        	sqlJanaLaporanBaitulmalPBN += " CASE ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='1' THEN '12/tahunsebelum'"; 
           sqlJanaLaporanBaitulmalPBN += " 	WHEN ind.l='2' THEN '01/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='3' THEN '02/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='4' THEN '03/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='5' THEN '04/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='6' THEN '05/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='7' THEN '06/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='8' THEN '07/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='9' THEN '08/"+tahun+"'";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='10' THEN '09/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='11' THEN '10/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='12' THEN '11/"+tahun+"'  ";
        sqlJanaLaporanBaitulmalPBN += " END )";
    	sqlJanaLaporanBaitulmalPBN += " AND F.ID_NEGERI = 10 ) AS BILANGANBULANSEBELUM,";
    	sqlJanaLaporanBaitulmalPBN += " (";
    	sqlJanaLaporanBaitulmalPBN += " SELECT count(distinct(F.ID_FAIL)) bilangans8 FROM TBLPPKPERMOHONAN p,TBLPFDFAIL F ";
    	sqlJanaLaporanBaitulmalPBN += " WHERE F.ID_FAIL = P.ID_FAIL ";
    	sqlJanaLaporanBaitulmalPBN += " AND f.id_suburusan = 60 ";
        sqlJanaLaporanBaitulmalPBN += " AND P.ID_STATUS NOT IN (18,41,999,171,152,70)";
    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(P.TARIKH_MOHON,'mm/yyyy')= (";
        	sqlJanaLaporanBaitulmalPBN += " CASE ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='1' THEN '12/tahunsebelum' ";
           	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='2' THEN '01/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='3' THEN '02/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='4' THEN '03/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='5' THEN '04/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='6' THEN '05/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='7' THEN '06/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='8' THEN '07/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='9' THEN '08/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='10' THEN '09/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='11' THEN '10/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='12' THEN '11/"+tahun+"'  ";
        sqlJanaLaporanBaitulmalPBN += " END )";
	sqlJanaLaporanBaitulmalPBN += " AND F.ID_NEGERI = 10 ) AS BILANGANBULANSEBELUM17,";
    	sqlJanaLaporanBaitulmalPBN += " (";
    	sqlJanaLaporanBaitulmalPBN += " SELECT count(distinct(F.ID_FAIL)) bilangans8 FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F ";
    	sqlJanaLaporanBaitulmalPBN += " WHERE F.ID_FAIL = P.ID_FAIL ";
    	sqlJanaLaporanBaitulmalPBN += " AND F.id_suburusan = 59 ";
    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(P.TARIKH_MOHON,'mm/yyyy')= (";
        	sqlJanaLaporanBaitulmalPBN += " CASE ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='1' THEN '01/"+tahun+"'"; 
           	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='2' THEN '02/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='3' THEN '03/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='4' THEN '04/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='5' THEN '05/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='6' THEN '06/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='7' THEN '07/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='8' THEN '08/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='9' THEN '09/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='10' THEN '10/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='11' THEN '11/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='12' THEN '12/"+tahun+"'";  
        sqlJanaLaporanBaitulmalPBN += " END )";
    	sqlJanaLaporanBaitulmalPBN += " AND F.ID_NEGERI = 10 ) AS BILANGANBULANSEMASA,";
    	sqlJanaLaporanBaitulmalPBN += " (";
    	sqlJanaLaporanBaitulmalPBN += " SELECT count(distinct(F.ID_FAIL)) bilangans8 FROM TBLPPKPERMOHONAN p,TBLPFDFAIL F"; 
    	sqlJanaLaporanBaitulmalPBN += " WHERE F.ID_FAIL = P.ID_FAIL"; 
    	sqlJanaLaporanBaitulmalPBN += " AND f.id_suburusan = 60 ";
    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(P.TARIKH_MOHON,'mm/yyyy')= (";
        	sqlJanaLaporanBaitulmalPBN += " CASE ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='1' THEN '01/"+tahun+"'"; 
           	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='2' THEN '02/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='3' THEN '03/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='4' THEN '04/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='5' THEN '05/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='6' THEN '06/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='7' THEN '07/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='8' THEN '08/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='9' THEN '09/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='10' THEN '10/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='11' THEN '11/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='12' THEN '12/"+tahun+"'  ";
        sqlJanaLaporanBaitulmalPBN += " END )";
	sqlJanaLaporanBaitulmalPBN += " AND F.ID_NEGERI = 10 ) AS BILANGANBULANSEMASA17";

sqlJanaLaporanBaitulmalPBN += " ,(    	SELECT count(distinct(F.ID_FAIL)) bilangans8 FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J"; 
    	sqlJanaLaporanBaitulmalPBN += " WHERE F.ID_FAIL = P.ID_FAIL ";
        sqlJanaLaporanBaitulmalPBN += " AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN";
        sqlJanaLaporanBaitulmalPBN += " AND P.ID_PERMOHONAN  =  J.ID_PERMOHONAN";
    	sqlJanaLaporanBaitulmalPBN += " AND f.id_suburusan = 59 ";
    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(I.TARIKH_BICARA,'mm/yyyy')= (";
        	sqlJanaLaporanBaitulmalPBN += " CASE ";
           	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='1' THEN '01/"+tahun+"' ";
           	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='2' THEN '02/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='3' THEN '03/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='4' THEN '04/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='5' THEN '05/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='6' THEN '06/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='7' THEN '07/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='8' THEN '08/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='9' THEN '09/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='10' THEN '10/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='11' THEN '11/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='12' THEN '12/"+tahun+"'  ";
        sqlJanaLaporanBaitulmalPBN += " END )";
	sqlJanaLaporanBaitulmalPBN += " AND F.ID_NEGERI = 1 ) AS bilanganbicara";
sqlJanaLaporanBaitulmalPBN += " ,(    	SELECT count(distinct(F.ID_FAIL)) bilangans8 FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J"; 
    	sqlJanaLaporanBaitulmalPBN += " WHERE F.ID_FAIL = P.ID_FAIL ";
        sqlJanaLaporanBaitulmalPBN += " AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN";
        sqlJanaLaporanBaitulmalPBN += " AND P.ID_PERMOHONAN  =  J.ID_PERMOHONAN";
    	sqlJanaLaporanBaitulmalPBN += " AND f.id_suburusan = 60";
    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(I.TARIKH_BICARA,'mm/yyyy')= (";
        	sqlJanaLaporanBaitulmalPBN += " CASE ";
           sqlJanaLaporanBaitulmalPBN += " 	WHEN ind.l='1' THEN '01/"+tahun+"' ";
           	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='2' THEN '02/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='3' THEN '03/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='4' THEN '04/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='5' THEN '05/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='6' THEN '06/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='7' THEN '07/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='8' THEN '08/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='9' THEN '09/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='10' THEN '10/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='11' THEN '11/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='12' THEN '12/"+tahun+"'  ";
        sqlJanaLaporanBaitulmalPBN += " END )";
	sqlJanaLaporanBaitulmalPBN += " AND F.ID_NEGERI = 1 ) AS bilanganbicara17";

sqlJanaLaporanBaitulmalPBN += " ,(    	SELECT count(distinct(F.ID_FAIL)) bilangans8 FROM ";
	sqlJanaLaporanBaitulmalPBN += " TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J,TBLPPKPERINTAH K ";
    	sqlJanaLaporanBaitulmalPBN += " WHERE F.ID_FAIL = P.ID_FAIL ";
        sqlJanaLaporanBaitulmalPBN += " AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN";
        sqlJanaLaporanBaitulmalPBN += " AND P.ID_PERMOHONAN  =  J.ID_PERMOHONAN";
	sqlJanaLaporanBaitulmalPBN += " AND I.ID_PERBICARAAN = K.ID_PERBICARAAN";
	sqlJanaLaporanBaitulmalPBN += " AND K.FLAG_JENIS_KEPUTUSAN = '0' --selesai";
    	sqlJanaLaporanBaitulmalPBN += " AND f.id_suburusan = 59";
    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(K.TARIKH_PERINTAH,'mm/yyyy')= (";
        	sqlJanaLaporanBaitulmalPBN += " CASE ";
           	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='1' THEN '01/"+tahun+"' ";
           	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='2' THEN '02/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='3' THEN '03/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='4' THEN '04/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='5' THEN '05/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='6' THEN '06/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='7' THEN '07/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='8' THEN '08/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='9' THEN '09/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='10' THEN '10/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='11' THEN '11/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='12' THEN '12/"+tahun+"'  ";
        sqlJanaLaporanBaitulmalPBN += " END )";
	sqlJanaLaporanBaitulmalPBN += " AND F.ID_NEGERI = 1 ) AS bilanganselesai ";
sqlJanaLaporanBaitulmalPBN += " ,(    	SELECT count(distinct(F.ID_FAIL)) bilangans8 FROM ";
	sqlJanaLaporanBaitulmalPBN += " TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J,TBLPPKPERINTAH K"; 
    	sqlJanaLaporanBaitulmalPBN += " WHERE F.ID_FAIL = P.ID_FAIL ";
        sqlJanaLaporanBaitulmalPBN += " AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN";
        sqlJanaLaporanBaitulmalPBN += " AND P.ID_PERMOHONAN  =  J.ID_PERMOHONAN";
	sqlJanaLaporanBaitulmalPBN += " AND I.ID_PERBICARAAN = K.ID_PERBICARAAN";
	sqlJanaLaporanBaitulmalPBN += " AND K.FLAG_JENIS_KEPUTUSAN = '0' --selesai";
    	sqlJanaLaporanBaitulmalPBN += " AND f.id_suburusan = 59";
    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(K.TARIKH_PERINTAH,'mm/yyyy')= (";
        	sqlJanaLaporanBaitulmalPBN += " CASE ";
           	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='1' THEN '12/tahunsebelum'"; 
           	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='2' THEN '01/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='3' THEN '02/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='4' THEN '03/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='5' THEN '04/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='6' THEN '05/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='7' THEN '06/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='8' THEN '07/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='9' THEN '08/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='10' THEN '09/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='11' THEN '10/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='12' THEN '11/"+tahun+"'  ";
         sqlJanaLaporanBaitulmalPBN += " END )";
	sqlJanaLaporanBaitulmalPBN += " AND F.ID_NEGERI = 1 ) AS bilanganselesaisebelum ";
       
sqlJanaLaporanBaitulmalPBN += " ,(    	SELECT count(distinct(F.ID_FAIL)) bilangans8 FROM ";
	sqlJanaLaporanBaitulmalPBN += " TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J,TBLPPKPERINTAH K ";
    	sqlJanaLaporanBaitulmalPBN += " WHERE F.ID_FAIL = P.ID_FAIL ";
        sqlJanaLaporanBaitulmalPBN += " AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN";
        sqlJanaLaporanBaitulmalPBN += " AND P.ID_PERMOHONAN  =  J.ID_PERMOHONAN";
	sqlJanaLaporanBaitulmalPBN += " AND I.ID_PERBICARAAN = K.ID_PERBICARAAN";
	sqlJanaLaporanBaitulmalPBN += " AND K.FLAG_JENIS_KEPUTUSAN = '0' --selesai";
    	sqlJanaLaporanBaitulmalPBN += " AND f.id_suburusan = 60";
    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(K.TARIKH_PERINTAH,'mm/yyyy')= (";
        	sqlJanaLaporanBaitulmalPBN += " CASE ";
           	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='1' THEN '01/"+tahun+"' ";
           	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='2' THEN '02/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='3' THEN '03/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='4' THEN '04/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='5' THEN '05/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='6' THEN '06/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='7' THEN '07/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='8' THEN '08/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='9' THEN '09/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='10' THEN '10/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='11' THEN '11/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='12' THEN '12/"+tahun+"'  ";
        sqlJanaLaporanBaitulmalPBN += " END )";
	sqlJanaLaporanBaitulmalPBN += " AND F.ID_NEGERI = 1 ) AS bilanganselesai17";
sqlJanaLaporanBaitulmalPBN += " ,(    	SELECT count(distinct(F.ID_FAIL)) bilangans8 FROM ";
	sqlJanaLaporanBaitulmalPBN += " TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J,TBLPPKPERINTAH K ";
    	sqlJanaLaporanBaitulmalPBN += " WHERE F.ID_FAIL = P.ID_FAIL ";
        sqlJanaLaporanBaitulmalPBN += " AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN";
        sqlJanaLaporanBaitulmalPBN += " AND P.ID_PERMOHONAN  =  J.ID_PERMOHONAN";
	sqlJanaLaporanBaitulmalPBN += " AND I.ID_PERBICARAAN = K.ID_PERBICARAAN";
	sqlJanaLaporanBaitulmalPBN += " AND K.FLAG_JENIS_KEPUTUSAN = '0' --selesai";
    	sqlJanaLaporanBaitulmalPBN += " AND f.id_suburusan = 60";
    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(K.TARIKH_PERINTAH,'mm/yyyy')= (";
        	sqlJanaLaporanBaitulmalPBN += " CASE ";
           	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='1' THEN '12/tahunsebelum' ";
           	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='2' THEN '01/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='3' THEN '02/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='4' THEN '03/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='5' THEN '04/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='6' THEN '05/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='7' THEN '06/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='8' THEN '07/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='9' THEN '08/"+tahun+"' ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='10' THEN '09/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='11' THEN '10/"+tahun+"'  ";
            	sqlJanaLaporanBaitulmalPBN += " WHEN ind.l='12' THEN '11/"+tahun+"'  ";
         sqlJanaLaporanBaitulmalPBN += " END )";
	sqlJanaLaporanBaitulmalPBN += " AND F.ID_NEGERI = 1 ) AS bilanganselesaisebelum17 ";
       

sqlJanaLaporanBaitulmalPBN += " ,(    	SELECT count(distinct(F.ID_FAIL)) bilangans8 FROM ";
	sqlJanaLaporanBaitulmalPBN += " TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J,TBLPPKPERINTAH K ";
    	sqlJanaLaporanBaitulmalPBN += " WHERE F.ID_FAIL = P.ID_FAIL ";
        sqlJanaLaporanBaitulmalPBN += " AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN";
       sqlJanaLaporanBaitulmalPBN += "  AND P.ID_PERMOHONAN  =  J.ID_PERMOHONAN";
	sqlJanaLaporanBaitulmalPBN += " AND I.ID_PERBICARAAN = K.ID_PERBICARAAN";
	sqlJanaLaporanBaitulmalPBN += " AND K.FLAG_JENIS_KEPUTUSAN = '2' --batal";
    	sqlJanaLaporanBaitulmalPBN += " AND f.id_suburusan = 59";
    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(K.TARIKH_PERINTAH,'mm/yyyy')= (";
        sqlJanaLaporanBaitulmalPBN += " 	CASE ";
         sqlJanaLaporanBaitulmalPBN += "   	WHEN ind.l='1' THEN '01/"+tahun+"' ";
          sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='2' THEN '02/"+tahun+"' ";
          sqlJanaLaporanBaitulmalPBN += "   	WHEN ind.l='3' THEN '03/"+tahun+"' ";
          sqlJanaLaporanBaitulmalPBN += "   	WHEN ind.l='4' THEN '04/"+tahun+"' ";
          sqlJanaLaporanBaitulmalPBN += "   	WHEN ind.l='5' THEN '05/"+tahun+"' ";
          sqlJanaLaporanBaitulmalPBN += "   	WHEN ind.l='6' THEN '06/"+tahun+"' ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='7' THEN '07/"+tahun+"' ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='8' THEN '08/"+tahun+"' ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='9' THEN '09/"+tahun+"' ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='10' THEN '10/"+tahun+"'  ";
            sqlJanaLaporanBaitulmalPBN += " 	WHEN ind.l='11' THEN '11/"+tahun+"'  ";
            sqlJanaLaporanBaitulmalPBN += " 	WHEN ind.l='12' THEN '12/"+tahun+"'  ";
      sqlJanaLaporanBaitulmalPBN += "   END )";
	sqlJanaLaporanBaitulmalPBN += " AND F.ID_NEGERI = 1 ) AS bilanganbatal";
sqlJanaLaporanBaitulmalPBN += " ,(    	SELECT count(distinct(F.ID_FAIL)) bilangans8 FROM ";
	sqlJanaLaporanBaitulmalPBN += " TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J,TBLPPKPERINTAH K ";
    	sqlJanaLaporanBaitulmalPBN += " WHERE F.ID_FAIL = P.ID_FAIL ";
      sqlJanaLaporanBaitulmalPBN += "   AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN";
      sqlJanaLaporanBaitulmalPBN += "   AND P.ID_PERMOHONAN  =  J.ID_PERMOHONAN";
	sqlJanaLaporanBaitulmalPBN += " AND I.ID_PERBICARAAN = K.ID_PERBICARAAN";
	sqlJanaLaporanBaitulmalPBN += " AND K.FLAG_JENIS_KEPUTUSAN = '2' --batal";
    sqlJanaLaporanBaitulmalPBN += " 	AND f.id_suburusan = 59";
    sqlJanaLaporanBaitulmalPBN += " 	AND TO_CHAR(K.TARIKH_PERINTAH,'mm/yyyy')= (";
        sqlJanaLaporanBaitulmalPBN += " 	CASE ";
         sqlJanaLaporanBaitulmalPBN += "          	WHEN ind.l='1' THEN '12/tahunsebelum' ";
          sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='2' THEN '01/"+tahun+"' ";
            sqlJanaLaporanBaitulmalPBN += " 	WHEN ind.l='3' THEN '02/"+tahun+"' ";
            sqlJanaLaporanBaitulmalPBN += " 	WHEN ind.l='4' THEN '03/"+tahun+"' ";
            sqlJanaLaporanBaitulmalPBN += " 	WHEN ind.l='5' THEN '04/"+tahun+"' ";
            sqlJanaLaporanBaitulmalPBN += " 	WHEN ind.l='6' THEN '05/"+tahun+"' ";
            sqlJanaLaporanBaitulmalPBN += " 	WHEN ind.l='7' THEN '06/"+tahun+"' ";
            sqlJanaLaporanBaitulmalPBN += " 	WHEN ind.l='8' THEN '07/"+tahun+"' ";
            sqlJanaLaporanBaitulmalPBN += " 	WHEN ind.l='9' THEN '08/"+tahun+"' ";
            sqlJanaLaporanBaitulmalPBN += " 	WHEN ind.l='10' THEN '09/"+tahun+"'  ";
            sqlJanaLaporanBaitulmalPBN += " 	WHEN ind.l='11' THEN '10/"+tahun+"' "; 
            sqlJanaLaporanBaitulmalPBN += " 	WHEN ind.l='12' THEN '11/"+tahun+"'  ";  
      sqlJanaLaporanBaitulmalPBN += "   END )";
	sqlJanaLaporanBaitulmalPBN += " AND F.ID_NEGERI = 1 ) AS bilanganbatalsebelum";
sqlJanaLaporanBaitulmalPBN += " ,(    	SELECT count(distinct(F.ID_FAIL)) bilangans8 FROM ";
	sqlJanaLaporanBaitulmalPBN += " TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J,TBLPPKPERINTAH K"; 
    sqlJanaLaporanBaitulmalPBN += " 	WHERE F.ID_FAIL = P.ID_FAIL ";
     sqlJanaLaporanBaitulmalPBN += "    AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN";
     sqlJanaLaporanBaitulmalPBN += "    AND P.ID_PERMOHONAN  =  J.ID_PERMOHONAN";
	sqlJanaLaporanBaitulmalPBN += " AND I.ID_PERBICARAAN = K.ID_PERBICARAAN";
	sqlJanaLaporanBaitulmalPBN += " AND K.FLAG_JENIS_KEPUTUSAN = '2' --batal";
    	sqlJanaLaporanBaitulmalPBN += " AND f.id_suburusan = 60";
    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(K.TARIKH_PERINTAH,'mm/yyyy')= (";
        sqlJanaLaporanBaitulmalPBN += " 	CASE ";
        sqlJanaLaporanBaitulmalPBN += "           	WHEN ind.l='1' THEN '12/tahunsebelum'"; 
         sqlJanaLaporanBaitulmalPBN += "   	WHEN ind.l='2' THEN '01/"+tahun+"' ";
         sqlJanaLaporanBaitulmalPBN += "    	WHEN ind.l='3' THEN '02/"+tahun+"' ";
          sqlJanaLaporanBaitulmalPBN += "   	WHEN ind.l='4' THEN '03/"+tahun+"' ";
          sqlJanaLaporanBaitulmalPBN += "   	WHEN ind.l='5' THEN '04/"+tahun+"' ";
          sqlJanaLaporanBaitulmalPBN += "   	WHEN ind.l='6' THEN '05/"+tahun+"' ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='7' THEN '06/"+tahun+"' ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='8' THEN '07/"+tahun+"' ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='9' THEN '08/"+tahun+"' ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='10' THEN '09/"+tahun+"'  ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='11' THEN '10/"+tahun+"'  ";
            sqlJanaLaporanBaitulmalPBN += " 	WHEN ind.l='12' THEN '11/"+tahun+"'";    
     sqlJanaLaporanBaitulmalPBN += "    END )";
	sqlJanaLaporanBaitulmalPBN += " AND F.ID_NEGERI = 1 ) AS bilanganbatalsebelum17";
            
sqlJanaLaporanBaitulmalPBN += " ,(    	SELECT count(distinct(F.ID_FAIL)) bilangans8 FROM ";
sqlJanaLaporanBaitulmalPBN += " 	TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J,TBLPPKPERINTAH K"; 
   sqlJanaLaporanBaitulmalPBN += "  	WHERE F.ID_FAIL = P.ID_FAIL ";
   sqlJanaLaporanBaitulmalPBN += "      AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN";
    sqlJanaLaporanBaitulmalPBN += "     AND P.ID_PERMOHONAN  =  J.ID_PERMOHONAN";
	sqlJanaLaporanBaitulmalPBN += " AND I.ID_PERBICARAAN = K.ID_PERBICARAAN";
	sqlJanaLaporanBaitulmalPBN += " AND K.FLAG_JENIS_KEPUTUSAN = '2' --batal";
    sqlJanaLaporanBaitulmalPBN += " 	AND f.id_suburusan = 60";
    sqlJanaLaporanBaitulmalPBN += " 	AND TO_CHAR(K.TARIKH_PERINTAH,'mm/yyyy')= (";
        sqlJanaLaporanBaitulmalPBN += " 	CASE ";
        sqlJanaLaporanBaitulmalPBN += "    	WHEN ind.l='1' THEN '01/"+tahun+"' ";
        sqlJanaLaporanBaitulmalPBN += "    	WHEN ind.l='2' THEN '02/"+tahun+"' ";
          sqlJanaLaporanBaitulmalPBN += "   	WHEN ind.l='3' THEN '03/"+tahun+"' ";
          sqlJanaLaporanBaitulmalPBN += "   	WHEN ind.l='4' THEN '04/"+tahun+"' ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='5' THEN '05/"+tahun+"' ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='6' THEN '06/"+tahun+"' ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='7' THEN '07/"+tahun+"' ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='8' THEN '08/"+tahun+"' ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='9' THEN '09/"+tahun+"' ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='10' THEN '10/"+tahun+"'  ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='11' THEN '11/"+tahun+"'";  
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='12' THEN '12/"+tahun+"'  ";
     sqlJanaLaporanBaitulmalPBN += "    END )";
	sqlJanaLaporanBaitulmalPBN += " AND F.ID_NEGERI = 1 ) AS bilanganbatal17";
  
sqlJanaLaporanBaitulmalPBN += " ,(    	SELECT count(distinct(F.ID_FAIL)) bilangans8 FROM ";
	sqlJanaLaporanBaitulmalPBN += " TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J,TBLPPKPERINTAH K"; 
    sqlJanaLaporanBaitulmalPBN += " 	WHERE F.ID_FAIL = P.ID_FAIL ";
     sqlJanaLaporanBaitulmalPBN += "    AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN";
      sqlJanaLaporanBaitulmalPBN += "   AND P.ID_PERMOHONAN  =  J.ID_PERMOHONAN";
sqlJanaLaporanBaitulmalPBN += " 	AND I.ID_PERBICARAAN = K.ID_PERBICARAAN";
	sqlJanaLaporanBaitulmalPBN += " AND K.FLAG_BATAL = '2' --batal";
    sqlJanaLaporanBaitulmalPBN += " 	AND f.id_suburusan = 59";
    sqlJanaLaporanBaitulmalPBN += " 	AND TO_CHAR(K.TARIKH_PERINTAH,'mm/yyyy')= (";
      sqlJanaLaporanBaitulmalPBN += "   	CASE ";
      sqlJanaLaporanBaitulmalPBN += "      	WHEN ind.l='1' THEN '01/"+tahun+"' ";
      sqlJanaLaporanBaitulmalPBN += "      	WHEN ind.l='2' THEN '02/"+tahun+"' ";
       sqlJanaLaporanBaitulmalPBN += "      	WHEN ind.l='3' THEN '03/"+tahun+"' ";
        sqlJanaLaporanBaitulmalPBN += "     	WHEN ind.l='4' THEN '04/"+tahun+"' ";
        sqlJanaLaporanBaitulmalPBN += "     	WHEN ind.l='5' THEN '05/"+tahun+"' ";
         sqlJanaLaporanBaitulmalPBN += "    	WHEN ind.l='6' THEN '06/"+tahun+"' ";
          sqlJanaLaporanBaitulmalPBN += "   	WHEN ind.l='7' THEN '07/"+tahun+"' ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='8' THEN '08/"+tahun+"' ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='9' THEN '09/"+tahun+"' ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='10' THEN '10/"+tahun+"'  ";
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='11' THEN '11/"+tahun+"' "; 
           sqlJanaLaporanBaitulmalPBN += "  	WHEN ind.l='12' THEN '12/"+tahun+"'  ";
       sqlJanaLaporanBaitulmalPBN += "  END )";
	sqlJanaLaporanBaitulmalPBN += " AND F.ID_NEGERI = 1 ) AS bilangan3xhadir  ";


  sqlJanaLaporanBaitulmalPBN += "   from    dual descr,";
   sqlJanaLaporanBaitulmalPBN += "  (    select  l from ";   
   sqlJanaLaporanBaitulmalPBN += "          (select level l from dual connect by level <= 12)";
  sqlJanaLaporanBaitulmalPBN += "   ) ind";
sqlJanaLaporanBaitulmalPBN += " order by 3";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();

					if (rs.getString("bilanganselesaisebelum") != null && rs.getString("bilanganselesaisebelum") != "") {
						h.put("bilanganselesaisebelum", rs.getString("bilanganselesaisebelum"));
					}

					SenaraiSerahan.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
			
		    
			}
	
	public static void setDataSenaraiSerahanDaerahtahun(String daerah, String tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=8";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.ID_DAERAHMHN ="+daerah;
		    	sqlJanaLaporanBaitulmalPBN += " and TO_CHAR(b.TARIKH_MOHON,'yyyy') ="+tahun;
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanDaerahtahunPA(String daerah, String tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and (d.id_tarafkptg=8 and e.id_tarafkptg=8)";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.ID_DAERAHMHN ="+daerah;
		    	sqlJanaLaporanBaitulmalPBN += " and TO_CHAR(b.TARIKH_MOHON,'yyyy') ="+tahun;
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanUnitselangmasa(String unit, String tarikh_mula, String tarikh_akhir) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=8";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanBaitulmalPBN += " AND b.TARIKH_MOHON between TO_DATE('"+tarikh_mula+"','dd/mm/yyyy') and TO_DATE('"+tarikh_akhir+"','dd/mm/yyyy')";
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanUnitselangmasaPA(String unit, String tarikh_mula, String tarikh_akhir) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and (d.id_tarafkptg=8 and e.id_tarafkptg=8)";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanBaitulmalPBN += " AND b.TARIKH_MOHON between TO_DATE('"+tarikh_mula+"','dd/mm/yyyy') and TO_DATE('"+tarikh_akhir+"','dd/mm/yyyy')";
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanNegeriselangmasa(String negeri, String tarikh_mula, String tarikh_akhir) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=8";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.ID_NEGERIMHN ="+negeri;
		    	sqlJanaLaporanBaitulmalPBN += " AND b.TARIKH_MOHON between TO_DATE('"+tarikh_mula+"','dd/mm/yyyy') and TO_DATE('"+tarikh_akhir+"','dd/mm/yyyy')";
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanNegeriselangmasaPA(String negeri, String tarikh_mula, String tarikh_akhir) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and (d.id_tarafkptg=8 and e.id_tarafkptg=8)";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.ID_NEGERIMHN ="+negeri;
		    	sqlJanaLaporanBaitulmalPBN += " AND b.TARIKH_MOHON between TO_DATE('"+tarikh_mula+"','dd/mm/yyyy') and TO_DATE('"+tarikh_akhir+"','dd/mm/yyyy')";
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanDaerahselangmasa(String daerah, String tarikh_mula, String tarikh_akhir) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=8";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.ID_DAERAHMHN ="+daerah;
		    	sqlJanaLaporanBaitulmalPBN += " AND b.TARIKH_MOHON between TO_DATE('"+tarikh_mula+"','dd/mm/yyyy') and TO_DATE('"+tarikh_akhir+"','dd/mm/yyyy')";
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanDaerahselangmasaPA(String daerah, String tarikh_mula, String tarikh_akhir) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and (d.id_tarafkptg=8 and e.id_tarafkptg=8)";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.ID_DAERAHMHN ="+daerah;
		    	sqlJanaLaporanBaitulmalPBN += " AND b.TARIKH_MOHON between TO_DATE('"+tarikh_mula+"','dd/mm/yyyy') and TO_DATE('"+tarikh_akhir+"','dd/mm/yyyy')";
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanUnitbulan(String unit, String mula_bulan, String mula_tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=8";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(b.TARIKH_MOHON,'mm') = '"+mula_bulan+"'";
		    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(b.TARIKH_MOHON,'yyyy') = '"+mula_tahun+"'";
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanUnitbulanPA(String unit, String mula_bulan, String mula_tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and (d.id_tarafkptg=8 and d.id_tarafkptg=8)";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(b.TARIKH_MOHON,'mm') = '"+mula_bulan+"'";
		    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(b.TARIKH_MOHON,'yyyy') = '"+mula_tahun+"'";
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanNegeribulan(String negeri, String mula_bulan, String mula_tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=8";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.ID_NEGERIMHN ="+negeri;
		    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(b.TARIKH_MOHON,'mm') = '"+mula_bulan+"'";
		    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(b.TARIKH_MOHON,'yyyy') = '"+mula_tahun+"'";
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	
	
	public static void setDataSenaraiSerahanNegeribulanPA(String negeri, String mula_bulan, String mula_tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and (d.id_tarafkptg=8 and e.id_tarafkptg=8)";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.ID_NEGERIMHN ="+negeri;
		    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(b.TARIKH_MOHON,'mm') = '"+mula_bulan+"'";
		    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(b.TARIKH_MOHON,'yyyy') = '"+mula_tahun+"'";
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanDaerahbulan(String daerah, String mula_bulan, String mula_tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=8";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.ID_DAERAHMHN ="+daerah;
		    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(b.TARIKH_MOHON,'mm') = '"+mula_bulan+"'";
		    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(b.TARIKH_MOHON,'yyyy') = '"+mula_tahun+"'";
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanDaerahbulanPA(String daerah, String mula_bulan, String mula_tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and (d.id_tarafkptg=8 and e.id_tarafkptg=8)";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.ID_DAERAHMHN ="+daerah;
		    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(b.TARIKH_MOHON,'mm') = '"+mula_bulan+"'";
		    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(b.TARIKH_MOHON,'yyyy') = '"+mula_tahun+"'";
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBNUnit = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	//
	public static void setDataSenaraiSerahanDaerah(String mula_hari, String mula_bulan, String mula_tahun, String akhir_hari, String akhir_bulan, String akhir_tahun, String akhir_daerah) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		String tarikh_mula = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
		String tarikh_akhir = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	//sqlJanaLaporanBaitulmalPBN += " where b.tarikh_mohon BETWEEN TO_DATE('"+tarikh_mula+"', 'DD/MM/YYYY') AND TO_DATE('"+tarikh_akhir+"', 'DD/MM/YYYY') ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=8";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBN = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanPBNUnit(String unit) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=17";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBN = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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

	public static void setDataSenaraiSerahanPBNUnittahun(String unit, String tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=17";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanBaitulmalPBN += " and TO_CHAR(b.TARIKH_MOHON,'yyyy') ="+tahun;
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBN = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanPBNUnitselangmasa(String unit, String tarikh_mula, String tarikh_akhir) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=17";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanBaitulmalPBN += " AND b.TARIKH_MOHON between TO_DATE('"+tarikh_mula+"','dd/mm/yyyy') and TO_DATE('"+tarikh_akhir+"','dd/mm/yyyy')";
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBN = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanPBNUnitbulan(String unit, String mula_bulan, String mula_tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=17";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_unitpskawal ="+unit;
		    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(b.TARIKH_MOHON,'mm') = '"+mula_bulan+"'";
		    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(b.TARIKH_MOHON,'yyyy') = '"+mula_tahun+"'";
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBN = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanPBNnegeri(String negeri) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=17";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and b.ID_NEGERIMHN ="+negeri;
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBN = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	
	public static void setDataSenaraiSerahanPBNnegeritahun(String negeri, String tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
	    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
	    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
	    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
	    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
	    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=17";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
	    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
	    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
	    	sqlJanaLaporanBaitulmalPBN += " and b.ID_NEGERIMHN ="+negeri;
	    	sqlJanaLaporanBaitulmalPBN += " and TO_CHAR(b.TARIKH_MOHON,'yyyy') ="+tahun;
	    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
	    	System.out.println("sqlJanaLaporanBaitulmalPBN = " + sqlJanaLaporanBaitulmalPBN);
	    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
				if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
					h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanPBNnegeriselangmasa(String negeri, String tarikh_mula, String tarikh_akhir) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
	    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
	    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
	    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
	    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
	    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=17";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
	    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
	    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
	    	sqlJanaLaporanBaitulmalPBN += " and b.ID_NEGERIMHN ="+negeri;
	    	sqlJanaLaporanBaitulmalPBN += " AND b.TARIKH_MOHON between TO_DATE('"+tarikh_mula+"','dd/mm/yyyy') and TO_DATE('"+tarikh_akhir+"','dd/mm/yyyy')";
	    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
	    	System.out.println("sqlJanaLaporanBaitulmalPBN = " + sqlJanaLaporanBaitulmalPBN);
	    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
				if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
					h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanPBNdaerah(String daerah) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
	    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
	    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
	    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
	    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
	    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=17";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
	    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
	    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
	    	sqlJanaLaporanBaitulmalPBN += " and b.ID_DAERAHMHN ="+daerah;
	    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
	    	System.out.println("sqlJanaLaporanBaitulmalPBN = " + sqlJanaLaporanBaitulmalPBN);
	    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
				if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
					h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanPBNdaerahtahun(String daerah, String tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
	    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
	    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
	    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
	    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
	    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=17";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
	    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
	    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
	    	sqlJanaLaporanBaitulmalPBN += " and b.ID_DAERAHMHN ="+daerah;
	    	sqlJanaLaporanBaitulmalPBN += " and TO_CHAR(b.TARIKH_MOHON,'yyyy') ="+tahun;
	    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
	    	System.out.println("sqlJanaLaporanBaitulmalPBN = " + sqlJanaLaporanBaitulmalPBN);
	    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
				if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
					h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanPBNdaerahselangmasa(String daerah, String tarikh_mula, String tarikh_akhir) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
	    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
	    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
	    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
	    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
	    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=17";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
	    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
	    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
	    	sqlJanaLaporanBaitulmalPBN += " and b.ID_DAERAHMHN ="+daerah;
	    	sqlJanaLaporanBaitulmalPBN += " AND b.TARIKH_MOHON between TO_DATE('"+tarikh_mula+"','dd/mm/yyyy') and TO_DATE('"+tarikh_akhir+"','dd/mm/yyyy')";
	    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
	    	System.out.println("sqlJanaLaporanBaitulmalPBN1 = " + sqlJanaLaporanBaitulmalPBN);
	    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
				if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
					h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanPBNdaerahbulan(String daerah, String mula_bulan, String mula_tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
	    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
	    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
	    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
	    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
	    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=17";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
	    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
	    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
	    	sqlJanaLaporanBaitulmalPBN += " and b.ID_DAERAHMHN ="+daerah;
	    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(b.TARIKH_MOHON,'mm') = '"+mula_bulan+"'";
	    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(b.TARIKH_MOHON,'yyyy') = '"+mula_tahun+"'";
	    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
	    	System.out.println("sqlJanaLaporanBaitulmalPBN2 = " + sqlJanaLaporanBaitulmalPBN);
	    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
				if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
					h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public static void setDataSenaraiSerahanPBNnegeribulan(String negeri, String mula_bulan, String mula_tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		//System.out.println("Dalam SaveData");
		try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
	    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
	    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
	    	sqlJanaLaporanBaitulmalPBN += " where a.id_fail=b.id_fail ";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
	    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
	    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
	    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=17";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
	    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
	    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
	    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
	    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
	    	sqlJanaLaporanBaitulmalPBN += " and b.ID_NEGERIMHN ="+negeri;
	    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(b.TARIKH_MOHON,'mm') = '"+mula_bulan+"'";
	    	sqlJanaLaporanBaitulmalPBN += " AND TO_CHAR(b.TARIKH_MOHON,'yyyy') = '"+mula_tahun+"'";
	    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
	    	System.out.println("sqlJanaLaporanBaitulmalPBN3 = " + sqlJanaLaporanBaitulmalPBN);
	    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
				if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
					h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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
	
	public void setDataSenaraiPrestasiKPI(String mula_bulan, String mula_tahun, String section, String negeri) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPrestasiKPI = "";
		List senaraiFail = null;
		
		//System.out.println("Dalam SaveData");
		try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sqlJanaLaporanPrestasiKPI = "SELECT LAYER, ID_NEGERI, NAMA_NEGERI, ID_PEJABATJKPTG,NAMA_PEJABAT, ID_DAERAHMHN, NAMA_DAERAH,JUMLAH_MOHON,JUMLAH_SELESAI, KPI FROM "; 
	    	sqlJanaLaporanPrestasiKPI += "(";
	    	sqlJanaLaporanPrestasiKPI += "SELECT 1 AS LAYER, PEJ.ID_NEGERI, N.NAMA_NEGERI, PEJ.ID_PEJABATJKPTG,PEJ.NAMA_PEJABAT, TO_CHAR(P.ID_DAERAHMHN) AS ID_DAERAHMHN, D.NAMA_DAERAH,"; 
	    	sqlJanaLaporanPrestasiKPI += "COUNT(DISTINCT P.ID_PERMOHONAN) AS JUMLAH_MOHON, ";
	    	sqlJanaLaporanPrestasiKPI += "COUNT  ";
	    	sqlJanaLaporanPrestasiKPI += "(DISTINCT CASE  ";
	    	sqlJanaLaporanPrestasiKPI += "    WHEN   ";
	    	sqlJanaLaporanPrestasiKPI += " stf.ID_SUBURUSANSTATUS IN (  ";
	    	sqlJanaLaporanPrestasiKPI += " SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS IN(21,47,70,169,152,70,50 )  ";
	    	sqlJanaLaporanPrestasiKPI += "       )  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND stf.aktif = '1'  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND (stf.tarikh_masuk - p.tarikh_mohon) < 136  ";
	    	sqlJanaLaporanPrestasiKPI += "     THEN p.id_permohonan  ";
	    	sqlJanaLaporanPrestasiKPI += " END  ";
	    	sqlJanaLaporanPrestasiKPI += " ) AS jumlah_selesai  ";
	    	sqlJanaLaporanPrestasiKPI += ",TO_CHAR(";
	    	sqlJanaLaporanPrestasiKPI += "(COUNT(DISTINCT CASE WHEN P.ID_STATUS = 21 AND STF.ID_SUBURUSANSTATUS = 358 AND (STF.TARIKH_MASUK - P.TARIKH_MOHON) < 136 THEN P.ID_PERMOHONAN END) / COUNT(DISTINCT P.ID_PERMOHONAN)  ) * 100"; 
	    	sqlJanaLaporanPrestasiKPI += ",'990.00') AS KPI  ";
	    	sqlJanaLaporanPrestasiKPI += "FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLRUJSUBURUSANSTATUSFAIL STF, ";
	    	sqlJanaLaporanPrestasiKPI += "TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU, TBLRUJNEGERI N, TBLRUJDAERAH D ";
	    	sqlJanaLaporanPrestasiKPI += "WHERE F.ID_FAIL = P.ID_FAIL AND STF.ID_FAIL = F.ID_FAIL AND STF.AKTIF = 1 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = PU.ID_DAERAHURUS ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_SEKSYEN = 2 ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_JENISPEJABAT = 22 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.SEKSYEN = " + section;
	    	if (negeri != "-1")
	    	{
	    		sqlJanaLaporanPrestasiKPI += " AND P.ID_NEGERIMHN = " + negeri;
	    	}
	    	sqlJanaLaporanPrestasiKPI += " AND P.TARIKH_MOHON IS NOT NULL ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_NEGERI = N.ID_NEGERI ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = D.ID_DAERAH ";
	    	sqlJanaLaporanPrestasiKPI += "AND TO_CHAR(P.TARIKH_MOHON,'MM/YYYY') = TO_CHAR(add_months(TO_DATE('"+mula_bulan+"/"+mula_tahun+"','MM/YYYY'), -5),'MM/YYYY') ";
	    	sqlJanaLaporanPrestasiKPI += "GROUP BY PEJ.ID_NEGERI, N.NAMA_NEGERI, PEJ.ID_PEJABATJKPTG, PEJ.NAMA_PEJABAT, P.ID_DAERAHMHN, D.NAMA_DAERAH ";
	    	sqlJanaLaporanPrestasiKPI += "UNION ALL ";
	    	sqlJanaLaporanPrestasiKPI += "SELECT 2 AS LAYER, PEJ.ID_NEGERI, N.NAMA_NEGERI, PEJ.ID_PEJABATJKPTG,'JUMLAH' AS NAMA_PEJABAT, '' AS ID_DAERAHMHN, '' AS NAMA_DAERAH,"; 
	    	sqlJanaLaporanPrestasiKPI += "COUNT(DISTINCT P.ID_PERMOHONAN) AS JUMLAH_MOHON,  ";
	    	sqlJanaLaporanPrestasiKPI += "COUNT  ";
	    	sqlJanaLaporanPrestasiKPI += "(DISTINCT CASE  ";
	    	sqlJanaLaporanPrestasiKPI += "    WHEN   ";
	    	sqlJanaLaporanPrestasiKPI += " stf.ID_SUBURUSANSTATUS IN (  ";
	    	sqlJanaLaporanPrestasiKPI += " SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS IN(21,47,70,169,152,70,50 )  ";
	    	sqlJanaLaporanPrestasiKPI += "       )  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND stf.aktif = '1'  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND (stf.tarikh_masuk - p.tarikh_mohon) < 136  ";
	    	sqlJanaLaporanPrestasiKPI += "     THEN p.id_permohonan  ";
	    	sqlJanaLaporanPrestasiKPI += " END  ";
	    	sqlJanaLaporanPrestasiKPI += " ) AS jumlah_selesai  ";
	    	sqlJanaLaporanPrestasiKPI += ",TO_CHAR(";
	    	sqlJanaLaporanPrestasiKPI += "(COUNT(DISTINCT CASE WHEN P.ID_STATUS = 21 AND STF.ID_SUBURUSANSTATUS = 358 AND (STF.TARIKH_MASUK - P.TARIKH_MOHON) < 136 THEN P.ID_PERMOHONAN END) / COUNT(DISTINCT P.ID_PERMOHONAN)  ) * 100"; 
	    	sqlJanaLaporanPrestasiKPI += ",'990.00') AS KPI  ";
	    	sqlJanaLaporanPrestasiKPI += "FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLRUJSUBURUSANSTATUSFAIL STF, ";
	    	sqlJanaLaporanPrestasiKPI += "TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU, TBLRUJNEGERI N, TBLRUJDAERAH D ";
	    	sqlJanaLaporanPrestasiKPI += "WHERE F.ID_FAIL = P.ID_FAIL AND STF.ID_FAIL = F.ID_FAIL AND STF.AKTIF = 1 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = PU.ID_DAERAHURUS ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_SEKSYEN = 2 ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_JENISPEJABAT = 22 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.SEKSYEN = " + section;
	    	if (negeri != "-1")
	    	{
	    		sqlJanaLaporanPrestasiKPI += " AND P.ID_NEGERIMHN = " + negeri;
	    	}
	    	sqlJanaLaporanPrestasiKPI += "AND P.TARIKH_MOHON IS NOT NULL ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_NEGERI = N.ID_NEGERI ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = D.ID_DAERAH ";
	    	sqlJanaLaporanPrestasiKPI += "AND TO_CHAR(P.TARIKH_MOHON,'MM/YYYY') = TO_CHAR(add_months(TO_DATE('"+mula_bulan+"/"+mula_tahun+"','MM/YYYY'), -5),'MM/YYYY') ";
	    	sqlJanaLaporanPrestasiKPI += "GROUP BY PEJ.ID_NEGERI, N.NAMA_NEGERI, PEJ.ID_PEJABATJKPTG ";
	    	sqlJanaLaporanPrestasiKPI += ") ";
	    	sqlJanaLaporanPrestasiKPI += "ORDER BY ID_NEGERI, ID_PEJABATJKPTG, LAYER, ID_DAERAHMHN ";
	    	System.out.println("sqlJanaLaporanPrestasiKPI = " + sqlJanaLaporanPrestasiKPI);
	    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPrestasiKPI);
	    	Hashtable h;
	    	//con.commit();
	    	while (rs.next()) {
				h = new Hashtable();

				if (rs.getString("LAYER") != null && rs.getString("LAYER") != "") {
					h.put("LAYER", rs.getString("LAYER"));
				}
				if (rs.getString("ID_NEGERI") != null && rs.getString("ID_NEGERI") != "") {
					h.put("ID_NEGERI", rs.getString("ID_NEGERI"));
				}
				if (rs.getString("NAMA_NEGERI") != null && rs.getString("NAMA_NEGERI") != "") {
					h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI"));
				}
				if (rs.getString("NAMA_PEJABAT") != null && rs.getString("NAMA_PEJABAT") != "") {
					h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT"));
				}
				if (rs.getString("NAMA_DAERAH") != null && rs.getString("NAMA_DAERAH") != "") {
					h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
				}
				if (rs.getString("JUMLAH_MOHON") != null && rs.getString("JUMLAH_MOHON") != "") {
					h.put("JUMLAH_MOHON", rs.getInt("JUMLAH_MOHON"));
				}
				if (rs.getString("JUMLAH_SELESAI") != null && rs.getString("JUMLAH_SELESAI") != "") {
					h.put("JUMLAH_SELESAI", rs.getInt("JUMLAH_SELESAI"));
				}
				if (rs.getString("KPI") != null && rs.getString("KPI") != "") {
					h.put("KPI", rs.getString("KPI"));
				}
				SenaraiSerahan.addElement(h);
				}
	   
	    	}
	    
	    finally {
		      if (db != null) db.close();
	    }
		
	    
		}
	
	public void setDataSenaraiPrestasiKPI120(String mula_bulan, String mula_tahun, String section, String negeri) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPrestasiKPI = "";
		List senaraiFail = null;
		
		//System.out.println("Dalam SaveData");
		try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sqlJanaLaporanPrestasiKPI = "SELECT LAYER, ID_NEGERI, NAMA_NEGERI, ID_PEJABATJKPTG,NAMA_PEJABAT, ID_DAERAHMHN, NAMA_DAERAH,JUMLAH_MOHON,JUMLAH_SELESAI, KPI FROM "; 
	    	sqlJanaLaporanPrestasiKPI += "(";
	    	sqlJanaLaporanPrestasiKPI += "SELECT 1 AS LAYER, PEJ.ID_NEGERI, N.NAMA_NEGERI, PEJ.ID_PEJABATJKPTG,PEJ.NAMA_PEJABAT, TO_CHAR(P.ID_DAERAHMHN) AS ID_DAERAHMHN, D.NAMA_DAERAH,"; 
	    	sqlJanaLaporanPrestasiKPI += "COUNT(DISTINCT P.ID_PERMOHONAN) AS JUMLAH_MOHON, ";
	    	sqlJanaLaporanPrestasiKPI += "COUNT  ";
	    	sqlJanaLaporanPrestasiKPI += "(DISTINCT CASE  ";
	    	sqlJanaLaporanPrestasiKPI += "    WHEN   ";
	    	sqlJanaLaporanPrestasiKPI += " stf.ID_SUBURUSANSTATUS IN (  ";
	    	sqlJanaLaporanPrestasiKPI += " SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS IN(21,47,70,169,152,70,50 )  ";
	    	sqlJanaLaporanPrestasiKPI += "       )  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND stf.aktif = '1'  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND (stf.tarikh_masuk - p.tarikh_mohon) < 121  ";
	    	sqlJanaLaporanPrestasiKPI += "     THEN p.id_permohonan  ";
	    	sqlJanaLaporanPrestasiKPI += " END  ";
	    	sqlJanaLaporanPrestasiKPI += " ) AS jumlah_selesai  ";
	    	sqlJanaLaporanPrestasiKPI += ",TO_CHAR(";
	    	sqlJanaLaporanPrestasiKPI += "(COUNT(DISTINCT CASE WHEN P.ID_STATUS = 21 AND STF.ID_SUBURUSANSTATUS = 358 AND (STF.TARIKH_MASUK - P.TARIKH_MOHON) < 121 THEN P.ID_PERMOHONAN END) / COUNT(DISTINCT P.ID_PERMOHONAN)  ) * 100"; 
	    	sqlJanaLaporanPrestasiKPI += ",'990.00') AS KPI  ";
	    	sqlJanaLaporanPrestasiKPI += "FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLRUJSUBURUSANSTATUSFAIL STF, ";
	    	sqlJanaLaporanPrestasiKPI += "TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU, TBLRUJNEGERI N, TBLRUJDAERAH D ";
	    	sqlJanaLaporanPrestasiKPI += "WHERE F.ID_FAIL = P.ID_FAIL AND STF.ID_FAIL = F.ID_FAIL AND STF.AKTIF = 1 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = PU.ID_DAERAHURUS ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_SEKSYEN = 2 ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_JENISPEJABAT = 22 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.SEKSYEN = " + section;
	    	if (negeri != "-1")
	    	{
	    		sqlJanaLaporanPrestasiKPI += " AND P.ID_NEGERIMHN = " + negeri;
	    	}
	    	sqlJanaLaporanPrestasiKPI += " AND P.TARIKH_MOHON IS NOT NULL ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_NEGERI = N.ID_NEGERI ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = D.ID_DAERAH ";
	    	
	    	sqlJanaLaporanPrestasiKPI += "AND TO_CHAR(P.TARIKH_MOHON,'MM/YYYY') = TO_CHAR(add_months(TO_DATE('"+mula_bulan+"/"+mula_tahun+"','MM/YYYY'), -4),'MM/YYYY') ";
	    	sqlJanaLaporanPrestasiKPI += "GROUP BY PEJ.ID_NEGERI, N.NAMA_NEGERI, PEJ.ID_PEJABATJKPTG, PEJ.NAMA_PEJABAT, P.ID_DAERAHMHN, D.NAMA_DAERAH ";
	    	sqlJanaLaporanPrestasiKPI += "UNION ALL ";
	    	sqlJanaLaporanPrestasiKPI += "SELECT 2 AS LAYER, PEJ.ID_NEGERI, N.NAMA_NEGERI, PEJ.ID_PEJABATJKPTG,'JUMLAH' AS NAMA_PEJABAT, '' AS ID_DAERAHMHN, '' AS NAMA_DAERAH,"; 
	    	sqlJanaLaporanPrestasiKPI += "COUNT(DISTINCT P.ID_PERMOHONAN) AS JUMLAH_MOHON,  ";
	    	sqlJanaLaporanPrestasiKPI += "COUNT  ";
	    	sqlJanaLaporanPrestasiKPI += "(DISTINCT CASE  ";
	    	sqlJanaLaporanPrestasiKPI += "    WHEN   ";
	    	sqlJanaLaporanPrestasiKPI += " stf.ID_SUBURUSANSTATUS IN (  ";
	    	sqlJanaLaporanPrestasiKPI += " SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS IN(21,47,70,169,152,70,50 )  ";
	    	sqlJanaLaporanPrestasiKPI += "       )  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND stf.aktif = '1'  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND (stf.tarikh_masuk - p.tarikh_mohon) < 121  ";
	    	sqlJanaLaporanPrestasiKPI += "     THEN p.id_permohonan  ";
	    	sqlJanaLaporanPrestasiKPI += " END  ";
	    	sqlJanaLaporanPrestasiKPI += " ) AS jumlah_selesai  ";
	    	sqlJanaLaporanPrestasiKPI += ",TO_CHAR(";
	    	sqlJanaLaporanPrestasiKPI += "(COUNT(DISTINCT CASE WHEN P.ID_STATUS = 21 AND STF.ID_SUBURUSANSTATUS = 358 AND (STF.TARIKH_MASUK - P.TARIKH_MOHON) < 121 THEN P.ID_PERMOHONAN END) / COUNT(DISTINCT P.ID_PERMOHONAN)  ) * 100"; 
	    	sqlJanaLaporanPrestasiKPI += ",'990.00') AS KPI  ";
	    	sqlJanaLaporanPrestasiKPI += "FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLRUJSUBURUSANSTATUSFAIL STF, ";
	    	sqlJanaLaporanPrestasiKPI += "TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU, TBLRUJNEGERI N, TBLRUJDAERAH D ";
	    	sqlJanaLaporanPrestasiKPI += "WHERE F.ID_FAIL = P.ID_FAIL AND STF.ID_FAIL = F.ID_FAIL AND STF.AKTIF = 1 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = PU.ID_DAERAHURUS ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_SEKSYEN = 2 ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_JENISPEJABAT = 22 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.SEKSYEN = " + section;
	    	if (negeri != "-1")
	    	{
	    		sqlJanaLaporanPrestasiKPI += " AND P.ID_NEGERIMHN = " + negeri;
	    	}
	    	sqlJanaLaporanPrestasiKPI += "AND P.TARIKH_MOHON IS NOT NULL ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_NEGERI = N.ID_NEGERI ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = D.ID_DAERAH ";
	    	
	    	sqlJanaLaporanPrestasiKPI += "AND TO_CHAR(P.TARIKH_MOHON,'MM/YYYY') = TO_CHAR(add_months(TO_DATE('"+mula_bulan+"/"+mula_tahun+"','MM/YYYY'), -4),'MM/YYYY') ";
	    	sqlJanaLaporanPrestasiKPI += "GROUP BY PEJ.ID_NEGERI, N.NAMA_NEGERI, PEJ.ID_PEJABATJKPTG ";
	    	sqlJanaLaporanPrestasiKPI += ") ";
	    	sqlJanaLaporanPrestasiKPI += "ORDER BY ID_NEGERI, ID_PEJABATJKPTG, LAYER, ID_DAERAHMHN ";
	    	System.out.println("sqlJanaLaporanPrestasiKPI = " + sqlJanaLaporanPrestasiKPI);
	    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPrestasiKPI);
	    	Hashtable h;
	    	//con.commit();
	    	while (rs.next()) {
				h = new Hashtable();

				if (rs.getString("LAYER") != null && rs.getString("LAYER") != "") {
					h.put("LAYER", rs.getString("LAYER"));
				}
				if (rs.getString("ID_NEGERI") != null && rs.getString("ID_NEGERI") != "") {
					h.put("ID_NEGERI", rs.getString("ID_NEGERI"));
				}
				if (rs.getString("NAMA_NEGERI") != null && rs.getString("NAMA_NEGERI") != "") {
					h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI"));
				}
				if (rs.getString("NAMA_PEJABAT") != null && rs.getString("NAMA_PEJABAT") != "") {
					h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT"));
				}
				if (rs.getString("NAMA_DAERAH") != null && rs.getString("NAMA_DAERAH") != "") {
					h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
				}
				if (rs.getString("JUMLAH_MOHON") != null && rs.getString("JUMLAH_MOHON") != "") {
					h.put("JUMLAH_MOHON", rs.getInt("JUMLAH_MOHON"));
				}
				if (rs.getString("JUMLAH_SELESAI") != null && rs.getString("JUMLAH_SELESAI") != "") {
					h.put("JUMLAH_SELESAI", rs.getInt("JUMLAH_SELESAI"));
				}
				if (rs.getString("KPI") != null && rs.getString("KPI") != "") {
					h.put("KPI", rs.getString("KPI"));
				}
				SenaraiSerahan.addElement(h);
				}
	   
	    	}
	    
	    finally {
		      if (db != null) db.close();
	    }
		
	    
		}
	
	public void setDataSenaraiPrestasiKPIsemuaState135byUnit(String mula_bulan, String mula_tahun, String section) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahanAllState.clear();
		Db db = null;
		String sqlJanaLaporanPrestasiKPI = "";
		List senaraiFail = null;
		
		//System.out.println("Dalam SaveData");
		try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sqlJanaLaporanPrestasiKPI = "SELECT LAYER, ID_NEGERI, NAMA_NEGERI, ID_PEJABATJKPTG,NAMA_PEJABAT, ID_DAERAHMHN, NAMA_DAERAH,JUMLAH_MOHON,JUMLAH_SELESAI, KPI FROM "; 
	    	sqlJanaLaporanPrestasiKPI += "(";
	    	sqlJanaLaporanPrestasiKPI += "SELECT 1 AS LAYER, PEJ.ID_NEGERI, N.NAMA_NEGERI, PEJ.ID_PEJABATJKPTG,PEJ.NAMA_PEJABAT, TO_CHAR(P.ID_DAERAHMHN) AS ID_DAERAHMHN, D.NAMA_DAERAH,"; 
	    	sqlJanaLaporanPrestasiKPI += "COUNT(DISTINCT P.ID_PERMOHONAN) AS JUMLAH_MOHON, ";
	    	sqlJanaLaporanPrestasiKPI += "COUNT  ";
	    	sqlJanaLaporanPrestasiKPI += "(DISTINCT CASE  ";
	    	sqlJanaLaporanPrestasiKPI += "    WHEN   ";
	    	sqlJanaLaporanPrestasiKPI += " stf.ID_SUBURUSANSTATUS IN (  ";
	    	sqlJanaLaporanPrestasiKPI += " SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS IN(21,47,70,169,152,70,50 )  ";
	    	sqlJanaLaporanPrestasiKPI += "       )  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND stf.aktif = '1'  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND (stf.tarikh_masuk - p.tarikh_mohon) < 136  ";
	    	sqlJanaLaporanPrestasiKPI += "     THEN p.id_permohonan  ";
	    	sqlJanaLaporanPrestasiKPI += " END  ";
	    	sqlJanaLaporanPrestasiKPI += " ) AS jumlah_selesai  ";
	    	sqlJanaLaporanPrestasiKPI += ",TO_CHAR(";
	    	sqlJanaLaporanPrestasiKPI += "(COUNT(DISTINCT CASE WHEN P.ID_STATUS = 21 AND STF.ID_SUBURUSANSTATUS = 358 AND (STF.TARIKH_MASUK - P.TARIKH_MOHON) < 136 THEN P.ID_PERMOHONAN END) / COUNT(DISTINCT P.ID_PERMOHONAN)  ) * 100"; 
	    	sqlJanaLaporanPrestasiKPI += ",'990.00') AS KPI  ";
	    	sqlJanaLaporanPrestasiKPI += "FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLRUJSUBURUSANSTATUSFAIL STF, ";
	    	sqlJanaLaporanPrestasiKPI += "TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU, TBLRUJNEGERI N, TBLRUJDAERAH D ";
	    	sqlJanaLaporanPrestasiKPI += "WHERE F.ID_FAIL = P.ID_FAIL AND STF.ID_FAIL = F.ID_FAIL AND STF.AKTIF = 1 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = PU.ID_DAERAHURUS ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_SEKSYEN = 2 ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_JENISPEJABAT = 22 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.SEKSYEN = " + section;
	    	sqlJanaLaporanPrestasiKPI += " AND P.TARIKH_MOHON IS NOT NULL ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_NEGERI = N.ID_NEGERI ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = D.ID_DAERAH ";
	    	sqlJanaLaporanPrestasiKPI += "AND TO_CHAR(P.TARIKH_MOHON,'MM/YYYY') = TO_CHAR(add_months(TO_DATE('"+mula_bulan+"/"+mula_tahun+"','MM/YYYY'), -5),'MM/YYYY') ";
	    	sqlJanaLaporanPrestasiKPI += "GROUP BY PEJ.ID_NEGERI, N.NAMA_NEGERI, PEJ.ID_PEJABATJKPTG, PEJ.NAMA_PEJABAT, P.ID_DAERAHMHN, D.NAMA_DAERAH ";
	    	sqlJanaLaporanPrestasiKPI += "UNION ALL ";
	    	sqlJanaLaporanPrestasiKPI += "SELECT 2 AS LAYER, PEJ.ID_NEGERI, N.NAMA_NEGERI, PEJ.ID_PEJABATJKPTG,'JUMLAH' AS NAMA_PEJABAT, '' AS ID_DAERAHMHN, '' AS NAMA_DAERAH,"; 
	    	sqlJanaLaporanPrestasiKPI += "COUNT(DISTINCT P.ID_PERMOHONAN) AS JUMLAH_MOHON,  ";
	    	sqlJanaLaporanPrestasiKPI += "COUNT  ";
	    	sqlJanaLaporanPrestasiKPI += "(DISTINCT CASE  ";
	    	sqlJanaLaporanPrestasiKPI += "    WHEN   ";
	    	sqlJanaLaporanPrestasiKPI += " stf.ID_SUBURUSANSTATUS IN (  ";
	    	sqlJanaLaporanPrestasiKPI += " SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS IN(21,47,70,169,152,70,50 )  ";
	    	sqlJanaLaporanPrestasiKPI += "       )  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND stf.aktif = '1'  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND (stf.tarikh_masuk - p.tarikh_mohon) < 136  ";
	    	sqlJanaLaporanPrestasiKPI += "     THEN p.id_permohonan  ";
	    	sqlJanaLaporanPrestasiKPI += " END  ";
	    	sqlJanaLaporanPrestasiKPI += " ) AS jumlah_selesai  ";
	    	sqlJanaLaporanPrestasiKPI += ",TO_CHAR(";
	    	sqlJanaLaporanPrestasiKPI += "(COUNT(DISTINCT CASE WHEN P.ID_STATUS = 21 AND STF.ID_SUBURUSANSTATUS = 358 AND (STF.TARIKH_MASUK - P.TARIKH_MOHON) < 136 THEN P.ID_PERMOHONAN END) / COUNT(DISTINCT P.ID_PERMOHONAN)  ) * 100"; 
	    	sqlJanaLaporanPrestasiKPI += ",'990.00') AS KPI  ";
	    	sqlJanaLaporanPrestasiKPI += "FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLRUJSUBURUSANSTATUSFAIL STF, ";
	    	sqlJanaLaporanPrestasiKPI += "TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU, TBLRUJNEGERI N, TBLRUJDAERAH D ";
	    	sqlJanaLaporanPrestasiKPI += "WHERE F.ID_FAIL = P.ID_FAIL AND STF.ID_FAIL = F.ID_FAIL AND STF.AKTIF = 1 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = PU.ID_DAERAHURUS ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_SEKSYEN = 2 ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_JENISPEJABAT = 22 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.SEKSYEN = " + section;
	    	sqlJanaLaporanPrestasiKPI += "AND P.TARIKH_MOHON IS NOT NULL ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_NEGERI = N.ID_NEGERI ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = D.ID_DAERAH ";
	    	sqlJanaLaporanPrestasiKPI += "AND TO_CHAR(P.TARIKH_MOHON,'MM/YYYY') = TO_CHAR(add_months(TO_DATE('"+mula_bulan+"/"+mula_tahun+"','MM/YYYY'), -5),'MM/YYYY') ";
	    	sqlJanaLaporanPrestasiKPI += "GROUP BY PEJ.ID_NEGERI, N.NAMA_NEGERI, PEJ.ID_PEJABATJKPTG ";
	    	sqlJanaLaporanPrestasiKPI += ") ";
	    	sqlJanaLaporanPrestasiKPI += "ORDER BY ID_NEGERI, ID_PEJABATJKPTG, LAYER, ID_DAERAHMHN ";
	    	System.out.println("sqlJanaLaporanPrestasiKPI = " + sqlJanaLaporanPrestasiKPI);
	    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPrestasiKPI);
	    	Hashtable h;
	    	//con.commit();
	    	while (rs.next()) {
				h = new Hashtable();

				if (rs.getString("LAYER") != null && rs.getString("LAYER") != "") {
					h.put("LAYER", rs.getString("LAYER"));
				}
				if (rs.getString("ID_NEGERI") != null && rs.getString("ID_NEGERI") != "") {
					h.put("ID_NEGERI", rs.getString("ID_NEGERI"));
				}
				if (rs.getString("NAMA_NEGERI") != null && rs.getString("NAMA_NEGERI") != "") {
					h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI"));
				}
				if (rs.getString("NAMA_PEJABAT") != null && rs.getString("NAMA_PEJABAT") != "") {
					h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT"));
				}
				if (rs.getString("NAMA_DAERAH") != null && rs.getString("NAMA_DAERAH") != "") {
					h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
				}
				if (rs.getString("JUMLAH_MOHON") != null && rs.getString("JUMLAH_MOHON") != "") {
					h.put("JUMLAH_MOHON", rs.getInt("JUMLAH_MOHON"));
				}
				if (rs.getString("JUMLAH_SELESAI") != null && rs.getString("JUMLAH_SELESAI") != "") {
					h.put("JUMLAH_SELESAI", rs.getInt("JUMLAH_SELESAI"));
				}
				if (rs.getString("KPI") != null && rs.getString("KPI") != "") {
					h.put("KPI", rs.getString("KPI"));
				}
				SenaraiSerahanAllState.addElement(h);
				}
	   
	    	}
	    
	    finally {
		      if (db != null) db.close();
	    }
		
	    
		}
	
	public void setDataSenaraiPrestasiKPIsemuaStatebyUnit(String mula_bulan, String mula_tahun, String section) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahanAllState.clear();
		Db db = null;
		String sqlJanaLaporanPrestasiKPI = "";
		List senaraiFail = null;
		
		//System.out.println("Dalam SaveData");
		try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sqlJanaLaporanPrestasiKPI = "SELECT LAYER, ID_NEGERI, NAMA_NEGERI, ID_PEJABATJKPTG,NAMA_PEJABAT, ID_DAERAHMHN, NAMA_DAERAH,JUMLAH_MOHON,JUMLAH_SELESAI, KPI FROM "; 
	    	sqlJanaLaporanPrestasiKPI += "(";
	    	sqlJanaLaporanPrestasiKPI += "SELECT 1 AS LAYER, PEJ.ID_NEGERI, N.NAMA_NEGERI, PEJ.ID_PEJABATJKPTG,PEJ.NAMA_PEJABAT, TO_CHAR(P.ID_DAERAHMHN) AS ID_DAERAHMHN, D.NAMA_DAERAH,"; 
	    	sqlJanaLaporanPrestasiKPI += "COUNT(DISTINCT P.ID_PERMOHONAN) AS JUMLAH_MOHON, ";
	    	sqlJanaLaporanPrestasiKPI += "COUNT  ";
	    	sqlJanaLaporanPrestasiKPI += "(DISTINCT CASE  ";
	    	sqlJanaLaporanPrestasiKPI += "    WHEN   ";
	    	sqlJanaLaporanPrestasiKPI += " stf.ID_SUBURUSANSTATUS IN (  ";
	    	sqlJanaLaporanPrestasiKPI += " SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS IN(21,47,70,169,152,70,50 )  ";
	    	sqlJanaLaporanPrestasiKPI += "       )  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND stf.aktif = '1'  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND (stf.tarikh_masuk - p.tarikh_mohon) < 121  ";
	    	sqlJanaLaporanPrestasiKPI += "     THEN p.id_permohonan  ";
	    	sqlJanaLaporanPrestasiKPI += " END  ";
	    	sqlJanaLaporanPrestasiKPI += " ) AS jumlah_selesai  ";
	    	sqlJanaLaporanPrestasiKPI += ",TO_CHAR(";
	    	sqlJanaLaporanPrestasiKPI += "(COUNT(DISTINCT CASE WHEN P.ID_STATUS = 21 AND STF.ID_SUBURUSANSTATUS = 358 AND (STF.TARIKH_MASUK - P.TARIKH_MOHON) < 121 THEN P.ID_PERMOHONAN END) / COUNT(DISTINCT P.ID_PERMOHONAN)  ) * 100"; 
	    	sqlJanaLaporanPrestasiKPI += ",'990.00') AS KPI  ";
	    	sqlJanaLaporanPrestasiKPI += "FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLRUJSUBURUSANSTATUSFAIL STF, ";
	    	sqlJanaLaporanPrestasiKPI += "TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU, TBLRUJNEGERI N, TBLRUJDAERAH D ";
	    	sqlJanaLaporanPrestasiKPI += "WHERE F.ID_FAIL = P.ID_FAIL AND STF.ID_FAIL = F.ID_FAIL AND STF.AKTIF = 1 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = PU.ID_DAERAHURUS ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_SEKSYEN = 2 ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_JENISPEJABAT = 22 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.SEKSYEN = " + section;
	    	sqlJanaLaporanPrestasiKPI += " AND P.TARIKH_MOHON IS NOT NULL ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_NEGERI = N.ID_NEGERI ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = D.ID_DAERAH ";

	    	sqlJanaLaporanPrestasiKPI += "AND TO_CHAR(P.TARIKH_MOHON,'MM/YYYY') = TO_CHAR(add_months(TO_DATE('"+mula_bulan+"/"+mula_tahun+"','MM/YYYY'), -4),'MM/YYYY') ";
	    	sqlJanaLaporanPrestasiKPI += "GROUP BY PEJ.ID_NEGERI, N.NAMA_NEGERI, PEJ.ID_PEJABATJKPTG, PEJ.NAMA_PEJABAT, P.ID_DAERAHMHN, D.NAMA_DAERAH ";
	    	sqlJanaLaporanPrestasiKPI += "UNION ALL ";
	    	sqlJanaLaporanPrestasiKPI += "SELECT 2 AS LAYER, PEJ.ID_NEGERI, N.NAMA_NEGERI, PEJ.ID_PEJABATJKPTG,'JUMLAH' AS NAMA_PEJABAT, '' AS ID_DAERAHMHN, '' AS NAMA_DAERAH,"; 
	    	sqlJanaLaporanPrestasiKPI += "COUNT(DISTINCT P.ID_PERMOHONAN) AS JUMLAH_MOHON,  ";
	    	sqlJanaLaporanPrestasiKPI += "COUNT  ";
	    	sqlJanaLaporanPrestasiKPI += "(DISTINCT CASE  ";
	    	sqlJanaLaporanPrestasiKPI += "    WHEN   ";
	    	sqlJanaLaporanPrestasiKPI += " stf.ID_SUBURUSANSTATUS IN (  ";
	    	sqlJanaLaporanPrestasiKPI += " SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS IN(21,47,70,169,152,70,50 )  ";
	    	sqlJanaLaporanPrestasiKPI += "       )  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND stf.aktif = '1'  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND (stf.tarikh_masuk - p.tarikh_mohon) < 121  ";
	    	sqlJanaLaporanPrestasiKPI += "     THEN p.id_permohonan  ";
	    	sqlJanaLaporanPrestasiKPI += " END  ";
	    	sqlJanaLaporanPrestasiKPI += " ) AS jumlah_selesai  ";
	    	sqlJanaLaporanPrestasiKPI += ",TO_CHAR(";
	    	sqlJanaLaporanPrestasiKPI += "(COUNT(DISTINCT CASE WHEN P.ID_STATUS = 21 AND STF.ID_SUBURUSANSTATUS = 358 AND (STF.TARIKH_MASUK - P.TARIKH_MOHON) < 121 THEN P.ID_PERMOHONAN END) / COUNT(DISTINCT P.ID_PERMOHONAN)  ) * 100"; 
	    	sqlJanaLaporanPrestasiKPI += ",'990.00') AS KPI  ";
	    	sqlJanaLaporanPrestasiKPI += "FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLRUJSUBURUSANSTATUSFAIL STF, ";
	    	sqlJanaLaporanPrestasiKPI += "TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU, TBLRUJNEGERI N, TBLRUJDAERAH D ";
	    	sqlJanaLaporanPrestasiKPI += "WHERE F.ID_FAIL = P.ID_FAIL AND STF.ID_FAIL = F.ID_FAIL AND STF.AKTIF = 1 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = PU.ID_DAERAHURUS ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_SEKSYEN = 2 ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_JENISPEJABAT = 22 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.SEKSYEN = " + section;
	    	sqlJanaLaporanPrestasiKPI += "AND P.TARIKH_MOHON IS NOT NULL ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_NEGERI = N.ID_NEGERI ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = D.ID_DAERAH ";
	    	
	    	sqlJanaLaporanPrestasiKPI += "AND TO_CHAR(P.TARIKH_MOHON,'MM/YYYY') = TO_CHAR(add_months(TO_DATE('"+mula_bulan+"/"+mula_tahun+"','MM/YYYY'), -4),'MM/YYYY') ";
	    	sqlJanaLaporanPrestasiKPI += "GROUP BY PEJ.ID_NEGERI, N.NAMA_NEGERI, PEJ.ID_PEJABATJKPTG ";
	    	sqlJanaLaporanPrestasiKPI += ") ";
	    	sqlJanaLaporanPrestasiKPI += "ORDER BY ID_NEGERI, ID_PEJABATJKPTG, LAYER, ID_DAERAHMHN ";
	    	System.out.println("sqlJanaLaporanPrestasiKPI = " + sqlJanaLaporanPrestasiKPI);
	    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPrestasiKPI);
	    	Hashtable h;
	    	//con.commit();
	    	while (rs.next()) {
				h = new Hashtable();

				if (rs.getString("LAYER") != null && rs.getString("LAYER") != "") {
					h.put("LAYER", rs.getString("LAYER"));
				}
				if (rs.getString("ID_NEGERI") != null && rs.getString("ID_NEGERI") != "") {
					h.put("ID_NEGERI", rs.getString("ID_NEGERI"));
				}
				if (rs.getString("NAMA_NEGERI") != null && rs.getString("NAMA_NEGERI") != "") {
					h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI"));
				}
				if (rs.getString("NAMA_PEJABAT") != null && rs.getString("NAMA_PEJABAT") != "") {
					h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT"));
				}
				if (rs.getString("NAMA_DAERAH") != null && rs.getString("NAMA_DAERAH") != "") {
					h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
				}
				if (rs.getString("JUMLAH_MOHON") != null && rs.getString("JUMLAH_MOHON") != "") {
					h.put("JUMLAH_MOHON", rs.getInt("JUMLAH_MOHON"));
				}
				if (rs.getString("JUMLAH_SELESAI") != null && rs.getString("JUMLAH_SELESAI") != "") {
					h.put("JUMLAH_SELESAI", rs.getInt("JUMLAH_SELESAI"));
				}
				if (rs.getString("KPI") != null && rs.getString("KPI") != "") {
					h.put("KPI", rs.getString("KPI"));
				}
				SenaraiSerahanAllState.addElement(h);
				}
	   
	    	}
	    
	    finally {
		      if (db != null) db.close();
	    }
		
	    
		}
	
	public void setDataSenaraiPrestasiKPIsemuaState(String mula_bulan, String mula_tahun, String section) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPrestasiKPI = "";
		List senaraiFail = null;
		
		//System.out.println("Dalam SaveData");
		try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sqlJanaLaporanPrestasiKPI = "SELECT LAYER, NAMA_NEGERI, JUMLAH_MOHON, JUMLAH_SELESAI, KPI FROM "; 
	    	sqlJanaLaporanPrestasiKPI += "(";
	    	sqlJanaLaporanPrestasiKPI += "SELECT 1 AS LAYER, N.NAMA_NEGERI, "; 
	    	sqlJanaLaporanPrestasiKPI += "COUNT(DISTINCT P.ID_PERMOHONAN) AS JUMLAH_MOHON, ";
	    	sqlJanaLaporanPrestasiKPI += "COUNT  ";
	    	sqlJanaLaporanPrestasiKPI += "(DISTINCT CASE  ";
	    	sqlJanaLaporanPrestasiKPI += "    WHEN   ";
	    	sqlJanaLaporanPrestasiKPI += " stf.ID_SUBURUSANSTATUS IN (  ";
	    	sqlJanaLaporanPrestasiKPI += " SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS IN(21,47,70,169,152,70,50 )  ";
	    	sqlJanaLaporanPrestasiKPI += "       )  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND stf.aktif = '1'  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND (stf.tarikh_masuk - p.tarikh_mohon) < 121  ";
	    	sqlJanaLaporanPrestasiKPI += "     THEN p.id_permohonan  ";
	    	sqlJanaLaporanPrestasiKPI += " END  ";
	    	sqlJanaLaporanPrestasiKPI += " ) AS jumlah_selesai  ";
	    	sqlJanaLaporanPrestasiKPI += ",TO_CHAR(";
	    	sqlJanaLaporanPrestasiKPI += "(COUNT(DISTINCT CASE WHEN P.ID_STATUS = 21 AND STF.ID_SUBURUSANSTATUS = 358 AND (STF.TARIKH_MASUK - P.TARIKH_MOHON) < 121 THEN P.ID_PERMOHONAN END) / COUNT(DISTINCT P.ID_PERMOHONAN)  ) * 100"; 
	    	sqlJanaLaporanPrestasiKPI += ",'990.00') AS KPI  ";
	    	sqlJanaLaporanPrestasiKPI += "FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLRUJSUBURUSANSTATUSFAIL STF, ";
	    	sqlJanaLaporanPrestasiKPI += "TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU, TBLRUJNEGERI N, TBLRUJDAERAH D ";
	    	sqlJanaLaporanPrestasiKPI += "WHERE F.ID_FAIL = P.ID_FAIL AND STF.ID_FAIL = F.ID_FAIL AND STF.AKTIF = 1 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = PU.ID_DAERAHURUS ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_SEKSYEN = 2 ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_JENISPEJABAT = 22 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.SEKSYEN = " + section;
	    	sqlJanaLaporanPrestasiKPI += " AND P.TARIKH_MOHON IS NOT NULL ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_NEGERI = N.ID_NEGERI ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = D.ID_DAERAH ";
	    	
	    	sqlJanaLaporanPrestasiKPI += "AND TO_CHAR(P.TARIKH_MOHON,'MM/YYYY') = TO_CHAR(add_months(TO_DATE('"+mula_bulan+"/"+mula_tahun+"','MM/YYYY'), -4),'MM/YYYY') ";
	    	sqlJanaLaporanPrestasiKPI += "GROUP BY PEJ.ID_NEGERI, N.NAMA_NEGERI ";	   
	    	//sampai sini	    	
	    	sqlJanaLaporanPrestasiKPI += "UNION ALL ";
	    	sqlJanaLaporanPrestasiKPI += "SELECT 2 AS LAYER, 'JUMLAH' AS nama_negeri, "; 
	    	sqlJanaLaporanPrestasiKPI += "COUNT(DISTINCT P.ID_PERMOHONAN) AS JUMLAH_MOHON,  ";
	    	sqlJanaLaporanPrestasiKPI += "COUNT  ";
	    	sqlJanaLaporanPrestasiKPI += "(DISTINCT CASE  ";
	    	sqlJanaLaporanPrestasiKPI += "    WHEN   ";
	    	sqlJanaLaporanPrestasiKPI += " stf.ID_SUBURUSANSTATUS IN (  ";
	    	sqlJanaLaporanPrestasiKPI += " SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS IN(21,47,70,169,152,70,50 )  ";
	    	sqlJanaLaporanPrestasiKPI += "       )  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND stf.aktif = '1'  ";
	    	sqlJanaLaporanPrestasiKPI += "   AND (stf.tarikh_masuk - p.tarikh_mohon) < 121  ";
	    	sqlJanaLaporanPrestasiKPI += "     THEN p.id_permohonan  ";
	    	sqlJanaLaporanPrestasiKPI += " END  ";
	    	sqlJanaLaporanPrestasiKPI += " ) AS jumlah_selesai  ";
	    	sqlJanaLaporanPrestasiKPI += ",TO_CHAR(";
	    	sqlJanaLaporanPrestasiKPI += "(COUNT(DISTINCT CASE WHEN P.ID_STATUS = 21 AND STF.ID_SUBURUSANSTATUS = 358 AND (STF.TARIKH_MASUK - P.TARIKH_MOHON) < 121 THEN P.ID_PERMOHONAN END) / COUNT(DISTINCT P.ID_PERMOHONAN)  ) * 100"; 
	    	sqlJanaLaporanPrestasiKPI += ",'990.00') AS KPI  ";
	    	sqlJanaLaporanPrestasiKPI += "FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLRUJSUBURUSANSTATUSFAIL STF, ";
	    	sqlJanaLaporanPrestasiKPI += "TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU, TBLRUJNEGERI N, TBLRUJDAERAH D ";
	    	sqlJanaLaporanPrestasiKPI += "WHERE F.ID_FAIL = P.ID_FAIL AND STF.ID_FAIL = F.ID_FAIL AND STF.AKTIF = 1 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = PU.ID_DAERAHURUS ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_SEKSYEN = 2 ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_JENISPEJABAT = 22 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.SEKSYEN = " + section;
	    	sqlJanaLaporanPrestasiKPI += " AND P.TARIKH_MOHON IS NOT NULL ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_NEGERI = N.ID_NEGERI ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = D.ID_DAERAH ";
	    	
	    	sqlJanaLaporanPrestasiKPI += "AND TO_CHAR(P.TARIKH_MOHON,'MM/YYYY') = TO_CHAR(add_months(TO_DATE('"+mula_bulan+"/"+mula_tahun+"','MM/YYYY'), -4),'MM/YYYY') ";
	    	sqlJanaLaporanPrestasiKPI += ") ";
	    	sqlJanaLaporanPrestasiKPI += "ORDER BY LAYER, NAMA_NEGERI ";
	    	System.out.println("sqlJanaLaporanPrestasiKPIsemuaState = " + sqlJanaLaporanPrestasiKPI);
	    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPrestasiKPI);
	    	Hashtable h;
	    	//con.commit();
	    	while (rs.next()) {
				h = new Hashtable();

				if (rs.getString("LAYER") != null && rs.getString("LAYER") != "") {
					h.put("LAYER", rs.getString("LAYER"));
				}
				//if (rs.getString("ID_NEGERI") != null && rs.getString("ID_NEGERI") != "") {
				//	h.put("ID_NEGERI", rs.getString("ID_NEGERI"));
				//}
				if (rs.getString("NAMA_NEGERI") != null && rs.getString("NAMA_NEGERI") != "") {
					h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI"));
				}
				//if (rs.getString("NAMA_PEJABAT") != null && rs.getString("NAMA_PEJABAT") != "") {
				//	h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT"));
				//}
				//if (rs.getString("NAMA_DAERAH") != null && rs.getString("NAMA_DAERAH") != "") {
				//	h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
				//}
				if (rs.getString("JUMLAH_MOHON") != null && rs.getString("JUMLAH_MOHON") != "") {
					h.put("JUMLAH_MOHON", rs.getInt("JUMLAH_MOHON"));
				}
				if (rs.getString("JUMLAH_SELESAI") != null && rs.getString("JUMLAH_SELESAI") != "") {
					h.put("JUMLAH_SELESAI", rs.getInt("JUMLAH_SELESAI"));
				}
				if (rs.getString("KPI") != null && rs.getString("KPI") != "") {
					h.put("KPI", rs.getString("KPI"));
				}
				SenaraiSerahan.addElement(h);
				}
	   
	    	}
	    
	    finally {
		      if (db != null) db.close();
	    }
		
	    
		}
	
	public void setDataSenaraiPrestasiKPIsemuaState135(String mula_bulan, String mula_tahun, String section) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanPrestasiKPI = "";
		List senaraiFail = null;
		
		//System.out.println("Dalam SaveData");
		try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	//con.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	sqlJanaLaporanPrestasiKPI = "SELECT LAYER, NAMA_NEGERI, JUMLAH_MOHON, JUMLAH_SELESAI, KPI FROM "; 
	    	sqlJanaLaporanPrestasiKPI += "(";
	    	sqlJanaLaporanPrestasiKPI += "SELECT 1 AS LAYER, N.NAMA_NEGERI, "; 
	    	sqlJanaLaporanPrestasiKPI += "COUNT(DISTINCT P.ID_PERMOHONAN) AS JUMLAH_MOHON, ";
	    	sqlJanaLaporanPrestasiKPI += "COUNT(DISTINCT CASE WHEN P.ID_STATUS = 21 AND STF.ID_SUBURUSANSTATUS = 358 AND (STF.TARIKH_MASUK - P.TARIKH_MOHON) <= 135 THEN P.ID_PERMOHONAN END) AS JUMLAH_SELESAI ";
	    	sqlJanaLaporanPrestasiKPI += ",TO_CHAR(";
	    	sqlJanaLaporanPrestasiKPI += "(COUNT(DISTINCT CASE WHEN P.ID_STATUS = 21 AND STF.ID_SUBURUSANSTATUS = 358 AND (STF.TARIKH_MASUK - P.TARIKH_MOHON) <= 135 THEN P.ID_PERMOHONAN END) / COUNT(DISTINCT P.ID_PERMOHONAN)  ) * 100"; 
	    	sqlJanaLaporanPrestasiKPI += ",'990.00') AS KPI  ";
	    	sqlJanaLaporanPrestasiKPI += "FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLRUJSUBURUSANSTATUSFAIL STF, ";
	    	sqlJanaLaporanPrestasiKPI += "TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU, TBLRUJNEGERI N, TBLRUJDAERAH D ";
	    	sqlJanaLaporanPrestasiKPI += "WHERE F.ID_FAIL = P.ID_FAIL AND STF.ID_FAIL = F.ID_FAIL AND STF.AKTIF = 1 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = PU.ID_DAERAHURUS ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_SEKSYEN = 2 ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_JENISPEJABAT = 22 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.SEKSYEN = " + section;
	    	sqlJanaLaporanPrestasiKPI += " AND P.TARIKH_MOHON IS NOT NULL ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_NEGERI = N.ID_NEGERI ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = D.ID_DAERAH ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_STATUS NOT IN (18,41,999,171,152,70) ";
	    	sqlJanaLaporanPrestasiKPI += "AND TO_CHAR(P.TARIKH_MOHON,'MM/YYYY') = TO_CHAR(add_months(TO_DATE('"+mula_bulan+"/"+mula_tahun+"','MM/YYYY'), -5),'MM/YYYY') ";
	    	sqlJanaLaporanPrestasiKPI += "GROUP BY PEJ.ID_NEGERI, N.NAMA_NEGERI ";	   
	    	//sampai sini	    	
	    	sqlJanaLaporanPrestasiKPI += "UNION ALL ";
	    	sqlJanaLaporanPrestasiKPI += "SELECT 2 AS LAYER, 'JUMLAH' AS nama_negeri, "; 
	    	sqlJanaLaporanPrestasiKPI += "COUNT(DISTINCT P.ID_PERMOHONAN) AS JUMLAH_MOHON,  ";
	    	sqlJanaLaporanPrestasiKPI += "COUNT(DISTINCT CASE WHEN P.ID_STATUS = 21 AND STF.ID_SUBURUSANSTATUS = 358 AND (STF.TARIKH_MASUK - P.TARIKH_MOHON) <= 135 THEN P.ID_PERMOHONAN END) AS JUMLAH_SELESAI ";
	    	sqlJanaLaporanPrestasiKPI += ",TO_CHAR(";
	    	sqlJanaLaporanPrestasiKPI += "(COUNT(DISTINCT CASE WHEN P.ID_STATUS = 21 AND STF.ID_SUBURUSANSTATUS = 358 AND (STF.TARIKH_MASUK - P.TARIKH_MOHON) <= 135 THEN P.ID_PERMOHONAN END) / COUNT(DISTINCT P.ID_PERMOHONAN)  ) * 100"; 
	    	sqlJanaLaporanPrestasiKPI += ",'990.00') AS KPI  ";
	    	sqlJanaLaporanPrestasiKPI += "FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLRUJSUBURUSANSTATUSFAIL STF, ";
	    	sqlJanaLaporanPrestasiKPI += "TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU, TBLRUJNEGERI N, TBLRUJDAERAH D ";
	    	sqlJanaLaporanPrestasiKPI += "WHERE F.ID_FAIL = P.ID_FAIL AND STF.ID_FAIL = F.ID_FAIL AND STF.AKTIF = 1 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = PU.ID_DAERAHURUS ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_SEKSYEN = 2 ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_JENISPEJABAT = 22 ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.SEKSYEN = " + section;
	    	sqlJanaLaporanPrestasiKPI += " AND P.TARIKH_MOHON IS NOT NULL ";
	    	sqlJanaLaporanPrestasiKPI += "AND PEJ.ID_NEGERI = N.ID_NEGERI ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_DAERAHMHN = D.ID_DAERAH ";
	    	sqlJanaLaporanPrestasiKPI += "AND P.ID_STATUS NOT IN (18,41,999,171,152,70) ";
	    	sqlJanaLaporanPrestasiKPI += "AND TO_CHAR(P.TARIKH_MOHON,'MM/YYYY') = TO_CHAR(add_months(TO_DATE('"+mula_bulan+"/"+mula_tahun+"','MM/YYYY'), -5),'MM/YYYY') ";
	    	sqlJanaLaporanPrestasiKPI += ") ";
	    	sqlJanaLaporanPrestasiKPI += "ORDER BY LAYER, NAMA_NEGERI ";
	    	System.out.println("sqlJanaLaporanPrestasiKPIsemuaState = " + sqlJanaLaporanPrestasiKPI);
	    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanPrestasiKPI);
	    	Hashtable h;
	    	//con.commit();
	    	while (rs.next()) {
				h = new Hashtable();

				if (rs.getString("LAYER") != null && rs.getString("LAYER") != "") {
					h.put("LAYER", rs.getString("LAYER"));
				}
				//if (rs.getString("ID_NEGERI") != null && rs.getString("ID_NEGERI") != "") {
				//	h.put("ID_NEGERI", rs.getString("ID_NEGERI"));
				//}
				if (rs.getString("NAMA_NEGERI") != null && rs.getString("NAMA_NEGERI") != "") {
					h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI"));
				}
				//if (rs.getString("NAMA_PEJABAT") != null && rs.getString("NAMA_PEJABAT") != "") {
				//	h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT"));
				//}
				//if (rs.getString("NAMA_DAERAH") != null && rs.getString("NAMA_DAERAH") != "") {
				//	h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
				//}
				if (rs.getString("JUMLAH_MOHON") != null && rs.getString("JUMLAH_MOHON") != "") {
					h.put("JUMLAH_MOHON", rs.getInt("JUMLAH_MOHON"));
				}
				if (rs.getString("JUMLAH_SELESAI") != null && rs.getString("JUMLAH_SELESAI") != "") {
					h.put("JUMLAH_SELESAI", rs.getInt("JUMLAH_SELESAI"));
				}
				if (rs.getString("KPI") != null && rs.getString("KPI") != "") {
					h.put("KPI", rs.getString("KPI"));
				}
				SenaraiSerahan.addElement(h);
				}
	   
	    	}
	    
	    finally {
		      if (db != null) db.close();
	    }
		
	    
		}
	
	
	public static void setDataSenaraiSerahanPBN(String mula_hari, String mula_bulan, String mula_tahun, String akhir_hari, String akhir_bulan, String akhir_tahun) throws Exception {
		//SenaraiSerahan = null;
		SenaraiSerahan.clear();
		Db db = null;
		String sqlJanaLaporanBaitulmalPBN = "";
		List senaraiFail = null;
		String tarikh_mula = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
		String tarikh_akhir = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sqlJanaLaporanBaitulmalPBN = "select distinct a.no_fail as noFail, f.nama_simati as namaSimati, e.nama_pemohon as namaPemohon, TO_CHAR(b.tarikh_mohon,'dd/mm/yyyy') as tarikhMohon, d.nama_ob AS NAMA_BAITULMAL ";
		    	sqlJanaLaporanBaitulmalPBN += "from tblpfdfail a , tblppkpermohonan b,";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkpermohonansimati c, tblppkob d, tblppkpemohon e, tblppksimati f, tblppkrujtarafkptg g, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkkeputusanpermohonan h, ";
		    	sqlJanaLaporanBaitulmalPBN += " tblppkperbicaraan i, tblppkperintah j, tblppkperintahhtaobmst k, tblppkperintahhtaobdtl l ";
		    	sqlJanaLaporanBaitulmalPBN += " where b.tarikh_mohon BETWEEN TO_DATE('"+tarikh_mula+"', 'DD/MM/YYYY') AND TO_DATE('"+tarikh_akhir+"', 'DD/MM/YYYY') ";
		    	sqlJanaLaporanBaitulmalPBN += " and a.id_fail=b.id_fail ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=c.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_permohonansimati=d.id_permohonansimati";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_pemohon=e.id_pemohon";
		    	sqlJanaLaporanBaitulmalPBN += " and c.id_simati=f.id_simati";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=g.id_tarafkptg";
		    	sqlJanaLaporanBaitulmalPBN += " and d.id_tarafkptg=17";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_status=21 ";
		    	sqlJanaLaporanBaitulmalPBN += " and b.id_permohonan=h.id_permohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and h.ID_KEPUTUSANPERMOHONAN=i.id_keputusanpermohonan";
		    	sqlJanaLaporanBaitulmalPBN += " and i.id_perbicaraan=j.id_perbicaraan";
		    	sqlJanaLaporanBaitulmalPBN += " and j.id_perintah = k.id_perintah";
		    	sqlJanaLaporanBaitulmalPBN += " and k.id_perintahhtaobmst =l.id_perintahhtaobmst";
		    	sqlJanaLaporanBaitulmalPBN += " and l.ba > 0  order by a.no_fail";
		    	System.out.println("sqlJanaLaporanBaitulmalPBN4 = " + sqlJanaLaporanBaitulmalPBN);
		    	ResultSet rs = stmt.executeQuery(sqlJanaLaporanBaitulmalPBN);
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
					if (rs.getString("NAMA_BAITULMAL") != null && rs.getString("NAMA_BAITULMAL") != "") {
						h.put("NAMA_BAITULMAL", rs.getString("NAMA_BAITULMAL"));
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

}// close class

	

	


