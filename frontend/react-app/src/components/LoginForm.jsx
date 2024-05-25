import React, { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "../service/auth.service";
import { isEmail } from "validator";
import styles from '../css/LoginForm.module.css';
import axios from "axios";

const required = (value) => {
    if (!value) {
        return (
            <div className="" role="alert">
                This field is required!
            </div>
        );
    }
};

const LoginForm = () => {
    let navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState("");

    const onChangeEmail = (e) => {
        const email = e.target.value;
        setEmail(email);
    };

    const onChangePassword = (e) => {
        const password = e.target.value;
        setPassword(password);
    };

    const handleLogin = (e) => {
        e.preventDefault();

        setMessage("");
        setLoading(true);

        if (isEmail(email)) {
            AuthService.login(email, password).then(
                () => {
                    navigate("/dashboard");
                },
                (error) => {
                    const resMessage =
                        (error.response &&
                            error.response.data &&
                            error.response.data.message) ||
                        error.message ||
                        error.toString();

                    setLoading(false);
                    setMessage(resMessage);
                }
            );
        } else {
            setLoading(false);
            setMessage("Invalid email format");
        }
    };

    return (
        <form className={styles.form_login} onSubmit={handleLogin}>
            <div className={styles.text1}>Log in</div>
            <div className={styles.text2}>Enter your details below</div>

            <div className={styles.field_name}>Your Email</div>
            <div className={styles.input_field}>
                <input
                    type="text"
                    name="email"
                    value={email}
                    onChange={onChangeEmail}
                    required
                />
            </div>
            <div className={styles.field_name}>Password</div>
            <div className={styles.input_field}>
                <input
                    type="password"
                    name="password"
                    value={password}
                    onChange={onChangePassword}
                    required
                />
            </div>
            <button className={styles.log_in_button} type="submit" disabled={loading}>
                Log in
            </button>
            {message && <div className={styles.error_message}>{message}</div>}
            <a className={styles.field_name} href="/signup">Don't have an account?</a>
        </form>
    );
}

export default LoginForm;