/**
 *
 */
package ekptg.view.ppk;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8KeputusanPermohonanInternalData;
import ekptg.model.ppk.FrmPrmhnnSek8SenaraiHTATHInternalData;
import ekptg.model.ppk.FrmPrmhnnSek8SenaraiSemakInternalData;
import ekptg.model.ppk.FrmSenaraiFailInternalCarianData;
import ekptg.model.ppk.FrmSenaraiFailInternalData;
import ekptg.model.ppk.FrmSenaraiFailKeputusanPermohonanInternalData;

/**
 * @author razman *
 */

public class FrmSenaraiFailKeputusanPermohonanInternal17 extends VTemplate {
	private static final long serialVersionUID = 1L;

	FrmPrmhnnSek8KeputusanPermohonanInternalData logicKeputusanPrmhnn = null;
	FrmSenaraiFailKeputusanPermohonanInternalData logicKeputusan = null;

	FrmPrmhnnSek8InternalData logic = null;
	FrmPrmhnnSek8DaftarSek8InternalData logic_A = null;
	FrmPrmhnnSek8SenaraiHTATHInternalData logic_B = null;
	FrmPrmhnnSek8SenaraiSemakInternalData logic_C = null;
	FrmSenaraiFailInternalCarianData logic_D = null;
	FrmSenaraiFailInternalData logic_E = null;
	FrmHeaderPpk mainheader = null;
	
	

	@SuppressWarnings("unchecked")
	public Template doTemplate() throws Exception {

		logicKeputusanPrmhnn = new FrmPrmhnnSek8KeputusanPermohonanInternalData();
		logicKeputusan = new FrmSenaraiFailKeputusanPermohonanInternalData();

		logic = new FrmPrmhnnSek8InternalData();
		logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
		logic_B = new FrmPrmhnnSek8SenaraiHTATHInternalData();
		logic_C = new FrmPrmhnnSek8SenaraiSemakInternalData();
		logic_D = new FrmSenaraiFailInternalCarianData();
		logic_E = new FrmSenaraiFailInternalData();
		mainheader = new FrmHeaderPpk();
		this.context.put("seksyen_kp","17");
		

		HttpSession session = this.request.getSession();
		String submit = "";
		submit = getParam("command");
		
		headerppk_baru_default();

		String bolehsimpan = "";
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			//System.out.println("browser refresh **********");
			bolehsimpan = "yes";
			// do what ever approriate action
		}

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

		FrmSenaraiFailKeputusanPermohonanInternalData.setListDaerahall();
		Vector listDaerahall = FrmSenaraiFailKeputusanPermohonanInternalData
				.getListDaerahall();
		this.context.put("ListDaerah", listDaerahall);

		String v_tab = getParam("v_tab");
		//System.out.print("V_TAB" + v_tab + ";");

		this.context.put("val_tab", v_tab);

		String v_loc = getParam("v_loc");
		//System.out.print("V_LOC" + v_loc + ";");

		this.context.put("val_loc", v_loc);

		this.context.put("ListNegeriPindah", "");
		this.context.put("ListNegeriPindah", "");
		this.context.put("listMaklumatMahkamahJPindah", "");

		this.context.put("daerahpindah", "");

		Vector listHTAX = new Vector();
		Vector listHTAXdulu = new Vector();
		Vector listHTA = new Vector();
		Vector listHTAdulu = new Vector();
		Vector listppkha = new Vector();
		Vector listppkhadulu = new Vector();

		this.context.put("listHTAX", "");
		this.context.put("listHTAXdulu", "");
		this.context.put("listHTA", "");
		this.context.put("listHTAdulu", "");
		this.context.put("listHa", "");
		this.context.put("listHadulu", "");


		if(getParam("idPermohonan")!=""){
			Vector list_status_nilai = new Vector();
			String id_per = getParam("idPermohonan");
			FrmSenaraiFailKeputusanPermohonanInternalData.setlist_status_nilai(id_per,"432");
			list_status_nilai = FrmSenaraiFailKeputusanPermohonanInternalData.getlist_status_nilai();
			this.context.put("list_status_nilai", list_status_nilai);
		/*
			Vector list_status_tunggu = new Vector();
			//String id_per = getParam("idPermohonan");
			FrmSenaraiFailKeputusanPermohonanInternalData.setlist_status_tunggu (id_per,553);
			list_status_tunggu  = FrmSenaraiFailKeputusanPermohonanInternalData.getlist_status_tunggu();
			this.context.put("list_status_tunggu", list_status_tunggu );
			*/
			}


		if ("paparKeputusan".equals(submit)) {

			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String t_mohon = getParam("tarikh_mohon");
			String september_mohon = "01/09/2009";
			//System.out.println("[check]1: " + t_mohon);

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date tar_mohon = df.parse(t_mohon);
			Date sep_mohon = df.parse(september_mohon);

			if (tar_mohon.before(sep_mohon)) {
				//System.out.print(" is before ");
				view_mode(session);
				int eventStatus = 1;
				this.context.put("EventStatus", eventStatus);
				String id = getParam("idPermohonan");
				FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
						(String) session.getAttribute("_ekptg_user_id"));
				headerppk_baru(session,id,"Y","","T");

				Vector list = FrmSenaraiFailKeputusanPermohonanInternalData
						.getData();
				this.context.put("ViewPemohon", list);

				// Vector listDaerahByPpkUnit =
				// FrmPrmhnnSek8KeputusanPermohonanInternalData.getListDaerahByPpkUnit(Integer.parseInt(h.get("negeritertinggi").toString()));
				this.context.put("ListDaerahPindah", logic_A
						.getListDaerahPindah());

				logic_A.setDataFail(id);
				listFail = logic_A.getDataFail();
				this.context.put("ViewFail", listFail);

				FrmPrmhnnSek8KeputusanPermohonanInternalData
						.setMaklumatMahkamah(id);
				Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
						.getMaklumatMahkamah();
				//System.out.println("listMaklumatMahkamah **::"
					//	+ listMaklumatMahkamah);
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

				this.context.put("viewcheckJ1", "");
				this.context.put("viewcheckJ2", "");
				this.context.put("viewcheckJ3", "");

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

				vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama17.jsp";
			}
			if (tar_mohon.after(sep_mohon) || tar_mohon.equals(sep_mohon)) {
			//	Sy//System.out.pri(" is after ");
				view_mode(session);
				int eventStatus = 1;
				this.context.put("EventStatus", eventStatus);
				String id = getParam("idPermohonan");
				FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
						(String) session.getAttribute("_ekptg_user_id"));
				headerppk_baru(session,id,"Y","","T");
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
				//System.out.println("listMaklumatMahkamah **::"
					//	+ listMaklumatMahkamah);
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

				this.context.put("viewcheckJ1", "");
				this.context.put("viewcheckJ2", "");
				this.context.put("viewcheckJ3", "");

				this.context.put("jenis_pej", "");
				this.context.put("jenis_pej_id", "");

				this.context.put("mahkamah_kosong", "yes");
				this.context.put("infoMahkamah", "");

				vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan17.jsp";
			}

			//System.out.println("TARIKH MOHON:" + tar_mohon);

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

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

			//System.out.println("MMM EX" + ex);

			vv.addElement(h);

			//System.out.println("PPP VV:" + vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			//System.out.println("jumHta" + getParam("txtNilaianHTA"));

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

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));

			h.put("id_Permohonansimati", getParam("idpermohonansimati"));

			this.context.put("ViewPemohon", vv);

			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			headerppk_baru(session,getParam("idPermohonan"),"Y","","T");

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan17.jsp";
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

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
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

			//System.out.println("MMM EX" + ex);

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			vv.addElement(h);

			//System.out.println("PPP VV:" + vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			//System.out.println("jumHta" + getParam("txtNilaianHTA"));

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

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));

			this.context.put("ViewPemohon", vv);

			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			headerppk_baru(session,getParam("idPermohonan"),"Y","","T");

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama17.jsp";
		}

		else if ("get_jenisPejabat".equals(submit)) {
			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String j_p = getParam("jenis_pej");

			// //System.out.println()

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

			//System.out.println("MMM EX" + ex);

			// h.put("negeritertinggi",getParam("id_negeripindah"));
			// h.put("daerahtertinggi",getParam("id_daerahpindah"));
			h.put("daerahmhn", getParam("id_daerahmohon"));
			h.put("negerimahkamah", getParam("negerimahkamah"));
			h.put("daerahmahkamah", getParam("daerahmahkamah"));

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			vv.addElement(h);

			//System.out.println("PPP VV:" + vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			//System.out.println("jumHta" + getParam("txtNilaianHTA"));

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

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));

			h.put("id_Permohonansimati", getParam("idpermohonansimati"));

			this.context.put("ViewPemohon", vv);
			this.context.put("listMaklumatMahkamahJ_ID", "");
			// this.context.put("jenis_pej_id", "");
			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			headerppk_baru(session,getParam("idPermohonan"),"Y","","T");

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");
			this.context.put("EventStatus", "");
			this.context.put("jenis_pej_id", "");

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan17.jsp";
		}

		else if ("get_jenisPejabatLama".equals(submit)) {
			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String j_p = getParam("jenis_pej");

			// //System.out.println()

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

			//System.out.println("MMM EX" + ex);

			h.put("negeritertinggi", getParam("id_negeripindah"));
			h.put("daerahtertinggi", getParam("id_daerahpindah"));
			h.put("daerahmhn", getParam("id_daerahmohon"));
			h.put("negerimahkamah", getParam("negerimahkamah"));
			h.put("daerahmahkamah", getParam("daerahmahkamah"));

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			vv.addElement(h);

			//System.out.println("PPP VV:" + vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			//System.out.println("jumHta" + getParam("txtNilaianHTA"));

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

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));

			this.context.put("ViewPemohon", vv);
			this.context.put("listMaklumatMahkamahJ_ID", "");
			// this.context.put("jenis_pej_id", "");
			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			headerppk_baru(session,getParam("idPermohonan"),"Y","","T");

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");
			this.context.put("EventStatus", "");
			this.context.put("jenis_pej_id", "");

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama17.jsp";
		}
/*
		else if ("getCari".equals(submit)) {

			// Vector listNegeriByPpkUnit =
			// FrmPrmhnnSek8KeputusanPermohonanInternalData.getListNegeriByhta(getParam("idpermohonansimati"));
			// this.context.put("ListNegeriPindah",listNegeriByPpkUnit);

			readability1 = " ";
			readability2 = "readonly";
			disability1 = "disabled";
			disability2 = "";
			String Carix = "1";
			this.context.put("carix", Carix);
			String noFail = getParam("txtNoFail");
			String namaPemohon = getParam("txtPemohon");
			String namaSimati = getParam("txtSimati");
			String icSimati = getParam("txtIcSimati");
			String JenisIc = getParam("jenisIc");
			String usid = (String) session.getAttribute("_ekptg_user_id");
			FrmSenaraiFailKeputusanPermohonanInternalData.setCarianFail17(
					noFail, namaPemohon, namaSimati, icSimati, JenisIc, usid);
			Vector list1 = new Vector();
			list1 = FrmSenaraiFailKeputusanPermohonanInternalData.getList17();
			int countList1 = list1.size();
			this.context.put("Senaraifail1", list1);
			this.context.put("CountList1", countList1);

			FrmPrmhnnSek8KeputusanPermohonanInternalData.setMaklumatMahkamahM();
			Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahM();
			this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahM);

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

			vm = "app/ppk/frmSenaraiFailKeputusanPermohonan.jsp";
		}
		*/

		 else if ("getKemaskini_keputusan".equals(submit)) {

			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String t_mohon = getParam("tarikh_mohon");
			String september_mohon = "01/09/2009";
			//System.out.println("[check]1: " + t_mohon);

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date tar_mohon = df.parse(t_mohon);
			Date sep_mohon = df.parse(september_mohon);

			if (tar_mohon.after(sep_mohon) || tar_mohon.equals(sep_mohon)) {
				//System.out.print(" is after ");
				view_mode(session);
				int eventStatus = 0;
				this.context.put("EventStatus", eventStatus);
				String id = getParam("idPermohonan");
				FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
						(String) session.getAttribute("_ekptg_user_id"));
				headerppk_baru(session,id,"Y","","T");
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
				//System.out.println("listMaklumatMahkamah **::"
				//		+ listMaklumatMahkamah);
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

				this.context.put("viewcheckJ1", "");
				this.context.put("viewcheckJ2", "");
				this.context.put("viewcheckJ3", "");

				this.context.put("jenis_pej", "");
				this.context.put("jenis_pej_id", "");

				this.context.put("mahkamah_kosong", "yes");
				this.context.put("infoMahkamah", "");

				vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan17.jsp";
			}

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

		}

		else if ("getKemaskini_keputusanLama".equals(submit)) {

			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getListNegeriByhta(getParam("idpermohonansimati"));
			this.context.put("ListNegeriPindah", listNegeriByPpkUnit);

			String t_mohon = getParam("tarikh_mohon");
			String september_mohon = "01/09/2009";
			//System.out.println("[check]1: " + t_mohon);

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date tar_mohon = df.parse(t_mohon);
			Date sep_mohon = df.parse(september_mohon);

			if (tar_mohon.before(sep_mohon)) {
				//System.out.print(" is before ");
				view_mode(session);
				int eventStatus = 0;
				this.context.put("EventStatus", eventStatus);
				String id = getParam("idPermohonan");
				FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
						(String) session.getAttribute("_ekptg_user_id"));
				headerppk_baru(session,id,"Y","","T");
				
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
					// Vector s3 =
					// logic_A.getListBandarByNegeri(Integer.parseInt(k.get("negeritertinggi").toString()));
					Vector listDaerahByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData
							.getListDaerahByPpkUnit((String)k.get("negeritertinggi"));

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
				//System.out.println("listMaklumatMahkamah **::"
				//		+ listMaklumatMahkamah);
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

				this.context.put("viewcheckJ1", "");
				this.context.put("viewcheckJ2", "");
				this.context.put("viewcheckJ3", "");

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

				// vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama17.jsp";
			}

			//System.out.println("TARIKH MOHON:" + tar_mohon);

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama17.jsp";
		}

		/*
		 * else if("getKemaskini_keputusanLama".equals(submit)){
		 *
		 *
		 *
		 * Vector listNegeriByPpkUnit =
		 * FrmPrmhnnSek8KeputusanPermohonanInternalData
		 * .getListNegeriByhta(Integer
		 * .parseInt(getParam("idpermohonansimati")));
		 * this.context.put("ListNegeriPindah",listNegeriByPpkUnit);
		 *
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
		 * System.out.println("listMaklumatMahkamah **::"+listMaklumatMahkamah);
		 * this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);
		 *
		 *
		 * vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama17.jsp"; }
		 */
		else if ("getBack_keputusan".equals(submit)) {

			Vector listNegeriByPpkUnit = FrmPrmhnnSek8KeputusanPermohonanInternalData.getListNegeriByhta(getParam("idpermohonansimati"));
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

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

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

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

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
			headerppk_baru(session,id,"Y","","T");
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
			//System.out.println("listMaklumatMahkamah **::"
			//		+ listMaklumatMahkamah);
			this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

			// vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan17.jsp";
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
		//	System.out.println("listMaklumatMahkamah **::"
		//			+ listMaklumatMahkamah);
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
			//h.put("jumHartaTarikhMohon", getParam("jumHartaTarikhMohon"));
			h.put("jumHartaTarikhMohon", Double.parseDouble(getParam("jumHartaTarikhMohon")));//razman edit
			h.put("jumHartaTarikhMati", getParam("jumHartaTarikhMati"));
			
			h.put("tujuanPindah", getParam("tujuanPindah"));//razman add

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
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

			//System.out.println("MMM EX" + ex);

			vv.addElement(h);

			//System.out.println("PPP VV:" + vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			//System.out.println("jumHta" + getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode2", getParam("setMode2"));
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));

			this.context.put("ViewPemohon", vv);

			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			headerppk_baru(session,getParam("idPermohonan"),"Y","","T");

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

			// vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";
			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan17.jsp";
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

			int idNegeri = Integer.parseInt(getParam("socNegeriPindah"));
			this.context.put("SocNegeri", idNegeri);

			/*
			 * FrmSenaraiFailKeputusanPermohonanInternalData.setListNegeri();
			 * Vector listNegeri =
			 * FrmSenaraiFailKeputusanPermohonanInternalData.getListNegeri();
			 * this.context.put("ListNegeri",listNegeri);
			 *
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
			//System.out.println("listMaklumatMahkamah **::"
					//+ listMaklumatMahkamah);
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

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
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

		//	System.out.println("MMM EX" + ex);

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			vv.addElement(h);

		//	System.out.println("PPP VV:" + vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
		//	System.out.println("jumHta" + getParam("txtNilaianHTA"));

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

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));

			this.context.put("ViewPemohon", vv);

			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			headerppk_baru(session,getParam("idPermohonan"),"Y","","T");

			this.context.put("eve", 8);
			this.context.put("EventStatus", "");
			this.context.put("eve_mah", "");

			this.context.put("daerahpindah", "0");

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

			// vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";
			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama17.jsp";
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
		//	System.out.println("listMaklumatMahkamah **::"
		//			+ listMaklumatMahkamah);
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

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
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

			//System.out.println("MMM EX" + ex);

			h.put("id_Permohonansimati", getParam("idpermohonansimati"));
			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));
			vv.addElement(h);

			//System.out.println("PPP VV:" + vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			//System.out.println("jumHta" + getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode2", getParam("setMode2"));
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));

			this.context.put("ViewPemohon", vv);

			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			headerppk_baru(session,getParam("idPermohonan"),"Y","","T");

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

			// vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";
			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama17.jsp";
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
			headerppk_baru(session,id,"Y","","T");
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
			//System.out.println("listMaklumatMahkamah **::"
				//	+ listMaklumatMahkamah);
			this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);

			// view_mahkamah(session);
			// int idDaerah = Integer.parseInt(getParam("socDaerah"));
			// int idNegeri = Integer.parseInt(getParam("socNegeri"));
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
			h.put("tujuanPindah", getParam("tujuanPindah"));//razman add
			h.put("jumHartaTarikhMati", getParam("jumHartaTarikhMati"));

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
			h.put("tarikhhantarnilaian", getParam("txdTarikhHantarNilaian"));
			h.put("tarikhterimanilaian", getParam("txdTarikhTerimaNilaian"));
			
			

			// h.put("jenisborangC",getParam("jenisborangC"));
			//razman add open
			String check = getParam("sorPenentuanBidangKuasa");
			String checkterus = getParam("sorPenentuanBidangKuasaTeruskan");
			//myLogger.info("check : "+check+" >>> checkterus : "+checkterus);
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
			
			
			h.put("catatan", getParam("catatan"));

			h.put("namaSimati", getParam("namaSimati"));
			h.put("namaPemohon", getParam("namaPemohon"));

			h.put("jenisborangC", getParam("sorKeputusanBorangC"));

			// h.put("jenisborangC",getParam("sorPenentuanBidangKuasa"));

			
			h.put("jenis_pej", getParam("jenis_pej"));

			h.put("nofailawal", getParam("nofailawal"));
			h.put("pemohonawal", getParam("namapemohonawal"));
			h.put("pejabatawal", getParam("tempatmohonawal"));

			h.put("negerimahkamah", getParam("socNegeri"));
			h.put("daerahmahkamah", getParam("socDaerah"));

			h.put("id_Permohonansimati", getParam("idpermohonansimati"));

			//System.out.println("MMM EX" + ex);

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			vv.addElement(h);

			//System.out.println("PPP VV:" + vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			//System.out.println("jumHta" + getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode2", getParam("setMode2"));
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));

			this.context.put("ViewPemohon", vv);

			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
		
			this.context.put("eve", 8);
			this.context.put("eve_mah", "");
			// vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan17.jsp";
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
			headerppk_baru(session,id,"Y","","T");
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
			//System.out.println("listMaklumatMahkamah **::"
			//		+ listMaklumatMahkamah);
			this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);

			// view_mahkamah(session);
			// int idDaerah = Integer.parseInt(getParam("socDaerah"));
			// int idNegeri = Integer.parseInt(getParam("socNegeri"));
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

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
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

		//	System.out.println("MMM EX" + ex);

			h.put("id_taraf_mohon", getParam("id_taraf_mohon"));
			h.put("taraf_pemohon", getParam("taraf_pemohon"));

			vv.addElement(h);

			//System.out.println("PPP VV:" + vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			//System.out.println("jumHta" + getParam("txtNilaianHTA"));

			this.context.put("setMode1", getParam("setMode1"));
			this.context.put("setMode2", getParam("setMode2"));
			this.context.put("setMode3", getParam("setMode3"));
			this.context.put("setMode4", getParam("setMode4"));
			this.context.put("setMode5", getParam("setMode5"));
			this.context.put("setMode6", getParam("setMode6"));

			this.context.put("checkJ1", getParam("checkJ1"));
			this.context.put("checkJ2", getParam("checkJ2"));
			this.context.put("checkJ3", getParam("checkJ3"));

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));

			this.context.put("ViewPemohon", vv);

			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			
			this.context.put("eve", 8);
			this.context.put("eve_mah", "");
			// vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama17.jsp";
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
			headerppk_baru(session,id,"Y","","T");
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
		//	System.out.println("listMaklumatMahkamah **::"
			//		+ listMaklumatMahkamah);
			this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);

			// view_mahkamah(session);
			// int idDaerah = Integer.parseInt(getParam("socDaerah"));
			// int idNegeri = Integer.parseInt(getParam("socNegeri"));
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

			h.put("tarikhborangB", getParam("txdTarikhHantarBorangB"));
			h.put("tarikhborangC", getParam("txdTarikhTerimaBorangC"));
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

			//System.out.println("MMM EX" + ex);

			vv.addElement(h);

			//System.out.println("PPP VV:" + vv);

			this.context.put("jumHta", getParam("txtNilaianHTA"));
			this.context.put("jumHa", getParam("txtNilaianHA"));
			this.context.put("Overall", getParam("txtJumKeseluruhan"));
			//System.out.println("jumHta" + getParam("txtNilaianHTA"));

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

			this.context.put("viewcheckJ1", getParam("viewcheckJ1"));
			this.context.put("viewcheckJ2", getParam("viewcheckJ2"));
			this.context.put("viewcheckJ3", getParam("viewcheckJ3"));

			this.context.put("ViewPemohon", vv);

			// this.context.put("jenis_pej", j_p);
			logic_A.setDataFail(getParam("idPermohonan"));
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");
			this.context.put("daerahpindah", "0");
			// vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama17.jsp";
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
			headerppk_baru(session,id,"Y","","T");
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
		//	System.out.println("listMaklumatMahkamah **::"
		//			+ listMaklumatMahkamah);
			this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);
			String idneg = getParam("socNegeri");
			Long daerahId = Long.parseLong(getParam("socDaerah"));
			Long negId = Long.parseLong(getParam("socNegeri"));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
					negId, "onChange=getDaerah()"));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idneg,
					"socDaerah", daerahId, "", "onChange=getAddress()"));

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

			vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";
		} else if ("getSimpan_keputusan".equals(submit)) {

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
					insertBorang(session);
				} else {
					updateBorang(session);
				}
			}
			view_mode(session);

			// String id = getParam("idPermohonan");
			FrmPrmhnnSek8KeputusanPermohonanInternalData.setDataKeputusan(id);
			int eventStatus = 1;
			this.context.put("EventStatus", eventStatus);

			FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session,id,"Y","","T");
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
		//	System.out.println("listMaklumatMahkamah **::"
		//			+ listMaklumatMahkamah);
			this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan17.jsp";
		} else if ("getSimpan_keputusanLama".equals(submit)) {

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
			headerppk_baru(session,id,"Y","","T");
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
		//	System.out.println("listMaklumatMahkamah **::"
		//			+ listMaklumatMahkamah);
			this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);

			FrmPrmhnnSek8KeputusanPermohonanInternalData
					.setMaklumatMahkamahMPindah();
			Vector listMaklumatMahkamahMKPindah = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahMPindah();
			this.context.put("listMaklumatMahkamahJPindah",
					listMaklumatMahkamahMKPindah);

			this.context.put("daerahpindah", "0");

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonanLama17.jsp";
		}
	/////
	/*

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
			FrmSenaraiFailKeputusanPermohonanInternalData.setCarianFail17(
					noFail, namaPemohon, namaSimati, icSimati, JenisIc, usid);
			Vector list1 = new Vector();
			list1 = FrmSenaraiFailKeputusanPermohonanInternalData.getList17();
			int countList1 = list1.size();
			this.context.put("Senaraifail1", list1);
			this.context.put("CountList1", countList1);
			this.context.put("Senaraifail", list1);
			prepareItemForDisplay(session, list1, 3, "first");
			session.setAttribute("Senaraifail", list1);



			FrmPrmhnnSek8KeputusanPermohonanInternalData.setMaklumatMahkamahM();
			Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahM();
			this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahM);

			String mati = getParam("idpermohonansimati");

			logic.setDataHTAX(mati);
			listHTAX = logic.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			logic.setDataHTAXdulu(mati);
			listHTAXdulu = logic.getDataHTAXdulu();
			this.context.put("listHTAXdulu", listHTAXdulu);

			logic.setDataHTA(mati);
			listHTA = logic.getDataHTA();
			this.context.put("listHTA", listHTA);

			logic.setDataHTAdulu_KP(mati);
			listHTAdulu = logic.getDataHTAdulu_KP();
			this.context.put("listHTAdulu", listHTAdulu);

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			logic_A.setDataHaDulu(mati);
			listppkhadulu = logic_A.getDataHaDulu();
			this.context.put("listHadulu", listppkhadulu);



			vm = "app/ppk/frmSenaraiFailKeputusanPermohonan.jsp";
		}
		*/

		else if("getCari".equals(submit)){
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
			String usid=(String)session.getAttribute("_ekptg_user_id");
			FrmSenaraiFailKeputusanPermohonanInternalData.setCarianFail17(
					noFail, namaPemohon, namaSimati, icSimati, JenisIc, usid);Vector list1 = new Vector();
			list1 = FrmSenaraiFailKeputusanPermohonanInternalData.getList17();

			System.out.println("CARI :"+list1);

			int countList1 = list1.size();
			this.context.put("Senaraifail",list1);


			this.context.put("CountList1",countList1);



			prepareItemForDisplay(session,list1,25,"first");
			session.setAttribute("Senaraifail", list1);


 			vm = "app/ppk/frmSenaraiFailKeputusanPermohonan.jsp";
		}


		else if (("next".equals(submit)) || ("previous".equals(submit))) {
			// Carix = "1";
			String usid="";
			String Carix = "2";
			this.context.put("carix", Carix);
			usid=(String)session.getAttribute("_ekptg_user_id");

			listKeputusan = (Vector) session.getAttribute("Senaraifail");
			this.context.put("Senaraifail", listKeputusan);
			prepareItemForDisplay(session, listKeputusan, 25, submit);
			int countList = listKeputusan.size();
			this.context.put("CountList", countList);

			 vm = "app/ppk/frmSenaraiFailKeputusanPermohonan.jsp";
		}

		else {
			String usid = "";
			// usid="81"; //kelantan
			// session.setAttribute("_ekptg_user_id", usid);
			// this.context.put("usid",usid);
			usid = (String) session.getAttribute("_ekptg_user_id");
			this.context.put("usid", usid);

			FrmSenaraiFailKeputusanPermohonanInternalData
					.setListKeputusan17(usid);
			listKeputusan = FrmSenaraiFailKeputusanPermohonanInternalData
					.getListKeputusan17();
			String Carix = "2";
			this.context.put("carix", Carix);
			this.context.put("Senaraifail", listKeputusan);
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri"));

			prepareItemForDisplay(session,listKeputusan,25,"first");
			session.setAttribute("Senaraifail", listKeputusan);

		}

		FrmSenaraiFailKeputusanPermohonanInternalData.setListNegeri();
		Vector listNegeri = FrmSenaraiFailKeputusanPermohonanInternalData
				.getListNegeri();
		this.context.put("ListNegeri", listNegeri);

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
/*
	private void insertBorang(HttpSession session) throws Exception {


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
		//System.out.println("CHECKKKup:::" + ex);

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

		////System.out.println("nofailawal:" + getParam("nofailawal"));
		//System.out.println("namapemohonawal:" + getParam("namapemohonawal"));
		//System.out.println("tempatmohonawal:" + getParam("tempatmohonawal"));
		//System.out.println("socDaerah:" + getParam("socDaerah"));
		//System.out.println("socNegeri:" + getParam("socNegeri"));

		// id_sta 151,152
		h.put("nofailawal", getParam("nofailawal"));
		h.put("namapemohonawal", getParam("namapemohonawal"));
		h.put("tempatmohonawal", getParam("tempatmohonawal"));







		// id_sta 50
		h.put("idDaerah", getParam("socDaerah"));
		h.put("idNegeri", getParam("socNegeri"));

		h.put("userId", (String) session.getAttribute("_ekptg_user_id"));
		h.put("id_Suburusanstatus", Integer
				.parseInt(getParam("id_Suburusanstatus")));
		h.put("id_Suburusanstatusfail", Integer
				.parseInt(getParam("id_Suburusanstatusfail")));

		h.put("id_Fail", getParam("id_Fail"));

		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));

		FrmPrmhnnSek8KeputusanPermohonanInternalData.insertBorang(h);
	}

	private void insertBorangLama(HttpSession session) throws Exception {

		//System.out.println("1111111 ad:::"
			//	+ getParam("sorPenentuanBidangKuasa"));
		//System.out.println("2222222 ad:::"
			//	+ getParam("sorPenentuanBidangKuasaTeruskan"));

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
		//System.out.println("CHECKKKup:::" + ex);

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

		//System.out.println("nofailawal:" + getParam("nofailawal"));
		//System.out.println("namapemohonawal:" + getParam("namapemohonawal"));
		//System.out.println("tempatmohonawal:" + getParam("tempatmohonawal"));
		//System.out.println("socDaerah:" + getParam("socDaerah"));
		//System.out.println("socNegeri:" + getParam("socNegeri"));

		// id_sta 151,152
		h.put("nofailawal", getParam("nofailawal"));
		h.put("namapemohonawal", getParam("namapemohonawal"));
		h.put("tempatmohonawal", getParam("tempatmohonawal"));

		// id_sta 50
		h.put("idDaerah", getParam("socDaerah"));
		h.put("idNegeri", getParam("socNegeri"));

		h.put("idDaerahPindah", getParam("socDaerahPindah"));
		h.put("idNegeriPindah", getParam("socNegeriPindah"));

		h.put("userId", (String) session.getAttribute("_ekptg_user_id"));
		h.put("id_Suburusanstatus", Integer
				.parseInt(getParam("id_Suburusanstatus")));
		h.put("id_Suburusanstatusfail", Integer
				.parseInt(getParam("id_Suburusanstatusfail")));

		h.put("id_Fail", getParam("id_Fail"));

		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));

		FrmPrmhnnSek8KeputusanPermohonanInternalData.insertBorangLama(h);
	}

	private void updateBorang(HttpSession session) throws Exception {
		//System.out.println("1111111 up:::"
			//	+ getParam("sorPenentuanBidangKuasa"));
		//System.out.println("2222222 up:::"
			//	+ getParam("sorPenentuanBidangKuasaTeruskan"));

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

		//System.out.println("CHECKKKup123:::" + ex);

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

		//System.out.println("nofailawal:" + getParam("nofailawal"));
		//System.out.println("namapemohonawal:" + getParam("namapemohonawal"));
		//System.out.println("tempatmohonawal:" + getParam("tempatmohonawal"));
		//System.out.println("socDaerah:" + getParam("socDaerah"));
		//System.out.println("socNegeri:" + getParam("socNegeri"));

		// id_sta 151,152
		h.put("nofailawal", getParam("nofailawal"));
		h.put("namapemohonawal", getParam("namapemohonawal"));
		h.put("tempatmohonawal", getParam("tempatmohonawal"));

		// id_sta 50
		h.put("idDaerah", getParam("socDaerah"));
		h.put("idNegeri", getParam("socNegeri"));

		//System.out.println("daerah :" + getParam("socDaerah") + "negeri :"
			//	+ getParam("socNegeri"));

		h.put("userId", (String) session.getAttribute("_ekptg_user_id"));
		h.put("id_Suburusanstatus", Integer
				.parseInt(getParam("id_Suburusanstatus")));
		h.put("id_Suburusanstatusfail", Integer
				.parseInt(getParam("id_Suburusanstatusfail")));
		h.put("id_Fail", getParam("id_Fail"));
		h.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));

		FrmPrmhnnSek8KeputusanPermohonanInternalData.updateBorang(h);
	}

	private void updateBorangLama(HttpSession session) throws Exception {
		//System.out.println("1111111 up:::"
			//	+ getParam("sorPenentuanBidangKuasa"));
		//System.out.println("2222222 up:::"
			//	+ getParam("sorPenentuanBidangKuasaTeruskan"));

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

		//System.out.println("CHECKKKup123:::" + ex);

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

		//System.out.println("nofailawal:" + getParam("nofailawal"));
		//System.out.println("namapemohonawal:" + getParam("namapemohonawal"));
		//System.out.println("tempatmohonawal:" + getParam("tempatmohonawal"));
		//System.out.println("socDaerah:" + getParam("socDaerah"));
		//System.out.println("socNegeri:" + getParam("socNegeri"));

		// id_sta 151,152
		h.put("nofailawal", getParam("nofailawal"));
		h.put("namapemohonawal", getParam("namapemohonawal"));
		h.put("tempatmohonawal", getParam("tempatmohonawal"));

		// id_sta 50
		h.put("idDaerah", getParam("socDaerah"));
		h.put("idNegeri", getParam("socNegeri"));

		h.put("idDaerahPindah", getParam("socDaerahPindah"));
		h.put("idNegeriPindah", getParam("socNegeriPindah"));

		//System.out.println("daerah :" + getParam("socDaerah") + "negeri :"
				//+ getParam("socNegeri"));

		h.put("userId", (String) session.getAttribute("_ekptg_user_id"));
		h.put("id_Suburusanstatus", Integer
				.parseInt(getParam("id_Suburusanstatus")));
		h.put("id_Suburusanstatusfail", Integer
				.parseInt(getParam("id_Suburusanstatusfail")));
		h.put("id_Fail", getParam("id_Fail"));
		h.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));

		FrmPrmhnnSek8KeputusanPermohonanInternalData.updateBorangLama(h);
	}*/

private void insertBorang(HttpSession session) throws Exception {

		String check=getParam("sorPenentuanBidangKuasa");
		String checkterus=getParam("sorPenentuanBidangKuasaTeruskan");
		String ex="";
		if(checkterus!="" )//((check=="53" || check=="50" || check=="63"))
		{
			ex=checkterus;
		}
		else //if((check=="1") && (checkterus=="151" || checkterus=="152"))
		{
			ex=check;
		}

		Hashtable h = null;
	    h = new Hashtable();
	    h.put("tarikhHantarBorangB", getParam("txdTarikhHantarBorangB"));
	    h.put("tarikhTerimaBorangC", getParam("txdTarikhTerimaBorangC"));
	    h.put("tarikhHantarNilaian", getParam("txdTarikhHantarNilaian"));
	    h.put("tarikhTerimaNilaian", getParam("txdTarikhTerimaNilaian"));
	    h.put("keputusanBorangC", getParam("sorKeputusanBorangC"));
 
	    h.put("penentuanBidangKuasa",ex);
	 //   h.put("penentuanBidangKuasaTeruskan", getParam("sorPenentuanBidangKuasaTeruskan"));
	    h.put("catatan", getParam("txtCatatan"));
	    h.put("IdPermohonan", getParam("idPermohonan"));


	   //System.out.println("nofailawal:"+getParam("nofailawal"));
	   //System.out.println("namapemohonawal:"+getParam("namapemohonawal"));
	   //System.out.println("tempatmohonawal:"+getParam("tempatmohonawal"));
	   //System.out.println("socDaerah:"+getParam("socDaerah"));
	   //System.out.println("socNegeri:"+getParam("socNegeri"));

	  //id_sta 151,152
	     h.put("nofailawal", getParam("nofailawal"));
	     h.put("namapemohonawal", getParam("namapemohonawal"));
	     h.put("tempatmohonawal", getParam("tempatmohonawal"));

	     //pej_99
	     h.put("jenis_pej",getParam("jenis_pej"));
	     if(getParam("jenis_pej").equals("99"))
	     {
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

	     }else
	     {
	    	  h.put("txtNamaKaveat", "");
	    	  h.put("txtNoKaveat", "");
	    	  h.put("txtNamaFirma", "");
	    	  h.put("txtAlamat1Peguam","");
	    	  h.put("txtAlamat2Peguam", "");
	    	  h.put("txtAlamat3Peguam","");
	    	  h.put("txtPoskodPeguam", "");
	    	  h.put("socNegeriPeguam","");
	    	  h.put("txtBandarPeguam","");
	    	  h.put("txtNomborTelefonPeguam", "");
	     }


	     //id_sta 50
	     h.put("idDaerah", getParam("socDaerah"));
		 h.put("idNegeri", getParam("socNegeri"));

		 h.put("tujuanPindah", getParam("tujuanPindah")); //razman add

	    h.put("userId", (String)session.getAttribute("_ekptg_user_id"));
        h.put("id_Suburusanstatus",getParam("id_Suburusanstatus"));
        h.put("id_Suburusanstatusfail", getParam("id_Suburusanstatusfail"));

        h.put("id_Fail", getParam("id_Fail"));

        h.put("id_Masuk",(String)session.getAttribute("_ekptg_user_id"));
        h.put("salinan_arahan", "1");



	    FrmPrmhnnSek8KeputusanPermohonanInternalData.insertBorang(session,h,"17");
	}
private void insertBorangLama(HttpSession session) throws Exception {


		String check=getParam("sorPenentuanBidangKuasa");
		String checkterus=getParam("sorPenentuanBidangKuasaTeruskan");
		String ex="";
		if(checkterus!="" )//((check=="53" || check=="50" || check=="63"))
		{
			ex=checkterus;
		}
		else //if((check=="1") && (checkterus=="151" || checkterus=="152"))
		{
			ex=check;
		}
		System.out.println("CHECKKKup:::"+ex);

		Hashtable h = null;
	    h = new Hashtable();
	    h.put("tarikhHantarBorangB", getParam("txdTarikhHantarBorangB"));
	    h.put("tarikhTerimaBorangC", getParam("txdTarikhTerimaBorangC"));
	    h.put("tarikhHantarNilaian", getParam("txdTarikhHantarNilaian"));
	    h.put("tarikhTerimaNilaian", getParam("txdTarikhTerimaNilaian"));
	    h.put("keputusanBorangC", getParam("sorKeputusanBorangC"));
	    h.put("penentuanBidangKuasa",ex);
	 //   h.put("penentuanBidangKuasaTeruskan", getParam("sorPenentuanBidangKuasaTeruskan"));
	    h.put("catatan", getParam("txtCatatan"));
	    h.put("IdPermohonan", getParam("idPermohonan"));


	   //System.out.println("nofailawal:"+getParam("nofailawal"));
	   //System.out.println("namapemohonawal:"+getParam("namapemohonawal"));
	   //System.out.println("tempatmohonawal:"+getParam("tempatmohonawal"));
	   //System.out.println("socDaerah:"+getParam("socDaerah"));
	   //System.out.println("socNegeri:"+getParam("socNegeri"));

	  //id_sta 151,152
	     h.put("nofailawal", getParam("nofailawal"));
	     h.put("namapemohonawal", getParam("namapemohonawal"));
	     h.put("tempatmohonawal", getParam("tempatmohonawal"));

	     //id_sta 50
	     h.put("idDaerah", getParam("socDaerah"));
		 h.put("idNegeri", getParam("socNegeri"));

		 h.put("tujuanPindah", getParam("tujuanPindah")); //razman add
		 
		 h.put("idDaerahPindah", getParam("socDaerahPindah"));
		 h.put("idNegeriPindah", getParam("socNegeriPindah"));

		 h.put("jenis_pej",getParam("jenis_pej"));
	     if(getParam("jenis_pej").equals("99"))
	     {
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

	     }else
	     {
	    	  h.put("txtNamaKaveat", "");
	    	  h.put("txtNoKaveat", "");
	    	  h.put("txtNamaFirma", "");
	    	  h.put("txtAlamat1Peguam","");
	    	  h.put("txtAlamat2Peguam", "");
	    	  h.put("txtAlamat3Peguam","");
	    	  h.put("txtPoskodPeguam", "");
	    	  h.put("socNegeriPeguam","");
	    	  h.put("txtBandarPeguam","");
	    	  h.put("txtNomborTelefonPeguam", "");
	     }




	    h.put("userId", (String)session.getAttribute("_ekptg_user_id"));
        h.put("id_Suburusanstatus",getParam("id_Suburusanstatus"));
        h.put("id_Suburusanstatusfail",getParam("id_Suburusanstatusfail"));

        h.put("id_Fail", getParam("id_Fail"));

        h.put("id_Masuk",(String)session.getAttribute("_ekptg_user_id"));




	    FrmPrmhnnSek8KeputusanPermohonanInternalData.insertBorangLama(session,h,"17");
	}
	private void updateBorang(HttpSession session) throws Exception {

		String check=getParam("sorPenentuanBidangKuasa");
		String checkterus=getParam("sorPenentuanBidangKuasaTeruskan");

		String ex="";


		String d = "";
		String n = "";
		String nf = "";
		String pf = "";
		String tf = "";



		if(checkterus!="" )//((check=="53" || check=="50" || check=="63"))
		{
			ex=checkterus;


		}
		else //if((check=="1") && (checkterus=="151" || checkterus=="152"))
		{
			ex=check;



		}


		//System.out.println("CHECKKKup123:::"+ex);

		Hashtable h = null;
	    h = new Hashtable();
	    h.put("tarikhHantarBorangB", getParam("txdTarikhHantarBorangB"));
	    h.put("tarikhTerimaBorangC", getParam("txdTarikhTerimaBorangC"));
	    h.put("tarikhHantarNilaian", getParam("txdTarikhHantarNilaian"));
	    h.put("tarikhTerimaNilaian", getParam("txdTarikhTerimaNilaian"));
	    h.put("keputusanBorangC", getParam("sorKeputusanBorangC"));
	    h.put("penentuanBidangKuasa",ex);
	 //   h.put("penentuanBidangKuasaTeruskan", getParam("sorPenentuanBidangKuasaTeruskan"));
	    h.put("catatan", getParam("txtCatatan"));
	    h.put("IdPermohonan", getParam("idPermohonan"));


	   //System.out.println("nofailawal:"+getParam("nofailawal"));
	   //System.out.println("namapemohonawal:"+getParam("namapemohonawal"));
	   //System.out.println("tempatmohonawal:"+getParam("tempatmohonawal"));
	   //System.out.println("socDaerah:"+getParam("socDaerah"));
	   //System.out.println("socNegeri:"+getParam("socNegeri"));

	  //id_sta 151,152
	     h.put("nofailawal", getParam("nofailawal"));
	     h.put("namapemohonawal", getParam("namapemohonawal"));
	     h.put("tempatmohonawal", getParam("tempatmohonawal"));

	     //id_sta 50
	     h.put("idDaerah", getParam("socDaerah"));
		 h.put("idNegeri", getParam("socNegeri"));

		 h.put("tujuanPindah", getParam("tujuanPindah"));//razman add

		//System.out.println("daerah :"+getParam("socDaerah")+"negeri :"+getParam("socNegeri"));


		  h.put("jenis_pej",getParam("jenis_pej"));
		     if(getParam("jenis_pej").equals("99"))
		     {
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

		     }else
		     {
		    	  h.put("txtNamaKaveat", "");
		    	  h.put("txtNoKaveat", "");
		    	  h.put("txtNamaFirma", "");
		    	  h.put("txtAlamat1Peguam","");
		    	  h.put("txtAlamat2Peguam", "");
		    	  h.put("txtAlamat3Peguam","");
		    	  h.put("txtPoskodPeguam", "");
		    	  h.put("socNegeriPeguam","");
		    	  h.put("txtBandarPeguam","");
		    	  h.put("txtNomborTelefonPeguam", "");
		     }


	    h.put("userId", (String)session.getAttribute("_ekptg_user_id"));
        h.put("id_Suburusanstatus",getParam("id_Suburusanstatus"));
        h.put("id_Suburusanstatusfail", getParam("id_Suburusanstatusfail"));
        h.put("id_Fail", getParam("id_Fail"));
        h.put("id_Kemaskini", (String)session.getAttribute("_ekptg_user_id"));
        h.put("salinan_arahan", "1");


	    FrmPrmhnnSek8KeputusanPermohonanInternalData.updateBorang(session,h,"17");
	}

	private void updateBorangLama(HttpSession session) throws Exception {

		String check=getParam("sorPenentuanBidangKuasa");
		String checkterus=getParam("sorPenentuanBidangKuasaTeruskan");

		String ex="";


		String d = "";
		String n = "";
		String nf = "";
		String pf = "";
		String tf = "";



		if(checkterus!="" )//((check=="53" || check=="50" || check=="63"))
		{
			ex=checkterus;


		}
		else //if((check=="1") && (checkterus=="151" || checkterus=="152"))
		{
			ex=check;



		}


		System.out.println("CHECKKKup123:::"+ex);

		Hashtable h = null;
	    h = new Hashtable();
	    h.put("tarikhHantarBorangB", getParam("txdTarikhHantarBorangB"));
	    h.put("tarikhTerimaBorangC", getParam("txdTarikhTerimaBorangC"));
	    h.put("tarikhHantarNilaian", getParam("txdTarikhHantarNilaian"));
	    h.put("tarikhTerimaNilaian", getParam("txdTarikhTerimaNilaian"));
	    h.put("keputusanBorangC", getParam("sorKeputusanBorangC"));
	    h.put("penentuanBidangKuasa",ex);
	 //   h.put("penentuanBidangKuasaTeruskan", getParam("sorPenentuanBidangKuasaTeruskan"));
	    h.put("catatan", getParam("txtCatatan"));
	    h.put("IdPermohonan", getParam("idPermohonan"));


	   //System.out.println("nofailawal:"+getParam("nofailawal"));
	   //System.out.println("namapemohonawal:"+getParam("namapemohonawal"));
	   //System.out.println("tempatmohonawal:"+getParam("tempatmohonawal"));
	   //System.out.println("socDaerah:"+getParam("socDaerah"));
	   //System.out.println("socNegeri:"+getParam("socNegeri"));

	  //id_sta 151,152
	     h.put("nofailawal", getParam("nofailawal"));
	     h.put("namapemohonawal", getParam("namapemohonawal"));
	     h.put("tempatmohonawal", getParam("tempatmohonawal"));

	     //id_sta 50
	     h.put("idDaerah", getParam("socDaerah"));
		 h.put("idNegeri", getParam("socNegeri"));
		 
		 h.put("tujuanPindah", getParam("tujuanPindah"));//razman add


		     h.put("idDaerahPindah", getParam("socDaerahPindah"));
			 h.put("idNegeriPindah", getParam("socNegeriPindah"));


			 h.put("jenis_pej",getParam("jenis_pej"));
		     if(getParam("jenis_pej").equals("99"))
		     {
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

		     }else
		     {
		    	  h.put("txtNamaKaveat", "");
		    	  h.put("txtNoKaveat", "");
		    	  h.put("txtNamaFirma", "");
		    	  h.put("txtAlamat1Peguam","");
		    	  h.put("txtAlamat2Peguam", "");
		    	  h.put("txtAlamat3Peguam","");
		    	  h.put("txtPoskodPeguam", "");
		    	  h.put("socNegeriPeguam","");
		    	  h.put("txtBandarPeguam","");
		    	  h.put("txtNomborTelefonPeguam", "");
		     }





		//System.out.println("daerah :"+getParam("socDaerah")+"negeri :"+getParam("socNegeri"));

	    h.put("userId", (String)session.getAttribute("_ekptg_user_id"));
        h.put("id_Suburusanstatus",getParam("id_Suburusanstatus"));
        h.put("id_Suburusanstatusfail", getParam("id_Suburusanstatusfail"));
        h.put("id_Fail", getParam("id_Fail"));
        h.put("id_Kemaskini", (String)session.getAttribute("_ekptg_user_id"));



	    FrmPrmhnnSek8KeputusanPermohonanInternalData.updateBorangLama(session,h,"17");
	}


	private void prepareItemForDisplay(HttpSession session, Vector objVector, int cntItemPage, String command) {
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
				//System.out.println("INT ::"+i);
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
	
	private void headerppk_baru(HttpSession session,String id_permohonan,String flag_permohonan,String id_fail,String flag_fail) throws Exception {
		//hashtable maklumat header
		this.context.put("headerppk",mainheader.getHeaderData(session,id_permohonan,flag_permohonan,id_fail,flag_fail));
		Vector list_sub = null;
		list_sub = mainheader.carianFail(id_permohonan,flag_permohonan,id_fail,flag_fail);		
		this.context.put("list_sub_header",list_sub);
		this.context.put("flag_jenis_vm","vtemplate");
	}
	private void headerppk_baru_default()
	{
		Hashtable headerppk = null;
		this.context.put("headerppk","");
		this.context.put("list_sub_header","");
		this.context.put("flag_jenis_vm","vtemplate");
	}


}
