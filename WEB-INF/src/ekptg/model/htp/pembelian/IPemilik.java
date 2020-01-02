package ekptg.model.htp.pembelian;

import java.util.Vector;

import ekptg.model.htp.PihakBerkepentingan;

public interface IPemilik {
	public PihakBerkepentingan savePemilik(PihakBerkepentingan pemilik);
	public PihakBerkepentingan updatePemilik(PihakBerkepentingan pemilik);
	public void deletePemilik(String idPihakBerkepentigan);
	public PihakBerkepentingan findPemilik(String idPihakBerkepentigan);
	public Vector<PihakBerkepentingan> findPemilikByPermohonan(String idPermohonan);
	
}
