axios.get('/rankings/')
.then(res => {
    console.log(res.data.msg)
    let result = res.data.result
    let d_result = res.data.d_result
    console.log(result[0])
    ranking = document.querySelector('#ranking-tbody')
    d_ranking = document.querySelector('#ranking-tbody-r')
    ranking.innerHTML = ''
    d_ranking.innerHTML = ''
    for(var i = 0; i < 5; i++){
        ranking.innerHTML += "<tr><td class=\"text-center\">"+result[i].api_rank
                            +"</td><td><p class=\"text-decoration-none\" onMouseOver=\"this.style.color='#AC58FA'\" onMouseOut=\"this.style.color='#000'\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + result[i].api_name +"</p></td>" 
    }
    for(var j = 0; j < 5; j++){
        d_ranking.innerHTML += "<tr><td class=\"text-center\">"+d_result[j].d_api_rank
                                +"</td><td><p class=\"text-decoration-none\" onMouseOver=\"this.style.color='#AC58FA'\" onMouseOut=\"this.style.color='#000'\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + d_result[j].down_name +"</p></td>" 

    }
})