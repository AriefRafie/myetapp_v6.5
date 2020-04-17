package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpbayaranId entity provides the base persistence definition of
 * the TblhtpbayaranId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpbayaranId implements java.io.Serializable {

	// Fields

	private Long idBayaran;
	private Tblhtppermohonan tblhtppermohonan;
	private String noResit;
	private Date tarikhResit;
	private Double amaunAtasResit;
	private Date tarikhHantarResit;
	private String noBayaran;
	private Double jumlahBayaran;
	private Date tarikhTerima;
	private String noBaucer;
	private Date tarikhBaucer;
	private Double amaunAtasBaucer;
	private String tujuanBayaran;
	private String noUrusan;
	private Date tarikhCek;
	private String namaBank;
	private Date tarikhLuput;
	private String statusBayar;
	private Date tkhMula;
	private Long idCarabayar;
	private Long idMasuk;
	private Date tarikhMasuk;
	private String idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpbayaranId() {
	}

	/** minimal constructor */
	public AbstractTblhtpbayaranId(Long idBayaran,
			Tblhtppermohonan tblhtppermohonan, Long idCarabayar, Long idMasuk) {
		this.idBayaran = idBayaran;
		this.tblhtppermohonan = tblhtppermohonan;
		this.idCarabayar = idCarabayar;
		this.idMasuk = idMasuk;
	}

	/** full constructor */
	public AbstractTblhtpbayaranId(Long idBayaran,
			Tblhtppermohonan tblhtppermohonan, String noResit,
			Date tarikhResit, Double amaunAtasResit, Date tarikhHantarResit,
			String noBayaran, Double jumlahBayaran, Date tarikhTerima,
			String noBaucer, Date tarikhBaucer, Double amaunAtasBaucer,
			String tujuanBayaran, String noUrusan, Date tarikhCek,
			String namaBank, Date tarikhLuput, String statusBayar,
			Date tkhMula, Long idCarabayar, Long idMasuk, Date tarikhMasuk,
			String idKemaskini, Date tarikhKemaskini) {
		this.idBayaran = idBayaran;
		this.tblhtppermohonan = tblhtppermohonan;
		this.noResit = noResit;
		this.tarikhResit = tarikhResit;
		this.amaunAtasResit = amaunAtasResit;
		this.tarikhHantarResit = tarikhHantarResit;
		this.noBayaran = noBayaran;
		this.jumlahBayaran = jumlahBayaran;
		this.tarikhTerima = tarikhTerima;
		this.noBaucer = noBaucer;
		this.tarikhBaucer = tarikhBaucer;
		this.amaunAtasBaucer = amaunAtasBaucer;
		this.tujuanBayaran = tujuanBayaran;
		this.noUrusan = noUrusan;
		this.tarikhCek = tarikhCek;
		this.namaBank = namaBank;
		this.tarikhLuput = tarikhLuput;
		this.statusBayar = statusBayar;
		this.tkhMula = tkhMula;
		this.idCarabayar = idCarabayar;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdBayaran() {
		return this.idBayaran;
	}

	public void setIdBayaran(Long idBayaran) {
		this.idBayaran = idBayaran;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

	public String getNoResit() {
		return this.noResit;
	}

	public void setNoResit(String noResit) {
		this.noResit = noResit;
	}

	public Date getTarikhResit() {
		return this.tarikhResit;
	}

	public void setTarikhResit(Date tarikhResit) {
		this.tarikhResit = tarikhResit;
	}

	public Double getAmaunAtasResit() {
		return this.amaunAtasResit;
	}

	public void setAmaunAtasResit(Double amaunAtasResit) {
		this.amaunAtasResit = amaunAtasResit;
	}

	public Date getTarikhHantarResit() {
		return this.tarikhHantarResit;
	}

	public void setTarikhHantarResit(Date tarikhHantarResit) {
		this.tarikhHantarResit = tarikhHantarResit;
	}

	public String getNoBayaran() {
		return this.noBayaran;
	}

	public void setNoBayaran(String noBayaran) {
		this.noBayaran = noBayaran;
	}

	public Double getJumlahBayaran() {
		return this.jumlahBayaran;
	}

	public void setJumlahBayaran(Double jumlahBayaran) {
		this.jumlahBayaran = jumlahBayaran;
	}

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public String getNoBaucer() {
		return this.noBaucer;
	}

	public void setNoBaucer(String noBaucer) {
		this.noBaucer = noBaucer;
	}

	public Date getTarikhBaucer() {
		return this.tarikhBaucer;
	}

	public void setTarikhBaucer(Date tarikhBaucer) {
		this.tarikhBaucer = tarikhBaucer;
	}

	public Double getAmaunAtasBaucer() {
		return this.amaunAtasBaucer;
	}

	public void setAmaunAtasBaucer(Double amaunAtasBaucer) {
		this.amaunAtasBaucer = amaunAtasBaucer;
	}

	public String getTujuanBayaran() {
		return this.tujuanBayaran;
	}

	public void setTujuanBayaran(String tujuanBayaran) {
		this.tujuanBayaran = tujuanBayaran;
	}

	public String getNoUrusan() {
		return this.noUrusan;
	}

	public void setNoUrusan(String noUrusan) {
		this.noUrusan = noUrusan;
	}

	public Date getTarikhCek() {
		return this.tarikhCek;
	}

	public void setTarikhCek(Date tarikhCek) {
		this.tarikhCek = tarikhCek;
	}

	public String getNamaBank() {
		return this.namaBank;
	}

	public void setNamaBank(String namaBank) {
		this.namaBank = namaBank;
	}

	public Date getTarikhLuput() {
		return this.tarikhLuput;
	}

	public void setTarikhLuput(Date tarikhLuput) {
		this.tarikhLuput = tarikhLuput;
	}

	public String getStatusBayar() {
		return this.statusBayar;
	}

	public void setStatusBayar(String statusBayar) {
		this.statusBayar = statusBayar;
	}

	public Date getTkhMula() {
		return this.tkhMula;
	}

	public void setTkhMula(Date tkhMula) {
		this.tkhMula = tkhMula;
	}

	public Long getIdCarabayar() {
		return this.idCarabayar;
	}

	public void setIdCarabayar(Long idCarabayar) {
		this.idCarabayar = idCarabayar;
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

	public String getIdKemaskini() {
		return this.idKemaskini;
	}

	public void setIdKemaskini(String idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public Date getTarikhKemaskini() {
		return this.tarikhKemaskini;
	}

	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractTblhtpbayaranId))
			return false;
		AbstractTblhtpbayaranId castOther = (AbstractTblhtpbayaranId) other;

		return ((this.getIdBayaran() == castOther.getIdBayaran()) || (this
				.getIdBayaran() != null
				&& castOther.getIdBayaran() != null && this.getIdBayaran()
				.equals(castOther.getIdBayaran())))
				&& ((this.getTblhtppermohonan() == castOther
						.getTblhtppermohonan()) || (this.getTblhtppermohonan() != null
						&& castOther.getTblhtppermohonan() != null && this
						.getTblhtppermohonan().equals(
								castOther.getTblhtppermohonan())))
				&& ((this.getNoResit() == castOther.getNoResit()) || (this
						.getNoResit() != null
						&& castOther.getNoResit() != null && this.getNoResit()
						.equals(castOther.getNoResit())))
				&& ((this.getTarikhResit() == castOther.getTarikhResit()) || (this
						.getTarikhResit() != null
						&& castOther.getTarikhResit() != null && this
						.getTarikhResit().equals(castOther.getTarikhResit())))
				&& ((this.getAmaunAtasResit() == castOther.getAmaunAtasResit()) || (this
						.getAmaunAtasResit() != null
						&& castOther.getAmaunAtasResit() != null && this
						.getAmaunAtasResit().equals(
								castOther.getAmaunAtasResit())))
				&& ((this.getTarikhHantarResit() == castOther
						.getTarikhHantarResit()) || (this
						.getTarikhHantarResit() != null
						&& castOther.getTarikhHantarResit() != null && this
						.getTarikhHantarResit().equals(
								castOther.getTarikhHantarResit())))
				&& ((this.getNoBayaran() == castOther.getNoBayaran()) || (this
						.getNoBayaran() != null
						&& castOther.getNoBayaran() != null && this
						.getNoBayaran().equals(castOther.getNoBayaran())))
				&& ((this.getJumlahBayaran() == castOther.getJumlahBayaran()) || (this
						.getJumlahBayaran() != null
						&& castOther.getJumlahBayaran() != null && this
						.getJumlahBayaran()
						.equals(castOther.getJumlahBayaran())))
				&& ((this.getTarikhTerima() == castOther.getTarikhTerima()) || (this
						.getTarikhTerima() != null
						&& castOther.getTarikhTerima() != null && this
						.getTarikhTerima().equals(castOther.getTarikhTerima())))
				&& ((this.getNoBaucer() == castOther.getNoBaucer()) || (this
						.getNoBaucer() != null
						&& castOther.getNoBaucer() != null && this
						.getNoBaucer().equals(castOther.getNoBaucer())))
				&& ((this.getTarikhBaucer() == castOther.getTarikhBaucer()) || (this
						.getTarikhBaucer() != null
						&& castOther.getTarikhBaucer() != null && this
						.getTarikhBaucer().equals(castOther.getTarikhBaucer())))
				&& ((this.getAmaunAtasBaucer() == castOther
						.getAmaunAtasBaucer()) || (this.getAmaunAtasBaucer() != null
						&& castOther.getAmaunAtasBaucer() != null && this
						.getAmaunAtasBaucer().equals(
								castOther.getAmaunAtasBaucer())))
				&& ((this.getTujuanBayaran() == castOther.getTujuanBayaran()) || (this
						.getTujuanBayaran() != null
						&& castOther.getTujuanBayaran() != null && this
						.getTujuanBayaran()
						.equals(castOther.getTujuanBayaran())))
				&& ((this.getNoUrusan() == castOther.getNoUrusan()) || (this
						.getNoUrusan() != null
						&& castOther.getNoUrusan() != null && this
						.getNoUrusan().equals(castOther.getNoUrusan())))
				&& ((this.getTarikhCek() == castOther.getTarikhCek()) || (this
						.getTarikhCek() != null
						&& castOther.getTarikhCek() != null && this
						.getTarikhCek().equals(castOther.getTarikhCek())))
				&& ((this.getNamaBank() == castOther.getNamaBank()) || (this
						.getNamaBank() != null
						&& castOther.getNamaBank() != null && this
						.getNamaBank().equals(castOther.getNamaBank())))
				&& ((this.getTarikhLuput() == castOther.getTarikhLuput()) || (this
						.getTarikhLuput() != null
						&& castOther.getTarikhLuput() != null && this
						.getTarikhLuput().equals(castOther.getTarikhLuput())))
				&& ((this.getStatusBayar() == castOther.getStatusBayar()) || (this
						.getStatusBayar() != null
						&& castOther.getStatusBayar() != null && this
						.getStatusBayar().equals(castOther.getStatusBayar())))
				&& ((this.getTkhMula() == castOther.getTkhMula()) || (this
						.getTkhMula() != null
						&& castOther.getTkhMula() != null && this.getTkhMula()
						.equals(castOther.getTkhMula())))
				&& ((this.getIdCarabayar() == castOther.getIdCarabayar()) || (this
						.getIdCarabayar() != null
						&& castOther.getIdCarabayar() != null && this
						.getIdCarabayar().equals(castOther.getIdCarabayar())))
				&& ((this.getIdMasuk() == castOther.getIdMasuk()) || (this
						.getIdMasuk() != null
						&& castOther.getIdMasuk() != null && this.getIdMasuk()
						.equals(castOther.getIdMasuk())))
				&& ((this.getTarikhMasuk() == castOther.getTarikhMasuk()) || (this
						.getTarikhMasuk() != null
						&& castOther.getTarikhMasuk() != null && this
						.getTarikhMasuk().equals(castOther.getTarikhMasuk())))
				&& ((this.getIdKemaskini() == castOther.getIdKemaskini()) || (this
						.getIdKemaskini() != null
						&& castOther.getIdKemaskini() != null && this
						.getIdKemaskini().equals(castOther.getIdKemaskini())))
				&& ((this.getTarikhKemaskini() == castOther
						.getTarikhKemaskini()) || (this.getTarikhKemaskini() != null
						&& castOther.getTarikhKemaskini() != null && this
						.getTarikhKemaskini().equals(
								castOther.getTarikhKemaskini())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIdBayaran() == null ? 0 : this.getIdBayaran().hashCode());
		result = 37
				* result
				+ (getTblhtppermohonan() == null ? 0 : this
						.getTblhtppermohonan().hashCode());
		result = 37 * result
				+ (getNoResit() == null ? 0 : this.getNoResit().hashCode());
		result = 37
				* result
				+ (getTarikhResit() == null ? 0 : this.getTarikhResit()
						.hashCode());
		result = 37
				* result
				+ (getAmaunAtasResit() == null ? 0 : this.getAmaunAtasResit()
						.hashCode());
		result = 37
				* result
				+ (getTarikhHantarResit() == null ? 0 : this
						.getTarikhHantarResit().hashCode());
		result = 37 * result
				+ (getNoBayaran() == null ? 0 : this.getNoBayaran().hashCode());
		result = 37
				* result
				+ (getJumlahBayaran() == null ? 0 : this.getJumlahBayaran()
						.hashCode());
		result = 37
				* result
				+ (getTarikhTerima() == null ? 0 : this.getTarikhTerima()
						.hashCode());
		result = 37 * result
				+ (getNoBaucer() == null ? 0 : this.getNoBaucer().hashCode());
		result = 37
				* result
				+ (getTarikhBaucer() == null ? 0 : this.getTarikhBaucer()
						.hashCode());
		result = 37
				* result
				+ (getAmaunAtasBaucer() == null ? 0 : this.getAmaunAtasBaucer()
						.hashCode());
		result = 37
				* result
				+ (getTujuanBayaran() == null ? 0 : this.getTujuanBayaran()
						.hashCode());
		result = 37 * result
				+ (getNoUrusan() == null ? 0 : this.getNoUrusan().hashCode());
		result = 37 * result
				+ (getTarikhCek() == null ? 0 : this.getTarikhCek().hashCode());
		result = 37 * result
				+ (getNamaBank() == null ? 0 : this.getNamaBank().hashCode());
		result = 37
				* result
				+ (getTarikhLuput() == null ? 0 : this.getTarikhLuput()
						.hashCode());
		result = 37
				* result
				+ (getStatusBayar() == null ? 0 : this.getStatusBayar()
						.hashCode());
		result = 37 * result
				+ (getTkhMula() == null ? 0 : this.getTkhMula().hashCode());
		result = 37
				* result
				+ (getIdCarabayar() == null ? 0 : this.getIdCarabayar()
						.hashCode());
		result = 37 * result
				+ (getIdMasuk() == null ? 0 : this.getIdMasuk().hashCode());
		result = 37
				* result
				+ (getTarikhMasuk() == null ? 0 : this.getTarikhMasuk()
						.hashCode());
		result = 37
				* result
				+ (getIdKemaskini() == null ? 0 : this.getIdKemaskini()
						.hashCode());
		result = 37
				* result
				+ (getTarikhKemaskini() == null ? 0 : this.getTarikhKemaskini()
						.hashCode());
		return result;
	}

}