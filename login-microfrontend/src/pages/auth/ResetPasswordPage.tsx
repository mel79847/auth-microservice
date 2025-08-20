import AuthLayout from "./AuthLayout";
import SocialLogin from "../../components/SocialLogin";

export default function ResetPasswordPage() {
  return (
    <AuthLayout position="right">
      <h2>Reset Password</h2>

      <form className="login-form">
        <input type="password" placeholder="Password" required />
        <input type="password" placeholder="Confirm Password" required />
        <button type="submit" className="login-btn">
          Login
        </button>
      </form>

      <SocialLogin />
    </AuthLayout>
  );
}
