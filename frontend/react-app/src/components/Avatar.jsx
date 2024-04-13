import React from 'react';
import PropTypes from 'prop-types';
import styles from '../css/Avatar.module.css';

function Avatar({ imagePath, username }) {
    return (
        <div className={styles.avatar}>
            <img className={styles.avatarImage}  src={imagePath} alt="avatar"/>
            <p id="username">{username}</p>
        </div>
    );
}

Avatar.propTypes = {
    imagePath: PropTypes.string,
    username: PropTypes.string
};

Avatar.defaultProps = {
    imagePath: '/assets/image/profile_empty.png',
    username: 'Username'
};

export default Avatar;
