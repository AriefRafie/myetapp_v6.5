package etapp.entity.htp.cukai;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import etapp.entity.rujukan.Daerah;
import etapp.entity.rujukan.Negeri;

@Entity
@Table(name = "TBLHTPBAUCER")
public class Baucer {
	@Id @Column(name="ID_BAUCER")
	private long id;
	@ManyToOne @JoinColumn(name="ID_CUKAIUTAMA")
	private CukaiUtama cukaiUtama;
	@ManyToOne @JoinColumn(name="ID_PERINGKATBAYARAN")
	private PeringkatBayaran peringkatBayaran;

	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="baucer")
	private List<BayaranCukai> listBayaranCukai;

	@ManyToOne @JoinColumn(name="ID_NEGERI")
	private Negeri negeri;
	@ManyToOne @JoinColumn(name="ID_DAERAH")
	private Daerah daerah;

	@Column(name="TAHUN")
	private String tahun;
	@Column(name="NO_BAUCER")
	private String noBaucer;
	@Column(name="AMAUN_BAUCER")
	private double jumlahBaucer;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_BAUCER")
	private Date tarikhBaucer;
	@Column(name="ID_BANK")
	private Long idBank;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_BAYARAN")
	private Date tarikhBayaran;
	@Column(name="NO_RESIT")
	private String noResit;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_RESIT")
	private Date tarikhResit;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TERIMA_RESIT")
	private Date tarikhTerimaResit;
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
	public CukaiUtama getCukaiUtama() {
		return cukaiUtama;
	}
	public void setCukaiUtama(CukaiUtama cukaiUtama) {
		this.cukaiUtama = cukaiUtama;
	}
	public PeringkatBayaran getPeringkatBayaran() {
		return peringkatBayaran;
	}
	public void setPeringkatBayaran(PeringkatBayaran peringkatBayaran) {
		this.peringkatBayaran = peringkatBayaran;
	}
	public Negeri getNegeri() {
		return negeri;
	}
	public void setNegeri(Negeri negeri) {
		this.negeri = negeri;
	}
	public Daerah getDaerah() {
		return daerah;
	}
	public void setDaerah(Daerah daerah) {
		this.daerah = daerah;
	}
	public String getTahun() {
		return tahun;
	}
	public void setTahun(String tahun) {
		this.tahun = tahun;
	}
	public String getNoBaucer() {
		return noBaucer;
	}
	public void setNoBaucer(String noBaucer) {
		this.noBaucer = noBaucer;
	}
	public double getJumlahBaucer() {
		return jumlahBaucer;
	}
	public void setJumlahBaucer(double jumlahBaucer) {
		this.jumlahBaucer = jumlahBaucer;
	}
	public Date getTarikhBaucer() {
		return tarikhBaucer;
	}
	public void setTarikhBaucer(Date tarikhBaucer) {
		this.tarikhBaucer = tarikhBaucer;
	}
	public Long getIdBank() {
		return idBank;
	}
	public void setIdBank(Long idBank) {
		this.idBank = idBank;
	}
	public Date getTarikhBayaran() {
		return tarikhBayaran;
	}
	public void setTarikhBayaran(Date tarikhBayaran) {
		this.tarikhBayaran = tarikhBayaran;
	}
	public String getNoResit() {
		return noResit;
	}
	public void setNoResit(String noResit) {
		this.noResit = noResit;
	}
	public Date getTarikhResit() {
		return tarikhResit;
	}
	public void setTarikhResit(Date tarikhResit) {
		this.tarikhResit = tarikhResit;
	}
	public Date getTarikhTerimaResit() {
		return tarikhTerimaResit;
	}
	public void setTarikhTerimaResit(Date tarikhTerimaResit) {
		this.tarikhTerimaResit = tarikhTerimaResit;
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
	public List<BayaranCukai> getListBayaranCukai() {
		return listBayaranCukai;
	}
	public void setListBayaranCukai(List<BayaranCukai> listBayaranCukai) {
		this.listBayaranCukai = listBayaranCukai;
	}

	//@Transient
	//private String tempohTanah;

	
}
