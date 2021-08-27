let reimbursementButton = document.getElementById('requestreimbursement');

function onLoad(event) {
    fetch('http://localhost:7000/currentuser', {
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
            'method': 'GET',
            'credentials': 'include'
        });

    }).then((response) => {
        return response.json()
    }).then((reimbursement) => {
        populateReimbursements(reimbursement);
    })
}

function addReimbursement(event) {
    event.preventDefault();

    window.location.href = '/addReimbursement.html';
}
function populateReimbursements(reimbursementArray) {
    let tbody = document.querySelector('#reimbursement tbody');
    for (const reimbursement of reimbursementArray) {

        // <th>Reimbursement ID</th>
        //     <th>Author Firstname</th>
        //     <th>Author Lastname</th>
        //     <th>Reimbursement Amount</th>
        //     <th>Reimbursement Submitted</th>
        //     <th>Reimbursement Resolved</th>
        //     <th>Reimbursement Description</th>
        //     <th>Reimbursement Status</th>

        let tr = document.createElement('tr');
        let reimbursementIdTd = document.createElement('td');
        reimbursementIdTd.innerHTML = reimbursement.id;

        let reimbursementAuthorFirstName = document.createElement('td');
        reimbursementAuthorFirstName.innerHTML = reimbursement.author.firstName;
        console.log(reimbursement);

        let reimbursementAuthorLastName = document.createElement('td');
        reimbursementAuthorLastName.innerHTML = reimbursement.author.lastName;

        let reimbursementAmountTd = document.createElement('td');
        reimbursementAmountTd.innerHTML = "$" + reimbursement.amount;

        let reimbursementSubmittedTd = document.createElement('td');
        let submitted = new Date(reimbursement.submitted);
        reimbursementSubmittedTd.innerHTML = submitted.getDate() + '-' + (submitted.getMonth() + 1) + '-' +submitted.getFullYear() ;

        let reimbursementResolvedTd = document.createElement('td');
        if (reimbursement.resolved == null) {
            reimbursementResolvedTd.innerHTML = "Not Resolved"
        }
        else {
            reimbursementResolvedTd.innerHTML = new Date(reimbursement.resolved);
        }

        let reimbursementDescriptionTd = document.createElement('td');
        reimbursementDescriptionTd.innerHTML = reimbursement.description;

        let reimbursementStatus = document.createElement('td');
        reimbursementStatus.innerHTML = reimbursement.status.status;

        let reimbursementTypeTd = document.createElement('td');
        reimbursementTypeTd.innerHTML = reimbursement.type.type;

        let reimbursementResolverTd = document.createElement('td');
        if (reimbursement.resolver == null) {
            reimbursementResolverTd.innerHTML = "No Resolver"
        }
        else {
            reimbursementResolverTd.innerHTML = reimbursement.resolver.firstName + " " + reimbursement.resolver.lastName;
        }

         // reimbursementSubmittedTd.innerHTML = submitted;

        //  submitted.getMonth()+1+"/"+submitted.getDate() +"/"+submitted.getFullYear();
        // submitted.getFullYear()+'-'+(submitted.getMonth()+1)+'-'+submitted.getDate();
        //  var today = new Date();
        //  var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();

        tr.appendChild(reimbursementIdTd);
        tr.appendChild(reimbursementAuthorFirstName);
        tr.appendChild(reimbursementAuthorLastName);
        tr.appendChild(reimbursementAmountTd);
        tr.appendChild(reimbursementSubmittedTd);
        tr.appendChild(reimbursementResolvedTd);
        tr.appendChild(reimbursementDescriptionTd);
        tr.appendChild(reimbursementStatus);
        tr.appendChild(reimbursementTypeTd);
        tr.appendChild(reimbursementResolverTd);

        tbody.appendChild(tr);


    }
}
reimbursementButton.addEventListener('click', addReimbursement);
window.addEventListener('load', onLoad);