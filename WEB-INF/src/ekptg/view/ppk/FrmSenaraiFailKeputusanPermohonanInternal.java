/**
 * 
 */
package ekptg.view.ppk;


import java.sql.Connection; 
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;    
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.velocity.VTemplate;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8KeputusanPermohonanInternalData;
import ekptg.model.ppk.FrmPrmhnnSek8KptsanBicaraData;
import ekptg.model.ppk.FrmPrmhnnSek8SenaraiHTATHInternalData;
import ekptg.model.ppk.FrmPrmhnnSek8SenaraiSemakInternalData;
import ekptg.model.ppk.FrmSenaraiFailInternalCarianData;
import ekptg.model.ppk.FrmSenaraiFailInternalData;
import ekptg.model.ppk.FrmSenaraiFailKeputusanPermohonanInternalData;

public class FrmSenaraiFailKeputusanPermohonanInternal extends VTemplate {
	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(PendaftaranCheck.class);
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	String currentDate = dateFormat.format(date);
	
	FrmPrmhnnSek8KeputusanPermohonanInternalData logic_internal = null;
	FrmPrmhnnSek8KeputusanPermohonanInternalData logicKeputusanPrmhnn = null;
	FrmSenaraiFailKeputusanPermohonanInternalData logicKeputusan = null;
	FrmPrmhnnSek8InternalData logic = null;
	FrmPrmhnnSek8DaftarSek8InternalData logic_A = null;
	FrmPrmhnnSek8SenaraiHTATHInternalData logic_B = null;
	FrmPrmhnnSek8SenaraiSemakInternalData logic_C = null;
	FrmSenaraiFailInternalCarianData logic_D = null;
	FrmSenaraiFailInternalData logic_E = null;
	FrmPrmhnnSek8KeputusanPermohonanInternalData logic_F = null;
	FrmHeaderPpk mainheader = null;
	
	

	@Override
	@SuppressWarnings("unchecked")
	public Template doTemplate() throws Exception {
		
		logic_internal = new FrmPrmhnnSek8KeputusanPermohonanInternalData();
		logicKeputusanPrmhnn = new FrmPrmhnnSek8KeputusanPermohonanInternalData();
		logicKeputusan = new FrmSenaraiFailKeputusanPermohonanInternalData();
		logic = new FrmPrmhnnSek8InternalData();
		logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
		logic_B = new FrmPrmhnnSek8SenaraiHTATHInternalData();
		logic_C = new FrmPrmhnnSek8SenaraiSemakInternalData();
		logic_D = new FrmSenaraiFailInternalCarianData();
		logic_E = new FrmSenaraiFailInternalData();
		logic_F = new FrmPrmhnnSek8KeputusanPermohonanInternalData();
		mainheader = new FrmHeaderPpk();
		Vector flag5juta =  new Vector(); //arief add 5 juta
		this.context.put("seksyen_kp", "8");
		Vector listSupportingDoc = null;
		
		HttpSession session = this.request.getSession();
		String submit = "";
		submit = getParam("command");
		String jenisDoc = "99205";
		myLogger.info(":::::::::::::::::::::::::::::" + submit);

		String bolehsimpan = "";
		String doPost = "true";//(String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			// System.out.println("browser refresh **********");
			bolehsimpan = "yes";
			System.out.println("bolehsimpan = "+bolehsimpan);
			// do what ever approriate action
		}

		headerppk_baru_default();

		myLogger.info("submit:" + submit);
		String vm = "app/ppk/frmSenaraiFailKeputusanPermohonan.jsp";
		// String vm = "app/ppk/tabpage.jsp";

		String disability1 = "";
		String disability2 = "";
		String readability1 = "";
		String readability2 = "";

		String flagFromSenaraiFailSek8 = getParam("flagFromSenaraiFailSek8");
		String flagFromSenaraiPermohonanSek8 = getParam("flagFromSenaraiPermohonanSek8");
		String flagForView = getParam("flagForView");

		Vector listKeputusan = new Vector();
		Vector listFail = new Vector();

		listFail.clear();

		listKeputusan.clear();

		this.context.put("eve", "");
		this.context.put("Util", new lebah.util.Util());
		this.context.put("pilih_negeri_ptd", "");

		FrmSenaraiFailKeputusanPermohonanInternalData.setListDaerahall();
		Vector listDaerahall = FrmSenaraiFailKeputusanPermohonanInternalData
				.getListDaerahall();
		this.context.put("ListDaerah", listDaerahall);
		/*
		 * FrmPrmhnnSek8KeputusanPermohonanInternalData.setMaklumatMahkamahM();
		 * Vector listMaklumatMahkamahMK =
		 * FrmPrmhnnSek8KeputusanPermohonanInternalData.getMaklumatMahkamahM();
		 * this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahMK);
		 */
		// System.out.println("submit="+submit);

		String v_tab = getParam("v_tab");
		System.out.print("V_TAB" + v_tab + ";");

		this.context.put("val_tab", v_tab);

		String v_loc = getParam("v_loc");
		System.out.print("V_LOC" + v_loc + ";");

		this.context.put("val_loc", v_loc);

		this.context.put("ListNegeriPindah", "");
		this.context.put("ListNegeriPindah", "");
		this.context.put("listMaklumatMahkamahJPindah", "");

		this.context.put("daerahpindah", "");

		Vector listC = new Vector();
		// FrmSenaraiFailKeputusanPermohonanInternalData
		// .setIntMahkamah_BrgC(id_per);
		listC = FrmSenaraiFailKeputusanPermohonanInternalData
				.getIntMahkamah_BrgC();
		System.out.println("SIZE LIST C: " + listC.size());
		this.context.put("listC", listC);

		// System.out.println("Apa command Submit kt cnie : " + submit);

		if (getParam("idPermohonan") != "") {
			Vector list_status_nilai = new Vector();
			String id_per = getParam("idPermohonan");
			FrmSenaraiFailKeputusanPermohonanInternalData.setlist_status_nilai(
					id_per, "342");
			list_status_nilai = FrmSenaraiFailKeputusanPermohonanInternalData
					.getlist_status_nilai();
			this.context.put("list_status_nilai", list_status_nilai);

			Vector list_status_tunggu = new Vector();
			// String id_per = getParam("idPermohonan");
			FrmSenaraiFailKeputusanPermohonanInternalData
					.setlist_status_tunggu(id_per, "553");
			list_status_tunggu = FrmSenaraiFailKeputusanPermohonanInternalData
					.getlist_status_tunggu();
			this.context.put("list_status_tunggu", list_status_tunggu);

			Vector listB = new Vector();
			FrmSenaraiFailKeputusanPermohonanInternalData
					.setIntMahkamah(id_per);
			listB = FrmSenaraiFailKeputusanPermohonanInternalData
					.getIntMahkamah();
			// System.out.println("SIZE LIST B: " + listB.size());
			this.context.put("listB", listB);

			// Vector listC = new Vector();
			// FrmSenaraiFailKeputusanPermohonanInternalData
			// .setIntMahkamah_BrgC(id_per);
			// listC = FrmSenaraiFailKeputusanPermohonanInternalData
			// .getIntMahkamah_BrgC();
			// System.out.println("SIZE LIST C: " + listC.size());
			// this.context.put("listC", listC);
		}
		
		FrmPrmhnnSek8KeputusanPermohonanInternalData.checkFlag5Juta(id);
		flag5juta = FrmPrmhnnSek8KeputusanPermohonanInternalData.getFlag5Juta();
		this.context.put("flag5juta", flag5juta);
		
		if ("paparKeputusan".equals(submit)) {
			System.out.println("~~~~~~~~ paparKeputusan ~~~~~~~~~~~~~~~~~~~");
			String idPermohonan = getParam("idPermohonan");
			logic_A.setSupportingDoc(idPermohonan, jenisDoc);
			listSupportingDoc = logic_A.setSupportingDoc(idPermohonan, jenisDoc);
			this.context.put("ViewSupportingDoc", listSupportingDoc);
			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);
			System.out.println("idpermohonansimati = "+getParam("idpermohonansimati"));
			Vector listHartaARB = new Vector();
			listHartaARB.clear();
			Vector listKaveatPeguam = new Vector();
			listKaveatPeguam.clear();
			
			System.out.println("Read here1 ");
			//check sama ada dah isi atau belum harta yang diselesaikan oleh ARB
			logic_F.setCheckHartaDiselesaikanARB(getParam("idpermohonansimati"));
			System.out.println("Read here2 ");
			logic_F.setListKaveatPeguam(getParam("idpermohonansimati"));
			listKaveatPeguam = logic_F.getListKaveatPeguam();
			this.context.put("listKaveatPeguam", listKaveatPeguam);
			listHartaARB = logic_F.getCheckHartaDiselesaikanARB();
			int countListARB = listHartaARB.size();
			System.out.println("countListARB = "+countListARB);
			this.context.put("flagUntukEdit", "");
			this.context.put("nowb", "");
			if (countListARB > 0)
			{
				this.context.put("ViewHartaARB", "1");}
				else
				{	this.context.put("ViewHartaARB", "0");
			}
			//System.out.println(listHartaARB);
			//this.context.put("ViewHartaARB", listHartaARB);

			String t_mohon = getParam("tarikh_mohon");

			if (!t_mohon.equals("")) {
				String september_mohon = "01/09/2009";
				// /System.out.println("[check]1: " +t_mohon );

				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date tar_mohon = df.parse(t_mohon);
				Date sep_mohon = df.parse(september_mohon);

				if (tar_mohon.before(sep_mohon)) {
					// System.out.print(" is before ");
					view_mode(session);
					int eventStatus = 1;
					this.context.put("EventStatus", eventStatus);
					String id = getParam("idPermohonan");
					FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
							(String) session.getAttribute("_ekptg_user_id"));
					headerppk_baru(session, id, "Y", "", "T");

					Vector list = FrmSenaraiFailKeputusanPermohonanInternalData
							.getData();
					this.context.put("ViewPemohon", list);

					// Vector listDaerahByPpkUnit =
					// FrmPrmhnnSek8KeputusanPermohonanInternalData.getListDaerahByPpkUnit(Integer.parseInt(h.get("negeritertinggi").toString()));
					this.context.put("ListDaerahPindah",
							logic_A.getListDaerahPindah());

					Hashtable h1 = (Hashtable) list.get(0);
					if (h1.get("socNegeriPeguam").toString() != ""
							&& h1.get("idnegeri").toString() != "0") {
						Vector s3 = logic_A
								.getListBandarByNegeri(Integer.parseInt(h1.get(
										"socNegeriPeguam").toString()));
						this.context.put("listBandarTetapbyNegeri", s3);
					} else {
						this.context.put("listBandarTetapbyNegeri", "");
					}

					logic_A.setDataFail(id);
					listFail = logic_A.getDataFail();
					this.context.put("ViewFail", listFail);

					FrmPrmhnnSek8KeputusanPermohonanInternalData
							.setMaklumatMahkamah(id);
					Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
							.getMaklumatMahkamah();
					// System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
					this.context.put("listMaklumatMahkamah",
							listMaklumatMahkamah);

					FrmPrmhnnSek8KeputusanPermohonanInternalData
							.setMaklumatMahkamahM();
					Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData
							.getMaklumatMahkamahM();
					this.context.put("listMaklumatMahkamahJ",
							listMaklumatMahkamahM);

					this.context.put("eve", "");

					this.context.put("setMode1", "");
					this.context.put("setMode2", "");
					this.context.put("setMode3", "");
					this.context.put("setMode4", "");
					this.context.put("setMode5", "");
					this.context.put("setMode6", "");

					this.context.put("checkJ1", "");
					this.context.put("checkJ2", "");
					this.context.put("checkJ3", "");
					this.context.put("checkJ4", "");

					this.context.put("viewcheckJ1", "");
					this.context.put("viewcheckJ2", "");
					this.context.put("viewcheckJ3", "");
					this.context.put("viewcheckJ4", "");

					this.context.put("jenis_pej", "");
					this.context.put("jenis_pej_id", "");

					this.context.put("mahkamah_kosong", "yes");
					this.context.put("infoMahkamah", "");

					FrmPrmhnnSek8KeputusanPermohonanInternalData
							.setMaklumatMahkamahMPindah();
					Vector listMaklumatMahkamahMKPindah = FrmPrmhnnSek8KeputusanPermohonanInternalData
							.getMaklumatMahkamahMPindah();
					this.context.put("listMaklumatMahkamahJPindah",
							listMaklumatMahkamahMKPindah);

					Hashtable h2 = (Hashtable) list.get(0);
					if (h2.get("socNegeriPeguam").toString() != ""
							&& h2.get("idnegeri").toString() != "0") {
						Vector s3 = logic_A
								.getListBandarByNegeri(Integer.parseInt(h2.get(
										"socNegeriPeguam").toString()));
						this.context.put("listBandarTetapbyNegeri", s3);
					} else {
						this.context.put("listBandarTetapbyNegeri", "");
					}

					vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama.jsp";
				}
				if (tar_mohon.after(sep_mohon) || tar_mohon.equals(sep_mohon)) {
					// System.out.print(" is after ");
					view_mode(session);
					int eventStatus = 1;
					this.context.put("EventStatus", eventStatus);
					String id = getParam("idPermohonan");
					FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
							(String) session.getAttribute("_ekptg_user_id"));
					headerppk_baru(session, id, "Y", "", "T");
					Vector list = FrmSenaraiFailKeputusanPermohonanInternalData
							.getData();
					this.context.put("ViewPemohon", list);
					logic_A.setDataFail(id);
					listFail = logic_A.getDataFail();
					this.context.put("ViewFail", listFail);

					FrmPrmhnnSek8KeputusanPermohonanInternalData
							.setMaklumatMahkamah(id);
					Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
							.getMaklumatMahkamah();
					// System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
					this.context.put("listMaklumatMahkamah",
							listMaklumatMahkamah);

					FrmPrmhnnSek8KeputusanPermohonanInternalData
							.setMaklumatMahkamahM();
					Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData
							.getMaklumatMahkamahM();
					this.context.put("listMaklumatMahkamahJ",
							listMaklumatMahkamahM);

					this.context.put("eve", "");

					this.context.put("setMode1", "");
					this.context.put("setMode2", "");
					this.context.put("setMode3", "");
					this.context.put("setMode4", "");
					this.context.put("setMode5", "");
					this.context.put("setMode6", "");

					this.context.put("checkJ1", "");
					this.context.put("checkJ2", "");
					this.context.put("checkJ3", "");
					this.context.put("checkJ4", "");

					this.context.put("viewcheckJ1", "");
					this.context.put("viewcheckJ2", "");
					this.context.put("viewcheckJ3", "");
					this.context.put("viewcheckJ4", "");

					this.context.put("jenis_pej", "");
					this.context.put("jenis_pej_id", "");

					this.context.put("mahkamah_kosong", "yes");
					this.context.put("infoMahkamah", "");

					Hashtable h3 = (Hashtable) list.get(0);
					if (h3.get("socNegeriPeguam").toString() != ""
							&& h3.get("idnegeri").toString() != "0") {
						Vector s3 = logic_A
								.getListBandarByNegeri(Integer.parseInt(h3.get(
										"socNegeriPeguam").toString()));
						this.context.put("listBandarTetapbyNegeri", s3);
					} else {
						this.context.put("listBandarTetapbyNegeri", "");
					}

					// headerppk_baru(session,id,"Y","","T");
					vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan.jsp";
				}

				// System.out.println("TARIKH MOHON:"+tar_mohon);
			} else {
				// System.out.print(" is before ");
				view_mode(session);
				int eventStatus = 1;
				this.context.put("EventStatus", eventStatus);
				String id = getParam("idPermohonan");
				FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
						(String) session.getAttribute("_ekptg_user_id"));
				headerppk_baru(session, id, "Y", "", "T");
				Vector list = FrmSenaraiFailKeputusanPermohonanInternalData
						.getData();
				this.context.put("ViewPemohon", list);

				// Vector listDaerahByPpkUnit =
				// FrmPrmhnnSek8KeputusanPermohonanInternalData.getListDaerahByPpkUnit(Integer.parseInt(h.get("negeritertinggi").toString()));
				this.context.put("ListDaerahPindah",
						logic_A.getListDaerahPindah());

				logic_A.setDataFail(id);
				listFail = logic_A.getDataFail();
				this.context.put("ViewFail", listFail);

				FrmPrmhnnSek8KeputusanPermohonanInternalData
						.setMaklumatMahkamah(id);
				Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
						.getMaklumatMahkamah();
				// System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
				this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);

				FrmPrmhnnSek8KeputusanPermohonanInternalData
						.setMaklumatMahkamahM();
				Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData
						.getMaklumatMahkamahM();
				this.context
						.put("listMaklumatMahkamahJ", listMaklumatMahkamahM);

				this.context.put("eve", "");

				this.context.put("setMode1", "");
				this.context.put("setMode2", "");
				this.context.put("setMode3", "");
				this.context.put("setMode4", "");
				this.context.put("setMode5", "");
				this.context.put("setMode6", "");

				this.context.put("checkJ1", "");
				this.context.put("checkJ2", "");
				this.context.put("checkJ3", "");
				this.context.put("checkJ4", "");

				this.context.put("viewcheckJ1", "");
				this.context.put("viewcheckJ2", "");
				this.context.put("viewcheckJ3", "");
				this.context.put("viewcheckJ4", "");

				this.context.put("jenis_pej", "");
				this.context.put("jenis_pej_id", "");

				this.context.put("mahkamah_kosong", "yes");
				this.context.put("infoMahkamah", "");

				FrmPrmhnnSek8KeputusanPermohonanInternalData
						.setMaklumatMahkamahMPindah();
				Vector listMaklumatMahkamahMKPindah = FrmPrmhnnSek8KeputusanPermohonanInternalData
						.getMaklumatMahkamahMPindah();
				this.context.put("listMaklumatMahkamahJPindah",
						listMaklumatMahkamahMKPindah);

				Hashtable h4 = (Hashtable) list.get(0);
				if (h4.get("socNegeriPeguam").toString() != ""
						&& h4.get("idnegeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h4.get("socNegeriPeguam").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				headerppk_baru(session, id, "Y", "", "T");
				vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama.jsp";

			}

		}
		
		else if ("deleteSuppDocMode".equals(submit)) {
			System.out.println("~~~~~~~~ deleteSuppDocMode ~~~~~~~~~~~~~~~~~~~");
			String idSimati = getParam("idSimati");
			deleteSuppDoc(idSimati, jenisDoc);
			String idPermohonan = getParam("idPermohonan");
			logic_A.setSupportingDoc(idPermohonan, jenisDoc);
			listSupportingDoc = logic_A.setSupportingDoc(idPermohonan, jenisDoc);
			this.context.put("ViewSupportingDoc", listSupportingDoc);
			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);
			System.out.println("idpermohonansimati = "+getParam("idpermohonansimati"));
			Vector listHartaARB = new Vector();
			listHartaARB.clear();
			Vector listKaveatPeguam = new Vector();
			listKaveatPeguam.clear();
			
			System.out.println("Read here1 ");
			//check sama ada dah isi atau belum harta yang diselesaikan oleh ARB
			logic_F.setCheckHartaDiselesaikanARB(getParam("idpermohonansimati"));
			System.out.println("Read here2 ");
			logic_F.setListKaveatPeguam(getParam("idpermohonansimati"));
			listKaveatPeguam = logic_F.getListKaveatPeguam();
			this.context.put("listKaveatPeguam", listKaveatPeguam);
			listHartaARB = logic_F.getCheckHartaDiselesaikanARB();
			int countListARB = listHartaARB.size();
			System.out.println("countListARB = "+countListARB);
			this.context.put("flagUntukEdit", "");
			this.context.put("nowb", "");
			if (countListARB > 0)
			{
				this.context.put("ViewHartaARB", "1");}
				else
				{	this.context.put("ViewHartaARB", "0");
			}
			//System.out.println(listHartaARB);
			//this.context.put("ViewHartaARB", listHartaARB);

			String t_mohon = getParam("tarikh_mohon");

			if (!t_mohon.equals("")) {
				String september_mohon = "01/09/2009";
				// /System.out.println("[check]1: " +t_mohon );

				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date tar_mohon = df.parse(t_mohon);
				Date sep_mohon = df.parse(september_mohon);

				if (tar_mohon.before(sep_mohon)) {
					// System.out.print(" is before ");
					view_mode(session);
					int eventStatus = 1;
					this.context.put("EventStatus", eventStatus);
					String id = getParam("idPermohonan");
					FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
							(String) session.getAttribute("_ekptg_user_id"));
					headerppk_baru(session, id, "Y", "", "T");

					Vector list = FrmSenaraiFailKeputusanPermohonanInternalData
							.getData();
					this.context.put("ViewPemohon", list);

					// Vector listDaerahByPpkUnit =
					// FrmPrmhnnSek8KeputusanPermohonanInternalData.getListDaerahByPpkUnit(Integer.parseInt(h.get("negeritertinggi").toString()));
					this.context.put("ListDaerahPindah",
							logic_A.getListDaerahPindah());

					Hashtable h1 = (Hashtable) list.get(0);
					if (h1.get("socNegeriPeguam").toString() != ""
							&& h1.get("idnegeri").toString() != "0") {
						Vector s3 = logic_A
								.getListBandarByNegeri(Integer.parseInt(h1.get(
										"socNegeriPeguam").toString()));
						this.context.put("listBandarTetapbyNegeri", s3);
					} else {
						this.context.put("listBandarTetapbyNegeri", "");
					}

					logic_A.setDataFail(id);
					listFail = logic_A.getDataFail();
					this.context.put("ViewFail", listFail);

					FrmPrmhnnSek8KeputusanPermohonanInternalData
							.setMaklumatMahkamah(id);
					Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
							.getMaklumatMahkamah();
					// System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
					this.context.put("listMaklumatMahkamah",
							listMaklumatMahkamah);

					FrmPrmhnnSek8KeputusanPermohonanInternalData
							.setMaklumatMahkamahM();
					Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData
							.getMaklumatMahkamahM();
					this.context.put("listMaklumatMahkamahJ",
							listMaklumatMahkamahM);

					this.context.put("eve", "");

					this.context.put("setMode1", "");
					this.context.put("setMode2", "");
					this.context.put("setMode3", "");
					this.context.put("setMode4", "");
					this.context.put("setMode5", "");
					this.context.put("setMode6", "");

					this.context.put("checkJ1", "");
					this.context.put("checkJ2", "");
					this.context.put("checkJ3", "");
					this.context.put("checkJ4", "");

					this.context.put("viewcheckJ1", "");
					this.context.put("viewcheckJ2", "");
					this.context.put("viewcheckJ3", "");
					this.context.put("viewcheckJ4", "");

					this.context.put("jenis_pej", "");
					this.context.put("jenis_pej_id", "");

					this.context.put("mahkamah_kosong", "yes");
					this.context.put("infoMahkamah", "");

					FrmPrmhnnSek8KeputusanPermohonanInternalData
							.setMaklumatMahkamahMPindah();
					Vector listMaklumatMahkamahMKPindah = FrmPrmhnnSek8KeputusanPermohonanInternalData
							.getMaklumatMahkamahMPindah();
					this.context.put("listMaklumatMahkamahJPindah",
							listMaklumatMahkamahMKPindah);

					Hashtable h2 = (Hashtable) list.get(0);
					if (h2.get("socNegeriPeguam").toString() != ""
							&& h2.get("idnegeri").toString() != "0") {
						Vector s3 = logic_A
								.getListBandarByNegeri(Integer.parseInt(h2.get(
										"socNegeriPeguam").toString()));
						this.context.put("listBandarTetapbyNegeri", s3);
					} else {
						this.context.put("listBandarTetapbyNegeri", "");
					}

					vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama.jsp";
				}
				if (tar_mohon.after(sep_mohon) || tar_mohon.equals(sep_mohon)) {
					// System.out.print(" is after ");
					view_mode(session);
					int eventStatus = 1;
					this.context.put("EventStatus", eventStatus);
					String id = getParam("idPermohonan");
					FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
							(String) session.getAttribute("_ekptg_user_id"));
					headerppk_baru(session, id, "Y", "", "T");
					Vector list = FrmSenaraiFailKeputusanPermohonanInternalData
							.getData();
					this.context.put("ViewPemohon", list);
					logic_A.setDataFail(id);
					listFail = logic_A.getDataFail();
					this.context.put("ViewFail", listFail);

					FrmPrmhnnSek8KeputusanPermohonanInternalData
							.setMaklumatMahkamah(id);
					Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
							.getMaklumatMahkamah();
					// System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
					this.context.put("listMaklumatMahkamah",
							listMaklumatMahkamah);

					FrmPrmhnnSek8KeputusanPermohonanInternalData
							.setMaklumatMahkamahM();
					Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData
							.getMaklumatMahkamahM();
					this.context.put("listMaklumatMahkamahJ",
							listMaklumatMahkamahM);

					this.context.put("eve", "");

					this.context.put("setMode1", "");
					this.context.put("setMode2", "");
					this.context.put("setMode3", "");
					this.context.put("setMode4", "");
					this.context.put("setMode5", "");
					this.context.put("setMode6", "");

					this.context.put("checkJ1", "");
					this.context.put("checkJ2", "");
					this.context.put("checkJ3", "");
					this.context.put("checkJ4", "");

					this.context.put("viewcheckJ1", "");
					this.context.put("viewcheckJ2", "");
					this.context.put("viewcheckJ3", "");
					this.context.put("viewcheckJ4", "");

					this.context.put("jenis_pej", "");
					this.context.put("jenis_pej_id", "");

					this.context.put("mahkamah_kosong", "yes");
					this.context.put("infoMahkamah", "");

					Hashtable h3 = (Hashtable) list.get(0);
					if (h3.get("socNegeriPeguam").toString() != ""
							&& h3.get("idnegeri").toString() != "0") {
						Vector s3 = logic_A
								.getListBandarByNegeri(Integer.parseInt(h3.get(
										"socNegeriPeguam").toString()));
						this.context.put("listBandarTetapbyNegeri", s3);
					} else {
						this.context.put("listBandarTetapbyNegeri", "");
					}

					// headerppk_baru(session,id,"Y","","T");
					vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan.jsp";
				}

				// System.out.println("TARIKH MOHON:"+tar_mohon);
			} else {
				// System.out.print(" is before ");
				view_mode(session);
				int eventStatus = 1;
				this.context.put("EventStatus", eventStatus);
				String id = getParam("idPermohonan");
				FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
						(String) session.getAttribute("_ekptg_user_id"));
				headerppk_baru(session, id, "Y", "", "T");
				Vector list = FrmSenaraiFailKeputusanPermohonanInternalData
						.getData();
				this.context.put("ViewPemohon", list);

				// Vector listDaerahByPpkUnit =
				// FrmPrmhnnSek8KeputusanPermohonanInternalData.getListDaerahByPpkUnit(Integer.parseInt(h.get("negeritertinggi").toString()));
				this.context.put("ListDaerahPindah",
						logic_A.getListDaerahPindah());

				logic_A.setDataFail(id);
				listFail = logic_A.getDataFail();
				this.context.put("ViewFail", listFail);

				FrmPrmhnnSek8KeputusanPermohonanInternalData
						.setMaklumatMahkamah(id);
				Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
						.getMaklumatMahkamah();
				// System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
				this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);

				FrmPrmhnnSek8KeputusanPermohonanInternalData
						.setMaklumatMahkamahM();
				Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData
						.getMaklumatMahkamahM();
				this.context
						.put("listMaklumatMahkamahJ", listMaklumatMahkamahM);

				this.context.put("eve", "");

				this.context.put("setMode1", "");
				this.context.put("setMode2", "");
				this.context.put("setMode3", "");
				this.context.put("setMode4", "");
				this.context.put("setMode5", "");
				this.context.put("setMode6", "");

				this.context.put("checkJ1", "");
				this.context.put("checkJ2", "");
				this.context.put("checkJ3", "");
				this.context.put("checkJ4", "");

				this.context.put("viewcheckJ1", "");
				this.context.put("viewcheckJ2", "");
				this.context.put("viewcheckJ3", "");
				this.context.put("viewcheckJ4", "");

				this.context.put("jenis_pej", "");
				this.context.put("jenis_pej_id", "");

				this.context.put("mahkamah_kosong", "yes");
				this.context.put("infoMahkamah", "");

				FrmPrmhnnSek8KeputusanPermohonanInternalData
						.setMaklumatMahkamahMPindah();
				Vector listMaklumatMahkamahMKPindah = FrmPrmhnnSek8KeputusanPermohonanInternalData
						.getMaklumatMahkamahMPindah();
				this.context.put("listMaklumatMahkamahJPindah",
						listMaklumatMahkamahMKPindah);

				Hashtable h4 = (Hashtable) list.get(0);
				if (h4.get("socNegeriPeguam").toString() != ""
						&& h4.get("idnegeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h4.get("socNegeriPeguam").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				headerppk_baru(session, id, "Y", "", "T");
				vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama.jsp";

			}	
		}

		else if ("get_alamatPejabat".equals(submit)) {

			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String j_p = getParam("jenis_pej");
			String id_p = getParam("jenis_pej_id");

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamahJ_ID(id_p);
			Vector listMaklumatMahkamahJ_ID = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahJ_ID();
			this.context.put("listMaklumatMahkamahJ_ID",
					listMaklumatMahkamahJ_ID);

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamahJ(j_p);
			Vector listMaklumatMahkamahJ = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahJ();
			this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahJ);

			Hashtable h = null;
			h = new Hashtable();
			Vector vv = new Vector();

			h.put("idFail", getParam("idFail"));
			h.put("noFail", getParam("noFail"));
			h.put("idDaerah", getParam("idDaerah"));
			h.put("idPermohonan", getParam("idPermohonan"));
			h.put("tarikhMohon", getParam("tarikhMohon"));
			h.put("idnegeri", getParam("idnegeri"));
			h.put("namanegeri", getParam("namanegeri"));
			h.put("namadaerah", getParam("namadaerah"));
			h.put("seksyen", getParam("seksyen"));
			h.put("keterangan", getParam("keterangan"));
			h.put("id_Status", getParam("id_Status"));
			h.put("namaPejabat", getParam("namaPejabat"));

			h.put("jumHtaTarikhMohon", getParam("jumHtaTarikhMohon"));
			h.put("jumHtaTarikhMati", getParam("jumHtaTarikhMati"));
			h.put("jumHaTarikhMohon", getParam("jumHaTarikhMohon"));
			h.put("jumHaTarikhMati", getParam("jumHaTarikhMati"));
			h.put("jumHartaTarikhMohon", getParam("jumHartaTarikhMohon"));
			h.put("jumHartaTarikhMati", getParam("jumHartaTarikhMati"));

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
			h.put("kep_BrgC", getParam("kep_brgC"));
			h.put("res_BrgC", getParam("res_brgC"));
			h.put("sCL", getParam("sec_codeLink"));

			h.put("tarikhhantarnilaian", getParam("txdTarikhHantarNilaian"));
			h.put("tarikhterimanilaian", getParam("txdTarikhTerimaNilaian"));

			// h.put("jenisborangC",getParam("jenisborangC"));
			h.put("keputusanpermohonan", getParam("keputusanpermohonan"));
			h.put("catatan", getParam("txtCatatan"));

			h.put("namaSimati", getParam("namaSimati"));
			h.put("namaPemohon", getParam("namaPemohon"));

			h.put("jenisborangC", getParam("sorKeputusanBorangC"));

			// h.put("jenisborangC",getParam("sorPenentuanBidangKuasa"));

			String check = getParam("sorPenentuanBidangKuasa");
			String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");
			String ex = "";
			if (checkterus != "")// ((check=="53" || check=="50" ||
									// check=="63"))
			{
				ex = checkterus;
			} else // if((check=="1") && (checkterus=="151" ||
					// checkterus=="152"))
			{
				ex = check;
			}
			h.put("keputusanpermohonan", ex);

			h.put("jenis_pej", getParam("jenis_pej"));

			h.put("nofailawal", getParam("nofailawal"));
			h.put("pemohonawal", getParam("namapemohonawal"));
			h.put("pejabatawal", getParam("jenis_pej_id"));

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			// System.out.println("MMM EX"+ex);

			vv.addElement(h);

			// System.out.println("PPP VV:"+vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			// System.out.println("jumHta"+getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode", "");
			this.context.put("setMode2", getParam("setMode2"));
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));
			this.context.put("checkJ4", getParam("checkJ4"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));
			this.context.put("viewcheckJ4", getParam("viewcheckJ4"));

			h.put("id_Permohonansimati", getParam("idpermohonansimati"));

			this.context.put("ViewPemohon", vv);

			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			headerppk_baru(session, getParam("idPermohonan"), "Y", "", "T");

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan.jsp";
		}

		else if ("get_alamatPejabatLama".equals(submit)) {
			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String j_p = getParam("jenis_pej");
			String id_p = getParam("jenis_pej_id");

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamahJ_ID(id_p);
			Vector listMaklumatMahkamahJ_ID = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahJ_ID();
			this.context.put("listMaklumatMahkamahJ_ID",
					listMaklumatMahkamahJ_ID);

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamahJ(j_p);
			Vector listMaklumatMahkamahJ = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahJ();
			this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahJ);

			Hashtable h = null;
			h = new Hashtable();
			Vector vv = new Vector();

			h.put("idFail", getParam("idFail"));
			h.put("noFail", getParam("noFail"));
			h.put("idDaerah", getParam("idDaerah"));
			h.put("idPermohonan", getParam("idPermohonan"));
			h.put("tarikhMohon", getParam("tarikhMohon"));
			h.put("idnegeri", getParam("idnegeri"));
			h.put("namanegeri", getParam("namanegeri"));
			h.put("namadaerah", getParam("namadaerah"));
			h.put("seksyen", getParam("seksyen"));
			h.put("keterangan", getParam("keterangan"));
			h.put("id_Status", getParam("id_Status"));
			h.put("namaPejabat", getParam("namaPejabat"));

			h.put("jumHtaTarikhMohon", getParam("jumHtaTarikhMohon"));
			h.put("jumHtaTarikhMati", getParam("jumHtaTarikhMati"));
			h.put("jumHaTarikhMohon", getParam("jumHaTarikhMohon"));
			h.put("jumHaTarikhMati", getParam("jumHaTarikhMati"));
			h.put("jumHartaTarikhMohon", getParam("jumHartaTarikhMohon"));
			h.put("jumHartaTarikhMati", getParam("jumHartaTarikhMati"));

			/*
			 * h.put("tarikhborangB",getParam("txdTarikhHantarBorangB"));
			 * h.put("tarikhborangC",getParam("txdTarikhTerimaBorangC"));
			 * h.put("tarikhhantarnilaian",getParam("txdTarikhHantarNilaian"));
			 * h.put("tarikhterimanilaian", getParam("txdTarikhHantarNilaian"));
			 */
			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
			h.put("kep_BrgC", getParam("kep_brgC"));
			h.put("res_BrgC", getParam("res_brgC"));
			h.put("sCL", getParam("sec_codeLink"));

			h.put("tarikhhantarnilaian", getParam("txdTarikhHantarNilaian"));
			h.put("tarikhterimanilaian", getParam("txdTarikhTerimaNilaian"));

			// h.put("jenisborangC",getParam("jenisborangC"));
			h.put("keputusanpermohonan", getParam("keputusanpermohonan"));
			h.put("catatan", getParam("txtCatatan"));

			h.put("namaSimati", getParam("namaSimati"));
			h.put("namaPemohon", getParam("namaPemohon"));

			h.put("jenisborangC", getParam("sorKeputusanBorangC"));

			// h.put("jenisborangC",getParam("sorPenentuanBidangKuasa"));

			String check = getParam("sorPenentuanBidangKuasa");
			String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");
			String ex = "";
			if (checkterus != "")// ((check=="53" || check=="50" ||
									// check=="63"))
			{
				ex = checkterus;
			} else // if((check=="1") && (checkterus=="151" ||
					// checkterus=="152"))
			{
				ex = check;
			}
			h.put("keputusanpermohonan", ex);

			h.put("jenis_pej", getParam("jenis_pej"));

			h.put("nofailawal", getParam("nofailawal"));
			h.put("pemohonawal", getParam("namapemohonawal"));
			h.put("pejabatawal", getParam("jenis_pej_id"));
			h.put("id_Permohonansimati", getParam("idpermohonansimati"));

			// System.out.println("MMM EX"+ex);

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			vv.addElement(h);

			// System.out.println("PPP VV:"+vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			// System.out.println("jumHta"+getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode", "");
			this.context.put("setMode2", getParam("setMode2"));
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));
			this.context.put("setMode99", getParam("setMode99"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));
			this.context.put("checkJ3", getParam("checkJ4"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ4"));

			this.context.put("ViewPemohon", vv);

			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			headerppk_baru(session, getParam("idPermohonan"), "Y", "", "T");

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama.jsp";
		}

		else if ("get_jenisPejabat".equals(submit)) {
			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String j_p = getParam("jenis_pej");

			// System.out.println()

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamahJ(j_p);
			Vector listMaklumatMahkamahJ = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahJ();
			this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahJ);

			Hashtable h = null;
			h = new Hashtable();
			Vector vv = new Vector();

			h.put("idFail", getParam("idFail"));
			h.put("noFail", getParam("noFail"));
			h.put("idDaerah", getParam("idDaerah"));
			h.put("idPermohonan", getParam("idPermohonan"));
			h.put("tarikhMohon", getParam("tarikhMohon"));
			h.put("idnegeri", getParam("idnegeri"));
			h.put("namanegeri", getParam("namanegeri"));
			h.put("namadaerah", getParam("namadaerah"));
			h.put("seksyen", getParam("seksyen"));
			h.put("keterangan", getParam("keterangan"));
			h.put("id_Status", getParam("id_Status"));
			h.put("namaPejabat", getParam("namaPejabat"));

			h.put("jumHtaTarikhMohon", getParam("jumHtaTarikhMohon"));
			h.put("jumHtaTarikhMati", getParam("jumHtaTarikhMati"));
			h.put("jumHaTarikhMohon", getParam("jumHaTarikhMohon"));
			h.put("jumHaTarikhMati", getParam("jumHaTarikhMati"));
			h.put("jumHartaTarikhMohon", getParam("jumHartaTarikhMohon"));
			h.put("jumHartaTarikhMati", getParam("jumHartaTarikhMati"));

			/*
			 * h.put("tarikhborangB",getParam("txdTarikhHantarBorangB"));
			 * h.put("tarikhborangC",getParam("txdTarikhTerimaBorangC"));
			 * h.put("tarikhhantarnilaian",getParam("txdTarikhHantarNilaian"));
			 * h.put("tarikhterimanilaian", getParam("txdTarikhHantarNilaian"));
			 */

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
			h.put("kep_BrgC", getParam("kep_brgC"));
			h.put("res_BrgC", getParam("res_brgC"));
			h.put("sCL", getParam("sec_codeLink"));

			h.put("tarikhhantarnilaian", getParam("txdTarikhHantarNilaian"));
			h.put("tarikhterimanilaian", getParam("txdTarikhTerimaNilaian"));

			// h.put("jenisborangC",getParam("jenisborangC"));
			h.put("keputusanpermohonan", getParam("keputusanpermohonan"));
			h.put("catatan", getParam("txtCatatan"));

			h.put("namaSimati", getParam("namaSimati"));
			h.put("namaPemohon", getParam("namaPemohon"));

			h.put("jenisborangC", getParam("sorKeputusanBorangC"));

			// h.put("jenisborangC",getParam("sorPenentuanBidangKuasa"));

			String check = getParam("sorPenentuanBidangKuasa");
			String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");
			String ex = "";
			if (checkterus != "")// ((check=="53" || check=="50" ||
									// check=="63"))
			{
				ex = checkterus;
			} else // if((check=="1") && (checkterus=="151" ||
					// checkterus=="152"))
			{
				ex = check;
			}
			h.put("keputusanpermohonan", ex);

			h.put("jenis_pej", getParam("jenis_pej"));
			h.put("nofailawal", getParam("nofailawal"));
			h.put("pemohonawal", getParam("namapemohonawal"));
			// h.put("pejabatawal",getParam("tempatmohonawal"));

			h.put("pejabatawal", "");

			// System.out.println("MMM EX"+ex);

			// h.put("negeritertinggi",getParam("id_negeripindah"));
			// h.put("daerahtertinggi",getParam("id_daerahpindah"));
			h.put("daerahmhn", getParam("id_daerahmohon"));
			h.put("negerimahkamah", getParam("negerimahkamah"));
			h.put("daerahmahkamah", getParam("daerahmahkamah"));

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			vv.addElement(h);

			// System.out.println("PPP VV:"+vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			// System.out.println("jumHta"+getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode2", getParam("setMode2"));

			// this.context.put("setMode2","disabled");
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));
			this.context.put("checkJ4", getParam("checkJ4"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));
			this.context.put("viewcheckJ4", getParam("viewcheckJ4"));

			h.put("id_Permohonansimati", getParam("idpermohonansimati"));

			this.context.put("ViewPemohon", vv);
			this.context.put("listMaklumatMahkamahJ_ID", "");
			// this.context.put("jenis_pej_id", "");
			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");
			this.context.put("EventStatus", "");
			this.context.put("jenis_pej_id", "");

			headerppk_baru(session, getParam("idPermohonan"), "Y", "", "T");

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan.jsp";
		}

		else if ("get_Bandarbaru".equals(submit)) {
			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String j_p = getParam("jenis_pej");

			// System.out.println()

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamahJ(j_p);
			Vector listMaklumatMahkamahJ = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahJ();
			this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahJ);

			Hashtable h = null;
			h = new Hashtable();
			Vector vv = new Vector();

			h.put("idFail", getParam("idFail"));
			h.put("noFail", getParam("noFail"));
			h.put("idDaerah", getParam("idDaerah"));
			h.put("idPermohonan", getParam("idPermohonan"));
			h.put("tarikhMohon", getParam("tarikhMohon"));
			h.put("idnegeri", getParam("idnegeri"));
			h.put("namanegeri", getParam("namanegeri"));
			h.put("namadaerah", getParam("namadaerah"));
			h.put("seksyen", getParam("seksyen"));
			h.put("keterangan", getParam("keterangan"));
			h.put("id_Status", getParam("id_Status"));
			h.put("namaPejabat", getParam("namaPejabat"));

			h.put("jumHtaTarikhMohon", getParam("jumHtaTarikhMohon"));
			h.put("jumHtaTarikhMati", getParam("jumHtaTarikhMati"));
			h.put("jumHaTarikhMohon", getParam("jumHaTarikhMohon"));
			h.put("jumHaTarikhMati", getParam("jumHaTarikhMati"));
			h.put("jumHartaTarikhMohon", getParam("jumHartaTarikhMohon"));
			h.put("jumHartaTarikhMati", getParam("jumHartaTarikhMati"));

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
			h.put("kep_BrgC", getParam("kep_brgC"));
			h.put("res_BrgC", getParam("res_brgC"));
			h.put("sCL", getParam("sec_codeLink"));

			h.put("tarikhhantarnilaian", getParam("txdTarikhHantarNilaian"));
			h.put("tarikhterimanilaian", getParam("txdTarikhTerimaNilaian"));

			h.put("keputusanpermohonan", getParam("keputusanpermohonan"));
			h.put("catatan", getParam("txtCatatan"));

			h.put("namaSimati", getParam("namaSimati"));
			h.put("namaPemohon", getParam("namaPemohon"));

			h.put("jenisborangC", getParam("sorKeputusanBorangC"));
			String check = getParam("sorPenentuanBidangKuasa");
			String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");

			h.put("txtNamaKaveat", getParam("txtNamaKaveat"));
			h.put("txtNoKaveat", getParam("txtNoKaveat"));
			h.put("txtNamaFirma", getParam("txtNamaFirma"));
			h.put("txtAlamat1Peguam", getParam("txtAlamat1Peguam"));
			h.put("txtAlamat2Peguam", getParam("txtAlamat2Peguam"));
			h.put("txtAlamat3Peguam", getParam("txtAlamat3Peguam"));
			h.put("txtPoskodPeguam", getParam("txtPoskodPeguam"));
			h.put("socNegeriPeguam", getParam("socNegeriPeguam"));

			

			h.put("txtBandarPeguam", "");
			h.put("txtNomborTelefonPeguam", getParam("txtNomborTelefonPeguam"));

			String ex = "";
			if (checkterus != "")// ((check=="53" || check=="50" ||
									// check=="63"))
			{
				ex = checkterus;
			} else // if((check=="1") && (checkterus=="151" ||
					// checkterus=="152"))
			{
				ex = check;
			}
			h.put("keputusanpermohonan", ex);

			h.put("jenis_pej", getParam("jenis_pej"));
			h.put("nofailawal", getParam("nofailawal"));
			h.put("pemohonawal", getParam("namapemohonawal"));
			// h.put("pejabatawal",getParam("tempatmohonawal"));

			h.put("pejabatawal", "");

			// System.out.println("MMM EX"+ex);

			// h.put("negeritertinggi",getParam("id_negeripindah"));
			// h.put("daerahtertinggi",getParam("id_daerahpindah"));
			h.put("daerahmhn", getParam("id_daerahmohon"));
			h.put("negerimahkamah", getParam("negerimahkamah"));
			h.put("daerahmahkamah", getParam("daerahmahkamah"));

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			vv.addElement(h);

			// System.out.println("PPP VV:"+vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			// System.out.println("jumHta"+getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode2", getParam("setMode2"));

			// this.context.put("setMode2","disabled");
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));
			this.context.put("checkJ4", getParam("checkJ4"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));
			this.context.put("viewcheckJ4", getParam("viewcheckJ4"));

			h.put("id_Permohonansimati", getParam("idpermohonansimati"));

			this.context.put("ViewPemohon", vv);
			this.context.put("listMaklumatMahkamahJ_ID", "");
			// this.context.put("jenis_pej_id", "");
			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			headerppk_baru(session, getParam("idPermohonan"), "Y", "", "T");

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");
			this.context.put("EventStatus", "");
			this.context.put("jenis_pej_id", "");

			Hashtable h3 = (Hashtable) vv.get(0);
			if (getParam("socNegeriPeguam") != ""
					&& getParam("socNegeriPeguam") != "0") {
				Vector s3 = logic_A.getListBandarByNegeri(Integer
						.parseInt(getParam("socNegeriPeguam")));
				this.context.put("listBandarTetapbyNegeri", s3);
			} else {
				this.context.put("listBandarTetapbyNegeri", "");
			}

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan.jsp";
		}
		
		else if ("get_BandarbaruEdit".equals(submit)) {
			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String j_p = getParam("jenis_pej");

			// System.out.println()

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamahJ(j_p);
			Vector listMaklumatMahkamahJ = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahJ();
			this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahJ);

			Hashtable h = null;
			h = new Hashtable();
			Vector vv = new Vector();

			h.put("idFail", getParam("idFail"));
			h.put("noFail", getParam("noFail"));
			h.put("idDaerah", getParam("idDaerah"));
			h.put("idPermohonan", getParam("idPermohonan"));
			h.put("tarikhMohon", getParam("tarikhMohon"));
			h.put("idnegeri", getParam("idnegeri"));
			h.put("namanegeri", getParam("namanegeri"));
			h.put("namadaerah", getParam("namadaerah"));
			h.put("seksyen", getParam("seksyen"));
			h.put("keterangan", getParam("keterangan"));
			h.put("id_Status", getParam("id_Status"));
			h.put("namaPejabat", getParam("namaPejabat"));

			h.put("jumHtaTarikhMohon", getParam("jumHtaTarikhMohon"));
			h.put("jumHtaTarikhMati", getParam("jumHtaTarikhMati"));
			h.put("jumHaTarikhMohon", getParam("jumHaTarikhMohon"));
			h.put("jumHaTarikhMati", getParam("jumHaTarikhMati"));
			h.put("jumHartaTarikhMohon", getParam("jumHartaTarikhMohon"));
			h.put("jumHartaTarikhMati", getParam("jumHartaTarikhMati"));

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
			h.put("kep_BrgC", getParam("kep_brgC"));
			h.put("res_BrgC", getParam("res_brgC"));
			h.put("sCL", getParam("sec_codeLink"));

			h.put("tarikhhantarnilaian", getParam("txdTarikhHantarNilaian"));
			h.put("tarikhterimanilaian", getParam("txdTarikhTerimaNilaian"));

			h.put("keputusanpermohonan", getParam("keputusanpermohonan"));
			h.put("catatan", getParam("txtCatatan"));

			h.put("namaSimati", getParam("namaSimati"));
			h.put("namaPemohon", getParam("namaPemohon"));

			h.put("jenisborangC", getParam("sorKeputusanBorangC"));
			String check = getParam("sorPenentuanBidangKuasa");
			String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");

			h.put("txtNamaKaveat", getParam("txtNamaKaveat"));
			h.put("txtNoKaveat", getParam("txtNoKaveat"));
			h.put("txtNamaFirma", getParam("txtNamaFirma"));
			h.put("txtAlamat1Peguam", getParam("txtAlamat1Peguam"));
			h.put("txtAlamat2Peguam", getParam("txtAlamat2Peguam"));
			h.put("txtAlamat3Peguam", getParam("txtAlamat3Peguam"));
			h.put("txtPoskodPeguam", getParam("txtPoskodPeguam"));
			h.put("socNegeriPeguam", getParam("socNegeriPeguamBodos"));

			//myLogger.info("socNegeriPeguam:" + getParam("socNegeriPeguamBodos"));

			h.put("txtBandarPeguam", "");
			h.put("txtNomborTelefonPeguam", getParam("txtNomborTelefonPeguam"));

			String ex = "";
			if (checkterus != "")// ((check=="53" || check=="50" ||
									// check=="63"))
			{
				ex = checkterus;
			} else // if((check=="1") && (checkterus=="151" ||
					// checkterus=="152"))
			{
				ex = check;
			}
			h.put("keputusanpermohonan", ex);

			h.put("jenis_pej", getParam("jenis_pej"));
			h.put("nofailawal", getParam("nofailawal"));
			h.put("pemohonawal", getParam("namapemohonawal"));
			// h.put("pejabatawal",getParam("tempatmohonawal"));

			h.put("pejabatawal", "");

			// System.out.println("MMM EX"+ex);

			// h.put("negeritertinggi",getParam("id_negeripindah"));
			// h.put("daerahtertinggi",getParam("id_daerahpindah"));
			h.put("daerahmhn", getParam("id_daerahmohon"));
			h.put("negerimahkamah", getParam("negerimahkamah"));
			h.put("daerahmahkamah", getParam("daerahmahkamah"));

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			vv.addElement(h);

			// System.out.println("PPP VV:"+vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			// System.out.println("jumHta"+getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode2", getParam("setMode2"));

			// this.context.put("setMode2","disabled");
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));
			this.context.put("checkJ4", getParam("checkJ4"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));
			this.context.put("viewcheckJ4", getParam("viewcheckJ4"));

			h.put("id_Permohonansimati", getParam("idpermohonansimati"));

			this.context.put("ViewPemohon", vv);
			this.context.put("listMaklumatMahkamahJ_ID", "");
			// this.context.put("jenis_pej_id", "");
			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			headerppk_baru(session, getParam("idPermohonan"), "Y", "", "T");

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");
			this.context.put("EventStatus", "");
			this.context.put("jenis_pej_id", "");

			Hashtable h3 = (Hashtable) vv.get(0);
			if (getParam("socNegeriPeguamBodos") != ""
					&& getParam("socNegeriPeguamBodos") != "0") {
				Vector s3 = logic_A.getListBandarByNegeri(Integer
						.parseInt(getParam("socNegeriPeguamBodos")));
				this.context.put("listBandarTetapbyNegeri", s3);
			} else {
				this.context.put("listBandarTetapbyNegeri", "");
			}

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan.jsp";
		}

		else if ("get_Bandarlama".equals(submit)) {
			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String j_p = getParam("jenis_pej");

			// System.out.println()

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamahJ(j_p);
			Vector listMaklumatMahkamahJ = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahJ();
			this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahJ);

			Hashtable h = null;
			h = new Hashtable();
			Vector vv = new Vector();

			h.put("idFail", getParam("idFail"));
			h.put("noFail", getParam("noFail"));
			h.put("idDaerah", getParam("idDaerah"));
			h.put("idPermohonan", getParam("idPermohonan"));
			h.put("tarikhMohon", getParam("tarikhMohon"));
			h.put("idnegeri", getParam("idnegeri"));
			h.put("namanegeri", getParam("namanegeri"));
			h.put("namadaerah", getParam("namadaerah"));
			h.put("seksyen", getParam("seksyen"));
			h.put("keterangan", getParam("keterangan"));
			h.put("id_Status", getParam("id_Status"));
			h.put("namaPejabat", getParam("namaPejabat"));

			h.put("jumHtaTarikhMohon", getParam("jumHtaTarikhMohon"));
			h.put("jumHtaTarikhMati", getParam("jumHtaTarikhMati"));
			h.put("jumHaTarikhMohon", getParam("jumHaTarikhMohon"));
			h.put("jumHaTarikhMati", getParam("jumHaTarikhMati"));
			h.put("jumHartaTarikhMohon", getParam("jumHartaTarikhMohon"));
			h.put("jumHartaTarikhMati", getParam("jumHartaTarikhMati"));

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
			h.put("kep_BrgC", getParam("kep_brgC"));
			h.put("res_BrgC", getParam("res_brgC"));
			h.put("sCL", getParam("sec_codeLink"));

			h.put("tarikhhantarnilaian", getParam("txdTarikhHantarNilaian"));
			h.put("tarikhterimanilaian", getParam("txdTarikhTerimaNilaian"));

			h.put("keputusanpermohonan", getParam("keputusanpermohonan"));
			h.put("catatan", getParam("txtCatatan"));

			h.put("namaSimati", getParam("namaSimati"));
			h.put("namaPemohon", getParam("namaPemohon"));

			h.put("jenisborangC", getParam("sorKeputusanBorangC"));
			String check = getParam("sorPenentuanBidangKuasa");
			String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");

			h.put("txtNamaKaveat", getParam("txtNamaKaveat"));
			h.put("txtNoKaveat", getParam("txtNoKaveat"));
			h.put("txtNamaFirma", getParam("txtNamaFirma"));
			h.put("txtAlamat1Peguam", getParam("txtAlamat1Peguam"));
			h.put("txtAlamat2Peguam", getParam("txtAlamat2Peguam"));
			h.put("txtAlamat3Peguam", getParam("txtAlamat3Peguam"));
			h.put("txtPoskodPeguam", getParam("txtPoskodPeguam"));
			h.put("socNegeriPeguam", getParam("socNegeriPeguam"));
			h.put("txtBandarPeguam", "");
			h.put("txtNomborTelefonPeguam", getParam("txtNomborTelefonPeguam"));

			String ex = "";
			if (checkterus != "")// ((check=="53" || check=="50" ||
									// check=="63"))
			{
				ex = checkterus;
			} else // if((check=="1") && (checkterus=="151" ||
					// checkterus=="152"))
			{
				ex = check;
			}
			h.put("keputusanpermohonan", ex);

			h.put("jenis_pej", getParam("jenis_pej"));
			h.put("nofailawal", getParam("nofailawal"));
			h.put("pemohonawal", getParam("namapemohonawal"));
			// h.put("pejabatawal",getParam("tempatmohonawal"));

			h.put("pejabatawal", "");

			// System.out.println("MMM EX"+ex);

			// h.put("negeritertinggi",getParam("id_negeripindah"));
			// h.put("daerahtertinggi",getParam("id_daerahpindah"));
			h.put("daerahmhn", getParam("id_daerahmohon"));
			h.put("negerimahkamah", getParam("negerimahkamah"));
			h.put("daerahmahkamah", getParam("daerahmahkamah"));

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			vv.addElement(h);

			// System.out.println("PPP VV:"+vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			// System.out.println("jumHta"+getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode2", getParam("setMode2"));

			// this.context.put("setMode2","disabled");
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));
			this.context.put("checkJ4", getParam("checkJ4"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));
			this.context.put("viewcheckJ4", getParam("viewcheckJ4"));

			h.put("id_Permohonansimati", getParam("idpermohonansimati"));

			this.context.put("ViewPemohon", vv);
			this.context.put("listMaklumatMahkamahJ_ID", "");
			// this.context.put("jenis_pej_id", "");
			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			headerppk_baru(session, getParam("idPermohonan"), "Y", "", "T");

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");
			this.context.put("EventStatus", "");
			this.context.put("jenis_pej_id", "");

			Hashtable h3 = (Hashtable) vv.get(0);
			if (getParam("socNegeriPeguam") != ""
					&& getParam("socNegeriPeguam") != "0") {
				Vector s3 = logic_A.getListBandarByNegeri(Integer
						.parseInt(getParam("socNegeriPeguam")));
				this.context.put("listBandarTetapbyNegeri", s3);
			} else {
				this.context.put("listBandarTetapbyNegeri", "");
			}

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama.jsp";
		}

		else if ("get_jenisPejabat_neg".equals(submit)) {
			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String j_p = getParam("jenis_pej");

			// System.out.println()

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamahJ(j_p);
			Vector listMaklumatMahkamahJ = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahJ();
			this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahJ);

			Hashtable h = null;
			h = new Hashtable();
			Vector vv = new Vector();

			h.put("idFail", getParam("idFail"));
			h.put("noFail", getParam("noFail"));
			h.put("idDaerah", getParam("idDaerah"));
			h.put("idPermohonan", getParam("idPermohonan"));
			h.put("tarikhMohon", getParam("tarikhMohon"));
			h.put("idnegeri", getParam("idnegeri"));
			h.put("namanegeri", getParam("namanegeri"));
			h.put("namadaerah", getParam("namadaerah"));
			h.put("seksyen", getParam("seksyen"));
			h.put("keterangan", getParam("keterangan"));
			h.put("id_Status", getParam("id_Status"));
			h.put("namaPejabat", getParam("namaPejabat"));

			h.put("jumHtaTarikhMohon", getParam("jumHtaTarikhMohon"));
			h.put("jumHtaTarikhMati", getParam("jumHtaTarikhMati"));
			h.put("jumHaTarikhMohon", getParam("jumHaTarikhMohon"));
			h.put("jumHaTarikhMati", getParam("jumHaTarikhMati"));
			h.put("jumHartaTarikhMohon", getParam("jumHartaTarikhMohon"));
			h.put("jumHartaTarikhMati", getParam("jumHartaTarikhMati"));

			/*
			 * h.put("tarikhborangB",getParam("txdTarikhHantarBorangB"));
			 * h.put("tarikhborangC",getParam("txdTarikhTerimaBorangC"));
			 * h.put("tarikhhantarnilaian",getParam("txdTarikhHantarNilaian"));
			 * h.put("tarikhterimanilaian", getParam("txdTarikhHantarNilaian"));
			 */

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
			h.put("kep_BrgC", getParam("kep_brgC"));
			h.put("res_BrgC", getParam("res_brgC"));
			h.put("sCL", getParam("sec_codeLink"));

			h.put("tarikhhantarnilaian", getParam("txdTarikhHantarNilaian"));
			h.put("tarikhterimanilaian", getParam("txdTarikhTerimaNilaian"));

			// h.put("jenisborangC",getParam("jenisborangC"));
			h.put("keputusanpermohonan", getParam("keputusanpermohonan"));
			h.put("catatan", getParam("txtCatatan"));

			h.put("namaSimati", getParam("namaSimati"));
			h.put("namaPemohon", getParam("namaPemohon"));

			h.put("jenisborangC", getParam("sorKeputusanBorangC"));

			// h.put("jenisborangC",getParam("sorPenentuanBidangKuasa"));

			String check = getParam("sorPenentuanBidangKuasa");
			String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");
			String ex = "";
			if (checkterus != "")// ((check=="53" || check=="50" ||
									// check=="63"))
			{
				ex = checkterus;
			} else // if((check=="1") && (checkterus=="151" ||
					// checkterus=="152"))
			{
				ex = check;
			}
			h.put("keputusanpermohonan", ex);

			h.put("jenis_pej", getParam("jenis_pej"));
			h.put("nofailawal", getParam("nofailawal"));
			h.put("pemohonawal", getParam("namapemohonawal"));
			// h.put("pejabatawal",getParam("tempatmohonawal"));

			h.put("pejabatawal", "");

			// System.out.println("MMM EX"+ex);

			// h.put("negeritertinggi",getParam("id_negeripindah"));
			// h.put("daerahtertinggi",getParam("id_daerahpindah"));
			h.put("daerahmhn", getParam("id_daerahmohon"));
			h.put("negerimahkamah", getParam("negerimahkamah"));
			h.put("daerahmahkamah", getParam("daerahmahkamah"));

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			vv.addElement(h);

			// System.out.println("PPP VV:"+vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			// System.out.println("jumHta"+getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode2", getParam("setMode2"));

			// this.context.put("setMode2","disabled");
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));
			this.context.put("checkJ4", getParam("checkJ4"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));
			this.context.put("viewcheckJ4", getParam("viewcheckJ4"));

			h.put("id_Permohonansimati", getParam("idpermohonansimati"));

			this.context.put("ViewPemohon", vv);
			this.context.put("listMaklumatMahkamahJ_ID", "");
			// this.context.put("jenis_pej_id", "");
			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			headerppk_baru(session, getParam("idPermohonan"), "Y", "", "T");

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");
			this.context.put("EventStatus", "");
			this.context.put("jenis_pej_id", "");

			this.context.put("socNegeriWarisSurat",
					getParam("socNegeriWarisSurat"));
			this.context.put("pilih_negeri_ptd", getParam("pilih_negeri_ptd"));

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan.jsp";
		}

		else if ("get_jenisPejabatLama_neg".equals(submit)) {
			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String j_p = getParam("jenis_pej");

			// System.out.println()

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamahJ(j_p);
			Vector listMaklumatMahkamahJ = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahJ();
			this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahJ);

			Hashtable h = null;
			h = new Hashtable();
			Vector vv = new Vector();

			h.put("idFail", getParam("idFail"));
			h.put("noFail", getParam("noFail"));
			h.put("idDaerah", getParam("idDaerah"));
			h.put("idPermohonan", getParam("idPermohonan"));
			h.put("tarikhMohon", getParam("tarikhMohon"));
			h.put("idnegeri", getParam("idnegeri"));
			h.put("namanegeri", getParam("namanegeri"));
			h.put("namadaerah", getParam("namadaerah"));
			h.put("seksyen", getParam("seksyen"));
			h.put("keterangan", getParam("keterangan"));
			h.put("id_Status", getParam("id_Status"));
			h.put("namaPejabat", getParam("namaPejabat"));

			h.put("jumHtaTarikhMohon", getParam("jumHtaTarikhMohon"));
			h.put("jumHtaTarikhMati", getParam("jumHtaTarikhMati"));
			h.put("jumHaTarikhMohon", getParam("jumHaTarikhMohon"));
			h.put("jumHaTarikhMati", getParam("jumHaTarikhMati"));
			h.put("jumHartaTarikhMohon", getParam("jumHartaTarikhMohon"));
			h.put("jumHartaTarikhMati", getParam("jumHartaTarikhMati"));

			/*
			 * h.put("tarikhborangB",getParam("txdTarikhHantarBorangB"));
			 * h.put("tarikhborangC",getParam("txdTarikhTerimaBorangC"));
			 * h.put("tarikhhantarnilaian",getParam("txdTarikhHantarNilaian"));
			 * h.put("tarikhterimanilaian", getParam("txdTarikhHantarNilaian"));
			 */

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
			h.put("kep_BrgC", getParam("kep_brgC"));
			h.put("res_BrgC", getParam("res_brgC"));
			h.put("sCL", getParam("sec_codeLink"));

			h.put("tarikhhantarnilaian", getParam("txdTarikhHantarNilaian"));
			h.put("tarikhterimanilaian", getParam("txdTarikhTerimaNilaian"));

			// h.put("jenisborangC",getParam("jenisborangC"));
			h.put("keputusanpermohonan", getParam("keputusanpermohonan"));
			h.put("catatan", getParam("txtCatatan"));

			h.put("namaSimati", getParam("namaSimati"));
			h.put("namaPemohon", getParam("namaPemohon"));

			h.put("jenisborangC", getParam("sorKeputusanBorangC"));

			// h.put("jenisborangC",getParam("sorPenentuanBidangKuasa"));

			String check = getParam("sorPenentuanBidangKuasa");
			String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");
			String ex = "";
			if (checkterus != "")// ((check=="53" || check=="50" ||
									// check=="63"))
			{
				ex = checkterus;
			} else // if((check=="1") && (checkterus=="151" ||
					// checkterus=="152"))
			{
				ex = check;
			}
			h.put("keputusanpermohonan", ex);

			h.put("jenis_pej", getParam("jenis_pej"));
			h.put("nofailawal", getParam("nofailawal"));
			h.put("pemohonawal", getParam("namapemohonawal"));
			// h.put("pejabatawal",getParam("tempatmohonawal"));

			h.put("pejabatawal", "");
			h.put("id_Permohonansimati", getParam("idpermohonansimati"));

			// System.out.println("MMM EX"+ex);

			h.put("negeritertinggi", getParam("id_negeripindah"));
			h.put("daerahtertinggi", getParam("id_daerahpindah"));
			h.put("daerahmhn", getParam("id_daerahmohon"));
			h.put("negerimahkamah", getParam("negerimahkamah"));
			h.put("daerahmahkamah", getParam("daerahmahkamah"));

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			vv.addElement(h);

			// System.out.println("PPP VV:"+vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			// System.out.println("jumHta"+getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode2", getParam("setMode2"));

			// this.context.put("setMode2","disabled");
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode99", getParam("setMode99"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));
			this.context.put("checkJ4", getParam("checkJ4"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));
			this.context.put("viewcheckJ4", getParam("viewcheckJ4"));

			this.context.put("ViewPemohon", vv);
			this.context.put("listMaklumatMahkamahJ_ID", "");
			// this.context.put("jenis_pej_id", "");
			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			headerppk_baru(session, getParam("idPermohonan"), "Y", "", "T");

			this.context.put("socNegeriWarisSurat",
					getParam("socNegeriWarisSurat"));
			this.context.put("pilih_negeri_ptd", getParam("pilih_negeri_ptd"));

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");
			this.context.put("EventStatus", "");
			this.context.put("jenis_pej_id", "");

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama.jsp";
		}

		else if ("get_jenisPejabatLama".equals(submit)) {
			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String j_p = getParam("jenis_pej");

			// System.out.println()

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamahJ(j_p);
			Vector listMaklumatMahkamahJ = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahJ();
			this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahJ);

			Hashtable h = null;
			h = new Hashtable();
			Vector vv = new Vector();

			h.put("idFail", getParam("idFail"));
			h.put("noFail", getParam("noFail"));
			h.put("idDaerah", getParam("idDaerah"));
			h.put("idPermohonan", getParam("idPermohonan"));
			h.put("tarikhMohon", getParam("tarikhMohon"));
			h.put("idnegeri", getParam("idnegeri"));
			h.put("namanegeri", getParam("namanegeri"));
			h.put("namadaerah", getParam("namadaerah"));
			h.put("seksyen", getParam("seksyen"));
			h.put("keterangan", getParam("keterangan"));
			h.put("id_Status", getParam("id_Status"));
			h.put("namaPejabat", getParam("namaPejabat"));

			h.put("jumHtaTarikhMohon", getParam("jumHtaTarikhMohon"));
			h.put("jumHtaTarikhMati", getParam("jumHtaTarikhMati"));
			h.put("jumHaTarikhMohon", getParam("jumHaTarikhMohon"));
			h.put("jumHaTarikhMati", getParam("jumHaTarikhMati"));
			h.put("jumHartaTarikhMohon", getParam("jumHartaTarikhMohon"));
			h.put("jumHartaTarikhMati", getParam("jumHartaTarikhMati"));

			/*
			 * h.put("tarikhborangB",getParam("txdTarikhHantarBorangB"));
			 * h.put("tarikhborangC",getParam("txdTarikhTerimaBorangC"));
			 * h.put("tarikhhantarnilaian",getParam("txdTarikhHantarNilaian"));
			 * h.put("tarikhterimanilaian", getParam("txdTarikhHantarNilaian"));
			 */

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
			h.put("kep_BrgC", getParam("kep_brgC"));
			h.put("res_BrgC", getParam("res_brgC"));
			h.put("sCL", getParam("sec_codeLink"));

			h.put("tarikhhantarnilaian", getParam("txdTarikhHantarNilaian"));
			h.put("tarikhterimanilaian", getParam("txdTarikhTerimaNilaian"));

			// h.put("jenisborangC",getParam("jenisborangC"));
			h.put("keputusanpermohonan", getParam("keputusanpermohonan"));
			h.put("catatan", getParam("txtCatatan"));

			h.put("namaSimati", getParam("namaSimati"));
			h.put("namaPemohon", getParam("namaPemohon"));

			h.put("jenisborangC", getParam("sorKeputusanBorangC"));

			// h.put("jenisborangC",getParam("sorPenentuanBidangKuasa"));

			String check = getParam("sorPenentuanBidangKuasa");
			String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");
			String ex = "";
			if (checkterus != "")// ((check=="53" || check=="50" ||
									// check=="63"))
			{
				ex = checkterus;
			} else // if((check=="1") && (checkterus=="151" ||
					// checkterus=="152"))
			{
				ex = check;
			}
			h.put("keputusanpermohonan", ex);

			h.put("jenis_pej", getParam("jenis_pej"));
			h.put("nofailawal", getParam("nofailawal"));
			h.put("pemohonawal", getParam("namapemohonawal"));
			// h.put("pejabatawal",getParam("tempatmohonawal"));

			h.put("pejabatawal", "");
			h.put("id_Permohonansimati", getParam("idpermohonansimati"));

			// System.out.println("MMM EX"+ex);

			h.put("negeritertinggi", getParam("id_negeripindah"));
			h.put("daerahtertinggi", getParam("id_daerahpindah"));
			h.put("daerahmhn", getParam("id_daerahmohon"));
			h.put("negerimahkamah", getParam("negerimahkamah"));
			h.put("daerahmahkamah", getParam("daerahmahkamah"));

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			vv.addElement(h);

			// System.out.println("PPP VV:"+vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			// System.out.println("jumHta"+getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode2", getParam("setMode2"));

			// this.context.put("setMode2","disabled");
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode99", getParam("setMode99"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));
			this.context.put("checkJ4", getParam("checkJ4"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));
			this.context.put("viewcheckJ4", getParam("viewcheckJ4"));

			this.context.put("ViewPemohon", vv);
			this.context.put("listMaklumatMahkamahJ_ID", "");
			// this.context.put("jenis_pej_id", "");
			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			headerppk_baru(session, getParam("idPermohonan"), "Y", "", "T");

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");
			this.context.put("EventStatus", "");
			this.context.put("jenis_pej_id", "");

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama.jsp";
		}

		else if ("Lama_neg".equals(submit)) {
			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String j_p = getParam("jenis_pej");

			// System.out.println()

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamahJ(j_p);
			Vector listMaklumatMahkamahJ = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahJ();
			this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahJ);

			Hashtable h = null;
			h = new Hashtable();
			Vector vv = new Vector();

			h.put("idFail", getParam("idFail"));
			h.put("noFail", getParam("noFail"));
			h.put("idDaerah", getParam("idDaerah"));
			h.put("idPermohonan", getParam("idPermohonan"));
			h.put("tarikhMohon", getParam("tarikhMohon"));
			h.put("idnegeri", getParam("idnegeri"));
			h.put("namanegeri", getParam("namanegeri"));
			h.put("namadaerah", getParam("namadaerah"));
			h.put("seksyen", getParam("seksyen"));
			h.put("keterangan", getParam("keterangan"));
			h.put("id_Status", getParam("id_Status"));
			h.put("namaPejabat", getParam("namaPejabat"));

			h.put("jumHtaTarikhMohon", getParam("jumHtaTarikhMohon"));
			h.put("jumHtaTarikhMati", getParam("jumHtaTarikhMati"));
			h.put("jumHaTarikhMohon", getParam("jumHaTarikhMohon"));
			h.put("jumHaTarikhMati", getParam("jumHaTarikhMati"));
			h.put("jumHartaTarikhMohon", getParam("jumHartaTarikhMohon"));
			h.put("jumHartaTarikhMati", getParam("jumHartaTarikhMati"));

			/*
			 * h.put("tarikhborangB",getParam("txdTarikhHantarBorangB"));
			 * h.put("tarikhborangC",getParam("txdTarikhTerimaBorangC"));
			 * h.put("tarikhhantarnilaian",getParam("txdTarikhHantarNilaian"));
			 * h.put("tarikhterimanilaian", getParam("txdTarikhHantarNilaian"));
			 */

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
			h.put("kep_BrgC", getParam("kep_brgC"));
			h.put("res_BrgC", getParam("res_brgC"));
			h.put("sCL", getParam("sec_codeLink"));

			h.put("tarikhhantarnilaian", getParam("txdTarikhHantarNilaian"));
			h.put("tarikhterimanilaian", getParam("txdTarikhTerimaNilaian"));

			// h.put("jenisborangC",getParam("jenisborangC"));
			h.put("keputusanpermohonan", getParam("keputusanpermohonan"));
			h.put("catatan", getParam("txtCatatan"));

			h.put("namaSimati", getParam("namaSimati"));
			h.put("namaPemohon", getParam("namaPemohon"));

			h.put("jenisborangC", getParam("sorKeputusanBorangC"));

			// h.put("jenisborangC",getParam("sorPenentuanBidangKuasa"));

			String check = getParam("sorPenentuanBidangKuasa");
			String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");
			String ex = "";
			if (checkterus != "")// ((check=="53" || check=="50" ||
									// check=="63"))
			{
				ex = checkterus;
			} else // if((check=="1") && (checkterus=="151" ||
					// checkterus=="152"))
			{
				ex = check;
			}
			h.put("keputusanpermohonan", ex);

			h.put("jenis_pej", getParam("jenis_pej"));
			h.put("nofailawal", getParam("nofailawal"));
			h.put("pemohonawal", getParam("namapemohonawal"));
			// h.put("pejabatawal",getParam("tempatmohonawal"));

			h.put("pejabatawal", "");
			h.put("id_Permohonansimati", getParam("idpermohonansimati"));

			// System.out.println("MMM EX"+ex);

			h.put("negeritertinggi", getParam("id_negeripindah"));
			h.put("daerahtertinggi", getParam("id_daerahpindah"));
			h.put("daerahmhn", getParam("id_daerahmohon"));
			h.put("negerimahkamah", getParam("negerimahkamah"));
			h.put("daerahmahkamah", getParam("daerahmahkamah"));

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			vv.addElement(h);

			// System.out.println("PPP VV:"+vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			// System.out.println("jumHta"+getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode2", getParam("setMode2"));

			// this.context.put("setMode2","disabled");
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode99", getParam("setMode99"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));
			this.context.put("checkJ4", getParam("checkJ4"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));
			this.context.put("viewcheckJ4", getParam("viewcheckJ4"));

			this.context.put("ViewPemohon", vv);
			this.context.put("listMaklumatMahkamahJ_ID", "");
			// this.context.put("jenis_pej_id", "");
			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			headerppk_baru(session, getParam("idPermohonan"), "Y", "", "T");

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");
			this.context.put("EventStatus", "");
			this.context.put("jenis_pej_id", "");

			this.context.put("socNegeriWarisSurat",
					getParam("socNegeriWarisSurat"));
			this.context.put("pilih_negeri_ptd", getParam("pilih_negeri_ptd"));

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama.jsp";
		}

		else if ("getKemaskini_keputusan".equals(submit)) {
			System.out.print(" *******************getKemaskini_keputusan************** ");
			Vector listKaveatPeguam = new Vector();
			listKaveatPeguam.clear();
			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);
			logic_F.setListKaveatPeguam(getParam("idpermohonansimati"));
			listKaveatPeguam = logic_F.getListKaveatPeguam();
			this.context.put("listKaveatPeguam", listKaveatPeguam);
			String t_mohon = getParam("tarikh_mohon");
			String september_mohon = "01/09/2009";
			System.out.println("[check]1: " + t_mohon);

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date tar_mohon = df.parse(t_mohon);
			Date sep_mohon = df.parse(september_mohon);

			if (tar_mohon.after(sep_mohon) || tar_mohon.equals(sep_mohon)) {
				// System.out.print(" is after ");
				view_mode(session);
				int eventStatus = 0;
				this.context.put("EventStatus", eventStatus);
				String id = getParam("idPermohonan");
				FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
						(String) session.getAttribute("_ekptg_user_id"));
				headerppk_baru(session, id, "Y", "", "T");
				Vector list = FrmSenaraiFailKeputusanPermohonanInternalData
						.getData();
				this.context.put("ViewPemohon", list);
				logic_A.setDataFail(id);
				listFail = logic_A.getDataFail();
				this.context.put("ViewFail", listFail);
				this.context.put("checkKaveat", getParam("checkKaveat"));
				this.context.put("flagUntukEdit", getParam("flagUntukEdit"));
				this.context.put("nowb", getParam("nowb"));

				FrmPrmhnnSek8KeputusanPermohonanInternalData
						.setMaklumatMahkamah(id);
				Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
						.getMaklumatMahkamah();
				// System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
				this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);

				FrmPrmhnnSek8KeputusanPermohonanInternalData
						.setMaklumatMahkamahM();
				Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData
						.getMaklumatMahkamahM();
				this.context
						.put("listMaklumatMahkamahJ", listMaklumatMahkamahM);

				String idm_s = getParam("negerimahkamah");

				if (idm_s != "") {
					String idm = idm_s;
					FrmSenaraiFailKeputusanPermohonanInternalData
							.setListDaerah(idm);
					Vector listDaerah = FrmSenaraiFailKeputusanPermohonanInternalData
							.getListDaerah();
					this.context.put("ListDaerah", listDaerah);
				}

				this.context.put("eve", "");

				this.context.put("setMode1", "");
				this.context.put("setMode2", "");
				this.context.put("setMode3", "");
				this.context.put("setMode4", "");
				this.context.put("setMode5", "");
				this.context.put("setMode6", "");

				this.context.put("checkJ1", "");
				this.context.put("checkJ2", "");
				this.context.put("checkJ3", "");
				this.context.put("checkJ4", "");

				this.context.put("viewcheckJ1", "");
				this.context.put("viewcheckJ2", "");
				this.context.put("viewcheckJ3", "");
				this.context.put("viewcheckJ4", "");

				this.context.put("jenis_pej", "");
				this.context.put("jenis_pej_id", "");

				this.context.put("mahkamah_kosong", "yes");
				this.context.put("infoMahkamah", "");

				Hashtable h2 = (Hashtable) list.get(0);
				if (h2.get("socNegeriPeguam").toString() != ""
						&& h2.get("idnegeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h2.get("socNegeriPeguam").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan.jsp";
			}

		}

		else if ("getKemaskini_keputusanLama".equals(submit)) {
			System.out.print(" *******************getKemaskini_keputusanLama************** ");
			// pas
			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String t_mohon = getParam("tarikh_mohon");

			if (!t_mohon.equals("")) {
				String september_mohon = "01/09/2009";
				System.out.println("[check]1: " + t_mohon);

				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date tar_mohon = df.parse(t_mohon);
				Date sep_mohon = df.parse(september_mohon);

				if (tar_mohon.before(sep_mohon)) {
					// System.out.print(" is before ");
					view_mode(session);
					int eventStatus = 0;
					this.context.put("EventStatus", eventStatus);
					String id = getParam("idPermohonan");
					FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
							(String) session.getAttribute("_ekptg_user_id"));
					headerppk_baru(session, id, "Y", "", "T");
					Vector list = FrmSenaraiFailKeputusanPermohonanInternalData
							.getData();
					this.context.put("ViewPemohon", list);

					// Vector listDaerahByPpkUnit =
					// FrmPrmhnnSek8KeputusanPermohonanInternalData.getListDaerahByPpkUnit(Integer.parseInt(h.get("negeritertinggi").toString()));
					// this.context.put("ListDaerahPindah",
					// logic_A.getListDaerahPindah() );
					//

					// negeritertinggi

					Hashtable k = (Hashtable) list.get(0);
					if (k.get("negeritertinggi").toString() != ""
							&& k.get("negeritertinggi").toString() != "0") {
						// Vector s3 = logic_A.getListBandarByNegeri((String)
						// k.get("negeritertinggi"));
						Vector listDaerahByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
								.getListDaerahByPpkUnit((String) k
										.get("negeritertinggi"));

						this.context.put("ListDaerahPindah",
								listDaerahByPpkUnit);
					} else {
						this.context.put("ListDaerahPindah", "");
						;
					}

					logic_A.setDataFail(id);
					listFail = logic_A.getDataFail();
					this.context.put("ViewFail", listFail);

					FrmPrmhnnSek8KeputusanPermohonanInternalData
							.setMaklumatMahkamah(id);
					Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
							.getMaklumatMahkamah();
					// System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
					this.context.put("listMaklumatMahkamah",
							listMaklumatMahkamah);

					FrmPrmhnnSek8KeputusanPermohonanInternalData
							.setMaklumatMahkamahM();
					Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData
							.getMaklumatMahkamahM();
					this.context.put("listMaklumatMahkamahJ",
							listMaklumatMahkamahM);

					String idm_s = getParam("negerimahkamah");

					if (idm_s != "") {
						String idm = idm_s;
						FrmSenaraiFailKeputusanPermohonanInternalData
								.setListDaerah(idm);
						Vector listDaerah = FrmSenaraiFailKeputusanPermohonanInternalData
								.getListDaerah();
						this.context.put("ListDaerah", listDaerah);
					}

					this.context.put("eve", "");

					this.context.put("setMode1", "");
					this.context.put("setMode2", "");
					this.context.put("setMode3", "");
					this.context.put("setMode4", "");
					this.context.put("setMode5", "");
					this.context.put("setMode6", "");

					this.context.put("checkJ1", "");
					this.context.put("checkJ2", "");
					this.context.put("checkJ3", "");
					this.context.put("checkJ4", "");

					this.context.put("viewcheckJ1", "");
					this.context.put("viewcheckJ2", "");
					this.context.put("viewcheckJ3", "");
					this.context.put("viewcheckJ4", "");

					this.context.put("jenis_pej", "");
					this.context.put("jenis_pej_id", "");

					this.context.put("mahkamah_kosong", "yes");
					this.context.put("infoMahkamah", "");

					FrmPrmhnnSek8KeputusanPermohonanInternalData
							.setMaklumatMahkamahMPindah();
					Vector listMaklumatMahkamahMKPindah = FrmPrmhnnSek8KeputusanPermohonanInternalData
							.getMaklumatMahkamahMPindah();
					this.context.put("listMaklumatMahkamahJPindah",
							listMaklumatMahkamahMKPindah);

				}
			} else {

				view_mode(session);
				int eventStatus = 0;
				this.context.put("EventStatus", eventStatus);
				String id = getParam("idPermohonan");
				FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
						(String) session.getAttribute("_ekptg_user_id"));
				headerppk_baru(session, id, "Y", "", "T");
				Vector list = FrmSenaraiFailKeputusanPermohonanInternalData
						.getData();
				this.context.put("ViewPemohon", list);

				// Vector listDaerahByPpkUnit =
				// FrmPrmhnnSek8KeputusanPermohonanInternalData.getListDaerahByPpkUnit(Integer.parseInt(h.get("negeritertinggi").toString()));
				// this.context.put("ListDaerahPindah",
				// logic_A.getListDaerahPindah() );
				//

				// negeritertinggi

				Hashtable k = (Hashtable) list.get(0);
				if (k.get("negeritertinggi").toString() != ""
						&& k.get("negeritertinggi").toString() != "0") {
					// Vector s3 = logic_A.getListBandarByNegeri((String)
					// k.get("negeritertinggi"));
					Vector listDaerahByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
							.getListDaerahByPpkUnit((String) k
									.get("negeritertinggi"));

					this.context.put("ListDaerahPindah", listDaerahByPpkUnit);
				} else {
					this.context.put("ListDaerahPindah", "");
					;
				}

				logic_A.setDataFail(id);
				listFail = logic_A.getDataFail();
				this.context.put("ViewFail", listFail);

				FrmPrmhnnSek8KeputusanPermohonanInternalData
						.setMaklumatMahkamah(id);
				Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
						.getMaklumatMahkamah();
				// System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
				this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);

				FrmPrmhnnSek8KeputusanPermohonanInternalData
						.setMaklumatMahkamahM();
				Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData
						.getMaklumatMahkamahM();
				this.context
						.put("listMaklumatMahkamahJ", listMaklumatMahkamahM);

				String idm_s = getParam("negerimahkamah");

				if (idm_s != "") {
					String idm = idm_s;
					FrmSenaraiFailKeputusanPermohonanInternalData
							.setListDaerah(idm);
					Vector listDaerah = FrmSenaraiFailKeputusanPermohonanInternalData
							.getListDaerah();
					this.context.put("ListDaerah", listDaerah);
				}

				this.context.put("eve", "");

				this.context.put("setMode1", "");
				this.context.put("setMode2", "");
				this.context.put("setMode3", "");
				this.context.put("setMode4", "");
				this.context.put("setMode5", "");
				this.context.put("setMode6", "");

				this.context.put("checkJ1", "");
				this.context.put("checkJ2", "");
				this.context.put("checkJ3", "");
				this.context.put("checkJ4", "");

				this.context.put("viewcheckJ1", "");
				this.context.put("viewcheckJ2", "");
				this.context.put("viewcheckJ3", "");
				this.context.put("viewcheckJ4", "");

				this.context.put("jenis_pej", "");
				this.context.put("jenis_pej_id", "");

				this.context.put("mahkamah_kosong", "yes");
				this.context.put("infoMahkamah", "");

				FrmPrmhnnSek8KeputusanPermohonanInternalData
						.setMaklumatMahkamahMPindah();
				Vector listMaklumatMahkamahMKPindah = FrmPrmhnnSek8KeputusanPermohonanInternalData
						.getMaklumatMahkamahMPindah();
				this.context.put("listMaklumatMahkamahJPindah",
						listMaklumatMahkamahMKPindah);

			}

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama.jsp";

			// System.out.println("TARIKH MOHON:"+tar_mohon);

			// vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama.jsp";
		}

		/*
		 * else if("getKemaskini_keputusanLama".equals(submit)){
		 * 
		 * 
		 * 
		 * Vector listNegeriByPpkUnit =
		 * FrmPrmhnnSek8KeputusanPermohonanInternalData
		 * .getListNegeriByhta(getParam("idpermohonansimati"));
		 * this.context.put("ListNegeriPindah",listNegeriByPpkUnit);
		 * 
		 * FrmPrmhnnSek8KeputusanPermohonanInternalData.setMaklumatMahkamahMPindah
		 * (); Vector listMaklumatMahkamahMKPindah =
		 * FrmPrmhnnSek8KeputusanPermohonanInternalData
		 * .getMaklumatMahkamahMPindah();
		 * this.context.put("listMaklumatMahkamahJPindah",
		 * listMaklumatMahkamahMKPindah);
		 * 
		 * 
		 * // ListDaerahPindah
		 * 
		 * String inp = getParam("id_negeripindah");
		 * 
		 * if(inp != "")
		 * 
		 * { Vector listDaerahByPpkUnit =
		 * FrmPrmhnnSek8KeputusanPermohonanInternalData
		 * .getListDaerahByPpkUnit(Integer.parseInt(inp));
		 * this.context.put("ListDaerahPindah",listDaerahByPpkUnit); } else {
		 * 
		 * this.context.put("ListDaerahPindah",""); }
		 * 
		 * 
		 * 
		 * 
		 * Vector listD = null; view_mode(session); listKeputusan =
		 * logicKeputusanPrmhnn.getDataKeputusan(); this.context.put("View",
		 * listKeputusan); int eventStatus = 0; this.context.put("EventStatus",
		 * eventStatus); String id = getParam("idPermohonan");
		 * FrmSenaraiFailKeputusanPermohonanInternalData
		 * .setData(id,(String)session.getAttribute("_ekptg_user_id"));
		 * 
		 * listD = FrmSenaraiFailKeputusanPermohonanInternalData.getData(); //
		 * Vector listk =
		 * FrmSenaraiFailKeputusanPermohonanInternalData.getData();
		 * this.context.put("ViewPemohon", listD); logic_A.setDataFail(id);
		 * listFail = logic_A.getDataFail(); this.context.put("ViewFail",
		 * listFail);
		 * 
		 * 
		 * String jps=getParam("jenis_pej"); String
		 * id_ps=getParam("jenis_pej_id");
		 * 
		 * 
		 * if(id_ps != ""){
		 * 
		 * int id_p = Integer.parseInt(id_ps);
		 * FrmPrmhnnSek8KeputusanPermohonanInternalData
		 * .setMaklumatMahkamahJ_ID(id_p); Vector listMaklumatMahkamahJ_ID =
		 * FrmPrmhnnSek8KeputusanPermohonanInternalData
		 * .getMaklumatMahkamahJ_ID();
		 * this.context.put("listMaklumatMahkamahJ_ID",
		 * listMaklumatMahkamahJ_ID); }
		 * 
		 * if(jps != ""){ int j_p = Integer.parseInt(jps);
		 * FrmPrmhnnSek8KeputusanPermohonanInternalData
		 * .setMaklumatMahkamahJ(j_p); Vector listMaklumatMahkamahJ =
		 * FrmPrmhnnSek8KeputusanPermohonanInternalData.getMaklumatMahkamahJ();
		 * this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahJ); }
		 * 
		 * 
		 * String idm_s = getParam("negerimahkamah");
		 * 
		 * if(idm_s != "") { String idm = idm_s;
		 * FrmSenaraiFailKeputusanPermohonanInternalData.setListDaerah(idm);
		 * Vector listDaerah =
		 * FrmSenaraiFailKeputusanPermohonanInternalData.getListDaerah();
		 * this.context.put("ListDaerah",listDaerah); }
		 * 
		 * FrmPrmhnnSek8KeputusanPermohonanInternalData.setMaklumatMahkamah(id);
		 * Vector listMaklumatMahkamah =
		 * FrmPrmhnnSek8KeputusanPermohonanInternalData.getMaklumatMahkamah();
		 * //
		 * System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
		 * this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);
		 * 
		 * 
		 * vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama.jsp"; }
		 */
		else if ("getBack_keputusan".equals(submit)) {

			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String usid = (String) session.getAttribute("_ekptg_user_id");
			logic_E.setList(usid);
			listKeputusan = logic_E.getList();
			String Carix = "2";
			this.context.put("carix", Carix);
			this.context.put("Senaraifail", listKeputusan);

			FrmPrmhnnSek8KeputusanPermohonanInternalData.setMaklumatMahkamahM();
			Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahM();
			this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahM);

			vm = "app/ppk/frmSenaraiFailKeputusanPermohonan.jsp";
		} else if ("getBack_keputusanLama".equals(submit)) {

			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String usid = (String) session.getAttribute("_ekptg_user_id");
			logic_E.setList(usid);
			listKeputusan = logic_E.getList();
			String Carix = "2";
			this.context.put("carix", Carix);
			this.context.put("Senaraifail", listKeputusan);

			FrmPrmhnnSek8KeputusanPermohonanInternalData.setMaklumatMahkamahM();
			Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahM();
			this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahM);

			vm = "app/ppk/frmSenaraiFailKeputusanPermohonanLama.jsp";
		} else if ("getSenaraiMahkamah".equals(submit)) {

			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String idflag = "0";
			this.context.put("idFlag", idflag);
			String IdPermohonan = getParam("idPermohonan");
			String id = getParam("idPermohonan");

			// String id = getParam("idPermohonan");
			FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			Vector list = FrmSenaraiFailKeputusanPermohonanInternalData
					.getData();
			this.context.put("ViewPemohon", list);
			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			this.context.put("idPermohonan", IdPermohonan);
			String usid = (String) session.getAttribute("_ekptg_user_id");
			logic_E.setList(usid);
			listKeputusan = logic_E.getList();
			String Carix = "2";
			this.context.put("carix", Carix);
			this.context.put("Senaraifail", listKeputusan);

			FrmSenaraiFailKeputusanPermohonanInternalData.setListNegeri();
			Vector listNegeri = FrmSenaraiFailKeputusanPermohonanInternalData
					.getListNegeri();
			this.context.put("ListNegeri", listNegeri);

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamah(id);
			Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamah();
			// System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
			this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);

			// vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";
			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan.jsp";
		} else if ("getMahkamah".equals(submit)) {

			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String IdPermohonan = getParam("idPermohonan");
			this.context.put("idPermohonan", IdPermohonan);

			String id = getParam("idPermohonan");

			// String id = getParam("idPermohonan");

			String idNegeri = getParam("socNegeri");
			this.context.put("SocNegeri", idNegeri);

			FrmSenaraiFailKeputusanPermohonanInternalData.setListNegeri();
			Vector listNegeri = FrmSenaraiFailKeputusanPermohonanInternalData
					.getListNegeri();
			this.context.put("ListNegeri", listNegeri);

			FrmSenaraiFailKeputusanPermohonanInternalData
					.setListDaerah(idNegeri);
			Vector listDaerah = FrmSenaraiFailKeputusanPermohonanInternalData
					.getListDaerah();
			this.context.put("ListDaerah", listDaerah);

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamah(id);
			Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamah();
			// System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
			this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);

			Hashtable h = null;
			h = new Hashtable();
			Vector vv = new Vector();

			h.put("idFail", getParam("idFail"));
			h.put("noFail", getParam("noFail"));
			h.put("idDaerah", getParam("idDaerah"));
			h.put("idPermohonan", getParam("idPermohonan"));
			h.put("tarikhMohon", getParam("tarikhMohon"));
			h.put("idnegeri", getParam("idnegeri"));
			h.put("namanegeri", getParam("namanegeri"));
			h.put("namadaerah", getParam("namadaerah"));
			h.put("seksyen", getParam("seksyen"));
			h.put("keterangan", getParam("keterangan"));
			h.put("id_Status", getParam("id_Status"));
			h.put("namaPejabat", getParam("namaPejabat"));

			h.put("jumHtaTarikhMohon", getParam("jumHtaTarikhMohon"));
			h.put("jumHtaTarikhMati", getParam("jumHtaTarikhMati"));
			h.put("jumHaTarikhMohon", getParam("jumHaTarikhMohon"));
			h.put("jumHaTarikhMati", getParam("jumHaTarikhMati"));
			h.put("jumHartaTarikhMohon", Double.parseDouble(getParam("jumHartaTarikhMohon")));//razman edit
			h.put("jumHartaTarikhMati", getParam("jumHartaTarikhMati"));
			
			h.put("tujuanPindah", getParam("tujuanPindah"));//razman add
			
			

			/*
			 * h.put("tarikhborangB",getParam("txdTarikhHantarBorangB"));
			 * h.put("tarikhborangC",getParam("txdTarikhTerimaBorangC"));
			 * h.put("tarikhhantarnilaian",getParam("txdTarikhHantarNilaian"));
			 * h.put("tarikhterimanilaian", getParam("txdTarikhHantarNilaian"));
			 */

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
			h.put("kep_BrgC", getParam("kep_brgC"));
			h.put("res_BrgC", getParam("res_brgC"));
			h.put("sCL", getParam("sec_codeLink"));

			h.put("tarikhhantarnilaian", getParam("txdTarikhHantarNilaian"));
			h.put("tarikhterimanilaian", getParam("txdTarikhTerimaNilaian"));

			// h.put("jenisborangC",getParam("jenisborangC"));
			h.put("keputusanpermohonan", getParam("keputusanpermohonan"));
			h.put("catatan", getParam("catatan"));

			h.put("namaSimati", getParam("namaSimati"));
			h.put("namaPemohon", getParam("namaPemohon"));

			h.put("jenisborangC", getParam("sorKeputusanBorangC"));

			// h.put("jenisborangC",getParam("sorPenentuanBidangKuasa"));

			String check = getParam("sorPenentuanBidangKuasa");
			String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");
			String ex = "";
			if (checkterus != "")// ((check=="53" || check=="50" ||
									// check=="63"))
			{
				ex = checkterus;
			} else // if((check=="1") && (checkterus=="151" ||
					// checkterus=="152"))
			{
				ex = check;
			}
			h.put("keputusanpermohonan", ex);

			h.put("jenis_pej", getParam("jenis_pej"));

			h.put("nofailawal", getParam("nofailawal"));
			h.put("pemohonawal", getParam("namapemohonawal"));
			h.put("pejabatawal", getParam("tempatmohonawal"));

			h.put("negerimahkamah", getParam("socNegeri"));
			h.put("daerahmahkamah", "");

			h.put("id_Permohonansimati", getParam("idpermohonansimati"));

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			// System.out.println("MMM EX"+ex);

			vv.addElement(h);

			// System.out.println("PPP VV:"+vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			// System.out.println("jumHta"+getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode2", getParam("setMode2"));
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));
			this.context.put("checkJ4", getParam("checkJ4"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ4", getParam("viewcheckJ4"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));

			this.context.put("ViewPemohon", vv);

			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			headerppk_baru(session, getParam("idPermohonan"), "Y", "", "T");

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");

			// vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";
			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan.jsp";
		}

		else if ("getMahkamahLamaPindah".equals(submit)) {

			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			Vector listDaerahByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListDaerahByPpkUnit(getParam("socNegeriPindah"));
			this.context.put("ListDaerahPindah", listDaerahByPpkUnit);

			String IdPermohonan = getParam("idPermohonan");
			this.context.put("idPermohonan", IdPermohonan);

			String id = getParam("idPermohonan");

			// String id = getParam("idPermohonan");

			String idNegeri = getParam("socNegeriPindah");
			this.context.put("SocNegeri", idNegeri);

			/*
			 * FrmSenaraiFailKeputusanPermohonanInternalData.setListNegeri();
			 * Vector listNegeri =
			 * FrmSenaraiFailKeputusanPermohonanInternalData.getListNegeri();
			 * this.context.put("ListNegeri",listNegeri);
			 * 
			 * FrmSenaraiFailKeputusanPermohonanInternalData.setListDaerah(idNegeri
			 * ); Vector listDaerah =
			 * FrmSenaraiFailKeputusanPermohonanInternalData.getListDaerah();
			 * this.context.put("ListDaerah",listDaerah);
			 */

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamah(id);
			Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamah();
			// System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
			this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);

			Hashtable h = null;
			h = new Hashtable();
			Vector vv = new Vector();

			h.put("idFail", getParam("idFail"));
			h.put("noFail", getParam("noFail"));
			h.put("idDaerah", getParam("idDaerah"));
			h.put("idPermohonan", getParam("idPermohonan"));
			h.put("tarikhMohon", getParam("tarikhMohon"));
			h.put("idnegeri", getParam("idnegeri"));
			h.put("namanegeri", getParam("namanegeri"));
			h.put("namadaerah", getParam("namadaerah"));
			h.put("seksyen", getParam("seksyen"));
			h.put("keterangan", getParam("keterangan"));
			h.put("id_Status", getParam("id_Status"));
			h.put("namaPejabat", getParam("namaPejabat"));

			h.put("jumHtaTarikhMohon", getParam("jumHtaTarikhMohon"));
			h.put("jumHtaTarikhMati", getParam("jumHtaTarikhMati"));
			h.put("jumHaTarikhMohon", getParam("jumHaTarikhMohon"));
			h.put("jumHaTarikhMati", getParam("jumHaTarikhMati"));
			h.put("jumHartaTarikhMohon", getParam("jumHartaTarikhMohon"));
			h.put("jumHartaTarikhMati", getParam("jumHartaTarikhMati"));

			/*
			 * 
			 * h.put("tarikhborangB",getParam("txdTarikhHantarBorangB"));
			 * h.put("tarikhborangC",getParam("txdTarikhTerimaBorangC"));
			 * h.put("tarikhhantarnilaian",getParam("txdTarikhHantarNilaian"));
			 * h.put("tarikhterimanilaian", getParam("txdTarikhHantarNilaian"));
			 */

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
			h.put("kep_BrgC", getParam("kep_brgC"));
			h.put("res_BrgC", getParam("res_brgC"));
			h.put("sCL", getParam("sec_codeLink"));

			h.put("tarikhhantarnilaian", getParam("txdTarikhHantarNilaian"));
			h.put("tarikhterimanilaian", getParam("txdTarikhTerimaNilaian"));

			// h.put("jenisborangC",getParam("jenisborangC"));
			h.put("keputusanpermohonan", getParam("keputusanpermohonan"));
			h.put("catatan", getParam("catatan"));

			h.put("namaSimati", getParam("namaSimati"));
			h.put("namaPemohon", getParam("namaPemohon"));

			h.put("jenisborangC", getParam("sorKeputusanBorangC"));

			// h.put("id_Permohonansimati",getParam("idpermohonansimati"));

			// h.put("jenisborangC",getParam("sorPenentuanBidangKuasa"));

			String check = getParam("sorPenentuanBidangKuasa");
			String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");
			String ex = "";
			if (checkterus != "")// ((check=="53" || check=="50" ||
									// check=="63"))
			{
				ex = checkterus;
			} else // if((check=="1") && (checkterus=="151" ||
					// checkterus=="152"))
			{
				ex = check;
			}
			h.put("keputusanpermohonan", ex);

			h.put("jenis_pej", getParam("jenis_pej"));

			h.put("nofailawal", getParam("nofailawal"));
			h.put("pemohonawal", getParam("namapemohonawal"));
			h.put("pejabatawal", getParam("tempatmohonawal"));

			h.put("negeritertinggi", getParam("socNegeriPindah"));
			h.put("daerahtertinggi", "");

			h.put("id_Permohonansimati", getParam("idpermohonansimati"));

			// System.out.println("MMM EX"+ex);

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			vv.addElement(h);

			// System.out.println("PPP VV:"+vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			// System.out.println("jumHta"+getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode2", getParam("setMode2"));
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));
			this.context.put("setMode99", getParam("setMode99"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));
			this.context.put("checkJ4", getParam("checkJ4"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));
			this.context.put("viewcheckJ4", getParam("viewcheckJ4"));

			this.context.put("ViewPemohon", vv);

			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			headerppk_baru(session, getParam("idPermohonan"), "Y", "", "T");

			this.context.put("eve", 8);
			this.context.put("EventStatus", "");
			this.context.put("eve_mah", "");

			this.context.put("daerahpindah", "0");

			// vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";
			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama.jsp";
		}

		else if ("getMahkamahLama".equals(submit)) {

			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String IdPermohonan = getParam("idPermohonan");
			this.context.put("idPermohonan", IdPermohonan);

			String id = getParam("idPermohonan");

			// String id = getParam("idPermohonan");

			String idNegeri = getParam("socNegeri");
			this.context.put("SocNegeri", idNegeri);

			FrmSenaraiFailKeputusanPermohonanInternalData.setListNegeri();
			Vector listNegeri = FrmSenaraiFailKeputusanPermohonanInternalData
					.getListNegeri();
			this.context.put("ListNegeri", listNegeri);

			FrmSenaraiFailKeputusanPermohonanInternalData
					.setListDaerah(idNegeri);
			Vector listDaerah = FrmSenaraiFailKeputusanPermohonanInternalData
					.getListDaerah();
			this.context.put("ListDaerah", listDaerah);

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamah(id);
			Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamah();
			// System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
			this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);

			Hashtable h = null;
			h = new Hashtable();
			Vector vv = new Vector();

			h.put("idFail", getParam("idFail"));
			h.put("noFail", getParam("noFail"));
			h.put("idDaerah", getParam("idDaerah"));
			h.put("idPermohonan", getParam("idPermohonan"));
			h.put("tarikhMohon", getParam("tarikhMohon"));
			h.put("idnegeri", getParam("idnegeri"));
			h.put("namanegeri", getParam("namanegeri"));
			h.put("namadaerah", getParam("namadaerah"));
			h.put("seksyen", getParam("seksyen"));
			h.put("keterangan", getParam("keterangan"));
			h.put("id_Status", getParam("id_Status"));
			h.put("namaPejabat", getParam("namaPejabat"));

			h.put("jumHtaTarikhMohon", getParam("jumHtaTarikhMohon"));
			h.put("jumHtaTarikhMati", getParam("jumHtaTarikhMati"));
			h.put("jumHaTarikhMohon", getParam("jumHaTarikhMohon"));
			h.put("jumHaTarikhMati", getParam("jumHaTarikhMati"));
			h.put("jumHartaTarikhMohon", Double.parseDouble(getParam("jumHartaTarikhMohon")));//razman edit
			h.put("jumHartaTarikhMati", getParam("jumHartaTarikhMati"));
			
			h.put("tujuanPindah", getParam("tujuanPindah"));//razman add

			/*
			 * h.put("tarikhborangB",getParam("txdTarikhHantarBorangB"));
			 * h.put("tarikhborangC",getParam("txdTarikhTerimaBorangC"));
			 * h.put("tarikhhantarnilaian",getParam("txdTarikhHantarNilaian"));
			 * h.put("tarikhterimanilaian", getParam("txdTarikhHantarNilaian"));
			 */

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
			h.put("kep_BrgC", getParam("kep_brgC"));
			h.put("res_BrgC", getParam("res_brgC"));
			h.put("sCL", getParam("sec_codeLink"));

			h.put("tarikhhantarnilaian", getParam("txdTarikhHantarNilaian"));
			h.put("tarikhterimanilaian", getParam("txdTarikhTerimaNilaian"));

			// h.put("jenisborangC",getParam("jenisborangC"));
			h.put("keputusanpermohonan", getParam("keputusanpermohonan"));
			h.put("catatan", getParam("catatan"));

			h.put("namaSimati", getParam("namaSimati"));
			h.put("namaPemohon", getParam("namaPemohon"));

			h.put("jenisborangC", getParam("sorKeputusanBorangC"));

			// h.put("jenisborangC",getParam("sorPenentuanBidangKuasa"));

			String check = getParam("sorPenentuanBidangKuasa");
			String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");
			String ex = "";
			if (checkterus != "")// ((check=="53" || check=="50" ||
									// check=="63"))
			{
				ex = checkterus;
			} else // if((check=="1") && (checkterus=="151" ||
					// checkterus=="152"))
			{
				ex = check;
			}
			h.put("keputusanpermohonan", ex);

			h.put("jenis_pej", getParam("jenis_pej"));

			h.put("nofailawal", getParam("nofailawal"));
			h.put("pemohonawal", getParam("namapemohonawal"));
			h.put("pejabatawal", getParam("tempatmohonawal"));

			h.put("negerimahkamah", getParam("socNegeri"));
			h.put("daerahmahkamah", "");

			// System.out.println("MMM EX"+ex);

			h.put("id_Permohonansimati", getParam("idpermohonansimati"));
			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));
			vv.addElement(h);

			// System.out.println("PPP VV:"+vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			// System.out.println("jumHta"+getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode2", getParam("setMode2"));
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));
			this.context.put("checkJ4", getParam("checkJ4"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));
			this.context.put("viewcheckJ4", getParam("viewcheckJ4"));

			this.context.put("ViewPemohon", vv);

			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			headerppk_baru(session, getParam("idPermohonan"), "Y", "", "T");

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");

			// vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";
			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama.jsp";
		} else if ("getMahkamahAddress".equals(submit)) {

			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String id = getParam("idPermohonan");
			String IdPermohonan = getParam("idPermohonan");
			this.context.put("idPermohonan", IdPermohonan);
			// String id = getParam("idPermohonan");
			FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			Vector list = FrmSenaraiFailKeputusanPermohonanInternalData
					.getData();
			this.context.put("ViewPemohon", list);
			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			String idflag = "1";
			this.context.put("idFlag", idflag);
			String idneg = getParam("socNegeri");
			Long daerahId = Long.parseLong(getParam("socDaerah"));
			Long negId = Long.parseLong(getParam("socNegeri"));
			this.context.put("Senaraifail", listKeputusan);

			String idNegeri = getParam("socNegeri");
			this.context.put("SocNegeri", idNegeri);

			String idDaerah = getParam("socDaerah");
			this.context.put("SocDaerah", idDaerah);

			FrmSenaraiFailKeputusanPermohonanInternalData.setListNegeri();
			Vector listNegeri = FrmSenaraiFailKeputusanPermohonanInternalData
					.getListNegeri();
			this.context.put("ListNegeri", listNegeri);

			FrmSenaraiFailKeputusanPermohonanInternalData
					.setListDaerah(idNegeri);
			Vector listDaerah = FrmSenaraiFailKeputusanPermohonanInternalData
					.getListDaerah();
			this.context.put("ListDaerah", listDaerah);

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamah(id);
			Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamah();
			// System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
			this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);

			// view_mahkamah(session);
			// String idDaerah = getParam("socDaerah");
			// String idNegeri = getParam("socNegeri");
			FrmPrmhnnSek8KeputusanPermohonanInternalData.semakAlamatMahkamah(
					idDaerah, idNegeri);
			Vector dataMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getSemakMahkamah();
			this.context.put("infoMahkamah", dataMahkamah);

			FrmPrmhnnSek8KeputusanPermohonanInternalData.setMaklumatMahkamahM();
			Vector listMaklumatMahkamahMK = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahM();
			this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahMK);

			Hashtable h = null;
			h = new Hashtable();
			Vector vv = new Vector();

			h.put("idFail", getParam("idFail"));
			h.put("noFail", getParam("noFail"));
			h.put("idDaerah", getParam("idDaerah"));
			h.put("idPermohonan", getParam("idPermohonan"));
			h.put("tarikhMohon", getParam("tarikhMohon"));
			h.put("idnegeri", getParam("idnegeri"));
			h.put("namanegeri", getParam("namanegeri"));
			h.put("namadaerah", getParam("namadaerah"));
			h.put("seksyen", getParam("seksyen"));
			h.put("keterangan", getParam("keterangan"));
			h.put("id_Status", getParam("id_Status"));
			h.put("namaPejabat", getParam("namaPejabat"));

			h.put("jumHtaTarikhMohon", getParam("jumHtaTarikhMohon"));
			h.put("jumHtaTarikhMati", getParam("jumHtaTarikhMati"));
			h.put("jumHaTarikhMohon", getParam("jumHaTarikhMohon"));
			h.put("jumHaTarikhMati", getParam("jumHaTarikhMati"));
			
			h.put("jumHartaTarikhMohon", Double.parseDouble(getParam("jumHartaTarikhMohon")));//razman edit
			
			h.put("jumHartaTarikhMati", getParam("jumHartaTarikhMati"));
			
			h.put("tujuanPindah", getParam("tujuanPindah"));//razman add

			/*
			 * 
			 * h.put("tarikhborangB",getParam("txdTarikhHantarBorangB"));
			 * h.put("tarikhborangC",getParam("txdTarikhTerimaBorangC"));
			 * h.put("tarikhhantarnilaian",getParam("txdTarikhHantarNilaian"));
			 * h.put("tarikhterimanilaian", getParam("txdTarikhHantarNilaian"));
			 */

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
			h.put("kep_BrgC", getParam("kep_brgC"));
			h.put("res_BrgC", getParam("res_brgC"));
			h.put("sCL", getParam("sec_codeLink"));

			h.put("tarikhhantarnilaian", getParam("txdTarikhHantarNilaian"));
			h.put("tarikhterimanilaian", getParam("txdTarikhTerimaNilaian"));

			// h.put("jenisborangC",getParam("jenisborangC"));
			
			//razman add open
			String check = getParam("sorPenentuanBidangKuasa");
			String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");
			myLogger.info("check : "+check+" >>> checkterus : "+checkterus);
			String ex = "";
			if (!checkterus.equals(""))// ((check=="53" || check=="50" ||
									// check=="63"))
			{
				ex = checkterus;
			} else // if((check=="1") && (checkterus=="151" ||
					// checkterus=="152"))
			{
				ex = check;
			}
			h.put("keputusanpermohonan", ex);
			//razman add close
			//h.put("keputusanpermohonan", getParam("keputusanpermohonan")); // razman comment
			
			h.put("catatan", getParam("catatan"));

			h.put("namaSimati", getParam("namaSimati"));
			h.put("namaPemohon", getParam("namaPemohon"));

			h.put("jenisborangC", getParam("sorKeputusanBorangC"));

			// h.put("jenisborangC",getParam("sorPenentuanBidangKuasa"));

			/*
			String check = getParam("sorPenentuanBidangKuasa");
			String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");
			String ex = "";
			if (checkterus != "")// ((check=="53" || check=="50" ||
									// check=="63"))
			{
				ex = checkterus;
			} else // if((check=="1") && (checkterus=="151" ||
					// checkterus=="152"))
			{
				ex = check;
			}
			h.put("keputusanpermohonan", ex);
			*/
			
			h.put("jenis_pej", getParam("jenis_pej"));

			h.put("nofailawal", getParam("nofailawal"));
			h.put("pemohonawal", getParam("namapemohonawal"));
			h.put("pejabatawal", getParam("tempatmohonawal"));

			h.put("negerimahkamah", getParam("socNegeri"));
			h.put("daerahmahkamah", getParam("socDaerah"));

			h.put("id_Permohonansimati", getParam("idpermohonansimati"));

			// System.out.println("MMM EX"+ex);

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			vv.addElement(h);

			// System.out.println("PPP VV:"+vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			// System.out.println("jumHta"+getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode2", getParam("setMode2"));
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));
			this.context.put("checkJ4", getParam("checkJ4"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));
			this.context.put("viewcheckJ4", getParam("viewcheckJ4"));

			this.context.put("ViewPemohon", vv);

			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			// headerppk_baru(session,getParam("idPermohonan"),"Y","","T");

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");
			// vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan.jsp";
		}

		else if ("getMahkamahAddressLama".equals(submit)) {

			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String id = getParam("idPermohonan");
			String IdPermohonan = getParam("idPermohonan");
			this.context.put("idPermohonan", IdPermohonan);
			// String id = getParam("idPermohonan");
			FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			Vector list = FrmSenaraiFailKeputusanPermohonanInternalData
					.getData();
			this.context.put("ViewPemohon", list);
			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			String idflag = "1";
			this.context.put("idFlag", idflag);
			String idneg = getParam("socNegeri");
			Long daerahId = Long.parseLong(getParam("socDaerah"));
			Long negId = Long.parseLong(getParam("socNegeri"));
			this.context.put("Senaraifail", listKeputusan);

			String idNegeri = getParam("socNegeri");
			this.context.put("SocNegeri", idNegeri);

			String idDaerah = getParam("socDaerah");
			this.context.put("SocDaerah", idDaerah);

			FrmSenaraiFailKeputusanPermohonanInternalData.setListNegeri();
			Vector listNegeri = FrmSenaraiFailKeputusanPermohonanInternalData
					.getListNegeri();
			this.context.put("ListNegeri", listNegeri);

			FrmSenaraiFailKeputusanPermohonanInternalData
					.setListDaerah(idNegeri);
			Vector listDaerah = FrmSenaraiFailKeputusanPermohonanInternalData
					.getListDaerah();
			this.context.put("ListDaerah", listDaerah);

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamah(id);
			Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamah();
			// System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
			this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);

			// view_mahkamah(session);
			// String idDaerah = getParam("socDaerah");
			// String idNegeri = getParam("socNegeri");
			FrmPrmhnnSek8KeputusanPermohonanInternalData.semakAlamatMahkamah(
					idDaerah, idNegeri);
			Vector dataMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getSemakMahkamah();
			this.context.put("infoMahkamah", dataMahkamah);

			FrmPrmhnnSek8KeputusanPermohonanInternalData.setMaklumatMahkamahM();
			Vector listMaklumatMahkamahMK = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahM();
			this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahMK);

			Hashtable h = null;
			h = new Hashtable();
			Vector vv = new Vector();

			h.put("idFail", getParam("idFail"));
			h.put("noFail", getParam("noFail"));
			h.put("idDaerah", getParam("idDaerah"));
			h.put("idPermohonan", getParam("idPermohonan"));
			h.put("tarikhMohon", getParam("tarikhMohon"));
			h.put("idnegeri", getParam("idnegeri"));
			h.put("namanegeri", getParam("namanegeri"));
			h.put("namadaerah", getParam("namadaerah"));
			h.put("seksyen", getParam("seksyen"));
			h.put("keterangan", getParam("keterangan"));
			h.put("id_Status", getParam("id_Status"));
			h.put("namaPejabat", getParam("namaPejabat"));

			h.put("jumHtaTarikhMohon", getParam("jumHtaTarikhMohon"));
			h.put("jumHtaTarikhMati", getParam("jumHtaTarikhMati"));
			h.put("jumHaTarikhMohon", getParam("jumHaTarikhMohon"));
			h.put("jumHaTarikhMati", getParam("jumHaTarikhMati"));
			h.put("jumHartaTarikhMohon", Double.parseDouble(getParam("jumHartaTarikhMohon")));//razman edit
			h.put("jumHartaTarikhMati", getParam("jumHartaTarikhMati"));
			
			h.put("tujuanPindah", getParam("tujuanPindah"));//razman add

			/*
			 * h.put("tarikhborangB",getParam("txdTarikhHantarBorangB"));
			 * h.put("tarikhborangC",getParam("txdTarikhTerimaBorangC"));
			 * h.put("tarikhhantarnilaian",getParam("txdTarikhHantarNilaian"));
			 * h.put("tarikhterimanilaian", getParam("txdTarikhHantarNilaian"));
			 */

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
			h.put("kep_BrgC", getParam("kep_brgC"));
			h.put("res_BrgC", getParam("res_brgC"));
			h.put("sCL", getParam("sec_codeLink"));

			h.put("tarikhhantarnilaian", getParam("txdTarikhHantarNilaian"));
			h.put("tarikhterimanilaian", getParam("txdTarikhTerimaNilaian"));

			// h.put("jenisborangC",getParam("jenisborangC"));
			h.put("keputusanpermohonan", getParam("keputusanpermohonan"));
			h.put("catatan", getParam("catatan"));

			h.put("namaSimati", getParam("namaSimati"));
			h.put("namaPemohon", getParam("namaPemohon"));

			h.put("jenisborangC", getParam("sorKeputusanBorangC"));

			// h.put("jenisborangC",getParam("sorPenentuanBidangKuasa"));

			String check = getParam("sorPenentuanBidangKuasa");
			String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");
			String ex = "";
			if (checkterus != "")// ((check=="53" || check=="50" ||
									// check=="63"))
			{
				ex = checkterus;
			} else // if((check=="1") && (checkterus=="151" ||
					// checkterus=="152"))
			{
				ex = check;
			}
			h.put("keputusanpermohonan", ex);

			h.put("jenis_pej", getParam("jenis_pej"));

			h.put("nofailawal", getParam("nofailawal"));
			h.put("pemohonawal", getParam("namapemohonawal"));
			h.put("pejabatawal", getParam("tempatmohonawal"));

			h.put("negerimahkamah", getParam("socNegeri"));
			h.put("daerahmahkamah", getParam("socDaerah"));

			h.put("id_Permohonansimati", getParam("idpermohonansimati"));

			// System.out.println("MMM EX"+ex);

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			vv.addElement(h);

			// System.out.println("PPP VV:"+vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			// System.out.println("jumHta"+getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode2", getParam("setMode2"));
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));
			this.context.put("checkJ4", getParam("checkJ4"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));
			this.context.put("viewcheckJ4", getParam("viewcheckJ4"));

			this.context.put("ViewPemohon", vv);

			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");
			// vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama.jsp";
		}

		else if ("getMahkamahAddressLamaPindah".equals(submit)) {

			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			Vector listDaerahByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListDaerahByPpkUnit(getParam("socNegeriPindah"));
			this.context.put("ListDaerahPindah", listDaerahByPpkUnit);

			String id = getParam("idPermohonan");
			String IdPermohonan = getParam("idPermohonan");
			this.context.put("idPermohonan", IdPermohonan);
			// String id = getParam("idPermohonan");
			FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			Vector list = FrmSenaraiFailKeputusanPermohonanInternalData
					.getData();
			this.context.put("ViewPemohon", list);
			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			String idflag = "1";
			this.context.put("idFlag", idflag);
			/*
			 * String idneg= getParam("socNegeri"); Long daerahId =
			 * Long.parseLong(getParam("socDaerah")); Long negId =
			 * Long.parseLong(getParam("socNegeri"));
			 */
			this.context.put("Senaraifail", listKeputusan);

			String idNegeri = getParam("socNegeriPindah");
			this.context.put("SocNegeri", idNegeri);

			String idDaerah = getParam("socDaerahPindah");
			this.context.put("SocDaerah", idDaerah);

			FrmSenaraiFailKeputusanPermohonanInternalData.setListNegeri();
			Vector listNegeri = FrmSenaraiFailKeputusanPermohonanInternalData
					.getListNegeri();
			this.context.put("ListNegeri", listNegeri);

			FrmSenaraiFailKeputusanPermohonanInternalData
					.setListDaerah(idNegeri);
			Vector listDaerah = FrmSenaraiFailKeputusanPermohonanInternalData
					.getListDaerah();
			this.context.put("ListDaerah", listDaerah);

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamah(id);
			Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamah();
			// System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
			this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);

			// view_mahkamah(session);
			// String idDaerah = getParam("socDaerah");
			// String idNegeri = getParam("socNegeri");
			FrmPrmhnnSek8KeputusanPermohonanInternalData.semakAlamatMahkamah(
					idDaerah, idNegeri);
			Vector dataMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getSemakMahkamah();
			this.context.put("infoMahkamah", dataMahkamah);

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamahMPindah();
			Vector listMaklumatMahkamahMKPindah = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahMPindah();
			this.context.put("listMaklumatMahkamahJPindah",
					listMaklumatMahkamahMKPindah);

			Hashtable h = null;
			h = new Hashtable();
			Vector vv = new Vector();

			h.put("idFail", getParam("idFail"));
			h.put("noFail", getParam("noFail"));
			h.put("idDaerah", getParam("idDaerah"));
			h.put("idPermohonan", getParam("idPermohonan"));
			h.put("tarikhMohon", getParam("tarikhMohon"));
			h.put("idnegeri", getParam("idnegeri"));
			h.put("namanegeri", getParam("namanegeri"));
			h.put("namadaerah", getParam("namadaerah"));
			h.put("seksyen", getParam("seksyen"));
			h.put("keterangan", getParam("keterangan"));
			h.put("id_Status", getParam("id_Status"));
			h.put("namaPejabat", getParam("namaPejabat"));

			h.put("jumHtaTarikhMohon", getParam("jumHtaTarikhMohon"));
			h.put("jumHtaTarikhMati", getParam("jumHtaTarikhMati"));
			h.put("jumHaTarikhMohon", getParam("jumHaTarikhMohon"));
			h.put("jumHaTarikhMati", getParam("jumHaTarikhMati"));
			h.put("jumHartaTarikhMohon", getParam("jumHartaTarikhMohon"));
			h.put("jumHartaTarikhMati", getParam("jumHartaTarikhMati"));

			/*
			 * h.put("tarikhborangB",getParam("txdTarikhHantarBorangB"));
			 * h.put("tarikhborangC",getParam("txdTarikhTerimaBorangC"));
			 * h.put("tarikhhantarnilaian",getParam("txdTarikhHantarNilaian"));
			 * h.put("tarikhterimanilaian", getParam("txdTarikhHantarNilaian"));
			 */

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
			h.put("kep_BrgC", getParam("kep_brgC"));
			h.put("res_BrgC", getParam("res_brgC"));
			h.put("sCL", getParam("sec_codeLink"));

			h.put("tarikhhantarnilaian", getParam("txdTarikhHantarNilaian"));
			h.put("tarikhterimanilaian", getParam("txdTarikhTerimaNilaian"));

			// h.put("jenisborangC",getParam("jenisborangC"));
			h.put("keputusanpermohonan", getParam("keputusanpermohonan"));
			h.put("catatan", getParam("catatan"));

			h.put("namaSimati", getParam("namaSimati"));
			h.put("namaPemohon", getParam("namaPemohon"));

			h.put("jenisborangC", getParam("sorKeputusanBorangC"));

			// h.put("jenisborangC",getParam("sorPenentuanBidangKuasa"));

			String check = getParam("sorPenentuanBidangKuasa");
			String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");
			String ex = "";
			if (checkterus != "")// ((check=="53" || check=="50" ||
									// check=="63"))
			{
				ex = checkterus;
			} else // if((check=="1") && (checkterus=="151" ||
					// checkterus=="152"))
			{
				ex = check;
			}
			h.put("keputusanpermohonan", ex);

			h.put("jenis_pej", getParam("jenis_pej"));

			h.put("nofailawal", getParam("nofailawal"));
			h.put("pemohonawal", getParam("namapemohonawal"));
			h.put("pejabatawal", getParam("tempatmohonawal"));

			h.put("negeritertinggi", getParam("socNegeriPindah"));
			h.put("daerahtertinggi", getParam("socDaerahPindah"));

			h.put("id_Permohonansimati", getParam("idpermohonansimati"));

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			// System.out.println("MMM EX"+ex);

			vv.addElement(h);

			// System.out.println("PPP VV:"+vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			// System.out.println("jumHta"+getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode2", getParam("setMode2"));
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));
			this.context.put("setMode99", getParam("setMode99"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));
			this.context.put("checkJ4", getParam("checkJ4"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));
			this.context.put("viewcheckJ4", getParam("viewcheckJ4"));

			this.context.put("ViewPemohon", vv);

			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");
			this.context.put("daerahpindah", "0");
			// vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama.jsp";
		}

		else if ("getSimpanMahkamah".equals(submit)) {

			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String IdPermohonan = getParam("idPermohonan");
			String id = getParam("idPermohonan");

			this.context.put("idPermohonan", IdPermohonan);
			check_id(session);
			Vector cntData = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getSemakId();
			Hashtable h = (Hashtable) cntData.get(0);
			String cntResult = h.get("cntid").toString();
			if (cntResult.equals("0")) {
				insertMahkamah(session);
			} else {
				updateMahkamah(session);
			}

			// String id = getParam("idPermohonan");
			FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			Vector list = FrmSenaraiFailKeputusanPermohonanInternalData
					.getData();
			this.context.put("ViewPemohon", list);
			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamah(id);
			Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamah();
			// System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
			this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);
			String idneg = getParam("socNegeri");
			Long daerahId = Long.parseLong(getParam("socDaerah"));
			Long negId = Long.parseLong(getParam("socNegeri"));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
					negId, "onChange=getDaerah()"));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idneg,
					"socDaerah", daerahId, "", "onChange=getAddress()"));
			vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";
		} else if ("getSimpan_keputusan".equals(submit)) {
			System.out.println("-------getSimpan_keputusan0.00000----");
			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			check_id(session);
			Vector cntData = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getSemakId();
			Hashtable h = (Hashtable) cntData.get(0);
			String cntResult = h.get("cntid").toString();

			// System.out.println("cntid" + cntResult);
			String ARB = getParam("ARB");
			String id = getParam("idPermohonan");
			
			String checkSD = getParam("sorPenentuanBidangKuasa");
			System.out.println("checkSD = "+checkSD);
			if (bolehsimpan.equals("yes")) {
				System.out.println("-------getSimpan_keputusan0----");
				if (cntResult.equals("0")) {
					System.out.println("-------getSimpan_keputusan1----");
					System.out.println("-------ARB----"+ARB);
					insertBorang(session,checkSD);
					/*if((checkSD=="107")) {
						insertSDTable(session);
					}*/
					
					if (ARB == "YES")
						{
							checkdulusamadaARBtelahwujudataubelumInsert(session);
						//insertARB(session);
						}
					
				} else {
					/*if("107".equals(checkSD)) {
					insertSDTable(session);
				}
				else
				{*/ 
				System.out.println("-------getSimpan_keputusan2----");
				System.out.println("-------ARB----"+ARB);
				check_idkeputusan(session);
				Vector checkIdKeputusan = FrmPrmhnnSek8KeputusanPermohonanInternalData
						.getSemakIdKeputusan();
				Hashtable j = (Hashtable) checkIdKeputusan.get(0);
				String idKeputusanPermohonan = j.get("cntid").toString();
				System.out.println("-------idKeputusanPermohonan----"+idKeputusanPermohonan);
				
				check_tblppksd(idKeputusanPermohonan);
				Vector checktblppksd = FrmPrmhnnSek8KeputusanPermohonanInternalData
						.getsemaktblppksd();
				Hashtable k = (Hashtable) checktblppksd.get(0);
				String cntResultppksd = k.get("cntid").toString();
				
				System.out.println("-------cntResultppksd----"+cntResultppksd);
				
				updateBorang(session,checkSD,idKeputusanPermohonan,cntResultppksd);
				if (ARB.equals("YES"))
					{
						System.out.println("-------ARB keputusan2----"+ARB);
						checkdulusamadaARBtelahwujudataubelumUpdate(session);
					}
				//insertARB(session);
				//updateARB(session);
				//}
			}
			}
			view_mode(session);

			// String id = getParam("idPermohonan");
			FrmPrmhnnSek8KeputusanPermohonanInternalData.setDataKeputusan(id);
			int eventStatus = 1;
			this.context.put("EventStatus", eventStatus);

			FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			Vector list = FrmSenaraiFailKeputusanPermohonanInternalData
					.getData();
			this.context.put("ViewPemohon", list);

			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamah(id);
			Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamah();
			// System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
			this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan.jsp";
		} else if ("getSimpan_keputusanLama".equals(submit)) {
			System.out.println("-------getSimpan_keputusan1110.00000----");
			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			check_id(session);
			Vector cntData = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getSemakId();
			Hashtable h = (Hashtable) cntData.get(0);
			String cntResult = h.get("cntid").toString();
			String id = getParam("idPermohonan");
			if (bolehsimpan.equals("yes")) {
				if (cntResult.equals("0")) {
					insertBorangLama(session);
				} else {
					updateBorangLama(session);
				}
			}
			view_mode(session);

			// String id = getParam("idPermohonan");
			FrmPrmhnnSek8KeputusanPermohonanInternalData.setDataKeputusan(id);
			int eventStatus = 1;
			this.context.put("EventStatus", eventStatus);

			FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			Vector list = FrmSenaraiFailKeputusanPermohonanInternalData
					.getData();
			this.context.put("ViewPemohon", list);

			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamah(id);
			Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamah();
			// System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
			this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamahMPindah();
			Vector listMaklumatMahkamahMKPindah = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahMPindah();
			this.context.put("listMaklumatMahkamahJPindah",
					listMaklumatMahkamahMKPindah);

			this.context.put("daerahpindah", "0");

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama.jsp";
		}

		else if ("getCari".equals(submit)) {
			readability1 = " ";
			readability2 = "readonly";
			disability1 = "disabled";
			disability2 = "";
			String Carix = "2";
			this.context.put("carix", Carix);
			String noFail = getParam("txtNoFail");
			String namaPemohon = getParam("txtPemohon");
			String namaSimati = getParam("txtSimati");
			String icSimati = getParam("txtIcSimati");
			String JenisIc = getParam("jenisIc");
			String usid = (String) session.getAttribute("_ekptg_user_id");
			FrmSenaraiFailKeputusanPermohonanInternalData.setCarianFail(noFail,
					namaPemohon, namaSimati, icSimati, JenisIc, usid);
			Vector list1 = new Vector();
			list1 = FrmSenaraiFailKeputusanPermohonanInternalData.getList();
			int countList1 = list1.size();
			this.context.put("Senaraifail", list1);

			FrmPrmhnnSek8KeputusanPermohonanInternalData.setMaklumatMahkamahM();
			Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahM();
			this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahM);

			prepareItemForDisplay(session, list1, 25, "first");
			session.setAttribute("Senaraifail", list1);
			this.context.put("CountList1", countList1);

			vm = "app/ppk/frmSenaraiFailKeputusanPermohonan.jsp";
		}

		else if (("next".equals(submit)) || ("previous".equals(submit))) {
			// Carix = "1";
			String usid = "";
			String Carix = "2";
			this.context.put("carix", Carix);
			usid = (String) session.getAttribute("_ekptg_user_id");

			listKeputusan = (Vector) session.getAttribute("Senaraifail");
			this.context.put("Senaraifail", listKeputusan);
			prepareItemForDisplay(session, listKeputusan, 25, submit);
			int countList = listKeputusan.size();
			this.context.put("CountList", countList);

			vm = "app/ppk/frmSenaraiFailKeputusanPermohonan.jsp";
		} else {
			String usid = "";
			// usid="81"; //kelantan
			// session.setAttribute("_ekptg_user_id", usid);
			// this.context.put("usid",usid);
			usid = (String) session.getAttribute("_ekptg_user_id");
			this.context.put("usid", usid);

			FrmSenaraiFailKeputusanPermohonanInternalData
					.setListKeputusan(usid);
			listKeputusan = FrmSenaraiFailKeputusanPermohonanInternalData
					.getListKeputusan();
			String Carix = "2";
			this.context.put("carix", Carix);
			this.context.put("Senaraifail", listKeputusan);
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri"));

			prepareItemForDisplay(session, listKeputusan, 25, "first");
			session.setAttribute("Senaraifail", listKeputusan);

		}

		FrmSenaraiFailKeputusanPermohonanInternalData.setListNegeri();
		Vector listNegeri = FrmSenaraiFailKeputusanPermohonanInternalData
				.getListNegeri();
		this.context.put("ListNegeri", listNegeri);

		// ##
		Vector listnegeri = logic_A.getListnegeri();
		this.context.put("listnegeri", listnegeri);

		this.context.put("flagFromSenaraiPermohonanSek8",
				flagFromSenaraiPermohonanSek8);
		this.context.put("flagFromSenaraiFailSek8", flagFromSenaraiFailSek8);
		this.context.put("flagForView", flagForView);

		Template template = this.engine.getTemplate(vm);
		return template;
	}

	private void view_mode(HttpSession session) throws Exception {
		String id = getParam("idPermohonan");
		FrmPrmhnnSek8KeputusanPermohonanInternalData.setDataKeputusan(id);
	}

	private void check_id(HttpSession session) throws Exception {
		String id = getParam("idPermohonan");
		FrmPrmhnnSek8KeputusanPermohonanInternalData.semakId(id);
	}
	
	
	private void check_idkeputusan(HttpSession session) throws Exception {
		String id = getParam("idPermohonan");
		FrmPrmhnnSek8KeputusanPermohonanInternalData.semakIdKeputusan(id);
	}
	
	private void check_tblppksd(String idKeputusanPermohonan) throws Exception {
		
		FrmPrmhnnSek8KeputusanPermohonanInternalData.semaktblppksd(idKeputusanPermohonan);
	}
	
	

	private void view_mahkamah(HttpSession session) throws Exception {
		String idDaerah = getParam("socDaerah");
		String idNegeri = getParam("socNegeri");
		FrmPrmhnnSek8KeputusanPermohonanInternalData.semakAlamatMahkamah(
				idDaerah, idNegeri);
	}

	private void insertMahkamah(HttpSession session) throws Exception {
		Hashtable h = null;
		h = new Hashtable();
		h.put("idDaerah", getParam("socDaerah"));
		h.put("idNegeri", getParam("socNegeri"));
		h.put("IdPermohonan", getParam("idPermohonan"));
		h.put("id_Fail", getParam("id_Fail"));

		FrmPrmhnnSek8KeputusanPermohonanInternalData.insertDataMahkamah(h);
	}

	private void updateMahkamah(HttpSession session) throws Exception {
		Hashtable h = null;
		h = new Hashtable();
		h.put("idDaerah", getParam("socDaerah"));
		h.put("idNegeri", getParam("socNegeri"));
		h.put("IdPermohonan", getParam("idPermohonan"));
		h.put("id_Fail", getParam("id_Fail"));
		FrmPrmhnnSek8KeputusanPermohonanInternalData.updateDataMahkamah(h);
	}
	
	/*private void insertSDTable(HttpSession session) throws Exception {

		
		Hashtable h = null;
		h = new Hashtable();
		h.put("jenisSD", getParam("sorPenentuanBidangKuasa"));
		h.put("txtCatatanSD", getParam("txtCatatanSD"));
		h.put("IdPermohonan", getParam("idPermohonan"));
		h.put("userId", session.getAttribute("_ekptg_user_id"));
		h.put("id_Masuk", session.getAttribute("_ekptg_user_id"));
		h.put("id_Fail", getParam("id_Fail"));
		String uid = (String) session.getAttribute("_ekptg_user_id");
		FrmPrmhnnSek8KeputusanPermohonanInternalData.insertSDTable(session, h,"8");
		FrmPrmhnnSek8KptsanBicaraData.add_maklumatPerintah("99991111111",uid,"","","","","","","");
	}*/

	private void insertBorang(HttpSession session, String checkSD) throws Exception {

		String check = getParam("sorPenentuanBidangKuasa");
		String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");
		String tarikhsuratARB = getParam("tarikhsuratARB");
		String ex = "";
		if (checkterus != "")// ((check=="53" || check=="50" || check=="63"))
		{
			ex = checkterus;
		} else // if((check=="1") && (checkterus=="151" || checkterus=="152"))
		{
			ex = check;
		}

		Hashtable h = null;
		h = new Hashtable();
		h.put("tarikhHantarBorangB", getParam("txdTarikhHantarBorangB"));
		h.put("tarikhTerimaBorangC", getParam("txdTarikhTerimaBorangC"));
		h.put("tarikhHantarNilaian", getParam("txdTarikhHantarNilaian"));
		h.put("tarikhTerimaNilaian", getParam("txdTarikhTerimaNilaian"));
		h.put("keputusanBorangC", getParam("sorKeputusanBorangC"));
		h.put("salinanArahan", getParam("salinanArahan"));
		h.put("tarikhsuratARB", tarikhsuratARB);
		if (checkSD == "107")
		{
			h.put("penentuanBidangKuasa", "107");
		}
		else
		{
			h.put("penentuanBidangKuasa", ex);
		}

		h.put("catatan", getParam("txtCatatan"));
		h.put("IdPermohonan", getParam("idPermohonan"));
		h.put("txtCatatanSD", getParam("txtCatatanSD"));

		// id_sta 151,152
		h.put("nofailawal", getParam("nofailawal"));
		h.put("namapemohonawal", getParam("namapemohonawal"));
		h.put("tempatmohonawal", getParam("tempatmohonawal"));

		// pej_99
		h.put("jenis_pej", getParam("jenis_pej"));
		if (getParam("jenis_pej").equals("99")) {
			h.put("txtNamaKaveat", getParam("txtNamaKaveat"));
			h.put("txtNoKaveat", getParam("txtNoKaveat"));
			h.put("txtNamaFirma", getParam("txtNamaFirma"));
			h.put("txtAlamat1Peguam", getParam("txtAlamat1Peguam"));
			h.put("txtAlamat2Peguam", getParam("txtAlamat2Peguam"));
			h.put("txtAlamat3Peguam", getParam("txtAlamat3Peguam"));
			h.put("txtPoskodPeguam", getParam("txtPoskodPeguam"));
			h.put("socNegeriPeguam", getParam("socNegeriPeguam"));
			h.put("txtBandarPeguam", getParam("txtBandarPeguam"));
			h.put("txtNomborTelefonPeguam", getParam("txtNomborTelefonPeguam"));
			h.put("txtTarikhKaveat", getParam("txtTarikhKaveat"));

		} else {
			h.put("txtNamaKaveat", "");
			h.put("txtNoKaveat", "");
			h.put("txtNamaFirma", "");
			h.put("txtAlamat1Peguam", "");
			h.put("txtAlamat2Peguam", "");
			h.put("txtAlamat3Peguam", "");
			h.put("txtPoskodPeguam", "");
			h.put("socNegeriPeguam", "");
			h.put("txtBandarPeguam", "");
			h.put("txtNomborTelefonPeguam", "");
			h.put("txtTarikhKaveat", "");

		}
		// id_sta 50
		h.put("idDaerah", getParam("socDaerah"));
		h.put("idNegeri", getParam("socNegeri"));
		h.put("tujuanPindah", getParam("tujuanPindah")); //razman add
		
		
		h.put("userId", session.getAttribute("_ekptg_user_id"));
		h.put("id_Suburusanstatus", getParam("id_Suburusanstatus"));
		h.put("id_Suburusanstatusfail", getParam("id_Suburusanstatusfail"));

		h.put("id_Fail", getParam("id_Fail"));

		h.put("id_Masuk", session.getAttribute("_ekptg_user_id"));

		FrmPrmhnnSek8KeputusanPermohonanInternalData.insertBorang(session, h,"8");
	}
	

	//update ARB. Start.
	private void updateARB(HttpSession session) throws Exception {
		System.out.println("-------Read Here7----");
		
		Hashtable h = new Hashtable();

		h.put("idSimati", getParam("idSimati"));
		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));
		h.put("namaOB", "AMANAH RAYA BERHAD");
		// String id_Permohonansimati =
		// Integer.parseInt(getParam("id_Permohonansimati"));

		//String kp_penting = getParam("txtNoKPBaru1Penting")
		//		+ getParam("txtNoKPBaru2Penting")
		//		+ getParam("txtNoKPBaru3Penting");

		h.put("nokpbaru", "");
		h.put("nokppenting", "");
		h.put("jenisKp", "");
		h.put("nokplain", "");
		h.put("statusOB", "");
		h.put("taraf", 6);

		// h.put("taraf",
		// Integer.parseInt(getParam("socTarafKepentinganPenting")));
		h.put("jantina", "");
		h.put("agama", "");
		h.put("warga", "");
		h.put("umur", "");

		h.put("dob", "");
		h.put("noberanak", "");
		h.put("alamat1", getParam("txtAlamatTerakhir1Penting"));
		h.put("alamat2", getParam("txtAlamatTerakhir2Penting"));
		h.put("alamat3", getParam("txtAlamatTerakhir3Penting"));
		h.put("poskod", getParam("txtPoskodPenting"));
		//System.out.println("-------Baca txtIdBandarPenting ----" + Integer.parseInt(getParam("txtIdBandarPenting")));
		//h.put("idbandar", "");
		h.put("idbandar", Integer.parseInt(getParam("txtIdBandarPenting")));
		//h.put("negeri", "");
		h.put("negeri", Integer.parseInt(getParam("txtIdNegeriPenting")));

		// h.put("negeri", Integer.parseInt(getParam("socNegeriPenting")));
		h.put("catatan", getParam("txtCatatanPenting"));
		h.put("FLAG_DAFTAR", "1");
		h.put("catatanhutang", "");

		h.put("id_Masuk", session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);

		h.put("alamat1Surat", getParam("txtAlamatTerakhir1Penting"));
		h.put("alamat2Surat", getParam("txtAlamatTerakhir2Penting"));
		h.put("alamat3Surat", getParam("txtAlamatTerakhir3Penting"));
		h.put("poskodSurat", getParam("txtPoskodPenting"));
		h.put("idbandarSurat", Integer.parseInt(getParam("txtIdBandarPenting")));
		h.put("negeriSurat", Integer.parseInt(getParam("txtIdNegeriPenting")));
		h.put("notel", getParam("txtNoTeleponPenting"));
		h.put("jenishutang", 1);
		h.put("noakaun", "");
		h.put("nilaihutang", 0.0);
 
		// baruOB
		h.put("jenis_pej", getParam("jenis_pej"));
		h.put("no_fax", getParam("txtNoFaxPenting"));
		h.put("jenis_pemohon", getParam("jenis_pemohon"));
		h.put("no_hp", getParam("txtNoHpPenting"));
		h.put("nama_warga", "");
		String salinanArahan = getParam("salinanArahan");
		if (salinanArahan.equals(""))
		{
		h.put("salinan_arahan", "0");
		}
		if (salinanArahan.equals("1"))
		{
		h.put("salinan_arahan", "1");
		}
		logic_internal.updatePenting(h);
		
	}
	//Add ARB sebagai OB. Start.	
	private void insertARB(HttpSession session) throws Exception {
		System.out.println("-------Read Here6----");
		
		Hashtable h = new Hashtable();

		h.put("idSimati", getParam("idSimati"));
		
		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));
		System.out.println("%%@%%@ id_Permohonansimati %%@@% :"+ getParam("id_Permohonansimati"));
		h.put("namaOB", "AMANAH RAYA BERHAD");
		// String id_Permohonansimati =
		// Integer.parseInt(getParam("id_Permohonansimati"));

		//String kp_penting = getParam("txtNoKPBaru1Penting")
		//		+ getParam("txtNoKPBaru2Penting")
		//		+ getParam("txtNoKPBaru3Penting");

		h.put("nokpbaru", "");
		h.put("nokppenting", "");
		h.put("jenisKp", "");
		h.put("nokplain", "");
		h.put("statusOB", "");
		h.put("taraf", 6);

		// h.put("taraf",
		// Integer.parseInt(getParam("socTarafKepentinganPenting")));
		h.put("jantina", "");
		h.put("agama", "");
		h.put("warga", "");
		h.put("umur", "");

		h.put("dob", "");
		h.put("noberanak", "");
		h.put("alamat1", getParam("txtAlamatTerakhir1Penting"));
		h.put("alamat2", getParam("txtAlamatTerakhir2Penting"));
		h.put("alamat3", getParam("txtAlamatTerakhir3Penting"));
		h.put("poskod", getParam("txtPoskodPenting"));
		h.put("idbandar", getParam("txtIdBandarPenting"));
		h.put("negeri", Integer.parseInt(getParam("txtIdNegeriPenting")));

		// h.put("negeri", Integer.parseInt(getParam("socNegeriPenting")));
		h.put("catatan", getParam("txtCatatanPenting"));
		h.put("FLAG_DAFTAR", "1");
		h.put("catatanhutang", "");

		h.put("id_Masuk", session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);

		h.put("alamat1Surat", getParam("txtAlamatTerakhir1Penting"));
		h.put("alamat2Surat", getParam("txtAlamatTerakhir2Penting"));
		h.put("alamat3Surat", getParam("txtAlamatTerakhir3Penting"));
		h.put("poskodSurat", getParam("txtPoskodPenting"));
		h.put("idbandarSurat", getParam("txtIdBandarPenting"));
		h.put("negeriSurat", Integer.parseInt(getParam("txtIdNegeriPenting")));
		h.put("notel", getParam("txtNoTeleponPenting"));
		h.put("jenishutang", 1);
		h.put("noakaun", "");
		h.put("nilaihutang", 0.0);

		// baruOB
		h.put("jenis_pej", getParam("jenis_pej"));
		h.put("no_fax", getParam("txtNoFaxPenting"));
		h.put("jenis_pemohon", getParam("jenis_pemohon"));
		h.put("no_hp", getParam("txtNoHpPenting"));
		h.put("nama_warga", "");

		logic_internal.addPenting(h);
		
	}
	
//Add ARB sebagai OB. End.
	
	
	private void checkdulusamadaARBtelahwujudataubelumInsert(HttpSession session) throws Exception {
		System.out.println("-------checkdulusamadaARBtelahwujudataubelum----");
		
		Hashtable h = new Hashtable();

		h.put("idSimati", getParam("idSimati"));
		h.put("namaOB", "AMANAH RAYA BERHAD");
		Vector cntData = FrmPrmhnnSek8KeputusanPermohonanInternalData
				.checkdulusamadaARBtelahwujudataubelum(h);
		int cntResult = cntData.size();
		System.out.println("-------cntResult----" + cntResult);
		if (((cntResult == 0)))
		{	
			System.out.println("-------checkdulusamadaARBtelahwujudataubelum2----");
			insertARB(session);
		}
				
	}
	
	private void checkdulusamadaARBtelahwujudataubelumUpdate(HttpSession session) throws Exception {
		System.out.println("-------checkdulusamadaARBtelahwujudataubelum----");
		
		Hashtable h = new Hashtable();

		h.put("idSimati", getParam("idSimati"));
		h.put("namaOB", "AMANAH RAYA BERHAD");
		Vector cntData = FrmPrmhnnSek8KeputusanPermohonanInternalData
				.checkdulusamadaARBtelahwujudataubelum(h);
		int cntResult = cntData.size();
		System.out.println("-------cntResult----" + cntResult);
		if (((cntResult == 0)))
		{	
			insertARB(session);
		}
				
	}


	
	private void insertBorangLama(HttpSession session) throws Exception {

		String check = getParam("sorPenentuanBidangKuasa");
		String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");
		String ex = "";
		if (checkterus != "")// ((check=="53" || check=="50" || check=="63"))
		{
			ex = checkterus;
		} else // if((check=="1") && (checkterus=="151" || checkterus=="152"))
		{
			ex = check;
		}

		Hashtable h = null;
		h = new Hashtable();
		h.put("tarikhHantarBorangB", getParam("txdTarikhHantarBorangB"));
		h.put("tarikhTerimaBorangC", getParam("txdTarikhTerimaBorangC"));
		h.put("tarikhHantarNilaian", getParam("txdTarikhHantarNilaian"));
		h.put("tarikhTerimaNilaian", getParam("txdTarikhTerimaNilaian"));
		h.put("keputusanBorangC", getParam("sorKeputusanBorangC"));
		h.put("penentuanBidangKuasa", ex);
		// h.put("penentuanBidangKuasaTeruskan",
		// getParam("sorPenentuanBidangKuasaTeruskan"));
		h.put("catatan", getParam("txtCatatan"));
		h.put("IdPermohonan", getParam("idPermohonan"));

		// System.out.println("nofailawal:"+getParam("nofailawal"));
		// System.out.println("namapemohonawal:"+getParam("namapemohonawal"));
		// System.out.println("tempatmohonawal:"+getParam("tempatmohonawal"));
		// System.out.println("socDaerah:"+getParam("socDaerah"));
		// System.out.println("socNegeri:"+getParam("socNegeri"));

		// id_sta 151,152
		h.put("nofailawal", getParam("nofailawal"));
		h.put("namapemohonawal", getParam("namapemohonawal"));
		h.put("tempatmohonawal", getParam("tempatmohonawal"));

		// id_sta 50
		h.put("idDaerah", getParam("socDaerah"));
		h.put("idNegeri", getParam("socNegeri"));

		h.put("idDaerahPindah", getParam("socDaerahPindah"));
		h.put("idNegeriPindah", getParam("socNegeriPindah"));
		
		h.put("tujuanPindah", getParam("tujuanPindah")); //razman add

		h.put("jenis_pej", getParam("jenis_pej"));
		if (getParam("jenis_pej").equals("99")) {
			h.put("txtNamaKaveat", getParam("txtNamaKaveat"));
			h.put("txtNoKaveat", getParam("txtNoKaveat"));
			h.put("txtNamaFirma", getParam("txtNamaFirma"));
			h.put("txtAlamat1Peguam", getParam("txtAlamat1Peguam"));
			h.put("txtAlamat2Peguam", getParam("txtAlamat2Peguam"));
			h.put("txtAlamat3Peguam", getParam("txtAlamat3Peguam"));
			h.put("txtPoskodPeguam", getParam("txtPoskodPeguam"));
			h.put("socNegeriPeguam", getParam("socNegeriPeguam"));
			h.put("txtBandarPeguam", getParam("txtBandarPeguam"));
			h.put("txtNomborTelefonPeguam", getParam("txtNomborTelefonPeguam"));
			h.put("txtTarikhKaveat", getParam("txtTarikhKaveat"));

		} else {
			h.put("txtNamaKaveat", "");
			h.put("txtNoKaveat", "");
			h.put("txtNamaFirma", "");
			h.put("txtAlamat1Peguam", "");
			h.put("txtAlamat2Peguam", "");
			h.put("txtAlamat3Peguam", "");
			h.put("txtPoskodPeguam", "");
			h.put("socNegeriPeguam", "");
			h.put("txtBandarPeguam", "");
			h.put("txtNomborTelefonPeguam", "");
			h.put("txtTarikhKaveat", "");
		}

		h.put("userId", session.getAttribute("_ekptg_user_id"));
		h.put("id_Suburusanstatus", getParam("id_Suburusanstatus"));
		h.put("id_Suburusanstatusfail", getParam("id_Suburusanstatusfail"));

		h.put("id_Fail", getParam("id_Fail"));

		h.put("id_Masuk", session.getAttribute("_ekptg_user_id"));
		FrmPrmhnnSek8KeputusanPermohonanInternalData.insertBorangLama(session,
				h,"8");
	}

	private void updateBorang(HttpSession session, String checkSD, String idKeputusanPermohonan, String cntResultppksd) throws Exception {
		// System.out.println("1111111 up:::"+getParam("sorPenentuanBidangKuasa"));
		// System.out.println("2222222 up:::"+getParam("sorPenentuanBidangKuasaTeruskan"));

		String check = getParam("sorPenentuanBidangKuasa");
		String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");

		String ex = "";

		String d = "";
		String n = "";
		String nf = "";
		String pf = "";
		String tf = "";
		
		

		if (checkterus != "")// ((check=="53" || check=="50" || check=="63"))
		{
			ex = checkterus;

		} else // if((check=="1") && (checkterus=="151" || checkterus=="152"))
		{
			ex = check;

		}

		// System.out.println("CHECKKKup123:::"+ex);

		Hashtable h = null;
		h = new Hashtable();
		
    	myLogger.info("tarikhsuratARB = "+getParam("tarikhsuratARB"));
    	h.put("tarikhsuratARB", getParam("tarikhsuratARB"));
		h.put("tarikhHantarBorangB", getParam("txdTarikhHantarBorangB"));
		h.put("tarikhTerimaBorangC", getParam("txdTarikhTerimaBorangC"));
		h.put("tarikhHantarNilaian", getParam("txdTarikhHantarNilaian"));
		h.put("tarikhTerimaNilaian", getParam("txdTarikhTerimaNilaian"));
		h.put("keputusanBorangC", getParam("sorKeputusanBorangC"));
		h.put("penentuanBidangKuasa", ex);
		// h.put("penentuanBidangKuasaTeruskan",
		// getParam("sorPenentuanBidangKuasaTeruskan"));
		h.put("catatan", getParam("txtCatatan"));
		h.put("IdPermohonan", getParam("idPermohonan"));
		if (checkSD == "107")
		{
			h.put("penentuanBidangKuasa", "107");
		}
		else
		{
			h.put("penentuanBidangKuasa", ex);
		}

		// System.out.println("nofailawal:"+getParam("nofailawal"));
		// System.out.println("namapemohonawal:"+getParam("namapemohonawal"));
		// System.out.println("tempatmohonawal:"+getParam("tempatmohonawal"));
		// System.out.println("socDaerah:"+getParam("socDaerah"));
		// System.out.println("socNegeri:"+getParam("socNegeri"));

		// id_sta 151,152
		h.put("nofailawal", getParam("nofailawal"));
		h.put("namapemohonawal", getParam("namapemohonawal"));
		h.put("tempatmohonawal", getParam("tempatmohonawal"));
		h.put("tarikhTerimaSuratAkuan", getParam("salinanArahan2"));
		myLogger.info("Salinan arahan = " + getParam("salinanArahan2"));
		h.put("txtCatatanSD", getParam("txtCatatanSD"));
		// id_sta 50
		h.put("idDaerah", getParam("socDaerah"));
		h.put("idNegeri", getParam("socNegeri"));
		h.put("idKeputusanPermohonan", idKeputusanPermohonan);
		h.put("cntResultppksd", cntResultppksd);
		
		h.put("tujuanPindah", getParam("tujuanPindah"));//razman add


		// System.out.println("daerah :"+getParam("socDaerah")+"negeri :"+getParam("socNegeri"));

		h.put("jenis_pej", getParam("jenis_pej"));
		if (getParam("jenis_pej").equals("99")) {
			h.put("txtNamaKaveat", getParam("txtNamaKaveat"));
			h.put("txtNoKaveat", getParam("txtNoKaveat"));
			h.put("txtNamaFirma", getParam("txtNamaFirma"));
			h.put("txtAlamat1Peguam", getParam("txtAlamat1Peguam"));
			h.put("txtAlamat2Peguam", getParam("txtAlamat2Peguam"));
			h.put("txtAlamat3Peguam", getParam("txtAlamat3Peguam"));
			h.put("txtPoskodPeguam", getParam("txtPoskodPeguam"));
			h.put("socNegeriPeguam", getParam("socNegeriPeguam"));
			h.put("txtBandarPeguam", getParam("txtBandarPeguam"));
			h.put("txtNomborTelefonPeguam", getParam("txtNomborTelefonPeguam"));
			h.put("txtTarikhKaveat", getParam("txtTarikhKaveat"));

		} else {
			h.put("txtNamaKaveat", "");
			h.put("txtNoKaveat", "");
			h.put("txtNamaFirma", "");
			h.put("txtAlamat1Peguam", "");
			h.put("txtAlamat2Peguam", "");
			h.put("txtAlamat3Peguam", "");
			h.put("txtPoskodPeguam", "");
			h.put("socNegeriPeguam", "");
			h.put("txtBandarPeguam", "");
			h.put("txtNomborTelefonPeguam", "");
			h.put("txtTarikhKaveat", "");
		}

		h.put("userId", session.getAttribute("_ekptg_user_id"));
		h.put("id_Suburusanstatus", getParam("id_Suburusanstatus"));
		h.put("id_Suburusanstatusfail", getParam("id_Suburusanstatusfail"));
		h.put("id_Fail", getParam("id_Fail"));
		h.put("id_Kemaskini", session.getAttribute("_ekptg_user_id"));
		String salinan_arahan = getParam("salinanArahan");
		if (salinan_arahan.equals("on"))
		{
			salinan_arahan = "1";
		}
		h.put("salinan_arahan", salinan_arahan);
		//myLogger.info("salinan_arahan:::"+salinan_arahan);
		
		FrmPrmhnnSek8KeputusanPermohonanInternalData.updateBorang(session, h,"8");
	}

	private void updateBorangLama(HttpSession session) throws Exception {
		// System.out.println("1111111 up:::"+getParam("sorPenentuanBidangKuasa"));
		// System.out.println("2222222 up:::"+getParam("sorPenentuanBidangKuasaTeruskan"));

		String check = getParam("sorPenentuanBidangKuasa");
		String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");

		String ex = "";

		String d = "";
		String n = "";
		String nf = "";
		String pf = "";
		String tf = "";

		if (checkterus != "")// ((check=="53" || check=="50" || check=="63"))
		{
			ex = checkterus;

		} else // if((check=="1") && (checkterus=="151" || checkterus=="152"))
		{
			ex = check;

		}

		// System.out.println("CHECKKKup123:::"+ex);

		Hashtable h = null;
		h = new Hashtable();
		h.put("tarikhHantarBorangB", getParam("txdTarikhHantarBorangB"));
		h.put("tarikhTerimaBorangC", getParam("txdTarikhTerimaBorangC"));
		h.put("tarikhHantarNilaian", getParam("txdTarikhHantarNilaian"));
		h.put("tarikhTerimaNilaian", getParam("txdTarikhTerimaNilaian"));
		h.put("keputusanBorangC", getParam("sorKeputusanBorangC"));
		h.put("penentuanBidangKuasa", ex);
		// h.put("penentuanBidangKuasaTeruskan",
		// getParam("sorPenentuanBidangKuasaTeruskan"));
		h.put("catatan", getParam("txtCatatan"));
		h.put("IdPermohonan", getParam("idPermohonan"));

		// System.out.println("nofailawal:"+getParam("nofailawal"));
		// System.out.println("namapemohonawal:"+getParam("namapemohonawal"));
		// System.out.println("tempatmohonawal:"+getParam("tempatmohonawal"));
		// System.out.println("socDaerah:"+getParam("socDaerah"));
		// System.out.println("socNegeri:"+getParam("socNegeri"));

		// id_sta 151,152
		h.put("nofailawal", getParam("nofailawal"));
		h.put("namapemohonawal", getParam("namapemohonawal"));
		h.put("tempatmohonawal", getParam("tempatmohonawal"));

		// id_sta 50
		h.put("idDaerah", getParam("socDaerah"));
		h.put("idNegeri", getParam("socNegeri"));

		h.put("idDaerahPindah", getParam("socDaerahPindah"));
		h.put("idNegeriPindah", getParam("socNegeriPindah"));
		
		h.put("tujuanPindah", getParam("tujuanPindah"));//razman add

		h.put("jenis_pej", getParam("jenis_pej"));
		if (getParam("jenis_pej").equals("99")) {
			h.put("txtNamaKaveat", getParam("txtNamaKaveat"));
			h.put("txtNoKaveat", getParam("txtNoKaveat"));
			h.put("txtNamaFirma", getParam("txtNamaFirma"));
			h.put("txtAlamat1Peguam", getParam("txtAlamat1Peguam"));
			h.put("txtAlamat2Peguam", getParam("txtAlamat2Peguam"));
			h.put("txtAlamat3Peguam", getParam("txtAlamat3Peguam"));
			h.put("txtPoskodPeguam", getParam("txtPoskodPeguam"));
			h.put("socNegeriPeguam", getParam("socNegeriPeguam"));
			h.put("txtBandarPeguam", getParam("txtBandarPeguam"));
			h.put("txtNomborTelefonPeguam", getParam("txtNomborTelefonPeguam"));
			h.put("txtTarikhKaveat", getParam("txtTarikhKaveat"));

		} else {
			h.put("txtNamaKaveat", "");
			h.put("txtNoKaveat", "");
			h.put("txtNamaFirma", "");
			h.put("txtAlamat1Peguam", "");
			h.put("txtAlamat2Peguam", "");
			h.put("txtAlamat3Peguam", "");
			h.put("txtPoskodPeguam", "");
			h.put("socNegeriPeguam", "");
			h.put("txtBandarPeguam", "");
			h.put("txtNomborTelefonPeguam", "");
			h.put("txtTarikhKaveat", "");

		}

		// System.out.println("daerah :"+getParam("socDaerah")+"negeri :"+getParam("socNegeri"));

		h.put("userId", session.getAttribute("_ekptg_user_id"));
		h.put("id_Suburusanstatus", getParam("id_Suburusanstatus"));
		h.put("id_Suburusanstatusfail", getParam("id_Suburusanstatusfail"));
		h.put("id_Fail", getParam("id_Fail"));
		h.put("id_Kemaskini", session.getAttribute("_ekptg_user_id"));
		h.put("salinan_arahan", getParam("salinanArahan"));

		FrmPrmhnnSek8KeputusanPermohonanInternalData.updateBorangLama(session,
				h,"8");
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
				// System.out.println("INT ::"+i);
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

	public void deleteSuppDoc(String idSimati, String jenisDoc) throws Exception 
	{
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " DELETE FROM TBLPPKDOKUMENSIMATI WHERE ID_SIMATI = '"+idSimati+"' AND ID_JENISDOKUMEN = '"+jenisDoc+"'";
			myLogger.info("sql1 >>> "+sql);
			stmt.executeUpdate(sql);
			
			
			conn.commit();

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	
	
}
	
	private void headerppk_baru(HttpSession session, String id_permohonan,
			String flag_permohonan, String id_fail, String flag_fail)
			throws Exception {
		// hashtable maklumat header
		this.context.put("headerppk", mainheader.getHeaderData(session,
				id_permohonan, flag_permohonan, id_fail, flag_fail));
		Vector list_sub = null;
		list_sub = mainheader.carianFail(id_permohonan, flag_permohonan,
				id_fail, flag_fail);
		this.context.put("list_sub_header", list_sub);
		this.context.put("flag_jenis_vm", "vtemplate");
	}

	private void headerppk_baru_default() {
		Hashtable headerppk = null;
		this.context.put("headerppk", "");
		this.context.put("list_sub_header", "");
		this.context.put("flag_jenis_vm", "vtemplate");
	}

}
