import React from 'react';
import PropTypes from 'prop-types';

function Avatar({ imagePath, username }) {
    return (
        <div className="avatar">
            <img src={imagePath} alt="avatar"/>
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
