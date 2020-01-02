<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<p>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>CARIAN</b></legend>
      <table width="100%" align="center" border="0">
        <tr>
          <td width="30%" height="24" scope="row" align="right">No Aduan : </td>
          <td width="70%"><input name="noAduan" id="noAduan" type="text" size="30" maxlength="30" style="text-transform:uppercase;" ></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Status Aduan: </td>
          <td width="70%"><input name="statusAduan" id="statusAduan" type="text" size="30" maxlength="50"></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Tarikh Aduan : </td>
          <td width="70%"><input type="text" name="tarikhAduan" id="tarikhAduan"  size="9"/>
            <a href="javascript:displayDatePicker('tarikhAduan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
        <tr>
          <td scope="row"></td>
          <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:cariAduan()">
            <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan()"></td>
        </tr>
        <tr>
          <td scope="row">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><b>SENARAI ADUAN</b></legend>
       #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        <tr>
          <td colspan="6" scope="row"><input name="cmdTambah" type="button" value="Tambah" onclick="javascript:daftarBaru()" id="cmdTambah"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="10%"><strong>No Aduan</strong></td>
           <td width="30%"><strong>Nama Pengadu</strong></td>
          <td width="20%"><strong>Status Aduan</strong></td>
          <td width="15%" align="center"><strong>Tarikh Aduan</strong></td>
        </tr>
        #set ($count = 0)
      #foreach ( $fail in $SenaraiFail )
      #set ($count = $count+1)
          #set( $i = $velocityCount )
          #if ( ($i % 2) != 1 )
               #set( $row = "row2" )
          #else
               #set( $row = "row1" )
          #end
        <tr>
          <td class="$row" align="center">$!count</td>
          <td class="$row"><a href="javascript:detail('$fail.id')" class="style1">$fail.id</a></td>
          <td class="$row">$!fail.namaPengadu</td>
          <td class="$row">$!fail.status</td>
          <td class="$row" align="center">$!fail.tarikhAduan</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
	function detail(id){
   	   		doAjaxCall${formName}("viewComplaint","idComplaint="+id);
   	}
		
	function cariAduan(){
	doAjaxCall${formName}("search");
	//	doAjaxCall${formName}("displayComplaint");
	}

	function kosongkan() {
	document.${formName}.reset();
	document.${formName}.noAduan.value = "";
	document.${formName}.statusAduan.value = "";
	document.${formName}.tarikhAduan.value = "";
	doAjaxCall${formName}("");
	}
	
	function daftarBaru() {
		doAjaxCall${formName}("viewComplaint");
	
	}
	function kembali() {
		doAjaxCall${formName}("");
	
	}
	function daftar(){
		
		if(document.${formName}.idJenisAduan.value==""){
			alert('Sila Pilih Jenis Aduan');
			return;
		}
		else if(document.${formName}.name.value ==""){
			alert('Sila Isi Nama Pengadu');
			return;
		}
		else if(document.${formName}.catatan.value==""){
			alert('Sila Isi Butiran Aduan');
			return;
		}
		else{
			
			doAjaxCall${formName}("simpanComplaint");
		}
		
	}
	function simpanKemaskini(){
		if(document.${formName}.idJenisAduan.value==""){
			alert('Sila Pilih Jenis Aduan');
			return;
		}
		else if(document.${formName}.name.value ==""){
			alert('Sila Isi Nama Pengadu');
			return;
		}
		else if(document.${formName}.catatan.value==""){
			alert('Sila Isi Butiran Aduan');
			return;
		}
		else{
			doAjaxCall${formName}("simpanKemaskiniComplaint");
		}
		
	}

</script>
<script type="text/javascript">
	

	checkValidEmail = function() {
		//Check Valid Email
		$jquery("#email").keyup(function(){
			var email = $jquery("#email").val();
			if(email != 0)
			{
				if(isValidEmailAddress(email))
				{
					$jquery("#validEmail").css({
						"background-image": "url('../portal/validyes.png')"
					});
				} else {
					$jquery("#validEmail").css({
						"background-image": "url('../portal/validno.png')"
					});
				}
			} else {
				$jquery("#validEmail").css({
					"background-image": "none"
				});			
			}		
			
		});
	}

	isValidEmailAddress = function (emailAddress) {
		var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
		return pattern.test(emailAddress);
	}

	doEffect = function () {
		new Effect.Highlight('doRegisterResult', {startcolor:'#CEB089',endcolor:'#FFFFFF', restorecolor:'#EFEFEF'});
	}

	doHide = function () {
		$jquery('#RegistrationForm').hide("slow");
	}


			      
</script>
