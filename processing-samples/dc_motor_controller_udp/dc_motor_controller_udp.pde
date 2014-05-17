import processing.net.*;

Client client;
int speed_L = 0;
int speed_R = 0;

void setup() {
  // connect to LOCOMO
  client = new Client(this, "192.168.1.29", 9750); // set your IP address
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
  client.write("L" + speed_L + " R" + speed_R + "\r");
}

// stop all motors while releasing keyboard
void keyReleased() {
  speed_L = 0;
  speed_R = 0;
  client.write("L" + speed_L + " R" + speed_R + "\r");
}
