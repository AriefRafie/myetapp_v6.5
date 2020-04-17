package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblintrujkategoritanah entity provides the base persistence definition of the
 * Tblintrujkategoritanah entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public abstract class AbstractTblrujjenisbangunan implements java.io.Serializable {

	// Fields

	private Long id_jenis_bangunan;
	private String nama_bangunan;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujjenisbangunan() {
	}

	/** minimal constructor */
	public AbstractTblrujjenisbangunan(Long idJenisBangunan) {
		this.id_jenis_bangunan = idJenisBangunan;
		
	}

	/** full constructor */
	public AbstractTblrujjenisbangunan(Long idJenisBangunan, String namaBangunan,String keteranganBangunan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.id_jenis_bangunan = idJenisBangunan;
		this.nama_bangunan = namaBangunan;
		this.keterangan = keteranganBangunan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdJenisBangunan() {
		return this.id_jenis_bangunan;
	}

	public void setIdJenisBangunan(Long idJenisBangunan) {
		this.id_jenis_bangunan = idJenisBangunan;
	}

	public String getNamaBangunan() {
		return this.nama_bangunan;
	}
	
	public void setNamaBangunan(String NamaBangunan) {
		this.nama_bangunan = NamaBangunan;
	}

	public String getKeteranganBangunan() {
		return this.keterangan;
	}
	
	public void setKeteranganBangunan(String keteranganBangunan) {
		this.keterangan = keteranganBangunan;
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