package ekptg.model.htp.pajakan;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.MaklumatBil;
import ekptg.model.htp.entity.Pajakan;
import ekptg.model.htp.entity.PajakanKadar;

public interface IPajakanUtamaBayaran {	
	
	public Pajakan getMaklumatCukai(String idPermohonan) throws Exception ;
	public PajakanKadar saveMaklumatKadar(PajakanKadar pk, HttpSession session) throws Exception ;
	public Vector getPajakanKadar(String idPermohonan) throws Exception ;
	public PajakanKadar getPajakanKadarI(String idPajakanKadar) throws Exception ;
	public PajakanKadar saveUpdateMaklumatKadar(PajakanKadar pk, HttpSession session) throws Exception ;
	public PajakanKadar deleteMaklumatKadar(PajakanKadar pk) throws Exception ;
	public Pajakan getMaklumatPajakanKadarPermohonan(String idPermohonan) throws Exception ;
	public PajakanKadar getPajakanKadarI(String idPajakan,String tahun) throws Exception ;
	public MaklumatBil getPajakanBil(String idPajakan,String tahun) throws Exception ;
	public boolean isHakmilikPajakan(HakMilik hakmilik);


}
