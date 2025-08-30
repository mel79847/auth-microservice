"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.default = ResetPasswordPage;
var SocialLogin_1 = require("../../components/SocialLogin");
var upblogo_jpeg_1 = require("/upblogo.jpeg");
var fotito_png_1 = require("../../assets/fotito.png");
var fotito_png_2 = require("../../assets/fotito.png");
function ResetPasswordPage(_a) {
    var _b = _a.position, position = _b === void 0 ? "center" : _b;
    return (<div className="login-background">
          <div className="login-layout two-columns">
            {position == "left" && (<div className="figure-col">
                <img src={fotito_png_1.default} alt="Figura izquierda"/>
              </div>)}
    
            <div className="form-col">
              <img src={upblogo_jpeg_1.default} alt="UPB Logo" className="logo"/>
              <h2>Reset Password</h2>
    
              <form className="login-form">
                <input type="password" placeholder="Password" required/>
                <input type="password" placeholder="Confirm Password" required/>
                <button type="submit" className="login-btn">
                  Login
                </button>
              </form>
              <SocialLogin_1.default />
            </div>
    
            {position == "right" && (<div className="figure-col">
                <img src={fotito_png_2.default} alt="Figura derecha"/>
              </div>)}
          </div>
        </div>);
}
