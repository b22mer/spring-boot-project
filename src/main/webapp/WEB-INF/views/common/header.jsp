<header id="header" class="d-flex align-items-center">
    <div class="container d-flex align-items-center justify-content-between">
        <h1 class="logo">
            <a class="nav-link scrollto" href="/">whereIsMyHome<span>.</span></a>
        </h1>

        <!--2-1. í¤ë ë©ë´ì°½ -->
        <nav id="navbar" class="navbar">
            <ul>
                <li><a lass="nav-link scrollto" href="${root}/board/upload">files</a></li>
                <li><a class="nav-link scrollto" href="deal.html">Deal Info</a></li>
                <li><a class="nav-link scrollto" href="#portfolio">Company</a></li>
                <c:if test="${member eq null}">
                    <li><a class="nav-link scrollto" href="${root}/user/login"
                           onclick="window.open(this.href, '_blank', 'width=600 height=800'); return false">Login</a>

                    <li>
                        <a class="nav-link scrollto" href="${root}/user/register"
                           onclick="window.open(this.href, '_blank', 'width=600 height=800'); return false">Register
                        </a>
                    </li>

                </c:if>
                <c:if test="${member ne null}">
                    <li><a class="nav-link scrollto" href="/user/logout">Logout</a></li>
                    <li><a class="nav-link scrollto" href="/board/selectall">Board</a></li>
                    <li><a class="nav-link scrollto" href="/board/list.html">Setting</a></li>
                    <i class="bi bi-chevron-down">
                        <li class="dropdown">

                            <ul>
                                <li><a href="#">ss</a></li>
                                <li><a href="#">ss</a></li>
                                <li><a href="#">ss</a></li>
                            </ul>
                        </li>
                    </i>

                </c:if>
            </ul>
            <i class="bi bi-list mobile-nav-toggle"></i>
        </nav>
    </div>
</header>
