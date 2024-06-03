
import Navbar from "../Navbar";
import BlueButton from "../BlueButton";
import OfferForm from "../offerCreating/OfferForm";
import styles from '../../css/sub_sites_css/Offer.module.css';
import {useLocation} from "react-router-dom";
function Offer() {

    const location = useLocation();
    const { itemId } = location.state;
    return (
        <div >
            <Navbar/>
            <OfferForm itemId={itemId}/>
        </div>
    );
}

export default Offer;