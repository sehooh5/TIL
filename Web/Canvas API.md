 # Canvas API

[TOC]

- 웹페이지에 그림을 그릴 수 있도로 지원하는 API

- 그림을 그리기 위한 영역이 필요한데 이때 사용하는 태그가  `<canvas>` 

  ```
  <canvas id="draw" width = "400" height = "300"></canvas>
  ```

- 객체 접근은 DOM 객체로 접근

  ```
  var area = document.getElementById("draw");
  var ctx = area.getContext("2d");
  ctx.fillRect(10,10,100,100); 색이 칠해진 사각형을 그린다.
  ```

  #### 그리기 기능을 지원하는 메서드들

  ![image-20200113111542786](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200113111542786.png)

  ​	**moveto, lineto 를 많이 사용한다

  ​	**lineto 는 밑바탕 선이여서 stroke 나 fill로 채워줘야한다

  ​	**arc 에서 Angle 은 Mate.pi 의 값으로 주고 시작은 3시방향부터 시작한다

  ![image-20200113114117909](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200113114117909.png)

  

- **그라디언트**

  - createLinearGradient(x1,y1,x2,y2) : 선형 그라디언트 객체를 생성한다.
  - createRadialGradient(x1,y1,r1,x2,y2,r2) : 원형 그라디언트 객체를 생성한다.
  - CanvasGradient 객채의 메서드
    - addColorStop(position, color) : position(0.0~1.0) 위치에 color를 설정한다

- **패턴**

  - createPattern(image,type)
    - image는 CanvasImageSource 객체를 지정
    - type 은 repeat-x, repeat-y, no-repeat 중 선택한다.

- **save() - restore()**

  - 캔버스의 상태정보를 **스택**에 저장 ==> 불러오기하면 마지막에 저장된게 불러와진다
  - 저장 정보 : 회전, 크기, 스트로크 등등



#### 스타일

- **색상**

  - fillStyle : 채워질 색상 지정

  - strokeStyle : 테두리 색상

  - globalAlpha : 투명도 지정 0-1(투명 - 불투명)

    ```javascript
    <!DOCTYPE HTML>
    <html>
    <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <script type="application/javascript">  
    function draw_m() {  
      var m_canvas = document.getElementById('m');  
    
      if(m_canvas.getContext) { 
        var m_ctx = m_canvas.getContext('2d'); 
    	
        m_ctx.fillStyle = "rgb(200,0,0)";  
        m_ctx.fillRect (10, 10, 150, 150);  
        m_ctx.fillStyle = "rgb(0,0,200)";  
        m_ctx.fillRect (160, 10, 150, 150);   
        m_ctx.fillStyle = "rgb(0,200,0)";  
        m_ctx.fillRect (160, 160, 150, 150); 
        m_ctx.fillStyle = "yellow";  
        m_ctx.fillRect (10, 160, 150, 150); 	
    
        m_ctx.globalAlpha=0.4;
    	m_ctx.beginPath();
    	m_ctx.arc(160,160,100,0,Math.PI*2,true);
    	m_ctx.fillStyle="white";
    	m_ctx.fill();
    	m_ctx.beginPath();
    	m_ctx.arc(160,160,60,0,Math.PI*2,true);
    	m_ctx.fillStyle="blue";
    	m_ctx.fill();	
      }  
      else {
        alert("사파리 브라우저나 파이어폭스 1.5 이상의 브라우저에서만 사이트 내용을 제대로 볼 수 있습니다.")
      }
    
    }  
    </script>  
    </head>  
    
    <body onload="draw_m()">  
    <canvas id="m" width="500" height="400"></canvas>  
    </body>  
    </html>  
    ```

    

- **선**

  ![image-20200113131644099](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200113131644099.png)

- **그림자 / 도형변형 / 도형합성**

  ![image-20200113131844390](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200113131844390.png)

- **비트맵이미지 관리**

  - createImageData(sw, sh)
  - createImageData(ImageData 객체)
  비트맵 이미지 객체(ImageData)를 생성핚다.
  - getImageData(sx, sy, sw, sh) :
  - `<canvas>`객체의 주어진 영역의 데이터를 비트맵 이미지 객체(ImageData)로 추출한다.
  - putImageData(ImageData 객체, dx, dy) :
    `<canvas>`객체의 (dx, dy) 위치에 비트맵 이미지 객체(ImageData)의 데이터를 출력한다.

- **HTMLObjectCanvas 객체 저장**
  toDataURL() : `<canvas>` 태그 영역의 모듞 내용을 png 형식의 URI 문자열로 벾홖하여
  리턴핚다.

  ```
  var canvas = document.getElementById("draw");
  var dataURL = canvas.toDataURL();
  console.log(dataURL);
  // "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNby
  // blAAAADElEQVQImWNgoBMAAABpAAFEI8ARAAAAAElFTkSuQmCC"
  ```

- `<video>` 와 `<audio>` 태그 관련 API

  - controls, autoplay, loop
    설정 여부를 조정하는 boolean 타입의 속성이다.

  - currentTime
    현재의 재생 위치를 초 단위로 나타내는 속성이다.

  - duration
    오디오 또는 비디오 파일의 길이를 초단위로 나타내는 속성이다

  - ended/paused
    재생의 종료여부 또는 일시 정지 여부를 나타내는 속성이다.

  - canPlayType(type)
    브라우저가 해당 미디어 타입을 재생할 수 있는지를 나타내는 문자열이다.

  - play()
    현재 위치에서 재생을 시작핚다.

  - pause()
    오디오가 재생 중일 경우 일시 정지한다.

    ```javascript
    var canvas, video;
    function initiate(){ 
      var elem=document.getElementById('canvas');
      canvas=elem.getContext('2d');
      video=document.getElementById('media');
    
      video.addEventListener('click', push, false);
    }
    function push(){
      if(!video.paused && !video.ended){
        video.pause();
        window.clearInterval(loop);
      }else{
        video.play();
        loop=setInterval(processFrames, 33);
      }
    }
    function processFrames(){	//카피해서 옆에 33/1000의 이미지를 보여주는 효과 + 흑백 공식 적용
      canvas.drawImage(video,0,0);
    
      var info=canvas.getImageData(0,0,483,272);
      var pos;
      var gray;
    /*  for(x=0;x<=483;x++){	//색상은 흑백으로 바꾸는 공식
        for(y=0;y<=272;y++){
          pos=(info.width*4*y)+(x*4);
          gray=parseInt(info.data[pos]*0.2989 + info.data[pos+1]*0.5870 + info.data[pos+2]*0.1140);
          info.data[pos]=gray;
          info.data[pos+1]=gray;
          info.data[pos+2]=gray;
        }
      }*/
      canvas.putImageData(info,0,0);
    }
    window.addEventListener("load", initiate, false);
    ```

    

  

  

