"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var LoginForm = function () { return (<form className="login-form">
    <input type="email" placeholder="Email" required/>
    <input type="password" placeholder="Password" required/>
    <div className="forgot-link">
      <a href="/forgot-password">Forgot Password?</a>
    </div>
    <button type="submit" className="login-btn">LOGIN</button>
  </form>); };
exports.default = LoginForm;
