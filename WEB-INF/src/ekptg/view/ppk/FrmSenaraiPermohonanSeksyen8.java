/**
 * 
 */

/*
package ekptg.view.ppk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.ppk.FrmSenaraiPermohonanSeksyen8Data;
*/
package ekptg.view.ppk;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.RazTemplete;
import ekptg.model.ppk.MyInfoPPKModel;

/*
 * author : RAZMAN MD ZAINAL
 * date start : 02/06/2018 9.00 PM
 * description : 
 * Class controllwe untuk myinfo PPK versi baru. Merupakan modul/skrin carian unmum untuk fail2 ppk.
 * Tujuan untuk menggantikan myinfo yg sedia ada yang sangat melembabkan kerana ia menyumbang masalah performance system
 * 
 * Additional info : 1st pilot modul/skrin menggunakan kaedah ajax + RazTemplete, bertujuan untuk preparation myetapp v8
 * 
 * **TERM [STANDARD] : merujukan, pekara2 standard/perlu di guna pakai untuk every modul/skrin yg ingin mengunakan kaedah ajax + RazTemplete
 */



/*
 * instruction
 * 
 * 1-perlu create model dalam folder model, untuk manipulating data or setupSkrin
 * 2-Standardkan nama module 'nama controller'+'Model'
 */

//FrmSenaraiPermohonanSeksyen8
//reference contoh modul/skrin menggunakan kaedah Ajax menggunakan RazTemplete
public class FrmSenaraiPermohonanSeksyen8 extends AjaxBasedModule {
	/*[STANDARD]
	 * bertujuan untuk get apa2 setting yang related
	 */
	//public ResourceBundle rbRaz = ResourceBundle.getBundle("RazTemplete");
	
	/*[STANDARD]
	>>wajib guna kaedah logger untuk println dalam console
	>>System.out.println adalah diharamkan sama sekali
	*/
	public Logger myLogger = Logger.getLogger(FrmSenaraiPermohonanSeksyen8.class);
	
	/*[STANDARD] call model class*/
	public MyInfoPPKModel MP = new MyInfoPPKModel();
	
	
	
	/*[STANDARD] extend RazTemplete class*/
	public RazTemplete RT = new RazTemplete();
	
	// [STANDARD] bila guna ajax mesti defaultkan skrin index as based
	//every modul/skrin kena ada forder app tersendiri
	//nama folder mestilah releven dan bersesuaian
	String skrin_name = "app/ppk/MyInfoPPK/index.jsp";	
	String modul = "PPK";
	
	public String doTemplate2() throws Exception {	
		//open >> [STANDARD] variable declared
		HttpSession session = this.request.getSession();//session akan slalu diguna pakai	
		RT.setEnvironmentRT(engine, context, request, response, session, modul); //perlu call saja untuk  kita inherite class model	
		MP.setMyInfoPPKModel(engine, context, request, response, session, modul);//perlu set & call untuk  kita inherite class model		
		RT.startTime();//start waktu untuk kira masa request		
		
		
		
		String cmdajax = RT.getLastParam("command");// untuk command paramter, perlu pakai method getLastParam
		String actionajax = getParam("actionajax");//reserve word bila kita guna paging	
		String skrinName = getParam("skrinName");
		String namaList = getParam("namaList");
		String div = getParam("div");
		//close >> [STANDARD] variable declared	
		
		myLogger.info("cmdajax : "+cmdajax+" actionajax : "+actionajax);
		myLogger.info("skrinName : "+skrinName);
		myLogger.info("div : "+div);
		myLogger.info("namaList : "+namaList);
				
		
        // [STANDARD] initialization mana2 object seperti List/Hash or apa2 object global
		// Jika ada error, segala detail error akan diassign kan pada variable ini
		String errorMesej = "";
		//String currentSQL = "";
				
		// [STANDARD] method ini penting untuk initialize all context.put
		// initialize ini penting, programmer selalu terlupa
		RT.defaultContextPutRT();//default dalam razTempelete //yang ni xperlu usik, call sahaja
		MP.defaultContextPut();//default dalam Skrin ini
				
		//open >> [STANDARD] db connection declared
		//bertujuan untuk menyatukan satu open db connection merujuk pada sesuatu cmdajax
		//kadang dalam sesuatu commad ada multiple request & transactionajax, jika ada terlalu bnyk open/close DB connection, akan melembabkan performance system
		Db dbMain = null;
		Connection conn = null;
		try {
			dbMain = new Db();			
			conn = dbMain.getConnection();
			conn.setAutoCommit(false);
			
			//open - programmer part
			
			//cmdajax bila carian dibuat
			if(cmdajax.equals("showSenarai"))
			{				
				//1st ajax call dari skrin index, untuk bukak skrin senarai tugasan
				//by default, skrin ni mmg tiada senarai				
				//kalo perlukan senarai appear with data, kita perlu send senarai di sini
				skrin_name = "app/ppk/MyInfoPPK/senarai.jsp";	
			}
			else if(cmdajax.equals("carianSenarai"))
			{
				/*
				//setting html table from Raztemplete
				//setting untuk standard list, with cache & paging
				String htmlSenaraiFail = RT.RThtmlListRekod( 
						actionajax,// actionajax dari request dalam paging
						cmdajax, // related cmdajax, get dlu..
						skrinName, // skrinName
						MP.querySenaraiFail(dbMain,skrinName), //method query
						MP.listColumnForSenaraiFail(skrinName,namaList), //column to display
						namaList, // nama List
						div,
						"TARIKH_MOHON", // senarai nama column untuk  kita sort kan
						"DATE", // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
						"DESC", // jenis sorting mengikut column
						1000, // set max row dispay, advice terperlu banyak2.. xde sapa nak scroll bayak2.. max letak 1K
						dbMain);
				
				context.put("htmlSenaraiFail", htmlSenaraiFail);
				skrin_name = "app/ppk/MyInfoPPK/senarai2.jsp";
				*/
				
				
				
				
				//open >> ni method kita nak return list, jika perlu customize
				List RTListRekodWithCache = RT.RTListRekodWithCache( 
						MP.querySenaraiFail(dbMain,skrinName), 
						"myinfoppk",
						//namaList, // nama list
						div, // div id for list
						//xperlu sort, lepas search biar user sort sendiri
						"TARIKH_MOHON,NO_FAIL",//column name to sort *JGN ADA SPACE!!//boleh buat multiple colum serentak
						"DATE,VARCHAR2",//data type colum to sort VARCHAR2/NUMBER/DATE *JGN ADA SPACE!!
						"DESC,ASC",//jenis sort ASC/DESC *JGN ADA SPACE!!
						"dd/MM/yyyy,-",//kalo ada column date, perlu masukan format date bedasarakan query
						10000, // bila melibatkan query myinfo or carian yg banyak, wajid letak LIMIT row nul
						"myinfoppk",
						dbMain);
				//call method paging jika perlu
						
						
				context.put("listSenaraiFail", RTListRekodWithCache);
				skrin_name = "app/ppk/MyInfoPPK/senarai.jsp";
				//close >> ni method kita nak return list, jika perlu customize
				
				
			}
			else
			{
				//*** AMARAN KERAS!! 
				//*** UNTUK MANA2 CONTROLLER, SEBETULNYA DIASAAT CONTROLLER DI BUKA TANPA COMMAND, JANGAN BUAT APA2 QUERY ATAU TRANSAKSI DIDALAM 'ELSE'
				//*** QUERY ATAU TRANSAKSI BOLEH DIBUAT SELEPAS 1ST COMMAND AJAX CALL DI PANGGIL
				//*** CONTOH NYA DALAM CASE INI.. 1ST SY HANTAR SATU AJAX CALL 'onStart' DALAM 1ST SKRIN IAITU INDEX.JSP
				//*** THEN, BARU KITA BLEH CALL OR HANTAR MANA2 COMMAND UNTUK QUERY OR TRANSAKSI
				//*** BERTUJUAN UNTUK MENGELAK QUERY YG PANJANG APABILA 1ST BUKAK CLASS DALAM KAEDAH SUBMISSION
				//*** TOLONG DENGAR NASIHAT SAYA
				//*** PEKARA INI SANGAT PENTING
				//*** YANG BENAR, RAZMAN
				
				String htmlSkrinCarian = MP.HTMLSkrinCarian(dbMain, "myinfoppk", modul); 
				context.put("htmlSkrinCarian", htmlSkrinCarian);
				//default method call time open class
				//method mestila return script + html syntax untuk excute cmdajax
				
				//mesti 1st landing page
				skrin_name = "app/ppk/MyInfoPPK/index.jsp";
				
				//akan landing dalam index page
				//String htmlOnStart = onStart();
				//context.put("htmlOnStart", htmlOnStart);
			}
			
			//close - programmer part
						
			
			conn.commit();
		}
		catch (SQLException se) {
			//jika ada, masalah/error.. segala transactionajax akan di ROLLBACK seperti asal
			try {
				conn.rollback();
				myLogger.info("ROLLBACK CALLED");
			} catch (SQLException se2) {
				errorMesej += "RALAT ROLLBACK :" + se2.getMessage();
				//throw new Exception("ROLLBACK ERROR :" + se2.getMessage());
			}
			se.printStackTrace();
			errorMesej += "RALAT SQL :" + se.getMessage();
			//throw new Exception("RALAT : " + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (dbMain != null)
				dbMain.close();
		}
		
		if(!errorMesej.equals(""))
		{
			//jika ada error pada SQL, skrin akan di Divert pada skrin error.jsp yang umum supaya paparan error lebih teratur
			skrin_name = RT.RTerrorControl(errorMesej);	
		}
		//close >> [STANDARD] connection declared
		
		//kira masa loading, betujuan untuk monitoring
		RT.RTloadingTimeControl(skrin_name); 
		//myLogger.info("**LOADING TIME BY cmdajax : '"+cmdajax+"', actionajax : '"+actionajax+"', TIME TAKEN : "+RT.finishTime()+" SEC");
		
		//defult context return to jsp		
		this.context.put("cmdajax",cmdajax);//return balik current cmdajax
		this.context.put("command",cmdajax);//return balik current cmdajax
		this.context.put("actionajax",actionajax);//return balik current actionajax	
		this.context.put("skrinName","myinfoppk");//return balik current actionajax			
				
		// [STANDARD] biasa kan show skrin yg kita bukak, senang untuk trouble shoot nanti
		myLogger.info("::: SKRIN NAME : "+skrin_name);
		// [STANDARD] finale skrin/jsp wajib direturn
		return skrin_name;
	}
	
}
