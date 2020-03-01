package ekptg.model.ppk.harta;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ekptg.model.ppk.FrmPrmhnnSek8InternalData;

public interface IMaklumatHarta{	
	
	public Hashtable<String,String> setSocParamValues(String negeri
		,String daerah
		,String mukim
		,String bandar
		,String jenisHakmilik
		,String kat
		,String luas
		,String pemilikan) throws Exception ;
	public void getHarta(String mode
		,Hashtable<String,String> hParam
		,FrmPrmhnnSek8InternalData logic_internal
		,HttpServletRequest request
		,HttpSession session
		,org.apache.velocity.VelocityContext context) throws Exception;	
	public void getHTA(String mode
		,Hashtable hParam
		,FrmPrmhnnSek8InternalData logic_internal
		,HttpServletRequest request
		,HttpSession session
		,org.apache.velocity.VelocityContext context) throws Exception;
	
	
}
