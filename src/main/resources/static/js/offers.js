const URLS = {
    offers: '/api-offers',
};

const MESSAGES = {

    alert: 'No available offers yet!',
};

const toString = ({departmentRent, apartmentType, agencyCommission}) =>{

let data = `<p>Rent: ${departmentRent}</p>
        <p>Type:  ${apartmentType}</p>
        <p>Commission(%): ${agencyCommission}</p>`;

return `<div class="apartment index-apartment">${data}</div>`;
};



fetch(URLS.offers)
    .then(response => response.json())
    .then(offers => {

        let result = '';

        offers.forEach(offer => {

            const itemStr = toString(offer);
            result += itemStr;
        });


        loader.hide();

        var offerDiv = document.createElement("div");
        offerDiv.innerHTML = result;

        if (result === '')
        {
            var findButton = document.getElementById('find-offer-button');
            hideElement(findButton);

            var message = MESSAGES['alert'];

            result = '<p id="message">' + message + '</p>';
            offerDiv.innerHTML = result;
        }

        removeElementClassName(document.getElementById('offerManager'), 'invisible');
        document.getElementById('page-footer').style.position = 'inherit';
        $('#offerManager').before(offerDiv);
    });

const loader = {

    show: () =>
    {
        // $('#offers-loader').show();
    }
    ,


    hide: () =>
    {
        $('#page-loader').hide();
    }

};

function hideElement(elem)
{
    elem.style.display ='none';
}

function removeElementClassName(elem, className)
{
    elem.classList.remove(className)
}