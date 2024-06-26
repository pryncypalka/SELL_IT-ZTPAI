import React from 'react';
import PropTypes from 'prop-types';
import styles from '../css/TemplateTile.module.css';
import axios from 'axios';
import authHeader from '../service/auth-header';
import { useNavigate} from 'react-router-dom';

function TemplateTile({templateId,  name, subcategoryName, createdAt, description , categoryName}) {
    const navigate = useNavigate();

    const handleDelete = (event) => {
        event.stopPropagation();

        if (window.confirm('Are you sure you want to delete this template?')) {
            axios.delete(`http://localhost:8080/api/template/delete/${templateId}`, { headers: authHeader() })
                .then(response => {
                    console.log('Template deleted successfully');
                    window.location.reload();

                })
                .catch(error => {
                    console.error('There was an error deleting the template!', error);
                });
        }
    };

    const handleInspect = () => {
        navigate('/offerInspect', { state: { id: templateId, isOffer: false } });
    };

    return (
        <div className={styles.template_tile} onClick={handleInspect}>
            <div className={styles.template_name}>{name}</div>
            <div className={styles.template_date}>{`${categoryName} / ${subcategoryName}`}</div>
            <div className={styles.template_date}>{createdAt}</div>
            <div className={styles.template_first_line}>{description}</div>
            <button className={styles.delete_button} type="submit" onClick={handleDelete}>Delete</button>
        </div>
    );
}


TemplateTile.propTypes = {
    templateId: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    subcategoryName: PropTypes.string.isRequired,
    categoryName: PropTypes.string.isRequired,
    createdAt: PropTypes.string.isRequired,
    description: PropTypes.string.isRequired
};


TemplateTile.defaultProps = {
    templateId: 0,
    name: "Default Name",
    subcategoryName: "Default Subcategory Name",
    categoryName: "Default Category Name",
    createdAt: "Default Created At",
    description: "Default Description"
};

export default TemplateTile;
