package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphpversikoordinat entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpversikoordinat extends AbstractTblphpversikoordinat
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpversikoordinat() {
	}

	/** minimal constructor */
	public Tblphpversikoordinat(Long idVersikoordinat) {
		super(idVersikoordinat);
	}

	/** full constructor */
	public Tblphpversikoordinat(Long idVersikoordinat,
			Tblphpjadualkedualesenapb tblphpjadualkedualesenapb,
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama, Long noVersi,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblphpkoordinatpermohonans) {
		super(idVersikoordinat, tblphpjadualkedualesenapb,
				tblphppmohonnjdualpertama, noVersi, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, tblphpkoordinatpermohonans);
	}

}
