package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblppkrayuan entity provides the base persistence definition of the
 * Tblppkrayuan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkrayuan implements java.io.Serializable {

	// Fields

	private Long idRayuan;
	private Tblrujppkjenisperintah tblrujppkjenisperintah;
	private Tblppkpermohonan tblppkpermohonan;
	private Tblrujppkunit tblrujppkunit;
	private Date tarikhMohon;
	private String maklumatPerayu;
	private String perkaraRayu;
	private Long idNegeri;
	private Long idDaerah;
	private Long idNegeriunitpsk;
	private String keputusan;
	private String item01;
	private String item02;
	private String item03;
	private String item04;
	private String item05;
	private String item06;
	private String item07;
	private String item08;
	private String item09;
	private String catatan;
	private Long idMahkamah;
	private Long idNegerimah;
	private String namaPuu;
	private String alamatPuu1;
	private String alamatPuu2;
	private String alamatPuu3;
	private String bandar;
	private String poskod;
	private Long idNegeripuu;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppkperayus = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblppkrayuan() {
	}

	/** minimal constructor */
	public AbstractTblppkrayuan(Long idRayuan, Tblppkpermohonan tblppkpermohonan) {
		this.idRayuan = idRayuan;
		this.tblppkpermohonan = tblppkpermohonan;
	}

	/** full constructor */
	public AbstractTblppkrayuan(Long idRayuan,
			Tblrujppkjenisperintah tblrujppkjenisperintah,
			Tblppkpermohonan tblppkpermohonan, Tblrujppkunit tblrujppkunit,
			Date tarikhMohon, String maklumatPerayu, String perkaraRayu,
			Long idNegeri, Long idDaerah, Long idNegeriunitpsk,
			String keputusan, String item01, String item02, String item03,
			String item04, String item05, String item06, String item07,
			String item08, String item09, String catatan, Long idMahkamah,
			Long idNegerimah, String namaPuu, String alamatPuu1,
			String alamatPuu2, String alamatPuu3, String bandar, String poskod,
			Long idNegeripuu, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblppkperayus) {
		this.idRayuan = idRayuan;
		this.tblrujppkjenisperintah = tblrujppkjenisperintah;
		this.tblppkpermohonan = tblppkpermohonan;
		this.tblrujppkunit = tblrujppkunit;
		this.tarikhMohon = tarikhMohon;
		this.maklumatPerayu = maklumatPerayu;
		this.perkaraRayu = perkaraRayu;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
		this.idNegeriunitpsk = idNegeriunitpsk;
		this.keputusan = keputusan;
		this.item01 = item01;
		this.item02 = item02;
		this.item03 = item03;
		this.item04 = item04;
		this.item05 = item05;
		this.item06 = item06;
		this.item07 = item07;
		this.item08 = item08;
		this.item09 = item09;
		this.catatan = catatan;
		this.idMahkamah = idMahkamah;
		this.idNegerimah = idNegerimah;
		this.namaPuu = namaPuu;
		this.alamatPuu1 = alamatPuu1;
		this.alamatPuu2 = alamatPuu2;
		this.alamatPuu3 = alamatPuu3;
		this.bandar = bandar;
		this.poskod = poskod;
		this.idNegeripuu = idNegeripuu;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppkperayus = tblppkperayus;
	}

	// Property accessors

	public Long getIdRayuan() {
		return this.idRayuan;
	}

	public void setIdRayuan(Long idRayuan) {
		this.idRayuan = idRayuan;
	}

	public Tblrujppkjenisperintah getTblrujppkjenisperintah() {
		return this.tblrujppkjenisperintah;
	}

	public void setTblrujppkjenisperintah(
			Tblrujppkjenisperintah tblrujppkjenisperintah) {
		this.tblrujppkjenisperintah = tblrujppkjenisperintah;
	}

	public Tblppkpermohonan getTblppkpermohonan() {
		return this.tblppkpermohonan;
	}

	public void setTblppkpermohonan(Tblppkpermohonan tblppkpermohonan) {
		this.tblppkpermohonan = tblppkpermohonan;
	}

	public Tblrujppkunit getTblrujppkunit() {
		return this.tblrujppkunit;
	}

	public void setTblrujppkunit(Tblrujppkunit tblrujppkunit) {
		this.tblrujppkunit = tblrujppkunit;
	}

	public Date getTarikhMohon() {
		return this.tarikhMohon;
	}

	public void setTarikhMohon(Date tarikhMohon) {
		this.tarikhMohon = tarikhMohon;
	}

	public String getMaklumatPerayu() {
		return this.maklumatPerayu;
	}

	public void setMaklumatPerayu(String maklumatPerayu) {
		this.maklumatPerayu = maklumatPerayu;
	}

	public String getPerkaraRayu() {
		return this.perkaraRayu;
	}

	public void setPerkaraRayu(String perkaraRayu) {
		this.perkaraRayu = perkaraRayu;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public Long getIdDaerah() {
		return this.idDaerah;
	}

	public void setIdDaerah(Long idDaerah) {
		this.idDaerah = idDaerah;
	}

	public Long getIdNegeriunitpsk() {
		return this.idNegeriunitpsk;
	}

	public void setIdNegeriunitpsk(Long idNegeriunitpsk) {
		this.idNegeriunitpsk = idNegeriunitpsk;
	}

	public String getKeputusan() {
		return this.keputusan;
	}

	public void setKeputusan(String keputusan) {
		this.keputusan = keputusan;
	}

	public String getItem01() {
		return this.item01;
	}

	public void setItem01(String item01) {
		this.item01 = item01;
	}

	public String getItem02() {
		return this.item02;
	}

	public void setItem02(String item02) {
		this.item02 = item02;
	}

	public String getItem03() {
		return this.item03;
	}

	public void setItem03(String item03) {
		this.item03 = item03;
	}

	public String getItem04() {
		return this.item04;
	}

	public void setItem04(String item04) {
		this.item04 = item04;
	}

	public String getItem05() {
		return this.item05;
	}

	public void setItem05(String item05) {
		this.item05 = item05;
	}

	public String getItem06() {
		return this.item06;
	}

	public void setItem06(String item06) {
		this.item06 = item06;
	}

	public String getItem07() {
		return this.item07;
	}

	public void setItem07(String item07) {
		this.item07 = item07;
	}

	public String getItem08() {
		return this.item08;
	}

	public void setItem08(String item08) {
		this.item08 = item08;
	}

	public String getItem09() {
		return this.item09;
	}

	public void setItem09(String item09) {
		this.item09 = item09;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public Long getIdMahkamah() {
		return this.idMahkamah;
	}

	public void setIdMahkamah(Long idMahkamah) {
		this.idMahkamah = idMahkamah;
	}

	public Long getIdNegerimah() {
		return this.idNegerimah;
	}

	public void setIdNegerimah(Long idNegerimah) {
		this.idNegerimah = idNegerimah;
	}

	public String getNamaPuu() {
		return this.namaPuu;
	}

	public void setNamaPuu(String namaPuu) {
		this.namaPuu = namaPuu;
	}

	public String getAlamatPuu1() {
		return this.alamatPuu1;
	}

	public void setAlamatPuu1(String alamatPuu1) {
		this.alamatPuu1 = alamatPuu1;
	}

	public String getAlamatPuu2() {
		return this.alamatPuu2;
	}

	public void setAlamatPuu2(String alamatPuu2) {
		this.alamatPuu2 = alamatPuu2;
	}

	public String getAlamatPuu3() {
		return this.alamatPuu3;
	}

	public void setAlamatPuu3(String alamatPuu3) {
		this.alamatPuu3 = alamatPuu3;
	}

	public String getBandar() {
		return this.bandar;
	}

	public void setBandar(String bandar) {
		this.bandar = bandar;
	}

	public String getPoskod() {
		return this.poskod;
	}

	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}

	public Long getIdNegeripuu() {
		return this.idNegeripuu;
	}

	public void setIdNegeripuu(Long idNegeripuu) {
		this.idNegeripuu = idNegeripuu;
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

	public Set getTblppkperayus() {
		return this.tblppkperayus;
	}

	public void setTblppkperayus(Set tblppkperayus) {
		this.tblppkperayus = tblppkperayus;
	}

}