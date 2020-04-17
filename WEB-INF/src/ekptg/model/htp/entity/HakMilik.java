package ekptg.model.htp.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import ekptg.model.entities.Agensi;
import ekptg.model.entities.Daerah;
import ekptg.model.entities.Mukim;
import ekptg.model.entities.Negeri;
import ekptg.model.entities.Seksyen;
import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.entities.Tblrujlot;
import ekptg.model.htp.cukai.entity.CukaiTemp;

public class HakMilik implements Serializable {

	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");	
	private long idHakmilik;
	private long idPermohonan;
	private long idSubkategori;
	private long idNegeri;
	private long idDaerah;
	private long idMukim;
	private long idJenisHakmilik;
	private long idJenisLot;
	private String noHakmlik;
	private String noHakmilik;
	private String noHakmilikAsal;
	private String noHakmilikBerikut;
	private String noWarta;
	private String noLot;
	private String noFail;
	private String noFailPTD;
	private String noFailPTG;
	private String noFailKJP;
	private String noFailJOPA;
	private String noFailHakmilik;
	private String perolehan;
	private String rizab;
	private String statusRizab;
	private String noRizab;
	private Date tarikhRizab;
	private String tarikhRizabStr;
	private String idRizab;
	private String kawasanRizab;
	private long idLuas;
	private double luas;
	private double luasBersamaan;
	private String luasString;
	private String statusGeran;
	private String statusSah;
	private long idMasuk;
	private String namaKemaskini;
	private HakmilikAgensi hakmilikAgensi;
	private HakmilikCukai hakmilikCukai;
	private CukaiTemp hakmilikCTemp;
	private String peganganHakmilik;
	private long idKategori;
	Agensi agensi;
	Negeri negeri;
	Daerah daerah;
	Mukim mukim;
	Seksyen sek;
	private Tblrujlot rujLot;
	private Tblrujjenishakmilik rujJenisHakmilik;
	private String kegunaan;
	private Date tarikhDaftar;
	private Date tarikhKemaskini;
	private Date tarikhLuput;
	private Date tarikhTerima;
	private Date tarikhWarta;
	private String tarikhDaftarStr;
	private String tarikhKemaskiniStr;
	private String tarikhLuputStr;
	private String tarikhTerimaStr;
	private String tarikhWartaStr;
	private String catatan;
	private String lokasi;
	private String noPelan;
	private String noSyit;
	private String noPu;
	private String sekatan;
	private String syarat;
	private String taraf;
	private String tempoh;
	private HakmilikStrata strata;
	private Permohonan permohonan;
	private StatusSah ss;
	private Date tarikhMasuk;
	private String tarikhMasukStr;
	private HakmilikUrusanTanah hUrusan;
	private Integer bilangan;
	private long idDB;
	private Integer idJenisRizab;

	public long getIdHakmilik() {
		return idHakmilik;
	}
	public void setIdHakmilik(long idHakmilik) {
		this.idHakmilik = idHakmilik;
	}
	public long getIdPermohonan() {
		return idPermohonan;
	}
	public void setIdPermohonan(long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}
	public long getIdSubkategori() {
		return idSubkategori;
	}
	public void setIdSubkategori(long idSubkategori) {
		this.idSubkategori = idSubkategori;
	}
	public long getIdNegeri() {
		return idNegeri;
	}
	public void setIdNegeri(long idNegeri) {
		this.idNegeri = idNegeri;
	}
	public long getIdDaerah() {
		return idDaerah;
	}
	public void setIdDaerah(long idDaerah) {
		this.idDaerah = idDaerah;
	}
	public long getIdMukim() {
		return idMukim;
	}
	public void setIdMukim(long idMukim) {
		this.idMukim = idMukim;
	}
	public long getIdJenisHakmilik() {
		return idJenisHakmilik;
	}
	public void setIdJenisHakmilik(long idJenisHakmilik) {
		this.idJenisHakmilik = idJenisHakmilik;
	}
	public long getIdJenisLot() {
		return idJenisLot;
	}
	public void setIdJenisLot(long idJenisLot) {
		this.idJenisLot = idJenisLot;
	}
	public String getNoHakmlik() {
		if (this.noHakmlik == null)noHakmlik = "";
		return noHakmlik;
	}
	public void setNoHakmlik(String noHakmlik) {
		this.noHakmlik = noHakmlik;
	}
	public String getNoLot() {
		if (this.noLot == null)noLot = "";
		return noLot;
	}
	public void setNoLot(String noLot) {
		this.noLot = noLot;
	}
	public long getIdLuas() {
		return idLuas;
	}
	public void setIdLuas(long idLuas) {
		this.idLuas = idLuas;
	}
	public double getLuas() {
		return luas;
	}
	public void setLuas(double luas) {
		this.luas = luas;
	}
	
	public String getStatusGeran() {
		if (this.statusGeran == null)statusGeran = "";
		return statusGeran;
	}
	public void setStatusGeran(String statusGeran) {
		this.statusGeran = statusGeran;
	}

	public String getStatusSah() {
		if (this.statusSah == null)statusSah = "";
		return statusSah;
	}
	public void setStatusSah(String statusSah) {
		this.statusSah = statusSah;
	}
	public long getIdMasuk() {
		return idMasuk;
	}
	public void setIdMasuk(long idMasuk) {
		this.idMasuk = idMasuk;
	}
	public HakmilikAgensi getHakmilikAgensi() {
		return hakmilikAgensi;
	}
	public void setHakmilikAgensi(HakmilikAgensi hakmilikAgensi) {
		this.hakmilikAgensi = hakmilikAgensi;
	}
	public HakmilikCukai getHakmilikCukai() {
		return hakmilikCukai;
	}
	public void setHakmilikCukai(HakmilikCukai hakmilikCukai) {
		this.hakmilikCukai = hakmilikCukai;
	}
	public String getPeganganHakmilik() {
		return peganganHakmilik;
	}
	public void setPeganganHakmilik(String peganganHakmilik) {
		this.peganganHakmilik = peganganHakmilik;
	}
	public long getIdKategori() {
		return idKategori;
	}
	public void setIdKategori(long idKategori) {
		this.idKategori = idKategori;
	}

	public String getNoFail() {
		if (this.noFail == null)noFail = "";
		return noFail;
	}
	public void setNoFail(String noFail) {
		this.noFail = noFail;
	}

	public String getNoFailPTD() {
		if (this.noFailPTD == null)noFailPTD = "";
		return noFailPTD;
	}
	public void setNoFailPTD(String noFailPTD) {
		this.noFailPTD = noFailPTD;
	}	

	public String getNoFailPTG() {
		if (this.noFailPTG == null)noFailPTG = "";
		return noFailPTG;
	}
	public void setNoFailPTG(String noFailPTG) {
		this.noFailPTG = noFailPTG;
	}	

	public Agensi getAgensi() {
		if (this.agensi == null)agensi = null;
		return agensi;
	}
	public void setAgensi(Agensi agensi) {
		this.agensi = agensi;
	}
	
	public Negeri getNegeri() {
		if (this.negeri == null)negeri = null;
		return negeri;
	}
	public void setNegeri(Negeri negeri) {
		this.negeri = negeri;
	}
	
	public Daerah getDaerah() {
		if (this.daerah == null)daerah = null;
		return daerah;
	}
	public void setDaerah(Daerah daerah) {
		this.daerah = daerah;
	}
	
	public Mukim getMukim() {
		if (this.mukim == null)mukim = null;
		return mukim;
	}
	public void setMukim(Mukim mukim) {
		this.mukim = mukim;
	}
	
	public Tblrujlot getRujLot() {
		return rujLot;
	}
	public void setRujLot(Tblrujlot rujLot) {
		this.rujLot = rujLot;
	}
	public Tblrujjenishakmilik getRujJenisHakmilik() {
		return rujJenisHakmilik;
	}
	public void setRujJenisHakmilik(Tblrujjenishakmilik rujJenisHakmilik) {
		this.rujJenisHakmilik = rujJenisHakmilik;
	}
	public String getPerolehan() {
		if (this.perolehan == null)perolehan = "";
		return perolehan;
	}
	public void setPerolehan(String perolehan) {
		this.perolehan = perolehan;
	}
	
	public double getLuasBersamaan() {
		return luasBersamaan;
	}
	public void setLuasBersamaan(double luasBersamaan) {
		this.luasBersamaan = luasBersamaan;
	}
	public String getLuasString() {
		return luasString;
	}
	public void setLuasString(String luasString) {
		this.luasString = luasString;
	}

	public String getKegunaan() {
		return kegunaan;
	}
	public void setKegunaan(String kegunaan) {
		this.kegunaan = kegunaan;
	}	
	
	public Date getTarikhDaftar() {
		return tarikhDaftar;
	}
	public String getTarikhDaftarFormat() {
		if(sdf.format(tarikhDaftar).equals("01/01/1900"))
			return "";
		else
			return sdf.format(tarikhDaftar);
	}
	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}

	public Date getTarikhWarta() {
		return tarikhWarta;
	}
	public String getTarikhWartaFormat() {
		if(sdf.format(tarikhWarta).equals("01/01/1900"))
			return "";
		else
			return sdf.format(tarikhWarta);
	}
	public void setTarikhWarta(Date tarikhWarta) {
		this.tarikhWarta = tarikhWarta;
	}
	
	public Date getTarikhRizab() {
		return tarikhRizab;
	}
	public String getTarikhRizabFormat() {
		if(sdf.format(tarikhRizab).equals("01/01/1900"))
			return "";
		else
			return sdf.format(tarikhRizab);
	}
	public void setTarikhRizab(Date tarikhRizab) {
		this.tarikhRizab = tarikhRizab;
	}

	public Date getTarikhTerima() {
		return tarikhTerima;
	}
	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public Date getTarikhKemaskini() {
		return tarikhKemaskini;
	}
	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}
	
	public String getTarikhDaftarStr() {
		return tarikhDaftarStr;
	}
	public void setTarikhDaftarStr(String tarikhDaftarStr) {
		this.tarikhDaftarStr = tarikhDaftarStr;
	}

	public String getTarikhWartaStr() {
		return tarikhWartaStr;
	}
	public void setTarikhWartaStr(String tarikhWartaStr) {
		this.tarikhWartaStr = tarikhWartaStr;
	}
	
	public String getTarikhRizabStr() {
		return tarikhRizabStr;
	}
	public void setTarikhRizabStr(String tarikhRizabStr) {
		this.tarikhRizabStr = tarikhRizabStr;
	}
	
	public void setTarikhTerimaStr(String tarikhTerimaStr) {
		this.tarikhTerimaStr = tarikhTerimaStr;
	}
	public String getTarikhTerimaStr() {
		return tarikhTerimaStr;
	}

	public void setTarikhLuputStr(String tarikhLuputStr) {
		this.tarikhLuputStr = tarikhLuputStr;
	}
	public String getTarikhLuputStr() {
		return tarikhLuputStr;
	}
	
	public void setTarikhKemaskiniStr(String tarikhKemaskiniStr) {
		this.tarikhKemaskiniStr = tarikhKemaskiniStr;
	}
	public String getTarikhKemaskiniStr() {
		return tarikhKemaskiniStr;
	}
	
	public String getCatatan() {
		return catatan;
	}
	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}	
	
	public String getNoWarta() {
		return noWarta;
	}
	public void setNoWarta(String noWarta) {
		this.noWarta = noWarta;
	}	

	public String getNamaKemaskini() {
		return namaKemaskini;
	}
	public void setNamaKemaskini(String namaKemaskini) {
		this.namaKemaskini = namaKemaskini;
	}	
	
	public String getLokasi() {
		return lokasi;
	}
	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}	
	
	public String getNoPelan() {
		return noPelan;
	}
	public void setNoPelan(String noPelan) {
		this.noPelan = noPelan;
	}	

	public String getNoSyit() {
		return noSyit;
	}
	public void setNoSyit(String noSyit) {
		this.noSyit = noSyit;
	}

	public String getNoPU() {
		return noPu;
	}
	public void setNoPU(String noPu) {
		this.noPu = noPu;
	}	
	
	public String getNoHakmilik() {
		if (this.noHakmilik == null)noHakmilik = "";
		return noHakmilik;
	}
	
	public void setNoHakmilik(String noHakmilik) {
		this.noHakmilik = noHakmilik;
	}
	
	public String getNoHakmilikAsal() {
		if (this.noHakmilikAsal == null)noHakmilikAsal = "";
		return noHakmilikAsal;
	}	
	public void setNoHakmilikAsal(String noHakmilikAsal) {
		this.noHakmilikAsal = noHakmilikAsal;
	}
	
	public String getNoHakmilikBerikut() {
		if (this.noHakmilikBerikut == null)noHakmilikBerikut = "";
		return noHakmilikBerikut;
	}	
	public void setNoHakmilikBerikut(String noHakmilikBerikut) {
		this.noHakmilikBerikut = noHakmilikBerikut;
	}
	
	public String getTaraf() {
		if (this.taraf == null)taraf = "";
		return taraf;
	}	
	public void setTaraf(String taraf) {
		this.taraf = taraf;
	}	
	
	public String getNoFailKJP() {
		if (this.noFailKJP == null)noFailKJP = "";
		return noFailKJP;
	}
	public void setNoFailKJP(String noFailKJP) {
		this.noFailKJP = noFailKJP;
	}
	
	public String getNoFailJOPA() {
		if (this.noFailJOPA == null)noFailJOPA = "";
		return noFailJOPA;
	}
	public void setNoFailJOPA(String noFailJOPA) {
		this.noFailJOPA = noFailJOPA;
	}

	public CukaiTemp getHakmilikCTemp() {
		return hakmilikCTemp;
	}
	public void setHakmilikCTemp(CukaiTemp hakmilikCTemp) {
		this.hakmilikCTemp = hakmilikCTemp;
	}	
	
	public String getSekatan() {
		if (this.sekatan == null)sekatan = "";
		return sekatan;
	}
	public void setSekatan(String sekatan) {
		this.sekatan = sekatan;
	}	
	
	public String getSyarat() {
		if (this.syarat == null)syarat = "";
		return syarat;
	}
	
	public void setSyarat(String syarat) {
		this.syarat = syarat;
	}
	
	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}
	
	public StatusSah getStatus() {
		return ss;
	}
	public void setStatus(StatusSah ss) {
		this.ss = ss;
	}
	
	public HakmilikStrata getStrata() {
		return strata;
	}
	public void setStrata(HakmilikStrata strata) {
		this.strata = strata;
	}	

	public String getTempoh() {
		if (this.tempoh == null)tempoh = "";
		return tempoh;
	}	
	public void setTempoh(String tempoh) {
		this.tempoh = tempoh;
	}	
	
	public Date getTarikhLuput() {
		return tarikhLuput;
	}
	public String getTarikhLuputFormat() {
		if(sdf.format(tarikhLuput).equals("01/01/1900"))
			return "";
		else
			return sdf.format(tarikhLuput);
	}
	public void setTarikhLuput(Date tarikhLuput) {
		this.tarikhLuput = tarikhLuput;
	}

	public String getNoRizab() {
		if (this.noRizab == null)noRizab = "";
		return noRizab;
	}	
	public void setNoRizab(String noRizab) {
		this.noRizab = noRizab;
	}

	public String getKawasanRizab() {
		if (this.kawasanRizab == null)kawasanRizab = "";
		return kawasanRizab;
	}	
	public void setKawasanRizab(String kawasanRizab) {
		this.kawasanRizab = kawasanRizab;
	}
	
	public String getRizab() {
		return rizab;
	}
	public void setRizab(String rizab) {
		this.rizab = rizab;
	}
	
	public String getIdRizab() {
		return idRizab;
	}
	public void setIdRizab(String idRizab) {
		this.idRizab = idRizab;
	}

	public String getStatusRizab() {
		return statusRizab;
	}
	public void setStatusRizab(String statusRizab) {
		this.statusRizab = statusRizab;
	}
	
	public Date getTarikhMasuk() {
		return tarikhMasuk;
	}
	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}
	
	public void setTarikhMasukStr(String tarikhMasukStr) {
		this.tarikhMasukStr = tarikhMasukStr;
	}
	public String getTarikhMasukStr() {
		return tarikhMasukStr;
	}
	
	public HakmilikUrusanTanah getUrusan() {
		return hUrusan;
	}
	public void setUrusan(HakmilikUrusanTanah hUrusan) {
		this.hUrusan = hUrusan;
	}
	
	public Integer getBil() {
		return bilangan;
	}
	public void setBil(Integer bilangan) {
		this.bilangan = bilangan;
	}

	public Integer getIdJenisRizab() {
		return idJenisRizab;
	}
	public void setIdJenisRizab(Integer idJenisRizab) {
		this.idJenisRizab = idJenisRizab;
	}

	public String getNoFailHakmilik() {
		if (this.noFailHakmilik == null)noFailHakmilik = "";
		return noFailHakmilik;
	}
	public void setNoFailHakmilik(String noFailHakmilik) {
		this.noFailHakmilik = noFailHakmilik;
	}
	
	public long getIdDB() {
		return idDB;
	}
	public void setIdDB(long idDB) {
		this.idDB = idDB;
	}
	
	public Seksyen getSeksyen() {
		return sek;
	}
	public void setSeksyen(Seksyen sek) {
		this.sek = sek;
	}
	
}
