"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var react_1 = require("react");
var ModeSelector = function (_a) {
    var mode = _a.mode, setMode = _a.setMode;
    return (<div className="mode-selector">
    <button onClick={function () { return setMode("left-figure"); }} disabled={mode === "left-figure"}>
      Figura Izquierda
    </button>
    <button onClick={function () { return setMode("center"); }} disabled={mode === "center"}>
      Centralizado
    </button>
    <button onClick={function () { return setMode("right-figure"); }} disabled={mode === "right-figure"}>
      Figura Derecha
    </button>
  </div>);
};
exports.default = ModeSelector;
