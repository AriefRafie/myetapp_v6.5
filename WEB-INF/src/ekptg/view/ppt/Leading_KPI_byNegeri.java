package ekptg.view.ppt;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.util.DateUtil;

import org.apache.log4j.Logger;

import ekptg.model.ppt.FrmPembatalanInternalData;
import ekptg.model.ppt.KPIData;


public class Leading_KPI_byNegeri extends AjaxBasedModule{	
	static Logger myLogger = Logger.getLogger(Leading_KPI_byNegeri.class);
	
	FrmPembatalanInternalData logic = new FrmPembatalanInternalData();
	KPIData logicKPI = new KPIData();
	
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();		
		String vm = ""; 
		String main_command = getParam("command");
		String sub_command = getParam("sub_command");
		String subminor_command = getParam("subminor_command");
		myLogger.info("main_command :"+main_command+";sub_command :"+sub_command);
		
		String paging_action = getParam("action");
		String location = getParam("location");
		String point = getParam("point");		
		
		Vector list_KPI_Penarikan_ByNegeri = null;
		Vector listdepan = null;
		Vector list_kementerian = null;
		Vector list_urusan = null;
		Vector list_status = null;
		Vector nama_user = null;
		Vector list_pejabat = null;
		Vector list_KPI_Penarikan = null;
		Vector list_KPI_Penarikan_Menunggu_MMK= null;
		Vector list_KPI_Penarikan_Menunggu_Bayaran = null;
		Vector list_KPI_Kecekapan_ByNegeri = null;
		Vector senarai_kpisasaran = null;
		
		
		Vector list_KPI_Penarikan_Menunggu_JPPH= null;
		Vector list_KPI_Penarikan_Menunggu_JT= null;
		Vector list_KPI_Penarikan_Menunggu_PBN= null;
		Vector list_KPI_Penarikan_Menunggu_DHDK= null;
		Vector list_KPI_Penarikan_Menunggu_BORANGK= null;
		Vector list_KPI_Penarikan_Menunggu_BAYARAN = null;
		Vector list_KPI_Seksyen8_ByNegeri = null;
		Vector list_KPI_Menunggu_Bantahan_Perintah = null;
		Vector list_KPI_Bantahan_byNegeri = null;
		

		this.context.put("list_KPI_Seksyen8_ByNegeri","");
		this.context.put("list_KPI_Penarikan_Menunggu_BAYARAN","");
		this.context.put("list_KPI_Penarikan_Menunggu_JPPH","");
		this.context.put("list_KPI_Penarikan_Menunggu_JT","");
		this.context.put("list_KPI_Penarikan_Menunggu_PBN","");
		this.context.put("list_KPI_Penarikan_Menunggu_DHDK","");
		this.context.put("list_KPI_Penarikan_Menunggu_BORANGK","");

		this.context.put("senarai_kpisasaran","");	
		this.context.put("list_KPI_Kecekapan_ByNegeri","");	
		this.context.put("list_KPI_Penarikan_ByNegeri","");	
		this.context.put("list_pejabat","");				
		this.context.put("list_kementerian","");
		this.context.put("list_urusan","");
		
		this.context.put("list_status","");
		this.context.put("listdepan","");
		this.context.put("listdepan_size","");		
		this.context.put("jenisUrusan","");
		this.context.put("txdTarikhMula","");
		this.context.put("txdTarikhAkhir", "");
		this.context.put("socPejabat","");
		this.context.put("socDaerah", "");
		this.context.put("list_KPI_Penarikan", "");
		this.context.put("open_KPI", "");
		this.context.put("list_KPI_Penarikan_Menunggu_MMK", "");
		this.context.put("list_KPI_Penarikan_Menunggu_Bayaran", "");
		
		
		context.put("DATEUTIL", new DateUtil());
		this.context.put("Util", new lebah.util.Util());
		
		String bolehsimpan = "";
		String readmode = "";
		
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {			
			bolehsimpan = "yes";
		}
		Vector list_daerahpejabat = null;
		this.context.put("list_daerahpejabat", "");
		
		if(main_command.equals("daerah"))
		{
			if(!getParam("socPejabat").equals(""))
			{
				list_daerahpejabat =logic.list_daerahpejabat(getParam("socPejabat"));
				this.context.put("list_daerahpejabat",list_daerahpejabat);
			}
			
			this.context.put("socPejabat", getParam("socPejabat"));
			this.context.put("jenisUrusan", getParam("jenisUrusan"));
			this.context.put("txdTarikhMula", getParam("txdTarikhMula"));
			this.context.put("txdTarikhAkhir", getParam("txdTarikhAkhir"));
			
			
		}

		else if(main_command.equals("urusan"))
		{
			if(getParam("jenisUrusan").equals("5"))
			{			
					
				
				list_KPI_Kecekapan_ByNegeri =logicKPI.list_KPI_Kecekapan_ByNegeri(getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Kecekapan_ByNegeri",list_KPI_Kecekapan_ByNegeri);			
				
				list_KPI_Penarikan_ByNegeri =logicKPI.list_KPI_Penarikan_ByNegeri(getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_ByNegeri",list_KPI_Penarikan_ByNegeri);
						    
			    list_KPI_Penarikan_Menunggu_MMK =logicKPI.list_KPI_Penarikan_Menunggu_MMK_byNegeri(getParam("jenisUrusan"),"000","16",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_MMK",list_KPI_Penarikan_Menunggu_MMK);
			 
			    list_KPI_Penarikan_Menunggu_Bayaran =logicKPI.list_KPI_Penarikan_Menunggu_Bayaran_byNegeri(getParam("jenisUrusan"),"000","16",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_Bayaran",list_KPI_Penarikan_Menunggu_Bayaran);
			 
			    
			}
			
			if(getParam("jenisUrusan").equals("4"))
			{
							
				 list_KPI_Bantahan_byNegeri =logicKPI.list_KPI_Bantahan_byNegeri(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			     this.context.put("list_KPI_Bantahan_byNegeri",list_KPI_Bantahan_byNegeri);
				
			     list_KPI_Menunggu_Bantahan_Perintah =logicKPI.list_KPI_Menunggu_Bantahan_Perintah_byNegeri(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
				 this.context.put("list_KPI_Menunggu_Bantahan_Perintah",list_KPI_Menunggu_Bantahan_Perintah);			 
				
				
				
			}
			
			if(getParam("jenisUrusan").equals("1"))
			{			
					
			        list_KPI_Seksyen8_ByNegeri =logicKPI.list_KPI_Seksyen8_ByNegeri(getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			        this.context.put("list_KPI_Seksyen8_ByNegeri",list_KPI_Seksyen8_ByNegeri);
				
				    list_KPI_Penarikan_Menunggu_JPPH =logicKPI.list_KPI_Penarikan_Menunggu_JPPH_byNegeri(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
				    this.context.put("list_KPI_Penarikan_Menunggu_JPPH",list_KPI_Penarikan_Menunggu_JPPH);
				 
				    list_KPI_Penarikan_Menunggu_PBN =logicKPI.list_KPI_Penarikan_Menunggu_PBN_byNegeri(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
				    this.context.put("list_KPI_Penarikan_Menunggu_PBN",list_KPI_Penarikan_Menunggu_PBN);
				 
				    list_KPI_Penarikan_Menunggu_DHDK =logicKPI.list_KPI_Penarikan_Menunggu_DHDK_byNegeri(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
				    this.context.put("list_KPI_Penarikan_Menunggu_DHDK",list_KPI_Penarikan_Menunggu_DHDK);
				 
				    list_KPI_Penarikan_Menunggu_JT =logicKPI.list_KPI_Penarikan_Menunggu_JT_byNegeri(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
				    this.context.put("list_KPI_Penarikan_Menunggu_JT",list_KPI_Penarikan_Menunggu_JT);
				 
				    list_KPI_Penarikan_Menunggu_BORANGK =logicKPI.list_KPI_Penarikan_Menunggu_BORANGK_byNegeri(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
				    this.context.put("list_KPI_Penarikan_Menunggu_BORANGK",list_KPI_Penarikan_Menunggu_BORANGK);
				 
				    list_KPI_Penarikan_Menunggu_BAYARAN =logicKPI.list_KPI_Penarikan_Menunggu_BAYARAN_byNegeri(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
				    this.context.put("list_KPI_Penarikan_Menunggu_BAYARAN",list_KPI_Penarikan_Menunggu_BAYARAN);
				 
						   
				    
				    
			  
			    
			}
			
			
			if(!getParam("socPejabat").equals(""))
			{
			list_daerahpejabat =logic.list_daerahpejabat(getParam("socPejabat"));
			this.context.put("list_daerahpejabat",list_daerahpejabat);
			}
						
			this.context.put("jenisUrusan", getParam("jenisUrusan"));
			this.context.put("txdTarikhMula", getParam("txdTarikhMula"));
			this.context.put("txdTarikhAkhir", getParam("txdTarikhAkhir"));
			this.context.put("socPejabat", getParam("socPejabat"));
			this.context.put("socDaerah", getParam("socDaerah"));
			this.context.put("open_KPI", "yes");
			senarai_kpisasaran = logicKPI.senarai_kpisasaran("",getParam("jenisUrusan"),"1",getParam("socPejabat"));
			this.context.put("senarai_kpisasaran",senarai_kpisasaran);
			
			
		}
		
		else if(main_command.equals("kosongkan"))
		{
			this.context.put("jenisUrusan","");
			this.context.put("txdTarikhMula","");
			this.context.put("txdTarikhAkhir", "");
			this.context.put("socPejabat","");
			this.context.put("socDaerah", "");
			
		}
		
				list_urusan = logic.list_urusan();
				this.context.put("list_urusan",list_urusan);
				list_status = logic.list_status();
				this.context.put("list_status",list_status);
				list_pejabat = logic.list_pejabat();
				this.context.put("list_pejabat",list_pejabat);				
				vm = "app/ppt/KPI/leadingKPI_main_byNegeri.jsp";	
		
		    this.context.put("portal_role",(String) session.getAttribute("_portal_role"));
		    this.context.put("negeri_user",(String) session.getAttribute("_ekptg_user_negeri"));	
		    nama_user = logic.nama_user((String) session.getAttribute("_ekptg_user_id"));
		    this.context.put("nama_user",nama_user);
		    this.context.put("location",location);
			this.context.put("point",point);		
            return vm;
     
	}

	


	
	
	

	 
	 
}// close class
