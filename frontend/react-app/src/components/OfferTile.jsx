import PropTypes from 'prop-types';

function OfferTile(props) {
    return (
        <div className="offer_tile">
            <img className="offer_image" src={props.image} alt="offer_image"/>
            <div className="offer_info">
                <div className="offer_name">{props.name}</div>
                <div className="offer_first_line">{props.firstLine}</div>
                <div className="offer_price">{props.price}</div>
                <div className="offer_date">{props.date}</div>
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
    image: '/assets/image/offer_default.png',
    name: 'Default Name',
    firstLine: 'Default First Line',
    price: 0,
    date: 'Default Date',
};

export default OfferTile;