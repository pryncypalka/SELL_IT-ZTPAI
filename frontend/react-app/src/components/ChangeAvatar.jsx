import PropTypes from 'prop-types';
import styles from '../css/ChangeAvatar.module.css';
import { Form, Button } from 'react-bootstrap';
import BlueButton from "./BlueButton";
function ChangeAvatar({ avatarPath }) {
    return (
        <form className={styles.formChangeAvatar} action="changeAvatar" method="post" encType="multipart/form-data">
            <p className={styles.changeAvatar}>Change avatar</p>

            <img className={styles.avatar_preview} src={avatarPath}
                 alt="avatar_preview"/>
            <input  className={styles.inputFile}  type="file" name="file"/><br/>
            <BlueButton text="Send"/>
        </form>
    );
}

ChangeAvatar.propTypes = {
    avatarPath: PropTypes.string,
};

ChangeAvatar.defaultProps = {
    avatarPath: '/assets/image/profile_empty.png',
};

export default ChangeAvatar;
