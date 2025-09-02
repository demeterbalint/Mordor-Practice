function renderOrc(idx, orc) {
    const row = document.createElement("tr");

    const idCell = document.createElement("td");
    idCell.innerHTML = orc.id;

    const nameCell = document.createElement("td");
    nameCell.innerHTML = orc.name;

    const raceCell = document.createElement("td");
    raceCell.innerHTML = orc.orcRaceType;

    const weaponsCell = document.createElement("td");
    const weaponsUl = document.createElement("ul");
    weaponsCell.appendChild(weaponsUl);
    for (let i = 0; i < orc.weapons.length; i++) {
        const weaponLi = document.createElement("li");
        weaponLi.innerHTML = orc.weapons[i];
        weaponsUl.appendChild(weaponLi);
    }

    const killCountCell = document.createElement("td");
    killCountCell.innerHTML = orc.killCount;

    const orcHordeCell = document.createElement("td");
    if (orc.hordeName == null) {
        orcHordeCell.innerHTML = "-";
    } else {
        orcHordeCell.innerHTML = orc.hordeName;
    }

    const buttonCell = document.createElement("td");

    const deleteButton = document.createElement("button");
    deleteButton.innerHTML = "<i class=\"fas fa-trash-alt\"></i>";
    deleteButton.setAttribute("id", "delete-" + orc.id);
    deleteButton.setAttribute("class", "btn btn-danger delete-button");
    deleteButton.onclick = () => {
        deleteOrc(orc);
    };

    row.appendChild(idCell);
    row.appendChild(nameCell);
    row.appendChild(raceCell);
    row.appendChild(killCountCell);
    row.appendChild(weaponsCell);
    row.appendChild(orcHordeCell);
    row.appendChild(buttonCell);
    buttonCell.appendChild(deleteButton);
    return row;
}

function renderOrcs() {
    document.getElementById("list-table-body").innerHTML = "";
    fetch(base_url + "/orcs", {method: 'GET'})
        .then(resp => {
            if (resp.status === 404) {
                throw new Error('URL not found');
            }
            return resp.json()
        })
        .then(data => {
            // console.log(data);
            for (let i = 0; i < data.length; i++) {
                document.getElementById("list-table-body").appendChild(renderOrc(i, data[i]));
            }
            showComponent("list-div");
        })
        .catch(err => {
            document.getElementById("error-div").innerHTML = "<span class='text-danger'>" + err + "</span>";
        });
}

function deleteOrc(orc) {
    fetch(base_url + `/orcs/${orc.id}`, {method: 'DELETE'})
        .then(resp => {
            console.log('Orc ' + orc.name + ' deleted');
            renderOrcs();
        })
        .catch(err => {
            document.getElementById("error-div").innerHTML = "<span class='text-danger'>" + err + "</span>";
        })
}
