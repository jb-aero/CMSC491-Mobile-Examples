<?php
/**
 * Created by PhpStorm.
 * User: jb_aero
 * Date: 4/6/2017
 * Time: 1:51 PM
 */

$con = mysql_connect("host", "user", "password");

if (!$con)
{
	die("Did not connect to the mysqlserver: " . mysql_error());
}

$list = mysql_list_tables("FriendFinder");

$idarray = array();
$latarray = array();
$lonarray = array();
$i = 0;

while($i < mysql_num_rows($list))
{
	$tb_names[$i] = mysql_tablename($list,$i);
	$query = "SELECT * FROM $tb_names[$i] ORDER BY Date DESC LIMIT 1";
	$result = mysql_query($con, $query);
	$j = 0;
	$num = mysql_num_rows($result);
	while($j < $num)
	{
		$fdate = mysql_result($result, $j, "Date");
		$flatitude = mysql_result($result, $j, "Latitude");
		$flongitude = mysql_result($result, $j, "Longitude");
		$longitudearray[] = $flongitude;
		$latitudearray[] = $flatitude;
		$j++;
	}
	$i++;
}

for ($k = 0; $k < $i; $k++)
{
	print("$idarray[$k],");
	print("$latitudearray[$k],");
	print("$longitudearray[$k]\n");
}

mysqli_close($con);
