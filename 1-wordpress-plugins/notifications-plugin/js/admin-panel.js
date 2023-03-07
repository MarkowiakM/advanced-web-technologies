
const toggleVisibility = (elem) =>
    // newNotificationForm.classList.toggle("hidden");
    elem.classList.toggle("hide");



window.onload = () => {
    const newNotificationForm = document.getElementById("new-notification-form");
    const addNewButton = document.getElementById("add-notification-button");
    const viewBtns = document.getElementsByClassName("view-btn");
    const codeViewBtns = document.getElementsByClassName("view-code-btn");
    const editBtns = document.getElementsByClassName("edit-btn");

    addNewButton.addEventListener('click', () => toggleVisibility(newNotificationForm));

    [...viewBtns].map((btn, idx) => { btn.addEventListener('click', () => toggleVisibility(document.getElementById('notification-view-' + idx))) });

    [...codeViewBtns].map((btn, idx) => { btn.addEventListener('click', () => toggleVisibility(document.getElementById('notification-code-view-' + idx))) });

    [...editBtns].map((btn, idx) => { btn.addEventListener('click', () => toggleVisibility(document.getElementById('notification-edit-view-' + idx))) });

};
