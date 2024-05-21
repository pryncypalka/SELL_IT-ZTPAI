
import Navbar from "../Navbar";
import LogoContainer from "../LogoContainer";
import Avatar from "../Avatar";
import SearchBar from "../SearchBar";
import TemplateTile from "../TemplateTile";
import BlueButton from "../BlueButton";
import OfferTile from "../OfferTile";
import styles from '../../css/sub_sites_css/Dashboard.module.css';

function Dashboard() {
    return (
        <>
            <Navbar/>
            <div className={styles.add_new}>
                <BlueButton text={"Create new offer"}/>
            </div>
            <div className={styles.container_main}>

                <div className={styles.templates_box}>
                    <div className={styles.your_templates}>Your templates</div>
                    <SearchBar/>
                    <div className={styles.template_tiles_container}>
                        <TemplateTile template={"Template"}/>
                        <TemplateTile template={"Template"}/>
                        <TemplateTile template={"Template"}/>
                        <TemplateTile template={"Template"}/>
                        <TemplateTile template={"Template"}/>
                        <TemplateTile template={"Template"}/>
                        <TemplateTile template={"Template"}/>
                        <TemplateTile template={"Template"}/>
                        <TemplateTile template={"Template"}/>

                    </div>
                </div>


                <div className={styles.offers_box}>
                    <div className={styles.your_offers}>Your Offers</div>
                    <SearchBar/>
                    <div className={styles.offer_tiles_container}>
                        <OfferTile/>
                        <OfferTile/>
                        <OfferTile/>
                        <OfferTile/>

                    </div>
                </div>
            </div>

        </>
    );
}

export default Dashboard;