
<fieldset >
<legend>$legend_kronologi_log
[$legend_tempoh_log : 
#if($ID_STATUS!="16123")

<span id="ov_show_time_$ID_ADUANPUBLIC">$OVERALL_TIME_TAKEN</span>

#else

	$OVERALL_TIME_TAKEN
#end
]


</legend>
#set($percentTD = 100 / $listKronologi.size())
#set($displaySkrinKrono = "none")
#if($listKronologi.size()>0)
	#set($displaySkrinKrono = "")
#end


<script>
document.getElementById('divKronologi_$ID_ADUANPUBLIC').style.display = "$displaySkrinKrono";
</script>


<script>

$jquery(document).ready(function () {

var ov_show_day = 0;
var ov_show_hour = 0;
var ov_show_minute = 0;
var ov_show_second = 0;
//alert('2');
var lt_show_day = 0;
var lt_show_hour = 0;
var lt_show_minute = 0;
var lt_show_second = 0;
		
if('$ID_STATUS' != '16125' && '$ID_STATUS' != '16123' && '$ID_STATUS' != '')
{
		ov_show_day = parseInt('$ov_show_day');
		ov_show_hour = parseInt('$ov_show_hour');
		ov_show_minute = parseInt('$ov_show_minute');
		ov_show_second = parseInt('$ov_show_second');
		//alert('2');
		lt_show_day = parseInt('$lt_show_day');
		lt_show_hour = parseInt('$lt_show_hour');
		lt_show_minute = parseInt('$lt_show_minute');
		lt_show_second = parseInt('$lt_show_second'); 		
		
		//alert(ov_show_day+':'+ov_show_hour+':'+ov_show_minute+':'+ov_show_second+' ---- '+lt_show_day+':'+lt_show_hour+':'+lt_show_minute+':'+lt_show_second);
		//alert('3');
		
		//alert('refreshInterval_showtime 1 : '+typeof(refreshInterval_showtime));
		if (typeof(refreshInterval_showtime['$ID_ADUANPUBLIC']) != 'undefined' && refreshInterval_showtime['$ID_ADUANPUBLIC'] != null)
		{
			//alert('xclear');
			window.clearInterval(refreshInterval_showtime['$ID_ADUANPUBLIC']);
			//refreshInterval_showtime = null;
		}
		//alert('refreshInterval_showtime 2 : '+typeof(refreshInterval_showtime));
		refreshInterval_showtime['$ID_ADUANPUBLIC'] = 
		setInterval(function(){	
			
			var ov_show_time = "";
			ov_show_second = ov_show_second +1;
			if(ov_show_second>59)
			{
				ov_show_second = 0;
				ov_show_minute = ov_show_minute + 1;
			}
			if(ov_show_minute>59)
			{
				ov_show_minute = 0;
				ov_show_hour = ov_show_hour + 1;
			}
			if(ov_show_hour>23)
			{
				ov_show_hour = 0;
				ov_show_day = ov_show_day + 1;
			}
			//alert('1 : '+ov_show_second);
			if(ov_show_day>0)
			{
				if('$selectedLanguage'=='ENGLISH')
				{
					if(ov_show_day==1)
					{
						ov_show_time = ov_show_time +ov_show_day+' Day ';
					}
					else
					{
						ov_show_time = ov_show_time +ov_show_day+' Days ';
					}	
				}
				else
				{
					ov_show_time = ov_show_time +ov_show_day+' Hari ';
				}
			}
			//alert('1.1');
			if(ov_show_hour>0)
			{
				if('$selectedLanguage'=='ENGLISH')
				{
					if(ov_show_hour==1)
					{
						ov_show_time = ov_show_time +ov_show_hour+' Hour ';
					}
					else
					{
						ov_show_time = ov_show_time +ov_show_hour+' Hours ';
					}	
				}
				else
				{
					ov_show_time = ov_show_time +ov_show_hour+' Jam ';
				}
			}
			//alert('1.2');
			if(ov_show_minute>0)
			{
				if('$selectedLanguage'=='ENGLISH')
				{
					if(ov_show_minute==1)
					{
						ov_show_time = ov_show_time +ov_show_minute+' Minute ';
					}
					else
					{
						ov_show_time = ov_show_time +ov_show_minute+' Minutes ';
					}	
				}
				else
				{
					ov_show_time = ov_show_time +ov_show_minute+' Minit ';
				}
			}
			//alert('1.3');
			if(ov_show_second>0)
			{
				if('$selectedLanguage'=='ENGLISH')
				{
					if(ov_show_second==1)
					{
						ov_show_time = ov_show_time +ov_show_second+' Second ';
					}
					else
					{
						ov_show_time = ov_show_time +ov_show_second+' Seconds ';
					}	
				}
				else
				{
					ov_show_time = ov_show_time +ov_show_second+' Saat ';
				}
			}
			//alert('1.4 : $ID_ADUANPUBLIC');
			//alert('2 : '+document.getElementById('ov_show_time_$ID_ADUANPUBLIC').innerHTML);
			
			var ov_show_time_element =  document.getElementById('ov_show_time_$ID_ADUANPUBLIC');
			if (typeof(ov_show_time_element) != 'undefined' && ov_show_time_element != null)
			{
				ov_show_time_element.innerHTML = ov_show_time; 
			}
			
			
			var lt_show_time = "";
			lt_show_second = lt_show_second +1;
			if(lt_show_second>59)
			{
				lt_show_second = 0;
				lt_show_minute = lt_show_minute + 1;
			}
			if(lt_show_minute>59)
			{
				lt_show_minute = 0;
				lt_show_hour = lt_show_hour + 1;
			}
			if(lt_show_hour>23)
			{
				lt_show_hour = 0;
				lt_show_day = lt_show_day + 1;
			}
			
			if(lt_show_day>0)
			{
				if('$selectedLanguage'=='ENGLISH')
				{
					if(lt_show_day==1)
					{
						lt_show_time = lt_show_time +lt_show_day+' Day ';
					}
					else
					{
						lt_show_time = lt_show_time +lt_show_day+' Days ';
					}	
				}
				else
				{
					lt_show_time = lt_show_time +lt_show_day+' Hari ';
				}
			}
			
			if(lt_show_hour>0)
			{
				if('$selectedLanguage'=='ENGLISH')
				{
					if(lt_show_hour==1)
					{
						lt_show_time = lt_show_time +lt_show_hour+' Hour ';
					}
					else
					{
						lt_show_time = lt_show_time +lt_show_hour+' Hours ';
					}	
				}
				else
				{
					lt_show_time = lt_show_time +lt_show_hour+' Jam ';
				}
			}
			
			if(lt_show_minute>0)
			{
				if('$selectedLanguage'=='ENGLISH')
				{
					if(lt_show_minute==1)
					{
						lt_show_time = lt_show_time +lt_show_minute+' Minute ';
					}
					else
					{
						lt_show_time = lt_show_time +lt_show_minute+' Minutes ';
					}	
				}
				else
				{
					lt_show_time = lt_show_time +lt_show_minute+' Minit ';
				}
			}
			
			if(lt_show_second>0)
			{
				if('$selectedLanguage'=='ENGLISH')
				{
					if(lt_show_second==1)
					{
						lt_show_time = lt_show_time +lt_show_second+' Second ';
					}
					else
					{
						lt_show_time = lt_show_time +lt_show_second+' Seconds ';
					}	
				}
				else
				{
					lt_show_time = lt_show_time +lt_show_second+' Saat ';
				}
			}
			
			var lt_show_time_element =  document.getElementById('lt_show_time_$ID_ADUANPUBLIC');
			if (typeof(lt_show_time_element) != 'undefined' && lt_show_time_element != null)
			{
				lt_show_time_element.innerHTML = lt_show_time; 
			}
			
			
		}, 1000);

}
else
{
	if (typeof(refreshInterval_showtime['$ID_ADUANPUBLIC']) != 'undefined' && refreshInterval_showtime['$ID_ADUANPUBLIC'] != null)
	{
		window.clearInterval(refreshInterval_showtime['$ID_ADUANPUBLIC']);
	}
}



//alert('xxxxxxxxxxxxxxxxxx');
});
</script>




<!-- border-bottom: 1px dotted black;font-size:85%; border-top: 1px dotted black;font-size:85%; -->
<table width="100%" border="0"  cellpadding="0" cellspacing="0"  >
#set($percentTD = $percentTD)
#foreach ($N in $listKronologi)	
#set($blinkClass = "")	
		
		<td align="center" width="$percentTD%" valign="top" style=""  >        
        #if($N.FLAG_AKTIF == "1" && $N.ID_KRONOLOGI != "")
        #set($blinkClass = "blink")
        #set($src = "../img/"+$N.BIL+"current.png" )
        #elseif($N.FLAG_AKTIF == "0" && $N.ID_KRONOLOGI != "")
        #set($src = "../img/"+$N.BIL+"enable.png" )
        #else
        #set($src = "../img/"+$N.BIL+"disable.png" )
        #end
        
        
        <img title="" class="$blinkClass"   src="$src" border="0">
        <br />
        <span class="font_tajuk_sub $blinkClass" >$N.KETERANGAN</span> 
        <br />
        #if($N.TARIKH_MASUK != "")
        $N.TARIKH_MASUK <br />
        #end
        
        
        #if($N.LAST_AKTIVITI == "false")
            #if($N.TIME_TAKEN != "")
            $N.TIME_TAKEN
            #end
        #else
            <span id="lt_show_time_$ID_ADUANPUBLIC">
            #if($N.TIME_TAKEN != "")
                $N.TIME_TAKEN
            #end
            </span>
        #end  
         
        
        
        
        </td>  
                                                   
#end
</table>




</fieldset>

#if($view.ID_ADUANPUBLIC!="")
<script>
if( $jquery('#div_row$ID_ADUANPUBLIC').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#div_row$ID_ADUANPUBLIC').offset().top - 20);
}
</script>
#else
<script>
if( $jquery('#div_row').length )         // use this if you are using id to check
{
	   window.scrollTo(0, $jquery('#div_row').offset().top);
}
</script>
#end

<script>		
  $jquery(document).ready(function () {
	 doDivAjaxCall3$formname('div_SenaraiNotifikasi','showListNotifikasi','FLAG_NOTIFIKASI=');			 	  
  });		
</script>		