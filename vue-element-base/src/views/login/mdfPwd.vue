<template>
  <div class="login-container">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="rules"
      class="login-form"
      autocomplete="on"
      label-position="left"
    >
      <div class="title-container">
        <h3 class="title">修改密码</h3>
      </div>
      <div class="title-container">
        <h3 class="title">请输入您的工号与原密码</h3>
      </div>
      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="工号"
          name="username"
          type="text"
          tabindex="1"
          autocomplete="on"
        />
      </el-form-item>

      <el-tooltip
        v-model="capsTooltip"
        content="Caps lock is On"
        placement="right"
        manual
      >
        <el-form-item prop="password">
          <span class="svg-container">
            <svg-icon icon-class="password" />
          </span>
          <el-input
            :key="passwordType"
            ref="password"
            v-model="loginForm.password"
            :type="passwordType"
            placeholder="原密码"
            name="password"
            tabindex="2"
            autocomplete="on"
            @keyup.native="checkCapslock"
            @blur="capsTooltip = false"
          />
          <span class="show-pwd" @click="showPwd">
            <svg-icon
              :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'"
            />
          </span>
        </el-form-item>
      </el-tooltip>

      <div class="title-container">
        <h3 class="title">请输入您的新密码</h3>
      </div>

      <el-tooltip
        v-model="capsTooltip"
        content="Caps lock is On"
        placement="right"
        manual
      >
        <el-form-item prop="newPwd">
          <span class="svg-container">
            <svg-icon icon-class="password" />
          </span>
          <el-input
            :key="passwordType"
            ref="password"
            v-model="loginForm.newPwd"
            :type="passwordType"
            placeholder="新密码"
            name="password"
            tabindex="2"
            autocomplete="on"
            @keyup.native="checkCapslock"
            @blur="capsTooltip = false"
            @keyup.enter.native="handleMdfPwd"
          />
          <span class="show-pwd" @click="showPwd">
            <svg-icon
              :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'"
            />
          </span>
        </el-form-item>
      </el-tooltip>
      <h4 style="color: white">密码安全等级：</h4>
      <el-rate
        v-model="level"
        disabled
        show-score
        text-color="#ffffff"
        score-template="{value}"
        style="margin: -30px auto auto 150px"
      >
      </el-rate>

      <el-button
        :loading="loading"
        type="primary"
        style="width: 100%; margin-bottom: 30px"
        @click.native.prevent="handleMdfPwd"
        >修改密码</el-button
      >

      <div style="position: relative">
        <div class="tips">
          <span>用户名：系统分配</span>
        </div>
        <div class="tips">
          <span>密码长度应大于6位,且至少包含一个字符</span>
        </div>
        <el-button
          class="thirdparty-button"
          type="primary"
          @click="handleRet()"
        >
          返回登录
        </el-button>
      </div>
    </el-form>

    <el-dialog title="Or connect with" :visible.sync="showDialog">
      Can not be simulated on local, so please combine you own business
      simulation! ! !
      <br />
      <br />
      <br />
      <social-sign />
    </el-dialog>
  </div>
</template>

<script>
import SocialSign from "./components/SocialSignin";
import { mdfPwd } from "@/api/user";

export default {
  name: "Login",
  components: { SocialSign },
  data() {
    // const validateUsername = (rule, value, callback) => {
    //   if (!validUsername(value)) {
    //     callback(new Error("Please enter the correct user name"));
    //   } else {
    //     callback();
    //   }
    // };
    // const validatePassword = (rule, value, callback) => {
    //   if (value.length < 6) {
    //     callback(new Error("The password can not be less than 6 digits"));
    //   } else {
    //     callback();
    //   }
    // };
    var validatePass = (rule, value, callback) => {
      if (!(value.length > 6)) {
        callback(new Error("密码长度未超过六位"));
      } else if (!/[a-zA-Z]/.test(value)) {
        callback(new Error("密码未包含字母"));
      } else if (!/[!,@,#,$,%,^,&,*,?,_,~,-,(,)]/.test(value)) {
        callback(new Error("建议包含特殊字符，可忽略"));
      } else {
        callback();
      }
      this.level =
        0 +
        //密码长度大于6位
        (value.length > 6) +
        //密码含有字母
        /[a-zA-Z]/.test(value) +
        //密码含有字母和数字
        /\d/.test(value) +
        //密码含有特殊字符
        /[!,@,#,$,%,^,&,*,?,_,~,-,(,)]/.test(value) +
        //密码长度大于12位
        (value.length > 12);
    };
    return {
      loginForm: {
        username: "admin",
        password: "111111",
        newPwd: "123456",
      },
      // loginRules: {
      //   username: [
      //     { required: true, trigger: "blur", validator: validateUsername },
      //   ],
      //   password: [
      //     { required: true, trigger: "blur", validator: validatePassword },
      //   ],
      // },
      rules: {
        newPwd: [{ validator: validatePass, trigger: "blur" }],
      },
      passwordType: "password",
      capsTooltip: false,
      loading: false,
      showDialog: false,
      redirect: undefined,
      otherQuery: {},
      level: 0,
    };
  },
  watch: {
    $route: {
      handler: function (route) {
        const query = route.query;
        if (query) {
          this.redirect = query.redirect;
          this.otherQuery = this.getOtherQuery(query);
        }
      },
      immediate: true,
    },
  },
  created() {
    // window.addEventListener('storage', this.afterQRScan)
  },
  mounted() {
    if (this.loginForm.username === "") {
      this.$refs.username.focus();
    } else if (this.loginForm.password === "") {
      this.$refs.password.focus();
    }
  },
  destroyed() {
    // window.removeEventListener('storage', this.afterQRScan)
  },
  methods: {
    checkCapslock(e) {
      const { key } = e;
      this.capsTooltip = key && key.length === 1 && key >= "A" && key <= "Z";
    },
    showPwd() {
      if (this.passwordType === "password") {
        this.passwordType = "";
      } else {
        this.passwordType = "password";
      }
      this.$nextTick(() => {
        this.$refs.password.focus();
      });
    },
    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          this.$store
            .dispatch("user/login", this.loginForm)
            .then(() => {
              this.$router.push({
                path: this.redirect || "/",
                query: this.otherQuery,
              });
              this.loading = false;
            })
            .catch(() => {
              this.loading = false;
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    handleMdfPwd() {
      this.$confirm("此操作将修改您的密码, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          var password = this.loginForm.newPwd;
          this.level =
            0 +
            //密码长度大于6位
            (password.length > 6) +
            //密码含有字母
            /[a-zA-Z]/.test(password) +
            //密码含有字母和数字
            /\d/.test(password) +
            //密码含有特殊字符
            /[!,@,#,$,%,^,&,*,?,_,~,-,(,)]/.test(password) +
            //密码长度大于12位
            (password.length > 12);
          if (this.level < 3) {
            this.$message({
              showClose: true,
              message: "密码安全等级过低，请重新设置！",
              type: "warning",
            });
          } else {
            mdfPwd(
              this.loginForm.username,
              this.loginForm.password,
              this.loginForm.newPwd
            ).then((res) => {
              if (res.success == true) {
                this.$message({
                  type: "success",
                  message: "修改成功!",
                });
              } else {
                this.$message({
                  type: "error",
                  message: "账号密码错误!",
                });
              }
            });
          }
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消修改",
          });
        });
    },
    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== "redirect") {
          acc[cur] = query[cur];
        }
        return acc;
      }, {});
    },
    handleRet() {
      this.$router.push("/login");
    },
    // afterQRScan() {
    //   if (e.key === 'x-admin-oauth-code') {
    //     const code = getQueryObject(e.newValue)
    //     const codeMap = {
    //       wechat: 'code',
    //       tencent: 'code'
    //     }
    //     const type = codeMap[this.auth_type]
    //     const codeName = code[type]
    //     if (codeName) {
    //       this.$store.dispatch('LoginByThirdparty', codeName).then(() => {
    //         this.$router.push({ path: this.redirect || '/' })
    //       })
    //     } else {
    //       alert('第三方登录失败')
    //     }
    //   }
    // }
  },
};
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #283443;
$light_gray: #fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: -100px auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  .thirdparty-button {
    position: absolute;
    right: 0;
    bottom: 6px;
  }

  @media only screen and (max-width: 470px) {
    .thirdparty-button {
      display: none;
    }
  }
}
</style>
