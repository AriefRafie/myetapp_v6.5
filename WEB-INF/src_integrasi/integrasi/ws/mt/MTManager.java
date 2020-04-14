package integrasi.ws.mt;

import java.rmi.RemoteException;



public class MTManager {
	
	//url sit 
	//private static String url = "https://eip.kehakiman.gov.my/sit/etapp?wsdl";
	private static String url = "http://eip.kehakiman.gov.my/services/etapp?wsdl"; //url live
	private static String source = "ETP";
	//private static String userName = "etpapi01"; //sit
	//private static String password = "etpapi01"; //sit
	private static String userName = "etappapiprod"; //live
	private static String password = "et@pp@p!pr0d"; //live
	private static String eventName = "MyeTaPPApplication";
	private static String idKadBiru = null;

	public static String sendMaklumat2Court(String noPetisyen,
			String namaSimati, String namaSimatiLain, String noKPSimatiBaru,
			String noKPSimatiLama, String noKPSimatiLain, String tarikhMati,
			String namaPemohon, String noKPPemohon, String hubSimatiPemohon,
			String tarikhJanaBorangB, String tarikhHantarBorangB,
			String kodPejabat, String namaPejabat, String idnegeri,
			String jeniskp, String applicationType, String transactionID) {
		
		String relationship = "";
		String reldetails = "";
		String msg = "";		
		
		try {
						
			if (hubSimatiPemohon.equals("ANAK LELAKI") || hubSimatiPemohon.equals("ANAK PEREMPUAN")) {
				relationship = "Children";
			} else if (hubSimatiPemohon.equals("SUAMI")) {
				relationship = "Husband";
			} else if (hubSimatiPemohon.equals("ISTERI") || hubSimatiPemohon.equals("ISTER(X)")) {
				relationship = "Wife";
			} else if (hubSimatiPemohon.equals("BAPA")) {
				relationship = "Father";
			} else if (hubSimatiPemohon.equals("IBU")) {
				relationship = "Mother";
			} else if (hubSimatiPemohon.equals("SAUDARA LELAKI SEBAPA")
					|| hubSimatiPemohon.equals("SAUDARA LELAKI SEIBU")
					|| hubSimatiPemohon.equals("SAUDARA LELAKI SEIBU SEBAPA")
					|| hubSimatiPemohon.equals("SAUDARA PEREMPUAN SEBAPA")
					|| hubSimatiPemohon.equals("SAUDARA PEREMPUAN SEIBU")
					|| hubSimatiPemohon.equals("SAUDARA PEREMPUAN SEIBU SEBAPA")) {

				relationship = "Sibling";
			} else if (hubSimatiPemohon.equals("CUCU LELAKI")) {
				relationship = "Grand Children";
			} else {
				relationship = "Others";
				reldetails = "Others";
			}
			
			//DATA
			NewType data = new NewType();
			data.setPetitionNo((noPetisyen != null ? noPetisyen : ""));
			data.setDName((namaSimati != null ? namaSimati : ""));
			data.setDNameOther((namaSimatiLain != null ? namaSimatiLain : ""));
			data.setDNewIC((noKPSimatiBaru != null ? noKPSimatiBaru : ""));
			data.setDOldIC((noKPSimatiLama != null ? noKPSimatiLama : ""));
			data.setDOtherID((noKPSimatiLain != null ? noKPSimatiLain : ""));
			data.setDDate((tarikhMati != null ? tarikhMati : ""));
			CardType_type1 card1 = new CardType_type1("BC", true);
			data.setCardType(card1);
			ApplicationType_type1 appType1 = new ApplicationType_type1(applicationType, true);
			data.setApplicationType(appType1);
//			ApplicationType_type1 appType1 = new ApplicationType_type1("G", true);
//			data.setApplicationType(appType1);
			data.setApplicationDate((tarikhHantarBorangB != null ? tarikhHantarBorangB : ""));
			data.setApplicantName((namaPemohon != null ? namaPemohon : ""));
			data.setApplicantIC((noKPPemohon != null ? noKPPemohon : ""));
			data.setApplicantRelationship((relationship != null ? relationship : ""));
			data.setOfficeName((kodPejabat != null ? kodPejabat : ""));
			data.setApplicantRelOther((reldetails != null ? reldetails : ""));
//			data.setBlueCardID("16061039");
//			data.setGrantDate("2016-09-29T00:00:00.00");
//			data.setGrantName("ANEETA BINTI HASHIM");
			//data.setOfficeName("");
			data.setDOtherIDType((jeniskp != null ? jeniskp : ""));
			
		SubmitApplicationResponse response = submitToMT(data, transactionID);			
			if (response != null) {
				msg = response.getCode() + "," + response.getDescription() + " , " + response.getDetail();
				if (response.getData() != null) {
					if (response.getData().getBlueCardID() != null) {
						idKadBiru = response.getData().getBlueCardID();
					}
				}
			}				
			
		} catch (Exception ex) {
			ex.printStackTrace();
			msg = " " + "," + ex.getMessage();
		}
	
		return msg;		
	}
	
	public static String sendMaklumat2CourtPetioner(String noPetisyen,
			String namaSimati, String namaSimatiLain, String noKPSimatiBaru,
			String noKPSimatiLama, String noKPSimatiLain, String tarikhMati,
			String namaPemohon, String noKPPemohon, String hubSimatiPemohon,
			String tarikhJanaBorangB, String tarikhHantarBorangB,
			String kodPejabat, String namaPejabat, String idnegeri,
			String jeniskp, String applicationType, String bluecardId, String transactionID) {
		
		String msg = "";
		String relationship = "";
		String reldetails = "";
		
		try {

			if (hubSimatiPemohon.equals("ANAK LELAKI") || hubSimatiPemohon.equals("ANAK PEREMPUAN")) {
				relationship = "Children";
			} else if (hubSimatiPemohon.equals("SUAMI")) {
				relationship = "Husband";
			} else if (hubSimatiPemohon.equals("ISTERI") || hubSimatiPemohon.equals("ISTER(X)")) {
				relationship = "Wife";
			} else if (hubSimatiPemohon.equals("BAPA")) {
				relationship = "Father";
			} else if (hubSimatiPemohon.equals("IBU")) {
				relationship = "Mother";
			} else if (hubSimatiPemohon.equals("SAUDARA LELAKI SEBAPA")
					|| hubSimatiPemohon.equals("SAUDARA LELAKI SEIBU")
					|| hubSimatiPemohon.equals("SAUDARA LELAKI SEIBU SEBAPA")
					|| hubSimatiPemohon.equals("SAUDARA PEREMPUAN SEBAPA")
					|| hubSimatiPemohon.equals("SAUDARA PEREMPUAN SEIBU")
					|| hubSimatiPemohon.equals("SAUDARA PEREMPUAN SEIBU SEBAPA")) {

				relationship = "Sibling";
			} else if (hubSimatiPemohon.equals("CUCU LELAKI")) {
				relationship = "Grand Children";
			} else {
				relationship = "Others";
				reldetails = "Others";
			}
			
			//DATA
			NewType data = new NewType();
			data.setPetitionNo((noPetisyen != null ? noPetisyen : ""));
			data.setDName((namaSimati != null ? namaSimati : ""));
			data.setDNameOther((namaSimatiLain != null ? namaSimatiLain : ""));
			data.setDNewIC((noKPSimatiBaru != null ? noKPSimatiBaru : ""));
			data.setDOldIC((noKPSimatiLama != null ? noKPSimatiLama : ""));
			data.setDOtherID((noKPSimatiLain != null ? noKPSimatiLain : ""));
			data.setDDate((tarikhMati != null ? tarikhMati : ""));
			CardType_type1 card1 = new CardType_type1("BC", true);
			data.setCardType(card1);
			ApplicationType_type1 appType1 = new ApplicationType_type1(applicationType, true);
			data.setApplicationType(appType1);
			data.setApplicationDate((tarikhHantarBorangB != null ? tarikhHantarBorangB : ""));
			data.setApplicantName((namaPemohon != null ? namaPemohon : ""));
			data.setApplicantIC((noKPPemohon != null ? noKPPemohon : ""));
			data.setApplicantRelationship((relationship != null ? relationship : ""));
			data.setOfficeName((kodPejabat != null ? kodPejabat : ""));
			data.setApplicantRelOther((reldetails != null ? reldetails : ""));
			data.setBlueCardID((bluecardId != null ? bluecardId : ""));
			data.setDOtherIDType((jeniskp != null ? jeniskp : "")); 
		
			SubmitApplicationResponse response = submitToMT(data, transactionID);			
			if (response != null) {
				msg = response.getCode() + "," + response.getDescription() + " , " + response.getDetail();
				if (response.getData() != null) {
					if (response.getData().getBlueCardID() != null) {
						idKadBiru = response.getData().getBlueCardID();
					}
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			msg = " " + "," + ex.getMessage();
		}

		return msg;
	}
	
	public static String sendMaklumatPerintah2Court(String noPetisyen,
			String namaSimati, String namaSimatiLain, String noKPSimatiBaru,
			String noKPSimatiLama, String noKPSimatiLain, String tarikhMati,
			String namaPemohon, String noKPPemohon, String hubSimatiPemohon,
			String tarikhPerintah, String dateHantarNotisPerintah,
			String kodPejabat, String namaPejabat, String idnegeri,
			String jeniskp, String applicationType, String grandName,
			String blueCardId, String transactionID) {
		
		String msg = "";
		
		try {
			
			//DATA
			NewType data = new NewType();
			data.setPetitionNo((noPetisyen != null ? noPetisyen : ""));
			data.setDName((namaSimati != null ? namaSimati : ""));
			data.setDNameOther((namaSimatiLain != null ? namaSimatiLain : ""));
			data.setDNewIC((noKPSimatiBaru != null ? noKPSimatiBaru : ""));
			data.setDOldIC((noKPSimatiLama != null ? noKPSimatiLama : ""));
			data.setDOtherIDType((jeniskp != null ? jeniskp : ""));
			data.setDOtherID((noKPSimatiLain != null ? noKPSimatiLain : ""));
			data.setDDate((tarikhMati != null ? tarikhMati : ""));
			CardType_type1 card1 = new CardType_type1("BC", true);
			data.setCardType(card1);
			ApplicationType_type1 appType1 = new ApplicationType_type1("G", true);
			data.setApplicationType(appType1);
			data.setApplicationDate((dateHantarNotisPerintah != null ? dateHantarNotisPerintah : ""));
			data.setApplicantName((namaPemohon != null ? namaPemohon : ""));
			data.setApplicantIC((noKPPemohon != null ? noKPPemohon : ""));
			data.setApplicantRelationship("");
			data.setApplicantRelOther("");
			data.setBlueCardID((blueCardId != null ? blueCardId : ""));
//			data.setBlueCardID("16061039");
			data.setGrantDate((tarikhPerintah != null ? tarikhPerintah : ""));
			data.setGrantName((grandName != null ? grandName : ""));
			data.setOfficeName((kodPejabat != null ? kodPejabat : ""));
			data.setOfficeAddress("");
			
			
						
			
			SubmitApplicationResponse response = submitToMT(data, transactionID);			
			if (response != null) {
				msg = response.getCode() + "," + response.getDescription() + " , " + response.getDetail();
				if (response.getData() != null) {
					if (response.getData().getBlueCardID() != null) {
						idKadBiru = response.getData().getBlueCardID();
					}
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			msg = " " + "," + ex.getMessage();
		}
	
		return msg;
	}

	private static SubmitApplicationResponse submitToMT(NewType data, String transactionID) throws RemoteException {
				
		EtappStub stub;
		SubmitApplicationResponse response = new SubmitApplicationResponse();
		
		stub = new EtappStub(url);
		SubmitApplicationRequest request = new SubmitApplicationRequest();
			
		Source_type1 source1 = new Source_type1(source, true);
		request.setSource(source1);
		request.setTransactionID(transactionID);
		request.setUsername(userName);
		request.setPassword(password);
		EventName_type1 event1 = new EventName_type1(eventName, true);
		request.setEventName(event1);
		request.setData(data);
		
		response = stub.submitApplication(request);
		
		return response;
	}

	public static String getIdKadBiru() {
		return idKadBiru;
	}

	public static void setIdKadBiru(String idKadBiru) {
		MTManager.idKadBiru = idKadBiru;
	}
}
