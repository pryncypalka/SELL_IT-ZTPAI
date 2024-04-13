import React, { useState } from 'react';
import styles from '../css/SignUpForm.module.css';


function SignUpForm() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [password2, setPassword2] = useState('');
    const [messages, setMessages] = useState([]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        // Tutaj można dodać logikę wysyłania żądania rejestracji do serwera
        console.log('Email:', email);
        console.log('Password:', password);
        console.log('Confirm Password:', password2);
    };

    return (
        <form className={styles.form_sign_up} onSubmit={handleSubmit}>
            <div className={styles.text1}>Sign up</div>
            <div className={styles.text2}>Create your account. It's free and only takes a minute.</div>

            <div className={styles.field_name}>Your Email</div>
            <div className={styles.input_field}>
                <input className={styles.input_field}
                    type="text"
                    name="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />

            </div>
            <div className={styles.field_name}>Password</div>
            <div className={styles.input_field}>
                <input className={styles.input_field}
                    type="password"
                    name="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />

            </div>
            <div className={styles.field_name}>Confirm password</div>
            <div className={styles.input_field}>
                <input className={styles.input_field}
                    type="password"
                    name="password2"
                    value={password2}
                    onChange={(e) => setPassword2(e.target.value)}
                />

            </div>
            <button className={styles.sign_up_button} type="submit">Create account</button>
            <a className={styles.field_name} href="/login">Already have an account? </a>

        </form>
    );
}

export default SignUpForm;
