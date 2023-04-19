import { useContext, useEffect, useState } from 'react';

import AppContext from 'context';
import Services from 'services';

import logo from 'assets/logo.svg';
import './Home.scss';
import { Container, Grid, Typography, Card, CardContent, Divider } from '@mui/material';
import { List, ListItem, ListItemText } from '@mui/material';
import util from 'util';

function Home() {
  const { context, setContext } = useContext(AppContext);
  const [memberships, setMemberships] = useState(null);
  const [allClasses, setAllClasses] = useState(null);

  const services = new Services(context, setContext);

  useEffect(() => {
    setContext({
      ...context,
      path: ['Home']
    });

    services.getMemberships(
      (res) => setMemberships(res)
    );

    services.getAllClasses(
      util.getGym(),
      (res) => { setAllClasses(res); console.log(res) }
    );
  }, []);

  return (
    <div className="page-home">
      <Container maxWidth="md">
        <Grid container spacing={5}>
          <Grid item xs={12} className='center-h'>
            <Typography variant='h2' color="text.primary">Welcome to <Typography sx={{ display: 'initial' }} variant='h2' color="primary">Carb Crusher</Typography>.</Typography>
          </Grid>

          <Grid item xs={12} className='center-h'>
            <img src="https://i.etsystatic.com/35314956/r/il/f33b8d/3913032229/il_fullxfull.3913032229_5t5a.jpg" />
          </Grid>

          {memberships &&
            <Grid item xs={12} className='center-h'>
              <Grid container spacing={3}>
                <Grid item xs={12} className='center-h'>
                  <Typography variant='h3' color="text.primary">Memberships</Typography>
                </Grid>

                <Grid item xs={12}>
                  <Grid container spacing={2} className='center-h'>
                    {memberships.map(membership =>
                      <Grid item className='center-h'>
                        <Card>
                          <CardContent>
                            <Typography variant="h5" component="div">
                              {membership.name}
                            </Typography>
                            <Typography sx={{ mb: 1.5 }} color="text.secondary" variant='h2'>
                              {membership.price}
                            </Typography>
                            <Typography sx={{ mb: 1.5 }} color="text.secondary" variant='h7'>
                              {membership.duration}
                            </Typography>
                          </CardContent>
                        </Card>
                      </Grid>
                    )}
                  </Grid>
                </Grid>
              </Grid>
            </Grid>
          }

          <Grid item xs={12} sx={{ mt: 3 }}>
            <Divider />
          </Grid>

          {allClasses && allClasses.length != 0 &&
            <Grid item xs={12} className='center-h'>
              <Grid container spacing={3}>
                <Grid item xs={12} className='center-h'>
                  <Typography variant='h3' color="text.primary">Classes</Typography>
                </Grid>

                <Grid item xs={12}>
                  <Card>
                    <CardContent>
                      <List className='my-list' component="nav" aria-label="mailbox folders">
                        {allClasses.map((session, i) =>
                          <ListItem divider={i != allClasses.length - 1}>
                            <ListItemText primary={session.className} secondary={`${util.timeify(session.startTime)} to ${util.timeify(session.endTime)}`} />
                          </ListItem>
                        )}
                      </List>
                    </CardContent>
                  </Card>
                </Grid>
              </Grid>
            </Grid>
          }

        </Grid>
      </Container>
    </div >
  );
}

export default Home;
