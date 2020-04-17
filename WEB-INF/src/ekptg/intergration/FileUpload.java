package ekptg.intergration;

public final class FileUpload {
	public String uploadPath;
	public String loginName;
	public String prosesType;
	public String uploadTableName;
	public String uploadFieldName;
	public long fkValue;
	public String fkField;
	public String pkField;
	public long pkValue;
	public String mimeField;

	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getProsesType() {
		return prosesType;
	}
	public void setProsesType(String prosesType) {
		this.prosesType = prosesType;
	}
	public String getUploadTableName() {
		return uploadTableName;
	}
	public void setUploadTableName(String uploadTableName) {
		this.uploadTableName = uploadTableName;
	}
	public String getUploadFieldName() {
		return uploadFieldName;
	}
	public void setUploadFieldName(String uploadFieldName) {
		this.uploadFieldName = uploadFieldName;
	}
	public long getFkValue() {
		return fkValue;
	}
	public void setFkValue(long fkValue) {
		this.fkValue = fkValue;
	}
	public String getFkField() {
		return fkField;
	}
	public void setFkField(String fkField) {
		this.fkField = fkField;
	}
	public String getPkField() {
		return pkField;
	}
	public void setPkField(String pkField) {
		this.pkField = pkField;
	}
	public long getPkValue() {
		return pkValue;
	}
	public void setPkValue(long pkValue) {
		this.pkValue = pkValue;
	}
	public String getMimeField() {
		return mimeField;
	}
	public void setMimeField(String mimeField) {
		this.mimeField = mimeField;
	}
	
}
