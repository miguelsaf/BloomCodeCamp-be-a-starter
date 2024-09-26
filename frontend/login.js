const projectForm = document.querySelector("#login-form");

projectForm.onsubmit = async function(evt) {
  evt.preventDefault();
  const credentialsRequest = {
    "username": document.querySelector("#username").value,
    "password": document.querySelector("#password").value,
  }
  axios.post("http://192.168.122.157:8080/api/auth/login", credentialsRequest)
  .then((res) => {
    console.log(res);
    //window.location.reload();
    window.location.href = '/indexAuth.html';
  })
  .catch(error => {
    console.error('Error', error);
  });
}

