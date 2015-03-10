<?php
	@mysql_connect("localhost","root","root");
	mysql_select_db("project3_se03");

// Create connection
$conn = new mysqli($servername, $username, $password);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
echo "Connected successfully";
?>