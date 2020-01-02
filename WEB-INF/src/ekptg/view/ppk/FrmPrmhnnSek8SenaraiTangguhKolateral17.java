package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.model.ppk.FrmPrmhnnSek8KptsanBicaraData;
import ekptg.model.ppk.FrmPrmhnnSek8Notis;

public class FrmPrmhnnSek8SenaraiTangguhKolateral17 extends VTemplate{
	
	FrmPrmhnnSek8Notis logic2 = new FrmPrmhnnSek8Notis();
	FrmPrmhnnSek8KptsanBicaraData logic = new FrmPrmhnnSek8KptsanBicaraData();
	
	public Template doTemplate() throws Exception{
		
			HttpSession session = request.getSession();

			String vm = "";
    	
			Vector keputusanPermohonan = new Vector();
			Vector list = new Vector();
			
			list.clear();
			keputusanPermohonan.clear();
	
			String idpermohonan = getParam("idpermohonan");
			
			//--get data keputusan permohonan
    		keputusanPermohonan = logic2.getKeputusanPermohonan(idpermohonan);
    		Hashtable kp = (Hashtable) keputusanPermohonan.get(0);
    		String idkp = (String)kp.get("id_keputusanpermohonan");
    		
    		
    		//get list tangguh kolateral	
    		logic.setListTangguhKolateral(idkp);
    		list = logic.getListTangguhKolateral();
    		
    		
    		
			//idpermohonan
    		context.put("idpermohonan", idpermohonan);
    		context.put("id_keputusanpermohonan", idkp);
    		
    		//data
    		context.put("listBicara",list);
    		
    		//size
    		context.put("listBicara_size",list.size());
    		
    		//println
    		System.out.println("[senarai bicara]:id permohonan = " +idpermohonan);
    		System.out.println("[senarai bicara]:id keputusan permohonan = " +idkp);
    		System.out.println("[senarai bicara]:list bicara = " +list);
    		System.out.println("[senarai bicara]:size bicara = " +list.size());
    		
            vm = "app/ppk/frmPrmhnnSek8SenaraiBicaraKolateral17.jsp";
          
            Template template = engine.getTemplate(vm);
            return template;
            
    }
}
