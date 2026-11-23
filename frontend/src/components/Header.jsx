import { Link, useLocation } from "react-router-dom";
import styles from "./Header.module.css";
import { useEffect, useState } from "react";

const Header = () => {
  const location = useLocation();
  const isHomePage = location.pathname === "/";

  const [isScrolled, setIsScrolled] = useState(false);

  useEffect(() => {
    if (isHomePage) {
      setIsScrolled(false);
      return;
    }
    const handleScroll = () => {
      setIsScrolled(window.scrollY > 0);
    };
    handleScroll();
    window.addEventListener("scroll", handleScroll, { passive: true });
    return () => window.removeEventListener("scroll", handleScroll);
  }, [isHomePage]);

  return (
    <header
      className={`d-flex align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom bg-white ${styles.myHeader} ${isHomePage ? "" : "sticky-top"} ${isScrolled ? styles.scrolled : ""}`}
    >
      <div className={`col-md-3 mb-2 mb-md-0 ${styles.logoDiv}`}>
        <Link to="/">
          <img
            src="src\assets\job-search.png"
            alt="job-portal logo"
            className={styles.logo}
          />
        </Link>
      </div>
      <ul
        className={`nav col-12 col-md-auto mb-2 justify-content-center mb-md-0 ${styles.myNav}`}
      >
        <li>
          <Link
            to="/jobs"
            className={`nav-link px-2 link-secondary ${styles.navLink}`}
          >
            <span className={styles.navLinks}>Jobs</span>
          </Link>
        </li>
        <li>
          <Link to="/companies" className={`nav-link px-2 ${styles.navLink}`}>
            <span className={styles.navLinks}>Companies</span>
          </Link>
        </li>
      </ul>
      <div className="col-md-3 text-end">
        <Link
          to="/login"
          className={`btn-outline theme-primary ${styles.loginBtn}`}
        >
          Login
        </Link>
        <Link
          to="/register"
          className={`btn-solid theme-danger ${styles.registerBtn}`}
        >
          Register
        </Link>
        <a href="#" className={styles.employers}>
          <span className={styles.employerLink}>For employers</span>
        </a>
      </div>
    </header>
  );
};

export default Header;
