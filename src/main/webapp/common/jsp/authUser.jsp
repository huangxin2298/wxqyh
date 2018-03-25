<script>
    if(typeof ("${adminUser}")=="undefined" || "${adminUser.userName}" == null || "${adminUser.userName}" == ""){
        window.location.href = "${baseURL}/manager/login.jsp";
    }
</script>
