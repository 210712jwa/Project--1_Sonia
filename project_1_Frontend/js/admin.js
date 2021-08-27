let filterInput = document.getElementById('status');
let filterButton = document.getElementById('filterButton');

function onLoad(event){
    fetch('http://localhost:7000/currentuser',{
        'credentials': 'include',
    'method':'GET'
    }).then((response) =>{
        if (response.status === 401){
            window.location.href= '/index.html'
        }else if (response.status === 200){
    return response.json();
        }
    }).then((user)=> {
        return fetch (`http://localhost:7000/getAllRequests`,{
            'method':'GET',
            'credentials':'include'
        });

    }).then((response) => {
        return response.json()
    }).then((reimbursements) => {
        populateReimbursements(reimbursements);
    })
}

function filter(event){
    event.preventDefault();
    if(filterInput.value === "All"){
        clearTable();
        onLoad();

    }else {

  

    fetch('http://localhost:7000/currentuser',{
        'credentials': 'include',
    'method':'GET'
    }).then((response) =>{
        if (response.status === 401){
            window.location.href= '/index.html'
        }else if (response.status === 200){
    return response.json();
        }
    }).then((user)=> {
        return fetch (`http://localhost:7000/filterRequestsByStatus/${filterInput.value}`,{
            'method':'GET',
            'credentials':'include'
        });

    }).then((response) => {
        return response.json()
    }).then((reimbursements) => {
        clearTable();
        populateReimbursements(reimbursements);
    })
}
}

// function processRequest(event){
//     event.preventDefault();

//     fetch('http://localhost:7000/currentuser',{
//         'credentials': 'include',
//     'method':'GET'
//     }).then((response) =>{
//         if (response.status === 401){
//             window.location.href= '/index.html'
//         }else if (response.status === 200){
//     return response.json();
//         }
//     }).then((user)=> {
//         // return fetch (`http://localhost:7000/filterRequestsByStatus/${filterInput.value}`,{
//         return fetch (`http://localhost:7000/user/${user.id}/processRequest/:reimbid`,{
//             'method':'PUT',
//             'credentials':'include'
//         });

//     }).then((response) => {
//         return response.json()
//     }).then((reimbursements) => {
//         clearTable();
//         populateReimbursements(reimbursements);
//     })
// }


function clearTable(){
    let tbody = document.querySelector('#reimbursement tbody');
    tbody.innerHTML = "";
}

function populateReimbursements(reimbursementArray){
    let tbody = document.querySelector('#reimbursement tbody');
    for(const reimbursement of reimbursementArray){

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
        // console.log(reimbursement);

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

    //  var btn = document.createElement("button");
    //  btn.type = 'button';
    //  btn.value = 'approved';
    //  btn.textContent='Approve';
    //  btn.addEventListener('click',processRequest);

    //  var btn1 = document.createElement("button");
    //  btn1.type = 'button';
    //  btn1.value = 'denied';
    //  btn1.textContent='Denied';
    //  btn1.addEventListener('click',processRequest);

        let reimbursementTypeTd = document.createElement('td');
        reimbursementTypeTd.innerHTML = reimbursement.type.type;

        let reimbursementResolverTd = document.createElement('td');
        if (reimbursement.resolver == null) {
            reimbursementResolverTd.innerHTML = "No Resolver"
        }
        else {
            reimbursementResolverTd.innerHTML = reimbursement.resolver.firstName + " " + reimbursement.resolver.lastName;
        }
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

        if(reimbursementStatus.innerHTML === "pending"){
          let  approveButton = document.createElement('button');
            approveButton.innerHTML = "Approve";
        approveButton.value = "2";
        tr.appendChild(approveButton);
            approveButton.onclick = function(){
              
                fetch(`http://localhost:7000/processRequest/${reimbursement.id}/${approveButton.value}`,{
                    'credentials':'include',
                    'method':'PUT',
                    headers :{
                        'Content-Type':'application/json'
                    },
                   
                }).then((response) =>{
                    if (response.status ===200){
                        window.location.href = '/admin.html'
        
                    }
                })
            };
           let  denyButton = document.createElement("button")
            denyButton.innerHTML = "Deny"
            denyButton.value = "3";
            tr.appendChild(denyButton);
            denyButton.onclick = function(){
               
                fetch(`http://localhost:7000/processRequest/${reimbursement.id}/${denyButton.value}`,{
                    'credentials':'include',
                    'method':'PUT',
                    headers :{
                        'Content-Type':'application/json'
                    },
                  
                }).then((response) =>{
                    if (response.status ===200){
                        window.location.href = '/admin.html'
        
                    }
                })
            };
        }

        tbody.appendChild(tr);
    }

    }

    filterButton.addEventListener('click',filter);
    window.addEventListener('load',onLoad);