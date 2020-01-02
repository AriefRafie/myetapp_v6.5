package ekptg.model.entities; 
//baru-yati
import java.util.Date;

/**
 * AbstractTblintrujkategoritanah entity provides the base persistence definition of the
 * Tblintrujkategoritanah entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public abstract class AbstractTblintrujkategoritanah implements java.io.Serializable {

	// Fields

	private Long id_kategoritanah;
	private String kod_kategoritanah;
	@SuppressWarnings("unused")
	private String kod_jpph;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblintrujkategoritanah() {
	}

	/** minimal constructor */
	public AbstractTblintrujkategoritanah(Long idKategoritanah) {
		this.id_kategoritanah = idKategoritanah;
		
	}

	/** full constructor */
	public AbstractTblintrujkategoritanah(Long idKategoritanah, String kodKategoritanah,
			String kodJPPH, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.id_kategoritanah = idKategoritanah;
		this.kod_kategoritanah = kodKategoritanah;
		this.kod_jpph = kodJPPH;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdKategoriTanah() {
		return this.id_kategoritanah;
	}

	public void setIdKategoriTanah(Long idKategoriTanah) {
		this.id_kategoritanah = idKategoriTanah;
	}

	public String getKodKategoriTanah() {
		return this.kod_kategoritanah;
	}

	public void setKodJPPH(String kodJPPH) {
		this.kod_jpph = kodJPPH;
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