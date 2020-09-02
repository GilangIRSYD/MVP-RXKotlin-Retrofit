<?php 

    require("function.php");

    if(!empty($_POST["id"]) && !empty($_POST["status"])){

        $id = $_POST["id"];
        $status = $_POST["status"];
        
        $query = "UPDATE task SET status = '$status' WHERE id = '$id' ";
        $result = mysqli_query($link,$query);

        if ($result) {
            setActionResponse(true,"Status changed");
        }else {
            setActionResponse(false,"error : ".mysqli_error($link));
        }
    }
?>