import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./includes.tsx";
import { ActivityProvider, CoordinateProvider } from "./contexts";
import App from "./App.tsx";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <CoordinateProvider>
      <ActivityProvider>
        <App />
      </ActivityProvider>
    </CoordinateProvider>
  </StrictMode>,
);
