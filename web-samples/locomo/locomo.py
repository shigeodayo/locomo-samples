import socket
from flask import Flask, render_template, Response, url_for, redirect, request

app = Flask(__name__)

UDP_PORT = 9750  # port
udp_ip = ''  # ip address

speed = 255  # default speed

sock = ''  # udp socket


@app.route('/')
def top():
    return render_template('index.html')


@app.route('/connection', methods=['POST'])
def connect_to_locomo():
    ip_address = request.form['locomo_ip']
    print ip_address

    global sock
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    global udp_ip
    udp_ip = ip_address

    return redirect(url_for('top'))


@app.route('/direction/<int:left_dir>/<int:right_dir>', methods=['POST'])
def motor_direction(left_dir, right_dir):
    print left_dir
    print right_dir

    left_dir = int(left_dir)
    right_dir = int(right_dir)

    speed_l = 0
    if left_dir == 1:  # up
        speed_l = speed
    elif left_dir == 2:  # down
        speed_l = -speed

    speed_r = 0
    if right_dir == 1:  # up
        speed_r = speed
    elif right_dir == 2:  # down
        speed_r = -speed

    print speed_l
    print speed_r
    send(speed_l, speed_r, 0)

    return Response()


def send(speed_l, speed_r, speed_lift):
    if udp_ip == '':
        return
    sock.sendto('a' + str(speed_l) + '\r', (udp_ip, UDP_PORT))
    sock.sendto('b' + str(speed_r) + '\r', (udp_ip, UDP_PORT))
    sock.sendto('c' + str(speed_lift) + '\r', (udp_ip, UDP_PORT))


if __name__ == '__main__':
    app.run(host='0.0.0.0')
