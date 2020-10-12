$(document).ready(function () {
    let x = undefined;
    let y = undefined;
    let r = undefined;

    let rPrevious = document.querySelector("#output > table > tbody > tr:nth-child(1) > td:nth-child(3)");
    if (rPrevious) {
        let rPreviousText = Number(rPrevious.textContent);
        $("#" + rPreviousText.toString().replaceAll(".", "\\.")).prop('checked', true);
        r = rPreviousText;
    }

    const svgSize = 300;
    const rValue = 50;

    function doRequest(x, y, r) {
        $.ajax({
            type: "GET",
            url: "controller",
            data: {
                "x": x,
                "y": y,
                "r": r
            },
            success: function () {
                document.location.reload();
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("Server Side Validation!");
            }
        });
    }

    $('svg').mousedown(function (e) {
        if (r) {
            const position = $('.svg-wrapper').offset();
            const rowX = e.pageX - position.left;
            const rowY = e.pageY - position.top;
            const x = (((r / 50) * (svgSize / 2 - rowX) * -1) / 2).toFixed(1);
            const y = (((r / 50) * (svgSize / 2 - rowY)) / 2).toFixed(1);

            // const x = (r * (rowX - svgSize / 2) / rValue).toFixed(1);
            // const y = (r * (svgSize / 2 - rowY) / rValue).toFixed(1);

            const calcX = x / r * rValue + svgSize / 2;
            const calcY = svgSize / 2 - y / r * rValue;

            $('#target-dot').attr({
                'cx': x.toString(),
                'cy': y.toString(),
            })

            // console.log('calcX= ' + calcX + ', calcY= ' + calcY);
            // console.log('x= ' + x + ', y= ' + y + ', r= ' + r);

            doRequest(x, y, r);

        } else alert("Выберите R")
    })

    $('.checkbox').click(function () {
        $('body input:checkbox').prop('checked', false);
        $(this).prop('checked', true);
        r = Number($(this).attr('id'));
    });

    $('.radio').click(function () {
        const id = $(this).attr("id");
        let value;
        switch (id) {
            case 'radio1':
                value = -2;
                break;
            case 'radio2':
                value = -1.5;
                break;
            case 'radio3':
                value = -1;
                break;
            case 'radio4':
                value = -0.5;
                break;
            case 'radio5':
                value = 0;
                break;
            case 'radio6':
                value = 0.5;
                break;
            case 'radio7':
                value = 1;
                break;
            case 'radio8':
                value = 1.5;
                break;
            case 'radio9':
                value = 2;
                break;
        }
        x = value;
    })

    $('.btn-x').on('click', function () {
        x = Number($(this).text());
        $('.btn-x').removeClass('selected-x');
        $(this).addClass('selected-x');
        console.log("тип: " + typeof (x) + ", значение: " + x);
    })

    $('#check-btn').on('click', function () {
        let value = $('#input-y').val().replace(',', '.').trim();
        if (value !== '') {
            value = Number(value);
            if (value >= -5 && value <= 5) {
                y = value;
            } else {
                alert("Введен некорректный Y");
                $('#input-y').val('');
                return;
            }
        }

        if (x !== undefined && y !== undefined && r !== undefined) {
            doRequest(x, y, r);
        } else {
            alert("Не все поля заполнены");
        }
    })
})
