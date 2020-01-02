package ekptg.view.meps;

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

public class FrmHTPPembelianBean implements IHTPMeps {
	
	static Logger myLog = Logger.getLogger(ekptg.model.meps.FrmHTPPembelianBean.class);
	private static SimpleDateFormat format =  new SimpleDateFormat("dd/MM/yyyy");
	private String sql = "";
	private CukaiPenyata cPenyata = null;
	private Vector getTotalPembelianMengikutNegeri = null;
    private String SQL;

	public Vector getJumlahMengikutNegeri(String socTahun,String socBulan)throws Exception {		 		 
		Db db = null;
		String sql = "";
		try{
			getTotalPembelianMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();									
			sql =  " SELECT  N.NAMA_NEGERI AS x, COUNT(*) AS y";					
			sql += " FROM TBLPFDFAIL A,TBLPERMOHONAN B" +
				//",TBLHTPPERMOHONAN C" +
				//",TBLHTPPERJANJIAN D,TBLRUJSUBURUSAN E" +
				",TBLRUJKEMENTERIAN F ,TBLRUJNEGERI N" +
				//",TBLRUJDAERAH G,TBLHTPPERJANJIANPEMBELIAN H, TBLRUJAGENSI I
				"";
			sql += " WHERE A.ID_URUSAN = 2 AND B.ID_FAIL = A.ID_FAIL " +
				//"AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_PERMOHONAN = B.ID_PERMOHONAN  AND A.ID_SUBURUSAN = E.ID_SUBURUSAN AND C.ID_AGENSI = I.ID_AGENSI";
				" AND A.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND A.ID_NEGERI = N.ID_NEGERI " +
				//"AND G.ID_DAERAH = C.ID_DAERAH AND D.ID_PERJANJIAN = H.ID_PERJANJIAN " +
				"";
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
					
			sql += " GROUP BY N.NAMA_NEGERI ";			
			setSQL(sql);
			myLog.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;	
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();			    	
		    	h.put("bil", bil);
		        //h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));		    	
		    	getTotalPembelianMengikutNegeri.addElement(h);
		    	bil++;
		      }      
		
		}finally{
			if(db != null)db.close();
		}	
		return getTotalPembelianMengikutNegeri;	
		
	}
	
	public void setSQL(String sql) {
		SQL = sql;
	}

	

}

