package ekptg.view.pdt;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.pdt.FrmTambahDokumenData;

public class FrmTambahDokumen extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String PATH="app/pdt/";
	static Logger myLogger = Logger.getLogger(FrmTambahDokumen.class);

	@Override
	public String doTemplate2() throws Exception {
		FrmTambahDokumenData logic = new FrmTambahDokumenData();
		HttpSession session = this.request.getSession();
		String hitButton = getParam("hitButton");
		String tajuk = getParam("tajuk");
		String id = getParam("id");
		String txtNamaDokumen = getParam("txtNamaDokumen");
		String idTblPdtSenaraiDokumen = getParam("idTblPdtSenaraiDokumen");
		String step = getParam("step");
		
		myLogger.info("hitButton : "+hitButton);
		myLogger.info("txtNamaDokumen : "+txtNamaDokumen);
		
		if(hitButton.equals("saveInduk")){
			logic.save(txtNamaDokumen, session);
		}else if(hitButton.equals("saveChild")){
			if(txtNamaDokumen != ""){
				logic.saveChild(txtNamaDokumen, session,id);
				this.context.put("hitButton", hitButton);
			}
		}else if(hitButton.equals("addChild")){
			this.context.put("id", id);
			this.context.put("txtNamaDokumen", "");
			this.context.put("step", "addChild");
		}else if(hitButton.equals("saveEditInduk")){
			logic.editChild(txtNamaDokumen, session,idTblPdtSenaraiDokumen);
		}else if(hitButton.equals("editInduk")){
			this.context.put("idTblPdtSenaraiDokumen", id);
			this.context.put("txtNamaDokumen", tajuk);
			this.context.put("step", "editInduk");
		}else{
			this.context.put("id", "");
			this.context.put("txtNamaDokumen", "");
			this.context.put("step", "addInduk");
		}
		
		return PATH+"frmTambahDokumen.jsp";
	}

}
