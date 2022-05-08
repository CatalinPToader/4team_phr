import { Routes, Route } from "react-router-dom"
import Home from "./Home"
import { VisDB } from "./pages/VisDB"

function App() {
  return (
    <div className="App">
      <h3 className="m-3 d-flex justify-content-center">Data Base</h3>
      <Routes>
        <Route path="/home" element={ <Home/> } />
        <Route path="/user" element={ <VisDB/> } />
      </Routes>
    </div>
  )
}

export default App