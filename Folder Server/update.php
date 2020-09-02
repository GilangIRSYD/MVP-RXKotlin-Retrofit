<?php 

    require("function.php");

    if(!empty($_POST['id']) && !empty($_POST['title']) && !empty($_POST['desc']) && !empty($_POST['author']) && !empty($_POST['status'])){
        
        $id = $_POST['id'];
        $title  = $_POST['title'];
        $desc   = $_POST['desc'];
        $author = $_POST['author'];
        $status = $_POST['status'];

        $query = "UPDATE task SET title='$title', status='$status' ,description='$desc' WHERE id='$id' ";
        $result = mysqli_query($link,$query);

        if($result){
            setActionResponse(true,"Data berhasil diupdate");
        }else{
            setActionResponse(false,"Gagal mengubah data" . mysqli_error($link));
        }

        
    }else {
        setActionResponse(false,"isi semua data update" . mysqli_error($link));
    }
?>