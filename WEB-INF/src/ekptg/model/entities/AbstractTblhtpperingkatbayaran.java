package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblhtpperingkatbayaran entity provides the base persistence
 * definition of the Tblhtpperingkatbayaran entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpperingkatbayaran implements
		java.io.Serializable {

	// Fields

	private Long idPeringkatbayaran;
	private Tblhtppermohonan tblhtppermohonan;
	private String peringkatBayaran;
	private String tahunCukai;
	private Long idPegawai;
	private Long idNegeri;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblhtpbayarancukais = new HashSet(0);
	private Set tblhtpcukaiutamas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblhtpperingkatbayaran() {
	}

	/** minimal constructor */
	public AbstractTblhtpperingkatbayaran(Long idPeringkatbayaran,
			Tblhtppermohonan tblhtppermohonan, String peringkatBayaran,
			Long idNegeri) {
		this.idPeringkatbayaran = idPeringkatbayaran;
		this.tblhtppermohonan = tblhtppermohonan;
		this.peringkatBayaran = peringkatBayaran;
		this.idNegeri = idNegeri;
	}

	/** full constructor */
	public AbstractTblhtpperingkatbayaran(Long idPeringkatbayaran,
			Tblhtppermohonan tblhtppermohonan, String peringkatBayaran,
			String tahunCukai, Long idPegawai, Long idNegeri, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblhtpbayarancukais, Set tblhtpcukaiutamas) {
		this.idPeringkatbayaran = idPeringkatbayaran;
		this.tblhtppermohonan = tblhtppermohonan;
		this.peringkatBayaran = peringkatBayaran;
		this.tahunCukai = tahunCukai;
		this.idPegawai = idPegawai;
		this.idNegeri = idNegeri;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblhtpbayarancukais = tblhtpbayarancukais;
		this.tblhtpcukaiutamas = tblhtpcukaiutamas;
	}

	// Property accessors

	public Long getIdPeringkatbayaran() {
		return this.idPeringkatbayaran;
	}

	public void setIdPeringkatbayaran(Long idPeringkatbayaran) {
		this.idPeringkatbayaran = idPeringkatbayaran;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

	public String getPeringkatBayaran() {
		return this.peringkatBayaran;
	}

	public void setPeringkatBayaran(String peringkatBayaran) {
		this.peringkatBayaran = peringkatBayaran;
	}

	public String getTahunCukai() {
		return this.tahunCukai;
	}

	public void setTahunCukai(String tahunCukai) {
		this.tahunCukai = tahunCukai;
	}

	public Long getIdPegawai() {
		return this.idPegawai;
	}

	public void setIdPegawai(Long idPegawai) {
		this.idPegawai = idPegawai;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
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

	public Set getTblhtpbayarancukais() {
		return this.tblhtpbayarancukais;
	}

	public void setTblhtpbayarancukais(Set tblhtpbayarancukais) {
		this.tblhtpbayarancukais = tblhtpbayarancukais;
	}

	public Set getTblhtpcukaiutamas() {
		return this.tblhtpcukaiutamas;
	}

	public void setTblhtpcukaiutamas(Set tblhtpcukaiutamas) {
		this.tblhtpcukaiutamas = tblhtpcukaiutamas;
	}

}