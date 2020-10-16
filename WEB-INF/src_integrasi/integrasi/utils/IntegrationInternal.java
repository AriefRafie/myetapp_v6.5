package integrasi.utils;

import java.util.Hashtable;
import java.util.Vector;

import ekptg.model.entities.Tblrujdokumen;
import integrasi.ws.etanah.ppt.ETanahPPTManager;
import lebah.db.Db;

public interface IntegrationInternal {
	
	public void hantar(ETanahPPTManager pptManager
		,Hashtable<String,String> permohonan
		,Vector<Tblrujdokumen> vecDok
		,String idPengguna, Db db);
	public void setHakmiliks(Vector<Hashtable<String,String>> vec);

//	public boolean kemaskiniPermohonan(String idPermohonan,String transactionID,Permohonan permohonan) throws Exception;
//	public TanahApplicationResponse semakanPermohonan(String idPermohonan,String transactionID,  Permohonan permohonan) throws Exception;

}
