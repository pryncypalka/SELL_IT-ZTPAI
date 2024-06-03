import PropTypes from 'prop-types';
import styles from '../css/ItemTemplate.module.css';
import {useNavigate} from "react-router-dom";

function ItemTemplateContainer({ itemId, category, itemName, subcategory}) {
    const navigate = useNavigate();
    const handleCreate = () => {
        navigate('/offer', { state: { itemId: itemId } });
    };
  return (
              <div className={styles.result_tile} onClick={handleCreate}>
                  <div className={styles.result_category}>{`${category} / ${subcategory}`}</div>
                  <div className={styles.result_item_name}>{itemName}</div>
              </div>

  );
}

ItemTemplateContainer.propTypes = {
    category: PropTypes.string,
    itemName: PropTypes.string,
    subcategory: PropTypes.string,
};

ItemTemplateContainer.defaultProps = {
    category: 'Default Category',
    itemName: 'Default Item Name',
    subcategory: 'Default Subcategory Name',
};

export default ItemTemplateContainer;