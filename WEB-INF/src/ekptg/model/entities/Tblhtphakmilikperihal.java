package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtphakmilikperihal entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtphakmilikperihal extends AbstractTblhtphakmilikperihal
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtphakmilikperihal() {
	}

	/** minimal constructor */
	public Tblhtphakmilikperihal(Long idPerihal, Long idHakmilikpegangan,
			Long idMasuk) {
		super(idPerihal, idHakmilikpegangan, idMasuk);
	}

	/** full constructor */
	public Tblhtphakmilikperihal(Long idPerihal, Long idHakmilikpegangan,
			String luasPetak, String kegunaanTapak, Double luasBangunan,
			Double luasParking, Double luasLain, Double luasBelumdibangunkan,
			String kegunaanTanah, Double luasJalan, Double luasPadang,
			String status, Long idMasuk, Date tarikhMasuk) {
		super(idPerihal, idHakmilikpegangan, luasPetak, kegunaanTapak,
				luasBangunan, luasParking, luasLain, luasBelumdibangunkan,
				kegunaanTanah, luasJalan, luasPadang, status, idMasuk,
				tarikhMasuk);
	}

}
