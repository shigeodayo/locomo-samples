# Samples to control LOCOMO

Sample programs to control LOCOMO.

about LOCOMO: [http://kougaku-navi.net/locomo/index.html](http://kougaku-navi.net/locomo/index.html "http://kougaku-navi.net/locomo/index.html")

'udp' samples are for 'Xbee\-WiFi', set the 'Xbee\-WiFi' instead of 'Xbee' to LOCOMO board.

You can set up a 'Xbee\-WiFi' by reference to the following link, or using X\-CTU.

[http://www.hughesy.net/wp/arduino/xbee-and-macs-the-easy-way/](http://www.hughesy.net/wp/arduino/xbee-and-macs-the-easy-way/ "None")

## Processing samples

### dc_motor_controller.pde

Processing sample to control LOCOMO via serial socket.

Change 'PORT' to the appropriate one.

	static final String PORT = "/dev/tty.usbserial-***";
### dc_motor_controller_udp.pde

Processing sample to control LOCOMO via UDP.

Require 'udp' library. Please download from the following link.

[http://ubaa.net/shared/processing/udp/](http://ubaa.net/shared/processing/udp/ "http://ubaa.net/shared/processing/udp/")

Change 'UDP\_IP' and 'UDP\_PORT' to the appropriate one.

	static final String UDP_IP = "192.168.1.**";  // ip address of Xbee wifi
	static final int UDP_PORT = 9750;  // port
