
package ekptg.view.integrasi.etanah;

import integrasi.rest.etanah.wpkl.entities.Hakmilik;
import integrasi.rest.etanah.wpkl.ppk.EtanahWPKLPPKManager;
import integrasi.ws.etanah.ppt.ETanahCarianManager;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;
import ekptg.helpers.Paging;
import ekptg.model.entities.Tblrujjenisnopb;
import ekptg.model.htp.PihakBerkepentingan;
import ekptg.model.htp.pembelian.IPemilik;
import ekptg.model.htp.pembelian.PemilikBean;
import ekptg.model.integrasi.CapaianHakmilikeTanahHTP;
import ekptg.model.integrasi.CapaianHakmilikeTanahPPK;
import ekptg.model.integrasi.FrmPopupCapaianHakmilikeTanahData;
import ekptg.model.integrasi.IIntegrasieTanahCarian;
import ekptg.model.integrasi.PemilikPPTBean;

/**
 * @author mohamad Rosli
 */
public class FrmPopupCapaianHakmilikeTanah extends AjaxBasedModule {

	private static final long serialVersionUID = 2124227445129379348L;
	static Logger myLog = Logger.getLogger(FrmPopupCapaianHakmilikeTanah.class);
	
 	private IIntegrasieTanahCarian carianHTP = null;  
 	private IIntegrasieTanahCarian carianPPK = null;  
 	FrmPopupCapaianHakmilikeTanahData logic = new FrmPopupCapaianHakmilikeTanahData();	
	Hakmilik hakmilik = null;
	private IPemilik iPemilik = null;
	private String jenisPB = "0";

	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		String idPengguna = (String)session.getAttribute("_ekptg_user_id");
		String namaPengguna = (String)session.getAttribute("_portal_username");

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "app/integrasi/etanah/frmPopupCapaianHakmilikeTanah.jsp";
		String actionPopup = getParam("actionPopup");
		String hitButt = getParam("hitButt");
//		String submit = getParam("command");
		String idPermohonan = getParam("idPermohonan");
		
		String noResit = getParam("txtNoResit").trim();
		String idHakmilik = getParam("txtHakmilik").trim();
		// akses css
		String modul = getParam("modul");
		this.context.put("modul", modul);
		//Modul PPK
		String idPermohonanSimati = getParam("idPermohonanSimati");
		context.put("idPermohonanSimati", idPermohonanSimati);
		//VECTOR
        Vector<Hashtable<String,String>> list = null;
        Vector beanMaklumatHakmilik = null;
        
        this.context.put("flagMsg", "");
		this.context.put("outputMsg", "");	
		myLog.info("actionPopup="+actionPopup+",hitButt="+hitButt);
		myLog.info("modul="+modul+",idPermohonan="+idPermohonan);

		if (hitButt.equals("hantar")){
			String kodNegeri = idHakmilik.substring(0, 2);
			if ("04".equals(kodNegeri) || "05".equals(kodNegeri)){
				ETanahCarianManager cm = new ETanahCarianManager("E-TANAH");
				if(modul.equals("htp")) {
					ETanahCarianManager.getMaklumatHakmilikeTanahHTP(noResit, idHakmilik, idPermohonan, kodNegeri);
				}else if (modul.equals("ppk")){
					idPermohonan = idPermohonanSimati;
					ETanahCarianManager.getMaklumatHakmilikeTanahPPK(noResit, idHakmilik, idPermohonan, kodNegeri);
				}else if (modul.equals("ppt")) {
					ETanahCarianManager.getMaklumatHakmilikFromEtanah(noResit, idHakmilik, idPermohonan, kodNegeri);
				}
				
//				Hakmilik hakmilik = ETanahCarianManager.getTanah();
//				myLog.info("hakmilik="+hakmilik);
//				myLog.info("hakmilik="+hakmilik.getPihakBerkepentinganList().size());

				this.context.put("hakmilik", hakmilik);

				this.context.put("flagMsg", ETanahCarianManager.getFlagMsg());
				this.context.put("outputMsg", ETanahCarianManager.getOutputMsg());
				
//				EtanahN9MelPPKManager manager = new EtanahN9MelPPKManager();
//				manager.getMaklumatHakmilikFromEtanah(idPermohonanSimati, noResit, idHakmilik, kodNegeri);
//				this.context.put("flagMsg", manager.getFlagMsg());
//				this.context.put("outputMsg", manager.getOutputMsg());
			}else if (kodNegeri.equals("14")){
				EtanahWPKLPPKManager manager = new EtanahWPKLPPKManager();
				hakmilik = manager.getMaklumatHakmilikFromEtanah(idHakmilik, noResit, namaPengguna, idPermohonanSimati);
//				if (hakmilik != null) {
//					listPemilik = hakmilik.getPihakBerkepentinganList();
//					getMaklumatUrusan(hakmilik);
//				}
				//hakmilik = manager.getMaklumatHakmilikFromEtanah(idHakmilik, noResit, namaPengguna, idPermohonanSimati);
//				manager.getMaklumatHakmilikFromEtanah(idPermohonan, noResit, idHakmilik, idPengguna);
				this.context.put("flagMsg", manager.getFlagMsg());
				this.context.put("outputMsg", manager.getOutputMsg());
				
			}		
			
			
		}else if (hitButt.equals("daftar") || hitButt.equals("simpanPemilik")){
			String idHarta = getParam("idPPKHTA");
			String idsPemilik[] = request.getParameterValues("idsPemilik");
			
			if(modul.equals("htp")) {
				idHarta = getCarianHTP().daftarHakmilik(idHarta, getParam("noResit_"), getParam("idHakmilik_"), idPermohonan, idPengguna);
				myLog.info("idHarta="+idHarta);
				if (idsPemilik != null) {
					PihakBerkepentingan pemilik = null;
					for (int i = 0; i < idsPemilik.length; i++) {
						pemilik = new PihakBerkepentingan();						
						String[] parts = idsPemilik[i].split("\\|");					
						//String nama = parts[0];
						String noPengenalan = parts[1].replace("-", "");
//						String ba = parts[2];
//						String bb = parts[3];
						
						//String Idpihakberkepentingan = 
						//pemilik.setIdpihakberkepentingan();
						pemilik.setIdHakmilikUrusan(idHarta);
						pemilik.setNama(parts[0]);
						getRujJenisNoPB(parts[4]);
						pemilik.setJenisPB(jenisPB);
						pemilik.setNoRujukan(noPengenalan);
						pemilik.setIdNegeri("0");
						pemilik.setIdDaerah("0");
						pemilik.setAlamat1("-");
						pemilik.setAlamat2("-");
						pemilik.setAlamat3("-");
						pemilik.setPoskod("00000");
						pemilik.setTel("0");
						pemilik.setFax("0");
		
						//pemilik = 
						getIPemilik().savePemilik(pemilik);
						//manager.insertMaklumatHakmilik(idPermohonanSimati, hakmilik, nama, noPengenalan, ba, bb, idHakmilik, noResit, session);
					
					}
				}
				
				
			}else if (modul.equals("ppk")) { 
				myLog.info("ppk=");
				getCarianPPK().daftarHakmilik(idHarta, getParam("noResit_"), getParam("idHakmilik_"), idPermohonan, idPengguna);
			}else if (modul.equals("ppt")) { 
				logic.daftarHakmilik(idHarta, getParam("noResit_"), getParam("idHakmilik_"), idPermohonan, idPengguna);
				myLog.info("id harta ppt :"+idHarta);
				if (idsPemilik != null) {
					PihakBerkepentingan pemilik = null;
					for (int i = 0; i < idsPemilik.length; i++) {
						pemilik = new PihakBerkepentingan();						
						String[] parts = idsPemilik[i].split("\\|");					
						//String nama = parts[0];
						String noPengenalan = parts[1].replace("-", "");
//						String ba = parts[2];
//						String bb = parts[3];
						
						//String Idpihakberkepentingan = 
						//pemilik.setIdpihakberkepentingan();
						pemilik.setIdHakmilikUrusan(idHarta);
						pemilik.setNama(parts[0]);
						getRujJenisNoPB(parts[4]);
						pemilik.setJenisPB(jenisPB);
						pemilik.setNoRujukan(noPengenalan);
						pemilik.setIdNegeri("0");
						pemilik.setIdDaerah("0");
						pemilik.setAlamat1("-");
						pemilik.setAlamat2("-");
						pemilik.setAlamat3("-");
						pemilik.setPoskod("00000");
						pemilik.setTel("0");
						pemilik.setFax("0");
		
						//pemilik = 
						getIPemilikPPT().savePemilik(pemilik);
						//manager.insertMaklumatHakmilik(idPermohonanSimati, hakmilik, nama, noPengenalan, ba, bb, idHakmilik, noResit, session);
					
					}
				}
			}
			
		}
		
		if (actionPopup.equals("papar")){			
			//vm = "app/integrasi/etanah/frmPopupMaklumatHakmilikeTanah.jsp";
			String idTanah = getParam("idPPKHTA");
			
			Hashtable<String,String> mt = null;
			if(modul.equals("htp")) {
				mt = getCarianHTP().setMaklumatHakmilik(idTanah);
				beanMaklumatHakmilik = getCarianHTP().getBeanMaklumatHakmilik();
			
			}else if (modul.equals("ppk")) { 
				mt = getCarianPPK().setMaklumatHakmilik(idTanah);
				beanMaklumatHakmilik = getCarianPPK().getBeanMaklumatHakmilik();
			
			}else if (modul.equals("ppt")) { 
//				mt = getCarianPPK().setMaklumatHakmilik(getParam("idPPKHTA"));
//				beanMaklumatHakmilik = getCarianPPK().getBeanMaklumatHakmilik();
//				beanMaklumatHakmilik = new Vector();
				mt = logic.setMaklumatHakmilik(idTanah);
				beanMaklumatHakmilik = logic.getBeanMaklumatHakmilik();			
			
			}
			idHakmilik = mt.get("idHakmilik");
			noResit= mt.get("noResit");
			
			Hakmilik hakmilik = ETanahCarianManager.getTanah(idTanah);
			myLog.info("hakmilik="+hakmilik);
			myLog.info("hakmilik="+hakmilik.getPihakBerkepentinganList().size());

			this.context.put("hakmilik", hakmilik);
			this.context.put("listPemilik", hakmilik.getPihakBerkepentinganList());		

			this.context.put("BeanMaklumatHakmilik", beanMaklumatHakmilik);
			
		} else {			
			vm = "app/integrasi/etanah/frmPopupCapaianHakmilikeTanah.jsp";		
			
			list = new Vector<Hashtable<String,String>>();
			list = logic.senaraiCarian(idPermohonan);
			//list = logic.getSenaraiHakmilik();
			this.context.put("SenaraiCarianRasmi", list);
			
			setupPage(session,action,list);
			
		}
		
		this.context.put("txtNoResit", noResit);
		this.context.put("txtHakmilik", idHakmilik);
		this.context.put("idPermohonan", idPermohonan);			
		
		System.out.println("vm : "+vm);
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
				this.context.put("SenaraiTanah",paging.getPage(page));
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
	
	public Tblrujjenisnopb getRujJenisNoPB(String kod) throws Exception {
		Db db = null;
		String sql = " ";
		Tblrujjenisnopb s = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_jenisnopb");
			r.add("kod_jenis_nopb");
			r.add("keterangan");

			if (kod != null)
				r.add("kod_jenis_nopb", kod);

			sql = r.getSQLSelect("tblrujjenisnopb");
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				s = new Tblrujjenisnopb();
				s.setIdJenisnopb(rs.getLong("id_jenisnopb"));
				s.setKodJenisNopb(rs.getString("kod_jenis_nopb"));
				s.setKeterangan(rs.getString("keterangan"));
				jenisPB = String.valueOf(s.getIdJenisnopb());
				
			}
		} finally {
			if (db != null)
				db.close();
		}
		return s;
		
	}
		  
	private IIntegrasieTanahCarian getCarianHTP(){
		if(carianHTP== null)
			carianHTP = new CapaianHakmilikeTanahHTP();
		return carianHTP;
	}
	
	private IIntegrasieTanahCarian getCarianPPK(){
		if(carianPPK== null)
			carianPPK = new CapaianHakmilikeTanahPPK();
		return carianPPK;
	}

	private IPemilik getIPemilik(){
		if(iPemilik==null){
			iPemilik = new PemilikBean();
		}
		return iPemilik;
		
	}
	
	private IPemilik getIPemilikPPT(){
		if(iPemilik==null){
			iPemilik = new PemilikPPTBean();
		}
		return iPemilik;
		
	}
	
	
}
