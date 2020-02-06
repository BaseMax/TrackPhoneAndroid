<?php
// date_default_timezone_set("Asia/Tehran");
// $date=new DateTime(null, new DateTimeZone('Asia/Tehran'));
// $date=$date->format("Y/m/d-H:i:s");
include "jdf.php";
$date=jdate("Y/m/d-H:i:s");
if(isset($_POST["title"], $_POST["text"])) {
	$data="- " . $date . " : " . $_POST["title"] . " = " .$_POST["text"] . "\n";
	file_put_contents("data.txt", $data, FILE_APPEND);
	print "ok";
}
else if(isset($_GET["title"], $_GET["text"])) {
	$data="- " . $date . " : " . $_GET["title"] . " = " .$_GET["text"] . "\n";
	file_put_contents("data.txt", $data, FILE_APPEND);
	print "ok";
}
