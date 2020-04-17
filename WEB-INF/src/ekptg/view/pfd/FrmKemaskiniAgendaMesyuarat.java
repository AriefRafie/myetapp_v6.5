package ekptg.view.pfd;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.model.pfd.FrmKemaskiniAgendaMesyuaratData;

public class FrmKemaskiniAgendaMesyuarat extends VTemplate{
	
	public Template doTemplate() throws Exception

    {
		
		
		 String command = getParam("command");
		 String command1 = getParam("command1");
		 String mode = getParam("mode");
		 HttpSession session = this.request.getSession();
		 Vector list = new Vector();
		 String readability = "";
		 String vm = "";
		 
		 if ("tambah".equals(command)){
			 
			 vm = "app/pfd/frmKemaskiniAgendaMesyuarat.jsp";
			 
			 FrmKemaskiniAgendaMesyuaratData.setDataAgenda(0);
	       	 list = FrmKemaskiniAgendaMesyuaratData.getDataAgenda();
	       	 this.context.put("AgendaMesyuarat",list);
			 this.context.put("mode","New");
			 this.context.put("readOnly","");
			 
			 if ("simpan".equals(command1)){
				 
				 if ("tambahBaru".equals(mode)){
					
					 simpanAgenda(session);
				 }
			 }

		 }
		 else if("papar".equals(command)){
			 readability = "readonly";
			 vm = "app/pfd/frmKemaskiniAgendaMesyuarat.jsp";
			 view_agenda(session);
			 list = FrmKemaskiniAgendaMesyuaratData.getDataAgenda();
	       	 this.context.put("AgendaMesyuarat",list);
	       	 this.context.put("mode","View");
	       	 this.context.put("readOnly",readability);
	       	 
	       	if ("kemaskini".equals(command1)){
	       	 readability = "";
			 vm = "app/pfd/frmKemaskiniAgendaMesyuarat.jsp";
			 view_agenda(session);
			 list = FrmKemaskiniAgendaMesyuaratData.getDataAgenda();
	       	 this.context.put("AgendaMesyuarat",list);
	       	 this.context.put("mode","Update");
	       	 this.context.put("readOnly",readability);
	       	
	       	}
	    	else if ("simpan".equals(command1)){
	       		
		       	 if ("kemaskiniAgenda".equals(mode)){
						
		       		simpanAgenda(session);
				 }
		       	}

			 
		 }
		 
		  Template template = this.engine.getTemplate(vm);

          return template;

    }
	private void view_agenda(HttpSession session) throws Exception {
    	int id = Integer.parseInt(getParam("agendaMsyrt"));
    	FrmKemaskiniAgendaMesyuaratData.setDataAgenda(id);
	   
	  }

	private int simpanAgenda(HttpSession session) throws Exception {
		 Db db = null;
		 
		 int idAgendamesyuarat;
		
		 if (getParam("idAgendamesyuarat") == "" || Integer.parseInt(getParam("idAgendamesyuarat")) == 0){
			 
			
			 Hashtable h = new Hashtable();
			 
			 h.put("id_Mesyuarat",getParam("idMesyuarat"));
			 h.put("agenda", getParam("txtAgenda"));
			
			 idAgendamesyuarat = FrmKemaskiniAgendaMesyuaratData.add(h);
			 
			 return idAgendamesyuarat;
		 }
		 else{
			 
			 Hashtable h = new Hashtable();
			 
			 h.put("idAgendamesyuarat", Integer.parseInt(getParam("idAgendamesyuarat")));
			 h.put("agenda", getParam("txtAgenda"));
			
			 idAgendamesyuarat = FrmKemaskiniAgendaMesyuaratData.update(h);
			 
			 return idAgendamesyuarat;
		 }
	   
	  }

}
