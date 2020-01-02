/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ekptg.view;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

public class FrmManualPenggunaMEP extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9012102407918820290L;
	private final String PATH="app/";
    private static Logger myLog = Logger.getLogger(ekptg.view.FrmManualPenggunaMEP.class);
      
    @Override
    public String doTemplate2() throws Exception {
      	//HttpSession session = this.request.getSession();
		String vm = PATH+"frmManualPenggunaMEP.jsp";
        return vm;
        
	}
	
	
}
