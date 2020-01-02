package etapp.entity.htp;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import etapp.entity.Permohonan;
import etapp.entity.rujukan.Daerah;
import etapp.entity.rujukan.JenisHakmilik;
import etapp.entity.rujukan.JenisRizab;
import etapp.entity.rujukan.Kategori;
import etapp.entity.rujukan.Lot;
import etapp.entity.rujukan.Mukim;
import etapp.entity.rujukan.Negeri;
import etapp.entity.rujukan.SubKategori;

@Entity
@Table(name = "TBLHTPHAKMILIKURUSAN")
public class HakmilikUrusan {
	@Id @Column(name="ID_HAKMILIKURUSAN")
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
	@ManyToOne @JoinColumn(name="ID_JENISRIZAB")
	private JenisRizab jenisRizab;	
	@ManyToOne @JoinColumn(name="ID_KATEGORI")
	private Kategori kategori;		
	@ManyToOne @JoinColumn(name="ID_SUBKATEGORI")
	private SubKategori subKategori;		
		
	//Permohonan
//	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="hakmilikUrusan")
//	private List<Charting> listCharting;
//	
//	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="hakmilikUrusan")
//	private List<PihakBerkepentingan> listPihakBerkepentingan;
//	//@OneToMany(cascade=CascadeType.PERSIST, mappedBy="hakmilikUrusan")
//	//private List<HakmilikUrusanPB> listHakmilikUrusanPB;
//	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="hakmilikUrusan")
//	private List<MaklumatTanah> listMaklumatTanah;

	@Column(name="NO_LOT")
	private String nolot;
	@Column(name="NO_HAKMILIK")
	private String nohakmilik;
	@Column(name="NO_BANGUNAN")
	private String noBangunan;
	@Column(name="NO_TINGKAT")
	private String noTingkat;
	@Column(name="NO_PETAK")
	private String noPetak;
	@Column(name="LUAS")
	private String luas;
	@Column(name="ID_LUAS")
	private String idLuas;
	@Column(name="ID_LUAS_BERSAMAAN")
	private String idLuasBersamaan;
	@Column(name="LUAS_BERSAMAAN")
	private String luasBersamaan;
	@Column(name="PEGANGAN_HAKMILIK")	
	private String peganganHakmilik;
	//@Column(name="TARIKH_TERIMA") 
	//private String tarikhTerima;
	//@Column(name="TARIKH_MULA") 
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MULA")
	private Date tarikhMula;
	//@Column(name="TARIKH_LUPUT") 
	//private Date tarikhLuput;
	@Transient 
	private Date tarikhLuput;
	@Column(name="LOKASI") 
	private String lokasi;
	@Column(name="NO_FAIL_JOFA") 
	private String noFailJofa;
	@Column(name="SYARAT") 
	private String syarat;
	@Column(name="SEKATAN") 
	private String sekatan;
	@Column(name="NO_PELAN") 
	private String noPelan;
	@Column(name="NO_SYIT") 
	private String noSyit;
	@Column(name="NO_PU") 
	private String noPu;
	@Column(name="CUKAI") 
	private double cukai = 0.00;
	@Column(name="CUKAI_TERKINI") 
	private double cukaiTerkini = 0.00;
	@Column(name="NO_RIZAB") 
	private String noRizab;
	//private Date tarikhTerima;
	//private Date tarikhWarta;
	@Column(name="TARIKH_RIZAB") 
	private String tarikhRizab;

	//@Column(name="ID_JENISRIZAB") 
	//private String idRizab;

	//private String luasKeterangan;
	//private String jenisKeterangan;
	@Column(name="STATUS_TANAH") 
	private String statusTanah ;
	@Column(name="ID_MASUK")
	private Long idMasuk;
	//@Column(name="TARIKH_MASUK") 
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MASUK")
	private java.util.Date tarikhMasuk;
	@Column(name="ID_KEMASKINI")
	private Long idKemaskini;
	//@Column(name="TARIKH_KEMASKINI") 
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEMASKINI")
	private Date tarikhKemaskini;

	@Transient
	private String tempohTanah;
	@Transient
	private String idKategoriTanah;
	@Transient
	private String idSubKategoriTanah;
	@Transient
	private String idJenisRizab;
	//private String tempohBakiTanah ;

//	public String getJenisKeterangan() {
//		return jenisKeterangan;
//	}
//
//
//	public void setJenisKeterangan(String jenisKeterangan) {
//		this.jenisKeterangan = jenisKeterangan;
//	}
//
//
//	public String getLuasKeterangan() {
//		return luasKeterangan;
//	}
//
//
//	public void setLuasKeterangan(String luasKeterangan) {
//		this.luasKeterangan = luasKeterangan;
//	}

	public String getLuas() {
		return luas;
	}


	public void setLuas(String luas) {
		this.luas = luas;
	}


	public String getLuasBersamaan() {
		return luasBersamaan;
	}

	public void setLuasBersamaan(String luasBersamaan) {
		this.luasBersamaan = luasBersamaan;
	}

	public String getNolot() {
		return nolot;
	}

	public void setNolot(String nolot) {
		this.nolot = nolot;
	}

	public String getNohakmilik() {
		return nohakmilik;
	}

	public void setNohakmilik(String nohakmilik) {
		this.nohakmilik = nohakmilik;
	}


//	public String getNamanegeri() {
//		return namanegeri;
//	}
//
//
//	public void setNamanegeri(String namanegeri) {
//		this.namanegeri = namanegeri;
//	}
//
//
//	public String getNamadaerah() {
//		return namadaerah;
//	}
//
//
//	public void setNamadaerah(String namadaerah) {
//		this.namadaerah = namadaerah;
//	}
//
//
//	public String getNamamukim() {
//		return namamukim;
//	}
//
//
//	public void setNamamukim(String namamukim) {
//		this.namamukim = namamukim;
//	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id= id;
	}


//	public String getKeterangan() {
//		return keterangan;
//	}
//
//
//	public void setKeterangan(String keterangan) {
//		this.keterangan = keterangan;
//	}
//
//
//	public String getKodjenishakmilik() {
//		return kodjenishakmilik;
//	}
//
//
//	public void setKodjenishakmilik(String kodjenishakmilik) {
//		this.kodjenishakmilik = kodjenishakmilik;
//	}
	
//	public Long getIdNegeri() {
//		return idNegeri;
//	}
//
//
//	public void setIdNegeri(Long idNegeri) {
//		this.idNegeri = idNegeri;
//	}


//	public Long getIdDaerah() {
//		return idDaerah;
//	}
//
//	public void setIdDaerah(Long idDaerah) {
//		this.idDaerah = idDaerah;
//	}
//
//	public Long getIdMukim() {
//		return idMukim;
//	}
//
//	public void setIdMukim(Long idMukim) {
//		this.idMukim = idMukim;
//	}
//
//	public Long getIdJenisHakmilik() {
//		return idJenisHakmilik;
//	}
//
//	public void setIdJenisHakmilik(Long idJenisHakmilik) {
//		this.idJenisHakmilik = idJenisHakmilik;
//	}

//	public String getIdHakmilik() {
//		return idHakmilik;
//	}
//
//
//	public void setIdHakmilik(String idHakmilik) {
//		this.idHakmilik = idHakmilik;
//	}


//	public Long getIdLot() {
//		return idLot;
//	}
//
//	public void setIdLot(Long idLot) {
//		this.idLot = idLot;
//	}
	

//	public Vector<PihakBerkepentingan> getPihaks() {
//		return pihaks;
//	}
//
//
//	public void setPihaks(Vector<PihakBerkepentingan> pihaks) {
//		this.pihaks = pihaks;
//	}
//	public void addPihakBerkepentingan(PihakBerkepentingan pb){
//		if(pihaks == null){
//			pihaks = new Vector<PihakBerkepentingan>();
//		}
//		this.pihaks.addElement(pb);
//	
//	}


	public String getNoBangunan() {
		return noBangunan;
	}


	public void setNoBangunan(String noBangunan) {
		this.noBangunan = noBangunan;
	}


	public String getNoPetak() {
		return noPetak;
	}


	public void setNoPetak(String noPetak) {
		this.noPetak = noPetak;
	}


	public String getNoTingkat() {
		return noTingkat;
	}


	public void setNoTingkat(String noTingkat) {
		this.noTingkat = noTingkat;
	}


	public Permohonan getPermohonan() {
		return permohonan;
	}


	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

//	public String getIdJenisRizab() {
//		return idJenisRizab;
//	}
//
//	public void setIdJenisRizab(String idJenisRizab) {
//		this.idJenisRizab = idJenisRizab;
//	}

	public String getIdLuas() {
		return idLuas;
	}

	public void setIdLuas(String idLuas) {
		this.idLuas = idLuas;
	}

	public String getIdLuasBersamaan() {
		return idLuasBersamaan;
	}


	public void setIdLuasBersamaan(String idLuasBersamaan) {
		this.idLuasBersamaan = idLuasBersamaan;
	}

	public String getPeganganHakmilik() {
		return peganganHakmilik;
	}


	public void setPeganganHakmilik(String peganganHakmilik) {
		this.peganganHakmilik = peganganHakmilik;
	}

//	public String getTarikhTerima() {
//		return tarikhTerima;
//	}
//
//	public void setTarikhTerima(String tarikhTerima) {
//		this.tarikhTerima = tarikhTerima;
//	}
	
	public Date getTarikhMula() {
		return tarikhMula;
	}

	public void setTarikhMula(Date tarikhMula) {
		this.tarikhMula = tarikhMula;
	}
	public void setTarikhMulaString(String tarikhMula) {
		this.tarikhMula = new Date(tarikhMula);
	}

	public Date getTarikhLuput() {
		return tarikhLuput;
	}
	
	public void setTarikhLuput(Date tarikhLuput) {
		this.tarikhLuput = tarikhLuput;
	}
	
	public void setTarikhLuputString(String tarikhLuput) {
		this.tarikhLuput = new Date(tarikhLuput);
	}


//	public String getKeteranganLot() {
//		return keteranganLot;
//	}
//
//
//	public void setKeteranganLot(String keteranganLot) {
//		this.keteranganLot = keteranganLot;
//	}
	

	public String getStatusTanah() {
		return statusTanah;
	}


	public void setStatusTanah(String statusTanah) {
		this.statusTanah = statusTanah;
	}	
	
	public String getTempohTanah() {
		return tempohTanah;
	}


	public void setTempohTanah(String tempohTanah) {
		this.tempohTanah = tempohTanah;
	}
//	
//
//	public String getTempohBakiTanah() {
//		return tempohBakiTanah;
//	}
//
//
//	public void setTempohBakiTanah(String tempohBakiTanah) {
//		this.tempohBakiTanah = tempohBakiTanah;
//	}
	public Long getIdMasuk() {
		return this.idMasuk;
	}

	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}

	public java.util.Date getTarikhMasuk() {
		return this.tarikhMasuk;
	}

	public void setTarikhMasuk(java.util.Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public Long getIdKemaskini() {
		return this.idKemaskini;
	}

	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public Date getTarikhKemaskini() {
		return this.tarikhKemaskini;
	}

	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
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
	
	public JenisHakmilik getJenisHakmilik() {
		return this.jenisHakmilik;
	}

	public void setJenisHakmilik(JenisHakmilik jenisHakmilik) {
		this.jenisHakmilik = jenisHakmilik;
	}
	
	public Lot getLot() {
		if (this.lot == null)lot = null;
		return lot;
	}
	public void setLot(Lot lot) {
		this.lot = lot;
	}
	
	public String getLokasi() {
		if (this.lokasi == null)lokasi = null;
		return lokasi;
	}
	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}
	
	public String getNoFailJofa() {
		if (this.noFailJofa == null)noFailJofa = null;
		return noFailJofa;
	}
	public void setNoFailJofa(String noFailJofa) {
		this.noFailJofa = noFailJofa;
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
	
	public double getCukaiTerkini() {
		return cukaiTerkini;
	}
	public void setCukaiTerkini(double cukaiTerkini) {
		this.cukaiTerkini = cukaiTerkini;
	}
	public double getCukai() {
		return cukai;
	}
	public void setCukai(double cukai) {
		this.cukai = cukai;
	}

	public String getNoRizab() {
		if (this.noRizab == null)noRizab = "";
		return noRizab;
	}	
	public void setNoRizab(String noRizab) {
		this.noRizab = noRizab;
	}
	
//	public String getRizab() {
//		return idRizab;
//	}
//	public void setRizab(String idRizab) {
//		this.idRizab = idRizab;
//	}
	
	public String getTarikhRizab() {
		return tarikhRizab;
	}
	public void setTarikhRizab(String tarikhRizab) {
		this.tarikhRizab = tarikhRizab;
	}

//	public List<Charting> getListCharting() {
//		return listCharting;
//	}
//
//	public void setListCharting(List<Charting> listCharting) {
//		this.listCharting = listCharting;
//	}
//
//	public List<PihakBerkepentingan> getListPihakBerkepentingan() {
//		return listPihakBerkepentingan;
//	}
//
//	public void setListPihakBerkepentingan(
//			List<PihakBerkepentingan> listPihakBerkepentingan) {
//		this.listPihakBerkepentingan = listPihakBerkepentingan;
//	}


//	public List<HakmilikUrusanPB> getListHakmilikUrusanPB() {
//		return listHakmilikUrusanPB;
//	}
//	public void setListHakmilikUrusanPB(List<HakmilikUrusanPB> listHakmilikUrusanPB) {
//		this.listHakmilikUrusanPB = listHakmilikUrusanPB;
//	}


	public String getNoPu() {
		return noPu;
	}


	public void setNoPu(String noPu) {
		this.noPu = noPu;
	}


	public JenisRizab getJenisRizab() {
		return jenisRizab;
	}


	public void setJenisRizab(JenisRizab jenisRizab) {
		this.jenisRizab = jenisRizab;
	}

//	public List<MaklumatTanah> getListMaklumatTanah() {
//		return listMaklumatTanah;
//	}
//
//	public void setListMaklumatTanah(List<MaklumatTanah> listMaklumatTanah) {
//		this.listMaklumatTanah = listMaklumatTanah;
//	}
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

	public String getIdKategoriTanah() {
		return idKategoriTanah;
	}
	public void setIdKategoriTanah(String idKategoriTanah) {
		this.idKategoriTanah = idKategoriTanah;
	}

	public String getIdSubKategoriTanah() {
		return idSubKategoriTanah;
	}

	public void setIdSubKategoriTanah(String idSubKategoriTanah) {
		this.idSubKategoriTanah = idSubKategoriTanah;
	}
	
	public String getIdJenisRizab() {
		return idJenisRizab;
	}

	public void setIdJenisRizab(String idJenisRizab) {
		this.idJenisRizab = idJenisRizab;
	}

	
}
