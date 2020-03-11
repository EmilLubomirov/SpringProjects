function validateForm()
{
    var x = document.forms["login-form"]["username"].value;
    var y = document.forms["login-form"]["password"].value;

    if (x !== '' && y !== '')
    {
        document.getElementById('login-button').removeAttribute('disabled');
    }

    else
        {
        document.getElementById('login-button').setAttribute('disabled', '');
    }
}

const URLS = {

    username: '/api-allUsername',
};


function findUser()
{
    const loginUsername = document.forms["login-form"]["username"].value;

    fetch(URLS.username).then(response => response.json()).then(allUsername =>{

        var isUsernameFound = allUsername.includes(loginUsername);

        if (isUsernameFound === false)
        {
            alert("No user with such username found!");
        }
    });
}