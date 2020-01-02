package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblrujpegawai entity provides the base persistence definition of the
 * Tblrujpegawai entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujpegawai implements java.io.Serializable {

	// Fields

	private Long idPegawai;
	private Long idNegeri;
	private Long idMukim;
	private String namaPegawai;
	private String noPekerja;
	private String noKp;
	private String jawatan;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private Long poskod;
	private String noTelPejabat;
	private String noTelRumah;
	private String noTelBimbit;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
        private Long idSeksyen;
        private String emel;
	private Set tblonlineaduans = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblrujpegawai() {
	}

	/** minimal constructor */
	public AbstractTblrujpegawai(Long idPegawai) {
		this.idPegawai = idPegawai;
	}

	/** full constructor */
	public AbstractTblrujpegawai(Long idPegawai,
			Long idNegeri, Long idMukim,
			String namaPegawai, String noPekerja, String noKp, String jawatan,
			String alamat1, String alamat2, String alamat3, Long poskod,
			String noTelPejabat, String noTelRumah, String noTelBimbit,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idSeksyen, String emel,Set tblonlineaduans) {
		this.idPegawai = idPegawai;
		this.idNegeri = idNegeri;
		this.idMukim = idMukim;
		this.namaPegawai = namaPegawai;
		this.noPekerja = noPekerja;
		this.noKp = noKp;
		this.jawatan = jawatan;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.noTelPejabat = noTelPejabat;
		this.noTelRumah = noTelRumah;
		this.noTelBimbit = noTelBimbit;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
                this.idSeksyen = idSeksyen;
		this.tarikhKemaskini = tarikhKemaskini;
                this.emel = emel;
		this.tblonlineaduans = tblonlineaduans;
	}

	// Property accessors

	public Long getIdPegawai() {
		return this.idPegawai;
	}

	public void setIdPegawai(Long idPegawai) {
		this.idPegawai = idPegawai;
	}

	public String getNamaPegawai() {
		return this.namaPegawai;
	}

	public void setNamaPegawai(String namaPegawai) {
		this.namaPegawai = namaPegawai;
	}

	public String getNoPekerja() {
		return this.noPekerja;
	}

	public void setNoPekerja(String noPekerja) {
		this.noPekerja = noPekerja;
	}

	public String getNoKp() {
		return this.noKp;
	}

	public void setNoKp(String noKp) {
		this.noKp = noKp;
	}

	public String getJawatan() {
		return this.jawatan;
	}

	public void setJawatan(String jawatan) {
		this.jawatan = jawatan;
	}

	public String getAlamat1() {
		return this.alamat1;
	}

	public void setAlamat1(String alamat1) {
		this.alamat1 = alamat1;
	}

	public String getAlamat2() {
		return this.alamat2;
	}

	public void setAlamat2(String alamat2) {
		this.alamat2 = alamat2;
	}

	public String getAlamat3() {
		return this.alamat3;
	}

	public void setAlamat3(String alamat3) {
		this.alamat3 = alamat3;
	}

	public Long getPoskod() {
		return this.poskod;
	}

	public void setPoskod(Long poskod) {
		this.poskod = poskod;
	}

	public String getNoTelPejabat() {
		return this.noTelPejabat;
	}

	public void setNoTelPejabat(String noTelPejabat) {
		this.noTelPejabat = noTelPejabat;
	}

	public String getNoTelRumah() {
		return this.noTelRumah;
	}

	public void setNoTelRumah(String noTelRumah) {
		this.noTelRumah = noTelRumah;
	}

	public String getNoTelBimbit() {
		return this.noTelBimbit;
	}

	public void setNoTelBimbit(String noTelBimbit) {
		this.noTelBimbit = noTelBimbit;
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
        public Long getIdSeksyen() {
                return this.idSeksyen;
        }
    
        public void setIdSeksyen(Long idSeksyen) {
                this.idSeksyen = idSeksyen;
        }
        public String getEmel() {
                return this.emel;
        }
    
        public void setEmel(String emel) {
                this.emel = emel;
        }
	public Set getTblonlineaduans() {
		return this.tblonlineaduans;
	}

	public void setTblonlineaduans(Set tblonlineaduans) {
		this.tblonlineaduans = tblonlineaduans;
	}
    
        public void setIdNegeri(Long idNegeri) {
            this.idNegeri = idNegeri;
        }
    
        public Long getIdNegeri() {
            return idNegeri;
        }
    
        public void setIdMukim(Long idMukim) {
            this.idMukim = idMukim;
        }
    
        public Long getIdMukim() {
            return idMukim;
        }
}
