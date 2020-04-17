package etapp.entity.htp.rekod;

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

import etapp.entity.rujukan.Agensi;
import etapp.entity.rujukan.Kementerian;

@Entity
@Table(name = "TBLHTPHAKMILIKAGENSI")
public class HakmilikAgensi{
	@Id @Column(name="ID_HAKMILIKAGENSI")
	private long id;
	@ManyToOne @JoinColumn(name="ID_HAKMILIK")
	private Hakmilik hakmilik;
	@ManyToOne @JoinColumn(name="ID_KEMENTERIAN")
	private Kementerian kementerian;
	@ManyToOne @JoinColumn(name="ID_AGENSI")
	private Agensi agensi;

	@Column(name="LUAS")
	private String luas;
	@Column(name="ID_LUAS")
	private String idLuas;
	@Column(name="ID_LUAS_BERSAMAAN")
	private String idLuasBersamaan;
	@Column(name="LUAS_BERSAMAAN")
	private String luasBersamaan;
	
	@Column(name="STATUS")	
	private String status;
	@Column(name="FLAG_AKTIF")	
	private String flagAktif;
	@Column(name="ID_MASUK")
	private Long idMasuk;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MASUK")
	private Date tarikhMasuk;
	@Column(name="ID_KEMASKINI")
	private Long idKemaskini;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEMASKINI")
	private Date tarikhKemaskini;

	@Transient
	private String tempohTanah;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Hakmilik getHakmilik() {
		return hakmilik;
	}

	public void setHakmilik(Hakmilik hakmilik) {
		this.hakmilik = hakmilik;
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

	public String getLuas() {
		return luas;
	}

	public void setLuas(String luas) {
		this.luas = luas;
	}

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

	public String getLuasBersamaan() {
		return luasBersamaan;
	}

	public void setLuasBersamaan(String luasBersamaan) {
		this.luasBersamaan = luasBersamaan;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFlagAktif() {
		return flagAktif;
	}

	public void setFlagAktif(String flagAktif) {
		this.flagAktif = flagAktif;
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

	public String getTempohTanah() {
		return tempohTanah;
	}

	public void setTempohTanah(String tempohTanah) {
		this.tempohTanah = tempohTanah;
	}

	
}
