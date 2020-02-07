s<?php
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
			!strstr($line, "none = none") &&
			!strstr($line, "Likee - Formerly LIKE Video") &&
			!strstr($line, "Kiwi VPN") &&
			!strstr($line, "KB - ↑ ") &&
			!strstr($line, "TibaMusic.CoM") &&
			!strstr($line, "Getting client configuration") &&
			!strstr($line, "Authenticating") &&
			!strstr($line, "~ UpMusic") &&
			!strstr($line, "blogmusic.ir") &&
			!strstr($line, "Amir Tataloo") &&
			!strstr($line, "دانلود آهنگ جدید") &&
			!strstr($line, "دقیقه باقیمانده") &&
			!strstr($line, "TikTok = none") &&
			!strstr($line, "مگابایت/") &&
			!strstr($line, "Music.Com") &&
			!strstr($line, "Music.ir") &&
			!strstr($line, "~ Music-Fa.Com") &&
			!strstr($line, "در حال آماده سازی…") &&
			!strstr($line, "myahangha.ir") &&
			!strstr($line, "MusicSingle.ir") &&
			!strstr($line, "Screenshot_") &&
			!strstr($line, "Mehrad Jam") &&
			!strstr($line, "Mehraad Jam") &&
			!strstr($line, "Mohammad Lotfi") &&
			!strstr($line, "Connected SUCCESS") &&
			!strstr($line, "Mehrab & Ershad") &&
			!strstr($line, "Trevor Daniel") &&
			!strstr($line, "Remix") &&
			!strstr($line, "@MuDownloadir") &&
			!strstr($line, "Alireza Pouya") &&
			!strstr($line, "ثانیه باقیمانده") &&
			!strstr($line, "مگابایت / ") &&
			!strstr($line, "ویرایشگر عکس روی برنامه‌های دیگر نشان داده می‌شود") &&
			!strstr($line, "Waiting for server reply") &&
			!strstr($line, "Waiting for usable network") &&
			!strstr($line, "Connecting to VPN") &&
			!strstr($line, "در حال دانلود نسخه به‌روز…") &&
			!(strstr($line, "- ↑") && strstr($line, "↓ ") && strstr($line, "MB")) &&
			!(strstr($line, " کیلوبایت / ")) &&
			!(strstr($line, "بانک موزیک")) &&
			!(strstr($line, "Maps - Navigate & Explore")) &&
			!(strstr($line, "YouTube = none")) &&
			!(strstr($line, "Google = none")) &&
			!(strstr($line, "در حال انجام…")) &&
			!(strstr($line, "عکس از صفحه گرفته شد = برای باز کردن آن در گالری، اینجا ضربه بزنید")) &&
			!strstr($line, "دانلود آهنگ جدید")
		) {
			print $line."\n<br>";
		}
	}
}
// print "</pre>";
