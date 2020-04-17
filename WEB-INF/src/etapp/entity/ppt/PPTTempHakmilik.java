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
import etapp.entity.rujukan.JenisHakmilik;
import etapp.entity.rujukan.Kategori;

@Entity
@Table(name = "TBLPPTTEMPHAKMILIK")
public class PPTTempHakmilik {
	
	@Id
	@Column(name="ID_HAKMILIK")
	private long id;
	@ManyToOne @JoinColumn(name="ID_HAKMILIK_ASAL")
	private PPTHakmilik hakmilikAsal;
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
	
	@ManyToOne @JoinColumn(name="ID_KATEGORITANAH")
	private Kategori kategori;
	
	public PPTTempHakmilik() {
		setId(UID.get());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PPTHakmilik getHakmilikAsal() {
		return hakmilikAsal;
	}

	public void setHakmilikAsal(PPTHakmilik hakmilikAsal) {
		this.hakmilikAsal = hakmilikAsal;
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

	public Kategori getKategori() {
		return kategori;
	}

	public void setKategori(Kategori kategori) {
		this.kategori = kategori;
	}

	
	

}
