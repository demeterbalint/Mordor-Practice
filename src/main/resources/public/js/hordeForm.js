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

    fetch(base_url + "/hordes", {
        method: "POST",
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