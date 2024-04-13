
import Navbar from './components/Navbar.jsx';
import ChangeAvatar from './components/ChangeAvatar.jsx';
import LoginForm from "./components/LoginForm";
import LogoContainer from "./components/LogoContainer";
import LoginPicture from "./components/LoginPicture";
import SignUpPicture from "./components/SignUpPicture";
import SignUpForm from "./components/SignUpForm";
import ChangePassword from "./components/ChangePassword";
import BlueButton from "./components/BlueButton";
import CategoryTile from "./components/CategoryTile";
import SearchBar from "./components/SearchBar";
import OfferTile from "./components/OfferTile";
import TemplateTile from "./components/TemplateTile";
import Avatar from "./components/Avatar";
import Messages from "./components/Messages";
import PhotosPreview from "./components/offerCreating/PhotosPreview";
import OfferForm from "./components/offerCreating/OfferForm";
import ComboBox from "./components/offerCreating/ComboBox";

import Account from "./components/subSites/Account";
import Create from "./components/subSites/Create";
import Dashboard from "./components/subSites/Dashboard";
import Login from "./components/subSites/Login";
import SignUp from "./components/subSites/SignUp";
import {Route, Routes} from "react-router-dom";

function App() {
        return (
            <>
                    <div>
                            <Routes>
                                    <Route path={'/account'} element={<Account/>}/>
                                <Route path={'/login'} element={<Login/>}/>
                                <Route path={'/create'} element={<Create/>}/>
                                <Route path={'/signup'} element={<SignUp/>}/>
                                <Route path={'/dashboard'} element={<Dashboard/>}/>
                            </Routes>
                    </div>
            </>
        );
}

export default App;