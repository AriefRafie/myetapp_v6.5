package lebah.planner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

public class CalendarMonth
{
  private String dateString;
  private String monthYearText;
  private int day;
  private int month;
  private int year;
  private int dayOfWeek;
  private int maxDays;
  private int weeks;
  private CalendarMonth calendarMonthNow;
  private CalendarMonth calendarMonthBefore;
  private CalendarMonth calendarMonthAfter;
  private Hashtable[][] calendarDays;
  private Vector errorMessages;

  public static void main(String[] args)
  {
    CalendarMonth t = new CalendarMonth();
    t.setCalendar("", 2006, 2);
  }

  public String getDateString()
  {
    return this.dateString;
  }

  public void setDateString(String dateString)
  {
    this.dateString = dateString;
  }

  public int getDay()
  {
    return this.day;
  }

  public void setDay(int day)
  {
    this.day = day;
  }

  public int getDayOfWeek()
  {
    return this.dayOfWeek;
  }

  public void setDayOfWeek(int dayOfWeek)
  {
    this.dayOfWeek = dayOfWeek;
  }

  public int getMaxDays()
  {
    return this.maxDays;
  }

  public void setMaxDays(int maxDays)
  {
    this.maxDays = maxDays;
  }

  public int getMonth()
  {
    return this.month;
  }

  public void setMonth(int month)
  {
    this.month = month;
  }

  public int getYear()
  {
    return this.year;
  }

  public void setYear(int year)
  {
    this.year = year;
  }

  public CalendarMonth getCalendarMonthAfter()
  {
    return this.calendarMonthAfter;
  }

  public void setCalendarMonthAfter(CalendarMonth calendarMonthAfter)
  {
    this.calendarMonthAfter = calendarMonthAfter;
  }

  public CalendarMonth getCalendarMonthBefore()
  {
    return this.calendarMonthBefore;
  }

  public void setCalendarMonthBefore(CalendarMonth calendarMonthBefore)
  {
    this.calendarMonthBefore = calendarMonthBefore;
  }

  public void setWeeks(int weeks) {
    this.weeks = weeks;
  }

  public int getWeeks() {
    return this.weeks;
  }

  public CalendarMonth getCalendarNow(int year, int month) {
    --month;
    Calendar calendar = new GregorianCalendar(year, month, 1);
    int day = calendar.get(5);
    int dayWeek = calendar.get(7);
    int maxDay = calendar.getActualMaximum(5);
    int weeks = calendar.getActualMaximum(4);

    CalendarMonth cm = new CalendarMonth();
    cm.setMonthYearText(new SimpleDateFormat("MMM-yyyy").format(calendar.getTime()));
    cm.setDateString(new SimpleDateFormat("d-MMM-yyyy").format(calendar.getTime()));
    cm.setDay(day);
    cm.setMonth(month + 1);
    cm.setYear(year);
    cm.setDayOfWeek(dayWeek);
    cm.setMaxDays(maxDay);
    cm.setWeeks(weeks);

    return cm;
  }

  public void setCalendar()
  {
    setCalendar("", 0, 0);
  }

  public void setCalendar(String user, int year, int month)
  {
    this.errorMessages = new Vector();

    if ((year == 0) || (month == 0)) {
      Calendar cal = new GregorianCalendar();
      year = (year == 0) ? cal.get(1) : year;
      month = (month == 0) ? cal.get(2) + 1 : month;
    }

    this.calendarMonthNow = getCalendarNow(year, month);
    this.calendarMonthBefore = getCalendarBefore(year, month);
    this.calendarMonthAfter = getCalendarAfter(year, month);

    int dayWeekNow = this.calendarMonthNow.getDayOfWeek();
    int maxDays = this.calendarMonthNow.getMaxDays();
    int lastDayPrev = this.calendarMonthBefore.getMaxDays();
    int weeks = this.calendarMonthNow.getWeeks();
    this.calendarDays = new Hashtable[weeks][7];

    int cntDay = 1;
    boolean now = false; boolean before = false; boolean after = false;
    if (dayWeekNow > 1) {
      cntDay = lastDayPrev - dayWeekNow + 2;
      before = true;
    } else {
      now = true;
    }
    int cyear = 0; int cmonth = 0; int cday = 0;
    String cmonthYearText = "";

    for (int week = 0; week < weeks; ++week)
      for (int day = 0; day < 7; ++day) {
        if (before) {
          cyear = this.calendarMonthBefore.getYear();
          cmonth = this.calendarMonthBefore.getMonth();
          cmonthYearText = this.calendarMonthBefore.getMonthYearText();
        } else if (now) {
          cyear = this.calendarMonthNow.getYear();
          cmonth = this.calendarMonthNow.getMonth();
          cmonthYearText = this.calendarMonthNow.getMonthYearText();
        } else if (after) {
          cyear = this.calendarMonthAfter.getYear();
          cmonth = this.calendarMonthAfter.getMonth();
          cmonthYearText = this.calendarMonthAfter.getMonthYearText();
        }

        this.calendarDays[week][day] = new Hashtable();
        this.calendarDays[week][day].put("day", new Integer(cntDay));
        this.calendarDays[week][day].put("month", new Integer(cmonth));
        this.calendarDays[week][day].put("year", new Integer(cyear));
        this.calendarDays[week][day].put("monthYearText", cmonthYearText);
        Collection eventList = null;
        try {
          eventList = EventData.getEvent(user, cyear, cmonth, cntDay);
          this.calendarDays[week][day].put("eventList", eventList);
        } catch (Exception e) {
          System.out.println(e.getMessage());
          this.errorMessages.addElement("eventList: " + e.getMessage());
        }
        this.calendarDays[week][day].put("now", new Boolean(now));

        Vector taskList = null;
        try {
          taskList = PlannerData.getInstance().getTaskVector(user, "", cyear, cmonth, cntDay);
        } catch (Exception e) {
          System.out.println(e.getMessage());
          this.errorMessages.addElement("taskList: " + e.getMessage());
        }
        this.calendarDays[week][day].put("taskList", taskList);
        ++cntDay;

        if ((before) && (cntDay > lastDayPrev)) {
          cntDay = 1;
          before = false;
          now = true;
        }
        if ((now) && (cntDay > maxDays)) {
          cntDay = 1;
          after = true;
          now = false;
        }
      }
  }

  public CalendarMonth getCalendarBefore(int year, int month)
  {
    if (month == 1) {
      --year;
      month = 12;
    } else {
      --month;
    }
    return getCalendarNow(year, month);
  }

  public CalendarMonth getCalendarAfter(int year, int month)
  {
    if (month == 12) {
      ++year;
      month = 1;
    } else {
      ++month;
    }
    return getCalendarNow(year, month);
  }

  public CalendarMonth getCalendarMonthNow()
  {
    return this.calendarMonthNow;
  }

  public void setCalendarMonthNow(CalendarMonth calendarMonthNow)
  {
    this.calendarMonthNow = calendarMonthNow;
  }

  public Hashtable[][] getCalendarDays()
  {
    return this.calendarDays;
  }

  public void setCalendarDays(Hashtable[][] calendarDays)
  {
    this.calendarDays = calendarDays;
  }

  public String getMonthYearText()
  {
    return this.monthYearText;
  }

  public void setMonthYearText(String monthYearText)
  {
    this.monthYearText = monthYearText;
  }

  public Vector getErrorMessages()
  {
    return this.errorMessages;
  }
}