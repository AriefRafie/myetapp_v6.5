package ekptg.model.entities;


import java.util.Date;

/**
 * Tblpdtahlimesyuarat entity. @author MyEclipse Persistence Tools
 */
public class Tblpdtahlimesyuarat extends AbstractTblpdtahlimesyuarat implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtahlimesyuarat() {
	}

	/** full constructor */
	public Tblpdtahlimesyuarat(Long idPegawai,Long idMesyuarat, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, String emel,
			String noTel, String noFaks, Long idDb,
                        String namaAhlimesyuarat, String jawatan, String flagPengerusi, Long idSeksyen) {
		super(idPegawai, idMesyuarat, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, emel, noTel, noFaks, idDb,
                                namaAhlimesyuarat, jawatan, flagPengerusi, idSeksyen);
	}

}
