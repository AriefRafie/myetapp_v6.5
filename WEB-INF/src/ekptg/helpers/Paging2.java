package ekptg.helpers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class Paging2 {
	private HttpSession session;
	  private boolean isFirstPage;
	  private boolean isLastPage;
	  private int LIST_ROWS = 10;
	  private int currentPage;
	  private int totalPages;
	  private int topNumber;
	  private int lastNumber;

	 
	  public Paging2(HttpSession session, List list, int rows)
	 {
	   this.session = session;
	   this.LIST_ROWS = rows;
	   this.totalPages = prepareList(list);

	  }

	  public Paging2(HttpSession session, int rows) {
	    this.session = session;
	    this.LIST_ROWS = rows;
	  }

	  public Paging2(HttpSession session) {
	    this.session = session;
	  }

	   public int prepareList(List list) {
			int pages = list.size() / this.LIST_ROWS;
		    double leftover = list.size() % this.LIST_ROWS;
		    if (leftover > 0D) ++pages;
		    this.session.setAttribute("pagingList", list);
		    this.totalPages = pages;
		    return pages;
	  }

	 

	  public int prepareList(List list, int rows) {
	    this.LIST_ROWS = rows;
	    int pages = list.size() / this.LIST_ROWS;
	    double leftover = list.size() % this.LIST_ROWS;
	    if (leftover > 0D) ++pages;
	    this.session.setAttribute("pagingList", list);
	    return pages;

	  }

	 

	  public List getPage(int page) {
	    this.currentPage = page;
	    List list = (List)this.session.getAttribute("pagingList");
	    return getPage(page, this.LIST_ROWS, list);
	  }

	 

	  public int getCurrentPage() {
	    return this.currentPage;
	  }

	 

	  public List goNextPage() {
	    return getPage(++this.currentPage);
	  }

	 

	  public List goPreviousPage() {
	    return getPage(--this.currentPage);
	  }

	 

	  public List goFirstPage() {
	    return getPage(1);
	  }

	 

	  public List goLastPage() {
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

	 

	  List getPage(int page, int size, List list) {

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
	      List v = new ArrayList();
	      for (int i = elementstart; i < elementlast + 1; ++i)
	      v.add(list.get(i));
	      this.topNumber = elementstart;
	      this.lastNumber = elementlast;
	      return v;
	  }
}
