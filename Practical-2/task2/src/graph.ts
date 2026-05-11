import Chart from "chart.js/auto";

type Coordinate = {
  x: number;
  y: number;
};

async function fetchData(): Promise<Coordinate[]> {
  try {
    debugger;
    const response = await fetch("/coordinates.json");
    const data = (await response.json()) as Coordinate[];
    return data;
  } catch (error) {
    console.error("Error fetching data:", error);
    return [];
  }
}

export async function setupGraph(element: HTMLCanvasElement) {
  const ctx = element.getContext("2d") as CanvasRenderingContext2D;
  const data = await fetchData();

  new Chart(ctx, {
    type: "line",
    data: {
      labels: data.map((point) => point.x),
      datasets: [
        {
          label: "f(x) = x^2",
          data: data.map((point) => point.y),
          fill: false,
          borderColor: "rgb(75, 192, 192)",
          tension: 0.1,
        },
      ],
    },
    options: {
      scales: {
        y: {
          beginAtZero: true,
          position: "center",
        },
      },
    },
  });
}
