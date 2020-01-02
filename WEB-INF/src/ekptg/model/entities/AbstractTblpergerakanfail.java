package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpergerakanfail entity provides the base persistence definition of
 * the Tblpergerakanfail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpergerakanfail implements java.io.Serializable {

	// Fields

	private Long idPergerakanFail;
	private String kodFail;
	private Date tarikhPergerakan;
	private String kodPenghantar;
	private String kodPenerima;
	private Date tarikhHantar;
	private Date tarikhTerima;
	private String kodStatusPergerakan;
	private String catatan;

	// Constructors

	/** default constructor */
	public AbstractTblpergerakanfail() {
	}

	/** full constructor */
	public AbstractTblpergerakanfail(String kodFail, Date tarikhPergerakan,
			String kodPenghantar, String kodPenerima, Date tarikhHantar,
			Date tarikhTerima, String kodStatusPergerakan, String catatan) {
		this.kodFail = kodFail;
		this.tarikhPergerakan = tarikhPergerakan;
		this.kodPenghantar = kodPenghantar;
		this.kodPenerima = kodPenerima;
		this.tarikhHantar = tarikhHantar;
		this.tarikhTerima = tarikhTerima;
		this.kodStatusPergerakan = kodStatusPergerakan;
		this.catatan = catatan;
	}

	// Property accessors

	public Long getIdPergerakanFail() {
		return this.idPergerakanFail;
	}

	public void setIdPergerakanFail(Long idPergerakanFail) {
		this.idPergerakanFail = idPergerakanFail;
	}

	public String getKodFail() {
		return this.kodFail;
	}

	public void setKodFail(String kodFail) {
		this.kodFail = kodFail;
	}

	public Date getTarikhPergerakan() {
		return this.tarikhPergerakan;
	}

	public void setTarikhPergerakan(Date tarikhPergerakan) {
		this.tarikhPergerakan = tarikhPergerakan;
	}

	public String getKodPenghantar() {
		return this.kodPenghantar;
	}

	public void setKodPenghantar(String kodPenghantar) {
		this.kodPenghantar = kodPenghantar;
	}

	public String getKodPenerima() {
		return this.kodPenerima;
	}

	public void setKodPenerima(String kodPenerima) {
		this.kodPenerima = kodPenerima;
	}

	public Date getTarikhHantar() {
		return this.tarikhHantar;
	}

	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public String getKodStatusPergerakan() {
		return this.kodStatusPergerakan;
	}

	public void setKodStatusPergerakan(String kodStatusPergerakan) {
		this.kodStatusPergerakan = kodStatusPergerakan;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

}