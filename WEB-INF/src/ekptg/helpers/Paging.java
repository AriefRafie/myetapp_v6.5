package ekptg.helpers;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

 

public class Paging

{

  private HttpSession session;
  private boolean isFirstPage;
  private boolean isLastPage;
  private int LIST_ROWS = 10;
  private int currentPage;
  private int totalPages;
  private int topNumber;
  private int lastNumber;

  public Paging(HttpSession session, List list, int rows)
  {
    this.session = session;
    this.LIST_ROWS = rows;
    this.totalPages = prepareList(list);
   }
  
  public Paging(HttpSession session, Vector list, int rows)
 {
   this.session = session;
   this.LIST_ROWS = rows;
   this.totalPages = prepareList(list);
  }

  public Paging(HttpSession session, int rows) {
    this.session = session;
    this.LIST_ROWS = rows;
  }

  public Paging(HttpSession session) {
    this.session = session;
  }

   public int prepareList(Vector list) {
		int pages = list.size() / this.LIST_ROWS;
	    double leftover = list.size() % this.LIST_ROWS;
	    if (leftover > 0D) ++pages;
	    this.session.setAttribute("pagingList", list);
	    this.totalPages = pages;
	    return pages;
  }

   public int prepareList(List list) {
		int pages = list.size() / this.LIST_ROWS;
	    double leftover = list.size() % this.LIST_ROWS;
	    if (leftover > 0D) ++pages;
	    this.session.setAttribute("pagingList", list);
	    this.totalPages = pages;
	    return pages;
 }

  public int prepareList(Vector list, int rows) {
    this.LIST_ROWS = rows;
    int pages = list.size() / this.LIST_ROWS;
    double leftover = list.size() % this.LIST_ROWS;
    if (leftover > 0D) ++pages;
    this.session.setAttribute("pagingList", list);
    return pages;

  }

 

  public Vector getPage(int page) {
    this.currentPage = page;
    Vector list = (Vector)this.session.getAttribute("pagingList");
    return getPage(page, this.LIST_ROWS, list);
  }

 

  public int getCurrentPage() {
    return this.currentPage;
  }

 

  public Vector goNextPage() {
    return getPage(++this.currentPage);
  }

 

  public Vector goPreviousPage() {
    return getPage(--this.currentPage);
  }

 

  public Vector goFirstPage() {
    return getPage(1);
  }

 

  public Vector goLastPage() {
    return getPage(this.totalPages);
  }

 

  public boolean isLastPage() {
    return this.isLastPage;
  }

 

  public boolean isFirstPage() {
    return this.isFirstPage;
  }

 

  public int getTopNumber() {
    return this.topNumber;
  }

 

  public int getLastNumber() {
    return this.lastNumber;
  }

 

  public int getTotalPages() {
    return this.totalPages;
  }

 

  Vector getPage(int page, int size, Vector list) {

    int elementstart = (page - 1) * size;
    int elementlast = 0;
    if (page * size < list.size()) {
      elementlast = page * size - 1;
      this.isLastPage = false;
    } else {
      elementlast = list.size() - 1;
      this.isLastPage = true;
    }

    if (page == 1) {
      this.isFirstPage = true;
    }
    else
      this.isFirstPage = false;
      Vector v = new Vector();
      for (int i = elementstart; i < elementlast + 1; ++i)
      v.addElement(list.elementAt(i));
      this.topNumber = elementstart;
      this.lastNumber = elementlast;
      return v;
  }

}

