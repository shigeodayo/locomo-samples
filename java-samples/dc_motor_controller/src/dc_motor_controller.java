import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class dc_motor_controller extends JFrame implements
		SerialPortEventListener {
	private static final long serialVersionUID = 1L;

	
	private static final String PORT = "/dev/tty.usbserial-A600eLfj";

	private SerialPort serialPort = null;
	private int speed = 255;
	private BufferedReader input = null;
	private OutputStream output = null;

	private MyPanel myPanel = null;

	public dc_motor_controller() {
		initialize();
		initializeConnection();
	}

	/**
	 * initialization of window
	 */
	private void initialize() {
		setTitle("dc_motor_controller");
		setBounds(20, 20, 200, 200);

		Container contentPane = getContentPane();
		contentPane.add(getMyPanel(), BorderLayout.CENTER);
		getMyPanel().setBackground(Color.BLACK);
	}

	/**
	 * initialization of connection
	 */
	private void initializeConnection() {
		CommPortIdentifier portId = null;
		try {
			Enumeration portList = CommPortIdentifier.getPortIdentifiers();
			while (portList.hasMoreElements()) {
				portId = (CommPortIdentifier) portList.nextElement();
				if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
					if (portId.getName().equals(PORT)) {
						System.out.println("found port:" + PORT);
					}
				}
			}

			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					2000);

			// set port parameters
			serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(
					serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	private MyPanel getMyPanel() {
		if (myPanel == null) {
			myPanel = new MyPanel();
		}
		return myPanel;
	}

	private void moveForward() {
		send(speed, speed, 0);
	}

	private void moveBackward() {
		send(-speed, -speed, 0);
	}

	private void turnRight() {
		send(speed, -speed, 0);
	}

	private void turnLeft() {
		send(-speed, speed, 0);
	}

	private void liftUp() {
		send(0, 0, speed);
	}

	private void liftDown() {
		send(0, 0, -speed);
	}

	private void stop() {
		send(0, 0, 0);
	}

	private void send(int speedR, int speedL, int speedLift) {
		try {
			output.write(("a" + speedR + "\r").getBytes());
			output.write(("b" + speedL + "\r").getBytes());
			output.write(("c" + speedLift + "\r").getBytes());
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				final dc_motor_controller thisClass = new dc_motor_controller();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine = input.readLine();
				System.out.println(inputLine);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}

	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * private class for detecting key event & drawing something
	 */
	private class MyPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		public MyPanel() {
			setSize(200, 200);
			setFocusable(true);
			setBackground(Color.white);

			addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent event) {
					int key = event.getKeyCode();
					switch (key) {
					case KeyEvent.VK_UP:
						moveForward();
						break;
					case KeyEvent.VK_DOWN:
						moveBackward();
						break;
					case KeyEvent.VK_RIGHT:
						turnRight();
						break;
					case KeyEvent.VK_LEFT:
						turnLeft();
						break;
					case KeyEvent.VK_S:
						stop();
						break;
					case KeyEvent.VK_PERIOD:
						liftUp();
						break;
					case KeyEvent.VK_COMMA:
						liftDown();
						break;
					}
				}

				public void keyReleased(KeyEvent event) {
					stop();
				}
			});
		}

		public void paint(Graphics g) {
			// draw something
		}
	}
}