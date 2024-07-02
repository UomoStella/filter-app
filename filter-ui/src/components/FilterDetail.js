import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import CriteriaRow from "./CriteriaRow";
import DynamicInputField from "./DynamicInputField";
import { Type } from "../constants/constants";
import "bootstrap/dist/css/bootstrap.min.css";

const FilterDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const emptyCriteria = {
    uniqueId: crypto.randomUUID(),
    id: null,
    typeId: 1,
    conditionId: 1,
    value: null,
  };

  const [filter, setFilter] = useState(null);
  const [pageInfo, setPageInfo] = useState(null);
  const [criteriaRows, setCriteriaRows] = useState([]);
  const [initialState, setInitialState] = useState([]);

  useEffect(() => {
    fetchFilter();
  }, []);

  const fetchFilter = () => {
    axios
      .get("http://localhost:8080/api/filters/page-info")
      .then((response) => {
        setPageInfo(response.data);
      })
      .catch((error) => {
        console.error("Error fetching page info:", error);
      });

    if (!id) {
      setCriteriaRows([emptyCriteria]);
      setInitialState([emptyCriteria]);
      return;
    }

    axios
      .get(`http://localhost:8080/api/filters/${id}`)
      .then((response) => {
        const filter = response.data;
        setFilter(filter);

        const criteria = filter?.criteria.map((value) => ({
          ...value,
          uniqueId: crypto.randomUUID(),
        }));
        setCriteriaRows(criteria || [emptyCriteria]);
        setInitialState(criteria || [emptyCriteria]);
      })
      .catch((error) => {
        console.error("Error fetching filter:", error);
      });
  };

  const addCriteriaRow = () => {
    setCriteriaRows([...criteriaRows, emptyCriteria]);
  };

  const removeCriteriaRow = (uniqueId) => {
    setCriteriaRows(criteriaRows.filter((row) => row.uniqueId !== uniqueId));
  };

  const updateCriteriaRow = (uniqueId, updatedRow) => {
    setCriteriaRows(
      criteriaRows.map((row) => (row.uniqueId === uniqueId ? updatedRow : row))
    );
  };

  const handleSave = () => {
    const updatedFilter = {
      ...filter,
      criteria: criteriaRows.map(({ uniqueId, ...rest }) => rest),
    };

    axios
      .post("http://localhost:8080/api/filters", updatedFilter)
      .then((response) => {
        console.log("Filter saved:", response.data);
        navigate("/");
      })
      .catch((error) => {
        console.error("Error saving filter:", error);
      });
  };

  const handleCancel = () => {
    setCriteriaRows(initialState);
    navigate("/");
  };

  const handleNameChange = (event) => {
    setFilter({ ...filter, name: event.target.value });
  };

  const handleSelectionChange = (event) => {
    setFilter({ ...filter, selection: event.target.value });
  };

  return (
    <div className="container">
      <div className="mb-4">
        <h1>Filter Details</h1>
      </div>
      <div className="mb-3">
        <label htmlFor="filterName" className="form-label">
          Name:
        </label>
        <input
          type="text"
          className="form-control"
          id="filterName"
          value={filter?.name || ""}
          onChange={handleNameChange}
        />
      </div>
      <div className="mb-3">
        <label className="form-label">Selection:</label>
        <div>
          <div className="form-check form-check-inline">
            <input
              className="form-check-input"
              type="radio"
              name="selection"
              id="selection1"
              value="SELECTION_1"
              checked={filter?.selection === "SELECTION_1"}
              onChange={handleSelectionChange}
            />
            <label className="form-check-label" htmlFor="selection1">
              Selection 1
            </label>
          </div>
          <div className="form-check form-check-inline">
            <input
              className="form-check-input"
              type="radio"
              name="selection"
              id="selection2"
              value="SELECTION_2"
              checked={filter?.selection === "SELECTION_2"}
              onChange={handleSelectionChange}
            />
            <label className="form-check-label" htmlFor="selection2">
              Selection 2
            </label>
          </div>
          <div className="form-check form-check-inline">
            <input
              className="form-check-input"
              type="radio"
              name="selection"
              id="selection3"
              value="SELECTION_3"
              checked={filter?.selection === "SELECTION_3"}
              onChange={handleSelectionChange}
            />
            <label className="form-check-label" htmlFor="selection3">
              Selection 3
            </label>
          </div>
        </div>
      </div>
      <h3>Criteria:</h3>
      {pageInfo &&
        criteriaRows.map((row) => (
          <CriteriaRow
            key={row.uniqueId}
            row={row}
            conditionsInfo={pageInfo}
            onRemove={() => removeCriteriaRow(row.uniqueId)}
            onUpdate={updateCriteriaRow}
          />
        ))}
      <div className="text-center">
        <button className="btn btn-secondary mt-3" onClick={addCriteriaRow}>
          Add Filter
        </button>
        <div className="mt-4">
          <button className="btn btn-warning me-2" onClick={handleCancel}>
            Cancel
          </button>
          <button className="btn btn-primary" onClick={handleSave}>
            Save
          </button>
        </div>
      </div>
    </div>
  );
};

export default FilterDetail;
