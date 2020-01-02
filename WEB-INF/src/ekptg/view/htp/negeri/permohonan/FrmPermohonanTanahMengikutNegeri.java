package ekptg.view.htp.negeri.permohonan;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.HTML;
import ekptg.helpers.InternalUserUtil;
import ekptg.helpers.Utils;
import ekptg.intergration.BorangKIntergrationBean;
import ekptg.intergration.IBorangKIntergration;
import ekptg.intergration.entity.BorangK;
import ekptg.intergration.entity.HTPBorangK;
import ekptg.model.entities.InternalUser;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.FrmPajakanBayaranPajakanCukaiTanahData;
import ekptg.model.htp.FrmSenaraiFailTerimaPohonData;
import ekptg.model.htp.FrmTerimaPohonData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HTPPermohonanBean;
import ekptg.model.htp.HTPStatusBean;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHTPBayaran;
import ekptg.model.htp.IHTPPermohonan;
import ekptg.model.htp.IHTPStatus;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.entity.Bayaran;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.KeputusanUlasan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.permohonan.HTPBayaranPermohonanBean;
import ekptg.model.htp.permohonan.IPermohonanPerizapan;
import ekptg.model.htp.permohonan.PermohonanPerizapanBean;
import ekptg.model.htp.rekod.FrmRekodUtilData;
import ekptg.model.htp.rekod.HakmilikBean;
import ekptg.model.htp.rekod.HakmilikInterface;
import ekptg.model.htp.utiliti.HTPSusulanBean;
import ekptg.model.htp.utiliti.IHTPSusulan;

/**
 * @author Mohamad Rosli Sub Modul Permohonan bagi kegunaan Pengguna Unit HTP
 *         JKPTG(N) Sabah/ Sarawak
 * 
 */

public class FrmPermohonanTanahMengikutNegeri extends AjaxBasedModule {
	private static final long serialVersionUID = 3744798063048260707L;
	private String DISABILITY = " disabled class=disabled ";
	private String DISABILITYNUM = " disabled class=\"inputnumberdisabled\" ";
	private String inputStyle = DISABILITY;
	private String inputStyleNum = DISABILITYNUM;
	private static Logger myLog = Logger
			.getLogger(ekptg.view.htp.negeri.permohonan.FrmPermohonanTanahMengikutNegeri.class);
	private final String PATH = "app/htp/permohonan/";
	private final String PATHVER = PATH + "v02/";
	private final String JENISTANAH = "1,2,3,6";
	private final String JENISTANAHPENGAMBILAN = "3";
	private final String IDSUBURUSANPERMOHONAN = "42";
	private BorangK borangK = null;
	private IHtp iHTP = null;
	private IHTPBayaran iBayaran = null;
	private ekptg.model.htp.IHtp iHtp = null;
	private ekptg.model.htp.IHTPPermohonan iPermohonan = null;
	private ekptg.model.htp.permohonan.IPermohonanPerizapan ipr = null;
	private FrmRekodUtilData frmRekodUtilData = null;
	private Hashtable TerimaPohonInfo = null;
	private HtpPermohonan htpPermohonan = null;
	private IBorangKIntergration kIntergration = null;
	String idJenisTanah = "3";
	private InternalUser iu = null;
	private IHTPStatus iStatus = null;
	private IHTPSusulan iSusulan = null;
	private PfdFail fail = null;
	private Permohonan permohonan = null;
	private String disability = DISABILITY;
	private String tarikhBukaFail = "";
	private String txtNoLot = "";
	private String noSyit = "";
	private String noPelan = "";
	private String socLot = "";
	private String socLuas = "";
	private String Luas = "";
	private String LuasLama = "";
	private String pageMode = "";
	private String userId = "";
	private Tblrujsuburusanstatusfail subUrusanStatusFail = null;

	FrmSenaraiFailTerimaPohonData fData = null;
	FrmTerimaPohonData model = new FrmTerimaPohonData();
	String socKementerian = "";
	String socAgensi = "";
	String socNegeri = "";
	String socDaerah = "";
	String socMukim = "";
	String socUrusan = "";
	String socSubUrusan = "";
	String socStatustanah = "";
	String socjenisFail = "";
	String readability = "";
	// String disability = "";
	String noLot = "";
	String txtTajuk = "";
	String txtTajukCarian = "";
	String id_kementerian = "";
	String id_agensi = "";
	String idnegeri = "";
	String iddaerah = "";
	String idmukim = "";
	String idurusan = "";
	String idsuburusan = "";
	String nofail = "";
	String idpermohonan = "";
	String idhakmilikurusan = "";
	String idfail = "";
	String flagNotifikasi = "";
	String idUser = "";
	String tabmode = "";
	String readOnly = "";
	String disabled = "";
	String style = "";
	String idNotis = "";
	String Lokasi = "";
	// ppt
	Vector<Hashtable<String, String>> listPPT = null;
	Vector<Hashtable<String, String>> listHakmilik = null;
	Vector<HTPBorangK> listBorangK = null;
	Vector<Hashtable<String, String>> MaklumatAsasTanahInfo = null;
	Vector<Hashtable<String, String>> senaraiTerimaPohon = null;
	// 18/08/2010
	String flagAdvSearch = "";
	String button_ = "";
	FrmPajakanBayaranPajakanCukaiTanahData pajakanData = new FrmPajakanBayaranPajakanCukaiTanahData();
	// END INIT

	FrmTerimaPohonData myInfo = new FrmTerimaPohonData();

	@Override
	public String doTemplate2() throws Exception {

		fData = FrmSenaraiFailTerimaPohonData.getInstance();
		// HttpSession session = request.getSession();
		HttpSession session = this.request.getSession();
		idUser = (String) session.getAttribute("_ekptg_user_id");
		String portal_role = (String) session.getAttribute("myrole");
		// String doPost = (String)session.getAttribute("doPost");
		context.put("portal_role_", portal_role);
		if (idUser == null || idUser.trim().length() == 0) {
			throw new Exception(getHTP().getErrorHTML(
					"[HTP PERMOHONAN] SILA LOGIN SEMULA"));
		}
		iu = InternalUserUtil.getSeksyenId(idUser);
		idnegeri = iu.getIdNegeri();

		// Parameters
		String action = getParam("action");
		String button = getParam("button");
		button_ = button;
		String doChange = getParam("doChange");
		String idNotis = getParam("idNotis");
		String mode = getParam("mode");
		String pagemode = getParam("pagemode");
		String selectedTab = getParam("tabId");
		String submit = getParam("command");
		tabmode = getParam("tabmode");
		idpermohonan = getParam("idpermohonan");
		idfail = getParam("idfail");
		idhakmilikurusan = getParam("idhakmilikurusan");
		// GET PARAM
		nofail = getParam("txtNoFail").trim();
		txtTajuk = getParam("txtTajuk");
		// 18/08/2010
		txtTajukCarian = getParam("txtTajukFail");
		id_kementerian = getParam("socKementerian");
		id_agensi = getParam("socAgensi");
		// idnegeri = getParam("socNegeri");
		iddaerah = getParam("socDaerah");
		idmukim = getParam("socMukim");
		idurusan = getParam("socUrusan");
		
		if ("".equals(idurusan)) {
			idurusan = "-1";
		}

		idsuburusan = getParam("socSubUrusan");
		
		if (selectedTab == null || "".equals(selectedTab)) {
			selectedTab = "0";
			tabmode = "0";
		}
		String selectedTab2 = "";
		String selectedTab3 = "";

		this.context.put("readOnly", "");
		this.context.put("disabled", "");
		// DEFAULT template
		String template_name = PATH + "frmTerimaPohonSenaraiFail2.jsp";
		//myLog.debug("SUBMIT=" + submit + ",MODE=" + mode + ",BUTTON =" + button);
		//myLog.debug("selectedTab= " + selectedTab + "tabmode= " + tabmode	+ ",PAGEMODE=" + pagemode);
		this.context.put("showSahkanButton", false);
		this.context.put("sahkanresult", "");
		// 18/08/2010
		flagAdvSearch = getParam("flagAdvSearch");
		tarikhBukaFail = getParam("txdtarikhdaftarfail");
		frmRekodUtilData = FrmRekodUtilData.getInstance();
		context.remove("senaraihakmilikrizab");
		this.context.put("pathTemp", PATHVER);
		userId = idUser;

		try {
			if (submit.equals("SahkanPermohonan")) {
				template_name = PATH + "FrmTerimaPohon.jsp";
				if ("doSahkan".equals(button)) {
					transferRecord(idpermohonan);
					this.context.put("sahkanresult",
							"Permohonan telah disahkan");

				} else {
					this.context.put("sahkanresult", "");
				}
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				Hashtable TerimaPohonInfo = FrmSenaraiFailTerimaPohonData
						.getTerimaPohonInfo(idfail);
				idpermohonan = (String) TerimaPohonInfo.get("lblIdPermohonan");
				setMaklumatPermohonan(TerimaPohonInfo);
				return template_name;

				// <link> No. Fail, index.jsp
			} else if (submit.equals("viewMaklumatPermohonan")) {
				template_name = PATHVER + "fail/frmMaklumatFail.jsp";
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				setPaging(false, true, true, false, false);
				Hashtable TerimaPohonInfo = FrmSenaraiFailTerimaPohonData
						.getTerimaPohonInfo(idfail);
				idpermohonan = (String) TerimaPohonInfo.get("lblIdPermohonan");
				setMaklumatPermohonan(TerimaPohonInfo);
				// dapatkan flag utk button sahkan
				if (fData.showSahkanButton(idpermohonan)) {
					this.context.put("showSahkanButton", true);
				}
				Vector senaraiHakmilikRizab = frmRekodUtilData
						.getHakmilikRizabMengikutPermohonan(idpermohonan);
				context.put("senaraihakmilikrizab", senaraiHakmilikRizab);

			} else if (submit.equals("maklumatpermohonan")) {
				template_name = PATHVER + "fail/frmMaklumatFailEdit.jsp";
				String hittButton = getParam("hittButton");
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				setPaging(false, true, true, false, false);
				Hashtable TerimaPohonInfo = FrmSenaraiFailTerimaPohonData
						.getTerimaPohonInfo(idfail);
				idpermohonan = (String) TerimaPohonInfo.get("lblIdPermohonan");
				setMaklumatPermohonan(TerimaPohonInfo);

				 //myLog.info("isSubUrusan="+String.valueOf(TerimaPohonInfo.get("lblidSubUrusan")));
				String isSubUrusan = "";
				if ((String.valueOf(TerimaPohonInfo.get("lblidSubUrusan"))
						.equals("1"))
						|| (String.valueOf(TerimaPohonInfo
								.get("lblidSubUrusan")).equals("2"))
						|| (String.valueOf(TerimaPohonInfo
								.get("lblidSubUrusan")).equals("3"))
						|| (String.valueOf(TerimaPohonInfo
								.get("lblidSubUrusan")).equals("4"))
						|| (String.valueOf(TerimaPohonInfo
								.get("lblidSubUrusan")).equals("5"))
						|| (String.valueOf(TerimaPohonInfo
								.get("lblidSubUrusan")).equals("6"))
						|| (String.valueOf(TerimaPohonInfo
								.get("lblidSubUrusan")).equals("42"))
								
						|| (String.valueOf(TerimaPohonInfo
								.get("lblidSubUrusan")).equals("1617126"))
						) {
					isSubUrusan = "ya";
				} else {
					isSubUrusan = "tidak";
				}
				this.context.put("isuburusan", isSubUrusan);
				
				// dapatkan flag utk button sahkan
				if (fData.showSahkanButton(idpermohonan)) {
					this.context.put("showSahkanButton", true);
				}
				if (hittButton.equals("kemaskini")) {
					// template_name = PATH+"fail/frmMaklumatFailEdit.jsp";
					disability = " class=\"\" ";
					pageMode = "kemaskini";
					style = "";
					// TerimaPohonInfo = fData.getTerimaPohonInfo(idfail);
					socDaerah = HTML.SelectSortDaerahByNegeri(idnegeri,
							"socdaerah2",
							Utils.parseLong(String.valueOf(TerimaPohonInfo
									.get("lblIdDaerah"))), disability, "");
					socKementerian = HTML.SelectKementerian("lblKementerian",
							Utils.parseLong((String) TerimaPohonInfo
									.get("lblidKementerian")), disability, "");
					socAgensi = HTML.SelectAgensi("lblidAgensi", Utils
							.parseLong((String) TerimaPohonInfo
									.get("lblidAgensi")), disability
							+ " style=\"width:400\"");
					socUrusan = UtilHTML.SelectUrusan("socUrusan",
							Utils.parseLong(idurusan), null);
					socSubUrusan = HTML
							.SelectSuburusanByIdUrusan(idurusan,
									"lblidSubUrusan", Utils
											.parseLong((String) TerimaPohonInfo
													.get("lblidSubUrusan")),
									disability);
					socStatustanah = HTML.SelectJenisTanah("lblidKodJTanah",
							Utils.parseLong((String) TerimaPohonInfo
									.get("lblidKodJTanah")), disability);
					socjenisFail = HTML.SelectTarafKeselamatan(
							"lblTanahKeselamatan", Utils
									.parseLong((String) TerimaPohonInfo
											.get("lblTanahKeselamatan")),
							disability);

				} else if (hittButton.equals("kemaskinisimpan")) {
					getValues(); // ()
					// TerimaPohonInfo = fData.getTerimaPohonInfo(idfail);
					idpermohonan = (String) TerimaPohonInfo.get("lblIdPermohonan");
					// htpPermohonan.setIdDaerah(String.valueOf(TerimaPohonInfo.get("lblIdDaerah")));
					// //lblIdDaerah
					// htpPermohonan.setIdAgensi(String.valueOf(TerimaPohonInfo.get("lblidAgensi")));
					// //lblidAgensi
					htpPermohonan.setIdJenisTanah(String.valueOf(TerimaPohonInfo.get("lblidKodJTanah"))); // lblidKodJTanah
					htpPermohonan.setIdHtpPermohonan(String.valueOf(TerimaPohonInfo.get("idHtpPermohonan"))); // idHtpPermohonan
					// fail.setIdTarafKeselamatan(String.valueOf(TerimaPohonInfo.get("lblTanahKeselamatan")));
					// //lblTanahKeselamatan
					fail.setTarikhDaftarFail(String.valueOf(TerimaPohonInfo
							.get("tarikhDaftarFail")));// tarikhDaftarFail
					permohonan.setPfdFail(fail);
					permohonan.setIdMasuk(Long.parseLong(idUser));
					// TBLPERMOHONAN
					// (TARIKH_SURAT,TARIKH_TERIMA,TUJUAN,ID_KEMASKINI,TARIKH_KEMASKINI-Auto)
					// TBLHTPPERMOHONAN
					// (NO_RUJUKAN_LAIN,NO_RUJUKAN_KJP,NO_RUJUKAN_UPT,NO_RUJUKAN_PTG,NO_RUJUKAN_PTD
					// ,ID_AGENSI,ID_DAERAH,ID_JENISTANAH,ID_KEMASKINI,TARIKH_KEMASKINI-Auto)
					// TBLPFDFAIL
					// (TAJUK_FAIL,TARIKH_DAFTAR_FAIL,ID_TARAFKESELAMATAN,ID_KEMASKINI,TARIKH_KEMASKINI-Auto)
					htpPermohonan = getHTP().kemaskiniPermohonan(htpPermohonan,idpermohonan, "");
					TerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);
					setMaklumatPermohonan(TerimaPohonInfo);

					template_name = PATHVER + "fail/frmMaklumatFail.jsp";
					
					context.put("disabled", "disabled");
					readOnly = "readonly";

				} else if (hittButton.equals("view")) {
					template_name = PATHVER + "fail/frmMaklumatFail.jsp";
					
					context.put("disabled", "disabled");
					readOnly = "readonly";

				}
				context.put("socDaerah", socDaerah);
				context.put("socKementerian", socKementerian);
				context.put("socAgensi", socAgensi);
				context.put("socjenisFail", socjenisFail);
				context.put("socUrusan", socUrusan);
				context.put("socSubUrusan", socSubUrusan);
				context.put("socStatustanah", socStatustanah);
				context.put("pageMode", pageMode);
				context.put("readOnly", readOnly);
				context.put("disability", disability);
				context.put("idNegeriNotis",String.valueOf(TerimaPohonInfo.get("lblNegeri")));

			} else if (submit.equals("terimapohoncarian")) {
				// CARIAN
				
				flagAdvSearch = "Y";
				senaraiTerimaPohon = getPR().TerimaPohongetList(null, nofail,
						txtTajukCarian, id_kementerian, id_agensi, idnegeri,
						iddaerah, idmukim, idurusan, tarikhBukaFail);

				this.context.put("tarikhBukaFail", tarikhBukaFail);
				doListing(session, action, mode, senaraiTerimaPohon);

			} else if (submit.equals("pohonfailbaru")) {
				template_name = PATHVER + "fail/frmMaklumatFailEdit.jsp";
				disability = " class=\"\" ";
				// this.context.put("readOnly", "");
				context.put("disabled", "");
				context.put("idpermohonan", "");
				context.put("idfail", "");
				viewPohonFailBaru(mode);
				context.put("readOnly", readOnly);
				context.put("disability", disability);
				context.put("idNegeriNotis", idnegeri);

			} else if (submit.equals("maklumatanah")) {
				template_name = PATHVER+ "maklumatanah/frmMaklumaTanahTABB.jsp";
				
				// MAKLUMAT ASAS TANAH
				// this.context.put("readOnly", "readonly");
				// this.context.put("disabled", "disabled");
				setPaging(false, false, true, false, false);
				// HEADER
				String pageMode = "";
				TerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);
				this.context.put("pageMode", pageMode);
				idpermohonan = (String) TerimaPohonInfo.get("lblIdPermohonan");
				// iddaerah =
				// String.valueOf(TerimaPohonInfo.get("lblIdDaerah"));
				setMaklumatPermohonan(TerimaPohonInfo);
				// Semakan ID_SUBURUSAN
				String isSubUrusan = "";
				if ((String.valueOf(TerimaPohonInfo.get("lblidSubUrusan"))
						.equals("1"))
						|| (String.valueOf(TerimaPohonInfo
								.get("lblidSubUrusan")).equals("2"))
						|| (String.valueOf(TerimaPohonInfo
								.get("lblidSubUrusan")).equals("3"))
						|| (String.valueOf(TerimaPohonInfo
								.get("lblidSubUrusan")).equals("4"))
						|| (String.valueOf(TerimaPohonInfo
								.get("lblidSubUrusan")).equals("5"))
						|| (String.valueOf(TerimaPohonInfo
								.get("lblidSubUrusan")).equals("6"))
						|| (String.valueOf(TerimaPohonInfo
								.get("lblidSubUrusan")).equals("42"))
						|| (String.valueOf(TerimaPohonInfo
								.get("lblidSubUrusan")).equals("1617126"))		
						) {
					isSubUrusan = "ya";
				} else {
					isSubUrusan = "tidak";
				}
				this.context.put("isuburusan", isSubUrusan);

				String id_jenistanah = TerimaPohonInfo.get("lblidKodJTanah").toString();
				context.put("id_jenistanah", id_jenistanah);
				context.put("idNegeriNotis",String.valueOf(TerimaPohonInfo.get("lblNegeri")));
				if (mode.equals("MakAsasTanah")) {
					// context.put("id_jenistanah", id_jenistanah);
					String hittButton = getParam("hittButton");
					// IF DO CHANGES DETECTED
					if (doChange.indexOf("doChange") != -1) {
						// log.debug("changes...");
						//myLog.info("doChange:TAB MODE=" + tabmode);
						idnegeri = getParam("socnegeri2");
						iddaerah = getParam("socdaerah2");
						idmukim = getParam("socMukim2");

					}
					socNegeri = HTML.SelectNegeri("socnegeri2",
							Utils.parseLong(idnegeri), DISABILITY);
					socDaerah = HTML.SelectSortDaerahByNegeri(idnegeri,
							"socdaerah2", Utils.parseLong(iddaerah), style,
							"onChange=\"doChanges2('" + tabmode + "')\"");
					socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim2",
							Utils.parseLong(idmukim), style);
					socLot = HTML.SelectLot("socLot", Utils.parseLong(socLot),
							style);

					Vector MaklumatAsasTanahInfo = fData.getMaklumatAsasTanahInfo(idpermohonan);

					this.context.put("bilBorangK", MaklumatAsasTanahInfo.size());
					this.context.put("MAT", MaklumatAsasTanahInfo);

					if (button.equals("KemaskiniMaklumatInfo")) {
						pageMode = "view";
						readOnly = "disabled";
						disabled = "disabled";
						style = readOnly + " class=" + disabled + " ";
						myLog.info("KemaskiniMaklumatInfo");
						Hashtable t = getPR().getMaklumatAsasTanahKemaskini(
								idhakmilikurusan);
						idhakmilikurusan = (String) t.get("idhakmilikurusan");
						idnegeri = (String) t.get("idnegeri");
						iddaerah = (String) t.get("iddaerah");
						idmukim = (String) t.get("idmukim");
						txtNoLot = (String) t.get("nolot");
						noSyit = (String) t.get("nosyit");
						noPelan = (String) t.get("nopelan");
						socLot = (String) t.get("idlot");
						socLuas = (String) t.get("idluas");
						Luas = (String) t.get("luasH");
						LuasLama = (String) t.get("luas");
						// myLog.info("LuasLama:"+LuasLama+",Luas:"+Luas+"");
						String luas = "0";
						String luas1 = "0";
						String luas2 = "0";
						if (socLuas.equals("1") || socLuas.equals("2")
								|| socLuas.equals("3")
								|| getParam("socLuas").equals("5")
								|| getParam("socLuas").equals("6")) {
							if (socLuas.equals("1")) {
								if (LuasLama.contains("KM"))
									luas = LuasLama.substring(0,
											(LuasLama.length() - 2));
								else
									luas = LuasLama;

							} else if (socLuas.equals("2")) {
								if (LuasLama.contains("H"))
									luas = LuasLama.substring(0,
											(LuasLama.length() - 1));
								else
									luas = LuasLama;

							} else if (socLuas.equals("3")) {
								if (LuasLama.contains("MP"))
									luas = LuasLama.substring(0,
											(LuasLama.length() - 2));
								else {
									if (LuasLama.contains("M"))
										luas = LuasLama.substring(0,
												(LuasLama.length() - 1));
									else
										luas = LuasLama;
								}

							} else if (socLuas.equals("5")) {
								if (LuasLama.contains("KP"))
									luas = LuasLama.substring(0,
											(LuasLama.length() - 2));
								else {
									if (LuasLama.contains("K"))
										luas = LuasLama.substring(0,
												(LuasLama.length() - 1));
									else
										luas = LuasLama;
								}

							} else if (socLuas.equals("6")) {
								if (LuasLama.contains("P"))
									luas = LuasLama.substring(0,
											(LuasLama.length() - 1));
								else
									luas = LuasLama;

							}

						} else if (socLuas.equals("4")) {
							if (LuasLama.contains("E,")
									&& LuasLama.contains("R,")) {
								luas = LuasLama.substring(0,
										LuasLama.indexOf("E,"));
								luas1 = LuasLama.substring(
										LuasLama.indexOf("E,") + 2,
										LuasLama.indexOf("R,"));
								luas2 = LuasLama.substring(
										LuasLama.indexOf("R,") + 2,
										(LuasLama.length() - 1));

							}
						} else if (socLuas.equals("7")) {
							// log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
							if (LuasLama.contains("E,")
									&& LuasLama.contains("D")) {
								luas = LuasLama.substring(0,
										LuasLama.indexOf("E,"));
								luas1 = LuasLama.substring(
										LuasLama.indexOf("E,") + 2,
										LuasLama.indexOf("D"));
							}

						} else if (socLuas.equals("8")) {
							// log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
							if (LuasLama.contains("R,")
									&& LuasLama.contains("J,")) {
								luas = LuasLama.substring(0,
										LuasLama.indexOf("R,"));
								luas1 = LuasLama.substring(
										LuasLama.indexOf("R,") + 2,
										LuasLama.indexOf("J"));
								luas2 = LuasLama.substring(
										LuasLama.indexOf("J,") + 2,
										(LuasLama.length() - 1));
							}

						} else { // 7||9 (TIADA SAMPLE DATA)
							luas = LuasLama;

						}
						this.context.put("txtLuasLama1", luas1.trim());
						this.context.put("txtLuasLama2", luas2.trim());
						this.context.put("txtLuasLama", luas);

						Lokasi = (String) t.get("lokasi");

						context.put("noSyit", noSyit);
						context.put("noPelan", noPelan);
						context.put("txtNoLot", txtNoLot);
						context.put("Lokasi", Lokasi);
						// context.put("txtLuas1", LuasLama);
						// context.put("Luas", Luas);

						this.context.put("readonly", "readonly=\"readonly\"");
						pageMode = "view";

						// Tambah Maklumat Tanah
					} else {
						disability = " class=\"\" ";

					}
					context.put("socNegeri2", socNegeri);
					context.put("socDaerah2", socDaerah);
					context.put("socMukim2", socMukim);
					context.put("socLot", socLot);
					context.put("socLuas", socLuas);
					// context.put("noSyit", noSyit);
					// context.put("noPelan", noPelan);
					// context.put("txtNoLot", txtNoLot);
					// context.put("Lokasi", Lokasi);
					// context.put("txtLuas1", LuasLama);
					// context.put("Luas", Luas);
					context.put("pageMode", pageMode);
					context.put("readOnly", readOnly);
					context.put("disability", disability);

				} else if (mode.equals("maklumatanahterperinci")) {
					//myLog.info("DetailLot");
					selectedTab2 = getParam("tabId2");
					this.context.put("selectedTab2", selectedTab2);
					if (selectedTab2.equals("1")) {
						initMaklumatCharting("");

					} else {
						initMaklumatLokasi("");

					}

				} else {
					Vector MaklumatAsasTanahInfo = fData.getMaklumatAsasTanahInfo(idpermohonan);
					// untuk ppt
					// String id_jenistanah =
					// TerimaPohonInfo.get("lblidKodJTanah").toString();
					// context.put("id_jenistanah", id_jenistanah);
					if (MaklumatAsasTanahInfo.size() > 0) {
						// tabmode = "3_1";
					}
					this.context.put("bilBorangK", MaklumatAsasTanahInfo.size());
					this.context.put("MAT", MaklumatAsasTanahInfo);

				}

			} else if (submit.equals("kemaskinipermohonan")) {
				
				// template_name = PATH+"maklumatanah/frmMaklumaTanahTABB.jsp";
				template_name = PATHVER	+ "maklumatanah/frmMaklumaTanahTABB.jsp";
				//myLog.info("kemaskinipermohonan=" + submit);
				// template_name = PATH+"frmTerimaPohonMaklumatTABB.jsp";
				// 14/09/2012
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				setPaging(false, false, true, false, false);
				// HEADER
				Hashtable TerimaPohonInfo = null;
				String hittButton = getParam("hittButton");
				// String
				pageMode = "";

				// SIMPAN
				if (hittButton.equals("Simpan")) {
					
					// template_name = PATH+"FrmTerimaPohon.jsp";
					setPaging(false, true, false, false, false);
					template_name = PATHVER + "fail/frmMaklumatFail.jsp";
					String idFailBaru = doSimpanMaklumatPermohonan();
					AuditTrail.logActivity("1", iu.getIdSeksyen(), this,session, "INS", "FAIL PERMOHONAN ["+ fData.strNoFail + "] DITAMBAH ");
					TerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idFailBaru);
					idfail = idFailBaru;
					String id_ppengarah = getParam("socPegawaiPP");
					String id_pengarahP = nameAndIdPengarah(id_ppengarah,idnegeri);
					this.context.put("id_ppengarah", id_ppengarah);
					nameAndIdPengarah(id_ppengarah, idnegeri);
					// pageMode = "kemaskini";
					
					hantarEmel(idpermohonan, "content","Tindakan Pengarah untuk Semakan", id_pengarahP);

					readOnly = "readonly";
					this.context.put("readOnly", readOnly);
					this.context.put("disabled", disabled);

				} else if (hittButton.equals("kemaskinisimpan")) {
					//myLog.info("**************** kemaskinisimpan line 597 ");
					template_name = PATHVER + "fail/frmMaklumatFail.jsp";
					getValues(); // ()
					TerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);
					idpermohonan = (String) TerimaPohonInfo.get("lblIdPermohonan");
					htpPermohonan.setIdDaerah(String.valueOf(TerimaPohonInfo.get("lblIdDaerah"))); // lblIdDaerah
					htpPermohonan.setIdAgensi(String.valueOf(TerimaPohonInfo.get("lblidAgensi"))); // lblidAgensi
					htpPermohonan.setIdJenisTanah(String.valueOf(TerimaPohonInfo.get("lblidKodJTanah"))); // lblidKodJTanah
					htpPermohonan.setIdHtpPermohonan(String.valueOf(TerimaPohonInfo.get("idHtpPermohonan"))); // idHtpPermohonan
					fail.setIdTarafKeselamatan(String.valueOf(TerimaPohonInfo.get("lblTanahKeselamatan"))); // lblTanahKeselamatan
					fail.setTarikhDaftarFail(String.valueOf(TerimaPohonInfo.get("tarikhDaftarFail")));// tarikhDaftarFail
					permohonan.setPfdFail(fail);
					permohonan.setIdMasuk(Long.parseLong(idUser));
					
					// TBLPERMOHONAN
					// (TARIKH_SURAT,TARIKH_TERIMA,TUJUAN,ID_KEMASKINI,TARIKH_KEMASKINI-Auto)
					// TBLHTPPERMOHONAN
					// (NO_RUJUKAN_LAIN,NO_RUJUKAN_KJP,NO_RUJUKAN_UPT,NO_RUJUKAN_PTG,NO_RUJUKAN_PTD
					// ,ID_AGENSI,ID_DAERAH,ID_JENISTANAH,ID_KEMASKINI,TARIKH_KEMASKINI-Auto)
					// TBLPFDFAIL
					// (TAJUK_FAIL,TARIKH_DAFTAR_FAIL,ID_TARAFKESELAMATAN,ID_KEMASKINI,TARIKH_KEMASKINI-Auto)
					
					htpPermohonan = getHTP().kemaskiniPermohonan(htpPermohonan,idpermohonan, "");
					TerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);

					this.context.put("readOnly", readOnly);
					this.context.put("disabled", disabled);
					

				} else if (hittButton.equals("kemaskini")) {
					template_name = PATH + "fail/frmMaklumatFailEdit.jsp";
					TerimaPohonInfo = FrmSenaraiFailTerimaPohonData	.getTerimaPohonInfo(idfail);
					readOnly = "";
					disabled = "";
					style = "";
					pageMode = "kemaskini";
					this.context.put("readOnly", readOnly);
					this.context.put("disabled", disabled);

				} else {
					TerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);

				}
				this.context.put("pageMode", pageMode);
				idpermohonan = (String) TerimaPohonInfo.get("lblIdPermohonan");
				iddaerah = String.valueOf(TerimaPohonInfo.get("lblIdDaerah"));
				setMaklumatPermohonan(TerimaPohonInfo);
				// ASSIGN VALUES
				// DISABLE OPTION UTK PILIH NEGERI
				socNegeri = HTML.SelectNegeri("socnegeri2",Utils.parseLong(idnegeri), DISABILITY);
				socDaerah = HTML.SelectSortDaerahByNegeri(idnegeri, "socdaerah2",
						Utils.parseLong(iddaerah), style,
						"onChange=\"doChanges2('" + tabmode + "')\"");
				socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim2",Utils.parseLong(idmukim), style);
				context.put("socNegeri2", socNegeri);
				context.put("socDaerah2", socDaerah);
				context.put("socMukim2", socMukim);
				String id_jenistanah = TerimaPohonInfo.get("lblidKodJTanah").toString();
				
				if (mode.equals("MakAsasTanah")) {
					// MAKLUMAT ASAS TANAH
					String txtNoLot = getParam("txtNoLot");
					String noSyit = getParam("noSyit");
					String noPelan = getParam("noPelan");
					String socLot = getParam("socLot");
					String socLuas = getParam("socLuas");
					// String LuasLama = getParam("txtLuas1");
					String LuasLama = getParam("txtLuasGabung");
					String Luas = getParam("Luas");
					String Lokasi = getParam("Lokasi");
					idmukim = getParam("socMukim2");
					noLot = getParam("socLot");
					
					// IF DO CHANGES DETECTED
					if (doChange.indexOf("doChange") != -1) {
						idnegeri = getParam("socnegeri2");
						iddaerah = getParam("socdaerah2");
						idmukim = getParam("socMukim2");
						// LuasLama = "";
					}
					if (button.equals("SimpanMaklumatAsasTanah")) {
						myLog.debug("SimpanMaklumatAsasTanah");
						readOnly = "readonly";
						disabled = "disabled";
						style = readOnly + " class=" + disabled + " ";
						// doSimpanMaklumatAsasTanah();
						idhakmilikurusan = simpanMaklumatAsasTanah();
						getMaklumatTanah();
						idnegeri = getParam("socnegeri2");
						iddaerah = getParam("socdaerah2");
						idmukim = getParam("socMukim2");
						context.remove("TerimaPohonInfo");
						TerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);
						setMaklumatPermohonan(TerimaPohonInfo);

					} else if (button.equals("KemaskiniMaklumatInfo")) {
						
						readOnly = "readonly";
						disabled = "disabled";
						style = readOnly + " class=" + disabled + " ";
						// Vector MaklumatAsasTanahInfo =
						// fData.getMaklumatAsasTanahKemaskini(idhakmilikurusan);
						// Hashtable t =
						// (Hashtable)MaklumatAsasTanahInfo.get(0);
						Hashtable t = getPR().getMaklumatAsasTanahKemaskini(idhakmilikurusan);
						idhakmilikurusan = (String) t.get("idhakmilikurusan");
						idnegeri = (String) t.get("idnegeri");
						iddaerah = (String) t.get("iddaerah");
						idmukim = (String) t.get("idmukim");
						txtNoLot = (String) t.get("nolot");
						noSyit = (String) t.get("nosyit");
						noPelan = (String) t.get("nopelan");
						socLot = (String) t.get("idlot");
						socLuas = (String) t.get("idluas");
						Luas = (String) t.get("luasH");
						LuasLama = (String) t.get("luas");
						// myLog.info("LuasLama:"+LuasLama+",Luas:"+Luas+"");

						String luas = "0";
						String luas1 = "0";
						String luas2 = "0";
						if (socLuas.equals("1") || socLuas.equals("2")
								|| socLuas.equals("3")
								|| getParam("socLuas").equals("5")
								|| getParam("socLuas").equals("6")) {
							if (socLuas.equals("1")) {
								if (LuasLama.contains("KM"))
									luas = LuasLama.substring(0,
											(LuasLama.length() - 2));
								else
									luas = LuasLama;

							} else if (socLuas.equals("2")) {
								if (LuasLama.contains("H"))
									luas = LuasLama.substring(0,
											(LuasLama.length() - 1));
								else
									luas = LuasLama;

							} else if (socLuas.equals("3")) {
								if (LuasLama.contains("MP"))
									luas = LuasLama.substring(0,
											(LuasLama.length() - 2));
								else {
									if (LuasLama.contains("M"))
										luas = LuasLama.substring(0,
												(LuasLama.length() - 1));
									else
										luas = LuasLama;
								}

							} else if (socLuas.equals("5")) {
								if (LuasLama.contains("KP"))
									luas = LuasLama.substring(0,
											(LuasLama.length() - 2));
								else {
									if (LuasLama.contains("K"))
										luas = LuasLama.substring(0,
												(LuasLama.length() - 1));
									else
										luas = LuasLama;
								}

							} else if (socLuas.equals("6")) {
								if (LuasLama.contains("P"))
									luas = LuasLama.substring(0,
											(LuasLama.length() - 1));
								else
									luas = LuasLama;

							}

						} else if (socLuas.equals("4")) {
							if (LuasLama.contains("E,")
									&& LuasLama.contains("R,")) {
								luas = LuasLama.substring(0,
										LuasLama.indexOf("E,"));
								luas1 = LuasLama.substring(
										LuasLama.indexOf("E,") + 2,
										LuasLama.indexOf("R,"));
								luas2 = LuasLama.substring(
										LuasLama.indexOf("R,") + 2,
										(LuasLama.length() - 1));

							}
						} else if (socLuas.equals("7")) {
							// log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
							if (LuasLama.contains("E,")
									&& LuasLama.contains("D")) {
								luas = LuasLama.substring(0,
										LuasLama.indexOf("E,"));
								luas1 = LuasLama.substring(
										LuasLama.indexOf("E,") + 2,
										LuasLama.indexOf("D"));
							}

						} else if (socLuas.equals("8")) {
							// log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
							if (LuasLama.contains("R,")
									&& LuasLama.contains("J,")) {
								luas = LuasLama.substring(0,
										LuasLama.indexOf("R,"));
								luas1 = LuasLama.substring(
										LuasLama.indexOf("R,") + 2,
										LuasLama.indexOf("J"));
								luas2 = LuasLama.substring(
										LuasLama.indexOf("J,") + 2,
										(LuasLama.length() - 1));
							}

						} else { // 7||9 (TIADA SAMPLE DATA)
							luas = LuasLama;

						}
						this.context.put("txtLuasLama1", luas1.trim());
						this.context.put("txtLuasLama2", luas2.trim());
						this.context.put("txtLuasLama", luas);

						Lokasi = (String) t.get("lokasi");

					} else if (button.equals("doViewForKemaskini")) {
						readOnly = "";
						disabled = "";
						style = "";
						// idhakmilikurusan = kemaskiniMaklumatAsasTanah();
						getMaklumatTanah();

					} else if (button.equals("doKemaskiniMaklumatAsasTanah")) {
						
						readOnly = "readonly";
						disabled = "disabled";
						style = readOnly + " class=" + disabled + " ";
						// doKemaskiniMaklumatAsasTanah()
						idhakmilikurusan = kemaskiniMaklumatAsasTanah();
						getMaklumatTanah();
						iddaerah = getParam("socdaerah2");
						idmukim = getParam("socMukim2");
						context.remove("TerimaPohonInfo");
						TerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);
						setMaklumatPermohonan(TerimaPohonInfo);

					} else if (button.equals("deletemaklumatanah")) {
						this.context.remove("MAT");
						
						getHTP().hapusHakmilik(idhakmilikurusan);
						MaklumatAsasTanahInfo = fData.getMaklumatAsasTanahInfo(idpermohonan);
						this.context.put("MAT", MaklumatAsasTanahInfo);

					} else if (button.equals("DetailLot")) {
						// SET SELECTED
						
						selectedTab2 = getParam("tabId2");
						this.context.put("selectedTab2", selectedTab2);
						if ("1".equals(selectedTab2)) {
							initMaklumatCharting("");

						} else {
							initMaklumatLokasi("");

						}

					} else if (button.equals("SimpanDetailLot")) {
						
						SimpanLokasiTanah();
						initMaklumatLokasi("readonly");

					} else if (button.equals("doKemaskiniDetailLot")) {
						
						doKemaskiniLokasiTanah();
						initMaklumatLokasi("readonly");

					} else if (button.equals("doViewForKemaskiniDetailLot")) {
						readOnly = "";
						disabled = "";
						style = "";

					} else if (button.equals("SimpanCharting")) {
						
						selectedTab2 = "1";
						this.context.put("selectedTab2", selectedTab2);
						SimpanCharting();
						initMaklumatCharting("readonly");

					} else if (button.equals("doKemaskiniCharting")) {
						
						selectedTab2 = "1";
						this.context.put("selectedTab2", selectedTab2);
						KemaskiniCharting();
						initMaklumatCharting("readonly");

					} else if (button.equals("doViewForKemaskiniCharting")) {
						readOnly = "";
						disabled = "";
						style = "";

					} else {
						// SENARAI
						
						// komen pada 25/06/2010 oleh mrosli
						Vector MaklumatAsasTanahInfo = fData.getMaklumatAsasTanahInfo(idpermohonan);
						// untuk ppt
						// String id_jenistanah =
						// TerimaPohonInfo.get("lblidKodJTanah").toString();
						context.put("id_jenistanah", id_jenistanah);
						if (MaklumatAsasTanahInfo.size() > 0) {
							// tabmode = "3_1";
						}
						this.context.put("bilBorangK",MaklumatAsasTanahInfo.size());
						this.context.put("MAT", MaklumatAsasTanahInfo);
						this.context.put("TerimaPohonInfo", TerimaPohonInfo);
						LuasLama = getParam("txtLuasGabung") == "" ? ""	: getParam("txtLuas1");

					}

					// ASSIGN VALUES
					// DISABLE OPTION UTK PILIH NEGERI
					socNegeri = HTML.SelectNegeri("socnegeri2",
							Utils.parseLong(idnegeri), DISABILITY);
					socDaerah = HTML.SelectSortDaerahByNegeri(idnegeri,
							"socdaerah2", Utils.parseLong(iddaerah), style,
							"onChange=\"doChanges2('" + tabmode + "')\"");
					socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim2",
							Utils.parseLong(idmukim), style);
					socLot = HTML.SelectLot("socLot", Utils.parseLong(socLot),
							style);

					context.put("socNegeri2", socNegeri);
					context.put("socDaerah2", socDaerah);
					context.put("socMukim2", socMukim);
					context.put("socLot", socLot);
					context.put("socLuas", socLuas);
					context.put("noSyit", noSyit);
					context.put("noPelan", noPelan);
					context.put("txtNoLot", txtNoLot);
					context.put("Lokasi", Lokasi);
					context.put("txtLuas1", LuasLama);
					context.put("Luas", Luas);

				} else if (mode.equals("BorangK")) {
					// list infoborangk
					// fData.setListBorangK();
					// listBorangK = fData.getListBorangK();
					listBorangK = getKIntergration().getHTPBorangKList(idpermohonan);
					context.put("listBorangK", listBorangK);
					context.put("saiz_borangk", listBorangK.size());

				} else if (mode.equals("Pembayaran")) {

					selectedTab = "1"; // TAB KEPUTUSAN/PEMBAYARAN
					if (id_jenistanah.equals("3")) {
						selectedTab = "2"; // TAB KEPUTUSAN/PEMBAYARAN (TANAH
											// PENGAMBILAN)
					}
					context.put("id_jenistanah", id_jenistanah);
					this.context.put("visible", "1");
					selectedTab3 = getParam("tabId3");
					

					if (selectedTab3.equals("1")) {
						// if ("1".equals(tabmode)) { //TAB KEPUTUSAN
						
						if (button.equals("hantarptgptdsimpan")) {
							// myLog.debug("hantarptgptdsimpan");
							Tblrujsuburusanstatusfail rsusf = new Tblrujsuburusanstatusfail();
							rsusf.setIdPermohonan(Long.parseLong(idpermohonan));
							rsusf.setIdFail(Long.parseLong(idfail));
							idsuburusan = String.valueOf(TerimaPohonInfo.get("lblidSubUrusan"));
							rsusf.setIdSuburusanstatusfail(Long.parseLong(idsuburusan));
							rsusf.setUrl(getParam("txtcatatanPTGPTD"));
							// myLog.info("txttarikhsuratPTGPTD 1 ="+getParam("txttarikhsuratPTGPTD"));
							// rsusf.setTarikhMasuk(new
							// Date(getParam("txttarikhsuratPTGPTD").equals("")?"01/01/1900":getParam("txttarikhsuratPTGPTD")));
							rsusf.setTarikhMasuk(getParam("txttarikhsuratPTGPTD").equals("") ? "01/01/1900": getParam("txttarikhsuratPTGPTD"));
							kemaskiniSimpanStatusSelesai(rsusf, "10");
							statusView(idpermohonan);
							this.context.put("selectedTab", 3);

						} else if (button.equals("kemaskiniptgptd")) {
							String idSuburusanStatusFail = getParam("txtid");
							long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("10", String.valueOf(TerimaPohonInfo.get("lblidSubUrusan")),"=");
							Tblrujsuburusanstatusfail rsusf = new Tblrujsuburusanstatusfail();
							rsusf.setIdPermohonan(Long.parseLong(idpermohonan));
							rsusf.setIdFail(Long.parseLong(idfail));
							rsusf.setIdSuburusanstatus(setIdSuburusanstatus);
							rsusf.setUrl(getParam("txtcatatanPTGPTD"));
							rsusf.setTarikhKemaskini(new Date(getParam("txttarikhsuratPTGPTD").equals("") ? "01/01/1900": getParam("txttarikhsuratPTGPTD")));
							// myLog.info(setIdSuburusanstatus);
							rsusf.setIdKemaskini(Long.parseLong(idUser));
							getStatus().kemaskiniStatus(rsusf,getParam("txttarikhsuratPTGPTD").equals("") ? "01/01/1900": getParam("txttarikhsuratPTGPTD"));
							statusView(idpermohonan);

						} else if (button.equals("paparanptgptd")) {
							readOnly = "";
							disabled = "";
							style = "";
							statusView(idpermohonan);

						} else if (button.equals("hapusptgptd")) {
							readOnly = "";
							disabled = "";
							style = "";
							statusView(idpermohonan);

						} else {
							statusView(idpermohonan);

						}

						/** TAB KEPUTUSAN **/
					} else if (selectedTab3.equals("2")) {

						Hashtable keputusan = null;
						Vector vecKeputusan = null;
						readOnly = "";
						disabled = "";
						style = "";
						idsuburusan = String.valueOf(TerimaPohonInfo.get("lblidSubUrusan"));

						if (button.equals("TambahKeputusan")) {
							keputusanPermohonan("insert");
							vecKeputusan = fData.getKeputusanInfo(idpermohonan);
							keputusan = (Hashtable) vecKeputusan.get(0);
							pageMode = "view";
							this.context.put("keputusan", keputusan);

						} else if (button.equals("KemaskiniKeputusan")) {
						
							keputusanPermohonan("update");
							initKeputusanPermohonan("readonly");
							this.context.put("visible", "0");
							pageMode = "view";

						} else if (button.equals("doViewForKemaskiniKeputusan")) {

							pageMode = "viewkemaskini";
							vecKeputusan = fData.getKeputusanInfo(idpermohonan);
							keputusan = (Hashtable) vecKeputusan.get(0);
							this.context.put("keputusan", keputusan);

						} else if (button.equals("hapuskeputusan")) {
							getPR().hapuskeputusan(idpermohonan);
							vecKeputusan = fData.getKeputusanInfo(idpermohonan);
							pageMode = "baru";
							tabmode = "3";// Insert
							if (vecKeputusan.size() > 0) {
								keputusan = (Hashtable) vecKeputusan.get(0);
								tabmode = "4";// Update
								readOnly = "readonly";
								disabled = "disabled";
								style = readOnly + " class=" + disabled + " ";
								pageMode = "view";
								this.context.put("idjeniskeputusan", Integer.parseInt(String.valueOf(keputusan.get("status"))));

							}

							this.context.put("keputusan", keputusan);

						} else {
						
							vecKeputusan = fData.getKeputusanInfo(idpermohonan);
							pageMode = "baru";
							tabmode = "3";// Insert
							if (vecKeputusan.size() > 0) {
								keputusan = (Hashtable) vecKeputusan.get(0);
								tabmode = "4";// Update
								readOnly = "readonly";
								disabled = "disabled";
								style = readOnly + " class=" + disabled + " ";
								pageMode = "view";
								
								this.context.put("idjeniskeputusan", Integer
										.parseInt(String.valueOf(keputusan
												.get("status"))));

							}
							this.context.put("keputusan", keputusan);
							// initKeputusanPermohonan("");
						}

						context.put("pagemode", pageMode);

					} else if (selectedTab3.equals("3")) {
						Hashtable hPergerakan = null;
						Vector<?> senaraiTindakan = null;
						String idPermohonan = idpermohonan;
						pagemode = getParam("pagemode");
						idsuburusan = String.valueOf(TerimaPohonInfo.get("lblidSubUrusan"));
						String idSubUrusan = idsuburusan;
						String idFail = idfail;

						if (button.equals("tambah")) {
							
							this.context.put("readonly", "");
							this.context.put("disabled", "");
							mode = "new";

						} else if (button.equals("simpan")) {
							Hashtable hInsert = null;
							if (portal_role.contains("PenggunaNegeri")) {
								hInsert = new Hashtable();
								String idSusulan = getISusulan().simpan(setSusulanValues(idPermohonan));
								String idStatusFail = kemaskiniSimpanStatusSelesai(idFail, idPermohonan, idSubUrusan, "81");
								hInsert.put("idStatusFail", idStatusFail);
								hInsert.put("idSusulan", idSusulan);
								hInsert.put("idMasuk", userId);
								getISusulan().simpanSusulanStatusFail(hInsert);

							} else if (portal_role.contains("PegawaiNegeri")) {
								hInsert = new Hashtable();
								String idSusulan = getISusulan().simpan(setSusulanValues(idPermohonan));
								String idStatusFail = kemaskiniSimpanStatusSelesai(idFail, idPermohonan, idSubUrusan, "83");
								hInsert.put("idStatusFail", idStatusFail);
								hInsert.put("idSusulan", idSusulan);
								hInsert.put("idMasuk", userId);
								getISusulan().simpanSusulanStatusFail(hInsert);

							} else if (portal_role.contains("PengarahNegeri")) {
								String idSusulan = getISusulan().simpan(setSusulanValues(idPermohonan));
								String idStatusFail = kemaskiniSimpanStatusSelesai(idFail, idPermohonan, idSubUrusan, "84");
								hInsert = new Hashtable();
								hInsert.put("idStatusFail", idStatusFail);
								hInsert.put("idSusulan", idSusulan);
								hInsert.put("idMasuk", userId);
								getISusulan().simpanSusulanStatusFail(hInsert);

							}
							
							this.context.put("readonly",
									"readonly=\"readonly\"");
							this.context.put("disabled", "disabled");
							this.context.put("mode", "view");
							
							this.context.put("hPergerakan", hPergerakan);
							senaraiTindakan = new Vector();
							senaraiTindakan = getISusulan().getMaklumat(idPermohonan);
							this.context.put("senaraiTindakan", senaraiTindakan);

						} else if (button.equals("KemaskiniKeputusan")) {
							keputusanPermohonan("update");
							initKeputusanPermohonan("readonly");
							this.context.put("visible", "0");

						} else if (button.equals("doViewForKemaskiniKeputusan")) {
							readOnly = "";
							disabled = "";
							style = "";

						} else if (button.equals("tindakanpermohonan")) {
							senaraiTindakan = new Vector();
							senaraiTindakan = getISusulan().getMaklumat(idPermohonan);
							this.context.put("senaraiTindakan", senaraiTindakan);

						} else if (button.equals("view")) {
							String idSusulan = getParam("idsusulan");
							mode = "view";
							this.context.put("readonly","readonly=\"readonly\"");
							this.context.put("disabled", "disabled");
							setSusulan(idPermohonan, idSusulan);

						} else if (button.equals("kemaskini")) {
							String idSusulan = getParam("idsusulan");
							this.context.put("readonly", "");
							this.context.put("disabled", "");
							mode = "update";
							setSusulan(idPermohonan, idSusulan);

						} else if (button.equals("dokemaskini")) {
							String idSusulan = getParam("idsusulan");
							String idSusulanStatus = getISusulan().kemaskini(setSusulanStatusValues(idPermohonan));
							setSusulan(idPermohonan, idSusulan);

							this.context.put("readonly","readonly=\"readonly\"");
							this.context.put("disabled", "disabled");
							mode = "view";

						} else if(button.equals("hapusTindakan")) {
							String idSusulan = getParam("idsusulan");
							
							getHTP().hapusTindakan(idSusulan);
							this.context.remove("senaraiTindakan");
							senaraiTindakan = new Vector();
							senaraiTindakan = getISusulan().getMaklumat(idPermohonan);
							this.context.put("senaraiTindakan", senaraiTindakan);
							
						}else {
							
							initKeputusanPermohonan("");

						}

					} else { // TAB BUKTI PEMBAYARAN
						myLog.info("Tab BUKTI PEMBAYARAN PERMOHONAN");
						if (button.equals("TambahPembayaran")) {
							
							Pembayaran("insert");
							// initMaklumatPembayaran("readonly");
							getValuesPembayaran("readonly");

						} else if (button.equals("KemaskiniPembayaran")) {
							Pembayaran("update");
							// initMaklumatPembayaran("readonly");
							getValuesPembayaran("readonly");

							this.context.put("visible", "0");

						} else if (button.equals("doViewForKemaskiniBuktiBayaran")) {
							
							readOnly = "";
							disabled = "";
							style = "";
							getValuesPembayaran("");

						} else { // DISPLAY
							// initMaklumatPembayaran("");
							getValuesPembayaran("");
						}
					}
					this.context.put("selectedTab3", selectedTab3);

				} else if ("Notis5A".equals(mode)) { // NOTIS 5 A

					idNotis = getParam("idNotis");
					this.context.put("idNotis", idNotis);
					// log.debug("idNotis:"+idNotis);

					if (button.equals("TambahNotis")) {
						// View Form Untuk Tambah Notis 5A
						this.context.remove("dat");

					} else if (button.equals("SimpanNotis")) {
						Notis5A("insert", null);
						senaraiNotis5A();

					} else if (button.equals("ViewNotis")) {
						viewNotis5A(idNotis);

					} else if (button.equals("KemaskiniNotis")) {
						tabmode = "2";
						Notis5A("update", idNotis);
						readOnly = "readonly";
						disabled = "disabled";
						style = readOnly + " class=" + disabled + " ";
						senaraiNotis5A();
						viewNotis5A(idNotis);

					} else if (button.equals("BuktiBayaranNotis")) {
						SimpanBuktiBayaranNotis();
						viewBuktiBayaranNotis();

					} else if (button.equals("BayaranNotisHapus")) {
						String idBayaran = getParam("idBayaran");
						getHTP().hapusBayaran(idBayaran);
						viewBuktiBayaranNotis();

					} else {
						// SENARAI NOTIS
						senaraiNotis5A();

					}

					selectedTab = "2"; // TAB NOTIS
					
					if (id_jenistanah.equals("3")) {
						
						selectedTab = "3"; // TAB NOTIS TANAH PENGAMBILAN

					}
					context.put("id_jenistanah", id_jenistanah);

				}
				this.context.put("idNegeriNotis",
						String.valueOf(TerimaPohonInfo.get("lblNegeri")));
				// this.context.put("selectedTab", selectedTab);
				// log.info("462,selectedTab:"+selectedTab);
				// Lama - lama
			} else if (submit.equals("viewBorangK")) {
				String idPermohonan = getParam("idpermohonan");
				String idFail = getParam("idfail");
				template_name = PATH + "borangKList.jsp";
				Vector<HTPBorangK> k = getKIntergration().getHTPBorangKList(
						idPermohonan);
				context.put("borangKList", k);
				context.put("idpermohonan", idPermohonan);
				context.put("idfail", idFail);
				context.remove("borangKMessage");

			} else if (submit.equals("searchBorangK")) {
				String idPermohonan = getParam("idpermohonan");
				String noFailPPT = getParam("noFailPPT");
				Vector<BorangK> k = getKIntergration().getAvailableList(
						noFailPPT);
				context.put("borangKList", k);
				template_name = PATH + "borangKList.jsp";
				context.put("idpermohonan", idPermohonan);

			} else if (submit.equals("simpanBorangK")) {
				template_name = PATH + "borangKList.jsp";
				String noFailPPT = getParam("noFailPPT");
				String idHakmilikPPT = getParam("idHakmilikPPT");
				String idPermohonanHTP = getParam("idpermohonan");
				getKIntergration().simpanHTPBorangK(idHakmilikPPT,
						idPermohonanHTP, idUser);
				Vector<BorangK> k = getKIntergration().getAvailableList(
						noFailPPT);
				context.put("borangKList", k);
				context.put("borangKMessage", "REKOD BERJAYA DISIMPAN");

			} else if ("daftarBorangK".equals(submit)) {
				String noFailPPT = getParam("noFailPPT");
				String idHakmilikPPT = getParam("idHakmilikPPT");
				String idPermohonanHTP = getParam("idpermohonan");
				template_name = PATH + "frmTerimaPermohonanBorangK.jsp";
				viewPohonFailBaruBorangK(mode);

			} else if ("kemaskinipermohonanborangK".equals(submit)) {
				myLog.debug("kemaskinipermohonanborangK:" + submit);
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				template_name = PATH + "frmTerimaPohonMaklumatTABB.jsp";
				// HEADER
				Hashtable TerimaPohonInfo = null;
				String hittButton = getParam("hittButton");
				// SIMPAN
				if ("Simpan".equals(hittButton)) {
					template_name = PATH + "FrmTerimaPohon.jsp";
					String idHakmilikPPT = getParam("idHakmilikPPT");
					String idFailBaru = doSimpanMaklumatPermohonan();
					AuditTrail.logActivity("1", iu.getIdSeksyen(), this,
							session, "INS", "FAIL PERMOHONAN ["
									+ fData.strNoFail + "] DITAMBAH ");
					TerimaPohonInfo = FrmSenaraiFailTerimaPohonData
							.getTerimaPohonInfo(idFailBaru);
					idpermohonan = (String) TerimaPohonInfo
							.get("lblIdPermohonan");
					// log.info("idHakmilikPPT="+idHakmilikPPT+",idPermohonan="+TerimaPohonInfo.get("lblIdPermohonan"));
					getKIntergration().simpanHTPBorangK(idHakmilikPPT,
							idpermohonan, idUser);
					idfail = idFailBaru;

				} else {
					System.out.println("****" + idfail);
					TerimaPohonInfo = FrmSenaraiFailTerimaPohonData
							.getTerimaPohonInfo(idfail);
				}
				idpermohonan = (String) TerimaPohonInfo.get("lblIdPermohonan");
				setMaklumatPermohonan(TerimaPohonInfo);

				// ASSIGN VALUES
				// DISABLE OPTION UTK PILIH NEGERI
				socNegeri = HTML.SelectNegeri("socnegeri2",
						Utils.parseLong(idnegeri), "disabled class=disabled");
				socDaerah = HTML.SelectSortDaerahByNegeri(idnegeri, "socdaerah2",
						Utils.parseLong(iddaerah), style,
						"onChange=\"doChanges2('" + tabmode + "')\"");
				socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim2",
						Utils.parseLong(idmukim), style);
				context.put("socNegeri2", socNegeri);
				context.put("socDaerah2", socDaerah);
				context.put("socMukim2", socMukim);
				String id_jenistanah = TerimaPohonInfo.get("lblidKodJTanah")
						.toString();

			} else if (submit.equals("maklumatasastanah")) {
				template_name = PATH + "maklumatanah/frmMaklumaTanahTABB.jsp";

				// MAKLUMAT ASAS TANAH
				String txtNoLot = getParam("txtNoLot");
				String noSyit = getParam("noSyit");
				String noPelan = getParam("noPelan");
				String socLot = getParam("socLot");
				String socLuas = getParam("socLuas");
				String LuasLama = getParam("txtLuasGabung");
				String Luas = getParam("Luas");
				String Lokasi = getParam("Lokasi");
				idmukim = getParam("socMukim2");
				noLot = getParam("socLot");
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				setPaging(false, false, true, false, false);
				// HEADER
				String pageMode = "";
				TerimaPohonInfo = FrmSenaraiFailTerimaPohonData
						.getTerimaPohonInfo(idfail);
				this.context.put("pageMode", pageMode);
				idpermohonan = (String) TerimaPohonInfo.get("lblIdPermohonan");
				iddaerah = String.valueOf(TerimaPohonInfo.get("lblIdDaerah"));
				setMaklumatPermohonan(TerimaPohonInfo);
				String id_jenistanah = TerimaPohonInfo.get("lblidKodJTanah")
						.toString();
				this.context.put("idNegeriNotis",
						String.valueOf(TerimaPohonInfo.get("lblNegeri")));

				// IF DO CHANGES DETECTED
				if (doChange.indexOf("doChange") != -1) {
					// OVERRIDE VALUES
					myLog.info("doChange:TAB MODE=" + tabmode);
					idnegeri = getParam("socnegeri2");
					iddaerah = getParam("socdaerah2");
					idmukim = getParam("socMukim2");

				}

				if (button.equals("SimpanMaklumatAsasTanah")) {
					myLog.debug("SimpanMaklumatAsasTanah");
					readOnly = "readonly";
					disabled = "disabled";
					style = readOnly + " class=" + disabled + " ";
					idhakmilikurusan = simpanMaklumatAsasTanah();
					getMaklumatTanah();
					idnegeri = getParam("socnegeri2");
					iddaerah = getParam("socdaerah2");
					idmukim = getParam("socMukim2");
					context.remove("TerimaPohonInfo");
					TerimaPohonInfo = FrmSenaraiFailTerimaPohonData
							.getTerimaPohonInfo(idfail);
					setMaklumatPermohonan(TerimaPohonInfo);

				} else if (button.equals("KemaskiniMaklumatInfo")) {
					myLog.info("KemaskiniMaklumatInfo");
					readOnly = "readonly";
					disabled = "disabled";
					style = readOnly + " class=" + disabled + " ";

					Hashtable t = getPR().getMaklumatAsasTanahKemaskini(
							idhakmilikurusan);
					idhakmilikurusan = (String) t.get("idhakmilikurusan");
					idnegeri = (String) t.get("idnegeri");
					iddaerah = (String) t.get("iddaerah");
					idmukim = (String) t.get("idmukim");
					txtNoLot = (String) t.get("nolot");
					noSyit = (String) t.get("nosyit");
					noPelan = (String) t.get("nopelan");
					socLot = (String) t.get("idlot");
					socLuas = (String) t.get("idluas");
					Luas = (String) t.get("luasH");
					LuasLama = (String) t.get("luas");
					String luas = "0";
					String luas1 = "0";
					String luas2 = "0";
					if (socLuas.equals("1") || socLuas.equals("2")
							|| socLuas.equals("3")
							|| getParam("socLuas").equals("5")
							|| getParam("socLuas").equals("6")) {
						if (socLuas.equals("1")) {
							if (LuasLama.contains("KM"))
								luas = LuasLama.substring(0,
										(LuasLama.length() - 2));
							else
								luas = LuasLama;

						} else if (socLuas.equals("2")) {
							if (LuasLama.contains("H"))
								luas = LuasLama.substring(0,
										(LuasLama.length() - 1));
							else
								luas = LuasLama;

						} else if (socLuas.equals("3")) {
							if (LuasLama.contains("MP"))
								luas = LuasLama.substring(0,
										(LuasLama.length() - 2));
							else {
								if (LuasLama.contains("M"))
									luas = LuasLama.substring(0,
											(LuasLama.length() - 1));
								else
									luas = LuasLama;
							}

						} else if (socLuas.equals("5")) {
							if (LuasLama.contains("KP"))
								luas = LuasLama.substring(0,
										(LuasLama.length() - 2));
							else {
								if (LuasLama.contains("K"))
									luas = LuasLama.substring(0,
											(LuasLama.length() - 1));
								else
									luas = LuasLama;
							}

						} else if (socLuas.equals("6")) {
							if (LuasLama.contains("P"))
								luas = LuasLama.substring(0,
										(LuasLama.length() - 1));
							else
								luas = LuasLama;

						}

					} else if (socLuas.equals("4")) {
						if (LuasLama.contains("E,") && LuasLama.contains("R,")) {
							luas = LuasLama
									.substring(0, LuasLama.indexOf("E,"));
							luas1 = LuasLama.substring(
									LuasLama.indexOf("E,") + 2,
									LuasLama.indexOf("R,"));
							luas2 = LuasLama.substring(
									LuasLama.indexOf("R,") + 2,
									(LuasLama.length() - 1));

						}
					} else if (socLuas.equals("7")) {
						// log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
						if (LuasLama.contains("E,") && LuasLama.contains("D")) {
							luas = LuasLama
									.substring(0, LuasLama.indexOf("E,"));
							luas1 = LuasLama.substring(
									LuasLama.indexOf("E,") + 2,
									LuasLama.indexOf("D"));
						}

					} else if (socLuas.equals("8")) {
						// log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
						if (LuasLama.contains("R,") && LuasLama.contains("J,")) {
							luas = LuasLama
									.substring(0, LuasLama.indexOf("R,"));
							luas1 = LuasLama.substring(
									LuasLama.indexOf("R,") + 2,
									LuasLama.indexOf("J"));
							luas2 = LuasLama.substring(
									LuasLama.indexOf("J,") + 2,
									(LuasLama.length() - 1));
						}

					} else { // 7||9 (TIADA SAMPLE DATA)
						luas = LuasLama;

					}
					this.context.put("txtLuasLama1", luas1.trim());
					this.context.put("txtLuasLama2", luas2.trim());
					this.context.put("txtLuasLama", luas);

					Lokasi = (String) t.get("lokasi");

				} else if (button.equals("doViewForKemaskini")) {
					readOnly = "";
					disabled = "";
					style = "";
					// idhakmilikurusan = kemaskiniMaklumatAsasTanah();
					getMaklumatTanah();

				} else if (button.equals("doKemaskiniMaklumatAsasTanah")) {
					myLog.info("doKemaskiniMaklumatAsasTanah");
					readOnly = "readonly";
					disabled = "disabled";
					style = readOnly + " class=" + disabled + " ";
					// doKemaskiniMaklumatAsasTanah()
					idhakmilikurusan = kemaskiniMaklumatAsasTanah();
					getMaklumatTanah();
					iddaerah = getParam("socdaerah2");
					idmukim = getParam("socMukim2");
					context.remove("TerimaPohonInfo");
					TerimaPohonInfo = FrmSenaraiFailTerimaPohonData
							.getTerimaPohonInfo(idfail);
					setMaklumatPermohonan(TerimaPohonInfo);

				} else if (button.equals("deletemaklumatanah")) {
					this.context.remove("MAT");
					// myLog.info("deletemaklumatanah:idhakmilikurusan:"+idhakmilikurusan+"");
					getHTP().hapusHakmilik(idhakmilikurusan);
					MaklumatAsasTanahInfo = fData
							.getMaklumatAsasTanahInfo(idpermohonan);
					this.context.put("MAT", MaklumatAsasTanahInfo);

				} else if (button.equals("DetailLot")) {
					// SET SELECTED
					myLog.debug("DetailLot");
					selectedTab2 = getParam("tabId2");
					this.context.put("selectedTab2", selectedTab2);
					if ("1".equals(selectedTab2)) {
						initMaklumatCharting("");

					} else {
						initMaklumatLokasi("");

					}

				} else if (button.equals("SimpanDetailLot")) {
					myLog.debug("SimpanDetailLot");
					SimpanLokasiTanah();
					initMaklumatLokasi("readonly");

				} else if (button.equals("doKemaskiniDetailLot")) {
					myLog.debug("doKemaskiniDetailLot");
					doKemaskiniLokasiTanah();
					initMaklumatLokasi("readonly");

				} else if (button.equals("doViewForKemaskiniDetailLot")) {
					readOnly = "";
					disabled = "";
					style = "";

				} else if (button.equals("SimpanCharting")) {
					myLog.debug("SimpanCharting");
					selectedTab2 = "1";
					this.context.put("selectedTab2", selectedTab2);
					SimpanCharting();
					initMaklumatCharting("readonly");

				} else if (button.equals("doKemaskiniCharting")) {
					myLog.debug("doKemaskiniCharting");
					selectedTab2 = "1";
					this.context.put("selectedTab2", selectedTab2);
					KemaskiniCharting();
					initMaklumatCharting("readonly");

				} else if (button.equals("doViewForKemaskiniCharting")) {
					readOnly = "";
					disabled = "";
					style = "";

				} else {
					// SENARAI
					myLog.debug(" ** SENARAI:tabmode= **" + tabmode);
					// komen pada 25/06/2010 oleh mrosli
					Vector MaklumatAsasTanahInfo = fData
							.getMaklumatAsasTanahInfo(idpermohonan);

					// untuk ppt
					// String id_jenistanah =
					// TerimaPohonInfo.get("lblidKodJTanah").toString();
					context.put("id_jenistanah", id_jenistanah);
					if (MaklumatAsasTanahInfo.size() > 0) {
						// tabmode = "3_1";
					}
					myLog.debug(" ** MaklumatAsasTanahInfo =====**"
							+ idpermohonan);
					this.context
							.put("bilBorangK", MaklumatAsasTanahInfo.size());
					this.context.put("MAT", MaklumatAsasTanahInfo);
					this.context.put("TerimaPohonInfo", TerimaPohonInfo);
					LuasLama = getParam("txtLuasGabung") == "" ? ""
							: getParam("txtLuas1");
				}

			} else if (submit.equals("maklumatbayarankeputusan")) {
				template_name = PATHVER
						+ "maklumatanah/frmMaklumaTanahTABB.jsp";
				myLog.info("maklumatbayarankeputusan=" + submit);
				TerimaPohonInfo = FrmSenaraiFailTerimaPohonData
						.getTerimaPohonInfo(idfail);
				idpermohonan = (String) TerimaPohonInfo.get("lblIdPermohonan");
				iddaerah = String.valueOf(TerimaPohonInfo.get("lblIdDaerah"));
				setMaklumatPermohonan(TerimaPohonInfo);
				String id_jenistanah = TerimaPohonInfo.get("lblidKodJTanah")
						.toString();
				this.context.put("idNegeriNotis",
						String.valueOf(TerimaPohonInfo.get("lblNegeri")));

				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				setPaging(false, false, true, false, false);
				// HEADER
				String hittButton = getParam("hittButton");
				String pageMode = "";
				// SIMPAN
				// if (mode.equals("Pembayaran")) {

				selectedTab = "1"; // TAB KEPUTUSAN/PEMBAYARAN
				if (id_jenistanah.equals("3")) {
					selectedTab = "2"; // TAB KEPUTUSAN/PEMBAYARAN (TANAH
										// PENGAMBILAN)

				}
				context.put("id_jenistanah", id_jenistanah);
				// myLog.debug("Pembayaran");
				this.context.put("visible", "1");
				selectedTab3 = getParam("tabId3");
				myLog.debug("Keputusan/Pembayaran:selectedTab3=" + selectedTab3);

				if (selectedTab3.equals("1")) {
					// if ("1".equals(tabmode)) { //TAB KEPUTUSAN
					myLog.debug("TAB HANTAR PTG/PTD:selectedTab3="
							+ selectedTab3);
					if (button.equals("hantarptgptdsimpan")) {
						// myLog.debug("hantarptgptdsimpan");
						Tblrujsuburusanstatusfail rsusf = new Tblrujsuburusanstatusfail();
						rsusf.setIdPermohonan(Long.parseLong(idpermohonan));
						rsusf.setIdFail(Long.parseLong(idfail));
						idsuburusan = String.valueOf(TerimaPohonInfo
								.get("lblidSubUrusan"));
						rsusf.setIdSuburusanstatusfail(Long
								.parseLong(idsuburusan));
						rsusf.setUrl(getParam("txtcatatanPTGPTD"));
						// myLog.info("txttarikhsuratPTGPTD 1 ="+getParam("txttarikhsuratPTGPTD"));
						// rsusf.setTarikhMasuk(new
						// Date(getParam("txttarikhsuratPTGPTD").equals("")?"01/01/1900":getParam("txttarikhsuratPTGPTD")));
						rsusf.setTarikhMasuk(getParam("txttarikhsuratPTGPTD")
								.equals("") ? "01/01/1900"
								: getParam("txttarikhsuratPTGPTD"));
						kemaskiniSimpanStatusSelesai(rsusf, "10");
						statusView(idpermohonan);
						this.context.put("selectedTab", 3);

					} else if (button.equals("kemaskiniptgptd")) {
						String idSuburusanStatusFail = getParam("txtid");
						long setIdSuburusanstatus = FrmUtilData
								.getIdSuburusanStatusByLangkah("10", String
										.valueOf(TerimaPohonInfo
												.get("lblidSubUrusan")), "=");
						Tblrujsuburusanstatusfail rsusf = new Tblrujsuburusanstatusfail();
						rsusf.setIdPermohonan(Long.parseLong(idpermohonan));
						rsusf.setIdFail(Long.parseLong(idfail));
						rsusf.setIdSuburusanstatus(setIdSuburusanstatus);
						rsusf.setUrl(getParam("txtcatatanPTGPTD"));
						rsusf.setTarikhKemaskini(new Date(
								getParam("txttarikhsuratPTGPTD").equals("") ? "01/01/1900"
										: getParam("txttarikhsuratPTGPTD")));
						// myLog.info(setIdSuburusanstatus);
						rsusf.setIdKemaskini(Long.parseLong(idUser));
						getStatus()
								.kemaskiniStatus(
										rsusf,
										getParam("txttarikhsuratPTGPTD")
												.equals("") ? "01/01/1900"
												: getParam("txttarikhsuratPTGPTD"));
						statusView(idpermohonan);

					} else if (button.equals("paparanptgptd")) {
						readOnly = "";
						disabled = "";
						style = "";
						statusView(idpermohonan);

					} else if (button.equals("hapusptgptd")) {
						readOnly = "";
						disabled = "";
						style = "";
						statusView(idpermohonan);

					} else {
						statusView(idpermohonan);

					}

					// }else if ("1".equals(selectedTab3)) {
				} else if ("2".equals(selectedTab3)) {
					// if ("1".equals(tabmode)) { //TAB KEPUTUSAN
					myLog.debug("TAB KEPUTUSAN");
					idsuburusan = String.valueOf(TerimaPohonInfo
							.get("lblidSubUrusan"));

					if (button.equals("TambahKeputusan")) {
						keputusanPermohonan("insert");
						// initKeputusanPermohonan("");
						initKeputusanPermohonan("readonly");
						this.context.put("visible", "0");
						this.context.put("pagemode", "kemaskini");

					} else if (button.equals("KemaskiniKeputusan")) {
						keputusanPermohonan("update");
						initKeputusanPermohonan("readonly");
						this.context.put("visible", "0");

					} else if (button.equals("doViewForKemaskiniKeputusan")) {
						readOnly = "";
						disabled = "";
						style = "";

					} else {
						initKeputusanPermohonan("");
					}

				} else if (selectedTab3.equals("3")) {
					myLog.debug("TAB TINDAKAN NEGERI");
					idsuburusan = String.valueOf(TerimaPohonInfo
							.get("lblidSubUrusan"));

					if (button.equals("TambahKeputusan")) {
						keputusanPermohonan("insert");
						// aeda tambah test
						initKeputusanPermohonan("readonly");

					} else if (button.equals("KemaskiniKeputusan")) {
						keputusanPermohonan("update");
						initKeputusanPermohonan("readonly");
						this.context.put("visible", "0");

					} else if (button.equals("doViewForKemaskiniKeputusan")) {
						readOnly = "";
						disabled = "";
						style = "";

					} else {
						initKeputusanPermohonan("");

					}

				} else { // TAB BUKTI PEMBAYARAN
					myLog.info("Tab BUKTI PEMBAYARAN PERMOHONAN");
					if (button.equals("TambahPembayaran")) {
						myLog.debug("TambahPembayaran");
						Pembayaran("insert");
						// initMaklumatPembayaran("readonly");
						getValuesPembayaran("readonly");

					} else if (button.equals("KemaskiniPembayaran")) {
						Pembayaran("update");
						// initMaklumatPembayaran("readonly");
						getValuesPembayaran("readonly");

						this.context.put("visible", "0");

					} else if (button.equals("doViewForKemaskiniBuktiBayaran")) {
						myLog.info("doViewForKemaskiniBuktiBayaran");
						readOnly = "";
						disabled = "";
						style = "";
						getValuesPembayaran("");

					} else { // DISPLAY
						// initMaklumatPembayaran("");
						getValuesPembayaran("");
					}
				}
				this.context.put("selectedTab3", selectedTab3);

			} else if (submit.equals("Notis5A")) {// NOTIS 5A
				template_name = PATHVER
						+ "maklumatanah/frmMaklumaTanahTABB.jsp";
				myLog.debug("Notis5A");
				TerimaPohonInfo = FrmSenaraiFailTerimaPohonData
						.getTerimaPohonInfo(idfail);
				// this.context.put("pageMode", pageMode);
				idpermohonan = (String) TerimaPohonInfo.get("lblIdPermohonan");
				iddaerah = String.valueOf(TerimaPohonInfo.get("lblIdDaerah"));
				setMaklumatPermohonan(TerimaPohonInfo);
				String id_jenistanah = TerimaPohonInfo.get("lblidKodJTanah")
						.toString();
				this.context.put("idNegeriNotis",
						String.valueOf(TerimaPohonInfo.get("lblNegeri")));

				idNotis = getParam("idNotis");
				this.context.put("idNotis", idNotis);
				if (button.equals("TambahNotis")) {
					// View Form Untuk Tambah Notis 5A
					this.context.remove("dat");
					inputStyleNum = " class=\"inputnumber\" ";
					context.put("inputStyleNum", inputStyleNum);

				} else if (button.equals("SimpanNotis")) {
					Notis5A("insert", null);
					senaraiNotis5A();

				} else if (button.equals("ViewNotis")) {
					myLog.info("Notis 5A:ViewNotis = " + button);
					String idcarabayar = "-1";
					String caraBayaran = "";
					caraBayaran = getIBayaran().selectCaraBayaran("socBayaran",
							Utils.parseLong(idcarabayar),
							style + " style=\"width:200\"", "", "");
					this.context.put("socBayaran", caraBayaran);
					viewNotis5A(idNotis);
					inputStyleNum = " class=\"inputnumber\" ";
					context.put("inputStyleNum", inputStyleNum);

				} else if (button.equals("KemaskiniNotis")) {
					tabmode = "2";
					Notis5A("update", idNotis);
					readOnly = "readonly";
					disabled = "disabled";
					style = readOnly + " class=" + disabled + " ";
					senaraiNotis5A();
					viewNotis5A(idNotis);

				} else if (button.equals("BuktiBayaranNotis")) {
					SimpanBuktiBayaranNotis();
					viewBuktiBayaranNotis();

				} else if (button.equals("BayaranNotisHapus")) {
					String idBayaran = getParam("idBayaran");
					getHTP().hapusBayaran(idBayaran);
					viewBuktiBayaranNotis();

				} else {
					// SENARAI NOTIS
					senaraiNotis5A();

				}

				selectedTab = "2"; // TAB NOTIS
				myLog.info("Notis 5A :id_jenistanah=" + id_jenistanah);
				if (id_jenistanah.equals("3")) {
					myLog.info("Notis 5A :pengambilan");
					selectedTab = "3"; // TAB NOTIS TANAH PENGAMBILAN

				}
				context.put("id_jenistanah", id_jenistanah);

			} else {

				template_name = PATHVER + "fail/index.jsp";

				// FIRST PAGE - SENARAI FAIL PERMOHONAN

				if (flagAdvSearch.equals("Y")) {
					// myLog.info("Y");
					senaraiTerimaPohon = getPR().TerimaPohongetList(null,
							nofail,
							txtTajukCarian == "" ? txtTajuk : txtTajukCarian,
							id_kementerian, id_agensi, idnegeri, iddaerah,
							idmukim, idurusan, tarikhBukaFail);
					doListing(session, action, mode, senaraiTerimaPohon);
				} else {
					//myLog.info("default page");
					senaraiTerimaPohon = getPR().TerimaPohongetList(idUser, "",
							"", "", "", "", "", "", "", tarikhBukaFail);

					doListing(session, action, mode, senaraiTerimaPohon);
					if (mode.equals("changeNegeri")) {
						flagAdvSearch = "Y";
						senaraiTerimaPohon = getPR().TerimaPohongetList(null,
								nofail, txtTajukCarian, id_kementerian,
								id_agensi, idnegeri, iddaerah, idmukim,
								idurusan, tarikhBukaFail);

						doListing(session, action, mode, senaraiTerimaPohon);
					}
				}

			}
			this.context.put("txtTajukCarian", txtTajukCarian);
			selectedTab2 = (String) this.context.get("selectedTab2");
			if (selectedTab2 == null || "".equals(selectedTab2)) {
				selectedTab2 = "0";
			}
			selectedTab3 = (String) this.context.get("selectedTab3");
			if (selectedTab3 == null || "".equals(selectedTab3)) {
				selectedTab3 = "0";
			}
			this.context.put("selectedTab", selectedTab);
			this.context.put("selectedTab2", selectedTab2);
			this.context.put("selectedTab3", selectedTab3);
			this.context.put("idnegeri", idnegeri);
			this.context.put("tabmode", tabmode);
			this.context.put("button", button);
			this.context.put("mode", mode);
			this.context.put("submit", submit);
			this.context.put("style", style);
			this.context.put("idpermohonan", idpermohonan);
			this.context.put("idPermohonan", idpermohonan);// Utk
															// frmTerimaPohon.jsp
			this.context.put("idhakmilikurusan", idhakmilikurusan);
			this.context.put("idfail", idfail);
			// 18/08/2010
			this.context.put("flagAdvSearch", flagAdvSearch);
			this.context.put("inputStyle", inputStyle);
			this.context.put("inputStyleNum", inputStyleNum);
			myLog.debug("button=" + button);
			myLog.debug("mode=" + mode);
			// myLog.debug("button="+button);

		} catch (Exception e) {
			e.printStackTrace();
			myLog.info("812");

		}
		myLog.info("************ template_name : " + template_name);
		return template_name;

	}

	/*--------------------nama dan ID Pengarah-------------------*/

	@SuppressWarnings({ "unchecked", "static-access" })
	private String nameAndIdPengarah(String id_ppengarah, String idNegeri)
			throws Exception {

		iu = InternalUserUtil.getSeksyenId(idUser);
		idNegeri = iu.getIdNegeri();
		Vector dataNamaPengarahP = new Vector();
		dataNamaPengarahP.clear();

		/* GET NAMA PENGARAH */
		String nama_pengarahp = "";
		String id_pengarahp = "";
		dataNamaPengarahP = model.setListEmel(id_ppengarah, idNegeri);
		if (dataNamaPengarahP.size() != 0) {
			Hashtable npp = (Hashtable) dataNamaPengarahP.get(0);
			nama_pengarahp = (String) npp.get("nama_pengarah");
			id_pengarahp = (String) npp.get("user_id");
		}
		context.put("nama_pengarah", nama_pengarahp);

		return id_pengarahp;

	}

	public void initContext() {
		this.context.put("socMukim", "");
	}

	public void emptyContext() {
		// log.debug(" ******** emptyContext ********888");
		this.context.put("txtNoFail", "");
		this.context.put("txtTajukFail", "");
	}

	public void doListing(HttpSession session, String action, String mode,
			Vector v) throws Exception {
		if (mode.indexOf("change") != -1) {
			myLog.debug("changes detected...");
			initContext();

		} else if ("cancel".equals(mode)) {
			myLog.debug("cancel mode");
			// emptyContext();
			nofail = "";
			txtTajuk = "";
			iddaerah = "-1";
			idmukim = "-1";
			id_agensi = "-1";

		} else {
			// myLog.debug(" do listing :"+nofail);

		}
		this.context.put("txtNoFail", nofail);
		this.context.put("txtTajuk", txtTajuk);

		socUrusan = UtilHTML.SelectUrusan("socUrusan",
				Utils.parseLong(idurusan), null);// disabled class=disabled
		socKementerian = HTML.SelectKementerian("socKementerian",
				Utils.parseLong(id_kementerian), null,
				"onChange=\"doChangeKementerianX()\" style=\"width:400\"");
		socAgensi = HTML.SelectAgensiByKementerian("socAgensi", id_kementerian,
				Utils.parseLong(id_agensi), "", " style=\"width:400\"");
		socNegeri = HTML.SelectNegeri("socNegeri", Utils.parseLong(idnegeri),
				DISABILITY);// ,"onChange=doChangeNegeriX()";
		socDaerah = HTML.SelectSortDaerahByNegeri(idnegeri, "socDaerah",
				Utils.parseLong(iddaerah), null,
				"onChange=\"doChangeDaerahX()\"");
		socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim",
				Utils.parseLong(idmukim), "");

		this.context.put("socUrusan", socUrusan);
		this.context.put("socKementerian", socKementerian);
		this.context.put("socAgensi", socAgensi);
		this.context.put("socDaerah", socDaerah);
		this.context.put("socNegeri", socNegeri);
		this.context.put("socMukim", socMukim);

		this.context.put("senaraiList1", v);
		this.context.put("disability", disability);
		setupPage(session, action, v);

	}

	public void setMaklumatPermohonan(Hashtable TerimaPohonInfo)
			throws Exception {
		try {
			String lblNegeri = "";
			String lblKementerian = "";
			String lblidAgensi = "";
			String lblidUrusan = "";
			String lblidSubUrusan = "";
			String lblidKodJTanah = "";
			String lblTanahKeselamatan = "";

			idurusan = (String) TerimaPohonInfo.get("lblidUrusan");
			idnegeri = (String) TerimaPohonInfo.get("lblNegeri");

			this.context.put("noFail", TerimaPohonInfo.get("lblNoFail"));
			this.context.put("diDaftarOleh",
					TerimaPohonInfo.get("diDaftarOleh"));
			this.context.put("diDaftarPada",
					TerimaPohonInfo.get("diDaftarPada"));

			socNegeri = HTML.SelectNegeri("lblNegeri",
					Utils.parseLong(idnegeri), DISABILITY);
			socDaerah = HTML.SelectSortDaerahByNegeri(idnegeri, "lblDaerah", Utils
					.parseLong((String) TerimaPohonInfo.get("lblIdDaerah")),
					disability,null);

			socKementerian = HTML.SelectKementerian("lblKementerian",
					Utils.parseLong((String) TerimaPohonInfo
							.get("lblidKementerian")), disability
							+ " style=\"width:400\"");
			socAgensi = HTML.SelectAgensi("lblidAgensi", Utils
					.parseLong((String) TerimaPohonInfo.get("lblidAgensi")),
					disability + " style=\"width:400\"");
			socUrusan = UtilHTML.SelectUrusan("lblidUrusan", Utils
					.parseLong((String) TerimaPohonInfo.get("lblidUrusan")),
					disability);

			socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,
					"lblidSubUrusan", Utils.parseLong((String) TerimaPohonInfo
							.get("lblidSubUrusan")), disability);
			socStatustanah = HTML.SelectJenisTanah("lblidKodJTanah", Utils
					.parseLong((String) TerimaPohonInfo.get("lblidKodJTanah")),
					disability);
			socjenisFail = HTML.SelectTarafKeselamatan("lblTanahKeselamatan",
					Utils.parseLong((String) TerimaPohonInfo
							.get("lblTanahKeselamatan")), disability);

			context.put("lblNamaNegeri", TerimaPohonInfo.get("lblNamaNegeri"));
			context.put("lblNamaDaerah", TerimaPohonInfo.get("lblNamaDaerah"));
			context.put("lblKementerian", TerimaPohonInfo.get("lblKementerian"));
			context.put("lblAgensi", TerimaPohonInfo.get("lblAgensi"));
			context.put("lblUrusan", TerimaPohonInfo.get("lblUrusan"));
			context.put("lblNamaSubUrusan",
					TerimaPohonInfo.get("lblNamaSubUrusan"));
			context.put("lblKeterangan", TerimaPohonInfo.get("lblKeterangan"));
			context.put("lblTarafKeselamatan",
					TerimaPohonInfo.get("lblTarafKeselamatan"));
			context.put("socKementerian", socKementerian);
			context.put("socStatustanah", socStatustanah);
			context.put("socUrusan", socUrusan);
			context.put("socAgensi", socAgensi);
			context.put("socNegeri", socNegeri);
			context.put("socDaerah", socDaerah);
			context.put("socjenisFail", socjenisFail);
			context.put("socSubUrusan", socSubUrusan);
			context.put("socNegeri", socNegeri);

			context.put("txtTajuk", TerimaPohonInfo.get("lblTujuan"));
			context.put("txdTarikhSuratKJP",
					TerimaPohonInfo.get("lblTrkhSurat"));
			context.put("txtnoFailKJP", TerimaPohonInfo.get("lblNoRujKJP"));
			context.put("txtnoFailUPT", TerimaPohonInfo.get("lblNoRujUPT"));
			context.put("idpermohonan", TerimaPohonInfo.get("lblIdPermohonan"));
			context.put("txtnoFailNegeri", TerimaPohonInfo.get("lblNoRujLain"));
			context.put("txtnoFailPTG", TerimaPohonInfo.get("lblNoRujPTG"));
			context.put("txtnoFailPTD", TerimaPohonInfo.get("lblNoRujPTD"));
		
		} catch (Exception e) {
			throw new Exception("Ralat:" + e.getCause());

		}

	}

	public void viewPohonFailBaru(String mode) throws Exception {
		// utk simpan
		socNegeri = HTML.SelectNegeri("socNegeri", Utils.parseLong(idnegeri),
				DISABILITY, "onChange=\"doChanges()\" ");
		socDaerah = HTML.SelectSortDaerahByNegeri(idnegeri, "socDaerah",
				Utils.parseLong(iddaerah), "", "");
		// socKementerian = HTML.SelectKementerian("socKementerian",
		// Utils.parseLong(id_kementerian), null,"onChange=\"doChanges()\" ");
		socKementerian = UtilHTML.SelectKementerianAktif("socKementerian",
				Utils.parseLong(id_kementerian), null,
				"onChange=\"doChanges()\" ");
		socAgensi = HTML.SelectAgensiByKementerian("socAgensi", id_kementerian,
				Utils.parseLong(id_agensi), "");
		socUrusan = UtilHTML.SelectUrusan("socUrusan",
				Utils.parseLong(idurusan), "onChange=\"doChanges()\" ");// disabled
																		// class=disabled

		// System.out.println(" viewPohonFailBaru idurusan : "+idurusan);

		socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan, "socSubUrusan",
				Utils.parseLong(idsuburusan), "");
		if (idurusan.equals("1")) {
			socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,
					"socSubUrusan", Utils.parseLong(IDSUBURUSANPERMOHONAN), "");
		}
		if (mode.indexOf("reset") != -1) {
			socNegeri = HTML.SelectNegeri("socNegeri",
					Utils.parseLong("99999"), "", "onChange=\"doChanges()\" ");
			socDaerah = HTML.SelectSortDaerahByNegeri(idnegeri, "socDaerah",
					Utils.parseLong("99999"), "", "");
			socKementerian = UtilHTML
					.SelectKementerianAktif("socKementerian",
							Utils.parseLong("99999"), null,
							"onChange=\"doChanges()\" ");

		}

		context.put("socKementerian", socKementerian);
		context.put("socUrusan", socUrusan);
		context.put("socAgensi", socAgensi);
		context.put("socNegeri", socNegeri);
		context.put("socDaerah", socDaerah);
		context.put("socSubUrusan", socSubUrusan);
		context.put("socNegeri", socNegeri);
		String statusTanah = "";
		String jenisFail = "";
		if (mode.indexOf("doChange") != -1) {
			myLog.debug(" ** CHANGES DETECTED !!");
			// socStatustanah = HTML.SelectJenisTanah("socStatustanah");
			// modified by rosli 2010/08/02
			// socStatustanah =
			// UtilHTML.SelectJenisTanah("socStatustanah",JENISTANAH);
			// modified by rosli 2010/09/03
			statusTanah = String.valueOf(getParamAsInteger("socStatustanah"));
			jenisFail = String.valueOf(getParamAsInteger("socjenisFail"));
			socStatustanah = UtilHTML.SelectJenisTanah("socStatustanah",
					Long.parseLong(statusTanah), "", JENISTANAH);
			socjenisFail = HTML.SelectTarafKeselamatan("socjenisFail",
					Long.parseLong(jenisFail), "");
			context.put("socStatustanah", socStatustanah);
			context.put("socjenisFail", socjenisFail);
			context.put("txtTajuk", getParam("txtTajuk"));
			context.put("noFail", "");
			// modified by rosli 2010/09/03
			context.put("txtnoFailKJP", getParam("txtnoFailKJP"));
			// modified by rosli 2010/09/03
			context.put("txtnoFailUPT", getParam("txtnoFailUPT"));
			// context.put("txdTarikhSuratKJP",lebah.util.Util.getDateTime(new
			// Date(), "dd/MM/yyyy"));
			// modified by rosli 2010/09/03
			context.put("txdTarikhSuratKJP", getParam("txdTarikhSuratKJP"));
			context.put("statusTanah", idurusan);
			context.put("txtnoFailNegeri", getParam("txtnofailnegeri"));
			context.put("txtnoFailPTG", getParam("txtnofailptg"));
			context.put("txtnoFailPTD", getParam("txtnofailptd"));

		} else {
			// 1-Tanah Kerajaan ,3-Tanah Kerajaan(Pengambilan),6-Tanah
			// Kerajaan(Penswastaan)
			// socStatustanah = HTML.SelectJenisTanah("socStatustanah");
			// modified by rosli 2010/08/02
			socStatustanah = UtilHTML.SelectJenisTanah("socStatustanah",
					JENISTANAH);
			socjenisFail = HTML.SelectTarafKeselamatan("socjenisFail");
			context.put("socStatustanah", socStatustanah);
			context.put("socjenisFail", socjenisFail);
			context.put("txtTajuk", "");
			context.put("noFail", "");
			context.put("txtnoFailKJP", "");
			context.put("txtnoFailUPT", "");
			context.put("txtnoFailNegeri", "");
			context.put("txtnoFailPTG", "");
			context.put("txtnoFailPTD", "");
			context.put("txdTarikhSuratKJP",
					lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));

		}

	}

	public void viewPohonFailBaruBorangK(String mode) throws Exception {
		String idHakmilikPPT = getParam("idHakmilikPPT");
		String idPermohonanHTP = getParam("idpermohonan");
		myLog.info("viewPohonFailBaruBorangK:idHakmilikPPT=" + idHakmilikPPT);
		// utk simpan
		borangK = getHTP().carianFailPPT("", idHakmilikPPT);
		idnegeri = borangK.getIdNegeri();
		iddaerah = borangK.getIdDaerah();
		id_kementerian = borangK.getIdKementerian();
		id_agensi = borangK.getIdAgensi();
		idurusan = "1";
		String txtnoFailUPT = borangK.getNoFail();
		socNegeri = HTML.SelectNegeri("socNegeri", Utils.parseLong(idnegeri),
				"", "onChange=\"doChangesBorangK()\" ");
		socDaerah = HTML.SelectSortDaerahByNegeri(idnegeri, "socDaerah",
				Utils.parseLong(iddaerah), "", "");
		socKementerian = HTML.SelectKementerian("socKementerian",
				Utils.parseLong(id_kementerian), null,
				"onChange=\"doChangesBorangK()\" ");
		socAgensi = HTML.SelectAgensiByKementerian("socAgensi", id_kementerian,
				Utils.parseLong(id_agensi), "");
		socUrusan = UtilHTML.SelectUrusan("socUrusan",
				Utils.parseLong(idurusan), "onChange=\"doChangesBorangK()\" ");// disabled
																				// class=disabled
		socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan, "socSubUrusan",
				Utils.parseLong(idsuburusan), "");
		if (idurusan.equals("1")) {
			socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,
					"socSubUrusan", Utils.parseLong(IDSUBURUSANPERMOHONAN), "");
		}

		context.put("socKementerian", socKementerian);
		context.put("socUrusan", socUrusan);
		context.put("socAgensi", socAgensi);
		context.put("socNegeri", socNegeri);
		context.put("socDaerah", socDaerah);
		context.put("socSubUrusan", socSubUrusan);
		context.put("socNegeri", socNegeri);
		context.put("idHakmilikPPT", idHakmilikPPT);

		if (mode.indexOf("doChange") != -1) {
			myLog.debug(" ** CHANGES DETECTED !!");
			// modified by rosli 2010/08/02
			// socStatustanah =
			// UtilHTML.SelectJenisTanah("socStatustanah",JENISTANAH);
			socStatustanah = UtilHTML.SelectJenisTanah("socStatustanah",
					Long.parseLong(getParam("socStatustanah")), "", JENISTANAH);
			socjenisFail = HTML.SelectTarafKeselamatan("socjenisFail");
			context.put("socStatustanah", socStatustanah);
			context.put("socjenisFail", socjenisFail);
			context.put("txtTajuk", getParam("txtTajuk"));
			context.put("noFail", "");
			context.put("txtnoFailKJP", "");
			context.put("txtnoFailUPT", getParam("txtnoFailUPT"));
			context.put("txdTarikhSuratKJP",
					lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));

		} else {
			// 1-Tanah Kerajaan ,3-Tanah Kerajaan(Pengambilan),6-Tanah
			// Kerajaan(Penswastaan)
			// modified by rosli 2010/08/02
			socStatustanah = UtilHTML.SelectJenisTanah("socStatustanah",
					Long.parseLong(JENISTANAHPENGAMBILAN), "", JENISTANAH);
			socjenisFail = HTML.SelectTarafKeselamatan("socjenisFail");
			context.put("socStatustanah", socStatustanah);
			context.put("socjenisFail", socjenisFail);
			context.put("txtTajuk", "");
			context.put("noFail", "");
			context.put("txtnoFailKJP", "");
			context.put("txtnoFailUPT", txtnoFailUPT);
			context.put("txdTarikhSuratKJP",
					lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));

		}

	}

	public String doSimpanMaklumatPermohonan() throws Exception {
		Hashtable h = new Hashtable();
		h.put("id_Fail", idfail);
		h.put("id_Tarafkeselamatan", "1");
		h.put("id_Seksyen", getParam("socSeksyen"));
		h.put("id_Urusan", idurusan);
		h.put("id_Suburusan", idsuburusan);// getParam("socSuburusan"));
		h.put("tajuk_Fail", txtTajuk);
		h.put("no_Fail", nofail);
		h.put("id_Negeri", idnegeri);
		h.put("id_Daerah", iddaerah);
		h.put("id_Kementerian", id_kementerian);
		h.put("id_Agensi", id_agensi);
		h.put("flag_Fail", 1);
		h.put("id_Status", "1");
		h.put("id_Masuk", idUser);
		h.put("TarikhSurKJP", getParam("txdTarikhSuratKJP"));
		h.put("TarikhPermohonan", getParam("txdTarikhSuratKJP"));
		h.put("noFailUPT", getParam("txtnoFailUPT"));
		h.put("noFailKJP", getParam("txtnoFailKJP"));
		h.put("StatusTanah", getParam("socStatustanah"));
		return fData.simpanPermohonan(h, idUser);
		// fData.janaFail(h);

	}

	public void doSimpanMaklumatAsasTanah() throws Exception {
		Hashtable data = new Hashtable();
		data.put("idUser", idUser);
		data.put("idpermohonan", getParam("idpermohonan"));
		data.put("socNegeri", getParam("socnegeri2"));
		data.put("socDaerah", getParam("socdaerah2"));
		data.put("socMukim", getParam("socMukim2"));
		data.put("socLot", getParam("socLot"));
		data.put("noLot", getParam("noLot"));
		data.put("txtNoLot", getParam("txtNoLot"));
		data.put("noSyit", getParam("noSyit"));
		data.put("noPelan", getParam("noPelan"));
		data.put("socLuas", getParam("socLuas"));
		data.put("Luas", getParam("Luas"));
		// data.put("LuasH", getParam("LuasH") == null ? "" :
		// getParam("LuasH"));
		data.put("LuasH", getParam("txtLuasGabung"));
		data.put("Lokasi", getParam("Lokasi"));
		// data.put("jenistanah", session.getAttribute("StatusTanah"));
		// 2010/11/04
		// FrmTerimaPohonData.simpanMaklumatAsasTanah(data);
		getPR().simpanMaklumatAsasTanah(data);

	}

	public String simpanMaklumatAsasTanah() throws Exception {
		Hashtable data = new Hashtable();
		data.put("idUser", idUser);
		data.put("idpermohonan", getParam("idpermohonan"));
		data.put("socNegeri", getParam("socnegeri2"));
		data.put("socDaerah", getParam("socdaerah2"));
		data.put("socMukim", getParam("socMukim2"));
		data.put("socLot", getParam("socLot"));
		data.put("noLot", getParam("noLot"));
		data.put("txtNoLot", getParam("txtNoLot"));
		data.put("noSyit", getParam("noSyit"));
		data.put("noPelan", getParam("noPelan"));
		data.put("socLuas", getParam("socLuas"));
		// data.put("LuasH", getParam("LuasH") == null ? "" :
		// getParam("LuasH"));
		data.put("LuasH", getParam("Luas"));
		data.put("Luas", getParam("txtLuasGabung"));
		data.put("Lokasi", getParam("Lokasi"));
		// data.put("jenistanah", session.getAttribute("StatusTanah"));
		// 2010/11/04
		// FrmTerimaPohonData.simpanMaklumatAsasTanah(data);
		// getPR().simpanMaklumatAsasTanah(data);
		return getPR().simpanMaklumatTanah(data);

	}

	public void doKemaskiniMaklumatAsasTanah() throws Exception {
		Hashtable data = new Hashtable();
		data.put("idUser", idUser);
		data.put("idhakmilikurusan", getParam("idhakmilikurusan"));
		data.put("idpermohonan", getParam("idpermohonan"));
		data.put("socNegeri", getParam("socnegeri2"));
		data.put("socDaerah", getParam("socdaerah2"));
		data.put("socMukim", getParam("socMukim2"));
		data.put("socLot", getParam("socLot"));
		data.put("noLot", getParam("noLot"));
		data.put("txtNoLot", getParam("txtNoLot"));
		data.put("noSyit", getParam("noSyit"));
		data.put("noPelan", getParam("noPelan"));
		data.put("socLuas", getParam("socLuas"));
		// data.put("Luas", getParam("Luas"));
		data.put("Luas", getParam("txtLuasGabung"));
		// data.put("LuasH", getParam("LuasH") == null ? "" :
		// getParam("LuasH"));
		data.put("LuasH", getParam("Luas") == null ? "" : getParam("Luas"));
		data.put("Lokasi", getParam("Lokasi"));
		// data.put("jenistanah", session.getAttribute("StatusTanah"));
		// 2010/11/04
		// FrmTerimaPohonData.updateMAT(data);
		getPR().kemaskiniMaklumatAsasTanah(data);
	}

	public String kemaskiniMaklumatAsasTanah() throws Exception {
		Hashtable data = new Hashtable();
		data.put("idUser", idUser);
		data.put("idhakmilikurusan", getParam("idhakmilikurusan"));
		data.put("idpermohonan", getParam("idpermohonan"));
		data.put("socNegeri", getParam("socnegeri2"));
		data.put("socDaerah", getParam("socdaerah2"));
		data.put("socMukim", getParam("socMukim2"));
		data.put("socLot", getParam("socLot"));
		data.put("noLot", getParam("noLot"));
		data.put("txtNoLot", getParam("txtNoLot"));
		data.put("noSyit", getParam("noSyit"));
		data.put("noPelan", getParam("noPelan"));
		data.put("socLuas", getParam("socLuas"));
		// data.put("Luas", getParam("Luas"));
		data.put("Luas", getParam("txtLuasGabung"));
		// data.put("LuasH", getParam("LuasH") == null ? "" :
		// getParam("LuasH"));
		data.put("LuasH", getParam("Luas") == null ? "" : getParam("Luas"));
		data.put("Lokasi", getParam("Lokasi"));
		// data.put("jenistanah", session.getAttribute("StatusTanah"));
		// 2010/11/04
		// FrmTerimaPohonData.updateMAT(data);
		return getPR().kemaskiniMaklumatTanah(data);
	}

	// /LOKASI TANAH

	private void initMaklumatLokasi(String readmode) throws Exception {
		Vector LokasiInfo = fData.getLokasiTanahInfo(idhakmilikurusan);
		Hashtable detail = null;
		if (LokasiInfo.size() > 0) {
			detail = (Hashtable) LokasiInfo.get(0);
			tabmode = "4";// Update
		} else {
			tabmode = "3";// Insert
		}
		this.context.put("detail", detail);

		if ("readonly".equals(readmode)) {
			readOnly = "readonly";
			disabled = "disabled";
			style = readOnly + " class=" + disabled + " ";
		} else {
			readOnly = "";
			disabled = "";
			style = "";
		}
	}

	private void initMaklumatCharting(String readmode) throws Exception {
		Vector LokasiInfo = fData.getPelanLakaranInfo(idhakmilikurusan);
		Hashtable detail = null;
		if (LokasiInfo.size() > 0) {
			detail = (Hashtable) LokasiInfo.get(0);
			tabmode = "4";// Update
		} else {
			tabmode = "3";// Insert
		}
		this.context.put("detail", detail);

		if ("readonly".equals(readmode)) {
			readOnly = "readonly";
			disabled = "disabled";
			style = readOnly + " class=" + disabled + " ";
		} else {
			readOnly = "";
			disabled = "";
			style = "";
		}
	}

	private void SimpanLokasiTanah() throws Exception {
		Hashtable data = new Hashtable();
		data.put("idUser", idUser);
		data.put("idhakmilikurusan", getParam("idhakmilikurusan"));

		data.put("txtbandar", getParam("txtJDbandar"));
		data.put("txtbandarperihal", getParam("txtJDbandarPerihal"));
		data.put("txtLebuhRaya", getParam("txtJDLebuhRaya"));
		data.put("txtLebuhRayaperihal", getParam("txtJDLebuhRayaPerihal"));
		data.put("txtJkeretapi", getParam("txtJDJalanKeretapi"));
		data.put("txtJkeretapiperihal", getParam("txtJDJalanKeretapiPerihal"));
		data.put("txtSgPntai", getParam("txtJDSungaiPantai"));

		data.put("txtSgPntaiperihal", getParam("txtJDSungaiPantaiPerihal"));
		data.put("txtSempadanUtara", getParam("txtSempadanUtara"));
		data.put("txtSempadanSelatan", getParam("txtSempadanSelatan"));
		data.put("txtSempadanTimur", getParam("txtSempadanTimur"));
		data.put("txtSempadanBarat", getParam("txtSempadanBarat"));
		data.put("txtSempadanKeteranganLain", getParam("txtKeteranganLain"));

		data.put("txtPerihalLokasi", getParam("PerihalLokasi"));
		data.put("txtZone", getParam("zone"));

		FrmTerimaPohonData.simpanLokasiTanah(data);
	}

	private void doKemaskiniLokasiTanah() throws Exception {
		Hashtable data = new Hashtable();
		data.put("idUser", idUser);
		data.put("idhakmilikurusan", getParam("idhakmilikurusan"));

		data.put("txtbandar", getParam("txtJDbandar"));
		data.put("txtbandarperihal", getParam("txtJDbandarPerihal"));
		data.put("txtLebuhRaya", getParam("txtJDLebuhRaya"));
		data.put("txtLebuhRayaperihal", getParam("txtJDLebuhRayaPerihal"));
		data.put("txtJkeretapi", getParam("txtJDJalanKeretapi"));
		data.put("txtJkeretapiperihal", getParam("txtJDJalanKeretapiPerihal"));
		data.put("txtSgPntai", getParam("txtJDSungaiPantai"));

		data.put("txtSgPntaiperihal", getParam("txtJDSungaiPantaiPerihal"));
		data.put("txtSempadanUtara", getParam("txtSempadanUtara"));
		data.put("txtSempadanSelatan", getParam("txtSempadanSelatan"));
		data.put("txtSempadanTimur", getParam("txtSempadanTimur"));
		data.put("txtSempadanBarat", getParam("txtSempadanBarat"));
		data.put("txtSempadanKeteranganLain", getParam("txtKeteranganLain"));

		data.put("txtPerihalLokasi", getParam("PerihalLokasi"));
		data.put("txtZone", getParam("zone"));

		FrmTerimaPohonData.kemaskiniLokasiTanah(data);
	}

	// /
	// CHARTING
	private void SimpanCharting() throws Exception {
		Hashtable data = new Hashtable();
		data.put("idUser", idUser);
		data.put("idpermohonan", getParam("idpermohonan"));
		data.put("idhakmilikurusan", getParam("idhakmilikurusan"));
		data.put("Flagcharting", getParam("RadioGroup1"));
		data.put("JumBayaranPelan", getParam("JumBayaranPelan"));
		data.put("ulasan", getParam("ulasan"));
		data.put("keteranganImej", getParam("keteranganImej"));
		data.put("nofail", this.context.get("noFail"));
		FrmTerimaPohonData.LakaranPelan(data, "insert");
	}

	private void KemaskiniCharting() throws Exception {
		Hashtable data = new Hashtable();
		data.put("idUser", idUser);
		data.put("idpermohonan", getParam("idpermohonan"));
		data.put("idhakmilikurusan", getParam("idhakmilikurusan"));
		myLog.debug("*** RadioGroup1:" + getParam("RadioGroup1"));
		data.put("Flagcharting", getParam("RadioGroup1"));
		data.put("JumBayaranPelan", getParam("JumBayaranPelan"));
		data.put("ulasan", getParam("ulasan"));
		data.put("keteranganImej", getParam("keteranganImej"));
		data.put("nofail", this.context.get("noFail"));
		FrmTerimaPohonData.LakaranPelan(data, "update");
	}

	// PEMBAYARAN
	private void initMaklumatPembayaran(String readmode) throws Exception {
		Vector PembayaranInfo = fData.getPembayaranInfo(idpermohonan);
		Hashtable pembayaran = null;
		String idcarabayar = "0";
		String caraBayaran = "";
		// pajakanBayaran = new FrmPajakanBayaranPajakanCukaiTanahData();

		if (PembayaranInfo.size() > 0) {
			pembayaran = (Hashtable) PembayaranInfo.get(0);
			tabmode = "4";// Update
			idcarabayar = (String) pembayaran.get("idcarabayar");
		} else {
			tabmode = "3";// Insert
		}
		myLog.debug("initMaklumatPembayaran:" + pembayaran);
		this.context.put("pembayaran", pembayaran);
		if ("readonly".equals(readmode)) {
			readOnly = "readonly";
			disabled = "disabled";
			style = readOnly + " class=" + disabled + " ";

		} else {
			readOnly = "";
			disabled = "";
			style = "";

		}
		caraBayaran = getIBayaran().selectCaraBayaran("socBayaran",
				Utils.parseLong(idcarabayar), style + " style=\"width:200\"",
				"", "");
		this.context.put("socBayaran", caraBayaran);

	}

	// PEMBAYARAN
	private void getValuesPembayaran(String readmode) throws Exception {
		Bayaran bayaran = null;
		bayaran = getIBayaran().getPembayaranByPermohonan(idpermohonan);
		myLog.info("bayaran:" + bayaran);
		String idCaraBayar = "0";
		String caraBayaran = "";
		String jumlahBayaranFormat = "0.00";
		readOnly = "";
		disabled = "";
		style = "";

		if (bayaran != null) {
			tabmode = "4";// Update
			idCaraBayar = getParam("socBayaran").equals("") ? String
					.valueOf(bayaran.getIdCaraBayaran())
					: getParam("socBayaran");
			if (this.button_.equalsIgnoreCase("doViewForKemaskiniBuktiBayaran")
					|| this.button_.equalsIgnoreCase("paparanbayaranper")) {
				myLog.info("idCaraBayar=" + idCaraBayar);
				caraBayaran = getIBayaran().selectCaraBayaran("socBayaran",
						Utils.parseLong(idCaraBayar),
						style + " style=\"width:200\"", "", "");

				this.context.put("mode", "");
				this.context.put("classDis", "");
				this.context.put("pagemode", "kemaskini");

			} else {
				caraBayaran = bayaran.getCaraBayar();
				tabmode = "1";// View
				this.context.put("mode", "disabled='disabled'");
				this.context.put("classDis", "class='disabled'");
				this.context.put("pagemode", "view");
				readOnly = "readonly";
				disabled = "disabled";
				style = readOnly + " class=" + disabled + " ";

			}
			jumlahBayaranFormat = Utils.format2Decimal(bayaran.getJumlah());

		} else {
			tabmode = "3";// Insert
			this.context.put("pagemode", "add");
			idCaraBayar = getParam("socBayaran");
			caraBayaran = getIBayaran().selectCaraBayaran("socBayaran",
					Utils.parseLong(idCaraBayar),
					style + " style=\"width:200\"", "", "");

		}
		//
		// if ("readonly".equals(readmode)) {
		// readOnly = "readonly";
		// disabled = "disabled";
		// style = readOnly + " class="+disabled+" ";
		//
		// }
		this.context.put("jumlahBayaranFormat", jumlahBayaranFormat);
		this.context.put("socBayaran", caraBayaran);
		this.context.put("pembayaran", bayaran);

	}

	private void Pembayaran(String mode) throws Exception {

		Hashtable data = null;
		data = new Hashtable();
		data.put("idUser", idUser);
		data.put("idpermohonan", getParam("idpermohonan"));
		// data.put("idhakmilikurusan", getParam("idhakmilikurusan"));
		data.put("TarikhSuratKePTD", getParam("txtTarikhSuratKePTD"));
		data.put("NoResit", getParam("txtNoResit"));
		data.put("caraBayaran", getParam("socBayaran"));
		data.put("tempatBayaran", getParam("txttempatBayaran"));
		data.put("JumBayaran", getParam("txtBayaranProses"));
		data.put("TarikhResit", getParam("txtTarikhResit"));
		data.put("NoBaucerCekDraft", getParam("txtNoBaucerCekDraft"));
		data.put("TarikhBaucerCek", getParam("txtTarikhBaucerCek"));
		FrmTerimaPohonData.Pembayaran(data, mode);

	}

	private void initKeputusanPermohonan(String readmode) throws Exception {
		Vector KeputusanInfo = fData.getKeputusanInfo(idpermohonan);
		Hashtable keputusan = null;
		String idcarabayar = "-1";
		String caraBayaran = "";
		//myLog.debug("*KeputusanInfo.size()=========** " + KeputusanInfo.size());
		if (KeputusanInfo.size() > 0) {
			keputusan = (Hashtable) KeputusanInfo.get(0);
			tabmode = "4";// Update
		} else {
			tabmode = "3";// Insert
		}
		this.context.put("keputusan", keputusan);
		if ("readonly".equals(readmode)) {
			readOnly = "readonly";
			disabled = "disabled";
			style = readOnly + " class=" + disabled + " ";

		} else {
			readOnly = "";
			disabled = "";
			style = "";

		}

		// caraBayaran =
		// HTML.selectCaraBayar("socBayaran",Utils.parseLong(idcarabayar),style
		// + " width=40px ", null);
		// this.context.put("socBayaran", caraBayaran);

	}

	public void KeputusanPermohonan(String mode) throws Exception {
		KeputusanUlasan ku = new KeputusanUlasan();
		Tblrujsuburusanstatusfail sf = new Tblrujsuburusanstatusfail();
		Hashtable data = null;
		data = new Hashtable();
		data.put("idUser", idUser);
		data.put("idpermohonan", getParam("idpermohonan"));
		data.put("idkeputusanmohon", getParam("idkeputusanmohon"));
		data.put("NoRujukPTD", getParam("txtNoRujukPTD"));
		data.put("TarikhKeputusan", getParam("txtTarikhKeputusan"));
		data.put("PilihKeputusan", getParam("PilihKeputusan"));
		data.put("Catatan", getParam("txtCatatan"));
		data.put("idSubUrusan", idsuburusan);
		String flagNotifikasi = getParam("flagNotifikasi");
		data.put("flagNotifikasi", flagNotifikasi);
		// data.put("Catatan", getParam("txtCatatan"));
		FrmTerimaPohonData.KeputusanBayaran(data, mode);

	}

	public void keputusanPermohonan(String mode) throws Exception {
		KeputusanUlasan ku = new KeputusanUlasan();
		Tblrujsuburusanstatusfail sf = new Tblrujsuburusanstatusfail();
		sf.setIdMasuk(Long.parseLong(idUser));
		sf.setIdPermohonan(Long.parseLong(getParam("idpermohonan")));
		sf.setIdSuburusanstatus(Long.parseLong(idsuburusan));
		sf.setIdFail(Long.parseLong(idfail));
		ku.setSubUrusanStatusFail(sf);
		ku.setIdUlasan(Long
				.parseLong(getParam("idkeputusanmohon").equals("") ? "0"
						: getParam("idkeputusanmohon")));
		ku.setNo(getParam("txtNoRujukPTD"));
		ku.setTarikhTerima(getParam("txtTarikhKeputusan"));
		ku.setKeputusan(getParam("PilihKeputusan"));
		String flagNotifikasi = getParam("flagNotifikasi");
		ku.setFlagNotifikasi(flagNotifikasi);

		ku.setUlasan(getParam("txtCatatan"));
		if ("update".equals(mode)) {
			getIPermohonan().keputusanPermohonanTanah(ku);
		} else {
			getIPermohonan().keputusanPermohonanTanah(ku);
		}

	}

	// /NOTIS 5A

	public void senaraiNotis5A() throws Exception {
		Vector Notis5ASenarai = fData.getSenaraiNotis5A(idpermohonan);
		this.context.put("SenaraiNotis5A", Notis5ASenarai);
	}

	public void viewNotis5A(String idNotis) throws Exception {
		Hashtable Notis5A = fData.getDataNotis5A(idNotis);
		this.context.put("dat", Notis5A);
		this.context.put("viewNotis", 0);
		viewBuktiBayaranNotis();
	}

	public void Notis5A(String mode, String idNotis) throws Exception {

		Hashtable hNotis = new Hashtable();
		hNotis.put("idUser", idUser);
		hNotis.put("idpermohonan", Utils.isNull(getParam("idpermohonan")));
		hNotis.put("NoKPPemilikAsal",
				Utils.isNull(getParam("txtNoKPPemilikAsal")));
		hNotis.put("TarikhNotis5A", Utils.isNull(getParam("txtTarikhNotis5A")));
		hNotis.put("TarikhLuputPertama",
				Utils.isNull(getParam("txtTarikhLuputPertama")));
		hNotis.put("CukaiTahunPertama", Utils.isNull(Utils
				.RemoveComma(getParam("txtCukaiTahunPertama"))));
		hNotis.put("BayaranNotis", Utils.isNull(getParam("txtBayaranNotis")));
		hNotis.put("BayaranNotisF", Utils.isNull(getParam("txtBayaranNotisF")));
		hNotis.put("Premium",
				Utils.isNull(Utils.RemoveComma(getParam("txtPremium"))));
		hNotis.put("RayuanPremium", Utils.isNull(getParam("txtRayuanPremium")));
		hNotis.put("RayuanLain", Utils.isNull(getParam("txtRayuanLain")));
		hNotis.put("PerihalRayuan", Utils.isNull(getParam("txtPerihalRayuan")));
		hNotis.put("TarikhTerimaNotis5A",
				Utils.isNull(getParam("txtTarikhTerimaNotis5A")));
		hNotis.put("TarikhLuputNotisKedua",
				Utils.isNull(getParam("txtTarikhLuputNotisKedua")));
		hNotis.put("TarikhLuputNotisKetiga",
				Utils.isNull(getParam("txtTarikhLuputNotisKetiga")));
		hNotis.put("PendaftaranHakmilik",
				Utils.isNull(getParam("txtPendaftaranHakmilik")));
		hNotis.put("BayarUkur", Utils.isNull(getParam("txtBayarUkur")));
		hNotis.put("BayaranPerenggan",
				Utils.isNull(getParam("txtBayaranPerenggan")));
		hNotis.put("TandaSempadan", Utils.isNull(getParam("txtTandaSempadan")));
		hNotis.put("BayaranLain", Utils.isNull(getParam("txtBayaranLain")));
		hNotis.put("JumBayaran", Utils.isNull(getParam("txtJumBayaran")));
		// --Azam Add
		hNotis.put("BayaranLain2", Utils.isNull(getParam("txtBayaranLain2")));
		hNotis.put("BayaranLain3", Utils.isNull(getParam("txtBayaranLain3")));
		hNotis.put("BayaranFail", Utils.isNull(getParam("txtBayaranFail")));
		
		hNotis.put("TarikhRayuan",Utils.isNull(getParam("TarikhRayuan")));
		hNotis.put("TempohRayuan", Utils.isNull(getParam("txtTempohRayuan")));

		FrmTerimaPohonData.Notis5A(hNotis, mode, idNotis);

	}

	private void SimpanBuktiBayaranNotis() throws Exception {

		Hashtable data = null;
		data = new Hashtable();
		data.put("idUser", idUser);
		data.put("idPermohonan", getParam("dipermohonanNotis"));
		data.put("NoBaucer", getParam("txtNoBaucer") == null ? ""
				: getParam("txtNoBaucer"));
		data.put("TarikhBaucer", getParam("txtTarikhBaucer") == null ? ""
				: getParam("txtTarikhBaucer"));
		data.put("TarikhResit", getParam("txtTarikhResit") == null ? ""
				: getParam("txtTarikhResit"));
		data.put("JumBaucer", getParam("txtJumBaucer") == null ? ""
				: getParam("txtJumBaucer"));
		data.put("NoResit", getParam("txtNoResit") == null ? ""
				: getParam("txtNoResit"));
		FrmTerimaPohonData.simpanBuktiNotis5A(data);
	}

	private void viewBuktiBayaranNotis() throws Exception {
		Vector BuktiBayaranInfo = fData.getBuktiBayaranNotis5A(idpermohonan);
		this.context.put("BuktiBayaranInfo", BuktiBayaranInfo);
	}

	public void setPaging(boolean page1, boolean page2, boolean page3,
			boolean page4, boolean page5) {
		this.context.put("page1", page1);
		this.context.put("page2", page2);
		this.context.put("page3", page3);
		this.context.put("page4", page4);
		this.context.put("page5", page5);
	}

	private void transferRecord(String idPermohonan) {
		HakmilikInterface bean = new HakmilikBean();
		bean.transferRecord(idPermohonan);
	}

	private IBorangKIntergration getKIntergration() {
		if (kIntergration == null)
			kIntergration = new BorangKIntergrationBean();
		return kIntergration;
	}

	private IHtp getHTP() {
		if (iHtp == null)
			iHtp = new HtpBean();
		return iHtp;
	}

	private IPermohonanPerizapan getPR() {
		if (ipr == null)
			ipr = new PermohonanPerizapanBean();
		return ipr;
	}

	private void getValues() {
		// String idNegeri = getParam("socNegeri");
		// String idKementerian = getParam("socKementerian");
		// String idSubUrusan = getParam("socSuburusan");
		String tajuk = getParam("txtTajuk");
		String failKJP = getParam("txtnoFailKJP");
		String tarikhSuratKJP = getParam("txdTarikhSuratKJP");
		// String tarikhPermohonan = getParam("txdTarikhPermohonan");
		// String idJenisTanah = getParam("socStatusTanah");
		// String noFail = getParam("txtNoFailSek");
		String idPermohonan = getParam("idpermohonan");
		// String idHtpPermohonan = getParam("txtidHtpPermohonan");
		String noFailUPT = getParam("txtnoFailUPT");
		String noFailLain = getParam("txtnofailnegeri");
		String noFailPTG = getParam("txtnofailptg");
		String noFailPTD = getParam("txtnofailptd");
		String idDaerah = getParam("socdaerah2");
		String idAgensi = getParam("lblidAgensi");
		String idKeselamatan = getParam("lblTanahKeselamatan");

		fail = new PfdFail();
		permohonan = new Permohonan();
		htpPermohonan = new HtpPermohonan();

		// fail.setIdNegeri(idNegeri);
		// fail.setIdKementerian(idKementerian);
		// fail.setIdSubUrusan(idSubUrusan);
		fail.setIdTarafKeselamatan(idKeselamatan);
		// fail.setNoFail(noFail);
		// senaraiFail = new FrmSenaraiFailTerimaPohonData();
		// senaraiFail.getKementerianByMampu(Integer.parseInt(idKementerian));

		// htpPermohonan.setIdHtpPermohonan(idHtpPermohonan);
		permohonan.setTujuan(tajuk);
		htpPermohonan.setNoRujukanKJP(failKJP);
		permohonan.setTarikhSurat(tarikhSuratKJP);
		htpPermohonan.setNoRujukanLain(noFailLain);
		htpPermohonan.setNoRujukanUPT(noFailUPT);
		htpPermohonan.setNoRujukanPTG(noFailPTG);
		htpPermohonan.setNoRujukanPTD(noFailPTD);
		// permohonan.setTarikhTerima(tarikhPermohonan);
		permohonan.setTarikhTerima(tarikhSuratKJP);
		// htpPermohonan.setIdJenisTanah(idJenisTanah);
		permohonan.setIdPermohonan(idPermohonan);
		permohonan.setIdMasuk(Long.parseLong(idUser));
		permohonan.setPfdFail(fail);
		htpPermohonan.setPermohonan(permohonan);
		htpPermohonan.setIdAgensi(idAgensi);
		htpPermohonan.setIdDaerah(idDaerah);

		context.put("permohonan", htpPermohonan);

	}

	private void getMaklumatTanah() throws Exception {

		Hashtable t = getPR().getMaklumatAsasTanahKemaskini(idhakmilikurusan);
		idhakmilikurusan = (String) t.get("idhakmilikurusan");
		idnegeri = (String) t.get("idnegeri");
		iddaerah = (String) t.get("iddaerah");
		idmukim = (String) t.get("idmukim");
		txtNoLot = (String) t.get("nolot");
		noSyit = (String) t.get("nosyit");
		noPelan = (String) t.get("nopelan");
		socLot = (String) t.get("idlot");
		socLuas = (String) t.get("idluas");
		Luas = (String) t.get("luasH");
		LuasLama = (String) t.get("luas");
		myLog.info("idhakmilikurusan ================:" + idhakmilikurusan);

		String luas = "0";
		String luas1 = "0";
		String luas2 = "0";
		if (socLuas.equals("1") || socLuas.equals("2") || socLuas.equals("3")
				|| getParam("socLuas").equals("5")
				|| getParam("socLuas").equals("6")) {
			if (socLuas.equals("1")) {
				if (LuasLama.contains("KM"))
					luas = LuasLama.substring(0, (LuasLama.length() - 2));
				else
					luas = LuasLama;

			} else if (socLuas.equals("2")) {
				if (LuasLama.contains("H"))
					luas = LuasLama.substring(0, (LuasLama.length() - 1));
				else
					luas = LuasLama;

			} else if (socLuas.equals("3")) {
				if (LuasLama.contains("MP"))
					luas = LuasLama.substring(0, (LuasLama.length() - 2));
				else {
					if (LuasLama.contains("M"))
						luas = LuasLama.substring(0, (LuasLama.length() - 1));
					else
						luas = LuasLama;
				}

			} else if (socLuas.equals("5")) {
				if (LuasLama.contains("KP"))
					luas = LuasLama.substring(0, (LuasLama.length() - 2));
				else {
					if (LuasLama.contains("K"))
						luas = LuasLama.substring(0, (LuasLama.length() - 1));
					else
						luas = LuasLama;
				}

			} else if (socLuas.equals("6")) {
				if (LuasLama.contains("P"))
					luas = LuasLama.substring(0, (LuasLama.length() - 1));
				else
					luas = LuasLama;

			}

		} else if (socLuas.equals("4")) {
			if (LuasLama.contains("E,") && LuasLama.contains("R,")) {
				luas = LuasLama.substring(0, LuasLama.indexOf("E,"));
				luas1 = LuasLama.substring(LuasLama.indexOf("E,") + 2,
						LuasLama.indexOf("R,"));
				luas2 = LuasLama.substring(LuasLama.indexOf("R,") + 2,
						(LuasLama.length() - 1));

			}
		} else if (socLuas.equals("7")) {
			// log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
			if (LuasLama.contains("E,") && LuasLama.contains("D")) {
				luas = LuasLama.substring(0, LuasLama.indexOf("E,"));
				luas1 = LuasLama.substring(LuasLama.indexOf("E,") + 2,
						LuasLama.indexOf("D"));
			}

		} else if (socLuas.equals("8")) {
			// log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
			if (LuasLama.contains("R,") && LuasLama.contains("J,")) {
				luas = LuasLama.substring(0, LuasLama.indexOf("R,"));
				luas1 = LuasLama.substring(LuasLama.indexOf("R,") + 2,
						LuasLama.indexOf("J"));
				luas2 = LuasLama.substring(LuasLama.indexOf("J,") + 2,
						(LuasLama.length() - 1));
			}

		} else { // 7||9 (TIADA SAMPLE DATA)
			luas = LuasLama;

		}
		this.context.put("txtLuasLama1", luas1.trim());
		this.context.put("txtLuasLama2", luas2.trim());
		this.context.put("txtLuasLama", luas);

		Lokasi = (String) t.get("lokasi");

	}

	private void kemaskiniSimpanStatusSelesai(Tblrujsuburusanstatusfail rsusf,
			String langkah) throws Exception {
		try {
			subUrusanStatusFail = new Tblrujsuburusanstatusfail();
			subUrusanStatusFail.setIdPermohonan(rsusf.getIdPermohonan());
			// myLog.info("idPermohonan:"+idPermohonan+",idFail:"+idFail);
			subUrusanStatusFail.setIdFail(rsusf.getIdFail());
			subUrusanStatusFail.setAktif("0");

			Tblrujsuburusanstatusfail subUrusanStatusFailN = new Tblrujsuburusanstatusfail();
			long setIdSuburusanstatus = FrmUtilData
					.getIdSuburusanStatusByLangkah(langkah,
							String.valueOf(rsusf.getIdSuburusanstatusfail()),
							"=");
			// myLog.info("setIdSuburusanstatus:"+setIdSuburusanstatus);
			subUrusanStatusFailN.setIdSuburusanstatus(setIdSuburusanstatus);
			subUrusanStatusFailN.setAktif("1");
			// myLog.info("getParam(\"txtKeterangan\"):"+getParam("txtKeterangan"));
			subUrusanStatusFailN.setUrl(Utils.isNull(rsusf.getUrl()));
			subUrusanStatusFailN.setIdMasuk(Long.parseLong(idUser));
			// myLog.info("getParam(\"txdTarikhTerima\"):"+getParam("txdTarikhTerima"));
			// myLog.info("txttarikhsuratPTGPTD 2 ="+rsusf.getTarikhMasukStr());
			subUrusanStatusFailN.setTarikhMasuk(rsusf.getTarikhMasukStr());
			// getHTP().kemaskiniSimpanStatusPermohonanAktif(subUrusanStatusFail,
			// subUrusanStatusFailN,rsusf.getTarikhMasukFormat());
			getHTP().kemaskiniSimpanStatusPermohonanAktif(subUrusanStatusFail,
					subUrusanStatusFailN, rsusf.getTarikhMasukStr());

		} catch (Exception e) {
			throw new Exception("Ralat FrmPermohonanTanah[1674]:"
					+ e.getCause());

		}

	}

	public void statusView(String idPermohonan) throws Exception {
		Hashtable hInfo = null;
		try {
			hInfo = new Hashtable();
			// hInfo = getHTP().getInfoSelesaiPermohonan(idPermohonan,"10");
			hInfo = getStatus().getInfoStatusPermohonan(idPermohonan, "10");
			// myLog.info("statusView("+idPermohonan+"):hInfo="+hInfo);
			// NumberToWords nw = new NumberToWords();
			// myLog.info(nw.convertTwoPart("12356777.22"));

			if (hInfo == null) {
				this.context.put("selesaiBean", hInfo);
				this.context.put("mode", "");
				this.context.put("classDis", "");
				this.context.put("pagemode", "baru");
				readOnly = "";
				disabled = "";
				style = "";
				tabmode = "3";// Insert

			} else {
				tabmode = "4";// Update
				if (this.button_.equalsIgnoreCase("paparanptgptd")) {
					this.context.put("selesaiBean", hInfo);
					this.context.put("mode", "");
					this.context.put("classDis", "");
					this.context.put("pagemode", "kemaskini");

				} else {
					tabmode = "1";// View
					this.context.put("selesaiBean", hInfo);
					this.context.put("mode", "disabled='disabled'");
					this.context.put("classDis", "class='disabled'");
					this.context.put("pagemode", "view");

				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private Hashtable setSusulanValues(String idPermohonan) throws Exception {
		String tarikh = getParam("txtarikhkeputusan");
		String catatan = getParam("txtcatatan");
		Hashtable h = new Hashtable();
		h.put("txdTarikh", tarikh);
		h.put("idPermohonan", idPermohonan);
		h.put("catatan", catatan);
		h.put("idMasuk", userId);
		h.put("idSusulanStatus", getParam("idsusulanstatus"));
		return h;
	}

	// Skrin Tindakan
	private String kemaskiniSimpanStatusSelesai(String idFail,
			String idPermohonan, String idSubUrusan, String langkah)
			throws Exception {
		try {
			subUrusanStatusFail = new Tblrujsuburusanstatusfail();
			subUrusanStatusFail.setIdPermohonan(Long.parseLong(idPermohonan));
			myLog.info("idFail=" + idFail + ",idPermohonan=" + idPermohonan);
			subUrusanStatusFail.setIdFail(Long.parseLong(idFail));
			subUrusanStatusFail.setAktif("0");

			Tblrujsuburusanstatusfail subUrusanStatusFailN = new Tblrujsuburusanstatusfail();
			long setIdSuburusanstatus = FrmUtilData
					.getIdSuburusanStatusByLangkah(langkah, idSubUrusan, "=");
			subUrusanStatusFailN.setIdSuburusanstatus(setIdSuburusanstatus);
			subUrusanStatusFailN.setAktif("1");
			myLog.info("setIdSuburusanstatus=" + setIdSuburusanstatus
					+ ",getParam(\"txtarikhkeputusan\")="
					+ getParam("txtarikhkeputusan"));
			// subUrusanStatusFailN.setUrl(getParam("txtcatatan") == null ? "" :
			// getParam("txtcatatan"));
			subUrusanStatusFailN.setUrl("-");
			subUrusanStatusFailN.setIdMasuk(Long.parseLong(userId));
			myLog.info("getParam(\"txdTarikhTerima\")="
					+ getParam("txtarikhkeputusan"));
			subUrusanStatusFailN.setTarikhMasuk(new Date(
					getParam("txtarikhkeputusan")));
			return String.valueOf(getStatus().kemaskiniSimpanStatusAktif(
					subUrusanStatusFail, subUrusanStatusFailN,
					getParam("txtarikhkeputusan")));

		} catch (Exception e) {
			throw new Exception(
					"Ralat FrmPajakanKecilNegeri, kemaskiniSimpanStatusSelesai:"
							+ getIHTP().getErrorHTML(e.getMessage()));

		}
	}

	private void setSusulan(String idPermohonan, String idSusulan)
			throws Exception {
		Hashtable h = getISusulan().getMaklumat(idPermohonan, idSusulan);
		this.context.put("idSusulanStatus", h.get("idSusulanStatus"));
		this.context.put("idSusulan", idSusulan);
		this.context.put("txtCatatan", h.get("keterangan"));
		this.context.put("tarikhHantar", h.get("tarikh"));

	}

	private Hashtable setSusulanStatusValues(String idPermohonan)
			throws Exception {
		String tarikh = getParam("txtarikhkeputusan");
		String catatan = getParam("txtcatatan");
		Hashtable h = getISusulan().getMaklumat(idPermohonan,
				getParam("idsusulan"));

		Hashtable hBaru = new Hashtable();
		hBaru.put("txdTarikh", tarikh);
		hBaru.put("idPermohonan", idPermohonan);
		hBaru.put("catatan", catatan);
		hBaru.put("idMasuk", userId);
		hBaru.put("idSubUrusanStatusFail", h.get("idStatusFail"));
		hBaru.put("idSusulan", h.get("idSusulan"));
		hBaru.put("idSusulanStatus", h.get("idSusulanStatus"));
		return hBaru;
	}

	private IHTPStatus getStatus() {
		if (iStatus == null) {
			iStatus = new HTPStatusBean();
		}
		return iStatus;

	}

	private IHTPPermohonan getIPermohonan() {
		if (iPermohonan == null) {
			iPermohonan = new HTPPermohonanBean();
		}
		return iPermohonan;

	}

	private IHTPBayaran getIBayaran() {
		if (iBayaran == null) {
			iBayaran = new HTPBayaranPermohonanBean();
		}
		return iBayaran;

	}

	private IHTPSusulan getISusulan() {
		if (iSusulan == null) {
			iSusulan = new HTPSusulanBean();
		}
		return iSusulan;

	}

	private IHtp getIHTP() {
		if (iHTP == null)
			iHTP = new HtpBean();
		return iHTP;

	}

	/*--------------------function hantar email-------------------*/
	public void hantarEmel(String idpermohonan, String content, String subjek,
			String id_pengarahP) throws Exception {

		Vector checkEmailPengarah = new Vector();
		checkEmailPengarah.clear();
		System.out.println("id dataListPengarah :::" + id_pengarahP);

		// check email (pengarah)
		checkEmailPengarah = myInfo.checkEmail(id_pengarahP);
		String emelPengarah = "";
		if (checkEmailPengarah.size() != 0) {
			Hashtable ceP = (Hashtable) checkEmailPengarah.get(0);
			emelPengarah = (String) ceP.get("EMEL");
		}

		System.out.println("emel ::" + emelPengarah);

		EmailProperty pro = EmailProperty.getInstance();
		EmailSender email = EmailSender.getInstance();

		// myLogger.info("EMAIL_HOST :"+EMAIL_HOST);
		// myLogger.info("EMAIL_FROM :"+pro.getFrom_GM());
		email.FROM = pro.getFrom();
		email.SUBJECT = subjek;
		email.MESSAGE = "<html><table><tr><td>Permohonan baru telah berjaya disimpan seperti berikut :</td></tr></table>"
				+ "<table><tr><td>ID Hakmilik Urusan</td><td>:</td><td>"
				+ idhakmilikurusan
				+ "</td></tr>"
				+ "<tr><td>Tajuk</td><td>:</td><td>"
				+ txtTajuk
				+ "</td></tr>"
				+ "<tr><td>No.Fail</td><td>:</td><td>"
				+ nofail
				+ "</td></tr>"
				+ "<tr><td>&nbsp;</td><td>&nbsp;</td></tr></table>"
				+ "<table><tr><td><em>Emel ini dijana oleh sistem MyeTaPP dan tidak perlu dibalas.</em><td></tr>"
				+ "</table></html>";
		email.RECIEPIENT = emelPengarah;

		myLog.info(" ---------- email :" + email);
		// email.MULTIPLE_RECIEPIENT[0] = "noorazmin.halim@gmail.com";
		email.TO_CC = new String[1];
		email.TO_CC[0] = "noorazmin8986@gmail.com";
		email.sendEmail();

	}

}
