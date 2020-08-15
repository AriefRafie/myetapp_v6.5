package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTbldokumen entity provides the base persistence definition of the
 * tblppdokumen, entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractDokumen implements java.io.Serializable {

	// Fields
	private String idDokumen;
	private String idJenis;
	private String namaDokumen;
	private String catatan;
	private String dokumen;
	private String kandungan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractDokumen() {
	}

	/** full constructor */
	public AbstractDokumen(String idDokumen, String namaDokumen
		,String catatan, String dokumen,String idJenis,String kandungan
		,Long idMasuk,Date tarikhMasuk,Long idKemaskini,Date tarikhKemaskini) {
		this.idDokumen = idDokumen;
		this.namaDokumen = namaDokumen;
		this.catatan = catatan;
		this.dokumen = dokumen;
		this.idJenis = idJenis;
		this.kandungan=kandungan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors
	public String getIdDokumen() {
		return this.idDokumen;
	}

	public void setIdDokumen(String idDokumen) {
		this.idDokumen = idDokumen;
	}

	public String getNamaDokumen() {
		return this.namaDokumen;
	}

	public void setNamaDokumen(String namaDokumen) {
		this.namaDokumen = namaDokumen;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public String getDokumen() {
		return this.dokumen;
	}

	public void setDokumen(String dokumen) {
		this.dokumen = dokumen;
	}

	public String getIdJenis() {
		return this.idJenis;
	}

	public void setKandungan(String kandungan) {
		this.kandungan = kandungan;
	}
	
	public String getKandungan() {
		return this.kandungan;
	}

	public void setIdJenis(String idJenis) {
		this.idJenis = idJenis;
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