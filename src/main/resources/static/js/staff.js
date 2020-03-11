const URLS = {

    staff: '/api-staff',
    count: '/api-staff-count',
};

const toString = ({firstName, lastName, position}) =>{

    return`<tr>
        <td class="first-name">${firstName}</td>
        <td class="last-name">${lastName}</td>
        <td class="position">${position}</td>
        <td><button type="button" class="btn btn-danger staff-fire">Fire</button></td>
    </tr>`;
};

fetch(URLS.staff).then(response => response.json()).then(staff => {

    let result = '';

    staff.forEach(person =>
    {
        result += toString(person);
    });

    loader.hide();

    let tableHTML =
        '<table class="table table-staff"> ' +
        '<tr><th scope="col">First name</th> ' +
        '<th scope="col">Last name</th>' +
        '<th scope="col">Position</th>' +
        '</thead>' +
        '<tbody>' + result + '</table>';


    $('#page-footer').before(tableHTML);
    $('.staff-fire').on('click', function () {


        fetch(URLS.count).then(response => response.json()).then(value =>{

            if (value === 4)
            {
                alert('You can NOT be left with less than 4 people staff!');
            }

            else
            {
                var staffFirstName = $(this).closest("tr").find('.first-name').text();
                var staffLastName = $(this).closest("tr").find('.last-name').text();
                var staffPosition = $(this).closest("tr").find('.position').text();

                $.ajax({
                    type : "POST",
                    url : "/staff/fired",
                    data: {staffFirstName: staffFirstName, staffLastName: staffLastName, staffPosition: staffPosition},
                });

                location.href = '/staff';
            }
        });
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