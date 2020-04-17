package ekptg.faraid;

//import java.util.ArrayList;

import org.apache.log4j.Logger;

public class Akta1958Layers
{
  static Logger myLogger = Logger.getLogger(Akta1958Layers.class);
	
  private int i=0,j=0;
  private String[][] a;
  private int b;
  private int c;
  @SuppressWarnings("unused")
  private boolean d = false;
  @SuppressWarnings("unused")
  private boolean e = false;

  public Akta1958Layers() {
	  
  }
  
  public void Initialize(int paramInt1, int paramInt2) throws Exception
  {
    try
    {
      if ((paramInt1 > 0) && (paramInt2 > 1))
      {
        this.b = paramInt1;
        this.c = paramInt2;
        this.a = new String[this.b + 1][2 * (this.c + this.c - 1) + 3];
        this.a[0][0] = "ID";
        this.a[0][1] = "Name";
        this.a[0][2] = "Layer";
        this.a[0][3] = "L1.0";
        this.a[0][4] = "L1.1";
        for (paramInt2 = 2; paramInt2 <= this.c; ++paramInt2)
        {
          this.a[0][(4 * (paramInt2 - 1) + 1)] = "L" + paramInt2 + ".0";
          this.a[0][(4 * (paramInt2 - 1) + 2)] = "L" + paramInt2 + ".1";
          this.a[0][(4 * (paramInt2 - 1) + 3)] = "R" + (paramInt2 - 1) + ".0";
          this.a[0][(4 * (paramInt2 - 1) + 4)] = "R" + (paramInt2 - 1) + ".1";
        }
        for (paramInt1 = 1; paramInt1 <= this.a.length - 1; ++paramInt1)
          for (paramInt2 = 0; paramInt2 <= this.a[paramInt1].length - 1; ++paramInt2)
            this.a[paramInt1][paramInt2] = "";
        for (paramInt1 = 1; paramInt1 <= this.b; ++paramInt1)
          this.a[paramInt1][2] = "0";
        this.d = true;
        this.e = false;
        return;
      }
      throw new Exception("Heir count must greater than 0 and layer count must greater than 1!");
    }
    catch (Exception localException)
    {
      throw localException ;
    }
  }

  public void InsertHeirDetail(String id,String name) throws Exception {
	  int j=0;
	  for (int i = 1; i <= this.b; ++i) {
		  //myLogger.info("i:"+i);
          if (this.a[i][0].equals(id))
          {
        	myLogger.info("found:"+i);
            j = 1;
            break;
          }
	  }
	  if (j==0) {
		  for (i = 1; i <= this.b; ++i)
              if (this.a[i][0].isEmpty())
              {
                j = 1;
                this.a[i][0] = id;
                this.a[i][1] = name;
                break;
              }
            if (j == 0)
              throw new Exception("Heir row is full for new ID!");
	  }
  	 }
  
  public void AssignLayerHeader(String id,int lapis) throws Exception {
	  int i=0;
	  for (int j = 1; j <= this.b; ++j)
          if (this.a[j][2].equals(Integer.toString(lapis)))
          {
            i = 1;
            break;
          }
	 if (i == 0) {
		  for (j = 1; j <= this.b; ++j) {
              if (this.a[j][0].equals(id))
              {
                this.a[j][2] = Integer.toString(lapis);
                i = 1;
                break;
              }
		  }
		  if (i == 0) throw new Exception("Heir ID not exist!");
	  }
  }
 
  public void AssignLayerTashieh(String id,int lapis,int bA,int bB) throws Exception {
	   if (lapis == 1)
       {
         i = 3;
         lapis = 4;
       }
       else
       {
         i = 4 * (lapis - 1) + 1;
         lapis = 4 * (lapis - 1) + 2;
       }
	   
	   j = 0;
       for (int k = 1; k <= this.b; ++k)
         if (this.a[k][0].equals(id))
         {
           this.a[k][i] = Long.toString(bA);
           this.a[k][lapis] = Long.toString(bB);
           j = 1;
           break;
         }
       if (j == 0)
         throw new Exception("Heir ID not exist!");
	   
  }
  
  public void Calculate() {
//	  ArrayList localArrayList = new ArrayList();	  
//	  String str = "";
      for (int i = 2; i <= this.c; ++i)
      {
        @SuppressWarnings("unused")
		int j;
        @SuppressWarnings("unused")
		int k;
        @SuppressWarnings("unused")
        int l;
        @SuppressWarnings("unused")
        int i1;
        @SuppressWarnings("unused")
        int i2;
        @SuppressWarnings("unused")
        int i3;
        if (i == 2)
        {
          j = 3;
          k = 4;
          l = 5;
          i1 = 6;
          i2 = 7;
          i3 = 8;
        }
        else
        {
          j = 4 * (i - 1) - 1;
          k = 4 * (i - 1);
          l = 4 * (i - 1) + 1;
          i1 = 4 * (i - 1) + 2;
          i2 = 4 * (i - 1) + 3;
          i3 = 4 * (i - 1) + 4;
        }
        //
        myLogger.info("i="+i+":c="+c);
      }
  }
 
  
}