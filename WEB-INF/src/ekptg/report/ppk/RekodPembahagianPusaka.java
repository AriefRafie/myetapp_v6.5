package ekptg.report.ppk;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;
import lebah.util.DateUtil;

import org.apache.velocity.Template;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8SenaraiHTATHInternalData;
import ekptg.model.ppk.FrmPrmhnnSek8SenaraiSemakInternalData;
import ekptg.model.ppk.FrmSenaraiFailInternalCarianData;
import ekptg.model.ppk.FrmSenaraiFailInternalData;

//betolkan sampai harta

public class RekodPembahagianPusaka extends VTemplate {

	private static final long serialVersionUID = 1L;
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	String currentDate = dateFormat.format(date);

	FrmPrmhnnSek8InternalData logic = new FrmPrmhnnSek8InternalData();
	FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
	FrmPrmhnnSek8SenaraiHTATHInternalData logic_B = new FrmPrmhnnSek8SenaraiHTATHInternalData();
	FrmPrmhnnSek8SenaraiSemakInternalData logic_C = new FrmPrmhnnSek8SenaraiSemakInternalData();
	FrmSenaraiFailInternalCarianData logic_D = new FrmSenaraiFailInternalCarianData();

	public Template doTemplate() throws Exception {
		String flagFromSenaraiFailSek8 = getParam("flagFromSenaraiFailSek8");
		// String flagFromSenaraiFailSek8 = "yes";
		System.out.println("** debug **");
		String name = "";
		String value = "";
		Enumeration allparam = request.getParameterNames();
		for (; allparam.hasMoreElements();) {
			name = (String) allparam.nextElement();
			value = request.getParameter(name);
			System.out.println(name + "=" + value);
		}
		System.out.println("** End debug **");

		String selectedTabatas = new String();
		String selectedTabtengah = new String();
		String selectedTabbawah = new String();
		String selectedTabtepi = new String();
		String selectLevelTab = new String();
		HttpSession session = this.request.getSession();
		String vm = "";

		String submit = getParam("command");
		String mode = getParam("mode");
		String idAlert = getParam("idAlert");

		int simpanStatus = 2;
		int eventStatus = 0;
		int backstatus = 1;

		String Carix = "";
		String disability1 = "";
		String disability2 = "";
		String readability1 = "";
		String readability2 = "";
		Vector listdaerah = null;
		Vector list = null;
		Vector listFail = null;
		Vector listPemohon = null;
		Vector listSimati = null;
		Vector listPeguam = null;
		Vector listKeputusan = null;
		Vector listWaris = null;
		Vector listWarisOB = null;
		Vector listHTA = null;
		Vector listHTAX = null;
		Vector listHTAid = null;
		Vector listHTAXid = null;
		Vector listWarisLapisanIdMati = null;
		Vector listWarisLapisan = null;
		Vector listWarisParent = null;
		Vector listWarisUpdate = null;
		Vector listHta = null;
		Vector listHtath = null;
		Vector listHa = null;
		Vector listPenting = null;
		Vector listPenghutang = null;
		Vector listPemiutang = null;
		Vector listSaksi = null;
		Vector listCheckPeguam = null;
		Vector listPenghutangbyIDOB = null;
		Vector listPentingbyIDOB = null;
		Vector listWarisLapisanIdMatiDelete = null;
		Vector listDaerahByUser = null;
		Vector chkId = null;
		Vector listUserid = null;
		Vector list1 = null;
		Vector listSemak = null;
		Vector listSemakSimati = null;
		Vector view1 = null;
		Vector list2 = null;
		Vector listIds = null;
		Vector view2 = null;
		Vector listpeguam = null;
		Vector listPemohonOB = null;
		Vector listKPSimati = null;
		Vector listpeguamX = null;
		Vector listWarisup = null;
		Vector listpenting = null;
		Vector listnegeri = null;
		Vector listpeguamcheck = null;
		Vector listSemak2 = null;
		Vector listnegeribydaerah = null;
		Vector listabc = null;
		Vector listppkha = null;
		Vector sumppkha = null;
		Vector listppkhta = null;
		Vector listppkha2 = null;
		Vector overallnilai = null;
		Vector overallnilaimati = null;
		Vector listppkhtath = null;
		Vector listmukim = null;
		Vector listJenisha = null;
		Vector selectedppkha = null;
		Vector sumppkhta = null;
		Vector sumoverallppkhta = null;
		Vector listxxx = null;
		Vector v = null;

		int flagno = 0;
		int idFlag = 0;
		int flag_no = 0;

		readability1 = "";
		this.context.put("SimpanStatus", "");
		this.context.put("mode1", "");
		this.context.put("flagno", "");
		this.context.put("idFlag", "");
		this.context.put("View", "");
		this.context.put("noFail", "");
		this.context.put("namapemohon", "");
		this.context.put("nokppemohon", "");
		this.context.put("carix", "");
		this.context.put("usid", "");
		this.context.put("Senaraifail", "");
		this.context.put("CountList", "");
		this.context.put("ListDaerahByUserX", "");
		this.context.put("GetNewPemohon", "");
		this.context.put("NegId", "");
		this.context.put("EventStatus", "");
		this.context.put("IdPermohonan", "");
		this.context.put("backStatus", "");
		this.context.put("ListSemakan", "");
		this.context.put("ListSemakanSimati", "");
		this.context.put("listkp", "");
		this.context.put("listkp2", "");
		this.context.put("tarikhmohon", "");
		this.context.put("EventStatus", "");
		this.context.put("idFlag", "");
		this.context.put("flagno", "");
		this.context.put("idAlert", "");
		this.context.put("IdPemohon", "");
		this.context.put("IdSimati", "");
		this.context.put("IdPemohon", "");
		this.context.put("tarikhmohondaftar", "");
		this.context.put("pendaftaran", "");
		this.context.put("duplicate", "");
		this.context.put("listKPSimati", "");
		this.context.put("tarikhmohon", "");
		this.context.put("readmode", "");
		this.context.put("show_kemaskini_button", "");
		this.context.put("show_simpan_button", "");
		this.context.put("show_batal_button", "");
		this.context.put("selectedTabatas", "");
		this.context.put("selectedTabtengah", "");
		this.context.put("selectedTabbawah", "");
		this.context.put("selectedTabtepi", "");
		this.context.put("show_kemaskini_button", "");
		this.context.put("show_simpan_button", "");
		this.context.put("show_batal_button", "");

		context.put("DATEUTIL", new DateUtil());
		this.context.put("Util", new lebah.util.Util());
		this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri"));

		String v_tab = getParam("v_tab");
		System.out.print("V_TAB" + v_tab + ";");

		this.context.put("val_tab", v_tab);

		if ("Cari".equals(submit)) {
			Carix = "1";
			this.context.put("carix", Carix);
			String noFail = getParam("txtNoFail");
			String namaPemohon = getParam("txtPemohon");
			String namaSimati = getParam("txtSimati");
			String icSimati = getParam("txtIcSimati");
			String JenisIc = getParam("jenisIc");
			String usid = (String) session.getAttribute("_ekptg_user_id");

			logic_D.setCarianFailRPP(usid, noFail, namaPemohon, namaSimati,
					icSimati, JenisIc);
			list1 = logic_D.getListRPP();
			listDaerahByUser = logic_A.getDaerahByNegeriUser((String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("ListDaerahByUserX", listDaerahByUser);
			int countList1 = list1.size();
			this.context.put("Senaraifail", list1);
			this.context.put("CountList", countList1);

			System.out.println("C:" + countList1 + "S:" + list1.size());

			prepareItemForDisplay(session, list1, 25, "first");

			vm = "app/ppk/frmSenaraiFailPusakaKecilRPP.jsp";
		} else if (("next".equals(submit)) || ("previous".equals(submit))) {
			// Carix = "1";
			this.context.put("carix", 1);

			// FrmSenaraiFailInternalData logic_E = new
			// FrmSenaraiFailInternalData();

			list = FrmSenaraiFailInternalData.setListRPP((String) session
					.getAttribute("_ekptg_user_id"));
			// this.context.put("Senaraifail",list);

			session.setAttribute("Senaraifail", list);
			list = (Vector) session.getAttribute("Senaraifail");
			this.context.put("Senaraifail", list);

			prepareItemForDisplay(session, list, 25, submit);
			int countList = list.size();
			this.context.put("CountList", countList);
			vm = "app/ppk/frmSenaraiFailPusakaKecilRPP.jsp";

		}

		else {

			String usid = "";
			usid = (String) session.getAttribute("_ekptg_user_id");
			this.context.put("usid", usid);
			System.out.println("JENIS USER" + usid);
			this.context.put("noFail", "");
			this.context.put("namapemohon", "");
			this.context.put("nokppemohon", "");
			Carix = "1";
			this.context.put("carix", Carix);
			list = FrmSenaraiFailInternalData.setListRPP((String) session
					.getAttribute("_ekptg_user_id"));
			int countList = list.size();
			this.context.put("Senaraifail", list);
			this.context.put("CountList", countList);
			prepareItemForDisplay(session, list, 25, "first");
			vm = "app/ppk/frmSenaraiFailPusakaKecilRPP.jsp";
		}

		this.context.put("DATEUTIL", new DateUtil());

		Vector count_dunia = logic_A.getNofaildunia(2, 1, 8);
		this.context.put("count_dunia", count_dunia);

		listnegeri = logic_A.getListnegeri();
		this.context.put("listnegeri", listnegeri);

		Vector listtaraf = logic_A.getListtaraf();
		this.context.put("listtaraf", listtaraf);

		Vector listsaudara = logic_A.getListsaudara();
		this.context.put("listsaudara", listsaudara);

		Vector listbuktimati = logic_A.getListbuktimati();
		this.context.put("listbuktimati", listbuktimati);

		listdaerah = logic_A.getListDaerah();
		this.context.put("listdaerah", listdaerah);

		Vector listluas = logic_A.getListLuas();
		this.context.put("listluas", listluas);

		String statuspemilik = "Y";
		Vector listpemilikan = logic_A.getListStatusPemilik(statuspemilik);
		this.context.put("listpemilikan", listpemilikan);

		Vector listtanah = logic_A.getListJenisTanah();
		this.context.put("listtanah", listtanah);

		listmukim = logic_A.getListMukim();
		this.context.put("listmukim", listmukim);

		String statushak = "Y";
		Vector listjenishakmilik = logic_A.getListJenisHakMilik(statushak);
		this.context.put("listJenisHakMilik", listjenishakmilik);

		Vector listkategori = logic_A.getListKategori();
		this.context.put("listkategori", listkategori);

		this.context.put("flagFromSenaraiFailSek8", flagFromSenaraiFailSek8);

		Template template = this.engine.getTemplate(vm);
		return template;

	}

	private void delete_semakan(HttpSession session, String idPermohonan)
			throws Exception {
		// FrmPrmhnnSek8SenaraiSemakInternalData frmonline = new
		// FrmPrmhnnSek8SenaraiSemakInternalData();
		logic_C.semakanDelete(idPermohonan);
	}

	private void updatePermohonan(HttpSession session) throws Exception {
		String idFail = getParam("idFail");
		String IdSimati = getParam("idSimati");
		String IdPemohon = getParam("idPemohon");
		String IdPermohonan = getParam("idPermohonan");
		String no_daerah = getParam("socDaerah");
		String tarikh_daftar = getParam("tarikh_daftar");
		String tarikh_masuk = getParam("txdTarikhMohon");
		String no_kpbaru_simati = getParam("txtNoKPBaruSimati1")
				+ getParam("txtNoKPBaruSimati2")
				+ getParam("txtNoKPBaruSimati3");
		String no_kplama_simati = getParam("txtNoKPLamaSimati");
		String sel_jeniskp_simati = getParam("socJenisKPLainSimati");
		String no_kplain_simati = getParam("txtNoKPLainSimati");
		String nama_simati = getParam("txtNamaSimati");
		String tarikh_simati = getParam("txtTarikhMati");
		String no_kpbaru_pemohon = getParam("txtNoKPBaruPemohon1")
				+ getParam("txtNoKPBaruPemohon2")
				+ getParam("txtNoKPBaruPemohon3");
		String no_kplama_pemohon = getParam("txtNoKPLamaPemohon");
		String sel_jeniskp_pemohon = getParam("socJenisKPLainPemohon");
		String no_kplain_pemohon = getParam("txtNoKPLainPemohon");
		String nama_pemohon = getParam("txtNamaPemohon");
		String alamat1 = getParam("txtAlamat1");
		String alamat2 = getParam("txtAlamat2");
		String alamat3 = getParam("txtAlamat3");
		String bandar = getParam("txtBandar");
		String poskod = getParam("txtPoskod");
		String negeri = getParam("socNegeri");

		String txtUmurSimati = getParam("txtUmurSimati");
		String socJantinaSimati = getParam("socJantinaSimati");
		String txtUmurPemohon = getParam("txtUmurPemohon");
		String socJantinaPemohon = getParam("socJantinaPemohon");

		// if()

		Hashtable h = null;
		h = new Hashtable();
		h.put("IdFail", idFail);
		h.put("IdSimati", IdSimati);
		h.put("IdPemohon", IdPemohon);
		h.put("IdPermohonan", IdPermohonan);
		h.put("no_daerah", no_daerah);
		h.put("tarikh_daftar", tarikh_daftar);
		h.put("tarikh_masuk", tarikh_masuk);
		h.put("no_kp_baru", no_kpbaru_simati);
		h.put("no_kplama_simati", no_kplama_simati);
		h.put("sel_jeniskp_simati", sel_jeniskp_simati);
		h.put("no_kplain_simati", no_kplain_simati);
		h.put("nama_simati", nama_simati);
		h.put("tarikh_simati", tarikh_simati);
		h.put("no_kpbaru_pemohon", no_kpbaru_pemohon);
		h.put("no_kplama_pemohon", no_kplama_pemohon);
		h.put("sel_jeniskp_pemohon", sel_jeniskp_pemohon);
		h.put("no_kplain_pemohon", no_kplain_pemohon);
		h.put("nama_pemohon", nama_pemohon);
		h.put("alamat1", alamat1);
		h.put("alamat2", alamat2);
		h.put("alamat3", alamat3);
		h.put("bandar", bandar);
		h.put("poskod", poskod);
		h.put("negeri", negeri);
		String usid = (String) session.getAttribute("_ekptg_user_id");
		h.put("id_Masuk", usid);
		h.put("txtUmurSimati", txtUmurSimati);
		h.put("socJantinaSimati", socJantinaSimati);
		h.put("txtUmurPemohon", txtUmurPemohon);
		h.put("socJantinaPemohon", socJantinaPemohon);

		logic_A.updatePermohonan(h);
	}

	private void updatesimati(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		Hashtable k = new Hashtable();
		Vector v = new Vector();

		h.put("id_Simati", Integer.parseInt(getParam("idSimati")));
		h.put("nama_Simati", getParam("txtNamaSimati"));
		h.put("nama_Lain", getParam("txtNamaLainSimati"));
		String nkpbaru = getParam("txtNoKPBaru1Simati")
				+ getParam("txtNoKPBaru2Simati")
				+ getParam("txtNoKPBaru3Simati");
		h.put("no_Kp_Baru", nkpbaru);
		h.put("no_Kp_Lama", getParam("txtNoKPLamaSimati"));
		h.put("jenis_Kp", getParam("socJenisKPLainSimati"));
		h.put("n0_Kp_Lain", getParam("txtNoKPLainSimati"));
		if (getParam("txtUmurSimati").equals("")) {
			h.put("umur", 0);
		} else {
			h.put("umur", Integer.parseInt(getParam("txtUmurSimati")));
		}
		h.put("jantina", getParam("socJantinaSimati"));
		h.put("no_Sijil_Mati", getParam("txtNoSijilMatiSimati"));
		h.put("tempat_Mati", getParam("txtTempatMatiSimati"));
		h.put("alamat_1", getParam("txtAlamatTerakhir1Simati"));
		h.put("alamat_2", getParam("txtAlamatTerakhir2Simati"));
		h.put("alamat_3", getParam("txtAlamatTerakhir3Simati"));
		h.put("bandar", getParam("txtBandarSimati"));
		h.put("poskod", getParam("txtPoskodSimati"));
		h.put("tarikh_Mati", getParam("txdTarikhMatiSimati"));
		h.put("waktu_Kematian", getParam(""));
		h.put("jenis_Waktu_Mati", getParam("socWaktuKematianSimati"));
		h.put("sebab_Mati", getParam("txtSebabKematianSimati"));
		h.put("catatan", getParam("txtCatatanSimati"));
		if (getParam("socNegeriSimati").equals("")) {
			h.put("id_Negeri", 0);
		} else {
			h.put("id_Negeri", Integer.parseInt(getParam("socNegeriSimati")));
		}
		h.put("id_Buktimati", getParam("socBuktiKematianSimati"));
		h.put("jenis_Agama", getParam("socAgamaSimati"));
		h.put("jenis_Warga", getParam("socWarganegaraSimati"));
		h.put("tarikh_Kkini", getParam(""));
		h.put("id_Permohonan", getParam("idPermohonan"));
		String usid = (String) session.getAttribute("_ekptg_user_id");
		h.put("id_Masuk", usid);
		h.put("id_Kemaskini", getParam(""));
		h.put("tarikh_Kemaskini", getParam(""));
		h.put("id_Db", getParam(""));
		logic.updatesimati(h);
	}

	private void updatesimatisemak(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		Hashtable k = new Hashtable();
		Vector v = new Vector();

		h.put("id_Simati", Integer.parseInt(getParam("id_Simati")));
		h.put("no_Sijil_Mati", getParam("sijilmati"));
		h.put("id_Buktimati", getParam("buktimati"));

		String usid = (String) session.getAttribute("_ekptg_user_id");
		h.put("id_Masuk", usid);
		h.put("id_Kemaskini", getParam(""));
		h.put("tarikh_Kemaskini", getParam(""));
		h.put("id_Db", getParam(""));

		logic.updatesimatisemak(h);
	}

	private void updatepemohon(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		Hashtable k = new Hashtable();

		String nkpbarupemohon = getParam("txtnoKpBaru1Pemohon")
				+ getParam("txtnoKpBaru2Pemohon")
				+ getParam("txtnoKpBaru3Pemohon");

		k.put("adataraf", getParam("adataraf"));
		System.out.println("XXX###" + getParam("adataraf"));
		k.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
		k.put("idPemohon", Integer.parseInt(getParam("idPemohon")));
		k.put("idSimati", Integer.parseInt(getParam("idSimati")));
		// k.put("idSimati", Integer.parseInt(getParam("idSimati")));
		k.put("noKpBaru", nkpbarupemohon);
		// k.put("noKpLama", getParam("txtNoKPLamaSimati"));
		k.put("jenisKp", getParam("socJenisKPLainPemohon"));
		k.put("noKpLain", getParam("txtNoKPLainPemohon"));
		k.put("no_Kp_Lama", getParam("txtNoKPLamaPemohon"));
		k.put("namaPemohon", getParam("txtNamaPemohonPemohon"));

		// k.put("taraf", getParam("socTarafKepentinganPemohon"));
		k.put("saudara", getParam("socTalianPersaudaraanPemohon"));
		// k.put("tarikhMatidb",getParam("txdTarikhMatiSimati"));
		k.put("jantina", getParam("socJantinaPemohon"));
		k.put("jenisWarga", getParam("socWarganegaraPemohon"));
		k.put("jenisAgama", getParam("socAgamaPemohon"));

		int tr = 0;
		if (getParam("socTarafKePemohonanPemohon") == "") {
			k.put("taraf", tr);
		}
		if (getParam("socTarafKePemohonanPemohon") != "") {
			k.put("taraf", Integer
					.parseInt(getParam("socTarafKePemohonanPemohon")));
		}

		if (getParam("txtUmurPemohon") != "") {
			k.put("umur", Integer.parseInt(getParam("txtUmurPemohon")));
		}
		int umu = 0;
		if (getParam("txtUmurPemohon") == "") {
			k.put("umur", umu);
		}

		k.put("nofaks", getParam("txtNoFaksPemohon"));
		k.put("notelefon", getParam("txtNoTelefonPemohon"));
		k.put("notelefonbimbit", getParam("txtNoTelefonBimbitPemohon"));
		k.put("emel", getParam("txtEmelPemohon"));
		// k.put("jeniswaktumati", getParam("socWaktuKematianSimati"));
		// k.put("sebabMati", getParam("txtSebabKematianSimati"));
		k.put("alamat1", getParam("txtAlamatTerakhir1Pemohon"));
		k.put("alamat2", getParam("txtAlamatTerakhir2Pemohon"));
		k.put("alamat3", getParam("txtAlamatTerakhir3Pemohon"));
		k.put("poskod", getParam("txtPoskodPemohon"));
		k.put("bandar", getParam("txtBandarPemohon"));

		int ne = 0;
		if (getParam("socNegeriPemohon") == "") {
			k.put("idnegeri", ne);
		}
		if (getParam("socNegeriPemohon") != "") {
			k.put("idnegeri", Integer.parseInt(getParam("socNegeriPemohon")));
		}

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String currentDate = dateFormat.format(date);

		k.put("catatan", getParam("txtCatatanPemohon"));
		k.put("id_Permohonansimati", Integer
				.parseInt(getParam("id_Permohonansimati")));

		k.put("status_Peguam", "");
		k.put("status_Pemohon", "");
		k.put("id_Masuk", "");

		k.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));
		k.put("tarikh_Kemaskini", currentDate);
		k.put("id_Db", "");

		k.put("namanegeri", getParam("socNegeriPemohon"));// nama negeri

		k.put("txdTarikhLahirPemohon", getParam("txdTarikhLahirPemohon"));

		k.put("alamat1Surat", getParam("txtAlamatTerakhir1PemohonSurat"));
		k.put("alamat2Surat", getParam("txtAlamatTerakhir2PemohonSurat"));
		k.put("alamat3Surat", getParam("txtAlamatTerakhir3PemohonSurat"));
		k.put("poskodSurat", getParam("txtPoskodPemohonSurat"));
		k.put("bandarSurat", getParam("txtBandarPemohonSurat"));

		int nes = 0;
		if (getParam("socNegeriPemohonSurat") == "") {
			k.put("idnegeriSurat", nes);
		}
		if (getParam("socNegeriPemohonSurat") != "") {
			k.put("idnegeriSurat", Integer
					.parseInt(getParam("socNegeriPemohonSurat")));
		}

		logic.updatepemohon(k);

	}

	private void tambahpeguam(HttpSession session) throws Exception {
		Hashtable k = new Hashtable();
		k.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
		k.put("idPemohon", Integer.parseInt(getParam("idPemohon")));
		k.put("firma", getParam("txtNamaFirmaPeguam2"));
		k.put("rujfirma", getParam("txtNoRujukanFirma2"));
		k.put("alamat1", getParam("txtAlamat1Peguam2"));
		k.put("alamat2", getParam("txtAlamat2Peguam2"));
		k.put("alamat3", getParam("txtAlamat3Peguam2"));
		k.put("poskod", getParam("txtPoskodPeguam2"));
		k.put("bandar", getParam("txtBandarPeguam2"));
		k.put("idnegeri", getParamAsInteger("socNegeriPeguam2"));
		k.put("noTel", getParam("txtNomborTelefonPeguam2"));
		k.put("noFax", getParam("txtNomborFaksPeguam2"));
		k.put("emel", getParam("txtEmelPeguam2"));
		k.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		logic.tambahpeguam(k);
		Vector list = new Vector();

	}

	private void updatepeguam(HttpSession session) throws Exception {

		Hashtable k = new Hashtable();

		k.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
		k.put("idPeguam", Integer.parseInt(getParam("idPeguam")));
		k.put("idPemohon", Integer.parseInt(getParam("idPemohon")));
		k.put("firma", getParam("txtNamaFirmaPeguam"));
		k.put("rujfirma", getParam("txtNoRujukanFirma"));
		k.put("alamat1", getParam("txtAlamat1Peguam"));
		k.put("alamat2", getParam("txtAlamat2Peguam"));
		k.put("alamat3", getParam("txtAlamat3Peguam"));
		k.put("poskod", getParam("txtPoskodPeguam"));
		k.put("bandar", getParam("txtBandarPeguam"));
		k.put("idnegeri", getParam("socNegeriPeguam"));
		k.put("noTel", getParam("txtNomborTelefonPeguam"));
		k.put("noFax", getParam("txtNomborFaksPeguam"));
		k.put("emel", getParam("txtEmelPeguam"));
		k.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));

		logic.updatepeguam(k);
		Vector list = new Vector();

	}

	private void addWaris(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();

		h.put("idSimati", Integer.parseInt(getParam("txtIdSimatiWaris")));
		h.put("namaOB", getParam("txtNamaOBWaris"));

		String kp_Waris = getParam("txtNoKPBaru1Waris")
				+ getParam("txtNoKPBaru2Waris") + getParam("txtNoKPBaru3Waris");

		h.put("nokpbaru", kp_Waris);

		h.put("nokpwaris", getParam("txtNoKPLamaWaris"));
		h.put("notel", getParam("txtNoTeleponWaris"));
		h.put("hp", getParam("txtNoTeleponBimbitWaris"));
		h.put("jenisKp", getParam("socJenisKPLainWaris"));
		h.put("nokplain", getParam("txtNoKPLainWaris"));

		// h.put("taraf",
		// Integer.parseInt(getParam("socTarafKepentinganPenting")));
		h.put("jantina", getParam("socJantinaWaris"));
		h.put("agama", getParam("socAgamaWaris"));
		h.put("warga", getParam("socWarganegaraWaris"));

		h.put("tarikhlahir", getParam("txdTarikhLahirWaris"));
		h.put("noberanak", getParam("txtNoSuratBeranakWaris"));
		// h.put("umur", Integer.parseInt(getParam("txtUmurWaris")));

		h.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
		h.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
		h.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
		h.put("poskod", getParam("txtPoskodWaris"));
		h.put("bandar", getParam("txtBandarWaris"));

		if (getParam("socNegeriWaris").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriWaris")));
		}

		h.put("alamat1Surat", getParam("txtAlamatTerakhir1WarisSurat"));
		h.put("alamat2Surat", getParam("txtAlamatTerakhir2WarisSurat"));
		h.put("alamat3Surat", getParam("txtAlamatTerakhir3WarisSurat"));
		h.put("poskodSurat", getParam("txtPoskodWarisSurat"));
		h.put("bandarSurat", getParam("txtBandarWarisSurat"));

		if (getParam("socNegeriWarisSurat").equals("")) {
			h.put("negeriSurat", 0);
		} else {
			h.put("negeriSurat", Integer
					.parseInt(getParam("socNegeriWarisSurat")));
		}

		// h.put("negeri", Integer.parseInt(getParam("socNegeriPenting")));
		h.put("catatan", getParam("txtCatatanWaris"));

		if (getParam("socSaudaraWaris").equals("")) {
			h.put("saudara", 0);
		} else {
			h.put("saudara", Integer.parseInt(getParam("socSaudaraWaris")));
		}

		if (getParam("socStatusOBWaris").equals("")) {
			h.put("statusWaris", 0);
		} else {
			h
					.put("statusWaris", Integer
							.parseInt(getParam("socStatusOBWaris")));
		}

		if (getParam("checkHidupWaris").equals("")) {
			h.put("checkmati", "0");
		} else {
			h.put("checkmati", "1");
		}

		if (getParam("txtUmurWaris").equals("")) {
			h.put("umur", 0);
		} else {
			h.put("umur", Integer.parseInt(getParam("txtUmurWaris")));
		}

		h.put("tarikhmati", getParam("txdTarikhMatiWaris"));
		h.put("waktumatiwaris", getParam("txtWaktuKematianWaris"));
		h.put("hp", getParam("txtNoTeleponBimbitWaris"));

		h.put("id_Permohonansimati", Integer
				.parseInt(getParam("id_Permohonansimati")));

		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);

		logic.addWaris(h);
	}

	private void addWarisBerikut(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();

		h.put("idSimati", Integer.parseInt(getParam("txtIdSimatiWaris")));
		h.put("namaOB", getParam("txtNamaOBWaris"));

		long idOB_gabung = DB.getNextID("TBLPPKOB_SEQ");

		String kp_Waris = getParam("txtNoKPBaru1Waris")
				+ getParam("txtNoKPBaru2Waris") + getParam("txtNoKPBaru3Waris");
		h.put("idOB_gabung", idOB_gabung);
		h.put("nokpbaru", kp_Waris);

		h.put("nokpwaris", getParam("txtNoKPLamaWaris"));
		h.put("notel", getParam("txtNoTeleponWaris"));
		h.put("hp", getParam("txtNoTeleponBimbitWaris"));
		h.put("jenisKp", getParam("socJenisKPLainWaris"));
		h.put("nokplain", getParam("txtNoKPLainWaris"));

		h.put("idparent", Integer.parseInt(getParam("txtIdParent")));

		if (getParam("txtLapisParent").equals("")) {
			h.put("lapis", 0);
		} else {
			h.put("lapis", Integer.parseInt(getParam("txtLapisParent")));
		}

		// h.put("taraf",
		// Integer.parseInt(getParam("socTarafKepentinganPenting")));
		h.put("jantina", getParam("socJantinaWaris"));
		h.put("agama", getParam("socAgamaWaris"));
		h.put("warga", getParam("socWarganegaraWaris"));

		h.put("tarikhlahir", getParam("txdTarikhLahirWaris"));
		h.put("noberanak", getParam("txtNoSuratBeranakWaris"));
		// h.put("umur", Integer.parseInt(getParam("txtUmurWaris")));

		h.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
		h.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
		h.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
		h.put("poskod", getParam("txtPoskodWaris"));
		h.put("bandar", getParam("txtBandarWaris"));

		if (getParam("socNegeriWaris").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriWaris")));
		}

		h.put("alamat1Surat", getParam("txtAlamatTerakhir1WarisSurat"));
		h.put("alamat2Surat", getParam("txtAlamatTerakhir2WarisSurat"));
		h.put("alamat3Surat", getParam("txtAlamatTerakhir3WarisSurat"));
		h.put("poskodSurat", getParam("txtPoskodWarisSurat"));
		h.put("bandarSurat", getParam("txtBandarWarisSurat"));

		if (getParam("socNegeriWarisSurat").equals("")) {
			h.put("negeriSurat", 0);
		} else {
			h.put("negeriSurat", Integer
					.parseInt(getParam("socNegeriWarisSurat")));
		}

		// h.put("negeri", Integer.parseInt(getParam("socNegeriPenting")));
		h.put("catatan", getParam("txtCatatanWaris"));

		if (getParam("socSaudaraWaris").equals("")) {
			h.put("saudara", 0);
		} else {
			h.put("saudara", Integer.parseInt(getParam("socSaudaraWaris")));
		}

		if (getParam("socStatusOBWaris").equals("")) {
			h.put("statusWaris", 0);
		} else {
			h
					.put("statusWaris", Integer
							.parseInt(getParam("socStatusOBWaris")));
		}

		if (getParam("checkHidupWaris").equals("")) {
			h.put("checkmati", "0");
		} else {
			h.put("checkmati", "1");
		}

		if (getParam("txtUmurWaris").equals("")) {
			h.put("umur", 0);
		} else {
			h.put("umur", Integer.parseInt(getParam("txtUmurWaris")));
		}

		h.put("tarikhmati", getParam("txdTarikhMatiWaris"));
		h.put("waktumatiwaris", getParam("txtWaktuKematianWaris"));

		h.put("hp", getParam("txtNoTeleponBimbitWaris"));

		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);

		h.put("id_Permohonansimati", Integer
				.parseInt(getParam("id_Permohonansimati")));

		logic.addWarisBerikut(h);
	}

	private void addWarisHubungan(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		h.put("idParent", Integer.parseInt(getParam("txtIdParent")));
		h.put("idOb", Integer.parseInt(getParam("txtIdParent")));
		h.put("saudara", Integer.parseInt(getParam("socSaudaraWaris")));

		logic.addWarisBerikut(h);
	}

	private void updatewaris(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();
		h.put("idwaris", Integer.parseInt(getParam("idwarisup")));
		h.put("idSimati", Integer.parseInt(getParam("txtIdSimatiWaris")));
		h.put("namaOB", getParam("txtNamaOBWaris"));

		String kp_Waris = getParam("txtNoKPBaru1Waris")
				+ getParam("txtNoKPBaru2Waris") + getParam("txtNoKPBaru3Waris");

		h.put("nokpbaru", kp_Waris);
		h.put("id_Pemohon", getParam("id_Pemohon"));

		h.put("nokpwaris", getParam("txtNoKPLamaWaris"));
		h.put("notel", getParam("txtNoTeleponWaris"));
		h.put("hp", getParam("txtNoTeleponBimbitWaris"));
		h.put("jenisKp", getParam("socJenisKPLainWaris"));
		h.put("nokplain", getParam("txtNoKPLainWaris"));

		// h.put("taraf",
		// Integer.parseInt(getParam("socTarafKepentinganPenting")));
		h.put("jantina", getParam("socJantinaWaris"));
		h.put("agama", getParam("socAgamaWaris"));
		h.put("warga", getParam("socWarganegaraWaris"));

		h.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
		h.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
		h.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
		h.put("poskod", getParam("txtPoskodWaris"));
		h.put("bandar", getParam("txtBandarWaris"));

		if (getParam("socNegeriWaris").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriWaris")));
		}

		h.put("alamat1Surat", getParam("txtAlamatTerakhir1WarisSurat"));
		h.put("alamat2Surat", getParam("txtAlamatTerakhir2WarisSurat"));
		h.put("alamat3Surat", getParam("txtAlamatTerakhir3WarisSurat"));
		h.put("poskodSurat", getParam("txtPoskodWarisSurat"));
		h.put("bandarSurat", getParam("txtBandarWarisSurat"));

		if (getParam("socNegeriWarisSurat").equals("")) {
			h.put("negeriSurat", 0);
		} else {
			h.put("negeriSurat", Integer
					.parseInt(getParam("socNegeriWarisSurat")));
		}

		// h.put("negeri", Integer.parseInt(getParam("socNegeriPenting")));
		h.put("catatan", getParam("txtCatatanWaris"));

		if (getParam("socSaudaraWaris").equals("")) {
			h.put("saudara", 0);
		} else {
			h.put("saudara", Integer.parseInt(getParam("socSaudaraWaris")));
		}

		if (getParam("socStatusOBWaris").equals("")) {
			h.put("statusWaris", 0);
		} else {
			h
					.put("statusWaris", Integer
							.parseInt(getParam("socStatusOBWaris")));
		}

		if (getParam("checkHidupWaris").equals("")) {
			h.put("checkmati", "0");
		} else {
			h.put("checkmati", "1");
		}

		h.put("tarikhlahir", getParam("txdTarikhLahirWaris"));
		h.put("noberanak", getParam("txtNoSuratBeranakWaris"));
		// h.put("umur", Integer.parseInt(getParam("txtUmurWaris")));

		if (getParam("txtUmurWaris").equals("")) {
			h.put("umur", 0);
		} else {
			h.put("umur", Integer.parseInt(getParam("txtUmurWaris")));
		}

		h.put("tarikhmati", getParam("txdTarikhMatiWaris"));
		h.put("waktumatiwaris", getParam("txtWaktuKematianWaris"));

		h.put("hp", getParam("txtNoTeleponBimbitWaris"));

		h.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Kemaskini", currentDate);

		logic.updateWaris(h);

	}

	private void addPenting(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();

		h.put("idSimati", Integer.parseInt(getParam("idSimati")));
		h.put("id_Permohonansimati", Integer
				.parseInt(getParam("id_Permohonansimati")));
		h.put("namaOB", getParam("txtNamaOBPenting"));
		// int id_Permohonansimati =
		// Integer.parseInt(getParam("id_Permohonansimati"));

		String kp_penting = getParam("txtNoKPBaru1Penting")
				+ getParam("txtNoKPBaru2Penting")
				+ getParam("txtNoKPBaru3Penting");

		h.put("nokpbaru", kp_penting);
		h.put("nokppenting", getParam("txtNoKPLamaPenting"));
		h.put("jenisKp", getParam("socJenisKPLainPenting"));
		h.put("nokplain", getParam("txtNoKPLainPenting"));
		h.put("statusOB", getParam("socStatusOB"));

		if (getParam("socTarafKepentinganPenting").equals("")) {
			int a = 0;
			h.put("taraf", a);
		} else {
			h.put("taraf", Integer
					.parseInt(getParam("socTarafKepentinganPenting")));
		}

		// h.put("taraf",
		// Integer.parseInt(getParam("socTarafKepentinganPenting")));
		h.put("jantina", getParam("socJantinaPenting"));
		h.put("agama", getParam("socAgamaPenting"));
		h.put("warga", getParam("socWarganegaraPenting"));

		if (getParam("txtUmurPenting").equals("")) {
			h.put("umur", 0);
		} else {
			h.put("umur", Integer.parseInt(getParam("txtUmurPenting")));
		}

		h.put("dob", getParam("txdTarikhLahirPenting"));
		h.put("noberanak", getParam("txtNoSuratBeranakPenting"));
		h.put("alamat1", getParam("txtAlamatTerakhir1Penting"));
		h.put("alamat2", getParam("txtAlamatTerakhir2Penting"));
		h.put("alamat3", getParam("txtAlamatTerakhir3Penting"));
		h.put("poskod", getParam("txtPoskodPenting"));
		h.put("bandar", getParam("txtBandarPenting"));

		if (getParam("socNegeriPenting").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriPenting")));
		}

		// h.put("negeri", Integer.parseInt(getParam("socNegeriPenting")));
		h.put("catatan", getParam("txtCatatanPenting"));

		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);

		h.put("alamat1Surat", getParam("txtAlamatTerakhir1WarisSurat"));
		h.put("alamat2Surat", getParam("txtAlamatTerakhir2WarisSurat"));
		h.put("alamat3Surat", getParam("txtAlamatTerakhir3WarisSurat"));
		h.put("poskodSurat", getParam("txtPoskodWarisSurat"));
		h.put("bandarSurat", getParam("txtBandarWarisSurat"));

		if (getParam("socNegeriWarisSurat").equals("")) {
			h.put("negeriSurat", 0);
		} else {
			h.put("negeriSurat", Integer
					.parseInt(getParam("socNegeriWarisSurat")));
		}

		logic.addPenting(h);
	}

	private void updatepenting(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		h.put("idSimati", Integer.parseInt(getParam("txtIdSimatiPenting")));
		h.put("jenishutang", getParam("socJenisHutangPentingU"));
		h.put("id_Pemohon", getParam("id_Pemohon"));
		h.put("idob", Integer.parseInt(getParam("txtIdOb")));
		h.put("namaOB", getParam("txtNamaOBPentingU"));
		String kp_penting = getParam("txtNoKPBaru1PentingU")
				+ getParam("txtNoKPBaru2PentingU")
				+ getParam("txtNoKPBaru3PentingU");
		h.put("nokpbaru", kp_penting);
		h.put("nokppenting", getParam("txtNoKPLamaPentingU"));
		h.put("jenisKp", getParam("socJenisKPLainPentingU"));
		h.put("nokplain", getParam("txtNoKPLainPentingU"));
		h.put("statusOB", getParam("socStatusOBU"));

		if (getParam("socTarafKepentinganPentingU").equals("")) {
			int a = 0;
			h.put("taraf", a);
		} else {
			h.put("taraf", Integer
					.parseInt(getParam("socTarafKepentinganPentingU")));
		}

		h.put("jantina", getParam("socJantinaPentingU"));
		h.put("agama", getParam("socAgamaPentingU"));
		h.put("warga", getParam("socWarganegaraPentingU"));
		if (getParam("txtUmurPentingU").equals("")) {
			h.put("umur", 0);
		} else {
			h.put("umur", Integer.parseInt(getParam("txtUmurPentingU")));
		}
		h.put("dob", getParam("txdTarikhLahirPentingU"));
		h.put("noberanak", getParam("txtNoSuratBeranakPentingU"));
		h.put("alamat1", getParam("txtAlamatTerakhir1PentingU"));
		h.put("alamat2", getParam("txtAlamatTerakhir2PentingU"));
		h.put("alamat3", getParam("txtAlamatTerakhir3PentingU"));
		h.put("poskod", getParam("txtPoskodPentingU"));
		h.put("bandar", getParam("txtBandarPentingU"));
		h.put("notel", getParam("txtNoTeleponPentingU"));
		if (getParam("socNegeriPentingU").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriPentingU")));
		}
		h.put("catatan", getParam("txtCatatanPentingU"));

		if (getParam("txtNilaiHutangPentingU").equals("")) {
			h.put("nilaihutang", 0.0);
		} else {
			h.put("nilaihutang", Double
					.parseDouble(getParam("txtNilaiHutangPentingU")));
		}

		// h.put("nilaihutang", getParam("txtNilaiHutangPentingU"));
		h.put("noakaun", getParam("txtNoAkaunPentingU"));
		h.put("butiranhutang", getParam("txtButiranHutangPentingU"));

		h.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Kemaskini", currentDate);

		h.put("alamat1Surat", getParam("txtAlamatTerakhir1WarisSurat"));
		h.put("alamat2Surat", getParam("txtAlamatTerakhir2WarisSurat"));
		h.put("alamat3Surat", getParam("txtAlamatTerakhir3WarisSurat"));
		h.put("poskodSurat", getParam("txtPoskodWarisSurat"));
		h.put("bandarSurat", getParam("txtBandarWarisSurat"));

		if (getParam("socNegeriWarisSurat").equals("")) {
			h.put("negeriSurat", 0);
		} else {
			h.put("negeriSurat", Integer
					.parseInt(getParam("socNegeriWarisSurat")));
		}

		logic.updatePenting(h);

	}

	private void addSaksi(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();

		h.put("idSimati", Integer.parseInt(getParam("idSimati")));
		h.put("id_Permohonansimati", Integer
				.parseInt(getParam("id_Permohonansimati")));

		// int id_Permohonansimati =
		// Integer.parseInt(getParam("id_Permohonansimati"));
		h.put("namaOB", getParam("txtNamaOBPenting"));
		String kp_penting = getParam("txtNoKPBaru1Penting")
				+ getParam("txtNoKPBaru2Penting")
				+ getParam("txtNoKPBaru3Penting");
		h.put("nokpbaru", kp_penting);
		h.put("nokppenting", getParam("txtNoKPLamaPenting"));
		h.put("jenisKp", getParam("socJenisKPLainPenting"));
		h.put("nokplain", getParam("txtNoKPLainPenting"));

		h.put("jantina", getParam("socJantinaPenting"));
		h.put("warga", getParam("socWarganegaraPenting"));
		h.put("alamat1", getParam("txtAlamatTerakhir1Penting"));
		h.put("alamat2", getParam("txtAlamatTerakhir2Penting"));
		h.put("alamat3", getParam("txtAlamatTerakhir3Penting"));
		h.put("poskod", getParam("txtPoskodPenting"));
		h.put("bandar", getParam("txtBandarPenting"));
		h.put("catatan", getParam("txtCatatanPenting"));
		h.put("notel", getParam("txtNoTeleponPenting"));
		if (getParam("socNegeriPenting").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriPenting")));

		}
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);
		logic.addSaksi(h);

	}

	private void addPemiutang(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		// id_Permohonansimati
		h.put("idSimati", Integer.parseInt(getParam("idSimati")));
		h.put("id_Permohonansimati", Integer
				.parseInt(getParam("id_Permohonansimati")));
		h.put("namaOB", getParam("txtNamaOBPenting"));
		String kp_penting = getParam("txtNoKPBaru1Penting")
				+ getParam("txtNoKPBaru2Penting")
				+ getParam("txtNoKPBaru3Penting");
		h.put("nokpbaru", kp_penting);
		h.put("nokppenting", getParam("txtNoKPLamaPenting"));
		h.put("jenisKp", getParam("socJenisKPLainPenting"));
		h.put("nokplain", getParam("txtNoKPLainPenting"));

		h.put("alamat1", getParam("txtAlamatTerakhir1Penting"));
		h.put("alamat2", getParam("txtAlamatTerakhir2Penting"));
		h.put("alamat3", getParam("txtAlamatTerakhir3Penting"));
		h.put("poskod", getParam("txtPoskodPenting"));
		h.put("bandar", getParam("txtBandarPenting"));

		if (getParam("socNegeriPenting").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriPenting")));
		}

		if (getParam("txtNilaiHutangPenting").equals("")) {
			h.put("nilaihutang", 0.0);
		} else {
			h.put("nilaihutang", Double
					.parseDouble(getParam("txtNilaiHutangPenting")));
		}
		h.put("jenishutang", getParam("socJenisHutangPenting"));
		// h.put("nilaihutang", getParam("txtNilaiHutangPentingU"));
		h.put("noakaun", getParam("txtNoAkaunPenting"));
		h.put("butiranhutang", getParam("txtButiranHutangPenting"));
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);

		logic.addPemiutang(h);

	}

	private void addPenghutang(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		// int id_Permohonansimati =
		// Integer.parseInt(getParam("id_Permohonansimati"));
		h.put("id_Permohonansimati", Integer
				.parseInt(getParam("id_Permohonansimati")));
		h.put("idSimati", Integer.parseInt(getParam("idSimati")));
		h.put("namaOB", getParam("txtNamaOBPenting"));
		String kp_penting = getParam("txtNoKPBaru1Penting")
				+ getParam("txtNoKPBaru2Penting")
				+ getParam("txtNoKPBaru3Penting");
		h.put("nokpbaru", kp_penting);
		h.put("nokppenting", getParam("txtNoKPLamaPenting"));
		h.put("jenisKp", getParam("socJenisKPLainPenting"));
		h.put("nokplain", getParam("txtNoKPLainPenting"));
		h.put("alamat1", getParam("txtAlamatTerakhir1Penting"));
		h.put("alamat2", getParam("txtAlamatTerakhir2Penting"));
		h.put("alamat3", getParam("txtAlamatTerakhir3Penting"));
		h.put("poskod", getParam("txtPoskodPenting"));
		h.put("bandar", getParam("txtBandarPenting"));

		if (getParam("socNegeriPenting").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriPenting")));
		}

		if (getParam("txtNilaiHutangPenting").equals("")) {
			h.put("nilaihutang", 0.0);
		} else {
			h.put("nilaihutang", Double
					.parseDouble(getParam("txtNilaiHutangPenting")));
		}

		h.put("noakaun", getParam("txtNoAkaunPenting"));
		h.put("butiranhutang", getParam("txtButiranHutangPenting"));
		h.put("jenishutang", getParam("socJenisHutangPenting"));

		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);

		logic.addPenghutang(h);

	}

	private void updatepenghutang(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		h.put("idSimati", Integer.parseInt(getParam("txtIdSimatiPenting")));
		h.put("idob", Integer.parseInt(getParam("txtIdOb")));
		h.put("jenishutang", getParam("socJenisHutangPentingU"));
		h.put("namaOB", getParam("txtNamaOBPentingU"));
		String kp_penting = getParam("txtNoKPBaru1PentingU")
				+ getParam("txtNoKPBaru2PentingU")
				+ getParam("txtNoKPBaru3PentingU");
		h.put("nokpbaru", kp_penting);
		h.put("nokppenting", getParam("txtNoKPLamaPentingU"));
		h.put("jenisKp", getParam("socJenisKPLainPentingU"));
		h.put("nokplain", getParam("txtNoKPLainPentingU"));
		h.put("alamat1", getParam("txtAlamatTerakhir1PentingU"));
		h.put("alamat2", getParam("txtAlamatTerakhir2PentingU"));
		h.put("alamat3", getParam("txtAlamatTerakhir3PentingU"));
		h.put("poskod", getParam("txtPoskodPentingU"));
		h.put("bandar", getParam("txtBandarPentingU"));

		if (getParam("socNegeriPentingU").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriPentingU")));
		}
		h.put("catatan", getParam("txtCatatanPentingU"));

		if (getParam("txtNilaiHutangPentingU").equals("")) {
			h.put("nilaihutang", 0.0);
		} else {
			h.put("nilaihutang", Double
					.parseDouble(getParam("txtNilaiHutangPentingU")));
		}

		// h.put("nilaihutang", getParam("txtNilaiHutangPentingU"));
		h.put("noakaun", getParam("txtNoAkaunPentingU"));
		h.put("butiranhutang", getParam("txtButiranHutangPentingU"));

		h.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Kemaskini", currentDate);

		logic.updatePenghutang(h);

	}

	public void addHtaam(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		h.put("noHakmilik", getParam("txtNoHakmilikHtaam"));
		h.put("idSimati", Integer.parseInt(getParam("idSimati")));
		h.put("id_Permohonansimati", Integer
				.parseInt(getParam("id_Permohonansimati")));
		// int mati = Integer.parseInt(getParam("id_Permohonansimati"));
		h.put("nopt", getParam("txtNoPTHtaam"));

		if (getParam("txtNilaiTarikhMohonHtaa") != "") {
			h.put("nilai_Hta_memohon", Double
					.parseDouble(getParam("txtNilaiTarikhMohonHtaa")));
		} else {
			h.put("nilai_Hta_memohon", 0.0);
		}

		if (getParam("txtNilaiTarikhMatiHtaam") != "") {
			h.put("nilai_Hta_mati", Double
					.parseDouble(getParam("txtNilaiTarikhMatiHtaam")));
		} else {
			h.put("nilai_Hta_mati", 0.0);
		}

		if (getParam("socKategoriTanahHtaam") != "") {
			h.put("kategori", Integer
					.parseInt(getParam("socKategoriTanahHtaam")));
		} else {
			h.put("kategori", 0);
		}

		if (getParam("socJenisHakmilikHtaam") != "") {
			h.put("jenishakmilik", Integer
					.parseInt(getParam("socJenisHakmilikHtaam")));
		} else {
			h.put("jenishakmilik", 0);
		}

		h.put("pemilikan", getParam("socStatusPemilikanHtaam"));

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

		h.put("luashmp", getParam("txtLuasHMpHtaam"));
		h.put("luasasal", getParam("txtLuasAsalHtaam"));
		h.put("nopajakan", getParam("txtNoPajakan"));
		h.put("jenistanah", getParam("socJenisTanahHtaam"));

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

		if (getParam("socJenisLuasHtaam") != "") {
			h.put("jenisluas", Integer.parseInt(getParam("socJenisLuasHtaam")));
		} else {
			h.put("jenisluas", 0);
		}

		h.put("tanggungan", getParam("txtTanggunganHtaam"));

		h.put("catatan", getParam("txtCatatanHtaam"));
		h.put("noperserahan", getParam("txtNoPersHtaam"));
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);

		// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
		h.put("sekatan", getParam("txtSekatan") == null ? ""
				: getParam("txtSekatan"));
		h.put("syaratNyata", getParam("txtSyaratNyata") == null ? ""
				: getParam("txtSyaratNyata"));

		logic.addHtaam(h);

	}

	private void updateHtaam(HttpSession session) throws Exception {

		Vector v = new Vector();
		Hashtable h = new Hashtable();

		h.put("idhta", Integer.parseInt(getParam("id_htaam")));

		h.put("noHakmilik", getParam("txtNoHakmilikHtaamUp"));
		h.put("idSimati", Integer.parseInt(getParam("idSimati")));
		h.put("nopt", getParam("txtNoPTHtaamUp"));

		if (getParam("txtNilaiTarikhMohonHt") != "") {
			h.put("nilai_Hta_memohon", Double
					.parseDouble(getParam("txtNilaiTarikhMohonHt")));
		} else {
			h.put("nilai_Hta_memohon", 0.0);
		}

		if (getParam("txtNilaiTarikhMatiHtaamUpd") != "") {
			h.put("nilai_Hta_mati", Double
					.parseDouble(getParam("txtNilaiTarikhMatiHtaamUpd")));
		} else {
			h.put("nilai_Hta_mati", 0.0);
		}

		if (getParam("socKategoriTanahHtaamUp") != "") {
			h.put("kategori", Integer
					.parseInt(getParam("socKategoriTanahHtaamUp")));
		} else {
			h.put("kategori", 0);
		}

		if (getParam("socJenisHakmilikHtaamUp") != "") {
			h.put("jenishakmilik", Integer
					.parseInt(getParam("socJenisHakmilikHtaamUp")));
		} else {
			h.put("jenishakmilik", 0);
		}

		h.put("pemilikan", getParam("socStatusPemilikanHtaamUpd"));

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

		h.put("luashmp", getParam("txtLuasHMpHtaamUpd"));
		h.put("luasasal", getParam("txtLuasAsalHtaamUpd"));
		h.put("nopajakan", getParam("txtNoPajakanUp"));
		h.put("jenistanah", getParam("socJenisTanahHtaamUpd"));

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

		if (getParam("socJenisLuasHtaamUpd") != "") {
			h.put("jenisluas", Integer
					.parseInt(getParam("socJenisLuasHtaamUpd")));
		} else {
			h.put("jenisluas", 0);
		}

		h.put("tanggungan", getParam("txtTanggunganHtaamUp"));

		h.put("catatan", getParam("txtCatatanHt"));
		h.put("noperserahan", getParam("txtNoPersHtaamUp"));

		h.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Kemaskini", currentDate);

		// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
		h.put("sekatan", getParam("txtSekatan") == null ? ""
				: getParam("txtSekatan"));
		h.put("syaratNyata", getParam("txtSyaratNyata") == null ? ""
				: getParam("txtSyaratNyata"));

		logic.updateHtaam(h);

	}

	public void addHtaamX(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		// h.put("noHakmilik", getParam("txtNoHakmilikHtaam"));
		h.put("idSimati", Integer.parseInt(getParam("idSimati")));
		h.put("id_Permohonansimati", Integer
				.parseInt(getParam("id_Permohonansimati")));
		// int mati = Integer.parseInt(getParam("id_Permohonansimati"));
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
		h.put("bandarpemaju", getParam("txtBandarPemaju1HtaamX"));

		if (getParam("socNegeriPemajuHtaamX").equals("")) {
			h.put("negeripemaju", 0);
		} else {
			h.put("negeripemaju", Integer
					.parseInt(getParam("socNegeriPemajuHtaamX")));
		}

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

		h.put("jeniskepentingan", getParam("txtJenisKepentinganX"));

		h.put("nolot", getParam("txtLotIdHtaamX"));

		h.put("nocagaran", getParam("txtNoCagaranX"));

		String[] radioHtaamViewX = this.request
				.getParameterValues("radioHtaamViewX");
		int n = 0;
		for (int i = 0; i < radioHtaamViewX.length; i++) {
			if (radioHtaamViewX[i].equals("1")) {
				n = 1;
			} else if (radioHtaamViewX[i].equals("2")) {
				n = 2;
			} else if (radioHtaamViewX[i].equals("3")) {
				n = 3;
			}
		}

		// String flag="";
		if (n == 1) {
			h.put("flag", "1");
		}
		if (n == 2) {
			h.put("flag", "2");
		}
		if (n == 3) {
			h.put("flag", "3");
		}
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);

		logic.addHtaamX(h);

	}

	private void updateHtaamX(HttpSession session) throws Exception {

		Vector v = new Vector();
		Hashtable h = new Hashtable();

		// h.put("idhta", Integer.parseInt(getParam("idhtaamXid")));
		h.put("idhta", Integer.parseInt(getParam("idhtaamid")));
		// int idhtaam=Integer.parseInt(getParam("idhtaamid"));

		h.put("idSimati", Integer.parseInt(getParam("idSimati")));
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
		h.put("bandarpemaju", getParam("txtBandarPemaju1HtaamX"));
		if (getParam("socNegeriPemajuHtaamX").equals("")) {
			h.put("negeripemaju", 0);
		} else {
			h.put("negeripemaju", Integer
					.parseInt(getParam("socNegeriPemajuHtaamX")));
		}

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

		h.put("nocagaran", getParam("txtNoCagaranX"));

		h.put("jeniskepentingan", getParam("txtJenisKepentinganX"));

		String flag1 = getParam("radioHtaamViewX1");
		String flag2 = getParam("radioHtaamViewX2");
		String flag3 = getParam("radioHtaamViewX3");
		String flag = "";
		if (flag1 != "") {
			h.put("flag", "1");
		}
		if (flag2 != "") {
			h.put("flag", "2");
		}
		if (flag3 != "") {
			h.put("flag", "3");
		}

		h.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Kemaskini", currentDate);
		logic.updateHtaamX(h);

	}

	private void addHa(HttpSession session) throws Exception {
		String id = getParam("id");
		String id1 = getParam("idSimati");
		String socJenisHartaAlih = getParam("socJenisHartaAlih");
		String txtBhgnSimati1 = getParam("txtBhgnSimati1");
		String txtBhgnSimati2 = getParam("txtBhgnSimati2");
		String txtNoRujukan = getParam("txtNoRujukan");
		String txtNilaiTarikhMati = getParam("txtNilaiTarikhMati");
		String txtNoSijil = getParam("txtNoSijil");
		String txtNilaiTarikhMohon = getParam("txtNilaiTarikhMohon");
		String txtBilUnit = getParam("txtBilUnit");
		String txtNilaiSeunit = getParam("txtNilaiSeunit");
		String txtAgensi = getParam("txtAgensi");
		String txtCatatan = getParam("txtCatatan");
		String socNegeriHtaam = getParam("socNegeriHtaam");
		String socDaerahHtaam = getParam("socDaerahHtaam");
		String bil = getParam("bil");
		int mati = Integer.parseInt(getParam("id_Permohonansimati"));

		String txtAlamat1 = getParam("txtAlamat1");
		String txtAlamat2 = getParam("txtAlamat2");
		String txtAlamat3 = getParam("txtAlamat3");
		String txtPoskod = getParam("txtPoskod");

		Hashtable h = null;
		h = new Hashtable();
		h.put("id", id);
		h.put("id1", id1);
		h.put("id_Permohonansimati", mati);
		h.put("socJenisHartaAlih", socJenisHartaAlih);
		h.put("txtBhgnSimati1", txtBhgnSimati1);
		h.put("txtBhgnSimati2", txtBhgnSimati2);
		h.put("txtNoRujukan", txtNoRujukan);
		h.put("txtNilaiTarikhMati", txtNilaiTarikhMati);
		h.put("txtNoSijil", txtNoSijil);
		h.put("txtNilaiTarikhMohon", txtNilaiTarikhMohon);
		h.put("txtBilUnit", txtBilUnit);
		h.put("txtNilaiSeunit", txtNilaiSeunit);
		h.put("txtAgensi", txtAgensi);
		h.put("txtCatatan", txtCatatan);
		h.put("bil", bil);

		h.put("txtAlamat1", txtAlamat1);
		h.put("txtAlamat2", txtAlamat2);
		h.put("txtAlamat3", txtAlamat3);
		h.put("txtPoskod", txtPoskod);

		h.put("socNegeriHtaam", socNegeriHtaam);
		h.put("socDaerahHtaam", socDaerahHtaam);
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));

		logic_A.addHa(h);
	}

	private void updateHa(HttpSession session) throws Exception {
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
		String txtAlamat1 = getParam("txtAlamat1");
		String txtAlamat2 = getParam("txtAlamat2");
		String txtAlamat3 = getParam("txtAlamat3");
		String txtPoskod = getParam("txtPoskod");

		String socDaerahHtaam = getParam("socDaerahHtaam");
		String socNegeriHtaam = getParam("socNegeriHtaam");

		Hashtable h = null;
		h = new Hashtable();
		h.put("id3", id3);
		h.put("id1", id1);
		h.put("socJenisHartaAlih", socJenisHartaAlih);
		h.put("txtBhgnSimati1", txtBhgnSimati1);
		h.put("txtBhgnSimati2", txtBhgnSimati2);
		h.put("txtNoRujukan", txtNoRujukan);
		h.put("txtNilaiTarikhMati", txtNilaiTarikhMati);
		h.put("txtNoSijil", txtNoSijil);
		h.put("txtNilaiTarikhMohon", txtNilaiTarikhMohon);
		h.put("txtBilUnit", txtBilUnit);
		h.put("txtNilaiSeunit", txtNilaiSeunit);
		h.put("Agensi", Agensi);
		h.put("txtCatatan", txtCatatan);
		h.put("bil", bil);
		h.put("txtAlamat1", txtAlamat1);
		h.put("txtAlamat2", txtAlamat2);
		h.put("txtAlamat3", txtAlamat3);
		h.put("txtPoskod", txtPoskod);
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));

		if (socNegeriHtaam != "" && socNegeriHtaam != "0") {
			h.put("socDaerahHtaam", socDaerahHtaam);
		} else {
			h.put("socDaerahHtaam", "0");
		}

		h.put("socNegeriHtaam", socNegeriHtaam);
		logic_A.kemaskiniHa(h);
	}

	private void view_mode(HttpSession session) throws Exception {
		String id = getParam("idtemp");
		logic_A.setData(id, (String) session.getAttribute("_ekptg_user_id"));
	}

	private void view_mode_ppkha(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		logic_A.setDataHa(id1);
	}

	private void view_sum_ppkha(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		logic_A.setDataHa(id1);
	}

	private void view_overallsum_ppkha(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		logic_A.setOverallSum(id1);
	}

	private void view_overallsummati_ppkha(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		logic_A.setOverallSumMati(id1);
	}

	private void view_mode_ppkhta(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		FrmPrmhnnSek8SenaraiHTATHInternalData frmPrmhnnSek8SenaraiHTATHInternalData = new FrmPrmhnnSek8SenaraiHTATHInternalData();
		frmPrmhnnSek8SenaraiHTATHInternalData.setDataHta(id1);
	}

	private void view_mode_ppkhtath(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		FrmPrmhnnSek8SenaraiHTATHInternalData frmPrmhnnSek8SenaraiHTATHInternalData = new FrmPrmhnnSek8SenaraiHTATHInternalData();
		frmPrmhnnSek8SenaraiHTATHInternalData.setDataHtath(id1);
	}

	private void view_mode_ppkha2(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		FrmPrmhnnSek8SenaraiHTATHInternalData frmPrmhnnSek8SenaraiHTATHInternalData = new FrmPrmhnnSek8SenaraiHTATHInternalData();
		frmPrmhnnSek8SenaraiHTATHInternalData.setDataHa(id1);
	}

	private void delete_mode_ppkha(HttpSession session, String id1, String id3)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		logic_A.deleteDataHa(id1, id3);
	}

	private void view_data_ppkha(HttpSession session, String id1, String id3)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		logic_A.setSelectedDataHa(id1, id3);
	}

	private void sum_nilai_ppkpermohonan(HttpSession session, String id,
			String id1) throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		logic_A.updateDataNilai(id, id1, (String) session
				.getAttribute("_ekptg_user_id"));
	}

	private void view_mode_pemohon(HttpSession session) throws Exception {
		String id = getParam("idPermohonan");
		logic_A.setData(id, (String) session.getAttribute("_ekptg_user_id"));
	}

	private void check_mode_permohonan(HttpSession session) throws Exception {
		String id = getParam("idPermohonan");
		logic_A.checkData(id);
	}

	private void view_get_id(HttpSession session) throws Exception {
		String id = getParam("idPermohonan");
		logic_A.setGetId(id);
	}

	private void view_get_userid(HttpSession session) throws Exception {
		String useridx = (String) session.getAttribute("_ekptg_user_id");
		logic_A.setGetUserId(useridx);
	}

	/*
	 * private void ha_negeri(HttpSession session) throws Exception { int id =
	 * Integer.parseInt(getParam("idPermohonan")); int id2 =
	 * Integer.parseInt(getParam("idPemohon")); int id1 =
	 * Integer.parseInt(getParam("idSimati")); int mati =
	 * Integer.parseInt(getParam("id_Permohonansimati"));
	 * 
	 * String eventstatus = getParam("eventStatus");
	 * 
	 * this.context.put("id", id); this.context.put("id1", id1);
	 * 
	 * 
	 * 
	 * 
	 * Vector listnegeri = logic_A.getListnegeri();
	 * this.context.put("listnegeri", listnegeri);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * this.context.put("readmodenegeri", "");
	 * this.context.put("readmodedaerah", "");
	 * 
	 * int idnegeri = Integer.parseInt(getParam("socNegeriHtaam")); Vector
	 * listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
	 * this.context.put("listDaerahbyNegeri", listnegeribydaerah);
	 * 
	 * Vector listabc = new Vector(); Hashtable h;
	 * 
	 * 
	 * h = new Hashtable();
	 * 
	 * 
	 * 
	 * 
	 * 
	 * h.put("negeri", idnegeri); h.put("daerah", ""); // h.put("id_Jenisha",
	 * getParam("socJenisHartaAlih")); h.put("socJenisHa",
	 * getParam("socJenisHa")); h.put("norujukan", getParam("txtNoRujukan"));
	 * h.put("nosijil", getParam("txtNoSijil")); h.put("bilunit",
	 * getParam("txtBilUnit")); h.put("nilaiseunit",
	 * getParam("txtNilaiSeunit")); h.put("agensi", getParam("txtAgensi"));
	 * h.put("alamat1", getParam("txtAlamat1")); h.put("alamat2",
	 * getParam("txtAlamat2")); h.put("alamat3", getParam("txtAlamat3"));
	 * h.put("poskod", getParam("txtPoskod")); h.put("negeri",
	 * getParam("socNegeriHtaam")); h.put("daerah", getParam("socDaerahHtaam"));
	 * h.put("bhgnmati1", getParam("txtBhgnSimati1")); h.put("bhgnmati2",
	 * getParam("txtBhgnSimati2")); h.put("nilaitarikhmati",
	 * getParam("txtNilaiTarikhMati")); h.put("nilaitarikhmohon",
	 * getParam("txtNilaiTarikhMohon")); h.put("catatan",
	 * getParam("txtCatatan"));
	 * 
	 * listabc.addElement(h);
	 * 
	 * 
	 * this.context.put("DataHa", listabc);
	 * 
	 * 
	 * this.context.put("tutup", "yes"); }
	 */
	public void updateStatus(HttpSession session) throws Exception {

		Vector v = new Vector();
		Hashtable h = new Hashtable();
		h.put("idPermohonan", getParam("idPermohonan"));
		h.put("idstatus", getParam("idstatus"));
		h.put("id_Fail", getParam("id_Fail"));

		h.put("userId", (String) session.getAttribute("_ekptg_user_id"));
		h.put("id_Suburusanstatus", getParam("id_Suburusanstatus"));
		h.put("id_Suburusanstatusfail", getParam("id_Suburusanstatusfail"));

		// logic.updateStatus(h);

	}

	private void prepareItemForDisplay(HttpSession session, Vector objVector,
			int cntItemPage, String command) {
		int x;
		int startno = 0;
		if (command == null)
			command = "first";
		if (session.getAttribute("_portal_startnoInternalFail") != null)
			startno = ((Integer) session
					.getAttribute("_portal_startnoInternalFail")).intValue();
		if (command.equals("previous"))
			if (startno == objVector.size()) {
				x = startno % cntItemPage;
				if (x > 0) {
					startno -= x;
					startno -= cntItemPage;
				} else {
					startno -= cntItemPage * 2;
					if (startno < 0)
						startno = 0;
				}
			} else {
				startno -= cntItemPage * 2;
				if (startno < 0)
					startno = 0;
			}
		else if (command.equals("first"))
			startno = 0;

		else if (command.equals("last"))
			x = cntItemPage;
		else if (command.equals("back"))
			if (startno == objVector.size()) {
				x = startno % cntItemPage;
				if (x == 0)
					x = cntItemPage;
				startno -= x;
			} else {
				startno -= cntItemPage;
				if (startno < 0)
					startno = 0;

			}

		Vector moduleVector = new Vector();
		int i = 0;
		int cnt = 0;
		if (objVector.size() > 0) {
			cnt = 0;
			for (i = startno; (cnt < cntItemPage) && (i < objVector.size());) {
				moduleVector.addElement(objVector.elementAt(i));

				++i;
				++cnt;
			}

		}

		session.setAttribute("_portal_moduleVectorInternalFail", moduleVector);

		this.context.put("startnoInt", new Integer(startno));
		this.context.put("totalInt", new Integer(objVector.size()));

		startno = i;

		session.setAttribute("_portal_startnoInternalFail",
				new Integer(startno));

	}

}
