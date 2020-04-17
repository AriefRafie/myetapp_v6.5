package ekptg.view.ppk;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.CetakLaporanPrestasi;

public class LaporanPrestasiByNegeriBulanan extends AjaxBasedModule {
	/**
	 * 
	 */
	private final String PATH = "app/ppk/";
	private final String ID_SEKSYEN = "2";
 	//private ILaporan iLaporan = null;  
	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.ppk.LaporanPrestasiByNegeriBulanan.class);
	CetakLaporanPrestasi modelLaporan = new CetakLaporanPrestasi();
	
	public String doTemplate2() throws Exception {
	   
	    
	    Vector listUnit = new Vector();
	    Vector listPrestasi = new Vector();
	    Vector listPrestasiDetail = new Vector();
	    Vector listPrestasiDetail2 = new Vector();
		String vm = PATH+"LaporanPrestasiByNegeriBulanan.jsp";
		//BULAN_DARI="+bulan1+"&BULAN_HINGGA="+bulan2+"&TAHUN_DARI="+tahun1+"&TAHUN_HINGGA="+tahun2+"&ID_NEGERI="+negeri+"&ID_UNIT="+unit;
		String BULAN = getParam("BULAN_DARI");	
		String TAHUN = getParam("TAHUN_DARI");
		String ID_NEGERI = getParam("ID_NEGERI");
		String ID_UNIT = getParam("ID_UNIT");
		String command = getParam("command");
		String ID_DAERAH = getParam("ID_DAERAH");
		System.out.println("ID_DAERAH = "+ID_DAERAH);
		HttpSession session = this.request.getSession();
		
		listPrestasi.clear();
		listUnit.clear();
		//CetakLaporanPrestasi.setDataUnit(BULAN,TAHUN,ID_NEGERI,ID_UNIT);
		//listUnit =  CetakLaporanPrestasi.getSenaraiPrestasi();
		CetakLaporanPrestasi.setDataPrestasibyNegeriBulan(BULAN,TAHUN,ID_NEGERI,ID_UNIT);
		listPrestasi = CetakLaporanPrestasi.getSenaraiPrestasi();
		listPrestasiDetail.clear();
		if (command.equals("details"))
		{	//listPrestasiDetail = null;
			listPrestasiDetail.clear();
			System.out.println("details");
			String usid = (String) session.getAttribute("ekptg_user_id");
			CetakLaporanPrestasi.setList(usid, BULAN,TAHUN,ID_DAERAH);
			listPrestasiDetail = CetakLaporanPrestasi.getList();
			context.put("seksyen", "8");
			context.put("listPrestasiDetail",listPrestasiDetail);
			
			
		}
		if (command.equals("details2"))
		{	//listPrestasiDetail = null;
			listPrestasiDetail2.clear();
			//System.out.println("details");
			String usid = (String) session.getAttribute("_ekptg_user_id");
			CetakLaporanPrestasi.setList2(usid, BULAN,TAHUN,ID_DAERAH);
			listPrestasiDetail = CetakLaporanPrestasi.getList();
			context.put("seksyen", "17");
			context.put("listPrestasiDetail",listPrestasiDetail);
			 
			
		}
		context.put("listPrestasi",listPrestasi);
		context.put("BULAN_DARI",BULAN);
		context.put("TAHUN_DARI",TAHUN);
		context.put("ID_NEGERI",ID_NEGERI);
		//context.put("ID_UNIT ",ID_UNIT );
		context.put("listPrestasiDetail_size",listPrestasiDetail.size());
		return vm;
		
	}
	
		
	
	

	
	public String getBulanSebelum(int i){
		String m="";
		String[]names = {"12", "01", "02","03", "04", "05","06", "07", "08","09", "10", "11"};
		m = names[i-1];
		return m;
	}
	
	public String getTahunSebelum(int bulan,int i){
		String m="";
		if(bulan==1) 
			m = ""+(i-1);
		else
			m = ""+i;
		return m;
	}
	
	public String getBulan(int i){
		String m="";
		String[]names = {"JANUARI", "FBBRUARI", "MAC","APRIL", "MEI", "JUN","JULAI", "OGOS", "SEPTEMBER","OKTOBER", "NOVEMBER", "DISEMBER"};
		m = names[i-1];
		return m;
	}
}