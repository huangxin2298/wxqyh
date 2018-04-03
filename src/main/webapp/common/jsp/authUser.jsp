<script>
    if(typeof ("${adminUser}")=="undefined" || "${adminUser.account}" == null
        || "${adminUser.account}" == ""){
        window.location.href = "${baseURL}/manager/login.jsp";
    }
</script>
