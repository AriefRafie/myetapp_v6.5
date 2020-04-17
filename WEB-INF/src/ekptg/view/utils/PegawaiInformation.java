package ekptg.view.utils;
/**
 * 
 * @author Mohd Nazrul
 *
 */
public class PegawaiInformation {

	private String id_unitpsk;
	private String id_pejabatjkptg;
	private String nama_pegawai;
	private String jawatan;

	public PegawaiInformation() {
	}

	public PegawaiInformation(String id_unitpsk, String id_pejabatjkptg,
			String nama_pegawai, String jawatan) {
		this.id_unitpsk = id_unitpsk;
		this.id_pejabatjkptg = id_pejabatjkptg;
		this.nama_pegawai = nama_pegawai;
		this.jawatan = jawatan;
	}

	public String getId_unitpsk() {
		return id_unitpsk;
	}

	public void setId_unitpsk(String idUnitpsk) {
		id_unitpsk = idUnitpsk;
	}

	public String getId_pejabatjkptg() {
		return id_pejabatjkptg;
	}

	public void setId_pejabatjkptg(String idPejabatjkptg) {
		id_pejabatjkptg = idPejabatjkptg;
	}

	public String getNama_pegawai() {
		return nama_pegawai;
	}

	public void setNama_pegawai(String namaPegawai) {
		nama_pegawai = namaPegawai;
	}

	public String getJawatan() {
		return jawatan;
	}

	public void setJawatan(String jawatan) {
		this.jawatan = jawatan;
	}

}
