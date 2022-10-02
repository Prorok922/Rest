
fetch("http://localhost:8080/api/users").then((data)=>{
    //получаем json и отдаем его объект
    return data.json();
}).then((objJson) => {
    let tableData = "";
    //получаем из общего списка пользователей
    objJson.map((objUser) => {
        //добавляем в таблицу каждого пользователя
        tableData += `<tr>
        <td>${objUser.id}</td>
        <td>${objUser.firstName}</td>
        <td>${objUser.lastName}</td>
        <td>${objUser.age}</td>
        <td>${objUser.email}</td>
        <td>${objUser.roles.map((objRole) => objRole.role)}</td>
        
        <td>
            <button type="button" class="btn btn-info text-white js-open-modal"
                data-toggle="modal" data-target="#modalEdit"
                data-bs-userId=${objUser.id}
                data-bs-userFirstName=${objUser.firstName} 
                data-bs-userLastName=${objUser.lastName} 
                data-bs-userAge=${objUser.age}
                data-bs-userEmail=${objUser.email} 
                data-bs-toggle="modalEdit"
                data-bs-target="#ModalEdit">Edit</button>
        </td>

        <td>
            <button type="button" class="btn btn-danger js-open-modal"
                data-toggle="modal" data-target="#modalDelete"
                data-bs-userId=${objUser.id} 
                data-bs-userFirstName=${objUser.firstName} 
                data-bs-userLastName=${objUser.lastName} 
                data-bs-userAge=${objUser.age}
                data-bs-userEmail=${objUser.email} 
                data-bs-toggle="modalDelete"
                data-bs-target="#ModalDelete">Delete</button>
        </td>
        </tr>`;
    });
    document.getElementById("table_body").
        innerHTML = tableData;

    buttonWork();
}).catch((err) => {
    console.log(err);
})

