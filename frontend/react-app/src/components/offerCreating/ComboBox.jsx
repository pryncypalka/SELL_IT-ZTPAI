
import PropTypes from 'prop-types';

function ComboBox({ selectLabel, selectName, options }) {
    return (
        <div className="combobox">
            <label htmlFor={selectName}>{selectLabel}:</label>
            <select name={selectName} id={selectName}>
                {options.map((option, index) => (
                    <option key={index} value={option.value}>{option.label}</option>
                ))}
            </select>
        </div>
    );
}

ComboBox.propTypes = {
    selectLabel: PropTypes.string,
    selectName: PropTypes.string,
    options: PropTypes.arrayOf(PropTypes.shape({
        value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
        label: PropTypes.string
    }))
};

ComboBox.defaultProps = {
    selectLabel: 'Select Label',
    selectName: 'selectName',
    options: [
        { value: '0', label: 'Option 1' },
        { value: '1', label: 'Option 2' }
    ]
};

export default ComboBox;
