package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblppkkeputusanpermohonan entity provides the base persistence
 * definition of the Tblppkkeputusanpermohonan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkkeputusanpermohonan implements
		java.io.Serializable {

	// Fields

	private Long idKeputusanpermohonan;
//	private Tblppkpermohonan tblppkpermohonan;
        private Long idPermohonan;
	private String catatan;
	private Date tarikhHantarBorangb;
	private Date tarikhTerimaBorangc;
	private String keputusanPermohonan;
	private Date tarikhHantarNilaian;
	private Date tarikhTerimaNilaian;
	private Long idNegerimahkamah;
	private Long idDaerahMahkamah;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Date idKemaskini;
	private Set tblppkperbicaraans = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblppkkeputusanpermohonan() {
	}

	/** minimal constructor */
	public AbstractTblppkkeputusanpermohonan(Long idKeputusanpermohonan
			) {
		this.idKeputusanpermohonan = idKeputusanpermohonan;
	//	this.tblppkpermohonan = tblppkpermohonan;
	}

	/** full constructor */
	public AbstractTblppkkeputusanpermohonan(Long idKeputusanpermohonan,
			Long idPermohonan, String catatan,
			Date tarikhHantarBorangb, Date tarikhTerimaBorangc,
			String keputusanPermohonan, Date tarikhHantarNilaian,
			Date tarikhTerimaNilaian, Long idNegerimahkamah,
			Long idDaerahMahkamah, Long idMasuk, Date tarikhMasuk,
			Date idKemaskini, Set tblppkperbicaraans) {
		this.idKeputusanpermohonan = idKeputusanpermohonan;
	//	this.tblppkpermohonan = tblppkpermohonan;
                this.idPermohonan = idPermohonan;
		this.catatan = catatan;
		this.tarikhHantarBorangb = tarikhHantarBorangb;
		this.tarikhTerimaBorangc = tarikhTerimaBorangc;
		this.keputusanPermohonan = keputusanPermohonan;
		this.tarikhHantarNilaian = tarikhHantarNilaian;
		this.tarikhTerimaNilaian = tarikhTerimaNilaian;
		this.idNegerimahkamah = idNegerimahkamah;
		this.idDaerahMahkamah = idDaerahMahkamah;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tblppkperbicaraans = tblppkperbicaraans;
	}

	// Property accessors

	public Long getIdKeputusanpermohonan() {
		return this.idKeputusanpermohonan;
	}

	public void setIdKeputusanpermohonan(Long idKeputusanpermohonan) {
		this.idKeputusanpermohonan = idKeputusanpermohonan;
	}

/*	public Tblppkpermohonan getTblppkpermohonan() {
		return this.tblppkpermohonan;
	}

	public void setTblppkpermohonan(Tblppkpermohonan tblppkpermohonan) {
		this.tblppkpermohonan = tblppkpermohonan;
	}
*/
	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public Date getTarikhHantarBorangb() {
		return this.tarikhHantarBorangb;
	}

	public void setTarikhHantarBorangb(Date tarikhHantarBorangb) {
		this.tarikhHantarBorangb = tarikhHantarBorangb;
	}

	public Date getTarikhTerimaBorangc() {
		return this.tarikhTerimaBorangc;
	}

	public void setTarikhTerimaBorangc(Date tarikhTerimaBorangc) {
		this.tarikhTerimaBorangc = tarikhTerimaBorangc;
	}

	public String getKeputusanPermohonan() {
		return this.keputusanPermohonan;
	}

	public void setKeputusanPermohonan(String keputusanPermohonan) {
		this.keputusanPermohonan = keputusanPermohonan;
	}

	public Date getTarikhHantarNilaian() {
		return this.tarikhHantarNilaian;
	}

	public void setTarikhHantarNilaian(Date tarikhHantarNilaian) {
		this.tarikhHantarNilaian = tarikhHantarNilaian;
	}

	public Date getTarikhTerimaNilaian() {
		return this.tarikhTerimaNilaian;
	}

	public void setTarikhTerimaNilaian(Date tarikhTerimaNilaian) {
		this.tarikhTerimaNilaian = tarikhTerimaNilaian;
	}

	public Long getIdNegerimahkamah() {
		return this.idNegerimahkamah;
	}

	public void setIdNegerimahkamah(Long idNegerimahkamah) {
		this.idNegerimahkamah = idNegerimahkamah;
	}

	public Long getIdDaerahMahkamah() {
		return this.idDaerahMahkamah;
	}

	public void setIdDaerahMahkamah(Long idDaerahMahkamah) {
		this.idDaerahMahkamah = idDaerahMahkamah;
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

	public Date getIdKemaskini() {
		return this.idKemaskini;
	}

	public void setIdKemaskini(Date idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public Set getTblppkperbicaraans() {
		return this.tblppkperbicaraans;
	}

	public void setTblppkperbicaraans(Set tblppkperbicaraans) {
		this.tblppkperbicaraans = tblppkperbicaraans;
	}

    public void setIdPermohonan(Long idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Long getIdPermohonan() {
        return idPermohonan;
    }
}
