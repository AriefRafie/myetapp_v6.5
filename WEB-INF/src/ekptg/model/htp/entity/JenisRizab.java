package ekptg.model.htp.entity;

import java.util.Date;

public class JenisRizab implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3577307385757920897L;
	// Fields
	private Long id;
	private String kod;
	private String keterangan;
	private Integer aktif = 1;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors
	/** default constructor */
	public JenisRizab() {
	}

	/** minimal constructor */
	public JenisRizab(Long id) {
		this.id = id;
	}

	/** full constructor */
	public JenisRizab(Long id, String kod, String keterangan,Integer aktif
			,Long idMasuk, Date tarikhMasuk, Long idKemaskini,Date tarikhKemaskini) {
		this.id = id;
		this.kod = kod;
		this.keterangan = keterangan;
		this.aktif = aktif;
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

	public String getKod() {
		return this.kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}
	
	public Integer getAktif() {
		return this.aktif;
	}

	public void setAktif(Integer aktif) {
		this.aktif = aktif;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
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