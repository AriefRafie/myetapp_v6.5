package ekptg.view.admin.utils;

public class BandarInformation {

	private String id_bandar;
	private String kod_bandar;
	private String keterangan;
	private String id_negeri;
	private String kod_mampu;
	private String nama_negeri;
	private String id_negara;
	private String kod_negara;
	private String nama_negara;
	
	public BandarInformation () {}
	
	public BandarInformation(
			String id_bandar,
			String kod_bandar,
			String keterangan,
			String id_negeri,
			String kod_mampu,
			String nama_negeri,
			String id_negara,
			String kod_negara,
			String nama_negara
	) {
		this.id_bandar = id_bandar;
		this.kod_bandar = kod_bandar;
		this.keterangan = keterangan;
		this.id_negeri = id_negeri;
		this.kod_mampu = kod_mampu;
		this.nama_negeri = nama_negeri;
		this.id_negara = id_negara;
		this.kod_negara = kod_negara;
		this.nama_negara = nama_negara;
		
	}

	public String getId_bandar() {
		return id_bandar.toUpperCase();
	}

	public void setId_bandar(String idBandar) {
		id_bandar = idBandar;
	}

	public String getKod_bandar() {
		return kod_bandar.toUpperCase();
	}

	public void setKod_bandar(String kodBandar) {
		kod_bandar = kodBandar.toUpperCase();
	}

	public String getKeterangan() {
		return keterangan.toUpperCase();
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan.toUpperCase();
	}

	public String getId_negeri() {
		return id_negeri;
	}

	public void setId_negeri(String idNegeri) {
		id_negeri = idNegeri;
	}

	public String getKod_mampu() {
		return kod_mampu.toUpperCase();
	}

	public void setKod_mampu(String kodMampu) {
		kod_mampu = kodMampu;
	}

	public String getNama_negeri() {
		return nama_negeri.toUpperCase();
	}

	public void setNama_negeri(String namaNegeri) {
		nama_negeri = namaNegeri;
	}

	public String getId_negara() {
		return id_negara.toUpperCase();
	}

	public void setId_negara(String idNegara) {
		id_negara = idNegara;
	}

	public String getKod_negara() {
		return kod_negara.toUpperCase();
	}

	public void setKod_negara(String kodNegara) {
		kod_negara = kodNegara;
	}

	public String getNama_negara() {
		return nama_negara.toUpperCase();
	}

	public void setNama_negra(String namaNegara) {
		nama_negara = namaNegara;
	}

}
