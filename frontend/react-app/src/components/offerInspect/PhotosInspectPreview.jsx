import PropTypes from 'prop-types';
import styles from '../../css/PhotosPreview.module.css';

function PhotosInspectPreview({ photos }) {
    return (
        <div className="photo_preview_container">
            {photos.map((photo, index) => (
                <img className={styles.photo_preview} key={index} src={photo} alt='preview'  />
            ))}
        </div>
    );
}

PhotosInspectPreview.propTypes = {
    photos: PropTypes.arrayOf(PropTypes.string),
};

PhotosInspectPreview.defaultProps = {
    photos: [],
};

export default PhotosInspectPreview;