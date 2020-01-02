package ekptg.model.entities;

import java.util.Date;


/**
 * Tblpfdsubjaket entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpfdsubjaket extends AbstractTblpfdsubjaket implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpfdsubjaket() {
	}

	/** minimal constructor */
	public Tblpfdsubjaket(Long idSubjaket, Long idFail) {
		super(idSubjaket, idFail);
	}

	/** full constructor */
	public Tblpfdsubjaket(Long idSubjaket, Long idFail,
			Long idPegawai, Date tarikhSubjaketFail, Date tarikhMasukFail,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, String noFailSubjaket, Long idDb) {
		super(idSubjaket, idFail, idPegawai, tarikhSubjaketFail,
				tarikhMasukFail, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, noFailSubjaket, idDb);
	}

}
