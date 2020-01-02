<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>

<input name="id_kementerian" type="hidden" id="id_kementerian" value="$id_kementerian"/>
<input name="id_agensi" type="hidden" id="id_agensi" value="$id_agensi"/>
<input name="id_negeri" type="hidden" id="id_negeri" value="$id_negeri"/>
<input name="id_daerah" type="hidden" id="id_daerah" value="$id_daerah"/>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    <fieldset>
        <legend><b>Semakan Permohonan</b></legend>
        <table width="100%" align="center" border="0">
          <tbody>
            <tr>
              <td width="30%" height="24" scope="row" align="right"> Nama Kementerian : </td>
              <td width="70%">$!selectKementerian</td>
            </tr>
            <tr>
              <td width="30%" height="24" scope="row" align="right"> Nama Agensi : </td>
              <td width="70%">$!selectAgensi</td>
            </tr>
            <tr>
              <td width="30%" height="24" scope="row" align="right"> Negeri : </td>
              <td width="70%">$!selectNegeri</td>
            </tr>
            <tr>
              <td width="30%" height="24" scope="row" align="right"> Daerah : </td>
              <td width="70%">$!selectDaerah</td>
            </tr>
            <!-- <tr>
              <td width="30%" height="24" scope="row" align="right"> Mukim : </td>
              <td width="70%">$!selectMukim</td>
            </tr> -->
            <tr>
              <td width="30%" height="24" scope="row" align="right"> Nama Projek : </td>
              <td width="70%"><input name="findNamaProjek" id="findNamaProjek" type="text" value="$!findNamaProjek" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
            </tr>
            <tr>
              <td width="30%" height="24" scope="row" align="right"> No Rujukan Online : </td>
              <td width="70%"><input name="findNoRujukan" id="findNoRujukan" type="text" value="$!findNoRujukan" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
            </tr>
            <tr>
              <td scope="row"></td>
              <td><input name="cmdCari" id="cmdCari" value="Semak" type="button" onClick="javascript:carian()">
                <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan()"></td>
            </tr>
            <tr>
              <td scope="row">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </tbody>
        </table>
	  </fieldset>
    </td>
  </tr>
  <tr>
    <td>
    <fieldset>
		<legend><b>Senarai Status Permohonan</b></legend>
		#parse("app/utils/record_paging.jsp")
        <table align="center" width="100%"> 
          <tbody>
            <tr class="table_header">
              <td scope="row" width="2%"><strong>Bil</strong></td>
              <td width="20%"><strong>Tajuk</strong></td>
              <td width="10%"><strong>No Rujukan Online</strong></td>
              <td width="10%"><strong>No Fail</strong></td>
              <td width="18%"><strong>Kementerian</strong></td>
              <td width="15%"><strong>Agensi</strong></td>
              <td width="5%"><strong>Negeri</strong></td>
              <td width="5%"><strong>Daerah</strong></td>
              <td width="7%"><strong>Status</strong></td>
              <td width="8%"><strong>Tindakan</strong></td>
            </tr>
          #set ($list = "")
		  #if ($SenaraiFail.size() > 0)
		  #foreach ($list in $SenaraiFail)
		  #if ($list.bil == '')
			#set( $row = "row1" )
		  #elseif (($list.bil % 2) != 0)
			#set( $row = "row1" )
		  #else 
			#set( $row = "row2" )
		  #end
          <tr>
            <td class="$row" valign="top">$list.bil</td>
            <td class="$row" valign="top">$list.TUJUAN</td>
            <td class="$row" valign="top">$list.NO_PERMOHONAN_ONLINE</td>
            <td class="$row" valign="top">$list.NO_FAIL</td>
            <td class="$row" valign="top">$list.NAMA_KEMENTERIAN</td>
            <td class="$row" valign="top">$list.NAMA_AGENSI</td>
            <td class="$row" valign="top">$list.NAMA_NEGERI</td>
            <td class="$row" valign="top">$list.NAMA_DAERAH</td>
            <td class="$row" valign="top">$list.KETERANGAN</td>
            <td class="$row" valign="top">$list.ROLE</td>
          </tr>
          #end
          #else
          <tr>
            <td class="row1" align="center">&nbsp;</td>
            <td class="row1" colspan="4">Tiada Rekod</td>
          </tr>
          #end
          </tbody>
        </table>
		</fieldset>
	</td>
  </tr>
</table>
<script>
function carian(){
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.UtilitiStatusKJP";
	document.${formName}.method="POST";
	document.${formName}.command.value = "carian";
	document.${formName}.submit();
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.findNoRujukan.value = "";
	document.${formName}.findNamaProjek.value = "";
	//document.${formName}.submit();
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.UtilitiStatusKJP";
	document.${formName}.method="POST";
	//	document.${formName}.action.value = "";
	document.${formName}.submit();
}
function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}
function doChangeNegeri() {
 	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeDaerah() {
 	doAjaxCall${formName}("doChangeDaerah");
}
</script>