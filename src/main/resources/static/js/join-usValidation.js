function validateData()
{
    const firstName = document.getElementById('inputFirstName').value;
    const lastName = document.getElementById('inputLastName').value;
    const linkToCV = document.getElementById('experience').value;
    const phoneNumber = document.getElementById('inputPhoneNumber').value;

    if (firstName === '' || lastName === '' || linkToCV === '' || phoneNumber === '')
    {
        alert('Please, enter all field data!');
        return;
    }

   return confirm('Are you sure?');
}