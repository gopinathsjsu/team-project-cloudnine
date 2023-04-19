import React, { useEffect, useContext, useState } from 'react';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';

import AppContext from 'context';

import './User.css';
import { Grid, Container } from '@mui/material';
import WeeklySchedule from 'components/WeeklySchedule/WeeklySchedule';
import Activities from 'components/Activities/Activities';

import ProfileComponent from 'components/Profile/Profile';
import util from 'util';
import LoginRegistration from 'components/LoginRegistration/LoginRegistration';

function User() {
  const [refresh, setRefresh] = useState(true)
  const { context, setContext } = useContext(AppContext);

  return (
    <div className="component-user">
      <ProfileComponent />
    </div>
  );
}

export default User;
