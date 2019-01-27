import React from 'react'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import { Layout } from 'antd'
import { Home, Header, Footer } from './service'
import './App.scss'

const { Content } = Layout

export default () => {
  return (
    <Router>
      <div className="App">
        <Layout>
          <Header />
          <Content className="content-root">
            <Switch>
              <Route exact path="/" component={Home} />
            </Switch>
          </Content>
          <Footer />
        </Layout>
      </div>
    </Router>
  )
}
