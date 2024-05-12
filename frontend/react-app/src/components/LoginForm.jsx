import React, { useState } from 'react';
import styles from '../css/LoginForm.module.css';
import axios from "axios";
function LoginForm() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');


    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            // Wysyłanie żądania do serwera
            const response = await axios.get(`http://localhost:8080/api/user/getUserByEmail/${email}`);

            // Obsługa odpowiedzi od serwera
            if (response.status === 200) {
                console.log('User data:', response.data);
            } else {
                console.error('Error fetching user data:', response.data);
            }
        } catch (error) {
            console.error('Login error:', error);
        }
    };

    return (
        <form className={styles.form_login} onSubmit={handleSubmit}>
            <div className={styles.text1}>Log in</div>
            <div className={styles.text2}>Enter your details below</div>

            <div className={styles.field_name}>Your Email</div>
            <div className={styles.input_field}>
                <input
                    type="text"
                    name="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
            </div>
            <div className={styles.field_name}>Password</div>
            <div className={styles.input_field}>
                <input
                    type="password"
                    name="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
            </div>
            <button className={styles.log_in_button} type="submit">
                Log in
            </button>
            <a className={styles.field_name} href="/signup">Don't have an account?</a>

        </form>
    );
}

export default LoginForm;