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

import ekptg.report.Utils;

/*import lebah.pm.entity.ActivityEvent;
 import lebah.pm.entity.UserActivityEvent;
 import lebah.portal.action.Command;
 import lebah.template.DbPersistence;*/

public class CetakLaporanPrestasi {

	static Logger myLogger = Logger.getLogger(ekptg.model.ppk.CetakLaporanPrestasi.class);
	private static SimpleDateFormat sdf_ = new SimpleDateFormat("dd/MM/yyyy");

	// seksyen 8 (ada campur utk seksyen 17)
	private static Vector list = new Vector();
	private static Vector listA = new Vector();
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
	private static Vector SenaraiPrestasi = new Vector();
	private static Vector SenaraiNamaUnit = new Vector();
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

	public static Vector getNamaUnit() {
		return SenaraiNamaUnit;
	}
	
	public static Vector getList() {
		return listA;
	}
	
	public static Vector getSenaraiPrestasi() {
		return SenaraiPrestasi;
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
	
		public static Vector getSenaraiLaporanSerahan2() {
		return SenaraiSerahan2;
	}

	// List permohonan Seksyen 8
	public static void setDataPrestasibyUnitTempoh(String BULAN_DARI, String BULAN_HINGGA, String TAHUN_DARI, String TAHUN_HINGGA, String ID_NEGERI, String ID_UNIT) throws Exception {
		//SenaraiSerahan = null;
		SenaraiPrestasi.clear();
		Db db = null;
		String sql = "";
		List senaraiFail = null;
		//String tarikh_mula = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
		//String tarikh_akhir = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sql = "SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,C.ID_PEJABATJKPTG, UPPER(C.NAMA_PEJABAT) AS NAMA_PEJABAT,UPPER(C.ALAMAT1) AS ALAMAT1, UPPER(F.NAMA_NEGERI) AS NAMA_NEGERI, ";
		    	sql += " UPPER(D.NAMA_DAERAH) AS NAMA_DAERAH, NVL(A1.JUMLAH_FAIL,'0') AS JUMLAH_FAIL, ";
		    	sql += " NVL(A2.PANGGIL_BICARA,'0') AS PANGGIL_BICARA, NVL(A3.PANGGIL_BICARA_S17,'0') AS PANGGIL_BICARA_S17, ";
		    	sql += " NVL(A4.PANGGIL_3X_XHADIR,'0') AS PANGGIL_3X_XHADIR, NVL(A5.SELESAI_BICARA,'0') AS SELESAI_BICARA, ";
		    	sql += " NVL(A6.SELESAI_BICARA_S17,'0') AS SELESAI_BICARA_S17, NVL(A7.SELESAI_BATAL,'0') AS SELESAI_BATAL, ";
		    	sql += " NVL(A8.SELESAI_BATAL_S17,'0') AS SELESAI_BATAL_S17, NVL(A9.BAKI_BULAN_LEPAS,'0') AS BAKI_BULAN_LEPAS, ";
		    	sql += " NVL(A10.BAKI_BULAN_LEPAS_S17,'0') AS BAKI_BULAN_LEPAS_S17, NVL(A11.KES_TERIMA_BULAN,'0') AS KES_TERIMA_BULAN, ";
		    	sql += " NVL(A12.KES_TERIMA_BULAN_S17,'0') AS KES_TERIMA_BULAN_S17 ";

		    	sql += " FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABATJKPTG C, TBLRUJDAERAH D, ";
		    	sql += " TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H,";

		    	sql += " (SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(*) AS JUMLAH_FAIL ";
		    	sql += " FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C, ";
		    	sql += " TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H ";
		    	sql += " WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += " AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += " AND A.ID_JENISPEJABAT <> 3  ";
		    	sql += " AND A.ID_SEKSYEN = 2 ";
		    	sql += " AND B.ID_JENISPEJABAT = 2 ";
		    	sql += " AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += " AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += " AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += " AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += " AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += " AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += " AND H.ID_SUBURUSAN=59 ";
		    	sql += " AND A.ID_NEGERIURUS = "+ID_NEGERI;
		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	sql += " AND TO_CHAR(G.TARIKH_MOHON,'mm/yyyy') >= '10/2010' ";
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A1, ";

		    	sql += " (SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(DISTINCT(G.ID_FAIL)) AS PANGGIL_BICARA ";
		    	sql += " FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += " TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H,  ";
		    	sql += " TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J, TBLPPKPERINTAH K ";
		    	sql += " WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += " AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += " AND A.ID_JENISPEJABAT <> 3  ";
		    	sql += " AND A.ID_SEKSYEN = 2 ";
		    	sql += " AND B.ID_JENISPEJABAT = 2 ";
		    	sql += " AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += " AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += " AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += " AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN "; 
		    	sql += " AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += " AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += " AND H.ID_SUBURUSAN=59 ";
		    	sql += " AND G.ID_PERMOHONAN  =  J.ID_PERMOHONAN ";
		    	sql += " AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN ";
		    	sql += " AND I.ID_PERBICARAAN = K.ID_PERBICARAAN ";
		    	
		    	sql += " AND A.ID_NEGERIURUS = "+ID_NEGERI;
		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	sql += " AND TO_CHAR(I.TARIKH_BICARA,'mm') between "+BULAN_DARI+" and "+BULAN_HINGGA; //asdad
		    	sql += " AND TO_CHAR(I.TARIKH_BICARA,'yyyy') between "+TAHUN_DARI+" and "+TAHUN_HINGGA;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A2, ";

		    	sql += " (SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(DISTINCT(G.ID_FAIL)) AS PANGGIL_BICARA_S17 ";
		    	sql += " FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += " TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H,  ";
		    	sql += " TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J, TBLPPKPERINTAH K ";
		    	sql += " WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += " AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += " AND A.ID_JENISPEJABAT <> 3 ";
		    	sql += " AND A.ID_SEKSYEN = 2 ";
		    	sql += " AND B.ID_JENISPEJABAT = 2 ";
		    	sql += " AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += " AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += " AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += " AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += " AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += " AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += " AND H.ID_SUBURUSAN=60 ";
		    	sql += " AND G.ID_PERMOHONAN  =  J.ID_PERMOHONAN ";
		    	sql += " AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN ";
		    	sql += " AND I.ID_PERBICARAAN = K.ID_PERBICARAAN ";
		    	
		    	sql += " AND A.id_negeriurus = "+ID_NEGERI;
		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	sql += " AND TO_CHAR(I.TARIKH_BICARA,'mm') between "+BULAN_DARI+" and "+BULAN_HINGGA;
		    	sql += " AND TO_CHAR(I.TARIKH_BICARA,'yyyy') between "+TAHUN_DARI+" and "+TAHUN_HINGGA;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A3, ";

		    	sql += " (SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(*) AS PANGGIL_3X_XHADIR ";
		    	sql += " FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += " TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H,  ";
		    	sql += " TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J, TBLPPKPERINTAH K ";
		    	sql += " WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += " AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += " AND A.ID_JENISPEJABAT <> 3 ";
		    	sql += " AND A.ID_SEKSYEN = 2 ";
		    	sql += " AND B.ID_JENISPEJABAT = 2 ";
		    	sql += " AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += " AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += " AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += " AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += " AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += " AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += " AND H.ID_SUBURUSAN=59 ";
		    	sql += " AND G.ID_PERMOHONAN  =  J.ID_PERMOHONAN ";
		    	sql += " AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN ";
		    	sql += " AND I.ID_PERBICARAAN = K.ID_PERBICARAAN ";
		    	sql += " AND K.FLAG_BATAL = '2' ";
		    	sql += " AND A.id_negeriurus = "+ID_NEGERI;
		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	sql += " AND TO_CHAR(G.TARIKH_MOHON,'mm') between "+BULAN_DARI+" and "+BULAN_HINGGA;
		    	sql += " AND TO_CHAR(G.TARIKH_MOHON,'yyyy') between "+TAHUN_DARI+" and "+TAHUN_HINGGA;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A4, ";

		    	sql += " (SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(*) AS SELESAI_BICARA ";
		    	sql += " FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += " TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H,  ";
		    	sql += " TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J, TBLPPKPERINTAH K ";
		    	sql += " WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += " AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += " AND A.ID_JENISPEJABAT <> 3   ";
		    	sql += " AND A.ID_SEKSYEN = 2 ";
		    	sql += " AND B.ID_JENISPEJABAT = 2   ";
		    	sql += " AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += " AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += " AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += " AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += " AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += " AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += " AND H.ID_SUBURUSAN=59 ";
		    	sql += " AND G.ID_PERMOHONAN  =  J.ID_PERMOHONAN ";
		    	sql += " AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN ";
		    	sql += " AND I.ID_PERBICARAAN = K.ID_PERBICARAAN ";
		    	sql += " AND K.FLAG_JENIS_KEPUTUSAN = '0' ";
		    	sql += " AND A.id_negeriurus = "+ID_NEGERI;
		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	sql += " AND TO_CHAR(K.TARIKH_PERINTAH,'mm') between "+BULAN_DARI+" and "+BULAN_HINGGA;
		    	sql += " AND TO_CHAR(K.TARIKH_PERINTAH,'yyyy') between "+TAHUN_DARI+" and "+TAHUN_HINGGA;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A5, ";

		    	sql += " (SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(*) AS SELESAI_BICARA_S17 ";
		    	sql += " FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += " TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H,  ";
		    	sql += " TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J, TBLPPKPERINTAH K ";
		    	sql += " WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += " AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += " AND A.ID_JENISPEJABAT <> 3   ";
		    	sql += " AND A.ID_SEKSYEN = 2 ";
		    	sql += " AND B.ID_JENISPEJABAT = 2   ";
		    	sql += " AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += " AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += " AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += " AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += " AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += " AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += " AND H.ID_SUBURUSAN=60 ";
		    	sql += " AND G.ID_PERMOHONAN  =  J.ID_PERMOHONAN ";
		    	sql += " AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN ";
		    	sql += " AND I.ID_PERBICARAAN = K.ID_PERBICARAAN ";
		    	sql += " AND K.FLAG_JENIS_KEPUTUSAN = '0' ";
		    	sql += " AND A.id_negeriurus = "+ID_NEGERI;
		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	sql += " AND TO_CHAR(K.TARIKH_PERINTAH,'mm') between "+BULAN_DARI+" and "+BULAN_HINGGA;
		    	sql += " AND TO_CHAR(K.TARIKH_PERINTAH,'yyyy') between "+TAHUN_DARI+" and "+TAHUN_HINGGA;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A6, ";

		    	sql += 	" (SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(*) AS SELESAI_BATAL ";
		    	sql += " FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += " TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H,  ";
		    	sql += " TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J, TBLPPKPERINTAH K ";
		    	sql += " WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += " AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += " AND A.ID_JENISPEJABAT <> 3   ";
		    	sql += " AND A.ID_SEKSYEN = 2 ";
		    	sql += " AND B.ID_JENISPEJABAT = 2   ";
		    	sql += " AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += " AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += " AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += " AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += " AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += " AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += " AND H.ID_SUBURUSAN=59 ";
		    	sql += " AND G.ID_PERMOHONAN  =  J.ID_PERMOHONAN ";
		    	sql += " AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN ";
		    	sql += " AND I.ID_PERBICARAAN = K.ID_PERBICARAAN ";
		    	sql += " AND K.FLAG_JENIS_KEPUTUSAN = '2' ";
		    	sql += " AND G.ID_STATUS = 47 ";
		    	sql += " AND A.id_negeriurus = "+ID_NEGERI;
		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	sql += " AND TO_CHAR(K.TARIKH_PERINTAH,'mm') between "+BULAN_DARI+" and "+BULAN_HINGGA;
		    	sql += " AND TO_CHAR(K.TARIKH_PERINTAH,'yyyy') between "+TAHUN_DARI+" and "+TAHUN_HINGGA;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A7, ";

		    	sql += " (SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(*) AS SELESAI_BATAL_S17 ";
		    	sql += " FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += " TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H,  ";
		    	sql += " TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J, TBLPPKPERINTAH K ";
		    	sql += " WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += " AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += " AND A.ID_JENISPEJABAT <> 3  ";
		    	sql += " AND A.ID_SEKSYEN = 2 ";
		    	sql += " AND B.ID_JENISPEJABAT = 2  ";
		    	sql += " AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += " AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += " AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += " AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += " AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += " AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += " AND H.ID_SUBURUSAN=60 ";
		    	sql += " AND G.ID_PERMOHONAN  =  J.ID_PERMOHONAN ";
		    	sql += " AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN ";
		    	sql += " AND I.ID_PERBICARAAN = K.ID_PERBICARAAN ";
		    	sql += " AND K.FLAG_JENIS_KEPUTUSAN = '2' ";
		    	sql += " AND G.ID_STATUS = 47 ";
		    	sql += 	" AND A.id_negeriurus = "+ID_NEGERI;
		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	sql += " AND TO_CHAR(K.TARIKH_PERINTAH,'mm') between "+BULAN_DARI+" and "+BULAN_HINGGA;
		    	sql += " AND TO_CHAR(K.TARIKH_PERINTAH,'yyyy') between "+TAHUN_DARI+" and "+TAHUN_HINGGA;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A8, ";

		    	sql += " (SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(*) AS BAKI_BULAN_LEPAS ";
		    	sql += " FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += " TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H ";
		    	sql += " WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += " AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += " AND A.ID_JENISPEJABAT <> 3 ";
		    	sql += " AND A.ID_SEKSYEN = 2 ";
		    	sql += " AND B.ID_JENISPEJABAT = 2   ";
		    	sql += " AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += " AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += " AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += " AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += " AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += 	" AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += " and g.id_status not in (18,41,999,171,152,70) ";
		    	sql += " AND H.ID_SUBURUSAN=59 ";
		    	sql += " AND A.id_negeriurus = "+ID_NEGERI;
		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	sql += " AND TO_CHAR(G.TARIKH_MOHON, 'mm/YYYY') < '"+BULAN_DARI+"/"+TAHUN_DARI+"'  ";
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A9, ";

		    	sql += " (SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS, COUNT(*) AS BAKI_BULAN_LEPAS_S17 ";
		    	sql += " FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C, ";
		    	sql += " TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H ";
		    	sql += " WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += " AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += " AND A.ID_JENISPEJABAT <> 3 ";
		    	sql += " AND A.ID_SEKSYEN = 2 ";
		    	sql += " AND B.ID_JENISPEJABAT = 2 ";
		    	sql += " AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += " AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += " AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += " AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += " AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += " and g.id_status not in (18,41,999,171,152,70) ";
		    	sql += " AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += " AND H.ID_SUBURUSAN=60 ";
		    	sql += " AND A.id_negeriurus = "+ID_NEGERI;
		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	sql += " AND TO_CHAR(G.TARIKH_MOHON, 'mm/YYYY') < '"+BULAN_DARI+"/"+TAHUN_DARI+"'  ";
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A10, ";

		    	sql += " (SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(*) AS KES_TERIMA_BULAN ";
		    	sql += " FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += " TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H ";
		    	sql += " WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += " AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += " AND A.ID_JENISPEJABAT <> 3   ";
		    	sql += " AND A.ID_SEKSYEN = 2 ";
		    	sql += " AND B.ID_JENISPEJABAT = 2  ";
		    	sql += " AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += " AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += " AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += " AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += " AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += " AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += " AND H.ID_SUBURUSAN=59 ";
		    	sql += " AND A.id_negeriurus = "+ID_NEGERI;
		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	sql += " AND TO_CHAR(G.TARIKH_MOHON,'mm') between "+BULAN_DARI+" and "+BULAN_HINGGA;
		    	sql += " AND TO_CHAR(G.TARIKH_MOHON,'yyyy') between "+TAHUN_DARI+" and "+TAHUN_HINGGA;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A11, ";

		    	sql += " (SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(*) AS KES_TERIMA_BULAN_S17 ";
		    	sql += " FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += " TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H ";
		    	sql += " WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += " AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += " AND A.ID_JENISPEJABAT <> 3   ";
		    	sql += " AND A.ID_SEKSYEN = 2 ";
		    	sql += " AND B.ID_JENISPEJABAT = 2   ";
		    	sql += " AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += " AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += " AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += " AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += " AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += " AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += " AND H.ID_SUBURUSAN=60 ";
		    	sql += " AND A.id_negeriurus = "+ID_NEGERI;
		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	sql += " AND TO_CHAR(G.TARIKH_MOHON,'mm') between "+BULAN_DARI+" and "+BULAN_HINGGA;
		    	sql += " AND TO_CHAR(G.TARIKH_MOHON,'yyyy') between "+TAHUN_DARI+" and "+TAHUN_HINGGA;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A12 ";

		    	sql += " WHERE A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += " AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += " AND A.ID_NEGERIURUS  = F.ID_NEGERI ";
		    	sql += " AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += " AND H.ID_SUBURUSAN=59 ";
		    	sql += " AND A.id_negeriurus = "+ID_NEGERI;
		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	sql += " AND A.ID_NEGERIURUS = A1.ID_NEGERIURUS(+) ";
		    	sql += " AND A.ID_DAERAHURUS = A1.ID_DAERAHURUS(+) ";
		    	sql += " AND A.ID_NEGERIURUS = A2.ID_NEGERIURUS(+) ";
		    	sql += " AND A.ID_DAERAHURUS = A2.ID_DAERAHURUS(+) ";
		    	sql += " AND A.ID_NEGERIURUS = A3.ID_NEGERIURUS(+) ";
		    	sql += " AND A.ID_DAERAHURUS = A3.ID_DAERAHURUS(+) ";
		    	sql += " AND A.ID_NEGERIURUS = A4.ID_NEGERIURUS(+) ";
		    	sql += " AND A.ID_DAERAHURUS = A4.ID_DAERAHURUS(+) ";
		    	sql += " AND A.ID_NEGERIURUS = A5.ID_NEGERIURUS(+) ";
		    	sql += " AND A.ID_DAERAHURUS = A5.ID_DAERAHURUS(+) ";
		    	sql += " AND A.ID_NEGERIURUS = A6.ID_NEGERIURUS(+) ";
		    	sql += " AND A.ID_DAERAHURUS = A6.ID_DAERAHURUS(+) ";
		    	sql += " AND A.ID_NEGERIURUS = A7.ID_NEGERIURUS(+) ";
		    	sql += " AND A.ID_DAERAHURUS = A7.ID_DAERAHURUS(+) ";
		    	sql += " AND A.ID_NEGERIURUS = A8.ID_NEGERIURUS(+) ";
		    	sql += " AND A.ID_DAERAHURUS = A8.ID_DAERAHURUS(+) ";
		    	sql += " AND A.ID_NEGERIURUS = A9.ID_NEGERIURUS(+) ";
		    	sql += " AND A.ID_DAERAHURUS = A9.ID_DAERAHURUS(+) ";
		    	sql += " AND A.ID_NEGERIURUS = A10.ID_NEGERIURUS(+) ";
		    	sql += " AND A.ID_DAERAHURUS = A10.ID_DAERAHURUS(+) ";
		    	sql += " AND A.ID_NEGERIURUS = A11.ID_NEGERIURUS(+) ";
		    	sql += " AND A.ID_DAERAHURUS = A11.ID_DAERAHURUS(+) ";
		    	sql += " AND A.ID_NEGERIURUS = A12.ID_NEGERIURUS(+) ";
		    	sql += " AND A.ID_DAERAHURUS = A12.ID_DAERAHURUS(+) ";
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS,C.ID_PEJABATJKPTG, C.NAMA_PEJABAT, C.ALAMAT1, F.NAMA_NEGERI, ";
		    	sql += " D.NAMA_DAERAH, A1.JUMLAH_FAIL, A2.PANGGIL_BICARA, A3.PANGGIL_BICARA_S17, A4.PANGGIL_3X_XHADIR, A5.SELESAI_BICARA, A6.SELESAI_BICARA_S17, A7.SELESAI_BATAL, "; 
		    	sql += " A8.SELESAI_BATAL_S17, A9.BAKI_BULAN_LEPAS, A10.BAKI_BULAN_LEPAS_S17, A11.KES_TERIMA_BULAN, A12.KES_TERIMA_BULAN_S17 ";
		    	sql += " ORDER BY D.NAMA_DAERAH ";
		    	
		    	
		    			
		    	
		    	System.out.println("sql = " + sql);
		    	ResultSet rs = stmt.executeQuery(sql);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();

					if (rs.getString("NAMA_DAERAH") != null && rs.getString("NAMA_DAERAH") != "") {
						h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
					}
					if (rs.getString("JUMLAH_FAIL") != null && rs.getString("JUMLAH_FAIL") != "") {
						h.put("JUMLAH_FAIL", rs.getInt("JUMLAH_FAIL"));
					}
					if (rs.getString("BAKI_BULAN_LEPAS") != null && rs.getString("BAKI_BULAN_LEPAS") != "") {
						h.put("BAKI_BULAN_LEPAS", rs.getInt("BAKI_BULAN_LEPAS"));
					}
					if (rs.getString("BAKI_BULAN_LEPAS_S17") != null && rs.getString("BAKI_BULAN_LEPAS_S17") != "") {
						h.put("BAKI_BULAN_LEPAS_S17", rs.getInt("BAKI_BULAN_LEPAS_S17"));
					}
					if (rs.getString("KES_TERIMA_BULAN") != null && rs.getString("KES_TERIMA_BULAN") != "") {
						h.put("KES_TERIMA_BULAN", rs.getInt("KES_TERIMA_BULAN"));
					}
					if (rs.getString("KES_TERIMA_BULAN_S17") != null && rs.getString("KES_TERIMA_BULAN_S17") != "") {
						h.put("KES_TERIMA_BULAN_S17", rs.getInt("KES_TERIMA_BULAN_S17"));
					}
					if (rs.getString("PANGGIL_BICARA") != null && rs.getString("PANGGIL_BICARA") != "") {
						h.put("PANGGIL_BICARA", rs.getInt("PANGGIL_BICARA"));
					}
					if (rs.getString("PANGGIL_BICARA_S17") != null && rs.getString("PANGGIL_BICARA_S17") != "") {
						h.put("PANGGIL_BICARA_S17", rs.getInt("PANGGIL_BICARA_S17"));
					}
					if (rs.getString("SELESAI_BICARA") != null && rs.getString("SELESAI_BICARA") != "") {
						h.put("SELESAI_BICARA", rs.getInt("SELESAI_BICARA"));
					}
					if (rs.getString("SELESAI_BICARA_S17") != null && rs.getString("SELESAI_BICARA_S17") != "") {
						h.put("SELESAI_BICARA_S17", rs.getInt("SELESAI_BICARA_S17"));
					}
					if (rs.getString("SELESAI_BATAL") != null && rs.getString("SELESAI_BATAL") != "") {
						h.put("SELESAI_BATAL", rs.getInt("SELESAI_BATAL"));
					}
					if (rs.getString("SELESAI_BATAL_S17") != null && rs.getString("SELESAI_BATAL_S17") != "") {
						h.put("SELESAI_BATAL_S17", rs.getInt("SELESAI_BATAL_S17"));
					}
					
					SenaraiPrestasi.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
			
		    
			}
	
	public static Vector setList(String userid, String mula_bulan, String mula_tahun, String ID_DAERAH) throws Exception {
		
		Db db = null;
		if (mula_bulan.equals("1"))
		{
			mula_bulan = "01";
		}
		if (mula_bulan.equals("2"))
		{
			mula_bulan = "02";
		}
		if (mula_bulan.equals("3"))
		{
			mula_bulan = "03";
		}
		if (mula_bulan.equals("4"))
		{
			mula_bulan = "04";
		}
		if (mula_bulan.equals("5"))
		{
			mula_bulan = "05";
		}
		if (mula_bulan.equals("6"))
		{
			mula_bulan = "06";
		}
		if (mula_bulan.equals("7"))
		{
			mula_bulan = "07";
		}
		if (mula_bulan.equals("8"))
		{
			mula_bulan = "08";
		}
		if (mula_bulan.equals("9"))
		{
			mula_bulan = "09";
		}
		 listA.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT DISTINCT A.ID_PERMOHONAN, F.NO_FAIL, A.TARIKH_MOHON, DD.NAMA_DAERAH, ";
			sql += "P.ID_SIMATI, ";
			sql += "P.NAMA_SIMATI, A.ID_DAERAHMHN, ";
			sql += "PM.NAMA_PEMOHON ";
			sql += "FROM  ";
			sql += "TBLPPKPERMOHONAN A, ";
			sql += "TBLPFDFAIL F, ";
			sql += "TBLPPKPERMOHONANSIMATI MS, ";
			sql += "TBLPPKSIMATI P, ";
			sql += "TBLRUJDAERAH D, ";
			sql += "TBLRUJSUBURUSANSTATUS ST, ";
			sql += "TBLPPKPEMOHON PM, ";
			sql += "TBLRUJDAERAH DD ";
			sql += "WHERE  ";
			sql += "A.ID_DAERAHMHN = DD.ID_DAERAH ";
			sql += "AND A.ID_FAIL = F.ID_FAIL ";
			sql += " AND P.ID_SIMATI = MS.ID_SIMATI ";
			sql += " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN ";
			sql += " AND A.ID_DAERAHMHN = D.ID_DAERAH(+) ";
			//sql += " AND A.ID_STATUS NOT IN (999, 70, 152) ";
			sql += " AND F.ID_SUBURUSAN IN (59, 60) ";
			sql += " AND F.ID_SEKSYEN = 2 ";
			sql += " AND F.NO_FAIL IS NOT NULL ";
			sql += " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) ";
			sql += " AND TO_CHAR (A.TARIKH_MOHON, 'MM/YYYY') = '"+mula_bulan+"/"+mula_tahun+"' ";
			sql += " AND A.SEKSYEN = 8 ";
			sql += "AND DD.ID_DAERAH = "+ID_DAERAH;
			sql += " AND F.NO_FAIL IS NOT NULL ";
			sql += "ORDER BY F.NO_FAIL ASC ";


			System.out.print("SQL 000" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h = null;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("no_Fail",
						rs.getString("no_Fail") == null ? "" : rs
								.getString("no_Fail"));
				h.put("tarikhmohon", rs.getDate("tarikh_Mohon") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("id_simati", rs.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_Simati") == null ? ""
						: rs.getString("nama_Simati"));
				h.put("namapemohon", rs.getString("nama_Pemohon") == null ? ""
						: rs.getString("nama_Pemohon"));
				h.put("daerahmohon", rs.getString("id_Daerahmhn") == null ? ""
						: rs.getString("id_Daerahmhn"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH"));

				listA.addElement(h);
				bil++;
			}
			return listA;
		} finally {
			if (db != null)
				db.close();
		}
		}
	
	public Vector<Hashtable<String,String>> getSenaraiMengikutDaerah(String laporan,String idSubUrusan
		,int mulaBulan,int tamatBulan, String mulaTahun, String tamatTahun
		,String idDaerah) throws Exception {	
		Db db = null;
		listA.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT DISTINCT A.ID_PERMOHONAN, F.NO_FAIL, A.TARIKH_MOHON, ";
			sql += "P.ID_SIMATI, ";
			sql += "P.NAMA_SIMATI, A.ID_DAERAHMHN, ";
			sql += "PM.NAMA_PEMOHON ";
			sql += "FROM  ";
			sql += "TBLPPKPERMOHONAN A, ";
			sql += "TBLPFDFAIL F, ";
			sql += "TBLPPKPERMOHONANSIMATI MS, ";
			sql += "TBLPPKSIMATI P, ";
			sql += "TBLRUJDAERAH D, ";
			sql += "TBLRUJSUBURUSANSTATUS ST, ";
			sql += "TBLPPKPEMOHON PM, ";
			sql += "TBLRUJDAERAH DD ";
			sql += "WHERE  ";
			sql += "A.ID_DAERAHMHN = DD.ID_DAERAH ";
			sql += "AND A.ID_FAIL = F.ID_FAIL ";
			sql += " AND P.ID_SIMATI = MS.ID_SIMATI ";
			sql += " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN ";
			sql += " AND A.ID_DAERAHMHN = D.ID_DAERAH(+) ";
			sql += " AND A.ID_STATUS NOT IN (999, 70, 152) ";
			//sql += " AND F.ID_SUBURUSAN IN (59, 60) ";
			sql += " AND F.ID_SUBURUSAN = "+idSubUrusan;
			//sql += " AND F.ID_SEKSYEN = 2 ";
			sql += " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) ";
			if(laporan.equals("tahun"))
				sql += " AND TO_CHAR (A.TARIKH_MOHON, 'YYYY') = '"+mulaTahun+"' ";
			else if(laporan.equals("selang"))
				sql += " AND TO_CHAR (A.TARIKH_MOHON, 'MM/YYYY') BETWEEN '"+Utils.getBulanNum(mulaBulan)+"/"+mulaTahun+"' AND '"+Utils.getBulanNum(tamatBulan)+"/"+tamatTahun+"' ";
			//sql += " AND A.SEKSYEN = 8 ";
			
			sql += " AND DD.ID_DAERAH = "+idDaerah;
			sql += " AND F.NO_FAIL IS NOT NULL ";
			sql += "ORDER BY A.TARIKH_MOHON ASC ";

			//System.out.print("SQL 000" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h = null;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("no_Fail",rs.getString("no_Fail") == null ? "" : rs.getString("no_Fail"));
				h.put("tarikhmohon", rs.getDate("tarikh_Mohon") == null ? "": sdf_.format(rs.getDate("tarikh_Mohon")));
				h.put("id_simati", rs.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_Simati") == null ? "": rs.getString("nama_Simati"));
				h.put("namapemohon", rs.getString("nama_Pemohon") == null ? "": rs.getString("nama_Pemohon"));
				h.put("daerahmohon", rs.getString("id_Daerahmhn") == null ? "": rs.getString("id_Daerahmhn"));
				listA.addElement(h);
				bil++;
				
			}
			return listA;
			
		} finally {
			if (db != null)
				db.close();
		}
		
	}
	public Vector<Hashtable<String,String>> getSenaraiMengikutUnit(String laporan,String idSubUrusan
		,int mulaBulan,int tamatBulan, String mulaTahun, String tamatTahun
		,String idRujukan) throws Exception {	
		Db db = null;
		listA.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT DISTINCT A.ID_PERMOHONAN,A.TARIKH_MOHON,A.ID_DAERAHMHN ";
			sql += ",F.NO_FAIL ";
			sql += ",P.ID_SIMATI,P.NAMA_SIMATI ";
			sql += ",PM.NAMA_PEMOHON ";
			sql += "FROM  ";
			sql += "TBLPPKPERMOHONAN A, ";
			sql += "TBLPFDFAIL F, ";
			sql += "TBLPPKPERMOHONANSIMATI MS, ";
			sql += "TBLPPKSIMATI P, ";
			//sql += "TBLRUJDAERAH D, ";
			sql += "TBLRUJSUBURUSANSTATUS ST, ";
			sql += "TBLPPKPEMOHON PM ";
			//sql += "TBLRUJDAERAH DD ";
			sql += ",TBLRUJPEJABATURUSAN RPU ";
			sql += "WHERE ";
			//sql += "A.ID_DAERAHMHN = DD.ID_DAERAH AND ";
			sql += " A.ID_FAIL = F.ID_FAIL ";
			sql += " AND P.ID_SIMATI = MS.ID_SIMATI ";
			sql += " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN ";
			//sql += " AND A.ID_DAERAHMHN = D.ID_DAERAH(+) ";
			//sql += " AND A.ID_STATUS NOT IN (999, 70, 152) ";
			//sql += " AND F.ID_SEKSYEN = 2 ";
			//sql += " AND F.NO_FAIL IS NOT NULL ";
			sql += " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) ";
			sql += " AND A.ID_DAERAHMHN =  RPU.ID_DAERAHURUS ";
			sql += " AND RPU.ID_JENISPEJABAT IN (22) ";
			sql += " AND RPU.ID_PEJABATJKPTG ="+idRujukan;
			//sql += " AND F.ID_SUBURUSAN IN (59, 60) ";
			sql += " AND F.ID_SUBURUSAN = "+idSubUrusan;
			if(laporan.equals("tahun"))
				sql += " AND TO_CHAR (A.TARIKH_MOHON, 'YYYY') = '"+mulaTahun+"' ";
			else if(laporan.equals("bulan"))
				sql += " AND TO_CHAR (A.TARIKH_MOHON, 'MM/YYYY') = '"+Utils.getBulanNum(mulaBulan)+"/"+mulaTahun+"' ";
			else if(laporan.equals("selang"))
				sql += " AND TO_CHAR (A.TARIKH_MOHON, 'MM/YYYY') BETWEEN '"+Utils.getBulanNum(mulaBulan)+"/"+mulaTahun+"' AND '"+Utils.getBulanNum(tamatBulan)+"/"+tamatTahun+"' ";

			//sql += " AND A.SEKSYEN =  "+sek;
			sql += " AND F.NO_FAIL IS NOT NULL ";
			sql += "ORDER BY F.NO_FAIL ASC ";
			System.out.println("SQL UNIT=" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h = null;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("no_Fail",
						rs.getString("no_Fail") == null ? "" : rs
								.getString("no_Fail"));
				h.put("tarikhmohon", rs.getDate("tarikh_Mohon") == null ? ""
						: sdf_.format(rs.getDate("tarikh_Mohon")));
				h.put("id_simati", rs.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_Simati") == null ? ""
						: rs.getString("nama_Simati"));
				h.put("namapemohon", rs.getString("nama_Pemohon") == null ? ""
						: rs.getString("nama_Pemohon"));
				h.put("daerahmohon", rs.getString("id_Daerahmhn") == null ? ""
						: rs.getString("id_Daerahmhn"));

				listA.addElement(h);
				bil++;
			}
			return listA;
			
		} finally {
			if (db != null)
				db.close();
		}
		
	}
	public Vector<Hashtable<String,String>> getSenaraiMengikutNegeri(String laporan,String idSubUrusan
		,int mulaBulan,int tamatBulan, String mulaTahun,String tamatTahun
		,String idRujukan) throws Exception {	
		Db db = null;
		listA.clear();
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT DISTINCT A.ID_PERMOHONAN,A.TARIKH_MOHON,A.ID_DAERAHMHN ";
				sql += ",F.NO_FAIL ";
				sql += ",P.ID_SIMATI,P.NAMA_SIMATI ";
				sql += ",PM.NAMA_PEMOHON ";
				sql += "FROM  ";
				sql += "TBLPPKPERMOHONAN A, ";
				sql += "TBLPFDFAIL F, ";
				sql += "TBLPPKPERMOHONANSIMATI MS, ";
				sql += "TBLPPKSIMATI P, ";
				sql += "TBLRUJSUBURUSANSTATUS ST, ";
				sql += "TBLPPKPEMOHON PM ";
				sql += "WHERE ";
				sql += " A.ID_FAIL = F.ID_FAIL ";
				sql += " AND P.ID_SIMATI = MS.ID_SIMATI ";
				sql += " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN ";
				//sql += " AND A.ID_STATUS NOT IN (999, 70, 152) ";
				sql += " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) ";
				sql += " AND A.ID_NEGERIMHN ="+idRujukan;
				sql += " AND F.ID_SUBURUSAN = "+idSubUrusan;
				if(laporan.equals("tahun"))
					sql += " AND TO_CHAR (A.TARIKH_MOHON, 'YYYY') = '"+mulaTahun+"' ";
				else if(laporan.equals("selang"))
					sql += " AND TO_CHAR (A.TARIKH_MOHON, 'MM/YYYY') BETWEEN '"+Utils.getBulanNum(mulaBulan)+"/"+mulaTahun+"' AND '"+Utils.getBulanNum(tamatBulan)+"/"+tamatTahun+"' ";

				sql += " AND F.NO_FAIL IS NOT NULL ";
				sql += "ORDER BY F.NO_FAIL ASC ";
				myLogger.info("SQL NEGERI=" + sql.toUpperCase());
				ResultSet rs = stmt.executeQuery(sql);

				Hashtable h = null;
				int bil = 1;

				while (rs.next()) {
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_Permohonan", rs.getString("id_Permohonan"));
					h.put("no_Fail",
							rs.getString("no_Fail") == null ? "" : rs
									.getString("no_Fail"));
					h.put("tarikhmohon", rs.getDate("tarikh_Mohon") == null ? ""
							: sdf_.format(rs.getDate("tarikh_Mohon")));
					h.put("id_simati", rs.getString("id_Simati"));
					h.put("namasimati", rs.getString("nama_Simati") == null ? ""
							: rs.getString("nama_Simati"));
					h.put("namapemohon", rs.getString("nama_Pemohon") == null ? ""
							: rs.getString("nama_Pemohon"));
					h.put("daerahmohon", rs.getString("id_Daerahmhn") == null ? ""
							: rs.getString("id_Daerahmhn"));

					listA.addElement(h);
					bil++;
				}
				return listA;
				
			} finally {
				if (db != null)
					db.close();
			}
			
		}
	
	public static Vector setList2(String userid, String mula_bulan, String mula_tahun, String ID_DAERAH) throws Exception {
		
		Db db = null;
		if (mula_bulan.equals("1"))
		{
			mula_bulan = "01";
		}
		if (mula_bulan.equals("2"))
		{
			mula_bulan = "02";
		}
		if (mula_bulan.equals("3"))
		{
			mula_bulan = "03";
		}
		if (mula_bulan.equals("4"))
		{
			mula_bulan = "04";
		}
		if (mula_bulan.equals("5"))
		{
			mula_bulan = "05";
		}
		if (mula_bulan.equals("6"))
		{
			mula_bulan = "06";
		}
		if (mula_bulan.equals("7"))
		{
			mula_bulan = "07";
		}
		if (mula_bulan.equals("8"))
		{
			mula_bulan = "08";
		}
		if (mula_bulan.equals("9"))
		{
			mula_bulan = "09";
		}
		 listA.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT DISTINCT A.ID_PERMOHONAN, F.NO_FAIL, A.TARIKH_MOHON, DD.NAMA_DAERAH,";
			sql += "P.ID_SIMATI, ";
			sql += "P.NAMA_SIMATI, A.ID_DAERAHMHN, ";
			sql += "PM.NAMA_PEMOHON ";
			sql += "FROM  ";
			sql += "TBLPPKPERMOHONAN A, ";
			sql += "TBLPFDFAIL F, ";
			sql += "TBLPPKPERMOHONANSIMATI MS, ";
			sql += "TBLPPKSIMATI P, ";
			sql += "TBLRUJDAERAH D, ";
			sql += "TBLRUJSUBURUSANSTATUS ST, ";
			sql += "TBLPPKPEMOHON PM, ";
			sql += "TBLRUJDAERAH DD ";
			sql += "WHERE  ";
			sql += "A.ID_DAERAHMHN = DD.ID_DAERAH ";
			sql += "AND A.ID_FAIL = F.ID_FAIL ";
			sql += " AND P.ID_SIMATI = MS.ID_SIMATI ";
			sql += " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN ";
			sql += " AND A.ID_DAERAHMHN = D.ID_DAERAH(+) ";
			sql += " AND A.ID_STATUS NOT IN (999, 70, 152) ";
			sql += " AND F.ID_SUBURUSAN IN (59, 60) ";
			sql += " AND F.ID_SEKSYEN = 2 ";
			sql += " AND F.NO_FAIL IS NOT NULL ";
			sql += " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) ";
			sql += " AND TO_CHAR (A.TARIKH_MOHON, 'MM/YYYY') = '"+mula_bulan+"/"+mula_tahun+"' ";
			sql += " AND A.SEKSYEN = 17 ";
			sql += "AND DD.ID_DAERAH = "+ID_DAERAH;
			sql += " AND F.NO_FAIL IS NOT NULL ";
			sql += "ORDER BY A.TARIKH_MOHON ASC ";


			System.out.print("SQL 000" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h = null;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("no_Fail",
						rs.getString("no_Fail") == null ? "" : rs
								.getString("no_Fail"));
				h.put("tarikhmohon", rs.getDate("tarikh_Mohon") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("id_simati", rs.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_Simati") == null ? ""
						: rs.getString("nama_Simati"));
				h.put("namapemohon", rs.getString("nama_Pemohon") == null ? ""
						: rs.getString("nama_Pemohon"));
				h.put("daerahmohon", rs.getString("id_Daerahmhn") == null ? ""
						: rs.getString("id_Daerahmhn"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH"));

				listA.addElement(h);
				bil++;
			}
			return listA;
		} finally {
			if (db != null)
				db.close();
		}
		}
	 
	public static void setDataPrestasibyUnitBulan(String BULAN_DARI, String TAHUN_DARI, String ID_NEGERI, String ID_UNIT) throws Exception {
		//SenaraiSerahan = null;		  
		SenaraiPrestasi.clear();
		Db db = null;
		String sql = "";
		List senaraiFail = null;
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sql = "SELECT PJ.NAMA_PEJABAT, DD.ID_DAERAH, DD.NAMA_DAERAH, ";
		    	sql += "MAIN_STAT.TOTAL_FAIL_S8 AS TOTAL_FAIL, ";
		    	sql +=		"MAIN_STAT.BAKI_SEBELUM_BULAN_S8 AS BAKI_BULAN_LEPAS_S8, ";
		    	sql +=		"MAIN_STAT.BAKI_SEBELUM_BULAN_S17 AS BAKI_BULAN_LEPAS_S17, ";
		    	sql +=		"MAIN_STAT.BY_BULAN_S8 AS TERIMA_BULAN_SEMASA_S8, ";
		    	sql +=		"MAIN_STAT.BY_BULAN_S17 AS TERIMA_BULAN_SEMASA_S17, ";
		    	sql +=		"(MAIN_STAT.BAKI_SEBELUM_BULAN_S8 + MAIN_STAT.BY_BULAN_S8) AS JUMLAH_DALAM_TANGAN_S8, ";
		    	sql +=		"(MAIN_STAT.BAKI_SEBELUM_BULAN_S17 + MAIN_STAT.BY_BULAN_S17) AS JUMLAH_DALAM_TANGAN_S17, ";
		    	sql +=		"MAIN_STAT.BICARA_BULAN_S8 AS PANGGIL_BICARA_DALAM_BULAN_S8, ";
		    	sql +=		"MAIN_STAT.BICARA_BULAN_S17 AS PANGGIL_BICARA_DALAM_BULAN_S17, ";
		    	sql +=		"MAIN_STAT.SELESAI_BULAN_S8 AS SELESAI_BULAN_SEMASA_S8, ";
		    	sql +=	"MAIN_STAT.SELESAI_BULAN_S17 AS SELESAI_BULAN_SEMASA_S17, ";
		    	sql +=	"MAIN_STAT.BATAL_BULAN_S8 AS BATAL_BULAN_SEMASA_S8, ";
		    	sql +=	"MAIN_STAT.BATAL_BULAN_S17 AS BATAL_BULAN_SEMASA_S17, ";
		    	sql +=	"((MAIN_STAT.BAKI_SEBELUM_BULAN_S8 + MAIN_STAT.BY_BULAN_S8)-(MAIN_STAT.SELESAI_BULAN_S8 + MAIN_STAT.BATAL_BULAN_S8)) AS BAKI_AKHIR_S8, ";
		    	sql +=	"((MAIN_STAT.BAKI_SEBELUM_BULAN_S17 + MAIN_STAT.BY_BULAN_S17)-(MAIN_STAT.SELESAI_BULAN_S17 + MAIN_STAT.BATAL_BULAN_S17)) AS BAKI_AKHIR_S17 ";
		    	sql +="FROM ";
		    	sql +="(SELECT   G.ID_DAERAHMHN, ";
		    	sql +="COUNT ";
		    	sql +="(DISTINCT CASE ";
		    	sql +=		"WHEN H.ID_SUBURUSAN IN (59) ";
		    	sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="THEN G.ID_PERMOHONAN ";
		    	sql +="END ";
		    	sql +=") AS TOTAL_FAIL_S8, ";
		    	sql +="COUNT ";
		    	sql +="(DISTINCT CASE ";
		    	sql +="WHEN H.ID_SUBURUSAN IN (59) ";
		    	sql +="AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="AND K.FLAG_JENIS_KEPUTUSAN IN ('3') THEN G.ID_PERMOHONAN ";
		    	sql +="WHEN H.ID_SUBURUSAN IN (59) ";
		    	sql +="AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="AND K.ID_PERINTAH IS NULL ";
		    	sql +="THEN G.ID_PERMOHONAN ";
		    	sql +="END ";
		    	sql +=") AS BAKI_SEBELUM_BULAN_S8, ";
		    	sql +="COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		    	sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN IN ('3') THEN G.ID_PERMOHONAN ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		    	sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	AND K.ID_PERINTAH IS NULL ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +=" END ";
		    	sql +="	) AS BAKI_SEBELUM_BULAN_S17, ";
		    	sql +="	COUNT ";
		    	sql +=" (DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
		    	sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BY_BULAN_S8, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		    	sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BY_BULAN_S17, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
		    	sql +="	AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BICARA_BULAN_S8, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		    	sql +="	AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BICARA_BULAN_S17, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
		    	sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '0' ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS SELESAI_BULAN_S8, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		    	sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '0' ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS SELESAI_BULAN_S17, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
		    	sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '2' ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BATAL_BULAN_S8, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		    	sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '2' ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BATAL_BULAN_S17 ";
		    	sql +="	FROM TBLPPKPERMOHONAN G, ";
		    	sql +="	TBLPFDFAIL H, ";
		    	sql +="	TBLPPKPERBICARAAN I, ";
		    	sql +="	TBLPPKKEPUTUSANPERMOHONAN J, ";
		    	sql +="	TBLPPKPERINTAH K, tblrujsuburusanstatusfail stf ";
		    	sql +="	WHERE G.ID_FAIL = H.ID_FAIL ";
		    	sql +="	AND STF.ID_FAIL = H.ID_FAIL ";
		    	sql +="	AND stf.aktif = 1 ";
		    	sql +="	AND G.ID_FAIL = H.ID_FAIL ";
		    	sql +="	AND H.ID_SUBURUSAN IN (59, 60) ";
		    	sql +="	AND H.ID_SEKSYEN = 2 ";
		    	sql +="	AND G.ID_PERMOHONAN = J.ID_PERMOHONAN(+) ";
		    	sql +="	AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN(+) ";
		    	sql +="	AND I.ID_PERBICARAAN = K.ID_PERBICARAAN(+) ";
		    	sql +="	AND H.NO_FAIL IS NOT NULL ";
		    	sql +="	AND G.ID_STATUS NOT IN (999) ";
		    	sql +="	GROUP BY G.ID_DAERAHMHN) MAIN_STAT, ";
		    	sql +="	TBLRUJDAERAH DD, ";
		    	sql +="	TBLRUJPEJABATURUSAN PU, ";
		    	sql +="	TBLRUJPEJABATJKPTG PJ ";
		    	sql +="	WHERE MAIN_STAT.ID_DAERAHMHN = DD.ID_DAERAH ";
		    	sql +="	AND PU.ID_DAERAHURUS = DD.ID_DAERAH ";
		    	sql +="	AND PU.ID_PEJABATJKPTG = PJ.ID_PEJABATJKPTG ";
		    	sql +="	AND PU.ID_JENISPEJABAT = 22 ";
		    	sql +="	AND PU.ID_SEKSYEN = 2 ";
		    	//sql +="	AND DD.ID_DAERAH = 121 ";
		    	//sql +="	AND PJ.ID_PEJABATJKPTG = "+ID_UNIT;
		    	if(!ID_UNIT.equals("0"))
		    		sql += " AND PJ.ID_PEJABATJKPTG = "+ID_UNIT;
			    	
		    	System.out.println("setDataPrestasibyUnitBulan:sql= " + sql);
		    	ResultSet rs = stmt.executeQuery(sql);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();

					if (rs.getString("NAMA_DAERAH") != null && rs.getString("NAMA_DAERAH") != "") {
						h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
					}
					if (rs.getString("NAMA_PEJABAT") != null && rs.getString("NAMA_PEJABAT") != "") {
						h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT"));
					}
					if (rs.getString("TOTAL_FAIL") != null && rs.getString("TOTAL_FAIL") != "") {
						h.put("TOTAL_FAIL", rs.getInt("TOTAL_FAIL"));
					}
					if (rs.getString("BAKI_BULAN_LEPAS_S8") != null && rs.getString("BAKI_BULAN_LEPAS_S8") != "") {
						h.put("BAKI_BULAN_LEPAS_S8", rs.getInt("BAKI_BULAN_LEPAS_S8"));
					}
					if (rs.getString("BAKI_BULAN_LEPAS_S8") != null && rs.getString("BAKI_BULAN_LEPAS_S8") != "") {
						h.put("BAKI_BULAN_LEPAS_S8", rs.getInt("BAKI_BULAN_LEPAS_S8"));
					}
					if (rs.getString("BAKI_AKHIR_S8") != null && rs.getString("BAKI_AKHIR_S8") != "") {
						h.put("BAKI_AKHIR_S8", rs.getInt("BAKI_AKHIR_S8"));
					}
					if (rs.getString("BAKI_AKHIR_S17") != null && rs.getString("BAKI_AKHIR_S17") != "") {
						h.put("BAKI_AKHIR_S17", rs.getInt("BAKI_AKHIR_S17"));
					}
					//if (rs.getString("BAKI_BULAN_SEMASA") != null && rs.getString("BAKI_BULAN_SEMASA") != "") {
					//	h.put("BAKI_BULAN_SEMASA", rs.getInt("BAKI_BULAN_SEMASA"));
					//}
					if (rs.getString("BAKI_BULAN_LEPAS_S17") != null && rs.getString("BAKI_BULAN_LEPAS_S17") != "") {
						h.put("BAKI_BULAN_LEPAS_S17", rs.getInt("BAKI_BULAN_LEPAS_S17"));
					}
					if (rs.getString("TERIMA_BULAN_SEMASA_S8") != null && rs.getString("TERIMA_BULAN_SEMASA_S8") != "") {
						h.put("TERIMA_BULAN_SEMASA_S8", rs.getInt("TERIMA_BULAN_SEMASA_S8"));
					}
					if (rs.getString("TERIMA_BULAN_SEMASA_S17") != null && rs.getString("TERIMA_BULAN_SEMASA_S17") != "") {
						h.put("TERIMA_BULAN_SEMASA_S17", rs.getInt("TERIMA_BULAN_SEMASA_S17"));
					}
					if (rs.getString("PANGGIL_BICARA_DALAM_BULAN_S8") != null && rs.getString("PANGGIL_BICARA_DALAM_BULAN_S8") != "") {
						h.put("PANGGIL_BICARA_DALAM_BULAN_S8", rs.getInt("PANGGIL_BICARA_DALAM_BULAN_S8"));
					}
					if (rs.getString("PANGGIL_BICARA_DALAM_BULAN_S17") != null && rs.getString("PANGGIL_BICARA_DALAM_BULAN_S17") != "") {
						h.put("PANGGIL_BICARA_DALAM_BULAN_S17", rs.getInt("PANGGIL_BICARA_DALAM_BULAN_S17"));
					}
					if (rs.getString("SELESAI_BULAN_SEMASA_S8") != null && rs.getString("SELESAI_BULAN_SEMASA_S8") != "") {
						h.put("SELESAI_BULAN_SEMASA_S8", rs.getInt("SELESAI_BULAN_SEMASA_S8"));
					}
					if (rs.getString("SELESAI_BULAN_SEMASA_S17") != null && rs.getString("SELESAI_BULAN_SEMASA_S17") != "") {
						h.put("SELESAI_BULAN_SEMASA_S17", rs.getInt("SELESAI_BULAN_SEMASA_S17"));
					}
					if (rs.getString("BATAL_BULAN_SEMASA_S8") != null && rs.getString("BATAL_BULAN_SEMASA_S8") != "") {
						h.put("BATAL_BULAN_SEMASA_S8", rs.getInt("BATAL_BULAN_SEMASA_S8"));
					}
					if (rs.getString("BATAL_BULAN_SEMASA_S17") != null && rs.getString("BATAL_BULAN_SEMASA_S17") != "") {
						h.put("BATAL_BULAN_SEMASA_S17", rs.getInt("BATAL_BULAN_SEMASA_S17"));
					}
					if (rs.getString("ID_DAERAH") != null && rs.getString("ID_DAERAH") != "") {
						h.put("ID_DAERAH", rs.getInt("ID_DAERAH"));
					}
					
					SenaraiPrestasi.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
				    
	}
	
	public static void setDataPrestasibyNegeriBulan(String BULAN_DARI, String TAHUN_DARI, String ID_NEGERI, String ID_UNIT) throws Exception {
		//SenaraiSerahan = null;		  
		SenaraiPrestasi.clear();
		Db db = null;
		String sql = "";
		List senaraiFail = null;
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sql = "SELECT PJ.NAMA_PEJABAT, DD.ID_DAERAH, DD.NAMA_DAERAH, ";
		    	sql += "MAIN_STAT.TOTAL_FAIL_S8 AS TOTAL_FAIL, ";
		    	sql +=		"MAIN_STAT.BAKI_SEBELUM_BULAN_S8 AS BAKI_BULAN_LEPAS_S8, ";
		    	sql +=		"MAIN_STAT.BAKI_SEBELUM_BULAN_S17 AS BAKI_BULAN_LEPAS_S17, ";
		    	sql +=		"MAIN_STAT.BY_BULAN_S8 AS TERIMA_BULAN_SEMASA_S8, ";
		    	sql +=		"MAIN_STAT.BY_BULAN_S17 AS TERIMA_BULAN_SEMASA_S17, ";
		    	sql +=		"(MAIN_STAT.BAKI_SEBELUM_BULAN_S8 + MAIN_STAT.BY_BULAN_S8) AS JUMLAH_DALAM_TANGAN_S8, ";
		    	sql +=		"(MAIN_STAT.BAKI_SEBELUM_BULAN_S17 + MAIN_STAT.BY_BULAN_S17) AS JUMLAH_DALAM_TANGAN_S17, ";
		    	sql +=		"MAIN_STAT.BICARA_BULAN_S8 AS PANGGIL_BICARA_DALAM_BULAN_S8, ";
		    	sql +=		"MAIN_STAT.BICARA_BULAN_S17 AS PANGGIL_BICARA_DALAM_BULAN_S17, ";
		    	sql +=		"MAIN_STAT.SELESAI_BULAN_S8 AS SELESAI_BULAN_SEMASA_S8, ";
		    	sql +=	"MAIN_STAT.SELESAI_BULAN_S17 AS SELESAI_BULAN_SEMASA_S17, ";
		    	sql +=	"MAIN_STAT.BATAL_BULAN_S8 AS BATAL_BULAN_SEMASA_S8, ";
		    	sql +=	"MAIN_STAT.BATAL_BULAN_S17 AS BATAL_BULAN_SEMASA_S17, ";
		    	sql +=	"((MAIN_STAT.BAKI_SEBELUM_BULAN_S8 + MAIN_STAT.BY_BULAN_S8)-(MAIN_STAT.SELESAI_BULAN_S8 + MAIN_STAT.BATAL_BULAN_S8)) AS BAKI_AKHIR_S8, ";
		    	sql +=	"((MAIN_STAT.BAKI_SEBELUM_BULAN_S17 + MAIN_STAT.BY_BULAN_S17)-(MAIN_STAT.SELESAI_BULAN_S17 + MAIN_STAT.BATAL_BULAN_S17)) AS BAKI_AKHIR_S17 ";
		    	sql +="FROM ";
		    	sql +="(SELECT   G.ID_DAERAHMHN, ";
		    	sql +="COUNT ";
		    	sql +="(DISTINCT CASE ";
		    	sql +=		"WHEN H.ID_SUBURUSAN IN (59) ";
		    	sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="THEN G.ID_PERMOHONAN ";
		    	sql +="END ";
		    	sql +=") AS TOTAL_FAIL_S8, ";
		    	sql +="COUNT ";
		    	sql +="(DISTINCT CASE ";
		    	sql +="WHEN H.ID_SUBURUSAN IN (59) ";
		    	sql +="AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="AND K.FLAG_JENIS_KEPUTUSAN IN ('3') THEN G.ID_PERMOHONAN ";
		    	sql +="WHEN H.ID_SUBURUSAN IN (59) ";
		    	sql +="AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="AND K.ID_PERINTAH IS NULL ";
		    	sql +="THEN G.ID_PERMOHONAN ";
		    	sql +="END ";
		    	sql +=") AS BAKI_SEBELUM_BULAN_S8, ";
		    	sql +="COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		    	sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN IN ('3') THEN G.ID_PERMOHONAN ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		    	sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	AND K.ID_PERINTAH IS NULL ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +=" END ";
		    	sql +="	) AS BAKI_SEBELUM_BULAN_S17, ";
		    	sql +="	COUNT ";
		    	sql +=" (DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
		    	sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BY_BULAN_S8, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		    	sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BY_BULAN_S17, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
		    	sql +="	AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BICARA_BULAN_S8, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		    	sql +="	AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BICARA_BULAN_S17, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
		    	sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '0' ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS SELESAI_BULAN_S8, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		    	sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '0' ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS SELESAI_BULAN_S17, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
		    	sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '2' ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BATAL_BULAN_S8, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		    	sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '2' ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BATAL_BULAN_S17 ";
		    	sql +="	FROM TBLPPKPERMOHONAN G, ";
		    	sql +="	TBLPFDFAIL H, ";
		    	sql +="	TBLPPKPERBICARAAN I, ";
		    	sql +="	TBLPPKKEPUTUSANPERMOHONAN J, ";
		    	sql +="	TBLPPKPERINTAH K ";
		    	sql +="	WHERE G.ID_FAIL = H.ID_FAIL ";
		    	sql +="	AND H.ID_SUBURUSAN IN (59, 60) ";
		    	sql +="	AND H.ID_SEKSYEN = 2 ";
		    	sql +="	AND G.ID_PERMOHONAN = J.ID_PERMOHONAN(+) ";
		    	sql +="	AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN(+) ";
		    	sql +="	AND I.ID_PERBICARAAN = K.ID_PERBICARAAN(+) ";
		    	sql +="	AND H.NO_FAIL IS NOT NULL ";
		    	sql +="	AND G.ID_STATUS NOT IN (999, 70, 152) ";
		    	sql +="	GROUP BY G.ID_DAERAHMHN) MAIN_STAT, ";
		    	sql +="	TBLRUJDAERAH DD, ";
		    	sql +="	TBLRUJPEJABATURUSAN PU, ";
		    	sql +="	TBLRUJPEJABATJKPTG PJ ";
		    	sql +="	WHERE MAIN_STAT.ID_DAERAHMHN = DD.ID_DAERAH ";
		    	sql +="	AND PU.ID_DAERAHURUS = DD.ID_DAERAH ";
		    	sql +="	AND PU.ID_PEJABATJKPTG = PJ.ID_PEJABATJKPTG ";
		    	sql +="	AND PU.ID_JENISPEJABAT = 22 ";
		    	sql +="	AND PU.ID_SEKSYEN = 2 ";
		    	//sql +="	AND DD.ID_DAERAH = 121 ";
		    	//sql +="	AND PJ.ID_PEJABATJKPTG = "+ID_UNIT;
		    	if(!ID_NEGERI.equals("0"))
		    		sql += " AND PJ.ID_PEJABATJKPTG = "+ID_NEGERI;
			    	
		    	myLogger.info("setDataPrestasibyNegeriBulan:sql= " + sql);
		    	ResultSet rs = stmt.executeQuery(sql);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();

					if (rs.getString("NAMA_DAERAH") != null && rs.getString("NAMA_DAERAH") != "") {
						h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
					}
					if (rs.getString("NAMA_PEJABAT") != null && rs.getString("NAMA_PEJABAT") != "") {
						h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT"));
					}
					if (rs.getString("TOTAL_FAIL") != null && rs.getString("TOTAL_FAIL") != "") {
						h.put("TOTAL_FAIL", rs.getInt("TOTAL_FAIL"));
					}
					if (rs.getString("BAKI_BULAN_LEPAS_S8") != null && rs.getString("BAKI_BULAN_LEPAS_S8") != "") {
						h.put("BAKI_BULAN_LEPAS_S8", rs.getInt("BAKI_BULAN_LEPAS_S8"));
					}
					if (rs.getString("BAKI_BULAN_LEPAS_S8") != null && rs.getString("BAKI_BULAN_LEPAS_S8") != "") {
						h.put("BAKI_BULAN_LEPAS_S8", rs.getInt("BAKI_BULAN_LEPAS_S8"));
					}
					if (rs.getString("BAKI_AKHIR_S8") != null && rs.getString("BAKI_AKHIR_S8") != "") {
						h.put("BAKI_AKHIR_S8", rs.getInt("BAKI_AKHIR_S8"));
					}
					if (rs.getString("BAKI_AKHIR_S17") != null && rs.getString("BAKI_AKHIR_S17") != "") {
						h.put("BAKI_AKHIR_S17", rs.getInt("BAKI_AKHIR_S17"));
					}
					//if (rs.getString("BAKI_BULAN_SEMASA") != null && rs.getString("BAKI_BULAN_SEMASA") != "") {
					//	h.put("BAKI_BULAN_SEMASA", rs.getInt("BAKI_BULAN_SEMASA"));
					//}
					if (rs.getString("BAKI_BULAN_LEPAS_S17") != null && rs.getString("BAKI_BULAN_LEPAS_S17") != "") {
						h.put("BAKI_BULAN_LEPAS_S17", rs.getInt("BAKI_BULAN_LEPAS_S17"));
					}
					if (rs.getString("TERIMA_BULAN_SEMASA_S8") != null && rs.getString("TERIMA_BULAN_SEMASA_S8") != "") {
						h.put("TERIMA_BULAN_SEMASA_S8", rs.getInt("TERIMA_BULAN_SEMASA_S8"));
					}
					if (rs.getString("TERIMA_BULAN_SEMASA_S17") != null && rs.getString("TERIMA_BULAN_SEMASA_S17") != "") {
						h.put("TERIMA_BULAN_SEMASA_S17", rs.getInt("TERIMA_BULAN_SEMASA_S17"));
					}
					if (rs.getString("PANGGIL_BICARA_DALAM_BULAN_S8") != null && rs.getString("PANGGIL_BICARA_DALAM_BULAN_S8") != "") {
						h.put("PANGGIL_BICARA_DALAM_BULAN_S8", rs.getInt("PANGGIL_BICARA_DALAM_BULAN_S8"));
					}
					if (rs.getString("PANGGIL_BICARA_DALAM_BULAN_S17") != null && rs.getString("PANGGIL_BICARA_DALAM_BULAN_S17") != "") {
						h.put("PANGGIL_BICARA_DALAM_BULAN_S17", rs.getInt("PANGGIL_BICARA_DALAM_BULAN_S17"));
					}
					if (rs.getString("SELESAI_BULAN_SEMASA_S8") != null && rs.getString("SELESAI_BULAN_SEMASA_S8") != "") {
						h.put("SELESAI_BULAN_SEMASA_S8", rs.getInt("SELESAI_BULAN_SEMASA_S8"));
					}
					if (rs.getString("SELESAI_BULAN_SEMASA_S17") != null && rs.getString("SELESAI_BULAN_SEMASA_S17") != "") {
						h.put("SELESAI_BULAN_SEMASA_S17", rs.getInt("SELESAI_BULAN_SEMASA_S17"));
					}
					if (rs.getString("BATAL_BULAN_SEMASA_S8") != null && rs.getString("BATAL_BULAN_SEMASA_S8") != "") {
						h.put("BATAL_BULAN_SEMASA_S8", rs.getInt("BATAL_BULAN_SEMASA_S8"));
					}
					if (rs.getString("BATAL_BULAN_SEMASA_S17") != null && rs.getString("BATAL_BULAN_SEMASA_S17") != "") {
						h.put("BATAL_BULAN_SEMASA_S17", rs.getInt("BATAL_BULAN_SEMASA_S17"));
					}
					if (rs.getString("ID_DAERAH") != null && rs.getString("ID_DAERAH") != "") {
						h.put("ID_DAERAH", rs.getInt("ID_DAERAH"));
					}
					
					SenaraiPrestasi.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
				    
	}
	
	public Vector getDataPrestasiMengikutDaerah(String laporan,String BULAN_DARI, String TAHUN_DARI, String ID_NEGERI, String ID_UNIT) 
		throws Exception {
		//SenaraiSerahan = null;		  
		SenaraiPrestasi.clear();
		Db db = null;
		String sql = "";
		List senaraiFail = null;
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sql = "SELECT PJ.NAMA_PEJABAT, DD.ID_DAERAH, DD.NAMA_DAERAH, ";
		    	sql += "MAIN_STAT.TOTAL_FAIL_S8 AS TOTAL_FAIL, ";
		    	sql +=		"MAIN_STAT.BAKI_SEBELUM_BULAN_S8 AS BAKI_BULAN_LEPAS_S8, ";
		    	sql +=		"MAIN_STAT.BAKI_SEBELUM_BULAN_S17 AS BAKI_BULAN_LEPAS_S17, ";
		    	sql +=		"MAIN_STAT.BY_BULAN_S8 AS TERIMA_BULAN_SEMASA_S8, ";
		    	sql +=		"MAIN_STAT.BY_BULAN_S17 AS TERIMA_BULAN_SEMASA_S17, ";
		    	sql +=		"(MAIN_STAT.BAKI_SEBELUM_BULAN_S8 + MAIN_STAT.BY_BULAN_S8) AS JUMLAH_DALAM_TANGAN_S8, ";
		    	sql +=		"(MAIN_STAT.BAKI_SEBELUM_BULAN_S17 + MAIN_STAT.BY_BULAN_S17) AS JUMLAH_DALAM_TANGAN_S17, ";
		    	sql +=		"MAIN_STAT.BICARA_BULAN_S8 AS PANGGIL_BICARA_DALAM_BULAN_S8, ";
		    	sql +=		"MAIN_STAT.BICARA_BULAN_S17 AS PANGGIL_BICARA_DALAM_BULAN_S17, ";
		    	sql +=		"MAIN_STAT.SELESAI_BULAN_S8 AS SELESAI_BULAN_SEMASA_S8, ";
		    	sql +=	"MAIN_STAT.SELESAI_BULAN_S17 AS SELESAI_BULAN_SEMASA_S17, ";
		    	sql +=	"MAIN_STAT.BATAL_BULAN_S8 AS BATAL_BULAN_SEMASA_S8, ";
		    	sql +=	"MAIN_STAT.BATAL_BULAN_S17 AS BATAL_BULAN_SEMASA_S17, ";
		    	sql +=	"((MAIN_STAT.BAKI_SEBELUM_BULAN_S8 + MAIN_STAT.BY_BULAN_S8)-(MAIN_STAT.SELESAI_BULAN_S8 + MAIN_STAT.BATAL_BULAN_S8)) AS BAKI_AKHIR_S8, ";
		    	sql +=	"((MAIN_STAT.BAKI_SEBELUM_BULAN_S17 + MAIN_STAT.BY_BULAN_S17)-(MAIN_STAT.SELESAI_BULAN_S17 + MAIN_STAT.BATAL_BULAN_S17)) AS BAKI_AKHIR_S17 ";
		    	sql +="FROM ";
		    	sql +="(SELECT   G.ID_DAERAHMHN, ";
		    	sql +="COUNT ";
		    	sql +="(DISTINCT CASE ";
		    	sql +=		"WHEN H.ID_SUBURUSAN IN (59) ";
		    	if(laporan.equals("bulan"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	else if(laporan.equals("tahun"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') <= TO_DATE('"+TAHUN_DARI+"', 'YYYY') ";
		    	else if(laporan.equals("selang"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
    		
		    	sql +="THEN G.ID_PERMOHONAN ";
		    	sql +="END ";
		    	sql +=") AS TOTAL_FAIL_S8, ";
		    	sql +="COUNT ";
		    	sql +="(DISTINCT CASE ";
		    	sql +="WHEN H.ID_SUBURUSAN IN (59) ";
		       	if(laporan.equals("bulan"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	else if(laporan.equals("tahun"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') < TO_DATE('"+TAHUN_DARI+"', 'YYYY') ";
		    	else if(laporan.equals("selang"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";

		    	sql +="AND K.FLAG_JENIS_KEPUTUSAN IN ('3') THEN G.ID_PERMOHONAN ";
		    	sql +="WHEN H.ID_SUBURUSAN IN (59) ";
		       	if(laporan.equals("bulan"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	else if(laporan.equals("tahun"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') < TO_DATE('"+TAHUN_DARI+"', 'YYYY') ";
		    	else if(laporan.equals("selang"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";

		    	sql +="AND K.ID_PERINTAH IS NULL ";
		    	sql +="THEN G.ID_PERMOHONAN ";
		    	sql +="END ";
		    	sql +=") AS BAKI_SEBELUM_BULAN_S8, ";
		    	sql +="COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		       	if(laporan.equals("bulan"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	else if(laporan.equals("tahun"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') < TO_DATE('"+TAHUN_DARI+"', 'YYYY') ";
		    	else if(laporan.equals("selang"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";

		    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN IN ('3') THEN G.ID_PERMOHONAN ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		       	if(laporan.equals("bulan"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	else if(laporan.equals("tahun"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') < TO_DATE('"+TAHUN_DARI+"', 'YYYY') ";
		    	else if(laporan.equals("selang"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";

		    	sql +="	AND K.ID_PERINTAH IS NULL ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +=" END ";
		    	sql +="	) AS BAKI_SEBELUM_BULAN_S17, ";
		    	sql +="	COUNT ";
		    	sql +=" (DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
		       	if(laporan.equals("bulan"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	else if(laporan.equals("tahun"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') = TO_DATE('"+TAHUN_DARI+"', 'YYYY') ";
		    	else if(laporan.equals("selang"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";

		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BY_BULAN_S8, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		       	if(laporan.equals("bulan"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	else if(laporan.equals("tahun"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') = TO_DATE('"+TAHUN_DARI+"', 'YYYY') ";
		    	else if(laporan.equals("selang"))
		    		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";

		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BY_BULAN_S17, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
		       	if(laporan.equals("bulan"))
		    		sql +=	"AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	else if(laporan.equals("tahun"))
		    		sql +=	"AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'YYYY'), 'YYYY') <= TO_DATE('"+TAHUN_DARI+"', 'YYYY') ";
		    	else if(laporan.equals("selang"))
		    		sql +=	"AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";

		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BICARA_BULAN_S8, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		       	if(laporan.equals("bulan"))
		    		sql +=	"AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	else if(laporan.equals("tahun"))
		    		sql +=	"AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'YYYY'), 'YYYY') = TO_DATE('"+TAHUN_DARI+"', 'YYYY') ";
		    	else if(laporan.equals("selang"))
		    		sql +=	"AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";

		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BICARA_BULAN_S17, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
		       	if(laporan.equals("bulan"))
		    		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	else if(laporan.equals("tahun"))
		    		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'YYYY'), 'YYYY') = TO_DATE('"+TAHUN_DARI+"', 'YYYY') ";
		    	else if(laporan.equals("selang"))
		    		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";

		    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '0' ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS SELESAI_BULAN_S8, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		       	if(laporan.equals("bulan"))
		    		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	else if(laporan.equals("tahun"))
		    		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'YYYY'), 'YYYY') <= TO_DATE('"+TAHUN_DARI+"', 'YYYY') ";
		    	else if(laporan.equals("selang"))
		    		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";

		    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '0' ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS SELESAI_BULAN_S17, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
		       	if(laporan.equals("bulan"))
		    		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	else if(laporan.equals("tahun"))
		    		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'YYYY'), 'YYYY') = TO_DATE('"+TAHUN_DARI+"', 'YYYY') ";
		    	else if(laporan.equals("selang"))
		    		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";

		    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '2' ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BATAL_BULAN_S8, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		       	if(laporan.equals("bulan"))
		    		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	else if(laporan.equals("tahun"))
		    		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'YYYY'), 'YYYY') = TO_DATE('"+TAHUN_DARI+"', 'YYYY') ";
		    	else if(laporan.equals("selang"))
		    		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";

		    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '2' ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BATAL_BULAN_S17 ";
		    	sql +="	FROM TBLPPKPERMOHONAN G, ";
		    	sql +="	TBLPFDFAIL H, ";
		    	sql +="	TBLPPKPERBICARAAN I, ";
		    	sql +="	TBLPPKKEPUTUSANPERMOHONAN J, ";
		    	sql +="	TBLPPKPERINTAH K ";
		    	sql +="	WHERE G.ID_FAIL = H.ID_FAIL ";
		    	sql +="	AND H.ID_SUBURUSAN IN (59, 60) ";
		    	sql +="	AND H.ID_SEKSYEN = 2 ";
		    	sql +="	AND G.ID_PERMOHONAN = J.ID_PERMOHONAN(+) ";
		    	sql +="	AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN(+) ";
		    	sql +="	AND I.ID_PERBICARAAN = K.ID_PERBICARAAN(+) ";
		    	sql +="	AND H.NO_FAIL IS NOT NULL ";
		    	sql +="	AND G.ID_STATUS NOT IN (999) ";
		    	sql +="	GROUP BY G.ID_DAERAHMHN) MAIN_STAT, ";
		    	sql +="	TBLRUJDAERAH DD, ";
		    	sql +="	TBLRUJPEJABATURUSAN PU, ";
		    	sql +="	TBLRUJPEJABATJKPTG PJ ";
		    	sql +="	WHERE MAIN_STAT.ID_DAERAHMHN = DD.ID_DAERAH ";
		    	sql +="	AND PU.ID_DAERAHURUS = DD.ID_DAERAH ";
		    	sql +="	AND PU.ID_PEJABATJKPTG = PJ.ID_PEJABATJKPTG ";
		    	sql +="	AND PU.ID_JENISPEJABAT = 22 ";
		    	sql +="	AND PU.ID_SEKSYEN = 2 ";
		    	//sql +="	AND DD.ID_DAERAH = 121 ";
		    	//sql +="	AND PJ.ID_PEJABATJKPTG = "+ID_UNIT;
		    	if(!ID_NEGERI.equals("0"))
		    		sql += " AND PJ.ID_PEJABATJKPTG = "+ID_NEGERI;
			    	
		    	myLogger.info("setDataPrestasibyUnitBulan:sql= " + sql);
		    	ResultSet rs = stmt.executeQuery(sql);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();

					if (rs.getString("NAMA_DAERAH") != null && rs.getString("NAMA_DAERAH") != "") {
						h.put("NAMA", rs.getString("NAMA_DAERAH"));
					}
					if (rs.getString("TOTAL_FAIL") != null && rs.getString("TOTAL_FAIL") != "") {
						h.put("TOTAL_FAIL", rs.getInt("TOTAL_FAIL"));
					}
					if (rs.getString("BAKI_BULAN_LEPAS_S8") != null && rs.getString("BAKI_BULAN_LEPAS_S8") != "") {
						h.put("BAKI_BULAN_LEPAS_S8", rs.getInt("BAKI_BULAN_LEPAS_S8"));
					}
					if (rs.getString("BAKI_BULAN_LEPAS_S8") != null && rs.getString("BAKI_BULAN_LEPAS_S8") != "") {
						h.put("BAKI_BULAN_LEPAS_S8", rs.getInt("BAKI_BULAN_LEPAS_S8"));
					}
					if (rs.getString("BAKI_AKHIR_S8") != null && rs.getString("BAKI_AKHIR_S8") != "") {
						h.put("BAKI_AKHIR_S8", rs.getInt("BAKI_AKHIR_S8"));
					}
					if (rs.getString("BAKI_AKHIR_S17") != null && rs.getString("BAKI_AKHIR_S17") != "") {
						h.put("BAKI_AKHIR_S17", rs.getInt("BAKI_AKHIR_S17"));
					}
					//if (rs.getString("BAKI_BULAN_SEMASA") != null && rs.getString("BAKI_BULAN_SEMASA") != "") {
					//	h.put("BAKI_BULAN_SEMASA", rs.getInt("BAKI_BULAN_SEMASA"));
					//}
					if (rs.getString("BAKI_BULAN_LEPAS_S17") != null && rs.getString("BAKI_BULAN_LEPAS_S17") != "") {
						h.put("BAKI_BULAN_LEPAS_S17", rs.getInt("BAKI_BULAN_LEPAS_S17"));
					}
					if (rs.getString("TERIMA_BULAN_SEMASA_S8") != null && rs.getString("TERIMA_BULAN_SEMASA_S8") != "") {
						h.put("TERIMA_BULAN_SEMASA_S8", rs.getInt("TERIMA_BULAN_SEMASA_S8"));
					}
					if (rs.getString("TERIMA_BULAN_SEMASA_S17") != null && rs.getString("TERIMA_BULAN_SEMASA_S17") != "") {
						h.put("TERIMA_BULAN_SEMASA_S17", rs.getInt("TERIMA_BULAN_SEMASA_S17"));
					}
					if (rs.getString("PANGGIL_BICARA_DALAM_BULAN_S8") != null && rs.getString("PANGGIL_BICARA_DALAM_BULAN_S8") != "") {
						h.put("PANGGIL_BICARA_DALAM_BULAN_S8", rs.getInt("PANGGIL_BICARA_DALAM_BULAN_S8"));
					}
					if (rs.getString("PANGGIL_BICARA_DALAM_BULAN_S17") != null && rs.getString("PANGGIL_BICARA_DALAM_BULAN_S17") != "") {
						h.put("PANGGIL_BICARA_DALAM_BULAN_S17", rs.getInt("PANGGIL_BICARA_DALAM_BULAN_S17"));
					}
					if (rs.getString("SELESAI_BULAN_SEMASA_S8") != null && rs.getString("SELESAI_BULAN_SEMASA_S8") != "") {
						h.put("SELESAI_BULAN_SEMASA_S8", rs.getInt("SELESAI_BULAN_SEMASA_S8"));
					}
					if (rs.getString("SELESAI_BULAN_SEMASA_S17") != null && rs.getString("SELESAI_BULAN_SEMASA_S17") != "") {
						h.put("SELESAI_BULAN_SEMASA_S17", rs.getInt("SELESAI_BULAN_SEMASA_S17"));
					}
					if (rs.getString("BATAL_BULAN_SEMASA_S8") != null && rs.getString("BATAL_BULAN_SEMASA_S8") != "") {
						h.put("BATAL_BULAN_SEMASA_S8", rs.getInt("BATAL_BULAN_SEMASA_S8"));
					}
					if (rs.getString("BATAL_BULAN_SEMASA_S17") != null && rs.getString("BATAL_BULAN_SEMASA_S17") != "") {
						h.put("BATAL_BULAN_SEMASA_S17", rs.getInt("BATAL_BULAN_SEMASA_S17"));
					}
					if (rs.getString("ID_DAERAH") != null && rs.getString("ID_DAERAH") != "") {
						h.put("ID_DAERAH", rs.getInt("ID_DAERAH"));
						h.put("ID_RUJUKAN", rs.getInt("ID_DAERAH"));
					}
					
					SenaraiPrestasi.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
			return SenaraiPrestasi ;  
	}
	
	public Vector<Hashtable<String,String>> getDataPrestasiNegeriBulan(
		String BULAN_DARI, String TAHUN_DARI, String ID_NEGERI) throws Exception {
		SenaraiPrestasi.clear();
		Db db = null;
		String sql = "";
		List senaraiFail = null;
		try {
			db = new Db();
			Connection con = db.getConnection();
			Statement stmt = db.getStatement();
			sql = "SELECT distinct PJ.ID_PEJABATJKPTG ID_RUJUKAN, PJ.NAMA_PEJABAT NAMA ";
		   	sql += ",MAIN_STAT.TOTAL_FAIL_S8 AS TOTAL_FAIL, ";
		   	sql +=	"MAIN_STAT.BAKI_SEBELUM_BULAN_S8 AS BAKI_BULAN_LEPAS_S8, ";
		   	sql +=	"MAIN_STAT.BAKI_SEBELUM_BULAN_S17 AS BAKI_BULAN_LEPAS_S17, ";
		   	sql +=	"MAIN_STAT.BY_BULAN_S8 AS TERIMA_BULAN_SEMASA_S8, ";
	    	sql +=	"MAIN_STAT.BY_BULAN_S17 AS TERIMA_BULAN_SEMASA_S17, ";
	    	sql +=	"(MAIN_STAT.BAKI_SEBELUM_BULAN_S8 + MAIN_STAT.BY_BULAN_S8) AS JUMLAH_DALAM_TANGAN_S8, ";
	    	sql +=	"(MAIN_STAT.BAKI_SEBELUM_BULAN_S17 + MAIN_STAT.BY_BULAN_S17) AS JUMLAH_DALAM_TANGAN_S17, ";
		   	sql +=	"MAIN_STAT.BICARA_BULAN_S8 AS PANGGIL_BICARA_DALAM_BULAN_S8, ";
		   	sql +=	"MAIN_STAT.BICARA_BULAN_S17 AS PANGGIL_BICARA_DALAM_BULAN_S17, ";
		   	sql +=	"MAIN_STAT.SELESAI_BULAN_S8 AS SELESAI_BULAN_SEMASA_S8, ";
		   	sql +=	"MAIN_STAT.SELESAI_BULAN_S17 AS SELESAI_BULAN_SEMASA_S17, ";
		   	sql +=	"MAIN_STAT.BATAL_BULAN_S8 AS BATAL_BULAN_SEMASA_S8, ";
		   	sql +=	"MAIN_STAT.BATAL_BULAN_S17 AS BATAL_BULAN_SEMASA_S17, ";
		   	sql +=	"((MAIN_STAT.BAKI_SEBELUM_BULAN_S8 + MAIN_STAT.BY_BULAN_S8)-(MAIN_STAT.SELESAI_BULAN_S8 + MAIN_STAT.BATAL_BULAN_S8)) AS BAKI_AKHIR_S8, ";
		   	sql +=	"((MAIN_STAT.BAKI_SEBELUM_BULAN_S17 + MAIN_STAT.BY_BULAN_S17)-(MAIN_STAT.SELESAI_BULAN_S17 + MAIN_STAT.BATAL_BULAN_S17)) AS BAKI_AKHIR_S17 ";
		   	sql +=" ,PU.ID_PEJABATJKPTG,PU.ID_SEKSYEN,PU.ID_JENISPEJABAT " +
		    			"FROM ";
	    	sql +="(SELECT PU.ID_PEJABATJKPTG, ";
	    	sql +="COUNT ";
	    	sql +="(DISTINCT CASE ";
	    	sql +=		"WHEN H.ID_SUBURUSAN IN (59) ";
		  	sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		   	sql +="THEN G.ID_PERMOHONAN ";
		   	sql +="END ";
	    	sql +=") AS TOTAL_FAIL_S8, ";
	    	sql +="COUNT ";
	    	sql +="(DISTINCT CASE ";
		   	sql +="WHEN H.ID_SUBURUSAN IN (59) ";
		   	sql +="AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		   	sql +="AND K.FLAG_JENIS_KEPUTUSAN IN ('3') THEN G.ID_PERMOHONAN ";
		   	sql +="WHEN H.ID_SUBURUSAN IN (59) ";
		   	sql +="AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		   	sql +="AND K.ID_PERINTAH IS NULL ";
		   	sql +="THEN G.ID_PERMOHONAN ";
	    	sql +="END ";
	    	sql +=") AS BAKI_SEBELUM_BULAN_S8, ";
	    	sql +="COUNT ";
		   	sql +="	(DISTINCT CASE ";
		   	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		   	sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		   	sql +="	AND K.FLAG_JENIS_KEPUTUSAN IN ('3') THEN G.ID_PERMOHONAN ";
	    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
	    	sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
	    	sql +="	AND K.ID_PERINTAH IS NULL ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +=" END ";
		    	sql +="	) AS BAKI_SEBELUM_BULAN_S17, ";
		    	sql +="	COUNT ";
		    	sql +=" (DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
		    	sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BY_BULAN_S8, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		    	sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BY_BULAN_S17, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
		    	sql +="	AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BICARA_BULAN_S8, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		    	sql +="	AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BICARA_BULAN_S17, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
		    	sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '0' ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS SELESAI_BULAN_S8, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		    	sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '0' ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS SELESAI_BULAN_S17, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
		    	sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '2' ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BATAL_BULAN_S8, ";
		    	sql +="	COUNT ";
		    	sql +="	(DISTINCT CASE ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
		    	sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '2' ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +="	END ";
		    	sql +="	) AS BATAL_BULAN_S17 ";
		    	sql +="	FROM TBLPPKPERMOHONAN G, ";
		    	sql +="	TBLPFDFAIL H, ";
		    	sql +="	TBLPPKPERBICARAAN I, ";
		    	sql +="	TBLPPKKEPUTUSANPERMOHONAN J, ";
		    	sql +="	TBLPPKPERINTAH K,TBLRUJPEJABATURUSAN PU, tblrujsuburusanstatusfail stf ";
		    	sql +="	WHERE G.ID_FAIL = H.ID_FAIL ";
		    	sql +="	AND STF.ID_FAIL = H.ID_FAIL ";
		    	sql +="	AND stf.aktif = 1 ";
		    	sql +="	AND H.ID_SUBURUSAN IN (59, 60) ";
		    	sql +="	AND H.ID_SEKSYEN = 2 ";
		    	sql +="	AND G.ID_PERMOHONAN = J.ID_PERMOHONAN(+) ";
		    	sql +="	AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN(+) ";
		    	sql +="	AND I.ID_PERBICARAAN = K.ID_PERBICARAAN(+) ";
		    	sql +="	AND H.NO_FAIL IS NOT NULL ";
		    	sql +="	AND G.ID_STATUS NOT IN (999) ";
		    	sql +="	AND PU.ID_DAERAHURUS = G.ID_DAERAHMHN ";
		    	sql +="	GROUP BY PU.ID_PEJABATJKPTG) MAIN_STAT, ";
		    	//sql +="	TBLRUJDAERAH DD, ";
		    	sql +="	TBLRUJPEJABATURUSAN PU, ";
		    	sql +="	TBLRUJPEJABATJKPTG PJ ";
		    	sql +="	WHERE MAIN_STAT.ID_PEJABATJKPTG = PJ.ID_PEJABATJKPTG ";
		    	//sql +="	AND PU.ID_DAERAHURUS = DD.ID_DAERAH ";
		    	sql +="	AND PU.ID_PEJABATJKPTG = PJ.ID_PEJABATJKPTG ";
		    	sql +="	AND PU.ID_JENISPEJABAT = 22 ";
		    	sql +="	AND PU.ID_SEKSYEN = 2 ";
		    	//sql +="	AND DD.ID_DAERAH = 121 ";
		    	//sql +="	AND PJ.ID_PEJABATJKPTG = "+ID_UNIT;
		    	if(!ID_NEGERI.equals("0"))
		    		sql += " AND PU.ID_NEGERI = "+ID_NEGERI;
			    	
		    	System.out.println("getDataPrestasiNegeriBulan:sql= " + sql);
		    	ResultSet rs = stmt.executeQuery(sql);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();
					if (rs.getString("NAMA") != null && rs.getString("NAMA") != "") {
						h.put("NAMA", rs.getString("NAMA"));
					}
					if (rs.getString("TOTAL_FAIL") != null && rs.getString("TOTAL_FAIL") != "") {
						h.put("TOTAL_FAIL", rs.getInt("TOTAL_FAIL"));
					}
					if (rs.getString("BAKI_BULAN_LEPAS_S8") != null && rs.getString("BAKI_BULAN_LEPAS_S8") != "") {
						h.put("BAKI_BULAN_LEPAS_S8", rs.getInt("BAKI_BULAN_LEPAS_S8"));
					}
					if (rs.getString("BAKI_BULAN_LEPAS_S8") != null && rs.getString("BAKI_BULAN_LEPAS_S8") != "") {
						h.put("BAKI_BULAN_LEPAS_S8", rs.getInt("BAKI_BULAN_LEPAS_S8"));
					}
					if (rs.getString("BAKI_AKHIR_S8") != null && rs.getString("BAKI_AKHIR_S8") != "") {
						h.put("BAKI_AKHIR_S8", rs.getInt("BAKI_AKHIR_S8"));
					}
					if (rs.getString("BAKI_AKHIR_S17") != null && rs.getString("BAKI_AKHIR_S17") != "") {
						h.put("BAKI_AKHIR_S17", rs.getInt("BAKI_AKHIR_S17"));
					}
					//if (rs.getString("BAKI_BULAN_SEMASA") != null && rs.getString("BAKI_BULAN_SEMASA") != "") {
					//	h.put("BAKI_BULAN_SEMASA", rs.getInt("BAKI_BULAN_SEMASA"));
					//}
					if (rs.getString("BAKI_BULAN_LEPAS_S17") != null && rs.getString("BAKI_BULAN_LEPAS_S17") != "") {
						h.put("BAKI_BULAN_LEPAS_S17", rs.getInt("BAKI_BULAN_LEPAS_S17"));
					}
					if (rs.getString("TERIMA_BULAN_SEMASA_S8") != null && rs.getString("TERIMA_BULAN_SEMASA_S8") != "") {
						h.put("TERIMA_BULAN_SEMASA_S8", rs.getInt("TERIMA_BULAN_SEMASA_S8"));
					}
					if (rs.getString("TERIMA_BULAN_SEMASA_S17") != null && rs.getString("TERIMA_BULAN_SEMASA_S17") != "") {
						h.put("TERIMA_BULAN_SEMASA_S17", rs.getInt("TERIMA_BULAN_SEMASA_S17"));
					}
					if (rs.getString("PANGGIL_BICARA_DALAM_BULAN_S8") != null && rs.getString("PANGGIL_BICARA_DALAM_BULAN_S8") != "") {
						h.put("PANGGIL_BICARA_DALAM_BULAN_S8", rs.getInt("PANGGIL_BICARA_DALAM_BULAN_S8"));
					}
					if (rs.getString("PANGGIL_BICARA_DALAM_BULAN_S17") != null && rs.getString("PANGGIL_BICARA_DALAM_BULAN_S17") != "") {
						h.put("PANGGIL_BICARA_DALAM_BULAN_S17", rs.getInt("PANGGIL_BICARA_DALAM_BULAN_S17"));
					}
					if (rs.getString("SELESAI_BULAN_SEMASA_S8") != null && rs.getString("SELESAI_BULAN_SEMASA_S8") != "") {
						h.put("SELESAI_BULAN_SEMASA_S8", rs.getInt("SELESAI_BULAN_SEMASA_S8"));
					}
					if (rs.getString("SELESAI_BULAN_SEMASA_S17") != null && rs.getString("SELESAI_BULAN_SEMASA_S17") != "") {
						h.put("SELESAI_BULAN_SEMASA_S17", rs.getInt("SELESAI_BULAN_SEMASA_S17"));
					}
					if (rs.getString("BATAL_BULAN_SEMASA_S8") != null && rs.getString("BATAL_BULAN_SEMASA_S8") != "") {
						h.put("BATAL_BULAN_SEMASA_S8", rs.getInt("BATAL_BULAN_SEMASA_S8"));
					}
					if (rs.getString("BATAL_BULAN_SEMASA_S17") != null && rs.getString("BATAL_BULAN_SEMASA_S17") != "") {
						h.put("BATAL_BULAN_SEMASA_S17", rs.getInt("BATAL_BULAN_SEMASA_S17"));
					}
					if (rs.getString("ID_RUJUKAN") != null && rs.getString("ID_RUJUKAN") != "") {
						h.put("ID_RUJUKAN", rs.getInt("ID_RUJUKAN"));
					}
					
					SenaraiPrestasi.addElement(h);
					
					}
		   
		    	}
		    
		  finally {
			  if (db != null) db.close();
		  }
		   return SenaraiPrestasi;
		    
	}
	public Vector<Hashtable<String,String>> getDataPrestasiMengikutUnit(String laporan
		,int bulanDari,int bulanTamat,String tahunDari,String tahunTamat, String idNegeri) 
		throws Exception {
		SenaraiPrestasi.clear();
		Db db = null;
		String sql = "";
		List senaraiFail = null;
		try {
			db = new Db();
			Connection con = db.getConnection();
			Statement stmt = db.getStatement();
			sql = "SELECT distinct PJ.ID_PEJABATJKPTG ID_RUJUKAN, PJ.NAMA_PEJABAT NAMA ";
			   	sql += ",MAIN_STAT.TOTAL_FAIL_S8 AS TOTAL_FAIL, ";
			   	sql +=	"MAIN_STAT.BAKI_SEBELUM_BULAN_S8 AS BAKI_BULAN_LEPAS_S8, ";
			   	sql +=	"MAIN_STAT.BAKI_SEBELUM_BULAN_S17 AS BAKI_BULAN_LEPAS_S17, ";
			   	sql +=	"MAIN_STAT.BY_BULAN_S8 AS TERIMA_BULAN_SEMASA_S8, ";
		    	sql +=	"MAIN_STAT.BY_BULAN_S17 AS TERIMA_BULAN_SEMASA_S17, ";
		    	sql +=	"(MAIN_STAT.BAKI_SEBELUM_BULAN_S8 + MAIN_STAT.BY_BULAN_S8) AS JUMLAH_DALAM_TANGAN_S8, ";
		    	sql +=	"(MAIN_STAT.BAKI_SEBELUM_BULAN_S17 + MAIN_STAT.BY_BULAN_S17) AS JUMLAH_DALAM_TANGAN_S17, ";
			   	sql +=	"MAIN_STAT.BICARA_BULAN_S8 AS PANGGIL_BICARA_DALAM_BULAN_S8, ";
			   	sql +=	"MAIN_STAT.BICARA_BULAN_S17 AS PANGGIL_BICARA_DALAM_BULAN_S17, ";
			   	sql +=	"MAIN_STAT.SELESAI_BULAN_S8 AS SELESAI_BULAN_SEMASA_S8, ";
			   	sql +=	"MAIN_STAT.SELESAI_BULAN_S17 AS SELESAI_BULAN_SEMASA_S17, ";
			   	sql +=	"MAIN_STAT.BATAL_BULAN_S8 AS BATAL_BULAN_SEMASA_S8, ";
			   	sql +=	"MAIN_STAT.BATAL_BULAN_S17 AS BATAL_BULAN_SEMASA_S17, ";
			   	sql +=	"((MAIN_STAT.BAKI_SEBELUM_BULAN_S8 + MAIN_STAT.BY_BULAN_S8)-(MAIN_STAT.SELESAI_BULAN_S8 + MAIN_STAT.BATAL_BULAN_S8)) AS BAKI_AKHIR_S8, ";
			   	sql +=	"((MAIN_STAT.BAKI_SEBELUM_BULAN_S17 + MAIN_STAT.BY_BULAN_S17)-(MAIN_STAT.SELESAI_BULAN_S17 + MAIN_STAT.BATAL_BULAN_S17)) AS BAKI_AKHIR_S17 ";
			   	sql +=" ,PU.ID_PEJABATJKPTG,PU.ID_SEKSYEN,PU.ID_JENISPEJABAT " +
			    			"FROM ";
		    	sql +="(SELECT PU.ID_PEJABATJKPTG, ";
		    	sql +="COUNT ";
		    	sql +="(DISTINCT CASE ";
		    	sql +=		"WHEN H.ID_SUBURUSAN IN (59) ";
			  	if(laporan.equals("bulan"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	else if(laporan.equals("tahun"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') <= TO_DATE('"+tahunDari+"', 'YYYY') ";
			  	if(laporan.equals("selang"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			   	
			  	sql +="THEN G.ID_PERMOHONAN ";
			   	sql +="END ";
		    	sql +=") AS TOTAL_FAIL_S8, ";
		    	sql +="COUNT ";
		    	sql +="(DISTINCT CASE ";
			   	sql +="WHEN H.ID_SUBURUSAN IN (59) ";
			  	if(laporan.equals("bulan"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	else if(laporan.equals("tahun"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') < TO_DATE('"+tahunDari+"', 'YYYY') ";
			  	if(laporan.equals("selang"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			   	//sql +="AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			   	
			  	sql +="AND K.FLAG_JENIS_KEPUTUSAN IN ('3') THEN G.ID_PERMOHONAN ";
			   	sql +="WHEN H.ID_SUBURUSAN IN (59) ";
			  	if(laporan.equals("bulan"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	else if(laporan.equals("tahun"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') < TO_DATE('"+tahunDari+"', 'YYYY') ";
			  	if(laporan.equals("selang"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	
			  	//sql +="AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			   	sql +="AND K.ID_PERINTAH IS NULL ";
			   	sql +="THEN G.ID_PERMOHONAN ";
		    	sql +="END ";
		    	sql +=") AS BAKI_SEBELUM_BULAN_S8, ";
		    	sql +="COUNT ";
			   	sql +="	(DISTINCT CASE ";
			   	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
			  	if(laporan.equals("bulan"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	else if(laporan.equals("tahun"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') < TO_DATE('"+tahunDari+"', 'YYYY') ";
			  	if(laporan.equals("selang"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	
			  	//sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			   	sql +="	AND K.FLAG_JENIS_KEPUTUSAN IN ('3') THEN G.ID_PERMOHONAN ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
			  	if(laporan.equals("bulan"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	else if(laporan.equals("tahun"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') < TO_DATE('"+tahunDari+"', 'YYYY') ";
			  	if(laporan.equals("selang"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	
			  	//sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
		    	sql +="	AND K.ID_PERINTAH IS NULL ";
			    sql +="	THEN G.ID_PERMOHONAN ";
			   	sql +=" END ";
			    sql +="	) AS BAKI_SEBELUM_BULAN_S17, ";
			    sql +="	COUNT ";
			    sql +=" (DISTINCT CASE ";
			    sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
			  	if(laporan.equals("bulan"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	else if(laporan.equals("tahun"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') = TO_DATE('"+tahunDari+"', 'YYYY') ";
			  	if(laporan.equals("selang"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	
			  	//sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			    	sql +="	THEN G.ID_PERMOHONAN ";
			    	sql +="	END ";
			    	sql +="	) AS BY_BULAN_S8, ";
			    	sql +="	COUNT ";
			    	sql +="	(DISTINCT CASE ";
			    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
				  	if(laporan.equals("bulan"))
				  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
				  	else if(laporan.equals("tahun"))
				  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') = TO_DATE('"+tahunDari+"', 'YYYY') ";
				  	if(laporan.equals("selang"))
				  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
				  	
				  	//sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			    	sql +="	THEN G.ID_PERMOHONAN ";
			    	sql +="	END ";
			    	sql +="	) AS BY_BULAN_S17, ";
			    	sql +="	COUNT ";
			    	sql +="	(DISTINCT CASE ";
			    	sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
				  	if(laporan.equals("bulan"))
				  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
				  	else if(laporan.equals("tahun"))
				  		sql +=	"AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'YYYY'), 'YYYY') = TO_DATE('"+tahunDari+"', 'YYYY') ";
				  	if(laporan.equals("selang"))
				  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
				  	
				  	//sql +="	AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			    	sql +="	THEN G.ID_PERMOHONAN ";
			    	sql +="	END ";
			    	sql +="	) AS BICARA_BULAN_S8, ";
			    	sql +="	COUNT ";
			    	sql +="	(DISTINCT CASE ";
			    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
				  	if(laporan.equals("bulan"))
				  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
				  	else if(laporan.equals("tahun"))
				  		sql +=	"AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'YYYY'), 'YYYY') = TO_DATE('"+tahunDari+"', 'YYYY') ";
				  	if(laporan.equals("selang"))
				  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
				  	
				  	//sql +="	AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			    	sql +="	THEN G.ID_PERMOHONAN ";
			    	sql +="	END ";
			    	sql +="	) AS BICARA_BULAN_S17, ";
			    	sql +="	COUNT ";
			    	sql +="	(DISTINCT CASE ";
			    	sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
				  	if(laporan.equals("bulan"))
				  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
				  	else if(laporan.equals("tahun"))
				  		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'YYYY'), 'YYYY') = TO_DATE('"+tahunDari+"', 'YYYY') ";
				  	if(laporan.equals("selang"))
				  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
				  	
				  	//sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '0' ";
			    	sql +="	THEN G.ID_PERMOHONAN ";
			    	sql +="	END ";
			    	sql +="	) AS SELESAI_BULAN_S8, ";
			    	sql +="	COUNT ";
			    	sql +="	(DISTINCT CASE ";
			    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
				  	if(laporan.equals("bulan"))
				  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
				  	else if(laporan.equals("tahun"))
				  		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'YYYY'), 'YYYY') = TO_DATE('"+tahunDari+"', 'YYYY') ";
				  	if(laporan.equals("selang"))
				  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
				  	
				  	//sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '0' ";
			    	sql +="	THEN G.ID_PERMOHONAN ";
			    	sql +="	END ";
			    	sql +="	) AS SELESAI_BULAN_S17, ";
			    	sql +="	COUNT ";
			    	sql +="	(DISTINCT CASE ";
			    	sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
				  	if(laporan.equals("bulan"))
				  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
				  	else if(laporan.equals("tahun"))
				  		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'YYYY'), 'YYYY') = TO_DATE('"+tahunDari+"', 'YYYY') ";
				  	if(laporan.equals("selang"))
				  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
				  	
				  	//sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '2' ";
			    	sql +="	THEN G.ID_PERMOHONAN ";
			    	sql +="	END ";
			    	sql +="	) AS BATAL_BULAN_S8, ";
			    	sql +="	COUNT ";
			    	sql +="	(DISTINCT CASE ";
			    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
				  	if(laporan.equals("bulan"))
				  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
				  	else if(laporan.equals("tahun"))
				  		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'YYYY'), 'YYYY') = TO_DATE('"+tahunDari+"', 'YYYY') ";
				  	if(laporan.equals("selang"))
				  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
				  	
				  	//sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
			    	sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '2' ";
			    	sql +="	THEN G.ID_PERMOHONAN ";
			    	sql +="	END ";
			    	sql +="	) AS BATAL_BULAN_S17 ";
			    	sql +="	FROM TBLPPKPERMOHONAN G, ";
			    	sql +="	TBLPFDFAIL H, ";
			    	sql +="	TBLPPKPERBICARAAN I, ";
			    	sql +="	TBLPPKKEPUTUSANPERMOHONAN J, ";
			    	sql +="	TBLPPKPERINTAH K,TBLRUJPEJABATURUSAN PU ";
			    	sql +="	WHERE G.ID_FAIL = H.ID_FAIL ";
			    	sql +="	AND H.ID_SUBURUSAN IN (59, 60) ";
			    	sql +="	AND H.ID_SEKSYEN = 2 ";
			    	sql +="	AND G.ID_PERMOHONAN = J.ID_PERMOHONAN(+) ";
			    	sql +="	AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN(+) ";
			    	sql +="	AND I.ID_PERBICARAAN = K.ID_PERBICARAAN(+) ";
			    	sql +="	AND H.NO_FAIL IS NOT NULL ";
			    	sql +="	AND G.ID_STATUS NOT IN (999, 70, 152) ";
			    	sql +="	AND PU.ID_DAERAHURUS = G.ID_DAERAHMHN ";
			    	sql +="	GROUP BY PU.ID_PEJABATJKPTG) MAIN_STAT, ";
			    	//sql +="	TBLRUJDAERAH DD, ";
			    	sql +="	TBLRUJPEJABATURUSAN PU, ";
			    	sql +="	TBLRUJPEJABATJKPTG PJ ";
			    	sql +="	WHERE MAIN_STAT.ID_PEJABATJKPTG = PJ.ID_PEJABATJKPTG ";
			    	//sql +="	AND PU.ID_DAERAHURUS = DD.ID_DAERAH ";
			    	sql +="	AND PU.ID_PEJABATJKPTG = PJ.ID_PEJABATJKPTG ";
			    	sql +="	AND PU.ID_JENISPEJABAT = 22 ";
			    	sql +="	AND PU.ID_SEKSYEN = 2 ";
			    	//sql +="	AND DD.ID_DAERAH = 121 ";
			    	//sql +="	AND PJ.ID_PEJABATJKPTG = "+ID_UNIT;
			    	if(!idNegeri.equals("0"))
			    		sql += " AND PU.ID_NEGERI = "+idNegeri;
				    	
			    	myLogger.info("getDataPrestasiNegeriBulan:sql= " + sql);
			    	ResultSet rs = stmt.executeQuery(sql);
			    	Hashtable h;
			    	//con.commit();
			    	while (rs.next()) {
						h = new Hashtable();
						if (rs.getString("NAMA") != null && rs.getString("NAMA") != "") {
							h.put("NAMA", rs.getString("NAMA"));
						}
						if (rs.getString("TOTAL_FAIL") != null && rs.getString("TOTAL_FAIL") != "") {
							h.put("TOTAL_FAIL", rs.getInt("TOTAL_FAIL"));
						}
						if (rs.getString("BAKI_BULAN_LEPAS_S8") != null && rs.getString("BAKI_BULAN_LEPAS_S8") != "") {
							h.put("BAKI_BULAN_LEPAS_S8", rs.getInt("BAKI_BULAN_LEPAS_S8"));
						}
						if (rs.getString("BAKI_BULAN_LEPAS_S8") != null && rs.getString("BAKI_BULAN_LEPAS_S8") != "") {
							h.put("BAKI_BULAN_LEPAS_S8", rs.getInt("BAKI_BULAN_LEPAS_S8"));
						}
						if (rs.getString("BAKI_AKHIR_S8") != null && rs.getString("BAKI_AKHIR_S8") != "") {
							h.put("BAKI_AKHIR_S8", rs.getInt("BAKI_AKHIR_S8"));
						}
						if (rs.getString("BAKI_AKHIR_S17") != null && rs.getString("BAKI_AKHIR_S17") != "") {
							h.put("BAKI_AKHIR_S17", rs.getInt("BAKI_AKHIR_S17"));
						}
						//if (rs.getString("BAKI_BULAN_SEMASA") != null && rs.getString("BAKI_BULAN_SEMASA") != "") {
						//	h.put("BAKI_BULAN_SEMASA", rs.getInt("BAKI_BULAN_SEMASA"));
						//}
						if (rs.getString("BAKI_BULAN_LEPAS_S17") != null && rs.getString("BAKI_BULAN_LEPAS_S17") != "") {
							h.put("BAKI_BULAN_LEPAS_S17", rs.getInt("BAKI_BULAN_LEPAS_S17"));
						}
						if (rs.getString("TERIMA_BULAN_SEMASA_S8") != null && rs.getString("TERIMA_BULAN_SEMASA_S8") != "") {
							h.put("TERIMA_BULAN_SEMASA_S8", rs.getInt("TERIMA_BULAN_SEMASA_S8"));
						}
						if (rs.getString("TERIMA_BULAN_SEMASA_S17") != null && rs.getString("TERIMA_BULAN_SEMASA_S17") != "") {
							h.put("TERIMA_BULAN_SEMASA_S17", rs.getInt("TERIMA_BULAN_SEMASA_S17"));
						}
						if (rs.getString("PANGGIL_BICARA_DALAM_BULAN_S8") != null && rs.getString("PANGGIL_BICARA_DALAM_BULAN_S8") != "") {
							h.put("PANGGIL_BICARA_DALAM_BULAN_S8", rs.getInt("PANGGIL_BICARA_DALAM_BULAN_S8"));
						}
						if (rs.getString("PANGGIL_BICARA_DALAM_BULAN_S17") != null && rs.getString("PANGGIL_BICARA_DALAM_BULAN_S17") != "") {
							h.put("PANGGIL_BICARA_DALAM_BULAN_S17", rs.getInt("PANGGIL_BICARA_DALAM_BULAN_S17"));
						}
						if (rs.getString("SELESAI_BULAN_SEMASA_S8") != null && rs.getString("SELESAI_BULAN_SEMASA_S8") != "") {
							h.put("SELESAI_BULAN_SEMASA_S8", rs.getInt("SELESAI_BULAN_SEMASA_S8"));
						}
						if (rs.getString("SELESAI_BULAN_SEMASA_S17") != null && rs.getString("SELESAI_BULAN_SEMASA_S17") != "") {
							h.put("SELESAI_BULAN_SEMASA_S17", rs.getInt("SELESAI_BULAN_SEMASA_S17"));
						}
						if (rs.getString("BATAL_BULAN_SEMASA_S8") != null && rs.getString("BATAL_BULAN_SEMASA_S8") != "") {
							h.put("BATAL_BULAN_SEMASA_S8", rs.getInt("BATAL_BULAN_SEMASA_S8"));
						}
						if (rs.getString("BATAL_BULAN_SEMASA_S17") != null && rs.getString("BATAL_BULAN_SEMASA_S17") != "") {
							h.put("BATAL_BULAN_SEMASA_S17", rs.getInt("BATAL_BULAN_SEMASA_S17"));
						}
						if (rs.getString("ID_RUJUKAN") != null && rs.getString("ID_RUJUKAN") != "") {
							h.put("ID_RUJUKAN", rs.getInt("ID_RUJUKAN"));
						}
						
						SenaraiPrestasi.addElement(h);
						
						}
			   
			    	}
			    
			  finally {
				  if (db != null) db.close();
			  }
			   return SenaraiPrestasi;
			    
		}
	
	//public Vector<Hashtable<String,String>> getDataPrestasiBulan(String BULAN_DARI, String TAHUN_DARI) throws Exception {
	public Vector<Hashtable<String,String>> getDataPrestasiMengikutNegeri(String laporan
		,int bulanDari,int bulanTamat,String tahunDari,String tahunTamat) throws Exception {
		SenaraiPrestasi.clear();
		Db db = null;
		String sql = "";
		List senaraiFail = null;
		try {
			db = new Db();
			Connection con = db.getConnection();
			Statement stmt = db.getStatement();
			sql = "SELECT distinct RN.ID_NEGERI ID_RUJUKAN,RN.NAMA_NEGERI NAMA,RN.KOD_MAMPU ";
			sql += ",MAIN_STAT.TOTAL_FAIL_S8 AS TOTAL_FAIL, ";
		   	sql +=	"MAIN_STAT.BAKI_SEBELUM_BULAN_S8 AS BAKI_BULAN_LEPAS_S8, ";
		   	sql +=	"MAIN_STAT.BAKI_SEBELUM_BULAN_S17 AS BAKI_BULAN_LEPAS_S17, ";
		   	sql +=	"MAIN_STAT.BY_BULAN_S8 AS TERIMA_BULAN_SEMASA_S8, ";
		    sql +=	"MAIN_STAT.BY_BULAN_S17 AS TERIMA_BULAN_SEMASA_S17, ";
		   	sql +=	"(MAIN_STAT.BAKI_SEBELUM_BULAN_S8 + MAIN_STAT.BY_BULAN_S8) AS JUMLAH_DALAM_TANGAN_S8, ";
		   	sql +=	"(MAIN_STAT.BAKI_SEBELUM_BULAN_S17 + MAIN_STAT.BY_BULAN_S17) AS JUMLAH_DALAM_TANGAN_S17, ";
		   	sql +=	"MAIN_STAT.BICARA_BULAN_S8 AS PANGGIL_BICARA_DALAM_BULAN_S8, ";
		   	sql +=	"MAIN_STAT.BICARA_BULAN_S17 AS PANGGIL_BICARA_DALAM_BULAN_S17, ";
		   	sql +=	"MAIN_STAT.SELESAI_BULAN_S8 AS SELESAI_BULAN_SEMASA_S8, ";
		   	sql +=	"MAIN_STAT.SELESAI_BULAN_S17 AS SELESAI_BULAN_SEMASA_S17, ";
		   	sql +=	"MAIN_STAT.BATAL_BULAN_S8 AS BATAL_BULAN_SEMASA_S8, ";
		   	sql +=	"MAIN_STAT.BATAL_BULAN_S17 AS BATAL_BULAN_SEMASA_S17, ";
		   	sql +=	"((MAIN_STAT.BAKI_SEBELUM_BULAN_S8 + MAIN_STAT.BY_BULAN_S8)-(MAIN_STAT.SELESAI_BULAN_S8 + MAIN_STAT.BATAL_BULAN_S8)) AS BAKI_AKHIR_S8, ";
		   	sql +=	"((MAIN_STAT.BAKI_SEBELUM_BULAN_S17 + MAIN_STAT.BY_BULAN_S17)-(MAIN_STAT.SELESAI_BULAN_S17 + MAIN_STAT.BATAL_BULAN_S17)) AS BAKI_AKHIR_S17 ";
		   	//sql +=" ,PU.ID_PEJABATJKPTG,PU.ID_SEKSYEN,PU.ID_JENISPEJABAT " +
		   	sql += " FROM ";
		    sql +="		(SELECT G.ID_NEGERIMHN, ";
		    sql +="		COUNT ";
		    sql +="		(DISTINCT CASE ";
		    sql +="			WHEN H.ID_SUBURUSAN IN (59) ";
		  	if(laporan.equals("bulan"))
		  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
		  	else if(laporan.equals("tahun"))
		  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') <= TO_DATE('"+tahunDari+"', 'YYYY') ";
		  	if(laporan.equals("selang"))
		  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";

		    //sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    sql +="THEN G.ID_PERMOHONAN ";
		   	sql +="END ";
	    	sql +=") AS TOTAL_FAIL_S8, ";
	    	sql +="COUNT ";
	    	sql +="(DISTINCT CASE ";
			sql +="WHEN H.ID_SUBURUSAN IN (59) ";
		  	if(laporan.equals("bulan"))
		  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
		  	else if(laporan.equals("tahun"))
		  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') < TO_DATE('"+tahunDari+"', 'YYYY') ";
		  	if(laporan.equals("selang"))
		  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";

		  	//sql +="AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
			sql +="AND K.FLAG_JENIS_KEPUTUSAN IN ('3') THEN G.ID_PERMOHONAN ";
			sql +="WHEN H.ID_SUBURUSAN IN (59) ";
		  	if(laporan.equals("bulan"))
		  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
		  	else if(laporan.equals("tahun"))
		  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') < TO_DATE('"+tahunDari+"', 'YYYY') ";
		  	if(laporan.equals("selang"))
		  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
		  	
		  	//sql +="AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
			sql +="AND K.ID_PERINTAH IS NULL ";
			sql +="THEN G.ID_PERMOHONAN ";
			sql +="END ";
			sql +=") AS BAKI_SEBELUM_BULAN_S8, ";
			sql +="COUNT ";
			   	sql +="	(DISTINCT CASE ";
			   	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
			  	if(laporan.equals("bulan"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	else if(laporan.equals("tahun"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') <= TO_DATE('"+tahunDari+"', 'YYYY') ";
			  	if(laporan.equals("selang"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";

			   	//sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
			   	sql +="	AND K.FLAG_JENIS_KEPUTUSAN IN ('3') THEN G.ID_PERMOHONAN ";
		    	sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
			  	if(laporan.equals("bulan"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	else if(laporan.equals("tahun"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') <= TO_DATE('"+tahunDari+"', 'YYYY') ";
			  	if(laporan.equals("selang"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	
			  	//sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') < TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
		    	sql +="	AND K.ID_PERINTAH IS NULL ";
		    	sql +="	THEN G.ID_PERMOHONAN ";
		    	sql +=" END ";
			    sql +="	) AS BAKI_SEBELUM_BULAN_S17, ";
			    sql +="	COUNT ";
			    sql +=" (DISTINCT CASE ";
			    sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
			  	if(laporan.equals("bulan"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	else if(laporan.equals("tahun"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') <= TO_DATE('"+tahunDari+"', 'YYYY') ";
			  	if(laporan.equals("selang"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";

			  	//sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
			    sql +="	THEN G.ID_PERMOHONAN ";
			    sql +="	END ";
			    sql +="	) AS BY_BULAN_S8, ";
			    sql +="	COUNT ";
			    sql +="	(DISTINCT CASE ";
			    sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
			  	if(laporan.equals("bulan"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	else if(laporan.equals("tahun"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'YYYY'), 'YYYY') <= TO_DATE('"+tahunDari+"', 'YYYY') ";
			  	if(laporan.equals("selang"))
			  		sql +=	"AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') <= TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	
			  	//sql +="	AND TO_DATE(TO_CHAR (G.TARIKH_MOHON, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
			    sql +="	THEN G.ID_PERMOHONAN ";
			    sql +="	END ";
			    sql +="	) AS BY_BULAN_S17, ";
			    sql +="	COUNT ";
			    sql +="	(DISTINCT CASE ";
			    sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
			  	if(laporan.equals("bulan"))
			  		sql +=	"AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	else if(laporan.equals("tahun"))
			  		sql +=	"AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'YYYY'), 'YYYY') = TO_DATE('"+tahunDari+"', 'YYYY') ";
			  	if(laporan.equals("selang"))
			  		sql +=	"AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";

			  	//sql +="	AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
			    sql +="	THEN G.ID_PERMOHONAN ";
			    sql +="	END ";
			    sql +="	) AS BICARA_BULAN_S8, ";
			    sql +="	COUNT ";
			    sql +="	(DISTINCT CASE ";
			    sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
			  	if(laporan.equals("bulan"))
			  		sql +=	"AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	else if(laporan.equals("tahun"))
			  		sql +=	"AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'YYYY'), 'YYYY') = TO_DATE('"+tahunDari+"', 'YYYY') ";
			  	if(laporan.equals("selang"))
			  		sql +=	"AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	
			  	//sql +="	AND TO_DATE(TO_CHAR (I.TARIKH_BICARA, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
			    sql +="	THEN G.ID_PERMOHONAN ";
			    sql +="	END ";
			    sql +="	) AS BICARA_BULAN_S17, ";
			    sql +="	COUNT ";
			    sql +="	(DISTINCT CASE ";
			    sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
			  	if(laporan.equals("bulan"))
			  		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	else if(laporan.equals("tahun"))
			  		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'YYYY'), 'YYYY') = TO_DATE('"+tahunDari+"', 'YYYY') ";
			  	if(laporan.equals("selang"))
			  		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	
			  	//sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
			    sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '0' ";
			    sql +="	THEN G.ID_PERMOHONAN ";
			    sql +="	END ";
			    sql +="	) AS SELESAI_BULAN_S8, ";
			    sql +="	COUNT ";
			    sql +="	(DISTINCT CASE ";
			    sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
			  	if(laporan.equals("bulan"))
			  		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	else if(laporan.equals("tahun"))
			  		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'YYYY'), 'YYYY') = TO_DATE('"+tahunDari+"', 'YYYY') ";
			  	if(laporan.equals("selang"))
			  		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";

			  	//sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
			    sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '0' ";
			    sql +="	THEN G.ID_PERMOHONAN ";
			    sql +="	END ";
			    sql +="	) AS SELESAI_BULAN_S17, ";
			    sql +="	COUNT ";
			    sql +="	(DISTINCT CASE ";
			    sql +="	WHEN H.ID_SUBURUSAN IN (59) ";
			  	if(laporan.equals("bulan"))
			  		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	else if(laporan.equals("tahun"))
			  		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'YYYY'), 'YYYY') = TO_DATE('"+tahunDari+"', 'YYYY') ";
			  	if(laporan.equals("selang"))
			  		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";

			  	//sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
			    sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '2' ";
			    sql +="	THEN G.ID_PERMOHONAN ";
			    sql +="	END ";
			    sql +="	) AS BATAL_BULAN_S8, ";
			    sql +="	COUNT ";
			    sql +="	(DISTINCT CASE ";
			    sql +="	WHEN H.ID_SUBURUSAN IN (60) ";
			  	if(laporan.equals("bulan"))
			  		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";
			  	else if(laporan.equals("tahun"))
			  		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'YYYY'), 'YYYY') = TO_DATE('"+tahunDari+"', 'YYYY') ";
			  	if(laporan.equals("selang"))
			  		sql +=	"AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+bulanDari+"/"+tahunDari+"', 'MM/YYYY') ";

			  	//sql +="	AND TO_DATE(TO_CHAR (K.TARIKH_PERINTAH, 'MM/YYYY'), 'MM/YYYY') = TO_DATE('"+BULAN_DARI+"/"+TAHUN_DARI+"', 'MM/YYYY') ";
			    sql +="	AND K.FLAG_JENIS_KEPUTUSAN = '2' ";
			    sql +="	THEN G.ID_PERMOHONAN ";
			    sql +="	END ";
			    sql +="	) AS BATAL_BULAN_S17 ";
			    sql +="	FROM TBLPPKPERMOHONAN G, ";
			    sql +="	TBLPFDFAIL H, ";
			    sql +="	TBLPPKPERBICARAAN I, ";
			    sql +="	TBLPPKKEPUTUSANPERMOHONAN J, ";
			    sql +="	TBLPPKPERINTAH K,TBLRUJNEGERI RN";
			    sql +="	WHERE G.ID_FAIL = H.ID_FAIL ";
			    sql +="	AND H.ID_SUBURUSAN IN (59, 60) ";
			    sql +="	AND H.ID_SEKSYEN = 2 ";
			    sql +="	AND G.ID_PERMOHONAN = J.ID_PERMOHONAN(+) ";
			    sql +="	AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN(+) ";
			    sql +="	AND I.ID_PERBICARAAN = K.ID_PERBICARAAN(+) ";
			    sql +="	AND H.NO_FAIL IS NOT NULL ";
			    sql +="	AND G.ID_STATUS NOT IN (999, 70, 152) ";
			    sql +="	AND G.ID_NEGERIMHN = RN.ID_NEGERI ";          
			    //sql +="	AND PU.ID_DAERAHURUS = G.ID_DAERAHMHN ";
			    sql +="	GROUP BY G.ID_NEGERIMHN) MAIN_STAT ";
			    sql +="	,TBLRUJNEGERI RN ";
			    sql +="	WHERE ";
			    sql +="	MAIN_STAT.ID_NEGERIMHN = RN.ID_NEGERI ";
			    sql +="	AND RN.ID_NEGERI NOT IN (0,17) ";
			    //sql +="	WHERE MAIN_STAT.ID_PEJABATJKPTG = PJ.ID_PEJABATJKPTG ";
			    sql +="	ORDER BY RN.KOD_MAMPU ";
			    	
			    	System.out.println("setDataPrestasibyBulan:sql= " + sql);
			    	ResultSet rs = stmt.executeQuery(sql);
			    	Hashtable h;
			    	//con.commit();
			    	while (rs.next()) {
						h = new Hashtable();
						if (rs.getString("NAMA") != null && rs.getString("NAMA") != "") {
							h.put("NAMA", rs.getString("NAMA"));
						}
						if (rs.getString("TOTAL_FAIL") != null && rs.getString("TOTAL_FAIL") != "") {
							h.put("TOTAL_FAIL", rs.getInt("TOTAL_FAIL"));
						}
						if (rs.getString("BAKI_BULAN_LEPAS_S8") != null && rs.getString("BAKI_BULAN_LEPAS_S8") != "") {
							h.put("BAKI_BULAN_LEPAS_S8", rs.getInt("BAKI_BULAN_LEPAS_S8"));
						}
						if (rs.getString("BAKI_BULAN_LEPAS_S8") != null && rs.getString("BAKI_BULAN_LEPAS_S8") != "") {
							h.put("BAKI_BULAN_LEPAS_S8", rs.getInt("BAKI_BULAN_LEPAS_S8"));
						}
						if (rs.getString("BAKI_AKHIR_S8") != null && rs.getString("BAKI_AKHIR_S8") != "") {
							h.put("BAKI_AKHIR_S8", rs.getInt("BAKI_AKHIR_S8"));
						}
						if (rs.getString("BAKI_AKHIR_S17") != null && rs.getString("BAKI_AKHIR_S17") != "") {
							h.put("BAKI_AKHIR_S17", rs.getInt("BAKI_AKHIR_S17"));
						}
						//if (rs.getString("BAKI_BULAN_SEMASA") != null && rs.getString("BAKI_BULAN_SEMASA") != "") {
						//	h.put("BAKI_BULAN_SEMASA", rs.getInt("BAKI_BULAN_SEMASA"));
						//}
						if (rs.getString("BAKI_BULAN_LEPAS_S17") != null && rs.getString("BAKI_BULAN_LEPAS_S17") != "") {
							h.put("BAKI_BULAN_LEPAS_S17", rs.getInt("BAKI_BULAN_LEPAS_S17"));
						}
						if (rs.getString("TERIMA_BULAN_SEMASA_S8") != null && rs.getString("TERIMA_BULAN_SEMASA_S8") != "") {
							h.put("TERIMA_BULAN_SEMASA_S8", rs.getInt("TERIMA_BULAN_SEMASA_S8"));
						}
						if (rs.getString("TERIMA_BULAN_SEMASA_S17") != null && rs.getString("TERIMA_BULAN_SEMASA_S17") != "") {
							h.put("TERIMA_BULAN_SEMASA_S17", rs.getInt("TERIMA_BULAN_SEMASA_S17"));
						}
						if (rs.getString("PANGGIL_BICARA_DALAM_BULAN_S8") != null && rs.getString("PANGGIL_BICARA_DALAM_BULAN_S8") != "") {
							h.put("PANGGIL_BICARA_DALAM_BULAN_S8", rs.getInt("PANGGIL_BICARA_DALAM_BULAN_S8"));
						}
						if (rs.getString("PANGGIL_BICARA_DALAM_BULAN_S17") != null && rs.getString("PANGGIL_BICARA_DALAM_BULAN_S17") != "") {
							h.put("PANGGIL_BICARA_DALAM_BULAN_S17", rs.getInt("PANGGIL_BICARA_DALAM_BULAN_S17"));
						}
						if (rs.getString("SELESAI_BULAN_SEMASA_S8") != null && rs.getString("SELESAI_BULAN_SEMASA_S8") != "") {
							h.put("SELESAI_BULAN_SEMASA_S8", rs.getInt("SELESAI_BULAN_SEMASA_S8"));
						}
						if (rs.getString("SELESAI_BULAN_SEMASA_S17") != null && rs.getString("SELESAI_BULAN_SEMASA_S17") != "") {
							h.put("SELESAI_BULAN_SEMASA_S17", rs.getInt("SELESAI_BULAN_SEMASA_S17"));
						}
						if (rs.getString("BATAL_BULAN_SEMASA_S8") != null && rs.getString("BATAL_BULAN_SEMASA_S8") != "") {
							h.put("BATAL_BULAN_SEMASA_S8", rs.getInt("BATAL_BULAN_SEMASA_S8"));
						}
						if (rs.getString("BATAL_BULAN_SEMASA_S17") != null && rs.getString("BATAL_BULAN_SEMASA_S17") != "") {
							h.put("BATAL_BULAN_SEMASA_S17", rs.getInt("BATAL_BULAN_SEMASA_S17"));
						}
						if (rs.getString("ID_RUJUKAN") != null && rs.getString("ID_RUJUKAN") != "") {
							h.put("ID_RUJUKAN", rs.getInt("ID_RUJUKAN"));
						}
						
						SenaraiPrestasi.addElement(h);
						
						}
			   
			    	}
			    
			  finally {
				  if (db != null) db.close();
			  }
			   return SenaraiPrestasi;
			    
		}

	public static void setDataPrestasibyUnitTahun(String TAHUN, String ID_NEGERI, String ID_UNIT) throws Exception {
		//SenaraiSerahan = null;
		SenaraiPrestasi.clear();
		Db db = null;
		String sql = "";
		List senaraiFail = null;
		int tahun_sebelum = Integer.parseInt(TAHUN)-1;
		//String tarikh_mula = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
		//String tarikh_akhir = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun;
		//System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sql = "SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,C.ID_PEJABATJKPTG, UPPER(C.NAMA_PEJABAT) AS NAMA_PEJABAT,UPPER(C.ALAMAT1) AS ALAMAT1, UPPER(F.NAMA_NEGERI) AS NAMA_NEGERI, ";
		    	sql += "UPPER(D.NAMA_DAERAH) AS NAMA_DAERAH, NVL(A1.JUMLAH_FAIL,'0') AS JUMLAH_FAIL, ";
		    	sql += "NVL(A2.PANGGIL_BICARA,'0') AS PANGGIL_BICARA, NVL(A3.PANGGIL_BICARA_S17,'0') AS PANGGIL_BICARA_S17, ";
		    	sql += "NVL(A4.PANGGIL_3X_XHADIR,'0') AS PANGGIL_3X_XHADIR, NVL(A5.SELESAI_BICARA,'0') AS SELESAI_BICARA, ";
		    	sql += "NVL(A6.SELESAI_BICARA_S17,'0') AS SELESAI_BICARA_S17, NVL(A7.SELESAI_BATAL,'0') AS SELESAI_BATAL, ";
		    	sql += "NVL(A8.SELESAI_BATAL_S17,'0') AS SELESAI_BATAL_S17, NVL(A9.BAKI_BULAN_LEPAS,'0') AS BAKI_BULAN_LEPAS, ";
		    	sql += "NVL(A10.BAKI_BULAN_LEPAS_S17,'0') AS BAKI_BULAN_LEPAS_S17, NVL(A11.KES_TERIMA_BULAN,'0') AS KES_TERIMA_BULAN, ";
		    	sql += "NVL(A12.KES_TERIMA_BULAN_S17,'0') AS KES_TERIMA_BULAN_S17 ";

		    	sql += "FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABATJKPTG C, TBLRUJDAERAH D, ";
		    	sql += "TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H,";

		    	sql += "(SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(*) AS JUMLAH_FAIL ";
		    	sql += "FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C, ";
		    	sql += "TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H ";
		    	sql += "WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += "AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += "AND A.ID_JENISPEJABAT <> 3  ";
		    	sql += "AND A.ID_SEKSYEN = 2 ";
		    	sql += "AND B.ID_JENISPEJABAT = 2 ";
		    	sql += "AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += "AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += "AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += "AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += "AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += "AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += "AND H.ID_SUBURUSAN=59 ";
		    	sql += " AND TO_CHAR(G.TARIKH_MOHON,'yyyy') BETWEEN 2010 AND "+TAHUN;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A1, ";

		    	sql += "(SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(DISTINCT(G.ID_FAIL)) AS PANGGIL_BICARA ";
		    	sql += "FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += "TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H,  ";
		    	sql += "TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J, TBLPPKPERINTAH K ";
		    	sql += "WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += "AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += "AND A.ID_JENISPEJABAT <> 3  ";
		    	sql += "AND A.ID_SEKSYEN = 2 ";
		    	sql += "AND B.ID_JENISPEJABAT = 2 ";
		    	sql += "AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += "AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += "AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += "AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN "; 
		    	sql += "AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += "AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += "AND H.ID_SUBURUSAN=59 ";
		    	sql += "AND G.ID_PERMOHONAN  =  J.ID_PERMOHONAN ";
		    	sql += "AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN ";
		    	sql += "AND I.ID_PERBICARAAN = K.ID_PERBICARAAN ";
		    	if(!ID_NEGERI.equals("0"))
		    		sql += "AND A.ID_NEGERIURUS = "+ID_NEGERI;
		    	
		    	if(!ID_UNIT.equals("0"))
		    		sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	
		    	sql += " AND TO_CHAR(I.TARIKH_BICARA,'yyyy') = "+TAHUN;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A2, ";

		    	sql += "(SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(DISTINCT(G.ID_FAIL)) AS PANGGIL_BICARA_S17 ";
		    	sql += "FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += "TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H,  ";
		    	sql += "TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J, TBLPPKPERINTAH K ";
		    	sql += "WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += "AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += "AND A.ID_JENISPEJABAT <> 3 ";
		    	sql += "AND A.ID_SEKSYEN = 2 ";
		    	sql += "AND B.ID_JENISPEJABAT = 2 ";
		    	sql += "AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += "AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += "AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += "AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += "AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += "AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += "AND H.ID_SUBURUSAN=60 ";
		    	sql += "AND G.ID_PERMOHONAN  =  J.ID_PERMOHONAN ";
		    	sql += "AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN ";
		    	sql += "AND I.ID_PERBICARAAN = K.ID_PERBICARAAN ";
		    	
		    	if(!ID_NEGERI.equals("0"))
		    		sql += "AND A.ID_NEGERIURUS = "+ID_NEGERI;
		    	
		    	if(!ID_UNIT.equals("0"))
		    		sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;

		    	sql += " AND TO_CHAR(I.TARIKH_BICARA,'yyyy') = "+TAHUN;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A3, ";

		    	sql += "(SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(*) AS PANGGIL_3X_XHADIR ";
		    	sql += "FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += "TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H,  ";
		    	sql += "TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J, TBLPPKPERINTAH K ";
		    	sql += "WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += "AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += "AND A.ID_JENISPEJABAT <> 3 ";
		    	sql += "AND A.ID_SEKSYEN = 2 ";
		    	sql += "AND B.ID_JENISPEJABAT = 2 ";
		    	sql += "AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += "AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += "AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += "AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += "AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += "AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += "AND H.ID_SUBURUSAN=59 ";
		    	sql += "AND G.ID_PERMOHONAN  =  J.ID_PERMOHONAN ";
		    	sql += "AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN ";
		    	sql += "AND I.ID_PERBICARAAN = K.ID_PERBICARAAN ";
		    	sql += "AND K.FLAG_BATAL = '2' ";
//		    	sql += "AND A.id_negeriurus = "+ID_NEGERI;
//		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	if(!ID_NEGERI.equals("0"))
		    		sql += "AND A.ID_NEGERIURUS = "+ID_NEGERI;
		    	
		    	if(!ID_UNIT.equals("0"))
		    		sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;

		    	sql += " AND TO_CHAR(K.TARIKH_PERINTAH,'yyyy') = "+TAHUN;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A4, ";

		    	sql += "(SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(*) AS SELESAI_BICARA ";
		    	sql += "FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += "TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H,  ";
		    	sql += "TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J, TBLPPKPERINTAH K ";
		    	sql += "WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += "AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += "AND A.ID_JENISPEJABAT <> 3   ";
		    	sql += "AND A.ID_SEKSYEN = 2 ";
		    	sql += "AND B.ID_JENISPEJABAT = 2   ";
		    	sql += "AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += "AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += "AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += "AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += "AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += "AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += "AND H.ID_SUBURUSAN=59 ";
		    	sql += "AND G.ID_PERMOHONAN  =  J.ID_PERMOHONAN ";
		    	sql += "AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN ";
		    	sql += "AND I.ID_PERBICARAAN = K.ID_PERBICARAAN ";
		    	sql += "AND K.FLAG_JENIS_KEPUTUSAN = '0' ";
//		    	sql += "AND A.id_negeriurus = "+ID_NEGERI;
//		    	sql +=  "AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	if(!ID_NEGERI.equals("0"))
		    		sql += "AND A.ID_NEGERIURUS = "+ID_NEGERI;
		    	
		    	if(!ID_UNIT.equals("0"))
		    		sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;

		    	sql += " AND TO_CHAR(K.TARIKH_PERINTAH,'yyyy') = "+TAHUN;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A5, ";

		    	sql += "(SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(*) AS SELESAI_BICARA_S17 ";
		    	sql += "FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += "TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H,  ";
		    	sql += "TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J, TBLPPKPERINTAH K ";
		    	sql += "WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += "AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += "AND A.ID_JENISPEJABAT <> 3   ";
		    	sql += "AND A.ID_SEKSYEN = 2 ";
		    	sql += "AND B.ID_JENISPEJABAT = 2   ";
		    	sql += "AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += "AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += "AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += "AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += "AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += "AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += "AND H.ID_SUBURUSAN=60 ";
		    	sql += "AND G.ID_PERMOHONAN  =  J.ID_PERMOHONAN ";
		    	sql += "AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN ";
		    	sql += "AND I.ID_PERBICARAAN = K.ID_PERBICARAAN ";
		    	sql += "AND K.FLAG_JENIS_KEPUTUSAN = '0' ";
		    	sql += "AND A.id_negeriurus = "+ID_NEGERI;
		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	sql += " AND TO_CHAR(K.TARIKH_PERINTAH,'yyyy') = "+TAHUN;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A6, ";

		    	sql += 	"(SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(*) AS SELESAI_BATAL ";
		    	sql += "FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += "TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H,  ";
		    	sql += "TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J, TBLPPKPERINTAH K ";
		    	sql += "WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += "AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += "AND A.ID_JENISPEJABAT <> 3   ";
		    	sql += "AND A.ID_SEKSYEN = 2 ";
		    	sql += "AND B.ID_JENISPEJABAT = 2   ";
		    	sql += "AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += "AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += "AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += "AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += "AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += "AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += "AND H.ID_SUBURUSAN=59 ";
		    	sql += "AND G.ID_PERMOHONAN  =  J.ID_PERMOHONAN ";
		    	sql += "AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN ";
		    	sql += "AND I.ID_PERBICARAAN = K.ID_PERBICARAAN ";
		    	sql += "AND K.FLAG_JENIS_KEPUTUSAN = '2' ";
		    	sql += "AND G.ID_STATUS = 47 ";
		    	sql += "AND A.id_negeriurus = "+ID_NEGERI;
		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	sql += " AND TO_CHAR(K.TARIKH_PERINTAH,'yyyy') = "+TAHUN;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A7, ";

		    	sql += "(SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(*) AS SELESAI_BATAL_S17 ";
		    	sql += "FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += "TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H,  ";
		    	sql += "TBLPPKPERBICARAAN I, TBLPPKKEPUTUSANPERMOHONAN J, TBLPPKPERINTAH K ";
		    	sql += "WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += "AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += "AND A.ID_JENISPEJABAT <> 3  ";
		    	sql += "AND A.ID_SEKSYEN = 2 ";
		    	sql += "AND B.ID_JENISPEJABAT = 2  ";
		    	sql += "AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += "AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += "AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += "AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += "AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += "AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += "AND H.ID_SUBURUSAN=60 ";
		    	sql += "AND G.ID_PERMOHONAN  =  J.ID_PERMOHONAN ";
		    	sql += "AND J.ID_KEPUTUSANPERMOHONAN = I.ID_KEPUTUSANPERMOHONAN ";
		    	sql += "AND I.ID_PERBICARAAN = K.ID_PERBICARAAN ";
		    	sql += "AND K.FLAG_JENIS_KEPUTUSAN = '2' ";
		    	sql += "AND G.ID_STATUS = 47 ";
//		    	sql += 	"AND A.id_negeriurus = "+ID_NEGERI;
//		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	if(!ID_NEGERI.equals("0"))
		    		sql += "AND A.ID_NEGERIURUS = "+ID_NEGERI;
		    	
		    	if(!ID_UNIT.equals("0"))
		    		sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;

		    	sql += " AND TO_CHAR(K.TARIKH_PERINTAH,'yyyy') = "+TAHUN;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A8, ";

		    	sql += "(SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(*) AS BAKI_BULAN_LEPAS ";
		    	sql += "FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += "TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H ";
		    	sql += "WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += "AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += "AND A.ID_JENISPEJABAT <> 3 ";
		    	sql += "AND A.ID_SEKSYEN = 2 ";
		    	sql += "AND B.ID_JENISPEJABAT = 2   ";
		    	sql += "AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += "AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += "AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += "AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += "AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += 	"AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += "and g.id_status not in (18,41,999,171,152,70) ";
		    	sql += "AND H.ID_SUBURUSAN=59 ";
		    	sql += "AND A.id_negeriurus = "+ID_NEGERI;
		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	sql += " AND TO_CHAR(G.TARIKH_MOHON, 'YYYY') = "+tahun_sebelum;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A9, ";

		    	sql += "(SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS, COUNT(*) AS BAKI_BULAN_LEPAS_S17 ";
		    	sql += "FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C, ";
		    	sql += "TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H ";
		    	sql += "WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += "AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += "AND A.ID_JENISPEJABAT <> 3 ";
		    	sql += "AND A.ID_SEKSYEN = 2 ";
		    	sql += "AND B.ID_JENISPEJABAT = 2 ";
		    	sql += "AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += "AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += "AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += "AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += "AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += "and g.id_status not in (18,41,999,171,152,70) ";
		    	sql += "AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += "AND H.ID_SUBURUSAN=60 ";
		    	sql += "AND A.id_negeriurus = "+ID_NEGERI;
		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	sql += " AND TO_CHAR(G.TARIKH_MOHON, 'YYYY') = "+tahun_sebelum;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A10, ";

		    	sql += "(SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(*) AS KES_TERIMA_BULAN ";
		    	sql += "FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += "TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H ";
		    	sql += "WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += "AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += "AND A.ID_JENISPEJABAT <> 3   ";
		    	sql += "AND A.ID_SEKSYEN = 2 ";
		    	sql += "AND B.ID_JENISPEJABAT = 2  ";
		    	sql += "AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += "AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += "AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += "AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += "AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += "AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += "AND H.ID_SUBURUSAN=59 ";
//		    	sql += "AND A.id_negeriurus = "+ID_NEGERI;
//		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	if(!ID_NEGERI.equals("0"))
		    		sql += "AND A.ID_NEGERIURUS = "+ID_NEGERI;
		    	
		    	if(!ID_UNIT.equals("0"))
		    		sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;

		    	sql += " AND TO_CHAR(G.TARIKH_MOHON, 'YYYY') = "+TAHUN;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A11, ";

		    	sql += "(SELECT A.ID_NEGERIURUS,A.ID_DAERAHURUS,COUNT(*) AS KES_TERIMA_BULAN_S17 ";
		    	sql += "FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJPEJABATJKPTG C,  ";
		    	sql += "TBLRUJDAERAH D, TBLRUJNEGERI F, TBLPPKPERMOHONAN G, TBLPFDFAIL H ";
		    	sql += "WHERE A.ID_NEGERIURUS = B.ID_NEGERI ";
		    	sql += "AND A.ID_DAERAHURUS  = B.ID_DAERAH ";
		    	sql += "AND A.ID_JENISPEJABAT <> 3   ";
		    	sql += "AND A.ID_SEKSYEN = 2 ";
		    	sql += "AND B.ID_JENISPEJABAT = 2   ";
		    	sql += "AND A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += "AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += "AND A.ID_NEGERI  = F.ID_NEGERI ";
		    	sql += "AND A.ID_NEGERIURUS =  G.ID_NEGERIMHN  ";
		    	sql += "AND A.ID_DAERAHURUS =  G.ID_DAERAHMHN ";
		    	sql += "AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += "AND H.ID_SUBURUSAN=60 ";
//		    	sql += " AND A.id_negeriurus = "+ID_NEGERI;
//		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	if(!ID_NEGERI.equals("0"))
		    		sql += "AND A.ID_NEGERIURUS = "+ID_NEGERI;
		    	
		    	if(!ID_UNIT.equals("0"))
		    		sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;

		    	sql += " AND TO_CHAR(G.TARIKH_MOHON, 'YYYY') = "+TAHUN;
		    	sql += " GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS) A12 ";

		    	sql += "WHERE A.ID_PEJABATJKPTG = C.ID_PEJABATJKPTG ";
		    	sql += "AND A.ID_DAERAHURUS = D.ID_DAERAH ";
		    	sql += "AND A.ID_NEGERIURUS  = F.ID_NEGERI ";
		    	sql += "AND G.ID_FAIL  =  H.ID_FAIL ";
		    	sql += "AND H.ID_SUBURUSAN=59 ";
//		    	sql += " AND A.id_negeriurus = "+ID_NEGERI;
//		    	sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;
		    	if(!ID_NEGERI.equals("0"))
		    		sql += "AND A.ID_NEGERIURUS = "+ID_NEGERI;
		    	
		    	if(!ID_UNIT.equals("0"))
		    		sql += " AND C.ID_PEJABATJKPTG = "+ID_UNIT;

		    	sql += " AND A.ID_NEGERIURUS = A1.ID_NEGERIURUS(+) ";
		    	sql += "AND A.ID_DAERAHURUS = A1.ID_DAERAHURUS(+) ";
		    	sql += "AND A.ID_NEGERIURUS = A2.ID_NEGERIURUS(+) ";
		    	sql += "AND A.ID_DAERAHURUS = A2.ID_DAERAHURUS(+) ";
		    	sql += "AND A.ID_NEGERIURUS = A3.ID_NEGERIURUS(+) ";
		    	sql += "AND A.ID_DAERAHURUS = A3.ID_DAERAHURUS(+) ";
		    	sql += "AND A.ID_NEGERIURUS = A4.ID_NEGERIURUS(+) ";
		    	sql += "AND A.ID_DAERAHURUS = A4.ID_DAERAHURUS(+) ";
		    	sql += "AND A.ID_NEGERIURUS = A5.ID_NEGERIURUS(+) ";
		    	sql += "AND A.ID_DAERAHURUS = A5.ID_DAERAHURUS(+) ";
		    	sql += "AND A.ID_NEGERIURUS = A6.ID_NEGERIURUS(+) ";
		    	sql += "AND A.ID_DAERAHURUS = A6.ID_DAERAHURUS(+) ";
		    	sql += "AND A.ID_NEGERIURUS = A7.ID_NEGERIURUS(+) ";
		    	sql += "AND A.ID_DAERAHURUS = A7.ID_DAERAHURUS(+) ";
		    	sql += "AND A.ID_NEGERIURUS = A8.ID_NEGERIURUS(+) ";
		    	sql += "AND A.ID_DAERAHURUS = A8.ID_DAERAHURUS(+) ";
		    	sql += "AND A.ID_NEGERIURUS = A9.ID_NEGERIURUS(+) ";
		    	sql += "AND A.ID_DAERAHURUS = A9.ID_DAERAHURUS(+) ";
		    	sql += "AND A.ID_NEGERIURUS = A10.ID_NEGERIURUS(+) ";
		    	sql += "AND A.ID_DAERAHURUS = A10.ID_DAERAHURUS(+) ";
		    	sql += "AND A.ID_NEGERIURUS = A11.ID_NEGERIURUS(+) ";
		    	sql += "AND A.ID_DAERAHURUS = A11.ID_DAERAHURUS(+) ";
		    	sql += "AND A.ID_NEGERIURUS = A12.ID_NEGERIURUS(+) ";
		    	sql += "AND A.ID_DAERAHURUS = A12.ID_DAERAHURUS(+) ";
		    	sql += "GROUP BY A.ID_NEGERIURUS,A.ID_DAERAHURUS,C.ID_PEJABATJKPTG, C.NAMA_PEJABAT, C.ALAMAT1, F.NAMA_NEGERI, ";
		    	sql += "D.NAMA_DAERAH, A1.JUMLAH_FAIL, A2.PANGGIL_BICARA, A3.PANGGIL_BICARA_S17, A4.PANGGIL_3X_XHADIR, A5.SELESAI_BICARA, A6.SELESAI_BICARA_S17, A7.SELESAI_BATAL, "; 
		    	sql += "A8.SELESAI_BATAL_S17, A9.BAKI_BULAN_LEPAS, A10.BAKI_BULAN_LEPAS_S17, A11.KES_TERIMA_BULAN, A12.KES_TERIMA_BULAN_S17 ";
		    	sql += "ORDER BY D.NAMA_DAERAH ";
		    			    	
		    	System.out.println("sql = " + sql);
		    	ResultSet rs = stmt.executeQuery(sql);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();

					if (rs.getString("NAMA_DAERAH") != null && rs.getString("NAMA_DAERAH") != "") {
						h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
					}
					if (rs.getString("JUMLAH_FAIL") != null && rs.getString("JUMLAH_FAIL") != "") {
						h.put("JUMLAH_FAIL", rs.getInt("JUMLAH_FAIL"));
					}
					if (rs.getString("BAKI_BULAN_LEPAS") != null && rs.getString("BAKI_BULAN_LEPAS") != "") {
						h.put("BAKI_BULAN_LEPAS", rs.getInt("BAKI_BULAN_LEPAS"));
					}
					if (rs.getString("BAKI_BULAN_LEPAS_S17") != null && rs.getString("BAKI_BULAN_LEPAS_S17") != "") {
						h.put("BAKI_BULAN_LEPAS_S17", rs.getInt("BAKI_BULAN_LEPAS_S17"));
					}
					if (rs.getString("KES_TERIMA_BULAN") != null && rs.getString("KES_TERIMA_BULAN") != "") {
						h.put("KES_TERIMA_BULAN", rs.getInt("KES_TERIMA_BULAN"));
					}
					if (rs.getString("KES_TERIMA_BULAN_S17") != null && rs.getString("KES_TERIMA_BULAN_S17") != "") {
						h.put("KES_TERIMA_BULAN_S17", rs.getInt("KES_TERIMA_BULAN_S17"));
					}
					if (rs.getString("PANGGIL_BICARA") != null && rs.getString("PANGGIL_BICARA") != "") {
						h.put("PANGGIL_BICARA", rs.getInt("PANGGIL_BICARA"));
					}
					if (rs.getString("PANGGIL_BICARA_S17") != null && rs.getString("PANGGIL_BICARA_S17") != "") {
						h.put("PANGGIL_BICARA_S17", rs.getInt("PANGGIL_BICARA_S17"));
					}
					if (rs.getString("SELESAI_BICARA") != null && rs.getString("SELESAI_BICARA") != "") {
						h.put("SELESAI_BICARA", rs.getInt("SELESAI_BICARA"));
					}
					if (rs.getString("SELESAI_BICARA_S17") != null && rs.getString("SELESAI_BICARA_S17") != "") {
						h.put("SELESAI_BICARA_S17", rs.getInt("SELESAI_BICARA_S17"));
					}
					if (rs.getString("SELESAI_BATAL") != null && rs.getString("SELESAI_BATAL") != "") {
						h.put("SELESAI_BATAL", rs.getInt("SELESAI_BATAL"));
					}
					if (rs.getString("SELESAI_BATAL_S17") != null && rs.getString("SELESAI_BATAL_S17") != "") {
						h.put("SELESAI_BATAL_S17", rs.getInt("SELESAI_BATAL_S17"));
					}
					
					SenaraiPrestasi.addElement(h);
					}
		   
		    	}
		    
		    finally {
			      if (db != null) db.close();
		    }
			
		    
			}

	// close default
	
	public static void setDataNamaUnit(String ID_UNIT) throws Exception {
		SenaraiNamaUnit.clear();
		Db db = null;
		String sql = "";
		List senaraiFail = null;

		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	//con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	sql = "SELECT NAMA_PEJABAT FROM TBLRUJPEJABATJKPTG WHERE ID_PEJABATJKPTG="+ID_UNIT;

		    	System.out.println("sql = " + sql);
		    	ResultSet rs = stmt.executeQuery(sql);
		    	Hashtable h;
		    	//con.commit();
		    	while (rs.next()) {
					h = new Hashtable();

					if (rs.getString("NAMA_PEJABAT") != null && rs.getString("NAMA_PEJABAT") != "") {
						h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT").toUpperCase( ));
					}
		    
					SenaraiNamaUnit.addElement(h);
				}
	   
	    	}
	    
	    finally {
		      if (db != null) db.close();
	    }
		
	    
		}
	
	public String getNamaUnitStr(String ID_UNIT) throws Exception {
		Db db = null;
		String sql = "";
		String returnVal = "";
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	Statement stmt = db.getStatement();
		    	sql = "SELECT NAMA_PEJABAT FROM TBLRUJPEJABATJKPTG WHERE ID_PEJABATJKPTG="+ID_UNIT;
		    	ResultSet rs = stmt.executeQuery(sql);
		    	Hashtable h;
		    	while (rs.next()) {
//					h = new Hashtable();
//					if (rs.getString("NAMA_PEJABAT") != null && rs.getString("NAMA_PEJABAT") != "") {
//						h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT").toUpperCase( ));
//					}		    
//					SenaraiNamaUnit.addElement(h);
					returnVal = rs.getString("NAMA_PEJABAT").toUpperCase();
					
				}
	   
	    	}
	    
	    finally {
		      if (db != null) db.close();
	    }
		return returnVal;
		
	}
	
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

	

	


