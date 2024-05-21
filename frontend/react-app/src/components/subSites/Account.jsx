import React from 'react';
import Navbar from "../Navbar";
import ChangeAvatar from "../ChangeAvatar";
import ChangePassword from "../ChangePassword";
import BlueButton from "../BlueButton";
import styles from '../../css/sub_sites_css/Account.module.css';
import AuthService from "../../service/auth.service";
import { useNavigate } from 'react-router-dom';


function Account() {
    let navigate = useNavigate();
    const handleLogout = () => {
        AuthService.logout();
        navigate('/login');
    }

    return (
          <>
              <Navbar />
              <div className={styles.logout_button} >
                <BlueButton text={"Logout"} onClick={handleLogout}/>
              </div>
              <div className={styles.content}>
                <ChangeAvatar />
                <ChangePassword />
              </div>
      </>
  );
}

export default Account;