
<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0033FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
</p>

<div id="divMainForm">
  <input type="hidden" id="idHutang" name="idHutang" value="$!penghutang.idHutang">
  <input type="hidden" id="command1" name="command1" value="$!command">
  
  
  #if ($!command == 'daftarPenghutang')
  		#parse("app/ppk/eHutang/daftarPenghutang.jsp")
  #elseif ($!command == 'paparPenghutang' || $!command == 'simpanPenghutang' || $!command == 'kemaskiniPenghutang' || $!command == 'hapusHutang')
  		#parse("app/ppk/eHutang/paparPenghutang.jsp")
  #elseif ($!command == 'daftarHutang')
  		#parse("app/ppk/eHutang/daftarHutang.jsp")	
  #elseif ($!command == 'paparHutang' || $!command == 'simpanHutang' || $!command == 'kemaskiniHutang' || $!command == 'refreshUpload' || $!command == 'muatNaikDokumen')
  		#parse("app/ppk/eHutang/paparHutang.jsp")
  #else 
  		#parse("app/ppk/eHutang/senaraiPenghutang.jsp")
  #end 
</div>
  
#parse("app/ppk/eHutang/script.jsp") 