<header id="header" class="d-flex align-items-center">
    <div class="container d-flex align-items-center justify-content-between">
        <h1 class="logo">
            <a href="/">whereIsMyHome<span>.</span></a>
        </h1>

        <!--2-1. 헤더 메뉴창 -->
        <nav id="navbar" class="navbar">
            <ul>
                <li><a class="nav-link scrollto" href="deal.html">Deal Info</a></li>
                <li><a class="nav-link scrollto" href="#portfolio">Company</a></li>
                <c:if test="${member eq null}">
                    <li><a class="nav-link scrollto" href="${root}/user/login"
                           onclick="window.open(this.href, '_blank', 'width=600 height=800'); return false">Login</a>
                    </li>
                    <li>
                        <a href="${root}/user/register"
                           onclick="window.open(this.href, '_blank', 'width=600 height=800'); return false"><span>Register</span>
                            <i class="bi bi-chevron-down"></i></a>
                    </li>

                </c:if>
                <c:if test="${member ne null}">
                    <li><a class="nav-link scrollto" href="#team">Logout</a></li>
                    <li><a class="nav-link scrollto" href="/board/list.html">Board</a></li>
                    <li class="dropdown">
                        <span>관리창</span>
                        <ul>
                            <li><a href="#">회원정보 삭제</a></li>
                            <li><a href="#">회원정보 수정</a></li>
                            <li><a href="#">회원정보 검색</a></li>
                        </ul>
                    </li>
                </c:if>



            </ul>
            <i class="bi bi-list mobile-nav-toggle"></i>
        </nav>
        <!--2-1. 헤더 메뉴창 end -->
    </div>
</header>