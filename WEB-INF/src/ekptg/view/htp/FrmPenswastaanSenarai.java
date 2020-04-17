package ekptg.view.htp;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;



public class FrmPenswastaanSenarai extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(FrmPenswastaanSenarai.class);
	private String result = new String();
	private Date now = new Date();
	
    private SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
    
	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
  		String vm ="app/htp/frmPenswastaanSenarai.jsp";

		this.context.put("carianNoFail", "");
		this.context.put("carian", "");
		//this.context.put("carianNoFail", "");
		//String selectNegeri= HTML.SelectNegeri("socNegeri")
	    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"));
	    
	    String submit = getParam("command");
	    log.info("submit"+submit);
	    if(submit.equals("simpan")){
		    log.info("submit simpan"+submit);
	    }else{
		    log.info("submit X"+submit);
	    	
	    }

		return vm;
		
    }//close do template
	
	}//close class
