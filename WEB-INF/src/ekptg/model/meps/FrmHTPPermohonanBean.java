package ekptg.model.meps;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.htp.cukai.entity.CukaiPenyata;

public class FrmHTPPermohonanBean implements IHTPMeps {
	
	static Logger myLog = Logger.getLogger(ekptg.model.meps.FrmHTPPembelianBean.class);
	private static SimpleDateFormat format =  new SimpleDateFormat("dd/MM/yyyy");
	private String sql = "";
	private CukaiPenyata cPenyata = null;
	private Vector getTotal = null;
    private String SQL;

	public Vector getJumlahMengikutNegeri(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		Db db = null;
		String sql = "";
		try{
			getTotal = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();									
			sql =  " SELECT  N.NAMA_NEGERI as x, COUNT(*) AS y";					
			sql += " FROM TBLPFDFAIL A,TBLPERMOHONAN B" +
				//",TBLHTPPERMOHONAN C " +
				//" ,TBLRUJDAERAH D" +
				", TBLRUJNEGERI N" +
				//", TBLRUJJENISTANAH E,TBLHTPKEPUTUSANMOHON F,TBLRUJAGENSI G" +
				",TBLRUJKEMENTERIAN H ";
			sql += " WHERE B.ID_FAIL=A.ID_FAIL " +
				//"AND B.ID_PERMOHONAN = C.ID_PERMOHONAN " +
				" AND A.ID_URUSAN = 1 AND A.ID_NEGERI = N.ID_NEGERI " +
				//" AND C.ID_DAERAH = D.ID_DAERAH AND C.ID_JENISTANAH = E.ID_JENISTANAH "+
				//" AND F.ID_PERMOHONAN = B.ID_PERMOHONAN " +
				//"AND C.ID_AGENSI=G.ID_AGENSI " +
				" AND A.ID_KEMENTERIAN = H.ID_KEMENTERIAN ";
			// TAHUN 
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
				}
			}					
			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND A.TARIKH_DAFTAR_FAIL BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}
			sql += " GROUP BY N.KOD_NEGERI, N.NAMA_NEGERI ";
			sql += " ORDER BY N.KOD_NEGERI ";
			
			setSQL(sql);
			System.out.println("Laporan Permohonan Pemberimilikan Mengikut Negeri = "+sql);
			//myLogger.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;	
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();				    
				h.put("bil", bil);
				//h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
			    h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    h.put("nama_negeri", rs.getString("x"));
			    getTotal.addElement(h);
			    bil++;
			}      
		
		}finally{
			if(db != null)db.close();
		}	
		return getTotal;					
	
	}
	
	public void setSQL(String sql) {
		SQL = sql;
	}

	

}

