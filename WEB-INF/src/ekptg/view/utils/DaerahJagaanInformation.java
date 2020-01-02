package ekptg.view.utils;
/**
 * 
 * @author Mohd Nazrul
 *
 */
public class DaerahJagaanInformation {

	private String id_pejabaturusan;
	private String id_daerahurus;
	private String kod_daerahurus;
	private String nama_daerahurus;
	private String id_pejabatjkptg;

	public DaerahJagaanInformation () {}
	
	public DaerahJagaanInformation(String id_pejabaturusan,
			String id_daerahurus, String kod_daerah, String nama_daerahurus,
			String id_pejabatjkptg){
		this.id_pejabaturusan = id_pejabaturusan;
		this.id_daerahurus = id_daerahurus;
		this.kod_daerahurus = kod_daerah;
		this.nama_daerahurus = nama_daerahurus;
		this.id_pejabatjkptg = id_pejabatjkptg;
	}
	
	
	public String getId_pejabaturusan() {
		return id_pejabaturusan;
	}

	public void setId_pejabaturusan(String idPejabaturusan) {
		id_pejabaturusan = idPejabaturusan;
	}

	public String getId_daerahurus() {
		return id_daerahurus;
	}

	public void setId_daerahurus(String idDaerahurus) {
		id_daerahurus = idDaerahurus;
	}

	public String getKod_daerahurus() {
		return kod_daerahurus;
	}

	public void setKod_daerahurus(String kodDaerahurus) {
		kod_daerahurus = kodDaerahurus;
	}

	public String getNama_daerahurus() {
		return nama_daerahurus;
	}

	public void setNama_daerahurus(String namaDaerahurus) {
		nama_daerahurus = namaDaerahurus;
	}

	public String getId_pejabatjkptg() {
		return id_pejabatjkptg;
	}

	public void setId_pejabatjkptg(String idPejabatjkptg) {
		id_pejabatjkptg = idPejabatjkptg;
	}

}
