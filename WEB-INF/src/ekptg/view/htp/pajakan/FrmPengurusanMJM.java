package ekptg.view.htp.pajakan;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ekptg.model.htp.entity.TblMemoMenteriForm;
import ekptg.model.htp.pajakan.FrmPengurusanMJMData;

public class FrmPengurusanMJM extends AjaxBasedModule{

	private static final String PATH="app/htp/pajakan/pengurusan/";
	private static Vector<Hashtable<String,String>> docData =  new Vector<Hashtable<String,String>>();
	private int bil = 1;
	FrmPengurusanMJMData logic = new FrmPengurusanMJMData();
	private static Vector<Hashtable<String,String>> listMemo = new Vector<Hashtable<String,String>>();
	private static Vector<Hashtable<String,String>> listMemobyNoFail = new Vector<Hashtable<String,String>>();
	private static final long serialVersionUID = 1L;
	private String socKategori = "";

	@Override
	public String doTemplate2() throws Exception {

	HttpSession session = this.request.getSession();
	String vm = "";
	Hashtable<String,String> h;
	//setupPage(session,null,new Vector());
	String hitButton = getParam("hitButton");
	String action = getParam("action");
	String pageNow = getParam("pageNow");

	this.context.put("saveNoti", false);
	this.context.put("mjm", "mjm");

	socKategori = getParam("sockategori");

	System.out.println("socStatus atas >>>> "+ socKategori);
	System.out.println("hitButton >>>> "+hitButton);

		if(hitButton.equals("carian")){
			String txtNoMemo = getParam("txtNoMemo");
	        String txtNoFail = getParam("txtNoFail");
	        String txdTarikh = getParam("txdTarikh");
	        listMemo = logic.findBy(txtNoMemo,txtNoFail,txdTarikh,"1,2");
	        this.context.put("pageNow", "carian");
	        setupPage(session,action,listMemo);
			vm = PATH+"index.jsp";

		}else if(hitButton.equals("hapus")){
			logic.delete(getParam("idTblDocMemo"));
			TblMemoMenteriForm mmf = logic.findBy(Long.parseLong(getParam("idTblMemoMenteri")));
			this.context.put("idTblMemoMenteri", mmf.getIdTblHtpMemoMenteri());
			this.context.put("txtNoMemo", mmf.getNoMemo());
			this.context.put("txtNoFail", mmf.getNoFailSeksyen());
			this.context.put("txdTarikh", mmf.getTarikh());
			this.context.put("txtCatatan", mmf.getCatatan());
			this.context.put("socStatus", mmf.getStatus());
			this.context.put("pageNow", "docMemo");
			docData = new Vector<>();
			docData = logic.findDocBy(mmf.getIdTblHtpMemoMenteri());
			setupPage(session,action,docData);
			vm =  PATH+"frmDaftarMemorandum.jsp";

		}else if(hitButton.equals("simpan")){
	        Long idMemo = logic.saveMemo(getParam("txtNoMemo")
    				,getParam("txtNoFail")
    				,getParam("txdTarikh")
    				,getParam("txtCatatan")
    				,getParam("socStatus")
    				,docData,session
    				,getParam("idTblMemoMenteri")
    				,Integer.parseInt(getParam("sockategori")));
	        docData = new Vector<>();
			docData = logic.findDocBy(idMemo != null ? idMemo :Long.parseLong(getParam("idTblMemoMenteri")));
			setupPage(session,action,docData);
//			if(idMemo == null){
				this.context.put("saveNoti", true);
//			}else{
//				if(idMemo != 0L){
//					this.context.put("saveNoti", false);
//				}else{
//					this.context.put("saveNoti", true);
//				}
//			}
			this.context.put("idTblMemoMenteri", idMemo);
			this.context.put("pageNow", "docMemo");
			this.context.put("txtNoMemo", getParam("txtNoMemo"));
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txdTarikh", getParam("txdTarikh"));
			this.context.put("txtCatatan", getParam("txtCatatan"));
			this.context.put("socStatus", getParam("socStatus"));
			vm =  PATH+"frmDaftarMemorandum.jsp";

		}else if(hitButton.equals("edit")){
			bil = 1;
			TblMemoMenteriForm mmf = logic.findBy(Long.parseLong(getParam("id")));
			this.context.put("idTblMemoMenteri", mmf.getIdTblHtpMemoMenteri());
			this.context.put("txtNoMemo", mmf.getNoMemo());
			this.context.put("txtNoFail", mmf.getNoFailSeksyen());
			this.context.put("txdTarikh", mmf.getTarikh());
			this.context.put("txtCatatan", mmf.getCatatan());
			this.context.put("socStatus", mmf.getStatus());
			this.context.put("socKategori", mmf.getIdKategori());
			docData = new Vector<>();
			docData = logic.findDocBy(mmf.getIdTblHtpMemoMenteri());
			setupPage(session,action,docData);
			if (1==mmf.getIdKategori()){
				String noFail = mmf.getNoFailSeksyen();
				System.out.println("noFail >>>> "+noFail);
				if(noFail.equals("null") || noFail.equals("") || noFail.equals("-")){

				}else{

					listMemobyNoFail = logic.findByNoFail(noFail);
					this.context.put("listMemobyNoFail", listMemobyNoFail);
				}
			}else{
				this.context.put("listMemobyNoFail", "");
			}
			if (1==mmf.getIdKategori()) {
				this.context.put("selected", "");
				this.context.put("selected1", "selected");
				this.context.put("selected2", "");
				this.context.put("selected3", "");
				this.context.put("selected4", "");
				this.context.put("sockategori", socKategori);
        	} else if (2==mmf.getIdKategori()) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "selected");
				this.context.put("selected3", "");
				this.context.put("selected4", "");
				this.context.put("sockategori", socKategori);
        	} else if (3==mmf.getIdKategori()) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
				this.context.put("selected3", "selected");
				this.context.put("selected4", "");
				this.context.put("sockategori", socKategori);
        	} else if (4==mmf.getIdKategori()) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
				this.context.put("selected3", "");
				this.context.put("selected4", "selected");
				this.context.put("sockategori", socKategori);
        	} else {
        		this.context.put("selected", "selected");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
				this.context.put("selected3", "");
				this.context.put("selected4", "");
				this.context.put("idJenisTanah", "0");
        	}
			this.context.put("pageNow", "docMemo");
			vm =  PATH+"frmDaftarMemorandum.jsp";

		}else if(hitButton.equals("daftarBaru")){
			bil = 1;
			this.context.put("txtNoMemo", "");
			this.context.put("txtNoFail", "");
			this.context.put("txdTarikh", "");
			this.context.put("txtCatatan", "");
			this.context.put("socStatus", "");
			this.context.put("idTblMemoMenteri", "");
			this.context.put("mode", "new");
			docData = new Vector<>();
			setupPage(session,action,docData);
			this.context.put("pageNow", "docMemo");
			vm =  PATH+"frmDaftarMemorandum.jsp";

		}else if(hitButton.equals("addDoc")){
			for (Object object : docData) {
				Hashtable<String,String> hashHeader = (Hashtable<String,String>) object;
				bil = Integer.parseInt(String.valueOf(hashHeader.get("bil").toString()));
				bil++;
			}
			String txtNamaDokumen = getParam("txtNamaDokumen");
			if(txtNamaDokumen != ""){
				FileItem item = getItem();
				if(item != null){
					h = new Hashtable<String,String>();
					h.put("bil", String.valueOf(bil));
					h.put("namaDokumen",
							txtNamaDokumen == null ? "" : txtNamaDokumen);
					h.put("namaFail",item.getName());
					h.put("itemInputStream",String.valueOf(item.getInputStream()));
					h.put("size",String.valueOf(item.getSize()));
					docData.addElement(h);
					bil++;

				}else{
					docData = new Vector<>();
				}
			}
			setupPage(session,action,docData);
			this.context.put("txtNoMemo", getParam("txtNoMemo"));
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txdTarikh", getParam("txdTarikh"));
			this.context.put("txtCatatan", getParam("txtCatatan"));
			this.context.put("socStatus", getParam("socStatus"));
			this.context.put("pageNow", "docMemo");
			vm =  PATH+"frmDaftarMemorandum.jsp";

		}else if(hitButton.equals("deleteMemo")){

			logic.deleteMemo(getParam("id"));

			bil = 1;
	        this.context.put("txtNoMemo", "");
	        this.context.put("txtNoFail", "");
	        this.context.put("txdTarikh", "");
	        listMemo = new Vector<>();
	        listMemo = logic.findBy("","","","1,2");
	        setupPage(session,action,listMemo);
	        this.context.put("pageNow", "carian");
			vm = PATH+"index.jsp";

		}else if(hitButton.equals("doChangeKategori")){
			bil = 1;
			this.context.put("txtNoMemo", getParam("txtNoMemo"));
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txdTarikh", getParam("txdTarikh"));
			this.context.put("txtCatatan", getParam("txtCatatan"));
			this.context.put("socStatus", getParam("socStatus"));
			this.context.put("sockategori", getParam("sockategori"));

			if ("1".equals(socKategori)) {
				this.context.put("selected", "");
				this.context.put("selected1", "selected");
				this.context.put("selected2", "");
				this.context.put("selected3", "");
				this.context.put("selected4", "");
				this.context.put("sockategori", socKategori);
        	} else if ("2".equals(socKategori)) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "selected");
				this.context.put("selected3", "");
				this.context.put("selected4", "");
				this.context.put("sockategori", socKategori);
        	} else if ("3".equals(socKategori)) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
				this.context.put("selected3", "selected");
				this.context.put("selected4", "");
				this.context.put("sockategori", socKategori);
        	} else if ("4".equals(socKategori)) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
				this.context.put("selected3", "");
				this.context.put("selected4", "selected");
				this.context.put("sockategori", socKategori);
        	} else {
        		this.context.put("selected", "selected");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
				this.context.put("selected3", "");
				this.context.put("selected4", "");
				this.context.put("idJenisTanah", "0");
        	}


			System.out.println("socKategori >>>> "+socKategori);
			if ("1".equals(socKategori)){
				String noFail = getParam("txtNoFail");
				System.out.println("noFail >>>> "+noFail);
				if(noFail.equals("null") || noFail.equals("")){

				}else{

					listMemobyNoFail = logic.findByNoFail(noFail);
					this.context.put("listMemobyNoFail", listMemobyNoFail);
				}
			}else{
				this.context.put("listMemobyNoFail", "");
			}
			vm =  PATH+"frmDaftarMemorandum.jsp";
			System.out.println("sockategori >>>>> "+socKategori);

		}else{
			if(action.equals("getPage")){
				if(pageNow.equals("carian")){
					setupPage(session,action,listMemo);
					this.context.put("pageNow", "carian");
					vm = PATH+"index.jsp";

				}else{
					setupPage(session,action,docData);
					this.context.put("pageNow", "docMemo");
					this.context.put("txtNoMemo", getParam("txtNoMemo"));
					this.context.put("txtNoFail", getParam("txtNoFail"));
					this.context.put("txdTarikh", getParam("txdTarikh"));
					this.context.put("txtCatatan", getParam("txtCatatan"));
					this.context.put("socStatus", getParam("socStatus"));
					vm = PATH+"frmDaftarMemorandum.jsp";

				}
			}else{
				bil = 1;
		        this.context.put("txtNoMemo", "");
		        this.context.put("txtNoFail", "");
		        this.context.put("txdTarikh", "");
		        listMemo = new Vector<>();
		        listMemo = logic.findBy("","","","1,2");
		        setupPage(session,action,listMemo);
		        this.context.put("pageNow", "carian");
		        vm = PATH+"index.jsp";

			}
		}
		return vm;

	}

	private FileItem getItem() throws FileUploadException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<?> items = upload.parseRequest(request);
		Iterator<?> itr = items.iterator();
		while (itr.hasNext()) {
		  FileItem item = (FileItem)itr.next();
		  if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
			  return item;
		  }
		}
		return null;

	}


}
