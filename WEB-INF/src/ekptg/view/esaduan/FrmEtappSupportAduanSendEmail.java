package ekptg.view.esaduan;

import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.lang.WordUtils;

import ekptg.intergration.XEkptgEmailSender;
import ekptg.model.esaduan.EtappSupportAduanData;

public class FrmEtappSupportAduanSendEmail extends AjaxBasedModule  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5543404791726565412L;

	@Override
	public String doTemplate2() throws Exception {
		String vm = "";
		String status = "";
		try {
			XEkptgEmailSender email = XEkptgEmailSender.getInstance();
			Hashtable data = new Hashtable();

			email.FROM = "etappsupport@jkptg.gov.my";// email_from;
			email.SUBJECT = "Peringatan untuk Membuat Ulasan Teknikal";// subject_emel;
//			email.MESSAGE = setParaGraphMohonBayaran(data);// setMessageTable(emailType,nama_projek)+""+setParaGraph(emailType,"",nofail,tarikh_permohonan,nama_kementerian,nama_projek);
			System.out.println(email.MESSAGE);
			email.MULTIPLE_RECIEPIENT = new String[2];
			email.MULTIPLE_RECIEPIENT[0] = "aniza@ilaunch.com.my"; // "mohd.syahrir@ilaunch.com.my";//data.get("emel");//"etappsupport@jkptg.gov.my";
			email.MULTIPLE_RECIEPIENT[1] = "fadzlisham@ilaunch.com.my";
			status = email.sendEmail();
			
			context.put("status", status);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "fail";
		}
		vm = "app/ppt/frmPopupHantarEmail.jsp";
		return vm;
	}

	public static String setParaGraphSelesai(Hashtable get_aduan, String tarikh_selesai, String username,Db db) throws Exception {

		EtappSupportAduanData logic = new EtappSupportAduanData();
		
		StringBuffer bff = new StringBuffer();
		
		String nama_pengadu = "";
		String nama_jawatan = "";
		String emel = "";
		String no_tel = "";
		String seksyen = "";
		String pejabat = "";
		String negeri = "";
		String daerah = "";
		String log_aduan = "";
		String nama_status = "";
		String tarikh_aduan_hantar = "";
		String no_fail = "";
		String nama_skrin ="";
		String aduan = ""; 
		String id_sumberaduan = "";
		String id_jenisaduan = "";
		String id_jenismodulesaduan = "";
		String sumberLog = "";
		String jenisLog = "";
		String modulUrusan = "";
		
		Vector list_jenisaduan = null;		
		Vector list_sumberaduan = null;		
		Vector list_users = null;
		Vector list_module = null;		
		
		log_aduan = (String)get_aduan.get("log_aduan");
		nama_status = (String)get_aduan.get("nama_status");
		tarikh_aduan_hantar = (String)get_aduan.get("tarikh_aduan_hantar");
		no_fail = (String)get_aduan.get("no_fail");
		nama_skrin =(String)get_aduan.get("nama_skrin");
		aduan = (String)get_aduan.get("aduan"); 
		id_sumberaduan = (String)get_aduan.get("id_sumberaduan");
		id_jenisaduan = (String)get_aduan.get("id_jenisaduan");
		id_jenismodulesaduan = (String)get_aduan.get("id_jenismodulesaduan");
		
		list_users = logic.getListUsers((String)get_aduan.get("user_id"),"","","","","",db);
				
		Hashtable get_user;
		if(list_users.size()!=0){
			get_user = (Hashtable) list_users.get(0);
			nama_pengadu = (String)get_user.get("user_name");
			nama_jawatan = (String)get_user.get("nama_jawatan");
			emel = (String)get_user.get("emel");
			no_tel = (String)get_user.get("no_tel");
			seksyen = (String)get_user.get("nama_seksyen");
			pejabat = (String)get_user.get("nama_pejabat");
			negeri = (String)get_user.get("nama_negeri");
			daerah = (String)get_user.get("nama_daerah");
		}
		
		list_module = logic.getListModule(id_jenismodulesaduan,db);
		Hashtable get_module;
		for(int i=0; i<list_module.size();i++){
			get_module = (Hashtable) list_module.get(i);
			
			if(id_jenismodulesaduan.equals((String)get_module.get("id_jenismodulesaduan"))){
				if((String)get_module.get("kod_module")!=null && (String)get_module.get("kod_module")!=""){
					modulUrusan = (String)get_module.get("kod_module");
				}
				modulUrusan = modulUrusan + (String)get_module.get("jenis_module");
			}
		}
		
		list_jenisaduan = logic.getListJenisAduan(db);
		Hashtable get_jenisaduan;
		for(int i=0; i<list_jenisaduan.size();i++){
			get_jenisaduan = (Hashtable) list_jenisaduan.get(i);
			
			if(id_jenisaduan.equals((String)get_jenisaduan.get("id_jenisaduan"))){
				jenisLog = (String)get_jenisaduan.get("jenis_aduan");
			}
		}
		
		list_sumberaduan = logic.getListSumberAduan(db);
		Hashtable get_sumberaduan;
		for(int i=0; i<list_sumberaduan.size();i++){
			get_sumberaduan = (Hashtable) list_sumberaduan.get(i);
			
			if(id_sumberaduan.equals((String)get_sumberaduan.get("id_sumberaduan"))){
				sumberLog = (String)get_sumberaduan.get("nama_sumber");
			}
		}
		
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Salam Tuan/Puan,");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Untuk makluman, pihak kami masih belum menerima sebarang maklumbalas dari pihak tuan berhubung Ulasan Teknikal bagi permohonan di bawah:");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nama Pengadu		:  "+nama_pengadu);
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Jawatan				: "+nama_jawatan);
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Emel				: "+emel);
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No. Tel				: "+no_tel);
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Seksyen				: "+seksyen);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pejabat				: "+pejabat);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Negeri				: "+negeri); 
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Daerah				: "+daerah);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Log				: "+log_aduan);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status Log				: "+nama_status);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tarikh Log				: "+tarikh_aduan_hantar);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No. Fail				: "+no_fail);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nama Modul/Urusan				: "+modulUrusan);
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nama Skrin				: "+nama_skrin);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Jenis Log				: "+jenisLog);
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sumber Log				: "+sumberLog);
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Keterangan Log				: "+aduan);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tarikh Selesai				: "+tarikh_selesai);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pegawai Terlibat				: "+username);	
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Diharap pihak Tuan/Puan dapat memberikan maklumbalas segera.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sekian, terima kasih.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("<u>webmaster@etapp.gov.my</u>");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Nota: E-mel ini dijana dari sistem eTaPP dan tidak memerlukan balasan.");

		return bff.toString();
	}
	
	public static String setParaGraphDalamTindakann(Hashtable get_aduan, String dari, String hingga, String username,Db db) throws Exception {

		EtappSupportAduanData logic = new EtappSupportAduanData();
		
		StringBuffer bff = new StringBuffer();
		
		String nama_pengadu = "";
		String nama_jawatan = "";
		String emel = "";
		String no_tel = "";
		String seksyen = "";
		String pejabat = "";
		String negeri = "";
		String daerah = "";
		String log_aduan = "";
		String nama_status = "";
		String tarikh_aduan_hantar = "";
		String no_fail = "";
		String nama_skrin ="";
		String aduan = ""; 
		String id_sumberaduan = "";
		String id_jenisaduan = "";
		String id_jenismodulesaduan = "";
		String sumberLog = "";
		String jenisLog = "";
		String modulUrusan = "";
		
		Vector list_jenisaduan = null;		
		Vector list_sumberaduan = null;		
		Vector list_users = null;
		Vector list_module = null;		
		
		log_aduan = (String)get_aduan.get("log_aduan");
		nama_status = (String)get_aduan.get("nama_status");
		tarikh_aduan_hantar = (String)get_aduan.get("tarikh_aduan_hantar");
		no_fail = (String)get_aduan.get("no_fail");
		nama_skrin =(String)get_aduan.get("nama_skrin");
		aduan = (String)get_aduan.get("aduan"); 
		id_sumberaduan = (String)get_aduan.get("id_sumberaduan");
		id_jenisaduan = (String)get_aduan.get("id_jenisaduan");
		id_jenismodulesaduan = (String)get_aduan.get("id_jenismodulesaduan");
		
		list_users = logic.getListUsers((String)get_aduan.get("user_id"),"","","","","",db);
				
		Hashtable get_user;
		if(list_users.size()!=0){
			get_user = (Hashtable) list_users.get(0);
			nama_pengadu = (String)get_user.get("user_name");
			nama_jawatan = (String)get_user.get("nama_jawatan");
			emel = (String)get_user.get("emel");
			no_tel = (String)get_user.get("no_tel");
			seksyen = (String)get_user.get("nama_seksyen");
			pejabat = (String)get_user.get("nama_pejabat");
			negeri = (String)get_user.get("nama_negeri");
			daerah = (String)get_user.get("nama_daerah");
		}
		
		list_module = logic.getListModule(id_jenismodulesaduan,db);
		Hashtable get_module;
		for(int i=0; i<list_module.size();i++){
			get_module = (Hashtable) list_module.get(i);
			
			if(id_jenismodulesaduan.equals((String)get_module.get("id_jenismodulesaduan"))){
				if((String)get_module.get("kod_module")!=null && (String)get_module.get("kod_module")!=""){
					modulUrusan = (String)get_module.get("kod_module");
				}
				modulUrusan = modulUrusan + (String)get_module.get("jenis_module");
			}
		}
		
		list_jenisaduan = logic.getListJenisAduan(db);
		Hashtable get_jenisaduan;
		for(int i=0; i<list_jenisaduan.size();i++){
			get_jenisaduan = (Hashtable) list_jenisaduan.get(i);
			
			if(id_jenisaduan.equals((String)get_jenisaduan.get("id_jenisaduan"))){
				jenisLog = (String)get_jenisaduan.get("jenis_aduan");
			}
		}
		
		list_sumberaduan = logic.getListSumberAduan(db);
		Hashtable get_sumberaduan;
		for(int i=0; i<list_sumberaduan.size();i++){
			get_sumberaduan = (Hashtable) list_sumberaduan.get(i);
			
			if(id_sumberaduan.equals((String)get_sumberaduan.get("id_sumberaduan"))){
				sumberLog = (String)get_sumberaduan.get("nama_sumber");
			}
		}
		
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Salam Tuan/Puan,");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Untuk makluman, pihak kami masih belum menerima sebarang maklumbalas dari pihak tuan berhubung Ulasan Teknikal bagi permohonan di bawah:");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nama Pengadu		:  "+nama_pengadu);
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Jawatan				: "+nama_jawatan);
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Emel				: "+emel);
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No. Tel				: "+no_tel);
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Seksyen				: "+seksyen);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pejabat				: "+pejabat);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Negeri				: "+negeri); 
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Daerah				: "+daerah);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Log				: "+log_aduan);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status Log				: "+nama_status);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tarikh Log				: "+tarikh_aduan_hantar);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No. Fail				: "+no_fail);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nama Modul/Urusan				: "+modulUrusan);
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nama Skrin				: "+nama_skrin);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Jenis Log				: "+jenisLog);
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sumber Log				: "+sumberLog);
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Keterangan Log				: "+aduan);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Jangkamasa				: "+dari+" hingga "+hingga);	
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pegawai Terlibat				: "+username);	
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Diharap pihak Tuan/Puan dapat memberikan maklumbalas segera.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sekian, terima kasih.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("<u>webmaster@etapp.gov.my</u>");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Nota: E-mel ini dijana dari sistem eTaPP dan tidak memerlukan balasan.");

		return bff.toString();
	}

	public static String toInitCap(String param) {
		if (param != null && param.length() > 0) {
			param = param.toLowerCase();
			return WordUtils.capitalizeFully(param);
		} else {
			return "";
		}
	}

}
