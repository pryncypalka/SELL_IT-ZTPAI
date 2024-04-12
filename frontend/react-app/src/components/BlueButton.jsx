import React from 'react';
import PropTypes from 'prop-types';

function BlueButton({ text, onClick }) {
    return (
        <button type="button" className="blue-button" onClick={onClick}>
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