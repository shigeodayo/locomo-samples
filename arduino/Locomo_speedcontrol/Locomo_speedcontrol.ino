/** morter port numbers **/
static int MOTOR1_CF  = 2;
static int MOTOR1_CR  = 4;
static int MOTOR1_PWM = 3;

static int MOTOR2_CF  = 5;
static int MOTOR2_CR  = 7;
static int MOTOR2_PWM = 6;

static int MOTOR3_CF  = 12;
static int MOTOR3_CR  = 13;
static int MOTOR3_PWM = 11;

/** motor speed parameters **/
int m1Param = 0;
int m2Param = 0;
int m3Param = 0;


/** setting the pin mode & serial **/
void setup() {
  pinMode(MOTOR1_CF, OUTPUT);
  pinMode(MOTOR1_CR, OUTPUT);
  pinMode(MOTOR1_PWM, OUTPUT);

  pinMode(MOTOR2_CF, OUTPUT);
  pinMode(MOTOR2_CR, OUTPUT);
  pinMode(MOTOR2_PWM, OUTPUT);

  pinMode(MOTOR3_CF, OUTPUT);
  pinMode(MOTOR3_CR, OUTPUT);
  pinMode(MOTOR3_PWM, OUTPUT);

  Serial.begin(9600);
}

void loop() {
  if (Serial.available() > 0) {
    int key = Serial.read();
    
    // read the value
    if (key == 'a') {
      m1Param = readIntValue();
    } else if (key == 'b') {
      m2Param = readIntValue();
    } else if (key == 'c') {
      m3Param = readIntValue();
    }

    if (m1Param == 0) {
      stopMotor1();
    } else if (m1Param > 0) {
      forwardMotor1();
    } else if (m1Param < 0) {
      backwardMotor1();
    }

    if (m2Param == 0) {
      stopMotor2();
    } else if (m2Param > 0) {
      forwardMotor2();
    } else if (m2Param < 0) {
      backwardMotor2();
    }

    if (m3Param == 0) {
      stopMotor3();
    } else if (m3Param > 0) {
      forwardMotor3();
    } else if (m3Param < 0) {
      backwardMotor3();
    }

    analogWrite(MOTOR1_PWM, abs(m1Param));
    analogWrite(MOTOR2_PWM, abs(m2Param));
    analogWrite(MOTOR3_PWM, abs(m3Param));
  }
}

// -32768 to 32767
int readIntValue() {
  char c;
  char string[10];
  int i = 0;

  while (i < sizeof(string)) {
    if (Serial.available() > 0) {
      c = Serial.read();
      if (c == '\0' || c == '\r') {
        string[i] = '\0';       
        break;
      } else {
        string[i] = c;
        i++;
      }
    }
  }
  return atoi(string);
}


/** motor1 **/
void forwardMotor1() {
  forwardMotor(MOTOR1_CF, MOTOR1_CR);
}
void backwardMotor1() {
  backwardMotor(MOTOR1_CF, MOTOR1_CR);
}
void stopMotor1() {
  stopMotor(MOTOR1_CF, MOTOR1_CR);
}
/** motor 2 **/
void forwardMotor2() {
  forwardMotor(MOTOR2_CF, MOTOR2_CR);
}
void backwardMotor2() {
  backwardMotor(MOTOR2_CF, MOTOR2_CR);
}
void stopMotor2() {
  stopMotor(MOTOR2_CF, MOTOR2_CR);
}
/** motor 3 **/
void forwardMotor3() {
  forwardMotor(MOTOR3_CF, MOTOR3_CR);
}
void backwardMotor3() {
  backwardMotor(MOTOR3_CF, MOTOR3_CR);
}
void stopMotor3() {
  stopMotor(MOTOR3_CF, MOTOR3_CR);
}

/** manager of the motor direction **/
void forwardMotor(int forward, int reverse) {
  digitalWrite(forward, HIGH);
  digitalWrite(reverse, LOW);
}
void backwardMotor(int forward, int reverse) {
  digitalWrite(forward, LOW);
  digitalWrite(reverse, HIGH);
}
void stopMotor(int forward, int reverse) {
  digitalWrite(forward, HIGH);
  digitalWrite(reverse, HIGH);
}
