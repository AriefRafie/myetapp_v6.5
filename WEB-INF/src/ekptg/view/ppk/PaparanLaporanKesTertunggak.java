package ekptg.view.ppk;


import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.CetakLaporanKesTertunggakInternalData;





public class PaparanLaporanKesTertunggak extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(PaparanLaporanKesTertunggak.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private final String PATH="app/ppk/cetakLaporanKesTertunggak.jsp";
	private String vm = PATH;
	private static Vector listDataOBNotis = new Vector();
	
	HttpSession session = null;
	String action = null;
	
	CetakLaporanKesTertunggakInternalData modelLaporan = new CetakLaporanKesTertunggakInternalData();
	
	public String doTemplate2() throws Exception {
		String print = getParam("print");
		String mula_hari = getParam("mula_hari");
		String mula_bulan = getParam("mula_bulan");
		String mula_tahun = getParam("mula_tahun");
		String akhir_hari = getParam("akhir_hari");
		String akhir_bulan = getParam("akhir_bulan");
		String akhir_tahun = getParam("akhir_tahun");
		String negeri = getParam("negeri");
		String daerah = getParam("daerah");
		String unit = getParam("unit");
		System.out.println("print = " + print);
		String tajuk = "";
		Vector listSenarai = new Vector();
		listSenarai = null;
		if (print.equals( "PrintLaporanA")){
			//System.out.println("print = " + print);
		modelLaporan.setDataSenaraiSerahan(mula_hari,mula_bulan,mula_tahun,akhir_hari,akhir_bulan,akhir_tahun, negeri, daerah, unit);
		listSenarai = modelLaporan.getSenaraiLaporanSerahan();
		tajuk = "MASIH MENUNGGU BORANG C";
		
	}
		else if (print.equals( "PrintLaporanB")){
			//System.out.println("print = " + print);
		modelLaporan.setDataSenaraiSerahanB(mula_hari,mula_bulan,mula_tahun,akhir_hari,akhir_bulan,akhir_tahun, negeri, daerah, unit);
		listSenarai = modelLaporan.getSenaraiLaporanSerahanB();
		tajuk = "MENUNGGU SIASATAN / NILAIAN";
	}
		else if (print.equals( "PrintLaporanC")){
			//System.out.println("print = " + print);
		modelLaporan.setDataSenaraiSerahanC(mula_hari,mula_bulan,mula_tahun,akhir_hari,akhir_bulan,akhir_tahun, negeri, daerah, unit);
		listSenarai = modelLaporan.getSenaraiLaporanSerahanB();
		tajuk = "PEMOHON / WARIS TIDAK HADIR";
	}
		else if (print.equals( "PrintLaporanD")){
			//System.out.println("print = " + print);
		modelLaporan.setDataSenaraiSerahanD(mula_hari,mula_bulan,mula_tahun,akhir_hari,akhir_bulan,akhir_tahun, negeri, daerah, unit);
		listSenarai = modelLaporan.getSenaraiLaporanSerahanB();
		tajuk = "SENARAI WARIS TIDAK LENGKAP";
	}
		else if (print.equals( "PrintLaporanE")){
			//System.out.println("print = " + print);
		modelLaporan.setDataSenaraiSerahanE(mula_hari,mula_bulan,mula_tahun,akhir_hari,akhir_bulan,akhir_tahun, negeri, daerah, unit);
		listSenarai = modelLaporan.getSenaraiLaporanSerahanB();
		tajuk = "MENUNGGU KEPUTUSAN RUJUKAN KE MAHKAMAH TINGGI / FARAID";
	}
		else if (print.equals( "PrintLaporanF")){
			//System.out.println("print = " + print);
		modelLaporan.setDataSenaraiSerahanF(mula_hari,mula_bulan,mula_tahun,akhir_hari,akhir_bulan,akhir_tahun, negeri, daerah, unit);
		listSenarai = modelLaporan.getSenaraiLaporanSerahanB();
		tajuk = "TUNGGU KEPUTUSAN RAYUAN MAHKAMAH";
	}
		else if (print.equals( "PrintLaporanG")){
			//System.out.println("print = " + print);
		modelLaporan.setDataSenaraiSerahanG(mula_hari,mula_bulan,mula_tahun,akhir_hari,akhir_bulan,akhir_tahun, negeri, daerah, unit);
		listSenarai = modelLaporan.getSenaraiLaporanSerahanB();
		tajuk = "SEBAB-SEBAB LAIN";
	}
		else
		{
			System.out.println("Invalid");
		}
		this.context.put("SenaraiSerahan", listSenarai);
		this.context.put("tajuk", tajuk);
		return vm;
	}
	
	
}	
	
	

