import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import AuthService from "../service/auth.service";
import { isEmail } from "validator";
import styles from '../css/SignUpForm.module.css';
import axios from 'axios';

const required = (value) => {
    if (!value) {
        return (
            <div className="" role="alert">
                This field is required!
            </div>
        );
    }
};

const SignUpForm = () => {
    let navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [username, setUsername] = useState('');
    const [password2, setPassword2] = useState('');
    const [message, setMessage] = useState();

    const handleSubmit = async (e) => {
        e.preventDefault();

        setMessage("");

        if (password !== password2) {
            setMessage('Passwords do not match');
            return;
        }

        if (isEmail(email)) {
            try {
                const response = await AuthService.register(username, email, password);

                if (response.status >= 200 && response.status < 300) {
                    setMessage('Registration successful');
                    navigate("/login");
                } else {
                    setMessage(typeof response.data === 'object' ? response.data.error : response.data);
                }
            } catch (error) {
                setMessage(error.message);
            }
        } else {
            setMessage("Invalid email format");
        }
    };

    return (
        <form className={styles.form_sign_up} onSubmit={handleSubmit}>
            <div className={styles.text1}>Sign up</div>

            <div>{message}</div>
            <div className={styles.field_name}>Your Username</div>
            <div className={styles.input_field}>
                <input className={styles.input_field}
                       type="text"
                       name="username"
                       value={username}
                       onChange={(e) => setUsername(e.target.value)}
                       required
                />

            </div>

            <div className={styles.field_name}>Your Email</div>
            <div className={styles.input_field}>
                <input className={styles.input_field}
                       type="text"
                       name="email"
                       value={email}
                       onChange={(e) => setEmail(e.target.value)}
                       required
                />

            </div>

            <div className={styles.field_name}>Password</div>
            <div className={styles.input_field}>
                <input className={styles.input_field}
                       type="password"
                       name="password"
                       value={password}
                       onChange={(e) => setPassword(e.target.value)}
                       required
                />

            </div>
            <div className={styles.field_name}>Confirm password</div>
            <div className={styles.input_field}>
                <input className={styles.input_field}
                       type="password"
                       name="password2"
                       value={password2}
                       onChange={(e) => setPassword2(e.target.value)}
                       required
                />

            </div>
            <button className={styles.sign_up_button} type="submit">Create account</button>
            <a className={styles.field_name} href="/login">Already have an account? </a>

        </form>
    );
}

export default SignUpForm;