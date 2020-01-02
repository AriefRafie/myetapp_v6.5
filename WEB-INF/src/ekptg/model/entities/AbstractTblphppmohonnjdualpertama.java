package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblphppmohonnjdualpertama entity provides the base persistence
 * definition of the Tblphppmohonnjdualpertama entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphppmohonnjdualpertama implements
		java.io.Serializable {

	// Fields

	private Long idPmohonnjdualpertama;
	private String idFail;
	private Long idJenistujuan;
	private String namaProjek;
	private String flagJenisPerjanjian;
	private String antara;
	private String danSiapa;
	private String daripada;
	private Double modalSemasa;
	private Double modalSedia;
	private String pengalaman;
	private String lokasiPermohonan;
	private Long tempohDipohon;
	private Long idTempoh;
	private Long bilTitik;
	private Long idUnitisipadu;
	private Double isipadu;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idUrusan;
	private Long idStatus;
	private Set tblphpkertaskerjaapbs = new HashSet(0);
	private Set tblphpversikoordinats = new HashSet(0);
	private Set tblphpprojeklesenapbs = new HashSet(0);
	private Set tblphppemohonlesenapbs = new HashSet(0);
	private Set tblphpjadualkedualesenapbs = new HashSet(0);
	private Set tblphpfailapbbertindihs = new HashSet(0);
	private Set tblphpbyrnsyrtkllsnlesenapbs = new HashSet(0);
	private Set tblphpulasanteknikalapbs = new HashSet(0);
	private Set tblphppembelipasirs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblphppmohonnjdualpertama() {
	}

	/** minimal constructor */
	public AbstractTblphppmohonnjdualpertama(Long idPmohonnjdualpertama) {
		this.idPmohonnjdualpertama = idPmohonnjdualpertama;
	}

	/** full constructor */
	public AbstractTblphppmohonnjdualpertama(Long idPmohonnjdualpertama,
			String idFail, Long idJenistujuan, String namaProjek,
			String flagJenisPerjanjian, String antara, String danSiapa,
			String daripada, Double modalSemasa, Double modalSedia,
			String pengalaman, String lokasiPermohonan, Long tempohDipohon,
			Long idTempoh, Long bilTitik, Long idUnitisipadu, Double isipadu,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idUrusan, Long idStatus,
			Set tblphpkertaskerjaapbs, Set tblphpversikoordinats,
			Set tblphpprojeklesenapbs, Set tblphppemohonlesenapbs,
			Set tblphpjadualkedualesenapbs, Set tblphpfailapbbertindihs,
			Set tblphpbyrnsyrtkllsnlesenapbs, Set tblphpulasanteknikalapbs,
			Set tblphppembelipasirs) {
		this.idPmohonnjdualpertama = idPmohonnjdualpertama;
		this.idFail = idFail;
		this.idJenistujuan = idJenistujuan;
		this.namaProjek = namaProjek;
		this.flagJenisPerjanjian = flagJenisPerjanjian;
		this.antara = antara;
		this.danSiapa = danSiapa;
		this.daripada = daripada;
		this.modalSemasa = modalSemasa;
		this.modalSedia = modalSedia;
		this.pengalaman = pengalaman;
		this.lokasiPermohonan = lokasiPermohonan;
		this.tempohDipohon = tempohDipohon;
		this.idTempoh = idTempoh;
		this.bilTitik = bilTitik;
		this.idUnitisipadu = idUnitisipadu;
		this.isipadu = isipadu;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idUrusan = idUrusan;
		this.idStatus = idStatus;
		this.tblphpkertaskerjaapbs = tblphpkertaskerjaapbs;
		this.tblphpversikoordinats = tblphpversikoordinats;
		this.tblphpprojeklesenapbs = tblphpprojeklesenapbs;
		this.tblphppemohonlesenapbs = tblphppemohonlesenapbs;
		this.tblphpjadualkedualesenapbs = tblphpjadualkedualesenapbs;
		this.tblphpfailapbbertindihs = tblphpfailapbbertindihs;
		this.tblphpbyrnsyrtkllsnlesenapbs = tblphpbyrnsyrtkllsnlesenapbs;
		this.tblphpulasanteknikalapbs = tblphpulasanteknikalapbs;
		this.tblphppembelipasirs = tblphppembelipasirs;
	}

	// Property accessors

	public Long getIdPmohonnjdualpertama() {
		return this.idPmohonnjdualpertama;
	}

	public void setIdPmohonnjdualpertama(Long idPmohonnjdualpertama) {
		this.idPmohonnjdualpertama = idPmohonnjdualpertama;
	}

	public String getIdFail() {
		return this.idFail;
	}

	public void setIdFail(String idFail) {
		this.idFail = idFail;
	}

	public Long getIdJenistujuan() {
		return this.idJenistujuan;
	}

	public void setIdJenistujuan(Long idJenistujuan) {
		this.idJenistujuan = idJenistujuan;
	}

	public String getNamaProjek() {
		return this.namaProjek;
	}

	public void setNamaProjek(String namaProjek) {
		this.namaProjek = namaProjek;
	}

	public String getFlagJenisPerjanjian() {
		return this.flagJenisPerjanjian;
	}

	public void setFlagJenisPerjanjian(String flagJenisPerjanjian) {
		this.flagJenisPerjanjian = flagJenisPerjanjian;
	}

	public String getAntara() {
		return this.antara;
	}

	public void setAntara(String antara) {
		this.antara = antara;
	}

	public String getDanSiapa() {
		return this.danSiapa;
	}

	public void setDanSiapa(String danSiapa) {
		this.danSiapa = danSiapa;
	}

	public String getDaripada() {
		return this.daripada;
	}

	public void setDaripada(String daripada) {
		this.daripada = daripada;
	}

	public Double getModalSemasa() {
		return this.modalSemasa;
	}

	public void setModalSemasa(Double modalSemasa) {
		this.modalSemasa = modalSemasa;
	}

	public Double getModalSedia() {
		return this.modalSedia;
	}

	public void setModalSedia(Double modalSedia) {
		this.modalSedia = modalSedia;
	}

	public String getPengalaman() {
		return this.pengalaman;
	}

	public void setPengalaman(String pengalaman) {
		this.pengalaman = pengalaman;
	}

	public String getLokasiPermohonan() {
		return this.lokasiPermohonan;
	}

	public void setLokasiPermohonan(String lokasiPermohonan) {
		this.lokasiPermohonan = lokasiPermohonan;
	}

	public Long getTempohDipohon() {
		return this.tempohDipohon;
	}

	public void setTempohDipohon(Long tempohDipohon) {
		this.tempohDipohon = tempohDipohon;
	}

	public Long getIdTempoh() {
		return this.idTempoh;
	}

	public void setIdTempoh(Long idTempoh) {
		this.idTempoh = idTempoh;
	}

	public Long getBilTitik() {
		return this.bilTitik;
	}

	public void setBilTitik(Long bilTitik) {
		this.bilTitik = bilTitik;
	}

	public Long getIdUnitisipadu() {
		return this.idUnitisipadu;
	}

	public void setIdUnitisipadu(Long idUnitisipadu) {
		this.idUnitisipadu = idUnitisipadu;
	}

	public Double getIsipadu() {
		return this.isipadu;
	}

	public void setIsipadu(Double isipadu) {
		this.isipadu = isipadu;
	}

	public Long getIdMasuk() {
		return this.idMasuk;
	}

	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}

	public Date getTarikhMasuk() {
		return this.tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
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

	public Long getIdUrusan() {
		return this.idUrusan;
	}

	public void setIdUrusan(Long idUrusan) {
		this.idUrusan = idUrusan;
	}

	public Long getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public Set getTblphpkertaskerjaapbs() {
		return this.tblphpkertaskerjaapbs;
	}

	public void setTblphpkertaskerjaapbs(Set tblphpkertaskerjaapbs) {
		this.tblphpkertaskerjaapbs = tblphpkertaskerjaapbs;
	}

	public Set getTblphpversikoordinats() {
		return this.tblphpversikoordinats;
	}

	public void setTblphpversikoordinats(Set tblphpversikoordinats) {
		this.tblphpversikoordinats = tblphpversikoordinats;
	}

	public Set getTblphpprojeklesenapbs() {
		return this.tblphpprojeklesenapbs;
	}

	public void setTblphpprojeklesenapbs(Set tblphpprojeklesenapbs) {
		this.tblphpprojeklesenapbs = tblphpprojeklesenapbs;
	}

	public Set getTblphppemohonlesenapbs() {
		return this.tblphppemohonlesenapbs;
	}

	public void setTblphppemohonlesenapbs(Set tblphppemohonlesenapbs) {
		this.tblphppemohonlesenapbs = tblphppemohonlesenapbs;
	}

	public Set getTblphpjadualkedualesenapbs() {
		return this.tblphpjadualkedualesenapbs;
	}

	public void setTblphpjadualkedualesenapbs(Set tblphpjadualkedualesenapbs) {
		this.tblphpjadualkedualesenapbs = tblphpjadualkedualesenapbs;
	}

	public Set getTblphpfailapbbertindihs() {
		return this.tblphpfailapbbertindihs;
	}

	public void setTblphpfailapbbertindihs(Set tblphpfailapbbertindihs) {
		this.tblphpfailapbbertindihs = tblphpfailapbbertindihs;
	}

	public Set getTblphpbyrnsyrtkllsnlesenapbs() {
		return this.tblphpbyrnsyrtkllsnlesenapbs;
	}

	public void setTblphpbyrnsyrtkllsnlesenapbs(Set tblphpbyrnsyrtkllsnlesenapbs) {
		this.tblphpbyrnsyrtkllsnlesenapbs = tblphpbyrnsyrtkllsnlesenapbs;
	}

	public Set getTblphpulasanteknikalapbs() {
		return this.tblphpulasanteknikalapbs;
	}

	public void setTblphpulasanteknikalapbs(Set tblphpulasanteknikalapbs) {
		this.tblphpulasanteknikalapbs = tblphpulasanteknikalapbs;
	}

	public Set getTblphppembelipasirs() {
		return this.tblphppembelipasirs;
	}

	public void setTblphppembelipasirs(Set tblphppembelipasirs) {
		this.tblphppembelipasirs = tblphppembelipasirs;
	}

}