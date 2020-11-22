package ekptg.report.php2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sun.xml.internal.messaging.saaj.util.Base64;
import lebah.db.Db;
import ekptg.model.utils.QRCodeAPB;
import ekptg.report.EkptgReportServlet;

public class APBMemoMenteri extends EkptgReportServlet {

	public APBMemoMenteri() {
		super.setReportName("APBorang2_Perenggan4");
		super.setFolderName("php2//APB");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
		HttpServletResponse response, ServletContext context, Map parameters)
		throws Exception {
		
		String idfail = "";
		if (parameters.get("ID_FAIL") != null){
			idfail = (String)parameters.get("ID_FAIL");
		}
		String nofail = getNoFailByIdFail(idfail);
		
		String namapemeganglesen="";
		String nopengenalan="";	
		String nolesen="";
		String tarikhmulalesen="";	
		String tarikhtamatlesen="";	
		
		if(!idfail.equals("")){
			Db db = null;
			try {
				db = new Db();			
				namapemeganglesen = getNamaPemegangLesen(idfail,db);
				nopengenalan=getNoPengenalan(idfail,db);	
				nolesen=getNoLesen(idfail,db);
				tarikhmulalesen=getTarikhMulaLesen(idfail,db);	
				tarikhtamatlesen=getTarikhTamatLesen(idfail,db);	
				
			}catch(Exception e){
				System.out.println(e.toString());
			}finally {
				if (db != null)
					db.close();
			}
		}
		
		//--------------------QR CODE START---------------------------------------------------------------
		String nofailEncode;
		nofailEncode = nofail;
		nofailEncode = "No fail : " + nofailEncode + ", Nama Pemegang Lesen : \r\n";
		nofailEncode = nofailEncode + namapemeganglesen + " , No. Pengenalan :" + nopengenalan +"\r\n";
		nofailEncode = nofailEncode + ", No. Lesen :" + nolesen +", Tarikh Mula Lesen : \r\n";
		nofailEncode = nofailEncode + tarikhmulalesen + ", Tarikh Tamat Lesen : \r\n";
		nofailEncode = nofailEncode + tarikhtamatlesen;
		
		
		byte[] bytesEncoded = Base64.encode(nofailEncode.getBytes());		
		System.out.println("bytesEncoded = "+bytesEncoded);
		String valueDecoded = Base64.base64Decode(new String(bytesEncoded));
		System.out.println("bytesDecoded = "+valueDecoded);

		QRCodeAPB model1 = new QRCodeAPB(nofail, new String(bytesEncoded),context);
		String slash = "/";
		String noFailremoveslash = nofail.replaceAll(slash,"");
		noFailremoveslash=noFailremoveslash.replaceAll(" ","");
		parameters.put("noFailremoveslash", noFailremoveslash);
		
		//-----------------------QR CODE END-------------------------------------------------------------		
	}
	
	public String getNoFailByIdFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail
					+ "'";
			System.out.println("getNoFailByIdFail"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("NO_FAIL").toString();
			}
			return "";
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getNamaPemegangLesen(String idFail,Db db) throws Exception {
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql= "SELECT DISTINCT C.NAMA AS NAMA_PEMEGANG_LESEN "
				+"FROM TBLPFDFAIL A,TBLPERMOHONAN B, TBLPHPPEMOHON C,TBLPHPPMOHONNJDUALPERTAMA E,TBLPHPBYRNSYRTKLLSNLESENAPB G, TBLPHPJADUALKEDUALESENAPB I "
				+"WHERE A.ID_FAIL=B.ID_FAIL AND C.ID_PEMOHON=B.ID_PEMOHON "
				+"AND G.ID_PERMOHONAN=B.ID_PERMOHONAN AND G.ID_PERMOHONAN=E.ID_PERMOHONAN "
				+"AND A.ID_FAIL="+idFail;
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("NAMA_PEMEGANG_LESEN").toString();
			}
			return "";
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getNoPengenalan(String idFail,Db db) throws Exception {
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql= "SELECT DISTINCT C.NO_PENGENALAN AS NO_PENGENALAN "
				+"FROM TBLPFDFAIL A,TBLPERMOHONAN B, TBLPHPPEMOHON C,TBLPHPPMOHONNJDUALPERTAMA E,TBLPHPBYRNSYRTKLLSNLESENAPB G, TBLPHPJADUALKEDUALESENAPB I "
				+"WHERE A.ID_FAIL=B.ID_FAIL AND C.ID_PEMOHON=B.ID_PEMOHON "
				+"AND G.ID_PERMOHONAN=B.ID_PERMOHONAN AND G.ID_PERMOHONAN=E.ID_PERMOHONAN "
				+"AND A.ID_FAIL="+idFail;
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("NO_PENGENALAN").toString();
			}
			return "";
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getNoLesen(String idFail,Db db) throws Exception {
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql= "SELECT DISTINCT G.NO_LESEN AS NO_LESEN "
				+"FROM TBLPFDFAIL A,TBLPERMOHONAN B, TBLPHPPEMOHON C,TBLPHPPMOHONNJDUALPERTAMA E,TBLPHPBYRNSYRTKLLSNLESENAPB G, TBLPHPJADUALKEDUALESENAPB I "
				+"WHERE A.ID_FAIL=B.ID_FAIL AND C.ID_PEMOHON=B.ID_PEMOHON "
				+"AND G.ID_PERMOHONAN=B.ID_PERMOHONAN AND G.ID_PERMOHONAN=E.ID_PERMOHONAN "
				+"AND A.ID_FAIL="+idFail;
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("NO_LESEN").toString();
			}
			return "";
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getTarikhMulaLesen(String idFail,Db db) throws Exception {
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql= "SELECT DISTINCT TO_CHAR(G.TARIKH_MULA_LESEN,'DD/MM/YYYY')AS TARIKH_MULA_LESEN "
				+"FROM TBLPFDFAIL A,TBLPERMOHONAN B, TBLPHPPEMOHON C,TBLPHPPMOHONNJDUALPERTAMA E,TBLPHPBYRNSYRTKLLSNLESENAPB G, TBLPHPJADUALKEDUALESENAPB I "
				+"WHERE A.ID_FAIL=B.ID_FAIL AND C.ID_PEMOHON=B.ID_PEMOHON "
				+"AND G.ID_PERMOHONAN=B.ID_PERMOHONAN AND G.ID_PERMOHONAN=E.ID_PERMOHONAN "
				+"AND A.ID_FAIL="+idFail;
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("TARIKH_MULA_LESEN").toString();
			}
			return "";
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getTarikhTamatLesen(String idFail,Db db) throws Exception {
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql= "SELECT DISTINCT TO_CHAR(G.TARIKH_TAMAT_LESEN,'DD/MM/YYYY')AS TARIKH_TAMAT_LESEN "
				+"FROM TBLPFDFAIL A,TBLPERMOHONAN B, TBLPHPPEMOHON C,TBLPHPPMOHONNJDUALPERTAMA E,TBLPHPBYRNSYRTKLLSNLESENAPB G, TBLPHPJADUALKEDUALESENAPB I "
				+"WHERE A.ID_FAIL=B.ID_FAIL AND C.ID_PEMOHON=B.ID_PEMOHON "
				+"AND G.ID_PERMOHONAN=B.ID_PERMOHONAN AND G.ID_PERMOHONAN=E.ID_PERMOHONAN "
				+"AND A.ID_FAIL="+idFail;
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("TARIKH_TAMAT_LESEN").toString();
			}
			return "";
		} finally {
			if (db != null)
				db.close();
		}
	}
}
