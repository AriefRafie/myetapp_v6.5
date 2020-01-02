package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.CetakLaporanPrestasi;
import ekptg.model.utils.ILaporan;
import ekptg.model.utils.LaporanBean;

public class LaporanPrestasiByUnitTahun extends AjaxBasedModule {
	/**
	 * 
	 */
	//private final String PATH = "app/ppk/";
	private final String PATH = "app/ppk/laporan/prestasi/";
 	private ILaporan iLaporan = null;  
	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.ppk.LaporanPrestasiByUnitTahun.class);
	CetakLaporanPrestasi modelLaporan = new CetakLaporanPrestasi();
	private Vector<Hashtable<String,String>> listPrestasiDetail = null;
			
	public String doTemplate2() throws Exception {
	    Vector listPrestasi = new Vector();
		String vm = PATH+"LaporanPrestasiByUnitTahun.jsp";
		//BULAN_DARI="+bulan1+"&BULAN_HINGGA="+bulan2+"&TAHUN_DARI="+tahun1+"&TAHUN_HINGGA="+tahun2+"&ID_NEGERI="+negeri+"&ID_UNIT="+unit;
		String TAHUN= getParam("TAHUN");
		String ID_NEGERI = getParam("NEGERI");
		String ID_UNIT = getParam("UNIT");
		String laporan = "DAERAH";
		String namaPejabat = modelLaporan.getNamaUnitStr(ID_UNIT);
		listPrestasi = null;
		//modelLaporan.setDataPrestasibyUnitTahun(TAHUN,ID_NEGERI,ID_UNIT);
		//listPrestasi = modelLaporan.getSenaraiPrestasi();
		//modelLaporan.setDataNamaUnit(ID_UNIT);
		//namaUnit = modelLaporan.getNamaUnit();
		if(ID_NEGERI.equals("0")){	
			//listPrestasi = modelLaporan.getDataPrestasiBulan("",TAHUN);
			listPrestasi = modelLaporan.getDataPrestasiMengikutNegeri("tahun",0,0,TAHUN,TAHUN);
			laporan = "NEGERI";
			
		}else{
			//if(!ID_NEGERI.equals("0") && ID_UNIT.equals("0")){			
			if(ID_UNIT.equals("0")){			
				//listPrestasi = modelLaporan.getDataPrestasiNegeri("",TAHUN,ID_NEGERI);
				listPrestasi = modelLaporan.getDataPrestasiMengikutUnit("tahun",0,0,TAHUN,TAHUN,ID_NEGERI);
				laporan = "UNIT";
				namaPejabat = "SEMUA";
			}else{
				listPrestasi = modelLaporan.getDataPrestasiMengikutDaerah("tahun","",TAHUN,ID_NEGERI,ID_UNIT);			
			}
			
		}
		//listPrestasiDetail.clear();
		CetakLaporanPrestasi clp = new CetakLaporanPrestasi();
		String command = getParam("command");
		String idSubUrusan = getParam("idsuburusan");

		if (command.equals("details")){	
			listPrestasiDetail = new Vector<Hashtable<String,String>>();
			ID_UNIT = getParam("idrujukan");
			if(laporan.equals("DAERAH")){
				listPrestasiDetail = clp.getSenaraiMengikutDaerah("tahun", idSubUrusan,0,0, TAHUN, "", ID_UNIT);				
			}else if (laporan.equals("UNIT")){
				listPrestasiDetail = clp.getSenaraiMengikutUnit("tahun", idSubUrusan,0,0, TAHUN, "", ID_UNIT);
			}else if (laporan.equals("NEGERI")){
				ID_NEGERI = getParam("idrujukan");
				listPrestasiDetail = clp.getSenaraiMengikutNegeri("tahun", idSubUrusan,0,0, TAHUN, "", ID_NEGERI);
				
			}
			context.put("listPrestasiDetail",listPrestasiDetail);			
			
		}
		
		context.put("laporan",laporan);
		context.put("listPrestasi",listPrestasi);
		context.put("TAHUN",TAHUN);
		context.put("ID_NEGERI",ID_NEGERI);
		context.put("ID_UNIT ",ID_UNIT );
		//context.put("namaUnit",modelLaporan.getNamaUnit() );
		context.put("NAMA_PEJABAT",namaPejabat);
		return vm;
		
	}
	
	private ILaporan getILaporan(){
		if(iLaporan == null)
			iLaporan = new LaporanBean();
		return iLaporan;
		
	}	
	
	
}
