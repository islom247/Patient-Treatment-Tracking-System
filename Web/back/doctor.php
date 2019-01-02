<?php 
	include("config.php");
	session_start();
	if ($con->connect_error) {
		die("Connection failed: " . $con->connect_error);
	}
	if ($_SERVER["REQUEST_METHOD"] == "POST") {
		$query = "INSERT INTO address (country, city, street, apartment, apartment_num) VALUES(?,?,?,?,?)";
		if ($stmt = $con->prepare($query)) {
			$country = $_POST["country"];
			$city = $_POST["city"];
			$street = $_POST["street"];
			$apt_name = $_POST["apt_name"];
			$apt_no = $_POST["apt_no"];
			$stmt->bind_param('ssssi', $country, $city, $street, $apt_name, $apt_no);
			$stmt->execute();
			$add_id = mysqli_insert_id($con);
		}
		$query = "INSERT INTO user (username, image, phone, name, password, birthday, gender, add_id) VALUES(?,?,?,?,?,?,?,?)";
		if ($stmt = $con->prepare($query)) {
			$username = $_POST["uname"];;
			$check = getimagesize($_FILES["image"]["tmp_name"]);
			if ($check) {
				$image = $_FILES['image']['tmp_name'];
				$imgContent = addslashes(file_get_contents($image));
				$phone = $_POST["phone"];
				$name = $_POST["name"];
				$password = $_POST["psw"];
				$rawdate = htmlentities($_POST["date"]);
				$birthday = date('Y-m-d', strtotime($rawdate));
				$gender = $_POST["gender"];
				$stmt->bind_param("sbsssssi", $username, $null, $phone, $name, $password, $birthday, $gender, $add_id);
				$stmt->send_long_data(1,$imgContent);
				$stmt->execute();
			}
		}
	}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="patient_css.css">
    <meta charset="UTF-8">
    <title>Doctor Profile</title>
</head>
<body>
<div class = "top">
    <img src="logo.png" class = "logo">
    <label id = "title"> Patient Medical Treatment Tracking System</label>
    <button type = "submit" class = "top_but" id = "right_1" name = "logout" >Log out</button>
    <button type = "submit" class = "top_but" id = "right_2" name = "switch_doc">Doctor</button>
    <button type = "submit" class = "top_but" id = "right_3" name = "switch_pharm">Pharmacist</button>
</div>

<div class = "menu">
    <button type = "submit" class = "menu_but" id = "top_1" name = "profile" >Profile</button>
    <button type = "submit" class = "menu_but" id = "top_2" name = "app" >Appointments</button>
    <button type = "submit" class = "menu_but" id = "top_3" name = "doctors" >Doctors</button>
    <button type = "submit" class = "menu_but" id = "top_4" name = "set" >Settings</button>
    <button type = "submit" class = "menu_but" id = "top_5" name = "developer" >Developers</button>
</div>

<div class="container">
    <!--<img src="img_avatar2.jpg" alt="Avatar" class="avatar"><br>-->
	<?php
		$result = $con->query("SELECT image FROM user WHERE username = ".$username);
    
		if($result->num_rows > 0){
			$imgData = $result->fetch_assoc();
			
			echo '<img src="data:image/jpeg;base64,'.base64_encode( $result['image'] ).'"/>';
		}
	?>
    <div class="info">
        <label><b>Name:    <?php echo "  ".$name?> </b></label><br></div>
    <div class="info"> <label><b>Address:     <?php echo $country.',  '.$city.',  '.$street.',  '.$apt_name.' - '.$apt_no?></b></label><br></div>
    <div class="info">
        <label><b>Date of birth:     <?php echo "  ".$birthdate?></b></label><br></div>
    <div class="info">
        <label><b>Gender:     <?php echo "  ".$gender?></b></label><br></div>
    <div class="info">
        <label><b>Phone:     <?php echo "  ".$phone?></b></label><br></div>
    <button type = "submit" class = "user_log" name = "edit">Edit profile</button>
</div>
</body>
</html>