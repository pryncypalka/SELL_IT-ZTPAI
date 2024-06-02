import React, { useEffect, useState } from 'react';
import Navbar from "../Navbar";

import TemplateTile from "../TemplateTile";
import OfferTile from "../OfferTile";
import styles from '../../css/sub_sites_css/Dashboard.module.css';
import axios from 'axios';
import authHeader from '../../service/auth-header';
import SearchBar from "../SearchBar";

function Dashboard() {
    const [templates, setTemplates] = useState([]);
    const [offers, setOffers] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [offerSearchTerm, setOfferSearchTerm] = useState('');

    useEffect(() => {
        axios.get('http://localhost:8080/api/template/get-all', { headers: authHeader() })
            .then(response => {
                const templates = response.data;
                const promises = templates.map(template =>
                    axios.get(`http://localhost:8080/api/item/get/${template.itemId}`, { headers: authHeader() })
                );
                Promise.all(promises)
                    .then(results => {
                        const updatedTemplates = templates.map((template, index) => {
                            const item = results[index].data;
                            let date = new Date(template.createdAt);
                            let formattedDate = date.toISOString().split('T')[0];
                            return {
                                ...template,
                                categoryName: item.categoryName,
                                subcategoryName: item.subcategoryName,
                                createdAt: formattedDate
                            };
                        });
                        setTemplates(updatedTemplates);
                    })
                    .catch(error => {
                        console.error('There was an error fetching the item details!', error);
                    });
            })
            .catch(error => {
                console.error('There was an error fetching the templates!', error);
            });
        axios.get('http://localhost:8080/api/offer/get-all', { headers: authHeader() })
            .then(response => {
                const offers = response.data;
                const updatedOffers = offers.map(offer => {
                    let date = new Date(offer.createdAt);
                    let formattedDate = date.toISOString().split('T')[0];
                    return {...offer, createdAt: formattedDate};
                });
                setOffers(updatedOffers);

            })
            .catch(error => {
                console.error('There was an error fetching the offers!', error);
            });
    }, []);


    const handleSearch = (term) => {
        setSearchTerm(term);
    };

    const handleOfferSearch = (term) => {
        setOfferSearchTerm(term);
    };

    const filteredTemplates = templates.filter(template =>
        template.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
        template.categoryName.toLowerCase().includes(searchTerm.toLowerCase()) ||
        template.subcategoryName.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const filteredOffers = offers.filter(offer =>
        offer.title.toLowerCase().includes(offerSearchTerm.toLowerCase())
    );


    return (
        <>
            <Navbar/>
            <div className={styles.container_main}>
                <div className={styles.templates_box}>
                    <div className={styles.your_templates}>Your templates</div>
                    <SearchBar onSearch={handleSearch}/>
                    <div className={styles.template_tiles_container}>
                        {filteredTemplates.map(template => <TemplateTile key={template.templateId} templateId={template.templateId} name={template.title} createdAt={template.createdAt} description={template.description} categoryName={template.categoryName} subcategoryName={template.subcategoryName} />)}
                    </div>
                </div>
                <div className={styles.offers_box}>
                    <div className={styles.your_offers}>Your Offers</div>
                    <SearchBar onSearch={handleOfferSearch}/>
                    <div className={styles.offer_tiles_container}>
                        {filteredOffers.map(offer => (
                            <OfferTile key={offer.offerId} offerId={offer.offerId} title={offer.title} date={offer.createdAt} description={offer.description} price={offer.price} image={offer.firstPhoto}/>
                        ))}
                    </div>
                </div>
            </div>
        </>
    );
}

export default Dashboard;