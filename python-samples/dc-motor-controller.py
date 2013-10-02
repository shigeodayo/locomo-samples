import serial

PORT = '/dev/tty.usbserial-A600eLfj'

speed = 255


def move_forward(ser):
    send(ser, speed, speed, 0)


def move_backward(ser):
    send(ser, -speed, -speed, 0)


def turn_right(ser):
    send(ser, speed, -speed, 0)


def turn_left(ser):
    send(ser, -speed, speed, 0)


def lift_up(ser):
    send(ser, 0, 0, speed)


def lift_down(ser):
    send(ser, 0, 0, -speed)


def stop(ser):
    send(ser, 0, 0, 0)


def send(ser, speed_r, speed_l, speed_lift):
    ser.write('a' + str(speed_r) + '\r')
    ser.write('b' + str(speed_l) + '\r')
    ser.write('c' + str(speed_lift) + '\r')


if __name__ == '__main__':

    # get serial inteface
    ser = serial.Serial(PORT, 9600)

    # manage key input
    while 1:
        key = raw_input('Enter your input:')
        if key == 'w':
            print 'move forward'
            move_forward(ser)
        elif key == 's':
            print 'move backward'
            move_backward(ser)
        elif key == 'a':
            print 'turn left'
            turn_left(ser)
        elif key == 'd':
            print 'turn right'
            turn_right(ser)
        elif key == 'q':
            print 'lift up'
            lift_up(ser)
        elif key == 'e':
            print 'lift down'
            lift_down(ser)
        elif key == '':
            print 'stop'
            stop(ser)
