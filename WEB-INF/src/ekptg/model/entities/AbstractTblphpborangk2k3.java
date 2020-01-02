package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphpborangk2k3 entity provides the base persistence definition of
 * the Tblphpborangk2k3 entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpborangk2k3 implements java.io.Serializable {

	// Fields

	private Long idBorangk2k3;
	private Tblphplaporanpasir tblphplaporanpasir;
	private Long idBarge;
	private Date tarikhHantar;
	private String lokasiDibekalkan;
	private String akuanKastam;
	private String tujuan;
	private Double kuantiti;
	private Long idUnitisipadu;
	private Double anggaranRoyalti;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphpborangk2k3() {
	}

	/** minimal constructor */
	public AbstractTblphpborangk2k3(Long idBorangk2k3) {
		this.idBorangk2k3 = idBorangk2k3;
	}

	/** full constructor */
	public AbstractTblphpborangk2k3(Long idBorangk2k3,
			Tblphplaporanpasir tblphplaporanpasir, Long idBarge,
			Date tarikhHantar, String lokasiDibekalkan, String akuanKastam,
			String tujuan, Double kuantiti, Long idUnitisipadu,
			Double anggaranRoyalti, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idBorangk2k3 = idBorangk2k3;
		this.tblphplaporanpasir = tblphplaporanpasir;
		this.idBarge = idBarge;
		this.tarikhHantar = tarikhHantar;
		this.lokasiDibekalkan = lokasiDibekalkan;
		this.akuanKastam = akuanKastam;
		this.tujuan = tujuan;
		this.kuantiti = kuantiti;
		this.idUnitisipadu = idUnitisipadu;
		this.anggaranRoyalti = anggaranRoyalti;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdBorangk2k3() {
		return this.idBorangk2k3;
	}

	public void setIdBorangk2k3(Long idBorangk2k3) {
		this.idBorangk2k3 = idBorangk2k3;
	}

	public Tblphplaporanpasir getTblphplaporanpasir() {
		return this.tblphplaporanpasir;
	}

	public void setTblphplaporanpasir(Tblphplaporanpasir tblphplaporanpasir) {
		this.tblphplaporanpasir = tblphplaporanpasir;
	}

	public Long getIdBarge() {
		return this.idBarge;
	}

	public void setIdBarge(Long idBarge) {
		this.idBarge = idBarge;
	}

	public Date getTarikhHantar() {
		return this.tarikhHantar;
	}

	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}

	public String getLokasiDibekalkan() {
		return this.lokasiDibekalkan;
	}

	public void setLokasiDibekalkan(String lokasiDibekalkan) {
		this.lokasiDibekalkan = lokasiDibekalkan;
	}

	public String getAkuanKastam() {
		return this.akuanKastam;
	}

	public void setAkuanKastam(String akuanKastam) {
		this.akuanKastam = akuanKastam;
	}

	public String getTujuan() {
		return this.tujuan;
	}

	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}

	public Double getKuantiti() {
		return this.kuantiti;
	}

	public void setKuantiti(Double kuantiti) {
		this.kuantiti = kuantiti;
	}

	public Long getIdUnitisipadu() {
		return this.idUnitisipadu;
	}

	public void setIdUnitisipadu(Long idUnitisipadu) {
		this.idUnitisipadu = idUnitisipadu;
	}

	public Double getAnggaranRoyalti() {
		return this.anggaranRoyalti;
	}

	public void setAnggaranRoyalti(Double anggaranRoyalti) {
		this.anggaranRoyalti = anggaranRoyalti;
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

}