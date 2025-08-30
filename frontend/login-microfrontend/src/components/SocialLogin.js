"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var fa_1 = require("react-icons/fa");
var SocialLogin = function () { return (<div className="social-login">
    <span className="social-text">Or login with</span>
    <div className="social-buttons">
      <button className="social-btn google">
        <fa_1.FaGoogle /> Google
      </button>
      <button className="social-btn facebook">
        <fa_1.FaFacebook /> Facebook
      </button>
    </div>
  </div>); };
exports.default = SocialLogin;
