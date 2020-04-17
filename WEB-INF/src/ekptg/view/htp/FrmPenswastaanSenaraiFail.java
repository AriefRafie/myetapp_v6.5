package ekptg.view.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.File;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.FrmSenaraiFailPajakanKecilData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.frmpenswastaanSenaraiFailData;

public class FrmPenswastaanSenaraiFail extends AjaxBasedModule {
	
	static Logger myLogger = Logger.getLogger(FrmPenswastaanSenaraiFail.class);

	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession(); // bila perlu untuk dapatkan maklumat daripada session(tiap x user login)
		String vm = "app/htp/frmpenswastaanSenaraiFail.jsp";
		String command = getParam("command");
		String action = getParam("action"); // ACTION UTK SETUP PAGING SHJ
		Hashtable permohonan = null;
		//myLogger.info("button 123 :: " + command);

		String usid = "";

		this.context.put("NoFail", "");
		this.context.put("NamaFail", "");

		String id_negeri = getParam("socNegeri");
		String id_kementerian = getParam("sockementerian");
		String id_agensi = getParam("socAgensi");

		String socKementerian = "";
		String socAgensi = "";
		// Vector list = null;

		myLogger.info("frmpenswastaanSenaraiFail submit:" + command +"-"+action);
		/* Senarai fail from DB */
		Vector list = FrmUtilData.getSenaraiFailByUrusan("4", "", "", 0L);
		String selectedTabUpper = getParam("selectedTabUpper").toString();
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		myLogger.info("PEJE123 :  " + selectedTabUpper);
		//PAGE PENDAFTARAN PERMOHONAN - UNTUK TAMBAH SENARAI PERMOHONAN
		if ("tambah_senarai_fail".equals(command)) {
			myLogger.info("TEST>>>>> ");

			vm = "app/htp/frmPenswastaanPendaftaran_permohonan.jsp";
			socKementerian = HTML.SelectKementerian("sockementerian",(id_kementerian == "") ? null : Long.parseLong(id_kementerian), null,"onChange=\"doChangeKementerian()\" ");
			socAgensi = HTML.SelectAgensiByKementerian("socAgensi",id_kementerian, Long.parseLong("1"), "");

			this.context.put("selectNegeri", HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri", Long.parseLong("" + id_negeri), "", ""));
			this.context.put("selectKementerian", socKementerian);

			this.context.put("selectAgensi", socAgensi);
			this.context.put("selectStatusTanah", HTML.SelectJenisTanah("socJenisTanah"));
			this.context.put("selectTarafKeselamatan", HTML.SelectTarafKeselamatan("socJenisFail"));
			
		}else if ("simpanPendaftaranPermohonan".equals(command)) {

			myLogger.debug("simpanPendaftaranPermohonan:id negeri=" + id_negeri);

			String no_fail_seksyen = getParam("txtnofailseky");
			String no_fail_KJP = "";
			String tarikh_surat_KJP = "";
			String no_fail_lain = "";
			String tarikh_buka_fail = "";
			String socNegeri = "";

			socNegeri = getParam("socNegeri");

			myLogger.debug("id negeri=" + socNegeri);

			// simpanPendaftaranPermohonan(no_fail_seksyen);
			long longIdFail = ekptg.helpers.DB.getNextID("TBLPFDFAIL_SEQ");
			simpanFail(session, longIdFail); // function simpanfail
			// int idPermohonan=46;
			String idPermohonan = simpanPermohonan(session, "" + longIdFail, 0);
			this.context.put("idPermohonan", idPermohonan);
			simpanStatus(session, Long.parseLong(idPermohonan), longIdFail);

			this.context.put("kemaskinibutton", "yes");
			myLogger.info("submit X" + submit);
			
		}else if ("backListSenarai".equals(command)) {
			vm = "app/htp/frmpenswastaanSenaraiFail.jsp";
			

// PAGE SENARAI FAIL -TUJUAN CARIAN FAIL
		}else if ("cariFail".equals(command)) {
			String nofail = getParam("txtNoFail");
			String namafail = getParam("txtNamaFail");
			int idNegeri = getParamAsInteger("socNegeri");
			String pilihanIdNegeri = getParam("socNegeri");
			String socNegeri = "";
			context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(pilihanIdNegeri), null));

			list = FrmUtilData.getSenaraiFailByUrusan("4", namafail, nofail, Long.parseLong("" + idNegeri));
		
//PAGE MAKLUMAT SYARIKAT -TUJUAN SAVE, UPDATE N VIEW SYARIKAT			
		}else if ("viewSyarikat".equals(command)) { //METHOD FOR SYARIKAT
			vm = "app/htp/frmpenswastaanPage.jsp";

			String id_permohonan = getParam("id_permohonan1");
			this.context.put("id_permohonan", id_permohonan);
			//koding for header
			permohonan = (Hashtable) frmpenswastaanSenaraiFailData.getPermohonanInfo(id_permohonan);
			label(permohonan);
			Vector maklumatSyarikat = new Vector();
			maklumatSyarikat.clear();
			maklumatSyarikat = frmpenswastaanSenaraiFailData.getMaklumatSyarikat(id_permohonan);
			this.context.put("MaklumatSyarikat", maklumatSyarikat);

			int idNegeri = getParamAsInteger("socNegeri");
			this.context.put("socNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong("" + idNegeri), ""));

			// get senarai pengarah
			String idPemaju = "";
			if (maklumatSyarikat.size() != 0) {
				Hashtable hash = (Hashtable) frmpenswastaanSenaraiFailData.getMaklumatSyarikat(id_permohonan).get(0);
				idPemaju = (String) hash.get("idPemaju");
			}
			Vector senaraiPengarah = new Vector();
			senaraiPengarah.clear();
			senaraiPengarah = frmpenswastaanSenaraiFailData.getSenaraiPengarah(idPemaju);
			this.context.put("SenaraiPengarah", senaraiPengarah);

			// set default value to empty-untuk flag tambah pengarah
			this.context.put("flagPopup", "");
			this.context.put("modePopup", "");

			if ("papar".equals(action)) {
				if (maklumatSyarikat.size() != 0) {
					// view maklumat syarikat
					this.context.put("mode", "view");
					this.context.put("readonly", "readonly");
					this.context.put("inputTextClass", "disabled");
					this.context.put("socNegeri1", HTML.SelectNegeri("socNegeri", Long.parseLong("" + idNegeri),"class=disabled"));
				} else {
					// new (insert maklumat baru)
					this.context.put("mode", "new");
					this.context.put("readonly", "");
					this.context.put("inputTextClass", "");
				}

			} else if ("kemaskini".equals(action)) {
				// update field maklumat syarikat
				this.context.put("mode", "update");
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");

			} else if ("simpan".equals(action)) {
				// simpan field
				simpanpemaju(session);

				maklumatSyarikat.clear();
				maklumatSyarikat = frmpenswastaanSenaraiFailData.getMaklumatSyarikat(id_permohonan);
				this.context.put("MaklumatSyarikat", maklumatSyarikat);

				// BACK TO VIEW AFTER SAVE
				this.context.put("mode", "view");
				this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");

			} else if ("simpanKemaskini".equals(action)) {
				// kemaskini field
				simpanKemaskiniPemaju(session);

				maklumatSyarikat.clear();
				maklumatSyarikat = frmpenswastaanSenaraiFailData.getMaklumatSyarikat(id_permohonan);
				this.context.put("MaklumatSyarikat", maklumatSyarikat);

				// BACK TO VIEW AFTER SAVE
				this.context.put("mode", "view");
				this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");

			} else if ("backToList".equals(action)) {
				vm = "app/htp/frmpenswastaanSenaraiFail.jsp";
			
//PAGE MAKLUMAT PENGARAH - TUJUAN SAVE, UPDATE N VIEW PENGARAH				
			} else if ("tambahPengarah".equals(action)) {
				this.context.put("flagPopup", "openPengarah");
				// mode untuk tambah pengarah
				this.context.put("modePopup", "new");

			} else if ("simpanPengarah".equals(action)) {
				simpanPengarah(session);
				senaraiPengarah.clear();
				senaraiPengarah = frmpenswastaanSenaraiFailData.getSenaraiPengarah(idPemaju);
				this.context.put("SenaraiPengarah", senaraiPengarah);
				
				//from list pengarah to next screen
			} else if ("paparPengarah".equals(action)) {
				String id_pengarah = getParam("id_pengarah");
				this.context.put("id_pengarah", id_pengarah);
				Vector maklumatPengarah = new Vector ();
				maklumatPengarah.clear();
				maklumatPengarah = frmpenswastaanSenaraiFailData.getMaklumatPengarah(id_pengarah); 
				this.context.put("MaklumatPengarah", maklumatPengarah);
				
				//koding untuk buka popup
				this.context.put("flagPopup", "openPengarah");
				this.context.put("modePopup", "view");
				this.context.put("readonlyPopup", "readonly");
				this.context.put("inputTextClassPopup", "disabled");
				
			} else if ("viewPengarah".equals(action)) {
				this.context.put("flagPopup", "openPengarah");
				this.context.put("modePopup", "view");
				
				this.context.put("modePopup", "update");
				this.context.put("readonlyPopup", "");
				this.context.put("inputTextClassPopup", "");
				
			
			} else if ("simpanKemaskiniPengarah".equals(action)) {
				// kemaskini field
				simpanKemaskiniPengarah(session);

				String id_pengarah = getParam("id_pengarah");
				this.context.put("id_pengarah", id_pengarah);
				Vector maklumatPengarah = new Vector ();
				maklumatPengarah.clear();
				maklumatPengarah = frmpenswastaanSenaraiFailData.getMaklumatPengarah(id_pengarah);
				this.context.put("MaklumatPengarah", maklumatPengarah);
				this.context.put("flagPopup", "openPengarah");
				this.context.put("modePopup", "view");

				 //BACK TO VIEW AFTER SAVE
				this.context.put("modePopup", "view");
				this.context.put("readonlyPopup", "readonlyPopup");
				this.context.put("readonlyPopup", "disabled");
				
				//return vm = "app/htp/frmpenswastaanMaklumatSyarikat.jsp";
			}
			
		//}else if ("kembaliListPengarah".equals(action)) {
	//		vm = "app/htp/frmpenswastaanMaklumatSyarikat.jsp";
			

/*PAGE MAKLUMAT MESYUARAT - TUJUAN SAVE, UPDATE N VIEW PENGARAH*/
		}else if ("viewMesyuarat".equals(command)) { //METHOD FOR MESYUARAT
			vm = "app/htp/frmpenswastaanMesyuarat.jsp";
			String id_permohonan = getParam("id_permohonan1");
			this.context.put("id_permohonan", id_permohonan);
			
			//koding for header
			permohonan = (Hashtable) frmpenswastaanSenaraiFailData.getPermohonanInfo(id_permohonan);
			label(permohonan);
			
		} else if ("backPageMaklumatSykt".equals(command)) { //METHOD FOR MESYUARAT
			vm = "app/htp/frmpenswastaanMaklumatSyarikat.jsp";
			String id_permohonan = getParam("id_permohonan1");
			this.context.put("id_permohonan", id_permohonan);
		
		} else if ("viewPindahMilik".equals(command)) { //METHOD FOR PINDAH MILIK
			
			vm = "app/htp/frmpenswastaanPindahmilik.jsp";
			String id_permohonan = getParam("id_permohonan1");
			this.context.put("id_permohonan", id_permohonan);
			
			//koding for header
			permohonan = (Hashtable) frmpenswastaanSenaraiFailData.getPermohonanInfo(id_permohonan);
			label(permohonan);
			
			
		}   else {
			String nofail = getParam("txtNoFail");
			String namafail = getParam("txtNamaFail");
			String kementerian = getParam("socKementerian");
			int idNegeri = getParamAsInteger("socNegeri");

			myLogger.info("else nofail :: " + nofail);
			//myLogger.info("namafail :: " + namafail);
			//myLogger.info("sockementerian :: " + id_kementerian);
			//myLogger.info("socNegeri :: " + idNegeri);

			// String socNegeri = getParam("");
			this.context.put("NoFail", nofail);
			this.context.put("NamaFail", namafail);
			this.context.put("socKementerian", kementerian);
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
					Long.parseLong("" + idNegeri), ""));

			// Vector list = cariFail(nofail,namafail,idNegeri);
			// Vector list = FrmUtilData.getSenaraiFailByUrusan("4", "", "",
			// 0L);
			// myLogger.info("list :: "+list.size());
			// this.context.put("senaraiFail",list);
			// setupPage(session,action,list);
		}

		this.context.put("senaraiFail", list);
		setupPage(session, action, list);
		
		this.context.put("selectedTabUpper", selectedTabUpper);		
		return vm;

	}

	private void simpanKemaskiniPengarah(HttpSession session) throws Exception {
		// simpan kemaskini Pengarah
		myLogger.info("simpan kemaskini pengarah ");
		Hashtable h = new Hashtable();

		String idPengarah= "";
		String id_pengarah = getParam("id_pengarah");
		String userId = session.getAttribute("_ekptg_user_id").toString();

		h.put("id_pengarah", id_pengarah);
		h.put("id_pemaju", getParam("id_pemaju"));
		h.put("namaPgrh", getParam("txtnamaPgrh"));
		h.put("icPgrh", getParam("txticPgrh"));
		h.put("userId",userId);
		
		frmpenswastaanSenaraiFailData.updateSimpanPengarah(h);

	}

	private void simpanPengarah(HttpSession session) throws Exception {
		// TODO maklumat simpan info pemaju
		myLogger.info("simpan pemaju test>>>>> ");
		Hashtable h = new Hashtable();

		String idPemaju = "";
		idPemaju = getParam("idPemaju");

		h.put("idPemaju", idPemaju);
		h.put("namaPgrh", getParam("txtnamaPgrh"));
		h.put("icPgrh", getParam("txticPgrh"));

		frmpenswastaanSenaraiFailData.addSimpanPengarah(h, session);

	}

	private void simpanKemaskiniPemaju(HttpSession session) throws Exception {
		// simpan kemaskini pemaju
		myLogger.info("simpan pemaju test>>>>> ");
		Hashtable h = new Hashtable();

		String idPemaju = "";
		String id_permohonan = getParam("id_permohonan");

		h.put("id_permohonan", id_permohonan);
		h.put("jenisPB", getParam("txtjenisPB"));
		h.put("nama", getParam("txtnama"));
		h.put("notel", getParam("txtnotel"));
		h.put("nofax", getParam("txtnofax"));
		h.put("alamat1", getParam("txtalamat1"));
		h.put("alamat2", getParam("txtalamat2"));
		h.put("alamat3", getParam("txtalamat3"));
		h.put("poskod", getParam("txtposkod"));
		h.put("selectNegeri", getParam("socNegeri"));

		frmpenswastaanSenaraiFailData.updateSimpanPemaju(h);

	}

	private void simpanpemaju(HttpSession session) throws Exception {
		// TODO maklumat simpan info pemaju
		myLogger.info("simpan pemaju test>>>>> ");
		Hashtable h = new Hashtable();

		String idPemaju = "";
		String id_permohonan = getParam("id_permohonan");

		h.put("id_permohonan", id_permohonan);
		h.put("jenisPB", getParam("txtjenisPB"));
		h.put("nama", getParam("txtnama"));
		h.put("notel", getParam("txtnotel"));
		h.put("nofax", getParam("txtnofax"));
		h.put("alamat1", getParam("txtalamat1"));
		h.put("alamat2", getParam("txtalamat2"));
		h.put("alamat3", getParam("txtalamat3"));
		h.put("poskod", getParam("txtposkod"));
		h.put("selectNegeri", getParam("socNegeri"));

		frmpenswastaanSenaraiFailData.addSimpanPemaju(h, session);

	}

	private void paparMaklumatPermohonan(String id_permohonan) throws Exception {
		// untuk papar maklumat pemohon

		Db db = null;
		String sql = "";

		try {

			Vector list = null;
			list = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " select k.nama_kementerian,a.nama_agensi,s.nama_suburusan,"
					+ " f.no_fail,f.tarikh_daftar_fail,h.no_rujukan_kjp,h.no_rujukan_lain,"
					+ " p.tarikh_surat,p.tarikh_terima,p.tujuan,"
					+ " FROM tblrujnegeri n,Tblpfdfail f,tblrujkementerian k,tblpermohonan p,"
					+ " tblhtppermohonan h,tblrujagensi a,tblrujsuburusan s where "
					+ " f.ID_NEGERI=n.ID_NEGERI and f.id_kementerian=k.id_kementerian "
					+ " and p.ID_FAIL=f.ID_FAIL and p.id_permohonan=h.ID_PERMOHONAN  "
					+ " and h.ID_AGENSI=a.id_agensi and f.ID_SUBURUSAN=s.ID_SUBURUSAN  "
					+ " and p.id_permohonan = '" + id_permohonan + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {

				h = new Hashtable();
				h.put("nama_negeri", rs.getString("nama_negeri"));
				h.put("nama_kementerian", rs.getString("nama_kementerian"));
				h.put("nama_agensi", rs.getString("nama_agensi"));
				h.put("nama_suburusan", rs.getString("nama_suburusan"));
				h.put("no_fail", rs.getString("no_fail"));
				h.put("tarikh_daftar_fail", rs.getString("tarikh_daftar_fail"));
				h.put("no_rujukan_kjp", rs.getString("no_rujukan_kjp"));
				h.put("no_rujukan_lain", rs.getString("no_rujukan_lain"));
				h.put("tarikh_surat", rs.getString("tarikh_surat"));
				h.put("tarikh_terima", rs.getString("tarikh_terima"));
				h.put("tujuan", rs.getString("tujuan"));

				list.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanPendaftaranPermohonan(String no_fail_seksyen)
			throws Exception {
		Db db = null;
		String sql = "";
		try {
			sql = "insert into table1 ";

		} catch (Exception e) {
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector cariFail(String nofail, String namafail, int idNegeri)
			throws Exception {

		myLogger.info("testing");

		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String chkNoFail = nofail.trim();
			String chkNamaFail = namafail.trim();
			// int chkIdNegeri = idNegeri.trim();

			sql = "SELECT A.NO_FAIL,A.TAJUK_FAIL,C.NAMA_NEGERI,D.KETERANGAN,C.ID_NEGERI"
					+

					" FROM TBLPFDFAIL A,"
					+ " TBLPERMOHONAN B,"
					+ " TBLRUJNEGERI C,"
					+ " TBLRUJSTATUS D"
					+

					" WHERE A.ID_FAIL = B.ID_FAIL"
					+ " AND C.ID_NEGERI = A.ID_NEGERI"
					+ " AND D.ID_STATUS = A.ID_STATUS";
			// "and no_fail like '%"+NoFail+"%'";
			boolean setLimit = true;
			if (nofail != null) {
				if (!nofail.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND ( UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ chkNoFail.toUpperCase() + "'|| '%' ) ";
				}
			}

			// namafail
			if (namafail != null) {
				if (!namafail.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(A.TAJUK_FAIL) LIKE '%' ||'"
							+ chkNamaFail.toUpperCase() + "'|| '%' ";
				}
			}

			if (idNegeri != 0) {
				setLimit = false;
				sql = sql + " AND UPPER(C.ID_NEGERI) LIKE '%' ||'" + idNegeri
						+ "'|| '%'  ";

			}

			myLogger.info("CARIFAIL 11111:: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			int bil = 1;
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("bil", bil);
				h.put("no_fail", rs.getString("NO_FAIL"));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));
				h.put("tajuk_fail", rs.getString("TAJUK_FAIL"));
				h.put("keterangan", rs.getString("KETERANGAN"));
				v.addElement(h);
				bil++;
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setupPage(HttpSession session, String action, Vector list) {
		try {

			this.context.put("totalRecords", list.size());
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

			Paging paging = new Paging(session, list, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1; // reset page number
			this.context.put("senaraiFail", paging.getPage(page));
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

	// page pendaftaran permohonan-function simpan fail
	private void simpanFail(HttpSession session, Long idfail) throws Exception {
		Hashtable h = null;
		h = new Hashtable();
		String kodNegeriMampu = "";
		String kodKementerianMampu = "";
		int idNegeri = 0;
		int idUrusan = 0;
		int idKementerian = 0;
		String idSeksyen = "";
		int idTarafkeselamatan = 1;
		/** TERBUKA */
		int idSubUrusan = 8;
		/** TERBUKA */
		int idLokasi = 1;
		int idFaharasat = 1;
		String idCatatan = "TIADA";
		String idFailRoot = "TIADA";
		String tarikhMasuk = getParam("txttarikhbukafail");

		Vector vecFail = new Vector();

		// idNegeri = Integer.parseInt(getParam("socNegeri"));
		idNegeri = getParamAsInteger("socNegeri");
		idUrusan = Integer.parseInt("4");
		idKementerian = Integer.parseInt(getParam("sockementerian"));
		idSeksyen = FrmUtilData.getSeksyenMengikutPengguna((String) session
				.getAttribute("_ekptg_user_id"));

		vecFail = FrmSenaraiFailPajakanKecilData.getFileCount(idNegeri,
				idUrusan);
		kodNegeriMampu = FrmSenaraiFailPajakanKecilData
				.getNegeriByMampu(idNegeri);
		kodKementerianMampu = FrmSenaraiFailPajakanKecilData
				.getKementerianByMampu(idNegeri);
		// biFail = vecFail.size();
		int fileSeq = 0;

		fileSeq = File.getSeqNo(Integer.parseInt(idSeksyen), idUrusan,
				idKementerian, idNegeri);
		System.out.println("FrmPajakanKecil:simpanFail::fileSeq:::" + fileSeq);

		String noFail = "JKPTG/101/906/";
		noFail += kodKementerianMampu + "/" + kodNegeriMampu + "-" + fileSeq;

		int idmasuk = Integer.parseInt((String) session
				.getAttribute("_ekptg_user_id"));
		h.put("id_Fail", idfail);
		h.put("id_Tarafkeselamatan", idTarafkeselamatan);
		h.put("id_Seksyen", Integer.parseInt(idSeksyen));
		h.put("id_Urusan", idUrusan);
		h.put("id_Suburusan", idSubUrusan);
		h.put("tajuk_Fail", getParam("txttajuk"));
		h.put("no_Fail", noFail);
		h.put("id_Negeri", idNegeri);
		h.put("id_Kementerian", idKementerian);
		h.put("flag_Fail", "1");
		h.put("id_Status", 7);
		h.put("id_Masuk", idmasuk);
		h.put("tarikh_Bukafail", tarikhMasuk);
		h.put("tarikhMasuk", tarikhMasuk);
		h.put("id_Lokasi", idLokasi);
		h.put("id_Faharasat", idFaharasat);
		h.put("catatan", idCatatan);
		h.put("no_Failroot", idFailRoot);
		FrmUtilData.simpanFail(h);

	}

	private void updatePermohonan(HttpSession session) throws Exception {
		String id = getParam("id_kemaskini");
		// String kod_cara_bayar = getParam("kod_cara_bayar");
		// String keterangan = getParam("keterangan");
		// String id_kemaskini = getParam("id_kemaskini");
		// Date tarikh_kemaskini = getParam("tarikh_kemaskini");
		// Date tarikh_kemaskini = new Date();
		// CaraBayarData.update(Long.parseLong(id_carabayar), kod_cara_bayar,
		// keterangan,Long.parseLong(id_kemaskini),tarikh_kemaskini);
	}

	private String simpanPermohonan(HttpSession session, String idFail, int flag)
			throws Exception {
		Hashtable data = null;
		data = new Hashtable();
		String txdBukafail = "";
		int idNegeri = 0;
		int idAgensi = 0;
		int idFaharasat = 1;
		int idStatus = 7;
		/** AKTIF */
		String tajuk = "TIADA";
		String strTiada = "TIADA";
		int idMasuk = 1;

		txdBukafail = getParam("txttarikhbukafail");
		tajuk = getParam("txttajuk");
		idMasuk = Integer.parseInt((String) session
				.getAttribute("_ekptg_user_id"));
		idAgensi = Integer.parseInt(getParam("socAgensi"));

		if (flag == 0) {
			// data.put("IdFail", fail);
			// else
			data.put("id_Fail", idFail);
			// id_PejabatJKPTG /** auto simpan*/
			data.put("no_Permohonan", strTiada);
			data.put("no_Perserahan", strTiada);
			data.put("tarikh_SuratKJP", txdBukafail);
			data.put("tarikh_Terima", txdBukafail);
			data.put("tajuk", tajuk);
			data.put("tarikh_Masuk", txdBukafail);
			data.put("id_Agensi", idAgensi);
			data.put("id_Jenistanah", 1);
			data.put("id_Pegawai", idMasuk);
			data.put("no_Failkjp", strTiada);
			data.put("no_Faillain", strTiada);
			data.put("tarikh_Agihan", txdBukafail);

			data.put("id_Masuk", idMasuk);
		}

		return FrmUtilData.simpanPermohonanHTP(data);
	}

	public void simpanStatus(HttpSession session, Long idPermohonan, Long idFail) {
		Long idMasuk = Long.parseLong((String) session
				.getAttribute("_ekptg_user_id"));

		Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
		s.setIdPermohonan(idPermohonan);
		s.setIdSuburusanstatus(Long.parseLong("2461"));
		s.setIdFail(idFail);
		s.setAktif("1");
		s.setIdMasuk(idMasuk);
		FrmUtilData.simpanStatusPermohonan(s);
	}

	private void label(Hashtable permohonan) {

		String labelNegeri = (String) permohonan.get("negeri");
		String labelKementerian = (String) permohonan.get("kementerian");
		String labelAgensi = (String) permohonan.get("agensi");
		String labelTajuk = (String) permohonan.get("tujuan");
		String labelNoFail = (String) permohonan.get("fail");
		String labelNoFailLain = (String) permohonan.get("rujukankjp");
		String labelTarikhSuratKJP = (String) permohonan.get("rtterima");
		String labelTarikhBukaFail = (String) permohonan.get("tdaftar");

		this.context.put("labelNegeri", labelNegeri);
		this.context.put("labelKementerian", labelKementerian);
		this.context.put("labelAgensi", labelAgensi);
		this.context.put("labelTajuk", labelTajuk);
		this.context.put("labelNoFail", labelNoFail);
		this.context.put("labelNoFailLain", labelNoFailLain);
		this.context.put("labelTarikhSuratKJP", labelTarikhSuratKJP);
		this.context.put("labelTarikhBukaFail", labelTarikhBukaFail);

	}

}
