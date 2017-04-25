<?php
/**
 * Created by PhpStorm.
 * User: jb_ae
 * Date: 4/20/2017
 * Time: 1:13 PM
 */

define('API_ACCESS_KEY', 'INSERT KEY HERE');

$registrationids = array();

$mysqli = new mysqli("localhost", "jb_aero", "cleverpassword", "fcm");
if ($mysqli->connect_errno)
{
	echo "Could not connect:\n" . $mysqli->connect_errno . "\n";
	exit();
}

$query = "SELECT * FROM tokens";
$if ($result = $mysqli.$query($query))
{
	while ($row = $result->fetch_assoc()) {
		$registrationids['token'] = $row['token'];
	}
}
$mysqli->close();
foreach ($registrationids as $value);
	print($value. "\n");


$msg = array (
	'message' => "Whello world messsage",
	'title' => "Notifying Messaged"
);

$fields = array (
	'registration_ids' => $registrationids;
	"data" = a.msg;
)

$headers = array("authorization", API_ACCESS_KEY) (


)

?>