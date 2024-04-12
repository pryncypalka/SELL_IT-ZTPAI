import PropTypes from 'prop-types';

function Messages({ messages }) {
    return (
        <div className="messages_container">
            {/* Wyświetlanie komunikatów */}
            {messages.map((message, index) => (
                <div key={index}>{message}</div>
            ))}
        </div>
    );
}

Messages.propTypes = {
    messages: PropTypes.arrayOf(PropTypes.string),
};

Messages.defaultProps = {
    messages: [],
};

export default Messages;