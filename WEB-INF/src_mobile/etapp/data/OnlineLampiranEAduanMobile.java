package etapp.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TBLONLINELAMPIRANADUAN")
public class OnlineLampiranEAduanMobile {

	@Id @Column(name="ID_LAMPIRANADUAN")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="ID_EADUAN")
	private OnlineEAduanMobile eAduan;
	
	@Lob
	@Column(name="CONTENT")
	private byte[] content;	
	@Column(name="JENIS_MIME")
	private String jenisMime;
	@Column(name="NAMA_FAIL")
	private String namaFail;
	@Column(name="TARIKH_MASUK")
	@Temporal(TemporalType.DATE)
	private Date tarikhMasuk;
	
	public OnlineLampiranEAduanMobile() {
		setId(lebah.db.UniqueID.get());
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public OnlineEAduanMobile getEAduan() {
		return eAduan;
	}
	public void setEAduan(OnlineEAduanMobile aduan) {
		eAduan = aduan;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public String getJenisMime() {
		return jenisMime;
	}
	public void setJenisMime(String jenisMime) {
		this.jenisMime = jenisMime;
	}
	public String getNamaFail() {
		return namaFail;
	}
	public void setNamaFail(String namaFail) {
		this.namaFail = namaFail;
	}
	public Date getTarikhMasuk() {
		return tarikhMasuk;
	}
	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}
}