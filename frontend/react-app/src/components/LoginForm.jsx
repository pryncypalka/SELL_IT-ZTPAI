import React, { useState } from 'react';

function LoginForm() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [messages, setMessages] = useState([]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            // Tutaj można dodać logikę wysyłania żądania logowania do serwera
            // np. za pomocą fetch lub axios
            console.log('Email:', email);
            console.log('Password:', password);
        } catch (error) {
            console.error('Login error:', error);
            // Obsługa błędów logowania
        }
    };

    return (
        <form className="form_login" onSubmit={handleSubmit}>
            <div className="text1">Log in</div>
            <div className="text2">Enter your details below</div>
            <div className="messages">
                {messages.map((message, index) => (
                    <div key={index}>{message}</div>
                ))}
            </div>
            <div className="field_name">Your Email</div>
            <div className="input_field">
                <input
                    type="text"
                    name="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
            </div>
            <div className="field_name">Password</div>
            <div className="input_field">
                <input
                    type="password"
                    name="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
            </div>
            <button className="log_in_button" type="submit">
                Log in
            </button>
            <div className="field_name">Don't have an account?</div>
            <a className="signup_link" href="/signup">
                Sign up
            </a>
        </form>
    );
}

export default LoginForm;