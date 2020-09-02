<?php 

    require("function.php");

    if( !empty($_POST["title"]) && !empty($_POST["desc"]) && !empty($_POST["author"]) && !empty($_POST["status"]) ) {

        $title  = $_POST['title'];
        $desc   = $_POST['desc'];
        $author = $_POST['author'];
        $status = $_POST['status'];
        $data   = array();
        $query  = "INSERT INTO `task` (`id`, `title`, `description`, `author`, `status`) VALUES (NULL, '$title', '$desc', '$author', '$status')";

        $result     = mysqli_query($link,$query);
        setActionResponse(true,"Berhasil menambahkan tugas");

    }else {
        setActionResponse(false,"Isi semua form task");
    }
