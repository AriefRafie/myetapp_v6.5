package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnPemindahanBKEData;
import ekptg.model.ppk.FrmPrmhnnPemindahanSek17BKEData;
//import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8Data;


public class FrmPemindahanSek17BKE extends AjaxBasedModule{

	//FrmPrmhnnSek8Data logic = new FrmPrmhnnSek8Data();
	FrmHeaderPpk mainheader = null;

	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		mainheader = new FrmHeaderPpk();
		String vm = "app/ppk/frmSenaraiFailPemindahanBKE.jsp";
		String submit = getParam("hitButt");
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
		int userid = Integer.parseInt((String)session.getAttribute("_ekptg_user_id"));


		System.out.println("View --->>>FrmPemindahanBKE");
		System.out.println("submit--->"+submit);
		System.out.println("mode--->"+mode);

		if("papar17".equals(submit)){

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
		else if("Cari".equals(submit)){
			/*Vector listUserDetail = FrmPrmhnnPemindahanBKEData.getUserDetail(Integer.parseInt((String)session.getAttribute("_ekptg_user_id")));*/
			Vector listUserDetail = FrmPrmhnnPemindahanBKEData.getUserDetail((String) session.getAttribute("_ekptg_user_id"));
			Hashtable a = (Hashtable) listUserDetail.get(0);
			int idnegeri = Integer.parseInt(a.get("idnegeri").toString());
			int iddaerah = Integer.parseInt(a.get("iddaerah").toString());

			FrmPrmhnnPemindahanBKEData.setCarianFail(getParam("txtNoFail"),getParam("txtPemohon"),getParam("txtSimati"),getParam("txtIcSimati"),getParam("jenisIc"),idnegeri);
			Vector list1 = FrmPrmhnnPemindahanBKEData.getList();
			Hashtable c1 = (Hashtable) list1.get(0);
			if (c1.get("bil")=="")c1.put("bil",0);
			int bil = Integer.parseInt(c1.get("bil").toString());
			int countList1 = list1.size();
			this.context.put("Senaraifail1",list1);
			this.context.put("CountList1",bil);
			this.context.put("carix",2);

			vm = "app/ppk/frmSenaraiFailPemindahanBKE.jsp";
		}
		else if("kembali".equals(submit)){
			vm = "app/ppk/frmSenaraiFailPemindahanBKE.jsp";
		}
		else if("Permohonan17".equals(submit)){

			String idPermohonan = getParam("idPermohonan");
			this.context.put("idPermohonan", getParam("idPermohonan"));

			Vector ListNegeri = FrmPrmhnnPemindahanBKEData.getListnegeribyhta(idPermohonan);
			this.context.put("listNegeri", ListNegeri);

			if ("Permohonanview17".equals(mode)){

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
			else if ("Permohonandaerah17".equals(mode)){

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
			else if ("Permohonansimpan17".equals(mode)){

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
			else if ("Permohonankemaskini17".equals(mode)){

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

			if ("Keputusanview17".equals(mode)){

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
			else if ("Keputusansimpan17".equals(mode)){
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
			else if ("Keputusankemaskini17".equals(mode)){
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
			else if ("Keputusanbatal17".equals(mode)){
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

			if ("Kptgview17".equals(mode)){
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
			else if ("Kptgsimpan17".equals(mode)){

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
			else if ("Kptgkemaskini17".equals(mode)){
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
			else if ("Kptgbatal17".equals(mode)){
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
		else{
			Vector listUserDetail = FrmPrmhnnPemindahanSek17BKEData.getUserDetail(Integer.parseInt((String)session.getAttribute("_ekptg_user_id")));
			Hashtable a = (Hashtable) listUserDetail.get(0);
			int idnegeri = Integer.parseInt(a.get("idnegeri").toString());
			int iddaerah = Integer.parseInt(a.get("iddaerah").toString());

			Vector list = FrmPrmhnnPemindahanSek17BKEData.setList(idnegeri,iddaerah);
			int countList = list.size();
			this.context.put("Senaraifail",list);
			this.context.put("CountList",countList);
			this.context.put("carix",1);
			vm = "app/ppk/frmSenaraiFailPemindahanSek17BKE.jsp";
		}
		return vm;
	}

	private void insertPermohonanBaru(HttpSession session) throws Exception {
		Hashtable h = null;
	    h = new Hashtable();

	    String[] cbAlasanPindah = this.request.getParameterValues("alasanpindah");

	    if(cbAlasanPindah!=null){
	    	for (int i = 0; i < cbAlasanPindah.length; i++) {
			    if (cbAlasanPindah[i].equals("83")){
			    	h.put("alasan1", "83");
			    }else if (cbAlasanPindah[i].equals("84")){
			    	h.put("alasan2", "84");
			    }else if (cbAlasanPindah[i].equals("85")){
			    	h.put("alasan3", "85");
			    }else if (cbAlasanPindah[i].equals("86")){
			    	h.put("alasan4", "86");
			    }else if (cbAlasanPindah[i].equals("87")){
			    	h.put("alasan5", "87");
			    }else if (cbAlasanPindah[i].equals("88")){
			    	h.put("alasan6", "88");
			    }else if (cbAlasanPindah[i].equals("89")){
			    	h.put("alasan7", "89");
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
	    FrmPrmhnnPemindahanSek17BKEData.insertNewBke(h);
	}

	private void updatePermohonanBaru(HttpSession session) throws Exception {
		Hashtable h = null;
	    h = new Hashtable();

	    String[] cbAlasanPindah = this.request.getParameterValues("alasanpindah");

	    if(cbAlasanPindah!=null){
	    	for (int i = 0; i < cbAlasanPindah.length; i++) {
			    if (cbAlasanPindah[i].equals("83")){
			    	h.put("alasan1", "83");
			    }
			    if (cbAlasanPindah[i].equals("84")){
			    	h.put("alasan2", "84");
			    }
			    if (cbAlasanPindah[i].equals("85")){
			    	h.put("alasan3", "85");
			    }
			    if (cbAlasanPindah[i].equals("86")){
			    	h.put("alasan4", "86");
			    }
			    if (cbAlasanPindah[i].equals("87")){
			    	h.put("alasan5", "87");
			    }
			    if (cbAlasanPindah[i].equals("88")){
			    	h.put("alasan6", "88");
			    }
			    if (cbAlasanPindah[i].equals("89")){
			    	h.put("alasan7", "89");
			    }
		    }
	    }
	    h.put("iduser", (String)session.getAttribute("_ekptg_user_id"));
	    h.put("IdPermohonan", getParam("idPermohonan"));
	    h.put("socnegeri", getParam("socNegeri"));
	    h.put("socdaerah", getParam("socDaerah"));
	    h.put("tarikhsurat", getParam("txdTarikhSurat"));
	    h.put("alasanlain", getParam("txtAlasanLain"));
	    FrmPrmhnnPemindahanSek17BKEData.updateBke(h);
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
	    FrmPrmhnnPemindahanSek17BKEData.updateBkeKeputusan(h);
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
	    FrmPrmhnnPemindahanSek17BKEData.updateKptgKeputusan(h);
	}
	private void headerppk_baru(HttpSession session,String id_permohonan,String flag_permohonan,String id_fail,String flag_fail) throws Exception {
		//hashtable maklumat header
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
