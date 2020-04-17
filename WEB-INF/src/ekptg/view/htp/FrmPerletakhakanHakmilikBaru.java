package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmPerletakhakanHakmilikBaruData;
import ekptg.model.htp.FrmPerletakhakanPendaftaranData;

public class FrmPerletakhakanHakmilikBaru extends AjaxBasedModule{
	/**
	 * 
	 */
	private final String PATH="app/htp/perletakhakan/pindahmilik/";
	private static final long serialVersionUID = 1928871979537596334L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.FrmPerletakhakanHakmilikBaru.class);	
    private String isCarian = "tidak";
	private FrmPerletakhakanHakmilikBaruData logic = new FrmPerletakhakanHakmilikBaruData();
	private FrmPerletakhakanPendaftaranData penData = new FrmPerletakhakanPendaftaranData(); //data tuk view

	public String doTemplate2() throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost = session.getAttribute("doPost").toString();
		if (doPost.equals("true")) {
			postDB = true;
		}
		
		String submit = getParam("command"); //untuk doAjaxCall
		String action = getParam("action");
		String actionPerletakhakan = getParam("actionPerletakhakan");
		String nextAction = getParam("nextAction");
		String hitButton = getParam("hitButton");
		String ActionLain = getParam("ActionLain");
		String idFail = getParam("idFail");
		System.out.println("idFail: "+idFail);
		String idPermohonan = getParam("idPermohonan");
		String idLetakhakpemilikbaru = getParam("idLetakhakpemilikbaru");
		String idHakmilikurusan = getParam("idHakmilikurusan");
		String noHakmilik = getParam("noHakmilik");
		myLog.info("actionPerletakhakan: "+actionPerletakhakan);
		myLog.info("hitButton: "+hitButton);
		
//		String popupHakmilikBaru ="";
		
		Vector list = null;
		Vector listHakmilik = null;
		
		String vm = "";
		
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		
		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0){
			idDaerah = "99999";
		}
		
		String idMukim = getParam("socMukim");
		if (idMukim == null || idMukim.trim().length() == 0){
			idMukim = "99999";
		}	
		
		//vm = "app/htp/frmPerletakhakanHakmilikBaru.jsp";
		context.remove("SenaraiFail");
		if("".equals(actionPerletakhakan)){
			//vm = "app/htp/frmPerletakhakanSenaraiHakmilikBaru.jsp";
			// set senarai
//			logic.setMaklumatSenarai();
//			list = logic.getMaklumatSenarai();
			penData.getSenaraiFail("", "", "", "", "", "", "",(String)session.getAttribute("_ekptg_user_id"));
			isCarian = getParam("txtcarian");
			if(isCarian.equals("ya")){
				//list = FrmUtilData.getSenaraiFailMengikutUrusanDanPengguna(IDURUSAN, getParam("NamaFail"), getParam("NoFail"),Negeri,null);		
				penData.getSenaraiFail(getParam("txtNoFail"), getParam("txtTajukFail"), "", "", "", "", "",null);
				isCarian = "ya";		
			}
			list = penData.getListFailPerletakhakan();

			//vm = "app/htp/frmPerletakhakanSenaraiFailHakmilik.jsp";
			vm = PATH+"index.jsp";
			//this.context.put("SenaraiFail", list);
			setupPage(session,action,list);
			
		}else if("papar".equals(actionPerletakhakan)){
				vm = "app/htp/frmPerletakhakanHakmilikBaru.jsp";

				//HEADER MASTER
				logic.setMaklumatFailMaster(idFail);
				list = logic.getMaklumatFailMaster();
				if (list.size() != 0){
					Hashtable hashHeader = (Hashtable) logic.getMaklumatFailMaster().get(0);
					//System.out.println("idpermohonan " + hashHeader);
				
					if (idPermohonan.isEmpty()){
						idPermohonan = hashHeader.get("idPermohonan").toString();
					}
					 
				}
				this.context.put("List", list);
				
				Hashtable hashHak = (Hashtable) list.get(0);
				this.context.put("txtNamaSuburusan",hashHak.get("nama_suburusan"));
				
					//LIST HAKMILIK
			    logic.setMaklumatSenaraiHakmilik(idFail);
			    listHakmilik = logic.getMaklumatSenaraiHakmilik();
			    this.context.put("Listhakmilik", listHakmilik);
			    setupPage(session,action,listHakmilik);
				
				//System.out.println("action:"+actionPerletakhakan);
					
				//TUKAR NAMA PEMILIK BARU
					
				this.context.put("mode","new");
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
					
            	this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"", "onChange=\"doChangeNegeri()\""));
            	this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah), "", "onChange=\"doChangeDaerah()\""));
            	this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah, "socMukim", Utils.parseLong(idMukim), "", ""));
            	this.context.put("txtNamaAsal","");
				this.context.put("txtAlamat1", "");
				this.context.put("txtAlamat2", "");
				this.context.put("txtAlamat3", "");
				this.context.put("txtPoskod","");
            	this.context.put("txtNamaBaru", "");
				
			}
			if("doChangeNegeri".equals(nextAction)){
	//			this.context.put("txtNamaAsal","");
	//			this.context.put("txtAlamat1", "");
	//			this.context.put("txtAlamat2", "");
	//			this.context.put("txtAlamat3", "");
	//			this.context.put("txtPoskod", "");
	//			this.context.put("txtNamaBaru", "");
				this.context.put("txtNamaAsal",getParam("txtNamaAsal"));
				this.context.put("txtAlamat1",getParam("txtAlamat1"));
				this.context.put("txtAlamat2", getParam("txtAlamat2"));
				this.context.put("txtAlamat3", getParam("txtAlamat3"));
				this.context.put("txtPoskod", getParam("txtPoskod"));
				this.context.put("txtNamaBaru", getParam("txtNamaBaru"));
				this.context.put("socNegeri", getParam("selectNegeri"));
				this.context.put("socDaerah", getParam("selectDaerah"));
				this.context.put("socMukim", getParam("selectMukim"));
	//			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri)," onChange=\"doChangeNegeri();\" style=\"width:200px\""));
	//			this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah),"","onChange=\"doChangeDaerah();\" style=\"width:200px\""));	
	//			this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim)," style=\"width:200px\""));
				
				//System.out.println("testtttttweweretrt");
			}
			if("doChangeDaerah".equals(nextAction)){
	//			this.context.put("txtNamaAsal","");
	//			this.context.put("txtAlamat1", "");
	//			this.context.put("txtAlamat2", "");
	//			this.context.put("txtAlamat3", "");
	//			this.context.put("txtPoskod", "");
	//			this.context.put("txtNamaBaru", "");
				this.context.put("txtNamaAsal",getParam("txtNamaAsal"));
				this.context.put("txtAlamat1",getParam("txtAlamat1"));
				this.context.put("txtAlamat2", getParam("txtAlamat2"));
				this.context.put("txtAlamat3", getParam("txtAlamat3"));
				this.context.put("txtPoskod", getParam("txtPoskod"));
				this.context.put("txtNamaBaru", getParam("txtNamaBaru"));
				this.context.put("socNegeri", getParam("selectNegeri"));
				this.context.put("socDaerah", getParam("selectDaerah"));
				this.context.put("socMukim", getParam("selectMukim"));
	//			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri)," onChange=\"doChangeNegeri();\" style=\"width:200px\""));
	//			this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah),"","onChange=\"doChangeDaerah();\" style=\"width:200px\""));	
	//			this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim)," style=\"width:200px\""));
				
				System.out.println("testttttt");
			
			}else
				
				if(postDB){
					if("simpanBru".equals(hitButton)){
						idLetakhakpemilikbaru = logic.simpanBaru(idHakmilikurusan,
								getParam("txtNamaAsal"),getParam("txtAlamat1"),
								getParam("txtAlamat2"),getParam("txtAlamat3"),
								getParam("txtNamaBaru"),getParam("txtPoskod"), 
								idDaerah, idMukim,idNegeri, session);
						System.out.println("idLetakhakpemilikbaru"+idLetakhakpemilikbaru);
					
					}else if("simpanKemaskini".equals(hitButton)){
						logic.update(idHakmilikurusan,getParam("txtNamaAsal"),getParam("txtAlamat1"),
						getParam("txtAlamat2"),getParam("txtAlamat3"),
						getParam("txtNamaBaru"),getParam("txtPoskod"), 
						idDaerah, idMukim,idNegeri, session);
					
					}else if("kemaskini".equals(hitButton)){
						System.out.println("alooo1234");
						this.context.put("mode", "update");
						this.context.put("readonly", "");
						this.context.put("inputTextClass", "");
						//VIEW PEMILIK BARU BY ID_LETAKHAKPEMILIKBARU
						viewPemilikbaruByidLetakhakpemilikbaru(hitButton,nextAction,idHakmilikurusan);
					
					}else if("paparSemula".equals(hitButton)){
						viewPemilikbaruByidLetakhakpemilikbaru(hitButton,nextAction,idHakmilikurusan);
			    
					}
					
		}
		
		this.context.put("idFail", idFail);
		this.context.put("idHakmilikurusan", idHakmilikurusan);
		this.context.put("noHakmilik",noHakmilik);
		this.context.put("iscarian", isCarian);
		return vm;
			
	}
	
	private void viewPemilikbaruByidLetakhakpemilikbaru(String hitButton,String nextAction,String idHakmilikurusan) throws Exception {
		Vector infoPekmilikbaru = null;
		infoPekmilikbaru = new Vector();
		logic.setInfoPemilikbaru(idHakmilikurusan);
		infoPekmilikbaru = logic.getInfoPemilikbaru();
		System.out.println("infoPekmilikbaru:"+infoPekmilikbaru);
		
		if(infoPekmilikbaru.size()!= 0){
			Hashtable hashPermohonanDB = (Hashtable) infoPekmilikbaru.get(0);
			
		
			this.context.put("txtNamaAsal",getParam("txtNamaAsal")== "" ? (String) hashPermohonanDB.get("NAMA_ASAL"):getParam("txtNamaAsal"));
			this.context.put("txtAlamat1", hashPermohonanDB.get("ALAMAT"));
			this.context.put("txtAlamat2", hashPermohonanDB.get("ALAMAT1"));
			this.context.put("txtAlamat3", hashPermohonanDB.get("ALAMAT2"));
			this.context.put("txtPoskod", hashPermohonanDB.get("POSKOD"));
			this.context.put("txtNamaBaru", hashPermohonanDB.get("NAMA_BARU"));
			this.context.put("idHakmilikurusan", hashPermohonanDB.get("idHakmilikurusan"));
			this.context.put("idLetakhakpemilikbaru", hashPermohonanDB.get("ID_LETAKHAKPEMILIKBARU"));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong((String) hashPermohonanDB.get("ID_NEGERI")),""," onChange=\"doChangeNegeri();\" style=\"width:200px\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri((String) hashPermohonanDB.get("ID_NEGERI"),"socDaerah",Utils.parseLong((String) hashPermohonanDB.get("ID_DAERAH")),""," onChange=\"doChangeDaerah();\" style=\"width:200px\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah((String) hashPermohonanDB.get("ID_DAERAH"),"socMukim",Utils.parseLong((String) hashPermohonanDB.get("ID_MUKIM"))," style=\"width:200px\""));
			System.out.println("huhuheuhdh123456");
			
			
			if(hitButton.equals("simpanBru")|| hitButton.equals("paparSemula")){
				this.context.put("mode","update");
				this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");
				
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong((String) hashPermohonanDB.get("ID_NEGERI")),"disabled", " class=\"disabled\" style=\"width:200px\""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri("ID_NEGERI","socDaerah",Utils.parseLong((String) hashPermohonanDB.get("ID_DAERAH")),"disabled", " class=\"disabled\" style=\"width:200px\""));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah((String) hashPermohonanDB.get("ID_DAERAH"),"socMukim",Utils.parseLong((String) hashPermohonanDB.get("ID_MUKIM")),"disabled", " class=\"disabled\" style=\"width:200px\""));
			}
//			if(nextAction.equals("doChangeNegeri")){
//				this.context.put("txtNamaAsal",getParam("txtNamaAsal")== "" ? (String) hashPermohonanDB.get("NAMA_ASAL"):getParam("txtNamaAsal"));
//				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong((String) hashPermohonanDB.get("ID_NEGERI")), " onChange=\"doChangeNegeri();\" style=\"width:200px\""));
//				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri((String) hashPermohonanDB.get("ID_NEGERI"),"socDaerah",Long.parseLong((String) hashPermohonanDB.get("ID_DAERAH")),""," onChange=\"doChangeDaerah();\" style=\"width:200px\""));
//				this.context.put("selectMukim", HTML.SelectMukimByDaerah((String) hashPermohonanDB.get("ID_DAERAH"),"socMukim",Long.parseLong((String) hashPermohonanDB.get("ID_MUKIM"))," style=\"width:200px\""));
//				System.out.println("testttttt");
//			}
//			if(nextAction.equals("doChangeDaerah")){
//				this.context.put("txtNamaAsal",getParam("txtNamaAsal")== "" ? (String) hashPermohonanDB.get("NAMA_ASAL"):getParam("txtNamaAsal"));
//				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong((String) hashPermohonanDB.get("ID_NEGERI")), " onChange=\"doChangeNegeri();\" style=\"width:200px\""));
//				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri((String) hashPermohonanDB.get("ID_NEGERI"),"socDaerah",Long.parseLong((String) hashPermohonanDB.get("ID_DAERAH")),""," onChange=\"doChangeDaerah();\" style=\"width:200px\""));
//				this.context.put("selectMukim", HTML.SelectMukimByDaerah((String) hashPermohonanDB.get("ID_DAERAH"),"socMukim",Long.parseLong((String) hashPermohonanDB.get("ID_MUKIM"))," style=\"width:200px\""));
//				System.out.println("semak");
//			}
			else
			{
				this.context.put("mode","new");
				this.context.put("mode", "");
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
			}
		}

	}					    
				
}
