# Geolocation API

- getCurrentPosition() : 현재 위치정보를 추출한다.
  	getCurrentPosition (successCallback [, errorCallback, options] )
- successCallback : 위치정보를 성공적으로 추출했을 때 호출되는 콜백 함수이다.
-  errorCallback : 위치정보를 추출하는데 실패했을 때 호출되는 콜백 함수이다.
- watchPosition() : 주기적으로 반복해서 위치정보를 구하는 작업을 수행핚다.
  watchCurrentPosition(successCallback[, errorCallback, options])