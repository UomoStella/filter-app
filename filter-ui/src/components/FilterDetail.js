import React, { useState, useEffect } from "react";
import axios from "axios";
import CriteriaRow from "./CriteriaRow";
import "bootstrap/dist/css/bootstrap.min.css";

const FilterDetail = ({ filterId, closeModal }) => {
  const emptyCriteria = {
    uniqueId: crypto.randomUUID(),
    id: null,
    typeId: "11111111-1111-1111-1111-111111111111",
    conditionId: "44444444-4444-4444-4444-444444444444",
    value: null,
  };

  const [filter, setFilter] = useState({ name: "", selection: "SELECTION_1" });
  const [pageInfo, setPageInfo] = useState(null);
  const [criteriaRows, setCriteriaRows] = useState([]);
  const [errors, setErrors] = useState({ name: "", criteria: "" });

  useEffect(() => {
    fetchFilter();
  }, [filterId]);

  const fetchFilter = () => {
    axios
      .get("http://localhost:8080/api/filters/page-info")
      .then((response) => {
        setPageInfo(response.data);
      })
      .catch((error) => {
        console.error("Error fetching page info:", error);
      });

    if (!filterId) {
      setCriteriaRows([emptyCriteria]);
      return;
    }

    axios
      .get(`http://localhost:8080/api/filters/${filterId}`)
      .then((response) => {
        const filter = response.data;
        setFilter(filter);

        const criteria = filter?.criteria.map((value) => ({
          ...value,
          uniqueId: crypto.randomUUID(),
        }));
        setCriteriaRows(criteria || [emptyCriteria]);
      })
      .catch((error) => {
        console.error("Error fetching filter:", error);
      });
  };

  const addCriteriaRow = () => {
    setCriteriaRows([...criteriaRows, emptyCriteria]);
    if (errors.criteria) {
      setErrors({ ...errors, criteria: "" });
    }
  };

  const removeCriteriaRow = (uniqueId) => {
    if (criteriaRows.length === 1) {
      setErrors({ ...errors, criteria: "At least one criteria is required." });
      return;
    }
    const updatedRows = criteriaRows.filter((row) => row.uniqueId !== uniqueId);
    setCriteriaRows(updatedRows);
  };

  const updateCriteriaRow = (uniqueId, updatedRow) => {
    setCriteriaRows(
      criteriaRows.map((row) => (row.uniqueId === uniqueId ? updatedRow : row))
    );
  };

  const validateForm = () => {
    const newErrors = { name: "", criteria: "" };

    if (!filter.name) {
      newErrors.name = "Name is required.";
    }
    if (criteriaRows.length === 0) {
      newErrors.criteria = "At least one criteria is required.";
    }

    const emptyValueCriteria = criteriaRows.filter(
      (criteria) => criteria?.value === null
    );
    if (emptyValueCriteria.length !== 0) {
      newErrors.criteria = "Value must be filled in.";
    }

    setErrors(newErrors);
    return !newErrors.name && !newErrors.criteria;
  };

  const handleSave = () => {
    if (!validateForm()) {
      return;
    }

    const updatedFilter = {
      ...filter,
      criteria: criteriaRows.map(({ uniqueId, ...rest }) => rest),
    };

    axios
      .post("http://localhost:8080/api/filters", updatedFilter)
      .then((response) => {
        console.log("Filter saved:", response.data);
        closeModal();
      })
      .catch((error) => {
        console.error("Error saving filter:", error);
      });
  };

  const handleCancel = () => {
    closeModal();
  };

  const handleNameChange = (event) => {
    setFilter({ ...filter, name: event.target.value });
    if (errors.name) {
      setErrors({ ...errors, name: "" });
    }
  };

  const handleSelectionChange = (event) => {
    setFilter({ ...filter, selection: event.target.value });
  };

  return (
    <>
      <div className="modal-filter-content">
        <div class="row">
          <label for="filterName" class="col-sm-2 col-form-label">
            FilterName:
          </label>
          <div class="col-sm-10">
            <div className="row">
              <div className="col-md-4">
                <input
                  type="text"
                  className={`form-control ${errors.name ? "is-invalid" : ""}`}
                  id="filterName"
                  value={filter?.name || ""}
                  onChange={handleNameChange}
                />
              </div>
              {errors.name && (
                <div className="invalid-feedback">{errors.name}</div>
              )}
            </div>
          </div>
        </div>

        <div class="mt-4 row">
          <div className="col-sm-2">
            <label class="col-form-label">Criteria:</label>
          </div>
          <div className="col-sm-10">
            {pageInfo &&
              criteriaRows &&
              criteriaRows.map((row) => (
                <CriteriaRow
                  key={row.uniqueId}
                  row={row}
                  conditionsInfo={pageInfo}
                  onRemove={() => removeCriteriaRow(row.uniqueId)}
                  onUpdate={updateCriteriaRow}
                />
              ))}
            {errors.criteria && (
              <div className="alert alert-danger mt-3">{errors.criteria}</div>
            )}
            {pageInfo && (
              <div className="text-center">
                <button className="btn btn-secondary" onClick={addCriteriaRow}>
                  <span>+</span> Add ROW
                </button>
              </div>
            )}
          </div>
        </div>

        <div className="mt-4 row">
          <div className="col-sm-2">
            <label class="form-label">Selection:</label>
          </div>
          <div class="col-sm-10">
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
      </div>

      <div className="modal-filter-footer mt-2">
        <button className="btn btn-secondary me-4" onClick={handleCancel}>
          Cancel
        </button>
        <button className="btn btn-primary" onClick={handleSave}>
          Save
        </button>
      </div>
    </>
  );
};

export default FilterDetail;
