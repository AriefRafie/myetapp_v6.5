package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBLHTPHAKMILIK")
public class HTPHakmilikMobile {
	
	@Id @Column(name="ID_HAKMILIK")
	private long id;
	@Column(name="NO_HAKMILIK")
	private String noHakmilik;
	@Column(name="NO_WARTA")
	private String noWarta;
	@Column(name="NO_LOT")
	private String noLot;
	
	@ManyToOne
	@JoinColumn(name="ID_LUAS_BERSAMAAN")
	private RujLuasMobile luas;
	
	@Column(name="LUAS_BERSAMAAN")
	private double luasBersamaan;

	@ManyToOne
	@JoinColumn(name="ID_NEGERI")
	private RujNegeriMobile negeri;
	@ManyToOne
	@JoinColumn(name="ID_DAERAH")
	private RujDaerahMobile daerah;
	@ManyToOne
	@JoinColumn(name="ID_MUKIM")
	private RujMukimMobile mukim;
	@ManyToOne
	@JoinColumn(name="ID_KATEGORI")
	private RujKategoriMobile kategori;
	@ManyToOne
	@JoinColumn(name="ID_KEMENTERIAN")
	private RujKementerianMobile kementerian;
	@ManyToOne
	@JoinColumn(name="ID_JENISHAKMILIK")
	private RujJenisHakmilikMobile jenisHakmilik;
	@ManyToOne
	@JoinColumn(name="ID_LOT")
	private RujLotMobile lot;
	
	@Column(name="LOKASI")
	private String lokasi;
	@Column(name="KEGUNAAN_TANAH")
	private String kegunaanTanah;
		

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNoHakmilik() {
		return noHakmilik;
	}
	public void setNoHakmilik(String noHakmilik) {
		this.noHakmilik = noHakmilik;
	}
	public String getNoWarta() {
		return noWarta;
	}
	public void setNoWarta(String noWarta) {
		this.noWarta = noWarta;
	}
	public String getNoLot() {
		return noLot;
	}
	public void setNoLot(String noLot) {
		this.noLot = noLot;
	}
	public RujLuasMobile getLuas() {
		return luas;
	}
	public void setLuas(RujLuasMobile luas) {
		this.luas = luas;
	}
	public double getLuasBersamaan() {
		return luasBersamaan;
	}
	public void setLuasBersamaan(double luasBersamaan) {
		this.luasBersamaan = luasBersamaan;
	}
	public RujNegeriMobile getNegeri() {
		return negeri;
	}
	public void setNegeri(RujNegeriMobile negeri) {
		this.negeri = negeri;
	}
	public RujDaerahMobile getDaerah() {
		return daerah;
	}
	public void setDaerah(RujDaerahMobile daerah) {
		this.daerah = daerah;
	}
	public RujMukimMobile getMukim() {
		return mukim;
	}
	public void setMukim(RujMukimMobile mukim) {
		this.mukim = mukim;
	}
	public RujKategoriMobile getKategori() {
		return kategori;
	}
	public void setKategori(RujKategoriMobile kategori) {
		this.kategori = kategori;
	}
	public String getLokasi() {
		return lokasi;
	}
	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}
	public String getKegunaanTanah() {
		return kegunaanTanah;
	}
	public void setKegunaanTanah(String kegunaanTanah) {
		this.kegunaanTanah = kegunaanTanah;
	}
	public RujKementerianMobile getKementerian() {
		return kementerian;
	}
	public void setKementerian(RujKementerianMobile kementerian) {
		this.kementerian = kementerian;
	}
	public RujJenisHakmilikMobile getJenisHakmilik() {
		return jenisHakmilik;
	}
	public void setJenisHakmilik(RujJenisHakmilikMobile jenisHakmilik) {
		this.jenisHakmilik = jenisHakmilik;
	}
	public RujLotMobile getLot() {
		return lot;
	}
	public void setLot(RujLotMobile lot) {
		this.lot = lot;
	}
}