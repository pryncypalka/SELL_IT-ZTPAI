


import Account from "./components/subSites/Account";
import Create from "./components/subSites/Create";
import Dashboard from "./components/subSites/Dashboard";
import Login from "./components/subSites/Login";
import SignUp from "./components/subSites/SignUp";
import {Route, Routes} from "react-router-dom";

import LoggedRoutes from "./utils/LoggedRoutes";
import PrivateRoutes from "./utils/PrivateRoutes";
import Offer from "./components/subSites/Offer";

function App() {
        return (
            <>
                    <div>
                        <Routes>
                            <Route element={<LoggedRoutes />}>
                                <Route path={'/signup'} element={<SignUp/>}/>
                                <Route path={'/login'} element={<Login/>}/>
                            </Route>

                            <Route element={<PrivateRoutes />}>
                                <Route path={'/account'} element={<Account/>}/>
                                <Route path={'/create'} element={<Create/>}/>
                                <Route path={'/dashboard'} element={<Dashboard/>}/>
                                <Route path={'/offer'} element={<Offer/>}/>
                            </Route>
                        </Routes>
                    </div>
            </>
        );
}

export default App;