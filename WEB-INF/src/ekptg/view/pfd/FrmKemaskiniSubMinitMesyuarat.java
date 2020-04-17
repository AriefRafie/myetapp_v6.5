package ekptg.view.pfd;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.model.pfd.FrmKemaskiniSubMinitMesyuaratData;

public class FrmKemaskiniSubMinitMesyuarat extends VTemplate{
	
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
				 
				 vm = "app/pfd/frmKemaskiniSubMinitMesyuarat.jsp";
				 
				 FrmKemaskiniSubMinitMesyuaratData.setDataSubPara(0);
		       	 list = FrmKemaskiniSubMinitMesyuaratData.getDataSubPara();
		       	 this.context.put("SubMinitMesyuarat",list);
				 this.context.put("mode","New");
				 this.context.put("readOnly","");
				 
				 if ("simpan".equals(command1)){
					 
					 if ("tambahBaru".equals(mode)){
						
						 simpanSubPara(session);
					 }
				 }

			 }
			 else if("papar".equals(command)){
				 readability = "readonly";
				 vm = "app/pfd/frmKemaskiniSubMinitMesyuarat.jsp";
				 view_subpara(session);
				 list = FrmKemaskiniSubMinitMesyuaratData.getDataSubPara();
		       	 this.context.put("SubMinitMesyuarat",list);
		       	 this.context.put("mode","View");
		       	 this.context.put("readOnly",readability);
		       	 
		       	if ("kemaskini".equals(command1)){
		       	 readability = "";
				 vm = "app/pfd/frmKemaskiniSubMinitMesyuarat.jsp";
				 view_subpara(session);
				 list = FrmKemaskiniSubMinitMesyuaratData.getDataSubPara();
		       	 this.context.put("SubMinitMesyuarat",list);
		       	this.context.put("mode","Update");
		       	 this.context.put("readOnly",readability);
		       	
		       	}
		    	else if ("simpan".equals(command1)){
		       		
			       	 if ("kemaskiniSubMinit".equals(mode)){
							
			       		simpanSubPara(session);
					 }
			       	}

				 
			 }
             Template template = this.engine.getTemplate(vm);

             return template;

    }
	private void view_subpara(HttpSession session) throws Exception {
    	int id = Integer.parseInt(getParam("passIdMinitmesyuaratsubpara"));
    	FrmKemaskiniSubMinitMesyuaratData.setDataSubPara(id);
	   
	  }

	private int simpanSubPara(HttpSession session) throws Exception {
		 Db db = null;
		 
		 int idMinitmesyuaratsubpara;
		
		 if (getParam("idMinitmesyuaratsubpara") == "" || Integer.parseInt(getParam("idMinitmesyuaratsubpara")) == 0){
			 
			
			 Hashtable h = new Hashtable();
			 
			 h.put("id_Minitmesyuaratpara",getParam("idMinitmesyuaratpara"));
			 h.put("sub_Para", getParam("txtSubMinit"));
			
			 idMinitmesyuaratsubpara = FrmKemaskiniSubMinitMesyuaratData.add(h);
			 
			 return idMinitmesyuaratsubpara;
		 }
		 else{
			 
			 Hashtable h = new Hashtable();
			 
			 h.put("id_Minitmesyuaratsubpara", Integer.parseInt(getParam("idMinitmesyuaratsubpara")));
			 h.put("sub_Para", getParam("txtSubMinit"));
			
			 idMinitmesyuaratsubpara = FrmKemaskiniSubMinitMesyuaratData.update(h);
			 
			 return idMinitmesyuaratsubpara;
		 }
	   
	  }

}
