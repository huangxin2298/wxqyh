<script>
    if (typeof ("${admin}") == "undefined" || "${admin.account}" == null
        || "${admin.account}" != "admin") {
        window.location.href = "${baseURL}/manager/login.jsp";
    }
</script>
