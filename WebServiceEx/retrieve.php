<?php
/**
 * Created by PhpStorm.
 * User: jb_aero
 * Date: 4/6/2017
 * Time: 1:51 PM
 */

$mysqli = new mysqli("host", "user", "password", "dbname");

if ($mysqli->connect_errno)
{
	die("Did not connect to the mysqlserver: " . $mysqli->connect_errno);
}

$listdbtables = $mysqli->query("SHOW TABLES");

$idarray = array();
$latarray = array();
$lonarray = array();
$i = 0;

while($row = $listdbtables->fetch_row())
{
	$tb_name = row[0];
	$query = "SELECT * FROM $tb_name ORDER BY Date DESC LIMIT 1";
	$result = $mysqli->query($con, $query);
	
	while($something = $result->fetch_assoc())
	{
		$fdate = mysql_result($result, $j, "Date");
		$flatitude = mysql_result($result, $j, "Latitude");
		$flongitude = mysql_result($result, $j, "Longitude");
		$longitudearray[] = $flongitude;
		$latitudearray[] = $flatitude;
		$j++;
	}
}

for ($k = 0; $k < $i; $k++)
{
	print("$idarray[$k],");
	print("$latitudearray[$k],");
	print("$longitudearray[$k]\n");
}

mysqli_close($con);
