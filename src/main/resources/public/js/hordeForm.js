function clearHordeForm() {
    document.getElementById("horde-id").value = "";
    document.getElementById("horde-name").value = "";
}

function showNewHordeForm() {
    clearHordeForm();
    showComponent("horde-form-div");
}

function parseHordeForm() {
    const horde = {};
    horde.id = document.getElementById("horde-id").value;
    horde.name = document.getElementById("horde-name").value;
    return horde;
}

function submitHorde() {
    const horde = parseHordeForm();
    const method = horde.id ? "PUT" : "POST";
    const url = horde.id ? base_url + "/hordes/" + horde.id : base_url +  "/hordes";

    fetch(url, {
        method: method,
        body: JSON.stringify(horde),
        headers: { "Content-Type": "application/json" }
    })
        .then(() => {
            renderHordes();
        })
        .catch((err) => {
            console.log(err);
            document.getElementById("error-div").innerHTML = "<span class='text-danger'>" + err + "</span>";
        })
}

function setHordeEditForm(horde) {
    document.getElementById("horde-id").value = horde.id;
    document.getElementById("horde-name").value = horde.name;
}