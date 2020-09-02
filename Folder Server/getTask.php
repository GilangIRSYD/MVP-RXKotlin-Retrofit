<?php 

    require("function.php");

    $query = "SELECT * FROM task";
    $query2= "SELECT * FROM task WHERE status = 'complete'";
    $query3= "SELECT * FROM task WHERE status = 'uncomplete'";

    if(!empty($_GET["id"])){
        $id = $_GET['id'];
        $query = "SELECT * FROM task WHERE id = '$id'";
    }

    $result = mysqli_query($link,$query);
    $result2 = mysqli_query($link,$query2);
    $result3 = mysqli_query($link,$query3);

    $data = array();

    $dataCount = mysqli_num_rows($result);
    $complete = mysqli_num_rows($result2);
    $uncompleted = mysqli_num_rows($result3);

    


    while($dataset = mysqli_fetch_assoc($result)){
        $data[] = $dataset;
    }

setTaskResponse(true,$data,$dataCount,$complete,$uncompleted);


    

?>