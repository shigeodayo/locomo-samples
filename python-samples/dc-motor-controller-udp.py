import socket

UDP_IP = "192.168.1.30"  # ip address
UDP_PORT = 9750  # port

speed = 255


def move_forward(sock):
    send(sock, speed, speed, 0)


def move_backward(sock):
    send(sock, -speed, -speed, 0)


def turn_right(sock):
    send(sock, speed, -speed, 0)


def turn_left(sock):
    send(sock, -speed, speed, 0)


def lift_up(sock):
    send(sock, 0, 0, speed)


def lift_down(sock):
    send(sock, 0, 0, -speed)


def stop(sock):
    send(sock, 0, 0, 0)


def send(sock, speed_r, speed_l, speed_lift):
    sock.sendto('a' + str(speed_r) + '\r', (UDP_IP, UDP_PORT))
    sock.sendto('b' + str(speed_l) + '\r', (UDP_IP, UDP_PORT))
    sock.sendto('c' + str(speed_lift) + '\r', (UDP_IP, UDP_PORT))


if __name__ == '__main__':

    # get upd interface
    sock = socket.socket(socket.AF_INET,
                         socket.SOCK_DGRAM)

    # manage key input
    while 1:
        key = raw_input('Enter your input:')
        if key == 'w':
            print 'move forward'
            move_forward(sock)
        elif key == 's':
            print 'move backward'
            move_backward(sock)
        elif key == 'a':
            print 'turn left'
            turn_left(sock)
        elif key == 'd':
            print 'turn right'
            turn_right(sock)
        elif key == 'q':
            print 'lift up'
            lift_up(sock)
        elif key == 'e':
            print 'lift down'
            lift_down(sock)
        elif key == '':
            print 'stop'
            stop(sock)
