
import Navbar from "../Navbar";
import LogoContainer from "../LogoContainer";
import styles from '../../css/sub_sites_css/Signup.module.css';
import SignUpForm from "../SignUpForm";
import SignUpPicture from "../SignUpPicture";

function SignUp() {
    return (
        <>

                <LogoContainer/>
                <div className={styles.signup_container}>
                    <SignUpForm/>
                    <SignUpPicture/>
                </div>


        </>

    );
}

export default SignUp;