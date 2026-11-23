import styles from "./RegisterPage.module.css";

const RegisterPage = () => {
  return (
    <div className={styles.container}>
      <div className={styles.registerWrapper}>
        <h1 className={styles.globalTitle}>Create your job profile</h1>
        <p className={styles.headingHelp}>
          Search & apply to jobs from India's No.1 Job Site
        </p>
        <form action="">
          <div className="form-field">
            <label
              htmlFor="fieldText"
              className={`form-label ${styles.required}`}
            >
              Full name
            </label>
            <input
              type="text"
              maxLength={35}
              className={`form-control ${styles.input}`}
              id="fieldText"
              placeholder="What is your name?"
            />
          </div>
          <div className={`form-field ${styles.myField}`}>
            <label
              htmlFor="fieldText"
              className={`form-label ${styles.required}`}
            >
              Email address
            </label>
            <input
              type="email"
              maxLength={100}
              className={`form-control ${styles.input}`}
              id="fieldText"
              placeholder="Tell us your Email ID"
            />
            <div className="form-text">
              We’ll never share your email with anyone else
            </div>
          </div>
          <div className={`form-field ${styles.myField}`}>
            <label
              htmlFor="fieldText"
              className={`form-label ${styles.required}`}
            >
              Password
            </label>
            <input
              type="password"
              maxLength={40}
              className={`form-control ${styles.input}`}
              id="fieldText"
              placeholder="(Minimum 6 characters)"
            />
            <div className="form-text">
              This helps your account stay protected
            </div>
          </div>
          <div className={`form-field ${styles.myField}`}>
            <label
              htmlFor="fieldText"
              className={`form-label ${styles.required}`}
            >
              Mobile number
            </label>
            <input
              type="tel"
              maxLength={10}
              className={`form-control ${styles.input}`}
              id="fieldText"
              placeholder="Enter your mobile number"
            />
            <div className="form-text">
              Recruiters will contact you on this number
            </div>
          </div>
          <p className={styles.confirmText}>
            By clicking register, you agree to the{" "}
            <a href="#" className={styles.terms}>
              Terms and Conditions
            </a>
            &
            <a href="#" className={styles.terms}>
              Privacy Policy
            </a>
          </p>
          <button
            type="button"
            className={`btn-solid theme-primary ${styles.registerBtn}`}
            disabled
          >
            Register now
          </button>
        </form>
      </div>
    </div>
  );
};

export default RegisterPage;
