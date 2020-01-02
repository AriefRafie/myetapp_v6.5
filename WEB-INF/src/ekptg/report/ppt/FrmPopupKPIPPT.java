/**
 * 
 */
package ekptg.report.ppt;





import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.util.DateUtil;

import org.apache.log4j.Logger;

import ekptg.model.ppt.FrmPembatalanInternalData;
import ekptg.model.ppt.KPIData;

/**
 * @author Razman
 *
 */
public class FrmPopupKPIPPT extends AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(FrmPopupKPIPPT.class);
	private static final long serialVersionUID = 1L;
	FrmPembatalanInternalData logic = new FrmPembatalanInternalData();
	KPIData logicKPI = new KPIData();
	
	
	@Override
	public String doTemplate2() throws Exception {
		
		
		HttpSession session = request.getSession();		
		String vm = "";
		//String main_command = getParam("command");			
		String tempat = getParam("tempat");		
		String jenisUrusan = getParam("jenisUrusan");
		String socDaerah = getParam("socDaerah");
		String socPejabat = getParam("socPejabat");
		String txdTarikhMula = getParam("txdTarikhMula");
		String txdTarikhAkhir = getParam("txdTarikhAkhir");
		String kategori = getParam("kategori");
		String tajuk = getParam("tajuk");
		
		
		double range1 = Double.parseDouble(getParam("range1"));
		double range2 = Double.parseDouble(getParam("range2"));
		
		
		Vector list_KPI_POPUP = null;
		this.context.put("list_KPI_POPUP","");
		
		Vector list_status = null;
		this.context.put("list_status","");	
		
		
		context.put("DATEUTIL", new DateUtil());
		this.context.put("Util", new lebah.util.Util());
		
		myLogger.info("tempat:"+tempat);
	 	
		if(tempat.equals("1"))
		{
	    list_KPI_POPUP = logicKPI.list_KPI_Penarikan_Menunggu_MMK(jenisUrusan,socDaerah,socPejabat,txdTarikhMula,txdTarikhAkhir);
        this.context.put("list_KPI_POPUP",list_KPI_POPUP);			 
		}
		
		if(tempat.equals("2"))
		{
	    list_KPI_POPUP = logicKPI.list_KPI_Penarikan_Menunggu_Bayaran(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
        this.context.put("list_KPI_POPUP",list_KPI_POPUP);			 
		}
		
		if(tempat.equals("3"))
		{
	    list_KPI_POPUP = logicKPI.list_KPI_Menunggu_Bantahan_Perintah(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
        this.context.put("list_KPI_POPUP",list_KPI_POPUP);			 
		}
		
		
		if(tempat.equals("4"))
		{
		list_KPI_POPUP =logicKPI.list_KPI_Penarikan_Menunggu_JPPH(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
	    this.context.put("list_KPI_POPUP",list_KPI_POPUP);
			 		 
		}
		
		if(tempat.equals("5"))
		{
		list_KPI_POPUP =logicKPI.list_KPI_Penarikan_Menunggu_JT(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
	    this.context.put("list_KPI_POPUP",list_KPI_POPUP);
			 		 
		}
		
		if(tempat.equals("6"))
		{
		list_KPI_POPUP =logicKPI.list_KPI_Penarikan_Menunggu_PBN(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
	    this.context.put("list_KPI_POPUP",list_KPI_POPUP);
			 		 
		}
		
		if(tempat.equals("7"))
		{
		list_KPI_POPUP =logicKPI.list_KPI_Penarikan_Menunggu_DHDK(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
	    this.context.put("list_KPI_POPUP",list_KPI_POPUP);
			 		 
		}
		
		if(tempat.equals("8"))
		{
		list_KPI_POPUP =logicKPI.list_KPI_Penarikan_Menunggu_BAYARAN(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
	    this.context.put("list_KPI_POPUP",list_KPI_POPUP);
			 		 
		}
		
		if(tempat.equals("9"))
		{
		list_KPI_POPUP =logicKPI.list_KPI_Penarikan_Menunggu_BORANGK(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
	    this.context.put("list_KPI_POPUP",list_KPI_POPUP);
			 		 
		}
		
		
		/*
		        list_KPI_Penarikan_Menunggu_JPPH =logicKPI.list_KPI_Penarikan_Menunggu_JPPH(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_JPPH",list_KPI_Penarikan_Menunggu_JPPH);
			 
			    list_KPI_Penarikan_Menunggu_JT =logicKPI.list_KPI_Penarikan_Menunggu_JT(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_JT",list_KPI_Penarikan_Menunggu_JT);
			 
			    list_KPI_Penarikan_Menunggu_PBN =logicKPI.list_KPI_Penarikan_Menunggu_PBN(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_PBN",list_KPI_Penarikan_Menunggu_PBN);
			 
			    list_KPI_Penarikan_Menunggu_DHDK =logicKPI.list_KPI_Penarikan_Menunggu_DHDK(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_DHDK",list_KPI_Penarikan_Menunggu_DHDK);
			 		  
			    list_KPI_Penarikan_Menunggu_BAYARAN =logicKPI.list_KPI_Penarikan_Menunggu_BAYARAN(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_BAYARAN",list_KPI_Penarikan_Menunggu_BAYARAN);
			 
			    list_KPI_Penarikan_Menunggu_BORANGK =logicKPI.list_KPI_Penarikan_Menunggu_BORANGK(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_BORANGK",list_KPI_Penarikan_Menunggu_BORANGK);
			 
		 */
		
		
		this.context.put("range1",range1);
		this.context.put("range2",range2);
		this.context.put("kategori",kategori);
		this.context.put("tempat",tempat);
		this.context.put("tajuk",tajuk);
		
		list_status = logic.list_status();
		this.context.put("list_status",list_status);
		
		vm = "app/ppt/frmPopupKPI_NOFAIL.jsp";		
		
		return vm;
	}
}
