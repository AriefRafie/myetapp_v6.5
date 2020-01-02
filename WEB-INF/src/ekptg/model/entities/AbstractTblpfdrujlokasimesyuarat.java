package ekptg.model.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpfdrujlokasimesyuarat entity provides the base persistence
 * definition of the Tblpfdrujlokasimesyuarat entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class AbstractTblpfdrujlokasimesyuarat implements
		java.io.Serializable {

	// Fields

	private long idLokasi;
	private String lokasi;
	private BigDecimal idMasuk;
	private Date tarikhMasuk;
	private BigDecimal idKemaskini;
	private Date tarikhKemaskini;
	private BigDecimal idNegeri;
	private BigDecimal idSeksyen;
	private Set tblpfdmesyuarats = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpfdrujlokasimesyuarat() {
	}

	/** full constructor */
	public AbstractTblpfdrujlokasimesyuarat(String lokasi, BigDecimal idMasuk,
			Date tarikhMasuk, BigDecimal idKemaskini, Date tarikhKemaskini,
			BigDecimal idNegeri, BigDecimal idSeksyen, Set tblpfdmesyuarats) {
		this.lokasi = lokasi;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idNegeri = idNegeri;
		this.idSeksyen = idSeksyen;
		this.tblpfdmesyuarats = tblpfdmesyuarats;
	}

	// Property accessors

	public Long getIdLokasi() {
		return this.idLokasi;
	}

	public void setIdLokasi(long idLokasi) {
		this.idLokasi = idLokasi;
	}

	public String getLokasi() {
		return this.lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

	public BigDecimal getIdMasuk() {
		return this.idMasuk;
	}

	public void setIdMasuk(BigDecimal idMasuk) {
		this.idMasuk = idMasuk;
	}

	public Date getTarikhMasuk() {
		return this.tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public BigDecimal getIdKemaskini() {
		return this.idKemaskini;
	}

	public void setIdKemaskini(BigDecimal idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public Date getTarikhKemaskini() {
		return this.tarikhKemaskini;
	}

	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

	public BigDecimal getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(BigDecimal idNegeri) {
		this.idNegeri = idNegeri;
	}

	public BigDecimal getIdSeksyen() {
		return this.idSeksyen;
	}

	public void setIdSeksyen(BigDecimal idSeksyen) {
		this.idSeksyen = idSeksyen;
	}

	public Set getTblpfdmesyuarats() {
		return this.tblpfdmesyuarats;
	}

	public void setTblpfdmesyuarats(Set tblpfdmesyuarats) {
		this.tblpfdmesyuarats = tblpfdmesyuarats;
	}

}