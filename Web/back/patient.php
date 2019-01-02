<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="patient_css.css">
    <meta charset="UTF-8">
    <title>Patient Profile</title>
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
        <img src="img_avatar2.jpg" alt="Avatar" class="avatar"><br>
        <div class="info">
            <label><b>Name:     </b></label><br></div>
        <div class="info"> <label><b>Address:     </b></label><br></div>
        <div class="info">
            <label><b>Date of birth:     </b></label><br></div>
        <div class="info">
            <label><b>Gender:     </b></label><br></div>
        <div class="info">
            <label><b>Phone:     </b></label><br></div>
        <button type = "submit" class = "user_log" name = "edit">Edit profile</button>
    </div>
</body>
</html>