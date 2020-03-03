package ekptg.model.ppk.harta;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.EkptgCache;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.htp.utiliti.IUtilHTMLPilihan;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8InternalData;
import ekptg.model.ppk.UtilHTML;
import ekptg.model.ppk.harta.FrmPermohonanHAData;
import ekptg.model.ppk.util.UtilHTMLPilihanJenisHA;
import ekptg.model.utils.Fungsi;
import ekptg.model.utils.IUtilHTMLPilihanExt;
import ekptg.model.utils.rujukan.UtilHTMLPilihanJenisPBPPK;

public class HABean extends EkptgCache implements IMaklumatHarta {
	
	private static Logger myLog = Logger.getLogger(ekptg.model.ppk.harta.HABean.class);
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	FrmPrmhnnSek8DaftarSek8InternalData logic_A = null; 
	FrmHeaderPpk mainheader = null;

	Vector senaraiHarta = null;
	String READMODE = "readmode";
	String READMODED = "disabled";
	String YES = "yes";
	Date date = new Date();
	String currentDate = dateFormat.format(date);
	
	String txtNoHakmilikHtaam = "";
	String idSimati = "";
	String txtNoPTHtaam = "";
	String txtNilaiTarikhMohonHtaa = "";
	String txtNilaiTarikhMatiHtaam =  "";
	String socKategoriTanahHtaam =  "";
	String socJenisHakmilikHtaam =  "";
	String socStatusPemilikanHtaam =  "";
	String txtLuasHMpHtaam = "";
	String txtLuasAsalHtaam = "";
	String txtNoPajakan = "";
	String socJenisTanahHtaam = "";
	String txtBahagianSimati1 = "";
	String txtBahagianSimati2 = "";
	String txtTanggunganHtaam = "";
	String socJenisLuasHtaam = "";
	String txtCatatanHtaam = "";
	String txtNoPersHtaam = "";
	String FLAG_DAFTAR = "";
	String txtSekatan = "";
	String txtSyaratNyata = "";
	
	String idhtaamid = "";
	String idPermohonanSimati = "";
	String idUser = "0";

	String socNegeriHtaamUp = "0";
	String socDaerahHtaamUp = "0";
	String idHarta = "0";
	FrmPermohonanHAData permohonanInternal = null;
	private UtilHTMLPilihanJenisHA jenisHA = null;
	private UtilHTMLPilihanJenisPBPPK jenisPB = null;

	public Hashtable setSelectedDataHa(String idPermohonanSimati, String idHA) throws Exception {
		Db db = null;
		Hashtable h = null;
		String sql = "";
		//senaraiHarta.clear();
		senaraiHarta = new Vector();

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			//SQLRenderer r = new SQLRenderer();
			sql = "SELECT H.FLAG_DAFTAR,H.ID_HA, H.BIL, H.ID_SIMATI, H.ID_JENISHA, H.ID_NEGERI, H.ID_DAERAH, H.JENAMA, H.NO_DAFTAR, H.NO_SIJIL, "
					+ " H.BIL_UNIT, H.TARIKH_HARTA, H.ALAMAT_HA1, H.ALAMAT_HA2, H.ALAMAT_HA3, H.POSKOD, H.NILAI_HA_TARIKHMOHON, "
					+ " H.NILAI_HA_TARIKHMATI, H.BA_SIMATI, H.BB_SIMATI, H.CATATAN, H.NILAI_SEUNIT, H.ID_MASUK, H.TARIKH_MASUK, "
					+ " H.ID_KEMASKINI, H.TARIKH_KEMASKINI, H.ID_DB, J.KOD, J.KETERANGAN, H.ALAMAT_HA1, H.ALAMAT_HA2, "
					+ " H.ALAMAT_HA3, H.POSKOD, H.ID_DAERAH, H.ID_NEGERI, H.NAMA_SAHAM,H.BUTIRAN "
					+ " FROM TBLPPKHA H1,TBLPPKHAPERMOHONAN H, TBLPPKRUJJENISHA J"
					+ " WHERE H1.ID_HA = H.ID_HA AND H.ID_JENISHA = J.ID_JENISHA   "
					+ " AND H.ID_HA = '" + idHA + "' ";

			myLog.info("HARTA ALIH :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("FLAG_DAFTAR",rs.getString("FLAG_DAFTAR") == null ? "" : rs.getString("FLAG_DAFTAR"));
				h.put("id_Ha",rs.getString("id_Ha") == null ? "" : rs.getString("id_Ha"));
				h.put("id_Jenisha", rs.getString("id_Jenisha") == null ? "": rs.getString("id_Jenisha"));
				h.put("id_Simati",rs.getString("id_Simati") == null ? "" : rs.getString("id_Simati"));
				h.put("idnegeri",rs.getString("id_negeri") == null ? "" : rs.getString("id_negeri"));
				h.put("iddaerah",rs.getString("id_daerah") == null ? "" : rs.getString("id_daerah"));
				h.put("jenama",rs.getString("jenama") == null ? "" : rs.getString("jenama"));
				h.put("noDaftar",rs.getString("no_Daftar") == null ? "" : rs.getString("no_Daftar"));
				h.put("nosijil",rs.getString("no_sijil") == null ? "" : rs.getString("no_sijil"));
				h.put("bilunit",rs.getString("bil_unit") == null ? "" : rs.getString("bil_unit"));
				h.put("tarikhharta", rs.getString("tarikh_harta") == null ? "": rs.getString("tarikh_harta"));
				h.put("alamat1",rs.getString("alamat_ha1") == null ? "" : rs.getString("alamat_ha1"));
				h.put("alamat2",rs.getString("alamat_ha2") == null ? "" : rs.getString("alamat_ha2"));
				h.put("alamat3",rs.getString("alamat_ha3") == null ? "" : rs.getString("alamat_ha3"));
				h.put("daerah",rs.getString("id_Daerah") == null ? "" : rs.getString("id_Daerah"));
				h.put("negeri",rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri"));
				h.put("poskod",rs.getString("poskod") == null ? "" : rs.getString("poskod"));
				h.put("nilaiha_tarikhmohon",rs.getString("nilai_ha_tarikhmohon") == null ? "" : rs.getDouble("nilai_ha_tarikhmohon"));
				h.put("nilaiha_tarikhmati",rs.getString("nilai_ha_tarikhmati") == null ? "" : rs.getDouble("nilai_ha_tarikhmati"));
				h.put("basimati",rs.getString("ba_simati") == null ? "" : rs.getString("ba_simati"));
				h.put("bbsimati",rs.getString("bb_simati") == null ? "" : rs.getString("bb_simati"));
				h.put("catatan",rs.getString("catatan") == null ? "" : rs.getString("catatan"));
				h.put("nilaiseunit", rs.getString("nilai_seunit") == null ? "": rs.getDouble("nilai_seunit"));
				h.put("Kod",rs.getString("kod") == null ? "" : rs.getString("kod"));
				h.put("Keterangan", rs.getString("keterangan") == null ? "": rs.getString("keterangan"));
				h.put("nama_saham", rs.getString("nama_saham") == null ? "": rs.getString("nama_saham"));
				h.put("butiran",rs.getString("butiran") == null ? "" : rs.getString("butiran"));
				senaraiHarta.addElement(h);
				bil++;

			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
		return h;
		
	}

	public Vector getSelectedDataHa() {
		return senaraiHarta;
	}
	
	public void getHarta(String mode
		,Hashtable hParam
		,FrmPrmhnnSek8InternalData logic_internal
		,HttpServletRequest request
		,HttpSession session
		,org.apache.velocity.VelocityContext context) throws Exception{
		Fungsi fnc = new Fungsi();
		logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();		
		permohonanInternal = new FrmPermohonanHAData();
		
		String idPermohonan = fnc.getParam(request,"idPermohonan");
		String idSimati = fnc.getParam(request,"idSimati");
		String idPemohon = fnc.getParam(request,"idPemohon");
		String mati = fnc.getParam(request,"id_Permohonansimati");
		
		idUser = String.valueOf(hParam.get("idUser"));
		Vector sumppkha = null;
		Vector selectedppkha = null;
		Hashtable<String,String> mh = null;
		
		if ("view_harta_alih".equals(mode)) {
			//String id = fnc.getParam(request,"idPermohonan");
			//String id2 = fnc.getParam(request,"idPemohon");
			//String id1 = fnc.getParam(request,"idSimati");

		//logic_A.setDataHa(mati);
		//listppkha = logic_A.getDataHa();
		//int countList = listppkha.size();
			permohonanInternal.getHarta(mati);
			context.put("listHa", permohonanInternal.getDataHA());
			context.put("jumList", permohonanInternal.getDataHA().size());

			logic_A.setSumDataHa(mati);
			sumppkha = logic_A.getSumDataHa();
			context.put("jumppkha", sumppkha);

//		listppkhta = logic_B.getDataHta();
//		logic_B.setDataHta(id);
//		this.context.put("listhta2", listppkhta);
//		int countListhta = listppkhta.size();
//		this.context.put("jumListhta2", countList);
//
//		logic_B.setDataHa(id);
//		listppkha2 = logic_B.getHa2();
//		this.context.put("listHa2", listppkha2);
//		int countListha2 = listppkha2.size();
//		this.context.put("jumListHa2", countListha2);
//
//		logic_A.updateDataNilai(id, mati, idUser);
//		logic_A.setOverallSum(mati);
//		overallnilai = logic_A.getOverallSum();
//		this.context.put("overall", overallnilai);
//
//		logic_A.setOverallSumMati(mati);
//		overallnilaimati = logic_A.getOverallSumMati();
//		this.context.put("overallmati", overallnilaimati);
//		this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri"));

			context.put("showbuttonkembali", "yes");
			context.put("showbuttontambah", "yes");
			context.put("EventStatus", 1);

		}else if ("tambah_harta".equals(mode)) {
//			String id = getParam("idPermohonan");
//			String id2 = getParam("idPemohon");
//			String id1 = getParam("idSimati");
//			String mati = getParam("id_Permohonansimati");
			String eventstatus = fnc.getParam(request,"eventStatus");
//
//			socJenisha = getParam("socJenisHartaAlih");
//			this.context.put("socJenisHa", socJenisha);
			mh = new Hashtable<String,String>();
			mh.put("socJenisHartaAlih", fnc.getParam(request,"socJenisHartaAlih"));
			mh.put("socIdNegeri", fnc.getParam(request,"socNegeriHtaam").equals("")?"-1":fnc.getParam(request,"socNegeriHtaam"));
			mh.put("socIdDaerah", fnc.getParam(request,"socDaerahHtaam"));
			setSocHA(mh,context);

//
//			logic_A.setDataHa(mati);
//			listppkha = logic_A.getDataHa();
//			this.context.put("listHa", listppkha);
//			int countList = listppkha.size();
//			this.context.put("jumList", countList);
//
//			logic_A.setSumDataHa(mati);
//			sumppkha = logic_A.getSumDataHa();
//			this.context.put("jumppkha", sumppkha);
//
			permohonanInternal.getHarta(mati);
			context.put("listHa", permohonanInternal.getDataHA());
			context.put("jumList", permohonanInternal.getDataHA().size());

			logic_A.setSumDataHa(mati);
			sumppkha = logic_A.getSumDataHa();
			context.put("jumppkha", sumppkha);

//			// FrmPrmhnnSek8SenaraiHTATHInternalData
//			// frmPrmhnnSek8SenaraiHTATHInternalData = new
//			// FrmPrmhnnSek8SenaraiHTATHInternalData();
//			listppkhta = logic_B.getDataHta();
//			logic_B.setDataHta(id);
//			this.context.put("listhta2", listppkhta);
//			int countListhta = listppkhta.size();
//			this.context.put("jumListhta2", countList);
//
//			logic_B.setDataHa(id);
//			listppkha2 = logic_B.getHa2();
//			this.context.put("listHa2", listppkha2);
//			int countListha2 = listppkha2.size();
//			this.context.put("jumListHa2", countListha2);
//
//			logic_A.updateDataNilai(id, mati, idUser);
//
//			logic_A.setOverallSum(mati);
//			overallnilai = logic_A.getOverallSum();
//			this.context.put("overall", overallnilai);
//
//			logic_A.setOverallSumMati(mati);
//			overallnilaimati = logic_A.getOverallSumMati();
//			this.context.put("overallmati", overallnilaimati);
//
//			listJenisha = logic_A.getJenisHa();
//			this.context.put("ViewJenisHa", listJenisha);
//
			context.put("negeri", "");
			context.put("daerah", "");
			context.put("socJenisHa", fnc.getParam(request,"socJenisHartaAlih"));
			context.put("norujukan", "");
			context.put("nosijil", "");
			context.put("bilunit", "");
			context.put("nilaiseunit", "");
			context.put("agensi", "");
			context.put("nama_saham", "");
			context.put("txtAlamat1", fnc.getParam(request,"txtAlamat1"));
			context.put("txtAlamat2", fnc.getParam(request,"txtAlamat2"));
			context.put("txtAlamat3", fnc.getParam(request,"txtAlamat3"));

			context.put("txtPoskod", fnc.getParam(request,"txtPoskod"));
			context.put("negeri", fnc.getParam(request,"socNegeriHtaam"));
			context.put("daerah", fnc.getParam(request,"socDaerahHtaam"));
			context.put("bhgnmati1", fnc.getParam(request,"txtBhgnSimati1"));
			context.put("bhgnmati2", fnc.getParam(request,"txtBhgnSimati2"));

			context.put("FLAG_DAFTAR", fnc.getParam(request,"FLAG_DAFTAR"));

			context.put("nilaitarikhmati",fnc.getParam(request,"txtNilaiTarikhMati"));
			context.put("nilaitarikhmohon",fnc.getParam(request,"txtNilaiTarikhMohon"));
			context.put("catatan", fnc.getParam(request,"txtCatatan"));
			context.put("butiran", fnc.getParam(request,"butiran"));
//			
			String jh = "0";
			jh = fnc.getParam(request,"socJenisHartaAlih");
			int jj = 0;
			if (jh != null && jh != "" && jh != "0") {
				jj = Integer.parseInt(jh);
			} else {
				jj = 0;
			}

			if (jj > 0) {
				context.put("showadd", "1");
			} else {
				context.put("showadd", "0");

			}
//
//			int idne = 0;
//			if (!"".equals(getParam("socNegeriHtaam"))
//					&& !"0".equals(getParam("socNegeriHtaam"))) {
//				idne = Integer.parseInt(getParam("socNegeriHtaam"));
//
//			} else {
//				idne = 0;
//
//			}
//
//			if (jh != "0" && jh != "") {
//				if (idne != 0) {
//					listnegeribydaerah = logic_A.getListDaerahbyNegeri(idne);
//					this.context.put("listDaerahbyNegeri", listnegeribydaerah);
//				} else {
//					this.context.put("readmodedaerah", "");
//					this.context.put("listDaerahbyNegeri", "");
//				}
//
//			}else {
//
//				this.context.put("readmodedaerah", "");
//				this.context.put("listDaerahbyNegeri", "");
//			}
			context.put("EventStatus", eventstatus);
//		
		}else if ("tambah_harta_baru".equals(mode)) {
//			String id = getParam("idPermohonan");
//			String id2 = getParam("idPemohon");
//			String id1 = getParam("idSimati");
//			String mati = getParam("id_Permohonansimati");

//			context.put("id", idPermohonan);
//			context.put("id1", idSimati);

			permohonanInternal.getHarta(mati);
			context.put("listHa", permohonanInternal.getDataHA());
			context.put("jumList", permohonanInternal.getDataHA().size());

//			logic_A.setSumDataHa(mati);
//			sumppkha = logic_A.getSumDataHa();
//			context.put("jumppkha", sumppkha);

//			logic_B.setDataHta(id);
//			listppkhta = logic_B.getDataHta();
//			this.context.put("listhta2", listppkhta);
//			int countListhta = listppkhta.size();
//			this.context.put("jumListhta2", countList);
//
//			logic_B.setDataHa(id);
//			listppkha2 = logic_B.getHa2();
//			this.context.put("listHa2", listppkha2);
//			int countListha2 = listppkha2.size();
//			this.context.put("jumListHa2", countListha2);
//
//			logic_A.updateDataNilai(id, mati, idUser);
//
//			logic_A.setOverallSum(mati);
//			overallnilai = logic_A.getOverallSum();
//			this.context.put("overall", overallnilai);
//
//			logic_A.setOverallSumMati(mati);
//			overallnilaimati = logic_A.getOverallSumMati();
//			this.context.put("overallmati", overallnilaimati);

//			listJenisha = logic_A.getJenisHa();
//			this.context.put("ViewJenisHa", listJenisha);
			mh = new Hashtable<String,String>();
			mh.put("socJenisHartaAlih", "0");
			mh.put("socIdNegeri", "0");
			mh.put("socIdDaerah", "0");
			setSocHA(mh,context);
			
			Db db = null;
			try {
				db = new Db();		
				context.put("listnegeri", logic_A.getListnegeriDb(db));
			}catch(Exception e){}

			context.put("negeri", "");
			context.put("daerah", "");
			context.put("socJenisHa", fnc.getParam(request,"socJenisHartaAlih"));
			context.put("norujukan", "");
			context.put("nosijil", "");
			context.put("bilunit", "");
			context.put("nilaiseunit", "");
			context.put("agensi", "");
			context.put("txtAlamat1", "");
			context.put("txtAlamat2", "");
			context.put("txtAlamat3", "");

			context.put("txtPoskod", "");
			context.put("nama_saham", "");
			context.put("negeri", "");
			context.put("daerah", "");
			context.put("bhgnmati1", "");
			context.put("bhgnmati2", "");
			context.put("nilaitarikhmati", "");
			context.put("nilaitarikhmohon", "");
			context.put("catatan", "");
			context.put("butiran", "");
			context.put("readmodedaerah", "");

			context.put("FLAG_DAFTAR", "");
			
			context.put("barula", "yes");
			context.put("EventStatus", 0);


		}else if ("simpan_harta".equals(mode)) {
//			String id = getParam("idPermohonan");
//			String id2 = getParam("idPemohon");
//			String id1 = getParam("idSimati");
//			String mati = getParam("id_Permohonansimati");
			String eventstatus = fnc.getParam(request,"eventStatus");
			String bolehsimpan = String.valueOf(hParam.get("bolehSimpan"));
			
			if (bolehsimpan.equals("yes")) {
				//online
				context.put("appear_skrin_info", "simpan");
				//addHA(session,request,fnc);
				permohonanInternal.addHA(hParam,request,fnc,logic_A);
				
			}
//			logic_A.setDataHa(mati);
//			listppkha = logic_A.getDataHa();
//			this.context.put("listHa", listppkha);
//			int countList = listppkha.size();
//			this.context.put("jumList", countList);

//			logic_A.setSumDataHa(mati);
//			sumppkha = logic_A.getSumDataHa();
//			this.context.put("jumppkha", sumppkha);
			permohonanInternal.getHarta(mati);
			context.put("listHa", permohonanInternal.getDataHA());
			context.put("jumList", permohonanInternal.getDataHA().size());

			logic_A.setSumDataHa(mati);
			sumppkha = logic_A.getSumDataHa();
			context.put("jumppkha", sumppkha);
//
//			logic_B.setDataHta(id);
//			listppkhta = logic_B.getDataHta();
//			this.context.put("listhta2", listppkhta);
//			int countListhta = listppkhta.size();
//			this.context.put("jumListhta2", countList);
//
//			logic_B.setDataHa(id);
//			listppkha2 = logic_B.getHa2();
//			this.context.put("listHa2", listppkha2);
//			int countListha2 = listppkha2.size();
//			this.context.put("jumListHa2", countListha2);
//
//			logic_A.updateDataNilai(id, mati,idUser);
//
//			logic_A.setOverallSum(mati);
//			overallnilai = logic_A.getOverallSum();
//			this.context.put("overall", overallnilai);
//
//			logic_A.setOverallSumMati(mati);
//			overallnilaimati = logic_A.getOverallSumMati();
//			this.context.put("overallmati", overallnilaimati);
//
//			listJenisha = logic_A.getJenisHa();
//			this.context.put("ViewJenisHa", listJenisha);
//
			context.put("EventStatus", eventstatus);
			context.put("showbuttonkembali", "yes");
			context.put("showbuttontambah", "yes");
			context.put("tutup", "yes");
//			context.put("id", idPermohonan);
//			context.put("id1", idSimati);
//			context.put("id2", idPemohon);
//
//		} else if ("batal_harta".equals(mode)) {
//
//			String id = getParam("idPermohonan");
//			String id2 = getParam("idPemohon");
//			String id1 = getParam("idSimati");
//			String mati = getParam("id_Permohonansimati");
//			String idha = getParam("idha");
//			String eventstatus = getParam("eventStatus");
//
//			logic_A.setSelectedDataHa(mati, idha);
//			selectedppkha = logic_A.getSelectedDataHa();
//			this.context.put("DataHa", selectedppkha);
//
//			logic_A.setDataHa(mati);
//			listppkha = logic_A.getDataHa();
//			this.context.put("listHa", listppkha);
//			int countList = listppkha.size();
//			this.context.put("jumList", countList);
//
//			logic_A.setSumDataHa(mati);
//			sumppkha = logic_A.getSumDataHa();
//			this.context.put("jumppkha", sumppkha);
//
//			logic_B.setDataHta(id);
//			listppkhta = logic_B.getDataHta();
//			this.context.put("listhta2", listppkhta);
//			int countListhta = listppkhta.size();
//			this.context.put("jumListhta2", countList);
//
//			logic_B.setDataHa(id);
//			listppkha2 = logic_B.getHa2();
//			this.context.put("listHa2", listppkha2);
//			int countListha2 = listppkha2.size();
//			this.context.put("jumListHa2", countListha2);
//
//			logic_A.updateDataNilai(id, mati, idUser);
//
//			logic_A.setOverallSum(mati);
//			overallnilai = logic_A.getOverallSum();
//			this.context.put("overall", overallnilai);
//
//			logic_A.setOverallSumMati(mati);
//			overallnilaimati = logic_A.getOverallSumMati();
//			this.context.put("overallmati", overallnilaimati);
//
//			listJenisha = logic_A.getJenisHa();
//			this.context.put("ViewJenisHa", listJenisha);
//
//			this.context.put("showbuttontambah", "yes");
//			this.context.put("showadd", "1");
//			this.context.put("EventStatus", eventstatus);
//			this.context.put("id", id);
//			this.context.put("id1", id1);
//			this.context.put("id2", id2);
//
		} else if ("edit_harta".equals(mode)) {
//			String id = getParam("idPermohonan");
//			String id2 = getParam("idPemohon");
//			String id1 = getParam("idSimati");
//			String mati = getParam("id_Permohonansimati");
			//internal/online
			String eventstatus = fnc.getParam(request,"eventStatus");
			String idha = fnc.getParam(request,"idha");
			myLog.info("idha="+idha +"|"+eventstatus);
//			context.put("id", idPermohonan);
//			context.put("id1", idSimati);
//			context.put("id2", idPemohon);
			
			//selectedppkha = new Vector();
			//logic_A.setSelectedDataHa(mati, idha);
			//selectedppkha = logic_A.getSelectedDataHa();
			Hashtable b = setSelectedDataHa(mati, idha);
			context.put("DataHa", getSelectedDataHa());

			mh = new Hashtable<String,String>();
			mh.put("socJenisHartaAlih", String.valueOf(b.get("id_Jenisha")));
			mh.put("socIdNegeri", String.valueOf(b.get("idnegeri")));
			mh.put("socIdDaerah", String.valueOf(b.get("iddaerah")));
			setSocHAKemaskini(mh,context);
			
			//Hashtable b = (Hashtable) selectedppkha.get(0);
			String nn = b.get("negeri").toString();
			if (nn != "" && nn != "0") {
				int idn = Integer.parseInt(nn);
				Vector listnegeribydaerah = logic_A.getListDaerahbyNegeri(idn);
				context.put("listDaerahbyNegeri", listnegeribydaerah);
				
			} else {
				context.put("readmodedaerah", "");
				context.put("listDaerahbyNegeri", "");
				
			}

			permohonanInternal.getHarta(mati);
			context.put("listHa", permohonanInternal.getDataHA());
			context.put("jumList", permohonanInternal.getDataHA().size());

			logic_A.setSumDataHa(mati);
			sumppkha = logic_A.getSumDataHa();
			context.put("jumppkha", sumppkha);

//			logic_B.setDataHta(id);
//			listppkhta = logic_B.getDataHta();
//			this.context.put("listhta2", listppkhta);
//			int countListhta = listppkhta.size();
//			this.context.put("jumListhta2", countList);
//
//			logic_B.setDataHa(id);
//			listppkha2 = logic_B.getHa2();
//			this.context.put("listHa2", listppkha2);
//			int countListha2 = listppkha2.size();
//			this.context.put("jumListHa2", countListha2);

//			logic_A.updateDataNilai(id, mati, idUser);
//
//			logic_A.setOverallSum(mati);
//			overallnilai = logic_A.getOverallSum();
//			this.context.put("overall", overallnilai);
//
//			logic_A.setOverallSumMati(mati);
//			overallnilaimati = logic_A.getOverallSumMati();
//			this.context.put("overallmati", overallnilaimati);

			//listJenisha = logic_A.getJenisHa();
			//context.put("ViewJenisHa", listJenisha);id_Jenisha

//			listdaerah = logic_A.getListDaerah();
//			this.context.put("listDaerahbyNegeri", listdaerah);

			//online
			//context.put("EventStatus", 3);
			context.put("EventStatus", eventstatus);
			context.put("idha", idha);
			context.put("showadd", "1");
			context.put("showbuttontambah", "yes");
			context.put("tutup", "yes");
			
		} else if ("kemaskini_harta".equals(mode)) {
//			String id = getParam("idPermohonan");
//			String id2 = getParam("idPemohon");
//			String id1 = getParam("idSimati");
//			String mati = getParam("id_Permohonansimati");
			String idha = fnc.getParam(request,"idha");

//			logic_A.setSelectedDataHa(mati, idha);
//			selectedppkha = logic_A.getSelectedDataHa();
//			this.context.put("DataHa", selectedppkha);
//
//			Hashtable b = (Hashtable) selectedppkha.get(0);
//			String nn = b.get("negeri").toString();
//			if (nn != "" && nn != "0") {
//				int idn = Integer.parseInt(nn);
//				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idn);
//				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
//			} else {
//				this.context.put("readmodedaerah", "");
//				this.context.put("listDaerahbyNegeri", "");
//			}
//
//			logic_A.setDataHa(mati);
//			listppkha = logic_A.getDataHa();
//			this.context.put("listHa", listppkha);
//			int countList = listppkha.size();
//			this.context.put("jumList", countList);
			
			Hashtable b = setSelectedDataHa(mati, idha);
			context.put("DataHa", getSelectedDataHa());

			String nn = b.get("negeri").toString();

			permohonanInternal.getHarta(mati);
			context.put("listHa", permohonanInternal.getDataHA());
			context.put("jumList", permohonanInternal.getDataHA().size());

			logic_A.setSumDataHa(mati);
			sumppkha = logic_A.getSumDataHa();
			context.put("jumppkha", sumppkha);

			
//			logic_A.setSumDataHa(mati);
//			sumppkha = logic_A.getSumDataHa();
//			this.context.put("jumppkha", sumppkha);
//
//			logic_B.setDataHta(id);
//			listppkhta = logic_B.getDataHta();
//			this.context.put("listhta2", listppkhta);
//			int countListhta = listppkhta.size();
//			this.context.put("jumListhta2", countList);
//
//			logic_B.setDataHa(id);
//			listppkha2 = logic_B.getHa2();
//			this.context.put("listHa2", listppkha2);
//			int countListha2 = listppkha2.size();
//			this.context.put("jumListHa2", countListha2);
//
			logic_A.updateDataNilai(idPermohonan, mati, idUser);
//
//			logic_A.setOverallSum(mati);
//			overallnilai = logic_A.getOverallSum();
//			this.context.put("overall", overallnilai);
//
//			logic_A.setOverallSumMati(mati);
//			overallnilaimati = logic_A.getOverallSumMati();
//			this.context.put("overallmati", overallnilaimati);
//			listJenisha = logic_A.getJenisHa();
//			this.context.put("ViewJenisHa", listJenisha);
//
//			context.put("id", id);
//			context.put("id1", id1);
//			context.put("id2", id2);
			//
			context.put("EventStatus", 3);
			context.put("idha", idha);
			context.put("showbuttontambah", "yes");
			context.put("showadd", "1");
			context.put("tutup", "yes");

		} else if ("update_harta".equals(mode)) {
			String bolehsimpan = String.valueOf(hParam.get("bolehSimpan"));
			if (bolehsimpan.equals("yes")) {
				//updateHA(session,request,fnc);
				permohonanInternal.updateHA(session,request,fnc,logic_A);
				//online
				context.put("appear_skrin_info", "kemaskini");
				
			}
//
//			String id = getParam("idPermohonan");
//			String id2 = getParam("idPemohon");
//			String id1 = getParam("idSimati");
//			String mati = getParam("id_Permohonansimati");
			String eventStatus = fnc.getParam(request,"eventStatus");
			String idha = fnc.getParam(request, "idha");
//
//			logic_A.setSelectedDataHa(mati, idha);
//			selectedppkha = logic_A.getSelectedDataHa();
//			this.context.put("DataHa", selectedppkha);
//
//			Hashtable b = (Hashtable) selectedppkha.get(0);
//			String nn = b.get("negeri").toString();
//
//			if (nn != "" && nn != "0") {
//				int idn = Integer.parseInt(nn);
//
//				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idn);
//				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
//			}
//
//			logic_A.setDataHa(mati);
//			listppkha = logic_A.getDataHa();
//			this.context.put("listHa", listppkha);
//			int countList = listppkha.size();
//			this.context.put("jumList", countList);
//
			Hashtable b = setSelectedDataHa(mati, idha);
			context.put("DataHa", getSelectedDataHa());

			String nn = String.valueOf(b.get("negeri"));
			if (nn != "" && nn != "0") {
				int idn = Integer.parseInt(nn);

				Vector listnegeribydaerah = logic_A.getListDaerahbyNegeri(idn);
				context.put("listDaerahbyNegeri", listnegeribydaerah);
			
			}
			
			mh = new Hashtable<String,String>();
			mh.put("socJenisHartaAlih", String.valueOf(b.get("id_Jenisha")));
			mh.put("socIdNegeri", String.valueOf(b.get("idnegeri")));
			mh.put("socIdDaerah", String.valueOf(b.get("iddaerah")));
			setSocHAKemaskini(mh,context);

			permohonanInternal.getHarta(mati);
			context.put("listHa", permohonanInternal.getDataHA());
			context.put("jumList", permohonanInternal.getDataHA().size());

			logic_A.setSumDataHa(mati);
			sumppkha = logic_A.getSumDataHa();
			context.put("jumppkha", sumppkha);

//			logic_B.setDataHta(id);
//			listppkhta = logic_B.getDataHta();
//			this.context.put("listhta2", listppkhta);
//			int countListhta = listppkhta.size();
//			this.context.put("jumListhta2", countList);
//
//			logic_B.setDataHa(id);
//			listppkha2 = logic_B.getHa2();
//			this.context.put("listHa2", listppkha2);
//			int countListha2 = listppkha2.size();
//			this.context.put("jumListHa2", countListha2);
//
			logic_A.updateDataNilai(idPermohonan, mati, idUser);
//
//			logic_A.setOverallSum(mati);
//			overallnilai = logic_A.getOverallSum();
//			this.context.put("overall", overallnilai);
//
//			logic_A.setOverallSumMati(mati);
//			overallnilaimati = logic_A.getOverallSumMati();
//			this.context.put("overallmati", overallnilaimati);
//			listJenisha = logic_A.getJenisHa();
//			this.context.put("ViewJenisHa", listJenisha);
//
//			this.context.put("id", id);
//			this.context.put("id1", id1);
//			this.context.put("id2", id2);
			//online
			//context.put("EventStatus", 3);
			context.put("EventStatus", eventStatus);
			context.put("idha", idha);
			context.put("tutup", "yes");
			context.put("showadd", "1");
			context.put("showbuttontambah", "yes");

		} else if ("hapus_harta".equals(mode)) {
//			String id = getParam("idPermohonan");
//			String id2 = getParam("idPemohon");
//			String id1 = getParam("idSimati");
//			String mati = getParam("id_Permohonansimati");
			String eventstatus =  fnc.getParam(request,"eventStatus");
			String idha =  fnc.getParam(request,"idha");
			deleteHA(session, mati, idha);
//
//			logic_A.setDataHa(mati);
//			listppkha = logic_A.getDataHa();
//			this.context.put("listHa", listppkha);
//			int countList = listppkha.size();
//			this.context.put("jumList", countList);
			permohonanInternal.getHarta(mati);
			context.put("listHa", permohonanInternal.getDataHA());
			context.put("jumList", permohonanInternal.getDataHA().size());

			logic_A.setSumDataHa(mati);
			sumppkha = logic_A.getSumDataHa();
			context.put("jumppkha", sumppkha);

//			logic_A.setSumDataHa(mati);
//			sumppkha = logic_A.getSumDataHa();
//			this.context.put("jumppkha", sumppkha);
//
//			logic_B.setDataHta(id);
//			listppkhta = logic_B.getDataHta();
//			this.context.put("listhta2", listppkhta);
//			int countListhta = listppkhta.size();
//			this.context.put("jumListhta2", countList);
//
//			logic_B.setDataHa(id);
//			listppkha2 = logic_B.getHa2();
//			this.context.put("listHa2", listppkha2);
//			int countListha2 = listppkha2.size();
//			this.context.put("jumListHa2", countListha2);
//
//			logic_A.updateDataNilai(id, mati, idUser);
//
//			logic_A.setOverallSum(mati);
//			overallnilai = logic_A.getOverallSum();
//			this.context.put("overall", overallnilai);
//
//			logic_A.setOverallSumMati(mati);
//			overallnilaimati = logic_A.getOverallSumMati();
//			this.context.put("overallmati", overallnilaimati);
//
//			listJenisha = logic_A.getJenisHa();
//			this.context.put("ViewJenisHa", listJenisha);
//
//			this.context.put("id", id);
//			this.context.put("id1", id1);
			context.put("EventStatus", eventstatus);
			context.put("idha", idha);
			context.put("showbuttonkembali", "yes");
			context.put("showbuttontambah", "yes");

		} else if ("negerichange".equals(mode)) {
			//String eventstatus = fnc.getParam(request,"eventStatus");

			int idnegeri = Integer.parseInt(fnc.getParam(request,"socNegeriHtaam"));

			Vector listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
			context.put("listDaerahbyNegeri", listnegeribydaerah);

			context.put("socJenisHa", fnc.getParam(request,"socJenisHartaAlih"));
			context.put("norujukan", fnc.getParam(request,"txtNoRujukan"));
			context.put("nosijil", fnc.getParam(request,"txtNoSijil"));
			context.put("bilunit", fnc.getParam(request,"txtBilUnit"));
			context.put("nilaiseunit", fnc.getParam(request,"txtNilaiSeunit"));
			context.put("agensi", fnc.getParam(request,"txtAgensi"));
			context.put("txtAlamat1", fnc.getParam(request,"txtAlamat1"));
			context.put("txtAlamat2", fnc.getParam(request,"txtAlamat2"));
			context.put("txtAlamat3", fnc.getParam(request,"txtAlamat3"));
			context.put("txtPoskod", fnc.getParam(request,"txtPoskod"));
			context.put("negeri", fnc.getParam(request,"socNegeriHtaam"));
			context.put("daerah", fnc.getParam(request,"socDaerahHtaam"));
			context.put("bhgnmati1", fnc.getParam(request,"txtBhgnSimati1"));
			context.put("bhgnmati2", fnc.getParam(request,"txtBhgnSimati2"));
			context.put("nilaitarikhmati",fnc.getParam(request,"txtNilaiTarikhMati"));
			context.put("nilaitarikhmohon",fnc.getParam(request,"txtNilaiTarikhMohon"));
			context.put("catatan", fnc.getParam(request,"txtCatatan"));
			context.put("butiran", fnc.getParam(request,"butiran"));
			context.put("nama_saham", fnc.getParam(request,"nama_saham"));
			context.put("FLAG_DAFTAR", fnc.getParam(request,"FLAG_DAFTAR"));

			permohonanInternal.getHarta(mati);
			context.put("listHa", permohonanInternal.getDataHA());
			context.put("jumList", permohonanInternal.getDataHA().size());


//			logic_A.setSumDataHa(mati);
//			sumppkha = logic_A.getSumDataHa();
//			this.context.put("jumppkha", sumppkha);
//
//			logic_B.setDataHta(id);
//			listppkhta = logic_B.getDataHta();
//			this.context.put("listhta2", listppkhta);
//			int countListhta = listppkhta.size();
//			this.context.put("jumListhta2", countList);
//
//			logic_B.setDataHa(id);
//			listppkha2 = logic_B.getHa2();
//			this.context.put("listHa2", listppkha2);
//			int countListha2 = listppkha2.size();
//			this.context.put("jumListHa2", countListha2);
//
//			logic_A.updateDataNilai(id, mati,idUser);
//
//			logic_A.setOverallSum(mati);
//			overallnilai = logic_A.getOverallSum();
//			this.context.put("overall", overallnilai);
//
//			logic_A.setOverallSumMati(mati);
//			overallnilaimati = logic_A.getOverallSumMati();
//			this.context.put("overallmati", overallnilaimati);

//			listJenisha = logic_A.getJenisHa();
//			this.context.put("ViewJenisHa", listJenisha);
//2018			String listJenisha = getPilihanHA().Pilihan("socJenisHartaAlih"
//					, fnc.getParam(request,"id_Jenisha")
//					, " class=\"autoselect\" "
//					, "onChange=\"setSelected(2,0,0,0);getJenisHa(this.value)\"");
//	
//end 2018			context.put("ViewJenisHa", listJenisha);
			mh = new Hashtable<String,String>();
			mh.put("socJenisHartaAlih", fnc.getParam(request,"socJenisHartaAlih"));
			mh.put("socIdNegeri", fnc.getParam(request,"socNegeriHtaam"));
			mh.put("socIdDaerah", fnc.getParam(request,"socDaerahHtaam"));
			setSocHA(mh,context);
			
//			if (fnc.getParam(request,"socNegeriHtaam") != ""
//					&& fnc.getParam(request,"socNegeriHtaam") != "0") {
//				Vector s3 = logic_A.getListBandarByNegeri(Integer
//						.parseInt(fnc.getParam(request,"socNegeriHtaam")));
//				context.put("listBandarSuratbyNegeri", s3);
//			} else {
//				context.put("listBandarSuratbyNegeri", "");
//			}

//			context.put("id", idPermohonan);
//			context.put("id1", idSimati);
			context.put("EventStatus", 0);
			context.put("negeri", idnegeri);
			context.put("showadd", "1");

		}else if ("negerichangeU".equals(mode)) {
			Vector listabc = new Vector();
//
//			String id = getParam("idPermohonan");
//			String id2 = getParam("idPemohon");
//			String id1 = getParam("idSimati");
//			String mati = getParam("id_Permohonansimati");
//			String eventstatus = getParam("eventStatus");
			context.put("socJenisHa", fnc.getParam(request,"socJenisHartaAlih"));
			String idha = fnc.getParam(request,"idha");
//
			int idnegeri = 0;
			if (!"".equals(fnc.getParam(request,"socNegeriHtaam"))
					&& !"0".equals(fnc.getParam(request,"socNegeriHtaam"))) {
				idnegeri = Integer.parseInt(fnc.getParam(request,"socNegeriHtaam"));
			} else {
				idnegeri = 0;
			}
			Vector listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
			context.put("listDaerahbyNegeri", listnegeribydaerah);
//
			Hashtable h;
			h = new Hashtable();
//
			h.put("negeri", idnegeri);
			h.put("daerah", "");
			h.put("socJenisHa", fnc.getParam(request,"socJenisHartaAlih"));
			h.put("norujukan", fnc.getParam(request,"txtNoRujukan"));
			h.put("nosijil", fnc.getParam(request,"txtNoSijil"));
			h.put("bilunit", fnc.getParam(request,"txtBilUnit"));
			h.put("jenama", fnc.getParam(request,"txtAgensi"));

			h.put("nilaiseunit", fnc.getParam(request,"txtNilaiSeunit"));
			h.put("agensi", fnc.getParam(request,"txtAgensi"));
			h.put("alamat1", fnc.getParam(request,"txtAlamat1"));
			h.put("alamat2", fnc.getParam(request,"txtAlamat2"));
			h.put("alamat3", fnc.getParam(request,"txtAlamat3"));
			h.put("poskod", fnc.getParam(request,"txtPoskod"));

			// h.put("negeri", idnegeri);
			// h.put("daerah", getParam("socDaerahHtaam"));
			h.put("basimati", fnc.getParam(request,"txtBhgnSimati1"));
			h.put("bbsimati", fnc.getParam(request,"txtBhgnSimati2"));
			h.put("nilaiha_tarikhmati", fnc.getParam(request,"txtNilaiTarikhMati"));
			h.put("nilaiha_tarikhmohon", fnc.getParam(request,"txtNilaiTarikhMohon"));
			h.put("catatan", fnc.getParam(request,"txtCatatan"));
			h.put("nama_saham", fnc.getParam(request,"nama_saham"));
			h.put("FLAG_DAFTAR", fnc.getParam(request,"FLAG_DAFTAR"));
			h.put("butiran", fnc.getParam(request,"butiran"));
			listabc.addElement(h);

			context.put("DataHa", listabc);
//
//			logic_A.setDataHa(mati);
//			listppkha = logic_A.getDataHa();
//			this.context.put("listHa", listppkha);
//			int countList = listppkha.size();
//			this.context.put("jumList", countList);
//
//			logic_A.setSumDataHa(mati);
//			sumppkha = logic_A.getSumDataHa();
//			this.context.put("jumppkha", sumppkha);
//
//			logic_B.setDataHta(id);
//			listppkhta = logic_B.getDataHta();
//			this.context.put("listhta2", listppkhta);
//			int countListhta = listppkhta.size();
//			this.context.put("jumListhta2", countList);
//
//			logic_B.setDataHa(id);
//			listppkha2 = logic_B.getHa2();
//			this.context.put("listHa2", listppkha2);
//			int countListha2 = listppkha2.size();
//			this.context.put("jumListHa2", countListha2);
//
//			logic_A.updateDataNilai(id, mati, idUser);
//
//			logic_A.setOverallSum(mati);
//			overallnilai = logic_A.getOverallSum();
//			this.context.put("overall", overallnilai);
//
//			logic_A.setOverallSumMati(mati);
//			overallnilaimati = logic_A.getOverallSumMati();
//			this.context.put("overallmati", overallnilaimati);
//
//			listJenisha = logic_A.getJenisHa();
//			this.context.put("ViewJenisHa", listJenisha);
//
//			this.context.put("id", id);
//			this.context.put("id2", id2);
//			this.context.put("idha", idha);
//			this.context.put("id1", id1);
			context.put("EventStatus", 3);
			context.put("showadd", "1");
			context.put("showbuttontambah", "yes");
			context.put("tutup", "yes");
			
		}
		context.put("id", idPermohonan);
		context.put("id1", idSimati);
		context.put("id2", idPemohon);

		
	}	

	public void updateDataNilai(String idPermohonan, String id1, String user) throws Exception {
		Connection conn = null;
		Db db = null;
		// Db db1 = null;
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			String sql = "update tblppkpermohonan set id_kemaskini = '"
					+ user
					+ "' , tarikh_kemaskini = sysdate, "
					+ "JUMLAH_HTA_TARIKHMOHON = (Select sum(a.NILAI_HTA_TARIKHMOHON) " +
							" from tblppkhta a1,tblppkhtapermohonan a where " +
							" a1.id_hta = a.id_hta " +
							" and a1.id_permohonansimati = a.id_permohonansimati " +
							" and a1.id_Permohonansimati = "
					+ id1
					+ "), "
					+ "JUMLAH_HTA_TARIKHMATI = (Select sum(a.NILAI_HTA_TARIKHMATI) " +
							" from tblppkhta a1,tblppkhtapermohonan a " +
							" where a1.id_hta = a.id_hta " +
							" and a1.id_permohonansimati = a.id_permohonansimati" +
							" and a1.id_Permohonansimati = "
					+ id1
					+ "), "
					+ "JUMLAH_HA_TARIKHMOHON = (Select sum(b.NILAI_HA_TARIKHMOHON) " +
							" from tblppkha b1,tblppkhapermohonan b " +
							" where b1.id_ha = b.id_ha and b1.id_permohonansimati = b.id_permohonansimati " +
							" and b1.id_Permohonansimati = "
					+ id1
					+ "),"
					+ "JUMLAH_HA_TARIKHMATI = (Select sum(b.NILAI_HA_TARIKHMATI) " +
							" from tblppkha b1,tblppkhapermohonan b " +
							" where " +
							" b1.id_ha = b.id_ha and b1.id_permohonansimati = b.id_permohonansimati " +
							" and b.id_Permohonansimati = "
					+ id1
					+ "),"
					+ "JUMLAH_HARTA_TARIKHMOHON = NVL(JUMLAH_HTA_TARIKHMOHON,0) + NVL(JUMLAH_HA_TARIKHMOHON,0), "
					+ " JUMLAH_HARTA_TARIKHMATI = NVL(JUMLAH_HTA_TARIKHMATI,0) + NVL(JUMLAH_HA_TARIKHMATI,0) "
					+ " where id_permohonan = " + idPermohonan + "";
			//myLogger.info("UPDATE NILAIAN SEK8:"+sql.toUpperCase());
			stmt.executeUpdate(sql);

			// db1 = new Db();
			Statement stmtT = db.getStatement();

			String sqlT = "update tblppkpermohonan set id_kemaskini = '"
					+ user
					+ "' , tarikh_kemaskini = sysdate, "
					+ "JUM_HTA_TAMBAHAN_TKHMOHON = (Select sum(a.NILAI_HTA_TARIKHMOHON) " +
							" from tblppkhta a1,tblppkhtapermohonan a, " +
							" tblppkpermohonan p, tblppkpermohonansimati sm  " +
							" where a1.id_hta = a.id_hta and a.id_permohonansimati = '"+id1+"' " +
							" and sm.id_Permohonan = p.id_Permohonan " +
							" and a1.id_Permohonansimati = sm.id_Permohonansimati " +
							" and p.id_Permohonan = "
					+ idPermohonan
					+ "), "
					+ "JUM_HTA_TAMBAHAN_TKHMATI = (Select sum(a.NILAI_HTA_TARIKHMATI) " +
							" from tblppkhta a1,tblppkhtapermohonan a, tblppkpermohonan p, tblppkpermohonansimati sm  " +
							" where a1.id_hta = a.id_hta and a.id_permohonansimati = '"+id1+"' " +
							" and sm.id_Permohonan = p.id_Permohonan " +
							" and a1.id_Permohonansimati = sm.id_Permohonansimati " +
							" and p.id_Permohonan = "
					+ idPermohonan
					+ "), "
					+ "JUM_HA_TAMBAHAN_TKHMOHON = (Select sum(a.NILAI_HA_TARIKHMOHON) " +
							" from tblppkha a1,tblppkhapermohonan a, " +
							" tblppkpermohonan p, tblppkpermohonansimati sm  " +
							" where a1.id_ha = a.id_ha and a.id_permohonansimati = '"+id1+"' " +
							" and sm.id_Permohonan = p.id_Permohonan " +
							" and a1.id_Permohonansimati = sm.id_Permohonansimati and p.id_Permohonan = "
					+ idPermohonan
					+ "),"
					+ "JUM_HA_TAMBAHAN_TKHMATI = (Select sum(a.NILAI_HA_TARIKHMATI) " +
							" from tblppkha a1,tblppkhapermohonan a, " +
							" tblppkpermohonan p, tblppkpermohonansimati sm  " +
							" where a1.id_ha = a.id_ha and a.id_permohonansimati = '"+id1+"' " +
							" and sm.id_Permohonan = p.id_Permohonan " +
							" and a1.id_Permohonansimati = sm.id_Permohonansimati and p.id_Permohonan = "
					+ idPermohonan
					+ "),"
					+ "JUM_HARTA_TAMBAHAN_TKHMOHON = NVL(JUM_HTA_TAMBAHAN_TKHMOHON,0) + NVL(JUM_HA_TAMBAHAN_TKHMOHON,0), "
					+ "JUM_HARTA_TAMBAHAN_TKHMATI = NVL(JUM_HTA_TAMBAHAN_TKHMATI,0) + NVL(JUM_HA_TAMBAHAN_TKHMATI,0) "
					+ "where id_permohonan = " + idPermohonan + "";
			//System.out.println("UPDATE DATA NILAI :" + sqlT.toUpperCase());
			stmtT.executeUpdate(sqlT);
			conn.commit();

		}catch (Exception re) {
			myLog.info("Error: "+re.getMessage());
			//throw re;

		} finally {
			if (db != null)
				db.close();
		}
		
	}
	
	private void deleteHA(HttpSession session, String id1, String id3) throws Exception {
		logic_A.deleteDataHa(id1, id3);
	}
	
//	private void addHA(HttpSession session,HttpServletRequest request,Fungsi fnc) throws Exception {
//		String id = fnc.getParam(request,"id");
//		String id1 =fnc.getParam(request,"idSimati");
//		String socJenisHartaAlih =fnc.getParam(request,"socJenisHartaAlih");
//		String txtBhgnSimati1 =fnc.getParam(request,"txtBhgnSimati1");
//		String txtBhgnSimati2 =fnc.getParam(request,"txtBhgnSimati2");
//		String txtNoRujukan =fnc.getParam(request,"txtNoRujukan");
//		String txtNilaiTarikhMati =fnc.getParam(request,"txtNilaiTarikhMati");
//		String txtNoSijil =fnc.getParam(request,"txtNoSijil");
//		String txtNilaiTarikhMohon =fnc.getParam(request,"txtNilaiTarikhMohon");
//		String txtBilUnit =fnc.getParam(request,"txtBilUnit");
//		String txtNilaiSeunit =fnc.getParam(request,"txtNilaiSeunit");
//		String txtAgensi =fnc.getParam(request,"txtAgensi");
//		String txtCatatan =fnc.getParam(request,"txtCatatan");
//		String socNegeriHtaam =fnc.getParam(request,"socNegeriHtaam");
//		String socDaerahHtaam =fnc.getParam(request,"socDaerahHtaam");
//		String bil =fnc.getParam(request,"bil");
//		String mati =fnc.getParam(request,"id_Permohonansimati");
//
//		String txtAlamat1 =fnc.getParam(request,"txtAlamat1");
//		String nama_saham =fnc.getParam(request,"nama_saham");
//		String txtAlamat2 =fnc.getParam(request,"txtAlamat2");
//		String txtAlamat3 =fnc.getParam(request,"txtAlamat3");
//		String txtPoskod =fnc.getParam(request,"txtPoskod");
//
//		Hashtable h = null;
//		h = new Hashtable();
//		h.put("FLAG_DAFTAR",fnc.getParam(request,"FLAG_DAFTAR"));
//		h.put("id", id);
//		h.put("id1", id1);
//		h.put("id_Permohonansimati", mati);
//		h.put("nama_saham", nama_saham);
//		h.put("socJenisHartaAlih", socJenisHartaAlih);
//		h.put("txtBhgnSimati1", txtBhgnSimati1);
//		h.put("txtBhgnSimati2", txtBhgnSimati2);
//		h.put("txtNoRujukan", txtNoRujukan);
//		h.put("txtNilaiTarikhMati", txtNilaiTarikhMati);
//		h.put("txtNoSijil", txtNoSijil);
//		h.put("txtNilaiTarikhMohon", txtNilaiTarikhMohon);
//		h.put("txtBilUnit", txtBilUnit);
//		h.put("txtNilaiSeunit", txtNilaiSeunit);
//		h.put("txtAgensi", txtAgensi);
//		h.put("txtCatatan", txtCatatan);
//		h.put("bil", bil);
//
//		h.put("txtAlamat1", txtAlamat1);
//		h.put("txtAlamat2", txtAlamat2);
//		h.put("txtAlamat3", txtAlamat3);
//		h.put("txtPoskod", txtPoskod);
//		h.put("butiran",fnc.getParam(request,"butiran"));
//
//		h.put("socNegeriHtaam", socNegeriHtaam);
//		h.put("socDaerahHtaam", socDaerahHtaam);
//		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
//
//		logic_A.addHa(h);
//	}

//	private void updateHA(HttpSession session,HttpServletRequest request,Fungsi fnc) throws Exception {
//		String id1 =fnc.getParam(request,"idSimati");
//		String id3 =fnc.getParam(request,"idha");
//		String socJenisHartaAlih =fnc.getParam(request,"socJenisHartaAlih");
//		String txtBhgnSimati1 =fnc.getParam(request,"txtBhgnSimati1");
//		String txtBhgnSimati2 =fnc.getParam(request,"txtBhgnSimati2");
//		String txtNoRujukan =fnc.getParam(request,"txtNoRujukan");
//		//String txtNilaiTarikhMati =fnc.getParam(request,"txtNilaiTarikhMati");
//		String txtNilaiTarikhMati = Utils.RemoveComma(fnc.getParam(request,"txtNilaiTarikhMati"));
//		String txtNoSijil =fnc.getParam(request,"txtNoSijil");
//		//String txtNilaiTarikhMohon = fnc.getParam(request,"txtNilaiTarikhMohon");
//		String txtNilaiTarikhMohon = Utils.RemoveComma(fnc.getParam(request,"txtNilaiTarikhMohon"));
//		String txtBilUnit =fnc.getParam(request,"txtBilUnit");
//		String txtNilaiSeunit =fnc.getParam(request,"txtNilaiSeunit");
//		String Agensi =fnc.getParam(request,"txtAgensi");
//		String txtCatatan =fnc.getParam(request,"txtCatatan");
//		String bil =fnc.getParam(request,"bil");
//		String txtAlamat1 =fnc.getParam(request,"txtAlamat1");
//		String txtAlamat2 =fnc.getParam(request,"txtAlamat2");
//		String txtAlamat3 =fnc.getParam(request,"txtAlamat3");
//		String txtPoskod =fnc.getParam(request,"txtPoskod");
//		String nama_saham =fnc.getParam(request,"nama_saham");
//
//		String socDaerahHtaam =fnc.getParam(request,"socDaerahHtaam");
//		String socNegeriHtaam =fnc.getParam(request,"socNegeriHtaam");
//
//		Hashtable h = null;
//		h = new Hashtable();
//		h.put("FLAG_DAFTAR",fnc.getParam(request,"FLAG_DAFTAR"));
//		h.put("id_Permohonansimati",fnc.getParam(request,"id_Permohonansimati"));
//		h.put("id3", id3);
//		h.put("id1", id1);
//		h.put("socJenisHartaAlih", socJenisHartaAlih);
//		h.put("txtBhgnSimati1", txtBhgnSimati1);
//		h.put("txtBhgnSimati2", txtBhgnSimati2);
//		h.put("txtNoRujukan", txtNoRujukan);
//		h.put("txtNilaiTarikhMati", txtNilaiTarikhMati);
//		h.put("txtNoSijil", txtNoSijil);
//		h.put("txtNilaiTarikhMohon", txtNilaiTarikhMohon);
//		h.put("txtBilUnit", txtBilUnit);
//		h.put("txtNilaiSeunit", txtNilaiSeunit);
//		h.put("Agensi", Agensi);
//		h.put("nama_saham", nama_saham);
//		h.put("txtCatatan", txtCatatan);
//		h.put("bil", bil);
//		h.put("txtAlamat1", txtAlamat1);
//		h.put("txtAlamat2", txtAlamat2);
//		h.put("txtAlamat3", txtAlamat3);
//		h.put("txtPoskod", txtPoskod);
//		h.put("butiran",fnc.getParam(request,"butiran"));
//		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
//
//		if (socNegeriHtaam != "" && socNegeriHtaam != "0") {
//			h.put("socDaerahHtaam", socDaerahHtaam);
//		} else {
//			h.put("socDaerahHtaam", "0");
//		}
//
//		h.put("socNegeriHtaam", socNegeriHtaam);
//		logic_A.kemaskiniHa(h);
//		
//	}
	public String addHtaamX(Hashtable<String,String> data) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		try {

			String idsimati = (String) data.get("idSimati");
			String id_Permohonansimati = (String) data.get("id_Permohonansimati");
			//String nopt = (String) data.get("nopt");
			String nilai_Hta_memohon = (String) data.get("nilai_Hta_memohon");
			String nilai_Hta_mati = (String) data.get("nilai_Hta_mati");
			String kategori = String.valueOf(data.get("kategori"));
			//int jenishakmilik = (Integer) data.get("jenishakmilik");
			String pemilikan = (String) data.get("pemilikan");
			String negeri = String.valueOf(data.get("negeri"));
			String daerah =String.valueOf(data.get("daerah"));
			String mukim = String.valueOf(data.get("mukim"));
			//String alamat_hta1 = (String) data.get("alamat_hta1");
			String alamathta1 = (String) data.get("alamathta1");
			String alamathta2 = (String) data.get("alamathta2");
			String alamathta3 = (String) data.get("alamathta3");
			String poskodhta = (String) data.get("poskodhta");
			String bandarhta = String.valueOf(data.get("bandarhta"));
					
			String luashmp = (String) data.get("luashmp");
			String luasasal = (String) data.get("luasasal");
			String nopajakan = (String) data.get("nopajakan");
			String jenistanah = (String) data.get("jenistanah");
			String basimati = (String) data.get("basimati");
			String bbsimati = (String) data.get("bbsimati");
			String tanggungan = (String) data.get("tanggungan");
			String jenisluas = String.valueOf(data.get("jenisluas"));
			String catatan = String.valueOf(data.get("catatan"));
			String jeniskepentingan = (String) data.get("jeniskepentingan");
			String noperserahan =  String.valueOf(data.get("noperserahan"));
			String namapemaju = (String) data.get("namapemaju");
			String alamatpemaju1 = (String) data.get("alamatpemaju1");
			String alamatpemaju2 = (String) data.get("alamatpemaju2");
			String alamatpemaju3 = (String) data.get("alamatpemaju3");
			String poskodpemaju = (String) data.get("poskodpemaju");
			String bandarpemaju = String.valueOf(data.get("bandarpemaju"));
			String negeripemaju =String.valueOf(data.get("negeripemaju"));
			
			String noperjanjian = (String) data.get("noperjanjian");
			String tarikhperja = (String) data.get("tarikhperjanjian");
			String tarikhperjanjian = "to_date('" + tarikhperja + "','dd/MM/yyyy')";
			String namarancangan = (String) data.get("namarancangan");
			String noroh = (String) data.get("noroh");
			String nolot = (String) data.get("nolot");
			String nocagaran = (String) data.get("nocagaran");
			String flag = (String) data.get("flag");
			String id_Masuk = (String) data.get("id_Masuk");
			//String tarikh_Masuk = (String) data.get("tarikh_Masuk");
			String FLAG_DAFTAR = (String) data.get("FLAG_DAFTAR");

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			//String id_hta = String.valueOf(DB.getNextID(db, "TBLPPKHTA_SEQ"));
			idhtaamid = String.valueOf(DB.getNextID(db, "TBLPPKHTA_SEQ"));
			r.add("id_hta", r.unquote(idhtaamid));
			r.add("id_simati", r.unquote(idsimati));
			r.add("id_permohonansimati", r.unquote(id_Permohonansimati));
			//r.add("no_Pt", nopt);
			r.add("nilai_hta_tarikhmohon", nilai_Hta_memohon);
			r.add("nilai_hta_tarikhmati", nilai_Hta_mati);
			if(!kategori.equals("")){
				r.add("id_kategori", r.unquote(kategori));
			}
			//r.add("id_Jenishm", jenishakmilik);
			r.add("status_pemilikan", pemilikan);
			r.add("id_negeri", r.unquote(negeri));
			r.add("id_daerah", r.unquote(daerah));
			r.add("id_mukim", r.unquote(mukim));
			r.add("luas_Hmp", luashmp);
			r.add("luas", luasasal);
			r.add("no_pajakan", nopajakan);
			r.add("jenis_tnh", jenistanah);
			r.add("ba_simati", basimati);
			r.add("bb_simati", bbsimati);
			if(!tanggungan.equals("")){
				r.add("tanggungan", tanggungan);
			}
			if(!jenisluas.equals("")){
				r.add("id_luas", r.unquote(jenisluas));
			}
			r.add("catatan", catatan);
			r.add("no_perserahan", noperserahan);
			r.add("jenis_hta", "T");
			r.add("nama_pemaju", namapemaju);
			r.add("alamat_pemaju1", alamatpemaju1);
			r.add("alamat_pemaju2", alamatpemaju2);
			r.add("alamat_pemaju3", alamatpemaju3);
			r.add("poskod_pemaju", poskodpemaju);
			if(!bandarpemaju.equals("")){
				r.add("id_bandarpemaju", r.unquote(bandarpemaju));
			}
			if(!negeripemaju.equals("")){
				r.add("id_negeripemaju", r.unquote(negeripemaju));
			}
			r.add("alamat_hta1", alamathta1);
			r.add("alamat_hta2", alamathta2);
			r.add("alamat_hta3", alamathta3);
			r.add("poskod_hta", poskodhta);
			if(!bandarhta.equals("")){
				r.add("id_bandarhta", r.unquote(bandarhta));
			}
			r.add("no_Perjanjian", noperjanjian);
			if(!tarikhperjanjian.equals("")){
				r.add("tarikh_Perjanjian", r.unquote(tarikhperjanjian));
			}
			r.add("nama_Rancangan", namarancangan);
			r.add("no_Roh", noroh);
			if(!nolot.equals("")){
				r.add("no_Lot_Id", nolot);
			}
			r.add("no_Cagaran", nocagaran);
			r.add("jenis_Kepentingan", jeniskepentingan);
			r.add("flag_kategori_hta", flag);
			r.add("id_masuk", id_Masuk);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("flag_pa", "T");
			r.add("flag_pt", "T");
			r.add("flag_selesai", "T");
			r.add("flag_daftar", FLAG_DAFTAR);
			sql = r.getSQLInsert("tblppkhta");
			myLog.info("tblppkhta:sql="+sql);
			stmt.executeUpdate(sql);

			r.clear();
			r.add("id_hta", r.unquote(idhtaamid));
			r.add("id_simati", r.unquote(idsimati));
			r.add("id_permohonansimati", r.unquote(id_Permohonansimati));
			//r.add("no_Pt", nopt);
			r.add("nilai_hta_tarikhmohon", nilai_Hta_memohon);
			r.add("nilai_hta_tarikhmati", nilai_Hta_mati);
			if(!kategori.equals("")){
				r.add("id_kategori", kategori);
			}
			//r.add("id_Jenishm", jenishakmilik);
			r.add("status_pemilikan", pemilikan);
			r.add("id_negeri", r.unquote(negeri));
			r.add("id_daerah", r.unquote(daerah));
			r.add("id_mukim", r.unquote(mukim));
			r.add("luas_hmp", luashmp);
			r.add("luas", luasasal);
			r.add("no_pajakan", nopajakan);
			r.add("jenis_tnh", jenistanah);
			r.add("ba_simati", basimati);
			r.add("bb_simati", bbsimati);
			if(!tanggungan.equals("")){
			r.add("tanggungan", tanggungan);
			}
			if(!jenisluas.equals("") || !jenisluas.equals(null)){
				r.add("id_luas", r.unquote(jenisluas));
			}
			r.add("catatan", catatan);
			r.add("no_perserahan", noperserahan);
			r.add("jenis_hta", "T");
			r.add("nama_pemaju", namapemaju);
			r.add("alamat_pemaju1", alamatpemaju1);
			r.add("alamat_pemaju2", alamatpemaju2);
			r.add("alamat_pemaju3", alamatpemaju3);
			r.add("poskod_pemaju", poskodpemaju);
			if(!bandarpemaju.equals("")){
				r.add("id_bandarpemaju", r.unquote(bandarpemaju));
			}
			if(!negeripemaju.equals("")){
				r.add("id_negeripemaju", r.unquote(negeripemaju));
			}
			r.add("alamat_hta1", alamathta1);
			r.add("alamat_hta2", alamathta2);
			r.add("alamat_hta3", alamathta3);
			r.add("poskod_hta", poskodhta);
			if(!bandarhta.equals("")){
				r.add("id_bandarhta", r.unquote(bandarhta));
			}
			r.add("no_perjanjian", noperjanjian);
			if(!tarikhperjanjian.equals("")){
				r.add("tarikh_perjanjian", r.unquote(tarikhperjanjian));
			}
			r.add("nama_rancangan", namarancangan);
			r.add("no_roh", noroh);
			if(!nolot.equals("")){
				r.add("no_Lot_Id", nolot);
			}
			r.add("no_cagaran", nocagaran);
			r.add("jenis_kepentingan", jeniskepentingan);
			r.add("flag_kategori_hta", flag);
			r.add("id_masuk", id_Masuk);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("flag_pa", "T");
			r.add("flag_pt", "T");
			r.add("flag_selesai", "T");
			r.add("flag_daftar", FLAG_DAFTAR);
			sql = r.getSQLInsert("tblppkhtapermohonan");
			myLog.info("tblppkhtapermohonan:sql="+sql);
			stmt.executeUpdate(sql);

			conn.commit();
			
		}catch (Exception re) {
			myLog.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
		return idhtaamid;
		
	}

	public void addHtaamX_(Hashtable<String,String> data
		,FrmPrmhnnSek8InternalData logicInternal,HttpServletRequest request) throws Exception {
		Hashtable<String,String> h = new Hashtable<String,String>();
		h.put("FLAG_DAFTAR", data.get("FLAG_DAFTAR"));
		h.put("idSimati", String.valueOf(data.get("idSimati")));
		h.put("id_Permohonansimati", String.valueOf(data.get("id_Permohonansimati")));
		h.put("nopt", String.valueOf(data.get("txtNoPTHtaamX")));
		h.put("nilai_Hta_memohon", String.valueOf(data.get("txtNilaiTarikhMohonHtaaX")));
		h.put("nilai_Hta_mati", String.valueOf(data.get("txtNilaiTarikhMatiHtaamX")));

		h.put("kategori", (String.valueOf(data.get("socKategoriTanahHtaamX"))));
		h.put("jenishakmilik", (String.valueOf(data.get("socKategoriTanahHtaamX"))));

		h.put("pemilikan", String.valueOf(data.get("socStatusPemilikanHtaamX")));
		h.put("negeri", String.valueOf(data.get("socNegeriHtaamX")));
		h.put("daerah", String.valueOf(data.get("socDaerahHtaamX")));
		h.put("mukim", String.valueOf(data.get("socMukimHtaamX")));
		h.put("jenisluas", String.valueOf(data.get("socJenisLuasHtaamX")));
		h.put("luashmp", String.valueOf(data.get("txtLuasHMpHtaamX")));
		h.put("luasasal", String.valueOf(data.get("txtLuasAsalHtaamX")));
		h.put("nopajakan", String.valueOf(data.get("txtNoPajakanX")));
		h.put("jenistanah", String.valueOf(data.get("socJenisTanahHtaamX")));
		h.put("basimati", String.valueOf(data.get("txtBahagianSimati1X")));
		h.put("bbsimati", String.valueOf(data.get("txtBahagianSimati2X")));		
		h.put("tanggungan", String.valueOf(data.get("txtTanggunganHtaamX")));
		h.put("namapemaju", String.valueOf(data.get("txtNamaPemajuHtaamX")));
		h.put("alamatpemaju1", String.valueOf(data.get("txtAlamatPemaju1HtaamX")));
		h.put("alamatpemaju2", String.valueOf(data.get("txtAlamatPemaju2HtaamX")));
		h.put("alamatpemaju3", String.valueOf(data.get("txtAlamatPemaju3HtaamX")));
		h.put("poskodpemaju", String.valueOf(data.get("txtPoskodPemaju1HtaamX")));
		h.put("bandarpemaju", String.valueOf(data.get("txtBandarPemaju1HtaamX")));
		h.put("negeripemaju", String.valueOf(data.get("txtBandarPemaju1HtaamX")));

		h.put("alamathta1", String.valueOf(data.get("txtAlamatHarta1HtaamX")));
		h.put("alamathta2", String.valueOf(data.get("txtAlamatHarta2HtaamX")));
		h.put("alamathta3", String.valueOf(data.get("txtAlamatHarta3HtaamX")));
		h.put("poskodhta", String.valueOf(data.get("txtPoskodHartaHtaamX")));
		h.put("bandarhta", String.valueOf(data.get("txtBandarHartaHtaamX")));
		
		h.put("noperjanjian", String.valueOf(data.get("txtNoPerjanjianHtaamX")));
		h.put("tarikhperjanjian", String.valueOf(data.get("txtTarikhPerjanjianHtaamX")));
		h.put("namarancangan", String.valueOf(data.get("txtNamaRancanganHtaamX")));
		h.put("noroh", String.valueOf(data.get("txtNoRohHtaamX")));
		h.put("nolot", String.valueOf(data.get("txtLotIdHtaamX")));
		h.put("jeniskepentingan", String.valueOf(data.get("txtJenisKepentinganX")));
		h.put("nocagaran", String.valueOf(data.get("txtNoCagaranX")));

		String[] radioHtaamViewX = request.getParameterValues("radioHtaamViewX");
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

		h.put("flag",String.valueOf(n));
		h.put("id_Masuk", String.valueOf(data.get("idUser")));
		h.put("tarikh_Masuk", currentDate);
		myLog.info("addHtaamX:h="+h);
		//logicInternal.addHtaamX(h);
		addHtaamX(h);

	}
	private void setSocHAKemaskini(Hashtable<String,String> hParam,org.apache.velocity.VelocityContext context) throws Exception  {
		Hashtable<String,String> b = setSocParamValuesHA(String.valueOf(hParam.get("socIdNegeri")),"0","0","0"
				,String.valueOf(hParam.get("socJenisHartaAlih")));
		
		setSocValues(b
			,"socNegeriHtaam","negerichange"
			,"socDaerahHtaam","daerahchangeX"
			,"socMukimHtaamX"
			,"txtBandarHartaHtaamX",""
			,"socJenisHartaAlih_"," disabled"
			,context
			);

	}	
	private void setSocHA(Hashtable<String,String> hParam,org.apache.velocity.VelocityContext context) throws Exception  {
		Hashtable<String,String> b = setSocParamValuesHA(String.valueOf(hParam.get("socIdNegeri")),"0","0","0"
				,String.valueOf(hParam.get("socJenisHartaAlih")));
		
		setSocValues(b
			,"socNegeriHtaam","negerichange"
			,"socDaerahHtaam","daerahchangeX"
			,"socMukimHtaamX"
			,"txtBandarHartaHtaamX",""
			,"socJenisHartaAlih",""
			,context
			);

	}

	public void setContextHTATH(Hashtable hParam,org.apache.velocity.VelocityContext context) throws Exception  {
		context.put("idSimati", String.valueOf(hParam.get("idSimatiX")));
		context.put("nopt", String.valueOf(hParam.get("txtNoPTHtaamX")));
		context.put("nilai_Hta_memohon",String.valueOf(hParam.get("txtNilaiTarikhMohonHtaaX")));
		context.put("nilai_Hta_mati",String.valueOf(hParam.get("txtNilaiTarikhMatiHtaamX")));
		context.put("kategori", String.valueOf(hParam.get("socKategoriTanahHtaamX")));
		context.put("luashmp", String.valueOf(hParam.get("txtLuasHMpHtaamX")));
		context.put("luasasal", String.valueOf(hParam.get("txtLuasAsalHtaamX")));
		context.put("nopajakan", String.valueOf(hParam.get("txtNoPajakanX")));
		context.put("jenistanah", String.valueOf(hParam.get("socJenisTanahHtaamX")));
		context.put("basimati", String.valueOf(hParam.get("txtBahagianSimati1X")));
		context.put("bbsimati", String.valueOf(hParam.get("txtBahagianSimati2X")));
		context.put("tanggungan", String.valueOf(hParam.get("txtTanggunganHtaamX")));
		context.put("jenisluas", String.valueOf(hParam.get("socJenisLuasHtaamX")));
		context.put("catatan", String.valueOf(hParam.get("txtCatatanHtaamX")));
		context.put("namapemaju", String.valueOf(hParam.get("txtNamaPemajuHtaamX")));
		context.put("alamatpemaju1",String.valueOf(hParam.get("txtAlamatPemaju1HtaamX")));
		context.put("alamatpemaju2",String.valueOf(hParam.get("txtAlamatPemaju2HtaamX")));
		context.put("alamatpemaju3",String.valueOf(hParam.get("txtAlamatPemaju3HtaamX")));
		context.put("poskodpemaju",String.valueOf(hParam.get("txtPoskodPemaju1HtaamX")));
		context.put("bandarpemaju",String.valueOf(hParam.get("txtBandarPemaju1HtaamX")));
		context.put("negeripemaju",String.valueOf(hParam.get("socNegeriPemajuHtaamX")));
		context.put("alamathta1",String.valueOf(hParam.get("txtAlamatHarta1HtaamX")));
		context.put("alamathta2",String.valueOf(hParam.get("txtAlamatHarta2HtaamX")));
		context.put("alamathta3",String.valueOf(hParam.get("txtAlamatHarta3HtaamX")));
		context.put("poskodhta", String.valueOf(hParam.get("txtPoskodHartaHtaamX")));
	
		context.put("noperjanjian",String.valueOf(hParam.get("txtNoPerjanjianHtaamX")));
		context.put("tarikhperjanjian",String.valueOf(hParam.get("txtTarikhPerjanjianHtaamX")));
		context.put("namarancangan",String.valueOf(hParam.get("txtNamaRancanganHtaamX")));
		context.put("noroh", String.valueOf(hParam.get("txtNoRohHtaamX")));
		context.put("nolot", String.valueOf(hParam.get("txtLotIdHtaamX")));
		//context.put("nolot", String.valueOf(hParam.get("txtLotIdHtaamX")));
		context.put("nocagaran", String.valueOf(hParam.get("txtNoCagaranX")));
		context.put("pemilikan",String.valueOf(hParam.get("socStatusPemilikanHtaamX")));
		context.put("jeniskepentingan",String.valueOf(hParam.get("txtJenisKepentinganX")));
		context.put("FLAG_DAFTAR", String.valueOf(hParam.get("FLAG_DAFTAR")));
	
	}
	
	//@Override
	public void setSocValues_(Hashtable b
			,String negeriNama,String negeriFunc
			,String daerahNama,String daerahFunc
			,String mukimNama
			,String bandarNama,String bandarFunc
			,String jenisNama,String katNama
			,String luasNama,String pbNama
			,org.apache.velocity.VelocityContext context
			) throws Exception{
			myLog.info("setSocValues:b="+b);
			String socStyle = "class=\"autoselect\" style=\"text-transform:uppercase;\" ";
			
			try{
				String nn = String.valueOf(b.get("negeri"));
				String dd = String.valueOf(b.get("daerah"));				
				String idMukim = String.valueOf(b.get("mukim"));
				String socNegeri = HTML.SelectNegeri(negeriNama, Long.valueOf(nn)
						," $!readmodenegeri id=\""+negeriNama+"\" onchange=\""+negeriFunc+"('"+daerahNama+"')\" "+socStyle);
				String socDaerah = HTML.SelectDaerahByNegeri(nn, daerahNama, Utils.parseLong(dd)
						," $!readmodedaerah id=\""+daerahNama+"\" onchange=\"setSelected(1,0,0,0);"+daerahFunc+"('"+mukimNama+"');daerah_harta();check_harta()\" "+socStyle);
				String socMukim = HTML.SelectMukimByDaerah(dd, mukimNama, Utils.parseLong(idMukim)
						," $!readmodemukim id=\""+mukimNama+"\" "+socStyle);
				
				String bandar = String.valueOf(b.get("bandar"));
				String socBandar = HTML.SelectBandarByNegeri(nn,bandarNama, Utils.parseLong(bandar)
						," id=\""+bandarNama+"\" onclick=\"CheckBandarSurat()\" "+socStyle);
				
				String jenisHakmilik = String.valueOf(b.get("jenishakmilik"));
				String socJenisHakmilik = HTML.SelectJenisHakmilik(jenisNama, Utils.parseLong(jenisHakmilik)
						," $!socJenisHakmilikHtaam2 id=\""+jenisNama+"\" "+socStyle);
				
				String kaTanah = String.valueOf(b.get("kategori"));
				String socKaTanah = HTML.SelectKategoriTanah(katNama, Utils.parseLong(kaTanah),""
						," $!readmode id=\""+katNama+"\" "+socStyle);
		
				String jenisLuas = String.valueOf(b.get("jenisluas"));
				String socLuas = HTML.SelectLuas(luasNama,Utils.parseLong(jenisLuas), ""
					," $!readmode id=\""+luasNama+"\"" +
					"onchange=\"pilih_jenis_luas('"+luasNama+"','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaamUpd','txtLuasHMpHtaamUpd','meterhektar')\" "+socStyle);
				
				String jenisPB = String.valueOf(b.get("pemilikan"));
				String socPB = getPilihanPB().Pilihan(pbNama,jenisPB,"",socStyle,null);
				
				context.put("socNegeri", socNegeri);
				context.put("socDaerah", socDaerah);
				context.put("socMukim", socMukim);
				context.put("socBandar", socBandar);
				context.put("socJenisHakmilik", socJenisHakmilik);
				context.put("socKaTanah", socKaTanah);
				context.put("socLuas", socLuas);
				context.put("socPB", socPB);
				
			}catch (Exception e){
				throw new Exception("public void setSocValues:Ralat");
			}
					
		}
	
	public void setSocValues(Hashtable<String,String> b
		,String negeriNama,String negeriFunc
		,String daerahNama,String daerahFunc
		,String mukimNama
		,String bandarNama,String bandarFunc
		,String namaJenisHA,String jenisHAStyle
		,org.apache.velocity.VelocityContext context
		) throws Exception{
		
		//myLog.info("setSocValues:b="+b);
		String socStyle = "class=\"autoselect\" style=\"text-transform:uppercase;\" " ;
			
		try{
			String idNegeri = String.valueOf(b.get("negeri"));
			//String idDaerah = String.valueOf(b.get("daerah"));				
			//String idMukim = String.valueOf(b.get("mukim"));
			//String idBandar = String.valueOf(b.get("bandar"));
			String idJenisHA = String.valueOf(b.get("jenisha"));
				
//				String socBandar = HTML.SelectBandarByNegeri(nn,bandarNama, Utils.parseLong(bandar)
//						," id=\""+bandarNama+"\" onclick=\"CheckBandarSurat()\" "+socStyle);
//				String socBandarAdd = HTML.SelectBandarByNegeri(nn,bandarNamaAdd, Utils.parseLong(bandarAdd)
//						," id=\""+bandarNamaAdd+"\" onclick=\"CheckBandarTetap()\" "+socStyle);
//							
				String socNegeri = HTML.SelectNegeri(negeriNama, Long.valueOf(idNegeri),jenisHAStyle
						," $!readmodenegeri id=\""+negeriNama+"\" onchange=\"setSelected(2,0,0,0);"+negeriFunc+"('"+daerahNama+"')\" "+socStyle);
//				String socNegeriAdd = HTML.SelectNegeri(negeriNamaAdd, Long.valueOf(idNegeri)
//						," $!readmodenegeri id=\""+negeriNamaAdd+"\" onchange=\"setSelected(1,0,0,1);"+negeriFuncAdd+"('"+bandarAdd+"')\" "+socStyle);
//				
//				String socDaerah = HTML.SelectDaerahByNegeri(nn, daerahNama, Utils.parseLong(dd)
//						," $!readmodedaerah id=\""+daerahNama+"\" onchange=\"setSelected(1,0,0,1);"+daerahFunc+"('"+mukimNama+"');daerah_harta();check_harta()\" "+socStyle);
//				
//				String socMukim = HTML.SelectMukimByDaerah(dd, mukimNama, Utils.parseLong(idMukim)
//						," $!readmodemukim id=\""+mukimNama+"\" onfocus=\"CheckMukim()\""+socStyle);
											
				String listJenisha = getPilihanHA().Pilihan(namaJenisHA,idJenisHA, jenisHAStyle
						," $!readmode id=\""+namaJenisHA+"\" onChange=\"setSelected(2,0,0,0);getJenisHa(this.value) \""+socStyle);
		
				
				context.put("socNegeri", socNegeri);
//				context.put("socDaerah", socDaerah);
//				context.put("socMukim", socMukim);
//				context.put("socBandar", socBandar);
				context.put("ViewJenisHa", listJenisha);
				
			}catch (Exception e){
				throw new Exception("public void setSocValues:Ralat"+e.getStackTrace());
			}
					
	}
	
	public Hashtable<String,String> setSocParamValuesHA(String negeri,String daerah,String mukim,String bandar
		,String jenisHA){
		Hashtable<String,String> b = new Hashtable<String,String>();
		b.put("negeri", negeri);
		//b.put("daerah", daerah);
		//b.put("mukim", mukim);
		//b.put("bandar", bandar);
		b.put("jenisha", jenisHA);
		return b;
			
	}
	
	@Override
	public Hashtable<String,String> setSocParamValues(String negeri,String daerah,String mukim,String bandar
		,String jenisHakmilik,String kat,String luas,String pemilikan){
		Hashtable<String,String> b = new Hashtable<String,String>();
		b.put("negeri", negeri);
		b.put("daerah", daerah);
		b.put("mukim", mukim);
		b.put("bandar", bandar);
		b.put("jenishakmilik", jenisHakmilik);
		b.put("kategori", kat);
		b.put("jenisluas", luas);
		b.put("pemilikan", pemilikan);
		return b;
		
	}
	@Override
	public void getHTA(String mode
			,Hashtable hParam
			,FrmPrmhnnSek8InternalData logic_internal
			,HttpServletRequest request
			,HttpSession session
			,org.apache.velocity.VelocityContext context) throws Exception{
		//int idnegerii = 0;
		String add_new_harta = "";
		String buttonhtaam = "";
		String tambahharta = "";
		String kembaliharta = "";
		int negeri = 0;
		int daerah = 0;
		int mukim = 0;
		String readmodenegeri = "";
		String readmodedaerah = "";
		String readmodemukim = "";
		String show_simpan_add_htaam = "";
		String show_batal_add_htaam = "";
		String show_kemaskini_htaam = "";
		String show_button = "";
		String show_htaa_add_table = "";
		String show_save_update_htaam = "";
		String show_batal_update_htaam = "";
		String show_hapus_htaam = "";
		String show_kembali_htaam = "";
		String show_htaa_update_table = "";
		String listnegeri = "";
		Vector listDaerahbyNegeri = null;
		Vector listMukimbyDaerah = null;
		
		//25/08/2017
		FrmPermohonanHAData permohonanHarta = new FrmPermohonanHAData();
		logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();		
		//senaraiHTA = new Vector();
		Vector beanMaklumatPelan = null;
		Vector listnegeribydaerah = null;
		Vector listmukim = null;
		Vector v = null;
		Vector listHTAid = null;
		
		Hashtable hashHTAid = null;
		
		//PARAM
		String mati = String.valueOf(hParam.get("id_Permohonansimati"));
		String matiHeader = String.valueOf(hParam.get("id_permohonansimati_atheader"));
		String idDokumen = String.valueOf(hParam.get("idDokumen"));
		String selectedHartaTakAlih = String.valueOf(hParam.get("selectedHartaTakAlih"));
		String idhtaam = String.valueOf(hParam.get("idhtaam"));
		String upload = String.valueOf(hParam.get("upload"));
		String idPermohonan = String.valueOf(hParam.get("idPermohonan"));
		//MAKLUMAT TANAH
		txtNoHakmilikHtaam = String.valueOf(hParam.get("txtNoHakmilikHtaam"));
		 idSimati = String.valueOf(hParam.get("idSimati"));
		 txtNoPTHtaam = String.valueOf(hParam.get("txtNoPTHtaam"));
		 txtNilaiTarikhMohonHtaa = String.valueOf(hParam.get("txtNilaiTarikhMohonHtaa"));
		 txtNilaiTarikhMatiHtaam = String.valueOf(hParam.get("txtNilaiTarikhMatiHtaam"));
		 socKategoriTanahHtaam = String.valueOf(hParam.get("socKategoriTanahHtaam"));
		 socJenisHakmilikHtaam = String.valueOf(hParam.get("socJenisHakmilikHtaam"));
		 socStatusPemilikanHtaam = String.valueOf(hParam.get("socStatusPemilikanHtaam"));
		 txtLuasHMpHtaam = String.valueOf(hParam.get("txtLuasHMpHtaam"));
		 txtLuasAsalHtaam = String.valueOf(hParam.get("txtLuasAsalHtaam"));
		 txtNoPajakan = String.valueOf(hParam.get("txtNoPajakan"));
		 socJenisTanahHtaam = String.valueOf(hParam.get("socJenisTanahHtaam"));
		 txtBahagianSimati1 = String.valueOf(hParam.get("txtBahagianSimati1"));
		 txtBahagianSimati2 = String.valueOf(hParam.get("txtBahagianSimati2"));
		 txtTanggunganHtaam = String.valueOf(hParam.get("txtTanggunganHtaam"));
		 socJenisLuasHtaam = String.valueOf(hParam.get("socJenisLuasHtaam"));
		 txtCatatanHtaam = String.valueOf(hParam.get("txtCatatanHtaam"));
		 txtNoPersHtaam = String.valueOf(hParam.get("txtNoPersHtaam"));
		 FLAG_DAFTAR = String.valueOf(hParam.get("FLAG_DAFTAR"));
		 txtSekatan = String.valueOf(hParam.get("txtSekatan"));
		 txtSyaratNyata = String.valueOf(hParam.get("txtSyaratNyata"));
		//SOC
		String socNegeriHtaam = String.valueOf(hParam.get("socNegeriHtaam"));
		String socDaerahHtaam = String.valueOf(hParam.get("socDaerahHtaam"));
		String socMukimHtaam = String.valueOf(hParam.get("socMukimHtaam"));
		
		socNegeriHtaamUp = String.valueOf(hParam.get("socNegeriHtaamUp"));
		socDaerahHtaamUp = String.valueOf(hParam.get("socDaerahHtaamUp"));
		idhtaamid = String.valueOf(hParam.get("idhtaamid"));
		String idPelan = String.valueOf(hParam.get("idPelan"));
		idUser = String.valueOf(session.getAttribute("_ekptg_user_id"));
		
		String bolehsimpan = String.valueOf(hParam.get("bolehsimpan"));

		myLog.info("mode="+mode);
		
	}
	
	private void headerppk_baru(HttpSession session, String id_permohonan,
			String flag_permohonan, String id_fail, String flag_fail
			,org.apache.velocity.VelocityContext context)
			throws Exception {
		// hashtable maklumat header
		context.put("headerppk", mainheader.getHeaderData(session, id_permohonan, flag_permohonan, id_fail, flag_fail));
		Vector list_sub = null;
		list_sub = mainheader.carianFail(id_permohonan, flag_permohonan, id_fail, flag_fail);

		context.put("list_sub_header", list_sub);
		context.put("flag_jenis_vm", "vtemplate");
	
	}

	private void updateHtaam(Hashtable hParam,FrmPrmhnnSek8InternalData logic_internal) throws Exception {
		myLog.info("updateHtaam:-------Read Here----");
		Vector v = new Vector();
		Hashtable h = new Hashtable();
		setValuesTanaHash(h,hParam);
			//h.put("alamat1", String.valueOf(hParam.get("txtAlamat1Htaam1")));
			//h.put("alamat2", String.valueOf(hParam.get("txtAlamat2Htaam")));
			//h.put("alamat3", String.valueOf(hParam.get("txtAlamat3Htaam")));
			//h.put("poskod", String.valueOf(hParam.get("txtAlamatPoskodHtaam")));
// ASAL
//		if (getParam("socKategoriTanahHtaamUp") != "") {
//			h.put("kategori", Integer.parseInt(getParam("socKategoriTanahHtaamUp")));
//		} else {
//			h.put("kategori", 0);
//		}
//ASAL
//		if (getParam("socJenisHakmilikHtaamUp") != "") {
//			h.put("jenishakmilik", Integer.parseInt(getParam("socJenisHakmilikHtaamUp")));
//		} else {
//			h.put("jenishakmilik", 0);
//		}		
			//h.put("bandar", "");
			//h.put("basimati", String.valueOf(hParam.get("txtBahagianSimati1Up")));
			//h.put("bbsimati", String.valueOf(hParam.get("txtBahagianSimati2Up")));
			//h.put("jenisluas", String.valueOf(hParam.get("socJenisLuasHtaamUpd")));
			//h.put("sekatan", txtSekatan);
			//h.put("syaratNyata", txtSyaratNyata);
		if (socNegeriHtaamUp != "") {
			h.put("negeri", Integer.parseInt(socNegeriHtaamUp));
		} else {
			h.put("negeri", 0);
		}		
		if (socDaerahHtaamUp != "") {
			h.put("daerah", Integer.parseInt(socDaerahHtaamUp));
		} else {
			h.put("daerah", 0);
		}
		//PARAM
		if (String.valueOf(hParam.get("socMukimHtaamUp")) != "") {
			h.put("mukim", Integer.parseInt(String.valueOf(hParam.get("socMukimHtaamUp"))));
		} else {
			h.put("mukim", 0);
		}		
		//PARAM
		if (String.valueOf(hParam.get("txtBahagianSimati1Up")) != "") {
			h.put("basimati", String.valueOf(hParam.get("txtBahagianSimati1Up")));
		} else {
			h.put("basimati", "0");
		}
		//PARAM
		if (String.valueOf(hParam.get("txtBahagianSimati2Up")) != "") {
			h.put("bbsimati", String.valueOf(hParam.get("txtBahagianSimati2Up")));
		} else {
			h.put("bbsimati", "0");
		}		
		if (String.valueOf(hParam.get("socJenisLuasHtaamUpd")) != "") {
			h.put("jenisluas", Integer.parseInt(String.valueOf(hParam.get("socJenisLuasHtaamUpd"))));
		} else {
			h.put("jenisluas", 0);
		}
		// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
		h.put("sekatan", String.valueOf(hParam.get("txtSekatan")) == null ? "" : String.valueOf(hParam.get("txtSekatan")));
		h.put("syaratNyata", String.valueOf(hParam.get("txtSyaratNyata")) == null ? "" : String.valueOf(hParam.get("txtSyaratNyata")));
		h.put("id_Permohonansimati", idPermohonanSimati);
		
		// ADD BY SALNIZAM - TAMBAH FIELD ALAMAT
		//PARAM
		h.put("alamat_hta1", String.valueOf(hParam.get("txtAlamat1Htaam1")));
		h.put("alamat_hta2", String.valueOf(hParam.get("txtAlamat2Htaam")));
		h.put("alamat_hta3", String.valueOf(hParam.get("txtAlamat3Htaam")));
		h.put("poskod", String.valueOf(hParam.get("txtAlamatPoskodHtaam")));
		if (String.valueOf(hParam.get("txtBandarHartaHtaamX2")) != "") {
			h.put("id_bandarhta", Integer.parseInt(String.valueOf(hParam.get("txtBandarHartaHtaamX2"))));
		} else {
			h.put("id_bandarhta", 0);
		}
		h.put("id_Kemaskini", idUser);
		h.put("tarikh_Kemaskini", currentDate);
		// fungsi baru untuk penambahbaikkan...boleh kemaskini jenis HTATH
		String radioHtaamViewX_update = String.valueOf(hParam.get("radioHtaamViewX_update"));
		String no_flag = "1";
		if (radioHtaamViewX_update.equals("1")) {
			no_flag = "1";
		}
		if (radioHtaamViewX_update.equals("2")) {
			no_flag = "2";
		}
		if (radioHtaamViewX_update.equals("3")) {
			no_flag = "3";
		}
		h.put("flag", no_flag);

		String flag_tukar_jenis_hta = String.valueOf(hParam.get("flag_tukar_jenis_hta"));
		if (flag_tukar_jenis_hta.equals("ADA")) {
			h.put("jenis_Hta", "Y");
		} else if (flag_tukar_jenis_hta.equals("TIADA")) {
			h.put("jenis_Hta", "T");
			h.put("flag", no_flag);
		} else {
			h.put("jenis_Hta", "Y");
			h.put("flag", "");
		}
		//h.put("id_bandarhta", getParam("txtBandarHartaHtaamX2"));
		//h.put("noHakmilik", getParam("txtAlamat2Htaam"));
	//	h.put("noHakmilik", getParam("txtAlamat3Htaam"));
		//h.put("noHakmilik", getParam("txtAlamatBandarHtaam"));
		//h.put("noHakmilik", getParam("txtAlamatPoskodHtaam"));
	//	h.put("noHakmilik", getParam("socNegeriHtaamUp2"));		
		//h.put("nilai_Hta_mati", getParam("txtAlamat1Htaam1"));

		logic_internal.updateHtaam(h);
		
	}
	
	private void updateHtaamX(Hashtable hParam,FrmPrmhnnSek8InternalData logic_internal) throws Exception {
		myLog.info("updateHtaamX:idhtaamid="+idhtaamid+",h="+hParam);
		//Vector v = new Vector();
		Hashtable h = new Hashtable();
		//setValuesTanaHash(h,hParam);
			//h.put("noHakmilik", String.valueOf(hParam.get("txtNoHakmilikHtaamUp")));
			//h.put("alamat1", String.valueOf(hParam.get("txtAlamat1Htaam1")));
			//h.put("alamat2", String.valueOf(hParam.get("txtAlamat2Htaam")));
			//h.put("alamat3", String.valueOf(hParam.get("txtAlamat3Htaam")));
			//h.put("poskod", String.valueOf(hParam.get("txtAlamatPoskodHtaam")));
		//h.put("idSimati", idSimati);
			//h.put("idhta", String.valueOf(hParam.get("id_htaam")));
			//h.put("nopt", String.valueOf(hParam.get("txtNoPTHtaamUp")));
			//h.put("nilai_Hta_memohon", String.valueOf(hParam.get("txtNilaiTarikhMohonHt")));
			//h.put("nilai_Hta_mati", String.valueOf(hParam.get("txtNilaiTarikhMatiHtaamUpd")));
			//h.put("kategori", String.valueOf(hParam.get("socKategoriTanahHtaamUp")));
			//h.put("jenishakmilik", String.valueOf(hParam.get("socJenisHakmilikHtaamUp")));
			//h.put("pemilikan", String.valueOf(hParam.get("socStatusPemilikanHtaamUpd")));
			//h.put("daerah", "");
			//h.put("mukim", "");
			//h.put("bandar", "");
			//h.put("luashmp", String.valueOf(hParam.get("txtLuasHMpHtaamUpd")));
			//h.put("luasasal", String.valueOf(hParam.get("txtLuasAsalHtaamUpd")));
			//h.put("nopajakan", String.valueOf(hParam.get("txtNoPajakanUp")));
			//h.put("jenistanah", String.valueOf(hParam.get("socJenisTanahHtaamUpd")));
			//h.put("basimati", String.valueOf(hParam.get("txtBahagianSimati1Up")));
			//h.put("bbsimati", String.valueOf(hParam.get("txtBahagianSimati2Up")));
			//h.put("tanggungan", String.valueOf(hParam.get("txtTanggunganHtaamUp")));
			//h.put("jenisluas", String.valueOf(hParam.get("socJenisLuasHtaamUpd")));
			//h.put("catatan", String.valueOf(hParam.get("txtCatatanHt")));
			//h.put("noperserahan", String.valueOf(hParam.get("txtNoPersHtaamUp")));
		//h.put("FLAG_DAFTAR", FLAG_DAFTAR);
			//h.put("sekatan", txtSekatan);
			//h.put("syaratNyata", txtSyaratNyata);
		//PARAM
		h.put("idhta", idhtaamid);
		h.put("nopt", String.valueOf(hParam.get("txtNoPTHtaamX")));
		h.put("nilai_Hta_memohon", String.valueOf(hParam.get("txtNilaiTarikhMohonHtaaX")));
		h.put("nilai_Hta_mati", String.valueOf(hParam.get("txtNilaiTarikhMatiHtaamX")));
		if (String.valueOf(hParam.get("socKategoriTanahHtaamX")) != "") {
			h.put("kategori", Integer.parseInt(String.valueOf(hParam.get("socKategoriTanahHtaamX"))));
		} else {
			h.put("kategori", 0);
		}
//		if (String.valueOf(hParam.get("socJenisHakmilikHtaamX")) != "") {
//			h.put("jenishakmilik", Integer.parseInt(String.valueOf(hParam.get("socJenisHakmilikHtaamX"))));
//		} else {
			h.put("jenishakmilik", 0);
//		}		
		h.put("pemilikan", String.valueOf(hParam.get("socStatusPemilikanHtaamX")));	
		if (String.valueOf(hParam.get("socNegeriHtaamX")) != "") {
			h.put("negeri", Integer.parseInt(String.valueOf(hParam.get("socNegeriHtaamX"))));
		} else {
			h.put("negeri", 0);
		}
		if (String.valueOf(hParam.get("socDaerahHtaamX")) != "") {
			h.put("daerah", Integer.parseInt(String.valueOf(hParam.get("socDaerahHtaamX"))));
		} else {
			h.put("daerah", 0);
		}
		if (String.valueOf(hParam.get("socMukimHtaamX")) != "") {
			h.put("mukim", Integer.parseInt(String.valueOf(hParam.get("socMukimHtaamX"))));
		} else {
			h.put("mukim", 0);
		}
		h.put("luashmp", String.valueOf(hParam.get("txtLuasHMpHtaamX")));
		h.put("luasasal", String.valueOf(hParam.get("txtLuasAsalHtaamX")));
		h.put("nopajakan", String.valueOf(hParam.get("txtNoPajakanX")));	
		h.put("jenistanah", String.valueOf(hParam.get("socJenisTanahHtaamX")));
		if (String.valueOf(hParam.get("txtBahagianSimati1X"))!= "") {
			h.put("basimati", String.valueOf(hParam.get("txtBahagianSimati1X")));
		} else {
			h.put("basimati", "0");
		}
		if (String.valueOf(hParam.get("txtBahagianSimati2X")) != "") {
			h.put("bbsimati", String.valueOf(hParam.get("txtBahagianSimati2X")));
		} else {
			h.put("bbsimati", "0");
		}
		h.put("tanggungan", String.valueOf(hParam.get("txtTanggunganHtaamX")));
		if (String.valueOf(hParam.get("socJenisLuasHtaamX")) != "") {
			h.put("jenisluas", Integer.parseInt(String.valueOf(hParam.get("socJenisLuasHtaamX"))));
		} else {
			h.put("jenisluas", 0);
		}
		h.put("catatan", String.valueOf(hParam.get("txtCatatanHtaamX")));
		h.put("noperserahan", String.valueOf(hParam.get("txtNoPersHtaamX")));
		h.put("FLAG_DAFTAR", FLAG_DAFTAR);		
		
		h.put("id_Permohonansimati", idPermohonanSimati);
		
		h.put("namapemaju", String.valueOf(hParam.get("txtNamaPemajuHtaamX")));
		h.put("alamatpemaju1", String.valueOf(hParam.get("txtAlamatPemaju1HtaamX")));
		h.put("alamatpemaju2", String.valueOf(hParam.get("txtAlamatPemaju2HtaamX")));
		h.put("alamatpemaju3", String.valueOf(hParam.get("txtAlamatPemaju3HtaamX")));
		h.put("poskodpemaju", String.valueOf(hParam.get("txtPoskodPemaju1HtaamX")));
		if (String.valueOf(hParam.get("socNegeriPemajuHtaamX")).equals("")) {
			h.put("negeripemaju", 0);
		} else {
			h.put("negeripemaju", Integer.parseInt(String.valueOf(hParam.get("socNegeriPemajuHtaamX"))));
		}
		if (String.valueOf(hParam.get("txtBandarPemaju1HtaamX")).equals("")) {
			h.put("bandarpemaju", 0);
		} else {
			h.put("bandarpemaju", Integer.parseInt(String.valueOf(hParam.get("txtBandarPemaju1HtaamX"))));
		}
	
		h.put("alamathta1", String.valueOf(hParam.get("txtAlamatHarta1HtaamX")));
		h.put("alamathta2", String.valueOf(hParam.get("txtAlamatHarta2HtaamX")));
		h.put("alamathta3", String.valueOf(hParam.get("txtAlamatHarta3HtaamX")));
		h.put("poskodhta", String.valueOf(hParam.get("txtPoskodHartaHtaamX")));
		h.put("bandarhta"
				,String.valueOf(hParam.get("txtBandarHartaHtaamX")).equals("")?"0":String.valueOf(hParam.get("txtBandarHartaHtaamX")));
		h.put("noperjanjian", String.valueOf(hParam.get("txtNoPerjanjianHtaamX")));
		h.put("tarikhperjanjian", String.valueOf(hParam.get("txtTarikhPerjanjianHtaamX")));
		h.put("namarancangan", String.valueOf(hParam.get("txtNamaRancanganHtaamX")));
		h.put("noroh", String.valueOf(hParam.get("txtNoRohHtaamX")));

		h.put("nolot", String.valueOf(hParam.get("txtLotIdHtaamX")));
		h.put("nocagaran", String.valueOf(hParam.get("txtNoCagaranX")));
		h.put("jeniskepentingan", String.valueOf(hParam.get("txtJenisKepentinganX")));
		h.put("id_Kemaskini", idUser);
		h.put("tarikh_Kemaskini", currentDate);

		// /fungsi baru untuk penambahbaikkan...boleh kemaskini jenis HTATH
		String radioHtaamViewX_update = String.valueOf(hParam.get("radioHtaamViewX_update"));
		String no_flag = "1";
		if (radioHtaamViewX_update.equals("1")) {
			no_flag = "1";
		}
		if (radioHtaamViewX_update.equals("2")) {
			no_flag = "2";
		}
		if (radioHtaamViewX_update.equals("3")) {
			no_flag = "3";
		}
		h.put("flag", no_flag);

		String flag_tukar_jenis_hta = String.valueOf(hParam.get("flag_tukar_jenis_hta"));
		if (flag_tukar_jenis_hta.equals("ADA")) {
			h.put("jenis_Hta", "Y");

		} else if (flag_tukar_jenis_hta.equals("TIADA")) {
			h.put("jenis_Hta", "T");
			h.put("flag", no_flag);
		} else {
			h.put("jenis_Hta", "T");
		}
		logic_internal.updateHtaamX(h);

	}
	private void addHtaam(HttpSession session
		,Hashtable hParam
		,FrmPrmhnnSek8InternalData logic_internal
		) throws Exception {
		myLog.info("addHtaam:-------Read Here8----");
		Hashtable h = new Hashtable();

		h.put("FLAG_DAFTAR", hParam.get("FLAG_DAFTAR"));
		h.put("noHakmilik", hParam.get("txtNoHakmilikHtaam"));
		// ADD BY SALNIZAM - TAMBAH FIELD ALAMAT
		h.put("alamat_hta1", hParam.get("txtAlamat1Htaam1"));
		h.put("alamat_hta2", hParam.get("txtAlamat2Htaam"));
		h.put("alamat_hta3", hParam.get("txtAlamat3Htaam"));
		h.put("poskod", hParam.get("txtAlamatPoskodHtaam"));
		//if (getParam("txtBandarHartaHtaamX2") != "") {
			h.put("id_bandarhta", hParam.get("txtBandarHartaHtaamX2"));
		//} else {
		//	h.put("id_bandarhta", "99999");
		//}
		
		//h.put("bandar_hta", getParam("txtBandarHartaHtaamX2"));
		h.put("idSimati", hParam.get("idSimati"));

		//IL start
		//h.put("id_Permohonansimati", getParam("id_Permohonansimati"));

		String mati = String.valueOf(hParam.get("id_permohonansimati_atheader"));
		if (mati.length() == 0) {
			mati =  String.valueOf(hParam.get("id_Permohonansimati"));
		}

		h.put("id_Permohonansimati", mati);
		//IL end

		// int mati = Integer.parseInt(getParam("id_Permohonansimati"));
		h.put("nopt", hParam.get("txtNoPTHtaam"));
		
		/*
		 * if (getParam("txtNilaiTarikhMohonHtaa") != "") {
		 * h.put("nilai_Hta_memohon", Double
		 * .parseDouble(getParam("txtNilaiTarikhMohonHtaa"))); } else {
		 * h.put("nilai_Hta_memohon", 0.0); }
		 */

		h.put("nilai_Hta_memohon", hParam.get("txtNilaiTarikhMohonHtaa"));
		/*
		 * if (getParam("txtNilaiTarikhMatiHtaam") != "") {
		 * h.put("nilai_Hta_mati", Double
		 * .parseDouble(getParam("txtNilaiTarikhMatiHtaam"))); } else {
		 * h.put("nilai_Hta_mati", 0.0); }
		 */

		h.put("nilai_Hta_mati", hParam.get("txtNilaiTarikhMatiHtaam"));

		if (hParam.get("socKategoriTanahHtaam") != "") {
			h.put("kategori", Integer
					.parseInt(String.valueOf(hParam.get("socKategoriTanahHtaam"))));
		} else {
			h.put("kategori", 0);
		}

		if (hParam.get("socJenisHakmilikHtaam") != "") {
			h.put("jenishakmilik", Integer
					.parseInt(String.valueOf(hParam.get("socJenisHakmilikHtaam"))));
		} else {
			h.put("jenishakmilik", 0);
		}

		h.put("pemilikan", hParam.get("socStatusPemilikanHtaam"));

		if (hParam.get("socNegeriHtaam") != "") {
			h.put("negeri", Integer.parseInt(String.valueOf(hParam.get("socNegeriHtaam"))));
		} else {
			h.put("negeri", 0);
		}
	
		if (hParam.get("socDaerahHtaam") != "") {
			h.put("daerah", Integer.parseInt(String.valueOf(hParam.get("socDaerahHtaam"))));
		} else {
			h.put("daerah", 0);
		}

		if (hParam.get("socMukimHtaam") != "") {
			h.put("mukim", Integer.parseInt(String.valueOf(hParam.get("socMukimHtaam"))));
		} else {
			h.put("mukim", 0);
		}

		h.put("luashmp", hParam.get("txtLuasHMpHtaam"));
		h.put("luasasal", hParam.get("txtLuasAsalHtaam"));
		h.put("nopajakan", hParam.get("txtNoPajakan"));
		h.put("jenistanah", hParam.get("socJenisTanahHtaam"));

		if (hParam.get("txtBahagianSimati1") != "") {
			h.put("basimati", hParam.get("txtBahagianSimati1"));
		} else {
			h.put("basimati", "0");
		}

		if (hParam.get("txtBahagianSimati2") != "") {
			h.put("bbsimati", hParam.get("txtBahagianSimati2"));
		} else {
			h.put("bbsimati", "0");
		}

		if (hParam.get("socJenisLuasHtaam") != "") {
			h.put("jenisluas", Integer.parseInt(String.valueOf(hParam.get("socJenisLuasHtaam"))));
		} else {
			h.put("jenisluas", 0);
		}

		h.put("tanggungan", hParam.get("txtTanggunganHtaam"));

		h.put("catatan", hParam.get("txtCatatanHtaam"));
		h.put("noperserahan", hParam.get("txtNoPersHtaam"));

		h.put("id_Masuk", session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);

		// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
		h.put("sekatan", hParam.get("txtSekatan") == null ? ""
				: hParam.get("txtSekatan"));
		h.put("syaratNyata", hParam.get("txtSyaratNyata") == null ? ""
				: hParam.get("txtSyaratNyata"));

		//logic_internal.addHtaam(h);	//IL comment
		String idHta = logic_internal.addHtaamUpload(h);//IL
		session.setAttribute("idHtaSession", idHta);//IL
		System.out.println("-------Read Here8j ends----");
		
	}
	private void setContextTanah(org.apache.velocity.VelocityContext context){
		context.put("noHakmilik", txtNoHakmilikHtaam);
		context.put("idSimati",idSimati);
		context.put("nopt",txtNoPTHtaam);
		context.put("nilai_Hta_memohon",txtNilaiTarikhMohonHtaa);
		context.put("nilai_Hta_mati",txtNilaiTarikhMatiHtaam);
		context.put("kategori",socKategoriTanahHtaam);
		context.put("jenishakmilik",socJenisHakmilikHtaam);
		context.put("pemilikan",socStatusPemilikanHtaam);
		context.put("luashmp",txtLuasHMpHtaam);
		context.put("luasasal",txtLuasAsalHtaam);
		context.put("nopajakan",txtNoPajakan);
		context.put("jenistanah",socJenisTanahHtaam);
		context.put("basimati",txtBahagianSimati1);
		context.put("bbsimati",txtBahagianSimati2);
		context.put("tanggungan",txtTanggunganHtaam);
		context.put("jenisluas",socJenisLuasHtaam);
		context.put("catatan",txtCatatanHtaam);
		context.put("noperserahan",txtNoPersHtaam);
		context.put("FLAG_DAFTAR", FLAG_DAFTAR);
		context.put("txtSekatan", txtSekatan);
		context.put("txtSyaratNyata", txtSyaratNyata);
				
	}

	private void setContextTanah(org.apache.velocity.VelocityContext context
		,Hashtable getLot){
		context.put("noHakmilik", getLot.get("NO_HAKMILIK"));
		context.put("idSimati", getLot.get("ID_SIMATI"));
		context.put("nilai_Hta_memohon", getLot.get("NILAI_HTA_TARIKHMOHON"));
		context.put("nilai_Hta_mati", getLot.get("NILAI_HTA_TARIKHMATI"));
		context.put("kategori", getLot.get("ID_KATEGORI"));
		context.put("jenishakmilik", getLot.get("ID_JENISHM"));
		context.put("pemilikan", getLot.get("STATUS_PEMILIKAN"));
		context.put("luashmp", getLot.get("LUAS_HMP"));
		context.put("luasasal", getLot.get("LUAS"));
		context.put("nopajakan", getLot.get("NO_PAJAKAN"));
		context.put("jenistanah", getLot.get("JENIS_TNH"));
		context.put("basimati", getLot.get("BA_SIMATI"));
		context.put("bbsimati", getLot.get("BB_SIMATI"));
		context.put("tanggungan", getLot.get("TANGGUNGAN"));
		context.put("jenisluas", getLot.get("ID_LUAS"));
		context.put("catatan", getLot.get("CATATAN"));
		context.put("noperserahan", getLot.get("NO_PERSERAHAN"));
		context.put("FLAG_DAFTAR", getLot.get("FLAG_DAFTAR"));
		
	}
		
	private void setValuesTanaHash(Hashtable h,Hashtable hParam){
	//PARAM
		h.put("noHakmilik", String.valueOf(hParam.get("txtNoHakmilikHtaamUp")));
		h.put("alamat1", String.valueOf(hParam.get("txtAlamat1Htaam1")));
		h.put("alamat2", String.valueOf(hParam.get("txtAlamat2Htaam")));
		h.put("alamat3", String.valueOf(hParam.get("txtAlamat3Htaam")));
		h.put("poskod", String.valueOf(hParam.get("txtAlamatPoskodHtaam")));
		h.put("idSimati", idSimati);
		h.put("idhta", String.valueOf(hParam.get("id_htaam")));
		h.put("nopt", String.valueOf(hParam.get("txtNoPTHtaamUp")));
		h.put("nilai_Hta_memohon", String.valueOf(hParam.get("txtNilaiTarikhMohonHt")));
		h.put("nilai_Hta_mati", String.valueOf(hParam.get("txtNilaiTarikhMatiHtaamUpd")));
		h.put("kategori", String.valueOf(hParam.get("socKategoriTanahHtaamUp")));
		h.put("jenishakmilik", String.valueOf(hParam.get("socJenisHakmilikHtaamUp")));
		h.put("pemilikan", String.valueOf(hParam.get("socStatusPemilikanHtaamUpd")));
		h.put("daerah", "");
		h.put("mukim", "");
		h.put("bandar", "");
		h.put("luashmp", String.valueOf(hParam.get("txtLuasHMpHtaamUpd")));
		h.put("luasasal", String.valueOf(hParam.get("txtLuasAsalHtaamUpd")));
		h.put("nopajakan", String.valueOf(hParam.get("txtNoPajakanUp")));
		h.put("jenistanah", String.valueOf(hParam.get("socJenisTanahHtaamUpd")));
		h.put("basimati", String.valueOf(hParam.get("txtBahagianSimati1Up")));
		h.put("bbsimati", String.valueOf(hParam.get("txtBahagianSimati2Up")));
		h.put("tanggungan", String.valueOf(hParam.get("txtTanggunganHtaamUp")));
		h.put("jenisluas", String.valueOf(hParam.get("socJenisLuasHtaamUpd")));
		h.put("catatan", String.valueOf(hParam.get("txtCatatanHt")));
		h.put("noperserahan", String.valueOf(hParam.get("txtNoPersHtaamUp")));
		h.put("FLAG_DAFTAR", FLAG_DAFTAR);
		// ADD BY PEJE TAMBAH FIELD SEKATAN & SYARAT NYATA
		h.put("sekatan", txtSekatan);
		h.put("syaratNyata", txtSyaratNyata);
				
	}
	
	private Hashtable setValuesTanahKemaskini(Hashtable data){
		Hashtable h = new Hashtable();
		h.put("idhta", String.valueOf(data.get("idhtaamid"))); 
		h.put("idSimati", String.valueOf(data.get("idSimatiX")));
		h.put("nopt", String.valueOf(data.get("txtNoPTHtaamX")));
		h.put("nilai_Hta_memohon",String.valueOf(data.get("txtNilaiTarikhMohonHtaaX")));
		h.put("nilai_Hta_mati", String.valueOf(data.get("txtNilaiTarikhMatiHtaamX")));
		h.put("kategori", String.valueOf(data.get("socKategoriTanahHtaamX")));
		h.put("luashmp", String.valueOf(data.get("txtLuasHMpHtaamX")));
		h.put("luasasal", String.valueOf(data.get("txtLuasAsalHtaamX")));
		h.put("nopajakan", String.valueOf(data.get("txtNoPajakanX")));
		h.put("jenistanah", String.valueOf(data.get("socJenisTanahHtaamX")));
		h.put("basimati", String.valueOf(data.get("txtBahagianSimati1X")));
		h.put("bbsimati", String.valueOf(data.get("txtBahagianSimati2X")));
		h.put("tanggungan", String.valueOf(data.get("txtTanggunganHtaamX")));
		h.put("jenisluas", String.valueOf(data.get("socJenisLuasHtaamX")));
		h.put("catatan", String.valueOf(data.get("txtCatatanHtaamX")));
		h.put("namapemaju", String.valueOf(data.get("txtNamaPemajuHtaamX")));
		h.put("alamatpemaju1", String.valueOf(data.get("txtAlamatPemaju1HtaamX")));
		h.put("alamatpemaju2", String.valueOf(data.get("txtAlamatPemaju2HtaamX")));
		h.put("alamatpemaju3", String.valueOf(data.get("txtAlamatPemaju3HtaamX")));
		h.put("poskodpemaju", String.valueOf(data.get("txtPoskodPemaju1HtaamX")));
		h.put("bandarpemaju", String.valueOf(data.get("txtBandarPemaju1HtaamX")));
		h.put("negeripemaju", String.valueOf(data.get("socNegeriPemajuHtaamX")));
		h.put("alamathta1", String.valueOf(data.get("txtAlamatHarta1HtaamX")));
		h.put("alamathta2", String.valueOf(data.get("txtAlamatHarta2HtaamX")));
		h.put("alamathta3", String.valueOf(data.get("txtAlamatHarta3HtaamX")));
		h.put("poskodhta", String.valueOf(data.get("txtPoskodHartaHtaamX")));
		h.put("noperjanjian", String.valueOf(data.get("txtNoPerjanjianHtaamX")));
		h.put("tarikhperjanjian",String.valueOf(data.get("txtTarikhPerjanjianHtaamX")));
		h.put("namarancangan", String.valueOf(data.get("txtNamaRancanganHtaamX")));
		h.put("noroh", String.valueOf(data.get("txtNoRohHtaamX")));
		h.put("nolot", String.valueOf(data.get("txtLotIdHtaamX")));
		h.put("flag", String.valueOf(data.get("flag")));
		h.put("nocagaran", String.valueOf(data.get("txtNoCagaranX")));
		h.put("pemilikan", String.valueOf(data.get("socStatusPemilikanHtaamX")));
		h.put("jeniskepentingan", String.valueOf(data.get("txtJenisKepentinganX")));
		h.put("FLAG_DAFTAR", String.valueOf(data.get("FLAG_DAFTAR")));
		return h; 
			
	}

	private Hashtable setValuesTanaHashX(Hashtable hi){
		Hashtable h = new Hashtable();
		h.put("noHakmilik", hi.get("txtNoHakmilikHtaamUp"));
		h.put("alamat1", hi.get("txtAlamat1Htaam1"));
		h.put("alamat2", hi.get("txtAlamat2Htaam"));
		h.put("alamat3", hi.get("txtAlamat3Htaam"));
		h.put("poskod", hi.get("txtAlamatPoskodHtaam"));
		h.put("idSimati", hi.get("idSimati"));
		h.put("idhta", hi.get("id_htaam"));
		h.put("nopt", hi.get("txtNoPTHtaamUp"));
		h.put("nilai_Hta_memohon", hi.get("txtNilaiTarikhMohonHt"));
		h.put("nilai_Hta_mati", hi.get("txtNilaiTarikhMatiHtaamUpd"));
		//h.put("alamatHta1", getParam("txtAlamat1Htaam1"));				
		h.put("kategori", hi.get("socKategoriTanahHtaamUp"));
		h.put("jenishakmilik", hi.get("socJenisHakmilikHtaamUp"));
		h.put("pemilikan", hi.get("socStatusPemilikanHtaamUpd"));
		h.put("daerah", "");
		h.put("mukim", "");
		h.put("bandar", "");
		h.put("luashmp", hi.get("txtLuasHMpHtaamUpd"));
		h.put("luasasal", hi.get("txtLuasAsalHtaamUpd"));
		h.put("nopajakan", hi.get("txtNoPajakanUp"));
		h.put("jenistanah", hi.get("socJenisTanahHtaamUpd"));
		h.put("basimati", hi.get("txtBahagianSimati1Up"));
		h.put("bbsimati", hi.get("txtBahagianSimati2Up"));
		h.put("tanggungan", hi.get("txtTanggunganHtaamUp"));
		h.put("jenisluas", hi.get("socJenisLuasHtaamUpd"));
		h.put("catatan", hi.get("txtCatatanHt"));
		h.put("noperserahan", hi.get("txtNoPersHtaamUp"));
		h.put("FLAG_DAFTAR", hi.get("FLAG_DAFTAR"));
		// ADD BY PEJE TAMBAH FIELD SEKATAN & SYARAT NYATA
		h.put("sekatan", hi.get("txtSekatan"));
		h.put("syaratNyata", hi.get("txtSyaratNyata"));
		return h;
		
	}
	private Hashtable<String,String> setSocParamValues_(String negeri,String negeriAdd
			,String daerah,String daerahAdd
			,String mukim
			,String bandar,String bandarAdd
			,String kat,String luas,String pemilikan){
		Hashtable<String,String> b = new Hashtable<String,String>();
		b.put("negeri", negeri.equals("")?"0":negeri);
		b.put("negeriAdd", negeriAdd.equals("")?"0":negeriAdd);
		b.put("daerah", daerah.equals("")?"0":daerah);
		b.put("daerahAdd", daerahAdd.equals("")?"0":daerahAdd);
		b.put("mukim", mukim.equals("")?"0":mukim);
		b.put("bandar", bandar.equals("")?"0":bandar);
		b.put("bandarAdd", bandarAdd.equals("")?"0":bandarAdd);
		b.put("kategori", kat.equals("")?"0":kat);
		b.put("jenisluas", luas.equals("")?"0":luas);
		b.put("pemilikan", pemilikan.equals("")?"0":pemilikan);
		return b;
		
	}
	
	private IUtilHTMLPilihan getPilihanHA(){
		if(jenisHA==null){
			jenisHA = new UtilHTMLPilihanJenisHA();
		}
		return jenisHA;
				
	}	
	
	private IUtilHTMLPilihanExt getPilihanPB(){
		if(jenisPB==null){
			jenisPB = new UtilHTMLPilihanJenisPBPPK();
		}
		return jenisPB;
				
	}

		
}
