package ekptg.model.htp.pembelian;

import ekptg.model.htp.entity.Peguam;

public interface IPembelianPeguam {
	public Peguam simpanPeguam(Peguam peguam);
	public Peguam updatePeguam(Peguam peguam);
	public Peguam findByIdPermohonan(String idPermohonan);
}
