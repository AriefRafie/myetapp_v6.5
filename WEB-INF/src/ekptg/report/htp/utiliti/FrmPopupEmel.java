package ekptg.report.htp.utiliti;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.entities.Tblhtppermohonan;
import ekptg.model.htp.IUtilHTML;
import ekptg.model.htp.UtilHTMLBean;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.PembelianBean;
import ekptg.model.utils.IUserPegawai;
import ekptg.model.utils.UserPegawaiBean;
import ekptg.model.utils.emel.EmailConfig;


public class FrmPopupEmel extends AjaxBasedModule{
 	/**
	 * 
	 */
	private static final long serialVersionUID = -5137632472454498354L;
	private IUserPegawai iUserPegawai = null;  
	private IUtilHTML iUtil = null;
	private IPembelian iPembelian = null;
	static Logger myLog = Logger.getLogger(ekptg.report.htp.utiliti.FrmPopupEmel.class);
//	FrmPopupPilihPegawaiReportData dbReportHtp = new FrmPopupPilihPegawaiReportData();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdfb = new SimpleDateFormat("mmm");
	SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");

	@Override
	public String doTemplate2() throws Exception {		
		//HttpSession session = request.getSession();		
		String vm = "";
		//Vector detailPegawai = new Vector();
		//detailPegawai.clear();
		
		submit = getParam("command");
		
		boolean hantarEmel = false;
		//String selectNoFail = getParam("selectNoFail");
		Date date = new Date();
		
		String idpermohonan = getParam("idpermohonan");
		myLog.info("idpermohonan="+idpermohonan);
		String report = getParam("report");	//emelperingatan
		String flagReport = getParam("flagReport");
		String txtKandungan = getParam("txtKandungan");	
//		String nama_pegawai = "";
//		String id_jawatan = "";
//		String jawatan = "";
//		String emel = "";		
		//package helper
		context.put("Utils", new ekptg.helpers.Utils());
		HtpPermohonan permohonan = getIPermohonan().findPermohonan(idpermohonan,"");
		
		EmailConfig ec = new EmailConfig();
		String tajukEmel = "Peringatan Bayaran "+"("+permohonan.getPermohonan().getPfdFail().getNoFail()+")";
		context.put("tajukEmel", tajukEmel);
		
		Hashtable<String,String> paj = getMaklumatPajakan(idpermohonan);

		txtKandungan ="\n Adalah dengan hormatnya saya merujuk kepada perkara di atas.\n\n";
		txtKandungan +="2.\t  Adalah dimaklumkan mengikut <style isBold='true' pdfFontName='Helvetica-Bold'>Klausa 3(1) perjanjian Pajakan</style>, "+
				" pihak tuan dikehendaki menjelaskan bayaran pajakan tanah sebelum atau pada " +paj.get("tarikhPatut")+" setiap tahun."+
				" Sehubungan dengan itu, pihak tuan diingatkan supaya menjelaskan bayaran pajakan bagi"+
				" tahun " +sdfy.format(date) +" sebanyak <style isBold='true' pdfFontName='Helvetica-Bold'>RM" +paj.get("kadarPajakan")+
				" sebelum atau pada " +paj.get("bulanPatut")+" "+ sdfy.format(date) +"</style> dengan"+
				" mengemukakan cek di atas nama <style isBold='true' pdfFontName='Helvetica-Bold'>Pesuruhjaya Tanah persekutuan</style>. "
				+ "Bayaran pajakan selepas <style isBold='true' pdfFontName='Helvetica-Bold'>" +paj.get("bulanPatut")+" "+ sdfy.format(date)+ "</style> akan menyebabkan pihak "+
				" tuan dikenakan kadar faedah sebanyak 8% setahun selaras dengan <style isBold='true' pdfFontName='Helvetica-Bold'>Klausa 3(3) perjanjian Pajakan</style>."+
				"";
    	txtKandungan += ec.getFooter();
		
		myLog.info("submit="+submit);
    	if (submit.equals("peringatanbayaran")) {
    		//
    		hantarEmel = ec.sendTo("roslizakaria@gmail.com", tajukEmel, txtKandungan);

    	}else {
    	}
   	
		myLog.info("txtKandungan="+txtKandungan);
		context.put("kandungan", txtKandungan);
		context.put("idpermohonan", idpermohonan);
		context.put("report", report);
//		context.put("kandungan", flagReport);
		context.put("hantarEmel", hantarEmel);

		//context.put("emelPegawai", emel);	
	
		//skrin
		vm = "app/htp/utiliti/emel/index.jsp";
		return vm;
		
	}//close template
	
	public Hashtable<String,String> getMaklumatPajakan(String idPermohonan) throws Exception {
		Db db = null;
		Hashtable<String,String> h = null;
		try {
			//Hashtable<String,String> beanMaklumatPajakan = new Hashtable<String,String>();
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "SELECT A.NOTIFIKASI_PERINGATAN, A.ID_PAJAKAN, A.TARIKH_TANDATANGAN, A.TARIKH_MULA_PAJAKAN, A.TARIKH_TAMATPAJAKAN" +
				", A.KADAR_PAJAKAN, A.TEMPOH_PAJAKAN, A.KADAR_CUKAI, C.KETERANGAN, A.CARA_BAYAR" +
				", A.KATEGORI_CUKAI, A.TARIKH_PATUT_BAYAR,A.CATATAN,A.DENDA,A.FLAG_AKTIF " +
				" ,f.no_fail "
				+ "FROM TBLHTPPAJAKAN A, TBLRUJCARABAYAR C,tblpermohonan p, tblpfdfail f " +
				" WHERE A.CARA_BAYAR = C.ID_CARABAYAR(+) "
				+ " and p.id_permohonan = a.id_permohonan (+)"
				+ " and p.id_fail=f.id_fail " +
				" AND A.ID_PERMOHONAN = '" + idPermohonan + "'";
			myLog.info("getMaklumatPajakan:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("noFail", rs.getString("no_fail") == null ? "" : rs.getString("no_fail"));
				h.put("idPajakan", rs.getString("ID_PAJAKAN") == null ? "" : rs.getString("ID_PAJAKAN"));
				h.put("tarikhTandatangan", rs.getDate("TARIKH_TANDATANGAN") == null ? "" : sdf.format(rs.getDate("TARIKH_TANDATANGAN")));
				h.put("tarikhMula", rs.getDate("TARIKH_MULA_PAJAKAN") == null ? "" : sdf.format(rs.getDate("TARIKH_MULA_PAJAKAN")));
				h.put("tarikhTamat", rs.getDate("TARIKH_TAMATPAJAKAN") == null ? "" : sdf.format(rs.getDate("TARIKH_TAMATPAJAKAN")));
				h.put("tempoh", rs.getString("TEMPOH_PAJAKAN") == null ? "0" : rs.getString("TEMPOH_PAJAKAN"));
				h.put("kadar", rs.getString("KADAR_CUKAI") == null ? "0" : rs.getString("KADAR_CUKAI"));
				h.put("kadarPajakan", rs.getString("KADAR_PAJAKAN") == null ? "0" : rs.getString("KADAR_PAJAKAN"));
				h.put("caraBayar", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("idCaraBayar", rs.getString("CARA_BAYAR") == null ? "0" : rs.getString("CARA_BAYAR"));
				h.put("katCukai", rs.getString("KATEGORI_CUKAI") == null ? "" : rs.getString("KATEGORI_CUKAI"));
				h.put("notifikasiPeringatan", String.valueOf(
					rs.getString("NOTIFIKASI_PERINGATAN") == null ? "0" : rs.getInt("NOTIFIKASI_PERINGATAN")));
				h.put("tarikhPatut", rs.getDate("TARIKH_PATUT_BAYAR") == null ? "" : sdf.format(rs.getDate("TARIKH_PATUT_BAYAR")));
				h.put("bulanPatut", rs.getDate("TARIKH_PATUT_BAYAR") == null ? "" : sdfb.format(rs.getDate("TARIKH_PATUT_BAYAR")));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("status", rs.getString("FLAG_AKTIF") == null ? "Y" : rs.getString("FLAG_AKTIF"));
				h.put("denda", rs.getString("DENDA") == null ? "" : rs.getString("DENDA"));
				//beanMaklumatPajakan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
		return h;
		
	}
	
	private IUserPegawai getIUP(){
		if(iUserPegawai== null)
			iUserPegawai = new UserPegawaiBean();
				
		return iUserPegawai;
			
	}		
	
	private IUtilHTML getHTPUtil(){
		if(iUtil==null){
			iUtil = new UtilHTMLBean();
		}
		return iUtil;
			
	}
    
	private IPembelian getIPermohonan(){
		if (iPembelian==null){
			iPembelian=new PembelianBean();
		}
		return iPembelian;
	} 	

}//close class
