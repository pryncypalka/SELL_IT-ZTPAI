import PropTypes from 'prop-types';

function PhotosPreview({ photos }) {
    return (
        <div className="photo_preview_container">
            {photos.map((photo, index) => (
                <img key={index} src={photo} alt='preview' className="photo_preview_image" />
            ))}
        </div>
    );
}

PhotosPreview.propTypes = {
    photos: PropTypes.arrayOf(PropTypes.string),
};

PhotosPreview.defaultProps = {
    photos: [],
};

export default PhotosPreview;
