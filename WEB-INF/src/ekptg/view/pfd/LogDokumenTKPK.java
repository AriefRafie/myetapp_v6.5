package ekptg.view.pfd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.pfd.FrmDaftarLogDokumenTKPKData;
import ekptg.model.pfd.FrmLogDokumenTKPKData;

public class LogDokumenTKPK extends AjaxBasedModule {

	private String selectedTab = "0";

	public String doTemplate2() throws Exception

	{

		String vm = "";
		String no_Rujukan_Lain = "";
		String tajuk_Dokumen = "";
		String tarikh_Dokumen = "";
		String tarikh_Diterima_Dihantar = "";
		String status_logdokumen = "";
		String flag_logdokumen = "";
		String idFail = getParam("idFail");
		String idPergerakan = getParam("idPergerakanfail");
		String idSeksyen = getParam("socSeksyenPAR");
		String paparArahan = getParam("paparArahan");
		context.put("idFail", idFail);
		String submit = getParam("command");
		String action1 = getParam("action1");
		String action = getParam("action");
			
			if ("doChangeItemPerPage".equals(action) ||
					"getPage".equals(action)) {
				action1 = action;
			}
		String mode = getParam("mode");
		Vector getPARbyIdSeksyen = null;
        Vector listLampiran = new Vector();
        Vector listPegawai = new Vector();
		Vector listLogDokumenTKPK = new Vector();
		Vector listTKP = new Vector();
		Vector listPAR2 = new Vector();
		Vector listPAR3 = new Vector();
		Vector listPAR4 = new Vector();
		Vector listPAR5 = new Vector();
		Vector listPAR6 = new Vector();
		Vector listMinitArahanPengarah1 = null;	
		Vector listMinitArahanPengarah2 = null;	
		Vector listMinitArahanPengarah3 = null;	
		Vector listPengarahSeksyen = new Vector();
		Vector listPengarahSeksyen1 = new Vector();
		Vector listPengarahSeksyen2 = new Vector();
		Vector listPengarahSeksyen3 = new Vector();
		Vector listPengarahSeksyen4 = new Vector();
		Vector listPengarahSeksyen5 = new Vector();
		Vector listPengarahSeksyen6 = new Vector();
		Vector paparLampiranLogDokumenTKPKDariSeksyenLain = new Vector();
		Vector paparLampiranLogDokumenMasuk = new Vector();
		Vector paparLampiranLogDokumenKeluar = new Vector();
		Vector paparMinitArahanSeksyen1 = new Vector();
		Vector paparMinitArahanSeksyen2 = new Vector();
		Vector paparMinitArahanSeksyen3 = new Vector();
		Vector paparSeksyen = null;
		Vector paparPegawaiSeksyenLainTKPK = null;
		Vector paparSeksyenPAR1 = null;
		Vector paparSeksyenPAR2 = null;
		Vector paparSeksyenPAR3 = null;
		Vector paparSeksyenPAR4 = null;
		Vector paparSeksyenPAR5 = null;
		Vector paparSeksyenPAR6 = null;
		Vector paparPegawai = null;
		Vector paparPegawaiSeksyen1 = null;
		Vector paparPARSeksyen1 = null;
		Vector paparPegawaiSeksyen2 = null;
		Vector paparPARSeksyen2 = null;
		Vector paparPegawaiSeksyen3 = null;
		Vector paparPARSeksyen3 = null;
		Vector paparLogDokumenKPTG = null;
		HttpSession session = this.request.getSession();
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		if ("tabLogDokumenMasukKPTG".equals(action1)) {
			vm = "app/pfd/frmLogDokumenMasukKPTG.jsp";
			context.put("mode", "New");
			context.put("readOnly", "");
			context.put("disabled", "");
			context.put("paparArahan",paparArahan);

			view_Seksyen(session);
			paparSeksyen = FrmLogDokumenTKPKData.getDataSeksyen();
			Hashtable hB = (Hashtable) paparSeksyen.get(0);
			String id_seksyen = String.valueOf(hB.get("id_seksyen"));
			String id_negeri = String.valueOf(hB.get("id_negeri"));
			context.put("idSeksyen", (hB.get("id_seksyen")));
			context.put("idNegeri", (hB.get("id_negeri")));
 			
			
			listTKP(session);
			listTKP = FrmLogDokumenTKPKData.getListTKP();
			this.context.put("SenaraiTKP", listTKP);

		}

		else if ("tabLogDokumenKeluarKPTG".equals(action1)) {
			vm = "app/pfd/frmLogDokumenKeluarKPTG.jsp";
			context.put("mode", "New");
			context.put("readOnly", "");
			context.put("disabled", "");
			context.put("paparArahan",paparArahan);

			view_Seksyen(session);
			paparSeksyen = FrmLogDokumenTKPKData.getDataSeksyen();
			Hashtable hB = (Hashtable) paparSeksyen.get(0);
			String id_seksyen = String.valueOf(hB.get("id_seksyen"));
			String id_negeri = String.valueOf(hB.get("id_negeri"));
			context.put("idSeksyen", (hB.get("id_seksyen")));
			context.put("idNegeri", (hB.get("id_negeri")));

			listTKP(session);
			listTKP = FrmLogDokumenTKPKData.getListTKP();
			this.context.put("SenaraiTKP", listTKP);

		}

		else if ("tabLogDokumenDariSeksyenLainKPTG".equals(action1)) {
			
			String pagemode = getParam("pagemode");
			
			if(pagemode.equals("1")){
				vm = "app/pfd/frmLogDokumenDariSeksyenLainKPTG.jsp";
				context.put("mode", "NewLog");
				context.put("readOnly","readonly");
	  			context.put("disabled","disabled");
	  			context.put("readOnlyTPD","");
	  			context.put("disabledTPD","");
	  			context.put("readOnlyRujuk","");
	  			context.put("disabledRujuk","");
				context.put("paparArahan",paparArahan);

				view_Seksyen(session);
				paparSeksyen = FrmLogDokumenTKPKData.getDataSeksyen();
				Hashtable hB = (Hashtable) paparSeksyen.get(0);
				String id_seksyen = String.valueOf(hB.get("id_seksyen"));
				String id_negeri = String.valueOf(hB.get("id_negeri"));
				context.put("idSeksyen", (hB.get("id_seksyen")));
				context.put("idNegeri", (hB.get("id_negeri")));
				
				String idLogDokumenKPTGSekLain = getParam("idLogDokumenKPTGSekLain");
				String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
				
				context.put("idLogDokumenKPTGSekLain", idLogDokumenKPTGSekLain);
				context.put("idLogDokumenKPTG", idLogDokumenKPTG);
				
				paparLogDokumenKPTG = FrmLogDokumenTKPKData.getDataLogDokumenKPTG(idLogDokumenKPTGSekLain);
	 			
	 			Hashtable hA = (Hashtable)paparLogDokumenKPTG.get(0);	
	 			context.put("idLogDokumenKPTG",hA.get("idLogDokumenKPTG"));
	 			if(hA.get("idMinit").equals("1")){
					    context.put("checkedc1","checked");
				    }
				    else
				    {
				    	context.put("checkedc1","");
				    }
				    if(hA.get("idLaporan").equals("1")){
				    	context.put("checkedc2","checked");
				    }
				    else
				    {
				    	context.put("checkedc2","");
				    }
	 			context.put("jenis_Dokumen",hA.get("jenis_Dokumen"));
	 			context.put("no_Rujukan_Lain",hA.get("no_Rujukan_Lain"));
	 			context.put("pengirim_Dokumen",hA.get("pengirim_Dokumen"));
	 			context.put("tajuk_Dokumen",hA.get("tajuk_Dokumen"));
	 			context.put("penerima_Dokumen",hA.get("penerima_Dokumen"));
	 			context.put("tarikh_Dokumen",hA.get("tarikh_Dokumen"));
	 			context.put("catatan",hA.get("catatan"));

	 			paparLampiranLogDokumenTKPKDariSeksyenLain = FrmLogDokumenTKPKData.getLampiranLogDokumenTKPKDariSeksyenLain(idLogDokumenKPTGSekLain);
	    		this.context.put("SenaraiListLampiranLogDokumenTKPKDariSeksyenLain",paparLampiranLogDokumenTKPKDariSeksyenLain);
	 			
	    		paparPegawaiSeksyenLainTKPK = FrmLogDokumenTKPKData.getDataPegawaiSeksyenLain(idLogDokumenKPTG);
	    		Hashtable h = (Hashtable)paparPegawaiSeksyenLainTKPK.get(0);	
				context.put("nama_Pegawai",h.get("user_name"));
				
	    		
	    		listPegawai(session);
	    		listPegawai = FrmLogDokumenTKPKData.getListPegawai();
	    		this.context.put("SenaraiPegawai",listPegawai);
        	}
			else{
				vm = "app/pfd/frmLogDokumenDariSeksyenLainKPTG.jsp";
				context.put("mode", "View");
				context.put("readOnly","readonly");
	  			context.put("disabled","disabled");
	  			context.put("readOnlyTPD","readonly");
	  			context.put("disabledTPD","disabled");
	  			context.put("readOnlyRujuk","readonly");
	  			context.put("disabledRujuk","disabled");
//				context.put("paparArahan",paparArahan);
//
//				view_Seksyen(session);
//				paparSeksyen = FrmLogDokumenTKPKData.getDataSeksyen();
//				Hashtable hB = (Hashtable) paparSeksyen.get(0);
//				String id_seksyen = String.valueOf(hB.get("id_seksyen"));
//				String id_negeri = String.valueOf(hB.get("id_negeri"));
//				context.put("idSeksyen", (hB.get("id_seksyen")));
//				context.put("idNegeri", (hB.get("id_negeri")));
//				
//				String idLogDokumenKPTGSekLain = getParam("idLogDokumenKPTGSekLain");
//				String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
//				
//				context.put("idLogDokumenKPTGSekLain", idLogDokumenKPTGSekLain);
//				context.put("idLogDokumenKPTG", idLogDokumenKPTG);
//				
//				paparLogDokumenKPTG = FrmLogDokumenTKPKData.getDataLogDokumenKPTG(idLogDokumenKPTGSekLain);
//	 			
//	 			Hashtable hA = (Hashtable)paparLogDokumenKPTG.get(0);	
//	 			context.put("idLogDokumenKPTG",hA.get("idLogDokumenKPTG"));
//	 			if(hA.get("idMinit").equals("1")){
//					    context.put("checkedc1","checked");
//				    }
//				    else
//				    {
//				    	context.put("checkedc1","");
//				    }
//				    if(hA.get("idLaporan").equals("1")){
//				    	context.put("checkedc2","checked");
//				    }
//				    else
//				    {
//				    	context.put("checkedc2","");
//				    }
//	 			context.put("jenis_Dokumen",hA.get("jenis_Dokumen"));
//	 			context.put("no_Rujukan_Lain",hA.get("no_Rujukan_Lain"));
//	 			context.put("pengirim_Dokumen",hA.get("pengirim_Dokumen"));
//	 			context.put("tajuk_Dokumen",hA.get("tajuk_Dokumen"));
//	 			context.put("penerima_Dokumen",hA.get("penerima_Dokumen"));
//	 			context.put("tarikh_Dokumen",hA.get("tarikh_Dokumen"));
//	 			context.put("catatan",hA.get("catatan"));
//
//	 			paparLampiranLogDokumenTKPKDariSeksyenLain = FrmLogDokumenTKPKData.getLampiranLogDokumenTKPKDariSeksyenLain(idLogDokumenKPTGSekLain);
//	    		this.context.put("SenaraiListLampiranLogDokumenTKPKDariSeksyenLain",paparLampiranLogDokumenTKPKDariSeksyenLain);
//	 			
//	    		paparPegawaiSeksyenLainTKPK = FrmLogDokumenTKPKData.getDataPegawaiSeksyenLain(idLogDokumenKPTG);
//	    		Hashtable h = (Hashtable)paparPegawaiSeksyenLainTKPK.get(0);	
//				context.put("nama_Pegawai",h.get("user_name"));
//				
//	    		
//	    		listPegawai(session);
//	    		listPegawai = FrmLogDokumenTKPKData.getListPegawai();
//	    		this.context.put("SenaraiPegawai",listPegawai);
        	}

		}
		
		else if ("simpanLogDokumenMasukKPTG".equals(action1)) {
			vm = "app/pfd/frmLogDokumenMasukKPTG.jsp";
			// context.put("pagemode",0);
			context.put("mode", "View");
			context.put("readOnly", "readonly");
			context.put("disabled", "disabled");
			context.put("paparArahan", "false");

			String idLogDokumenKPTG = simpanLogDokumenMasuk(session);
			// view_LogDokumen(session);
			paparLogDokumenKPTG = FrmLogDokumenTKPKData
					.getDataLogKPTGDokumenMasuk(idLogDokumenKPTG);

			Hashtable h = (Hashtable) paparLogDokumenKPTG.get(0);
			context.put("idLogDokumenKPTG", h.get("idLogDokumenKPTG"));
			context.put("jenis_Dokumen", h.get("jenis_Dokumen"));
			context.put("no_Rujukan_Lain", h.get("no_Rujukan_Lain"));
			context.put("tajuk_Dokumen", h.get("tajuk_Dokumen"));
			context.put("pengirim_Dokumen", h.get("pengirim_Dokumen"));
			context.put("dirujuk_Kepada", h.get("dirujuk_Kepada"));
			context.put("tarikh_Dokumen", h.get("tarikh_Dokumen"));
			context.put("tarikh_Diterima_Dihantar", h.get("tarikh_Diterima_Dihantar"));
			context.put("dirujuk_Kepada", h.get("dirujuk_Kepada"));
			context.put("status_LogDokumen", h.get("status_LogDokumen"));
			if (h.get("idMinit").equals("1")) {
				context.put("checkedc1", "checked");
			} else {
				context.put("checkedc1", "");
			}
			if (h.get("idLaporan").equals("1")) {
				context.put("checkedc2", "checked");
			} else {
				context.put("checkedc2", "");
			}
			
			tajuk_Dokumen = String.valueOf(h.get("tajuk_Dokumen"));
 			String tarikh_Dokumen_Diterima = String.valueOf(h.get("tarikh_Diterima_Dihantar"));
 			String noRujukanLain = String.valueOf(h.get("no_Rujukan_Lain"));
 			String ID_TKP = String.valueOf(h.get("ID_TKP"));

			downloadDraf(idLogDokumenKPTG);

			listTKP = FrmLogDokumenTKPKData.getListTKP(idLogDokumenKPTG);
			this.context.put("SenaraiTKP", listTKP);

			paparLampiranLogDokumenMasuk = FrmLogDokumenTKPKData
					.getListLampiranLogDokumenMasuk(idLogDokumenKPTG);
			this.context.put("SenaraiListLampiranLogDokumenMasuk",
					paparLampiranLogDokumenMasuk);
			
			String idLogDokumenKPTGSekLain = getParam("idLogDokumenKPTGSekLain");
			context.put("idLogDokumenKPTG", idLogDokumenKPTG);
			context.put("idLogDokumenKPTGSekLain", idLogDokumenKPTGSekLain);
			
//    		String emailRecipient = getEmail(ID_TKP);
//    		
//    		EkptgEmailSender email = EkptgEmailSender.getInstance();
//    		email.FROM ="etapp_webmaster@ekptg.gov.my";
//    		email.SUBJECT ="Dokumen baru telah didaftarkan - "+noRujukanLain+".";
//    		email.MESSAGE ="TAJUK : <B>'"+tajuk_Dokumen+"'</B>."+
//    					   "<br><br>Tuan/Puan,"+
//    					   "<br><br>Permohonan : Dokumen <B>"+noRujukanLain+"</B> telah diterima pada : <B>"+tarikh_Dokumen_Diterima+"</B> dan telah "+
//    					   "sedia untuk semakan dan pengesahan dari pihak tuan/puan."+
//    					   "<br><br>Sila <i><b>login</b></i> masuk ke <b>www.etapp.gov.my</b> untuk semakan serta pengesahan dari pihak tuan/puan."+
//    					   "<br><br>Sekian,Terima Kasih."+
//    					   "<br><b>webmaster@etapp.gov.my</b>"+
//    					   "<br><br>Nota : Email ini adalah dijana oleh sistem eTapp dan tidak perlu dibalas.";
//
//    		email.MULTIPLE_RECIEPIENT = new String[1];
//    		email.MULTIPLE_RECIEPIENT[0]=emailRecipient;
//
//    		email.TO_CC = new String[3];
//    		email.TO_CC[0]  = "cipon.it@gmail.com";
//    		email.TO_CC[1]  = "rasul@hla-group.com";
//    		email.TO_CC[2]  = "rizuan@hla-group.com";
//    		email.sendEmail();

		}

		else if ("simpanLogDokumenKeluarKPTG".equals(action1)) {
			vm = "app/pfd/frmLogDokumenKeluarKPTG.jsp";
			// context.put("pagemode",0);
			context.put("mode", "View");
			context.put("readOnly", "readonly");
			context.put("disabled", "disabled");
			context.put("paparArahan", "false");

			String idLogDokumenKPTG = simpanLogDokumenKeluar(session);
			paparLogDokumenKPTG = FrmLogDokumenTKPKData.getDataLogKPTGDokumenKeluar(idLogDokumenKPTG);

			Hashtable h = (Hashtable) paparLogDokumenKPTG.get(0);
			context.put("idLogDokumenKPTG", h.get("idLogDokumenKPTG"));
			context.put("jenis_Dokumen", h.get("jenis_Dokumen"));
			context.put("jenis_Penghantaran", h.get("jenis_Penghantaran"));
			context.put("no_Rujukan_Lain", h.get("no_Rujukan_Lain"));
			context.put("tajuk_Dokumen", h.get("tajuk_Dokumen"));
			context.put("penerima_Dokumen", h.get("penerima_Dokumen"));
			context.put("tarikh_Dokumen", h.get("tarikh_Dokumen"));
			context.put("tarikh_Diterima_Dihantar", h.get("tarikh_Diterima_Dihantar"));
			// context.put("dirujuk_Kepada",h.get("dirujuk_Kepada"));
			context.put("status_LogDokumen", h.get("status_LogDokumen"));
			if (h.get("idMinit").equals("1")) {
				context.put("checkedc1", "checked");
			} else {
				context.put("checkedc1", "");
			}
			if (h.get("idLaporan").equals("1")) {
				context.put("checkedc2", "checked");
			} else {
				context.put("checkedc2", "");
			}

			downloadDraf(idLogDokumenKPTG);

			listTKP = FrmLogDokumenTKPKData
					.getListPengirimKeluar(idLogDokumenKPTG);
			this.context.put("SenaraiTKP", listTKP);

			paparLampiranLogDokumenKeluar = FrmLogDokumenTKPKData
					.getListLampiranLogDokumenMasuk(idLogDokumenKPTG);
			this.context.put("SenaraiListLampiranLogDokumenKeluar",
					paparLampiranLogDokumenKeluar);

		}

		else if ("papar".equals(action1)) {

			String user_role = (String) session.getAttribute("_portal_role");

			context.put("user_Role", user_role);

			String pagemode = getParam("pagemode");
			String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
			String idLogDokumenKPTGSekLain = getParam("idLogDokumenKPTGSekLain");
			context.put("idLogDokumenKPTG", idLogDokumenKPTG);
			context.put("idLogDokumenKPTGSekLain", idLogDokumenKPTGSekLain);
			
			if (pagemode.equals("1")) {
				vm = "app/pfd/frmLogDokumenMasukKPTG.jsp";
				// context.put("pagemode",0);
				context.put("mode", "View");
				context.put("readOnly", "readonly");
				context.put("disabled", "disabled");
				context.put("paparArahan", paparArahan);

				paparLogDokumenKPTG = FrmLogDokumenTKPKData
						.getDataLogKPTGDokumenMasuk(idLogDokumenKPTG);

				Hashtable h = (Hashtable) paparLogDokumenKPTG.get(0);
				context.put("idLogDokumenKPTG", h.get("idLogDokumenKPTG"));
				context.put("jenis_Dokumen", h.get("jenis_Dokumen"));
				context.put("no_Rujukan_Lain", h.get("no_Rujukan_Lain"));
				context.put("tajuk_Dokumen", h.get("tajuk_Dokumen"));
				context.put("pengirim_Dokumen", h.get("pengirim_Dokumen"));
				context.put("tarikh_Dokumen", h.get("tarikh_Dokumen"));
				context.put("tarikh_Diterima_Dihantar", h
						.get("tarikh_Diterima_Dihantar"));
				context.put("dirujuk_Kepada", h.get("dirujuk_Kepada"));
				context.put("status_LogDokumen", h.get("status_LogDokumen"));
				if (h.get("idMinit").equals("1")) {
					context.put("checkedc1", "checked");
				} else {
					context.put("checkedc1", "");
				}
				if (h.get("idLaporan").equals("1")) {
					context.put("checkedc2", "checked");
				} else {
					context.put("checkedc2", "");
				}

				listTKP = FrmLogDokumenTKPKData.getListTKP(idLogDokumenKPTG);
				this.context.put("SenaraiTKP", listTKP);

				paparLampiranLogDokumenMasuk = FrmLogDokumenTKPKData
						.getListLampiranLogDokumenMasuk(idLogDokumenKPTG);
				this.context.put("SenaraiListLampiranLogDokumenMasuk",
						paparLampiranLogDokumenMasuk);
				
	 			paparLampiranLogDokumenTKPKDariSeksyenLain = FrmLogDokumenTKPKData.getLampiranLogDokumenDariSeksyenLainKPTG(idLogDokumenKPTGSekLain);
	    		this.context.put("SenaraiListLampiranLogDokumenTKPKDariSeksyenLain",paparLampiranLogDokumenTKPKDariSeksyenLain);
				
				listMinitArahanPengarah1(session);
        		listMinitArahanPengarah1 = FrmLogDokumenTKPKData.getlistMinitArahanKetuaPengarah1();
        		context.put("SenaraiMinitArahanPengarah1",listMinitArahanPengarah1);
        		Hashtable h1 = (Hashtable) listMinitArahanPengarah1.get(0);
				if(h1.get("pegawaiMenerima").equals(""))
				{
					context.put("modeLevel","0");
					context.put("hidden1", "true");
					
				}
				else
				{
					context.put("modeLevel","1");
					context.put("hidden1", "false");
					context.put("action1", "papar");
					context.put("SenaraiMinitArahanPengarah1",listMinitArahanPengarah1);
				}
        		
        		
				listMinitArahanPengarah2(session);
        		listMinitArahanPengarah2 = FrmLogDokumenTKPKData.getlistMinitArahanKetuaPengarah2();
        		context.put("SenaraiMinitArahanPengarah2",listMinitArahanPengarah2);
        		Hashtable h2 = (Hashtable) listMinitArahanPengarah2.get(0);
				if(h2.get("pegawaiMenerima").equals(""))
				{
					context.put("modeLevel","0");
					context.put("hidden2", "true");
				}
				else
				{
					context.put("modeLevel","1");
					context.put("hidden2", "false");
					context.put("action1", "papar");
					context.put("SenaraiMinitArahanPengarah2",listMinitArahanPengarah2);
				}
        		
				listMinitArahanPengarah3(session);
        		listMinitArahanPengarah3 = FrmLogDokumenTKPKData.getlistMinitArahanKetuaPengarah3();
        		context.put("SenaraiMinitArahanPengarah3",listMinitArahanPengarah3);
        		Hashtable h3 = (Hashtable) listMinitArahanPengarah3.get(0);
				if(h3.get("pegawaiMenerima").equals(""))
				{
					context.put("modeLevel","0");
					context.put("hidden3", "true");
				}
				else
				{
					context.put("modeLevel","1");
					context.put("hidden3", "false");
					context.put("action1", "papar");
					context.put("SenaraiMinitArahanPengarah3",listMinitArahanPengarah3);
				}


			}

			else {
				vm = "app/pfd/frmLogDokumenKeluarKPTG.jsp";
				context.put("mode", "View");
				context.put("readOnly", "readonly");
				context.put("disabled", "disabled");
				context.put("paparArahan", paparArahan);

				paparLogDokumenKPTG = FrmLogDokumenTKPKData
						.getDataLogKPTGDokumenKeluar(idLogDokumenKPTG);

				Hashtable h = (Hashtable) paparLogDokumenKPTG.get(0);
				context.put("idLogDokumenKPTG", h.get("idLogDokumenKPTG"));
				context.put("jenis_Dokumen", h.get("jenis_Dokumen"));
				context.put("jenis_Penghantaran", h.get("jenis_Penghantaran"));
				context.put("no_Rujukan_Lain", h.get("no_Rujukan_Lain"));
				context.put("tajuk_Dokumen", h.get("tajuk_Dokumen"));

				context.put("penerima_Dokumen", h.get("penerima_Dokumen"));
				context.put("tarikh_Dokumen", h.get("tarikh_Dokumen"));
				context.put("tarikh_Diterima_Dihantar", h
						.get("tarikh_Diterima_Dihantar"));
				context.put("dirujuk_Kepada", h.get("dirujuk_Kepada"));
				context.put("status_LogDokumen", h.get("status_LogDokumen"));
				if (h.get("idMinit").equals("1")) {
					context.put("checkedc1", "checked");
				} else {
					context.put("checkedc1", "");
				}
				if (h.get("idLaporan").equals("1")) {
					context.put("checkedc2", "checked");
				} else {
					context.put("checkedc2", "");
				}

				listTKP = FrmLogDokumenTKPKData
						.getListIDPenghantar(idLogDokumenKPTG);
				this.context.put("SenaraiTKP", listTKP);

				paparLampiranLogDokumenKeluar = FrmLogDokumenTKPKData
						.getListLampiranLogDokumenKeluar(idLogDokumenKPTG);
				this.context.put("SenaraiListLampiranLogDokumenKeluar",
						paparLampiranLogDokumenKeluar);

			}
		}
		
		else if ("kemaskiniLogDokumenMasukKPTG".equals(action1)){
			vm = "app/pfd/frmLogDokumenMasukKPTG.jsp";
			context.put("mode", "Update");
			context.put("readOnly", "");
			context.put("disabled", "");
			context.put("paparArahan","false");

			view_Seksyen(session);
			paparSeksyen = FrmLogDokumenTKPKData.getDataSeksyen();
			Hashtable hB = (Hashtable) paparSeksyen.get(0);
			String id_seksyen = String.valueOf(hB.get("id_seksyen"));
			String id_negeri = String.valueOf(hB.get("id_negeri"));
			context.put("idSeksyen", (hB.get("id_seksyen")));
			context.put("idNegeri", (hB.get("id_negeri")));

			listTKP(session);
			listTKP = FrmLogDokumenTKPKData.getListTKP();
			this.context.put("SenaraiTKP", listTKP);
		}
		
		else if ("kemaskiniLogDokumenKeluarKPTG".equals(action1)){
			vm = "app/pfd/frmLogDokumenKeluarKPTG.jsp";
			context.put("mode", "Update");
			context.put("readOnly", "");
			context.put("disabled", "");
			context.put("paparArahan","false");

			view_Seksyen(session);
			paparSeksyen = FrmLogDokumenTKPKData.getDataSeksyen();
			Hashtable hB = (Hashtable) paparSeksyen.get(0);
			String id_seksyen = String.valueOf(hB.get("id_seksyen"));
			String id_negeri = String.valueOf(hB.get("id_negeri"));
			context.put("idSeksyen", (hB.get("id_seksyen")));
			context.put("idNegeri", (hB.get("id_negeri")));

			listTKP(session);
			listTKP = FrmLogDokumenTKPKData.getListTKP();
			this.context.put("SenaraiTKP", listTKP);
		}
		
		else if ("kemaskiniLogDokumenDariSeksyenLainKPTG".equals(action1)){
			vm = "app/pfd/frmLogDokumenDariSeksyenlainKPTG.jsp";
			context.put("mode", "Update");
			context.put("readOnly", "");
			context.put("disabled", "");
			context.put("paparArahan","false");

			view_Seksyen(session);
			paparSeksyen = FrmLogDokumenTKPKData.getDataSeksyen();
			Hashtable hB = (Hashtable) paparSeksyen.get(0);
			String id_seksyen = String.valueOf(hB.get("id_seksyen"));
			String id_negeri = String.valueOf(hB.get("id_negeri"));
			context.put("idSeksyen", (hB.get("id_seksyen")));
			context.put("idNegeri", (hB.get("id_negeri")));

			listTKP(session);
			listTKP = FrmLogDokumenTKPKData.getListTKP();
			this.context.put("SenaraiTKP", listTKP);
		}
		
		else if ("updateLogDokumenDariSeksyenLainKPTG".equals(action1)){
			vm = "app/pfd/frmLogDokumenDariSeksyenLainKPTG.jsp";
			context.put("mode", "View");
			context.put("readOnly","readonly");
  			context.put("disabled","disabled");
  			context.put("readOnlyTPD","readonly");
  			context.put("disabledTPD","disabled");
  			context.put("readOnlyRujuk","readonly");
  			context.put("disabledRujuk","disabled");
			context.put("paparArahan",paparArahan);

			view_Seksyen(session);
			paparSeksyen = FrmLogDokumenTKPKData.getDataSeksyen();
			Hashtable hB = (Hashtable) paparSeksyen.get(0);
			String id_seksyen = String.valueOf(hB.get("id_seksyen"));
			String id_negeri = String.valueOf(hB.get("id_negeri"));
			context.put("idSeksyen", (hB.get("id_seksyen")));
			context.put("idNegeri", (hB.get("id_negeri")));
			
			String idLogDokumenKPTGSekLain = getParam("idLogDokumenKPTGSekLain");
			String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
			
			context.put("idLogDokumenKPTGSekLain", idLogDokumenKPTGSekLain);
			context.put("idLogDokumenKPTG", idLogDokumenKPTG);
			
			updateLogDokumenSeksyenLainKPTG(session);
			
			paparLogDokumenKPTG = FrmLogDokumenTKPKData.getDataLogDokumenKPTG(idLogDokumenKPTGSekLain);
 			
 			Hashtable hA = (Hashtable)paparLogDokumenKPTG.get(0);	
 			context.put("idLogDokumenKPTG",hA.get("idLogDokumenKPTG"));
 			if(hA.get("idMinit").equals("1")){
				    context.put("checkedc1","checked");
			    }
			    else
			    {
			    	context.put("checkedc1","");
			    }
			    if(hA.get("idLaporan").equals("1")){
			    	context.put("checkedc2","checked");
			    }
			    else
			    {
			    	context.put("checkedc2","");
			    }
 			context.put("jenis_Dokumen",hA.get("jenis_Dokumen"));
 			context.put("no_Rujukan_Lain",hA.get("no_Rujukan_Lain"));
 			context.put("pengirim_Dokumen",hA.get("pengirim_Dokumen"));
 			context.put("tajuk_Dokumen",hA.get("tajuk_Dokumen"));
 			context.put("penerima_Dokumen",hA.get("penerima_Dokumen"));
 			context.put("tarikh_Dokumen",hA.get("tarikh_Dokumen"));
 			context.put("tarikh_Dokumen_Diterima",hA.get("tarikh_Diterima_Dihantar"));
 			context.put("catatan",hA.get("catatan"));

 			paparLampiranLogDokumenTKPKDariSeksyenLain = FrmLogDokumenTKPKData.getLampiranLogDokumenTKPKDariSeksyenLain(idLogDokumenKPTGSekLain);
    		this.context.put("SenaraiListLampiranLogDokumenTKPKDariSeksyenLain",paparLampiranLogDokumenTKPKDariSeksyenLain);
 			
    		paparPegawaiSeksyenLainTKPK = FrmLogDokumenTKPKData.getDataPegawaiSeksyenLain(idLogDokumenKPTG);
    		Hashtable h = (Hashtable)paparPegawaiSeksyenLainTKPK.get(0);	
			context.put("nama_Pegawai",h.get("user_name"));
			
    		listPegawai(session);
    		listPegawai = FrmLogDokumenTKPKData.getListPegawai();
    		this.context.put("SenaraiPegawai",listPegawai);
		}

		else if ("tambahLampiran".equals(action1)){
				
				vm = "app/pfd/frmPaparLampiranLogDokumenKPTG.jsp";
					
				String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
				context.put("idLogDokumenKPTG", idLogDokumenKPTG);

				paparLogDokumenKPTG = FrmLogDokumenTKPKData.getDataLogKPTGDokumenKeluar(idLogDokumenKPTG);
				
				Hashtable hA = (Hashtable)paparLogDokumenKPTG.get(0);	
	 			context.put("idLogDokumenKPTG",hA.get("idLogDokumenKPTG"));
	 			if(hA.get("idMinit").equals("1")){
					    context.put("checkedc1","checked");
				    }
				    else
				    {
				    	context.put("checkedc1","");
				    }
				    if(hA.get("idLaporan").equals("1")){
				    	context.put("checkedc2","checked");
				    }
				    else
				    {
				    	context.put("checkedc2","");
				    }
	 			context.put("jenis_Dokumen",hA.get("jenis_Dokumen"));
	 			context.put("no_Rujukan_Lain",hA.get("no_Rujukan_Lain"));
	 			context.put("pengirim_Dokumen",hA.get("pengirim_Dokumen"));
	 			context.put("tajuk_Dokumen",hA.get("tajuk_Dokumen"));
	 			context.put("penerima_Dokumen",hA.get("penerima_Dokumen"));
	 			context.put("tarikh_Dokumen",hA.get("tarikh_Dokumen"));
	 			context.put("tarikh_Dokumen_Diterima",hA.get("tarikh_Diterima_Dihantar"));
	 			context.put("catatan",hA.get("catatan"));

	    	 listLampiran(session);
	    	 listLampiran = FrmLogDokumenTKPKData.getListLampiran();
	    	 context.put("SenaraiLampiran",listLampiran);
	    	 context.put("completed",false);
		}
		
		else if ("simpanLampiran".equals(action1)){
			vm = "app/pfd/frmPaparLampiranLogDokumenKPTG.jsp";
			
			String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
			context.put("idLogDokumenKPTG", idLogDokumenKPTG);
 	 		
			uploadLampiran(idLogDokumenKPTG);

			paparLogDokumenKPTG = FrmLogDokumenTKPKData.getDataLogKPTGDokumenKeluar(idLogDokumenKPTG);
			
			Hashtable hA = (Hashtable)paparLogDokumenKPTG.get(0);	
 			context.put("idLogDokumenKPTG",hA.get("idLogDokumenKPTG"));
 			if(hA.get("idMinit").equals("1")){
				    context.put("checkedc1","checked");
			    }
			    else
			    {
			    	context.put("checkedc1","");
			    }
			    if(hA.get("idLaporan").equals("1")){
			    	context.put("checkedc2","checked");
			    }
			    else
			    {
			    	context.put("checkedc2","");
			    }
 			context.put("jenis_Dokumen",hA.get("jenis_Dokumen"));
 			context.put("no_Rujukan_Lain",hA.get("no_Rujukan_Lain"));
 			context.put("pengirim_Dokumen",hA.get("pengirim_Dokumen"));
 			context.put("tajuk_Dokumen",hA.get("tajuk_Dokumen"));
 			context.put("penerima_Dokumen",hA.get("penerima_Dokumen"));
 			context.put("tarikh_Dokumen",hA.get("tarikh_Dokumen"));
 			context.put("tarikh_Dokumen_Diterima",hA.get("tarikh_Diterima_Dihantar"));
 			context.put("catatan",hA.get("catatan"));

    	 listLampiran(session);
    	 listLampiran = FrmLogDokumenTKPKData.getListLampiran();
    	 context.put("SenaraiLampiran",listLampiran);
    	 context.put("completed",false);
   		
		} 
			
		else if ("hapusLampiran".equals(action1)){
			
			vm = "app/pfd/frmPaparLampiranLogDokumenKPTG.jsp";
			
			String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
			context.put("idLogDokumenKPTG", idLogDokumenKPTG);
			
			hapusLampiran(session);
			
			vm = "app/pfd/frmPaparLampiranLogDokumen.jsp";

			  
			paparLogDokumenKPTG = FrmLogDokumenTKPKData.getDataLogKPTGDokumenKeluar(idLogDokumenKPTG);
			
			Hashtable hA = (Hashtable)paparLogDokumenKPTG.get(0);	
 			context.put("idLogDokumenKPTG",hA.get("idLogDokumenKPTG"));
 			if(hA.get("idMinit").equals("1")){
				    context.put("checkedc1","checked");
			    }
			    else
			    {
			    	context.put("checkedc1","");
			    }
			    if(hA.get("idLaporan").equals("1")){
			    	context.put("checkedc2","checked");
			    }
			    else
			    {
			    	context.put("checkedc2","");
			    }
 			context.put("jenis_Dokumen",hA.get("jenis_Dokumen"));
 			context.put("no_Rujukan_Lain",hA.get("no_Rujukan_Lain"));
 			context.put("pengirim_Dokumen",hA.get("pengirim_Dokumen"));
 			context.put("tajuk_Dokumen",hA.get("tajuk_Dokumen"));
 			context.put("penerima_Dokumen",hA.get("penerima_Dokumen"));
 			context.put("tarikh_Dokumen",hA.get("tarikh_Dokumen"));
 			context.put("tarikh_Dokumen_Diterima",hA.get("tarikh_Diterima_Dihantar"));
 			context.put("catatan",hA.get("catatan"));

    	 listLampiran(session);
    	 listLampiran = FrmLogDokumenTKPKData.getListLampiran();
    	 context.put("SenaraiLampiran",listLampiran);
    	 context.put("completed",false);
		}
		
		// SEKSYEN 1
		else if ("tambahSeksyen1".equals(action1)) {

			// GET DROPDOWN PARAM
			String idSeksyen1 = getParam("socSeksyen1");
			if (idSeksyen1 == null || idSeksyen1.trim().length() == 0) {
				idSeksyen1 = "99999";
			}
			String idPengarah1 = getParam("socPengarah1");
			if (idPengarah1 == null || idPengarah1.trim().length() == 0) {
				idPengarah1 = "99999";
			}
			String idPAR1 = getParam("socPAR1");
			if (idPAR1 == null || idPAR1.trim().length() == 0) {
				idPAR1 = "99999";
			}

			vm = "app/pfd/frmKemaskiniMinitArahanKPTG.jsp";

			context.put("mode", "New");
			context.put("readOnlyMinit", "");
			context.put("disabledMinit", "");
			context.put("modeSeksyen", "1");
			context.put("action1", "tambahSeksyen1");

			String user_name = (String) session
					.getAttribute("_portal_username");
			String user_id = (String) session.getAttribute("_ekptg_user_id");
			String user_role = (String) session.getAttribute("_portal_role");

			context.put("user_Role", user_role);
			context.put("user_Name", user_name);
			context.put("user_Id", user_id);

			String minit_Arahan = getParam("minit_Arahan");
			context.put("minit_Arahan", minit_Arahan);
			context.put("tarikh_Minit_Arahan", sdf.format(now));

			String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
			context.put("idLogDokumenKPTG", idLogDokumenKPTG);
			paparLogDokumenKPTG = FrmLogDokumenTKPKData
					.getDataLogKPTGDokumenMasuk(idLogDokumenKPTG);

			Hashtable h = (Hashtable) paparLogDokumenKPTG.get(0);
			context.put("no_Rujukan_Dokumen", h.get("no_Rujukan_Lain"));
			context.put("jenis_Dokumen", h.get("jenis_Dokumen"));

			this.context.put("selectSeksyen1", HTML.SelectSeksyenPAR("socSeksyen1", Utils.parseLong(idSeksyen1), null,"onChange=\"doChangeSeksyenPAR1();\" "));
			this.context.put("selectPengarah1", HTML.SelectPengarahBySeksyen(idSeksyen1, "socPengarah1", Utils.parseLong(idPengarah1),"", ""));
			this.context.put("selectPAR1", HTML.SelectPARByIdSeksyen(idSeksyen1,"socPAR1", Utils.parseLong(idPAR1), null, null));

		} else if ("simpanSeksyen1".equals(action1)) {

			// GET DROPDOWN PARAM
			String idSeksyen1 = getParam("socSeksyen1");
			if (idSeksyen1 == null || idSeksyen1.trim().length() == 0) {
				idSeksyen1 = "99999";
			}
			String idPengarah1 = getParam("socPengarah1");
			if (idPengarah1 == null || idPengarah1.trim().length() == 0) {
				idPengarah1 = "99999";
			}
			String idPAR1 = getParam("socPAR1");
			if (idPAR1 == null || idPAR1.trim().length() == 0) {
				idPAR1 = "99999";
			}

			context.put("mode", "ViewSeksyen1");
			context.put("readonlyMinit", "readonly");
			context.put("disabledMinit", "disabled");
			context.put("modeSeksyen", "1");
			context.put("action1", "simpanSeksyen1");

			vm = "app/pfd/frmKemaskiniMinitArahanKPTG.jsp";

			String user_name = (String) session
					.getAttribute("_portal_username");
			String user_id = (String) session.getAttribute("_ekptg_user_id");
			String user_role = (String) session.getAttribute("_portal_role");

			context.put("user_Role", user_role);
			context.put("user_Name", user_name);
			context.put("user_Id", user_id);

			String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
			context.put("idLogDokumenKPTG", idLogDokumenKPTG);
			paparLogDokumenKPTG = FrmLogDokumenTKPKData
					.getDataLogKPTGDokumenMasuk(idLogDokumenKPTG);

			Hashtable hash = (Hashtable) paparLogDokumenKPTG.get(0);
			context.put("no_Rujukan_Dokumen", hash.get("no_Rujukan_Lain"));
			context.put("jenis_Dokumen", hash.get("jenis_Dokumen"));

			String idMinitArahanSeksyen1 = simpanSeksyen1(session);
			context.put("idMinitArahanSeksyen1", idMinitArahanSeksyen1);

			paparMinitArahanSeksyen1 = FrmLogDokumenTKPKData
					.getDataMinitArahanSeksyen1(idMinitArahanSeksyen1);

			Hashtable h = (Hashtable) paparMinitArahanSeksyen1.get(0);
			context.put("minit_Arahan", h.get("minit_Arahan"));
			context.put("tarikh_Minit_Arahan", h.get("tarikh_Minit_Arahan"));

			idSeksyen1 = (String) h.get("idSeksyen1");
			idPengarah1 = (String) h.get("idPenerima1");
			idPAR1 = (String) h.get("idPAR1");

			this.context.put("selectSeksyen1", HTML.SelectSeksyenPAR("socSeksyen1", Utils.parseLong(idSeksyen1), "disabled"," class=\"disabled\""));
			this.context.put("selectPengarah1", HTML.SelectPengarahBySeksyen(idSeksyen1, "socPengarah1", Utils.parseLong(idPengarah1),"disabled", " class=\"disabled\""));
			this.context.put("selectPAR1", HTML.SelectPARByIdSeksyen(idSeksyen1, "socPAR1", Utils.parseLong(idPAR1), "disabled"," class=\"disabled\""));
			
			String no_Rujukan_Dokumen = getParam("no_Rujukan_Dokumen");		
		    tajuk_Dokumen = String.valueOf(hash.get("tajuk_Dokumen"));
			String tarikh_Minit_Arahan = String.valueOf(h.get("tarikh_Minit_Arahan"));

//			String emailRecipient1 = getEmail1(idPAR1);
//			
//    		EkptgEmailSender email = EkptgEmailSender.getInstance();
//    		email.FROM ="etapp_webmaster@ekptg.gov.my";
//    		email.SUBJECT ="Sila semak Minit Arahan bagi dokumen "+no_Rujukan_Dokumen+" untuk tindakan selanjutnya.";
//    		email.MESSAGE ="TAJUK : <B>'"+tajuk_Dokumen+"'</B>."+
//    					   "<br><br>Tuan/Puan,"+
//    					   "<br><br>Permohonan : Dokumen <B>"+no_Rujukan_Dokumen+"</B> telah diterima pada : <B>"+tarikh_Minit_Arahan+"</B> dan telah "+
//    					   "sedia untuk semakan dan pengesahan dari pihak tuan/puan."+
//    					   "<br><br>Sila <i><b>login</b></i> masuk ke <b>www.etapp.gov.my</b> untuk semakan serta pengesahan dari pihak tuan/puan."+
//    					   "<br><br>Sekian,Terima Kasih."+
//    					   "<br><b>webmaster@etapp.gov.my</b>"+
//    					   "<br><br>Nota : Email ini adalah dijana oleh sistem eTapp dan tidak perlu dibalas.";
//
//    		email.MULTIPLE_RECIEPIENT = new String[1];
//    		email.MULTIPLE_RECIEPIENT[0]=emailRecipient1;
//
//    		email.TO_CC = new String[3];
//    		email.TO_CC[0]  = "cipon.it@gmail.com";
//    		email.TO_CC[1]  = "rasul@hla-group.com";
//    		email.TO_CC[2]  = "rizuan@hla-group.com";
//    		email.sendEmail();

		} else if ("kemaskiniSeksyen1".equals(action1)) {

			// GET DROPDOWN PARAM
			String idSeksyen1 = getParam("socSeksyen1");
			if (idSeksyen1 == null || idSeksyen1.trim().length() == 0) {
				idSeksyen1 = "99999";
			}
			String idPengarah1 = getParam("socPengarah1");
			if (idPengarah1 == null || idPengarah1.trim().length() == 0) {
				idPengarah1 = "99999";
			}
			String idPAR1 = getParam("socPAR1");
			if (idPAR1 == null || idPAR1.trim().length() == 0) {
				idPAR1 = "99999";
			}

			context.put("mode", "KemaskiniSeksyen1");
			context.put("readonlyMinit", "");
			context.put("disabledMinit", "");
			context.put("modeSeksyen", "1");
			context.put("action1", "kemaskiniSeksyen1");

			vm = "app/pfd/frmKemaskiniMinitArahanKPTG.jsp";

			String user_name = (String) session
					.getAttribute("_portal_username");
			String user_id = (String) session.getAttribute("_ekptg_user_id");
			String user_role = (String) session.getAttribute("_portal_role");

			context.put("user_Role", user_role);
			context.put("user_Name", user_name);
			context.put("user_Id", user_id);

			String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
			context.put("idLogDokumenKPTG", idLogDokumenKPTG);
			paparLogDokumenKPTG = FrmLogDokumenTKPKData
					.getDataLogKPTGDokumenMasuk(idLogDokumenKPTG);

			Hashtable hash = (Hashtable) paparLogDokumenKPTG.get(0);
			context.put("no_Rujukan_Dokumen", hash.get("no_Rujukan_Lain"));
			context.put("jenis_Dokumen", hash.get("jenis_Dokumen"));

			String idMinitArahanSeksyen1 = getParam("idMinitArahanSeksyen1");
			context.put("idMinitArahanSeksyen1", idMinitArahanSeksyen1);

			paparMinitArahanSeksyen1 = FrmLogDokumenTKPKData
					.getDataMinitArahanSeksyen1(idMinitArahanSeksyen1);

			Hashtable h = (Hashtable) paparMinitArahanSeksyen1.get(0);
			context.put("minit_Arahan", h.get("minit_Arahan"));
			if ("".equals(submit)) {
				context
						.put("tarikh_Minit_Arahan", h
								.get("tarikh_Minit_Arahan"));
				idSeksyen1 = (String) h.get("idSeksyen1");
				idPengarah1 = (String) h.get("idPenerima1");
				idPAR1 = (String) h.get("idPAR1");
			}

			this.context.put("selectSeksyen1", HTML.SelectSeksyenPAR(
					"socSeksyen1", Utils.parseLong(idSeksyen1), null,
					"onChange=\"doChangeKemaskiniSeksyenPAR1();\" "));
			this.context.put("selectPengarah1", HTML.SelectPengarahBySeksyen(
					idSeksyen1, "socPengarah1", Utils.parseLong(idPengarah1),
					"", ""));
			this.context
					.put("selectPAR1", HTML.SelectPARByIdSeksyen(idSeksyen1,
							"socPAR1", Utils.parseLong(idPAR1), null, null));

		} else if ("batalKemaskiniSeksyen1".equals(action1)) {

			// GET DROPDOWN PARAM
			String idSeksyen1 = getParam("socSeksyen1");
			if (idSeksyen1 == null || idSeksyen1.trim().length() == 0) {
				idSeksyen1 = "99999";
			}
			String idPengarah1 = getParam("socPengarah1");
			if (idPengarah1 == null || idPengarah1.trim().length() == 0) {
				idPengarah1 = "99999";
			}
			String idPAR1 = getParam("socPAR1");
			if (idPAR1 == null || idPAR1.trim().length() == 0) {
				idPAR1 = "99999";
			}

			context.put("mode", "ViewSeksyen1");
			context.put("readonlyMinit", "readonly");
			context.put("disabledMinit", "disabled");
			context.put("modeSeksyen", "1");
			context.put("action1", "batalKemaskiniSeksyen1");

			vm = "app/pfd/frmKemaskiniMinitArahanKPTG.jsp";

			String user_name = (String) session
					.getAttribute("_portal_username");
			String user_id = (String) session.getAttribute("_ekptg_user_id");
			String user_role = (String) session.getAttribute("_portal_role");

			context.put("user_Role", user_role);
			context.put("user_Name", user_name);
			context.put("user_Id", user_id);

			String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
			context.put("idLogDokumenKPTG", idLogDokumenKPTG);
			paparLogDokumenKPTG = FrmLogDokumenTKPKData
					.getDataLogKPTGDokumenMasuk(idLogDokumenKPTG);

			Hashtable hash = (Hashtable) paparLogDokumenKPTG.get(0);
			context.put("no_Rujukan_Dokumen", hash.get("no_Rujukan_Lain"));
			context.put("jenis_Dokumen", hash.get("jenis_Dokumen"));

			String idMinitArahanSeksyen1 = getParam("idMinitArahanSeksyen1");
			context.put("idMinitArahanSeksyen1", idMinitArahanSeksyen1);

			paparMinitArahanSeksyen1 = FrmLogDokumenTKPKData
					.getDataMinitArahanSeksyen1(idMinitArahanSeksyen1);

			Hashtable h = (Hashtable) paparMinitArahanSeksyen1.get(0);
			context.put("minit_Arahan", h.get("minit_Arahan"));
			context.put("tarikh_Minit_Arahan", h.get("tarikh_Minit_Arahan"));
			idSeksyen1 = (String) h.get("idSeksyen1");
			idPengarah1 = (String) h.get("idPenerima1");
			idPAR1 = (String) h.get("idPAR1");

			this.context.put("selectSeksyen1", HTML.SelectSeksyenPAR(
					"socSeksyen1", Utils.parseLong(idSeksyen1), "disabled",
					" class=\"disabled\""));
			this.context.put("selectPengarah1", HTML.SelectPengarahBySeksyen(
					idSeksyen1, "socPengarah1", Utils.parseLong(idPengarah1),
					"disabled", " class=\"disabled\""));
			this.context.put("selectPAR1", HTML.SelectPARByIdSeksyen(
					idSeksyen1, "socPAR1", Utils.parseLong(idPAR1), "disabled",
					" class=\"disabled\""));

		} else if ("simpanKemaskiniSeksyen1".equals(action1)) {

			// GET DROPDOWN PARAM
			String idSeksyen1 = getParam("socSeksyen1");
			if (idSeksyen1 == null || idSeksyen1.trim().length() == 0) {
				idSeksyen1 = "99999";
			}
			String idPengarah1 = getParam("socPengarah1");
			if (idPengarah1 == null || idPengarah1.trim().length() == 0) {
				idPengarah1 = "99999";
			}
			String idPAR1 = getParam("socPAR1");
			if (idPAR1 == null || idPAR1.trim().length() == 0) {
				idPAR1 = "99999";
			}

			context.put("mode", "ViewSeksyen1");
			context.put("readonlyMinit", "readonly");
			context.put("disabledMinit", "disabled");
			context.put("modeSeksyen", "1");
			context.put("action1", "simpanKemaskiniSeksyen1");

			vm = "app/pfd/frmKemaskiniMinitArahanKPTG.jsp";

			String user_name = (String) session
					.getAttribute("_portal_username");
			String user_id = (String) session.getAttribute("_ekptg_user_id");
			String user_role = (String) session.getAttribute("_portal_role");

			context.put("user_Role", user_role);
			context.put("user_Name", user_name);
			context.put("user_Id", user_id);

			String idMinitArahanSeksyen1 = getParam("idMinitArahanSeksyen1");
			context.put("idMinitArahanSeksyen1", idMinitArahanSeksyen1);

			simpanKemaskiniSeksyen1(idMinitArahanSeksyen1, session);

			paparMinitArahanSeksyen1 = FrmLogDokumenTKPKData
					.getDataMinitArahanSeksyen1(idMinitArahanSeksyen1);

			Hashtable h = (Hashtable) paparMinitArahanSeksyen1.get(0);
			context.put("minit_Arahan", h.get("minit_Arahan"));
			context.put("tarikh_Minit_Arahan", h.get("tarikh_Minit_Arahan"));
			idSeksyen1 = (String) h.get("idSeksyen1");
			idPengarah1 = (String) h.get("idPenerima1");
			idPAR1 = (String) h.get("idPAR1");

			this.context.put("selectSeksyen1", HTML.SelectSeksyenPAR(
					"socSeksyen1", Utils.parseLong(idSeksyen1), "disabled",
					" class=\"disabled\""));
			this.context.put("selectPengarah1", HTML.SelectPengarahBySeksyen(
					idSeksyen1, "socPengarah1", Utils.parseLong(idPengarah1),
					"disabled", " class=\"disabled\""));
			this.context.put("selectPAR1", HTML.SelectPARByIdSeksyen(
					idSeksyen1, "socPAR1", Utils.parseLong(idPAR1), "disabled",
					" class=\"disabled\""));

		}

		// SEKSYEN 2
		else if ("tambahSeksyen2".equals(action1)) {

			// GET DROPDOWN PARAM
			String idSeksyen1 = getParam("socSeksyen1");
			if (idSeksyen1 == null || idSeksyen1.trim().length() == 0) {
				idSeksyen1 = "99999";
			}
			String idPengarah1 = getParam("socPengarah1");
			if (idPengarah1 == null || idPengarah1.trim().length() == 0) {
				idPengarah1 = "99999";
			}
			String idPAR1 = getParam("socPAR1");
			if (idPAR1 == null || idPAR1.trim().length() == 0) {
				idPAR1 = "99999";
			}
			String idSeksyen2 = getParam("socSeksyen2");
			if (idSeksyen2 == null || idSeksyen2.trim().length() == 0) {
				idSeksyen2 = "99999";
			}
			String idPengarah2 = getParam("socPengarah2");
			if (idPengarah2 == null || idPengarah2.trim().length() == 0) {
				idPengarah2 = "99999";
			}
			String idPAR2 = getParam("socPAR2");
			if (idPAR2 == null || idPAR2.trim().length() == 0) {
				idPAR2 = "99999";
			}

			vm = "app/pfd/frmKemaskiniMinitArahanKPTG.jsp";

			context.put("mode", "New");
			context.put("readOnlyMinit", "");
			context.put("disabledMinit", "");
			context.put("modeSeksyen", "2");
			context.put("action1", "tambahSeksyen2");

			String user_name = (String) session
					.getAttribute("_portal_username");
			String user_id = (String) session.getAttribute("_ekptg_user_id");
			String user_role = (String) session.getAttribute("_portal_role");

			context.put("user_Role", user_role);
			context.put("user_Name", user_name);
			context.put("user_Id", user_id);

			String minit_Arahan = getParam("minit_Arahan");
			context.put("minit_Arahan", minit_Arahan);
			context.put("tarikh_Minit_Arahan", sdf.format(now));

			String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
			context.put("idLogDokumenKPTG", idLogDokumenKPTG);
			paparLogDokumenKPTG = FrmLogDokumenTKPKData
					.getDataLogKPTGDokumenMasuk(idLogDokumenKPTG);

			Hashtable h = (Hashtable) paparLogDokumenKPTG.get(0);
			context.put("no_Rujukan_Dokumen", h.get("no_Rujukan_Lain"));
			context.put("jenis_Dokumen", h.get("jenis_Dokumen"));

			this.context.put("selectSeksyen1", HTML.SelectSeksyenPAR(
					"socSeksyen1", Utils.parseLong(idSeksyen1), null,
					"onChange=\"doChangeSeksyenPAR2();\" "));
			this.context.put("selectPengarah1", HTML.SelectPengarahBySeksyen(
					idSeksyen1, "socPengarah1", Utils.parseLong(idPengarah1),
					"", ""));
			this.context
					.put("selectPAR1", HTML.SelectPARByIdSeksyen(idSeksyen1,
							"socPAR1", Utils.parseLong(idPAR1), null, null));

			this.context.put("selectSeksyen2", HTML.SelectSeksyenPAR(
					"socSeksyen2", Utils.parseLong(idSeksyen2), null,
					"onChange=\"doChangeSeksyenPAR2();\" "));
			this.context.put("selectPengarah2", HTML.SelectPengarahBySeksyen(
					idSeksyen2, "socPengarah2", Utils.parseLong(idPengarah2),
					"", ""));
			this.context
					.put("selectPAR2", HTML.SelectPARByIdSeksyen(idSeksyen2,
							"socPAR2", Utils.parseLong(idPAR2), null, null));

		} else if ("simpanSeksyen2".equals(action1)) {

			// GET DROPDOWN PARAM
			String idSeksyen1 = getParam("socSeksyen1");
			if (idSeksyen1 == null || idSeksyen1.trim().length() == 0) {
				idSeksyen1 = "99999";
			}
			String idPengarah1 = getParam("socPengarah1");
			if (idPengarah1 == null || idPengarah1.trim().length() == 0) {
				idPengarah1 = "99999";
			}
			String idPAR1 = getParam("socPAR1");
			if (idPAR1 == null || idPAR1.trim().length() == 0) {
				idPAR1 = "99999";
			}
			String idSeksyen2 = getParam("socSeksyen2");
			if (idSeksyen2 == null || idSeksyen1.trim().length() == 0) {
				idSeksyen2 = "99999";
			}
			String idPengarah2 = getParam("socPengarah2");
			if (idPengarah2 == null || idPengarah2.trim().length() == 0) {
				idPengarah2 = "99999";
			}
			String idPAR2 = getParam("socPAR2");
			if (idPAR2 == null || idPAR2.trim().length() == 0) {
				idPAR2 = "99999";
			}

			context.put("mode", "ViewSeksyen2");
			context.put("readonlyMinit", "readonly");
			context.put("disabledMinit", "disabled");
			context.put("modeSeksyen", "2");
			context.put("action1", "simpanSeksyen2");

			vm = "app/pfd/frmKemaskiniMinitArahanKPTG.jsp";

			String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
			context.put("idLogDokumenKPTG", idLogDokumenKPTG);
			paparLogDokumenKPTG = FrmLogDokumenTKPKData
					.getDataLogKPTGDokumenMasuk(idLogDokumenKPTG);

			Hashtable hash = (Hashtable) paparLogDokumenKPTG.get(0);
			context.put("no_Rujukan_Dokumen", hash.get("no_Rujukan_Lain"));
			context.put("jenis_Dokumen", hash.get("jenis_Dokumen"));
			
			String idMinitArahanSeksyen2 = simpanSeksyen2(session);
			context.put("idMinitArahanSeksyen2", idMinitArahanSeksyen2);

			paparMinitArahanSeksyen2 = FrmLogDokumenTKPKData
					.getDataMinitArahanSeksyen2(idMinitArahanSeksyen2);

			Hashtable h = (Hashtable) paparMinitArahanSeksyen2.get(0);
			context.put("minit_Arahan", h.get("minit_Arahan"));
			context.put("tarikh_Minit_Arahan", h.get("tarikh_Minit_Arahan"));

			idSeksyen1 = (String) h.get("idSeksyen1");
			idPengarah1 = (String) h.get("idPenerima1");
			idPAR1 = (String) h.get("idPAR1");
			idSeksyen2 = (String) h.get("idSeksyen2");
			idPengarah2 = (String) h.get("idPenerima2");
			idPAR2 = (String) h.get("idPAR2");

			this.context.put("selectSeksyen1", HTML.SelectSeksyenPAR(
					"socSeksyen1", Utils.parseLong(idSeksyen1), "disabled",
					" class=\"disabled\""));
			this.context.put("selectPengarah1", HTML.SelectPengarahBySeksyen(
					idSeksyen1, "socPengarah1", Utils.parseLong(idPengarah1),
					"disabled", " class=\"disabled\""));
			this.context.put("selectPAR1", HTML.SelectPARByIdSeksyen(
					idSeksyen1, "socPAR1", Utils.parseLong(idPAR1), "disabled",
					" class=\"disabled\""));

			this.context.put("selectSeksyen2", HTML.SelectSeksyenPAR(
					"socSeksyen2", Utils.parseLong(idSeksyen2), "disabled",
					" class=\"disabled\""));
			this.context.put("selectPengarah2", HTML.SelectPengarahBySeksyen(
					idSeksyen2, "socPengarah2", Utils.parseLong(idPengarah2),
					"disabled", " class=\"disabled\""));
			this.context.put("selectPAR2", HTML.SelectPARByIdSeksyen(
					idSeksyen2, "socPAR2", Utils.parseLong(idPAR2), "disabled",
					" class=\"disabled\""));
			
			String no_Rujukan_Dokumen = getParam("no_Rujukan_Dokumen");	
			tajuk_Dokumen = String.valueOf(hash.get("tajuk_Dokumen"));
			String tarikh_Minit_Arahan = String.valueOf(h.get("tarikh_Minit_Arahan"));
			
//			String emailRecipient1 = getEmail1(idPAR1);
//			String emailRecipient2 = getEmail2(idPAR2);
//
//	    		
//    		EkptgEmailSender email = EkptgEmailSender.getInstance();
//    		email.FROM ="etapp_webmaster@ekptg.gov.my";
//    		email.SUBJECT ="Sila semak Minit Arahan bagi dokumen "+no_Rujukan_Dokumen+" untuk tindakan selanjutnya.";
//    		email.MESSAGE ="TAJUK : <B>'"+tajuk_Dokumen+"'</B>."+
//    					   "<br><br>Tuan/Puan,"+
//    					   "<br><br>Permohonan : Dokumen <B>"+no_Rujukan_Dokumen+"</B> telah diterima pada : <B>"+tarikh_Minit_Arahan+"</B> dan telah "+
//    					   "sedia untuk semakan dan pengesahan dari pihak tuan/puan."+
//    					   "<br><br>Sila <i><b>login</b></i> masuk ke <b>www.etapp.gov.my</b> untuk semakan serta pengesahan dari pihak tuan/puan."+
//    					   "<br><br>Sekian,Terima Kasih."+
//    					   "<br><b>webmaster@etapp.gov.my</b>"+
//    					   "<br><br>Nota : Email ini adalah dijana oleh sistem eTapp dan tidak perlu dibalas.";
//
//    		email.MULTIPLE_RECIEPIENT = new String[2];
//    		email.MULTIPLE_RECIEPIENT[0]=emailRecipient1;
//    		email.MULTIPLE_RECIEPIENT[1]=emailRecipient2;
//
//    		email.TO_CC = new String[3];
//    		email.TO_CC[0]  = "cipon.it@gmail.com";
//    		email.TO_CC[1]  = "rasul@hla-group.com";
//    		email.TO_CC[2]  = "rizuan@hla-group.com";
//    		email.sendEmail();


		} else if ("kemaskiniSeksyen2".equals(action1)) {

			// GET DROPDOWN PARAM
			String idSeksyen1 = getParam("socSeksyen1");
			if (idSeksyen1 == null || idSeksyen1.trim().length() == 0) {
				idSeksyen1 = "99999";
			}
			String idPengarah1 = getParam("socPengarah1");
			if (idPengarah1 == null || idPengarah1.trim().length() == 0) {
				idPengarah1 = "99999";
			}
			String idPAR1 = getParam("socPAR1");
			if (idPAR1 == null || idPAR1.trim().length() == 0) {
				idPAR1 = "99999";
			}
			String idSeksyen2 = getParam("socSeksyen2");
			if (idSeksyen2 == null || idSeksyen1.trim().length() == 0) {
				idSeksyen2 = "99999";
			}
			String idPengarah2 = getParam("socPengarah2");
			if (idPengarah2 == null || idPengarah2.trim().length() == 0) {
				idPengarah2 = "99999";
			}
			String idPAR2 = getParam("socPAR2");
			if (idPAR2 == null || idPAR2.trim().length() == 0) {
				idPAR2 = "99999";
			}

			context.put("mode", "KemaskiniSeksyen2");
			context.put("readonlyMinit", "");
			context.put("disabledMinit", "");
			context.put("modeSeksyen", "2");
			context.put("action1", "kemaskiniSeksyen2");

			vm = "app/pfd/frmKemaskiniMinitArahanKPTG.jsp";

			String idMinitArahanSeksyen2 = getParam("idMinitArahanSeksyen2");
			context.put("idMinitArahanSeksyen2", idMinitArahanSeksyen2);

			paparMinitArahanSeksyen2 = FrmLogDokumenTKPKData
					.getDataMinitArahanSeksyen2(idMinitArahanSeksyen2);

			Hashtable h = (Hashtable) paparMinitArahanSeksyen2.get(0);

			if ("".equals(submit)) {
				context.put("minit_Arahan", h.get("minit_Arahan"));
				context
						.put("tarikh_Minit_Arahan", h
								.get("tarikh_Minit_Arahan"));

				idSeksyen1 = (String) h.get("idSeksyen1");
				idPengarah1 = (String) h.get("idPenerima1");
				idPAR1 = (String) h.get("idPAR1");
				idSeksyen2 = (String) h.get("idSeksyen2");
				idPengarah2 = (String) h.get("idPenerima2");
				idPAR2 = (String) h.get("idPAR2");
			}

			this.context.put("selectSeksyen1", HTML.SelectSeksyenPAR(
					"socSeksyen1", Utils.parseLong(idSeksyen1), null,
					"onChange=\"doChangeKemaskiniSeksyenPAR2();\" "));
			this.context.put("selectPengarah1", HTML.SelectPengarahBySeksyen(
					idSeksyen1, "socPengarah1", Utils.parseLong(idPengarah1),
					"", ""));
			this.context.put("selectPAR1", HTML.SelectPARByIdSeksyen(
					idSeksyen1, "socPAR1", Utils.parseLong(idPAR1), "", ""));

			this.context.put("selectSeksyen2", HTML.SelectSeksyenPAR(
					"socSeksyen2", Utils.parseLong(idSeksyen2), null,
					"onChange=\"doChangeKemaskiniSeksyenPAR2();\" "));
			this.context.put("selectPengarah2", HTML.SelectPengarahBySeksyen(
					idSeksyen2, "socPengarah2", Utils.parseLong(idPengarah2),
					"", ""));
			this.context.put("selectPAR2", HTML.SelectPARByIdSeksyen(
					idSeksyen2, "socPAR2", Utils.parseLong(idPAR2), "", ""));

		}

		else if ("batalKemaskiniSeksyen2".equals(action1)) {

			// GET DROPDOWN PARAM
			String idSeksyen1 = getParam("socSeksyen1");
			if (idSeksyen1 == null || idSeksyen1.trim().length() == 0) {
				idSeksyen1 = "99999";
			}
			String idPengarah1 = getParam("socPengarah1");
			if (idPengarah1 == null || idPengarah1.trim().length() == 0) {
				idPengarah1 = "99999";
			}
			String idPAR1 = getParam("socPAR1");
			if (idPAR1 == null || idPAR1.trim().length() == 0) {
				idPAR1 = "99999";
			}
			String idSeksyen2 = getParam("socSeksyen2");
			if (idSeksyen2 == null || idSeksyen1.trim().length() == 0) {
				idSeksyen2 = "99999";
			}
			String idPengarah2 = getParam("socPengarah2");
			if (idPengarah2 == null || idPengarah2.trim().length() == 0) {
				idPengarah2 = "99999";
			}
			String idPAR2 = getParam("socPAR2");
			if (idPAR2 == null || idPAR2.trim().length() == 0) {
				idPAR2 = "99999";
			}

			context.put("mode", "ViewSeksyen2");
			context.put("readonlyMinit", "readonly");
			context.put("disabledMinit", "disabled");
			context.put("modeSeksyen", "2");
			context.put("action1", "batalKemaskiniSeksyen2");

			vm = "app/pfd/frmKemaskiniMinitArahanKPTG.jsp";

			String idMinitArahanSeksyen2 = getParam("idMinitArahanSeksyen2");
			context.put("idMinitArahanSeksyen2", idMinitArahanSeksyen2);

			paparMinitArahanSeksyen2 = FrmLogDokumenTKPKData
					.getDataMinitArahanSeksyen2(idMinitArahanSeksyen2);

			Hashtable h = (Hashtable) paparMinitArahanSeksyen2.get(0);
			context.put("minit_Arahan", h.get("minit_Arahan"));
			context.put("tarikh_Minit_Arahan", h.get("tarikh_Minit_Arahan"));

			idSeksyen1 = (String) h.get("idSeksyen1");
			idPengarah1 = (String) h.get("idPenerima1");
			idPAR1 = (String) h.get("idPAR1");
			idSeksyen2 = (String) h.get("idSeksyen2");
			idPengarah2 = (String) h.get("idPenerima2");
			idPAR2 = (String) h.get("idPAR2");

			this.context.put("selectSeksyen1", HTML.SelectSeksyenPAR(
					"socSeksyen1", Utils.parseLong(idSeksyen1), "disabled",
					" class=\"disabled\""));
			this.context.put("selectPengarah1", HTML.SelectPengarahBySeksyen(
					idSeksyen1, "socPengarah1", Utils.parseLong(idPengarah1),
					"disabled", " class=\"disabled\""));
			this.context.put("selectPAR1", HTML.SelectPARByIdSeksyen(
					idSeksyen1, "socPAR1", Utils.parseLong(idPAR1), "disabled",
					" class=\"disabled\""));

			this.context.put("selectSeksyen2", HTML.SelectSeksyenPAR(
					"socSeksyen2", Utils.parseLong(idSeksyen2), "disabled",
					" class=\"disabled\""));
			this.context.put("selectPengarah2", HTML.SelectPengarahBySeksyen(
					idSeksyen2, "socPengarah2", Utils.parseLong(idPengarah2),
					"disabled", " class=\"disabled\""));
			this.context.put("selectPAR2", HTML.SelectPARByIdSeksyen(
					idSeksyen2, "socPAR2", Utils.parseLong(idPAR2), "disabled",
					" class=\"disabled\""));

		}

		else if ("simpanKemaskiniSeksyen2".equals(action1)) {

			// GET DROPDOWN PARAM
			String idSeksyen1 = getParam("socSeksyen1");
			if (idSeksyen1 == null || idSeksyen1.trim().length() == 0) {
				idSeksyen1 = "99999";
			}
			String idPengarah1 = getParam("socPengarah1");
			if (idPengarah1 == null || idPengarah1.trim().length() == 0) {
				idPengarah1 = "99999";
			}
			String idPAR1 = getParam("socPAR1");
			if (idPAR1 == null || idPAR1.trim().length() == 0) {
				idPAR1 = "99999";
			}
			String idSeksyen2 = getParam("socSeksyen2");
			if (idSeksyen2 == null || idSeksyen1.trim().length() == 0) {
				idSeksyen2 = "99999";
			}
			String idPengarah2 = getParam("socPengarah2");
			if (idPengarah2 == null || idPengarah2.trim().length() == 0) {
				idPengarah2 = "99999";
			}
			String idPAR2 = getParam("socPAR2");
			if (idPAR2 == null || idPAR2.trim().length() == 0) {
				idPAR2 = "99999";
			}

			context.put("mode", "ViewSeksyen2");
			context.put("readonlyMinit", "readonly");
			context.put("disabledMinit", "disabled");
			context.put("modeSeksyen", "2");
			context.put("action1", "simpanKemaskiniSeksyen2");

			vm = "app/pfd/frmKemaskiniMinitArahanKPTG.jsp";

			String idMinitArahanSeksyen2 = simpanSeksyen2(session);
			context.put("idMinitArahanSeksyen2", idMinitArahanSeksyen2);

			paparMinitArahanSeksyen2 = FrmLogDokumenTKPKData
					.getDataMinitArahanSeksyen2(idMinitArahanSeksyen2);

			Hashtable h = (Hashtable) paparMinitArahanSeksyen2.get(0);
			context.put("minit_Arahan", h.get("minit_Arahan"));
			context.put("tarikh_Minit_Arahan", h.get("tarikh_Minit_Arahan"));

			idSeksyen1 = (String) h.get("idSeksyen1");
			idPengarah1 = (String) h.get("idPenerima1");
			idPAR1 = (String) h.get("idPAR1");
			idSeksyen2 = (String) h.get("idSeksyen2");
			idPengarah2 = (String) h.get("idPenerima2");
			idPAR2 = (String) h.get("idPAR2");

			this.context.put("selectSeksyen1", HTML.SelectSeksyenPAR(
					"socSeksyen1", Utils.parseLong(idSeksyen1), "disabled",
					" class=\"disabled\""));
			this.context.put("selectPengarah1", HTML.SelectPengarahBySeksyen(
					idSeksyen1, "socPengarah1", Utils.parseLong(idPengarah1),
					"disabled", " class=\"disabled\""));
			this.context.put("selectPAR1", HTML.SelectPARByIdSeksyen(
					idSeksyen1, "socPAR1", Utils.parseLong(idPAR1), "disabled",
					" class=\"disabled\""));

			this.context.put("selectSeksyen2", HTML.SelectSeksyenPAR(
					"socSeksyen2", Utils.parseLong(idSeksyen2), "disabled",
					" class=\"disabled\""));
			this.context.put("selectPengarah2", HTML.SelectPengarahBySeksyen(
					idSeksyen2, "socPengarah2", Utils.parseLong(idPengarah2),
					"disabled", " class=\"disabled\""));
			this.context.put("selectPAR2", HTML.SelectPARByIdSeksyen(
					idSeksyen2, "socPAR2", Utils.parseLong(idPAR2), "disabled",
					" class=\"disabled\""));

		}

		//SEKSYEN3
		else if ("tambahSeksyen3".equals(action1)) {
			
			// GET DROPDOWN PARAM
			String idSeksyen1 = getParam("socSeksyen1");
			if (idSeksyen1 == null || idSeksyen1.trim().length() == 0) {
				idSeksyen1 = "99999";
			}
			String idPengarah1 = getParam("socPengarah1");
			if (idPengarah1 == null || idPengarah1.trim().length() == 0) {
				idPengarah1 = "99999";
			}
			String idPAR1 = getParam("socPAR1");
			if (idPAR1 == null || idPAR1.trim().length() == 0) {
				idPAR1 = "99999";
			}
			String idSeksyen2 = getParam("socSeksyen2");
			if (idSeksyen2 == null || idSeksyen2.trim().length() == 0) {
				idSeksyen2 = "99999";
			}
			String idPengarah2 = getParam("socPengarah2");
			if (idPengarah2 == null || idPengarah2.trim().length() == 0) {
				idPengarah2 = "99999";
			}
			String idPAR2 = getParam("socPAR2");
			if (idPAR2 == null || idPAR2.trim().length() == 0) {
				idPAR2 = "99999";
			}
			String idSeksyen3 = getParam("socSeksyen3");
			if (idSeksyen3 == null || idSeksyen3.trim().length() == 0) {
				idSeksyen3 = "99999";
			}
			String idPengarah3 = getParam("socPengarah3");
			if (idPengarah3 == null || idPengarah3.trim().length() == 0) {
				idPengarah3 = "99999";
			}
			String idPAR3 = getParam("socPAR3");
			if (idPAR3 == null || idPAR3.trim().length() == 0) {
				idPAR3 = "99999";
			}
			
			vm = "app/pfd/frmKemaskiniMinitArahanKPTG.jsp";

			context.put("mode", "New");
			context.put("readOnlyMinit", "");
			context.put("disabledMinit", "");
			context.put("modeSeksyen", "3");
			context.put("action1", "tambahSeksyen3");

			String user_name = (String) session
					.getAttribute("_portal_username");
			String user_id = (String) session.getAttribute("_ekptg_user_id");
			String user_role = (String) session.getAttribute("_portal_role");

			context.put("user_Role", user_role);
			context.put("user_Name", user_name);
			context.put("user_Id", user_id);

			String minit_Arahan = getParam("minit_Arahan");
			context.put("minit_Arahan", minit_Arahan);
			context.put("tarikh_Minit_Arahan", sdf.format(now));

			String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
			context.put("idLogDokumenKPTG", idLogDokumenKPTG);
			paparLogDokumenKPTG = FrmLogDokumenTKPKData
					.getDataLogKPTGDokumenMasuk(idLogDokumenKPTG);

			Hashtable h = (Hashtable) paparLogDokumenKPTG.get(0);
			context.put("no_Rujukan_Dokumen", h.get("no_Rujukan_Lain"));
			context.put("jenis_Dokumen", h.get("jenis_Dokumen"));

			this.context.put("selectSeksyen1", HTML.SelectSeksyenPAR(
					"socSeksyen1", Utils.parseLong(idSeksyen1), null,
					"onChange=\"doChangeSeksyenPAR3();\" "));
			this.context.put("selectPengarah1", HTML.SelectPengarahBySeksyen(
					idSeksyen1, "socPengarah1", Utils.parseLong(idPengarah1),
					"", ""));
			this.context
					.put("selectPAR1", HTML.SelectPARByIdSeksyen(idSeksyen1,
							"socPAR1", Utils.parseLong(idPAR1), null, null));

			this.context.put("selectSeksyen2", HTML.SelectSeksyenPAR(
					"socSeksyen2", Utils.parseLong(idSeksyen2), null,
					"onChange=\"doChangeSeksyenPAR3();\" "));
			this.context.put("selectPengarah2", HTML.SelectPengarahBySeksyen(
					idSeksyen2, "socPengarah2", Utils.parseLong(idPengarah2),
					"", ""));
			this.context
					.put("selectPAR2", HTML.SelectPARByIdSeksyen(idSeksyen2,
							"socPAR2", Utils.parseLong(idPAR2), null, null));
			
			this.context.put("selectSeksyen3", HTML.SelectSeksyenPAR(
					"socSeksyen3", Utils.parseLong(idSeksyen3), null,
					"onChange=\"doChangeSeksyenPAR3();\" "));
			this.context.put("selectPengarah3", HTML.SelectPengarahBySeksyen(
					idSeksyen3, "socPengarah3", Utils.parseLong(idPengarah3),
					"", ""));
			this.context
					.put("selectPAR3", HTML.SelectPARByIdSeksyen(idSeksyen3,
							"socPAR3", Utils.parseLong(idPAR3), null, null));

		}

		else if ("simpanSeksyen3".equals(action1)) {
			
			// GET DROPDOWN PARAM
			String idSeksyen1 = getParam("socSeksyen1");
			if (idSeksyen1 == null || idSeksyen1.trim().length() == 0) {
				idSeksyen1 = "99999";
			}
			String idPengarah1 = getParam("socPengarah1");
			if (idPengarah1 == null || idPengarah1.trim().length() == 0) {
				idPengarah1 = "99999";
			}
			String idPAR1 = getParam("socPAR1");
			if (idPAR1 == null || idPAR1.trim().length() == 0) {
				idPAR1 = "99999";
			}
			String idSeksyen2 = getParam("socSeksyen2");
			if (idSeksyen2 == null || idSeksyen2.trim().length() == 0) {
				idSeksyen2 = "99999";
			}
			String idPengarah2 = getParam("socPengarah2");
			if (idPengarah2 == null || idPengarah2.trim().length() == 0) {
				idPengarah2 = "99999";
			}
			String idPAR2 = getParam("socPAR2");
			if (idPAR2 == null || idPAR2.trim().length() == 0) {
				idPAR2 = "99999";
			}
			String idSeksyen3 = getParam("socSeksyen3");
			if (idSeksyen3 == null || idSeksyen3.trim().length() == 0) {
				idSeksyen3 = "99999";
			}
			String idPengarah3 = getParam("socPengarah3");
			if (idPengarah3 == null || idPengarah3.trim().length() == 0) {
				idPengarah3 = "99999";
			}
			String idPAR3 = getParam("socPAR3");
			if (idPAR3 == null || idPAR3.trim().length() == 0) {
				idPAR3 = "99999";
			}
			
			context.put("mode", "ViewSeksyen3");
			context.put("readonlyMinit", "readonly");
			context.put("disabledMinit", "disabled");
			context.put("modeSeksyen", "3");
			context.put("action1", "simpanSeksyen3");

			vm = "app/pfd/frmKemaskiniMinitArahanKPTG.jsp";

			String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
			context.put("idLogDokumenKPTG", idLogDokumenKPTG);
			paparLogDokumenKPTG = FrmLogDokumenTKPKData
					.getDataLogKPTGDokumenMasuk(idLogDokumenKPTG);

			Hashtable hash = (Hashtable) paparLogDokumenKPTG.get(0);
			context.put("no_Rujukan_Dokumen", hash.get("no_Rujukan_Lain"));
			context.put("jenis_Dokumen", hash.get("jenis_Dokumen"));
			
			String idMinitArahanSeksyen3 = simpanSeksyen3(session);
			context.put("idMinitArahanSeksyen3", idMinitArahanSeksyen3);

			paparMinitArahanSeksyen3 = FrmLogDokumenTKPKData
					.getDataMinitArahanSeksyen3(idMinitArahanSeksyen3);

			Hashtable h = (Hashtable) paparMinitArahanSeksyen3.get(0);

			context.put("minit_Arahan", h.get("minit_Arahan"));
			context.put("tarikh_Minit_Arahan", h.get("tarikh_Minit_Arahan"));
			
			idSeksyen1 = (String) h.get("idSeksyen1");
			idPengarah1 = (String) h.get("idPenerima1");
			idPAR1 = (String) h.get("idPAR1");
			idSeksyen2 = (String) h.get("idSeksyen2");
			idPengarah2 = (String) h.get("idPenerima2");
			idPAR2 = (String) h.get("idPAR2");
			idSeksyen3 = (String) h.get("idSeksyen3");
			idPengarah3 = (String) h.get("idPenerima3");
			idPAR3 = (String) h.get("idPAR3");

			this.context.put("selectSeksyen1", HTML.SelectSeksyenPAR(
					"socSeksyen1", Utils.parseLong(idSeksyen1), "disabled",
					" class=\"disabled\""));
			this.context.put("selectPengarah1", HTML.SelectPengarahBySeksyen(
					idSeksyen1, "socPengarah1", Utils.parseLong(idPengarah1),
					"disabled", " class=\"disabled\""));
			this.context.put("selectPAR1", HTML.SelectPARByIdSeksyen(
					idSeksyen1, "socPAR1", Utils.parseLong(idPAR1), "disabled",
					" class=\"disabled\""));

			this.context.put("selectSeksyen2", HTML.SelectSeksyenPAR(
					"socSeksyen2", Utils.parseLong(idSeksyen2), "disabled",
					" class=\"disabled\""));
			this.context.put("selectPengarah2", HTML.SelectPengarahBySeksyen(
					idSeksyen2, "socPengarah2", Utils.parseLong(idPengarah2),
					"disabled", " class=\"disabled\""));
			this.context.put("selectPAR2", HTML.SelectPARByIdSeksyen(
					idSeksyen2, "socPAR2", Utils.parseLong(idPAR2), "disabled",
					" class=\"disabled\""));
			
			this.context.put("selectSeksyen3", HTML.SelectSeksyenPAR(
					"socSeksyen3", Utils.parseLong(idSeksyen3), "disabled",
					" class=\"disabled\""));
			this.context.put("selectPengarah3", HTML.SelectPengarahBySeksyen(
					idSeksyen3, "socPengarah3", Utils.parseLong(idPengarah3),
					"disabled", " class=\"disabled\""));
			this.context.put("selectPAR3", HTML.SelectPARByIdSeksyen(
					idSeksyen3, "socPAR3", Utils.parseLong(idPAR3), "disabled",
					" class=\"disabled\""));
			
			String no_Rujukan_Dokumen = getParam("no_Rujukan_Dokumen");	
			tajuk_Dokumen = String.valueOf(hash.get("tajuk_Dokumen"));
			String tarikh_Minit_Arahan = String.valueOf(h.get("tarikh_Minit_Arahan"));
			
//			String emailRecipient1 = getEmail1(idPAR1);
//			String emailRecipient2 = getEmail2(idPAR2);
//			String emailRecipient3 = getEmail3(idPAR3);
//	    		
//    		EkptgEmailSender email = EkptgEmailSender.getInstance();
//    		email.FROM ="etapp_webmaster@ekptg.gov.my";
//    		email.SUBJECT ="Sila semak Minit Arahan bagi dokumen "+no_Rujukan_Dokumen+" untuk tindakan selanjutnya.";
//    		email.MESSAGE ="TAJUK : <B>'"+tajuk_Dokumen+"'</B>."+
//    					   "<br><br>Tuan/Puan,"+
//    					   "<br><br>Permohonan : Dokumen <B>"+no_Rujukan_Dokumen+"</B> telah diterima pada : <B>"+tarikh_Minit_Arahan+"</B> dan telah "+
//    					   "sedia untuk semakan dan pengesahan dari pihak tuan/puan."+
//    					   "<br><br>Sila <i><b>login</b></i> masuk ke <b>www.etapp.gov.my</b> untuk semakan serta pengesahan dari pihak tuan/puan."+
//    					   "<br><br>Sekian,Terima Kasih."+
//    					   "<br><b>webmaster@etapp.gov.my</b>"+
//    					   "<br><br>Nota : Email ini adalah dijana oleh sistem eTapp dan tidak perlu dibalas.";
//
//    		email.MULTIPLE_RECIEPIENT = new String[3];
//    		email.MULTIPLE_RECIEPIENT[0]=emailRecipient1;
//    		email.MULTIPLE_RECIEPIENT[1]=emailRecipient2;
//    		email.MULTIPLE_RECIEPIENT[2]=emailRecipient3;
//
//    		email.TO_CC = new String[3];
//    		email.TO_CC[0]  = "cipon.it@gmail.com";
//    		email.TO_CC[1]  = "rasul@hla-group.com";
//    		email.TO_CC[2]  = "rizuan@hla-group.com";
//    		email.sendEmail();

		}
		
		else if ("kemaskiniSeksyen3".equals(action1)) {
			
			// GET DROPDOWN PARAM
			String idSeksyen1 = getParam("socSeksyen1");
			if (idSeksyen1 == null || idSeksyen1.trim().length() == 0) {
				idSeksyen1 = "99999";
			}
			String idPengarah1 = getParam("socPengarah1");
			if (idPengarah1 == null || idPengarah1.trim().length() == 0) {
				idPengarah1 = "99999";
			}
			String idPAR1 = getParam("socPAR1");
			if (idPAR1 == null || idPAR1.trim().length() == 0) {
				idPAR1 = "99999";
			}
			String idSeksyen2 = getParam("socSeksyen2");
			if (idSeksyen2 == null || idSeksyen2.trim().length() == 0) {
				idSeksyen2 = "99999";
			}
			String idPengarah2 = getParam("socPengarah2");
			if (idPengarah2 == null || idPengarah2.trim().length() == 0) {
				idPengarah2 = "99999";
			}
			String idPAR2 = getParam("socPAR2");
			if (idPAR2 == null || idPAR2.trim().length() == 0) {
				idPAR2 = "99999";
			}
			String idSeksyen3 = getParam("socSeksyen3");
			if (idSeksyen3 == null || idSeksyen3.trim().length() == 0) {
				idSeksyen3 = "99999";
			}
			String idPengarah3 = getParam("socPengarah3");
			if (idPengarah3 == null || idPengarah3.trim().length() == 0) {
				idPengarah3 = "99999";
			}
			String idPAR3 = getParam("socPAR3");
			if (idPAR3 == null || idPAR3.trim().length() == 0) {
				idPAR3 = "99999";
			}
			
			vm = "app/pfd/frmKemaskiniMinitArahanKPTG.jsp";

			context.put("mode", "KemaskiniSeksyen3");
			context.put("readOnlyMinit", "");
			context.put("disabledMinit", "");
			context.put("modeSeksyen", "3");
			context.put("action1", "kemaskiniSeksyen3");

			String user_name = (String) session
					.getAttribute("_portal_username");
			String user_id = (String) session.getAttribute("_ekptg_user_id");
			String user_role = (String) session.getAttribute("_portal_role");

			context.put("user_Role", user_role);
			context.put("user_Name", user_name);
			context.put("user_Id", user_id);

			String minit_Arahan = getParam("minit_Arahan");
			context.put("minit_Arahan", minit_Arahan);
			context.put("tarikh_Minit_Arahan", sdf.format(now));

			String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
			context.put("idLogDokumenKPTG", idLogDokumenKPTG);
			paparLogDokumenKPTG = FrmLogDokumenTKPKData
					.getDataLogKPTGDokumenMasuk(idLogDokumenKPTG);

			Hashtable h = (Hashtable) paparLogDokumenKPTG.get(0);
			context.put("no_Rujukan_Dokumen", h.get("no_Rujukan_Lain"));
			context.put("jenis_Dokumen", h.get("jenis_Dokumen"));
			
			String idMinitArahanSeksyen3 = getParam("idMinitArahanSeksyen3");
			context.put("idMinitArahanSeksyen3", idMinitArahanSeksyen3);

			paparMinitArahanSeksyen3 = FrmLogDokumenTKPKData
					.getDataMinitArahanSeksyen3(idMinitArahanSeksyen3);

			Hashtable hash = (Hashtable) paparMinitArahanSeksyen3.get(0);
			if ("".equals(submit)){
				context.put("minit_Arahan", h.get("minit_Arahan"));
				context.put("tarikh_Minit_Arahan", h.get("tarikh_Minit_Arahan"));
				
				idSeksyen1 = (String) hash.get("idSeksyen1");
				idPengarah1 = (String) hash.get("idPenerima1");
				idPAR1 = (String) hash.get("idPAR1");
				idSeksyen2 = (String) hash.get("idSeksyen2");
				idPengarah2 = (String) hash.get("idPenerima2");
				idPAR2 = (String) hash.get("idPAR2");
				idSeksyen3 = (String) hash.get("idSeksyen3");
				idPengarah3 = (String) hash.get("idPenerima3");
				idPAR3 = (String) hash.get("idPAR3");
			}
			

			this.context.put("selectSeksyen1", HTML.SelectSeksyenPAR(
					"socSeksyen1", Utils.parseLong(idSeksyen1), null,
					"onChange=\"doChangeKemaskiniSeksyenPAR3();\" "));
			this.context.put("selectPengarah1", HTML.SelectPengarahBySeksyen(
					idSeksyen1, "socPengarah1", Utils.parseLong(idPengarah1),
					"", ""));
			this.context
					.put("selectPAR1", HTML.SelectPARByIdSeksyen(idSeksyen1,
							"socPAR1", Utils.parseLong(idPAR1), null, null));

			this.context.put("selectSeksyen2", HTML.SelectSeksyenPAR(
					"socSeksyen2", Utils.parseLong(idSeksyen2), null,
					"onChange=\"doChangeKemaskiniSeksyenPAR3();\" "));
			this.context.put("selectPengarah2", HTML.SelectPengarahBySeksyen(
					idSeksyen2, "socPengarah2", Utils.parseLong(idPengarah2),
					"", ""));
			this.context
					.put("selectPAR2", HTML.SelectPARByIdSeksyen(idSeksyen2,
							"socPAR2", Utils.parseLong(idPAR2), null, null));
			
			this.context.put("selectSeksyen3", HTML.SelectSeksyenPAR(
					"socSeksyen3", Utils.parseLong(idSeksyen3), null,
					"onChange=\"doChangeKemaskiniSeksyenPAR3();\" "));
			this.context.put("selectPengarah3", HTML.SelectPengarahBySeksyen(
					idSeksyen3, "socPengarah3", Utils.parseLong(idPengarah3),
					"", ""));
			this.context
					.put("selectPAR3", HTML.SelectPARByIdSeksyen(idSeksyen3,
							"socPAR3", Utils.parseLong(idPAR3), null, null));
		}
		
		else if ("batalKemaskiniSeksyen3".equals(action1)) {
			
			// GET DROPDOWN PARAM
			String idSeksyen1 = getParam("socSeksyen1");
			if (idSeksyen1 == null || idSeksyen1.trim().length() == 0) {
				idSeksyen1 = "99999";
			}
			String idPengarah1 = getParam("socPengarah1");
			if (idPengarah1 == null || idPengarah1.trim().length() == 0) {
				idPengarah1 = "99999";
			}
			String idPAR1 = getParam("socPAR1");
			if (idPAR1 == null || idPAR1.trim().length() == 0) {
				idPAR1 = "99999";
			}
			String idSeksyen2 = getParam("socSeksyen2");
			if (idSeksyen2 == null || idSeksyen2.trim().length() == 0) {
				idSeksyen2 = "99999";
			}
			String idPengarah2 = getParam("socPengarah2");
			if (idPengarah2 == null || idPengarah2.trim().length() == 0) {
				idPengarah2 = "99999";
			}
			String idPAR2 = getParam("socPAR2");
			if (idPAR2 == null || idPAR2.trim().length() == 0) {
				idPAR2 = "99999";
			}
			String idSeksyen3 = getParam("socSeksyen3");
			if (idSeksyen3 == null || idSeksyen3.trim().length() == 0) {
				idSeksyen3 = "99999";
			}
			String idPengarah3 = getParam("socPengarah3");
			if (idPengarah3 == null || idPengarah3.trim().length() == 0) {
				idPengarah3 = "99999";
			}
			String idPAR3 = getParam("socPAR3");
			if (idPAR3 == null || idPAR3.trim().length() == 0) {
				idPAR3 = "99999";
			}
			
			context.put("mode", "ViewSeksyen3");
			context.put("readonlyMinit", "readonly");
			context.put("disabledMinit", "disabled");
			context.put("modeSeksyen", "3");
			context.put("action1", "batalKemaskiniSeksyen3");

			vm = "app/pfd/frmKemaskiniMinitArahanKPTG.jsp";

			String idMinitArahanSeksyen3 = getParam("idMinitArahanSeksyen3");
			context.put("idMinitArahanSeksyen3", idMinitArahanSeksyen3);

			paparMinitArahanSeksyen3 = FrmLogDokumenTKPKData
					.getDataMinitArahanSeksyen3(idMinitArahanSeksyen3);

			Hashtable h = (Hashtable) paparMinitArahanSeksyen3.get(0);

			context.put("minit_Arahan", h.get("minit_Arahan"));
			context.put("tarikh_Minit_Arahan", h.get("tarikh_Minit_Arahan"));
			
			idSeksyen1 = (String) h.get("idSeksyen1");
			idPengarah1 = (String) h.get("idPenerima1");
			idPAR1 = (String) h.get("idPAR1");
			idSeksyen2 = (String) h.get("idSeksyen2");
			idPengarah2 = (String) h.get("idPenerima2");
			idPAR2 = (String) h.get("idPAR2");
			idSeksyen3 = (String) h.get("idSeksyen3");
			idPengarah3 = (String) h.get("idPenerima3");
			idPAR3 = (String) h.get("idPAR3");

			this.context.put("selectSeksyen1", HTML.SelectSeksyenPAR(
					"socSeksyen1", Utils.parseLong(idSeksyen1), "disabled",
					" class=\"disabled\""));
			this.context.put("selectPengarah1", HTML.SelectPengarahBySeksyen(
					idSeksyen1, "socPengarah1", Utils.parseLong(idPengarah1),
					"disabled", " class=\"disabled\""));
			this.context.put("selectPAR1", HTML.SelectPARByIdSeksyen(
					idSeksyen1, "socPAR1", Utils.parseLong(idPAR1), "disabled",
					" class=\"disabled\""));

			this.context.put("selectSeksyen2", HTML.SelectSeksyenPAR(
					"socSeksyen2", Utils.parseLong(idSeksyen2), "disabled",
					" class=\"disabled\""));
			this.context.put("selectPengarah2", HTML.SelectPengarahBySeksyen(
					idSeksyen2, "socPengarah2", Utils.parseLong(idPengarah2),
					"disabled", " class=\"disabled\""));
			this.context.put("selectPAR2", HTML.SelectPARByIdSeksyen(
					idSeksyen2, "socPAR2", Utils.parseLong(idPAR2), "disabled",
					" class=\"disabled\""));
			
			this.context.put("selectSeksyen3", HTML.SelectSeksyenPAR(
					"socSeksyen3", Utils.parseLong(idSeksyen3), "disabled",
					" class=\"disabled\""));
			this.context.put("selectPengarah3", HTML.SelectPengarahBySeksyen(
					idSeksyen3, "socPengarah3", Utils.parseLong(idPengarah3),
					"disabled", " class=\"disabled\""));
			this.context.put("selectPAR3", HTML.SelectPARByIdSeksyen(
					idSeksyen3, "socPAR3", Utils.parseLong(idPAR3), "disabled",
					" class=\"disabled\""));
		}
		
		else if ("simpanKemaskiniSeksyen3".equals(action1)) {
			
			// GET DROPDOWN PARAM
			String idSeksyen1 = getParam("socSeksyen1");
			if (idSeksyen1 == null || idSeksyen1.trim().length() == 0) {
				idSeksyen1 = "99999";
			}
			String idPengarah1 = getParam("socPengarah1");
			if (idPengarah1 == null || idPengarah1.trim().length() == 0) {
				idPengarah1 = "99999";
			}
			String idPAR1 = getParam("socPAR1");
			if (idPAR1 == null || idPAR1.trim().length() == 0) {
				idPAR1 = "99999";
			}
			String idSeksyen2 = getParam("socSeksyen2");
			if (idSeksyen2 == null || idSeksyen2.trim().length() == 0) {
				idSeksyen2 = "99999";
			}
			String idPengarah2 = getParam("socPengarah2");
			if (idPengarah2 == null || idPengarah2.trim().length() == 0) {
				idPengarah2 = "99999";
			}
			String idPAR2 = getParam("socPAR2");
			if (idPAR2 == null || idPAR2.trim().length() == 0) {
				idPAR2 = "99999";
			}
			String idSeksyen3 = getParam("socSeksyen3");
			if (idSeksyen3 == null || idSeksyen3.trim().length() == 0) {
				idSeksyen3 = "99999";
			}
			String idPengarah3 = getParam("socPengarah3");
			if (idPengarah3 == null || idPengarah3.trim().length() == 0) {
				idPengarah3 = "99999";
			}
			String idPAR3 = getParam("socPAR3");
			if (idPAR3 == null || idPAR3.trim().length() == 0) {
				idPAR3 = "99999";
			}
			
			context.put("mode", "ViewSeksyen3");
			context.put("readonlyMinit", "readonly");
			context.put("disabledMinit", "disabled");
			context.put("modeSeksyen", "3");
			context.put("action1", "simpanSeksyen3");

			vm = "app/pfd/frmKemaskiniMinitArahanKPTG.jsp";

			String idMinitArahanSeksyen3 = getParam("idMinitArahanSeksyen3");
			context.put("idMinitArahanSeksyen3", idMinitArahanSeksyen3);
			
			simpanKemaskiniSeksyen3(idMinitArahanSeksyen3, session);

			paparMinitArahanSeksyen3 = FrmLogDokumenTKPKData
					.getDataMinitArahanSeksyen3(idMinitArahanSeksyen3);

			Hashtable h = (Hashtable) paparMinitArahanSeksyen3.get(0);

			context.put("minit_Arahan", h.get("minit_Arahan"));
			context.put("tarikh_Minit_Arahan", h.get("tarikh_Minit_Arahan"));
			
			idSeksyen1 = (String) h.get("idSeksyen1");
			idPengarah1 = (String) h.get("idPenerima1");
			idPAR1 = (String) h.get("idPAR1");
			idSeksyen2 = (String) h.get("idSeksyen2");
			idPengarah2 = (String) h.get("idPenerima2");
			idPAR2 = (String) h.get("idPAR2");
			idSeksyen3 = (String) h.get("idSeksyen3");
			idPengarah3 = (String) h.get("idPenerima3");
			idPAR3 = (String) h.get("idPAR3");

			this.context.put("selectSeksyen1", HTML.SelectSeksyenPAR(
					"socSeksyen1", Utils.parseLong(idSeksyen1), "disabled",
					" class=\"disabled\""));
			this.context.put("selectPengarah1", HTML.SelectPengarahBySeksyen(
					idSeksyen1, "socPengarah1", Utils.parseLong(idPengarah1),
					"disabled", " class=\"disabled\""));
			this.context.put("selectPAR1", HTML.SelectPARByIdSeksyen(
					idSeksyen1, "socPAR1", Utils.parseLong(idPAR1), "disabled",
					" class=\"disabled\""));

			this.context.put("selectSeksyen2", HTML.SelectSeksyenPAR(
					"socSeksyen2", Utils.parseLong(idSeksyen2), "disabled",
					" class=\"disabled\""));
			this.context.put("selectPengarah2", HTML.SelectPengarahBySeksyen(
					idSeksyen2, "socPengarah2", Utils.parseLong(idPengarah2),
					"disabled", " class=\"disabled\""));
			this.context.put("selectPAR2", HTML.SelectPARByIdSeksyen(
					idSeksyen2, "socPAR2", Utils.parseLong(idPAR2), "disabled",
					" class=\"disabled\""));
			
			this.context.put("selectSeksyen3", HTML.SelectSeksyenPAR(
					"socSeksyen3", Utils.parseLong(idSeksyen3), "disabled",
					" class=\"disabled\""));
			this.context.put("selectPengarah3", HTML.SelectPengarahBySeksyen(
					idSeksyen3, "socPengarah3", Utils.parseLong(idPengarah3),
					"disabled", " class=\"disabled\""));
			this.context.put("selectPAR3", HTML.SelectPARByIdSeksyen(
					idSeksyen3, "socPAR3", Utils.parseLong(idPAR3), "disabled",
					" class=\"disabled\""));
		}
		
		else if ("hapusLogDokumenMasukKPTG".equals(action1)){
			
			vm = "app/pfd/frmLogDokumenMasukKPTG.jsp";
			context.put("mode", "View");
			context.put("jenis_Dokumen", "0");
			context.put("tarikh_Diterima_Dihantar", "");
			context.put("no_Rujukan_Lain", "");
			context.put("tarikh_Dokumen", "");
			context.put("pengirim_Dokumen", "");
			context.put("tajuk_Dokumen", "");
			context.put("paparArahan",paparArahan);
 			
			String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
     		
     		hapusLogDokumenMasukKPTG(session);
			
			listTKP(session);
			listTKP = FrmLogDokumenTKPKData.getListTKP();
			this.context.put("SenaraiTKP", listTKP);
		}

        else if("cetakLogDokumenKPTG".equals(action1)){
       	 vm = "app/pfd/frmCetakLogDokumenKPTG.jsp";
       	 context.put("action1", "cetakLogDokumenKPTG");
        }
		
		else {

			vm = "app/pfd/frmCariDokumenTKPK.jsp";

			String user_name = (String) session
					.getAttribute("_portal_username");
			String user_id = (String) session.getAttribute("_ekptg_user_id");
			String user_role = (String) session.getAttribute("_portal_role");

			context.put("user_Role", user_role);
			context.put("user_Name", user_name);
			context.put("user_Id", user_id);

			this.context.put("JumlahDokumen",FrmDaftarLogDokumenTKPKData.totalDokumen(session));
			
			if (!"".equals(getParam("no_Rujukan_Lain")))
				no_Rujukan_Lain = getParam("no_Rujukan_Lain");
			if (!"".equals(getParam("tajuk_Dokumen")))
				tajuk_Dokumen = getParam("tajuk_Dokumen");
			if (!"".equals(getParam("tarikh_Dokumen")))
				tarikh_Dokumen = getParam("tarikh_Dokumen");
			if (!"".equals(getParam("tarikh_Diterima_Dihantar")))
				tarikh_Diterima_Dihantar = getParam("tarikh_Diterima_Dihantar");

			FrmDaftarLogDokumenTKPKData.setCarianLogDokumenTKPK(no_Rujukan_Lain, tajuk_Dokumen, tarikh_Dokumen,tarikh_Diterima_Dihantar, status_logdokumen,flag_logdokumen, user_id);
			listLogDokumenTKPK = FrmDaftarLogDokumenTKPKData.getListLogDokumenTKPK();
			this.context.put("SenaraiDokumenTKPK", listLogDokumenTKPK);
			this.context.put("no_Rujukan_Lain", no_Rujukan_Lain);
			this.context.put("tajuk_Dokumen", tajuk_Dokumen);
			this.context.put("tarikh_Dokumen", tarikh_Dokumen);
			this.context.put("tarikh_Diterima_Dihantar",tarikh_Diterima_Dihantar);
			// this.context.put("status_logdokumen", status_logdokumen);

			setupPage(session,action1,listLogDokumenTKPK);

		}

		return vm;
	}

	private void uploadLampiran(String idLogDokumenKPTG) throws Exception {
		uploadFiles(idLogDokumenKPTG);
		
	}

	private void uploadFiles(String idLogDokumenKPTG) throws Exception {
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);

	    List items = upload.parseRequest(request);
	    Iterator itr = items.iterator();
	    while (itr.hasNext()) {
	      FileItem item = (FileItem)itr.next();
	      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
	    	  saveData1(item, idLogDokumenKPTG);
	      }
	    }
		
	}

	private void hapusLampiran(HttpSession session) throws Exception {
		String idLampiran = getParam("idLampiran");
		FrmLogDokumenTKPKData.hapus(idLampiran);
		
	}

	private void listLampiran(HttpSession session) throws Exception {
		String id = getParam("idLogDokumenKPTG");
		FrmLogDokumenTKPKData.setListLampiran(id);	
	}

	private String getEmail1(String idPenerima1) throws Exception {
		
		return FrmLogDokumenTKPKData.getEmailPenerima1(idPenerima1);
	}

	private String getEmail2(String idPenerima2) throws Exception {
		
		return FrmLogDokumenTKPKData.getEmailPenerima2(idPenerima2);
	}
	
	private String getEmail3(String idPenerima3) throws Exception {
		
		return FrmLogDokumenTKPKData.getEmailPenerima3(idPenerima3);
	}

	private String getEmail(String id_tkp) throws Exception {
		// TODO Auto-generated method stub
		return FrmLogDokumenTKPKData.getEmailPTFail(id_tkp);
	}

	private void hapusLogDokumenMasukKPTG(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idLogDokumenKPTG = getParam("idLogDokumenKPTG");

			Hashtable h = new Hashtable();
			h.put("idLogDokumenKPTG",idLogDokumenKPTG);
			h.put("id_Kemaskini",user_id);

			FrmLogDokumenTKPKData.deleteLogDokumenMasukKPTG(h);
		
	}

	private void updateLogDokumenSeksyenLainKPTG(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idLogDokumen = getParam("idLogDokumen");
		String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
		String idSeksyen = getParam("idSeksyen");
		String idNegeri = getParam("idNegeri");
		String idPenerima = getParam("socPegawai");
			Hashtable h = new Hashtable();
			h.put("idLogDokumen",idLogDokumen);
			h.put("idLogDokumenKPTG",idLogDokumenKPTG);
		    h.put("tarikh_Dokumen_Diterima", getParam("tarikh_Dokumen_Diterima"));
			h.put("id_nama_penerima", getParam("socPegawai"));
			h.put("idSeksyen",idSeksyen);
			h.put("idNegeri",idNegeri);
		    h.put("id_Kemaskini",user_id);
		    h.put("status_logdokumen","1");
		    
//		    if(!idLogDokumenKPTG.equals(""))
//		    {
//		    	FrmLogDokumenData.updateLogDokumenSeksyenKPTG(h);
//		    }
//		    else
//		    {
		    	FrmLogDokumenTKPKData.updateLogDokumenSeksyenLainKPTG(h);
//		    }
		
	}

	private void listPegawai(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmLogDokumenTKPKData.setListPegawai(user_id,(String) session.getAttribute("_ekptg_user_negeri"));
		
	}


	private void listMinitArahanPengarah1(HttpSession session) throws Exception {
		String id = getParam("idLogDokumenKPTG");
		FrmLogDokumenTKPKData.setDataMinitArahanKetuaPengarah1(id);
		
	}
	
	private void listMinitArahanPengarah2(HttpSession session) throws Exception {
		String id = getParam("idLogDokumenKPTG");
		FrmLogDokumenTKPKData.setDataMinitArahanKetuaPengarah2(id);
		
	}
	
	private void listMinitArahanPengarah3(HttpSession session) throws Exception {
		String id = getParam("idLogDokumenKPTG");
		FrmLogDokumenTKPKData.setDataMinitArahanKetuaPengarah3(id);
		
	}


	public void setupPage(HttpSession session,String action,Vector list) {
		try {
		
		this.context.put("totalRecords",list.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
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
	    	
	    Paging paging = new Paging(session,list,itemsPerPage);
		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("SenaraiFail",paging.getPage(page));
	    this.context.put("page", new Integer(page));
	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}
	
	private String simpanSeksyen1(HttpSession session) throws Exception {
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
		String minitArahan = getParam("minit_Arahan");
		String tarikhMinit = getParam("tarikh_Minit_Arahan");
		String socPengarahSeksyen1 = getParam("socPengarah1");
		String socSeksyenPAR1 = getParam("socSeksyen1");
		String socPAR1 = getParam("socPAR1");

		Hashtable h = new Hashtable();
		h.put("idLogDokumenKPTG", idLogDokumenKPTG);
		h.put("minitArahan", minitArahan);
		h.put("tarikhMinit", tarikhMinit);
		h.put("pegawaiMengarah", user_id);
		h.put("pegawaiMenerima1", socPengarahSeksyen1);
		h.put("idSeksyen1", socSeksyenPAR1);
		h.put("PAR1", socPAR1);
		h.put("status_LogDokumen", "1");
		h.put("id_Masuk", user_id);

		return FrmLogDokumenTKPKData.addSeksyen1(h);
	}

	private void simpanKemaskiniSeksyen1(String idMinitArahanSeksyen1,
			HttpSession session) throws Exception {
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
		String minitArahan = getParam("minit_Arahan");
		String tarikhMinit = getParam("tarikh_Minit_Arahan");
		String socPengarahSeksyen1 = getParam("socPengarah1");
		String socSeksyenPAR1 = getParam("socSeksyen1");
		String socPAR1 = getParam("socPAR1");

		Hashtable h = new Hashtable();
		h.put("idLogDokumenKPTG", idLogDokumenKPTG);
		h.put("minitArahan", minitArahan);
		h.put("tarikhMinit", tarikhMinit);
		h.put("pegawaiMengarah", user_id);
		h.put("pegawaiMenerima1", socPengarahSeksyen1);
		h.put("idSeksyen1", socSeksyenPAR1);
		h.put("PAR1", socPAR1);
		h.put("status_LogDokumen", "1");
		h.put("id_Masuk", user_id);

		FrmLogDokumenTKPKData.updateSeksyen1(idMinitArahanSeksyen1, h);
	}

	private String simpanSeksyen2(HttpSession session) throws Exception {
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
		String minitArahan = getParam("minit_Arahan");
		String tarikhMinit = getParam("tarikh_Minit_Arahan");
		String socPengarahSeksyen2 = getParam("socPengarah1");
		String socSeksyenPAR2 = getParam("socSeksyen1");
		String socPAR2 = getParam("socPAR1");
		String socPengarahSeksyen3 = getParam("socPengarah2");
		String socSeksyenPAR3 = getParam("socSeksyen2");
		String socPAR3 = getParam("socPAR2");

		Hashtable h = new Hashtable();
		h.put("idLogDokumenKPTG", idLogDokumenKPTG);
		h.put("minitArahan", minitArahan);
		h.put("tarikhMinit", tarikhMinit);
		h.put("pegawaiMengarah", user_id);
		h.put("pegawaiMenerima2", socPengarahSeksyen2);
		h.put("idSeksyen2", socSeksyenPAR2);
		h.put("PAR2", socPAR2);
		h.put("pegawaiMenerima3", socPengarahSeksyen3);
		h.put("idSeksyen3", socSeksyenPAR3);
		h.put("PAR3", socPAR3);
		h.put("status_LogDokumen", "1");
		h.put("id_Masuk", user_id);

		return FrmLogDokumenTKPKData.addSeksyen2(h);
	}

	private void simpanKemaskiniSeksyen2(String idMinitArahanSeksyen2,
			HttpSession session) throws Exception {
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
		String minitArahan = getParam("minit_Arahan");
		String tarikhMinit = getParam("tarikh_Minit_Arahan");
		String socPengarahSeksyen2 = getParam("socPengarah1");
		String socSeksyenPAR2 = getParam("socSeksyen1");
		String socPAR2 = getParam("socPAR1");
		String socPengarahSeksyen3 = getParam("socPengarah2");
		String socSeksyenPAR3 = getParam("socSeksyen2");
		String socPAR3 = getParam("socPAR2");

		Hashtable h = new Hashtable();
		h.put("idLogDokumenKPTG", idLogDokumenKPTG);
		h.put("minitArahan", minitArahan);
		h.put("tarikhMinit", tarikhMinit);
		h.put("pegawaiMengarah", user_id);
		h.put("pegawaiMenerima2", socPengarahSeksyen2);
		h.put("idSeksyen2", socSeksyenPAR2);
		h.put("PAR2", socPAR2);
		h.put("pegawaiMenerima3", socPengarahSeksyen3);
		h.put("idSeksyen3", socSeksyenPAR3);
		h.put("PAR3", socPAR3);
		h.put("status_LogDokumen", "1");
		h.put("id_Masuk", user_id);

		FrmLogDokumenTKPKData.simpanKemaskiniSeksyen2(idMinitArahanSeksyen2, h);
	}

	private String simpanSeksyen3(HttpSession session) throws Exception {
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
		String minitArahan = getParam("minit_Arahan");
		String tarikhMinit = getParam("tarikh_Minit_Arahan");
		String socPengarahSeksyen4 = getParam("socPengarah1");
		String socSeksyenPAR4 = getParam("socSeksyen1");
		String socPAR4 = getParam("socPAR1");
		String socPengarahSeksyen5 = getParam("socPengarah2");
		String socSeksyenPAR5 = getParam("socSeksyen2");
		String socPAR5 = getParam("socPAR2");
		String socPengarahSeksyen6 = getParam("socPengarah3");
		String socSeksyenPAR6 = getParam("socSeksyen3");
		String socPAR6 = getParam("socPAR3");

		Hashtable h = new Hashtable();
		h.put("idLogDokumenKPTG", idLogDokumenKPTG);
		h.put("minitArahan", minitArahan);
		h.put("tarikhMinit", tarikhMinit);
		h.put("pegawaiMengarah", user_id);
		h.put("pegawaiMenerima4", socPengarahSeksyen4);
		h.put("idSeksyen4", socSeksyenPAR4);
		h.put("PAR4", socPAR4);
		h.put("pegawaiMenerima5", socPengarahSeksyen5);
		h.put("idSeksyen5", socSeksyenPAR5);
		h.put("PAR5", socPAR5);
		h.put("pegawaiMenerima6", socPengarahSeksyen6);
		h.put("idSeksyen6", socSeksyenPAR6);
		h.put("PAR6", socPAR6);
		h.put("status_LogDokumen", "1");
		h.put("id_Masuk", user_id);

		return FrmLogDokumenTKPKData.addSeksyen3(h);
	}
	
	private void simpanKemaskiniSeksyen3(String idMinitArahanSeksyen3, HttpSession session) throws Exception {
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
		String minitArahan = getParam("minit_Arahan");
		String tarikhMinit = getParam("tarikh_Minit_Arahan");
		String socPengarahSeksyen4 = getParam("socPengarah1");
		String socSeksyenPAR4 = getParam("socSeksyen1");
		String socPAR4 = getParam("socPAR1");
		String socPengarahSeksyen5 = getParam("socPengarah2");
		String socSeksyenPAR5 = getParam("socSeksyen2");
		String socPAR5 = getParam("socPAR2");
		String socPengarahSeksyen6 = getParam("socPengarah3");
		String socSeksyenPAR6 = getParam("socSeksyen3");
		String socPAR6 = getParam("socPAR3");

		Hashtable h = new Hashtable();
		h.put("idLogDokumenKPTG", idLogDokumenKPTG);
		h.put("minitArahan", minitArahan);
		h.put("tarikhMinit", tarikhMinit);
		h.put("pegawaiMengarah", user_id);
		h.put("pegawaiMenerima4", socPengarahSeksyen4);
		h.put("idSeksyen4", socSeksyenPAR4);
		h.put("PAR4", socPAR4);
		h.put("pegawaiMenerima5", socPengarahSeksyen5);
		h.put("idSeksyen5", socSeksyenPAR5);
		h.put("PAR5", socPAR5);
		h.put("pegawaiMenerima6", socPengarahSeksyen6);
		h.put("idSeksyen6", socSeksyenPAR6);
		h.put("PAR6", socPAR6);
		h.put("status_LogDokumen", "1");
		h.put("id_Masuk", user_id);

		FrmLogDokumenTKPKData.updateSeksyen3(idMinitArahanSeksyen3,h);
	}

	private String simpanLogDokumenKeluar(HttpSession session) throws Exception {
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		String idFail = getParam("idFail");
		String minit = getParam("c1");
		String laporan = getParam("c2");
		String idTKP = getParam("socTKP");
		String idNegeri = getParam("idNegeri");
		String idSeksyen = getParam("idSeksyen");


		Hashtable h = new Hashtable();
		h.put("id_Jenisdokumen", getParam("socJenisDokumen"));
		h.put("id_Jenispenghantaran", getParam("socJenisPenghantaran"));
		h.put("no_Rujukan_Lain", getParam("no_Rujukan_LainEncode"));
		h.put("tajuk_Dokumen", getParam("tajuk_DokumenEncode"));
		h.put("penerima_Dokumen", getParam("penerima_Dokumen"));
		h.put("tarikh_Dokumen", getParam("tarikh_Dokumen"));
		h.put("tarikh_Diterima_Dihantar", getParam("tarikh_Diterima_Dihantar"));
		h.put("id_PengirimKeluar", idTKP);
		h.put("idNegeri", idNegeri);
		h.put("idSeksyen", idSeksyen);
		// h.put("dirujuk_kepada",penerima_Dokumen);
		h.put("status_LogDokumen", "1");
		h.put("flag_LogDokumen", "2");
		h.put("id_Masuk", user_id);

		if ("true".equalsIgnoreCase(minit)) {
			h.put("idMinit", "1");
		} else {
			h.put("idMinit", "0");
		}
		if ("true".equalsIgnoreCase(laporan)) {
			h.put("idLaporan", "1");
		} else {
			h.put("idLaporan", "0");
		}

		return FrmLogDokumenTKPKData.addKeluar(h);
	}

	private void listPengarahSeksyen1(HttpSession session) throws Exception {
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		FrmLogDokumenTKPKData.setListPengarahSeksyen1(user_id);

	}

	private void listPengarahSeksyen2(HttpSession session) throws Exception {
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		FrmLogDokumenTKPKData.setListPengarahSeksyen2(user_id);

	}

	private void listPengarahSeksyen3(HttpSession session) throws Exception {
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		FrmLogDokumenTKPKData.setListPengarahSeksyen3(user_id);

	}

	private void listPengarahSeksyen4(HttpSession session) throws Exception {
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		FrmLogDokumenTKPKData.setListPengarahSeksyen4(user_id);

	}

	private void listPengarahSeksyen5(HttpSession session) throws Exception {
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		FrmLogDokumenTKPKData.setListPengarahSeksyen5(user_id);

	}

	private void listPengarahSeksyen6(HttpSession session) throws Exception {
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		FrmLogDokumenTKPKData.setListPengarahSeksyen6(user_id);

	}

	private void downloadDraf(String idLogDokumenKPTG) throws Exception {
		System.out.println("=======Download file==========" + idLogDokumenKPTG);
		uploadFiles1(idLogDokumenKPTG);

	}
	
	private void uploadFiles1(String idLogDokumenKPTG) throws Exception {
		 DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload upload = new ServletFileUpload(factory);

		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		      FileItem item = (FileItem)itr.next();
		     // myLogger.info("ContentType :" + item.getContentType());
		      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    	  saveData1(item, idLogDokumenKPTG);
		      }
		    }
		
	}

	private void saveData1(FileItem item, String idLogDokumenKPTG)
			throws Exception {
		Db db = null;
		HttpSession session = this.request.getSession();
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String currentDate = "to_date('" + sdf.format(now) + "','dd/MM/yyyy')";
		String id = idLogDokumenKPTG;
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		try {
			db = new Db();
			long id_Lampiran = DB.getNextID("TBLPFDRUJLAMPIRANLOGKPTG_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("insert into TBLPFDRUJLAMPIRANLOGKPTG "
							+ "(id_Lampiran,id_LogDokumenKPTG,nama_Fail,jenis_Mime,content) "
							+ "values(?,?,?,?,?)");
			ps.setLong(1, id_Lampiran);
			ps.setString(2, id);
			ps.setString(3, item.getName());
			ps.setString(4, item.getContentType());
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.executeUpdate();
			con.commit();
		} finally {
			if (db != null)
				db.close();
		}

	}

	private String simpanLogDokumenMasuk(HttpSession session) throws Exception {
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		String idFail = getParam("idFail");
		String minit = getParam("c1");
		String laporan = getParam("c2");
		String idTKP = getParam("socTKP");
		String idNegeri = getParam("idNegeri");
		String idSeksyen = getParam("idSeksyen");
		String dirujuk_Kepada = getParam("dirujuk_Kepada");
		
		
		String no_Rujukan_Lain = getParam("no_Rujukan_Lain");
		//String no_Rujukan_LainEncode = java.net.URLDecoder.decode(no_Rujukan_Lain);
		String pengirim_Dokumen = getParam("pengirim_Dokumen");
		//String pengirim_DokumenEncode = java.net.URLDecoder.decode(pengirim_Dokumen);
		String tajuk_Dokumen = getParam("tajuk_Dokumen");
		//String tajuk_DokumenEncode = java.net.URLDecoder.decode(tajuk_Dokumen);

		Hashtable h = new Hashtable();
		h.put("id_Jenisdokumen", getParam("socJenisDokumen"));
		h.put("no_Rujukan_Lain", getParam("no_Rujukan_Lain"));
		h.put("tajuk_Dokumen", getParam("tajuk_Dokumen"));
		h.put("pengirim_Dokumen", getParam("pengirim_Dokumen"));
		// h.put("id_PenerimaDokumen", getParam("socPegawai"));
		// h.put("id_PengirimDokumen", getParam("socPegawai"));
		// h.put("penerima_Dokumen", getParam("penerima_Dokumen"));
		h.put("tarikh_Dokumen", getParam("tarikh_Dokumen"));
		h.put("tarikh_Diterima_Dihantar", getParam("tarikh_Diterima_Dihantar"));
		h.put("id_TKP", idTKP);
		h.put("idNegeri", idNegeri);
		h.put("idSeksyen", idSeksyen);
		h.put("dirujuk_kepada", dirujuk_Kepada);
		h.put("status_LogDokumen", "1");
		h.put("flag_LogDokumen", "1");
		h.put("id_Masuk", user_id);

		if ("true".equalsIgnoreCase(minit)) {
			h.put("idMinit", "1");
		} else {
			h.put("idMinit", "0");
		}
		if ("true".equalsIgnoreCase(laporan)) {
			h.put("idLaporan", "1");
		} else {
			h.put("idLaporan", "0");
		}

		return FrmLogDokumenTKPKData.addMasuk(h);
	}

	private void listTKP(HttpSession session) throws Exception {
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		FrmLogDokumenTKPKData.setListTKP(user_id);

	}

	private void view_Seksyen(HttpSession session) throws Exception {
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		FrmLogDokumenTKPKData.setSeksyen(user_id);

	}

}
