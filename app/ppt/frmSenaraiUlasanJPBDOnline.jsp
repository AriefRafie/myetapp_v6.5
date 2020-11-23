<fieldset>
	<legend>Carian</legend>
    	<table  width="100%" cellspacing="4" cellpadding="0" border="0">
        	<tr>
            	<td width="8%">&nbsp;</td>
            	<td width="22%" align="right">No. Fail Permohonan</td>
              	<td width="1%">:</td>
              	<td width="69%"><input type="text" name="txtNoFail" id="txtNoFail" value="$!txtNoFail" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" size="50" /></td>
          </tr>  
            <!-- <tr>
            	<td width="8%">&nbsp;</td>
           	  	<td align="right">Kementerian</td>
                <td>:</td>
              	<td>$socKementerian</td>               
            </tr> -->
      	  	<tr>
      	    	<td>&nbsp;</td>
          		<td></td>
                <td></td>
                <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:search_data()" />
                <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onclick="javascript:kosongkan()" /></td>
          	</tr>                  
        </table>
</fieldset>
<fieldset>
  <legend><strong>SENARAI FAIL</strong>
  </legend><table width="100%" cellspacing="0" cellpadding="2" style="border:1px solid #000">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="25%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="15%" align="center"><strong>TARIKH HANTAR</strong></td>
      <td width="15%" align="center"><strong>STATUS</strong></td>
      <td width="25%" align="center"><strong>&nbsp;</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListBorangLampiranA1)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
    #if ($list.No != '') 
      <td class="$row" valign="top" align="center">$list.No</td>
      <td class="$row" valign="top"><a href="javascript:viewBorangLampiranA1('$list.IDPermohonan');" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top">$list.NoPermohonan</td>
      <td class="$row" valign="top" align="center">$list.TarikhHantar</td>
      <td class="$row" valign="top" align="center">$list.Status</td>
      <td class="$row" valign="top" align="center"><input type="button" id="PaparLot$list.IDPermohonan" name="PaparLot$list.IDPermohonan" onclick="javascript:viewFullPermohonan('$list.IDPermohonan');" value="Papar Lot Terlibat" /></td>
    #else
      <td colspan="6" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
	#if ($viewFullPermohonan == 'true' && $selectedFullPermohonan == $list.IDPermohonan)
    	#set ($row = 'row1')
    <tr>
      <td colspan="6" class="$row" align="center">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="6" class="$row" align="center">
        <fieldset>
          <legend><strong>SENARAI LOT BAGI FAIL $list.NoFail</strong></legend>
          <table width="70%" cellspacing="0" cellpadding="2" style="border:1px solid #000" align="center">
            <tr class="table_header">
              <td width="5%" scope="row" align="center"><strong>NO</strong></td>
              <td width="20%" align="center"><strong>NO LOT</strong></td>
              <td width="55%" align="center"><strong>MUKIM</strong></td>
              <td width="20%" align="center"><strong>NO SYIT UKUR</strong></td>
            </tr>
		#set ($lstLot = '')
		#foreach ($lstLot in $ListFullPermohonan)
			#if ($lstLot.No == '') 
    			#set ($row = 'row1')
  		  	#elseif ($lstLot.No % 2 != 0)
  		  		#set ($row = 'row1')
  		  	#else 
  		  		#set ($row = 'row2')
  		  	#end
            <tr>
    		#if ($lstLot.No != '') 
              <td class="$row" valign="top" align="center">$lstLot.No</td>
              <td class="$row" valign="top" align="center"><a href="javascript:viewBorangLampiranA1('$lstLot.IDPermohonan');" style="color:#0000FF">$lstLot.NoLot</a></td>
              <td class="$row" valign="top" align="center">$lstLot.NamaMukim</td>
              <td class="$row" valign="top" align="center">$lstLot.NoSyit</td>
    		#else
              <td colspan="4" class="$row" style="text-align:center">Tiada Rekod</td>
    		#end
            </tr>
		#end
          </table>
        </fieldset>
      </td>
    </tr>
    #end    
#end
    <tr>
      <td colspan="6">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
#if ($isJPBDUser != 'true')
<!-- <fieldset>
  <legend><strong>SENARAI BORANG LAMPIRAN A1 YANG TELAH SELESAI</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2" style="border:1px solid #000">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="15%" align="center"><strong>TARIKH HANTAR</strong></td>
      <td width="15%" align="center"><strong>STATUS</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListBorangLampiranA1Selesai)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
    #if ($list.No != '') 
      <td class="$row" valign="top" align="center">$list.No</td>
      <td class="$row" valign="top"><a href="javascript:viewBorangLampiranA1('$list.IDPermohonan')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top">$list.NoPermohonan</td>
      <td class="$row" valign="top" align="center">$list.TarikhHantar</td>
      <td class="$row" valign="top" align="center">$list.Status</td>
    #else
      <td colspan="5" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
#end
    <tr>
      <td colspan="5">&nbsp;</td>
    </tr>
  </table>
</fieldset> -->
#elseif ($isPegawaiJPBD == 'true')
<fieldset>
  <legend><strong>SENARAI BORANG LAMPIRAN A1 YANG MENUNGGU PENGESAHAN</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2" style="border:1px solid #000">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="15%" align="center"><strong>TARIKH HANTAR</strong></td>
      <td width="15%" align="center"><strong>STATUS</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListBorangLampiranA1Pengesahan)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
    #if ($list.No != '') 
      <td class="$row" valign="top" align="center">$list.No</td>
      <td class="$row" valign="top"><a href="javascript:viewBorangLampiranA1('$list.IDPermohonan')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top">$list.NoPermohonan</td>
      <td class="$row" valign="top" align="center">$list.TarikhHantar</td>
      <td class="$row" valign="top" align="center">$list.Status</td>
    #else
      <td colspan="5" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
#end
    <tr>
      <td colspan="5">&nbsp;</td>
    </tr>
  </table>
</fieldset>
#end
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" />
<input type="hidden" id="ID_NEGERI" name="ID_NEGERI" />
<input type="hidden" id="mode" name="mode" value="$mode" />
<input type="hidden" id="action2" name="action2" value="$action2" />
<script type="text/javascript">
function viewBorangLampiranA1(ID_PERMOHONAN) {
    document.${formName}.action = "$EkptgUtil.getTabID("JPBD",$portal_role)?_portal_module=ekptg.view.ppt.FrmUlasanJPBDOnline&action2=viewBorangLampiranA1&ID_PERMOHONAN=" + ID_PERMOHONAN;
    document.${formName}.submit();
}
function viewFullPermohonan(ID_PERMOHONAN) {
    document.${formName}.ID_PERMOHONAN.value = ID_PERMOHONAN;
    document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMyInfoBorangLampiranA1&ID_PERMOHONAN=" + ID_PERMOHONAN;
    doAjaxCall${formName}("viewFullPermohonan");
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	//document.${formName}.socKementerian.value = "";
	document.${formName}.submit();
}
function search_data(){
	document.${formName}.command.value = "";
	document.${formName}.action = "$EkptgUtil.getTabID("JPBD",$portal_role)?_portal_module=ekptg.view.ppt.FrmUlasanJPBDOnline";
	document.${formName}.submit();
}
</script>