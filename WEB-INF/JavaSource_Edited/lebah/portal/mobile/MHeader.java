/* ************************************************************************
LEBAH PORTAL FRAMEWORK, http://lebah.sf.net
Copyright (C) 2007  Shamsul Bahrin







but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

* ************************************************************************ */
package lebah.portal.mobile;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * 
 * @author Shamsul Bahrin Abd Mutalib
 * @version 1.01
 */

public class MHeader extends lebah.portal.velocity.VTemplate {

	public MHeader(VelocityEngine engine, VelocityContext context, HttpServletRequest req, HttpServletResponse res) {
		super(engine, context, req, res);
	}
	
	public Template doTemplate() throws Exception {
        HttpSession session = request.getSession();
        
        String submit = getParam("command");
        String template_name = prepareTemplate(session, submit);
    	
        Template template = engine.getTemplate(template_name);  
        return template;
	}
	
	String prepareTemplate(HttpSession session, String submit) throws Exception {
        String role = (String) session.getAttribute("_portal_role");
        String template_name = "vtl/mobile/top.vm";	
        
        Vector modules = MobileData.getModules(role);
        context.put("modules", modules);
    	
        return template_name;
	}
}
