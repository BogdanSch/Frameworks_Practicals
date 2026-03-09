const quoteElement = document.getElementById("quote");

fetch("./quotes.json")
  .then((response) => response.json())
  .then((quotes) => {
    const quote = quotes[Math.floor(Math.random() * quotes.length)];
    quoteElement.textContent = quote;
  })
  .catch((error) => {
    console.error("Error fetching quotes:", error);
    quoteElement.textContent = "Failed to load quote.";
  });
