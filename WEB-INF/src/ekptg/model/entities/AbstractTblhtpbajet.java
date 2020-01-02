package ekptg.model.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblhtpbajet entity provides the base persistence definition of the
 * Tblhtpbajet entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpbajet implements java.io.Serializable {

	// Fields

	private Long idBajet;
	private Double jumlah;
	private Long tahun;
	private Long idNegeri;
	private Set tblhtpbajetgunas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblhtpbajet() {
	}

	/** minimal constructor */
	public AbstractTblhtpbajet(Long idBajet, Long idNegeri) {
		this.idBajet = idBajet;
		this.idNegeri = idNegeri;
	}

	/** full constructor */
	public AbstractTblhtpbajet(Long idBajet, Double jumlah, Long tahun,
			Long idNegeri, Set tblhtpbajetgunas) {
		this.idBajet = idBajet;
		this.jumlah = jumlah;
		this.tahun = tahun;
		this.idNegeri = idNegeri;
		this.tblhtpbajetgunas = tblhtpbajetgunas;
	}

	// Property accessors

	public Long getIdBajet() {
		return this.idBajet;
	}

	public void setIdBajet(Long idBajet) {
		this.idBajet = idBajet;
	}

	public Double getJumlah() {
		return this.jumlah;
	}

	public void setJumlah(Double jumlah) {
		this.jumlah = jumlah;
	}

	public Long getTahun() {
		return this.tahun;
	}

	public void setTahun(Long tahun) {
		this.tahun = tahun;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public Set getTblhtpbajetgunas() {
		return this.tblhtpbajetgunas;
	}

	public void setTblhtpbajetgunas(Set tblhtpbajetgunas) {
		this.tblhtpbajetgunas = tblhtpbajetgunas;
	}

}