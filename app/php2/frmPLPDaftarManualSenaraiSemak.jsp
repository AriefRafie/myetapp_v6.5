<fieldset>
<legend>SENARAI SEMAK</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2">
    <table width="100%" border="0" cellspacing="2" cellpadding="2">
    	<tr class="row2">
			<td width="3%"></td>
			<td width="82%"><b>Keterangan</b></td>
			<td width="15%"><b>Dokumen</b></td>
		</tr>
		#if ($SenaraiSemak.size() > 0)
	        #set ($list = "")
	        #foreach ($list in $SenaraiSemak)
	          	#set( $i = $velocityCount )
	       		#if ( ($i % 2) == 0 )
	   	        	#set( $row = "row2" )
	            #else
	               	#set( $row = "row1" )
	          	#end
	        #if($list.flag == 'Y')
	        	#set($checked = 'checked')
	        	#set($disabled = 'disabled')
	        #else
	        	#set($checked = '')
	        #end
	        #if ($mode == 'update')
		    	<tr class="$row">
		          <td class="$row" width="3%"><input type="checkbox" value="$list.id" name="idsSenaraiSemak" $checked /></td>
		          <td class="$row" width="82%">$i. $list.keterangan</td>
		          <td class="$row" width="15%">$!list.lampirans</td>
		        </tr>
		    #end
		    #if ($mode == 'view')
		      	<tr class="$row">
		          <td class="$row" width="3%"><input type="checkbox" value="$list.id" name="idsSenaraiSemak" $checked $disabled /></td>
		          <td class="$row" width="82%">$i. $list.keterangan</td>
		          <td class="$row" width="15%">$!list.lampirans</td>
		        </tr>
		    #end
		    #if ($mode == 'new')
		      	<tr class="$row">
		          <td class="$row" width="3%"></td>
		          <td class="$row" width="82%">$i. $list.keterangan</td>
		          <td class="$row" width="15%">$!list.lampirans</td>
		        </tr>
		    #end
	    	#end
	    	#else
	    	<tr>
	        	<td class="$row" width="3%">&nbsp;</td>
	         	<td class="$row" colspan="2" width="95%">Tiada Rekod</td>
	        </tr>
	        #end
      </table></td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%">
    	#if ($mode == 'update')
      		<input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="doSimpanKemaskiniSenaraiSemak()"/>
      		<input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
      	#end
      	#if ($mode == 'view')
      		#if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
      			<!-- <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskini()"/> -->
      				#if($idStatus == '1610198')
      					<input type="button" name="cmdSeterusnya" id="cmdHantar" value="Ke Jabatan Teknikal" onClick="doSeterusnya()"/>
      					<input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
      				#end
      		#end
      #end
      #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
      		<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
      #end
    </td>
  </tr>
</table>
</fieldset>
