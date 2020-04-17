package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.ppk.FrmPrmhnnSek8Notis;
/*
 * @author
 * @Muhamad Syazreen bin Yahaya
 */

public class FrmPrmhnnSek17SenaraiBicara extends AjaxBasedModule{
	
	FrmPrmhnnSek8Notis modelNotis = new FrmPrmhnnSek8Notis();
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public String doTemplate2() throws Exception{
		
			HttpSession session = request.getSession();

			String vm = "";
    	
			String screen = "app/ppk/frmPrmhnnSek17SenaraiBicara.jsp";
			
			Vector keputusanPermohonan = new Vector();
			Vector list = new Vector();
			
			list.clear();
			keputusanPermohonan.clear();
	
			String id = getParam("id_permohonan");
			
			//--17122009
			context.put("flagscreen", getParam("flag"));
			
			//--get data keputusan permohonan
    		keputusanPermohonan = modelNotis.getKeputusanPermohonan(id);
    		
    		String idkp = "";
    		
    		if(keputusanPermohonan.size()!=0)
    		{
    			Hashtable kp = (Hashtable) keputusanPermohonan.get(0);
    			idkp = kp.get("id_keputusanpermohonan").toString();
    		}
    		
    		//get list bicara
			modelNotis.setListBicara(idkp);
    		list = modelNotis.getListBicara();
    		
			//id
    		context.put("id_permohonan", id);
    		context.put("id_keputusanpermohonan", idkp);
    		
    		//data
    		context.put("listBicara",list);
    		
    		//size
    		context.put("listBicara_size",list.size());
    		
            vm = screen;
          
            //Template template = engine.getTemplate(vm);
            //return template;
            
            return vm;
    }
}
