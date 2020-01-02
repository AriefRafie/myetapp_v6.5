package ekptg.view.ppk;
 
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.CetakLaporanPrestasi;

public class LaporanPrestasiByUnitBulanan extends AjaxBasedModule {
	/**
	 * 
	 */
	private final String PATH = "app/ppk/";
	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.ppk.LaporanPrestasiByUnitBulanan.class);
	CetakLaporanPrestasi modelLaporan = new CetakLaporanPrestasi();
	Vector<Hashtable<String,String>> permohonans = null;
	
	public String doTemplate2() throws Exception {   
		System.out.println("LaporanPrestasiByUnitBulanan1234567890:doTemplate2");
	    Vector listPrestasi = new Vector();
	    Vector<Hashtable<String,String>> listPrestasiDetail = new Vector<Hashtable<String,String>>();
	    Vector listPrestasiDetail2 = new Vector();
		String vm = PATH+"LaporanPrestasiByUnitBulanan.jsp";
		//BULAN_DARI="+bulan1+"&BULAN_HINGGA="+bulan2+"&TAHUN_DARI="+tahun1+"&TAHUN_HINGGA="+tahun2+"&ID_NEGERI="+negeri+"&ID_UNIT="+unit;
		String BULAN = getParam("BULAN_DARI");	
		String TAHUN = getParam("TAHUN_DARI");
		String ID_NEGERI = getParam("ID_NEGERI");
		String ID_UNIT = getParam("ID_UNIT")==null? getParam("idrujukan"):getParam("ID_UNIT");
		String command = getParam("command");
		String ID_DAERAH = getParam("ID_DAERAH");
		String idSuburusan = getParam("idsuburusan");
		HttpSession session = this.request.getSession();
		String laporan = "DAERAH";
		
		listPrestasi.clear();
		if(ID_NEGERI.equals("0")){	
			System.out.println("ID_NEGERI = 0 ");
			vm = PATH+"laporan/prestasi/LaporanPrestasiByUnitBulanan.jsp";
			listPrestasi = modelLaporan.getDataPrestasiMengikutNegeri("bulan",Integer.parseInt(BULAN),Integer.parseInt(BULAN),TAHUN,TAHUN);
			laporan = "NEGERI";
		
		}else{	
		//}else if(ID_UNIT.equals("0") || !ID_NEGERI.equals("0")){
			if(ID_UNIT.equals("0")){
				System.out.println("ID_NEGERI = 01 ");
				vm = PATH+"laporan/prestasi/LaporanPrestasiByUnitBulanan.jsp";
				listPrestasi = modelLaporan.getDataPrestasiNegeriBulan(BULAN,TAHUN,ID_NEGERI);
				laporan = "UNIT";
			
			}else{
				System.out.println("ID_NEGERI = 02 ");
				CetakLaporanPrestasi.setDataPrestasibyUnitBulan(BULAN,TAHUN,ID_NEGERI,ID_UNIT);		
				listPrestasi = CetakLaporanPrestasi.getSenaraiPrestasi();
				
			}
		}
		System.out.println("laporan="+laporan);
		System.out.println("command="+command);
		
		context.put("laporan",laporan);
		String usid = (String) session.getAttribute("ekptg_user_id");

		listPrestasiDetail.clear();
		CetakLaporanPrestasi clp = new CetakLaporanPrestasi();
		if (command.equals("details")){	
			ID_UNIT = getParam("idrujukan");
			//listPrestasiDetail = null;
			listPrestasiDetail.clear();
			System.out.println("details");
			//String usid = (String) session.getAttribute("_ekptg_user_id");
			//CetakLaporanPrestasi.setList(usid, BULAN,TAHUN,"");
			if(laporan.equals("DAERAH")){
				System.out.println("*****Read Here1****");
				CetakLaporanPrestasi.setList(usid, BULAN,TAHUN,ID_DAERAH);
				listPrestasiDetail = CetakLaporanPrestasi.getList();
				
			}else if (laporan.equals("UNIT")){
				System.out.println("*****Read Here1UNIT****");
				listPrestasiDetail = clp.getSenaraiMengikutUnit("bulan","59",Integer.parseInt(BULAN),0,TAHUN,"",ID_UNIT);
			}else if (laporan.equals("NEGERI")){
				System.out.println("*****Read Here1NEGERI****");
				ID_NEGERI = getParam("idrujukan");
				listPrestasiDetail = clp.getSenaraiMengikutNegeri("bulan","59",Integer.parseInt(BULAN),0,TAHUN,"",ID_NEGERI);
				
			}
			context.put("listPrestasiDetail",listPrestasiDetail);
			context.put("seksyen", "8");
			
		}
		if (command.equals("details2")){	
			//listPrestasiDetail = null;
			listPrestasiDetail2.clear();
			if(laporan.equals("DAERAH")){
				System.out.println("Read Here2****");
				CetakLaporanPrestasi.setList2(usid, BULAN,TAHUN,ID_DAERAH);
				listPrestasiDetail = CetakLaporanPrestasi.getList();
			
			}else if (laporan.equals("UNIT")){
				listPrestasiDetail = clp.getSenaraiMengikutUnit("bulan","60",Integer.parseInt(BULAN),0,TAHUN,"",ID_UNIT);
			}else if (laporan.equals("NEGERI")){
				ID_NEGERI = getParam("idrujukan");
				listPrestasiDetail = clp.getSenaraiMengikutNegeri("bulan","60",Integer.parseInt(BULAN),0,TAHUN,"",ID_NEGERI);
			}
			context.put("listPrestasiDetail",listPrestasiDetail);
			context.put("seksyen", "17"); 
			
		} 
		
  		String submit = getParam("command_");
  		String idUrusan = getParam("idurusan");
		if(submit.equals("terperinci")){
			String tahun = getParam("tahun");
			String bulan = getParam("bulan");
			//permohonans = getILaporan().getSenarai(ID_NEGERI,ID_UNIT,tahun,bulan,idUrusan);
		    
	    }
		
	    context.put("ListPermohonan", permohonans);
		context.put("listPrestasi",listPrestasi);
		context.put("BULAN_DARI",BULAN);
		context.put("TAHUN_DARI",TAHUN);
		context.put("ID_NEGERI",ID_NEGERI);
		context.put("ID_UNIT",ID_UNIT );
		context.put("listPrestasiDetail_size",listPrestasiDetail.size());
		
		return vm;
		
	}
		
	
}
