
let loginButton = document.getElementById('login');
let usernameInput = document.getElementById('username');
let passwordInput = document.getElementById('password');


function login(event){
    event.preventDefault();
// console.log('USername entered was'+ usernameInput.value);
// console.log('Password entered was'+ passwordInput.value);

const loginInfo = {
    'username' : usernameInput.value,
    'password' : passwordInput.value,
};
fetch('http://localhost:7000/login',{
    'method':'POST',
    'credentials':'include', // this specifies that when you receive cookies,
    // you should include them in future requests.
    'headers':{
        'Content-Type': 'application/json'// application/json is a MIME type

    },
    'body':JSON.stringify(loginInfo)
}).then((response)=>{
    if (response.status ===200){
        return response.json()
    // window.location.href = '/viewreimbursement.html';
}else if (response.status === 400) {
    displayInvalidLogin();
}
}).then((user)=>{
    if(user.userRole.id === 1){
        window.location.href = '/admin.html'
    }else {
        window.location.href = '/viewreimbursement.html'
    }
})
};
function displayInvalidLogin(){
    let loginFrom = document.getElementById('loginform');

    let p = document.createElement('p');
    p.style.color = 'red';
    p.innerHTML = 'INVALID LOGIN!';

    loginForm.appendChild(p);
};

function checkIfUserCurrentlyLoggedIn(event) {
    fetch('http://localhost:7000/currentuser', {
        'credentials': 'include',
        'method': 'GET'
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = '/viewreimbursement.html';
        }
    });
}

loginButton.addEventListener('click',login);
window.addEventListener('load', checkIfUserCurrentlyLoggedIn)
