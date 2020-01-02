<!--<br/>-->
<form name="f1" method="post">
	<fieldset><legend><b>SEMAKAN STATUS</b></legend>
		
		<table width="100%" align="center" border="0">
			<tr>
      			<td scope="row" align="right">No Fail : </td>
      			<td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$nofail" size="30" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
      			<td scope="row"></td>
      			<td><input name="cmdSemak" id="cmdSemak" value="Semak" type="button" onclick="javascript:search_data()">
        			<input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onClick="javascript:kosongkan()"></td>
    		</tr>
    		
		</table>
		
	</fieldset>

##if($listSemak_size!=0 && $listSemak_size!="")	
 #if ($listSemak_size == false || $carianrekod == "tiada")
 
<br/>

	<fieldset>
	<legend><strong>STATUS SEMASA</strong></legend>
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr class="table_header">
				<td><b>Status</b></td>
			</tr>

         	<tr>
            	<td>$keterangan_status</td>
        	</tr>	
      
			
		</table>
	</fieldset>

<br/>
	##if($id_status!=21 && $id_status!=64 && $id_status!=163 && $id_status!=169)
	#if($listSemak != 0)
	<fieldset>
	<legend><strong>PERTUKARAN STATUS</strong></legend>
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr class="table_header">
      			<td scope="row" width="5%"><b>Bil</b></td>
      			<td width="95%"><b>Status</b></td>
    		</tr>
    		
   			#set ( $cnt = 0 )			
    		#foreach($list in $listSemak)
   			#set ( $cnt = $cnt + 1 )
   		 	#set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
    		<tr>
      			<td class="$row">$cnt</td>
      			<td class="$row">
      				<a href="javascript:tukarstatus('!$id_permohonan','!$keterangan_status','!$id_suburusanstatusfail','!$id_fail','!$list.level','!$list.list_keterangan','!$list.idsuburusan','!$id_perbicaraan','!$id_perintah')">
      					<font color="blue">$list.list_keterangan</font></a></td>
    		</tr>
    		#end
    		
    		#if($cnt !=0)
       			<tr>
	      			<td scope="row" width="5%"></td>
	      			<td width="95%">
	      				<table>
		      				<tr>
			      				<td width="19%"><b>Tarikh</b></td>
			      				<td width="1%">:</td>
			      				<td width="80%">
			      					<input name="txdTarikh" type="text" id="txdTarikh" value="$txdTarikh" $readonly class="$inputTextClass" />
			      				    <a href="javascript:displayDatePicker('txdTarikh',false,'dmy');"> <img src="../img/calendar.gif" border="0"/></a>
			      				</td>
		      				</tr>
	      				<tr>
			      				<td width="19%" valign=top><b>Keterangan</b></td>
			      				<td width="1%"  valign=top>:</td>
			      				<td width="80%">
			      					<textarea name="txtUlasan" id="txtUlasan" rows="3" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"></textarea>
			      				</td>
		      				</tr>
		      			</table>
	              	</td>
    			</tr>
    			<!-- <tr >
      			<td scope="row" width="5%"></td>
      			<td width="95%" valign="center"><b>Keterangan</b> : 
      				
      			</td>
    			</tr> -->
    		#end
		</table>
	</fieldset>	
    #end
    
#end

#if ($!carianrekod == "tiada")
    <br/>

	<fieldset>
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr>
				<td>No fail : <b>$!nofail</b> &nbsp;tiada dalam pangkalan data.</td>
			</tr>
		</table>
	</fieldset>
#end	

	
<input type="hidden" name="command" />	
<input type="hidden" name="id_status" value="$id_status"/>
<input type="hidden" name="keterangan" value="$keterangan_status"/>
<input type="hidden" name="id_fail" value="$id_fail"/>
<input type="hidden" name="id_permohonan" value="$id_permohonan"/>
<input type="hidden" name="id_suburusanstatusfail" value="$id_suburusanstatusfail"/>
<input type="hidden" name="seksyen" value="$seksyen"/>
<input type="hidden" name="level" value=""/>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="id_keputusanpermohonan">
<input type="hidden" name="id_perbicaraan">
<input type="hidden" name="id_perintah">
</form>

<script>
function kosongkan() {
	document.f1.action = "";
	document.f1.command.value = "kosongkan";
	document.f1.txtNoFail.focus();
	document.f1.submit();
}
function tukarstatus(id_permohonan,keterangan,id_suburusanstatusfail,id_fail,level,keterangan2,id_keputusanpermohonan,id_perbicaraan,id_perintah) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.f1.action = "";
	document.f1.id_permohonan.value = id_permohonan;
	document.f1.id_suburusanstatusfail.value = id_suburusanstatusfail;
	document.f1.id_keputusanpermohonan.value = id_keputusanpermohonan;
	document.f1.id_fail.value = id_fail;
	document.f1.id_perbicaraan.value = id_perbicaraan;
	document.f1.id_perintah.value = id_perintah;
	document.f1.level.value = level;
	document.f1.command.value = "tukarstatus";
	document.f1.submit();
	alert("Status \""+keterangan+"\" telah ditukar kepada status \""+keterangan2+"\" ");
}
function search_data(){
	if(document.f1.txtNoFail.value=="" ){
		return ;	}
	else{
		document.f1.command.value = "Cari";
		document.f1.action = "";
		document.f1.submit();
	}
}
</script>