/* eslint-disable no-return-assign */
const IMG_PATHS = [
  "market-old",
  "wieniawa-old",
  "slowianska",
  "railway-station"];

const QUOTES = [
  "jednak nic ci nie powiem",
  "bardzo lubię spanko",
  "czekolaaaaaaada"];

const FAMOUS_PEOPLE = [
  "Jan Kowalski",
  "Maria Konopnicka",
  "Kazimierz Koza",
  "Stefan Batory",
  "Adam Mickiewicz"];

function getRandomFromArray(values) {
  return values[Math.floor(Math.random() * values.length)];
}

const getColor = () => Math.floor(Math.random() * 255);

// insert new elems into list
const list = document.getElementById("ordered-list");

FAMOUS_PEOPLE.forEach((person) => {
  const listItem = document.createElement("li");
  const span = document.createElement("span");
  span.textContent = person;
  listItem.appendChild(span);
  list.appendChild(listItem);
});

// replaceChild, removeChild
const imgContainer = document.getElementsByClassName("img-container")[0];

function createPhoto() {
  const img = document.createElement("img");
  const imgName = getRandomFromArray(IMG_PATHS);
  img.src = `./Images/${imgName}.jpg`;
  img.alt = imgName;
  img.classList.add("photo");
  return img;
}

(function chooseRandomPhoto() {
  imgContainer.appendChild(createPhoto());
}());

function replacePhoto() {
  const img = document.getElementsByClassName("photo")[0];
  if (img) {
    imgContainer
      .replaceChild(createPhoto(), document.getElementsByClassName("photo")[0]);
  }
}

function removePhoto() {
  const img = document.getElementsByClassName("photo")[0];
  if (img) {
    imgContainer.removeChild(img);
  }
}

document.getElementById("replace-photo")
  .addEventListener("click", replacePhoto);

document.getElementById("remove-photo")
  .addEventListener("click", removePhoto);

// insertBefore, createTextNode
const insertBeforeBtn = document.getElementById("insert-before");
const createTextNodeBtn = document.getElementById("create-text-node");
const paragraph = document.getElementById("text-node");

function handleInsertBeforeBtnClick() {
  const quote = document.createElement("p");
  quote.classList.add("quote__p");
  quote.textContent = getRandomFromArray(QUOTES);
  insertBeforeBtn.parentNode.insertBefore(quote, insertBeforeBtn);
}

insertBeforeBtn.addEventListener("click", handleInsertBeforeBtnClick);

function handleCreateTextNodeBtnClick() {
  paragraph.appendChild(document.createTextNode(getRandomFromArray(QUOTES)));
}

createTextNodeBtn.addEventListener("click", handleCreateTextNodeBtnClick);

// bgc onclick change
function handlePersonClick(person) {
  person.addEventListener(
    "click",
    (e) => {
      const { id } = e.target;
      switch (id) {
        case "roman-maciejewski":
          person.style.color = "white";
          break;
        case "stanislaw-leszczynski":
          person.style.color = "green";
          break;
        case "alfred-smoczyk":
          person.style.color = "pink";
          break;
        default:
          person.style.color = "blue";
      }
      person
        .style
        .backgroundColor = `rgb(${getColor()},${getColor()},${getColor()})`;
    },
  );
}

// [...document.getElementsByClassName("person")]
//   .forEach((person) => handlePersonClick(person));

const people = [...document.getElementsByClassName("person")];

for (let i = 0; i < people.length; i += 1) {
  handlePersonClick(people[i]);
}

// window.scroll
const handlePageScroll = () => {
  const nav = document.getElementById("nav");
  if (window.scrollY > 200) {
    nav.classList.add("nav-scrolled");
  } else {
    nav.classList.remove("nav-scrolled");
  }
};

window.addEventListener("scroll", handlePageScroll);

// user settings

const bgcColorInput = document.getElementById("bgc-color");
const fontColorInput = document.getElementById("font-color");
const fontInput = document.getElementById("font");
const background = document.getElementsByClassName("user-changes")[0];

document.getElementById("bgc-color-btn").addEventListener(
  "click",
  () => background.style.background = bgcColorInput.value,
);

document.getElementById("font-color-btn").addEventListener(
  "click",
  () => background.style.color = fontColorInput.value,
);

document.getElementById("font-btn")
  .addEventListener("click", () => {
    background.style.fontFamily = fontInput.value;
  });
