package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;


public class FrmPenswastaanMaklumatProjekData {
	
	static SimpleDateFormat Formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	static Logger mylogger = Logger.getLogger(FrmPenswastaanMaklumatProjekData.class);
	
	
	public static Vector getmaklumatProjek(String id_permohonan)throws Exception {
		Db db = null;
		String sql = "";

		try {
			Vector maklumatProjek = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

	sql = "SELECT NILAI_TNH,HARGA_SETUJU,HARGA_BERSAMAAN"
			+ " FROM TBLHTPMAKLUMATMSYRT where ID_PERMOHONAN = '"+ id_permohonan + "'";
	
	

	
	mylogger.info(" SQL GET MAKLUMAT PROJEK :" + sql);
	ResultSet rs = stmt.executeQuery(sql);

	Hashtable h = new Hashtable();
	int bil = 1;
	while (rs.next()) {
		h = new Hashtable();
//		h.put("id_permohonan",rs.getString("id_permohonan") == null ? "" : rs.getString("id_permohonan").toUpperCase());
		h.put("txtNilaiTanah", rs.getString("NILAI_TNH") == null ? "" : rs.getString("NILAI_TNH"));
		h.put("txtNilaiProjek", rs.getString("HARGA_SETUJU") == null ? "": rs.getString("HARGA_SETUJU"));
		h.put("txtCaraPelaksanaanFee", rs.getString("HARGA_BERSAMAAN") == null ? "": rs.getString("HARGA_BERSAMAAN"));
		maklumatProjek.addElement(h);
		bil++;
		}
	return maklumatProjek;

		} finally {
	if (db != null)
		db.close();
	}
	}

	

	
	public static Hashtable getPermohonanInfo(String idpermohonan)
	throws Exception {
		Db db = null;
		String sql = "";
		try {
	db = new Db();
	Statement stmt = db.getStatement();
	SQLRenderer r = new SQLRenderer();
	sql = " select trm.nama_mukim, trd.id_daerah, trd.kod_daerah, trd.NAMA_DAERAH, n.nama_negeri,k.nama_kementerian,a.nama_agensi, "
			+ " s.nama_suburusan,f.no_fail,to_char(f.tarikh_daftar_fail,'dd/mm/yyyy') tarikh_daftar_fail ,h.no_rujukan_kjp, "
			+ " h.no_rujukan_lain,to_char(p.tarikh_surat,'dd/mm/yyyy') tarikh_surat,to_char(p.tarikh_terima,'dd/mm/yyyy') tarikh_terima,p.tujuan,"
			+ " p.id_permohonan,f.id_fail,k.id_kementerian,a.id_agensi, "
			+ " n.id_negeri"
			+ " FROM tblrujnegeri n, Tblpfdfail f, tblrujkementerian k,tblpermohonan p, tblrujdaerah trd, tblrujmukim trm,"
			+ " tblhtppermohonan h, tblrujagensi a,tblrujsuburusan s"
			+ " where  trm.id_daerah=trd.id_daerah and trd.id_negeri=n.ID_NEGERI and "
			+ " f.ID_NEGERI=n.ID_NEGERI and f.id_kementerian=k.id_kementerian and "
			+ " p.ID_FAIL=f.ID_FAIL and p.id_permohonan=h.ID_PERMOHONAN and "
			+ " h.ID_AGENSI=a.id_agensi and f.ID_SUBURUSAN=s.ID_SUBURUSAN and "
			+ " p.id_permohonan= '" + idpermohonan + "'";
	
	
	mylogger.info("HEADER PENSWASTAAN (" + idpermohonan + ") : sql:" + sql);
	ResultSet rs = stmt.executeQuery(sql);
	Hashtable h = new Hashtable();

	while (rs.next()) {
		if (rs.getString("nama_negeri") == null) {
			h.put("negeri", "");
		} else {
			h.put("negeri", rs.getString("nama_negeri"));
		}
		if (rs.getString("nama_kementerian") == null) {
			h.put("kementerian", "");
		} else {
			h.put("kementerian", rs.getString("nama_kementerian"));
		}
		if (rs.getString("nama_agensi") == null) {
			h.put("agensi", "");
		} else {
			h.put("agensi", rs.getString("nama_agensi"));
		}
		if (rs.getString("nama_suburusan") == null) {
			h.put("suburusan", "");
		} else {
			h.put("suburusan", rs.getString("nama_suburusan"));
		}
		if (rs.getString("no_fail") == null) {
			h.put("fail", "");
		} else {
			h.put("fail", rs.getString("no_fail"));
		}
		if (rs.getString("tarikh_daftar_fail") == null) {
			h.put("tdaftar", new Date());
		} else {
			h.put("tdaftar", rs.getString("tarikh_daftar_fail"));
		}
		if (rs.getString("no_rujukan_kjp") == null) {
			h.put("rujukankjp", "");
		} else {
			h.put("rujukankjp", rs.getString("no_rujukan_kjp"));
		}
		if (rs.getString("no_rujukan_lain") == null) {
			h.put("rujukanlain", "");
		} else {
			h.put("rujukanlain", rs.getString("no_rujukan_lain"));
		}
		if (rs.getString("tarikh_surat") == null) {
			h.put("tsurat", new Date());
		} else {
			h.put("tsurat", rs.getString("tarikh_surat"));
		}
		if (rs.getString("tarikh_terima") == null) {
			h.put("rtterima", new Date());
		} else {
			h.put("rtterima", rs.getString("tarikh_terima"));
		}
		if (rs.getString("tujuan") == null) {
			h.put("tujuan", "");
		} else {
			h.put("tujuan", rs.getString("tujuan"));
		}
		if (rs.getString("id_permohonan") == null) {
			h.put("idpermohonan", "");
		} else {
			h.put("idpermohonan", rs.getString("id_permohonan"));
		}
		if (rs.getString("id_fail") == null) {
			h.put("idfail", "");
		} else {
			h.put("idfail", rs.getString("id_fail"));
		}
		h.put("idagensi", rs.getString("id_agensi"));
		h.put("idkementerian", rs.getString("id_kementerian"));
		h.put("idnegeri", rs.getString("id_negeri"));
		h.put("iddaerah", rs.getString("id_daerah"));
		 h.put("namamukim", rs.getString("nama_mukim"));
		// list.addElement(h);
	}
	return h;
} finally {
	if (db != null)
		db.close();
}
}
}
