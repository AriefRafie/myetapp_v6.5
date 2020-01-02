package etapp.entity.htp.rekod;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import etapp.entity.Permohonan;
import etapp.entity.rujukan.Agensi;
import etapp.entity.rujukan.Daerah;
import etapp.entity.rujukan.JenisHakmilik;
import etapp.entity.rujukan.JenisRizab;
import etapp.entity.rujukan.Kategori;
import etapp.entity.rujukan.Kementerian;
import etapp.entity.rujukan.Lot;
import etapp.entity.rujukan.Luas;
import etapp.entity.rujukan.Mukim;
import etapp.entity.rujukan.Negeri;
import etapp.entity.rujukan.SubKategori;

@Entity
@Table(name = "TBLHTPHAKMILIK")
public class Hakmilik{
	@Id @Column(name="ID_HAKMILIK")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;
	
	@ManyToOne @JoinColumn(name="ID_NEGERI")
	private Negeri negeri;
	@ManyToOne @JoinColumn(name="ID_DAERAH")
	private Daerah daerah;
	@ManyToOne @JoinColumn(name="ID_MUKIM")
	private Mukim mukim;

	@ManyToOne @JoinColumn(name="ID_JENISHAKMILIK")
	private JenisHakmilik jenisHakmilik;	
	@ManyToOne @JoinColumn(name="ID_LOT")
	private Lot lot;		
	@ManyToOne @JoinColumn(name="ID_KEMENTERIAN")
	private Kementerian kementerian;
	@ManyToOne @JoinColumn(name="ID_AGENSI")
	private Agensi agensi;
	@ManyToOne @JoinColumn(name="ID_LUAS")
	private Luas luasLama;		
	@ManyToOne @JoinColumn(name="ID_LUAS_BERSAMAAN")
	private Luas luasBaru;		
	@ManyToOne @JoinColumn(name="ID_KATEGORI")
	private Kategori kategori;		
	@ManyToOne @JoinColumn(name="ID_SUBKATEGORI")
	private SubKategori subKategori;		
	@ManyToOne @JoinColumn(name="ID_RIZAB")
	private JenisRizab jenisRizab;		
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="hakmilik")
	private List<HakmilikAgensi> listHakmilikAgensi;
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="hakmilik")
	private List<HakmilikCukai> listHakmilikCukai;
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="hakmilik")
	private List<HakmilikPerihal> listHakmilikPerihal;
	
//	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="hakmilik")
//	private List<MaklumatPembangunan> listMaklumatPembangunan;

	@Column(name="PEGANGAN_HAKMILIK")	
	private String peganganHakmilik;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TERIMA")
	private Date tarikhTerima;

	@Column(name="NO_HAKMILIK")
	private String noHakmilik;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_DAFTAR")
	private Date tarikhDaftar;
	@Column(name="NO_BANGUNAN")
	private String noBangunan;
	@Column(name="NO_TINGKAT")
	private String noTingkat;
	@Column(name="NO_PETAK")
	private String noPetak;
	@Column(name="CUKAI") 
	private double cukai = 0.00;
	@Column(name="CUKAI_TERKINI") 
	private double cukaiTerkini = 0.00;
	@Column(name="STATUS_RIZAB") 
	private String statusRizab ;
	@Column(name="TEMPOH")
	private int tempoh;
	@Column(name="KAWASAN_RIZAB")
	private String kawasanRizab;
	@Column(name="NO_RIZAB")
	private String noRizab;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_RIZAB")
	private Date tarikhRizab;

	@Column(name="NO_WARTA")
	private String noWarta;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_WARTA")
	private Date tarikhWarta;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_LUPUT")
	private Date tarikhLuput;
		
	@Column(name="NO_LOT")
	private String noLot;
	@Column(name="LUAS")
	private String luas;
	@Column(name="LUAS_BERSAMAAN")
	private double luasBersamaan;
	@Column(name="SYARAT") 
	private String syarat;
	@Column(name="SEKATAN") 
	private String sekatan;
	@Column(name="NO_PELAN") 
	private String noPelan;
	@Column(name="NO_SYIT") 
	private String noSyit;
	@Column(name="NO_PU") 
	private String noPU;
	@Column(name="STATUS_GERAN") 
	private String statusGeran ;

	@Column(name="STATUS_SWASTA") 
	private String statusSwasta ;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MOHON_SWASTA")
	private Date tarikhMohonSwasta;	
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_SWASTA")
	private Date tarikhSwasta;	
	@Column(name="STATUS_PAJAKAN") 
	private String statusPajakan ;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MOHON_PAJAKAN")
	private Date tarikhMohonPajakan;	
	@Column(name="STATUS_SEWA") 
	private String statusSewa ;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MOHON_SEWA")
	private Date tarikhMohonSewa;	
	@Column(name="STATUS_PELEPASAN") 
	private String statusPelepasan ;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MOHON_PELEPASAN")
	private Date tarikhMohonPelepasan;	

	@Column(name="LOKASI")
	private String lokasi;
	@Column(name="STATUS_SAH") 
	private String statusSah ;
	@Column(name="NO_FAIL_HAKMILIK") 
	private String noFailHakmilik;
	@Column(name="TARAF_HAKMILIK") 
	private String tarafHakmilik;
	@Column(name="HAKMILIK_ASAL") 
	private String hakmilikAsal;
	@Column(name="HAKMILIK_BERIKUT") 
	private String hakmilikBerikut;
	@Column(name="PEGANGAN_HAKMILIK_TUKARGANTI") 
	private String hakmilikTukarganti;
	@Column(name="NO_FAIL") 
	private String noFail;
	@Column(name="NO_FAIL_PTG") 
	private String noFailPTG;
	@Column(name="NO_FAIL_PTD") 
	private String noFailPTD;
	@Column(name="NO_FAIL_KJP") 
	private String noFailKJP;
	@Column(name="NO_FAIL_AGENSI") 
	private String noFailAgensi;
	@Column(name="NO_FAIL_JOFA") 
	private String noFailJofa;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_BUKA_FAIL")
	private Date tarikhBukaFail;
	@Column(name="NO_PERSERAHAN") 
	private String noPerserahan;
	@Column(name="CARA_DAPAT")
	private String caraDapat;
	@Column(name="ID_HAKMILIKSPTB")
	private Long hakmilikSPTB;
	@Column(name="CATATAN")	
	private String catatan;
	@Column(name="KEGUNAAN_TANAH") 
	private String kegunaanTanah ;
	@Column(name="ID_MASUK")
	private Long idMasuk;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MASUK")
	private Date tarikhMasuk;
	@Column(name="ID_KEMASKINI")
	private Long idKemaskini;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEMASKINI")
	private Date tarikhKemaskini;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}
	public Negeri getNegeri() {
		return negeri;
	}
	public void setNegeri(Negeri negeri) {
		this.negeri = negeri;
	}
	public Daerah getDaerah() {
		return daerah;
	}
	public void setDaerah(Daerah daerah) {
		this.daerah = daerah;
	}
	public Mukim getMukim() {
		return mukim;
	}
	public void setMukim(Mukim mukim) {
		this.mukim = mukim;
	}
	public JenisHakmilik getJenisHakmilik() {
		return jenisHakmilik;
	}
	public void setJenisHakmilik(JenisHakmilik jenisHakmilik) {
		this.jenisHakmilik = jenisHakmilik;
	}
	public Lot getLot() {
		return lot;
	}
	public void setLot(Lot lot) {
		this.lot = lot;
	}
	public Kementerian getKementerian() {
		return kementerian;
	}
	public void setKementerian(Kementerian kementerian) {
		this.kementerian = kementerian;
	}
	public Agensi getAgensi() {
		return agensi;
	}
	public void setAgensi(Agensi agensi) {
		this.agensi = agensi;
	}
	public Luas getLuasLama() {
		return luasLama;
	}
	public void setLuasLama(Luas luasLama) {
		this.luasLama = luasLama;
	}
	public Luas getLuasBaru() {
		return luasBaru;
	}
	public void setLuasBaru(Luas luasBaru) {
		this.luasBaru = luasBaru;
	}
	public Kategori getKategori() {
		return kategori;
	}
	public void setKategori(Kategori kategori) {
		this.kategori = kategori;
	}
	public SubKategori getSubKategori() {
		return subKategori;
	}
	public void setSubKategori(SubKategori subKategori) {
		this.subKategori = subKategori;
	}
	public JenisRizab getJenisRizab() {
		return jenisRizab;
	}
	public void setJenisRizab(JenisRizab jenisRizab) {
		this.jenisRizab = jenisRizab;
	}
	public String getPeganganHakmilik() {
		return peganganHakmilik;
	}
	public void setPeganganHakmilik(String peganganHakmilik) {
		this.peganganHakmilik = peganganHakmilik;
	}
	public Date getTarikhTerima() {
		return tarikhTerima;
	}
	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}
	public String getNoHakmilik() {
		return noHakmilik;
	}
	public void setNoHakmilik(String noHakmilik) {
		this.noHakmilik = noHakmilik;
	}
	public Date getTarikhDaftar() {
		return tarikhDaftar;
	}
	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}
	public String getNoBangunan() {
		return noBangunan;
	}
	public void setNoBangunan(String noBangunan) {
		this.noBangunan = noBangunan;
	}
	public String getNoTingkat() {
		return noTingkat;
	}
	public void setNoTingkat(String noTingkat) {
		this.noTingkat = noTingkat;
	}
	public String getNoPetak() {
		return noPetak;
	}
	public void setNoPetak(String noPetak) {
		this.noPetak = noPetak;
	}
	public double getCukai() {
		return cukai;
	}
	public void setCukai(double cukai) {
		this.cukai = cukai;
	}
	public double getCukaiTerkini() {
		return cukaiTerkini;
	}
	public void setCukaiTerkini(double cukaiTerkini) {
		this.cukaiTerkini = cukaiTerkini;
	}
	public String getStatusRizab() {
		return statusRizab;
	}
	public void setStatusRizab(String statusRizab) {
		this.statusRizab = statusRizab;
	}
	public int getTempoh() {
		return tempoh;
	}
	public void setTempoh(int tempoh) {
		this.tempoh = tempoh;
	}
	public String getKawasanRizab() {
		return kawasanRizab;
	}
	public void setKawasanRizab(String kawasanRizab) {
		this.kawasanRizab = kawasanRizab;
	}
	public String getNoRizab() {
		return noRizab;
	}
	public void setNoRizab(String noRizab) {
		this.noRizab = noRizab;
	}
	public Date getTarikhRizab() {
		return tarikhRizab;
	}
	public void setTarikhRizab(Date tarikhRizab) {
		this.tarikhRizab = tarikhRizab;
	}
	public String getNoWarta() {
		return noWarta;
	}
	public void setNoWarta(String noWarta) {
		this.noWarta = noWarta;
	}
	public Date getTarikhWarta() {
		return tarikhWarta;
	}
	public void setTarikhWarta(Date tarikhWarta) {
		this.tarikhWarta = tarikhWarta;
	}
	public Date getTarikhLuput() {
		return tarikhLuput;
	}
	public void setTarikhLuput(Date tarikhLuput) {
		this.tarikhLuput = tarikhLuput;
	}
	public String getNoLot() {
		return noLot;
	}
	public void setNoLot(String noLot) {
		this.noLot = noLot;
	}
	public String getLuas() {
		return luas;
	}
	public void setLuas(String luas) {
		this.luas = luas;
	}
	public double getLuasBersamaan() {
		return luasBersamaan;
	}
	public void setLuasBersamaan(double luasBersamaan) {
		this.luasBersamaan = luasBersamaan;
	}
	public String getSyarat() {
		return syarat;
	}
	public void setSyarat(String syarat) {
		this.syarat = syarat;
	}
	public String getSekatan() {
		return sekatan;
	}
	public void setSekatan(String sekatan) {
		this.sekatan = sekatan;
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
		return noPU;
	}
	public void setNoPU(String noPU) {
		this.noPU = noPU;
	}
	public String getStatusGeran() {
		return statusGeran;
	}
	public void setStatusGeran(String statusGeran) {
		this.statusGeran = statusGeran;
	}
	public String getStatusSwasta() {
		return statusSwasta;
	}
	public void setStatusSwasta(String statusSwasta) {
		this.statusSwasta = statusSwasta;
	}
	public Date getTarikhMohonSwasta() {
		return tarikhMohonSwasta;
	}
	public void setTarikhMohonSwasta(Date tarikhMohonSwasta) {
		this.tarikhMohonSwasta = tarikhMohonSwasta;
	}
	public Date getTarikhSwasta() {
		return tarikhSwasta;
	}
	public void setTarikhSwasta(Date tarikhSwasta) {
		this.tarikhSwasta = tarikhSwasta;
	}
	public String getStatusPajakan() {
		return statusPajakan;
	}
	public void setStatusPajakan(String statusPajakan) {
		this.statusPajakan = statusPajakan;
	}
	public Date getTarikhMohonPajakan() {
		return tarikhMohonPajakan;
	}
	public void setTarikhMohonPajakan(Date tarikhMohonPajakan) {
		this.tarikhMohonPajakan = tarikhMohonPajakan;
	}
	public String getStatusSewa() {
		return statusSewa;
	}
	public void setStatusSewa(String statusSewa) {
		this.statusSewa = statusSewa;
	}
	public Date getTarikhMohonSewa() {
		return tarikhMohonSewa;
	}
	public void setTarikhMohonSewa(Date tarikhMohonSewa) {
		this.tarikhMohonSewa = tarikhMohonSewa;
	}
	public String getStatusPelepasan() {
		return statusPelepasan;
	}
	public void setStatusPelepasan(String statusPelepasan) {
		this.statusPelepasan = statusPelepasan;
	}
	public Date getTarikhMohonPelepasan() {
		return tarikhMohonPelepasan;
	}
	public void setTarikhMohonPelepasan(Date tarikhMohonPelepasan) {
		this.tarikhMohonPelepasan = tarikhMohonPelepasan;
	}
	public String getLokasi() {
		return lokasi;
	}
	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}
	public String getStatusSah() {
		return statusSah;
	}
	public void setStatusSah(String statusSah) {
		this.statusSah = statusSah;
	}
	public String getNoFailHakmilik() {
		return noFailHakmilik;
	}
	public void setNoFailHakmilik(String noFailHakmilik) {
		this.noFailHakmilik = noFailHakmilik;
	}
	public String getTarafHakmilik() {
		return tarafHakmilik;
	}
	public void setTarafHakmilik(String tarafHakmilik) {
		this.tarafHakmilik = tarafHakmilik;
	}
	public String getHakmilikAsal() {
		return hakmilikAsal;
	}
	public void setHakmilikAsal(String hakmilikAsal) {
		this.hakmilikAsal = hakmilikAsal;
	}
	public String getHakmilikBerikut() {
		return hakmilikBerikut;
	}
	public void setHakmilikBerikut(String hakmilikBerikut) {
		this.hakmilikBerikut = hakmilikBerikut;
	}
	public String getHakmilikTukarganti() {
		return hakmilikTukarganti;
	}
	public void setHakmilikTukarganti(String hakmilikTukarganti) {
		this.hakmilikTukarganti = hakmilikTukarganti;
	}
	public String getNoFail() {
		return noFail;
	}
	public void setNoFail(String noFail) {
		this.noFail = noFail;
	}
	public String getNoFailPTG() {
		return noFailPTG;
	}
	public void setNoFailPTG(String noFailPTG) {
		this.noFailPTG = noFailPTG;
	}
	public String getNoFailPTD() {
		return noFailPTD;
	}
	public void setNoFailPTD(String noFailPTD) {
		this.noFailPTD = noFailPTD;
	}
	public String getNoFailKJP() {
		return noFailKJP;
	}
	public void setNoFailKJP(String noFailKJP) {
		this.noFailKJP = noFailKJP;
	}
	public String getNoFailAgensi() {
		return noFailAgensi;
	}
	public void setNoFailAgensi(String noFailAgensi) {
		this.noFailAgensi = noFailAgensi;
	}
	public String getNoFailJofa() {
		return noFailJofa;
	}
	public void setNoFailJofa(String noFailJofa) {
		this.noFailJofa = noFailJofa;
	}
	public Date getTarikhBukaFail() {
		return tarikhBukaFail;
	}
	public void setTarikhBukaFail(Date tarikhBukaFail) {
		this.tarikhBukaFail = tarikhBukaFail;
	}
	public String getNoPerserahan() {
		return noPerserahan;
	}
	public void setNoPerserahan(String noPerserahan) {
		this.noPerserahan = noPerserahan;
	}
	public String getCaraDapat() {
		return caraDapat;
	}
	public void setCaraDapat(String caraDapat) {
		this.caraDapat = caraDapat;
	}
	public Long getHakmilikSPTB() {
		return hakmilikSPTB;
	}
	public void setHakmilikSPTB(Long hakmilikSPTB) {
		this.hakmilikSPTB = hakmilikSPTB;
	}
	public String getCatatan() {
		return catatan;
	}
	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
	public String getKegunaanTanah() {
		return kegunaanTanah;
	}
	public void setKegunaanTanah(String kegunaanTanah) {
		this.kegunaanTanah = kegunaanTanah;
	}
	public Long getIdMasuk() {
		return idMasuk;
	}
	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}
	public Date getTarikhMasuk() {
		return tarikhMasuk;
	}
	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}
	public Long getIdKemaskini() {
		return idKemaskini;
	}
	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}
	public Date getTarikhKemaskini() {
		return tarikhKemaskini;
	}
	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}
	public List<HakmilikAgensi> getListHakmilikAgensi() {
		return listHakmilikAgensi;
	}
	public void setListHakmilikAgensi(List<HakmilikAgensi> listHakmilikAgensi) {
		this.listHakmilikAgensi = listHakmilikAgensi;
	}
	public List<HakmilikCukai> getListHakmilikCukai() {
		return listHakmilikCukai;
	}
	public void setListHakmilikCukai(List<HakmilikCukai> listHakmilikCukai) {
		this.listHakmilikCukai = listHakmilikCukai;
	}
	public List<HakmilikPerihal> getListHakmilikPerihal() {
		return listHakmilikPerihal;
	}
	public void setListHakmilikPerihal(List<HakmilikPerihal> listHakmilikPerihal) {
		this.listHakmilikPerihal = listHakmilikPerihal;
	}
	
//	public List<MaklumatPembangunan> getListMaklumatPembangunan() {
//		return listMaklumatPembangunan;
//	}
//	public void setListMaklumatPembangunan(
//			List<MaklumatPembangunan> listMaklumatPembangunan) {
//		this.listMaklumatPembangunan = listMaklumatPembangunan;
//	}



	
}
