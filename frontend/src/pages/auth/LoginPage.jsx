import { useState } from "react";
import styles1 from "./LoginPage.module.css";
import styles from "./RegisterPage.module.css";

const LoginPage = () => {
  const [state, setState] = useState("hide");

  const handleToggle = (state) => {
    return state === "hide" ? "show" : "hide";
  };

  return (
    <div className={styles1.loginContainer}>
      <div className={styles1.loginWrapper}>
        <h1 className={styles1.loginTitle}>Login</h1>
        <form action="">
          <div className={`form-field ${styles.myField}`}>
            <label htmlFor="emailText" className="form-label">
              Email ID
            </label>
            <input
              type="email"
              maxLength={100}
              className={`form-control ${styles.input}`}
              id="emailText"
              placeholder="Enter your active Email ID"
            />
          </div>
          <div className={`form-field ${styles.myField}`}>
            <label htmlFor="passwordText" className="form-label">
              Password
            </label>
            <input
              type={state === "hide" ? "password" : "text"}
              maxLength={40}
              className={`form-control ${styles.input}`}
              id="passwordText"
              placeholder="Enter your password"
            />

            <span
              className={styles1.showhide}
              role="button"
              onClick={() => {
                setState(handleToggle(state));
              }}
            >
              <small className={styles1.toggle}>
                {state === "hide" ? "Show" : "Hide"}
              </small>
            </span>
          </div>
          <button
            type="button"
            className={`btn-solid theme-primary ${styles1.loginBtn}`}
          >
            Login
          </button>
        </form>
      </div>
    </div>
  );
};

export default LoginPage;
