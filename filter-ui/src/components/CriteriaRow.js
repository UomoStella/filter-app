import React, { useState, useEffect } from "react";
import DynamicInputField from "./DynamicInputField";

const CriteriaRow = ({ row, conditionsInfo, onRemove, onUpdate }) => {
  const [typeId, setTypeId] = useState(
    row.typeId ? row.typeId : conditionsInfo[0]?.typeId
  );
  const [type, setType] = useState(null);
  const [condition, setCondition] = useState(row);
  const [filteredConditions, setFilteredConditions] = useState([]);

  useEffect(() => {
    if (conditionsInfo) {
      const initialConditions = conditionsInfo.find(
        (ci) => ci.typeId == typeId
      );
      setFilteredConditions(
        initialConditions ? initialConditions.conditions : []
      );
      setType(initialConditions?.type);
    }
  }, [typeId, conditionsInfo]);

  useEffect(() => {
    onUpdate(row.uniqueId, condition);
  }, [condition]);

  const handleTypeChange = (e) => {
    setCondition({ ...condition, typeId: e.target.value });
    setTypeId(e.target.value);
  };

  const handleConditionChange = (e) => {
    setCondition({ ...condition, conditionId: e.target.value });
  };

  const handleValueChange = (e) => {
    setCondition({ ...condition, value: e.target.value });
  };

  return (
    conditionsInfo && (
      <div className="row mb-3">
        <div className="col-md-4">
          <select
            name="type"
            className="form-select"
            value={condition.typeId}
            onChange={handleTypeChange}
          >
            {conditionsInfo.map((ci) => (
              <option key={ci.typeId} value={ci.typeId}>
                {ci.typeName}
              </option>
            ))}
          </select>
        </div>
        <div className="col-md-3">
          <select
            name="condition"
            className="form-select"
            value={condition.conditionId}
            onChange={handleConditionChange}
          >
            {filteredConditions.map((cond) => (
              <option key={cond.id} value={cond.id}>
                {cond.name}
              </option>
            ))}
          </select>
        </div>
        <div className="col-md">
          <DynamicInputField
            type={type}
            value={condition.value}
            onChange={handleValueChange}
          />
        </div>
        <div className="col-md-1">
          <button className="btn btn-remove" onClick={onRemove}>
            <span>—</span>
          </button>
        </div>
      </div>
    )
  );
};

export default CriteriaRow;
