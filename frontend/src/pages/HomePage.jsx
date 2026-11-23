import { IoSearchOutline } from "react-icons/io5";
import CompanyCard from "../components/CompanyCard";
import styles from "./HomePage.module.css";
import { Link } from "react-router-dom";

const HomePage = () => {
  const companies = [
    {
      image: "src/assets/Amazon_logo.png",
      title: "Amazon",
      text: "World's largest internet company.",
    },
    {
      image: "src/assets/acn_logo.jpg",
      title: "Accenture",
      text: "Global leader in technology services.",
    },
    {
      image: "src/assets/Google__G__logo.svg",
      title: "Google",
      text: "Trusted global solutions company.",
    },
    {
      image: "src/assets/1280px-Infosys_logo.svg.webp",
      title: "Infosys",
      text: "Global leader in next-gen digital services & consulting.",
    },
  ];

  return (
    <>
      <div className={styles.headerContainer}>
        <h1 className={styles.mainTitle}>Find your dream job now</h1>
        <p id={styles.line}>5 lakh+ jobs for you to explore</p>
      </div>
      <div className={styles.wrapper}>
        <div className={styles["search-bar"]}>
          <IoSearchOutline />
          <input
            type="text"
            placeholder="Enter skills / designations / companies"
            className={styles["input-box"]}
          />
          <button
            type="button"
            className={`btn-solid theme-primary ${styles["search-btn"]}`}
          >
            Search
          </button>
        </div>
      </div>
      <h2 className={styles.headline}>Top companies hiring now</h2>
      <div className={styles["card-container"]}>
        {companies.map((company) => (
          <CompanyCard key={company.title} company={company}></CompanyCard>
        ))}
      </div>
      <div id={styles["btn-container"]}>
        <Link
          to="/companies"
          className={`btn-outline theme-primary ${styles["companies-btn"]}`}
        >
          View all companies
        </Link>
      </div>
    </>
  );
};

export default HomePage;
