package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.htp.FrmFailLainKemaskiniData;

public class FrmFailLainKemaskini extends AjaxBasedModule{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.htp.FrmFailLainKemaskini.class);

	public String doTemplate2() throws Exception{				
		 String command = getParam("command");
		 String command1 = getParam("command1");
		 String mode = getParam("mode");
		 HttpSession session = this.request.getSession();
		 Vector list = new Vector();
		 String readability = "";
		 String vm = "";
		 String idFail = getParam("idFailLama");
		 myLog.info("idfail:"+idFail);
		 myLog.info("command,command1,mode:"+command+","+command1+","+mode);
		 if ("tambah".equals(command)){
			 
			 vm = "app/htp/frmFailLainKemaskini.jsp";
				 
			 if ("simpan".equals(command1)){
				 
				 if ("tambahBaru".equals(mode)){					
					 simpanFail(session);
				 }
		       	 if ("kemaskiniAgenda".equals(mode)){					
		       		 simpanFail(session);
		       	 }
			 }else if("hapus".equals(command1)){
				 FrmFailLainKemaskiniData.deleteFailLain((String)getParam("idAgendamesyuarat")); 
			 }
			 //FrmFailLainKemaskiniData.setDataAgenda(0);
	       	 list = FrmFailLainKemaskiniData.getDataFailLain(idFail);
	       	 this.context.put("AgendaMesyuarat",list);
			 this.context.put("mode","New");
			 this.context.put("readOnly","");
	       	 this.context.put("idFailLama",idFail);

		 } else if("papar".equals(command)){
			 readability = "readonly";
			 vm = "app/pfd/frmFailLainKemaskini.jsp";
			 view_agenda(session);
			 list = FrmFailLainKemaskiniData.getDataAgenda();
	       	 this.context.put("AgendaMesyuarat",list);
	       	 this.context.put("mode","View");
	       	 this.context.put("readOnly",readability);
	       	 
	       	if ("kemaskini".equals(command1)){
	       	 readability = "";
			 vm = "app/pfd/frmFailLainKemaskini.jsp";
			 view_agenda(session);
			 list = FrmFailLainKemaskiniData.getDataAgenda();
	       	 this.context.put("AgendaMesyuarat",list);
	       	 this.context.put("mode","Update");
	       	 this.context.put("readOnly",readability);
	       	
	       	}else if ("simpan".equals(command1)){
	       		
		       	 if ("kemaskiniAgenda".equals(mode)){					
		       		simpanFail(session);
				 }
		       	 list = FrmFailLainKemaskiniData.getDataFailLain(idFail);
		       	 this.context.put("AgendaMesyuarat",list);
				 this.context.put("mode","New");
				 this.context.put("readOnly","");
		       	 this.context.put("idFailLama",idFail);

	    	}

			 
		 }else if("hapus".equals(command)){
			 FrmFailLainKemaskiniData.deleteFailLain((String)getParam("idAgendamesyuarat")); 
	       	 list = FrmFailLainKemaskiniData.getDataFailLain(idFail);
	       	 this.context.put("AgendaMesyuarat",list);
			 this.context.put("mode","New");
			 this.context.put("readOnly","");
	       	 this.context.put("idFailLama",idFail);
		 }
		 
		 // Template template = this.engine.getTemplate(vm);

          return vm;

    }
	private void view_agenda(HttpSession session) throws Exception {
    	int id = Integer.parseInt(getParam("agendaMsyrt"));
    	FrmFailLainKemaskiniData.setDataAgenda(id);
	   
	  }

	private int simpanFail(HttpSession session) throws Exception {
		 Db db = null;
		 
		 int idAgendamesyuarat;
		
		 if (getParam("idAgendamesyuarat") == "" || Integer.parseInt(getParam("idAgendamesyuarat")) == 0){
			 
			
			 Hashtable h = new Hashtable();
			 
			 h.put("id_Mesyuarat",getParam("idMesyuarat"));
			 h.put("agenda", getParam("txtAgenda"));
			 h.put("idmasuk", (String)session.getAttribute("_ekptg_user_id"));
			 idAgendamesyuarat = FrmFailLainKemaskiniData.add(h);
			 
			 return idAgendamesyuarat;
		 } else{
			 
			 Hashtable h = new Hashtable();
			 
			 h.put("idAgendamesyuarat", Integer.parseInt(getParam("idAgendamesyuarat")));
			 h.put("agenda", getParam("txtAgenda"));
			 h.put("idmasuk", (String)session.getAttribute("_ekptg_user_id"));
			
			 idAgendamesyuarat = FrmFailLainKemaskiniData.update(h);
			 
			 return idAgendamesyuarat;
		 }
	   
	  }

}
