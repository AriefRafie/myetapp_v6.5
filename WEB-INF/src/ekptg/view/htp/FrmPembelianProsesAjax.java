/**
 * @author Firzan.Fir
 * 
 */

package ekptg.view.htp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.model.entities.Tblrujagensi;
import ekptg.model.entities.Tblrujkementerian;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.htp.FrmPembelianInfoData;
import ekptg.model.htp.FrmPembelianPeguamData;
import ekptg.model.htp.FrmPembelianPemilikData;
import ekptg.model.htp.FrmPembelianPenjualData;
import ekptg.model.htp.FrmPembelianPerjanjianJualBeliData;
import ekptg.model.htp.FrmPembelianSemakan1Data;
import ekptg.model.htp.FrmPembelianSemakanData;
import ekptg.model.htp.FrmPembelianTBangunData;
import ekptg.model.htp.FrmPembelianTanahData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmSenaraiFailPembelianData;

public class FrmPembelianProsesAjax extends AjaxBasedModule {

	private static Logger log = Logger.getLogger(FrmPembelianProsesAjax.class);
	private static final long serialVersionUID = 1L;
	private String result = "";
	String submit = "";
	String mode = "";
	String dochange = "";
	private Date now = new Date();
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	String idPermohonan = null;
	String idHakmilikurusan = null;
	String idFail = null;
	String idDraf = null;
	String idPerjanjian = null;
	String idPerjanjianTambahan = null;
	String tabSelection = null;

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		log.info("PembelianProcess::");
		String vm = "";
		String disability = "disabled";
		String readability = "readonly";
		this.context.put("SimpanStatus", "");
		Vector list = new Vector();
		list.clear();
		this.submit = getParam("command");
		this.mode = getParam("mode");
		this.dochange = getParam("dochange");
		this.tabSelection = getParam("tabSelection");

		log.info("command : " + submit);
		log.info("mode : " + mode);
		log.info("dochange : " + dochange);
		log.info("tabSelection : " + tabSelection);

		String selectedTab = getParam("tabId");
		String tabmode = getParam("tabmode");

		if (selectedTab == null || "".equals(selectedTab)) {
			selectedTab = "0";
			tabmode = "0";
		}
		this.context.put("selectedTab", selectedTab);
		this.context.put("tabmode", tabmode);

		// fir modified 25012010
		if (getParam("idHakmilikurusan") != "" ) {
			idHakmilikurusan = getParam("idHakmilikurusan");
		}

		log.info("idHakmilikurusan : " + idHakmilikurusan);

		if (submit.equalsIgnoreCase("FailBaru")) {


			vm = "app/htp/frmPembelianProsesAjax.jsp";
			log.info("fir id : " + getParam("idPermohonan"));

			this.context.put("IdPermohonan", getParam("idPermohonan"));
			this.context.put("senaraiSemakan", FrmSemakan
					.getSenaraiSemakan("frmPembelianSemakNew"));
			this.context.put("semakclass", new FrmSemakan());
			this.context.put("page", 1);

			if (mode.equalsIgnoreCase("view")) {

				idPermohonan = getParam("idPermohonan");
				log.info(submit + mode + idPermohonan);
				setFailBaru(session, idPermohonan);
				ViewFailBaru(session, disability, readability);

			} else if (mode.equalsIgnoreCase("kemaskini")) {

				idPermohonan = getParam("idPermohonan");
				setFailBaru(session, idPermohonan);
				ViewFailBaru(session, disability, readability);

			} else if (mode.equalsIgnoreCase("simpan")) {

				idPermohonan = SimpanFailBaru(session);

				String[] cbsemaks = this.request.getParameterValues("cbsemaks");
				FrmSemakan frmSemak = new FrmSemakan();
				frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
				if (cbsemaks != null) {
					for (int i = 0; i < cbsemaks.length; i++) {
						frmSemak = new FrmSemakan();
						log
						.info("PembelianProcess:FailBaru::simpan::cbsemaks:::"
								+ cbsemaks[i]);
						frmSemak.semakanTambah(cbsemaks[i], String
								.valueOf(idPermohonan));
					}
				}

				setFailBaru(session, idPermohonan);
				ViewFailBaru(session, disability, readability);
				this.context.put("IdPermohonan", idPermohonan);
				this.context.put("SimpanStatus", "success");
				this.context.put("ResultSimpan", result);

			} else if (mode.equalsIgnoreCase("dochangekementerian")) {
				DataFailBaru(session, "", "");
			}

			else {
				DataFailBaru(session, "", "");
			}

			log.info("PembelianProcess::FailBaru");

		} 

		/*
		 * end of FAIL BARU
		 */



		/* 
		 * submit = MAKLUMAT
		 * template name : app/htp/frmPembelianMaklumat.jsp 
		 * page = 2
		 */

		else if (submit.equalsIgnoreCase("Maklumat")) {

			vm = "app/htp/frmPembelianProsesAjax.jsp";

			idPermohonan = getParam("idPermohonan");
			log.info("Maklumat : idPermohon : " + idPermohonan);
			this.context.put("IdPermohonan", idPermohonan);
			this.context.put("IdFail", getParam("idFail"));
			this.context.put("IdHakmilikurusan", getParam("idHakmilikurusan"));
			String idPenjual = getParam("socPenjual");
			log.info("idPenjual : " + idPenjual);
			this.context.put("page", 2);

			// ***tanah search
			String Daerah = getParam("socDaerah") == "" ? "0"
					: getParam("socDaerah");
			Long idDaerah = Long.parseLong(Daerah);

			String Mukim = getParam("socMukim") == "" ? "0"
					: getParam("socMukim");
			Long idMukim = Long.parseLong(Mukim);

			// ***tbangun
			String DaerahB = getParam("socDaerahB") == "" ? "0"
					: getParam("socDaerahB");
			Long idDaerahB = Long.parseLong(DaerahB);
			String MukimB = getParam("socMukimB") == "" ? "0"
					: getParam("socMukimB");
			Long idMukimB = Long.parseLong(MukimB);

			list = FrmPembelianInfoData.getSemak(Long.parseLong(idPermohonan));
			this.context.put("Info", list);
			Hashtable h = (Hashtable) list.get(0);


			long idNegeri = Long.parseLong(h.get("idNegeri").toString());

			this.context.put("Negeri", getNamaNegeri(h.get("idNegeri")
					.toString()));
			this.context.put("selectKementerian", getNamaKementerian(h.get(
			"idKementerian").toString()));
			this.context.put("selectAgensi", getNamaAgensi(h.get("idAgensi")
					.toString()));
			this.context.put("selectSuburusan", getNamaSubUrusan(h.get(
			"idSuburusan").toString()));
			this.context.put("urusan", h.get("idSuburusan".toString()));

			//fir 20022010
			this.context.put("negeri", h.get("idNegeri").toString());


			if (mode.equalsIgnoreCase("pemilik")) {

				setTanah(session, idPermohonan, idDaerah, idMukim);
				ViewTanah(session, idNegeri);
				ListAllPemilik(idPermohonan);
				session.setAttribute("idPermohonanTanah",idPermohonan );

				DataPemilikDepan(session, "", "");

			} else if (mode.equalsIgnoreCase("pemilikview")) {

				this.context.put("IdHakmilikurusan",
						getParam("idHakmilikurusan"));
				ListPemilik(session);
				DataPemilik(session, "", "");

			} else if (mode.equalsIgnoreCase("simpanpemilik")) {

				this.context.put("IdHakmilikurusan",
						getParam("idHakmilikurusan"));
				SimpanPemilik(session);
				ListAllPemilik(idPermohonan);
				ListPemilik(session);
//				DataPemilik(session, disability, readability);
				session.setAttribute("idPermohonanTanah",idPermohonan );
				DataPemilikDepan(session, "", "");
				this.context.put("SimpanStatus", "success");
				this.context.put("ResultSimpan", result);
				this.context.put("selectedTab", 2);

			} else if (mode.equalsIgnoreCase("kemaskinipemilik")) {

				this.context.put("IdHakmilikurusan",
						getParam("idHakmilikurusan"));
				ListPemilik(session);
				DataPemilik(session, disability, readability);

			} else if (mode.equalsIgnoreCase("hapuspemilik")) {

			} else if (mode.equalsIgnoreCase("peguamview")) {

				ListPeguam(session);
				DataPeguam(session, disability, readability);
				this.context.put("IdHakmilikurusan", getParam("idHakmilikurusan"));

			} else if (mode.equalsIgnoreCase("peguamsimpan")) {

				SimpanPeguam(session);
				ListPeguam(session);
				DataPeguam(session, disability, readability);
				this.context.put("SimpanStatus", "success");
				this.context.put("ResultSimpan", result);
				this.context.put("selectedTab", 3);


			} else if (mode.equalsIgnoreCase("peguamkemaskini")) {

				ListPeguam(session);
				DataPeguam(session, disability, readability);


			} else if (mode.equalsIgnoreCase("peguamhapus")) {



			} else if (mode.equalsIgnoreCase("tanahview")) {

				if (dochange.equalsIgnoreCase("daerah")) {
					ViewTanah(session, idNegeri);

				} else {
					setTanah(session, idPermohonan, idDaerah, idMukim);
					ViewTanah(session, idNegeri);
//					ViewTanahUlasan(session, disability, readability);
					log.info("PembelianProcess::Maklumat:tanahview");

				}

			} else if (mode.equalsIgnoreCase("tanahsimpan")) {

//				SimpanTanahUlasan(session);
				setTanah(session, idPermohonan, idDaerah, idMukim);
				ViewTanah(session, idNegeri);
//				ViewTanahUlasan(session, disability, readability);
				log.info("PembelianProcess::Maklumat:tanahsimpan");

			} else if (mode.equalsIgnoreCase("tanahkemaskini")) {

				setTanah(session, idPermohonan, idDaerah, idMukim);
				ViewTanah(session, idNegeri);
				ViewTanahUlasan(session, disability, readability);

			}
			else if (mode.equalsIgnoreCase("perakuankptgview")) {

				setUlasanJKPTG(session, this.idPermohonan);
				ViewTanahUlasan(session, disability, readability);

			}
			else if (mode.equalsIgnoreCase("perakuankptgsimpan")) {

				SimpanTanahUlasan(session);
				setUlasanJKPTG(session, this.idPermohonan);
				ViewTanahUlasan(session, disability, readability);

			}
			else if (mode.equalsIgnoreCase("perakuankptgkemaskini")) {

				setUlasanJKPTG(session, this.idPermohonan);
				ViewTanahUlasan(session, "", "");

			}
			
			
			
			else if (mode.equalsIgnoreCase("tanahhapus")) {

			} else if (mode.equalsIgnoreCase("tbangunview")) {

				setTBangun(session, Long.parseLong(idPermohonan), idDaerahB, idMukimB);
				ViewTBangun(session, idNegeri, idDaerahB, idMukimB);
				log.info("PembelianProcess::Maklumat:tbangunview");

			} else if (mode.equalsIgnoreCase("tbangunsimpan")) {

			} else if (mode.equalsIgnoreCase("tbangunkemaskini")) {

			}

			else if(mode.equalsIgnoreCase("penjualview")){
				
				SetMaklumatPenjual(idPermohonan);
				
				if(getParam("PenjualSama") != null && getParam("PenjualSama")  != ""){
					if(idPenjual != null && idPenjual != ""){
						ListPemilikById(idPenjual);
					}else{
						ListAllPemilik(idPermohonan);
					}


					this.context.put("show", "open");
					this.context.put("modeselected", "checked=\"checked\"");
				}
				else{
					this.context.put("show", "close");
					this.context.put("modeselected", "");
				}
				//                            ListAllPemilik(idPermohonan);
				log.info("Checkbox penjual : " + getParam("PenjualSama") );
				TabMaklumatNamaPenjualData(session, "");
				this.context.put("selectedTab", 4);

			}
			else if(mode.equalsIgnoreCase("penjualsimpan")){
				TabMaklumatPenjualSimpan(session);
				SetMaklumatPenjual(idPermohonan);
				TabMaklumatNamaPenjualData(session, "");
				this.context.put("selectedTab", 4);

			}
			else if(mode.equalsIgnoreCase("penjualkemaskini")){

			}

			log.info("PembelianProcess::Maklumat");
		}

		/*
		 * end of MAKLUMAT
		 */

		/*
		 * submit = TANAH
		 * 
		 */

		else if (submit.equalsIgnoreCase("Tanah")) {

			// vm = "app/htp/frmPembelianTanah.jsp";

			vm = "app/htp/frmPembelianProsesAjax.jsp";
			idPermohonan = getParam("idPermohonan");

			if (mode.equalsIgnoreCase("simpan")){

				log.info("PembelianPopup::Tanah::simpan1");
				idHakmilikurusan = SimpanTanah(session, Long.parseLong(idPermohonan));
				log.info("PembelianPopup::Tanah::simpan2");
				ListTanah(session, Long.parseLong(idPermohonan), Long.parseLong(idHakmilikurusan));
				DataTanah(session, disability, readability);
				log.info("PembelianPopup::Tanah::simpan");
				this.context.put("selectedTab", 0);
				this.context.put("tabmode", 1);

				//fir 02022010
				//this.context.put("IdHakmilikurusan", idHakmilikurusan);

			} else if (mode.equalsIgnoreCase("kemaskini")){

				ListTanah(session, Long.parseLong(idPermohonan), Long.parseLong(idHakmilikurusan));
				DataTanah(session, disability, readability);
				this.context.put("selectedTab", 0);
				this.context.put("tabmode", 1);
				log.info("PembelianPopup::Tanah::kemaskini");

			} else if (mode.equalsIgnoreCase("view")) {

				ListTanah(session, Long.parseLong(idPermohonan), Long.parseLong(idHakmilikurusan));
				DataTanah(session, disability, readability);
				log.info("PembelianPopup::Tanah::view");

			} else if (mode.equalsIgnoreCase("baru")) {

				DataTanahBaru(session, disability, readability);
				this.context.put("IdFail", getParam("idFail"));
				log.info("PembelianPopup::Tanah::baru");

			} else if ("dochangenegeri".equalsIgnoreCase(mode)) {
				DataTanahBaru(session, disability, readability);
				//				this.context.put("", "");
				this.context.put("IdFail", getParam("idFail"));
			} else if ("dochangedaerah".equalsIgnoreCase(mode)) {
				DataTanahBaru(session, disability, readability);
				//				this.context.put("", "");
				this.context.put("IdFail", getParam("idFail"));
			}

		} 

		/*
		 * end of TANAH
		 */


		/*
		 * submit = TBANGUN
		 */

		else if (submit.equalsIgnoreCase("TBangun")) {

			// vm = "app/htp/frmPembelianTBangun.jsp";
			vm = "app/htp/frmPembelianProsesAjax.jsp";
			idPermohonan = getParam("idPermohonan");
			idHakmilikurusan = getParam("idHakmilikurusan");
			this.context.put("IdHakmilikurusan", idHakmilikurusan);
			log.info("Tbangun : " + idPermohonan);

			if (mode.equalsIgnoreCase("simpan")) {

				log.info("PembelianPopup::TBangun::simpan1");
				idHakmilikurusan = SimpanTBangun(session, idPermohonan);
				log.info("PembelianPopup::TBangun::simpan2");
				ListTBangun(session, Long.parseLong(idPermohonan), Long.parseLong(idHakmilikurusan));
				DataTBangun(session, Long.parseLong(idPermohonan), disability, readability);
				log.info("PembelianPopup::TBangun::simpan :: idhakmilikurusan :" + idHakmilikurusan);
				//				this.context.put("IdHakmilikurusan", idHakmilikurusan);
				this.context.put("selectedTab", 1);
				this.context.put("tabmode", 0);
				this.context.put("page", 2);

			} else if (mode.equalsIgnoreCase("kemaskini")) {

				ListTBangun(session, Long.parseLong(idPermohonan), Long.parseLong(idHakmilikurusan));
				DataTBangun(session, Long.parseLong(idPermohonan), disability, readability);
				log.info("PembelianPopup::TBangun::kemaskini");

			} else if (mode.equalsIgnoreCase("view")) {

				ListTBangun(session, Long.parseLong(idPermohonan), Long.parseLong(idHakmilikurusan));
				DataTBangun(session, Long.parseLong(idPermohonan), disability, readability);
				log.info("PembelianPopup::TBangun::view");

			} else if (mode.equalsIgnoreCase("baru")) {

				DataTBangunBaru(session, Long.parseLong(idPermohonan), disability, readability);
				//				this.context.put("IdHakmilikurusan", getParam("idHakmilikurusan"));
				this.context.put("IdHakmilikurusan", idHakmilikurusan);
				log.info("PembelianPopup::TBangun::baru");
			}
		}

		/*
		 * end of TBANGUN
		 */


		/*
		 * start of SEMAKAN
		 */

		else if (submit.equalsIgnoreCase("Semakan")) {
			/*
			 * template name : app/htp/frmPembelianSemakan1.jsp
			 */

			vm = "app/htp/frmPembelianProsesAjax.jsp";
			idPermohonan = getParam("idPermohonan");
			this.context.put("IdPermohonan", idPermohonan);
			this.context.put("senaraiSemakan", FrmSemakan
					.getSenaraiSemakan("frmPembelianSemakNew"));
			this.context.put("semakclass", new FrmSemakan());
			this.context.put("page", 10);

			if (mode.equalsIgnoreCase("view")) {

				idPermohonan = getParam("idPermohonan");
				setSemak(session, idPermohonan);
				ViewSemak(session, disability, readability);

			} 
			else if (mode.equalsIgnoreCase("kemaskini")) {

				idPermohonan = getParam("idPermohonan");
				setSemak(session, idPermohonan);
				ViewSemak(session, "", "");

			} 
			else if (mode.equalsIgnoreCase("simpan")) {

				SimpanSemak(session);

				String[] cbsemaks = this.request.getParameterValues("cbsemaks");
				FrmSemakan frmSemak = new FrmSemakan();
				frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
				if (cbsemaks != null) {
					for (int i = 0; i < cbsemaks.length; i++) {
						frmSemak = new FrmSemakan();
						log
						.info("PembelianProcess:Semakan::simpan::cbsemaks:::"
								+ cbsemaks[i]);
						frmSemak.semakanTambah(cbsemaks[i], String
								.valueOf(idPermohonan));
					}
				}

				setSemak(session, idPermohonan);
				ViewSemak(session, disability, readability);
				this.context.put("SimpanStatus", "success");
				this.context.put("ResultSimpan", result);
			}

			log.info("PembelianProcess::Semakan");

		} 

		/*
		 * end of SEMAKAN
		 */


		/*
		 * submit = DRAF
		 */
		//		
		//		else if (submit.equalsIgnoreCase("draf")){
		//			
		//			vm = "app/htp/frmPembelianProsesAjax.jsp";
		//			this.idPermohonan = getParam("idPermohonan");
		//			this.context.put("idPermohonan", this.idPermohonan);
		//			this.context.put("page", 3);
		//			
		//			log.info("draf selected tab = " + selectedTab);
		//			log.info("draf tabmode : " + tabmode);
		//			
		//			
		//			if (mode.equalsIgnoreCase("baru")){	
		//				if(tabSelection.equalsIgnoreCase("kelulusan")){
		//					TabKelulusanBaru(session, readability, disability);
		//					
		//				}
		//				
		//				//tabselection = draf pembelian
		//				else{
		//					TabDrafPembelianBaru(session , readability, disability);
		//					
		//				}
		//				
		//			}
		//			else if(mode.equalsIgnoreCase("kemaskini")){
		//				
		//			}
		//			else if(mode.equalsIgnoreCase("simpan")){
		//				TabKelulusanSimpan(session, this.idPermohonan);
		//				TabKelulusanView(session, readability, disability);
		//				
		//
		//			}
		//			else if(mode.equalsIgnoreCase("")){
		//				
		//			}
		//			
		//			
		//			
		//		}

		/*
		 * end of DRAF
		 */


		/*
		 * submit = PERJANJIAN 
		 */
		else if(submit.equalsIgnoreCase("Perjanjian")){
			vm = "app/htp/frmPembelianProsesAjax.jsp";
			this.context.put("page", 4);
			this.idPermohonan = getParam("idPermohonan");
			this.idFail  = getParam("idFail");
			this.idDraf = getParam("idDraf");
			this.idPerjanjian = getParam("idPerjanjian");
			this.idPerjanjianTambahan = getParam("idPerjanjianTambahan");
			this.context.put("IdPermohonan", this.idPermohonan);
			this.context.put("IdFail", this.idFail);
			this.context.put("idDraf", this.idDraf);
			
			log.info("perjanjian idpermohonan : " + this.idPermohonan);
			log.info("perjanjian idfail : " + this.idFail);
			log.info("idPerjanjian : " + this.idPerjanjian);
			//                      


			log.info("perjanjian selected tab = " + selectedTab);
			log.info("perjanjian tabmode : " + tabmode);


			if (mode.equalsIgnoreCase("baru")){	
				if(tabSelection.equalsIgnoreCase("kelulusan")){
					TabKelulusanBaru(session, readability, disability);
					
				}
				
				//tabselection = draf pembelian
				else{
					TabDrafPembelianBaru(session , readability, disability);
					
				}
			}
				
			if (mode.equalsIgnoreCase("drafview")){

				SetSenaraiDraf(idPermohonan);
				SetMaklumatDraf(idDraf);
				TabPerjanjianDrafKelulusanData(session, "", "");

			}
			else if(mode.equalsIgnoreCase("drafsimpan")){
				TabPerjanjianDrafKelulusanSimpan(session);
				TabPerjanjianDrafKelulusanData(session, "readonly", "disabled");


			}
			else if (mode.equalsIgnoreCase("drafupdate")){
				TabPerjanjianDrafKelulusanUpdate(session);
				SetSenaraiDraf(idPermohonan);
				SetMaklumatDraf(idDraf);
				this.mode = "drafview";
				this.dochange = "view";
				TabPerjanjianDrafKelulusanData(session, "readonly", "disabled");
				
			}


			else if(mode.equalsIgnoreCase("senaraisemakview")){

				SenaraiSemakView("disabled");

			}

			else if(mode.equalsIgnoreCase("senaraisemakkemaskini")){

				SenaraiSemakView("");

			}
			else if(mode.equalsIgnoreCase("senaraisemakSimpan")){

				String[] cbsemaks = this.request.getParameterValues("cbsemaks");
				FrmSemakan frmSemak = new FrmSemakan();
				frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
				if (cbsemaks != null) {
					for (int i = 0; i < cbsemaks.length; i++) {
						frmSemak = new FrmSemakan();
						log
						.info("FrmPembelianProsesAjax:SenaraiSemakSimpan:cbsemaks:"
								+ cbsemaks[i]);
						frmSemak.semakanTambah(cbsemaks[i], String
								.valueOf(idPermohonan));
					}
				}

				this.context.put("selectedTab", 1);

				SenaraiSemakView("disabled");




			}

			else if(mode.equalsIgnoreCase("pembelianview")){

				FrmPembelianPerjanjianJualBeliData.setListPerjanjianJualBeli(this.idPermohonan);
				TabPerjanjianPembelianData(session, "", "");




			}

			else if(mode.equalsIgnoreCase("pembeliansimpan")){
				TabPerjanjianPembelianSimpan(session);
				FrmPembelianPerjanjianJualBeliData.setListPerjanjianJualBeli(this.idPermohonan);
				TabPerjanjianPembelianData(session, "readonly", "disabled");
				this.context.put("selectedTab", 2);

			}
			
			else if(mode.equalsIgnoreCase("pembelianupdate")){
				TabPerjanjianPembelianSimpanUpdate(session);
				FrmPembelianPerjanjianJualBeliData.setListPerjanjianJualBeli(this.idPermohonan);
				TabPerjanjianPembelianData(session, "readonly", "disabled");
				this.context.put("selectedTab", 2);

			}
			
			else if(mode.equalsIgnoreCase("pembeliankemaskini")){
				FrmPembelianPerjanjianJualBeliData.setListPerjanjianJualBeli(this.idPermohonan);
				TabPerjanjianPembelianData(session, "readonly", "disabled");
				this.context.put("selectedTab", 2);

			}
			
			else if(mode.equalsIgnoreCase("ptambahanview")){
				FrmPembelianPerjanjianJualBeliData.setListPerjanjianTambahan(this.idPermohonan);
				SetMaklumatPerjanjianTambahan(idPerjanjianTambahan);
				TabPerjanjianTambahanData(session, "", "");
				this.context.put("selectedTab", 3);

			}
			else if(mode.equalsIgnoreCase("ptambahansimpan")){
				
				TabPerjanjianTambahanSimpan(session);
				FrmPembelianPerjanjianJualBeliData.setListPerjanjianTambahan(this.idPermohonan);
				SetMaklumatPerjanjianTambahan(idPerjanjianTambahan);
				TabPerjanjianTambahanData(session, "", "");
				this.context.put("selectedTab", 3);

			}
			else if(mode.equalsIgnoreCase("ptambahanupdate")){
				TabPerjanjianTambahanUpdate(session);
				FrmPembelianPerjanjianJualBeliData.setListPerjanjianTambahan(this.idPermohonan);
				SetMaklumatPerjanjianTambahan(idPerjanjianTambahan);
				TabPerjanjianTambahanData(session, "", "");
				this.context.put("selectedTab", 3);

			}


		}

		//end of draf pembelian jual beli

		/*
		 * submit = ""
		 * 
		 * INITIAL PAGE
		 */
		else {

			/*
			 * template name : app/htp/frmSenaraiFailPembelian.jsp
			 */

			vm = "app/htp/frmPembelianProsesAjax.jsp";
			String key_cari = getParam("NamaFail");
			String keyNo_cari = getParam("NoFail");
			String Negeri = getParam("socNegeri") == "" ? "20"
					: getParam("socNegeri");
			Long idNegeri = Long.parseLong(Negeri);
			ListFail(session, key_cari, keyNo_cari, idNegeri);
			list = FrmSenaraiFailPembelianData.getList();
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
					idNegeri, ""));
			this.context.put("Senaraifail", list);
			this.context.put("carian", getParam("NamaFail"));
			this.context.put("carianNoFail", getParam("NoFail"));
			this.context.put("Negeri", idNegeri);
			this.context.put("page", 0);
			log.info("PembelianProcess::SenaraiFail");

		}

		/*
		 * end of INITIAL PAGE
		 */
		System.out.println(vm);
		return vm;

	}// close method doTemplate2


	// *** Senarai Fail Pembelian Controller
	public void ListFail(HttpSession session, String key_cari,
			String keyNo_cari, Long idNegeri) throws Exception {
		FrmSenaraiFailPembelianData.setList(key_cari, keyNo_cari, idNegeri);
	}

	// *** Fail Baru Controller
	private void DataFailBaru(HttpSession session, String disability,
			String readability) throws Exception {
		String mode = this.mode;

		try {

			if (mode.equalsIgnoreCase("dochangekementerian")) {

				log.info("DataFailBaru :: mode : " + mode);
				String negeri = getParam("socNegeri");
				String kementerian = getParam("socKementerian");
				String agensi = getParam("socAgensi");
				String suburusan = getParam("socSuburusan");
				String NoFailKJP = getParam("txtNoFailKJP");
				String tajuk = getParam("txtTajuk");
				String statusTanah = getParam("socStatusTanah");
				String tarafKeselamatanFail = getParam("socTarafKeselamatan");

				if(statusTanah.equalsIgnoreCase("") || statusTanah.equalsIgnoreCase(null)){
					statusTanah = "0";
				}

				if (tarafKeselamatanFail.equalsIgnoreCase("") || tarafKeselamatanFail.equalsIgnoreCase(null)){
					tarafKeselamatanFail = "0";
				}

				log.info("statusTanah :" + statusTanah);
				log.info("taraf kslmtan : " + tarafKeselamatanFail);

				this.context.put("Semak", "");
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						Long.parseLong(negeri)));
				this.context.put("selectKementerian", HTML.SelectKementerian(
						"socKementerian", Long.parseLong(kementerian), "",
				"onchange=\"doChangeKementerian()\""));
				this.context.put("selectAgensi", HTML
						.SelectAgensiByKementerian("socAgensi", kementerian,
								Long.parseLong("0"), ""));
				this.context.put("selectSuburusan", HTML
						.SelectSuburusanByIdUrusan("2", "socSuburusan", null,
								disability));
				this.context.put("statusTanah", HTML.SelectJenisTanah("socStatusTanah", Long.parseLong(statusTanah), ""));
				this.context.put("jenisFail", HTML.SelectTarafKeselamatan("socTarafKeselamatan", Long.parseLong(tarafKeselamatanFail), "") );
				this.context.put("datenow", formatter.format(now));
				this.context.put("modeSoc", disability);
				this.context.put("mode", readability);
				this.context.put("classDis", "");
				this.context.put("viewmode", "new");

			} 

			else {

				this.context.put("Semak", "");
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri"));
				this.context.put("selectKementerian", HTML.SelectKementerian(
						"socKementerian",
				"onchange=\"doChangeKementerian()\""));

				this.context.put("selectAgensi", HTML
						.SelectAgensiByKementerian("socAgensi", "", Long
								.parseLong("0"), ""));
				this.context.put("selectSuburusan", HTML
						.SelectSuburusanByIdUrusan("2", "socSuburusan", null,
								disability));

				this.context.put("statusTanah", HTML.SelectJenisTanah("socStatusTanah"));
				this.context.put("jenisFail", HTML.SelectTarafKeselamatan("socTarafKeselamatan"));
				this.context.put("datenow", formatter.format(now));
				this.context.put("modeSoc", disability);
				this.context.put("mode", readability);
				this.context.put("classDis", "");
				this.context.put("viewmode", "new");

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setFailBaru(HttpSession session, String idPermohonan)
	throws Exception {
		FrmPembelianSemakanData.setSemak(idPermohonan);
	}

	private void ViewFailBaru(HttpSession session, String disability,
			String readability) throws Exception {
		Vector list = new Vector();
		list.clear();
		try {
			list = FrmPembelianSemakanData.getSemak();
			this.context.put("Semak", list);
			Hashtable h = (Hashtable) list.get(0);
			log.info("view : " + h);

			if (this.mode.equalsIgnoreCase("kemaskini")){

				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						Long.parseLong(h.get("idNegeri").toString()), " disabled=\"disabled\" class=\"disabled\" "));
				this.context.put("selectKementerian", HTML.SelectKementerian(
						"socKementerian", Long.parseLong(h.get("idKementerian")
								.toString()), " disabled=\"disabled\" class=\"disabled\" "));
				this.context.put("selectAgensi", HTML.SelectAgensi("socAgensi",
						Long.parseLong(h.get("idAgensi").toString()), " disabled=\"disabled\" class=\"disabled\" "));
				this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(
						"2", "socSuburusan", Long.parseLong(h.get("idSuburusan")
								.toString()), " disabled=\"disabled\" class=\"disabled\" "));
				this.context.put("statusTanah", HTML.SelectJenisTanah("socStatusTanah", Long.parseLong(h.get("StatusTanah").toString()), " disabled=\"disabled\" class=\"disabled\" "));
				this.context.put("jenisFail", HTML.SelectTarafKeselamatan("socTarafKeselamatan", Long.parseLong(h.get("TarafKeselamatanFail").toString()), " disabled=\"disabled\" class=\"disabled\" ") );
				this.context.put("modeSoc", "");
				this.context.put("mode", "");
				this.context.put("classDis", "");

			}
			else{

				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						Long.parseLong(h.get("idNegeri").toString()), " disabled=\"disabled\" class=\"disabled\" "));
				this.context.put("selectKementerian", HTML.SelectKementerian(
						"socKementerian", Long.parseLong(h.get("idKementerian")
								.toString()), " disabled=\"disabled\" class=\"disabled\" "));
				this.context.put("selectAgensi", HTML.SelectAgensi("socAgensi",
						Long.parseLong(h.get("idAgensi").toString()), " disabled=\"disabled\" class=\"disabled\" "));
				this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(
						"2", "socSuburusan", Long.parseLong(h.get("idSuburusan")
								.toString()), " disabled=\"disabled\" class=\"disabled\" "));
				this.context.put("statusTanah", HTML.SelectJenisTanah("socStatusTanah", Long.parseLong(h.get("StatusTanah").toString()), " disabled=\"disabled\" class=\"disabled\" "));
				this.context.put("jenisFail", HTML.SelectTarafKeselamatan("socTarafKeselamatan", Long.parseLong(h.get("TarafKeselamatanFail").toString()), " disabled=\"disabled\" class=\"disabled\" ") );
				this.context.put("modeSoc", disability);
				this.context.put("mode", readability);
				String classDis = "class=\"disabled\"";
				this.context.put("classDis", classDis);
				this.context.put("viewnode","new" );

			}

			//			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
			//					Long.parseLong(h.get("idNegeri").toString()), " disabled=\"disabled\" class=\"disabled\" "));
			//			this.context.put("selectKementerian", HTML.SelectKementerian(
			//					"socKementerian", Long.parseLong(h.get("idKementerian")
			//							.toString()), " disabled=\"disabled\" class=\"disabled\" "));
			//			this.context.put("selectAgensi", HTML.SelectAgensi("socAgensi",
			//					Long.parseLong(h.get("idAgensi").toString()), " disabled=\"disabled\" class=\"disabled\" "));
			//			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(
			//					"2", "socSuburusan", Long.parseLong(h.get("idSuburusan")
			//							.toString()), " disabled=\"disabled\" class=\"disabled\" "));
			//			this.context.put("statusTanah", HTML.SelectJenisTanah("socStatusTanah", Long.parseLong(h.get("StatusTanah").toString()), " disabled=\"disabled\" class=\"disabled\" "));
			//			this.context.put("jenisFail", HTML.SelectTarafKeselamatan("socTarafKeselamatan", Long.parseLong(h.get("TarafKeselamatanFail").toString()), " disabled=\"disabled\" class=\"disabled\" ") );
			//			this.context.put("modeSoc", disability);
			//			this.context.put("mode", readability);
			//			String classDis = "class=\"disabled\"";
			//			this.context.put("classDis", classDis);

		} catch (Exception ex) {
			//log.info("PembelianProcess::ListFailBaru::Exception = " + ex);
			ex.printStackTrace();
		}
	}

	private String SimpanFailBaru(HttpSession session) throws Exception {
		long idPermohonan;

		if (getParam("idPermohonan") == "") {
			// fail baru
			Hashtable<String, Comparable> h = new Hashtable<String, Comparable>();
			h.put("idNegeri", Integer.parseInt(getParam("socNegeri")));
			h.put("idKementerian", Integer.parseInt(getParam("socKementerian")));
			h.put("idSuburusan", Integer.parseInt(getParam("socSuburusan")));
			h.put("idAgensi", Integer.parseInt(getParam("socAgensi")));
			h.put("Tajuk", getParam("txtTajuk"));
			h.put("NoFailKJP", getParam("txtNoFailKJP"));
			h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
			h.put("NoFailLain", getParam("txtNoFailLain"));
			h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
			h.put("TarafKeselamatanFail", getParam("socTarafKeselamatan"));
			h.put("StatusTanah", getParam("socStatusTanah"));
			log.info("Fir PembelianProcess::SimpanSemak:: h = " + h);
			idPermohonan = FrmPembelianSemakanData.simpan(h);
			//			result = "baru";


		} else {

			// kemaskini fail
			Hashtable<String, Comparable> h = new Hashtable<String, Comparable>();
			// int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			h.put("idFail", getParam("idFail"));
			h.put("idPermohonan", getParam("idPermohonan"));
			// h.put("idSuburusan", Integer.parseInt(getParam("socSuburusan")));
			h.put("idAgensi", Integer.parseInt(getParam("socAgensi")));
			h.put("Tajuk", getParam("txtTajuk"));
			h.put("NoFailKJP", getParam("txtNoFailKJP"));
			h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
			h.put("NoFailLain", getParam("txtNoFailLain"));
			h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
			h.put("TarafKeselamatanFail", getParam("socTarafKeselamatan"));
			h.put("StatusTanah", getParam("socStatusTanah"));

			log.info("PembelianProcess::SimpanSemak:: h = " + h);
			idPermohonan = FrmPembelianSemakanData.update(h);
			//			result = "kemaskini";
			// return idPermohonan;
		}

		return String.valueOf(idPermohonan);
	}

	// *** Pembelian Pemilik Controller
	private void DataPemilikDepan(HttpSession session, String disability,
			String readability) throws Exception {

		Vector vlist;

		try {

			vlist = new Vector();
			vlist = FrmPembelianPemilikData.getListAllPemilik();

			if(vlist.size() != 0){
				this.context.put("senaraiPemilik", vlist);
			}
			else{
				this.context.put("senaraiPemilik", "");
			}
			
			String idPermohonan = session.getAttribute("idPermohonanTanah").toString();
			session.removeAttribute("idPermohonanTanah");

			if(this.dochange.equalsIgnoreCase("negeri")){
				
				Hashtable hashPemilik = new Hashtable();
				hashPemilik.put("Nama", getParam("txtNamaPemaju"));
				hashPemilik.put("noRujukan", getParam("txtNoRuj"));
				hashPemilik.put("Alamat1", getParam("txtAlamat1"));
				hashPemilik.put("Alamat2", getParam("txtAlamat2"));
				hashPemilik.put("Alamat3", getParam("txtAlamat3"));
				hashPemilik.put("Poskod", getParam("txtPoskod"));
				hashPemilik.put("NoTel", getParam("txtNoTelefon"));
				hashPemilik.put("NoFax", getParam("txtNoFax"));
				
				Vector Pemilik = new Vector();
				
				Pemilik.addElement(hashPemilik);
				
				this.context.put("Pemilik", Pemilik);

				this.context.put("noHakMilik", SelectHakmilik("socNoHakmilik", idPermohonan, getParam("socNoHakmilik"), "", ""));

				this.context.put("selectJenisNoPB", HTML.SelectJenisNoPb(
						"socJenisNoPB", Long.parseLong(getParam("socJenisNoPB")), disability));
				
				this.context.put("selectANegeri", HTML.SelectNegeri("socANegeri",
						Long.parseLong(getParam("socANegeri")), disability,"onChange='doChangeNegeriPembeli()'"));
//				this.context.put("selectADaerah", HTML.SelectDaerah("socADaerah",
//						null, disability));
				this.context.put("selectADaerah", HTML.SelectDaerahByNegeri(getParam("socANegeri"), "socADaerah"));
				
				
			}
			else{
				
				if(mode.equalsIgnoreCase("simpanpemilik")){
					
					Hashtable hashPemilik = new Hashtable();
					hashPemilik.put("Nama", "");
					hashPemilik.put("noRujukan", "");
					hashPemilik.put("Alamat1", "");
					hashPemilik.put("Alamat2", "");
					hashPemilik.put("Alamat3", "");
					hashPemilik.put("Poskod", "");
					hashPemilik.put("NoTel", "");
					hashPemilik.put("NoFax", "");
					
					Vector Pemilik = new Vector();
					
					Pemilik.addElement(hashPemilik);
					
					
					
					this.context.put("Pemilik", Pemilik);

					this.context.put("noHakMilik", SelectHakmilik("socNoHakmilik", idPermohonan, "", "", ""));

					this.context.put("selectJenisNoPB", HTML.SelectJenisNoPb(
							"socJenisNoPB", null, disability));
					
					this.context.put("selectANegeri", HTML.SelectNegeri("socANegeri",
							null, disability,"onChange='doChangeNegeriPembeli()'"));
//					this.context.put("selectADaerah", HTML.SelectDaerah("socADaerah",
//							null, disability));
					this.context.put("selectADaerah", HTML.SelectDaerahByNegeri("0", "socADaerah"));

					
				}
				else{
					
					Hashtable hashPemilik = new Hashtable();
					hashPemilik.put("Nama", "");
					hashPemilik.put("noRujukan", "");
					hashPemilik.put("Alamat1", "");
					hashPemilik.put("Alamat2", "");
					hashPemilik.put("Alamat3", "");
					hashPemilik.put("Poskod", "");
					hashPemilik.put("NoTel", "");
					hashPemilik.put("NoFax", "");
					
					Vector Pemilik = new Vector();
					
					Pemilik.addElement(hashPemilik);
					
					
					
					this.context.put("Pemilik", Pemilik);
					
//					this.context.put("Pemilik", "");

					this.context.put("noHakMilik", SelectHakmilik("socNoHakmilik", idPermohonan, "", "", ""));

					this.context.put("selectJenisNoPB", HTML.SelectJenisNoPb(
							"socJenisNoPB", null, disability));
					
					this.context.put("selectANegeri", HTML.SelectNegeri("socANegeri",
							null, disability,"onChange='doChangeNegeriPembeli()'"));
//					this.context.put("selectADaerah", HTML.SelectDaerah("socADaerah",
//							null, disability));
					this.context.put("selectADaerah", HTML.SelectDaerahByNegeri("0", "socADaerah"));
					
					
				}

				
			}
			
			log.info("PembelianProcess::DataPemilikDepan::list " + 0000);

			

//			log.info("PembelianProcess::DataPemilikDepan::list " + 0000);
//			this.context.put("Pemilik", "");
//
//			this.context.put("noHakMilik", SelectHakmilik("socNoHakmilik", idPermohonan, "", ""));
//
//			this.context.put("selectJenisNoPB", HTML.SelectJenisNoPb(
//					"socJenisNoPB", null, disability));
//			
//			this.context.put("selectANegeri", HTML.SelectNegeri("socANegeri",
//					null, disability,"onChange='doChangeNegeriPembeli()'"));
////			this.context.put("selectADaerah", HTML.SelectDaerah("socADaerah",
////					null, disability));
//			this.context.put("selectADaerah", HTML.SelectDaerahByNegeri("0", "socADaerah"));
			

		} catch (Exception ex) {
			// log.info("PembelianProcess::DataPemilikDepan::Exception = "+ex);
			ex.printStackTrace();
		}
	}

	private void ListPemilik(HttpSession session) throws Exception {

		//		int idHakmilikurusan = Integer.parseInt(getParam("idHakmilikurusan"));
		//		String idHakmilikurusan = getParam("idHakmilikurusan");
		String idHakmilikurusan = getParam("socNoHakmilik");
		log.info("idHakmilikurusan : " + idHakmilikurusan);
		FrmPembelianPemilikData.setListPemilik(idHakmilikurusan);

	}

	private void DataPemilik(HttpSession session, String disability,
			String readability) throws Exception {
		Vector list = new Vector();
		Vector pemiliklist = new Vector();
		list.clear();
		try {

			log.info("data pemilik : read " + readability);
			log.info("data pemilik : dis " + disability);

			list = FrmPembelianPemilikData.getListPemilik();
			log.info("FrmPembelianProcessAjax::list.size() " + list.size());
			log.info("PembelianProcess::list :: " + list);
			if (list.size() != 0) {


				this.context.put("Pemilik", list);
				Hashtable hak = (Hashtable) list.get(0);
				this.context.put("selectJenisNoPB", HTML.SelectJenisNoPb(
						"socJenisNoPB", Long.parseLong(hak.get("idJenisnopb")
								.toString()), disability));
				this.context.put("selectADaerah", HTML.SelectDaerah(
						"socADaerah", Long.parseLong(hak.get("IdDaerah")
								.toString()), disability));
				this.context.put("selectANegeri", HTML.SelectNegeri(
						"socANegeri", Long.parseLong(hak.get("IdNegeri")
								.toString()), disability));
				this.context.put("noHakMilik",hak.get("NoHakmilik").toString());
				this.context.put("modeSoc", disability);
				this.context.put("mode", readability);



			} else {
				this.context.put("Pemilik", "");
				this.context.put("selectJenisNoPB", HTML
						.SelectJenisNoPb("socJenisNoPB"));
				this.context.put("selectADaerah", HTML
						.SelectDaerah("socADaerah"));
				this.context.put("selectANegeri", HTML
						.SelectNegeri("socANegeri"));
				this.context.put("modeSoc", "");
				this.context.put("mode", "");
				this.context.put("selectedTab", 2);


			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void SimpanPemilik(HttpSession session) throws Exception {
		if (getParam("idPihakberkepentingan") == "") {
			// fail baru
			Hashtable<String, Comparable> h = new Hashtable<String, Comparable>();
			//			h.put("idHakmilikurusan", getParam("idHakmilikurusan"));
			h.put("idHakmilikurusan", getParam("socNoHakmilik"));
			h.put("namaPemaju", getParam("txtNamaPemaju"));
			h.put("idJenisNoPB", getParam("socJenisNoPB"));
			h.put("noRuj", getParam("txtNoRuj"));
			h.put("alamat1", getParam("txtAlamat1"));
			h.put("alamat2", getParam("txtAlamat2"));
			h.put("alamat3", getParam("txtAlamat3"));
			h.put("poskod", getParam("txtPoskod"));
			h.put("idDaerah", (getParam("socADaerah")));
			h.put("idNegeri", (getParam("socANegeri")));
			h.put("noTelefon", getParam("txtNoTelefon"));
			h.put("noFax", getParam("txtNoFax"));
			h.put("noHakmilik", getParam("socNoHakmilik"));

			log.info("Simpan pemilik : hashtable : " + h);

			FrmPembelianPemilikData.simpan(h);
			result = "baru";

		} else {
			// kemaskini fail
			Hashtable<String, Comparable> h = new Hashtable<String, Comparable>();
			h.put("idPihakberkepentingan", getParam("idPihakberkepentingan"));
			h.put("namaPemaju", getParam("txtNamaPemaju"));
			//			h.put("idJenisNoPB", getParamAsInteger("socJenisNoPB"));
			h.put("idJenisNoPB", getParam("socJenisNoPB"));
			h.put("noRuj", getParam("txtNoRuj"));
			h.put("alamat1", getParam("txtAlamat1"));
			h.put("alamat2", getParam("txtAlamat2"));
			h.put("alamat3", getParam("txtAlamat3"));
			h.put("poskod", getParam("txtPoskod"));
			h.put("idDaerah", Integer.parseInt(getParam("socADaerah")));
			h.put("idNegeri", Integer.parseInt(getParam("socANegeri")));
			h.put("noTelefon", getParam("txtNoTelefon"));
			h.put("noFax", getParam("txtNoFax"));
			FrmPembelianPemilikData.update(h);
			result = "kemaskini";
		}
	}

	// *** Pembelian Peguam Controller
	private void ListPeguam(HttpSession session) throws Exception {

		//		int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
		String idPermohonan = getParam("idPermohonan");
		FrmPembelianPeguamData.setListPeguam(idPermohonan);

	}

	private void DataPeguam(HttpSession session, String disability,
			String readability) throws Exception {
		Vector list = new Vector();
		list.clear();
		try {
			list = FrmPembelianPeguamData.getListPeguam();
			log.info("PembelianProcess::DataPeguam::list.size() " + list.size());
			
			if (list.size() != 0) {
				
				if(this.mode.equalsIgnoreCase("peguamkemaskini")){
					
					
					if (dochange.equalsIgnoreCase("negeri")){
						
						Vector vPeguam = new Vector();
						Hashtable hashPeguam = new Hashtable();
						
						hashPeguam.put("IdPeguam", getParam("idPeguam") == null ? "" : getParam("idPeguam") );
						hashPeguam.put("NamaPeguam", getParam("txtNamaPeguam") == null ? "" : getParam("txtNamaPeguam") );
						hashPeguam.put("noRujukan", getParam("txtKodPeguam") == null ? "" : getParam("txtKodPeguam") );
						hashPeguam.put("Alamat1", getParam("txtAlamat1") == null ? "" : getParam("txtAlamat1") );
						hashPeguam.put("Alamat2", getParam("txtAlamat2") == null ? "" : getParam("txtAlamat2") );
						hashPeguam.put("Alamat3", getParam("txtAlamat3") == null ? "" : getParam("txtAlamat3") );
						hashPeguam.put("Poskod", getParam("txtPoskod") == null ? "" : getParam("txtPoskod") );
						hashPeguam.put("NoTel", getParam("txtNoTelefon") == null ? "" : getParam("txtNoTelefon") );
						hashPeguam.put("NoFax", getParam("txtNoFax") == null ? "" : getParam("txtNoFax") );
						
						vPeguam.add(hashPeguam);
						
						this.context.put("Peguam", vPeguam);
						
						Hashtable peg = (Hashtable) list.get(0);
						log.info("PembelianProcess::DataPeguam::list " + list);
//						
						this.context.put("selectBNegeri", HTML.SelectNegeri("socBNegeri", Long.parseLong(getParam("socBNegeri")), "", "onChange='doChangeNegeriPeguamKemaskini()'"));
//						this.context.put("selectBDaerah", HTML.SelectDaerahByNegeri(getParam("socBNegeri"), "socBDaerah", Long.parseLong(getParam("socBDaerah")), ""));
						this.context.put("selectBDaerah", HTML.SelectDaerahByNegeri(getParam("socBNegeri"), "socBDaerah"));
						
						this.context.put("modeSoc", "");
						this.context.put("mode", "");
						this.context.put("classDis", "");
						
					}
					else{
						
						this.context.put("Peguam", list);
						Hashtable peg = (Hashtable) list.get(0);
						log.info("PembelianProcess::DataPeguam::list " + list);
//						this.context.put("selectBNegeri", HTML.SelectNegeri(
//								"socBNegeri", Long.parseLong(peg.get("IdNegeri")
//										.toString()), ""));
//						this.context.put("selectBDaerah", HTML.SelectDaerahByNegeri(peg.get("IdNegeri").toString(), "socBDaerah", Long.parseLong(peg.get("IdDaerah").toString()), ""));
						
//						this.context.put("selectBNegeri", HTML.SelectNegeri("socBNegeri", Long.parseLong(getParam("socBNegeri")), "", "onChange='doChangeNegeriPeguamKemaskini()'"));
//						this.context.put("selectBDaerah", HTML.SelectDaerahByNegeri(getParam("socBNegeri"), "socBDaerah", Long.parseLong(getParam("socBDaerah")), ""));
						
						this.context.put("selectBNegeri", HTML.SelectNegeri("socBNegeri", Long.parseLong(peg.get("IdNegeri").toString()), "", "onChange='doChangeNegeriPeguamKemaskini()'"));
						this.context.put("selectBDaerah", HTML.SelectDaerahByNegeri(getParam("socBNegeri"), "socBDaerah", Long.parseLong(peg.get("IdDaerah").toString()), ""));
						
						this.context.put("modeSoc", "");
						this.context.put("mode", "");
						this.context.put("classDis", "");
						
					}
					
				
					log.info("peguam kemaskini");
					
				}else{
					
					this.context.put("Peguam", list);
					Hashtable peg = (Hashtable) list.get(0);
					log.info("PembelianProcess::DataPeguam::list " + list);
					this.context.put("selectBNegeri", HTML.SelectNegeri(
							"socBNegeri", Long.parseLong(peg.get("IdNegeri")
									.toString()), disability + " class='disabled'"));
					this.context.put("selectBDaerah", HTML.SelectDaerahByNegeri(peg.get("IdNegeri").toString(), "socBDaerah", Long.parseLong(peg.get("IdDaerah").toString()), disability + " class='disabled'"));
					
					this.context.put("modeSoc", disability);
					this.context.put("mode", readability);
					this.context.put("classDis", "class='disabled'");
					
				}
				

				

			} else {

				//kat sini kite implement doChange 
				if(dochange.equalsIgnoreCase("negeri")){
					
					Vector vPeguam = new Vector();
					Hashtable hashPeguam = new Hashtable();
					
					hashPeguam.put("IdPeguam", getParam("idPeguam") == null ? "" : getParam("idPeguam") );
					hashPeguam.put("NamaPeguam", getParam("txtNamaPeguam") == null ? "" : getParam("txtNamaPeguam") );
					hashPeguam.put("noRujukan", getParam("txtKodPeguam") == null ? "" : getParam("txtKodPeguam") );
					hashPeguam.put("Alamat1", getParam("txtAlamat1") == null ? "" : getParam("txtAlamat1") );
					hashPeguam.put("Alamat2", getParam("txtAlamat2") == null ? "" : getParam("txtAlamat2") );
					hashPeguam.put("Alamat3", getParam("txtAlamat3") == null ? "" : getParam("txtAlamat3") );
					hashPeguam.put("Poskod", getParam("txtPoskod") == null ? "" : getParam("txtPoskod") );
					hashPeguam.put("NoTel", getParam("txtNoTelefon") == null ? "" : getParam("txtNoTelefon") );
					hashPeguam.put("NoFax", getParam("txtNoFax") == null ? "" : getParam("txtNoFax") );
					
					vPeguam.add(hashPeguam);
					
					this.context.put("Peguam", vPeguam);
					
					this.context.put("selectBNegeri", HTML.SelectNegeri("socBNegeri", Long.parseLong(getParam("socBNegeri")), "", "onChange='doChangeNegeriPeguam()'"));
					this.context.put("selectBDaerah", HTML.SelectDaerahByNegeri(getParam("socBNegeri"), "socBDaerah", 0L, ""));

					
					log.info("doChange peguam");

				}
				else{

					//					this.context.put("selectBNegeri", HTML
					//					.SelectNegeri("socBNegeri"));
					this.context.put("selectBNegeri", HTML.SelectNegeri("socBNegeri", "onChange='doChangeNegeriPeguam()'"));

					//			this.context.put("selectBDaerah", HTML
					//					.SelectDaerah("socBDaerah"));
					this.context.put("selectBDaerah", HTML.SelectDaerahByNegeri("0", "socBDaerah", 0L, ""));
					


				}

				this.context.put("classDis", "");
				this.context.put("modeSoc", "");
				this.context.put("mode", "");
			}
		} catch (Exception ex) {
			// log.info("PembelianProcess::DataPeguam::Exception = "+ex);
			ex.printStackTrace();
		}
	}

	private void SimpanPeguam(HttpSession session) throws Exception {
		if (getParam("idPeguam") == "") {
			// fail baru
			Hashtable<String, Comparable> h = new Hashtable<String, Comparable>();
			h.put("idPermohonan", getParam("idPermohonan"));
			h.put("nama", getParam("txtNamaPeguam"));
			h.put("kod", getParam("txtKodPeguam"));
			h.put("alamat1", getParam("txtAlamat1"));
			h.put("alamat2", getParam("txtAlamat2"));
			h.put("alamat3", getParam("txtAlamat3"));
			h.put("poskod", getParam("txtPoskod"));
			h.put("idDaerah", getParam("socBDaerah"));
			h.put("idNegeri", getParam("socBNegeri"));
			h.put("noTelefon", getParam("txtNoTelefon"));
			h.put("noFax", getParam("txtNoFax"));
			
			log.info("hash peguam : " + h);
			
			FrmPembelianPeguamData.simpan(h);
			result = "baru";
		} else {
			// kemaskini fail
			Hashtable<String, Comparable> h = new Hashtable<String, Comparable>();
			h.put("idPeguam", getParam("idPeguam"));
			h.put("nama", getParam("txtNamaPeguam"));
			h.put("kod", getParam("txtKodPeguam"));
			h.put("alamat1", getParam("txtAlamat1"));
			h.put("alamat2", getParam("txtAlamat2"));
			h.put("alamat3", getParam("txtAlamat3"));
			h.put("poskod", getParam("txtPoskod"));
			h.put("idDaerah", Integer.parseInt(getParam("socBDaerah")));
			h.put("idNegeri", Integer.parseInt(getParam("socBNegeri")));
			h.put("noTelefon", getParam("txtNoTelefon"));
			h.put("noFax", getParam("txtNoFax"));
			FrmPembelianPeguamData.update(h);
			result = "kemaskini";
		}
	}

	// *** Pembelian Semakan Controller
	public void setSemak(HttpSession session, String idPermohonan)
	throws Exception {
		FrmPembelianSemakan1Data.setSemak(idPermohonan);
	}

	private void ViewSemak(HttpSession session, String disability,
			String readability) throws Exception {
		Vector list = new Vector();
		list.clear();
		try {
			list = FrmPembelianSemakan1Data.getSemak();
			this.context.put("Semak", list);
			Hashtable h = (Hashtable) list.get(0);

			if(this.mode.equalsIgnoreCase("kemaskini")){

				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						Long.parseLong(h.get("idNegeri").toString()), " disabled=\"disabled\" class=\"disabled\" "));
				this.context.put("selectKementerian", HTML.SelectKementerian(
						"socKementerian", Long.parseLong(h.get("idKementerian")
								.toString()), " disabled=\"disabled\" class=\"disabled\" "));
				this.context.put("selectAgensi", HTML.SelectAgensi("socAgensi",
						Long.parseLong(h.get("idAgensi").toString()), " disabled=\"disabled\" class=\"disabled\" "));
				this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(
						"2", "socSuburusan", Long.parseLong(h.get("idSuburusan")
								.toString()), " disabled=\"disabled\" class=\"disabled\" "));
				this.context.put("modeSoc", disability);
				this.context.put("mode", readability);
				this.context.put("viewmode", "update");
				this.context.put("classDis", "");


			}
			else{

				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						Long.parseLong(h.get("idNegeri").toString()), " disabled=\"disabled\" class=\"disabled\" "));
				this.context.put("selectKementerian", HTML.SelectKementerian(
						"socKementerian", Long.parseLong(h.get("idKementerian")
								.toString()), " disabled=\"disabled\" class=\"disabled\" "));
				this.context.put("selectAgensi", HTML.SelectAgensi("socAgensi",
						Long.parseLong(h.get("idAgensi").toString()), " disabled=\"disabled\" class=\"disabled\" "));
				this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(
						"2", "socSuburusan", Long.parseLong(h.get("idSuburusan")
								.toString()), " disabled=\"disabled\" class=\"disabled\" "));
				this.context.put("modeSoc", disability);
				this.context.put("mode", readability);
				this.context.put("viewmode", "view");
				this.context.put("classDis", "class=\'disabled\'");

			}

			//			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
			//					Long.parseLong(h.get("idNegeri").toString()), " disabled=\"disabled\" class=\"disabled\" "));
			//			this.context.put("selectKementerian", HTML.SelectKementerian(
			//					"socKementerian", Long.parseLong(h.get("idKementerian")
			//							.toString()), " disabled=\"disabled\" class=\"disabled\" "));
			//			this.context.put("selectAgensi", HTML.SelectAgensi("socAgensi",
			//					Long.parseLong(h.get("idAgensi").toString()), " disabled=\"disabled\" class=\"disabled\" "));
			//			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(
			//					"2", "socSuburusan", Long.parseLong(h.get("idSuburusan")
			//							.toString()), " disabled=\"disabled\" class=\"disabled\" "));
			//			this.context.put("modeSoc", disability);
			//			this.context.put("mode", readability);
			//			this.context.put("viewmode", "view");
			//			this.context.put("classDis", "class=\'disabled\'");


		} catch (Exception ex) {
			// log.info("PembelianProcess::ListSemakan1::Exception = "+ex);
			ex.printStackTrace();
		}
	}

	private void SimpanSemak(HttpSession session) throws Exception {
		// kemaskini fail
		Hashtable<String, Comparable> h = new Hashtable<String, Comparable>();
		//		h.put("idFail", Integer.parseInt(getParam("idFail")));
		h.put("idFail", getParam("idFail"));
		//		h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
		h.put("idPermohonan", getParam("idPermohonan"));
		h.put("idAgensi", Integer.parseInt(getParam("socAgensi")));
		h.put("Tajuk", getParam("txtTajuk"));
		h.put("NoFailKJP", getParam("txtNoFailKJP"));
		h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
		h.put("NoFailLain", getParam("txtNoFailLain"));
		h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
		log.info("PembelianProcess::SimpanSemak1:: h = " + h);
		FrmPembelianSemakan1Data.update(h);
		result = "kemaskini";
	}

	// *** Pembelian Tanah Controller
	public void setTanah(HttpSession session, String idPermohonan, long idDaerah,
			long idMukim) throws Exception {
		FrmPembelianTanahData.setTanah(Long.parseLong(idPermohonan), idDaerah, idMukim);
//		FrmPembelianTanahData.setUlasan(Long.parseLong(idPermohonan));
	}
	
	public void setUlasanJKPTG(HttpSession session, String idPermohonan) throws Exception {
		FrmPembelianTanahData.setUlasan(Long.parseLong(idPermohonan));
	}

	// private void ViewTanah(HttpSession session, long idNegeri, long idDaerah,
	// long idMukim) throws Exception
	private void ViewTanah(HttpSession session, long idNegeri) throws Exception {
		Vector list = new Vector();
		list.clear();
		try {
			list = FrmPembelianTanahData.getTanah();
			if (list.size() == 0) {
				this.context.put("Tanah", "");
			} else {
				this.context.put("Tanah", list);
			}

			if (this.dochange.equalsIgnoreCase("daerah")) {

				if (getParam("socDaerah") != ""
					|| getParam("socDaerah") != null) {
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(
							String.valueOf(idNegeri), "socDaerah", Long
							.parseLong(getParam("socDaerah")), "",
					"onchange=\"doChangeDaerah()\""));
					this.context.put("selectMukim", HTML.SelectMukimByDaerah(
							getParam("socDaerah"), "socMukim"));
				} else {
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(
							String.valueOf(idNegeri), "socDaerah", Long
							.parseLong("0"), "",
					"onchange=\"doChangeDaerah()\""));
					this.context.put("selectMukim", HTML.SelectMukimByDaerah(
							"", "socMukim"));

				}

			} else {
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(
						String.valueOf(idNegeri), "socDaerah", Long
						.parseLong("0"), "",
				"onchange=\"doChangeDaerah()\""));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah("",
				"socMukim"));

			}

			// this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(""+idNegeri,"socDaerah",idDaerah,null));
			// this.context.put("selectMukim",HTML.SelectMukim("socMukim",idMukim,null));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void ViewTanahUlasan(HttpSession session, String disability,
			String readability) throws Exception {
		Vector listUlasan = new Vector();
		listUlasan.clear();
		try {
			listUlasan = FrmPembelianTanahData.getUlasan();
			if (listUlasan.size() == 0) {
				this.context.put("IdUlasankptg", "");
				this.context.put("TarikhHantar", "");
				this.context.put("Syarat", "");
				this.context.put("Ulasan", "");
				this.context.put("modeSoc", "");
				this.context.put("mode", "");
				this.context.put("classDis", "");
				log.info("PembelianProcess::TanahUlasan::null");

			} else {
				
				if(this.mode.equalsIgnoreCase("perakuankptgkemaskini")){

					Hashtable h = (Hashtable) listUlasan.get(0);
					this.context.put("IdUlasankptg", h.get("IdUlasankptg")
							.toString());
					this.context.put("TarikhHantar", h.get("TarikhHantar")
							.toString());
					this.context.put("Syarat", h.get("Syarat").toString());
					this.context.put("Ulasan", h.get("Ulasan").toString());
					this.context.put("modeSoc", "");
					this.context.put("mode", "");
					this.context.put("classDis", "");
					log.info("PembelianProcess::TanahUlasan::kemaskini");
					
				}
				else{
					//view
					Hashtable h = (Hashtable) listUlasan.get(0);
					this.context.put("IdUlasankptg", h.get("IdUlasankptg")
							.toString());
					this.context.put("TarikhHantar", h.get("TarikhHantar")
							.toString());
					this.context.put("Syarat", h.get("Syarat").toString());
					this.context.put("Ulasan", h.get("Ulasan").toString());
					this.context.put("modeSoc", disability);
					this.context.put("mode", readability);
					this.context.put("classDis", "class='disabled'");
					log.info("PembelianProcess::TanahUlasan::not null");
					
				}
				
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void SimpanTanahUlasan(HttpSession session) throws Exception {
		if (getParam("IdUlasankptg") == "") {
			// //fail baru
			Hashtable<String, Comparable> h = new Hashtable<String, Comparable>();
			h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
			h.put("tarikhHantar", getParam("txdTarikhHantar"));
			h.put("syarat", getParam("socPerakuan"));
			h.put("ulasan", getParam("Ulasan"));
			FrmPembelianTanahData.simpanUlasan(h);
			result = "baru";

		} else {
			// kemaskini fail
			Hashtable<String, Comparable> h = new Hashtable<String, Comparable>();
			h.put("idUlasankptg", getParam("IdUlasankptg"));
			h.put("idPermohonan", getParam("idPermohonan"));
			h.put("tarikhHantar", getParam("txdTarikhHantar"));
			h.put("syarat", getParam("socPerakuan"));
			h.put("ulasan", getParam("Ulasan"));
			FrmPembelianTanahData.updateUlasan(h);
			result = "kemaskini";

		}
	}

	// *** Pembelian Tanah Bangunan Controller
	public void setTBangun(HttpSession session, long idPermohonan,
			long idDaerah, long idMukim) throws Exception {
		FrmPembelianTBangunData.setTBangun(idPermohonan, idDaerah, idMukim);
	}

	private void ViewTBangun(HttpSession session, long idNegeri, long idDaerah,
			long idMukim) throws Exception {
		Vector list = new Vector();
		list.clear();
		try {
			list = FrmPembelianTBangunData.getTBangun();
			if (list.size() == 0)
				this.context.put("TBangun", "");
			else
				this.context.put("TBangun", list);
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(""
					+ idNegeri, "socDaerahB", idDaerah, null));
			this.context.put("selectMukim", HTML.SelectMukim("socMukimB",
					idMukim, null));
			this.context.put("DaerahB", idDaerah);
			this.context.put("MukimB", idMukim);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String getNamaNegeri(String idNegeri) throws Exception {

		Vector v = null;
		Tblrujnegeri n = null;
		try {
			v = new Vector();
			v = DB.getNegeri(idNegeri);
			n = (Tblrujnegeri) v.get(0);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return n.getNamaNegeri();
	}

	public String getNamaKementerian(String idKementerian) throws Exception {

		Vector v = null;
		Tblrujkementerian k = null;
		String namaKementerian = "";
		try {
			v = new Vector();
			v = DB.getKementerian(idKementerian);
			k = (Tblrujkementerian) v.get(0);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return k.getNamaKementerian();

	}

	public String getNamaAgensi(String idagensi) throws Exception {

		Vector v = null;
		Tblrujagensi a = null;
		String agensiName = "";
		try {
			v = new Vector();
			v = DB.getAgensi(idagensi);
			a = (Tblrujagensi) v.get(0);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return a.getNamaAgensi();

	}

	public String getNamaSubUrusan(String idSuburusan) throws Exception {

		Tblrujsuburusan s = null;

		try {
			s = new Tblrujsuburusan();
			s = DB.getSubUrusan(idSuburusan);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return s.getNamaSuburusan();

	}

	/*
	 * copy from pembelianpopup.java
	 */

	// *** Pembelian Tanah Controller
	private void ListTanah(HttpSession session, long idPermohonan,
			long idHakmilikurusan) throws Exception {
		FrmPembelianTanahData.setTanahPop(idPermohonan, idHakmilikurusan);
	}

	private void DataTanah(HttpSession session, String disability,
			String readability) throws Exception {
		Vector list = new Vector();
		list.clear();
		try {
			list = FrmPembelianTanahData.getTanahPop();
			if (list.size() != 0) {

				if(mode.equalsIgnoreCase("kemaskini")){
					this.context.put("Tanah", list);
					Hashtable h = (Hashtable) list.get(0);
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
							Long.parseLong(h.get("IdNegeri").toString()),
					"style='width:200px;' "));
					this.context.put("selectDaerah", HTML.SelectDaerah("socDaerah",
							Long.parseLong(h.get("IdDaerah").toString()),
					"style='width:200px;' "));
					this.context.put("selectMukim", HTML
							.SelectMukim("socMukim", Long.parseLong(h
									.get("IdMukim").toString()), "style='width:200px;' "));
					this.context.put("selectJenisHakmilik", HTML
							.SelectJenisHakmilik("socJenisHakmilik",
									Long.parseLong(h.get("IdJenishakmilik")
											.toString()), "style='width:200px;' "));
					this.context.put("selectLot", HTML.SelectLot("socLot", Long
							.parseLong(h.get("IdLot").toString()), "style='width:200px;' "));
					this.context.put("selectLuas", HTML.SelectLuas("socLuas", Long
							.parseLong(h.get("IdLuas").toString()), "style='width:200px;' "));
					this.context.put("selectRizab", HTML
							.SelectRizab("socRizab", Long.parseLong(h
									.get("IdRizab").toString()), "style='width:200px;' "));
					this.context.put("selectKategori", HTML.SelectKategori(
							"socKategori", Long.parseLong(h.get("IdKategori")
									.toString()), "style='width:200px;' "));


					this.context.put("classDis", "");
					this.context.put("mode", "");
					this.context.put("pagemode", "kemaskini");


				}
				else{
					this.context.put("Tanah", list);
					Hashtable h = (Hashtable) list.get(0);
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
							Long.parseLong(h.get("IdNegeri").toString()),
					"disabled=\"disabled\" class=\"disabled\" style='width:200px;' "));
					this.context.put("selectDaerah", HTML.SelectDaerah("socDaerah",
							Long.parseLong(h.get("IdDaerah").toString()),
					"disabled=\"disabled\" class=\"disabled\" style='width:200px;' "));
					this.context.put("selectMukim", HTML
							.SelectMukim("socMukim", Long.parseLong(h
									.get("IdMukim").toString()), "disabled=\"disabled\" class=\"disabled\" style='width:200px;' "));
					this.context.put("selectJenisHakmilik", HTML
							.SelectJenisHakmilik("socJenisHakmilik",
									Long.parseLong(h.get("IdJenishakmilik")
											.toString()), "disabled=\"disabled\" class=\"disabled\" style='width:200px;' "));
					this.context.put("selectLot", HTML.SelectLot("socLot", Long
							.parseLong(h.get("IdLot").toString()), "disabled=\"disabled\" class=\"disabled\" style='width:200px;' "));
					this.context.put("selectLuas", HTML.SelectLuas("socLuas", Long
							.parseLong(h.get("IdLuas").toString()), "disabled=\"disabled\" class=\"disabled\" style='width:200px;' "));
					this.context.put("selectRizab", HTML
							.SelectRizab("socRizab", Long.parseLong(h
									.get("IdRizab").toString()), "disabled=\"disabled\" class=\"disabled\" style='width:200px;' "));
					this.context.put("selectKategori", HTML.SelectKategori(
							"socKategori", Long.parseLong(h.get("IdKategori")
									.toString()), "disabled=\"disabled\" class=\"disabled\" style='width:200px;' "));

					String classDis = "class=\"disabled\"";
					this.context.put("classDis", classDis);
					this.context.put("mode", disability);
					this.context.put("pagemode", "simpan");


				}


			} else {

				this.context.put("Tanah", "");
				log.info("PembelianPopup::DataTanah:: list.size() = "
						+ list.size());
				this.context
				.put("selectNegeri", HTML.SelectNegeri("socNegeri"));
				this.context
				.put("selectDaerah", HTML.SelectDaerah("socDaerah"));
				this.context.put("selectMukim", HTML.SelectMukim("socMukim"));
				this.context.put("selectJenisHakmilik", HTML
						.SelectJenisHakmilik("socJenisHakmilik"));
				this.context.put("selectLot", HTML.SelectLot("socLot"));
				this.context.put("selectLuas", HTML.SelectLuas("socLuas"));
				this.context.put("selectRizab", HTML.SelectRizab("socRizab"));
				this.context.put("selectKategori", HTML
						.SelectKategori("socKategori"));
				this.context.put("modeSoc", "");
				this.context.put("mode", "");
				this.context.put("classDis", "");
				this.context.put("pagemode", "baru");

			}
		} catch (Exception ex) {
			log.info("PembelianPopup::DataTanah::Exception = " + ex);
			ex.printStackTrace();
		}
	}

	private void DataTanahBaru(HttpSession session, String disability,
			String readability) throws Exception {
		String mode = this.mode;
		Vector list = new Vector();
		list.clear();
		try {

			this.context.put("Tanah", "");
			log.info("PembelianPopup::DataTanah:: list.size() = "+ list.size());
			log.info("DAtaTanahBaru mode : " + mode);

			//			if (mode.equalsIgnoreCase("dochangenegeri")) {
			//
			//				log.info("DAtaTanahBaru :: doChangeNegeri selected");
			//
			//				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
			//						Long.parseLong(getParam("socNegeri")), "",
			//						"onchange = \"fPM_doChangeNegeri()\" "));
			//				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(
			//						getParam("socNegeri"), "socDaerahC", Long
			//								.parseLong("0"), "",
			//						"onchange = \"fPM_doChangeDaerah()\" "));
			//				this.context.put("selectMukim", HTML.SelectMukimByDaerah("",
			//						"socMukimC", Long.parseLong("0"), ""));
			//				this.context.put("selectJenisHakmilik", HTML
			//						.SelectJenisHakmilik("socJenisHakmilik"));
			//				this.context.put("selectLot", HTML.SelectLot("socLot"));
			//				this.context.put("selectLuas", HTML.SelectLuas("socLuas"));
			//				this.context.put("selectRizab", HTML.SelectRizab("socRizab"));
			//				this.context.put("selectKategori", HTML
			//						.SelectKategori("socKategori"));
			//
			//			}

			if (mode.equalsIgnoreCase("dochangedaerah")) {

				log.info("DAtaTanahBaru :: dochangedaerah selected");

				//				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
				//						Long.parseLong(getParam("socNegeri")), "",
				//						"onchange = \"fPM_doChangeNegeri()\" "));
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						Long.parseLong(getParam("socNegeri")), " disabled=\"disabled\" class=\"disabled\" style=\"width:200px;\" "));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(
						getParam("socNegeri"), "socDaerahC", Long
						.parseLong(getParam("socDaerahC")), " style=\"width:200px;\" ",
				"onchange = \"fPM_doChangeDaerah()\" "));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah(
						getParam("socDaerahC"), "socMukimC", Long
						.parseLong("0"), " style=\"width:200px;\" "));
				this.context.put("selectJenisHakmilik", HTML
						.SelectJenisHakmilik("socJenisHakmilik",Long.parseLong("0"),"style=\"width:200px;\""));
				this.context.put("selectLot", HTML.SelectLot("socLot",Long.parseLong("0")," style=\"width:200px;\" "));
				this.context.put("selectLuas", HTML.SelectLuas("socLuas", Long.parseLong("0"),"style=\"width:200px;\""));
				this.context.put("selectRizab", HTML.SelectRizab("socRizab",Long.parseLong("0"),"style=\"width:200px;\""));
				this.context.put("selectKategori", HTML
						.SelectKategori("socKategori",Long.parseLong("0"),"style=\"width:200px;\""));

				this.context.put("pagemode", "baru");
				this.context.put("classDis", "");
				this.context.put("mode", "");


			} else {

				String negeri = getParam("negeri");

				log.info("DAtaTanahBaru :: initial ");
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(negeri), "disabled=\"disabled\" class=\"disabled\" style=\"width:200px;\" "));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(negeri,
						"socDaerahC", Long.parseLong("0")," style=\"width:200px;\" ", "onchange = \"fPM_doChangeDaerah()\" "));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah("0",
						"socMukimC", Long.parseLong("0"), " style=\"width:200px;\" "));
				this.context.put("selectJenisHakmilik", HTML
						.SelectJenisHakmilik("socJenisHakmilik",Long.parseLong("0")," style=\"width:200px;\" "));
				this.context.put("selectLot", HTML.SelectLot("socLot",Long.parseLong("0")," style=\"width:200px;\" "));
				this.context.put("selectLuas", HTML.SelectLuas("socLuas",Long.parseLong("0")," style=\"width:200px;\" "));
				this.context.put("selectRizab", HTML.SelectRizab("socRizab",Long.parseLong("0")," style=\"width:200px;\" "));
				this.context.put("selectKategori", HTML
						.SelectKategori("socKategori",Long.parseLong("0"),"style=\"width:200px;\" "));

				this.context.put("pagemode", "baru");
				this.context.put("classDis", "");
				this.context.put("mode", "");

			}

			// //
			// this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"));
			// this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",
			// "onchange = \"doChangeNegeri()\" "));
			// //
			// this.context.put("selectDaerah",HTML.SelectDaerah("socDaerah"));
			// this.context.put("selectDaerah",HTML.SelectDaerahByNegeri("",
			// "socNegeri", Long.parseLong("0"), ""));
			// // this.context.put("selectMukim",HTML.SelectMukim("socMukim"));
			// this.context.put("selectMukim",HTML.SelectMukimByDaerah("",
			// "socMukim", Long.parseLong("0"), ""));
			// this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik"));
			// this.context.put("selectLot",HTML.SelectLot("socLot"));
			// this.context.put("selectLuas",HTML.SelectLuas("socLuas"));
			// this.context.put("selectRizab",HTML.SelectRizab("socRizab"));
			// this.context.put("selectKategori",HTML.SelectKategori("socKategori"));

		} catch (Exception ex) {
			log.info("PembelianPopup::DataTanahBaru::Exception = " + ex);
			ex.printStackTrace();
		}
	}

	private String SimpanTanah(HttpSession session, long idPermohonan)
	throws Exception {

		long idHakmilikurusan = 0;
		log.info("PembelianPopup::SimpanTanah");
		log.info("SimpanTanah : " + idPermohonan);
		if (getParam("idHakmilikurusan") == ""
			|| Long.parseLong(getParam("idHakmilikurusan")) == 0) {
			// fail baru
			Hashtable h = new Hashtable();
			h.put("idPermohonan", idPermohonan);
			h.put("socNegeri", getParam("socNegeri"));

			h.put("socDaerah", getParam("socDaerahC"));

			h.put("socMukim", getParam("socMukimC"));
			h.put("socJenisHakmilik", getParam("socJenisHakmilik"));
			h.put("NoHakmilik", getParam("txtNoHakmilik"));
			h.put("NoLot", getParam("txtNoLot"));
			h.put("socLot", getParam("socLot"));
			h.put("TarikhTerima", getParam("txdTarikhTerima"));
			h.put("Luas", getParam("txtLuas"));
			h.put("socLuas", getParam("socLuas"));
			h.put("NoPelan", getParam("txtNoPelan"));
			h.put("socRizab", getParam("socRizab"));
			h.put("socKategori", getParam("socKategori"));
			h.put("TarikhLuput", getParam("txdTarikhLuput"));
			// h.put("statusTanah", getParam("socStatus"));
			idHakmilikurusan = FrmPembelianTanahData.simpan(h);
			result = "baru";
			log.info("PembelianPopup::SimpanTanah::Baru::h = " + h);

		} else {
			// kemaskini fail
			Hashtable h = new Hashtable();
			idHakmilikurusan = Long.parseLong(getParam("idHakmilikurusan"));
			h.put("idPermohonan", idPermohonan);
			h.put("idHakmilikurusan", idHakmilikurusan);
			h.put("socNegeri", Integer.parseInt(getParam("socNegeri")));
			h.put("socDaerah", getParam("socDaerah"));
			h.put("socMukim", getParam("socMukim"));
			h.put("socJenisHakmilik", getParam("socJenisHakmilik"));
			h.put("NoHakmilik", getParam("txtNoHakmilik"));
			h.put("NoLot", getParam("txtNoLot"));
			h.put("socLot", getParam("socLot"));
			h.put("TarikhTerima", getParam("txdTarikhTerima"));
			h.put("Luas", getParam("txtLuas"));
			h.put("socLuas", getParam("socLuas"));
			h.put("NoPelan", getParam("txtNoPelan"));
			h.put("socRizab", getParam("socRizab"));
			h.put("socKategori", getParam("socKategori"));
			h.put("TarikhLuput", getParam("txdTarikhLuput"));
			// h.put("statusTanah", getParam("socStatus"));
			FrmPembelianTanahData.update(h);
			result = "kemaskini";
			log.info("PembelianPopup::SimpanTanah::Kemaskini::h = " + h);
		}
		log.info("SimpanTanah :: idHakmilikurusan : " + idHakmilikurusan);
		//		return idHakmilikurusan;
		return String.valueOf(idHakmilikurusan);

	}

	// *** Pembelian Tanah & Bangunan Controller
	private void ListTBangun(HttpSession session, long idPermohonan,
			long idHakmilikurusan) throws Exception {
		try {
			FrmPembelianTBangunData.setTBangunPop(idPermohonan,
					idHakmilikurusan);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void DataTBangun(HttpSession session, long idPermohonan,
			String disability, String readability) throws Exception {
		Vector list = new Vector();
		list.clear();
		try {
			list = FrmPembelianTBangunData.getTBangunPop();

			if (list.size() != 0) {
				this.context.put("TBangun", list);
				Hashtable h = (Hashtable) list.get(0);
				this.context.put("selectNoHakmilik", FrmPembelianTBangunData
						.SelectNoHakmilik(idPermohonan, "SocNoHakmilik", h.get(
						"NoHakmilik").toString(), disability));
				this.context.put("modeSoc", disability);
				this.context.put("mode", readability);

			} else {

				this.context.put("TBangun", "");
				log.info("PembelianPopup::DataTBangun:: list.size() = "
						+ list.size());
				this.context.put("selectNoHakmilik",
						FrmPembelianTBangunData.SelectNoHakmilik(idPermohonan,
								"SocNoHakmilik", "", ""));
				this.context.put("modeSoc", "");
				this.context.put("mode", "");

			}

		} catch (Exception ex) {
			log.info("PembelianPopup::DataTBangun::Exception = " + ex);
			ex.printStackTrace();
		}
	}

	private void DataTBangunBaru(HttpSession session, long idPermohonan,
			String disability, String readability) throws Exception {
		Vector list = new Vector();
		list.clear();
		try {
			this.context.put("TBangun", "");
			log.info("PembelianPopup::DataTBangun:: list.size() = "
					+ list.size());
			this.context.put("selectNoHakmilik", FrmPembelianTBangunData
					.SelectNoHakmilik(idPermohonan, "SocNoHakmilik", "", ""));
			this.context.put("modeSoc", "");
			this.context.put("mode", "");

		} catch (Exception ex) {
			log.info("PembelianPopup::DataTBangunBaru::Exception = " + ex);
			ex.printStackTrace();
		}
	}

	private String SimpanTBangun(HttpSession session, String idPermohonan)
	throws Exception {
		long idHakmilikurusan = 0L;
		log.info("PembelianPopup::SimpanTanah");
		if (getParam("idHakmilikurusan") == "") {
			// fail baru
			Hashtable h = new Hashtable();
			h.put("idPermohonan", idPermohonan);
			h.put("NoHakmilik", getParam("SocNoHakmilik"));
			h.put("NoPetak", getParam("txtNoPetak"));
			h.put("NoBangunan", getParam("txtNoBangunan"));
			h.put("NoTingkat", getParam("txtNoTingkat"));
			idHakmilikurusan = FrmPembelianTBangunData.simpan(h);
			result = "baru";
			log.info("PembelianPopup::SimpanTBangun::Baru::h = " + h);

		} else {
			// kemaskini fail
			Hashtable h = new Hashtable();
			idHakmilikurusan = Integer.parseInt(getParam("idHakmilikurusan"));
			h.put("idPermohonan", idPermohonan);
			h.put("idHakmilikurusan", idHakmilikurusan);
			h.put("NoPetak", getParam("txtNoPetak"));
			h.put("NoBangunan", getParam("txtNoBangunan"));
			h.put("NoTingkat", getParam("txtNoTingkat"));
			FrmPembelianTBangunData.update(h);
			result = "kemaskini";
			log.info("PembelianPopup::SimpanTBangun::Kemaskini::h = " + h);
			// return idHakmilikurusan;
		}
		//		return idHakmilikurusan;
		return String.valueOf(idHakmilikurusan);

	}

	/*
	 * end of pembelian popup.java
	 */

	
	
	
	public void TabKelulusanBaru(HttpSession session, String readability, String disability){
		
		this.context.put("semakanKem", "");
		this.context.put("noRujSurKem", "");
		this.context.put("tarikhSurKem", "");
		this.context.put("catatanKementerian", "");
		this.context.put("selectAsalPKP", "");
		this.context.put("noRujSurPKP", "");
		this.context.put("tarikhSurPKP", "");
		this.context.put("catatanPKP", "");
		this.context.put("pagemode", "baru");
	}
	
	public void TabKelulusanSimpan(HttpSession session) throws Exception{
		
		if (getParam("idPermohonan") == "") {
			
			/*
			 *  new data (this data will go to table TBLHTPULASANKJP
			 *  
			 *  idUlasanKJP (PK)
			 *  idPermohonan (FK)
			 *  tarikhHantar
			 *  tarikhTerima
			 *  tarikhSuratKeputusan
			 *  ulasan
			 *  StatusKeputusan
			 *  idMasuk
			 *  tarikhmasuk
			 *  tarikhkemaskini
			 *  idKemaskini
			 *  id_db
			 *  
			 */
			
			Hashtable<String, String> h = new Hashtable<String, String>();
			
			String userId = session.getAttribute("_ekptg_user_id").toString();

			
			h.put("idPermohonan", getParam(""));
			h.put("semakanKem", getParam(""));
			h.put("noRujSurKem", getParam(""));
			h.put("tarikhSurKem", getParam(""));
			h.put("catatanKementerian", getParam(""));
			h.put("selectAsalPKP", getParam(""));
			h.put("noRujSurPKP", getParam(""));
			h.put("tarikhSurPKP", getParam(""));
			h.put("catatanPKP", getParam(""));
			h.put("userId", userId);
			
			
			
			
//			idPermohonan = FrmPembelianSemakanData.simpan(h);
//			



		} else {
			
			// kemaskini fail
			Hashtable<String, Comparable> h = new Hashtable<String, Comparable>();
			
			h.put("idFail", getParam("idFail"));
			h.put("idPermohonan", getParam("idPermohonan"));
			
			h.put("idAgensi", Integer.parseInt(getParam("socAgensi")));
			h.put("Tajuk", getParam("txtTajuk"));
			h.put("NoFailKJP", getParam("txtNoFailKJP"));
			h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
			h.put("NoFailLain", getParam("txtNoFailLain"));
			h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
			h.put("TarafKeselamatanFail", getParam("socTarafKeselamatan"));
			h.put("StatusTanah", getParam("socStatusTanah"));
			
			log.info("PembelianProcess::SimpanSemak:: h = " + h);
//			idPermohonan = FrmPembelianSemakanData.update(h);
			result = "kemaskini";
	
		}
		
//		return String.valueOf(idPermohonan);
		
	}
	
	public void TabKelulusanView(HttpSession session, String readability, String disability) throws Exception{
		
		Vector list = new Vector();
		list.clear();
		try {
			
			//get the data from here 
//			list = FrmPembelianSemakanData.getSemak();
//			Hashtable h = (Hashtable) list.get(0);
			
			this.context.put("semakanKem", "");
			this.context.put("noRujSurKem", "");
			this.context.put("tarikhSurKem", "");
			this.context.put("catatanKementerian", "");
			this.context.put("selectAsalPKP", "");
			this.context.put("noRujSurPKP", "");
			this.context.put("tarikhSurPKP", "");
			this.context.put("catatanPKP", "");
			this.context.put("pagemode", "baru");
			this.context.put("modeSoc", disability);
			this.context.put("mode", readability);
			String classDis = "class=\"disabled\"";
			this.context.put("classDis", classDis);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void TabDrafPembelianBaru(HttpSession session, String readability, String disability){
		
		
	}
	
	
	
	
//	public void TabKelulusanBaru(HttpSession session, String readability, String disability){
//		
//		this.context.put("semakanKem", "");
//		this.context.put("noRujSurKem", "");
//		this.context.put("tarikhSurKem", "");
//		this.context.put("catatanKementerian", "");
//		this.context.put("selectAsalPKP", "");
//		this.context.put("noRujSurPKP", "");
//		this.context.put("tarikhSurPKP", "");
//		this.context.put("catatanPKP", "");
//		this.context.put("pagemode", "baru");
//	}
//	
//	public void TabKelulusanSimpan(HttpSession session, String idPermohonan) throws Exception{
//		
//		if (getParam("idHakmilikurusan") == "") {
//			
//			/*
//			 *  new data (this data will go to table TBLHTPULASANKJP
//			 *  
//			 *  idUlasanKJP (PK)
//			 *  idPermohonan (FK)
//			 *  tarikhHantar
//			 *  tarikhTerima
//			 *  tarikhSuratKeputusan
//			 *  ulasan
//			 *  StatusKeputusan
//			 *  idMasuk
//			 *  tarikhmasuk
//			 *  tarikhkemaskini
//			 *  idKemaskini
//			 *  id_db
//			 *  
//			 */
//			
//			Hashtable<String, String> h = new Hashtable<String, String>();
//			
//			String userId = session.getAttribute("_ekptg_user_id").toString();
//
//			
//			h.put("idPermohonan", this.idPermohonan);
//			
//			h.put("semakanKem", getParam("semakanKem"));
//			h.put("noRujSurKem", getParam("txtNoSuratRujukan"));
//			h.put("tarikhSurKem", getParam("txdTarikhSuratKem"));
//			h.put("catatanKementerian", getParam("txtcatatanKem"));
//			h.put("selectAsalPKP", getParam("selectAsalPKP"));
//			h.put("noRujSurPKP", getParam("txtNoSuratRujukanPKP"));
//			h.put("tarikhSurPKP", getParam("txdTarikhSuratPKP"));
//			h.put("catatanPKP", getParam("txtcatatanPKP"));
//			h.put("userId", userId);
//			log.info("TabKelulusanSimpan : hash : " + h);
//			frmPembelianKelulusan.SimpanKelulusan(h);
//			
////			idPermohonan = FrmPembelianSemakanData.simpan(h);
//		
//
//
//
//		} else {
//			
//			// kemaskini fail
//			Hashtable<String, Comparable> h = new Hashtable<String, Comparable>();
//			
//			h.put("idFail", getParam("idFail"));
//			h.put("idPermohonan", getParam("idPermohonan"));
//			
//			h.put("idAgensi", Integer.parseInt(getParam("socAgensi")));
//			h.put("Tajuk", getParam("txtTajuk"));
//			h.put("NoFailKJP", getParam("txtNoFailKJP"));
//			h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
//			h.put("NoFailLain", getParam("txtNoFailLain"));
//			h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
//			h.put("TarafKeselamatanFail", getParam("socTarafKeselamatan"));
//			h.put("StatusTanah", getParam("socStatusTanah"));
//			
//			log.info("PembelianProcess::SimpanSemak:: h = " + h);
////			idPermohonan = FrmPembelianSemakanData.update(h);
//			result = "kemaskini";
//	
//		}
//		
////		return String.valueOf(idPermohonan);
//		
//	}
//	
//	public void TabKelulusanView(HttpSession session, String readability, String disability) throws Exception{
//		
//		Vector list = new Vector();
//		list.clear();
//		try {
//			
//			//get the data from here 
////			list = FrmPembelianSemakanData.getSemak();
////			Hashtable h = (Hashtable) list.get(0);
//			
//			this.context.put("semakanKem", "");
//			this.context.put("noRujSurKem", "");
//			this.context.put("tarikhSurKem", "");
//			this.context.put("catatanKementerian", "");
//			this.context.put("selectAsalPKP", "");
//			this.context.put("noRujSurPKP", "");
//			this.context.put("tarikhSurPKP", "");
//			this.context.put("catatanPKP", "");
//			this.context.put("pagemode", "baru");
//			this.context.put("modeSoc", disability);
//			this.context.put("mode", readability);
//			String classDis = "class=\"disabled\"";
//			this.context.put("classDis", classDis);
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		
//	}
//	
//	public void TabDrafPembelianBaru(HttpSession session, String readability, String disability){
//		
//		
//	}
	

	public String SelectHakmilik(String selectName, String idPermohonan, String selectedValue, String disability, String jsFunction){

		StringBuffer sb = null;
		Vector nohakmilik = null;
		String selected = null;
		try{
			nohakmilik = new Vector();
			FrmPembelianTanahData.setListHakmilik(idPermohonan);
			nohakmilik = FrmPembelianTanahData.getListHakmilik();


			sb = new StringBuffer();
			sb.append("<select name=\"" + selectName + "\" ");
			if(disability != null){
				sb.append(disability);
			}

			if(jsFunction != null){
				sb.append(jsFunction);
			}

			sb.append(" > ");
			sb.append("<option value=>Sila Pilih No Hakmilik </option>\n");

			
			
			for(int i = 0; i < nohakmilik.size(); i++){
				Hashtable h = new Hashtable();
				h = (Hashtable)nohakmilik.get(i);
				String noHakM = (String)h.get("NoHakmilik");
				String idHakmilikUrusan = (String)h.get("idHakMilikUrusan");

				log.info("selectedvalue : " + selectedValue);
				log.info("value : " + idHakmilikUrusan );
				
				if(selectedValue.equals(idHakmilikUrusan)){
					selected = "selected";
					log.info("inside");
				}
				else{
					selected = "";
				}
				
				sb.append("<option " + selected + " value=\""+ idHakmilikUrusan + "\" >" + noHakM + " </option>\n");
			}
			sb.append("</select>");


		}catch(Exception e){
			e.printStackTrace();
		}



		return sb.toString();

	}

	public void ListAllPemilik(String idPermohonan){
		try{
			FrmPembelianPemilikData.ListAllPemilik(idPermohonan);

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void ListPemilikById(String idPihakKepentingan) throws Exception{
		try{
			FrmPembelianPemilikData.ListPemilikById(idPihakKepentingan);

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	/*
	 * controller for tab draf kelulusan
	 */

	 public void TabPerjanjianDrafKelulusanBaru(HttpSession session, String readability, String disability){

	 }

	 public void TabPerjanjianDrafKelulusanView(HttpSession session, String readability, String disability){

	 }

	 public void TabPerjanjianDrafKelulusanSimpan(HttpSession session) throws Exception{
		 try{
			 Hashtable h = new Hashtable();

			 log.info("perjanjian userid : " + session.getAttribute("_ekptg_user_id") );

			 h.put("idPermohonan", this.idPermohonan);
			 h.put("tarikhTerima", getParam("txdTarikhTerima"));
			 h.put("tarikhHantar", getParam("txdTarikhHantar"));
			 h.put("ulasan", getParam("txtcatatan"));
			 h.put("userID", session.getAttribute("_ekptg_user_id"));

			 log.info("TabPerjanjianDrafKelulusanSimpan : hash : " + h );

			 FrmPembelianPerjanjianJualBeliData.SimpanDrafKelulusan(h);

		 }catch(Exception e){
			 e.printStackTrace();
		 }


	 }

	 //table involved for draf - tblhtpderafperjanjian
	 public void TabPerjanjianDrafKelulusanData(HttpSession session, String readability, String disability) throws Exception{

		 Vector vlist;
		 try{
			 vlist = new Vector();
			 vlist = FrmPembelianPerjanjianJualBeliData.getSenaraiDrafPerjanjian();
			 

			 if(vlist != null && vlist.size() != 0){
				 this.context.put("SenaraiDraf", vlist);

			 }
			 else{
				 this.context.put("SenaraiDraf", "");
			 }
			 
			 if(mode.equalsIgnoreCase("drafview")){
				 //new
				 if(dochange.equalsIgnoreCase("newDraf")){
					 Vector Draf = new Vector();
					 Hashtable hashDraf = new Hashtable();
					 
					 hashDraf.put("tarikhTerima", "");
					 hashDraf.put("tarikhHantar", "");
					 hashDraf.put("ulasan", "");
					 
					 Draf.addElement(hashDraf);

					 this.context.put("Draf", Draf);
					 this.context.put("classDis", "");
					 this.context.put("mode", "");
					 this.context.put("addmode", "newDraf");
					 
				 }
				 //view
				 else if (dochange.equalsIgnoreCase("view")){
					 Vector Draf = new Vector();
					 Draf = FrmPembelianPerjanjianJualBeliData.getMaklumatDraf();

					 this.context.put("Draf", Draf);
					 this.context.put("classDis", "class='disabled'");
					 this.context.put("mode", "disabled");
				
					 this.context.put("addmode", "viewDraf");
				 }
				 else{
					 //kemaskini
					 Vector Draf = new Vector();
					 Draf = FrmPembelianPerjanjianJualBeliData.getMaklumatDraf();

					 this.context.put("Draf", Draf);
					 this.context.put("classDis", "");
					 this.context.put("mode", "");
				
					 this.context.put("addmode", "updateDraf");
				 }
				 
			 }
			 
			
			 
//			 if()
			 
//			 else{
//				 this.context.put("Draf", "");
//				 this.context.put("tarikhTerimaDraf", "");
//				 this.context.put("tarikhHantarDraf", "");
//				 this.context.put("Ulasan", "");
//				 this.context.put("classDis", "");
//				 this.context.put("disabled", disability);
//			 }






		 }catch(Exception e){
			 e.printStackTrace();
		 }



	 }
	 
	 public void TabPerjanjianDrafKelulusanUpdate(HttpSession session) throws Exception{
		 try{
			 Hashtable h = new Hashtable();

			 log.info("perjanjian userid : " + session.getAttribute("_ekptg_user_id") );

			 h.put("idPermohonan", this.idPermohonan);
			 h.put("tarikhTerima", getParam("txdTarikhTerima"));
			 h.put("tarikhHantar", getParam("txdTarikhHantar"));
			 h.put("ulasan", getParam("txtcatatan"));
			 h.put("idDraf", this.idDraf);
			 h.put("userID", session.getAttribute("_ekptg_user_id"));

			 log.info("TabPerjanjianDrafKelulusanSimpanUpdate : hash : " + h );

			 FrmPembelianPerjanjianJualBeliData.SimpanUpdateDraf(h);

		 }catch(Exception e){
			 e.printStackTrace();
		 }

	 }

	 /*
	  * end of tab draf kelulusan
	  */


	 /*
	  * controller for tab perjanjian senarai semak
	  */

	 public void TabPerjanjianSenaraiSemakBaru(HttpSession session, String readability, String disability ){

	 }

	 public void TabPerjanjianSenaraiSemakView(HttpSession session, String readability, String disability ){

	 }

	 public void TabPerjanjianSenaraiSemakSimpan(HttpSession session, String readability, String disability ){

	 }

	 public void TabPerjanjianSenaraiSemakData(HttpSession session, String readability, String disability ){

	 }

	 /*
	  * end of tab senarai semak
	  */


	 /*
	  * controller for tab pembelian baru
	  */	

	 public void TabPerjanjianPembelianBaru(HttpSession session, String readability, String disability){

	 }

	 public void TabPerjanjianPembelianView(HttpSession session, String readability, String disability) throws Exception{


		 try{


		 }catch(Exception e){
			 e.printStackTrace();
		 }

	 }

	 public void TabPerjanjianPembelianSimpan(HttpSession session) throws Exception{
		 try{
			 Hashtable h = new Hashtable();

			 //perjanjian jual beli
			 h.put("NoKontrak", getParam("txtNoKontrak"));
			 h.put("TarikhPerjanjian", getParam("txtTarikhPerjanjian"));
			 //                h.put("TempohBulan", getParam("txtTempohBulan"));
			 h.put("TarikhSempurna14A", getParam("txtTarikhSempurna14A"));
			 h.put("HargaTambahan", getParam("txtHargaTambahan"));
			 h.put("HargaBeli", getParam("txtHargaBeli"));
			 h.put("TarikhPTP", getParam("txtTarikhPTP"));
			 //                h.put("tarikhSerahBangunan", getParam("txtarikhSerahBangunan"));
			 h.put("jumlahHakmilik", getParam("txtjumlahHakmilik"));
			 h.put("bilUnitBangunan", getParam("txtbilUnitBangunan"));
			 h.put("nilaiTanah", getParam("txtnilaiTanah"));
			 h.put("nilaiBangunan", getParam("txtnilaiBangunan"));
			 h.put("idPermohonan", this.idPermohonan);
			 h.put("userID", session.getAttribute("_ekptg_user_id"));
			 
			 //untuk perjanjian tambahan
			 /*
			 h.put("tarikhPTambahan", getParam("txttarikhPTambahan"));
			 h.put("Sebab", getParam("socSebab"));
			 h.put("Alasan", getParam("Alasan"));
			 h.put("TempohPerjanjian", getParam("txtTempohPerjanjian"));
			 h.put("TarikhSerahPTambahan", getParam("txTarikhSerahPTambahan"));
			 h.put("idPermohonan", this.idPermohonan);
			 h.put("userID", session.getAttribute("_ekptg_user_id"));
			 */

			 log.info("TabPerjanjianPembelianSimpan : hashtable : " + h);

			 FrmPembelianPerjanjianJualBeliData.SimpanPerjanjianJualBeli(h);

		 }catch(Exception e){
			 e.printStackTrace();
		 }

	 }
	 
	 public void TabPerjanjianPembelianSimpanUpdate(HttpSession session) throws Exception{
		 try{
			 Hashtable h = new Hashtable();

			 //perjanjian jual beli
			 h.put("idPerjanjian", getParam("idPerjanjian"));
			 h.put("NoKontrak", getParam("txtNoKontrak"));
			 h.put("TarikhPerjanjian", getParam("txtTarikhPerjanjian"));
			 h.put("TarikhSempurna14A", getParam("txtTarikhSempurna14A"));
			 h.put("HargaTambahan", getParam("txtHargaTambahan"));
			 h.put("HargaBeli", getParam("txtHargaBeli"));
			 h.put("TarikhPTP", getParam("txtTarikhPTP"));
			 h.put("jumlahHakmilik", getParam("txtjumlahHakmilik"));
			 h.put("bilUnitBangunan", getParam("txtbilUnitBangunan"));
			 h.put("nilaiTanah", getParam("txtnilaiTanah"));
			 h.put("nilaiBangunan", getParam("txtnilaiBangunan"));
			 h.put("idPermohonan", this.idPermohonan);
			 h.put("userID", session.getAttribute("_ekptg_user_id"));
			 
			 log.info("pembelian perjanjian update : " + h);
			 
			 FrmPembelianPerjanjianJualBeliData.SimpanUpdatePerjanjianJualBeli(h);
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
	 }

	 public void TabPerjanjianPembelianData(HttpSession session, String readability, String disability) throws Exception{

		 Vector vList;
		 try{
			 vList = new Vector();
			 vList = FrmPembelianPerjanjianJualBeliData.getListPerjanjianJualBeli();

			 if(vList.size() != 0){
				 
				 if(mode.equalsIgnoreCase("pembeliankemaskini")){
					 
					 this.context.put("PJualBeli", vList);
					 this.context.put("mode", "");
					 this.context.put("classDis", "");
					 this.context.put("addmode", "update");
					 
				 }
				 else{
					 String classDis = "class=\"disabled\"";
					 this.context.put("PJualBeli", vList);
					 this.context.put("mode", "disabled");
					 this.context.put("classDis", classDis);
					 this.context.put("addmode", "view");
					 
				 }

				

			 }else{
				 this.context.put("PJualBeli", "");
				 this.context.put("noKontrak", "");
				 this.context.put("tarikhPerjanjian", "");
				 this.context.put("tempohBulan", "");
				 this.context.put("tarikhSempurna14A", "");
				 this.context.put("HargaTambahan", "");
				 this.context.put("HargaBeli", "");
				 this.context.put("tarikhPTP", "");
				 this.context.put("tarikhSerahBangunan", "");
				 this.context.put("jumlahHakmilik", "");
				 this.context.put("bilUnitBangunan", "");
				 this.context.put("nilaiTanah", "");
				 this.context.put("nilaiBangunan", "");
				 this.context.put("tarikhPTambahan", "");
				 this.context.put("socSebab", "");
				 this.context.put("alasan", "");
				 this.context.put("TempohPerjanjian", "");
				 this.context.put("TarikhSerahPTambahan", "");
				 this.context.put("addmode", "new");

			 }



		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }

	 /*
	  * end of tab pembelian baru
	  */

	 //tab nama penjual
	 public void TabMaklumatNamaPenjualData(HttpSession session, String disability) throws Exception{

		 try{
			 Vector v = new Vector();
			 v = FrmPembelianPenjualData.getMaklumatPenjual();
			 
			 //query back data from database
			 if(v != null && v.size() != 0 ){
				 
				 Hashtable h = new Hashtable();
				 h = (Hashtable)v.get(0);
				 
				 this.context.put("Penjual", v);
				 this.context.put("selectNegeriP", HTML.SelectNegeri("socNegeri",Long.parseLong((String)h.get("idNegeri")), " disabled class='disabled' ", ""));
				 this.context.put("selectDaerahP", HTML.SelectDaerahByNegeri((String)h.get("idNegeri"), "socDaerah", Long.parseLong((String)h.get("idDaerah")), " disabled class='disabled' ", ""));
				 this.context.put("mode", "disabled");
				 this.context.put("classDis", "class='disabled'");
				 
			 }
			 else{
				 
				 if (getParam("PenjualSama") != null && getParam("PenjualSama") != ""){
					 if(this.dochange.equalsIgnoreCase("doChangePenjual")){
						 v = FrmPembelianPemilikData.getListPemilikByID();
						 if(v.isEmpty()){
							 this.context.put("Penjual", "");
						 }
						 else{
							 this.context.put("Penjual", v);
							 Hashtable h = (Hashtable) v.get(0);
							 String idPihakBerkepentingan = (String) h.get("idPihakKepentingan");
							 this.context.put("selectPenjual", SelectNamaPenjual("socPenjual",idPihakBerkepentingan, "", "onchange=\"doChangePenjual()\""));
							 this.context.put("selectNegeriP", HTML.SelectNegeri("socNegeri",Long.parseLong((String)h.get("idNegeri")), " disabled class='disabled' ", ""));
							 this.context.put("selectDaerahP", HTML.SelectDaerahByNegeri((String)h.get("idNegeri"), "socDaerah", Long.parseLong((String)h.get("idDaerah")), " disabled class='disabled' ", ""));
							 
						 }

					 }else{
						 this.context.put("selectPenjual", SelectNamaPenjual("socPenjual","", "", "onchange=\"doChangePenjual()\""));
						 this.context.put("selectNegeriP", HTML.SelectNegeri("socNegeri", 0L, "disabled class='disabled' ", ""));
						 this.context.put("selectDaerahP", HTML.SelectDaerahByNegeri("0", "socDaerah", 0L, "disabled class='disabled' ", ""));
						 this.context.put("mode", "disabled");
						 this.context.put("classDis", "class='disabled'");
					 }




				 }else{
					 
					 if(this.dochange.equalsIgnoreCase("negeri")){
						 Vector penjual = new Vector();
						 Hashtable hashPenjual = new Hashtable();
						 
						 hashPenjual.put("nama", getParam("txtNamaPenjual"));
						 hashPenjual.put("alamat1", getParam("txtAlamat1"));
						 hashPenjual.put("alamat2", getParam("txtAlamat2"));
						 hashPenjual.put("alamat3", getParam("txtAlamat3"));
						 hashPenjual.put("poskod", getParam("txtPoskod"));
						 hashPenjual.put("tel", getParam("txtNoTelefon"));
						 hashPenjual.put("fax", getParam("txtNoFax"));
						 hashPenjual.put("noPA", getParam("txtNoPA"));
						
						 penjual.add(hashPenjual);
						 
						 this.context.put("Penjual", penjual);
						 this.context.put("selectNegeriP", HTML.SelectNegeri("socNegeri", Long.parseLong(getParam("socNegeri")), "", "onChange='doChangeNegeriPenjual()'"));
						 this.context.put("selectDaerahP", HTML.SelectDaerahByNegeri(getParam("socNegeri"), "socDaerah", 0L, "", ""));
					 }
					 
					 else{
						 
						 this.context.put("Penjual", "");
						 this.context.put("NamaPenjual", "");
						 this.context.put("Alamat1Penjual", "");
						 this.context.put("Alamat2Penjual", "");
						 this.context.put("Alamat3Penjual", "");
						 this.context.put("PoskodPenjual", "");
						 this.context.put("NoTelPenjual", "");
						 this.context.put("NoFaxPenjual", "");
						 this.context.put("noIc", "");
						 this.context.put("noPA", "");
						 this.context.put("selectNegeriP", HTML.SelectNegeri("socNegeri", 0L, "", "onChange='doChangeNegeriPenjual()'"));
						 this.context.put("selectDaerahP", HTML.SelectDaerahByNegeri("0", "socDaerah", 0L, "", ""));
						 this.context.put("mode", "");
						 this.context.put("classDis", "");			 
					 }

				 }
				 
			 }






		 }catch(Exception e){
			 e.printStackTrace();
		 }

	 }

	 public void TabMaklumatPenjualSimpan(HttpSession session) throws Exception{
		 try{

			 Hashtable h = new Hashtable();

			 if(getParam("PenjualSama") != null && getParam("PenjualSama")!= ""){
				 h.put("flagPenjual","Y");
			 }
			 else{
				 h.put("flagPenjual","N");
			 }
			 
			 h.put("namaPenjual",getParam("txtNamaPenjual") );
			 h.put("ic", getParam("txtKodPenjual"));
			 h.put("alamat1", getParam("txtAlamat1") );
			 h.put("alamat2", getParam("txtAlamat2"));
			 h.put("alamat3", getParam("txtAlamat3"));
			 h.put("poskod", getParam("txtPoskod"));
			 h.put("negeri",getParam("socNegeri") );
			 h.put("daerah", getParam("socDaerah") );
			 h.put("tel",getParam("txtNoTelefon") );
			 h.put("fax",getParam("txtNoFax") );
			 h.put("noPA", getParam("txtNoPA"));
			 h.put("userID", session.getAttribute("_ekptg_user_id") );
			 h.put("idPihakKepentinganPenjual",getParam("socPenjual") );
			 h.put("idPermohonan", this.idPermohonan);

			 FrmPembelianPenjualData.SimpanPenjual(h);

		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }

	 public String SelectNamaPenjual(String selectName,String idPihakBerkepentingan, String disability, String jsFunction) throws Exception{

		 StringBuffer sb = null;
		 try{
			 sb = new StringBuffer();
			 sb.append("<select name=\"" + selectName + "\" ");

			 if(!disability.equals(null)){
				 sb.append(disability);
			 }

			 if(!jsFunction.equals(null)){
				 sb.append(jsFunction);
			 }

			 sb.append(" > ");
			 sb.append("<option value=>Sila Pilih Nama Penjual </option>\n");

			 Vector vPeminjam = new Vector();
			 vPeminjam = FrmPembelianPemilikData.getListAllPemilik();

			 for(int i = 0; i < vPeminjam.size(); i++){
				 Hashtable h = new Hashtable();
				 h = (Hashtable)vPeminjam.get(i);
				 String namaPenjual = (String)h.get("nama");
				 String idPihakKepentingan = (String)h.get("idPihakKepentingan");

				 log.info("fir idP : " + idPihakKepentingan);
				 log.info("fir idB : " + idPihakBerkepentingan);
				 if(idPihakKepentingan.equalsIgnoreCase(idPihakBerkepentingan)){
					 sb.append("<option value=\""+ idPihakKepentingan + "\" selected=\"selected\">" + namaPenjual + " </option>\n");
				 }
				 else{
					 sb.append("<option value=\""+ idPihakKepentingan + "\">" + namaPenjual + " </option>\n");
				 }


			 }
			 sb.append("</select>");

		 }catch(Exception e){
			 e.printStackTrace();
		 }

		 return sb.toString();

	 }


	 public void SenaraiSemakView(String disability) throws Exception{

		 String status = "new";
		 try{

			 if(this.mode.equalsIgnoreCase("senaraisemakview")){
				 
				 Vector semakList = FrmSemakan.getSenaraiSemakan("frmPembelianDraf");
				 FrmSemakan semak = new FrmSemakan();
				 Hashtable h = null;
				 
				 for(int i=0; i < semakList.size(); i++){
					 
					 h = new Hashtable();
					 h = (Hashtable)semakList.get(i);
					 
					 if(semak.isSemakan(this.idPermohonan, h.get("id").toString())){
						 log.info("checked");
						 status = "view";
						 break;
					 }

				 }
				 
				 if(status.equalsIgnoreCase("view")){
					 this.context.put("senaraiSemakanList", semakList);
					 this.context.put("semakclass", new FrmSemakan());
					 this.context.put("modeSoc", disability);
					 this.context.put("classDis", "class='disabled'"); 
					 log.info("status : " + status);
				 }
				 else{
					 //new
					 
					 this.context.put("senaraiSemakanList", semakList);
					 this.context.put("semakclass", new FrmSemakan());
					 this.context.put("modeSoc", "");
					 log.info("status : " + status);

				 }
				 
				 
				 
			 }
			 
			 //kemaskini
			 else{
				 this.context.put("senaraiSemakanList", FrmSemakan.getSenaraiSemakan("frmPembelianDraf"));
				 this.context.put("semakclass", new FrmSemakan());
				 this.context.put("modeSoc", disability);
				 
				 
			 }

		 }catch(Exception e){
			 log.info("Error : " + e.getMessage());
			 e.printStackTrace();
		 }

	 }
	 
	 public void SetMaklumatPenjual(String idPermohonan) throws Exception{
		 try{
			 FrmPembelianPenjualData.setMaklumatPenjual(idPermohonan);
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
	 }
	 
	 public void SetSenaraiDraf(String idPermohonan) throws Exception{
		 try{
			 FrmPembelianPerjanjianJualBeliData.setListDrafKelulusan(idPermohonan);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 
	 public void SetMaklumatDraf(String idDraf) throws Exception{
		 try{
			 FrmPembelianPerjanjianJualBeliData.setMaklumatDraf(idDraf);
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 
	 public void TabPerjanjianTambahanData(HttpSession session, String readonly, String disability) throws Exception{
		 Vector vlist;
		 try{
			 vlist = new Vector();
			 vlist = FrmPembelianPerjanjianJualBeliData.getSenaraiPerjanjianTambahan();
			 

			 if(vlist != null && vlist.size() != 0){
				 this.context.put("SenaraiPTambahan", vlist);

			 }
			 else{
				 this.context.put("SenaraiPTambahan", "");
			 }
			 
			 if(mode.equalsIgnoreCase("ptambahanview")){
				 //new
				 if(dochange.equalsIgnoreCase("newPTambahan")){
					 Vector PTambahan = new Vector();
					 Hashtable hashPtambahan = new Hashtable();
					 
					 hashPtambahan.put("tarikhPTambahan", "");
					 hashPtambahan.put("tarikhHantar", "");
					 hashPtambahan.put("alasan", "");
					 hashPtambahan.put("sebab", "");
					 hashPtambahan.put("tempoh", "");
					 hashPtambahan.put("TarikhAkhirPTambahan", "");
			
					 
					 PTambahan.addElement(hashPtambahan);

					 this.context.put("PTambahan", PTambahan);
					 this.context.put("classDis", "");
					 this.context.put("mode", "");
					 this.context.put("addmode", "newPTambahan");
					 
				 }
				 //view
				 else if (dochange.equalsIgnoreCase("view")){
					 Vector PTambahan = new Vector();
					 PTambahan = FrmPembelianPerjanjianJualBeliData.getMaklumatPerjanjianTambahan();

					 this.context.put("PTambahan", PTambahan);
					 this.context.put("classDis", "class='disabled'");
					 this.context.put("mode", "disabled");
				
					 this.context.put("addmode", "viewPTambahan");
				 }
				 else{
					 //kemaskini
					 Vector PTambahan = new Vector();
					 PTambahan = FrmPembelianPerjanjianJualBeliData.getMaklumatPerjanjianTambahan();

					 this.context.put("PTambahan", PTambahan);
					 this.context.put("classDis", "");
					 this.context.put("mode", "");
				
					 this.context.put("addmode", "updatePTambahan");
				 }
				 
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 
	 public void SetMaklumatPerjanjianTambahan(String idPerjanjianTambahan) throws Exception{
		 try{
			 FrmPembelianPerjanjianJualBeliData.setMaklumatPerjanjianTambahan(idPerjanjianTambahan);
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 
	 public void TabPerjanjianTambahanSimpan(HttpSession session) throws Exception{
		 try{
			 Hashtable h = new Hashtable();
			 
			 //untuk perjanjian tambahan
			 
			 h.put("tarikhPTambahan", getParam("txttarikhPTambahan"));
			 h.put("sebab", getParam("socSebab"));
			 h.put("alasan", getParam("Alasan"));
			 h.put("TempohPerjanjian", getParam("txtTempohPerjanjian"));
			 h.put("tarikhAkhir", getParam("txTarikhAkhirPTambahan"));
			 //h.put("TarikhSerahPTambahan", getParam("txTarikhSerahPTambahan"));
			 h.put("tarikhHantar", getParam("txTarikhHantar"));
			 h.put("idPermohonan", this.idPermohonan);
			 h.put("userID", session.getAttribute("_ekptg_user_id"));
			 

			 log.info("TabPerjanjianTambahanSimpan : hashtable : " + h);

			 FrmPembelianPerjanjianJualBeliData.SimpanMaklumatPerjanjianTambahan(h);
			 
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
 
	 }
	 
	 public void TabPerjanjianTambahanUpdate(HttpSession session)throws Exception{
		 try{
			 Hashtable h = new Hashtable();
			 
			 //untuk perjanjian tambahan
			 
			 h.put("tarikhPTambahan", getParam("txttarikhPTambahan"));
			 h.put("Sebab", getParam("socSebab"));
			 h.put("Alasan", getParam("Alasan"));
			 h.put("TempohPerjanjian", getParam("txtTempohPerjanjian"));
			 h.put("TarikhSerahPTambahan", getParam("txTarikhSerahPTambahan"));
			 h.put("tarikhHantar", getParam("txTarikhHantar"));
			 h.put("idPermohonan", this.idPermohonan);
			 h.put("idPerjanjianTambahan", this.idPerjanjianTambahan);
			 h.put("userID", session.getAttribute("_ekptg_user_id"));
			 

			 log.info("TabPerjanjianTambahanSimpanUpdate : hashtable : " + h);

			 FrmPembelianPerjanjianJualBeliData.UpdateMaklumatPerjanjianTambahan(h);
			 
		 }catch (Exception e) {
			e.printStackTrace();
		}
	 }
		 
		 
	 





}// close class
