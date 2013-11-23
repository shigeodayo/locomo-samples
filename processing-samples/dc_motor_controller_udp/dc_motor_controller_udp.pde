import processing.net.*;

Client client;

static final String UDP_IP = "192.168.1.58";  // ip address of Xbee wifi
static final int UDP_PORT = 9750;  // port

int speed = 255;


void setup() {
  client = new Client(this, UDP_IP, UDP_PORT);

  noLoop();
}

void draw() {
}


void keyPressed() {
  if (keyCode == UP) {
    moveForward();
  } else if (keyCode == DOWN) {
    moveBackward();
  } else if (keyCode == RIGHT) {
    turnRight();
  } else if (keyCode == LEFT) {
    turnLeft();
  } else if (key == '.') {
    liftUp();
  } else if (key == ',') {
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

void send(int speedR, int speedL, int speedM) {
  client.write("L" + speedL + " R" + speedR + " M" + speedM + "\r");
}
