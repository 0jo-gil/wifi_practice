// 로딩 중 표시
const loadingModal = () => {
    const element = `
        <div class="modal-wrap">
            <h1>LOADING...</h1>
        </div>
    `

    document.body.insertAdjacentHTML('beforeend', element);
}
const removeLoadingModal = () => {
    document.querySelector('.modal-wrap').remove();
}

const setInputValue = async (key, value) => {
    const element = document.querySelector("input[name="+ key +"]");
    element.value = value;
}

const locationValue = ({coords}) => {
    let lat = coords.latitude;
    let lnt = coords.longitude;

    console.log( lat, lnt);

    setInputValue('lat', lat);
    setInputValue('lnt', lnt).then(() => {
        removeLoadingModal();
    });
}



// 위치정보 가져오기 이벤트
const getLocationHandler = async (e) => {
    e.preventDefault();
    if(!geolocation) {
        alert("위치정보 지원 불가!")
    } else {
        loadingModal();
        await geolocation.getCurrentPosition(locationValue);
    }
}



const requestApi = async (params, callback, element) => {
    const {url, method} = params;
    const xhr = new XMLHttpRequest();

    xhr.open(method, url);

    // loadingModal();

    xhr.onreadystatechange = (e) => {
        const {target} = e;

        if(target.readyState === XMLHttpRequest.DONE){
            // removeLoadingModal()
            callback(e);
        } else {
            // element.innerHTML = 'WIFI 정보 저장을 실패했습니다.';
            // removeLoadingModal()
            // console.error('통신 에러');
        }
    }

    xhr.send();
}