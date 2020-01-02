package ekptg.model.htp.entity;

import java.util.Date;

public class HakmilikStrata implements java.io.Serializable {
	// Fields
	private Long id;
	private String bangunan;
	private String tingkat;
	private String petak;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public HakmilikStrata() {
	}

	/** minimal constructor */
	public HakmilikStrata(Long id) {
		this.id = id;
	}

	/** full constructor */
	public HakmilikStrata(Long id, String bangunan, String tingkat,String petak
			,Long idMasuk, Date tarikhMasuk, Long idKemaskini,Date tarikhKemaskini) {
		this.id = id;
		this.bangunan = bangunan;
		this.tingkat = tingkat;
		this.petak = petak;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBangunan() {
		return this.bangunan;
	}

	public void setBangunan(String bangunan) {
		this.bangunan = bangunan;
	}
	
	public String getPetak() {
		return this.petak;
	}

	public void setPetak(String petak) {
		this.petak = petak;
	}

	public String getTingkat() {
		return this.tingkat;
	}

	public void setTingkat(String tingkat) {
		this.tingkat = tingkat;
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