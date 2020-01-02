package ekptg.view.meps;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmModelLaporanPungutanBayaranFee;

public class PPK_FrmLaporanPungutanBayaranFee extends VTemplate {

	static Logger myLogger = Logger.getLogger(PPK_FrmLaporanPungutanBayaranFee.class);
	FrmModelLaporanPungutanBayaranFee model = new FrmModelLaporanPungutanBayaranFee();

	public Template doTemplate() throws Exception {

		HttpSession session = request.getSession();

		String vm = "";

		Vector dataUnitJkptg = new Vector();
		Vector dataSemak = new Vector();

		dataUnitJkptg.clear();
		dataSemak.clear();

		context.put("Util", new lebah.util.Util());

		String usid="";
   		usid=(String)session.getAttribute("_ekptg_user_id");

   		String submit = getParam("command");
    	myLogger.info("[submit] :: " +submit);

    	if ("doChangeUnit".equals(submit)){

    		//reset data to zero
			context.put("pengeluaran_perintah", "");
			context.put("salinan_perintah", "");
			context.put("fee_rayuan", "");
			context.put("salinan_dokumen", "");
			context.put("duti", "");
			context.put("baitulmal", "");
			context.put("iddaerah", "");
			context.put("bulan", "");
			context.put("lain2", "");

    		String id_negeri = getParam("socNegeri");

    		//dropdown
    		if(id_negeri!=""){
    			context.put("selectUnit",HTML.SelectUnitJKPTGByIdNegeri("socUnit",null,null,"style=width:340",id_negeri));
    			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(id_negeri),null,"style=width:340 onChange=\"doChangeUnit();\" "));
        	}else{
        		context.put("selectUnit",HTML.SelectUnitJKPTG("socUnit",null,null,"style=width:340"));
    			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,null,"style=width:340 onChange=\"doChangeUnit();\" "));
        	}

    		vm = "app/meps/ppk/frmLaporanPungutanBayaranFee.jsp";

    	}//close doChangeTempatBicara

    	else if ("semak".equals(submit)){

    		String id_negeri = getParam("socNegeri");
    		String id_kptg = getParam("socUnit");
    		String id_daerah = "";
    		String year = getParam("tahun");
    		String month = getParam("bulan");

    		//dropdown
    		context.put("selectUnit",HTML.SelectUnitJKPTGByIdNegeri("socUnit",Utils.parseLong(id_kptg),null,"style=width:340",id_negeri));
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(id_negeri),null,"style=width:340 onChange=\"doChangeUnit();\" "));


    		Double pengeluaran_perintah = 0.00;
    		Double salinan_perintah = 0.00;
    		Double fee_rayuan = 0.00;
    		Double salinan_dokumen = 0.00;
    		Double duti = 0.00;
    		Double baitulmal = 0.00;
    		Double lain2 = 0.00;

    		model.setUnitJkptg(id_kptg);
    		dataUnitJkptg = model.getUnitJkptg();
    		if(dataUnitJkptg.size()!=0){
    			Hashtable x = (Hashtable) dataUnitJkptg.get(0);
    			id_daerah = x.get("id_daerah").toString();
    		}

//    		model.setDataSemak(id_negeri,id_daerah,year,month);
//    		dataSemak = model.getDataSemak();
//    		if(dataSemak.size()!=0){
//    			Hashtable z = (Hashtable) dataSemak.get(0);
//    			pengeluaran_perintah = Double.parseDouble(z.get("pengeluaran_perintah").toString());
//        		salinan_perintah = Double.parseDouble(z.get("salinan_perintah").toString());
//        		fee_rayuan = Double.parseDouble(z.get("fee_rayuan").toString());
//        		salinan_dokumen = Double.parseDouble(z.get("salinan_dokumen").toString());
//        		duti = Double.parseDouble(z.get("duti").toString());
//        		baitulmal = Double.parseDouble(z.get("baitulmal").toString());
//        	}

    			//set data
    			context.put("pengeluaran_perintah", pengeluaran_perintah);
    			context.put("salinan_perintah", salinan_perintah);
    			context.put("fee_rayuan", fee_rayuan);
    			context.put("salinan_dokumen", salinan_dokumen);
    			context.put("duti", duti);
    			context.put("baitulmal", baitulmal);
    			context.put("lain2", lain2);
    			context.put("iddaerah", id_daerah);
    			context.put("bulan", month);


    		vm = "app/meps/ppk/frmLaporanPungutanBayaranFee.jsp";

    	}//close semak

    	else{

    		//reset data to zero
			context.put("pengeluaran_perintah", "");
			context.put("salinan_perintah", "");
			context.put("fee_rayuan", "");
			context.put("salinan_dokumen", "");
			context.put("duti", "");
			context.put("baitulmal", "");
			context.put("iddaerah", "");
			context.put("bulan", "");
			context.put("lain2", "");

    		//dropdown
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"style=width:340 onChange=\"doChangeUnit()\" "));
    		context.put("selectUnit",HTML.SelectUnitJKPTG("socUnit",null,null,"style=width:340"));

    		vm = "app/meps/ppk/frmLaporanPungutanBayaranFee.jsp";

   		}//close else

    	   Template template = engine.getTemplate(vm);
           return template;

	}//close template

}//close class

