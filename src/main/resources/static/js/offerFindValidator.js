function validateData()
{
    const budget = document.getElementById('budget').value;
    const type = document.getElementById('type').value;
    const name = document.getElementById('name').value;


    if (budget === '' || type === '' || name === '')
    {
        alert('Please, enter all field data!\n' +
            'Budget must be greater than or equal to 0!');
    }
}