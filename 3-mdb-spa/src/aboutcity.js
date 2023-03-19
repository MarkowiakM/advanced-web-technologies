/* eslint-disable no-return-assign */

const getColor = () => Math.floor(Math.random() * 255);

// bgc onclick change
function handlePersonClick(person) {
  person.addEventListener(
    "click",
    (e) => {
      person
        .style
        .backgroundColor = `rgb(${getColor()},${getColor()},${getColor()})`;
    },
  );
}

const people = [...document.getElementsByClassName("person")];

for (let i = 0; i < people.length; i += 1) {
  handlePersonClick(people[i]);
}

// window.scroll
const handlePageScroll = () => {
  const nav = document.getElementsByClassName("nav")[0];
  if (window.scrollY > 200) {
    nav.classList.add("nav-scrolled");
  } else {
    nav.classList.remove("nav-scrolled");
  }
};

window.addEventListener("scroll", handlePageScroll);
