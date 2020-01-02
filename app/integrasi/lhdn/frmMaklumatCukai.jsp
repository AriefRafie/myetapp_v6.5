<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
.xinputnumber {
     text-align: right;
     //background: url("eTapp_HTP/fieldBg.jpg") repeat scroll 0 0 transparent;
     //border: 1px solid #4C2F4D;
     //color: #000000;
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="actionPerintah" type="hidden" id="actionPerintah" value="$actionPerintah"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="selectedTabLower" type="hidden" id="selectedTabLower" value="$selectedTabLower"/>
  <input name="hitButt" type="hidden" id="hitButt" value="$hitButt"/>
  <input name="anchor" type="hidden" id="anchor"/>
  <input name="jenisPerintah" type="hidden" id="jenisPerintah" value="$jenisPerintah"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idPermohonanSimati" type="hidden" id="idPermohonanSimati" value="$idPermohonanSimati"/>
  <input name="idPerintah" type="hidden" id="idPerintah" value="$idPerintah"/>
  <input name="idPerbicaraan" type="hidden" id="idPerbicaraan" value="$idPerbicaraan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="flagAdaHTA" type="hidden" id="flagAdaHTA" value="$flagAdaHTA"/>
  <input name="flagAdaHTATH" type="hidden" id="flagAdaHTATH" value="$flagAdaHTATH"/>
  <input name="flagAdaHA" type="hidden" id="flagAdaHA" value="$flagAdaHA"/>
  <input name="flagAdaHTAPT" type="hidden" id="flagAdaHTAPT" value="$flagAdaHTAPT"/>
  <input name="flagAdaHAPT" type="hidden" id="flagAdaHAPT" value="$flagAdaHAPT"/>
  <input name="flagAdaHTAPKT" type="hidden" id="flagAdaHTAPKT" value="$flagAdaHTAPKT"/>
  <input name="flagAdaHAPKT" type="hidden" id="flagAdaHAPKT" value="$flagAdaHAPKT"/>
  <input name="flagAdaHTAPL" type="hidden" id="flagAdaHTAPL" value="$flagAdaHTAPL"/>
  <input name="flagAdaHAPL" type="hidden" id="flagAdaHAPL" value="$flagAdaHAPL"/>
  <input name="flagAdaHTAPF" type="hidden" id="flagAdaHTAPF" value="$flagAdaHTAPF"/>
  <input name="flagAdaHAPF" type="hidden" id="flagAdaHAPF" value="$flagAdaHAPF"/>
  <input name="flagSelesaiHTA" type="hidden" id="flagSelesaiHTA" value="$flagSelesaiHTA"/>
  <input name="flagSelesaiHA" type="hidden" id="flagSelesaiHA" value="$flagSelesaiHA"/>
  <input name="idHTA" type="hidden" id="idHTA" value="$idHTA"/>
  <input name="idHA" type="hidden" id="idHA" value="$idHA"/>
  <input name="idJenisHA" type="hidden" id="idJenisHA" value="$idJenisHA"/>
  <input name="printOption" type="hidden" id="printOption"/>
  <input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
  <input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>
</p>
<body onLoad = $onload >
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  	<tr>
    	<td>&nbsp;</td>
	</tr>
	<tr>
    	<td> 
 #foreach($list in $MaklumatPermohonan)
      #set($idPermohonan=$list.idPermohonan)
      #set($idSimati=$list.idSimati)
      <input name="idSimati" type="hidden" id="idSimati" value="$idSimati"/>
      #set($noFail=$list.noFail)
      #set($idFail=$list.idFail)
      <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
      #set($negeri=$list.pmNama_negeri)
      <input name="idNegeri" type="hidden" id="idNegeri" value="$list.pmidnegeri"/>
      #set($idNegeri = $list.pmidnegeri)
      #set($daerah=$list.namadaerah)
      #set($unit=$list.namaPejabat)
      #set($seksyen=$list.seksyen)
      #set($statusFail=$list.keterangan)
      #set($tarikhMohon=$list.tarikhMohon)
      #set($namaSimati=$list.namaSimati)
      #set($namaSipemohon=$list.namaPemohon)
      #end
      
      
      #if($!headerppk.size()>0)
      	##parse("app/ppk/headerppk.jsp")
      	#parse("app/integrasi/utiliti/headerPPK.jsp")
      #end
      <fieldset id="header_lama" style="display:none" >
        <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
        <table width="100%" border="0" cellspacing="2" cellpadding="2">
          <tr>
            <td width="50%"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
                <tr>
                  <td width="37%" align="right">NO. FAIL :</td>
                  <td width="63%"><font color="blue">$noFail
                    <input type="hidden" name="noFail" id="noFail" value="$noFail">
                    </font></td>
                </tr>
                <tr>
                  <td align="right">NEGERI :</td>
                  <td><font color="blue">$negeri.toUpperCase()</font></td>
                </tr>
                <tr>
                  <td align="right">DAERAH / JAJAHAN :</td>
                  <td><font color="blue">$daerah.toUpperCase()</font></td>
                </tr>
                <tr>
                  <td align="right" valign="top">UNIT :</td>
                  <td rowspan="2" valign="top"><font color="blue">$unit.toUpperCase()</font></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                </tr>
              </table></td>
            <td width="50%"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
                <tr>
                  <td width="37%" align="right">STATUS FAIL :</td>
                  <td width="63%"><font color="blue">$statusFail.toUpperCase()</font></td>
                </tr>
                <tr>
                  <td align="right">SEKSYEN :</td>
                  <td><font color="blue">$seksyen.toUpperCase()</font></td>
                </tr>
                <tr>
                  <td align="right">TARIKH MOHON :</td>
                  <td><font color="blue">$tarikhMohon</font></td>
                </tr>
                <tr>
                  <td align="right">NAMA PEMOHON :</td>
                  <td><font color="blue">$namaSipemohon.toUpperCase()</font></td>
                </tr>
                <tr>
                  <td align="right">NAMA SIMATI :</td>
                  <td><font color="blue">$namaSimati.toUpperCase()</font></td>
                </tr>
              </table></td>
          </tr>
        </table>
      </fieldset>
      
      #if($!headerppk.size()>0)
      #else 
     	<script  type="text/javascript">
		if(document.getElementById("header_lama").style.display=="none"){
			document.getElementById("header_lama").style.display="block";
		}
		</script> 
      #end 
      </td>
  </tr>
  <!-- END HEADER --> 
  <!-- START MAKLUMAT CUKAI -->
  	<tr>
    	<td> 
	    	<fieldset>
	        <legend><strong>MAKLUMAT CUKAI</strong></legend>
	        
               <table width="100%" border="0">
                	<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'view') * #end </span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Bayaran Cukai(RM)</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="txtcukai" size="15" maxlength="14" value="$!cukai" $!inputStyleNum onkeyup="validateNumber(this,this.value);" onblur="validateCurrency(this,this.value);"/>
						</td>
					</tr>
               		<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'view') * #end </span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tunggakan Cukai(RM)</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="txttunggakan" size="15" maxlength="14" value="$!tunggakan" $!inputStyleNum onkeyup="validateNumber(this,this.value);" onblur="validateCurrency(this,this.value);"/>
						</td>
					</tr>
               		<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'view') * #end </span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Jumlah Perlu Dibayar(RM)</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="txtjumlah" size="15" maxlength="14" value="$!jumlah" $!inputStyleNum onkeyup="validateNumber(this,this.value);" onblur="validateCurrency(this,this.value);"/>
						</td>
					</tr>
               		<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'view')  #end </span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Bahagian Simati(RM)</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="txtbaki" size="15" maxlength="14" value="$!baki" $!inputStyleNum onkeyup="validateNumber(this,this.value);" onblur="validateCurrency(this,this.value);"/>
						</td>
					</tr>	
                     </table> 


	        
	        </fieldset>
    	</td>
	</tr> 
	#if($!jspMode != "view")
	<tr>
        <td>
   			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
        </td>
	<tr>
	#end
	<tr>
    	<td align="center">
		#if($!jspMode == "view")
     		<input type="button" name="cmdpengesahan" value="Hantar JKPTG" onClick="javascript:simpanStatus('$!idMaklumatCukai')"/>    	
     		<input type="button" class="stylobutton100" name="cmdsimpan" value="Kemaskini" onClick="javascript:kemaskini('$!idMaklumatCukai')"/>    	
			<input type="button" class="stylobutton100" name="cmdKembali" value="Kembali" onClick="javascript:carian();"/>    	
		#elseif($!jspMode == "viewsimpan")
	    	<input type="button" class="stylobutton100" name="cmdsimpankemaskini" value="Kemaskini" onClick="javascript:kemaskini('$!idMaklumatCukai')"/>    	
			<input type="button" class="stylobutton100" name="cmdKembali" value="Batal" onClick="javascript:papar('$idPermohonanSimati','$idPermohonan','$idStatus','$!idMaklumatCukai')"/>    	
		
		#elseif($!jspMode == "viewInternal")
			<input type="button" class="stylobutton100" name="cmdKembali" value="Kembali" onClick="javascript:carianSimati();"/>    	
			
		#elseif($!jspMode == "kemaskini")
	   		<input type="button" class="stylobutton100" name="cmdsimpankemaskini" value="Simpan" onClick="javascript:simpan('$!idMaklumatCukai')"/>    	
			<input type="button" class="stylobutton100" name="cmdKembali" value="Batal" onClick="javascript:papar('$idPermohonanSimati','$idPermohonan','$idStatus','$!idMaklumatCukai')"/>    	
		
		#else
     		<input type="button" class="stylobutton100" name="cmdsimpan" value="Simpan" onClick="javascript:simpan('')"/>    	
     		<input type="reset" class="stylobutton100" name="cmdKembali" value="Kosongkan" />    	
			<input type="button" class="stylobutton100" name="cmdKembali" value="Batal" onClick="javascript:kembaliSenaraiPermohonan('$noFail')"/>    	
 		
 		#end
    	</td>
	</tr> 
 <!-- START MAKLUMAT CUKAI --> 
 </table>
 </body> 