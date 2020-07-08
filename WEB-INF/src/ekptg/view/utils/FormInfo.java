
package ekptg.view.utils;

import lebah.portal.AjaxBasedModule;
import org.apache.log4j.Logger;

public class FormInfo extends AjaxBasedModule {

	private final String PATH="portal/";
	private static final long serialVersionUID = 1L;
	private static Logger myLog = Logger.getLogger(ekptg.view.utils.FormInfo.class);
	
	String readability = "";
	String disability = "";
	String idUser = "0"; 
    String idRujukan = "";

	@Override
	public String doTemplate2() throws Exception {		
		//GET DEFAULT PARAM
	    String vm = PATH+"myetappInfo.jsp";
	    String jenisInfo = getParam("jenis");
	    String namaImej = "";
		myLog.info("jenisInfo="+jenisInfo);
		
		if (jenisInfo.equals("tanah")){
			namaImej = "Jenis Hakmilik.jpg";
		}
		if (jenisInfo.equals("hakmilik")){
			namaImej = "No.Hakmilik.jpg";
		}
		if (jenisInfo.equals("lot")){
			namaImej = "No.Lot.jpg";
		}
		if (jenisInfo.equals("bahagian")){
			namaImej = "Bahagian Simati.jpg";
		}
		if (jenisInfo.equals("maklumat_tanah")) {
			namaImej = "Maklumat tanah.jpg";
		}
		if (jenisInfo.equals("geran_kereta")) {
			namaImej = "Geran Kereta.jpg";
		}
		if (jenisInfo.equals("daftar")) {
			namaImej = "No.Daftar.jpg";
		}
		if (jenisInfo.equals("agensi")) {
			namaImej = "Agensi.jpg";
		}
		if (jenisInfo.equals("akaun")) {
			namaImej = "No.Akaun.jpg";
		}
		if (jenisInfo.equals("sijil")) {
			namaImej = "No.Sijil.jpg";
		}
		if (jenisInfo.equals("tarikh")) {
			namaImej = "tarikh_masa.jpg";
		}
		if (jenisInfo.equals("tempat")) {
			namaImej = "Tempat kematian.jpg";
		}
		if (jenisInfo.equals("sebab")) {
			namaImej = "Sebab Kematian.jpg";
		}
		if (jenisInfo.equals("alamat")) {
			namaImej = "alamat terakhir.jpg";
		}
		if (jenisInfo.equals("perjanjian")) {
			namaImej = "Perjanjian.jpg";
		}
		this.context.put("namaInfo", namaImej);
	   	return vm;
		
	}

	
}
