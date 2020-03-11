const URLS = {
  clientsServices: '/api-services',
    userRoles: '/api-userRoles',
};

const toString = ({description, price}) =>
{
    return `<tr>
        <td>${description}</td>
        <td class="service-price">${price}</td>
        <td class="service-amount">0</td>
        <td><button type="button" class="btn btn-primary service-add">Add</button></td>
    </tr>`;
};


fetch(URLS.clientsServices)
    .then(response => response.json())
    .then(services => {


       document.getElementById('page-footer').display = 'none';

        //loader.show();

        let result = '';

        services.forEach(service =>{

            const itemStr = toString(service);
            result += itemStr;
        });

        var elem = document.getElementsByClassName('services-message')[0];
        elem.classList.remove('invisible');

        if (result === '')
        {
            elem.innerHTML = 'Thanks for booking all of our services :)' + '<br>' +
                              'Stay tuned for new ones soon!';
        }

        loader.hide();

        let additionalTablePropertiesRow =
            '<tr><td><h5>*all prices are for a single person</h5></td>'+
            '<td><h5>*purchase is made on the reception</h5></td>' +
            '<td><button class="btn btn-success" id="add-new-service-button">New</button></td>' +
            '<td><button class="btn btn-danger" id="clear-sum-button">Clear</button></td>' +
            '<td><button class="btn btn-success" id="services-buy">Book</button></td>' +
            '</tr>';

        let tableHTML =
            '<table class="table table-services"> ' +
            '<tr><th scope="col">Description</th> ' +
            '<th scope="col">Price(lv)</th>' +
            '<th scope="col">Amount</th></tr>' +
            '</thead>' +
            '<tbody>' + result + additionalTablePropertiesRow + '</table>';


        var priceLblDiv = document.createElement('div');
        priceLblDiv.id = 'price-label';
        priceLblDiv.innerHTML = 'Additional sum: 0.00 lv.';

        if (result !== '')
        {
            document.getElementById('page-footer').style.position = 'inherit';
        }

        $('#page-footer').before(priceLblDiv);
        $('#price-label').before(tableHTML);


        $('.table-services tbody').on('click', '.service-add', function () {

            var currow = $(this).closest('tr');

            var price = currow.find('.service-price').text();
            var amount = currow.find('.service-amount').text();
            var updatedAmount = (parseInt(amount) + 1);

            const beginIndex = 16;

            var priceLabelDiv = document.getElementById('price-label');
            var priceLabelText = priceLabelDiv.innerText;
            var endIndex = priceLabelText.lastIndexOf(' ');


            var oldPrice = priceLabelDiv.innerText.slice(beginIndex, endIndex);
            console.log(oldPrice);

            var newSum = calculateNewSum(oldPrice, price);

            console.log(updatedAmount, price);

            currow.find('.service-amount')[0].innerHTML = updatedAmount;
            addContentToElement('price-label', 'Additional sum: ' + newSum + ' lv.');
        });

        $('#clear-sum-button').on('click', function () {

            document.getElementById('price-label').innerHTML ='Additional sum: 0.00 lv.';
            nullAllElementsByClassName('service-amount');
        });

        $('#services-buy').on('click', function () {


            var data = getSelectedServices();
            var priceData = getElementContentById('price-label');


            $.ajax({
                type : "POST",
                url : "/services/bought",
                data: {price: priceData, servicesData: data}
            });


            location.href = "/services";
        });

        let isUserRoot = false;

        fetch(URLS.userRoles).then(response => response.json()).then(roles =>
        {
            if (roles.indexOf('ADMIN') <= -1)
            {
                document.getElementById('add-new-service-button')
                    .style.display = 'none';
            }

            else {

                    $('#add-new-service-button').on('click', function ()
                    {
                        location.href = '/service-new';
                    });
            }

            if (roles.indexOf('ROOT') > -1)
            {
                isUserRoot = true;
            }
        });

        if(isUserRoot === false && result === '')
        {
            disableElementById('clear-sum-button');
            disableElementById('services-buy');
        }
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

function addContentToElement(id, content)
{
    document.getElementById(id).innerHTML = content;
}

function calculateNewSum(current, add)
{
    var newSum = parseFloat(current) + parseFloat(add);
    return newSum.toFixed(2);
}

function nullAllElementsByClassName(className)
{
    var elements = document.getElementsByClassName(className);

    for (let i = 0; i < elements.length; ++i)
    {
        elements[i].innerHTML = '0';
    }
}

function getElementContentById(id)
{
    return document.getElementById(id).innerText;
}

function getSelectedServices()
{
   var data = '';

    var table = document.getElementsByClassName('table-services')[0];
    var rows = table.rows;

    for (let i = 1; i < rows.length - 1; ++i)
    {

        var serviceName = rows[i].cells[0].innerHTML;
        var serviceAmount = rows[i].cells[2].innerHTML;

        if (parseInt(serviceAmount) > 0)
        {
            data += serviceName += "-";
            data += serviceAmount;
            data += '!@';
        }
    }

    return data;
}

function disableElementById(id)
{
    document.getElementById(id)
        .setAttribute('disabled', '');
}

