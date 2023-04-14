import React from 'react';
import { Redirect } from 'react-router-dom';

const AdminRoute = ({ component: Component, userRole, ...rest }) => (
  <Route {...rest} render={(props) => (
    userRole === 'ADMIN'
      ? <Component {...props} />
      : <Redirect to='/' />
  )} />
);

export default AdminRoute;