import React, { useState } from 'react';

function SearchBar({ placeholder }) {
    const [searchTerm, setSearchTerm] = useState('');

    const handleChange = (e) => {
        setSearchTerm(e.target.value);
        // Tutaj możesz umieścić logikę wyszukiwania, jeśli chcesz ją obsłużyć na bieżąco
    };

    return (
        <div className="search_field">
            <input
                type="text"
                name="search_template"
                value={searchTerm}
                onChange={handleChange}
                placeholder={placeholder} // Placeholder jako parametr funkcji
            />
        </div>
    );
}

export default SearchBar;