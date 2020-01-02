package ekptg.view.pfd;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.model.pfd.FrmKemaskiniAhliMesyuaratData;

public class FrmKemaskiniAhliMesyuarat extends VTemplate{
	
	public Template doTemplate() throws Exception

    {
			
			 String command = getParam("command");
			 String command1 = getParam("command1");
			 String mode = getParam("mode");
			 HttpSession session = this.request.getSession();
			 Vector list = new Vector();
			 String disability1 = "";
			 String readability = "";
			 String vm = "";
	
			 if ("tambah".equals(command)){
				 
				 vm = "app/pfd/frmKemaskiniAhliMesyuarat.jsp";
				 readability = "";
				 
				 FrmKemaskiniAhliMesyuaratData.setDataAhli(0);
		       	 list = FrmKemaskiniAhliMesyuaratData.getDataAhli();
		       	 this.context.put("AhliMesyuarat",list);

				 this.context.put("selectPegawai",HTML.SelectPegawai("socPegawai"));
				 this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen"));
				 this.context.put("mode","New");

				 if ("".equals(command1)){
					 command1 = "dalaman";
				 }
				
				 if ("dalaman".equals(command1)){
					 vm = "app/pfd/frmKemaskiniAhliMesyuarat.jsp";
					 
					 FrmKemaskiniAhliMesyuaratData.setDataAhli(0);
			       	 list = FrmKemaskiniAhliMesyuaratData.getDataAhli();
			       	 this.context.put("AhliMesyuarat",list);
			       	 
			       				       	 
					 this.context.put("jenisPegawai","dalaman");
					 this.context.put("radioCheck1", "checked");
					 this.context.put("radioCheck2", "");
					 this.context.put("readOnly",readability);
				 }
				 else if ("luaran".equals(command1)){
					 vm = "app/pfd/frmKemaskiniAhliMesyuarat.jsp";
					 this.context.put("jenisPegawai","luaran");
					 this.context.put("radioCheck1", "");
					 this.context.put("radioCheck2", "checked");
					 this.context.put("readOnly",readability);
				 }
				 else if ("simpan".equals(command1)){
					 
					 if ("tambahBaru".equals(mode)){
						
						 simpanAhli(session);
					 }
					
					 
				 }
			 }
			else if("papar".equals(command)){

					 disability1="disabled";
					 readability = "readonly";
					 vm = "app/pfd/frmKemaskiniAhliMesyuarat.jsp";
					 view_ahli(session);
					 list = FrmKemaskiniAhliMesyuaratData.getDataAhli();
			       	 this.context.put("AhliMesyuarat",list);
			       	 
			       	 Hashtable h = (Hashtable) list.get(0);
		         	 this.context.put("mode","View");
			       	 this.context.put("readOnly",readability);
			       	 
			       	 if (Long.parseLong(h.get("id_Pegawai").toString()) != 0 && h.get("id_Pegawai") != "" ){
			       		 this.context.put("jenisPegawai","dalaman"); 
			       		 this.context.put("radioCheck1", "checked");
						 this.context.put("radioCheck2", "");
						
						 this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Long.parseLong(h.get("id_Seksyen").toString()),disability1));
			         	 this.context.put("selectPegawai",HTML.SelectPegawai("socPegawai",Long.parseLong(h.get("id_Pegawai").toString()),disability1));
				       	 
			       	 }
			       	
			       	 else {
			       		 
			       		 view_ahli(session);
						 list = FrmKemaskiniAhliMesyuaratData.getDataAhli();
				       	 this.context.put("AhliMesyuarat",list);
				       	 
			       		 this.context.put("jenisPegawai","luaran"); 
			       		 this.context.put("radioCheck1", "");
						 this.context.put("radioCheck2", "checked");
						 
				       	 
			       	 }
			       	 
			       	if ("kemaskini".equals(command1)){
			       		
				       	 disability1="";
						 readability = "";
						 vm = "app/pfd/frmKemaskiniAhliMesyuarat.jsp";
						 view_ahli(session);
						 list = FrmKemaskiniAhliMesyuaratData.getDataAhli();
				       	 this.context.put("AhliMesyuarat",list);
				       	 
				       	 
			         	 this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Long.parseLong(h.get("id_Seksyen").toString()),disability1));
			         	 this.context.put("selectPegawai",HTML.SelectPegawai("socPegawai",Long.parseLong(h.get("id_Pegawai").toString()),disability1));
				       	 this.context.put("mode","Update");
				       	 this.context.put("readOnly",readability);
			       		
			       	}
			       	else if ("simpan".equals(command1)){
			       		
			       	 if ("kemaskiniAhli".equals(mode)){
							
						 simpanAhli(session);
					 }
			       	}
			      
			       
		 }
			 


            Template template = this.engine.getTemplate(vm);

            return template;

    }
	private int simpanAhli(HttpSession session) throws Exception {
		 Db db = null;
		 
		 int idAhlimesyuarat;
		
		 if (getParam("idAhliMesyuarat") == "" || Integer.parseInt(getParam("idAhliMesyuarat")) == 0){
			 
			 Hashtable h = new Hashtable();
			 
			 h.put("id_Mesyuarat",getParam("idMesyuarat"));
			 h.put("id_Pegawai", getParam("socPegawai"));
			 h.put("id_Seksyen", getParam("socSeksyen"));
			 h.put("nama_Ahlimesyuarat",getParam("txtPegawaiLuaran"));
			 h.put("agensi_Luar",getParam("txtAgensi"));
			 h.put("jawatan", getParam("txtJawatan"));
			 h.put("emel", getParam("txtEmel"));
			 h.put("pengerusi", getParam("checkedBox"));
			 idAhlimesyuarat = FrmKemaskiniAhliMesyuaratData.add(h);
			 
			 return idAhlimesyuarat;
		 }
		 else{
			 Hashtable h = new Hashtable();
			 
			 h.put("id_Ahlimesyuarat", Integer.parseInt(getParam("idAhliMesyuarat")));
			 h.put("id_Pegawai", getParam("socPegawai"));
			 h.put("id_Seksyen", getParam("socSeksyen"));
			 h.put("nama_Ahlimesyuarat",getParam("txtPegawaiLuaran"));
			 h.put("agensi_Luar",getParam("txtAgensi"));
			 h.put("jawatan", getParam("txtJawatan"));
			 h.put("emel", getParam("txtEmel"));
			 h.put("pengerusi", getParam("checkedBox"));
			 idAhlimesyuarat = FrmKemaskiniAhliMesyuaratData.update(h);
			 
			 return idAhlimesyuarat;
		 }
	   
	  }
	private void view_ahli(HttpSession session) throws Exception {
    	int id = Integer.parseInt(getParam("ahliMesyuarat"));
    	FrmKemaskiniAhliMesyuaratData.setDataAhli(id);
	   
	  }

}
