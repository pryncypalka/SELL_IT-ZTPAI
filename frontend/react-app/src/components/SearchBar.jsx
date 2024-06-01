import React from 'react';
import PropTypes from 'prop-types';
import styles from '../css/SearchBar.module.css';

function SearchBar({ placeholder, onSearch }) {
    const handleChange = (e) => {
        onSearch(e.target.value);
    };

    return (
        <div className={styles.search_field}>
            <input className={styles.input_search}
                   type="text"
                   name="search_template"
                   onChange={handleChange}
                   placeholder={placeholder}
            />
        </div>
    );
}

SearchBar.propTypes = {
    placeholder: PropTypes.string,
    onSearch: PropTypes.func.isRequired
};

SearchBar.defaultProps = {
    placeholder: "Search..."
};

export default SearchBar;