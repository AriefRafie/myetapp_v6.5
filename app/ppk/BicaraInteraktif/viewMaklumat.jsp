#if($htmlSkrinMaklumat != "")

	
    #if($scrolPosition!="" && $command != "showSenarai")
     <script>
     setPageLocation('$scrolPosition');
     </script>
    #end
    
    <!--
    table { page-break-inside:auto }
			tr    { page-break-inside:avoid; page-break-after:auto;  }
			
    -->
    
    
    <div class="viewMaklumatTR"  >
    
    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="margin-top:5px;margin-bottom:5px" >
    <tr  >
    <td width="2%" >
    </td>
    <td width="96%" >
        #if($flagPrint == "Y")
        <body onload="window.print()" >   
        <style>
		.onTT {display:none;}
    	@media print{ 		
			.tableMain {page-break-inside:auto !important;}
			.trMain {page-break-inside:auto !important;}
			table { page-break-inside:auto }
			tr    { page-break-inside:avoid; page-break-after:auto;  }
			.autoBreak { page-break-inside:avoid; page-break-before:auto;  }
			.onTT {display:block;}
			.divKeteranganPrint {
				border:1px solid black;
				width:99%	!important;
				padding:5px !important;
			}
		} 	
    	</style>
        
        
        <link rel="stylesheet" type="text/css" media="screen print" href="../../bootstrap-wysihtml5-master/lib/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" media="screen print" href="../../bootstrap-wysihtml5-master/lib/css/prettify.css" />
        <link rel="stylesheet" type="text/css" media="screen print" href="../../bootstrap-wysihtml5-master/src/bootstrap-wysihtml5.css" />
		<!--
        <table border="0" width="100%">
           <thead>
            <tr>
             <th style="width:100%"><br>&nbsp;</th>
           </tr>          
        </thead> 
        </table>
    	<tr>
        <td width="100%">  
        -->   
        <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="tableMain"  >
        <thead ><tr class="trMain"><td valign="top"  align="right" colspan="10" >$NO_FAIL<br /><br /></td></tr></thead>
        <tr  class="trMain"><td>        
        	#parse("app/ppk/BicaraInteraktif/viewHeaderPrint.jsp")       
        #end
                	
        	$htmlSkrinMaklumat
            
         #if($flagPrint == "Y")
         </td>
         </tr>
         </table>
         <!--
           </td>
           </tr>
         </tbody>
        </table>        
        <table id="header" width="100%">
          <tr>
            <td width="100%" align="right">
             no_fail
            </td>
          </tr>
        </table>
        -->
        </body>
        #end
        
    </td>
    <td width="2%" >
    </td>
    </tr>
    </table>
   
    
    
    </div>
    
    <!--
    skrinName ::: $!skrinName <br />
    current_previous ::: $!current_previous <br />
    divId ::: $!divId
    -->
   
    
    
    
    <script>
	var skrinName = "$!skrinName";
	var current_previous = "$!current_previous";
	var divId = "$!divId";
	var divLocation ="";
	var flagDisable = document.getElementById("flagDisable").value;
	var adaMasalah = document.getElementById("adaMasalah").value;
	var JAGAAN_MATCH = document.getElementById("JAGAAN_MATCH").value;
	var LEPASTARIKHHARINI = document.getElementById("LEPASTARIKHHARINI").value;	
	var PEGAWAIBICARAASLOGIN = document.getElementById("PEGAWAIBICARAASLOGIN").value;	
	
	if(divId != "")
	{
		divLocation = divId;
	}
	else
	{
		if(skrinName=="pemohon" || skrinName=="simati" ||  skrinName=="perubahan"  ||  skrinName=="keteranganhadir" || skrinName=="bantahan" || skrinName=="keputusan")
		{
			divLocation ="view_"+skrinName;
		}
		else if(skrinName!="pemohon" && skrinName!="simati"  && (current_previous == "current" || current_previous == "previous"))
		{
			divLocation ="senarai_"+skrinName+current_previous;			
		}
		
	}
	
	//alert(divLocation + "ADA CETAK : "+$jquery("#"+divLocation+":contains('Cetak')").length);
	//alert(divLocation + "ADA CETAK : "+$jquery("#"+div).find("button:contains('Cetak')").length);
	//$jquery("#"+divLocation+" :button").addClass("disabledbutton");
	//$jquery("#"+divLocation+" input[value=Cetak]").removeClass("disabledbutton");
	
	//alert("flagDisable : "+flagDisable+" divLocation : "+divLocation+" skrinName : "+skrinName);
	if(flagDisable == "Y" && divLocation != "" 
	&& skrinName != "keputusan"
	)
	{
		/*
		//alert('LEPAS');
		if(skrinName != "keputusan")
		{
			offIconByClass("buttonHapus");
			disableInput(divLocation);
		}
		else
		{
			//alert("adaMasalah : "+adaMasalah);
			if(adaMasalah=="Y")
			{
				$jquery("#"+divLocation).html("<div class=\"viewMaklumatTR\"><font color=\"red\" ><strong class=\"blink\" style=\"color:red\">PERHATIAN!</strong> DIDAPATI DATA PADA PERBICARAAN INI BERMASALAH, SKRIN KEPUTUSAN PERBICARAAN PERLU DISEKAT SEHINGGA DATA PERBICARAAN INI DIBAIK PULIH.</font></div>");
			}
		}
		*/
		
		offIconByClass("buttonHapus");
		disableInput(divLocation);
		
				
	}
	
	if(skrinName == "keputusan")
	{
		
		var mesej = "";
		if(adaMasalah=="Y")
		{
			mesej += "<br><font color=\"red\" ><strong class=\"blink\" style=\"color:red\">PERHATIAN!</strong> DIDAPATI DATA PADA PERBICARAAN INI BERMASALAH, SKRIN KEPUTUSAN PERBICARAAN PERLU DISEKAT SEHINGGA DATA PERBICARAAN INI DIBAIK PULIH.</font>";
			
		}
		/*
		if(parseInt(JAGAAN_MATCH)==0)
		{
			mesej += "<br><font color=\"red\" ><strong class=\"blink\" style=\"color:red\">PERHATIAN!</strong> DIDAPATI DAERAH MOHON UNTUK PERMOHONAN INI BUKAN DIBAWAH DAERAH JAGAAN UNIT ANDA, SILA MEMDAFTAR PERMOHONAN MEMBANTU UPP <a class=\"red\" href=\"javascript:goToBU()\"><u> <b>DISINI</b></u></a></font>";
		}
		*/
		if(LEPASTARIKHHARINI=="Y")
		{
			mesej += "<br><font color=\"red\" ><strong class=\"blink\" style=\"color:red\">PERHATIAN!</strong> MAKLUMAT PERBICARAAN HANYA BOLEH DIKEMASKINI PADA HARI PERBICARAAN DAN KEATAS SAHAJA</font>";
		}
		
		
		if(PEGAWAIBICARAASLOGIN!="Y" || parseInt(JAGAAN_MATCH)==0)
		{
			//alert("1"+divLocation);
			//offIconByClass("buttonHapus");
			//disableInput(divLocation);
			//alert("2");
			$jquery("#view_keputusan input[id=cmdSimpankeputusan]").hide();		
			$jquery("#view_keputusan input[id=cmdKemaskinikeputusan]").hide();	
			
		}
		
		
		if(adaMasalah=="Y" 
		//|| parseInt(JAGAAN_MATCH)==0 
		|| LEPASTARIKHHARINI=="Y")
		{
			$jquery("#"+divLocation).html("<div class=\"viewMaklumatTR\">"+mesej+"<br><br></div>");
		}
		
	}
	
	</script>

#end

<!-- 
::: $div
::: $command
::: $skrinName
-->
#if($div != "" && $command != "showSenarai" && $skrinName != "keputusan")
 <script>
 $jquery(document).ready(function () {
     //alert('x');
     //divToTop("view_keputusan");
     $jquery('#'+'$div').scrollView();
     //alert('x2');
 });	 
 </script>    
#end