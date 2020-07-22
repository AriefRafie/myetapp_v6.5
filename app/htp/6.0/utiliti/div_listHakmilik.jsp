

   <style>
    #boxListHakmilik {        
        position: absolute;       
        background: #eeeeee;       
        border: 1px solid #1a1a1a;       
		display: block;		
		z-index:2;
		width: 600;	
		height: 300;		
		
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



#if($!div_getListHakmilik_open == "Y")

	<div id="log"  style="display:none"></div>
	<div id="boxListHakmilik" >
	
	<input id="current_page_num" name="current_page_num" type="hidden" value="$!current_page_num">
	<input type="hidden" name="last_page" value="$!total_page">
	<input type="hidden" id="div_getListHakmilik_open" name="div_getListHakmilik_open" value="Y">
	<table border="0" width="100%" > 
	<tr>
	<td align="left" valign="top"><img src="../img/drag.png" id="move" name="move" title='Ubah Lokasi' onMouseOver="this.style.cursor='move'" width="18" height="18" align="center"/>
	</td>
	<td align="right" valign="top"><a href="javascript:doGetListHakmilik()" id="trigger">
	<img  src="../img/validno.png" title='Tutup' align="center"/>
	</a></td>
	</tr>
	</table>
	
	<table border="0" width="100%"  class="nav"> 
	<tr  >
	<td valign="top" >
	SENARAI HAKMILIK
	 </td>
	</tr>
	</table>
	
	<div id="scroll_boxListHakmilik">
	<table border="0" width="100%" > 
	 
	   <tr class="table_header">
	          
	              <td scope="row" width="5%" align="center">BIL</td>
	              <td width="50%">NO HAKMILIK</td> 
	              <td width="45%">NO FAIL</td>             
	              <!-- <td width="45%">NO FAIL</td> -->
	     
	   </tr>
	   
	   #if($listHakmilik.size()>0)
	 			       #set( $i = 0 )   
	           #foreach($list in $listHakmilik)
	         
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
	            
	             <td class="$row"  valign="top">
	           #set($span3 = "span3"+$i)
				<span id="$span3"> 
				 
				 $list.KOD_JENIS_HAKMILIK.toUpperCase()
				 $list.NO_HAKMILIK.toUpperCase()
		            #if($list.NO_LOT != "")
		            <br /> No. Lot/PT : $!list.KOD_LOT $list.NO_LOT
		            #end
	         
	            </span>
	            
	            </td>
	            
	            <td class="$row" valign="top">
	             #set($span1 = "span1"+$i) 
				<span id="$span1">  
			            #if($list.no_fail == '')
			            	<a href="javascript:paparDetailHakmilik('$!list.ID_HAKMILIK','$!list.NO_HAKMILIK','$!list.STATUS_SAH')" class="style1">TIADA NO FAIL</a>
			            #else
			            	<a href="javascript:paparDetailHakmilik('$!list.ID_HAKMILIK','$list.NO_HAKMILIK','$!list.STATUS_SAH')" class="style1">
			            		<font color="blue">
			            			$list.no_fail.toUpperCase() 
			            		</font>
			            	</a>
			            #end
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
		   
		    width  = 600;			
		    left = width+e.pageX;
            top  = height+e.pageY;			
			height = 300;
			$jquery("#boxListHakmilik").css('width', width).css('height', height).css('top', top).css('left', left);
			$jquery("#scroll_boxListHakmilik").css('width', width).css('height', height-55).css('top', top).css('left', left).css('overflow-y','scroll');
		   // $jquery('#boxListHakmilik').show();	     
        });	
		
		/*
		 $jquery("div#boxListHakmilik").mousemove(function(e) {
			 $jquery("div#boxListHakmilik").css('top', e.pageY).css('left', e.pageX);
			});
		*/	
		
		
		var boxListHakmilik = $jquery("#boxListHakmilik")
		boxListHakmilik.offset({
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
				//$jquery("div#boxListHakmilik").css('top', e.pageY).css('left', e.pageX);
				var cur_offset = $jquery(drag.elem).offset();
		
				$jquery(drag.elem).offset({
					left: (cur_offset.left + delta.x),
					top: (cur_offset.top + delta.y)	
								
				});
		
				//$jquery("div#boxListHakmilik").css('top', e.pageY).css('left', e.pageX);
				drag.x = e.pageX;
				drag.y = e.pageY;
				$jquery("div#boxListHakmilik").css('top', e.pageY).css('left', e.pageX);
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
	
	  for (x = 0; x < parseInt('$listHakmilik.size()'); x++)
	  {
	  var span1 = "span1"+(x+1);
	  var span2 = "span2"+(x+1);
	  var span3 = "span3"+(x+1);	  
	  temp_span1 = document.getElementById(span1);
	  temp_span2 = document.getElementById(span2);	
	  temp_span3 = document.getElementById(span3);  
	  var divText1 = temp_span1.innerHTML;
	  var divText2 = temp_span2.innerHTML;
	  var divText3 = temp_span3.innerHTML;
	   
		  for (var i = 0; i < searchArray.length; i++) 
		  {
			  divText1 = doHighlight(divText1,searchArray[i], highlightStartTag, highlightEndTag);	
			  divText2 = doHighlight(divText2,searchArray[i], highlightStartTag, highlightEndTag);	
			  divText3 = doHighlight(divText3,searchArray[i], highlightStartTag, highlightEndTag);			
		  }
		 
	  temp_span1.innerHTML = divText1; 
	  temp_span2.innerHTML = divText2; 
	  temp_span3.innerHTML = divText3;
	  }
 }
</script>



 ##parse("app/ppt/dashboard/script.jsp")