/* author: saiful bakhtiar
 * description : page controller for Pajakan Kecil
 * 
 * 
 */
package ekptg.view.htp;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.action.AjaxModule;
import ekptg.helpers.File;
import ekptg.helpers.HTML;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.FrmPajakanKecilHakmilikData;
import ekptg.model.htp.FrmPajakanKecilPendaftaranData;
import ekptg.model.htp.FrmPajakanKecilSenaraiPermohonanData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmSenaraiFailPajakanKecilData;
import ekptg.model.htp.FrmUtilData;

public class FrmPajakanKecilModule extends AjaxModule{
	private final String PATH="app/htp/pajakanKecil/";
	private String jsp = PATH+"index.jsp";
	private String userID = null;
	private static final long URUSAN_CODE = 309;
	private static final int URUSAN_ID = 309;
	private static final long SUBURUSAN_CODE = 44;
	private static final int SUBURUSAN_ID = 44;
	private static final int SECTION = 3;
	private static final String NOFILE_PREFIX = "JKPTG/101/847/";
	@Override
	public String doAction() throws Exception {
		 HttpSession session = request.getSession();
		 userID = (String)session.getAttribute("_ekptg_user_id");
		 userID = "3";
		 String command = getParam("command");
		 System.out.println("COMMAND :"+command);
		 if(command.equals("find")){
			 findFile();
			 jsp = PATH+"index.jsp";
		 }
		 else if(command.equals("openNewFile")){
			 getUtilData();
			 jsp = PATH+"newFile.jsp";
		 }
		 else if (command.equals("doChangeKementerian")){
			 changeKementerian();
			 jsp = PATH+"newFile.jsp";
		 }
		 else if(command.equals("createNewFile")){
			 saveFile();
			 jsp = PATH+"fileInfo.jsp";
		 }
		 else if(command.equals("detailList")){
			 String idFail = getParam("fail");
			 Vector senaraiPermohonan = FrmPajakanKecilSenaraiPermohonanData.getList(idFail);
			 context.put("senaraiList", senaraiPermohonan);	    	
		     context.put("idFail", idFail);	    	
		     jsp = PATH+"detailList.jsp";	
		 }
		 else if (command.equals("addApplication")){
			 String idFail = getParam("idPermohonan");
			 findPermohonan(idFail);
			 jsp = PATH+"addApplication.jsp";
		 }
		 else if (command.equals("viewChecklist")){
			 String idPermohonan = getParam("idPermohonan");
		     findPermohonan(idPermohonan);
		     Vector semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemak");
		     context.put("senaraiSemakan",semakanSenarai);
		     context.put("semakclass", new ekptg.model.htp.FrmSemakan());
		     context.put("idpermohonan", idPermohonan);
			 jsp = PATH+"checkList.jsp";
		 }
		 else if(command.equals("ownership")){
			 String idFail = getParam("idpermohonan");
			 findPermohonan(idFail);
			 
			 Vector senaraiHakmilik = FrmPajakanKecilHakmilikData.getSenaraiHakmilik(Long.parseLong(idFail));
			 context.put("senaraihakmilik", senaraiHakmilik);
			 Vector senaraiPemilik = FrmPajakanKecilHakmilikData.getSenaraiPemilik(Long.parseLong(idFail));
			 context.put("senaraipemilik", senaraiPemilik);
			 context.put("idpermohonan", idFail);
		     jsp = PATH+"hakmilik.jsp";
		 }
		 else if(command.equals("addHakmilik")){
			 String idPermohonan = getParam("idpermohonan");
			 Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(idPermohonan);
			 String socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),"disabled class=disabled");
			 context.put("socDaerah1",HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah1", " onChange=\"doChangeDaerahHakmilik("+id+")\" "));
			 context.put("socMukim", HTML.SelectMukimByNegeri((String)permohonan.get("idnegeri"), "socMukim"));
		     context.put("socHakmilik",HTML.SelectJenisHakmilik("socHakmilik"));
	         context.put("noLot", HTML.SelectLot("noLot"));
		     context.put("idnegeri", permohonan.get("idnegeri"));
		     context.put("idpermohonan", idPermohonan);
			 context.put("socDaerah",HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah"));
			 jsp = PATH+"addHakmilik.jsp";
		 }
		 else if(command.equals("saveHakmilik")){
			 
			 saveHakmilik();
		 }
		 else{
			 removeContext();
			 jsp = PATH+"index.jsp";
		 }
		 
		 String strdate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
	     this.context.put("sekarang",strdate);
		return jsp;
	}
	private void saveHakmilik()throws Exception{
		String idFail = getParam("idpermohonan");
		findPermohonan(idFail);
		Vector senaraiHakmilik = FrmPajakanKecilHakmilikData.getSenaraiHakmilik(Long.parseLong(idFail));
		context.put("senaraihakmilik", senaraiHakmilik);
		Vector senaraiPemilik = FrmPajakanKecilHakmilikData.getSenaraiPemilik(Long.parseLong(idFail));
		context.put("senaraipemilik", senaraiPemilik);
		context.put("idpermohonan", idFail);
		
		 Hashtable<String,Object> h= new Hashtable();
		 h.put("idHakmilikurusan", "");
         h.put("noRujukan", getParam("txtnorujukan"));
         h.put("nama", getParam("txtnama"));
         h.put("alamat1", getParam("txtalamat1"));
         h.put("alamat2", getParam("txtalamat2"));
         h.put("alamat3", getParam("txtalamat3"));
         h.put("poskod", getParam("txtposkod"));
         h.put("idDaerah", Integer.parseInt(getParam("socDaerah")));
         h.put("idNegeri", Integer.parseInt(getParam("socNegeri")));
         h.put("noTelefon", "TIADA");
         h.put("noFax", "TIADA");
         h.put("noPerserahan","TIADA");
	    jsp = PATH+"hakmilik.jsp";
	}
	private void saveFile()throws Exception{
		long longIdFail = ekptg.helpers.DB.getNextID("TBLPFDFAIL_SEQ");
		String idnegeri = getParam("socNegeri");
		String idagensi = getParam("socAgensi");
		String idkementerian = getParam("sockementerian");
		String txdBukafail = getParam("txdTarikhBukaFail");
		String tajukFile = getParam("txttajuk");
		String noFile = getParam("txtNoFailSek");
		String txtFileLain = getParam("txtNoFailLain");
		String bukaFileDate =  getParam("txdTarikhBukaFail");
		String kodNegeriMampu = FrmSenaraiFailPajakanKecilData.getNegeriByMampu(Integer.parseInt(idnegeri));
		String kodKementerianMampu = FrmSenaraiFailPajakanKecilData.getKementerianByMampu(Integer.parseInt(idnegeri));
		int idTarafkeselamatan = 1; /** TERBUKA*/
		int idSeksyen = 3;
		String noFailroot = "TIADA";
		int idFaharasat = 1;
		String flagFail = "1";
		int idStatus = 7;/**AKTIF*/
		String catatan = "TIADA";
		int idmasuk = Integer.parseInt(userID);
		int idLokasi= 1;  
		if(noFile.equals("")){
			int fileSeq = File.getSeqNo(SECTION, URUSAN_ID, Integer.parseInt(idkementerian), Integer.parseInt(idnegeri));
			System.out.println("no file :"+fileSeq);
			noFile = NOFILE_PREFIX +  kodKementerianMampu+"/"+kodNegeriMampu+"-"+fileSeq;	
		}
		
		Hashtable<String,Object> h = new Hashtable<String,Object>();
		h.put("id_Fail", longIdFail);
		h.put("id_Tarafkeselamatan", idTarafkeselamatan);
		h.put("id_Seksyen", idSeksyen);
		h.put("id_Urusan", URUSAN_ID);
		h.put("id_Suburusan", SUBURUSAN_ID);
		h.put("tarikh_Bukafail",txdBukafail);		  
		h.put("tajuk_Fail",tajukFile);
		h.put("no_Fail", noFile);
		h.put("no_Failroot", noFailroot);
		h.put("id_Negeri",Integer.parseInt(idnegeri));
		h.put("id_Kementerian",Integer.parseInt(idkementerian));
		h.put("id_Faharasat",idFaharasat);
		h.put("flag_Fail",flagFail);
		h.put("id_Status",idStatus);
		h.put("catatan",catatan);
		h.put("id_Masuk", idmasuk);
		h.put("id_Lokasi", idLokasi); 
		FrmUtilData.simpanFail(h);
		
		String noPermohonan = "TIADA";
      	String noPerserahan = "TIADA";
		Hashtable<String,Object> data = new Hashtable<String,Object>();
		data.put("id_Fail", String.valueOf(longIdFail));
		data.put("no_Permohonan",noPermohonan);
	  	data.put("no_Perserahan",noPerserahan); 
      	data.put("tarikh_SuratKJP", getParam("txdTarikhSuratKJP"));
      	data.put("tarikh_Terima", getParam("txdTarikhSuratKJP"));
      	data.put("tajuk", getParam("txttajuk"));
      	data.put("id_Agensi", Integer.parseInt(idagensi));
      	data.put("idSuburusan", SUBURUSAN_ID);
        data.put("id_Jenistanah", 1);
        data.put("no_Failkjp", "TIADA");
      	data.put("no_Faillain",txtFileLain);
      	data.put("tarikh_Agihan", bukaFileDate);
	    data.put("TarikhBukaFail",bukaFileDate);
	    data.put("id_Masuk", Integer.parseInt(userID));
	    String idPermohonan = FrmUtilData.simpanPermohonanHTP(data);
	    
	    Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
	    
	    long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("1",String.valueOf(SUBURUSAN_CODE),"=");
		s.setIdPermohonan(Long.parseLong(idPermohonan));
		s.setIdFail(longIdFail);
		
		s.setIdSuburusanstatus(setIdSuburusanstatus);
		s.setAktif("1");
		s.setIdMasuk(Long.parseLong(userID));
		FrmUtilData.simpanStatusPermohonan(s);
		findPermohonan(idPermohonan);
	}
	private void findPermohonan(String permohonanID) throws Exception{
		Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(permohonanID);
		String socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),"disabled class=disabled");
    	String socKementerian = HTML.SelectKementerian("sockementerian",Long.parseLong(permohonan.get("idkementerian").toString()),"disabled class=disabled");
    	String socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),"disabled class=disabled");
    	String socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("309"),"disabled");
	    context.put("permohonanInfo", permohonan);
	    context.put("socKementerian",socKementerian);
		context.put("socAgensi",socAgensi);
		context.put("socNegeri",socNegeri);
		context.put("socUrusan",socUrusan);
		label(permohonan);
	}
	private void changeKementerian()throws Exception{
		String id_kementerian = getParam("sockementerian");
		String idnegeri = getParam("socNegeri");
		if(idnegeri.equals(""))
			idnegeri = "1";
		String socKementerian = HTML.SelectKementerian("sockementerian", (id_kementerian == "") ? null : Long.parseLong(id_kementerian), null, "onChange=\"doChangeKementerian()\" ");
    	String socAgensi = HTML.SelectAgensiByKementerian("socAgensi",id_kementerian,Long.parseLong("1"),"");
    	String socNegeri = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri",Long.parseLong(idnegeri),"enabled");
    	context.put("socKementerian",socKementerian);
		context.put("socAgensi",socAgensi);
		context.put("socNegeri",socNegeri);
	}
	private void findFile()throws Exception{
		String nofail = getParam("nofail");
		Vector<Hashtable<String, String>> fileList = FrmSenaraiFailPajakanKecilData.getList(nofail);
		context.put("senaraiList", fileList); 
	}
	private void getUtilData()throws Exception{
		String id_kementerian = getParam("sockementerian");
	    String socNegeri = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri");
	    String socKementerian = HTML.SelectKementerian("sockementerian", (id_kementerian == "") ? null : Long.parseLong(id_kementerian), null, "onChange=\"doChangeKementerian()\" ");
		String socAgensi = HTML.SelectAgensiByKementerian("socAgensi","",Long.parseLong("1"),"");
		String socUrusan = HTML.SelectUrusan("socUrusan",URUSAN_CODE,"disabled class=disabled");
    	context.put("socUrusan",socUrusan);
		context.put("socNegeri",socNegeri);
		context.put("socKementerian",socKementerian);
		context.put("socAgensi",socAgensi);
	}
	 private void label(Hashtable permohonan){
			String labelNegeri = (String)permohonan.get("negeri");
			String labelKementerian = (String)permohonan.get("kementerian");
			String labelAgensi = (String)permohonan.get("agensi");
			String labelTajuk = (String)permohonan.get("tujuan");
			String labelNoFail = (String)permohonan.get("fail");
			String labelNoFailLain = (String)permohonan.get("rujukankjp");
			String labelTarikhSuratKJP = (String)permohonan.get("rtterima");
			String labelTarikhBukaFail = (String)permohonan.get("tdaftar");
		
			
			this.context.put("labelNegeri", labelNegeri);
			this.context.put("labelKementerian", labelKementerian);
			this.context.put("labelAgensi", labelAgensi);
			this.context.put("labelTajuk", labelTajuk);
			this.context.put("labelNoFail", labelNoFail);
			this.context.put("labelNoFailLain", labelNoFailLain);
			this.context.put("labelTarikhSuratKJP", labelTarikhSuratKJP);
			this.context.put("labelTarikhBukaFail", labelTarikhBukaFail);

	    }
	private void removeContext(){
		context.remove("senaraiList");
		context.remove("permohonanInfo");
	}
}
