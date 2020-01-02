package ekptg.report.htp;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.model.htp.utiliti.HTPSusulanBean;
import ekptg.model.htp.utiliti.IHTPSusulan;
import ekptg.report.EkptgReportServlet;

public class SuratPajakanPeringatanBayarKepadaPemohon extends EkptgReportServlet{
	static Logger myLog = Logger.getLogger(SuratPajakanKepadaPemohon.class);
	private IHTPSusulan iSusulan = null;

	public SuratPajakanPeringatanBayarKepadaPemohon() {
		super.setReportName("HTPajakanSuratBayaran");
		super.setFolderName("htp");
	
	}

	public void doProcessing(HttpServletRequest request,HttpServletResponse response
		, ServletContext context, Map<String, Object> parameters) throws Exception {
		String idPermohonan = String.valueOf(parameters.get("idpermohonan"));
		//String template =(String)parameters.get("template");
		String pegawai =(String)parameters.get("namaPegawai");
		//String jawatan =(String)parameters.get("jawatan");
		String email =(String)parameters.get("emelPegawai");
		//String noTelefon =(String)parameters.get("notelefon");
		String tarikhHantar = String.valueOf(parameters.get("tarikh"));
		String userID = String.valueOf(parameters.get("userid"));
		//myLog.info(userID);
		Hashtable<String,String> dataAsal = new Hashtable<String,String>();
		dataAsal = getISusulan().getMaklumatSusulan(idPermohonan, "PAJAKAN_SURAT_PERINGATANB");
		String idSusulan = "-1";
		//myLog.info(idSusulan);
		//myLog.info(dataAsal);
		if(dataAsal != null)
			idSusulan = dataAsal.get("idSusulan");
		
		//myLog.info("01:"+idSusulan);
		Hashtable<String,String> data = new Hashtable<String,String>();
		data.put("idPermohonan", idPermohonan);
		data.put("txdTarikh", tarikhHantar);
		data.put("catatan", "Surat Peringatan Kepada Pemohon");
		data.put("sumber", "PAJAKAN_SURAT_PERINGATANB");
		data.put("idSusulan",idSusulan);
		data.put("idMasuk",userID);	
		idSusulan = getISusulan().simpanKemaskini(data);

		//parameters.put("BILDOKUMEN",bildok);
		parameters.put("idPermohonan",idPermohonan);
		parameters.put("namaPegawai",pegawai);
		//parameters.put("JAWATAN",jawatan);
		parameters.put("emelPegawai",email);
		parameters.put("tarikhHantar",tarikhHantar);
		
	}
	private IHTPSusulan getISusulan(){
		if(iSusulan==null){
			iSusulan = new HTPSusulanBean();
		}
		return iSusulan;
				
	}

	
}
