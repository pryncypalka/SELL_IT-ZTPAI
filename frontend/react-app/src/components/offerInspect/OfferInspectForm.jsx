import PropTypes from 'prop-types';
import styles from '../../css/OfferForm.module.css';
import PhotosInspectPreview from './PhotosInspectPreview.jsx';
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import authHeader from "../../service/auth-header";
import { useNavigate } from 'react-router-dom';

function OfferInspectForm({ isOffer, id }) {
    const navigate = useNavigate();
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [categoryName, setCategoryName] = useState('category');
    const [subcategoryName, setSubcategoryName] = useState('subcategory');
    const [itemName, setItemName] = useState('item');
    const [photos, setPhotos] = useState([]);
    const [photoURLs, setPhotoURLs] = useState([]);
    const [price, setPrice] = useState(0);
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [message, setMessage] = useState('');
    const [itemId, setItemId] = useState(0);

    useEffect(() => {
        const endpoint = isOffer ? `http://localhost:8080/api/offer/get/${id}` : `http://localhost:8080/api/template/get/${id}`;

        axios.get(endpoint, { headers: authHeader() })
            .then(response => {
                setTitle(response.data.title);
                setDescription(response.data.description);
                setItemId(response.data.itemId);
                if (isOffer) {
                    setPrice(response.data.price);
                    axios.get(`http://localhost:8080/api/offer/offers/${id}/photoPaths`, { headers: authHeader() })
                        .then(response => {
                            const photoURLs = response.data.map(photo => {
                                return `http://localhost:8080/api/user/images?path=${encodeURIComponent(photo.replace(/\\\\/g, '/'))}`;
                            });
                            setPhotoURLs(photoURLs);
                        })
                        .catch(error => {
                            console.error('There was an error fetching photo paths!', error);
                        });
                }
                axios.get(`http://localhost:8080/api/item/get/${response.data.itemId}`, { headers: authHeader() })
                    .then(itemResponse => {
                        setItemName(itemResponse.data.itemName);
                        setCategoryName(itemResponse.data.categoryName);
                        setSubcategoryName(itemResponse.data.subcategoryName);
                    })
                    .catch(error => {
                        console.error('There was an error fetching the item data!', error);
                    });
            })
            .catch(error => {
                console.error('There was an error fetching the data!', error);
            });
    }, [isOffer, id]);

    const handleExit = () => {
        navigate('/dashboard');
    };

    const handlePhotoChange = (e) => {
        let newPhotos = [...photos];
        let newPhotoURLs = [...photoURLs];
        for (let i = 0; i < e.target.files.length; i++) {
            newPhotos.push(e.target.files[i]);
            newPhotoURLs.push(URL.createObjectURL(e.target.files[i]));
        }
        setPhotos(newPhotos);
        setPhotoURLs(newPhotoURLs);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (isSubmitting) {
            return;
        }

        if (!title.trim() || !description.trim()) {
            setMessage("Title and description cannot be empty.");
            return;
        }

        setIsSubmitting(true);

        const formData = {
            title,
            description,
        };

        if (isOffer) {
            formData.price = price;
        }

        const endpoint = !isOffer ? `http://localhost:8080/api/template/update/${id}` : `http://localhost:8080/api/offer/update/${id}`;

        try {
            const response1 = await axios.put(endpoint, formData, { headers: authHeader() });

            if (isOffer) {
                const offerId = response1.data.offerId;
                const formData1 = new FormData();

                photos.forEach(photo => {
                    formData1.append('files', photo);
                });

                if (photos.length > 0) {

                    await axios.post(`http://localhost:8080/api/offer/update-offer-images/${offerId}`, formData1, {headers: authHeader()});
                }
            }

            setMessage("Data saved successfully!");
            setTimeout(() => {
                navigate('/dashboard');
            }, 500);
        } catch (error) {
            console.error(error);
            setMessage("Failed to save data. Please try again.");
        } finally {
            setIsSubmitting(false);
        }
    };

    return (
        <form className={styles.form_offer} onSubmit={handleSubmit} encType="multipart/form-data">
            <div>
                <label className={styles.label_offer}>{itemName} - {categoryName} / {subcategoryName}</label>
            </div>
            <label className={styles.label_offer} htmlFor="title">Title:</label>
            <input
                className={styles.input_offer}
                type="text"
                id="title"
                name="title"
                placeholder="Title"
                value={title}
                onChange={e => setTitle(e.target.value)}
            />

            <label className={styles.label_offer} htmlFor="description">Description:</label>
            <textarea
                className={styles.textarea_offer}
                id="description"
                name="description"
                rows="10"
                placeholder="Description"
                value={description}
                onChange={e => setDescription(e.target.value)}
            />

            {isOffer && (
                <>
                    <label className={styles.label_offer} htmlFor="price">Price:</label>
                    <input
                        className={styles.offer_price}
                        type="number"
                        id="price"
                        name="price"
                        step="0.01"
                        placeholder="Price"
                        value={price}
                        onChange={e => setPrice(e.target.value)}
                    />
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
                        <PhotosInspectPreview photos={photoURLs} />
                    </div>
                </>
            )}
            <div className={styles.button_container}>
                <button className={styles.button_offer} type="submit" name="action" value="save">Save</button>
                <button className={styles.button_offer} type="button" onClick={handleExit}>Exit</button>
            </div>
            <div className={styles.label_offer}>{message}</div>
        </form>
    );
}

OfferInspectForm.propTypes = {
    itemId: PropTypes.number,
    messages: PropTypes.arrayOf(PropTypes.string),
    isOffer: PropTypes.bool,
    id: PropTypes.number
};

OfferInspectForm.defaultProps = {
    messages: [],
};

export default OfferInspectForm;
