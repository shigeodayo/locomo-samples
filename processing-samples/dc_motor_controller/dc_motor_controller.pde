import processing.serial.*;

static final String PORT = "/dev/tty.usbserial-A600eLfj";

Serial serial;
int speed = 255;


void setup() {
  serial = new Serial(this, PORT, 9600);
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
  serial.write("a" + speedR + "\r");
  serial.write("b" + speedL + "\r");
  serial.write("c" + speedLift + "\r");
}
