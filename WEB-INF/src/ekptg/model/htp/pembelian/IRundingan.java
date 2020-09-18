package ekptg.model.htp.pembelian;

import java.util.Vector;

import ekptg.model.htp.entity.Rundingan;

public interface IRundingan {
	public Vector<Rundingan> senaraiRundingan(String idPermohonan);
	public Rundingan saveRundingan(Rundingan rundingan);
	public Rundingan updateRundingan(Rundingan rundingan);
	public Rundingan findByPermohonan(String idPermohonan);
	public Rundingan findByPermohonan(String idPermohonan,String idRundingan);
	
}
