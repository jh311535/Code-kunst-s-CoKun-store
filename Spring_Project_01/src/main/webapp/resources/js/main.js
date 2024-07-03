let currentIndex = 0; // 현재 슬라이드 인덱스

// 슬라이드를 이동시키는 함수
function slide(offset) {
    const slides = document.querySelectorAll('.book-slide');
    currentIndex += offset;

    // 슬라이드 인덱스를 순환하도록 설정
    if (currentIndex >= slides.length) {
        currentIndex = 0;
    } else if (currentIndex < 0) {
        currentIndex = slides.length - 1;
    }

    // 슬라이더를 이동시키기
    const sliderInner = document.querySelector('.book-slider-inner');
    sliderInner.style.transform = `translateX(-${currentIndex * 100}%)`;
}

// 자동으로 슬라이드를 이동시키는 함수
function autoSlide() {
    slide(1); // 다음 슬라이드로 이동
    setTimeout(autoSlide, 3000); // 3초마다 자동으로 슬라이드 이동
}

// 페이지 로드 시 자동 슬라이드 시작
window.onload = autoSlide;

function openSection(evt, sectionName) {
    // 모든 탭 콘텐츠 숨기기
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    // 모든 탭 링크의 활성화 상태 제거
    tablinks = document.getElementsByClassName("tablink");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    // 선택된 섹션 보여주기
    document.getElementById(sectionName).style.display = "block";
    evt.currentTarget.className += " active";
    
    // 첫 10개의 책만 노출
    var books = document.getElementById(sectionName).getElementsByClassName("product");
    for (i = 0; i < books.length; i++) {
        if (i < 10) {
            books[i].style.display = "block";
        } else {
            books[i].style.display = "none";
        }
    }
}

// 기본으로 첫 번째 탭 열기
document.addEventListener("DOMContentLoaded", function() {
    document.querySelector(".tablink").click();
});