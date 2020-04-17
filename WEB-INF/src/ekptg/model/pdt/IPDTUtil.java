package ekptg.model.pdt;

import java.util.Vector;

public interface IPDTUtil {	
	
	public String getAktaPilihan(String idAkta, String selectName,
			String selectedValue, String disability, String jsFunction) throws Exception;
	public Vector getMaklumat(String id,String no, String nama,String tag, String sumber) throws Exception;

}
