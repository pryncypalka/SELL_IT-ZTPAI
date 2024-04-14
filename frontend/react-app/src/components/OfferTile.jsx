
import styles from '../css/OfferTile.module.css';
import PropTypes from 'prop-types';
function OfferTile(props) {
    return (

        <div className={styles.offer_tile}>
            <img className={styles.offer_image} src={props.image} alt="offer_image"/>
            <div className="offer_info">
                <div className={styles.offer_name}>{props.name}</div>
                <div className={styles.offer_first_line}>{props.firstLine}</div>
                <div className={styles.offer_price}>{props.price}</div>
                <div className={styles.offer_date}>{props.date}</div>
                <button type="submit" onClick=''>Delete</button>
            </div>
        </div>
    );
}

OfferTile.propTypes = {
    image: PropTypes.string,
    name: PropTypes.string,
    firstLine: PropTypes.string,
    price: PropTypes.number,
    date: PropTypes.string,
};

OfferTile.defaultProps = {
    image: '/assets/image/logo.png',
    name: 'Default Name',
    firstLine: 'Default First Line',
    price: 0,
    date: 'Default Date',
};

export default OfferTile;