const newUserForm = document.getElementById('newUserForm');
newUserForm.onsubmit = async (e) => {
    e.preventDefault();

    let response = await fetch('/api/new-user', {
        method: 'POST',
        body: new FormData(newUserForm)
    });

    let result = await response.json();

};
