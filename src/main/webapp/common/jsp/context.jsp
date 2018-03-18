<%
    String baseURL = request.getContextPath();
    pageContext.setAttribute("baseURL", baseURL);
%>
<script>
    var baseURL = "${baseURL}";
</script>
