axios.get('/apis/totalapis/')
.then(res => {
    document.querySelector('#total_apis').innerHTML = res.data.total_apis
    document.querySelector('#good_apis').innerHTML = res.data.good_apis
    document.querySelector('#bad_apis').innerHTML = res.data.bad_apis
})