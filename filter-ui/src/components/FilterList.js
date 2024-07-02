import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

const FilterList = () => {
  const [filters, setFilters] = useState([]);

  useEffect(() => {
    fetchFilters();
  }, []);

  const fetchFilters = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/filters");
      setFilters(response.data);
    } catch (error) {
      console.error("Error fetching filters:", error);
    }
  };

  return (
    <div className="container mt-5">
      <h1>Filters List</h1>
      <ul className="list-group mt-3">
        {filters.map((filter) => (
          <li
            key={filter.id}
            className="list-group-item d-flex justify-content-between align-items-center"
          >
            <Link to={`/filter/${filter.id}`}>{filter.name}</Link>
            <span className="badge bg-primary rounded-pill">
              ID: {filter.id}
            </span>
          </li>
        ))}
      </ul>

      <div className="mt-4">
        <Link to="/filter/" className="btn btn-primary">
          Add Filter
        </Link>
      </div>
    </div>
  );
};

export default FilterList;
