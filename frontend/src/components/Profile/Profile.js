import React, { useEffect, useContext, useState, useRef } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';

import AppContext from 'context';
import Services from 'services';

import './Profile.css';
import { Grid, Container, Box } from '@mui/material';
import WeeklySchedule from 'components/WeeklySchedule/WeeklySchedule';
import Activities from 'components/Activities/Activities';
import util from 'util';
import Checkins from 'components/Checkins/Checkins';
import Chart from 'components/Chart/Chart';

function Profile(props) {
  const [profile, setProfile] = useState(null);

  const isViewerAdmin = useLocation().state ?? false;
  const email = useLocation().state?.email;

  const { context, setContext } = useContext(AppContext);

  const services = new Services(context, setContext);

  useEffect(() => {
    setContext({
      ...context,
      path: [...(isViewerAdmin ? ['Admin'] : []), `${isViewerAdmin ? 'User ' : ''}Profile`]
    });

    services.getProfile(
      email ?? util.getEmail(),
      (res) => {
        setProfile(res);
      }
    );

    console.log('profile', profile);

  }, []);

  const navigate = useNavigate();

  // --------------------------------------

  const handleSignOut = () => {
    util.removeUser();
    setContext({
      ...context,
      toast: util.getToast("You've been logged out", 'info'),
      isAdmin: false,
    });
    navigate('/home');
  };

  const getLastCheckinTime = () => {
    const lastObj = profile.checkInCheckOutList[profile.checkInCheckOutList.length - 1];
    if (profile.checkInCheckOutList)
      return `${util.timeify(lastObj.checkInTime)} on ${util.dateify(lastObj.checkInTime)}`;
    else
      return '-';
  }

  return (
    <div className="component-profile">
      {profile &&
        <Box>
          <Container maxWidth="md">
            <Grid container spacing={2}>
              {/* name and sign out button */}
              <Grid item xs={6} sx={{ display: 'flex', alignItems: 'center' }}>
                <Typography variant="h2">{isViewerAdmin ? `${profile.firstName} ${profile.lastName}` : `Hello, ${profile.firstName}`}</Typography>
              </Grid>
              {!isViewerAdmin &&
                <Grid item xs={6} sx={{ display: 'flex', alignItems: 'center', justifyContent: 'end' }}>
                  <Button variant="contained" onClick={handleSignOut}>sign out</Button>
                </Grid>
              }
            </Grid>

            {/* email */}
            <Grid container spacing={2} sx={{ mt: '1rem' }}>
              <Grid item xs={6}>
                <Typography>Email</Typography>
                <Typography variant='h5'>{profile.email}</Typography>
              </Grid>
            </Grid>

            {/* membership */}
            <Grid container spacing={2} sx={{ mt: '1rem' }}>
              <Grid item xs={6}>
                <Typography>Active Membership</Typography>
                <Typography variant='h5'>{(context.isAdmin && !isViewerAdmin) ? 'Unlimited' : (profile.membership?.name ?? '-')}</Typography>
              </Grid>
            </Grid>

            {/* last checked-in */}
            {!(context.isAdmin && !isViewerAdmin) &&
              profile.checkInCheckOutList && profile.checkInCheckOutList.length != 0 &&
              <Grid container spacing={2} sx={{ mt: '1rem' }}>
                <Grid item xs={6}>
                  <Typography>Last Checkin</Typography>
                  <Typography variant='h5'>{getLastCheckinTime()}</Typography>
                </Grid>
              </Grid>
            }

            {/* weekly schedule */}
            {profile.enrolledClasses &&
              <Grid container spacing={2} sx={{ mt: '2rem' }}>
                <Grid item xs={12}>
                  <WeeklySchedule data={profile.enrolledClasses} />
                </Grid>
              </Grid>
            }

            {/* activities */}
            {profile.activities &&
              <Grid container spacing={2} sx={{ mt: '2rem' }}>
                <Grid item xs={12}>
                  <Activities data={profile.activities} />
                </Grid>
              </Grid>
            }

            {/* checkins */}
            {profile.checkInCheckOutList && profile.checkInCheckOutList.length != 0 &&
              <Grid container spacing={2} sx={{ mt: '2rem' }}>
                <Grid item xs={12}>
                  <Checkins data={profile.checkInCheckOutList} />
                </Grid>
              </Grid>
            }

            {/* time spent hours */}
            {isViewerAdmin &&
              <Grid container spacing={2} sx={{ mt: '2rem' }}>
                <Grid item xs={12}>
                  <Chart profile={profile} />
                </Grid>
              </Grid>
            }
          </Container>
        </Box>
      }
    </div>
  );
}

export default Profile;
