import styles from '../css/OfferTile.module.css';
import PropTypes from 'prop-types';
import axios from 'axios';
import authHeader from '../service/auth-header';


function OfferTile( {offerId, image, title, description, price, date}) {
    const handleDelete = () => {
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
        <div className={styles.offer_tile}>
            <img className={styles.offer_image} src={image} alt="offer_image"/>
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
    image: '/assets/image/logo.png',
    title: 'Default Name',
    description: 'Default First Line',
    price: 0,
    date: 'Default Date',
};

export default OfferTile;