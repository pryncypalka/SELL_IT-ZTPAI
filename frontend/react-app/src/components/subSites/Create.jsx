import Navbar from "../Navbar";
import CategoryTile from "../CategoryTile";
import SearchBar from "../SearchBar";
import styles from '../../css/sub_sites_css/Create.module.css';
import ItemTemplate from "../ItemTemplate";

function Create() {
    return (
        <>
            <Navbar/>
            <div className={styles.content}>
            <div className={styles.result_box}>
                <div className={styles.result_tiles_container}>
                    <SearchBar/>
                    <ItemTemplate/>
                    <ItemTemplate/>
                </div>
            </div>
            <div className={styles.categories_box}>
                <div className={styles.categories_text}>Categories</div>
                <div className={styles.category_container}>
                    <CategoryTile category={"Category"}/>
                    <CategoryTile category={"Category"}/>
                    <CategoryTile category={"Category"}/>
                    <CategoryTile category={"Category"}/>
                    <CategoryTile category={"Category"}/>
                    <CategoryTile category={"Category"}/>
                    <CategoryTile category={"Category"}/>
                    <CategoryTile category={"Category"}/>
                    <CategoryTile category={"Category"}/>
                    <CategoryTile category={"Category"}/>
                    <CategoryTile category={"Category"}/>
                    <CategoryTile category={"Category"}/>
                    <CategoryTile category={"Category"}/>
                    <CategoryTile category={"Category"}/>
                    <CategoryTile category={"Category"}/>



                </div>
            </div>
            </div>
        </>
    );
}

export default Create;
