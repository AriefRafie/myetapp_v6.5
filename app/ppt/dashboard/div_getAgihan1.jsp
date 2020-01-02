
   <style>
    #boxAgihan1 {        
        position: absolute;       
        background: #eeeeee;       
        border: 1px solid #1a1a1a;       
		display: block;		
		z-index:2;
		
		/* CSS Box Shadow */
		-moz-box-shadow: 0 0 8px #000000;
		-webkit-box-shadow: 0 0 8px #000000;
		box-shadow: 0 0 8px #000000;		
		
		/* CSS Box Radius */
		-moz-border-radius: 5px;
		-webkit-border-shadow: 5px;
		border-radius: 5px;
      }	 
    </style>

#if($!div_getAgihan1_open == "Y")

<div id="log"  style="display:none"></div>
<div id="boxAgihan1" >

<input id="current_page_num" name="current_page_num" type="hidden" value="$!current_page_num">
<input type="hidden" name="last_page" value="$!total_page">
<input type="hidden" id="div_getAgihan1_open" name="div_getAgihan1_open" value="Y">
<table border="0" width="100%" > 
<tr>
<td align="left" valign="top"><img src="../img/drag.png" id="move" name="move" title='Ubah Lokasi' onMouseOver="this.style.cursor='move'" width="18" height="18" align="center"/>
</td>
<td align="right" valign="top"><a href="javascript:gotoAgihan1()" id="trigger">
<img  src="../img/validno.png" title='Tutup' align="center"/>
</a></td>
</tr>
</table>

<table border="0" width="100%"  class="nav"> 
<tr  >
<td valign="top" >
SENARAI FAIL (TUNGGU PENGESAHAN DAN AGIHAN)
 </td>
</tr>
</table>

<div id="scroll_boxAgihan1" >
<table border="0" width="100%" > 
 
   <tr class="table_header">
          
              <td scope="row" width="5%" align="center">BIL</td>
              <td width="50%">NO FAIL JKPTG</td>             
              <td width="45%" align="center">TARIKH PERMOHONAN DITERIMA</td>
              
                           
     
            </tr>
   #if($listAgihan.size()>0)
 			       #set( $i = 0 )   
           #foreach($list in $listAgihan)
           #if($list.ID_STATUS=="11" && $list.FLAG_SEMAK=="1" && $list.ID_SUBURUSAN=="51") 
         
         
                 #set( $i = $i + 1 ) 
             
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
           
          
           
          <tr>
            <td class="$row" align="center" valign="top">
     
			$i
            
            </td>
            
          	#set($status_fail = "$list.STATUS.toUpperCase()")
         	
         	#if($list.ID_STATUS=="11" && $list.FLAG_SEMAK=="1")
         	    #if($list.ID_SUBURUSAN=="51")
         	    	#set($status_fail = "<img src='../img/alert.gif' alt='' border='0'/><b><i>&nbsp;<blink>TUNGGU PENGESAHAN DAN AGIHAN</blink></i></b>")
         	    #else
         	    	#set($status_fail = "<img src='../img/alert.gif' alt='' border='0'/><b><i>&nbsp;<blink>TUNGGU PENGESAHAN</blink></i></b>")
         	    #end
         	#elseif($list.ID_STATUS=="127" && $list.FLAG_SEMAK=="2")
            	#set($status_fail = "<b><i><blink>TELAH DISAHKAN</blink></i></b>")
            #elseif($list.ID_STATUS=="16")
            	#set($status_fail = "<img src='../img/alert.gif' alt='' border='0'/><b><i>&nbsp;<blink>TUNGGU UNTUK DIAGIHAN</blink></i></b>")	
            #elseif($list.ID_STATUS=="148")
            	#set($status_fail = "<b><i><blink>TELAH DIAGIHKAN</blink></i></b>")	
            #elseif(($list.ID_STATUS=="132" && $list.FLAG_SEMAKAN_PENGARAH=="1") || ($list.ID_STATUS=="26" && $list.FLAG_SEMAKAN_PENGARAH=="1"))
            	#set($status_fail = "<img src='../img/alert.gif' alt='' border='0'/><b>&nbsp;<i><blink>TUNGGU SEMAKAN MMK</blink></i></b>")	
            #elseif(($list.ID_STATUS=="133" && $list.FLAG_SEMAKAN_PENGARAH=="2") || ($list.ID_STATUS=="26" && $list.FLAG_SEMAKAN_PENGARAH=="2"))
            	#set($status_fail = "<b><i><blink>TELAH DISEMAK</blink></i></b>")	
            	
            #elseif($list.ID_STATUS=="74" && $list.FLAG_MMK_PENARIKAN=="1")	
            	#set($status_fail = "<img src='../img/alert.gif' alt='' border='0'/><b>&nbsp;<i><span class='blink'>TUNGGU SEMAKAN MMK (PENARIKAN BALIK)</span></i></b>")		
            #elseif($list.ID_STATUS=="74" && $list.FLAG_MMK_PENARIKAN=="2" && $list.STATUS_KEPUTUSAN == "")	
            	#set($status_fail = "<b><i><blink>TELAH DISEMAK (PENARIKAN BALIK)</blink></i></b>")	
            #else
            	#set($status_fail = "$list.STATUS")
            #end
            
            
            
            
            <td class="$row">
            #if($list.NO_JKPTG == '')
            	<a href="javascript:papar('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('myrole')}')" class="style1">TIADA NO FAIL JKPTG </a>
            #else
            	<a href="javascript:papar('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('myrole')}')" class="style1">$list.NO_JKPTG.toUpperCase()</a>
            #end
             </td>
           
            
            <td class="$row"  align="center">$list.TARIKH_PERMOHONAN.toUpperCase()</td>
           
            
            </tr>            
            #end
            #end
            #else
            <tr >
            <td colspan="10">
            Tiada Rekod
            </td>
            </tr>
            #end
            
    </table>
</div>
   
</td>
</tr>
</table>
</div>

#end
 
  
   <script type="text/javascript">
      $jquery(function() {
		   var width  = 0;
		   var height = 0;			
		   var left = 0;
           var top  = 0;	  
		  
      $jquery(document).ready(function(e) {
		    $jquery('#boxAgihan1').show();
		    width  = 500;			
		    left = width+e.pageX;
            top  = height+e.pageY;	
			
			if(document.getElementById('boxAgihan1').clientHeight>250)
			{
		    height = 250;
			//$jquery("#boxAgihan1").css('width', width).css('height', height).css('overflow-y','scroll').css('top', top).css('left', left);
			$jquery("#boxAgihan1").css('width', width).css('height', height).css('top', top).css('left', left);	
			$jquery("#scroll_boxAgihan1").css('width', width).css('height', height-55).css('top', top).css('left', left).css('overflow-y','scroll');	
			}
			else
			{
			height = document.getElementById('boxAgihan1').clientHeight;	
			$jquery("#boxAgihan1").css('width', width).css('height', height).css('top', top).css('left', left);	
			}
		     
        });	
		
		/*
		 $jquery("div#boxAgihan1").mousemove(function(e) {
			 $jquery("div#boxAgihan1").css('top', e.pageY).css('left', e.pageX);
			});
		*/	
		
		
		var boxAgihan1 = $jquery("#boxAgihan1")
		boxAgihan1.offset({
			left: left,
			top: top
		});		
		var drag = {
			elem: null,
			x: 0,
			y: 0,
			state: false
		};
		var delta = {
			x: 0,
			y: 0
		};		
		$jquery("#move").mousedown(function(e) {
			//alert("mousedown");
			if (!drag.state) {
				drag.elem = this;
				//this.style.backgroundColor = '#f00';
				drag.x = e.pageX;
				drag.y = e.pageY;
				drag.state = true;
				
			
			}
			return false;
		});		
		$jquery(document).mousemove(function(e) {
			//alert("mousemove");
			if (drag.state) {
				var get_inside_top = drag.elem.style.top;
		
				delta.x = e.pageX - drag.x;
				delta.y = e.pageY - drag.y;
		
				//$jquery('#log').text(e.pageX + ' ' + e.pageY + ' ' + delta.x + ' ' + delta.y+'get_inside_top :'+get_inside_top);
				//$jquery("div#boxAgihan1").css('top', e.pageY).css('left', e.pageX);
				var cur_offset = $jquery(drag.elem).offset();
		
				$jquery(drag.elem).offset({
					left: (cur_offset.left + delta.x),
					top: (cur_offset.top + delta.y)	
								
				});
		
				//$jquery("div#boxAgihan1").css('top', e.pageY).css('left', e.pageX);
				drag.x = e.pageX;
				drag.y = e.pageY;
				$jquery("div#boxAgihan1").css('top', e.pageY).css('left', e.pageX);
			}
		});
		
		$jquery(document).mouseup(function() {
			//alert("mouseup");
			if (drag.state) {
				//drag.elem.style.backgroundColor = '#808';
				drag.state = false;
			}
		});

		
      });
    </script>


##parse("app/ppt/dashboard/script.jsp")