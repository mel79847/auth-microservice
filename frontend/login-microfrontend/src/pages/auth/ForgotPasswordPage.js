"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.default = ForgotPasswordPage;
var react_router_dom_1 = require("react-router-dom");
var SocialLogin_1 = require("../../components/SocialLogin");
var upblogo_jpeg_1 = require("/upblogo.jpeg");
var fotito_png_1 = require("../../assets/fotito.png");
var fotito_png_2 = require("../../assets/fotito.png");
function ForgotPasswordPage(_a) {
    var _b = _a.position, position = _b === void 0 ? "center" : _b;
    var navigate = (0, react_router_dom_1.useNavigate)();
    return (<div className="login-background">
      <div className="login-layout two-columns">
        {position == "left" && (<div className="figure-col">
            <img src={fotito_png_1.default} alt="Figura izquierda"/>
          </div>)}

        <div className="form-col">
          <img src={upblogo_jpeg_1.default} alt="UPB Logo" className="logo"/>
          <h2>Forgot Password</h2>

          <form className="login-form">
            <input type="email" placeholder="Username o Email" required/>
            <button type="submit" className="login-btn">
              Next
            </button>
            <div className="forgot-link">
              <span onClick={function () {
            navigate("/login");
        }}>
                Back to Login?
              </span>
            </div>
          </form>
          <SocialLogin_1.default />
        </div>

        {position == "right" && (<div className="figure-col">
            <img src={fotito_png_2.default} alt="Figura derecha"/>
          </div>)}
      </div>
    </div>);
}
