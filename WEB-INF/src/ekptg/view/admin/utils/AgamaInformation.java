package ekptg.view.admin.utils;

public class AgamaInformation {

	private String id_agama;
	private String kod_agama;
	private String keterangan;

	public AgamaInformation() {}
	
	public AgamaInformation(String id_agama, String kod_agama, String keterangan){
		this.id_agama = id_agama;
		this.kod_agama = kod_agama;
		this.keterangan = keterangan;	
	}
	
	
	public String getId_agama() {
		return id_agama.toUpperCase();
	}

	public void setId_agama(String idAgama) {
		id_agama = idAgama;
	}

	public String getKod_agama() {
		return kod_agama.toUpperCase();
	}

	public void setKod_agama(String kodAgama) {
		kod_agama = kodAgama;
	}

	public String getKeterangan() {
		return keterangan.toUpperCase();
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

}
