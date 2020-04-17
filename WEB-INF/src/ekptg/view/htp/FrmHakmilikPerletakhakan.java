package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmHakmilikPerletakhakanData;

public class FrmHakmilikPerletakhakan extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmHakmilikPerletakhakanData logic = new FrmHakmilikPerletakhakanData();

	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost = session.getAttribute("doPost").toString();
		if (doPost.equals("true")) {
			postDB = true;
		}

		//DEFAULT PARAM
		String vm = "";
		String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
		String submit = getParam("command");
		String actionPerletakhakan = getParam("actionPerletakhakan");
		String mode  = getParam("mode");
		String hitButton  = getParam("hitButton");
		
		//ID PARAM
		String idFail = getParam("idFail");
		String idPermohonan = getParam("idPermohonan");
		String idHakmilikurusan = getParam("idHakmilikurusan");
		String idPihakberkepentingan = getParam("idPihakberkepentingan");
		String idBebanan = getParam("idBebanan");
		
		//DROPDOWN PARAM
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}		
		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0){
			idDaerah = "99999";
		}
		String idNegeriPemilik = getParam("socNegeriPemilik");
		if (idNegeriPemilik == null || idNegeriPemilik.trim().length() == 0){
			idNegeriPemilik = "99999";
		}		
		String idDaerahPemilik = getParam("socDaerahPemilik");
		if (idDaerahPemilik == null || idDaerahPemilik.trim().length() == 0){
			idDaerahPemilik = "99999";
		}
		String idNegeriBebanan = getParam("socNegeriBebanan");
		if (idNegeriBebanan == null || idNegeriBebanan.trim().length() == 0){
			idNegeriBebanan = "99999";
		}		
		String idDaerahBebanan = getParam("socDaerahBebanan");
		if (idDaerahBebanan == null || idDaerahBebanan.trim().length() == 0){
			idDaerahBebanan = "99999";
		}
		String idMukim = getParam("socMukim");
		if (idMukim == null || idMukim.trim().length() == 0){
			idMukim = "99999";
		}		
		String idJenisHakmilik = getParam("socJenisHakmilik");
		if (idJenisHakmilik == null || idJenisHakmilik.trim().length() == 0){
			idJenisHakmilik = "99999";
		}		
		String idKategori = getParam("socKategori");
		if (idKategori == null || idKategori.trim().length() == 0){
			idKategori = "99999";
		}		
		String idSubkategori = getParam("socSubkategori");
		if (idSubkategori == null || idSubkategori.trim().length() == 0){
			idSubkategori = "99999";
		}
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}		
		String idLot = getParam("socLot");
		if (idLot == null || idLot.trim().length() == 0){
			idLot = "99999";
		}
		String idJenisBebanan = getParam("socJenisBebanan");
		if (idJenisBebanan == null || idJenisBebanan.trim().length() == 0){
			idJenisBebanan = "99999";
		}
		String idJenisPB = getParam("socJenisPB");
		if (idJenisPB == null || idJenisPB.trim().length() == 0){
			idJenisPB = "99999";
		}
		
		//VECTOR PARAM
		Vector list = null;
		Vector beanHeader = null;
		Vector listHakmilik = null;
		Vector beanHakmilik = null;
		Vector listPemilik = null;
		Vector beanPemilik = null;
		Vector listBebanan = null;
		Vector beanBebanan = null;
		
		//BUTTON ACTION
		if(postDB){
			if("simpanHakmilik".equals(hitButton)){
				idHakmilikurusan = logic.saveHakmilik(idPermohonan,getParam("txtLokasi"),
                 		getParam("txtCukaiSemasa"),
    					getParam("txtSyaratNyata"),
    					getParam("txtNoHakmilik"),
    					getParam("txtNoStrata"),
    					getParam("txtNoPelan"),
    					getParam("txtLuasBersamaan"),
    					getParam("txtNoSyit"),
    					getParam("txtSekatan"),
    					getParam("txtLot"),
    					getParam("txtLuas"),
    					idNegeri,  
    					idLuas,
    					idDaerah,
    					idLot,
    					idKategori,
    					idMukim,
    					idJenisHakmilik,
    				    session);
			}
			
			if("simpanUpdateHakmilik".equals(hitButton)){
				logic.updateHakmilik(idHakmilikurusan,getParam("txtLokasi"),
                 		getParam("txtCukaiSemasa"),
    					getParam("txtSyaratNyata"),
    					getParam("txtNoHakmilik"),
    					getParam("txtNoStrata"),
    					getParam("txtNoPelan"),
    					getParam("txtLuasBersamaan"),
    					getParam("txtNoSyit"),
    					getParam("txtSekatan"),
    					getParam("txtLot"),
    					getParam("txtLuas"),
    					idNegeri,  
    					idLuas,
    					idDaerah,
    					idLot,
    					idKategori,
    					idMukim,
    					idJenisHakmilik,
    				    session);
			}
			
			if("simpanPemilik".equals(hitButton)){
				idPihakberkepentingan = logic.simpanPemilik(idHakmilikurusan,getParam("txtNama"),
						getParam("txtNoPengenalan"),
						getParam("txtAlamat1"),
    					getParam("txtAlamat2"),
    					getParam("txtAlamat3"),
    					getParam("txtPoskod"),
    					idNegeriPemilik,
    					idDaerahPemilik,
    					session);
			}
			
			if("simpanUpdatePemilik".equals(hitButton)){
				logic.updatePemilik(idPihakberkepentingan,getParam("txtNama"),
						getParam("txtNoPengenalan"),
						getParam("txtAlamat1"),
    					getParam("txtAlamat2"),
    					getParam("txtAlamat3"),
    					getParam("txtPoskod"),
    					idNegeriPemilik,
    					idDaerahPemilik,
    					session);
			}
			
			if("simpanBebanan".equals(hitButton)){
				idBebanan = logic.simpanBebanan(idPihakberkepentingan,getParam("txtNoPerserahan"),
    					getParam("txdTarikhDaftar"),
    					getParam("txtNamaPb"),
    					getParam("txtAlamat1"),
    					getParam("txtAlamat2"),
    					getParam("txtAlamat3"),
    					getParam("txtPoskod"),
    					getParam("txtJilid"),
    					getParam("txtFolio"),
    					getParam("txtNoPB"),
    					idNegeriBebanan,
    					idDaerahBebanan,
    					idJenisBebanan,
    					idJenisPB,
    				    session);
			}
			
			if("simpanUpdateBebanan".equals(hitButton)){
				logic.simpanUpdateBebanan(idBebanan,getParam("txtNoPerserahan"),
    					getParam("txdTarikhDaftar"),
    					getParam("txtNamaPb"),
    					getParam("txtAlamat1"),
    					getParam("txtAlamat2"),
    					getParam("txtAlamat3"),
    					getParam("txtPoskod"),
    					getParam("txtJilid"),
    					getParam("txtFolio"),
    					getParam("txtNoPB"),
    					idNegeriBebanan,
    					idDaerahBebanan,
    					idJenisBebanan,
    					idJenisPB,
    				    session);
			}
		}
		
		if ("papar".equals(actionPerletakhakan)){
			
			vm = "app/htp/frmPerletakhakanTabHakmilik.jsp";
			
			//HEADER
			beanHeader = new Vector();
			logic.setMaklumatHeader(idFail);
			beanHeader = logic.getBeanHeader();
			if (beanHeader.size() != 0){
				Hashtable hashHeader = (Hashtable) logic.getBeanHeader().get(0);				
				if (idPermohonan.isEmpty()){
					idPermohonan = hashHeader.get("idPermohonan").toString();
				}
			}
			this.context.put("List", beanHeader);
			
			//SENARAI HAKMILIK
			logic.setMaklumatSenaraiHakmilik(idPermohonan);
	    	listHakmilik = logic.getSenaraiHakmilik();
	    	this.context.put("Listhakmilik", listHakmilik);
	    	
	    	if ("newHakmilik".equals(mode)){
	    		
	    		this.context.put("readonly", "");
	    		this.context.put("inputTextClass", "");
	    		
	    		this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\" style=\"width:200px\""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),"",""));
	    		this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(idJenisHakmilik), "", " style=\"width:200px\""));
	    		this.context.put("selectKategori", HTML.SelectKategori("socKategori", Long.parseLong(idKategori), "", " style=\"width:200px\" "));
	    		this.context.put("selectLuas", HTML.SelectLuas("socLuas", Long.parseLong(idLuas), "", " style=\"width:200px\""));
	    		this.context.put("selectLot", HTML.SelectLot("socLot", Long.parseLong(idLot), "", " style=\"width:200px\""));
	    		
	    		this.context.put("txtLokasi", getParam("txtLokasi"));
	    		this.context.put("txtCukaiSemasa",getParam("txtCukaiSemasa"));
	    		this.context.put("txtSyaratNyata",getParam("txtSyaratNyata"));
	    		this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
	    		this.context.put("txtNoStrata", getParam("txtNoStrata"));
	    		this.context.put("txtNoPelan", getParam("txtNoPelan"));
	    		this.context.put("txtLuasBersamaan", getParam("txtLuasBersamaan"));
	    		this.context.put("txtNoSyit", getParam("txtNoSyit"));
	    		this.context.put("txtSekatan", getParam("txtSekatan"));
	    		this.context.put("txtLot", getParam("txtLot"));
	    		this.context.put("txtLuas", getParam("txtLuas"));
	    		
	    	} else if ("viewHakmilik".equals(mode)){

	    		this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");
				
	    		beanHakmilik = new Vector();
	    		logic.setMaklumatHakmilik(idHakmilikurusan);
	    		beanHakmilik = logic.getBeanHakmilik();
	    		
	    		if(beanHakmilik.size()!= 0){
	    			Hashtable hashPermohonanDB = (Hashtable) beanHakmilik.get(0);

	    			this.context.put("txtLokasi", hashPermohonanDB.get("LOKASI"));
	    			this.context.put("txtCukaiSemasa", hashPermohonanDB.get("CUKAI_TERKINI"));
	    			this.context.put("txtSyaratNyata", hashPermohonanDB.get("SYARAT"));
	    			this.context.put("txtNoPelan", hashPermohonanDB.get("NO_PELAN"));
	    			this.context.put("txtNoStrata", "");
	    			this.context.put("txtLuasBersamaan", hashPermohonanDB.get("LUAS_BERSAMAAN"));
	    			this.context.put("txtSekatan", hashPermohonanDB.get("SEKATAN"));
	    			this.context.put("txtLot", hashPermohonanDB.get("NO_LOT"));
	    			this.context.put("txtLuas", hashPermohonanDB.get("LUAS"));
	    			this.context.put("txtNoSyit", hashPermohonanDB.get("NO_SYIT"));
	    			this.context.put("txtNoHakmilik", hashPermohonanDB.get("NO_HAKMILIK"));
	    			
	    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong((String) hashPermohonanDB.get("ID_NEGERI")),"disabled", " class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri((String) hashPermohonanDB.get("ID_NEGERI"),"socDaerah",Long.parseLong((String) hashPermohonanDB.get("ID_DAERAH")),"disabled", " class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectMukim", HTML.SelectMukimByDaerah((String) hashPermohonanDB.get("ID_DAERAH"),"socMukim",Long.parseLong((String) hashPermohonanDB.get("ID_MUKIM")),"disabled", " class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Long.parseLong((String) hashPermohonanDB.get("ID_JENISHAKMILIK")),"disabled"," class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectKategori", HTML.SelectKategori("socKategori",Long.parseLong((String) hashPermohonanDB.get("ID_KATEGORI")),"disabled"," class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectLuas", HTML.SelectLuas("socLuas",Long.parseLong((String) hashPermohonanDB.get("ID_LUAS")),"disabled"," class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectLot", HTML.SelectLot("socLot",Long.parseLong((String) hashPermohonanDB.get("ID_LOT")),"disabled"," class=\"disabled\" style=\"width:200px\""));
	    		}
	    	} else if ("updateHakmilik".equals(mode)){
	    		
	    		this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				
				beanHakmilik = new Vector();
	    		logic.setMaklumatHakmilik(idHakmilikurusan);
	    		beanHakmilik = logic.getBeanHakmilik();
	    		
	    		if(beanHakmilik.size()!= 0){
	    			Hashtable hashPermohonanDB = (Hashtable) beanHakmilik.get(0);
	    			
	    			if (idNegeri == "99999"){
	    				idNegeri = (String) hashPermohonanDB.get("ID_NEGERI");
	    			}		
	    			if (idDaerah == "99999"){
	    				idDaerah = (String) hashPermohonanDB.get("ID_DAERAH");
	    			}		
	    			if (idMukim == "99999"){
	    				idMukim = (String) hashPermohonanDB.get("ID_MUKIM");
	    			}		
	    			if (idJenisHakmilik == "99999"){
	    				idJenisHakmilik = (String) hashPermohonanDB.get("ID_JENISHAKMILIK");
	    			}		
	    			if (idKategori == "99999"){
	    				idKategori = (String) hashPermohonanDB.get("ID_KATEGORI");
	    			}		
	    			if (idLuas == "99999"){
	    				idLuas = (String) hashPermohonanDB.get("ID_LUAS");
	    			}		
	    			if (idLot == "99999"){
	    				idLot = (String) hashPermohonanDB.get("ID_LOT");
	    			}
	    		}
				
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\" style=\"width:200px\""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),"",""));
	    		this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(idJenisHakmilik), "", " style=\"width:200px\""));
	    		this.context.put("selectKategori", HTML.SelectKategori("socKategori", Long.parseLong(idKategori), "", " style=\"width:200px\" "));
	    		this.context.put("selectLuas", HTML.SelectLuas("socLuas", Long.parseLong(idLuas), "", " style=\"width:200px\""));
	    		this.context.put("selectLot", HTML.SelectLot("socLot", Long.parseLong(idLot), "", " style=\"width:200px\""));
	    		
	    		this.context.put("txtLokasi", getParam("txtLokasi"));
	    		this.context.put("txtCukaiSemasa",getParam("txtCukaiSemasa"));
	    		this.context.put("txtSyaratNyata",getParam("txtSyaratNyata"));
	    		this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
	    		this.context.put("txtNoStrata", getParam("txtNoStrata"));
	    		this.context.put("txtNoPelan", getParam("txtNoPelan"));
	    		this.context.put("txtLuasBersamaan", getParam("txtLuasBersamaan"));
	    		this.context.put("txtNoSyit", getParam("txtNoSyit"));
	    		this.context.put("txtSekatan", getParam("txtSekatan"));
	    		this.context.put("txtLot", getParam("txtLot"));
	    		this.context.put("txtLuas", getParam("txtLuas"));
	    	}
			
		} else if ("paparPemilik".equals(actionPerletakhakan)){
			
			vm = "app/htp/frmPerletakhakanTabPemilikAsal.jsp";
			
			//HEADER
			beanHeader = new Vector();
			logic.setMaklumatHeader(idFail);
			beanHeader = logic.getBeanHeader();
			if (beanHeader.size() != 0){
				Hashtable hashHeader = (Hashtable) logic.getBeanHeader().get(0);				
				if (idPermohonan.isEmpty()){
					idPermohonan = hashHeader.get("idPermohonan").toString();
				}
			}
			this.context.put("List", beanHeader);
			
			//SENARAI PEMILIK
			logic.setMaklumatSenaraiPemilik(idHakmilikurusan);
    	    listPemilik = logic.getSenaraiPemilik();
    		this.context.put("Listpemilik", listPemilik);
	    	
	    	if ("newPemilik".equals(mode)){
	    		
	    		this.context.put("readonly", "");
	    		this.context.put("inputTextClass", "");
	    		
	    		this.context.put("selectNegeriPemilik", HTML.SelectNegeri("socNegeriPemilik",Utils.parseLong(idNegeriPemilik), " onChange=\"doChangeState();\" style=\"width:200px\""));
				this.context.put("selectDaerahPemilik", HTML.SelectDaerahByNegeri(idNegeriPemilik,"socDaerahPemilik", Utils.parseLong(idDaerahPemilik),""," style=\"width:200px\""));
	    		
	    		this.context.put("txtNama", getParam("txtNama"));
	    		this.context.put("txtAlamat1",getParam("txtAlamat1"));
	    		this.context.put("txtAlamat2",getParam("txtAlamat2"));
	    		this.context.put("txtAlamat3", getParam("txtAlamat3"));
	    		this.context.put("txtPoskod", getParam("txtPoskod"));
	    		this.context.put("txtNoPengenalan", getParam("txtNoPengenalan"));
	    		
	    	} else if ("viewPemilik".equals(mode)){

	    		this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");
				
	    		beanPemilik = new Vector();
	    		logic.setMaklumatPemilik(idPihakberkepentingan);
	    		beanPemilik = logic.getBeanPemilik();
	    		
	    		if(beanPemilik.size()!= 0){
	    			Hashtable hashPemilikDB = (Hashtable) beanPemilik.get(0);

	    			this.context.put("txtNama", hashPemilikDB.get("NAMA"));
		    		this.context.put("txtAlamat1",hashPemilikDB.get("ALAMAT1"));
		    		this.context.put("txtAlamat2",hashPemilikDB.get("ALAMAT2"));
		    		this.context.put("txtAlamat3", hashPemilikDB.get("ALAMAT3"));
		    		this.context.put("txtPoskod", hashPemilikDB.get("POSKOD"));
		    		this.context.put("txtNoPengenalan", hashPemilikDB.get("NO_PENGENALAN"));
	    			
	    			this.context.put("selectNegeriPemilik", HTML.SelectNegeri("socNegeriPemilik",Long.parseLong((String) hashPemilikDB.get("ID_NEGERI")),"disabled", " class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectDaerahPemilik", HTML.SelectDaerahByNegeri((String) hashPemilikDB.get("ID_NEGERI"),"socDaerahPemilik",Long.parseLong((String) hashPemilikDB.get("ID_DAERAH")),"disabled", " class=\"disabled\" style=\"width:200px\""));
	    		}
	    	} else if ("updatePemilik".equals(mode)){
	    		
	    		this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				
				beanPemilik = new Vector();
	    		logic.setMaklumatPemilik(idPihakberkepentingan);
	    		beanPemilik = logic.getBeanPemilik();
	    		
	    		if(beanPemilik.size()!= 0){
	    			Hashtable hashPemilikDB = (Hashtable) beanPemilik.get(0);
	    			
	    			if (idNegeriPemilik == "99999"){
	    				idNegeriPemilik = (String) hashPemilikDB.get("ID_NEGERI");
	    			}		
	    			if (idDaerahPemilik == "99999"){
	    				idDaerahPemilik = (String) hashPemilikDB.get("ID_DAERAH");
	    			}
	    		}
				
				this.context.put("selectNegeriPemilik", HTML.SelectNegeri("socNegeriPemilik",Utils.parseLong(idNegeriPemilik), " onChange=\"doChangeState();\" style=\"width:200px\""));
				this.context.put("selectDaerahPemilik", HTML.SelectDaerahByNegeri(idNegeriPemilik,"socDaerahPemilik", Utils.parseLong(idDaerahPemilik),""," style=\"width:200px\""));
	    		
				this.context.put("txtNama", getParam("txtNama"));
	    		this.context.put("txtAlamat1",getParam("txtAlamat1"));
	    		this.context.put("txtAlamat2",getParam("txtAlamat2"));
	    		this.context.put("txtAlamat3", getParam("txtAlamat3"));
	    		this.context.put("txtPoskod", getParam("txtPoskod"));
	    		this.context.put("txtNoPengenalan", getParam("txtNoPengenalan"));
	    	}
			
		} else if ("paparBebanan".equals(actionPerletakhakan)){
			
			vm = "app/htp/frmPerletakhakanTabUrusanSediaAda.jsp";
			
			//HEADER
			beanHeader = new Vector();
			logic.setMaklumatHeader(idFail);
			beanHeader = logic.getBeanHeader();
			if (beanHeader.size() != 0){
				Hashtable hashHeader = (Hashtable) logic.getBeanHeader().get(0);				
				if (idPermohonan.isEmpty()){
					idPermohonan = hashHeader.get("idPermohonan").toString();
				}
			}
			this.context.put("List", beanHeader);
			
			//SENARAI Bebanan
			logic.setMaklumatSenaraiBebanan(idPihakberkepentingan);
    	    listBebanan = logic.getSenaraiBebanan();
    		this.context.put("ListBebanan", listBebanan);
	    	
	    	if ("newBebanan".equals(mode)){
	    		
	    		this.context.put("readonly", "");
	    		this.context.put("inputTextClass", "");
	    		
	    		this.context.put("selectJenisBebanan", HTML.SelectBebanan("socJenisBebanan", Long.parseLong(idJenisBebanan), "style=\"width:200px\""));
  		  		this.context.put("selectJenisPB", HTML.SelectJenisNoPb("socJenisPB", Long.parseLong(idJenisPB), "style=\"width:200px\""));
	    		this.context.put("selectNegeriBebanan", HTML.SelectNegeri("socNegeriBebanan",Utils.parseLong(idNegeriBebanan), " onChange=\"doChangeState();\" style=\"width:200px\""));
				this.context.put("selectDaerahBebanan", HTML.SelectDaerahByNegeri(idNegeriBebanan,"socDaerahBebanan", Utils.parseLong(idDaerahBebanan),""," style=\"width:200px\""));
	    		
	    		this.context.put("txtNoPerserahan", getParam("txtNoPerserahan"));
	    		this.context.put("txtJilid",getParam("txtJilid"));
	    		this.context.put("txtFolio",getParam("txtFolio"));
	    		this.context.put("txdTarikhDaftar", getParam("txdTarikhDaftar"));
	    		this.context.put("txtNoPB", getParam("txtNoPB"));
	    		this.context.put("txtNamaPb", getParam("txtNamaPb"));	    		
	    		this.context.put("txtAlamat1", getParam("txtAlamat1"));
	    		this.context.put("txtAlamat2", getParam("txtAlamat2"));
	    		this.context.put("txtAlamat3", getParam("txtAlamat3"));
	    		this.context.put("txtPoskod", getParam("txtPoskod"));
	    		
	    	} else if ("viewBebanan".equals(mode)){

	    		this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");
				
	    		beanBebanan = new Vector();
	    		logic.setMaklumatBebanan(idBebanan);
	    		beanBebanan = logic.getBeanBebanan();
	    		
	    		if(beanBebanan.size()!= 0){
	    			Hashtable hashBebananDB = (Hashtable) beanBebanan.get(0);

	    			this.context.put("txtNoPerserahan", hashBebananDB.get("NO_PERSERAHAN"));
		    		this.context.put("txtJilid",hashBebananDB.get("JILID"));
		    		this.context.put("txtFolio",hashBebananDB.get("FOLIO"));
		    		this.context.put("txdTarikhDaftar", hashBebananDB.get("TARIKH_DAFTAR"));
		    		this.context.put("txtNoPB", hashBebananDB.get("NO_PIHAK_BERKEPENTINGAN"));
		    		this.context.put("txtNamaPb", hashBebananDB.get("NAMA_PIHAK_BERKEPENTINGAN"));	    		
		    		this.context.put("txtAlamat1", hashBebananDB.get("ALAMAT1"));
		    		this.context.put("txtAlamat2", hashBebananDB.get("ALAMAT2"));
		    		this.context.put("txtAlamat3", hashBebananDB.get("ALAMAT3"));
		    		this.context.put("txtPoskod", hashBebananDB.get("POSKOD"));
	    			
		    		this.context.put("selectJenisBebanan", HTML.SelectBebanan("socJenisBebanan", Long.parseLong((String) hashBebananDB.get("ID_RUJBEBANAN")), "disabled", " class=\"disabled\" style=\"width:200px\""));
	  		  		this.context.put("selectJenisPB", HTML.SelectJenisNoPb("socJenisPB", Long.parseLong((String) hashBebananDB.get("ID_JENISPB")),"disabled", " class=\"disabled\" style=\"width:200px\""));
	    			this.context.put("selectNegeriBebanan", HTML.SelectNegeri("socNegeriBebanan",Long.parseLong((String) hashBebananDB.get("ID_NEGERI")),"disabled", " class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectDaerahBebanan", HTML.SelectDaerahByNegeri((String) hashBebananDB.get("ID_NEGERI"),"socDaerahBebanan",Long.parseLong((String) hashBebananDB.get("ID_DAERAH")),"disabled", " class=\"disabled\" style=\"width:200px\""));
	    		}
	    	} else if ("updateBebanan".equals(mode)){
	    		
	    		this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				
				beanBebanan = new Vector();
	    		logic.setMaklumatBebanan(idBebanan);
	    		beanBebanan = logic.getBeanBebanan();
	    		
	    		if(beanBebanan.size()!= 0){
	    			Hashtable hashBebananDB = (Hashtable) beanBebanan.get(0);
	    			
	    			if (idJenisBebanan == "99999"){
	    				idJenisBebanan = (String) hashBebananDB.get("ID_RUJBEBANAN");
	    			}		
	    			if (idJenisPB == "99999"){
	    				idJenisPB = (String) hashBebananDB.get("ID_JENISPB");
	    			}
	    			if (idNegeriBebanan == "99999"){
	    				idNegeriBebanan = (String) hashBebananDB.get("ID_NEGERI");
	    			}		
	    			if (idDaerahBebanan == "99999"){
	    				idDaerahBebanan = (String) hashBebananDB.get("ID_DAERAH");
	    			}
	    		}
				
	    		this.context.put("selectJenisBebanan", HTML.SelectBebanan("socJenisBebanan", Long.parseLong(idJenisBebanan), "style=\"width:200px\""));
  		  		this.context.put("selectJenisPB", HTML.SelectJenisNoPb("socJenisPB", Long.parseLong(idJenisPB), "style=\"width:200px\""));
	    		this.context.put("selectNegeriBebanan", HTML.SelectNegeri("socNegeriBebanan",Utils.parseLong(idNegeriBebanan), " onChange=\"doChangeState();\" style=\"width:200px\""));
				this.context.put("selectDaerahBebanan", HTML.SelectDaerahByNegeri(idNegeriBebanan,"socDaerahBebanan", Utils.parseLong(idDaerahBebanan),""," style=\"width:200px\""));
	    		
	    		this.context.put("txtNoPerserahan", getParam("txtNoPerserahan"));
	    		this.context.put("txtJilid",getParam("txtJilid"));
	    		this.context.put("txtFolio",getParam("txtFolio"));
	    		this.context.put("txdTarikhDaftar", getParam("txdTarikhDaftar"));
	    		this.context.put("txtNoPB", getParam("txtNoPB"));
	    		this.context.put("txtNamaPb", getParam("txtNamaPb"));	    		
	    		this.context.put("txtAlamat1", getParam("txtAlamat1"));
	    		this.context.put("txtAlamat2", getParam("txtAlamat2"));
	    		this.context.put("txtAlamat3", getParam("txtAlamat3"));
	    		this.context.put("txtPoskod", getParam("txtPoskod"));
	    	}
			
		} else {
			
			//GO TO SENARAI FAIL
			vm = "app/htp/frmPerletakhakanSenaraiFailHakmilik.jsp";
			
			list = new Vector();
			logic.carianFail(getParam("txtNoFail"));
			list = logic.getSenaraiFail();
			this.context.put("SenaraiFail", list);
			
			this.context.put("txtNoFail", getParam("txtNoFail"));
			setupPage(session,action,list);
		}
		
		//SET DEFAULT PARAM
		this.context.put("actionPerletakhakan", actionPerletakhakan);
		this.context.put("mode", mode);
		
		//SET ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idHakmilikurusan", idHakmilikurusan);
		this.context.put("idPihakberkepentingan", idPihakberkepentingan);
		this.context.put("idBebanan", idBebanan);
		
		return vm;
	}
	
	public void setupPage(HttpSession session,String action,Vector list) {
		
		try {
		
			this.context.put("totalRecords",list.size());
			int page = getParam("page") == "" ? 1:getParamAsInteger("page");
			
			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer)this.context.get("itemsPerPage");
			}
		    
		    if ("getNext".equals(action)) {
		    	page++;
		    } else if ("getPrevious".equals(action)) {
		    	page--;
		    } else if ("getPage".equals(action)) {
		    	page = getParamAsInteger("value");
		    } else if ("doChangeItemPerPage".equals(action)) {
		       itemsPerPage = getParamAsInteger("itemsPerPage");
		    }
		    	
		    Paging paging = new Paging(session,list,itemsPerPage);
			
			if (page > paging.getTotalPages()) page = 1; //reset page number
				this.context.put("SenaraiFail",paging.getPage(page));
			    this.context.put("page", new Integer(page));
			    this.context.put("itemsPerPage", new Integer(itemsPerPage));
			    this.context.put("totalPages", new Integer(paging.getTotalPages()));
			    this.context.put("startNumber", new Integer(paging.getTopNumber()));
			    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
			    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}
}
