import PropTypes from 'prop-types';
import styles from '../css/ItemTemplate.module.css';

function ItemTemplateContainer({ category, itemName}) {
  return (
              <div className={styles.result_tile}>
                  <div className={styles.result_category}>{category}</div>
                  <div className={styles.result_item_name}>{itemName}</div>
              </div>

  );
}

ItemTemplateContainer.propTypes = {
    category: PropTypes.string,
    itemName: PropTypes.string,
};

ItemTemplateContainer.defaultProps = {
    category: 'Default Category',
    itemName: 'Default Item Name',
};

export default ItemTemplateContainer;