function openTab(evt, tabName) {
  let i, tabcontent, tablinks;

  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }

  document.getElementById(tabName).style.display = "block";
  evt.currentTarget.className += " active";
}

function login(role) {
  const username =
    role === "Admin"
      ? document.getElementById("adminUser").value
      : document.getElementById("studentUser").value;
  const password =
    role === "Admin"
      ? document.getElementById("adminPass").value
      : document.getElementById("studentPass").value;

  alert(`${role} Login Attempt\nUsername: ${username}\nPassword: ${password}`);
  return false; // prevent form submission
}

function exitApp() {
  if (confirm("Are you sure you want to exit?")) {
    window.close(); // works in desktop browsers / apps
  }
}
