package etapp.internal;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.velocity.Template;

import etapp.data.PPTHakmilikMobile;
import etapp.data.PPTPermohonanMobile;
import etapp.template.VMobile;

public class PPTBantahanModule extends VMobile<PPTPermohonanMobile> {
	
	public Template doTemplate() throws Exception {	
		HttpSession session = request.getSession();
//		this.setPersistentClass(PPTBantahan.class);
//		this.setOrderBy("status");
//		this.setOrderType("DESC");
//		this.setRowSize(10);
//		this.setRowStart(0);
		
		context.put("divList", "hidden");
		String submit = getParam("command");
		String role = (String) session.getAttribute("_portal_role");
		String user = (String) session.getAttribute("_portal_login");
		
		if ( role.equals("online")) {
			List<PPTPermohonanMobile> records = db.list("SELECT DISTINCT p FROM PPTPermohonanMobile p, PPTHakmilikPBMobile pb, UsersMobile u, UsersDataMobile ud, PPTAwardMobile a, PPTBorangHMobile bh WHERE pb.hakmilik.permohonan.id = p.id AND pb.pihakPenting.noPB = ud.uKPBaru AND ud.id = u.id AND pb.id = a.hakmilikPB.id AND pb.id = bh.hakmilikPB.id AND p.fail.subUrusan.id = 52 AND u.uLogin = '" + user + "' AND pb.hakmilik.fTarikKeseluruhan IS NULL AND pb.hakmilik.fBatalKeseluruhan IS NULL");
			context.put("records", records);
		} else {
			if ( !"".equals(submit) ) {
//				List<PPTBantahan> recordsBantahan = db.list("SELECT DISTINCT b, h FROM PPTBantahan b, PPTHakmilik h, PPTPermohonan p, UsersKementerian uk WHERE b.hakmilik.id = h.id AND h.permohonan.id = p.id AND p.fail.noFail = '" + submit + "' AND p.fail.kementerian.id = uk.kementerian.id AND uk.users.uLogin = '" + user + "' AND p.fail.subUrusan.id = 52 AND (SELECT DISTINCT bh.hakmilikPB.hakmilik.permohonan.id FROM PPTBorangH bh WHERE bh.hakmilikPB.hakmilik.permohonan.id = p.id) > 0 AND h.fTarikKeseluruhan IS NULL AND h.fBatalKeseluruhan IS NULL");
				List<PPTHakmilikMobile> recordsHakmilik = db.list("SELECT DISTINCT h FROM PPTHakmilikMobile h, PPTPermohonanMobile p, UsersKementerianMobile uk WHERE h.permohonan.id = p.id AND p.fail.noFail = '" + submit + "' AND p.fail.kementerian.id = uk.kementerian.id AND uk.users.uLogin = '" + user + "' AND p.fail.subUrusan.id = 52 AND (SELECT DISTINCT bh.hakmilikPB.hakmilik.permohonan.id FROM PPTBorangHMobile bh WHERE bh.hakmilikPB.hakmilik.permohonan.id = p.id) > 0 AND h.fTarikKeseluruhan IS NULL AND h.fBatalKeseluruhan IS NULL");
				
				context.put("recordsHakmilik", recordsHakmilik); context.put("divList", "visible");
			}
			List<PPTPermohonanMobile> records = db.list("SELECT DISTINCT p FROM PPTPermohonanMobile p, UsersKementerianMobile uk, PPTHakmilikMobile h WHERE h.permohonan.id = p.id AND p.fail.kementerian.id = uk.kementerian.id AND uk.users.uLogin = '" + user + "' AND p.fail.subUrusan.id = 52 AND (SELECT DISTINCT bh.hakmilikPB.hakmilik.permohonan.id FROM PPTBorangHMobile bh WHERE bh.hakmilikPB.hakmilik.permohonan.id = p.id) > 0 AND h.fTarikKeseluruhan IS NULL AND h.fBatalKeseluruhan IS NULL");
			context.put("records", records);
		}		
//		listRecord(1);
		
	    Template template = engine.getTemplate("vtl/mobile/PPTBantahan/bantahan.vm"); 
	    return template;       
	}
	
	@Override
	public Map<String, Object> searchCriteria() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
