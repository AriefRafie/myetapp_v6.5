package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.util.DateUtil;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.pfd.FrmTukaranStatus;
import ekptg.model.ppk.FrmBorangPSek17OnlineData;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8Data;
import ekptg.model.ppk.FrmPrmhnnSek8Data;
import ekptg.model.ppk.FrmPrmhnnSek8SecaraOnlineData;
import ekptg.model.ppk.FrmPrmhnnSek8SenaraiHTATHData;
import ekptg.model.ppk.FrmPrmhnnStatusPengunaOnlineData;
import ekptg.model.ppk.online.IStatusPermohonan;
import ekptg.model.ppk.online.StatusPermohonanFacade;

public class FrmDraffPermohonanOnlinePPK extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4843489266845962934L;

	static Logger myLogger = Logger.getLogger(FrmPrmhnnStatusPengunaOnline.class);

	FrmPrmhnnSek8SecaraOnlineData logiconline = new FrmPrmhnnSek8SecaraOnlineData();
	FrmBorangPSek17OnlineData logic = new FrmBorangPSek17OnlineData();
	FrmPrmhnnSek8Data logic3 = new FrmPrmhnnSek8Data();

	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String usid = "";
		usid = (String) session.getAttribute("_ekptg_user_id");

		String vm = "app/ppk/frmPrmhnnStatusPengunaOnline.jsp";
		// String submit = getParam("hitButt");
		String mode = getParam("mode");
		String submit = getParam("command"); // First Level - default by
												// AjaxBasedModule Call
		String action = getParam("action"); // Second Level
		myLogger.info("submit command : "+submit+",action : "+action);;
	
		String disability1 = "";
		String disability2 = "";
		String readability1 = "";
		String readability2 = "";
		this.context.put("Util", new lebah.util.Util());
		Vector senaraiFail = new Vector();
		
		String USER_LOGIN_SYSTEM = (String)session.getAttribute("_portal_login");

		this.context.put("skrin_deraf", "yes");

		// this.context.put("skrin_online_popup","");

		if ("check_kp".equals(submit)) {
			String NoKPBaru = getParam("txtNoKPBaru1a")
					+ getParam("txtNoKPBaru2a") + getParam("txtNoKPBaru3a");
			String NoKPLama = getParam("txtNoKPLamaa");
			String NoKPLain = getParam("txtNoKPLaina");

			myLogger.info("test 1111");
			// check samada data pemohon wujud atau tidak
			int getResultChkId = logiconline.checkPemohon(NoKPBaru, NoKPLama,
					NoKPLain);
			myLogger.info("getResultChkId :: " + getResultChkId);
			if (getResultChkId == 0) {
				String idAlert = "1";
				this.context.put("IdAlert", idAlert);
			} else {
				senaraiFail = FrmPrmhnnStatusPengunaOnlineData
						.getSenaraiTugasan("", (String) session
								.getAttribute("_ekptg_user_id"),
								(String) session.getAttribute("_portal_role"),
								getParam("kppemohon"), getParam("kpsimati"), USER_LOGIN_SYSTEM,
								"yes");

				this.context.put("senaraitugasan", senaraiFail);
				vm = "app/ppk/frmPrmhnnStatusPengunaOnline.jsp";
			}
		} else if ("doChanges".equals(submit)) {

			vm = "app/ppk/frmPrmhnnStatusPengunaOnline.jsp";
			setupPage(session, action, senaraiFail);
			
		} else if ("doHapus".equals(submit)){
			
			myLogger.info("try ::idFail= "+getParam("idFail")+"idPermohonan="+getParam("idPermohonan") );
			//this.context.put("senaraitugasan", senaraiFail);
			Hashtable fifah = new Hashtable();
			fifah.put("id_permohonan", getParam("idPermohonan"));
			fifah.put("id_fail",  getParam("idFail"));
			fifah.put("id_masuk", usid);
			FrmTukaranStatus.hapusfail(fifah);
			
//			AuditTrail.logActivity("999","2",this,session,"DEL","NO PERMOHONAN ["+NoFail+"] DIHAPUSKAN");
						
		}
		// daftar dan kemaskini seksyen17
		else if ("check_kp17".equals(submit)) {
			String typez = getParam("typez");

			myLogger.info("typez :: " + typez);

			String NoKPBaru = "";
			String NoKPLama = "";
			String NoKPLain = "";
			String NoPermohonan = "";
			String NoFail = getParam("txtNoPermohonan");
			if (typez.equals("online")) {
				NoPermohonan = getParam("nopermohonan");
				Vector infoPermohonan = logiconline.semakDetailSimati(NoKPBaru,
						NoKPLama, NoKPLain, NoPermohonan);
				Hashtable l = (Hashtable) infoPermohonan.get(0);
				NoKPBaru = l.get("nokpbarusimati").toString();
				NoKPLama = l.get("nokplamasimati").toString();
				NoKPLain = l.get("nokplainsimati").toString();

				myLogger.info("NoKPBaru :: " + NoKPBaru);
				myLogger.info("NoKPLama:: " + NoKPLama);
				myLogger.info("NoKPLain :: " + NoKPLain);
				System.out.println("typez :: " + typez);
				System.out.println("NoPermohonan :: " + NoPermohonan);

			} else {
				NoKPBaru = getParam("txtNoKPBaru1a")
						+ getParam("txtNoKPBaru2a") + getParam("txtNoKPBaru3a");
				NoKPLama = getParam("txtNoKPLamaa");
				NoKPLain = getParam("txtNoKPLaina");
			}

			if (NoPermohonan != "") {
				// No Permohonan Online sudah wujud, go to tabs skrin
				// vm = "app/ppk/frmBorangPMaklumatPermohonanx.jsp";
				System.out.println("to be done here.... ");

				String idPermohonan = FrmBorangPSek17OnlineData
						.getIdPermohonan(NoPermohonan);
				String idPermohonanSimati = FrmBorangPSek17OnlineData
						.getIdPermohonanSimati(idPermohonan);
				Vector maklumatPermohonan = FrmBorangPSek17OnlineData
						.getDetailsPemohonDetails(idPermohonan);
				this.context.put("DetailPemohon", maklumatPermohonan);
				this.context.put("idPermohonan", idPermohonan);
				this.context.put("idpermohonansimati", idPermohonanSimati);

				// ////////////
				Hashtable hN = (Hashtable) maklumatPermohonan.get(0);

				String bkp = getParam("bkp");
				String lp = getParam("lp");
				String bpa = getParam("bpa");
				String lpa = getParam("lpa");

				// senarai harta pa dan pt
				Hashtable n = new Hashtable();
				Vector listHTA = FrmBorangPSek17OnlineData.setDataHTAdulu(
						(String) hN.get("idpermohonansimatilama"), (String) hN
								.get("batalpentadbir"), (String) hN
								.get("lantikpentadbir"), (String) hN
								.get("batalamanah"), (String) hN
								.get("lantikamanah"));
				this.context.put("listHTA", listHTA);

				// senarai harta baru
				Vector listHTAbaru = FrmBorangPSek17OnlineData
						.getDataHTA(idPermohonanSimati);

				this.context.put("listHTAbaru", listHTAbaru);
				this.context.put("sizeBaru", listHTAbaru.size());

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				context.put("DATEUTIL", new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("show_htaa_view_table", "yes");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");
				this.context.put("show_button", "yes");

				// ///////////

				vm = "app/ppk/frmPrmhnnBorangPHTAAH.jsp";
			} else {
				// Proceed kepada skrin senarai semak.
				// check samada data pemohon wujud atau tidak

				/*
				 * check_mode_pemohon17(session,NoKPBaru,NoKPLama,NoKPLain);
				 * Vector chkId = FrmBorangPSek17OnlineData.getListPemohon();
				 * Hashtable h = (Hashtable) chkId.get(0); int getResultChkId =
				 * Integer.parseInt(h.get("idCnt").toString());
				 */
				int getResultChkId = logic.checkPemohon(NoKPBaru, NoKPLama,
						NoKPLain);

				if (getResultChkId == 0) {
					this.context.put("IdAlert", 1);
					vm = "app/ppk/frmBorangPMaklumatPemohon.jsp";

					Vector view1 = FrmPrmhnnSek8DaftarSek8Data.getJenisKp();
					this.context.put("listkp", view1);
				} else {
					// check klu ada permohonan selesai. tak kira 8 atau pun 17
					// AZAM REMARK THIS PART 25/11/2010
					/*
					 * int getResultChkSeksyen =
					 * logiconline.checkSimatiSeksyen(NoKPBaru
					 * ,NoKPLama,NoKPLain,
					 * NoPermohonan,(String)session.getAttribute
					 * ("_ekptg_user_id")); if (getResultChkSeksyen == 21){ if
					 * (!typez.equals("online")){
					 * //insertNewOnlinePermohonan(session); } }
					 */
					String getNoFail17 = logic.getNoFail(NoKPBaru, NoKPLama,
							NoKPLain, (String) session
									.getAttribute("_ekptg_user_id"), NoFail);

					// tarik dan paparkan data pemohon
					/*
					 * Vector detailPermohonan =
					 * FrmBorangPSek17OnlineData.getDetailsPemohonRecordLama2
					 * (NoKPBaru,NoKPLama,NoKPLain,getNoFail17);
					 * this.context.put("DetailPemohon", detailPermohonan);
					 * Hashtable k = (Hashtable) detailPermohonan.get(0); int
					 * idPermohonan =
					 * Integer.parseInt(k.get("idPermohonan").toString());
					 * 
					 * this.context.put("idPermohonan", idPermohonan);
					 * this.context.put("nosubjaket",
					 * k.get("nosubjaket").toString());
					 * this.context.put("idSimati"
					 * ,k.get("idSimati").toString()); if
					 * (k.get("idstatus")==""){ k.put("idstatus","0"); }
					 * this.context.put("idstatus",
					 * k.get("idstatus").toString());
					 * 
					 * initSenaraiSemakP(k.get("noPermohonan").toString(),k.get(
					 * "nofaillama").toString(),
					 * k.get("namadaerah").toString(),k
					 * .get("namanegeri").toString
					 * (),k.get("hartatinggal").toString(),
					 * k.get("batalamanah").
					 * toString(),k.get("lantikamanah").toString(),
					 * k.get("lantikpentadbir"
					 * ).toString(),k.get("batalpentadbir").toString(),
					 * k.get("perintah_baru"
					 * ).toString(),k.get("perintah_lama").toString());
					 * 
					 * String idPermohonanSimati =
					 * k.get("idPermohonanSimati").toString();
					 * this.context.put("idpermohonansimati",
					 * idPermohonanSimati);
					 * 
					 * if (k.get("hartatinggal")!=""){
					 * this.context.put("btnKemaskini", "yes");
					 * this.context.put("btnSimpan", "");
					 * this.context.put("btnSeterusnya", "yes");
					 * this.context.put("btnBatal", "");
					 * this.context.put("btnKembali", "yes"); }else{ //list
					 * button this.context.put("btnKemaskini", "");
					 * this.context.put("btnSimpan", "yes");
					 * this.context.put("btnSeterusnya", "");
					 * this.context.put("btnBatal", "");
					 * this.context.put("btnKembali", "yes"); }
					 */
					vm = "app/ppk/frmBorangPMaklumatPermohonan.jsp";
				}

			}
			// vm = "app/ppk/frmBorangPMaklumatPermohonanx.jsp";

		} else if ("SimpanSemak".equals(submit)) {

			simpandatasemak(session);

			Vector detailPermohonan = FrmBorangPSek17OnlineData
					.getDetailsPemohonRecord(getParam("idPermohonan"));
			this.context.put("DetailPemohon", detailPermohonan);
			Hashtable k = (Hashtable) detailPermohonan.get(0);
			int idPermohonan = Integer.parseInt(k.get("idPermohonan")
					.toString());
			this.context.put("idPermohonan", idPermohonan);
			this.context.put("nosubjaket", k.get("nosubjaket").toString());
			this.context.put("idSimati", Integer.parseInt(k.get("idSimati")
					.toString()));
			if (k.get("idstatus") == "")
				k.put("idstatus", "0");
			this.context.put("idstatus", k.get("idstatus").toString());

			initSenaraiSemakP(k.get("noPermohonan").toString(), k.get(
					"nofaillama").toString(), k.get("namadaerah").toString(), k
					.get("namanegeri").toString(), k.get("hartatinggal")
					.toString(), k.get("batalamanah").toString(), k.get(
					"lantikamanah").toString(), k.get("lantikpentadbir")
					.toString(), k.get("batalpentadbir").toString(), k.get(
					"perintah_lama").toString(), k.get("perintah_baru")
					.toString());

			int idPermohonanSimati = Integer.parseInt(k.get(
					"idPermohonanSimati").toString());
			this.context.put("idpermohonansimati", idPermohonanSimati);
			this.context.put("btnKemaskini", "yes");
			this.context.put("btnSimpan", "");
			this.context.put("btnSeterusnya", "yes");
			this.context.put("btnBatal", "");
			this.context.put("btnKembali", "yes");

			vm = "app/ppk/frmBorangPMaklumatPermohonan.jsp";
		} else if ("HtaamX".equals(submit)) {
			readability2 = "readonly class=\"disabled\"";
			disability1 = "disabled class=\"disabled\"";
			disability2 = "";

			this.context.put("radio2", "yes");
			this.context.put("radio3", "yes");

			Vector listluas = FrmPrmhnnSek8DaftarSek8Data.getListLuas();
			this.context.put("listluas", listluas);

			Vector maklumatPermohonan = FrmBorangPSek17OnlineData
					.getDetailsPemohonDetails(getParam("idPermohonan"));
			this.context.put("DetailPemohon", maklumatPermohonan);
			Hashtable hN = (Hashtable) maklumatPermohonan.get(0);

			Vector list2 = FrmBorangPSek17OnlineData
					.semakDataSimati(getParam("idPermohonan"));
			this.context.put("View", list2);
			Hashtable n = (Hashtable) list2.get(0);
			String id1 = (String) n.get("idsimati");
			String id2 = (String) n.get("idPemohon");
			String id = getParam("idPermohonan");
			if (n.get("flaghubungan") == "")
				n.put("flaghubungan", "0");
			int flaghub = Integer.parseInt(n.get("flaghubungan").toString());
			String idpermohonansimati = (String) n.get("idpermohonansimati");
			this.context.put("idpermohonansimati", idpermohonansimati);
			this.context.put("flaghub", flaghub);
			this.context.put("idSimati", id1);
			this.context.put("idPemohon", id2);

			initBandarNegeriBaruSingle();

			Vector listHTAX = FrmBorangPSek17OnlineData.setDataHTATHdulu(
					(String) hN.get("idpermohonansimatilama"), (String) n
							.get("batalkuasatadbir"), (String) n
							.get("lantiktadbir"),
					(String) n.get("batalamanah"), (String) n
							.get("lantikamanah"));
			this.context.put("listHTAX", listHTAX);

			if ("HtaamviewX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = (String) getParam("idPemohon");
				id1 = (String) getParam("idSimati");

				String radioX1 = getParam("radioHtaamViewX1");
				String radioX2 = getParam("radioHtaamViewX2");
				String radioX3 = getParam("radioHtaamViewX3");
				this.context.put("checked1", "");
				this.context.put("checked2", "");
				this.context.put("checked3", "");

				this.context.put("buttonhtaam", "Tambah");

				logic.setDataSimati(id);
				Vector listSimati = logic.getDataSimati();
				this.context.put("listSimati", listSimati);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				context.put("DATEUTIL", new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				listHTAX = FrmBorangPSek17OnlineData.setDataHTATHdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTAX", listHTAX);

				Vector listHTAXbaru = FrmBorangPSek17OnlineData
						.getDataHTAX((String) n.get("idpermohonansimati"));
				this.context.put("listHTAXbaru", listHTAXbaru);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");

				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_button", "yes");

				initBandarNegeriBaruSingle();
				initValueBandarNegeriSinglehtaamx(getParam("socNegeriHtaamX"),
						getParam("socDaerahx"));

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";
			}
			if ("add_new".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				String radioX1 = getParam("radioHtaamViewX1");
				String radioX2 = getParam("radioHtaamViewX2");
				String radioX3 = getParam("radioHtaamViewX3");

				initHtaam1();

				logic.setDataSimati(id);
				Vector listSimati = logic.getDataSimati();
				this.context.put("listSimati", listSimati);
				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				context.put("DATEUTIL", new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_button", "yes");

				listHTAX = FrmBorangPSek17OnlineData.setDataHTATHdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTAX", listHTAX);

				Vector listHTAXbaru = FrmBorangPSek17OnlineData
						.getDataHTAX((String) n.get("idpermohonansimati"));
				this.context.put("listHTAXbaru", listHTAXbaru);

				vm = "app/ppk/frmPrmhnnBorangPHTATH.jsp";

			}
			if ("HtaamviewX1".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("radioHtaamViewX1",
						getParam("radioHtaamViewX1"));

				if (getParam("radioHtaamViewX1") == "1") {
					String radioX1 = "1";
				} else if (getParam("radioHtaamViewX1") == "2") {
					String radioX2 = "2";
				} else if (getParam("radioHtaamViewX1") == "3") {
					String radioX3 = "3";
				}
				this.context.put("radio2", "yes");
				this.context.put("radio3", "");
				this.context.put("checked1", "checked");
				this.context.put("checked2", "");
				this.context.put("checked3", "");
				this.context.put("idhta", "");
				this.context.put("noHakmilik", "");
				this.context.put("nopt", "");
				this.context.put("nilai_Hta_memohon", "");
				this.context.put("nilai_Hta_mati", "");
				this.context.put("kategori", "");
				this.context.put("jenishakmilik", "");
				this.context.put("pemilikan", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("luashmp", "");
				this.context.put("luasasal", "");
				this.context.put("nocagaran", "");
				this.context.put("nopajakan", "");
				this.context.put("jenistanah", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("jenishta", "");
				this.context.put("tanggungan", "");
				this.context.put("jenisluas", "");
				this.context.put("catatan", "");
				this.context.put("noperserahan", "");
				this.context.put("namapemaju", "");
				this.context.put("alamatpemaju1", "");
				this.context.put("alamatpemaju2", "");
				this.context.put("alamatpemaju3", "");

				this.context.put("poskodpemaju", "");
				this.context.put("bandarpemaju", "");

				this.context.put("negeripemaju", "");
				this.context.put("alamathta1", "");
				this.context.put("alamathta2", "");
				this.context.put("alamathta3", "");

				this.context.put("poskodhta", "");
				this.context.put("bandarhta", "");
				this.context.put("noperjanjian", "");
				this.context.put("tarikhperjanjian", "");

				this.context.put("namarancangan", "");
				this.context.put("noroh", "");
				this.context.put("nolot", "");

				this.context.put("nolot", "");

				this.context.put("nocagaran", "");
				this.context.put("buttonhtaam", "Tambah");

				logic.setDataSimati(id);
				Vector listSimati = logic.getDataSimati();
				this.context.put("listSimati", listSimati);

				logic.setDataHTA(id1);
				Vector listHTA = logic.getDataHTA();
				this.context.put("listHTA", listHTA);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				context.put("DATEUTIL", new DateUtil());
				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");

				this.context.put("show_button", "yes");

				listHTAX = FrmBorangPSek17OnlineData.setDataHTATHdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTAX", listHTAX);

				Vector listHTAXbaru = FrmBorangPSek17OnlineData
						.getDataHTAX((String) n.get("idpermohonansimati"));
				this.context.put("listHTAXbaru", listHTAXbaru);

				initValueBandarNegeriSingle(getParam("socNegeriTetap"),
						getParam("socDaerah"));
				initBandarNegeriBaruSinglehtaamx();

				vm = "app/ppk/frmPrmhnnBorangPHTATH.jsp";

			}

			if ("HtaamviewX2".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");
				this.context.put("radioHtaamViewX1",
						getParam("radioHtaamViewX1"));
				String radioX1 = getParam("radioHtaamViewX1");
				String radioX2 = getParam("radioHtaamViewX2");
				String radioX3 = getParam("radioHtaamViewX3");

				this.context.put("radio2", "");
				this.context.put("radio3", "yes");
				this.context.put("checked2", "checked");
				this.context.put("checked1", "");
				this.context.put("checked3", "");

				this.context.put("idhta", "");
				this.context.put("noHakmilik", "");
				this.context.put("nopt", "");
				this.context.put("nilai_Hta_memohon", "");
				this.context.put("nilai_Hta_mati", "");
				this.context.put("kategori", "");
				this.context.put("jenishakmilik", "");
				this.context.put("pemilikan", "");

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("luashmp", "");
				this.context.put("luasasal", "");
				this.context.put("nocagaran", "");
				this.context.put("nopajakan", "");

				this.context.put("jenistanah", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("jenishta", "");
				this.context.put("tanggungan", "");
				this.context.put("jenisluas", "");

				this.context.put("catatan", "");

				this.context.put("noperserahan", "");

				this.context.put("namapemaju", "");
				this.context.put("alamatpemaju1", "");
				this.context.put("alamatpemaju2", "");
				this.context.put("alamatpemaju3", "");

				this.context.put("poskodpemaju", "");
				this.context.put("bandarpemaju", "");

				this.context.put("negeripemaju", "");
				this.context.put("alamathta1", "");
				this.context.put("alamathta2", "");
				this.context.put("alamathta3", "");

				this.context.put("poskodhta", "");
				this.context.put("bandarhta", "");
				this.context.put("noperjanjian", "");
				this.context.put("tarikhperjanjian", "");

				this.context.put("namarancangan", "");
				this.context.put("noroh", "");
				this.context.put("nolot", "");

				this.context.put("nolot", "");

				this.context.put("nocagaran", "");
				this.context.put("buttonhtaam", "Tambah");

				logic.setDataSimati(id);
				Vector listSimati = logic.getDataSimati();
				this.context.put("listSimati", listSimati);
				logic.setDataHTA(id1);
				Vector listHTA = logic.getDataHTA();
				this.context.put("listHTA", listHTA);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				context.put("DATEUTIL", new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");

				this.context.put("show_button", "yes");

				listHTAX = FrmBorangPSek17OnlineData.setDataHTATHdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTAX", listHTAX);

				Vector listHTAXbaru = FrmBorangPSek17OnlineData
						.getDataHTAX((String) n.get("idpermohonansimati"));
				this.context.put("listHTAXbaru", listHTAXbaru);

				initValueBandarNegeriSingle(getParam("socNegeriTetap"),
						getParam("socDaerah"));

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";
			}

			if ("HtaamviewX3".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("radioHtaamViewX1",
						getParam("radioHtaamViewX1"));

				String radioX1 = getParam("radioHtaamViewX1");
				String radioX2 = getParam("radioHtaamViewX2");
				String radioX3 = getParam("radioHtaamViewX3");

				this.context.put("radio2", "");
				this.context.put("radio3", "");
				this.context.put("checked2", "");
				this.context.put("checked1", "");
				this.context.put("checked3", "checked");
				this.context.put("idhta", "");
				this.context.put("noHakmilik", "");
				this.context.put("nopt", "");
				this.context.put("nilai_Hta_memohon", "");
				this.context.put("nilai_Hta_mati", "");
				this.context.put("kategori", "");
				this.context.put("jenishakmilik", "");
				this.context.put("pemilikan", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("luashmp", "");
				this.context.put("luasasal", "");
				this.context.put("nocagaran", "");
				this.context.put("nopajakan", "");
				this.context.put("jenistanah", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("jenishta", "");
				this.context.put("tanggungan", "");
				this.context.put("jenisluas", "");
				this.context.put("catatan", "");
				this.context.put("noperserahan", "");
				this.context.put("namapemaju", "");
				this.context.put("alamatpemaju1", "");
				this.context.put("alamatpemaju2", "");
				this.context.put("alamatpemaju3", "");
				this.context.put("poskodpemaju", "");
				this.context.put("bandarpemaju", "");
				this.context.put("negeripemaju", "");
				this.context.put("alamathta1", "");
				this.context.put("alamathta2", "");
				this.context.put("alamathta3", "");
				this.context.put("poskodhta", "");
				this.context.put("bandarhta", "");
				this.context.put("noperjanjian", "");
				this.context.put("tarikhperjanjian", "");
				this.context.put("namarancangan", "");
				this.context.put("noroh", "");
				this.context.put("nolot", "");
				this.context.put("nolot", "");
				this.context.put("nocagaran", "");
				this.context.put("buttonhtaam", "Tambah");

				logic.setDataSimati(id);
				Vector listSimati = logic.getDataSimati();
				this.context.put("listSimati", listSimati);
				logic.setDataHTA(id1);
				Vector listHTA = logic.getDataHTA();
				this.context.put("listHTA", listHTA);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				context.put("DATEUTIL", new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");

				this.context.put("show_button", "yes");

				listHTAX = FrmBorangPSek17OnlineData.setDataHTATHdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTAX", listHTAX);

				Vector listHTAXbaru = FrmBorangPSek17OnlineData
						.getDataHTAX((String) n.get("idpermohonansimati"));
				this.context.put("listHTAXbaru", listHTAXbaru);

				initValueBandarNegeriSingle(getParam("socNegeriTetap"),
						getParam("socDaerah"));

				vm = "app/ppk/frmPrmhnnBorangPHTATH.jsp";
			} else if ("onChangeBandarTetap".equals(mode)) {
				this.context.put("radioHtaamViewX1",
						getParam("radioHtaamViewX1"));
				if (getParam("radioHtaamViewX1").equals("1")) {
					this.context.put("radio2", "yes");
					this.context.put("radio3", "");
					this.context.put("checked1", "checked");
					this.context.put("checked2", "");
					this.context.put("checked3", "");
				} else if (getParam("radioHtaamViewX1").equals("2")) {
					this.context.put("radio2", "");
					this.context.put("radio3", "yes");
					this.context.put("checked1", "");
					this.context.put("checked2", "checked");
					this.context.put("checked3", "");
				} else if (getParam("radioHtaamViewX1").equals("3")) {
					this.context.put("radio2", "");
					this.context.put("radio3", "");
					this.context.put("checked1", "");
					this.context.put("checked2", "");
					this.context.put("checked3", "checked");
				}
				textfieldHtaamValue();
				initValueBandarNegeriSingle(getParam("socNegeriTetap"),
						getParam("socDaerah"));

				vm = "app/ppk/frmPrmhnnBorangPHTATH.jsp";
			} else if ("tambah_htaam".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");
				this.context.put("idPermohonan", id);
				this.context.put("idSimati", id1);
				this.context.put("idPemohon", id2);

				logic.setDataPenting(id1);
				Vector listPenting = logic.getDataPenting();

				logic.setDataSaksi(id1);
				Vector listSaksi = logic.getDataSaksi();
				this.context.put("idhta", "");
				this.context.put("noHakmilik", "");
				this.context.put("nopt", "");
				this.context.put("nilai_Hta_memohon", "");
				this.context.put("nilai_Hta_mati", "");
				this.context.put("kategori", "");
				this.context.put("jenishakmilik", "");
				this.context.put("pemilikan", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("luashmp", "");
				this.context.put("luasasal", "");
				this.context.put("nocagaran", "");
				this.context.put("nopajakan", "");
				this.context.put("jenistanah", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("jenishta", "");
				this.context.put("tanggungan", "");
				this.context.put("jenisluas", "");
				this.context.put("catatan", "");
				this.context.put("noperserahan", "");
				this.context.put("namapemaju", "");
				this.context.put("alamatpemaju1", "");
				this.context.put("alamatpemaju2", "");
				this.context.put("alamatpemaju3", "");
				this.context.put("poskodpemaju", "");
				this.context.put("bandarpemaju", "");
				this.context.put("negeripemaju", "");
				this.context.put("alamathta1", "");
				this.context.put("alamathta2", "");
				this.context.put("alamathta3", "");
				this.context.put("poskodhta", "");
				this.context.put("bandarhta", "");
				this.context.put("noperjanjian", "");
				this.context.put("tarikhperjanjian", "");
				this.context.put("namarancangan", "");
				this.context.put("noroh", "");
				this.context.put("nolot", "");
				this.context.put("nolot", "");
				this.context.put("nocagaran", "");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");

				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");

				this.context.put("show_button", "yes");

				listHTAX = FrmBorangPSek17OnlineData.setDataHTATHdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTAX", listHTAX);

				Vector listHTAXbaru = FrmBorangPSek17OnlineData
						.getDataHTAX((String) n.get("idpermohonansimati"));
				this.context.put("listHTAXbaru", listHTAXbaru);

				vm = "app/ppk/frmPrmhnnBorangPHTATH.jsp";

			} else if ("simpanflagX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				logic.deletePilihanHta(getParam("idpermohonansimati"));

				String[] cbsemaks = this.request.getParameterValues("chkbox");
				if (cbsemaks != null) {
					for (int i = 0; i < cbsemaks.length; i++) {
						logic.addPilihanHta(String.valueOf((String) session
								.getAttribute("_ekptg_user_id")), String
								.valueOf(getParam("idpermohonansimati")),
								cbsemaks[i]);
					}
				}

				this.context.put("radio2", "yes");
				this.context.put("radio3", "");
				this.context.put("checked1", "checked");
				this.context.put("checked2", "");
				this.context.put("checked3", "");

				this.context.put("idhta", "");
				this.context.put("noHakmilik", "");
				this.context.put("nopt", "");
				this.context.put("nilai_Hta_memohon", "");
				this.context.put("nilai_Hta_mati", "");
				this.context.put("kategori", "");
				this.context.put("jenishakmilik", "");
				this.context.put("pemilikan", "");

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("luashmp", "");
				this.context.put("luasasal", "");
				this.context.put("nocagaran", "");
				this.context.put("nopajakan", "");

				this.context.put("jenistanah", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("jenishta", "");
				this.context.put("tanggungan", "");
				this.context.put("jenisluas", "");

				this.context.put("catatan", "");

				this.context.put("noperserahan", "");

				this.context.put("namapemaju", "");
				this.context.put("alamatpemaju1", "");
				this.context.put("alamatpemaju2", "");
				this.context.put("alamatpemaju3", "");

				this.context.put("poskodpemaju", "");
				this.context.put("bandarpemaju", "");

				this.context.put("negeripemaju", "");
				this.context.put("alamathta1", "");
				this.context.put("alamathta2", "");
				this.context.put("alamathta3", "");

				this.context.put("poskodhta", "");
				this.context.put("bandarhta", "");
				this.context.put("noperjanjian", "");
				this.context.put("tarikhperjanjian", "");

				this.context.put("namarancangan", "");
				this.context.put("noroh", "");
				this.context.put("nolot", "");

				this.context.put("nolot", "");

				this.context.put("nocagaran", "");

				logic.setDataSimati(id);
				Vector listSimati = logic.getDataSimati();
				this.context.put("listSimati", listSimati);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				context.put("DATEUTIL", new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");

				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_button", "yes");

				listHTAX = FrmBorangPSek17OnlineData.setDataHTATHdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTAX", listHTAX);

				Vector listHTAXbaru = FrmBorangPSek17OnlineData
						.getDataHTAX((String) n.get("idpermohonansimati"));
				this.context.put("listHTAXbaru", listHTAXbaru);

				vm = "app/ppk/frmPrmhnnBorangPHTATH.jsp";
			} else if ("masukkanX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				addHtaamX(session);

				this.context.put("radio2", "yes");
				this.context.put("radio3", "");
				this.context.put("checked1", "checked");
				this.context.put("checked2", "");
				this.context.put("checked3", "");

				this.context.put("idhta", "");
				this.context.put("noHakmilik", "");
				this.context.put("nopt", "");
				this.context.put("nilai_Hta_memohon", "");
				this.context.put("nilai_Hta_mati", "");
				this.context.put("kategori", "");
				this.context.put("jenishakmilik", "");
				this.context.put("pemilikan", "");

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("luashmp", "");
				this.context.put("luasasal", "");
				this.context.put("nocagaran", "");
				this.context.put("nopajakan", "");

				this.context.put("jenistanah", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("jenishta", "");
				this.context.put("tanggungan", "");
				this.context.put("jenisluas", "");

				this.context.put("catatan", "");

				this.context.put("noperserahan", "");

				this.context.put("namapemaju", "");
				this.context.put("alamatpemaju1", "");
				this.context.put("alamatpemaju2", "");
				this.context.put("alamatpemaju3", "");

				this.context.put("poskodpemaju", "");
				this.context.put("bandarpemaju", "");

				this.context.put("negeripemaju", "");
				this.context.put("alamathta1", "");
				this.context.put("alamathta2", "");
				this.context.put("alamathta3", "");

				this.context.put("poskodhta", "");
				this.context.put("bandarhta", "");
				this.context.put("noperjanjian", "");
				this.context.put("tarikhperjanjian", "");

				this.context.put("namarancangan", "");
				this.context.put("noroh", "");
				this.context.put("nolot", "");

				this.context.put("nolot", "");

				this.context.put("nocagaran", "");

				logic.setDataSimati(id);
				Vector listSimati = logic.getDataSimati();
				this.context.put("listSimati", listSimati);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				context.put("DATEUTIL", new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");

				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_button", "yes");

				listHTAX = FrmBorangPSek17OnlineData.setDataHTATHdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTAX", listHTAX);

				Vector listHTAXbaru = FrmBorangPSek17OnlineData
						.getDataHTAX((String) n.get("idpermohonansimati"));
				this.context.put("listHTAXbaru", listHTAXbaru);

				vm = "app/ppk/frmPrmhnnBorangPHTATH.jsp";

			}

			else if ("negerichangeX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", "");
				this.context.put("readmodemukim", disability1);

				this.context.put("radioHtaamViewX1",
						getParam("radioHtaamViewX1"));

				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));
				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);

				this.context.put("negeri", idnegeri);
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				if (getParam("radioHtaamViewX1") == "1") {
					this.context.put("radio2", "yes");
					this.context.put("radio3", "");
					this.context.put("checked1", "checked");
					this.context.put("checked2", "");
					this.context.put("checked3", "");
				}
				if (getParam("radioHtaamViewX1") == "2") {
					this.context.put("radio2", "");
					this.context.put("radio3", "yes");
					this.context.put("checked1", "");
					this.context.put("checked2", "checked");
					this.context.put("checked3", "");
				}
				if (getParam("radioHtaamViewX1") == "3") {
					this.context.put("radio2", "");
					this.context.put("radio3", "");
					this.context.put("checked1", "");
					this.context.put("checked2", "");
					this.context.put("checked3", "checked");
				}

				this.context.put("nopt", getParam("txtNoPTHtaamX"));
				this.context.put("nilai_Hta_memohon",
						getParam("txtNilaiTarikhMohonHtaaX"));
				this.context.put("nilai_Hta_mati",
						getParam("txtNilaiTarikhMatiHtaamX"));
				this.context
						.put("kategori", getParam("socKategoriTanahHtaamX"));

				this.context.put("luashmp", getParam("txtLuasHMpHtaamX"));
				this.context.put("luasasal", getParam("txtLuasAsalHtaamX"));
				this.context.put("nopajakan", getParam("txtNoPajakanX"));
				this.context.put("jenistanah", getParam("socJenisTanahHtaamX"));
				this.context.put("basimati", getParam("txtBahagianSimati1X"));
				this.context.put("bbsimati", getParam("txtBahagianSimati2X"));

				this.context.put("tanggungan", getParam("txtTanggunganHtaamX"));
				this.context.put("jenisluas", getParam("socJenisLuasHtaamX"));
				this.context.put("catatan", getParam("txtCatatanHtaamX"));

				this.context.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
				this.context.put("alamatpemaju1",
						getParam("txtAlamatPemaju1HtaamX"));
				this.context.put("alamatpemaju2",
						getParam("txtAlamatPemaju2HtaamX"));
				this.context.put("alamatpemaju3",
						getParam("txtAlamatPemaju3HtaamX"));

				this.context.put("poskodpemaju",
						getParam("txtPoskodPemaju1HtaamX"));
				this.context.put("bandarpemaju",
						getParam("txtBandarPemaju1HtaamX"));
				this.context.put("negeripemaju",
						getParam("socNegeriPemajuHtaamX"));
				this.context.put("alamathta1",
						getParam("txtAlamatHarta1HtaamX"));
				this.context.put("alamathta2",
						getParam("txtAlamatHarta2HtaamX"));
				this.context.put("alamathta3",
						getParam("txtAlamatHarta3HtaamX"));

				this.context.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
				this.context.put("bandarhta", getParam("txtBandarHartaHtaamX"));
				this.context.put("noperjanjian",
						getParam("txtNoPerjanjianHtaamX"));
				this.context.put("tarikhperjanjian",
						getParam("txtTarikhPerjanjianHtaamX"));

				this.context.put("namarancangan",
						getParam("txtNamaRancanganHtaamX"));
				this.context.put("noroh", getParam("txtNoRohHtaamX"));
				this.context.put("nolot", getParam("txtLotIdHtaamX"));
				this.context.put("nolot", getParam("txtLotIdHtaamX"));
				this.context.put("nocagaran", getParam("txtNoCagaranX"));

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");

				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_button", "yes");

				initValueBandarNegeriSinglehtaamx(getParam("socNegeriHtaamX"),
						getParam("socDaerahx"));

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";
			}

			else if ("negerichangeupX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", "");
				this.context.put("readmodemukim", disability1);

				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);
				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);

				this.context.put("negeri", idnegeri);
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				initGetParamHtaamxValue(idnegeri);

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");

				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");

				this.context.put("show_kembali_htaam", "yes");

				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");
				this.context.put("show_button", "yes");
				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";

			} else if ("daerahchangeupX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", "");
				this.context.put("readmodemukim", "");

				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));

				int iddaerah = Integer.parseInt(getParam("socDaerahHtaamX"));

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);

				this.context.put("negeri", idnegeri);
				this.context.put("daerah", iddaerah);
				this.context.put("mukim", "");

				Vector v = new Vector();
				Hashtable h = new Hashtable();

				h.put("idhta", getParam("idhtaamXid"));
				h.put("negeri", idnegeri);
				h.put("daerah", iddaerah);
				h.put("idSimati", getParam("idSimatiX"));
				h.put("nopt", getParam("txtNoPTHtaamX"));
				h
						.put("nilai_Hta_memohon",
								getParam("txtNilaiTarikhMohonHtaaX"));
				h.put("nilai_Hta_mati", getParam("txtNilaiTarikhMatiHtaamX"));
				h.put("kategori", getParam("socKategoriTanahHtaamX"));

				h.put("luashmp", getParam("txtLuasHMpHtaamX"));
				h.put("luasasal", getParam("txtLuasAsalHtaamX"));

				h.put("nopajakan", getParam("txtNoPajakanX"));
				h.put("jenistanah", getParam("socJenisTanahHtaamX"));
				h.put("basimati", getParam("txtBahagianSimati1X"));
				h.put("bbsimati", getParam("txtBahagianSimati2X"));

				h.put("tanggungan", getParam("txtTanggunganHtaamX"));
				h.put("jenisluas", getParam("socJenisLuasHtaamX"));

				h.put("catatan", getParam("txtCatatanHtaamX"));
				h.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
				h.put("alamatpemaju1", getParam("txtAlamatPemaju1HtaamX"));
				h.put("alamatpemaju2", getParam("txtAlamatPemaju2HtaamX"));
				h.put("alamatpemaju3", getParam("txtAlamatPemaju3HtaamX"));

				h.put("poskodpemaju", getParam("txtPoskodPemaju1HtaamX"));
				h.put("bandarpemaju", getParam("txtBandarPemaju1HtaamX"));

				h.put("negeripemaju", getParam("socNegeriPemajuHtaamX"));
				h.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
				h.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
				h.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));

				h.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
				h.put("bandarhta", getParam("txtBandarHartaHtaamX"));
				h.put("noperjanjian", getParam("txtNoPerjanjianHtaamX"));
				h
						.put("tarikhperjanjian",
								getParam("txtTarikhPerjanjianHtaamX"));

				h.put("namarancangan", getParam("txtNamaRancanganHtaamX"));
				h.put("noroh", getParam("txtNoRohHtaamX"));
				h.put("nolot", getParam("txtLotIdHtaamX"));

				h.put("nolot", getParam("txtLotIdHtaamX"));
				h.put("flag", getParam("flag"));

				h.put("nocagaran", getParam("txtNoCagaranX"));

				v.addElement(h);

				this.context.put("listHTAXid", v);

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");

				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");

				this.context.put("show_kembali_htaam", "yes");

				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");
				this.context.put("show_button", "yes");
				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";

			} else if ("getHtaamX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				String idhtaam = getParam("idhtaamXid");
				logic3.setDataHTAXbyIdHtaam(idhtaam);
				Vector listHTAXid = logic3.getDataHTAXbyIdHtaam();

				listHTAX = FrmBorangPSek17OnlineData.setDataHTATHdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTAX", listHTAX);

				Vector listHTAXbaru = FrmBorangPSek17OnlineData
						.getDataHTAX((String) n.get("idpermohonansimati"));
				this.context.put("listHTAXbaru", listHTAXbaru);

				Hashtable b = (Hashtable) listHTAXid.get(0);
				if (b.get("negeri") == "")
					b.put("negeri", "0");
				if (b.get("daerah") == "")
					b.put("daerah", "0");
				if (b.get("negeripemaju") == "")
					b.put("negeripemaju", "0");
				if (b.get("idbandarpemaju") == "")
					b.put("idbandarpemaju", "0");
				if (b.get("idbandarhta") == "")
					b.put("idbandarhta", "0");

				int idnegeri = Integer.parseInt(b.get("negeri").toString());
				int iddaerah = Integer.parseInt(b.get("daerah").toString());

				initValueBandarNegeriSingleDisabled(b.get("negeripemaju")
						.toString(), b.get("idbandarpemaju").toString());
				initValueBandarNegeriSingleDisabledhtaamx(b.get("negeri")
						.toString(), b.get("idbandarhta").toString());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				Vector listmukim2 = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listmukim", listmukim2);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				Vector listdaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listdaerah", listdaerah);

				this.context.put("idhtaamX", idhtaam);
				this.context.put("idhtaam", idhtaam);

				this.context.put("listHTAXid", listHTAXid);

				this.context.put("readmodenegeri", disability1);
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);
				this.context.put("readmode", "readonly class=\"disabled\"");

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");

				this.context.put("show_kemaskini_htaam", "yes");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");

				this.context.put("show_kembali_htaam", "yes");

				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");
				this.context.put("show_button", "yes");

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";
			} else if ("batalHtaamX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				String idhtaam = getParam("idhtaamXid");
				logic.setDataHTAXbyIdHtaam(idhtaam);
				Vector listHTAXid = logic.getDataHTAXbyIdHtaam();

				Hashtable b = (Hashtable) listHTAXid.get(0);
				int idnegeri = Integer.parseInt(b.get("negeri").toString());
				int iddaerah = Integer.parseInt(b.get("daerah").toString());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				Vector listmukim2 = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listmukim", listmukim2);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				Vector listdaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listdaerah", listdaerah);

				this.context.put("idhtaamX", idhtaam);
				this.context.put("idhtaam", idhtaam);

				this.context.put("listHTAXid", listHTAXid);
				this.context.put("readmodenegeri", disability1);
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);
				this.context.put("readmode", "disabled");

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");

				this.context.put("show_kemaskini_htaam", "yes");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "yes");

				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_button", "yes");

				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";

			} else if ("kembaliHtaamX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				initTextfieldHtaamxBlank();

				logic.setDataSimati(id);
				Vector listSimati = logic.getDataSimati();
				this.context.put("listSimati", listSimati);
				logic.setDataHTA(id1);
				Vector listHTA = logic.getDataHTA();
				this.context.put("listHTA", listHTA);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", disability1);
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				context.put("DATEUTIL", new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");

				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");

				this.context.put("show_kembali_htaam", "");

				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";

			} else if ("hapusHtaamX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				String idhtaam = getParam("idhtaamXid");
				FrmPrmhnnSek8Data.deleteHtaam(idhtaam);

				logic.setDataSimati(id);
				Vector listSimati = logic.getDataSimati();
				this.context.put("listSimati", listSimati);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				context.put("DATEUTIL", new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");

				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_button", "yes");

				this.context.put("radio2", "yes");
				this.context.put("radio3", "");
				this.context.put("checked2", "");
				this.context.put("checked1", "checked");
				this.context.put("checked3", "");

				listHTAX = FrmBorangPSek17OnlineData.setDataHTATHdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTAX", listHTAX);

				Vector listHTAXbaru = FrmBorangPSek17OnlineData
						.getDataHTAX((String) n.get("idpermohonansimati"));
				this.context.put("listHTAXbaru", listHTAXbaru);

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";

			} else if ("kemaskiniHtaamX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				String idhtaam = getParam("idhtaamXid");
				logic3.setDataHTAXbyIdHtaam(idhtaam);
				Vector listHTAXid = logic3.getDataHTAXbyIdHtaam();
				Hashtable b = (Hashtable) listHTAXid.get(0);
				if (b.get("negeri") == "")
					b.put("negeri", "0");
				if (b.get("daerah") == "")
					b.put("daerah", "0");
				if (b.get("negeripemaju") == "")
					b.put("negeripemaju", "0");
				if (b.get("idbandarpemaju") == "")
					b.put("idbandarpemaju", "0");
				if (b.get("idbandarhta") == "")
					b.put("idbandarhta", "0");

				initValueBandarNegeriSingle(b.get("negeripemaju").toString(), b
						.get("idbandarpemaju").toString());
				initValueBandarNegeriSinglehtaamx(b.get("negeri").toString(), b
						.get("idbandarhta").toString());

				this.context.put("idhtaamX", idhtaam);
				this.context.put("idhtaam", idhtaam);

				this.context.put("listHTAXid", listHTAXid);

				this.context.put("readmodenegeri", disability1);
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);
				this.context.put("readmode", "");
				this.context.put("readmodecheckbox", disability1);

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");

				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "");

				this.context.put("show_kembali_htaam", "yes");

				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				listHTAX = FrmBorangPSek17OnlineData.setDataHTATHdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTAX", listHTAX);

				Vector listHTAXbaru = FrmBorangPSek17OnlineData
						.getDataHTAX((String) n.get("idpermohonansimati"));
				this.context.put("listHTAXbaru", listHTAXbaru);

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";
			} else if ("simpanHtaamX".equals(mode)) {
				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				String idhtaam = getParam("idhtaamXid");
				// update data
				updateHtaamX(session);

				logic3.setDataHTAXbyIdHtaam(idhtaam);
				Vector listHTAXid = logic3.getDataHTAXbyIdHtaam();
				Hashtable b = (Hashtable) listHTAXid.get(0);
				if (b.get("negeri") == "")
					b.put("negeri", "0");
				if (b.get("daerah") == "")
					b.put("daerah", "0");
				if (b.get("negeripemaju") == "")
					b.put("negeripemaju", "0");
				if (b.get("idbandarpemaju") == "")
					b.put("idbandarpemaju", "0");
				if (b.get("idbandarhta") == "")
					b.put("idbandarhta", "0");

				initValueBandarNegeriSingleDisabled(b.get("negeripemaju")
						.toString(), b.get("idbandarpemaju").toString());
				initValueBandarNegeriSingleDisabledhtaamx(b.get("negeri")
						.toString(), b.get("idbandarhta").toString());

				this.context.put("idhtaamX", idhtaam);
				this.context.put("idhtaam", idhtaam);

				this.context.put("listHTAXid", listHTAXid);

				this.context.put("readmodenegeri", disability1);
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);
				this.context.put("readmode", "readonly class=\"disabled\"");
				this.context.put("readmodecheckbox", disability1);

				context.put("DATEUTIL", new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				logic.setDataSimati(id);
				Vector listSimati = logic.getDataSimati();
				this.context.put("listSimati", listSimati);

				logic.setDataHTA(id1);
				Vector listHTA = logic.getDataHTA();
				this.context.put("listHTA", listHTA);

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");

				this.context.put("show_kemaskini_htaam", "yes");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");

				this.context.put("show_kembali_htaam", "yes");

				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");

				this.context.put("radio2", "yes");
				this.context.put("radio3", "");
				this.context.put("checked1", "checked");
				this.context.put("checked2", "");
				this.context.put("checked3", "");

				// list senarai harta tak alih simati
				listHTAX = FrmBorangPSek17OnlineData.setDataHTATHdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTAX", listHTAX);

				Vector listHTAXbaru = FrmBorangPSek17OnlineData
						.getDataHTAX((String) n.get("idpermohonansimati"));
				this.context.put("listHTAXbaru", listHTAXbaru);

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";
			} else if ("daerahchangeX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				initBandarNegeriBaruSingle();

				this.context.put("radioHtaamViewX1",
						getParam("radioHtaamViewX1"));

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", "");
				this.context.put("readmodemukim", "");

				int iddaerah = Integer.parseInt(getParam("socDaerahHtaamX"));
				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);

				this.context.put("negeri", idnegeri);
				this.context.put("daerah", iddaerah);
				this.context.put("mukim", "");

				if (getParam("radioHtaamViewX1") == "1") {

					this.context.put("radio2", "yes");
					this.context.put("radio3", "");
					this.context.put("checked1", "checked");
					this.context.put("checked2", "");
					this.context.put("checked3", "");
				}
				if (getParam("radioHtaamViewX1") == "2") {
					this.context.put("radio2", "");
					this.context.put("radio3", "yes");
					this.context.put("checked1", "");
					this.context.put("checked2", "checked");
					this.context.put("checked3", "");
				}
				if (getParam("radioHtaamViewX1") == "3") {
					this.context.put("radio2", "");
					this.context.put("radio3", "");
					this.context.put("checked1", "");
					this.context.put("checked2", "");
					this.context.put("checked3", "checked");
				}

				this.context.put("nopt", getParam("txtNoPTHtaamX"));
				this.context.put("nilai_Hta_memohon",
						getParam("txtNilaiTarikhMohonHtaaX"));
				this.context.put("nilai_Hta_mati",
						getParam("txtNilaiTarikhMatiHtaamX"));
				this.context
						.put("kategori", getParam("socKategoriTanahHtaamX"));

				this.context.put("luashmp", getParam("txtLuasHMpHtaamX"));
				this.context.put("luasasal", getParam("txtLuasAsalHtaamX"));
				this.context.put("nopajakan", getParam("txtNoPajakanX"));
				this.context.put("jenistanah", getParam("socJenisTanahHtaamX"));
				this.context.put("basimati", getParam("txtBahagianSimati1X"));
				this.context.put("bbsimati", getParam("txtBahagianSimati2X"));
				this.context.put("tanggungan", getParam("txtTanggunganHtaamX"));
				this.context.put("jenisluas", getParam("socJenisLuasHtaamX"));
				this.context.put("catatan", getParam("txtCatatanHtaamX"));
				this.context.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
				this.context.put("alamatpemaju1",
						getParam("txtAlamatPemaju1HtaamX"));
				this.context.put("alamatpemaju2",
						getParam("txtAlamatPemaju2HtaamX"));
				this.context.put("alamatpemaju3",
						getParam("txtAlamatPemaju3HtaamX"));
				this.context.put("poskodpemaju",
						getParam("txtPoskodPemaju1HtaamX"));
				this.context.put("bandarpemaju",
						getParam("txtBandarPemaju1HtaamX"));
				this.context.put("negeripemaju",
						getParam("socNegeriPemajuHtaamX"));
				this.context.put("alamathta1",
						getParam("txtAlamatHarta1HtaamX"));
				this.context.put("alamathta2",
						getParam("txtAlamatHarta2HtaamX"));
				this.context.put("alamathta3",
						getParam("txtAlamatHarta3HtaamX"));
				this.context.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
				this.context.put("bandarhta", getParam("txtBandarHartaHtaamX"));
				this.context.put("noperjanjian",
						getParam("txtNoPerjanjianHtaamX"));
				this.context.put("tarikhperjanjian",
						getParam("txtTarikhPerjanjianHtaamX"));
				this.context.put("namarancangan",
						getParam("txtNamaRancanganHtaamX"));
				this.context.put("noroh", getParam("txtNoRohHtaamX"));
				this.context.put("nolot", getParam("txtLotIdHtaamX"));
				this.context.put("nocagaran", getParam("txtNoCagaranX"));
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";
			}
			vm = "app/ppk/frmPrmhnnBorangPHTATH.jsp";
		} else if ("CetakPengesahanView".equals(submit)) {

			String idfailSeksyen8 = getParam("idfailSeksyen8");
			context.put("idfailSeksyen8", idfailSeksyen8);

			String idsimati = getParam("idSimati");
			context.put("idsimati", idsimati);

			String idPemohon = getParam("idPemohon");

			String idpermohonan = "";

			String result = "";
			result = generateNoPermohonanOnlineSeksyen17(idsimati, usid,
					idfailSeksyen8, idPemohon);
			context.put("idPermohonanBaru", result);
			myLogger.info("RESULT IDP :: " + result);

			idpermohonan = result;

			System.out.println("result--" + result);

			simpandatasemak2(session, result);

			Vector maklumatPermohonan = FrmBorangPSek17OnlineData
					.getDetailsPemohonDetails(idpermohonan);
			this.context.put("DetailPemohon", maklumatPermohonan);

			vm = "app/ppk/frmBorangPMaklumatPemohonCetakPengesahan.jsp";

		} else if ("Htaam".equals(submit)) {
			readability1 = "";
			readability2 = "disabled readonly class=\"disabled\"";
			disability1 = "disabled class=\"disabled\"";
			disability2 = "";

			String statushak = "Y";
			Vector listjenishakmilik = FrmPrmhnnSek8DaftarSek8Data
					.getListJenisHakMilik(statushak);
			this.context.put("listJenisHakMilik", listjenishakmilik);

			Vector listluas = FrmPrmhnnSek8DaftarSek8Data.getListLuas();
			this.context.put("listluas", listluas);

			Vector listkategori = FrmPrmhnnSek8DaftarSek8Data.getListKategori();
			this.context.put("listkategori", listkategori);

			Vector maklumatPermohonan = FrmBorangPSek17OnlineData
					.getDetailsPemohonDetails(getParam("idPermohonan"));
			this.context.put("DetailPemohon", maklumatPermohonan);

			Hashtable hN = (Hashtable) maklumatPermohonan.get(0);

			Vector list = FrmBorangPSek17OnlineData
					.semakDataSimati(getParam("idPermohonan"));
			this.context.put("View", list);
			Hashtable n = (Hashtable) list.get(0);
			String id1 = (String) n.get("idsimati");
			String id2 = (String) n.get("idPemohon");
			String id = getParam("idPermohonan");
			if (n.get("flaghubungan") == "")
				n.put("flaghubungan", "0");
			int flaghub = Integer.parseInt(n.get("flaghubungan").toString());

			if (n.get("idstatus") == "")
				n.put("idstatus", "0");
			int idstatus = Integer.parseInt(n.get("idstatus").toString());

			myLogger.info("idstatus online :: " + idstatus);

			this.context.put("idStatus", idstatus);
			String idpermohonansimati = (String) n.get("idpermohonansimati");
			this.context.put("flaghub", flaghub);
			this.context.put("idpermohonansimati", idpermohonansimati);
			this.context.put("idSimati", id1);
			this.context.put("idPemohon", id2);

			if ("Htaamview".equals(mode)) {
				this.context.put("buttonhtaam", "Tambah");

				// logic.setDataSimati(id);
				// Vector listSimati = logic.getDataSimati();
				// this.context.put("listSimati", listSimati);

				// senarai harta pa dan
				// pt(String)hN.get("idpermohonansimatilama")
				Vector listHTA = FrmBorangPSek17OnlineData.setDataHTAdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTA", listHTA);

				// senarai harta baru
				Vector listHTAbaru = FrmBorangPSek17OnlineData
						.getDataHTA((String) n.get("idpermohonansimati"));

				this.context.put("listHTAbaru", listHTAbaru);
				this.context.put("sizeBaru", listHTAbaru.size());

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", "");
				this.context.put("readmodemukim", "");

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				context.put("DATEUTIL", new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("show_htaa_view_table", "yes");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");
				this.context.put("show_button", "yes");

			} else if ("simpanflag".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				this.context.put("idhta", "");
				this.context.put("noHakmilik", "");

				this.context.put("nopt", "");
				this.context.put("nilai_Hta_memohon", "");
				this.context.put("nilai_Hta_mati", "");
				this.context.put("kategori", "");
				this.context.put("jenishakmilik", "");
				this.context.put("pemilikan", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("luashmp", "");
				this.context.put("luasasal", "");
				this.context.put("nocagaran", "");
				this.context.put("nopajakan", "");
				this.context.put("jenistanah", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("jenishta", "");
				this.context.put("tanggungan", "");
				this.context.put("jenisluas", "");
				this.context.put("catatan", "");
				this.context.put("noperserahan", "");
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("nopt", "");
				this.context.put("nilai_Hta_memohon", "");
				this.context.put("nilai_Hta_mati", "");
				this.context.put("kategori", "");
				this.context.put("jenishakmilik", "");
				this.context.put("pemilikan", "");
				this.context.put("luashmp", "");
				this.context.put("luasasal", "");
				this.context.put("nopajakan", "");
				this.context.put("jenistanah", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("tanggungan", "");
				this.context.put("jenisluas", "");
				this.context.put("catatan", "");
				this.context.put("noperserahan", "");
				this.context.put("buttonhtaam", "Tambah");

				logic.deletePilihanHta(getParam("idpermohonansimati"));

				String[] cbsemaks = this.request.getParameterValues("chkbox");
				if (cbsemaks != null) {
					for (int i = 0; i < cbsemaks.length; i++) {
						logic.addPilihanHta(String.valueOf((String) session
								.getAttribute("_ekptg_user_id")), String
								.valueOf(getParam("idpermohonansimati")),
								cbsemaks[i]);
					}
				}

				logic.setDataSimati(id);
				Vector listSimati = logic.getDataSimati();
				this.context.put("listSimati", listSimati);

				Vector listHTA = FrmBorangPSek17OnlineData.setDataHTAdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTA", listHTA);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				context.put("DATEUTIL", new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");
				this.context.put("show_button", "yes");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");

			} else if ("add_new".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				this.context.put("idhta", "");
				this.context.put("noHakmilik", "");

				this.context.put("nopt", "");
				this.context.put("nilai_Hta_memohon", "");
				this.context.put("nilai_Hta_mati", "");
				this.context.put("kategori", "");
				this.context.put("jenishakmilik", "");
				this.context.put("pemilikan", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("luashmp", "");
				this.context.put("luasasal", "");
				this.context.put("nocagaran", "");
				this.context.put("nopajakan", "");
				this.context.put("jenistanah", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("jenishta", "");
				this.context.put("tanggungan", "");
				this.context.put("jenisluas", "");
				this.context.put("catatan", "");
				this.context.put("noperserahan", "");
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("nopt", "");
				this.context.put("nilai_Hta_memohon", "");
				this.context.put("nilai_Hta_mati", "");
				this.context.put("kategori", "");
				this.context.put("jenishakmilik", "");
				this.context.put("pemilikan", "");
				this.context.put("luashmp", "");
				this.context.put("luasasal", "");
				this.context.put("nopajakan", "");
				this.context.put("jenistanah", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("tanggungan", "");
				this.context.put("jenisluas", "");
				this.context.put("catatan", "");
				this.context.put("noperserahan", "");
				this.context.put("buttonhtaam", "Tambah");

				logic.setDataSimati(id);
				Vector listSimati = logic.getDataSimati();
				this.context.put("listSimati", listSimati);

				// logic.setDataHTA(id);
				Vector listHTA = FrmBorangPSek17OnlineData.setDataHTAdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTA", listHTA);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				context.put("DATEUTIL", new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");
				this.context.put("show_button", "yes");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");

			} else if ("masukkan".equals(mode)) {

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				myLogger.info("IDPermohonan ::" + id);

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				addHtaam(session);
				logic.setDataSimati(id);
				Vector listSimati = logic.getDataSimati();
				this.context.put("listSimati", listSimati);

				Vector listHTA = FrmBorangPSek17OnlineData.setDataHTAdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTA", listHTA);

				// senarai harta baru
				Vector listHTAbaru = FrmBorangPSek17OnlineData
						.getDataHTA((String) n.get("idpermohonansimati"));
				this.context.put("listHTAbaru", listHTAbaru);
				this.context.put("sizeBaru", listHTAbaru.size());

				int idnegeri = 0;
				int iddaerah = 0;

				myLogger.debug("listHTA size " + listHTA.size());

				if (listHTAbaru.size() > 0) {
					Hashtable f = (Hashtable) listHTAbaru.get(0);
					idnegeri = Integer.parseInt(f.get("negeri").toString());
					iddaerah = Integer.parseInt(f.get("daerah").toString());
				}

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				Vector listmukimbyDaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listmukim", listmukimbyDaerah);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				Vector listdaerahbynegeri = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listdaerah", listdaerahbynegeri);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", "");
				this.context.put("readmodemukim", "");

				initTabData();

				context.put("DATEUTIL", new DateUtil());

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");
				this.context.put("show_button", "yes");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");

			} else if ("negerichange".equals(mode)) {

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", "");
				this.context.put("readmodemukim", disability1);

				int idnegeri = parseInt(getParam("socNegeriHtaam"));

				Vector listHTA = FrmBorangPSek17OnlineData.setDataHTAdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTA", listHTA);
				//
				// //senarai harta baru
				// Vector listHTAbaru =
				// FrmBorangPSek17OnlineData.getDataHTA((String)
				// hN.get("idpermohonansimati"));
				// this.context.put("listHTAbaru",listHTAbaru);
				// this.context.put("sizeBaru", listHTAbaru.size());

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);

				this.context.put("negeri", idnegeri);
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("noHakmilik", getParam("txtNoHakmilikHtaam"));
				this.context.put("idSimati", getParam("idSimati"));

				this.context.put("nopt", getParam("txtNoPTHtaam"));
				this.context.put("nilai_Hta_memohon",
						getParam("txtNilaiTarikhMohonHtaam"));
				this.context.put("nilai_Hta_mati",
						getParam("txtNilaiTarikhMatiHtaam"));
				this.context.put("kategori", getParam("socKategoriTanahHtaam"));
				this.context.put("jenishakmilik",
						getParam("socJenisHakmilikHtaam"));
				this.context.put("pemilikan",
						getParam("socStatusPemilikanHtaam"));

				this.context.put("luashmp", getParam("txtLuasHMpHtaam"));
				this.context.put("luasasal", getParam("txtLuasAsalHtaam"));

				this.context.put("nopajakan", getParam("txtNoPajakan"));
				this.context.put("jenistanah", getParam("socJenisTanahHtaam"));
				this.context.put("basimati", getParam("txtBahagianSimati1"));
				this.context.put("bbsimati", getParam("txtBahagianSimati2"));

				this.context.put("tanggungan", getParam("txtTanggunganHtaam"));
				this.context.put("jenisluas", getParam("socJenisLuasHtaam"));

				this.context.put("catatan", getParam("txtCatatanHtaam"));
				this.context.put("noperserahan", getParam("txtNoPersHtaam"));

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");

				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");

				this.context.put("show_kembali_htaam", "yes");

				this.context.put("show_button", "yes");

				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				
				// ADD BY PEJE TAMBAH FIELD SEKATAN & SYARAT NYATA
				this.context.put("sekatan", getParam("txtSekatan"));
				this.context.put("syaratNyata", getParam("txtSyaratNyata"));

			} else if ("negerichangeup".equals(mode)) {

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", "");
				this.context.put("readmodemukim", disability1);

				int idnegeri = parseInt(getParam("socNegeriHtaamUp"));

				this.context.put("negeriup", idnegeri);

				Vector listHTA = FrmBorangPSek17OnlineData.setDataHTAdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTA", listHTA);

				// //senarai harta baru
				// Vector listHTAbaru =
				// FrmBorangPSek17OnlineData.getDataHTA((String)
				// hN.get("idpermohonansimati"));
				// this.context.put("listHTAbaru",listHTAbaru);
				// this.context.put("sizeBaru", listHTAbaru.size());

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);

				this.context.put("negeriup", idnegeri);
				this.context.put("daerahup", "");
				this.context.put("mukimup", "");
				Vector v = new Vector();
				Hashtable k = new Hashtable();
				k.put("noHakmilik", getParam("txtNoHakmilikHtaamUp"));
				k.put("idSimati", getParam("idSimati"));
				k.put("idhta", getParam("id_htaam"));
				k.put("nopt", getParam("txtNoPTHtaamUp"));
				k.put("nilai_Hta_memohon", getParam("txtNilaiTarikhMohonHt"));
				k.put("nilai_Hta_mati", getParam("txtNilaiTarikhMatiHtaamUpd"));
				k.put("kategori", getParam("socKategoriTanahHtaamUp"));
				k.put("jenishakmilik", getParam("socJenisHakmilikHtaamUp"));
				k.put("pemilikan", getParam("socStatusPemilikanHtaamUpd"));
				k.put("negeri", idnegeri);
				k.put("daerah", "");
				k.put("luashmp", getParam("txtLuasHMpHtaamUpd"));
				k.put("luasasal", getParam("txtLuasAsalHtaamUpd"));
				k.put("nopajakan", getParam("txtNoPajakanUp"));
				k.put("jenistanah", getParam("socJenisTanahHtaamUpd"));
				k.put("basimati", getParam("txtBahagianSimati1Up"));
				k.put("bbsimati", getParam("txtBahagianSimati2Up"));
				k.put("tanggungan", getParam("txtTanggunganHtaamUp"));
				k.put("jenisluas", getParam("socJenisLuasHtaamUpd"));
				k.put("catatan", getParam("txtCatatanHt"));
				k.put("noperserahan", getParam("txtNoPersHtaamUp"));
				
				// ADD BY PEJE TAMBAH FIELD SEKATAN & SYARAT NYATA
				k.put("sekatan", getParam("txtSekatan"));
				k.put("syaratNyata", getParam("txtSyaratNyata"));
				
				v.addElement(k);

				this.context.put("listHTAid", v);

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");

				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");

				this.context.put("show_kembali_htaam", "yes");

				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");
				this.context.put("show_button", "yes");

			} else if ("daerahchangeup".equals(mode)) {

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", "");
				this.context.put("readmodemukim", "");

				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamUp"));
				int iddaerah = Integer.parseInt(getParam("socDaerahHtaamUp"));

				Vector listHTA = FrmBorangPSek17OnlineData.setDataHTAdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTA", listHTA);

				// //senarai harta baru
				// Vector listHTAbaru =
				// FrmBorangPSek17OnlineData.getDataHTA((String)
				// hN.get("idpermohonansimati"));
				// this.context.put("listHTAbaru",listHTAbaru);
				// this.context.put("sizeBaru", listHTAbaru.size());

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				this.context.put("negeriup", idnegeri);
				this.context.put("daerahup", "");
				this.context.put("mukimup", "");
				Vector v = new Vector();
				Hashtable h = new Hashtable();
				h.put("noHakmilik", getParam("txtNoHakmilikHtaamUp"));
				h.put("idSimati", getParam("idSimati"));
				h.put("idhta", getParam("id_htaam"));
				h.put("nopt", getParam("txtNoPTHtaamUp"));
				h.put("nilai_Hta_memohon", getParam("txtNilaiTarikhMohonHt"));
				h.put("nilai_Hta_mati", getParam("txtNilaiTarikhMatiHtaamUpd"));
				h.put("kategori", getParam("socKategoriTanahHtaamUp"));
				h.put("jenishakmilik", getParam("socJenisHakmilikHtaamUp"));
				h.put("pemilikan", getParam("socStatusPemilikanHtaamUpd"));
				h.put("negeri", idnegeri);
				h.put("daerah", iddaerah);
				h.put("mukim", "");
				h.put("luashmp", getParam("txtLuasHMpHtaamUpd"));
				h.put("luasasal", getParam("txtLuasAsalHtaamUpd"));
				h.put("nopajakan", getParam("txtNoPajakanUp"));
				h.put("jenistanah", getParam("socJenisTanahHtaamUpd"));
				h.put("basimati", getParam("txtBahagianSimati1Up"));
				h.put("bbsimati", getParam("txtBahagianSimati2Up"));
				h.put("tanggungan", getParam("txtTanggunganHtaamUp"));
				h.put("jenisluas", getParam("socJenisLuasHtaamUpd"));
				h.put("catatan", getParam("txtCatatanHt"));
				h.put("noperserahan", getParam("txtNoPersHtaamUp"));
				
				// ADD BY PEJE TAMBAH FIELD SEKATAN & SYARAT NYATA
				h.put("sekatan", getParam("txtSekatan"));
				h.put("syaratNyata", getParam("txtSyaratNyata"));
				
				v.addElement(h);

				this.context.put("listHTAid", v);
				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");
				this.context.put("show_button", "yes");
			} else if ("getHtaam".equals(mode)) {

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				// ID RECORD
				String idhtaam = getParam("idhtaam");

				logic.setDataHTAbyIdHtaam(idhtaam);
				Vector listHTAid = logic.getDataHTAbyIdHtaam();
				this.context.put("listHTAid", listHTAid);
				this.context.put("idhtaam", idhtaam);

				Vector listHTA = FrmBorangPSek17OnlineData.setDataHTAdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTA", listHTA);

				// //senarai harta baru
				// Vector listHTAbaru =
				// FrmBorangPSek17OnlineData.getDataHTA((String)
				// hN.get("idpermohonansimati"));
				// this.context.put("listHTAbaru",listHTAbaru);
				// this.context.put("sizeBaru", listHTAbaru.size());

				int idnegeri = 0;
				int iddaerah = 0;

				if (listHTAid.size() > 0) {
					Hashtable f = (Hashtable) listHTAid.get(0);
					idnegeri = Integer.parseInt(f.get("negeri").toString());
					iddaerah = Integer.parseInt(f.get("daerah").toString());
				}
				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				Vector listmukimbyDaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listmukim", listmukimbyDaerah);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				Vector listdaerahbynegeri = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listdaerah", listdaerahbynegeri);

				this.context.put("readmodenegeri", disability1);
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);
				this.context.put("readmode", disability1);

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");

				this.context.put("show_kemaskini_htaam", "yes");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");

				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_button", "yes");

				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");

				initTabData();
			} else if ("batalHtaam".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				Vector listHTA = FrmBorangPSek17OnlineData.setDataHTAdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTA", listHTA);

				// //senarai harta baru
				// Vector listHTAbaru =
				// FrmBorangPSek17OnlineData.getDataHTA((String)
				// hN.get("idpermohonansimati"));
				// this.context.put("listHTAbaru",listHTAbaru);
				// this.context.put("sizeBaru", listHTAbaru.size());

				String idhtaam = getParam("idhtaamid");
				logic.setDataHTAbyIdHtaam(idhtaam);
				Vector listHTAid = logic.getDataHTAbyIdHtaam();
				this.context.put("listHTAid", listHTAid);
				this.context.put("idhtaam", idhtaam);
				int idnegeri = 0;
				int iddaerah = 0;

				if (listHTAid.size() > 0) {
					Hashtable f = (Hashtable) listHTAid.get(0);
					idnegeri = Integer.parseInt(f.get("negeri").toString());
					iddaerah = Integer.parseInt(f.get("daerah").toString());
				}

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				Vector listmukimbyDaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listmukim", listmukimbyDaerah);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				Vector listdaerahbynegeri = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listdaerah", listdaerahbynegeri);

				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);
				this.context.put("readmode", "");

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");

				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");

				this.context.put("show_kembali_htaam", "");

				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_button", "yes");

				initTabData();

			}

			else if ("hapusHtaam".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				String idhtaam = getParam("idhtaamid");
				FrmPrmhnnSek8Data.deleteHtaam(idhtaam);

				logic.setDataSimati(id);
				Vector listSimati = logic.getDataSimati();
				this.context.put("listSimati", listSimati);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();

				context.put("DATEUTIL", new DateUtil());
				Vector listHTA = FrmBorangPSek17OnlineData.setDataHTAdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTA", listHTA);
				int idnegeri = 0;
				int iddaerah = 0;

				// senarai harta baru
				Vector listHTAbaru = FrmBorangPSek17OnlineData
						.getDataHTA((String) n.get("idpermohonansimati"));
				this.context.put("listHTAbaru", listHTAbaru);
				this.context.put("sizeBaru", listHTAbaru.size());

				if (listHTAbaru.size() > 0) {
					Hashtable f = (Hashtable) listHTAbaru.get(0);
					idnegeri = Integer.parseInt(f.get("negeri").toString());
					iddaerah = Integer.parseInt(f.get("daerah").toString());
				}

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				Vector listmukimbyDaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listmukim", listmukimbyDaerah);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				Vector listdaerahbynegeri = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listdaerah", listdaerahbynegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_button", "yes");
				this.context.put("show_kembali_htaam", "");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
			} else if ("kemaskiniHtaam".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);
				;

				String idhtaam = getParam("idhtaamid");
				logic.setDataHTAbyIdHtaam(idhtaam);
				this.context.put("idhtaam", idhtaam);

				logic.setDataSimati(id);
				Vector listSimati = logic.getDataSimati();
				this.context.put("listSimati", listSimati);

				Vector listHTA = FrmBorangPSek17OnlineData.setDataHTAdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTA", listHTA);

				// //senarai harta baru
				// Vector listHTAbaru =
				// FrmBorangPSek17OnlineData.getDataHTA((String)
				// hN.get("idpermohonansimati"));
				// this.context.put("listHTAbaru",listHTAbaru);
				// this.context.put("sizeBaru", listHTAbaru.size());

				Vector listHTAid = logic.getDataHTAbyIdHtaam();
				this.context.put("listHTAid", listHTAid);
				int idnegeri = 0;
				int iddaerah = 0;

				if (listHTAid.size() > 0) {
					Hashtable f = (Hashtable) listHTAid.get(0);
					idnegeri = Integer.parseInt(f.get("negeri").toString());
					iddaerah = Integer.parseInt(f.get("daerah").toString());
				}
				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				Vector listmukimbyDaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listmukim", listmukimbyDaerah);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				Vector listdaerahbynegeri = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listdaerah", listdaerahbynegeri);

				this.context.put("readmodenegeri", disability1);
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);
				this.context.put("readmode", "");

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_button", "yes");
				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");

				initTabData();

			} else if ("simpanHtaam".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				String idhtaam = getParam("idhtaamid");
				this.context.put("idhtaam", idhtaam);

				updateHtaam(session);

				this.context.put("readmodenegeri", disability1);
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);
				this.context.put("readmode", disability1);
				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");
				this.context.put("show_kemaskini_htaam", "yes");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");
				this.context.put("show_kembali_htaam", "");
				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");
				this.context.put("show_button", "yes");

				Vector listHTA = FrmBorangPSek17OnlineData.setDataHTAdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTA", listHTA);

				// //senarai harta baru
				// Vector listHTAbaru =
				// FrmBorangPSek17OnlineData.getDataHTA((String)
				// hN.get("idpermohonansimati"));
				// this.context.put("listHTAbaru",listHTAbaru);
				// this.context.put("sizeBaru", listHTAbaru.size());

				logic.setDataHTAbyIdHtaam(idhtaam);
				Vector listHTAid = logic.getDataHTAbyIdHtaam();
				this.context.put("listHTAid", listHTAid);
				this.context.put("idhtaam", idhtaam);

				int idnegeri = 0;
				int iddaerah = 0;

				if (listHTAid.size() > 0) {
					Hashtable f = (Hashtable) listHTAid.get(0);
					idnegeri = Integer.parseInt(f.get("negeri").toString());
					iddaerah = Integer.parseInt(f.get("daerah").toString());
				}

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				Vector listmukimbyDaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listmukim", listmukimbyDaerah);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				Vector listdaerahbynegeri = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listdaerah", listdaerahbynegeri);

				this.context.put("selectedTabatas", getParam("tabIdatas"));
				this.context.put("selectedTabtengah", getParam("tabIdtengah"));
				this.context.put("selectedTabbawah", getParam("tabIdbawah"));
				this.context.put("selectedTabtepi", getParam("tabIdtepi"));
				this.context.put("SimpanStatus", getParam("simpanStatus"));

			} else if ("daerahchange".equals(mode)) {

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", "");
				this.context.put("readmodemukim", "");

				int iddaerah = parseInt(getParam("socDaerahHtaam").toString());
				int idnegeri = parseInt(getParam("socNegeriHtaam").toString());

				Vector listHTA = FrmBorangPSek17OnlineData.setDataHTAdulu(
						(String) hN.get("idpermohonansimatilama"), (String) n
								.get("batalkuasatadbir"), (String) n
								.get("lantiktadbir"), (String) n
								.get("batalamanah"), (String) n
								.get("lantikamanah"));
				this.context.put("listHTA", listHTA);

				// //senarai harta baru
				// Vector listHTAbaru =
				// FrmBorangPSek17OnlineData.getDataHTA((String)
				// hN.get("idpermohonansimati"));
				// this.context.put("listHTAbaru",listHTAbaru);
				// this.context.put("sizeBaru", listHTAbaru.size());

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				this.context.put("negeri", idnegeri);
				this.context.put("daerah", iddaerah);
				this.context.put("mukim", "");
				this.context.put("noHakmilik", getParam("txtNoHakmilikHtaam"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("nopt", getParam("txtNoPTHtaam"));
				this.context.put("nilai_Hta_memohon",
						getParam("txtNilaiTarikhMohonHtaam"));
				this.context.put("nilai_Hta_mati",
						getParam("txtNilaiTarikhMatiHtaam"));
				this.context.put("kategori", getParam("socKategoriTanahHtaam"));
				this.context.put("jenishakmilik",
						getParam("socJenisHakmilikHtaam"));
				this.context.put("pemilikan",
						getParam("socStatusPemilikanHtaam"));
				this.context.put("luashmp", getParam("txtLuasHMpHtaam"));
				this.context.put("luasasal", getParam("txtLuasAsalHtaam"));
				this.context.put("nopajakan", getParam("txtNoPajakan"));
				this.context.put("jenistanah", getParam("socJenisTanahHtaam"));
				this.context.put("basimati", getParam("txtBahagianSimati1"));
				this.context.put("bbsimati", getParam("txtBahagianSimati2"));
				this.context.put("tanggungan", getParam("txtTanggunganHtaam"));
				this.context.put("jenisluas", getParam("socJenisLuasHtaam"));
				this.context.put("catatan", getParam("txtCatatanHtaam"));
				this.context.put("noperserahan", getParam("txtNoPersHtaam"));
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_button", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				
				// ADD BY PEJE TAMBAH FIELD SEKATAN & SYARAT NYATA
				this.context.put("sekatan", getParam("txtSekatan"));
				this.context.put("syaratNyata", getParam("txtSyaratNyata"));
			}
			vm = "app/ppk/frmPrmhnnBorangPHTAAH.jsp";
		}

		else if ("pengesahan_permohonan".equals(submit)) {

			initTabData();//
			Vector list = FrmBorangPSek17OnlineData
					.semakDataSimati(getParam("idPermohonan"));
			this.context.put("View", list);
			Hashtable n = (Hashtable) list.get(0);
			myLogger.debug(n);
			String id1 = (String) n.get("idsimati");
			String id2 = (String) n.get("idPemohon");
			String id = getParam("idPermohonan");

			this.context.put("idStatus", n.get("idstatus").toString());
			this.context.put("IdPermohonan", id);
			this.context.put("idSimati", id1);
			this.context.put("idPemohon", id2);

			if (n.get("nopermohonanonline") == "")
				n.put("nopermohonanonline", "");
			this.context.put("nopermohonanonline", n.get("nopermohonanonline")
					.toString());

			if (n.get("idfail") == "")
				n.put("idfail", "");
			this.context.put("idfail", n.get("idfail").toString());

			Vector maklumatPermohonan = FrmBorangPSek17OnlineData
					.getDetailsPemohonDetails(getParam("idPermohonan"));
			this.context.put("DetailPemohon", maklumatPermohonan);

			if (n.get("flaghubungan") == "")
				n.put("flaghubungan", "0");
			int flaghub = Integer.parseInt(n.get("flaghubungan").toString());
			this.context.put("idpermohonansimati", n.get("idpermohonansimati"));
			this.context.put("flaghub", flaghub);

			// negeri based on negeri hta
			if (n.get("idpermohonansimati") == "")
				n.put("idpermohonansimati", "0");
			int dataHta = logic.getPengesahan((String) n
					.get("idpermohonansimati"));

			// Vector getdaerah = logic.getDaerahNegeri((String)
			// n.get("idpermohonanterdahulu"));

			// elly alter 15.11.2010
			Vector getdaerah = logic
					.getDaerahNegeri((String) n.get("idsimati"));

			Hashtable m1 = (Hashtable) getdaerah.get(0);

			if (n.get("idstatus") == "")
				n.put("idstatus", "");
			if (n.get("idstatus") == "171") {
				this.context.put("setmode", "disabled class=\"disabled\"");
			} else if (n.get("idstatus") == "160") {
				this.context.put("setmode", "");
			}

			int x = 0;

			// if (dataHta==0){
			Vector listnegeri = logic.getListnegeri(parseInt(m1.get(
					"idnegerimhn").toString()));
			this.context.put("senaraiNegeriByPpkUnit", listnegeri);
			this.context.put("saizData", listnegeri.size());
			this.context.put("typex", "0");
			/*
			 * }else{ Vector listNegeriByPpkUnit =
			 * logiconline.getListNegeriByhta(n.get("idpermohonansimati"));
			 * this.context.put("senaraiNegeriByPpkUnit",listNegeriByPpkUnit);
			 * this.context.put("saizData",listNegeriByPpkUnit.size());
			 * this.context.put("typex","1"); //}
			 */

			int idnegerimhn = 0;
			int iddaerahmhn = 0;

			if (dataHta == 0) {
				this.context.put("selNegeri", parseInt(m1.get("idnegerimhn")
						.toString()));
				this.context.put("selDaerah", parseInt(m1.get("iddaerahmhn")
						.toString()));
				this.context.put("negerimhn", parseInt(m1.get("idnegerimhn")
						.toString()));
				this.context.put("daerahmhn", parseInt(m1.get("iddaerahmhn")
						.toString()));

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(parseInt(m1.get("idnegerimhn")
								.toString()));
				this.context.put("selectedDaerah", listnegeribydaerah);

				Vector getPpkAddressNegerimhn = logiconline
						.getListDaerahByPpkUnitSelected(parseInt(m1.get(
								"idnegerimhn").toString()), parseInt(m1.get(
								"iddaerahmhn").toString()));
				this.context
						.put("selectedDaerahByUnit", getPpkAddressNegerimhn);

				Vector getAddressPpkDaerahmhn = logiconline
						.getAddress(parseInt(m1.get("iddaerahmhn").toString()));
				this.context.put("selectedPpkAddress", getAddressPpkDaerahmhn);
			} else {
				if (n.get("idnegerimhn").toString() != "") {
					idnegerimhn = parseInt(n.get("idnegerimhn").toString());
					iddaerahmhn = parseInt(n.get("iddaerahmhn").toString());

					this.context.put("selNegeri", 0);
					this.context.put("selDaerah", 0);
					this.context.put("negerimhn", idnegerimhn);
					this.context.put("daerahmhn", iddaerahmhn);

					// Vector getPpkAddressDaerah =
					// logiconline.getListDaerahByPpkUnit(idnegerimhn);
					Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
							.getListDaerahbyNegeri(idnegerimhn);
					this.context.put("selectedDaerah", listnegeribydaerah);

					Vector getPpkAddressNegerimhn = logiconline
							.getListDaerahByPpkUnitSelected(idnegerimhn,
									iddaerahmhn);
					this.context.put("selectedDaerahByUnit",
							getPpkAddressNegerimhn);

					Vector getAddressPpkDaerahmhn = logiconline
							.getAddress(parseInt(n.get("iddaerahmhn")
									.toString()));
					this.context.put("selectedPpkAddress",
							getAddressPpkDaerahmhn);
				} else if (n.get("idnegerimhn").toString() != ""
						&& n.get("iddaerahmhn").toString() != "") {
					Vector getAddressPpkDaerahmhn = logiconline
							.getAddress(parseInt(n.get("iddaerahmhn")
									.toString()));
					this.context.put("selectedPpkAddress",
							getAddressPpkDaerahmhn);
				} else {
					initInputPpkPengesahan();
				}
			}

			if ("pengesahanView".equals(mode)) {
				initTabData();

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("selNegeri", "0");
				this.context.put("selDaerah", "0");
				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				if (n.get("idnegerimhn").toString() != "") {

					iddaerahmhn = Integer.parseInt(n.get("iddaerahmhn")
							.toString());

					Vector getAddressPpkDaerahmhn = logiconline
							.getAddress(iddaerahmhn);
					this.context.put("selectedPpkAddress",
							getAddressPpkDaerahmhn);

					Hashtable m = (Hashtable) getAddressPpkDaerahmhn.get(0);
					String namaPejabat = m.get("namapejabat").toString();
					String alamatOne = m.get("alamatOne").toString();
					String alamatTwo = m.get("alamatTwo").toString();
					String alamatThree = m.get("alamatThree").toString();
					String poskod = m.get("poskod").toString();
					String notel = m.get("notel").toString();
					String notel_sambungan = m.get("notel_sambungan")
							.toString();
					String negeriNama = m.get("negerinama").toString();

					this.context.put("namapejabat", namaPejabat);
					this.context.put("alamat1", alamatOne);
					this.context.put("alamat2", alamatTwo);
					this.context.put("alamat3", alamatThree);
					this.context.put("poskod", poskod);
					this.context.put("no_tel", notel);
					this.context.put("no_tel_samb", notel_sambungan);

				} else {
					iddaerahmhn = Integer.parseInt(m1.get("iddaerahmhn")
							.toString());

					Vector getAddressPpkDaerahmhn = logiconline
							.getAddress(iddaerahmhn);
					this.context.put("selectedPpkAddress",
							getAddressPpkDaerahmhn);

					Hashtable m = (Hashtable) getAddressPpkDaerahmhn.get(0);
					String namaPejabat = m.get("namapejabat").toString();
					String alamatOne = m.get("alamatOne").toString();
					String alamatTwo = m.get("alamatTwo").toString();
					String alamatThree = m.get("alamatThree").toString();
					String poskod = m.get("poskod").toString();
					String notel = m.get("notel").toString();
					String notel_sambungan = m.get("notel_sambungan")
							.toString();
					String negeriNama = m.get("negerinama").toString();

					this.context.put("namapejabat", namaPejabat);
					this.context.put("alamat1", alamatOne);
					this.context.put("alamat2", alamatTwo);
					this.context.put("alamat3", alamatThree);
					this.context.put("poskod", poskod);
					this.context.put("no_tel", notel);
					this.context.put("no_tel_samb", notel_sambungan);
				}
			} else if ("selection_daerah".equals(mode)) {
				initTabData();

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("selDaerah", "0");

				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				if (!getParam("socNegeriPengesahan").equals("")) {
					String idnegeri = getParam("socNegeriPengesahan");
					this.context.put("selNegeri",
							getParam("socNegeriPengesahan"));
					Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
							.getListDaerahbyNegeri(Integer.parseInt(getParam(
									"socNegeriPengesahan").toString()));
					this.context.put("selectedDaerah", listnegeribydaerah);

				} else {
					this.context.put("selectedDaerah", "");
					this.context.put("selNegeri", 0);
				}
			} else if ("ppkAddressView".equals(mode)) {
				initTabData();

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				this.context.put("selNegeri", getParam("socNegeriPengesahan"));
				this.context.put("selDaerah", getParam("socDaerahPengesahan"));
				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(Integer.parseInt(getParam(
								"socNegeriPengesahan").toString()));
				this.context.put("selectedDaerah", listnegeribydaerah);
				Vector getAddressPpk = logiconline.getAddress(Integer
						.parseInt(getParam("socDaerahPengesahan").toString()));
				this.context.put("selectedPpkAddress", getAddressPpk);
			}
			vm = "app/ppk/frmPrmhnnBorangPPengesahan.jsp";
		} else if ("harta_alih".equals(submit)) {
			initTabData();
			this.context.put("SimpanStatus", getParam("simpanStatus"));
			this.context.put("Id", getParam("idPermohonan"));

			Vector listJenisha = FrmPrmhnnSek8DaftarSek8Data.getJenisHa();
			this.context.put("ViewJenisHa", listJenisha);

			Vector maklumatPermohonan = FrmBorangPSek17OnlineData
					.getDetailsPemohonDetails(getParam("idPermohonan"));
			this.context.put("DetailPemohon", maklumatPermohonan);
			Hashtable hN = (Hashtable) maklumatPermohonan.get(0);

			Vector listNegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
			this.context.put("listnegeri", listNegeri);

			Vector list = FrmBorangPSek17OnlineData
					.semakDataSimati(getParam("idPermohonan"));
			this.context.put("View", list);
			Hashtable h = (Hashtable) list.get(0);
			String id1 = (String) h.get("idsimati");
			String id2 = (String) h.get("idPemohon");
			String id = getParam("idPermohonan");
			if (h.get("flaghubungan") == "")
				h.put("flaghubungan", "0");
			int flaghub = Integer.parseInt(h.get("flaghubungan").toString());
			String idpermohonansimati = (String) h.get("idpermohonansimati");
			this.context.put("flaghub", flaghub);
			this.context.put("idSimati", id1);
			this.context.put("idPemohon", id2);
			this.context.put("idSimati", id1);

			if ("view_harta_alih".equals(mode)) {
				this.context.put("idJenisHa", "");
				this.context.put("tutup", "");
				this.context.put("socJenisHa", "0");
				this.context.put("bhgnmati1", "");
				this.context.put("bhgnmati2", "");
				this.context.put("norujukan", "");
				this.context.put("nosijil", "");
				this.context.put("nilaitarikhmohon", "0.00");
				this.context.put("nilaitarikhmati", "0.00");
				this.context.put("nilaiunit", "0.00");
				this.context.put("nilaimohon", "0.00");
				this.context.put("bilunit", "0");
				this.context.put("nilaiseunit", "0.00");
				this.context.put("agensi", "");
				this.context.put("add1", "");
				this.context.put("add2", "");
				this.context.put("add3", "");
				this.context.put("poskod", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("catatan", "");
				this.context.put("idha", "0");

				readability1 = " ";
				readability2 = "readonly class=\"disabled\"";
				disability1 = "disabled";
				disability2 = "";

				this.context.put("id", id);
				this.context.put("id2", id2);
				this.context.put("id1", id1);

				view_mode_ppkha(session, idpermohonansimati);
				view_sum_ppkha(session, id1);
				view_overallsum_ppkha(session, id1);
				view_overallsummati_ppkha(session, id1);

				String socJenisha = "0";
				socJenisha = getParam("socJenisHartaAlih");
				this.context.put("socJenisHa", socJenisha);

				Vector listppkha = logic.getDataHa((String) hN
						.get("idpermohonansimatilama"));
				this.context.put("listHa", listppkha);

				Vector listppkhabaru = logic.getDataHaBaru((String) h
						.get("idpermohonansimati"));
				this.context.put("listHaBaru", listppkhabaru);

				int countList = listppkha.size();
				this.context.put("jumList", countList);

				Vector sumppkha = FrmPrmhnnSek8DaftarSek8Data.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				view_mode_ppkhta(session, id);
				FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
				Vector listppkhta = frmPrmhnnSek8SenaraiHTATHData.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				view_mode_ppkha2(session, id);
				Vector listppkha2 = frmPrmhnnSek8SenaraiHTATHData.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				Vector overallnilai = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSum();
				this.context.put("overall", overallnilai);

				Vector overallnilaimati = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				this.context
						.put("selectNegeri", HTML.SelectNegeri("socNegeri"));
				this.context.put("readmode", disability1);

				this.context.put("show_kemaskini_button", "yes");
				this.context.put("show_simpan_button", "");
				this.context.put("show_batal_button", "");
				this.context.put("show_kembali_butt", "");

				context.put("DATEUTIL", new DateUtil());

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));
				this.context.put("EventStatus", 0);

				// sum_nilai_ppkpermohonan(session, id, id1,
				// idpermohonansimati);

			} else if ("simpanflagAlih".equals(mode)) {
				this.context.put("idJenisHa", "");
				this.context.put("tutup", "");
				this.context.put("socJenisHa", "0");
				this.context.put("bhgnmati1", "");
				this.context.put("bhgnmati2", "");
				this.context.put("norujukan", "");
				this.context.put("nosijil", "");
				this.context.put("nilaitarikhmohon", "0.00");
				this.context.put("nilaiunit", "0.00");
				this.context.put("nilaimohon", "0.00");
				this.context.put("bilunit", "0");
				this.context.put("nilaiseunit", "0.00");
				this.context.put("agensi", "");
				this.context.put("add1", "");
				this.context.put("add2", "");
				this.context.put("add3", "");
				this.context.put("poskod", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("catatan", "");
				this.context.put("idha", "0");

				readability1 = " ";
				readability2 = "readonly class=\"disabled\"";
				disability1 = "disabled";
				disability2 = "";

				this.context.put("id", id);
				this.context.put("id2", id2);
				this.context.put("id1", id1);

				logic
						.deletePilihanHa((String) hN
								.get("idpermohonansimatilama"));

				String[] cbsemaks = this.request.getParameterValues("chkbox");
				if (cbsemaks != null) {
					for (int i = 0; i < cbsemaks.length; i++) {
						logic.addPilihanHa(String.valueOf((String) session
								.getAttribute("_ekptg_user_id")), String
								.valueOf((String) hN
										.get("idpermohonansimatilama")),
								cbsemaks[i]);
					}
				}

				view_mode_ppkha(session, idpermohonansimati);
				view_sum_ppkha(session, id1);
				view_overallsum_ppkha(session, id1);
				view_overallsummati_ppkha(session, id1);

				String socJenisha = "0";
				socJenisha = getParam("socJenisHartaAlih");
				this.context.put("socJenisHa", socJenisha);

				Vector listppkha = logic.getDataHa((String) hN
						.get("idpermohonansimatilama"));
				this.context.put("listHa", listppkha);

				Vector listppkhabaru = logic.getDataHaBaru((String) h
						.get("idpermohonansimati"));
				this.context.put("listHaBaru", listppkhabaru);

				int countList = listppkha.size();
				this.context.put("jumList", countList);

				Vector sumppkha = FrmPrmhnnSek8DaftarSek8Data.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				view_mode_ppkhta(session, id);
				FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
				Vector listppkhta = frmPrmhnnSek8SenaraiHTATHData.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				view_mode_ppkha2(session, id);
				Vector listppkha2 = frmPrmhnnSek8SenaraiHTATHData.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				Vector overallnilai = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSum();
				this.context.put("overall", overallnilai);

				Vector overallnilaimati = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				this.context
						.put("selectNegeri", HTML.SelectNegeri("socNegeri"));
				this.context.put("readmode", disability1);

				this.context.put("show_kemaskini_button", "yes");
				this.context.put("show_simpan_button", "");
				this.context.put("show_batal_button", "");
				this.context.put("show_kembali_butt", "");

				context.put("DATEUTIL", new DateUtil());

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));
				this.context.put("EventStatus", 0);

				// sum_nilai_ppkpermohonan(session, id, id1,
				// idpermohonansimati);

			} else if ("tambah_harta".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");
				this.context.put("idPermohonan", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);
				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("idJenisHa", 0);

				this.context.put("tutup", "");
				this.context.put("EventStatus", 0);
				this.context.put("tutup", "");
				this.context.put("bhgnmati1", "");
				this.context.put("bhgnmati2", "");
				this.context.put("norujukan", "");
				this.context.put("nosijil", "");
				this.context.put("nilaitarikhmohon", "0.00");
				this.context.put("nilaimohon", "0.00");
				this.context.put("bilunit", "0");
				this.context.put("nilaiseunit", "0.00");
				this.context.put("agensi", "");
				this.context.put("add1", "");
				this.context.put("add2", "");
				this.context.put("add3", "");
				this.context.put("poskod", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("catatan", "");

				String socJenishax = getParam("socJenisHartaAlih");
				this.context.put("socJenisHa", socJenishax);

				view_mode_ppkha(session, idpermohonansimati);
				Vector listppkha = logic.getDataHa((String) hN
						.get("idpermohonansimatilama"));
				this.context.put("listHa", listppkha);

				Vector listppkhabaru = logic.getDataHaBaru((String) h
						.get("idpermohonansimati"));
				this.context.put("listHaBaru", listppkhabaru);

				int countList = listppkha.size();
				this.context.put("jumList", countList);

				Vector sumppkha = FrmPrmhnnSek8DaftarSek8Data.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				view_mode_ppkhta(session, id);
				FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
				Vector listppkhta = frmPrmhnnSek8SenaraiHTATHData.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				view_mode_ppkha2(session, id);
				Vector listppkha2 = frmPrmhnnSek8SenaraiHTATHData.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				Vector overallnilai = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSum();
				this.context.put("overall", overallnilai);

				Vector overallnilaimati = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

			} else if ("simpan_harta".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("idJenisHa", "");
				this.context.put("tutup", "");
				this.context.put("socJenisHa", "");
				this.context.put("bhgnmati1", "");
				this.context.put("bhgnmati2", "");
				this.context.put("norujukan", "");
				this.context.put("nosijil", "");
				this.context.put("nilaitarikhmohon", "0.00");
				// added by elly
				this.context.put("nilaitarikhmati", "0.00");
				// end
				this.context.put("nilaimohon", "0");
				this.context.put("nilaiunit", "0.00");
				this.context.put("bilunit", "0");
				this.context.put("nilaiseunit", "0.00");
				this.context.put("agensi", "");
				this.context.put("add1", "");
				this.context.put("add2", "");
				this.context.put("add3", "");
				this.context.put("poskod", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("catatan", "");
				this.context.put("EventStatus", 0);
				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("id2", id2);

				System.out.println("addHa");
				addHa(session);

				view_mode_ppkha(session, idpermohonansimati);
				Vector listppkha = logic.getDataHa((String) hN
						.get("idpermohonansimatilama"));
				this.context.put("listHa", listppkha);

				Vector listppkhabaru = logic.getDataHaBaru((String) h
						.get("idpermohonansimati"));
				this.context.put("listHaBaru", listppkhabaru);

				view_sum_ppkha(session, id1);
				Vector sumppkha = FrmPrmhnnSek8DaftarSek8Data.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				view_overallsum_ppkha(session, id1);
				view_overallsummati_ppkha(session, id1);

				int countList = listppkha.size();
				this.context.put("jumList", countList);

				view_mode_ppkhta(session, id);
				FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
				Vector listppkhta = frmPrmhnnSek8SenaraiHTATHData.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				view_mode_ppkhtath(session, id);
				Vector listppkhtath = frmPrmhnnSek8SenaraiHTATHData.getHtath();
				this.context.put("listHtaht2", listppkhtath);
				int countListhtath = listppkhtath.size();
				this.context.put("jumListHtaht", countList);

				view_mode_ppkha2(session, id);
				Vector listppkha2 = frmPrmhnnSek8SenaraiHTATHData.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				Vector overallnilai = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSum();
				this.context.put("overall", overallnilai);

				Vector overallnilaimati = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));
				this.context.put("socJenisHa", 0);
			} else if ("batal_harta".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("tutup", "");
				String eventstatus = getParam("eventStatus");
				this.context.put("EventStatus", eventstatus);

				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("id2", id2);

				view_mode_ppkha(session, idpermohonansimati);
				Vector listppkha = logic.getDataHa((String) hN
						.get("idpermohonansimatilama"));
				this.context.put("listHa", listppkha);

				Vector listppkhabaru = logic.getDataHaBaru((String) h
						.get("idpermohonansimati"));
				this.context.put("listHaBaru", listppkhabaru);

				int countList = listppkha.size();
				this.context.put("jumList", countList);

				Vector sumppkha = FrmPrmhnnSek8DaftarSek8Data.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				view_mode_ppkhta(session, id);
				FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
				Vector listppkhta = frmPrmhnnSek8SenaraiHTATHData.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				view_mode_ppkha2(session, id);
				Vector listppkha2 = frmPrmhnnSek8SenaraiHTATHData.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				Vector overallnilai = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSum();
				this.context.put("overall", overallnilai);

				Vector overallnilaimati = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

			} else if ("edit_harta".equals(mode)) {

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("snegeri", "");
				this.context.put("tutup", "yes");
				String eventstatus = getParam("eventStatus");
				this.context.put("EventStatus", eventstatus);
				this.context.put("new_data", "no");

				String idha = "0";
				idha = getParam("idha");

				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("id2", id2);
				this.context.put("idha", idha);

				Vector selectedppkha = FrmPrmhnnSek8DaftarSek8Data
						.setSelectedDataHa(id1, idha);
				this.context.put("DataHa", selectedppkha);
				Hashtable v = (Hashtable) selectedppkha.get(0);
				if (v.get("idnegeri").toString() != "") {
					// Vector listDaerahByNegeri =
					// FrmPrmhnnSek8DaftarSek8Data.getListDaerahbyNegeri(Integer.parseInt(v.get("idnegeri").toString()));
					Vector listBandarByNegeri = logiconline.getBandarByNegeri(v
							.get("idnegeri").toString());
					this.context.put("listDaerahbyNegeri", listBandarByNegeri);
				}
				view_mode_ppkha(session, idpermohonansimati);
				Vector listppkha = logic.getDataHa((String) hN
						.get("idpermohonansimatilama"));
				this.context.put("listHa", listppkha);

				Vector listppkhabaru = logic.getDataHaBaru((String) h
						.get("idpermohonansimati"));
				this.context.put("listHaBaru", listppkhabaru);

				int countList = listppkha.size();
				this.context.put("jumList", countList);

				Vector sumppkha = FrmPrmhnnSek8DaftarSek8Data.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				view_mode_ppkhta(session, id);
				FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
				Vector listppkhta = frmPrmhnnSek8SenaraiHTATHData.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				view_mode_ppkha2(session, id);
				Vector listppkha2 = frmPrmhnnSek8SenaraiHTATHData.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				Vector overallnilai = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSum();
				this.context.put("overall", overallnilai);

				Vector overallnilaimati = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				this.context.put("selectedTabatas", getParam("tabIdatas"));
				this.context.put("selectedTabtengah", getParam("tabIdtengah"));
				this.context.put("selectedTabbawah", getParam("tabIdbawah"));
				this.context.put("selectedTabtepi", getParam("tabIdtepi"));
				this.context.put("SimpanStatus", getParam("simpanStatus"));
			} else if ("kemaskini_harta".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("tutup", "yes");
				String eventstatus = getParam("eventStatus");
				this.context.put("EventStatus", eventstatus);
				this.context.put("new_data", "no");

				this.context.put("id1", id1);
				String idha = "0";

				if (!getParam("idha").equals("")) {
					idha = getParam("idha");
				}

				this.context.put("id", id);
				this.context.put("id2", id2);
				this.context.put("idha", idha);
				this.context.put("socJenisHa", 0);

				// view_data_ppkha(session,id1,idha);
				Vector selectedppkha = FrmPrmhnnSek8DaftarSek8Data
						.setSelectedDataHa(id1, idha);
				this.context.put("DataHa", selectedppkha);
				Hashtable v = (Hashtable) selectedppkha.get(0);
				if (v.get("idnegeri") == "")
					v.put("idnegeri", "0");
				Vector listBandarByNegeri = logiconline.getBandarByNegeri(v
						.get("idnegeri").toString());
				this.context.put("listDaerahbyNegeri", listBandarByNegeri);

				Vector listppkha = logic.getDataHa((String) hN
						.get("idpermohonansimatilama"));
				this.context.put("listHa", listppkha);

				Vector listppkhabaru = logic.getDataHaBaru((String) h
						.get("idpermohonansimati"));
				this.context.put("listHaBaru", listppkhabaru);

				int countList = listppkha.size();
				this.context.put("jumList", countList);

				Vector sumppkha = FrmPrmhnnSek8DaftarSek8Data.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				view_mode_ppkhta(session, id);
				FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
				Vector listppkhta = frmPrmhnnSek8SenaraiHTATHData.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				view_mode_ppkha2(session, id);
				Vector listppkha2 = frmPrmhnnSek8SenaraiHTATHData.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				Vector overallnilai = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSum();
				this.context.put("overall", overallnilai);

				Vector overallnilaimati = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				this.context.put("selectedTabatas", getParam("tabIdatas"));
				this.context.put("selectedTabtengah", getParam("tabIdtengah"));
				this.context.put("selectedTabbawah", getParam("tabIdbawah"));
				this.context.put("selectedTabtepi", getParam("tabIdtepi"));
				this.context.put("SimpanStatus", getParam("simpanStatus"));

			} else if ("update_harta".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("tutup", "yes");
				String eventstatus = getParam("eventStatus");
				this.context.put("EventStatus", eventstatus);

				String idha = getParam("idha");

				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("id2", id2);
				this.context.put("idha", idha);
				updateHa(session);
				// view_data_ppkha(session,id1,idha);
				view_sum_ppkha(session, id1);
				view_overallsum_ppkha(session, id1);
				view_overallsummati_ppkha(session, id1);

				// listkan senarai harta alih
				// view_mode_ppkha(session,idpermohonansimati);
				Vector listppkha = logic.getDataHa((String) hN
						.get("idpermohonansimatilama"));
				this.context.put("listHa", listppkha);

				Vector listppkhabaru = logic.getDataHaBaru((String) h
						.get("idpermohonansimati"));
				this.context.put("listHaBaru", listppkhabaru);

				int countList = listppkha.size();
				this.context.put("jumList", countList);

				Vector sumppkha = FrmPrmhnnSek8DaftarSek8Data.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				Vector selectedppkha = FrmPrmhnnSek8DaftarSek8Data
						.setSelectedDataHa(id1, idha);
				this.context.put("DataHa", selectedppkha);

				view_mode_ppkhta(session, id);
				FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
				Vector listppkhta = frmPrmhnnSek8SenaraiHTATHData.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				view_mode_ppkha2(session, id);
				Vector listppkha2 = frmPrmhnnSek8SenaraiHTATHData.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				Vector overallnilai = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSum();
				this.context.put("overall", overallnilai);

				Vector overallnilaimati = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				this.context.put("selectedTabatas", getParam("tabIdatas"));
				this.context.put("selectedTabtengah", getParam("tabIdtengah"));
				this.context.put("selectedTabbawah", getParam("tabIdbawah"));
				this.context.put("selectedTabtepi", getParam("tabIdtepi"));
				this.context.put("SimpanStatus", getParam("simpanStatus"));

			} else if ("hapus_harta".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("idJenisHa", "");
				this.context.put("tutup", "");
				this.context.put("socJenisHa", "");
				this.context.put("bhgnmati1", "");
				this.context.put("bhgnmati2", "");
				this.context.put("norujukan", "");
				this.context.put("nosijil", "");
				this.context.put("nilaitarikhmohon", "0.00");
				this.context.put("nilaiunit", "0.00");
				this.context.put("nilaimohon", "0.00");
				this.context.put("bilunit", "0");
				this.context.put("nilaiseunit", "0.00");
				this.context.put("agensi", "");
				this.context.put("add1", "");
				this.context.put("add2", "");
				this.context.put("add3", "");
				this.context.put("poskod", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("catatan", "");

				this.context.put("tutup", "");
				this.context.put("EventStatus", 0);

				this.context.put("id1", id1);

				// id_tbl harta alih
				String idha = getParam("idha");

				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("idha", idha);

				delete_mode_ppkha(session, id1, idha);
				Vector selectedppkha = FrmPrmhnnSek8DaftarSek8Data
						.setSelectedDataHa(id1, idha);
				this.context.put("DataHa", selectedppkha);

				view_mode_ppkha(session, idpermohonansimati);
				Vector listppkha = logic.getDataHa((String) hN
						.get("idpermohonansimatilama"));
				this.context.put("listHa", listppkha);

				Vector listppkhabaru = logic.getDataHaBaru((String) h
						.get("idpermohonansimati"));
				this.context.put("listHaBaru", listppkhabaru);

				int countList = listppkha.size();
				this.context.put("jumList", countList);

				Vector sumppkha = FrmPrmhnnSek8DaftarSek8Data.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				view_mode_ppkhta(session, id);
				FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
				Vector listppkhta = frmPrmhnnSek8SenaraiHTATHData.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				view_mode_ppkha2(session, id);
				Vector listppkha2 = frmPrmhnnSek8SenaraiHTATHData.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				Vector overallnilai = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSum();
				this.context.put("overall", overallnilai);

				Vector overallnilaimati = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				this.context.put("selectedTabatas", getParam("tabIdatas"));
				this.context.put("selectedTabtengah", getParam("tabIdtengah"));
				this.context.put("selectedTabbawah", getParam("tabIdbawah"));
				this.context.put("selectedTabtepi", getParam("tabIdtepi"));
				this.context.put("SimpanStatus", getParam("simpanStatus"));

			} else if ("get_daerah".equals(mode)) {

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");
				Vector listBandarByNegeri = logiconline
						.getBandarByNegeri(getParam("socNegeri"));
				this.context.put("listDaerahbyNegeri", listBandarByNegeri);

				if (!getParam("valueSocHa").equals("0")) {
					this.context.put("socJenisHa", getParam("valueSocHa"));
					this.context.put("idJenisHa", 0);
					this.context.put("tutup", "");
					this.context.put("EventStatus", 0);
					this.context.put("negeri", getParam("socNegeri"));
				} else if (!getParam("valueJenisHa").equals("0")) {
					this.context.put("idJenisHa", getParam("valueJenisHa"));
					this.context.put("socJenisHa", 0);
					this.context.put("tutup", "yes");
					this.context.put("EventStatus", 3);
					this.context.put("idha", getParam("idha"));
					this.context.put("id1", getParam("id1"));
					this.context.put("snegeri", getParam("socNegeri"));
				}

				this.context.put("bhgnmati1", getParam("txtBhgnSimati1"));
				this.context.put("bhgnmati2", getParam("txtBhgnSimati2"));
				this.context.put("norujukan", getParam("txtNoRujukan"));
				this.context.put("nosijil", getParam("txtNoSijil"));
				this.context.put("nilaitarikhmohon",
						getParam("txtNilaiTarikhMohon"));
				this.context.put("nilaimohon", getParam("txtNilaiTarikhMohon"));
				this.context.put("bilunit", getParam("txtBilUnit"));
				this.context.put("nilaiseunit", getParam("txtNilaiSeunit"));
				this.context.put("agensi", getParam("txtAgensi"));
				this.context.put("catatan", getParam("txtCatatan"));
				this.context.put("add1", getParam("txtAlamatHartaAlih1"));
				this.context.put("add2", getParam("txtAlamatHartaAlih2"));
				this.context.put("add3", getParam("txtAlamatHartaAlih3"));
				this.context.put("poskod", getParam("txtPoskodPemohon"));
				this.context.put("daerah", getParam("socDaerah"));
				this.context.put("catatan", getParam("txtCatatan"));
				this.context.put("disabled", "");
				this.context.put("new_data", "yes");

				// id_tbl harta alih
				this.context.put("id", id);
				this.context.put("id1", id1);
				view_mode_ppkha(session, idpermohonansimati);
				if (!getParam("idha").equals("0")) {
					this.context.put("idha", getParam("idha"));
					Vector selectedppkha = FrmPrmhnnSek8DaftarSek8Data
							.setSelectedDataHa(id1, (String) getParam("idha"));
					this.context.put("DataHa", selectedppkha);
				}

				Vector listppkha = logic.getDataHa((String) hN
						.get("idpermohonansimatilama"));
				this.context.put("listHa", listppkha);

				Vector listppkhabaru = logic.getDataHaBaru((String) h
						.get("idpermohonansimati"));
				this.context.put("listHaBaru", listppkhabaru);

				int countList = listppkha.size();
				this.context.put("jumList", countList);

				Vector sumppkha = FrmPrmhnnSek8DaftarSek8Data.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				view_mode_ppkhta(session, id);
				FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
				Vector listppkhta = frmPrmhnnSek8SenaraiHTATHData.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				view_mode_ppkha2(session, id);
				Vector listppkha2 = frmPrmhnnSek8SenaraiHTATHData.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				Vector overallnilai = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSum();
				this.context.put("overall", overallnilai);

				Vector overallnilaimati = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

			}
			vm = "app/ppk/frmPrmhnnBorangPHA.jsp";
		} else if ("cetak_surat".equals(submit)) {

			this.context.put("command", "cetak_surat");
			String idPermohonan = getParam("idPermohonan");
			int idnegeri = Integer.parseInt(getParam("socNegeriPengesahan")
					.toString());
			int iddaerah = Integer.parseInt(getParam("socDaerahPengesahan")
					.toString());

			Vector idfail = logiconline.getIdFail(idPermohonan);
			Hashtable n = (Hashtable) idfail.get(0);

			logiconline.insertDaerahMohon(idnegeri, iddaerah, idPermohonan,
					(String) session.getAttribute("_ekptg_user_id"), (String) n
							.get("noidfail"));

			Vector getAddressPpk = logiconline.getAddress(iddaerah);
			this.context.put("selectedPpkAddress", getAddressPpk);

			vm = "app/ppk/frmPrmhnnBorangPPengesahan.jsp";

		} else if ("searchByIc".equals(submit)) {
			context.remove("statusList");
			vm = "app/ppk/online/searchIc.jsp";
		} else if ("cariIc".equals(submit)) {
			String noKP = getParam("icNo");
			Vector v = getStatusPermohonanFacade().getPermohonanList(noKP);
			context.put("statusList", v);
			vm = "app/ppk/online/searchIc.jsp";
		} else if ("searchByIcSiMati".equals(submit)) {
			context.remove("statusList");
			vm = "app/ppk/online/searchSiMati.jsp";
		} else if ("cariSiMati".equals(submit)) {
			String noKPSiMati = getParam("icNo");
			Vector v = getStatusPermohonanFacade().getPermohonanSiMatiList(
					noKPSiMati);
			context.put("statusList", v);
			vm = "app/ppk/online/searchSiMati.jsp";
		}
		// end of seksyen 17
		else {
			/*Vector listDataPemohon = logiconline.getUserParticular((String) session.getAttribute("_ekptg_user_id"));
			Hashtable n = (Hashtable) listDataPemohon.get(0);

			this.context.put("noKpBaru1", n.get("icno1").toString());
			this.context.put("noKpBaru2", n.get("icno2").toString());
			this.context.put("noKpBaru3", n.get("icno3").toString());*/
			
			this.context.put("noKpBaru1", "");
			this.context.put("noKpBaru2", "");
			this.context.put("noKpBaru3", "");

		}

		senaraiFail = FrmPrmhnnStatusPengunaOnlineData.getSenaraiTugasanDraf("",
				(String) session.getAttribute("_ekptg_user_id"),
				(String) session.getAttribute("_portal_role"),
				getParam("kppemohon"), 
				getParam("kpsimati"), 
				"yes");
		// context.put("senaraiFail",senaraiFail);             
		setupPage(session, action, senaraiFail);

		this.context.put("kppemohon", getParam("kppemohon"));
		this.context.put("kpsimati", getParam("kpsimati"));

		myLogger.info("vm:" + vm);
		Template template = this.engine.getTemplate(vm);
		return vm;

	}

	private IStatusPermohonan getStatusPermohonanFacade() {
		IStatusPermohonan sp = new StatusPermohonanFacade();
		return sp;
	}

	private String generateNoPermohonanOnlineSeksyen17(String idsimati,
			String usid, String idfailSeksyen8, String idPemohon)
			throws Exception {
		return FrmBorangPSek17OnlineData.generateNoPermohonanOnlineSeksyen17(
				idsimati, usid, idfailSeksyen8, idPemohon);
	}

	private void check_mode_pemohon17(HttpSession session, String kpBaru,
			String kpLama, String kpLain) throws Exception {
		FrmBorangPSek17OnlineData.checkPemohon(kpBaru, kpLama, kpLain);
	}

	private void check_mode_pemohon(HttpSession session, String kpBaru,
			String kpLama, String kpLain) throws Exception {
		logiconline.checkPemohon(kpBaru, kpLama, kpLain);
	}

	private void check_data_pemohon(HttpSession session, String kpBaru,
			String kpLama, String kpLain) throws Exception {
		logiconline.semakDetailPemohon(kpBaru, kpLama, kpLain);
	}

	public void setupPage(HttpSession session, String action, Vector senaraiFail) {
		try {

			this.context.put("totalRecords", senaraiFail.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null
					|| this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10
						: getParamAsInteger("itemsPerPage");
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

			Paging paging = new Paging(session, senaraiFail, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1; // reset page number
			this.context.put("senaraitugasan", paging.getPage(page));
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

	// seksyen17
	public void initSenaraiSemakP(String noPermohonan, String nofaillama,
			String namadaerah, String namanegeri, String hartatinggal,
			String batalamanah, String lantikamanah, String lantikpentadbir,
			String batalpentadbir, String perintahlama, String perintahbaru) {

		if (noPermohonan != "") {
			String noPermohonan1 = noPermohonan;
			this.context.put("nopermohonan", noPermohonan1);
		} else {
			this.context.put("nopermohonan", "");
		}

		if (nofaillama != "") {
			String nofaillama1 = nofaillama;
			this.context.put("nofail", nofaillama1);
		} else {
			this.context.put("nofail", "");
		}

		if (namadaerah != "") {
			String namaDaerah1 = namadaerah;
			this.context.put("daerah", namaDaerah1);
		} else {
			this.context.put("daerah", "");
		}
		if (namanegeri != "") {
			String namaNegeri1 = namanegeri;
			this.context.put("negeri", namaNegeri1);
		} else {
			this.context.put("negeri", "");
		}

		if (perintahlama != "") {
			String perintahlama1 = perintahlama;
			this.context.put("perintahlama", perintahlama1);
		} else {
			this.context.put("perintahlama", "");
		}

		if (perintahbaru != "") {
			String perintahbaru1 = perintahbaru;
			this.context.put("perintahbaru", perintahbaru1);
		} else {
			this.context.put("perintahbaru", "");
		}

		if (hartatinggal.equals("Y")) {
			this.context.put("check1", "checked");
		} else {
			this.context.put("check1", "");
		}
		if (batalamanah.equals("Y")) {
			this.context.put("check2", "checked");
		} else {
			this.context.put("check2", "");
		}
		if (lantikamanah.equals("Y")) {
			this.context.put("check3", "checked");
		} else {
			this.context.put("check3", "");
		}
		if (lantikpentadbir.equals("Y")) {
			this.context.put("check5", "checked");
		} else {
			this.context.put("check5", "");
		}
		if (batalpentadbir.toString().equals("Y")) {
			this.context.put("check4", "checked");
		} else {
			this.context.put("check4", "");
		}
	}

	private void simpandatasemak(HttpSession session) throws Exception {
		Hashtable h = null;
		h = new Hashtable();
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("idPermohonan", getParam("idPermohonan"));
		if (getParam("cb1").equals("Y")) {
			h.put("cb1", getParam("cb1"));
		} else {
			h.put("cb1", "T");
		}
		if (getParam("cb3").equals("Y")) {
			h.put("cb3", getParam("cb3"));
		} else {
			h.put("cb3", "T");
		}
		if (getParam("cb4").equals("Y")) {
			h.put("cb4", getParam("cb4"));
		} else {
			h.put("cb4", "T");
		}
		if (getParam("cb6").equals("Y")) {
			h.put("cb6", getParam("cb6"));
		} else {
			h.put("cb6", "T");
		}
		if (getParam("cb7").equals("Y")) {
			h.put("cb7", getParam("cb7"));
		} else {
			h.put("cb7", "T");
		}
		if (getParam("cb8").equals("Y")) {
			h.put("cb8", getParam("cb8"));
		} else {
			h.put("cb8", "T");
		}

		h.put("perintahdahulu", getParam("perintahdahulu"));
		h.put("perintahminta", getParam("perintahminta"));
		System.out.println("h---> " + h);
		logic.simpandatasemak(h);
	}

	private void simpandatasemak2(HttpSession session, String idPermohonan)
			throws Exception {
		Hashtable h = null;
		h = new Hashtable();
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("idPermohonan", idPermohonan);
		if (getParam("cb1").equals("Y")) {
			h.put("cb1", getParam("cb1"));
		} else {
			h.put("cb1", "T");
		}
		if (getParam("cb3").equals("Y")) {
			h.put("cb3", getParam("cb3"));
		} else {
			h.put("cb3", "T");
		}
		if (getParam("cb4").equals("Y")) {
			h.put("cb4", getParam("cb4"));
		} else {
			h.put("cb4", "T");
		}
		if (getParam("cb6").equals("Y")) {
			h.put("cb6", getParam("cb6"));
		} else {
			h.put("cb6", "T");
		}
		if (getParam("cb7").equals("Y")) {
			h.put("cb7", getParam("cb7"));
		} else {
			h.put("cb7", "T");
		}
		if (getParam("cb8").equals("Y")) {
			h.put("cb8", getParam("cb8"));
		} else {
			h.put("cb8", "T");
		}

		h.put("perintahdahulu", getParam("perintahdahulu"));
		h.put("perintahminta", getParam("perintahminta"));
		System.out.println("h---> " + h);
		logic.simpandatasemak(h);
	}

	public void initBandarNegeriBaruSingle() throws Exception {
		String idNegeriTetap = "";
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = "";
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}

		this.context
				.put(
						"selectNegeriTetap",
						HTML
								.SelectNegeri(
										"socNegeriTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetap()\""));
		this.context.put("selectBandarTetap", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socDaerah", Long.parseLong(idBandarTetap),
				"style=\"width:225px;text-transform:uppercase;\""));
	}

	public void initTabData() {
		this.context.put("selectedTabatas", getParam("tabIdatas"));
		this.context.put("selectedTabtengah", getParam("tabIdtengah"));
		this.context.put("selectedTabbawah", getParam("tabIdbawah"));
		this.context.put("selectedTabtepi", getParam("tabIdtepi"));
	}

	public void initValueBandarNegeriSinglehtaamx(String socNegeriTetap,
			String socBandarTetap) throws Exception {
		String idNegeriTetap = socNegeriTetap;
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = socBandarTetap;
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}

		this.context
				.put(
						"selectNegerixTetap",
						HTML
								.SelectNegeri(
										"socNegerixTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetaphtaam()\""));
		this.context.put("selectBandarxTetap", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socDaerahx", Long.parseLong(idBandarTetap),
				"style=\"width:225px;text-transform:uppercase;\""));
	}

	public void initHtaam1() {
		this.context.put("radio2", "yes");
		this.context.put("radio3", "");
		this.context.put("checked1", "checked");
		this.context.put("checked2", "");
		this.context.put("checked3", "");
		this.context.put("idhta", "");
		this.context.put("noHakmilik", "");
		this.context.put("nopt", "");
		this.context.put("nilai_Hta_memohon", "");
		this.context.put("nilai_Hta_mati", "");
		this.context.put("kategori", "");
		this.context.put("jenishakmilik", "");
		this.context.put("pemilikan", "");
		this.context.put("negeri", "");
		this.context.put("daerah", "");
		this.context.put("mukim", "");
		this.context.put("luashmp", "");
		this.context.put("luasasal", "");
		this.context.put("nocagaran", "");
		this.context.put("nopajakan", "");
		this.context.put("jenistanah", "");
		this.context.put("basimati", "");
		this.context.put("bbsimati", "");
		this.context.put("jenishta", "");
		this.context.put("tanggungan", "");
		this.context.put("jenisluas", "");
		this.context.put("catatan", "");
		this.context.put("noperserahan", "");
		this.context.put("namapemaju", "");
		this.context.put("alamatpemaju1", "");
		this.context.put("alamatpemaju2", "");
		this.context.put("alamatpemaju3", "");
		this.context.put("poskodpemaju", "");
		this.context.put("bandarpemaju", "");
		this.context.put("negeripemaju", "");
		this.context.put("alamathta1", "");
		this.context.put("alamathta2", "");
		this.context.put("alamathta3", "");
		this.context.put("poskodhta", "");
		this.context.put("bandarhta", "");
		this.context.put("noperjanjian", "");
		this.context.put("tarikhperjanjian", "");
		this.context.put("namarancangan", "");
		this.context.put("noroh", "");
		this.context.put("nolot", "");
		this.context.put("nolot", "");
		this.context.put("nocagaran", "");
		this.context.put("buttonhtaam", "Tambah");
	}

	public void initValueBandarNegeriSingle(String socNegeriTetap,
			String socBandarTetap) throws Exception {
		String idNegeriTetap = socNegeriTetap;
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = socBandarTetap;
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}

		this.context
				.put(
						"selectNegeriTetap",
						HTML
								.SelectNegeri(
										"socNegeriTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetap()\""));
		this.context.put("selectBandarTetap", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socBandarTetap", Long.parseLong(idBandarTetap),
				"style=\"width:225px;text-transform:uppercase;\""));
	}

	public void initBandarNegeriBaruSinglehtaamx() throws Exception {
		String idNegeriTetap = "";
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = "";
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}

		this.context
				.put(
						"selectNegerixTetap",
						HTML
								.SelectNegeri(
										"socNegerixTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"javascript:onChangeBandarTetaphtaam()\""));
		this.context.put("selectBandarxTetap", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socDaerahx", Long.parseLong(idBandarTetap),
				"style=\"width:225px;text-transform:uppercase;\""));
	}

	public void textfieldHtaamValue() {
		this.context.put("mukim", getParam("socMukimHtaamX"));
		this.context.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
		this.context.put("alamatpemaju1", getParam("txtAlamatPemaju1HtaamX"));
		this.context.put("alamatpemaju2", getParam("txtAlamatPemaju2HtaamX"));
		this.context.put("alamatpemaju3", getParam("txtAlamatPemaju3HtaamX"));
		this.context.put("poskodpemaju", getParam("txtPoskodPemaju1HtaamX"));
		this.context.put("luasasal", getParam("txtLuasAsalHtaamX"));
		this.context.put("jenisluas", getParam("socJenisLuasHtaamX"));
		this.context.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
		this.context.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
		this.context.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));
		this.context.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
		this.context.put("noperjanjian", getParam("txtNoPerjanjianHtaamX"));
		this.context.put("tarikhperjanjian",
				getParam("txtTarikhPerjanjianHtaamX"));
		this.context.put("namarancangan", getParam("txtNamaRancanganHtaamX"));
		this.context.put("noroh", getParam("txtNoRoh"));
		this.context.put("nolot", getParam("txtLotId"));
		this.context.put("jeniskepentingan", getParam("txtKepentinganHtaamX"));
	}

	public void addHtaamX(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("idSimati", getParam("idSimati"));
		h.put("nopt", getParam("txtNoPTHtaamX"));

		if (!getParam("txtNilaiTarikhMohonHtaaX").equals("")) {
			h.put("nilai_Hta_memohon", Double
					.parseDouble(getParam("txtNilaiTarikhMohonHtaaX")));
		} else {
			h.put("nilai_Hta_memohon", 0.0);
		}

		if (!getParam("txtNilaiTarikhMatiHtaamX").equals("")) {
			h.put("nilai_Hta_mati", Double
					.parseDouble(getParam("txtNilaiTarikhMatiHtaamX")));
		} else {
			h.put("nilai_Hta_mati", 0.0);
		}

		if (!getParam("socKategoriTanahHtaamX").equals("")) {
			h.put("kategori", Integer
					.parseInt(getParam("socKategoriTanahHtaamX")));
		} else {
			h.put("kategori", 0);
		}

		if (!getParam("socJenisHakmilikHtaamX").equals("")) {
			h.put("jenishakmilik", Integer
					.parseInt(getParam("socJenisHakmilikHtaamX")));
		} else {
			h.put("jenishakmilik", 0);
		}

		h.put("pemilikan", getParam("socStatusPemilikanHtaamX"));

		if (!getParam("socNegeriHtaamX").equals("")) {
			h.put("negeri", Integer.parseInt(getParam("socNegeriHtaamX")));
		} else {
			h.put("negeri", 0);
		}

		if (!getParam("socDaerahHtaamX").equals("")) {
			h.put("daerah", Integer.parseInt(getParam("socDaerahHtaamX")));
		} else {
			h.put("daerah", 0);
		}

		if (!getParam("socMukimHtaamX").equals("")) {
			h.put("mukim", Integer.parseInt(getParam("socMukimHtaamX")));
		} else {
			h.put("mukim", 0);
		}

		h.put("luashmp", getParam("txtLuasHMpHtaamX"));
		h.put("luasasal", getParam("txtLuasAsalHtaamX"));
		h.put("nopajakan", getParam("txtNoPajakanX"));
		h.put("jenistanah", getParam("socJenisTanahHtaamX"));

		if (!getParam("txtBahagianSimati1X").equals("")) {
			h
					.put("basimati", Integer
							.parseInt(getParam("txtBahagianSimati1X")));
		} else {
			h.put("basimati", 0);
		}

		if (!getParam("txtBahagianSimati2X").equals("")) {
			h
					.put("bbsimati", Integer
							.parseInt(getParam("txtBahagianSimati2X")));
		} else {
			h.put("bbsimati", 0);
		}

		if (!getParam("socJenisLuasHtaamX").equals("")) {
			h
					.put("jenisluas", Integer
							.parseInt(getParam("socJenisLuasHtaamX")));
		} else {
			h.put("jenisluas", 0);
		}
		h.put("tanggungan", getParam("txtTanggunganHtaamX"));
		h.put("catatan", getParam("txtCatatanHtaamX"));
		h.put("noperserahan", getParam("txtNoPersHtaamX"));
		h.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
		h.put("alamatpemaju1", getParam("txtAlamatPemaju1HtaamX"));
		h.put("alamatpemaju2", getParam("txtAlamatPemaju2HtaamX"));
		h.put("alamatpemaju3", getParam("txtAlamatPemaju3HtaamX"));
		h.put("poskodpemaju", getParam("txtPoskodPemaju1HtaamX"));
		h.put("bandarpemaju", getParam("txtBandarPemaju1HtaamX"));
		h.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
		h.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
		h.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));
		h.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
		if (getParam("socNegeriTetap").equals("")) {
			h.put("id_negeripemaju", 0);
		} else {
			h.put("id_negeripemaju", Integer
					.parseInt(getParam("socNegeriTetap")));
		}
		if (getParam("socBandarTetap").equals("")) {
			h.put("id_bandarpemaju", 0);
		} else {
			h.put("id_bandarpemaju", Integer
					.parseInt(getParam("socBandarTetap")));
		}
		if (getParam("socDaerahx").equals("")) {
			h.put("id_bandarhta", 0);
		} else {
			h.put("id_bandarhta", Integer.parseInt(getParam("socDaerahx")));
		}
		h.put("bandarhta", getParam("txtBandarHartaHtaamX"));
		h.put("noperjanjian", getParam("txtNoPerjanjianHtaamX"));
		h.put("tarikhperjanjian", getParam("txtTarikhPerjanjianHtaamX"));
		h.put("namarancangan", getParam("txtNamaRancanganHtaamX"));
		h.put("noroh", getParam("txtNoRohHtaamX"));
		h.put("nolot", getParam("txtLotIdHtaamX"));
		h.put("nocagaran", getParam("txtNoCagaranX"));
		h.put("flag", getParam("radioHtaamViewX1"));
		h.put("idpermohonansimati", getParam("idpermohonansimati"));
		h.put("jeniskepentingan", getParam("txtKepentinganHtaamX"));
		FrmPrmhnnSek8Data.addHtaamX(h);
	}

	public void initGetParamHtaamxValue(int idnegeri) {
		Vector v = new Vector();
		Hashtable h = new Hashtable();
		h.put("idhta", getParam("idhtaamXid"));
		h.put("negeri", idnegeri);
		h.put("idSimati", getParam("idSimatiX"));
		h.put("nopt", getParam("txtNoPTHtaamX"));
		h.put("nilai_Hta_memohon", getParam("txtNilaiTarikhMohonHtaaX"));
		h.put("nilai_Hta_mati", getParam("txtNilaiTarikhMatiHtaamX"));
		h.put("kategori", getParam("socKategoriTanahHtaamX"));
		h.put("luashmp", getParam("txtLuasHMpHtaamX"));
		h.put("luasasal", getParam("txtLuasAsalHtaamX"));
		h.put("nopajakan", getParam("txtNoPajakanX"));
		h.put("jenistanah", getParam("socJenisTanahHtaamX"));
		h.put("basimati", getParam("txtBahagianSimati1X"));
		h.put("bbsimati", getParam("txtBahagianSimati2X"));
		h.put("tanggungan", getParam("txtTanggunganHtaamX"));
		h.put("jenisluas", getParam("socJenisLuasHtaamX"));
		h.put("catatan", getParam("txtCatatanHtaamX"));
		h.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
		h.put("alamatpemaju1", getParam("txtAlamatPemaju1HtaamX"));
		h.put("alamatpemaju2", getParam("txtAlamatPemaju2HtaamX"));
		h.put("alamatpemaju3", getParam("txtAlamatPemaju3HtaamX"));
		h.put("poskodpemaju", getParam("txtPoskodPemaju1HtaamX"));
		h.put("bandarpemaju", getParam("txtBandarPemaju1HtaamX"));
		h.put("negeripemaju", getParam("socNegeriPemajuHtaamX"));
		h.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
		h.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
		h.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));
		h.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
		h.put("bandarhta", getParam("txtBandarHartaHtaamX"));
		h.put("noperjanjian", getParam("txtNoPerjanjianHtaamX"));
		h.put("tarikhperjanjian", getParam("txtTarikhPerjanjianHtaamX"));
		h.put("namarancangan", getParam("txtNamaRancanganHtaamX"));
		h.put("noroh", getParam("txtNoRohHtaamX"));
		h.put("nolot", getParam("txtLotIdHtaamX"));
		h.put("nolot", getParam("txtLotIdHtaamX"));
		h.put("flag", getParam("flag"));
		h.put("nocagaran", getParam("txtNoCagaranX"));
		v.addElement(h);
		this.context.put("listHTAXid", v);
	}

	public void initValueBandarNegeriSingleDisabled(String socNegeriTetap,
			String socBandarTetap) throws Exception {
		String idNegeriTetap = socNegeriTetap;
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = socBandarTetap;
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}

		this.context
				.put(
						"selectNegeriTetap",
						HTML
								.SelectNegeri(
										"socNegeriTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"class=\"disabled\" disabled style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetap()\""));
		this.context
				.put(
						"selectBandarTetap",
						HTML
								.SelectBandarByNegeri(idNegeriTetap,
										"socBandarTetap", Long
												.parseLong(idBandarTetap),
										"class=\"disabled\" disabled style=\"width:225px;text-transform:uppercase;\""));
	}

	public void initValueBandarNegeriSingleDisabledhtaamx(
			String socNegeriTetap, String socBandarTetap) throws Exception {
		String idNegeriTetap = socNegeriTetap;
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = socBandarTetap;
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}

		this.context
				.put(
						"selectNegerixTetap",
						HTML
								.SelectNegeri(
										"socNegerixTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"class=\"disabled\" disabled style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetaphtaam()\""));
		this.context
				.put(
						"selectBandarxTetap",
						HTML
								.SelectBandarByNegeri(idNegeriTetap,
										"socDaerahx", Long
												.parseLong(idBandarTetap),
										"class=\"disabled\" disabled style=\"width:225px;text-transform:uppercase;\""));
	}

	public void initTextfieldHtaamxBlank() {
		this.context.put("radio2", "yes");
		this.context.put("radio3", "");
		this.context.put("checked2", "");
		this.context.put("checked1", "checked");
		this.context.put("checked3", "");
		this.context.put("idhta", "");
		this.context.put("noHakmilik", "");
		this.context.put("nopt", "");
		this.context.put("nilai_Hta_memohon", "");
		this.context.put("nilai_Hta_mati", "");
		this.context.put("kategori", "");
		this.context.put("jenishakmilik", "");
		this.context.put("pemilikan", "");
		this.context.put("negeri", "");
		this.context.put("daerah", "");
		this.context.put("mukim", "");
		this.context.put("luashmp", "");
		this.context.put("luasasal", "");
		this.context.put("nocagaran", "");
		this.context.put("nopajakan", "");
		this.context.put("jenistanah", "");
		this.context.put("basimati", "");
		this.context.put("bbsimati", "");
		this.context.put("jenishta", "");
		this.context.put("tanggungan", "");
		this.context.put("jenisluas", "");
		this.context.put("catatan", "");
		this.context.put("noperserahan", "");
		this.context.put("namapemaju", "");
		this.context.put("alamatpemaju1", "");
		this.context.put("alamatpemaju2", "");
		this.context.put("alamatpemaju3", "");
		this.context.put("poskodpemaju", "");
		this.context.put("bandarpemaju", "");
		this.context.put("negeripemaju", "");
		this.context.put("alamathta1", "");
		this.context.put("alamathta2", "");
		this.context.put("alamathta3", "");
		this.context.put("poskodhta", "");
		this.context.put("bandarhta", "");
		this.context.put("noperjanjian", "");
		this.context.put("tarikhperjanjian", "");
		this.context.put("namarancangan", "");
		this.context.put("noroh", "");
		this.context.put("nolot", "");
		this.context.put("nolot", "");
		this.context.put("nocagaran", "");
	}

	private void updateHtaamX(HttpSession session) throws Exception {
		Vector v = new Vector();
		Hashtable h = new Hashtable();
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("idhta", getParam("idhtaamXid"));
		h.put("idSimati", getParam("idSimati"));
		h.put("nopt", getParam("txtNoPTHtaamX"));
		if (getParam("txtNilaiTarikhMohonHtaaX") != "") {
			h.put("nilai_Hta_memohon", Double
					.parseDouble(getParam("txtNilaiTarikhMohonHtaaX")));
		} else {
			h.put("nilai_Hta_memohon", 0.0);
		}

		if (getParam("txtNilaiTarikhMatiHtaamX") != "") {
			h.put("nilai_Hta_mati", Double
					.parseDouble(getParam("txtNilaiTarikhMatiHtaamX")));
		} else {
			h.put("nilai_Hta_mati", 0.0);
		}

		if (getParam("socKategoriTanahHtaamX") != "") {
			h.put("kategori", Integer
					.parseInt(getParam("socKategoriTanahHtaamX")));
		} else {
			h.put("kategori", 0);
		}

		if (getParam("socJenisHakmilikHtaamX") != "") {
			h.put("jenishakmilik", Integer
					.parseInt(getParam("socJenisHakmilikHtaamX")));
		} else {
			h.put("jenishakmilik", 0);
		}

		h.put("pemilikan", getParam("socStatusPemilikanHtaamX"));

		if (getParam("socNegeriHtaamX") != "") {
			h.put("negeri", Integer.parseInt(getParam("socNegeriHtaamX")));
		} else {
			h.put("negeri", 0);
		}

		if (getParam("socDaerahHtaamX") != "") {
			h.put("daerah", Integer.parseInt(getParam("socDaerahHtaamX")));
		} else {
			h.put("daerah", 0);
		}

		if (getParam("socMukimHtaamX") != "") {
			h.put("mukim", Integer.parseInt(getParam("socMukimHtaamX")));
		} else {
			h.put("mukim", 0);
		}

		h.put("luashmp", getParam("txtLuasHMpHtaamX"));
		h.put("luasasal", getParam("txtLuasAsalHtaamX"));
		h.put("nopajakan", getParam("txtNoPajakanX"));
		h.put("jenistanah", getParam("socJenisTanahHtaamX"));

		if (getParam("txtBahagianSimati1X") != "") {
			h
					.put("basimati", Integer
							.parseInt(getParam("txtBahagianSimati1X")));
		} else {
			h.put("basimati", 0);
		}

		if (getParam("txtBahagianSimati2X") != "") {
			h
					.put("bbsimati", Integer
							.parseInt(getParam("txtBahagianSimati2X")));
		} else {
			h.put("bbsimati", 0);
		}

		if (getParam("socJenisLuasHtaamX") != "") {
			h
					.put("jenisluas", Integer
							.parseInt(getParam("socJenisLuasHtaamX")));
		} else {
			h.put("jenisluas", 0);
		}

		h.put("tanggungan", getParam("txtTanggunganHtaamX"));
		h.put("catatan", getParam("txtCatatanHtaamX"));
		h.put("noperserahan", getParam("txtNoPersHtaamX"));
		h.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
		h.put("alamatpemaju1", getParam("txtAlamatPemaju1HtaamX"));
		h.put("alamatpemaju2", getParam("txtAlamatPemaju2HtaamX"));
		h.put("alamatpemaju3", getParam("txtAlamatPemaju3HtaamX"));
		h.put("poskodpemaju", getParam("txtPoskodPemaju1HtaamX"));
		if (getParam("socNegeriTetap").equals("")) {
			h.put("id_negeripemaju", 0);
		} else {
			h.put("id_negeripemaju", Integer
					.parseInt(getParam("socNegeriTetap")));
		}
		if (getParam("socBandarTetap").equals("")) {
			h.put("id_bandarpemaju", 0);
		} else {
			h.put("id_bandarpemaju", Integer
					.parseInt(getParam("socBandarTetap")));
		}
		h.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
		h.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
		h.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));
		h.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
		if (getParam("socDaerahx").equals("")) {
			h.put("id_bandarhta", 0);
		} else {
			h.put("id_bandarhta", Integer.parseInt(getParam("socDaerahx")));
		}
		h.put("noperjanjian", getParam("txtNoPerjanjianHtaamX"));
		h.put("tarikhperjanjian", getParam("txtTarikhPerjanjianHtaamX"));
		h.put("namarancangan", getParam("txtNamaRancanganHtaamX"));
		h.put("noroh", getParam("txtNoRohHtaamX"));
		h.put("nolot", getParam("txtLotIdHtaamX"));
		h.put("nolot", getParam("txtLotIdHtaamX"));
		h.put("nocagaran", getParam("txtNoCagaranX"));
		h.put("flag", getParam("radioHtaamViewX1"));
		h.put("idpermohonansimati", getParam("idpermohonansimati"));
		h.put("jeniskepentingan", getParam("txtKepentinganHtaamX"));
		FrmPrmhnnSek8Data.updateHtaamX(h);
	}

	public void initTambahHtaamField() {
		this.context.put("idhta", "");
		this.context.put("noHakmilik", "");

		this.context.put("nopt", "");
		this.context.put("nilai_Hta_memohon", "");
		this.context.put("nilai_Hta_mati", "");
		this.context.put("kategori", "");
		this.context.put("jenishakmilik", "");
		this.context.put("pemilikan", "");
		this.context.put("negeri", "");
		this.context.put("daerah", "");
		this.context.put("mukim", "");
		this.context.put("luashmp", "");
		this.context.put("luasasal", "");
		this.context.put("nocagaran", "");
		this.context.put("nopajakan", "");
		this.context.put("jenistanah", "");
		this.context.put("basimati", "");
		this.context.put("bbsimati", "");
		this.context.put("jenishta", "");
		this.context.put("tanggungan", "");
		this.context.put("jenisluas", "");
		this.context.put("catatan", "");
		this.context.put("noperserahan", "");
		this.context.put("idSimati", getParam("idSimati"));
		this.context.put("nopt", "");
		this.context.put("nilai_Hta_memohon", "");
		this.context.put("nilai_Hta_mati", "");
		this.context.put("kategori", "");
		this.context.put("jenishakmilik", "");
		this.context.put("pemilikan", "");
		this.context.put("luashmp", "");
		this.context.put("luasasal", "");
		this.context.put("nopajakan", "");
		this.context.put("jenistanah", "");
		this.context.put("basimati", "");
		this.context.put("bbsimati", "");
		this.context.put("tanggungan", "");
		this.context.put("jenisluas", "");
		this.context.put("catatan", "");
		this.context.put("noperserahan", "");
		this.context.put("show_simpan_add_htaam", "yes");
		this.context.put("show_batal_add_htaam", "yes");
		this.context.put("show_kemaskini_htaam", "");
		this.context.put("show_save_update_htaam", "");
		this.context.put("show_batal_update_htaam", "");
		this.context.put("show_hapus_htaam", "");
		this.context.put("show_kembali_htaam", "");
		this.context.put("show_button", "yes");
		this.context.put("show_htaa_update_table", "");
		this.context.put("show_htaa_add_table", "yes");
	}

	public void addHtaam(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("noHakmilik", getParam("txtNoHakmilikHtaam"));
		h.put("idSimati", getParam("idSimati"));
		h.put("nopt", getParam("txtNoPTHtaam"));
		if (getParam("txtNilaiTarikhMohonHtaa") != "") {
			h.put("nilai_Hta_memohon", Double
					.parseDouble(getParam("txtNilaiTarikhMohonHtaa")));
		} else {
			h.put("nilai_Hta_memohon", 0.0);
		}
		if (getParam("socJenisHakmilikHtaam") != "") {
			h.put("jenishakmilik", Integer
					.parseInt(getParam("socJenisHakmilikHtaam")));
		} else {
			h.put("jenishakmilik", 0);
		}
		if (getParam("socNegeriHtaam") != "") {
			h.put("negeri", Integer.parseInt(getParam("socNegeriHtaam")));
		} else {
			h.put("negeri", 0);
		}

		if (getParam("socDaerahHtaam") != "") {
			h.put("daerah", Integer.parseInt(getParam("socDaerahHtaam")));
		} else {
			h.put("daerah", 0);
		}

		if (getParam("socMukimHtaam") != "") {
			h.put("mukim", Integer.parseInt(getParam("socMukimHtaam")));
		} else {
			h.put("mukim", 0);
		}
		if (getParam("txtBahagianSimati1") != "") {
			h.put("basimati", getParam("txtBahagianSimati1"));
		} else {
			h.put("basimati", "0");
		}

		if (getParam("txtBahagianSimati2") != "") {
			h.put("bbsimati", getParam("txtBahagianSimati2"));
		} else {
			h.put("bbsimati", "0");
		}
		h.put("catatan", getParam("txtCatatanHtaam"));
		h.put("idpermohonansimati", getParam("idpermohonansimati"));

		// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
		h.put("sekatan", getParam("txtSekatan") == null ? ""
				: getParam("txtSekatan"));
		h.put("syaratNyata", getParam("txtSyaratNyata") == null ? ""
				: getParam("txtSyaratNyata"));

		logiconline.addHtaam(h);
	}

	private void updateHtaam(HttpSession session) throws Exception {
		Vector v = new Vector();
		Hashtable h = new Hashtable();
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("idhta", getParam("id_htaam"));
		h.put("noHakmilik", getParam("txtNoHakmilikHtaamUp"));
		h.put("idSimati", getParam("idSimati"));
		h.put("nopt", getParam("txtNoPTHtaamUp"));

		if (getParam("txtNilaiTarikhMohonHt") != "") {
			h.put("nilai_Hta_memohon", Double
					.parseDouble(getParam("txtNilaiTarikhMohonHt")));
		} else {
			h.put("nilai_Hta_memohon", 0.0);
		}

		if (getParam("socJenisHakmilikHtaamUp") != "") {
			h.put("jenishakmilik", Integer
					.parseInt(getParam("socJenisHakmilikHtaamUp")));
		} else {
			h.put("jenishakmilik", 0);
		}

		if (getParam("socNegeriHtaamUp") != "") {
			h.put("negeri", Integer.parseInt(getParam("socNegeriHtaamUp")));
		} else {
			h.put("negeri", 0);
		}

		if (getParam("socDaerahHtaamUp") != "") {
			h.put("daerah", Integer.parseInt(getParam("socDaerahHtaamUp")));
		} else {
			h.put("daerah", 0);
		}

		if (getParam("socMukimHtaamUp") != "") {
			h.put("mukim", Integer.parseInt(getParam("socMukimHtaamUp")));
		} else {
			h.put("mukim", 0);
		}
		if (getParam("txtBahagianSimati1Up") != "") {
			h.put("basimati", Integer
					.parseInt(getParam("txtBahagianSimati1Up")));
		} else {
			h.put("basimati", 0);
		}

		if (getParam("txtBahagianSimati2Up") != "") {
			h.put("bbsimati", Integer
					.parseInt(getParam("txtBahagianSimati2Up")));
		} else {
			h.put("bbsimati", 0);
		}
		h.put("catatan", getParam("txtCatatanHt"));
		
		// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
		h.put("sekatan", getParam("txtSekatan") == null ? ""
				: getParam("txtSekatan"));
		h.put("syaratNyata", getParam("txtSyaratNyata") == null ? ""
				: getParam("txtSyaratNyata"));
		
		logiconline.updateHtaam(h);
	}

	public void initInputPpkPengesahan() {
		this.context.put("namapejabat", "");
		this.context.put("alamat1", "");
		this.context.put("alamat2", "");
		this.context.put("alamat3", "");
		this.context.put("poskod", "");
		this.context.put("no_tel", "");
		this.context.put("no_tel_samb", "");
	}

	private void view_mode_ppkha(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		FrmPrmhnnSek8DaftarSek8Data.setDataHa(id1);
	}

	private void view_mode_ppkhta(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
		frmPrmhnnSek8SenaraiHTATHData.setDataHta(id1);
	}

	private void view_sum_ppkha(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		FrmPrmhnnSek8DaftarSek8Data.setDataHa(id1);
	}

	private void view_overallsummati_ppkha(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		FrmPrmhnnSek8DaftarSek8Data.setOverallSumMati(id1);
	}

	private void view_overallsum_ppkha(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		FrmPrmhnnSek8DaftarSek8Data.setOverallSum(id1);
	}

	private void view_mode_ppkha2(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
		frmPrmhnnSek8SenaraiHTATHData.setDataHa(id1);
	}

	private void view_mode_ppkhtath(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
		frmPrmhnnSek8SenaraiHTATHData.setDataHtath(id1);
	}

	private void addHa(HttpSession session) throws Exception {
		String id = getParam("id");
		String id1 = getParam("idSimati");
		String socJenisHartaAlih = getParam("socJenisHartaAlih");
		String txtBhgnSimati1 = getParam("txtBahagianSimatiHA1");
		String txtBhgnSimati2 = getParam("txtBahagianSimatiHA2");
		String txtNoRujukan = getParam("txtNoRujukan");
		String txtNilaiTarikhMati = getParam("txtNilaiTarikhMati");
		String txtNoSijil = getParam("txtNoSijil");
		String txtNilaiTarikhMohon = getParam("txtNilaiTarikhMohon");
		String txtBilUnit = getParam("txtBilUnit");
		String txtNilaiSeunit = getParam("txtNilaiSeunit");
		String txtAgensi = getParam("txtAgensi");
		String txtCatatan = getParam("txtCatatan");
		String bil = getParam("bil");

		Hashtable h = null;
		h = new Hashtable();
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("id", id);
		h.put("id1", id1);
		h.put("socJenisHartaAlih", socJenisHartaAlih);
		// h.put("basimati", txtBhgnSimati1);
		// h.put("bbsimati", txtBhgnSimati2);
		h.put("txtNoRujukan", txtNoRujukan);
		if (getParam("txtBahagianSimatiHA1") != "") {
			h.put("basimati", getParam("txtBahagianSimatiHA1"));
		} else {
			h.put("basimati", "0");
		}

		if (getParam("txtBahagianSimatiHA2") != "") {
			h.put("bbsimati", getParam("txtBahagianSimatiHA2"));
		} else {
			h.put("bbsimati", "0");
		}
		if (getParam("txtNilaiTarikhMati") != "") {
			h.put("txtNilaiTarikhMati", Double
					.parseDouble(getParam("txtNilaiTarikhMati")));
		} else {
			h.put("txtNilaiTarikhMati", 0.0);
		}

		if (getParam("txtNilaiTarikhMohon") != "") {
			h.put("txtNilaiTarikhMohon", Double
					.parseDouble(getParam("txtNilaiTarikhMohon")));
		} else {
			h.put("txtNilaiTarikhMohon", 0.0);
		}

		// h.put("txtNilaiTarikhMati", txtNilaiTarikhMati);
		h.put("txtNoSijil", txtNoSijil);
		// h.put("txtNilaiTarikhMohon", txtNilaiTarikhMohon);
		h.put("txtBilUnit", txtBilUnit);
		h.put("txtNilaiSeunit", txtNilaiSeunit);
		h.put("txtAgensi", txtAgensi);
		h.put("txtCatatan", txtCatatan);
		h.put("bil", bil);
		h.put("alamat1", getParam("txtAlamatHartaAlih1"));
		h.put("alamat2", getParam("txtAlamatHartaAlih2"));
		h.put("alamat3", getParam("txtAlamatHartaAlih3"));
		h.put("poskod", getParam("txtPoskodPemohon"));
		h.put("idnegeri", getParam("socNegeri"));
		h.put("iddaerah", getParam("socDaerah"));
		h.put("idpermohonansimati", getParam("idpermohonansimati"));

		FrmPrmhnnSek8DaftarSek8Data.addHa(h);
	}

	private void updateHa(HttpSession session) throws Exception {
		String id1 = getParam("idSimati");
		String id3 = getParam("idha");
		String socJenisHartaAlih = getParam("socJenisHartaAlih");
		String txtBhgnSimati1 = getParam("txtBahagianSimatiHA1");
		String txtBhgnSimati2 = getParam("txtBahagianSimatiHA2");
		String txtNoRujukan = getParam("txtNoRujukan");
		String txtNilaiTarikhMati = getParam("txtNilaiTarikhMati");
		String txtNoSijil = getParam("txtNoSijil");
		String txtNilaiTarikhMohon = getParam("txtNilaiTarikhMohon");
		String txtBilUnit = getParam("txtBilUnit");
		String txtNilaiSeunit = getParam("txtNilaiSeunit");
		String Agensi = getParam("txtAgensi");
		String txtCatatan = getParam("txtCatatan");
		String bil = getParam("bil");

		Hashtable h = null;
		h = new Hashtable();
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("id3", id3);
		h.put("id1", id1);
		h.put("socJenisHartaAlih", socJenisHartaAlih);
		// h.put("txtBhgnSimati1", txtBhgnSimati1);
		// h.put("txtBhgnSimati2", txtBhgnSimati2);
		h.put("txtNoRujukan", txtNoRujukan);
		h.put("txtNoSijil", txtNoSijil);
		if (getParam("txtBahagianSimatiHA1") != "") {
			h.put("basimati", getParam("txtBahagianSimatiHA1"));
		} else {
			h.put("basimati", "0");
		}

		if (getParam("txtBahagianSimatiHA2") != "") {
			h.put("bbsimati", getParam("txtBahagianSimatiHA2"));
		} else {
			h.put("bbsimati", "0");
		}
		if (getParam("txtNilaiTarikhMati") != "") {
			h.put("txtNilaiTarikhMati", Double
					.parseDouble(getParam("txtNilaiTarikhMati")));
		} else {
			h.put("txtNilaiTarikhMati", 0.0);
		}

		if (getParam("txtNilaiTarikhMohon") != "") {
			h.put("txtNilaiTarikhMohon", Double
					.parseDouble(getParam("txtNilaiTarikhMohon")));
		} else {
			h.put("txtNilaiTarikhMohon", 0.0);
		}

		h.put("txtBilUnit", txtBilUnit);
		h.put("txtNilaiSeunit", txtNilaiSeunit);
		h.put("Agensi", Agensi);
		h.put("txtCatatan", txtCatatan);
		h.put("bil", bil);
		h.put("alamat1", getParam("txtAlamatHartaAlih1"));
		h.put("alamat2", getParam("txtAlamatHartaAlih2"));
		h.put("alamat3", getParam("txtAlamatHartaAlih3"));
		h.put("poskod", getParam("txtPoskodPemohon"));
		h.put("idnegeri", getParam("socNegeri"));
		h.put("iddaerah", getParam("socDaerah"));
		logiconline.kemaskiniHa(h);
	}

	private void delete_mode_ppkha(HttpSession session, String id1, String id3)
			throws Exception {
		FrmPrmhnnSek8DaftarSek8Data.deleteDataHa(id1, id3);
	}

	private void insertpilihanha(HttpSession session) throws Exception {
		String id1 = getParam("idSimati");
		String id3 = getParam("idha");
		String socJenisHartaAlih = getParam("socJenisHartaAlih");
		String txtBhgnSimati1 = getParam("txtBhgnSimati1");
		String txtBhgnSimati2 = getParam("txtBhgnSimati2");
		String txtNoRujukan = getParam("txtNoRujukan");
		String txtNilaiTarikhMati = getParam("txtNilaiTarikhMati");
		String txtNoSijil = getParam("txtNoSijil");
		String txtNilaiTarikhMohon = getParam("txtNilaiTarikhMohon");
		String txtBilUnit = getParam("txtBilUnit");
		String txtNilaiSeunit = getParam("txtNilaiSeunit");
		String Agensi = getParam("txtAgensi");
		String txtCatatan = getParam("txtCatatan");
		String bil = getParam("bil");

		Hashtable h = null;
		h = new Hashtable();
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("id3", id3);
		h.put("id1", id1);
		h.put("socJenisHartaAlih", socJenisHartaAlih);
		h.put("txtBhgnSimati1", txtBhgnSimati1);
		h.put("txtBhgnSimati2", txtBhgnSimati2);
		h.put("txtNoRujukan", txtNoRujukan);
		h.put("txtNoSijil", txtNoSijil);
		h.put("txtNilaiTarikhMohon", txtNilaiTarikhMohon);
		h.put("txtBilUnit", txtBilUnit);
		h.put("txtNilaiSeunit", txtNilaiSeunit);
		h.put("Agensi", Agensi);
		h.put("txtCatatan", txtCatatan);
		h.put("bil", bil);
		h.put("alamat1", getParam("txtAlamatHartaAlih1"));
		h.put("alamat2", getParam("txtAlamatHartaAlih2"));
		h.put("alamat3", getParam("txtAlamatHartaAlih3"));
		h.put("poskod", getParam("txtPoskodPemohon"));
		h.put("idnegeri", getParam("socNegeri"));
		h.put("iddaerah", getParam("socDaerah"));
		logiconline.kemaskiniHa(h);
	}

	public int parseInt(String x) {
		if (x == null || "".equals(x)) {
			return -1;
		} else
			return Integer.parseInt(x);
	}

	// end of seksyen 17
}
