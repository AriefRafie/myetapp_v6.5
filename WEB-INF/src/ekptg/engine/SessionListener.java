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

package ekptg.engine;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

/**
 * @author Shamsul Bahrin Abd Mutalib
 * @version 1.01
 */

public final class SessionListener implements HttpSessionListener {
	private static long session_counter = 0;
	static Logger myLogger = Logger.getLogger(SessionListener.class);
	
	public SessionListener() {} 

	//Notification that a session was created. 
	public void sessionCreated(HttpSessionEvent event) {
		Object source = event.getSource();	
		String sourceClassName = source.getClass().getName();
		session_counter++;
		//2016/11/15
		//myLogger.debug("### SESSION CREATED: " + event.getSession().getId());
	
	}
          
	//Notification that a session was invalidated. 
	public void sessionDestroyed(HttpSessionEvent event) {
		Object source = event.getSource();
		session_counter--;
		HttpSession session = event.getSession();
		//2016/11/15
		//myLogger.debug("### SESSION DESTROYED: " + session.getId());
		lebah.portal.ClassLoadManager.clearCache(session.getId());
		
	}
	
	public static long getNumberOfSession() { 
		return session_counter;
	}
	
	
}