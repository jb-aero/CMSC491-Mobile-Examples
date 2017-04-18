<?php
/**
 * Created by PhpStorm.
 * User: jb_aero
 * Date: 4/6/2017
 * Time: 1:23 PM
 */

$latitude = $_REQUEST["latitude"];
$longitude = $_REQUEST["longitude"];
$timestamp = $_REQUEST["timestamp"];
$id = $_REQUEST["id"];

$mysqli = new mysqli("host", "user", "password", "dbname");

if ($mysqli->connect_errno)
{
	die("Did not connect to the mysqlserver: " . $mysqli->connect_errno . "\n");
}

$db_sel = mysql_select_db("friendfinder", $con);
if (!$db_sel)
{
	die("DB not selected: " . mysql_error());
}

if(!table_exists($id))
{
	$query = "CREATE TABLE $id(Date DATETIME PRIMARY KEY, Latitude DECIMAL(10,16), Longitude DECIMAL(10,16)";

	$result = $mysql->query($con, $query);
	if ($mysqli->errno)
	{
		die("The table was not created " . $mysqli->errno);
	}
}

$query = "INSERT INTO $id(Date, Latitude, Longitude) VALUES('$timestamp','$latitude','$longitude')";
$result = $mysqli->query($con, $query);
if(!$result)
{
	die("Data was not inserted into the table " . mysql_error());
}

mysqli_close($mysqli);

?>
