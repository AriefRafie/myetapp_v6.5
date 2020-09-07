
<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>
#set($ModuleName = "${session.getAttribute('_portal_module')}")

<!--
#if($list_default == "yes")
#set($txtNoFail="")
#set($txtNoRujJkptgNegeri="")
#set($socKementerian="")
#set($socUrusan="")
#set($socStatus="")
#end
-->

#parse("app/ppt/ListFailOnlineExpired.jsp") 

#if($ModuleName!="ekptg.view.ppt.FrmPenarikanBalikInternalOnline" && $ModuleName!="ekptg.view.ppt.FrmPembatalanInternalOnline")     
    <fieldset>
    <legend>CARIAN</legend>
    <table width="100%">
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="18%" ><div align="left">No. Fail</div></td>
    <td width="1%">:</td>
    <td width="80%">
      <label>
        <input name="txtNoFail" type="text" id="txtNoFail" size="40" maxlength="100" value="$!txtNoFail" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" >
        <input name="txtNoRujJkptgNegeri" type="hidden" id="txtNoRujJkptgNegeri" size="70" maxlength="100" value="$!txtNoRujJkptgNegeri" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" >
        </label>      </td>
  </tr>
  <!--
  <tr>
    <td>&nbsp;</td>
    <td><div align="left">No Ruj JKPTG Negeri</div></td>
    <td>:</td>
    <td>
      <label>
        
        </label>    </td>
  </tr>
  -->
  
  <!--
  #if($jenis_permohonan != '3'  && $jenis_permohonan != '1')
  <tr>
    <td>&nbsp;</td>
    <td><div align="left">Nama Kementerian</div></td>
    <td>:</td>
    <td>
      <label>
      
      <select name="socKementerian" id="socKementerian" class="autoselect">
      
      #if($!socKementerian == "")
      <option value="">SILA PILIH KEMENTERIAN</option>
      #else
      #foreach($listkementerian in $list_kementerian)
      #if($!socKementerian == $listkementerian.ID_KEMENTERIAN)    
      <option value="$listkementerian.ID_KEMENTERIAN">$listkementerian.KOD_KEMENTERIAN - $listkementerian.NAMA_KEMENTERIAN</option>
      #end
      #end
      #end
        
       #foreach($listkementerian in $list_kementerian)
       #if($!socKementerian != $listkementerian.ID_KEMENTERIAN)    
       <option value="$listkementerian.ID_KEMENTERIAN">$listkementerian.KOD_KEMENTERIAN - $listkementerian.NAMA_KEMENTERIAN</option>
       #end       
       #end
       
       #if($!socKementerian!="")
        <option value="">SILA PILIH KEMENTERIAN</option>
        #end
      </select>
      </label>       </td>
  </tr>
  #end
  
  <tr style="display:none">
    <td>&nbsp;</td>
    <td><div align="left">Urusan</div></td>
    <td>:</td>
    <td>
        <label>       
        <select name="socUrusan" id="socUrusan" class="autoselect">
        #if($!socUrusan=="")
        <option value="">SILA PILIH URUSAN</option>
        #else
        #foreach($listurusan in $list_urusan)
        #if($!socUrusan==$listurusan.ID_SUBURUSAN)
        <option value="$listurusan.ID_SUBURUSAN">$listurusan.KOD_SUBURUSAN - $listurusan.NAMA_SUBURUSAN</option>
        #end
        #end
        #end
      	#foreach($listurusan in $list_urusan)
        #if($!socUrusan!=$listurusan.ID_SUBURUSAN)
        <option value="$listurusan.ID_SUBURUSAN">$listurusan.KOD_SUBURUSAN - $listurusan.NAMA_SUBURUSAN</option>
        #end
        #end
        #if($!socUrusan!="")
        <option value="">SILA PILIH URUSAN</option>
        #end
        
        </select>
        </label>      
        </td>
  </tr>
  
  --> 
  
  <!--
  #if($jenis_permohonan != '3'  && $jenis_permohonan != '1')
   <tr>
    <td>&nbsp;</td>
    <td><div align="left">Status</div></td>
    <td>:</td>
    <td>
      <label>
        
         <select name="socStatus" id="socStatus" class="autoselect">
        #if($!socStatus=="")
        <option value="">SILA PILIH STATUS</option>
        #else
        #foreach($liststatus in $list_status)
        #if($!socStatus==$liststatus.ID_STATUS)
        <option value="$liststatus.ID_STATUS">$liststatus.KOD_STATUS - $liststatus.KETERANGAN</option>
        #end
        #end
        #end
        
        
      	#foreach($liststatus in $list_status)
        #if($!socStatus!=$liststatus.ID_STATUS)
        <option value="$liststatus.ID_STATUS">$liststatus.KOD_STATUS - $liststatus.KETERANGAN</option>
        #end
        #end
        #if($!socStatus!="")
        <option value="">SILA PILIH STATUS</option>
        #end
        </select>
        </label>       </td>
  </tr> 
  #end
 
--> 
  
  <tr>
    <td>&nbsp;</td>
    <td><div align="left"></div></td>
    <td>&nbsp;</td>
    <td>
      <label>
        <input type="button" name="cmdCari " id="cmdCari " value="Cari" onClick="cari()">
        </label>
      <label>
      <input  type="button" name="cmdIsiSemula  " id="cmdIsiSemula  " value="Kosongkan" onClick="batal()">
      </label>    </td>
  </tr>
</table>

    </fieldset>
#else

	<!-- Carian -->
	#parse("app/ppt/frmCarianOnlinePPT.jsp")
	<br/>
#end


    <fieldset>
    <legend>SENARAI PERMOHONAN  #if($jenis_permohonan == '4'  || $jenis_permohonan == '3')
    PENARIKAN BALIK
    #else
    PEMBATALAN
    #end</legend>
    
    <!-- <input name="cmdPapar" type="button" value="Papar" onClick="papar()"> -->
    #if($listdepan_size!=0)
    #parse("app/utils/record_paging.jsp")
    #end
    
    <table width="100%"  cellpadding="1" cellspacing="2" border="0">
		<tr>
			#if($jenis_permohonan == '4'  || $jenis_permohonan == '3')
			<td><i><font style=font-size:10px>Hanya fail yang boleh dibuat Penarikan Balik sahaja yang akan dipaparkan pada senarai ini.</font></i></td>
			#else
			<td><i><font style=font-size:10px>Hanya fail yang boleh dibuat Pembatalan sahaja yang akan dipaparkan pada senarai ini.</font></i></td>
			#end
		</tr>
			#if($jenis_permohonan == '4'  || $jenis_permohonan == '3')
			<td><i><font style=font-size:10px>Proses Penarikan Balik boleh dibuat setelah Borang D diwartakan dan sebelum Borang K dikeluarkan.</font></i></td>
			#else
			<td><i><font style=font-size:10px>Proses Pembatalan boleh dibuat sebelum Borang D diwartakan.</font></i></td>
			#end
			
		<tr>
			
		</tr>
	</table>
 
    <table width="100%">
  <tr class="table_header">
    <td width="4%" align="center"><b>Bil</b></td>
    <td><b>No.Fail Permohonan</b></td>
    <td><b>No.Fail PTG</b></td>
    <td><b>No.Fail PTD</b></td>
    <td><b>No.Fail UPT</b></td>
    <!--
    <td>No. Tarik Balik</td>
    <td>Tarikh Tarik Balik</td>   
    --> 
  <!--  <td width="15%">No. Jkptg Negeri</td> -->
    #if($jenis_permohonan == '2'  || $jenis_permohonan == '4')
    <td><b>Kementerian</b></td>
    <td><b>Status</b></td>
    #end
  </tr>
  
  #if($listdepan_size!=0)
           #foreach($list in $listdepan)         
           #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
         		
  <tr>
    <td class="$row" align="center">
    $list.BIL          
    </td>
    <td class="$row" ><!-- a href="javascript:papar('$list.ID_PERMOHONAN','')" -->
    <div>$!list.NO_FAIL</div>                                    
    </a>       </td>
    <td class="$row" ><!-- a href="javascript:papar('$list.ID_PERMOHONAN','')" -->
    <div>$!list.NO_RUJUKAN_PTG</div>                                    
    </a>       </td>
    <td class="$row" ><!-- a href="javascript:papar('$list.ID_PERMOHONAN','')" -->
    <div>$!list.NO_RUJUKAN_PTD</div>                                    
    </a>       </td>
    <td class="$row" ><!-- a href="javascript:papar('$list.ID_PERMOHONAN','')" -->
    <div>$!list.NO_RUJUKAN_UPT</div>                                    
    </a>       </td>
    <!--
    <td class="$row" >$list.NO_PEMBATALAN</td>
    <td class="$row" >$list.TARIKH_PEMBATALAN</td>  
    -->  
  <!--  <td class="$row" >$list.NO_RUJUKAN_UPT</td>  -->
    #if($jenis_permohonan == '2'  || $jenis_permohonan == '4')
    <td class="$row" >$!list.NAMA_KEMENTERIAN</td>
    <td class="$row" >$!list.KETERANGAN</td>
    #end
  </tr>
  #end
  #else   	
    		<tr>
    			<td colspan="8">Tiada Rekod</td>
    		</tr>
  #end
</table>

    </fieldset>
  
<input type="hidden" name="sub_command" id="sub_command" />
<input type="hidden" name="id_permohonan" id="id_permohonan" />
<input type="hidden" name="id_pembatalan" id="id_pembatalan" />
 <input type="hidden" name="list_name" id="list_name" value="list_permohonan" />

#if($ModuleName=="ekptg.view.ppt.FrmPenarikanBalikInternalOnline")
<script>
function search_data(){
	document.${formName}.command.value = "xx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.submit();
}
function clearData() {
	document.${formName}.command.value = "clearValue";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.submit();
}
</script>
#else
<script>
function search_data(){
	document.${formName}.command.value = "xx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.submit();
}
function clearData() {
	document.${formName}.command.value = "clearValue";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.submit();
}
</script>
#end

<script>
function papar(id_permohonan,id_pembatalan)
{
	document.${formName}.command.value = "senarai";
	document.${formName}.sub_command.value = "papar";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();
}
function cari()
{	
	document.${formName}.action = "";
	document.${formName}.submit();
}
function batal()
{
	document.${formName}.txtNoFail.value = "";
	doAjaxCall${formName}("clearData");
}
</script>
