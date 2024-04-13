
import 'bootstrap/dist/css/bootstrap.min.css';
import styles from '../css/Navbar.module.css';
import Avatar from './Avatar';
import LogoContainer from './LogoContainer';

function Navbar() {
    return (
        <nav>
            <LogoContainer />
            <div className={styles.Options}>
                <a className={styles.textHome} href="/dashboard">HOME</a>
                <a className={styles.textCreate} href="/create">CREATE</a>
                <a className={styles.textAccount} href="/account">ACCOUNT</a>
            </div>
            <Avatar />
        </nav>
    );
}

export default Navbar;