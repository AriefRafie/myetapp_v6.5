/**
 * 
 */
package ekptg.view.ppk;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.servlets.IServlet2;
import EDU.oswego.cs.dl.util.concurrent.misc.Fraction;
import ekptg.model.ppk.FrmPerintahSek8Data;

/**
 * 
 * 
 */
public class FrmPerintahSek8Check implements IServlet2 {
	
	FrmPerintahSek8Data logic = new FrmPerintahSek8Data();

	public void doService(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String contextPath = request.getContextPath();

		String submit = request.getParameter("command");
		int index = 0;
		String indexString = request.getParameter("index");
		if (indexString != null){
			if (indexString.trim().length() > 0){
				index = Integer.parseInt(indexString) - 1;
			}
		}
		String chkWarisHilang = request.getParameter("chkWarisHilang");
		
		Vector list = new Vector();
		list.clear();
		
		if ("calculateTotal".equals(submit)) {
			
			String ids[] = request.getParameterValues("idspentadbir");
			String BA[] = request.getParameterValues("txtBA");
			String BB[] = request.getParameterValues("txtBB");
			String BAHilang = request.getParameter("txtBAHilang");
			String BBHilang = request.getParameter("txtBBHilang");
			String bahagianSimatiAtas = request.getParameter("bahagianSimatiAtas");
			String bahagianSimatiBawah = request.getParameter("bahagianSimatiBawah");
			String idJenisTanah = request.getParameter("idJenisTanah");
			Fraction f = new Fraction(0,1);
			
			
			
				/*Ini untuk check samaada tanah adalah jenis GSA atau tidak*/
				 if (ids != null) {
					for (int i = 0; i < ids.length; i++) {
						long pembawah = 1;
											
						if (Long.parseLong(BB[i]) > 0){
							pembawah = Long.parseLong(BB[i]);
							
						}
							Fraction f2 = new Fraction(Long.parseLong(BA[i]), pembawah);
							f = f.plus(f2);
					}
				}
			
			
			if (chkWarisHilang != null){
				long pembawahHilang = 1;
				if (Long.parseLong(BBHilang) > 0){
					pembawahHilang = Long.parseLong(BBHilang);
				}
				Fraction f2 = new Fraction(Long.parseLong(BAHilang), pembawahHilang);
				f = f.plus(f2);
			}
			
			//modified by peje on 11042011
//			if (ids.length == 1){
//				out.println("<script>setPecahan('1','1','1','1');</script>");
//				out.println("<script>setTotalPecahan('1','1');</script>");
//			} else {				
//				out.println("<script>setTotalPecahan('" + f.numerator() + "','" + f.denominator() + "');</script>");
//			}
			out.println("<script>setTotalPecahan('" + f.numerator() + "','" + f.denominator() + "');</script>");
			
			
			if (f.asDouble() > 1){
				out.println("Jumlah pembahagian telah melebihi dari 1");
			} else if (f.asDouble() < 1){
				out.println("Jumlah pembahagian kurang dari 1");
			}
		
		} else if ("pembahagianSepara".equals(submit)) {
			
			String ids[] = request.getParameterValues("idspentadbir");

			if (ids != null) {
				
				int count = ids.length;
				if (chkWarisHilang != null){
					count = count + 1;
					out.println("<script>setPecahanHilang('1','" + count + "');</script>");
				}
				
				for (int i = 0; i < ids.length; i++) {
					
					//SET PEMBAHAGIAN SEPARA
					out.println("<script>setPecahan('1','" + count + "','" + i + "','" + ids.length + "');</script>");
				}
				
				out.println("<script>setTotalPecahan('" + count + "','" + count + "');</script>");
			}
			
			out.println("<script>document.getElementById('calculateTotal_result').innerHTML='';</script>");
			
		} else if ("kosongkanPembahagian".equals(submit)) {
			
			String ids[] = request.getParameterValues("idspentadbir");

			if (ids != null) {
				
				int count = ids.length;
				if (chkWarisHilang != null){
					count = count + 1;
					out.println("<script>setPecahanHilang('0','0');</script>");
				}
				
				for (int i = 0; i < ids.length; i++) {
					
					//SET PEMBAHAGIAN SEPARA
					out.println("<script>setPecahan('0','0','" + i + "','" + ids.length + "');</script>");
				}
				
				out.println("<script>setTotalPecahan('0','1');</script>");
			}
			
			out.println("<script>document.getElementById('calculateTotal_result').innerHTML='';</script>");
		
		} else if ("samakanPembawah".equals(submit)){
			
			String ids[] = request.getParameterValues("idspentadbir");
			String BA[] = request.getParameterValues("txtBA");
			String BB[] = request.getParameterValues("txtBB");
			String BAHilang = request.getParameter("txtBAHilang");
			String BBHilang = request.getParameter("txtBBHilang");
			
			if (ids != null) {
				
				for (int i = 0; i < ids.length; i++) {					
					Pecahan pchn = new Pecahan(Long.parseLong(BA[i]), Long.parseLong(BB[i]));
					list = pchn.addToList(list);
				}
				
				if (chkWarisHilang != null){
					Pecahan pchn = new Pecahan(Long.parseLong(BAHilang), Long.parseLong(BBHilang));
					list = pchn.addToList(list);
					
					Pecahan pchnHilang = (Pecahan) list.lastElement();
					out.println("<script>setPecahanHilang('" + pchnHilang.getPengangka() + "','" + pchnHilang.getPenyebut() + "');</script>");
				}
				
				for (int j = 0; j < ids.length; j++) {
					Pecahan pchn = (Pecahan) list.get(j);
					out.println("<script>setPecahan('" + pchn.getPengangka() + "','" + pchn.getPenyebut() + "','" + j + "','" + ids.length + "');</script>");
				}
			}
		} else if ("checkPembahagianGSA".equals(submit)){
			int count = Integer.valueOf(request.getParameter("count"));
			String ids[] = request.getParameterValues("idspentadbir");
			String BA[] = request.getParameterValues("txtBA");
			String BB[] = request.getParameterValues("txtBB");
			
			int j = 0;
			
			if (ids != null) {
				for (int i = 0; i < ids.length; i++) {
					if (Long.parseLong(BA[i]) > 0){
						j++;
					}
					if (j > 2){
						
						out.println("<script>popupMsg('Pembahagian Tanah Jenis GSA dihadkan kepada 2 waris sahaja.');</script>");
						out.println("<script>setPecahan('0','0','" + (count - 1) + "','" + ids.length + "');</script>");
						break;
					}
				}
			}
			
		} else if ("pembahagianFaraid".equals(submit)) {
			Db db = null;
			String sqlOB = "";
			
			String idPermohonan = request.getParameter("idPermohonan");
			String idSimati = request.getParameter("idSimati");
			String idPermohonanSimati = request.getParameter("idPermohonanSimati");
			
			String ids[] = request.getParameterValues("idspentadbir");
			
			int totalBA = 0;
			int totalBB = 1;
			
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				logic.generateFaraid(idPermohonan, idSimati, idPermohonanSimati);
				
				//GET ALL WARIS
				sqlOB = "SELECT B1.ID_OB, B1.BA_FARAID, B1.BB_FARAID, B.UMUR, B.STATUS_OB " +
						" FROM TBLPPKOB B1,TBLPPKOBPERMOHONAN B " +
						" WHERE B1.ID_OB = B.ID_OB " +
						" AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
								" AND B.STATUS_HIDUP = 0 AND B.ID_TARAFKPTG = 1 " +
								" AND B.ID_SIMATI = '" + idSimati + "' " +
								" AND B1.ID_PERMOHONANSIMATI IN (" + logic.getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ")";

				ResultSet rs = stmt.executeQuery(sqlOB);
				
				while (rs.next()){					
					if (ids != null) {					
						for (int i = 0; i < ids.length; i++) {
							if (rs.getString("ID_OB").equals(ids[i])){
								out.println("<script>setPecahan('" + rs.getString("BA_FARAID") + "','" + rs.getString("BB_FARAID") + "','" + i + "','" + ids.length + "');</script>");
								
								totalBA = totalBA + Integer.valueOf(rs.getString("BA_FARAID"));
								totalBB = Integer.valueOf(rs.getString("BB_FARAID"));
							}
						}
					}
				}
				
				out.println("<script>setTotalPecahan('" + totalBA + "','" + totalBB + "');</script>");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
			
			
	}
	
	static private long gcd (long totalPengangka, long totalPenyebut) {
		  
		  if (totalPenyebut==0) 
			  return totalPengangka;
		   return gcd(totalPenyebut,totalPengangka%totalPenyebut);
	  }
}

class Pecahan {

	private long pengangka;
	private long penyebut;

	public void setPengangka(long a) {
		this.pengangka = a;
	}

	public long getPengangka() {
		return pengangka;
	}

	public void setPenyebut(long b) {
		this.penyebut = b;
	}

	public long getPenyebut() {
		return penyebut;
	}

	public Pecahan(long a, long b) {
		if (b == 0){
			b = 1;
		}
		setPengangka(a);
		setPenyebut(b);
	}
	
	static private long gcd (long a, long b) {
		  
		  if (b==0) 
			  return a;
		   return gcd(b,a%b);
	  }
	
	public Vector addToList(Vector list){
		
		if (list.size() == 0){
			list.add(this);
			
		} else {
			long pembawahBaru = 1;
			long pembawahLama = 1;
			
			boolean update = false;
			
			
			Pecahan pchn0 = (Pecahan) list.get(0);
				if (pchn0.getPenyebut() != this.getPenyebut()){
					update = true;
					pembawahBaru = this.getPenyebut();
					pembawahLama = pchn0.getPenyebut();
				}
				
			if (update){
				
				//update pecahan dlm list
				for (int i = 0; i < list.size(); i++){
					Pecahan pchn1 = (Pecahan) list.get(i);
					pchn1.setPengangka(pchn1.getPengangka() * pembawahBaru);
					pchn1.setPenyebut(pchn1.getPenyebut() * pembawahBaru);
					list.set(i, pchn1);
				}
				
				//update pecahan yang terbaru
				this.setPengangka(this.getPengangka() * pembawahLama);
				this.setPenyebut(this.getPenyebut() * pembawahLama);					
			}
			
			list.add(this);
			
			//get gcd
			long temp = this.getPenyebut();
			for (int j = 0; j < list.size(); j++) {
				Pecahan pchn2 = (Pecahan) list.get(j);
				if (pchn2.getPengangka() > 0){
					temp = gcd(temp,pchn2.getPengangka());
				}
			}

			//kecikkan
			if (temp == 0){
				temp = 1;
			}
			for (int k = 0; k < list.size(); k++) {
				Pecahan pchn3 = (Pecahan) list.get(k);
				pchn3.setPengangka(pchn3.getPengangka() / temp);
				pchn3.setPenyebut(pchn3.getPenyebut() / temp);
				list.set(k, pchn3);
			}
		}

		return list;
	}
	
	
	
}

