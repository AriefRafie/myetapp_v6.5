
   <style>
    #boxOnlineSementara {        
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
    
    
	  


         
 
#if($!div_getOnlineSementara_open == "Y")




<div id="log"  style="display:none"></div>
<div id="boxOnlineSementara" >

<input id="current_page_num" name="current_page_num" type="hidden" value="$!current_page_num">
<input type="hidden" name="last_page" value="$!total_page">
<input type="hidden" id="div_getOnlineSementara_open" name="div_getOnlineSementara_open" value="Y">
<table border="0" width="100%" > 
<tr>
<td align="left" valign="top"><img src="../img/drag.png" id="move" name="move" title='Ubah Lokasi' onMouseOver="this.style.cursor='move'" width="18" height="18" align="center"/>
</td>
<td align="right" valign="top"><a href="javascript:gotoSekSementara()" id="trigger">
<img  src="../img/validno.png" title='Tutup' align="center"/>
</a></td>
</tr>
</table>

<table border="0" width="100%"  class="nav"> 
<tr  >
<td valign="top" >
PERMOHONAN ONLINE PENGGUNAAN/PENDUDUKAN SEMENTARA
 </td>
</tr>
</table>

<div id="scroll_boxOnlineSementara">

<table border="0" width="100%" > 
 
   <tr class="table_header">
   
          
              <td scope="row" width="5%" align="center" style="text-transform:uppercase">Bil</td>
              <td width="55%" style="text-transform:uppercase">No. Rujukan Online</td>                       
              <td width="40%" style="text-transform:uppercase"><div align="center">Tarikh Permohonan Diterima</div></td>
             
             
              
            </tr>
   
           #if($listing_online_sementara.size()>0)
           #foreach($list in $listing_online_sementara)
            #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
                
          <tr>
            <td class="$row" align="center">
     $i
			
            </td>
            
            #set($flagStatusOnline="")
            #if($list.FLAG_STATUS_ONLINE=="1")
            	 #set($flagStatusOnline="<br/><font style='font-size:10px'><b><i>(PERMOHONAN TELAH DIKEMBALIKAN)</i></b></font>")
            #else
            	 #set($flagStatusOnline="")
            #end
            
            <td class="$row">
            #if($list.NO_PERMOHONAN_ONLINE == '')
            	<a href="javascript:papar('$list.ID_PERMOHONAN','138','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">TIADA NO FAIL ONLINE </a>
            #else
            	<a href="javascript:papar('$list.ID_PERMOHONAN','138','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$!list.NO_PERMOHONAN_ONLINE.toUpperCase() $!flagStatusOnline</a>
            #end
             </td>
           
            <td class="$row"><div align="center">$list.TARIKH_PERMOHONAN</div></td>
           
            
            </tr>
            
            #end
            
             #else
            <tr >
            <td colspan="5">
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
 function carian()
 {
	doDivAjaxCall$formname('div_getOnlineSementara','getOnlineSementara_carian','');	 
 }
 </script>
  
   <script type="text/javascript">
      $jquery(function() {
		   var width  = 0;
		   var height = 0;			
		   var left = 0;
           var top  = 0;	  
		  
      $jquery(document).ready(function(e) {
		  $jquery('#boxOnlineSementara').show();
		  width  = 500;			
		    left = width+e.pageX;
            top  = height+e.pageY;				
			if(document.getElementById('boxOnlineSementara').clientHeight>250)
			{
		    height = 250;
			//$jquery("#boxOnlineSementara").css('width', width).css('height', height).css('overflow-y','scroll').css('top', top).css('left', left);
			$jquery("#boxOnlineSementara").css('width', width).css('height', height).css('top', top).css('left', left);	
			$jquery("#scroll_boxOnlineSementara").css('width', width).css('height', height-55).css('top', top).css('left', left).css('overflow-y','scroll');	
				
			}
			else
			{
			height = document.getElementById('boxOnlineSementara').clientHeight;	
			$jquery("#boxOnlineSementara").css('width', width).css('height', height).css('top', top).css('left', left);	
			}        
        });	
		
		/*
		 $jquery("div#boxOnlineSementara").mousemove(function(e) {
			 $jquery("div#boxOnlineSementara").css('top', e.pageY).css('left', e.pageX);
			});
		*/	
		
		
		var boxOnlineSementara = $jquery("#boxOnlineSementara")
		boxOnlineSementara.offset({
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
				//$jquery("div#boxOnlineSementara").css('top', e.pageY).css('left', e.pageX);
				var cur_offset = $jquery(drag.elem).offset();
		
				$jquery(drag.elem).offset({
					left: (cur_offset.left + delta.x),
					top: (cur_offset.top + delta.y)	
								
				});
		
				//$jquery("div#boxOnlineSementara").css('top', e.pageY).css('left', e.pageX);
				drag.x = e.pageX;
				drag.y = e.pageY;
				$jquery("div#boxOnlineSementara").css('top', e.pageY).css('left', e.pageX);
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