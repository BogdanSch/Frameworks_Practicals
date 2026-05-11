import "./style.css";
import { setupGraph } from "./graph";

document.querySelector<HTMLDivElement>("#app")!.innerHTML = `
  <div>
    <div style="width: 800px;"><canvas id="carthesianSystem"></canvas></div>
  </div>
`;

const canvas = document.getElementById("carthesianSystem") as HTMLCanvasElement;
setupGraph(canvas);
