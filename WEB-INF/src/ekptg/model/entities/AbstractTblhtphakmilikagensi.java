package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtphakmilikagensi entity provides the base persistence definition
 * of the Tblhtphakmilikagensi entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtphakmilikagensi implements
		java.io.Serializable {

	// Fields

	private Long idHakmilikagensi;
	private Long idHakmilik;
	private Double luasAsal;
	private Double luasBaru;
	private Long idAgensi;
	private Long idKementerian;
	private Date tarikhKkini;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtphakmilikagensi() {
	}

	/** minimal constructor */
	public AbstractTblhtphakmilikagensi(Long idHakmilikagensi, Long idHakmilik,
			Long idAgensi, Long idKementerian) {
		this.idHakmilikagensi = idHakmilikagensi;
		this.idHakmilik = idHakmilik;
		this.idAgensi = idAgensi;
		this.idKementerian = idKementerian;
	}

	/** full constructor */
	public AbstractTblhtphakmilikagensi(Long idHakmilikagensi, Long idHakmilik,
			Double luasAsal, Double luasBaru, Long idAgensi,
			Long idKementerian, Date tarikhKkini, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idHakmilikagensi = idHakmilikagensi;
		this.idHakmilik = idHakmilik;
		this.luasAsal = luasAsal;
		this.luasBaru = luasBaru;
		this.idAgensi = idAgensi;
		this.idKementerian = idKementerian;
		this.tarikhKkini = tarikhKkini;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdHakmilikagensi() {
		return this.idHakmilikagensi;
	}

	public void setIdHakmilikagensi(Long idHakmilikagensi) {
		this.idHakmilikagensi = idHakmilikagensi;
	}

	public Long getIdHakmilik() {
		return this.idHakmilik;
	}

	public void setIdHakmilik(Long idHakmilik) {
		this.idHakmilik = idHakmilik;
	}

	public Double getLuasAsal() {
		return this.luasAsal;
	}

	public void setLuasAsal(Double luasAsal) {
		this.luasAsal = luasAsal;
	}

	public Double getLuasBaru() {
		return this.luasBaru;
	}

	public void setLuasBaru(Double luasBaru) {
		this.luasBaru = luasBaru;
	}

	public Long getIdAgensi() {
		return this.idAgensi;
	}

	public void setIdAgensi(Long idAgensi) {
		this.idAgensi = idAgensi;
	}

	public Long getIdKementerian() {
		return this.idKementerian;
	}

	public void setIdKementerian(Long idKementerian) {
		this.idKementerian = idKementerian;
	}

	public Date getTarikhKkini() {
		return this.tarikhKkini;
	}

	public void setTarikhKkini(Date tarikhKkini) {
		this.tarikhKkini = tarikhKkini;
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