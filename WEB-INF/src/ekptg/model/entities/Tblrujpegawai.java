package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblrujpegawai entity. @author MyEclipse Persistence Tools
 */
public class Tblrujpegawai extends AbstractTblrujpegawai implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujpegawai() {
	}

	/** minimal constructor */
	public Tblrujpegawai(Long idPegawai) {
		super(idPegawai);
	}

	/** full constructor */
	public Tblrujpegawai(Long idPegawai, Long idNegeri,
			Long idMukim, String namaPegawai, String noPekerja,
			String noKp, String jawatan, String alamat1, String alamat2,
			String alamat3, Long poskod, String noTelPejabat,
			String noTelRumah, String noTelBimbit, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,Long idSeksyen,String emel,
			Set tblonlineaduans) {
		super(idPegawai, idNegeri, idMukim, namaPegawai, noPekerja,
				noKp, jawatan, alamat1, alamat2, alamat3, poskod, noTelPejabat,
				noTelRumah, noTelBimbit, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini,idSeksyen,emel, tblonlineaduans);
	}

}
