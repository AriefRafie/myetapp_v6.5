
package ekptg.view.ppk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;
import lebah.util.Util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmSenaraiHutangData;

/**
 * @author Mohd Faizal
 *
 */
public class FrmSenaraiHutangView extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;
    private static Logger myLog = Logger.getLogger(ekptg.view.ppk.FrmSenaraiHutangView.class);	
	FrmSenaraiHutangData logic = new FrmSenaraiHutangData();
	//private String templateDir = "app/ppk/eHutang";

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}
		String templateDir = "app/ppk/eHutang";
		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String command = getParam("command");
//		System.out.println("command="+command);
		context.put("command", command);
		context.put("templateDir", templateDir);
		Vector<Hashtable<String,String>> senaraiUtama = null;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		String portal_role = (String)session.getAttribute("myrole");
		context.put("role", portal_role);
		//try {
			if ("hapusDokumen".equals(command)) {
				String idDokumen = getParam("idDokumen");
				logic.hapusDokumen(idDokumen);
				
				Hashtable hutang = logic.getMaklumatHutang(getParam("idSenaraiHutang"));				
				context.put("hutang", hutang);
				Hashtable penghutang = logic.getMaklumatPenghutang((String) hutang.get("idHutang"));				
				context.put("penghutang", penghutang);
				Vector listDokumenSokongan = logic.getSenaraiDokumenSokongan(getParam("idSenaraiHutang"));
				this.context.put("listDokumenSokongan", listDokumenSokongan);
				
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong((String) hutang.get("idNegeri")), ""," onChange=\"doChangeNegeri();\""));
				context.put("selectBandar", HTML.SelectBandarByNegeri((String) hutang.get("idNegeri"), "socBandar", Long.parseLong((String) hutang.get("idBandar")), ""));
				
				vm = "/dokumenSokongan.jsp";				
			
			} else if ("refreshDokumenMuatNaik".equals(command)) {
				String idHutang = getParam("idHutang");
				String idSenaraiHutang = getParam("idSenaraiHutang");
				String namaDokumen = getParam("namaDokumen");
				
				Hashtable hutang = logic.getMaklumatHutang(getParam("idSenaraiHutang"));				
				context.put("hutang", hutang);
				Hashtable penghutang = logic.getMaklumatPenghutang((String) hutang.get("idHutang"));				
				context.put("penghutang", penghutang);
				Vector listDokumenSokongan = logic.getSenaraiDokumenSokongan(getParam("idSenaraiHutang"));
				this.context.put("listDokumenSokongan", listDokumenSokongan);
				
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong((String) hutang.get("idNegeri")), ""," onChange=\"doChangeNegeri();\""));
				context.put("selectBandar", HTML.SelectBandarByNegeri((String) hutang.get("idNegeri"), "socBandar", Long.parseLong((String) hutang.get("idBandar")), ""));
				
				vm = "/dokumenSokongan.jsp";				
			
			} else if ("muatNaikDokumen".equals(command)) {
				String idHutang = getParam("idHutang");
				String idSenaraiHutang = getParam("idSenaraiHutang");
				String namaDokumen = getParam("namaDokumen");
				
				uploadFiles(idHutang, idSenaraiHutang, namaDokumen, session);
				
				vm = "/refreshDokumenSokongan.jsp";				
			
			} else if ("daftarHutang".equals(command)) {	
				Hashtable penghutang = logic.getMaklumatPenghutang(getParam("idHutang"));				
				context.put("penghutang", penghutang);
				context.remove("hutang");
				context.put("alamat1", "");
				context.put("tarikhPerjanjian","");
				//context.put("nama", getParam("nama"));
				context.put("noPengenalan", getParam("noPengenalan"));
				context.put("alamat2", "");
				context.put("alamat3", "");
				context.put("poskod", "");
				//context.put("idNegeri", getParam("socNegeri"));
				//context.put("idBandar", getParam("socBandar"));
				context.put("noTelefon", "");		
				context.put("emel", "");
				context.put("flag_salin", "");
				context.put("jenisHutang", "");
				context.put("noAkaun", "");
				context.put("butiranHutang", "");
				context.put("nilaiHutang", "");
				context.put("bakiHutang", "");
				context.put("statusHutang", "");
				context.put("tarikhTamatTempohHutang", "");
				context.put("tarikhPerjanjian", "");
				context.put("tarikhSelesaiHutang", "");
				context.put("catatan", "");
				context.put("catatanInsuransHutang", "");
				context.put("insuransHutang", getParam(""));
				Hashtable namaPejabat = logic.namaPejabat(USER_ID_SYSTEM);
				context.put("namaPejabat", namaPejabat);
				context.put("disabled", "");
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong("99999"), ""," onChange=\"doChangeNegeri();\""));
				context.put("selectBandar", HTML.SelectBandarByNegeri("9999", "socBandar", Long.parseLong("9999"), ""));
				
				vm = "/start.jsp";
				
			} else if ("simpanHutang".equals(command)) {
				Hashtable penghutang = logic.getMaklumatPenghutang(getParam("idHutang"));				
				context.put("penghutang", penghutang);
//				System.out.println("***simpanHutang***");
				Hashtable hutang = logic.simpanHutang(getParam("idHutang"), getParam("nama"), getParam("noPengenalan"), getParam("alamat1"), getParam("alamat2"), 
									getParam("noTelefon"), getParam("alamat3"), getParam("poskod"), getParam("socNegeri"), getParam("socBandar"), getParam("jenisHutang"), getParam("noAkaun"),
									getParam("butiranHutang"), getParam("nilaiHutang"), getParam("bakiHutang"), getParam("tarikhTamatTempohHutang"), getParam("tarikhPerjanjian"), 
									getParam("catatan"), getParam("insuransHutang"), getParam("catatanInsuransHutang"), session);
				context.put("hutang", hutang);
				context.put("cmdSimpan", "simpan");
				context.put("disabled", "disabled");
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong((String) hutang.get("idNegeri")), "disabled"," onChange=\"doChangeNegeri();\""));
				context.put("selectBandar", HTML.SelectBandarByNegeri((String) hutang.get("idNegeri"), "socBandar", Long.parseLong((String) hutang.get("idBandar")), "disabled"));
				
				vm = "/start.jsp";
				
			} else if ("kemaskiniHutang".equals(command)) {
//				System.out.println("kemaskiniHutang ");
				Hashtable penghutang = logic.getMaklumatPenghutang(getParam("idHutang"));				
				context.put("penghutang", penghutang);
				Hashtable hutang = logic.kemaskiniHutang(getParam("idSenaraiHutang"), getParam("nama"), getParam("noTelefon"), getParam("noPengenalan"), getParam("alamat1"), getParam("alamat2"), 
								getParam("alamat3"), getParam("poskod"), getParam("socNegeri"), getParam("socBandar"), getParam("jenisHutang"), getParam("noAkaun"),
								getParam("butiranHutang"), getParam("nilaiHutang"), getParam("bakiHutang"), getParam("tarikhTamatTempohHutang"), getParam("tarikhPerjanjian"), getParam("catatan"), getParam("statusHutang"), 
								getParam("tarikhSelesaiHutang"), getParam("insuransHutang"), getParam("catatanInsuransHutang"), session);
				context.put("hutang", hutang);
				context.put("disabled", "");
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong((String) hutang.get("idNegeri")), ""," onChange=\"doChangeNegeri();\""));
				context.put("selectBandar", HTML.SelectBandarByNegeri((String) hutang.get("idNegeri"), "socBandar", Long.parseLong((String) hutang.get("idBandar")), ""));
				
				vm = "/start.jsp";
				
			} else if ("kemaskiniHutang2".equals(command)) {
//				System.out.println("kemaskiniHutang ");
				Hashtable penghutang = logic.getMaklumatPenghutang(getParam("idHutang"));				
				context.put("penghutang", penghutang);
				Hashtable hutang = logic.kemaskiniHutang(getParam("idSenaraiHutang"), getParam("nama"), getParam("noTelefon"), getParam("noPengenalan"), getParam("alamat1"), getParam("alamat2"), 
								getParam("alamat3"), getParam("poskod"), getParam("socNegeri"), getParam("socBandar"), getParam("jenisHutang"), getParam("noAkaun"),
								getParam("butiranHutang"), getParam("nilaiHutang"), getParam("bakiHutang"), getParam("tarikhTamatTempohHutang"), getParam("tarikhPerjanjian"), getParam("catatan"), 
								getParam("statusHutang"), getParam("tarikhSelesaiHutang"), 
								getParam("insuransHutang"), getParam("catatanInsuransHutang"), session);
				context.put("hutang", hutang);
				context.put("disabled", "disabled");
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong((String) hutang.get("idNegeri")), "disabled"," onChange=\"doChangeNegeri();\""));
				context.put("selectBandar", HTML.SelectBandarByNegeri((String) hutang.get("idNegeri"), "socBandar", Long.parseLong((String) hutang.get("idBandar")), "disabled"));
				
				vm = "/start.jsp";
				
			} else if ("doChangeStatusHutang".equals(command)) {
//				System.out.println("doChangeStatusHutang ");
				Hashtable hutang = logic.getMaklumatHutang(getParam("idSenaraiHutang"));
				hutang.put("nama", getParam("nama"));
				hutang.put("noPengenalan", getParam("noPengenalan"));
				hutang.put("alamat1", getParam("alamat1"));
				hutang.put("alamat2", getParam("alamat2"));
				hutang.put("alamat3", getParam("alamat3"));
				hutang.put("poskod", getParam("poskod"));
				hutang.put("idNegeri", getParam("socNegeri"));
				hutang.put("idBandar", getParam("socBandar"));
				hutang.put("noTelefon", getParam("noTelefon"));		
				hutang.put("emel", getParam("emel"));
				hutang.put("flag_salin", getParam("flag_salin"));
				hutang.put("jenisHutang", getParam("jenisHutang"));
				hutang.put("noAkaun", getParam("noAkaun"));
				hutang.put("butiranHutang", getParam("butiranHutang"));
				hutang.put("nilaiHutang", getParam("nilaiHutang") == null ? "0.00" : Util.formatDecimal(Double.valueOf(Utils.RemoveComma(getParam("nilaiHutang")))));
				hutang.put("bakiHutang", getParam("bakiHutang") == null ? "0.00" : Util.formatDecimal(Double.valueOf(Utils.RemoveComma(getParam("bakiHutang")))));
				hutang.put("statusHutang", getParam("statusHutang"));
				hutang.put("tarikhTamatTempohHutang", getParam("tarikhTamatTempohHutang"));
				hutang.put("tarikhPerjanjian", getParam("tarikhPerjanjian"));
				hutang.put("tarikhSelesaiHutang", getParam("tarikhSelesaiHutang"));
				hutang.put("catatan", getParam("catatan"));
				context.put("hutang", hutang);
				Hashtable penghutang = logic.getMaklumatPenghutang((String) hutang.get("idHutang"));				
				context.put("penghutang", penghutang);				
				Vector listDokumenSokongan = logic.getSenaraiDokumenSokongan(getParam("id"));
				this.context.put("listDokumenSokongan", listDokumenSokongan);
				context.put("disabled", "");
				
				String idNegeri = "99999";
				if (!getParam("socNegeri").equals("")){
					idNegeri = getParam("socNegeri");
				}
				String idBandar = "99999";
				if (!getParam("socBandar").equals("")){
					idBandar = getParam("socBandar");
				}
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
				context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), ""));
				
				vm = "/start.jsp";
				
			} else if ("doChangeInsuransHutang".equals(command)) {
//				System.out.println("doChangeInsuransHutang ");
				Hashtable hutang = logic.getMaklumatHutang(getParam("idSenaraiHutang"));
				hutang.put("nama", getParam("nama"));
				hutang.put("noPengenalan", getParam("noPengenalan"));
				hutang.put("alamat1", getParam("alamat1"));
				hutang.put("alamat2", getParam("alamat2"));
				hutang.put("alamat3", getParam("alamat3"));
				hutang.put("poskod", getParam("poskod"));
				hutang.put("idNegeri", getParam("socNegeri"));
				hutang.put("idBandar", getParam("socBandar"));
				hutang.put("noTelefon", getParam("noTelefon"));		
				hutang.put("emel", getParam("emel"));
				hutang.put("flag_salin", getParam("flag_salin"));
				hutang.put("jenisHutang", getParam("jenisHutang"));
				hutang.put("noAkaun", getParam("noAkaun"));
				hutang.put("butiranHutang", getParam("butiranHutang"));
				hutang.put("nilaiHutang", getParam("nilaiHutang") == null ? "0.00" : Util.formatDecimal(Double.valueOf(Utils.RemoveComma(getParam("nilaiHutang")))));
				hutang.put("bakiHutang", getParam("bakiHutang") == null ? "0.00" : Util.formatDecimal(Double.valueOf(Utils.RemoveComma(getParam("bakiHutang")))));
				hutang.put("statusHutang", getParam("statusHutang"));
				hutang.put("tarikhTamatTempohHutang", getParam("tarikhTamatTempohHutang"));
				hutang.put("tarikhPerjanjian", getParam("tarikhPerjanjian"));
				hutang.put("tarikhSelesaiHutang", getParam("tarikhSelesaiHutang"));
				hutang.put("catatan", getParam("catatan"));
				hutang.put("catatanInsuransHutang", getParam("catatanInsuransHutang"));
				hutang.put("insuransHutang", getParam("insuransHutang"));
				context.put("hutang", hutang);
				Hashtable penghutang = logic.getMaklumatPenghutang((String) hutang.get("idHutang"));				
				context.put("penghutang", penghutang);				
				Vector listDokumenSokongan = logic.getSenaraiDokumenSokongan(getParam("id"));
				this.context.put("listDokumenSokongan", listDokumenSokongan);
				context.put("disabled", "");
				
				String idNegeri = "99999";
				if (!getParam("socNegeri").equals("")){
					idNegeri = getParam("socNegeri");
				}
				String idBandar = "99999";
				if (!getParam("socBandar").equals("")){
					idBandar = getParam("socBandar");
				}
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
				context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), ""));
				
				vm = "/start.jsp";

			} else if ("doChangeInsuransHutang2".equals(command)) {
		//		System.out.println("doChangeInsuransHutang2 ");
				context.put("nama", getParam("nama"));
				context.put("noPengenalan", getParam("noPengenalan"));
				context.put("alamat1", getParam("alamat1"));
				context.put("alamat2", getParam("alamat2"));
				context.put("alamat3", getParam("alamat3"));
				context.put("poskod", getParam("poskod"));
				context.put("idNegeri", getParam("socNegeri"));
				context.put("idBandar", getParam("socBandar"));
				context.put("noTelefon", getParam("noTelefon"));		
				context.put("emel", getParam("emel"));
				context.put("flag_salin", getParam("flag_salin"));
				context.put("jenisHutang", getParam("jenisHutang"));
				context.put("noAkaun", getParam("noAkaun"));
				context.put("butiranHutang", getParam("butiranHutang"));
				context.put("nilaiHutang", getParam("nilaiHutang") );
				context.put("bakiHutang", getParam("bakiHutang") );
				context.put("statusHutang", getParam("statusHutang"));
				context.put("tarikhTamatTempohHutang", getParam("tarikhTamatTempohHutang"));
				context.put("tarikhPerjanjian", getParam("tarikhPerjanjian"));
				context.put("tarikhSelesaiHutang", getParam("tarikhSelesaiHutang"));
				context.put("catatan", getParam("catatan"));
				context.put("catatanInsuransHutang", getParam("catatanInsuransHutang"));
				context.put("insuransHutang", getParam("insuransHutang"));
				
				String idNegeri = "99999";
				if (!getParam("socNegeri").equals("")){
					idNegeri = getParam("socNegeri");
				}
				String idBandar = "99999";
				if (!getParam("socBandar").equals("")){
					idBandar = getParam("socBandar");
				}
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
				context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), ""));
				
				vm = "/start.jsp";
				
			}else if ("SimpanDalamPenghutang".equals(command)) {
				Hashtable h = new Hashtable();
//				System.out.println("SimpanDalamPenghutang ");
				String id_SenaraiHutang = (getParam("id_SenaraiHutang"));
//				System.out.println("id_SenaraiHutang " + id_SenaraiHutang);
				String no_kp = (getParam("no_kp"));
				Vector id_Simati = logic.getMaklumatSimati(getParam("id_SenaraiHutang"));	
				//System.out.println("id_Simati " + id_Simati);
				Vector listHutang = logic.getSenaraiHutang2(id_SenaraiHutang);
				//Vector listHutangNama = logic.getSenaraiHutang2Nama(id_SenaraiHutang);
				
				Hashtable namaHash = (Hashtable) listHutang.get(0);
				Hashtable idSimatiHash = (Hashtable) id_Simati.get(0);
				
				String idPermohonansimati2, idSimati, nama, jenisHutang, bakiHutang, noAkaun, noPengenalan, alamat1, alamat2, alamat3, poskod, negeri, bandar, noTelefon;
				idSimati = 	idSimatiHash.get("ID_SIMATI").toString();
				
				Vector id_Permohonansimati = logic.getMaklumatPermohonanSimati(idSimati);
				Hashtable idPermohonansimati = (Hashtable) id_Permohonansimati.get(0);
				
//				System.out.println("*****listHutangNama1**** ");
				idPermohonansimati2 = idPermohonansimati.get("ID_PERMOHONANSIMATI").toString();
				nama = 	namaHash.get("nama").toString();
				jenisHutang = 	namaHash.get("jenisHutang").toString();
				jenisHutang = 	namaHash.get("jenisHutang").toString();
				noPengenalan = 	namaHash.get("noPengenalan").toString();
				alamat1 = 	namaHash.get("alamat1").toString();
				alamat2 = 	namaHash.get("alamat2").toString();
				alamat3 = 	namaHash.get("alamat3").toString();
				poskod = 	namaHash.get("poskod").toString();
				negeri = 	namaHash.get("id_negeri").toString();
				bandar = 	namaHash.get("id_bandar").toString();
				noTelefon = namaHash.get("noTelefon").toString();
				noAkaun = 	namaHash.get("noAkaun").toString();
				bakiHutang = namaHash.get("bakiHutang").toString();
				bakiHutang = bakiHutang.replaceAll(",", "");
				
				h.put("idPermohonansimati", idPermohonansimati2);
				h.put("idSimati", idSimati);
				h.put("nama", nama);
				h.put("jenisHutang", jenisHutang);
				h.put("noPengenalan", noPengenalan);
				h.put("alamat1", alamat1);
				h.put("alamat2", alamat2);
				h.put("alamat3", alamat3);
				h.put("poskod", poskod);
				h.put("negeri", negeri);
				h.put("bandar", bandar);
				h.put("noTelefon", noTelefon);
				h.put("noAkaun", noAkaun);
				h.put("bakiHutang", bakiHutang);
				h.put("id_SenaraiHutang",id_SenaraiHutang);
				
//				System.out.println("*****bakiHutang**** "+bakiHutang);
				String idPerbicaraan = logic.simpanHutang(h);
				
				vm = "/start.jsp";
				
			} else if ("SimpanDalamPenghutangSemua2".equals(command)) {
//				System.out.println("SimpanDalamPenghutangSemua2 ");
				String count2 =  (getParam("count")); 
//				System.out.println("count2 = "+count2);
				int count = Integer.parseInt(count2);
//				System.out.println("count = "+count);
				String[] salin2 = this.request.getParameterValues("salin");
//				System.out.println("*********");
				if (salin2 != null) {
					for (int i = 0; i<salin2.length; i++)
					{
						System.out.println("salin = "+salin2[i]);
					}
				}
							
				vm = "/start.jsp";
				
			}else if ("SimpanDalamPenghutangSemua".equals(command)) {
				Hashtable h = new Hashtable();
//				System.out.println("SimpanDalamPenghutangSemua ");
				String count2 =  (getParam("count")); 
//				System.out.println("count2 = "+count2);
				int count = Integer.parseInt(count2);
//				System.out.println("count = "+count);
				String[] salin2 = this.request.getParameterValues("salin");
//				System.out.println("*********");
				String id_SenaraiHutang = null;
				if (salin2 != null) {
					for (int i = 0; i<salin2.length; i++)
					{
//						System.out.println("salin = "+salin2[i]);
						id_SenaraiHutang = salin2[i];	
						Vector id_Simati = logic.getMaklumatSimati(id_SenaraiHutang);	
						//System.out.println("id_Simati " + id_Simati);
						Vector listHutang = logic.getSenaraiHutang2(id_SenaraiHutang);
						//Vector listHutangNama = logic.getSenaraiHutang2Nama(id_SenaraiHutang);
//						System.out.println("*****listHutang***** ");
						Hashtable namaHash = (Hashtable) listHutang.get(0);
//						System.out.println("*****listHutang1***** ");
						Hashtable idSimatiHash = (Hashtable) id_Simati.get(0);
//						System.out.println("*****listHutang2***** ");

						String idPermohonansimati2, idSimati, nama, jenisHutang, bakiHutang, noAkaun, noPengenalan, alamat1, alamat2, alamat3, poskod, negeri, bandar, noTelefon;
						idSimati = 	idSimatiHash.get("ID_SIMATI").toString();
						
						Vector id_Permohonansimati = logic.getMaklumatPermohonanSimati(idSimati);
						Hashtable idPermohonansimati = (Hashtable) id_Permohonansimati.get(0);
						
//						System.out.println("*****listHutangNama1**** ");
						idPermohonansimati2 = idPermohonansimati.get("ID_PERMOHONANSIMATI").toString();
						nama = 	namaHash.get("nama").toString();
						jenisHutang = 	namaHash.get("jenisHutang").toString();
						jenisHutang = 	namaHash.get("jenisHutang").toString();
						noPengenalan = 	namaHash.get("noPengenalan").toString();
						alamat1 = 	namaHash.get("alamat1").toString();
						alamat2 = 	namaHash.get("alamat2").toString();
						alamat3 = 	namaHash.get("alamat3").toString();
						poskod = 	namaHash.get("poskod").toString();
						negeri = 	namaHash.get("id_negeri").toString();
						bandar = 	namaHash.get("id_bandar").toString();
						noTelefon = namaHash.get("noTelefon").toString();
						noAkaun = 	namaHash.get("noAkaun").toString();
						bakiHutang = namaHash.get("bakiHutang").toString();
						bakiHutang = bakiHutang.replaceAll(",", "");
						
						h.put("idPermohonansimati", idPermohonansimati2);
						h.put("idSimati", idSimati);
						h.put("nama", nama);
						h.put("jenisHutang", jenisHutang);
						h.put("noPengenalan", noPengenalan);
						h.put("alamat1", alamat1);
						h.put("alamat2", alamat2);
						h.put("alamat3", alamat3);
						h.put("poskod", poskod);
						h.put("negeri", negeri);
						h.put("bandar", bandar);
						h.put("noTelefon", noTelefon);
						h.put("noAkaun", noAkaun);
						h.put("bakiHutang", bakiHutang);
						h.put("id_SenaraiHutang",id_SenaraiHutang);
						
//						System.out.println("*****bakiHutang**** "+bakiHutang);
						String idPerbicaraan = logic.simpanHutang(h);
					}
				}
				//String id_SenaraiHutang = (getParam("id_SenaraiHutang"));
				//System.out.println("id_SenaraiHutang " + id_SenaraiHutang);
				//Vector myID = logic.getMaklumatMyID(getParam("id_SenaraiHutang"));
				
				/*
				Hashtable myIDHash = (Hashtable) myID.get(0);
				
				String myID2;
				myID2 = myIDHash.get("NO_PENGENALAN_BARU").toString();
				System.out.println(" myID2 " + myID2);
				
				Vector checkHutangviaMyID = logic.getSenaraiHutangviaMyID(myID2);
				
				if (checkHutangviaMyID.size() != 0)
				{
					for (int i = 0; i < checkHutangviaMyID.size(); i++) {
						System.out.println("checkHutangviaMyID ["+i+ "]" + checkHutangviaMyID);
						Hashtable checkHutangviaMyIDhash = (Hashtable) checkHutangviaMyID.get(i);	
						String id_SenaraiHutangx = checkHutangviaMyIDhash.get("idSenaraiHutang").toString();
						
						Vector id_Simati = logic.getMaklumatSimati(id_SenaraiHutangx);	
						System.out.println("*****listHutangNama000000**** ");
						Vector listHutang = logic.getSenaraiHutang2(id_SenaraiHutangx);
						System.out.println("*****listHutangNama100000**** ");
						Hashtable namaHash = (Hashtable) listHutang.get(0);
						Hashtable idSimatiHash = (Hashtable) id_Simati.get(0);
						System.out.println("*****listHutangNama200000**** ");
						String idPermohonansimati2, idSimati, nama, jenisHutang, bakiHutang, noAkaun, noPengenalan, alamat1, alamat2, alamat3, poskod, negeri, bandar, noTelefon;
						idSimati = 	idSimatiHash.get("ID_SIMATI").toString();
						
						Vector id_Permohonansimati = logic.getMaklumatPermohonanSimati(idSimati);
						System.out.println("*****listHutangNama300000**** ");
						
						Hashtable idPermohonansimati = (Hashtable) id_Permohonansimati.get(0);
						System.out.println("*****listHutangNama1**** ");
						
						idPermohonansimati2 = idPermohonansimati.get("ID_PERMOHONANSIMATI").toString();
						nama = 	namaHash.get("nama").toString();
						jenisHutang = 	namaHash.get("jenisHutang").toString();
						jenisHutang = 	namaHash.get("jenisHutang").toString();
						noPengenalan = 	namaHash.get("noPengenalan").toString();
						alamat1 = 	namaHash.get("alamat1").toString();
						alamat2 = 	namaHash.get("alamat2").toString();
						alamat3 = 	namaHash.get("alamat3").toString();
						poskod = 	namaHash.get("poskod").toString();
						negeri = 	namaHash.get("id_negeri").toString();
						bandar = 	namaHash.get("id_bandar").toString();
						noTelefon = namaHash.get("noTelefon").toString();
						noAkaun = 	namaHash.get("noAkaun").toString();
						bakiHutang = namaHash.get("bakiHutang").toString();
						bakiHutang = bakiHutang.replaceAll(",", "");
						
						h.put("idPermohonansimati", idPermohonansimati2);
						h.put("idSimati", idSimati);
						h.put("nama", nama);
						h.put("jenisHutang", jenisHutang);
						h.put("noPengenalan", noPengenalan);
						h.put("alamat1", alamat1);
						h.put("alamat2", alamat2);
						h.put("alamat3", alamat3);
						h.put("poskod", poskod);
						h.put("negeri", negeri);
						h.put("bandar", bandar);
						h.put("noTelefon", noTelefon);
						h.put("noAkaun", noAkaun);
						h.put("bakiHutang", bakiHutang);
						h.put("id_SenaraiHutang",id_SenaraiHutangx);
						
						String idPerbicaraan = logic.simpanHutang(h);
					
					}
					
				}*/
				//context.put("command", "paparPenghutang");
//				System.out.println("*****Read Here**** ");
				//context.put("id", "99181007");
				vm = "/start.jsp";
				
			} else if ("paparHutang".equals(command)) {
//				System.out.println("paparHutang ");
				Hashtable penghutang = logic.getMaklumatPenghutang(getParam("idHutang"));				
				context.put("penghutang", penghutang);
				Hashtable hutang = logic.getMaklumatHutang(getParam("id"));				
				context.put("hutang", hutang);
				//Hashtable maklumathutangPPK = logic.getMaklumatPenghutangPPK(getParam("idHutang"));	
				//context.put("maklumathutangPPK", maklumathutangPPK);		
				Vector listDokumenSokongan = logic.getSenaraiDokumenSokongan(getParam("id"));
				this.context.put("listDokumenSokongan", listDokumenSokongan);
				context.put("disabled", "disabled");
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong((String) hutang.get("idNegeri")), "disabled"," onChange=\"doChangeNegeri();\""));
				context.put("selectBandar", HTML.SelectBandarByNegeri((String) hutang.get("idNegeri"), "socBandar", Long.parseLong((String) hutang.get("idBandar")), "disabled"));				
				
				vm = "/start.jsp";
				
			} else if ("hapusHutang".equals(command)) {
				logic.hapusHutang(getParam("id"));
				Hashtable penghutang = logic.getMaklumatPenghutang(getParam("idHutang"));				
				context.put("penghutang", penghutang);
				Vector listHutang = logic.getSenaraiHutangAgensi(getParam("idHutang"));
				this.context.put("listHutang", listHutang);
				
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong((String) penghutang.get("idNegeri")), "disabled"," onChange=\"doChangeNegeri();\""));
				context.put("selectBandar", HTML.SelectBandarByNegeri((String) penghutang.get("idNegeri"), "socBandar", Long.parseLong((String) penghutang.get("idBandar")), "disabled"));
				
				vm = "/start.jsp";
				
			} else if ("daftarPenghutang".equals(command)) {
				context.remove("penghutang");
				context.remove("flagSemakanNoPengenalanBaru");
				context.remove("semakanNoPengenalanBaruMsg");
				Vector idPejabat = logic.idPejabat(USER_ID_SYSTEM);
				context.put("idPejabat", idPejabat);
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong("99999"), ""," onChange=\"doChangeNegeri();\""));
				context.put("selectBandar", HTML.SelectBandarByNegeri("9999", "socBandar", Long.parseLong("9999"), ""));
				
				vm = "/start.jsp";
				
			} else if ("simpanPenghutang".equals(command)) {
//				System.out.println("Read Here -- simpanPenghutang");
				Hashtable penghutang = logic.simpanPenghutang(getParam("nama"), getParam("noPengenalanBaru"), getParam("noTelefon"), getParam("emel"),
									getParam("catatan"), getParam("alamat1"), getParam("alamat2"), getParam("alamat3"), getParam("poskod"), getParam("socNegeri"),
									getParam("socBandar"), getParam("jabatan"), session, getParam("idPejabat_user"));
				context.put("penghutang", penghutang);
				Vector listHutang = logic.getSenaraiHutang((String) penghutang.get("idHutang"));
				this.context.put("listHutang", listHutang);
				context.put("disabled", "disabled");
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong((String) penghutang.get("idNegeri")), "disabled"," onChange=\"doChangeNegeri();\""));
				context.put("selectBandar", HTML.SelectBandarByNegeri((String) penghutang.get("idNegeri"), "socBandar", Long.parseLong((String) penghutang.get("idBandar")), "disabled"));
				
				Vector maklumathutangPPK = logic.getMaklumatPenghutangPPK((String) penghutang.get("idHutang"));	
				this.context.put("maklumathutangPPK", maklumathutangPPK);
				
				vm = "/start.jsp";
			
			} else if ("kemaskiniPenghutang".equals(command)) {
				Hashtable penghutang = logic.kemaskiniPenghutang(getParam("idHutang"), getParam("nama"), getParam("noTelefon"), getParam("emel"),
									getParam("catatan"), getParam("alamat1"), getParam("alamat2"), getParam("alamat3"), getParam("poskod"), getParam("socNegeri"),
									getParam("socBandar"), getParam("jabatan"), session);
				context.put("penghutang", penghutang);
				Vector listHutang = logic.getSenaraiHutangAgensi(getParam("idHutang"));
				this.context.put("listHutang", listHutang);
				context.put("disabled", "");
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong((String) penghutang.get("idNegeri")), ""," onChange=\"doChangeNegeri();\""));
				context.put("selectBandar", HTML.SelectBandarByNegeri((String) penghutang.get("idNegeri"), "socBandar", Long.parseLong((String) penghutang.get("idBandar")), ""));
				
				vm = "/start.jsp";
			    //untuk simpan maklumat penghutang
			
			}  else if ("kemaskiniPenghutang2".equals(command)) {
				Hashtable penghutang = logic.kemaskiniPenghutang(getParam("idHutang"), getParam("nama"), getParam("noTelefon"), getParam("emel"),
									getParam("catatan"), getParam("alamat1"), getParam("alamat2"), getParam("alamat3"), getParam("poskod"), getParam("socNegeri"),
									getParam("socBandar"), getParam("jabatan"), session);
				context.put("penghutang", penghutang);
				Vector listHutang = logic.getSenaraiHutangAgensi(getParam("idHutang"));
				this.context.put("listHutang", listHutang);
				context.put("disabled", "disabled");
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong((String) penghutang.get("idNegeri")), "disabled"," onChange=\"doChangeNegeri();\""));
				context.put("selectBandar", HTML.SelectBandarByNegeri((String) penghutang.get("idNegeri"), "socBandar", Long.parseLong((String) penghutang.get("idBandar")), "disabled"));
				
				vm = "/start.jsp";
				//untuk simpan maklumat penghutang
			
			}else if ("paparPenghutang".equals(command)) {
//				System.out.println("paparPenghutang");
				String idPenghutang = getParam("id");
				String id2 = id;
				String jenisPenghutang = id.substring(id.length() - 1);
				id = id.substring(0, id.length() - 1);
//				System.out.println("**********id*********** = "+id);
//				System.out.println("**********jenisPenghutang*********** = "+jenisPenghutang);
				Hashtable penghutang = logic.getMaklumatPenghutang(id);				
				context.put("penghutang", penghutang);
				if(portal_role.equals("adminppk")){
					Vector listHutang = logic.getSenaraiHutang(id);
					this.context.put("listHutang", listHutang);
				
				}else{
					Vector listHutang = logic.getSenaraiHutangAgensi(id);
					this.context.put("listHutang", listHutang);
				
				}
//				System.out.println("Read Here --- paparPenghutang");
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong((String) penghutang.get("idNegeri")), "disabled"," onChange=\"doChangeNegeri();\""));
				context.put("selectBandar", HTML.SelectBandarByNegeri((String) penghutang.get("idNegeri"), "socBandar", Long.parseLong((String) penghutang.get("idBandar")), "disabled"));
				context.put("disabled", "disabled");
				context.put("jenisPenghutang", jenisPenghutang);
				context.put("id2", id2);

				Vector maklumathutangPPK = logic.getMaklumatPenghutangPPK((String) penghutang.get("idHutang"));	
				this.context.put("maklumathutangPPK", maklumathutangPPK);
				
				vm = "/start.jsp";
			
			} else if ("hapusPenghutang".equals(command)) {
				logic.hapusPenghutang(getParam("id"));
				Vector listPenghutang = logic.getSenaraiPenghutang(null, null, USER_ID_SYSTEM);
				this.context.put("SenaraiFail", listPenghutang);
				setupPage(session, action, listPenghutang);
				
				context.remove("findNama");
				context.remove("findNoPengenalanBaru");
				context.remove("findNoPengenalanLama");
				context.remove("findNoPengenalanLain");
				
				vm = "/start.jsp";
				
			} else if ("doSemakNoPengenalanBaru".equals(command)) {				
				boolean semakanExistPenghutang = logic.semakanExistPenghutang(getParam("noPengenalanBaru"));
				if (semakanExistPenghutang) {
					//context.put("flagSemakanNoPengenalanBaru", "Y");
					//context.put("semakanNoPengenalanBaruMsg", "MyID Telah Wujud.");
					context.remove("flagSemakanNoPengenalanBaru");
					context.remove("semakanNoPengenalanBaruMsg");
				} else {
					context.remove("flagSemakanNoPengenalanBaru");
					context.remove("semakanNoPengenalanBaruMsg");
				}
				vm = "/semakNoPengenalanBaru.jsp";
				
			} else if ("doChangeNegeri".equals(command)) {				
				context.put("selectBandar", HTML.SelectBandarByNegeri(getParam("socNegeri"), "socBandar", Long.parseLong("9999"), ""));				
				vm = "/selectBandar.jsp";
				
			} else if ("carianPenghutang".equals(command) || "doChanges".equals(command)) {
//				System.out.println("carianPenghutang");				
				String nama = getParam("findNama");
				String noPengenalanBaru = getParam("findNoPengenalanBaru");
				String noPengenalanLama = getParam("findNoPengenalanLama");
				String noPengenalanLain = getParam("findNoPengenalanLain");
				if(portal_role.equals("adminppk")){
					Vector listPenghutang = logic.getSenaraiPenghutangPPK(nama, noPengenalanBaru, USER_ID_SYSTEM);
					this.context.put("SenaraiFail", listPenghutang);
					setupPage(session, action, listPenghutang);
				
				}else{
					Vector listPenghutang = logic.getSenaraiPenghutang(nama, noPengenalanBaru, USER_ID_SYSTEM);
					this.context.put("SenaraiFail", listPenghutang);
					setupPage(session, action, listPenghutang);
				}
				
				Vector listPenghutang2 = logic.getSenaraiPenghutang2(null, null, USER_ID_SYSTEM);
				this.context.put("SenaraiFail2", listPenghutang2);
				
				context.put("findNama", nama);
				context.put("findNoPengenalanBaru", noPengenalanBaru);
				context.put("findNoPengenalanLama", noPengenalanLama);
				context.put("findNoPengenalanLain", noPengenalanLain);
				
				vm = "/start.jsp";
				
			} else if ("carianPenghutangolehPPK".equals(command) || "doChanges".equals(command)) {
//				System.out.println("carianPenghutangolehPPK");
				Vector idpejabat = logic.idPejabat(USER_ID_SYSTEM);
				String nama = getParam("findNama");
				String noPengenalanBaru = getParam("findNoPengenalanBaru");
				String noPengenalanLama = getParam("findNoPengenalanLama");
				String noPengenalanLain = getParam("findNoPengenalanLain");
				//System.out.println("findNoPengenalanBaru = "+noPengenalanBaru);
				//Vector listPenghutang = logic.getSenaraiPenghutang(nama, noPengenalanBaru, USER_ID_SYSTEM);
				//this.context.put("SenaraiFail", listPenghutang);
//				System.out.println("carianPenghutangolehPPK2");
		        if(portal_role.equals("adminppk")){
//		        	System.out.println("--->adminppk");
			        //Vector listPenghutang = logic.getSenaraiPenghutang(null, null, USER_ID_SYSTEM);
			       	senaraiUtama = logic.getSenaraiPenghutangPPK(nama, noPengenalanBaru, USER_ID_SYSTEM);
			       	this.context.put("SenaraiFail", senaraiUtama);
			        	
		        }else{
//		        	System.out.println("--->else");
			       	//Vector listPenghutang2 = logic.getSenaraiPenghutang2(null, null);
			       	senaraiUtama = logic.getSenaraiPenghutang2(null, null, USER_ID_SYSTEM);
			       	this.context.put("SenaraiFail2", senaraiUtama);
			        	
		        }
				setupPage(session, action, senaraiUtama);
				
				context.put("findNama", nama);
				context.put("findNoPengenalanBaru", noPengenalanBaru);
				context.put("findNoPengenalanLama", noPengenalanLama);
				context.put("findNoPengenalanLain", noPengenalanLain);
				context.remove("maklumathutangPPK");
				 
				vm = "/start.jsp";
				
			} else {
//				System.out.println("Read Here --- else");
				Vector idpejabat = logic.idPejabat(USER_ID_SYSTEM);
		        if(portal_role.equals("adminppk")){
		        	//Vector listPenghutang = logic.getSenaraiPenghutang(null, null, USER_ID_SYSTEM);
		        	senaraiUtama = logic.getSenaraiPenghutangPPK(null, null, USER_ID_SYSTEM);
		        	this.context.put("SenaraiFail", senaraiUtama);
		        	
		        }else{
		        	//Vector listPenghutang2 = logic.getSenaraiPenghutang2(null, null);
		        	senaraiUtama = logic.getSenaraiPenghutang2(null, null, USER_ID_SYSTEM);
		        	this.context.put("SenaraiFail2", senaraiUtama);
		        	
		        }
				setupPage(session, action, senaraiUtama);
				//setupPage(session, action, listPenghutang2);
				//context.put("idpejabat", idpejabat);
				context.remove("findNama");
				context.remove("findNoPengenalanBaru");
				context.remove("findNoPengenalanLama");
				context.remove("findNoPengenalanLain");
				context.remove("maklumathutangPPK");
				
				vm = "/start.jsp";
				
			}
//			System.out.println("VM :::::::::: "+templateDir+vm);
			return templateDir + vm;
			/*
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		*/		
	}

	private void uploadFiles(String idHutang, String idSenaraiHutang,
		String namaDokumen, HttpSession session) throws FileUploadException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart != false) {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null)
						&& (!("".equals(item.getName())))) {
					saveData(item, idHutang, idSenaraiHutang, namaDokumen, session);
				}
			}
		}		
	}

	private void saveData(FileItem item, String idHutang,
			String idSenaraiHutang, String namaDokumen, HttpSession session) {
		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id"); 
		
		try {
			db = new Db();
			// TBLPPKDOKUMENSENARAIHUTANG
			long idDokumen = DB.getNextID("TBLPPKDOKUMENSENARAIHUTANG_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO TBLPPKDOKUMENSENARAIHUTANG"
							+ " (ID_DOKUMEN, ID_SENARAIHUTANG, NAMA_DOKUMEN, NAMA_FAIL, CONTENT, JENIS_MIME, ID_MASUK, TARIKH_MASUK)"
							+ " VALUES(?,?,?,?,?,?,?,SYSDATE)");
			ps.setLong(1, idDokumen);
			ps.setLong(2, Long.valueOf(idSenaraiHutang));
			ps.setString(3, namaDokumen);
			ps.setString(4, item.getName());
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, userId);
			ps.executeUpdate();

			con.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		context.put("idSenaraiHutangReload", idSenaraiHutang);
	}

	public void setupPage(HttpSession session, String action, Vector list) {

		try {

			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10: getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage");
			}

			if ("getNext".equals(action)) {
				page++;
			} else if ("getPrevious".equals(action)) {
				page--;
			} else if ("getPage".equals(action)) {
				page = getParamAsInteger("value");
			} else if ("doChangeItemPerPage".equals(action)) {
				itemsPerPage = getParamAsInteger("itemsPerPage");
			}

			Paging paging = new Paging(session, list, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1; // reset page number
			this.context.put("SenaraiFail", paging.getPage(page));
			this.context.put("page", new Integer(page));
			this.context.put("itemsPerPage", new Integer(itemsPerPage));
			this.context.put("totalPages", new Integer(paging.getTotalPages()));
			this.context.put("startNumber", new Integer(paging.getTopNumber()));
			this.context.put("isFirstPage", new Boolean(paging.isFirstPage()));
			this.context.put("isLastPage", new Boolean(paging.isLastPage()));

		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error", e.getMessage());
		}
		
	}
	
	
}
