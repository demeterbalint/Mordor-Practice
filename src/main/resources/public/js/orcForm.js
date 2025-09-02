let weapons;
let races;
let hordes;


function clearForm() {
    document.getElementById("orc-id").value = "";
    document.getElementById("orc-name").value = "";

    document.getElementById("orc-race").innerHTML = "";
    let option;
    for (let race in races) {
        option = document.createElement("option");
        option.value = race;
        option.innerHTML = races[race];
        document.getElementById("orc-race").appendChild(option);
    }

    document.getElementById("orc-weapons").innerHTML = "";
    let checkBox;
    for (let weapon in weapons) {
        checkBox = document.createElement("input");
        checkBox.setAttribute("type", "checkbox");
        checkBox.setAttribute("id", "weapon" + weapon);
        checkBox.dataset.value = weapon;
        const checkBoxLabel = document.createElement("label");
        checkBoxLabel.setAttribute("for", "weapon" + weapon);
        checkBoxLabel.innerHTML = "&nbsp;" + weapons[weapon];
        const br = document.createElement("br");
        document.getElementById("orc-weapons").appendChild(checkBox);
        document.getElementById("orc-weapons").appendChild(checkBoxLabel);
        document.getElementById("orc-weapons").appendChild(br);
    }

    document.getElementById("orc-kill-count").value = "";

    document.getElementById("orc-horde-name").innerHTML = "";
    let hordeOption;
    hordeOption = document.createElement("option");
    hordeOption.value = "";
    hordeOption.innerHTML = "No Horde";
    document.getElementById("orc-horde-name").appendChild(hordeOption);
    for (let i = 0; i < hordes.length; i++) {
        hordeOption = document.createElement("option");
        hordeOption.value = hordes[i].name;  // Use name instead of id
        hordeOption.innerHTML = hordes[i].name;
        document.getElementById("orc-horde-name").appendChild(hordeOption);
    }
}


function parseOrcForm() {
    const orc = {};
    orc.id = document.getElementById("orc-id").value;

    orc.name = document.getElementById("orc-name").value;

    const raceOptions = document.getElementById("orc-race").childNodes;
    for (let i = 0; i < raceOptions.length; i++) {
        if (raceOptions[i].selected) {
            orc.orcRaceType = raceOptions[i].value;
        }
    }

    orc.weapons = [];
    const weaponCheckboxes = document.getElementById("orc-weapons").childNodes;
    for (let i = 0; i < weaponCheckboxes.length; i++) {
        if (weaponCheckboxes[i].checked)
            orc.weapons.push(weaponCheckboxes[i].dataset.value);
    }

    orc.killCount = document.getElementById("orc-kill-count").value;
    if (orc.killCount === "")
        orc.killCount = 0;
    
    orc.hordeName = document.getElementById("orc-horde-name").value;
    if (orc.hordeName === "") {
        orc.hordeName = null;
    }

    return orc;
}

function updateWeapons() {
    return fetch(base_url + "/orcs/weapons",
        {method: 'GET'})
        .then(resp => resp.json())
        .then(data => {
            console.log(data);
            weapons = data;
        })
        .catch(err => console.warn(err))
}

function updateRaces() {
    return fetch(base_url + "/orcs/races", {method: 'GET'})
        .then(resp => resp.json())
        .then(data => {
            console.log(data);
            races = data
        })
        .catch(err => {
            console.warn(err)
        });
}

function updateHordes() {
    return fetch(base_url + "/hordes", {method: 'GET'})
        .then(resp => resp.json())
        .then(data => {
            console.log(data);
            hordes = data;
        })
        .catch(err => {
            console.warn(err)
        })
}

function showNewForm() {
    updateHordes()
        .then(() => {
            updateWeapons()
                .then(() => {
                    updateRaces()
                        .then(() => {
                            clearForm();
                            showComponent("orc-form-div");
                        })
                })
        })
        .catch(err => {
            console.warn(err);
            document.getElementById("error-div").innerHTML = "<span class='text-danger'>" + err + "</span>";
        })
}

function submitOrc() {
    const orc = parseOrcForm();

    fetch(base_url + "/orcs", {
        method: 'POST',
        body: JSON.stringify(orc),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(() => {
            renderOrcs();
        })
        .catch((err) => {
            console.log(err);
            document.getElementById("error-div").innerHTML = "<span class='text-danger'>" + err + "</span>";
        });
}
