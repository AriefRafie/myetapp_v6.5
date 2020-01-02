package ekptg.model.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Tblpfdrujlokasimesyuarat entity. @author MyEclipse Persistence Tools
 */
public class Tblpfdrujlokasimesyuarat extends AbstractTblpfdrujlokasimesyuarat
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpfdrujlokasimesyuarat() {
	}

	/** full constructor */
	public Tblpfdrujlokasimesyuarat(String lokasi, BigDecimal idMasuk,
			Date tarikhMasuk, BigDecimal idKemaskini, Date tarikhKemaskini,
			BigDecimal idNegeri, BigDecimal idSeksyen, Set tblpfdmesyuarats) {
		super(lokasi, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				idNegeri, idSeksyen, tblpfdmesyuarats);
	}

}
