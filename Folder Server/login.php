<?php 

    require("function.php");

    if ( !empty($_POST["email"]) && !empty($_POST["password"]) ){

        $email      = $_POST['email'];
        $password   = $_POST['password'];

        $reslut  =   mysqli_query($link,"
            SELECT * FROM user WHERE ( email = '$email' AND password = '$password')
        ");


        if (mysqli_num_rows($reslut) > 0){
            $user = mysqli_fetch_array($reslut);
            $fullname = "$user[1]&split&$user[2]";
            setActionResponse(true, $fullname );
        }else {
            setActionResponse(false,"email atau password salah");
        }
    }

?>