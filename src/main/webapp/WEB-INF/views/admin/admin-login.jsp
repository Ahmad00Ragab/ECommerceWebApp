<!DOCTYPE html>
<html lang="en">

<!-- ======= Head ========= -->
<head>
    <meta charset="UTF-8">
    <title>Admin Login</title>
</head>


<!-- ======= Body ========= -->
<body>

    <!-- =========================== HTML Section ======================== -->
    <h1> Admin Login</h1>

        <!-- User Name Verification With AJAX -->
                <b>User Name verification with AJAX </b><br>
                User Name 
                <input type="text" id="email" value="" size="50" onblur="submitForm2()">
                <span id="err" style="margin-left: 10px;"></span><br><br>
                <!-- <div id="err"></div><br> -->
                Password
                <input type="text" id="password"><br>
                <input type="button" value="Submit">

    <!-- =========================  JavaScript Section ========================= -->
    <script>
        var req3 = null;
       
        /* ============= Third Request: Verify User ============= */
        function submitForm2() {
            if (window.ActiveXObject)
                req3 = new ActiveXObject("Microsoft.XMLHTTP");
            else if (window.XMLHttpRequest)
                req3 = new XMLHttpRequest();
            req3.onreadystatechange = handleStateChange3;
            var email = document.getElementById("email").value;
            req3.open("Post", "AdminLoginServlet?username=" + email, true);
            req3.send(null);
        }


        function handleStateChange3() {
            if (req3.readyState == 4) {
                var textValue = req3.responseText;
                document.getElementById("err").innerHTML = textValue;
            }
        }
    </script>



</body>

</html>
