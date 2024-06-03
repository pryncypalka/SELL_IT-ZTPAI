import styles from '../css/OfferTile.module.css';
import PropTypes from 'prop-types';
import axios from 'axios';
import authHeader from '../service/auth-header';
import {useEffect, useState} from "react";
import { useNavigate} from "react-router-dom";

function OfferTile( {offerId, image, title, description, price, date}) {
    const [imagePath, setImagePath] = useState('');
    const navigate = useNavigate();



    useEffect(() => {
        if (image === null) {
            return;
        }

        const imageUrl = `http://localhost:8080/api/user/images?path=${encodeURIComponent(image.replace(/\\\\/g, '/'))}`;
        axios.get(imageUrl, { headers: authHeader() })
            .then(response => {
                setImagePath(imageUrl);
            })
            .catch(error => {
                console.error('There was an error fetching the data!', error);
            });
    }, [image]);

    const handleInspect = () => {
        navigate('/offerInspect', { state: { id: offerId, isOffer: true } });
    };


    const handleDelete = (event) => {
        event.stopPropagation();
        if (window.confirm('Are you sure you want to delete this offer?')) {
            axios.delete(`http://localhost:8080/api/offer/delete/${offerId}`, { headers: authHeader() })
                .then(response => {
                    console.log('Offer deleted successfully');
                    window.location.reload();

                })
                .catch(error => {
                    console.error('There was an error deleting the offer!', error);
                });
        }
    };

    return (
        <div className={styles.offer_tile} onClick={handleInspect}>
            <img className={styles.offer_image} src={imagePath || '/assets/image/image-not-found-icon.png'}
                 alt="offer_image"/>
            <div className="offer_info">
                <div className={styles.offer_name}>{title}</div>
                <div className={styles.offer_first_line}>{description}</div>
                <div className={styles.offer_price}>{price}</div>
                <div className={styles.offer_date}>{date}</div>
                <button className={styles.delete_button} type="submit" onClick={handleDelete}>Delete</button>
            </div>
        </div>
    );
}

OfferTile.propTypes = {
    offerId: PropTypes.number.isRequired,
    image: PropTypes.string,
    title: PropTypes.string,
    description: PropTypes.string,
    price: PropTypes.number,
    date: PropTypes.string
};

OfferTile.defaultProps = {
    OfferId: 0,
    image: '/assets/image/image-not-found-icon.png',
    title: 'Default Name',
    description: 'Default First Line',
    price: 0,
    date: 'Default Date',
};

export default OfferTile;