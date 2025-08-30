"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var LoginForm_1 = require("./LoginForm");
var SocialLogin_1 = require("./SocialLogin");
var upblogo_jpeg_1 = require("/upblogo.jpeg");
var fotito_png_1 = require("../assets/fotito.png");
var fotito_png_2 = require("../assets/fotito.png");
var Login = function (_a) {
    var _b = _a.mode, mode = _b === void 0 ? "center" : _b;
    var renderContent = function () {
        switch (mode) {
            case "left-figure":
                return (<div className="login-layout two-columns">
            <div className="figure-col">
              <img src={fotito_png_1.default} alt="Figura izquierda"/>
            </div>
            <div className="form-col">
              <img src={upblogo_jpeg_1.default} alt="UPB Logo" className="logo"/>
              <h2>Login</h2>
              <LoginForm_1.default />
              <SocialLogin_1.default />
            </div>
          </div>);
            case "right-figure":
                return (<div className="login-layout two-columns">
            <div className="form-col">
              <img src={upblogo_jpeg_1.default} alt="UPB Logo" className="logo"/>
              <h2>Login</h2>
              <LoginForm_1.default />
              <SocialLogin_1.default />
            </div>
            <div className="figure-col">
              <img src={fotito_png_2.default} alt="Figura derecha"/>
            </div>
          </div>);
            default:
                return (<div className="login-layout center">
            <img src={upblogo_jpeg_1.default} alt="UPB Logo" className="logo"/>
            <h2>Login</h2>
            <LoginForm_1.default />
            <SocialLogin_1.default />
          </div>);
        }
    };
    return <div className="login-background">{renderContent()}</div>;
};
exports.default = Login;
