
import Navbar from "../Navbar";
import LogoContainer from "../LogoContainer";
import Avatar from "../Avatar";
import SearchBar from "../SearchBar";
import TemplateTile from "../TemplateTile";
import BlueButton from "../BlueButton";
import OfferTile from "../OfferTile";
import styles from '../../css/sub_sites_css/Offer.module.css';

function Dashboard() {
    return (
        <>
            <LogoContainer/>
            <Navbar/>
            <Avatar/>
            <BlueButton text={"Create new offer"}/>
            <SearchBar/>
            <TemplateTile template={"Template"}/>
            <SearchBar/>
            <OfferTile/>

        </>
    );
}

export default Dashboard;