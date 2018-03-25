<%@ page import="java.util.Enumeration" %>
<%
    request.setCharacterEncoding("UTF-8");

    String baseURL = request.getContextPath();
    pageContext.setAttribute("baseURL", baseURL);

    Enumeration<String> enums = request.getParameterNames();
    while(enums.hasMoreElements()){
        String name = enums.nextElement();
        String value = request.getParameter(name);
        pageContext.setAttribute("param."+name, value);
    }
%>
<script>
    var baseURL = "${baseURL}";
</script>
