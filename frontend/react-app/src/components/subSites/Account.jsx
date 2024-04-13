import React from 'react';
import Navbar from "../Navbar";
import ChangeAvatar from "../ChangeAvatar";
import ChangePassword from "../ChangePassword";
import BlueButton from "../BlueButton";
import styles from '../../css/sub_sites_css/Account.module.css';


function Account() {
    return (
          <>
              <Navbar />
              <div className={styles.logout_button} >
                <BlueButton text={"Logout"}/>
              </div>
              <div className={styles.content}>
                <ChangeAvatar />
                <ChangePassword />
              </div>
      </>
  );
}

export default Account;