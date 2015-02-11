<?php
@mysql_connect("localhost","root","");
mysql_select_db("project3_se03");

//ดึงข้อมูลออกมาในรูปแบบ UTF 8
mysql_query("SET NAMES UTF8");

$q=mysql_query("SELECT * FROM activity");

while($e=mysql_fetch_assoc($q))
       $output[]=$e;

function jsonRemoveUnicodeSequences($struct) {
  return @preg_replace("/\\\\u([a-f0-9]{4})/e", "iconv('UCS-4LE','UTF-8',pack('V', hexdec('U$1')))", json_encode($struct));
}   
   
//print(json_encode($output));
print jsonRemoveUnicodeSequences($output);
mysql_close();
?>

