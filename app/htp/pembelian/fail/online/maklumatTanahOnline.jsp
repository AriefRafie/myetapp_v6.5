
<table width="100%" border="0" cellspacing="2" cellpadding="2">

	<tr>
		<td>
<fieldset>
<legend>MAKLUMAT TANAH</legend>
<table width="100%">
	#set($portal_role = "${session.getAttribute('myrole')}")
	
	#if ($portal_role!='online_kjpagensi')
	<tr>
	  <td colspan="2">
	    <input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Tambah" onclick="doAjaxCall${formName}('tambahTanah')">      </td>
	</tr>
	#end
	<tr class="table_header">
		<td scope="col" width="3%">Bil.</td>
					<!--<td scope="col">Jenis Hakmilik</td> -->
		<td scope="col" width="20%">Jenis dan No. Hakmilik</td>
                  	<!-- <td scope="col">Kod Lot</td> -->
		<td scope="col" width="17%">No. Lot/PT</td>
					<!-- <td scope="col">Kod Luas</td> -->
		<td scope="col" width="20%">Luas(H)</td>
		<td scope="col" width="20%">No. Pelan</td>
		<td scope="col" width="10%">Tarikh Mula</td>
		<td scope="col" width="10%">Tarikh Luput</td>
		<td scope="col" width="5%">&nbsp;</td>
	</tr>
    #set ( $cnt = 0 )
    #foreach($mo in $mt)
    #set ( $cnt = $cnt + 1 )
    #if ($senarai.bil == '')
    #set( $row = "row1" )
    #elseif (($senarai.bil % 2) != 0)
    #set( $row = "row1" )
    #else 
    #set( $row = "row2" )
    #end
		<tr >
		  <td class="$row">$cnt.</td>
		  <!--<td class="$row">$!mo.getJenisKeterangan()</td> -->
		  <td class="$row"><a href="javascript:paparTanahOnline('$!mo.getIdhakmilikurusan()')" style="color:#00C">$!mo.getKodjenishakmilik()$!mo.nohakmilik</a></td>
		  <!--<td class="$row">$!mo.getKeteranganLot()</td> -->
		  <td class="$row">$!mo.getKeteranganLot()$!mo.getNolot()</td>
		  <!--<td class="$row">$!mo.getLuasKeterangan()</td> -->
		  <td class="$row">$!mo.getLuas() </td>
		  <td class="$row">$!mo.getNoPlan()</td>
		  <td class="$row">$!mo.getTarikhMula()</td>
		  <td class="$row">$!mo.getTarikhLuput()</td>
		  <td class="$row">
			#if ($portal_role!='online_kjpagensi')
		  	<input type="button" value="Hapus" onclick="javascript:deleteTanah('$!mo.getIdhakmilikurusan()','$!htpPermohonan.permohonan.getIdPermohonan()')">
		  	#end
		  </td>
    </tr>
      #end
      
</table>
</fieldset>
 		  </td>
    </tr>
   	 	<tr>
    
		    	<td >

        	<table width="100%" border="0">
          		<tr>
          			<td width="8%"><font color=red style=font-size:10px>Perhatian :</font></td>
          			<td width="92%">$!perhatian7</td>
          		</tr>
				<tr>
					<td>&nbsp;</td>
        			<td>$!perhatian8</td>
        		</tr>
        		<tr>
					<td>&nbsp;</td>
        			<td>$!perhatian9</td>
        		</tr>
			</table>    	
		    	</td>
		    
	 </tr>   
</table>
<input type="hidden" name="txtidPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>