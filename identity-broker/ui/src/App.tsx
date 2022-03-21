import { HashRouter as Router, Route, Switch } from 'react-router-dom'
import ChooseIdentityProviderPage from './view/ChooseIdentityProviderPage/ChooseIdentityProviderPage'

const App = () => {
  return (
    <Router>
      <Switch>
        <Route path="/">
          <ChooseIdentityProviderPage />
        </Route>
      </Switch>
    </Router>
  )
}

export default App
