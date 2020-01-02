<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<input type="hidden" id="minit" name="minit" value="$minit" />
#if($minit == 'true') 
<fieldset>
  <legend><strong>MINIT</strong></legend>
  <table width="100%"  align="center">
    <tr>
      <td  align="right" valign="top"><div align="left">Tajuk Minit</div></td>
      <td  align="center" valign="top">:</td>
      <td valign="top"><input name="Minit_Tajuk" type="hidden" id="Minit_Tajuk" value="$!Minit_Tajuk" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" $readonly/>
      $!Minit_Tajuk</td>
    </tr>
    <tr>
      <td  align="right" valign="top"><div align="left">Minit</div></td>
      <td  align="center" valign="top">:</td>
      <td  valign="top">$!Minit_Minit</td>
    </tr>
    <tr>
      <td align="right" valign="top"><div align="left">Tindakan/Makluman</div></td>
      <td align="center" valign="top">:</td>
      <td valign="top">$!Minit_Tindakan</td>
    </tr>
    
    <tr>
      <td align="right" valign="top"><div align="left">Maklumbalas</div></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><textarea id="Minit_Maklumbalas" name="Minit_Maklumbalas" cols="80" rows="8" $readonlyMakluman >$!Minit_Maklumbalas</textarea>
      </td>
    </tr>
    
    
    <tr>
      <td colspan="2"><div align="left"></div></td>
      <td>
#if ($hideSaveButton != 'true')  
        <input type="button" id="cmdSimpan" name="cmdSimpan" value="Simpan" onclick="simpanMesyuaratMinitEdit();" />
#end        </td>
    </tr>
    <tr>
      <td colspan="3"><div align="left"></div></td>
    </tr>
  </table>
</fieldset>
#else
</br>
<fieldset>
<legend><strong>SENARAI MINIT MESYUARAT</strong></legend>
 <table style="width:100%; border:1px solid #000000" align="center">
    <tr class="table_header">
	#if ($selectedTab == '1')
      <td width="5%" align="center" valign="top" nowrap="nowrap">No</td>
      <td width="75%" valign="top" nowrap="nowrap">Agenda</td>
	#elseif ($selectedTab == '2')
      <td width="5%" align="center" valign="top" nowrap="nowrap">No</td>
      <td width="35%" valign="top" nowrap="nowrap">Agenda</td>
      <td width="45%" valign="top" nowrap="nowrap">Minit</td>
	#elseif ($selectedTab == '3')
      <td width="5%" align="center" valign="top" nowrap="nowrap">No</td>
      <td width="20%" valign="top" nowrap="nowrap">Agenda</td>
      <td width="20%" valign="top" nowrap="nowrap">Minit</td>
      <td width="40%" valign="top" nowrap="nowrap">Sub-Minit</td>
	#end
    </tr>
	#set ($fail = '')
	#foreach ($fail in $List_CarianMinit)
		#if ($fail.No == '') 
	    	#set ($row = 'row1')
	    #elseif ($fail.No % 2 != 0)	
		    #set ($row = 'row1')
	    #else 
		    #set ($row = 'row2')
	    #end
    <tr>
      <td class="$row" align="center">$fail.No</td>
    	#if ($fail.No != '') 
			#if ($selectedTab == '1')
      <td class="$row"><a href="javascript:viewMesyuaratAgendaEdit('$fail.IDMinit')" style="color:#0000FF">$fail.Agenda</a></td>
			#elseif ($selectedTab == '2')
      <td class="$row"><a href="javascript:viewMesyuaratMinitEdit('$fail.IDMinit')" style="color:#0000FF">$fail.Agenda</a></td>
			#elseif ($selectedTab == '3')
      <td class="$row"><a href="javascript:viewMesyuaratSubMinitEdit('$fail.IDMinit')" style="color:#0000FF">$fail.Agenda</a></td>
			#end
	    #else
      <td class="$row">$fail.Agenda</td>
    	#end
		#if ($selectedTab == '1')

		#elseif ($selectedTab == '2')
      <td class="$row">$fail.Minit</td>
		#elseif ($selectedTab == '3')
      <td class="$row">$fail.Minit</td>
      <td class="$row">$fail.SubMinit</td>
		#end
    </tr>
	#end
  </table>
</fieldset>
#end