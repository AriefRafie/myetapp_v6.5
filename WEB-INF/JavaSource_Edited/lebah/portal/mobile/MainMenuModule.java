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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.velocity.Template;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class MainMenuModule extends lebah.portal.velocity.VTemplate {
    
    public Template doTemplate() throws Exception {
        HttpSession session = request.getSession();
        Date now = new Date();
        String submit = getParam("command");
        String template_name = prepareTemplate(session, submit);
        request.getSession().removeAttribute("_where_stmt");
        
    	context.put("now", now);
    	context.put("dateFormat", new SimpleDateFormat("EEEE, dd MMM yyyy"));
    	
        Template template = engine.getTemplate(template_name); 
        return template;        
    }
    
    String prepareTemplate(HttpSession session, String submit) throws Exception {
        String template_name = "vtl/mobile/main_menu.vm";
        String user = (String) session.getAttribute("_portal_login");
        String role = (String) session.getAttribute("_portal_role");
        String moduleName = (String) session.getAttribute("_portal_module");

        if ( "anon".equals(user)) {
        	context.put("isLogin", false);
        }
        else {
        	String userName = (String) session.getAttribute("_portal_username");
        	context.put("isLogin", true);
        	context.put("userName", userName);
        }
        
        Vector modules = MobileData.getModules(role);
        context.put("modules", modules);
        context.put("moduleName", moduleName);
        context.put("role", role);
        return template_name;
    }
    
	private Date parseDate(String dateTxt) throws java.text.ParseException { 
		if ( dateTxt != null && !"".equals(dateTxt)) { 
			try { 
				return new SimpleDateFormat("dd-MM-yyyy").parse(dateTxt);			
			} catch (ParseException e) {
				return null;
			}
		}
			return null;
	}
}