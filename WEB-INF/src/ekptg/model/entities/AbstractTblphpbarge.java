package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphpbarge entity provides the base persistence definition of the
 * Tblphpbarge entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpbarge implements java.io.Serializable {

	// Fields

	private Long idBarge;
	private Tblphpboranga tblphpboranga;
	private Date tarikhHantar;
	private String namaBarge;
	private Long idJenisbarge;
	private String lokasiDibekalkan;
	private String noPendaftaran;
	private Long muatan;
	private Long idMuatan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphpbarge() {
	}

	/** minimal constructor */
	public AbstractTblphpbarge(Long idBarge) {
		this.idBarge = idBarge;
	}

	/** full constructor */
	public AbstractTblphpbarge(Long idBarge, Tblphpboranga tblphpboranga,
			Date tarikhHantar, String namaBarge, Long idJenisbarge,
			String lokasiDibekalkan, String noPendaftaran, Long muatan,
			Long idMuatan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idBarge = idBarge;
		this.tblphpboranga = tblphpboranga;
		this.tarikhHantar = tarikhHantar;
		this.namaBarge = namaBarge;
		this.idJenisbarge = idJenisbarge;
		this.lokasiDibekalkan = lokasiDibekalkan;
		this.noPendaftaran = noPendaftaran;
		this.muatan = muatan;
		this.idMuatan = idMuatan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdBarge() {
		return this.idBarge;
	}

	public void setIdBarge(Long idBarge) {
		this.idBarge = idBarge;
	}

	public Tblphpboranga getTblphpboranga() {
		return this.tblphpboranga;
	}

	public void setTblphpboranga(Tblphpboranga tblphpboranga) {
		this.tblphpboranga = tblphpboranga;
	}

	public Date getTarikhHantar() {
		return this.tarikhHantar;
	}

	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}

	public String getNamaBarge() {
		return this.namaBarge;
	}

	public void setNamaBarge(String namaBarge) {
		this.namaBarge = namaBarge;
	}

	public Long getIdJenisbarge() {
		return this.idJenisbarge;
	}

	public void setIdJenisbarge(Long idJenisbarge) {
		this.idJenisbarge = idJenisbarge;
	}

	public String getLokasiDibekalkan() {
		return this.lokasiDibekalkan;
	}

	public void setLokasiDibekalkan(String lokasiDibekalkan) {
		this.lokasiDibekalkan = lokasiDibekalkan;
	}

	public String getNoPendaftaran() {
		return this.noPendaftaran;
	}

	public void setNoPendaftaran(String noPendaftaran) {
		this.noPendaftaran = noPendaftaran;
	}

	public Long getMuatan() {
		return this.muatan;
	}

	public void setMuatan(Long muatan) {
		this.muatan = muatan;
	}

	public Long getIdMuatan() {
		return this.idMuatan;
	}

	public void setIdMuatan(Long idMuatan) {
		this.idMuatan = idMuatan;
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