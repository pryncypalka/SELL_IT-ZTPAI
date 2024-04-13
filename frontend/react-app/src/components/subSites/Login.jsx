import LogoContainer from "../LogoContainer";
import LoginForm from "../LoginForm";
import LoginPicture from "../LoginPicture";
import styles from '../../css/sub_sites_css/Login.module.css';

function Login() {
    return (
        <>
            <LogoContainer/>
            <div className={styles.login_container}>
            <LoginForm/>
            <LoginPicture/>
            </div>
        </>

    );
}

export default Login;