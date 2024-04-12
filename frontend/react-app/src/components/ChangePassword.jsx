function changePassword() {
    return (
        <form action="changePassword" method="post">
            <p className="Change_password">Change password</p>

            <input className="input_field" type="password" name="old_password" placeholder="Old password"/>
            <input className="input_field" type="password" name="new_password" placeholder="New password"/>
            <input className="input_field" type="password" name="new_password_repeat"
                   placeholder="Repeat new password"/>
            <button type="submit">Send</button>
        </form>

    );
}

export default changePassword;