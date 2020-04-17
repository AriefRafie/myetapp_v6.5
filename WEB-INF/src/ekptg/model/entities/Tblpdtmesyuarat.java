package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtmesyuarat entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtmesyuarat extends AbstractTblpdtmesyuarat implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtmesyuarat() {
	}

	/** minimal constructor */
	public Tblpdtmesyuarat(Long idMesyuarat) {
		super(idMesyuarat);
	}

	/** full constructor */
	public Tblpdtmesyuarat(Long idMesyuarat, String bilMesyuarat,
			String tajukMesyuarat, String kategoriMesyuarat,
			Date tarikhMesyuarat, String masaMesyuaratDari, Long idLokasi,
			Long idFail, Long idSeksyen, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,Long idPegawai,
			String catatan, Long idDb,String waktuMesyuaratDari,String masaMesyuaratHingga,
                        String waktuMesyuaratHingga, Long idStatus,
                        Set tblpdtminitmesyuarats, Set tblpdtahlimesyuarats,Set tblpdtversis) {
		super(idMesyuarat, bilMesyuarat, tajukMesyuarat, kategoriMesyuarat,
				tarikhMesyuarat, masaMesyuaratDari, idLokasi, idFail,
				idSeksyen, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, idPegawai, catatan, idDb,
                                waktuMesyuaratDari,masaMesyuaratHingga,waktuMesyuaratHingga,idStatus,
                                tblpdtminitmesyuarats, tblpdtahlimesyuarats,tblpdtversis);
	}

}
