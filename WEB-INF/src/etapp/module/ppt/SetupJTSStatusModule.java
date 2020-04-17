package etapp.module.ppt;

import java.util.HashMap;
import java.util.Map;

import lebah.template.RecordTemplateModule2;
import etapp.entity.ppt.PPTStatus;

public class SetupJTSStatusModule extends RecordTemplateModule2<PPTStatus> {

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
		
		r.setKod(kod);
		r.setNama(nama);
		
		
	}

	@Override
	public Map<String, Object> searchCriteria() throws Exception {
		String findKod = get("find_kod");
		String findNama = get("find_nama");
		context.put("findKod", findKod);
		context.put("findNama", findNama);

		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("findKod", findKod);
		map.put("findNama", findNama);
		return map;
	}

}
