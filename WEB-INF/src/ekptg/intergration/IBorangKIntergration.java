package ekptg.intergration;

import java.util.Vector;

import ekptg.intergration.entity.BorangK;
import ekptg.intergration.entity.HTPBorangK;

public interface IBorangKIntergration {
	public Vector<BorangK> getAvailableList();
	public Vector<BorangK> getAvailableList(String noFailPPT);
	public Vector<BorangK> getAvailableList(String noFailPPT,String idKementerian);
	public Vector<BorangK> getAvailableList(String noFailPPT,String idKementerian, String noLot);
	public Vector<HTPBorangK> getHTPBorangKList(String idPermohonanHTP);
	public void simpanHTPBorangK(String idHakmilikPPT,String idPermohonanHTP,String idUser) throws Exception;
	public void simpanBorangK(String idHakmilikPPT,String idPermohonanHTP,String idUser) throws Exception;

}
