package etapp.entity.ppt;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lebah.template.UID;
import etapp.entity.rujukan.Daerah;
import etapp.entity.rujukan.JenisHakmilik;
import etapp.entity.rujukan.Kategori;
import etapp.entity.rujukan.Luas;
import etapp.entity.rujukan.Mukim;
import etapp.entity.rujukan.Negeri;

@Entity
@Table(name = "TBLPPTHAKMILIK")
public class PPTHakmilik {
	
	@Id
	@Column(name="ID_HAKMILIK")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private PPTPermohonan permohonan;
	
	@ManyToOne @JoinColumn(name="ID_NEGERI")
	private Negeri negeri;
	@ManyToOne @JoinColumn(name="ID_DAERAH")
	private Daerah daerah;
	@ManyToOne @JoinColumn(name="ID_MUKIM")
	private Mukim mukim;
	@Column(name="SEKSYEN")
	private String seksyen;
	
	@ManyToOne @JoinColumn(name="ID_UNITLUASLOT")
	private Luas unitLuasLot;
	@ManyToOne @JoinColumn(name="ID_UNITLUASAMBIL")
	private Luas unitLuasAmbil;
	
	@Column(name="NAMA_LUAS_ASAL")
	private String namaLuasAsal;
	@Column(name="NAMA_LUAS_AMBIL")
	private String namaLuasAmbil;
	
	@ManyToOne @JoinColumn(name="ID_JENISHAKMILIK")
	private JenisHakmilik jenisHakmilik;
	@Column(name="NO_HAKMILIK")
	private String nomborHakmilik;
	@Column(name="TARIKH_DAFTAR")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tarikhDaftar;
	@Column(name="NO_SYIT")
	private String nomborSyit;
	@Column(name="NO_PT")
	private String nomborPT;
	@Column(name="NO_LOT")
	private String nomborLot;
	@Column(name="LUAS_LOT")
	private double luasLot;
	@Column(name="LUAS_AMBIL")
	private double luasAmbil;
	
	@ManyToOne @JoinColumn(name="ID_KATEGORITANAH")
	private Kategori kategori;
	 
	public PPTHakmilik() {
		setId(UID.get());
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public PPTPermohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(PPTPermohonan permohonan) {
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
	public String getSeksyen() {
		return seksyen;
	}
	public void setSeksyen(String seksyen) {
		this.seksyen = seksyen;
	}
	public JenisHakmilik getJenisHakmilik() {
		return jenisHakmilik;
	}
	public void setJenisHakmilik(JenisHakmilik jenisHakmilik) {
		this.jenisHakmilik = jenisHakmilik;
	}
	public String getNomborHakmilik() {
		return nomborHakmilik;
	}
	public void setNomborHakmilik(String nomborHakmilik) {
		this.nomborHakmilik = nomborHakmilik;
	}
	public Date getTarikhDaftar() {
		return tarikhDaftar;
	}
	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}
	public String getNomborSyit() {
		return nomborSyit;
	}
	public void setNomborSyit(String nomborSyit) {
		this.nomborSyit = nomborSyit;
	}
	public String getNomborPT() {
		return nomborPT;
	}
	public void setNomborPT(String nomborPT) {
		this.nomborPT = nomborPT;
	}

	public String getNomborLot() {
		return nomborLot;
	}

	public void setNomborLot(String nomborLot) {
		this.nomborLot = nomborLot;
	}

	public double getLuasLot() {
		return luasLot;
	}

	public void setLuasLot(double luasLot) {
		this.luasLot = luasLot;
	}

	public Kategori getKategori() {
		return kategori;
	}

	public void setKategori(Kategori kategori) {
		this.kategori = kategori;
	}

	public Luas getUnitLuasLot() {
		return unitLuasLot;
	}

	public void setUnitLuasLot(Luas unitLuasLot) {
		this.unitLuasLot = unitLuasLot;
	}

	public String getNamaLuasAsal() {
		return namaLuasAsal;
	}

	public void setNamaLuasAsal(String namaLuasAsal) {
		this.namaLuasAsal = namaLuasAsal;
	}

	public Luas getUnitLuasAmbil() {
		return unitLuasAmbil;
	}

	public void setUnitLuasAmbil(Luas unitLuasAmbil) {
		this.unitLuasAmbil = unitLuasAmbil;
	}

	public String getNamaLuasAmbil() {
		return namaLuasAmbil;
	}

	public void setNamaLuasAmbil(String namaLuasAmbil) {
		this.namaLuasAmbil = namaLuasAmbil;
	}

	public double getLuasAmbil() {
		return luasAmbil;
	}

	public void setLuasAmbil(double luasAmbil) {
		this.luasAmbil = luasAmbil;
	}
	
	
	

}
