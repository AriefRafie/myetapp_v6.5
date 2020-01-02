package ekptg.view.ppk;


import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.CetakLaporanSerahanBaitulmalPBNInternalData1;


public class PaparanLaporanSerahanBaitulmalPBN1 extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(PaparanLaporanSerahanBaitulmalPBN.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private final String PATH="app/ppk/cetakLaporanSerahanBaitulmalPBN1.jsp";
	private String vm = PATH;
	private final String PATH2="app/ppk/cetakLaporanSerahanBaitulmalPBN2.jsp";
	private String vm2 = PATH2;
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
		String unit = getParam("unit");
		String daerah = getParam("daerah");
		String negeri = getParam("negeri");
		String tarikh_mula = getParam("mula");
		String tarikh_akhir = getParam("akhir");
		
		listSenarai = null;
		if (jenis_laporan.equals("BaitulmalUNIT"))
		{
			modelLaporan.setDataSenaraiSerahanUnit(unit);
		}
		else if (jenis_laporan.equals("BaitulmalUNITtahun"))
		{
			modelLaporan.setDataSenaraiSerahanUnittahun(unit, mula_tahun);
		}
		else if (jenis_laporan.equals("BaitulmalUNITselangmasa"))
		{
			modelLaporan.setDataSenaraiSerahanUnitselangmasa(unit, tarikh_mula, tarikh_akhir);
		}
		else if (jenis_laporan.equals("BaitulmalUNITbulan"))
		{
			modelLaporan.setDataSenaraiSerahanUnitbulan(unit, mula_bulan, mula_tahun);
		}
		else if (jenis_laporan.equals("BaitulmalNegeri"))
		{
			modelLaporan.setDataSenaraiSerahannegeri(negeri);
		}
		else if (jenis_laporan.equals("BaitulmalNegeritahun"))
		{
			modelLaporan.setDataSenaraiSerahanNegeritahun(negeri, mula_tahun);
		}
		else if (jenis_laporan.equals("BaitulmalNegeriselangmasa"))
		{
			modelLaporan.setDataSenaraiSerahanNegeriselangmasa(negeri, tarikh_mula, tarikh_akhir);
		}
		else if (jenis_laporan.equals("BaitulmalNegeribulan"))
		{
			modelLaporan.setDataSenaraiSerahanNegeribulan(negeri, mula_bulan, mula_tahun);
		}
		
		else if (jenis_laporan.equals("BaitulmalDAERAH"))
		{
			modelLaporan.setDataSenaraiSerahanDaerah(daerah);
		}
		else if (jenis_laporan.equals("BaitulmalDAERAHtahun"))
		{
			modelLaporan.setDataSenaraiSerahanDaerahtahun(daerah, mula_tahun);
		}
		else if (jenis_laporan.equals("BaitulmalDAERAHselangmasa"))
		{
			modelLaporan.setDataSenaraiSerahanDaerahselangmasa(daerah, tarikh_mula, tarikh_akhir);
		}
		else if (jenis_laporan.equals("BaitulmalDAERAHbulan"))
		{
			modelLaporan.setDataSenaraiSerahanDaerahbulan(daerah, mula_bulan, mula_tahun);
		}
		else if (jenis_laporan.equals("PBNunit"))
		{
			modelLaporan.setDataSenaraiSerahanPBNUnit(unit);
			vm = vm2;
		}
		else if (jenis_laporan.equals("PBNunittahun"))
		{
			modelLaporan.setDataSenaraiSerahanPBNUnittahun(unit, mula_tahun);
			vm = vm2;
		}
		else if (jenis_laporan.equals("PBNunitselangmasa"))
		{
			modelLaporan.setDataSenaraiSerahanPBNUnitselangmasa(unit, tarikh_mula, tarikh_akhir);
			vm = vm2;
		}
		else if (jenis_laporan.equals("PBNunitbulan"))
		{
			modelLaporan.setDataSenaraiSerahanPBNUnitbulan(unit, mula_bulan, mula_tahun);
			vm = vm2;
		}
		
		else if (jenis_laporan.equals("PBNnegeri"))
		{
			modelLaporan.setDataSenaraiSerahanPBNnegeri(negeri);
			vm = vm2;
		}
		else if (jenis_laporan.equals("PBNnegeritahun"))
		{
			modelLaporan.setDataSenaraiSerahanPBNnegeritahun(negeri, mula_tahun);
			vm = vm2;
		}
		else if (jenis_laporan.equals("PBNnegeriselangmasa"))
		{
			modelLaporan.setDataSenaraiSerahanPBNnegeriselangmasa(negeri, tarikh_mula, tarikh_akhir);
			vm = vm2;
		}
		else if (jenis_laporan.equals("PBNnegeribulan"))
		{
			modelLaporan.setDataSenaraiSerahanPBNnegeribulan(negeri, mula_bulan, mula_tahun);
			vm = vm2;
		}
		else if (jenis_laporan.equals("PBNdaerah"))
		{
			modelLaporan.setDataSenaraiSerahanPBNdaerah(daerah);
			vm = vm2;
		}
		else if (jenis_laporan.equals("PBNdaerahtahun"))
		{
			modelLaporan.setDataSenaraiSerahanPBNdaerahtahun(daerah, mula_tahun);
			vm = vm2;
		}
		else if (jenis_laporan.equals("PBNdaerahselangmasa"))
		{
			modelLaporan.setDataSenaraiSerahanPBNdaerahselangmasa(daerah, tarikh_mula, tarikh_akhir);
			vm = vm2;
		}
		else if (jenis_laporan.equals("PBNdaerahbulan"))
		{
			modelLaporan.setDataSenaraiSerahanPBNdaerahbulan(daerah, mula_bulan, mula_tahun);
			vm = vm2;
		}
		
		
		listSenarai = modelLaporan.getSenaraiLaporanSerahan();
		this.context.put("SenaraiSerahan", listSenarai);
		return vm;
	}
	
	
}	
	
	

