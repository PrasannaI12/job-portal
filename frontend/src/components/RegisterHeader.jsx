import { Link } from "react-router-dom";
import styles from "./Header.module.css";
import styles1 from "./RegisterHeader.module.css";

const RegisterHeader = () => {
  return (
    <header
      className={`d-flex align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom ${styles.myHeader}`}
    >
      <div className={styles1.content}>
        <div className={`col-md-3 mb-2 mb-md-0 ${styles.logoDiv}`}>
          <Link to="/">
            <img
              src="src\assets\job-search.png"
              alt="job-portal logo"
              className={styles.logo}
            />
          </Link>
        </div>
        <span className={styles1.loginText}>
          Already Registered?{" "}
          <Link to="/login" className={styles1.loginLink}>
            Login
          </Link>{" "}
          here
        </span>
      </div>
    </header>
  );
};

export default RegisterHeader;
