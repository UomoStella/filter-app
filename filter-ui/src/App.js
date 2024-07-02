import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import FilterList from "./components/FilterList";
import FilterDetail from "./components/FilterDetail";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<FilterList />} />
        <Route path={"/filter/:id"} element={<FilterDetail />} />
        <Route path="/filter/" element={<FilterDetail />} />
      </Routes>
    </Router>
  );
}

export default App;
