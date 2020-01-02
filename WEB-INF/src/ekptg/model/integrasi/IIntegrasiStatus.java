package ekptg.model.integrasi;

import etapp.entity.integrasi.StatusSemasa;

public interface IIntegrasiStatus {	

	public String simpan(StatusSemasa status,StatusSemasa statusBaru) throws Exception;
	public String kemaskini(StatusSemasa status) throws Exception;
    public Long getIdStatusModulByLangkah (String langkah, String modul, String op) throws Exception;

}
