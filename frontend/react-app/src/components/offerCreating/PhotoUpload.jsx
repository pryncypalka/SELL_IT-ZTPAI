import React, { useState } from 'react';
import PhotosPreview from './PhotosPreview.jsx';
import styles from '../../css/OfferForm.module.css';

function PhotoUpload() {
    const [photos, setPhotos] = useState([]);

    const handlePhotoChange = (e) => {
        let newPhotos = [...photos];
        for (let i = 0; i < e.target.files.length; i++) {
            newPhotos.push(e.target.files[i]);
        }
        setPhotos(newPhotos);
    };

    return (
        <div className={styles.photo_container}>
            <label className={styles.label_offer}>Photos:</label>
            <input
                className={styles.input_offer}
                type="file"
                id="photo"
                name="photo[]"
                accept="image/*"
                multiple
                onChange={handlePhotoChange}
            />
            <PhotosPreview photos={photos.map(photo => URL.createObjectURL(photo))} />

        </div>
    );
}

export default PhotoUpload;
