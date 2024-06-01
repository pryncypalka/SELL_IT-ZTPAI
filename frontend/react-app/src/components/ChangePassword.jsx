import React, { useState } from 'react';
import axios from 'axios';
import authHeader from '../service/auth-header'; // Import authHeader function
import styles from '../css/ChangePassword.module.css'
import BlueButton from "./BlueButton";

function ChangePassword() {
    const [oldPassword, setOldPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [newPasswordRepeat, setNewPasswordRepeat] = useState('');
    const [message, setMessage] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();

        if (newPassword !== newPasswordRepeat) {
            setMessage("New passwords do not match");
            return;
        }

        try {
            await axios.put('http://localhost:8080/api/user/password', {
                oldPassword,
                newPassword,
            }, {
                headers: authHeader()
            });

            setMessage("Password changed successfully");
        } catch (error) {
            if (error.response && error.response.data && error.response.data.message) {
                setMessage(error.response.data.message);
            } else {
                setMessage("Error changing password");
            }
        }
    };

    return (
        <form className={styles.change_password_form} onSubmit={handleSubmit}>
            <p className="Change_password">Change password</p>

            <input className={styles.input_field} type="password" name="old_password" placeholder="Old password" value={oldPassword} onChange={e => setOldPassword(e.target.value)}/>
            <input className={styles.input_field} type="password" name="new_password" placeholder="New password" value={newPassword} onChange={e => setNewPassword(e.target.value)}/>
            <input className={styles.input_field} type="password" name="new_password_repeat" placeholder="Repeat new password" value={newPasswordRepeat} onChange={e => setNewPasswordRepeat(e.target.value)}/>
            <BlueButton text="Change password" onClick={handleSubmit}/>
            {message && <p>{message}</p>}
        </form>
    );
}

export default ChangePassword;