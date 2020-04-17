package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptpermintaanukur entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptpermintaanukur extends AbstractTblpptpermintaanukur
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptpermintaanukur() {
	}

	/** full constructor */
	public Tblpptpermintaanukur(String noRujukanPtg, Date tarikhSuratPtg,
			String noPelan, String noPu, Date tarikhPu, Date tarikhTerimaSp,
			String noSp, Date tarikhTerimaSa, Date tarikhTerimaPa, String noPa,
			Date tarikhSelesai, Date tarikhKembaliUkur, String catatan,
			String namaPegawai, Date tarikhSedia, Date tarikhBayar,
			Date tarikhHakmilikSambung, Long jenisPelarasan, Double amaunPu,
			Long idUnitluaspu, Long luasPu, Double faedahSebelum,
			Double faedahSelepas, Date tarikhBayarAward, Date tarikhBorangk,
			Date tarikhTerimaAgensi, Date tarikhAkhirAgensi,
			Date tarikhHantarJupem, Long idHakmilik, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(noRujukanPtg, tarikhSuratPtg, noPelan, noPu, tarikhPu,
				tarikhTerimaSp, noSp, tarikhTerimaSa, tarikhTerimaPa, noPa,
				tarikhSelesai, tarikhKembaliUkur, catatan, namaPegawai,
				tarikhSedia, tarikhBayar, tarikhHakmilikSambung,
				jenisPelarasan, amaunPu, idUnitluaspu, luasPu, faedahSebelum,
				faedahSelepas, tarikhBayarAward, tarikhBorangk,
				tarikhTerimaAgensi, tarikhAkhirAgensi, tarikhHantarJupem,
				idHakmilik, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				idDb);
	}

}
