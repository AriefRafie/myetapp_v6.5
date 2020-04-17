
package ekptg.view.pdt;

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
import org.apache.log4j.Logger;

import ekptg.model.pdt.FrmTambahDokumenData;

public class FrmSenaraiDokumen_Backup extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String PATH="app/pdt/";
	static Logger myLogger = Logger.getLogger(FrmSenaraiDokumen_Backup.class);

	@Override
	public String doTemplate2() throws Exception {
		FrmTambahDokumenData logic = new FrmTambahDokumenData();
		HttpSession session = this.request.getSession();
		String hitButton = getParam("hitButton");
		String vm = "";
		
		if(hitButton.equals("saveRujLamp")){
			String id = getParam("id");
			this.context.put("id", id);
			this.context.put("txtNamaDokumen", "");
			vm = PATH+"frmPopupMuatNaikImejTblPdtRujLamp.jsp";
		}else if(hitButton.equals("addDoc")){
			String txtNamaDokumen = getParam("txtNamaDokumen");
			String id = getParam("id");
			if(txtNamaDokumen != ""){
				FileItem item = getItem();
				logic.saveRujLamp(item,id,txtNamaDokumen,session);
				vm = viewList(logic, id,session);
			}
		}else if(hitButton.equals("hapus")){
			String id = getParam("id");
			String idTblRujLamp = getParam("idTblRujLamp");
			logic.deleteTblRujLamp(idTblRujLamp);
			vm = viewList(logic, id,session);
		}else if(hitButton.equals("viewList")){
			String txtNamaDokumen = getParam("txtNamaDokumen");
			String txtNamaDokumenHide = getParam("txtNamaDokumenHide");
			String id = getParam("id");
			this.context.put("txtNamaDokumenHide", txtNamaDokumen == "" ? txtNamaDokumenHide : txtNamaDokumen);
			vm = viewList(logic, id,session);
			
		}else{
			String txtNamaDokumen = getParam("txtNamaDokumen");
			String txtNamaDokumenHide = getParam("txtNamaDokumenHide");
			String id = getParam("id");
			Vector senaraiDokumenInduk = new Vector<>();
			if(hitButton.equals("cari")){
				senaraiDokumenInduk = logic.findByNamaDok(txtNamaDokumen == "" ? txtNamaDokumenHide : txtNamaDokumen);
				this.context.put("txtNamaDokumenHide", txtNamaDokumen == "" ? txtNamaDokumenHide : txtNamaDokumen);
				this.context.put("txtNamaDokumen", txtNamaDokumen == "" ? txtNamaDokumenHide : txtNamaDokumen);
			}
			if(hitButton.equals("delete")){
				logic.delete(id);
				senaraiDokumenInduk = logic.findByNamaDok(txtNamaDokumen == "" ? txtNamaDokumenHide : txtNamaDokumen);
				this.context.put("txtNamaDokumenHide", txtNamaDokumen == "" ? txtNamaDokumenHide : txtNamaDokumen);
				this.context.put("txtNamaDokumen", txtNamaDokumen == "" ? txtNamaDokumenHide : txtNamaDokumen);
			}
			if(!txtNamaDokumenHide.equals("")){
				senaraiDokumenInduk = logic.findByNamaDok(txtNamaDokumenHide);
			}
			if(hitButton.equals("")){
				senaraiDokumenInduk = logic.findByNamaDok("");
			}
			setupPage(session,null,senaraiDokumenInduk);
			this.context.put("senaraiDokumenInduk", senaraiDokumenInduk);
			vm = PATH+"frmSenaraiDokumen.jsp";
		}
		
		return vm;
	}

	private String viewList(FrmTambahDokumenData logic, String id, HttpSession session) {
		String vm;
		Hashtable h = logic.findById(id);
		Vector snriRujLam = new Vector<>();
		snriRujLam = logic.findByIdPdtSnriDkmn(id);
		setupPage(session,null,snriRujLam);
		this.context.put("id", id);
		this.context.put("snriRujLam", snriRujLam);
		this.context.put("title", h.get("perihal"));
		vm = PATH+"frmTambahSenaraiDokumen.jsp";
		return vm;
	}
	
	private FileItem getItem() throws FileUploadException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List items = upload.parseRequest(request);
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
		  FileItem item = (FileItem)itr.next();
		  if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
			  return item;
		  }
		}
		return null;
	}

}
