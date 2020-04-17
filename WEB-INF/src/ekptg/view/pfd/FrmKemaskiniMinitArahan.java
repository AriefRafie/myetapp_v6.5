package ekptg.view.pfd;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.model.pfd.FrmKemaskiniMinitArahanData;

public class FrmKemaskiniMinitArahan extends AjaxBasedModule{
	
	public String doTemplate2() throws Exception{

    	String vm = "";
    	String action = getParam("action");
    	String command1 = getParam("command1");
    	String mode = getParam("mode");
		HttpSession session = this.request.getSession();
		Vector list = new Vector();
		this.context.put("action",action);
    	
    	if ("tambah".equals(action)){
    		vm = "app/pfd/frmKemaskiniMinitArahan.jsp";
    		FrmKemaskiniMinitArahanData.setData(0);
    		list = FrmKemaskiniMinitArahanData.getData();
    		this.context.put("MinitArahan",list);
			this.context.put("selectPegawaiA",HTML.SelectPegawai("socPegawaiA"));
			this.context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB"));
			this.context.put("mode","New");
			this.context.put("readOnly1","");
			this.context.put("readOnly2","");
			this.context.put("disable","");
			
			if ("simpan".equals(command1)){
				if("tambahBaru".equals(mode)){
					 simpanMinit(session);
				}
			}
    	}
    	else if ("papar".equals(action)){
    		vm = "app/pfd/frmKemaskiniMinitArahan.jsp";
    		view_minit(session);
    		list = FrmKemaskiniMinitArahanData.getData();
    		this.context.put("MinitArahan",list);
    		Hashtable h1 = (Hashtable) list.get(0);
    		this.context.put("selectPegawaiA",HTML.SelectPegawai("socPegawaiA",Long.parseLong(h1.get("pegawai_Mengarah").toString()),"disabled"));
			this.context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB",Long.parseLong(h1.get("pegawai_Menerima").toString()),"disabled"));
			this.context.put("mode","View");
			this.context.put("readOnly1","readonly");
			this.context.put("readOnly2","readonly");
			this.context.put("disable","disabled");
			
			if ("kemaskini".equals(command1)){
				vm = "app/pfd/frmKemaskiniMinitArahan.jsp";
	    		view_minit(session);
	    		list = FrmKemaskiniMinitArahanData.getData();
	    		this.context.put("MinitArahan",list);
	    		Hashtable h2 = (Hashtable) list.get(0);
	    		this.context.put("selectPegawaiA",HTML.SelectPegawai("socPegawaiA",Long.parseLong(h1.get("pegawai_Mengarah").toString()),""));
				this.context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB",Long.parseLong(h1.get("pegawai_Menerima").toString()),""));
				this.context.put("mode","Update");
				this.context.put("readOnly1","readonly");
				this.context.put("readOnly2","");
				this.context.put("disable","");
	
			}
			else if ("simpan".equals(command1)){
				if ("kemaskiniMinitArahan".equals(mode)){
					simpanMinit(session);
				}
			}

    	}
		
		
//		  Template template = this.engine.getTemplate(vm);

          return vm;
		
    }
	private void view_minit(HttpSession session) throws Exception {
    	int id = Integer.parseInt(getParam("passIdMinitArahan"));
    	FrmKemaskiniMinitArahanData.setData(id);
	   
	  }
	private int simpanMinit(HttpSession session) throws Exception {
		 Db db = null;
		 
		 int idMinitarahan;
		
		 if (getParam("idMinitArahan") == "" || Integer.parseInt(getParam("idMinitArahan")) == 0){
			 
			 Hashtable h = new Hashtable();
			 
			 h.put("id_Dokumen",getParam("idDokumen"));
			 h.put("minit_Arahan",getParam("txtMinitArahan"));
			 h.put("id_Pegawai_Ygmengarah", getParam("socPegawaiA"));
			 h.put("id_Pegawai_Ygmenerima", getParam("socPegawaiB"));
			 h.put("status_Tindakan",getParam("socStatusTindakan"));
			 h.put("tarikh_Minit_Arahan",getParam("txdTarikhMinitArahan"));
			
			 idMinitarahan = FrmKemaskiniMinitArahanData.add(h);
			 
			 return idMinitarahan;
		 }
		 else{
			 
			 Hashtable h = new Hashtable();
			 
			 h.put("id_Minitarahan", Integer.parseInt(getParam("idMinitArahan")));
			 h.put("id_Dokumen",getParam("idDokumen"));
			 h.put("minit_Arahan",getParam("txtMinitArahan"));
			 h.put("id_Pegawai_Ygmengarah", getParam("socPegawaiA"));
			 h.put("id_Pegawai_Ygmenerima", getParam("socPegawaiB"));
			 h.put("status_Tindakan",getParam("socStatusTindakan"));
			 h.put("tarikh_Minit_Arahan",getParam("txdTarikhMinitArahan"));
			 idMinitarahan = FrmKemaskiniMinitArahanData.update(h);
			 
			 return idMinitarahan;
		 }
	   
	  }

}
