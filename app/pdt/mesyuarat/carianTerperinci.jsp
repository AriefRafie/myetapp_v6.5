
<fieldset>
<legend>Carian Umum</legend>
<table width="100%" align="center" border="0" cellpadding="2" cellspacing="2" >
<tr>
<td width="1%" valign="top" >
</td>
<td width="28%" valign="top" >
Tajuk Mesyuarat</td>
<td width="1%" valign="top" >
:
</td>
<td width="70%" valign="top" >
<input type="text" size="50" id="carianMesyuarat" name="carianMesyuarat" style="text-transform:uppercase;" value="$carianMesyuarat" onkeypress="if(event.keyCode == 13) { if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y'); return false; }}" >

</td>
</tr>
<tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				Tahun Mesyuarat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
               
<input type="text" size="10" id="carianTahun" name="carianTahun"  
value="$carianTahun" maxLength="4"
onKeyUp="numberOnly(this,this.value);"
onkeypress="if(event.keyCode == 13) { if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y'); return false; }}"
>		


				</td>				
			</tr>
            <tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				Bilangan Mesyuarat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				

<input type="text" size="10" id="carianBilangan" name="carianBilangan"  
value="$carianBilangan" maxLength="5"
onKeyUp="numberOnly(this,this.value);"
onkeypress="if(event.keyCode == 13) { if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y'); return false; }}"
>		
				</td>				
			</tr>
            
            <tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				Status Mesyuarat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                
               
                
                <input type="radio" id="radioCarianStatusMesyuarat" 
				name="radioCarianStatusMesyuarat" 
                #if($carianStatusMesyuarat=='A') checked #end
				onClick="fromRadioToText(this,this.value,'carianStatusMesyuarat')" 
				value="A" 
                onkeypress="if(event.keyCode == 13) { if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y'); return false; }}"
                >Aktif
				&nbsp;&nbsp;
				<input type="radio" id="radioCarianStatusMesyuarat" 
                #if($carianStatusMesyuarat=='T') checked #end
				name="radioCarianStatusMesyuarat" 
				onClick="fromRadioToText(this,this.value,'carianStatusMesyuarat')" 
                onkeypress="if(event.keyCode == 13) { if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y'); return false; }}"
				value="T">Tangguh
                &nbsp;&nbsp;
				<input type="radio" id="radioCarianStatusMesyuarat"
                #if($carianStatusMesyuarat=='') checked #end 
				name="radioCarianStatusMesyuarat" 
				onClick="fromRadioToText(this,this.value,'carianStatusMesyuarat')" 
                onkeypress="if(event.keyCode == 13) { if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y'); return false; }}"
				value="">Tiada
                
                
                
                
                
               
<input type="hidden" size="50" id="carianStatusMesyuarat" name="carianStatusMesyuarat" 
value="$carianStatusMesyuarat" >
                </td>
</tr>
            
<tr>
<td width="1%" valign="top" >
</td>
<td width="28%" valign="top" >
Item Mesyuarat</td>
<td width="1%" valign="top" >
:
</td>
<td width="70%" valign="top" >
<input type="text" size="50" id="carianTerperinci" name="carianTerperinci" style="text-transform:uppercase;" value="$carianTerperinci" onkeypress="if(event.keyCode == 13) { if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y'); return false; }}" >

</td>
</tr>

<tr>
<td width="1%" valign="top" >
</td>
<td width="28%" valign="top" >
PIC</td>
<td width="1%" valign="top" >
:
</td>
<td width="70%" valign="top" >
<input type="text" size="50" id="carianPIC" name="carianPIC" style="text-transform:uppercase;" value="$carianPIC" onkeypress="if(event.keyCode == 13) { if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y'); return false; }}" >

</td>
</tr>

<tr>
<td></td>
<td>Bahagian Terlibat</td>
<td>:</td>
<td>

                 <datalist id="dropBahagianCarian">                 
                 </datalist> 
<input type="text" list="dropBahagianCarian" size="50" id="carianBahagian" name="carianBahagian" style="text-transform:uppercase;" 
onKeyUp="doDivAjaxCall3$formname('dropBahagianCarian','dropBahagian','SEARCH='+this.value);"
value="$carianBahagian" onkeypress="if(event.keyCode == 13) { if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y'); return false; }}" >

</td>
</tr>

<tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				Status Tindakan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                
                <input type="radio" id="radioCarianStatus" 
				name="radioCarianStatus" 
                #if($carianStatus=='1') checked #end
				onClick="fromRadioToText(this,this.value,'carianStatus')" 
				value="1" 
                onkeypress="if(event.keyCode == 13) { if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y'); return false; }}"
                >Dalam Tindakan
				&nbsp;&nbsp;
				<input type="radio" id="radioCarianStatus" 
                #if($carianStatus=='2') checked #end
				name="radioCarianStatus" 
				onClick="fromRadioToText(this,this.value,'carianStatus')" 
                onkeypress="if(event.keyCode == 13) { if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y'); return false; }}"
				value="2">Selesai
                &nbsp;&nbsp;
				<input type="radio" id="radioCarianStatus"
                #if($carianStatus=='') checked #end 
				name="radioCarianStatus" 
				onClick="fromRadioToText(this,this.value,'carianStatus')" 
                onkeypress="if(event.keyCode == 13) { if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y'); return false; }}"
				value="">Tiada
                
                
                
                
                
               
<input type="hidden" size="50" id="carianStatus" name="carianStatus" 
value="$carianStatus" >
                </td>
</tr>

<tr>
<td></td>
<td>Lampiran</td>
<td>:</td>
<td>
<input type="text" size="50" id="carianLampiran" name="carianLampiran" style="text-transform:uppercase;" value="$carianLampiran" onkeypress="if(event.keyCode == 13) { if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y'); return false; }}" >

</td>
</tr>


<tr>
<td></td>
<td></td>
<td></td>
<td>
<input type="button" id="cmdCariFolder" name="cmdCariFolder" value="Cari" onClick="if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','FlagCari=Y');}" >
<input type="button" id="cmdBatalCariFolder" name="cmdBatalCariFolder" value="Reset" onClick="kosongkanCarian();doDivAjaxCall$formname('div_senaraiUtama','batalCarianUtama','FlagCari=Y');" >
</td>
</tr>
</table>
</fieldset>

