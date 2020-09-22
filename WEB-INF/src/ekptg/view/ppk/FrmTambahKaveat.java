package ekptg.view.ppk;

import integrasi.utils.IntLogManager;
//import integrasi.ws.mt.MTManager;

/*import my.gov.kehakiman.eip.services.MTManager;
import my.gov.kehakiman.eip.services.MTManagerCivilRegisterCase;*/

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.velocity.VTemplate;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;

//import sun.misc.BASE64Encoder;

//import com.sun.xml.internal.messaging.saaj.util.Base64;

import com.Ostermiller.util.Base64;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8KeputusanPermohonanInternalData;
import ekptg.model.ppk.FrmSenaraiFailKeputusanPermohonanInternalData;


public class FrmTambahKaveat extends VTemplate {
	/**
	 *  
	 */
	SkrinPopupUploadDokumen logic_F = null;
	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmIntegrasiMT.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	FrmPrmhnnSek8DaftarSek8InternalData logic_A = null;
	      
	@Override
	public Template doTemplate() throws Exception {
		logic_F = new SkrinPopupUploadDokumen();
		
		
		HttpSession session = this.request.getSession();
		logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
		String user = (String) session.getAttribute("_portal_login");
		String usid = (String) session.getAttribute("_ekptg_user_id");
		this.context.put("usid", usid);

		String vm = "";
		String submit = request.getParameter("command");
		String fFrom = request.getParameter("frmFrom") != null ? (String) request.getParameter("frmFrom") : "";
		String idPermohonan = getParam("idPermohonan");
		String sendI = "tiada";
		String isExistPetioner = "";
		String id = getParam("idpermohonan");
		myLogger.info("submit = "+submit);
		myLogger.info("id = "+id);
		if ("tambahKaveat".equals(submit)) {
			this.context.put("selesai", "0");
			this.context.put("txtNamaKaveat2a", "");
			this.context.put("txtNoKaveat2a", "");
			this.context.put("txtNamaFirma2a", "");
			this.context.put("txtAlamat1Peguam2a", "");
			this.context.put("txtAlamat2Peguam2a", "");
			this.context.put("txtAlamat3Peguam2a", "");
			this.context.put("txtPoskodPeguam2a", "");
			FrmSenaraiFailKeputusanPermohonanInternalData.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			//headerppk_baru(session, id, "Y", "", "T");
			Vector list = FrmSenaraiFailKeputusanPermohonanInternalData
					.getData();
			this.context.put("ViewPemohon", list);


			vm = "app/ppk/frmTambahKaveat.jsp";
			
		

		} 
		
		else if ("getSimpan_keputusan".equals(submit)) {
			insertBorang(session);
			this.context.put("selesai", "1");
			vm = "app/ppk/frmTambahKaveat.jsp";
		}
		
		else if ("get_Bandarbaru".equals(submit)) {
			this.context.put("selesai", "0");
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
			//logic_A.setDataFail(getParam("idPermohonan"));
			//listFail = logic_A.getDataFail();
			//this.context.put("ViewFail", listFail);
			//headerppk_baru(session, getParam("idPermohonan"), "Y", "", "T");

			this.context.put("eve", 8);
			this.context.put("eve_mah", "");
			this.context.put("EventStatus", "");
			this.context.put("jenis_pej_id", "");
			this.context.put("txtNamaKaveat2a", getParam("txtNamaKaveat"));
			this.context.put("txtNoKaveat2a", getParam("txtNoKaveat"));
			this.context.put("txtNamaFirma2a", getParam("txtNamaFirma"));
			this.context.put("txtAlamat1Peguam2a", getParam("txtAlamat1Peguam"));
			this.context.put("txtAlamat2Peguam2a", getParam("txtAlamat2Peguam"));
			this.context.put("txtAlamat3Peguam2a", getParam("txtAlamat3Peguam"));
			this.context.put("txtPoskodPeguam2a", getParam("txtPoskodPeguam"));

			Hashtable h3 = (Hashtable) vv.get(0);
			if (getParam("socNegeriPeguam") != ""
					&& getParam("socNegeriPeguam") != "0") {
				Vector s3 = logic_A.getListBandarByNegeri(Integer
						.parseInt(getParam("socNegeriPeguam")));
				this.context.put("listBandarTetapbyNegeri", s3);
			} else {
				this.context.put("listBandarTetapbyNegeri", "");
			}

			vm = "app/ppk/frmTambahKaveat.jsp";
		}
		
		else if ("hantarBorangI".equals(submit)) {/*
			String idFail = request.getParameter("idFail");
			
			// CHECK PERMOHONAN
			if (existPermohonan(idFail)) {
				context.put("semakPermohonan", "ada");
			} else {
				myLogger.info("tiada permohonan");
				context.put("semakPermohonan", "tiada");
			}
			
			
			
			myLogger.info("existTukarPemohon(idFail)------ "+existTukarPemohon(idFail));
			myLogger.info("existPetioner(idFail)------ "+existPetioner(idFail));
			
			if (existTukarPemohon(idFail)== true && existPetioner(idFail) == true ) {
				sendI = "tiada";
			}else if (existTukarPemohon(idFail)== true  && existPetioner(idFail) == false ) {
				sendI = "ada";
			}else if (existTukarPemohon(idFail)== false  && existPetioner(idFail) == false ) {
				sendI = "ada";
			}else if (existTukarPemohon(idFail)== false  && existPetioner(idFail) == true ) {
				sendI = "tiada";
			}
			context.put("sendI", sendI);
			// CHECK EXIST PETIONER
			if (existPetioner(idFail)) {
				
//				isExistPetioner = "ada";
//				myLogger.info("isExistPetioner 3--- "+isExistPetioner);
				context.put("semakPetioner", "ada");
				myLogger.info("ada petioner" + context.put("semakPetioner", "ada"));
			
			 }
			
			myLogger.info("isExistPetioner 4--- "+isExistPetioner);
			myLogger.info("isExistPetioner--------------"+isExistPetioner);
			myLogger.info("isExistPetioner context 1--------------"+context.put("isExistPetioner", isExistPetioner));
			context.put("isExistPetioner", isExistPetioner);
			
			

			// CHECK SUCCESS SENT
			if (successSend(idFail)) {
				context.put("successSend", "ya");
			} else {
				context.put("successSend", "tidak");
			}

			Hashtable permohonanMT = getPermohonanMT(user, idFail);
			String noPetisyen = (String) permohonanMT.get("noPetisyen");
			String namaSimati = (String) permohonanMT.get("namaSimati");
			String namaSimatiLain = (String) permohonanMT.get("namaSimatiLain");
			String noKPSimatiBaru = (String) permohonanMT.get("noKPSimatiBaru");
			String noKPSimatiLama = (String) permohonanMT.get("noKPSimatiLama");
			String noKPSimatiLain = (String) permohonanMT.get("noKPSimatiLain");
			String jeniskp = (String) permohonanMT.get("jeniskp");
			String tarikhMati = (String) permohonanMT.get("tarikhMati");
			String nosijilmati = (String) permohonanMT.get("nosijilmati");
			String umurSimati = (String) permohonanMT.get("umursimati");
			String newtarikhmati = (String) permohonanMT.get("newtarikhmati");
			String alamat1simati = (String) permohonanMT.get("alamat1simati");
			String alamat2simati = (String) permohonanMT.get("alamat2simati");
			String alamat3simati = (String) permohonanMT.get("alamat3simati");
			String bandarsimati = (String) permohonanMT.get("bandarsimati");
			String idbandarsimati = (String) permohonanMT.get("idbandarsimati");
			String poskodsimati = (String) permohonanMT.get("poskodsimati");
			String idnegerisimati = (String) permohonanMT.get("idnegerisimati");
			String jantinasimati = (String) permohonanMT.get("jantinasimati");		
			
			String namaPemohon = (String) permohonanMT.get("namaPemohon");
			String noKPBaruPemohon = (String) permohonanMT.get("noKPBaruPemohon");
			String noKPLamaPemohon = (String) permohonanMT.get("noKPLamaPemohon");
			String noKPLainPemohon = (String) permohonanMT.get("noKPLainPemohon");
			String alamat1Pemohon = (String) permohonanMT.get("alamat1");
			String alamat2Pemohon = (String) permohonanMT.get("alamat2");
			String alamat3Pemohon = (String) permohonanMT.get("alamat3");
			String poskodPemohon  = (String) permohonanMT.get("poskod");
			String idBandar = (String) permohonanMT.get("idBandar");
			String idNegeri = (String) permohonanMT.get("idNegeri");
			String idNegeriPemohon = (String) permohonanMT.get("idNegeriPemohon");
			String umurPemohon = (String) permohonanMT.get("umurPemohon");
			String jantinaPemohon = (String) permohonanMT.get("jantinaPemohon");
			
			Hashtable getNegeriPemohon = getNegeriPerayu(idNegeriPemohon);
			String negeriPemohon = (String) getNegeriPemohon.get("namaNegeri");
			Hashtable getBandarPemohon = getBandarPerayu(idBandar);
			String bandarPemohon = (String) getBandarPemohon.get("bandar");
			Hashtable getBandarSimati = getBandarPerayu(idbandarsimati);
			String bandarSimati = (String) getBandarPemohon.get("bandar");
			
			String hubSimatiPemohon = (String) permohonanMT.get("hubSimatiPemohon");
			String idhubSimatiPemohon = (String) permohonanMT.get("ID_HUBSIMATIPEMOHON");
			String idnegeri = (String) permohonanMT.get("idnegeri");
			idPermohonan = (String) permohonanMT.get("idPermohonan");
			String namaDokumen = (String) permohonanMT.get("namaDokumen");
			String docContent = (String) permohonanMT.get("docContent");
			String idSimatiA = (String) permohonanMT.get("idSimati");
			String jumlahharta = (String) permohonanMT.get("jumlahharta");
			double jumlahhartaDouble = Double.parseDouble(jumlahharta);
			context.put("jumlahharta", jumlahhartaDouble);
			myLogger.info("jumlahharta = "+jumlahharta);
			if (noKPSimatiBaru.equals("")) {
				if (logic_F.checkDahUpload(idSimatiA) == false)
				{
					this.context.put("Errormsg", "Error1");
				}
			}
			String dari = "";
			dari = request.getParameter("dari");
			String idPerbicaraan = request.getParameter("idPerbicaraan");
			myLogger.info("dari = "+dari);
			if (dari.equals("KeputusanPerbicaraan"))
			{
				FrmPrmhnnSek8KeputusanPermohonanInternalData.setMaklumatMahkamah2(idPerbicaraan);
				Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData.getMaklumatMahkamah();
				this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);
			}
			else
			{
				FrmPrmhnnSek8KeputusanPermohonanInternalData.setMaklumatMahkamah(idPermohonan);
				Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData.getMaklumatMahkamah();
				this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);
			}
			
			String formatTarikhMati = tarikhMati + "T00:00:00.00";

			// GET TARIKH HANTAR BORANG B IF TELAH DIHANTAR.
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String reportDate = dateFormat.format(date);
			String dateHantarBorangB = reportDate.substring(0, 10) + "T" + reportDate.substring(11) + ".00";

			String hubSimatiPemohon_PM = "";
			String hubSimatiPemohon_OB = "";

			if (!hubSimatiPemohon_PM.equals("")) {
				hubSimatiPemohon = hubSimatiPemohon_PM;

			} else if (!hubSimatiPemohon_OB.equals("")) {
				hubSimatiPemohon = hubSimatiPemohon_OB;
			}

			String tarikhJanaBorangB = (String) permohonanMT.get("tarikhJanaBorangB");
			String kodPejabat = (String) permohonanMT.get("kodPejabat");

			String namaPejabat = getPejabatJKPTGByKodPejabat(kodPejabat);
			String jenisTransaksi = (String) permohonanMT.get("jenisTransaksi");
			

			context.put("noPetisyen", noPetisyen);
			context.put("namaSimati", namaSimati);
			context.put("namaSimatiLain", namaSimatiLain);
			context.put("noKPSimatiBaru", noKPSimatiBaru);
			context.put("noKPSimatiLama", noKPSimatiLama);
			context.put("noKPSimatiLain", noKPSimatiLain);
			context.put("namaDokumen", namaDokumen);
			// context.put("tarikhMati", tarikhMati);
			context.put("tarikhMati", formatTarikhMati);
			context.put("nosijilmati", nosijilmati);
			context.put("newtarikhmati", newtarikhmati);
			context.put("alamat1simati", alamat1simati);
			context.put("alamat2simati", alamat2simati);
			context.put("alamat3simati", alamat3simati);
			context.put("bandarsimati", bandarsimati);
			context.put("idbandarsimati", idbandarsimati);
			context.put("poskodsimati", poskodsimati);
			context.put("idnegerisimati", idnegerisimati);
			context.put("jantinasimati", jantinasimati);
			context.put("umursimati", umurSimati);
			context.put("bandarSimati", bandarSimati);		
			
			
			context.put("namaPemohon", namaPemohon);
			context.put("noKPBaruPemohon", noKPBaruPemohon);
			context.put("noKPLamaPemohon", noKPLamaPemohon);
			context.put("noKPLainPemohon", noKPLainPemohon);
			context.put("umurPemohon", umurPemohon);
			context.put("jantinaPemohon", jantinaPemohon);
			context.put("hubSimatiPemohon", hubSimatiPemohon);
			context.put("tarikhJanaBorangB", tarikhJanaBorangB);
			context.put("kodPejabat", kodPejabat);
			context.put("namaPejabat", namaPejabat);
			context.put("jenisTransaksi", jenisTransaksi);
			context.put("dateFormat", new SimpleDateFormat("dd/MM/yyyy"));
			context.put("idhubSimatiPemohon", idhubSimatiPemohon);
			context.put("hubSimatiPemohonXXXX", hubSimatiPemohon);
			context.put("idnegeri", idnegeri);
			context.put("dateHantarBorangB", dateHantarBorangB);
			context.put("jeniskp", jeniskp);
			context.put("idFail", idFail);
			context.put("idPermohonan", idPermohonan);
			context.put("docContent", docContent);
			
			context.put("alamat1Pemohon", alamat1Pemohon);
			context.put("alamat2Pemohon", alamat2Pemohon);
			context.put("alamat3Pemohon", alamat3Pemohon);
			context.put("poskodPemohon", poskodPemohon);
			context.put("bandarPemohon", bandarPemohon);
			context.put("negeriPemohon", negeriPemohon);
			context.put("idnegeriPemohon", idNegeriPemohon);
			context.put("idbandarPemohon", idBandar);
			
			Hashtable tarikhHantar = getTarikhHantarMT(idFail);
			String tarikhHantarBorangB = (String) tarikhHantar.get("TARIKH_HANTAR");
			context.put("tarikhHantarBrgB", tarikhHantarBorangB);
			//myLogger.info("dateHantarBorangB----- "+context.put("dateHantarBorangB", dateHantarBorangB));


			vm = "app/ppk/integrasi/MahkamahTinggiBorangI.jsp";
			
		

		*/} 
		
		//////////
		
		else if ("borangI".equals(submit)) {
			String idFail = request.getParameter("idFail");
			
			// CHECK PERMOHONAN
			if (existPermohonan(idFail)) {
				context.put("semakPermohonan", "ada");
			} else {
				myLogger.info("tiada permohonan");
				context.put("semakPermohonan", "tiada");
			}
			
			myLogger.info("existTukarPemohon(idFail)------ "+existTukarPemohon(idFail));
			myLogger.info("existPetioner(idFail)------ "+existPetioner(idFail));
			
			if (existTukarPemohon(idFail)== true && existPetioner(idFail) == true ) {
				sendI = "tiada";
			}else if (existTukarPemohon(idFail)== true  && existPetioner(idFail) == false ) {
				sendI = "ada";
			}else if (existTukarPemohon(idFail)== false  && existPetioner(idFail) == false ) {
				sendI = "ada";
			}else if (existTukarPemohon(idFail)== false  && existPetioner(idFail) == true ) {
				sendI = "tiada";
			}
			context.put("sendI", sendI);
			// CHECK EXIST PETIONER
			if (existPetioner(idFail)) {
				
//				isExistPetioner = "ada";
//				myLogger.info("isExistPetioner 3--- "+isExistPetioner);
				context.put("semakPetioner", "ada");
				myLogger.info("ada petioner" + context.put("semakPetioner", "ada"));
			
			 }
			
			/*myLogger.info("isExistPetioner 4--- "+isExistPetioner);
			myLogger.info("isExistPetioner--------------"+isExistPetioner);
			myLogger.info("isExistPetioner context 1--------------"+context.put("isExistPetioner", isExistPetioner));*/
			context.put("isExistPetioner", isExistPetioner);
			
			

			// CHECK SUCCESS SENT
			if (successSend(idFail)) {
				context.put("successSend", "ya");
			} else {
				context.put("successSend", "tidak");
			}

			Hashtable permohonanMT = getPermohonanMT(user, idFail);
			String noPetisyen = (String) permohonanMT.get("noPetisyen");
			String namaSimati = (String) permohonanMT.get("namaSimati");
			String namaSimatiLain = (String) permohonanMT.get("namaSimatiLain");
			String noKPSimatiBaru = (String) permohonanMT.get("noKPSimatiBaru");
			String noKPSimatiLama = (String) permohonanMT.get("noKPSimatiLama");
			String noKPSimatiLain = (String) permohonanMT.get("noKPSimatiLain");
			String jeniskp = (String) permohonanMT.get("jeniskp");
			String tarikhMati = (String) permohonanMT.get("tarikhMati");
			String namaPemohon = (String) permohonanMT.get("namaPemohon");
			String noKPBaruPemohon = (String) permohonanMT.get("noKPBaruPemohon");
			String noKPLamaPemohon = (String) permohonanMT.get("noKPLamaPemohon");
			String noKPLainPemohon = (String) permohonanMT.get("noKPLainPemohon");
			String hubSimatiPemohon = (String) permohonanMT.get("hubSimatiPemohon");
			String idhubSimatiPemohon = (String) permohonanMT.get("ID_HUBSIMATIPEMOHON");
			String idnegeri = (String) permohonanMT.get("idnegeri");
			idPermohonan = (String) permohonanMT.get("idPermohonan");
			String namaDokumen = (String) permohonanMT.get("namaDokumen");
			String docContent = (String) permohonanMT.get("docContent");
			
			
			//COMMENT BY BELLA - MAHKAMAH CEK DENGAN JPN TARIKH MATI SAHAJA
//			String WAKTU_KEMATIAN = (String) permohonanMT.get("WAKTU_KEMATIAN");
//			String JAM = "";
//			String MINIT = "";

//			if (!WAKTU_KEMATIAN.equals("")) {
//				JAM = WAKTU_KEMATIAN.substring(0, 2);
//				MINIT = WAKTU_KEMATIAN.substring(2);
//			}
//			String JENIS_WAKTU_MATI = (String) permohonanMT.get("JENIS_WAKTU_MATI");

//			String waktuMati = waktuMati = "0000";			
//			int jam_1 = 0;
//			if (!JENIS_WAKTU_MATI.equals("")) {
//				if (!JENIS_WAKTU_MATI.equals("1")) {
//					jam_1 = Integer.parseInt(JAM) + 12;
//					waktuMati = Integer.toString(jam_1) + MINIT;
//				} else {
//					waktuMati = WAKTU_KEMATIAN;
//				}
//			} else {
//				waktuMati = "0000";
//			}

			// 2016-06-21T17:24:01.00
//			String formatTarikhMati = tarikhMati + "T" + waktuMati.substring(0, 2) + ":" + waktuMati.substring(2) + ":00.00";
			String formatTarikhMati = tarikhMati + "T00:00:00.00";

			// GET TARIKH HANTAR BORANG B IF TELAH DIHANTAR.
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String reportDate = dateFormat.format(date);
			String dateHantarBorangB = reportDate.substring(0, 10) + "T" + reportDate.substring(11) + ".00";

			String hubSimatiPemohon_PM = "";
			String hubSimatiPemohon_OB = "";

			if (!hubSimatiPemohon_PM.equals("")) {
				hubSimatiPemohon = hubSimatiPemohon_PM;

			} else if (!hubSimatiPemohon_OB.equals("")) {
				hubSimatiPemohon = hubSimatiPemohon_OB;
			}

			String tarikhJanaBorangB = (String) permohonanMT.get("tarikhJanaBorangB");
			String kodPejabat = (String) permohonanMT.get("kodPejabat");

			String namaPejabat = getPejabatJKPTGByKodPejabat(kodPejabat);
			String jenisTransaksi = (String) permohonanMT.get("jenisTransaksi");

			context.put("noPetisyen", noPetisyen);
			context.put("namaSimati", namaSimati);
			context.put("namaSimatiLain", namaSimatiLain);
			context.put("noKPSimatiBaru", noKPSimatiBaru);
			context.put("noKPSimatiLama", noKPSimatiLama);
			context.put("noKPSimatiLain", noKPSimatiLain);
			context.put("namaDokumen", namaDokumen);
			// context.put("tarikhMati", tarikhMati);
			context.put("tarikhMati", formatTarikhMati);
			context.put("namaPemohon", namaPemohon);
			context.put("noKPBaruPemohon", noKPBaruPemohon);
			context.put("noKPLamaPemohon", noKPLamaPemohon);
			context.put("noKPLainPemohon", noKPLainPemohon);
			context.put("hubSimatiPemohon", hubSimatiPemohon);
			context.put("tarikhJanaBorangB", tarikhJanaBorangB);
			context.put("kodPejabat", kodPejabat);
			context.put("namaPejabat", namaPejabat);
			context.put("jenisTransaksi", jenisTransaksi);
			context.put("dateFormat", new SimpleDateFormat("dd/MM/yyyy"));
			context.put("idhubSimatiPemohon", idhubSimatiPemohon);
			context.put("hubSimatiPemohonXXXX", hubSimatiPemohon);
			context.put("idnegeri", idnegeri);
			context.put("dateHantarBorangB", dateHantarBorangB);
			context.put("jeniskp", jeniskp);
			context.put("idFail", idFail);
			context.put("idPermohonan", idPermohonan);
			context.put("docContent", docContent);
			
			Hashtable tarikhHantar = getTarikhHantarMT(idFail);
			String tarikhHantarBorangB = (String) tarikhHantar.get("TARIKH_HANTAR");
			context.put("tarikhHantarBrgB", tarikhHantarBorangB);
			//myLogger.info("dateHantarBorangB----- "+context.put("dateHantarBorangB", dateHantarBorangB));


			vm = "app/ppk/integrasi/MahkamahTinggi.jsp";
			
		

		} 
		
		
		else if ("hantarPermohonanRayuan".equals(submit)) {
			String idFail = request.getParameter("idfail");
			myLogger.info("idFail= "+idFail);
			
			// CHECK PERMOHONAN
			/*if (existPermohonan(idFail)) {
				context.put("semakPermohonan", "ada");
			} else {
				myLogger.info("tiada permohonan");
				context.put("semakPermohonan", "tiada");
			}
			*/
			
			/*
			myLogger.info("existTukarPemohon(idFail)------ "+existTukarPemohon(idFail));
			myLogger.info("existPetioner(idFail)------ "+existPetioner(idFail));
			
			if (existTukarPemohon(idFail)== true && existPetioner(idFail) == true ) {
				sendI = "tiada";
			}else if (existTukarPemohon(idFail)== true  && existPetioner(idFail) == false ) {
				sendI = "ada";
			}else if (existTukarPemohon(idFail)== false  && existPetioner(idFail) == false ) {
				sendI = "ada";
			}else if (existTukarPemohon(idFail)== false  && existPetioner(idFail) == true ) {
				sendI = "tiada";
			}
			context.put("sendI", sendI);
			// CHECK EXIST PETIONER
			if (existPetioner(idFail)) {
				
//				isExistPetioner = "ada";
//				myLogger.info("isExistPetioner 3--- "+isExistPetioner);
				context.put("semakPetioner", "ada");
				myLogger.info("ada petioner" + context.put("semakPetioner", "ada"));
			
			 }*/
			
			/*myLogger.info("isExistPetioner 4--- "+isExistPetioner);
			myLogger.info("isExistPetioner--------------"+isExistPetioner);
			myLogger.info("isExistPetioner context 1--------------"+context.put("isExistPetioner", isExistPetioner));*/
			/*context.put("isExistPetioner", isExistPetioner);*/
			
			

			// CHECK SUCCESS SENT
			if (successSend(idFail)) {
				context.put("successSend", "ya");
			} else {
				context.put("successSend", "tidak");
			}

			Hashtable permohonanMT = getPermohonanMT(user, idFail);
			String noPetisyen = (String) permohonanMT.get("noPetisyen");
			String namaSimati = (String) permohonanMT.get("namaSimati");
			String namaSimatiLain = (String) permohonanMT.get("namaSimatiLain");
			String noKPSimatiBaru = (String) permohonanMT.get("noKPSimatiBaru");
			String noKPSimatiLama = (String) permohonanMT.get("noKPSimatiLama");
			String noKPSimatiLain = (String) permohonanMT.get("noKPSimatiLain");
			String jeniskpSimati = (String) permohonanMT.get("jeniskp");
			String tarikhMati = (String) permohonanMT.get("tarikhMati");
			idPermohonan = (String) permohonanMT.get("idPermohonan");
			
			Hashtable getPerayuMT = getPerayuMT(idPermohonan);
			String namaPerayu = (String) getPerayuMT.get("namaPerayu");
			String noKPBaruPerayu = (String) getPerayuMT.get("noKPBaruPerayu");
			String noKPLamaPerayu = (String) getPerayuMT.get("noKPLamaPerayu");
			String noKPLainPerayu = (String) getPerayuMT.get("noKPLainPerayu");
			String alamat1Perayu = (String) getPerayuMT.get("alamat1Perayu");
			String alamat2Perayu = (String) getPerayuMT.get("alamat2Perayu");
			String alamat3Perayu = (String) getPerayuMT.get("alamat3Perayu");
			String poskodPerayu = (String) getPerayuMT.get("poskodPerayu");
			String idnegeriPerayu = (String) getPerayuMT.get("idnegeriPerayu");
			String idbandarPerayu = (String) getPerayuMT.get("idbandarPerayu");
			
			Hashtable getNegeriPerayu = getNegeriPerayu(idnegeriPerayu);
			String negeriPerayu = (String) getNegeriPerayu.get("namaNegeri");
			Hashtable getBandarPerayu = getBandarPerayu(idbandarPerayu);
			String bandarPerayu = (String) getBandarPerayu.get("bandar");
			
			Hashtable getMahkamah = getMahkamah(idPermohonan);
			String idMahkamah = (String) getMahkamah.get("idMahkamah");
			
			Hashtable getnamaMahkamah = getnamaMahkamah(idMahkamah);
			String namaMahkamah = (String) getnamaMahkamah.get("namaMahkamah");
			
			
			String namaDokumen = (String) permohonanMT.get("namaDokumen");
			String docContent = (String) permohonanMT.get("docContent");
			
			
			
			
			FrmPrmhnnSek8KeputusanPermohonanInternalData
			.setMaklumatMahkamah(idPermohonan);
			Vector listMaklumatMahkamah = FrmPrmhnnSek8KeputusanPermohonanInternalData
			.getMaklumatMahkamah();
			this.context.put("listMaklumatMahkamah", listMaklumatMahkamah);
			
			String formatTarikhMati = tarikhMati + "T00:00:00.00";

			// GET TARIKH HANTAR BORANG B IF TELAH DIHANTAR.
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String reportDate = dateFormat.format(date);
			String dateHantarBorangB = reportDate.substring(0, 10) + "T" + reportDate.substring(11) + ".00";

			String hubSimatiPemohon_PM = "";
			String hubSimatiPemohon_OB = "";

			

			//String tarikhJanaBorangB = (String) permohonanMT.get("tarikhJanaBorangB");
			//String kodPejabat = (String) permohonanMT.get("kodPejabat");

			//String namaPejabat = getPejabatJKPTGByKodPejabat(kodPejabat);
			//String jenisTransaksi = (String) permohonanMT.get("jenisTransaksi");

			context.put("noPetisyen", noPetisyen);
			context.put("namaSimati", namaSimati);
			context.put("namaSimatiLain", namaSimatiLain);
			context.put("noKPSimatiBaru", noKPSimatiBaru);
			context.put("noKPSimatiLama", noKPSimatiLama);
			context.put("noKPSimatiLain", noKPSimatiLain);
			context.put("namaDokumen", namaDokumen);
			// context.put("tarikhMati", tarikhMati);
			context.put("tarikhMati", formatTarikhMati);
			context.put("namaPerayu", namaPerayu);
			context.put("noKPBaruPerayu", noKPBaruPerayu);
			context.put("noKPLamaPerayu", noKPLamaPerayu);
			context.put("noKPLainPerayu", noKPLainPerayu);
			context.put("alamat1Perayu", alamat1Perayu);
			context.put("alamat2Perayu", alamat2Perayu);
			context.put("alamat3Perayu", alamat3Perayu);
			context.put("poskodPerayu", poskodPerayu);	
			context.put("negeriPerayu", negeriPerayu);	
			context.put("bandarPerayu", bandarPerayu);	
			
			context.put("idMahkamah", idMahkamah);	
			context.put("namaMahkamah", namaMahkamah);	
			
			//context.put("hubSimatiPemohon", hubSimatiPemohon);
			//context.put("tarikhJanaBorangB", tarikhJanaBorangB);
			//context.put("kodPejabat", kodPejabat);
			//context.put("namaPejabat", namaPejabat);
			//context.put("jenisTransaksi", jenisTransaksi);
			context.put("dateFormat", new SimpleDateFormat("dd/MM/yyyy"));
			//context.put("idhubSimatiPemohon", idhubSimatiPemohon);
			//context.put("hubSimatiPemohonXXXX", hubSimatiPemohon);
			//context.put("idnegeri", idnegeri);
			//context.put("idbandar", idbandar);
			
			//context.put("dateHantarBorangB", dateHantarBorangB);
			context.put("jeniskpPerayu", "IC");
			context.put("idFail", idFail);
			context.put("idPermohonan", idPermohonan);
			context.put("docContent", docContent);
			
			Hashtable tarikhHantar = getTarikhHantarMT(idFail);
			String tarikhHantarBorangB = (String) tarikhHantar.get("TARIKH_HANTAR");
			context.put("tarikhHantarBrgB", tarikhHantarBorangB);
			//myLogger.info("dateHantarBorangB----- "+context.put("dateHantarBorangB", dateHantarBorangB));


			vm = "app/ppk/integrasi/MahkamahTinggiRayuan.jsp";
			
		

		} 
		
		
		else if ("hantarPermohonan".equals(submit)) {/*
			String idFail = request.getParameter("idFail");
			Hashtable permohonanMT = getPermohonanMT(user, idFail);
			String docContent = (String) permohonanMT.get("docContent");//(String) permohonanMT.get("docContent");

			String transactionID = "";
			Calendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			transactionID = Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.YEAR)), 4) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MONTH) + 1), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.DATE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MINUTE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.SECOND)), 2);
			String tarikhJanaBorangB = request.getParameter("tarikhJanaBorangB");			
			String tarikhMati = request.getParameter("tarikhMati");
			String DDate = tarikhMati.substring(0, 10);

			String[] parts = DDate.split("-");
			String part1 = parts[0];
			String part2 = parts[1];
			String part3 = parts[2];
			DDate = part3 + "/" + part2 + "/" + part1;

			
//			String BDate = tarikhJanaBorangB.substring(0, 10);
//			
//						String[] Bparts = BDate.split("-");
//						String Bpart1 = Bparts[0];
//						String Bpart2 = Bparts[1];
//						String Bpart3 = Bparts[2];
//						BDate = Bpart3 + "/" + Bpart2 + "/" + Bpart1;
						
			String BDate = "";
			if(tarikhJanaBorangB != ""){
			BDate = tarikhJanaBorangB.substring(0, 10);

			String[] Bparts = BDate.split("-");
			String Bpart1 = Bparts[0];
			String Bpart2 = Bparts[1];
			String Bpart3 = Bparts[2];
			BDate = Bpart3 + "/" + Bpart2 + "/" + Bpart1;
			}

			Db db = null;
			String sql = "";
			String sql2 = "";

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			Statement stmt2 = db.getStatement();
			SQLRenderer kptsn = new SQLRenderer();
			String noFail = request.getParameter("noPetisyen");
			r.add("PETISYENNO", request.getParameter("noPetisyen"));
			r.add("NAMASIMATI", request.getParameter("namaSimati"));
			r.add("NAMASIMATILAIN", request.getParameter("namaSimatiLain"));
			r.add("NOKPBARUSIMATI", request.getParameter("noKPSimatiBaru"));
			r.add("NOKPLAMASIMATI", request.getParameter("noKPSimatiLama"));
			r.add("NOKPLAINSIMATI", request.getParameter("noKPSimatiLain"));
			r.add("TARIKHMATI", r.unquote("to_date('" + DDate + "','DD/MM/YYYY')"));
			r.add("NAMAPEMOHON", request.getParameter("namaPemohon"));
			r.add("TARIKHJANABRGB", r.unquote("to_date('" + BDate + "','DD/MM/YYYY')"));
			r.add("NOKPBARUPEMOHON", request.getParameter("noKPPemohon"));
			r.add("HUBUNGAN", request.getParameter("hubSimatiPemohon"));
			r.add("KODPEJABAT", request.getParameter("kodPejabat"));
			r.add("JENISTRANSAKSI", request.getParameter("applicationType"));
			r.add("FLAG_REP", "0");
			r.add("FLAG_NEGERI", request.getParameter("idnegeri"));// ecah
			r.add("JENISKP", request.getParameter("jeniskp"));
			r.add("TARIKH_HANTAR", r.unquote("to_date('" + sdf.format(cal.getTime()) + "','DD/MM/YYYY')"));
			r.add("ID_TRANSAKSI", transactionID);
			
			context.put("noPetisyen", request.getParameter("noPetisyen"));
			context.put("namaSimati", request.getParameter("namaSimati"));
			context.put("namaLainSimati", request.getParameter("namaSimatiLain"));
			context.put("MyIDSimati", request.getParameter("noKPSimatiBaru"));
			context.put("ICLamaSimati", request.getParameter("noKPSimatiLama"));
			context.put("ICLainSimati", request.getParameter("noKPSimatiLain"));
			context.put("tarikhMati", request.getParameter("tarikhMati"));
			context.put("namaPemohon", request.getParameter("namaPemohon"));
			context.put("MyIDPemohon", request.getParameter("noKPPemohon"));
			context.put("hubungan", request.getParameter("hubSimatiPemohon"));
			context.put("tarikhJanaBorangB", request.getParameter("tarikhJanaBorangB"));
			context.put("tarikhHantarBorangB", request.getParameter("tarikhHantarBorangB"));
			context.put("kodPejabat", request.getParameter("kodPejabat"));
			context.put("namaPejabat", request.getParameter("namaPejabat"));
			context.put("idnegeri", request.getParameter("idnegeri"));
			context.put("jeniskp", request.getParameter("jeniskp"));
			context.put("docContent", docContent);
			
			if (!"".equals(fFrom)) {
				context.put("fFrom", "ya");
			} else {
				context.put("fFrom", "tidak");
			}

			// aishah start integration ecourt
			MTManager manager = new MTManager();

			String returnMessage = "";
			returnMessage = manager.sendMaklumat2Court(
					request.getParameter("noPetisyen"),
					request.getParameter("namaSimati"),
					request.getParameter("namaSimatiLain"),
					request.getParameter("noKPSimatiBaru"),
					request.getParameter("noKPSimatiLama"),
					request.getParameter("noKPSimatiLain"),
					request.getParameter("tarikhMati"),
					request.getParameter("namaPemohon"),
					request.getParameter("noKPPemohon"),
					request.getParameter("hubSimatiPemohon"),
					request.getParameter("tarikhJanaBorangB"),
					request.getParameter("tarikhHantarBorangB"),
					request.getParameter("kodPejabat"),
					getPejabatJKPTGByKodPejabat(request.getParameter("kodPejabat")),
					request.getParameter("idnegeri"),
					request.getParameter("jeniskp"),
					docContent,
					request.getParameter("applicationType"), transactionID);
			

			if (!returnMessage.equals("")) {

				String code = returnMessage.substring(0, 1);
				String details = returnMessage.substring(2);

				if (code.equals("0")) {
					if (manager.getIdKadBiru() != null) {
						r.add("IDKADBIRU", manager.getIdKadBiru());
						//r.add("IDKADBIRU", "00001");
					}
					sql = r.getSQLInsert("TBLINTMTPERMOHONAN");
					stmt.executeUpdate(sql);
					myLogger.info("getSQLInsert:::: "+sql);

					// TODO - update tarikh hantar borang B di
					// TBLPPKKEPUTUSANPERMOHONAN
					long idKeputusanPermohonan = DB.getNextID("TBLPPKKEPUTUSANPERMOHONAN_SEQ");
					kptsn.add("ID_KEPUTUSANPERMOHONAN", idKeputusanPermohonan);
					kptsn.add("ID_PERMOHONAN", idPermohonan);
					kptsn.add("TARIKH_HANTAR_BORANGB", r.unquote("SYSDATE"));
					sql2 = kptsn.getSQLInsert("TBLPPKKEPUTUSANPERMOHONAN");
					stmt2.executeUpdate(sql2);

					vm = "app/ppk/integrasi/MahkamahTinggiSuccess.jsp";
					
					IntLogManager.recordLogMT(noFail, "I", "O", "Y", "SUCCESS");
				} else {

					context.put("details", details);
					vm = "app/ppk/integrasi/MahkamahTinggiFailed.jsp";

					IntLogManager.recordLogMT(noFail, "I", "O", "T", details);
				}

			} else {
				context.put("details", returnMessage);
				vm = "app/ppk/integrasi/MahkamahTinggiFailed.jsp";
				
				IntLogManager.recordLogMT(noFail, "I", "O", "T", returnMessage);
			}		

		*/} 
		
		else if ("hantarPermohonanBorangI".equals(submit)) {/*
			myLogger.info("hantarPermohonanBorangI");
			String idFail = request.getParameter("idFail");
			Hashtable permohonanMT = getPermohonanMT(user, idFail);
			String docContent = (String) permohonanMT.get("docContent");//(String) permohonanMT.get("docContent");

			String transactionID = "";
			Calendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			transactionID = Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.YEAR)), 4) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MONTH) + 1), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.DATE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MINUTE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.SECOND)), 2);
			String tarikhJanaBorangB = request.getParameter("tarikhJanaBorangB");			
			String tarikhMati = request.getParameter("tarikhMati");
			String DDate = tarikhMati.substring(0, 10);

			String[] parts = DDate.split("-");
			String part1 = parts[0];
			String part2 = parts[1];
			String part3 = parts[2];
			DDate = part3 + "/" + part2 + "/" + part1;

			
			// aishah start integration ecourt
			myLogger.info("hantarPermohonanBorangI11");
			MTManagerCivilRegisterCase manager = new MTManagerCivilRegisterCase();
			myLogger.info("hantarPermohonanBorangI22");
			String returnMessage = "";
			returnMessage = manager.sendMaklumat2Court(
				    request.getParameter("noPetisyen"),
					request.getParameter("namaSimati"),
					request.getParameter("namaSimatiLain"),
					request.getParameter("noKPSimatiBaru"),
					request.getParameter("noKPSimatiLama"),
					request.getParameter("noKPSimatiLain"),
					request.getParameter("tarikhMati"),
					request.getParameter("jantinasimati"),
					request.getParameter("umursimati"),
					request.getParameter("nosijilmati"),
					request.getParameter("newtarikhmati"),
					request.getParameter("alamat1simati"),
					request.getParameter("alamat2simati"),
					request.getParameter("alamat3simati"),
					request.getParameter("bandarsimati"),
					request.getParameter("bandarSimati"),
					request.getParameter("poskodsimati"),
					request.getParameter("idnegerisimati"),
										
					request.getParameter("namaPemohon"),
					request.getParameter("noKPPemohon"),
					request.getParameter("hubSimatiPemohon"),
					request.getParameter("alamat1Pemohon"),
					request.getParameter("alamat2Pemohon"),
					request.getParameter("alamat3Pemohon"),
					request.getParameter("poskodPemohon"),
					request.getParameter("bandarPemohon"),
					request.getParameter("idbandarPemohon"),
					request.getParameter("idnegeriPemohon"),
					request.getParameter("idMahkamah"),
					request.getParameter("namaDokumen"),
					docContent,
					request.getParameter("applicationType"), transactionID, 
					request.getParameter("umurPemohon"),
					request.getParameter("jumlahharta"),
					request.getParameter("jantinaPemohon"));
			

			if (!returnMessage.equals("")) {

				String code = returnMessage.substring(0, 1);
				String details = returnMessage.substring(2);
				
				context.put("namaPemohon", request.getParameter("namaPemohon"));
				context.put("noKPPemohon", request.getParameter("noKPPemohon"));
				context.put("noKPSimatiBaru", request.getParameter("noKPSimatiBaru"));
				context.put("noKPSimatiLama", request.getParameter("noKPSimatiLama"));
				context.put("noKPSimatiLain", request.getParameter("noKPSimatiLain"));

				if (code.equals("0")) {
					if (manager.getIdKadBiru() != null) {
						r.add("IDKADBIRU", manager.getIdKadBiru());
						//r.add("IDKADBIRU", "00001");
					}
					sql = r.getSQLInsert("TBLINTMTPERMOHONAN");
					stmt.executeUpdate(sql);
					myLogger.info("getSQLInsert:::: "+sql);

					// TODO - update tarikh hantar borang B di
					// TBLPPKKEPUTUSANPERMOHONAN
					long idKeputusanPermohonan = DB.getNextID("TBLPPKKEPUTUSANPERMOHONAN_SEQ");
					kptsn.add("ID_KEPUTUSANPERMOHONAN", idKeputusanPermohonan);
					kptsn.add("ID_PERMOHONAN", idPermohonan);
					kptsn.add("TARIKH_HANTAR_BORANGB", r.unquote("SYSDATE"));
					sql2 = kptsn.getSQLInsert("TBLPPKKEPUTUSANPERMOHONAN");
					stmt2.executeUpdate(sql2);

					vm = "app/ppk/integrasi/MahkamahTinggiSuccessBorangI.jsp";
					
					IntLogManager.recordLogMT("JKPTG", "I", "O", "Y", "SUCCESS");
				} else {

					context.put("details", details);
					vm = "app/ppk/integrasi/MahkamahTinggiFailed.jsp";

					IntLogManager.recordLogMT("JKPTG", "I", "O", "T", details);
				}

			} else {
				context.put("details", returnMessage);
				vm = "app/ppk/integrasi/MahkamahTinggiFailed.jsp";
				
				IntLogManager.recordLogMT("JKPTG", "I", "O", "T", returnMessage);
			}		

		*/} 
		
		else if ("hantarPermohonanRayuan2".equals(submit)) {/*
			myLogger.info("hantarPermohonanRayuan2");
			String idFail = request.getParameter("idFail");
			Hashtable permohonanMT = getPermohonanMT(user, idFail);
			String docContent = (String) permohonanMT.get("docContent");//(String) permohonanMT.get("docContent");

			String transactionID = "";
			Calendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			transactionID = Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.YEAR)), 4) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MONTH) + 1), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.DATE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MINUTE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.SECOND)), 2);
			String tarikhJanaBorangB = request.getParameter("tarikhJanaBorangB");			
			String tarikhMati = request.getParameter("tarikhMati");
			String DDate = tarikhMati.substring(0, 10);

			String[] parts = DDate.split("-");
			String part1 = parts[0];
			String part2 = parts[1];
			String part3 = parts[2];
			DDate = part3 + "/" + part2 + "/" + part1;

			
			// aishah start integration ecourt
			myLogger.info("hantarPermohonanRayuan2");
			MTManagerCivilRegisterCase manager = new MTManagerCivilRegisterCase();
			myLogger.info("hantarPermohonanRayuan2");
			String returnMessage = "";
			returnMessage = manager.sendMaklumat2Court16A(
				    request.getParameter("noPetisyen"),
					request.getParameter("namaSimati"),
					request.getParameter("namaSimatiLain"),
					request.getParameter("noKPSimatiBaru"),
					request.getParameter("noKPSimatiLama"),
					request.getParameter("noKPSimatiLain"),
					request.getParameter("tarikhMati"),
					request.getParameter("namaPerayu"),
					request.getParameter("noKPBaruPerayu"),
					request.getParameter("alamat1Perayu"),
					request.getParameter("alamat2Perayu"),
					request.getParameter("alamat3Perayu"),
					request.getParameter("poskodPerayu"),
					request.getParameter("bandarPerayu"),
					request.getParameter("idbandarPerayu"),
					request.getParameter("idnegeriPerayu"),
					request.getParameter("idMahkamah"),
					request.getParameter("namaDokumen"),
					docContent,
					request.getParameter("applicationType"), transactionID);
			
			context.put("namaPerayu", request.getParameter("namaPerayu"));
			context.put("noKPBaruPerayu", request.getParameter("noKPBaruPerayu"));
			context.put("noKPSimatiBaru", request.getParameter("noKPSimatiBaru"));
			context.put("noKPSimatiLama", request.getParameter("noKPSimatiLama"));
			context.put("noKPSimatiLain", request.getParameter("noKPSimatiLain"));
			if (!returnMessage.equals("")) {

				String code = returnMessage.substring(0, 1);
				String details = returnMessage.substring(2);

				if (code.equals("0")) {
					
					vm = "app/ppk/integrasi/MahkamahTinggiSuccessRayuan.jsp";
					
					IntLogManager.recordLogMT("JKPTG", "I", "O", "Y", "SUCCESS");
				} else {

					context.put("details", details);
					vm = "app/ppk/integrasi/MahkamahTinggiFailed.jsp";

					IntLogManager.recordLogMT("JKPTG", "I", "O", "T", details);
				}

			} else {
				context.put("details", returnMessage);
				vm = "app/ppk/integrasi/MahkamahTinggiFailed.jsp";
				
				IntLogManager.recordLogMT("JKPTG", "I", "O", "T", returnMessage);
			}		

		*/} 
		
		
		else if ("hantarPermohonanPetioner".equals(submit)) {/*
			
			String transactionID = "";
			Calendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			transactionID = Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.YEAR)), 4) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MONTH) + 1), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.DATE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MINUTE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.SECOND)), 2);


			String tarikhMati = request.getParameter("tarikhMati");
			String DDate = tarikhMati.substring(0, 10);

			String[] parts = DDate.split("-");
			String part1 = parts[0];
			String part2 = parts[1];
			String part3 = parts[2];
			DDate = part3 + "/" + part2 + "/" + part1;

			String tarikhJanaBorangB = request.getParameter("tarikhJanaBorangB");
			String BDate = "";
			if(tarikhJanaBorangB != ""){
			BDate = tarikhJanaBorangB.substring(0, 10);

			String[] Bparts = BDate.split("-");
			String Bpart1 = Bparts[0];
			String Bpart2 = Bparts[1];
			String Bpart3 = Bparts[2];
			BDate = Bpart3 + "/" + Bpart2 + "/" + Bpart1;
			}
			
//			String BDate = tarikhJanaBorangB.substring(0, 10);
//
//			String[] Bparts = BDate.split("-");
//			String Bpart1 = Bparts[0];
//			String Bpart2 = Bparts[1];
//			String Bpart3 = Bparts[2];
//			BDate = Bpart3 + "/" + Bpart2 + "/" + Bpart1;

			Db db = null;
			String sql = "";

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			String id_Permohonan = request.getParameter("idPermohonan");
			String noFail = request.getParameter("noPetisyen");
			context.put("noPetisyen", request.getParameter("noPetisyen"));
			context.put("namaSimati", request.getParameter("namaSimati"));
			context.put("namaLainSimati", request.getParameter("namaSimatiLain"));
			context.put("MyIDSimati", request.getParameter("noKPSimatiBaru"));
			context.put("ICLamaSimati", request.getParameter("noKPSimatiLama"));
			context.put("ICLainSimati", request.getParameter("noKPSimatiLain"));
			context.put("tarikhMati", request.getParameter("tarikhMati"));
			context.put("namaPemohon", request.getParameter("namaPemohon"));
			context.put("MyIDPemohon", request.getParameter("noKPPemohon"));
			context.put("hubungan", request.getParameter("hubSimatiPemohon"));
			context.put("tarikhJanaBorangB", request.getParameter("tarikhJanaBorangB"));
			context.put("tarikhHantarBorangB", request.getParameter("tarikhHantarBorangB"));
			context.put("kodPejabat", request.getParameter("kodPejabat"));
			context.put("namaPejabat", request.getParameter("namaPejabat"));
			context.put("idnegeri", request.getParameter("idnegeri"));
			context.put("jeniskp", request.getParameter("jeniskp"));
			//context.put("namaDokumen", request.getParameter("namaDokumen"));
			context.put("docContent", request.getParameter("docContent"));
			

			if (!"".equals(fFrom)) {
				context.put("fFrom", "ya");
			} else {
				context.put("fFrom", "tidak");
			}

			String no_fail = request.getParameter("noPetisyen");
			String blueCardId = "";
			// aishah add 26072017
			String sqlGetBlueCardId = "SELECT IDKADBIRU FROM TBLINTMTPERMOHONAN WHERE FLAG_AKTIF = 'Y' AND  PETISYENNO = '" + no_fail + "'";

			ResultSet rsKadBiru = stmt.executeQuery(sqlGetBlueCardId);
			if (rsKadBiru.next()) {
				if (rsKadBiru.getString("IDKADBIRU") != null) {
					blueCardId = rsKadBiru.getString("IDKADBIRU");
				}
			}		

			// aishah start integration ecourt
			MTManager manager = new MTManager();

			String returnMessage = "";
			returnMessage = manager.sendMaklumat2CourtPetioner(
					request.getParameter("noPetisyen"),
					request.getParameter("namaSimati"),
					request.getParameter("namaSimatiLain"),
					request.getParameter("noKPSimatiBaru"),
					request.getParameter("noKPSimatiLama"),
					request.getParameter("noKPSimatiLain"),
					request.getParameter("tarikhMati"),
					request.getParameter("namaPemohon"),
					request.getParameter("noKPPemohon"),
					request.getParameter("hubSimatiPemohon"),
					request.getParameter("tarikhJanaBorangB"),
					request.getParameter("tarikhHantarBorangB"),
					request.getParameter("kodPejabat"),
					getPejabatJKPTGByKodPejabat(request.getParameter("kodPejabat")),
					request.getParameter("idnegeri"),
					request.getParameter("jeniskp"),
					request.getParameter("docContent"),
					request.getParameter("applicationType"), blueCardId, transactionID);

			if (!returnMessage.equals("")) {

				String code = returnMessage.substring(0, 1);
				String details = returnMessage.substring(2);

				if (code.equals("0")) {
					sql = "UPDATE TBLINTMTPERMOHONAN SET FLAG_AKTIF = 'T' WHERE PETISYENNO = '" + no_fail + "'";
					stmt.executeUpdate(sql);
					
					r = new SQLRenderer();
					r.add("PETISYENNO", request.getParameter("noPetisyen"));
					r.add("NAMASIMATI", request.getParameter("namaSimati"));
					r.add("NAMASIMATILAIN", request.getParameter("namaSimatiLain"));
					r.add("NOKPBARUSIMATI", request.getParameter("noKPSimatiBaru"));
					r.add("NOKPLAMASIMATI", request.getParameter("noKPSimatiLama"));
					r.add("NOKPLAINSIMATI", request.getParameter("noKPSimatiLain"));
					r.add("TARIKHMATI", r.unquote("to_date('" + DDate + "','DD/MM/YYYY')"));
					r.add("NAMAPEMOHON", request.getParameter("namaPemohon"));
					r.add("TARIKHJANABRGB", r.unquote("to_date('" + BDate + "','DD/MM/YYYY')"));
					r.add("NOKPBARUPEMOHON", request.getParameter("noKPPemohon"));
					r.add("HUBUNGAN", request.getParameter("hubSimatiPemohon"));
					r.add("KODPEJABAT", request.getParameter("kodPejabat"));
					r.add("JENISTRANSAKSI", request.getParameter("applicationType"));
					r.add("FLAG_REP", "0");
					r.add("FLAG_NEGERI", request.getParameter("idnegeri"));// ecah
					r.add("JENISKP", request.getParameter("jeniskp"));
					r.add("TARIKH_HANTAR", r.unquote("to_date('" + sdf.format(cal.getTime()) + "','DD/MM/YYYY')"));
					r.add("ID_TRANSAKSI", transactionID);
					r.add("IDKADBIRU", blueCardId);
					sql = r.getSQLInsert("TBLINTMTPERMOHONAN");
					stmt.executeUpdate(sql);

					String idKeputusanPermohonan = "";
					String sqlKeputusanPermohonan = "SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN WHERE ID_PERMOHONAN = '" + id_Permohonan + "'";
					ResultSet rsKeputusanPermohonan = stmt.executeQuery(sqlKeputusanPermohonan);
					
					if (rsKeputusanPermohonan.next()) {
						if (rsKeputusanPermohonan.getString("ID_KEPUTUSANPERMOHONAN") != null) {
							idKeputusanPermohonan = rsKeputusanPermohonan.getString("ID_KEPUTUSANPERMOHONAN");
							
							r = new SQLRenderer();
							r.update("ID_KEPUTUSANPERMOHONAN", idKeputusanPermohonan);
							r.add("TARIKH_HANTAR_BORANGB", r.unquote("SYSDATE"));
							sql = r.getSQLUpdate("TBLPPKKEPUTUSANPERMOHONAN");
							stmt.executeUpdate(sql);
						}
					}

					if (db != null) { db.close(); }
					vm = "app/ppk/integrasi/MahkamahTinggiSuccess.jsp";
					
					IntLogManager.recordLogMT(noFail, "P", "O", "Y", "SUCCESS");
					
				} else {

					context.put("details", details);
					vm = "app/ppk/integrasi/MahkamahTinggiFailed.jsp";

					IntLogManager.recordLogMT(noFail, "P", "O", "T", details);
				}

			} else {
				context.put("details", returnMessage);
				vm = "app/ppk/integrasi/MahkamahTinggiFailed.jsp";
				
				IntLogManager.recordLogMT(noFail, "P", "O", "T", returnMessage);
			}
		*/}
		FrmSenaraiFailKeputusanPermohonanInternalData.setListNegeri();
		Vector listNegeri = FrmSenaraiFailKeputusanPermohonanInternalData
				.getListNegeri();
		this.context.put("ListNegeri", listNegeri);

		// ##
		Vector listnegeri = logic_A.getListnegeri();
		this.context.put("listnegeri", listnegeri);
		Template template = this.engine.getTemplate(vm);
		return template;
	}

	private boolean existPermohonan(String idFail) {
		boolean existPermohonan = false;
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PFD.ID_FAIL PERMOHONAN FROM TBLINTMTPERMOHONAN MTP, TBLPFDFAIL PFD"
					+ " WHERE MTP.PETISYENNO = PFD.NO_FAIL AND PFD.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);
			myLogger.info("existPermohonan::::: "+sql);
			if (rs.next()) {
				existPermohonan = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}

		return existPermohonan;
	}

	private boolean existPetioner(String idFail) {
		boolean existPetioner = true;
		Db db = null;
		String sql = "";
		String sqlTukar = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
		

			sql = "SELECT TBLINTMTPERMOHONAN.NAMAPEMOHON, REPLACE(TBLINTMTPERMOHONAN.NOKPBARUPEMOHON, '-', '') NOKPBARUPEMOHON,"
					+ " TBLPPKPEMOHON.NAMA_PEMOHON, TBLPPKPEMOHON.NO_KP_BARU, TBLPPKPEMOHON.NO_KP_LAMA, TBLPPKPEMOHON.NO_KP_LAIN"
					+ " FROM TBLPFDFAIL, TBLINTMTPERMOHONAN, TBLPPKPERMOHONAN, TBLPPKPEMOHON"
					+ " WHERE TBLPFDFAIL.NO_FAIL = TBLINTMTPERMOHONAN.PETISYENNO AND TBLINTMTPERMOHONAN.FLAG_AKTIF = 'Y'"
					+ " AND TBLPFDFAIL.ID_FAIL = TBLPPKPERMOHONAN.ID_FAIL AND TBLPPKPERMOHONAN.ID_PEMOHON = TBLPPKPEMOHON.ID_PEMOHON"
					+ " AND TBLPFDFAIL.ID_FAIL = '" + idFail + "'";
			//System.out.println("sql petitioner"+sql);
			
			
			
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {//if ada idkadbiru
					String namaPemohonMT = rs.getString("NAMAPEMOHON");
					String noKPMT = rs.getString("NOKPBARUPEMOHON");

					String namaPemohon = rs.getString("NAMA_PEMOHON");
					String noKPBaru = rs.getString("NO_KP_BARU");
					String noKPLama = rs.getString("NO_KP_LAMA");
					String noKPLain = rs.getString("NO_KP_LAIN");
					
					if (noKPBaru != null){
						if (!"".equals(noKPBaru)) {
							if (noKPMT.equals(noKPBaru)) {
								existPetioner = false;
								
							}else{
								existPetioner = true;
							}
							
						} else if (namaPemohon.equals(namaPemohonMT)){
							existPetioner = false;
							
						} else {
							existPetioner = true;
						}
					} else if (namaPemohon.equals(namaPemohonMT)){
						existPetioner = false;
						
					}
				
				}else{
					existPetioner = false;
				}
				
				
		
			

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		myLogger.info("existPetioner------ "+existPetioner);
		return existPetioner;
	}
	
	private boolean existTukarPemohon(String idFail) {
		boolean tukarPemohon = false;
		Db db = null;
	
		String sqlTukar = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sqlTukar = "SELECT A.ID_FAIL, E.ID_SIMATI" +
					" FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKPERMOHONANSIMATI C, TBLPPKSIMATI D, TBLPPKTUKARPEMOHON E" +
					" WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_SIMATI = D.ID_SIMATI AND D.ID_SIMATI = E.ID_SIMATI" +
					" AND A.ID_FAIL = '" + idFail + "'";
			
			//System.out.println("sql petitioner"+sqlTukar);
			
			
			ResultSet rs1 = stmt.executeQuery(sqlTukar);
			if (rs1.next()) {//if ada buat pertukaran pemohon
				tukarPemohon  =  true;
			}
			
			

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		myLogger.info("tukarPemohon------ "+tukarPemohon);
		return tukarPemohon;
	}

	private boolean successSend(String idFail) {
		boolean successSend = false;
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PFD.ID_FAIL "
					+ " FROM TBLINTMTPERMOHONAN MTP, TBLPFDFAIL PFD"
					+ " WHERE MTP.PETISYENNO = PFD.NO_FAIL AND MTP.IDKADBIRU IS NOT NULL"
					+ " AND PFD.ID_FAIL = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				successSend = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}

		return successSend;
	}

	public Hashtable<String,String> getPermohonanMT(String userLogin, String idFail) {
		Db db = null;
		String sql = "";
		Hashtable<String,String> permohonanMT = new Hashtable<String,String>();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT P.ID_PERMOHONAN, p.jumlah_harta_tarikhmohon as jumlahharta, F.NO_FAIL AS noPetisyen,"
					+ " SM.NAMA_SIMATI AS namaSimati,"
					+ " SM.ID_SIMATI AS idSimati,"					
					+ " SM.NAMA_LAIN AS namaSimatiLain,"
					+ " SM.NO_KP_BARU AS noKPSimatiBaru,"
					+ " SM.NO_KP_LAMA AS noKPSimatiLama,"
					+ " SM.NO_KP_LAIN AS noKPSimatiLain,"
					+ " sm.no_sijil_mati AS nosijilmati, sm.umur as umursimati, sm.tarikh_mati as newtarikhmati, sm.alamat_1 as alamat1simati, "
					+ " sm.alamat_2 as alamat2simati, sm.alamat_3 as alamat3simati,"
					+ " sm.bandar as bandarsimati, sm.id_bandar as idbandarsimati, sm.poskod as poskodsimati, sm.id_negeri as idnegerisimati,  sm.jantina as jantinasimati,"
					+ " decode( sm.JENIS_KP , 4,'PP',5,'SO',6,'PO',7,'OT',13,'PDC') as jeniskp,"
					+ " (SELECT TO_CHAR(MAX(SM.TARIKH_MATI), 'YYYY-MM-DD') AS TARIKH_MATI_SIMATI"
					+ " FROM DUAL) AS tarikhMati,"
					+ " PM.NAMA_PEMOHON AS namaPemohon,"
					+ " PM.NO_KP_BARU AS noKPBaruPemohon,"
					+ " PM.NO_KP_LAMA AS noKPLamaPemohon,"
					+ " PM.NO_KP_LAIN AS noKPLainPemohon,"
					+ " PM.UMUR AS umurPemohon,"
					+ " PM.JANTINA AS jantinaPemohon,"
					+ " PM.ALAMAT_1 AS alamat1,"
					+ " PM.ALAMAT_2 AS alamat2,"
					+ " PM.ALAMAT_3 AS alamat3,"
					+ " PM.POSKOD AS poskod,"
					+ " PM.ID_BANDAR AS idBandar,"
					+ " PM.ID_NEGERI AS idNegeriPemohon,"
					+ " RS.KETERANGAN AS hubSimatiPemohon,"
					+ " (SELECT TO_CHAR(MAX(SSF.TARIKH_MASUK), 'YYYY-MM-DD') AS TARIKH_HANTAR_BORANGB"
					+ " FROM TBLRUJSUBURUSANSTATUSFAIL SSF,"
					+ " TBLRUJSUBURUSANSTATUS SST,"
					+ " TBLRUJSTATUS ST"
					+ " WHERE SSF.ID_FAIL = F.ID_FAIL"
					+ " AND SSF.ID_SUBURUSANSTATUS = SST.ID_SUBURUSANSTATUS"
					+ " AND ST.ID_STATUS = SST.ID_STATUS) AS tarikhJanaBorangB "					
//					+ " ( select RPU.ID_PEJABATJKPTG " 
//					+  " from  TBLRUJPEJABATURUSAN RPU,TBLRUJPEJABATJKPTG RP" 
//					+  " where RPU.ID_PEJABATJKPTG = RP.ID_PEJABATJKPTG " 
//					+  " and RPU.ID_JENISPEJABAT = 22 " 
// 					+  " and RP.ID_SEKSYEN = 2 " 
//					+  " and RPU.ID_DAERAHURUS = P.ID_DAERAHMHN " 
//					+  ") AS kodPejabat,"		
					+ ",PEJ.ID_PEJABATJKPTG KODPEJABAT "  
					+ ", (SELECT UI.ID_NEGERI FROM USERS U,"
					+ " USERS_INTERNAL UI WHERE UI.USER_ID = U.USER_ID"
					+ " AND U.USER_LOGIN = '"
					+ userLogin
					+ "') AS idnegeri,"
					+ " ('I') AS jenisTransaksi, ob.id_saudara AS ID_HUBSIMATIPEMOHON, "
					+ " SM.WAKTU_KEMATIAN AS WAKTU_KEMATIAN, SM.JENIS_WAKTU_MATI, dsm.nama_dokumen, dsm.kandungan "
					+ " FROM TBLPFDFAIL F,"
					+ " TBLPPKPERMOHONAN P,"
					+ " TBLPPKPERMOHONANSIMATI PSM,"
					+ " TBLPPKSIMATI SM,"
					+ " TBLPPKPEMOHON PM,"
					+ " TBLPPKOB OB,"
					+ " TBLPPKRUJSAUDARA RS,"
					+ " TBLRUJJENISNOPB JKP,"
					+ " TBLRUJJENISNOPB JKP_PM, tblppkdokumensimati dsm "
					+ " ,( SELECT RPU.ID_PEJABATJKPTG ,RPU.ID_DAERAHURUS ID_DAERAH "
					+ " FROM  TBLRUJPEJABATURUSAN RPU,TBLRUJPEJABATJKPTG RP "
					+ " WHERE RPU.ID_PEJABATJKPTG=RP.ID_PEJABATJKPTG "
					+ " AND RPU.ID_NEGERI=RP.ID_NEGERI "
					+ " AND RPU.ID_DAERAH=RP.ID_DAERAH "
					+ " AND RP.ID_SEKSYEN = 2 "
					+ " AND RPU.ID_JENISPEJABAT = 22 "
					//+ " --AND  RPU.ID_DAERAHURUS = P.ID_DAERAHMHN "
					//+ " --ORDER BY RPU.ID_DAERAHURUS "
					+ " ) PEJ "
					+ " WHERE P.ID_FAIL = F.ID_FAIL"
					+ " AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN"
					+ " AND PSM.ID_SIMATI = SM.ID_SIMATI"
					+ " AND P.ID_PEMOHON = PM.ID_PEMOHON"
					+ " AND  PEJ.ID_DAERAH = P.ID_DAERAHMHN "
					+ " AND sm.id_simati = dsm.id_simati(+) "
					+ " AND PM.ID_PEMOHON = OB.ID_PEMOHON(+)"
					+ " AND OB.ID_SAUDARA = RS.ID_SAUDARA(+)"
					+ " AND SM.JENIS_KP = JKP.ID_JENISNOPB(+)"
					+ " AND PM.JENIS_KP = JKP_PM.ID_JENISNOPB(+)"
					+ " AND F.ID_FAIL = '" + idFail + "'";
			myLogger.info("SQL STATEMENT - PERMOHONAN MT : " + sql);

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				permohonanMT.put(
						"noPetisyen",
						rs.getString("noPetisyen") == null ? "" : rs
								.getString("noPetisyen"));
				permohonanMT.put(
						"namaSimati",
						rs.getString("namaSimati") == null ? "" : rs
								.getString("namaSimati"));
				permohonanMT.put(
						"namaSimatiLain",
						rs.getString("namaSimatiLain") == null ? "" : rs
								.getString("namaSimatiLain"));
				permohonanMT.put(
						"noKPSimatiBaru",
						rs.getString("noKPSimatiBaru") == null ? "" : rs
								.getString("noKPSimatiBaru"));
				permohonanMT.put(
						"noKPSimatiLama",
						rs.getString("noKPSimatiLama") == null ? "" : rs
								.getString("noKPSimatiLama"));
				permohonanMT.put(
						"noKPSimatiLain",
						rs.getString("noKPSimatiLain") == null ? "" : rs
								.getString("noKPSimatiLain"));
				permohonanMT.put(
						"jeniskp",
						rs.getString("jeniskp") == null ? "" : rs
								.getString("jeniskp"));
				permohonanMT.put(
						"tarikhMati",
						rs.getString("tarikhMati") == null ? "" : rs
								.getString("tarikhMati"));
				permohonanMT.put(
						"nosijilmati",
						rs.getString("nosijilmati") == null ? "" : rs
								.getString("nosijilmati"));
				permohonanMT.put(
						"umursimati",
						rs.getString("umursimati") == null ? "" : rs
								.getString("umursimati"));
				
				permohonanMT.put(
						"newtarikhmati",
						rs.getString("newtarikhmati") == null ? "" : rs
								.getString("newtarikhmati"));
				permohonanMT.put(
						"alamat1simati",
						rs.getString("alamat1simati") == null ? "" : rs
								.getString("alamat1simati"));
				permohonanMT.put(
						"alamat2simati",
						rs.getString("alamat2simati") == null ? "" : rs
								.getString("alamat2simati"));
				permohonanMT.put(
						"alamat3simati",
						rs.getString("alamat3simati") == null ? "" : rs
								.getString("alamat3simati"));
				permohonanMT.put(
						"bandarsimati",
						rs.getString("bandarsimati") == null ? "" : rs
								.getString("bandarsimati"));
				permohonanMT.put(
						"idbandarsimati",
						rs.getString("idbandarsimati") == null ? "" : rs
								.getString("idbandarsimati"));
				permohonanMT.put(
						"poskodsimati",
						rs.getString("poskodsimati") == null ? "" : rs
								.getString("poskodsimati"));
				permohonanMT.put(
						"idnegerisimati",
						rs.getString("idnegerisimati") == null ? "" : rs
								.getString("idnegerisimati"));
				permohonanMT.put(
						"jantinasimati",
						rs.getString("jantinasimati") == null ? "" : rs
								.getString("jantinasimati"));
				
				permohonanMT.put(
						"namaPemohon",
						rs.getString("namaPemohon") == null ? "" : rs
								.getString("namaPemohon"));
				permohonanMT.put(
						"noKPBaruPemohon",
						rs.getString("noKPBaruPemohon") == null ? "" : rs
								.getString("noKPBaruPemohon"));
				permohonanMT.put(
						"noKPLamaPemohon",
						rs.getString("noKPLamaPemohon") == null ? "" : rs
								.getString("noKPLamaPemohon"));
				permohonanMT.put(
						"noKPLainPemohon",
						rs.getString("noKPLainPemohon") == null ? "" : rs
								.getString("noKPLainPemohon"));
				permohonanMT.put(
						"umurPemohon",
						rs.getString("umurPemohon") == null ? "" : rs
								.getString("umurPemohon"));
				permohonanMT.put(
						"jantinaPemohon",
						rs.getString("jantinaPemohon") == null ? "" : rs
								.getString("jantinaPemohon"));
				permohonanMT.put(
						"alamat1",
						rs.getString("alamat1") == null ? "" : rs
								.getString("alamat1"));
				permohonanMT.put(
						"alamat2",
						rs.getString("alamat2") == null ? "" : rs
								.getString("alamat2"));
				permohonanMT.put(
						"alamat3",
						rs.getString("alamat3") == null ? "" : rs
								.getString("alamat3"));
				permohonanMT.put(
						"poskod",
						rs.getString("poskod") == null ? "" : rs
								.getString("poskod"));
				permohonanMT.put(
						"idBandar",
						rs.getString("idBandar") == null ? "" : rs
								.getString("idBandar"));
				permohonanMT.put(
						"idNegeri",
						rs.getString("idNegeri") == null ? "" : rs
								.getString("idNegeri"));
				permohonanMT.put(
						"idNegeriPemohon",
						rs.getString("idNegeriPemohon") == null ? "" : rs
								.getString("idNegeriPemohon"));
				permohonanMT.put(
						"hubSimatiPemohon",
						rs.getString("hubSimatiPemohon") == null ? "" : rs
								.getString("hubSimatiPemohon"));
				permohonanMT.put(
						"tarikhJanaBorangB",
						rs.getString("tarikhJanaBorangB") == null ? "" : rs
								.getString("tarikhJanaBorangB"));
				permohonanMT.put(
						"kodPejabat",
						rs.getString("kodPejabat") == null ? "" : rs
								.getString("kodPejabat"));
				permohonanMT.put(
						"jenisTransaksi",
						rs.getString("jenisTransaksi") == null ? "" : rs
								.getString("jenisTransaksi"));
				permohonanMT.put(
						"ID_HUBSIMATIPEMOHON",
						rs.getString("ID_HUBSIMATIPEMOHON") == null ? "" : rs
								.getString("ID_HUBSIMATIPEMOHON"));
				permohonanMT.put(
						"idnegeriPemohon",
						rs.getString("idnegeriPemohon") == null ? "" : rs
								.getString("idnegeriPemohon"));
				permohonanMT.put(
						"WAKTU_KEMATIAN",
						rs.getString("WAKTU_KEMATIAN") == null ? "" : rs
								.getString("WAKTU_KEMATIAN"));
				permohonanMT.put(
						"JENIS_WAKTU_MATI",
						rs.getString("JENIS_WAKTU_MATI") == null ? "" : rs
								.getString("JENIS_WAKTU_MATI"));
				permohonanMT.put(
						"idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				permohonanMT.put(
						"idSimati",
						rs.getString("idSimati") == null ? "" : rs
								.getString("idSimati"));
				permohonanMT.put(
						"jumlahharta",
						rs.getString("jumlahharta") == null ? "" : rs
								.getString("jumlahharta"));
				
				permohonanMT.put(
						"namaDokumen",
						rs.getString("NAMA_DOKUMEN") == null ? "" : rs
								.getString("NAMA_DOKUMEN"));
				
				Blob  b = rs.getBlob("KANDUNGAN");
				InputStream is = b.getBinaryStream();
				 byte [] b2 = IOUtils.toByteArray(is);
					String content = Base64.encodeToString(b2);
					myLogger.info("*****KANDUNGAN*****");
				
				permohonanMT.put("docContent", content);
				
				
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return permohonanMT;
	}

	public Hashtable<String,String> getPerayuMT(String idPermohonan) {
		Db db = null;
		String sql = "";
		Hashtable<String,String> getPerayuMT = new Hashtable<String,String>();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT * FROM TBLPPKPERAYU WHERE ID_RAYUAN = (SELECT ID_RAYUAN FROM TBLPPKRAYUAN WHERE ID_PERMOHONAN = '"+idPermohonan+"')";
			myLogger.info("SQL STATEMENT - PERAYU MT : " + sql);

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				getPerayuMT.put(
						"namaPerayu",
						rs.getString("NAMA_PERAYU") == null ? "" : rs
								.getString("NAMA_PERAYU"));
				getPerayuMT.put(
						"noKPBaruPerayu",
						rs.getString("NO_KP_BARU") == null ? "" : rs
								.getString("NO_KP_BARU"));
				getPerayuMT.put(
						"noKPLamaPerayu",
						rs.getString("NO_KP_LAMA") == null ? "" : rs
								.getString("NO_KP_LAMA"));
				getPerayuMT.put(
						"noKPLainPerayu",
						rs.getString("NO_KP_LAIN") == null ? "" : rs
								.getString("NO_KP_LAIN"));
				getPerayuMT.put(
						"alamat1Perayu",
						rs.getString("ALAMAT_1") == null ? "" : rs
								.getString("ALAMAT_1"));
				getPerayuMT.put(
						"alamat2Perayu",
						rs.getString("ALAMAT_2") == null ? "" : rs
								.getString("ALAMAT_2"));
				getPerayuMT.put(
						"alamat3Perayu",
						rs.getString("ALAMAT_3") == null ? "" : rs
								.getString("ALAMAT_3"));
				getPerayuMT.put(
						"bandarPerayu",
						rs.getString("BANDAR") == null ? "" : rs
								.getString("BANDAR"));
				getPerayuMT.put(
						"poskodPerayu",
						rs.getString("POSKOD") == null ? "" : rs
								.getString("POSKOD"));
				getPerayuMT.put(
						"idnegeriPerayu",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
				getPerayuMT.put(
						"idbandarPerayu",
						rs.getString("ID_BANDAR") == null ? "" : rs
								.getString("ID_BANDAR"));
				
				
				
				
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return getPerayuMT;
	}
	
	public Hashtable<String,String> getNegeriPerayu(String idNegeri) {
		Db db = null;
		String sql = "";
		Hashtable<String,String> getNegeriPerayu = new Hashtable<String,String>();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT NAMA_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI = "+idNegeri;
			myLogger.info("SQL STATEMENT - getNegeriPerayu : " + sql);

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				getNegeriPerayu.put(
						"namaNegeri",
						rs.getString("NAMA_NEGERI") == null ? "" : rs
								.getString("NAMA_NEGERI"));
					
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return getNegeriPerayu;
	}
	
	public Hashtable<String,String> getMahkamah(String idPermohonan) {
		Db db = null;
		String sql = "";
		Hashtable<String,String> getMahkamah = new Hashtable<String,String>();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_MAHKAMAH FROM TBLPPKRAYUAN WHERE ID_PERMOHONAN = '"+idPermohonan+"'";
			myLogger.info("SQL STATEMENT - getMahkamah : " + sql);

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				getMahkamah.put(
						"idMahkamah",
						rs.getString("ID_MAHKAMAH") == null ? "" : rs
								.getString("ID_MAHKAMAH"));
					
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return getMahkamah;
	}
	
	private void insertBorang(HttpSession session) throws Exception {

		Hashtable h = null;
		h = new Hashtable();
		String txtTarikhKaveat = getParam("txtTarikhKaveat");
		 
		String ntxtTarikhKaveat = "to_date('" + txtTarikhKaveat + "','dd/MM/yyyy')";
		Db db = null;	
			
			 try
			    {
				 long idKaveatPeguam = DB.getNextID("TBLPPKKAVEATPEGUAM_SEQ");
				 String sql9 ="";
				 db = new Db();
				 Statement stmt = db.getStatement();
				 SQLRenderer r = new SQLRenderer();

				 Statement stmt9 = db.getStatement();
				 SQLRenderer r9 = new SQLRenderer();
				 r9.add("id_kaveatpeguam", idKaveatPeguam);
				 r9.add("id_permohonan", getParam("idPermohonan"));
				 r9.add("nama_firma",getParam("txtNamaFirma"));					
				 r9.add("nama_kaveat", getParam("txtNamaKaveat"));
				 r9.add("no_kaveat", getParam("txtNoKaveat"));
				 r9.add("alamat1", getParam("txtAlamat1Peguam"));
				 r9.add("alamat2", getParam("txtAlamat2Peguam"));
				 r9.add("alamat3", getParam("txtAlamat3Peguam"));
				 r9.add("poskod", getParam("txtPoskodPeguam"));
				 r9.add("id_negeri", getParam("socNegeriPeguam"));
				 r9.add("id_bandar", getParam("txtBandarPeguam"));
				 r9.add("no_tel", getParam("txtNomborTelefonPeguam"));
				 r9.add("tarikh_kaveat", r.unquote(ntxtTarikhKaveat));

				 r9.add("id_Masuk", session.getAttribute("_ekptg_user_id"));
				 r9.add("tarikh_Masuk",r.unquote("sysdate"));	
				 r9.add("id_Kemaskini", session.getAttribute("_ekptg_user_id"));	
				 r9.add("tarikh_Kemaskini",r.unquote("sysdate"));	

				 sql9 = r9.getSQLInsert("tblppkkaveatpeguam");				
				 stmt9.executeUpdate(sql9);
			    } finally {
		    		if (db != null) db.close();
		    	}
	}
	
	public Hashtable<String,String> getnamaMahkamah(String idPejabat) {
		Db db = null;
		String sql = "";
		Hashtable<String,String> getnamaMahkamah = new Hashtable<String,String>();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT NAMA_PEJABAT FROM TBLRUJPEJABAT WHERE ID_PEJABAT = '"+idPejabat+"'";
			myLogger.info("SQL STATEMENT - getMahkamah : " + sql);

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				getnamaMahkamah.put(
						"namaMahkamah",
						rs.getString("NAMA_PEJABAT") == null ? "" : rs
								.getString("NAMA_PEJABAT"));
					
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return getnamaMahkamah;
	}
	
	public Hashtable<String,String> getBandarPerayu(String idBandar) {
		Db db = null;
		String sql = "";
		Hashtable<String,String> getBandarPerayu = new Hashtable<String,String>();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT KETERANGAN FROM TBLRUJBANDAR WHERE ID_BANDAR = "+idBandar;
			myLogger.info("SQL STATEMENT - getNegeriPerayu : " + sql);

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				getBandarPerayu.put(
						"bandar",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
					
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return getBandarPerayu;
	}

	
	public String getPejabatJKPTGByKodPejabat(String kodPejabat) {
		Db db = null;
		String sql = "";
		String namaPejabat = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT pj.nama_pejabat AS namapejabat"
					+ " FROM tblintmtkodpej mpj, " + "tblrujpejabatjkptg pj"
					+ " WHERE pj.id_pejabatjkptg = mpj.kodpejabat"
					+ " AND mpj.kodpejabat = '" + kodPejabat + "'";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				namaPejabat = rs.getString("namaPejabat");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return namaPejabat;
	}
	
	public Hashtable<String,String> getTarikhHantarMT(String idFail) {
		Db db = null;
		String sql = "";
		Hashtable<String,String> TarikhHantarMT = new Hashtable<String,String>();

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT MT.PETISYENNO, TO_CHAR(MT.TARIKH_HANTAR, 'YYYY-MM-DD') AS TARIKH_HANTAR, F.ID_FAIL, F.NO_FAIL  FROM TBLINTMTPERMOHONAN MT, TBLPFDFAIL F  " +
					" WHERE F.NO_FAIL = MT.PETISYENNO " +
					" AND F.ID_FAIL = '" + idFail + "'";
			myLogger.info("SQL STATEMENT - PERMOHONAN MT : " + sql);

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				TarikhHantarMT.put(
						"TARIKH_HANTAR",
						rs.getString("TARIKH_HANTAR") == null ? "" : rs
								.getString("TARIKH_HANTAR"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return TarikhHantarMT;
	}
}