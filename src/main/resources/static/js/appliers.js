const URLS = {
    appliers: '/api-appliers',
};

const toString = ({firstName, lastName, linkToCV, phoneNumber, requestedPosition, username}) =>
{
    return `<tr>
        <td>${firstName}</td>
        <td>${lastName}</td>
        <td>${linkToCV}</td>
        <td>${phoneNumber}</td>
        <td>${requestedPosition}</td>
        <td class="username">${username}</td>
        <td><button type="button" class="btn btn-success appliers-hire">Hire</button></td>
        <td><button type="button" class="btn btn-danger appliers-remove">Remove</button></td>
    </tr>`;
};

fetch(URLS.appliers).then(response => response.json()).then(appliers =>{

    let result = '';

    appliers.forEach(applier =>
    {
        const itemStr = toString(applier);
        result += itemStr;
    });

    loader.hide();

    let tableHTML =
        '<table class="table table-appliers"> ' +
        '<tr><th scope="col">First name</th> ' +
        '<th scope="col">Last name</th>' +
        '<th scope="col">Link to CV</th>' +
        '<th scope="col">Phone number</th>' +
        '<th scope="col">Requested position</th>' +
        '<th scope="col">Username</th></tr>' +
        '</thead>' +
        '<tbody>' + result + '</table>';

    if (result === '')
    {
        var elem = document.createElement('div');
        elem.id = 'no-appliers';
        elem.innerHTML = '<p>There are not any appliers yet  :(</p>';

        $('.topnav').after(elem);
    }

    else {
        document.getElementsByClassName('appliers-greet')[0]
            .classList
            .remove('invisible');
    }


    $('#page-footer').before(tableHTML);
    $('.appliers-hire').on('click', function () {

        var userUsername = $(this).closest("tr").find('.username').text();

        $.ajax({
            type : "POST",
            url : "/appliers/hired",
            data: {userUsername: userUsername}
        });


        location.href = "/appliers";

    });

    $('.appliers-remove').on('click', function () {

        var username = $(this).closest('tr').find('.username').text();

        $.ajax({

            type: "POST",
            url: "/appliers/removed",
            data: {username: username}
        });

        location.href = '/appliers';
    })
});

const loader = {

    // show: () =>
    // {
    //     $('#page-loader').show();
    // }

    hide: () =>
    {
        $('#page-loader').hide();
    }
};