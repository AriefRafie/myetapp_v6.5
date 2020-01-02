package ekptg.model.ppk.harta;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;

import ekptg.model.ppk.FrmPrmhnnSek8InternalData;

public interface IMaklumatHarta{	
	
	public void getMaklumtHarta(org.apache.velocity.VelocityContext context
		,HttpSession session
		,Hashtable hParam
		,String mode
		,String bolehsimpan
		,int idNegeri
		,FrmPrmhnnSek8InternalData logic_internal
			) throws Exception ;
	
	
}
