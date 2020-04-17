package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.model.htp.FrmPembelianTBangunData;
import ekptg.model.htp.FrmPembelianTanahData;

public class PembelianPopup extends VTemplate {
	private String result = new String();
	public Template doTemplate() throws Exception
    {
    	HttpSession session = this.request.getSession();
    	System.out.println("PembelianPopup::");
    	String vm = "";
    	String disability = "";
    	String readability = "";
    	String style1 = "";
    	String style2 = "";
    	Vector list = new Vector();
    	list.clear();
    	Long idPermohonan = Long.parseLong(getParam("idPermohonan"));
    	Long idHakmilikurusan =0L;
    	if (getParam("idHakmilikurusan") != "")
    		idHakmilikurusan = Long.parseLong(getParam("idHakmilikurusan"));
    	else
    		idHakmilikurusan = 0L;
		String submit = getParam("command");
		String mode = getParam("mode");
		
		if("Tanah".equals(submit)){
			vm = "app/htp/frmPembelianTanah.jsp";
			if("simpan".equals(mode)){
				System.out.println("PembelianPopup::Tanah::simpan1");
				idHakmilikurusan = SimpanTanah(session,idPermohonan);
				System.out.println("PembelianPopup::Tanah::simpan2");
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";				
				ListTanah(session,Integer.parseInt(""+idPermohonan),Integer.parseInt(""+idHakmilikurusan));
				DataTanah(session,disability,readability,style1,style2);
				System.out.println("PembelianPopup::Tanah::simpan");
			}else if("kemaskini".equals(mode)){
				style1 = "none";
				ListTanah(session,Integer.parseInt(""+idPermohonan),Integer.parseInt(""+idHakmilikurusan));
				DataTanah(session,disability,readability,style1,style2);
				System.out.println("PembelianPopup::Tanah::kemaskini");
			}else if("view".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
				ListTanah(session,Integer.parseInt(""+idPermohonan),Integer.parseInt(""+idHakmilikurusan));
				DataTanah(session,disability,readability,style1,style2);
				System.out.println("PembelianPopup::Tanah::view");
			}else if("baru".equals(mode)){
				DataTanahBaru(session,disability,readability,style1,style2);
				System.out.println("PembelianPopup::Tanah::baru");
			}
		}else if("TBangun".equals(submit)){
			vm = "app/htp/frmPembelianTBangun.jsp";
			if("simpan".equals(mode)){
				System.out.println("PembelianPopup::TBangun::simpan1");
				idHakmilikurusan = SimpanTBangun(session,Integer.parseInt(""+idPermohonan));
				System.out.println("PembelianPopup::TBangun::simpan2");
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";				
				ListTBangun(session,Integer.parseInt(""+idPermohonan),Integer.parseInt(""+idHakmilikurusan));
				DataTBangun(session,Integer.parseInt(""+idPermohonan),disability,readability,style1,style2);
				System.out.println("PembelianPopup::TBangun::simpan");
			}else if("kemaskini".equals(mode)){
				style1 = "none";
				ListTBangun(session,Integer.parseInt(""+idPermohonan),Integer.parseInt(""+idHakmilikurusan));
				DataTBangun(session,Integer.parseInt(""+idPermohonan),disability,readability,style1,style2);
				System.out.println("PembelianPopup::TBangun::kemaskini");
			}else if("view".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
				ListTBangun(session,Integer.parseInt(""+idPermohonan),Integer.parseInt(""+idHakmilikurusan));
				DataTBangun(session,Integer.parseInt(""+idPermohonan),disability,readability,style1,style2);
				System.out.println("PembelianPopup::TBangun::view");
			}else if("baru".equals(mode)){
				DataTBangunBaru(session,Integer.parseInt(""+idPermohonan),disability,readability,style1,style2);
				System.out.println("PembelianPopup::TBangun::baru");
			}
		}
		this.context.put("IdPermohonan", idPermohonan);
		Template template = this.engine.getTemplate(vm);
        return template;
    }
	
	//*** Pembelian Tanah Controller
	private void ListTanah(HttpSession session, int idPermohonan, int idHakmilikurusan) throws Exception
	{
		FrmPembelianTanahData.setTanahPop(idPermohonan, idHakmilikurusan);
	}
	
	private void DataTanah(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmPembelianTanahData.getTanahPop();
			if(list.size() != 0){
				this.context.put("Tanah", list);
			    Hashtable h = (Hashtable) list.get(0);
			    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("IdNegeri").toString()),disability));
			    this.context.put("selectDaerah",HTML.SelectDaerah("socDaerah",Long.parseLong(h.get("IdDaerah").toString()),disability));
			    this.context.put("selectMukim",HTML.SelectMukim("socMukim",Long.parseLong(h.get("IdMukim").toString()),disability));
			    this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Long.parseLong(h.get("IdJenishakmilik").toString()),disability));
			    this.context.put("selectLot",HTML.SelectLot("socLot",Long.parseLong(h.get("IdLot").toString()),disability));
			    this.context.put("selectLuas",HTML.SelectLuas("socLuas",Long.parseLong(h.get("IdLuas").toString()),disability));
			    this.context.put("selectRizab",HTML.SelectRizab("socRizab",Long.parseLong(h.get("IdRizab").toString()),disability));
			    this.context.put("selectKategori",HTML.SelectKategori("socKategori",Long.parseLong(h.get("IdKategori").toString()),disability));
			    this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			}else{
				this.context.put("Tanah", "");
				System.out.println("PembelianPopup::DataTanah:: list.size() = "+list.size());
			    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"));
			    this.context.put("selectDaerah",HTML.SelectDaerah("socDaerah"));
			    this.context.put("selectMukim",HTML.SelectMukim("socMukim"));
			    this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik"));
			    this.context.put("selectLot",HTML.SelectLot("socLot"));
			    this.context.put("selectLuas",HTML.SelectLuas("socLuas"));
			    this.context.put("selectRizab",HTML.SelectRizab("socRizab"));
			    this.context.put("selectKategori",HTML.SelectKategori("socKategori"));
			    this.context.put("modeSoc", "");
			    this.context.put("mode", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			}
		}catch(Exception ex){
			System.out.println("PembelianPopup::DataTanah::Exception = "+ex);
		}
	}
	
	private void DataTanahBaru(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			this.context.put("Tanah", "");
			System.out.println("PembelianPopup::DataTanah:: list.size() = "+list.size());
		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"));
		    this.context.put("selectDaerah",HTML.SelectDaerah("socDaerah"));
		    this.context.put("selectMukim",HTML.SelectMukim("socMukim"));
		    this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik"));
		    this.context.put("selectLot",HTML.SelectLot("socLot"));
		    this.context.put("selectLuas",HTML.SelectLuas("socLuas"));
		    this.context.put("selectRizab",HTML.SelectRizab("socRizab"));
		    this.context.put("selectKategori",HTML.SelectKategori("socKategori"));
		    this.context.put("modeSoc", "");
		    this.context.put("mode", "");
		    style1 = "none";
		    this.context.put("Style1", style1);
		    this.context.put("Style2", "");
		}catch(Exception ex){
			System.out.println("PembelianPopup::DataTanahBaru::Exception = "+ex);
		}
	}
	
	private Long SimpanTanah(HttpSession session, Long idPermohonan) throws Exception
	{
		Long idHakmilikurusan = 0L;
		System.out.println("PembelianPopup::SimpanTanah");
		if(getParam("idHakmilikurusan") == ""){
			//fail baru
			Hashtable h = new Hashtable();
			h.put("idPermohonan", idPermohonan);
			h.put("socNegeri", getParam("socNegeri"));
			h.put("socDaerah", getParam("socDaerah"));
			h.put("socMukim", getParam("socMukim"));
			h.put("socJenisHakmilik", getParam("socJenisHakmilik"));
			h.put("NoHakmilik", getParam("txtNoHakmilik"));
			h.put("NoLot", getParam("txtNoLot"));
			h.put("socLot", getParam("socLot"));
			h.put("TarikhTerima", getParam("txdTarikhTerima"));
			h.put("Luas", getParam("txtLuas"));
			h.put("socLuas", getParam("socLuas"));
			h.put("NoPelan", getParam("txtNoPelan"));
			h.put("socRizab", getParam("socRizab"));
			h.put("socKategori", getParam("socKategori"));
			h.put("TarikhLuput", getParam("txdTarikhLuput"));
//			h.put("statusTanah", getParam("socStatus"));
			idHakmilikurusan = FrmPembelianTanahData.simpan(h);
			result = "baru";
			System.out.println("PembelianPopup::SimpanTanah::Baru::h = "+h);
			return idHakmilikurusan;
		}else{
			//kemaskini fail
			Hashtable h = new Hashtable();
			idHakmilikurusan = Long.parseLong(getParam("idHakmilikurusan"));
			h.put("idPermohonan", idPermohonan);
			h.put("idHakmilikurusan", idHakmilikurusan);
			h.put("socNegeri", Integer.parseInt(getParam("socNegeri")));
			h.put("socDaerah", getParam("socDaerah"));
			h.put("socMukim", getParam("socMukim"));
			h.put("socJenisHakmilik", getParam("socJenisHakmilik"));
			h.put("NoHakmilik", getParam("txtNoHakmilik"));
			h.put("NoLot", getParam("txtNoLot"));
			h.put("socLot", getParam("socLot"));
			h.put("TarikhTerima", getParam("txdTarikhTerima"));
			h.put("Luas", getParam("txtLuas"));
			h.put("socLuas", getParam("socLuas"));
			h.put("NoPelan", getParam("txtNoPelan"));
			h.put("socRizab", getParam("socRizab"));
			h.put("socKategori", getParam("socKategori"));
			h.put("TarikhLuput", getParam("txdTarikhLuput"));
//			h.put("statusTanah", getParam("socStatus"));
			FrmPembelianTanahData.update(h);
			result = "kemaskini";
			System.out.println("PembelianPopup::SimpanTanah::Kemaskini::h = "+h);
			return idHakmilikurusan;
		}
	}
	
	//*** Pembelian Tanah & Bangunan Controller
	private void ListTBangun(HttpSession session, int idPermohonan, int idHakmilikurusan) throws Exception
	{
		FrmPembelianTBangunData.setTBangunPop(idPermohonan, idHakmilikurusan);
	}
	
	private void DataTBangun(HttpSession session, int idPermohonan, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmPembelianTBangunData.getTBangunPop();
			if(list.size() != 0){
				this.context.put("TBangun", list);
			    Hashtable h = (Hashtable) list.get(0);
			    this.context.put("selectNoHakmilik",FrmPembelianTBangunData.SelectNoHakmilik(idPermohonan,"SocNoHakmilik",h.get("NoHakmilik").toString(),disability));
			    this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			}else{
				this.context.put("TBangun", "");
				System.out.println("PembelianPopup::DataTBangun:: list.size() = "+list.size());
				this.context.put("selectNoHakmilik",FrmPembelianTBangunData.SelectNoHakmilik(idPermohonan,"SocNoHakmilik"));
			    this.context.put("modeSoc", "");
			    this.context.put("mode", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			}
		}catch(Exception ex){
			System.out.println("PembelianPopup::DataTBangun::Exception = "+ex);
		}
	}
	
	private void DataTBangunBaru(HttpSession session,int idPermohonan, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			this.context.put("TBangun", "");
			System.out.println("PembelianPopup::DataTBangun:: list.size() = "+list.size());
		    this.context.put("selectNoHakmilik",FrmPembelianTBangunData.SelectNoHakmilik(idPermohonan,"SocNoHakmilik"));
		    this.context.put("modeSoc", "");
		    this.context.put("mode", "");
		    style1 = "none";
		    this.context.put("Style1", style1);
		    this.context.put("Style2", "");
		}catch(Exception ex){
			System.out.println("PembelianPopup::DataTBangunBaru::Exception = "+ex);
		}
	}
	
	private Long SimpanTBangun(HttpSession session, int idPermohonan) throws Exception
	{
		Long idHakmilikurusan = 0L;
		System.out.println("PembelianPopup::SimpanTanah");
		if(getParam("idHakmilikurusan") == ""){
			//fail baru
			Hashtable h = new Hashtable();
			h.put("idPermohonan", idPermohonan);
			h.put("NoHakmilik", getParam("SocNoHakmilik"));
			h.put("NoPetak", getParam("txtNoPetak"));
			h.put("NoBangunan", getParam("txtNoBangunan"));
			h.put("NoTingkat", getParam("txtNoTingkat"));
			idHakmilikurusan = FrmPembelianTBangunData.simpan(h);
			result = "baru";
			System.out.println("PembelianPopup::SimpanTBangun::Baru::h = "+h);
			return Long.parseLong(""+idHakmilikurusan);
		}else{
			//kemaskini fail
			Hashtable h = new Hashtable();
			//idHakmilikurusan = Integer.parseInt(getParam("idHakmilikurusan"));
			h.put("idPermohonan", idPermohonan);
			h.put("idHakmilikurusan", idHakmilikurusan);
			h.put("NoPetak", getParam("txtNoPetak"));
			h.put("NoBangunan", getParam("txtNoBangunan"));
			h.put("NoTingkat", getParam("txtNoTingkat"));
			FrmPembelianTBangunData.update(h);
			result = "kemaskini";
			System.out.println("PembelianPopup::SimpanTBangun::Kemaskini::h = "+h);
			return Long.parseLong(""+idHakmilikurusan);
		}
	}
}
