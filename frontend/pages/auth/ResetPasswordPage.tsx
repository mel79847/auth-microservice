import SocialLogin from "../../src/components/SocialLogin";
import upbLogo from "../../public/upblogo.jpeg";
import figuraIzq from "../../src/assets/fotito.png";
import figuraDer from "../../src/assets/fotito.png";

export default function ResetPasswordPage({ position = "center" }) {
  return (
    <div className="login-background">
          <div className="login-layout two-columns">
            {position == "left" && (
              <div className="figure-col">
                <img src={figuraIzq} alt="Figura izquierda" />
              </div>
            )}
    
            <div className="form-col">
              <img src={upbLogo} alt="UPB Logo" className="logo" />
              <h2>Reset Password</h2>
    
              <form className="login-form">
                <input type="password" placeholder="Password" required />
                <input type="password" placeholder="Confirm Password" required />
                <button type="submit" className="login-btn">
                  Login
                </button>
              </form>
              <SocialLogin />
            </div>
    
            {position == "right" && (
              <div className="figure-col">
                <img src={figuraDer} alt="Figura derecha" />
              </div>
            )}
          </div>
        </div>
  )
}
