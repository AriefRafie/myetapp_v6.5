package ekptg.report.htp;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.helpers.NumberToWords;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.cukai.CukaiBaucerBean;
import ekptg.model.htp.cukai.ICukaiBaucer;
import ekptg.report.EkptgReportServlet;

public class SuratHTPCukai extends EkptgReportServlet {
	
	static Logger myLog = Logger.getLogger(ekptg.report.htp.SuratHTPCukai.class);
	FrmUtilData frmUtil = null; 
	private ICukaiBaucer iCukaiBaucer = null;
	public void LaporanHtp() {
		//super.setReportName("LaporanHTP");
		super.setFolderName("htp");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		frmUtil = new FrmUtilData();

		String bildok =(String)parameters.get("bil");
		String idPermohonan =(String)parameters.get("idpermohonan");
		String template =(String)parameters.get("template");
		String pegawai =(String)parameters.get("pegawai");
		String jawatan =(String)parameters.get("jawatan");
		String email =(String)parameters.get("email");
		String noTelefon =(String)parameters.get("notelefon");
		String up =(String)parameters.get("up");
		String beban =(String)parameters.get("beban");
		String tahun =(String)parameters.get("tahun");
		String idFail =(String)parameters.get("idfail");
		String langkah =(String)parameters.get("langkah");
		String idUser =(String)parameters.get("iduser");
		String idSuburusan =(String)parameters.get("idsuburusan");
		String idBaucer =(String)parameters.get("idbaucer");
		if(idPermohonan!="" && idFail!=""){
			frmUtil.simpanKemaskiniStatusPermohonan(idSuburusan,langkah,idPermohonan,idFail,idUser)	;	
		}
		
		super.setFolderName("htp");
		super.setReportName(template);	
	
		parameters.put("BILDOKUMEN",bildok);
		parameters.put("ID_PERMOHONAN",idPermohonan);
		parameters.put("NAMAPEGAWAI",pegawai);
		parameters.put("JAWATAN",jawatan);
		parameters.put("EMAIL",email);
		parameters.put("NO_TELEFON",noTelefon);
		parameters.put("UP",up);
		parameters.put("BEBANAN",beban);
		parameters.put("RUJ_SURAT",up);
		parameters.put("RUJ_TARIKH",beban);
		//30/04/2010
		parameters.put("TAHUN",tahun);
		parameters.put("ID_BAUCER",idBaucer);
		NumberToWords nw = new NumberToWords();
		getICukaiBaucer().isBaucer(idBaucer);
		//myLog.info(nw.convertTwoPart("12356777.22"));
		//myLog.info("Jumlah:"+getICukaiBaucer().getJumlahBaucer());
		parameters.put("PERKATAAN",NumberToWords.convertTwoPart(String.valueOf(getICukaiBaucer().getJumlahBaucer())).toUpperCase());
	
		
	}
	
	
	private ICukaiBaucer getICukaiBaucer(){
		if(iCukaiBaucer==null){
			iCukaiBaucer = new CukaiBaucerBean();
		}
		return iCukaiBaucer;
		
	}
	
}
	
	