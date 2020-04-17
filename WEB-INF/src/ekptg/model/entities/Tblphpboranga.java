package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphpboranga entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpboranga extends AbstractTblphpboranga implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpboranga() {
	}

	/** minimal constructor */
	public Tblphpboranga(Long idBoranga) {
		super(idBoranga);
	}

	/** full constructor */
	public Tblphpboranga(Long idBoranga,
			Tblphpjadualkedualesenapb tblphpjadualkedualesenapb, String tujuan,
			String destinasi, Long idIsipadu, Double isipadu,
			Double anggaranRoyalti, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblphpbarges) {
		super(idBoranga, tblphpjadualkedualesenapb, tujuan, destinasi,
				idIsipadu, isipadu, anggaranRoyalti, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, tblphpbarges);
	}

}
