
   <style>
    #boxListFail {        
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


#if($!div_getListFail_open == "Y")

	<div id="log"  style="display:none"></div>
	<div id="boxListFail" >
	
	<input id="current_page_num" name="current_page_num" type="hidden" value="$!current_page_num">
	<input type="hidden" name="last_page" value="$!total_page">
	<input type="hidden" id="div_getListFail_open" name="div_getListFail_open" value="Y">
	<table border="0" width="100%" > 
	<tr>
	<td align="left" valign="top"><img src="../img/drag.png" id="move" name="move" title='Ubah Lokasi' onMouseOver="this.style.cursor='move'" width="18" height="18" align="center"/>
	</td>
	<td align="right" valign="top"><a href="javascript:doGetListFail()" id="trigger">
	<img  src="../img/validno.png" title='Tutup' align="center"/>
	</a></td>
	</tr>
	</table>
	
	<table border="0" width="100%"  class="nav"> 
	<tr  >
	<td valign="top" >
	SENARAI FAIL
	 </td>
	</tr>
	</table>
	
	<div id="scroll_boxListFail" >
	<table border="0" width="100%" > 
	 
	   <tr class="table_header">
	          
	              <td scope="row" width="5%" align="center">BIL</td>
	              <td width="25%">NO FAIL JKPTG</td>             
	              <td width="40%">TAJUK FAIL</td>
	              <td width="30%">STATUS</td>
	                           
	     
	            </tr>
	   #if($listFail.size()>0)
	 			       #set( $i = 0 )   
	           #foreach($list in $listFail)
	         
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
	       
	            
	            
	            
	            <td class="$row" valign="top">
	             #set($span1 = "span1"+$i)
				<span id="$span1">  
	            
	            
	            #if($list.NO_JKPTG == '')
	            	<a href="javascript:papar('$list.id_fail','$list.id_urusan','$list.id_status','$list.id_permohonan','$list.id_htppermohonan','$list.no_fail')" class="style1">TIADA NO FAIL JKPTG</a>
	            #else
	            	<a href="javascript:papar('$list.id_fail','$list.id_urusan','$list.id_status','$list.id_permohonan','$list.id_htppermohonan','$list.no_fail')" class="style1">$list.no_fail.toUpperCase() </a>
	                <!-- 
	                #if($list.NO_RUJUKAN_PTG != "")                
	                <br />PTG : $list.NO_RUJUKAN_PTG.toUpperCase()
	                #end
	                #if($list.NO_RUJUKAN_PTD != "") 
	                <br />PTD : $list.NO_RUJUKAN_PTD.toUpperCase()
	                #end
	                #if($list.NO_RUJUKAN_UPT != "") 
	                <br />UPT : $list.NO_RUJUKAN_UPT.toUpperCase()
	                #end
	                 -->               
	            #end
	            
	            </span>
	             </td>
	           
	            
	            <td class="$row">
		            #set($span2 = "span2"+$i)
					<span id="$span2">
		            
		            	$list.tajuk_fail.toUpperCase()
		            
		            </span>
	            </td>
	           
	            <td class="$row">
		            #set($span1 = "span1"+$i)
					<span id="$span1">
		            
		            	$list.status_fail.toUpperCase()
		            
		            </span>
	            </td>
	            
	            </tr>            
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
		    
		    width  = 500;			
		    left = width+e.pageX;
            top  = height+e.pageY;			
			height = 300;
			$jquery("#boxListFail").css('width', width).css('height', height).css('top', top).css('left', left);	
			$jquery("#scroll_boxListFail").css('width', width).css('height', height-55).css('top', top).css('left', left).css('overflow-y','scroll');
		   
			//$jquery('#boxListFail').show();     
        });	
		
		/*
		 $jquery("div#boxListFail").mousemove(function(e) {
			 $jquery("div#boxListFail").css('top', e.pageY).css('left', e.pageX);
			});
		*/	
		
		
		var boxListFail = $jquery("#boxListFail")
		boxListFail.offset({
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
				//$jquery("div#boxListFail").css('top', e.pageY).css('left', e.pageX);
				var cur_offset = $jquery(drag.elem).offset();
		
				$jquery(drag.elem).offset({
					left: (cur_offset.left + delta.x),
					top: (cur_offset.top + delta.y)	
								
				});
		
				//$jquery("div#boxListFail").css('top', e.pageY).css('left', e.pageX);
				drag.x = e.pageX;
				drag.y = e.pageY;
				$jquery("div#boxListFail").css('top', e.pageY).css('left', e.pageX);
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
    
    
    <script>
if('$!search' != "")
{
	var word = '$!search';
	searchArray = [word];	
	highlightStartTag = "<font style='color:black; background-color:yellow;'>";
	highlightEndTag = "</font>";
	
	  for (x = 0; x < parseInt('$listFail.size()'); x++)
	  {
	  var span1 = "span1"+(x+1);
	  var span2 = "span2"+(x+1);	  
	  temp_span1 = document.getElementById(span1);
	  temp_span2 = document.getElementById(span2);	  
	  var divText1 = temp_span1.innerHTML;
	  var divText2 = temp_span2.innerHTML;
	   
		  for (var i = 0; i < searchArray.length; i++) 
		  {
			  divText1 = doHighlight(divText1,searchArray[i], highlightStartTag, highlightEndTag);	
			  divText2 = doHighlight(divText2,searchArray[i], highlightStartTag, highlightEndTag);			
		  }
		 
	  temp_span1.innerHTML = divText1; 
	  temp_span2.innerHTML = divText2; 
	  }
 }
</script>

