package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtphakmilikbangunanId entity provides the base persistence
 * definition of the TblhtphakmilikbangunanId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtphakmilikbangunanId implements
		java.io.Serializable {

	// Fields

	private Long idHakmilikbangunan;
	private Tblhtppermohonan tblhtppermohonan;
        //private Long idPermohonan;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private Long idLuas;
	private String luas;
	private Long idMukim;
	private Long idDaerah;
	private Long idNegeri;
	private Double sewaBulanan;
	private String ulasan;
	private Date tarikhMula;
	private Date tarikhTamat;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtphakmilikbangunanId() {
	}

	/** minimal constructor */
	public AbstractTblhtphakmilikbangunanId(Long idHakmilikbangunan,
			Tblhtppermohonan tblhtppermohonan, Long idLuas, Long idMukim,
			Long idDaerah, Long idNegeri) {
		this.idHakmilikbangunan = idHakmilikbangunan;
		this.tblhtppermohonan = tblhtppermohonan;
                //this.idPermohonan = idPermohonan;
		this.idLuas = idLuas;
		this.idMukim = idMukim;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
	}

	/** full constructor */
	public AbstractTblhtphakmilikbangunanId(Long idHakmilikbangunan,
			Tblhtppermohonan tblhtppermohonan, String alamat1, String alamat2,
			String alamat3, String poskod, Long idLuas, String luas,
			Long idMukim, Long idDaerah, Long idNegeri, Double sewaBulanan,
			String ulasan, Date tarikhMula, Date tarikhTamat, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idHakmilikbangunan = idHakmilikbangunan;
		this.tblhtppermohonan = tblhtppermohonan;
                //this.idPermohonan = idPermohonan;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idLuas = idLuas;
		this.luas = luas;
		this.idMukim = idMukim;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
		this.sewaBulanan = sewaBulanan;
		this.ulasan = ulasan;
		this.tarikhMula = tarikhMula;
		this.tarikhTamat = tarikhTamat;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdHakmilikbangunan() {
		return this.idHakmilikbangunan;
	}

	public void setIdHakmilikbangunan(Long idHakmilikbangunan) {
		this.idHakmilikbangunan = idHakmilikbangunan;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}
        
        /*START
        //Add 070109
        public Long getIdPermohonan() {
            return this.idPermohonan;
        }
        
        public void setIdPermohonan(Long idPermohonan) {
            this.idPermohonan = idPermohonan;
        }        
        //END */

	public String getAlamat1() {
		return this.alamat1;
	}

	public void setAlamat1(String alamat1) {
		this.alamat1 = alamat1;
	}

	public String getAlamat2() {
		return this.alamat2;
	}

	public void setAlamat2(String alamat2) {
		this.alamat2 = alamat2;
	}

	public String getAlamat3() {
		return this.alamat3;
	}

	public void setAlamat3(String alamat3) {
		this.alamat3 = alamat3;
	}

	public String getPoskod() {
		return this.poskod;
	}

	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}

	public Long getIdLuas() {
		return this.idLuas;
	}

	public void setIdLuas(Long idLuas) {
		this.idLuas = idLuas;
	}

	public String getLuas() {
		return this.luas;
	}

	public void setLuas(String luas) {
		this.luas = luas;
	}

	public Long getIdMukim() {
		return this.idMukim;
	}

	public void setIdMukim(Long idMukim) {
		this.idMukim = idMukim;
	}

	public Long getIdDaerah() {
		return this.idDaerah;
	}

	public void setIdDaerah(Long idDaerah) {
		this.idDaerah = idDaerah;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public Double getSewaBulanan() {
		return this.sewaBulanan;
	}

	public void setSewaBulanan(Double sewaBulanan) {
		this.sewaBulanan = sewaBulanan;
	}

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}

	public Date getTarikhMula() {
		return this.tarikhMula;
	}

	public void setTarikhMula(Date tarikhMula) {
		this.tarikhMula = tarikhMula;
	}

	public Date getTarikhTamat() {
		return this.tarikhTamat;
	}

	public void setTarikhTamat(Date tarikhTamat) {
		this.tarikhTamat = tarikhTamat;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractTblhtphakmilikbangunanId))
			return false;
		AbstractTblhtphakmilikbangunanId castOther = (AbstractTblhtphakmilikbangunanId) other;

		return ((this.getIdHakmilikbangunan() == castOther
				.getIdHakmilikbangunan()) || (this.getIdHakmilikbangunan() != null
				&& castOther.getIdHakmilikbangunan() != null && this
				.getIdHakmilikbangunan().equals(
						castOther.getIdHakmilikbangunan())))
				&& ((this.getTblhtppermohonan() == castOther
						.getTblhtppermohonan()) || (this.getTblhtppermohonan() != null
						&& castOther.getTblhtppermohonan() != null && this
						.getTblhtppermohonan().equals(
								castOther.getTblhtppermohonan())))
				&& ((this.getAlamat1() == castOther.getAlamat1()) || (this
						.getAlamat1() != null
						&& castOther.getAlamat1() != null && this.getAlamat1()
						.equals(castOther.getAlamat1())))
				&& ((this.getAlamat2() == castOther.getAlamat2()) || (this
						.getAlamat2() != null
						&& castOther.getAlamat2() != null && this.getAlamat2()
						.equals(castOther.getAlamat2())))
				&& ((this.getAlamat3() == castOther.getAlamat3()) || (this
						.getAlamat3() != null
						&& castOther.getAlamat3() != null && this.getAlamat3()
						.equals(castOther.getAlamat3())))
				&& ((this.getPoskod() == castOther.getPoskod()) || (this
						.getPoskod() != null
						&& castOther.getPoskod() != null && this.getPoskod()
						.equals(castOther.getPoskod())))
				&& ((this.getIdLuas() == castOther.getIdLuas()) || (this
						.getIdLuas() != null
						&& castOther.getIdLuas() != null && this.getIdLuas()
						.equals(castOther.getIdLuas())))
				&& ((this.getLuas() == castOther.getLuas()) || (this.getLuas() != null
						&& castOther.getLuas() != null && this.getLuas()
						.equals(castOther.getLuas())))
				&& ((this.getIdMukim() == castOther.getIdMukim()) || (this
						.getIdMukim() != null
						&& castOther.getIdMukim() != null && this.getIdMukim()
						.equals(castOther.getIdMukim())))
				&& ((this.getIdDaerah() == castOther.getIdDaerah()) || (this
						.getIdDaerah() != null
						&& castOther.getIdDaerah() != null && this
						.getIdDaerah().equals(castOther.getIdDaerah())))
				&& ((this.getIdNegeri() == castOther.getIdNegeri()) || (this
						.getIdNegeri() != null
						&& castOther.getIdNegeri() != null && this
						.getIdNegeri().equals(castOther.getIdNegeri())))
				&& ((this.getSewaBulanan() == castOther.getSewaBulanan()) || (this
						.getSewaBulanan() != null
						&& castOther.getSewaBulanan() != null && this
						.getSewaBulanan().equals(castOther.getSewaBulanan())))
				&& ((this.getUlasan() == castOther.getUlasan()) || (this
						.getUlasan() != null
						&& castOther.getUlasan() != null && this.getUlasan()
						.equals(castOther.getUlasan())))
				&& ((this.getTarikhMula() == castOther.getTarikhMula()) || (this
						.getTarikhMula() != null
						&& castOther.getTarikhMula() != null && this
						.getTarikhMula().equals(castOther.getTarikhMula())))
				&& ((this.getTarikhTamat() == castOther.getTarikhTamat()) || (this
						.getTarikhTamat() != null
						&& castOther.getTarikhTamat() != null && this
						.getTarikhTamat().equals(castOther.getTarikhTamat())))
				&& ((this.getIdMasuk() == castOther.getIdMasuk()) || (this
						.getIdMasuk() != null
						&& castOther.getIdMasuk() != null && this.getIdMasuk()
						.equals(castOther.getIdMasuk())))
				&& ((this.getTarikhMasuk() == castOther.getTarikhMasuk()) || (this
						.getTarikhMasuk() != null
						&& castOther.getTarikhMasuk() != null && this
						.getTarikhMasuk().equals(castOther.getTarikhMasuk())))
				&& ((this.getIdKemaskini() == castOther.getIdKemaskini()) || (this
						.getIdKemaskini() != null
						&& castOther.getIdKemaskini() != null && this
						.getIdKemaskini().equals(castOther.getIdKemaskini())))
				&& ((this.getTarikhKemaskini() == castOther
						.getTarikhKemaskini()) || (this.getTarikhKemaskini() != null
						&& castOther.getTarikhKemaskini() != null && this
						.getTarikhKemaskini().equals(
								castOther.getTarikhKemaskini())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getIdHakmilikbangunan() == null ? 0 : this
						.getIdHakmilikbangunan().hashCode());
		result = 37
				* result
				+ (getTblhtppermohonan() == null ? 0 : this
						.getTblhtppermohonan().hashCode());
		result = 37 * result
				+ (getAlamat1() == null ? 0 : this.getAlamat1().hashCode());
		result = 37 * result
				+ (getAlamat2() == null ? 0 : this.getAlamat2().hashCode());
		result = 37 * result
				+ (getAlamat3() == null ? 0 : this.getAlamat3().hashCode());
		result = 37 * result
				+ (getPoskod() == null ? 0 : this.getPoskod().hashCode());
		result = 37 * result
				+ (getIdLuas() == null ? 0 : this.getIdLuas().hashCode());
		result = 37 * result
				+ (getLuas() == null ? 0 : this.getLuas().hashCode());
		result = 37 * result
				+ (getIdMukim() == null ? 0 : this.getIdMukim().hashCode());
		result = 37 * result
				+ (getIdDaerah() == null ? 0 : this.getIdDaerah().hashCode());
		result = 37 * result
				+ (getIdNegeri() == null ? 0 : this.getIdNegeri().hashCode());
		result = 37
				* result
				+ (getSewaBulanan() == null ? 0 : this.getSewaBulanan()
						.hashCode());
		result = 37 * result
				+ (getUlasan() == null ? 0 : this.getUlasan().hashCode());
		result = 37
				* result
				+ (getTarikhMula() == null ? 0 : this.getTarikhMula()
						.hashCode());
		result = 37
				* result
				+ (getTarikhTamat() == null ? 0 : this.getTarikhTamat()
						.hashCode());
		result = 37 * result
				+ (getIdMasuk() == null ? 0 : this.getIdMasuk().hashCode());
		result = 37
				* result
				+ (getTarikhMasuk() == null ? 0 : this.getTarikhMasuk()
						.hashCode());
		result = 37
				* result
				+ (getIdKemaskini() == null ? 0 : this.getIdKemaskini()
						.hashCode());
		result = 37
				* result
				+ (getTarikhKemaskini() == null ? 0 : this.getTarikhKemaskini()
						.hashCode());
		return result;
	}

}