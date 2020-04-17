package ekptg.view.admin.utils;

public class BangsaInformation {

	private String id_bangsa;
	private String kod_bangsa;
	private String keterangan;

	public BangsaInformation() {
	}

	public BangsaInformation(String id_bangsa, String kod_bangsa,
			String keterangan) {
		this.id_bangsa = id_bangsa;
		this.kod_bangsa = kod_bangsa;
		this.keterangan = keterangan;
	}

	public String getId_bangsa() {
		return id_bangsa;
	}

	public void setId_bangsa(String idBangsa) {
		id_bangsa = idBangsa.toUpperCase();
	}

	public String getKod_bangsa() {
		return kod_bangsa;
	}

	public void setKod_bangsa(String kodBangsa) {
		kod_bangsa = kodBangsa.toUpperCase();
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan.toUpperCase();
	}

}
