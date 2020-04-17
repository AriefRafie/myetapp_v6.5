package etapp.module.ppt;

import javax.servlet.http.HttpSession;

import ekptg.helpers.InternalUserUtil;
import ekptg.model.entities.InternalUser;

public class PPTPermohonanSabahSarawakModule extends PPTPermohonanModule {
	
	private String idUser = null;
	private InternalUser iu = null;
	
	public void begin() {
		try{
			HttpSession session = this.request.getSession();
			//System.out.println("1. idNegeri="+session);
			idUser = session.getAttribute("_ekptg_user_id").toString();
			//System.out.println("2. idNegeri="+idUser);
			iu = InternalUserUtil.getSeksyenId(idUser);
			idNegeri = Long.parseLong(iu.getIdNegeri());
			//System.out.println("3. idNegeri="+idNegeri);
			setIdNegeri(idNegeri);
			super.begin();
		
		}catch(Exception e){
			//throw new Exception("PPT MODUL] SILA LOGIN SEMULA");
		}
		
	}

}
