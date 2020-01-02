package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblppkborangj entity provides the base persistence definition of the
 * Tblppkborangj entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkborangj implements java.io.Serializable {

	// Fields
	private Long idBorangj;
	private Tblppkperbicaraan tblppkperbicaraan;
	private String jenisRujukan;
	private String sebabTangguh;
	private Date tarikhBicara;
	private Date tarikhMohon;
	private Long idNegerimahkamah;
	private Long idDaerahMahkamah;
	private Long idMahkamah;
	private String catatan1;
	private String catatan2;
	private String catatan3;
	private String catatan4;
	private String catatan5;
	private Date tarikhHantarBorangj;
	private Date tarikhTerimaBorangj;
	private String keputusanMahkamah;
	private String catatanLain;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppkborangjdtls = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblppkborangj() {
	}

	/** minimal constructor */
	public AbstractTblppkborangj(Long idBorangj,
			Tblppkperbicaraan tblppkperbicaraan, String jenisRujukan) {
		this.idBorangj = idBorangj;
		this.tblppkperbicaraan = tblppkperbicaraan;
		this.jenisRujukan = jenisRujukan;
	}

	/** full constructor */
	public AbstractTblppkborangj(Long idBorangj,
			Tblppkperbicaraan tblppkperbicaraan, String jenisRujukan,
			String sebabTangguh, Date tarikhBicara, Date tarikhMohon,
			Long idNegerimahkamah, Long idDaerahMahkamah, Long idMahkamah,
			String catatan1, String catatan2, String catatan3, String catatan4,
			String catatan5, Date tarikhHantarBorangj,
			Date tarikhTerimaBorangj, String keputusanMahkamah,
			String catatanLain, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblppkborangjdtls) {
		this.idBorangj = idBorangj;
		this.tblppkperbicaraan = tblppkperbicaraan;
		this.jenisRujukan = jenisRujukan;
		this.sebabTangguh = sebabTangguh;
		this.tarikhBicara = tarikhBicara;
		this.tarikhMohon = tarikhMohon;
		this.idNegerimahkamah = idNegerimahkamah;
		this.idDaerahMahkamah = idDaerahMahkamah;
		this.idMahkamah = idMahkamah;
		this.catatan1 = catatan1;
		this.catatan2 = catatan2;
		this.catatan3 = catatan3;
		this.catatan4 = catatan4;
		this.catatan5 = catatan5;
		this.tarikhHantarBorangj = tarikhHantarBorangj;
		this.tarikhTerimaBorangj = tarikhTerimaBorangj;
		this.keputusanMahkamah = keputusanMahkamah;
		this.catatanLain = catatanLain;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppkborangjdtls = tblppkborangjdtls;
	}

	// Property accessors

	public Long getIdBorangj() {
		return this.idBorangj;
	}

	public void setIdBorangj(Long idBorangj) {
		this.idBorangj = idBorangj;
	}

	public Tblppkperbicaraan getTblppkperbicaraan() {
		return this.tblppkperbicaraan;
	}

	public void setTblppkperbicaraan(Tblppkperbicaraan tblppkperbicaraan) {
		this.tblppkperbicaraan = tblppkperbicaraan;
	}

	public String getJenisRujukan() {
		return this.jenisRujukan;
	}

	public void setJenisRujukan(String jenisRujukan) {
		this.jenisRujukan = jenisRujukan;
	}

	public String getSebabTangguh() {
		return this.sebabTangguh;
	}

	public void setSebabTangguh(String sebabTangguh) {
		this.sebabTangguh = sebabTangguh;
	}

	public Date getTarikhBicara() {
		return this.tarikhBicara;
	}

	public void setTarikhBicara(Date tarikhBicara) {
		this.tarikhBicara = tarikhBicara;
	}

	public Date getTarikhMohon() {
		return this.tarikhMohon;
	}

	public void setTarikhMohon(Date tarikhMohon) {
		this.tarikhMohon = tarikhMohon;
	}

	public Long getIdNegerimahkamah() {
		return this.idNegerimahkamah;
	}

	public void setIdNegerimahkamah(Long idNegerimahkamah) {
		this.idNegerimahkamah = idNegerimahkamah;
	}

	public Long getIdDaerahMahkamah() {
		return this.idDaerahMahkamah;
	}

	public void setIdDaerahMahkamah(Long idDaerahMahkamah) {
		this.idDaerahMahkamah = idDaerahMahkamah;
	}

	public Long getIdMahkamah() {
		return this.idMahkamah;
	}

	public void setIdMahkamah(Long idMahkamah) {
		this.idMahkamah = idMahkamah;
	}

	public String getCatatan1() {
		return this.catatan1;
	}

	public void setCatatan1(String catatan1) {
		this.catatan1 = catatan1;
	}

	public String getCatatan2() {
		return this.catatan2;
	}

	public void setCatatan2(String catatan2) {
		this.catatan2 = catatan2;
	}

	public String getCatatan3() {
		return this.catatan3;
	}

	public void setCatatan3(String catatan3) {
		this.catatan3 = catatan3;
	}

	public String getCatatan4() {
		return this.catatan4;
	}

	public void setCatatan4(String catatan4) {
		this.catatan4 = catatan4;
	}

	public String getCatatan5() {
		return this.catatan5;
	}

	public void setCatatan5(String catatan5) {
		this.catatan5 = catatan5;
	}

	public Date getTarikhHantarBorangj() {
		return this.tarikhHantarBorangj;
	}

	public void setTarikhHantarBorangj(Date tarikhHantarBorangj) {
		this.tarikhHantarBorangj = tarikhHantarBorangj;
	}

	public Date getTarikhTerimaBorangj() {
		return this.tarikhTerimaBorangj;
	}

	public void setTarikhTerimaBorangj(Date tarikhTerimaBorangj) {
		this.tarikhTerimaBorangj = tarikhTerimaBorangj;
	}

	public String getKeputusanMahkamah() {
		return this.keputusanMahkamah;
	}

	public void setKeputusanMahkamah(String keputusanMahkamah) {
		this.keputusanMahkamah = keputusanMahkamah;
	}

	public String getCatatanLain() {
		return this.catatanLain;
	}

	public void setCatatanLain(String catatanLain) {
		this.catatanLain = catatanLain;
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

	public Set getTblppkborangjdtls() {
		return this.tblppkborangjdtls;
	}

	public void setTblppkborangjdtls(Set tblppkborangjdtls) {
		this.tblppkborangjdtls = tblppkborangjdtls;
	}

}