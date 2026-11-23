import "bootstrap/dist/css/bootstrap.min.css";
import HomePage from "./pages/HomePage";
import RegisterPage from "./pages/auth/ResgisterPage";
import RegisterHeader from "./components/RegisterHeader";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { MainLayout } from "./components/MainLayout";
import LoginPage from "./pages/auth/LoginPage";
import Header from "./components/Header";
import CompanyListPage from "./pages/companies/CompanyListPage";
import JobListPage from "./pages/jobs/JobListPage";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route element={<MainLayout />}>
            <Route path="/" element={<HomePage />} />
            <Route path="/companies" element={<CompanyListPage />} />
            <Route path="/jobs" element={<JobListPage />} />
          </Route>
          <Route
            path="/register"
            element={
              <>
                <RegisterHeader />
                <RegisterPage />
              </>
            }
          />
          <Route
            path="/login"
            element={
              <>
                <Header />
                <LoginPage />
              </>
            }
          ></Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
