
import { useLocation } from 'react-router-dom';
import Navbar from "../Navbar";
import OfferInspectForm from "../offerInspect/OfferInspectForm";

function OfferInspect() {
    const location = useLocation();
    const { id, isOffer } = location.state;

    return (
        <div >
            <Navbar/>
            <OfferInspectForm id={id} isOffer={isOffer}/>
        </div>
    );
}

export default OfferInspect;