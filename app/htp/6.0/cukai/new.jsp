
   <style>
    #boxListCukai {        
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


#if($!div_getListCukai_open == "Y")

	<div id="log"  style="display:none"></div>
	
	<div id="boxListCukai" >
	
	<input id="current_page_num" name="current_page_num" type="hidden" value="$!current_page_num">
	<input type="hidden" name="last_page" value="$!total_page">
	<input type="hidden" id="div_getListCukai_open" name="div_getListCukai_open" value="Y">
	<table style="width:100%" > 
	<tr>
	<td align="left" valign="top"><img src="../img/drag.png" id="move" name="move" title='Ubah Lokasi' onMouseOver="this.style.cursor='move'" width="18" height="18" align="center"/>
	</td>
	<td align="right" valign="top"><a href="javascript:doGetListCukai()" id="trigger">
	<img  src="../img/validno.png" title='Tutup' align="center"/>
	</a></td>
	</tr>
	</table>
	
	<table style="width:98%" class="nav"> 
		<tr>
			<td valign="top" >
				MAKLUMAT HAKMILIK</td>
		</tr>
	</table>
	
	<div id="scroll_boxListCukai" >
	<table style="width:100%" > 
	 <tr>
    				<td width="50%" valign="top">
    				<fieldset><legend>MAKLUMAT TANAH</legend>
				
    					        <table style="width:100%">
							          <tr>
							            <td width="1%"><font color="red">*</font></td>
							            <td width="28%">Negeri</td>
							            <td width="1%">:</td>
							            <td width="70%">$manualNegeri</td>
							          </tr>
							          <tr>
							            <td><font color="red">*</font></td>
							            <td>Daerah</td>
							            <td>:</td>
							            <td>$manualDaerah</td>
							          </tr>
							          <tr>
							            <td><font color="red">*</font></td>
							            <td>Mukim/Pekan/Bandar</td>
							            <td>:</td>
							            <td>$manualMukim</td>
							          </tr>
							          <tr>
							            <td>&nbsp;</td>
							            <td>Jenis Hakmilik</td>
							            <td>:</td>
							            <td>$JenisHakmilik</td>
							          </tr>
							          <tr>
							            <td><font color="red">*</font></td>
							            <td>No. Hakmilik</td>
							            <td>:</td>
							            <td><input name="txtNoHakmilik" type="text" id="txtNoHakmilik" size="44" onkeyup="this.value=this.value.toUpperCase();">
							              </td>
							          </tr>
							          <tr>
							            <td><font color="red">*</font></td>
							            <td>Jenis Lot/PT</td>
							            <td>:</td>
							            <td>$jenisLot
							              </td>
							          </tr>
							          <tr>
							            <td >&nbsp;</td>
							            <td>No. Lot/PT</td>
							            <td>:</td>
							            <td><input name="txtNoLot" type="text" id="txtNoLot" size="44" onkeyup="this.value=this.value.toUpperCase();" /></td>
							          </tr>
							          <tr>
							            <td valign="top"><font color="red">*</font></td>
							            <td valign="top" >Kegunaan Tanah</td>
							            <td valign="top" >:</td>
							            <td><textarea name="txtKegunaanTanah" id="txtKegunaanTanah" cols="41" rows="5" onkeyup="this.value=this.value.toUpperCase();"></textarea></td>
							          </tr>
							        </table>  
						</fieldset>  				
    				</td>
    				<td width="50%" valign="top">
 					<fieldset><legend>BAYARAN</legend>
  						<table style="width:100%" >
									<tr>
							            <td width="1%"><font color="red">*</font></td>
							            <td width="28%">Cukai Semasa (RM)</td>
							            <td width="1%">:</td>
							            <td width="63%">
							              <input name="txtTahunan" type="text" id="txtTahunan" size="11"  onblur="validateCurrency(this,this.value,'');calculate()" value="$!UTIL.format2Decimal($!cukai.cukaiKenaBayar)">
							              </td>
							          </tr>
							       
							          <tr>
							            <td>&nbsp;</td>
							            <td>Tunggakan (RM)</td>
							            <td>:</td>
							            <td>
							              <input name="txtTungakan" type="text" id="txtTungakan" size="11"  onblur="validateCurrency(this,this.value,'');calculate()" value="$!UTIL.format2Decimal($!cukai.tunggakkan)">
							              </td>
							          </tr>
							          <tr>
							            <td>&nbsp;</td>
							            <td>Denda Lewat (RM)</td>
							            <td>:</td>
							            <td>
							                <input name="txtDenda" type="text" id="txtDenda" size="11"  onblur="validateCurrency(this,this.value,'');calculate()" value="$!UTIL.format2Decimal($!cukai.denda)">
							              </td>
							          </tr>
							          <tr>
							            <td>&nbsp;</td>
							            <td>Cukai Lain (RM)</td>
							            <td>:</td>
							            <td><input name="txtCukaiLain" type="text" id="txtCukaiLain" size="11"  onblur="validateCurrency(this,this.value,'');calculate()" value="$!UTIL.format2Decimal($!cukai.cukailain)"/></td>
							            </tr>
							          <tr>
							            <td>&nbsp;</td>
							            <td>Lebihan [ - ] (RM)</td>
							            <td>:</td>
							            <td><input name="txtLebihan" type="text" id="txtLebihan" size="11"  onblur="validateCurrency(this,this.value,'');calculate()" value="$!UTIL.format2Decimal($!cukai.lebihan)"></td>
							          </tr>
							          <tr>
							            <td><font color="red">*</font></td>
							            <td>Cukai Kena Bayar (RM)</td>
							            <td>:</td>
							            <td><input name="txtJumBayaran" type="text" id="txtJumBayaran" size="11"  onblur="validateCurrency(this,this.value,'');calculate()" value="$!UTIL.format2Decimal($!cukai.cukaiPerluBayar)" $inputstyle></td>
							          </tr>
							          <tr>
							            <td >&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							          </tr> 
							          <tr>
							            <td >&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							          </tr> 
							          <tr>
							            <td >&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							          </tr> 
							          <tr>
							            <td >&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							          </tr> 
							          <tr>
							            <td >&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							          </tr> 
							          <tr>
							            <td >&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							          </tr> 
					
							      	</table>
         				</fieldset>					
 					</td>
 	</tr>				
            <tr>
				<td colspan="2" align="center">
					<input class="stylobutton100" name="Simpan" type="button" value="Simpan" onclick="simpanManual()"/>
		 			<input class="stylobutton100" name="Batal" type="button" value="Batal" onclick="Kembali()"/>
				</td>
			</tr>
			<input type="hidden" name="idcukai" value="$cukai.idCukaiTemp" />
			<input type="hidden" value="$Tahun" name="tahun"/>  
	    </table>
	</div>
	   
	<!-- </td>
	</tr>
	</table> -->
	</div>

#end
 
  
   <script type="text/javascript">
      $jquery(function() {
		   var width  = 0;
		   var height = 0;			
		   var left = 0;
           var top  = 0;	  
		  
      $jquery(document).ready(function(e) {
		    
		    width  = 950;			
		    left = width+e.pageX;
            top  = height+e.pageY;			
			height = 400;
			$jquery("#boxListCukai").css('width', width).css('height', height).css('top', top).css('left', left);	
			$jquery("#scroll_boxListCukai").css('width', width).css('height', height-55).css('top', top).css('left', left).css('overflow-y','scroll');
		       
        });	
		
		
		var boxListCukai = $jquery("#boxListCukai")
		boxListCukai.offset({
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
		
				drag.x = e.pageX;
				drag.y = e.pageY;
				$jquery("div#boxListCukai").css('top', e.pageY).css('left', e.pageX);
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
	
	  for (x = 0; x < parseInt('$listCukai.size()'); x++)
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

