import React from 'react';
import PropTypes from 'prop-types'; // Importujemy PropTypes
import styles from '../css/TemplateTile.module.css';

function TemplateTile({ name, subcategoryName, createdAt, description }) {
    return (
        <div className={styles.template_tile}>
            <div className={styles.template_name}>{name}</div>
            <div className={styles.template_date}>{subcategoryName}</div>
            <div className={styles.template_date}>{createdAt}</div>
            <div className={styles.template_first_line}>{description}</div>
            <button className={styles.delete_button} type="submit" onClick={() => console.log("Delete")}>Delete</button>
        </div>
    );
}


TemplateTile.propTypes = {
    name: PropTypes.string.isRequired,
    subcategoryName: PropTypes.string.isRequired,
    createdAt: PropTypes.string.isRequired,
    description: PropTypes.string.isRequired
};


TemplateTile.defaultProps = {
    name: "Default Name",
    subcategoryName: "Default Subcategory Name",
    createdAt: "Default Created At",
    description: "Default Description"
};

export default TemplateTile;
