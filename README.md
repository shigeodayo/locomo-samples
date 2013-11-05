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
## Python samples

### dc-motor-controller.py

Python sample to control LOCOMO via serial socket.

Require 'pyserial' to run. You can install 'pyserial' by using 'pip'.

	pip install pyserial
Change 'PORT' to the appropriate one.

	PORT = '/dev/tty.usbserial-***'
### dc-motor-controller-udp.py

Python sample to control LOCOMO via UDP.

Change 'UDP\_IP' and 'UDP\_PORT' to the appropriate one.

	UDP_IP = "192.168.1.**"  # ip address of Xbee wifi
	UDP_PORT = 9750  # port
## Java samples

### dc_motor_controller.java

Require 'rxtx\-2.2pre\-bins'. Please download from the following link, and add 'RXTXcomm.jar' and librxtxSerial.{so, jnilib, dll} to the build path.

[http://rxtx.qbang.org/wiki/index.php/Download](http://rxtx.qbang.org/wiki/index.php/Download "http://rxtx.qbang.org/wiki/index.php/Download")

Change 'PORT' to the appropriate one.

	private static final String PORT = '/dev/tty.usbserial-***';
If you have an error while establishing connection, this link may be useful for you.

[http://jlog.org/rxtx-mac.html](http://jlog.org/rxtx-mac.html "http://jlog.org/rxtx-mac.html")

### dc_motor_controller_udp.java

Java sample to control LOCOMO via UDP.

Change 'UDP\_IP' and 'UDP\_PORT' to the appropriate one.

	    private static final String UDP_IP = "192.168.1.**"; // ip address of Xbee wifi
	    private static final int UDP_PORT = 9750; // port
## Web app samples

Control LOCOMO via web browser.

### setup

Install python modules.

	pip install -r packages.txt
Require 'jquery\-1.9.1.min.js' and 'jquery.touchSwipe.js'. Please download them from following links, and put those files to the static/js directory.

jquery: [http://jquery.com](http://jquery.com "http://jquery.com")

jquery.touchSwipe: [https://github.com/mattbryson/TouchSwipe-Jquery-Plugin](https://github.com/mattbryson/TouchSwipe-Jquery-Plugin "https://github.com/mattbryson/TouchSwipe-Jquery-Plugin")

### Run

	PYTHONPATH=$(pwd) python locomo.py
