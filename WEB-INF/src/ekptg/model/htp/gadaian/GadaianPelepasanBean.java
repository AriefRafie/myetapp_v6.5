package ekptg.model.htp.gadaian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import lebah.db.Db;
import ekptg.model.entities.Bebanan;
import ekptg.model.entities.MaklumatGadaian;
import ekptg.model.htp.entity.HtpPermohonan;

public class GadaianPelepasanBean implements IGadaianPelepasan{
	private static final String KOD_JABATAN ="JKPTG";
	private static final long IDTARAFKESELAMTAN = 1;
	private static final int IDSEKSYEN = 3;
	private static final int IDURUSAN = 2;
	MaklumatGadaian mg = null;
	Bebanan beban = null;	

	//@Override
	public MaklumatGadaian cariMaklumat(String idPermohonan) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";
		
		Date now = new Date();
		 SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		 String tarikhDaftarFail = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			sql = "select TPMG.TARIKH_LEPASGADAI,TPMG.id_Permohonan ";
			sql += " from TBLHTPMAKLUMATGADAIAN TPMG ";
			sql += " where ";
			sql += " TPMG.ID_PERMOHONAN = '"+ idPermohonan +"'";
			sql +=" ORDER BY TPMG.id_Permohonan desc";
			System.out.println("MaklumatGadaian:::cariMaklumat::sql::"+sql);
											
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);		
			mg = new MaklumatGadaian();
			mg.setTarikhLepasgadai(now);							     
										    
			if(rs.next()){
			
			mg.setTarikhLepasgadai(rs.getDate("TARIKH_LEPASGADAI"));

			conn.commit();
										     }									    	
		 } finally {
		      if (db != null) db.close();
		    }
		

		return mg;
	}
	//@Override

	
	//@Override
	public Bebanan cariBebanan(String idPermohonan) {
		Db db = null;
		Vector<HtpPermohonan> v = new Vector();
		try{
			db = new Db();
			Statement stmt = db.getStatement();

		      String sql = "SELECT f.id_fail, P.ID_PERMOHONAN , PK.NAMA , PK.ALAMAT1,PK.ALAMAT2,PK.ALAMAT3, ";
		      sql += " PK.POSKOD, PK.NO_TEL, PK.NO_FAX, HTPP.NO_RUJUKAN_LAIN, ";
		      sql += " N.NAMA_NEGERI, D.NAMA_DAERAH, HU.NO_HAKMILIK, HU.NO_LOT, L.KETERANGAN as nama_lot,";
		      sql += " HK.KETERANGAN as jenis_hakmilik, B.NO_PERSERAHAN ";
		      sql += " from tblpfdfail f,TBLHTPFAILMAPPING FP,TBLPERMOHONAN P,TBLHTPHAKMILIKURUSAN HU,TBLHTPPIHAKBERKEPENTINGAN PK,";
		      sql += "  tblhtppermohonan htpp, tblrujdaerah d, tblrujnegeri n, tblrujlot l, tblrujjenishakmilik hk,  tblhtpbebanan b";
		      sql += " where " ;
		      //		"f.id_fail=" + idFail;
		      sql += " AND FP.ID_FAIL=F.ID_FAIL ";
		      sql += " AND FP.ID_FAILLAMA =P.ID_FAIL ";
		      sql += " AND P.ID_PERMOHONAN="+idPermohonan;
		      sql += " AND P.ID_PERMOHONAN=HU.ID_PERMOHONAN ";
		      sql += " AND P.ID_PERMOHONAN=htpp.ID_PERMOHONAN ";
		      sql += " AND PK.ID_HAKMILIKURUSAN=HU.ID_HAKMILIKURUSAN ";
		      sql += " AND PK.ID_DAERAH = D.ID_DAERAH ";
		      sql += " AND PK.ID_NEGERI = N.ID_NEGERI ";
		      sql += " AND HU.ID_LOT = L.ID_LOT ";
		      sql += " AND HU.ID_JENISHAKMILIK = HK.ID_JENISHAKMILIK ";
		      sql += " AND PK.ID_PIHAKBERKEPENTINGAN = B.ID_PIHAKBERKEPENTINGAN ";
			
			beban = new Bebanan();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				beban.setNamaPihakBerkepentingan(rs.getString("NAMA"));
				
				//v.addElement(htpPermohonan);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			 if (db != null){
		    	  db.close();
		      }
		}
		return beban;
	}
	
	
}
