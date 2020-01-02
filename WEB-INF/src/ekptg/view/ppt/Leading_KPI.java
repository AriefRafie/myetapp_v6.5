package ekptg.view.ppt;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.util.DateUtil;

import org.apache.log4j.Logger;

import ekptg.model.ppt.FrmPembatalanInternalData;
import ekptg.model.ppt.KPIData;


public class Leading_KPI extends AjaxBasedModule{	
	static Logger myLogger = Logger.getLogger(Leading_KPI.class);
	
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
		
		Vector listdepan = null;
		Vector list_kementerian = null;
		Vector list_urusan = null;
		Vector list_status = null;
		Vector nama_user = null;
		Vector list_pejabat = null;
		Vector list_KPI_Penarikan = null;
		Vector list_KPI_Penarikan_Menunggu_MMK= null;
		Vector list_KPI_Penarikan_Menunggu_Bayaran = null;
		Vector senarai_kpisasaran = null;
		Vector list_KPI_Seksyen8 = null;
		
		Vector list_KPI_Penarikan_Menunggu_JPPH= null;
		Vector list_KPI_Penarikan_Menunggu_JT= null;
		Vector list_KPI_Penarikan_Menunggu_PBN= null;
		Vector list_KPI_Penarikan_Menunggu_DHDK= null;
		Vector list_KPI_Penarikan_Menunggu_BORANGK= null;
		Vector list_KPI_Penarikan_Menunggu_BAYARAN = null;
		Vector list_KPI_Bantahan = null;
		Vector list_KPI_Menunggu_Bantahan_Perintah = null;
		

		this.context.put("list_KPI_Menunggu_Bantahan_Perintah","");
		this.context.put("list_KPI_Bantahan","");
		this.context.put("list_KPI_Penarikan_Menunggu_BAYARAN","");
		this.context.put("list_KPI_Penarikan_Menunggu_JPPH","");
		this.context.put("list_KPI_Penarikan_Menunggu_JT","");
		this.context.put("list_KPI_Penarikan_Menunggu_PBN","");
		this.context.put("list_KPI_Penarikan_Menunggu_DHDK","");
		this.context.put("list_KPI_Penarikan_Menunggu_BORANGK","");
		
		this.context.put("senarai_kpisasaran","");
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
		this.context.put("list_KPI_Seksyen8", "");
		this.context.put("open_KPI", "");
		this.context.put("list_KPI_Penarikan_Menunggu_MMK", "");
		this.context.put("list_KPI_Penarikan_Menunggu_Bayaran", "");
		this.context.put("open_penetapan","");
		
		
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
		else if(main_command.equals("kosongkan"))
		{
			this.context.put("jenisUrusan","");
			this.context.put("txdTarikhMula","");
			this.context.put("txdTarikhAkhir", "");
			this.context.put("socPejabat","");
			this.context.put("socDaerah", "");
			
		}
		else if(main_command.equals("urusan"))
		{
			if(getParam("jenisUrusan").equals("5"))
			{			
				list_KPI_Penarikan =logicKPI.list_KPI_Penarikan(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan",list_KPI_Penarikan);
			    
			    list_KPI_Penarikan_Menunggu_MMK =logicKPI.list_KPI_Penarikan_Menunggu_MMK(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_MMK",list_KPI_Penarikan_Menunggu_MMK);
			 
			    list_KPI_Penarikan_Menunggu_Bayaran =logicKPI.list_KPI_Penarikan_Menunggu_Bayaran(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_Bayaran",list_KPI_Penarikan_Menunggu_Bayaran);
			  
			}
			
			if(getParam("jenisUrusan").equals("4"))
			{			
				list_KPI_Bantahan =logicKPI.list_KPI_Bantahan(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Bantahan",list_KPI_Bantahan);
			    
			    list_KPI_Menunggu_Bantahan_Perintah =logicKPI.list_KPI_Menunggu_Bantahan_Perintah(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Menunggu_Bantahan_Perintah",list_KPI_Menunggu_Bantahan_Perintah);			 
			}
			
			
			if(getParam("jenisUrusan").equals("1"))
			{			
				list_KPI_Seksyen8 =logicKPI.list_KPI_Seksyen8(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Seksyen8",list_KPI_Seksyen8);
			    
			    list_KPI_Penarikan_Menunggu_JPPH =logicKPI.list_KPI_Penarikan_Menunggu_JPPH(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_JPPH",list_KPI_Penarikan_Menunggu_JPPH);
			 
			    list_KPI_Penarikan_Menunggu_PBN =logicKPI.list_KPI_Penarikan_Menunggu_PBN(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_PBN",list_KPI_Penarikan_Menunggu_PBN);
			 
			    list_KPI_Penarikan_Menunggu_DHDK =logicKPI.list_KPI_Penarikan_Menunggu_DHDK(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_DHDK",list_KPI_Penarikan_Menunggu_DHDK);
			 
			    list_KPI_Penarikan_Menunggu_JT =logicKPI.list_KPI_Penarikan_Menunggu_JT(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_JT",list_KPI_Penarikan_Menunggu_JT);
			 
			    list_KPI_Penarikan_Menunggu_BORANGK =logicKPI.list_KPI_Penarikan_Menunggu_BORANGK(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_BORANGK",list_KPI_Penarikan_Menunggu_BORANGK);
			 
			    list_KPI_Penarikan_Menunggu_BAYARAN =logicKPI.list_KPI_Penarikan_Menunggu_BAYARAN(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_BAYARAN",list_KPI_Penarikan_Menunggu_BAYARAN);
			 
			
			
			    
			    
			    
			    
			
			
			}
			
			this.context.put("open_penetapan",getParam("open_penetapan"));
					
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
			senarai_kpisasaran = logicKPI.senarai_kpisasaran((String) session.getAttribute("_ekptg_user_negeri"),getParam("jenisUrusan"),"1",getParam("socPejabat"));
		    this.context.put("senarai_kpisasaran",senarai_kpisasaran);
			
		    if(senarai_kpisasaran.size()>0)
		    {
			this.context.put("readmode","view");
		    }
		    else
		    {
		    this.context.put("readmode","edit");
		    }
			
		}
		
		
		else if(main_command.equals("KPI"))
		{
			senarai_kpisasaran = logicKPI.senarai_kpisasaran((String) session.getAttribute("_ekptg_user_negeri"),getParam("jenisUrusan"),"1",getParam("socPejabat"));
			this.context.put("open_penetapan",getParam("open_penetapan"));
			
			
			myLogger.info("SIZE SASARAN:"+senarai_kpisasaran.size()+"JENIS URUSAN :"+getParam("jenisUrusan"));
			
			
			
			if(sub_command.equals("Simpan"))
			{
				if(senarai_kpisasaran.size() == 0)
				{
					if (bolehsimpan.equals("yes")) 
					{
						if(getParam("jenisUrusan").equals("5"))
						{
				        addSasaranPenarikan(session);
						}
						else if(getParam("jenisUrusan").equals("1"))
						{
				        addSasaranSek8(session);
						}
						else if(getParam("jenisUrusan").equals("4"))
						{
				        addSasaranBantahan(session);
						}
					}
				}
				else
				{
					if(getParam("jenisUrusan").equals("5"))
					{
				    updateSasaranPenarikan(session);
					}
					else if(getParam("jenisUrusan").equals("1"))
					{
				    updateSasaranSek8(session);
					}
					else if(getParam("jenisUrusan").equals("4"))
					{
				    updateSasaranBantahan(session);
					}
				}
				this.context.put("readmode","view");				
								
			}			
			else if(sub_command.equals("Kemaskini"))
			{				
				this.context.put("readmode","edit");				
				
			}
			else if(sub_command.equals("open_penetapan"))
			{				
				this.context.put("open_penetapan","yes");				
			}
			else if(sub_command.equals("close_penetapan"))
			{				
				this.context.put("open_penetapan","");				
			}
				
			
			if(getParam("jenisUrusan").equals("4"))
			{			
				list_KPI_Bantahan =logicKPI.list_KPI_Bantahan(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Bantahan",list_KPI_Bantahan);
			    
			    list_KPI_Menunggu_Bantahan_Perintah =logicKPI.list_KPI_Menunggu_Bantahan_Perintah(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Menunggu_Bantahan_Perintah",list_KPI_Menunggu_Bantahan_Perintah);			 
		
			    
			  /*  list_KPI_Penarikan_Menunggu_MMK =logicKPI.list_KPI_Penarikan_Menunggu_MMK(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_MMK",list_KPI_Penarikan_Menunggu_MMK);
			 
			    list_KPI_Penarikan_Menunggu_Bayaran =logicKPI.list_KPI_Penarikan_Menunggu_Bayaran(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_Bayaran",list_KPI_Penarikan_Menunggu_Bayaran);
			*/  
			}
			
			if(getParam("jenisUrusan").equals("5"))//penarikan balik
			{			
				list_KPI_Penarikan =logicKPI.list_KPI_Penarikan(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan",list_KPI_Penarikan);
			    
			    list_KPI_Penarikan_Menunggu_MMK =logicKPI.list_KPI_Penarikan_Menunggu_MMK(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_MMK",list_KPI_Penarikan_Menunggu_MMK);
			 
			    list_KPI_Penarikan_Menunggu_Bayaran =logicKPI.list_KPI_Penarikan_Menunggu_Bayaran(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_Bayaran",list_KPI_Penarikan_Menunggu_Bayaran);
			 
			    
			}
			if(getParam("jenisUrusan").equals("1"))//sekyen 8
			{			
				list_KPI_Seksyen8 =logicKPI.list_KPI_Seksyen8(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Seksyen8",list_KPI_Seksyen8);
			    
			    list_KPI_Penarikan_Menunggu_JPPH =logicKPI.list_KPI_Penarikan_Menunggu_JPPH(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_JPPH",list_KPI_Penarikan_Menunggu_JPPH);
			 
			    list_KPI_Penarikan_Menunggu_PBN =logicKPI.list_KPI_Penarikan_Menunggu_PBN(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_PBN",list_KPI_Penarikan_Menunggu_PBN);
			 
			    list_KPI_Penarikan_Menunggu_DHDK =logicKPI.list_KPI_Penarikan_Menunggu_DHDK(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_DHDK",list_KPI_Penarikan_Menunggu_DHDK);
			 
			    list_KPI_Penarikan_Menunggu_JT =logicKPI.list_KPI_Penarikan_Menunggu_JT(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_JT",list_KPI_Penarikan_Menunggu_JT);
			 
			    list_KPI_Penarikan_Menunggu_BAYARAN =logicKPI.list_KPI_Penarikan_Menunggu_BAYARAN(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_BAYARAN",list_KPI_Penarikan_Menunggu_BAYARAN);
			 
			    
			    list_KPI_Penarikan_Menunggu_BORANGK =logicKPI.list_KPI_Penarikan_Menunggu_BORANGK(getParam("jenisUrusan"),getParam("socDaerah"),getParam("socPejabat"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
			    this.context.put("list_KPI_Penarikan_Menunggu_BORANGK",list_KPI_Penarikan_Menunggu_BORANGK);
			 
			    
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
			
			senarai_kpisasaran = logicKPI.senarai_kpisasaran((String) session.getAttribute("_ekptg_user_negeri"),getParam("jenisUrusan"),"1",getParam("socPejabat"));
			this.context.put("senarai_kpisasaran",senarai_kpisasaran);
			
			
			
		}
		
				list_urusan = logic.list_urusan();
				this.context.put("list_urusan",list_urusan);
				list_status = logic.list_status();
				this.context.put("list_status",list_status);
				list_pejabat = logic.list_pejabat();
				this.context.put("list_pejabat",list_pejabat);				
				vm = "app/ppt/KPI/leadingKPI_main.jsp";	
			
		    this.context.put("portal_role",(String) session.getAttribute("_portal_role"));
		    this.context.put("negeri_user",(String) session.getAttribute("_ekptg_user_negeri"));	
		    nama_user = logic.nama_user((String) session.getAttribute("_ekptg_user_id"));
		    this.context.put("nama_user",nama_user);
		    this.context.put("location",location);
			this.context.put("point",point);		
            return vm;
     
	}

	

	private void addSasaranSek8(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("SS1",getParam("SS1"));
		h.put("SS2",getParam("SS2"));
		h.put("SS3",getParam("SS3"));
		h.put("SS4",getParam("SS4"));
		h.put("SS5",getParam("SS5"));
		h.put("SS6",getParam("SS6"));
		h.put("SS7",getParam("SS7"));
		h.put("SS8",getParam("SS8"));
		h.put("SS9",getParam("SS9"));
		h.put("SS10",getParam("SS10"));
		
		h.put("SX1",getParam("SX1"));
		h.put("SX2",getParam("SX2"));
		h.put("SX3",getParam("SX3"));
		h.put("SX4",getParam("SX4"));
		h.put("SX5",getParam("SX5"));
		h.put("SX6",getParam("SX6"));
		h.put("SX7",getParam("SX7"));
		h.put("SX8",getParam("SX8"));
		h.put("SX9",getParam("SX9"));
		h.put("SX10",getParam("SX10"));
		h.put("SX11",getParam("SX11"));
		h.put("SX12",getParam("SX12"));
		h.put("SX13",getParam("SX13"));
		h.put("SX14",getParam("SX14"));
		h.put("SX15",getParam("SX15"));
		h.put("SX16",getParam("SX16"));		
		
		
		h.put("id_negeri",(String) session.getAttribute("_ekptg_user_negeri"));
		h.put("id_urusan","1");			
		h.put("id_user", (String) session.getAttribute("_ekptg_user_id"));
		logicKPI.addSasaranSek8(h);		
	}
	
	private void updateSasaranSek8(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("SS1",getParam("SS1"));
		h.put("SS2",getParam("SS2"));
		h.put("SS3",getParam("SS3"));
		h.put("SS4",getParam("SS4"));
		h.put("SS5",getParam("SS5"));
		h.put("SS6",getParam("SS6"));
		h.put("SS7",getParam("SS7"));
		h.put("SS8",getParam("SS8"));
		h.put("SS9",getParam("SS9"));
		h.put("SS10",getParam("SS10"));
		
		h.put("SX1",getParam("SX1"));
		h.put("SX2",getParam("SX2"));
		h.put("SX3",getParam("SX3"));
		h.put("SX4",getParam("SX4"));
		h.put("SX5",getParam("SX5"));
		h.put("SX6",getParam("SX6"));
		h.put("SX7",getParam("SX7"));
		h.put("SX8",getParam("SX8"));
		h.put("SX9",getParam("SX9"));
		h.put("SX10",getParam("SX10"));
		h.put("SX11",getParam("SX11"));
		h.put("SX12",getParam("SX12"));
		h.put("SX13",getParam("SX13"));
		h.put("SX14",getParam("SX14"));
		h.put("SX15",getParam("SX15"));
		h.put("SX16",getParam("SX16"));		
		
		h.put("id_kpisasaran",getParam("id_kpisasaran"));
		
		
		h.put("id_negeri",(String) session.getAttribute("_ekptg_user_negeri"));
		h.put("id_urusan","1");			
		h.put("id_user", (String) session.getAttribute("_ekptg_user_id"));
		logicKPI.updateSasaranSek8(h);		
	}

	 private void addSasaranPenarikan(HttpSession session) throws Exception {
			Hashtable h = new Hashtable();
			h.put("SS1",getParam("SS1"));
			h.put("SS2",getParam("SS2"));
			h.put("SS3",getParam("SS3"));
			h.put("SS4",getParam("SS4"));
			h.put("SS5",getParam("SS5"));
			h.put("SS6",getParam("SS6"));
			h.put("SS7",getParam("SS7"));
			h.put("SS8", getParam("SS8"));			
			h.put("id_negeri",(String) session.getAttribute("_ekptg_user_negeri"));
			h.put("id_urusan","5");			
			h.put("id_user", (String) session.getAttribute("_ekptg_user_id"));
			logicKPI.addSasaranPenarikan(h);		
		}
	 
	 private void updateSasaranPenarikan(HttpSession session) throws Exception {
			Hashtable h = new Hashtable();
			h.put("SS1",getParam("SS1"));
			h.put("SS2",getParam("SS2"));
			h.put("SS3",getParam("SS3"));
			h.put("SS4",getParam("SS4"));
			h.put("SS5",getParam("SS5"));
			h.put("SS6",getParam("SS6"));
			h.put("SS7",getParam("SS7"));
			h.put("SS8", getParam("SS8"));			
			h.put("id_negeri",(String) session.getAttribute("_ekptg_user_negeri"));
			h.put("id_urusan","5");	
			h.put("id_kpisasaran",getParam("id_kpisasaran"));	
			h.put("id_user", (String) session.getAttribute("_ekptg_user_id"));
			logicKPI.updateSasaranPenarikan(h);		
		}
	 
	 
	 
	 private void addSasaranBantahan(HttpSession session) throws Exception {
			Hashtable h = new Hashtable();
			h.put("SS1",getParam("SS1"));
			h.put("SS2",getParam("SS2"));		
			h.put("SS5",getParam("SS5"));
			h.put("SS6",getParam("SS6"));
						
			h.put("id_negeri",(String) session.getAttribute("_ekptg_user_negeri"));
			h.put("id_urusan","4");			
			h.put("id_user", (String) session.getAttribute("_ekptg_user_id"));
			logicKPI.addSasaranBantahan(h);		
		}
	 
	 private void updateSasaranBantahan(HttpSession session) throws Exception {
			Hashtable h = new Hashtable();
			h.put("SS1",getParam("SS1"));
			h.put("SS2",getParam("SS2"));			
			h.put("SS5",getParam("SS5"));
			h.put("SS6",getParam("SS6"));
						
			h.put("id_negeri",(String) session.getAttribute("_ekptg_user_negeri"));
			h.put("id_urusan","4");	
			h.put("id_kpisasaran",getParam("id_kpisasaran"));	
			h.put("id_user", (String) session.getAttribute("_ekptg_user_id"));
			logicKPI.updateSasaranBantahan(h);		
		}
	 
	
	
	

	 
	 
}// close class
