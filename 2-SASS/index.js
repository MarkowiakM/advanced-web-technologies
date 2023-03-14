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


// gallery

const IMAGES = [...document.getElementsByClassName('gallery__section--div')];

const extendImage = (img) => {
    img.classList.toggle('extended');
}

IMAGES.forEach((img) => img.addEventListener('click', () => extendImage(img)));
