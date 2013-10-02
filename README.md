# Samples to control LOCOMO

Sample programs to control LOCOMO.

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
