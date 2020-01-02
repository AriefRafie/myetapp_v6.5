package ekptg.view.utils;
/**
 * 
 * @author Mohd Nazrul
 *
 */
public class PegawaiInfoDetail {

	private String id_pejabat;
	private String id_unitpsk;
	private String keterangan;
	private String nama_pegawai;
	private String kod_pegawai;
	private String jawatan;
	private String no_tel;
	private String no_tel_sam;

	public PegawaiInfoDetail() {
	}

	public PegawaiInfoDetail(String kod_pegawai,String id_pejabat, String id_unitpsk,
			String keterangan, String nama_pegawai, String jawatan,
			String no_tel, String no_tel_sam) {
		this.id_pejabat = id_pejabat;
		this.id_unitpsk = id_unitpsk;
		this.keterangan = keterangan;
		this.nama_pegawai = nama_pegawai;
		this.kod_pegawai = kod_pegawai;
		this.jawatan = jawatan;
		this.no_tel = no_tel;
		this.no_tel_sam = no_tel_sam;
	}

	public String getId_unitpsk() {
		return id_unitpsk;
	}

	public void setId_unitpsk(String idUnitpsk) {
		id_unitpsk = idUnitpsk;
	}

	public String getNama_pegawai() {
		return nama_pegawai;
	}

	public void setNama_pegawai(String namaPegawai) {
		nama_pegawai = namaPegawai;
	}

	public String getKod_pegawai() {
		return kod_pegawai;
	}

	public void setKod_pegawai(String kodPegawai) {
		kod_pegawai = kodPegawai;
	}
	
	public String getJawatan() {
		return jawatan;
	}

	public void setJawatan(String jawatan) {
		this.jawatan = jawatan;
	}

	public String getNo_tel() {
		return no_tel;
	}

	public void setNo_tel(String noTel) {
		no_tel = noTel;
	}

	public String getNo_tel_sam() {
		return no_tel_sam;
	}

	public void setNo_tel_sam(String noTelSam) {
		no_tel_sam = noTelSam;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getId_pejabat() {
		return id_pejabat;
	}

	public void setId_pejabat(String idPejabat) {
		id_pejabat = idPejabat;
	}

}
