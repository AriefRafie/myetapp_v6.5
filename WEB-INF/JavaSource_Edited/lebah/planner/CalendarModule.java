package lebah.planner;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

public class CalendarModule extends VTemplate
{
  public Template doTemplate()
    throws Exception
  {
    HttpSession session = this.request.getSession();
    setShowVM(false);
    String user = (!("".equals(getParam("peer_id")))) ? getParam("peer_id") : (String)session.getAttribute("_portal_login");
    this.context.put("user", user);

    this.context.put("current_user", user);
    String template_name = "vtl/calendar/calendar.vm";
    int yearSelect = 0; int monthSelect = 0;

    String submit = getParam("command");
    if ((!("getMonthly".equals(submit))) && 
      (!("getDaily".equals(submit))))
    {
      if ("addEventMark".equals(submit)) {
        int eventYear = (!("".equals(getParam("eventYear")))) ? Integer.parseInt(getParam("eventYear")) : 0;
        int eventMonth = (!("".equals(getParam("eventMonth")))) ? Integer.parseInt(getParam("eventMonth")) : 0;
        int eventDay = (!("".equals(getParam("eventDay")))) ? Integer.parseInt(getParam("eventDay")) : 0;
        Hashtable event = new Hashtable();
        event.put("userId", user);
        event.put("eventId", getParam("eventId"));
        event.put("eventText", getParam("eventText"));
        event.put("viewScope", getParam("viewScope"));
        EventData.addEvent(eventYear, eventMonth, eventDay, event);
      }
      else if ("deleteEvent".equals(submit)) {
        String uid = getParam("uid");
        EventData.deleteEvent(uid);
      }
    }
    yearSelect = (!("".equals(getParam("yearSelect")))) ? Integer.parseInt(getParam("yearSelect")) : 0;
    monthSelect = (!("".equals(getParam("monthSelect")))) ? Integer.parseInt(getParam("monthSelect")) : 0;
    this.context.put("yearSelect", new Integer(yearSelect));
    this.context.put("monthSelect", new Integer(monthSelect));

    CalendarMonth cm = new CalendarMonth();
    cm.setCalendar(user, yearSelect, monthSelect);
    CalendarMonth now = cm.getCalendarMonthNow();
    CalendarMonth before = cm.getCalendarMonthBefore();
    CalendarMonth after = cm.getCalendarMonthAfter();
    Hashtable[][] calendarDays = cm.getCalendarDays();
    this.context.put("now", now);
    this.context.put("before", before);
    this.context.put("after", after);
    this.context.put("calendarDays", calendarDays);

    Calendar calendar = new GregorianCalendar();
    int today = calendar.get(5);
    this.context.put("today", new Integer(today));

    Vector errorMessages = cm.getErrorMessages();
    this.context.put("errorMessages", errorMessages);

    Template template = this.engine.getTemplate(template_name);
    return template;
  }
}