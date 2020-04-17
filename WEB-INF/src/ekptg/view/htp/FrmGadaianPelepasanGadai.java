/**
 * 
 */
package ekptg.view.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.model.entities.MaklumatGadaian;
import ekptg.model.entities.Tblrujagensi;
import ekptg.model.entities.Tblrujkementerian;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.htp.FrmGadaianPelepasanGadaianData;
import ekptg.model.htp.FrmGadaianPelepasanMaklumatPeminjam;
import ekptg.model.htp.FrmGadaianSemakan1Data;
import ekptg.model.htp.FrmGadaianSemakanData;
import ekptg.model.htp.FrmGadaianSenaraiPermohonanData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmSenaraiFailGadaianData;
import ekptg.model.htp.FrmSenaraiFailPelepasanGadaianData;
import ekptg.model.htp.GadaianSubUrusanAgensi;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.PihakBerkepentingan;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.gadaian.GadaianPelepasanBean;
import ekptg.model.htp.gadaian.IGadaianPelepasan;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.IPemilik;
import ekptg.model.htp.pembelian.PembelianBean;
import ekptg.model.htp.pembelian.PemilikBean;

/**
 * @author Firzan
 *
 */
public class FrmGadaianPelepasanGadai extends AjaxBasedModule {

	private final String PATH="app/htp/gadaian/";
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ekptg.view.htp.FrmGadaianPelepasanGadai.class);
	private Date now = new Date();
    private SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
	private String submit = null;
	private String mode = null;
	private IGadaianPelepasan iGadaianPelepasan = null;
	private IPembelian iPembelian = null;
	private IPemilik iPemilik = null;
	private PihakBerkepentingan pemilik = null;

	Vector<?> v = null;
	Vector<?> list = null;
	String result = null;
	
	@Override
	public String doTemplate2() throws Exception {
		String vm = PATH+"frmGadaianPelepasanGadai.jsp";;
		HttpSession session = request.getSession();
		this.submit = getParam("command");
		this.mode = getParam("mode");
		String action = getParam("action");
		String action1 = getParam("action1");
		String negeri = getParam("negeri");
		String noFailLain = getParam("NoFailLain");
		String peminjam = getParam("peminjam");
		String urusan = getParam("urusan");
		log.info("submit :: "+submit+",mode :: "+mode);
		
		if("FailBaru1".equalsIgnoreCase(submit)){
			vm = PATH+"frmGadaianPelepasanGadaiSemakan2Ajax.jsp";
		//	vm = "app/htp/frmGadaianSemakanPelepasanAjax.jsp";
			String idFail = "0";
			if(mode.equalsIgnoreCase("view")){
				String strFail = getParam("idFail");			
				idFail = getParam("idFail");
			    setFailBaru(session,idFail);
			    ViewFailBaru(session,"disabled","readonly");
				
			}else if(mode.equalsIgnoreCase("kemaskini")){
				idFail = getParam("idFail");
			    setFailBaru(session,idFail);
			    ViewFailBaru(session,"","");
			
			}else if (mode.equalsIgnoreCase("simpan")){			
				idFail = SimpanFailBaru(session);
				setFailBaru(session,idFail);
				ViewFailBaru(session,"disabled","readonly");
				this.context.put("SimpanStatus", "success");
				this.context.put("ResultSimpan", result);
			
//			}else if("".equalsIgnoreCase("dochangeurusan")){
//				DataFailBaru(session, "", "");
//			}else if(submit.equalsIgnoreCase("doChangeUrusan")){
//								 context.put("noFailLain", "");
//								 context.put("peminjam", "");
//								 context.put("urusan", urusan);
//								 context.put("selectUrusan", gadai.getSubUrusanForGadaian("socUrusan", Long.parseLong(urusan), "", "onchange=\"doChangeUrusan()\""));
//								 context.put("selectNegeri", HTML.SelectNegeri("socNegeri"));
//								
//							}
				
			}else{
				//this.context.put("pagemode", "view");	
				this.context.put("pagemode", "new");	
				DataFailBaru(session, "", "");

			}

		}else if(submit.equalsIgnoreCase("semakan")){			
			/*********************************************
			 * template : app/htp/frmGadaianSemakanPelepasanAjax.jsp
			 * *******************************************/
			
			vm = "app/htp/frmGadaianSemakanPelepasanAjax.jsp";
			String idPermohonan = "";
			this.context.put("NoFail", getParam("noFail"));
		    this.context.put("IdFail", getParam("idFail"));
		    this.context.put("IdPermohonan", getParam("idPermohonan"));
		    this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmGadaianSemakan"));
		    this.context.put("semakclass", new FrmSemakan());

			if(mode.equalsIgnoreCase("baru")){
				ListSemakBaru(session);
			    DataSemakBaru(session,"","");
			
			}else if(mode.equalsIgnoreCase("kemaskini")){				
				ListSemak(session,idPermohonan);
			    DataSemak(session,"","");
			    
			}else if(mode.equalsIgnoreCase("simpan")){

//				vm = "app/htp/frmGadaianSemakanAjax.jsp";
				idPermohonan = SimpanSemak(session);

				String[] cbsemaks = this.request.getParameterValues("cbsemaks");
								
	        	FrmSemakan frmSemak = new FrmSemakan();
	        	frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
	        	if(cbsemaks!=null){
	        		for (int i = 0; i < cbsemaks.length; i++) { 
	        			frmSemak = new FrmSemakan();
	        			//log.info("GadaianProcess:Semakan::simpan::cbsemaks:::"+cbsemaks[i]);
	        			frmSemak.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));           
	        		}
	        	}
					        	
				ListSemak(session,idPermohonan);
			    DataSemak(session,"disabled","readonly");

			    this.context.put("IdPermohonan", idPermohonan);
			    this.context.put("SimpanStatus", "success");
				this.context.put("ResultSimpan", result);			
				
			}else{
			    ListSemak(session,idPermohonan);
			    DataSemak(session,"disabled","readonly");
			}
		
		}else if(submit.equalsIgnoreCase("SenaraiFailGadaian")){
			vm = "app/htp/frmGadaianSenaraiFailGadaian.jsp";
			//negeri = getParam("socNegeri") ;
			SenaraiFailUntukMelepasGadai(urusan,negeri);
			list = FrmGadaianPelepasanGadaianData.getFailGadaian();
			this.context.put("Senaraifail", list);
			this.context.put("idFail", getParam("idFail"));
			
		}else if(submit.equalsIgnoreCase("carian")){
//			CarianFailGadaian(urusan, noFailLain, negeri, peminjam);	
		
		}else if(submit.equalsIgnoreCase("SenaraiPermohonan")){			
			vm = PATH+"frmGadaianPelepasanGadaiSenaraiPermohonan.jsp";
			String idFail = getParam("idFail");
			String carian = getParam("NamaPemohon");
			String noFailKJP = getParam("noFailKJP");

			ListPermohonan(session, idFail,noFailKJP, carian);
			list = FrmGadaianSenaraiPermohonanData.getListPermohonan();
			doListing(session,action,submit,mode,list);
		    this.context.put("SenaraiPermohonan", list);
		    this.context.put("NoFail", getParam("noFail"));
		    this.context.put("IdFail", getParam("idFail"));
		    this.context.put("carian", getParam("NamaPemohon"));
		
		}else if(submit.equalsIgnoreCase("DaftarPelepasan")){
			//vm = "app/htp/frmGadaianPelepasanGadai.jsp";
			vm = PATH+"frmGadaianPelepasanGadaiFailSenaraiPermohonan.jsp";
			String failGadaian = getParam("PelepasanGadai");
			String idFail = getParam("idFail");
			log.info("fail untuk melepas gadaian : " + failGadaian);
			DaftarFailPelepasan(idFail,failGadaian);
			
		}else if(submit.equalsIgnoreCase("TambahPermohonanPelepasanGadai")){
			vm = PATH+"frmGadaianPelepasanGadaiFailSenaraiPermohonan.jsp";
			String idFail = getParam("idFail");
			String noFail = getParam("noFail");
			FrmSenaraiFailPelepasanGadaianData p =new FrmSenaraiFailPelepasanGadaianData();
			//ListPermohonanPelepasanGadaian(idFail, noFail);
			//list = FrmSenaraiFailPelepasanGadaianData.getSenaraiFailPelepasan();
			list = p.getPermohonanPelepasanGadaian(idFail, noFail);
			this.context.put("Senaraifail", list);	
			
		}else if(submit.equalsIgnoreCase("PelepasanGadaiDetailsBaru")){		
			vm = PATH+"frmGadaianPelepasanGadaiPeminjam.jsp";
			FrmGadaianPelepasanMaklumatPeminjam p =new FrmGadaianPelepasanMaklumatPeminjam();
			String idFail = getParam("idFail");
			String idPermohonan = getParam("idPermohonan");
			log.info("1. PelepasanGadaiDetails:"+idFail+"-"+idPermohonan);
			Hashtable<?, ?> peminjamHash = (Hashtable<?, ?>)p.maklumatPeminjam(idFail, idPermohonan);
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(idPermohonan);
			//pemilik = getIPemilik().findPemilik(m.getKodjenishakmilik().);
			Vector<PihakBerkepentingan> pb = getIPemilik().findPemilikByPermohonan(idPermohonan);

			this.context.put("mt", m);
			this.context.put("pp", pb);

			//this.context.put("Peminjam", list);
			this.context.put("peminjam", peminjamHash);
			this.context.put("pagemode", "new");
			this.context.put("TarikhLepasGadai", "");
			this.context.put("idFail", idFail);
			this.context.put("idPermohonan", idPermohonan);
			
		}else if(submit.equalsIgnoreCase("PelepasanGadaiDetails")){		
			vm = PATH+"frmGadaianPelepasanGadaiPeminjam.jsp";
			FrmGadaianPelepasanMaklumatPeminjam p =new FrmGadaianPelepasanMaklumatPeminjam();
			String idFail = getParam("idFail");
			String idPermohonan = getParam("idPermohonan");
			log.info("1. PelepasanGadaiDetails:"+idFail+"-"+idPermohonan);
			String idFailLama = getParam("idfaillama");

			Hashtable<?, ?> peminjamHash = (Hashtable<?, ?>)p.maklumatPeminjam(idFailLama, idPermohonan);
			log.info("1. PelepasanGadaiDetails:"+peminjamHash);
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(idPermohonan);
			Vector<PihakBerkepentingan> pb = getIPemilik().findPemilikByPermohonan(idPermohonan);
			MaklumatGadaian mg = getIGadaianPelepasan().cariMaklumat(idPermohonan);
			this.context.put("mt", m);
			this.context.put("pp", pb);

			//this.context.put("Peminjam", list);
			this.context.put("peminjam", peminjamHash);
			//this.context.put("pagemode", "baru");
			this.context.put("pagemode", "view");
			this.context.put("TarikhLepasGadai", formatter.format(mg.getTarikhLepasgadai()));
			this.context.put("idFail", idFail);
			this.context.put("idPermohonan", idPermohonan);
				
		}else if(submit.equalsIgnoreCase("kemaskinipelepasan")){		
			vm = PATH+"frmGadaianPelepasanGadaiPeminjam.jsp";
			FrmGadaianPelepasanMaklumatPeminjam p =new FrmGadaianPelepasanMaklumatPeminjam();
			String idFail = getParam("idFail");
			log.info("1. PelepasanGadaiDetails:"+idFail);
			String idPermohonan = getParam("idPermohonan");
			Hashtable<?, ?> peminjamHash = (Hashtable<?, ?>)p.maklumatPeminjam(idFail, idPermohonan);
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(idPermohonan);
			//pemilik = getIPemilik().findPemilik(m.getKodjenishakmilik().);
			Vector<PihakBerkepentingan> pb = getIPemilik().findPemilikByPermohonan(idPermohonan);
			MaklumatGadaian mg = getIGadaianPelepasan().cariMaklumat(idPermohonan);

			this.context.put("mt", m);
			this.context.put("pp", pb);

			//this.context.put("Peminjam", list);
			this.context.put("peminjam", peminjamHash);
			this.context.put("pagemode", "update");
			this.context.put("TarikhLepasGadai", formatter.format(mg.getTarikhLepasgadai()));
			this.context.put("idFail", idFail);
			this.context.put("idPermohonan", idPermohonan);
			
		}else if(submit.equalsIgnoreCase("simpanpelepasan")){
			vm = PATH+"frmGadaianPelepasanGadaiPeminjam.jsp";
			String idFail = getParam("idFail");
			String idPermohonan = getParam("idPermohonan");
			SimpanPelepasanGadaian(idFail,idPermohonan, session);
			ViewPermohonanLepasGadai(idFail,idPermohonan);
		
		}else{
			String key_cari = getParam("NamaFail");
			String keyNo_cari = getParam("NoFail");
			String Negeri = getParam("socNegeri")==""?"20":getParam("socNegeri");
			Long idNegeri = Long.parseLong(Negeri);
			list = getSenaraiFailPelepasan(session, key_cari, keyNo_cari, Negeri);
			doListing(session,action,"",mode,list);

	    	this.context.put("selectNegeri",UtilHTML.SelectNegeri("socNegeri",idNegeri,""));
		    this.context.put("Senaraifail", list);
		    this.context.put("carian", getParam("NamaFail"));
		    this.context.put("carianNoFail", getParam("NoFail"));
		    this.context.put("Negeri", idNegeri);
			//log.info("GadaianProcess::SenaraiFail");
			
		}

		log.info("vm :" + vm);
		return vm;
	}
	
	
	//all the function located here
	
//	public void CarianFailGadaian(String urusan, String noFailLain, String negeri, String peminjam){
//		Db db = null;
//		String sql = "";
//		
//		Hashtable h = null;
//		
//
//		try {
//			db = new Db();
//			Statement stmt = db.getStatement();
//								
//			String tajuk = "";
//			
//			/*
//			 *  suburusan :
//			 * 
//			 *  38 - pinjaman perumahan perbendaharaan (PP)
//			 *  39 - pinjaman kos rendah jabatan perumahan negera (KPKT)
//			 *  40 - pinjaman tmp & skm
//			 *  41 - peletakhakan hak gadaian
//			 *  
//			 *  negeri :
//			 *  
//			 *  12 - sabah
//			 *  13 - sarawak
//			 *  
//			 */ 
//			
//			
//			
//			
//			if(urusan.equalsIgnoreCase("38")){
//				
//				if(negeri.equalsIgnoreCase("12")){
//					tajuk = "satisfaction of charge".toUpperCase();
//				}
//				else{
//					tajuk = "memorandum of charge".toUpperCase();
//				}
//			}
//			
//			if(urusan.equalsIgnoreCase("39")){
//					tajuk = "menandatangani borang gadaian 16a".toUpperCase();
//			}
//			
//			if(urusan.equalsIgnoreCase("40")){
//				tajuk = "menandatangani borang gadaian 16a".toUpperCase();
//			}
//			
//			if(urusan.equalsIgnoreCase("41")){
//				tajuk = "menandatangani borang gadaian 16a".toUpperCase();
//			}
//			
//			
//			String select = "SELECT  ";
//				   select += " ";
//				   
//			String from = "FROM  ";
//				   from += " ";
//				   
//			String where = "WHERE peguam.id_permohonan = mohon.id_permohonan  ";
//			where += "AND pihak.id_hakmilikurusan = hakmilik.id_hakmilikurusan  ";
//			where += "AND hakmilik.id_permohonan = mohon.id_permohonan ";
//			where += "AND htppohon.id_permohonan = mohon.id_permohonan ";
//			where += "AND hakmilik.id_daerah = daerah.id_daerah ";
//			where += "AND hakmilik.id_negeri = negeri.id_negeri  ";
//			where += "AND hakmilik.id_mukim = mukim.id_mukim ";
//			where += "AND htppohon.no_rujukan_lain LIKE '%"+ "" +"%' ";
//			//where += "AND htppohon.no_rujukan_lain LIKE '%"+ noFailPP +"%' ";
//			
//			sql = select + from + where;
//			
//			log.info("frmGadaianSenaraiPP :: SQL : " + sql);
//			
//			ResultSet rs = stmt.executeQuery(sql);
//			
//			int bil = 1;
//			h = new Hashtable();
//			v = new Vector();
//			
//			while(rs.next()){
//				h.put("bil", bil);
//				h.put("peguam", rs.getString("tetuan"));
//				h.put("peminjam", rs.getString("peminjam"));
//				
//				h.put("nolot", rs.getString("no_lot"));
//				h.put("daerah", rs.getString("nama_daerah"));
//				h.put("mukim", rs.getString("nama_mukim"));
//				h.put("negeri", rs.getString("nama_negeri"));
//				h.put("noPP", rs.getString("no_rujukan_lain"));
//				v.add(h);
//				bil++;
//				
//			}
//			
//			log.info("Hashtable " + h);
//
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		} finally {
//			if (db != null) {
//				db.close();
//			}
//		}
//			
//	}
//	
//	public Vector getList() {
//		log.info("frmGadaianPelepasan:: getList : "+ v.size());
//		return v;
//	}
	
	/*
	 * list permohonan yang terdapat dalam sesuatu fail. Cth permohonan A dan B dlm fail MOC, 
	 */
	public void ListPermohonan(HttpSession session, String idFail,String noFail, String carian) 
		throws Exception {
		try{
			FrmGadaianSenaraiPermohonanData.setListPermohonan(idFail,noFail,carian);
		
		}catch(Exception e){
			log.error("Error : " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	/*
	 * list semua fail2 yang berkaitan. Cth : Fail MOC,DOC, SOC
	 */
	public void ListFail(HttpSession session, String key_cari, String keyNo_cari, Long idNegeri) 
		throws Exception{
		try{
			FrmSenaraiFailGadaianData.setList(key_cari,keyNo_cari,idNegeri,null,null,null,null,null);
		
		}catch(Exception e){
			log.error("Error : " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void ListFailPelepasan(HttpSession session, String key_cari, String keyNo_cari, Long idNegeri) throws Exception {
		try{
			FrmSenaraiFailPelepasanGadaianData.SenaraiFailPelepasanGadaian(key_cari,keyNo_cari,idNegeri);
		}catch(Exception e){
			log.error("Error : " + e.getMessage());
			e.printStackTrace();
		}
		
	}

	//	18/06/2010
	public Vector getSenaraiFailPelepasan(HttpSession session, String key_cari, String keyNo_cari, String idNegeri) throws Exception {
		//try{
			return FrmSenaraiFailPelepasanGadaianData.getSenaraiFailPelepasanGadaian(key_cari,keyNo_cari,idNegeri);
		//}catch(Exception e){
			//log.error("Error : " + e.getMessage());
			//e.printStackTrace();
		//}
		
	}
	
	public void setFailBaru(HttpSession session, String idFail) throws Exception{
		try{
			FrmGadaianSemakan1Data.setSemak(idFail);	
		}
		catch(Exception e){
			log.error("Error : " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	private void ViewFailBaru(HttpSession session, String disability, String readability) throws Exception{
		Vector list = new Vector();
		list.clear();
		try{
			if(this.mode.equalsIgnoreCase("kemaskini")){				
				list = FrmGadaianSemakan1Data.getSemak();
			    this.context.put("Semak", list);
			    Hashtable h = (Hashtable) list.get(0);				    
			    log.info("frmGadaianPelepasanGadai :: ViewFailBaru : " + h);
			    
			    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()), "disabled"));	    
			    this.context.put("socKementerian", h.get("idKementerian").toString());
			    this.context.put("selectAgensi",getAgensiDanKementerianName(Long.parseLong((String)h.get("idKementerian")))); 			    
			    this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan",Long.parseLong(h.get("idSuburusan").toString()), "disabled"));
			    this.context.put("selectTajuk", h.get("tajuk"));
			    this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("pagemode", "view");
			    this.context.put("idNegeri", h.get("idNegeri"));
				
			}else{		
				list = FrmGadaianSemakan1Data.getSemak();
			    this.context.put("Semak", list);
			    Hashtable h = (Hashtable) list.get(0);	
			    
			    log.info("frmGadaianPelepasanGadai :: ViewFailBaru : " + h);
			    this.context.put("selectNegeri",getNegeri((String)h.get("idNegeri")));
			    this.context.put("socKementerian", h.get("idKementerian").toString());
			    this.context.put("selectAgensi",getAgensiDanKementerianName(Long.parseLong((String)h.get("idKementerian"))));
			    this.context.put("selectSuburusan",getSubUrusan((String)h.get("idSuburusan")));
			    this.context.put("selectTajuk", h.get("tajuk"));	    
			    this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("pagemode", "view");
			    this.context.put("idNegeri", h.get("idNegeri"));
		
			}

		}catch(Exception ex){
			log.error("FrmGadaianPelepasanGadai::ListFailBaru::Exception = "+ex.getMessage());
		}
	}
	
	public String getAgensiDanKementerianName(Long selectedValue){
		String namaAgensi = "";
		try{
			
			Vector v = DB.getAgensi();
			Vector ve = DB.getKementerian();
			Vector tempAgensi = new Vector<Tblrujagensi>();
			Tblrujagensi a = new Tblrujagensi();
			Tblrujkementerian k = new Tblrujkementerian();
					
			//363 Bahagian Pinjaman Perumahan - kementerian kewangan
            //455 Jabatan Perumahan Negara - kementerian perumahan dan kerajaan tempatan(kena update id_kementerian kpd 19)
            //751 suruhanjaya koperasi malaysia - kpdnhep
            //X guna -750 kewangan

            for (int i = 0; i < v.size(); i++) {
                  a = (Tblrujagensi) v.get(i);
                  if (a.getIdAgensi().equals(Long.parseLong("363")) || a.getIdAgensi().equals(Long.parseLong("455")) || a.getIdAgensi().equals(Long.parseLong("751"))) {
                        tempAgensi.add(a);
                  }

            }
			
        	Iterator<Tblrujagensi> it = tempAgensi.iterator();
			while (it.hasNext()){
				a = it.next();
				for(int x = 0; x < ve.size(); x++){
					k = (Tblrujkementerian)ve.get(x);
					if (a.getIdKementerian().equals(k.getIdKementerian())){
						if(k.getIdKementerian().equals(selectedValue)){
							namaAgensi += a.getNamaAgensi() + " - (" + k.getNamaKementerian() + " )";
							//untuk capture id agensi value
							this.context.put("agensiVal", a.getIdAgensi());
							
						}
					}
				}
			}		
		}
		catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
		
		return namaAgensi;
	}
	
	public String getNegeri(String idnegeri){
		
	    Vector v = null;
	    Tblrujnegeri n = null;
		try {
			v = new Vector();
			v = DB.getNegeri(idnegeri);
			n = (Tblrujnegeri)v.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return n.getNamaNegeri();
		
	}
	
	public String getSubUrusan(String idSub){
		
	    Tblrujsuburusan s = null;
		try {
			s = DB.getSubUrusan(idSub);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    	
		return s.getNamaSuburusan();
		
	}
	
	/*this method is for manual idfail*/
	private String SimpanFailBaru(HttpSession session) throws Exception {
		String idFail = "0";
		try{
			if(getParam("idFail") == ""){
				
				//fail baru
				Hashtable h = new Hashtable();
				h.put("idNegeri", Integer.parseInt(getParam("socNegeri")));
				h.put("idKementerian", Integer.parseInt(getParam("socAgensi")));
				h.put("idSuburusan", Integer.parseInt(getParam("socSuburusan")));		
				h.put("Tajuk", getParam("socTajuk"));
				h.put("noFailSek", getParam("txtNoFailSek"));
				
				log.info("GadaianProcessA::SimpanSemak:: h = " + h);
				idFail = FrmGadaianSemakan1Data.simpan(h);
				result = "baru";
			
			}else{	
				//kemaskini fail
				Hashtable h = new Hashtable();
				h.put("idFail", Integer.parseInt(getParam("idFail")));			
				h.put("Tajuk", getParam("txtTajuk"));
				idFail = FrmGadaianSemakan1Data.update(h);
				result = "kemaskini";
			
			}
			
		}catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
		
		return idFail;
	
	}
	
	//*** Fail Baru Controller
	private void DataFailBaru(HttpSession session, String disability, String readability) throws Exception {
		try{
			log.info("DataFailBaru : mode=" + mode);
			
			if(mode.equalsIgnoreCase("doChangeUrusan")){
				String urusan = getParam("socSuburusan");				
				this.context.put("Semak", "");
			    this.context.put("selectSuburusan",getSubUrusanForGadaian("socSuburusan", Long.parseLong(urusan), "", " onchange=\"doChangeUrusan()\" "));
			    this.context.put("selectAgensi",getAgensiDanKementerianForGadaian("socAgensi", urusan,""));
			    this.context.put("selectNegeri",UtilHTML.SelectNegeri("socNegeri"));
			    this.context.put("selectTajuk", getTajukForGadaian("socTajuk",urusan, ""));
			    this.context.put("datenow", formatter.format(now));
			    this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
				
			}else{				
				this.context.put("Semak", "");
				this.context.put("selectNegeri",UtilHTML.SelectNegeri("socNegeri"));
			    this.context.put("selectAgensi",getAgensiDanKementerianForGadaian("socAgensi"));  
			    this.context.put("selectSuburusan",getSubUrusanForGadaian("socSuburusan"," onchange=\"doChangeUrusan()\" "));
			    this.context.put("selectTajuk", getTajukForGadaian("socTajuk"));
			    this.context.put("datenow", formatter.format(now));
			    this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    
			}
		
		}catch(Exception ex){
			log.info("frmGadaianPelepasanGadai::DataFailBaru::Exception = "+ex.getMessage());
			ex.printStackTrace();
		}
		
	}
	
	//fir 05022010
	public String getSubUrusanForGadaian(String selectName, Long selectedValue, String disabled, String jsFunction){
		StringBuffer sb = new StringBuffer();
		
		try{
			
			sb.append("<select name='" + selectName + "'");
			if(disabled != null){
				sb.append(disabled);
			}
			if(jsFunction !=null){
				sb.append(jsFunction);
			}
			
			sb.append(" > ");
			sb.append("<option value=\"\">SILA PILIH URUSAN</option>\n");
			Vector v = DB.getSubUrusan();
			Tblrujsuburusan f = null;
			String selected = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(Long.parseLong("45")) || f.getIdSuburusan().equals(Long.parseLong("46")) || f.getIdSuburusan().equals(Long.parseLong("47")) ) {
					if(f.getIdSuburusan().equals(selectedValue)){
						selected = "selected";
					}
					else{
						selected="";
					}
					sb.append("<option " + selected + " value= " + f.getIdSuburusan() + ">"
							+ f.getKodSuburusan() + " - " + f.getNamaSuburusan()
							+ "</option>\n");
				} 
	
			}
			sb.append("</select>");
			
		}
		catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
		
		return sb.toString();
	}
	
	public String getAgensiDanKementerianForGadaian(String selectName, String urusan, String disabled ){
		StringBuffer sb = new StringBuffer();
		GadaianSubUrusanAgensi g = null;
		
		try{
			
			sb.append("<select name='" + selectName + "'");
			if(disabled != null){
				sb.append(disabled);
			}
			sb.append(" >\n ");
			sb.append("<option value=\"\">SILA PILIH AGENSI</option>\n");
			Vector v = DB.getAgensi();
			Vector ve = DB.getKementerian();
			
			Vector agensiUrusan = getAgensiBySubUrusan();
			Vector tempAgensi = new Vector<Tblrujagensi>();
			Tblrujagensi a = new Tblrujagensi();
			Tblrujkementerian k = new Tblrujkementerian();
			
			//363 Bahagian Pinjaman Perumahan - kementerian kewangan
            //455 Jabatan Perumahan Negara - kementerian perumahan dan kerajaan tempatan(kena update id_kementerian kpd 19)
            //751 suruhanjaya koperasi malaysia - kpdnhep
            //X guna -750 kewangan
			
        	g = new GadaianSubUrusanAgensi();
            for (int i = 0; i < v.size(); i++) {
                a = (Tblrujagensi) v.get(i);
                for (int x = 0; x < agensiUrusan.size(); x++){
                	g = (GadaianSubUrusanAgensi)agensiUrusan.get(x);
                	 if ((a.getIdAgensi().equals(Long.parseLong(g.getId_agensi()))) && (g.getId_subUrusan().equalsIgnoreCase(urusan)) ) {
                         tempAgensi.add(a);
                   }
                	
                }
          }

			Iterator<Tblrujagensi> it = tempAgensi.iterator();
			String selected = "";
			log.info("urusan : " + urusan);
			while (it.hasNext()){
				a = it.next();
				for(int x = 0; x < ve.size(); x++){
					k = (Tblrujkementerian)ve.get(x);
					if (a.getIdKementerian().equals(k.getIdKementerian())){
						log.info("agensi id kem : " + a.getIdKementerian());
						log.info("kem id kem : " + k.getIdKementerian());
							sb.append("<option value= " + k.getIdKementerian() + ">"
									+ a.getKodAgensi() + " - " + a.getNamaAgensi() + " ( " 
									+ k.getNamaKementerian() + " ) </option>\n");
					}
				}
			}
			sb.append("</select>");
			
		}
		catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
		
		return sb.toString();
	}
	
	public Vector getAgensiBySubUrusan(){
		Vector v =  null;
		Db db = null;
		GadaianSubUrusanAgensi g = null;
		String sql = "Select id_suburusanstatus,id_suburusan,id_agensi from "
				+ " TBLHTPRUJSUBURUSANAGENSI order by id_suburusanstatus";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			v = new Vector();
			while (rs.next()) {
				g = new GadaianSubUrusanAgensi();
				g.setId_agensi(rs.getString("id_agensi"));
				g.setId_subUrusan(rs.getString("id_suburusan"));
				g.setId_suburusanstatus(rs.getString("id_suburusanstatus"));
				v.add(g);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			if (db != null)
				db.close();
		}

		return v;
	}
	
	public String getTajukForGadaian(String selectName, String urusan , String jsFunction){
		StringBuffer sb = new StringBuffer();		
		try{
			//TAJUK BAGI PINJAMAN PERUMAHAN PERBENDAHARAAN (id 38)
			String tajukPP1 = "discharge of charge ".toUpperCase();
//			String tajukPP2 = "memorandum of charge ".toUpperCase(); 
			String tajukPP3 = "satisfaction of charge ".toUpperCase();
			
			//TAJUK BAGI PINJAMAN TMP & SKM (id 40 )
//			String skm1 = "gadaian/cagaran bagi pembiayaan/pinjaman tabung modal pusingan jpk ".toUpperCase();
//			String skm1 = "menandatangani borang gadaian 16a ".toUpperCase();
			String skm2 = "pelepasan gadaian 16n ".toUpperCase();
			
			//TAJUK BAGI PINJAMAN KOS RENDAH JABATAN PERUMAHAN NEGERA (KPKT) (id 39)
//			String kpkt1 = "menandatangani borang gadaian 16a ".toUpperCase();
			String kpkt2 = "pelepasan gadaian 16n ".toUpperCase();
			
			//TAJUK BAGI PELETAKHAKAN HAK GADAIAN (id 41)
//			String gadaian1 = "menandatangani borang gadaian 16a ".toUpperCase();
			String gadaian2 = "pelepasan gadaian 16n ".toUpperCase();
	
			sb.append("<select name='" + selectName + "'");
			if(jsFunction != null){
				sb.append(jsFunction);
			}
			sb.append(" >\n ");
			sb.append("<option value=\"\">SILA PILIH TAJUK </option>\n");
			
			if(urusan.equalsIgnoreCase("45")){
				sb.append("<option value=\""+ tajukPP1 + "\" >" + tajukPP1 + "  </option>\n");
//				sb.append("<option value=\""+ tajukPP2 + "\" >" + tajukPP2 + "  </option>\n");
				sb.append("<option value=\""+ tajukPP3 + "\" >" + tajukPP3 + "  </option>\n");
				
			}
			if(urusan.equalsIgnoreCase("46")){
//				sb.append("<option value=\""+ kpkt1 + "\" >" + kpkt1 + "  </option>\n");
				sb.append("<option value=\""+ kpkt2 + "\" >" + kpkt2 + "  </option>\n");
				
			}
			if (urusan.equalsIgnoreCase("47")){
//				sb.append("<option value=\""+ skm1 + "\" >" + skm1 + "  </option>\n");
				sb.append("<option value=\""+ skm2 + "\" >" + skm2 + "  </option>\n");
				
			}
//			if (urusan.equalsIgnoreCase("41")){
////				sb.append("<option value=\""+ gadaian1 + "\" >" + gadaian1 + "  </option>\n");
//				sb.append("<option value=\""+ gadaian2 + "\" >" + gadaian2 + "  </option>\n");
//				
//			}
					
			sb.append("</select>");		
			
		}catch(Exception e){
			e.printStackTrace();
		}		
		return sb.toString();
		
	}
	
	//fir 06022010
	public String getTajukForGadaian(String selectName){
		StringBuffer sb = new StringBuffer();		
		try{
			sb.append("<select name='" + selectName + "'> ");
			sb.append("<option value=\"\">SILA PILIH TAJUK </option>\n");
			sb.append("</select>");		
		}catch(Exception e){
			e.printStackTrace();
		}		
		return sb.toString();
		
	}
	
	//fir 06022010
	public String getAgensiDanKementerianForGadaian(String selectName ){
		StringBuffer sb = new StringBuffer();		
		try{			
			sb.append("<select name='" + selectName + "'> ");
			sb.append("<option value=\"\">SILA PILIH AGENSI </option>\n");
			sb.append("</select>");
			
		}catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
		return sb.toString();
		
	}
	
	//fir
	public String getSubUrusanForGadaian(String selectName, String jsFunction){
		StringBuffer sb = new StringBuffer();		
		try{
			
			sb.append("<select name='" + selectName + "'");
			if(jsFunction != null){
				sb.append(jsFunction);
			}
			sb.append(" > ");
			sb.append("<option value=\"\">SILA PILIH URUSAN</option>\n");
			Vector v = DB.getSubUrusan();
			Tblrujsuburusan f = null;
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(Long.parseLong("38")) || f.getIdSuburusan().equals(Long.parseLong("39")) || f.getIdSuburusan().equals(Long.parseLong("40")) || f.getIdSuburusan().equals(Long.parseLong("41"))) {
					sb.append("<option value= " + f.getIdSuburusan() + ">"
							+ f.getKodSuburusan() + " - " + f.getNamaSuburusan()
							+ "</option>\n");
				} 
	
			}
			sb.append("</select>");
			
		}catch(Exception e){
			log.error("Error : " + e.getMessage());
		}		
		return sb.toString();
		
	}
	
	private void ListSemak(HttpSession session, String id) throws Exception {
		try{
	
			String idPermohonan = "";
			if (getParam("idPermohonan") != ""){
				idPermohonan = (String)getParam("idPermohonan");
			
			}else if (id != ""){
				idPermohonan = id;
			}
			log.info("GadaianProcess::ListSemak::idPermohonan = 0");
			FrmGadaianSemakanData.setSemak(idPermohonan);		
		
		}catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
	}
	
	private void DataSemak(HttpSession session, String disability, String readability) throws Exception{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmGadaianSemakanData.getSemak();
		    this.context.put("Semak", list);
		    Hashtable h = (Hashtable) list.get(0); 
		    log.info("DataSemak Hashtable : " + h);
		    this.context.put("selectNegeri",getNegeri((String)h.get("idNegeri")));		    
		    this.context.put("idNegeri",getNegeri((String)h.get("idNegeri")));		    
		    this.context.put("selectSuburusan",getSubUrusan((String)h.get("idSuburusan")));
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("pagemode", "simpan");
		    this.context.put("noRujukan", getParam("txtNoFailLain"));
  
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void ListSemakBaru(HttpSession session) throws Exception{
		try{
			FrmGadaianSemakanData.setSemakBaru(getParam("idFail"));
		
		}catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
	}
	
	private void DataSemakBaru(HttpSession session, String disability, String readability) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmGadaianSemakanData.getSemakBaru();
		    this.context.put("SemakBaru", list);
		    Hashtable h = (Hashtable) list.get(0);	
		    this.context.put("selectNegeri",getNegeri((String)h.get("idNegeri")));
		    this.context.put("selectAgensi",getAgensiDanKementerianName(Long.parseLong((String)h.get("idKementerian"))));
		    this.context.put("selectSuburusan",getSubUrusan((String)h.get("idSuburusan")));
		    this.context.put("datenow", formatter.format(now));
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("pagemode", "baru");
		}
		catch(Exception ex){
			log.error("GadaianProcess::DataSemak::Exception = "+ex.getMessage());
		}
	}
	
	private String SimpanSemak(HttpSession session) throws Exception{
		String idPermohonan = "";
		try{
			if(getParam("idPermohonan") == ""){
				//fail baru
				
				Hashtable h = new Hashtable();
				h.put("IdFail", Integer.parseInt(getParam("idFail")));
				h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
				h.put("Tajuk", getParam("txtTajuk"));
				h.put("socAgensi", getParam("socAgensi"));
				h.put("idSuburusan", Integer.parseInt(getParam("idSuburusan")));
				h.put("NoFailLain", getParam("txtNoFailLain"));
				h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
				log.info("frmGadaianPelepasanGadai::SimpanSemak::FailBaru:: h = "+h);
				idPermohonan = FrmGadaianSemakanData.simpan(h);
				result = "baru";
			
			}else{	
				//kemaskini fail
				Hashtable h = new Hashtable();
				h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
				h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
				h.put("NoFailLain", getParam("txtNoFailLain"));
				h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
				log.info("GadaianProcess::SimpanSemak::KemaskiniFail:: h = "+h);
				idPermohonan = FrmGadaianSemakanData.update(h);
				result = "kemaskini";
				
			}
		
		}catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
		
		return idPermohonan;
	
	}
	
	public void SenaraiFailUntukMelepasGadai(String urusan, String negeri){
		try {
			FrmGadaianPelepasanGadaianData.SenaraiFailGadaian(urusan, negeri);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void DaftarFailPelepasan(String idFail, String failGadaian){
		try{
			FrmGadaianPelepasanGadaianData.DaftarFailMelepasGadaian(idFail, failGadaian);
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void ListPermohonanPelepasanGadaian(String idFail, String noFail){
		try{
			FrmSenaraiFailPelepasanGadaianData.ListPermohonanPelepasanGadaian(idFail, noFail);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void MaklumatPeminjam(String idFail, String idPermohonan){
		try{
			FrmGadaianPelepasanMaklumatPeminjam.InfoPeminjam(idFail, idPermohonan);
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public void SimpanPelepasanGadaian(String idFail, String idPermohonan, HttpSession session){
		try{
			String tarikhLepasGadai = getParam("txTarikhLepasGadai");
			FrmGadaianPelepasanGadaianData.SimpanPelepasanGadaian(idFail, idPermohonan, tarikhLepasGadai );
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public void ViewPermohonanLepasGadai(String idFail, String idPermohonan){
		
	}
	
	public void doListing(HttpSession session,String action,String submit,String mode,Vector v) throws Exception {		
		//this.context.put("senaraiList1", v); 
		this.context.put("submit", submit); 
		setupPage(session,action,v);
		
	}
	
	private IGadaianPelepasan getIGadaianPelepasan(){
		if (iGadaianPelepasan==null){
			iGadaianPelepasan = new GadaianPelepasanBean();
		}
		return iGadaianPelepasan;
	}
	
	private IPembelian getIPembelian(){
		if (iPembelian==null){
			iPembelian=new PembelianBean();
		}
		return iPembelian;
	} 
	
	private IPemilik getIPemilik(){
		if(iPemilik==null){
			iPemilik = new PemilikBean();
		}
		return iPemilik;
		
	}

}//close class
