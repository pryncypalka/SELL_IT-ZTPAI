import PropTypes from 'prop-types';

function ChangeAvatar({ avatarPath }) {
    return (
        <form action="changeAvatar" method="post" encType="multipart/form-data">
            <p className="Change_avatar">Change avatar</p>

            <img className="avatar_preview" src={avatarPath}
                 alt="avatar_preview"/>
            <input type="file" name="file"/><br/>
            <button type="submit">Send</button>
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
