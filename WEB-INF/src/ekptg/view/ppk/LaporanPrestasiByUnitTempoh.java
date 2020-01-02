package ekptg.view.ppk;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.CetakLaporanPrestasi;
import ekptg.model.utils.ILaporan;
import ekptg.model.utils.LaporanBean;

public class LaporanPrestasiByUnitTempoh extends AjaxBasedModule {
	/**
	 * 
	 */
	private final String PATH = "app/ppk/";
	private final String ID_SEKSYEN = "2";
 	private ILaporan iLaporan = null;  
	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.ppk.LaporanPrestasiByUnitTempoh.class);
	CetakLaporanPrestasi modelLaporan = new CetakLaporanPrestasi();
	
	public String doTemplate2() throws Exception {
	    HttpSession session = this.request.getSession();
	    Vector listPrestasi = new Vector();
		String vm = PATH+"LaporanPrestasiByUnitTempoh.jsp";
		//BULAN_DARI="+bulan1+"&BULAN_HINGGA="+bulan2+"&TAHUN_DARI="+tahun1+"&TAHUN_HINGGA="+tahun2+"&ID_NEGERI="+negeri+"&ID_UNIT="+unit;
		String BULAN_DARI = getParam("BULAN_DARI");
		String BULAN_HINGGA = getParam("BULAN_HINGGA");
		String TAHUN_DARI = getParam("TAHUN_DARI");
		String TAHUN_HINGGA = getParam("TAHUN_HINGGA");
		String ID_NEGERI = getParam("ID_NEGERI");
		String ID_UNIT = getParam("ID_UNIT");
		
		listPrestasi = null;
		modelLaporan.setDataPrestasibyUnitTempoh(BULAN_DARI,BULAN_HINGGA,TAHUN_DARI,TAHUN_HINGGA,ID_NEGERI,ID_UNIT);
		listPrestasi = modelLaporan.getSenaraiPrestasi();
		context.put("listPrestasi",listPrestasi);
		context.put("BULAN_DARI",BULAN_DARI);
		context.put("BULAN_HINGGA",BULAN_HINGGA);
		context.put("TAHUN_DARI",TAHUN_DARI);
		context.put("TAHUN_HINGGA",TAHUN_HINGGA);
		context.put("ID_NEGERI",ID_NEGERI);
		context.put("ID_UNIT ",ID_UNIT );
		return vm;
		
	}
	
	private ILaporan getILaporan(){
		if(iLaporan == null)
			iLaporan = new LaporanBean();
		return iLaporan;
		
	}	
	
	
}
