package ekptg.model.htp.pembelian;

import java.util.Vector;

import ekptg.model.htp.entity.DrafPerjanjian;

public interface IPembelianDrafPerjanjian {
	public DrafPerjanjian saveDrafPerjanjian(DrafPerjanjian draf);
	public DrafPerjanjian updateDrafPerjanjian(DrafPerjanjian draf);
	public  DrafPerjanjian getDrafPerjanjian(String idDraf);
	public Vector<DrafPerjanjian> getDrafList(String idPermohonan);
	public void deleteDrafPerjanjian(String idDraf);
}
