import React from "react";
import { Type } from "../constants/constants";

const DynamicInputField = ({ type, value, onChange }) => {
  switch (type) {
    case Type.NUMBER:
      return (
        <input
          type="number"
          className="form-control"
          value={value}
          onChange={onChange}
          placeholder="Enter number"
        />
      );
    case Type.DATE:
      return (
        <input
          type="date"
          className="form-control"
          value={value}
          onChange={onChange}
        />
      );
    case Type.TEXT:
    default:
      return (
        <input
          type="text"
          className="form-control"
          value={value}
          onChange={onChange}
          placeholder="Enter text"
        />
      );
  }
};

export default DynamicInputField;
