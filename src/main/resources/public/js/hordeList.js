function renderHorde(idx, horde) {
    const row = document.createElement("tr");

    const idCell = document.createElement("td");
    idCell.innerHTML = horde.id;

    const nameCell = document.createElement("td");
    nameCell.innerHTML = horde.name;

    const headcountCell = document.createElement("td");
    headcountCell.innerHTML = horde.headcount;

    const buttonCell = document.createElement("td");

    const editButton = document.createElement("button");
    editButton.innerHTML = "<i class=\"fas fa-edit\"></i>";
    editButton.setAttribute("id", "edit-" + horde.id)
    editButton.setAttribute("class", "btn btn-primary edit-button mr-2");
    editButton.style.width = "40px";
    editButton.onclick = () => {
        editHorde(horde);
    }

    const deleteButton = document.createElement("button");
    deleteButton.innerHTML = "<i class=\"fas fa-trash-alt\"></i>";
    deleteButton.setAttribute("id", "delete-" + horde.id);
    deleteButton.setAttribute("class", "btn btn-danger delete-button");
    deleteButton.style.width = "40px";
    deleteButton.onclick = () => {
        deleteHorde(horde);
    };

    row.appendChild(idCell);
    row.appendChild(nameCell);
    row.appendChild(headcountCell);
    row.appendChild(buttonCell);
    buttonCell.appendChild(editButton);
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

function editHorde(horde) {
    try {
        setHordeEditForm(horde);
        showComponent("horde-form-div");
    } catch (err) {
        console.warn(err);
        document.getElementById("error-div").innerHTML = "<span class='text-danger'>" + err + "</span>";
    }
}