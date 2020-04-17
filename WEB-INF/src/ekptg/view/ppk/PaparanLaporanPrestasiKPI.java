package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.ppk.CetakLaporanSerahanBaitulmalPBNInternalData1;
  
public class PaparanLaporanPrestasiKPI extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(PaparanLaporanPrestasiKPI.class);
	//private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private final String PATH="app/ppk/cetakLaporanPrestasiKPI.jsp";
	private String vm = PATH;
	private final String PATH2="app/ppk/cetakLaporanPrestasiKPI.jsp";
	private String vm2 = PATH2;
	//private static Vector listDataOBNotis = new Vector();
	
	HttpSession session = null;
	String action = null;	
	CetakLaporanSerahanBaitulmalPBNInternalData1 modelLaporan = new CetakLaporanSerahanBaitulmalPBNInternalData1();
	
	public String doTemplate2() throws Exception {		
		Vector<Hashtable<String,String>> listSenarai = new Vector<Hashtable<String,String>>();
		
		String mula_bulan = getParam("mula_bulan");
		String mula_tahun = getParam("mula_tahun");
		int tahun_sebelum = Integer.parseInt(mula_tahun)-1;
		String section = getParam("section");
		String negeri = getParam("negeri");		
		listSenarai = null;
		
		modelLaporan.setDataSenaraiPrestasiKPI(mula_bulan, mula_tahun,section,negeri);
		vm = vm2;
				
		listSenarai = modelLaporan.getSenaraiLaporanSerahan();
		Double jumlahPohon = 0.0;
		Double jumlahSelesai = 0.0;
		Double jumlahPeratus = 0.0;
		
		for (int i = 0; i < listSenarai.size(); i++){
			Hashtable<String,String> h = (Hashtable<String,String>)listSenarai.get(i);
			if(h.get("LAYER").equals("1")){
				jumlahPohon = jumlahPohon + Double.parseDouble(String.valueOf(h.get("JUMLAH_MOHON")));
				jumlahSelesai = jumlahSelesai + Double.parseDouble(String.valueOf(h.get("JUMLAH_SELESAI")));
			}

		}
		jumlahPeratus = (jumlahSelesai/jumlahPohon)*100;

		this.context.put("SenaraiLaporan", listSenarai);
		this.context.put("mula_bulan", mula_bulan);
		this.context.put("mula_tahun", mula_tahun);
		this.context.put("tahun_sebelum", tahun_sebelum);
		this.context.put("section", section);
		this.context.put("jumlahMohon",jumlahPohon.intValue());
		this.context.put("jumlahSelesai", jumlahSelesai.intValue());
		this.context.put("jumlahPeratus", Utils.format2Decimal(jumlahPeratus));
		return vm;
		
	}
	
	
}	
	
	

