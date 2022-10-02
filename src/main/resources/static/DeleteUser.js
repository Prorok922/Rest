const formDelete = document.getElementById('formDelete');
formDelete.onsubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData(formDelete);
    let response = await fetch('/api/'+ formData.get("id"), {
        method: 'DELETE',
    })

    $("#modalDelete").modal("hide");
};
