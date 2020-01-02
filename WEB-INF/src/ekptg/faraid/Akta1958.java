package ekptg.faraid;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.faraid.EkptgEngine.Simati;
import ekptg.faraid.EkptgEngine.Waris;

public class Akta1958 {

	static Logger myLogger = Logger.getLogger(Akta1958.class);
	private static boolean debugmode = true;
	
	private GenderEnum gender = GenderEnum.Unknown;
	private boolean inProgress = false;
	//Jumlah
	private int totalWaris=0;
	private boolean paramExist[]={false,false,false,false,false,false,};
	private int relation_count[]={0,0,0,0,0,0};
	private int baseResult[][] = {{0,0},{0,0},{0,0},{0,0},{0,0},{0,0}};
	private int tashiehResult[][] = {{0,0},{0,0},{0,0},{0,0},{0,0},{0,0}};
    long lBase[] = {0,0};
    long lTashieh[] = {0,0};
    public String firstLayer_IDOB="";
    
	public Akta1958() {
		//Initialize();
	}
	
	public void SetGender(GenderEnum g) {
		this.gender = g;
	}
	
	public Vector doAllAkta1958Processing(EkptgEngine ekptgEngine,
			Simati sm,String id_simati,String id_permohonan) {
		//Simati sm;
		//EkptgEngine ekptgEngine;
		Vector hasilFaraid = null;
		Vector listWaris = null;
		Akta1958 akta1958;
		try {
			hasilFaraid = new Vector();
			sm.setId_simati(id_simati);
			sm.setId_permohonan(id_permohonan);
			ekptgEngine.getSimatiInfo(sm);
			ekptgEngine.setHTMLBuilder("** "+sm.getNamaSimati()+" (Meninggal)");
			int lapisan = ekptgEngine.getLapisan(id_simati,id_permohonan);
			this.gender = (GenderEnum)ConvertTextEnumJantina(sm.getJantina());
			sm.setLapisan(lapisan);
			if (lapisan > 1) {
				 Vector hasilFaraidLapisan1 = null;
				 Vector hasilFaraidBerikut = null;
				 Waris obInfo;
				 ekptgEngine.setHTMLBuilder("** Kes "+lapisan+" Lapis **");
			 	 Faraid.Engine.Layers oLayer = new Faraid.Engine.Layers();
				 int jumWaris = ekptgEngine.getJumWaris(id_simati,id_permohonan);
				 oLayer.Initialize(jumWaris, lapisan);
				 
				 //Masukkan semua waris dalam oLayers
				 listWaris = senaraiSemuaWaris(id_simati,id_permohonan);
				 for (int x=0;x<listWaris.size();x++) {
					obInfo = (Waris)listWaris.get(x);
					if (debugmode) {
						myLogger.info(obInfo.getID()+":"+obInfo.getNama());
					}
					oLayer.InsertHeirDetail(obInfo.getID(),obInfo.getNama());
				 }
				 
				//Lapisan pertama
				hasilFaraidLapisan1 = doFirstLayer(ekptgEngine,id_simati,id_permohonan);
				ekptgEngine.AssignLayerTashieh(oLayer,hasilFaraidLapisan1,1);
				
				//***** Lapisan-lapisan yang berikut
				Vector waris_meninggal = ekptgEngine.getWarisMeninggal(hasilFaraidLapisan1);
				
				
				String id_ob_yg_meninggal = "";
				for (int i=0;i<waris_meninggal.size();i++) {
					obInfo = (Waris)waris_meninggal.get(i);
					oLayer.AssignLayerHeader(obInfo.getID(),(i+2));
					id_ob_yg_meninggal = id_ob_yg_meninggal + obInfo.getID() + ","; //remember id_ob yg sudah meninggal
					
					//create new Faraid object - Single Layer
					akta1958 = new Akta1958();
					if (debugmode) {
						 myLogger.info("Gender simati:"+obInfo.getJantina());
						 myLogger.info("Converted Gender simati:"+ConvertTextEnumJantina(obInfo.getJantina()));
					}
					try {
						akta1958.SetGender((GenderEnum)ConvertTextEnumJantina(obInfo.getJantina()));
					} catch (Exception x) {
						throw new Exception ("Simati="+obInfo.getNama()+" ( "+x.getMessage() + ")");
					}
					ekptgEngine.setHTMLBuilder("");
					ekptgEngine.setHTMLBuilder("** "+obInfo.getNama()+" (Meninggal)");
					
					//dapatkan senarai lapis n+1
					hasilFaraidBerikut = akta1958.doMultiLayer(ekptgEngine, akta1958,
							obInfo.getID(),
							ekptgEngine.getOneShorter(this.getFirstLayer_IDOB2()),
							ekptgEngine.getOneShorter(id_ob_yg_meninggal),
							sm.getJantina(), obInfo.getHubunganAsal());
					
					ekptgEngine.AssignLayerTashieh(oLayer,hasilFaraidBerikut,(i+2));
					
					hasilFaraidBerikut.clear();
				}
				
				//Final Result
				hasilFaraidBerikut.clear();
				hasilFaraid = ekptgEngine.getFinalResult(oLayer);
				ekptgEngine.setHTMLBuilder("");
				ekptgEngine.setHTMLBuilder("** Final Result **");
				ekptgEngine.displayResult(hasilFaraid,sm);
				
				
			} else {
				ekptgEngine.setHTMLBuilder("** Kes 1 Lapis **");
				hasilFaraid = doFirstLayer(ekptgEngine,id_simati,id_permohonan);
				
				ekptgEngine.displayResult(hasilFaraid,sm);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hasilFaraid;
	}
	
	///
	
	public String getFirstLayer_IDOB2() {
		
		String txt="";String name="";String value="";
		Enumeration allparam = this.firstLayer_IDOB2.elements();
		for (; allparam.hasMoreElements(); ) {
	        name = (String)allparam.nextElement();
	        value = (String)this.firstLayer_IDOB2.get(name);
	        txt = txt + value + ",";
		}
		return txt;
	}
	
	//
	
	public Vector doMultiLayer(EkptgEngine ekptgEngine,Akta1958 akta1958,
			String id_ob,String id_ob_from_first_layer,
			String list_of_ids,
			String JantinaSimati,String HubunganWarisMati) 
	throws Exception{
		Vector output = null;
		Vector lists = null;
		try {
			output = new Vector();
			lists = akta1958.getMultiLayerData(id_ob, id_ob_from_first_layer, list_of_ids, JantinaSimati, HubunganWarisMati);
			akta1958.addParamaters(lists);
			akta1958.Calculate();
			output = akta1958.getOutput(lists);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;

	}
	
	///
	public Vector doFirstLayer(EkptgEngine ekptgEngine,String id_simati,String id_permohonan) {
		Vector output = null;
		Vector lists = null;
		try {
			output = new Vector();
			lists = getFirstLayerData(id_simati,id_permohonan);
			addParamaters(lists);
			Calculate();
			output = getOutput(lists);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}
	
	public void addParamaters(Vector lists) {
		Waris ob;
		try {
			for (int i=0;i<lists.size();i++) {
				ob = (Waris)lists.get(i);
				if (debugmode) myLogger.info(ob.getNama()+"-"+ob.getHubungan()+" - "+ob.getJumlahHubungan());
				SetRelationCount("Add",(RelationEnum)ConvertTextEnumHubungan(ob.getHubungan()),ob.getJumlahHubungan());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Vector getOutput(Vector lists) {
		Vector FaraidOutput = null;
		Waris ob;
		try {
			FaraidOutput = new Vector();
			for (int i=0;i<lists.size();i++) {
			  ob = (Waris)lists.get(i);
			  //ekptgEngine.setHTMLBuilder(ob);
			  GetRelationFraction((RelationEnum)ConvertTextEnumHubungan(ob.getHubungan()), lBase, lTashieh);
			  
			  //Change - bahagian tashieh actually is bahagian asal
			  
//			  ob.setBahagian(lBase[0]/ob.getJumlahHubungan() + "/" + lBase[1]);
//			  ob.setBahagianAtas(lBase[0]/ob.getJumlahHubungan());
//			  ob.setBahagianBawah(lBase[1]);
			  ob.setBahagianTashieh(lBase[0]/ob.getJumlahHubungan() + "/" + lBase[1]);
			  ob.setBahagianAtasTashieh(lBase[0]/ob.getJumlahHubungan());
			  ob.setBahagianBawahTashieh(lBase[1]);		
			  
			  ob.setBahagian(lTashieh[0]/ob.getJumlahHubungan() + "/" + lTashieh[1]);
			  ob.setBahagianAtas(lTashieh[0]/ob.getJumlahHubungan());
			  ob.setBahagianBawah(lTashieh[1]);	
			  FaraidOutput.add(ob);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FaraidOutput;
	}
	//////////////////////////
	public static void main(String[] args) {
		long lBase[] = {0,0};
        long lTashieh[] = {0,0};
		try {
			
			//berlapis
			/*
			myLogger.info("Akta 1958 Berlapis");
			Akta1958Layers a = new Akta1958Layers();
			a.Initialize(5,2);
			a.InsertHeirDetail("ID1", "A");
			a.InsertHeirDetail("ID2", "B");
			a.InsertHeirDetail("ID3", "C");
			a.InsertHeirDetail("ID4", "D");
			a.InsertHeirDetail("ID5", "E");
			
			a.AssignLayerHeader("ID3", 2);
			
			a.AssignLayerTashieh("ID1", 1,1,3);
			a.AssignLayerTashieh("ID2", 1,1,3);
			a.AssignLayerTashieh("ID3", 1,1,3);
			
			a.AssignLayerTashieh("ID4", 2,1,2);
			a.AssignLayerTashieh("ID5", 2,1,2);
			
			a.Calculate();
			*/
			 
			
			Akta1958 a = new Akta1958();
			 
			EkptgEngine ekptgEngine = new EkptgEngine();
			Simati sm = ekptgEngine.new Simati();
			Vector hasilFaraid = new Vector();
			hasilFaraid = a.doAllAkta1958Processing(ekptgEngine,sm,"108","181");
			/**/
			/*
			Akta1958 a = new Akta1958();
			a.setGender(GenderEnum.Male);
			a.SetRelationCount("",RelationEnum.Wife,1);
			a.SetRelationCount("",RelationEnum.Child,1);
			a.SetRelationCount("",RelationEnum.Mother,1);
			a.SetRelationCount("",RelationEnum.Father,1);
			*/
			/*
			Akta1958 a = new Akta1958();
			a.setGender(GenderEnum.Male);
			
			a.SetRelationCount(RelationEnum.Mother,1);
			a.SetRelationCount(RelationEnum.Father,1);
			a.SetRelationCount(RelationEnum.Husband,1);
			a.SetRelationCount(RelationEnum.Wife,1);
			a.SetRelationCount(RelationEnum.Wife,1);
			a.SetRelationCount(RelationEnum.Child,2);
			
			//myLogger.info("is waris tunggal "+a.isWarisTunggal(RelationEnum.Wife));
			//myLogger.info("is waris tunggal "+a.isWarisTunggal());
			*/
			
			//a.Calculate();
			//a.printResult(a,lBase,lTashieh);
			
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void printResult(Akta1958 a,long[] lBase,long[] lTashieh) {
		myLogger.info("** Result ***");
		for (RelationEnum r : RelationEnum.values()) {
	        a.GetRelationFraction(r,lBase,lTashieh);
	        if (lBase[0] > 0) {
//	        	if (r == RelationEnum.Child) {
//	        		myLogger.info(r + "-" + lBase[0] + "/" + lBase[1]);
//	        		myLogger.info(r + "-" + lTashieh[0] + "/" + lTashieh[1]);
//	        		myLogger.info(".........");
//	        	} else {
	        		myLogger.info(r + "-" + lBase[0] + "/" + lBase[1]);
	        		myLogger.info(r + "-" + lTashieh[0] + "/" + lTashieh[1]);
	        		myLogger.info(".........");
	        	//}
	        }
		}
	}

	
	public void Initialize() {
		
		for (int i=0;i<getRelationSize();i++) {
			//myLogger.info("init value relation_count["+i+"]=0");
			relation_count[i]=0;
			paramExist[i]=false;
		}
	}
	
	public int getRelationSize() {
		int x=0;
		for (RelationEnum rr : RelationEnum.values()) {
			x++;
		}
		return x;
	}
	
	//Calculation *************
	public void Calculate() throws Exception {
		myLogger.info("** Calculate **");
		
		if (this.gender == GenderEnum.Unknown) {
			myLogger.warn("Jantina Simati Tiada!!");
			throw new Exception("Jantina Simati Tiada!!");
		}
		
		initExist();
		//myLogger.info("Total waris = "+this.totalWaris);
		if (this.totalWaris == 0) {
			myLogger.info("0 waris:nothing to calculate");
			return;
		}
		int r1=0,r2=0,r3=0,r4=0;
		int indexParam=0;
		int bA=1;int bB=1;
		int check = isWarisTunggal();
		//Condition 1 - waris tunggal
		if (check > 0) {
			if (check == 5 && this.totalWaris > 0) {
				bB = this.totalWaris;
			} 
			saveBaseResult(check,bA,bB);
			saveTashiehResult(check,bA,bB);
		} else if (checkRules(1)) {
			myLogger.info("** Rules 1 **");
			bB=2;
			saveBaseResult(1,bA,bB);
			saveBaseResult(2,bA,bB);
			saveTashiehResult(1,bA,bB);
			saveTashiehResult(2,bA,bB);
			
		} else if (checkRules(2)) {
			myLogger.info("** Rules 2 **");
			//anak
			bA=2;bB=3*relation_count[5];
			//myLogger.info("anak:"+bA+"/"+bB);
			saveBaseResult(5,bA,bB);
			//if gender=M,pasangan=Wife
			//if gender=F,pasangan=Husband
			bA=1;bB=3;
			
			if (this.gender == GenderEnum.Male) {
				indexParam=4;
				saveBaseResult(4,bA,bB);
			} else if (this.gender == GenderEnum.Female) {
				indexParam=3;
				saveBaseResult(3,bA,bB);
			} 
			
			//Rules2 tashieh calculation
			CalculateTashieh(5,indexParam);
			
		}else if (checkRules(3)) {
			myLogger.info("** Rules 3 **");
			//parent
			bA=1;bB=2;
			if (paramExist[1] == false) {
				saveBaseResult(2,bA,bB);
			}else if (paramExist[2] == false) {
				saveBaseResult(1,bA,bB);
			} else {
				bA=1;bB=bB*2;
				saveBaseResult(1,bA,bB);
				saveBaseResult(2,bA,bB);
			}
			//pasangan
			bA=1;bB=2;
			if (this.gender == GenderEnum.Male) {
				indexParam=4;
				saveBaseResult(4,bA,bB);
			}else if (this.gender == GenderEnum.Female) {
				indexParam=3;
				saveBaseResult(3,bA,bB);
			}
			CalculateTashieh(1,2,indexParam);
			
		} else if (checkRules(4)) {
			myLogger.info("** Rules 4 **");
			//anak
			bA=2;bB=3*relation_count[5];
			saveBaseResult(5,bA,bB);
			
			//parent
			bA=1;bB=3;
			if (paramExist[1] == false) {
				saveBaseResult(2,bA,bB);
			}else if (paramExist[2] == false) {
				saveBaseResult(1,bA,bB);
			} else {
				bA=1;bB=bB*2;
				saveBaseResult(1,bA,bB);
				saveBaseResult(2,bA,bB);
			}
			CalculateTashieh(1,2,5);
		} else if (checkRules(5)) {
			myLogger.info("** Rules 5 **");
			//anak
			bA=1;bB=2*relation_count[5];
			saveBaseResult(5,bA,bB);
			
			//parent
			bA=1;bB=4;
			if (paramExist[1] == false) {
				saveBaseResult(2,bA,bB);
			}else if (paramExist[2] == false) {
				saveBaseResult(1,bA,bB);
			} else {
				bA=1;bB=bB*2;
				saveBaseResult(1,bA,bB);
				saveBaseResult(2,bA,bB);
			}
			
			//pasangan
			bA=1;bB=4;
			if (this.gender == GenderEnum.Male) {
				indexParam=4;
				saveBaseResult(4,bA,bB);
			}else if (this.gender == GenderEnum.Female) {
				indexParam=3;
				saveBaseResult(3,bA,bB);
			}
			
			CalculateTashieh(1,2,5,indexParam);
		}
		else {
			myLogger.info("not in rules..so..do something ajae");
		}
		//CalculateTashieh();
	}

	public void CalculateTashieh (int r1,int r2) {
		int a,b,c,d;
		a = baseResult[r1][0];b=baseResult[r1][1];
		Fraction f1 = new Fraction(a,b);
		
		c = baseResult[r2][0];d=baseResult[r2][1];
		Fraction f2 = new Fraction(c,d);
		
		Fraction.getTashiehFraction(f1, f2);
		saveTashiehResult(r1,f1);
		saveTashiehResult(r2,f2);

	}
	
	public void CalculateTashieh (int r1,int r2,int r3) {
		//r1,r2 reserve to mother and father
		int a,b,c,d,e,f;
		a = baseResult[r1][0];b=baseResult[r1][1];
		c = baseResult[r2][0];d=baseResult[r2][1];
		Fraction f1=null;
		if (a>0) {
			f1 = new Fraction(a,b);
		} else {
			f1 = new Fraction(c,d);
		}
		
		e = baseResult[r3][0];f=baseResult[r3][1];
		Fraction f2 = new Fraction(e,f);
		
		Fraction.getTashiehFraction(f1, f2);
		if (r1>0) saveTashiehResult(r1,f1);
		if (r2>0) saveTashiehResult(r2,f1);
		saveTashiehResult(r3,f2);
	}
	
	public void CalculateTashieh (int r1,int r2,int r3,int r4) {
		//r1,r2 reserve to mother and father
		//r3 child,r4 spouse
		int a,b,c,d,e,f,g,h;
		a = baseResult[r1][0];b=baseResult[r1][1];
		c = baseResult[r2][0];d=baseResult[r2][1];
		Fraction f1=null;
		if (a>0) {
			f1 = new Fraction(a,b);
		} else {
			f1 = new Fraction(c,d);
		}
		
		e = baseResult[r3][0];f=baseResult[r3][1];
		Fraction f2 = new Fraction(e,f);
		
		g = baseResult[r4][0];h=baseResult[r4][1];
		Fraction f3 = new Fraction(g,h);
		
		Fraction.getTashiehFraction(f1, f2,f3);
		
		if (r1>0) saveTashiehResult(r1,f1);
		if (r2>0) saveTashiehResult(r2,f1);
		saveTashiehResult(r3,f2);
		saveTashiehResult(r4,f3);
	}
	
	
	public void initExist() {
		int index=0;
		for (RelationEnum r : RelationEnum.values()) {
			index = r.ordinal();
			if (index>0) {
				if (relation_count[index] > 0) {
					paramExist[index]=true;
					this.totalWaris = this.totalWaris + relation_count[index];
					//myLogger.debug(r+":"+index+" ]:"+relation_count[index]+":"+paramExist[index]);
				}
			}
		}
	}
	
	//// Rules
	public boolean checkRules(int rules) {
		boolean flag=false;
		switch (rules)
		{
		case 1: //only has parent
			if ( paramExist[1] == true && paramExist[2]==true && 
				 paramExist[3]==false && paramExist[4]==false && 
				 paramExist[5]==false ) {
				flag=true;
			}
			break;
		case 2://pasangan & anak
			if ( paramExist[1] == false && paramExist[2]==false && 
					 (paramExist[3]==true || paramExist[4]==true) && 
					 paramExist[5]==true ) {
					flag=true;
				}			
			break;
		case 3://pasangan & ibu bapa
			if ( (paramExist[1] == true || paramExist[2]==true) && 
					 (paramExist[3]==true || paramExist[4]==true) && 
					 paramExist[5]==false ) {
					flag=true;
				}	
			break;
		case 4:// anak & ibu bapa
			if ( (paramExist[1] == true || paramExist[2]==true) && 
					 paramExist[3]==false && paramExist[4]==false && 
					 paramExist[5]==true ) {
					flag=true;
				}
			break;		
		case 5://pasangan & anak & ibu bapa
			if ( (paramExist[1] == true || paramExist[2]==true) && 
					 (paramExist[3]==true || paramExist[4]==true) && 
					 paramExist[5]==true ) {
					flag=true;
				}
			break;
		}
		return flag;
	}
	
	public void GetRelationFraction(RelationEnum paramRelationEnum,
			long[] lBase,
			long[] lTashieh) {
		
	    int base0=0;int base1=0;
		int l0=0;int l1=0;
		int index = paramRelationEnum.ordinal();
		
        lBase[0] = baseResult[index][0];
        lBase[1] = baseResult[index][1];
	    lTashieh[0] = tashiehResult[index][0];
	    lTashieh[1] = tashiehResult[index][1];
	}
	
	//setter
	public void setGender(GenderEnum genderenum) throws Exception {
        if(genderenum == GenderEnum.Female || genderenum == GenderEnum.Male)
        {
            this.gender = genderenum;
        } else
        {
            throw new Exception("Invalid Gender enumeration assignment!");
        }
	}
	
	///////////
	
	public void AddRelationCount(RelationEnum paramRelationEnum, int count) 
	throws Exception {
		int index=paramRelationEnum.ordinal();
		int current_count=0;
		switch (index) {
		case 1:
			current_count = this.relation_count[1];
			count = count + current_count;
			if (count > 1) {
        		throw new Exception("Invalid Relation:Cannot have more than 1 mother");
        	} else {
        		this.relation_count[1] = count;
        	}
			break;
		}
	}
	
	
	///
	public void SetRelationCount(String type,RelationEnum paramRelationEnum, int count)
	throws Exception
    {
		int index=paramRelationEnum.ordinal();
		int current_count=0;
		
		if ("".equals(type)) {
	        if(count <= 0)
	        {
	            throw new Exception("Invalid relation count!");
	        }
		} else {
			current_count = this.relation_count[index];
			count = count + current_count;
		}
        
        
        //myLogger.info("SetRelationCount:"+paramRelationEnum+"("+index+"):"+count);
        switch (index)
        {
        case 1:
        	if (count > 1) {
        		throw new Exception("Invalid Relation:Cannot have more than 1 mother");
        	} else {
        		this.relation_count[1] = count;
        	}
       	break;
        case 2:
        	if (count > 1) {
        		throw new Exception("Invalid Relation:Cannot have more than 1 father");
        	} else {
        		this.relation_count[2] = count;
        	}
        	break;
        case 3:
         	if (this.gender == GenderEnum.Male) {
           		throw new Exception("Invalid Relation:Cannot have husband for gender Male");
        	} else if (count > 1) { 
           		throw new Exception("Invalid Relation:Cannot have more than 1 husband");
        	} else {
        		this.relation_count[3] = count;
        	}
        	break;
        case 4:
        	if (this.gender == GenderEnum.Female) {
           		throw new Exception("Invalid Relation:Cannot have wife for gender FeMale");
        	} else if (count > 1) { 
           		throw new Exception("Invalid Relation:Cannot have more than 1 wife");
        	} else {
        		this.relation_count[4] = count;
        	}
        	break;
        case 5:
        	this.relation_count[5] = count;
        	break;
        //default:
        //throw new Exception("Invalid Relation enum to set relation count!");
        }
    }
	
	public int isWarisTunggal() {
		int count=0;
		int index=0;
		int match=0;
		for (RelationEnum r : RelationEnum.values()) {
			index=r.ordinal();
			if (index>0) {
				//myLogger.info(r.ordinal());
				if (relation_count[index] > 0) {
					match = index;
					count++;
				}
			}
		}
		if (count == 1) return match;
		else return 0;
	}

	public void saveBaseResult(int x,int y,int z) {
		//myLogger.info("saving "+x+":"+y+":"+z);
		baseResult[x][0] = y;
		baseResult[x][1] = z;
	}
	public void saveTashiehResult(int x,int y,int z) {
		tashiehResult[x][0] = y;
		tashiehResult[x][1] = z;
	}
	
	public void saveTashiehResult(int x,Fraction f) {
		tashiehResult[x][0] = f.getNumerator();
		tashiehResult[x][1] = f.getDenominator();
	}
	
	//////////////
	private Hashtable firstLayer_IDOB2 = new Hashtable();
	
	public Vector getFirstLayerData(String id_simati,String id_permohonan) 
	throws Exception {
		Db db = null;
		Vector lists = null;
		Waris ob;
		String sql;
		try {
			db = new Db(); 
			lists = new Vector();
			//Add Distinct - on 19/10/2009
			sql ="SELECT B.LAPIS,B.ID_OB,B.NAMA_OB,B.JANTINA,NVL(A.KETERANGAN,'Tiada Hubungan') KETERANGAN," +
			"B.STATUS_HIDUP,TO_CHAR(B.TARIKH_MATI,'DD/MM/YYYY') as TARIKH_MATI," +
			"1 as Jumlah_Hubungan "+
			"FROM TBLPPKOB B LEFT OUTER JOIN TBLPPKPERMOHONANSIMATI C "+
			//"ON B.ID_PERMOHONANSIMATI = C.ID_PERMOHONANSIMATI "+
			"ON B.ID_SIMATI = C.ID_SIMATI "+
			"LEFT OUTER JOIN TBLPPKRUJSAUDARA A ON B.ID_SAUDARA = A.ID_SAUDARA "+
			"WHERE "+
			"B.LAPIS = 1  and b.id_tarafkptg in (1,8) " +
			"AND C.ID_SIMATI='"+id_simati+"' "+
			"AND C.ID_PERMOHONANSIMATI NOT IN (SELECT ID_PERMOHONANSIMATI FROM tblppkfailpindah)"+
			"AND C.ID_PERMOHONAN = '"+id_permohonan+"' "+
			"Order by KETERANGAN,LAPIS,B.tarikh_mati  ";
			
			if (debugmode) myLogger.info("FIRST LAYER DATA:"+sql);
			//get some data 
			ResultSet rs = db.getStatement().executeQuery(sql); 
			while (rs.next()){	
				ob = new EkptgEngine().new Waris();
				ob.setLapisan(rs.getString("LAPIS"));
				ob.setID(rs.getString("ID_OB"));
				ob.setNama(rs.getString("NAMA_OB"));
				ob.setJantina(rs.getString("JANTINA"));
				ob.setKeteranganJantina(rs.getString("KETERANGAN"));
				ob.setHubungan(rs.getString("KETERANGAN"));
				ob.setHubunganAsal(rs.getString("KETERANGAN"));
				ob.setStatus_Hidup(rs.getString("STATUS_HIDUP"));
				ob.setTarikhMati(rs.getString("TARIKH_MATI"));
				if (rs.getInt("Jumlah_Hubungan") == 0) {
					ob.setJumlahHubungan(1);
				}else {
					ob.setJumlahHubungan(rs.getInt("Jumlah_Hubungan"));
				}
				ob.setDirectWithSimatiAsal(true);
				//this.firstLayer_IDOB = this.firstLayer_IDOB + ob.getID() + ",";
				firstLayer_IDOB2.put(ob.getID(),ob.getID());
				lists.add(ob);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( db != null ) db.close(); 
		}
	return lists;
	}
	//	
	
	public Vector getMultiLayerData(String id_ob,String id_ob_from_first_layer,
			String list_of_ids,
			String JantinaSimati,String HubunganWarisMati) 
	throws Exception {
		Db db = null;
		Connection conn = null;
		Vector lists = null;
		Waris ob;
		firstLayer_IDOB = "";
		String sql = "";
		String sqlWhere="";
		try {
			db = new Db(); 
			conn = db.getConnection();
	        conn.setAutoCommit(false);
	        Statement stmt = db.getStatement();
	        
	        //Prepare data first
	        //1.DELETE 
	        sql = "DELETE FROM TBL_FARAID_TMP WHERE ID='"+id_ob+"' ";
	        if (debugmode) myLogger.debug(sql);
	        stmt.executeUpdate(sql);
	        
	        //2.Insert
	        sql = "INSERT INTO TBL_FARAID_TMP "+
			"SELECT DISTINCT "+id_ob+",B.ID_OB,B.NAMA_OB,A.KETERANGAN AS Hubungan_Asal,CASE WHEN B.LAPIS = 1 THEN ( "+
			"Select NVL(upper(HBGN_BARU),'') AS HBGN_BARU From TBLRUJHUBUNGAN WHERE  "+
			"upper(JANTINA_SIMATI)='"+JantinaSimati.toUpperCase()+"' "+
			"AND upper(HBGN_WARIS_MATI)='"+HubunganWarisMati.toUpperCase()+"'  "+
			"AND upper(HBGN_WARIS_BERIKUT)=A.KETERANGAN AND ROWNUM<=1)  "+
			"ELSE (NVL(A.KETERANGAN,'Tiada Hubungan')) END  "+
			"AS Hubungan_Baru,0 as JUMLAH_HUBUNGAN  "+
			"FROM TBLPPKOB B LEFT OUTER JOIN TBLPPKPERMOHONANSIMATI C " +
			//"ON B.ID_PERMOHONANSIMATI = C.ID_PERMOHONANSIMATI "+ 
			"ON B.ID_SIMATI = C.ID_SIMATI "+ 
			"LEFT OUTER JOIN TBLPPKRUJSAUDARA A ON B.ID_SAUDARA = A.ID_SAUDARA ";
			sqlWhere = "WHERE b.id_tarafkptg in (1,8) and B.ID_OB IN (select ID_OB from TBLPPKHUBUNGANOB where id_parentob='"+id_ob+"') ";
			if (id_ob_from_first_layer != null) {
				sqlWhere = sqlWhere + " OR ( " +
						"( B.ID_OB IN ("+id_ob_from_first_layer+") AND Status_Hidup=0 ) " +
						"OR " +
						 "( B.ID_OB IN ("+id_ob_from_first_layer+") AND Status_Hidup=1 AND " +
									  "B.ID_OB NOT IN ("+list_of_ids+") "+
						 ") "+
						")";
			}	      
			if (debugmode)myLogger.debug(sql+sqlWhere);
	        stmt.executeUpdate(sql+sqlWhere);
	        
	        //3.Update
	        sql = "UPDATE TBL_FARAID_TMP A SET " +
	        	  "A.JUMLAH_HUBUNGAN = ( SELECT count(*) from TBL_FARAID_TMP WHERE HUBUNGAN_BARU = A.HUBUNGAN_BARU AND ID="+id_ob+") "+
	        	  "WHERE A.ID='"+id_ob+"'";
	        if (debugmode) myLogger.debug(sql);
	        stmt.executeUpdate(sql);
	        
	        
	        conn.commit();
	        
			lists = new Vector();
	

			sql = "SELECT ID_OB,NAMA_OB,HUBUNGAN_ASAL,HUBUNGAN_BARU,JUMLAH_HUBUNGAN FROM " +
					"TBL_FARAID_TMP WHERE ID='"+id_ob+"' ";
			if (debugmode)myLogger.debug(sql);
			//get some data 
			ResultSet rs = db.getStatement().executeQuery(sql); 
			while (rs.next()){	
				ob = new EkptgEngine().new Waris();
				ob.setID(rs.getString("ID_OB"));
				ob.setNama(rs.getString("NAMA_OB"));
				ob.setHubungan(rs.getString("HUBUNGAN_BARU"));
				ob.setHubunganAsal(rs.getString("HUBUNGAN_ASAL"));
				ob.setJumlahHubungan(rs.getInt("JUMLAH_HUBUNGAN"));
				lists.add(ob);
			}
		}  catch (SQLException se) { 
			try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Jana Faraid:"+se.getMessage());
		} finally {
			if ( db != null ) db.close(); 
		}
		return lists;
	}
	//	
	
	public Vector senaraiSemuaWaris(String id_simati,String id_permohonan) 
	throws Exception{

		Db db = new Db();
		Vector v = null;
		Waris ob;
		String sql="";
		try{
		v=new Vector();	
		sql = "SELECT B.LAPIS,B.ID_OB,B.NAMA_OB" +
			  " FROM TBLPPKRUJSAUDARA A , TBLPPKOB B, TBLPPKPERMOHONANSIMATI C" +
			  " WHERE B.ID_SAUDARA = A.ID_SAUDARA" +
			  " AND B.ID_SIMATI = C.ID_SIMATI" +
			  " AND B.ID_PERMOHONANSIMATI = C.ID_PERMOHONANSIMATI" +
			  //" AND C.ID_PERMOHONAN = '" + id_permohonan + "'"+
			  " AND B.ID_SIMATI = '" + id_simati + "'"+
			  " AND C.ID_PERMOHONANSIMATI NOT IN (select id_permohonansimati from tblppkfailpindah)"; //Exclude from fail pindah cases
			
		
		ResultSet rs = db.getStatement().executeQuery(sql);
		if (debugmode) {
			myLogger.info(sql);
		}
		while (rs.next()) {
			//ob = new EkptgEngine.Waris();
			ob = new EkptgEngine().new Waris();
			ob.setID(rs.getString("ID_OB"));
			ob.setNama(rs.getString("NAMA_OB"));
			ob.setLapisan(rs.getString("LAPIS"));
			v.add(ob);
		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( db != null ) db.close(); 
		}
		return v;

	}
	
	//Constant
	public static enum GenderEnum
	{
		Unknown, Male, Female;
	    public Integer value;
	}
	public static enum RelationEnum
	{
		NotDetermined,Mother,Father,Husband, Wife,Child ;
	    public Integer value;
	}	
	
	//Mapping
	
	//-- Ekptg to Faraid Engine Mapping
	
	 public static GenderEnum ConvertTextEnumJantina(String sGender) {
		 if ("1".equals(sGender)) return GenderEnum.Male;
		 else if ("2".equals(sGender)) return GenderEnum.Female;
		 else if ("L".equals(sGender)) return GenderEnum.Male;
		 else if ("P".equals(sGender)) return GenderEnum.Female;
		 else if ("Lelaki".equals(sGender)) return GenderEnum.Male;
		 else if ("Perempuan".equals(sGender)) return GenderEnum.Female;
		 else return GenderEnum.Unknown;
	 }
	 public static RelationEnum ConvertTextEnumHubungan(String sRelation) {
		 if ("ISTERI".equals(sRelation)) return RelationEnum.Wife;
		 else if ("SUAMI".equals(sRelation)) return RelationEnum.Husband;
		 else if ("ANAK LELAKI".equals(sRelation)) return RelationEnum.Child;
		 else if ("ANAK PEREMPUAN".equals(sRelation)) return RelationEnum.Child;
		 else if ("IBU".equals(sRelation)) return RelationEnum.Mother;
		 else if ("BAPA".equals(sRelation)) return RelationEnum.Father;
		 else return RelationEnum.NotDetermined;
	 }
	
	
}
