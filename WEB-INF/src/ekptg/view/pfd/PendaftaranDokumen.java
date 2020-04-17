package ekptg.view.pfd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import ekptg.intergration.XEkptgEmailSender;
import ekptg.model.pfd.FrmDaftarDokumenData;
import ekptg.model.pfd.FrmKemaskiniDokumenData;
import ekptg.model.pfd.FrmLogDokumenData;
import ekptg.model.pfd.FrmPaparLampiranData;
import ekptg.model.pfd.FrmSenaraiTugasanPFD;

public class PendaftaranDokumen extends AjaxBasedModule{
	FrmKemaskiniDokumenData logic = new FrmKemaskiniDokumenData();
	private Vector getLampiran;
	
	public String doTemplate2() throws Exception{
		
			HttpSession session = request.getSession();
	    	//prevent duplicate when refresh page
	    	String doPost = (String) session.getAttribute("doPost");
			String submit = getParam("command");
			String action1 = getParam("action1");
			String action = getParam("action");
			
			if ("doChangeItemPerPage".equals(action) ||
					"getPage".equals(action)) {
				action1 = action;
			}
			
			if ("doChangeItemPerPage1".equals(action) || 
					"getPage1".equals(action) || 
					"getPrevious1".equals(action) ||
					"getNext1".equals(action)) {
				action1 = action;
			}
			
			context.put("action1",action1);
			String mode = getParam("mode");
			String pagemode = getParam("pagemode")==null?"":getParam("pagemode");
			String no_Ruju = getParam("no_Rujukan_Dokumen");
			String vm = "";
			String noFail = "";
			String no_Fail = "";
			String noFailLama ="";
			String id_Fail = "";
	        String tajukFail = "";
	        String negeri = getParam("socNegeriD");
	        String seksyen = getParam("socSeksyenD");
	        String status = getParam("socStatusD");
	        String idSeksyen = getParam("socSeksyenPAR");
	        String tarikhDaftar = "";
	        String jnsDok = "0";
	        String noRujDok = "";
	        String tajukDok = "";    	
			Vector paparFail = null;
			Vector listLogDokumenByUserId = null;
			Vector listFail = null;
			Vector listPTFail = null;
			Vector listPTFail2 = null;
			Vector listPA = null;
			Vector listPAKeluar = null;
			Vector listSubjaket = null;
			Vector listPegawai = null;
			Vector listPegawai1 = null;
			Vector getSeksyen = null;
			Vector listPegawai2 = null;
			Vector listPegawai3 = null;
			Vector listPegawaiTinggi = null;
			Vector listLampiranLogDokumen = null;
			Vector listLampiranDokumen = null;
			Vector listDokumen = null;
			Vector listDokumenFailSubjaket = null;
			String listStatusLogDokumen = "";
			Vector listMinitArahanPengarah = null;	
			Vector listMinitArahanPegawai1 = null;
			Vector listMinitArahanPegawai2 = null;
			Vector listMinitArahanPegawai3 = null;
			Vector listMinitArahanPegawai4 = null;
			Vector listMinitArahanSelesai = null;
			Vector listMinitArahanSeksyenLain = null;
			Vector paparDokumen = null;
			Vector paparDokumenFail = null;
			Vector paparCountLevelArahan = null;
			Vector paparCountDokumen = null;
			Vector paparNoDokumen = null;
			Vector listMinit = null;
			Vector listLampiran = null;
			Vector simpanLogDokumen = null;
			Vector paparMinit = null;
			Vector paparSeksyen = null;
			Vector paparMinitArahan2b = null;
			Vector paparMinitArahan2c = null;
			Vector paparMinitArahanSeksyenLain = null;
			Vector getPARbyIdSeksyen = null;
			Vector paparLampiranDokumenMasuk = null;
			Vector paparLampiranDokumenKeluar = null;
			
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			this.context.put("Util",new Utils());
			
			if ("tabDokumenCarian".equals(action1)){
				vm = "app/pfd/frmKemaskiniDokumenCarian.jsp";
				
				view_fail(session);
				paparFail = FrmKemaskiniDokumenData.getDataFail();
		
				Hashtable h = (Hashtable) paparFail.get(0);
				context.put("status",h.get("keterangan"));
				context.put("idFail",h.get("idFail"));
		    	context.put("noFail",h.get("noFail"));
		    	context.put("tajukFail",h.get("tajukFail"));
		    	context.put("keterangan",h.get("keterangan"));
		    	context.put("id_Status",h.get("id_Status"));
		    	context.put("tarikh_Daftar_Fail",h.get("tarikh_Daftar_Fail"));
				
		    	String idFail = getParam("idFail");
				
				listLogDokumenByUserId(session);
		    	listLogDokumenByUserId = FrmSenaraiTugasanPFD.getListLogDokumen();
		    	this.context.put("SenaraiDokumenByUserId", listLogDokumenByUserId);
		    	
			}
			
			else if ("tabDokumenMasuk".equals(action1)){
				
				pagemode = getParam("pagemode");
		    	String idLogDokumen = getParam("idLogDokumen");
		    	
				
				String idFail = "";
				if(!"".equals(getParam("idFail"))){
					idFail = getParam("idFail");
				}
				else{
					idFail = getParam("id_Fail");
				}
				
				
				if(pagemode.equalsIgnoreCase("1")){
					vm = "app/pfd/frmKemaskiniDokumenMasuk.jsp";
					context.put("mode","NewLog");
					context.put("readonly","");
					context.put("disabled","");
					context.put("readonlyBil","");
					context.put("disabledBil","");
					context.put("readonlyNo","readonly");
					context.put("disabledNo","disabled");
					context.put("readonlyPT","");
					context.put("disabledPT","");
					context.put("paparArahan","false");
					
					view_fail(session);
					paparFail = FrmKemaskiniDokumenData.getDataFail();
					
					if(paparFail.isEmpty()){
						context.put("status","");
						context.put("idFail","");
				    	context.put("noFail","");
				    	context.put("tajukFail","");
				    	context.put("keterangan","");
				    	context.put("id_Status","");
				    	context.put("tarikh_Daftar_Fail","");
				    	context.put("no_Rujukan_Dokumen","");
				    	context.put("bil_Minit_Dokumen","");
						
					}
					else{
						
						Hashtable h = (Hashtable) paparFail.get(0);
						context.put("status",h.get("keterangan"));
						context.put("idFail",h.get("idFail"));
				    	context.put("noFail",h.get("noFail"));
				    	context.put("tajukFail",h.get("tajukFail"));
				    	context.put("keterangan",h.get("keterangan"));
				    	context.put("id_Status",h.get("id_Status"));
				    	context.put("tarikh_Daftar_Fail",h.get("tarikh_Daftar_Fail"));
				    	
				    	view_CountDokumen(session);
				    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
						Hashtable hc = (Hashtable) paparCountDokumen.get(0);
						
						String a = hc.get("jumlah_Dokumen").toString();
						if (Integer.parseInt(hc.get("jumlah_Dokumen").toString()) >= 100){
							context.put("mode","Disable");
						}
						else
						{
							context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
						}
						
				    	view_DokumenFail(session);
				    	paparDokumenFail = FrmKemaskiniDokumenData.getDataDokumenFail(idFail);
						Hashtable ha = (Hashtable) paparDokumenFail.get(0);
						Integer ab = Integer.parseInt(ha.get("bil_Minit_Dokumen").toString());
						 ab =	ab + 1; 
						context.put("bil_Minit_Dokumen",Integer.parseInt(ha.get("bil_Minit_Dokumen").toString()) + 1 );
						
						view_NoDokumen(session);
						paparNoDokumen = FrmKemaskiniDokumenData.getDataNoDokumen(idFail);
						Hashtable hb = (Hashtable) paparNoDokumen.get(0);
						context.put("no_Rujukan_Dokumen",hb.get("no_Rujukan_Dokumen"));					
					
					}
			
			    	Vector paparLogDokumen = FrmLogDokumenData.getDataLogDokumen(idLogDokumen);
					Hashtable hd = (Hashtable) paparLogDokumen.get(0);
					context.put("idLogDokumen",hd.get("idLogDokumen"));
					context.put("jenis_Dokumen",hd.get("jenis_Dokumen"));
			    	context.put("no_Rujukan_Lain",hd.get("no_Rujukan_Lain"));
			    	context.put("tajuk_Dokumen",hd.get("tajuk_Dokumen"));
					context.put("tarikh_Dokumen_Keluar",hd.get(""));
			    	context.put("tarikh_Dokumen_Diterima",hd.get("tarikh_Dokumen_Diterima"));
			    	context.put("tarikh_Dokumen",hd.get("tarikh_Dokumen"));
					context.put("pengirim_Dokumen",hd.get("pengirim_Dokumen"));
					context.put("tag_Dokumen",hd.get("tag_Dokumen"));
			    	context.put("tarikh_Daftar",hd.get(""));
			    	context.put("flag_Dokumen",hd.get(""));
			    	if(hd.get("idMinit").equals("1")){
	 				    context.put("checkedc1","checked");
	 			    }
	 			    else if(hd.get("idMinit")!= null)
	 			    {
	 			    	context.put("checkedc1","");
	 			    }
	 			    else
	 			    {
	 			    	context.put("checkedc1","");
	 			    }
	 			    if(hd.get("idLaporan").equals("1")){
	 			    	context.put("checkedc2","checked");
	 			    }
	 			    else if(hd.get("idLaporan")!= null)
	 			    {
	 			    	context.put("checkedc2","");
	 			    }
	 			    else
	 			    {
	 			    	context.put("checkedc2","");
	 			    }
	 			    if(hd.get("idCD").equals("1")){
	 			    	context.put("checkedc3","checked");
	 			    }
	 			    else if(hd.get("idCD")!= null)
	 			    {
	 			    	context.put("checkedc3","");
	 			    }
	 			    else
	 			    {
	 			    	context.put("checkedc3","");
	 			    }

			    	
			    	if(!idLogDokumen.equals(null)){
			    		context.put("mode","NewLog");
			    		listPegawaiTinggi(session);
				    	listPegawaiTinggi = FrmLogDokumenData.getListPegawai();
			    		this.context.put("SenaraiPegawaiTinggi",listPegawaiTinggi);
			    		
			    		Vector listPegawaiMasuk = FrmLogDokumenData.getDataPegawai(idLogDokumen);
		        		Hashtable x = (Hashtable)listPegawaiMasuk.elementAt(0);
		     			this.context.put("selectidPegawaiTinggi",x.get("user_id"));
			    		
			    	}
			    	else{
			    		listPegawai = FrmLogDokumenData.getDataPegawai(idLogDokumen);
			    		this.context.put("SenaraiPegawai",listPegawai);
			    		
			    		Vector listPegawaiMasuk = FrmLogDokumenData.getDataPegawai(idLogDokumen);
		        		Hashtable x = (Hashtable)listPegawaiMasuk.elementAt(0);
		     			this.context.put("selectidPegawaiTinggi",x.get("user_id"));
			    	}
			    	
			    	listStatusLogDokumen = FrmLogDokumenData.getListStatusLogDokumen(idLogDokumen);
		    		this.context.put("SenaraiStatusLogDokumen",listStatusLogDokumen);
			    	
			    	listPegawaiTinggi(session);
			    	listPegawaiTinggi = FrmLogDokumenData.getListPegawai();
		    		this.context.put("SenaraiPegawaiTinggi",listPegawaiTinggi);
			    	
			    	listPA(session);
			    	listPA = FrmKemaskiniDokumenData.getListPA();
		    		this.context.put("SenaraiPA",listPA);
		    				    		
		    		listLampiranLogDokumen(session);
      				listLampiranLogDokumen = FrmLogDokumenData.getListLampiranLogDokumen();
            		this.context.put("SenaraiListLampiranLogDokumen",listLampiranLogDokumen);
            		this.context.put("listLampiranLogDokumen_size", listLampiranLogDokumen.size());
            		
					listDokumen(session);
					listDokumen = FrmKemaskiniDokumenData.getListDokumen();
					this.context.put("SenaraiDokumen",listDokumen); 			
			    	
				}
				
				else{
					vm = "app/pfd/frmKemaskiniDokumenMasuk.jsp";
					context.put("mode","New");
					context.put("readonly","readonly");
					context.put("disabled","disabled");
					context.put("readonlyBil","readonly");
					context.put("disabledBil","disabled");
					context.put("readonlyNo","readonly");
					context.put("disabledNo","disabled");
					context.put("readonlyPT","readonly");
					context.put("disabledPT","disabled");
					context.put("paparArahan","false");
					
					view_fail(session);
					paparFail = FrmKemaskiniDokumenData.getDataFail();
			
					Hashtable h = (Hashtable) paparFail.get(0);
					context.put("status",h.get("keterangan"));
					context.put("idFail",h.get("idFail"));
			    	context.put("noFail",h.get("noFail"));
			    	context.put("tajukFail",h.get("tajukFail"));
			    	context.put("keterangan",h.get("keterangan"));
			    	context.put("id_Status",h.get("id_Status"));
			    	context.put("tarikh_Daftar_Fail",h.get("tarikh_Daftar_Fail"));
								    	
			    	view_CountDokumen(session);
			    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
					Hashtable hc = (Hashtable) paparCountDokumen.get(0);
					
					String a = hc.get("jumlah_Dokumen").toString();
					if (Integer.parseInt(hc.get("jumlah_Dokumen").toString()) >= 100){
						context.put("mode","Disable");
					}
					else
					{
						context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
					}
					
			    	view_DokumenFail(session);
			    	paparDokumenFail = FrmKemaskiniDokumenData.getDataDokumenFail(idFail);
					Hashtable ha = (Hashtable) paparDokumenFail.get(0);
					Integer ab = Integer.parseInt(ha.get("bil_Minit_Dokumen").toString());
					 ab =	ab + 1; 
					context.put("bil_Minit_Dokumen",Integer.parseInt(ha.get("bil_Minit_Dokumen").toString()) + 1 );
					
					view_NoDokumen(session);
					paparNoDokumen = FrmKemaskiniDokumenData.getDataNoDokumen(idFail);
					Hashtable hb = (Hashtable) paparNoDokumen.get(0);
					context.put("no_Rujukan_Dokumen",hb.get("no_Rujukan_Dokumen"));
					
					if ("minit" != null){
						this.context.put("checkboxMinit", "1");
					}
					else{
						this.context.put("checkboxMinit", "0");
					}
				
					if ("laporan" != null){
						this.context.put("checkboxLaporan", "1");
					}
					else{
						this.context.put("checkboxLaporan", "0");
					}
					
					if ("cd" != null){
						this.context.put("checkboxCD", "1");
					}
					else{
						this.context.put("checkboxCD", "0");
					}
				
				
				
					context.put("id_Dokumen","");
			    	context.put("jenis_Dokumen","");
			    	context.put("no_Rujukan_Lain","");
			    	context.put("tajuk_Dokumen","");
			    	context.put("tag_Dokumen","");
			    	context.put("tarikh_Dokumen", "");
			    	context.put("tarikh_Dokumen_Diterima","");
			    	context.put("tarikh_Dokumen_Masuk","");
			    	context.put("pengirim_Dokumen","");
					context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB"));
			    	context.put("tarikh_Daftar", "");
			    	context.put("flag_Dokumen","");
			    	context.put("jenisDokumen","dokumenMsk");
			    	context.put("checkedc1","");
			    	context.put("checkedc2","");
			    	context.put("checkedc3","");
			    	
			    	listStatusLogDokumen = FrmLogDokumenData.getListStatusLogDokumen(idLogDokumen);
		    		this.context.put("SenaraiStatusLogDokumen",listStatusLogDokumen);
			    	
			    	listPegawaiTinggi(session);
			    	listPegawaiTinggi = FrmLogDokumenData.getListPegawai();
		    		this.context.put("SenaraiPegawaiTinggi",listPegawaiTinggi);
			    	
		    		listPegawaiTinggi(session);
			    	listPegawaiTinggi = FrmLogDokumenData.getListPegawai();
		    		this.context.put("SenaraiPegawaiTinggi",listPegawaiTinggi);
		    		
			    	listPA(session);
			    	listPA = FrmKemaskiniDokumenData.getListPA();
		    		this.context.put("SenaraiPA",listPA);
		    		
		    		listLampiranLogDokumen(session);
      				listLampiranLogDokumen = FrmLogDokumenData.getListLampiranLogDokumen();
            		this.context.put("SenaraiListLampiranLogDokumen",listLampiranLogDokumen);
            		
			    	
					listDokumen(session);
					listDokumen = FrmKemaskiniDokumenData.getListDokumen();
					this.context.put("SenaraiDokumen",listDokumen); 
					
				}
			}

			else if ("tabDokumenKeluar".equals(action1)){
				vm = "app/pfd/frmKemaskiniDokumenKeluar.jsp";
				context.put("mode","New");
				context.put("readonly","");
				context.put("disabled","");
				context.put("readonlyBil","");
				context.put("disabledBil","");
				context.put("readonlyNo","readonly");
				context.put("disabledNo","disabled");
				
				String idFail = getParam("idFail");
		    	
		    	view_CountDokumen(session);
		    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
				Hashtable hc = (Hashtable) paparCountDokumen.get(0);
				
				String a = hc.get("jumlah_Dokumen").toString();
				if (Integer.parseInt(hc.get("jumlah_Dokumen").toString()) >= 100){
					context.put("mode","Disable");
				}
				else
				{
					context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
				}
				
				view_DokumenFail(session);
		    	paparDokumenFail = FrmKemaskiniDokumenData.getDataDokumenFail(idFail);
				Hashtable ha = (Hashtable) paparDokumenFail.get(0);
				Integer ab = Integer.parseInt(ha.get("bil_Minit_Dokumen").toString());
				ab = ab + 1; 
				context.put("bil_Minit_Dokumen",Integer.parseInt(ha.get("bil_Minit_Dokumen").toString()) + 1 );
				
				view_NoDokumen(session);
				paparNoDokumen = FrmKemaskiniDokumenData.getDataNoDokumen(idFail);
				Hashtable hb = (Hashtable) paparNoDokumen.get(0);
				context.put("no_Rujukan_Dokumen",hb.get("no_Rujukan_Dokumen"));
				
				
				view_fail(session);
				paparFail = FrmKemaskiniDokumenData.getDataFail();
		
				Hashtable h = (Hashtable) paparFail.get(0);
				context.put("status",h.get("keterangan"));
				context.put("idFail",h.get("idFail"));
		    	context.put("noFail",h.get("noFail"));
		    	context.put("tajukFail",h.get("tajukFail"));
		    	context.put("keterangan",h.get("keterangan"));
		    	context.put("id_Status",h.get("id_Status"));
		    	context.put("tarikh_Daftar_Fail",h.get("tarikh_Daftar_Fail"));
		    	
		    	
				context.put("checkedMinit","");
	 			context.put("checkedLaporan","");
				context.put("id_Dokumen","");
		    	context.put("jenis_Dokumen","");
		    	context.put("no_Rujukan_Lain","");
		    	context.put("tarikh_Hantar_Dokumen", "");
		    	context.put("tajuk_Dokumen","");
		    	context.put("penerima_Dokumen","");
		    	context.put("tarikh_Daftar",sdf.format(now));
		    	context.put("flag_Dokumen","");
		    	context.put("jenisDokumen","dokumenMsk");
		    	context.put("tag_Dokumen","");
		    	context.put("checkedc1","");
		    	context.put("checkedc2","");
		    	context.put("checkedc3","");
		    	context.put("selectPTFail","");
		    	
				listDokumen(session);
				listDokumen = FrmKemaskiniDokumenData.getListDokumen();
				this.context.put("SenaraiDokumen",listDokumen); 
				
				listPegawai(session);
	    		listPegawai = FrmKemaskiniDokumenData.getListPegawai();
	    		this.context.put("SenaraiPegawai",listPegawai);
	    		
 	    		listPTFail(session);
	    		listPTFail = FrmKemaskiniDokumenData.getListPTFail();
	    		this.context.put("SenaraiPTFail",listPTFail);
	    		
			}
			
			else if ("tabDokumenSubjaket".equals(action1)){
				vm = "app/pfd/frmKemaskiniDokumenSubjaket.jsp";
				
				String modeSubjaket = getParam("modeSubjaket");
				
				if(modeSubjaket.equals("1")){
					if (!"".equals(getParam("no_Fail")))
		  	    		noFail = getParam("no_Fail");
					
			    	FrmDaftarDokumenData.setCarianSubjaket(noFail);
			    	listSubjaket = FrmDaftarDokumenData.getListSubjaket();
			    	this.context.put("SenaraiSubjaket", listSubjaket);
					this.context.put("id_FailSubjaket", id_Fail);
					this.context.put("no_Fail", no_Fail);
				}
				else{
					context.put("viewSimpanSubjaket","3");
					context.put("action1","tabDokumenSubjaket");
					context.put("mode","New");
					context.put("readOnly","");
					context.put("disabled","");
					context.put("readonlyBil","");
					context.put("disabledBil","");
					context.put("readonlyNo","readonly");
					context.put("disabledNo","");
					String idFail = getParam("idFail");
					String idFailSubjaket = getParam("idFailSubjaket");
					String noFailSubjaket = getParam("noFail");
					this.context.put("no_Fail", noFailSubjaket);
			    	
			    	view_CountDokumen(session);
			    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
					Hashtable hc = (Hashtable) paparCountDokumen.get(0);
					
					String a = hc.get("jumlah_Dokumen").toString();
					if (Integer.parseInt(hc.get("jumlah_Dokumen").toString()) >= 100){
						context.put("mode","Disable");
					}
					else
					{
						context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
					}
					
					
					view_fail(session);
					paparFail = FrmKemaskiniDokumenData.getDataFail();
			
					Hashtable h = (Hashtable) paparFail.get(0);
					context.put("status",h.get("keterangan"));
					context.put("idFail",h.get("idFail"));
			    	context.put("noFail",h.get("noFail"));
			    	context.put("tajukFail",h.get("tajukFail"));
			    	context.put("keterangan",h.get("keterangan"));
			    	context.put("id_Status",h.get("id_Status"));
			    	context.put("tarikh_Daftar_Fail",h.get("tarikh_Daftar_Fail"));
			    	
			    	view_DokumenFail(session);
			    	paparDokumenFail = FrmKemaskiniDokumenData.getDataDokumenFail(idFail);
					Hashtable ha = (Hashtable) paparDokumenFail.get(0);
					context.put("bil_Minit_Dokumen",Integer.parseInt(ha.get("bil_Minit_Dokumen").toString()) + 1 );

					view_NoDokumen(session);
					paparNoDokumen = FrmKemaskiniDokumenData.getDataNoDokumen(idFail);
					Hashtable hb = (Hashtable) paparNoDokumen.get(0);
					context.put("no_Rujukan_Dokumen",hb.get("no_Rujukan_Dokumen"));
					
					context.put("idFailSubjaket",idFailSubjaket);
					context.put("checkedMinit","");
		 			context.put("checkedLaporan","");
					context.put("id_Dokumen","");
			    	context.put("jenis_Dokumen","");
			    	context.put("no_Rujukan_Lain","");
			    	context.put("tarikh_Hantar_Dokumen", "");
			    	context.put("tajuk_Dokumen","");
			    	context.put("penerima_Dokumen","");
			    	context.put("tarikh_Daftar", "");
			    	context.put("flag_Dokumen","");
			    	context.put("jenisDokumen","dokumenMsk");
			    	context.put("tag_Dokumen","");
			    	context.put("checkedc1","");
			    	context.put("checkedc2","");
			    	context.put("checkedc3","");
			    	context.put("selectPTFail","");
			    	
	       	    	listDokumen(session);
					listDokumen = FrmKemaskiniDokumenData.getListDokumen();
					context.put("SenaraiDokumen",listDokumen);
					
				}
			}
			else if ("tabDokumenDalaman".equals(action1)){
				vm = "app/pfd/frmKemaskiniDokumenDalaman.jsp";
				context.put("mode","New");
				context.put("readonly","");
				context.put("disabled","");
				context.put("readonlyBil","");
				context.put("disabledBil","");
				context.put("readonlyNo","readonly");
				context.put("disabledNo","disabled");
				
				String idFail = getParam("idFail");
		    	
		    	view_CountDokumen(session);
		    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
				Hashtable hc = (Hashtable) paparCountDokumen.get(0);
				
				String a = hc.get("jumlah_Dokumen").toString();
				if (Integer.parseInt(hc.get("jumlah_Dokumen").toString()) >= 100){
					context.put("mode","Disable");
				}
				else
				{
					context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
				}
				
				view_DokumenFail(session);
		    	paparDokumenFail = FrmKemaskiniDokumenData.getDataDokumenFail(idFail);
				Hashtable ha = (Hashtable) paparDokumenFail.get(0);
				Integer ab = Integer.parseInt(ha.get("bil_Minit_Dokumen").toString());
				 ab =	ab + 1; 
				context.put("bil_Minit_Dokumen",Integer.parseInt(ha.get("bil_Minit_Dokumen").toString()) + 1 );
				
				view_NoDokumen(session);
				paparNoDokumen = FrmKemaskiniDokumenData.getDataNoDokumen(idFail);
				Hashtable hb = (Hashtable) paparNoDokumen.get(0);
				context.put("no_Rujukan_Dokumen",hb.get("no_Rujukan_Dokumen"));
				
				
				view_fail(session);
				paparFail = FrmKemaskiniDokumenData.getDataFail();
		
				Hashtable h = (Hashtable) paparFail.get(0);
				context.put("status",h.get("keterangan"));
				context.put("idFail",h.get("idFail"));
		    	context.put("noFail",h.get("noFail"));
		    	context.put("tajukFail",h.get("tajukFail"));
		    	context.put("keterangan",h.get("keterangan"));
		    	context.put("id_Status",h.get("id_Status"));
		    	context.put("tarikh_Daftar_Fail",h.get("tarikh_Daftar_Fail"));
		    	
				context.put("no_Rujukan_Dokumen",hb.get("no_Rujukan_Dokumen"));
		    	
				context.put("checkedMinit","");
	 			context.put("checkedLaporan","");
				context.put("id_Dokumen","");
		    	context.put("jenis_Dokumen","");
		    	context.put("no_Rujukan_Lain","");
		    	context.put("tarikh_Hantar_Dokumen", "");
		    	context.put("tajuk_Dokumen","");
		    	context.put("penerima_Dokumen","");
		    	context.put("tarikh_Daftar",sdf.format(now));
		    	context.put("flag_Dokumen","");
		    	context.put("jenisDokumen","dokumenMsk");
		    	context.put("tag_Dokumen","");
		    	context.put("checkedc1","");
		    	context.put("checkedc2","");
		    	context.put("checkedc3","");
		    	context.put("selectPTFail","");
		    	
				listDokumen(session);
				listDokumen = FrmKemaskiniDokumenData.getListDokumen();
				this.context.put("SenaraiDokumen",listDokumen); 
				
				listPegawai(session);
	    		listPegawai = FrmKemaskiniDokumenData.getListPegawai();
	    		this.context.put("SenaraiPegawai",listPegawai);
	    		
 	    		listPTFail2(session);
	    		listPTFail2 = FrmKemaskiniDokumenData.getListPTFail2();
	    		this.context.put("SenaraiPTFail",listPTFail2);
	    		
	    		setupPage1(session,action1,listDokumen);
			}
			
			else if ("simpanDokumenMasuk".equals(action1)){
				
				vm = "app/pfd/frmKemaskiniDokumenMasuk.jsp";
				context.put("mode","View");
				context.put("readonly","readonly");
				context.put("disabled","disabled");
				context.put("readonlyBil","readonly");
				context.put("disabledBil","disabled");
				context.put("readonlyNo","readonly");
				context.put("disabledNo","disabled");
				
				String idFail = "";
				if(!"".equals(getParam("idFail"))){
					idFail = getParam("idFail");
				}
				else{
					idFail = getParam("id_Fail");
				}
				
				
				String jum = getParam("txtJumDok");
				
				view_CountDokumen(session);
		    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
				Hashtable hc = (Hashtable) paparCountDokumen.get(0);
				context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
				
				view_fail(session);
				paparFail = FrmKemaskiniDokumenData.getDataFail();
				
				if(paparFail.isEmpty()){
					context.put("status","");
					context.put("idFail","");
			    	context.put("noFail","");
			    	context.put("tajukFail","");
			    	context.put("keterangan","");
			    	context.put("id_Status","");
			    	context.put("tarikh_Daftar_Fail","");
					
					
				}
				else{
		
				Hashtable ha = (Hashtable) paparFail.get(0);
				context.put("status",ha.get("keterangan"));
				context.put("idFail",ha.get("idFail"));
		    	context.put("noFail",ha.get("noFail"));
		    	context.put("tajukFail",ha.get("tajukFail"));
		    	context.put("keterangan",ha.get("keterangan"));
		    	context.put("id_Status",ha.get("id_Status"));
		    	context.put("tarikh_Daftar_Fail",ha.get("tarikh_Daftar_Fail"));
				
				}
		    	
		    	String idLogDokumen = getParam("idLogDokumen");
		    	String idDokumen = "";
        		
		    	Vector paparIdDokumen = FrmKemaskiniDokumenData.getIdDokumen(idLogDokumen);
		    	if(paparIdDokumen.size()!=0){
		    		Hashtable hA = (Hashtable)paparIdDokumen.get(0);
		    		idDokumen = (String)hA.get("idDokumen");
		    		context.put("idDokumen" ,idDokumen);
		    		updateDokumenMasuk(session);
		    		
		    	}
		    	else{
		    		idDokumen = simpanDokumenMasuk(session);
		    	}
		    	
				view_dokumen(session);
				paparDokumen = FrmKemaskiniDokumenData.getDataDokumen(idDokumen);
				Hashtable h = (Hashtable)paparDokumen.get(0);
					
				if(h.get("jenis_Dokumen").equals("1")){
						
					context.put("jenis_Dokumen","SURAT");

				}
				if (h.get("jenis_Dokumen").equals("2")){
					context.put("jenis_Dokumen","MEMO");
				}
				if(h.get("jenis_Dokumen").equals("3")){
					context.put("jenis_Dokumen","LAPORAN");

				}
				if(h.get("jenis_Dokumen").equals("4")){
					context.put("jenis_Dokumen","MINIT MESYUARAT");

				}
				if(h.get("jenis_Dokumen").equals("5")){
					context.put("jenis_Dokumen","FAIL SUBJAKET");

				}
				
				context.put("jenis_Dokumen",h.get("jenis_Dokumen"));
				context.put("idDokumen",h.get("id_Dokumen"));
			    context.put("no_Rujukan_Dokumen",h.get("no_Rujukan_Dokumen"));
			    context.put("no_Rujukan_Lain",h.get("no_Rujukan_Lain"));
			    context.put("bil_Minit_Dokumen",h.get("bil_Minit_Dokumen"));
			    context.put("id_tagdokumen",h.get("id_tagdokumen"));
			    context.put("tag_Dokumen",h.get("tag_Dokumen"));
			    context.put("tajuk_Dokumen",h.get("tajuk_Dokumen"));
			    context.put("pengirim_Dokumen",h.get("nama_Pengirim"));
			    if(h.get("idMinit").equals("1")){
				    context.put("checkedc1","checked");
			    }
			    else
			    {
			    	context.put("checkedc1","");
			    }
			    if(h.get("idLaporan").equals("1")){
			    	context.put("checkedc2","checked");
			    }
			    else
			    {
			    	context.put("checkedc2","");
			    }
			    if(h.get("idCD").equals("1")){
			    	context.put("checkedc3","checked");
			    }
			    else
			    {
			    	context.put("checkedc3","");
			    }
			    context.put("tarikh_Dokumen_Diterima",h.get("tarikh_Dokumen_Diterima"));

			    downloadDraf(idDokumen);
			    
			    
		    	listPegawaiTinggi = FrmKemaskiniDokumenData.getListPegawai(idDokumen);
	    		this.context.put("SenaraiPegawaiTinggi",listPegawaiTinggi);
	    		
		    	listPA = FrmKemaskiniDokumenData.getListPA(idDokumen);
	    		this.context.put("SenaraiPA",listPA);
	    		
	    		
	    		Vector listPAPegawai = FrmLogDokumenData.getDataPAPegawai(idDokumen);
	    		Hashtable x ;
	    		if (listPAPegawai.size() > 0) {
        			x = (Hashtable)listPAPegawai.elementAt(0);
         			this.context.put("selectidPA",x.get("user_id"));
    			 } else {
    				 this.context.put("selectidPA","0"); 
    			 }
	    		
			
       	    	paparLampiranDokumenMasuk = FrmKemaskiniDokumenData.getListLampiranDokumenMasuk(idDokumen);
        		this.context.put("SenaraiListLampiranDokumenMasuk",paparLampiranDokumenMasuk);
        		this.context.put("paparLampiranDokumenMasuk_size", paparLampiranDokumenMasuk.size());
 				
       	    	
       	    	listDokumen(session);
				listDokumen = FrmKemaskiniDokumenData.getListDokumen();
				context.put("SenaraiDokumen",listDokumen);
				
				String tajuk_Dokumen = String.valueOf(h.get("tajuk_Dokumen"));
	 			String tarikh_Dokumen_Diterima = String.valueOf(h.get("tarikh_Dokumen_Diterima"));
				String no_Rujukan_Dokumen = String.valueOf(h.get("no_Rujukan_Dokumen"));
				
			    String idPenerima1 = getIdPengarah(idDokumen);
			    String idPenerima2 = getIdPA(idDokumen);
			    			
		}
			
		else if ("simpanDokumenKeluar".equals(action1)){
			
			vm = "app/pfd/frmKemaskiniDokumenKeluar.jsp";
			context.put("mode","View");
			context.put("readonly","readonly");
			context.put("disabled","disabled");
			context.put("readonlyBil","readonly");
			context.put("disabledBil","disabled");
			context.put("readonlyNo","readonly");
			context.put("disabledNo","disabled");
			
			String idFail = getParam("idFail");
			
			view_CountDokumen(session);
	    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
			Hashtable hc = (Hashtable) paparCountDokumen.get(0);
			context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
	
			Hashtable ha = (Hashtable) paparFail.get(0);
			context.put("status",ha.get("keterangan"));
			context.put("idFail",ha.get("idFail"));
	    	context.put("noFail",ha.get("noFail"));
	    	context.put("tajukFail",ha.get("tajukFail"));
	    	context.put("keterangan",ha.get("keterangan"));
	    	context.put("id_Status",ha.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",ha.get("tarikh_Daftar_Fail"));
			
			String idDokumen = simpanDokumenKeluar(session);
		
			
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen(idDokumen);
			Hashtable h = (Hashtable)paparDokumen.get(0);
				
			if(h.get("jenis_Dokumen").equals("1")){
					
				context.put("jenis_Dokumen","SURAT");

			}
			if (h.get("jenis_Dokumen").equals("2")){
				context.put("jenis_Dokumen","MEMO");
			}
			if(h.get("jenis_Dokumen").equals("3")){
				context.put("jenis_Dokumen","LAPORAN");

			}
			if(h.get("jenis_Dokumen").equals("4")){
				context.put("jenis_Dokumen","MINIT MESYUARAT");

			}
			if(h.get("jenis_Dokumen").equals("5")){
				context.put("jenis_Dokumen","FAIL SUBJAKET");

			}
			context.put("jenis_Dokumen",h.get("jenis_Dokumen"));
			context.put("id_Dokumen",h.get("id_Dokumen"));
		    context.put("no_Rujukan_Dokumen",h.get("no_Rujukan_Dokumen"));
		    context.put("bil_Minit_Dokumen",h.get("bil_Minit_Dokumen"));
		    context.put("no_Rujukan_Dokumen",h.get("no_Rujukan_Dokumen"));
		    context.put("no_Rujukan_Lain",h.get("no_Rujukan_Lain"));
		    context.put("id_tagdokumen",h.get("id_tagdokumen"));
		    context.put("tag_Dokumen",h.get("tag_Dokumen"));
		    context.put("tajuk_Dokumen",h.get("tajuk_Dokumen"));
		    context.put("penerima_Dokumen",h.get("nama_Penerima"));
		    context.put("tarikh_Dokumen",h.get("tarikh_Dokumen"));
		    if(h.get("idMinit").equals("1")){
			    context.put("checkedc1","checked");
		    }
		    else
		    {
		    	context.put("checkedc1","");
		    }
		    if(h.get("idLaporan").equals("1")){
		    	context.put("checkedc2","checked");
		    }
		    else
		    {
		    	context.put("checkedc2","");
		    }
		    if(h.get("idCD").equals("1")){
		    	context.put("checkedc3","checked");
		    }
		    else
		    {
		    	context.put("checkedc3","");
		    }
			
		    downloadDraf(idDokumen);
			
		    
			Vector getlampiran = FrmPaparLampiranData.getLampiran(idDokumen);
     	    this.context.put("getLampiran_size", getlampiran.size());
				
     	    paparLampiranDokumenKeluar = FrmKemaskiniDokumenData.getListLampiranDokumenKeluar(idDokumen);
      		this.context.put("SenaraiListLampiranDokumenKeluar",paparLampiranDokumenKeluar);
      		
      		listPegawai = FrmKemaskiniDokumenData.getListPegawaiKeluar(idDokumen);
    		this.context.put("SenaraiPegawai",listPegawai);
      		 		
    		Vector listPAR = FrmKemaskiniDokumenData.getDataPARKeluar(idDokumen);
    		Hashtable x = (Hashtable)listPAR.elementAt(0);
 			this.context.put("selectPTFail",x.get("user_id"));
    		
			listDokumen(session);
			listDokumen = FrmKemaskiniDokumenData.getListDokumen();
			context.put("SenaraiDokumen",listDokumen);
				
		
		}
			
		else if ("simpanDokumenSubjaket".equals(action1)){
			
			vm = "app/pfd/frmKemaskiniDokumenSubjaket.jsp";
			context.put("mode","View");
			context.put("readOnly","readonly");
			context.put("disabled","disabled");
			
			String idFail = getParam("idFail");
			String jum = getParam("txtJumDok");
			
			view_CountDokumen(session);
	    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
			Hashtable hc = (Hashtable) paparCountDokumen.get(0);
			context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
	
			Hashtable ha = (Hashtable) paparFail.get(0);
			context.put("status",ha.get("keterangan"));
			context.put("idFail",ha.get("idFail"));
	    	context.put("noFail",ha.get("noFail"));
	    	context.put("tajukFail",ha.get("tajukFail"));
	    	context.put("keterangan",ha.get("keterangan"));
	    	context.put("id_Status",ha.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",ha.get("tarikh_Daftar_Fail"));
			
	    	String idLogDokumen = getParam("idLogDokumen");
			String idDokumen = simpanDokumenSubjaket(session);
			
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen(idDokumen);
			Hashtable h = (Hashtable)paparDokumen.get(0);
				
			if(h.get("jenis_Dokumen").equals("1")){
					
				context.put("jenis_Dokumen","SURAT");

			}
			if (h.get("jenis_Dokumen").equals("2")){
				context.put("jenis_Dokumen","MEMO");
			}
			if(h.get("jenis_Dokumen").equals("3")){
				context.put("jenis_Dokumen","LAPORAN");

			}
			if(h.get("jenis_Dokumen").equals("4")){
				context.put("jenis_Dokumen","MINIT MESYUARAT");

			}
			if(h.get("jenis_Dokumen").equals("5")){
				context.put("jenis_Dokumen","FAIL SUBJAKET");

			}
			
			context.put("jenis_Dokumen",h.get("jenis_Dokumen"));
			context.put("idDokumen",h.get("id_Dokumen"));
		    context.put("no_Rujukan_Dokumen",h.get("no_Rujukan_Dokumen"));
		    context.put("no_Rujukan_Lain",h.get("no_Rujukan_Lain"));
		    context.put("bil_Minit_Dokumen",h.get("bil_Minit_Dokumen"));
		    context.put("tajuk_Dokumen",h.get("tajuk_Dokumen"));
		    context.put("pengirim_Dokumen",h.get("nama_Pengirim"));
		    if(h.get("idMinit").equals("1")){
			    context.put("checkedc1","checked");
		    }
		    else
		    {
		    	context.put("checkedc1","");
		    }
		    if(h.get("idLaporan").equals("1")){
		    	context.put("checkedc2","checked");
		    }
		    else
		    {
		    	context.put("checkedc2","");
		    }
		    if(h.get("idCD").equals("1")){
		    	context.put("checkedc3","checked");
		    }
		    else
		    {
		    	context.put("checkedc3","");
		    }

		    Vector getlampiran = FrmPaparLampiranData.getLampiran(idDokumen);
   	    	this.context.put("getLampiran_size", getlampiran.size());
			
   	    	listDokumen(session);
			listDokumen = FrmKemaskiniDokumenData.getListDokumen();
			context.put("SenaraiDokumen",listDokumen);
					
	}
		else if ("simpanDokumenDalaman".equals(action1)){
			
			vm = "app/pfd/frmKemaskiniDokumenDalaman.jsp";
			context.put("mode","View");
			context.put("readonly","readonly");
			context.put("disabled","disabled");
			context.put("readonlyBil","readonly");
			context.put("disabledBil","disabled");
			context.put("readonlyNo","readonly");
			context.put("disabledNo","disabled");
			
			String idFail = getParam("idFail");
			
			view_CountDokumen(session);
	    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
			Hashtable hc = (Hashtable) paparCountDokumen.get(0);
			context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
	
			Hashtable ha = (Hashtable) paparFail.get(0);
			context.put("status",ha.get("keterangan"));
			context.put("idFail",ha.get("idFail"));
	    	context.put("noFail",ha.get("noFail"));
	    	context.put("tajukFail",ha.get("tajukFail"));
	    	context.put("keterangan",ha.get("keterangan"));
	    	context.put("id_Status",ha.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",ha.get("tarikh_Daftar_Fail"));
			
			String idDokumen = simpanDokumenDalaman(session);
		
			
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen(idDokumen);
			Hashtable h = (Hashtable)paparDokumen.get(0);
				
			if(h.get("jenis_Dokumen").equals("1")){
					
				context.put("jenis_Dokumen","SURAT");

			}
			if (h.get("jenis_Dokumen").equals("2")){
				context.put("jenis_Dokumen","MEMO");
			}
			if(h.get("jenis_Dokumen").equals("3")){
				context.put("jenis_Dokumen","LAPORAN");

			}
			if(h.get("jenis_Dokumen").equals("4")){
				context.put("jenis_Dokumen","MINIT MESYUARAT");

			}
			if(h.get("jenis_Dokumen").equals("5")){
				context.put("jenis_Dokumen","FAIL SUBJAKET");

			}
			context.put("jenis_Dokumen",h.get("jenis_Dokumen"));
			context.put("id_Dokumen",h.get("id_Dokumen"));
		    context.put("no_Rujukan_Dokumen",h.get("no_Rujukan_Dokumen"));
		    context.put("bil_Minit_Dokumen",h.get("bil_Minit_Dokumen"));
		    context.put("no_Rujukan_Dokumen",h.get("no_Rujukan_Dokumen"));
		    context.put("no_Rujukan_Lain",h.get("no_Rujukan_Lain"));
		    context.put("id_tagdokumen",h.get("id_tagdokumen"));
		    context.put("tag_Dokumen",h.get("tag_Dokumen"));
		    context.put("tajuk_Dokumen",h.get("tajuk_Dokumen"));
		    context.put("penerima_Dokumen",h.get("nama_Penerima"));
		    context.put("tarikh_Dokumen",h.get("tarikh_Dokumen"));
		    if(h.get("idMinit").equals("1")){
			    context.put("checkedc1","checked");
		    }
		    else
		    {
		    	context.put("checkedc1","");
		    }
		    if(h.get("idLaporan").equals("1")){
		    	context.put("checkedc2","checked");
		    }
		    else
		    {
		    	context.put("checkedc2","");
		    }
		    if(h.get("idCD").equals("1")){
		    	context.put("checkedc3","checked");
		    }
		    else
		    {
		    	context.put("checkedc3","");
		    }
			
		    downloadDraf(idDokumen);
			
		    
			Vector getlampiran = FrmPaparLampiranData.getLampiran(idDokumen);
     	    this.context.put("getLampiran_size", getlampiran.size());
				
     	    paparLampiranDokumenKeluar = FrmKemaskiniDokumenData.getListLampiranDokumenKeluar(idDokumen);
      		this.context.put("SenaraiListLampiranDokumenKeluar",paparLampiranDokumenKeluar);
      		
      		listPegawai = FrmKemaskiniDokumenData.getListPegawaiKeluar(idDokumen);
    		this.context.put("SenaraiPegawai",listPegawai);
  		
    		Vector listPAR = FrmKemaskiniDokumenData.getDataPARKeluar(idDokumen);
    		Hashtable x = (Hashtable)listPAR.elementAt(0);
 			this.context.put("selectPTFail",x.get("user_id"));
    		
			listDokumen(session);
			listDokumen = FrmKemaskiniDokumenData.getListDokumen();
			context.put("SenaraiDokumen",listDokumen);
						
		
		}	
		else if ("paparDokumenFailSubjaket".equals(action1)){
			vm = "app/pfd/frmKemaskiniDokumenViewFailSubjaket.jsp";
			context.put("mode","View");
	    	String idFail = getParam("idFailSubjaket");
	    	
	    	listDokumenFailSubjaket(session);
			listDokumenFailSubjaket = FrmKemaskiniDokumenData.getListDokumenFailSubjaket();
			context.put("SenaraiDokumen",listDokumenFailSubjaket);
	    	
			
		}	

			
		else if ("kemaskiniDokumenMasuk".equals(action1)){
			
			vm = "app/pfd/frmKemaskiniDokumenMasuk.jsp";
			
			context.put("mode","Update");
			context.put("readonly","");
			context.put("disabled","");
			context.put("readonlyBil","");
			context.put("disabledBil","");
			context.put("readonlyNo","");
			context.put("disabledNo","");
			
			String idFail = getParam("idFail");
			
			view_CountDokumen(session);
	    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
			Hashtable hc = (Hashtable) paparCountDokumen.get(0);
			context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
	
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
  	    	
			String idDokumen = getParam("idDokumen");
			
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen(idDokumen);
			Hashtable h = (Hashtable)paparDokumen.get(0);
				
			if(h.get("jenis_Dokumen").equals("1")){
					
				context.put("jenis_Dokumen","SURAT");

			}
			if (h.get("jenis_Dokumen").equals("2")){
				context.put("jenis_Dokumen","MEMO");
			}
			if(h.get("jenis_Dokumen").equals("3")){
				context.put("jenis_Dokumen","LAPORAN");

			}
			if(h.get("jenis_Dokumen").equals("4")){
				context.put("jenis_Dokumen","MINIT MESYUARAT");

			}
			if(h.get("jenis_Dokumen").equals("5")){
				context.put("jenis_Dokumen","FAIL SUBJAKET");

			}
			context.put("jenis_Dokumen",h.get("jenis_Dokumen"));
			context.put("id_Dokumen",h.get("id_Dokumen"));
		    context.put("no_Rujukan_Dokumen",h.get("no_Rujukan_Dokumen"));
		    context.put("bil_Minit_Dokumen",h.get("bil_Minit_Dokumen"));
		    context.put("no_Rujukan_Dokumen",h.get("no_Rujukan_Dokumen"));
		    context.put("no_Rujukan_Lain",h.get("no_Rujukan_Lain"));
		    context.put("tajuk_Dokumen",h.get("tajuk_Dokumen"));
		    context.put("pengirim_Dokumen",h.get("nama_Pengirim"));
		    context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB",Long.parseLong(h.get("id_nama_Penerima").toString()),""));					
		    context.put("tarikh_Dokumen_Masuk",h.get("tarikh_Dokumen_Masuk"));
		    context.put("tarikh_Dokumen_Diterima",h.get("tarikh_Dokumen_Diterima"));
		    context.put("tag_Dokumen",h.get("tag_Dokumen"));
		    context.put("id_tagdokumen",h.get("id_tagdokumen"));
		    
		    if(h.get("idMinit").equals("1")){
			    context.put("checkedc1","checked");
		    }
		    else
		    {
		    	context.put("checkedc1","");
		    }
		    if(h.get("idLaporan").equals("1")){
		    	context.put("checkedc2","checked");
		    }
		    else
		    {
		    	context.put("checkedc2","");
		    }
		    if(h.get("idCD").equals("1")){
		    	context.put("checkedc3","checked");
		    }
		    else
		    {
		    	context.put("checkedc3","");
		    }

		    listPegawaiTinggiDokumenMasuk(session);
	    	listPegawaiTinggi = FrmKemaskiniDokumenData.getListPegawaiTinggi();
    		this.context.put("SenaraiPegawaiTinggi",listPegawaiTinggi);
    		
    		Vector listPegawaiDokumenMasuk = FrmKemaskiniDokumenData.getDataPegawaiTinggi(idDokumen);
    		Hashtable x = (Hashtable)listPegawaiDokumenMasuk.elementAt(0);
 			this.context.put("selectidPegawaiTinggi",x.get("user_id"));
		    
 			listPA(session);
    		listPA = FrmKemaskiniDokumenData.getListPA();
    		this.context.put("SenaraiPA",listPA);
    		
    		
    		Vector listPAPegawai = FrmLogDokumenData.getDataPAPegawai(idDokumen);
    		Hashtable y ;
    		if (listPAPegawai.size() > 0) {
    			y = (Hashtable)listPAPegawai.elementAt(0);
     			this.context.put("selectidPA",y.get("user_id"));
			 } else {
				 this.context.put("selectidPA","0"); 
			 }
    		
    		paparLampiranDokumenMasuk = FrmKemaskiniDokumenData.getListLampiranDokumenMasuk(idDokumen);
    		this.context.put("SenaraiListLampiranDokumenMasuk",paparLampiranDokumenMasuk);
 			
			listDokumen(session);
			listDokumen = FrmKemaskiniDokumenData.getListDokumen();
			context.put("SenaraiDokumen",listDokumen);
			
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			context.put("SenaraiMinit",listMinit);
				
		}
			
		else if ("kemaskiniDokumenKeluar".equals(action1)){
			
			vm = "app/pfd/frmKemaskiniDokumenKeluar.jsp";
			
			context.put("mode","Update");
			context.put("readonly","");
			context.put("disabled","");
			context.put("readonlyBil","");
			context.put("disabledBil","");
			context.put("readonlyNo","");
			context.put("disabledNo","");
  	    	
			String idFail = getParam("idFail");
			
			view_CountDokumen(session);
	    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
			Hashtable hc = (Hashtable) paparCountDokumen.get(0);
			context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
			
			
			String idDokumen = getParam("idDokumen");
			
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen(idDokumen);
			Hashtable h = (Hashtable)paparDokumen.get(0);
				
			if(h.get("jenis_Dokumen").equals("1")){
					
				context.put("jenis_Dokumen","SURAT");

			}
			if (h.get("jenis_Dokumen").equals("2")){
				context.put("jenis_Dokumen","MEMO");
			}
			if(h.get("jenis_Dokumen").equals("3")){
				context.put("jenis_Dokumen","LAPORAN");

			}
			if(h.get("jenis_Dokumen").equals("4")){
				context.put("jenis_Dokumen","MINIT MESYUARAT");

			}
			if(h.get("jenis_Dokumen").equals("5")){
				context.put("jenis_Dokumen","FAIL SUBJAKET");

			}
			context.put("jenis_Dokumen",h.get("jenis_Dokumen"));
			context.put("id_Dokumen",h.get("id_Dokumen"));
		    context.put("no_Rujukan_Dokumen",h.get("no_Rujukan_Dokumen"));
		    context.put("bil_Minit_Dokumen",h.get("bil_Minit_Dokumen"));
		    context.put("no_Rujukan_Dokumen",h.get("no_Rujukan_Dokumen"));
		    context.put("no_Rujukan_Lain",h.get("no_Rujukan_Lain"));
		    context.put("tajuk_Dokumen",h.get("tajuk_Dokumen"));
		    context.put("penerima_Dokumen",h.get("nama_Penerima"));
		    context.put("tarikh_Dokumen",h.get("tarikh_Dokumen"));
		    context.put("tag_Dokumen",h.get("tag_Dokumen"));
		    context.put("id_tagdokumen",h.get("id_tagdokumen"));
		    if(h.get("idMinit").equals("1")){
			    context.put("checkedc1","checked");
		    }
		    else
		    {
		    	context.put("checkedc1","");
		    }
		    if(h.get("idLaporan").equals("1")){
		    	context.put("checkedc2","checked");
		    }
		    else
		    {
		    	context.put("checkedc2","");
		    }
		    if(h.get("idCD").equals("1")){
		    	context.put("checkedc3","checked");
		    }
		    else
		    {
		    	context.put("checkedc3","");
		    }
				
     	    paparLampiranDokumenKeluar = FrmKemaskiniDokumenData.getListLampiranDokumenKeluar(idDokumen);
      		this.context.put("SenaraiListLampiranDokumenKeluar",paparLampiranDokumenKeluar);
     		
      		listPegawai(session);
      		listPegawai = FrmKemaskiniDokumenData.getListPegawai();
      		this.context.put("SenaraiPegawai",listPegawai);
      		
    		Vector listPegawaiKeluar = FrmKemaskiniDokumenData.getDataPegawaiKeluar(idDokumen);
    		Hashtable x ;
    		if (listPegawai.size() > 0) {
    			x = (Hashtable)listPegawaiKeluar.elementAt(0);
     			this.context.put("selectidorang",x.get("user_id"));
			 } else {
				 this.context.put("selectidorang","0"); 
			 }
      		
   		
    		listPTFail(session);
			listPTFail = FrmKemaskiniDokumenData.getListPTFail();
    		this.context.put("SenaraiPTFail",listPTFail);
    		
    		Vector listPAR = FrmKemaskiniDokumenData.getDataPARKeluar(idDokumen);
    		
 			if(listPAR.size()>0)
    		{
 				Hashtable y = (Hashtable)listPAR.elementAt(0);
 	 			this.context.put("selectidPTFail",y.get("user_id"));
    		}
    		else
    		{
    		this.context.put("selectidPTFail","");	
    		}
    		
			listDokumen(session);
			listDokumen = FrmKemaskiniDokumenData.getListDokumen();
			context.put("SenaraiDokumen",listDokumen);

			
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			context.put("SenaraiMinit",listMinit);
			
	
		}
		else if ("kemaskiniDokumenDalaman".equals(action1)){
			
			vm = "app/pfd/frmKemaskiniDokumenDalaman.jsp";
			
			context.put("mode","Update");
			context.put("readonly","");
			context.put("disabled","");
			context.put("readonlyBil","");
			context.put("disabledBil","");
			context.put("readonlyNo","");
			context.put("disabledNo","");
  	    	
			String idFail = getParam("idFail");
			
			view_CountDokumen(session);
	    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
			Hashtable hc = (Hashtable) paparCountDokumen.get(0);
			context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
			
			
			String idDokumen = getParam("idDokumen");
			
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen(idDokumen);
			Hashtable h = (Hashtable)paparDokumen.get(0);
				
			if(h.get("jenis_Dokumen").equals("1")){
					
				context.put("jenis_Dokumen","SURAT");

			}
			if (h.get("jenis_Dokumen").equals("2")){
				context.put("jenis_Dokumen","MEMO");
			}
			if(h.get("jenis_Dokumen").equals("3")){
				context.put("jenis_Dokumen","LAPORAN");

			}
			if(h.get("jenis_Dokumen").equals("4")){
				context.put("jenis_Dokumen","MINIT MESYUARAT");

			}
			if(h.get("jenis_Dokumen").equals("5")){
				context.put("jenis_Dokumen","FAIL SUBJAKET");

			}
			context.put("jenis_Dokumen",h.get("jenis_Dokumen"));
			context.put("id_Dokumen",h.get("id_Dokumen"));
		    context.put("no_Rujukan_Dokumen",h.get("no_Rujukan_Dokumen"));
		    context.put("bil_Minit_Dokumen",h.get("bil_Minit_Dokumen"));
		    context.put("no_Rujukan_Dokumen",h.get("no_Rujukan_Dokumen"));
		    context.put("no_Rujukan_Lain",h.get("no_Rujukan_Lain"));
		    context.put("tajuk_Dokumen",h.get("tajuk_Dokumen"));
		    context.put("penerima_Dokumen",h.get("nama_Penerima"));
		    context.put("tarikh_Dokumen",h.get("tarikh_Dokumen"));
		    context.put("tag_Dokumen",h.get("tag_Dokumen"));
		    context.put("id_tagdokumen",h.get("id_tagdokumen"));
		    if(h.get("idMinit").equals("1")){
			    context.put("checkedc1","checked");
		    }
		    else
		    {
		    	context.put("checkedc1","");
		    }
		    if(h.get("idLaporan").equals("1")){
		    	context.put("checkedc2","checked");
		    }
		    else
		    {
		    	context.put("checkedc2","");
		    }
		    if(h.get("idCD").equals("1")){
		    	context.put("checkedc3","checked");
		    }
		    else
		    {
		    	context.put("checkedc3","");
		    }
				
     	    paparLampiranDokumenKeluar = FrmKemaskiniDokumenData.getListLampiranDokumenKeluar(idDokumen);
      		this.context.put("SenaraiListLampiranDokumenKeluar",paparLampiranDokumenKeluar);
     		
      		listPegawai(session);
      		listPegawai = FrmKemaskiniDokumenData.getListPegawai();
      		this.context.put("SenaraiPegawai",listPegawai);
      		
    		Vector listPegawaiKeluar = FrmKemaskiniDokumenData.getDataPegawaiKeluar(idDokumen);
    		Hashtable x ;
    		if (listPegawai.size() > 0) {
    			x = (Hashtable)listPegawaiKeluar.elementAt(0);
     			this.context.put("selectidorang",x.get("user_id"));
			 } else {
				 this.context.put("selectidorang","0"); 
			 }
      		     		
    		listPTFail(session);
			listPTFail = FrmKemaskiniDokumenData.getListPTFail();
    		this.context.put("SenaraiPTFail",listPTFail);
    		
    		Vector listPAR = FrmKemaskiniDokumenData.getDataPARKeluar(idDokumen);
    		
 			if(listPAR.size()>0)
    		{
 				Hashtable y = (Hashtable)listPAR.elementAt(0);
 	 			this.context.put("selectidPTFail",y.get("user_id"));
    		}
    		else
    		{
    		this.context.put("selectidPTFail","");	
    		}
    		
			listDokumen(session);
			listDokumen = FrmKemaskiniDokumenData.getListDokumen();
			context.put("SenaraiDokumen",listDokumen);

			
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			context.put("SenaraiMinit",listMinit);
				
		}	
		else if ("updateDokumenMasuk".equals(action1)){
			
			updateDokumenMasuk(session);
			vm = "app/pfd/frmKemaskiniDokumenMasuk.jsp";
			
			context.put("mode","PaparUpdate");
			context.put("readonly","readonly");
			context.put("disabled","disabled");
			context.put("readonlyBil","readonly");
			context.put("disabledBil","disabled");
			context.put("readonlyNo","readonly");
			context.put("disabledNo","disabled");
  	    	
			String idFail = getParam("idFail");
			
			view_CountDokumen(session);
	    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
			Hashtable hc = (Hashtable) paparCountDokumen.get(0);
			context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
			
	    	String idDokumen = getParam("idDokumen");
			
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen(idDokumen);
			Hashtable h = (Hashtable)paparDokumen.get(0);
				
			if(h.get("jenis_Dokumen").equals("1")){
					
				context.put("jenis_Dokumen","SURAT");

			}
			if (h.get("jenis_Dokumen").equals("2")){
				context.put("jenis_Dokumen","MEMO");
			}
			if(h.get("jenis_Dokumen").equals("3")){
				context.put("jenis_Dokumen","LAPORAN");

			}
			if(h.get("jenis_Dokumen").equals("4")){
				context.put("jenis_Dokumen","MINIT MESYUARAT");

			}
			if(h.get("jenis_Dokumen").equals("5")){
				context.put("jenis_Dokumen","FAIL SUBJAKET");

			}
			context.put("jenis_Dokumen",h.get("jenis_Dokumen"));
			context.put("id_Dokumen",h.get("id_Dokumen"));
		    context.put("no_Rujukan_Dokumen",h.get("no_Rujukan_Dokumen"));
		    context.put("bil_Minit_Dokumen",h.get("bil_Minit_Dokumen"));
		    context.put("no_Rujukan_Dokumen",h.get("no_Rujukan_Dokumen"));
		    context.put("no_Rujukan_Lain",h.get("no_Rujukan_Lain"));
		    context.put("tajuk_Dokumen",h.get("tajuk_Dokumen"));
		    context.put("pengirim_Dokumen",h.get("nama_Pengirim"));
		    context.put("penerima_Dokumen",h.get("nama_Penerima"));
		    context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB",Long.parseLong(h.get("id_nama_Penerima").toString()),"disabled"));					
		    context.put("tarikh_Dokumen_Keluar",h.get("tarikh_Dokumen_Keluar"));
		    context.put("tarikh_Dokumen_Diterima",h.get("tarikh_Dokumen_Diterima"));
		    context.put("tag_Dokumen",h.get("tag_Dokumen"));
		    context.put("id_tagdokumen",h.get("id_tagdokumen"));
		    if(h.get("idMinit").equals("1")){
			    context.put("checkedc1","checked");
		    }
		    else
		    {
		    	context.put("checkedc1","");
		    }
		    if(h.get("idLaporan").equals("1")){
		    	context.put("checkedc2","checked");
		    }
		    else
		    {
		    	context.put("checkedc2","");
		    }
		    if(h.get("idCD").equals("1")){
		    	context.put("checkedc3","checked");
		    }
		    else
		    {
		    	context.put("checkedc3","");
		    }
			
		    listPegawaiTinggi = FrmKemaskiniDokumenData.getListPegawai(idDokumen);
    		this.context.put("SenaraiPegawaiTinggi",listPegawaiTinggi);
    		
    		
    		Vector listPAPegawai = FrmLogDokumenData.getDataPAPegawai(idDokumen);
    		Hashtable x ;
    		if (listPAPegawai.size() > 0) {
    			x = (Hashtable)listPAPegawai.elementAt(0);
     			this.context.put("selectidPA",x.get("user_id"));
			 } else {
				 this.context.put("selectidPA","0"); 
			 }
		    
			listDokumen(session);
			listDokumen = FrmKemaskiniDokumenData.getListDokumen();
			context.put("SenaraiDokumen",listDokumen);
			
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			context.put("SenaraiMinit",listMinit);
		
		}
			
		else if ("updateDokumenKeluar".equals(action1)){
			
			updateDokumenKeluar(session);
			vm = "app/pfd/frmKemaskiniDokumenKeluar.jsp";
			
			context.put("mode","PaparUpdate");
			context.put("readonly","readonly");
			context.put("disabled","disabled");
			context.put("readonlyBil","readonly");
			context.put("disabledBil","disabled");
			context.put("readonlyNo","readonly");
			context.put("disabledNo","disabled");
  	    	
			String idFail = getParam("idFail");
			
			view_CountDokumen(session);
	    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
			Hashtable hc = (Hashtable) paparCountDokumen.get(0);
			context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
			
	    	String idDokumen = getParam("idDokumen");
	    	
	    	paparDokumen = FrmKemaskiniDokumenData.getDataDokumen(idDokumen);
			Hashtable h = (Hashtable)paparDokumen.get(0);
				
			if(h.get("jenis_Dokumen").equals("1")){
					
				context.put("jenis_Dokumen","SURAT");

			}
			if (h.get("jenis_Dokumen").equals("2")){
				context.put("jenis_Dokumen","MEMO");
			}
			if(h.get("jenis_Dokumen").equals("3")){
				context.put("jenis_Dokumen","LAPORAN");

			}
			if(h.get("jenis_Dokumen").equals("4")){
				context.put("jenis_Dokumen","MINIT MESYUARAT");

			}
			if(h.get("jenis_Dokumen").equals("5")){
				context.put("jenis_Dokumen","FAIL SUBJAKET");

			}
			context.put("jenis_Dokumen",h.get("jenis_Dokumen"));
			context.put("id_Dokumen",h.get("id_Dokumen"));
		    context.put("no_Rujukan_Dokumen",h.get("no_Rujukan_Dokumen"));
		    context.put("bil_Minit_Dokumen",h.get("bil_Minit_Dokumen"));
		    context.put("no_Rujukan_Dokumen",h.get("no_Rujukan_Dokumen"));
		    context.put("no_Rujukan_Lain",h.get("no_Rujukan_Lain"));
		    context.put("id_tagdokumen",h.get("id_tagdokumen"));
		    context.put("tag_Dokumen",h.get("tag_Dokumen"));
		    context.put("tajuk_Dokumen",h.get("tajuk_Dokumen"));
		    context.put("penerima_Dokumen",h.get("nama_Penerima"));
		    context.put("tarikh_Dokumen",h.get("tarikh_Dokumen"));
		    if(h.get("idMinit").equals("1")){
			    context.put("checkedc1","checked");
		    }
		    else
		    {
		    	context.put("checkedc1","");
		    }
		    if(h.get("idLaporan").equals("1")){
		    	context.put("checkedc2","checked");
		    }
		    else
		    {
		    	context.put("checkedc2","");
		    }
		    if(h.get("idCD").equals("1")){
		    	context.put("checkedc3","checked");
		    }
		    else
		    {
		    	context.put("checkedc3","");
		    }
			
			Vector getlampiran = FrmPaparLampiranData.getLampiran(idDokumen);
     	    this.context.put("getLampiran_size", getlampiran.size());
				
     	    paparLampiranDokumenKeluar = FrmKemaskiniDokumenData.getListLampiranDokumenKeluar(idDokumen);
      		this.context.put("SenaraiListLampiranDokumenKeluar",paparLampiranDokumenKeluar);
     		
      		listPegawai = FrmKemaskiniDokumenData.getListPegawaiKeluar(idDokumen);
    		this.context.put("SenaraiPegawai",listPegawai);
    		
    		Vector listPAR = FrmKemaskiniDokumenData.getDataPARKeluar(idDokumen);
    		Hashtable x = (Hashtable)listPAR.elementAt(0);
 			this.context.put("selectPTFail",x.get("user_id"));
    		
			listDokumen(session);
			listDokumen = FrmKemaskiniDokumenData.getListDokumen();
			context.put("SenaraiDokumen",listDokumen);
			
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			context.put("SenaraiMinit",listMinit);
					
		
		}
		
		else if ("updateDokumenDalaman".equals(action1)){
			
			updateDokumenDalaman(session);
			vm = "app/pfd/frmKemaskiniDokumenDalaman.jsp";
			
			context.put("mode","PaparUpdate");
			context.put("readonly","readonly");
			context.put("disabled","disabled");
			context.put("readonlyBil","readonly");
			context.put("disabledBil","disabled");
			context.put("readonlyNo","readonly");
			context.put("disabledNo","disabled");
  	    	
			String idFail = getParam("idFail");
			
			view_CountDokumen(session);
	    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
			Hashtable hc = (Hashtable) paparCountDokumen.get(0);
			context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
			
	    	String idDokumen = getParam("idDokumen");
	    	
	    	paparDokumen = FrmKemaskiniDokumenData.getDataDokumen(idDokumen);
			Hashtable h = (Hashtable)paparDokumen.get(0);
				
			if(h.get("jenis_Dokumen").equals("1")){
					
				context.put("jenis_Dokumen","SURAT");

			}
			if (h.get("jenis_Dokumen").equals("2")){
				context.put("jenis_Dokumen","MEMO");
			}
			if(h.get("jenis_Dokumen").equals("3")){
				context.put("jenis_Dokumen","LAPORAN");

			}
			if(h.get("jenis_Dokumen").equals("4")){
				context.put("jenis_Dokumen","MINIT MESYUARAT");

			}
			if(h.get("jenis_Dokumen").equals("5")){
				context.put("jenis_Dokumen","FAIL SUBJAKET");

			}
			context.put("jenis_Dokumen",h.get("jenis_Dokumen"));
			context.put("id_Dokumen",h.get("id_Dokumen"));
		    context.put("no_Rujukan_Dokumen",h.get("no_Rujukan_Dokumen"));
		    context.put("bil_Minit_Dokumen",h.get("bil_Minit_Dokumen"));
		    context.put("no_Rujukan_Dokumen",h.get("no_Rujukan_Dokumen"));
		    context.put("no_Rujukan_Lain",h.get("no_Rujukan_Lain"));
		    context.put("id_tagdokumen",h.get("id_tagdokumen"));
		    context.put("tag_Dokumen",h.get("tag_Dokumen"));
		    context.put("tajuk_Dokumen",h.get("tajuk_Dokumen"));
		    context.put("penerima_Dokumen",h.get("nama_Penerima"));
		    context.put("tarikh_Dokumen",h.get("tarikh_Dokumen"));
		    if(h.get("idMinit").equals("1")){
			    context.put("checkedc1","checked");
		    }
		    else
		    {
		    	context.put("checkedc1","");
		    }
		    if(h.get("idLaporan").equals("1")){
		    	context.put("checkedc2","checked");
		    }
		    else
		    {
		    	context.put("checkedc2","");
		    }
		    if(h.get("idCD").equals("1")){
		    	context.put("checkedc3","checked");
		    }
		    else
		    {
		    	context.put("checkedc3","");
		    }
			
			Vector getlampiran = FrmPaparLampiranData.getLampiran(idDokumen);
     	    this.context.put("getLampiran_size", getlampiran.size());
				
     	    paparLampiranDokumenKeluar = FrmKemaskiniDokumenData.getListLampiranDokumenKeluar(idDokumen);
      		this.context.put("SenaraiListLampiranDokumenKeluar",paparLampiranDokumenKeluar);
      		
      		listPegawai = FrmKemaskiniDokumenData.getListPegawaiKeluar(idDokumen);
    		this.context.put("SenaraiPegawai",listPegawai);
   		
    		Vector listPAR = FrmKemaskiniDokumenData.getDataPARKeluar(idDokumen);
    		Hashtable x = (Hashtable)listPAR.elementAt(0);
 			this.context.put("selectPTFail",x.get("user_id"));
    		
			listDokumen(session);
			listDokumen = FrmKemaskiniDokumenData.getListDokumen();
			context.put("SenaraiDokumen",listDokumen);
			
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			context.put("SenaraiMinit",listMinit);
					
		
		}
			
		else if ("papar".equals(action1)){
			
			String user_name = (String)session.getAttribute("_portal_username");
         	String user_id = (String)session.getAttribute("_ekptg_user_id");
         	String portal_role = (String)session.getAttribute("_portal_role");
		    String user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
			context.put("idNegeri",user_negeri);	
         	context.put("user_Name", user_name);
         	context.put("user_Id", user_id);
			
         	String idMinitArahan = getParam("idMinitArahan");
	    	String idDokumen = getParam("idDokumen");
	    	String paparArahan = getParam("paparArahan");
	    	context.put("idDokumen", idDokumen);
         	context.put("id_Minitarahan", idMinitArahan);
    
	    	
	    	if("true".equals(paparArahan))
	    	{
	    	
	    	view_LevelArahan(session);
	    	paparCountLevelArahan = FrmKemaskiniDokumenData.getDataCountLevelArahan(id);
			Hashtable hz = (Hashtable) paparCountLevelArahan.get(0);
			Integer ab = Integer.parseInt(hz.get("levelArahan").toString());
			ab =	ab + 1; 
			context.put("levelArahan",Integer.parseInt(hz.get("levelArahan").toString()) + 1 );
						
			String flag_dokumen = "";
	    	paparDokumen = FrmKemaskiniDokumenData.getDataDokumen(idDokumen);
	    	if(paparDokumen.size()!=0){
	    		Hashtable h = (Hashtable)paparDokumen.get(0);
	    		flag_dokumen = (String)h.get("flag_Dokumen");
	    	}
						
			if(flag_dokumen.equals("1")){
				
				vm = "app/pfd/frmKemaskiniDokumenMasuk.jsp";
				context.put("mode","View");
				context.put("readOnly","readonly");
				context.put("disabled","disabled");
				context.put("readOnlyBil","readonly");
				context.put("disabledBil","disabled");
				context.put("readOnlyNo","readonly");
				context.put("disabledNo","disabled");
				context.put("paparArahan","true");
	  	    	
				String idFail = getParam("idFail");
				
				view_CountDokumen(session);
				
				String jumlah_dokumen = "";
		    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
		    	if(paparCountDokumen.size()!=0){
		    		Hashtable hc = (Hashtable) paparCountDokumen.get(0);
		    		jumlah_dokumen = (String)hc.get("jumlah_Dokumen");
		    	}
				
				context.put("jumlah_Dokumen",jumlah_dokumen);
				
				view_fail(session);
				
				String keterangan = "";
				String idfail = "";
				String nofail = "";
				String tajukfail = "";
				String id_status = "";
				String tarikh_daftar_fail = "";
				
				paparFail = FrmKemaskiniDokumenData.getDataFail();
				if(paparFail.size()!=0){
					Hashtable hd = (Hashtable) paparFail.get(0);
					keterangan = (String)hd.get("keterangan");
					idfail = (String)hd.get("idFail");
					nofail = (String)hd.get("noFail");
					tajukfail = (String)hd.get("tajukFail");
					id_status = (String)hd.get("keterangan");
					tarikh_daftar_fail = (String)hd.get("tarikh_Daftar_Fail");
				}
				
				context.put("status",keterangan);
				context.put("idFail",idfail);
				context.put("noFail",nofail);
				context.put("tajukFail",tajukfail);
				context.put("keterangan",keterangan);
				context.put("id_Status",id_status);
				context.put("tarikh_Daftar_Fail",tarikh_daftar_fail);
				
				String statusTugasan = FrmKemaskiniDokumenData.getMinitArahan(idMinitArahan, idDokumen,(String)session.getAttribute("_ekptg_user_id"));
				
				if("1".equalsIgnoreCase(statusTugasan)){
					Vector getUser = FrmKemaskiniDokumenData.getUserSelesai(idMinitArahan, idDokumen,(String)session.getAttribute("_ekptg_user_id"));
		   	    	this.context.put("getUser_size", getUser.size());	

				}
				else
				{
					Vector getUser = FrmKemaskiniDokumenData.getUser(idDokumen,(String)session.getAttribute("_ekptg_user_id"));
		   	    	this.context.put("getUser_size", getUser.size());	
	
				}
				
				String statusTugasanSekLain = FrmKemaskiniDokumenData.getMinitArahanSekLain(idDokumen,(String)session.getAttribute("_ekptg_user_id"));

				if("88".equalsIgnoreCase(statusTugasanSekLain)){
					Vector getUser = FrmKemaskiniDokumenData.getUserSekLain(idDokumen,(String)session.getAttribute("_ekptg_user_id"));
		   	    	this.context.put("getUser_size", getUser.size());	
		   	    	context.put("levelArahan","2");
				}
				else
				{
	
				}
				
				String idDokumen2 = getParam("idDokumen");
				context.put("idDokumen",idDokumen2);
		    	
				String jenis_dokumen = "";
				String id_dokumen = "";
				String bil_minit_dokumen = "";
				String no_rujukan_dokumen = "";
				String no_rujukan_lain = "";
				String tajuk_dokumen = "";
				String pengirim_dokumen = "";
				String id_nama_penerima = "";
				String tarikh_dokumen = "";
				String tarikh_dokumen_diterima = "";
				String id_minit = "";
				String id_laporan = "";
				String id_cd = "";
				String tag_Dokumen = "";
				String id_tagdokumen = "";
				
		    	paparDokumen = FrmKemaskiniDokumenData.getDataDokumen2(idDokumen2);
		    	if(paparDokumen.size()!=0){
		    		Hashtable ha = (Hashtable)paparDokumen.get(0);
		    		jenis_dokumen = (String)ha.get("jenis_Dokumen");
					id_dokumen = (String)ha.get("id_Dokumen");
					bil_minit_dokumen = (String)ha.get("bil_Minit_Dokumen");
					no_rujukan_dokumen = (String)ha.get("no_Rujukan_Dokumen");
					no_rujukan_lain = (String)ha.get("no_Rujukan_Lain");
					tajuk_dokumen = (String)ha.get("tajuk_Dokumen");
					id_tagdokumen = (String)ha.get("id_tagdokumen");
					tag_Dokumen = (String)ha.get("tag_Dokumen");
					pengirim_dokumen = (String)ha.get("nama_Pengirim");
					id_nama_penerima = (String)ha.get("id_nama_Penerima");
					tarikh_dokumen = (String)ha.get("tarikh_Dokumen");
					tarikh_dokumen_diterima = (String)ha.get("tarikh_Dokumen_Diterima");
					id_minit = (String)ha.get("idMinit");
					id_laporan = (String)ha.get("idLaporan");
					id_cd = (String)ha.get("idCD");
		    	}
				
		    	
		    	if(jenis_dokumen.equals("1")){
					
					context.put("jenis_Dokumen","SURAT");

				}
				if (jenis_dokumen.equals("2")){
					context.put("jenis_Dokumen","MEMO");
				}
				if(jenis_dokumen.equals("3")){
					context.put("jenis_Dokumen","LAPORAN");

				}
				if(jenis_dokumen.equals("4")){
					context.put("jenis_Dokumen","MINIT MESYUARAT");

				}
				if(jenis_dokumen.equals("5")){
					context.put("jenis_Dokumen","FAIL SUBJAKET");

				}
				
				context.put("jenis_Dokumen",jenis_dokumen);
				context.put("id_Dokumen",id_dokumen);
			    context.put("bil_Minit_Dokumen",bil_minit_dokumen);
			    context.put("no_Rujukan_Dokumen",no_rujukan_dokumen);
			    context.put("no_Rujukan_Lain",no_rujukan_lain);
			    context.put("tajuk_Dokumen",tajuk_dokumen);
			    context.put("tag_Dokumen",tag_Dokumen);
			    context.put("id_tagdokumen",id_tagdokumen);
			    context.put("pengirim_Dokumen",pengirim_dokumen);
			    context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB",Long.parseLong(id_nama_penerima),"disabled"));					
			    context.put("tarikh_Dokumen",tarikh_dokumen);
			    context.put("tarikh_Dokumen_Diterima",tarikh_dokumen_diterima);
			    
			    
			    if(id_minit.equals("1")){
				    context.put("checkedc1","checked");
			    }
			    else
			    {
			    	context.put("checkedc1","");
			    }
			    if(id_laporan.equals("1")){
			    	context.put("checkedc2","checked");
			    }
			    else
			    {
			    	context.put("checkedc2","");
			    }
			    if(id_cd.equals("1")){
			    	context.put("checkedc3","checked");
			    }
			    else
			    {
			    	context.put("checkedc3","");
			    }
 				
			    
			    
			    listPegawaiTinggiDokumenMasuk(session);
		    	listPegawaiTinggi = FrmKemaskiniDokumenData.getListPegawai(idDokumen);
	    		this.context.put("SenaraiPegawaiTinggi",listPegawaiTinggi);
	    		
	    		String userid = "";
	    		Vector listPegawaiDokumenMasuk = FrmKemaskiniDokumenData.getDataPegawaiTinggi(idDokumen);
	    		if(listPegawaiDokumenMasuk.size()!=0){
	    			Hashtable x = (Hashtable)listPegawaiDokumenMasuk.elementAt(0);
	    			userid = (String)x.get("user_id");
	    		}
        		
     			this.context.put("selectPegawai",userid);
				
     			
     			listPA(session);
			    listPA = FrmKemaskiniDokumenData.getListPA(idDokumen);
	    		this.context.put("SenaraiPA",listPA);
     			
	    		Vector listPAPegawai = FrmLogDokumenData.getDataPAPegawai(idDokumen);
	    		Hashtable y ;
	    		if (listPAPegawai.size() > 0) {
        			y = (Hashtable)listPAPegawai.elementAt(0);
         			this.context.put("selectidPA",y.get("user_id"));
    			 } else {
    				 this.context.put("selectidPA","0"); 
    			 }
	    		
				paparLampiranDokumenMasuk = FrmKemaskiniDokumenData.getListLampiranDokumenMasuk(idDokumen2);
        		this.context.put("SenaraiListLampiranDokumenMasuk",paparLampiranDokumenMasuk);
        		this.context.put("paparLampiranDokumenMasuk_size", paparLampiranDokumenMasuk.size());
				
        		listMinitArahanPengarah(session);
        		
        		String pegawaiMengarah = "";
        		listMinitArahanPengarah = FrmKemaskiniDokumenData.getlistMinitArahanPengarah();
        		if(listMinitArahanPengarah.size()!=0){
        			Hashtable h1 = (Hashtable) listMinitArahanPengarah.get(0);
        			pegawaiMengarah = (String)h1.get("pegawaiMengarah");
        		}
        		
				if(pegawaiMengarah.equals(""))
				{
					context.put("modeLevel","0");
					context.put("hidden1","true");
				}
				else
				{
					context.put("modeLevel","1");
					context.put("hidden1", "false");
					context.put("hiddenSeksyenLain","true");
					context.put("action1", "papar");
					context.put("SenaraiMinitArahanPengarah",listMinitArahanPengarah);
				}
        		
        		listMinitArahanPegawai1(session);
        		
        		String pegawaiMengarah1 = "";
        		listMinitArahanPegawai1 = FrmKemaskiniDokumenData.getListMinitArahanPegawai1();
        		if(listMinitArahanPegawai1.size()!=0){
        			Hashtable h2 = (Hashtable) listMinitArahanPegawai1.get(0);
        			pegawaiMengarah1 = (String)h2.get("pegawaiMengarah");
        		}
        		
				if(pegawaiMengarah1.equals(""))
				{
					context.put("modeLevel","0");
					context.put("hidden2","true");
				}
				else
				{
					context.put("modeLevel","2");
					context.put("hidden2", "false");
					context.put("hiddenSeksyenLain","true");
					context.put("action1", "papar");
					context.put("SenaraiMinitArahanPegawai1",listMinitArahanPegawai1);
				}
				
        		listMinitArahanPegawai2(session);
        		
        		String pegawaiMengarah2 = "";
        		listMinitArahanPegawai2 = FrmKemaskiniDokumenData.getListMinitArahanPegawai2();
        		if(listMinitArahanPegawai2.size()!=0){
        			Hashtable h3 = (Hashtable) listMinitArahanPegawai2.get(0);
        			pegawaiMengarah = (String)h3.get("pegawaiMengarah");
        		}
        		
				if(pegawaiMengarah.equals(""))
				{
					context.put("modeLevel","0");
					context.put("hidden3","true");
				}
				else
				{
					context.put("modeLevel","3");
					context.put("hidden3", "false");
					context.put("hiddenSeksyenLain","true");
					context.put("action1", "papar");
					context.put("SenaraiMinitArahanPegawai2",listMinitArahanPegawai2);
				}
				
        		listMinitArahanPegawai3(session);
        		
        		String pegawaiMengarah3 = "";
        		listMinitArahanPegawai3 = FrmKemaskiniDokumenData.getListMinitArahanPegawai3();
        		if(listMinitArahanPegawai3.size()!=0){
        			Hashtable h4 = (Hashtable) listMinitArahanPegawai3.get(0);
        			pegawaiMengarah3 = (String)h4.get("pegawaiMengarah");
        		}
        		
				if(pegawaiMengarah3.equals(""))
				{
					context.put("modeLevel","0");
					context.put("hidden4","true");
				}
				else
				{
					context.put("modeLevel","4");
					context.put("hidden4", "false");
					context.put("hiddenSeksyenLain","true");
					context.put("action1", "papar");
					context.put("SenaraiMinitArahanPegawai3",listMinitArahanPegawai3);
				}
				
				listMinitArahanSelesai(session);
				String pegawaiMengarah5 = "";
				listMinitArahanSelesai = FrmKemaskiniDokumenData.getListMinitArahanSelesai();
				if(listMinitArahanSelesai.size()!=0){
					Hashtable h5 = (Hashtable) listMinitArahanSelesai.get(0);
					pegawaiMengarah5 = (String)h5.get("pegawaiMengarah");
				}
	       		
				if(pegawaiMengarah5.equals(""))
				{
					context.put("modeLevel","0");
					context.put("hiddenSelesai","true");
				}
				else
				{
					context.put("modeLevel","selesai");
					context.put("hiddenSelesai", "false");
					context.put("hiddenSeksyenLain","true");
					context.put("action1", "papar");
					context.put("SenaraiMinitArahanSelesai",listMinitArahanSelesai);
				}
							
				listMinitArahanSeksyenLain(session);
				
				String pegawaiMengarah6 = "";
				listMinitArahanSeksyenLain = FrmKemaskiniDokumenData.getListMinitArahanSeksyenLain();
				if(listMinitArahanSeksyenLain.size()!=0){
					Hashtable h6 = (Hashtable) listMinitArahanSeksyenLain.get(0);
					pegawaiMengarah6 = (String)h6.get("pegawaiMengarah");
				}
        		
				if(pegawaiMengarah6.equals(""))
				{
					context.put("modeLevel","0");
					context.put("hiddenSeksyenLain","true");
				}
				else
				{
					context.put("modeLevel","seksyenLain");
					context.put("hiddenSeksyenLain", "false");
					context.put("action1", "papar");
					context.put("SenaraiMinitArahanSeksyenLain",listMinitArahanSeksyenLain);
				}
       		
			}
			
			else if(flag_dokumen.equals("2")){
			
				vm = "app/pfd/frmKemaskiniDokumenKeluar.jsp";
				context.put("mode","View");
				context.put("readOnly","readonly");
				context.put("disabled","disabled");
				context.put("readOnlyBil","readonly");
				context.put("disabledBil","disabled");
				context.put("readOnlyNo","readonly");
				context.put("disabledNo","disabled");
	  	    	
				String idFail = getParam("idFail");
				
				view_CountDokumen(session);
		    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
				Hashtable hc = (Hashtable) paparCountDokumen.get(0);
				context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
				
				view_fail(session);
				paparFail = FrmKemaskiniDokumenData.getDataFail();
				Hashtable hd = (Hashtable) paparFail.get(0);
				context.put("status",hd.get("keterangan"));
				context.put("idFail",hd.get("idFail"));
		    	context.put("noFail",hd.get("noFail"));
		    	context.put("tajukFail",hd.get("tajukFail"));
		    	context.put("keterangan",hd.get("keterangan"));
		    	context.put("id_Status",hd.get("id_Status"));
		    	context.put("tarikh_Daftar_Fail",hd.get("tarikh_Daftar_Fail"));
				
		    	String idDokumen2 = getParam("idDokumen");
		    	context.put("idDokumen",idDokumen2);
		    	
		    	paparDokumen = FrmKemaskiniDokumenData.getDataDokumen2(idDokumen2);
				Hashtable ha = (Hashtable)paparDokumen.get(0);
						    	
		    	if(ha.get("jenis_Dokumen").equals("1")){
					
					context.put("jenis_Dokumen","SURAT");

				}
				if (ha.get("jenis_Dokumen").equals("2")){
					context.put("jenis_Dokumen","MEMO");
				}
				if(ha.get("jenis_Dokumen").equals("3")){
					context.put("jenis_Dokumen","LAPORAN");

				}
				if(ha.get("jenis_Dokumen").equals("4")){
					context.put("jenis_Dokumen","MINIT MESYUARAT");

				}
				if(ha.get("jenis_Dokumen").equals("5")){
					context.put("jenis_Dokumen","FAIL SUBJAKET");

				}
				context.put("jenis_Dokumen",ha.get("jenis_Dokumen"));
				context.put("id_Dokumen",ha.get("id_Dokumen"));
			    context.put("bil_Minit_Dokumen",ha.get("bil_Minit_Dokumen"));
			    context.put("no_Rujukan_Dokumen",ha.get("no_Rujukan_Dokumen"));
			    context.put("no_Rujukan_Lain",ha.get("no_Rujukan_Lain"));
			    context.put("tag_Dokumen",ha.get("tag_Dokumen"));
			    context.put("id_tagdokumen",ha.get("id_tagdokumen"));
			    context.put("tajuk_Dokumen",ha.get("tajuk_Dokumen"));
			    context.put("penerima_Dokumen",ha.get("nama_Penerima"));
			    context.put("tarikh_Dokumen",ha.get("tarikh_Dokumen"));
			    if(ha.get("idMinit").equals("1")){
				    context.put("checkedc1","checked");
			    }
			    else
			    {
			    	context.put("checkedc1","");
			    }
			    if(ha.get("idLaporan").equals("1")){
			    	context.put("checkedc2","checked");
			    }
			    else
			    {
			    	context.put("checkedc2","");
			    }
			    if(ha.get("idCD").equals("1")){
			    	context.put("checkedc3","checked");
			    }
			    else
			    {
			    	context.put("checkedc3","");
			    }
			    
			    
			    listPegawai = FrmKemaskiniDokumenData.getPaparDataPegawai(idDokumen2);
				this.context.put("SenaraiPegawai",listPegawai);
				
				listLampiranDokumen(session);
				listLampiranDokumen = FrmKemaskiniDokumenData.getListLampiranDokumen();
        		this.context.put("SenaraiListLampiranDokumen",listLampiranDokumen);
			    
			}
			else if(flag_dokumen.equals("5")){
				
				vm = "app/pfd/frmKemaskiniDokumenDalaman.jsp";
				context.put("mode","View");
				context.put("readOnly","readonly");
				context.put("disabled","disabled");
				context.put("readOnlyBil","readonly");
				context.put("disabledBil","disabled");
				context.put("readOnlyNo","readonly");
				context.put("disabledNo","disabled");
	  	    	
				String idFail = getParam("idFail");
				
				view_CountDokumen(session);
		    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
				Hashtable hc = (Hashtable) paparCountDokumen.get(0);
				context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
				
				view_fail(session);
				paparFail = FrmKemaskiniDokumenData.getDataFail();
				Hashtable hd = (Hashtable) paparFail.get(0);
				context.put("status",hd.get("keterangan"));
				context.put("idFail",hd.get("idFail"));
		    	context.put("noFail",hd.get("noFail"));
		    	context.put("tajukFail",hd.get("tajukFail"));
		    	context.put("keterangan",hd.get("keterangan"));
		    	context.put("id_Status",hd.get("id_Status"));
		    	context.put("tarikh_Daftar_Fail",hd.get("tarikh_Daftar_Fail"));
				
		    	String idDokumen2 = getParam("idDokumen");
		    	context.put("idDokumen",idDokumen2);
		    	
		    	paparDokumen = FrmKemaskiniDokumenData.getDataDokumen2(idDokumen2);
				Hashtable ha = (Hashtable)paparDokumen.get(0);
						    	
		    	if(ha.get("jenis_Dokumen").equals("1")){
					
					context.put("jenis_Dokumen","SURAT");

				}
				if (ha.get("jenis_Dokumen").equals("2")){
					context.put("jenis_Dokumen","MEMO");
				}
				if(ha.get("jenis_Dokumen").equals("3")){
					context.put("jenis_Dokumen","LAPORAN");

				}
				if(ha.get("jenis_Dokumen").equals("4")){
					context.put("jenis_Dokumen","MINIT MESYUARAT");

				}
				if(ha.get("jenis_Dokumen").equals("5")){
					context.put("jenis_Dokumen","FAIL SUBJAKET");

				}
				context.put("jenis_Dokumen",ha.get("jenis_Dokumen"));
				context.put("id_Dokumen",ha.get("id_Dokumen"));
			    context.put("bil_Minit_Dokumen",ha.get("bil_Minit_Dokumen"));
			    context.put("no_Rujukan_Dokumen",ha.get("no_Rujukan_Dokumen"));
			    context.put("no_Rujukan_Lain",ha.get("no_Rujukan_Lain"));
			    context.put("tag_Dokumen",ha.get("tag_Dokumen"));
			    context.put("id_tagdokumen",ha.get("id_tagdokumen"));
			    context.put("tajuk_Dokumen",ha.get("tajuk_Dokumen"));
			    context.put("penerima_Dokumen",ha.get("nama_Penerima"));
			    context.put("tarikh_Dokumen",ha.get("tarikh_Dokumen"));
			    if(ha.get("idMinit").equals("1")){
				    context.put("checkedc1","checked");
			    }
			    else
			    {
			    	context.put("checkedc1","");
			    }
			    if(ha.get("idLaporan").equals("1")){
			    	context.put("checkedc2","checked");
			    }
			    else
			    {
			    	context.put("checkedc2","");
			    }
			    if(ha.get("idCD").equals("1")){
			    	context.put("checkedc3","checked");
			    }
			    else
			    {
			    	context.put("checkedc3","");
			    }
			    
			    
			    listPegawai = FrmKemaskiniDokumenData.getPaparDataPegawai(idDokumen2);
				this.context.put("SenaraiPegawai",listPegawai);
								
				listLampiranDokumen(session);
				listLampiranDokumen = FrmKemaskiniDokumenData.getListLampiranDokumen();
        		this.context.put("SenaraiListLampiranDokumen",listLampiranDokumen);
			    
			}
			
			else{
				
				vm = "app/pfd/frmLogDokumenDariSeksyenLain.jsp";
				
			}

  	    	listDokumen(session);
  	    	listDokumen = FrmKemaskiniDokumenData.getListDokumen();
			context.put("SenaraiDokumen",listDokumen); 
			
			listMinitArahan(session);
  	    	listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			context.put("SenaraiMinit",listMinit);
				    	
	    	}
	    	
	    	else
		    {
		    	view_LevelArahan(session);
		    	paparCountLevelArahan = FrmKemaskiniDokumenData.getDataCountLevelArahan(id);
				Hashtable hz = (Hashtable) paparCountLevelArahan.get(0);
				Integer ab = Integer.parseInt(hz.get("levelArahan").toString());
				ab =	ab + 1; 
				context.put("levelArahan",Integer.parseInt(hz.get("levelArahan").toString()) + 1 );
		    	
		    	paparDokumen = FrmKemaskiniDokumenData.getDataDokumen(idDokumen);
				Hashtable h = (Hashtable)paparDokumen.get(0);
				
				
				if(h.get("flag_Dokumen").equals("1")){
					
					vm = "app/pfd/frmKemaskiniDokumenMasuk.jsp";
					context.put("mode","View");
					context.put("readOnly","readonly");
					context.put("disabled","disabled");
					context.put("readOnlyBil","readonly");
					context.put("disabledBil","disabled");
					context.put("readOnlyNo","readonly");
					context.put("disabledNo","disabled");
					context.put("paparArahan","false");
		  	    	
					String idFail = getParam("idFail");
					
					view_CountDokumen(session);
			    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
					Hashtable hc = (Hashtable) paparCountDokumen.get(0);
					context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
					
					view_fail(session);
					paparFail = FrmKemaskiniDokumenData.getDataFail();
					Hashtable hd = (Hashtable) paparFail.get(0);
					context.put("status",hd.get("keterangan"));
					context.put("idFail",hd.get("idFail"));
					context.put("noFail",hd.get("noFail"));
					context.put("tajukFail",hd.get("tajukFail"));
					context.put("keterangan",hd.get("keterangan"));
					context.put("id_Status",hd.get("id_Status"));
					context.put("tarikh_Daftar_Fail",hd.get("tarikh_Daftar_Fail"));
					
					String idDokumen2 = getParam("idDokumen");
					context.put("idDokumen",idDokumen2);
			    	
			    	paparDokumen = FrmKemaskiniDokumenData.getDataDokumen2(idDokumen2);
					Hashtable ha = (Hashtable)paparDokumen.get(0);
					
			    	
			    	if(ha.get("jenis_Dokumen").equals("1")){
						
						context.put("jenis_Dokumen","SURAT");

					}
					if (ha.get("jenis_Dokumen").equals("2")){
						context.put("jenis_Dokumen","MEMO");
					}
					if(ha.get("jenis_Dokumen").equals("3")){
						context.put("jenis_Dokumen","LAPORAN");

					}
					if(ha.get("jenis_Dokumen").equals("4")){
						context.put("jenis_Dokumen","MINIT MESYUARAT");

					}
					if(ha.get("jenis_Dokumen").equals("5")){
						context.put("jenis_Dokumen","FAIL SUBJAKET");

					}
					context.put("jenis_Dokumen",ha.get("jenis_Dokumen"));
					context.put("id_Dokumen",ha.get("id_Dokumen"));
				    context.put("bil_Minit_Dokumen",ha.get("bil_Minit_Dokumen"));
				    context.put("no_Rujukan_Dokumen",ha.get("no_Rujukan_Dokumen"));
				    context.put("no_Rujukan_Lain",ha.get("no_Rujukan_Lain"));
				    context.put("tajuk_Dokumen",ha.get("tajuk_Dokumen"));
				    context.put("id_tagdokumen",ha.get("id_tagdokumen"));
				    context.put("tag_Dokumen",ha.get("tag_Dokumen"));
				    context.put("pengirim_Dokumen",ha.get("nama_Pengirim"));
				    context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB",Long.parseLong(ha.get("id_nama_Penerima").toString()),"disabled"));					
				    context.put("tarikh_Dokumen",ha.get("tarikh_Dokumen"));
				    context.put("tarikh_Dokumen_Diterima",ha.get("tarikh_Dokumen_Diterima"));
				    if(ha.get("idMinit").equals("1")){
					    context.put("checkedc1","checked");
				    }
				    else
				    {
				    	context.put("checkedc1","");
				    }
				    if(ha.get("idLaporan").equals("1")){
				    	context.put("checkedc2","checked");
				    }
				    else
				    {
				    	context.put("checkedc2","");
				    }
				    if(ha.get("idCD").equals("1")){
				    	context.put("checkedc3","checked");
				    }
				    else
				    {
				    	context.put("checkedc3","");
				    }
	 				
				    listPegawaiTinggiDokumenMasuk(session);
			    	listPegawaiTinggi = FrmKemaskiniDokumenData.getListPegawai(idDokumen);
		    		this.context.put("SenaraiPegawaiTinggi",listPegawaiTinggi);
		    		
		    		Vector listPegawaiDokumenMasuk = FrmKemaskiniDokumenData.getDataPegawaiTinggi(idDokumen);
	        		Hashtable x = (Hashtable)listPegawaiDokumenMasuk.elementAt(0);
	     			this.context.put("selectPegawai",x.get("user_id"));
					
	     			listPA = FrmKemaskiniDokumenData.getListPA(idDokumen);
		    		this.context.put("SenaraiPA",listPA);
		    		
		    		
		    		Vector listPAPegawai = FrmLogDokumenData.getDataPAPegawai(idDokumen);
		    		Hashtable y ;
		    		if (listPAPegawai.size() > 0) {
	        			y = (Hashtable)listPAPegawai.elementAt(0);
	         			this.context.put("selectidPA",y.get("user_id"));
	    			 } else {
	    				 this.context.put("selectidPA","0"); 
	    			 }
		    		
					paparLampiranDokumenMasuk = FrmKemaskiniDokumenData.getListLampiranDokumenMasuk(idDokumen2);
	        		this.context.put("SenaraiListLampiranDokumenMasuk",paparLampiranDokumenMasuk);
					
	        		listMinitArahanPengarah(session);
	        		listMinitArahanPengarah = FrmKemaskiniDokumenData.getlistMinitArahanPengarah();
	        		Hashtable h1 = (Hashtable) listMinitArahanPengarah.get(0);
					if(h1.get("pegawaiMengarah").equals(""))
					{
						context.put("modeLevel","0");
						context.put("hidden1", "style=visibility:hidden");
					}
					else
					{
						context.put("modeLevel","1");
						context.put("hidden1", "");
						context.put("action1", "papar");
						context.put("SenaraiMinitArahanPengarah",listMinitArahanPengarah);
					}
	        		
	        		listMinitArahanPegawai1(session);
	        		listMinitArahanPegawai1 = FrmKemaskiniDokumenData.getListMinitArahanPegawai1();
	        		Hashtable h2 = (Hashtable) listMinitArahanPegawai1.get(0);
					if(h2.get("pegawaiMengarah").equals(""))
					{
						context.put("modeLevel","0");
						context.put("hidden2", "style=visibility:hidden");
					}
					else
					{
						context.put("modeLevel","2");
						context.put("hidden2", "");
						context.put("action1", "papar");
						context.put("SenaraiMinitArahanPegawai1",listMinitArahanPegawai1);
					}
					
	        		listMinitArahanPegawai2(session);
	        		listMinitArahanPegawai2 = FrmKemaskiniDokumenData.getListMinitArahanPegawai2();
	        		Hashtable h3 = (Hashtable) listMinitArahanPegawai2.get(0);
					if(h3.get("pegawaiMengarah").equals(""))
					{
						context.put("modeLevel","0");
						context.put("hidden3", "style=visibility:hidden");
					}
					else
					{
						context.put("modeLevel","3");
						context.put("hidden3", "");
						context.put("action1", "papar");
						context.put("SenaraiMinitArahanPegawai2",listMinitArahanPegawai2);
					}
					
	        		listMinitArahanPegawai3(session);
	        		listMinitArahanPegawai3 = FrmKemaskiniDokumenData.getListMinitArahanPegawai3();
	        		Hashtable h4 = (Hashtable) listMinitArahanPegawai3.get(0);
					if(h4.get("pegawaiMengarah").equals(""))
					{
						context.put("modeLevel","0");
						context.put("hidden4", "style=visibility:hidden");
					}
					else
					{
						context.put("modeLevel","4");
						context.put("hidden4", "");
						context.put("action1", "papar");
						context.put("SenaraiMinitArahanPegawai3",listMinitArahanPegawai3);
					}
					
	        		listMinitArahanPegawai4(session);
	        		
	        		String pegawaiMengarah4 = "";
	        		listMinitArahanPegawai4 = FrmKemaskiniDokumenData.getListMinitArahanPegawai4();
	        		if(listMinitArahanPegawai4.size()!=0){
	        			Hashtable h5 = (Hashtable) listMinitArahanPegawai4.get(0);
	        			pegawaiMengarah4 = (String)h5.get("pegawaiMengarah");
	        		}
	        		
					if(pegawaiMengarah4.equals(""))
					{
						context.put("modeLevel","0");
						context.put("hidden4", "style=visibility:hidden");
					}
					else
					{
						context.put("modeLevel","5");
						context.put("hidden5", "");
						context.put("action1", "papar");
						context.put("SenaraiMinitArahanPegawai4",listMinitArahanPegawai4);
					}
					
					listMinitArahanSelesai(session);
					listMinitArahanSelesai = FrmKemaskiniDokumenData.getListMinitArahanSelesai();
		       		Hashtable h99 = (Hashtable) listMinitArahanSelesai.get(0);
					if(h99.get("pegawaiMengarah").equals(""))
					{
						context.put("modeLevel","0");
						context.put("hiddenSelesai", "style=visibility:hidden");
					}
					else
					{
						context.put("modeLevel","selesai");
						context.put("hiddenSelesai", "");
						context.put("action1", "papar");
						context.put("SenaraiMinitArahanSelesai",listMinitArahanSelesai);
					}
					
					listMinitArahanSeksyenLain(session);
					listMinitArahanSeksyenLain = FrmKemaskiniDokumenData.getListMinitArahanSeksyenLain();
		       		Hashtable h6 = (Hashtable) listMinitArahanSelesai.get(0);
					if(h6.get("pegawaiMengarah").equals(""))
					{
						context.put("modeLevel","0");
						context.put("hiddenSeksyenLain","true");
					}
					else
					{
						context.put("modeLevel","seksyenLain");
						context.put("hiddenSeksyenLain", "false");
						context.put("action1", "papar");
						context.put("SenaraiMinitArahanSeksyenLain",listMinitArahanSeksyenLain);
					}
						        		
				}
				
				else if(h.get("flag_Dokumen").equals("2")){
					
					vm = "app/pfd/frmKemaskiniDokumenKeluar.jsp";
					context.put("mode","View");
					context.put("readOnly","readonly");
					context.put("disabled","disabled");
					context.put("readOnlyBil","readonly");
					context.put("disabledBil","disabled");
					context.put("readOnlyNo","readonly");
					context.put("disabledNo","disabled");
		  	    	
					String idFail = getParam("idFail");
					
					view_CountDokumen(session);
			    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
					Hashtable hc = (Hashtable) paparCountDokumen.get(0);
					context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
					
					view_fail(session);
					paparFail = FrmKemaskiniDokumenData.getDataFail();
					Hashtable hd = (Hashtable) paparFail.get(0);
					context.put("status",hd.get("keterangan"));
					context.put("idFail",hd.get("idFail"));
			    	context.put("noFail",hd.get("noFail"));
			    	context.put("tajukFail",hd.get("tajukFail"));
			    	context.put("keterangan",hd.get("keterangan"));
			    	context.put("id_Status",hd.get("id_Status"));
			    	context.put("tarikh_Daftar_Fail",hd.get("tarikh_Daftar_Fail"));
					
			    	String idDokumen2 = getParam("idDokumen");
			    	context.put("idDokumen",idDokumen2);
			    	
			    	paparDokumen = FrmKemaskiniDokumenData.getDataDokumen2(idDokumen2);
					Hashtable ha = (Hashtable)paparDokumen.get(0);
							    	
			    	if(ha.get("jenis_Dokumen").equals("1")){
						
						context.put("jenis_Dokumen","SURAT");

					}
					if (ha.get("jenis_Dokumen").equals("2")){
						context.put("jenis_Dokumen","MEMO");
					}
					if(ha.get("jenis_Dokumen").equals("3")){
						context.put("jenis_Dokumen","LAPORAN");

					}
					if(ha.get("jenis_Dokumen").equals("4")){
						context.put("jenis_Dokumen","MINIT MESYUARAT");

					}
					if(ha.get("jenis_Dokumen").equals("5")){
						context.put("jenis_Dokumen","FAIL SUBJAKET");

					}
					context.put("jenis_Dokumen",ha.get("jenis_Dokumen"));
					context.put("id_Dokumen",ha.get("id_Dokumen"));
				    context.put("bil_Minit_Dokumen",ha.get("bil_Minit_Dokumen"));
				    context.put("no_Rujukan_Dokumen",ha.get("no_Rujukan_Dokumen"));
				    context.put("no_Rujukan_Lain",ha.get("no_Rujukan_Lain"));
				    context.put("tajuk_Dokumen",ha.get("tajuk_Dokumen"));
				    context.put("id_tagdokumen",ha.get("id_tagdokumen"));
				    context.put("tag_Dokumen",ha.get("tag_Dokumen"));
				    context.put("penerima_Dokumen",ha.get("nama_Penerima"));
				    context.put("tarikh_Dokumen",ha.get("tarikh_Dokumen"));
				    if(ha.get("idMinit").equals("1")){
					    context.put("checkedc1","checked");
				    }
				    else
				    {
				    	context.put("checkedc1","");
				    }
				    if(ha.get("idLaporan").equals("1")){
				    	context.put("checkedc2","checked");
				    }
				    else
				    {
				    	context.put("checkedc2","");
				    }
				    if(ha.get("idCD").equals("1")){
				    	context.put("checkedc3","checked");
				    }
				    else
				    {
				    	context.put("checkedc3","");
				    }
				    
				    
				    listPegawai = FrmKemaskiniDokumenData.getPaparDataPegawai(idDokumen2);
					this.context.put("SenaraiPegawai",listPegawai);
					
		    		listPTFail(session);
					listPTFail = FrmKemaskiniDokumenData.getListPTFail();
		    		this.context.put("SenaraiPTFail",listPTFail);
		    		
		    		
		    		
		    		Vector listPTFailKeluar = FrmKemaskiniDokumenData.getDataPTKeluar(idDokumen2);
		    		
		    		if(listPTFailKeluar.size()>0)
		    		{
	        		Hashtable x = (Hashtable)listPTFailKeluar.elementAt(0);
	     			this.context.put("selectPTFail",x.get("user_id"));
		    		}
		    		else
		    		{
		    		this.context.put("selectPTFail","");	
		    		}
	     			
	     			
	     			
	     			paparLampiranDokumenKeluar = FrmKemaskiniDokumenData.getListLampiranDokumenKeluar(idDokumen2);
	           		this.context.put("SenaraiListLampiranDokumenKeluar",paparLampiranDokumenKeluar);
					
					listLampiranDokumen(session);
					listLampiranDokumen = FrmKemaskiniDokumenData.getListLampiranDokumen();
	        		this.context.put("SenaraiListLampiranDokumen",listLampiranDokumen);
				    
				}
				else if(h.get("flag_Dokumen").equals("5")){
					
					vm = "app/pfd/frmKemaskiniDokumenDalaman.jsp";
					context.put("mode","View");
					context.put("readOnly","readonly");
					context.put("disabled","disabled");
					context.put("readOnlyBil","readonly");
					context.put("disabledBil","disabled");
					context.put("readOnlyNo","readonly");
					context.put("disabledNo","disabled");
		  	    	
					String idFail = getParam("idFail");
					
					view_CountDokumen(session);
			    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
					Hashtable hc = (Hashtable) paparCountDokumen.get(0);
					context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
					
					view_fail(session);
					paparFail = FrmKemaskiniDokumenData.getDataFail();
					Hashtable hd = (Hashtable) paparFail.get(0);
					context.put("status",hd.get("keterangan"));
					context.put("idFail",hd.get("idFail"));
			    	context.put("noFail",hd.get("noFail"));
			    	context.put("tajukFail",hd.get("tajukFail"));
			    	context.put("keterangan",hd.get("keterangan"));
			    	context.put("id_Status",hd.get("id_Status"));
			    	context.put("tarikh_Daftar_Fail",hd.get("tarikh_Daftar_Fail"));
					
			    	String idDokumen2 = getParam("idDokumen");
			    	context.put("idDokumen",idDokumen2);
			    	
			    	paparDokumen = FrmKemaskiniDokumenData.getDataDokumen2(idDokumen2);
					Hashtable ha = (Hashtable)paparDokumen.get(0);
					
			    	
			    	if(ha.get("jenis_Dokumen").equals("1")){
						
						context.put("jenis_Dokumen","SURAT");

					}
					if (ha.get("jenis_Dokumen").equals("2")){
						context.put("jenis_Dokumen","MEMO");
					}
					if(ha.get("jenis_Dokumen").equals("3")){
						context.put("jenis_Dokumen","LAPORAN");

					}
					if(ha.get("jenis_Dokumen").equals("4")){
						context.put("jenis_Dokumen","MINIT MESYUARAT");

					}
					if(ha.get("jenis_Dokumen").equals("5")){
						context.put("jenis_Dokumen","FAIL SUBJAKET");

					}
					context.put("jenis_Dokumen",ha.get("jenis_Dokumen"));
					context.put("id_Dokumen",ha.get("id_Dokumen"));
				    context.put("bil_Minit_Dokumen",ha.get("bil_Minit_Dokumen"));
				    context.put("no_Rujukan_Dokumen",ha.get("no_Rujukan_Dokumen"));
				    context.put("no_Rujukan_Lain",ha.get("no_Rujukan_Lain"));
				    context.put("tajuk_Dokumen",ha.get("tajuk_Dokumen"));
				    context.put("id_tagdokumen",ha.get("id_tagdokumen"));
				    context.put("tag_Dokumen",ha.get("tag_Dokumen"));
				    context.put("penerima_Dokumen",ha.get("nama_Penerima"));
				    context.put("tarikh_Dokumen",ha.get("tarikh_Dokumen"));
				    if(ha.get("idMinit").equals("1")){
					    context.put("checkedc1","checked");
				    }
				    else
				    {
				    	context.put("checkedc1","");
				    }
				    if(ha.get("idLaporan").equals("1")){
				    	context.put("checkedc2","checked");
				    }
				    else
				    {
				    	context.put("checkedc2","");
				    }
				    if(ha.get("idCD").equals("1")){
				    	context.put("checkedc3","checked");
				    }
				    else
				    {
				    	context.put("checkedc3","");
				    }
				    
				    
				    listPegawai = FrmKemaskiniDokumenData.getPaparDataPegawai(idDokumen2);
					this.context.put("SenaraiPegawai",listPegawai);
					
		    		listPTFail(session);
					listPTFail = FrmKemaskiniDokumenData.getListPTFail();
		    		this.context.put("SenaraiPTFail",listPTFail);
		    		
		    		
		    		
		    		Vector listPTFailKeluar = FrmKemaskiniDokumenData.getDataPTKeluar(idDokumen2);
		    		
		    		if(listPTFailKeluar.size()>0)
		    		{
	        		Hashtable x = (Hashtable)listPTFailKeluar.elementAt(0);
	     			this.context.put("selectPTFail",x.get("user_id"));
		    		}
		    		else
		    		{
		    		this.context.put("selectPTFail","");	
		    		}
	     			
	     			
	     			
	     			paparLampiranDokumenKeluar = FrmKemaskiniDokumenData.getListLampiranDokumenKeluar(idDokumen2);
	           		this.context.put("SenaraiListLampiranDokumenKeluar",paparLampiranDokumenKeluar);
					
					listLampiranDokumen(session);
					listLampiranDokumen = FrmKemaskiniDokumenData.getListLampiranDokumen();
	        		this.context.put("SenaraiListLampiranDokumen",listLampiranDokumen);
				    
				}
				else{
					
					vm = "app/pfd/frmLogDokumenDariSeksyenLain.jsp";
					
				}

	  	    	listDokumen(session);
	  	    	listDokumen = FrmKemaskiniDokumenData.getListDokumen();
				context.put("SenaraiDokumen",listDokumen); 
				
				listMinitArahan(session);
	  	    	listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
				context.put("SenaraiMinit",listMinit);
	    	}
		
		}
			
		else if ("hapusDokumenMasuk".equals(action1)){
			
			hapus_Dokumen(session);
			
			vm = "app/pfd/frmKemaskiniDokumenMasuk.jsp";
			context.put("mode","New");
			context.put("readOnly","readOnly");
			context.put("disabled","disabled");
			context.put("readOnlyBil","disabled");
			context.put("disabledBil","disabledBil");
			context.put("readOnlyNo","readOnlyNo");
			context.put("disabledNo","disabledNo");
			context.put("action1","tabDokumenMasuk");
			
			String idFail = getParam("idFail");
			
			view_CountDokumen(session);
	    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
			Hashtable hc = (Hashtable) paparCountDokumen.get(0);
			context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
	
			Hashtable h = (Hashtable) paparFail.get(0);
			context.put("status",h.get("keterangan"));
			context.put("idFail",h.get("idFail"));
	    	context.put("noFail",h.get("noFail"));
	    	context.put("tajukFail",h.get("tajukFail"));
	    	context.put("keterangan",h.get("keterangan"));
	    	context.put("id_Status",h.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",h.get("tarikh_Daftar_Fail"));
			
			context.put("checkedMasuk","");
 			context.put("checkedKeluar","");
			context.put("id_Dokumen","");
	    	context.put("bil_Minit_Dokumen", "");
	    	context.put("jenis_Dokumen","");
	    	context.put("no_Rujukan_Dokumen","");
	    	context.put("no_Rujukan_Lain","");
	    	context.put("tajuk_Dokumen","");
	    	context.put("tarikh_Dokumen", "");
	    	context.put("tarikh_Dokumen_Diterima","");
	    	context.put("tarikh_Dokumen_Masuk","");
	    	context.put("selectPegawaiA",HTML.SelectPegawai("socPegawaiA"));
			context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB"));
	    	context.put("tarikh_Daftar", "");
	    	context.put("flag_Dokumen","");
	    	context.put("jenisDokumen","dokumenMsk");
	    	context.put("pengirim_Dokumen","");
	    	
	    	listPA(session);
	    	listPA = FrmKemaskiniDokumenData.getListPA();
    		this.context.put("SenaraiPA",listPA);   	

			listDokumen(session);
			listDokumen = FrmKemaskiniDokumenData.getListDokumen();
			this.context.put("SenaraiDokumen",listDokumen);
			
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			this.context.put("SenaraiMinit",listMinit);
						
		}
			
		else if ("hapusDokumenKeluar".equals(action1)){

			hapus_Dokumen(session);
			
			vm = "app/pfd/frmKemaskiniDokumenKeluar.jsp";
			context.put("mode","New");
			context.put("readOnly","");
			context.put("disabled","");
			context.put("readOnlyBil","");
			context.put("disabledBil","");
			context.put("readOnlyNo","");
			context.put("disabledNo","");
			context.put("action1","");
			
			
			String idFail = getParam("idFail");
			
			view_CountDokumen(session);
	    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
			Hashtable hc = (Hashtable) paparCountDokumen.get(0);
			context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
	
			Hashtable h = (Hashtable) paparFail.get(0);
			context.put("status",h.get("keterangan"));
			context.put("idFail",h.get("idFail"));
	    	context.put("noFail",h.get("noFail"));
	    	context.put("tajukFail",h.get("tajukFail"));
	    	context.put("keterangan",h.get("keterangan"));
	    	context.put("id_Status",h.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",h.get("tarikh_Daftar_Fail"));
			
			context.put("checkedc1","");
 			context.put("checkedc2","");
			context.put("checkedc3","");
	    	context.put("bil_Minit_Dokumen", "");
	    	context.put("jenis_Dokumen","");
	    	context.put("no_Rujukan_Dokumen","");
	    	context.put("no_Rujukan_Lain","");
	    	context.put("tajuk_Dokumen","");
	    	context.put("tarikh_Dokumen", "");
	    	context.put("tarikh_Dokumen_Keluar", "");
	    	context.put("tarikh_Dokumen_Diterima","");
	    	context.put("penerima_Dokumen","");
	    	context.put("SenaraiPegawai","");
	    	context.put("SenaraiPTFail", "");
	    	context.put("flag_Dokumen","");
	    	context.put("jenisDokumen","dokumenMsk");
			
	    	
	    	String idDokumen = getParam("idDokumen");
	    	
	    	paparLampiranDokumenKeluar = FrmKemaskiniDokumenData.getListLampiranDokumenKeluar(idDokumen);
       		this.context.put("SenaraiListLampiranDokumenKeluar",paparLampiranDokumenKeluar);
	    	
			listDokumen(session);
			listDokumen = FrmKemaskiniDokumenData.getListDokumen();
			this.context.put("SenaraiDokumen",listDokumen);
			
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			this.context.put("SenaraiMinit",listMinit);
						
		}
		else if ("hapusDokumenDalaman".equals(action1)){

			hapus_Dokumen(session);
			
			vm = "app/pfd/frmKemaskiniDokumenDalaman.jsp";
			context.put("mode","New");
			context.put("readOnly","");
			context.put("disabled","");
			context.put("readOnlyBil","");
			context.put("disabledBil","");
			context.put("readOnlyNo","");
			context.put("disabledNo","");
			context.put("action1","");
			
			
			String idFail = getParam("idFail");
			
			view_CountDokumen(session);
	    	paparCountDokumen = FrmKemaskiniDokumenData.getDataCountDokumen(idFail);
			Hashtable hc = (Hashtable) paparCountDokumen.get(0);
			context.put("jumlah_Dokumen",hc.get("jumlah_Dokumen"));
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
	
			Hashtable h = (Hashtable) paparFail.get(0);
			context.put("status",h.get("keterangan"));
			context.put("idFail",h.get("idFail"));
	    	context.put("noFail",h.get("noFail"));
	    	context.put("tajukFail",h.get("tajukFail"));
	    	context.put("keterangan",h.get("keterangan"));
	    	context.put("id_Status",h.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",h.get("tarikh_Daftar_Fail"));
			
			context.put("checkedc1","");
 			context.put("checkedc2","");
			context.put("checkedc3","");
	    	context.put("bil_Minit_Dokumen", "");
	    	context.put("jenis_Dokumen","");
	    	context.put("no_Rujukan_Dokumen","");
	    	context.put("no_Rujukan_Lain","");
	    	context.put("tajuk_Dokumen","");
	    	context.put("tarikh_Dokumen", "");
	    	context.put("tarikh_Dokumen_Keluar", "");
	    	context.put("tarikh_Dokumen_Diterima","");
	    	context.put("penerima_Dokumen","");
	    	context.put("SenaraiPegawai","");
	    	context.put("SenaraiPTFail", "");
	    	context.put("flag_Dokumen","");
	    	context.put("jenisDokumen","dokumenMsk");
			
	    	
	    	String idDokumen = getParam("idDokumen");
	    	
	    	paparLampiranDokumenKeluar = FrmKemaskiniDokumenData.getListLampiranDokumenKeluar(idDokumen);
       		this.context.put("SenaraiListLampiranDokumenKeluar",paparLampiranDokumenKeluar);
	    	
			listDokumen(session);
			listDokumen = FrmKemaskiniDokumenData.getListDokumen();
			this.context.put("SenaraiDokumen",listDokumen);
			
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			this.context.put("SenaraiMinit",listMinit);
						
		}
		
		else if ("tambahLampiran".equals(action1)){
			
			vm = "app/pfd/frmPaparLampiran.jsp";
				
			String idDokumen = getParam("idDokumen");
			context.put("idDokumen", idDokumen);
	    	
	    	paparDokumen = FrmKemaskiniDokumenData.getDataDokumen(idDokumen);
			Hashtable hD = (Hashtable)paparDokumen.get(0);
			
			
			if(hD.get("jenis_Dokumen").equals("1")){
				
				context.put("jenis_Dokumen","SURAT");

			}
			if (hD.get("jenis_Dokumen").equals("2")){
				context.put("jenis_Dokumen","MEMO");

			}
			if(hD.get("jenis_Dokumen").equals("3")){
				context.put("jenis_Dokumen","LAPORAN");

			}
			if(hD.get("jenis_Dokumen").equals("4")){
				context.put("jenis_Dokumen","MINIT MESYUARAT");

			}
			if(hD.get("jenis_Dokumen").equals("5")){
				context.put("jenis_Dokumen","FAIL SUBJAKET");

			}
			context.put("id_Dokumen",hD.get("id_Dokumen"));
	    	context.put("no_Rujukan_Dokumen",hD.get("no_Rujukan_Dokumen"));
	    	
	    	 list_Lampiran(session);
	    	 listLampiran = FrmKemaskiniDokumenData.getListLampiran();
        	 context.put("SenaraiLampiran",listLampiran);
        	 context.put("completed",false);
        	 
        	 
        	 
        	 
		}
			
		else if ("simpanLampiran".equals(action1)){
 	 		String idDokumen = getParam("idDokumen");
			
			uploadLampiran(idDokumen);

   	 		vm = "app/pfd/frmPaparLampiran.jsp";
	    	
   	 		paparDokumen = FrmKemaskiniDokumenData.getDataDokumen(idDokumen);
   	 		Hashtable hD = (Hashtable)paparDokumen.get(0);
			
   	 		if(hD.get("jenis_Dokumen").equals("1")){	
			context.put("jenis_Dokumen","SURAT");
   	 		}
   	 		if (hD.get("jenis_Dokumen").equals("2")){
			context.put("jenis_Dokumen","MEMO");
   	 		}
   	 		if(hD.get("jenis_Dokumen").equals("3")){
			context.put("jenis_Dokumen","LAPORAN");
   	 		}
   	 		if(hD.get("jenis_Dokumen").equals("4")){
			context.put("jenis_Dokumen","MINIT MESYUARAT");
   	 		}
			if(hD.get("jenis_Dokumen").equals("5")){
				context.put("jenis_Dokumen","FAIL SUBJAKET");

			}
   	 		context.put("id_Dokumen",hD.get("id_Dokumen"));
   	 		context.put("no_Rujukan_Dokumen",hD.get("no_Rujukan_Dokumen"));
   	 	
   	 		
   	 		list_Lampiran(session);
   	 		listLampiran = FrmKemaskiniDokumenData.getListLampiran();
   	 		context.put("SenaraiLampiran",listLampiran);
   	 		context.put("completed",true);	 	 
   		
		} 
			
		else if ("hapusLampiran".equals(action1)){
			hapusLampiran(session);
			vm = "app/pfd/frmPaparLampiran.jsp";
			 
			String idDokumen = getParam("idDokumen");
	    	
	    	paparDokumen = FrmKemaskiniDokumenData.getDataDokumen(idDokumen);
			Hashtable hD = (Hashtable)paparDokumen.get(0);
			
			
			if(hD.get("jenis_Dokumen").equals("1")){
				
				context.put("jenis_Dokumen","SURAT");

			}
			if (hD.get("jenis_Dokumen").equals("2")){
				context.put("jenis_Dokumen","MEMO");

			}
			if(hD.get("jenis_Dokumen").equals("3")){
				context.put("jenis_Dokumen","LAPORAN");

			}
			if(hD.get("jenis_Dokumen").equals("4")){
				context.put("jenis_Dokumen","MINIT MESYUARAT");

			}
			if(hD.get("jenis_Dokumen").equals("5")){
				context.put("jenis_Dokumen","FAIL SUBJAKET");

			}
			context.put("id_Dokumen",hD.get("id_Dokumen"));
	    	context.put("no_Rujukan_Dokumen",hD.get("no_Rujukan_Dokumen"));
		    	
		     list_Lampiran(session);
	         listLampiran = FrmKemaskiniDokumenData.getListLampiran();
	         context.put("SenaraiLampiran",listLampiran);
			 context.put("completed",false);
			
		}
		
		else if ("tambahMinitPage".equals(action1)){
			
			vm = "app/pfd/frmKemaskiniDokumenMasukArahan.jsp";
			context.put("modeMinit","newMinit");
			context.put("modetambahPeg","");
			context.put("readOnlyMinit","");
			context.put("disabledMinit","");
			
			String statusTindakan = getParam("statusTindakan");
			String idMinitArahan = getParam("idMinitArahan");
		    String user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
			context.put("idNegeri",user_negeri);
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
			
			if(paparFail.isEmpty()){
				context.put("status","");
				context.put("idFail","");
		    	context.put("noFail","");
		    	context.put("tajukFail","");
		    	context.put("keterangan","");
		    	context.put("id_Status","");
		    	context.put("tarikh_Daftar_Fail","");
				
				
			}
			else{
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
			}
			
			if(statusTindakan.equalsIgnoreCase("99") ){
				context.put("modeStatusTindakan","1"); 
				context.put("action1","tambahPeg3Masuk");
				context.put("status_Tindakan",statusTindakan);
				context.put("idMinitArahan",idMinitArahan);
				
			}
			
			else{
				context.put("modeStatusTindakan",""); 
				context.put("action1","tambahPeg3Masuk");
				context.put("status_Tindakan",statusTindakan);
				context.put("idMinitArahan",idMinitArahan);
			}
			
		}	
			
		else if ("tambahPeg3Masuk".equals(action1)){
			
			vm = "app/pfd/frmKemaskiniDokumenMasukArahan.jsp";
			context.put("modeMinit","newMinit");
			context.put("modetambahPeg","3");
			context.put("readOnlyMinit","");
			context.put("disabledMinit","");
			context.put("emel","notsuccess");
			
			view_Seksyen(session);
         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
         	Hashtable hB = (Hashtable) paparSeksyen.get(0);
         	String id_seksyen = String.valueOf(hB.get("id_seksyen"));
         	String id_negeri = String.valueOf(hB.get("id_negeri"));
         	context.put("idSeksyen",(hB.get("id_seksyen")));
         	context.put("idNegeri",(hB.get("id_negeri")));
			
			String id = getParam("idDokumen");
			String idMinitArahanSebelum = getParam("idMinitArahan");
			String minitArahan = getParam("minitArahan");
			String socStatusTindakan = getParam("statusTindakan");
			String tarikh = getParam("tarikh");
			String catatan = getParam("catatan");
			
			context.put("minit_Arahan", minitArahan);
			context.put("status_Tindakan",socStatusTindakan);
			context.put("tarikh_Minit_Arahan", sdf.format(now));
			context.put("catatan",catatan);
			context.put("idDokumen",id);
			context.put("idMinitArahanSebelum",idMinitArahanSebelum);
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
			
			if(paparFail.isEmpty()){
				context.put("status","");
				context.put("idFail","");
		    	context.put("noFail","");
		    	context.put("tajukFail","");
		    	context.put("keterangan","");
		    	context.put("id_Status","");
		    	context.put("tarikh_Daftar_Fail","");
				
				
			}
			else{
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
			}
			
	    	String user_name = (String)session.getAttribute("_portal_username");
	    	String user_id = (String)session.getAttribute("_ekptg_user_id");
	    	context.put("user_Name", user_name);
	    	context.put("user_Id", user_id);
	    	
	    	view_LevelArahan(session);
	    	paparCountLevelArahan = FrmKemaskiniDokumenData.getDataCountLevelArahan(id);
			Hashtable hc = (Hashtable) paparCountLevelArahan.get(0);
			Integer ab = Integer.parseInt(hc.get("levelArahan").toString());
			ab =	ab + 1; 
			context.put("levelArahan",Integer.parseInt(hc.get("levelArahan").toString()) + 1 );
	    	
			paparLampiranDokumenMasuk = FrmKemaskiniDokumenData.getListLampiranDokumenMasuk(id);
    		this.context.put("SenaraiListLampiranDokumenMasuk",paparLampiranDokumenMasuk);
    		this.context.put("paparLampiranDokumenMasuk_size", paparLampiranDokumenMasuk.size());
			
			view_dokumen(session);
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen();
			Hashtable hD = (Hashtable)paparDokumen.get(0);
			
			if(hD.get("jenis_Dokumen").equals("1")){
			context.put("jenis_Dokumen","SURAT");
			}
			if (hD.get("jenis_Dokumen").equals("2")){
				context.put("jenis_Dokumen","MEMO");
			}
			if(hD.get("jenis_Dokumen").equals("3")){
				context.put("jenis_Dokumen","LAPORAN");
			}
			if(hD.get("jenis_Dokumen").equals("4")){
				context.put("jenis_Dokumen","MINIT MESYUARAT");
			}
			if(hD.get("jenis_Dokumen").equals("5")){
				context.put("jenis_Dokumen","SUBJAKET");
			}
			context.put("tajuk_Dokumen",hD.get("tajuk_Dokumen"));
			context.put("id_Dokumen",hD.get("id_Dokumen"));
			context.put("no_Rujukan_Dokumen",hD.get("no_Rujukan_Dokumen"));
						    
			context.put("id_Minitarahan","");
			
			listPegawai1(session);
			listPegawai1 = FrmKemaskiniDokumenData.getListPegawai1();
    		this.context.put("SenaraiPegawai1",listPegawai1);
    		
			listPegawai2(session);
			listPegawai2 = FrmKemaskiniDokumenData.getListPegawai2();
    		this.context.put("SenaraiPegawai2",listPegawai2);
    		
			listPegawai3(session);
			listPegawai3 = FrmKemaskiniDokumenData.getListPegawai3();
    		this.context.put("SenaraiPegawai3",listPegawai3);
				
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			context.put("SenaraiMinit",listMinit);    	

		}
			
		else if ("tambahMinitSeksyenLain".equals(action1)){
			
			String idDokumen = getParam("idDokumen");
			
			vm = "app/pfd/frmKemaskiniArahanMinitSeksyenLain.jsp";
			context.put("modeMinit","newMinit");
			context.put("readonly","readonly");
			context.put("disabled","");
			context.put("readonlyMinit","");
			context.put("disabledMinit","");
			context.put("pagemode","1");
			context.put("idDokumen",idDokumen);
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("catatan",hb.get("catatan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
	    	
	    	String user_name = (String)session.getAttribute("_portal_username");
	    	String user_id = (String)session.getAttribute("_ekptg_user_id");
	    	context.put("user_Name", user_name);
	    	context.put("user_Id", user_id);
	    	
			paparLampiranDokumenMasuk = FrmKemaskiniDokumenData.getListLampiranDokumenMasuk(idDokumen);
    		this.context.put("SenaraiListLampiranDokumenMasuk",paparLampiranDokumenMasuk);
    		this.context.put("paparLampiranDokumenMasuk_size", paparLampiranDokumenMasuk.size());

	    	this.context.put("selectSeksyenPAR",HTML.SelectSeksyenPAR("socSeksyenPAR",Utils.parseLong(idSeksyen),null,"onChange=\"doChangeSeksyenPAR();\" "));
	    	
	    		if ("doChangeSeksyenPAR".equals(submit)){
	    			idDokumen = getParam("idDokumen");
	    			String tajuk_Dokumen = getParam("tajuk_Dokumen");
	    			String no_Rujukan_Lain = getParam("no_Rujukan_Lain");
	    			String pengirim_Dokumen = getParam("pengirim_Dokumen");	 
	    			String levelArahan = getParam("levelArahan");	 
        	    	this.context.put("selectPAR",HTML.SelectPARByIdSeksyen(idSeksyen,"socPAR",null,null,null));
        	    	String socPAR = getParam("socPAR");
        	    	String socSeksyenPAR = getParam("socSeksyenPAR");
        	    	getPARbyIdSeksyen = logic.getPARbyIdSeksyen(getParam("socSeksyenPAR"));
           	    	this.context.put("getPARbyIdSeksyen_size", getPARbyIdSeksyen.size());
           	    	this.context.put("idDokumen", idDokumen);
           	    	this.context.put("tajuk_Dokumen", tajuk_Dokumen);
           	    	this.context.put("no_Rujukan_Lain", no_Rujukan_Lain);
           	    	this.context.put("pengirim_Dokumen", pengirim_Dokumen);
           	    	this.context.put("levelArahan", levelArahan);
        	    	
        	    
        	    } 
        	    else {
        	    
        		}   
	    	
			view_dokumen(session);
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen();
			Hashtable hD = (Hashtable)paparDokumen.get(0);
			
			context.put("tajuk_Dokumen",hD.get("tajuk_Dokumen"));
			context.put("idDokumen",hD.get("id_Dokumen"));
			context.put("no_Rujukan_Lain",hD.get("no_Rujukan_Lain"));
						    
			context.put("id_Minitarahan","");
			context.put("minit_Arahan","");
			context.put("catatan","");
			context.put("selectStatusTindakan",HTML.SelectStatusTindakan("socStatusTindakan"));
			context.put("tarikh_Minit_Arahan",sdf.format(now));
			
			
		}
			
		else if ("simpanSeksyenLain".equals(action1)){
			context.put("modeMinit","paparMinit");
			context.put("readOnlyMinit","readonly");
			context.put("disabledMinit","disabled");
			
			String idDokumen = getParam("idDokumen");
			
			vm = "app/pfd/frmKemaskiniArahanMinitSeksyenLain.jsp";
			
			String idMinitArahanSeksyenLain = simpanSeksyenLain(session);
			
		   	String user_name = (String)session.getAttribute("_portal_username");
	    	String user_id = (String)session.getAttribute("_ekptg_user_id");
	    	context.put("user_Name", user_name);
	    	context.put("user_Id", user_id);
	    	
			paparLampiranDokumenMasuk = FrmKemaskiniDokumenData.getListLampiranDokumenMasuk(idDokumen);
    		this.context.put("SenaraiListLampiranDokumenMasuk",paparLampiranDokumenMasuk);
    		this.context.put("paparLampiranDokumenMasuk_size", paparLampiranDokumenMasuk.size());
         	
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("catatan",hb.get("catatan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
			
	    	view_dokumen(session);
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen();
			Hashtable hD = (Hashtable)paparDokumen.get(0);
			context.put("tajuk_Dokumen",hD.get("tajuk_Dokumen"));
			context.put("id_Dokumen",hD.get("id_Dokumen"));
			context.put("no_Rujukan_Lain",hD.get("no_Rujukan_Lain"));
	    	
 			paparMinitArahanSeksyenLain = FrmKemaskiniDokumenData.getDataMinitArahanSeksyenLain(idMinitArahanSeksyenLain);
 			
 			Hashtable h = (Hashtable)paparMinitArahanSeksyenLain.get(0);	
 			context.put("idMinitArahanSeksyenLain",h.get("idMinitArahanSeksyenLain"));
 			context.put("idDokumen",h.get("idDokumen"));
 			context.put("tajuk_Dokumen",h.get("tajuk_Dokumen"));
 			context.put("no_Rujukan_Lain",h.get("no_Rujukan_Lain"));
 			context.put("pengarah",h.get("pengarah"));
 			context.put("penerima",h.get("penerima"));
 			this.context.put("selectSeksyen",HTML.SelectSeksyen("idSeksyen",Utils.parseLong(idSeksyen),"disabled"));
 			context.put("tarikh_Minit_Arahan",h.get("tarikh_Arahan"));
 			context.put("catatan",h.get("catatan"));
 			
 			String tarikh_Minit_Arahan = String.valueOf(h.get("tarikh_Arahan"));
			String tajuk_Dokumen = String.valueOf(h.get("tajuk_Dokumen"));
			String no_Rujukan_Dokumen = String.valueOf(h.get("no_Rujukan_Lain"));
 			
		    updateStatusTugasan(session); 			
		}
			
		else if ("kemaskiniMinitSeksyenLain".equals(action1)){
			
			String idMinitArahanSeksyenLain = getParam("idMinitArahanSeksyenLain");		
			String idDokumen = getParam("idDokumen");
			
			vm = "app/pfd/frmKemaskiniArahanMinitSeksyenLain.jsp";
			context.put("modeMinit","newMinit");
			context.put("readonly","readonly");
			context.put("disabled","");
			context.put("readonlyMinit","");
			context.put("disabledMinit","");
			context.put("pagemode","1");
			
			String user_name = (String)session.getAttribute("_portal_username");
	    	String user_id = (String)session.getAttribute("_ekptg_user_id");
	    	context.put("user_Name", user_name);
	    	context.put("user_Id", user_id);
	    	
			paparLampiranDokumenMasuk = FrmKemaskiniDokumenData.getListLampiranDokumenMasuk(idDokumen);
    		this.context.put("SenaraiListLampiranDokumenMasuk",paparLampiranDokumenMasuk);
    		this.context.put("paparLampiranDokumenMasuk_size", paparLampiranDokumenMasuk.size());
         	
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("catatan",hb.get("catatan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
			
	    	view_dokumen(session);
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen();
			Hashtable hD = (Hashtable)paparDokumen.get(0);
			context.put("tajuk_Dokumen",hD.get("tajuk_Dokumen"));
			context.put("id_Dokumen",hD.get("id_Dokumen"));
			context.put("no_Rujukan_Lain",hD.get("no_Rujukan_Lain"));
	    	
			paparLampiranDokumenMasuk = FrmKemaskiniDokumenData.getListLampiranDokumenMasuk(idDokumen);
    		this.context.put("SenaraiListLampiranDokumenMasuk",paparLampiranDokumenMasuk);
    		this.context.put("paparLampiranDokumenMasuk_size", paparLampiranDokumenMasuk.size());
	    	
 			paparMinitArahanSeksyenLain = FrmKemaskiniDokumenData.getDataMinitArahanSeksyenLain(idMinitArahanSeksyenLain);
 			
 			Hashtable h = (Hashtable)paparMinitArahanSeksyenLain.get(0);	
 			context.put("idMinitArahanSeksyenLain",h.get("idMinitArahanSeksyenLain"));
 			context.put("idDokumen",h.get("idDokumen"));
 			context.put("tajuk_Dokumen",h.get("tajuk_Dokumen"));
 			context.put("no_Rujukan_Lain",h.get("no_Rujukan_Lain"));
 			context.put("pengarah",h.get("pengarah"));
 			context.put("penerima",h.get("penerima"));
 			this.context.put("selectSeksyen",HTML.SelectSeksyen("idSeksyen",Utils.parseLong(idSeksyen),"disabled"));
 			context.put("tarikh_Minit_Arahan",h.get("tarikh_Arahan"));
 			context.put("catatan",h.get("catatan"));
 			
 			String tarikh_Minit_Arahan = String.valueOf(h.get("tarikh_Arahan"));
			String tajuk_Dokumen = String.valueOf(h.get("tajuk_Dokumen"));
			String no_Rujukan_Dokumen = String.valueOf(h.get("no_Rujukan_Lain"));
			
		}
			
		else if ("paparMinitSeksyenLain".equals(action1)){
			
			String idMinitArahanSeksyenLain = getParam("idMinitArahanSeksyenLain");		
			String idDokumen = getParam("idDokumen");
			
			vm = "app/pfd/frmKemaskiniArahanMinitSeksyenLain.jsp";
			context.put("modeMinit","paparMinit");
			context.put("readonly","readonly");
			context.put("disabled","");
			context.put("readonlyMinit","");
			context.put("disabledMinit","");
			context.put("pagemode","1");
			
			String user_name = (String)session.getAttribute("_portal_username");
	    	String user_id = (String)session.getAttribute("_ekptg_user_id");
	    	context.put("user_Name", user_name);
	    	context.put("user_Id", user_id);
	    	
			paparLampiranDokumenMasuk = FrmKemaskiniDokumenData.getListLampiranDokumenMasuk(idDokumen);
    		this.context.put("SenaraiListLampiranDokumenMasuk",paparLampiranDokumenMasuk);
    		this.context.put("paparLampiranDokumenMasuk_size", paparLampiranDokumenMasuk.size());
         	
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("catatan",hb.get("catatan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
			
	    	view_dokumen(session);
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen();
			Hashtable hD = (Hashtable)paparDokumen.get(0);
			context.put("tajuk_Dokumen",hD.get("tajuk_Dokumen"));
			context.put("id_Dokumen",hD.get("id_Dokumen"));
			context.put("no_Rujukan_Lain",hD.get("no_Rujukan_Lain"));
	    	
			paparLampiranDokumenMasuk = FrmKemaskiniDokumenData.getListLampiranDokumenMasuk(idDokumen);
    		this.context.put("SenaraiListLampiranDokumenMasuk",paparLampiranDokumenMasuk);
    		this.context.put("paparLampiranDokumenMasuk_size", paparLampiranDokumenMasuk.size());
	    	
 			paparMinitArahanSeksyenLain = FrmKemaskiniDokumenData.getDataMinitArahanSeksyenLain(idMinitArahanSeksyenLain);
 			
 			Hashtable h = (Hashtable)paparMinitArahanSeksyenLain.get(0);	
 			context.put("idMinitArahanSeksyenLain",h.get("idMinitArahanSeksyenLain"));
 			context.put("idDokumen",h.get("idDokumen"));
 			context.put("tajuk_Dokumen",h.get("tajuk_Dokumen"));
 			context.put("no_Rujukan_Lain",h.get("no_Rujukan_Lain"));
 			context.put("pengarah",h.get("pengarah"));
 			context.put("penerima",h.get("penerima"));
  		    this.context.put("selectTarafB",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(h.get("idSeksyen").toString()),"disabled"));
 			context.put("tarikh_Minit_Arahan",h.get("tarikh_Arahan"));
 			context.put("catatan",h.get("catatan"));
 			
 			String tarikh_Minit_Arahan = String.valueOf(h.get("tarikh_Arahan"));
			String tajuk_Dokumen = String.valueOf(h.get("tajuk_Dokumen"));
			String no_Rujukan_Dokumen = String.valueOf(h.get("no_Rujukan_Lain"));
		}
			
		else if ("tambahMinitKeluar".equals(action1)){
			
			String id = getParam("idDokumen");
			
			vm = "app/pfd/frmKemaskiniDokumenKeluar.jsp";
			context.put("modeMinit","newMinit");
			context.put("readOnlyMinit","");
			context.put("disabledMinit","");
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("catatan",hb.get("catatan"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
			
				
			view_dokumen(session);
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen();
			Hashtable hD = (Hashtable)paparDokumen.get(0);
					
			context.put("tajuk_Dokumen",hD.get("tajuk_Dokumen"));
			context.put("id_Dokumen",hD.get("id_Dokumen"));
			context.put("no_Rujukan_Dokumen",hD.get("no_Rujukan_Dokumen"));						    
			context.put("id_Minitarahan","");
			context.put("minit_Arahan","");
			context.put("catatan","");
			context.put("selectStatusTindakan",HTML.SelectStatusTindakan("socStatusTindakan"));
			context.put("tarikh_Minit_Arahan",sdf.format(now));
			context.put("selectPegawaiA",HTML.SelectPegawai("socPegawaiA"));
			context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB"));
				
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			context.put("SenaraiMinit",listMinit);

		}	
			
		else if ("tambahPeg2Keluar".equals(action1)){
			
			String id = getParam("idDokumen");
			
			vm = "app/pfd/frmKemaskiniDokumenKeluarArahan.jsp";
			context.put("modeMinit","newMinit");
			context.put("readOnlyMinit","");
			context.put("disabledMinit","");
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("catatan",hb.get("catatan"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
			
				
			view_dokumen(session);
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen();
			Hashtable hD = (Hashtable)paparDokumen.get(0);
					
			context.put("tajuk_Dokumen",hD.get("tajuk_Dokumen"));
			context.put("id_Dokumen",hD.get("id_Dokumen"));
			context.put("no_Rujukan_Dokumen",hD.get("no_Rujukan_Dokumen"));			    
			context.put("id_Minitarahan","");
			context.put("minit_Arahan","");
			context.put("catatan","");
			context.put("selectStatusTindakan",HTML.SelectStatusTindakan("socStatusTindakan"));
			context.put("tarikh_Minit_Arahan",sdf.format(now));
			context.put("selectPegawaiA",HTML.SelectPegawai("socPegawaiA"));
			context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB"));
			context.put("selectPegawaiC",HTML.SelectPegawai("socPegawaiC"));
				
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			context.put("SenaraiMinit",listMinit);

		}		
			
		else if ("tambahPeg3Keluar".equals(action1)){
			
			String id = getParam("idDokumen");
			
			vm = "app/pfd/frmKemaskiniDokumenKeluarArahan.jsp";
			context.put("modeMinit","newMinit");
			context.put("readOnlyMinit","");
			context.put("disabledMinit","");
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("catatan",hb.get("catatan"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
			
				
			view_dokumen(session);
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen();
			Hashtable hD = (Hashtable)paparDokumen.get(0);
					

			context.put("tajuk_Dokumen",hD.get("tajuk_Dokumen"));
			context.put("id_Dokumen",hD.get("id_Dokumen"));
			context.put("no_Rujukan_Dokumen",hD.get("no_Rujukan_Dokumen"));
						    
			context.put("id_Minitarahan","");
			context.put("minit_Arahan","");
			context.put("catatan","");
			context.put("selectStatusTindakan",HTML.SelectStatusTindakan("socStatusTindakan"));
			context.put("tarikh_Minit_Arahan",sdf.format(now));
			context.put("selectPegawaiA",HTML.SelectPegawai("socPegawaiA"));
			context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB"));
			context.put("selectPegawaiC",HTML.SelectPegawai("socPegawaiC"));
			context.put("selectPegawaiD",HTML.SelectPegawai("socPegawaiD"));
				
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			context.put("SenaraiMinit",listMinit);

		}		


		else if ("simpanMinitMasukPeg3".equals(action1)){
			
			vm = "app/pfd/frmKemaskiniDokumenMasukArahan.jsp";
			context.put("modeMinit","paparMinit");
			context.put("action1","tambahPeg3Masuk");
			context.put("readOnlyMinit","readonly");
			context.put("disabledMinit","disabled");
			context.put("emel","notsuccess");
			
			String idMinitArahanSebelum = getParam("idMinitArahanSebelum");
			
			String idMinitarahan = simpanMinit(session);
			String id = getParam("idDokumen");
			
			paparLampiranDokumenMasuk = FrmKemaskiniDokumenData.getListLampiranDokumenMasuk(id);
    		this.context.put("SenaraiListLampiranDokumenMasuk",paparLampiranDokumenMasuk);
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
			
			if(paparFail.isEmpty()){
				context.put("status","");
				context.put("idFail","");
		    	context.put("noFail","");
		    	context.put("tajukFail","");
		    	context.put("keterangan","");
		    	context.put("id_Status","");
		    	context.put("tarikh_Daftar_Fail","");
				
				
			}
			else{
			
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("catatan",hb.get("catatan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
			}
	    	
	    	view_dokumen(session);
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen();
			Hashtable hD = (Hashtable)paparDokumen.get(0);
			context.put("tajuk_Dokumen",hD.get("tajuk_Dokumen"));
			context.put("id_Dokumen",hD.get("id_Dokumen"));
			context.put("no_Rujukan_Dokumen",hD.get("no_Rujukan_Dokumen"));
					
			String user_name = (String)session.getAttribute("_portal_username");
	    	String user_id = (String)session.getAttribute("_ekptg_user_id");
	    	context.put("user_Name", user_name);
	    	context.put("user_Id", user_id);
	    	
			paparMinit = FrmKemaskiniDokumenData.getPaparMinit(idMinitarahan);
			Hashtable hM = (Hashtable)paparMinit.get(0);
			context.put("id_Minitarahan",hM.get("id_Minitarahan"));
			context.put("minit_Arahan",hM.get("minit_Arahan"));
			context.put("catatan",hM.get("catatan"));
			context.put("status_Tindakan",hM.get("id_statusTindakan"));
			context.put("tarikh_Minit_Arahan",hM.get("tarikh_Minit_Arahan"));
			context.put("user_Name", user_name);
    		
    		listPegawai1 = FrmKemaskiniDokumenData.getDataPegawai1(idMinitarahan);
			this.context.put("SenaraiPegawai1",listPegawai1);
			
			listPegawai2 = FrmKemaskiniDokumenData.getDataPegawai2(idMinitarahan);
			this.context.put("SenaraiPegawai2",listPegawai2);
			
			listPegawai3 = FrmKemaskiniDokumenData.getDataPegawai3(idMinitarahan);
			this.context.put("SenaraiPegawai3",listPegawai3);
			
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			context.put("SenaraiMinit",listMinit);
					
			
			String check = checkPegawaiMenerima(session);
			
		    if("".equalsIgnoreCase(check) || "0".equalsIgnoreCase(check)){
		    	 // nothing
		    }
		    else
		    {
		    	updateStatusTugasan(session);
		    }

				
		}
			
		else if("emelMinitArahan".equals(action1)){
			
			vm = "app/pfd/frmKemaskiniDokumenMasukArahan.jsp";
			context.put("modeMinit","paparMinit");
			context.put("action1","tambahPeg3Masuk");
			context.put("readOnlyMinit","readonly");
			context.put("disabledMinit","disabled");
			context.put("emel","success");
			
			String idMinitArahanSebelum = getParam("idMinitArahanSebelum");
			String idMinitarahan = getParam("idMinitarahan");
			String id = getParam("idDokumen");
			
			paparLampiranDokumenMasuk = FrmKemaskiniDokumenData.getListLampiranDokumenMasuk(id);
    		this.context.put("SenaraiListLampiranDokumenMasuk",paparLampiranDokumenMasuk);
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
			
			if(paparFail.isEmpty()){
				context.put("status","");
				context.put("idFail","");
		    	context.put("noFail","");
		    	context.put("tajukFail","");
		    	context.put("keterangan","");
		    	context.put("id_Status","");
		    	context.put("tarikh_Daftar_Fail","");
				
				
			}
			else{
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("catatan",hb.get("catatan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
			}
	    	
	    	view_dokumen(session);
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen();
			Hashtable hD = (Hashtable)paparDokumen.get(0);
			context.put("tajuk_Dokumen",hD.get("tajuk_Dokumen"));
			context.put("id_Dokumen",hD.get("id_Dokumen"));
			context.put("no_Rujukan_Dokumen",hD.get("no_Rujukan_Dokumen"));
					
			String user_name = (String)session.getAttribute("_portal_username");
	    	String user_id = (String)session.getAttribute("_ekptg_user_id");
	    	context.put("user_Name", user_name);
	    	context.put("user_Id", user_id);
	    	
			paparMinit = FrmKemaskiniDokumenData.getPaparMinit(idMinitarahan);
			Hashtable hM = (Hashtable)paparMinit.get(0);
			context.put("id_Minitarahan",hM.get("id_Minitarahan"));
			context.put("minit_Arahan",hM.get("minit_Arahan"));
			context.put("catatan",hM.get("catatan"));
			context.put("status_Tindakan",hM.get("id_statusTindakan"));
			context.put("tarikh_Minit_Arahan",hM.get("tarikh_Minit_Arahan"));
			context.put("user_Name", user_name);
    		
    		listPegawai1 = FrmKemaskiniDokumenData.getDataPegawai1(idMinitarahan);
			this.context.put("SenaraiPegawai1",listPegawai1);
			
			listPegawai2 = FrmKemaskiniDokumenData.getDataPegawai2(idMinitarahan);
			this.context.put("SenaraiPegawai2",listPegawai2);
			
			listPegawai3 = FrmKemaskiniDokumenData.getDataPegawai3(idMinitarahan);
			this.context.put("SenaraiPegawai3",listPegawai3);
			
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			context.put("SenaraiMinit",listMinit);
					
			
			String check = checkPegawaiMenerima(session);
			
		    if("".equalsIgnoreCase(check) || "0".equalsIgnoreCase(check)){
		    	 // nothing
		    }
		    else
		    {
		    	updateStatusTugasan(session);
		    }
		    
		    
 			String tarikh_Minit_Arahan = String.valueOf(hM.get("tarikh_Minit_Arahan"));
			String tajuk_Dokumen = String.valueOf(hD.get("tajuk_Dokumen"));
			String no_Rujukan_Dokumen = String.valueOf(hD.get("no_Rujukan_Dokumen"));
			user_id = (String)session.getAttribute("_ekptg_user_id");
			
			String emelPengirim = getEmelPengirim(user_id);
			
		    
		    String idPenerima1 = getUserId1(idMinitarahan);
		    String idPenerima2 = getUserId2(idMinitarahan);
		    String idPenerima3 = getUserId3(idMinitarahan);
		    		    
		    int emailList = 0;
		    
		    String emailRecipient1 = getEmail1(idPenerima1);
		    String emailRecipient2 = getEmail2(idPenerima2);
		    String emailRecipient3 = getEmail3(idPenerima3);
		    
		    		
		    String[] email_pegawai = {emailRecipient1,emailRecipient2,emailRecipient3};		    
    		
		    XEkptgEmailSender email = XEkptgEmailSender.getInstance();
    		email.FROM =emelPengirim;
    		email.SUBJECT ="Sila semak Minit Arahan bagi dokumen "+no_Rujukan_Dokumen+" untuk tindakan selanjutnya.";
    		email.MESSAGE ="TAJUK : <B>'"+tajuk_Dokumen+"'</B>."+
    					   "<br><br>Tuan/Puan,"+
    					   "<br><br>Permohonan : Dokumen <B>"+no_Rujukan_Dokumen+"</B> telah diterima pada : <B>"+tarikh_Minit_Arahan+"</B> dan telah "+
    					   "sedia untuk semakan dan pengesahan dari pihak tuan/puan."+
    					   "<br><br>Sila <i><b>login</b></i> masuk ke <b>www.etapp.gov.my</b> untuk semakan serta pengesahan dari pihak tuan/puan."+
    					   "<br><br>Sekian,Terima Kasih."+
    					   "<br><b>webmaster@etapp.gov.my</b>"+
    					   "<br><br>Nota : Email ini adalah dijana oleh sistem eTapp dan tidak perlu dibalas.";

    		
    		Vector<String> penerima = new Vector<String>();
    	   int index_email = 0;
 		   for (int i = 0; i < email_pegawai.length; i++) {	
 			   if (isValidEmail(email_pegawai[i])) {
 				   penerima.addElement(email_pegawai[i]);
 				  index_email++;
			   }   
 
 		   }
 		  email.MULTIPLE_RECIEPIENT = new String[penerima.size()];
 		  for(int i = 0;i<penerima.size();i++){
 			 email.MULTIPLE_RECIEPIENT[i]=penerima.elementAt(i);
 		  }
    		   	
    		email.sendEmail();
		}
			
		else if("kemaskiniMinitMasuk".equals(action1)){
			
			vm = "app/pfd/frmKemaskiniDokumenMasukArahan.jsp";
			context.put("modeMinit","kemaskiniMinit");
			context.put("action1","tambahPeg3Masuk");
			context.put("readOnlyMinit","");
			context.put("disabledMinit","");
			context.put("emel","notsuccess");
			
			String idMinitArahanSebelum = getParam("idMinitArahanSebelum");
			
			String idMinitarahan = getParam("idMinitarahan");
			String id = getParam("idDokumen");
			context.put("id_Minitarahan",idMinitarahan);
			context.put("idDokumen",id);
			
			paparLampiranDokumenMasuk = FrmKemaskiniDokumenData.getListLampiranDokumenMasuk(id);
    		this.context.put("SenaraiListLampiranDokumenMasuk",paparLampiranDokumenMasuk);
    		
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("catatan",hb.get("catatan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
	    	
	    	view_dokumen(session);
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen();
			Hashtable hD = (Hashtable)paparDokumen.get(0);
			context.put("tajuk_Dokumen",hD.get("tajuk_Dokumen"));
			context.put("id_Dokumen",hD.get("id_Dokumen"));
			context.put("no_Rujukan_Dokumen",hD.get("no_Rujukan_Dokumen"));
					
			String user_name = (String)session.getAttribute("_portal_username");
	    	String user_id = (String)session.getAttribute("_ekptg_user_id");
	    	context.put("user_Name", user_name);
	    	context.put("user_Id", user_id);
	    	
			paparMinit = FrmKemaskiniDokumenData.getPaparMinit(idMinitarahan);
			Hashtable hM = (Hashtable)paparMinit.get(0);
			context.put("id_Minitarahan",hM.get("id_Minitarahan"));
			context.put("minit_Arahan",hM.get("minit_Arahan"));
			context.put("catatan",hM.get("catatan"));
			context.put("status_Tindakan",hM.get("id_statusTindakan"));
			context.put("tarikh_Minit_Arahan",hM.get("tarikh_Minit_Arahan"));
			context.put("user_Name", user_name);
    					
         	listPegawai1(session);
    		listPegawai1 = FrmKemaskiniDokumenData.getListPegawai1();
    		this.context.put("SenaraiPegawai1",listPegawai1);
    		
       		listPegawai1 = FrmKemaskiniDokumenData.getListPegawaiMinitArahan1(idMinitarahan);
       		if(listPegawai1.size()!=0){
       			Hashtable x = (Hashtable)listPegawai1.elementAt(0);
     			this.context.put("selectidorang1",x.get("user_id"));
    		}  
    					
         	listPegawai2(session);
    		listPegawai2 = FrmKemaskiniDokumenData.getListPegawai2();
    		this.context.put("SenaraiPegawai2",listPegawai2);
    		
       		listPegawai2 = FrmKemaskiniDokumenData.getListPegawaiMinitArahan2(idMinitarahan);
       		if(listPegawai2.size()!=0){
    			Hashtable y = (Hashtable)listPegawai2.elementAt(0);
     			this.context.put("selectidorang2",y.get("user_id"));
    		}   		
			
         	listPegawai3(session);
    		listPegawai3 = FrmKemaskiniDokumenData.getListPegawai3();
    		this.context.put("SenaraiPegawai3",listPegawai3);
    		
       		listPegawai3 = FrmKemaskiniDokumenData.getListPegawaiMinitArahan3(idMinitarahan);
      		if(listPegawai3.size()!=0){
        		Hashtable z = (Hashtable)listPegawai3.elementAt(0);
     			this.context.put("selectidorang3",z.get("user_id"));
    		}   
   
			
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			context.put("SenaraiMinit",listMinit);
					
			
			String check = checkPegawaiMenerima(session);
			
		    if("".equalsIgnoreCase(check) || "0".equalsIgnoreCase(check)){
		    	 // nothing
		    }
		    else
		    {
		    	updateStatusTugasan(session);
		    }
			
		}
			
		else if("updateMinitMasuk".equals(action1))
		{
			
			vm = "app/pfd/frmKemaskiniDokumenMasukArahan.jsp";
			context.put("modeMinit","updateMinit");
			context.put("action1","tambahPeg3Masuk");
			context.put("readOnlyMinit","readonly");
			context.put("disabledMinit","disabled");
			context.put("emel","notsuccess");
			
			updateMinitMasuk(session);
			
			String idMinitarahan = getParam("idMinitarahan");
			String id = getParam("idDokumen");
			
			paparLampiranDokumenMasuk = FrmKemaskiniDokumenData.getListLampiranDokumenMasuk(id);
    		this.context.put("SenaraiListLampiranDokumenMasuk",paparLampiranDokumenMasuk);
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("catatan",hb.get("catatan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
	    	
	    	view_dokumen(session);
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen();
			Hashtable hD = (Hashtable)paparDokumen.get(0);
			context.put("tajuk_Dokumen",hD.get("tajuk_Dokumen"));
			context.put("id_Dokumen",hD.get("id_Dokumen"));
			context.put("no_Rujukan_Dokumen",hD.get("no_Rujukan_Dokumen"));
					
			String user_name = (String)session.getAttribute("_portal_username");
	    	String user_id = (String)session.getAttribute("_ekptg_user_id");
	    	context.put("user_Name", user_name);
	    	context.put("user_Id", user_id);
	    	
			paparMinit = FrmKemaskiniDokumenData.getPaparMinit(idMinitarahan);
			Hashtable hM = (Hashtable)paparMinit.get(0);
			context.put("id_Minitarahan",hM.get("id_Minitarahan"));
			context.put("minit_Arahan",hM.get("minit_Arahan"));
			context.put("catatan",hM.get("catatan"));
			context.put("status_Tindakan",hM.get("id_statusTindakan"));
			context.put("tarikh_Minit_Arahan",hM.get("tarikh_Minit_Arahan"));
			context.put("user_Name", user_name);
    		
    		listPegawai1 = FrmKemaskiniDokumenData.getDataPegawai1(idMinitarahan);
			this.context.put("SenaraiPegawai1",listPegawai1);
			
			listPegawai2 = FrmKemaskiniDokumenData.getDataPegawai2(idMinitarahan);
			this.context.put("SenaraiPegawai2",listPegawai2);
			
			listPegawai3 = FrmKemaskiniDokumenData.getDataPegawai3(idMinitarahan);
			this.context.put("SenaraiPegawai3",listPegawai3);
			
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			context.put("SenaraiMinit",listMinit);
					
			
			String check = checkPegawaiMenerima(session);
			
		    if("".equalsIgnoreCase(check) || "0".equalsIgnoreCase(check)){
		    	 // nothing
		    }
		    else
		    {
		    	updateStatusTugasan(session);
		    }
			
		}

		else if ("paparMinitMasuk".equals(action1)){
				
			vm = "app/pfd/frmKemaskiniDokumenMasukArahan.jsp";
			context.put("modeMinit","paparMinit");
			context.put("action1","tambahPeg3Masuk");
			context.put("readOnlyMinit","readonly");
			context.put("disabledMinit","disabled");
			context.put("emel","notsuccess");

			
			String idMinitArahanSebelum = getParam("idMinitArahanSebelum");
			
			String idMinitarahan = getParam("idMinitarahan");
			String id = getParam("idDokumen");
			
			paparLampiranDokumenMasuk = FrmKemaskiniDokumenData.getListLampiranDokumenMasuk(id);
    		this.context.put("SenaraiListLampiranDokumenMasuk",paparLampiranDokumenMasuk);
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("catatan",hb.get("catatan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
	    	
	    	view_dokumen(session);
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen();
			Hashtable hD = (Hashtable)paparDokumen.get(0);
			context.put("tajuk_Dokumen",hD.get("tajuk_Dokumen"));
			context.put("id_Dokumen",hD.get("id_Dokumen"));
			context.put("no_Rujukan_Dokumen",hD.get("no_Rujukan_Dokumen"));
					
			String user_name = (String)session.getAttribute("_portal_username");
	    	String user_id = (String)session.getAttribute("_ekptg_user_id");
	    	context.put("user_Name", user_name);
	    	context.put("user_Id", user_id);
	    	
			paparMinit = FrmKemaskiniDokumenData.getPaparMinit(idMinitarahan);
			Hashtable hM = (Hashtable)paparMinit.get(0);
			context.put("id_Minitarahan",hM.get("id_Minitarahan"));
			context.put("minit_Arahan",hM.get("minit_Arahan"));
			context.put("catatan",hM.get("catatan"));
			context.put("status_Tindakan",hM.get("id_statusTindakan"));
			context.put("tarikh_Minit_Arahan",hM.get("tarikh_Minit_Arahan"));
			context.put("user_Name", user_name);
    		
    		listPegawai1 = FrmKemaskiniDokumenData.getDataPegawai1(idMinitarahan);
			this.context.put("SenaraiPegawai1",listPegawai1);
			
			listPegawai2 = FrmKemaskiniDokumenData.getDataPegawai2(idMinitarahan);
			this.context.put("SenaraiPegawai2",listPegawai2);
			
			listPegawai3 = FrmKemaskiniDokumenData.getDataPegawai3(idMinitarahan);
			this.context.put("SenaraiPegawai3",listPegawai3);
			
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			context.put("SenaraiMinit",listMinit);
					
			
			String check = checkPegawaiMenerima(session);
			
		    if("".equalsIgnoreCase(check) || "0".equalsIgnoreCase(check)){
		    	 // nothing
		    }
		    else
		    {
		    	updateStatusTugasan(session);
		    }
				
				
		}
			
		else if ("paparMinitKeluar".equals(action1)){
			
			vm = "app/pfd/frmKemaskiniDokumenKeluar.jsp";
			context.put("action1","tambahMinitKeluar");
			context.put("modeMinit","paparMinit");
			context.put("readOnlyMinit","readonly");
			context.put("disabledMinit","disabled");
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("catatan",hb.get("catatan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
	    	
			view_dokumen(session);
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen();
			Hashtable hD = (Hashtable)paparDokumen.get(0);
					
			context.put("tajuk_Dokumen",hD.get("tajuk_Dokumen"));
			context.put("id_Dokumen",hD.get("id_Dokumen"));
			context.put("no_Rujukan_Dokumen",hD.get("no_Rujukan_Dokumen"));
				
			
			view_minitKeluar(session);
			paparMinit = FrmKemaskiniDokumenData.getPaparMinit();
			Hashtable hM = (Hashtable)paparMinit.get(0);
			context.put("id_Minitarahan",hM.get("id_Minitarahan"));
			context.put("minit_Arahan",hM.get("minit_Arahan"));
			context.put("catatan",hM.get("catatan"));
			context.put("selectStatusTindakan",HTML.SelectStatusTindakan("socStatusTindakan",Long.parseLong(hM.get("id_statusTindakan").toString()),"disabled"));
			context.put("tarikh_Minit_Arahan",hM.get("tarikh_Minit_Arahan"));
			context.put("selectPegawaiA",HTML.SelectPegawai("socPegawaiA",Long.parseLong(hM.get("pegawai_Mengarah").toString()),"disabled"));
			context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB",Long.parseLong(hM.get("pegawai_Menerima").toString()),"disabled"));
			
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			context.put("SenaraiMinit",listMinit);
				
				
		}
		
		
		else if ("kemaskiniMinitKeluar".equals(action1)){
			
			vm = "app/pfd/frmKemaskiniDokumenKeluar.jsp";
			context.put("action1","tambahMinitKeluar");
			context.put("modeMinit","kemaskiniMinit");
			context.put("readOnlyMinit","");
			context.put("disabledMinit","");
			
			view_fail(session);
			paparFail = FrmKemaskiniDokumenData.getDataFail();
			Hashtable hb = (Hashtable) paparFail.get(0);
			context.put("status",hb.get("keterangan"));
			context.put("idFail",hb.get("idFail"));
	    	context.put("noFail",hb.get("noFail"));
	    	context.put("tajukFail",hb.get("tajukFail"));
	    	context.put("keterangan",hb.get("keterangan"));
	    	context.put("catatan",hb.get("catatan"));
	    	context.put("id_Status",hb.get("id_Status"));
	    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
			
			view_dokumen(session);
			paparDokumen = FrmKemaskiniDokumenData.getDataDokumen();
			Hashtable hD = (Hashtable)paparDokumen.get(0);
					
			context.put("tajuk_Dokumen",hD.get("tajuk_Dokumen"));
			context.put("id_Dokumen",hD.get("id_Dokumen"));
			context.put("no_Rujukan_Dokumen",hD.get("no_Rujukan_Dokumen"));
				
			view_minitKeluar(session);
			paparMinit = FrmKemaskiniDokumenData.getPaparMinit();
			Hashtable hM = (Hashtable)paparMinit.get(0);
			    
			context.put("id_Minitarahan",hM.get("id_Minitarahan"));
			context.put("minit_Arahan",hM.get("minit_Arahan"));
			context.put("catatan",hM.get("catatan"));
		    context.put("tarikh_Minit_Arahan",hM.get("tarikh_Minit_Arahan"));
			context.put("selectPegawaiA",HTML.SelectPegawai("socPegawaiA",Utils.parseLong(hM.get("pegawai_Mengarah").toString()),""));
			context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB",Utils.parseLong(hM.get("pegawai_Menerima").toString()),""));
			context.put("selectStatusTindakan",HTML.SelectStatusTindakan("socStatusTindakan",Utils.parseLong(hM.get("id_statusTindakan").toString()),""));
			    
			listMinitArahan(session);
			listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
			context.put("SenaraiMinit",listMinit);
				
		}			
			
			
			else if ("updateMinitKeluar".equals(action1)){
					
				updateMinitKeluar(session);
				
				vm = "app/pfd/frmKemaskiniDokumenKeluar.jsp";
				context.put("action1","tambahMinitKeluar");
				context.put("modeMinit","paparMinit");
				context.put("readOnlyMinit","readonly");
				context.put("disabledMinit","disabled");
					
				view_dokumen(session);
				paparDokumen = FrmKemaskiniDokumenData.getDataDokumen();
				Hashtable hD = (Hashtable)paparDokumen.get(0);
				context.put("tajuk_Dokumen",hD.get("tajuk_Dokumen"));
				context.put("id_Dokumen",hD.get("id_Dokumen"));
				context.put("no_Rujukan_Dokumen",hD.get("no_Rujukan_Dokumen"));
					
				view_minitKeluar(session);
			    paparMinit = FrmKemaskiniDokumenData.getPaparMinit();
				Hashtable hM = (Hashtable)paparMinit.get(0);
				    
				context.put("id_Minitarahan",hM.get("id_Minitarahan"));
				context.put("minit_Arahan",hM.get("minit_Arahan"));
				context.put("catatan",hM.get("catatan"));
				context.put("selectStatusTindakan",HTML.SelectStatusTindakan("socStatusTindakan",Long.parseLong(hM.get("id_statusTindakan").toString()),"disabled"));
				context.put("tarikh_Minit_Arahan",hM.get("tarikh_Minit_Arahan"));
				context.put("selectPegawaiA",HTML.SelectPegawai("socPegawaiA",Long.parseLong(hM.get("pegawai_Mengarah").toString()),"disabled"));
				context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB",Long.parseLong(hM.get("pegawai_Menerima").toString()),"disabled"));
				
				listMinitArahan(session);
				listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
				context.put("SenaraiMinit",listMinit);
					
			}
			
			else if ("hapusMinitMasuk".equals(action1)){
					
				hapusMinitMasuk(session);
				vm = "app/pfd/frmKemaskiniDokumenMasuk.jsp";
				context.put("action1","tambahMinitMasuk");
				context.put("modeMinit","newMinit");
				context.put("readOnlyMinit","readonly");
				context.put("disabledMinit","disabled");
					
				view_fail(session);
				paparFail = FrmKemaskiniDokumenData.getDataFail();
				Hashtable hb = (Hashtable) paparFail.get(0);
				context.put("status",hb.get("keterangan"));
				context.put("idFail",hb.get("idFail"));
		    	context.put("noFail",hb.get("noFail"));
		    	context.put("tajukFail",hb.get("tajukFail"));
		    	context.put("keterangan",hb.get("keterangan"));
		    	context.put("catatan",hb.get("catatan"));
		    	context.put("id_Status",hb.get("id_Status"));
		    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
				
					
				view_dokumen(session);
				paparDokumen = FrmKemaskiniDokumenData.getDataDokumen();
				Hashtable hD = (Hashtable)paparDokumen.get(0);
						
				context.put("tajuk_Dokumen",hD.get("tajuk_Dokumen"));
				context.put("id_Dokumen",hD.get("id_Dokumen"));
				context.put("no_Rujukan_Dokumen",hD.get("no_Rujukan_Dokumen"));
							    
				context.put("id_Minitarahan","");
				context.put("minit_Arahan","");
				context.put("catatan","");
				context.put("selectStatusTindakan",HTML.SelectStatusTindakan("socStatusTindakan"));
				context.put("tarikh_Minit_Arahan","");
				context.put("selectPegawaiA",HTML.SelectPegawai("socPegawaiA"));
				context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB"));
					
				listMinitArahan(session);
				listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
				context.put("SenaraiMinit",listMinit);
				}
			
			else if ("hapusMinitKeluar".equals(action1)){
			
				hapusMinitKeluar(session);
				vm = "app/pfd/frmKemaskiniDokumenKeluar.jsp";
				context.put("action1","tambahMinitKeluar");
				context.put("modeMinit","newMinit");
				context.put("readOnlyMinit","readonly");
				context.put("disabledMinit","disabled");
					
				view_fail(session);
				paparFail = FrmKemaskiniDokumenData.getDataFail();
				Hashtable hb = (Hashtable) paparFail.get(0);
				context.put("status",hb.get("keterangan"));
				context.put("idFail",hb.get("idFail"));
		    	context.put("noFail",hb.get("noFail"));
		    	context.put("tajukFail",hb.get("tajukFail"));
		    	context.put("keterangan",hb.get("keterangan"));
		    	context.put("catatan",hb.get("catatan"));
		    	context.put("id_Status",hb.get("id_Status"));
		    	context.put("tarikh_Daftar_Fail",hb.get("tarikh_Daftar_Fail"));
				
					
				view_dokumen(session);
				paparDokumen = FrmKemaskiniDokumenData.getDataDokumen();
				Hashtable hD = (Hashtable)paparDokumen.get(0);
						
				context.put("tajuk_Dokumen",hD.get("tajuk_Dokumen"));
				context.put("id_Dokumen",hD.get("id_Dokumen"));
				context.put("no_Rujukan_Dokumen",hD.get("no_Rujukan_Dokumen"));
							    
				context.put("id_Minitarahan","");
				context.put("minit_Arahan","");
				context.put("catatan","");
				context.put("selectStatusTindakan",HTML.SelectStatusTindakan("socStatusTindakan"));
				context.put("tarikh_Minit_Arahan","");
				context.put("selectPegawaiA",HTML.SelectPegawai("socPegawaiA"));
				context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB"));
					
				listMinitArahan(session);
				listMinit = FrmKemaskiniDokumenData.getListMinitArahan();
				context.put("SenaraiMinit",listMinit);
			}
			else{
				
  	    		String user_name = (String)session.getAttribute("_portal_username");
  	    		String user_id = (String)session.getAttribute("_ekptg_user_id");
  	    		String user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
  	    		String portal_role = (String)session.getAttribute("_portal_role");
  	    		String myrole = (String)session.getAttribute("myrole");
  	    		String carianfail = getParam("carianfail");

  	    		view_Seksyen(session);
	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
	         	String id_seksyen = String.valueOf(hA.get("id_seksyen"));
	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
	         	context.put("idSeksyen",(hA.get("id_seksyen")));
	         	context.put("idNegeri",(hA.get("id_negeri")));
  	    	
  	    		context.put("user_Name", user_name);
  	    		context.put("user_Id", user_id);
  	    		
  	    		if("true".equalsIgnoreCase(carianfail) || "".equalsIgnoreCase(carianfail)){
  	    			context.put("carianfail","true");
  	    		}
  	    		else{
  	    			context.put("carianfail","false");
  	    		}
  	    		
  	    		
  	    	this.context.put("JumlahFail",FrmDaftarDokumenData.totalFail(session));
  	    		
			vm = "app/pfd/frmDaftarDokumen.jsp";
			if (!"".equals(getParam("txtNoFail")))
  	    		noFail = getParam("txtNoFail");
			if (!"".equals(getParam("txtNoFailLama")))
  	    		noFailLama = getParam("txtNoFailLama");
			if (!"".equals(getParam("txtTajukFail")))
				tajukFail = getParam("txtTajukFail");
			if (!"".equals(getParam("socNegeriD")))
				negeri = getParam("socNegeriD");
			if (!"".equals(getParam("socSeksyenD")))
				seksyen = getParam("socSeksyenD");
			if (!"".equals(getParam("socStatusD")))
				status = getParam("socStatusD");
			if (!"".equals(getParam("txdTarikhDaftar")))
				tarikhDaftar = getParam("txdTarikhDaftar");
			if (!"".equals(getParam("txtNoRujukanDok")))
				noRujDok = getParam("txtNoRujukanDok");
			if (!"".equals(getParam("socJenisDokumenD")))
				jnsDok = getParam("socJenisDokumenD");
			if (!"".equals(getParam("txtTajukDokumen")))
				tajukDok = getParam("txtTajukDokumen");
			
			String noRujukanLain = null;
			if (!"".equals(getParam("noRujukanLain")))
				noRujukanLain = getParam("noRujukanLain");
			String noRujukanDokumen = null;
			if (!"".equals(getParam("noRujukanDokumen")))
				noRujukanDokumen = getParam("noRujukanDokumen");
			String tajukDokumen = null;
			if (!"".equals(getParam("tajukDokumen")))
				tajukDokumen = getParam("tajukDokumen");
			String tarikhDaftarDokumen = null;
			if (!"".equals(getParam("tarikhDaftarDokumen")))
				tarikhDaftarDokumen = getParam("tarikhDaftarDokumen");
			
			String idLogDokumen = getParam("idLogDokumen")==null?"":getParam("idLogDokumen");
			context.put("idLogDokumen", idLogDokumen);
			
	    	FrmDaftarDokumenData.setCarianFail(noFail,noFailLama,tajukFail,negeri,seksyen,status,tarikhDaftar,user_id, myrole, user_negeri, id_seksyen, noRujukanLain, noRujukanDokumen, tajukDokumen, tarikhDaftarDokumen);
	    	listFail = FrmDaftarDokumenData.getList();
	    	this.context.put("SenaraiFail", listFail);
	    	this.context.put("selectNegeriD",HTML.SelectNegeri("socNegeriD",Utils.parseLong(negeri),""));
	    	this.context.put("selectSeksyenD",HTML.SelectSeksyen("socSeksyenD",Utils.parseLong(seksyen),""));
	    	this.context.put("selectStatusD",HTML.SelectStatusFail("socStatusD",Utils.parseLong(status),""));
	    	this.context.put("selectJenisDokumenD",HTML.SelectJenisDokumen("socJenisDokumenD",Utils.parseLong(jnsDok),""));
	    	this.context.put("txtNoFail", noFail);
	    	this.context.put("txtNoFailLama", noFailLama);
	    	this.context.put("txtTajukFail", tajukFail);
	    	this.context.put("txdTarikhDaftar", tarikhDaftar);
	    	this.context.put("txtNoRujukanDok", noRujDok);
	    	this.context.put("txtTajukDokumen",tajukDok);
	    	
	    	this.context.put("noRujukanLain", noRujukanLain);
	    	this.context.put("noRujukanDokumen",noRujukanDokumen);
	    	this.context.put("tajukDokumen", tajukDokumen);

	    	
	    	setupPage(session,action1,listFail);
		}
            return vm;
		   
			
    }

	private String getUserIdSeksyenLain(String idMinitArahanSeksyenLain) {
		// TODO Auto-generated method stub
		return FrmKemaskiniDokumenData.getUserIdSeksyenLain(idMinitArahanSeksyenLain);
	}


	private String getEmelPengirim(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return FrmKemaskiniDokumenData.getEmelPengirim(user_id);
	}


	private void uploadLampiran(String idDokumen) throws Exception {
		uploadFiles2(idDokumen);
	}


	 private void uploadFiles2(String idDokumen) throws Exception {
		    DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload upload = new ServletFileUpload(factory);

		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		      FileItem item = (FileItem)itr.next();
		      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    	  saveData1(item, idDokumen);
		      }
		    }
		  }


	private String getIdPengarah(String idDokumen) throws Exception {
		// TODO Auto-generated method stub
		return FrmKemaskiniDokumenData.getIdPengarah(idDokumen);
	}
	
	private String getIdPA(String idDokumen) throws Exception {
		// TODO Auto-generated method stub
		return FrmKemaskiniDokumenData.getIdPA(idDokumen);
	}


	private String getUserId1(String idMinitarahan) throws Exception {
		// TODO Auto-generated method stub
		return FrmKemaskiniDokumenData.getUserId1(idMinitarahan);
	}
	
	private String getUserId2(String idMinitarahan) throws Exception {
		// TODO Auto-generated method stub
		return FrmKemaskiniDokumenData.getUserId2(idMinitarahan);
	}
	
	private String getUserId3(String idMinitarahan) throws Exception {
		// TODO Auto-generated method stub
		return FrmKemaskiniDokumenData.getUserId3(idMinitarahan);
	}


	private String getEmail1(String idPenerima1) throws Exception {
		
		return FrmKemaskiniDokumenData.getEmailPenerima1(idPenerima1);
	}

	private String getEmail2(String idPenerima2) throws Exception {
		
		return FrmKemaskiniDokumenData.getEmailPenerima2(idPenerima2);
	}
	
	private String getEmail3(String idPenerima3) throws Exception {
		
		return FrmKemaskiniDokumenData.getEmailPenerima3(idPenerima3);
	}

	private String checkPegawaiMenerima(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idDokumen = getParam("idDokumen");
		return FrmKemaskiniDokumenData.checkPegawaiMenerima(user_id,idDokumen);
	}

	private void updateStatusTugasan(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idDokumen = getParam("idDokumen");
		String idMinitArahanSebelum = getParam("idMinitArahanSebelum");
		FrmKemaskiniDokumenData.updateStatusTugasan(user_id,idDokumen,idMinitArahanSebelum);
		
	}


	private void listPegawaiTinggiDokumenMasuk(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
		FrmKemaskiniDokumenData.setListPegawaiAtas(user_id,user_negeri);
		
	}


	private void listMinitArahanPengarah(HttpSession session) throws Exception {
		String id = getParam("idDokumen");
		FrmKemaskiniDokumenData.setDataMinitArahanPengarah(id);
		
	}


	private void listMinitArahanPegawai1(HttpSession session) throws Exception {
		String id = getParam("idDokumen");
		FrmKemaskiniDokumenData.setDataMinitArahanPegawai1(id);
		
	}
	
	private void listMinitArahanPegawai2(HttpSession session) throws Exception {
		String id = getParam("idDokumen");
		FrmKemaskiniDokumenData.setDataMinitArahanPegawai2(id);
		
	}
	
	private void listMinitArahanPegawai3(HttpSession session) throws Exception {
		String id = getParam("idDokumen");
		FrmKemaskiniDokumenData.setDataMinitArahanPegawai3(id);
		
	}
	
	private void listMinitArahanPegawai4(HttpSession session) throws Exception {
		String id = getParam("idDokumen");
		FrmKemaskiniDokumenData.setDataMinitArahanPegawai4(id);
		
	}
	
	private void listMinitArahanSelesai(HttpSession session) throws Exception {
		String id = getParam("idDokumen");
		FrmKemaskiniDokumenData.setDataMinitArahanSelesai(id);
		
	}
	
	private void listMinitArahanSeksyenLain(HttpSession session) throws Exception {
		String id = getParam("idDokumen");
		FrmKemaskiniDokumenData.setDataMinitArahanSeksyenLain(id);
		
	}


	private void view_Seksyen(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmLogDokumenData.setSeksyen(user_id);
		
	}


	private void view_LevelArahan(HttpSession session) throws Exception {
		String id = getParam("idDokumen");
    	System.out.println("idFail"+id);
	    FrmKemaskiniDokumenData.setDataCountLevelArahan(id);
		
	}


	private void listDokumenFailSubjaket(HttpSession session) throws Exception {
		String id = getParam("idFailSubjaket");
	    FrmKemaskiniDokumenData.setListDokumenFailSubjaket(id);
		
	}



	private String simpanSeksyenLain(HttpSession session) throws Exception {
		
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idDokumen = getParam("idDokumen");
		String no_Rujukan_Dokumen = getParam("no_Rujukan_Lain");
		String tajuk_Dokumen = getParam("tajuk_Dokumen");
		String pegawaiMengarah = getParam("user_Id");
		String idSeksyen = getParam("socSeksyenPAR");	
		String pegawaiMenerima = getParam("socPAR");
		String catatan = getParam("txtCatatan");
	    String tarikhArahan = getParam("txdTarikhMinitArahan");
			
	    Hashtable h = new Hashtable();

			h.put("id_statusdokumen","88");
		    h.put("idDokumen", idDokumen);
		    h.put("no_Rujukan_Dokumen", no_Rujukan_Dokumen);
		    h.put("tajuk_Dokumen", tajuk_Dokumen);
		    h.put("pegawaiMengarah",pegawaiMengarah);
		    h.put("pegawaiMenerima", pegawaiMenerima);
		    h.put("idSeksyen",idSeksyen);
		    h.put("catatan",catatan);
		    h.put("tarikhArahan",tarikhArahan);
		    h.put("idMasuk",user_id);
		    h.put("level",getParam("levelArahan"));

		    return FrmKemaskiniDokumenData.addMinitSeksyenLain(h);
	}



	private String simpanDokumenSubjaket(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String no_Rujukan_Dokumen = getParam("txtNoRujDokumen");
		String tajuk_Dokumen = getParam("tajuk_Dokumen");
		String no_Rujukan_DokumenEncode = java.net.URLDecoder.decode(no_Rujukan_Dokumen);	
		String bil_Minit_Dokumen = getParam("txtBilMinitFail");
	    String new_no_Rujukan_Dokumen = no_Rujukan_DokumenEncode+"("+bil_Minit_Dokumen+")";
		String idFailSubjaket = getParam("idFailSubjaket");	
	    
	    Hashtable h = new Hashtable();

			h.put("flag_Dokumen","4");
		    h.put("bil_Minit_Dokumen", bil_Minit_Dokumen);
		    h.put("id_Jenisdokumen", "5");
		    h.put("no_Rujukan_Dokumen", new_no_Rujukan_Dokumen);
		    h.put("idFailSubjaket", idFailSubjaket);
		    h.put("tajuk_Dokumen",tajuk_Dokumen);
		    h.put("tarikh_Dokumen_Masuk", getParam("txtTkhDokumenMasuk"));
		    h.put("id_Fail",getParam("idFail"));
		    h.put("id_Masuk",user_id);

		    return FrmKemaskiniDokumenData.addSubjaket(h);
	}



	private void listPegawai1(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String user_negeri = (String)session.getAttribute("_ekptg_user_negeri");
		FrmKemaskiniDokumenData.setListPegawai1(user_id,user_negeri);
		
	}

	private void listPegawai2(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String user_negeri = (String)session.getAttribute("_ekptg_user_negeri");
		FrmKemaskiniDokumenData.setListPegawai2(user_id,user_negeri);
		
	}
	private void listPegawai3(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String user_negeri = (String)session.getAttribute("_ekptg_user_negeri");
		FrmKemaskiniDokumenData.setListPegawai3(user_id,user_negeri);
		
	}


	private void listPA(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmKemaskiniDokumenData.setListPA(user_id);
		
	}

	private void listPTFail(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmKemaskiniDokumenData.setListPTFail(user_id);
			
	}

	private void listPTFail2(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmKemaskiniDokumenData.setListPTFail2(user_id);
			
	}

	private void listLampiranLogDokumen(HttpSession session) throws Exception {
		String idLogDokumen = getParam("idLogDokumen");
		FrmLogDokumenData.setLampiranLogDokumen(idLogDokumen);
		
	}
	
	private void listLampiranDokumen(HttpSession session) throws Exception {
		String idDokumen = getParam("idDokumen");
		FrmKemaskiniDokumenData.setLampiranDokumen(idDokumen);
		
	}



	private void listPegawai(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmKemaskiniDokumenData.setListPegawai(user_id,(String) session.getAttribute("_ekptg_user_negeri"));
		
	}



	private void listPegawaiTinggi(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmLogDokumenData.setListPegawai(user_id, (String) session.getAttribute("_ekptg_user_negeri"));
		
	}


	private void listLogDokumenByUserId(HttpSession session) throws Exception {
		 String user_id = (String)session.getAttribute("_ekptg_user_id");
		 FrmSenaraiTugasanPFD.setListPergerakanPemohonFail(user_id);
		
	}


	private void downloadDraf(String idDokumen) throws Exception {
		uploadFiles1(idDokumen);
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
	
	public void setupPage1(HttpSession session,String action1,Vector listDokumen) {
		try {
		
		this.context.put("totalRecords",listDokumen.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
		}
	    
	    if ("getNext".equals(action1)) {
	    	page++;
	    } else if ("getPrevious".equals(action1)) {
	    	page--;
	    } else if ("getPage".equals(action1)) {
	    	page = getParamAsInteger("value");
	    } else if ("doChangeItemPerPage".equals(action1)) {
	       itemsPerPage = getParamAsInteger("itemsPerPage");
	    }
	    	
	    Paging paging = new Paging(session,listDokumen,itemsPerPage);
		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("SenaraiDokumen",paging.getPage(page));
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


	private void view_CountDokumen(HttpSession session) throws Exception {
		String id = getParam("idFail");
	    FrmKemaskiniDokumenData.setDataCountDokumen(id);
		
	}


	
	private void view_NoDokumen(HttpSession session) throws Exception {
		String id = "";
		if(!"".equals(getParam("idFail"))){
			id = getParam("idFail");
		}
		else{
			id = getParam("id_Fail");
		}
	    FrmKemaskiniDokumenData.setDataNoDokumen(id);
		
	}
	
	private void view_DokumenFail(HttpSession session) throws Exception {
		String id = "";
		
		if(!"".equals(getParam("idFail"))){
			id = getParam("idFail");
		}
		else{
			id = getParam("id_Fail");
		}
		
	    FrmKemaskiniDokumenData.setDataDokumenFail(id);
	}
	
	private void hapus_Dokumen(HttpSession session) throws Exception {
    	String idDokumen = getParam("idDokumen");
	    FrmKemaskiniDokumenData.hapus(idDokumen);
	   
	}
	private void hapusLampiran(HttpSession session) throws Exception {
    	String id = getParam("idLampiran");
	    FrmKemaskiniDokumenData.hapusLampiran(id);
	   
	}
	private void view_fail(HttpSession session) throws Exception {
    	
		String idFail = "";
		if(!"".equals(getParam("idFail"))){
			idFail = getParam("idFail");
		}
		else{
			idFail = getParam("id_Fail");
		}
		
		
	    FrmKemaskiniDokumenData.setDataFail(idFail);
	   
	}
	private void view_dokumen(HttpSession session) throws Exception {
		
		String id = getParam("idDokumen");
	    FrmKemaskiniDokumenData.setDataDokumen(id);
	   
	}
	private void view_dokumenMasuk(HttpSession session) throws Exception {
	
		String id = getParam("idDokumen");
	    FrmKemaskiniDokumenData.setDataDokumen(id);
	   
	}
	private void view_dokumenKeluar(HttpSession session) throws Exception {
		
		String id = getParam("idDokumen");
	    FrmKemaskiniDokumenData.setDataDokumen(id);
	   
	}
	private void view_minitMasuk(HttpSession session) throws Exception {
		
		String id = getParam("idMinitarahan");
	    FrmKemaskiniDokumenData.setDataMinit(id);
	   
	}
	private void view_minitKeluar(HttpSession session) throws Exception {
		
		String id = getParam("idMinitarahan");
	    FrmKemaskiniDokumenData.setDataMinit(id);
	   
	}
	private void listDokumen(HttpSession session) throws Exception {
		String idFail = "";
		if(!"".equals(getParam("idFail"))){
			idFail = getParam("idFail");
		}
		else{
			idFail = getParam("id_Fail");
		}
	    FrmKemaskiniDokumenData.setListDokumen(idFail);
	   
	  }
	private void listMinitArahan(HttpSession session) throws Exception {
		String id = getParam("idDokumen");
		FrmKemaskiniDokumenData.setListMinitArahan(id);
	   
	}
	private String simpanDokumenMasuk(HttpSession session) throws Exception {
		
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idLogDokumen = getParam("idLogDokumen");
		String no_Rujukan_Dokumen = getParam("no_Rujukan_Dokumen");
		String no_Rujukan_DokumenEncode = java.net.URLDecoder.decode(no_Rujukan_Dokumen);
		
		String bil_Minit_Dokumen = getParam("bil_Minit_Dokumen");
	    String new_no_Rujukan_Dokumen = no_Rujukan_DokumenEncode+"("+bil_Minit_Dokumen+")";
		String minit = getParam("c1");
		String laporan = getParam("c2");
		String cd = getParam("c3");
		String id_PA = getParam("socPA");
		
			Hashtable h = new Hashtable();  
			h.put("flag_Dokumen","1");
		    h.put("bil_Minit_Dokumen", getParam("bil_Minit_Dokumen"));
		    h.put("id_Jenisdokumen", getParam("socJenisDokumen"));
		    h.put("no_Rujukan_Dokumen", new_no_Rujukan_Dokumen);
		    h.put("tarikh_Dokumen_Diterima", getParam("tarikh_Dokumen_Diterima"));
		    h.put("tarikh_Dokumen", getParam("tarikh_Dokumen"));
		    h.put("no_Rujukan_Lain", getParam("no_Rujukan_Lain"));
		    h.put("tajuk_Dokumen",getParam("tajuk_Dokumen"));
			h.put("namaPengirim", getParam("pengirim_Dokumen"));
			h.put("id_nama_penerima",getParam("socPegawai") );
			h.put("id_PA", id_PA);
			h.put("idLogDokumen", idLogDokumen);
		    h.put("id_Fail",getParam("idFail"));
		    h.put("tag_dokumen", getParam("tag_dokumen"));
		    h.put("id_Masuk",user_id);
	 	    if ("true".equalsIgnoreCase(minit)){
				h.put("idMinit", "1");
			}
			else{
				h.put("idMinit", "0");
			}
			if ("true".equalsIgnoreCase(laporan)){
				h.put("idLaporan", "1");
			}
			else{
				h.put("idLaporan", "0");
			}
			if ("true".equalsIgnoreCase(cd)){
				h.put("idCD", "1");
			}
			else{
				h.put("idCD", "0");
			}
		    
		    return FrmKemaskiniDokumenData.addMasuk(h);
	
	  }
	
	private String simpanDokumenKeluar(HttpSession session) throws Exception {
		
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		
			Hashtable h = new Hashtable();
			String no_Rujukan_Dokumen = getParam("no_Rujukan_Dokumen");
			String no_Rujukan_Lain = getParam("no_Rujukan_Lain");
			String no_Rujukan_DokumenEncode = java.net.URLDecoder.decode(no_Rujukan_Dokumen);
			
			String bil_Minit_Dokumen = getParam("bil_Minit_Dokumen");
		    String new_no_Rujukan_Dokumen = no_Rujukan_DokumenEncode+"("+bil_Minit_Dokumen+")";
			String minit = getParam("c1");
			String laporan = getParam("c2");
			String cd = getParam("c3");
			
			h.put("flag_Dokumen", "2");
		    h.put("bil_Minit_Dokumen", getParam("bil_Minit_Dokumen"));
		    h.put("id_Jenisdokumen", getParam("socJenisDokumen"));
		    h.put("no_Rujukan_Dokumen", new_no_Rujukan_Dokumen);
		    h.put("no_Rujukan_Lain", no_Rujukan_Lain);
		    h.put("tajuk_Dokumen",getParam("tajuk_Dokumen"));
		    h.put("tarikh_Dokumen", getParam("tarikh_Dokumen"));
			h.put("namaPenerima", getParam("penerima_Dokumen"));
			h.put("id_nama_pengirim", getParam("socPegawai"));
			h.put("tag_dokumen", getParam("tag_dokumen"));
			h.put("id_PAR", getParam("socPT"));
			if ("true".equalsIgnoreCase(minit)){
				h.put("idMinit", "1");
			}
			else{
				h.put("idMinit", "0");
			}
			if ("true".equalsIgnoreCase(laporan)){
				h.put("idLaporan", "1");
			}
			else{
				h.put("idLaporan", "0");
			}
			if ("true".equalsIgnoreCase(cd)){
				h.put("idCD", "1");
			}
			else{
				h.put("idCD", "0");
			}
		    h.put("id_Fail",getParam("idFail"));
		    h.put("id_Masuk",user_id);
		    return FrmKemaskiniDokumenData.addKeluar(h);
	
	  }
	
	private String simpanDokumenDalaman(HttpSession session) throws Exception {
		
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		
			Hashtable h = new Hashtable();
			String no_Rujukan_Dokumen = getParam("no_Rujukan_Dokumen");
			String no_Rujukan_Lain = getParam("no_Rujukan_Lain");
			String no_Rujukan_DokumenEncode = java.net.URLDecoder.decode(no_Rujukan_Dokumen);
			
			String bil_Minit_Dokumen = getParam("bil_Minit_Dokumen");
		    String new_no_Rujukan_Dokumen = no_Rujukan_DokumenEncode+"("+bil_Minit_Dokumen+")";
			String minit = getParam("c1");
			String laporan = getParam("c2");
			String cd = getParam("c3");
			
			h.put("flag_Dokumen", "5");
		    h.put("bil_Minit_Dokumen", getParam("bil_Minit_Dokumen"));
		    h.put("id_Jenisdokumen", getParam("socJenisDokumen"));
		    h.put("no_Rujukan_Dokumen", new_no_Rujukan_Dokumen);
		    h.put("no_Rujukan_Lain", no_Rujukan_Lain);
		    h.put("tajuk_Dokumen",getParam("tajuk_Dokumen"));
		    h.put("tarikh_Dokumen", getParam("tarikh_Dokumen"));
			h.put("namaPenerima", getParam("penerima_Dokumen"));
			h.put("id_nama_pengirim", getParam("socPegawai"));
			h.put("tag_dokumen", getParam("tag_dokumen"));
			h.put("id_PAR", getParam("socPT"));
			if ("true".equalsIgnoreCase(minit)){
				h.put("idMinit", "1");
			}
			else{
				h.put("idMinit", "0");
			}
			if ("true".equalsIgnoreCase(laporan)){
				h.put("idLaporan", "1");
			}
			else{
				h.put("idLaporan", "0");
			}
			if ("true".equalsIgnoreCase(cd)){
				h.put("idCD", "1");
			}
			else{
				h.put("idCD", "0");
			}
		    h.put("id_Fail",getParam("idFail"));
		    h.put("id_Masuk",user_id);
		    return FrmKemaskiniDokumenData.addDalaman(h);
	
	  }
	private void updateDokumenMasuk(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idDokumen = getParam("idDokumen");
		String no_Rujukan_Dokumen = getParam("no_Rujukan_Dokumen");
		String no_Rujukan_DokumenEncode = java.net.URLDecoder.decode(no_Rujukan_Dokumen);
		
		String bil_Minit_Dokumen = getParam("bil_Minit_Dokumen");
	    String new_no_Rujukan_Dokumen = no_Rujukan_DokumenEncode;
		String minit = getParam("c1");
		String laporan = getParam("c2");
		String cd = getParam("c3");
		String id_PA = getParam("socPA");
		
			Hashtable h = new Hashtable(); 
			h.put("id_Dokumen",idDokumen);
			h.put("flag_Dokumen","1");
		    h.put("bil_Minit_Dokumen", getParam("bil_Minit_Dokumen"));
		    h.put("id_Jenisdokumen", getParam("socJenisDokumen"));
		    h.put("no_Rujukan_Dokumen", new_no_Rujukan_Dokumen);
		    h.put("tarikh_Dokumen_Diterima", getParam("tarikh_Dokumen_Diterima"));
		    h.put("tarikh_Dokumen", getParam("tarikh_Dokumen"));
		    h.put("no_Rujukan_Lain", getParam("no_Rujukan_Lain"));
		    h.put("tajuk_Dokumen",getParam("tajuk_Dokumen"));
			h.put("namaPengirim", getParam("pengirim_Dokumen"));
			h.put("id_nama_penerima",getParam("socPegawai") );
			h.put("tag_dokumen",getParam("tag_dokumen"));
			h.put("id_tagdokumen",getParam("id_tagdokumen"));
			h.put("id_PA", id_PA);
		    h.put("id_Fail",getParam("idFail"));
		    h.put("id_Kemaskini",user_id);
	 	    if (!"".equalsIgnoreCase(minit)){
				h.put("idMinit", "1");
			}
			else{
				h.put("idMinit", "0");
			}
			if (!"".equalsIgnoreCase(laporan)){
				h.put("idLaporan", "1");
			}
			else{
				h.put("idLaporan", "0");
			}
			if (!"".equalsIgnoreCase(cd)){
				h.put("idCD", "1");
			}
			else{
				h.put("idCD", "0");
			}
	    FrmKemaskiniDokumenData.updateMasuk(h);
	
	  }
	
	private void updateDokumenKeluar(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idDokumen = getParam("idDokumen");
			Hashtable h = new Hashtable();
			String no_Rujukan_Dokumen = getParam("no_Rujukan_Dokumen");
			String no_Rujukan_Lain = getParam("no_Rujukan_Lain");
			String no_Rujukan_DokumenEncode = java.net.URLDecoder.decode(no_Rujukan_Dokumen);
			String tajuk_Dokumen = getParam("tajuk_Dokumen");
			String bil_Minit_Dokumen = getParam("bil_Minit_Dokumen");
		    String new_no_Rujukan_Dokumen = no_Rujukan_DokumenEncode;
			String minit = getParam("c1");
			String laporan = getParam("c2");
			String cd = getParam("c3");
			String idPAR =getParam("socPT");
			
			h.put("id_Dokumen",idDokumen);
			h.put("flag_Dokumen", "2");
		    h.put("bil_Minit_Dokumen", getParam("bil_Minit_Dokumen"));
		    h.put("id_Jenisdokumen", getParam("socJenisDokumen"));
		    h.put("no_Rujukan_Dokumen", new_no_Rujukan_Dokumen);
		    h.put("no_Rujukan_Lain", no_Rujukan_Lain);
		    h.put("tajuk_Dokumen",getParam("tajuk_Dokumen"));
		    h.put("tag_dokumen", getParam("tag_dokumen"));
		    h.put("id_tagdokumen", getParam("id_tagdokumen"));
		    h.put("tarikh_Dokumen", getParam("tarikh_Dokumen"));
			h.put("namaPenerima", getParam("penerima_Dokumen"));
			h.put("id_nama_pengirim", getParam("socPegawai"));
			h.put("id_PAR", idPAR);
			if ("1".equalsIgnoreCase(minit)){
				h.put("idMinit", "1");
			}
			else{
				h.put("idMinit", "0");
			}
			if ("1".equalsIgnoreCase(laporan)){
				h.put("idLaporan", "1");
			}
			else{
				h.put("idLaporan", "0");
			}
			if ("1".equalsIgnoreCase(cd)){
				h.put("idCD", "1");
			}
			else{
				h.put("idCD", "0");
			}
		    h.put("id_Fail",getParam("idFail"));
		    h.put("id_Kemaskini",user_id);
		    FrmKemaskiniDokumenData.updateKeluar(h);
	
	  }
	private void updateDokumenDalaman(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idDokumen = getParam("idDokumen");
			Hashtable h = new Hashtable();
			String no_Rujukan_Dokumen = getParam("no_Rujukan_Dokumen");
			String no_Rujukan_Lain = getParam("no_Rujukan_Lain");
			String no_Rujukan_DokumenEncode = java.net.URLDecoder.decode(no_Rujukan_Dokumen);
			String tajuk_Dokumen = getParam("tajuk_Dokumen");
			String bil_Minit_Dokumen = getParam("bil_Minit_Dokumen");
		    String new_no_Rujukan_Dokumen = no_Rujukan_DokumenEncode;
			String minit = getParam("c1");
			String laporan = getParam("c2");
			String cd = getParam("c3");
			String idPAR =getParam("socPT");
			
			h.put("id_Dokumen",idDokumen);
			h.put("flag_Dokumen", "5");
		    h.put("bil_Minit_Dokumen", getParam("bil_Minit_Dokumen"));
		    h.put("id_Jenisdokumen", getParam("socJenisDokumen"));
		    h.put("no_Rujukan_Dokumen", new_no_Rujukan_Dokumen);
		    h.put("no_Rujukan_Lain", no_Rujukan_Lain);
		    h.put("tajuk_Dokumen",getParam("tajuk_Dokumen"));
		    h.put("tag_dokumen", getParam("tag_dokumen"));
		    h.put("id_tagdokumen", getParam("id_tagdokumen"));
		    h.put("tarikh_Dokumen", getParam("tarikh_Dokumen"));
			h.put("namaPenerima", getParam("penerima_Dokumen"));
			h.put("id_nama_pengirim", getParam("socPegawai"));
			h.put("id_PAR", idPAR);
			if ("1".equalsIgnoreCase(minit)){
				h.put("idMinit", "1");
			}
			else{
				h.put("idMinit", "0");
			}
			if ("1".equalsIgnoreCase(laporan)){
				h.put("idLaporan", "1");
			}
			else{
				h.put("idLaporan", "0");
			}
			if ("1".equalsIgnoreCase(cd)){
				h.put("idCD", "1");
			}
			else{
				h.put("idCD", "0");
			}
		    h.put("id_Fail",getParam("idFail"));
		    h.put("id_Kemaskini",user_id);
		    FrmKemaskiniDokumenData.updateDalam(h);
	
	  }
	private void list_Lampiran(HttpSession session) throws Exception {
    	String id = getParam("idDokumen");
    	FrmKemaskiniDokumenData.setListLampiran(id);
	   
	  }
	 private void uploadFiles() throws Exception {
		    DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload upload = new ServletFileUpload(factory);

		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		      FileItem item = (FileItem)itr.next();
		      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    	  saveData(item);
		      }
		    }
		  }
	 
	 private void uploadFiles1(String idDokumen) throws Exception {
		    DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload upload = new ServletFileUpload(factory);

		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		      FileItem item = (FileItem)itr.next();
		      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    	  saveData1(item, idDokumen);
		      }
		    }
		  }
	 
	 
	 private void saveData(FileItem item) throws Exception {
	  		Db db = null;
   	    HttpSession session = this.request.getSession();
   	    Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String currentDate = "to_date('" +  sdf.format(now) + "','dd/MM/yyyy')";
	  		String id = getParam("idDokumen");
			String user_id = (String)session.getAttribute("_ekptg_user_id");
	        try {
	        	db = new Db();
	        	long id_Lampiran = DB.getNextID("TBLPFDRUJLAMPIRAN_SEQ");
	        	Connection con = db.getConnection();
	        	con.setAutoCommit(false);
	        	PreparedStatement ps = con.prepareStatement("insert into TBLPFDRUJLAMPIRAN " +
	        			"(id_Lampiran,id_Dokumen,nama_Fail,jenis_Mime,content) " +
	        			"values(?,?,?,?,?)");
	        	ps.setLong(1, id_Lampiran);
	        	ps.setString(2, id);
	        	ps.setString(3,item.getName());
	        	ps.setString(4,item.getContentType());
	        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
	        	ps.executeUpdate();
	            con.commit();
		    } finally {
			      if (db != null) db.close();
		    }
	  }
	 
	 private void saveData1(FileItem item, String IDDokumen) throws Exception {
	  		Db db = null;
	    HttpSession session = this.request.getSession();
	    Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String currentDate = "to_date('" +  sdf.format(now) + "','dd/MM/yyyy')";
	  		String id = IDDokumen;
			String user_id = (String)session.getAttribute("_ekptg_user_id");
	        try {
	        	db = new Db();
	        	long id_Lampiran = DB.getNextID("TBLPFDRUJLAMPIRAN_SEQ");
	        	Connection con = db.getConnection();
	        	con.setAutoCommit(false);
	        	PreparedStatement ps = con.prepareStatement("insert into TBLPFDRUJLAMPIRAN " +
	        			"(id_Lampiran,id_Dokumen,nama_Fail,jenis_Mime,content) " +
	        			"values(?,?,?,?,?)");
	        	ps.setLong(1, id_Lampiran);
	        	ps.setString(2, id);
	        	ps.setString(3,item.getName());
	        	ps.setString(4,item.getContentType());
	        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
	        	ps.executeUpdate();
	            con.commit();
		    } finally {
			      if (db != null) db.close();
		    }
	  }
	 
		private String simpanMinit(HttpSession session) throws Exception {
			
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			String user_negeri = (String)session.getAttribute("_ekptg_user_negeri");
			 
			Hashtable h = new Hashtable();
			 
			 h.put("id_Dokumen",getParam("idDokumen"));
			 h.put("idMinitArahanSebelum",getParam("idMinitArahanSebelum"));
			 h.put("minit_Arahan",getParam("txtMinitArahan"));
			 h.put("catatan",getParam("txtCatatan"));
			 h.put("level",getParam("levelArahan"));
			 h.put("idSeksyen",getParam("idSeksyen"));
			 h.put("idNegeri",getParam("idNegeri"));
			 h.put("id_Pegawai_Ygmengarah", user_id);
			 h.put("id_Pegawai_Ygmenerima1", getParam("socPegawai1"));
			 h.put("id_Pegawai_Ygmenerima2", getParam("socPegawai2"));
			 h.put("id_Pegawai_Ygmenerima3", getParam("socPegawai3"));
			 h.put("id_status_Tindakan",getParam("socStatusTindakan"));
			 h.put("tarikh_Minit_Arahan",getParam("txdTarikhMinitArahan"));
			 h.put("idMasuk",user_id);
			 
			 return FrmKemaskiniDokumenData.addMinit(h);

			
		}
	

		private void updateMinitMasuk(HttpSession session) throws Exception {		
			
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			String user_negeri = (String)session.getAttribute("_ekptg_user_negeri");
			String id = getParam("idMinitarahan"); 
			
			Hashtable h = new Hashtable();
			 h.put("id_Minitarahan", getParam("idMinitarahan"));
			 h.put("minit_Arahan",getParam("txtMinitArahan"));
			 h.put("catatan",getParam("txtCatatan"));
			 h.put("id_Pegawai_Ygmengarah", user_id);
			 h.put("id_Pegawai_Ygmenerima1", getParam("socPegawai1"));
			 h.put("id_Pegawai_Ygmenerima2", getParam("socPegawai2"));
			 h.put("id_Pegawai_Ygmenerima3", getParam("socPegawai3"));
			 h.put("id_status_Tindakan",getParam("socStatusTindakan"));
			 h.put("tarikh_Minit_Arahan",getParam("txdTarikhMinitArahan"));
			 h.put("idMasuk",user_id);
			 
			 FrmKemaskiniDokumenData.updateMinit(h);

			
		}
		
		private void updateMinitKeluar(HttpSession session) throws Exception {
			
			String idMinitarahan = getParam("id_Minitarahan");
			
			 Hashtable h = new Hashtable();
			 
			 h.put("id_Minitarahan", getParam("idMinitarahan"));
			 h.put("id_Dokumen",getParam("idDokumen"));
			 h.put("minit_Arahan",getParam("txtMinitArahan"));
			 h.put("catatan",getParam("txtCatatan"));
			 h.put("id_Pegawai_Ygmengarah", getParam("socPegawaiA"));
			 h.put("id_Pegawai_Ygmenerima", getParam("socPegawaiB"));
			 h.put("status_Tindakan",getParam("socStatusTindakan"));
			 h.put("tarikh_Minit_Arahan",getParam("txdTarikhMinitArahan"));
			 
			 FrmKemaskiniDokumenData.updateMinit(h);

			
		}
		
		private void hapusMinitKeluar(HttpSession session) throws Exception {
		   	String id = getParam("idMinitarahan");
		    FrmKemaskiniDokumenData.hapusMinit(id);
			
		}


		private void hapusMinitMasuk(HttpSession session) throws Exception {
		   	String id = getParam("idMinitArahan");
		    FrmKemaskiniDokumenData.hapusMinit(id);
			
		}
		
		public boolean isValidEmail(String email) {
			if (email == null) {
				email = "";
			}
			Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
			Matcher m = p.matcher(email);
			boolean matchFound = m.matches();

			if(matchFound){
				return true;
			}else{
				return false;
			}
		}
		
	
}
