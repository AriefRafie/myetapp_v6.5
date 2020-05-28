<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT ADUAN</li>
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">LOG AKTIVITI</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td colspan="2"><fieldset>
                  <strong>
                  <legend>MAKLUMAT ADUAN</legend>
                  </strong>
                  <table width="100%" border="0" cellspacing="2" cellpadding="2">
                    <tr>
                      <td width="1%">&nbsp;</td>
                      <td width="28%">No Aduan</td>
                      <td width="1%">:</td>
                      <td width="70%">$!complaint.id </td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>Status</td>
                      <td>:</td>
                      <td>$!complaint.status </td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>Nama Pengadu</td>
                      <td>:</td>
                      <td>$!complaint.namaPengadu</td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>Emel</td>
                      <td>:</td>
                      <td>$!complaint.emailPengadu </td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>No Telefon</td>
                      <td>:</td>
                      <td>$!complaint.phonePengadu </td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>Jenis Aduan</td>
                      <td>:</td>
                      <td>$!complaint.type.code -  $!complaint.type.description </td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>Catatan</td>
                      <td>:</td>
                      <td>$!complaint.catatan </td>
                    </tr>

                    <tr>
                      <td></td>
                      <td valign="top"> Lampiran </td>
                      <td valign="top"> : </td>
                      <td colspan="4"><table>
                          #foreach($lampiran in $complaint.lampiran)
                          <tr>
                            <td><a href="javascript:papar_Lampiran('$lampiran.id')" class="style1">$lampiran.fileName</a> </td>
                          </tr>
                          #end
                        </table></td>
                    </tr>
                  </table>
                  </fieldset></td>
              </tr>
              <tr>
                <td colspan="6"><fieldset>
                  <legend><strong>MESEJ</strong></legend>
                  <table width="50%" border="0" cellspacing="2" cellpadding="2">
                   
                    <tr>
                      <td>&nbsp;</td>
                      <td valign="top"></td>
                      
                      <td>$!response.arahan</td>
                    </tr>
                  </table>
                  </fieldset></td>
              </tr>
              <tr>
				  	<td>
				  		<fieldset>
				  		<legend>MAKLUMBALAS</legend>
				  		<table>
				  			<tr>
				  					 <td valign="top"><span class="style1">*</span></td>
				                      <td valign="top">Ulasan</td>
				                      <td valign="top">:</td>
				                      <td><textarea rows="10" cols="100" name="ulasanBalas" onblur="this.value=this.value.toUpperCase();">$!response.jawapan</textarea>
				                      </td>
				  			</tr>
				  			<tr>
				  				<td colspan="4" align="center">
				  					<input type="button" name="cmdCari" id="cmdCari" value="Simpan Draf Jawapan" #if($editable == true) onclick="simpan()" #else onclick="notAllowed()" #end"/>
				  					<input type="button" name="cmdCari" id="cmdCari" value="Tidak Lengkap" #if($editable == true) onclick="tidakLengkap()" #else onclick="notAllowed()" #end/>
				  					<input type="button" name="cmdCari" id="cmdCari" value="Tidak Relevan" #if($editable == true) onclick="tidakRelevan()" #else onclick="notAllowed()" #end/>
				  					<input type="button" name="cmdCari" id="cmdCari" value="Selesai" #if($editable == true) onclick="selesai()" #else onclick="notAllowed()" #end/>
				  					<input type="button" name="cmdEmel" id="cmdEmel" value="Hantar Emel" onclick="parent.location='mailto:?subject=ADUAN ONLINE NO $complaint.id'" />
				  				</td>
				  			</tr>
				  		</table>
				  		</fieldset>
				  	</td>
				  </tr>
              
            </table>
          </div>
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td><table align="center" width="100%">
                    <tr class="table_header">
                      <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
                      <td width="15%"><strong>Tarikh</strong></td>
                      <td width="80%"><strong>Aktiviti</strong></td>
                    </tr>
                    #set ($count = 0)
                    #foreach ( $fail in $complaint.logs )
                    #set ($count = $count+1)
                    #set( $i = $velocityCount )
                    #if ( ($i % 2) != 1 )
                    #set( $row = "row2" )
                    #else
                    #set( $row = "row1" )
                    #end
                    <tr>
                      <td class="$row" align="center">$!count</td>
                      <td class="$row">$!fail.tarikhMasuk</td>
                      <td class="$row">$!fail.aktiviti</td>
                    </tr>
                    #end
                  </table></td>
              </tr>
            </table>
          </div>
        </div>
      </div></td>
  </tr>
  
</table>
<input type="hidden" name="idRespon" value="$!response.id">
<p>
  <input type="hidden" name="idComplaint" value="$!complaint.id">
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
</p>
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
</script>