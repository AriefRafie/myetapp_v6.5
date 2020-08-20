package ekptg.report.php2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import lebah.db.Db;
import com.sun.xml.internal.messaging.saaj.util.Base64;
import ekptg.model.utils.QRCodeAPB;
import ekptg.report.EkptgReportServlet;
import ekptg.report.ppk.FrmPopupPilihPegawaiReportData;

public class APBLesenPasir extends EkptgReportServlet {
	static Logger myLogger = Logger.getLogger(APBLesenPasir.class);
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public APBLesenPasir() {
		super.setReportName("APBBorang2Perenggan4");
		super.setFolderName("php2\\APB");
		//super.setFolderName("php2);
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		//rozai tambah utk qr code (14082020)
		String idfail = "";
		if (parameters.get("ID_FAIL") != null){
			idfail = (String)parameters.get("ID_FAIL");
		}
		Db db = null;
		String nofail = logic.getNoFailByIdFail(idfail);	
		String namaPelesen= "";
		String noSyarikat= "";
		String lokasi= "";
		String luasKawasan= "";
		String tarikhMula= "";
		String tarikhTamat= "";
		String jumlahFi= "";
		
		if(!idfail.equals(""))
		{
			db = new Db();
			String sql = "";
			
			sql = "SELECT DISTINCT A.ID_FAIL,A.NO_FAIL,A.TAJUK_FAIL,G.LUAS_KAWASAN,C.ID_PEMOHON,C.NAMA,C.NO_PENGENALAN,C.ALAMAT1_TETAP,C.ALAMAT2_TETAP,C.ALAMAT3_TETAP, B.ID_PERMOHONAN,G.JUMLAH_FEELESEN,G.NO_LESEN, G.TARIKH_MULA_LESEN,G.TARIKH_TAMAT_LESEN, E.LOKASI_PERMOHONAN" 
					
			+" FROM TBLPFDFAIL A,TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLPHPKERTASKERJAAPB D, TBLPHPPMOHONNJDUALPERTAMA E, TBLRUJNEGERI F, TBLPHPBYRNSYRTKLLSNLESENAPB G"

			+" WHERE A.ID_FAIL=B.ID_FAIL AND C.ID_PEMOHON=B.ID_PEMOHON AND D.ID_PERMOHONAN=B.ID_PERMOHONAN AND G.ID_PERMOHONAN=B.ID_PERMOHONAN AND E.ID_PERMOHONAN=B.ID_PERMOHONAN"

			+" AND A.ID_FAIL='"+idfail+"'";

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
				
			while (rs.next()) {
				namaPelesen= rs.getString("NAMA");
				noSyarikat= rs.getString("NO_PENGENALAN");
				lokasi= rs.getString("LOKASI_PERMOHONAN");
				tarikhMula= rs.getString("TARIKH_MULA_LESEN");
				tarikhTamat= rs.getString("TARIKH_TAMAT_LESEN");
				jumlahFi= rs.getString("JUMLAH_FEELESEN");
			}
		}
		//QR CODE START
		String nofailEncode;
		nofailEncode = nofail;
		nofailEncode = "No fail : " + nofailEncode + ", Nama Pelesen : \r\n";
		nofailEncode = nofailEncode + namaPelesen + " \r\n";
		nofailEncode = nofailEncode + ", No Syarikat : " + noSyarikat + " \r\n";				
		nofailEncode = nofailEncode + ", Lokasi : " + lokasi + " \r\n";
		nofailEncode = nofailEncode + ", Tarikh Mula : " + tarikhMula + " \r\n";
		nofailEncode = nofailEncode + ", Tarikh Tamat : " + tarikhTamat + " \r\n";
		nofailEncode = nofailEncode + ", Fee Lesen : RM" + jumlahFi;
										
		byte[] bytesEncoded = Base64.encode(nofailEncode.getBytes());
		
		//System.out.println("nofailEncode = "+nofailEncode);
		System.out.println("bytesEncoded = "+bytesEncoded);
		String valueDecoded = Base64.base64Decode(new String(bytesEncoded));
		System.out.println("bytesDecoded = "+valueDecoded);
		
		//remove encode base64
		QRCodeAPB model1 = new QRCodeAPB(nofail, new String(bytesEncoded));
		
		//QRCode model1 = new QRCode(nofail, nofailEncode);
		System.out.println("Return to Borang E");
		String slash = "/";
		String noFailremoveslash = nofail.replaceAll(slash,"");
		
		//QR CODE END
	}
}
