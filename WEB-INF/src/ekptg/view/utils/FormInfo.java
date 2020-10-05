
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
			namaImej = "Jenis_Hakmilik_n.jpg";
		}
		if (jenisInfo.equals("hakmilik")){
			namaImej = "No.Hakmilik_n.jpg";
		}
		if (jenisInfo.equals("syarat")){
			namaImej = "Syarat_n.jpg";
		}
		if (jenisInfo.equals("lot")){
			namaImej = "No.Lot_n.jpg";
		}
		if (jenisInfo.equals("bahagian")){
			namaImej = "Bahagian_Simati_n.jpg";
		}
		if (jenisInfo.equals("maklumat_tanah")) {
			namaImej = "Maklumat_tanah_n.jpg";
		}
		if (jenisInfo.equals("geran_kereta")) {
			namaImej = "Geran_Kereta_n.jpg";
		}
		if (jenisInfo.equals("daftar")) {
			namaImej = "No.Daftar_n.jpg";
		}
		if (jenisInfo.equals("agensi")) {
			namaImej = "Agensi_n.jpg";
		}
		if (jenisInfo.equals("akaun")) {
			namaImej = "No.Akaun_n.jpg";
		}
		if (jenisInfo.equals("sijil")) {
			namaImej = "No.Sijil_n.jpg";
		}
		if (jenisInfo.equals("tarikh")) {
			namaImej = "tarikh_masa_n.jpg";
		}
		if (jenisInfo.equals("tempat")) {
			namaImej = "Tempat_kematian_n.jpg";
		}
		if (jenisInfo.equals("sebab")) {
			namaImej = "Sebab_Kematian_n.jpg";
		}
		if (jenisInfo.equals("alamat")) {
			namaImej = "alamat_terakhir_n.jpg";
		}
		if (jenisInfo.equals("perjanjian")) {
			namaImej = "Perjanjian_n.jpg";
		}
		if (jenisInfo.equals("Tanah_gsa")) {
			namaImej = "Tanah_gsa.jpg";
		}
		if (jenisInfo.equals("daerah")) {
			namaImej = "daerah.jpg";
		}
		if (jenisInfo.equals("mukim")) {
			namaImej = "mukim.jpg";
		}
		this.context.put("namaInfo", namaImej);
	   	return vm;
		
	}

	
}
