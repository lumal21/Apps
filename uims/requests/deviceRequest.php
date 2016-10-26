<?php  
include('./httpful.phar');

	
		$urlDevices = "http://raise.uiot.org/devices?token=UIMS-MANAGEMENT-SYSTEM";

		$response = \Httpful\Request::get($urlDevices)->send();
		$res = $response->body;

		echo json_encode($res);	
?>