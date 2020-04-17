package ekptg.view.htp;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.File;
import ekptg.helpers.HTML;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.FrmPajakanKecilSenaraiPermohonanData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmSenaraiFailPajakanKecilData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.cukai.CukaiBean;
import ekptg.model.htp.cukai.ICukai;

public class FrmCukaiPendaftaran extends AjaxBasedModule{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String PATH="app/htp/cukai/";
	private static Logger mylog = Logger.getLogger(ekptg.view.htp.FrmCukaiPendaftaran.class);
	private static String idUrusan = "11";
	private static String idSubUrusan = "43";
	String idMasuk = null;
	private String idNegeri = "0";
	private ICukai iCukai = null;
	private String strNoFail = "";

	public String doTemplate2()throws Exception {
		
		context.put("UTIL", new ekptg.helpers.Utils());
		HttpSession session = this.request.getSession();
		idMasuk = (String)session.getAttribute("_ekptg_user_id");
		//System.out.println("ID MASUK "+idMasuk);
		if(idMasuk == null)
			throw new Exception("[001]LOGIN ID TIDAK SAH");
	    String template_name = "";
	    String socAgensi = "";
	    String socKementerian = "";
	    String socNegeri = "";
	    String socUrusan = "";
	    String strTitle = "Cukai";
	   	    
		Vector semakanSenarai = new Vector();
	    Vector senaraiFail = null;

	    String submit = getParam("command");
	    String idFail = getParam("fail");
	    String pageMode = getParam("pagemode");
	    String langkah = getParam("langkah");
	    idNegeri= getParam("socNegeri");

	    this.context.put("util", new lebah.util.Util());
	    //socNegeri = HTML.SelectNegeri("socNegeri");
	    System.out.println("idNegeri : "+idNegeri);
	    socNegeri = HTML.SelectNegeri("socNegeri",idNegeri.equals("")?0L:Long.parseLong(idNegeri),"","onchange=\"doChangeNegeri()\"");

	   	mylog.info(": equals(submit)="+submit +":pageMode="+pageMode);
	    if(submit.equals("cukaifailbaru")){
		   	template_name = PATH+"frmCukaiPendaftaran.jsp";
		   	mylog.info("FrmCukaiPendaftaran: equals(submit)::cukaifailbaru");
		   	String strOperation = "";
		    this.context.put("socSeksyen","3");
		    
		    if(pageMode.equals("0")){
		    	strOperation = "success";
			    String idnegeri = getParam("socNegeri");
			    String idagensi = getParam("idagensi");
				long longIdFail = ekptg.helpers.DB.getNextID("TBLPFDFAIL_SEQ");
				simpanFail(session,longIdFail);
				//int idPermohonan=46;
				String idPermohonan = simpanPermohonan(session,String.valueOf(longIdFail),0);
			    this.context.put("idPermohonan",idPermohonan);
		    	System.out.println("FrmCukaiPendaftaran: idPermohonan::"+idPermohonan);
			    simpanStatus(session,Long.parseLong(idPermohonan),longIdFail);
    			AuditTrail.logActivity("1", getParam("idseksyen"), this, session, "INS", "FAIL PERMOHONAN ["+strNoFail+"] DITAMBAH ");
		    
	        	//after saved
				Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(idPermohonan);
			    this.context.put("permohonanInfo", permohonan);
			    
			    socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),"disabled class=disabled");
		    	socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong(idUrusan),"disabled");
			    this.context.put("socUrusan",socUrusan);
			    			    
			    pageMode = "2";
		    }else if(pageMode.equals("3")){
			    String id = getParam("id_kemaskini");

				Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
			    this.context.put("permohonanInfo", permohonan);
			    socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),"disabled class=disabled");
		    	socKementerian = HTML.SelectKementerian("sockementerian",Long.parseLong(permohonan.get("idkementerian").toString()),"disabled class=disabled");
		    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),null);
		    	socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("309"),"disabled");
		    	this.context.put("socUrusan",socUrusan);
			    	
		    	pageMode = "4";
		    }else{
		    	String id = getParam("id_kemaskini");
		    	System.out.println("FrmPajakanKecilA: equals(submit)::pkfailbaru:::else|id"+id);
			    	
				Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
			    this.context.put("permohonanInfo", permohonan);
			    Hashtable h = new Hashtable();
				h.put("id_Fail", Long.parseLong((String)permohonan.get("idpermohonan")));
			    h.put("id_kemaskini", 1);
			    FrmSenaraiFailPajakanKecilData.kemaskiniFail(h);
			    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());
			    socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),"disabled class=disabled");
		    	socKementerian = HTML.SelectKementerian("sockementerian",Long.parseLong(permohonan.get("idkementerian").toString()),"disabled class=disabled");
		    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),"disabled class=disabled");
		    	socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("309"),"disabled");
		    	this.context.put("socUrusan",socUrusan);
			    
			    // Senarai semakan
			    semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh","aktif");
			    this.context.put("senaraiSemakan",semakanSenarai );
		    	pageMode = "2";
		    }
		    this.context.put("operation",strOperation);  
		    this.context.put("socNegeri",socNegeri);
		    this.context.put("socKementerian",socKementerian);
		    this.context.put("socAgensi",socAgensi);
		    this.context.put("pageMode",pageMode);
    	
	    }
	    else{ // !=submit
	    	strTitle = "FAIL CUKAI";
	        System.out.println("FrmCukaiPendaftaran: !=submit::else:::user_id="+session.getAttribute("_ekptg_user_id"));
    		template_name = PATH+"frmCukaiPendaftaran.jsp";
	        
	    	String strdate = "";
	    	strdate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
	    	this.context.put("socSeksyen","3");
	    	this.context.put("nofail","");
	    	//agensi 436 JKPTG
	    	this.context.put("idkementerian","26");
	    	this.context.put("idagensi","436");
	    	this.context.put("pageMode", "0");  
	    	this.context.put("titleProses", strTitle);  
	    	this.context.put("senaraiList", senaraiFail);  
		   	this.context.put("sekarang",strdate);
		   	
		   	//fix bug. syaz. 17112014
		   	this.context.put("tambahdisable", false);
		   	
		   	if(submit.indexOf("doChange") != -1) {
		   		Vector vecNegeri = (Vector)DB.getNegeri(idNegeri); 
		   		Tblrujnegeri n = (Tblrujnegeri)vecNegeri.elementAt(0);
			  	this.context.put("tajukTemp",strTitle+" "+n.getNamaNegeri());
			  	mylog.info("doChange:getICukai().isFailCukaiSelesai(idNegeri)="+getICukai().isFailCukaiSelesai(idNegeri));
			  	this.context.put("tambahdisable",getICukai().isFailCukaiSelesai(idNegeri));
		   	}
	    }
	  	socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong(idUrusan),"disabled");
	  	this.context.put("socNegeri", HTML.SelectNegeri("socNegeri",null,"","onchange=\"doChangeNegeri()\""));
    	this.context.put("socUrusan",socUrusan);
    	this.context.put("idurusan",idUrusan);
	   	this.context.put("idsuburusan",idSubUrusan);
	   	//System.out.println(template_name);
	    return template_name;
	    
	  }

	private void simpanFail(HttpSession session, Long idFail) throws Exception {
		  
		  Hashtable h = null;
		  h = new Hashtable();
		  String kodNegeriMampu = "";
		  String kodKementerianMampu = "";
		  int idTarafkeselamatan = 1; /** TERBUKA*/
		  int idSeksyen = 3;
		  int idUrusan = 0;
		  int idSuburusan = 0;
		  String txdBukafail = "";
		  String namaFail = "";
		  String noFail = "JKPTG/101/";
		  String noFailroot = "TIADA";
		  int idLokasi= 1;  
		  int idNegeri = 0;
		  int idKementerian = 0;
		  int idFaharasat = 1;
		  String flagFail = "1";
		  int idStatus = 7;/**AKTIF*/
		  String catatan = "TIADA";
		  Vector vecFail = new Vector();
		  
		  idSeksyen = Integer.parseInt(getParam("idseksyen"));
		  idUrusan = Integer.parseInt(getParam("idurusan"));
		  idSuburusan = Integer.parseInt(getParam("idsuburusan"));
		  txdBukafail = getParam("txdbukafail"); 
		  namaFail = getParam("txttajuk");	  
		  idNegeri = Integer.parseInt(getParam("socNegeri"));

		  idKementerian = Integer.parseInt(getParam("idkementerian"));
		  /** configurasi no fail*/
		  int fileSeq = 0;
		  kodNegeriMampu = FrmSenaraiFailPajakanKecilData.getNegeriByMampu(idNegeri);
		  kodKementerianMampu = FrmSenaraiFailPajakanKecilData.getKementerianByMampu(idKementerian);
		  Vector vecUrusan = null;
		  vecUrusan = DB.getUrusan(""+idUrusan);
		  Tblrujsuburusan u = null;
		  u = (Tblrujsuburusan)vecUrusan.get(0);
		  
		  fileSeq = File.getSeqNo(idSeksyen, idUrusan, idKementerian, idNegeri);
		  System.out.println("FrmCukaiPendaftaran:simpanFail::fileSeq:::"+fileSeq);
		  noFail += u.getKodSuburusan()+"/"+kodKementerianMampu+"/"+kodNegeriMampu+"-"+fileSeq;
		  /** end */
		  strNoFail = noFail;		 	
		  h.put("id_Fail", idFail);
		  //KOD_JABATAN /** kemasukan manual - JKPTG*/
		  h.put("id_Tarafkeselamatan", idTarafkeselamatan);
		  h.put("id_Seksyen",idSeksyen);
		  h.put("id_Urusan", idUrusan);
		  h.put("id_Suburusan",idSuburusan);
		  h.put("tarikh_Bukafail",txdBukafail);		  
		  h.put("tajuk_Fail", namaFail);
		  h.put("no_Fail", noFail);
		  h.put("no_Failroot", noFailroot);
		  h.put("id_Lokasi", idLokasi); 
		  h.put("id_Negeri", idNegeri);
		  h.put("id_Kementerian",idKementerian);
		  h.put("id_Faharasat",idFaharasat);
		  h.put("flag_Fail",flagFail);
		  h.put("id_Status",idStatus);
		  h.put("catatan",catatan);
		  h.put("id_Masuk",Integer.parseInt(idMasuk));
		  h.put("tarikh_Masuk",txdBukafail);
		  FrmUtilData.simpanFail(h);
		  /**/
		  //System.out.println(this.className+":simpanFail::noFail:::"+noFail);

	  }
	  
	  private String simpanPermohonan(HttpSession session,String idFail, int flag)throws Exception {
		  Hashtable data = null;
		  data = new Hashtable();
		  String txdBukafail = "";
		  int idNegeri = 0;
		  int idAgensi = 0;
		  int idFaharasat = 1;
		  int idStatus = 7;/**AKTIF*/
		  String tajuk = "TIADA";
		  String strTiada = "TIADA";
		  txdBukafail = getParam("txdbukafail"); 
		  tajuk = getParam("txttajuk");
		
		  idAgensi = Integer.parseInt(getParam("idagensi"));
		  
		  if(flag==0){
			  //data.put("IdFail", fail);
			  //else
			  data.put("id_Fail",idFail);
			  //id_PejabatJKPTG /** auto simpan*/
			  data.put("no_Permohonan",strTiada);
			  data.put("no_Perserahan",strTiada); 
			  data.put("tarikh_SuratKJP", txdBukafail);
			  data.put("tarikh_Terima", txdBukafail);
			  data.put("tajuk",tajuk);
			  data.put("tarikh_Masuk", txdBukafail);
	    	  data.put("id_Agensi", idAgensi);
	    	  data.put("id_Jenistanah", 1);
	    	  data.put("id_Pegawai", idMasuk);
	    	  data.put("no_Failkjp", strTiada);
	    	  data.put("no_Faillain", strTiada);
	    	  data.put("tarikh_Agihan", txdBukafail);

	    	  data.put("id_Masuk", idMasuk);
		  }		  
		  return FrmUtilData.simpanPermohonanHTP(data);
		  
	  }
	  
	  public void simpanStatus(HttpSession session,Long idPermohonan,Long idFail) throws Exception {
		  Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
		  Long setIdSuburusanstatus = 0L;
		  setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("1",idSubUrusan,"=");

		  s.setIdPermohonan(idPermohonan);
		  s.setIdFail(idFail);	  
		  s.setIdSuburusanstatus(setIdSuburusanstatus);
		  s.setAktif("1");
		  s.setIdMasuk(Long.parseLong(idMasuk));
		  s.setUrl("TIADA");
		  s.setTarikhMasuk(new Date());
		  FrmUtilData.simpanStatusPermohonan(s);
		  
	  }
	  
	    private String getJarakBulan(Date dari,Date hingga) {
	        Long diff, DayDur, MonthDur;
	            if(hingga != null && dari != null) {
	                diff = hingga.getTime() - dari.getTime();
	                DayDur = diff / (1000*60*60*24);
	                //MonthDur = (DayDur % 365) / 29; // baki daripada tahun dalam bentuk bulan
	                 MonthDur = DayDur / 29;
	                System.out.println("Duration in Month : "+MonthDur);
	            }else {
	                System.out.println("date hingga or dari is null on JarakBulan");
	                MonthDur = null;
	            }
	        return ""+MonthDur;
	    }	

		private ICukai getICukai(){
			if(iCukai==null){
				iCukai = new CukaiBean();
			}
			return iCukai;
			
		}	    
	    
}
