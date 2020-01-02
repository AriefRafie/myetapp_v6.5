<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
        #if($selectJenisPengguna == "INTERNAL" || $selectJenisPengguna == "ONLINE" )
        <tr>
          <td width="25%" align="right">Negeri</td>
          <td align="center">:</td>
          <td>$selectNegeri</td>
        </tr>
        #end
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
        
        #end


        #if($selectJenisPengguna == "KJP")
        <tr>
          <td width="25%" align="right">Kementerian</td>
          <td align="center">:</td>
          <td>$selectKementerian</td>
        </tr>
                <tr>
	          <td width="25%" align="right">Agensi</td>
	          <td align="center">:</td>
	          <td>$selectAgensi</td>
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
                    <td width="10%"><strong>ID PENGGUNA</strong></td>
                    <td width="25%"><strong>NAMA</strong></td>
                    <td width="25%"><strong>KEMENTERIAN</strong></td>
                    <td width="20%" ><strong>AGENSI</strong></td>
                    <td width="15%" ><strong>JAWATAN</strong></td>
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
                    <a href="javascript:doAjaxCall${formname}('detail','id_user=$user.User_id&JenisPengguna=KJP');" class="linkPengguna"><u>$user.User_login</u></a> </td>
                    <td class="$rowClass">$user.User_name</td>
                    <td class="$rowClass">$user.getNama_kementerian()</td>
                    <td class="$rowClass">$user.getNama_agensi()</td>
                    <td class="$rowClass">$user.getNama_jawatan()</td>
                    <td align="center" class="$rowClass"><input type="checkbox" value="$user.user_login/$user.getUser_type()" name="users"></td>
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
   doAjaxCall${formname}('kemaskini','JenisPengguna=KJP&id_user='+ t);
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

function doChangesKJP() {
	doAjaxCall${formName}("kemaskini",'mode=doChanges&id_user=$listUser.user_id&JenisPengguna=$listUser.user_type' );

}

function update_dataKJP() {
	doAjaxCall${formName}("updateDataKJP");
}


function batal(s) {	
    document.${formName}.command.value = s;
    document.${formName}.action = "";
    document.${formName}.submit();
}

</script>
