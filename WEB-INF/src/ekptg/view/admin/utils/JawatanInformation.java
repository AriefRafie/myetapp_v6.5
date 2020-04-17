package ekptg.view.admin.utils;

public class JawatanInformation {

	private String id_jawatan;
	private String kod_jawatan;
	private String keterangan;

	public JawatanInformation() {}
	public JawatanInformation(
			String id_jawatan, String kod_jawatan, String keterangan
	){
		this.id_jawatan = id_jawatan;
		this.kod_jawatan = kod_jawatan;
		this.keterangan = keterangan;
	}
	
	public String getId_jawatan() {
		return id_jawatan.toUpperCase();
	}

	public void setId_jawatan(String idJawatan) {
		id_jawatan = idJawatan;
	}

	public String getKod_jawatan() {
		return kod_jawatan.toUpperCase();
	}

	public void setKod_jawatan(String kodJawatan) {
		kod_jawatan = kodJawatan;
	}

	public String getKeterangan() {
		return keterangan.toUpperCase();
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

}
