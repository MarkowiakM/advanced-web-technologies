const OFFSET = 600;
const ELEMS = [...document.getElementsByClassName('main__section--descr')];

const fadeElem = (elem) => {
    if (elem.offsetTop - OFFSET < window.scrollY) {
        elem.classList.add('active');
    }
};

const fadeInElems = (elems) => {
    elems.forEach((e) => fadeElem(e));
};

document.addEventListener('scroll', () => fadeInElems(ELEMS));