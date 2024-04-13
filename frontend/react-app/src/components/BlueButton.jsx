import React from 'react';
import PropTypes from 'prop-types';
import styles from '../css/BlueButton.module.css';
function BlueButton({ text, onClick }) {
    return (
        <button type="button" className={styles.blueButton} onClick={onClick}>
            {text}
        </button>
    );
}

BlueButton.propTypes = {
    text: PropTypes.string.isRequired,
    onClick: PropTypes.func.isRequired
};

BlueButton.defaultProps = {
    text: 'Click me',
    onClick: () => {}
};

export default BlueButton;