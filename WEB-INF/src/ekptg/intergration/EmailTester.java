package ekptg.intergration;

public class EmailTester {
	public static void main(String args []){
		XEkptgEmailSender email = XEkptgEmailSender.getInstance();
		email.FROM ="roslizakaria@gmail.com";
		email.SUBJECT="PANGGILAN MESYUARAT(sistem under testing please ignore) ";
		email.MESSAGE = setMessageTable()+"<b>"+setParaGraph();
		email.MULTIPLE_RECIEPIENT = new String[2];
		email.MULTIPLE_RECIEPIENT[0]="rosli@hla-group.com";
		email.MULTIPLE_RECIEPIENT[1]="razman@hla-group.com";
		
		email.sendEmail();
	}
	
	public static String setMessageTable(){
		StringBuffer bff = new StringBuffer();
		bff.append("<table border='0'>");
		bff.append("<tr>");
		bff.append("<td><b>Tarikh :</td>");
		bff.append("<td><b></b></td>");
		bff.append("</tr>");
		bff.append("<tr>");
		bff.append("<td><b>Masa :</td>");
		bff.append("<td><b></b></td>");
		bff.append("</tr>");
		bff.append("<tr>");
		bff.append("<td><b>Tempat :</td>");
		bff.append("<td><b></b></td>");
		bff.append("</tr>");
		bff.append("</table>");
		
		return bff.toString();
	}
	public static String setParaGraph(){
		StringBuffer bff = new StringBuffer();
		bff.append("<p>");
		bff.append("Sehubungan itu,pihak Y.Bhg Dato'/Tuan/Puan dijemput untuk menghadiri mesyuarat ini bagi melancarkan lagi perbincangan.");
		bff.append(" Segala kerjasama dan perhatian Y.Bhg Dato'/Tuan/Puan berhubung perkara ini amatlah di hargai.");
		bff.append("<br><br>Sekian, terima kasih");
		bff.append("</p>");
		return bff.toString();
	}
}
