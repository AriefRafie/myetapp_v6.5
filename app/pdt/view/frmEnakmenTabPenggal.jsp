       <fieldset>
        <table width="100%" border="0">
          <tr>
            <td width="29%" align="right" valign="top">No Penggal</td>
            <td width="1%" align="center" valign="top">:</td>
            <td width="70%" valign="top">
              <input type="text" name="txtNoPenggal" id="txtNoPenggal" value="$!Penggal_NoPenggal" size="50" $RO_General /> 
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">rrrrrrrrrrrrrrTajuk Penggal</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txtTajukPenggal" id="txtTajukPenggal" value="$!Penggal_TajukPenggal" size="50" $RO_General />
            </td>
          </tr>  
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
            <td>
#if($action == 'view')
        <input type="button" value="Kemaskini" onclick="doAdd()"/>
#elseif ($action == 'doUpdate')
     	<input type="button" value="Simpan" onclick="doKemaskiniPenggal()"/>
             <!-- <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="doUpdate()"/>
              <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="doPrint()" />-->
             <input type="button" value="Batal" onclick="doCancel()" />
             #elseif($action == 'update')
              <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="doSavePenggal()"/>
              
 #else
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="doBack()"/>
#end
            </td>
          </tr>
        </table>
        </fieldset>
        <br />
        <fieldset>
          <legend>SENARAI PENGGAL</legend>
          <table width="100%" align="center" cellpadding="2" cellspacing="0">
            <tr class="table_header">
              <td width="19%"><strong>No Penggal</strong></td>
              <td width="33%"><strong>Tajuk Penggal</strong></td>
              <td width="16%">&nbsp;</td>
            </tr>
#set ($fail = '')
#foreach ($fail in $List_CarianPenggal)
	#if ($fail.No == '') 
    	#set ($row = 'row1')
    #elseif ($fail.No % 2 != 0)
	    #set ($row = 'row1')
    #else 
	    #set ($row = 'row2')
    #end
            <tr>
              <td class="$row">
              <a href="javascript:viewPenggal('$fail.IDPenggal')" style="color:#0000FF">
              $fail.NoPenggal              </a></td>
              <td width="33%" class="$row">$fail.TajukPenggal</td>
              <td width="16%" class="$row"><input type=button value=Hapus onClick=doHapusPenggal('$fail.IDPenggal');></td>
            </tr>
#end
          </table>
</fieldset>
      
