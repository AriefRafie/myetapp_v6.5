package ekptg.view.ppk;


import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.CetakLaporanPindahkeMahkamahTinggiInternalData;


public class PaparanLaporanPindahkeMahkamahTinggi extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(PaparanLaporanPindahkeMahkamahTinggi.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private final String PATH="app/ppk/cetakLaporanPindahkeMahkamahTinggi.jsp";
	private String vm = PATH;
	private static Vector listDataOBNotis = new Vector();
	
	HttpSession session = null;
	String action = null;
	
	CetakLaporanPindahkeMahkamahTinggiInternalData modelLaporan = new CetakLaporanPindahkeMahkamahTinggiInternalData();
	
	public String doTemplate2() throws Exception {		
		Vector listSenarai = new Vector();
		String mula_hari = getParam("mula_hari");
		String mula_bulan = getParam("mula_bulan");
		String mula_tahun = getParam("mula_tahun");
		String akhir_hari = getParam("akhir_hari");
		String akhir_bulan = getParam("akhir_bulan");
		String akhir_tahun = getParam("akhir_tahun");
		String jenis_report = getParam("jenis_report");
		String unit = getParam("unit");
		String daerah = getParam("daerah");
		String negeri = getParam("negeri");
		String tarikh_mula = getParam("mula");
		String tarikh_akhir = getParam("akhir");
		listSenarai = null;
		
		
		if (jenis_report.equals("lebih2jutaunit") )
		{
			modelLaporan.setDataSenaraiSerahanUnit(unit);
			this.context.put("tajuk", " (DISEBABKAN OLEH NILAI HARTA MELEBIHI RM 2 JUTA)");
		}
		else if (jenis_report.equals("lebih2jutaunittahun") )
		{
			modelLaporan.setDataSenaraiSerahanUnitTahun(unit, mula_tahun);
			this.context.put("tajuk", " (DISEBABKAN OLEH NILAI HARTA MELEBIHI RM 2 JUTA)");
		}
		else if (jenis_report.equals("lebih2jutaunitselangmasa") )
		{
			modelLaporan.setDataSenaraiSerahanUnitSelangmasa(unit, tarikh_mula, tarikh_akhir);
			this.context.put("tajuk", " (DISEBABKAN OLEH NILAI HARTA MELEBIHI RM 2 JUTA)");
		}
		else if (jenis_report.equals("lebih2jutaunitbulan") )
		{
			modelLaporan.setDataSenaraiSerahanUnitBulan(unit, mula_bulan, mula_tahun);
			this.context.put("tajuk", " (DISEBABKAN OLEH NILAI HARTA MELEBIHI RM 2 JUTA)");
		}
		else if (jenis_report.equals("lebih2jutanegeri") )
		{
			modelLaporan.setDataSenaraiSerahanNegeri(negeri);
			this.context.put("tajuk", " (DISEBABKAN OLEH NILAI HARTA MELEBIHI RM 2 JUTA)");
		}
		else if (jenis_report.equals("lebih2jutanegeritahun") )
		{
			modelLaporan.setDataSenaraiSerahanNegeriTahun(negeri, mula_tahun);
			this.context.put("tajuk", " (DISEBABKAN OLEH NILAI HARTA MELEBIHI RM 2 JUTA)");
		}
		else if (jenis_report.equals("lebih2jutanegeriselangmasa") )
		{
			modelLaporan.setDataSenaraiSerahanNegeriSelangmasa(negeri, tarikh_mula, tarikh_akhir);
			this.context.put("tajuk", " (DISEBABKAN OLEH NILAI HARTA MELEBIHI RM 2 JUTA)");
			
		}
		else if (jenis_report.equals("lebih2jutanegeribulan") )
		{
			modelLaporan.setDataSenaraiSerahanNegeriBulan(negeri, mula_bulan, mula_tahun);
			this.context.put("tajuk", " (DISEBABKAN OLEH NILAI HARTA MELEBIHI RM 2 JUTA)");
		}
		else if (jenis_report.equals("lebih2jutadaerah") )
		{
			modelLaporan.setDataSenaraiSerahanDaerah(daerah);
			this.context.put("tajuk", " (DISEBABKAN OLEH NILAI HARTA MELEBIHI RM 2 JUTA)");
		}
		else if (jenis_report.equals("lebih2jutadaerahtahun") )
		{
			modelLaporan.setDataSenaraiSerahanDaerahTahun(daerah, mula_tahun);
			this.context.put("tajuk", " (DISEBABKAN OLEH NILAI HARTA MELEBIHI RM 2 JUTA)");
		}
		else if (jenis_report.equals("lebih2jutadaerahselangmasa") )
		{
			modelLaporan.setDataSenaraiSerahanDaerahSelangmasa(daerah, tarikh_mula, tarikh_akhir);
			this.context.put("tajuk", " (DISEBABKAN OLEH NILAI HARTA MELEBIHI RM 2 JUTA)");
		}
		else if (jenis_report.equals("lebih2jutadaerahbulan") )
		{
			modelLaporan.setDataSenaraiSerahanDaerahBulan(daerah, mula_bulan, mula_tahun);
			this.context.put("tajuk", " (DISEBABKAN OLEH NILAI HARTA MELEBIHI RM 2 JUTA)");
		}
		else if (jenis_report.equals("wasiatunit") )
		{
			modelLaporan.setDataSenaraiWasiatSerahanUnit(unit);
			this.context.put("tajuk", " (DISEBABKAN TERDAPAT WASIAT)");
		}
		
		else if (jenis_report.equals("wasiatunittahun") )
		{
			modelLaporan.setDataSenaraiWasiatSerahanUnitTahun(unit, mula_tahun);
			this.context.put("tajuk", " (DISEBABKAN TERDAPAT WASIAT)");
		}
		else if (jenis_report.equals("wasiatunitselangmasa") )
		{
			modelLaporan.setDataSenaraiWasiatSerahanUnitSelangmasa(unit, tarikh_mula, tarikh_akhir);
			this.context.put("tajuk", " (DISEBABKAN TERDAPAT WASIAT)");

		}
		else if (jenis_report.equals("wasiatunitbulan") )
		{
			modelLaporan.setDataSenaraiWasiatSerahanUnitBulan(unit, mula_bulan, mula_tahun);
			this.context.put("tajuk", " (DISEBABKAN TERDAPAT WASIAT)");
		}
		else if (jenis_report.equals("wasiatnegeri") )
		{
			modelLaporan.setDataSenaraiWasiatSerahanNegeri(negeri);
			this.context.put("tajuk", " (DISEBABKAN TERDAPAT WASIAT)");
		}
		else if (jenis_report.equals("wasiatnegeritahun") )
		{
			modelLaporan.setDataSenaraiWasiatSerahanNegeriTahun(negeri, mula_tahun);
			this.context.put("tajuk", " (DISEBABKAN TERDAPAT WASIAT)");
		}
		else if (jenis_report.equals("wasiatnegeriselangmasa") )
		{
			modelLaporan.setDataSenaraiWasiatSerahanNegeriSelangmasa(negeri, tarikh_mula, tarikh_akhir);
			this.context.put("tajuk", " (DISEBABKAN TERDAPAT WASIAT)");
		}
		else if (jenis_report.equals("wasiatnegeribulan") )
		{
			modelLaporan.setDataSenaraiWasiatSerahanNegeriBulan(negeri, mula_bulan, mula_tahun);
			this.context.put("tajuk", " (DISEBABKAN TERDAPAT WASIAT)");
		}
		else if (jenis_report.equals("wasiatdaerah") )
		{
			modelLaporan.setDataSenaraiWasiatSerahanDaerah(daerah);
			this.context.put("tajuk", " (DISEBABKAN TERDAPAT WASIAT)");
		}
		else if (jenis_report.equals("wasiatdaerahtahun") )
		{
			modelLaporan.setDataSenaraiWasiatSerahanDaerahTahun(daerah, mula_tahun);
			this.context.put("tajuk", " (DISEBABKAN TERDAPAT WASIAT)");
		}
		else if (jenis_report.equals("wasiatdaerahselangmasa") )
		{
			modelLaporan.setDataSenaraiWasiatSerahanDaerahSelangmasa(daerah, tarikh_mula, tarikh_akhir);
			this.context.put("tajuk", " (DISEBABKAN TERDAPAT WASIAT)");
		}
		else if (jenis_report.equals("wasiatdaerahbulan") )
		{
			modelLaporan.setDataSenaraiWasiatSerahanDaerahBulan(daerah, mula_bulan, mula_tahun);
			this.context.put("tajuk", " (DISEBABKAN TERDAPAT WASIAT)");
		}
		
		
		listSenarai = modelLaporan.getSenaraiLaporanSerahan();
		this.context.put("SenaraiSerahan", listSenarai);
		return vm;
	}
	
	
}	
	
	

