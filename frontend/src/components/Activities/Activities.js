import React, { useEffect, useContext, useState } from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import Checkbox from '@mui/material/Checkbox';
import { List, ListItem, ListItemText, Divider, ListItemButton, ListItemIcon } from '@mui/material';
import { Accordion, AccordionActions, AccordionSummary, AccordionDetails } from '@mui/material';
import { FormGroup, FormControl, TextField, FormHelperText } from '@mui/material';
import { DatePicker, TimePicker, LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'
import { ExpandMore, Add } from '@mui/icons-material';

import { Grid, Container } from '@mui/material';

import './Activities.scss'
import ProfileHeader from 'components/ProfileHeader/ProfileHeader';
import util from 'util';
import Services from 'services';
import AppContext from 'context';

function Activities(props) {

  const { dayjs } = new AdapterDayjs();

  // --------------------------------

  const [data, setData] = useState(props.data);
  const [activityType, setActivityType] = useState('');
  const [startTime, setStartTime] = useState(dayjs(new Date()));
  const [endTime, setEndTime] = useState(dayjs(new Date()));

  const [errorActivityType, setErrorActivityType] = useState('');

  const [checked, setChecked] = useState([false, false, false, false]);

  const { context, setContext } = useContext(AppContext);

  // --------------------------------

  const services = new Services(context, setContext);

  // --------------------------------

  const dateRangeTitles = ['last week', 'last month', 'last 90 days'];
  const dateRangeValues = ['week', 'month', '90days'];
  const dateRangeButtonGroup = {
    titles: dateRangeTitles,
    handleClick(e) {
      services.getUserActivityByPeriod(
        util.getUserId(),
        dateRangeValues[dateRangeTitles.indexOf(e.target.innerText.trim().toLowerCase())],
        (res) => {
          setData(res);
          setContext({
            ...context,
            toast: util.getToast('Data updated', 'info')
          });
        }
      );
    }
  };

  const handleToggle = (i) => {
    checked[i] = !checked[i];
    setChecked(checked);
  };

  const validateInputs = () => {
    setErrorActivityType('');

    if (!activityType) setErrorActivityType('Activity type cannot be empty.');

    return activityType && startTime && endTime;
  }

  const handleSave = async () => {

    if (validateInputs()) {

      services.postUserActivity(
        util.getUserId(),
        util.getGym(),
        activityType,
        startTime,
        endTime,
        () => {
          data.push({ activityType, startTime, endTime });
          setData(data.slice(0, data.length));
        }
      );

      return true;
    }
    return false;
  }

  // --------------------------------

  return (
    <div className="component-activities">

      {/* header */}
      <ProfileHeader
        title="Activities"
        actionButton="Activity"
        bottomSheetTitle="Add Activity"
        bottomSheetOnSave={handleSave}
        buttonGroup={dateRangeButtonGroup}
        layout={[4, 5, 3]} >

        {/* bottom sheet content */}
        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <FormGroup>
            <Grid container spacing={2} sx={{ mt: 1 }}>
              <Grid item xs={12}>
                <FormControl error={errorActivityType} fullWidth>
                  <TextField value={activityType} error={errorActivityType} label="Activity Type" variant="outlined" onInput={(e) => setActivityType(e.target.value)} />
                  <FormHelperText>{errorActivityType}</FormHelperText>
                </FormControl>
              </Grid>
            </Grid>

            <Grid container spacing={2} sx={{ mt: 1 }}>
              <Grid item xs={6}>
                <FormControl fullWidth>
                  <TimePicker
                    label="Start Time"
                    defaultValue={dayjs(new Date())}
                    onChange={(newStartTime) => setStartTime(newStartTime.toISOString())} />
                </FormControl>
              </Grid>
              <Grid item xs={6}>
                <FormControl fullWidth>
                  <TimePicker
                    label="End Time"
                    defaultValue={dayjs(new Date())}
                    onChange={(newEndTime) => setEndTime(newEndTime.toISOString())} />
                </FormControl>
              </Grid>
            </Grid>
          </FormGroup>
        </LocalizationProvider>

      </ProfileHeader>

      {/* content */}
      {data.map(activity =>
        <Accordion>
          <AccordionSummary
            expandIcon={<ExpandMore />}
            aria-controls="panel1a-content"
            id="panel1a-header"
          >
            <Grid container spacing={2}>
              <Grid item xs={4}>
                <Typography>{activity.activityType}</Typography>
              </Grid>
              <Grid item xs={4} sx={{ textAlign: 'center' }}>
                <Typography>{util.getDuration(activity.startTime, activity.endTime)}m</Typography>
              </Grid>
              <Grid item xs={4} sx={{ textAlign: 'end' }}>
                <Typography>{util.dateify(activity.startTime)}</Typography>
              </Grid>
            </Grid>
          </AccordionSummary>
          <AccordionDetails>
            <Typography paragraph>Start time: {util.timeify(activity.startTime)}</Typography>
            <Typography paragraph>End time: {util.timeify(activity.endTime)}</Typography>
          </AccordionDetails>
        </Accordion>
      )}
    </div>
  );
}

export default Activities;
