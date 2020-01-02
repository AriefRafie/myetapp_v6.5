package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblppknotisobmst entity provides the base persistence definition of
 * the Tblppknotisobmst entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppknotisobmst implements java.io.Serializable {

	// Fields

	private Long idNotisobmst;
	private Long bil;
	private Date tarikhSerahan;
	private String statusSerah;
	private String jenisSerah;
	private String statusAkuanSumpah;
	private String catatan;
	private String namaPenghantarNotis;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppknotisperbicaraans = new HashSet(0);
	private Set tblppknotisobdtls = new HashSet(0);
	private Set tblppknotiskolaterals = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblppknotisobmst() {
	}

	/** minimal constructor */
	public AbstractTblppknotisobmst(Long idNotisobmst) {
		this.idNotisobmst = idNotisobmst;
	}

	/** full constructor */
	public AbstractTblppknotisobmst(Long idNotisobmst, Long bil,
			Date tarikhSerahan, String statusSerah, String jenisSerah,
			String statusAkuanSumpah, String catatan,
			String namaPenghantarNotis, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini,
			Set tblppknotisperbicaraans, Set tblppknotisobdtls,
			Set tblppknotiskolaterals) {
		this.idNotisobmst = idNotisobmst;
		this.bil = bil;
		this.tarikhSerahan = tarikhSerahan;
		this.statusSerah = statusSerah;
		this.jenisSerah = jenisSerah;
		this.statusAkuanSumpah = statusAkuanSumpah;
		this.catatan = catatan;
		this.namaPenghantarNotis = namaPenghantarNotis;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppknotisperbicaraans = tblppknotisperbicaraans;
		this.tblppknotisobdtls = tblppknotisobdtls;
		this.tblppknotiskolaterals = tblppknotiskolaterals;
	}

	// Property accessors

	public Long getIdNotisobmst() {
		return this.idNotisobmst;
	}

	public void setIdNotisobmst(Long idNotisobmst) {
		this.idNotisobmst = idNotisobmst;
	}

	public Long getBil() {
		return this.bil;
	}

	public void setBil(Long bil) {
		this.bil = bil;
	}

	public Date getTarikhSerahan() {
		return this.tarikhSerahan;
	}

	public void setTarikhSerahan(Date tarikhSerahan) {
		this.tarikhSerahan = tarikhSerahan;
	}

	public String getStatusSerah() {
		return this.statusSerah;
	}

	public void setStatusSerah(String statusSerah) {
		this.statusSerah = statusSerah;
	}

	public String getJenisSerah() {
		return this.jenisSerah;
	}

	public void setJenisSerah(String jenisSerah) {
		this.jenisSerah = jenisSerah;
	}

	public String getStatusAkuanSumpah() {
		return this.statusAkuanSumpah;
	}

	public void setStatusAkuanSumpah(String statusAkuanSumpah) {
		this.statusAkuanSumpah = statusAkuanSumpah;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public String getNamaPenghantarNotis() {
		return this.namaPenghantarNotis;
	}

	public void setNamaPenghantarNotis(String namaPenghantarNotis) {
		this.namaPenghantarNotis = namaPenghantarNotis;
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

	public Set getTblppknotisperbicaraans() {
		return this.tblppknotisperbicaraans;
	}

	public void setTblppknotisperbicaraans(Set tblppknotisperbicaraans) {
		this.tblppknotisperbicaraans = tblppknotisperbicaraans;
	}

	public Set getTblppknotisobdtls() {
		return this.tblppknotisobdtls;
	}

	public void setTblppknotisobdtls(Set tblppknotisobdtls) {
		this.tblppknotisobdtls = tblppknotisobdtls;
	}

	public Set getTblppknotiskolaterals() {
		return this.tblppknotiskolaterals;
	}

	public void setTblppknotiskolaterals(Set tblppknotiskolaterals) {
		this.tblppknotiskolaterals = tblppknotiskolaterals;
	}

}