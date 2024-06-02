import PropTypes from 'prop-types';
import ComboBox from './ComboBox.jsx';
import styles from '../../css/OfferForm.module.css';
import PhotosPreview from './PhotosPreview.jsx';
import React, { useEffect, useState } from 'react';
import BlueButton from "../BlueButton";
import axios from 'axios';
import authHeader from "../../service/auth-header";
import {Navigate, useNavigate} from 'react-router-dom';


function OfferForm({
                       itemId = 1,
                       typeOptions
                   }) {
    const navigate = useNavigate();
    const [selectedOption, setSelectedOption] = useState('');
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [userText, setUserText] = useState('');
    const [freeOffers, setFreeOffers] = useState(0);
    const [categoryName, setCategoryName] = useState('category');
    const [subcategoryName, setSubcategoryName] = useState('subcategory');
    const [itemName, setItemName] = useState('item');
    const [photos, setPhotos] = useState([]);
    const [price, setPrice] = useState(0);
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [message, setMessage] = useState('');

    const handlePhotoChange = (e) => {
        let newPhotos = [...photos];
        for (let i = 0; i < e.target.files.length; i++) {
            newPhotos.push(e.target.files[i]);
        }
        setPhotos(newPhotos);
    };



    const fetchItemInfo = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/api/item/get/${itemId}`, { headers: authHeader() });
            setCategoryName(response.data.categoryName);
            setSubcategoryName(response.data.subcategoryName);
            setItemName(response.data.itemName);
        } catch (error) {
            console.error(error);
        }
    };

    const fetchFreeOffers = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/user/freeOffers', { headers: authHeader() });
            setFreeOffers(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        fetchFreeOffers();
        fetchItemInfo();
    }, []);

    const handleButtonClick = async () => {
        console.log("userText:", userText);

        if (!userText.trim()) {
            console.error("userText is empty");
            return;
        }

        try {
            const response = await axios.get(`http://localhost:8080/api/OpenAI/chat?userText=${encodeURIComponent(userText)}`, { headers: authHeader() });
            setDescription(response.data);
            fetchFreeOffers();
        } catch (error) {
            console.error(error);
        }
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

        if (selectedOption !== '0') {
            formData.price = price;
        }

        const endpoint = selectedOption === '0' ? `http://localhost:8080/api/template/add/${itemId}` : `http://localhost:8080/api/offer/add/${itemId}`;

        try {
            const response1 = await axios.post(endpoint, formData, { headers: authHeader() });


            if (selectedOption !== '0') {
                const offerId = response1.data.offerId;
                const formData1 = new FormData();

                photos.forEach(photo => {
                    formData1.append('files', photo);
                });

                await axios.post(`http://localhost:8080/api/offer/update-offer-images/${offerId}`, formData1, { headers: authHeader() });
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
                <ComboBox
                    selectLabel="Select Type"
                    selectName="Type"
                    options={typeOptions}
                    onOptionSelected={setSelectedOption}
                />
            </div>

            {selectedOption && (
                <>
                    <label className={styles.label_offer} htmlFor="userText">Generate description with AI:</label>
                    <textarea
                        className={styles.textarea_chat}
                        id="userText"
                        name="userText"
                        rows="5"
                        placeholder="Short description"
                        value={userText}
                        onChange={e => setUserText(e.target.value)}
                    />
                    <div>
                        <BlueButton onClick={handleButtonClick} text="Generate Text"/>
                        <label className={styles.label_offer}> {freeOffers} free description generations
                            left</label>
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

                    {selectedOption !== '0' && (
                        <>
                            <label className={styles.label_offer} htmlFor="price">Price:</label>
                            <input
                                className={styles.offer_price}
                                type="number"
                                id="price"
                                name="price"
                                step="0.01"
                                placeholder="Price"
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
                                <PhotosPreview photos={photos.map(photo => URL.createObjectURL(photo))}/>

                            </div>
                        </>
                    )}

                    <div className={styles.button_container}>
                        <button className={styles.button_offer} type="submit" name="action" value="save">Save
                        </button>
                    </div>
                    <div className={styles.label_offer}>{message}</div>
                </>
            )}
        </form>
    );
}


OfferForm.propTypes = {
    itemId: PropTypes.number,
    messages: PropTypes.arrayOf(PropTypes.string),
    typeOptions: PropTypes.arrayOf(PropTypes.shape({
        value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
        label: PropTypes.string
    })),
};

OfferForm.defaultProps = {

    messages: [],
    typeOptions: [
        { value: '', label: 'Select Type' },
        { value: '0', label: 'Template' },
        { value: '1', label: 'Offer' }
    ],
};

export default OfferForm;
