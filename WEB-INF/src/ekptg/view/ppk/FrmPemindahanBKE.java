package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Paging;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnPemindahanBKEData;
import ekptg.model.ppk.FrmPrmhnnPemindahanSek17BKEData;
//import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8Data;

public class FrmPemindahanBKE extends AjaxBasedModule{
	
	static Logger myLogger = Logger.getLogger(FrmTukarStatus.class);
	//FrmPrmhnnSek8Data logic = new FrmPrmhnnSek8Data();
	FrmPrmhnnPemindahanSek17BKEData logic2 = null;
	FrmHeaderPpk mainheader = null;

	public String doTemplate2() throws Exception {
		logic2 = new FrmPrmhnnPemindahanSek17BKEData();
		HttpSession session = this.request.getSession();
		String vm = "app/ppk/frmSenaraiFailPemindahanBKE.jsp";
		String submit = "";
		mainheader = new FrmHeaderPpk();

		if (getParam("command")!=""){
			submit = getParam("command");
		}else{
			submit = getParam("hitButt");
		}
		String action = getParam("action");
		String getvalue = getParam("getvalue");
		myLogger.debug("action --->"+action);
		String mode = getParam("mode");
		String readability1 = "";
		String readability2 = "";
		String disability1 = "";
		String disability2 = "";
		this.context.put("Util", new lebah.util.Util());
		this.context.put("checkbx1", "");
		this.context.put("checkbx2", "");
		this.context.put("checkbx3", "");
		this.context.put("checkbx4", "");
		this.context.put("checkbx5", "");
		this.context.put("checkbx6", "");
		this.context.put("checkbx7", "");
		this.context.put("alasan1", "");
		this.context.put("alasan2", "");
		this.context.put("alasan3", "");
		this.context.put("alasan4", "");
		this.context.put("alasan5", "");
		this.context.put("alasan6", "");
		this.context.put("alasan7", "");
		//int userid = Integer.parseInt((String)session.getAttribute("_ekptg_user_id"));
		String userid = (String) session.getAttribute("_ekptg_user_id");
		Vector listpaparan = new Vector();
		Vector list1 = new Vector();
		Vector listUserDetail = new Vector();
		myLogger.debug("View --->>>FrmPemindahanBKE");
		myLogger.debug("submit--->"+submit);
		myLogger.debug("mode--->"+mode);

		if("papar".equals(submit)){

			String idPermohonan = getParam("idpermohonan");
			this.context.put("idPermohonan", idPermohonan);

			Vector ListNegeriDaerah = FrmPrmhnnPemindahanBKEData.getNegeriDaerahMohon(idPermohonan);
			this.context.put("listNegeriDaerah",ListNegeriDaerah);

			Vector ListNegeri = FrmPrmhnnPemindahanBKEData.getListnegeribyhta(idPermohonan);
			this.context.put("listNegeri", ListNegeri);

			int ListNegericnt = FrmPrmhnnPemindahanBKEData.getListnegeribyhtacount(idPermohonan);
			this.context.put("listNegericnt", ListNegericnt);

			Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
			this.context.put("View", list);
			
			headerppk_baru(session,idPermohonan,"Y","","T");
			
			//check dulu klu data ada atau tidak
            int cntdata = FrmPrmhnnPemindahanBKEData.chkBkeData(idPermohonan);

			if (cntdata == 0){
				this.context.put("idnegeri", "");
				this.context.put("iddaerah", "");
				this.context.put("display_info", "");
				this.context.put("daerahmode", "disabled");
				this.context.put("btnKemaskini", "");
				this.context.put("btnSimpan", "yes");
				this.context.put("btnCetak", "");
				this.context.put("btnBatal", "yes");
				this.context.put("readmode", "");
				this.context.put("checkbx1", "");
				this.context.put("checkbx2", "");
				this.context.put("checkbx3", "");
				this.context.put("checkbx4", "");
				this.context.put("checkbx5", "");
				this.context.put("checkbx6", "");
				this.context.put("checkbx7", "");
				this.context.put("alasan1", "");
				this.context.put("alasan2", "");
				this.context.put("alasan3", "");
				this.context.put("alasan4", "");
				this.context.put("alasan5", "");
				this.context.put("alasan6", "");
				this.context.put("alasan7", "");
				this.context.put("alasanlain", "");
				this.context.put("tarikhmohon", "");
				this.context.put("keputusanpegawai", "");
			}else{
				Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
				this.context.put("listBkeData", listBkeData);

				Vector ListDaerahbyNegeri = FrmPrmhnnPemindahanBKEData.getDaerahbyNegeri(idPermohonan);
				this.context.put("listDaerah", ListDaerahbyNegeri);
				this.context.put("display_info", "yes");
				this.context.put("daerahmode", "disabled");
				this.context.put("btnKemaskini", "yes");
				this.context.put("btnSimpan", "");
				this.context.put("btnCetak", "yes");
				this.context.put("readmode", "disabled");
				this.context.put("btnBatal", "");
			}

			this.context.put("btnKembali", "yes"); // kembali ke list
			this.context.put("selectedTabatas",0);
            this.context.put("selectedTabtengah",0);
            this.context.put("selectedTabbawah",0);
            this.context.put("selectedTabtepi",0);

			vm = "app/ppk/frmPemindahanBKE.jsp";
		}
		else if("Cari".equals(submit)){
			listUserDetail = FrmPrmhnnPemindahanBKEData.getUserDetail((String) session.getAttribute("_ekptg_user_id"));
			Hashtable a = (Hashtable) listUserDetail.get(0);
			int idnegeri = Integer.parseInt(a.get("idnegeri").toString());
			//int iddaerah = Integer.parseInt(a.get("iddaerah").toString());
			
			String iddaerah = getParam("iddaerah");

			FrmPrmhnnPemindahanBKEData.setCarianFail(getParam("txtNoFail"),getParam("txtPemohon"),getParam("txtSimati"),getParam("txtIcSimati"),getParam("jenisIc"),idnegeri);
			list1 = FrmPrmhnnPemindahanBKEData.getList();
			Hashtable c1 = (Hashtable) list1.get(0);
			if (c1.get("bil")=="")c1.put("bil",0);
			int bil = Integer.parseInt(c1.get("bil").toString());
			int countList1 = list1.size();
			this.context.put("Senaraifail1",list1);
			this.context.put("CountList1",bil);
			this.context.put("carix",2);
			initTextFieldCarian(getParam("txtNoFail"),getParam("txtPemohon"),getParam("txtSimati"),getParam("txtIcSimati"),getParam("jenisIc"));

			setupPage1(session,action,list1);

			vm = "app/ppk/frmSenaraiFailPemindahanBKE.jsp";
		}
		else if("kembali".equals(submit)){
			vm = "app/ppk/frmSenaraiFailPemindahanBKE.jsp";
		}
		else if("Permohonan".equals(submit)){

			String idPermohonan = getParam("idPermohonan");
			this.context.put("idPermohonan", getParam("idPermohonan"));

			Vector ListNegeri = FrmPrmhnnPemindahanBKEData.getListnegeribyhta(idPermohonan);
			this.context.put("listNegeri", ListNegeri);

			if ("Permohonanview".equals(mode)){

				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				this.context.put("selectedTabatas",getParam("tabIdatas"));
	            this.context.put("selectedTabtengah",getParam("tabIdtengah"));
	            this.context.put("selectedTabbawah",getParam("tabIdbawah"));
	            this.context.put("selectedTabtepi",getParam("tabIdtepi"));

				Vector ListNegeriDaerah = FrmPrmhnnPemindahanBKEData.getNegeriDaerahMohon(idPermohonan);
				this.context.put("listNegeriDaerah",ListNegeriDaerah);

				//check dulu klu data ada atau tidak
	            int cntdata = FrmPrmhnnPemindahanBKEData.chkBkeData(idPermohonan);
				if (cntdata == 0){
					this.context.put("idnegeri", "");
					this.context.put("iddaerah", "");
					this.context.put("display_info", "");
					this.context.put("daerahmode", "disabled");
					this.context.put("btnKemaskini", "");
					this.context.put("btnSimpan", "yes");
					this.context.put("btnCetak", "");
					this.context.put("btnBatal", "");
					this.context.put("checkbx1", "");
					this.context.put("checkbx2", "");
					this.context.put("checkbx3", "");
					this.context.put("checkbx4", "");
					this.context.put("checkbx5", "");
					this.context.put("checkbx6", "");
					this.context.put("checkbx7", "");
					this.context.put("alasan1", "");
					this.context.put("alasan2", "");
					this.context.put("alasan3", "");
					this.context.put("alasan4", "");
					this.context.put("alasan5", "");
					this.context.put("alasan6", "");
					this.context.put("alasan7", "");
					this.context.put("alasanlain", "");
					this.context.put("tarikhmohon", "");
				}else{
					Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
					this.context.put("listBkeData", listBkeData);

					Vector ListDaerahbyNegeri = FrmPrmhnnPemindahanBKEData.getDaerahbyNegeri(idPermohonan);
					this.context.put("listDaerah", ListDaerahbyNegeri);
					this.context.put("display_info", "yes");
					this.context.put("daerahmode", "disabled");
					this.context.put("btnKemaskini", "yes");
					this.context.put("btnSimpan", "");
					this.context.put("btnCetak", "yes");
					this.context.put("readmode", "disabled");
					this.context.put("btnBatal", "");
				}

				this.context.put("btnKembali", "yes");

			}
			else if ("Permohonandaerah".equals(mode)){

				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				this.context.put("daerahmode", "");
				this.context.put("btnKemaskini", "");
				this.context.put("btnSimpan", "yes");
				this.context.put("display_info", "");
				this.context.put("btnCetak", "");
				this.context.put("btnBatal", "yes");
				this.context.put("btnKembali", "yes");

				this.context.put("idnegeri",getParam("socNegeri"));
				this.context.put("iddaerah","");
				if (getParam("alasanpindah").equals("100")){
					this.context.put("alasanlain1", "100");
				}
				if (getParam("alasanpindah").equals("101")){
					this.context.put("alasanlain2", "101");
				}
				if (getParam("alasanpindah").equals("102")){
					this.context.put("alasanlain3", "102");
				}
				if (getParam("alasanpindah").equals("103")){
					this.context.put("alasanlain4", "103");
				}
				if (getParam("alasanpindah").equals("104")){
					this.context.put("alasanlain5", "104");
				}
				if (getParam("alasanpindah").equals("105")){
					this.context.put("alasanlain6", "105");
				}
				if (getParam("alasanpindah").equals("106")){
					this.context.put("alasanlain7", "106");
				}
				this.context.put("alasanlain", getParam("txtAlasanLain"));
				this.context.put("tarikhmohon", getParam("txdTarikhSurat"));

				this.context.put("selectedTabatas",getParam("tabIdatas"));
	            this.context.put("selectedTabtengah",getParam("tabIdtengah"));
	            this.context.put("selectedTabbawah",getParam("tabIdbawah"));
	            this.context.put("selectedTabtepi",getParam("tabIdtepi"));

				Vector ListNegeriDaerah = FrmPrmhnnPemindahanBKEData.getNegeriDaerahMohon(idPermohonan);
				this.context.put("listNegeriDaerah",ListNegeriDaerah);

				int idnegeri = Integer.parseInt(getParam("socNegeri"));

				Vector listnegeribydaerah = FrmPrmhnnPemindahanBKEData.getListDaerahbyNegeri(idnegeri,idPermohonan);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
			}
			else if ("Permohonansimpan".equals(mode)){

				this.context.put("selectedTabatas",getParam("tabIdatas"));
	            this.context.put("selectedTabtengah",getParam("tabIdtengah"));
	            this.context.put("selectedTabbawah",getParam("tabIdbawah"));
	            this.context.put("selectedTabtepi",getParam("tabIdtepi"));

	            //check dulu klu data ada atau tidak
	            int cntdata1 = FrmPrmhnnPemindahanBKEData.chkBkeData(idPermohonan);
	            if (cntdata1 == 0){ // insert data baru

	            	insertPermohonanBaru(session);

	            	String[] cbAlasan = this.request.getParameterValues("alasanpindah");
	            	String idmasuk = (String)session.getAttribute("_ekptg_user_id");
	            	String alasanlain = "";

	            	if(cbAlasan!=null){
	            		for (int i = 0; i < cbAlasan.length; i++) {
	            			if (cbAlasan[i].equals("106")){
	            				alasanlain = getParam("txtAlasanLain");
	            			}
	            			FrmPrmhnnPemindahanBKEData.insertPermohonanBke(cbAlasan[i], String.valueOf(idPermohonan), String.valueOf(alasanlain), String.valueOf(idmasuk));
	            		}
	            	}

	            }else{// update data permohonan

	            	updatePermohonanBaru(session);

	            	//delete data
	            	FrmPrmhnnPemindahanBKEData.deletePermohonanBke(getParam("idPermohonan"),(String)session.getAttribute("_ekptg_user_id"));

	            	String[] cbAlasan = this.request.getParameterValues("alasanpindah");
	            	String idmasuk = (String)session.getAttribute("_ekptg_user_id");
	            	String alasanlain = "";

	            	if(cbAlasan!=null){
	            		for (int i = 0; i < cbAlasan.length; i++) {
	            			if (cbAlasan[i].equals("106")){
	            				alasanlain = getParam("txtAlasanLain");
	            			}
	            			FrmPrmhnnPemindahanBKEData.insertPermohonanBke(cbAlasan[i], String.valueOf(idPermohonan), String.valueOf(alasanlain), String.valueOf(idmasuk));
	            		}
	            	}
	            }

	            Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				Vector ListNegeriDaerah = FrmPrmhnnPemindahanBKEData.getNegeriDaerahMohon(idPermohonan);
				this.context.put("listNegeriDaerah",ListNegeriDaerah);

				Vector ListDaerahbyNegeri = FrmPrmhnnPemindahanBKEData.getDaerahbyNegeri(idPermohonan);
				this.context.put("listDaerah", ListDaerahbyNegeri);

				Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
				this.context.put("listBkeData", listBkeData);

				this.context.put("readmode", "disabled");
				this.context.put("display_info", "yes");
				this.context.put("daerahmode", "disabled");
				this.context.put("btnKemaskini", "yes");
				this.context.put("btnSimpan", "");
				this.context.put("btnCetak", "no");
				this.context.put("btnBatal", "");
				this.context.put("btnKembali", "yes"); // kembali ke list
			}
			else if ("Permohonankemaskini".equals(mode)){

				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				this.context.put("selectedTabatas",getParam("tabIdatas"));
	            this.context.put("selectedTabtengah",getParam("tabIdtengah"));
	            this.context.put("selectedTabbawah",getParam("tabIdbawah"));
	            this.context.put("selectedTabtepi",getParam("tabIdtepi"));

	            ListNegeri = FrmPrmhnnPemindahanBKEData.getListnegeribyhta(idPermohonan);
				this.context.put("listNegeri", ListNegeri);

	            Vector ListNegeriDaerah = FrmPrmhnnPemindahanBKEData.getNegeriDaerahMohon(idPermohonan);
				this.context.put("listNegeriDaerah",ListNegeriDaerah);

				Vector ListDaerahbyNegeri = FrmPrmhnnPemindahanBKEData.getDaerahbyNegerihta(idPermohonan);
				this.context.put("listDaerah", ListDaerahbyNegeri);

				Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
				this.context.put("listBkeData", listBkeData);
				Hashtable a = (Hashtable) listBkeData.get(0);
				int idnegeri = Integer.parseInt(a.get("idnegeri").toString());

				Vector listnegeribydaerah = FrmPrmhnnPemindahanBKEData.getListDaerahbyNegerihta(idnegeri,idPermohonan);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);

				this.context.put("readmode", "");
				this.context.put("daerahmode", "");
				this.context.put("btnKemaskini", "");
				this.context.put("btnSimpan", "yes");
				this.context.put("btnCetak", "no");
				this.context.put("btnBatal", "yes");
				this.context.put("btnKembali", "yes"); // kembali ke list
			}
			vm = "app/ppk/frmPemindahanBKE.jsp";
		}
		else if("Keputusan".equals(submit)){

			this.context.put("selectedTabatas",getParam("tabIdatas"));
            this.context.put("selectedTabtengah",getParam("tabIdtengah"));
            this.context.put("selectedTabbawah",getParam("tabIdbawah"));
            this.context.put("selectedTabtepi",getParam("tabIdtepi"));

			String idPermohonan = getParam("idPermohonan");
			this.context.put("idPermohonan", getParam("idPermohonan"));

			Vector ListPegawai = FrmPrmhnnPemindahanBKEData.getNamaPegawai(Integer.parseInt((String)session.getAttribute("_ekptg_user_id")));
			this.context.put("listPegawai", ListPegawai);

			if ("Keputusanview".equals(mode)){

				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				int cntdatakeputusan = FrmPrmhnnPemindahanBKEData.cntKeputusan(idPermohonan);
				if (cntdatakeputusan == 0){
					this.context.put("readmode", "");
					this.context.put("btnKemaskini", "");
					this.context.put("btnSimpan", "yes");
					this.context.put("btnCetak", "");
					this.context.put("btnBatal", "yes");
					this.context.put("btnKembali", "");
					this.context.put("checkeda", "");
					this.context.put("checkedb", "");
					this.context.put("checkedc", "");
					this.context.put("checked1", "");
					this.context.put("checked2", "");
					this.context.put("catatan", "");
				}else{
					this.context.put("readmode", "disabled");
					this.context.put("btnKemaskini", "yes");
					this.context.put("btnSimpan", "");
					this.context.put("btnCetak", "yes");
					this.context.put("btnBatal", "");
					this.context.put("btnKembali", "yes");

					Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
					this.context.put("listBkeData", listBkeData);
				}

			}
			else if ("Keputusansimpan".equals(mode)){
				this.context.put("readmode", "disabled");
				this.context.put("btnKemaskini", "yes");
				this.context.put("btnSimpan", "");
				this.context.put("btnCetak", "yes");
				this.context.put("btnBatal", "");
				this.context.put("btnKembali", "yes");

				Vector dblcheck = FrmPrmhnnPemindahanBKEData.cntDataPersamaan(idPermohonan,getParam("sorkeputusan"));
				Hashtable b = (Hashtable) dblcheck.get(0);
				int cntpersamaan = Integer.parseInt(b.get("cntpersamaan").toString());
				int insertFail = 0;

				if (cntpersamaan == 0){
					insertFail = 1; //masukkan data dlm statusfail
				}else{
					insertFail = 2; //tak masukkan data dlm statusfail
				}

				updateBkeInfo(session,insertFail);

				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
				this.context.put("listBkeData", listBkeData);
			}
			else if ("Keputusankemaskini".equals(mode)){
				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				this.context.put("readmode", "");
				this.context.put("btnKemaskini", "");
				this.context.put("btnSimpan", "yes");
				this.context.put("btnCetak", "");
				this.context.put("btnBatal", "yes");
				this.context.put("btnKembali", "");

				Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
				this.context.put("listBkeData", listBkeData);
			}
			else if ("Keputusanbatal".equals(mode)){
				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				this.context.put("readmode", "disabled");
				this.context.put("btnKemaskini", "yes");
				this.context.put("btnSimpan", "");
				this.context.put("btnCetak", "");
				this.context.put("btnBatal", "");
				this.context.put("btnKembali", "yes");

				Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
				this.context.put("listBkeData", listBkeData);
			}
			vm = "app/ppk/frmPemindahanBKEKeputusan.jsp";
		}
		else if("Kptg".equals(submit)){

			this.context.put("selectedTabatas",getParam("tabIdatas"));
            this.context.put("selectedTabtengah",getParam("tabIdtengah"));
            this.context.put("selectedTabbawah",getParam("tabIdbawah"));
            this.context.put("selectedTabtepi",getParam("tabIdtepi"));

			String idPermohonan = getParam("idPermohonan");
			this.context.put("idPermohonan", getParam("idPermohonan"));

			if ("Kptgview".equals(mode)){
				Vector resultKptgData = FrmPrmhnnPemindahanBKEData.cntKeputusanKptg(idPermohonan);
	            Hashtable b = (Hashtable) resultKptgData.get(0);
				int cntkptg = Integer.parseInt(b.get("cntkptg").toString());
				if (cntkptg == 0){
					this.context.put("releaseData", "0");
					this.context.put("tarikhLulus", "");
					this.context.put("checked1", "");
					this.context.put("checked2", "");
					this.context.put("readmode", "");
					this.context.put("btnKemaskini", "");
					this.context.put("btnSimpan", "yes");
					this.context.put("btnCetak", "");
					this.context.put("btnBatal", "");
					this.context.put("catatan", "");
					this.context.put("btnKembali", "");
				}else{
					this.context.put("releaseData", "1");
					this.context.put("readmode", "disabled");
					this.context.put("btnKemaskini", "yes");
					this.context.put("btnSimpan", "");
					this.context.put("btnCetak", "");
					this.context.put("btnBatal", "");
					this.context.put("btnKembali", "yes");

					Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
					this.context.put("listBkeData", listBkeData);
				}
			}
			else if ("Kptgsimpan".equals(mode)){

				this.context.put("releaseData", "1");
				this.context.put("tarikhLulus", "");
				this.context.put("checked1", "");
				this.context.put("checked2", "");
				this.context.put("readmode", "disabled");
				this.context.put("btnKemaskini", "yes");
				this.context.put("btnSimpan", "");
				this.context.put("btnCetak", "");
				this.context.put("btnBatal", "");
				this.context.put("catatan", "");
				this.context.put("btnKembali", "yes");

				Vector dblcheck = FrmPrmhnnPemindahanBKEData.cntDataPersamaan(idPermohonan,getParam("sorkeputusan"));
				Hashtable b = (Hashtable) dblcheck.get(0);
				int cntpersamaan = Integer.parseInt(b.get("cntpersamaan").toString());
				int insertFail = 0;

				if (cntpersamaan == 0){
					insertFail = 1; //masukkan data dlm statusfail
				}else{
					insertFail = 2; //tak masukkan data dlm statusfail
				}
				updateKptg(session,insertFail);

				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
				this.context.put("listBkeData", listBkeData);
			}
			else if ("Kptgkemaskini".equals(mode)){
				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				this.context.put("releaseData", "1");
				this.context.put("readmode", "");
				this.context.put("btnKemaskini", "");
				this.context.put("btnSimpan", "yes");
				this.context.put("btnCetak", "");
				this.context.put("btnBatal", "yes");
				this.context.put("btnKembali", "");

				Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
				this.context.put("listBkeData", listBkeData);
			}
			else if ("Kptgbatal".equals(mode)){
				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				this.context.put("releaseData", "1");
				this.context.put("readmode", "disabled");
				this.context.put("btnKemaskini", "yes");
				this.context.put("btnSimpan", "");
				this.context.put("btnCetak", "");
				this.context.put("btnBatal", "");
				this.context.put("btnKembali", "yes");

				Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
				this.context.put("listBkeData", listBkeData);
			}

			vm = "app/ppk/frmPemindahanBKEKeputusanKptg.jsp";
		}
		else if("papar17".equals(submit)){

			String idPermohonan = getParam("idpermohonan");
			this.context.put("idPermohonan", idPermohonan);

			Vector ListNegeriDaerah = FrmPrmhnnPemindahanBKEData.getNegeriDaerahMohon(idPermohonan);
			this.context.put("listNegeriDaerah",ListNegeriDaerah);

			Vector ListNegeri = FrmPrmhnnPemindahanBKEData.getListnegeribyhta(idPermohonan);
			this.context.put("listNegeri", ListNegeri);

			int ListNegericnt = FrmPrmhnnPemindahanBKEData.getListnegeribyhtacount(idPermohonan);
			this.context.put("listNegericnt", ListNegericnt);

			Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
			this.context.put("View", list);
			
			headerppk_baru(session,idPermohonan,"Y","","T");

			//check dulu klu data ada atau tidak
            int cntdata = FrmPrmhnnPemindahanBKEData.chkBkeData(idPermohonan);

			if (cntdata == 0){
				this.context.put("idnegeri", "");
				this.context.put("iddaerah", "");
				this.context.put("display_info", "");
				this.context.put("daerahmode", "disabled");
				this.context.put("btnKemaskini", "");
				this.context.put("btnSimpan", "yes");
				this.context.put("btnCetak", "");
				this.context.put("btnBatal", "yes");
				this.context.put("readmode", "");
				this.context.put("checkbx1", "");
				this.context.put("checkbx2", "");
				this.context.put("checkbx3", "");
				this.context.put("checkbx4", "");
				this.context.put("checkbx5", "");
				this.context.put("checkbx6", "");
				this.context.put("checkbx7", "");
				this.context.put("alasan1", "");
				this.context.put("alasan2", "");
				this.context.put("alasan3", "");
				this.context.put("alasan4", "");
				this.context.put("alasan5", "");
				this.context.put("alasan6", "");
				this.context.put("alasan7", "");
				this.context.put("alasanlain", "");
				this.context.put("tarikhmohon", "");
				this.context.put("keputusanpegawai", "");
			}else{
				Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
				this.context.put("listBkeData", listBkeData);

				Vector ListDaerahbyNegeri = FrmPrmhnnPemindahanBKEData.getDaerahbyNegeri(idPermohonan);
				this.context.put("listDaerah", ListDaerahbyNegeri);
				this.context.put("display_info", "yes");
				this.context.put("daerahmode", "disabled");
				this.context.put("btnKemaskini", "yes");
				this.context.put("btnSimpan", "");
				this.context.put("btnCetak", "yes");
				this.context.put("readmode", "disabled");
				this.context.put("btnBatal", "");
			}

			this.context.put("btnKembali", "yes"); // kembali ke list

			this.context.put("selectedTabatas",0);
            this.context.put("selectedTabtengah",0);
            this.context.put("selectedTabbawah",0);
            this.context.put("selectedTabtepi",0);

			vm = "app/ppk/frmPemindahanSek17BKE.jsp";
		}
		else if("Permohonan17".equals(submit)){

			String idPermohonan = getParam("idPermohonan");
			this.context.put("idPermohonan", getParam("idPermohonan"));

			Vector ListNegeri = FrmPrmhnnPemindahanBKEData.getListnegeribyhta(idPermohonan);
			this.context.put("listNegeri", ListNegeri);

			if ("Permohonanview".equals(mode)){

				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				this.context.put("selectedTabatas",getParam("tabIdatas"));
	            this.context.put("selectedTabtengah",getParam("tabIdtengah"));
	            this.context.put("selectedTabbawah",getParam("tabIdbawah"));
	            this.context.put("selectedTabtepi",getParam("tabIdtepi"));

				Vector ListNegeriDaerah = FrmPrmhnnPemindahanBKEData.getNegeriDaerahMohon(idPermohonan);
				this.context.put("listNegeriDaerah",ListNegeriDaerah);

				//check dulu klu data ada atau tidak
	            int cntdata = FrmPrmhnnPemindahanBKEData.chkBkeData(idPermohonan);
				if (cntdata == 0){
					this.context.put("idnegeri", "");
					this.context.put("iddaerah", "");
					this.context.put("display_info", "");
					this.context.put("daerahmode", "disabled");
					this.context.put("btnKemaskini", "");
					this.context.put("btnSimpan", "yes");
					this.context.put("btnCetak", "");
					this.context.put("btnBatal", "");
					this.context.put("checkbx1", "");
					this.context.put("checkbx2", "");
					this.context.put("checkbx3", "");
					this.context.put("checkbx4", "");
					this.context.put("checkbx5", "");
					this.context.put("checkbx6", "");
					this.context.put("checkbx7", "");
					this.context.put("alasan1", "");
					this.context.put("alasan2", "");
					this.context.put("alasan3", "");
					this.context.put("alasan4", "");
					this.context.put("alasan5", "");
					this.context.put("alasan6", "");
					this.context.put("alasan7", "");
					this.context.put("alasanlain", "");
					this.context.put("tarikhmohon", "");
				}else{
					Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
					this.context.put("listBkeData", listBkeData);

					Vector ListDaerahbyNegeri = FrmPrmhnnPemindahanBKEData.getDaerahbyNegeri(idPermohonan);
					this.context.put("listDaerah", ListDaerahbyNegeri);
					this.context.put("display_info", "yes");
					this.context.put("daerahmode", "disabled");
					this.context.put("btnKemaskini", "yes");
					this.context.put("btnSimpan", "");
					this.context.put("btnCetak", "yes");
					this.context.put("readmode", "disabled");
					this.context.put("btnBatal", "");
				}

				this.context.put("btnKembali", "yes");

			}
			else if ("Permohonandaerah".equals(mode)){

				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				this.context.put("daerahmode", "");
				this.context.put("btnKemaskini", "");
				this.context.put("btnSimpan", "yes");
				this.context.put("display_info", "");
				this.context.put("btnCetak", "");
				this.context.put("btnBatal", "yes");
				this.context.put("btnKembali", "yes");

				this.context.put("idnegeri",getParam("socNegeri"));
				this.context.put("iddaerah","");
				if (getParam("alasanpindah").equals("100")){
					this.context.put("alasanlain1", "100");
				}
				if (getParam("alasanpindah").equals("101")){
					this.context.put("alasanlain2", "101");
				}
				if (getParam("alasanpindah").equals("102")){
					this.context.put("alasanlain3", "102");
				}
				if (getParam("alasanpindah").equals("103")){
					this.context.put("alasanlain4", "103");
				}
				if (getParam("alasanpindah").equals("104")){
					this.context.put("alasanlain5", "104");
				}
				if (getParam("alasanpindah").equals("105")){
					this.context.put("alasanlain6", "105");
				}
				if (getParam("alasanpindah").equals("106")){
					this.context.put("alasanlain7", "106");
				}
				this.context.put("alasanlain", getParam("txtAlasanLain"));
				this.context.put("tarikhmohon", getParam("txdTarikhSurat"));

				this.context.put("selectedTabatas",getParam("tabIdatas"));
	            this.context.put("selectedTabtengah",getParam("tabIdtengah"));
	            this.context.put("selectedTabbawah",getParam("tabIdbawah"));
	            this.context.put("selectedTabtepi",getParam("tabIdtepi"));

				Vector ListNegeriDaerah = FrmPrmhnnPemindahanBKEData.getNegeriDaerahMohon(idPermohonan);
				this.context.put("listNegeriDaerah",ListNegeriDaerah);

				int idnegeri = Integer.parseInt(getParam("socNegeri"));

				Vector listnegeribydaerah = FrmPrmhnnPemindahanBKEData.getListDaerahbyNegeri(idnegeri,idPermohonan);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
			}
			else if ("Permohonansimpan".equals(mode)){

				this.context.put("selectedTabatas",getParam("tabIdatas"));
	            this.context.put("selectedTabtengah",getParam("tabIdtengah"));
	            this.context.put("selectedTabbawah",getParam("tabIdbawah"));
	            this.context.put("selectedTabtepi",getParam("tabIdtepi"));

	            //check dulu klu data ada atau tidak
	            int cntdata1 = FrmPrmhnnPemindahanBKEData.chkBkeData(idPermohonan);
	            if (cntdata1 == 0){ // insert data baru

	            	insertPermohonanBaru17(session);

	            	String[] cbAlasan = this.request.getParameterValues("alasanpindah");
	            	String idmasuk = (String)session.getAttribute("_ekptg_user_id");
	            	String alasanlain = "";

	            	if(cbAlasan!=null){
	            		for (int i = 0; i < cbAlasan.length; i++) {
	            			if (cbAlasan[i].equals("106")){
	            				alasanlain = getParam("txtAlasanLain");
	            			}
	            			FrmPrmhnnPemindahanBKEData.insertPermohonanBke(cbAlasan[i], String.valueOf(idPermohonan), String.valueOf(alasanlain), String.valueOf(idmasuk));
	            		}
	            	}

	            }else{// update data permohonan

	            	updatePermohonanBaru(session);

	            	//delete data
	            	FrmPrmhnnPemindahanBKEData.deletePermohonanBke(getParam("idPermohonan"),(String)session.getAttribute("_ekptg_user_id"));

	            	String[] cbAlasan = this.request.getParameterValues("alasanpindah");
	            	String idmasuk = (String)session.getAttribute("_ekptg_user_id");
	            	String alasanlain = "";

	            	if(cbAlasan!=null){
	            		for (int i = 0; i < cbAlasan.length; i++) {
	            			if (cbAlasan[i].equals("106")){
	            				alasanlain = getParam("txtAlasanLain");
	            			}
	            			FrmPrmhnnPemindahanBKEData.insertPermohonanBke(cbAlasan[i], String.valueOf(idPermohonan), String.valueOf(alasanlain), String.valueOf(idmasuk));
	            		}
	            	}
	            }

	            Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				Vector ListNegeriDaerah = FrmPrmhnnPemindahanBKEData.getNegeriDaerahMohon(idPermohonan);
				this.context.put("listNegeriDaerah",ListNegeriDaerah);

				Vector ListDaerahbyNegeri = FrmPrmhnnPemindahanBKEData.getDaerahbyNegeri(idPermohonan);
				this.context.put("listDaerah", ListDaerahbyNegeri);

				Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
				this.context.put("listBkeData", listBkeData);

				this.context.put("readmode", "disabled");
				this.context.put("display_info", "yes");
				this.context.put("daerahmode", "disabled");
				this.context.put("btnKemaskini", "yes");
				this.context.put("btnSimpan", "");
				this.context.put("btnCetak", "no");
				this.context.put("btnBatal", "");
				this.context.put("btnKembali", "yes"); // kembali ke list
			}
			else if ("Permohonankemaskini".equals(mode)){

				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				this.context.put("selectedTabatas",getParam("tabIdatas"));
	            this.context.put("selectedTabtengah",getParam("tabIdtengah"));
	            this.context.put("selectedTabbawah",getParam("tabIdbawah"));
	            this.context.put("selectedTabtepi",getParam("tabIdtepi"));

	            ListNegeri = FrmPrmhnnPemindahanBKEData.getListnegeribyhta(idPermohonan);
				this.context.put("listNegeri", ListNegeri);

	            Vector ListNegeriDaerah = FrmPrmhnnPemindahanBKEData.getNegeriDaerahMohon(idPermohonan);
				this.context.put("listNegeriDaerah",ListNegeriDaerah);

				Vector ListDaerahbyNegeri = FrmPrmhnnPemindahanBKEData.getDaerahbyNegerihta(idPermohonan);
				this.context.put("listDaerah", ListDaerahbyNegeri);

				Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
				this.context.put("listBkeData", listBkeData);
				Hashtable a = (Hashtable) listBkeData.get(0);
				int idnegeri = Integer.parseInt(a.get("idnegeri").toString());

				Vector listnegeribydaerah = FrmPrmhnnPemindahanBKEData.getListDaerahbyNegerihta(idnegeri,idPermohonan);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);

				this.context.put("readmode", "");
				this.context.put("daerahmode", "");
				this.context.put("btnKemaskini", "");
				this.context.put("btnSimpan", "yes");
				this.context.put("btnCetak", "no");
				this.context.put("btnBatal", "yes");
				this.context.put("btnKembali", "yes"); // kembali ke list
			}
			vm = "app/ppk/frmPemindahanSek17BKE.jsp";
		}
		else if("Keputusan17".equals(submit)){

			this.context.put("selectedTabatas",getParam("tabIdatas"));
            this.context.put("selectedTabtengah",getParam("tabIdtengah"));
            this.context.put("selectedTabbawah",getParam("tabIdbawah"));
            this.context.put("selectedTabtepi",getParam("tabIdtepi"));

			String idPermohonan = getParam("idPermohonan");
			this.context.put("idPermohonan", getParam("idPermohonan"));

			Vector ListPegawai = FrmPrmhnnPemindahanBKEData.getNamaPegawai(Integer.parseInt((String)session.getAttribute("_ekptg_user_id")));
			this.context.put("listPegawai", ListPegawai);

			if ("Keputusanview".equals(mode)){

				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				int cntdatakeputusan = FrmPrmhnnPemindahanBKEData.cntKeputusan(idPermohonan);
				if (cntdatakeputusan == 0){
					this.context.put("readmode", "");
					this.context.put("btnKemaskini", "");
					this.context.put("btnSimpan", "yes");
					this.context.put("btnCetak", "");
					this.context.put("btnBatal", "yes");
					this.context.put("btnKembali", "");
					this.context.put("checkeda", "");
					this.context.put("checkedb", "");
					this.context.put("checkedc", "");
					this.context.put("checked1", "");
					this.context.put("checked2", "");
					this.context.put("catatan", "");
				}else{
					this.context.put("readmode", "disabled");
					this.context.put("btnKemaskini", "yes");
					this.context.put("btnSimpan", "");
					this.context.put("btnCetak", "yes");
					this.context.put("btnBatal", "");
					this.context.put("btnKembali", "yes");

					Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
					this.context.put("listBkeData", listBkeData);
				}

			}
			else if ("Keputusansimpan".equals(mode)){
				this.context.put("readmode", "disabled");
				this.context.put("btnKemaskini", "yes");
				this.context.put("btnSimpan", "");
				this.context.put("btnCetak", "yes");
				this.context.put("btnBatal", "");
				this.context.put("btnKembali", "yes");

				Vector dblcheck = FrmPrmhnnPemindahanBKEData.cntDataPersamaan(idPermohonan,getParam("sorkeputusan"));
				Hashtable b = (Hashtable) dblcheck.get(0);
				int cntpersamaan = Integer.parseInt(b.get("cntpersamaan").toString());
				int insertFail = 0;

				if (cntpersamaan == 0){
					insertFail = 1; //masukkan data dlm statusfail
				}else{
					insertFail = 2; //tak masukkan data dlm statusfail
				}

				updateBkeInfo17(session,insertFail);

				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
				this.context.put("listBkeData", listBkeData);
			}
			else if ("Keputusankemaskini".equals(mode)){
				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				this.context.put("readmode", "");
				this.context.put("btnKemaskini", "");
				this.context.put("btnSimpan", "yes");
				this.context.put("btnCetak", "");
				this.context.put("btnBatal", "yes");
				this.context.put("btnKembali", "");

				Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
				this.context.put("listBkeData", listBkeData);
			}
			else if ("Keputusanbatal".equals(mode)){
				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				this.context.put("readmode", "disabled");
				this.context.put("btnKemaskini", "yes");
				this.context.put("btnSimpan", "");
				this.context.put("btnCetak", "");
				this.context.put("btnBatal", "");
				this.context.put("btnKembali", "yes");

				Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
				this.context.put("listBkeData", listBkeData);
			}
			vm = "app/ppk/frmPemindahanSek17BKEKeputusan.jsp";
		}
		else if("Kptg17".equals(submit)){

			this.context.put("selectedTabatas",getParam("tabIdatas"));
            this.context.put("selectedTabtengah",getParam("tabIdtengah"));
            this.context.put("selectedTabbawah",getParam("tabIdbawah"));
            this.context.put("selectedTabtepi",getParam("tabIdtepi"));

			String idPermohonan = getParam("idPermohonan");
			this.context.put("idPermohonan", getParam("idPermohonan"));

			if ("Kptgview".equals(mode)){
				Vector resultKptgData = FrmPrmhnnPemindahanBKEData.cntKeputusanKptg(idPermohonan);
	            Hashtable b = (Hashtable) resultKptgData.get(0);
				int cntkptg = Integer.parseInt(b.get("cntkptg").toString());
				if (cntkptg == 0){
					this.context.put("releaseData", "0");
					this.context.put("tarikhLulus", "");
					this.context.put("checked1", "");
					this.context.put("checked2", "");
					this.context.put("readmode", "");
					this.context.put("btnKemaskini", "");
					this.context.put("btnSimpan", "yes");
					this.context.put("btnCetak", "");
					this.context.put("btnBatal", "");
					this.context.put("catatan", "");
					this.context.put("btnKembali", "");
				}else{
					this.context.put("releaseData", "1");
					this.context.put("readmode", "disabled");
					this.context.put("btnKemaskini", "yes");
					this.context.put("btnSimpan", "");
					this.context.put("btnCetak", "");
					this.context.put("btnBatal", "");
					this.context.put("btnKembali", "yes");

					Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
					this.context.put("listBkeData", listBkeData);
				}
			}
			else if ("Kptgsimpan".equals(mode)){

				this.context.put("releaseData", "1");
				this.context.put("tarikhLulus", "");
				this.context.put("checked1", "");
				this.context.put("checked2", "");
				this.context.put("readmode", "disabled");
				this.context.put("btnKemaskini", "yes");
				this.context.put("btnSimpan", "");
				this.context.put("btnCetak", "");
				this.context.put("btnBatal", "");
				this.context.put("catatan", "");
				this.context.put("btnKembali", "yes");

				Vector dblcheck = FrmPrmhnnPemindahanBKEData.cntDataPersamaan(idPermohonan,getParam("sorkeputusan"));
				Hashtable b = (Hashtable) dblcheck.get(0);
				int cntpersamaan = Integer.parseInt(b.get("cntpersamaan").toString());
				int insertFail = 0;

				if (cntpersamaan == 0){
					insertFail = 1; //masukkan data dlm statusfail
				}else{
					insertFail = 2; //tak masukkan data dlm statusfail
				}
				updateKptg17(session,insertFail);

				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
				this.context.put("listBkeData", listBkeData);
			}
			else if ("Kptgkemaskini".equals(mode)){
				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				this.context.put("releaseData", "1");
				this.context.put("readmode", "");
				this.context.put("btnKemaskini", "");
				this.context.put("btnSimpan", "yes");
				this.context.put("btnCetak", "");
				this.context.put("btnBatal", "yes");
				this.context.put("btnKembali", "");

				Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
				this.context.put("listBkeData", listBkeData);
			}
			else if ("Kptgbatal".equals(mode)){
				Vector list = FrmPrmhnnPemindahanBKEData.getMaklumatPermohonan(idPermohonan);
				this.context.put("View", list);
				
				headerppk_baru(session,idPermohonan,"Y","","T");

				this.context.put("releaseData", "1");
				this.context.put("readmode", "disabled");
				this.context.put("btnKemaskini", "yes");
				this.context.put("btnSimpan", "");
				this.context.put("btnCetak", "");
				this.context.put("btnBatal", "");
				this.context.put("btnKembali", "yes");

				Vector listBkeData = FrmPrmhnnPemindahanBKEData.getBkeData(idPermohonan);
				this.context.put("listBkeData", listBkeData);
			}

			vm = "app/ppk/frmPemindahanSek17BKEKeputusanKptg.jsp";
		}
		else if ("doChangesCari".equals(submit)) {
				listUserDetail = FrmPrmhnnPemindahanBKEData.getUserDetail((String) session.getAttribute("_ekptg_user_id"));
				Hashtable a = (Hashtable) listUserDetail.get(0);
				int idnegeri = Integer.parseInt(a.get("idnegeri").toString());
				int iddaerah = Integer.parseInt(a.get("iddaerah").toString());

				FrmPrmhnnPemindahanBKEData.setCarianFail(getParam("txtNoFail"),getParam("txtPemohon"),getParam("txtSimati"),getParam("txtIcSimati"),getParam("jenisIc"),idnegeri);
				list1 = FrmPrmhnnPemindahanBKEData.getList();
				Hashtable c1 = (Hashtable) list1.get(0);
				if (c1.get("bil")=="")c1.put("bil",0);
				int bil = Integer.parseInt(c1.get("bil").toString());
				int countList1 = list1.size();
				this.context.put("Senaraifail1",list1);
				this.context.put("CountList1",bil);
				this.context.put("carix",2);
				initTextFieldCarian(getParam("txtNoFail"),getParam("txtPemohon"),getParam("txtSimati"),getParam("txtIcSimati"),getParam("jenisIc"));

				setupPage1(session,action,list1);

				vm = "app/ppk/frmSenaraiFailPemindahanBKE.jsp";
		}
		else if ("doChangesvalue".equals(submit)) {
			listUserDetail = FrmPrmhnnPemindahanBKEData.getUserDetail((String) session.getAttribute("_ekptg_user_id"));
			Hashtable a = (Hashtable) listUserDetail.get(0);
			int idnegeri = Integer.parseInt(a.get("idnegeri").toString());
			int iddaerah = Integer.parseInt(a.get("iddaerah").toString());

			FrmPrmhnnPemindahanBKEData.setCarianFail(getParam("txtNoFail"),getParam("txtPemohon"),getParam("txtSimati"),getParam("txtIcSimati"),getParam("jenisIc"),idnegeri);
			list1 = FrmPrmhnnPemindahanBKEData.getList();
			Hashtable c1 = (Hashtable) list1.get(0);
			if (c1.get("bil")=="")c1.put("bil",0);
			int bil = Integer.parseInt(c1.get("bil").toString());
			int countList1 = list1.size();
			this.context.put("Senaraifail1",list1);
			this.context.put("CountList1",bil);
			this.context.put("carix",2);

			initTextFieldCarian(getParam("txtNoFail"),getParam("txtPemohon"),getParam("txtSimati"),getParam("txtIcSimati"),getParam("jenisIc"));

			setupPage1(session,action,list1);

			vm = "app/ppk/frmSenaraiFailPemindahanBKE.jsp";

		}
		else if ("doChanges".equals(submit)) {
			listUserDetail = FrmPrmhnnPemindahanBKEData.getUserDetail((String) session.getAttribute("_ekptg_user_id"));
			Hashtable a = (Hashtable) listUserDetail.get(0);
			int idnegeri = Integer.parseInt(a.get("idnegeri").toString());
			//int iddaerah = Integer.parseInt(a.get("iddaerah").toString());
			
			String iddaerah = getParam("iddaerah");

			listpaparan = FrmPrmhnnPemindahanBKEData.setList(idnegeri,iddaerah);
			int countList = listpaparan.size();
			this.context.put("Senaraifail",listpaparan);
			this.context.put("CountList",countList);
			this.context.put("carix",1);

			setupPage(session,action,listpaparan);

			vm = "app/ppk/frmSenaraiFailPemindahanBKE.jsp";

		}
		else{
			listUserDetail = FrmPrmhnnPemindahanBKEData.getUserDetail((String) session.getAttribute("_ekptg_user_id"));
			Hashtable a = (Hashtable) listUserDetail.get(0);
			int idnegeri = Integer.parseInt(a.get("idnegeri").toString());
			//int iddaerah = Integer.parseInt(a.get("iddaerah").toString());
			
			String iddaerah = getParam("iddaerah");

			listpaparan = FrmPrmhnnPemindahanBKEData.setList(idnegeri,iddaerah);
			int countList = listpaparan.size();
			this.context.put("Senaraifail",listpaparan);
			this.context.put("CountList",countList);
			this.context.put("carix",1);
			this.context.put("txtnofail","");
			this.context.put("txtpemohon","");
			this.context.put("txtsimati","");
			this.context.put("txticsimati","");
			this.context.put("txtjenisic","");

			vm = "app/ppk/frmSenaraiFailPemindahanBKE.jsp";

			setupPage(session,action,listpaparan);
		}
		return vm;


	}

	public void setupPage(HttpSession session,String action,Vector listpaparan) {
		try {

		this.context.put("totalRecords",listpaparan.size());
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
	    	page = getParamAsInteger("getvalue");
	    } else if ("doChangeItemPerPage".equals(action)) {
	       itemsPerPage = getParamAsInteger("itemsPerPage");
	    }

	    Paging paging = new Paging(session,listpaparan,itemsPerPage);

		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("senaraitugasan",paging.getPage(page));
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

	public void setupPage1(HttpSession session,String action,Vector list1) {
		try {

		this.context.put("totalRecords",list1.size());
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
	    	page = getParamAsInteger("getvalue");
	    } else if ("doChangeItemPerPage".equals(action)) {
	       itemsPerPage = getParamAsInteger("itemsPerPage");
	    }

	    Paging paging = new Paging(session,list1,itemsPerPage);

		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("senaraitugasan1",paging.getPage(page));
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

	private void insertPermohonanBaru(HttpSession session) throws Exception {
		Hashtable h = null;
	    h = new Hashtable();

	    String[] cbAlasanPindah = this.request.getParameterValues("alasanpindah");

	    if(cbAlasanPindah!=null){
	    	for (int i = 0; i < cbAlasanPindah.length; i++) {
			    if (cbAlasanPindah[i].equals("100")){
			    	h.put("alasan1", "100");
			    }else if (cbAlasanPindah[i].equals("101")){
			    	h.put("alasan2", "101");
			    }else if (cbAlasanPindah[i].equals("102")){
			    	h.put("alasan3", "102");
			    }else if (cbAlasanPindah[i].equals("103")){
			    	h.put("alasan4", "103");
			    }else if (cbAlasanPindah[i].equals("104")){
			    	h.put("alasan5", "104");
			    }else if (cbAlasanPindah[i].equals("105")){
			    	h.put("alasan6", "105");
			    }else if (cbAlasanPindah[i].equals("106")){
			    	h.put("alasan7", "106");
			    }
		    }
	    }else{
	    	h.put("alasan1", "0");
	    	h.put("alasan2", "0");
	    	h.put("alasan3", "0");
	    	h.put("alasan4", "0");
	    	h.put("alasan5", "0");
	    	h.put("alasan6", "0");
	    	h.put("alasan7", "0");
	    }
	    h.put("iduser", (String)session.getAttribute("_ekptg_user_id"));
	    h.put("IdPermohonan", getParam("idPermohonan"));
	    h.put("socnegeri", getParam("socNegeri"));
	    h.put("socdaerah", getParam("socDaerah"));
	    h.put("tarikhsurat", getParam("txdTarikhSurat"));
	    h.put("alasanlain", getParam("txtAlasanLain"));
	    FrmPrmhnnPemindahanBKEData.insertNewBke(h);
	}

	private void insertPermohonanBaru17(HttpSession session) throws Exception {
		Hashtable h = null;
	    h = new Hashtable();

	    String[] cbAlasanPindah = this.request.getParameterValues("alasanpindah");

	    if(cbAlasanPindah!=null){
	    	for (int i = 0; i < cbAlasanPindah.length; i++) {
			    if (cbAlasanPindah[i].equals("100")){
			    	h.put("alasan1", "100");
			    }else if (cbAlasanPindah[i].equals("101")){
			    	h.put("alasan2", "101");
			    }else if (cbAlasanPindah[i].equals("102")){
			    	h.put("alasan3", "102");
			    }else if (cbAlasanPindah[i].equals("103")){
			    	h.put("alasan4", "103");
			    }else if (cbAlasanPindah[i].equals("104")){
			    	h.put("alasan5", "104");
			    }else if (cbAlasanPindah[i].equals("105")){
			    	h.put("alasan6", "105");
			    }else if (cbAlasanPindah[i].equals("106")){
			    	h.put("alasan7", "106");
			    }
		    }
	    }else{
	    	h.put("alasan1", "0");
	    	h.put("alasan2", "0");
	    	h.put("alasan3", "0");
	    	h.put("alasan4", "0");
	    	h.put("alasan5", "0");
	    	h.put("alasan6", "0");
	    	h.put("alasan7", "0");
	    }
	    h.put("iduser", (String)session.getAttribute("_ekptg_user_id"));
	    h.put("IdPermohonan", getParam("idPermohonan"));
	    h.put("socnegeri", getParam("socNegeri"));
	    h.put("socdaerah", getParam("socDaerah"));
	    h.put("tarikhsurat", getParam("txdTarikhSurat"));
	    h.put("alasanlain", getParam("txtAlasanLain"));
	    logic2.insertNewBke(h);
	}

	private void updatePermohonanBaru(HttpSession session) throws Exception {
		Hashtable h = null;
	    h = new Hashtable();

	    String[] cbAlasanPindah = this.request.getParameterValues("alasanpindah");

	    if(cbAlasanPindah!=null){
	    	for (int i = 0; i < cbAlasanPindah.length; i++) {
			    if (cbAlasanPindah[i].equals("100")){
			    	h.put("alasan1", "100");
			    }
			    if (cbAlasanPindah[i].equals("101")){
			    	h.put("alasan2", "101");
			    }
			    if (cbAlasanPindah[i].equals("102")){
			    	h.put("alasan3", "102");
			    }
			    if (cbAlasanPindah[i].equals("103")){
			    	h.put("alasan4", "103");
			    }
			    if (cbAlasanPindah[i].equals("104")){
			    	h.put("alasan5", "104");
			    }
			    if (cbAlasanPindah[i].equals("105")){
			    	h.put("alasan6", "105");
			    }
			    if (cbAlasanPindah[i].equals("106")){
			    	h.put("alasan7", "106");
			    }
		    }
	    }
	    h.put("iduser", (String)session.getAttribute("_ekptg_user_id"));
	    h.put("IdPermohonan", getParam("idPermohonan"));
	    h.put("socnegeri", getParam("socNegeri"));
	    h.put("socdaerah", getParam("socDaerah"));
	    h.put("tarikhsurat", getParam("txdTarikhSurat"));
	    h.put("alasanlain", getParam("txtAlasanLain"));
	    FrmPrmhnnPemindahanBKEData.updateBke(h);
	}

	private void updateBkeInfo(HttpSession session, int insertFail) throws Exception {
		Hashtable h = null;
	    h = new Hashtable();
	    h.put("iduser", (String)session.getAttribute("_ekptg_user_id"));
	    h.put("IdPermohonan", getParam("idPermohonan"));
	    h.put("keputusan", getParam("sorkeputusan"));
	    h.put("pegawai", getParam("sorpegawai"));
	    h.put("pengendali", getParam("socPengendali"));
	    h.put("idnegeripsk", insertFail);
	    h.put("catatan", getParam("txtCatatanPegawai"));
	    FrmPrmhnnPemindahanBKEData.updateBkeKeputusan(h);
	}

	private void updateBkeInfo17(HttpSession session, int insertFail) throws Exception {
		Hashtable h = null;
	    h = new Hashtable();
	    h.put("iduser", (String)session.getAttribute("_ekptg_user_id"));
	    h.put("IdPermohonan", getParam("idPermohonan"));
	    h.put("keputusan", getParam("sorkeputusan"));
	    h.put("pegawai", getParam("sorpegawai"));
	    h.put("pengendali", getParam("socPengendali"));
	    h.put("idnegeripsk", insertFail);
	    h.put("catatan", getParam("txtCatatanPegawai"));
	    logic2.updateBkeKeputusan(h);
	}

	private void updateKptg(HttpSession session, int insertFail) throws Exception {
		Hashtable h = null;
	    h = new Hashtable();
	    h.put("iduser", (String)session.getAttribute("_ekptg_user_id"));
	    h.put("IdPermohonan", getParam("idPermohonan"));
	    h.put("tarikhlulus", getParam("txdTarikhLulus"));
	    h.put("insertfail", insertFail);
	    h.put("keputusan", getParam("sorkeputusan"));
	    h.put("catatankptg", getParam("txtCatatanKptg"));
	    FrmPrmhnnPemindahanBKEData.updateKptgKeputusan(h);
	}
	private void updateKptg17(HttpSession session, int insertFail) throws Exception {
		Hashtable h = null;
	    h = new Hashtable();
	    h.put("iduser", (String)session.getAttribute("_ekptg_user_id"));
	    h.put("IdPermohonan", getParam("idPermohonan"));
	    h.put("tarikhlulus", getParam("txdTarikhLulus"));
	    h.put("insertfail", insertFail);
	    h.put("keputusan", getParam("sorkeputusan"));
	    h.put("catatankptg", getParam("txtCatatanKptg"));
	    FrmPrmhnnPemindahanSek17BKEData.updateKptgKeputusan(h);
	}

	public void initTextFieldCarian(String txtnofail, String txtpemohon, String txtsimati, String txticsimati, String jenisic) {

		this.context.put("txtnofail",txtnofail);
		this.context.put("txtpemohon",txtpemohon);
		this.context.put("txtsimati",txtsimati);
		this.context.put("txticsimati",txticsimati);
		this.context.put("txtjenisic",jenisic);
	}
	
	private void headerppk_baru(HttpSession session,String id_permohonan,String flag_permohonan,String id_fail,String flag_fail) throws Exception {
		//hashtable maklumat header
		myLogger.info("CHECK id_permohonan :"+id_permohonan);
		this.context.put("headerppk",mainheader.getHeaderData(session,id_permohonan,flag_permohonan,id_fail,flag_fail));
		Vector list_sub = null;
		list_sub = mainheader.carianFail(id_permohonan,flag_permohonan,id_fail,flag_fail);		
		this.context.put("list_sub_header",list_sub);
		this.context.put("flag_jenis_vm","ajax");
	}
	private void headerppk_baru_default()
	{
		Hashtable headerppk = null;
		this.context.put("headerppk","");
		this.context.put("list_sub_header","");
		this.context.put("flag_jenis_vm","ajax");
	}
}
