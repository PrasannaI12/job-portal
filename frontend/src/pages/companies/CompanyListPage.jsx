import { useEffect, useState } from "react";
import CompanyCard from "../../components/CompanyCard";
import styles from "./CompanyListPage.module.css";
import api from "../../api/axios";
import axios from "axios";

const CompanyListPage = () => {
  const [companies, setCompanies] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const controller = new AbortController();

    const fetchCompanies = async () => {
      try {
        setLoading(true);
        const response = await api.get("/companies", {
          signal: controller.signal,
        });
        setCompanies(response.data);
      } catch (err) {
        if (!axios.isCancel(err)) {
          setError(err.response?.data?.message || err.message);
        }
      } finally {
        if (!controller.signal.aborted) {
          setLoading(false);
        }
      }
    };
    fetchCompanies();

    return () => controller.abort();
  }, []);

  if (loading) return <p>Loading companies...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <div className="container">
      <h1 className={styles.companyListingTitle}>
        Featured companies actively hiring
      </h1>
      <div className={styles.companyListing}>
        <h2 className={styles.subhead}>Showing {companies.length} companies</h2>
        <div className={styles.content}>
          {companies.map((company) => (
            <CompanyCard
              key={company.id}
              company={company}
              classes={{
                card: styles.listCard,
                img: styles.myImg,
                text: styles.myText,
              }}
            />
          ))}
        </div>
      </div>
    </div>
  );
};

export default CompanyListPage;
