 <table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%" >
			</td>
			</tr>
	<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" >	
                #if($mode=="edit")
                
                
                 <input type="button" id="BTNSAVE$view.ID_WARTATRM" name="BTNSAVE$view.ID_WARTATRM" 
                    onClick="if(valEdit('$view.ID_WARTATRM','hantar')==true){if(confirm('$label_adakah_pasti')){doDivAjaxCall$formname('div_row$view.ID_WARTATRM','save','ID_WARTATRM=$view.ID_WARTATRM&mode=view&commandFrom=$commandFrom&BIL=$BIL&rowCss=$rowCss');}}" value="Simpan" > 
              	
                <input type="button" id="BTNBTL$view.ID_WARTATRM" name="BTNBTL$view.ID_WARTATRM" onClick="doDivAjaxCall3$formname('div_row$view.ID_WARTATRM','edit','ID_WARTATRM=$view.ID_WARTATRM&mode=edit&commandFrom=$commandFrom&BIL=$BIL&rowCss=$rowCss');" value="Batal" >               
                #else
                
               
                <input type="button" id="BTNBTL$view.ID_WARTATRM" name="BTNBTL$view.ID_WARTATRM" onClick="doDivAjaxCall3$formname('div_row$view.ID_WARTATRM','edit','ID_WARTATRM=$view.ID_WARTATRM&mode=edit&commandFrom=$commandFrom&BIL=$BIL&rowCss=$rowCss');" value="Kemaskini" >
               <input type="button" id="BTNPRINT$view.ID_WARTATRM" name="BTNPRINT$view.ID_WARTATRM" 
                    onclick="printAduanForm('printableArea_$view.ID_WARTATRM','$view.ID_WARTATRM')" 
                    value="Cetak Maklumat Warta" />
                
                #end
                
                #set($divClose = "div_row")
                #if($commandFrom=="list")
                	#set($divClose = "div_row"+$view.ID_WARTATRM)
                #end
                
                <input type="button" id="BTNCLOSE$view.ID_WARTATRM" name="BTNCLOSE$view.ID_WARTATRM" onClick="doDivAjaxCall3$formname('$divClose','close','ID_WARTATRM=$view.ID_WARTATRM&commandFrom=$commandFrom&BIL=$BIL&rowCss=$rowCss');" value="Tutup" > 
                
	   			</td>
			</tr>
	</table>
    
	