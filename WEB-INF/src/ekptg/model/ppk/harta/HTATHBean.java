package ekptg.model.ppk.harta;

import java.sql.Connection;
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
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8SenaraiSemakInternalData;
import ekptg.model.ppk.UtilHTML;
import ekptg.model.ppk.harta.FrmPermohonanHTAData;
import ekptg.model.utils.Fungsi;
import ekptg.model.utils.IUtilHTMLPilihanExt;
import ekptg.model.utils.rujukan.UtilHTMLPilihanJenisPBPPK;


public class HTATHBean extends EkptgCache implements IMaklumatHarta {
	
	private static Logger myLog = Logger.getLogger(ekptg.model.ppk.harta.HTATHBean.class);
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	FrmPrmhnnSek8DaftarSek8InternalData logic_A = null; 
	FrmHeaderPpk mainheader = null;

	Vector senaraiHTA = null;
	String NOW = "now";
	String READMODE = "readmode";
	String READMODED = "disabled";
	String YES = "yes";
	Date date = new Date();
	String currentDate = dateFormat.format(date);
	
	String idBandarStr = "0";
	String idNegeriStr = "0";
	String idaerahStr = "0";
	String idMukimStr = "0";
	String idJenisHakmilik =  "0";
	String idKategoriTanah =  "0";
	String idStatusPemilikan =  "0";
	String idJenisLuas = "0";

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
	FrmPermohonanHTAData permohonanInternal = null;
	Hashtable<String,String> mh = null;
	private UtilHTMLPilihanJenisPBPPK jenisPB = null;

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

	public String addHtaamX(Hashtable<String,String> data, String mode) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		myLog.info("addHtaamX:data="+data);
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
			//String id_Masuk = (String) data.get("id_Masuk");
			String idUser = (String) data.get("idUser");
			//String tarikh_Masuk = (String) data.get("tarikh_Masuk");
			String FLAG_DAFTAR = (String) data.get("FLAG_DAFTAR");
			
			// values kemaskini
			//String nopt = (String) data.get("nopt");
			//int jenishakmilik = (Integer) data.get("jenishakmilik");		

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(mode.equals("simpan")){
				//String id_hta = String.valueOf(DB.getNextID(db, "TBLPPKHTA_SEQ"));
				idhtaamid = String.valueOf(DB.getNextID(db, "TBLPPKHTA_SEQ"));
				r.add("id_hta", r.unquote(idhtaamid));

			}else{
				String idhta = String.valueOf(data.get("idhta"));
				idhtaamid = idhta;
				r.update("id_Hta", idhta);
			}
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
			
			String jenis_Hta = "T";
			if(!mode.equals("simpan")){
				jenis_Hta = (String) data.get("jenis_Hta");
			}
			r.add("jenis_hta", jenis_Hta);
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
			if(!tarikhperja.equals("")){
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
			r.add("flag_daftar", FLAG_DAFTAR);

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
			myLog.info("tblppkhta:sql="+sql);
			stmt.executeUpdate(sql);

			r.clear();
			
			// TBLPPKHTAPERMOHONAN			
			if(mode.equals("simpan")){
				r.add("id_hta", r.unquote(idhtaamid));
				r.add("id_permohonansimati", id_Permohonansimati);
				
			}else{
				String idhta = String.valueOf(data.get("idhta"));
				r.update("id_hta", r.unquote(idhta));
				r.update("id_permohonansimati", id_Permohonansimati);
				
			}
			//r.add("id_hta", r.unquote(idhtaamid));
			r.add("id_simati", r.unquote(idsimati));
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
			
			if(!mode.equals("simpan")){
				jenis_Hta = (String) data.get("jenis_Hta");
			}
			r.add("jenis_hta", jenis_Hta);
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
			if(!tarikhperja.equals("")){
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
			r.add("flag_daftar", FLAG_DAFTAR);
			
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

	public void addHtaamX(Hashtable<String,String> data
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
		//h.put("id_Masuk", String.valueOf(data.get("idUser")));
		h.put("idUser", String.valueOf(data.get("idUser")));
		h.put("tarikh_Masuk", currentDate);
		myLog.info("addHtaamX:h="+h);
		//logicInternal.addHtaamX(h);
		addHtaamX(h,"simpan");

	}
	
	private void setSocHTATH(Hashtable hParam,org.apache.velocity.VelocityContext context) throws Exception  {
		Hashtable b = setSocParamValues(String.valueOf(hParam.get("socNegeriHtaamX")),String.valueOf(hParam.get("socNegeriPemajuHtaamX"))
				,String.valueOf(hParam.get("socDaerahHtaamX")),String.valueOf(hParam.get("socDaerahHtaamX"))
				,String.valueOf(hParam.get("socMukimHtaamX"))
				,String.valueOf(hParam.get("txtBandarHartaHtaamX"))
				,String.valueOf(hParam.get("txtBandarPemaju1HtaamX"))
				,String.valueOf(hParam.get("socKategoriTanahHtaamX"))
				,String.valueOf(hParam.get("socJenisLuasHtaamX"))
				,String.valueOf(hParam.get("socStatusPemilikanHtaamX")));
		
		setSocValues(b
			,"socNegeriHtaamX","negerichangeX"
			,"socNegeriPemajuHtaamX","negerichangepemajuX"
			,"socDaerahHtaamX","daerahchangeX"
			,"socMukimHtaamX"
			,"txtBandarHartaHtaamX",""
			,"txtBandarPemaju1HtaamX",""
			,"socKategoriTanahHtaamX"
			,"socJenisLuasHtaamX"
			,"socStatusPemilikanHtaamX"
			,context
			);

	}
	//private void setSocHarta(Hashtable hParam,org.apache.velocity.VelocityContext context) 
	private void setSocHarta(Hashtable hParam,org.apache.velocity.VelocityContext context,String jenisSoc) 
		throws Exception  {
		Hashtable b = setSocParamValues(String.valueOf(hParam.get("socNegeri")),String.valueOf(hParam.get("socNegeriAlamat"))
				,String.valueOf(hParam.get("socDaerah")),String.valueOf(hParam.get("socDaerahAlamat"))
				,String.valueOf(hParam.get("socMukim"))
				,String.valueOf(hParam.get("socBandar"))
				,String.valueOf(hParam.get("socBandarAlamat"))
				,String.valueOf(hParam.get("socKategori"))
				,String.valueOf(hParam.get("socJenisLuas"))
				,String.valueOf(hParam.get("socStatusPemilikan")));
		
		if(jenisSoc.equals("readonly")){
			setSocValues(b
				,"socNegeriHtaamX","negerichangeupX"
				,"socNegeriPemajuHtaamX","negerichangepemajuupX"
				,"socDaerahHtaamX","daerahchangeupX"
				,"socMukimHtaamX"
				,"txtBandarHartaHtaamX",""
				,"txtBandarPemaju1HtaamX",""
				,"socKategoriTanahHtaamX"
				,"socJenisLuasHtaamX"
				,"socStatusPemilikanHtaamX"
				,context,""
				);
		}else if(jenisSoc.equals("kemaskini")){
			READMODED = "";
			setSocValues(b
					,"socNegeriHtaamX","negerichangeupX"
					,"socNegeriPemajuHtaamX","negerichangepemajuupX"
					,"socDaerahHtaamX","daerahchangeupX"
					,"socMukimHtaamX"
					,"txtBandarHartaHtaamX",""
					,"txtBandarPemaju1HtaamX",""
					,"socKategoriTanahHtaamX"
					,"socJenisLuasHtaamX"
					,"socStatusPemilikanHtaamX"
					,context,""
					);
		}else {
			READMODED="";
			setSocValues(b
					,"socNegeriHtaamX","negerichangeX"
					,"socNegeriPemajuHtaamX","negerichangepemajuX"
					,"socDaerahHtaamX","daerahchangeX"
					,"socMukimHtaamX"
					,"txtBandarHartaHtaamX",""
					,"txtBandarPemaju1HtaamX",""
					,"socKategoriTanahHtaamX"
					,"socJenisLuasHtaamX"
					,"socStatusPemilikanHtaamX"
					,context,""
					);
		}

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
	
	public void getHarta(String mode
		,Hashtable<String,String> hParam
		,HttpServletRequest request
		,HttpSession session
		,org.apache.velocity.VelocityContext context) throws Exception  {
		myLog.info("getHarta:hParam="+hParam);
		//Mula HTAAMX
		Hashtable<String,String> mh = null;
		Vector vecHarta = new Vector();
		String idhtaam = "";
		//String idhtaamX = "";			
		String idNegeriStrAdd = "0";
		//String idDaerahStrAdd = "0";
		String idBandarStrAdd = "0";

		//String add_new_harta = "";
		//String add_new = "";
		String kembalibutton = "";
		String show_button = "";
		String tambahbutton = "";
		//String buttonhtaam = "";
		//String kembaliHarta = "";
		//String nowPast = "";
		//String tambaHarta = "";
					
		String readmode = "";
		String readmodenegeri = "";
		String readmodedaerah = "";
		String readmodemukim = "";
//			String show_simpan_add_htaam = "";
//			String show_batal_add_htaam = "";
//			String show_kemaskini_htaam = "";
//			String show_htaa_add_table = "";
//			String show_save_update_htaam = "";
//			String show_batal_update_htaam = "";
//			String show_hapus_htaam = "";
//			String show_kembali_htaam = "";
//			String show_htaa_update_table = "";
		String[] radioHtaamViewX = null;
		
		Fungsi fnc = new Fungsi();
		logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
		permohonanInternal = new FrmPermohonanHTAData();
			
		//String idPemohon = fnc.getParam(request,"idPemohon");
		String idPermohonan = fnc.getParam(request,"idPermohonan");
		String idSimati = fnc.getParam(request,"idSimati");
		String mati = fnc.getParam(request,"id_Permohonansimati");
					
		idUser = String.valueOf(hParam.get("idUser"));
		String bolehsimpan = String.valueOf(hParam.get("bolehSimpan"));
			
		myLog.info("mode======="+mode);
		//idhtaam = String.valueOf(hParam.get("idhtaamXid"));		
			
		if ("HtaamviewX".equals(mode)) {
				context.put("nowpast", NOW);
				kembalibutton = YES;
				tambahbutton = YES;

		} else if ("Simpan_pilihan".equals(mode)) {
			//simpanpilihanHTAX(session, bolehsimpan);	
			permohonanInternal.simpanPilihanHTAX(fnc,logic_A,request,session,hParam);
			context.put("nowpast", "past");
			kembalibutton = YES;
			tambahbutton = YES;
			
		}else if ("HtaamviewXDulu".equals(mode)) {
			context.put("nowpast", "past");
			kembalibutton = YES;
			tambahbutton = YES;
			
		}else if ("add_new".equals(mode)) {
			mh = new Hashtable<String,String>();
			mh.put("socNegeri", idNegeriStr);
			mh.put("socNegeriAlamat", idNegeriStr);
			mh.put("socDaerah", idaerahStr);
			mh.put("socDaerahAlamat", idaerahStr);
			mh.put("socMukim", idMukimStr);
			mh.put("socBandar", "0");
			mh.put("socBandarAlamat", "0");
				//mh.put("socJenisHakmilik",idJenisHakmilik);
			mh.put("socKategori",idKategoriTanah);
			mh.put("socJenisLuas", idJenisLuas);
			mh.put("socStatusPemilikan", idStatusPemilikan);
			setSocHarta(mh,context,"");

			//add_new = YES;
			context.put("add_new", YES);
			context.put("buttonhtaam", "Tambah");
			context.put("show_simpan_add_htaam", YES);
			context.put("show_batal_add_htaam", YES);
			context.put("show_htaa_add_table", YES);
			show_button = YES;
				
		}else if ("HtaamviewX1".equals(mode)) {
			idNegeriStr = fnc.getParam(request,"socNegeriHtaamX").equals("")
						?idNegeriStr:fnc.getParam(request,"socNegeriHtaamX");
			idaerahStr = fnc.getParam(request,"socDaerahHtaamX").equals("")
						?idaerahStr:fnc.getParam(request,"socDaerahHtaamX");
			idMukimStr = fnc.getParam(request,"socMukimHtaamX").equals("")
						?idMukimStr:fnc.getParam(request,"socMukimHtaamX");
			idBandarStr = fnc.getParam(request,"txtBandarHartaHtaamX").equals("")
						?idBandarStr:fnc.getParam(request,"txtBandarHartaHtaamX");
				//idJenisHakmilik =  fnc.getParam(request,"socJenisTanahHtaamUp");
			idKategoriTanah = fnc.getParam(request,"socKategoriTanahHtaamX").equals("")
						?idKategoriTanah:fnc.getParam(request,"socKategoriTanahHtaamX");
			idJenisLuas = fnc.getParam(request,"socJenisLuasHtaamX").equals("")
						?idJenisLuas:fnc.getParam(request,"socJenisLuasHtaamX");
			idStatusPemilikan = fnc.getParam(request,"socStatusPemilikanHtaamX").equals("")
						?idStatusPemilikan:fnc.getParam(request,"socStatusPemilikanHtaamX");

			idNegeriStrAdd = fnc.getParam(request,"socNegeriPemajuHtaamX").equals("")
						?idNegeriStrAdd:fnc.getParam(request,"socNegeriPemajuHtaamX");
			idBandarStrAdd = fnc.getParam(request,"txtBandarPemaju1HtaamX").equals("")
						?idBandarStrAdd:fnc.getParam(request,"txtBandarPemaju1HtaamX");

			mh = new Hashtable<String,String>();
			mh.put("socNegeri", idNegeriStr);
			mh.put("socNegeriAlamat", idNegeriStrAdd);
			mh.put("socDaerah", idaerahStr);
			mh.put("socDaerahAlamat", idaerahStr);
			mh.put("socMukim", idMukimStr);
			mh.put("socBandar", idBandarStr);
			mh.put("socBandarAlamat", idBandarStrAdd);
				//mh.put("socJenisHakmilik",idJenisHakmilik);
			mh.put("socKategori",idKategoriTanah);
			mh.put("socJenisLuas", idJenisLuas);
			mh.put("socStatusPemilikan", idStatusPemilikan);
			setSocHarta(mh,context,"");
	
			setContextHartaTanah(context,request,fnc,mh); 
			setContextHartaLain(context,request,fnc,mh); 
				
			context.put("HtaamviewX1", "yes");
			context.put("buttonhtaam", "Tambah");
			context.put("radio2", "yes");
			context.put("checked1", "checked");
			context.put("show_simpan_add_htaam", "yes");
			context.put("show_batal_add_htaam", "yes");
			context.put("show_htaa_add_table", "yes");
			show_button = YES;
			
		}else if ("HtaamviewX2".equals(mode)) {
			idNegeriStr = fnc.getParam(request,"socNegeriHtaamX").equals("")
						?idNegeriStr:fnc.getParam(request,"socNegeriHtaamX");
			idaerahStr = fnc.getParam(request,"socDaerahHtaamX").equals("")
						?idaerahStr:fnc.getParam(request,"socDaerahHtaamX");
			idMukimStr = fnc.getParam(request,"socMukimHtaamX").equals("")
						?idMukimStr:fnc.getParam(request,"socMukimHtaamX");
			idBandarStr = fnc.getParam(request,"txtBandarHartaHtaamX").equals("")
						?idBandarStr:fnc.getParam(request,"txtBandarHartaHtaamX");
				//idJenisHakmilik =  fnc.getParam(request,"socJenisTanahHtaamUp");
			idKategoriTanah = fnc.getParam(request,"socKategoriTanahHtaamX").equals("")
						?idKategoriTanah:fnc.getParam(request,"socKategoriTanahHtaamX");
			idJenisLuas = fnc.getParam(request,"socJenisLuasHtaamX").equals("")
						?idJenisLuas:fnc.getParam(request,"socJenisLuasHtaamX");
			idStatusPemilikan = fnc.getParam(request,"socStatusPemilikanHtaamX").equals("")
						?idStatusPemilikan:fnc.getParam(request,"socStatusPemilikanHtaamX");

			idNegeriStrAdd = fnc.getParam(request,"socNegeriPemajuHtaamX").equals("")
						?idNegeriStrAdd:fnc.getParam(request,"socNegeriPemajuHtaamX");
			idBandarStrAdd = fnc.getParam(request,"txtBandarPemaju1HtaamX").equals("")
						?idBandarStrAdd:fnc.getParam(request,"txtBandarPemaju1HtaamX");

			mh = new Hashtable<String,String>();
			mh.put("socNegeri", idNegeriStr);
			mh.put("socNegeriAlamat", idNegeriStrAdd);
			mh.put("socDaerah", idaerahStr);
			mh.put("socDaerahAlamat", idaerahStr);
			mh.put("socMukim", idMukimStr);
			mh.put("socBandar", idBandarStr);
			mh.put("socBandarAlamat", idBandarStrAdd);
				//mh.put("socJenisHakmilik",idJenisHakmilik);
			mh.put("socKategori",idKategoriTanah);
			mh.put("socJenisLuas", idJenisLuas);
			mh.put("socStatusPemilikan", idStatusPemilikan);
			setSocHarta(mh,context,"");
				
			setContextHartaTanah(context,request,fnc,mh); 
			setContextHartaLain(context,request,fnc,mh); 
	
			context.put("radio3", "yes");
			context.put("checked2", "checked");
			context.put("buttonhtaam", "Tambah");
			context.put("show_simpan_add_htaam", "yes");
			context.put("show_batal_add_htaam", "yes");
			context.put("show_htaa_add_table", "yes");
			context.put("HtaamviewX2", "yes");
			show_button = YES;

		}else if ("HtaamviewX3".equals(mode)) {
			idNegeriStr = fnc.getParam(request,"socNegeriHtaamX").equals("")
					?idNegeriStr:fnc.getParam(request,"socNegeriHtaamX");
			idaerahStr = fnc.getParam(request,"socDaerahHtaamX").equals("")
					?idaerahStr:fnc.getParam(request,"socDaerahHtaamX");
			idMukimStr = fnc.getParam(request,"socMukimHtaamX").equals("")
					?idMukimStr:fnc.getParam(request,"socMukimHtaamX");
			idBandarStr = fnc.getParam(request,"txtBandarHartaHtaamX").equals("")
					?idBandarStr:fnc.getParam(request,"txtBandarHartaHtaamX");
			//idJenisHakmilik =  fnc.getParam(request,"socJenisTanahHtaamUp");
			idKategoriTanah = fnc.getParam(request,"socKategoriTanahHtaamX").equals("")
					?idKategoriTanah:fnc.getParam(request,"socKategoriTanahHtaamX");
			idJenisLuas = fnc.getParam(request,"socJenisLuasHtaamX").equals("")
					?idJenisLuas:fnc.getParam(request,"socJenisLuasHtaamX");
			idStatusPemilikan = fnc.getParam(request,"socStatusPemilikanHtaamX").equals("")
					?idStatusPemilikan:fnc.getParam(request,"socStatusPemilikanHtaamX");

			idNegeriStrAdd = fnc.getParam(request,"socNegeriPemajuHtaamX").equals("")
					?idNegeriStrAdd:fnc.getParam(request,"socNegeriPemajuHtaamX");
			idBandarStrAdd = fnc.getParam(request,"txtBandarPemaju1HtaamX").equals("")
					?idBandarStrAdd:fnc.getParam(request,"txtBandarPemaju1HtaamX");

			mh = new Hashtable<String,String>();
			mh.put("socNegeri", idNegeriStr);
			mh.put("socNegeriAlamat", idNegeriStrAdd);
			mh.put("socDaerah", idaerahStr);
			mh.put("socDaerahAlamat", idaerahStr);
			mh.put("socMukim", idMukimStr);
			mh.put("socBandar", idBandarStr);
			mh.put("socBandarAlamat", idBandarStrAdd);
			//mh.put("socJenisHakmilik",idJenisHakmilik);
			mh.put("socKategori",idKategoriTanah);
			mh.put("socJenisLuas", idJenisLuas);
			mh.put("socStatusPemilikan", idStatusPemilikan);
			setSocHarta(mh,context,"");

			setContextHartaTanah(context,request,fnc,mh); 
			setContextHartaLain(context,request,fnc,mh); 

			context.put("buttonhtaam", "Tambah");
			context.put("checked3", "checked");
			context.put("show_simpan_add_htaam", "yes");
			context.put("show_batal_add_htaam", "yes");
			context.put("show_htaa_add_table", "yes");
			context.put("HtaamviewX3", "yes");
			show_button = YES;

		}else if ("masukkanX".equals(mode)) {	
//			myLog.info("masukkanX="+bolehsimpan);
			mh = new Hashtable<String,String>();
			if (bolehsimpan.equals("yes")) {
				radioHtaamViewX = request.getParameterValues("radioHtaamViewX");
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
				String flag_tukar_jenis_hta = fnc.getParam(request,"flag_tukar_jenis_hta");
				if (flag_tukar_jenis_hta.equals("ADA")) {
					mh.put("jenis_Hta", "Y");
					mh.put("flag", "");
					
				} else if (flag_tukar_jenis_hta.equals("TIADA")) {
					mh.put("jenis_Hta", "T");
					mh.put("flag", String.valueOf(n));
					
				} else {
					mh.put("jenis_Hta", "T");
					//mh.put("flag", "1");
					mh.put("flag", String.valueOf(n));

				}	
				mh.put("idSimati", idSimati);
				mh.put("id_Permohonansimati", mati);
				mh.put("idUser", idUser);
				setContextHartaTanah(context,request,fnc,mh); 
				setContextHartaLain(context,request,fnc,mh); 

				permohonanInternal.tambahKemaskiniHarta(mh,"simpan");
				//idHarta = addHtaamX(mh,"simpan");	
				idHarta = permohonanInternal.getIDHarta();
				//online
				context.put("appear_skrin_info", "simpan");

				logic_A.updateDataNilai17(idPermohonan, idSimati, idUser, mati);

				Hashtable<String,String> b = permohonanInternal.getDataHTAX(idHarta,mati);
				idNegeriStr = String.valueOf(b.get("negeri"));
				idaerahStr = String.valueOf(b.get("daerah"));
				idMukimStr = String.valueOf(b.get("mukim"));
				idBandarStr = String.valueOf(b.get("bandar")).equals("")
						?idBandarStr:String.valueOf(b.get("bandar"));
				//idJenisHakmilik =  fnc.getParam(request,"socJenisTanahHtaamUp");
				idKategoriTanah = String.valueOf(b.get("kategori")).equals("")
						?idKategoriTanah:String.valueOf(b.get("kategori"));
				idJenisLuas = String.valueOf(b.get("jenisluas")).equals("")
						?idJenisLuas:String.valueOf(b.get("jenisluas"));
				idStatusPemilikan = String.valueOf(b.get("pemilikan")).equals("")
						?idStatusPemilikan:String.valueOf(b.get("pemilikan"));

				idNegeriStrAdd = String.valueOf(b.get("negeriAdd")).equals("")
						?idNegeriStrAdd:String.valueOf(b.get("negeriAdd"));
				idBandarStrAdd = String.valueOf(b.get("bandarAdd")).equals("")
						?idBandarStrAdd:String.valueOf(b.get("bandarAdd"));

				mh = new Hashtable<String,String>();
				mh.put("socNegeri", idNegeriStr);
				mh.put("socNegeriAlamat", idNegeriStrAdd);
				mh.put("socDaerah", idaerahStr);
				mh.put("socDaerahAlamat", idaerahStr);
				mh.put("socMukim", idMukimStr);
				mh.put("socBandar", idBandarStr);
				mh.put("socBandarAlamat", idBandarStrAdd);
				//mh.put("socJenisHakmilik",idJenisHakmilik);
				mh.put("socKategori",idKategoriTanah);
				mh.put("socJenisLuas", idJenisLuas);
				mh.put("socStatusPemilikan", idStatusPemilikan);
				setSocHarta(mh,context,"readonly");

				context.put("idhtaam", idHarta);
				context.put("idhtaamX", idHarta);
				context.put("listHTAXid", permohonanInternal.getDataHTA());								

			}

			kembalibutton = YES;
			tambahbutton = YES;

		}else if ("negerichangeX".equals(mode)
				||"negerichangepemajuX".equals(mode)
				||"daerahchangeX".equals(mode)) {			
			idNegeriStr = fnc.getParam(request,"socNegeriHtaamX").equals("")
					?idNegeriStr:fnc.getParam(request,"socNegeriHtaamX");
			idaerahStr = fnc.getParam(request,"socDaerahHtaamX").equals("")
					?idaerahStr:fnc.getParam(request,"socDaerahHtaamX");
			idMukimStr = fnc.getParam(request,"socMukimHtaamX").equals("")
					?idMukimStr:fnc.getParam(request,"socMukimHtaamX");
			idBandarStr = fnc.getParam(request,"txtBandarHartaHtaamX").equals("")
					?idBandarStr:fnc.getParam(request,"txtBandarHartaHtaamX");
			//idJenisHakmilik =  fnc.getParam(request,"socJenisTanahHtaamUp");
			idKategoriTanah = fnc.getParam(request,"socKategoriTanahHtaamX").equals("")
					?idKategoriTanah:fnc.getParam(request,"socKategoriTanahHtaamX");
			idJenisLuas = fnc.getParam(request,"socJenisLuasHtaamX").equals("")
					?idJenisLuas:fnc.getParam(request,"socJenisLuasHtaamX");
			idStatusPemilikan = fnc.getParam(request,"socStatusPemilikanHtaamX").equals("")
					?idStatusPemilikan:fnc.getParam(request,"socStatusPemilikanHtaamX");

			idNegeriStrAdd = fnc.getParam(request,"socNegeriPemajuHtaamX").equals("")
					?idNegeriStrAdd:fnc.getParam(request,"socNegeriPemajuHtaamX");
			idBandarStrAdd = fnc.getParam(request,"txtBandarPemaju1HtaamX").equals("")
					?idBandarStrAdd:fnc.getParam(request,"txtBandarPemaju1HtaamX");

			mh = new Hashtable<String,String>();
			mh.put("socNegeri", idNegeriStr);
			mh.put("socNegeriAlamat", idNegeriStrAdd);
			mh.put("socDaerah", idaerahStr);
			mh.put("socDaerahAlamat", idaerahStr);
			mh.put("socMukim", idMukimStr);
			mh.put("socBandar", idBandarStr);
			mh.put("socBandarAlamat", idBandarStrAdd);
			//mh.put("socJenisHakmilik",idJenisHakmilik);
			mh.put("socKategori",idKategoriTanah);
			mh.put("socJenisLuas", idJenisLuas);
			mh.put("socStatusPemilikan", idStatusPemilikan);
			setSocHarta(mh,context,"");

			radioHtaamViewX = request.getParameterValues("radioHtaamViewX");
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

			if (n == 1) {
				context.put("radio2", "yes");
				context.put("checked1", "checked");

			}
			if (n == 2) {
				context.put("radio3", "yes");
				context.put("checked2", "checked");

			}
			if (n == 3) {
				context.put("checked3", "checked");
			}

			context.put("idSimati", idSimati);
			setContextHartaTanah(context,request,fnc,mh);
			setContextHartaLain(context,request,fnc,mh);

			context.put("show_batal_add_htaam", YES);
			context.put("show_kembali_htaam", YES);
			context.put("show_htaa_add_table", YES);
			context.put("show_simpan_add_htaam", YES);
			show_button = YES;

		}else if ("negerichangepemajuX_".equals(mode)) {				
			//
		}else if ("negerichangeupX".equals(mode)
				||"daerahchangeupX".equals(mode)
				||"negerichangepemajuupX".equals(mode)) {			
			idHarta =  fnc.getParam(request,"idhtaamXid");

			idNegeriStr = fnc.getParam(request,"socNegeriHtaamX").equals("")
					?idNegeriStr:fnc.getParam(request,"socNegeriHtaamX");
			idaerahStr = fnc.getParam(request,"socDaerahHtaamX").equals("")
					?idaerahStr:fnc.getParam(request,"socDaerahHtaamX");
			idMukimStr = fnc.getParam(request,"socMukimHtaamX").equals("")
					?idMukimStr:fnc.getParam(request,"socMukimHtaamX");
			idBandarStr = fnc.getParam(request,"txtBandarHartaHtaamX").equals("")
					?idBandarStr:fnc.getParam(request,"txtBandarHartaHtaamX");
			idKategoriTanah = fnc.getParam(request,"socKategoriTanahHtaamX").equals("")
					?idKategoriTanah:fnc.getParam(request,"socKategoriTanahHtaamX");
			idJenisLuas = fnc.getParam(request,"socJenisLuasHtaamX").equals("")
					?idJenisLuas:fnc.getParam(request,"socJenisLuasHtaamX");
			idStatusPemilikan = fnc.getParam(request,"socStatusPemilikanHtaamX").equals("")
					?idStatusPemilikan:fnc.getParam(request,"socStatusPemilikanHtaamX");

			idNegeriStrAdd = fnc.getParam(request,"socNegeriPemajuHtaamX").equals("")
					?idNegeriStrAdd:fnc.getParam(request,"socNegeriPemajuHtaamX");
			idBandarStrAdd = fnc.getParam(request,"txtBandarPemaju1HtaamX").equals("")
					?idBandarStrAdd:fnc.getParam(request,"txtBandarPemaju1HtaamX");

			mh = new Hashtable<String,String>();
			radioHtaamViewX = request.getParameterValues("radioHtaamViewX_update");
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
			String flag_tukar_jenis_hta = fnc.getParam(request,"flag_tukar_jenis_hta");
			if (flag_tukar_jenis_hta.equals("ADA")) {
				mh.put("jenis_Hta", "Y");
				mh.put("flag", "");
				
			} else if (flag_tukar_jenis_hta.equals("TIADA")) {
				mh.put("jenis_Hta", "T");
				mh.put("flag", String.valueOf(n));
				
			} else {
				mh.put("jenis_Hta", "T");
				//mh.put("flag", "1");
				mh.put("flag", String.valueOf(n));

			}		
			mh.put("socNegeri", idNegeriStr);
			mh.put("socNegeriAlamat", idNegeriStrAdd);
			mh.put("socDaerah", idaerahStr);
			mh.put("socDaerahAlamat", idaerahStr);
			mh.put("socMukim", idMukimStr);
			mh.put("socBandar", idBandarStr);
			mh.put("socBandarAlamat", idBandarStrAdd);
			mh.put("socKategori",idKategoriTanah);
			mh.put("socJenisLuas", idJenisLuas);
			mh.put("socStatusPemilikan", idStatusPemilikan);
			setSocHarta(mh,context,"kemaskini");

			setContextHartaTanah(context,request,fnc,mh); 
			setContextHartaLain(context,request,fnc,mh); 

			vecHarta = new Vector();
			vecHarta.addElement(mh);

			context.put("idhtaamX", idHarta);
			context.put("idhtaam", idHarta);
			context.put("listHTAXid", vecHarta);
			context.put("show_batal_update_htaam", YES);
			context.put("show_hapus_htaam", YES);
			context.put("show_htaa_update_table", YES);
			context.put("show_kembali_htaam", YES);
			context.put("show_save_update_htaam", YES);
			show_button =YES;
			tambahbutton=YES;

		}else if ("negerichangepemajuupX_".equals(mode)) {
			//
		}else if ("daerahchangeupX_".equals(mode)) {
			//
		}else if ("getHtaamStatus".equals(mode)) {
			String id_sub =  fnc.getParam(request,"id_Suburusanstatusfail");
			String id_Fail =  fnc.getParam(request,"id_Fail");
			if (bolehsimpan.equals("yes")) {
				logic_A.htaamstatus17(session, idPermohonan,idUser, id_sub, id_Fail);
			}
			context.put("nowpast", "now");		
			tambahbutton =YES;
			kembalibutton =YES;

		}else if ("getHtaamX".equals(mode)) {	
			idHarta =  fnc.getParam(request,"idhtaamXid");

			Hashtable<String,String> b = permohonanInternal.getDataHTAX(idHarta,mati);
			idNegeriStr = String.valueOf(b.get("negeri"));
			idaerahStr = String.valueOf(b.get("daerah"));
			idMukimStr = String.valueOf(b.get("mukim"));
			idBandarStr = String.valueOf(b.get("bandar")).equals("")
					?idBandarStr:String.valueOf(b.get("bandar"));
			//idJenisHakmilik =  fnc.getParam(request,"socJenisTanahHtaamUp");
			idKategoriTanah = String.valueOf(b.get("kategori")).equals("")
					?idKategoriTanah:String.valueOf(b.get("kategori"));
			idJenisLuas = String.valueOf(b.get("jenisluas")).equals("")
					?idJenisLuas:String.valueOf(b.get("jenisluas"));
			idStatusPemilikan = String.valueOf(b.get("pemilikan")).equals("")
					?idStatusPemilikan:String.valueOf(b.get("pemilikan"));

			idNegeriStrAdd = String.valueOf(b.get("negeriAdd")).equals("")
					?idNegeriStrAdd:String.valueOf(b.get("negeriAdd"));
			idBandarStrAdd = String.valueOf(b.get("bandarAdd")).equals("")
					?idBandarStrAdd:String.valueOf(b.get("bandarAdd"));

			mh = new Hashtable<String,String>();
			mh.put("socNegeri", idNegeriStr);
			mh.put("socNegeriAlamat", idNegeriStrAdd);
			mh.put("socDaerah", idaerahStr);
			mh.put("socDaerahAlamat", idaerahStr);
			mh.put("socMukim", idMukimStr);
			mh.put("socBandar", idBandarStr);
			mh.put("socBandarAlamat", idBandarStrAdd);
			//mh.put("socJenisHakmilik",idJenisHakmilik);
			mh.put("socKategori",idKategoriTanah);
			mh.put("socJenisLuas", idJenisLuas);
			mh.put("socStatusPemilikan", idStatusPemilikan);
			setSocHarta(mh,context,"readonly");

			context.put("idhtaam", idHarta);
			context.put("idhtaamX", idHarta);
			context.put("listHTAXid", permohonanInternal.getDataHTA());
			context.put("readmode", READMODED);
			context.put("show_hapus_htaam", YES);
			context.put("show_htaa_update_table", YES);
			context.put("show_kemaskini_htaam", YES);
			context.put("show_kembali_htaam", YES);
			show_button = YES;
			tambahbutton = YES;

		}else if ("getHtaamXstatus".equals(mode)) {

		}else if ("batalHtaamX".equals(mode)) {

		} else if ("hapusHtaamX".equals(mode)) {
			String idDokumen =  fnc.getParam(request,"idDokumen");
			idHarta =  fnc.getParam(request,"idhtaamXid");
			permohonanInternal.deleteHtaamInternal(idDokumen, idHarta, mati);

			logic_A.updateDataNilai17(idPermohonan, idSimati,idUser,mati);
			kembalibutton = YES;
			tambahbutton = YES;

		} else if ("kemaskiniHtaamX".equals(mode)) {
			idHarta =  fnc.getParam(request,"idhtaamXid");
			String flag_tukar_jenis_hta = fnc.getParam(request,"flag_tukar_jenis_hta");
			String namaSkrin = fnc.getParam(request,"nama_skrin");
			myLog.info("kemaskiniHtaamX:flag_tukar_jenis_hta="+flag_tukar_jenis_hta);
			myLog.info("kemaskiniHtaamX:bolehsimpan="+bolehsimpan);
			myLog.info("kemaskiniHtaamX:namaSkrin="+namaSkrin);
			//	HTABean htaBean = null; 
			if (flag_tukar_jenis_hta.equals("TIADA")) {
				if (bolehsimpan.equals("yes")) {
					mh = new Hashtable<String,String>();
					mh.put("idhta",idHarta);
					mh.put("idSimati", idSimati);
					mh.put("id_Permohonansimati", mati);
					mh.put("idUser", idUser);

					if (namaSkrin.equals("adahakmilik")) {
						mh.put("jenis_Hta", "T");
						mh.put("flag", "1");
						//Ada Hakmilik kepada Tiada
							
						mh.put("alamatpemaju1", "");
						mh.put("alamatpemaju2", "");
						mh.put("alamatpemaju3", "");
						mh.put("bandarpemaju", "0");
						mh.put("jeniskepentingan", "");
						mh.put("namapemaju", "");
						mh.put("namarancangan", "");
						mh.put("negeripemaju", "0");
						mh.put("poskodpemaju", "");
						
						mh.put("nocagaran", "");
						mh.put("nolot", "");
						mh.put("noroh", "");
						mh.put("noperjanjian", "");
						mh.put("noperserahan", "");
						mh.put("tarikhperjanjian", "");
		
						HTABean17 htaBean = new HTABean17();
						htaBean.setContextHartaTanah(context,request,fnc,mh);
						htaBean.setContextHartaLain(context,request,fnc,mh);						

						permohonanInternal.tambahKemaskiniHarta(mh,"kemaskini");				

					} else {
						setContextHartaTanah(context,request,fnc,mh);
						setContextHartaLain(context,request,fnc,mh);
						radioHtaamViewX = request.getParameterValues("radioHtaamViewX_update");
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

						if (flag_tukar_jenis_hta.equals("ADA")) {
							mh.put("jenis_Hta", "Y");
							mh.put("flag", "");
							
						} else if (flag_tukar_jenis_hta.equals("TIADA")) {
							mh.put("jenis_Hta", "T");
							mh.put("flag", String.valueOf(n));
							
						} else {
							mh.put("jenis_Hta", "T");
							//mh.put("flag", "1");
							mh.put("flag", String.valueOf(n));

						}	
						//addHtaamX(mh,"kemaskini");						
						permohonanInternal.tambahKemaskiniHarta(mh,"kemaskini");

					}
				}
			}

			READMODED = "";
			mh = permohonanInternal.getDataHTAbyIdHtaam(idHarta,mati);
			setSocValues(mh
					,"socNegeriHtaamX","negerichangeupX"
					,"socNegeriPemajuHtaamX","negerichangepemajuupX"
					,"socDaerahHtaamX","daerahchangeupX"
					,"socMukimHtaamX"
					,"txtBandarHartaHtaamX",""
					,"txtBandarPemaju1HtaamX",""
					,"socKategoriTanahHtaamX"
					,"socJenisLuasHtaamX"
					,"socStatusPemilikanHtaamX"
					,context,"");

			context.put("idhtaamX", idHarta);
			context.put("idhtaam", idHarta);
			context.put("listHTAXid", permohonanInternal.getDataHTA());
			context.put("show_save_update_htaam", YES);
			context.put("show_batal_update_htaam", YES);
			context.put("show_hapus_htaam", YES);
			context.put("show_kembali_htaam", YES);
			context.put("show_htaa_update_table", YES);

			show_button=YES;
			tambahbutton =YES;

		} else if ("simpanHtaamX".equals(mode)) {
			idHarta =  fnc.getParam(request,"idhtaamXid");
			if (bolehsimpan.equals("yes")) {
				mh = new Hashtable<String,String>();
				radioHtaamViewX = request.getParameterValues("radioHtaamViewX_update");
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
				String flag_tukar_jenis_hta = fnc.getParam(request,"flag_tukar_jenis_hta");
				if (flag_tukar_jenis_hta.equals("ADA")) {
					mh.put("jenis_Hta", "Y");
					mh.put("flag", "");
					
				} else if (flag_tukar_jenis_hta.equals("TIADA")) {
					mh.put("jenis_Hta", "T");
					mh.put("flag", String.valueOf(n));
					
				} else {
					mh.put("jenis_Hta", "T");
					//mh.put("flag", "1");
					mh.put("flag", String.valueOf(n));

				}					
				mh.put("idhta",idHarta);
				mh.put("idSimati", idSimati);
				mh.put("id_Permohonansimati", mati);
				mh.put("idUser", idUser);
				setContextHartaTanah(context,request,fnc,mh); 
				setContextHartaLain(context,request,fnc,mh); 

				//addHtaamX(mh,"kemaskini");
				permohonanInternal.tambahKemaskiniHarta(mh,"kemaskini");
				context.put("appear_skrin_info", "kemaskini");

				logic_A.updateDataNilai17(idPermohonan, idSimati, idUser, mati);

			}

			Hashtable b = permohonanInternal.getDataHTAX(idHarta,mati);
			Hashtable hashSoc = setSocParamValues(String.valueOf(b.get("negeri")),String.valueOf(b.get("negeriAdd"))
					,String.valueOf(b.get("daerah")),String.valueOf(b.get("daerah"))
					,String.valueOf(b.get("mukim"))
					,String.valueOf(b.get("bandar"))
					,String.valueOf(b.get("bandarAdd"))
					,String.valueOf(b.get("kategori"))
					,String.valueOf(b.get("jenisluas"))
					,String.valueOf(b.get("pemilikan")));

			setSocValues(hashSoc
					,"socNegeriHtaamX","negerichangeupX"
					,"socNegeriPemajuHtaamX","negerichangepemajuX"
					,"socDaerahHtaamX","daerahchangeupX"
					,"socMukimHtaamX"
					,"txtBandarHartaHtaamX",""
					,"txtBandarPemaju1HtaamX",""
					,"socKategoriTanahHtaamX"
					,"socJenisLuasHtaamX"
					,"socStatusPemilikanHtaamX"
					,context,"readonly");

			context.put("idhtaamX", idHarta);
			context.put("idhtaam", idHarta);
			context.put("listHTAXid", permohonanInternal.getDataHTA());

			context.put("readmode", READMODED);
			context.put("show_kemaskini_htaam", "yes");
			context.put("show_hapus_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_update_table", "yes");
			context.put("radio2", "yes");
			context.put("checked1", "checked");
			show_button =YES;
			tambahbutton = YES;	

		}else if ("daerahchangeX_".equals(mode)) {

		}

		context.put("selectedTabatas", 1);
		context.put("selectedTabtengah", 0);
		context.put("selectedTabbawah", 0);
		context.put("selectedTabtepi", 1);

		context.put("kembalibutton", kembalibutton);
		context.put("show_button", show_button);
		context.put("tambahbutton", tambahbutton);


	}
	
	@Override
	public void getHarta(String mode
		,Hashtable hParam
		,FrmPrmhnnSek8InternalData logic_internal
		,HttpServletRequest request
		,HttpSession session
		,org.apache.velocity.VelocityContext context) throws Exception  {
		myLog.info("getHTATH:hParam="+hParam);
		 //Mula HTAAMX
		Vector vSenaraiHTAXid = new Vector();
		String idhtaam = "";
		String idhtaamX = "";
		context.put("add_new", "");
		context.put("tambahbutton", "");
		context.put("kembalibutton", "");
		context.put("show_button", "");
		context.put("HtaamviewX1", "");
		context.put("HtaamviewX2", "");
		context.put("HtaamviewX3", "");
		context.put("negeri", "");
		context.put("daerah", "");
		context.put("mukim", "");
		context.put("readmode", "");
		context.put("readmodenegeri", "");
		context.put("readmodedaerah", "");
		context.put("readmodemukim", "");
		context.put("show_simpan_add_htaam", "");
		context.put("show_batal_add_htaam", "");
		context.put("show_kemaskini_htaam", "");
		context.put("show_save_update_htaam", "");
		context.put("show_batal_update_htaam", "");
		context.put("show_hapus_htaam", "");
		context.put("show_kembali_htaam", "");
		context.put("show_htaa_update_table", "");
		context.put("show_htaa_add_table", "");
		context.put("buttonhtaam", "");
		context.put("tambahbutton", "");
		context.put("kembalibutton", "");
		context.put("radio1", "");
		context.put("radio2", "");
		context.put("radio3", "");
		context.put("checked1", "");
		context.put("checked2", "");
		context.put("checked3", "");
		permohonanInternal = new FrmPermohonanHTAData();
		//permohonanDaftar = new FrmPermohonanDaftarData();
		myLog.info("mode======="+mode);
		String mati = String.valueOf(hParam.get("id_Permohonansimati"));
		String idPermohonan = String.valueOf(hParam.get("idPermohonan"));
		String bolehsimpan = String.valueOf(hParam.get("bolehsimpan"));
		String idUser = String.valueOf(hParam.get("idUser"));
		idhtaam = String.valueOf(hParam.get("idhtaamXid"));		
		Vector listHTAX = null;
		Vector v = null;
		
		if ("HtaamviewX".equals(mode)) {
			//String id = String.valueOf(hParam.get("idPermohonan"));
			String radioX1 = String.valueOf(hParam.get("radioHtaamViewX1"));
			String radioX2 = String.valueOf(hParam.get("radioHtaamViewX2"));
			String radioX3 = String.valueOf(hParam.get("radioHtaamViewX3"));
			String selectedHartaTakAlih = String.valueOf(hParam.get("selectedHartaTakAlih"));
			setSocHTATH(hParam,context);

			listHTAX = permohonanInternal.getDataHTAX(mati);
			context.put("tambahbutton", "yes");
			context.put("kembalibutton", "yes");

			
		}else if ("add_new".equals(mode)) {
//			String id = getParam("idPermohonan");
//			String radioX1 = getParam("radioHtaamViewX1");
//			String radioX2 = getParam("radioHtaamViewX2");
//			String radioX3 = getParam("radioHtaamViewX3");
			setSocHTATH(hParam,context);

			listHTAX = permohonanInternal.getDataHTAX(mati);

			context.put("show_button", "yes");
			context.put("add_new", "yes");
			context.put("buttonhtaam", "Tambah");
			context.put("show_simpan_add_htaam", "yes");
			context.put("show_batal_add_htaam", "yes");
			context.put("show_htaa_add_table", "yes");

		}else if ("HtaamviewX1".equals(mode)) {
//			String radioX1 = getParam("radioHtaamViewX1");
//			String radioX2 = getParam("radioHtaamViewX2");
//			String radioX3 = getParam("radioHtaamViewX3");
			
			setSocHTATH(hParam,context);
			listHTAX = permohonanInternal.getDataHTAX(mati);
//
			context.put("HtaamviewX1", "yes");
			context.put("buttonhtaam", "Tambah");
			context.put("radio2", "yes");
			context.put("checked1", "checked");
			context.put("show_simpan_add_htaam", "yes");
			context.put("show_batal_add_htaam", "yes");
			context.put("show_htaa_add_table", "yes");
			context.put("show_button", "yes");
		
		}else if ("HtaamviewX2".equals(mode)) {
//			String radioX1 = getParam("radioHtaamViewX1");
//			String radioX2 = getParam("radioHtaamViewX2");
//			String radioX3 = getParam("radioHtaamViewX3");
//			
			setSocHTATH(hParam,context);
			listHTAX = permohonanInternal.getDataHTAX(mati);			
//
			context.put("radio3", "yes");
			context.put("checked2", "checked");
			context.put("buttonhtaam", "Tambah");
			context.put("show_simpan_add_htaam", "yes");
			context.put("show_batal_add_htaam", "yes");
			context.put("show_htaa_add_table", "yes");
			context.put("show_button", "yes");
			context.put("HtaamviewX2", "yes");
//
		}else if ("HtaamviewX3".equals(mode)) {
//			String radioX1 = getParam("radioHtaamViewX1");
//			String radioX2 = getParam("radioHtaamViewX2");
//			String radioX3 = getParam("radioHtaamViewX3");
//			
			setSocHTATH(hParam,context);
			listHTAX = permohonanInternal.getDataHTAX(mati);

			context.put("buttonhtaam", "Tambah");
			context.put("checked3", "checked");
			context.put("show_simpan_add_htaam", "yes");
			context.put("show_batal_add_htaam", "yes");
			context.put("show_htaa_add_table", "yes");
			context.put("show_button", "yes");
			context.put("HtaamviewX3", "yes");
			
		}else if ("masukkanX".equals(mode)) {
			
			setSocHTATH(hParam,context);
			if (bolehsimpan.equals("yes")) {
				//addHtaamX(session);
				addHtaamX(hParam,logic_internal,request);					
				context.put("appear_skrin_info", "simpan");
				
			}
			listHTAX = permohonanInternal.getDataHTAX(mati);
//
			context.put("tambahbutton", "yes");
			context.put("kembalibutton", "yes");
//
//			String id = getParam("idPermohonan");
			//logic_A.updateDataNilai(idPermohonan, mati,idUser);
			updateDataNilai(idPermohonan, mati,idUser);

		}else if ("negerichangeX".equals(mode)) {
			
			setSocHTATH(hParam,context);
//
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
			String radioX = String.valueOf(hParam.get("noradio"));
			listHTAX = permohonanInternal.getDataHTAX(mati);
//
			if (n == 1) {
				context.put("radio2", "yes");
				context.put("checked1", "checked");

			}
			if (n == 2) {
				context.put("radio3", "yes");
				context.put("checked2", "checked");

			}
			if (n == 3) {
				context.put("checked3", "checked");
			}
//
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
			context.put("nolot", String.valueOf(hParam.get("txtLotIdHtaamX")));
			context.put("nocagaran", String.valueOf(hParam.get("txtNoCagaranX")));
			context.put("pemilikan",String.valueOf(hParam.get("socStatusPemilikanHtaamX")));
			context.put("jeniskepentingan",String.valueOf(hParam.get("txtJenisKepentinganX")));
			context.put("FLAG_DAFTAR", String.valueOf(hParam.get("FLAG_DAFTAR")));
//
			context.put("show_simpan_add_htaam", "yes");
			context.put("show_batal_add_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_add_table", "yes");

			context.put("show_button", "yes");
			
		}else if ("negerichangepemajuX".equals(mode)) {
			
			setSocHTATH(hParam,context);
			
			listHTAX = permohonanInternal.getDataHTAX(mati);

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
			String radioX = String.valueOf(hParam.get("noradio"));
//
			if (n == 1) {
				context.put("radio2", "yes");
				context.put("checked1", "checked");

			}
			if (n == 2) {
				context.put("radio3", "yes");
				context.put("checked2", "checked");

			}
			if (n == 3) {
				context.put("checked3", "checked");
			}
//
//			this.context.put("idSimati", getParam("idSimatiX"));
//			this.context.put("nopt", getParam("txtNoPTHtaamX"));
//			this.context.put("nilai_Hta_memohon",getParam("txtNilaiTarikhMohonHtaaX"));
//			this.context.put("nilai_Hta_mati",getParam("txtNilaiTarikhMatiHtaamX"));
//			this.context.put("kategori", getParam("socKategoriTanahHtaamX"));
//			this.context.put("luashmp", getParam("txtLuasHMpHtaamX"));
//			this.context.put("luasasal", getParam("txtLuasAsalHtaamX"));
//			this.context.put("nopajakan", getParam("txtNoPajakanX"));
//			this.context.put("jenistanah", getParam("socJenisTanahHtaamX"));
//			this.context.put("basimati", getParam("txtBahagianSimati1X"));
//			this.context.put("bbsimati", getParam("txtBahagianSimati2X"));
//			this.context.put("tanggungan", getParam("txtTanggunganHtaamX"));
//			this.context.put("jenisluas", getParam("socJenisLuasHtaamX"));
//			this.context.put("catatan", getParam("txtCatatanHtaamX"));
//			this.context.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
//			this.context.put("alamatpemaju1",getParam("txtAlamatPemaju1HtaamX"));
//			this.context.put("alamatpemaju2",getParam("txtAlamatPemaju2HtaamX"));
//			this.context.put("alamatpemaju3",getParam("txtAlamatPemaju3HtaamX"));
//			this.context.put("poskodpemaju",getParam("txtPoskodPemaju1HtaamX"));
//			this.context.put("bandarpemaju", "");
//			this.context.put("negeripemaju",getParam("socNegeriPemajuHtaamX"));
//			this.context.put("alamathta1",getParam("txtAlamatHarta1HtaamX"));
//			this.context.put("alamathta2",getParam("txtAlamatHarta2HtaamX"));
//			this.context.put("alamathta3",getParam("txtAlamatHarta3HtaamX"));
//			this.context.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
//			this.context.put("bandarhta", getParam("txtBandarHartaHtaamX"));
//			this.context.put("noperjanjian",getParam("txtNoPerjanjianHtaamX"));
//			this.context.put("tarikhperjanjian",getParam("txtTarikhPerjanjianHtaamX"));
//			this.context.put("namarancangan",getParam("txtNamaRancanganHtaamX"));
//			this.context.put("noroh", getParam("txtNoRohHtaamX"));
//			this.context.put("nolot", getParam("txtLotIdHtaamX"));
//			this.context.put("nolot", getParam("txtLotIdHtaamX"));
//			this.context.put("nocagaran", getParam("txtNoCagaranX"));
//			this.context.put("pemilikan",getParam("socStatusPemilikanHtaamX"));
//			this.context.put("jeniskepentingan",getParam("txtJenisKepentinganX"));
//			this.context.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
//
			setContextHTATH(hParam,context);
			
			context.put("show_simpan_add_htaam", "yes");
			context.put("show_batal_add_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_add_table", "yes");
			context.put("show_button", "yes");
			
		}else if ("negerichangeupX".equals(mode)) {			
			v = new Vector();
//			this.context.put("listMukimbyDaerah", "");
//			if (getParam("socNegeriHtaamX") != ""
//				&& getParam("socNegeriHtaamX") != "0") {
//				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));
//				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
//				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
//				this.context.put("negeri", idnegeri);
//				this.context.put("bandarhta", "");
//				
//			} else {
//				this.context.put("listDaerahbyNegeri", "");
//				this.context.put("negeri", "");
//				this.context.put("bandarhta", "");
//				
//			}
//
//			if (getParam("socNegeriPemajuHtaamX") != ""
//				&& getParam("socNegeriPemajuHtaamX") != "0") {
//				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(getParam("socNegeriPemajuHtaamX")));
//				this.context.put("listBandarTetapbyNegeri", s3);
//				
//			} else {
//				this.context.put("listBandarTetapbyNegeri", "");
//			}
//
//			if (getParam("socNegeriHtaamX") != ""
//				&& getParam("socNegeriHtaamX") != "0") {
//				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(getParam("socNegeriHtaamX")));
//				this.context.put("listBandarSuratbyNegeri", s3);
//				
//			} else {
//				this.context.put("listBandarSuratbyNegeri", "");
//			}
			Hashtable<String,String> socHash 
				= setSocParamValues(String.valueOf(hParam.get("socNegeriHtaamX")),String.valueOf(hParam.get("socNegeriPemajuHtaamX"))
					,String.valueOf(hParam.get("socDaerahHtaamX")),String.valueOf(hParam.get("socDaerahHtaamX"))
					,String.valueOf(hParam.get("socMukimHtaamX"))
					,String.valueOf(hParam.get("txtBandarHartaHtaamX"))
					,String.valueOf(hParam.get("txtBandarPemaju1HtaamX"))
					,String.valueOf(hParam.get("socKategoriTanahHtaamX"))
					,String.valueOf(hParam.get("socJenisLuasHtaamX"))
					,String.valueOf(hParam.get("socStatusPemilikanHtaamX")));
			
			setSocValues(socHash
			,"socNegeriHtaamX","negerichangeupX"
			,"socNegeriPemajuHtaamX","negerichangepemajuX"
			,"socDaerahHtaamX","daerahchangeupX"
			,"socMukimHtaamX"
			,"txtBandarHartaHtaamX",""
			,"txtBandarPemaju1HtaamX",""
			,"socKategoriTanahHtaamX"
			,"socJenisLuasHtaamX"
			,"socStatusPemilikanHtaamX"
			,context
			);
			Hashtable h = new Hashtable();
//			listHTAX = permohonanInternal.getDataHTAX(mati);
//			h.put("idhta", getParam("idhtaamid"));
//
//			if (getParam("socNegeriHtaamX") != ""
//				&& getParam("socNegeriHtaamX") != "0") {
//				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));
//				h.put("negeri", idnegeri);
//				
//			} else {
//				h.put("negeri", "");
//			}
//
//			h.put("daerah", "");
//			h.put("mukim", "");
//			h.put("idSimati", getParam("idSimatiX"));
//			h.put("nopt", getParam("txtNoPTHtaamX"));
//			h.put("nilai_Hta_memohon",getParam("txtNilaiTarikhMohonHtaaX"));
//			h.put("nilai_Hta_mati", getParam("txtNilaiTarikhMatiHtaamX"));
//			h.put("kategori", getParam("socKategoriTanahHtaamX"));
//			h.put("luashmp", getParam("txtLuasHMpHtaamX"));
//			h.put("luasasal", getParam("txtLuasAsalHtaamX"));
//			h.put("nopajakan", getParam("txtNoPajakanX"));
//			h.put("jenistanah", getParam("socJenisTanahHtaamX"));
//			h.put("basimati", getParam("txtBahagianSimati1X"));
//			h.put("bbsimati", getParam("txtBahagianSimati2X"));
//			h.put("tanggungan", getParam("txtTanggunganHtaamX"));
//			h.put("jenisluas", getParam("socJenisLuasHtaamX"));
//			h.put("catatan", getParam("txtCatatanHtaamX"));
//			h.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
//			h.put("alamatpemaju1", getParam("txtAlamatPemaju1HtaamX"));
//			h.put("alamatpemaju2", getParam("txtAlamatPemaju2HtaamX"));
//			h.put("alamatpemaju3", getParam("txtAlamatPemaju3HtaamX"));
//			h.put("poskodpemaju", getParam("txtPoskodPemaju1HtaamX"));
//			h.put("bandarpemaju", getParam("txtBandarPemaju1HtaamX"));
//			h.put("negeripemaju", getParam("socNegeriPemajuHtaamX"));
//			h.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
//			h.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
//			h.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));
//			h.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
//			h.put("bandarhta", "");
//			h.put("noperjanjian", getParam("txtNoPerjanjianHtaamX"));
//			h.put("tarikhperjanjian",getParam("txtTarikhPerjanjianHtaamX"));
//			h.put("namarancangan", getParam("txtNamaRancanganHtaamX"));
//			h.put("noroh", getParam("txtNoRohHtaamX"));
//			h.put("nolot", getParam("txtLotIdHtaamX"));
//			h.put("nolot", getParam("txtLotIdHtaamX"));
//			h.put("flag", getParam("flag"));
//			h.put("nocagaran", getParam("txtNoCagaranX"));
//			h.put("pemilikan", getParam("socStatusPemilikanHtaamX"));
//			h.put("jeniskepentingan", getParam("txtJenisKepentinganX"));
//			h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
			//public void setHTATH(Hashtable hParam,org.apache.velocity.VelocityContext context) throws Exception  {
			//h = setValuesTanahKemaskini(hParam);
			//v.addElement(h);
			v.addElement(hParam);

			listHTAX = permohonanInternal.getDataHTAX(mati);
			
			context.put("listHTAXid", v);
			context.put("show_save_update_htaam", "yes");
			context.put("show_batal_update_htaam", "yes");
			context.put("show_hapus_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_update_table", "yes");
			context.put("tambahbutton", "yes");
			context.put("show_button", "yes");
			//context.put("idhtaam", getParam("idhtaamid"));
			context.put("idhtaamX", idhtaam);
			context.put("idhtaam", idhtaam);

//
		}else if ("negerichangepemajuupX".equals(mode)) {
			v = new Vector();
			Hashtable<String,String> socHash 
			= setSocParamValues(String.valueOf(hParam.get("socNegeriHtaamX")),String.valueOf(hParam.get("socNegeriPemajuHtaamX"))
				,String.valueOf(hParam.get("socDaerahHtaamX")),String.valueOf(hParam.get("socDaerahHtaamX"))
				,String.valueOf(hParam.get("socMukimHtaamX"))
				,String.valueOf(hParam.get("txtBandarHartaHtaamX"))
				,String.valueOf(hParam.get("txtBandarPemaju1HtaamX"))
				,String.valueOf(hParam.get("socKategoriTanahHtaamX"))
				,String.valueOf(hParam.get("socJenisLuasHtaamX"))
				,String.valueOf(hParam.get("socStatusPemilikanHtaamX")));
		
			setSocValues(socHash
			,"socNegeriHtaamX","negerichangeupX"
			,"socNegeriPemajuHtaamX","negerichangepemajuX"
			,"socDaerahHtaamX","daerahchangeupX"
			,"socMukimHtaamX"
			,"txtBandarHartaHtaamX",""
			,"txtBandarPemaju1HtaamX",""
			,"socKategoriTanahHtaamX"
			,"socJenisLuasHtaamX"
			,"socStatusPemilikanHtaamX"
			,context
			);
		//Hashtable h = new Hashtable();
//		listHTAX = permohonanInternal.getDataHTAX(mati);
//
//			if (getParam("socNegeriHtaamX") != ""
//				&& getParam("socNegeriHtaamX") != "0") {
//				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));
//				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
//				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
//				this.context.put("negeri", idnegeri);
//				
//			} else {
//				this.context.put("listDaerahbyNegeri", "");
//				this.context.put("negeri", "");
//			
//			}
//
//			if (getParam("socNegeriHtaamX") != ""
//				&& getParam("socNegeriHtaamX") != "0") {
//				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(getParam("socNegeriHtaamX")));
//				this.context.put("listBandarSuratbyNegeri", s3);
//				
//			} else {
//				this.context.put("listBandarSuratbyNegeri", "");
//			}
//
//			if (getParam("socNegeriPemajuHtaamX") != ""
//				&& getParam("socNegeriPemajuHtaamX") != "0") {
//				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(getParam("socNegeriPemajuHtaamX")));
//				this.context.put("listBandarTetapbyNegeri", s3);
//				
//			} else {
//				this.context.put("listBandarTetapbyNegeri", "");
//			}
//
//			listHTAX = permohonanInternal.getDataHTAX(mati);
//
//			Hashtable h = new Hashtable();
//			h.put("idhta", getParam("idhtaamid"));
//
//			if (getParam("socNegeriHtaamX") != ""
//				&& getParam("socNegeriHtaamX") != "0") {
//				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));
//				h.put("negeri", idnegeri);
//				
//			} else {
//				h.put("negeri", "");
//			}
//
//			if (getParam("socDaerahHtaamX") != ""
//				&& getParam("socDaerahHtaamX") != "0") {
//				int iddaerah = Integer.parseInt(getParam("socDaerahHtaamX"));
//				listmukim = logic_A.getListMukimbyDaerah(iddaerah);
//				this.context.put("listMukimbyDaerah", listmukim);
//				h.put("daerah", iddaerah);
//				h.put("mukim", getParam("socMukimHtaamX"));
//				
//			} else {
//				this.context.put("listMukimbyDaerah", "");
//				h.put("daerah", "");
//				h.put("mukim", "");
//			
//			}
//
//			h.put("idSimati", getParam("idSimatiX"));
//			h.put("nopt", getParam("txtNoPTHtaamX"));
//			h.put("nilai_Hta_memohon",getParam("txtNilaiTarikhMohonHtaaX"));
//			h.put("nilai_Hta_mati", getParam("txtNilaiTarikhMatiHtaamX"));
//			h.put("kategori", getParam("socKategoriTanahHtaamX"));
//			h.put("luashmp", getParam("txtLuasHMpHtaamX"));
//			h.put("luasasal", getParam("txtLuasAsalHtaamX"));
//			h.put("nopajakan", getParam("txtNoPajakanX"));
//			h.put("jenistanah", getParam("socJenisTanahHtaamX"));
//			h.put("basimati", getParam("txtBahagianSimati1X"));
//			h.put("bbsimati", getParam("txtBahagianSimati2X"));
//			h.put("tanggungan", getParam("txtTanggunganHtaamX"));
//			h.put("jenisluas", getParam("socJenisLuasHtaamX"));
//			h.put("catatan", getParam("txtCatatanHtaamX"));
//			h.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
//			h.put("alamatpemaju1", getParam("txtAlamatPemaju1HtaamX"));
//			h.put("alamatpemaju2", getParam("txtAlamatPemaju2HtaamX"));
//			h.put("alamatpemaju3", getParam("txtAlamatPemaju3HtaamX"));
//			h.put("poskodpemaju", getParam("txtPoskodPemaju1HtaamX"));
//			h.put("bandarpemaju", "");
//			h.put("negeripemaju", getParam("socNegeriPemajuHtaamX"));
//			h.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
//			h.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
//			h.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));
//			h.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
//			h.put("bandarhta", getParam("txtBandarHartaHtaamX"));
//			h.put("noperjanjian", getParam("txtNoPerjanjianHtaamX"));
//			h.put("tarikhperjanjian",getParam("txtTarikhPerjanjianHtaamX"));
//			h.put("namarancangan", getParam("txtNamaRancanganHtaamX"));
//			h.put("noroh", getParam("txtNoRohHtaamX"));
//			h.put("nolot", getParam("txtLotIdHtaamX"));
//			h.put("nolot", getParam("txtLotIdHtaamX"));
//			h.put("flag", getParam("flag"));
//			h.put("nocagaran", getParam("txtNoCagaranX"));
//			h.put("pemilikan", getParam("socStatusPemilikanHtaamX"));
//			h.put("jeniskepentingan", getParam("txtJenisKepentinganX"));
//			h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
//			v.addElement(h);
			v.addElement(hParam);
			context.put("listHTAXid", v);
//
			context.put("show_save_update_htaam", "yes");
			context.put("show_batal_update_htaam", "yes");
			context.put("show_hapus_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_update_table", "yes");
			context.put("tambahbutton", "yes");
			context.put("show_button", "yes");
			//context.put("idhtaam", getParam("idhtaamid"));
			context.put("idhtaamX", idhtaam);
			context.put("idhtaam", idhtaam);

		}else if ("daerahchangeupX".equals(mode)) {
			v = new Vector();
//			int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));
//			int iddaerah = Integer.parseInt(getParam("socDaerahHtaamX"));
//			listmukim = logic_A.getListMukimbyDaerah(iddaerah);
//			this.context.put("listMukimbyDaerah", listmukim);
//			listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
//			this.context.put("listDaerahbyNegeri", listnegeribydaerah);
//
//			listHTAX = permohonanInternal.getDataHTAX(mati);
//			
//			if (getParam("socNegeriHtaamX") != ""
//				&& getParam("socNegeriHtaamX") != "0") {
//				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(getParam("socNegeriHtaamX")));
//				this.context.put("listBandarSuratbyNegeri", s3);
//				
//			} else {
//				this.context.put("listBandarSuratbyNegeri", "");
//			}
//
//			if (getParam("socNegeriPemajuHtaamX") != ""
//				&& getParam("socNegeriPemajuHtaamX") != "0") {
//				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(getParam("socNegeriPemajuHtaamX")));
//				this.context.put("listBandarTetapbyNegeri", s3);
//			
//			} else {
//				this.context.put("listBandarTetapbyNegeri", "");
//			}
//
//			Hashtable h = new Hashtable();
//			// h.put("idhta",getParam("idhtaamXid"));
//			h.put("idhta", getParam("idhtaamid"));
//			h.put("negeri", idnegeri);
//			h.put("daerah", iddaerah);
//			h.put("mukim", "");
//			h.put("idSimati", getParam("idSimatiX"));
//			h.put("nopt", getParam("txtNoPTHtaamX"));
//			h.put("nilai_Hta_memohon",getParam("txtNilaiTarikhMohonHtaaX"));
//			h.put("nilai_Hta_mati", getParam("txtNilaiTarikhMatiHtaamX"));
//			h.put("kategori", getParam("socKategoriTanahHtaamX"));
//			h.put("luashmp", getParam("txtLuasHMpHtaamX"));
//			h.put("luasasal", getParam("txtLuasAsalHtaamX"));
//			h.put("nopajakan", getParam("txtNoPajakanX"));
//			h.put("jenistanah", getParam("socJenisTanahHtaamX"));
//			h.put("basimati", getParam("txtBahagianSimati1X"));
//			h.put("bbsimati", getParam("txtBahagianSimati2X"));
//			h.put("tanggungan", getParam("txtTanggunganHtaamX"));
//			h.put("jenisluas", getParam("socJenisLuasHtaamX"));
//			h.put("catatan", getParam("txtCatatanHtaamX"));
//			h.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
//			h.put("alamatpemaju1", getParam("txtAlamatPemaju1HtaamX"));
//			h.put("alamatpemaju2", getParam("txtAlamatPemaju2HtaamX"));
//			h.put("alamatpemaju3", getParam("txtAlamatPemaju3HtaamX"));
//			h.put("poskodpemaju", getParam("txtPoskodPemaju1HtaamX"));
//			h.put("bandarpemaju", getParam("txtBandarPemaju1HtaamX"));
//			h.put("negeripemaju", getParam("socNegeriPemajuHtaamX"));
//			h.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
//			h.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
//			h.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));
//			h.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
//			h.put("bandarhta", getParam("txtBandarHartaHtaamX"));
//			h.put("noperjanjian", getParam("txtNoPerjanjianHtaamX"));
//			h.put("tarikhperjanjian",getParam("txtTarikhPerjanjianHtaamX"));
//			h.put("namarancangan", getParam("txtNamaRancanganHtaamX"));
//			h.put("noroh", getParam("txtNoRohHtaamX"));
//			h.put("nolot", getParam("txtLotIdHtaamX"));
//			h.put("nolot", getParam("txtLotIdHtaamX"));
//			h.put("flag", getParam("flag"));
//			h.put("nocagaran", getParam("txtNoCagaranX"));
//			h.put("pemilikan", getParam("socStatusPemilikanHtaamX"));
//			h.put("jeniskepentingan", getParam("txtJenisKepentinganX"));
//			h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
			v.addElement(hParam);
			context.put("listHTAXid", v);

			context.put("show_save_update_htaam", "yes");
			context.put("show_batal_update_htaam", "yes");
			context.put("show_hapus_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_update_table", "yes");
			//context.put("negeri", idnegeri);
			//context.put("daerah", iddaerah);
			context.put("tambahbutton", "yes");
			context.put("show_button", "yes");
//			this.context.put("idhtaam", getParam("idhtaamid"));
			context.put("idhtaamX", idhtaam);
			context.put("idhtaam", idhtaam);

		}else if ("getHtaamStatus".equals(mode)) {
//			String id = getParam("idPermohonan");
//			String radioX1 = getParam("radioHtaamViewX1");
//			String radioX2 = getParam("radioHtaamViewX2");
//			String radioX3 = getParam("radioHtaamViewX3");
//
//			listHTAX = permohonanInternal.getDataHTAX(mati);
//			
//			this.context.put("tambahbutton", "yes");
//			this.context.put("kembalibutton", "yes");
//
//			String id_sub = getParam("id_Suburusanstatusfail");
//			String id_Fail = getParam("id_Fail");
//			if (bolehsimpan.equals("yes")) {
//				logic_A.htaamstatus(session, id,idUser, id_sub, id_Fail);
//			}
			
		}else if ("getHtaamX".equals(mode)) {				
//			String idhtaam = getParam("idhtaamXid");
			Hashtable<String,String> b = permohonanInternal.getDataHTAX(idhtaam,mati);
			Hashtable hashSoc = setSocParamValues(String.valueOf(b.get("negeri")),String.valueOf(b.get("negeriAdd"))
					,String.valueOf(b.get("daerah")),String.valueOf(b.get("daerah"))
					,String.valueOf(b.get("mukim"))
					,String.valueOf(b.get("bandar"))
					,String.valueOf(b.get("bandarAdd"))
					,String.valueOf(b.get("kategori"))
					,String.valueOf(b.get("jenisluas"))
					,String.valueOf(b.get("pemilikan")));
	
			setSocValues(hashSoc
					,"socNegeriHtaamX","negerichangeupX"
					,"socNegeriPemajuHtaamX","negerichangepemajuX"
					,"socDaerahHtaamX","daerahchangeupX"
					,"socMukimHtaamX"
					,"txtBandarHartaHtaamX",""
					,"txtBandarPemaju1HtaamX",""
					,"socKategoriTanahHtaamX"
					,"socJenisLuasHtaamX"
					,"socStatusPemilikanHtaamX"
					,context
					);
			listHTAX = permohonanInternal.getDataHTAX(mati);
//
			context.put("tambahbutton", "yes");
			context.put("show_button", "yes");
			context.put("idhtaamX", idhtaam);
			context.put("idhtaam", idhtaam);
			context.put("listHTAXid", permohonanInternal.getDataHTA());
			context.put("readmodenegeri", "disabled");
			context.put("readmodedaerah", "disabled");
			context.put("readmodemukim", "disabled");
			context.put("readmode", "disabled");
			context.put("show_kemaskini_htaam", "yes");
			context.put("show_hapus_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_update_table", "yes");
//		
		}else if ("getHtaamXstatus".equals(mode)) {
//			String idhtaam = getParam("idhtaamXid");
//			String id = getParam("idPermohonan");
			String id_sub = String.valueOf(hParam.get("id_Suburusanstatusfail"));
			String id_Fail = String.valueOf(hParam.get("id_Fail"));
			if (bolehsimpan.equals("yes")) {
				logic_A.htaamstatus(session, idPermohonan, idUser, id_sub, id_Fail);
			}
//
			Hashtable<String,String> b = permohonanInternal.getDataHTAbyIdHtaam(idhtaam,mati);
			Hashtable<String,String> hashSoc =  setSocParamValues(String.valueOf(b.get("negeri")),String.valueOf(b.get("negeripemaju"))
					,String.valueOf(b.get("daerah")),String.valueOf(b.get("daerah"))
					,String.valueOf(b.get("mukim"))
					,String.valueOf(b.get("bandarhta"))
					,String.valueOf(b.get("bandarpemaju"))
					,String.valueOf(b.get("kategori"))
					,String.valueOf(b.get("jenisluas"))
					,String.valueOf(b.get("jeniskepentingan")));				
			setSocHTATH(hashSoc,context);

			listHTAX = permohonanInternal.getDataHTAX(mati);
//
//			listmukim = logic_A.getListMukim();
//			this.context.put("listMukimbyDaerah", listmukim);
//
			context.put("tambahbutton", "yes");
			context.put("show_button", "yes");
			context.put("idhtaamX", idhtaam);
			context.put("idhtaam", idhtaam);
			context.put("listHTAXid", permohonanInternal.getDataHTA());
			context.put("readmodenegeri", "disabled");
			context.put("readmodedaerah", "disabled");
			context.put("readmodemukim", "disabled");
			context.put("readmode", "disabled");
			context.put("show_kemaskini_htaam", "yes");
			context.put("show_hapus_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_update_table", "yes");
//
		}else if ("batalHtaamX".equals(mode)) {
//			String idhtaam = getParam("idhtaamXid");
			Hashtable<String,String> b = permohonanInternal.getDataHTAbyIdHtaam(idhtaam,mati);
			Hashtable<String,String> hashSoc =  setSocParamValues(String.valueOf(b.get("negeri")),String.valueOf(b.get("negeripemaju"))
					,String.valueOf(b.get("daerah")),String.valueOf(b.get("daerah"))
					,String.valueOf(b.get("mukim"))
					,String.valueOf(b.get("bandarhta"))
					,String.valueOf(b.get("bandarpemaju"))
					,String.valueOf(b.get("kategori"))
					,String.valueOf(b.get("jenisluas"))
					,String.valueOf(b.get("jeniskepentingan")));				
			setSocHTATH(hashSoc,context);
			
			listHTAX = permohonanInternal.getDataHTAX(mati);				
			context.put("listHTAXid", permohonanInternal.getDataHTA());
//
			context.put("idhtaamX", idhtaam);
			context.put("idhtaam", idhtaam);
			context.put("show_save_update_htaam", "yes");
			context.put("show_batal_update_htaam", "yes");
			context.put("show_hapus_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_update_table", "yes");
			context.put("tambahbutton", "yes");
			context.put("show_button", "yes");
//
		} else if ("hapusHtaamX".equals(mode)) {
			logic_internal.deleteHtaamInternal("", idhtaam, mati);
//
			listHTAX = permohonanInternal.getDataHTAX(mati);
//
			context.put("tambahbutton", "yes");
			context.put("kembalibutton", "yes");
//
		} else if ("kemaskiniHtaamX".equals(mode)) {
			//String flag_tukar_jenis_hta = getParam("flag_tukar_jenis_hta");
			String flag_tukar_jenis_hta = String.valueOf(hParam.get("flag_tukar_jenis_hta"));
			Hashtable b = null;
			HTABean htaBean = null; 
			if (flag_tukar_jenis_hta.equals("TIADA")) {
				if (bolehsimpan.equals("yes")) {
					if (String.valueOf(hParam.get("nama_skrin")).equals("adahakmilik")) {
						updateHtaam(hParam,logic_internal);
						b = permohonanInternal.getDataHTAbyIdHtaam(idhtaam,mati);
						htaBean = new HTABean();
						htaBean.setSocValues(b
								,"socNegeriHtaamUp","negerichangeup"
								,"socDaerahHtaamUP","daerahchangeup"
								,"socMukimHtaamUp"
								,"txtBandarHartaHtaamX2",""
								,"socJenisHakmilikHtaamUp"
								,"socKategoriTanahHtaamUp"
								,"socJenisLuasHtaamUpd"
								,"socStatusPemilikanHtaamUpd"
								,context);

					} else {
						//updateHtaamX(session);
						updateHtaamX(hParam,logic_internal);

						b = permohonanInternal.getDataHTAbyIdHtaam(idhtaam,mati);
						setSocValues(b
								,"socNegeriHtaamX","negerichangeupX"
								,"socNegeriPemajuHtaamX","negerichangepemajuX"
								,"socDaerahHtaamX","daerahchangeupX"
								,"socMukimHtaamX"
								,"txtBandarHartaHtaamX",""
								,"txtBandarPemaju1HtaamX",""
								,"socKategoriTanahHtaamX"
								,"socJenisLuasHtaamX"
								,"socStatusPemilikanHtaamX"
								,context
								);
						
					}
				}
			}
//
//			String idhtaam = getParam("idhtaamXid");
//			
			context.put("listHTAXid", permohonanInternal.getDataHTA());
			listHTAX = permohonanInternal.getDataHTAX(mati);

			context.put("idhtaamX", idhtaam);
			context.put("idhtaam", idhtaam);
			context.put("show_save_update_htaam", "yes");
			context.put("show_batal_update_htaam", "yes");
			context.put("show_hapus_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_update_table", "yes");
			context.put("tambahbutton", "yes");
			context.put("show_button", "yes");
//
		} else if ("simpanHtaamX".equals(mode)) {
//				String idhtaam = getParam("idhtaamid");
				if (bolehsimpan.equals("yes")) {
					//updateHtaamX(session);
					addHtaamX(hParam,"kemaskini");
					context.put("appear_skrin_info", "kemaskini");
				
				}
//				
				Hashtable b = permohonanInternal.getDataHTAX(idhtaam,mati);
				Hashtable hashSoc = setSocParamValues(String.valueOf(b.get("negeri")),String.valueOf(b.get("negeriAdd"))
						,String.valueOf(b.get("daerah")),String.valueOf(b.get("daerah"))
						,String.valueOf(b.get("mukim"))
						,String.valueOf(b.get("bandar"))
						,String.valueOf(b.get("bandarAdd"))
						,String.valueOf(b.get("kategori"))
						,String.valueOf(b.get("jenisluas"))
						,String.valueOf(b.get("pemilikan")));
				setSocValues(hashSoc
						,"socNegeriHtaamX","negerichangeupX"
						,"socNegeriPemajuHtaamX","negerichangepemajuX"
						,"socDaerahHtaamX","daerahchangeupX"
						,"socMukimHtaamX"
						,"txtBandarHartaHtaamX",""
						,"txtBandarPemaju1HtaamX",""
						,"socKategoriTanahHtaamX"
						,"socJenisLuasHtaamX"
						,"socStatusPemilikanHtaamX"
						,context
						);
				context.put("listHTAXid", permohonanInternal.getDataHTA());
				listHTAX = permohonanInternal.getDataHTAX(mati);
//
//				if (b.get("negeripemaju").toString() != ""
//					&& b.get("negeripemaju").toString() != "0") {
//					Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(b.get("negeripemaju").toString()));
//					this.context.put("listBandarTetapbyNegeri", s3);
//				} else {
//					this.context.put("listBandarTetapbyNegeri", "");
//				}
//
//				if (b.get("negeri").toString() != ""
//						&& b.get("negeri").toString() != "0") {
//					Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(b.get("negeri").toString()));
//					this.context.put("listBandarSuratbyNegeri", s3);
//					
//				} else {
//					this.context.put("listBandarSuratbyNegeri", "");
//				}
//
//
				context.put("idhtaamX", idhtaam);
				context.put("idhtaam", idhtaam);

				context.put("readmodenegeri", "disabled");
				context.put("readmodedaerah", "disabled");
				context.put("readmodemukim", "disabled");
				context.put("readmode", "disabled");
				context.put("show_kemaskini_htaam", "yes");
				context.put("show_hapus_htaam", "yes");
				context.put("show_kembali_htaam", "yes");
				context.put("show_htaa_update_table", "yes");
				context.put("radio2", "yes");
				context.put("checked1", "checked");
				context.put("tambahbutton", "yes");
				context.put("show_button", "yes");
//				this.context.put("idhtaam", getParam("idhtaamid"));
//
//				String id = getParam("idPermohonan");
				//logic_A.updateDataNilai(idPermohonan, mati,idUser);
				updateDataNilai(idPermohonan, mati,idUser);
//			
		}else if ("daerahchangeX".equals(mode)) {
			int idnegeri = Integer.parseInt(String.valueOf(hParam.get("socNegeriHtaamX")));
			int iddaerah = Integer.parseInt(String.valueOf(hParam.get("socDaerahHtaamX")));
//
			setSocHTATH(hParam,context);
						
			listHTAX = permohonanInternal.getDataHTAX(mati);
//
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

			if (n == 1) {
				context.put("radio2", "yes");
				context.put("checked1", "checked");

			}
			if (n == 2) {
				context.put("radio3", "yes");
				context.put("checked2", "checked");
				
			}
			if (n == 3) {
				context.put("checked3", "checked");
			}
//
//			this.context.put("idSimati", getParam("idSimatiX"));
//			this.context.put("nopt", getParam("txtNoPTHtaamX"));
//			this.context.put("nilai_Hta_memohon",getParam("txtNilaiTarikhMohonHtaaX"));
//			this.context.put("nilai_Hta_mati",getParam("txtNilaiTarikhMatiHtaamX"));
//			this.context.put("kategori", getParam("socKategoriTanahHtaamX"));
//			this.context.put("luashmp", getParam("txtLuasHMpHtaamX"));
//			this.context.put("luasasal", getParam("txtLuasAsalHtaamX"));
//			this.context.put("nopajakan", getParam("txtNoPajakanX"));
//			this.context.put("jenistanah", getParam("socJenisTanahHtaamX"));
//			this.context.put("basimati", getParam("txtBahagianSimati1X"));
//			this.context.put("bbsimati", getParam("txtBahagianSimati2X"));
//			this.context.put("tanggungan", getParam("txtTanggunganHtaamX"));
//			this.context.put("jenisluas", getParam("socJenisLuasHtaamX"));
//			this.context.put("catatan", getParam("txtCatatanHtaamX"));
//			this.context.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
//			this.context.put("alamatpemaju1",getParam("txtAlamatPemaju1HtaamX"));
//			this.context.put("alamatpemaju2",getParam("txtAlamatPemaju2HtaamX"));
//			this.context.put("alamatpemaju3",getParam("txtAlamatPemaju3HtaamX"));
//			this.context.put("poskodpemaju",getParam("txtPoskodPemaju1HtaamX"));
//			this.context.put("bandarpemaju",getParam("txtBandarPemaju1HtaamX"));
//			this.context.put("negeripemaju",getParam("socNegeriPemajuHtaamX"));
//			this.context.put("alamathta1",getParam("txtAlamatHarta1HtaamX"));
//			this.context.put("alamathta2",getParam("txtAlamatHarta2HtaamX"));
//			this.context.put("alamathta3",getParam("txtAlamatHarta3HtaamX"));
//			this.context.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
//			this.context.put("bandarhta", getParam("txtBandarHartaHtaamX"));
//			this.context.put("noperjanjian",getParam("txtNoPerjanjianHtaamX"));
//			this.context.put("tarikhperjanjian",getParam("txtTarikhPerjanjianHtaamX"));
//			this.context.put("namarancangan",getParam("txtNamaRancanganHtaamX"));
//			this.context.put("noroh", getParam("txtNoRohHtaamX"));
//			this.context.put("nolot", getParam("txtLotIdHtaamX"));
//			this.context.put("nocagaran", getParam("txtNoCagaranX"));
//			this.context.put("pemilikan",getParam("socStatusPemilikanHtaamX"));
//			this.context.put("jeniskepentingan",getParam("txtJenisKepentinganX"));
//
//			this.context.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
//
			setContextHTATH(hParam,context);
			
			context.put("show_simpan_add_htaam", "yes");
			context.put("show_batal_add_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_add_table", "yes");
			context.put("negeri", idnegeri);
			context.put("daerah", iddaerah);
			context.put("show_button", "yes");
//
		}

		context.put("selectedTabatas", 1);
		context.put("selectedTabtengah", 0);
		context.put("selectedTabbawah", 0);
		context.put("selectedTabtepi", 1);
//
//		String id = getParam("idPermohonan");
//		Vector list = logic_A.setData_online(idPermohonan,idUser);
//		headerppk_baru(session, idPermohonan, "Y", "", "T",context);
//		context.put("View", list);
//
//		logic_A.updateDataNilai(idPermohonan, mati,idUser);
//
//		logic_A.setDataFail(idPermohonan);
//		Vector listFail = logic_A.getDataFail();
//		context.put("ViewFail", listFail);
//		context.put("DATEUTIL", new DateUtil());
//		hideTabPengesahan_simati = checkEmptyField_simati(idPermohonan);
//		context.put("hideTabPengesahan_simati", hideTabPengesahan_simati);
//		hideTabPengesahan_pemohon = checkEmptyField_pemohon(getParam("idPermohonan"));
//		context.put("hideTabPengesahan_pemohon", hideTabPengesahan_pemohon);
//		hideTabPengesahan_hta = checkEmptyField_hta(getParam("idPermohonan"));
//		context.put("hideTabPengesahan_hta", hideTabPengesahan_hta);
		
		context.put("listHTAX", listHTAX);

	}
	@Override
	//public void getHTATH(String mode,Hashtable hParam
	public void getHTA(String mode
		,Hashtable hParam
		,FrmPrmhnnSek8InternalData logic_internal
		,HttpServletRequest request
		,HttpSession session
		,org.apache.velocity.VelocityContext context) throws Exception  {
		myLog.info("getHTATH:hParam="+hParam);
		 //Mula HTAAMX
		Vector vSenaraiHTAXid = new Vector();
		String idhtaam = "";
		String idhtaamX = "";
		context.put("add_new", "");
		context.put("tambahbutton", "");
		context.put("kembalibutton", "");
		context.put("show_button", "");
		context.put("HtaamviewX1", "");
		context.put("HtaamviewX2", "");
		context.put("HtaamviewX3", "");
		context.put("negeri", "");
		context.put("daerah", "");
		context.put("mukim", "");
		context.put("readmode", "");
		context.put("readmodenegeri", "");
		context.put("readmodedaerah", "");
		context.put("readmodemukim", "");
		context.put("show_simpan_add_htaam", "");
		context.put("show_batal_add_htaam", "");
		context.put("show_kemaskini_htaam", "");
		context.put("show_save_update_htaam", "");
		context.put("show_batal_update_htaam", "");
		context.put("show_hapus_htaam", "");
		context.put("show_kembali_htaam", "");
		context.put("show_htaa_update_table", "");
		context.put("show_htaa_add_table", "");
		context.put("buttonhtaam", "");
		context.put("tambahbutton", "");
		context.put("kembalibutton", "");
		context.put("radio1", "");
		context.put("radio2", "");
		context.put("radio3", "");
		context.put("checked1", "");
		context.put("checked2", "");
		context.put("checked3", "");
		permohonanInternal = new FrmPermohonanHTAData();
		//permohonanDaftar = new FrmPermohonanDaftarData();
		myLog.info("mode======="+mode);
		String mati = String.valueOf(hParam.get("id_Permohonansimati"));
		String idPermohonan = String.valueOf(hParam.get("idPermohonan"));
		String bolehsimpan = String.valueOf(hParam.get("bolehsimpan"));
		String idUser = String.valueOf(hParam.get("idUser"));
		idhtaam = String.valueOf(hParam.get("idhtaamXid"));		
		Vector listHTAX = null;
		Vector v = null;
		
		if ("HtaamviewX".equals(mode)) {
			//String id = String.valueOf(hParam.get("idPermohonan"));
			String radioX1 = String.valueOf(hParam.get("radioHtaamViewX1"));
			String radioX2 = String.valueOf(hParam.get("radioHtaamViewX2"));
			String radioX3 = String.valueOf(hParam.get("radioHtaamViewX3"));
			String selectedHartaTakAlih = String.valueOf(hParam.get("selectedHartaTakAlih"));
			setSocHTATH(hParam,context);

			listHTAX = permohonanInternal.getDataHTAX(mati);
			context.put("tambahbutton", "yes");
			context.put("kembalibutton", "yes");
			
		}else if ("add_new".equals(mode)) {
//			String id = getParam("idPermohonan");
//			String radioX1 = getParam("radioHtaamViewX1");
//			String radioX2 = getParam("radioHtaamViewX2");
//			String radioX3 = getParam("radioHtaamViewX3");
			setSocHTATH(hParam,context);

			listHTAX = permohonanInternal.getDataHTAX(mati);

			context.put("show_button", "yes");
			context.put("add_new", "yes");
			context.put("buttonhtaam", "Tambah");
			context.put("show_simpan_add_htaam", "yes");
			context.put("show_batal_add_htaam", "yes");
			context.put("show_htaa_add_table", "yes");

		}else if ("HtaamviewX1".equals(mode)) {
//			String radioX1 = getParam("radioHtaamViewX1");
//			String radioX2 = getParam("radioHtaamViewX2");
//			String radioX3 = getParam("radioHtaamViewX3");
			
			setSocHTATH(hParam,context);
			listHTAX = permohonanInternal.getDataHTAX(mati);
//
			context.put("HtaamviewX1", "yes");
			context.put("buttonhtaam", "Tambah");
			context.put("radio2", "yes");
			context.put("checked1", "checked");
			context.put("show_simpan_add_htaam", "yes");
			context.put("show_batal_add_htaam", "yes");
			context.put("show_htaa_add_table", "yes");
			context.put("show_button", "yes");
		
		}else if ("HtaamviewX2".equals(mode)) {
//			String radioX1 = getParam("radioHtaamViewX1");
//			String radioX2 = getParam("radioHtaamViewX2");
//			String radioX3 = getParam("radioHtaamViewX3");
//			
			setSocHTATH(hParam,context);
			listHTAX = permohonanInternal.getDataHTAX(mati);			
//
			context.put("radio3", "yes");
			context.put("checked2", "checked");
			context.put("buttonhtaam", "Tambah");
			context.put("show_simpan_add_htaam", "yes");
			context.put("show_batal_add_htaam", "yes");
			context.put("show_htaa_add_table", "yes");
			context.put("show_button", "yes");
			context.put("HtaamviewX2", "yes");
//
		}else if ("HtaamviewX3".equals(mode)) {
//			String radioX1 = getParam("radioHtaamViewX1");
//			String radioX2 = getParam("radioHtaamViewX2");
//			String radioX3 = getParam("radioHtaamViewX3");
//			
			setSocHTATH(hParam,context);
			listHTAX = permohonanInternal.getDataHTAX(mati);

			context.put("buttonhtaam", "Tambah");
			context.put("checked3", "checked");
			context.put("show_simpan_add_htaam", "yes");
			context.put("show_batal_add_htaam", "yes");
			context.put("show_htaa_add_table", "yes");
			context.put("show_button", "yes");
			context.put("HtaamviewX3", "yes");
			
		}else if ("masukkanX".equals(mode)) {
			
			setSocHTATH(hParam,context);
			if (bolehsimpan.equals("yes")) {
				//addHtaamX(session);
				addHtaamX(hParam,logic_internal,request);					
				context.put("appear_skrin_info", "simpan");
				
			}
			listHTAX = permohonanInternal.getDataHTAX(mati);
//
			context.put("tambahbutton", "yes");
			context.put("kembalibutton", "yes");
//
//			String id = getParam("idPermohonan");
			//logic_A.updateDataNilai(idPermohonan, mati,idUser);
			updateDataNilai(idPermohonan, mati,idUser);

		}else if ("negerichangeX".equals(mode)) {
			
			setSocHTATH(hParam,context);
//
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
			String radioX = String.valueOf(hParam.get("noradio"));
			listHTAX = permohonanInternal.getDataHTAX(mati);
//
			if (n == 1) {
				context.put("radio2", "yes");
				context.put("checked1", "checked");

			}
			if (n == 2) {
				context.put("radio3", "yes");
				context.put("checked2", "checked");

			}
			if (n == 3) {
				context.put("checked3", "checked");
			}
//
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
			context.put("nolot", String.valueOf(hParam.get("txtLotIdHtaamX")));
			context.put("nocagaran", String.valueOf(hParam.get("txtNoCagaranX")));
			context.put("pemilikan",String.valueOf(hParam.get("socStatusPemilikanHtaamX")));
			context.put("jeniskepentingan",String.valueOf(hParam.get("txtJenisKepentinganX")));
			context.put("FLAG_DAFTAR", String.valueOf(hParam.get("FLAG_DAFTAR")));
//
			context.put("show_simpan_add_htaam", "yes");
			context.put("show_batal_add_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_add_table", "yes");

			context.put("show_button", "yes");
			
		}else if ("negerichangepemajuX".equals(mode)) {
			
			setSocHTATH(hParam,context);
			
			listHTAX = permohonanInternal.getDataHTAX(mati);

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
			String radioX = String.valueOf(hParam.get("noradio"));
//
			if (n == 1) {
				context.put("radio2", "yes");
				context.put("checked1", "checked");

			}
			if (n == 2) {
				context.put("radio3", "yes");
				context.put("checked2", "checked");

			}
			if (n == 3) {
				context.put("checked3", "checked");
			}
//
//			this.context.put("idSimati", getParam("idSimatiX"));
//			this.context.put("nopt", getParam("txtNoPTHtaamX"));
//			this.context.put("nilai_Hta_memohon",getParam("txtNilaiTarikhMohonHtaaX"));
//			this.context.put("nilai_Hta_mati",getParam("txtNilaiTarikhMatiHtaamX"));
//			this.context.put("kategori", getParam("socKategoriTanahHtaamX"));
//			this.context.put("luashmp", getParam("txtLuasHMpHtaamX"));
//			this.context.put("luasasal", getParam("txtLuasAsalHtaamX"));
//			this.context.put("nopajakan", getParam("txtNoPajakanX"));
//			this.context.put("jenistanah", getParam("socJenisTanahHtaamX"));
//			this.context.put("basimati", getParam("txtBahagianSimati1X"));
//			this.context.put("bbsimati", getParam("txtBahagianSimati2X"));
//			this.context.put("tanggungan", getParam("txtTanggunganHtaamX"));
//			this.context.put("jenisluas", getParam("socJenisLuasHtaamX"));
//			this.context.put("catatan", getParam("txtCatatanHtaamX"));
//			this.context.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
//			this.context.put("alamatpemaju1",getParam("txtAlamatPemaju1HtaamX"));
//			this.context.put("alamatpemaju2",getParam("txtAlamatPemaju2HtaamX"));
//			this.context.put("alamatpemaju3",getParam("txtAlamatPemaju3HtaamX"));
//			this.context.put("poskodpemaju",getParam("txtPoskodPemaju1HtaamX"));
//			this.context.put("bandarpemaju", "");
//			this.context.put("negeripemaju",getParam("socNegeriPemajuHtaamX"));
//			this.context.put("alamathta1",getParam("txtAlamatHarta1HtaamX"));
//			this.context.put("alamathta2",getParam("txtAlamatHarta2HtaamX"));
//			this.context.put("alamathta3",getParam("txtAlamatHarta3HtaamX"));
//			this.context.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
//			this.context.put("bandarhta", getParam("txtBandarHartaHtaamX"));
//			this.context.put("noperjanjian",getParam("txtNoPerjanjianHtaamX"));
//			this.context.put("tarikhperjanjian",getParam("txtTarikhPerjanjianHtaamX"));
//			this.context.put("namarancangan",getParam("txtNamaRancanganHtaamX"));
//			this.context.put("noroh", getParam("txtNoRohHtaamX"));
//			this.context.put("nolot", getParam("txtLotIdHtaamX"));
//			this.context.put("nolot", getParam("txtLotIdHtaamX"));
//			this.context.put("nocagaran", getParam("txtNoCagaranX"));
//			this.context.put("pemilikan",getParam("socStatusPemilikanHtaamX"));
//			this.context.put("jeniskepentingan",getParam("txtJenisKepentinganX"));
//			this.context.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
//
			setContextHTATH(hParam,context);
			
			context.put("show_simpan_add_htaam", "yes");
			context.put("show_batal_add_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_add_table", "yes");
			context.put("show_button", "yes");
			
		}else if ("negerichangeupX".equals(mode)) {			
			v = new Vector();
//			this.context.put("listMukimbyDaerah", "");
//			if (getParam("socNegeriHtaamX") != ""
//				&& getParam("socNegeriHtaamX") != "0") {
//				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));
//				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
//				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
//				this.context.put("negeri", idnegeri);
//				this.context.put("bandarhta", "");
//				
//			} else {
//				this.context.put("listDaerahbyNegeri", "");
//				this.context.put("negeri", "");
//				this.context.put("bandarhta", "");
//				
//			}
//
//			if (getParam("socNegeriPemajuHtaamX") != ""
//				&& getParam("socNegeriPemajuHtaamX") != "0") {
//				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(getParam("socNegeriPemajuHtaamX")));
//				this.context.put("listBandarTetapbyNegeri", s3);
//				
//			} else {
//				this.context.put("listBandarTetapbyNegeri", "");
//			}
//
//			if (getParam("socNegeriHtaamX") != ""
//				&& getParam("socNegeriHtaamX") != "0") {
//				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(getParam("socNegeriHtaamX")));
//				this.context.put("listBandarSuratbyNegeri", s3);
//				
//			} else {
//				this.context.put("listBandarSuratbyNegeri", "");
//			}
			Hashtable<String,String> socHash 
				= setSocParamValues(String.valueOf(hParam.get("socNegeriHtaamX")),String.valueOf(hParam.get("socNegeriPemajuHtaamX"))
					,String.valueOf(hParam.get("socDaerahHtaamX")),String.valueOf(hParam.get("socDaerahHtaamX"))
					,String.valueOf(hParam.get("socMukimHtaamX"))
					,String.valueOf(hParam.get("txtBandarHartaHtaamX"))
					,String.valueOf(hParam.get("txtBandarPemaju1HtaamX"))
					,String.valueOf(hParam.get("socKategoriTanahHtaamX"))
					,String.valueOf(hParam.get("socJenisLuasHtaamX"))
					,String.valueOf(hParam.get("socStatusPemilikanHtaamX")));
			
			setSocValues(socHash
			,"socNegeriHtaamX","negerichangeupX"
			,"socNegeriPemajuHtaamX","negerichangepemajuX"
			,"socDaerahHtaamX","daerahchangeupX"
			,"socMukimHtaamX"
			,"txtBandarHartaHtaamX",""
			,"txtBandarPemaju1HtaamX",""
			,"socKategoriTanahHtaamX"
			,"socJenisLuasHtaamX"
			,"socStatusPemilikanHtaamX"
			,context
			);
			Hashtable h = new Hashtable();
//			listHTAX = permohonanInternal.getDataHTAX(mati);
//			h.put("idhta", getParam("idhtaamid"));
//
//			if (getParam("socNegeriHtaamX") != ""
//				&& getParam("socNegeriHtaamX") != "0") {
//				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));
//				h.put("negeri", idnegeri);
//				
//			} else {
//				h.put("negeri", "");
//			}
//
//			h.put("daerah", "");
//			h.put("mukim", "");
//			h.put("idSimati", getParam("idSimatiX"));
//			h.put("nopt", getParam("txtNoPTHtaamX"));
//			h.put("nilai_Hta_memohon",getParam("txtNilaiTarikhMohonHtaaX"));
//			h.put("nilai_Hta_mati", getParam("txtNilaiTarikhMatiHtaamX"));
//			h.put("kategori", getParam("socKategoriTanahHtaamX"));
//			h.put("luashmp", getParam("txtLuasHMpHtaamX"));
//			h.put("luasasal", getParam("txtLuasAsalHtaamX"));
//			h.put("nopajakan", getParam("txtNoPajakanX"));
//			h.put("jenistanah", getParam("socJenisTanahHtaamX"));
//			h.put("basimati", getParam("txtBahagianSimati1X"));
//			h.put("bbsimati", getParam("txtBahagianSimati2X"));
//			h.put("tanggungan", getParam("txtTanggunganHtaamX"));
//			h.put("jenisluas", getParam("socJenisLuasHtaamX"));
//			h.put("catatan", getParam("txtCatatanHtaamX"));
//			h.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
//			h.put("alamatpemaju1", getParam("txtAlamatPemaju1HtaamX"));
//			h.put("alamatpemaju2", getParam("txtAlamatPemaju2HtaamX"));
//			h.put("alamatpemaju3", getParam("txtAlamatPemaju3HtaamX"));
//			h.put("poskodpemaju", getParam("txtPoskodPemaju1HtaamX"));
//			h.put("bandarpemaju", getParam("txtBandarPemaju1HtaamX"));
//			h.put("negeripemaju", getParam("socNegeriPemajuHtaamX"));
//			h.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
//			h.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
//			h.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));
//			h.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
//			h.put("bandarhta", "");
//			h.put("noperjanjian", getParam("txtNoPerjanjianHtaamX"));
//			h.put("tarikhperjanjian",getParam("txtTarikhPerjanjianHtaamX"));
//			h.put("namarancangan", getParam("txtNamaRancanganHtaamX"));
//			h.put("noroh", getParam("txtNoRohHtaamX"));
//			h.put("nolot", getParam("txtLotIdHtaamX"));
//			h.put("nolot", getParam("txtLotIdHtaamX"));
//			h.put("flag", getParam("flag"));
//			h.put("nocagaran", getParam("txtNoCagaranX"));
//			h.put("pemilikan", getParam("socStatusPemilikanHtaamX"));
//			h.put("jeniskepentingan", getParam("txtJenisKepentinganX"));
//			h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
			//public void setHTATH(Hashtable hParam,org.apache.velocity.VelocityContext context) throws Exception  {
			//h = setValuesTanahKemaskini(hParam);
			//v.addElement(h);
			v.addElement(hParam);

			listHTAX = permohonanInternal.getDataHTAX(mati);
			
			context.put("listHTAXid", v);
			context.put("show_save_update_htaam", "yes");
			context.put("show_batal_update_htaam", "yes");
			context.put("show_hapus_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_update_table", "yes");
			context.put("tambahbutton", "yes");
			context.put("show_button", "yes");
			//context.put("idhtaam", getParam("idhtaamid"));
			context.put("idhtaamX", idhtaam);
			context.put("idhtaam", idhtaam);

//
		}else if ("negerichangepemajuupX".equals(mode)) {
			v = new Vector();
			Hashtable<String,String> socHash 
			= setSocParamValues(String.valueOf(hParam.get("socNegeriHtaamX")),String.valueOf(hParam.get("socNegeriPemajuHtaamX"))
				,String.valueOf(hParam.get("socDaerahHtaamX")),String.valueOf(hParam.get("socDaerahHtaamX"))
				,String.valueOf(hParam.get("socMukimHtaamX"))
				,String.valueOf(hParam.get("txtBandarHartaHtaamX"))
				,String.valueOf(hParam.get("txtBandarPemaju1HtaamX"))
				,String.valueOf(hParam.get("socKategoriTanahHtaamX"))
				,String.valueOf(hParam.get("socJenisLuasHtaamX"))
				,String.valueOf(hParam.get("socStatusPemilikanHtaamX")));
		
			setSocValues(socHash
			,"socNegeriHtaamX","negerichangeupX"
			,"socNegeriPemajuHtaamX","negerichangepemajuX"
			,"socDaerahHtaamX","daerahchangeupX"
			,"socMukimHtaamX"
			,"txtBandarHartaHtaamX",""
			,"txtBandarPemaju1HtaamX",""
			,"socKategoriTanahHtaamX"
			,"socJenisLuasHtaamX"
			,"socStatusPemilikanHtaamX"
			,context
			);
		//Hashtable h = new Hashtable();
//		listHTAX = permohonanInternal.getDataHTAX(mati);
//
//			if (getParam("socNegeriHtaamX") != ""
//				&& getParam("socNegeriHtaamX") != "0") {
//				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));
//				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
//				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
//				this.context.put("negeri", idnegeri);
//				
//			} else {
//				this.context.put("listDaerahbyNegeri", "");
//				this.context.put("negeri", "");
//			
//			}
//
//			if (getParam("socNegeriHtaamX") != ""
//				&& getParam("socNegeriHtaamX") != "0") {
//				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(getParam("socNegeriHtaamX")));
//				this.context.put("listBandarSuratbyNegeri", s3);
//				
//			} else {
//				this.context.put("listBandarSuratbyNegeri", "");
//			}
//
//			if (getParam("socNegeriPemajuHtaamX") != ""
//				&& getParam("socNegeriPemajuHtaamX") != "0") {
//				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(getParam("socNegeriPemajuHtaamX")));
//				this.context.put("listBandarTetapbyNegeri", s3);
//				
//			} else {
//				this.context.put("listBandarTetapbyNegeri", "");
//			}
//
//			listHTAX = permohonanInternal.getDataHTAX(mati);
//
//			Hashtable h = new Hashtable();
//			h.put("idhta", getParam("idhtaamid"));
//
//			if (getParam("socNegeriHtaamX") != ""
//				&& getParam("socNegeriHtaamX") != "0") {
//				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));
//				h.put("negeri", idnegeri);
//				
//			} else {
//				h.put("negeri", "");
//			}
//
//			if (getParam("socDaerahHtaamX") != ""
//				&& getParam("socDaerahHtaamX") != "0") {
//				int iddaerah = Integer.parseInt(getParam("socDaerahHtaamX"));
//				listmukim = logic_A.getListMukimbyDaerah(iddaerah);
//				this.context.put("listMukimbyDaerah", listmukim);
//				h.put("daerah", iddaerah);
//				h.put("mukim", getParam("socMukimHtaamX"));
//				
//			} else {
//				this.context.put("listMukimbyDaerah", "");
//				h.put("daerah", "");
//				h.put("mukim", "");
//			
//			}
//
//			h.put("idSimati", getParam("idSimatiX"));
//			h.put("nopt", getParam("txtNoPTHtaamX"));
//			h.put("nilai_Hta_memohon",getParam("txtNilaiTarikhMohonHtaaX"));
//			h.put("nilai_Hta_mati", getParam("txtNilaiTarikhMatiHtaamX"));
//			h.put("kategori", getParam("socKategoriTanahHtaamX"));
//			h.put("luashmp", getParam("txtLuasHMpHtaamX"));
//			h.put("luasasal", getParam("txtLuasAsalHtaamX"));
//			h.put("nopajakan", getParam("txtNoPajakanX"));
//			h.put("jenistanah", getParam("socJenisTanahHtaamX"));
//			h.put("basimati", getParam("txtBahagianSimati1X"));
//			h.put("bbsimati", getParam("txtBahagianSimati2X"));
//			h.put("tanggungan", getParam("txtTanggunganHtaamX"));
//			h.put("jenisluas", getParam("socJenisLuasHtaamX"));
//			h.put("catatan", getParam("txtCatatanHtaamX"));
//			h.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
//			h.put("alamatpemaju1", getParam("txtAlamatPemaju1HtaamX"));
//			h.put("alamatpemaju2", getParam("txtAlamatPemaju2HtaamX"));
//			h.put("alamatpemaju3", getParam("txtAlamatPemaju3HtaamX"));
//			h.put("poskodpemaju", getParam("txtPoskodPemaju1HtaamX"));
//			h.put("bandarpemaju", "");
//			h.put("negeripemaju", getParam("socNegeriPemajuHtaamX"));
//			h.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
//			h.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
//			h.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));
//			h.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
//			h.put("bandarhta", getParam("txtBandarHartaHtaamX"));
//			h.put("noperjanjian", getParam("txtNoPerjanjianHtaamX"));
//			h.put("tarikhperjanjian",getParam("txtTarikhPerjanjianHtaamX"));
//			h.put("namarancangan", getParam("txtNamaRancanganHtaamX"));
//			h.put("noroh", getParam("txtNoRohHtaamX"));
//			h.put("nolot", getParam("txtLotIdHtaamX"));
//			h.put("nolot", getParam("txtLotIdHtaamX"));
//			h.put("flag", getParam("flag"));
//			h.put("nocagaran", getParam("txtNoCagaranX"));
//			h.put("pemilikan", getParam("socStatusPemilikanHtaamX"));
//			h.put("jeniskepentingan", getParam("txtJenisKepentinganX"));
//			h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
//			v.addElement(h);
			v.addElement(hParam);
			context.put("listHTAXid", v);
//
			context.put("show_save_update_htaam", "yes");
			context.put("show_batal_update_htaam", "yes");
			context.put("show_hapus_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_update_table", "yes");
			context.put("tambahbutton", "yes");
			context.put("show_button", "yes");
			//context.put("idhtaam", getParam("idhtaamid"));
			context.put("idhtaamX", idhtaam);
			context.put("idhtaam", idhtaam);

		}else if ("daerahchangeupX".equals(mode)) {
			v = new Vector();
//			int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));
//			int iddaerah = Integer.parseInt(getParam("socDaerahHtaamX"));
//			listmukim = logic_A.getListMukimbyDaerah(iddaerah);
//			this.context.put("listMukimbyDaerah", listmukim);
//			listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
//			this.context.put("listDaerahbyNegeri", listnegeribydaerah);
//
//			listHTAX = permohonanInternal.getDataHTAX(mati);
//			
//			if (getParam("socNegeriHtaamX") != ""
//				&& getParam("socNegeriHtaamX") != "0") {
//				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(getParam("socNegeriHtaamX")));
//				this.context.put("listBandarSuratbyNegeri", s3);
//				
//			} else {
//				this.context.put("listBandarSuratbyNegeri", "");
//			}
//
//			if (getParam("socNegeriPemajuHtaamX") != ""
//				&& getParam("socNegeriPemajuHtaamX") != "0") {
//				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(getParam("socNegeriPemajuHtaamX")));
//				this.context.put("listBandarTetapbyNegeri", s3);
//			
//			} else {
//				this.context.put("listBandarTetapbyNegeri", "");
//			}
//
//			Hashtable h = new Hashtable();
//			// h.put("idhta",getParam("idhtaamXid"));
//			h.put("idhta", getParam("idhtaamid"));
//			h.put("negeri", idnegeri);
//			h.put("daerah", iddaerah);
//			h.put("mukim", "");
//			h.put("idSimati", getParam("idSimatiX"));
//			h.put("nopt", getParam("txtNoPTHtaamX"));
//			h.put("nilai_Hta_memohon",getParam("txtNilaiTarikhMohonHtaaX"));
//			h.put("nilai_Hta_mati", getParam("txtNilaiTarikhMatiHtaamX"));
//			h.put("kategori", getParam("socKategoriTanahHtaamX"));
//			h.put("luashmp", getParam("txtLuasHMpHtaamX"));
//			h.put("luasasal", getParam("txtLuasAsalHtaamX"));
//			h.put("nopajakan", getParam("txtNoPajakanX"));
//			h.put("jenistanah", getParam("socJenisTanahHtaamX"));
//			h.put("basimati", getParam("txtBahagianSimati1X"));
//			h.put("bbsimati", getParam("txtBahagianSimati2X"));
//			h.put("tanggungan", getParam("txtTanggunganHtaamX"));
//			h.put("jenisluas", getParam("socJenisLuasHtaamX"));
//			h.put("catatan", getParam("txtCatatanHtaamX"));
//			h.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
//			h.put("alamatpemaju1", getParam("txtAlamatPemaju1HtaamX"));
//			h.put("alamatpemaju2", getParam("txtAlamatPemaju2HtaamX"));
//			h.put("alamatpemaju3", getParam("txtAlamatPemaju3HtaamX"));
//			h.put("poskodpemaju", getParam("txtPoskodPemaju1HtaamX"));
//			h.put("bandarpemaju", getParam("txtBandarPemaju1HtaamX"));
//			h.put("negeripemaju", getParam("socNegeriPemajuHtaamX"));
//			h.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
//			h.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
//			h.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));
//			h.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
//			h.put("bandarhta", getParam("txtBandarHartaHtaamX"));
//			h.put("noperjanjian", getParam("txtNoPerjanjianHtaamX"));
//			h.put("tarikhperjanjian",getParam("txtTarikhPerjanjianHtaamX"));
//			h.put("namarancangan", getParam("txtNamaRancanganHtaamX"));
//			h.put("noroh", getParam("txtNoRohHtaamX"));
//			h.put("nolot", getParam("txtLotIdHtaamX"));
//			h.put("nolot", getParam("txtLotIdHtaamX"));
//			h.put("flag", getParam("flag"));
//			h.put("nocagaran", getParam("txtNoCagaranX"));
//			h.put("pemilikan", getParam("socStatusPemilikanHtaamX"));
//			h.put("jeniskepentingan", getParam("txtJenisKepentinganX"));
//			h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
			v.addElement(hParam);
			context.put("listHTAXid", v);

			context.put("show_save_update_htaam", "yes");
			context.put("show_batal_update_htaam", "yes");
			context.put("show_hapus_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_update_table", "yes");
			//context.put("negeri", idnegeri);
			//context.put("daerah", iddaerah);
			context.put("tambahbutton", "yes");
			context.put("show_button", "yes");
//			this.context.put("idhtaam", getParam("idhtaamid"));
			context.put("idhtaamX", idhtaam);
			context.put("idhtaam", idhtaam);

		}else if ("getHtaamStatus".equals(mode)) {
//			String id = getParam("idPermohonan");
//			String radioX1 = getParam("radioHtaamViewX1");
//			String radioX2 = getParam("radioHtaamViewX2");
//			String radioX3 = getParam("radioHtaamViewX3");
//
//			listHTAX = permohonanInternal.getDataHTAX(mati);
//			
//			this.context.put("tambahbutton", "yes");
//			this.context.put("kembalibutton", "yes");
//
//			String id_sub = getParam("id_Suburusanstatusfail");
//			String id_Fail = getParam("id_Fail");
//			if (bolehsimpan.equals("yes")) {
//				logic_A.htaamstatus(session, id,idUser, id_sub, id_Fail);
//			}
			
		}else if ("getHtaamX".equals(mode)) {				
//			String idhtaam = getParam("idhtaamXid");
			Hashtable<String,String> b = permohonanInternal.getDataHTAX(idhtaam,mati);
			Hashtable hashSoc = setSocParamValues(String.valueOf(b.get("negeri")),String.valueOf(b.get("negeriAdd"))
					,String.valueOf(b.get("daerah")),String.valueOf(b.get("daerah"))
					,String.valueOf(b.get("mukim"))
					,String.valueOf(b.get("bandar"))
					,String.valueOf(b.get("bandarAdd"))
					,String.valueOf(b.get("kategori"))
					,String.valueOf(b.get("jenisluas"))
					,String.valueOf(b.get("pemilikan")));
	
			setSocValues(hashSoc
					,"socNegeriHtaamX","negerichangeupX"
					,"socNegeriPemajuHtaamX","negerichangepemajuX"
					,"socDaerahHtaamX","daerahchangeupX"
					,"socMukimHtaamX"
					,"txtBandarHartaHtaamX",""
					,"txtBandarPemaju1HtaamX",""
					,"socKategoriTanahHtaamX"
					,"socJenisLuasHtaamX"
					,"socStatusPemilikanHtaamX"
					,context
					);
			listHTAX = permohonanInternal.getDataHTAX(mati);
//
			context.put("tambahbutton", "yes");
			context.put("show_button", "yes");
			context.put("idhtaamX", idhtaam);
			context.put("idhtaam", idhtaam);
			context.put("listHTAXid", permohonanInternal.getDataHTA());
			context.put("readmodenegeri", "disabled");
			context.put("readmodedaerah", "disabled");
			context.put("readmodemukim", "disabled");
			context.put("readmode", "disabled");
			context.put("show_kemaskini_htaam", "yes");
			context.put("show_hapus_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_update_table", "yes");
//		
		}else if ("getHtaamXstatus".equals(mode)) {
//			String idhtaam = getParam("idhtaamXid");
//			String id = getParam("idPermohonan");
			String id_sub = String.valueOf(hParam.get("id_Suburusanstatusfail"));
			String id_Fail = String.valueOf(hParam.get("id_Fail"));
			if (bolehsimpan.equals("yes")) {
				logic_A.htaamstatus(session, idPermohonan, idUser, id_sub, id_Fail);
			}
//
			Hashtable<String,String> b = permohonanInternal.getDataHTAbyIdHtaam(idhtaam,mati);
			Hashtable<String,String> hashSoc =  setSocParamValues(String.valueOf(b.get("negeri")),String.valueOf(b.get("negeripemaju"))
					,String.valueOf(b.get("daerah")),String.valueOf(b.get("daerah"))
					,String.valueOf(b.get("mukim"))
					,String.valueOf(b.get("bandarhta"))
					,String.valueOf(b.get("bandarpemaju"))
					,String.valueOf(b.get("kategori"))
					,String.valueOf(b.get("jenisluas"))
					,String.valueOf(b.get("jeniskepentingan")));				
			setSocHTATH(hashSoc,context);

			listHTAX = permohonanInternal.getDataHTAX(mati);
//
//			listmukim = logic_A.getListMukim();
//			this.context.put("listMukimbyDaerah", listmukim);
//
			context.put("tambahbutton", "yes");
			context.put("show_button", "yes");
			context.put("idhtaamX", idhtaam);
			context.put("idhtaam", idhtaam);
			context.put("listHTAXid", permohonanInternal.getDataHTA());
			context.put("readmodenegeri", "disabled");
			context.put("readmodedaerah", "disabled");
			context.put("readmodemukim", "disabled");
			context.put("readmode", "disabled");
			context.put("show_kemaskini_htaam", "yes");
			context.put("show_hapus_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_update_table", "yes");
//
		}else if ("batalHtaamX".equals(mode)) {
//			String idhtaam = getParam("idhtaamXid");
			Hashtable<String,String> b = permohonanInternal.getDataHTAbyIdHtaam(idhtaam,mati);
			Hashtable<String,String> hashSoc =  setSocParamValues(String.valueOf(b.get("negeri")),String.valueOf(b.get("negeripemaju"))
					,String.valueOf(b.get("daerah")),String.valueOf(b.get("daerah"))
					,String.valueOf(b.get("mukim"))
					,String.valueOf(b.get("bandarhta"))
					,String.valueOf(b.get("bandarpemaju"))
					,String.valueOf(b.get("kategori"))
					,String.valueOf(b.get("jenisluas"))
					,String.valueOf(b.get("jeniskepentingan")));				
			setSocHTATH(hashSoc,context);
			
			listHTAX = permohonanInternal.getDataHTAX(mati);				
			context.put("listHTAXid", permohonanInternal.getDataHTA());
//
			context.put("idhtaamX", idhtaam);
			context.put("idhtaam", idhtaam);
			context.put("show_save_update_htaam", "yes");
			context.put("show_batal_update_htaam", "yes");
			context.put("show_hapus_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_update_table", "yes");
			context.put("tambahbutton", "yes");
			context.put("show_button", "yes");
//
		} else if ("hapusHtaamX".equals(mode)) {
			logic_internal.deleteHtaamInternal("", idhtaam, mati);
//
			listHTAX = permohonanInternal.getDataHTAX(mati);
//
			context.put("tambahbutton", "yes");
			context.put("kembalibutton", "yes");
//
		} else if ("kemaskiniHtaamX".equals(mode)) {
			//String flag_tukar_jenis_hta = getParam("flag_tukar_jenis_hta");
			String flag_tukar_jenis_hta = String.valueOf(hParam.get("flag_tukar_jenis_hta"));
			
			Hashtable b = null;
			HTABean htaBean = null; 
			if (flag_tukar_jenis_hta.equals("TIADA")) {
				if (bolehsimpan.equals("yes")) {
					if (String.valueOf(hParam.get("nama_skrin")).equals("adahakmilik")) {
						updateHtaam(hParam,logic_internal);
						b = permohonanInternal.getDataHTAbyIdHtaam(idhtaam,mati);
						htaBean = new HTABean();
						htaBean.setSocValues(b
								,"socNegeriHtaamUp","negerichangeup"
								,"socDaerahHtaamUP","daerahchangeup"
								,"socMukimHtaamUp"
								,"txtBandarHartaHtaamX2",""
								,"socJenisHakmilikHtaamUp"
								,"socKategoriTanahHtaamUp"
								,"socJenisLuasHtaamUpd"
								,"socStatusPemilikanHtaamUpd"
								,context);

					} else {
						//updateHtaamX(session);
						updateHtaamX(hParam,logic_internal);

						b = permohonanInternal.getDataHTAbyIdHtaam(idhtaam,mati);
						setSocValues(b
								,"socNegeriHtaamX","negerichangeupX"
								,"socNegeriPemajuHtaamX","negerichangepemajuX"
								,"socDaerahHtaamX","daerahchangeupX"
								,"socMukimHtaamX"
								,"txtBandarHartaHtaamX",""
								,"txtBandarPemaju1HtaamX",""
								,"socKategoriTanahHtaamX"
								,"socJenisLuasHtaamX"
								,"socStatusPemilikanHtaamX"
								,context
								);
						
					}
				}
			}
//
//			String idhtaam = getParam("idhtaamXid");
//			
			context.put("listHTAXid", permohonanInternal.getDataHTA());
			listHTAX = permohonanInternal.getDataHTAX(mati);

			context.put("idhtaamX", idhtaam);
			context.put("idhtaam", idhtaam);
			context.put("show_save_update_htaam", "yes");
			context.put("show_batal_update_htaam", "yes");
			context.put("show_hapus_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_update_table", "yes");
			context.put("tambahbutton", "yes");
			context.put("show_button", "yes");
//
		} else if ("simpanHtaamX".equals(mode)) {
//				String idhtaam = getParam("idhtaamid");
				if (bolehsimpan.equals("yes")) {
					//updateHtaamX(session);
					addHtaamX(hParam,"kemaskini");
					context.put("appear_skrin_info", "kemaskini");
				
				}
//				
				Hashtable b = permohonanInternal.getDataHTAX(idhtaam,mati);
				Hashtable hashSoc = setSocParamValues(String.valueOf(b.get("negeri")),String.valueOf(b.get("negeriAdd"))
						,String.valueOf(b.get("daerah")),String.valueOf(b.get("daerah"))
						,String.valueOf(b.get("mukim"))
						,String.valueOf(b.get("bandar"))
						,String.valueOf(b.get("bandarAdd"))
						,String.valueOf(b.get("kategori"))
						,String.valueOf(b.get("jenisluas"))
						,String.valueOf(b.get("pemilikan")));
				setSocValues(hashSoc
						,"socNegeriHtaamX","negerichangeupX"
						,"socNegeriPemajuHtaamX","negerichangepemajuX"
						,"socDaerahHtaamX","daerahchangeupX"
						,"socMukimHtaamX"
						,"txtBandarHartaHtaamX",""
						,"txtBandarPemaju1HtaamX",""
						,"socKategoriTanahHtaamX"
						,"socJenisLuasHtaamX"
						,"socStatusPemilikanHtaamX"
						,context
						);
				context.put("listHTAXid", permohonanInternal.getDataHTA());
				listHTAX = permohonanInternal.getDataHTAX(mati);
//
//				if (b.get("negeripemaju").toString() != ""
//					&& b.get("negeripemaju").toString() != "0") {
//					Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(b.get("negeripemaju").toString()));
//					this.context.put("listBandarTetapbyNegeri", s3);
//				} else {
//					this.context.put("listBandarTetapbyNegeri", "");
//				}
//
//				if (b.get("negeri").toString() != ""
//						&& b.get("negeri").toString() != "0") {
//					Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(b.get("negeri").toString()));
//					this.context.put("listBandarSuratbyNegeri", s3);
//					
//				} else {
//					this.context.put("listBandarSuratbyNegeri", "");
//				}
//
//
				context.put("idhtaamX", idhtaam);
				context.put("idhtaam", idhtaam);

				context.put("readmodenegeri", "disabled");
				context.put("readmodedaerah", "disabled");
				context.put("readmodemukim", "disabled");
				context.put("readmode", "disabled");
				context.put("show_kemaskini_htaam", "yes");
				context.put("show_hapus_htaam", "yes");
				context.put("show_kembali_htaam", "yes");
				context.put("show_htaa_update_table", "yes");
				context.put("radio2", "yes");
				context.put("checked1", "checked");
				context.put("tambahbutton", "yes");
				context.put("show_button", "yes");
//				this.context.put("idhtaam", getParam("idhtaamid"));
//
//				String id = getParam("idPermohonan");
				//logic_A.updateDataNilai(idPermohonan, mati,idUser);
				updateDataNilai(idPermohonan, mati,idUser);
//			
		}else if ("daerahchangeX".equals(mode)) {
			int idnegeri = Integer.parseInt(String.valueOf(hParam.get("socNegeriHtaamX")));
			int iddaerah = Integer.parseInt(String.valueOf(hParam.get("socDaerahHtaamX")));
//
			setSocHTATH(hParam,context);
						
			listHTAX = permohonanInternal.getDataHTAX(mati);
//
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

			if (n == 1) {
				context.put("radio2", "yes");
				context.put("checked1", "checked");

			}
			if (n == 2) {
				context.put("radio3", "yes");
				context.put("checked2", "checked");
				
			}
			if (n == 3) {
				context.put("checked3", "checked");
			}
//
//			this.context.put("idSimati", getParam("idSimatiX"));
//			this.context.put("nopt", getParam("txtNoPTHtaamX"));
//			this.context.put("nilai_Hta_memohon",getParam("txtNilaiTarikhMohonHtaaX"));
//			this.context.put("nilai_Hta_mati",getParam("txtNilaiTarikhMatiHtaamX"));
//			this.context.put("kategori", getParam("socKategoriTanahHtaamX"));
//			this.context.put("luashmp", getParam("txtLuasHMpHtaamX"));
//			this.context.put("luasasal", getParam("txtLuasAsalHtaamX"));
//			this.context.put("nopajakan", getParam("txtNoPajakanX"));
//			this.context.put("jenistanah", getParam("socJenisTanahHtaamX"));
//			this.context.put("basimati", getParam("txtBahagianSimati1X"));
//			this.context.put("bbsimati", getParam("txtBahagianSimati2X"));
//			this.context.put("tanggungan", getParam("txtTanggunganHtaamX"));
//			this.context.put("jenisluas", getParam("socJenisLuasHtaamX"));
//			this.context.put("catatan", getParam("txtCatatanHtaamX"));
//			this.context.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
//			this.context.put("alamatpemaju1",getParam("txtAlamatPemaju1HtaamX"));
//			this.context.put("alamatpemaju2",getParam("txtAlamatPemaju2HtaamX"));
//			this.context.put("alamatpemaju3",getParam("txtAlamatPemaju3HtaamX"));
//			this.context.put("poskodpemaju",getParam("txtPoskodPemaju1HtaamX"));
//			this.context.put("bandarpemaju",getParam("txtBandarPemaju1HtaamX"));
//			this.context.put("negeripemaju",getParam("socNegeriPemajuHtaamX"));
//			this.context.put("alamathta1",getParam("txtAlamatHarta1HtaamX"));
//			this.context.put("alamathta2",getParam("txtAlamatHarta2HtaamX"));
//			this.context.put("alamathta3",getParam("txtAlamatHarta3HtaamX"));
//			this.context.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
//			this.context.put("bandarhta", getParam("txtBandarHartaHtaamX"));
//			this.context.put("noperjanjian",getParam("txtNoPerjanjianHtaamX"));
//			this.context.put("tarikhperjanjian",getParam("txtTarikhPerjanjianHtaamX"));
//			this.context.put("namarancangan",getParam("txtNamaRancanganHtaamX"));
//			this.context.put("noroh", getParam("txtNoRohHtaamX"));
//			this.context.put("nolot", getParam("txtLotIdHtaamX"));
//			this.context.put("nocagaran", getParam("txtNoCagaranX"));
//			this.context.put("pemilikan",getParam("socStatusPemilikanHtaamX"));
//			this.context.put("jeniskepentingan",getParam("txtJenisKepentinganX"));
//
//			this.context.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
//
			setContextHTATH(hParam,context);
			
			context.put("show_simpan_add_htaam", "yes");
			context.put("show_batal_add_htaam", "yes");
			context.put("show_kembali_htaam", "yes");
			context.put("show_htaa_add_table", "yes");
			context.put("negeri", idnegeri);
			context.put("daerah", iddaerah);
			context.put("show_button", "yes");
//
		}

		context.put("selectedTabatas", 1);
		context.put("selectedTabtengah", 0);
		context.put("selectedTabbawah", 0);
		context.put("selectedTabtepi", 1);
//
//		String id = getParam("idPermohonan");
//		Vector list = logic_A.setData_online(idPermohonan,idUser);
//		headerppk_baru(session, idPermohonan, "Y", "", "T",context);
//		context.put("View", list);
//
//		logic_A.updateDataNilai(idPermohonan, mati,idUser);
//
//		logic_A.setDataFail(idPermohonan);
//		Vector listFail = logic_A.getDataFail();
//		context.put("ViewFail", listFail);
//		context.put("DATEUTIL", new DateUtil());
//		hideTabPengesahan_simati = checkEmptyField_simati(idPermohonan);
//		context.put("hideTabPengesahan_simati", hideTabPengesahan_simati);
//		hideTabPengesahan_pemohon = checkEmptyField_pemohon(getParam("idPermohonan"));
//		context.put("hideTabPengesahan_pemohon", hideTabPengesahan_pemohon);
//		hideTabPengesahan_hta = checkEmptyField_hta(getParam("idPermohonan"));
//		context.put("hideTabPengesahan_hta", hideTabPengesahan_hta);
		
		context.put("listHTAX", listHTAX);

	}
	
//	@Override
//	public void setSocValues(Hashtable b
//			,String negeriNama,String negeriFunc
//			,String daerahNama,String daerahFunc
//			,String mukimNama
//			,String bandarNama,String bandarFunc
//			,String jenisNama,String katNama
//			,String luasNama,String pbNama
//			,org.apache.velocity.VelocityContext context
//			) throws Exception{
//			myLog.info("setSocValues:b="+b);
//			String socStyle = "class=\"autoselect\" style=\"text-transform:uppercase;\" ";
//			
//			try{
//				String nn = String.valueOf(b.get("negeri"));
//				String dd = String.valueOf(b.get("daerah"));				
//				String idMukim = String.valueOf(b.get("mukim"));
//				String socNegeri = HTML.SelectNegeri(negeriNama, Long.valueOf(nn)
//						," $!readmodenegeri id=\""+negeriNama+"\" onchange=\""+negeriFunc+"('"+daerahNama+"')\" "+socStyle);
//				String socDaerah = HTML.SelectDaerahByNegeri(nn, daerahNama, Utils.parseLong(dd)
//						," $!readmodedaerah id=\""+daerahNama+"\" onchange=\"setSelected(1,0,0,0);"+daerahFunc+"('"+mukimNama+"');daerah_harta();check_harta()\" "+socStyle);
//				String socMukim = HTML.SelectMukimByDaerah(dd, mukimNama, Utils.parseLong(idMukim)
//						," $!readmodemukim id=\""+mukimNama+"\" "+socStyle);
//				
//				String bandar = String.valueOf(b.get("bandar"));
//				String socBandar = HTML.SelectBandarByNegeri(nn,bandarNama, Utils.parseLong(bandar)
//						," id=\""+bandarNama+"\" onclick=\"CheckBandarSurat()\" "+socStyle);
//				
//				String jenisHakmilik = String.valueOf(b.get("jenishakmilik"));
//				String socJenisHakmilik = HTML.SelectJenisHakmilik(jenisNama, Utils.parseLong(jenisHakmilik)
//						," $!socJenisHakmilikHtaam2 id=\""+jenisNama+"\" "+socStyle);
//				
//				String kaTanah = String.valueOf(b.get("kategori"));
//				String socKaTanah = HTML.SelectKategoriTanah(katNama, Utils.parseLong(kaTanah),""
//						," $!readmode id=\""+katNama+"\" "+socStyle);
//		
//				String jenisLuas = String.valueOf(b.get("jenisluas"));
//				String socLuas = HTML.SelectLuas(luasNama,Utils.parseLong(jenisLuas), ""
//					," $!readmode id=\""+luasNama+"\"" +
//					"onchange=\"pilih_jenis_luas('"+luasNama+"','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaamUpd','txtLuasHMpHtaamUpd','meterhektar')\" "+socStyle);
//				
//				String jenisPB = String.valueOf(b.get("pemilikan"));
//				String socPB = UtilHTML.SelectJenisPB(pbNama,jenisPB,"",socStyle,null);
//				
//				context.put("socNegeri", socNegeri);
//				context.put("socDaerah", socDaerah);
//				context.put("socMukim", socMukim);
//				context.put("socBandar", socBandar);
//				context.put("socJenisHakmilik", socJenisHakmilik);
//				context.put("socKaTanah", socKaTanah);
//				context.put("socLuas", socLuas);
//				context.put("socPB", socPB);
//				
//			}catch (Exception e){
//				throw new Exception("public void setSocValues:Ralat");
//			}
//					
//		}
	
	public void setSocValues(Hashtable b
			,String negeriNama,String negeriFunc
			,String negeriNamaAdd,String negeriFuncAdd
			,String daerahNama,String daerahFunc
			,String mukimNama
			,String bandarNama,String bandarFunc
			,String bandarNamaAdd,String bandarFuncAdd
			,String katNama
			,String luasNama
			,String pbNama
			,org.apache.velocity.VelocityContext context,String disablility
			) throws Exception{
			
			//myLog.info("setSocValues:b="+b);
			String socStyle = "class=\"autoselect\" style=\"text-transform:uppercase;\" "+READMODED;
				
				try{
					String nn = String.valueOf(b.get("negeri"));
					String dd = String.valueOf(b.get("daerah"));				
					String idMukim = String.valueOf(b.get("mukim"));
					String idNegeri = String.valueOf(b.get("negeriAdd"));
					String bandar = String.valueOf(b.get("bandar"));
					String bandarAdd = String.valueOf(b.get("bandarAdd"));
					
					String socBandar = HTML.SelectBandarByNegeri(nn,bandarNama, Utils.parseLong(bandar)
							," id=\""+bandarNama+"\" onclick=\"CheckBandarSurat()\" "+socStyle);
					String socBandarAdd = HTML.SelectBandarByNegeri(idNegeri,bandarNamaAdd, Utils.parseLong(bandarAdd)
							," id=\""+bandarNamaAdd+"\" onclick=\"CheckBandarTetap()\" "+socStyle);
								
//					String socNegeri = HTML.SelectNegeri(negeriNama, Long.valueOf(nn)
//							," $!readmodenegeri id=\""+negeriNama+"\" onchange=\"setSelected(1,0,0,1);"+negeriFunc+"('"+daerahNama+"')\" "+socStyle);
					String socNegeri = HTML.SelectNegeriExcludePelbagaiNegeri(negeriNama, Long.valueOf(nn),""
							," $!readmodenegeri id=\""+negeriNama+"\" onchange=\"setSelected(1,0,0,1);"+negeriFunc+"('"+daerahNama+"')\" "+socStyle);
					String socNegeriAdd = HTML.SelectNegeri(negeriNamaAdd, Long.valueOf(idNegeri)
							," $!readmodenegeri id=\""+negeriNamaAdd+"\" onchange=\"setSelected(1,0,0,1);"+negeriFuncAdd+"('"+bandarAdd+"')\" "+socStyle);
					
					String socDaerah = HTML.SelectDaerahByNegeri(nn, daerahNama, Utils.parseLong(dd)
							," $!readmodedaerah id=\""+daerahNama+"\" onchange=\"setSelected(1,0,0,1);"+daerahFunc+"('"+mukimNama+"');daerah_harta();check_harta()\" "+socStyle);
					
					String socMukim = HTML.SelectMukimByDaerah(dd, mukimNama, Utils.parseLong(idMukim)
							," $!readmodemukim id=\""+mukimNama+"\" onfocus=\"CheckMukim()\""+socStyle);
												
					String kaTanah = String.valueOf(b.get("kategori"));
					String socKaTanah = HTML.SelectKategoriTanah(katNama, Utils.parseLong(kaTanah),""
							," $!readmode id=\""+katNama+"\" "+socStyle);
			
					String jenisLuas = String.valueOf(b.get("jenisluas"));
					String socLuas = HTML.SelectLuas(luasNama,Utils.parseLong(jenisLuas), ""
						," $!readmode id=\""+luasNama+"\"" +
						"onchange=\"pilih_jenis_luas('"+luasNama+"','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaamX','txtLuasHMpHtaamX','meterhektar')\" "+socStyle);
					
					String jenisPB = String.valueOf(b.get("pemilikan"));
					String socPB =  getPilihanPB().Pilihan(pbNama,jenisPB,"",socStyle,null);
					
					context.put("socNegeri", socNegeri);
					context.put("socNegeriAdd", socNegeriAdd);
					context.put("socDaerah", socDaerah);
					context.put("socMukim", socMukim);
					context.put("socBandar", socBandar);
					context.put("socBandarAdd", socBandarAdd);
					context.put("socKaTanah", socKaTanah);
					context.put("socLuas", socLuas);
					context.put("socPB", socPB);
					
				}catch (Exception e){
					throw new Exception("public void setSocValues:Ralat"+e.getStackTrace());
				}
						
			}
		
	//@Override
	public void setSocValues(Hashtable b
		,String negeriNama,String negeriFunc
		,String negeriNamaAdd,String negeriFuncAdd
		,String daerahNama,String daerahFunc
		,String mukimNama
		,String bandarNama,String bandarFunc
		,String bandarNamaAdd,String bandarFuncAdd
		,String katNama
		,String luasNama
		,String pbNama
		,org.apache.velocity.VelocityContext context
		) throws Exception{
		
		//myLog.info("setSocValues:b="+b);
		String socStyle = "class=\"autoselect\" style=\"text-transform:uppercase;\" ";
			
			try{
				String nn = String.valueOf(b.get("negeri"));
				String dd = String.valueOf(b.get("daerah"));				
				String idMukim = String.valueOf(b.get("mukim"));
				String idNegeri = String.valueOf(b.get("negeriAdd"));
				String bandar = String.valueOf(b.get("bandar"));
				String bandarAdd = String.valueOf(b.get("bandarAdd"));
				
				String socBandar = HTML.SelectBandarByNegeri(nn,bandarNama, Utils.parseLong(bandar)
						," id=\""+bandarNama+"\" onclick=\"CheckBandarSurat()\" "+socStyle);
				String socBandarAdd = HTML.SelectBandarByNegeri(nn,bandarNamaAdd, Utils.parseLong(bandarAdd)
						," id=\""+bandarNamaAdd+"\" onclick=\"CheckBandarTetap()\" "+socStyle);
							
				String socNegeri = HTML.SelectNegeri(negeriNama, Long.valueOf(nn)
						," $!readmodenegeri id=\""+negeriNama+"\" onchange=\"setSelected(1,0,0,1);"+negeriFunc+"('"+daerahNama+"')\" "+socStyle);
				String socNegeriAdd = HTML.SelectNegeri(negeriNamaAdd, Long.valueOf(idNegeri)
						," $!readmodenegeri id=\""+negeriNamaAdd+"\" onchange=\"setSelected(1,0,0,1);"+negeriFuncAdd+"('"+bandarAdd+"')\" "+socStyle);
				
				String socDaerah = HTML.SelectDaerahByNegeri(nn, daerahNama, Utils.parseLong(dd)
						," $!readmodedaerah id=\""+daerahNama+"\" onchange=\"setSelected(1,0,0,1);"+daerahFunc+"('"+mukimNama+"');daerah_harta();check_harta()\" "+socStyle);
				
				String socMukim = HTML.SelectMukimByDaerah(dd, mukimNama, Utils.parseLong(idMukim)
						," $!readmodemukim id=\""+mukimNama+"\" onfocus=\"CheckMukim()\""+socStyle);
											
				String kaTanah = String.valueOf(b.get("kategori"));
				String socKaTanah = HTML.SelectKategoriTanah(katNama, Utils.parseLong(kaTanah),""
						," $!readmode id=\""+katNama+"\" "+socStyle);
		
				String jenisLuas = String.valueOf(b.get("jenisluas"));
				String socLuas = HTML.SelectLuas(luasNama,Utils.parseLong(jenisLuas), ""
					," $!readmode id=\""+luasNama+"\"" +
					"onchange=\"pilih_jenis_luas('"+luasNama+"','tr_luasharta','tr_luasharta_b','luas1','luas2','luas3','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','txtLuasAsalHtaamX','txtLuasHMpHtaamX','meterhektar')\" "+socStyle);
				
				String jenisPB = String.valueOf(b.get("pemilikan"));
				String socPB = getPilihanPB().Pilihan(pbNama,jenisPB,"",socStyle,null);
				
				context.put("socNegeri", socNegeri);
				context.put("socNegeriAdd", socNegeriAdd);
				context.put("socDaerah", socDaerah);
				context.put("socMukim", socMukim);
				context.put("socBandar", socBandar);
				context.put("socBandarAdd", socBandarAdd);
				context.put("socKaTanah", socKaTanah);
				context.put("socLuas", socLuas);
				context.put("socPB", socPB);
				
			}catch (Exception e){
				throw new Exception("public void setSocValues:Ralat"+e.getStackTrace());
			}
					
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
		//logic_internal.updateHtaamX(h);

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
	
	//private void setContextHartaLain(org.apache.velocity.VelocityContext context,HttpServletRequest request,Fungsi fnc) 
	public void setContextHartaLain(org.apache.velocity.VelocityContext context
		,HttpServletRequest request,Fungsi fnc,Hashtable<String,String> mh) 
		throws Exception {
		context.put("alamatpemaju1",fnc.getParam(request,"txtAlamatPemaju1HtaamX"));
		context.put("alamatpemaju2",fnc.getParam(request,"txtAlamatPemaju2HtaamX"));
		context.put("alamatpemaju3",fnc.getParam(request,"txtAlamatPemaju3HtaamX"));
		context.put("bandarpemaju",fnc.getParam(request,"txtBandarPemaju1HtaamX"));
		context.put("FLAG_DAFTAR", fnc.getParam(request,"FLAG_DAFTAR"));	
		context.put("jeniskepentingan",fnc.getParam(request,"txtJenisKepentinganX"));
		context.put("namapemaju", fnc.getParam(request,"txtNamaPemajuHtaamX"));
		context.put("namarancangan",fnc.getParam(request,"txtNamaRancanganHtaamX"));
		context.put("negeripemaju",fnc.getParam(request,"socNegeriPemajuHtaamX"));
		context.put("nocagaran", fnc.getParam(request,"txtNoCagaranX"));
		context.put("noperjanjian",fnc.getParam(request,"txtNoPerjanjianHtaamX"));
		context.put("poskodpemaju",fnc.getParam(request,"txtPoskodPemaju1HtaamX"));
		context.put("tarikhperjanjian",fnc.getParam(request,"txtTarikhPerjanjianHtaamX"));

//		this.context.put("pemilikan",
//				getParam("socStatusPemilikanHtaamX"));
		mh.put("alamatpemaju1",fnc.getParam(request,"txtAlamatPemaju1HtaamX"));
		mh.put("alamatpemaju2",fnc.getParam(request,"txtAlamatPemaju2HtaamX"));
		mh.put("alamatpemaju3",fnc.getParam(request,"txtAlamatPemaju3HtaamX"));
		mh.put("bandarpemaju", fnc.getParam(request,"txtBandarPemaju1HtaamX").equals("")
				?"0":fnc.getParam(request,"txtBandarPemaju1HtaamX"));

		mh.put("FLAG_DAFTAR", fnc.getParam(request,"FLAG_DAFTAR"));	
		mh.put("jeniskepentingan",fnc.getParam(request,"txtJenisKepentinganX"));
		mh.put("namapemaju", fnc.getParam(request,"txtNamaPemajuHtaamX"));
		mh.put("namarancangan",fnc.getParam(request,"txtNamaRancanganHtaamX"));
		mh.put("negeripemaju", fnc.getParam(request,"socNegeriPemajuHtaamX").equals("")
				?"0":fnc.getParam(request,"socNegeriPemajuHtaamX"));
		mh.put("nocagaran", fnc.getParam(request,"txtNoCagaranX"));
		mh.put("noperjanjian",fnc.getParam(request,"txtNoPerjanjianHtaamX"));
		mh.put("poskodpemaju",fnc.getParam(request,"txtPoskodPemaju1HtaamX"));
		mh.put("tarikhperjanjian",fnc.getParam(request,"txtTarikhPerjanjianHtaamX"));	
		
	}
	
	public void setContextHartaTanah(org.apache.velocity.VelocityContext context
		,HttpServletRequest request,Fungsi fnc,Hashtable<String,String> mh) 
		throws Exception {
		context.put("basimati", fnc.getParam(request,"txtBahagianSimati1X"));
		context.put("bbsimati", fnc.getParam(request,"txtBahagianSimati2X"));
		context.put("catatan", fnc.getParam(request,"txtCatatanHtaamX"));
//		context.put("jenishakmilik",idJenisHakmilik);
		context.put("jenisluas", fnc.getParam(request,"socJenisLuasHtaamX"));
		context.put("jenistanah", fnc.getParam(request,"socJenisTanahHtaamX"));
		context.put("kategori", fnc.getParam(request,"socKategoriTanahHtaamX"));
		context.put("luasasal", fnc.getParam(request,"txtLuasAsalHtaamX"));
		context.put("luashmp", fnc.getParam(request,"txtLuasHMpHtaamX"));
		context.put("nilai_Hta_mati",fnc.getParam(request,"txtNilaiTarikhMatiHtaamX"));
		context.put("nilai_Hta_memohon",fnc.getParam(request,"txtNilaiTarikhMohonHtaaX"));
//		context.put("noHakmilik", txtNoHakmilikHtaam);
		context.put("nolot", fnc.getParam(request,"txtLotIdHtaamX"));
		context.put("nopajakan", fnc.getParam(request,"txtNoPajakanX"));
//		context.put("noperserahan",txtNoPersHtaam);
		context.put("nopt", fnc.getParam(request,"txtNoPTHtaamX"));
		context.put("noroh", fnc.getParam(request,"txtNoRohHtaamX"));
//		context.put("pemilikan",socStatusPemilikanHtaam);
		context.put("tanggungan",fnc.getParam(request,"txtTanggunganHtaamX"));
//		context.put("txtSekatan", txtSekatan);
//		context.put("txtSyaratNyata", txtSyaratNyata);

		context.put("alamathta1",fnc.getParam(request,"txtAlamatHarta1HtaamX"));
		context.put("alamathta2",fnc.getParam(request,"txtAlamatHarta2HtaamX"));
		context.put("alamathta3",fnc.getParam(request,"txtAlamatHarta3HtaamX"));
		context.put("poskodhta", fnc.getParam(request,"txtPoskodHartaHtaamX"));
		
		//return Hashtable
		mh.put("basimati", fnc.getParam(request,"txtBahagianSimati1X"));
		mh.put("bbsimati", fnc.getParam(request,"txtBahagianSimati2X"));
		mh.put("catatan", fnc.getParam(request,"txtCatatanHtaamX"));
//		context.put("jenishakmilik",idJenisHakmilik);
		mh.put("jenisluas", fnc.getParam(request,"socJenisLuasHtaamX").equals("")
				?"0":fnc.getParam(request,"socJenisLuasHtaamX"));
		mh.put("jenistanah", fnc.getParam(request,"socJenisTanahHtaamX").equals("")
				?"0":fnc.getParam(request,"socJenisTanahHtaamX"));
		mh.put("kategori", fnc.getParam(request,"socKategoriTanahHtaamX").equals("")
				?"0":fnc.getParam(request,"socKategoriTanahHtaamX"));
		mh.put("luasasal", fnc.getParam(request,"txtLuasAsalHtaamX"));
		mh.put("luashmp", fnc.getParam(request,"txtLuasHMpHtaamX"));
		mh.put("nilai_Hta_mati",fnc.getParam(request,"txtNilaiTarikhMatiHtaamX"));
		mh.put("nilai_Hta_memohon",fnc.getParam(request,"txtNilaiTarikhMohonHtaaX"));
//		context.put("noHakmilik", txtNoHakmilikHtaam);
		mh.put("nolot", fnc.getParam(request,"txtLotIdHtaamX"));
		mh.put("nopajakan", fnc.getParam(request,"txtNoPajakanX"));
		mh.put("noperserahan",txtNoPersHtaam);
		mh.put("nopt", fnc.getParam(request,"txtNoPTHtaamX"));
		mh.put("noroh", fnc.getParam(request,"txtNoRohHtaamX"));
		mh.put("pemilikan",socStatusPemilikanHtaam);
		mh.put("tanggungan",fnc.getParam(request,"txtTanggunganHtaamX"));

		mh.put("alamat_hta1",fnc.getParam(request,"txtAlamatHarta1HtaamX"));
		mh.put("alamat_hta2",fnc.getParam(request,"txtAlamatHarta2HtaamX"));
		mh.put("alamat_hta3",fnc.getParam(request,"txtAlamatHarta3HtaamX"));
		// guna pada fungsi add (lama), mh.put("poskod_hta", fnc.getParam(request,"txtPoskodHartaHtaamX"));
		mh.put("poskod", fnc.getParam(request,"txtPoskodHartaHtaamX"));

		mh.put("negeri", fnc.getParam(request,"socNegeriHtaamX"));
		mh.put("daerah", fnc.getParam(request,"socDaerahHtaamX"));
		//myLog.info("daerah:"+fnc.getParam(request,"socDaerahHtaamX"));
		mh.put("mukim", fnc.getParam(request,"socMukimHtaamX"));
		mh.put("id_bandarhta", fnc.getParam(request,"txtBandarHartaHtaamX").equals("")
				?"0":fnc.getParam(request,"txtBandarHartaHtaamX"));
			
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

	private Hashtable setValuesTanahParam(Hashtable hi){
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
	
	private Hashtable<String,String> setSocParamValues(String negeri,String negeriAdd
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
	
	private IUtilHTMLPilihanExt getPilihanPB(){
		if(jenisPB==null){
			jenisPB = new UtilHTMLPilihanJenisPBPPK();
		}
		return jenisPB;
				
	}
	
	
}
