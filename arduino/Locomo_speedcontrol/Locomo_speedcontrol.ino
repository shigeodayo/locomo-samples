static int FWD_L = 2;  // forward pin of left motor
static int REV_L = 4;  // reverse pin of left motor
static int PWM_L = 3;  // pwm pin of left motor

static int FWD_R = 5;  // forward pin of right motor
static int REV_R = 7;  // reverse pin of right motor
static int PWM_R = 6;  // pwm pin of right motor

int speed_L = 0;  // revolution speed of left motor (0-255)
int speed_R = 0;  // revolution speed of right motor (0-255)

void setup() {
  // serial setting of baud rate
  Serial.begin(9600);

  // set motor control pins as outputs
  pinMode( FWD_L, OUTPUT );
  pinMode( REV_L, OUTPUT );
  pinMode( PWM_L, OUTPUT );
  pinMode( FWD_R, OUTPUT );
  pinMode( REV_R, OUTPUT );
  pinMode( PWM_R, OUTPUT );
}

void loop() {
    // no operation
}

// handle event of serial communication
void serialEvent() {
  if ( Serial.available() > 0 ) {
    int key = Serial.read();

    // get the integer value follow after 'L' or 'R', and set it as motor speed
    if (key=='L') { speed_L = readIntValue(); }
    if (key=='R') { speed_R = readIntValue(); }

    // settings of revolution speed
    analogWrite( PWM_L, abs(speed_L) );
    analogWrite( PWM_R, abs(speed_R) );

    // revolution control of motors
    if ( speed_L == 0 ) { motor_stop( FWD_L, REV_L ); }
    else if ( speed_L > 0 ) { motor_forward( FWD_L, REV_L ); }
    else if ( speed_L < 0 ) { motor_reverse( FWD_L, REV_L ); }

    if ( speed_R == 0 ) { motor_stop( FWD_R, REV_R ); }
    else if ( speed_R > 0 ) { motor_forward( FWD_R, REV_R ); }
    else if ( speed_R < 0 ) { motor_reverse( FWD_R, REV_R ); }
  }
}

// rotate a motor
void motor_forward( int pin_fwd, int pin_rev ) {
  digitalWrite( pin_fwd, HIGH );
  digitalWrite( pin_rev, LOW );
}

// rotate a motor in reverse
void motor_reverse( int pin_fwd, int pin_rev ) {
  digitalWrite( pin_fwd, LOW );
  digitalWrite( pin_rev, HIGH );
}

// stop a motor
void motor_stop( int pin_fwd, int pin_rev ) {
  digitalWrite( pin_fwd, HIGH );
  digitalWrite( pin_rev, HIGH );
}

// read an integer value
int readIntValue() {
  int i = 0;
  char string[10];

  while ( i < sizeof(string) ) {
    if ( Serial.available() ) {
      char c = Serial.read();
      if ( (c>='0' && c<='9') || c=='-' ) {
        string[i] = c;
        i++;
      } else {
        string[i] = '\0';
        break;
      }
    }
  }
  return atoi(string);
}