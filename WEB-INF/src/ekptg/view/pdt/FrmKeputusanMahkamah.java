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

import ekptg.model.pdt.FrmKeputusanMahkamahData;

public class FrmKeputusanMahkamah extends AjaxBasedModule {
	private static Logger myLog = Logger.getLogger(ekptg.view.pdt.FrmKeputusanMahkamah.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Vector list = new Vector();

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();	
		FrmKeputusanMahkamahData logic = new FrmKeputusanMahkamahData();
		String vm = "app/pdt/frmKeputusanMahkamah.jsp";
		String action = getParam("action");
		String txtSkop = getParam("txtSkop");
		String txtNamaKes = getParam("txtNamaKes");
		String txtCitation = getParam("txtCitation");
		//tambah medan tahun (bella)
		String tahun = getParam("tahun");
		String idDokumen = getParam("idDokumen");
		String hitButton = getParam("hitButton");
		String idLampiran = getParam("idLampiran");
		List list_TBLRUJSKOP = null;
		
		list_TBLRUJSKOP = logic.listTableRujukanSkop(session,"TBLRUJSKOP","");
		this.context.put("list_TBLRUJSKOP",list_TBLRUJSKOP);
		
		//println command 
		myLog.info(" action : "+action);
		if(action.equals("carian")){
			list = new Vector<>();
			list = logic.findTblPdtDokumenBy(txtSkop,txtNamaKes,txtCitation);
			setupPage(session,action,list);
		}else if(action.equals("tambahBaru")){
			 context.put("idDokumen","");
	    	 context.put("skop","");
	    	 context.put("namaKes","");
	    	 context.put("rujMahkamah","");
	    	 context.put("tahun","");
	    	 context.put("idLampiran","");
			vm = "app/pdt/frmAddKeputusanMahkamah.jsp";
		}else if(action.equals("delete")){
			logic.delete(idDokumen);
			list = new Vector<>();
			list = logic.findTblPdtDokumenBy(txtSkop,txtNamaKes,txtCitation);
			setupPage(session,action,list);
		}else if(action.equals("edit")){
			Hashtable h = logic.findTblPdtDokumenById(idDokumen);
	    	 context.put("idDokumen",h.get("idDokumen")== null?"":h.get("idDokumen"));
	    	 context.put("skop",h.get("skop")== null?"":h.get("skop"));
	    	 context.put("namaKes",h.get("namaKes")== null?"":h.get("namaKes"));
	    	 context.put("rujMahkamah",h.get("rujMahkamah")== null?"":h.get("rujMahkamah"));
	    	 context.put("tahun",h.get("tahun")== null?"":h.get("tahun"));
	    	 context.put("idLampiran",h.get("idLampiran")== null?"":h.get("idLampiran"));
	    	 context.put("content",h.get("content"));
	    	 vm = "app/pdt/frmAddKeputusanMahkamah.jsp";
		}else if(action.equals("getPage")){
			setupPage(session,action,list);
		}else{
			if(hitButton.equals("save")){
				FileItem item = getItem();
				if(item != null){
					//penambahan parameter tahun (bella)
					logic.save(txtSkop,txtNamaKes,txtCitation,tahun,session,item);
				}else{
					//penambahan parameter tahun (bella)
					logic.save(txtSkop,txtNamaKes,txtCitation,tahun,session);
				}
				list = new Vector<>();
				//list = logic.findTblPdtDokumenBy(txtSkop,txtNamaKes,txtCitation);
				list = logic.findTblPdtDokumenBy("","","");
				setupPage(session,action,list);
			}else if(hitButton.equals("saveEdit")){
				FileItem item = getItem();
				if(item != null){
					//penambahan parameter tahun (bella)
					logic.saveEdit(idDokumen,txtSkop,txtNamaKes,txtCitation,tahun,session,item,idLampiran);
				}else{
					//penambahan parameter tahun (bella)
					logic.saveEdit(idDokumen, txtSkop, txtNamaKes,txtCitation,tahun,session,idLampiran);
				}
				list = new Vector<>();
				//list = logic.findTblPdtDokumenBy(txtSkop,txtNamaKes,txtCitation);
				list = logic.findTblPdtDokumenBy("","","");
				setupPage(session,action,list);
			}else{
				list = new Vector<>();
				list = logic.findTblPdtDokumenBy("","","");
				setupPage(session,action,list);
			}
		}
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
			  myLog.info(item.getName());
			  return item;
		  }
		}
		return null;
	}

}
