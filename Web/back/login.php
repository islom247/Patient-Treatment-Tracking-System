<?php
	include("config.php");
	session_start();
	include('config.php');
	if ($con->connect_error) {
		die("Connection failed: " . $con->connect_error);
	}
	if ($_SERVER["REQUEST_METHOD"] == "POST") {
		//checking whether the user input is empty
		if (empty($_POST["uname"])) {
			$username_error = "*";
			$field_error = "* Required fields!";
		}
		if (empty($_POST["psw"])) {
			$password_error = "*";
			$field_error = "* Required fields!";
		}
		$username = mysqli_real_escape_string($con, $_POST['uname']);
		$password = mysqli_real_escape_string($con, $_POST['psw']);
		$query = "SELECT * FROM user WHERE username = ? and password = ?";
		
		//preparing a statement
		if ($stmt = $con->prepare($query)) {
			//binding parameters into prepared statement
			$stmt->bind_param('ss', $username, $password);
			
			//executing the query(statement)
			$stmt->execute();
			
			//getting the result
			$result = $stmt->get_result();
			
			//number of rows in result set
			$count = $result->num_rows;
			
			//if count is 1 the user entered correct data
			if ($count == 1) {
				$_SESSION['username'] = $username;
				$_SESSION['password'] = $password;
				header("location: doctor.php");
			} else {
				//warning the user about the wrong data input
				$field_error = "Wrong username or password!";
				if ($password_error=="*" || $username_error == "*") {
					$field_error = "* Required fields!";
				}
			}
		}
	}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="index_css.css">
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <div class = "top">
        <img src="logo.png" class = "logo">
        <label id = "title"> Patient Medical Treatment Tracking System</label>
        <button type = "submit" class = "top_but" id = "sign" >Sign up</button>
        <button type = "submit" class = "top_but" id = "log" >Log in</button>
    </div>

    <div class="container">
		<form action="" method=post>
        <label><b>Username</b></label>
        <input type="text" placeholder="Enter Username" name="uname" required></input>

        <label><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required></input>

        <button type="submit" class = "user_log">Login</button>
        <label>
            <input type="checkbox" class = "checked" name = "remember"> Remember me</input>
        </label>
		<div><?php echo $field_error?></div>
		</form>
    </div>
</body>
</html>
