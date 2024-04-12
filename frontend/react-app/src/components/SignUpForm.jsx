import React, { useState } from 'react';

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
        <form className="form_sign_up" onSubmit={handleSubmit}>
            <div className="text1">Sign up</div>
            <div className="text2">Create your account. It's free and only takes a minute.</div>
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
                <div className="email-message"></div>
            </div>
            <div className="field_name">Password</div>
            <div className="input_field">
                <input
                    type="password"
                    name="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <div className="password-message"></div>
            </div>
            <div className="field_name">Confirm password</div>
            <div className="input_field">
                <input
                    type="password"
                    name="password2"
                    value={password2}
                    onChange={(e) => setPassword2(e.target.value)}
                />
                <div className="password-message2"></div>
            </div>
            <button className="sign_up_button" type="submit">Create account</button>
            <div className="field_name">Already have an account?</div>
            <a className="login_link" href="/login">Log in</a>
        </form>
    );
}

export default SignUpForm;
