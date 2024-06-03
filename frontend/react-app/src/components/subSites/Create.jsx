import React, { useEffect, useState } from 'react';
import Navbar from "../Navbar";
import CategoryTile from "../CategoryTile";
import SearchBar from "../SearchBar";
import styles from '../../css/sub_sites_css/Create.module.css';
import ItemTemplate from "../ItemTemplate";
import axios from 'axios';
import authHeader from '../../service/auth-header';
import BlueButton from "../BlueButton";

function Create() {
    const [categories, setCategories] = useState([]);
    const [items, setItems] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState(null);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        axios.get('http://localhost:8080/api/item/get-all-categories', { headers: authHeader() })
            .then(response => {
                setCategories(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the categories!', error);
            });

        axios.get('http://localhost:8080/api/item/get-all', { headers: authHeader() })
            .then(response => {
                setItems(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the items!', error);
            });
    }, []);

    const handleCategoryClick = (categoryName) => {
        setSelectedCategory(categoryName);
    };

    const handleResetClick = () => {
        setSelectedCategory(null);
    };

    const filteredItems = items.filter(item => {
        const matchesCategory = selectedCategory ? item.categoryName === selectedCategory : true;
        const matchesSearch = item.itemName.includes(searchTerm) || item.categoryName.includes(searchTerm) || item.subcategoryName.includes(searchTerm);
        return matchesCategory && matchesSearch;
    });

    return (
        <>
            <Navbar/>
            <div className={styles.content}>
                <div className={styles.result_box}>
                    <div className={styles.result_tiles_container}>
                        <SearchBar onSearch={setSearchTerm}/>
                        {selectedCategory && <BlueButton onClick={handleResetClick} text={`Wyświetlane kategorie: ${selectedCategory}`}></BlueButton>}
                        {filteredItems.map(item => <ItemTemplate key={item.itemId} itemId={item.itemId}  itemName={item.itemName} category={item.categoryName} subcategory={item.subcategoryName}/>)}
                    </div>
                </div>
                <div className={styles.categories_box}>
                    <div className={styles.categories_text}>Categories</div>
                    <div className={styles.category_container}>
                        {categories.map(category => <CategoryTile key={category.categoryId} category={category.categoryName} onClick={() => handleCategoryClick(category.categoryName)}/>)}
                    </div>
                </div>
            </div>
        </>
    );
}

export default Create;