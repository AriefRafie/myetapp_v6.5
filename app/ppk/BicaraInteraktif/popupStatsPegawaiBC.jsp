<script type="text/javascript" src="../../library/js/jquery-1.7.2.min.js" ></script>
<script>var $jquery = jQuery.noConflict();</script>
<!--
$listStatsJumlahBicara
-->
<br />


#if($flagPrint == "Y")
        <body onload="window.print()">      
    	<style>
		.onTT {display:none;}
    	@media { 
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

		#parse("app/ppk/BicaraInteraktif/viewHeaderPrint.jsp")       
#end

	
	
    #if($listStatsJumlahBicara.size()>0)
    	#set($tarikhDisplay = "")	
        #set($negeriDisplay = "")	
		#foreach($pr in $listStatsJumlahBicara) 
        
          	TARIKH_BICARA_DISPLAY :: $pr.TARIKH_BICARA_DISPLAY
            ID_NEGERI :: $pr.ID_NEGERI	
            layer :: $pr.LAYER1
            tarikhDisplay :: $tarikhDisplay
            negeriDisplay :: $negeriDisplay
            <br />
        	<!--       
            #if($pr.LAYER1 == "1")
            <tr>
            <td colspan="30" style="border: 1px solid black;">
            <b>$pr.TARIKH_BICARA_DISPLAY</b>
            </td>
            </tr>
            #elseif($pr.LAYER1 == "2")
            <tr>
            <td colspan="30" style="border: 1px solid black;">
            <b>$pr.NAMA_NEGERI</b>
            </td>
            </tr>
            #elseif($pr.LAYER1 == "3")  
            #end 
            -->   
           
           
            <!--
                      
                #if(($tarikhDisplay == "" && $negeriDisplay == "") || (($tarikhDisplay != $pr.TARIKH_BICARA_DISPLAY || $negeriDisplay != $pr.ID_NEGERI)))
                
               
                
                #set($tarikhDisplay = $pr.TARIKH_BICARA_DISPLAY)	
                #set($negeriDisplay = $pr.ID_NEGERI)
                
                
                open<br />
                
                #if($tarikhDisplay != "" && $negeriDisplay != "")
                </table><br /><br />
                close<br />           
                #end
                
               
                
                <table style="border-collapse:collapse;"  cellspacing="1" cellpadding="1"  align="center"  width="98%">
                <tr >
                       <td rowspan="2"   align="left" valign="top" style="border: 1px solid black;" width="25%" ><b>Nama Pegawai</b></td>
                       <td rowspan="2"   align="center" valign="top" style="border: 1px solid black;"  width="8.5%" ><b>Jumlah<br />Perbicaraan</b></td>
                       <td   align="center" valign="top" style="border: 1px solid black;" colspan="19" width="66.5%"><b>Waktu Bicara</b></td>         
                </tr> 
                <tr >
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;" width="3.5%">0830<br />0859</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;" width="3.5%">0900<br />0929</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;" width="3.5%">0930<br />0959</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;" width="3.5%">1000<br />1029</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;" width="3.5%">1030<br />1059</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;" width="3.5%">1100<br />1129</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;" width="3.5%">1130<br />1159</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;" width="3.5%">1200<br />1229</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;" width="3.5%">1230<br />1259</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;" width="3.5%">0100<br />0129</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;" width="3.5%">0130<br />0159</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;" width="3.5%">0200<br />0229</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;" width="3.5%">0230<br />0259</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;" width="3.5%">0300<br />0329</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;" width="3.5%">0330<br />0359</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;" width="3.5%">0400<br />0429</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;" width="3.5%">0430<br />0459</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;" width="3.5%">0500<br />0529</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;" width="3.5%">0530<br />0559</td>          
                </tr> 
             
                #else 
                 
            TARIKH_BICARA_DISPLAY :: $pr.TARIKH_BICARA_DISPLAY
            ID_NEGERI :: $pr.ID_NEGERI	
            layer :: $pr.LAYER1
            tarikhDisplay :: $tarikhDisplay
            negeriDisplay :: $negeriDisplay
            <br />
                    
                    <tr >
                       <td   align="left" valign="top" style="border: 1px solid black;font-size:60%;" >$pr.NAMA_PEGAWAI</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.JUMLAH_PERBICARAAN</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.T0830AM_0859AM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.T0900AM_0929AM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.T0930AM_0959AM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.T1000AM_1029AM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.T1030AM_1059AM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.T1100AM_1129AM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.T1130AM_1159AM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.T1200PM_1229PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.T1230PM_1259PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.T0100PM_0129PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.T0130PM_0159PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.T0200PM_0229PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.T0230PM_0259PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.T0300PM_0329PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.T0330PM_0359PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.T0400PM_0429PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.T0430PM_0459PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.T0500PM_0529PM</td>
                       <td   align="center" valign="top" style="border: 1px solid black;font-size:60%;">$pr.T0530PM_0559PM</td>          
                    </tr>
                    
                #end
           -->
            
        #end
    #end

