package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import lebah.portal.AjaxBasedModule;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPermohonanData;

public class FrmHakmilikSementaraMaklumatPermohonan extends AjaxBasedModule{
	
	
	@Override
	public String doTemplate2() throws Exception {
		
	
		String vm = "app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp";
		FrmHakmilikSementaraMaklumatPermohonanData prmhnnMaster = new FrmHakmilikSementaraMaklumatPermohonanData();
		
		Vector listAgensi = null;
		
		String id_fail = getParam("id_fail");
		context.put("id_fail", getParam("id_fail"));
		
		String id_permohonan = getParam("id_permohonan");
		context.put("id_permohonan", getParam("id_permohonan"));
		
		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
		Hashtable h = (Hashtable) getrecord_permohonan.get(0);
		context.put("nama_kementerian",h.get("nama_kementerian"));
		context.put("tarikh_terima",h.get("tarikh_terima"));
		context.put("projek_negeri",h.get("projek_negeri"));
		context.put("nama_daerah",h.get("nama_daerah"));
		context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
		context.put("tujuan",h.get("tujuan"));
		context.put("no_fail",h.get("no_fail"));
		context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
		context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
		context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
		context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
		context.put("keterangan",h.get("keterangan"));
		
		
		String idAgensi = h.get("id_agensi").toString();
		if(idAgensi!="")
		{
			int id_agensi = Integer.parseInt(idAgensi);
			listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
			Hashtable ag = (Hashtable) listAgensi.get(0);
			context.put("idAgensi", ag.get("nama_agensi").toString()); 
		}
		else
		{
			context.put("idAgensi","-");
		}
		

		
		return vm;
	}
}
