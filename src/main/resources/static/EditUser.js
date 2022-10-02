const formEdit = document.getElementById('formEdit');
formEdit.onsubmit = async (e) => {
    e.preventDefault();

    let formData = new FormData(formEdit);

    let response = await fetch('/api/'+ formData.get("id"), {
        method: 'PUT',
        body: formData,
    });

    $('#modalEdit').modal('hide');
};