<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<!-- Basic -->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- favicon -->
	<link rel="icon" type="image/png" sizes="16x16"
		href="/pmkim/resources/images/favicon-16x16.png">
    <!-- Site Metas -->
    <title>편마, 김편복 - 오너페이지</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Site Icons -->
    <!-- <link rel="apple-touch-icon" href="/pmkim/resources//pmkim/resources/images/apple-touch-icon.png"> -->
    <link rel="icon" href="/pmkim/resources/images/pmkim.png"  type="image/png" sizes="16X16">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/pmkim/resources/css/bootstrap.min.css">
    <!-- Site CSS -->
    <link rel="stylesheet" href="/pmkim/resources/css/styleOwnerpage.css">
    <link rel="stylesheet" href="/pmkim/resources/css/style_nav.css">
    <!-- Responsive CSS -->
    <link rel="stylesheet" href="/pmkim/resources/css/responsive.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="/pmkim/resources/css/custom.css">
	<link rel="stylesheet" href="/pmkim/resources/css/footerus.css">
</head>

<body>

    <c:set var="sessionMemberId" value="${ sessionScope.id }"/>
    <!-- Start Main Top -->
    <header class="main-header">
        <!-- Start Navigation -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light navbar-default bootsnav">
            <div class="container">
                <!-- Start Header Navigation -->
                <div class="navbar-header">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-menu" aria-controls="navbars-rs-food" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fa fa-bars"></i>
                </button>
                    <a class="navbar-brand" href="/pmkim/home"><img src="/pmkim/resources/images/pmkim_Logo_1.jpg" class="logo" alt=""></a>
                </div>
                <!-- End Header Navigation -->

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="navbar-menu">
                    <ul class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp">
                        <li class="nav-item active"><a class="nav-link" href="/pmkim/home">Home</a></li> <!-- href는 jsp/html 형식이 아닌, {/매핑명}으로  해주시면돼용! 나중에 고쳐주세요~ -->
                        <li class="nav-item active"><a class="nav-link" href="/pmkim/map">지도</a></li> <!--성진오빠파트-->
                        <li class="nav-item active"><a class="nav-link" href="/pmkim/analysis">SNS 분석</a></li> <!-- 세호-->
                        <li class="nav-item active"><a class="nav-link" href="/pmkim/event">행사</a></li> <!-- 지혜파트-->
                        <li class="nav-item active"><a class="nav-link" href="/pmkim/cart">만원의 행복</a></li> <!-- 규영언니파트♡ -->
						<li class="nav-item active"><a class="nav-link" href="/pmkim/news">편의점 뉴스</a></li> <!-- 규영언니파트♡ -->
                        <!-- <li class="nav-item active"><a class="nav-link" data-toggle="modal" data-target="#loginModal" href="/pmkim/#">로그인</a></li>	                        
						<li class="nav-item active"><a class="nav-link" href="/pmkim/signup">회원가입</a></li> -->
						<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
						<c:choose>
							<c:when test="${msg eq 'success' || !empty sessionMemberId}">
								<li class="nav-item active"><a class="nav-link" id="userView" href="/pmkim/ownerpage"><img src="/pmkim/resources/images/star.png" style="width:13px;">${sessionMemberId}님</a></li>
								<li class="nav-item active"><a class="nav-link" id="logout" href="/pmkim/logout"><b>Logout</b></a></li>
								
							</c:when>
							<c:when test="${msg eq 'fail' || msg eq 'logout' || msg eq '' || msg eq null}">
								<li class="nav-item active"><a class="nav-link" id="login" data-toggle="modal" data-target="#loginModal" href="/pmkim/#">로그인</a></li>
								<li class="nav-item active"><a class="nav-link" id="signup" href="/pmkim/signup">회원가입</a></li>	
						    </c:when>
						</c:choose>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
			
            </div>
            
            <!-- Login -->
           
			<!-- Login End-->
        </nav>
        <!-- End Navigation -->
    </header>
    <!-- End Main Top -->

   


    <!-- Start My Account  -->
    <div class="my-account-box-main">
        <div class="container">
            <div class="my-account-page">
                <div class="row">
                    <div class="col-lg-4 col-md-12">
                        <div class="account-box">
                            <div class="service-box">
                                <div class="service-icon">
                                    <a href="#"> <i class="fa fa-user" aria-hidden="true"></i> </a>
                                </div>
                                <div class="service-desc">
                                    <h4>회원 정보 수정</h4>
                                    <p>나만의 정보를 수정해보세요</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-12">
                        <div class="account-box">
                            <div class="service-box">
                                <div class="service-icon">
                                    <a href="/pmkim/ask"> <i class="fas fa-question"></i> </a>
                                </div>
                                <div class="service-desc">
                                    <h4>문의내역</h4>
                                    <p>문의 해보세요</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-12">
                        <div class="account-box">
                            <div class="service-box">
                                <div class="service-icon">
                                    <a href="#"> <i class="fas fa-sign-out-alt"></i> </a>
                                </div>
                                <div class="service-desc">
                                    <h4>회원탈퇴</h4>
                                    <p>더 좋은 서비스를 위해 노력하겠습니다</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-12">
                        <div class="account-box">
                            <div class="service-box">
                                <div class="service-icon">
                                    <a href="/pmkim/upload"> <i class="fas fa-file-upload"></i> </a>
                                </div>
                                <div class="service-desc">
                                    <h4>데이터 업로드</h4>
                                    <p>데이터를 새롭게 업로드하세요</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-12">
                        <div class="account-box">
                            <div class="service-box">
                                <div class="service-icon">
                                    <a href="#"> <i class="fas fa-upload"></i> </a>
                                </div>
                                <div class="service-desc">
                                    <h4>판매 업로드</h4>
                                    <p>행사 상품을 업로드하세요</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-12">
                        <div class="account-box">
                            <div class="service-box">
                                <div class="service-icon">
                                    <a href="#"> <i class="fas fa-edit"></i> </a>
                                </div>
                                <div class="service-desc">
                                    <h4>메모</h4>
                                    <p>해야할 리스트를 정리해보세요</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>
    </div>
    <!-- End My Account -->


	<!-- Start Footer  -->
	<footer>
		<div class="footer-main">
			<div class="container3">
				<div class="row">
					<div class="col-lg-4 col-md-12 col-sm-12">
						<div class="footer-top-box">
							<h3>챗봇(Coming Soon)</h3>
						</div>
					</div>
				</div>
				<br><br>
				<div class="row">
					<div class="col-lg-4 col-md-12 col-sm-12">
						<div class="footer-top-box">
							<h3>We are One ; 곰돌이 감자 </h3>
						</div>
					</div>
				</div>

				<div class="container text-centers">
					<div class="row">
						<!-- Footer 1-->
						<div class="col-lg-422 mb-5 mb-lg-0">
							<a><img src="/pmkim/resources/images/haley.png" class="profile"  alt="" /></a><br><br>
							<a href="https://github.com/JihyeHaley" class="result-text">Haley Oh</a><br><br>
							<a class="result-text">You know? I'm 팀장4;)</a><br><br>
						<a class= "profile-text">has kinda professional language skills English, Chinese, Japanese. Currently working and studying for full-stack developing skills</a>
						
						</div>

						<!-- Footer 2-->
						<div class="col-lg-422 mb-5 mb-lg-0">
							<a><img src="/pmkim/resources/images/seho.png" class="profile"   alt="" /></a><br><br>
							<a href="https://github.com/sehooh5" class="result-text">Seho Oh </a><br><br>
							<a class="result-text">You know? I'm 팀장1;)</a><br><br>
							<a class= "profile-text">is one of the treasurous in gomgam since he has professional analysis skills based on R selenium. His main major was Design. Amazing!</a>
						
						</div>

						<!-- Footer 3-->
						<div class="col-lg-422 mb-5 mb-lg-0">
							<a><img src="/pmkim/resources/images/linda.png" class="profile" alt="" /></a><br><br>
							<a href="https://github.com/GyuyoungEom" class="result-text">Linda Eom </a><br><br>
							<a  class="result-text">You know? I'm 팀장3;)</a><br><br>
							<a class= "profile-text">without her, this work could not be achieved... haha He is superwomen among us. Her diction is based on Austrailia, and she is familiar at super power on everything..!!!! </a>
						</div>

						<!-- Footer 4-->
						<div class="col-lg-422 mb-5 mb-lg-0">
							<a><img src="/pmkim/resources/images/oliver.png" class="profile"  /></a><br><br>
							<a href="https://github.com/SeongjinOliver" class="result-text">Oliver Yoo </a><br><br>
							<a class="result-text">You know? I'm 팀장1;)</a><br><br>
							<a class= "profile-text">is amazing man because he is familiar with back-frond end, DB, Spring, and Java, C... Cannot count all LOL. Surprise thing is he is even studying himself at every night despite project season :)</a>
							
							
						</div>
					</div>
				</div>


				<div class="row">
					<div class="col-lg-4 col-md-12 col-sm-12">
						<div class="footer-widget" ">
							<h4>About 편마 김편복</h4>
							<p class="result-text">편의점 마스터! 김편복
								데이터, 위치기반을 활용한 편의점 상품 추천 서비스</p>
							<p class="result-text">편의점에서 점심을 간단히 먹고 싶은 김편복씨는 자신의 위치에서 먹고자하는
								상품 어느 편의점에서 행사를 하는지 알고 싶은데 알 수 있는 방법이 없다!!
								이럴때 필요한 서비스는 "<b>편마 김편복</b>"</p>
						</div>
					</div>
					<div class="col-lg-4 col-md-12 col-sm-12">
						<div class="footer-link result-text">
							<h4>Information</h4>
								<a href="#/pmkim/home" >  HOME</a><br>
								<a href="#/pmkim/map">  지도</a><br>
								<a href="#/pmkim/analysis">  SNS 분석</a><br>
								<a href="#/pmkim/event">  행사</a><br>
								<a href="#/pmkim/cart">  만원의 행복</a><br>
								<a href="#/pmkim/cart">  편의점 뉴스</a>
						</div>
					</div>
					<div class="col-lg-4 col-md-12 col-sm-12">
						<div class="footer-link-contact result-text">
							<h4>Contact Us</h4>
							<ul>
								<li>
									<p>
										<i class="fas fa-map-marker-alt"></i>주소: 서울특별시 강남구 
										테헤란로 212 <br>(역삼동 718-5번지)<br> (우) 06220
									</p>
								</li>
								<li>
									<p>
										<i class="fas fa-phone-square"></i>Phone: <a
											href="tel:+81-1029852154">+82-10 2985 2154</a>
									</p>
								</li>
								<li>
									<p>
										<i class="fas fa-envelope"></i>Email: <a
											href="mailto:ohhojh@gmail.com">ohhojh@gmail.com</a>
									</p>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- End Footer  -->
	<!-- Start copyright  -->
	<div class="footer-copyright">
		<p class="footer-company">
			All Rights Jihye Oh. &copy; 2020 <a href="#">PM, KIM</a> Design By GomGam
		</p>
	</div>
	<!-- End copyright  -->

	<!-- Start copyright  -->
	<div class="footer-copyright">
		<p class="footer-company">
			© PMKIM CORP. ALL RIGHTS RESERVED &copy; 2020 <a href="#PMKIM">PMKIM</a>
		</p>
	</div>
	<!-- End copyright  -->

    <a href="#" id="back-to-top" title="Back to top" style="display: none;">&uarr;</a>

    <!-- ALL JS FILES -->
    <script src="/pmkim/resources/js/jquery-3.2.1.min.js"></script>
    <script src="/pmkim/resources/js/popper.min.js"></script>
    <script src="/pmkim/resources/js/bootstrap.min.js"></script>
    <!-- ALL PLUGINS -->
    <script src="/pmkim/resources/js/jquery.superslides.min.js"></script>
    <script src="/pmkim/resources/js/bootstrap-select.js"></script>
    <script src="/pmkim/resources/js/inewsticker.js"></script>
    <script src="/pmkim/resources/js/bootsnav.js"></script>
    <script src="/pmkim/resources/js//pmkim/resources/images-loded.min.js"></script>
    <script src="/pmkim/resources/js/isotope.min.js"></script>
    <script src="/pmkim/resources/js/owl.carousel.min.js"></script>
    <script src="/pmkim/resources/js/baguetteBox.min.js"></script>
    <script src="/pmkim/resources/js/form-validator.min.js"></script>
    <script src="/pmkim/resources/js/contact-form-script.js"></script>
    <script src="/pmkim/resources/js/custom.js"></script>
</body>

</html>