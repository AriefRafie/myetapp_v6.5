 

#if($OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CARIAN').style.display = "";
	document.getElementById('span_LINK_CARIAN').style.display = "none";	
</script>
#end
#if($OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CARIAN').style.display = "none";
	document.getElementById('span_LINK_CARIAN').style.display = "";
</script>
#end


#if($FLAG_CARIAN=="Y")
	<script>
	if( $jquery('#div_CARIAN').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_CARIAN').offset().top-10);
	}
	</script>
#else
<!--
:::::: div_Senarai$JENISSUB$ID_WARTATRMINDUK
-->
	<script>
	if( $jquery('#div_Senarai$JENISSUB$ID_WARTATRMINDUK').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_Senarai$JENISSUB$ID_WARTATRMINDUK').offset().top-10);
	}
	</script>
#end
<!--
:::::: LOCATION : $LOCATION <BR />
:::::: [JENISSUB : $JENISSUB]
[ID_WARTATRMINDUK : $ID_WARTATRMINDUK]
-->


#if($command=="showSenaraiBatal" || $command=="showSenaraiGanti")

#set($TAJUKSUB="")
#set($setJENISWARTA="")
#set($NAMABUTTONSUB="")
#if($command=="showSenaraiBatal")
	#set($TAJUKSUB="SENARAI WARTA PEMBATALAN")
    #set($setJENISWARTA="B")
    #set($NAMABUTTONSUB="Tambah Warta Pembatalan")
    #set($divSenaraiGanti = "div_Senarai"+"G"+$ID_WARTATRMINDUK) 
    #if($list.size()>0)
    <script>
	//alert('1 : $divSenaraiGanti');
	document.getElementById('$divSenaraiGanti').style.display = "";
    </script>
    #else
    <script>
	//alert('2 : $divSenaraiGanti');
	document.getElementById('$divSenaraiGanti').style.display = "none";
    </script>
    #end
    
#elseif($command=="showSenaraiGanti")
	#set($TAJUKSUB="SENARAI WARTA PENGGANTIAN")
    #set($setJENISWARTA="G")
    #set($NAMABUTTONSUB="Tambah Warta Penggantian")
    #if($list.size()>0)
    <script>
	//alert('1 : $divSenaraiGanti');
	document.getElementById('$divSenaraiGanti').style.display = "";
    </script>
    #else
    <script>
	//alert('2 : $divSenaraiGanti');
	//document.getElementById('$divSenaraiGanti').style.display = "none";
    </script>
    #end
#end


<br />
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		<td width="2%" class="underline_td_sub">
		</td>
		<td width="38%" class="underline_td_sub">
		<strong>$TAJUKSUB</strong>		
		</td>
		<td width="38%" class="underline_td_sub" align="right" valign="top" >
		</td>
		<td width="21%" align="right" class="underline_td_sub">
        
         <input type="button" id="BTNADD$JENISSUB$ID_WARTATRMINDUK"  name="BTNADD$JENISSUB$ID_WARTATRMINDUK" 
                    onclick="document.getElementById('div_row$JENISSUB$ID_WARTATRMINDUK').style.display=''; doDivAjaxCall3$formname('div_row$JENISSUB$ID_WARTATRMINDUK','add','ID_WARTATRM=&mode=edit&commandFrom=form&setJENISWARTA=$setJENISWARTA&ID_WARTATRMINDUK=$ID_WARTATRMINDUK&JENISSUB=$JENISSUB');" value="$NAMABUTTONSUB" />                        
        </td>
        <td width="2%" class="underline_td_sub" ></td>
		</tr>
		</table>
        <table width="100%" id="div_row$JENISSUB$ID_WARTATRMINDUK" style="display:none">
        <tr >
        </tr>
        </table>
#end


<fieldset id="">
<table width="100%" align="center">
<tr>
<td>	
	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($list.size()>0)
		<script>
        var butoncetak =  document.getElementById('cmdCetak');
        if (typeof(butoncetak) != 'undefined' && butoncetak != null)
        {
            butoncetak.style.display = "";
        }
        </script>
        <tr >
               <td  align="left" valign="top" colspan="14" >
               #parse("app/pdt/trm/record_paging.jsp")
               </td>		     
        </tr>
	#end 
	<tr class="table_header" >
		   <td   align="center" valign="top" width="3%" >Bil</td>
           <td   align="left" valign="top" width="">No. Warta</td>
           <td   align="center" valign="top" width="">Tarikh Warta</td>
		   <td   align="left" valign="top" width="">Negeri</td>
		   <td   align="left" valign="top" width="">Daerah / Jajahan</td>
           <td   align="left" valign="top" width="">Bandar / Pekan / Mukim</td>
           <!--
           <td   align="left" valign="top" width="">Kawasan</td>
		   <td   align="left" valign="top" width="">No. Pelan</td>
           -->
           <td   align="left" valign="top" width="">Luas Asal (Hektar)</td>
           <td   align="left" valign="top" width="">Luas (Hektar)</td>
           <td   align="left" valign="top" width="">Jenis Warta</td>
           <td   align="left" valign="top" width="">Status Warta</td>
		   <td   align="left" valign="top"width="">Catatan</td>	   
	</tr>
	#if($list.size()>0)    
		#foreach($view in $list)	
		<tr id="div_row$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" >
			   <td class="$view.rowCss"  align="center" valign="top" >$view.BIL
			   
			   </td>
			   <td class="$view.rowCss"  align="left" valign="top">			   
			   #set($span1 = "span1list"+$view.BIL)
			   <span id="$span1"> 
               <a href="javascript:document.getElementById('div_row$JENISSUB$ID_WARTATRMINDUK').innerHTML ='';doDivAjaxCall$formname('div_row$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM','view','ID_WARTATRM=$view.ID_WARTATRM&BIL=$view.BIL&rowCss=$view.rowCss&mode=view&commandFrom=list&ID_WARTATRMINDUK=$ID_WARTATRMINDUK&JENISSUB=$JENISSUB');">
			   <font color='blue' class="font_tajuk_sub" >	
			   <u>$view.NO_WARTA</u>              		   
			   </font>
			   </a>
			   </span>			
			   <script>highlight_item('$CR_NO_WARTA','$span1')</script>
			   </td>
               
               <td class="$view.rowCss"  align="center" valign="top">			   
			  
               
               
               #set($span9= "span9list"+$view.BIL)	   
			   <span id="$span9">$view.TARIKH_WARTA</span>
               #if($CR_TARIKH_MULA != "" || $CR_TARIKH_AKHIR!= "")
               <script>			   
			   //highlight_item(returnDropDownSelectedText_value('CR_ID_MUKIM'),'$span4');
			   highlight_item('$view.TARIKH_WARTA','$span9')
               </script>
               #end
               
			   </td>	
               		   
			   <td class="$view.rowCss"  align="left" valign="top">			   
			   #set($span2= "span2list"+$view.BIL)	   
			   <span id="$span2">$view.NEGERI</span>
               #if($CR_ID_NEGERI != "")
               <script>			   
			   highlight_item(returnDropDownSelectedText_value('CR_ID_NEGERI'),'$span2');
               </script>
               #end
			   </td>
               
               <td class="$view.rowCss"  align="left" valign="top">			   
			   #set($span3= "span3list"+$view.BIL)	   
			   <span id="$span3">$view.DAERAH</span>
               #if($CR_ID_DAERAH != "")
               <script>			   
			   highlight_item(returnDropDownSelectedText_value('CR_ID_DAERAH'),'$span3');
               </script>
               #end
			   </td>
               
               <td class="$view.rowCss"  align="left" valign="top">			   
			   #set($span4= "span4list"+$view.BIL)	   
			   <span id="$span4">$view.MUKIM</span>
               #if($CR_ID_MUKIM != "")
               <script>			   
			   highlight_item(returnDropDownSelectedText_value('CR_ID_MUKIM'),'$span4');
               </script>
               #end
			   </td>
               
              
               <!--
               <td class="$view.rowCss"  align="left" valign="top">		
               #set($span5= "span5list"+$view.BIL)	   
			   <span id="$span5">$view.KAWASAN</span>
               #if($CR_KAWASAN != "")
               <script>	
			   highlight_item('$CR_KAWASAN','$span5')		   
			   </script>
               #end
			   </td>
               
               <td class="$view.rowCss"  align="left" valign="top">		
               #set($span6= "span6list"+$view.BIL)	   
			   <span id="$span6">$view.NO_PELAN</span>
               #if($CR_NO_PELAN != "")
               <script>	
			   highlight_item('$CR_NO_PELAN','$span6')		   
			   </script>
               #end
			   </td>
               -->
               
               <td class="$view.rowCss"  align="left" valign="top">		
               #if($view.FLAG_JENISWARTA == "W")
               #if($view.LUAS_ASAL < 0)
                            <font color="red"><b>$view.LUAS_ASAL_DISPLAY</b></font>
                    #else
                        $view.LUAS_ASAL_DISPLAY
                    #end
               #end
			   </td>
               
               <td class="$view.rowCss"  align="left" valign="top">		
               #if($view.LUAS < 0)
                            <font color="red"><b>$view.LUAS_DISPLAY</b></font>
                    #else
                        $view.LUAS_DISPLAY
                    #end
			   </td>
               
               <td class="$view.rowCss"  align="left" valign="top">			   
			   #set($span7= "span7list"+$view.BIL)	   
			   <span id="$span7">$view.JENISWARTA</span>
               #if($CR_FLAG_JENISWARTA != "")
               <script>			   
			   highlight_item(returnDropDownSelectedText_value('CR_FLAG_JENISWARTA'),'$span7');
               </script>
               #end
               
             
               
               <!--
               #if($view.NO_WARTA_GANTI!="")
               <BR />[PENGGANTIAN $view.NO_WARTA_GANTI]
               #end
               
               #if($view.NO_WARTA_BATAL!="")
               <BR />[PEMBATALAN $view.NO_WARTA_BATAL]
               #end
               -->
               
			   </td>
               
               <td class="$view.rowCss"  align="left" valign="top">			   
			   #set($span8= "span8list"+$view.BIL)	   
			   <span id="$span8">$view.STATUSWARTA</span>
               #if($CR_FLAG_STATUSWARTA != "")
               <script>			   
			   highlight_item(returnDropDownSelectedText_value('CR_FLAG_STATUSWARTA'),'$span8');
               </script>
               #end
			   </td>
               
             
              
			   
			   
			   <td class="$view.rowCss"  align="left" valign="top">
               
               #if($view.NO_WARTA_ASAL != "")
               		#if($view.FLAG_JENISWARTA == "B")
               		$view.JENISWARTA : $view.NO_WARTA_ASAL<br />
                    #else
                    PENGGANTIAN : $view.NO_WARTA_ASAL<br />
                    #end
               #end
             
               #if($view.KOSONG>0)               
               <span class="blink" ><font color="red"><b>$view.KOSONG_DISPLAY HEKTAR TIDAK DIGANTI</b></font></span><br />
               #end
               <!--
               <a href="javascript:if(confirm('$label_adakah_pasti_delete')){ doDivAjaxCall$formname('div_Senarai','delete','ID_WARTATRM=$view.ID_WARTATRM');}"><img title="$label_hapus"  src="../img/hapus.gif" border="0"></a>	
               -->   		  
			   </td>	   
		</tr>
		
		#end
      
	#else
	<tr >
		   <td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
		     
	</tr>
    <script>
	var butoncetakCT =  document.getElementById('cmdCetak');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "none";
    }
	</script>
	#end
	</table>


</td>
</tr>
</table>
</fieldset>
<!--::::: $command-->

#set($divSenarai = "div_Senarai"+"G"+$ID_WARTATRMINDUK) 
<!--$divSenarai -->
#if($command=="showSenaraiBatal")
<script>
//alert('1');
var id_w = '$ID_WARTATRM';
if($ID_WARTATRMINDUK!="")
{
	id_w = '$ID_WARTATRMINDUK';
}

var LUASASALINDUK = document.getElementById("LUAS_"+id_w).value;
//alert('LUASASALINDUK : '+LUASASALINDUK);

if(LUASASALINDUK!="")
{
	//alert("**** BTNADD$JENISSUB$ID_WARTATRMINDUK$ID_WARTATRM");
	if(parseFloat(LUASASALINDUK)<=0)
	{
		document.getElementById("BTNADD$JENISSUB$ID_WARTATRMINDUK").style.display = "none";
	}
	else
	{
		document.getElementById("BTNADD$JENISSUB$ID_WARTATRMINDUK").style.display = "";
	}
}	


//alert(id_w);

$jquery(document).ready(function () {
 doDivAjaxCall$formname('$divSenarai','showSenaraiGanti','ID_WARTATRMINDUK='+id_w+'&LOCATION=$divSenarai&JENISSUB=G&commandFrom=$commandFrom&BIL=$BIL&rowCss=$rowCss'); 
});

</script>	
#elseif($mode == "view" && $ID_WARTATRMINDUK != "" && $JENISSUB != "" && $command=="showSenaraiGanti")
<script> 
//alert(1);  
$jquery(document).ready(function () {
doDivAjaxCall$formname('div_ListHISTORY$ID_WARTATRMINDUK','displayUtamaHISTORY','&carianTerperinciHISTORY=&TARIKH_MULA_HISTORY=&TARIKH_AKHIR_HISTORY=&ID_WARTATRM=$ID_WARTATRMINDUK&FLAG_DEFAULT_HISTORY=Y&ID_WARTATRMINDUK=$ID_WARTATRMINDUK&JENISSUB=$JENISSUB&commandFrom=$commandFrom&BIL=$BIL&rowCss=$rowCss');		 	  
});				  
</script>	
#end
