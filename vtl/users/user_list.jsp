<style type="text/css">
			/*demo page css*/
			body{ font: 62.5% "Trebuchet MS", sans-serif; }
			.demoHeaders { margin-top: 2em; }
			#dialog_link {padding: .4em 1em .4em 20px;text-decoration: none;position: relative;}
			#dialog_link span.ui-icon {margin: 0 5px 0 0;position: absolute;left: .2em;top: 50%;margin-top: -8px;}
			ul#icons {margin: 0; padding: 0;}
			ul#icons li {margin: 2px; position: relative; padding: 4px 0; cursor: pointer; float: left;  list-style: none;}
			ul#icons span.ui-icon {float: left; margin: 0 4px;}
.require {
	color: #F00;
}
.legend {
	color: #F00;
	font-size:10px;
}
</style>	
<STYLE>
<!--
  .initial { background-color: #DDDDDD; color:#000000 }
  .normal { background-color: #CCCCCC }
  .highlight { background-color: #8888FF }
//.linkPengguna {
	color: #00F;
}
-->
</style>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="100%" border="0" cellspacing="2" cellpadding="5">
      <tr>
        <td>
        #if($errorMsg)
          <div class="ui-widget">
            <div class="ui-state-error ui-corner-all" style="padding: 0 .7em; color: #F00;">
              <table width="100%" border="0" cellspacing="3" cellpadding="2">
                <tr>
                <!--  <td width="4%" align="center">--><!--<img src="$urlLinkImg/jquery_custom/css/custom-theme/images/warning.png" width="32" height="32">--><!--</td>-->
                  <td width="96%"><strong>AMARAN:</strong> <em>$errorMsg</em></td>
                </tr>
              </table>
              </div>
            </div>
          #end
          </td>
      </tr>
      <tr>
        <td>#if($kemasMsg)<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td bgcolor="#0066CC"><table width="100%" border="0" cellspacing="1" cellpadding="0">
              <tr>
                <td bgcolor="#33CCFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td width="2%">&nbsp;</td>
                    <td width="98%"><strong>Informasi pengguna berjaya di kemaskinikan!</strong></td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
          </tr>
        </table>#end</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    
    <fieldset>
      <legend>CARIAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="0">
        <tr>
          <td align="right">Pengguna</td>
          <td align="center">:</td>
          <td><input name="user" type="text"  size="45"  value="$user" /></td>
        </tr>
        <tr>
          <td align="right">&nbsp;</td>
          <td align="center">&nbsp;</td>
          <td><strong>(melalui)</strong></td>
        </tr>
        <tr>
          <td align="right">&nbsp;</td>
          <td align="center">&nbsp;</td>
          <td><select name="searchType" id="searchType" onChange="doChanges()">
          #foreach($type in $searchType)
            <option value="$type" #if($type == $selectSearch) selected #end >$type</option>
          #end
          </select></td>
        </tr>
        <tr>
          <td width="25%" align="right">Jenis Pengguna</td>
          <td width="1%" align="center">:</td>
          <td width="74%">
            
            
            <select name="selectJenisPengguna" id="selectJenisPengguna" onChange="doChanges()">

          #set ($listJenis = ["INTERNAL","ONLINE","KJP"])
          #foreach ($jenis in $listJenis)
          <option value="$jenis" #if ($jenis == $selectJenisPengguna) selected #end> 
          $jenis 
          </option>
          
          ##<option value="$jenis.UserType" #if ($jenis.UserType == $selectJenisPengguna) selected #end> $jenis.UserType </option>
          #end         
          
          
          </select> 
            <!--(<span class="require">*</span>)-->
            </td>
        </tr>
        #if($selectJenisPengguna == "INTERNAL")
        <tr >
          <td width="25%" align="right">Jawatan</td>
          <td width="1%" align="center">:</td>
          <td width="74%">$selectJawatan</td>
        </tr>
       
        <tr>
          <td width="25%" align="right">Seksyen</td>
          <td width="1%" align="center">:</td>
          <td width="74%">$selectSeksyen</td>
        </tr>
        #end
        <tr>
          <td width="25%" align="right">Negeri</td>
          <td align="center">:</td>
          <td>$selectNegeri</td>
        </tr>
        #if($selectJenisPengguna == "INTERNAL")
        <tr>
          <td align="right">Pejabat</td>
          <td align="center">:</td>
          <td>$selectPejabatJKPTG</td>
        </tr>

        <tr>
          <td align="right">Role</td>
          <td align="center">:</td>
          <td><select name="user_role" onChange="doChanges()">
				<option>Sila Pilih Role Pengguna</option>
            #foreach ( $role in $userRoles )
                #if ( $selectRole == $role.getName() )
                    #set ( $selected = "selected")
                #else
                    #set ($selected = "")
                #end
                <option value="$role.getName()" $selected>$role.getName()</option>
            #end
            </select></td>
        </tr>

        <tr>
          <td align="right">Susun Mengikut</td>
          <td align="center">:</td>
          <td>
          <select name="orderBy">
          #set ($listJenis = ["ID Pengguna","Nama","Negeri","Role"])
          #foreach ($jenis in $listJenis)
          <option value="$jenis" #if ($jenis == $orderBy) selected #end> 
          $jenis 
          </option>
           #end         
          </select> 
           
          </td>
        </tr>
        #end

        <tr>
          <td width="25%" align="right">&nbsp;</td>
          <td width="1%" align="center">&nbsp;</td>
          <td width="74%"><input class="stylobutton" type="button" value="Cari" onClick="submitCarian('carian')" />
            <input class="stylobutton" type="button"  value="Kosongkan" onClick="resetFields('clear')"/></td>
        </tr>
        <tr>
          <td align="right">&nbsp;</td>
          <td align="center">&nbsp;</td>
          <!--
          <td><em class="legend">(<span class="require ">*</span>) <span class="legend">Simbol ini adalah wajib di pilih</span></em></td>
          -->
        </tr>
      </table>
    </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>
    #if($listUsers)
    <fieldset>
      <legend>
      #if($submit == "carian")
      KEPUTUSAN SENARAI PENGGUNA 
      #else
      SENARAI PENGGUNA 
      #end
      </legend>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><table width="100%" cellpadding="1" cellspacing="1">
            <tr>
              <td class="table_header" colspan="6"><table width="100%" cellpadding="0" cellspacing="0" border="0">
                <tr></tr>
                </table>
                <table width="100%" cellpadding="1" cellspacing="1">
                  <tr>
                    <td class="table_header" colspan="6"><table width="100%" cellpadding="2" cellspacing="0" border="0">
                      <tr>
                        <td class="table_header" nowrap>#if($submit!="carian")#else
                          <h4>SENARAI PENGGUNA</h4>
                          #end</td>
                        <td class="table_header" nowrap align="right">
                        #if($totalRecords != 0)
                        <input type="submit" name="button" id="button" value="Delete" onClick="delete_user()">
                        #end
                        </td>
                      </tr>
                      #if (!$listUsers.isEmpty())
                      <tr>
                        <td colspan="2" bgcolor="#fbf8cd" >
                        #if($submit == "carian")
                        	#parse("app/utils/record_paging2.jsp")
                        #else
                        	#parse("app/utils/record_paging4.jsp")
                        #end
                        </td>
                        </tr>
                        #end
                      </table></td>
                    </tr>
                       #if (!$listUsers.isEmpty())
                  <tr>
                  
                    <td align="center" width="5%"><strong>NO.</strong></td>
                    <td width="15%"><strong>ID PENGGUNA</strong></td>
                    <td width="25%"><strong>NAMA</strong></td>
                    <td width="25%"><strong>NEGERI</strong></td>
                    <td align="center" width="20%" ><strong>ROLE</strong></td>
                    <td align="center" width="5%"><input type="checkbox" name="all" onClick="doCheckAll()"></td>
                    </tr> #end
                  #if (!$listUsers.isEmpty())
                  #foreach ($user in $listUsers)
                  #set ($rowCount = $velocityCount)
                  #if ( ($rowCount % 2) == 0 )
                  #set( $rowClass = "row2" )
                  #else
                  #set( $rowClass = "row1" )
                  #end
                  <tr >
                    <td align="center" class="$rowClass">$rowCount</td>
                    <td class="$rowClass">
                    <a href="javascript:doAjaxCall${formname}('detail','id_user=$user.User_id&JenisPengguna=$user.User_type');" class="linkPengguna"><u>$user.User_login</u></a> </td>
                    <td class="$rowClass">$user.User_name</td>
                    <td class="$rowClass">$user.Nama_negeri</td>
                    <td align="center" class="$rowClass">$user.User_role</td>
                    <td align="center" class="$rowClass"><input type="checkbox" value="$user.user_login/$user.user_type" name="users"></td>
                  </tr>
                    
                  #end
                  #else
                  <tr>
                    <td class="row1" colspan="6" align="left"><strong>Jumlah Rekod: $totalRecords<br>
                      <br>TIADA PENGGUNA</strong></td>
                    </tr>
                  #end
                  </table>
                <table width="100%" cellpadding="0" cellspacing="0" border="0">
                  </table></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td><table width="100%" border="0" cellspacing="3" cellpadding="0">
          #if (!$listUsers.isEmpty())
            <tr>
              <td>
              #if($submit == "carian")
              	#parse("app/utils/record_paging3.jsp")
               #else
               	#parse("app/utils/record_paging5.jsp")
               #end
              </td>
            </tr>
          #end
          </table></td>
        </tr>
        </table>
    </fieldset>
    #end
    </td>
  </tr>
</table>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name=id_daerah value="$!pejabatJKPTGInfo.get("id_daerah")">  
</body>
<script>
function doCheckAll()
{    
    if (document.${formName}.all.checked == true)
    {
        if (document.${formName}.users.length == null)
        {
            document.${formName}.users.checked = true;
        } else {
            for (i = 0; i < document.${formName}.users.length; i++)
            {
                document.${formName}.users[i].checked = true;
            }
        }
    } else {
        if (document.${formName}.users.length == null)
        {
            document.${formName}.users.checked = false;
        } else {
            for (i = 0; i < document.${formName}.users.length; i++)
            {
                document.${formName}.users[i].checked = false;
            }
        }
    }
}
function submitCarian(s) {	
    document.${formName}.command.value = s;
    document.${formName}.action = "";
    document.${formName}.submit();
}
function submitKemaskini(s, t) {
   doAjaxCall${formname}('kemaskini','id_user=t');
}

function resetFields(s) {
    document.${formName}.command.value = s;
    document.${formName}.action = "";
    document.${formName}.submit();
    //reset also the drop down please...
}

function delete_user() {
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = 'deleteUser';
	document.${formName}.action = "";
	document.${formName}.submit();
	}
	
}

function doChanges() {
	doAjaxCall${formName}("doChanges");
}

function showhide(layer_ref,displayType) {

	if (displayType =="show") displayType = "display:inline";
	else displayType = "display:none";
	
	if (document.all) { //IS IE 4 or 5 (or 6 beta)
	eval( "document.all." + layer_ref + ".style.display = "+displayType);
	}
	if (document.layers) { //IS NETSCAPE 4 or below
	document.layers[layer_ref].display = displayType ;
	}
	if (document.getElementById &&!document.all) {
	hza = document.getElementById(layer_ref);
	hza.style.display = displayType;
	}

} 


</script>
