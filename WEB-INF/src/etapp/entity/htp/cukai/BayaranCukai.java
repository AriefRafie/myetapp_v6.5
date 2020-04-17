package etapp.entity.htp.cukai;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TBLHTPBAYARANCUKAI")
public class BayaranCukai {
	@Id @Column(name="ID_BAYARANCUKAI")
	private long id;
	@ManyToOne @JoinColumn(name="ID_BAUCER")
	private Baucer baucer;
	@ManyToOne @JoinColumn(name="ID_PERINGKATBAYARAN")
	private PeringkatBayaran peringkatBayaran;

	@Column(name="STATUS")
	private String status;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_BAYARAN")
	private Date tarikhBayaran;	
	@Column(name="NAMA_BANK")
	private String namaBank;
	@Column(name="AMAUN")
	private double jumlah;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TERIMA_BAYARAN")
	private Date tarikhTerimaBayaran;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_HANTAR_PTG")
	private Date tarikhHantar;
	@Column(name="NO_RUJBAYARAN")
	private String noRujukan;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_RESIT")
	private Date tarikhResit;
	@Column(name="ID_MASUK")
	private Long idMasuk;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MASUK")
	private Date tarikhMasuk;
	@Column(name="ID_KEMASKINI")
	private Long idKemaskini;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEMASKINI")
	private Date tarikhKemaskini;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Baucer getBaucer() {
		return baucer;
	}
	public void setBaucer(Baucer baucer) {
		this.baucer = baucer;
	}
	public PeringkatBayaran getPeringkatBayaran() {
		return peringkatBayaran;
	}
	public void setPeringkatBayaran(PeringkatBayaran peringkatBayaran) {
		this.peringkatBayaran = peringkatBayaran;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getTarikhBayaran() {
		return tarikhBayaran;
	}
	public void setTarikhBayaran(Date tarikhBayaran) {
		this.tarikhBayaran = tarikhBayaran;
	}
	public String getNamaBank() {
		return namaBank;
	}
	public void setNamaBank(String namaBank) {
		this.namaBank = namaBank;
	}
	public double getJumlah() {
		return jumlah;
	}
	public void setJumlah(double jumlah) {
		this.jumlah = jumlah;
	}
	public Date getTarikhTerimaBayaran() {
		return tarikhTerimaBayaran;
	}
	public void setTarikhTerimaBayaran(Date tarikhTerimaBayaran) {
		this.tarikhTerimaBayaran = tarikhTerimaBayaran;
	}
	public Date getTarikhHantar() {
		return tarikhHantar;
	}
	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}
	public String getNoRujukan() {
		return noRujukan;
	}
	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
	}
	public Date getTarikhResit() {
		return tarikhResit;
	}
	public void setTarikhResit(Date tarikhResit) {
		this.tarikhResit = tarikhResit;
	}
	public Long getIdMasuk() {
		return idMasuk;
	}
	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}
	public Date getTarikhMasuk() {
		return tarikhMasuk;
	}
	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}
	public Long getIdKemaskini() {
		return idKemaskini;
	}
	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}
	public Date getTarikhKemaskini() {
		return tarikhKemaskini;
	}
	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

	//@Transient
	//private String tempohTanah;

	
}
