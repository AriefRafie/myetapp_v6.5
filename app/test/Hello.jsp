Nama : <input type=text name=nama>
IC : <input type=text name=nokp>
<input type=button value=Hantar onclick="x()">
<input type=button value=Test onclick="sayTest()">
<input type=button value=SIMPAN onclick="simpan()">

<br>
#if ( $name != "" )
<b><font size="4">
HELLO THERE...<br> $name <br>
IC = $ic <br>
x = $x <br>
y = $y
</font></b>
#end


#if ( $result != "" )
Data telah Disimpan
#end

<script>
function x(){
   doAjaxCall${formName}("sayHelloXXX","x=hehe&y=jengjeng");
}

function sayTest(){
	   doAjaxCall${formName}("sayTest");
}

function simpan(){
	   doAjaxCall${formName}("doSimpan");
}


</script>