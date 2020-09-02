<?php 

    require("function.php");

    if( !empty($_POST["id"]) ){
        $id     = $_POST["id"];

        $query  = "DELETE FROM task WHERE id = '$id'";
        $result = mysqli_query($link,$query);

        if ($result) {
            setActionResponse(true,"Data sudah terhapus");
        }else {
            setActionResponse(false,"Gagal menghapus data");
        }
        
    }
?>