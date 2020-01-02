#set($ModuleName = "${session.getAttribute('_portal_module')}")


<table width="100%"  cellpadding="0" cellspacing="2" border="0">   
    <tr>
    	#if($ModuleName=="ekptg.view.ppt.FrmPermohonanUPTSek8" || $ModuleName=="ekptg.view.ppt.FrmUPTSek8Hakmilik")
      	<td width="30%" align="left"><input type="button" name="cmdTambah" value ="Tambah" onClick="javascript:tambahHakmilik('$!flag_subjaket');"></td>
    	#else
    	<td width="30%" align="left">&nbsp;</td>
    	#end
    	<td width="70%" align="right">Carian No.LOT<b>/</b>No.PT<b>/</b>Nama Pihak Berkepentingan :&nbsp;<input type="text" name="carianNoLot" value="$!carianNoLot" maxlength="20" size="20"><a href="javascript:cariLOT('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></td>
   	</tr>
</table>