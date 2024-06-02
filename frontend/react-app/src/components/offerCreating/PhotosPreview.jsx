import PropTypes from 'prop-types';
import styles from '../../css/PhotosPreview.module.css';

function PhotosPreview({ photos }) {
    return (
        <div className="photo_preview_container">
            {photos.map((photo, index) => (
                <img className={styles.photo_preview} key={index} src={photo} alt='preview'  />
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