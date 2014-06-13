
function simpleController($scope) {

    var timer, counter = 0;

    function showWarning(text) {

        $scope.data.warning.text = text;
        $scope.data.warning.visible = true;

        if (timer) {
            clearTimeout(timer);
        }

        timer = setTimeout(function () {
            $scope.data.warning.visible = false;
        }, 1000);

    }

    $scope.data = {
        "counter": counter,
        "warning": {
            "visible": false,
            "text": ""
        }
    };

    $scope.handlers = {
        "incrementCounter": function () {
            if (counter < 20) {
                $scope.data.counter = ++counter;
            } else {
                showWarning("Max: 20");
            }
        },
        "decrementCounter": function () {
            if (counter > 0) {
                $scope.data.counter = --counter;
            } else {
                showWarning("Min: 0");
            }
        }
    };

}
