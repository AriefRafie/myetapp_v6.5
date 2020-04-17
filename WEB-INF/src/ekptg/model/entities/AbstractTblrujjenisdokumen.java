package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujjenisdokumen entity provides the base persistence definition of
 * the Tblrujjenisdokumen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujjenisdokumen implements
		java.io.Serializable {

	// Fields

	private Long idJenisdokumen;
	private String kodJenisDokumen;
	private String keterangan;
	private String idLaporan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
        private Long idSeksyen;

	// Constructors

	/** default constructor */
	public AbstractTblrujjenisdokumen() {
	}

	/** minimal constructor */
	public AbstractTblrujjenisdokumen(Long idJenisdokumen) {
		this.idJenisdokumen = idJenisdokumen;
	}

	/** full constructor */
	public AbstractTblrujjenisdokumen(Long idJenisdokumen, String kodJenisDokumen,
			String keterangan, String idLaporan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idSeksyen) {
		this.idJenisdokumen = idJenisdokumen;
		this.kodJenisDokumen = kodJenisDokumen;
		this.keterangan = keterangan;
		this.idLaporan = idLaporan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
                this.idSeksyen = idSeksyen;
	}

	// Property accessors

	public Long getIdJenisdokumen() {
		return this.idJenisdokumen;
	}

	public void setIdJenisdokumen(Long idJenisdokumen) {
		this.idJenisdokumen = idJenisdokumen;
	}

	public String getKodJenisDokumen() {
		return this.kodJenisDokumen;
	}

	public void setKodJenisDokumen(String kodJenisDokumen) {
		this.kodJenisDokumen = kodJenisDokumen;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getIdLaporan() {
		return this.idLaporan;
	}

	public void setIdLaporan(String idLaporan) {
		this.idLaporan = idLaporan;
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
         public Long getIdSeksyen() {
            return this.idSeksyen;
         }

        public void setIdSeksyen(Long idSeksyen) {
            this.idSeksyen = idSeksyen;
        }

}