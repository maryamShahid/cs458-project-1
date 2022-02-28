<template>
  <div class="card">
    <img src="./../assets/netflix-icon.svg" alt="" style="float: left; margin-left: 50px">
    <div class="outer">
      <div class="middle">
        <div class="inner">
          <div class="login-page__start_form">
            <div class="login-page__start_form_input">
              <p>Sign In</p>
              <div v-if="errorMessage" class="error"> {{ errorMessage }}</div>
              <div :class="{'login-input-error': usernameError}">
                <input v-model="username" @click="usernameError = false" placeholder="Email or phone number" @keyup.enter="register">
              </div>
              <div v-if="usernameError" class="error-text">Please enter a valid email or phone number.</div>
              <div class="login-page__start_form_input">
                <div style="position: relative">
                  <div :class="{'login-input-error': passwordError}" style="margin-top: 20px">
                    <input v-model="password" :type="hidden ? 'password' : 'text'" class="loginInput" @click="passwordError = false" placeholder="Password" @keyup.enter="register">
                  </div>
                </div>
                <div v-if="passwordError" class="error-text">Your password must contain between 4 and 60 characters.</div>
              </div>
              <button class="loginBtn" @click="register">Sign In</button>
              <div class="description" style="font-size: 20px; margin-bottom: -60px">
                <img alt="" src="./../assets/fb-icon.svg" style="margin-bottom: -16px">
                Login with Facebook
              </div>
              <div class="description">New to Netflix? Sign up now.</div>
              <div class="description-2">This page is protected by Google reCAPTCHA to ensure you're not a bot. Learn more.</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SignIn',
  components: {},
  data() {
    return {
      username: '',
      password: '',
      acceptedUsers: ['maryam', 'maryam.shahid@ug.bilkent.edu.tr', '05527856126'],
      acceptedPasswords: ['1234', 'jem123'],
      errorMessage: '',
      usernameError: '',
      passwordError: '',
      hidden: true,
      success:false
    }
  },
  methods: {
    register() {
      console.log(this.username)
      this.usernameError = this.username === ''
      this.passwordError = this.password === ''
      var correctPass
      var correctUser
      if (this.acceptedPasswords.indexOf(this.password) > -1) {
        correctPass = true
      } else if (this.password !== '') {
        this.errorMessage = 'Incorrect password. Please try again or you can reset your password.'
      }
      if (this.acceptedUsers.indexOf(this.username) > -1) {
        correctUser = true
      } else if (this.username !== '') {
        this.errorMessage = 'Sorry, we can\'t find an account with this email address. Please try again or create a new account.'
      }
      if (correctPass && correctUser) {
        this.errorMessage = ''
        this.$router.push('/success')
      }
    }
  }
}
</script>

<style>
.card {
  background-image: url("./../assets/bg.jpg");
  min-width: 100%;
  min-height: 100%;
  position: fixed;
  top: 0;
  left: 0;
}
.outer {
  display: table;
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
}

.middle {
  display: table-cell;
  vertical-align: middle;
}

.inner {
  background: rgba(0, 0, 0, 0.78);
  margin-left: auto;
  margin-right: auto;
  width: 700px;
  border-radius: 10px;
}
.login-page__start_form_input{
  margin-bottom: 30px;
}
.login-page__start_form_input p{
  margin-left: 90px;
  padding-top: 80px;
  padding-bottom: 20px;
  font-size: 52px;
  line-height: 68px;
  color: white;
  font-weight: bolder;
  text-align: left;
}
.login-page__start_form_input input{
  width: 60%;
  background: #424040;
  border-radius: 10px;
  border:4px solid #424040;
  padding: 10px 15px;
  font-size: 22px;
  line-height: 40px;
  color:white;
  outline: none;
  transition:.3s ease-in-out;
  margin-left: -40px;
}
.login-input-error p{
  margin:0 2px 0 0;
  font-size: 12px;
  line-height: 18px;
  color:#383838;
  text-align: left;
}
.login-input-error input{
  width: 60%;
  background: #424040;
  border-radius: 10px;
  border-bottom:4px solid #e5872b;
  padding: 10px 15px;
  font-size: 22px;
  line-height: 40px;
  color:white;
  outline: none;
  transition:.3s ease-in-out;
  margin-left: -40px;
}
.login-input-error input:focus{
  border-color:#0681C5;
}
.login-page__start_form button{
  font-size: 20px;
  line-height: 40px;
  font-weight: bold;
  color:#ffffff;
  background: #db0707;
  border-radius: 10px;
  border:none;
  display: block;
  width: 65%;
  padding: 13px 10px;
  margin-left: 100px;
}
.description {
  margin-left: 90px;
  padding-top: 80px;
  font-size: 24px;
  line-height: 48px;
  color: #8e8b8b;
  font-weight: normal;
  text-align: left;
  margin-bottom: -10px;
}
.description-2 {
  margin-left: 90px;
  margin-right: 70px;
  padding-top: 20px;
  padding-bottom: 80px;
  font-size: 18px;
  line-height: 48px;
  color: #8e8b8b;
  font-weight: normal;
  text-align: left;
}
.error-text{
  color: darkorange;
  font-size: 20px;
  line-height: 22px;
  text-align: left;
  width: 100%;
  margin-top: 15px;
  margin-left: 105px;
}
.error-text.cnt{
  text-align: center;
}
.error{
  margin-left: 105px;
  margin-right: 145px;
  margin-bottom: 30px;
  padding: 10px 30px;
  font-size: 20px;
  color: #ffffff;
  font-weight: bold;
  text-align: left;
  background-color: darkorange;
  border-radius: 10px;
}
</style>

