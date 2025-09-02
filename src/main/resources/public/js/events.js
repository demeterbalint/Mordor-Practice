document.addEventListener('DOMContentLoaded', () => {
    console.log("Application loaded.");
    renderOrcs();

    document.getElementById("nav-index").onclick = () => {
        renderOrcs();
    };

    document.getElementById("new-orc-link").onclick = () => {
        showNewForm();
    };

    document.getElementById("list-link").onclick = () => {
        renderOrcs();
    };

    document.getElementById("orc-form").onsubmit = (event) => {
        event.preventDefault(); //disable default browser form submit
    };

    document.getElementById("orc-submit").onclick = () => {
        submitOrc();
    };

    document.querySelectorAll('.nav-link').forEach(link => {
        link.addEventListener('click', () => {
            const navbarCollapse = document.getElementById('navbar');
            if (navbarCollapse.classList.contains('show')) {
                $('.navbar-collapse').collapse('hide');
            }
        })
    })
});