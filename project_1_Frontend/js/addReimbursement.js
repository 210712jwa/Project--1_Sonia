let submitButton = document.getElementById('btn_submit');
let amountInput = document.getElementById('amount');
let descriptionInput = document.getElementById('description');
let typeInput = document.getElementById('type');

function btn_submit(event){
    event.preventDefault();


const submissionInfo = {
    'amount': amountInput.value,
    'reimbDescription': descriptionInput.value,
    'type': typeInput.value
};
fetch('http://localhost:7000/currentuser',{
    'credentials': 'include',
        'method': 'GET'
}).then((response) => {
    if (response.status === 401) {
        window.location.href = '/index.html'
    } else if (response.status === 200) {
        return response.json();
    }
}).then((user) => {
    return fetch(`http://localhost:7000/user/${user.id}/reimbursement`, {
        'method': 'POST', 
        'credentials': 'include',
        headers: {
            'Content-Type': 'application/json' // application/json is a MIME type
        },
        body: JSON.stringify(submissionInfo)
    });
}).then((response) => {
    if (response.status === 200) {
        window.location.href = '/viewreimbursement.html';
    } 
})
};
submitButton.addEventListener('click', btn_submit);