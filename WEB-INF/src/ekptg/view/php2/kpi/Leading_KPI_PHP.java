package ekptg.view.php2.kpi;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.util.DateUtil;

import org.apache.log4j.Logger;

import ekptg.model.php2.kpi.KPIData_PHP;


public class Leading_KPI_PHP extends AjaxBasedModule{	
	static Logger myLogger = Logger.getLogger(Leading_KPI_PHP.class);	
	KPIData_PHP logicKPI = new KPIData_PHP();	
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();		
		String vm = ""; 
		String main_command = getParam("command");
		String sub_command = getParam("sub_command");		
		myLogger.info("main_command :"+main_command+";sub_command :"+sub_command);		
		
		String location = getParam("location");
		String point = getParam("point");		
		Vector list_urusan = null;		
		Vector nama_user = null;
		Vector senarai_kpisasaran = null;
		Vector list_suburusan_php = null;
		Vector list_KPI_MENUNGGU_PENYEWAAN_A = null;
		Vector list_KPI_MENUNGGU_PENYEWAAN_B = null;
		Vector list_KPI_MENUNGGU_PENYEWAAN_C = null;
		Vector list_KPI_MENUNGGU_PENAWARAN_A = null;
		Vector list_KPI_MENUNGGU_PENAWARAN_B = null;
		Vector list_KPI_MENUNGGU_PENAWARAN_C = null;
		Vector list_KPI_MENUNGGU_PELEPASAN_A = null;
		Vector list_KPI_MENUNGGU_PELEPASAN_B = null;
		Vector list_KPI_MENUNGGU_PELEPASAN_C = null;
		Vector list_KPI_MENUNGGU_PELEPASAN_D = null;
		Vector list_KPI_MENUNGGU_TUKARGUNA_A = null;
		Vector list_KPI_MENUNGGU_TUKARGUNA_B = null;
		
		Vector list_KPI_MENUNGGU_APB_A = null;
		Vector list_KPI_MENUNGGU_APB_B = null;
		Vector list_KPI_MENUNGGU_APB_C = null;
		Vector list_KPI_MENUNGGU_APB_D = null;
		Vector list_KPI_MENUNGGU_APB_E = null;
		Vector list_KPI_MENUNGGU_APB_F = null;
		Vector listMaklumatKPIPelepasan = null;
		Vector listMaklumatKPIPenawaran = null;
		Vector listMaklumatKPITukarguna = null;
		Vector listMaklumatKPIPenyewaan =null;
		Vector listMaklumatKPIAPB = null;
	
		this.context.put("list_urusan","");
		this.context.put("nama_user","");
		this.context.put("senarai_kpisasaran","");
		this.context.put("list_suburusan_php","");
		this.context.put("list_KPI_MENUNGGU_APB_A","");
		this.context.put("list_KPI_MENUNGGU_APB_B","");
		this.context.put("list_KPI_MENUNGGU_APB_C","");
		this.context.put("list_KPI_MENUNGGU_APB_D","");
		this.context.put("list_KPI_MENUNGGU_APB_E","");
		this.context.put("list_KPI_MENUNGGU_APB_F","");	
		this.context.put("list_KPI_MENUNGGU_PENYEWAAN_A","");
		this.context.put("list_KPI_MENUNGGU_PENYEWAAN_B","");
		this.context.put("list_KPI_MENUNGGU_PENYEWAAN_C","");
		this.context.put("list_KPI_MENUNGGU_PENAWARAN_A","");
		this.context.put("list_KPI_MENUNGGU_PENAWARAN_B","");
		this.context.put("list_KPI_MENUNGGU_PENAWARAN_C","");
		this.context.put("list_KPI_MENUNGGU_PELEPASAN_A","");
		this.context.put("list_KPI_MENUNGGU_PELEPASAN_B","");
		this.context.put("list_KPI_MENUNGGU_PELEPASAN_C","");
		this.context.put("list_KPI_MENUNGGU_PELEPASAN_D","");
		this.context.put("list_KPI_MENUNGGU_TUKARGUNA_A","");
		this.context.put("list_KPI_MENUNGGU_TUKARGUNA_B","");
		this.context.put("listMaklumatKPIAPB","");
		this.context.put("listMaklumatKPIPenyewaan","");
		this.context.put("listMaklumatKPIPelepasan","");
		this.context.put("listMaklumatKPIPenawaran","");
		this.context.put("listMaklumatKPITukarguna","");		
		this.context.put("open_KPI", "");
		this.context.put("open_penetapan","");
		this.context.put("socUrusan","");
		this.context.put("socSuburusan","");
		this.context.put("txdTarikhAkhir", "");
		this.context.put("txdTarikhMula","");	
		
		context.put("DATEUTIL", new DateUtil());
		this.context.put("Util", new lebah.util.Util());		
		String bolehsimpan = "";		
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {			
			bolehsimpan = "yes";
		}	
		
		 if(main_command.equals("Suburusan"))
		{
			if(!getParam("socUrusan").equals(""))
			{
				list_suburusan_php =logicKPI.list_suburusan_php(getParam("socUrusan"));
				this.context.put("list_suburusan_php",list_suburusan_php);
			}			
			this.context.put("socUrusan", getParam("socUrusan"));
			this.context.put("socSuburusan","");
			this.context.put("txdTarikhMula", getParam("txdTarikhMula"));
			this.context.put("txdTarikhAkhir", getParam("txdTarikhAkhir"));
		}
		else if(main_command.equals("kosongkan"))
		{
			this.context.put("socUrusan","");
			this.context.put("socSuburusan","");
			this.context.put("txdTarikhAkhir", "");
			this.context.put("txdTarikhMula","");		
		}
		else if(main_command.equals("urusan"))
		{
			if(getParam("socUrusan").equals("6"))
			{	
			if(getParam("socSuburusan").equals("32"))
			{
		        logicKPI.setMaklumatKPIPenawaran(getParam("id_kpisasaran"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
		        listMaklumatKPIPenawaran = logicKPI.getBeanMaklumatKPIPenawaran();
			    this.context.put("listMaklumatKPIPenawaran",listMaklumatKPIPenawaran);
			    
			    list_KPI_MENUNGGU_PENAWARAN_A =logicKPI.list_KPI_MENUNGGU("1610199","32",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
			    this.context.put("list_KPI_MENUNGGU_PENAWARAN_A",list_KPI_MENUNGGU_PENAWARAN_A);
			     
			    list_KPI_MENUNGGU_PENAWARAN_B =logicKPI.list_KPI_MENUNGGU("1610210","32",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
			    this.context.put("list_KPI_MENUNGGU_PENAWARAN_B",list_KPI_MENUNGGU_PENAWARAN_B);
			     
			    list_KPI_MENUNGGU_PENAWARAN_C =logicKPI.list_KPI_MENUNGGU("1610201","32",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
			    this.context.put("list_KPI_MENUNGGU_PENAWARAN_C",list_KPI_MENUNGGU_PENAWARAN_C);
		    }			
			if(getParam("socSuburusan").equals("33"))
			{
		        logicKPI.setMaklumatKPITukarguna(getParam("id_kpisasaran"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
		        listMaklumatKPITukarguna = logicKPI.getBeanMaklumatKPITukarguna();
			    this.context.put("listMaklumatKPITukarguna",listMaklumatKPITukarguna);
			    
			    list_KPI_MENUNGGU_TUKARGUNA_A =logicKPI.list_KPI_MENUNGGU("1610199","33",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
			    this.context.put("list_KPI_MENUNGGU_TUKARGUNA_A",list_KPI_MENUNGGU_TUKARGUNA_A);
			     
			    list_KPI_MENUNGGU_TUKARGUNA_B =logicKPI.list_KPI_MENUNGGU("1610201","33",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
			    this.context.put("list_KPI_MENUNGGU_TUKARGUNA_B",list_KPI_MENUNGGU_TUKARGUNA_B);
			}			
			if(getParam("socSuburusan").equals("34"))
			{
		        logicKPI.setMaklumatKPIPelepasan(getParam("id_kpisasaran"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
		        listMaklumatKPIPelepasan = logicKPI.getBeanMaklumatKPIPelepasan();
			    this.context.put("listMaklumatKPIPelepasan",listMaklumatKPIPelepasan);
			    
			    list_KPI_MENUNGGU_PELEPASAN_A =logicKPI.list_KPI_MENUNGGU("1610199","34",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
			    this.context.put("list_KPI_MENUNGGU_PELEPASAN_A",list_KPI_MENUNGGU_PELEPASAN_A);
			     
			    list_KPI_MENUNGGU_PELEPASAN_B =logicKPI.list_KPI_MENUNGGU("1610202","34",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
			    this.context.put("list_KPI_MENUNGGU_PELEPASAN_B",list_KPI_MENUNGGU_PELEPASAN_B);
			     
			    list_KPI_MENUNGGU_PELEPASAN_C =logicKPI.list_KPI_MENUNGGU("1610203","34",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
			    this.context.put("list_KPI_MENUNGGU_PELEPASAN_C",list_KPI_MENUNGGU_PELEPASAN_C);
		  
			    list_KPI_MENUNGGU_PELEPASAN_D =logicKPI.list_KPI_MENUNGGU("1610206","34",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
			    this.context.put("list_KPI_MENUNGGU_PELEPASAN_D",list_KPI_MENUNGGU_PELEPASAN_D);
		   	}
			}
			if(getParam("socUrusan").equals("7"))
			{
				if(getParam("socSuburusan").equals("35") || getParam("socSuburusan").equals("36"))
				{
			        logicKPI.setMaklumatKPIPenyewaan(getParam("id_kpisasaran"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"),getParam("socUrusan"),getParam("socSuburusan"));
			        listMaklumatKPIPenyewaan = logicKPI.getBeanMaklumatKPIPenyewaan();
				    this.context.put("listMaklumatKPIPenyewaan",listMaklumatKPIPenyewaan);
				    
				    list_KPI_MENUNGGU_PENYEWAAN_A =logicKPI.list_KPI_MENUNGGU("1610199",getParam("socSuburusan"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
				    this.context.put("list_KPI_MENUNGGU_PENYEWAAN_A",list_KPI_MENUNGGU_PENYEWAAN_A);
				     
				    list_KPI_MENUNGGU_PENYEWAAN_B =logicKPI.list_KPI_MENUNGGU("1610201",getParam("socSuburusan"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
				    this.context.put("list_KPI_MENUNGGU_PENYEWAAN_B",list_KPI_MENUNGGU_PENYEWAAN_B);
				     
				    list_KPI_MENUNGGU_PENYEWAAN_C =logicKPI.list_KPI_MENUNGGU("1610214",getParam("socSuburusan"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
				    this.context.put("list_KPI_MENUNGGU_PENYEWAAN_C",list_KPI_MENUNGGU_PENYEWAAN_C);		 
			   	}
			}
			if(getParam("socUrusan").equals("9"))
			{
				if(getParam("socSuburusan").equals("57"))
				{
			        logicKPI.setMaklumatKPIAPB(getParam("id_kpisasaran"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"),getParam("socUrusan"),getParam("socSuburusan"));
			        listMaklumatKPIAPB = logicKPI.getBeanMaklumatKPIAPB();
				    this.context.put("listMaklumatKPIAPB",listMaklumatKPIAPB);
				    
				    list_KPI_MENUNGGU_APB_A =logicKPI.list_KPI_MENUNGGU("1610198",getParam("socSuburusan"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
				    this.context.put("list_KPI_MENUNGGU_APB_A",list_KPI_MENUNGGU_APB_A);
				    
				    list_KPI_MENUNGGU_APB_B =logicKPI.list_KPI_MENUNGGU("1610235",getParam("socSuburusan"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
				    this.context.put("list_KPI_MENUNGGU_APB_B",list_KPI_MENUNGGU_APB_B);
				    
				    list_KPI_MENUNGGU_APB_C =logicKPI.list_KPI_MENUNGGU("1610213",getParam("socSuburusan"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
				    this.context.put("list_KPI_MENUNGGU_APB_C",list_KPI_MENUNGGU_APB_C);
				    
				    list_KPI_MENUNGGU_APB_D =logicKPI.list_KPI_MENUNGGU("1610206",getParam("socSuburusan"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
				    this.context.put("list_KPI_MENUNGGU_APB_D",list_KPI_MENUNGGU_APB_D);
				    
				    list_KPI_MENUNGGU_APB_E =logicKPI.list_KPI_MENUNGGU("1610237",getParam("socSuburusan"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
				    this.context.put("list_KPI_MENUNGGU_APB_E",list_KPI_MENUNGGU_APB_E);
				    
				    list_KPI_MENUNGGU_APB_F =logicKPI.list_KPI_MENUNGGU("1610238",getParam("socSuburusan"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
				    this.context.put("list_KPI_MENUNGGU_APB_F",list_KPI_MENUNGGU_APB_F);
			   	}
			}	
			
			
			this.context.put("open_penetapan",getParam("open_penetapan"));					
			if(!getParam("socUrusan").equals(""))
			{
				list_suburusan_php =logicKPI.list_suburusan_php(getParam("socUrusan"));
				this.context.put("list_suburusan_php",list_suburusan_php);
			}			
			this.context.put("socUrusan", getParam("socUrusan"));
			this.context.put("socSuburusan",getParam("socSuburusan"));
			this.context.put("txdTarikhMula", getParam("txdTarikhMula"));
			this.context.put("txdTarikhAkhir", getParam("txdTarikhAkhir"));
			this.context.put("open_KPI", "yes");
			senarai_kpisasaran = logicKPI.senarai_kpisasaran(getParam("socUrusan"),getParam("socSuburusan"),"4");
		    this.context.put("senarai_kpisasaran",senarai_kpisasaran);			
		    if(senarai_kpisasaran.size()>0)
		    {
			this.context.put("readmode","view");//define screen sasaran in view mode
		    }
		    else
		    {
		    this.context.put("readmode","edit");//define screen sasaran in edit mode
		    }			
		}
		else if(main_command.equals("KPI"))
		{
			senarai_kpisasaran = logicKPI.senarai_kpisasaran(getParam("socUrusan"),getParam("socSuburusan"),"4");
			this.context.put("open_penetapan",getParam("open_penetapan"));			
			myLogger.info("senarai_kpisasaran.size() :"+senarai_kpisasaran.size());
			myLogger.info("bolehsimpan :"+bolehsimpan);
			myLogger.info("getParam('socUrusan') :"+getParam("socUrusan"));			
			if(sub_command.equals("Simpan"))
			{
				if(senarai_kpisasaran.size() == 0)
				{
					if (bolehsimpan.equals("yes")) 
					{						
				        addSasaran(session);//add sasaran KPI						
					}
				}
				else
				{					
					if (bolehsimpan.equals("yes")) 
					{						
				        updateSasaran(session);//update sasaran KPI						
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
			if(getParam("socUrusan").equals("6"))
			{	
			if(getParam("socSuburusan").equals("32"))
			{
		        logicKPI.setMaklumatKPIPenawaran(getParam("id_kpisasaran"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
		        listMaklumatKPIPenawaran = logicKPI.getBeanMaklumatKPIPenawaran();
			    this.context.put("listMaklumatKPIPenawaran",listMaklumatKPIPenawaran);
			    
			    list_KPI_MENUNGGU_PENAWARAN_A =logicKPI.list_KPI_MENUNGGU("1610199","32",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
			    this.context.put("list_KPI_MENUNGGU_PENAWARAN_A",list_KPI_MENUNGGU_PENAWARAN_A);
			     
			    list_KPI_MENUNGGU_PENAWARAN_B =logicKPI.list_KPI_MENUNGGU("1610210","32",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
			    this.context.put("list_KPI_MENUNGGU_PENAWARAN_B",list_KPI_MENUNGGU_PENAWARAN_B);
			     
			    list_KPI_MENUNGGU_PENAWARAN_C =logicKPI.list_KPI_MENUNGGU("1610201","32",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
			    this.context.put("list_KPI_MENUNGGU_PENAWARAN_C",list_KPI_MENUNGGU_PENAWARAN_C);
		    }			
			if(getParam("socSuburusan").equals("33"))
			{
		        logicKPI.setMaklumatKPITukarguna(getParam("id_kpisasaran"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
		        listMaklumatKPITukarguna = logicKPI.getBeanMaklumatKPITukarguna();
			    this.context.put("listMaklumatKPITukarguna",listMaklumatKPITukarguna);
			    
			    list_KPI_MENUNGGU_TUKARGUNA_A =logicKPI.list_KPI_MENUNGGU("1610199","33",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
			    this.context.put("list_KPI_MENUNGGU_TUKARGUNA_A",list_KPI_MENUNGGU_TUKARGUNA_A);
			     
			    list_KPI_MENUNGGU_TUKARGUNA_B =logicKPI.list_KPI_MENUNGGU("1610201","33",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
			    this.context.put("list_KPI_MENUNGGU_TUKARGUNA_B",list_KPI_MENUNGGU_TUKARGUNA_B);
			}			
			if(getParam("socSuburusan").equals("34"))
			{
		        logicKPI.setMaklumatKPIPelepasan(getParam("id_kpisasaran"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));
		        listMaklumatKPIPelepasan = logicKPI.getBeanMaklumatKPIPelepasan();
			    this.context.put("listMaklumatKPIPelepasan",listMaklumatKPIPelepasan);
			    
			    list_KPI_MENUNGGU_PELEPASAN_A =logicKPI.list_KPI_MENUNGGU("1610199","34",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
			    this.context.put("list_KPI_MENUNGGU_PELEPASAN_A",list_KPI_MENUNGGU_PELEPASAN_A);
			     
			    list_KPI_MENUNGGU_PELEPASAN_B =logicKPI.list_KPI_MENUNGGU("1610202","34",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
			    this.context.put("list_KPI_MENUNGGU_PELEPASAN_B",list_KPI_MENUNGGU_PELEPASAN_B);
			     
			    list_KPI_MENUNGGU_PELEPASAN_C =logicKPI.list_KPI_MENUNGGU("1610203","34",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
			    this.context.put("list_KPI_MENUNGGU_PELEPASAN_C",list_KPI_MENUNGGU_PELEPASAN_C);
		  
			    list_KPI_MENUNGGU_PELEPASAN_D =logicKPI.list_KPI_MENUNGGU("1610206","34",getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
			    this.context.put("list_KPI_MENUNGGU_PELEPASAN_D",list_KPI_MENUNGGU_PELEPASAN_D);
		   	}
			}
			if(getParam("socUrusan").equals("7"))
			{
				if(getParam("socSuburusan").equals("35") || getParam("socSuburusan").equals("36"))
				{
			        logicKPI.setMaklumatKPIPenyewaan(getParam("id_kpisasaran"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"),getParam("socUrusan"),getParam("socSuburusan"));
			        listMaklumatKPIPenyewaan = logicKPI.getBeanMaklumatKPIPenyewaan();
				    this.context.put("listMaklumatKPIPenyewaan",listMaklumatKPIPenyewaan);
				    
				    list_KPI_MENUNGGU_PENYEWAAN_A =logicKPI.list_KPI_MENUNGGU("1610199",getParam("socSuburusan"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
				    this.context.put("list_KPI_MENUNGGU_PENYEWAAN_A",list_KPI_MENUNGGU_PENYEWAAN_A);
				     
				    list_KPI_MENUNGGU_PENYEWAAN_B =logicKPI.list_KPI_MENUNGGU("1610201",getParam("socSuburusan"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
				    this.context.put("list_KPI_MENUNGGU_PENYEWAAN_B",list_KPI_MENUNGGU_PENYEWAAN_B);
				     
				    list_KPI_MENUNGGU_PENYEWAAN_C =logicKPI.list_KPI_MENUNGGU("1610214",getParam("socSuburusan"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
				    this.context.put("list_KPI_MENUNGGU_PENYEWAAN_C",list_KPI_MENUNGGU_PENYEWAAN_C);		 
			   	}
			}
			if(getParam("socUrusan").equals("9"))
			{
				if(getParam("socSuburusan").equals("57"))
				{
			        logicKPI.setMaklumatKPIAPB(getParam("id_kpisasaran"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"),getParam("socUrusan"),getParam("socSuburusan"));
			        listMaklumatKPIAPB = logicKPI.getBeanMaklumatKPIAPB();
				    this.context.put("listMaklumatKPIAPB",listMaklumatKPIAPB);
				    
				    list_KPI_MENUNGGU_APB_A =logicKPI.list_KPI_MENUNGGU("1610198",getParam("socSuburusan"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
				    this.context.put("list_KPI_MENUNGGU_APB_A",list_KPI_MENUNGGU_APB_A);
				    
				    list_KPI_MENUNGGU_APB_B =logicKPI.list_KPI_MENUNGGU("1610235",getParam("socSuburusan"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
				    this.context.put("list_KPI_MENUNGGU_APB_B",list_KPI_MENUNGGU_APB_B);
				    
				    list_KPI_MENUNGGU_APB_C =logicKPI.list_KPI_MENUNGGU("1610213",getParam("socSuburusan"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
				    this.context.put("list_KPI_MENUNGGU_APB_C",list_KPI_MENUNGGU_APB_C);
				    
				    list_KPI_MENUNGGU_APB_D =logicKPI.list_KPI_MENUNGGU("1610206",getParam("socSuburusan"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
				    this.context.put("list_KPI_MENUNGGU_APB_D",list_KPI_MENUNGGU_APB_D);
				    
				    list_KPI_MENUNGGU_APB_E =logicKPI.list_KPI_MENUNGGU("1610237",getParam("socSuburusan"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
				    this.context.put("list_KPI_MENUNGGU_APB_E",list_KPI_MENUNGGU_APB_E);
				    
				    list_KPI_MENUNGGU_APB_F =logicKPI.list_KPI_MENUNGGU("1610238",getParam("socSuburusan"),getParam("txdTarikhMula"),getParam("txdTarikhAkhir"));	    
				    this.context.put("list_KPI_MENUNGGU_APB_F",list_KPI_MENUNGGU_APB_F);
			   	}
			}			
			if(!getParam("socUrusan").equals(""))
			{
				list_suburusan_php =logicKPI.list_suburusan_php(getParam("socUrusan"));
				this.context.put("list_suburusan_php",list_suburusan_php);
			}			
			this.context.put("socUrusan", getParam("socUrusan"));
			this.context.put("socSuburusan",getParam("socSuburusan"));
			this.context.put("txdTarikhMula", getParam("txdTarikhMula"));
			this.context.put("txdTarikhAkhir", getParam("txdTarikhAkhir"));
			this.context.put("open_KPI", "yes");			
			senarai_kpisasaran = logicKPI.senarai_kpisasaran(getParam("socUrusan"),getParam("socSuburusan"),"4");
			this.context.put("senarai_kpisasaran",senarai_kpisasaran);
		}		
				list_urusan = logicKPI.list_urusan_php("_portal_role");
				this.context.put("list_urusan",list_urusan);						
				vm = "app/php2/kpi/leadingKPI_main_PHP.jsp";				
		    this.context.put("portal_role",(String) session.getAttribute("_portal_role"));
		    this.context.put("negeri_user",(String) session.getAttribute("_ekptg_user_negeri"));	
		     this.context.put("nama_user",nama_user);
		    this.context.put("location",location);
			this.context.put("point",point);		
            return vm;     
	}
	private void addSasaran(HttpSession session) throws Exception {
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
		
		h.put("id_seksyen","4");
		h.put("id_urusan",getParam("socUrusan"));	
		h.put("id_suburusan",getParam("socSuburusan"));	
		h.put("id_user", (String) session.getAttribute("_ekptg_user_id"));
		logicKPI.addSasaran(h);		
	}
	private void updateSasaran(HttpSession session) throws Exception {
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
		h.put("id_seksyen","4");
		h.put("id_urusan",getParam("socUrusan"));	
		h.put("id_suburusan",getParam("socSuburusan"));	
		h.put("id_kpisasaran",getParam("id_kpisasaran"));
		h.put("id_user", (String) session.getAttribute("_ekptg_user_id"));
		logicKPI.updateSasaran(h);		
	}
}// close class
