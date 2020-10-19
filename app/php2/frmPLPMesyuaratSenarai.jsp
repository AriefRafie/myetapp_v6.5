<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
   <tr>
    <td><fieldset>
      <legend><strong>BENTUK PAMPASAN</strong></legend>
      <table align="center" width="100%">
       #foreach($beanMaklumatPampasan in $BeanMaklumatPampasan)
         <tr>
         	<td width="1%">&nbsp;</td>
          	<td width="27%" align="top">Jenis Pampasan</td>
          	<td width="1%">:</td>
		  	<td width="71%">
		  		<select name="socGanti" id="socGanti" onChange="onChangeValue(this)" $readonly class="$disabled" $disabled>
				  #if ($beanMaklumatPampasan.jenis == '1')
                	<option value="0">SILA PILIH</option>
					<option value="1" selected>TANAH GANTI</option>
					<option value="2">WANG PAMPASAN</option>
					<option value="3">TANAH GANTI DAN WANG PAMPASAN</option>
					<option value="4">LAIN-LAIN</option>
                  #elseif($beanMaklumatPampasan.jenis == '2')
	                <option value="0">SILA PILIH</option>
					<option value="1" >TANAH GANTI</option>
					<option value="2" selected>WANG PAMPASAN</option>
					<option value="3">TANAH GANTI DAN WANG PAMPASAN</option>
					<option value="4">LAIN-LAIN</option>
               	  #elseif($beanMaklumatPampasan.jenis == '3')
	                <option value="0">SILA PILIH</option>
					<option value="1" >TANAH GANTI</option>
					<option value="2">WANG PAMPASAN</option>
					<option value="3" selected>TANAH GANTI DAN WANG PAMPASAN</option>
					<option value="4">LAIN-LAIN</option>
               	  #elseif($beanMaklumatPampasan.jenis == '4')
	                <option value="0">SILA PILIH</option>
					<option value="1" >TANAH GANTI</option>
					<option value="2">WANG PAMPASAN</option>
					<option value="3">TANAH GANTI DAN WANG PAMPASAN</option>
					<option value="4" selected>LAIN-LAIN</option>
                  #else
	                <option value="0" selected>SILA PILIH</option>
					<option value="1" >TANAH GANTI</option>
					<option value="2">WANG PAMPASAN</option>
					<option value="3">TANAH GANTI DAN WANG PAMPASAN</option>
					<option value="4">LAIN-LAIN</option>
                  #end
			</select></td>
        </tr>
        #if($beanMaklumatPampasan.jenis == '2' || $beanMaklumatPampasan.jenis == '3')
        <tr>
          <td width="1%">&nbsp;</td>
          <td align="top">Nilai Pampasan(RM)</td>
          <td>:</td>
          <td><input name="txtPampasan" type="text" value="$beanMaklumatPampasan.pampasan" $readonly class="$inputTextClass" onblur="validateCurrency(this,this.value,'$beanMaklumatPampasan.pampasan');"></td>
        </tr>
        #end
        #if($beanMaklumatPampasan.jenis == '4')
        <tr>
          <td width="1%">&nbsp;</td>
          <td align="top">Catatan</td>
          <td>:</td>
          <td><textarea cols="40" rows="5" id="txtCatatan" name="txtCatatan" style="text-align:left" $readonly class="$inputTextClass">$beanMaklumatPampasan.catatan</textarea></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
        <td colspan="3">&nbsp;</td>
            <td>
      </td>
        </tr>
        </table>
        </fieldset>
        </td>
	</tr>
    #end
</table>
<script>
function validateCurrency(elmnt,content,content2) {
	//if it is character, then remove it..
	content = content.replace(/,/g,'');
	content2 = content2.replace(/,/g,'');
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}
</script>