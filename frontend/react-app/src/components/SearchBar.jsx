import React, { useState } from 'react';
import PropTypes from 'prop-types'; // Importujemy PropTypes
import styles from '../css/SearchBar.module.css';

function SearchBar({ placeholder }) {
    const [searchTerm, setSearchTerm] = useState('');

    const handleChange = (e) => {
        setSearchTerm(e.target.value);
        // Tutaj możesz umieścić logikę wyszukiwania, jeśli chcesz ją obsłużyć na bieżąco
    };

    return (
        <div className={styles.search_field}>
            <input className={styles.input_search}
                   type="text"
                   name="search_template"
                   value={searchTerm}
                   onChange={handleChange}
                   placeholder={placeholder} // Placeholder jako parametr funkcji
            />
        </div>
    );
}

SearchBar.propTypes = {
    placeholder: PropTypes.string // Placeholder powinien być typu string
};

SearchBar.defaultProps = {
    placeholder: "Wyszukaj..." // Domyślny placeholder
};

export default SearchBar;
