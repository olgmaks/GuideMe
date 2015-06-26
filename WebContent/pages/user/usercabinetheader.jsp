<nav class="white" role="navigation">
    <div class="nav-wrapper container">
        <ul>
            <li><a href= ${sessionUser==null ? "home.do" : "userCabinet.do"}>Guide ME</a></li>
        </ul>
        <a id="logo-container" href="home.do" class="brand-logo center">
            <div>
                <img src="icons/brandlabel2.png" style="width: 100px; height: 100px;">
            </div>
        </a>
        <ul class="right hide-on-med-and-down">
            <li><a class="modal-trigger" href=${sessionUser==null ? "#signInModal" : "#logoutModal"} id="signinlabel">
                ${sessionUser==null ? "Sign In" :  sessionUser.getEmail()=='' ? sessionUser.getFirstNameAndLastName()
					: sessionUser.getEmail()}
            </a></li>
        </ul>

    </div>
</nav>
