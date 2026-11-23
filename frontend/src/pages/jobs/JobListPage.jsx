import JobCard from "../../components/JobCard";
import styles from "./JobListPage.module.css";

const JobListPage = () => {
  return (
    <div className={styles.jobListingContainer}>
      <ul style={{ listStyle: "none" }}>
        <li className={styles.listItem}>
          <JobCard />
        </li>
        <li className={styles.listItem}>
          <JobCard />
        </li>
        <li className={styles.listItem}>
          <JobCard />
        </li>
        <li className={styles.listItem}>
          <JobCard />
        </li>
        <li className={styles.listItem}>
          <JobCard />
        </li>
        <li className={styles.listItem}>
          <JobCard />
        </li>
        <li className={styles.listItem}>
          <JobCard />
        </li>
        <li className={styles.listItem}>
          <JobCard />
        </li>
      </ul>
    </div>
  );
};

export default JobListPage;
