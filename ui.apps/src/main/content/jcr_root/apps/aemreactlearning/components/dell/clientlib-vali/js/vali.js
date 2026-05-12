// AC2: Overflow detection → shows/hides arrows
function checkOverflow(nav) {
    const list = nav.querySelector('.tab-navigation__list');
    if (!list) return;
    nav.classList.toggle('tab-navigation--overflow', list.scrollWidth > list.clientWidth);
}

function initTabNav(nav) {
    const list = nav.querySelector('.tab-navigation__list');
    const btnLeft = nav.querySelector('.tab-navigation__arrow--left');
    const btnRight = nav.querySelector('.tab-navigation__arrow--right');

    if (btnLeft) btnLeft.addEventListener('click', () => { list.scrollLeft -= 160; });
    if (btnRight) btnRight.addEventListener('click', () => { list.scrollLeft += 160; });

    checkOverflow(nav);
    window.addEventListener('resize', () => checkOverflow(nav));
}

// Init all tab navs on page
document.querySelectorAll('.tab-navigation').forEach(initTabNav);