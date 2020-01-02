package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtppergerakan entity provides the base persistence definition of
 * the Tblhtppergerakan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtppergerakan implements java.io.Serializable {

	// Fields

	private Long idPergerakan;
	private Tblhtppermohonan tblhtppermohonan;
	private Long idHakmilikpegangan;
	private Date tarikh;
	private String keterangan;
	private Long bilSalinan;
	private String status;
	private Long idMasuk;
	private Date tarikhMasuk;

	// Constructors

	/** default constructor */
	public AbstractTblhtppergerakan() {
	}

	/** minimal constructor */
	public AbstractTblhtppergerakan(Long idPergerakan,
			Tblhtppermohonan tblhtppermohonan, Long idHakmilikpegangan,
			Long idMasuk) {
		this.idPergerakan = idPergerakan;
		this.tblhtppermohonan = tblhtppermohonan;
		this.idHakmilikpegangan = idHakmilikpegangan;
		this.idMasuk = idMasuk;
	}

	/** full constructor */
	public AbstractTblhtppergerakan(Long idPergerakan,
			Tblhtppermohonan tblhtppermohonan, Long idHakmilikpegangan,
			Date tarikh, String keterangan, Long bilSalinan, String status,
			Long idMasuk, Date tarikhMasuk) {
		this.idPergerakan = idPergerakan;
		this.tblhtppermohonan = tblhtppermohonan;
		this.idHakmilikpegangan = idHakmilikpegangan;
		this.tarikh = tarikh;
		this.keterangan = keterangan;
		this.bilSalinan = bilSalinan;
		this.status = status;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
	}

	// Property accessors

	public Long getIdPergerakan() {
		return this.idPergerakan;
	}

	public void setIdPergerakan(Long idPergerakan) {
		this.idPergerakan = idPergerakan;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

	public Long getIdHakmilikpegangan() {
		return this.idHakmilikpegangan;
	}

	public void setIdHakmilikpegangan(Long idHakmilikpegangan) {
		this.idHakmilikpegangan = idHakmilikpegangan;
	}

	public Date getTarikh() {
		return this.tarikh;
	}

	public void setTarikh(Date tarikh) {
		this.tarikh = tarikh;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public Long getBilSalinan() {
		return this.bilSalinan;
	}

	public void setBilSalinan(Long bilSalinan) {
		this.bilSalinan = bilSalinan;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

}