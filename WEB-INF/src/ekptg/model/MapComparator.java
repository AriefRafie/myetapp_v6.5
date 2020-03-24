package ekptg.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import ekptg.view.ppk.BicaraInteraktif;
//razman create, baru laju sorting
class MapComparator implements Comparator<Map<String, String>>
{
	static Logger myLogger = Logger.getLogger(MapComparator.class);
    private final String key;
    private final String sortType;
    private final String columnType;
    private final String dateFormat;
    
    public MapComparator(String key,String sortType,String columnType)
    {
    	this.key = key;
        this.sortType = sortType;
        this.columnType = columnType;
        this.dateFormat = "";
    }
    
    public MapComparator(String key,String sortType,String columnType,String dateFormat)
    {
        this.key = key;
        this.sortType = sortType;
        this.columnType = columnType;
        this.dateFormat = dateFormat;
    }

    public int compare(Map<String, String> first,
                       Map<String, String> second)
    {
        // TODO: Null checking, both for maps and values
        String firstValue = first.get(key).toUpperCase().trim();
        String secondValue = second.get(key).toUpperCase().trim();
        //myLogger.info("COMPARE >>>>>>>>>>> firstValue : '"+firstValue+"' secondValue : '"+secondValue+"' sortType : "+sortType+" columnType : "+columnType+" key : "+key+ " dateFormat : "+dateFormat);
        int returnValue = 0;
        
       // if(sortType.equals("ASC") || sortType.equals("DESC"))
        {
        	if(columnType.contains("CHAR") || columnType.contains("VARCHAR2"))
        	{
        		return firstValue.compareTo(secondValue);
        	}
        	else if(columnType.equals("NUMBER") || columnType.equals("INTEGER"))
        	{
        		if(firstValue.equals("") || firstValue == null)
        		{
        			firstValue = "-1";
        		}
        		
        		if(secondValue.equals("") || secondValue == null)
        		{
        			secondValue = "-1";
        		}
        		
        		/*
        		if(columnType.equals("NUMBER") && (firstValue.contains(".") || secondValue.contains(".")) )
    			{
    				//myLogger.info("ASC contains decimal firstValue : "+Double.valueOf(firstValue)+" secondValue : "+Double.valueOf(secondValue));
    				returnValue = Double.valueOf(firstValue).compareTo(Double.valueOf(secondValue));
    			}
    			else
    			{
    				//myLogger.info("asc Lepas");
    				returnValue = Long.valueOf(firstValue).compareTo(Long.valueOf(secondValue));
    			}
    			*/
        		
        		return Double.valueOf(firstValue).compareTo(Double.valueOf(secondValue));
        		
        	}
        	else if(columnType.equals("DATE") || columnType.equals("TIMESTAMP"))
        	{
        		//myLogger.info(">>>>> firstValue : "+firstValue+" secondValue : "+secondValue);   
        		
        		if (firstValue.equals(secondValue)) {
        			return returnValue = 0;
        		}
        		
        		if (firstValue.equals("") || firstValue.equals(null)) {
        			return returnValue = -1;
        		}
        		
        		if (secondValue.equals("") || secondValue.equals(null)) {
        			return returnValue = 1;
        		}
        		
        		Date date1 = new Date();
				Date date2 = new Date();
				
				try {
					
						//date1 = stringToDate(firstValue,"yyyy-MM-dd HH:mm:ss.S");
						//date2 = stringToDate(secondValue,"yyyy-MM-dd HH:mm:ss.S");
						date1 = stringToDate(firstValue,dateFormat);
						date2 = stringToDate(secondValue,dateFormat);
						int result = date1.compareTo(date2);
						if (result != 0) {
							return result;
						}
					
					/*
					if(date1 != null && date2 != null)
					{
						returnValue = date1.compareTo(date2);
						
					}
					else
					{
						returnValue = -1;
						
					}
					*/
					
				} catch (Exception e) {
					//returnValue = -1;					
				}			
        		
        		/*
        		if(!firstValue.equals("") && firstValue != null && !secondValue.equals("") && secondValue != null)
        		{
        			//myLogger.info(">>>>> lepas ");
        			Date date1 = new Date();
					Date date2 = new Date();
					try {
						if(columnType.equals("DATE") || columnType.equals("TIMESTAMP")) 
						{
							//date1 = stringToDate(firstValue,"yyyy-MM-dd HH:mm:ss.S");
							//date2 = stringToDate(secondValue,"yyyy-MM-dd HH:mm:ss.S");
							date1 = stringToDate(firstValue,dateFormat);
							date2 = stringToDate(secondValue,dateFormat);
						}
						
						if(date1 != null && date2 != null)
						{
							returnValue = date1.compareTo(date2);
							
						}
						else
						{
							returnValue = -1;
							
						}
						
					} catch (Exception e) {
						returnValue = -1;
						
					}					
        		}
        		else
        		{
        			returnValue = -1;
        			
        		}
        		*/
        		//myLogger.info(">>>>> returnValue : "+returnValue);
				
        	}
        	
        	
        	
        }
        /*
        else if(sortType.equals("DESC"))
        {
        	if(columnType.contains("CHAR") || columnType.contains("VARCHAR2"))
        	{
        		returnValue = secondValue.compareTo(firstValue);
        	}
        	else if(columnType.equals("NUMBER") || columnType.equals("INTEGER"))
        	{
        		if(firstValue.equals("") || firstValue == null)
        		{
        			firstValue = "-1";
        		}
        		
        		if(secondValue.equals("") || secondValue == null)
        		{
        			secondValue = "-1";
        		}
        		
        		
        		returnValue = Double.valueOf(secondValue).compareTo(Double.valueOf(firstValue));
        		
        	}        	
        	else if(columnType.equals("DATE") || columnType.equals("TIMESTAMP"))
        	{
        		
        		if(!firstValue.equals("") && firstValue != null && !secondValue.equals("") && secondValue != null)
        		{
        			Date date1 = new Date();
					Date date2 = new Date();
					try {
						if(columnType.equals("DATE")) 
						{
							//date1 = stringToDate(firstValue,"yyyy-MM-dd HH:mm:ss.S");
							//date2 = stringToDate(secondValue,"yyyy-MM-dd HH:mm:ss.S");
							date1 = stringToDate(firstValue,dateFormat);
							date2 = stringToDate(secondValue,dateFormat);
						}
						else if(columnType.equals("TIMESTAMP")) 
						{
							//date1 = stringToDate(firstValue,"yyyy-M-dd.H.m. s. S");
							//date2 = stringToDate(secondValue,"yyyy-M-dd.H.m. s. S");
							date1 = stringToDate(firstValue,dateFormat);
							date2 = stringToDate(secondValue,dateFormat);
						}
						
						if(date1 != null && date2 != null)
						{
							//myLogger.info(returnValue+" >>> COMPARE >>>>>>>>>>> firstValue : '"+firstValue+"' secondValue : '"+secondValue+"' sortType : "+sortType+" columnType : "+columnType+" key : "+key+ " dateFormat : "+dateFormat);
						    returnValue = date2.compareTo(date1);
							//myLogger.info("RESULT : "+returnValue);
						       
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
        		}
        		else
        		{
        			returnValue = -1;
        		}
        	}        	
        }*/
        
        
        return 0;
        //return returnValue;
    }
    
    public Date stringToDate(String strDate,String format) throws Exception {
    	//myLogger.info("stringToDate >>>>>>> strDate : "+strDate+" format : "+format);
		DateFormat sourceFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
		date = sourceFormat.parse(strDate);		
		//myLogger.info("strDate : "+strDate+" date"+date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//myLogger.info("error >>> stringToDate >>>>>>> strDate : "+strDate+" format : "+format);
			date = null;
		}
			
		return date;
	}
}