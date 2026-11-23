import styles from "./JobCard.module.css";
import { MdOutlineWorkOutline } from "react-icons/md";
import { GrLocation } from "react-icons/gr";
import { CgNotes } from "react-icons/cg";
import { FaRegBookmark } from "react-icons/fa";

const JobCard = () => {
  return (
    <div className={styles.layoutWrapper}>
      <div className={styles.row1}>
        <h2>
          <a href="#" className={styles.title}>
            Financial Controller
          </a>
        </h2>
        <span className={styles.imagewrap}>
          <img
            src="src/assets/Google__G__logo.svg"
            alt="Google logo"
            className={styles.logoImage}
          />
        </span>
      </div>
      <div className={styles.row2}>
        <a href="#" className={styles.compName}>
          Capgemini
        </a>
      </div>
      <div className={styles.row3}>
        <span className={styles.expWrap}>
          <span className={styles.expIcon}>
            <MdOutlineWorkOutline />
          </span>
          <span title="4-9 Yrs" className={styles.expwdth}>
            4-9 Yrs
          </span>
        </span>
        <span className={styles.locWrap}>
          <span className={styles.locIcon}>
            <GrLocation />
          </span>
          <span
            title="Hybrid - Pune, Mumbai (All Areas)"
            className={styles.locWdth}
          >
            Hybrid - Pune, Mumbai (All Areas)
          </span>
        </span>
      </div>
      <div className={styles.row4}>
        <span className={styles.jobDesc}>
          <span className={styles.descIcon}>
            <CgNotes />
          </span>
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Est, soluta?
          Eos, nam aut placeat saepe eligendi ipsa alias porro qui nostrum.
          Voluptatum perspiciatis quisquam voluptatem deleniti nihil nesciunt
          ipsum quidem.
        </span>
      </div>
      <div className={styles.row5}>
        <ul className={styles.tags}>
          <li className={styles.tagLi}>Forecasting</li>
          <li className={styles.tagLi}>Forecasting</li>
          <li className={styles.tagLi}>Forecasting</li>
          <li className={styles.tagLi}>Forecasting</li>
          <li className={styles.tagLi}>Forecasting</li>
        </ul>
      </div>
      <div className={styles.row6}>
        <span className={styles.jobPostDay}>3 days ago</span>
        <span className={styles.saveJobTag} role="button">
          <FaRegBookmark className={styles.saveIcon} />
          Save
        </span>
      </div>
    </div>
  );
};

export default JobCard;
