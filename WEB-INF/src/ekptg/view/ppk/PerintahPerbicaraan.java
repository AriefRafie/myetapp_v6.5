/**
 *
 */
package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import org.apache.velocity.VelocityContext;

import lebah.portal.velocity.VTemplate;

/**
 * @author delang
 *
 */
@SuppressWarnings("serial")
public class PerintahPerbicaraan extends VTemplate {

	/*
	 * delang
	 * */
	public String perintahHtaah(String idHTA)
		throws Exception {

        Vector<Hashtable<String, String>> beanMaklumatHTA = new Vector<Hashtable<String, String>>();
		beanMaklumatHTA.clear();
		//logic.setDataMaklumatHTA(idHTA, idPermohonanSimati);
		//beanMaklumatHTA = logic.getBeanMaklumatHTA();

		this.context.put("beanMaklumatHTA", "beanMaklumatHTA");
		return "app/ppk/BicaraInteraktif/fm_perintah_perbicaraan_htaah.jsp";
	}

    protected VelocityContext context;
}