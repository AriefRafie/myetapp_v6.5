package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppkperintahhtaobdtl entity provides the base persistence
 * definition of the Tblppkperintahhtaobdtl entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkperintahhtaobdtl implements
		java.io.Serializable {

	// Fields

	private Long idPerintahhtaobdtl;
	private Tblppkperintahhtaobmst tblppkperintahhtaobmst;
	private Tblppkob tblppkob;
	private Long ba;
	private Long bb;
	private Long pekali;
	private String minor;
	private Long idPenjaga1;
	private String kaveat1;
	private String batalPa1;
	private Long idPenjaga2;
	private String kaveat2;
	private String batalPa2;
	private Long idPenjaga3;
	private String kaveat3;
	private String batalPa3;
	private Long idPenjaga4;
	private String kaveat4;
	private String batalPa4;
	private String statusTadbir;
	private String catatan;
	private Long idPa1;
	private Long idPa2;
	private Long idPa3;
	private Long idPa4;
	private String wakil;
	private String keteranganOb;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblppkperintahhtaobdtl() {
	}

	/** minimal constructor */
	public AbstractTblppkperintahhtaobdtl(Long idPerintahhtaobdtl,
			Tblppkperintahhtaobmst tblppkperintahhtaobmst, Tblppkob tblppkob) {
		this.idPerintahhtaobdtl = idPerintahhtaobdtl;
		this.tblppkperintahhtaobmst = tblppkperintahhtaobmst;
		this.tblppkob = tblppkob;
	}

	/** full constructor */
	public AbstractTblppkperintahhtaobdtl(Long idPerintahhtaobdtl,
			Tblppkperintahhtaobmst tblppkperintahhtaobmst, Tblppkob tblppkob,
			Long ba, Long bb, Long pekali, String minor, Long idPenjaga1,
			String kaveat1, String batalPa1, Long idPenjaga2, String kaveat2,
			String batalPa2, Long idPenjaga3, String kaveat3, String batalPa3,
			Long idPenjaga4, String kaveat4, String batalPa4,
			String statusTadbir, String catatan, Long idPa1, Long idPa2,
			Long idPa3, Long idPa4, String wakil, String keteranganOb,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idPerintahhtaobdtl = idPerintahhtaobdtl;
		this.tblppkperintahhtaobmst = tblppkperintahhtaobmst;
		this.tblppkob = tblppkob;
		this.ba = ba;
		this.bb = bb;
		this.pekali = pekali;
		this.minor = minor;
		this.idPenjaga1 = idPenjaga1;
		this.kaveat1 = kaveat1;
		this.batalPa1 = batalPa1;
		this.idPenjaga2 = idPenjaga2;
		this.kaveat2 = kaveat2;
		this.batalPa2 = batalPa2;
		this.idPenjaga3 = idPenjaga3;
		this.kaveat3 = kaveat3;
		this.batalPa3 = batalPa3;
		this.idPenjaga4 = idPenjaga4;
		this.kaveat4 = kaveat4;
		this.batalPa4 = batalPa4;
		this.statusTadbir = statusTadbir;
		this.catatan = catatan;
		this.idPa1 = idPa1;
		this.idPa2 = idPa2;
		this.idPa3 = idPa3;
		this.idPa4 = idPa4;
		this.wakil = wakil;
		this.keteranganOb = keteranganOb;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPerintahhtaobdtl() {
		return this.idPerintahhtaobdtl;
	}

	public void setIdPerintahhtaobdtl(Long idPerintahhtaobdtl) {
		this.idPerintahhtaobdtl = idPerintahhtaobdtl;
	}

	public Tblppkperintahhtaobmst getTblppkperintahhtaobmst() {
		return this.tblppkperintahhtaobmst;
	}

	public void setTblppkperintahhtaobmst(
			Tblppkperintahhtaobmst tblppkperintahhtaobmst) {
		this.tblppkperintahhtaobmst = tblppkperintahhtaobmst;
	}

	public Tblppkob getTblppkob() {
		return this.tblppkob;
	}

	public void setTblppkob(Tblppkob tblppkob) {
		this.tblppkob = tblppkob;
	}

	public Long getBa() {
		return this.ba;
	}

	public void setBa(Long ba) {
		this.ba = ba;
	}

	public Long getBb() {
		return this.bb;
	}

	public void setBb(Long bb) {
		this.bb = bb;
	}

	public Long getPekali() {
		return this.pekali;
	}

	public void setPekali(Long pekali) {
		this.pekali = pekali;
	}

	public String getMinor() {
		return this.minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}

	public Long getIdPenjaga1() {
		return this.idPenjaga1;
	}

	public void setIdPenjaga1(Long idPenjaga1) {
		this.idPenjaga1 = idPenjaga1;
	}

	public String getKaveat1() {
		return this.kaveat1;
	}

	public void setKaveat1(String kaveat1) {
		this.kaveat1 = kaveat1;
	}

	public String getBatalPa1() {
		return this.batalPa1;
	}

	public void setBatalPa1(String batalPa1) {
		this.batalPa1 = batalPa1;
	}

	public Long getIdPenjaga2() {
		return this.idPenjaga2;
	}

	public void setIdPenjaga2(Long idPenjaga2) {
		this.idPenjaga2 = idPenjaga2;
	}

	public String getKaveat2() {
		return this.kaveat2;
	}

	public void setKaveat2(String kaveat2) {
		this.kaveat2 = kaveat2;
	}

	public String getBatalPa2() {
		return this.batalPa2;
	}

	public void setBatalPa2(String batalPa2) {
		this.batalPa2 = batalPa2;
	}

	public Long getIdPenjaga3() {
		return this.idPenjaga3;
	}

	public void setIdPenjaga3(Long idPenjaga3) {
		this.idPenjaga3 = idPenjaga3;
	}

	public String getKaveat3() {
		return this.kaveat3;
	}

	public void setKaveat3(String kaveat3) {
		this.kaveat3 = kaveat3;
	}

	public String getBatalPa3() {
		return this.batalPa3;
	}

	public void setBatalPa3(String batalPa3) {
		this.batalPa3 = batalPa3;
	}

	public Long getIdPenjaga4() {
		return this.idPenjaga4;
	}

	public void setIdPenjaga4(Long idPenjaga4) {
		this.idPenjaga4 = idPenjaga4;
	}

	public String getKaveat4() {
		return this.kaveat4;
	}

	public void setKaveat4(String kaveat4) {
		this.kaveat4 = kaveat4;
	}

	public String getBatalPa4() {
		return this.batalPa4;
	}

	public void setBatalPa4(String batalPa4) {
		this.batalPa4 = batalPa4;
	}

	public String getStatusTadbir() {
		return this.statusTadbir;
	}

	public void setStatusTadbir(String statusTadbir) {
		this.statusTadbir = statusTadbir;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public Long getIdPa1() {
		return this.idPa1;
	}

	public void setIdPa1(Long idPa1) {
		this.idPa1 = idPa1;
	}

	public Long getIdPa2() {
		return this.idPa2;
	}

	public void setIdPa2(Long idPa2) {
		this.idPa2 = idPa2;
	}

	public Long getIdPa3() {
		return this.idPa3;
	}

	public void setIdPa3(Long idPa3) {
		this.idPa3 = idPa3;
	}

	public Long getIdPa4() {
		return this.idPa4;
	}

	public void setIdPa4(Long idPa4) {
		this.idPa4 = idPa4;
	}

	public String getWakil() {
		return this.wakil;
	}

	public void setWakil(String wakil) {
		this.wakil = wakil;
	}

	public String getKeteranganOb() {
		return this.keteranganOb;
	}

	public void setKeteranganOb(String keteranganOb) {
		this.keteranganOb = keteranganOb;
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