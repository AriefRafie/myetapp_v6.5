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
import etapp.entity.rujukan.Mukim;
import etapp.entity.rujukan.Negeri;

@Entity
@Table(name = "TBLHTPCUKAIUTAMA")
public class CukaiUtama {
	@Id @Column(name="ID_CUKAIUTAMA")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERINGKATBAYARAN")
	private PeringkatBayaran peringkatBayaran;
	@ManyToOne @JoinColumn(name="ID_NEGERI")
	private Negeri negeri;
	@ManyToOne @JoinColumn(name="ID_DAERAH")
	private Daerah daerah;
	@ManyToOne @JoinColumn(name="ID_MUKIM")
	private Mukim mukim;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="cukaiUtama")
	private List<Baucer> listBaucer;

	@Column(name="JUMLAH_CUKAI")
	private double jumlahCukai;
	@Column(name="JUMLAH_HAKMILIK")
	private double jumlahHakmilik;
	@Column(name="AMOUNT_BAYARAN_CUKAI")
	private double jumlahBayaranCukai;
	
	@Column(name="TAHUN")
	private String tahun;
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
	public Mukim getMukim() {
		return mukim;
	}
	public void setMukim(Mukim mukim) {
		this.mukim = mukim;
	}
	public double getJumlahCukai() {
		return jumlahCukai;
	}
	public void setJumlahCukai(double jumlahCukai) {
		this.jumlahCukai = jumlahCukai;
	}
	public double getJumlahHakmilik() {
		return jumlahHakmilik;
	}
	public void setJumlahHakmilik(double jumlahHakmilik) {
		this.jumlahHakmilik = jumlahHakmilik;
	}
	public double getJumlahBayaranCukai() {
		return jumlahBayaranCukai;
	}
	public void setJumlahBayaranCukai(double jumlahBayaranCukai) {
		this.jumlahBayaranCukai = jumlahBayaranCukai;
	}
	public String getTahun() {
		return tahun;
	}
	public void setTahun(String tahun) {
		this.tahun = tahun;
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
	public List<Baucer> getListBaucer() {
		return listBaucer;
	}
	public void setListBaucer(List<Baucer> listBaucer) {
		this.listBaucer = listBaucer;
	}

	//@Transient
	//private String tempohTanah;

	
}
