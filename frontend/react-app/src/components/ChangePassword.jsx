import styles from '../css/ChangePassword.module.css'
import BlueButton from "./BlueButton";

function changePassword() {
    return (
        <form className={styles.change_password_form} action="changePassword" method="post">
            <p className="Change_password">Change password</p>

            <input className={styles.input_field} type="password" name="old_password" placeholder="Old password"/>
            <input className={styles.input_field}type="password" name="new_password" placeholder="New password"/>
            <input className={styles.input_field} type="password" name="new_password_repeat"
                   placeholder="Repeat new password"/>
            <BlueButton text="Change password"/>
        </form>

    );
}

export default changePassword;