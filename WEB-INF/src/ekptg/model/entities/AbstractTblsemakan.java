package ekptg.model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblsemakan entity provides the base persistence definition of the
 * Tblsemakan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblsemakan implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2788984866636892387L;
	private Long idSemakan;
	private Long idParent;
	private String kodSemak;
	private String perihal;
	private String lainLain;
	private String tarikhMasukf;
	private String tarikhKemaskinif;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblsemakansenarais = new HashSet(0);
	private int bilangan;

	// Constructors

	/** default constructor */
	public AbstractTblsemakan() {
	}

	/** minimal constructor */
	public AbstractTblsemakan(Long idSemakan, String kodSemak) {
		this.idSemakan = idSemakan;
		this.kodSemak = kodSemak;
	}

	/** full constructor */
	public AbstractTblsemakan(Long idSemakan, Long idParent, String kodSemak,
			String perihal, String lainLain, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblsemakansenarais) {
		this.idSemakan = idSemakan;
		this.idParent = idParent;
		this.kodSemak = kodSemak;
		this.perihal = perihal;
		this.lainLain = lainLain;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblsemakansenarais = tblsemakansenarais;
	}

	// Property accessors
	public int getBil() {
		return this.bilangan;
	}

	public void setBil(int bilangan) {
		this.bilangan = bilangan;
	}
	
	public Long getIdSemakan() {
		return this.idSemakan;
	}

	public void setIdSemakan(Long idSemakan) {
		this.idSemakan = idSemakan;
	}

	public Long getIdParent() {
		return this.idParent;
	}

	public void setIdParent(Long idParent) {
		this.idParent = idParent;
	}

	public String getKodSemak() {
		return this.kodSemak;
	}

	public void setKodSemak(String kodSemak) {
		this.kodSemak = kodSemak;
	}

	public String getPerihal() {
		return this.perihal;
	}

	public void setPerihal(String perihal) {
		this.perihal = perihal;
	}

	public String getLainLain() {
		return this.lainLain;
	}

	public void setLainLain(String lainLain) {
		this.lainLain = lainLain;
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

	public void setTarikhMasukf(String tarikhMasuk) {
		this.tarikhMasukf = tarikhMasuk;
	}
	
	public String getTarikhMasukf() {
		return this.tarikhMasukf;
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
	
	public void setTarikhKemaskinif(String tarikhKemaskini) {
		this.tarikhKemaskinif = tarikhKemaskini;
	}
	
	public String getTarikhKemaskinif() {
		return tarikhKemaskinif;
	}
	
	public Set getTblsemakansenarais() {
		return this.tblsemakansenarais;
	}

	public void setTblsemakansenarais(Set tblsemakansenarais) {
		this.tblsemakansenarais = tblsemakansenarais;
	}

	
}