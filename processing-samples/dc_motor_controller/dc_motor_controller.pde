import processing.serial.*;

Serial serial;
int speed_L = 0;
int speed_R = 0;

void setup() {
  // connect to LOCOMO
  serial = new Serial(this, "/dev/tty.usbserial-A600eLfj", 9600);
}

void draw() {
  // no operation
}

// control motors with yoru PC keyboard
void keyPressed() {
  if ( keyCode==UP ) {
    speed_L = 255;
    speed_R = 255;
  }
  if ( keyCode==DOWN ) {
    speed_L = -255;
    speed_R = -255;
  }
  if ( keyCode==LEFT ) {
    speed_L = -255;
    speed_R = 255;
  }
  if ( keyCode==RIGHT ) {
    speed_L = 255;
    speed_R = -255;
  }
  serial.write("L" + speed_L + " R" + speed_R + "\r");
}

// stop all motors while releasing keyboard
void keyReleased() {
  speed_L = 0;
  speed_R = 0;
  serial.write("L" + speed_L + " R" + speed_R + "\r");
}