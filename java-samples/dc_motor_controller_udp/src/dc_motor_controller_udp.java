import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class dc_motor_controller_udp extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final String UDP_IP = "192.168.1.30"; // ip address of Xbee wifi
	private static final int UDP_PORT = 9750; // port

	private DatagramSocket sendSocket = null;
	private InetAddress inetAddress = null;
	private int speed = 255;

	private MyPanel myPanel = null;

	public dc_motor_controller_udp() {
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
		try {
			sendSocket = new DatagramSocket();
			inetAddress = InetAddress.getByName(UDP_IP);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
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
		byte[] buf = (("a" + speedR + "\r") + 
				("b" + speedL + "\r") + 
				("c" + speedLift + "\r")).getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length,
				inetAddress, UDP_PORT);
		try {
			sendSocket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				final dc_motor_controller_udp thisClass = new dc_motor_controller_udp();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
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
