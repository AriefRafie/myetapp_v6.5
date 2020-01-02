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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class MFooter extends lebah.portal.velocity.VTemplate {

	public MFooter(VelocityEngine engine, VelocityContext context, HttpServletRequest req, HttpServletResponse res) {
		super(engine, context, req, res);
	}
	
	public Template doTemplate() throws Exception {
        HttpSession session = request.getSession();
        String moduleName = (String) session.getAttribute("_portal_module");
        
        Template template = engine.getTemplate("vtl/mobile/bottom.vm"); 
        context.put("moduleName", moduleName);
        context.put("htpPath", (String) request.getSession().getAttribute("_HTP_path"));
        context.put("ppkPath", (String) request.getSession().getAttribute("_PPK_path"));
        return template; 
	}
}