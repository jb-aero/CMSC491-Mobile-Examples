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

$con = mysql_connect("host", "user", "password");

if (!$con)
{
	die("Did not connect to the mysqlserver: " . mysql_error());
}

$db_sel = mysql_select_db("friendfinder", $con);
if (!$db_sel)
{
	die("DB not selected: " . mysql_error());
}

if(!table_exists($id))
{
	$query = "CREATE TABLE $id(Date DATETIME PRIMARY KEY, Latitude DECIMAL(10,16), Longitude DECIMAL(10,16)";

	$result = mysql_query($con, $query);
	if(!$result)
	{
		die("The table was not created " . mysql_error());
	}
}

$query = "INSERT INTO $id(Date, Latitude, Longitude) VALUES('$timestamp','$latitude','$longitude')";
$result = mysql_query($con, $query);
if(!$result)
{
	die("Data was not inserted into the table " . mysql_error());
}

mysqli_close($con);

?>
