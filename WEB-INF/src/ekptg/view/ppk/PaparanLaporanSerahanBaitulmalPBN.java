package ekptg.view.ppk;


import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.CetakLaporanSerahanBaitulmalPBNInternalData1;

public class PaparanLaporanSerahanBaitulmalPBN extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(PaparanLaporanSerahanBaitulmalPBN.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private final String PATH="app/ppk/cetakLaporanSerahanBaitulmalPBN.jsp";
	private String vm = PATH;
	private static Vector listDataOBNotis = new Vector();
	
	HttpSession session = null;
	String action = null;
	
	CetakLaporanSerahanBaitulmalPBNInternalData1 modelLaporan = new CetakLaporanSerahanBaitulmalPBNInternalData1();
	
	public String doTemplate2() throws Exception {		
		Vector listSenarai = new Vector();
		String mula_hari = getParam("mula_hari");
		String mula_bulan = getParam("mula_bulan");
		String mula_tahun = getParam("mula_tahun");
		String akhir_hari = getParam("akhir_hari");
		String akhir_bulan = getParam("akhir_bulan");
		String akhir_tahun = getParam("akhir_tahun");
		String PBN = getParam("PBN");
		String jenis_laporan = getParam("jenis_laporan");
		String daerah = getParam("daerah");
		listSenarai = null;
		if (jenis_laporan.equals("Baitulmaldaerah"))
		{
			modelLaporan.setDataSenaraiSerahanDaerah(mula_hari,mula_bulan,mula_tahun,akhir_hari,akhir_bulan,akhir_tahun, daerah);
		}
		else if (jenis_laporan.equals("Baitulmalselangmasa"))
		{
			modelLaporan.setDataSenaraiSerahan(mula_hari,mula_bulan,mula_tahun,akhir_hari,akhir_bulan,akhir_tahun);
			}
		else if	(jenis_laporan.equals("Baitulmaldaerahtahun"))
		{
			modelLaporan.setDataSenaraiSerahandaerahtahun(mula_hari,mula_bulan,mula_tahun,akhir_hari,akhir_bulan,akhir_tahun);
		}
		else if (jenis_laporan.equals("Baitulmaldaerahbulan"))
		{
			modelLaporan.setDataSenaraiSerahandaerahbulan(mula_hari,mula_bulan,mula_tahun,akhir_hari,akhir_bulan,akhir_tahun);
		}
			else
				
			{
				modelLaporan.setDataSenaraiSerahanPBN(mula_hari,mula_bulan,mula_tahun,akhir_hari,akhir_bulan,akhir_tahun);
			}
		
		listSenarai = modelLaporan.getSenaraiLaporanSerahan();
		this.context.put("SenaraiSerahan", listSenarai);
		return vm;
	}
	
	
}	
	
	

