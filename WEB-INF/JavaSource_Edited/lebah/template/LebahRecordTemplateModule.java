package lebah.template;


public abstract class LebahRecordTemplateModule<T> extends RecordTemplateModule<T>{
	
	public abstract Class getIdType();
	

	@Override
	public String getTableHeaderPage() {
		return getPath() + "/row_title.vm";
	}
	
	@Override
	public String getDataRowPage() {
		return getPath() + "/row_data.vm";
	}
	
	@Override
	public String getDataEntryPage() {
		return getPath() + "/entry_page.vm";
	}
	
	@Override
	public String getSearchEntryPage() {
		return getPath() + "/search_page.vm";
	}
	
	@Override
	public int getRowSize() {
		return 10;
	}


}
