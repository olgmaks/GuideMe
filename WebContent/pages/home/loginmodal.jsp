<div id="signInModal" class="modal"
     style="margin-left: 60%; width: 35%;">
  <!-- Show this window when user not logged -->
  <form id="loginform">
    <div class="modal-content">
      <p style="margin-left: 40%;">Login form</p>

      <div class="row">
        <div class="row">
          <div class="input-field col s12">
            <input id="email" type="email" name="email" class="validate">
            <label for="email">Email</label>
          </div>
        </div>
        <div class="row">
          <div class="input-field col s12">
            <input id="password" type="password" name="password"
                   class="validate"> <label for="password">Password</label>
          </div>
        </div>
        <a href="register.do" style="margin-left: 5%;">Registrate your self</a>
        <br><font color="red" id="errorMessage" style="margin-left: 5%;"></font>
      </div>
    </div>
    <div class="modal-footer">
      <button class="light-blue btn waves-effect waves-light"
              type="submit" name="action" style="margin-right: 33%;">
        Submit <i class="mdi-content-send right"></i>
      </button>
    </div>
  </form>
</div>

