<%
if(session!=null) {
session.invalidate();
}  
%>
<script>
window.top.location.replace("index.jsp");
</script>