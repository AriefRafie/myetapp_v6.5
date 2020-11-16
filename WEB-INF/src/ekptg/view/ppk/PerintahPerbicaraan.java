/**
 *
 */
package ekptg.view.ppk;

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

		//VelocityContext context = new VelocityContext();

        //Vector beanMaklumatHTA = null;
		//beanMaklumatHTA.clear();
		//logic.setDataMaklumatHTA(idHTA, idPermohonanSimati);
		//beanMaklumatHTA = logic.getBeanMaklumatHTA();

		this.context.put("beanMaklumatHTA", "123");
		return "app/ppk/BicaraInteraktif/fm_perintah_perbicaraan_htaah.jsp";
	}
}