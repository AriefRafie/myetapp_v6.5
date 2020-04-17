package ekptg.model.htp.pembelian;

import ekptg.model.htp.entity.Rundingan;

public interface IRundingan {
	public Rundingan saveRundingan(Rundingan rundingan);
	public Rundingan updateRundingan(Rundingan rundingan);
	public Rundingan findByPermohonan(String idPermohonan);
}
