/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ekptg.view;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class FrmManualPenggunaKJP extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9012102407918820290L;
	private final String PATH="app/";
    private static Logger myLog = Logger.getLogger(ekptg.view.FrmManualPenggunaKJP.class);
      
    @Override
    public String doTemplate2() throws Exception {
      	HttpSession session = this.request.getSession();
		String vm = PATH+"frmManualPenggunaKJP.jsp";
		Utils utils = new Utils();
		//myLog.info(utils.getTabID("Menu Utama","online_kjp"));
        return vm;
        
	}
	
	
}
