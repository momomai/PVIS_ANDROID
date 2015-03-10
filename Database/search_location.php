<?php
include("connection.php");
?>

<?php
mysql_connect($hostname,$username,$password);
mysql_select_db($db_name);

//ดึงข้อมูลออกมาในรูปแบบ UTF 8
mysql_query("SET NAMES UTF8");
$location_name = $_GET['LocationName'];
$request_name = $_GET['RequestName'];
$project_name = $_GET['ProjectName'];

$q=mysql_query("SELECT\n".
"location_volunteer.LocationName\n".
"request_help.RequestName\n".
"request_help.ProjectName\n".

"FROM\n".
"location_volunteer\n".
"request_help\n".

"WHERE\n".
"location_volunteer.location_name LIKE '%{$location_name}' OR\n".
"location_volunteer.location_name LIKE '{$location_name}%' OR\n".
"location_volunteer.location_name LIKE '%{$location_name}%'"
"request_help.RequestName LIKE '%{$RequestName}' OR\n".
"request_help.RequestName LIKE '{$RequestName}%' OR\n".
"request_help.RequestName LIKE '%{$RequestName}%'"
"request_help.ProjectName LIKE '%{$ProjectName}' OR\n".
"request_help.ProjectName LIKE '{$ProjectName}%' OR\n".
"request_help.ProjectName LIKE '%{$ProjectName}%'");

while($e=mysql_fetch_assoc($q))
       $output[]=$e;

function jsonRemoveUnicodeSequences($struct) {
   return preg_replace("/\\\\u([a-f0-9]{4})/e", "iconv('UCS-4LE','UTF-8',pack('V', hexdec('U$1')))", json_encode($struct));
}   
   
//print(json_encode($output));
print jsonRemoveUnicodeSequences($output);
mysql_close();
?>