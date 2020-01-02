/**
 *
 */
package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.model.ppk.FrmPrmhnnSek8KeputusanPermohonanData;
import ekptg.model.ppk.FrmPrmhnnSek8KeputusanPermohonanInternalData;
import ekptg.model.ppk.FrmSenaraiFailKeputusanPermohonanData;
import ekptg.model.ppk.FrmSenaraiFailPusakaKecilData;

/**
 * @author kak ayu
 *
 */
public class FrmSenaraiFailKeputusanPermohonan  extends VTemplate{
	
	private static final long serialVersionUID = 1L;

	FrmPrmhnnSek8KeputusanPermohonanData logicKeputusanPrmhnn = new FrmPrmhnnSek8KeputusanPermohonanData();
	FrmSenaraiFailKeputusanPermohonanData logicKeputusan = new FrmSenaraiFailKeputusanPermohonanData();


	@SuppressWarnings("unchecked")
	public Template doTemplate() throws Exception
	{
		System.out.println("-------Read Here----");
		HttpSession session = this.request.getSession();
		String vm = "app/ppk/frmSenaraiFailKeputusanPermohonan.jsp";
		String submit = getParam("command");
		String disability1 = "";
		String disability2 = "";
		String readability1 = "";
		String readability2 = "";

		Vector listKeputusan = new Vector();

		listKeputusan.clear();

		//System.out.println("submit="+submit);

		if("paparKeputusan".equals(submit)){
			view_mode(session);
			int eventStatus = 1;
			this.context.put("EventStatus", eventStatus);
			String id = getParam("idPermohonan");
			FrmSenaraiFailKeputusanPermohonanData.setData(id);
			Vector list = FrmSenaraiFailKeputusanPermohonanData.getData();
  	    	this.context.put("ViewPemohon", list);

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan.jsp";
		}
		else if("getCari".equals(submit)){
			readability1 = " ";
			readability2 = "readonly";
			disability1 = "disabled";
			disability2 = "";
			String Carix = "1";
			this.context.put("carix", Carix);
			String noFail = getParam("txtNoFail");
			String namaPemohon = getParam("txtPemohon");
			String namaSimati = getParam("txtSimati");
			String icSimati = getParam("txtIcSimati");
			String JenisIc = getParam("jenisIc");
			String statusFail = getParam("statusFail");
			FrmSenaraiFailKeputusanPermohonanData.setCarianFail(noFail,namaPemohon,namaSimati,icSimati,JenisIc,statusFail);
			Vector list1 = new Vector();
			list1 = FrmSenaraiFailKeputusanPermohonanData.getList();
			int countList1 = list1.size();
			System.out.println("-------Read Here----");
			this.context.put("Senaraifail1",list1);
			this.context.put("CountList1",countList1);

 			vm = "app/ppk/frmSenaraiFailKeputusanPermohonan.jsp";
		}
		else if("getKemaskini_keputusan".equals(submit)){
			view_mode(session);
			listKeputusan = logicKeputusanPrmhnn.getDataKeputusan();
			this.context.put("View", listKeputusan);
			int eventStatus = 0;
			this.context.put("EventStatus", eventStatus);
			String id = getParam("idPermohonan");
			FrmSenaraiFailKeputusanPermohonanData.setData(id);
			Vector list = FrmSenaraiFailKeputusanPermohonanData.getData();
  	    	this.context.put("ViewPemohon", list);

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan.jsp";
		}
		else if("getBack_keputusan".equals(submit)){
			FrmSenaraiFailPusakaKecilData.setList();
			listKeputusan = FrmSenaraiFailPusakaKecilData.getList();
			String Carix = "2";
			this.context.put("carix", Carix);
			this.context.put("Senaraifail",listKeputusan);

			vm = "app/ppk/frmSenaraiFailKeputusanPermohonan.jsp";
		}
		else if("getSenaraiMahkamah".equals(submit)){
			String idflag = "0";
			this.context.put("idFlag", idflag);
			String IdPermohonan = getParam("idPermohonan");
			this.context.put("idPermohonan", IdPermohonan);
			FrmSenaraiFailPusakaKecilData.setList();
			listKeputusan = FrmSenaraiFailPusakaKecilData.getList();
			String Carix = "2";
			this.context.put("carix", Carix);
			this.context.put("Senaraifail",listKeputusan);

			FrmSenaraiFailKeputusanPermohonanData.setListNegeri();
			Vector listNegeri = FrmSenaraiFailKeputusanPermohonanData.getListNegeri();
			this.context.put("ListNegeri",listNegeri);

			vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";
		}
		else if("getMahkamah".equals(submit)){
			String IdPermohonan = getParam("idPermohonan");
			this.context.put("idPermohonan", IdPermohonan);

			int idNegeri = Integer.parseInt(getParam("socNegeri"));
			this.context.put("SocNegeri",idNegeri);

			FrmSenaraiFailKeputusanPermohonanData.setListNegeri();
			Vector listNegeri = FrmSenaraiFailKeputusanPermohonanData.getListNegeri();
			this.context.put("ListNegeri",listNegeri);

			FrmSenaraiFailKeputusanPermohonanData.setListDaerah(idNegeri);
			Vector listDaerah = FrmSenaraiFailKeputusanPermohonanData.getListDaerah();
			this.context.put("ListDaerah",listDaerah);

			vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";
		}
		else if("getMahkamahAddress".equals(submit)){
			String IdPermohonan = getParam("idPermohonan");
			this.context.put("idPermohonan", IdPermohonan);
			String idflag = "1";
			this.context.put("idFlag", idflag);
			String idneg= getParam("socNegeri");
			Long daerahId = Long.parseLong(getParam("socDaerah"));
			Long negId = Long.parseLong(getParam("socNegeri"));
			this.context.put("Senaraifail",listKeputusan);

			int idNegeri = Integer.parseInt(getParam("socNegeri"));
			this.context.put("SocNegeri",idNegeri);

			int idDaerah = Integer.parseInt(getParam("socDaerah"));
			this.context.put("SocDaerah",idDaerah);

			FrmSenaraiFailKeputusanPermohonanData.setListNegeri();
			Vector listNegeri = FrmSenaraiFailKeputusanPermohonanData.getListNegeri();
			this.context.put("ListNegeri",listNegeri);

			FrmSenaraiFailKeputusanPermohonanData.setListDaerah(idNegeri);
			Vector listDaerah = FrmSenaraiFailKeputusanPermohonanData.getListDaerah();
			this.context.put("ListDaerah",listDaerah);

			view_mahkamah(session);
			Vector dataMahkamah = FrmPrmhnnSek8KeputusanPermohonanData.getSemakMahkamah();
			this.context.put("infoMahkamah", dataMahkamah);
			vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";
		}
		else if("getSimpanMahkamah".equals(submit)){
			String IdPermohonan = getParam("idPermohonan");
			this.context.put("idPermohonan", IdPermohonan);
			check_id(session);
			Vector cntData = FrmPrmhnnSek8KeputusanPermohonanData.getSemakId();
			Hashtable h = (Hashtable)cntData.get(0);
			String cntResult = h.get("cntid").toString();
			if (cntResult.equals("0")){
				insertMahkamah(session);
			}else{
				updateMahkamah(session);
			}
			String idneg = getParam("socNegeri");
			Long daerahId = Long.parseLong(getParam("socDaerah"));
			Long negId = Long.parseLong(getParam("socNegeri"));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",negId,"onChange=getDaerah()"));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idneg,"socDaerah",daerahId,"","onChange=getAddress()"));
			vm = "app/ppk/frmPrmhnnSek8RujMahkamah.jsp";
		}
		else if("getSimpan_keputusan".equals(submit)){
			check_id(session);
			Vector cntData = FrmPrmhnnSek8KeputusanPermohonanData.getSemakId();
			Hashtable h = (Hashtable)cntData.get(0);
			String cntResult = h.get("cntid").toString();
			String id = getParam("idPermohonan");
			if (cntResult.equals("0")){
				insertBorang(session);
			}else{
				updateBorang(session);
			}
			view_mode(session);
			int eventStatus = 1;
			this.context.put("EventStatus", eventStatus);

			FrmSenaraiFailKeputusanPermohonanData.setData(id);
			Vector list = FrmSenaraiFailKeputusanPermohonanData.getData();
  	    	this.context.put("ViewPemohon", list);

			vm = "app/ppk/frmPrmhnnSek8KeputusanPermohonan.jsp";
		}

		else if (("next".equals(submit)) || ("previous".equals(submit))) {
			// Carix = "1";
			this.context.put("carix", 1);

			FrmSenaraiFailKeputusanPermohonanData.setListKeputusan();
			listKeputusan = FrmSenaraiFailKeputusanPermohonanData.getListKeputusan();


			session.setAttribute("Senaraifail", listKeputusan);
			listKeputusan = (Vector) session.getAttribute("Senaraifail");
			this.context.put("Senaraifail", listKeputusan);

			prepareItemForDisplay(session, listKeputusan, 15, submit);
			int countList = listKeputusan.size();
			this.context.put("CountList", countList);
			//vm = "app/ppk/frmSenaraiFailPusakaKecil.jsp";
			 vm = "app/ppk/frmSenaraiFailKeputusanPermohonan.jsp";
		}

		else{
			FrmSenaraiFailKeputusanPermohonanData.setListKeputusan();
			listKeputusan = FrmSenaraiFailKeputusanPermohonanData.getListKeputusan();
			String Carix = "2";
			this.context.put("carix", Carix);
			this.context.put("Senaraifail",listKeputusan);
			prepareItemForDisplay(session, listKeputusan, 15, "first");

			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"));
			 vm = "app/ppk/frmSenaraiFailKeputusanPermohonan.jsp";
			}
		this.context.put("Util", new lebah.util.Util());

		FrmPrmhnnSek8KeputusanPermohonanInternalData.setMaklumatMahkamahM();
		Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData.getMaklumatMahkamahM();
        this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahM);

		Template template = this.engine.getTemplate(vm);
		return template;
	}
	private void view_mode(HttpSession session) throws Exception {
		String id = getParam("idPermohonan");
		FrmPrmhnnSek8KeputusanPermohonanData.setDataKeputusan(id);
	}
	private void check_id(HttpSession session) throws Exception {
		String id = getParam("idPermohonan");
		FrmPrmhnnSek8KeputusanPermohonanData.semakId(id);
	}
	private void view_mahkamah(HttpSession session) throws Exception {
		int idDaerah = Integer.parseInt(getParam("socDaerah"));
		int idNegeri = Integer.parseInt(getParam("socNegeri"));
		FrmPrmhnnSek8KeputusanPermohonanData.semakAlamatMahkamah(idDaerah,idNegeri);
	}
	private void insertMahkamah(HttpSession session) throws Exception {
		Hashtable h = null;
	    h = new Hashtable();
	    h.put("idDaerah", getParam("socDaerah"));
	    h.put("idNegeri", getParam("socNegeri"));
	    h.put("IdPermohonan", getParam("idPermohonan"));
	    FrmPrmhnnSek8KeputusanPermohonanData.insertDataMahkamah(h);
	}
	private void updateMahkamah(HttpSession session) throws Exception {
		Hashtable h = null;
	    h = new Hashtable();
	    h.put("idDaerah", getParam("socDaerah"));
	    h.put("idNegeri", getParam("socNegeri"));
	    h.put("IdPermohonan", getParam("idPermohonan"));
	    FrmPrmhnnSek8KeputusanPermohonanData.updateDataMahkamah(h);
	}
	private void insertBorang(HttpSession session) throws Exception {

		System.out.println("1111111 ad:::"+getParam("sorPenentuanBidangKuasa"));
		System.out.println("2222222 ad:::"+getParam("sorPenentuanBidangKuasaTeruskan"));

		String check=getParam("sorPenentuanBidangKuasa");
		String checkterus=getParam("sorPenentuanBidangKuasaTeruskan");

		String ex="";






		if(checkterus!="" )//((check=="53" || check=="50" || check=="63"))
		{
			ex=checkterus;
		}
		else //if((check=="1") && (checkterus=="151" || checkterus=="152"))
		{
			ex=check;
		}


		System.out.println("CHECKKKup:::"+ex);
		//System.out.println("EXXXXXXXXXup:::"+ex);



		Hashtable h = null;
	    h = new Hashtable();

	    h.put("tarikhHantarBorangB", getParam("txdTarikhHantarBorangB"));
	    h.put("tarikhTerimaBorangC", getParam("txdTarikhTerimaBorangC"));
	    h.put("tarikhHantarNilaian", getParam("txdTarikhHantarNilaian"));
	    h.put("tarikhTerimaNilaian", getParam("txdTarikhTerimaNilaian"));
	    h.put("keputusanBorangC", getParam("sorKeputusanBorangC"));
	    h.put("penentuanBidangKuasa",ex);
	   // h.put("penentuanBidangKuasaTeruskan", getParam("sorPenentuanBidangKuasaTeruskan"));
	    h.put("catatan", getParam("txtCatatan"));
	    h.put("IdPermohonan", getParam("idPermohonan"));
	    FrmPrmhnnSek8KeputusanPermohonanData.insertBorang(h);
	}
	private void updateBorang(HttpSession session) throws Exception {
		System.out.println("1111111 up:::"+getParam("sorPenentuanBidangKuasa"));
		System.out.println("2222222 up:::"+getParam("sorPenentuanBidangKuasaTeruskan"));

		String check=getParam("sorPenentuanBidangKuasa");
		String checkterus=getParam("sorPenentuanBidangKuasaTeruskan");

		String ex="";






		if(checkterus!="" )//((check=="53" || check=="50" || check=="63"))
		{
			ex=checkterus;
		}
		else //if((check=="1") && (checkterus=="151" || checkterus=="152"))
		{
			ex=check;
		}


		System.out.println("CHECKKKup:::"+ex);
		//System.out.println("EXXXXXXXXXup:::"+ex);



		Hashtable h = null;
	    h = new Hashtable();
	    h.put("tarikhHantarBorangB", getParam("txdTarikhHantarBorangB"));
	    h.put("tarikhTerimaBorangC", getParam("txdTarikhTerimaBorangC"));
	    h.put("tarikhHantarNilaian", getParam("txdTarikhHantarNilaian"));
	    h.put("tarikhTerimaNilaian", getParam("txdTarikhTerimaNilaian"));
	    h.put("keputusanBorangC", getParam("sorKeputusanBorangC"));
	    h.put("penentuanBidangKuasa",ex);
	 //   h.put("penentuanBidangKuasaTeruskan", getParam("sorPenentuanBidangKuasaTeruskan"));
	    h.put("catatan", getParam("txtCatatan"));
	    h.put("IdPermohonan", getParam("idPermohonan"));
	    FrmPrmhnnSek8KeputusanPermohonanData.updateBorang(h);
	}

	private void prepareItemForDisplay(HttpSession session, Vector objVector,
			int cntItemPage, String command) {
		int x;
		int startno = 0;
		if (command == null)
			command = "first";
		if (session.getAttribute("_portal_startnoInternalFail") != null)
			startno = ((Integer) session
					.getAttribute("_portal_startnoInternalFail")).intValue();
		if (command.equals("previous"))
			if (startno == objVector.size()) {
				x = startno % cntItemPage;
				if (x > 0) {
					startno -= x;
					startno -= cntItemPage;
				} else {
					startno -= cntItemPage * 2;
					if (startno < 0)
						startno = 0;
				}
			} else {
				startno -= cntItemPage * 2;
				if (startno < 0)
					startno = 0;
			}
		else if (command.equals("first"))
			startno = 0;

		else if (command.equals("last"))
			x = cntItemPage;
		else if (command.equals("back"))
			if (startno == objVector.size()) {
				x = startno % cntItemPage;
				if (x == 0)
					x = cntItemPage;
				startno -= x;
			} else {
				startno -= cntItemPage;
				if (startno < 0)
					startno = 0;

			}

		Vector moduleVector = new Vector();
		int i = 0;
		int cnt = 0;
		if (objVector.size() > 0) {
			cnt = 0;
			for (i = startno; (cnt < cntItemPage) && (i < objVector.size());) {
				moduleVector.addElement(objVector.elementAt(i));

				++i;
				System.out.println("INT ::"+i);
				++cnt;
			}

		}

		session.setAttribute("_portal_moduleVectorInternalFail", moduleVector);

		this.context.put("startnoInt", new Integer(startno));
		this.context.put("totalInt", new Integer(objVector.size()));

		startno = i;

		session.setAttribute("_portal_startnoInternalFail",
				new Integer(startno));

	}

}
