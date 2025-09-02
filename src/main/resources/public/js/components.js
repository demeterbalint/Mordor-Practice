const base_url = "http://localhost:8080/api"
const components = ["list-div", "orc-form-div", "horde-list-div", "horde-form-div"];

function showComponent(component) {
    for (let i = 0; i < components.length; i++) {
        document.getElementById(components[i]).style.display = "none";
    }
    document.getElementById(component).style.display = "block";
    document.getElementById("error-div").innerHTML = "";
}
