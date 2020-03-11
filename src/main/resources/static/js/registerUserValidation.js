const URLS = {
    username: '/api-allUsername',
};


function validateData()
{

    var username = document.getElementById('inputUsername').value;
    var password = document.getElementById('inputPassword1').value;
    var passwordConf = document.getElementById('inputPassword2').value;


    if (username === '' || password === '' || passwordConf === '')
    {
        alert('Please, enter all field data!');
        return;
    }


    if (password !== passwordConf)
    {
        alert('Password and confirm password do NOT match!');
        return;
    }

    findIfExistingUsername(username);
}

function findIfExistingUsername(username)
{

    fetch(URLS.username).then(response => response.json()).then(allUsername => {

        for (let existingUsername of allUsername)
        {

            if (username === existingUsername)
            {
                alert('User already exists!');
                return;
            }
        }
    });
}