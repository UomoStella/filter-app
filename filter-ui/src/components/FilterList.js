import React, { useState, useEffect } from "react";
import axios from "axios";
import { Modal, Button } from "react-bootstrap";
import FilterDetail from "./FilterDetail";

const FilterList = () => {
  const [filters, setFilters] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [selectedFilterId, setSelectedFilterId] = useState(null);

  useEffect(() => {
    fetchFilters();
  }, [showModal]);

  const fetchFilters = () => {
    if (!showModal) {
      axios
        .get("http://localhost:8080/api/filters")
        .then((response) => {
          setFilters(response.data);
        })
        .catch((error) => {
          console.error("Error fetching filters:", error);
        });
    }
  };

  const handleShowModal = (filterId = null) => {
    setSelectedFilterId(filterId);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
    setSelectedFilterId(null);
  };

  return (
    <div className="container mt-5">
      <h1>Filters List</h1>
      <ul className="list-group mt-3">
        {filters.map((filter) => (
          <li
            onClick={() => handleShowModal(filter.id)}
            style={{ cursor: "pointer" }}
            key={filter.id}
            className="list-group-item d-flex justify-content-between align-items-center"
          >
            <span>{filter.name}</span>
            <span className="badge bg-primary rounded-pill">
              ID: {filter.id}
            </span>
          </li>
        ))}
      </ul>

      <div className="mt-4">
        <Button onClick={() => handleShowModal()} className="btn btn-primary">
          Add Filter
        </Button>
      </div>

      <Modal show={showModal} onHide={handleCloseModal} size="lg">
        <Modal.Header closeButton>
          <Modal.Title>Filter Detail</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <FilterDetail
            filterId={selectedFilterId}
            closeModal={handleCloseModal}
          />
        </Modal.Body>
      </Modal>
    </div>
  );
};

export default FilterList;
