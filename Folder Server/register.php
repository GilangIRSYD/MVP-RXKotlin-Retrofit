<?php 

    require("function.php");

    if (!empty($_POST["first_name"]) && !empty($_POST["last_name"]) && !empty($_POST["email"]) && !empty($_POST["password"])) {

        $firstName = $_POST["first_name"];
        $lastName = $_POST["last_name"];
        $email = $_POST["email"];
        $password = $_POST["password"];

        $validate = mysqli_query($link,"
            SELECT * FROM user WHERE email = '$email'
        ");

        if (mysqli_num_rows($validate) > 0 ) {
            setActionResponse(false,"Email sudah terdaftar");
            die;
        }

        $query = "INSERT INTO user(first_name,last_name,email,password) VALUES (

            '$firstName',
            '$lastName',
            '$email',
            '$password'        
        )";
        $result = mysqli_query($link,$query);

        if ($result) {
             setActionResponse(true,"User berhasil ditambahkan");
        }else{
            setActionResponse(false,"User gagal ditambahkan");
            echo mysqli_error($link);
        }
    }else {
        setActionResponse(false,"Input tidak boleh kosong");
    }

?>