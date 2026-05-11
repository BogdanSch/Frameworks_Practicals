import { ActivityForm, InteractiveMap } from "./components";

function App() {
  return (
    <>
      <main className="main">
        <section id="app" className="mt-5">
          <div className="container">
            <div className="app__wrap">
              <div className="text-center">
                <h1 className="app__title">Interactive Map</h1>
                <p className="app__description">
                  A simple interactive map built to track all your important
                  events.
                </p>
              </div>
              <InteractiveMap />
              <ActivityForm />
            </div>
          </div>
        </section>
      </main>
    </>
  );
}

export default App;
