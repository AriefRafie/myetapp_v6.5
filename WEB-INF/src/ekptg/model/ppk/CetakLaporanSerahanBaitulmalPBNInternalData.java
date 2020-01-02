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
public class CetakLaporanSerahanBaitulmalPBNInternalData {

	static Logger myLogger = Logger.getLogger(CetakLaporanSerahanBaitulmalPBNInternalData.class);

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

	

}// close class

