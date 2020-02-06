<?php
header("Content-Type: text/html; charset=utf-8");
// print "<style>pre {direction: ltr;text-align: right;}</style><pre>";
$data=file_get_contents("data.txt");
if(isset($_GET["log"])) {
	print $data;
}
else {
	$lines=explode("\n", $data);
	foreach($lines as $line) {
		if(
			!strstr($line, "Kiwi VPN") &&
			!strstr($line, "در حال دانلود نسخه به‌روز…") &&
			!(strstr($line, "- ↑") && strstr($line, "↓ ") && strstr($line, "MB")) &&
			!(strstr($line, " کیلوبایت / ")) &&
			!(strstr($line, " مگابایت‎ / "))
		) {
			print $line."\n<br>";
		}
	}
}
// print "</pre>";
