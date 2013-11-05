var locomo = locomo || {};

(function() {
    locomo.init = function() {
        initTouch();
    };

    function initTouch() {
        var leftMotorDirection = '';
        var rightMotorDirection = '';
        var preLeftMotorDirection = '';
        var preRightMotorDirection = '';

        $("#left_motor").swipe( {
            swipeStatus:function(event, phase, direction, distance, duration, fingers) {
                if (phase === 'end') {
                    direction = 'stop'
                }

                if (direction === 'left' || direction === 'right') {
                    return;
                }

                showStatus('#left_motor', phase, direction, distance, duration, fingers);

                if (direction == null || direction === preLeftMotorDirection) {
                    return;
                }

                leftMotorDirection = direction;
                sendMotorDirection();
                preLeftMotorDirection = direction;
            },
            triggerOnTouchLeave:true,
            threshold:0,
            allowPageScroll:"none",
            fingers:2
        });

        $("#right_motor").swipe( {
            swipeStatus:function(event, phase, direction, distance, duration, fingers) {
                if (phase === 'end') {
                    direction = 'stop'
                }

                if (direction === 'left' || direction === 'right') {
                    return;
                }

                showStatus('#right_motor', phase, direction, distance, duration, fingers);

                if (direction == null || direction === preRightMotorDirection) {
                    return;
                }

                rightMotorDirection = direction;
                sendMotorDirection();
                preRightMotorDirection = direction;
            },
            triggerOnTouchLeave:true,
            threshold:0,
            allowPageScroll:"none",
            fingers:2
        });

        function sendMotorDirection() {
            var leftMotorVal = 0;
            if (leftMotorDirection === 'up') {
                leftMotorVal = 1;
            } else if (leftMotorDirection === 'down') {
                leftMotorVal = 2;
            }
            var rightMotorVal = 0;
            if (rightMotorDirection === 'up') {
                rightMotorVal = 1;
            } else if (rightMotorDirection === 'down') {
                rightMotorVal = 2;
            }
            console.log(leftMotorVal);
            console.log(rightMotorVal);

            $.ajax({
                type: 'POST',
                url: '/direction/' + leftMotorVal + '/' + rightMotorVal,
                success: function(data) {
                    console.log('sent');
                }
            });
        }

        function showStatus(tagId, phase, direction, distance, duration, fingers) {
            var str = "<h4>Swipe Phase : " + phase + "<br/>";
            str += "Direction from inital touch: " + direction + "<br/>";
            str += "Distance from inital touch: " + distance + "<br/>";
            str += "Duration of swipe: " + duration + "<br/>";
            str += "Fingers used: " + fingers + "<br/></h4>";

            $(tagId).html(str);
        }
    }
})();

$(document).ready(locomo.init);
