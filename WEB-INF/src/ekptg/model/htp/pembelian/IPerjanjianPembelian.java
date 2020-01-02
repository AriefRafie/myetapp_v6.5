package ekptg.model.htp.pembelian;

import java.util.Vector;

import ekptg.model.htp.entity.PerjanjianPembelian;
import ekptg.model.htp.entity.PerjanjianPindahMilik;

public interface IPerjanjianPembelian {
	public PerjanjianPembelian simpanPerjanjian(PerjanjianPembelian perjanjian);
	public PerjanjianPembelian updatePerjanjian(PerjanjianPembelian perjanjian);
	public PerjanjianPembelian getPerjanjianByPermohonan(String idPermohonan);
	public PerjanjianPindahMilik getPerjanjianPermohonan(String idPermohonan);
	public PerjanjianPindahMilik updatePerjanjian(PerjanjianPindahMilik pindahMilik);
	public void hapusPerjanjian(String idDokumenPerjanjianAttach);
	public Vector getPerjanjianUpload(String idPermohonan);
	public Vector getPerjanjianAttach(String idPermohonan);
	
	public PerjanjianPindahMilik simpanPindahMilik(PerjanjianPindahMilik pindahMilik);
	public PerjanjianPindahMilik simpanPerjanjian(PerjanjianPindahMilik pindahMilik);
	public PerjanjianPindahMilik updatePindahMilik(PerjanjianPindahMilik pindahMilik);
	public Vector getPindahMilikByPermohonan(String idPermohonan);
	public PerjanjianPindahMilik getPindahMilik(String idPindahMilik);
}
