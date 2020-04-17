package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkrayuan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkrayuan extends AbstractTblppkrayuan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkrayuan() {
	}

	/** minimal constructor */
	public Tblppkrayuan(Long idRayuan, Tblppkpermohonan tblppkpermohonan) {
		super(idRayuan, tblppkpermohonan);
	}

	/** full constructor */
	public Tblppkrayuan(Long idRayuan,
			Tblrujppkjenisperintah tblrujppkjenisperintah,
			Tblppkpermohonan tblppkpermohonan, Tblrujppkunit tblrujppkunit,
			Date tarikhMohon, String maklumatPerayu, String perkaraRayu,
			Long idNegeri, Long idDaerah, Long idNegeriunitpsk,
			String keputusan, String item01, String item02, String item03,
			String item04, String item05, String item06, String item07,
			String item08, String item09, String catatan, Long idMahkamah,
			Long idNegerimah, String namaPuu, String alamatPuu1,
			String alamatPuu2, String alamatPuu3, String bandar, String poskod,
			Long idNegeripuu, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblppkperayus) {
		super(idRayuan, tblrujppkjenisperintah, tblppkpermohonan,
				tblrujppkunit, tarikhMohon, maklumatPerayu, perkaraRayu,
				idNegeri, idDaerah, idNegeriunitpsk, keputusan, item01, item02,
				item03, item04, item05, item06, item07, item08, item09,
				catatan, idMahkamah, idNegerimah, namaPuu, alamatPuu1,
				alamatPuu2, alamatPuu3, bandar, poskod, idNegeripuu, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, tblppkperayus);
	}

}
