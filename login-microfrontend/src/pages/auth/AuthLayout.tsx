import upbLogo from "/upblogo.jpeg";
import figuraIzq from "../assets/fotito.png";
import figuraDer from "../assets/fotito.png";

interface AuthLayoutProps {
  position?: "left" | "right" | "center";
  children: React.ReactNode;
}

export default function AuthLayout({ position = "center", children }: AuthLayoutProps) {
  return (
    <div className="login-background">
      <div className="login-layout two-columns">
        {position === "left" && (
          <div className="figure-col">
            <img src={figuraIzq} alt="Figura izquierda" />
          </div>
        )}

        <div className="form-col">
          <img src={upbLogo} alt="UPB Logo" className="logo" />
          {children}
        </div>

        {position === "right" && (
          <div className="figure-col">
            <img src={figuraDer} alt="Figura derecha" />
          </div>
        )}
      </div>
    </div>
  );
}
