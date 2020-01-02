/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package ekptg.view.ppk;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8Data;
import ekptg.model.ppk.FrmPrmhnnSek8Data;

public class FrmPrmhnnSek8SecaraOnline extends VTemplate{

private static final long serialVersionUID = 1L;
	
	FrmPrmhnnSek8Data logic = new FrmPrmhnnSek8Data();
	
	@SuppressWarnings("unchecked")
	public Template doTemplate() throws Exception {
		HttpSession session = this.request.getSession();
		String vm = "app/ppk/frmBorangASimati.jsp";
		String submit = getParam("command");
		int eventStatus = 0;
		
		
		if("getFormWaris".equals(submit)){
		
			vm = "app/ppk/frmBorangAWaris.jsp";
			
		}
		else if("getHartaTakAlihYa".equals(submit)){
			eventStatus = 0;
			this.context.put("EventStatus", eventStatus);
			
			String valueSor = getParam("sorAdaHTA");
			this.context.put("sorAdaHTA", valueSor);
			
			vm = "app/ppk/frmBorangASimati.jsp";
			
		}
		else if("getHartaTakAlihTidak".equals(submit)){
			eventStatus = 1;
			this.context.put("EventStatus", eventStatus);
			
			String valueSor = getParam("sorAdaHTA");
			this.context.put("sorAdaHTA", valueSor);
			
			vm = "app/ppk/frmBorangASimati.jsp";
			
		}
		else{
			eventStatus = 1;
			this.context.put("EventStatus", eventStatus);
			
			Vector listSaudara = FrmPrmhnnSek8DaftarSek8Data.getListsaudara();
			this.context.put("ListPersaudaraan", listSaudara);
			
			Vector view1 = FrmPrmhnnSek8DaftarSek8Data.getJenisKp();
			this.context.put("listkp", view1);
			
			Vector listbuktimati = FrmPrmhnnSek8DaftarSek8Data.getListbuktimati();
			this.context.put("ListBuktiMati", listbuktimati);
			
		}
		
		Template template = this.engine.getTemplate(vm);
		return template;
	}
}
