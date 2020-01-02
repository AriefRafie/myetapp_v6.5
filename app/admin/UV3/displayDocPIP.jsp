#if($viewPenggunaMOHON.NAMA_DOC!="")
<a href="javascript:paparDocPIP('$viewPenggunaMOHON.ID_PERMOHONAN')"><font color="blue"><u>$viewPenggunaMOHON.NAMA_DOC</u></font></a>
#end

<script>
//alert('FILE_'+'$internalType'+'$USER_ID'+' : ');
document.getElementById('FILE_'+'$internalType'+'$USER_ID').value="";
</script>
