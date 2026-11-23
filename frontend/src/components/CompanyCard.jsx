import styles from "./CompanyCard.module.css";

const CompanyCard = ({ company, classes = {} }) => {
  return (
    <section className={`card ${styles["my-card"]} ${classes.card || ""}`}>
      <img
        src={company.image}
        className={`card-img-top ${styles["my-img"]} ${classes.img || ""}`}
        alt={`${company.title} logo`}
      />
      <div className={`card-body ${styles["my-body"]}`}>
        <h4 className={`card-title ${styles["my-title"]}`}>
          {company.companyName}
        </h4>
        <p className={`card-text ${styles["my-text"]} ${classes.text || ""}`}>
          {company.companyOverview}
        </p>
        <button
          type="button"
          className={`btn-subtle theme-primary ${styles["jobs-btn"]}`}
        >
          View jobs
        </button>
      </div>
    </section>
  );
};

export default CompanyCard;
