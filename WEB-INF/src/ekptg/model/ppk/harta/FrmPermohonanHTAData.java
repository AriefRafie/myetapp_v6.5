
package ekptg.model.ppk.harta;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
import ekptg.helpers.Utils;
import ekptg.model.ppk.BicaraInteraktifData;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8SenaraiSemakInternalData;
import ekptg.model.utils.Fungsi;

public class FrmPermohonanHTAData extends FrmPrmhnnSek8InternalData{
	static Logger myLog = Logger.getLogger(ekptg.model.ppk.harta.FrmPermohonanHTAData.class);
	
	private Vector<Hashtable<String,String>> listHTAX = new Vector<Hashtable<String,String>>();
	private Vector<Hashtable<String,String>> listHTA = new Vector<Hashtable<String,String>>();
	private Vector<Hashtable<String,String>> listHarta = new Vector<Hashtable<String,String>>();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	FrmPrmhnnSek8SenaraiSemakInternalData logic_C = null;
	String idhta = "0";

	public String getIDHarta(){
		return idhta;
	}
	//private void simpanPilihanHTA(HttpSession session, String bolehsimpan)
	public void simpanPilihanHTA(Fungsi fnc,FrmPrmhnnSek8DaftarSek8InternalData logic_A
		,HttpServletRequest request
		,HttpSession session
		,Hashtable harta)	throws Exception {
		logic_C = new FrmPrmhnnSek8SenaraiSemakInternalData();
		String per_mati = fnc.getParam(request,"id_Permohonansimati");
		String user_id = String.valueOf(harta.get("idUser"));
		String bolehsimpan = String.valueOf(harta.get("bolehSimpan"));

		logic_C.clear_pilihanHTAMainDelete(per_mati, "Y");
		String[] ids = request.getParameterValues("ids");
		String[] idsx = request.getParameterValues("idsx");

		// check ada x perintah kuasa tadbir
		int check_pkt = 0;
		int check_pkt_true = 0;
		String flag_perlu_batal_kt = "";
		String[] ids_check = request.getParameterValues("ids_check");
		if (ids_check != null) {
			for (int ii = 0; ii < ids_check.length; ii++) {
				String temp_jenis_perintah_check = "jenis_perintah_harta"
						+ ids_check[ii];
				String jenis_perintah_check = fnc.getParam(request,temp_jenis_perintah_check);
				if (jenis_perintah_check.equals("2")) {
					check_pkt++;
				}
			}
		}
		// tutup

		String temp = null;
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				if (bolehsimpan.equals("yes")) {

					long id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
					String flag_daftar = "flag_daftar" + ids[i];
					logic_C.pilihanAdd(per_mati, ids[i], user_id,id_Pilihanhta, fnc.getParam(request,flag_daftar));
					String temp_id_ob = "main_ob" + ids[i];
					String[] ids_ob = request.getParameterValues(temp_id_ob);
					// buka
					String temp_jenis_perintah = "jenis_perintah_harta"+ ids[i];
					String jenis_perintah = fnc.getParam(request,temp_jenis_perintah);
					if (jenis_perintah.equals("2")) {
						check_pkt_true++;
					}
					// tutup

					if (ids_ob != null) {
						for (int y = 0; y < ids_ob.length; y++) {
							String temp_id_ob_sub = "check_ob" + ids[i]+ ids_ob[y];

							String[] ids_ob_sub = request.getParameterValues(temp_id_ob_sub);
							String ids_ob_sub1 = "", ids_ob_sub2 = "", ids_ob_sub3 = "", ids_ob_sub4 = "";
							if (ids_ob_sub != null) {

								for (int x = 0; x < ids_ob_sub.length; x++) {

									if (x == 0) {
										ids_ob_sub1 = ids_ob_sub[x];
									}
									if (x == 1) {
										ids_ob_sub2 = ids_ob_sub[x];
									}
									if (x == 2) {
										ids_ob_sub3 = ids_ob_sub[x];
									}
									if (x == 3) {
										ids_ob_sub4 = ids_ob_sub[x];
									}

								}
							}

							String flag_daftar_ob = "flag_daftar_ob" + ids[i]+ ids_ob[y];
							logic_C.pilihanAddOBHTA(jenis_perintah
									,per_mati
									,ids_ob[y]
									,ids_ob_sub1
									,ids_ob_sub2
									,ids_ob_sub3
									,ids_ob_sub4
									,user_id
									,id_Pilihanhta + ""
									, fnc.getParam(request,flag_daftar_ob)
									, "");

						}
					}
				}

			}
		}
		// buka

		if (check_pkt > 0 && check_pkt_true == 0) {
			flag_perlu_batal_kt = "unbatalkan";
		} else if (check_pkt > 0 && check_pkt_true > 0) {
			flag_perlu_batal_kt = "batalkan";
		}

		if (!flag_perlu_batal_kt.equals("")) {
			String bkp = fnc.getParam(request,"bkp");
			String lp = fnc.getParam(request,"lp");
			String bpa = fnc.getParam(request,"bpa");
			String lpa = fnc.getParam(request,"lpa");
			Vector listHartaTakAlihdulu = new Vector();
			listHartaTakAlihdulu = setDataSemuaHartaTakAlihdulu(per_mati,bkp, lp, bpa, lpa);
			
			for (int i = 0; i < listHartaTakAlihdulu.size(); i++) {
				Hashtable h = (Hashtable) listHartaTakAlihdulu.get(i);
				logic_C.clear_pilihanHTAMainDelete_byId(h.get("idhta").toString(), per_mati, "");

				if (flag_perlu_batal_kt.equals("batalkan")) {
					long id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
					logic_C.pilihanAdd(per_mati, h.get("idhta").toString(),
							user_id, id_Pilihanhta, "");

					Vector listHartaTakAlihduluOB = new Vector();
					setDataOBHTAdulu(per_mati, bkp, lp, bpa, lpa, "");
					listHartaTakAlihduluOB = getDataOBHTAdulu();
					
					for (int ix = 0; ix < listHartaTakAlihduluOB.size(); ix++) {
						Hashtable hx = (Hashtable) listHartaTakAlihduluOB.get(ix);
						if (h.get("idhta").toString().equals(
								hx.get("ID_HTA").toString())) {
							logic_C.pilihanAddOBHTA("", per_mati, hx.get(
									"ID_OB").toString(), "", "", "", "",
									(String) session
											.getAttribute("_ekptg_user_id"),
									id_Pilihanhta + "", "", "");
						}
					}
				}
			}

			Vector listHartaAlihdulu = new Vector();
			listHartaAlihdulu = setDataSemuaHartaAlihdulu(per_mati, bkp,lp, bpa, lpa);
			
			for (int i = 0; i < listHartaAlihdulu.size(); i++) {
				Hashtable h = (Hashtable) listHartaAlihdulu.get(i);
				logic_C.clear_pilihanHAMainDelete_byId(h.get("id_Ha").toString(), per_mati, "");

				if (flag_perlu_batal_kt.equals("batalkan")) {
					long id_Pilihanha = DB.getNextID("TBLPPKPILIHANHA_SEQ");
					logic_C.pilihanAdd_HA(per_mati, h.get("id_Ha").toString(),user_id, id_Pilihanha, "");

					Vector listHartaAlihduluOB = new Vector();
					setDataOBHAdulu(per_mati, bkp, lp, bpa, lpa, "");
					listHartaAlihduluOB = getDataOBHAdulu();
					for (int iy = 0; iy < listHartaAlihduluOB.size(); iy++) {
						Hashtable hy = (Hashtable) listHartaAlihduluOB.get(iy);
						if (h.get("id_Ha").toString().equals(hy.get("ID_HA").toString())) {
							logic_C.pilihanAddOBHA("", per_mati, hy.get("ID_OB").toString(), "", "", "", "",user_id,id_Pilihanha + "", "", "");
						}
					}
				}
			}

		}
		// tutup
//		this.context.put("tambahharta", "yes");
//		this.context.put("kembaliharta", "yes");
//		this.context.put("nowpast", "past");
		// this.context.put("appear_skrin_info", "simpan_pilihan");
		
		//razman komen dlu
		//ni versi baru
		//BicaraInteraktifData modelBI = new BicaraInteraktifData();
		//modelBI.generateListHartaSkrinPerintahBySimpanPilihanHarta(session, "", per_mati, null);
	    
	}
	//private void simpanpilihanHTAX(HttpSession session, String bolehsimpan)
	public void simpanPilihanHTAX(Fungsi fnc,FrmPrmhnnSek8DaftarSek8InternalData logic_A
		,HttpServletRequest request
		,HttpSession session
		,Hashtable harta)
		throws Exception {
		logic_C = new FrmPrmhnnSek8SenaraiSemakInternalData();
		String per_mati = fnc.getParam(request,"id_Permohonansimati");
		String user_id = String.valueOf(harta.get("idUser"));
		String bolehsimpan = String.valueOf(harta.get("bolehSimpan"));
		
		logic_C.clear_pilihanHTAMainDelete(per_mati, "T");
		//myLogger.info("NAK SIMPAN PILIHAN OB");
		String[] ids = request.getParameterValues("ids");
		//myLogger.info("ids :" + ids);

		// check ada x perintah kuasa tadbir
		int check_pkt = 0;
		int check_pkt_true = 0;
		String flag_perlu_batal_kt = "";
		String[] ids_check = request.getParameterValues("ids_check");
		if (ids_check != null) {
			for (int ii = 0; ii < ids_check.length; ii++) {
				String temp_jenis_perintah_check = "jenis_perintah_harta"
						+ ids_check[ii];
				String jenis_perintah_check = fnc.getParam(request,temp_jenis_perintah_check);
				if (jenis_perintah_check.equals("2")) {
					check_pkt++;
				}
			}
		}
		// tutup

		String temp = null;
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				if (bolehsimpan.equals("yes")) {
					// logic_C.clear_pilihanHTADelete(per_mati,ids[i],(String)session.getAttribute("_ekptg_user_id"));
					long id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
					String flag_daftar = "flag_daftar" + ids[i];
					logic_C.pilihanAdd(per_mati, ids[i], user_id,id_Pilihanhta, fnc.getParam(request,flag_daftar));
					String temp_id_ob = "main_ob" + ids[i];
					String[] ids_ob = request.getParameterValues(temp_id_ob);
					//myLogger.info("temp_id_ob :" + temp_id_ob);
					//myLogger.info("main_ob :" + ids_ob);

					// buka
					String temp_jenis_perintah = "jenis_perintah_harta"+ ids[i];
					String jenis_perintah = fnc.getParam(request,temp_jenis_perintah);
					if (jenis_perintah.equals("2")) {
						check_pkt_true++;
					}
					// tutup

					if (ids_ob != null) {
						for (int y = 0; y < ids_ob.length; y++) {
							String temp_id_ob_sub = "check_ob" + ids[i]
									+ ids_ob[y];
							String[] ids_ob_sub = request.getParameterValues(temp_id_ob_sub);
							String ids_ob_sub1 = "", ids_ob_sub2 = "", ids_ob_sub3 = "", ids_ob_sub4 = "";
							if (ids_ob_sub != null) {

								for (int x = 0; x < ids_ob_sub.length; x++) {

									if (x == 0) {
										ids_ob_sub1 = ids_ob_sub[x];
									}
									if (x == 1) {
										ids_ob_sub2 = ids_ob_sub[x];
									}
									if (x == 2) {
										ids_ob_sub3 = ids_ob_sub[x];
									}
									if (x == 3) {
										ids_ob_sub4 = ids_ob_sub[x];
									}

								}
							}
							logic_C.pilihanAddOBHTAX(per_mati, ids_ob[y],
									ids_ob_sub1, ids_ob_sub2, ids_ob_sub3,ids_ob_sub4, user_id,id_Pilihanhta + "");
						}
					}
				}
			}
		}

		// buka
		if (check_pkt > 0 && check_pkt_true == 0) {
			flag_perlu_batal_kt = "unbatalkan";
		} else if (check_pkt > 0 && check_pkt_true > 0) {
			flag_perlu_batal_kt = "batalkan";
		}
		myLog.info("flag_perlu_batal_kt:" + flag_perlu_batal_kt);

		if (!flag_perlu_batal_kt.equals("")) {
			String bkp = fnc.getParam(request,"bkp");
			String lp = fnc.getParam(request,"lp");
			String bpa = fnc.getParam(request,"bpa");
			String lpa = fnc.getParam(request,"lpa");
			Vector listHartaTakAlihdulu = new Vector();
			//listHartaTakAlihdulu = logic.setDataSemuaHartaTakAlihdulu(per_mati,bkp, lp, bpa, lpa);
			listHartaTakAlihdulu = setDataSemuaHartaTakAlihdulu(per_mati,bkp, lp, bpa, lpa);
			for (int i = 0; i < listHartaTakAlihdulu.size(); i++) {
				Hashtable h = (Hashtable) listHartaTakAlihdulu.get(i);
				//myLogger.info("GET ID HTA :" + h.get("idhta").toString());
				logic_C.clear_pilihanHTAMainDelete_byId(h.get("idhta").toString(), per_mati, "");

				if (flag_perlu_batal_kt.equals("batalkan")) {
					long id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
					logic_C.pilihanAdd(per_mati, h.get("idhta").toString(),user_id, id_Pilihanhta, "");

					Vector listHartaTakAlihduluOB = new Vector();
					//logic.setDataOBHTAdulu(per_mati, bkp, lp, bpa, lpa, "");
					setDataOBHTAdulu(per_mati, bkp, lp, bpa, lpa, "");
					//listHartaTakAlihduluOB = logic.getDataOBHTAdulu();
					listHartaTakAlihduluOB = getDataOBHTAdulu();
					for (int ix = 0; ix < listHartaTakAlihduluOB.size(); ix++) {
						Hashtable hx = (Hashtable) listHartaTakAlihduluOB.get(ix);
						//myLogger.info("GET ID HTA OB :"+ hx.get("ID_HTA").toString());
						if (h.get("idhta").toString().equals(hx.get("ID_HTA").toString())) {
							//myLogger.info("GET ID OB OB :"+ hx.get("ID_OB").toString());
							logic_C.pilihanAddOBHTA("", per_mati, hx.get("ID_OB").toString(), "", "", "", "",user_id,
									id_Pilihanhta + "", "", "");
						}
					}
				}
			}

			Vector listHartaAlihdulu = new Vector();
			//listHartaAlihdulu = logic.setDataSemuaHartaAlihdulu(per_mati, bkp,lp, bpa, lpa);
			listHartaAlihdulu = setDataSemuaHartaAlihdulu(per_mati, bkp,lp, bpa, lpa);
			for (int i = 0; i < listHartaAlihdulu.size(); i++) {
				Hashtable h = (Hashtable) listHartaAlihdulu.get(i);
				//myLogger.info("GET ID HA :" + h.get("id_Ha").toString());
				logic_C.clear_pilihanHAMainDelete_byId(h.get("id_Ha").toString(), per_mati, "");

				if (flag_perlu_batal_kt.equals("batalkan")) {
					long id_Pilihanha = DB.getNextID("TBLPPKPILIHANHA_SEQ");
					logic_C.pilihanAdd_HA(per_mati, h.get("id_Ha").toString(),user_id, id_Pilihanha, "");

					Vector listHartaAlihduluOB = new Vector();
					//logic.setDataOBHAdulu(per_mati, bkp, lp, bpa, lpa, "");
					//listHartaAlihduluOB = logic.getDataOBHAdulu();
					setDataOBHAdulu(per_mati, bkp, lp, bpa, lpa, "");
					listHartaAlihduluOB = getDataOBHAdulu();
					for (int iy = 0; iy < listHartaAlihduluOB.size(); iy++) {
						Hashtable hy = (Hashtable) listHartaAlihduluOB.get(iy);
						//myLogger.info("GET ID HA OB :"+ hy.get("ID_HA").toString());
						if (h.get("id_Ha").toString().equals(hy.get("ID_HA").toString())) {
							//myLogger.info("GET ID OB OB :"+ hy.get("ID_OB").toString());
							logic_C.pilihanAddOBHA("", per_mati, hy.get("ID_OB").toString(), "", "", "", "",user_id,
									id_Pilihanha + "", "", "");
						}
					}
				}
			}

		}
		// tutup

//		String radioX1 = getParam("radioHtaamViewX1");
//		String radioX2 = getParam("radioHtaamViewX2");
//		String radioX3 = getParam("radioHtaamViewX3");
//		this.context.put("tambahbutton", "yes");
//		this.context.put("kembalibutton", "yes");
//		this.context.put("nowpast", "past");
		// this.context.put("appear_skrin_info", "simpan_pilihan");
		
		//razman komen dlu, ni versi baru
		//BicaraInteraktifData modelBI = new BicaraInteraktifData();
		//modelBI.generateListHartaSkrinPerintahBySimpanPilihanHarta(session, "", per_mati, null);
	
	}
	
	public void tambahKemaskiniHarta(Hashtable<String,String> data,String mode) throws Exception {
		Db db = null;
		String sql = "";
		Connection conn = null;
		try {
			//Maklumat Tanah
			int daerah = Integer.parseInt(String.valueOf(data.get("daerah")));
			int jenishakmilik = 0;
			int jenisluas = Integer.parseInt(String.valueOf(data.get("jenisluas")));
			int kategori = Integer.parseInt(String.valueOf(data.get("kategori")));
			int mukim = Integer.parseInt(String.valueOf(data.get("mukim")));
			int negeri = Integer.parseInt(String.valueOf(data.get("negeri")));

			String alamat_hta1 = (String) data.get("alamat_hta1");
			String alamat_hta2 = (String) data.get("alamat_hta2");
			String alamat_hta3 = (String) data.get("alamat_hta3");
			String basimati = (String) data.get("basimati");
			String bbsimati = (String) data.get("bbsimati");
			String catatan = (String) data.get("catatan");
			String flag = (String) data.get("flag");
			String FLAG_DAFTAR = (String) data.get("FLAG_DAFTAR");
			String jenis_Hta = (String) data.get("jenis_Hta");
			String jenistanah = (String) data.get("jenistanah");			
			String id_bandarhta = (String) data.get("id_bandarhta");
			String luasasal = (String) data.get("luasasal");
			String luashmp = (String) data.get("luashmp");
			String nilai_Hta_mati = (String) data.get("nilai_Hta_mati");
			String nilai_Hta_memohon = (String) data.get("nilai_Hta_memohon");
			String noHakmilik = "";
			String nopajakan = (String) data.get("nopajakan");
			String noperserahan = "";
			String nopt = "";
			String pemilikan = (String) data.get("pemilikan");
			String poskod_hta = (String) data.get("poskod");
			String sekatan = "";
			String syaratNyata = "";
			String tanggungan = (String) data.get("tanggungan");					
				
			//LAIN2
			int bandarpemaju = 0;
			int negeripemaju = 0;							
			
			String alamatpemaju1 = "";
			String alamatpemaju2 = "";
			String alamatpemaju3 = "";
			String idsimati = (String) data.get("idSimati");			
			String idUser = (String) data.get("idUser");
			String id_Permohonansimati = (String) data.get("id_Permohonansimati");
			String jeniskepentingan="";
			String namapemaju = "";
			String namarancangan ="";
			String nocagaran = "";
			String nolot ="";
			String noroh = "";
			String noperjanjian = "";
			String poskodpemaju = "";
			//String tarikhKemaskini = (String) data.get("tarikh_Kemaskini");
			String tarikhperja = "";
			String tarikhperjanjian="";
			
			if(jenis_Hta.equals("T")){
				//Maklumat Tanah
				nocagaran = (String) data.get("nocagaran");
				nolot = (String) data.get("nolot");
				noroh = (String) data.get("noroh");
				noperjanjian = (String) data.get("noperjanjian");
				tarikhperja = (String) data.get("tarikhperjanjian");
				tarikhperjanjian = "to_date('" + tarikhperja + "','dd/MM/yyyy')";
				
				//LAIN2
				alamatpemaju1 = String.valueOf(data.get("alamatpemaju1"));
				alamatpemaju2 = String.valueOf(data.get("alamatpemaju2"));
				alamatpemaju3 = String.valueOf(data.get("alamatpemaju3"));
				bandarpemaju = Integer.parseInt(String.valueOf(data.get("bandarpemaju")));
				jeniskepentingan = (String) data.get("jeniskepentingan");
				namapemaju = String.valueOf(data.get("namapemaju"));
				namarancangan = (String) data.get("namarancangan");
				negeripemaju =  Integer.parseInt(String.valueOf(data.get("negeripemaju")));				
				poskodpemaju = String.valueOf(data.get("poskodpemaju"));
			
			}else if(jenis_Hta.equals("Y")){
				jenishakmilik = Integer.parseInt(String.valueOf(data.get("jenishakmilik")));
				noHakmilik = (String) data.get("noHakmilik");
				noperserahan = (String) data.get("noperserahan");
				nopt = (String) data.get("nopt");
				sekatan = (String) data.get("sekatan");
				syaratNyata = (String) data.get("syaratNyata");
				
			}	
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(mode.equals("simpan")){
				idhta = String.valueOf(DB.getNextID(db, "TBLPPKHTA_SEQ"));
				r.add("id_hta", r.unquote(idhta));
				r.add("id_permohonansimati", id_Permohonansimati);

			}else{
				idhta = (String) data.get("idhta");
				r.update("id_hta", r.unquote(idhta));
			}			

			if (basimati != "0") {
				r.add("ba_simati", basimati);
			}
			if (bbsimati != "0") {
				r.add("bb_simati", bbsimati);
			}
			r.add("catatan", catatan);			
			if (daerah != 0) {
				r.add("id_daerah", daerah);
			}
			if (kategori != 0) {
				r.add("id_kategori", kategori);
			}			
			if (jenisluas != 0) {
				r.add("id_luas", jenisluas);
			}
			if (mukim != 0) {
				r.add("id_mukim", mukim);
			}
			if (negeri != 0) {
				r.add("id_negeri", negeri);
			}
			r.add("id_simati", idsimati);
			r.add("jenis_tnh", jenistanah);
			r.add("luas", luasasal);
			r.add("luas_hmp", luashmp);
			if (nilai_Hta_memohon != "") {
				String nilai_hta_P = nilai_Hta_memohon.replace(",", "");
				r.add("nilai_hta_tarikhmohon", Double.parseDouble(nilai_hta_P));
			} else {
				r.add("nilai_hta_tarikhmohon", nilai_Hta_memohon);
			}
			if (nilai_Hta_mati != "") {
				String nilai_hta_M = nilai_Hta_mati.replace(",", "");
				r.add("nilai_hta_tarikhmati", Double.parseDouble(nilai_hta_M));
			} else {
				r.add("nilai_hta_tarikhmati", nilai_Hta_mati);
			}			
			r.add("no_pajakan", nopajakan);
			r.add("status_pemilikan", pemilikan);
			r.add("tanggungan", tanggungan);
			//ALAMAT
			r.add("alamat_hta1", alamat_hta1);
			r.add("alamat_hta2", alamat_hta2);
			r.add("alamat_hta3", alamat_hta3);
			r.add("id_bandarhta", id_bandarhta);
			r.add("poskod_hta", poskod_hta);
//			if(!bandar_hta.equals("")){
//				r.add("bandar_hta", bandar_hta);
//			}
			//LAIN2
			//r.add("", noperserahan);
			r.add("flag_daftar", FLAG_DAFTAR);
			r.add("flag_kategori_hta", flag);
			r.add("jenis_hta", jenis_Hta);
			
			// String jenishta="T";
			if(jenis_Hta.equals("T")){
				r.add("id_bandarpemaju", bandarpemaju);
				r.add("id_negeripemaju", negeripemaju);
				r.add("alamat_pemaju1", alamatpemaju1);
				r.add("alamat_pemaju2", alamatpemaju2);
				r.add("alamat_pemaju3", alamatpemaju3);
				r.add("nama_pemaju", namapemaju);
				r.add("poskod_pemaju", poskodpemaju);
				
				r.add("jenis_kepentingan", jeniskepentingan);
				r.add("nama_rancangan", namarancangan);
				r.add("no_lot_id", nolot);
				r.add("no_roh", noroh);
				r.add("no_cagaran", nocagaran);				
				r.add("no_perjanjian", noperjanjian);
				if(!tarikhperja.equals("")){
					r.add("tarikh_perjanjian", r.unquote(tarikhperjanjian));
				}
			
			}else if(jenis_Hta.equals("Y")){
				r.add("id_jenishm", jenishakmilik);
				r.add("no_hakmilik", noHakmilik);
				r.add("no_perserahan", noperserahan);
				r.add("no_pt", nopt);
				r.add("sekatan", sekatan);
				r.add("syarat_nyata", syaratNyata);			
				
			}
			//sql = r.getSQLUpdate("tblppkhta");
			if(mode.equals("simpan")){
				r.add("flag_pa", "T");
				r.add("flag_pt", "T");
				r.add("flag_selesai", "T");
				r.add("id_masuk", idUser);
				r.add("tarikh_masuk", r.unquote("sysdate"));
				sql = r.getSQLInsert("tblppkhta");
				
			}else{
				r.add("id_kemaskini", idUser);
				r.add("tarikh_kemaskini", r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblppkhta");
				
			}
			//
			myLog.info("tambahKemaskiniHarta:sql tblppkhta="+sql);
			stmt.executeUpdate(sql);

			// TBLPPKHTAPERMOHONAN			
			r.clear();
			if(mode.equals("simpan")){
				r.add("id_hta", r.unquote(idhta));
				r.add("id_permohonansimati", id_Permohonansimati);
				
			}else{
				r.update("id_hta", r.unquote(idhta));
				r.update("id_permohonansimati", id_Permohonansimati);
				
			}
			
			if (basimati != "0") {
				r.add("ba_simati", basimati);
			}
			if (bbsimati != "0") {
				r.add("bb_simati", bbsimati);
			}
			r.add("catatan", catatan);
			if (daerah != 0) {
				r.add("id_daerah", daerah);
			}			
			//r.add("id_jenishm", jenishakmilik);
			if (kategori != 0) {
				r.add("id_kategori", kategori);
			}			
			if (jenisluas != 0) {
				r.add("id_luas", jenisluas);
			}			
			if (mukim != 0) {
				r.add("id_mukim", mukim);
			}				
			if (negeri != 0) {
				r.add("id_negeri", negeri);
			}		
			r.add("id_simati", idsimati);
			r.add("jenis_tnh", jenistanah);
			r.add("luas", luasasal);
			r.add("luas_hmp", luashmp);
			if (nilai_Hta_mati != "") {
				String nilai_hta_M = nilai_Hta_mati.replace(",", "");
				r.add("nilai_hta_tarikhmati", Double.parseDouble(nilai_hta_M));
				//r.add("nilai_Hta_Tarikhmati", Double.parseDouble(nilai_Hta_mati));
			} else {
				r.add("nilai_hta_tarikhmati", nilai_Hta_mati);
			}
			if (nilai_Hta_memohon != "") {
				String nilai_hta_P = nilai_Hta_memohon.replace(",", "");
				r.add("nilai_hta_tarikhmohon", Double.parseDouble(nilai_hta_P));
				//r.add("nilai_Hta_Tarikhmohon", Double.parseDouble(nilai_Hta_memohon));
			} else {
				r.add("nilai_hta_tarikhmohon", nilai_Hta_memohon);
			}			
			r.add("no_pajakan", nopajakan);	
			r.add("status_pemilikan", pemilikan);			
			r.add("tanggungan", tanggungan);
			//r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			//ALAMAT
			r.add("alamat_hta1", alamat_hta1);
			r.add("alamat_hta2", alamat_hta2);
			r.add("alamat_hta3", alamat_hta3);
			r.add("poskod_hta", poskod_hta);
			r.add("id_bandarhta", id_bandarhta);
			//LAIN2
			r.add("flag_daftar", FLAG_DAFTAR);
			r.add("flag_kategori_hta", flag);
			r.add("jenis_hta", jenis_Hta);
			// String jenishta="T";
			if(jenis_Hta.equals("T")){
				r.add("id_bandarpemaju", bandarpemaju);
				r.add("id_negeripemaju", negeripemaju);
				r.add("alamat_pemaju1", alamatpemaju1);
				r.add("alamat_pemaju2", alamatpemaju2);
				r.add("alamat_pemaju3", alamatpemaju3);
				r.add("nama_pemaju", namapemaju);
				r.add("poskod_pemaju", poskodpemaju);
				
				r.add("jenis_kepentingan", jeniskepentingan);
				r.add("nama_rancangan", namarancangan);
				r.add("no_lot_id", nolot);
				r.add("no_roh", noroh);
				r.add("no_cagaran", nocagaran);				
				r.add("no_perjanjian", noperjanjian);
				if(!tarikhperja.equals("")){
					r.add("tarikh_perjanjian", r.unquote(tarikhperjanjian));
				}
			
			}else if(jenis_Hta.equals("Y")){
				r.add("id_jenishm", jenishakmilik);
				r.add("no_hakmilik", noHakmilik);
				r.add("no_perserahan", noperserahan);
				r.add("no_pt", nopt);
				r.add("sekatan", sekatan);
				r.add("syarat_nyata", syaratNyata);			
				
			}					 
			//sql = r.getSQLUpdate("tblppkhtapermohonan");
			if(mode.equals("simpan")){
				r.add("flag_pa", "T");
				r.add("flag_pt", "T");
				r.add("flag_selesai", "T");

				r.add("id_masuk", idUser);
				r.add("tarikh_masuk", r.unquote("sysdate"));
				sql = r.getSQLInsert("tblppkhtapermohonan");
				
			}else{
				r.add("id_kemaskini", idUser);
				r.add("tarikh_kemaskini", r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblppkhtapermohonan");
				
			}
			//
			myLog.info("tambahKemaskiniHarta:sql tblppkhtapermohonan="+sql);
			stmt.executeUpdate(sql);

			conn.commit();

		}catch (Exception re) {
			myLog.error("Error: ", re);
			//throw re;
		} finally {
			if (db != null)
				db.close();
		}
		
	}
	
	public void kemaskiniHarta(Hashtable hashData,HttpServletRequest request,Fungsi fnc) throws Exception {
		myLog.info("kemaskiniHarta:-------Read Here----");
		Hashtable h = new Hashtable();
		if (fnc.getParam(request,"socNegeriHtaamUp") != "") {
			h.put("negeri", Integer.parseInt(fnc.getParam(request,"socNegeriHtaamUp")));
		} else {
			h.put("negeri", 0);
		}
		if (fnc.getParam(request,"socDaerahHtaamUp") != "") {
			h.put("daerah", Integer.parseInt(fnc.getParam(request,"socDaerahHtaamUp")));
		} else {
			h.put("daerah", 0);
		}
		if (fnc.getParam(request,"socMukimHtaamUp") != "") {
			h.put("mukim", Integer.parseInt(fnc.getParam(request,"socMukimHtaamUp")));
		} else {
			h.put("mukim", 0);
		}

		h.put("idhta", fnc.getParam(request,"id_htaam"));
		
		if (fnc.getParam(request,"socJenisHakmilikHtaamUp") != "") {
			h.put("jenishakmilik", Integer.parseInt(fnc.getParam(request,"socJenisHakmilikHtaamUp")));
		} else {
			h.put("jenishakmilik", 0);
		}		
		h.put("noHakmilik", fnc.getParam(request,"txtNoHakmilikHtaamUp"));
		h.put("nopt", fnc.getParam(request,"txtNoPTHtaamUp"));
		if (fnc.getParam(request,"socKategoriTanahHtaamUp") != "") {
			h.put("kategori", Integer.parseInt(fnc.getParam(request,"socKategoriTanahHtaamUp")));
		} else {
			h.put("kategori", 0);
		}
		h.put("luashmp", fnc.getParam(request,"txtLuasHMpHtaamUpd"));
		h.put("jenistanah", fnc.getParam(request,"socJenisTanahHtaamUpd"));
		if (fnc.getParam(request,"socJenisLuasHtaamUpd") != "") {
			h.put("jenisluas", Integer.parseInt(fnc.getParam(request,"socJenisLuasHtaamUpd")));
		} else {
			h.put("jenisluas", 0);
		}
		h.put("luasasal", fnc.getParam(request,"txtLuasAsalHtaamUpd"));
		h.put("sekatan", fnc.getParam(request,"txtSekatan") == null ? "" : fnc.getParam(request,"txtSekatan"));
		h.put("syaratNyata", fnc.getParam(request,"txtSyaratNyata") == null ? "" : fnc.getParam(request,"txtSyaratNyata"));
		h.put("tanggungan", fnc.getParam(request,"txtTanggunganHtaamUp"));
		h.put("catatan", fnc.getParam(request,"txtCatatanHt"));
		h.put("noperserahan", fnc.getParam(request,"txtNoPersHtaamUp"));

		h.put("alamat_hta1", fnc.getParam(request,"txtAlamat1Htaam1"));
		h.put("alamat_hta2", fnc.getParam(request,"txtAlamat2Htaam"));
		h.put("alamat_hta3", fnc.getParam(request,"txtAlamat3Htaam"));
		h.put("poskod", fnc.getParam(request,"txtAlamatPoskodHtaam"));
		h.put("id_bandarhta", fnc.getParam(request,"txtBandarHartaHtaamX2"));
		
		h.put("nilai_Hta_memohon", Utils.RemoveComma(fnc.getParam(request,"txtNilaiTarikhMohonHt")));
		h.put("nilai_Hta_mati", Utils.RemoveComma(fnc.getParam(request,"txtNilaiTarikhMatiHtaamUpd")));
		if (fnc.getParam(request,"socStatusPemilikanHtaamUpd") != "") {
			h.put("pemilikan", fnc.getParam(request,"socStatusPemilikanHtaamUpd"));
		} else {
			h.put("pemilikan", "0");
		}		
		h.put("nopajakan", fnc.getParam(request,"txtNoPajakanUp"));
		if (fnc.getParam(request,"txtBahagianSimati1Up") != "") {
			h.put("basimati", fnc.getParam(request,"txtBahagianSimati1Up"));
		} else {
			h.put("basimati", "0");
		}		
		if (fnc.getParam(request,"txtBahagianSimati2Up") != "") {
			h.put("bbsimati", fnc.getParam(request,"txtBahagianSimati2Up"));
		} else {
			h.put("bbsimati", "0");
		}		
		h.put("FLAG_DAFTAR", String.valueOf(hashData.get("flagDaftar")));
		h.put("id_Permohonansimati", String.valueOf(hashData.get("idPermohonanSimati")));
		h.put("idSimati", String.valueOf(hashData.get("idSimati")));
		
		// fungsi baru untuk penambahbaikkan...boleh kemaskini jenis HTATH
		String radioHtaamViewX_update = fnc.getParam(request,"radioHtaamViewX_update");
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

		String flag_tukar_jenis_hta = fnc.getParam(request,"flag_tukar_jenis_hta");
		if (flag_tukar_jenis_hta.equals("ADA")) {
			h.put("flag", "");
			h.put("jenis_Hta", "Y");
		} else if (flag_tukar_jenis_hta.equals("TIADA")) {
			h.put("jenis_Hta", "T");
			h.put("flag", no_flag);
		} else {
			h.put("flag", "");
			h.put("jenis_Hta", "Y");
		}

		//h.put("id_Kemaskini", String.valueOf(hashData.get("idUser")));
		h.put("idUser", String.valueOf(hashData.get("idUser")));
		Date date = new Date();
		String currentDate = sdf.format(date);
		h.put("tarikh_Kemaskini", currentDate);
		
		//updateHtaam(h);
		tambahKemaskiniHarta(h,"kemaskini");
		
	}
	//public void addHtaam(HttpSession session) throws Exception {
	public void tambahHarta(Hashtable hashData,HttpServletRequest request,Fungsi fnc) throws Exception {
		String bandar = fnc.getParam(request,"txtBandarHartaHtaamX2");
		//System.out.println("Bandar = "+bandar);
		Hashtable h = new Hashtable();
		h.put("FLAG_DAFTAR", fnc.getParam(request,"FLAG_DAFTAR"));
		h.put("noHakmilik", fnc.getParam(request,"txtNoHakmilikHtaam"));
		h.put("idSimati", fnc.getParam(request,"idSimati"));
		h.put("id_Permohonansimati", fnc.getParam(request,"id_Permohonansimati"));
		h.put("nopt", fnc.getParam(request,"txtNoPTHtaam"));
		h.put("alamat_hta1", fnc.getParam(request,"txtAlamat1Htaam1"));
		h.put("alamat_hta2", fnc.getParam(request,"txtAlamat2Htaam"));
		h.put("alamat_hta3", fnc.getParam(request,"txtAlamat3Htaam"));
		h.put("poskod", fnc.getParam(request,"txtAlamatPoskodHtaam"));
		h.put("id_bandarhta", fnc.getParam(request,"txtBandarHartaHtaamX2"));
		h.put("nilai_Hta_memohon", fnc.getParam(request,"txtNilaiTarikhMohonHtaa"));
		h.put("nilai_Hta_mati", fnc.getParam(request,"txtNilaiTarikhMatiHtaam"));

		if (fnc.getParam(request,"socKategoriTanahHtaam") != "") {
			h.put("kategori", Integer.parseInt(fnc.getParam(request,"socKategoriTanahHtaam")));
		} else {
			h.put("kategori", 0);
		}

		if (fnc.getParam(request,"socJenisHakmilikHtaam") != "") {
			h.put("jenishakmilik", Integer.parseInt(fnc.getParam(request,"socJenisHakmilikHtaam")));
		} else {
			h.put("jenishakmilik", 0);
		}

		h.put("pemilikan", fnc.getParam(request,"socStatusPemilikanHtaam"));

		if (fnc.getParam(request,"socNegeriHtaam") != "") {
			h.put("negeri", Integer.parseInt(fnc.getParam(request,"socNegeriHtaam")));
		} else {
			h.put("negeri", 0);
		}

		if (fnc.getParam(request,"socDaerahHtaam") != "") {
			h.put("daerah", Integer.parseInt(fnc.getParam(request,"socDaerahHtaam")));
		} else {
			h.put("daerah", 0);
		}

		if (fnc.getParam(request,"socMukimHtaam") != "") {
			h.put("mukim", Integer.parseInt(fnc.getParam(request,"socMukimHtaam")));
		} else {
			h.put("mukim", 0);
		}

		h.put("luashmp", fnc.getParam(request,"txtLuasHMpHtaam"));
		h.put("luasasal", fnc.getParam(request,"txtLuasAsalHtaam"));
		h.put("nopajakan", fnc.getParam(request,"txtNoPajakan"));
		h.put("jenistanah", fnc.getParam(request,"socJenisTanahHtaam"));

		if (fnc.getParam(request,"txtBahagianSimati1") != "") {
			h.put("basimati", fnc.getParam(request,"txtBahagianSimati1"));
		} else {
			h.put("basimati", "0");
		}

		if (fnc.getParam(request,"txtBahagianSimati2") != "") {
			h.put("bbsimati", fnc.getParam(request,"txtBahagianSimati2"));
		} else {
			h.put("bbsimati", "0");
		}

		if (fnc.getParam(request,"socJenisLuasHtaam") != "") {
			h.put("jenisluas", Integer.parseInt(fnc.getParam(request,"socJenisLuasHtaam")));
		} else {
			h.put("jenisluas", 0);
		}

		h.put("tanggungan", fnc.getParam(request,"txtTanggunganHtaam"));

		h.put("catatan", fnc.getParam(request,"txtCatatanHtaam"));
		h.put("noperserahan", fnc.getParam(request,"txtNoPersHtaam"));
		h.put("id_Masuk", String.valueOf(hashData.get("idUser")));
		Date date = new Date();
		String currentDate = sdf.format(date);

		h.put("tarikh_Masuk", currentDate);
		h.put("sekatan", fnc.getParam(request,"txtSekatan") == null ? "" : fnc.getParam(request,"txtSekatan"));
		h.put("syaratNyata", fnc.getParam(request,"txtSyaratNyata") == null ? "" : fnc.getParam(request,"txtSyaratNyata"));

		if(Boolean.parseBoolean(String.valueOf(hashData.get("jenisHarta"))))
			addHtaam(h);		
		else
			addHtaamX(h);		
			
		//session.setAttribute("idHtaSession", idHta);
		//return idHta;
		
	}
	
	public Hashtable<String,String> getDataHTAX(String idhtaam, String id_permohonansimati)
		throws Exception {
		Db db = null;
		listHarta.clear();
		String sql = "";
		Hashtable<String,String> h = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT H.FLAG_DAFTAR,H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT" +
				", TRIM(TO_CHAR(NVL(H.NILAI_HTA_TARIKHMOHON,0),'999,999,999.99')) NILAI_HTA_TARIKHMOHON" +
				", TRIM(TO_CHAR(NVL(H.NILAI_HTA_TARIKHMATI,0),'999,999,999.99')) NILAI_HTA_TARIKHMATI" +
							//" ,DOKUMEN.ID_DOKUMEN , DOKUMEN.NAMA_DOKUMEN ,PELAN.ID_PELAN " +
				", H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, "+
				" H.ID_NEGERI, H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, H.LUAS, "+
				" H.NO_CAGARAN, H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, "+
				" H.TANGGUNGAN, H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN, H.NAMA_PEMAJU, "+
				" H.ALAMAT_PEMAJU1, H.ALAMAT_PEMAJU2, H.ALAMAT_PEMAJU3, H.POSKOD_PEMAJU, "+
				" H.BANDAR_PEMAJU, H.ID_BANDARPEMAJU, H.ID_NEGERIPEMAJU, H.ALAMAT_HTA1, "+
				" H.ALAMAT_HTA2, H.ALAMAT_HTA3, H.POSKOD_HTA, H.BANDAR_HTA, H.ID_BANDARHTA, "+
				" H.NO_PERJANJIAN, H.TARIKH_PERJANJIAN, H.NAMA_RANCANGAN, H.NO_ROH, H.NO_LOT_ID, "+
				" H.FLAG_KATEGORI_HTA, H.JENIS_KEPENTINGAN  "+
				" FROM TBLPPKHTAPERMOHONAN H "+
				" WHERE " +
				" H.ID_PERMOHONANSIMATI = '"+ id_permohonansimati+ "'"+
				" AND H.JENIS_HTA = 'T'  AND H.ID_HTA = '"+ idhtaam + "' " +
				"";
			//myLogger.info("***GET BY ID getDataHTAX :sql=" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
					
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("FLAG_DAFTAR", rs.getString("FLAG_DAFTAR") == null ? "" : rs.getString("FLAG_DAFTAR"));				
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs.getString("id_Hta"));
				//h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? "": rs.getString("id_Jenishm"));
				//h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? "" : rs.getString("no_Hakmilik"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs.getString("id_Mukim"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs.getString("no_Pt"));
				h.put("nolot", rs.getString("no_Lot_Id") == null ? "" : rs.getString("no_Lot_Id"));
				h.put("noroh", rs.getString("no_Roh") == null ? "" : rs.getString("no_Roh"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs.getString("id_Kategori"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs.getString("id_Luas"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs.getString("luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs.getString("catatan"));

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs.getString("id_Simati"));
				h.put("jeniskepentingan",rs.getString("jenis_Kepentingan") == null ? "" : rs.getString("jenis_Kepentingan"));
				h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon"));			
				h.put("nilai_Hta_mati",rs.getString("nilai_Hta_Tarikhmati"));
				h.put("pemilikan",rs.getString("status_Pemilikan") == null ? "" : rs.getString("status_Pemilikan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs.getString("jenis_Hta"));
				h.put("flag", rs.getString("flag_Kategori_Hta") == null ? "": rs.getString("flag_Kategori_Hta"));

				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs.getString("no_Pajakan"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? "": rs.getString("tanggungan"));
				h.put("noperserahan",rs.getString("no_perserahan") == null ? "" : rs.getString("no_perserahan"));
				h.put("noperjanjian",rs.getString("no_Perjanjian") == null ? "" : rs.getString("no_Perjanjian"));
				h.put("tarikhperjanjian",rs.getString("tarikh_Perjanjian") == null 
							? "": sdf.format(rs.getDate("tarikh_Perjanjian")));
				h.put("namarancangan",rs.getString("nama_Rancangan") == null ? "" : rs.getString("nama_Rancangan"));
				
				h.put("namapemaju", rs.getString("nama_Pemaju") == null ? "": rs.getString("nama_Pemaju"));
				h.put("alamatpemaju1",rs.getString("alamat_Pemaju1") == null ? "" : rs.getString("alamat_Pemaju1"));
				h.put("alamatpemaju2",rs.getString("alamat_Pemaju2") == null ? "" : rs.getString("alamat_Pemaju2"));
				h.put("alamatpemaju3",rs.getString("alamat_Pemaju3") == null ? "" : rs.getString("alamat_Pemaju3"));
				h.put("poskodpemaju",rs.getString("poskod_Pemaju") == null ? "" : rs.getString("poskod_Pemaju"));
				h.put("bandarpemaju",rs.getString("id_bandarpemaju") == null ? "" : rs.getString("id_bandarpemaju"));
				h.put("bandarAdd",rs.getString("id_bandarpemaju") == null ? "0" : rs.getString("id_bandarpemaju"));
				h.put("negeripemaju",rs.getString("id_Negeripemaju") == null ? "" : rs.getString("id_Negeripemaju"));
				h.put("negeriAdd",rs.getString("id_Negeripemaju") == null ? "0" : rs.getString("id_Negeripemaju"));
				
				h.put("alamathta1", rs.getString("alamat_Hta1") == null ? "": rs.getString("alamat_Hta1"));
				h.put("alamathta2", rs.getString("alamat_Hta2") == null ? "": rs.getString("alamat_Hta2"));
				h.put("alamathta3", rs.getString("alamat_Hta3") == null ? "": rs.getString("alamat_Hta3"));
				h.put("poskodhta", rs.getString("poskod_Hta") == null ? "" : rs.getString("poskod_Hta"));
				h.put("bandarhta", rs.getString("id_bandarhta") == null ? "": rs.getString("id_bandarhta"));
				h.put("bandar", rs.getString("id_bandarhta") == null ? "": rs.getString("id_bandarhta"));
				listHarta.addElement(h);
										
			}
			
		}catch (Exception re) {
				myLog.error("Error: ", re);
				//throw re;
		} finally {
			if (db != null)
				db.close();
		}
		myLog.info("***GET BY ID getDataHTAX :h=" + h);
		return h;
				
	}

	public Vector<Hashtable<String,String>> getDataHTA(String idPerSimati,String jenisHarta) throws Exception {
		Db db = null;
		listHarta.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT H.ID_HTA" +
				" ,H.NO_HAKMILIK, H.NO_PT" +
				" ,H.ID_NEGERI" +
				" ,H.BA_SIMATI, H.BB_SIMATI "+
				" ,RN.NAMA_NEGERI,RD.NAMA_DAERAH,RM.NAMA_MUKIM" +
				" ,RUJ.KOD_JENIS_HAKMILIK,RUJ.KETERANGAN  " +
				" ,PS.ID_PERMOHONAN "+
				//", H.ALAMAT_HTA1, H.ALAMAT_HTA2, H.ALAMAT_HTA3" +
				//" , H.ID_JENISHM, H.ID_JENISPB, H.ID_DAERAH, H.ID_LUAS, H.ID_BANDARHTA, H.ID_MUKIM, H.LUAS_HMP,"+
				//" ,H.ID_SIMATI, H.NILAI_HTA_TARIKHMOHON, H.NILAI_HTA_TARIKHMATI,H.ID_KATEGORI "+
				//" H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, H.JENIS_TNH, H.JENIS_HTA, H.TANGGUNGAN," +
				//" H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN " +
				" FROM TBLPPKHTA H, TBLPPKPERMOHONANSIMATI PS, TBLRUJJENISHAKMILIK RUJ "+
				", TBLRUJNEGERI RN,TBLRUJDAERAH RD,TBLRUJMUKIM RM "+
				" WHERE H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) "+
				" AND H.ID_NEGERI=RN.ID_NEGERI "+
				" AND H.ID_DAERAH=RD.ID_DAERAH "+
				" AND H.ID_MUKIM = RM.ID_MUKIM " +
				" AND PS.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI "+
				" AND H.ID_PERMOHONANSIMATI = '"+ idPerSimati+ "' "+
				" AND H.JENIS_HTA = '"+jenisHarta+"' " + //Y-Ada hakmilik, T-Tiada Hakmilik
				" ORDER BY H.ID_HTA DESC ";
			//
			myLog.info("getDataHTA:sql=" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			int i = 0;
			while (rs.next()) {			    
				h = new Hashtable<String,String>();
				//h.put("alamathta1", rs.getString("alamat_hta1") == null ? "" : rs.getString("alamat_hta1"));
				//h.put("alamathta2", rs.getString("alamat_hta2") == null ? "" : rs.getString("alamat_hta2"));
				//h.put("alamathta3", rs.getString("alamat_hta3") == null ? "" : rs.getString("alamat_hta3"));
				//h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs.getString("id_Simati"));
//				h.put("nilai_Hta_memohon", String.valueOf(rs.getString("nilai_Hta_Tarikhmohon") == null 
//					? "" : rs.getDouble("nilai_Hta_Tarikhmohon")));
//				h.put("nilai_Hta_mati",String.valueOf(rs.getString("nilai_Hta_Tarikhmati") == null 
//					? "" : rs.getDouble("nilai_Hta_Tarikhmati")));
//				h.put("pemilikan", rs.getString("id_Jenispb") == null ? "" : rs.getString("id_Jenispb"));
//				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs.getString("jenis_Hta"));				
//				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs.getString("jenis_Tnh"));			
//				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? "": rs.getString("id_Jenishm"));
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs.getString("id_Hta"));		
				h.put("kod_hakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK"));				
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? "": rs.getString("no_Hakmilik"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs.getString("no_Pt"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs.getString("ba_Simati"));				
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs.getString("bb_Simati"));				
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "": rs.getString("KETERANGAN"));				
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri")); 
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());
//				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs.getString("id_Kategori"));
//				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs.getString("id_Daerah"));
//				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs.getString("id_Mukim"));				
//				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs.getString("luas_Hmp"));				
//				h.put("luasasal", rs.getString("luas") == null ? "" : rs.getString("luas"));				
//				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs.getString("no_Cagaran"));				
//				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs.getString("no_Pajakan"));				
//				h.put("tanggungan", rs.getString("tanggungan") == null ? "": rs.getString("tanggungan"));				
//				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs.getString("id_Luas"));				
//				h.put("catatan", rs.getString("catatan") == null ? "" : rs.getString("catatan"));				
//				h.put("noperserahan", rs.getString("no_perserahan") == null ? "" : rs.getString("NO_PERSERAHAN"));				
				// ADD BY PEJE - ADD FIELD SEKATAN & SYARAT NYATA
				//h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());
				//h.put("syaratNyata", rs.getString("SYARAT_NYATA") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				//2018/05/24 Lampiran 
				ekptg.model.ppk.util.LampiranBean l = new ekptg.model.ppk.util.LampiranBean();
				h.put("lampirans", l.getLampirans(rs.getString("ID_HTA")));
				
				//2018/05/25 Tambah paparan negeri,daerah dan Mukim 
				h.put("namaNegeri", Utils.isNull(rs.getString("NAMA_NEGERI")));
				h.put("namaDaerah", Utils.isNull(rs.getString("NAMA_DAERAH")));
				h.put("namaMukim", Utils.isNull(rs.getString("NAMA_MUKIM")));
				
				i = i+1;
			    listHarta.addElement(h);
				
			}
		} finally {
			if (db != null)
				db.close();
		}
		return listHarta;
		
	}
	//setDataHTAbyIdHtaam
	public Hashtable<String,String> getDataHTAbyIdHtaam(String idhtaam, String idPermohonanSimati) throws Exception {
		Db db = null;
		listHarta.clear();
		Hashtable<String,String> h = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT H.FLAG_DAFTAR,H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT" +
					",NVL(LTRIM(to_char(H.NILAI_HTA_TARIKHMOHON,'99999990.00')),'0.00') NILAI_HTA_TARIKHMOHON" +
					",NVL(LTRIM(to_char(H.NILAI_HTA_TARIKHMATI,'99999990.00')),'0.00') NILAI_HTA_TARIKHMATI "+
					",NVL(H.ID_KATEGORI,-0) ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB" +
					",H.ID_NEGERI,H.ID_DAERAH, H.ID_MUKIM"+
					",NVL(H.ID_LUAS,-0) ID_LUAS, H.LUAS_HMP, H.LUAS, H.NO_CAGARAN"+
					",H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN"+ 
					",H.ALAMAT_HTA1, H.ALAMAT_HTA2, H.ALAMAT_HTA3, H.BANDAR_HTA, H.POSKOD_HTA, NVL(H.ID_BANDARHTA,-0) ID_BANDARHTA"+
					",H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN"+
					",H.SEKATAN, H.SYARAT_NYATA"+
					//HTATH
					",H.NAMA_PEMAJU"+
					",H.ALAMAT_PEMAJU1, H.ALAMAT_PEMAJU2, H.ALAMAT_PEMAJU3, H.POSKOD_PEMAJU"+
					",H.BANDAR_PEMAJU, H.ID_BANDARPEMAJU, H.ID_NEGERIPEMAJU"+
					",H.NO_PERJANJIAN, H.TARIKH_PERJANJIAN, H.NAMA_RANCANGAN, H.NO_ROH, H.NO_LOT_ID"+
					",H.FLAG_KATEGORI_HTA, H.JENIS_KEPENTINGAN  "+
					"FROM "+
					"TBLPPKHTAPERMOHONAN H "+
					//"TBLPPKHTA H " 
					//+", TBLPPKSIMATI S"
					" WHERE "+
					//"H.ID_SIMATI = S.ID_SIMATI"
					//+ " AND HP.ID_HTA = H.ID_HTA " + " AND PELAN.ID_HTA (+) = H.ID_HTA  "
					//+ " AND DOKUMEN.ID_DOKUMEN (+)= PELAN.ID_DOKUMEN  "
					// " AND H.ID_PERMOHONANSIMATI = HP.ID_PERMOHONANSIMATI " +
					//+ " AND H.JENIS_HTA = 'Y'  " +
					" H.ID_HTA = '"+ idhtaam+ "'"+
					" AND H.ID_PERMOHONANSIMATI = '"+ idPermohonanSimati+ "'"+
					" ";
			myLog.info("getDataHTAbyIdHtaam:sql="+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("FLAG_DAFTAR", rs.getString("FLAG_DAFTAR") == null ? "": rs.getString("FLAG_DAFTAR"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "0" : rs.getString("id_Simati"));
				
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs.getString("id_Hta"));
				//h.put("nilai_Hta_memohon",lebah.util.Util.formatDecimal(rs.getDouble("nilai_Hta_Tarikhmohon")));
				//h.put("nilai_Hta_mati",lebah.util.Util.formatDecimal(rs.getDouble("nilai_Hta_Tarikhmati")));
				h.put("nilai_Hta_mati",rs.getString("nilai_Hta_Tarikhmati"));
				h.put("nilai_Hta_memohon",rs.getString("nilai_Hta_Tarikhmohon"));
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? "": rs.getString("no_Hakmilik"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs.getString("no_Pt"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? "": rs.getString("tanggungan"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs.getString("catatan"));
				h.put("noperserahan", rs.getString("NO_PERSERAHAN") == null ? "" : rs.getString("NO_PERSERAHAN"));				
				// ADD BY SALNIZAM - ADD FIELD ALAMAT
				h.put("alamat1", rs.getString("alamat_Hta1") == null ? "" : rs.getString("alamat_Hta1"));
				h.put("alamat2", rs.getString("alamat_Hta2") == null ? "" : rs.getString("alamat_Hta2"));
				h.put("alamat3", rs.getString("alamat_Hta3") == null ? "" : rs.getString("alamat_Hta3"));
				h.put("poskod", rs.getString("poskod_hta") == null ? "" : rs.getString("poskod_hta"));		
				//h.put("id_bandarhta", rs.getString("id_bandarhta") == null ? "" : rs.getString("id_bandarhta"));	
				// ADD BY PEJE - ADD FIELD SEKATAN & SYARAT NYATA
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());
				h.put("syaratNyata", rs.getString("SYARAT_NYATA") == null ? "": rs.getString("SYARAT_NYATA").toUpperCase());
				//2018 HTATH
				h.put("namapemaju", rs.getString("nama_Pemaju") == null ? "" : rs.getString("nama_Pemaju"));
				h.put("alamatpemaju1",rs.getString("alamat_Pemaju1") == null ? "" : rs.getString("alamat_Pemaju1"));
				h.put("alamatpemaju2",rs.getString("alamat_Pemaju2") == null ? "" : rs.getString("alamat_Pemaju2"));
				h.put("alamatpemaju3",rs.getString("alamat_Pemaju3") == null ? "" : rs.getString("alamat_Pemaju3"));
				h.put("poskodpemaju",rs.getString("poskod_Pemaju") == null ? "" : rs.getString("poskod_Pemaju"));
				h.put("bandarpemaju",rs.getString("id_bandarpemaju") == null ? "0" : rs.getString("id_bandarpemaju"));
				h.put("negeripemaju",rs.getString("id_Negeripemaju") == null ? "0" : rs.getString("id_Negeripemaju"));
				// mula sama
				h.put("alamathta1", rs.getString("alamat_Hta1") == null ? "": rs.getString("alamat_Hta1"));
				h.put("alamathta2", rs.getString("alamat_Hta2") == null ? "": rs.getString("alamat_Hta2"));
				h.put("alamathta3", rs.getString("alamat_Hta3") == null ? "": rs.getString("alamat_Hta3"));
				h.put("poskodhta", rs.getString("poskod_Hta") == null ? "" : rs.getString("poskod_Hta"));
				h.put("bandarhta", rs.getString("id_bandarhta") == null ? "0": rs.getString("id_bandarhta"));
				// tamat sama
				h.put("noperjanjian",rs.getString("no_Perjanjian") == null ? "" : rs.getString("no_Perjanjian"));
				h.put("tarikhperjanjian"
					,rs.getString("tarikh_Perjanjian") == null ? "": sdf.format(rs.getDate("tarikh_Perjanjian")));
				h.put("namarancangan",rs.getString("nama_Rancangan") == null ? "" : rs.getString("nama_Rancangan"));
				h.put("noroh", rs.getString("no_Roh") == null ? "" : rs.getString("no_Roh"));
				h.put("nolot", rs.getString("no_Lot_Id") == null ? "" : rs.getString("no_Lot_Id"));
				h.put("jeniskepentingan",rs.getString("jenis_Kepentingan") == null ? "" : rs.getString("jenis_Kepentingan"));
				h.put("flag", rs.getString("FLAG_KATEGORI_HTA") == null ? "" : rs.getString("FLAG_KATEGORI_HTA"));
				
				//Kegunaan Pilihan
				//HTA
				h.put("bandar", rs.getString("id_bandarhta") == null ? "0" : rs.getString("id_bandarhta"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "0" : rs.getString("id_Daerah"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? "-1": rs.getString("id_Jenishm"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "-1" : rs.getString("id_Luas"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "-1" : rs.getString("id_Kategori"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "-1" : rs.getString("id_Mukim"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "0" : rs.getString("id_Negeri"));
				h.put("pemilikan",rs.getString("status_Pemilikan") == null ? "-1" : rs.getString("status_Pemilikan"));
				
				//HTATH
				h.put("negeriAdd",rs.getString("id_Negeripemaju") == null ? "0" : rs.getString("id_Negeripemaju"));
				h.put("bandarAdd", rs.getString("id_bandarpemaju") == null ? "0": rs.getString("id_bandarpemaju"));

				listHarta.addElement(h);
				
			}

		}catch (Exception re) {
			myLog.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
		return h;

	}
	
	public Vector<Hashtable<String,String>> getDataHTA() {
		return listHarta;
	}

	public Vector<Hashtable<String,String>> getDataHTAX(String idPerSimati) throws Exception {
		Db db = null;
		listHTAX.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT H.ID_HTA" +
				" ,H.ALAMAT_HTA1,H.ALAMAT_HTA2,H.ALAMAT_HTA3, H.POSKOD_HTA, H.BANDAR_HTA "+
				" ,H.NO_ROH"+
				" ,H.BA_SIMATI, H.BB_SIMATI "+
				" ,H.FLAG_KATEGORI_HTA,H.JENIS_KEPENTINGAN "+
				" ,RN.NAMA_NEGERI,RD.NAMA_DAERAH,RM.NAMA_MUKIM" +
				" ,PS.ID_PERMOHONAN "+
				" FROM TBLPPKHTA H, TBLPPKPERMOHONANSIMATI PS "+
				" ,TBLRUJNEGERI RN,TBLRUJDAERAH RD,TBLRUJMUKIM RM "+
				" WHERE PS.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI "+
				" AND H.ID_NEGERI=RN.ID_NEGERI "+
				" AND H.ID_DAERAH=RD.ID_DAERAH "+
				" AND H.ID_MUKIM = RM.ID_MUKIM " +
				" AND H.ID_PERMOHONANSIMATI = '"+ idPerSimati+ "' "+
				" AND H.JENIS_HTA = 'T'  " +
				" ORDER BY H.ID_HTA DESC ";
			myLog.info("getDataHTAX:sql=" + sql.toUpperCase());
			//System.out.println("getDataHTAX:sql=" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable<String,String> h;

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs.getString("id_Hta"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs.getString("bb_Simati"));
				// ADD BY SALNIZAM - ADD FIELD ALAMAT
				h.put("alamat1", rs.getString("ALAMAT_HTA1") == null ? "" : rs.getString("ALAMAT_HTA1"));
				h.put("alamat2", rs.getString("ALAMAT_HTA2") == null ? "" : rs.getString("ALAMAT_HTA2"));
				h.put("alamat3", rs.getString("ALAMAT_HTA3") == null ? "" : rs.getString("ALAMAT_HTA3"));
				h.put("poskod", rs.getString("POSKOD_HTA") == null ? "" : rs.getString("POSKOD_HTA"));
				h.put("bandar", Utils.isNull(rs.getString("BANDAR_HTA")));
				h.put("noroh", rs.getString("no_Roh") == null ? "" : rs.getString("no_Roh"));
				h.put("jenis_penting",rs.getString("JENIS_KEPENTINGAN") == null ? "" : rs.getString("JENIS_KEPENTINGAN"));
				h.put("kategori_hta",rs.getString("FLAG_KATEGORI_HTA") == null ? "" : rs.getString("FLAG_KATEGORI_HTA"));
				
				//2018/05/25 Tambah paparan negeri,daerah dan Mukim 
				h.put("namaNegeri", Utils.isNull(rs.getString("NAMA_NEGERI")));
				h.put("namaDaerah", Utils.isNull(rs.getString("NAMA_DAERAH")));
				h.put("namaMukim", Utils.isNull(rs.getString("NAMA_MUKIM")));
				
				//2018/05/24 Lampiran 
				ekptg.model.ppk.util.LampiranBean l = new ekptg.model.ppk.util.LampiranBean();
				h.put("lampirans", Utils.isNull(l.getLampirans(rs.getString("ID_HTA"))));
				listHTAX.addElement(h);

			}
			
		}catch (Exception re) {
			myLog.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
		return listHTAX;
		
	} 
	public Vector<Hashtable<String,String>> getDataHTA_(String idsimati) throws Exception {
		Db db = null;
		listHTA.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT H.ID_HTA, H.ALAMAT_HTA1, H.ALAMAT_HTA2, H.ALAMAT_HTA3, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, H.NILAI_HTA_TARIKHMATI, "
					+ " H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, H.ID_DAERAH, H.ID_LUAS, H.ID_BANDARHTA, H.ID_MUKIM, H.LUAS_HMP,"
					+ " H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN, "
					+ " H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN,RUJ.KOD_JENIS_HAKMILIK,RUJ.KETERANGAN,  "

					// ADD BY PEJE - ADD FIELD SEKATAN & SYARAT NYATA
					+ " H.SEKATAN, H.SYARAT_NYATA  , MS.ID_PERMOHONAN  " +
					",DOKUMEN.ID_DOKUMEN , DOKUMEN.NAMA_DOKUMEN ,PELAN.ID_PELAN"
					+ " FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI MS, TBLRUJJENISHAKMILIK RUJ "
					+ ", TBLPPKUPLOADPELAN PELAN , TBLPPKDOKUMEN DOKUMEN  " //IL
					+ " WHERE H.ID_SIMATI = S.ID_SIMATI "
					+ " AND HP.ID_HTA = H.ID_HTA AND HP.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI  "
					+ " AND H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) "
					+ " AND H.ID_SIMATI = MS.ID_SIMATI "
					+ " AND MS.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI "
					+ " AND PELAN.ID_HTA (+) = H.ID_HTA " //IL
					+ " AND DOKUMEN.ID_DOKUMEN (+)= PELAN.ID_DOKUMEN " //IL
					+ " AND MS.ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "'  "
					+ " AND H.JENIS_HTA = 'Y'  ORDER BY H.ID_HTA DESC";

			// System.out.println("HTAAM :"+sql.toUpperCase());
			//System.out.println("******SQL :"+sql);
			myLog.info("HTAAM STRUCTURE BARU" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			//
			Hashtable<String,String> h;
			int i = 0;
			while (rs.next()) {
			    
				h = new Hashtable<String,String>();
				//System.out.println("Try kat sini " + i);
				h.put("alamathta1", rs.getString("alamat_hta1") == null ? "" : rs
						//.getString("alamat_Hta1"));
						.getString("alamat_hta1"));
				h.put("alamathta2", rs.getString("alamat_hta2") == null ? "" : rs
						//.getString("alamat_Hta1"));
						.getString("alamat_hta2"));
				h.put("alamathta3", rs.getString("alamat_hta3") == null ? "" : rs
						//.getString("alamat_Hta1"));
						.getString("alamat_hta3"));
				//System.out.println("Alamat1 " + rs.getString("alamat_hta1"));
				//System.out.println("Alamat2 " + rs.getString("alamat_hta2"));
				//System.out.println("Try kat sini");
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs.getString("id_Hta"));		
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idDokumen", rs.getString("id_dokumen") == null ? "" : rs.getString("id_dokumen"));//IL
				h.put("idPelan", rs.getString("id_pelan") == null ? "" : rs.getString("id_pelan"));//IL		
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("nilai_Hta_memohon", String.valueOf(rs.getString("nilai_Hta_Tarikhmohon") == null 
					? "" : rs.getDouble("nilai_Hta_Tarikhmohon")));
				h.put("nilai_Hta_mati",String.valueOf(rs.getString("nilai_Hta_Tarikhmati") == null 
					? "" : rs.getDouble("nilai_Hta_Tarikhmati")));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb") == null ? "" : rs
						.getString("id_Jenispb"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs
						.getString("luas_Hmp"));
				
				h.put("luasasal", rs.getString("luas") == null ? "" : rs
						.getString("luas"));
				
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs
						.getString("no_Cagaran"));
				
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs
						.getString("no_Pajakan"));
				
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs
						.getString("jenis_Tnh"));
				
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs
						.getString("jenis_Hta"));
				
				h.put("tanggungan", rs.getString("tanggungan") == null ? ""
						: rs.getString("tanggungan"));
				
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs
						.getString("id_Luas"));
				
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				
				h.put("noperserahan", rs.getString("no_perserahan") == null ? "" : rs
								.getString("NO_PERSERAHAN"));
				
				h.put("kod_hakmilik",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				
				h.put("keterangan", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));

				// ADD BY PEJE - ADD FIELD SEKATAN & SYARAT NYATA
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());
				h.put("syaratNyata", rs.getString("SYARAT_NYATA") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());

			    myLog.info("getDataHTA:h="+h);
			   // h.put("alamathta2","hta 2");
			    i = i+1;
				listHTA.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return listHTA;
	}

//IL end
}
