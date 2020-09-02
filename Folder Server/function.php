<?php 

    $host = "localhost";
    $user = "root";
    $password = "";
    $database = "tugeh";

    $link = mysqli_connect($host,$user,$password,$database);

    if ($link) {
        $_POST["isConnect"] = true;
    }else{
        $_POST["isConnect"] = false;
        die;
    }

    function setTaskResponse($isConnect,$data,$dataCount,$complete,$uncompleted){

        $result = array(
            'isConnect' => $isConnect,
            'dataCount' => $dataCount,
            'completeTask' => $complete,
            'uncompletedTask' => $uncompleted,
            'data' => $data
        );
        
        echo json_encode($result);

    }

    function setActionResponse ($isSuccess,$message){
        $result = array (

            'isSuccess' => $isSuccess,
            'message' => $message
        );

        echo json_encode($result);
    }

    

?>