function renderHorde(idx, horde) {
    const row = document.createElement("tr");

    const idCell = document.createElement("td");
    idCell.innerHTML = horde.id;

    const nameCell = document.createElement("td");
    nameCell.innerHTML = horde.name;

    const headcountCell = document.createElement("td");
    headcountCell.innerHTML = horde.headcount;

    const buttonCell = document.createElement("td");

    const deleteButton = document.createElement("button");
    deleteButton.innerHTML = "<i class=\"fas fa-trash-alt\"></i>";
    deleteButton.setAttribute("id", "delete-" + horde.id);
    deleteButton.setAttribute("class", "btn btn-danger delete-button");
    deleteButton.onclick = () => {
        deleteHorde(horde);
    };

    row.appendChild(idCell);
    row.appendChild(nameCell);
    row.appendChild(headcountCell);
    row.appendChild(buttonCell);
    buttonCell.appendChild(deleteButton);
    return row;
}

function renderHordes() {
    document.getElementById("horde-list-table-body").innerHTML = "";
    fetch(base_url + "/hordes", {
        method: "GET",
    })
        .then(response => response.json())
        .then(data => {
            for (let i = 0; i < data.length; i++) {
                document.getElementById("horde-list-table-body").appendChild(renderHorde(i, data[i]));
            }
            showComponent("horde-list-div");
        })
        .catch(err => {
            document.getElementById("error-div").innerHTML = "<span class='text-danger'>" + err + "</span>";
        })
}

function deleteHorde(horde) {
    fetch(base_url + `/hordes/${horde.id}`, {
        method: "DELETE",
    })
    .then(response => {
        console.log('Horde ' + horde.name + ' deleted');
        renderHordes()
    })
        .catch(err => {
            document.getElementById("error-div").innerHTML = "<span class='text-danger'>" + err + "</span>";
        })
}