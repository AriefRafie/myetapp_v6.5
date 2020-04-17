package etapp.module.ppt;

import java.util.HashMap;
import java.util.Map;

import lebah.template.LebahRecordTemplateModule;
import etapp.entity.ppt.PPTStatus;

public class SetupPPTStatusModule  extends LebahRecordTemplateModule<PPTStatus>{
	

	@Override
	public Class getIdType() {
		return Long.class;
	}

	@Override
	public void afterSave(PPTStatus r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeSave() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void begin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(PPTStatus r) throws Exception {
		return true;
	}

	@Override
	public String getPath() {
		return "ekptg/ppt/jts_status";
	}

	@Override
	public Class<PPTStatus> getPersistenceClass() {
		return PPTStatus.class;
	}

	@Override
	public void getRelatedData(PPTStatus r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(PPTStatus r) throws Exception {
		String kod = get("kod");
		String nama = get("nama");
		String kategori = get("kategori");
		
		r.setKod(kod);
		r.setNama(nama);
		r.setKategori(kategori);
		
	}

	@Override
	public Map<String, Object> searchCriteria() throws Exception {
		String findKod = get("findKod");
		String findNama = get("findNama");
		String findKategori = get("findKategori");
		context.put("findKod", findKod);
		context.put("findNama", findNama);
		context.put("findKategori", findKategori);

		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("kod", findKod);
		map.put("nama", findNama);
		map.put("kategori", findKategori);
		return map;
	}

}
