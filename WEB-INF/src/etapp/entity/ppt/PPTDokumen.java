package etapp.entity.ppt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lebah.template.UID;

@Entity
@Table(name = "TBLPPTDOKUMEN")
public class PPTDokumen {

	@Id
	@Column(name="ID_DOKUMEN")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private PPTPermohonan permohonan;
	
	@Column(name="JENIS_MIME")
	private String jenisMime;
	@Column(name="KETERANGAN")
	private String keterangan;
	@Column(name="NAMA_FAIL")
	private String namaFail;
	@Column(name="NO_RUJUKAN")
	private String nomborRujukan;
	@Column(name="TAJUK")
	private String tajuk;
    @Lob
    @Column(name="CONTENT")
    private byte[] content;
    
	
	public PPTDokumen() {
		setId(UID.get());
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public PPTPermohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(PPTPermohonan permohonan) {
		this.permohonan = permohonan;
	}
	public String getJenisMime() {
		return jenisMime;
	}
	public void setJenisMime(String jenisMime) {
		this.jenisMime = jenisMime;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	public String getNamaFail() {
		return namaFail;
	}
	public void setNamaFail(String namaFail) {
		this.namaFail = namaFail;
	}
	public String getNomborRujukan() {
		return nomborRujukan;
	}
	public void setNomborRujukan(String nomborRujukan) {
		this.nomborRujukan = nomborRujukan;
	}
	public String getTajuk() {
		return tajuk;
	}
	public void setTajuk(String tajuk) {
		this.tajuk = tajuk;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	
	
}
