package ekptg.model.ppk.laporan;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.model.laporan.ILaporan;

public class LaporanPrestasiPegawaiBean implements ILaporan {
	
	private Db db = null;
	private String sql ="";
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Vector<Hashtable<String,String>> senarai = null;
	static Logger myLog = Logger.getLogger(ekptg.model.ppk.laporan.LaporanPrestasiPegawaiBean.class);
	
	public int getBilPerbicaraanSelesai(
		String idNegeri, String idUnit, String idPegawai, String tahun, String bulanMM) 
		throws Exception {
		int returnValue = 0;
		db = new Db();
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			sql = "SELECT count(*) bilangan FROM TBLPPKPERINTAH PER" +
				" ,TBLPPKPERBICARAAN PKP" +
				" ,TBLPPKKEPUTUSANPERMOHONAN PKKP " +
				" ,TBLPPKPERMOHONAN P " +
				" ,TBLPFDFAIL F " +
				" ,TBLPPKRUJUNIT RU " +
				" WHERE PKP.ID_KEPUTUSANPERMOHONAN = PKKP.ID_KEPUTUSANPERMOHONAN " +
				" AND PKKP.ID_PERMOHONAN = P.ID_PERMOHONAN " +
				" AND P.ID_FAIL = F.ID_FAIL " +
				" AND F.ID_NEGERI = RU.ID_NEGERI " +
				" AND RU.ID_UNITPSK = PKP.ID_UNITPSK " +
				" AND PKP.ID_PERBICARAAN = PER.ID_PERBICARAAN " +
				" AND PER.FLAG_JENIS_KEPUTUSAN = 0 ";
			if(!idNegeri.equals("0"))
				sql += " AND RU.ID_NEGERI = "+idNegeri; 
		
			if(!idUnit.equals("0"))
				sql += " AND RU.ID_JKPTG = "+idUnit; 
				
			sql +=" AND PKP.ID_UNITPSK = "+idPegawai +
				" AND TO_CHAR(PKP.TARIKH_BICARA,'YYYY') = " +tahun+ ""+
				" AND TO_CHAR(PKP.TARIKH_BICARA,'MM') ='" +bulanMM+"'"+
				"";		
			
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				returnValue = rs.getInt(1);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	
	}	
	
	public int getBilPerbicaraan(
			String idNegeri, String idUnit, String idPegawai, String tahun, String bulanMM) 
		throws Exception {
		int returnValue = 0;
		db = new Db();
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			sql = "SELECT count(*) bilangan FROM TBLPPKPERBICARAAN PKP " +
				" ,TBLPPKKEPUTUSANPERMOHONAN PKKP " +
				" ,TBLPPKPERMOHONAN P " +
				" ,TBLPFDFAIL F " +
				" ,TBLPPKRUJUNIT RU " +
				" WHERE PKP.ID_KEPUTUSANPERMOHONAN = PKKP.ID_KEPUTUSANPERMOHONAN " +
				" AND PKKP.ID_PERMOHONAN = P.ID_PERMOHONAN " +
				" AND P.ID_FAIL = F.ID_FAIL " +
				" AND F.ID_NEGERI = RU.ID_NEGERI " +
				" AND RU.ID_UNITPSK = PKP.ID_UNITPSK ";
			if(!idNegeri.equals("0"))
				sql += " AND RU.ID_NEGERI = "+idNegeri; 
			if(!idUnit.equals("0"))
				sql += " AND RU.ID_JKPTG = "+idUnit; 
			
				sql +=" AND PKP.ID_UNITPSK = "+idPegawai +
				" AND TO_CHAR(PKP.TARIKH_BICARA,'YYYY') = " +tahun+ ""+
				" AND TO_CHAR(PKP.TARIKH_BICARA,'MM') ='" +bulanMM+"'"+
				"";
			//myLog.info("getBilPerbicaraan:sql="+sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				returnValue = rs.getInt(1);
			}

		} finally {
			if (db != null)
				db.close();
			}
		return returnValue;
		
	}

	public Vector<Hashtable<String,String>> getSenarai(
		String idNegeri, String idUnit,String idPegawai, String tahun, String bulanMM) throws Exception{
		 try{
			 senarai = new Vector<Hashtable<String,String>>();
		     db = new Db();
		     Statement stmt = db.getStatement();
		     sql = "SELECT F.NO_FAIL,TO_CHAR(P.TARIKH_MOHON,'dd/mm/yyyy') TARIKH_MOHON" +
		     		" ,S.NAMA_SIMATI,PE.NAMA_PEMOHON" +
		     		" ,TO_CHAR(PKP.TARIKH_BICARA,'dd/mm/yyyy') TARIKH_BICARA" +
		     		" FROM " +
		     		" TBLPPKPERBICARAAN PKP " +
		     		" ,TBLPPKKEPUTUSANPERMOHONAN PKKP " +
		     		" ,TBLPPKPERMOHONAN P " +
		     		" ,TBLPFDFAIL F " +
		     		" ,TBLPPKPEMOHON PE " +
		     		" ,TBLPPKPERMOHONANSIMATI PKS " +
		     		" ,TBLPPKSIMATI S " +
					" ,TBLPPKRUJUNIT RU " +
				    " WHERE PKP.ID_KEPUTUSANPERMOHONAN = PKKP.ID_KEPUTUSANPERMOHONAN " +
				    " AND PKKP.ID_PERMOHONAN = P.ID_PERMOHONAN " +
				    " AND P.ID_FAIL = F.ID_FAIL " +
				    " AND P.ID_PEMOHON = PE.ID_PEMOHON " +
				    " AND P.ID_PERMOHONAN = PKS.ID_PERMOHONAN " +
				    " AND PKS.ID_SIMATI = S.ID_SIMATI " +
					" AND F.ID_NEGERI = RU.ID_NEGERI " +
					" AND RU.ID_UNITPSK = PKP.ID_UNITPSK ";
				if(!idNegeri.equals("0"))
					sql += " AND RU.ID_NEGERI = "+idNegeri; 
				if(!idUnit.equals("0"))
					sql += " AND RU.ID_JKPTG = "+idUnit; 
				
					sql +=" AND PKP.ID_UNITPSK = "+idPegawai +
					" AND TO_CHAR(PKP.TARIKH_BICARA,'YYYY') = " +tahun+ ""+
					" AND TO_CHAR(PKP.TARIKH_BICARA,'MM') ='" +bulanMM+"'"+
		     		" ORDER BY PKP.TARIKH_BICARA DESC ";
		     //myLog.info(sql);
		     ResultSet rs = stmt.executeQuery(sql);
		     Hashtable<String,String> h = null;
		     int bil=1;
		     while (rs.next()) {		    	  
		    	  h = new Hashtable<String,String>();
		    	  h.put("bil",String.valueOf(bil));
		    	  h.put("noFail",rs.getString("NO_FAIL"));
		    	  h.put("pemohon",rs.getString("NAMA_PEMOHON"));
		    	  h.put("tarikhMohon",rs.getString("TARIKH_MOHON"));
		    	  h.put("tarikhBicara",rs.getString("TARIKH_BICARA"));
		    	  h.put("simati",rs.getString("NAMA_SIMATI"));
		    	  senarai.addElement(h);
		    	  bil++;
		     }
		     return senarai;
			 
		 }finally {
			if(db != null) db.close();
		 }
		 
	 }
	public Vector<Hashtable<String,String>> getSenaraiSelesai(String idNegeri, String idUnit,String idPegawai, String tahun, String bulanMM) throws Exception{
		 try{
			 senarai = new Vector<Hashtable<String,String>>();
		     db = new Db();
		     Statement stmt = db.getStatement();
		     sql = "SELECT F.NO_FAIL,TO_CHAR(P.TARIKH_MOHON,'dd/mm/yyyy') TARIKH_MOHON" +
		     		" ,S.NAMA_SIMATI,PE.NAMA_PEMOHON" +
		     		" ,TO_CHAR(PKP.TARIKH_BICARA,'dd/mm/yyyy') TARIKH_BICARA" +
		     		" FROM " +
		     		" TBLPPKPERBICARAAN PKP " +
		     		" ,TBLPPKKEPUTUSANPERMOHONAN PKKP " +
		     		" ,TBLPPKPERMOHONAN P " +
		     		" ,TBLPFDFAIL F " +
		     		" ,TBLPPKPEMOHON PE " +
		     		" ,TBLPPKPERMOHONANSIMATI PKS " +
		     		" ,TBLPPKSIMATI S " +
		     		" ,TBLPPKPERINTAH PKPE "+
					" ,TBLPPKRUJUNIT RU " +
				    " WHERE PKP.ID_KEPUTUSANPERMOHONAN = PKKP.ID_KEPUTUSANPERMOHONAN " +
				    " AND PKKP.ID_PERMOHONAN = P.ID_PERMOHONAN " +
				    " AND P.ID_FAIL = F.ID_FAIL " +
				    " AND P.ID_PEMOHON = PE.ID_PEMOHON " +
				    " AND P.ID_PERMOHONAN = PKS.ID_PERMOHONAN " +
				    " AND PKS.ID_SIMATI = S.ID_SIMATI " +
		     		" AND PKP.ID_PERBICARAAN = PKPE.ID_PERBICARAAN " +
		     		" AND PKPE.FLAG_JENIS_KEPUTUSAN = 0 " +
					" AND F.ID_NEGERI = RU.ID_NEGERI " +
					" AND RU.ID_UNITPSK = PKP.ID_UNITPSK ";
				if(!idNegeri.equals("0"))
					sql += " AND RU.ID_NEGERI = "+idNegeri; 
				if(!idUnit.equals("0"))
					sql += " AND RU.ID_JKPTG = "+idUnit; 
				
					sql +=" AND PKP.ID_UNITPSK = "+idPegawai +
					" AND PKP.ID_UNITPSK = "+idPegawai +
					" AND TO_CHAR(PKP.TARIKH_BICARA,'YYYY') = " +tahun+ ""+
					" AND TO_CHAR(PKP.TARIKH_BICARA,'MM') ='" +bulanMM+"'"+
		     		" ORDER BY PKP.TARIKH_BICARA DESC ";
		     //myLog.info(sql);
		     ResultSet rs = stmt.executeQuery(sql);
		     Hashtable<String,String> h = null;
		     int bil=1;
		     while (rs.next()) {		    	  
		    	  h = new Hashtable<String,String>();
		    	  h.put("bil",String.valueOf(bil));
		    	  h.put("noFail",rs.getString("NO_FAIL"));
		    	  h.put("pemohon",rs.getString("NAMA_PEMOHON"));
		    	  h.put("tarikhMohon",rs.getString("TARIKH_MOHON"));
		    	  h.put("tarikhBicara",rs.getString("TARIKH_BICARA"));
		    	  h.put("simati",rs.getString("NAMA_SIMATI"));
		    	  senarai.addElement(h);
		    	  bil++;
		     }
		     return senarai;
			 
		 }finally {
			if(db != null) db.close();
		 }
		 
	 }
	
}