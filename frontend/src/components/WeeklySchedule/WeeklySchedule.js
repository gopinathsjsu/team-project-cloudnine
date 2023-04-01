import React, { useEffect, useContext, useState } from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import { Button, IconButton } from '@mui/material';
import Checkbox from '@mui/material/Checkbox';
import { List, ListItem, ListItemText, Divider, ListItemButton, ListItemIcon } from '@mui/material';
import { style } from '@mui/material';
import { Add, Comment } from '@mui/icons-material';

import { Grid, Container } from '@mui/material';

import './WeeklySchedule.scss'
import ProfileHeader from 'components/ProfileHeader/ProfileHeader';
import util from 'util';
import Services from 'services';
import AppContext from 'context';

function WeeklySchedule(props) {
  const [data, setData] = useState(props.data);
  let [checked, setChecked] = useState([]);
  const [allClasses, setAllClasses] = useState([]);
  const [myClassIds, setMyClassIds] = useState([]);

  const { context, setContext } = useContext(AppContext);

  const services = new Services(context, setContext);

  useEffect(() => {
    setMyClassIds(data.map(session => session.id));

    services.getAllClasses(
      util.getGym(),
      (res) => {
        setAllClasses(res)
        checked = res.map(session => myClassIds.includes(session.id));
        setChecked(checked);
      }
    );
  }, [props]);

  const handleToggle = (i) => {
    checked[i] = !checked[i];
    setChecked(checked.slice(0, checked.length));
  };

  const handleOnEnrollment = () => {
    const newClasses = [];
    checked.forEach(async (bool, i) => {
      if (!bool) return;  // @TODO unenroll class for this user

      await services.getClassEnrollment(
        util.getUserId(),
        allClasses[i].id,
        null,
        null,
        true
      );

      newClasses.push(allClasses[i]);

      console.log('Enrolled in: ' + allClasses[i].id);
    });

    setData(newClasses);

    setContext({
      ...context,
      toast: util.getToast('Enrollment(s) successful', 'success')
    });

    return true;
  };

  return (
    <div className="component-weekly-schedule">

      {/* header */}
      <ProfileHeader
        title="Weekly Schedule"
        actionButton="Class"
        bottomSheetTitle="Select Classes"
        bottomSheetOnSave={handleOnEnrollment}
        layout={[4, 8]} >

        {/* bottom sheet content */}
        <List className='my-list' component="nav" aria-label="mailbox folders">
          {allClasses.map((session, i) => {
            const labelId = `checkbox-list-label-${session}`;
            return (
              <ListItem
                divider
                key={session}
                disablePadding
              >
                <ListItemButton role={undefined} onClick={() => handleToggle(i)} dense>
                  <ListItemIcon>
                    <Checkbox
                      edge="start"
                      checked={checked[i]}
                      tabIndex={-1}
                      disableRipple
                      inputProps={{ 'aria-labelledby': labelId }}
                    />
                  </ListItemIcon>
                  <ListItemText id={labelId} primary={session.className} secondary={`${util.timeify(session.startTime)} to ${util.timeify(session.endTime)}`} />
                </ListItemButton>
              </ListItem>
            );
          })}
        </List>

      </ProfileHeader>

      <Card>
        <CardContent>

          <List className='my-list' component="nav" aria-label="mailbox folders">
            {data.map(session =>
              <ListItem>
                <ListItemText primary={session.className} secondary={`${util.timeify(session.startTime)} to ${util.timeify(session.endTime)}`} />
              </ListItem>
            )}
          </List>

        </CardContent>
      </Card>

    </div>
  );
}

export default WeeklySchedule;
