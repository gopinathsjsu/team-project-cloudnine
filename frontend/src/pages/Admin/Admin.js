import React, { useEffect, useContext, useRef, useState } from 'react';
import { useNavigate } from "react-router-dom";
import Typography from '@mui/material/Typography';
import { Button, ButtonGroup, ListItemButton } from '@mui/material';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import { Accordion, AccordionActions, AccordionSummary, AccordionDetails } from '@mui/material';
import { List, ListItem, ListItemText } from '@mui/material';
import { FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import { ExpandMore, Add } from '@mui/icons-material';

import { Bar } from 'react-chartjs-2';
import 'chart.js/auto';

import AppContext from 'context';
import Services from 'services';

import './Admin.css';
import { Grid, Container } from '@mui/material';
import WeeklySchedule from 'components/WeeklySchedule/WeeklySchedule';
import Activities from 'components/Activities/Activities';
import ProfileHeader from 'components/ProfileHeader/ProfileHeader';
import util from 'util';

function Admin() {
  const buttonGroupDayWeek = {
    titles: ['day', 'week'],
    handleClick(e) {
      console.log(e.target);
      setSelectedPeriod(e.target.innerText.toLowerCase());
      setAtMount(false);
    }
  };

  const buttonGroupVisitors = {
    titles: ['hour', 'day', 'week'],
    handleClick(e) {
      console.log(e.target);
      setSelectedPeriodVisitors(e.target.innerText.toLowerCase());
      setAtMount(false);
    }
  };

  // -----------------------------------

  const [memberships, setMemberships] = useState(null);
  let [selectedMemberships, setSelectedMemberships] = useState([]);
  const [chartDataEnrollments, setChartDataEnrollments] = useState(null);
  const [chartDataVisitors, setChartDataVisitors] = useState(null);
  const [selectedClass, setSelectedClass] = useState(null);
  const [selectedPeriod, setSelectedPeriod] = useState(buttonGroupDayWeek.titles[0]);
  const [selectedPeriodVisitors, setSelectedPeriodVisitors] = useState(buttonGroupVisitors.titles[0]);
  const [dropdownDataAllClasses, setDropdownDataAllClasses] = useState({
    title: 'Class',
    data: [
      {
        id: '123',
        className: 'Swimming'
      }
    ],
    handleSelect: (e) => {
      setSelectedClass(e.target.value);
    }
  });
  const [users, setUsers] = useState(null);
  const [atMount, setAtMount] = useState(true);

  const refChart = useRef();
  const refChartVisitors = useRef();

  const { context, setContext } = useContext(AppContext);

  const navigate = useNavigate();

  // -----------------------------------

  const services = new Services(context, setContext);

  useEffect(() => {
    setContext({
      ...context,
      path: ['Admin']
    });

    services.getMemberships(
      res => {
        setMemberships(res);
      }
    );

    services.getAllClasses(
      'gym1',
      (res) => {
        dropdownDataAllClasses.data = res.map(session => ({
          id: session.id,
          className: session.className
        }));
      });

    services.getAllUsers(
      util.getGym(),
      (res) => {
        setUsers(res);
        selectedMemberships = res.map(user => user.membership)
      });
  }, []);

  useEffect(() => {
    // get all classes
    services.getEnrollments(
      selectedPeriod,
      util.getGym(),
      selectedClass,
      (resEnrollments) => {
        const chartData = {
          labels: [],
          datasets: [{
            label: 'Enrollments',
            data: [],
            borderWidth: 1
          }]
        };

        resEnrollments.forEach(enrollment => {
          chartData.labels.push(enrollment.period);
          chartData.datasets[0].data.push(enrollment.enrollmentCount);
        });

        setChartDataEnrollments(chartData);

        if (!atMount)
          setContext({
            ...context,
            toast: util.getToast('Chart updated', 'info')
          });
      }
    );
  }, [selectedPeriod, selectedClass]);

  useEffect(() => {
    services.getVisitors(
      selectedPeriodVisitors,
      util.getGym(),
      (resVisitors) => {
        const chartData = {
          labels: [],
          datasets: [{
            label: 'Visitors',
            data: [],
            borderWidth: 1
          }]
        };

        resVisitors.forEach(visitor => {
          chartData.labels.push(util.dateify(visitor.date));
          chartData.datasets[0].data.push(visitor.hours);
        });

        setChartDataVisitors(chartData);

        if (!atMount)
          setContext({
            ...context,
            toast: util.getToast('Chart updated', 'info')
          });
      });
  }, [selectedPeriodVisitors]);

  // -----------------------------------

  const handleCheckin = (user) => {
    services.postCheckin(user.id);
  };

  const handleCheckout = (user) => {
    services.postCheckout(user.id);
  };

  const handleJumpToProfile = (user) => {
    navigate('/user', {
      replace: true,
      state: {
        email: user.email
      }
    });
  };

  const handleSelectMembership = (e, user) => {
    const i = parseInt(e.target.value);

    selectedMemberships[i] = memberships[i].name;
    setSelectedMemberships(selectedMemberships.slice());

    services.postEnrollMembership(
      user.id,
      memberships[i].id
    );
  };

  // -----------------------------------

  return (
    <div className="component-admin">
      <Container maxWidth="md">

        {/* username */}
        <Grid container spacing={3}>

          {/* page title */}
          <Grid item xs={6} sx={{ display: 'flex', alignItems: 'center' }}>
            <Typography variant="h2">Control Panel</Typography>
          </Grid>

          {chartDataEnrollments &&
            <Grid item xs={12} sx={{ display: 'flex', alignItems: 'center', mt: '1rem' }}>

              {/* enrollments chart */}
              <Card sx={{ width: '100%' }}>
                <CardContent>
                  {/* card header */}
                  <Grid container spacing={2}>
                    <Grid item xs={12} sx={{ display: 'flex', alignItems: 'center' }}>
                      <ProfileHeader
                        noAction
                        title="Enrollments"
                        layout={[4, 5, 3]}
                        buttonGroup={buttonGroupDayWeek}
                        dropdownData={dropdownDataAllClasses} />
                    </Grid>
                    {/* <Grid item xs={6} sx={{ display: 'flex', alignItems: 'center', justifyContent: 'end' }}>
                    <ButtonGroup variant="outlined" aria-label="text button group">
                      {chartButtonGroup.map(button =>
                        <Button onClick={button.handleClick}>{button.title}</Button>
                      )}
                    </ButtonGroup>
                  </Grid> */}
                  </Grid>

                  {/* chart.js */}
                  <Bar ref={refChart} sx={{ mt: 1.5 }} data={chartDataEnrollments} />
                </CardContent>
              </Card>
            </Grid>
          }

          {chartDataVisitors &&
            <Grid item xs={12} sx={{ display: 'flex', alignItems: 'center', mt: '1rem' }}>

              {/* enrollments chart */}
              <Card sx={{ width: '100%' }}>
                <CardContent>
                  {/* card header */}
                  <Grid container spacing={2}>
                    <Grid item xs={12} sx={{ display: 'flex', alignItems: 'center' }}>
                      <ProfileHeader
                        noAction
                        title="Visitors"
                        layout={[4, 8]}
                        buttonGroup={buttonGroupVisitors} />
                    </Grid>
                    {/* <Grid item xs={6} sx={{ display: 'flex', alignItems: 'center', justifyContent: 'end' }}>
                    <ButtonGroup variant="outlined" aria-label="text button group">
                      {chartButtonGroup.map(button =>
                        <Button onClick={button.handleClick}>{button.title}</Button>
                      )}
                    </ButtonGroup>
                  </Grid> */}
                  </Grid>

                  {/* chart.js */}
                  <Bar ref={refChartVisitors} sx={{ mt: 1.5 }} data={chartDataVisitors} />
                </CardContent>
              </Card>
            </Grid>
          }

          {users &&
            <Grid item xs={12} sx={{ display: 'flex', alignItems: 'center', justifyContent: 'center', mt: '1rem' }}>
              <Grid spacing={0} item xs={12}>

                <Grid item xs={12}>
                  <ProfileHeader layout={[8]} noAction title="Users Controls" actionButton="Class" bottomSheetTitle="Select Classes" />
                </Grid>

                <Grid item xs={12}>
                  {users.map((user, j) =>
                    <Accordion>
                      <AccordionSummary
                        expandIcon={<ExpandMore />}
                        aria-controls="panel1a-content"
                        id="panel1a-header"
                      >
                        <Typography>{`${user.firstName} ${user.lastName}`}</Typography>
                      </AccordionSummary>
                      <AccordionDetails>
                        <List>
                          <ListItemButton onClick={() => handleCheckin(user)}>
                            <ListItemText
                              primary="Checkin"
                              secondary="Approve member checkin"
                            />
                          </ListItemButton>

                          <ListItemButton onClick={() => handleCheckout(user)}>
                            <ListItemText
                              primary="Checkout"
                              secondary="Approve member checkout"
                            />
                          </ListItemButton>

                          <Grid container sx={{ px: 2, py: 1.5 }} spacing={2}>
                            <Grid item xs={6}>
                              <ListItemText
                                primary="Free Trial"
                                secondary="Grant access to a free trial"
                              />
                            </Grid>
                            <Grid item xs={6} sx={{ display: 'flex', justifyContent: 'end' }}>
                              {memberships &&
                                <FormControl sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
                                  <InputLabel size='small' id="select-label-membership">Membership</InputLabel>
                                  <Select
                                    size='small'
                                    labelId="select-label-membership"
                                    value={selectedMemberships[j]}
                                    label="Location"
                                    onChange={(e) => handleSelectMembership(e, user)}
                                  >
                                    {memberships.map((membership, i) =>
                                      <MenuItem value={i}>{membership.name}</MenuItem>
                                    )}
                                  </Select>
                                </FormControl>
                              }
                            </Grid>
                          </Grid>

                          <ListItemButton onClick={() => handleJumpToProfile(user)}>
                            <ListItemText
                              primary="Profile"
                              secondary="Jump to profile view for this user"
                            />
                          </ListItemButton>
                        </List>
                      </AccordionDetails>
                    </Accordion>
                  )}
                </Grid>

              </Grid>
            </Grid>
          }

        </Grid>

      </Container>
    </div>
  );
}

export default Admin;
