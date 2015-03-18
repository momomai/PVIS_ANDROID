<?php
@mysql_connect("localhost","root","root");
mysql_select_db("pvis_psu");

//ดึงข้อมูลออกมาในรูปแบบ UTF 8
mysql_query("SET NAMES UTF8");
$id=$_GET['ActivityName'];


$q=mysql_query("SELECT * FROM activity_tb INNER JOIN location_volunteer ON activity_tb.LocationID = location_volunteer.LocationID  WHERE  ActivityName='$id' ");

while($e=mysql_fetch_assoc($q))
       $output[]=$e;

function jsonRemoveUnicodeSequences($struct) {
  return @preg_replace("/\\\\u([a-f0-9]{4})/e", "iconv('UCS-4LE','UTF-8',pack('V', hexdec('U$1')))", json_encode($struct));
}   
   
//print(json_encode($output));
print jsonRemoveUnicodeSequences($output);
mysql_close();
?>