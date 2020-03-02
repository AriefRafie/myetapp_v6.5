
package ekptg.model.ppk.harta;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8InternalData;
import ekptg.model.utils.Fungsi;

public class FrmPermohonanHAData extends FrmPrmhnnSek8InternalData{
	static Logger myLog = Logger.getLogger(ekptg.model.ppk.harta.FrmPermohonanHAData.class);
	
	private Vector<Hashtable<String,String>> listHTAX = new Vector<Hashtable<String,String>>();
	//private Vector<Hashtable<String,String>> listHTA = new Vector<Hashtable<String,String>>();
	private Vector<Hashtable<String,String>> listHarta = new Vector<Hashtable<String,String>>();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void addHA(Hashtable hashData,HttpServletRequest request,Fungsi fnc,FrmPrmhnnSek8DaftarSek8InternalData logic_A) 
		throws Exception {
		String id = fnc.getParam(request,"id");
		String id1 =fnc.getParam(request,"idSimati");
		String socJenisHartaAlih =fnc.getParam(request,"socJenisHartaAlih");
		String txtBhgnSimati1 =fnc.getParam(request,"txtBhgnSimati1");
		String txtBhgnSimati2 =fnc.getParam(request,"txtBhgnSimati2");
		String txtNoRujukan =fnc.getParam(request,"txtNoRujukan");
		String txtNilaiTarikhMati =fnc.getParam(request,"txtNilaiTarikhMati");
		String txtNoSijil =fnc.getParam(request,"txtNoSijil");
		String txtNilaiTarikhMohon =fnc.getParam(request,"txtNilaiTarikhMohon");
		String txtBilUnit =fnc.getParam(request,"txtBilUnit");
		String txtNilaiSeunit =fnc.getParam(request,"txtNilaiSeunit");
		String txtAgensi =fnc.getParam(request,"txtAgensi");
		String txtCatatan =fnc.getParam(request,"txtCatatan");
		String socNegeriHtaam =fnc.getParam(request,"socNegeriHtaam");
		String socDaerahHtaam =fnc.getParam(request,"socDaerahHtaam");
		String bil =fnc.getParam(request,"bil");
		String mati =fnc.getParam(request,"id_Permohonansimati");

		String txtAlamat1 =fnc.getParam(request,"txtAlamat1");
		String nama_saham =fnc.getParam(request,"nama_saham");
		String txtAlamat2 =fnc.getParam(request,"txtAlamat2");
		String txtAlamat3 =fnc.getParam(request,"txtAlamat3");
		String txtPoskod =fnc.getParam(request,"txtPoskod");

		Hashtable h = null;
		h = new Hashtable();
		h.put("FLAG_DAFTAR",fnc.getParam(request,"FLAG_DAFTAR"));
		h.put("id", id);
		h.put("id1", id1);
		h.put("id_Permohonansimati", mati);
		h.put("nama_saham", nama_saham);
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
		h.put("butiran",fnc.getParam(request,"butiran"));

		h.put("socNegeriHtaam", socNegeriHtaam);
		h.put("socDaerahHtaam", socDaerahHtaam);
		h.put("id_Masuk", String.valueOf(hashData.get("idUser")));

		logic_A.addHa(h);
		
	}
	public void updateHA(HttpSession session,HttpServletRequest request,Fungsi fnc,FrmPrmhnnSek8DaftarSek8InternalData logic_A) 
		throws Exception {
		String id1 =fnc.getParam(request,"idSimati");
		String id3 =fnc.getParam(request,"idha");
		String socJenisHartaAlih =fnc.getParam(request,"socJenisHartaAlih");
		String txtBhgnSimati1 =fnc.getParam(request,"txtBhgnSimati1");
		String txtBhgnSimati2 =fnc.getParam(request,"txtBhgnSimati2");
		String txtNoRujukan =fnc.getParam(request,"txtNoRujukan");
		String txtNilaiTarikhMati =fnc.getParam(request,"txtNilaiTarikhMati");
		String txtNoSijil =fnc.getParam(request,"txtNoSijil");
		String txtNilaiTarikhMohon =fnc.getParam(request,"txtNilaiTarikhMohon");
		String txtBilUnit =fnc.getParam(request,"txtBilUnit");
		String txtNilaiSeunit =fnc.getParam(request,"txtNilaiSeunit");
		String Agensi =fnc.getParam(request,"txtAgensi");
		String txtCatatan =fnc.getParam(request,"txtCatatan");
		String bil =fnc.getParam(request,"bil");
		String txtAlamat1 =fnc.getParam(request,"txtAlamat1");
		String txtAlamat2 =fnc.getParam(request,"txtAlamat2");
		String txtAlamat3 =fnc.getParam(request,"txtAlamat3");
		String txtPoskod =fnc.getParam(request,"txtPoskod");
		String nama_saham =fnc.getParam(request,"nama_saham");

		String socDaerahHtaam =fnc.getParam(request,"socDaerahHtaam");
		String socNegeriHtaam =fnc.getParam(request,"socNegeriHtaam");

		Hashtable h = null;
		h = new Hashtable();
		h.put("FLAG_DAFTAR",fnc.getParam(request,"FLAG_DAFTAR"));
		h.put("id_Permohonansimati",fnc.getParam(request,"id_Permohonansimati"));
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
		h.put("nama_saham", nama_saham);
		h.put("txtCatatan", txtCatatan);
		h.put("bil", bil);
		h.put("txtAlamat1", txtAlamat1);
		h.put("txtAlamat2", txtAlamat2);
		h.put("txtAlamat3", txtAlamat3);
		h.put("txtPoskod", txtPoskod);
		h.put("butiran",fnc.getParam(request,"butiran"));
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));

		if (socNegeriHtaam != "" && socNegeriHtaam != "0") {
			h.put("socDaerahHtaam", socDaerahHtaam);
		} else {
			h.put("socDaerahHtaam", "0");
		}

		h.put("socNegeriHtaam", socNegeriHtaam);
		logic_A.kemaskiniHa(h);
		
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
				h.put("bandarAdd",rs.getString("id_bandarpemaju") == null ? "" : rs.getString("id_bandarpemaju"));
				h.put("negeripemaju",rs.getString("id_Negeripemaju") == null ? "" : rs.getString("id_Negeripemaju"));
				h.put("negeriAdd",rs.getString("id_Negeripemaju") == null ? "" : rs.getString("id_Negeripemaju"));
				
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

	public Vector<Hashtable<String,String>> getHarta(String idPermohonanSimati) throws Exception {
		Db db = null;
		listHarta.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT DISTINCT H.ID_HA,H.BIL"+
				" ,H.ID_SIMATI, H.ID_JENISHA, H.NO_DAFTAR"+
				" ,H.NILAI_HA_TARIKHMOHON, H.NILAI_HA_TARIKHMATI, H.NO_SIJIL, J.KOD, H.BUTIRAN,H.NAMA_SAHAM "+
				" ,J.KETERANGAN, H.ALAMAT_HA1, H.ALAMAT_HA2, H.ALAMAT_HA3, H.POSKOD, H.JENAMA "+
				" FROM TBLPPKHA H1,TBLPPKHAPERMOHONAN H, TBLPPKRUJJENISHA J, TBLPPKPERMOHONANSIMATI M "+
				" WHERE H1.ID_HA = H.ID_HA "+
				" AND H.ID_PERMOHONANSIMATI = H1.ID_PERMOHONANSIMATI "+
				" AND H.ID_JENISHA = J.ID_JENISHA "+
				" AND H.ID_SIMATI = M.ID_SIMATI "+
				" AND H.ID_PERMOHONANSIMATI = '"+ idPermohonanSimati+ "' "+
				" ORDER BY H.ID_JENISHA,H.ID_HA DESC ";
			//
			myLog.info("getHarta:sql=" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			//
			Hashtable<String,String> h;
			int bil = 0;
			while (rs.next()) {			    
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("id_ha", rs.getString("ID_HA") == null ? "" : rs.getString("ID_HA"));	
				h.put("idha", rs.getString("ID_HA") == null ? "" : rs.getString("ID_HA"));	
				h.put("nilai_tm",
						rs.getString("NILAI_HA_TARIKHMOHON") == null ? "" : Utils.format2Decimal(rs.getDouble("NILAI_HA_TARIKHMOHON")));
				h.put("nilai_tarikhmati",
						rs.getString("NILAI_HA_TARIKHMATI") == null ? "" : Utils.format2Decimal(rs.getDouble("NILAI_HA_TARIKHMATI")));
				h.put("id_Jenisha", rs.getString("id_Jenisha") == null ? "": rs.getString("id_Jenisha"));
				h.put("id_Simati",rs.getString("id_Simati") == null ? "" : rs.getString("id_Simati"));
				h.put("nosijil",rs.getString("no_sijil") == null ? "" : rs.getString("no_sijil"));
				h.put("noDaftar",rs.getString("no_Daftar") == null ? "" : rs.getString("no_Daftar"));
				h.put("Kod",rs.getString("kod") == null ? "" : rs.getString("kod"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "": rs.getString("KETERANGAN"));				
				//h.put("alamathta1", rs.getString("alamat_hta1") == null ? "" : rs.getString("alamat_hta1"));
				//h.put("alamathta2", rs.getString("alamat_hta2") == null ? "" : rs.getString("alamat_hta2"));
				//h.put("alamathta3", rs.getString("alamat_hta3") == null ? "" : rs.getString("alamat_hta3"));

				
//				h.put("alamat1",
//						rs.getString("alamat_ha1") == null ? "" : rs
//								.getString("alamat_ha1"));
//				h.put("alamat2",
//						rs.getString("alamat_ha2") == null ? "" : rs
//								.getString("alamat_ha2"));
//				h.put("alamat3",
//						rs.getString("alamat_ha3") == null ? "" : rs
//								.getString("alamat_ha3"));
//				h.put("poskod",
//						rs.getString("poskod") == null ? "" : rs
//								.getString("poskod"));

				h.put("nama_saham", rs.getString("nama_saham") == null ? "": rs.getString("nama_saham"));
				h.put("jenama",rs.getString("jenama") == null ? "" : rs.getString("jenama"));
				h.put("butiran",rs.getString("butiran") == null ? "" : rs.getString("butiran"));
				
				ekptg.model.ppk.util.LampiranBean l = new ekptg.model.ppk.util.LampiranBean();
				h.put("lampirans", l.getLampiransHA(rs.getString("ID_HA")));				
				listHarta.addElement(h);
				bil= bil+1;
				
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
					", NVL(LTRIM(to_char(H.NILAI_HTA_TARIKHMOHON,'99999990.00')),'0.00') NILAI_HTA_TARIKHMOHON" +
					",NVL(LTRIM(to_char(H.NILAI_HTA_TARIKHMATI,'99999990.00')),'0.00') NILAI_HTA_TARIKHMATI "+
					" ,H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB" +
					", H.ID_NEGERI,H.ID_DAERAH, H.ID_MUKIM "+
					", H.ID_LUAS, H.LUAS_HMP, H.LUAS, H.NO_CAGARAN, "+
					"H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN"+ 
					",H.ALAMAT_HTA1, H.ALAMAT_HTA2, H.ALAMAT_HTA3, H.BANDAR_HTA, H.POSKOD_HTA, H.ID_BANDARHTA, "+
					"H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN  "+
					//+ ADD BY PEJE - ADD FIELD SEKATAN & SYARAT NYATA
					",H.SEKATAN, H.SYARAT_NYATA  "+
					//HTATH
					",H.NAMA_PEMAJU "+
					",H.ALAMAT_PEMAJU1, H.ALAMAT_PEMAJU2, H.ALAMAT_PEMAJU3, H.POSKOD_PEMAJU "+
					",H.BANDAR_PEMAJU, H.ID_BANDARPEMAJU, H.ID_NEGERIPEMAJU "+
					",H.NO_PERJANJIAN, H.TARIKH_PERJANJIAN, H.NAMA_RANCANGAN, H.NO_ROH, H.NO_LOT_ID "+
					",H.FLAG_KATEGORI_HTA, H.JENIS_KEPENTINGAN  "+
					"FROM TBLPPKHTAPERMOHONAN H "+
					//",TBLPPKHTA HP, TBLPPKSIMATI S"
					" WHERE "+
					//"H.ID_SIMATI = S.ID_SIMATI"
					//+ " AND HP.ID_HTA = H.ID_HTA " + " AND PELAN.ID_HTA (+) = H.ID_HTA  "
					//+ " AND DOKUMEN.ID_DOKUMEN (+)= PELAN.ID_DOKUMEN  "
					// " AND H.ID_PERMOHONANSIMATI = HP.ID_PERMOHONANSIMATI " +
					//+ " AND H.JENIS_HTA = 'Y'  " +
					" H.ID_HTA = '"+ idhtaam+ "'"+
					" AND H.ID_PERMOHONANSIMATI = '"+ idPermohonanSimati+ "'"+
					" ";
			
			//myLogger.info("getDataHTAbyIdHtaam:sql="+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("FLAG_DAFTAR", rs.getString("FLAG_DAFTAR") == null ? "": rs.getString("FLAG_DAFTAR"));
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs.getString("id_Hta"));
				//h.put("idDokumen", rs.getString("id_dokumen") == null ? "" : rs.getString("id_dokumen"));//IL
				//h.put("namaPelanUp", rs.getString("nama_dokumen") == null ? "" : rs.getString("nama_dokumen"));//IL
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? "": rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "0" : rs.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs.getString("no_Pt"));
				h.put("nilai_Hta_memohon",rs.getString("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",rs.getString("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "0" : rs.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? "0": rs.getString("id_Jenishm"));
				h.put("pemilikan",rs.getString("status_Pemilikan") == null ? "" : rs.getString("status_Pemilikan"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "0" : rs.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "0" : rs.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? "": rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "0" : rs.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs.getString("catatan"));
				h.put("noperserahan", rs.getString("NO_PERSERAHAN") == null ? "" : rs.getString("NO_PERSERAHAN"));				
				// ADD BY SALNIZAM - ADD FIELD ALAMAT
				h.put("alamat1", rs.getString("alamat_Hta1") == null ? "" : rs.getString("alamat_Hta1"));
				h.put("alamat2", rs.getString("alamat_Hta2") == null ? "" : rs.getString("alamat_Hta2"));
				h.put("alamat3", rs.getString("alamat_Hta3") == null ? "" : rs.getString("alamat_Hta3"));
				//System.out.println("Baca sini4##### " + rs.getString("id_bandarhta"));
				h.put("poskod", rs.getString("poskod_hta") == null ? "" : rs.getString("poskod_hta"));		
				h.put("bandar", rs.getString("id_bandarhta") == null ? "0" : rs.getString("id_bandarhta"));
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
				h.put("alamathta1", rs.getString("alamat_Hta1") == null ? "": rs.getString("alamat_Hta1"));
				h.put("alamathta2", rs.getString("alamat_Hta2") == null ? "": rs.getString("alamat_Hta2"));
				h.put("alamathta3", rs.getString("alamat_Hta3") == null ? "": rs.getString("alamat_Hta3"));
				h.put("poskodhta", rs.getString("poskod_Hta") == null ? "" : rs.getString("poskod_Hta"));
				h.put("bandarhta", rs.getString("id_bandarhta") == null ? "0": rs.getString("id_bandarhta"));
				h.put("noperjanjian",rs.getString("no_Perjanjian") == null ? "" : rs.getString("no_Perjanjian"));
				h.put("tarikhperjanjian"
					,rs.getString("tarikh_Perjanjian") == null ? "": sdf.format(rs.getDate("tarikh_Perjanjian")));
				h.put("namarancangan",rs.getString("nama_Rancangan") == null ? "" : rs.getString("nama_Rancangan"));
				h.put("noroh", rs.getString("no_Roh") == null ? "" : rs.getString("no_Roh"));
				h.put("nolot", rs.getString("no_Lot_Id") == null ? "" : rs.getString("no_Lot_Id"));
				h.put("jeniskepentingan",rs.getString("jenis_Kepentingan") == null ? "" : rs.getString("jenis_Kepentingan"));
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
	
	public Vector<Hashtable<String,String>> getDataHA() {
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
				" FROM TBLPPKHTAPERMOHONAN H, TBLPPKPERMOHONANSIMATI PS "+
				" ,TBLRUJNEGERI RN,TBLRUJDAERAH RD,TBLRUJMUKIM RM "+
				" WHERE PS.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI "+
				" AND H.ID_NEGERI=RN.ID_NEGERI "+
				" AND H.ID_DAERAH=RD.ID_DAERAH "+
				" AND H.ID_MUKIM = RM.ID_MUKIM " +
				" AND H.ID_PERMOHONANSIMATI = '"+ idPerSimati+ "' "+
				" AND H.JENIS_HTA = 'T'  " +
				" ORDER BY H.ID_HTA DESC ";
			myLog.info("getDataHTAX:sql=" + sql.toUpperCase());
			System.out.println("getDataHTAX:sql=" + sql.toUpperCase());
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

	
}
