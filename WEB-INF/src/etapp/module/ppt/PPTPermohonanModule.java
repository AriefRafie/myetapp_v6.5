package etapp.module.ppt;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import lebah.db.DbException;
import lebah.portal.action.Command;
import lebah.template.LebahRecordTemplateModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import ekptg.helpers.File;
import etapp.entity.User;
import etapp.entity.pfd.Fail;
import etapp.entity.ppt.PPTBorangK;
import etapp.entity.ppt.PPTDokumen;
import etapp.entity.ppt.PPTEndosanBorangK;
import etapp.entity.ppt.PPTHakmilik;
import etapp.entity.ppt.PPTPermohonan;
import etapp.entity.ppt.PPTStatus;
import etapp.entity.ppt.PPTStatusUpdate;
import etapp.entity.ppt.PPTTempHakmilik;
import etapp.entity.rujukan.Agensi;
import etapp.entity.rujukan.Daerah;
import etapp.entity.rujukan.JenisHakmilik;
import etapp.entity.rujukan.Kategori;
import etapp.entity.rujukan.Kementerian;
import etapp.entity.rujukan.Luas;
import etapp.entity.rujukan.Mukim;
import etapp.entity.rujukan.Negeri;
import etapp.entity.rujukan.Status;
import etapp.entity.rujukan.SubUrusan;

/**
 * 
 * @author Shamsul Bahrin Abd Mutalib
 * @since Jun 2012
 *
 */
public class PPTPermohonanModule extends LebahRecordTemplateModule<PPTPermohonan> { // extends RecordTemplateModule2<PPTPermohonan> {
	
	protected long idNegeri;
	
	@Override
	public Class getIdType() {
		return Long.class;
	}
	
	public void setIdNegeri(long id) {
		idNegeri = id;
	}

	@Override
	public String getPath() {
		return "ekptg/ppt/permohonan";
	}

	@Override
	public void begin() {
		System.out.println("command=" + command);
	
		if ( idNegeri != 0 ) {
			clearFilter();
			this.addFilter("daerah.negeri.id = " + idNegeri);
			context.put("filterNegeri", true);
		}
		else {
			context.remove("filterNegeri");
		}
		
		this.setOrderBy("tarikhPermohonan");
		this.setOrderType("desc");
		
		prepareSelectOptions();
		
		//formatting
		context.put("dateFormat", new SimpleDateFormat("dd-MM-yyyy"));
		context.put("timeFormat", new SimpleDateFormat("hh:mm a"));	
		context.put("numFormat", new DecimalFormat("#.00"));
	}

	private void prepareSelectOptions() {
		//prepare list of kementerian
		List<Kementerian> kementerianList = db.list("select k from Kementerian k order by k.kod");
		context.put("kementerian_list", kementerianList);
		//prepare list of negeri
		if ( idNegeri == 0 ) {
			List<Negeri> negeriList =  db.list("select n from Negeri n order by n.kodNegeri");
			context.put("negeri_list", negeriList);
		}
		else {
			List<Negeri> negeriList =  db.list("select n from Negeri n where n.id = " + idNegeri);
			context.put("negeri_list", negeriList);
			
			List<Daerah> daerahList = db.list("select d from Daerah d where d.negeri.id = " + idNegeri + " order by d.kod");
			context.put("daerah_list", daerahList);
		}
		//ini adalah urusan pengambilan tanah
		//rujuk TBLRUJURUSAN, id untuk pengambilan ialah 17
		List<SubUrusan> subUrusanList = db.list("select su from SubUrusan su where su.urusan.id = 17 order by su.kod");
		context.put("suburusan_list", subUrusanList);
		
		//status JTS/JTU
		if ( idNegeri == 12 )
			context.put("jts_status_list", db.list("select s from PPTStatus s where s.kategori = 'SBH'"));
		else if ( idNegeri == 13 )
			context.put("jts_status_list", db.list("select s from PPTStatus s where s.kategori = 'SWK'"));
		else
			context.put("jts_status_list", db.list("select s from PPTStatus s"));
	}

	@Override
	public Class getPersistenceClass() {
		return PPTPermohonan.class;
	}

	@Override
	public Map searchCriteria() throws Exception {
		String findNoFail = get("find_noFail");
		String findNoRujukanPTG = get("find_noRujukanPTG");
		String findNoRujukanPTD = get("find_noRujukanPTD");
		String findNoRujukanUPT = get("find_noRujukanUPT");
		String findStatus = get("find_status");
		long status = !"".equals(findStatus) ? Long.parseLong(findStatus) : -99999;
		
		String findJtsStatus = get("find_jts_status");
		long jtsStatus = !"".equals(findJtsStatus) ? Long.parseLong(findJtsStatus) : -99999;
		
		context.put("find_nomborFail", findNoFail);
		context.put("find_noRujukanPTG", findNoRujukanPTG);
		context.put("find_noRujukanPTD", findNoRujukanPTD);
		context.put("find_noRujukanUPT", findNoRujukanUPT);
		context.put("find_status", status);
		context.put("find_jts_status", jtsStatus);

		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("fail.noFail", findNoFail);
		map.put("noRujukanPTG", findNoRujukanPTG);
		map.put("noRujukanPTD", findNoRujukanPTD);
		map.put("noRujukanUPT", findNoRujukanUPT);
		if ( status > 0 ) map.put("status.id", new Long(status));
		if ( jtsStatus > 0 ) map.put("jtsStatus.id", new Long(jtsStatus));
		return map;
	}

	@Override
	public boolean delete(PPTPermohonan r) throws Exception {
		System.out.println("ABOUT TO DELETE TBLPPTPERMOHONAN ID " + r.getId());
		//delete jika tiada tarikh permohonan
		if ( r.getTarikhPermohonan() == null ) return true;
		//atau tarikh permohonan adalah hari ini
		if ( r.getTarikhPermohonan() != null ) {
			String d1 = new SimpleDateFormat("dd-MM-yyyy").format(r.getTarikhPermohonan());
			String d2 = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			if ( d1.equals(d2)) return true;
			else return false;
		}
		return false;
	}


	@Override
	public void beforeSave() {
	}

	@Override
	public void save(PPTPermohonan r) throws Exception {
		Fail fail = r.getFail();
		if ( fail == null ) {
			//cipta Fail, fail akan dipersist melalui cascade=CascadeType.PERSIST
			fail = new Fail();
			r.setFail(fail);
			//STATUS - default status permohonan ialah id 11
			Status status = db.find(Status.class, new Long(11));
			r.setStatus(status);
		}
		//urusan pengambilan tanah
		String subUrusanId = get("suburusan_id");
		if ( !"".equals(subUrusanId)) {
			SubUrusan subUrusan = db.find(SubUrusan.class, Long.parseLong(subUrusanId));
			fail.setUrusan(subUrusan.getUrusan());
			fail.setSubUrusan(subUrusan);
		}

		r.setNoRujukanPTG(get("noRujukanPTG"));
		r.setNoRujukanPTD(get("noRujukanPTD"));
		r.setNoRujukanUPT(get("noRujukanUPT"));
		Date tarikhPermohonan = getDate("tarikhPermohonan");
		if ( tarikhPermohonan == null ) {
			tarikhPermohonan = new Date();
		}
		r.setTarikhPermohonan(tarikhPermohonan);
		

		String agensiId = get("agensi_id");
		if ( !"".equals(agensiId)) {
			Agensi agensi = db.find(Agensi.class, Long.parseLong(agensiId));
			r.setAgensi(agensi);
		}

		r.setFlagPeruntukan(get("flagPeruntukan"));
		r.setFlagSegera(get("flagSegera"));
		String negeriId = get("negeri_id");
		if ( !"".equals(negeriId)) {
			Negeri negeri = db.find(Negeri.class, Long.parseLong(negeriId));
			r.setNegeri(negeri);	
		}
		String daerahId = get("daerah_id");
		if ( !"".equals(daerahId)) {
			Daerah daerah = db.find(Daerah.class, Long.parseLong(daerahId));
			r.setDaerah(daerah);
		}
		r.setTujuan(get("tujuan"));
		r.setFlagJenisProjek(get("flagJenisProjek"));
		r.setNomborRujukanSurat(get("nomborRujukanSurat"));
		r.setTarikhSurat(getDate("tarikhSurat"));
		r.setTarikhKehendaki(getDate("tarikhKehendaki"));
	}

	@Override
	public void afterSave(PPTPermohonan r) {
		// TODO Auto-generated method stub
		try {
			createPengesahanJKPTG(r);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Override
	public void getRelatedData(PPTPermohonan r) {
		context.put("permohonan", r);
		//list agensi based on kementerian
		if ( r.getAgensi() != null ) {
			long kementerianId = r.getAgensi().getKementerian().getId();
			listAgensi(kementerianId);
		}
		//list daerah
		if ( r.getDaerah() != null ) {
			long negeriId = r.getDaerah().getNegeri().getId();
			List<Daerah> daerahList = db.list("select d from Daerah d where d.negeri.id = " + negeriId + " order by d.kod");
			context.put("daerah_list", daerahList);
		}

	}


	@Command("list_agensi")
	public String listAgensi() throws Exception {
		String kementerianId = request.getParameter("kementerian_id");
		listAgensi(kementerianId);
		return getPath() + "/vm/select_agensi.vm";
	}

	private void listAgensi(long kementerianId) {
		List<Agensi> agensiList = db.list("select a from Agensi a where a.kementerian.id = " + kementerianId + "order by a.kod");
		context.put("agensi_list", agensiList);
	}

	private void listAgensi(String kementerianId) {
		List<Agensi> agensiList = db.list("select a from Agensi a where a.kementerian.id = " + kementerianId + "order by a.kod");
		context.put("agensi_list", agensiList);
	}

	@Command("alamat_kementerian")
	public String getAlamatKementerian() throws Exception {
		String kementerianId = request.getParameter("kementerian_id");
		Kementerian kementerian = db.find(Kementerian.class, Long.parseLong(kementerianId));
		context.put("kementerian", kementerian);
		return getPath() + "/vm/alamat_kementerian2.vm";
	}

	@Command("list_daerah")
	public String listDaerah() throws Exception {
		String negeriId = request.getParameter("negeri_id");
		List<Daerah> daerahList = db.list("select d from Daerah d where d.negeri.id = " + negeriId + " order by d.kod");
		context.put("daerah_list", daerahList);
		return getPath() + "/vm/select_daerah.vm";
	}

	@Command("add_maklumat_tanah")
	public String addMaklumatTanah() throws Exception {
		context.remove("tanah");
		maklumatTanahSelectParams();
		String permohonanId = request.getParameter("ppt_permohonan_id");
		return getPath() + "/vm/maklumat_tanah.vm";
	}

	private void maklumatTanahSelectParams() {
		List<JenisHakmilik> jenisHakmilikList = db.list("select j from JenisHakmilik j order by j.kodJenisHakmilik");
		context.put("jenisHakmilik_list", jenisHakmilikList);
		//list kategori
		List<Kategori> kategoriList = db.list("select k from Kategori k order by k.kod");
		context.put("kategori_list", kategoriList);
		//luas
		List<Luas> luasList = db.list("select l from Luas l order by l.kod");
		context.put("unitLuas_list", luasList);
	}
	
	@Command("simpan_maklumat_k")
	public String simpanMaklumatBorangK() throws Exception {
		String hakmilikId = request.getParameter("hakmilik_id");
		PPTHakmilik hakmilik = db.find(PPTHakmilik.class, Long.parseLong(hakmilikId));
		context.put("tanah", hakmilik);

		boolean add = false;
		PPTBorangK borangK = null;
		Hashtable d = new Hashtable();
		d.put("hakmilik", hakmilik);
		List<PPTBorangK> listBorangK = db.list("select k from PPTBorangK k where k.hakmilik = :hakmilik", d);
		if ( listBorangK.size() > 0 ) {
			borangK = listBorangK.get(0);
		}
		
		PPTEndosanBorangK endosan = null;
		if ( borangK != null ) {
			Hashtable h = new Hashtable();
			h.put("borangK", borangK);
			//endosan = (PPTEndosanBorangK) 
			List<PPTEndosanBorangK> listEndosan = db.list("select e from PPTEndosanBorangK e where e.borangK = :borangK", h);
			if ( listEndosan.size() > 0 ) {
				endosan = listEndosan.get(0);
			}
			if ( endosan != null ) context.put("endosan", endosan);
			else {
				context.remove("endosan");
				endosan = new PPTEndosanBorangK();
			}
		} else {
			context.remove("endosan");
			add = true;
			borangK = new PPTBorangK();
			endosan = new PPTEndosanBorangK();
		}

		db.begin();
		
		borangK.setHakmilik(hakmilik);
		borangK.setPermohonan(hakmilik.getPermohonan());
		borangK.setTarikhBorangK(getDate("tarikhBorangK"));
		borangK.setCatatan(get("catatanBorangK"));
		
		borangK.setLS80(get("LS80"));
		borangK.setPampasan(get("pampasan"));
		borangK.setKosPerolehan(get("kosPerolehan"));
		borangK.setKaedahPerolehan(get("kaedahPerolehan"));
		
		endosan.setBorangK(borangK);
		endosan.setMasaCatatan(get("masaCatatan"));
		endosan.setJenisMasa(get("jenisMasa"));
		endosan.setNoPerserahan(get("noPerserahan"));
		endosan.setTarikhTerima(getDate("tarikhTerima"));
		endosan.setTarikhCatatan(getDate("tarikhCatatan"));
		
		if ( add ) {
			db.persist(borangK);
			db.persist(endosan);
		}
		
		db.commit();
		
		context.put("borangK", borangK);
		context.put("endosan", endosan);
		
		
		
		//simpan maklumat pengambilan
		simpanMaklumatPengambilan(hakmilik);
		
		
		return getPath() + "/vm/maklumat_borang_k.vm";
	}

	private void simpanMaklumatPengambilan(PPTHakmilik hakmilik) throws Exception {
		
		String jenisHakmilikId = get("temp_jenisHakmilik_id");
		JenisHakmilik jenisHakmilik = !"".equals(jenisHakmilikId) ? db.find(JenisHakmilik.class, Long.parseLong(jenisHakmilikId)) : null;
		
		String kategoriId = get("temp_kategori_id");
		Kategori kategori = !"".equals(kategoriId) ? db.find(Kategori.class, Long.parseLong(kategoriId)) : null;

		boolean addTemp = false;
		PPTTempHakmilik temp = null;
		Hashtable h = new Hashtable();
		h.put("hakmilik", hakmilik);
		List<PPTTempHakmilik> listTemp = db.list("select t from PPTTempHakmilik t where t.hakmilikAsal = :hakmilik", h);
		if ( listTemp.size() > 0 ) {
			temp = listTemp.get(0);
		}
		if ( temp == null ) {
			temp = new PPTTempHakmilik();
			addTemp = true;
		}
		
		db.begin();
		
		temp.setHakmilikAsal(hakmilik);
		
		temp.setJenisHakmilik(jenisHakmilik);
		temp.setKategori(kategori);

		temp.setNomborHakmilik(get("temp_nomborHakmilik"));
		temp.setTarikhDaftar(getDate("temp_tarikhDaftar"));
		temp.setNomborSyit(get("temp_nomborSyit"));
		temp.setNomborPT(get("temp_nomborPT"));
		temp.setNomborLot(get("temp_nomborLot"));
		
		if ( addTemp ) {
			db.persist(temp);
		}

		db.commit();
		
		context.put("temp", temp);
		
	}
	
	@Command("edit_maklumat_k")
	public String getMaklumatBorangK() throws Exception {
		String hakmilikId = request.getParameter("hakmilik_id");
		PPTHakmilik hakmilik = db.find(PPTHakmilik.class, Long.parseLong(hakmilikId));
		context.put("tanah", hakmilik);
		
		Hashtable d = new Hashtable();
		d.put("hakmilik", hakmilik);
		PPTBorangK borangK = null;
		List<PPTBorangK> list = db.list("select k from PPTBorangK k where k.hakmilik = :hakmilik", d);
		if ( list.size() > 0 ) {
			borangK = list.get(0);
			context.put("borangK", borangK);
		} else context.remove("borangK");
		
		if ( borangK != null ) {
			
			Hashtable h = new Hashtable();
			h.put("borangK", borangK);
			
			PPTEndosanBorangK endosan = null;
			List<PPTEndosanBorangK> listEndosan = db.list("select e from PPTEndosanBorangK e where e.borangK = :borangK", h);
			if ( listEndosan.size() > 0 ) {
				endosan = listEndosan.get(0);
				context.put("endosan", endosan);
			} else
				context.remove("endosan");
							
		} else context.remove("endosan");
		
		maklumatTanahSelectParams();
		
		//get maklumat tanah dpd PPTTempHakmilik
		
		Hashtable h = new Hashtable();
		h.put("hakmilikAsal", hakmilik);
		PPTTempHakmilik temp = null; 
		
		List<PPTTempHakmilik> listTemp = db.list("select t from PPTTempHakmilik t where t.hakmilikAsal = :hakmilikAsal", h);
		if ( listTemp.size() > 0 ) {
			temp = listTemp.get(0);
		}
		if ( temp != null ) context.put("temp", temp);
		else context.remove("temp");
		
		return getPath() + "/vm/maklumat_borang_k.vm";
	}

	@Command("edit_maklumat_tanah")
	public String getMaklumatTanah() throws Exception {
		//String permohonanId = request.getParameter("ppt_permohonan_id");
		String hakmilikId = request.getParameter("hakmilik_id");
		PPTHakmilik hakmilik = db.find(PPTHakmilik.class, Long.parseLong(hakmilikId));
		context.put("tanah", hakmilik);
		
		createNamaLuasAsal(hakmilik);
		createNamaLuasAmbil(hakmilik);

		maklumatTanahSelectParams();
		//list daerah
		List<Daerah> daerahList = db.list("select d from Daerah d where d.negeri.id = " + hakmilik.getNegeri().getId() + " order by d.kod");
		context.put("daerah_list", daerahList);
		//list mukim
		List<Mukim> mukimList = db.list("select m from Mukim m where m.daerah.id = " + hakmilik.getDaerah().getId() + " order by m.kod");
		context.put("mukim_list", mukimList);

		

		return getPath() + "/vm/maklumat_tanah.vm";

	}

	private void createNamaLuasAsal(PPTHakmilik hakmilik) {
		List<String> listNamaLuasAsal = new ArrayList<String>();
		context.put("listNamaLuasAsal", listNamaLuasAsal);
		if ( hakmilik.getNamaLuasAsal() != null && !"".equals(hakmilik.getNamaLuasAsal()) ) {
			if ( hakmilik.getNamaLuasAsal().indexOf(" ") > 0 ) {
				StringTokenizer tk = new StringTokenizer(hakmilik.getNamaLuasAsal(), " ");
				String s = "";
				while ( tk.hasMoreTokens() ) listNamaLuasAsal.add(tk.nextToken());
			}
		}
		context.put("_util", Util.getInstance());
	}
	
	private void createNamaLuasAmbil(PPTHakmilik hakmilik) {
		List<String> listNamaLuasAmbil = new ArrayList<String>();
		context.put("listNamaLuasAmbil", listNamaLuasAmbil);
		if ( hakmilik.getNamaLuasAmbil() != null && !"".equals(hakmilik.getNamaLuasAmbil()) ) {
			if ( hakmilik.getNamaLuasAmbil().indexOf(" ") > 0 ) {
				StringTokenizer tk = new StringTokenizer(hakmilik.getNamaLuasAmbil(), " ");
				String s = "";
				while ( tk.hasMoreTokens() ) listNamaLuasAmbil.add(tk.nextToken());
			}
		}
		context.put("_util", Util.getInstance());
	}
	
	@Command("simpan_hakmilik")
	public String simpanMaklumatTanah() throws Exception {
		String permohonanId = request.getParameter("ppt_permohonan_id");
		PPTPermohonan permohonan = db.find(PPTPermohonan.class, Long.parseLong(permohonanId));

		String hakmilikId = request.getParameter("hakmilik_id");
		PPTHakmilik hakmilik = null;
		boolean add = false;
		if ( hakmilikId == null || "".equals(hakmilikId)) {
			add = true;
			hakmilik = new PPTHakmilik();
		}
		else {
			hakmilik = db.find(PPTHakmilik.class, Long.parseLong(hakmilikId));
		}

		String negeriId = get("tanah_negeri_id");
		Negeri negeri = db.find(Negeri.class, Long.parseLong(negeriId));
		String daerahId = get("tanah_daerah_id");
		Daerah daerah = !"".equals(daerahId) ? db.find(Daerah.class, Long.parseLong(daerahId)) : null;
		String mukimId = get("tanah_mukim_id");
		Mukim mukim = !"".equals(mukimId) ? db.find(Mukim.class, Long.parseLong(mukimId)) : null;
		
		String jenisHakmilikId = get("jenisHakmilik_id");
		JenisHakmilik jenisHakmilik = !"".equals(jenisHakmilikId) ? db.find(JenisHakmilik.class, Long.parseLong(jenisHakmilikId)) : null;
		
		String kategoriId = get("kategori_id");
		Kategori kategori = !"".equals(kategoriId) ? db.find(Kategori.class, Long.parseLong(kategoriId)) : null;
		
		String unitLuasLotId = get("unitLuasLot_id");
		Luas unitLuas = null;
		List<String> unitKeluasan = new ArrayList<String>();
		String namaLuasAsal = "";
		if ( !"".equals(unitLuasLotId)) {
			unitLuas = db.find(Luas.class, Long.parseLong(unitLuasLotId));
			context.put("unit_keluasan", unitKeluasan);
			if ( unitLuas.getKeterangan().indexOf(",") > -1 ) {
				StringTokenizer st = new StringTokenizer(unitLuas.getKeterangan(), ",");
				while ( st.hasMoreTokens()) {
					String unit = st.nextToken().trim();
					unitKeluasan.add(unit);
				}
			}
			else {
				unitKeluasan.add(unitLuas.getKeterangan());
			}
			String[] luasAsal = request.getParameterValues("luas_asal");
			if ( luasAsal != null && unitKeluasan.size() > 0) {
				int i = 0;
				for ( String luas : luasAsal ) {
					namaLuasAsal += luas.concat(" ").concat(unitKeluasan.get(i).concat(" "));
					i++;
				}
			}
		}
		
		
		
		String unitLuasAmbilId = get("unitLuasAmbil_id");
		Luas unitLuasAmbil = null;
		List<String> unitKeluasanAmbil = new ArrayList<String>();
		String namaLuasAmbil = "";
		if ( !"".equals(unitLuasAmbilId)) {
			unitLuasAmbil = db.find(Luas.class, Long.parseLong(unitLuasAmbilId));
			context.put("unit_keluasan_ambil", unitKeluasanAmbil);
			if ( unitLuasAmbil.getKeterangan().indexOf(",") > -1 ) {
				StringTokenizer st = new StringTokenizer(unitLuasAmbil.getKeterangan(), ",");
				while ( st.hasMoreTokens()) {
					String unit = st.nextToken().trim();
					unitKeluasanAmbil.add(unit);
				}
			}
			else {
				unitKeluasanAmbil.add(unitLuasAmbil.getKeterangan());
			}
			String[] luasAmbil = request.getParameterValues("luas_ambil");
			if ( luasAmbil != null && unitKeluasanAmbil.size() > 0) {
				int i = 0;
				for ( String luas : luasAmbil ) {
					namaLuasAmbil += luas.concat(" ").concat(unitKeluasanAmbil.get(i).concat(" "));
					i++;
				}
			}
		}
		
		db.begin();
		
		//luas asal
		if ( unitLuas != null ) {
			hakmilik.setUnitLuasLot(unitLuas);
			hakmilik.setNamaLuasAsal(namaLuasAsal);
			
			//selepas convert ke meter sq
			String d_ = get("luas_asal_meter_sq");
			double d = 0.0d;
			try {
				d = Double.parseDouble(d_);
				hakmilik.setLuasLot(d);
			} catch ( Exception e ) {}
		}
		
		//luas ambil
		if ( unitLuasAmbil != null ) {
			hakmilik.setUnitLuasAmbil(unitLuasAmbil);
			hakmilik.setNamaLuasAmbil(namaLuasAmbil);
			
			//selepas convert ke meter sq
			String d_ = get("luas_ambil_meter_sq");
			double d = 0.0d;
			try {
				d = Double.parseDouble(d_);
				hakmilik.setLuasAmbil(d);
			} catch ( Exception e ) {}
		}

		
		hakmilik.setNegeri(negeri);
		hakmilik.setDaerah(daerah);
		hakmilik.setMukim(mukim);
		hakmilik.setJenisHakmilik(jenisHakmilik);
		hakmilik.setKategori(kategori);

		hakmilik.setNomborHakmilik(get("nomborHakmilik"));
		hakmilik.setTarikhDaftar(getDate("tarikhDaftar"));
		hakmilik.setNomborSyit(get("nomborSyit"));
		hakmilik.setNomborPT(get("nomborPT"));
		hakmilik.setNomborLot(get("nomborLot"));

		if ( add ) {
			hakmilik.setPermohonan(permohonan);
			if ( permohonan.getListHakmilik() == null ) permohonan.setListHakmilik(new ArrayList<PPTHakmilik>());
			permohonan.getListHakmilik().add(hakmilik);
			db.persist(hakmilik);
		}
		db.commit();

		//find back
		long id = hakmilik.getId();
		hakmilik = db.find(PPTHakmilik.class, new Long(id));
		context.put("tanah", hakmilik);
		createNamaLuasAsal(hakmilik);
		createNamaLuasAmbil(hakmilik);

		return getPath() + "/vm/maklumat_tanah.vm";
	}

	@Command("list_hakmilik")
	public String listHakmilik() throws Exception {
		String permohonanId = get("ppt_permohonan_id");
		PPTPermohonan p = db.find(PPTPermohonan.class, Long.parseLong(permohonanId));
		context.put("r", p);
		return getPath() + "/vm/list_hakmilik.vm";
	}
	
	@Command("hapus_hakmilik")
	public String hapusHakmilik() throws Exception {
		String permohonanId = get("ppt_permohonan_id");
		PPTPermohonan p = db.find(PPTPermohonan.class, Long.parseLong(permohonanId));
		context.put("r", p);
		
		String hakmilikId = get("hakmilik_id");
		PPTHakmilik hakmilik = db.find(PPTHakmilik.class, Long.parseLong(hakmilikId));
		db.begin();
		p.getListHakmilik().remove(hakmilik);
		db.remove(hakmilik);
		db.commit();
		
		return getPath() + "/vm/list_hakmilik.vm";
	}

	@Command("tanah_list_daerah")
	public String tanahListDaerah() throws Exception {
		String negeriId = request.getParameter("tanah_negeri_id");
		List<Daerah> daerahList = db.list("select d from Daerah d where d.negeri.id = " + negeriId + " order by d.kod");
		context.put("daerah_list", daerahList);
		return getPath() + "/vm/tanah_select_daerah.vm";
	}

	@Command("tanah_list_mukim")
	public String tanahListMukim() throws Exception {
		String daerahId = request.getParameter("tanah_daerah_id");
		List<Mukim> mukimList = db.list("select m from Mukim m where m.daerah.id = " + daerahId + " order by m.kod");
		context.put("mukim_list", mukimList);
		return getPath() + "/vm/tanah_select_mukim.vm";
	}

	@Command("add_dokumen")
	public String addDokumen() throws Exception {
		String permohonanId = get("ppt_permohonan_id");

		return getPath() + "/vm/maklumat_dokumen.vm";
	}



	@Command("list_dokumen")
	public String listDokumen() throws Exception {
		String permohonanId = request.getParameter("ppt_permohonan_id");
		PPTPermohonan p = db.find(PPTPermohonan.class, Long.parseLong(permohonanId));
		context.put("r", p);
		return getPath() + "/vm/list_dokumen.vm";
	}

	@Command("hapus_dokumen")
	public String hapusDokumen() throws Exception {
		String permohonanId = get("ppt_permohonan_id");
		PPTPermohonan p = db.find(PPTPermohonan.class, Long.parseLong(permohonanId));
		context.put("r", p);

		String dokumenId = get("dokumen_id");
		PPTDokumen dokumen = db.find(PPTDokumen.class, Long.parseLong(dokumenId));
		db.begin();
		p.getListDokumen().remove(dokumen);
		db.remove(dokumen);
		db.commit();
		return getPath() + "/vm/list_dokumen.vm";
	}

	@Command("save_dokumen")
	public String saveDokumen() throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List items = upload.parseRequest(request);
		Iterator itr = items.iterator();
		String permohonanId = "", tajuk = "", keterangan = "";
		List<FileItem> files = new ArrayList<FileItem>();
		while (itr.hasNext()) {
			FileItem item = (FileItem)itr.next();
			if ( ((item.isFormField())) ) {
				if ( "ppt_permohonan_id".equals((String)item.getFieldName())) permohonanId = (String) item.getString();
				if ( "tajuk".equals((String)item.getFieldName())) tajuk = (String) item.getString(); 
				if ( "keterangan".equals((String)item.getFieldName())) keterangan = (String) item.getString(); 
			}
			else if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
				files.add(item);
			}
		}

		PPTPermohonan permohonan = db.find(PPTPermohonan.class, Long.parseLong(permohonanId));
		for ( FileItem item : files ) {
			
			String fileName = item.getName();
			String fileType = item.getContentType();
			InputStream is = item.getInputStream();
			
			db.begin();
			PPTDokumen dokumen = new PPTDokumen();
			dokumen.setPermohonan(permohonan);
			dokumen.setTajuk(!"".equals(tajuk)? tajuk:"Tiada Tajuk");
			dokumen.setKeterangan(!"".equals(keterangan)? keterangan:"Tiada Keterangan");
			dokumen.setNamaFail(fileName);
			dokumen.setJenisMime(fileType);
			dokumen.setContent(IOUtils.toByteArray(is));
			db.persist(dokumen);
			db.commit();
			
			db.begin();
			if ( permohonan.getListDokumen() == null ) permohonan.setListDokumen(new ArrayList<PPTDokumen>());
			permohonan.getListDokumen().add(dokumen);
			db.commit();

			
		}
		
		return getPath() + "/vm/upload_doc.vm";
	}
	
	@Command("jts_status_log")
	public String JTSStatusUpdateLog() throws Exception {
		//String permohonanId = get("log_status_permohonan_id");
		String permohonanId = get("ppt_permohonan_id");
		
		PPTPermohonan p = db.find(PPTPermohonan.class, Long.parseLong(permohonanId));
		context.put("permohonan", p);
		Hashtable h = new Hashtable();
		h.put("permohonanId", Long.parseLong(permohonanId));
		String sql = "select p from PPTStatusUpdate p where p.permohonan.id = :permohonanId order by p.tarikhKemaskini desc";
		List<PPTStatusUpdate> logs = db.list(sql, h);
		context.put("logs", logs);
		return getPath() + "/vm/jts_status_log.vm";
	}
	
	@Command("jts_status_update")
	public String JTSStatusUpdate() throws Exception {
		//String permohonanId = get("update_status_permohonan_id");
		String permohonanId = get("status_permohonan_id");
		PPTPermohonan p = db.find(PPTPermohonan.class, Long.parseLong(permohonanId));
		context.put("permohonan", p);
		return getPath() + "/vm/jts_status_update.vm";
	}

	@Command("save_jts_status_update")
	public String SaveJTSStatusUpdate() throws Exception {
		String permohonanId = get("status_permohonan_id");
		PPTPermohonan p = db.find(PPTPermohonan.class, Long.parseLong(permohonanId));
		
		String statusId = get("jts_status_id");
		PPTStatus status = null;
		if ( !"".equals(statusId)) {
			status = db.find(PPTStatus.class, Long.parseLong(statusId));
			if ( status != null ) {
				db.begin();
				p.setJtsStatus(status);
				db.commit();
				
			}
		}
		else {
			db.begin();
			p.setJtsStatus(null);
			db.commit();
		}
		
		//status update log
		
		User user = null;
		if ( !"".equals(userId)) {
			//user = (User) db.get("select u from etapp.entity.User u where u.login = '" + userId + "'");
			user = (User) db.get("select u from User u where u.login = '" + userId + "'");
		}
		
		String remark = get("jts_status_remark");
		db.begin();
		PPTStatusUpdate statusLog = new PPTStatusUpdate();
		statusLog.setPermohonan(p);
		statusLog.setStatus(status);
		
//		new SimpleDateFormat("dd-MM-yyyy hh:mm a").format(new Date());
		statusLog.setTarikhKemaskini(new Date());
		statusLog.setRemark(remark);
		if ( user != null ) statusLog.setUser(user);
		db.persist(statusLog);
		db.commit();
		
		db.begin();
		p.setJtsStatusUpdateLog(statusLog);
		db.commit();
		
		context.put("permohonan", p);
		
		//return getPath() + "/vm/div_jts_status_update.vm";
		
		Hashtable h = new Hashtable();
		h.put("permohonanId", Long.parseLong(permohonanId));
		
		String sql = "select p from PPTStatusUpdate p where p.permohonan.id = :permohonanId order by p.tarikhKemaskini desc";
		List<PPTStatusUpdate> logs = db.list(sql, h);
		context.put("logs", logs);
		return getPath() + "/vm/jts_status_log.vm";

	}
	
	public static Date parseDateTime(String dateTxt) {
		if ( dateTxt != null && !"".equals(dateTxt)) {
			try {
				return new SimpleDateFormat("dd-MM-yyyy hh:mm a").parse(dateTxt);
			} catch (ParseException e) {
				return null;
			}
		}
		return null;
	}
	
	@Command("convert_luas_asal")
	public String convertLuasAsal() throws Exception {
		String unitLuasLotId = get("unitLuasLot_id");
		String[] luasAsal = request.getParameterValues("luas_asal");
		Luas unitLuas = db.find(Luas.class, Long.parseLong(unitLuasLotId));
		double total = convertLuasByFormula(unitLuas, luasAsal);
		context.put("luas_asal_meter_sq", total);
		return getPath() + "/vm/luas_asal_meter_sq.vm";
	}
	
	@Command("convert_luas_ambil")
	public String convertLuasAmbil() throws Exception {
		String unitLuasAmbilId = get("unitLuasAmbil_id");
		String[] luasAmbil = request.getParameterValues("luas_ambil");
		Luas unitLuas = db.find(Luas.class, Long.parseLong(unitLuasAmbilId));
		double total = convertLuasByFormula(unitLuas, luasAmbil);
		context.put("luas_ambil_meter_sq", total);
		return getPath() + "/vm/luas_ambil_meter_sq.vm";
	}


	private double convertLuasByFormula(Luas unitLuas, String[] luasAsal) {
		List<String> unitKeluasan = new ArrayList<String>();
		if ( unitLuas != null ) {
			if ( unitLuas.getKeterangan().indexOf(",") > -1 ) {
				StringTokenizer st = new StringTokenizer(unitLuas.getKeterangan(), ",");
				while ( st.hasMoreTokens()) {
					String unit = st.nextToken().trim();
					unitKeluasan.add(unit);
				}
			}
			else {
				unitKeluasan.add(unitLuas.getKeterangan());
			}
			List<String> formulaUnitAsal = new ArrayList<String>();
			if ( unitLuas.getFormulaUnitAsal() != null && !"".equals(unitLuas.getFormulaUnitAsal())) {
				if ( unitLuas.getFormulaUnitAsal().indexOf(",") > -1 ) {
					StringTokenizer st = new StringTokenizer(unitLuas.getFormulaUnitAsal(), ",");
					while ( st.hasMoreElements() ) {
						formulaUnitAsal.add(st.nextToken().trim());
					}
				}
				else {
					formulaUnitAsal.add(unitLuas.getFormulaUnitAsal());
				}
			}
			double total = 0.0d;
			if ( luasAsal != null && unitKeluasan.size() > 0) {
				int i = 0;
				for ( String luas : luasAsal ) {
					try {
						double d = Double.parseDouble(luas);
						double f = Double.parseDouble(formulaUnitAsal.get(i));
						total += d*f;
					} catch ( Exception e ) {
						e.printStackTrace();
					}
					i++;
				}
			}
			total = total * unitLuas.getUnitConversion();
			return total;
		}
		return 0;
	}
	
	@Command("pengesahan_jkptg")
	public String pengesahanJKPTG() throws Exception {
		
 		
		String permohonanId = get("ppt_permohonan_id");
		PPTPermohonan p = db.find(PPTPermohonan.class, Long.parseLong(permohonanId));
		
		createPengesahanJKPTG(p);
		
		return getPath() + "/vm/pengesahan_jkptg.vm";
	}

	private void createPengesahanJKPTG(PPTPermohonan p) throws DbException,
			Exception {
		//String negeri = p.getDaerah().getNegeri().getAbbrev();
		//String daerah = p.getDaerah().getKod();
		//String kementerian = p.getFail().getKementerian().getKod();
		long negeri = p.getDaerah().getNegeri().getId();
		//long seksyen = p.getFail().getSeksyen().getId();
		//long urusan = p.getFail().getUrusan().getId();

 		int seksyen = 1;
 		//int urusan = 17;
 		long urusan = p.getFail().getSubUrusan().getUrusan().getId();
 		System.out.println("urusan = " + urusan);

		int seq = File.getSeqNo(seksyen, (int) urusan, 0, (int) negeri);
		context.put("seq", seq);
		
		Date now = new Date();
    	SimpleDateFormat formatter =  new SimpleDateFormat("yyyy");
    	String tahun = formatter.format(now);
    	//-- Generate no Fail "JKPTG(S).NEGERI/KOD DAERAH/881/kod_kementerian/TAHUN-seq_fail
    	//-- Untuk N.Sembilan "JKPTG.NS(S)/UPT/KOD DAERAH/KOD KEMENTERIAN/TAHUN/SEQ-FAIL  "
    	
    	String kodNegeri = p.getDaerah().getNegeri().getAbbrev();
    	String kodDaerah = p.getDaerah().getKod();
    	String kodKementerian = p.getAgensi().getKementerian().getKod();
    	//String kodUrusan = p.getFail().getSubUrusan().getUrusan().getKod();
    	
 		String noFail = "JKPTG(" + kodNegeri + ")/" + kodDaerah +"/881/" + kodKementerian + "/" + tahun + "/" + seq;
 		context.put("noFail", noFail);
 		
 		db.begin();
 		p.setNomborPermohonan(noFail);
 		p.getFail().setNoFail(noFail);
 		db.commit();
	}
	
	@Command("list_maklumat_asal")
	public String listMaklumatAsal() throws Exception {
		
		String permohonanId = get("ppt_permohonan_id");
		System.out.println("permohonan id = " + permohonanId);
		PPTPermohonan p = db.find(PPTPermohonan.class, Long.parseLong(permohonanId));
		
		context.put("r", p);
		
		return getPath() + "/vm/list_hakmilik_3.vm";
	}

	
	@Command("list_selepas_pengambilan")
	public String listSelepasPengambilan() throws Exception {
		
		String permohonanId = get("ppt_permohonan_id");
		System.out.println("permohonan id = " + permohonanId);
		PPTPermohonan p = db.find(PPTPermohonan.class, Long.parseLong(permohonanId));
		
		Hashtable h = new Hashtable();
		h.put("permohonan", p);
		List<PPTTempHakmilik> pengambilanList = db.list("select r from PPTTempHakmilik r where r.hakmilikAsal.permohonan = :permohonan", h);
		context.put("pengambilanList", pengambilanList);
		
		return getPath() + "/vm/list_selepas_pengambilan.vm";
	}


}
