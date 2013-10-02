import hypermedia.net.*;

static final String UDP_IP = "192.168.1.30";  // ip address of Xbee wifi
static final int UDP_PORT = 9750;  // port

UDP udp;
int speed = 255;


void setup() {
  udp = new UDP(this); //port from xbee wi-fi
  udp.listen(true);

  noLoop();
}

void draw() {
}


void keyPressed() {
  if ( keyCode==UP ) {
    moveForward();
  } else if ( keyCode==DOWN ) {
    moveBackward();
  } else if ( keyCode==RIGHT ) {
    turnRight();
  } else if ( keyCode==LEFT ) {
    turnLeft();
  } else if ( key == '.') {
    liftUp();
  } else if ( key == ',') {
    liftDown();
  }
}

void keyReleased() {
  stop();
}


void moveForward() {
  send(speed, speed, 0);
}
void moveBackward() {
  send(-speed, -speed, 0);
}
void turnRight() {
  send(speed, -speed, 0);
}
void turnLeft() {
  send(-speed, speed, 0);
}
void liftUp() {
  send(0, 0, speed);
}
void liftDown() {
  send(0, 0, -speed);
}
void stop() {
  send(0, 0, 0);
}

void send(int speedR, int speedL, int speedLift) {
  udp.send("a" + speedR + "\r", UDP_IP, UDP_PORT);
  udp.send("b" + speedL + "\r", UDP_IP, UDP_PORT);
  udp.send("c" + speedLift + "\r", UDP_IP, UDP_PORT);
}
